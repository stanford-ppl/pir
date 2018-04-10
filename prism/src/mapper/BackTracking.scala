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
    minOptionBy(pnodes) { case k => 
      val ssize = snodes(k, init).size
      dbg(s"${quote(k)} -> $ssize")
      ssize
    }.fold[EOption[M]](Right(init)) { pnode =>
      val sns = snodes(pnode, init)
      dbgblk(s"Mapping ${quote(pnode)} => ${sns.map(quote)}") {
        sns.foldLeft[Either[BindingTrace[P],M]](Left(BindingTrace(pnode))) {
          case (Right(m), snode) => Right(m)
          case (Left(f), snode) => 
            dbgblk(s"Try ${quote(pnode)} -> ${quote(snode)}") {
              f.append(bindLambda(pnode, snode, init).flatMap { m => bind(pnodes - pnode, snodes, m, bindLambda) })
            }
        }
      }
    }
  }

}
