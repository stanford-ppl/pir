package pir
package mapper

import pir.node._
import pir.util._
import spade.param._
import prism.collection.immutable._
import prism.graph._
import scala.collection.mutable

class MemoryPruner(implicit compiler:PIR) extends CUPruner with BankPartitioner {

  override def getCosts(x:Any):List[Cost[_]] = List(x.getCost[SRAMCost])

  override def recover(x:EOption[CUMap]):EOption[CUMap] = {
    x match {
      case Left(f@InvalidFactorGraph(fg:CUMap, k:CUMap.K)) =>
        val kcost = getCosts(k).head.as[SRAMCost]
        val vs = fg.freeValuesOf(k)
        val vcost = assertOne(vs.map { getCosts(_) }, s"sramCost").head.as[SRAMCost]
        dbg(s"Recover $k")
        dbg(s"kcost=$kcost")
        dbg(s"vcost=$vcost")
        val mem = quoteSrcCtx(k.collectDown[Memory]().head)
        val ks = split(k, kcost, vcost).toSet
        info(s"Split $k $mem into ${ks.size} CUs $kcost")
        //breakPoint(s"$k")
        newFG(fg, k, ks, vs)
      case x => super.recover(x)
    }
  }

  def getAccessPattern(mem:Memory) = {
    mem.accesses.map { access =>
      access.as[FlatBankedAccess].offset.collect[Shuffle]().flatMap { shuffle =>
        shuffle.from.T match {
          case Const(c:List[_]) => Some(c.as[List[Int]])
          case Const(c:Int) => Some(List(c))
          case _ => None
        }
      }
    }.flatten.distinct
  }

  def split(k:CUMap.K, kcost:SRAMCost, vcost:SRAMCost):List[GlobalContainer] = dbgblk(s"split($k)"){
    val mem = k.collectDown[Memory]().head
    val (totalBanks, bankPerCU, numCU, sizePerBank, bankMult) = splitBanks(kcost, vcost)

    if (bankMult > 1) {
      mem.accesses.foreach { access =>
        updateAddrCalc(access.as[FlatBankedAccess], bankMult, sizePerBank)
      }
      val banks = mem.banks.get
      mem.banks.reset
      mem.banks(banks :+ bankMult)
    }

    val accessGrps = getAccessPattern(mem)
    val parts = partitionBanks(accessGrps, totalBanks, bankPerCU).toList
    dbg(s"parts:")
    parts.foreach { part =>
      dbg(part.mkString(","))
    }

    val nodes = k.descendentTree
    val toBanks = nodes.collect { case s:Shuffle => s.to.T }
    val mappings = parts.map { bankids =>
      val mapping = mutable.Map[IR,IR]()
      mapping ++= toBanks.map { to => to -> stage(Const(bankids.toList)).mirrorMetas(to) }
      mapping += (mem -> mirror[Memory](mem).bankids(bankids.toList, reset=true).numPart(numCU)) 
      val mm = mapping(mem).as[Memory]
      within(pirTop) { mirrorAll(nodes, mapping=mapping) }
    }
    val mks = mappings.map { _(k).as[GlobalContainer] }
   
    // Merge bankReads of multiple partitions
    val bankReads = k.collectDown[FlatBankedRead]()
    bankReads.foreach { bankRead =>
      //breakPoint(s"$k, $bankRead")
      mergeReads(k, mem, bankRead, mappings)
    }
    removeNodes(k.collectDown[TokenRead]())
    free(k)
    //breakPoint(s"$k, $mks")
    mks.foreach { mk => free(mk.collectChildren[GlobalOutput]) }
    mks
  }

  def visitIn(n:PIRNode) = n match {
    case n:BufferRead => n.in.neighbors.toList
    case n:BufferWrite => n.data.neighbors.toList
    case n => visitGlobalIn(n)
  }

  def updateAddrCalc(access:FlatBankedAccess, bankMult:Int, sizePerBank:Int) = {
    val ofstShuffle = access.offset.collect[Shuffle]().groupBy { _.getCtrl }
    val dataShuffles = access.to[FlatBankedWrite].map { access =>
      access.data.collect[Shuffle]().groupBy { _.getCtrl }
    }
    val readShuffles = access.to[FlatBankedRead].map { access =>
      access.out.collect[Shuffle]().groupBy { _.getCtrl }
    }
    ofstShuffle.foreach { case (ctrl, List(ofstShuffle)) =>
      val bank = ofstShuffle.from.singleConnected.get
      val offset = ofstShuffle.base.singleConnected.get
      within(ofstShuffle.parent.get, ofstShuffle.getCtrl) {
        val bm = allocConst(bankMult, tp=Some(Fix(true,32,0)))
        val bs = allocConst(sizePerBank, tp=Some(Fix(true,32,0)))
        val newOfst = stage(OpDef(FixMod).addInput(offset, bs).out)
        val newBank = stage(OpDef(FixDiv).addInput(offset, bs).out)
        val newFlatBank = stage(OpDef(FixFMA).addInput(bank, bm, newBank).out)
        swapConnection(ofstShuffle.base, offset, newOfst)
        swapConnection(ofstShuffle.from, bank, newFlatBank)
        dataShuffles.foreach { dataShuffles =>
          val dataShuffle = assertOne(dataShuffles(ctrl), s"shuffle for $ctrl")
          swapConnection(dataShuffle.from, bank, newFlatBank)
        }
        readShuffles.foreach { readShuffles =>
          val readShuffle = assertOne(readShuffles(ctrl), s"shuffle for $ctrl")
          swapConnection(readShuffle.to, readShuffle.to.singleConnected.get, newFlatBank)
          bufferInput(readShuffle.to)
          dupDeps(readShuffle.ctx.get, from=Some(ofstShuffle.ctx.get))
        }
      }
    }
  }

  def mergeReads(k:CUMap.K, mem:Memory, br:FlatBankedRead, mappings:List[mutable.Map[IR, IR]]) = dbgblk(s"mergeReads($k, $br)") {
    val reads = br.collect[BufferRead](visitGlobalOut _)
    reads.groupBy { _.global.get }.foreach { case (global, reads) =>
      reads.groupBy { _.ctx.get }.foreach { case (ctx, reads) =>
        val read = assertOne(reads, s"reads of $br in $ctx")
        val rctrl = read.ctrl.get
        within(ctx, rctrl) {
          read.out.T.foreach { deped =>
            dbgblk(s"Merge $read => $deped in $ctx") {
              val (toSwap:Output[PIRNode], toMerge) = deped match {
                case Unbox(shuffle:Shuffle, from, Const(toBanks), base) => 
                  dbg(s"Read by $shuffle.to(Const($toBanks))")
                  val toMerge = mappings.map { mapping => 
                    val mmem = mapping(mem).as[Memory]
                    val mbr = mapping(br).as[FlatBankedRead]
                    val fromBanks = mmem.bankids.get
                    if (fromBanks == toBanks) {
                      mbr.out
                    } else {
                      stage(Shuffle(0).base(mbr.out).from(allocConst(fromBanks)).to(allocConst(toBanks))).out
                    }
                  }
                  (shuffle.out, toMerge)
                case Unbox(shuffle:Shuffle, from, to, base) => 
                  dbg(s"Read by $shuffle with non-constant to=$to")
                  val toMerge = mappings.map { mapping => 
                    val mmem = mapping(mem).as[Memory]
                    val mbr = mapping(br).as[FlatBankedRead]
                    val fromBanks = mmem.bankids.get
                    val mbankAddr = shuffle.to.collect[BufferWrite](visitIn _).headOption.map { bout =>
                      val bankAddr = bout.data.T
                      dbg(s"bout=$bout bankAddr=$bankAddr")
                      mapping(bankAddr)
                    }
                    val mto = mbankAddr.getOrElse { to }
                    dbg(s"mbankAddr=$mbankAddr, mto=$mto")
                    val mshuffle = stage(Shuffle(0).base(mbr.out).from(allocConst(fromBanks)).to(mto))
                    mshuffle.out
                  }
                  (shuffle.out, toMerge)
                //case out => 
                  //err(s"Read by non Shuffle $out")
                  //// Shuffle eliminated, which means bankAddr was constant and was
                  //// original bankIds. Likely from capacity splitting.
                  //val toMerge = mappings.map { mapping => 
                    //val mmem = mapping(mem).as[Memory]
                    //val mbr = mapping(br).as[FlatBankedRead]
                    //val fromBanks = mmem.bankids.get
                    //val toBanks = mem.bankids.get
                    //stage(Shuffle(0).base(mbr.out).from(allocConst(fromBanks)).to(allocConst(toBanks))).out
                  //}
                  //(read.out, toMerge)
              }

              var red:List[Output[PIRNode]] = toMerge
              while(red.size > 1) {
                red = red.sliding(2,2).map{ 
                  case List(o1, o2) => stage(OpDef(FixOr).addInput(o1, o2)).out
                  case List(o1) => o1
                }.toList
              }
              val List(out) = red.toList
              swapOutput(toSwap, out)
            }
          }
          bufferInput(ctx)
        }
        dupDeps(ctx, from=None).values.foreach { _.as[PIRNode].ctrl(rctrl,reset=true) }
      }
    }
  }
}

trait BankPartitioner extends Logging {

  def splitBanks(kcost:SRAMCost, vcost:SRAMCost) = {
    var sizePerBank = kcost.size /! kcost.bank
    var cuPerBank = sizePerBank /! vcost.size
    var bankPerCU = Math.min(vcost.size / sizePerBank, vcost.bank)
    val (numCU, bankMult) = if (bankPerCU < 1) {
      val numCU = cuPerBank * kcost.bank
      val bankMult = cuPerBank
      (numCU, bankMult)
    } else {
      val numCU = kcost.bank /! bankPerCU
      (numCU, 1)
    }
    val totalBanks = kcost.bank * bankMult  
    sizePerBank = kcost.size /! totalBanks
    bankPerCU = Math.min(vcost.size / sizePerBank, vcost.bank)

    dbg(s"totalBanks=$totalBanks, numCU=$numCU, bankMult=$bankMult, bankPerCU=$bankPerCU")
    (totalBanks, bankPerCU, numCU, sizePerBank, bankMult)
  }

  // Objective: 
  // Partition totalBanks into subgroups such that each group cannot exceed bankPerCU while 
  // 1. minimizing # of groups
  // 2. minimizing # of groups accessed by access bundles
  def partitionBanks(accessGroup:List[List[Int]], totalBanks:Int, bankPerCU:Int):Set[List[Int]] = {
    // ListBuffer[List[Int]]: List of staticially analyzed access bundle.
    dbg(s"accessGroup:")
    accessGroup.foreach { access =>
      dbg(s"${access.mkString(",")}")
    }
    
    // Map [AccessGroup, List[Bank]]
    val touchedBy = (0 until totalBanks).groupBy { bank =>
      accessGroup.zipWithIndex.flatMap { case (banks, accessId) => if (banks.contains(bank)) Some(accessId) else None }
    }
    val partition = touchedBy.map { case (aids, banks) => (aids, banks) }

    // TODO: use union find to speedup this
    var groups:Set[List[Int]] = partition.flatMap { case (aids, banks) =>
      dbg(s"aids:$aids banks:$banks ")
      if (aids.nonEmpty)  {
        assert(banks.size <= bankPerCU, s"aids=$aids, banks=$banks, bankPerCU:$bankPerCU")
        List(banks.toList)
      } else {
        banks.grouped(bankPerCU).map{_.toList}.toList
      }
    }.toSet

    def groupsOf(aid:Int):Set[List[Int]] = {
      accessGroup(aid).map { bank => groups.find {_.contains(bank) }.get }.toSet
    }

    (0 until accessGroup.size).sortBy { aid => groupsOf(aid).size }.foreach { aid =>
      val accessGrps = groupsOf(aid)
      groups = merge(accessGrps, bankPerCU) ++ groups.filterNot { accessGrps.contains(_) }
    }

    groups = merge(groups, bankPerCU)

    groups
  }

  private def merge(unmergable:Set[List[Int]], mergable:Set[List[Int]], sizeLimit:Int):Set[List[Int]] = {
    val group = if (mergable.size == 0) unmergable else {
      val head = mergable.maxBy { _.size }
      val rest = mergable - head
      rest.find { _.size + head.size <= sizeLimit }.fold {
        merge(unmergable + head, rest, sizeLimit)
      } { mergeWith =>
        val merged = head ++ mergeWith
        if (head.size + mergeWith.size == sizeLimit) {
          merge(unmergable + merged, rest.filterNot { _ == mergeWith}, sizeLimit)
        } else {
          merge(unmergable, rest.map { case `mergeWith` => merged; case grp => grp }, sizeLimit)
        }
      }
    }
    group.map { _.sorted }
  }

  // Merge lists in list with sizeLimit for each sublist until they cannot be further merged
  def merge(list:Set[List[Int]], sizeLimit:Int):Set[List[Int]] = {
    val (unmergable, mergable) = list.partition { _.size >= sizeLimit }
    merge(unmergable, mergable, sizeLimit)
  }

}

