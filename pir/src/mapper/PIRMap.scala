package pir.mapper

import pir._
import pir.node._
import pir.pass._
import spade.node._
import spade.config._
import prism.collection.immutable._

case class PIRMap (
  cumap:CUMap,
  fimap:FIMap,
  cfmap:ConfigMap
) extends SpadeMapLike {
  type S = PIRMap
}
object PIRMap {
  def empty(pass:CUPruning):PIRMap = PIRMap(pass.initCUMap, FIMap.empty, ConfigMap.empty) 
}

case class CUMap(freeMap:CUMap.FM, weights:CUMap.W, usedMap:CUMap.UM) extends Serializable {
  type K = CUMap.K
  type V = CUMap.V
  val default:Float = 1.0f
  def ++ (pairs:(Set[K],Set[V])):CUMap = { 
    val (kk,vv) = pairs
    val wt = kk.foldLeft(weights) { case (wt, k) => 
      vv.foldLeft(wt) { case (wt, v) => wt + ((k,v) -> wt.getOrElse((k,v), default)) }
    }
    CUMap(freeMap ++ pairs, wt, usedMap)
  }
  def * (k:K, v:V, factor:Float) = {
    var newWeights = weights
    newWeights += (k,v) -> (newWeights((k,v)) * factor)
    CUMap(freeMap, newWeights, usedMap)
  }
  def freeKeys = freeMap.keys.filterNot { k => usedMap.contains(k) }
  def free(k:K):Set[V] = freeMap.fmap(k).filterNot{ v => weights((k,v)) <= 0 }
  def free(v:V):Set[K] = freeMap.bmap(v).filterNot{ k => weights((k,v)) <= 0 }
  def sortedFree(k:K) = free(k).toList.sortBy { v => -weights((k,v)) } // max to min
  def topFree(k:K):Option[V] = {
    val vv = free(k)
    if (vv.isEmpty) None else Some(vv.maxBy { v => weights(k,v) })
  }
  def foreachFree(lambda:(K,V) => Unit) = {
    freeMap.foreach { case (k,vv) =>
      vv.foreach { 
        case v if weights((k,v)) <= 0 => 
        case v => lambda(k,v)
      }
    }
  }
  def multiplyFactor(factorLambda: (K,V) => Float) = {
    var newWeights = weights
    foreachFree { case (k,v) => newWeights += ((k,v) -> (newWeights((k,v)) * factorLambda(k,v))) }
    CUMap(freeMap, newWeights, usedMap)
  }
  def map(k:K, v:V):CUMap = {
    assert(weights.get((k,v)).map(_ > 0).getOrElse(false))
    var newWeights = weights
    freeMap.fmap.foreach { case (`k`, vv) => vv.foreach { v => newWeights += ((k,v) -> 0.0f) }; case _ => }
    freeMap.bmap.foreach { case (`v`, kk) => kk.foreach { k => newWeights += ((k,v) -> 0.0f) }; case _ => }
    val newUsedMap = usedMap + (k -> v)
    CUMap(freeMap, newWeights, newUsedMap)
  }
}
object CUMap {
  type K = GlobalContainer
  type V = Routable
  type W = Map[(CUMap.K, CUMap.V), Float]
  type FM = BiManyToManyMap[CUMap.K, CUMap.V]
  type UM = OneToOneMap[CUMap.K, CUMap.V]
  def empty = CUMap(BiManyToManyMap.empty, Map.empty, OneToOneMap.empty)
}
