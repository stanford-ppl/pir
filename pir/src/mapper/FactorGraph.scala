package pir.mapper

import prism._
import prism.collection.immutable._

trait FactorGraph[K,V,S] extends Serializable { self:S =>
  type W = Map[(K, V), Float]
  type FM = BiManyToManyMap[K, V]
  type UM = OneToOneMap[K, V]

  val default:Float = 1.0f

  val freeMap:FM
  val weights:W
  val usedMap:UM

  def newInstance(freeMap:FM, weights:W, usedMap:UM):S = {
    val constructor = this.getClass.getConstructor(classOf[FM], classOf[W], classOf[UM])
    constructor.newInstance(freeMap, weights, usedMap).asInstanceOf[S]
  }

  def ++ (pairs:(Set[K],Set[V])):S = { 
    val (kk,vv) = pairs
    val wt = kk.foldLeft(weights) { case (wt, k) => 
      vv.foldLeft(wt) { case (wt, v) => wt + ((k,v) -> wt.getOrElse((k,v), default)) }
    }
    newInstance(freeMap ++ pairs, wt, usedMap)
  }
  def * (k:K, v:V, factor:Float) = {
    var newWeights = weights
    newWeights += (k,v) -> (newWeights((k,v)) * factor)
    newInstance(freeMap, newWeights, usedMap)
  }
  def freeKeys = freeMap.keys.filterNot { k => usedMap.contains(k) }
  def freeValues(k:K):Set[V] = freeMap.fmap(k).filterNot{ v => weights((k,v)) <= 0 }
  def freeKeys(v:V):Set[K] = freeMap.bmap(v).filterNot{ k => weights((k,v)) <= 0 }
  def sortedFreeValues(k:K) = freeValues(k).toList.sortBy { v => -weights((k,v)) } // max to min
  def topFreeValue(k:K):Option[V] = {
    val vv = freeValues(k)
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
    newInstance(freeMap, newWeights, usedMap)
  }
  def map(k:K, v:V):S = {
    assert(weights.get((k,v)).map(_ > 0).getOrElse(false))
    var newWeights = weights
    freeMap.fmap.foreach { case (`k`, vv) => vv.foreach { v => newWeights += ((k,v) -> 0.0f) }; case _ => }
    freeMap.bmap.foreach { case (`v`, kk) => kk.foreach { k => newWeights += ((k,v) -> 0.0f) }; case _ => }
    val newUsedMap = usedMap + (k -> v)
    newInstance(freeMap, newWeights, newUsedMap)
  }
}
