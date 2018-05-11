package prism
package mapper

trait BackTrackingMatch extends MappingLogging {
  def minOptionBy[A, B:Ordering](ks:Iterable[A])(lambda:A => B) = if (ks.isEmpty) None else Some(ks.minBy(lambda))

  def bind[P, S, M](
    pnodes:Seq[P], 
    snodes:(P,M) => List[S],
    init:M, 
    bindLambda:(P,S,M) => EOption[M]
  ):EOption[M] = {
    minOptionBy(pnodes) { case k => snodes(k, init).size }.fold[EOption[M]](Right(init)) { pnode =>
      val sns = snodes(pnode, init)
      dbgblk(s"Mapping ${quote(pnode)} => ${sns.map(quote)} (remain:${pnodes.size-1})",buffer=false) {
        sns.foldLeft[Either[BindingTrace[P,M],M]](Left(BindingTrace(pnode, init))) { case (prev, snode) =>
          prev.left.flatMap { f =>
            dbgblk(s"Try ${quote(pnode)} -> ${quote(snode)} (remain:${pnodes.size-1})",buffer=false) {
              val nextTry = bindLambda(pnode, snode, init).flatMap { m => bind(pnodes.filterNot { _ == pnode }, snodes, m, bindLambda) }
              nextTry.left.foreach { failure => dbg(s"${failure}") }
              f.append(nextTry)
            }
          }
        }
      }
    }
  }

}
