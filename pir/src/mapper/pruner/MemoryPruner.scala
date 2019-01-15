package pir
package mapper

import pir.node._
import prism.graph._
import spade.param._
import prism.collection.immutable._
import scala.collection.mutable

class MemoryPruner(implicit compiler:PIR) extends CUPruner {

  def getCosts(x:Any):List[Cost[_]] = List(x.getCost[SRAMCost])

  override def recover(x:EOption[CUMap]):EOption[CUMap] = {
    x match {
      case Left(f@InvalidFactorGraph(fg:CUMap, k:CUMap.K)) =>
        val kcost = getCosts(k).head.as[SRAMCost]
        val vs = fg.freeValuesOf(k)
        val vcost = assertOne(vs.map { getCosts(_) }, s"sramCost").head.as[SRAMCost]
        dbg(s"Recover $k")
        dbg(s"kcost=$kcost")
        dbg(s"vcost=$vcost")
        val ks = split(k, kcost, vcost).toSet.as[Set[CUMap.K]]
        newFG(fg, k, ks, vs)
      case x => super.recover(x)
    }
  }

  def split(k:CUMap.K, kcost:SRAMCost, vcost:SRAMCost):List[GlobalContainer] = dbgblk(s"split($k)"){
    val mem = k.collectDown[Memory]().head
    val bankMult = kcost.bank /! vcost.bank
    val newSize = mem.getDepth * mem.size * vcost.bank /! mem.totalBanks 
    val capMult = newSize /! vcost.size
    val totalBanks = kcost.bank * capMult
    val numCU = bankMult * capMult
    dbg(s"bankMult=$bankMult capMult=$capMult totalBanks=$totalBanks")
    info(s"Split $k into ${numCU} CUs")

    val nodes = k::k.descendents
    val mappings = List.fill(numCU) { within(pirTop) { mirrorAll(nodes) } }
    val mks = mappings.map { _(k).as[GlobalContainer] }

    // Update metadata of new partitions
    val bankids = (0 until totalBanks).toList.grouped(vcost.bank).toList
    (mappings, bankids).zipped.foreach { case (mapping, bankids) =>
      val mmem = mapping(mem).as[Memory]
      if (capMult != 1) {
        mmem.banks.reset
        mmem.banks := mem.banks.get :+ capMult
      }
      mmem.bankids.reset
      mmem.bankids := bankids
    }

    // Update Shuffles within new partitions
    updateShuffle(k, mem, mappings)

    // TODO: update bank address calculation if capMult is not one
    if (capMult > 1) {
      warn(s"Capacity splitting to $capMult. TODO: update bank address calculation")
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
    var toremove = nodes
    toremove ++= k.accum(visitFunc = visitGlobalOut _)
    removeNodes(toremove)
    //breakPoint(s"$k, $mks")
    mks
  }

  def removeUnusedConst(global:CUMap.K) = {
    val consts = global.collectDown[Const]().filter { !_.out.isConnected }
    removeNodes(consts)
  }

  def updateShuffle(k:CUMap.K, mem:Memory, mappings:List[mutable.Map[N, N]]) = {
    val shuffles = k.collectDown[Shuffle]()
    mappings.foreach { mapping =>
      val mk = mapping(k).as[CUMap.K]
      val mmem = mapping(mem).as[Memory]
      shuffles.foreach { shuffle =>
        val mshuffle = mapping(shuffle).as[Shuffle]
        within(mshuffle.parent.get.as[PIRNode], mshuffle.ctrl.get) {
          mshuffle.to.disconnect
          mshuffle.to(allocConst(mmem.bankids.get).out)
        }
      }
      removeUnusedConst(mk)
    }
  }

  def mergeReads(k:CUMap.K, mem:Memory, br:BankedRead, mappings:List[mutable.Map[N, N]]) = dbgblk(s"mergeReads($k, $br)") {
    val shuffles = br.collect[Shuffle](visitGlobalOut _)
    shuffles.foreach { shuffle =>
      val ctx = shuffle.ctx.get
      dbgblk(s"merge $shuffle in $ctx") {
        within(ctx, shuffle.ctrl.get) {
          val mshuffles = mappings.map { mapping =>
            val mmem = mapping(mem).as[Memory]
            val mbr = mapping(br).as[BankedRead]
            val mshuffle = mirrorAll(List(shuffle))(shuffle).as[Shuffle]
            // Swap shuffle.base connection from new cu
            mshuffle.base.disconnect
            mshuffle.base(mbr.out)
            bufferInput(mshuffle.base)
            // Swap connection shuffle.to, which is bank address. If bank address is passed from k, 
            // change to bank address calculated in corresponding new partitions
            mshuffle.to.T.collect[BufferWrite](visitFunc=visitGlobalIn _, logger=Some(this)).headOption.foreach { bout =>
              val bankAddr = bout.data.T
              mapping.get(bankAddr).foreach { mbankAddr =>
                dbg(s"bankAddr=$bankAddr, mbankAddr=$mbankAddr")
                mshuffle.to.disconnect
                mshuffle.to(mbankAddr)
                bufferInput(mshuffle)
              }
            }
            // Swap shuffle.from based on new statically assigned bankids.
            mshuffle.from.disconnect
            mshuffle.from(allocConst(mmem.bankids.get))
            mshuffle
          }
          var red:List[Output] = mshuffles.map { _.out }
          while(red.size > 1) {
            red = red.sliding(2,2).map{ 
              case List(o1, o2) =>
                val op = OpDef(FixOr).input(o1, o2)
                dbg(s"add val $op = FixOr.input(${o1.src}.$o1, ${o2.src}.$o2)")
                op.out
              case List(o1) => o1
            }.toList
          }
          val List(out) = red.toList
          swapOutput(shuffle.out, out)
        }
      }
    }
    shuffles.map { _.global.get }.distinct.foreach { global =>
      insertGlobalInput(global)
      removeUnusedConst(global)
    }
  }
}

case class SRAMBankNotFit(k:CUMap.K, bank:Int) extends MappingFailure {
  val msg = s"BankNotFit at key=$k. Number of banks=$bank"
}
