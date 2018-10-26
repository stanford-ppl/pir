package prism
package graph

import prism.util._

trait GraphUtil {

  /*
   * Visit from buttom up
   * */
  def visitUp(n:N):List[N] = n.parent.toList

  /*
   * Visit subgraph
   * */
  def visitDown(n:N):List[N] = n.children

  /*
   * Visit siblings
   * */
  def visitPeer(n:N):List[N] = n.parent.map { p => p.children.filterNot(_ == n) }.getOrElse(Nil)

  /*
   * Visit inputs of a node
   * */
  def visitLocalIn(n:N):List[N] = n.siblingDeps.toList

  /*
   * Visit outputs of a node 
   * */
  def visitLocalOut(n:N):List[N] = n.siblingDepeds.toList

  def visitLocal(n:N):List[N] = visitLocalIn(n) ++ visitLocalOut(n) 

  def visitGlobalIn(n:N):List[N] = n.deps.toList
  def visitGlobalOut(n:N):List[N] = n.depeds.toList

  def leastCommonAncesstor(n1:N, n2:N):Option[N] = {
    ((n1 :: n1.ancestors) intersect (n2 :: n2.ancestors)).headOption
  }

  def leastCommonAncesstor(ns:Iterable[N]):Option[N] = {
    val head::rest = ns.toList
    rest.foldLeft[Option[N]](Some(head)){ 
      case (Some(lca), n) => leastCommonAncesstor(lca, n)
      case (None, n) => None
    }
  }

  def leastMatchedPeers(ns:Iterable[N], lca:N):Map[N, N] = {
    ns.map { n =>
      n -> (if (n == lca) n else {
        val ancestors = n :: n.ancestors
        val idx = ancestors.indexOf(lca)
        ancestors(idx-1)
      })
    }.toMap
  }

  def leastMatchedPeers(ns:Iterable[N]):Option[Map[N, N]] = {
    leastCommonAncesstor(ns).map { lca =>
      leastMatchedPeers(ns, lca)
    }
  }

}

trait GraphUtilImplicits {
  implicit class NodeUtil(node:N) {
    def visitUp:List[N] = graph.visitUp(node)
    def visitDown:List[N] = graph.visitDown(node)
    def visitPeer:List[N] = graph.visitPeer(node)
    def visitLocalIn:List[N] = graph.visitLocalIn(node)
    def visitLocalOut:List[N] = graph.visitLocalOut(node)
    def visitLocal:List[N] = graph.visitLocal(node)
    def visitGlobalIn:List[N] = graph.visitGlobalIn(node)
    def visitGlobalOut:List[N] = graph.visitGlobalOut(node)
    def leastCommonAncesstor(n2:N):Option[N] = graph.leastCommonAncesstor(node, n2)
  }
}
