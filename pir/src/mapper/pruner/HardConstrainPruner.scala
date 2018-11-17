package pir
package pass

import pir.node._
import pir.mapper._
import prism.graph._
import spade.param._

class HardConstrainPruner(implicit compiler:PIR) extends ConstrainPruner with CostUtil {

  override def prune[T](x:T):EOption[T] = super.prune[T](x).flatMap {
    case x:CUMap if !spadeParam.isAsic =>
      flatFold(x.freeKeys, x) { case (x, k) =>
        x.filterNotAtKey(k) { v => 
          (k.getCost[AFGCost] hardNotFit v.getCost[AFGCost]) ||
          (k.getCost[MCCost] hardNotFit v.getCost[MCCost]) ||
          (k.getCost[DAGCost] hardNotFit v.getCost[DAGCost]) ||
          (k.getCost[SRAMCost] hardNotFit v.getCost[SRAMCost])
        }
      }.asInstanceOf[EOption[T]]
    case x => super.prune(x)
  }

}
