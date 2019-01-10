package pir
package mapper

import pir.node._
import prism.graph._
import spade.param._
import prism.collection.immutable._
import scala.collection.mutable

class MemoryPruner(implicit compiler:PIR) extends ConstrainPruner with MemoryPartitioner {

  override def prune[T](x:T):EOption[T] = super.prune[T](x).flatMap {
    case x:CUMap if !spadeParam.isAsic =>
      flatFold(x.freeKeys, x) { case (x, k) =>
        val kc = k.getCost[SRAMCost]
        recover(x.filterNotAtKey(k) { v => !kc.fit(v.getCost[SRAMCost]) })
      }.asInstanceOf[EOption[T]]
    case x => super.prune(x)
  }

}

case class SRAMBankNotFit(k:CUMap.K, bank:Int) extends MappingFailure {
  val msg = s"BankNotFit at key=$k. Number of banks=$bank"
}
