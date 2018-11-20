package pir
package mapper

import pir.node._
import prism.graph._
import spade.param._
import prism.collection.immutable._

class SRAMBankPruner(implicit compiler:PIR) extends ConstrainPruner with Partitioner {

  override def prune[T](x:T):EOption[T] = super.prune[T](x).flatMap {
    case x:CUMap if !spadeParam.isAsic =>
      flatFold(x.freeKeys, x) { case (x, k) =>
        val kc = k.getCost[SRAMCost].bank
        recover(x.filterNotAtKey(k) { v => v.getCost[SRAMCost].bank < kc })
      }.asInstanceOf[EOption[T]]
    case x => super.prune(x)
  }

  override def recover(x:EOption[CUMap]):EOption[CUMap] = {
    x match {
      case Left(f@InvalidFactorGraph(fg:CUMap, k:CUMap.K)) =>
        val kcost = k.getCost[SRAMCost]
        val vs = fg.freeValuesOf(k)
        val vcost = assertOne(vs.map { _.getCost[SRAMCost] }, s"sramCost")
        dbg(s"kcost: $kcost")
        dbg(s"vcost=$vcost")
        if (kcost.bank > vcost.bank) {
          val ks = bankSplit(fg, k, kcost.bank, vcost.bank).toSet
          Right(fg.mapFreeMap { _ - k ++ (ks, vs) })
        } else {
          super.recover(x)
        }
      case x => super.recover(x)
    }
  }

  // Split one pmu into multiple pmu that fits total number of banks
  def bankSplit(x:CUMap, k:CUMap.K, kbanks:Int, vbanks:Int) = dbgblk(s"bankSplit($k)"){
    val numCU = kbanks /! vbanks
    dbg(s"Split $k into $numCU cus")
    val nodes = k::k.descendents
    val mappings = List.fill(numCU) { within(pirTop) { mirrorAll(nodes) } }
    val bankReads = k.collectDown[BankedRead]()
    val globals = bankReads.flatMap { br =>
      val mbrs = mappings.map { _(br).as[BankedRead] }
      val mrs = mbrs.map { mbr => (mbr.bankHit, mbr.out) }
      val reads = br.collect[BufferRead](visitGlobalOut _).groupBy { _.global.get }
      reads.flatMap { case (global, reads) => mergeBanks(global, br, reads, mrs) }
    }
    val mks = mappings.map { _(k).as[GlobalContainer] }
    mks.foreach { mk =>
      mk.children.collect { case m:SRAM => m }.foreach { m =>
        m.banks.reset
        m.banks := List(vbanks)
      }
    }
    mks.foreach { nk =>
      insertGlobalOutput(nk)
    }
    var toremove = nodes
    toremove ++= k.accum(visitFunc = visitGlobalOut _)
    toremove.foreach { removeNode }
    mks ++ globals
  }

  def mergeBanks(global:GlobalContainer, br:BankedRead, reads:List[BufferRead], mrs:List[(Output, Output)]):Seq[GlobalContainer] = dbgblk(s"mergeBanks($global)") {
    global match {
      case fringe:DRAMFringe =>
        within(pirTop) {
          val global = ComputeContainer()
          val read = within(global) {
            val ctx = Context()
            dbg(s"Create $global and $ctx to merge banks for $fringe")
            val ctrl = br.ctrl.get
            val owrite = reads.head.inAccess 
            val done = owrite.done.T
            val deps = getDeps(owrite, { case `owrite` => List(owrite.done.T); case n => visitGlobalIn(n) })
            val mdone = within(ctx) { mirrorAll(deps)(done) }
            val read = within(ctx, ctrl) {
              val read = BufferRead(reads.head.isFIFO).in(br.out).banks(reads.head.banks.get).done(mdone)
              val write = BufferWrite().data(read.out).done(mdone)
              reads.foreach { read =>
                read.in.disconnect
                read.in(write)
              }
              read
            }
            read
          }
          insertGlobalInput(fringe)
          insertGlobalOutput(global)
          global +: mergeBanks(global, br, List(read), mrs)
        }
      case global =>
        reads.groupBy { _.ctx.get }.foreach { case (ctx, reads) =>
          reads.foreach { read =>
            within(ctx, read.ctrl.get) {
              var red:List[(Output,Output)] = mrs
              while(red.size > 1) {
                red = red.sliding(2,2).map{ 
                  case List((m1, o1),(m2, o2)) =>
                    val op = OpDef(Mux).input(m1, o1, o2)
                    dbg(s"add $op.input(${m1.src}.$m1, ${o1.src}.$o1, ${o2.src}.$o2)")
                    (m2, op.out)
                  case List((m1, o1)) => (m1, o1)
                }.toList
              }
              val List((mask, out)) = red.toList
              read.localDepeds.foreach { deped =>
                swapInput(deped, read.out, out)
              }
              removeNode(read)
            }
          }
          bufferInput(ctx)
        }
        insertGlobalInput(global)
        Nil
    }
  }

}

case class SRAMBankNotFit(k:CUMap.K, bank:Int) extends MappingFailure {
  val msg = s"BankNotFit at key=$k. Number of banks=$bank"
}
