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
    bankReads.foreach { br =>
      val mbrs = mappings.map { _(br).as[BankedRead] }
      val mrs:List[(Output, Output)] = mbrs.map { mbr => (mbr.bankHit, mbr.out) }
      val brs = br.collect[BufferRead](visitGlobalOut _).groupBy { _.global.get }
      brs.foreach { case (global, brs) =>
        brs.groupBy { _.ctx.get }.foreach { case (ctx, brs) =>
          brs.foreach { br =>
            within(ctx, br.ctrl.get) {
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
              br.localDepeds.foreach { deped =>
                swapInput(deped, br.out, out)
              }
            }
          }
          bufferInput(ctx)
        }
        insertGlobalInput(global)
      }
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
    mks
  }

}

case class SRAMBankNotFit(k:CUMap.K, bank:Int) extends MappingFailure {
  val msg = s"BankNotFit at key=$k. Number of banks=$bank"
}
