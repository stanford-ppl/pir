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
    info(s"Split $k into ${numCU} CUs")

    val nodes = k.descendentTree

    val mappings = parts.map { bankids =>
      within(pirTop) { mirrorAll(nodes, mirrorN=mirrorWithBank(mem, bankMult, bankids) _) }
    }
    val mks = mappings.map { _(k).as[GlobalContainer] }

     //Update metadata of new partitions
    //(mappings, parts).zipped.foreach { case (mapping, bankids) =>
      //updateMetadata(k, mem, mapping, bankMult, bankids)
    //}

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
    val usedByRemove = k.accum(visitFunc = visitGlobalOut _, logger=Some(this))
    removeNodes(nodes)
    removeNodes(usedByRemove)
    //breakPoint(s"$k, $mks")
    mks
  }

  def mirrorWithBank(mem:Memory, bankMult:Int, bankids:Iterable[Int])(n:ND, margs:Seq[Any]) = {
    n match {
      case `mem` => 
        val mmem = mirrorN[Memory](mem, margs)
        if (bankMult != 1) {
          mmem.banks.reset
          mmem.banks := mem.banks.get :+ bankMult
        }
        mmem.bankids.reset
        mmem.bankids := bankids.toList
        mmem
      case n:Const if n.out.connected.exists { case InputField(n:Shuffle, "to") => true; case _ => false } => 
        val c = Const(bankids.toList)
        c.mirrorMetas(n)
        c
      case n => mirrorN[ND](n, margs)
    }
  }

  def removeUnusedConst(global:CUMap.K) = {
    val consts = global.collectDown[Const]().filter { !_.out.isConnected }
    removeNodes(consts)
  }

  def updateMetadata(k:CUMap.K, mem:Memory, mapping:mutable.Map[ND, ND], bankMult:Int, bankids:Iterable[Int]) = {
    val mmem = mapping(mem).as[Memory]
    if (bankMult != 1) {
      mmem.banks.reset
      mmem.banks := mem.banks.get :+ bankMult
    }
    mmem.bankids.reset
    mmem.bankids := bankids.toList

    def visitIn(n:PIRNode) = n match {
      case n:BufferRead => n.in.neighbors.toList
      case n:BufferWrite => n.data.neighbors.toList
      case n => visitGlobalIn(n)
    }

    mmem.accesses.foreach { access =>
      // From access to memory bank shuffle
      var nodes = access match {
        case access:BankedRead =>
          access.offset.accumTill[Shuffle](visitFunc=visitIn)
        case access:BankedWrite =>
          access.offset.accumTill[Shuffle](visitFunc=visitIn) ++
          access.data.accumTill[Shuffle](visitFunc=visitIn) ++
          access.en.accumTill[Shuffle](visitFunc=visitIn)
      }
      nodes = nodes.distinct
      nodes :+= access
      val shuffles = nodes.collect { case n: Shuffle => n }
      shuffles.foreach { shuffle =>
        within(shuffle.parent.get, shuffle.ctrl.get) {
          shuffle.to.disconnect
          shuffle.to(allocConst(mmem.bankids.get).out)
        }
      }
      dbgblk(s"updateRequestVec(access=$access)") {
        nodes.foreach { node =>
          dbg(s"$node vec=${node.vec.v}")
          assert(node.vec.get == mem.nBanks, s"${node}.vec=${node.vec.v} != mem.nBanks=${mem.nBanks}")
          node.vec.reset
          node.localEdges.foreach { e => e.resetMeta("vec"); e.inferVec }
          node.to[BufferRead].foreach { br =>
            br.banks.reset
            br.banks := List(mmem.nBanks)
          }
        }
      }

      // From memory bank shuffle to access
      access match {
        case access:BankedRead =>
          var nodes = access.out.accumTill[Shuffle](visitFunc=visitGlobalOut)
          nodes = nodes.filterNot { case n:Shuffle => true; case _ => false }
          dbgblk(s"updateDataVec(access=$access)") {
            nodes.foreach { node =>
              dbg(s"$node vec=${node.vec.v}")
              assert(node.vec.get == mem.nBanks, s"${node}.vec=${node.vec.v} != mem.nBanks=${mem.nBanks}")
              node.vec.reset
              node.vec := mmem.nBanks
              node.localEdges.foreach { _.resetMeta("vec") }
              node.to[BufferRead].foreach { br =>
                br.banks.reset
                br.banks := List(mmem.nBanks)
              }
            }
          }
        case access =>
      }
    }
    removeUnusedConst(mapping(k).as[CUMap.K])
  }

  def mergeReads(k:CUMap.K, mem:Memory, br:BankedRead, mappings:List[mutable.Map[ND, ND]]) = dbgblk(s"mergeReads($k, $br)") {
    val reads = br.collect[BufferRead](visitGlobalOut _)
    reads.groupBy { _.global.get }.foreach { case (global, reads) =>
      reads.groupBy { _.ctx.get }.foreach { case (ctx, reads) =>
        val read = assertOne(reads, s"reads of $br in $ctx")
        dbgblk(s"Merge $read in $ctx") {
          within(ctx, read.ctrl.get) {
            val (toSwap, toMerge) = read.out.T match {
              case List(Unbox(shuffle:Shuffle, from, Const(toBanks), base)) => 
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
              case List(Unbox(shuffle:Shuffle, from, to, base)) => 
                dbg(s"Read by $shuffle with non-constant to=$to")
                val toMerge = mappings.map { mapping => 
                  val mmem = mapping(mem).as[Memory]
                  val mbr = mapping(br).as[BankedRead]
                  val fromBanks = mmem.bankids.get
                  val mbankAddr = shuffle.to.T.collect[BufferWrite](visitGlobalIn _).headOption.flatMap { bout =>
                    val bankAddr = bout.data.T
                    mapping.get(bankAddr)
                  }
                  val mto = mbankAddr.getOrElse { to }
                  dbg(s"mbankAddr=$mbankAddr, mto=$mto")
                  val mshuffle = stage(Shuffle(0).base(mbr.out).from(allocConst(fromBanks)).to(mto))
                  mshuffle.out
                }
                (shuffle.out, toMerge)
              case outs => 
                dbg(s"Read by non Shuffle $outs")
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
                case List(o1, o2) => stage(OpDef(FixOr).input(o1, o2)).out
                case List(o1) => o1
              }.toList
            }
            val List(out) = red.toList
            swapOutput(toSwap, out)
            bufferInput(ctx)
          }
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
