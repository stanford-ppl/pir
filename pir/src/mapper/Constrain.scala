package pir
package mapper

import prism.collection.immutable._
import prism.util.Memorization
import scala.collection.mutable

abstract class Constrain(implicit val pass:PIRPass) extends Logging with Memorization with MappingLogger {
  type K
  type V
  type FG <: FactorGraphLike[K,V,FG]
  implicit def fgct:ClassTag[FG]
  override lazy val logger = pass.logger
  override def toString = this.getClass.getSimpleName.replace("$","")
  override def quote(n:Any) = pass.quote(n)
  memorizing = true
  def prune(fg:FG):EOption[FG]
  def prune(pmap:PIRMap):EOption[PIRMap] = {
    pmap.flatMap[FG](field => prune(field))
  }
}
trait Cost[C] extends Ordered[C] {
  val isSplittable:Boolean
  def compareAsC(x:Any) = compare(x.asInstanceOf[C])
  def fit(x:Any):(Boolean, Boolean) // (fit, splittable)
}
trait CostConstrain[C<:Cost[C]] extends Constrain {
  def getKeyCost(cuP:K):C
  def getValueCost(cuS:V):C
  val keyCost = memorize(getKeyCost)
  val valueCost = memorize(getValueCost)
  def fit(key:K, value:V):(Boolean, Boolean)
  def prune(fg:FG):EOption[FG] = {
    flatFold(fg.freeKeys, fg) { case (fg, key) =>
      val values:Set[(V, Boolean, Boolean)] = fg.freeValues(key).map { value =>
        val (fits, splitable) = fit(key, value)
        (value, fits, splitable)
      }
      val (fits, nonFits) = values.partition { _._2 }
      if (fits.isEmpty) { // not fit
        val (splitables, nonSplitables) = nonFits.partition { _._3 }
        val nonSplitableValues = nonSplitables.map { _._1 }
        dbg(s"${quote(key)} not fit. Cost:${keyCost(key)}")
        fg.filterNotAt(key) { v => nonSplitableValues.contains(v) } match {
          case Left(InvalidFactorGraph(fg:FG, key)) => Left(CostConstrainFailure(fg , key, false))
          case Right(fg) => Left(CostConstrainFailure(fg , key, splitables.nonEmpty))
        }
      } else {
        val nonFitValues = nonFits.map { _._1 }
        fg.filterNotAt(key) { v => nonFitValues.contains(v) }
      }
    }
  }
}
trait ArcConsistencyConstrain extends Constrain {
  def prune(fg:FG):EOption[FG] = {
    flatFold(fg.freeKeys,fg) { case (fg, k) => ac3[K,V,FG](fg, k) }
  }
  def ac3[K,V,FG<:FactorGraphLike[K,V,FG]](fg:FG, k:K):EOption[FG] = {
    flatFold(fg(k),fg) { case (fg, v) =>
      val neighbors = fg.freeKeys(v).filterNot { _ == k }
      val nfg = fg.set(k,v)
      nfg match {
        case Left(_) => fg - (k,v)
        case Right(nfg) =>
          flatFold(neighbors, fg) { case (fg, neighbor) => 
            if (ac3[K,V,FG](nfg, neighbor).isLeft) fg - (k,v) else Right(fg)
          }
      }
    }
  }
}
trait MatchingConstrain extends Constrain {

  //def createCompleteBipartiteSubgraphs(fg:FG) = {
    //val subgraphs = mutable.Map[Set[V], mutable.Set[K]]()
    //fg.freeKeys.foreach { key =>
      //val values = fg.freeValues(key)
      //if (!subgraphs.contains(values)) {
        //subgraphs += (values -> mutable.Set[K]())
      //}
      //subgraphs.foreach { case (vs, ks) =>
        //if (vs.subsetOf(values)) ks += key
      //}
    //}
    //subgraphs
  //}

  def check(fg:FG, ks:Set[K], vs:Set[V]) = {
    val keySize = ks.size
    val valueSize = vs.size
    val fit = keySize <= valueSize
    dbgblk(s"MatchingConstrain: keySize=$keySize <= valueSize=$valueSize = $fit") {
      dbg(s"keys=${quote(ks)}")
      dbg(s"values=${quote(vs)}")
    }
    if (fit) Right(fg) else Left(MatchConstrainFailure(fg, ks.toSet))
  }
  
  def prune(fg:FG):EOption[FG] = {
    log(fg)
    val sets = fg.freeKeys.groupBy { key => fg.freeValues(key) }.map { case (values, keys) =>
      (keys.head, values)
    }
    flatFold(sets, fg) { case (fg, (key, values)) =>
      val keys = values.flatMap { value => fg.freeKeys(value) }.filter { key => 
        fg.freeValues(key).subsetOf(values)
      }
      check(fg, keys, values)
    }
  }

}

case class CostConstrainFailure[FG<:FactorGraphLike[_,_,FG]](@transient fg:FG, key:Any, isSplittable:Boolean) extends MappingFailure
case class MatchConstrainFailure[FG<:FactorGraphLike[_,_,FG]](@transient fg:FG, keys:Set[_]) extends MappingFailure {
  override def toString = s"MatchConstrainFailure(${fg.getClass.getSimpleName}, $keys)"
}
