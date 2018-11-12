package pir
package mapper

import prism.collection.immutable._

trait BackTrackingMatch extends prism.mapper.BackTrackingMatch { self:Logging =>
  def bind[P, S, F<:OneToOneFactorGraphLike[P,S,F]](
    init:F,
  ):EOption[F] = {
    bind[P,S,F,Int,Int](
      pnodes=init.freeKeys.toSeq,
      snodes=(p:P, m:F) => m(p).toList,
      init=init,
      bindLambda=(p:P,s:S,m:F) => m.set(p,s)
    )(
      rankS = (p:P, s:S, m:F) => m.freeKeys(s).size
    )
  }

}
