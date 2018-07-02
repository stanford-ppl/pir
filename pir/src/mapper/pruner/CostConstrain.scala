package pir
package mapper

import pir.node._
import pir.pass._
import prism.collection.immutable._

trait Cost[C] extends Ordered[C] {
  type K
  def compareAsC(x:Any) = compare(x.asInstanceOf[C])
  def fit(key:K,x:Any):(Boolean, Boolean) // (fit, splittable)
}
trait CostConstrain[C<:Cost[C]] extends Constrain {
  def getKeyCost(cuP:K):C
  def getValueCost(cuS:V):C

  def fit(key:K, value:V):(Boolean, Boolean) // (fit, splitable)
  def prune(fg:FG, key:K):EOption[FG] = {
    val values:Set[(V, Boolean, Boolean)] = fg.freeValues(key).map { value =>
      val (fits, splitable) = fit(key, value)
      (value, fits, splitable)
    }
    val (fits, nonFits) = values.partition { _._2 }
    if (fits.isEmpty) { // not fit
      val (splitables, nonSplitables) = nonFits.partition { _._3 }
      val nonSplitableValues = nonSplitables.map { _._1 }
      val kcost = getKeyCost(key)
      dbg(s"${quote(key)} not fit. Cost:${kcost}")
      fg.filterNotAt(key) { v => nonSplitableValues.contains(v) } match {
        case Left(InvalidFactorGraph(fg:FG, key)) => Left(CostConstrainFailure(fg , key, kcost, false))
        case Right(fg) => Left(CostConstrainFailure(fg , key, kcost, splitables.nonEmpty))
      }
    } else {
      val nonFitValues = nonFits.map { _._1 }
      fg.filterNotAt(key) { v => nonFitValues.contains(v) }
    }
  }
  def prune(fg:FG):EOption[FG] = {
    flatFold(fg.freeKeys, fg) { case (fg, key) => prune(fg, key) }
  }
}
case class CostConstrainFailure[FG<:FactorGraphLike[_,_,FG]](@transient fg:FG, key:Any, keyCost:Cost[_], isSplittable:Boolean) extends MappingFailure
