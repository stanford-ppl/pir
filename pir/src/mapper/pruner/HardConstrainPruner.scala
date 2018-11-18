package pir
package pass

import pir.node._
import pir.mapper._
import prism.graph._
import spade.param._
import prism.collection.immutable._

class HardConstrainPruner(implicit compiler:PIR) extends ConstrainPruner with CUCostUtil {

  override def prune[T](x:T):EOption[T] = super.prune[T](x).flatMap {
    case x:CUMap if !spadeParam.isAsic =>
      flatFold(x.freeKeys, x) { case (x, k) =>
        val kc = getCosts(k)
        x.filterNotAtKey(k) { v => 
          val vc = getCosts(v)
          (kc, vc).zipped.exists { case (kc, vc) => notFit(kc, vc) }
        }
      }.asInstanceOf[EOption[T]]
    case x => super.prune(x)
  }

  def getCosts(x:Any) = {
    x.getCost[AFGCost] ::
    x.getCost[MCCost] ::
    x.getCost[SRAMCost] ::
    x.getCost[LaneCost] ::
    x.getCost[OpCost] ::
    Nil
  }

  override def notFit(kc:Cost[_], vc:Cost[_]) = {
    (kc, vc) match {
      case (kc:QuantityCost[_], vc:QuantityCost[_]) =>
        (kc.quantities, vc.quantities).zipped.exists { case (kq, vq) => kq > 0 && vq == 0 }
      case (kc, vc) => super.notFit(kc,vc)
    }
  }

  override def fail(f:Any) = {
    super.fail(f)
    f match {
      case e@InvalidFactorGraph(fg, k:CUMap.K) =>
        err(s"Constrain failed on $k", exception=false)
        err(s"$k costs:", exception=false)
        val kc = getCosts(k)
        kc.foreach { kc =>
          err(s"${kc}:", exception=false)
        }
      case _ =>
    }
  }

}
