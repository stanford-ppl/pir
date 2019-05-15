package pir
package mapper

import pir.node._
import prism.graph._
import spade.param._
import prism.collection.immutable._
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
        val ks = split(k, kcost, vcost).toSet
        newFG(fg, k, ks, vs)
      case x => super.recover(x)
    }
  }

  def split(k:CUMap.K, kcost:SRAMCost, vcost:SRAMCost):List[GlobalContainer] = dbgblk(s"split($k)"){
    val mem = k.collectDown[Memory]().head
    val parts = splitBanks(kcost, vcost)
    val totalBanks = parts.map { _.size }.sum
    val bankMult = totalBanks /! kcost.bank
    val numCU = parts.size

    dbg(s"bankMult=$bankMult bankMult=$bankMult totalBanks=$totalBanks")

    val nodes = k.descendentTree

    val toBanks = nodes.collect { case s:Shuffle => s.to.T }
    val mappings = parts.map { bankids =>
      val mapping = mutable.Map[IR,IR]()
      mapping ++= toBanks.map { to => to -> stage(Const(bankids.toList)).mirrorMetas(to) }
      mapping += (mem -> mirror[Memory](mem).bankids(bankids.toList, reset=true).numPart(numCU)) 
      within(pirTop) { mirrorAll(nodes, mapping=mapping) }
    }
    val mks = mappings.map { _(k).as[GlobalContainer] }

    // TODO: update bank address calculation if bankMult is not one
    if (bankMult > 1) {
      warn(s"Capacity splitting to $bankMult. TODO: update bank address calculation")
    }
    
    // Merge bankReads of multiple partitions
    val bankReads = k.collectDown[BankedRead]()
    bankReads.foreach { bankRead =>
      //breakPoint(s"$k, $bankRead")
      mergeReads(k, mem, bankRead, mappings)
    }
    mks.foreach { nk =>
      insertGlobalOutput(nk)
    }
    //breakPoint(s"$k, $mks")
    val usedByRemove = k.accum(visitFunc = { n =>
      val ns = visitGlobalOut(n)
      n.to[Shuffle].foreach { n =>
        if (ns.nonEmpty) err(s"Deleted Shuffle $n when remove use of deleted MemoryContainer $k are still used by $ns")
      }
      ns
    })
    removeNodes(nodes)
    removeNodes(usedByRemove)
    //breakPoint(s"$k, $mks")
    mks
  }

  def removeUnusedConst(global:CUMap.K) = {
    val consts = global.collectDown[Const]().filter { !_.out.isConnected }
    removeNodes(consts)
  }

  def visitIn(n:PIRNode) = n match {
    case n:BufferRead => n.in.neighbors.toList
    case n:BufferWrite => n.data.neighbors.toList
    case n => visitGlobalIn(n)
  }

  def mergeReads(k:CUMap.K, mem:Memory, br:BankedRead, mappings:List[mutable.Map[IR, IR]]) = dbgblk(s"mergeReads($k, $br)") {
    val reads = br.collect[BufferRead](visitGlobalOut _)
    reads.groupBy { _.global.get }.foreach { case (global, reads) =>
      reads.groupBy { _.ctx.get }.foreach { case (ctx, reads) =>
        val read = assertOne(reads, s"reads of $br in $ctx")
          within(ctx, read.ctrl.get) {
            read.out.T.foreach { deped =>
              dbgblk(s"Merge $read => $deped in $ctx") {
              val (toSwap, toMerge) = deped match {
                case Unbox(shuffle:Shuffle, from, Const(toBanks), base) => 
                  dbg(s"Read by $shuffle.to(Const($toBanks))")
                  val toMerge = mappings.map { mapping => 
                    val mmem = mapping(mem).as[Memory]
                    val mbr = mapping(br).as[BankedRead]
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
                    val mbr = mapping(br).as[BankedRead]
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
                case out => 
                  err(s"Read by non Shuffle $out")
                  // Shuffle eliminated, which means bankAddr was constant and was
                  // original bankIds. Likely from capacity splitting.
                  val toMerge = mappings.map { mapping => 
                    val mmem = mapping(mem).as[Memory]
                    val mbr = mapping(br).as[BankedRead]
                    val fromBanks = mmem.bankids.get
                    val toBanks = mem.bankids.get
                    stage(Shuffle(0).base(mbr.out).from(allocConst(fromBanks)).to(allocConst(toBanks))).out
                  }
                  (read.out, toMerge)
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
          dupDeps(ctx, from=None)
        }
      }
      insertGlobalInput(global)
      removeUnusedConst(global)
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
    (0 until totalBanks).grouped(bankPerCU).toList
  }
}

case class SRAMBankNotFit(k:CUMap.K, bank:Int) extends MappingFailure {
  val msg = s"BankNotFit at key=$k. Number of banks=$bank"
}
