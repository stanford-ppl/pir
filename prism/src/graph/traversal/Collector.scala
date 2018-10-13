package prism
package graph

trait Collector extends GraphUtil with Logging {

  /*
   * Traverse the graph until hit node satisfying prefix or depth = 0
   * Accumulate the result based on accumulate function
   * */
  class PrefixTraversal[TT](
    prefix:Node[_] => Boolean, 
    vf:Node[_] => List[Node[_]], 
    accumulate:(TT, Node[_]) => TT, 
    val zero:TT,
    logging:Boolean
  ) extends DFSTraversal with GraphUtil {
    type N = (Node[_], Int)
    type T = TT

    override def isVisited(n:N) = {
      val (node, depth) = n
      visited.contains(node)
    }
    // depth = -1 is infinate depth
    def withinDepth(depth:Int) = (depth > 0 || depth < 0)

    override def visitNode(n:N, prev:T):T = dbgblk(logging, s"visitNode($n, depth=${n._2})") {
      val (node, depth) = n
      visited += node
      node match {
        case _ if withinDepth(depth) & prefix(node) => accumulate(prev, node)
        case _ if withinDepth(depth) => super.visitNode(n, accumulate(prev, node))
        case _ => prev 
      }
    }

    def visitFunc(n:N):List[N] = {
      val (node, depth) = n
      vf(node).map { next => (next, depth-1) }
    }
  }

  implicit class NodeCollector(node:N) {

    def filter(prefix:N => Boolean, visitFunc:N => List[N], depth:Int = -1, logging:Boolean=false):List[N] = 
      dbgblk(logging, s"filter($node, depth=$depth)") {
        def accumulate(prev:List[N], n:N) = {
          if (!prev.contains(n) && prefix(n)) (prev :+ n) else prev
        }
        new PrefixTraversal[List[N]](prefix, visitFunc, accumulate _, Nil, logging).traverseNode((node, depth), Nil)
      }
 
    def collect[M<:N:ClassTag](visitFunc:N => List[N], depth:Int = -1, logging:Boolean=false):List[M] = 
      dbgblk(logging, s"collect($node, depth=$depth)") {
        def prefix(n:N) = n match { case `node` => false; case n:M => true; case _ => false }
        def accumulate(prev:List[M], n:N) = {
          if (!prev.contains(n) && prefix(n)) (prev :+ n.asInstanceOf[M]) else prev
        }
        new PrefixTraversal[List[M]](prefix, visitFunc, accumulate _, Nil, logging).traverseNode((node, depth), Nil)
      }

    def collectUp[M<:N:ClassTag](depth:Int= -1, logging:Boolean=false):List[M] =
      collect[M](visitUp _, depth, logging)

    def collectDown[M<:N:ClassTag:TypeTag](depth:Int= -1, logging:Boolean=false):List[M] = 
      collect[M](visitDown _, depth, logging)

    def collectIn[M<:N:ClassTag](depth:Int= -1, logging:Boolean=false):List[M] = 
      collect[M](visitLocalIn _, depth, logging)

    def collectOut[M<:N:ClassTag](depth:Int= -1, logging:Boolean=false):List[M] = 
      collect[M](visitLocalOut _, depth, logging)

    def collectPeer[M<:N:ClassTag](depth:Int= -1, logging:Boolean=false):List[M] =  {
      collect[M](visitPeer _, depth, logging)
    }

    def accum(prefix:N => Boolean, visitFunc:N => List[N], depth:Int= -1, logging:Boolean=false):List[N] = 
      dbgblk(logging, s"accum(depth=$depth)"){
        def accumulate(prev:List[N], n:N) = {
          if (!prev.contains(n)) (prev :+ n) else prev
        }
        new PrefixTraversal[List[N]](prefix, visitFunc, accumulate _, Nil, logging).traverseNode((node, depth), Nil)
      }

    def canReach(target:N, visitFunc:N => List[N], depth:Int= -1, logging:Boolean=false):Boolean = 
      dbgblk(logging, s"canReach($target, depth=$depth)"){
        def prefix(n:N) = n == target
        def accumulate(prev:Boolean, n:N) = prefix(n) || prev
        new PrefixTraversal[Boolean](prefix, visitFunc, accumulate _, false, logging).traverseNode((node, depth), false)
      }

    def areLinealInherited(that:N, logging:Boolean=false):Boolean = 
      dbgblk(logging, s"areLinealInherited($node, $that)") {
        node == that || node.ancestors.contains(that) || that.ancestors.contains(node)
      }
  }

}
