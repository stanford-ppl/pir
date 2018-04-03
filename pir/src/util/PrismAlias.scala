package pir.util

trait PrismAlias extends prism.Alias with prism.util.Misc with prism.mapper.Alias with prism.enums.Ops {
  type Pass = prism.Pass
  type RunPass[P<:Pass] = prism.RunPass[P]
  val OneToManyMap = prism.collection.immutable.OneToManyMap
  type OneToManyMap[K,V] = prism.collection.immutable.OneToManyMap[K,V]
  type FactorGraphLike[K,V,S] = prism.collection.immutable.FactorGraphLike[K,V,S]
  val InvalidFactorGraph = prism.collection.immutable.InvalidFactorGraph
  val Config = prism.Config
  type Op = prism.enums.Op
}
