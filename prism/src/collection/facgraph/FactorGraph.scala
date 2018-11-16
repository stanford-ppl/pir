package prism
package collection.immutable

trait FactorGraphLike[K,V,S<:FactorGraphLike[K,V,S]] { self:S =>
  type FM = BiManyToManyMap[K,V]
  type W = Map[(K,V),Int]
  type M <: BiMap[K,V,M]
  implicit val kct = freeMap.kct
  implicit val vct = freeMap.vct

  /* ---------- API -------------- */
  val freeMap:FM
  val usedMap:M
  val defaultWeight:Int = 0
  val weights:W
  /* ---------- freeMap ---------- */
  def freeKeys = freeMap.keys
  def freeValues = freeMap.bmap.keys
  def freeValuesOf(k:K) = freeMap.fmap(k)
  def freeKeysOf(v:V) = freeMap.bmap(v)
  def filter(lambda:(K,V) => Boolean):EOption[S] = {
    def notLambda(k:K, v:V) = !lambda(k,v)
    filterNot(notLambda _)
  }
  def filterAt(k:K)(lambda:V => Boolean):EOption[S] = {
    def notLambda(v:V) = !lambda(v)
    filterNotAtKey(k)(notLambda)
  }
  def filterNot(lambda:(K,V) => Boolean):EOption[S] = {
    flatFold(freeMap.keys,this) { case (prev, k) =>
      prev.filterNotAtKey(k)(lambda(k, _))
    }
  }
  def filterNotAtKey(k:K)(lambda:V => Boolean):EOption[S] = {
    val vv = freeMap.fmap(k).filter { v => lambda(v) }
    newInstance(freeMap -- (k,vv), weights, usedMap).check(k)
  }
  def filterNotAtValue(v:V)(lambda:K => Boolean):EOption[S] = {
    val kk = freeMap.bmap(v).filter { k => lambda(k) }
    val nm = newInstance(freeMap -- (kk,v), weights, usedMap)
    flatFold(kk, nm) { case (nm, k) => nm.check(k) }
  }
  def ++ (pairs:(Any,Any)):S = newInstance(freeMap ++ pairs, weights, usedMap)
  def - (pair:(K,V)):EOption[S] = newInstance(freeMap - pair, weights, usedMap).check(pair._1)
  def - (x:Any):S = newInstance(freeMap - x, weights, usedMap)

  /* ---------- usedMap ---------- */
  def mappedKeys = usedMap.keys
  def mappedValues = usedMap.values
  def isMapped(x:Any) = x match {
    case x:K => usedMap.fmap.contains(x)
    case x:V => usedMap.bmap.contains(x)
  }
  def mappedValue(k:K) = usedMap.fmap(k)
  def mappedKey(v:V) = usedMap.bmap(v)
  def set(k:K, v:V):EOption[S]
  def apply(x:K):Set[V] 
  /* ----------- weights ------------ */
  def weight(k:K, v:V) = weights.getOrElse((k,v),defaultWeight)
  def setWeight(k:K,v:V, w:Int) = newInstance(freeMap, weights + (((k,v),w)), usedMap)
  def addWeight(k:K,v:V, w:Int) = setWeight(k,v,weight(k,v) + w)
  def weightedFilterAt(k:K)(lambda:V => Option[Int]):EOption[S] = {
    val vw = freeMap.fmap(k).map { v => (v, lambda(v)) }
    val vv = vw.flatMap { case (v, None) => Some(v); case (v, Some(w)) => None }
    val nfm = if (vv.isEmpty) freeMap else freeMap -- (k,vv)
    val nws = vw.foldLeft(weights) { 
      case (weights, (v, None)) => weights
      case (weights, (v, Some(w))) => weights + (((k,v), w))
    }
    newInstance(nfm, nws, usedMap).check(k)
  }
  def freeWeightedValues(k:K) = freeValuesOf(k).map { v => (v, weight(k,v)) }
  /* ------------------------------- */
  def keys = (freeKeys ++ mappedKeys).toSet
  def values = (freeValues ++ mappedValues).toSet

  /* --------------------------------- */
  protected def newInstance(freeMap:FM, weights:W, usedMap:M):S = {
    val constructor = this.getClass.getConstructors()(0)
    (constructor.getParameterTypes.toList match {
      case l if l == List(classOf[FM], classOf[W], usedMap.getClass, classOf[ClassTag[K]], classOf[ClassTag[V]]) => constructor.newInstance(freeMap, weights, usedMap, kct, vct)
      case l if l == List(classOf[FM], classOf[W], usedMap.getClass) => constructor.newInstance(freeMap, weights, usedMap)
    }).asInstanceOf[S]
  }
  protected def check(k:K):EOption[S] = if (freeMap.fmap(k).isEmpty) Left(InvalidFactorGraph(this, k)) else Right(this)
}

trait OneToOneFactorGraphLike[K,V,S<:OneToOneFactorGraphLike[K,V,S]] extends FactorGraphLike[K,V,S] { self:S =>
  type M = BiOneToOneMap[K,V]
  def set(k:K, v:V):EOption[S] = {
    if (usedMap.get(k) == Some(v)) return Right(this)
    assert(freeMap.fmap.get(k).fold(false) { vv => vv.contains(v) })
    val vv = freeMap.fmap(k)
    val kk = freeMap.bmap(v)
    val nfg = newInstance(freeMap - k - v, weights, usedMap + ((k,v)))
    flatFold(kk - k, nfg) { case (nfg, k) => nfg.check(k) }
  }
  def apply(x:K):Set[V] = x match {
    case x:K if isMapped(x) => Set(usedMap.fmap(x))
    case x:K => freeMap.fmap(x)
  }
}
case class OneToOneFactorGraph[K:ClassTag,V:ClassTag](
  freeMap:BiManyToManyMap[K,V],
  weights:Map[(K,V),Int],
  usedMap:BiOneToOneMap[K,V]
) extends OneToOneFactorGraphLike[K,V,OneToOneFactorGraph[K,V]]
object OneToOneFactorGraph {
  def empty[K:ClassTag, V:ClassTag] = OneToOneFactorGraph[K,V](
    BiManyToManyMap.empty, 
    Map.empty,
    BiOneToOneMap.empty
  )
}

trait OneToManyFactorGraphLike[K,V,S<:OneToManyFactorGraphLike[K,V,S]] extends FactorGraphLike[K,V,S] { self:S =>
  type M = BiOneToManyMap[K,V]
  def set(k:K, v:V):EOption[S] = {
    if (usedMap.get(k).fold(false) { _.contains(v) }) return Right(this)
    assert(freeMap.fmap.get(k).fold(false) { vv => vv.contains(v) })
    val kk = freeMap.bmap(v) - k
    val nfg = newInstance(freeMap - v, weights, usedMap + ((k,v)))
    flatFold(kk, nfg) { case (nfg, k) => nfg.check(k) }
  }
  def apply(x:K):Set[V] = x match {
    case x:K if isMapped(x) => usedMap.fmap(x) ++ freeMap(x)
    case x:K => freeMap.fmap(x)
  }
}
case class OneToManyFactorGraph[K:ClassTag,V:ClassTag](
  freeMap:BiManyToManyMap[K,V],
  weights:Map[(K,V),Int],
  usedMap:BiOneToManyMap[K,V]
) extends OneToManyFactorGraphLike[K,V,OneToManyFactorGraph[K,V]]
object OneToManyFactorGraph {
  def empty[K:ClassTag, V:ClassTag] = OneToManyFactorGraph[K,V](
    BiManyToManyMap.empty, 
    Map.empty,
    BiOneToManyMap.empty
  )
}

trait ManyToOneFactorGraphLike[K,V,S<:ManyToOneFactorGraphLike[K,V,S]] extends FactorGraphLike[K,V,S] { self:S =>
  type M = BiManyToOneMap[K,V]
  def set(k:K, v:V):EOption[S] = {
    if (usedMap.get(k) == Some(v)) return Right(this)
    assert(freeMap.fmap.get(k).fold(false) { vv => vv.contains(v) })
    val nfg = newInstance(freeMap - k, weights, usedMap + ((k,v)))
    Right(nfg)
  }
  def apply(x:K):Set[V] = x match {
    case x:K if isMapped(x) => Set(usedMap.fmap(x))
    case x:K => freeMap.fmap(x)
  }
}
case class ManyToOneFactorGraph[K:ClassTag,V:ClassTag](
  freeMap:BiManyToManyMap[K,V],
  weights:Map[(K,V),Int],
  usedMap:BiManyToOneMap[K,V]
) extends ManyToOneFactorGraphLike[K,V,ManyToOneFactorGraph[K,V]]
object ManyToOneFactorGraph {
  def empty[K:ClassTag, V:ClassTag] = ManyToOneFactorGraph[K,V](
    BiManyToManyMap.empty, 
    Map.empty,
    BiManyToOneMap.empty
  )
}

case class InvalidFactorGraph[K,V,S<:FG[K,V,S]](@transient fg:FG[K,V,S], k:K) extends MappingFailure {
  val msg = s"InvalidFactorGraph ${fg.getClass.getSimpleName} at key=$k"
}
