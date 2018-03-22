package pir.mapper

import prism._
import prism.collection.immutable._

trait FactorGraph[K,V,S] extends Serializable { self:S =>
  type W = Map[(K, V), Float]
  type FM = BiManyToManyMap[K, V]

  val default:Float = 1.0f

  val freeMap:FM
  var weights:W

  def newInstance(freeMap:FM, weights:W):S with FactorGraph[K,V,S] = {
    val constructor = this.getClass.getConstructor(classOf[FM], classOf[W])
    constructor.newInstance(freeMap, weights).asInstanceOf[S with FactorGraph[K,V,S]]
  }

  def ++ (pairs:(Set[K],Set[V])):S = { 
    val (kk,vv) = pairs
    val wt = kk.foldLeft(weights) { case (wt, k) => 
      vv.foldLeft(wt) { case (wt, v) => wt + ((k,v) -> wt.getOrElse((k,v), default)) }
    }
    newInstance(freeMap ++ pairs, wt)
  }
  def freeKeys = freeMap.keys.filter { k => freeValues(k).size > 1 }
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
  def multiplyFactor(factorLambda: (K,V) => Float):MOption[S with FactorGraph[K,V,S]] = {
    flatFold(freeKeys, this) { case (fg, k) =>
      freeValues(k).foreach { v =>
        weights += ((k,v) -> (weights((k,v)) * factorLambda(k,v)))
      }
      check(k)
    }
  }
  // Mapping with look ahead
  def map(k:K, v:V):MOption[S with FactorGraph[K,V,S]] = {
    assert(weights.get((k,v)).map(_ > 0).getOrElse(false))
    val fg = newInstance(freeMap, weights)
    val notUsed = freeValues(k).filterNot { _ == v }
    val neighbors = freeKeys(v).filterNot { _ == k }
    notUsed.foreach { v => fg.weights += ((k,v) -> 0.0f) }
    flatFold(neighbors, fg) { case (fg, neighbor) => fg.removeEdge(neighbor, v) }
  }

  def removeEdge(k:K, v:V):MOption[S with FactorGraph[K,V,S]] = {
    weights += ((k,v) -> 0.0f)
    check(k)
  }

  def check(k:K):MOption[S with FactorGraph[K,V,S]] = if (freeValues(k).nonEmpty) Right(this) else Left(InvalidFactorGraph(this, k))

  def get(k:K):Option[V] = {
    val vv = freeValues(k)
    if (vv.size==1) Some(vv.head) else None
  }

  def apply(k:K):V = get(k).get
}
