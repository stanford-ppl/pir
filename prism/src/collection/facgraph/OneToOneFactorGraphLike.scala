package prism.collection.immutable

import prism.mapper._

trait OneToOneFactorGraphLike[K,V,S<:OneToOneFactorGraphLike[K,V,S]] extends FactorGraphLike[K,V,S] { self:S =>
  def set(k:K, v:V):EOption[S] = {
    assert(fmap.get(k).fold(false) { vv => vv.contains(v) })
    val vv = fmap(k) - v
    val kk = bmap(v) - k
    var nfg = this
    nfg --= ((k, vv))
    nfg --= ((kk, v))
    flatFold(kk, nfg) { case (nfg, k) => nfg.check(k) }
  }

  def freeKeys = keys.filter { k => !isMapped(k) }
  def mappedKeys = keys.filter { k => isMapped(k) }
  def mapped = fmap.map.flatMap { case (k, vv) => if (isMapped(k)) Some((k,vv.head)) else None }

  override def isMapped(x:Any) = x match {
    case AsK(x) => fmap(x).size==1
    case AsV(x) => bmap(x).size==1
  }

  def mappedValue(k:K) = {
    if (fmap(k).size==1) Some(fmap(k).head) else None
  }
  def mappedKey(v:V) = {
    if (bmap(v).size==1) Some(bmap(v).head) else None
  }
}

case class OneToOneFactorGraph[K:ClassTag,V:ClassTag](
  fmap:OneToManyMap[K,V], 
  bmap:OneToManyMap[V,K]
) extends OneToOneFactorGraphLike[K,V,OneToOneFactorGraph[K,V]]
object OneToOneFactorGraph {
  def empty[K:ClassTag, V:ClassTag] = OneToOneFactorGraph[K,V](OneToManyMap.empty, OneToManyMap.empty)
}
