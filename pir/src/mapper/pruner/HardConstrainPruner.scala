package pir
package pass

import pir.node._
import pir.mapper._
import prism.graph._
import spade.param._
import prism.collection.immutable._

class HardConstrainPruner(implicit compiler:PIR) extends CUPruner {

  override def getCosts(x:Any) = {
    x.getCost[AFGCost] ::
    x.getCost[MCCost] ::
    x.getCost[SRAMCost] ::
    x.getCost[LaneCost] ::
    x.getCost[OpCost] ::
    Nil
  }

  override def notFit(kc:Any, vc:Any) = {
    (kc, vc) match {
      case (kc:QuantityCost[_], vc:QuantityCost[_]) =>
        (kc.quantities, vc.quantities).zipped.exists { case (kq, vq) => kq > 0 && vq == 0 }
      case (kc, vc) => super.notFit(kc,vc)
    }
  }

}
