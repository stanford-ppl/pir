package pir
package mapper

import pir.node._
import pir.pass._
import prism.graph._
import spade.param._
import prism.collection.immutable._
import scala.collection.mutable

trait PMUPartitioner extends Partitioner with BufferAnalyzer {

  override def recover(x:EOption[CUMap]):EOption[CUMap] = {
    x match {
      case Left(f@InvalidFactorGraph(fg:CUMap, k:CUMap.K)) =>
        val kcost = k.getCost[SRAMCost]
        val vs = fg.freeValuesOf(k)
        val vcost = assertOne(vs.map { _.getCost[SRAMCost] }, s"sramCost")
        dbg(s"kcost: $kcost")
        dbg(s"vcost=$vcost")
        if (kcost.bank > vcost.bank) {
          bankSplit(fg, k, kcost.bank, vcost.bank)
        } else if (kcost.size > vcost.size){ 
          capacitySplit(fg, k, kcost.size, vcost.size)
        } else {
          super.recover(x)
        }
      case x => super.recover(x)
    }
  }

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
          breakPoint(s"split $k after bufferInput $ctx", None)
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
    dbg(s"splits=${mks}")
    breakPoint(s"split $k", None)
    Left(SRAMBankNotFit(k, kbanks))
  }

  def capacitySplit(x:CUMap, k:CUMap.K, ksize:Int, vsize:Int) = dbgblk(s"capacitySplit($k)"){
    val numCU = vsize /! ksize
    dbg(s"Split $k into $numCU cus")
    Left(SRAMCapacityNotFit(k, ksize))
  }

}

case class SRAMBankNotFit(k:CUMap.K, bank:Int) extends MappingFailure {
  val msg = s"BankNotFit at key=$k. Number of banks=$bank"
}
case class SRAMCapacityNotFit(k:CUMap.K, size:Int) extends MappingFailure {
  val msg = s"SRAMCapacityNotFit at key=$k. Number of size=$size"
}
