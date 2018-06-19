package pir
package mapper

import pir.node._
import pir.pass._
import spade.node._

case class CUCost(costs:Cost[_]*) extends Cost[CUCost]{
  override def toString = s"CUCost(${costs.mkString(",")})"
  val isSplittable = true
  def overCosts(that:CUCost) = {
    costs.zip(that.costs).filter { case (cost, tcost) =>
      cost.compareAsC(tcost) > 0
    }
  }
  def fit(that:Any) = {
    val fits = costs.zip(that.asInstanceOf[CUCost].costs).map { case (cost, tcost) =>
      cost.fit(tcost)
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
  val isSplittable = false 
  val prefix:Boolean
  def fit(that:Any) = (this <= that.asInstanceOf[C], isSplittable)
  def compare(that:C) = if (prefix == that.prefix) 0 else 1
}
trait QuantityCost[C<:QuantityCost[C]] extends Cost[C] {
  val quantity:Int
  def fit(that:Any) = (this <= that.asInstanceOf[C], isSplittable)
  def compare(that:C) = quantity.compare(that.quantity)
}
case class AFGCost(prefix:Boolean) extends PrefixCost[AFGCost]
case class MCCost(prefix:Boolean) extends PrefixCost[MCCost]
case class SramCost(quantity:Int) extends QuantityCost[SramCost] { val isSplittable = false }
case class ControlFifoCost(quantity:Int) extends QuantityCost[ControlFifoCost] { val isSplittable = true }
case class ScalarFifoCost(quantity:Int) extends QuantityCost[ScalarFifoCost] { val isSplittable = true }
case class VectorFifoCost(quantity:Int) extends QuantityCost[VectorFifoCost] { val isSplittable = true }
case class ControlInputCost(quantity:Int) extends QuantityCost[ControlInputCost] { val isSplittable = true }
case class ScalarInputCost(quantity:Int) extends QuantityCost[ScalarInputCost] { val isSplittable = true }
case class VectorInputCost(quantity:Int) extends QuantityCost[VectorInputCost] { val isSplittable = true }
case class ControlOutputCost(quantity:Int) extends QuantityCost[ControlOutputCost] { val isSplittable = true }
case class ScalarOutputCost(quantity:Int) extends QuantityCost[ScalarOutputCost] { val isSplittable = true }
case class VectorOutputCost(quantity:Int) extends QuantityCost[VectorOutputCost] { val isSplittable = true }
case class StageCost(quantity:Int) extends QuantityCost[StageCost] { val isSplittable = true }
case class LaneCost(quantity:Int) extends QuantityCost[LaneCost] { val isSplittable = false }
