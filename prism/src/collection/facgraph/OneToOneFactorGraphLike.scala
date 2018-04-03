package prism.collection.immutable

import prism._
import prism.mapper._

trait OneToOneFactorGraphLike[K,V,S<:OneToOneFactorGraphLike[K,V,S]] extends FactorGraphLike[K,V,S] { self:S =>
  def set(k:K, v:V):EOption[S] = {
    assert(fmap.get(k).fold(false) { vv => vv.contains(v) })
    val vv = fmap(k) - v
    val kk = bmap(v) - k
    var nfg = this
    nfg --= ((k, vv))
    nfg ---= ((kk, v))
    flatFold(kk, nfg) { case (nfg, k) => nfg.check(k) }
  }
}

case class OneToOneFactorGraph[K:ClassTag,V:ClassTag](
  fmap:OneToManyMap[K,V], 
  bmap:OneToManyMap[V,K]
) extends OneToOneFactorGraphLike[K,V,OneToOneFactorGraph[K,V]]
object OneToOneFactorGraph {
  def empty[K:ClassTag, V:ClassTag] = OneToOneFactorGraph[K,V](OneToManyMap.empty, OneToManyMap.empty)
}
