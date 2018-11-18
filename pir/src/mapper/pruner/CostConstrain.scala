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
}
trait PrefixCost[C<:PrefixCost[C]] extends Cost[C] { self:C =>
  val prefix:Boolean
  def - (x:C) = this.newInstance[C](Seq(prefix != x.as[C].prefix))
  def + (x:C) = this.newInstance[C](Seq(prefix || x.prefix))
  def hardNotFit(oc:C) = prefix != oc.prefix
}
trait QuantityCost[C<:QuantityCost[C]] extends Cost[C] with Product { self:C =>
  def quantities:List[Int] = productIterator.toList.asInstanceOf[List[Int]]
  def - (x:C) = this.newInstance[C]((quantities, x.quantities).zipped.map { _ + _ })
  def + (x:C) = this.newInstance[C]((quantities, x.quantities).zipped.map { _ + _ })
  def hardNotFit(oc:C) = (quantities, (oc.quantities)).zipped.exists { case (q, oq) => q > 0 && oq == 0 }
}
trait MaxCost[C<:MaxCost[C]] extends Cost[C] { self:C =>
  val quantity:Int
  def - (x:C) = this.newInstance[C](Seq(quantity - x.as[C].quantity))
  def + (x:C) = this.newInstance[C](Seq(Math.max(quantity, x.quantity)))
}
trait SetCost[T,C<:SetCost[T,C]] extends Cost[C] { self:C =>
  val set:Set[T]
  def - (x:C) = this.newInstance[C](Seq(set diff x.as[C].set))
  def + (x:C) = this.newInstance[C](Seq(set ++ x.set))
}

trait CostUtil {
  def notFit(kc:Cost[_], vc:Cost[_]) = (kc, vc) match {
    case (kc:QuantityCost[_], vc:QuantityCost[_]) =>
      (kc.quantities, vc.quantities).zipped.exists { case (kq, vq) => kq > vq }
    case (kc:MaxCost[_], vc:MaxCost[_]) =>
      kc.quantity > vc.quantity
    case (kc:PrefixCost[_], vc:PrefixCost[_]) =>
      kc.prefix != vc.prefix
    case (kc:SetCost[_,_], vc:SetCost[_,_]) =>
      kc.set.exists { k => !vc.set.contains(k) }
    case _ => throw PIRException(s"Don't know how to compare $kc with $vc")
  }
}
