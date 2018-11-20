package pir
package mapper

import pir.node._
import prism.graph._
import spade.param._
import prism.collection.immutable._

class SRAMCapacityPruner(implicit compiler:PIR) extends ConstrainPruner with Partitioner {

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
          capacitySplit(fg, k, kcost.size, vcost.size)
          Left(SRAMCapacityNotFit(k, kcost.size))
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
