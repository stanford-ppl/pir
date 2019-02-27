package prism
package mapper

trait BackTrackingMatch extends MappingLogging {
  def maxOptionBy[A, B:Ordering](ks:Iterable[A])(lambda:A => B) = if (ks.isEmpty) None else Some(ks.maxBy(lambda))

  def bind[P, S, M, SR:Ordering, PR:Ordering](
    pnodes:Seq[P], 
    snodes:(P,M) => Seq[S],
    init:M, 
    bindLambda:(P,S,M) => EOption[M]
  )(
    rankS:(P,S,M) => SR, // higher rank gets used first
    rankP:(P,M) => PR = (p:P, m:M) => -snodes(p, m).size // higher rank gets mapped first
  ):EOption[M] = {
    maxOptionBy(pnodes) { p => rankP(p, init) }.fold[EOption[M]](Right(init)) { pnode =>
      val sns = snodes(pnode, init).sortBy(s => rankS(pnode,s,init))(implicitly[Ordering[SR]].reverse)
      dbgblk(s"Mapping ${dquote(pnode)} => ${sns.map(dquote)} (remain:${pnodes.size-1})",buffer=false) {
        sns.foldLeft[Either[BindingTrace[P,M],M]](Left(BindingTrace(pnode, init))) { case (prev, snode) =>
          prev.left.flatMap { f =>
            dbgblk(s"Try ${dquote(pnode)} -> ${dquote(snode)} (remain:${pnodes.size-1})",buffer=false) {
              val nextTry = bindLambda(pnode, snode, init).flatMap { m => bind(pnodes.filterNot { _ == pnode }, snodes, m, bindLambda)(rankS,rankP) }
              nextTry.left.foreach { failure => dbg(s"${failure}") }
              f.append(nextTry)
            }
          }
        }
      }
    }
  }

}
