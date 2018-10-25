package prism
package graph

/*
 * Traverse the graph until hit node satisfying prefix or depth = 0
 * Accumulate the result based on accumulate function
 * */
class PrefixTraversal[TT](
  prefix:Node[_] => Boolean, 
  vf:Node[_] => List[Node[_]], 
  accumulate:(TT, Node[_]) => TT, 
  val zero:TT,
  logging:Option[Logging]
) extends DFSTraversal {
  type N = (Node[_], Int)
  type T = TT

  override def isVisited(n:N) = {
    val (node, depth) = n
    visited.contains(node)
  }
  // depth = -1 is infinate depth
  def withinDepth(depth:Int) = (depth > 0 || depth < 0)

  override def visitNode(n:N, prev:T):T = prism.dbgblk(logging, s"visitNode($n, depth=${n._2})") {
    val (node, depth) = n
    visited += node
    val pfx = prefix(node)
    node match {
      case _ if withinDepth(depth) & pfx => accumulate(prev, node)
      case _ if withinDepth(depth) => super.visitNode(n, accumulate(prev, node))
      case _ => prev 
    }
  }

  def visitFunc(n:N):List[N] = {
    val (node, depth) = n
    vf(node).map { next => (next, depth-1) }
  }
}

trait CollectorUtil { self =>

  def filter(node:N, prefix:N => Boolean, visitFunc:N => List[N], depth:Int = -1, logger:Option[Logging]=None):List[N] = 
    dbgblk(logger, s"filter($node, depth=$depth)") {
      def accumulate(prev:List[N], n:N) = {
        if (!prev.contains(n) && prefix(n)) (prev :+ n) else prev
      }
      new PrefixTraversal[List[N]](prefix, visitFunc, accumulate _, Nil, logger).traverseNode((node, depth), Nil)
    }
 
  def collect[M<:N:ClassTag](node:N, visitFunc:N => List[N], depth:Int = -1, logger:Option[Logging]=None):List[M] = 
    dbgblk(logger, s"collect($node, depth=$depth)") {
      def prefix(n:N) = n match { case `node` => false; case n:M => true; case _ => false }
      def accumulate(prev:List[M], n:N) = {
        if (!prev.contains(n) && prefix(n)) (prev :+ n.asInstanceOf[M]) else prev
      }
      new PrefixTraversal[List[M]](prefix, visitFunc, accumulate _, Nil, logger).traverseNode((node, depth), Nil)
    }

  def collectUp[M<:N:ClassTag](node:N, depth:Int= -1, logger:Option[Logging]=None):List[M] =
    collect[M](node, visitUp _, depth, logger)

  def collectDown[M<:N:ClassTag:TypeTag](node:N, depth:Int= -1, logger:Option[Logging]=None):List[M] = 
    collect[M](node, visitDown _, depth, logger)

  def collectIn[M<:N:ClassTag](node:N, depth:Int= -1, logger:Option[Logging]=None):List[M] = 
    collect[M](node, visitLocalIn _, depth, logger)

  def collectOut[M<:N:ClassTag](node:N, depth:Int= -1, logger:Option[Logging]=None):List[M] = 
    collect[M](node, visitLocalOut _, depth, logger)

  def collectPeer[M<:N:ClassTag](node:N, depth:Int= -1, logger:Option[Logging]=None):List[M] =  {
    collect[M](node, visitPeer _, depth, logger)
  }

  def accum(node:N, prefix:N => Boolean, visitFunc:N => List[N], depth:Int= -1, logger:Option[Logging]=None):List[N] = 
    dbgblk(logger, s"accum(depth=$depth)"){
      def accumulate(prev:List[N], n:N) = {
        if (!prev.contains(n)) (prev :+ n) else prev
      }
      new PrefixTraversal[List[N]](prefix, visitFunc, accumulate _, Nil, logger).traverseNode((node, depth), Nil)
    }


  def accumIn(node:N, prefix:N => Boolean, depth:Int= -1, logger:Option[Logging]=None):List[N] = 
      accum(node, prefix, visitLocalIn _, depth, logger)

  def accumTill[M<:N:ClassTag](node:N, visitFunc:N => List[N]=visitLocalIn _, depth:Int= -1, logger:Option[Logging]=None):List[N] = {
      def prefix(n:N) = n match { case `node` => false; case n:M => true; case _ => false }
    accum(node, prefix _, visitFunc, depth, logger)
  }

  def canReach(node:N, target:N, visitFunc:N => List[N], depth:Int= -1, logger:Option[Logging]=None):Boolean = 
    dbgblk(logger, s"canReach($target, depth=$depth)"){
      def prefix(n:N) = n == target
      def accumulate(prev:Boolean, n:N) = prefix(n) || prev
      new PrefixTraversal[Boolean](prefix, visitFunc, accumulate _, false, logger).traverseNode((node, depth), false)
    }

  def areLinealInherited(node:N, that:N, logger:Option[Logging]=None):Boolean = 
    dbgblk(logger, s"areLinealInherited($node, $that)") {
      node == that || node.ancestors.contains(that) || that.ancestors.contains(node)
    }

}

trait CollectorImplicit {
  implicit class NodeCollector(node:N) {
    def filter(prefix:N => Boolean, visitFunc:N => List[N], depth:Int = -1, logger:Option[Logging]=None):List[N] = 
      graph.filter(node, prefix, visitFunc, depth, logger)
 
    def collect[M<:N:ClassTag](visitFunc:N => List[N], depth:Int = -1, logger:Option[Logging]=None):List[M] = 
      graph.collect(node, visitFunc, depth, logger)

    def collectUp[M<:N:ClassTag](depth:Int= -1, logger:Option[Logging]=None):List[M] =
      graph.collectUp(node, depth, logger)

    def collectDown[M<:N:ClassTag:TypeTag](depth:Int= -1, logger:Option[Logging]=None):List[M] = 
      graph.collectDown(node, depth, logger)

    def collectIn[M<:N:ClassTag](depth:Int= -1, logger:Option[Logging]=None):List[M] = 
      graph.collectIn(node, depth, logger)

    def collectOut[M<:N:ClassTag](depth:Int= -1, logger:Option[Logging]=None):List[M] = 
      graph.collectOut(node, depth, logger)

    def collectPeer[M<:N:ClassTag](depth:Int= -1, logger:Option[Logging]=None):List[M] =
      graph.collectPeer(node, depth, logger)

    def accum(prefix:N => Boolean, visitFunc:N => List[N], depth:Int= -1, logger:Option[Logging]=None):List[N] = 
      graph.accum(node, prefix, visitFunc, depth, logger)

    def accumIn(prefix:N => Boolean, depth:Int= -1, logger:Option[Logging]=None):List[N] = 
      graph.accumIn(node, prefix, depth, logger)

    def accumTill[M<:N:ClassTag](visitFunc:N => List[N]=visitLocalIn _, depth:Int= -1, logger:Option[Logging]=None):List[N] = {
      def prefix(n:N) = n match { case `node` => false; case n:M => true; case _ => false }
      graph.accumTill(node, visitFunc, depth, logger)
    }

    def canReach(target:N, visitFunc:N => List[N], depth:Int= -1, logger:Option[Logging]=None):Boolean = 
      graph.canReach(node, target, visitFunc, depth, logger)

    def areLinealInherited(that:N, logger:Option[Logging]=None):Boolean = 
      graph.areLinealInherited(node, that, logger)
  }
}
