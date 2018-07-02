package pir
package mapper

import pir.node._
import pir.pass._
import spade.node._

case class CUCost(costs:Cost[_]*) extends Cost[CUCost]{
  type K = CUMap.K
  override def toString = s"CUCost(${costs.mkString(",")})"
  def overCosts(that:CUCost) = {
    costs.zip(that.costs).filter { case (cost, tcost) =>
      cost.compareAsC(tcost) > 0
    }
  }
  override def fit(key:K, that:Any) = {
    val fits = costs.zip(that.asInstanceOf[CUCost].costs).map { case (cost, tcost) =>
      cost.fit(key.asInstanceOf[cost.K], tcost)
    }
    (fits.forall(_._1), fits.filter(!_._1).forall(_._2))
  }
  def compare(that:CUCost) = {
    val comps = costs.zip(that.costs).map { case (cost, tcost) =>
      cost.compareAsC(tcost)
    }
    if (comps.exists { _ > 0 }) 1 
    else if (comps.forall { _ == 0 }) 0
    else -1
  }
}
trait PrefixCost[C<:PrefixCost[C]] extends Cost[C] {
  type K = CUMap.K
  val prefix:Boolean
  def fit(key:K, that:Any) = (this <= that.asInstanceOf[C], false)
  def compare(that:C) = if (prefix == that.prefix) 0 else 1
}
trait QuantityCost[C<:QuantityCost[C]] extends Cost[C] {
  type K = CUMap.K
  val quantity:Int
  def isSplittable(key:K):Boolean
  def fit(key:K, that:Any) = (this <= that.asInstanceOf[C], isSplittable(key))
  def compare(that:C) = quantity.compare(that.quantity)
}
case class AFGCost(prefix:Boolean)(implicit pass:CUPruner) extends PrefixCost[AFGCost]
case class MCCost(prefix:Boolean)(implicit pass:CUPruner) extends PrefixCost[MCCost]
case class SramSizeCost(quantity:Int)(implicit pass:CUPruner) extends QuantityCost[SramSizeCost] { def isSplittable(key:K) = false }
case class SramCost(quantity:Int)(implicit pass:CUPruner) extends QuantityCost[SramCost] { def isSplittable(key:K) = false }
case class ControlInputCost(quantity:Int)(implicit pass:CUPruner) extends QuantityCost[ControlInputCost] { def isSplittable(key:K) = !pass.isAFG(key) }
case class ScalarInputCost(quantity:Int)(implicit pass:CUPruner) extends QuantityCost[ScalarInputCost] { def isSplittable(key:K) = !pass.isAFG(key) }
case class VectorInputCost(quantity:Int)(implicit pass:CUPruner) extends QuantityCost[VectorInputCost] { def isSplittable(key:K) = !pass.isAFG(key) }
case class ControlOutputCost(quantity:Int)(implicit pass:CUPruner) extends QuantityCost[ControlOutputCost] { def isSplittable(key:K) = !pass.isAFG(key) }
case class ScalarOutputCost(quantity:Int)(implicit pass:CUPruner) extends QuantityCost[ScalarOutputCost] { def isSplittable(key:K) = !pass.isAFG(key) }
case class VectorOutputCost(quantity:Int)(implicit pass:CUPruner) extends QuantityCost[VectorOutputCost] { def isSplittable(key:K) = !pass.isAFG(key) }
case class StageCost(quantity:Int)(implicit pass:CUPruner) extends QuantityCost[StageCost] { def isSplittable(key:K) = true }
case class LaneCost(quantity:Int)(implicit pass:CUPruner) extends QuantityCost[LaneCost] { def isSplittable(key:K) = false }
