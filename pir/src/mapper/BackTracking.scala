package pir.mapper

trait BackTracking extends prism.mapper.BackTracking { self:Logging =>
  def minOptionBy[A, B:Ordering](ks:Iterable[A])(lambda:A => B) = if (ks.isEmpty) None else Some(ks.minBy(lambda))

  def bind[P, S, F<:FactorGraphLike[P,S,F]](
    init:F
  ):EOption[F] = {
    super.bind[P,S,F](
      pnodes=(m:F) => minOptionBy(m.keys) { case k => m(k).size },
      snodes=(p:P, m:F) => m(p).toList.sortBy { case v => -m.bmap(v).size},
      init=init,
      bindLambda=(p:P,s:S,m:F) => m.set(p,s)
    )
  }
}
