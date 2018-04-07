package prism.mapper

import prism._

trait BackTracking { self:Logging => 
  def minOptionBy[A, B:Ordering](ks:Iterable[A])(lambda:A => B) = if (ks.isEmpty) None else Some(ks.minBy(lambda))

  def bind[P, S, M](
    pnodes:Set[P], 
    snodes:(P,M) => List[S],
    init:M, 
    bindLambda:(P,S,M) => EOption[M]
  ):EOption[M] = {
    minOptionBy(pnodes) { case k => snodes(k, init).size }.fold[EOption[M]](Right(init)) { pnode =>
      snodes(pnode, init).foldLeft[EOption[M]](Left(EmptyBinding(pnode))) {
        case (Right(m), snode) => Right(m)
        case (Left(f), snode) => bindLambda(pnode, snode, init)
      }.flatMap { m => bind(pnodes - pnode, snodes, m, bindLambda) }
    }
  }

}
