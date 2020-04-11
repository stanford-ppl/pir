package pir
package mapper

import pir.node._
import pir.util._
import spade.param._
import prism.collection.immutable._
import prism.graph._
import scala.collection.mutable

class MemoryPruner(implicit compiler:PIR) extends CUPruner with BankPartitioner with MemoryComputePartitioner {

  override def getCosts(x:Any):List[Cost[_]] = x match {
    case global:GlobalContainer =>
      global.to[MemoryContainer].map { x =>
        List(x.getCost[SRAMCost], x.getCost[StageCost], x.getCost[InputCost], x.getCost[OutputCost])
      }.getOrElse(Nil)
    case _ => 
      List(x.getCost[SRAMCost], x.getCost[StageCost], x.getCost[InputCost], x.getCost[OutputCost])
  }

  override def recover(x:EOption[CUMap]):EOption[CUMap] = {
    x match {
      case Left(f@InvalidFactorGraph(fg:CUMap, k:CUMap.K)) =>
        val kcost = getCosts(k)
        val vs = fg.freeValuesOf(k)
        dbg(s"Recover $k ${quoteSrcCtx(k)}")
        val vcost = assertOne(vs.map { getCosts(_) }, s"MemoryCost")
        dbg(s"kcost=$kcost")
        dbg(s"vcost=$vcost")
        val mem = quoteSrcCtx(k.collectDown[Memory]().head)
        val (ms, cs) = split(k, kcost, vcost)
        info(s"Split $k $mem into ${ms.size} Memory CUs and ${cs.size} Compute CUs $kcost")
        //breakPoint(s"$k")
        Right(fg.mapFreeMap { _ - k ++ ((ms, vs)) ++ ((cs, getAvailableCUs.toSet)) })
      case x => super.recover(x)
    }
  }

  def split(k:CUMap.K, kcost:List[Cost[_]], vcost:List[Cost[_]]):(Set[GlobalContainer],Set[GlobalContainer]) = dbgblk(s"split($k)"){
    val mem = k.collectDown[Memory]().head
    val kMemCost::kCompCost = kcost
    val vMemCost::vCompCost = vcost
    val (totalBanks, bankPerCU, numCU, sizePerBank, bankMult) = splitBanks(kMemCost.as[SRAMCost], vMemCost.as[SRAMCost])

    val memNotFit = notFit(kMemCost, vMemCost)
    val compNotFit = notFit(kCompCost, vCompCost)

    if (memNotFit) {
      mem match {
        case mem:SRAM =>
        case mem:LUT =>
        case mem => 
          err(s"${quoteSrcCtx(mem)} exceeds memory capacity but is not splittable!")
      }
    }

    // Capacity Splitting. Update bank calculation
    if (memNotFit && bankMult > 1) {
      mem.accesses.foreach { access =>
        updateAddrCalc(access.as[FlatBankedAccess], bankMult, sizePerBank)
      }
      val banks = mem.banks.get
      mem.banks.reset
      mem.banks(banks :+ bankMult)
    }

    // Splitting address calculation
    val globs1 = if (compNotFit) { split(k, vCompCost) } else Set.empty[GlobalContainer]

    // Partition Memory
    val globs2 = if (memNotFit) splitMemoryContainer(k,totalBanks,bankPerCU,numCU) else Set(k)

    (globs1.toSet ++ globs2).partition { _.isInstanceOf[MemoryContainer] }
  }

  def getAccessPattern(mem:Memory) = {
    mem.accesses.flatMap { access =>
      val shuffles = access.as[FlatBankedAccess].offset.collect[Shuffle](logger=Some(this), visitFunc=visitIn)
      dbg(s"shuffles=${shuffles}")
      shuffles.foreach { shuffle => 
        assert(shuffle.name.v == Some("offsetShuffle"), s"getAccessPattern of $access found non offset shuffle ${shuffle}")
      }
      val pattern = shuffles.flatMap { shuffle =>
        shuffle.from.T match {
          case Const(c:List[_]) => Some(c.as[List[Int]])
          case Const(c:Int) => Some(List(c))
          case _ => None
        }
      }
      if (pattern.isEmpty) None 
      else Some(access -> pattern.flatten.distinct)
    }.toMap
  }

  def splitMemoryContainer(k:CUMap.K, totalBanks:Int, bankPerCU:Int, numCU:Int):Set[GlobalContainer] = {
    val mem = k.collectDown[Memory]().head
    val accessPattern = getAccessPattern(mem)
    dbg(s"accessPattern")
    accessPattern.foreach { case (access, pattern) =>
      dbg(s"${pattern.mkString(",")} ${quoteSrcCtx(access)}")
    }
    val parts = partitionBanks(accessPattern, totalBanks, bankPerCU).toList
    assert(parts.size == numCU, s"parts.size=${parts.size} numCU=$numCU")
    dbg(s"parts:")
    parts.foreach { part =>
      dbg(part.mkString(","))
    }

    val nodes = k.descendentTree.toList
    val toBanks = mem.accesses.flatMap { access =>
      val shuffles = access match {
        case access:FlatBankedRead =>
          access.offset.collect[Shuffle](visitFunc=visitIn _)
        case access:FlatBankedWrite =>
          access.data.collect[Shuffle](visitFunc=visitIn _) ++ access.offset.collect[Shuffle](visitFunc=visitIn _)
        case access => Nil
      }
      dbg(s"access=${access} shuffles=${shuffles}")
      shuffles.map { s => s.to.T.to[Const].getOrElse(bug(s"$s.to is not constant ${s.to.T}")) }
    }.distinct
    val mappings = parts.map { bankids =>
      within(pirTop) { 
        val mapping = mutable.Map[IR,IR]()
        val mmem = mirror[Memory](mem)
        mirrorMetas(mem, mmem)
        mapping += (mem -> mmem) 
        mmem.bankids(bankids.toList,reset=true) // Need to set to infer vec for other mirrored nodes
        mmem.numPart(numCU,reset=true)
        withMirrorRule { 
          case (`mem`,to,name,fvalue,tovalue) => None // Already manually mirrored. Don't change
          case (from:BufferRead,to:BufferRead,"banks",fvalue,tvalue) => Some(List(to.in.getVec))
          case (from,to,"vec",fvalue,tvalue) => to.inferVec
          case (from,to,"presetVec",fvalue,Some(tovalue)) => None
        } {
          mapping ++= toBanks.map { to =>
            to -> within(to.getCtrl) { stage(Const(bankids.toList)).mirrorMetas(to) }
          }
          mapping.values.foreach { to =>
            assert(!nodes.contains(to))
          }
          mirrorAll(nodes, mapping=mapping)
        }
      }
    }
    val mgs = mappings.map { _(k).as[GlobalContainer] }.toSet
   
    // Merge bankReads of multiple partitions
    val bankReads = k.collectDown[FlatBankedRead]()
    val cgs = bankReads.flatMap { bankRead =>
      //breakPoint(s"$k, $bankRead")
      mergeReads(k, mem, bankRead, mappings)
    }
    removeNodes(k.collectDown[TokenRead]())
    free(List(k), List(k))
    //breakPoint(s"$k, $mgs")
    mgs ++ cgs.toSet
  }

  def visitIn(n:PIRNode) = n match {
    case n:BufferRead => n.in.neighbors.toList
    case n:BufferWrite => n.data.neighbors.toList
    case n => visitGlobalIn(n)
  }

  def updateAddrCalc(access:FlatBankedAccess, bankMult:Int, sizePerBank:Int) = {
    val ofstShuffles = access.offset.collect[Shuffle](visitFunc=visitIn _).groupBy { _.aid }
    val dataShuffles = access.to[FlatBankedWrite].map { access =>
      access.data.collect[Shuffle](visitFunc=visitIn _).groupBy { _.aid }
    }
    val readShuffles = access.to[FlatBankedRead].map { access =>
      access.out.collect[Shuffle]().groupBy { _.aid }
    }
    dbgblk(s"$access") {
      dbg(s"ofstShuffle=${ofstShuffles}")
      dbg(s"dataShuffle=${dataShuffles}")
      dbg(s"readShuffle=${readShuffles}")
    }
    ofstShuffles.foreach { case (aid, List(ofstShuffle)) =>
      val bank = ofstShuffle.from.singleConnected.get
      val offset = ofstShuffle.base.singleConnected.get
      within(ofstShuffle.parent.get, ofstShuffle.getCtrl) {
        val bm = allocConst(bankMult, tp=Some(Fix(true,32,0)))
        val bs = allocConst(sizePerBank, tp=Some(Fix(true,32,0)))
        val newOfst = stage(OpDef(FixMod).addInput(offset, bs).out)
        swapConnection(ofstShuffle.base, offset, newOfst)
        val newBank = stage(OpDef(FixDiv).addInput(offset, bs).out)
        val newFlatBank = stage(OpDef(FixFMA).addInput(bank, bm, newBank).out)
        swapConnection(ofstShuffle.from, bank, newFlatBank)
        dataShuffles.foreach { dataShuffles =>
          val dataShuffle = assertOne(dataShuffles(aid), s"shuffle for $aid")
          swapConnection(dataShuffle.from, bank, newFlatBank)
        }
        readShuffles.foreach { readShuffleMap =>
          val readShuffles = readShuffleMap(aid)
          readShuffles.foreach { readShuffle =>
            if (!config.dupReadAddr) {
              swapConnection(readShuffle.to, readShuffle.to.singleConnected.get, newFlatBank)
              bufferInput(readShuffle.to)
              dupDeps(readShuffle.ctx.get, from=Some(ofstShuffle.ctx.get))
            } else {
              within(readShuffle.ctx.get, readShuffle.getCtrl) {
                val bm = allocConst(bankMult, tp=Some(Fix(true,32,0)))
                val bs = allocConst(sizePerBank, tp=Some(Fix(true,32,0)))
                val offset = readShuffle.offset.singleConnected.get
                val bank = readShuffle.to.singleConnected.get
                val newBank = stage(OpDef(FixDiv).addInput(offset, bs).out)
                val newFlatBank = stage(OpDef(FixFMA).addInput(bank, bm, newBank).out)
                swapConnection(readShuffle.to, bank, newFlatBank)
                free(readShuffle.offset)
              }
            }
          }
        }
      }
    }
  }

  def mergeShuffle(shuffle:Shuffle, mem:Memory, br:FlatBankedRead, mappings:List[mutable.Map[IR, IR]], ctx:Context) = dbgblk(s"Merge $shuffle in $ctx") {
    val ctrl = shuffle.getCtrl
    val out = within(ctx, ctrl) {
      val toMerge = shuffle.to.T match {
        case Const(toBanks) => 
          dbg(s"Read by $shuffle.to(Const($toBanks))")
          mappings.map { mapping => 
            val mmem = mapping(mem).as[Memory]
            val mbr = mapping(br).as[FlatBankedRead]
            val fromBanks = mmem.bankids.get
            if (fromBanks == toBanks) {
              mbr.out
            } else {
              stage(Shuffle(0,shuffle.aid).base(mbr.out).from(allocConst(fromBanks)).to(allocConst(toBanks))).out
            }
          }
        case to => 
          dbg(s"Read by $shuffle with non-constant to=$to")
          mappings.map { mapping => 
            val mmem = mapping(mem).as[Memory]
            val mbr = mapping(br).as[FlatBankedRead]
            val fromBanks = mmem.bankids.get
            val mbankAddr = shuffle.to.collect[BufferWrite](visitIn _).headOption.map { bout =>
              val bankAddr = bout.data.T
              dbg(s"bout=$bout bankAddr=$bankAddr")
              mapping.get(bankAddr).getOrElse(bankAddr)
            }
            val mto = mbankAddr.getOrElse { to }
            dbg(s"mbankAddr=$mbankAddr, mto=$mto")
            val mshuffle = stage(Shuffle(0,shuffle.aid).base(mbr.out).from(allocConst(fromBanks)).to(mto))
            mshuffle.out
          }
      }

      var red:List[Output[PIRNode]] = toMerge
      while(red.size > 1) {
        red = red.sliding(2,2).map{ 
          case List(o1, o2) => stage(OpDef(FixOr).addInput(o1, o2)).out
          case List(o1) => o1
        }.toList
      }
      val List(out) = red.toList
      out
    }
    if (!shuffle.isDescendentOf(ctx)) {
      val orig = shuffle.ctx.get
      val mshuffles = ctx.collectDown[Shuffle]()
      val bases = mshuffles.map { s => 
        val base = s.base.singleConnected.get
        dbg(s"$s.base=${dquote(base)}")
        s.base.disconnect
        base 
      }
      val deps = getDeps(ctx, Some(shuffle.ctx.get), Some(ctx),logger=Some(this))
      dupDeps(ctx, from=Some(shuffle.ctx.get))
      mshuffles.zip(bases).foreach { case (s, base) =>
        s.base(base)
      }
    }
    bufferInput(ctx)
    dupDeps(ctx, from=None).values.foreach { _.as[PIRNode].ctrl(ctrl,reset=true) }
    out
  }

  def mergeReads(k:CUMap.K, mem:Memory, br:FlatBankedRead, mappings:List[mutable.Map[IR, IR]]):Set[GlobalContainer] = dbgblk(s"mergeReads($k, $br)") {
    val cgs = mutable.ListBuffer[GlobalContainer]()
    val shuffles = br.collect[Shuffle](visitGlobalOut _)
    shuffles.groupBy { _.aid }.foreach { case (aid, shuffles) =>
      if (shuffles.size > 1 && config.enableBroadcastRead) {
        val mergeCtx = within(pirTop) {
          val mergeGlob = stage(ComputeContainer())
          cgs += mergeGlob
          dbg(s"mergeGlob=$mergeGlob")
          within(mergeGlob, shuffles.head.getCtrl) { 
            stage(Context())
          }
        }
        val merged = mergeShuffle(shuffles.head,mem,br,mappings,mergeCtx)
        shuffles.foreach { shuffle =>
          val ctx = shuffle.ctx.get
          swapOutput(shuffle.out, merged)
          bufferInput(ctx)
        }
      } else {
        shuffles.foreach { shuffle =>
          val ctx = shuffle.ctx.get
          val merged = mergeShuffle(shuffle,mem,br,mappings,ctx)
          swapOutput(shuffle.out, merged)
        }
      }
      free(shuffles,shuffles)
    }
    cgs.toSet
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

    dbg(s"sizePerBank=$sizePerBank cuPerBank=$cuPerBank totalBanks=$totalBanks, numCU=$numCU, bankMult=$bankMult, bankPerCU=$bankPerCU")
    (totalBanks, bankPerCU, numCU, sizePerBank, bankMult)
  }

  // Objective: 
  // Partition totalBanks into subgroups such that each group cannot exceed bankPerCU while 
  // 1. minimizing # of groups
  // 2. minimizing # of groups accessed by access bundles
  def partitionBanks[Access](accessPattern:Map[Access, List[Int]], totalBanks:Int, bankPerCU:Int):List[List[Int]] = dbgblk(s"partitionBanks($totalBanks,$bankPerCU)"){
    // ListBuffer[List[Int]]: List of staticially analyzed access bundle.
    
    // Map [Access, List[Bank]]: Mapping of access group to list of banks they touch
    val touchedBy = (0 until totalBanks).groupBy { bank =>
      accessPattern.flatMap { case (access, pattern) => if (pattern.contains(bank)) Some(access) else None }
    }
    // TODO: use union find to speedup this
    // Initial group assignment
    var groups:List[List[Int]] = (0 until totalBanks).map { i => List(i) }.toList

    // Bank groups touched by access 
    def groupsOf(access:Access):List[List[Int]] = {
      accessPattern(access).map { bank => groups.find {_.contains(bank) }.get }.distinct
    }

    // Starting with access that touch least # of groups, try to merge groups touched by this access
    // And iteratively doing this for all accesses
    accessPattern.keys.toList.sortBy { access => groupsOf(access).size }.foreach { access =>
      val accessGrps = groupsOf(access)
      val rest = groups.filterNot { accessGrps.contains(_) }
      val merged = merge(accessGrps, bankPerCU)
      groups = merged ++ rest
    }

    groups = merge(groups, bankPerCU)

    groups
  }

  // Merge groups in list with sizeLimit for each sublist until they cannot be further merged
  def merge(list:List[List[Int]], sizeLimit:Int):List[List[Int]] = {
    val (unmergable, mergable) = list.partition { _.size >= sizeLimit }
    merge(unmergable, mergable, sizeLimit)
  }

  // Recursively merge groups until they all over size limit or no longer can be combined
  private def merge(unmergable:List[List[Int]], mergable:List[List[Int]], sizeLimit:Int):List[List[Int]] = {
    val group = if (mergable.size == 0) unmergable else {
      val head = mergable.maxBy { _.size }
      val rest = mergable.filterNot { _ == head }
      rest.find { _.size + head.size <= sizeLimit }.fold {
        merge(unmergable :+ head, rest, sizeLimit)
      } { mergeWith =>
        val merged = head ++ mergeWith
        if (head.size + mergeWith.size == sizeLimit) {
          merge(unmergable :+ merged, rest.filterNot { _ == mergeWith }, sizeLimit)
        } else {
          merge(unmergable, rest.map { case `mergeWith` => merged; case grp => grp }, sizeLimit)
        }
      }
    }
    group.map { _.sorted }
  }

}

