package prism
package collection1

import prism.collection1.immutable._

trait FactorGraph1[K,V,S<:FactorGraph1[K,V,S]] extends Serializable { self:S =>
  type W = Map[(K, V), Float]
  type FM = BiManyToManyMap[K, V]

  def name = getClass.getSimpleName

  val default:Float = 1.0f

  val freeMap:FM
  var weights:W

  def newInstance(freeMap:FM, weights:W):S = {
    val constructor = this.getClass.getConstructor(classOf[FM], classOf[W])
    constructor.newInstance(freeMap, weights).asInstanceOf[S]
  }

  override def clone = newInstance(freeMap, weights)

  def ++ (pairs:(Set[K],Set[V])):S = { 
    val (kk,vv) = pairs
    val wt = kk.foldLeft(weights) { case (wt, k) => 
      vv.foldLeft(wt) { case (wt, v) => wt + ((k,v) -> wt.getOrElse((k,v), default)) }
    }
    newInstance(freeMap ++ pairs, wt)
  }

  def keys = freeMap.keys
  def values = freeMap.values.flatten.toSet
  def keys(v:V) = freeMap.bmap(v)
  def values(k:K) = freeMap.fmap(k)
  def freeKeys = freeMap.keys.filter { k => freeValues(k).size > 1 }
  def freeValues(k:K):Set[V] = values(k).filter{ v => !isMarked(k,v) && hasEdge(k,v) }
  def freeKeys(v:V):Set[K] = keys(v).filter{ k => !isMarked(k,v) && hasEdge(k,v) }
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

  def multiplyFactor(factorLambda: (K,V) => Float):EOption[S] = {
    flatFold(freeKeys, this) { case (fg, k) =>
      freeValues(k).foreach { v =>
        weights += ((k,v) -> (weights((k,v)) * factorLambda(k,v)))
      }
      check(k)
    }
  }

  def multiplyFactor(k:K, v:V, factor:Float):EOption[S] = {
    weights += ((k,v) -> (weights(k,v) * factor))
    check(k)
  }

  /*
   * List of interfering keys that cannot be mapped to the same value as key. Default all other keys
   * */
  def neighbors(k:K):List[K] = keys.toList.filterNot { _ == k }

  // Mapping with look ahead
  def map(k:K, v:V):EOption[S] = {
    val fg = newInstance(freeMap, weights)
    fg.markEdge(k,v)
    val notUsed = freeValues(k).filterNot { _ == v }
    notUsed.foreach { v => fg.dropEdge(k, v) }
    flatFold(neighbors(k), fg) { case (fg, neighbor) => fg.removeEdge(neighbor, v) }
  }

  def dropEdge(k:K, v:V):Unit = {
    weights += ((k,v) -> 0.0f)
  }

  def removeEdge(k:K, v:V):EOption[S] = {
    if (hasEdge(k,v)) { dropEdge(k,v); check(k) } else Right(this)
  }

  def markEdge(k:K, v:V):Unit = {
    assert(hasEdge(k,v))
    weights += ((k,v) -> -1.0f)
  }

  def hasEdge(k:K, v:V) = weights((k,v)) > 0
  def isMarked(k:K, v:V) = weights((k,v)) == -1.0f

  def check(k:K):EOption[S] = {
    val invalid = values(k).filter { v => hasEdge(k,v) || isMarked(k, v) }.isEmpty
    if (invalid) Left(InvalidFactorGraph(this, k)) else Right(this)
  }

  def get(k:K):Option[V] = {
    val vv = values(k).filter { v => isMarked(k,v) }
    if (vv.size==1) Some(vv.head) else None
  }

  def apply(k:K):V = get(k).get
}

case class InvalidFactorGraph[K,FG<:FactorGraph1[K,_,FG]](@transient fg:FG, k:K) extends MappingFailure {
  var info = s"InvalidFactorGraph ${fg.name} at key=$k\n"
  info += s"freeValues: \n"
  info += fg.keys.map { k => s"$k -> ${fg.freeValues(k)}" }.mkString("\n") + "\n"
  info += s"values: \n${fg.values.mkString(",")}\n"
  val msg = info
}

