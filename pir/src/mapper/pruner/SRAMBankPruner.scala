package pir
package mapper

import pir.node._
import prism.graph._
import spade.param._
import prism.collection.immutable._

class SRAMBankPruner(implicit compiler:PIR) extends ConstrainPruner with MemoryPartitioner {

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
          val numCU = kcost.bank /! vcost.bank
          val mks = split(fg, k, numCU).toSet
          info(s"Split $k into ${numCU} CUs")
          mks.foreach { mk =>
            mk.children.collect { case m:SRAM => m }.foreach { m =>
              m.banks.reset
              m.dims.reset
              m.banks := List(vcost.bank)
              m.dims := List(m.size / numCU)
            }
          }
          //TODO: maybe not reset banks, but assign contained banks to be 
          // half of the banks. And recompute size
          Right(fg.mapFreeMap { _ - k ++ (mks, vs) })
        } else {
          super.recover(x)
        }
      case x => super.recover(x)
    }
  }

}

case class SRAMBankNotFit(k:CUMap.K, bank:Int) extends MappingFailure {
  val msg = s"BankNotFit at key=$k. Number of banks=$bank"
}
