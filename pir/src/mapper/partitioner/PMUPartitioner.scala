package pir
package mapper

import pir.node._
import prism.graph._
import spade.param._
import prism.collection.immutable._

trait PMUPartitioner extends Partitioner {

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
    val numCU = vbanks /! kbanks
    dbg(s"Split $k into $numCU cus")
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
