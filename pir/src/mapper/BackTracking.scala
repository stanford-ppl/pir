package pir.mapper

import prism.collection.immutable._

trait BackTracking extends prism.mapper.BackTracking { self:Logging =>
  def bind[P, S, F<:OneToOneFactorGraphLike[P,S,F]](
    init:F,
  ):EOption[F] = {
    bind[P,S,F](
      pnodes=init.freeKeys.toSeq,
      snodes=(p:P, m:F) => m(p).toList.sortBy { case v => -m.freeKeys(v).size},
      init=init,
      bindLambda=(p:P,s:S,m:F) => m.set(p,s)
    )
  }

}
