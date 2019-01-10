package pir
package mapper

import pir.node._
import pir.pass._
import prism.collection.immutable._

abstract class Cost[C<:Cost[C]:ClassTag] extends Product { self:C =>
  val ct = classTag[C]
  def - (x:C):C
  def + (x:C):C
  def fit(x:C):Boolean
  def isfit(x:Any):Boolean = fit(x.as[C])
}
trait PrefixCost[C<:PrefixCost[C]] extends Cost[C] { self:C =>
  val prefix:Boolean
  def - (x:C) = this.newInstance[C](Seq(prefix != x.as[C].prefix))
  def + (x:C) = this.newInstance[C](Seq(prefix || x.prefix))
  def fit(x:C):Boolean = this.prefix == x.prefix
}
trait QuantityCost[C<:QuantityCost[C]] extends Cost[C] with Product { self:C =>
  def quantities:List[Int] = productIterator.toList.asInstanceOf[List[Int]]
  def - (x:C) = this.newInstance[C]((quantities, x.quantities).zipped.map { _ + _ })
  def + (x:C) = this.newInstance[C]((quantities, x.quantities).zipped.map { _ + _ })
  def fit(x:C):Boolean = {
    (this.quantities, x.quantities).zipped.forall { case (kq, vq) => kq <= vq }
  }
}
trait MaxCost[C<:MaxCost[C]] extends Cost[C] { self:C =>
  val quantity:Int
  def - (x:C) = this.newInstance[C](Seq(quantity - x.as[C].quantity))
  def + (x:C) = this.newInstance[C](Seq(Math.max(quantity, x.quantity)))
  def fit(x:C):Boolean = this.quantity <= x.quantity
}
trait SetCost[T,C<:SetCost[T,C]] extends Cost[C] { self:C =>
  val set:Set[T]
  def - (x:C) = this.newInstance[C](Seq(set diff x.as[C].set))
  def + (x:C) = this.newInstance[C](Seq(set ++ x.set))
  def fit(x:C):Boolean = {
    this.set.forall { k => x.set.contains(k) }
  }
}

trait CostUtil {
  implicit class ListUtil(x:List[Cost[_]]) {
    def fit(y:List[Cost[_]]):Boolean = {
      val xg = x.groupBy { _.ct }
      val yg = y.groupBy { _.ct }
      xg.keys.toList.intersect(yg.keys.toList).forall { k =>
        val xc = assertOne(xg(k), s"$x with $k")
        val yc = assertOne(yg(k), s"$y with $k")
        xc.isfit(yc)
      }
    }
  }
  def notFit(kc:Any, vc:Any):Boolean = (kc, vc) match {
    case (kc:List[_], vc:List[_]) =>
      (kc, vc).zipped.exists { case (kc, vc) => notFit(kc, vc) }
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
