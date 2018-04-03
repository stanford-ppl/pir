package prism.collection.immutable

import prism.mapper._

trait OneToManyFactorGraphLike[K,V,S<:OneToManyFactorGraphLike[K,V,S]] extends FactorGraphLike[K,V,S] { self:S =>
  def set(k:K, v:V):EOption[S] = {
    assert(fmap.get(k).fold(false) { vv => vv.contains(v) })
    val kk = bmap(v) - k
    var nfg = this
    nfg ---= ((kk, v))
    flatFold(kk, nfg) { case (nfg, k) => nfg.check(k) }
  }
}

