package prism
package graph

import implicits._
import prism.util._

trait GraphUtil {

  /*
   * Visit from buttom up
   * */
  def visitUp[N<:Node[N]](n:Node[N]):List[N] = n.parent.toList

  /*
   * Visit subgraph
   * */
  def visitDown[N<:Node[N]](n:Node[N]):List[N] = n.children

  /*
   * Visit siblings
   * */
  def visitPeer[N<:Node[N]](n:Node[N]):List[N] = n.parent.map { p => p.children.filterNot(_ == n) }.getOrElse(Nil)

  /*
   * Visit inputs of a node
   * */
  def visitLocalIn[N<:Node[N]](n:Node[N]):List[N] = n.siblingDeps().toList

  /*
   * Visit outputs of a node 
   * */
  def visitLocalOut[N<:Node[N]](n:Node[N]):List[N] = n.siblingDepeds().toList

  def visitLocal[N<:Node[N]](n:Node[N]):List[N] = visitLocalIn(n) ++ visitLocalOut(n) 

  def visitGlobalIn[N<:Node[N]](n:Node[N]):List[N] = n.deps().toList
  def visitGlobalOut[N<:Node[N]](n:Node[N]):List[N] = n.depeds().toList

  /*
   * For each node returned by visitFunc, swap node to its ancesstor with type T if it has one.
   * Otherwise keep the node
   * */
  def lift[N<:Node[N],T<:N:ClassTag](visitFunc:Node[N] => List[N])(n:Node[N]) = visitFunc(n).map { x =>
    x.ancestors.collectFirst{ case x:T => x }.getOrElse(x)
  }.distinct

  /*
   * For each node returned by visitFunc, include its ancesstor with type T and ancesstor's
   * descendents if it has one. Otherwise keep the node
   * */
  def cover[N<:Node[N],T<:N:ClassTag](ns:List[Node[N]]):List[N] = ns.flatMap { x =>
    x.ancestors.collectFirst { case x:T => x }.map { _.descendentTree.toList }.getOrElse(List(x.as[N]))
  }.distinct

  def leastCommonAncesstor[N<:Node[N]](n1:Node[N], n2:Node[N]):Option[N] = {
    (n1.ancestorTree intersect n2.ancestorTree).headOption
  }

  def leastCommonAncesstor[N<:Node[N]](ns:Iterable[Node[N]]):Option[N] = {
    val head::rest = ns.toList
    rest.foldLeft[Option[N]](Some(head)){ 
      case (Some(lca), n) => leastCommonAncesstor(lca, n)
      case (None, n) => None
    }
  }

  def leastMatchedPeers[N<:Node[N]](ns:Seq[Node[N]], lca:Node[N]):Map[N, N] = {
    ns.map { n =>
      n.as[N] -> (if (n == lca) n else {
        val ancestors = n.ancestorTree
        val idx = ancestors.indexOf(lca)
        ancestors(idx-1)
      }).as[N]
    }.toMap
  }

  def leastMatchedPeers[N<:Node[N]](ns:Seq[Node[N]]):Option[Map[N, N]] = {
    leastCommonAncesstor(ns).map { lca =>
      leastMatchedPeers(ns, lca)
    }
  }

}

trait GraphUtilImplicits {
  implicit class NodeUtil[N<:Node[N]](node:Node[N]) {
    def visitUp:List[N] = graph.visitUp(node)
    def visitDown:List[N] = graph.visitDown(node)
    def visitPeer:List[N] = graph.visitPeer(node)
    def visitLocalIn:List[N] = graph.visitLocalIn(node)
    def visitLocalOut:List[N] = graph.visitLocalOut(node)
    def visitLocal:List[N] = graph.visitLocal(node)
    def visitGlobalIn:List[N] = graph.visitGlobalIn(node)
    def visitGlobalOut:List[N] = graph.visitGlobalOut(node)
    def leastCommonAncesstor(n2:Node[N]):Option[N] = graph.leastCommonAncesstor(node, n2)
  }
  //implicit def field[T](f:Field[T]):T = f.T
}
