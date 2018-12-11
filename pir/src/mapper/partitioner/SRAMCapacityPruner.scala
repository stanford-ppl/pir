package pir
package mapper

import pir.node._
import prism.graph._
import spade.param._
import prism.collection.immutable._

class SRAMCapacityPruner(implicit compiler:PIR) extends ConstrainPruner with MemoryPartitioner {

  override def prune[T](x:T):EOption[T] = super.prune[T](x).flatMap {
    case x:CUMap if !spadeParam.isAsic =>
      flatFold(x.freeKeys, x) { case (x, k) =>
        val kc = k.getCost[SRAMCost]
        recover(x.filterNotAtKey(k) { v => notFit(kc, v.getCost[SRAMCost]) })
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
        if (kcost.size > vcost.size){ 
          val numCU = kcost.size /! vcost.size
          val sram = assertOne(k.children.collect { case sram:SRAM => sram }, s"SRAM in $k")
          var sraminfo = sram.srcCtx.v.fold("") { sc => s"$sc" }
          sraminfo += sram.name.v.fold("") { n => s": $n" }
          val mks = split(fg, k, numCU).toSet
          info(s"Split $k into ${numCU} CUs $sraminfo kcost=${sram.size} vcost=${vcost.size}")
          mks.foreach { mk =>
            mk.children.collect { case m:SRAM => m }.foreach { m =>
              m.dims.reset
              m.dims := List(m.size / numCU)
            }
          }
          //TODO: Also need to fixes up the bank addr calculation
          Right(fg.mapFreeMap { _ - k ++ (mks, vs) })
        } else {
          super.recover(x)
        }
      case x => super.recover(x)
    }
  }

  def capacitySplit(x:CUMap, k:CUMap.K, ksize:Int, vsize:Int) = dbgblk(s"capacitySplit($k)"){
    val numCU = ksize /! vsize
    dbg(s"Split $k into $numCU cus")
  }

}

case class SRAMCapacityNotFit(k:CUMap.K, size:Int) extends MappingFailure {
  val msg = s"SRAMCapacityNotFit at key=$k. Number of size=$size"
}
