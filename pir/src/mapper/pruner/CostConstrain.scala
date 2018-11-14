package pir
package mapper

import pir.node._
import pir.pass._
import prism.collection.immutable._

abstract class Cost[C:ClassTag] extends Product { self:C =>
  def - (x:C):C with Cost[C]
  def + (x:C):C with Cost[C]
  def add (x:Any):C with Cost[C] = this + x.as[C]
  def diff (x:Any):C with Cost[C] = this - x.as[C]
  def nonEmpty:Boolean
}
trait PrefixCost[C<:PrefixCost[C]] extends Cost[C] { self:C =>
  val prefix:Boolean
  def - (x:C) = this.newInstance[C](Seq(prefix != x.as[C].prefix))
  def + (x:C) = this.newInstance[C](Seq(prefix || x.prefix))
  def nonEmpty:Boolean = prefix
}
trait QuantityCost[C<:QuantityCost[C]] extends Cost[C] { self:C =>
  val quantity:Int
  def - (x:C) = this.newInstance[C](Seq(quantity - x.as[C].quantity))
  def + (x:C) = this.newInstance[C](Seq(quantity + x.quantity))
  def nonEmpty:Boolean = quantity > 0
}
trait MaxCost[C<:MaxCost[C]] extends Cost[C] { self:C =>
  val quantity:Int
  def - (x:C) = this.newInstance[C](Seq(quantity - x.as[C].quantity))
  def + (x:C) = this.newInstance[C](Seq(Math.max(quantity, x.quantity)))
  def nonEmpty:Boolean = quantity > 0
}
trait SetCost[T,C<:SetCost[T,C]] extends Cost[C] { self:C =>
  val set:Set[T]
  def - (x:C) = this.newInstance[C](Seq(set diff x.as[C].set))
  def + (x:C) = this.newInstance[C](Seq(set ++ x.set))
  def nonEmpty:Boolean = set.nonEmpty
}

trait CostConstrain[C<:Cost[C]] extends FactorConstrain {
  def getCost(n:Any):C
  //def fit(key:Any, value:Any):(Boolean, Boolean) // (fit, splitable)
  def prune[K,V,S<:FG[K,V,S]](fg:S):EOption[S] = {
    flatFold[K,S](fg.freeKeys, fg) { case (fg, key) => prune[K,V,S](fg, key) }
  }
  def prune[K,V,S<:FG[K,V,S]](fg:S, key:K):EOption[S] = {
    val keyCost = getCost(key)
    val values = fg.freeValues(key).toIterator
    val diff = values.map { value => (value, keyCost - getCost(value)) }
    val (fits, notFits) = diff.partition { _._2.nonEmpty }
    if (fits.toSeq.isEmpty) {
      Left(CostConstrainFailure[K,V,S,C](fg, key, keyCost, notFits.toSeq))
    } else {
      Right(fg)
    }
  }
}
case class CostConstrainFailure[K,V,S<:FG[K,V,S],C<:Cost[C]](fg:S, key:Any, keyCost:C, notFits:Seq[(V,C)]) extends MappingFailure

