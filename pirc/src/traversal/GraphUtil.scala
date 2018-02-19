package prism.traversal

import prism.node._
import pirc.util._

trait GraphUtil {

  /*
   * Visit from buttom up
   * */
  def visitUp[N<:Node[N]](n:N):List[N] = n.parent.toList

  /*
   * Visit subgraph
   * */
  def visitDown[N<:Node[N]](n:N):List[N] = n.children

  /*
   * Visit inputs of a node
   * */
  def visitLocalIn[N<:Node[N]](n:N):List[N] = n.localDeps.toList

  /*
   * Visit outputs of a node 
   * */
  def visitLocalOut[N<:Node[N]](n:N):List[N] = n.localDepeds.toList


  def visitGlobalIn[N<:Node[N]](n:N):List[N] = n.deps.toList
  def visitGlobalOut[N<:Node[N]](n:N):List[N] = n.depeds.toList

  def leastCommonAncesstor[N<:Node[N]](n1:N, n2:N):Option[N] = {
    ((n1 :: n1.ancestors) intersect (n2 :: n2.ancestors)).headOption
  }

  def leastCommonAncesstor[N<:Node[N]](ns:Iterable[N]):Option[N] = {
    val head::rest = ns.toList
    rest.foldLeft[Option[N]](Some(head)){ 
      case (Some(lca), n) => leastCommonAncesstor(lca, n)
      case (None, n) => None
    }
  }

  def leastMatchedPeers[N<:Node[N]](ns:Iterable[N], lca:Option[N]):Option[Map[N, N]] = {
    lca.map { lca =>
      ns.map { n =>
        n -> (if (n == lca) n else {
          val ancestors = n :: n.ancestors
          val idx = ancestors.indexOf(lca)
          ancestors(idx-1)
        })
      }.toMap
    }
  }

  def leastMatchedPeers[N<:Node[N]](ns:Iterable[N]):Option[Map[N, N]] = {
    leastMatchedPeers(ns, leastCommonAncesstor(ns))
  }

}
