package prism.collection.immutable

import prism.mapper._

trait FactorGraphLike[K,V,S<:FactorGraphLike[K,V,S]] { self:S =>
  type M <: BiMap[K,V,M]
  implicit val kct = freeMap.kct
  implicit val vct = freeMap.vct
  val freeMap:BiManyToManyMap[K,V]
  val usedMap:M
  def newInstance(freeMap:BiManyToManyMap[K,V], usedMap:M):S = {
    val constructor = this.getClass.getConstructors()(0)
    (constructor.getParameterTypes.toList match {
      case l if l == List(freeMap.getClass, usedMap.getClass, classOf[ClassTag[K]], classOf[ClassTag[V]]) => constructor.newInstance(freeMap, usedMap, kct, vct)
      case l if l == List(freeMap.getClass, usedMap.getClass) => constructor.newInstance(freeMap, usedMap)
    }).asInstanceOf[S]
  }
  /* ---------- freeMap ---------- */
  def freeKeys = freeMap.keys
  def freeValues = freeMap.values
  def freeValues(k:K) = freeMap.fmap(k)
  def freeKeys(v:V) = freeMap.bmap(v)
  def filter(lambda:(K,V) => Boolean):EOption[S] = {
    def notLambda(k:K, v:V) = !lambda(k,v)
    filterNot(notLambda _)
  }
  def filterNot(lambda:(K,V) => Boolean):EOption[S] = {
    val pairs = freeMap.fmap.map.toSeq.map { case (k,vv) =>
      (k, vv.filter { v => lambda(k,v) })
    }
    val nfm = pairs.foldLeft(freeMap) { case (prev, (k, vv)) => prev -- (k,vv) }
    flatFold(freeMap.keys,newInstance(nfm, usedMap)) { case (prev, k) =>
      prev.check(k)
    }
  }
  def filterNot(k:K)(lambda:V => Boolean):EOption[S] = {
    val vv = freeMap.fmap(k).filter { v => lambda(v) }
    newInstance(freeMap -- (k,vv), usedMap).check(k)
  }
  def check(k:K):EOption[S] = if (freeMap.fmap(k).isEmpty) Left(InvalidFactorGraph(this, k)) else Right(this)
  def ++ (pairs:(Any,Any)):S = newInstance(freeMap ++ pairs, usedMap)
  def - (pair:(K,V)):EOption[S] = newInstance(freeMap - pair, usedMap).check(pair._1)

  /* ---------- usedMap ---------- */
  def mappedKeys = usedMap.keys
  def isMapped(x:Any) = x match {
    case x:K => usedMap.fmap.contains(x)
    case x:V => usedMap.bmap.contains(x)
  }
  def mappedValue(k:K) = usedMap.fmap(k)
  def mappedKey(v:V) = usedMap.bmap(v)
  def set(k:K, v:V):EOption[S]
  def apply(x:K):Set[V] 
}

trait OneToOneFactorGraphLike[K,V,S<:OneToOneFactorGraphLike[K,V,S]] extends FactorGraphLike[K,V,S] { self:S =>
  type M = BiOneToOneMap[K,V]
  def set(k:K, v:V):EOption[S] = {
    assert(freeMap.fmap.get(k).fold(false) { vv => vv.contains(v) })
    val vv = freeMap.fmap(k)
    val kk = freeMap.bmap(v)
    var nfm = freeMap
    nfm --= ((k, vv))
    nfm --= ((kk, v))
    val nfg = newInstance(nfm, usedMap + ((k,v)))
    flatFold(kk - k, nfg) { case (nfg, k) => nfg.check(k) }
  }
  def apply(x:K):Set[V] = x match {
    case x:K if isMapped(x) => Set(usedMap.fmap(x))
    case x:K => freeMap.fmap(x)
  }
}
case class OneToOneFactorGraph[K:ClassTag,V:ClassTag](
  freeMap:BiManyToManyMap[K,V],
  usedMap:BiOneToOneMap[K,V]
) extends OneToOneFactorGraphLike[K,V,OneToOneFactorGraph[K,V]]
object OneToOneFactorGraph {
  def empty[K:ClassTag, V:ClassTag] = OneToOneFactorGraph[K,V](
    BiManyToManyMap.empty, 
    BiOneToOneMap.empty
  )
}

trait OneToManyFactorGraphLike[K,V,S<:OneToManyFactorGraphLike[K,V,S]] extends FactorGraphLike[K,V,S] { self:S =>
  type M = BiOneToManyMap[K,V]
  def set(k:K, v:V):EOption[S] = {
    assert(freeMap.fmap.get(k).fold(false) { vv => vv.contains(v) })
    val kk = freeMap.bmap(v) - k
    var nfm = freeMap
    nfm --= ((kk, v))
    val nfg = newInstance(nfm, usedMap + ((k,v)))
    flatFold(kk, nfg) { case (nfg, k) => nfg.check(k) }
  }
  def apply(x:K):Set[V] = x match {
    case x:K if isMapped(x) => usedMap.fmap(x)
    case x:K => freeMap.fmap(x)
  }
}

case class InvalidFactorGraph[K,FG<:FactorGraphLike[K,_,FG]](@transient fg:FG, k:K) extends MappingFailure {
  val msg = s"InvalidFactorGraph ${fg.getClass.getSimpleName} at key=$k"
}


