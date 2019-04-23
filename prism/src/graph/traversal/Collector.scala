package prism
package graph

/*
 * Traverse the graph until hit node satisfying prefix or depth = 0
 * Accumulate the result based on accumulate function
 * */
class PrefixTraversal[NN<:Node[NN],TT](
  prefix:NN => Boolean, 
  vf:NN => List[NN], 
  accumulate:(TT, NN) => TT, 
  val zero:TT,
  logging:Option[Logging]
) extends DFSTraversal {
  type N = (NN, Int)
  type T = TT

  override def isVisited(n:Any) = {
    val (node, depth) = n
    super.isVisited(node)
  }
  // depth = -1 is infinate depth
  def withinDepth(depth:Int) = (depth > 0 || depth < 0)

  override def visitNode(n:N, prev:T):T = prism.dbgblk(logging, s"visitNode($n, depth=${n._2})") {
    val (node, depth) = n
    markVisited(node)
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

trait CollectorImplicit {
  implicit class NodeCollector[N<:Node[N]](node:Node[N]) {
    def filter(prefix:N => Boolean, visitFunc:N => List[N], depth:Int = -1, logger:Option[Logging]=None):List[N] = 
      dbgblk(logger, s"filter($node, depth=$depth)") {
        def accumulate(prev:List[N], n:N) = {
          if (!prev.contains(n) && prefix(n)) (prev :+ n) else prev
        }
        new PrefixTraversal[N,List[N]](prefix, visitFunc, accumulate _, Nil, logger).traverseNode((node, depth))
      }
 
    def collect[M<:Node[N]:ClassTag](visitFunc:N => List[N], depth:Int = -1, logger:Option[Logging]=None):List[M] = 
      dbgblk(logger, s"collect($node, depth=$depth)") {
        def prefix(n:N) = n match { case `node` => false; case n:M => true; case _ => false }
        def accumulate(prev:List[M], n:N) = {
          if (!prev.contains(n) && prefix(n)) (prev :+ n.asInstanceOf[M]) else prev
        }
        new PrefixTraversal[N,List[M]](prefix, visitFunc, accumulate _, Nil, logger).traverseNode((node, depth))
      }

    def collectFirst[M<:N:ClassTag](visitFunc:Node[N] => List[N], depth:Int = -1, logger:Option[Logging]=None):M = 
      dbgblk(logger, s"collectFirst($node, depth=$depth)") {
        def prefix(n:N) = n match { case `node` => false; case n:M => true; case _ => false }
        def accumulate(prev:Option[M], n:N) = {
          if (prev.isEmpty && prefix(n)) (Some(n.as[M])) else prev
        }
        new PrefixTraversal[N,Option[M]](prefix, visitFunc, accumulate _, None, logger).traverseNode((node, depth)).get
      }

    def collectUp[M<:N:ClassTag](depth:Int= -1, logger:Option[Logging]=None):List[M] =
      collect[M](visitUp _, depth, logger)

    def collectDown[M<:N:ClassTag:TypeTag](depth:Int= -1, logger:Option[Logging]=None):List[M] = 
      collect[M](visitDown _, depth, logger)

    def collectIn[M<:N:ClassTag](depth:Int= -1, logger:Option[Logging]=None):List[M] = 
      collect[M](visitLocalIn _, depth, logger)

    def collectOut[M<:N:ClassTag](depth:Int= -1, logger:Option[Logging]=None):List[M] = 
      collect[M](visitLocalOut _, depth, logger)

    def collectPeer[M<:N:ClassTag](depth:Int= -1, logger:Option[Logging]=None):List[M] =
      collect[M](visitPeer _, depth, logger)

    def collectChildren[M<:N:ClassTag]:List[M] = node.children.collect { case m:M => m }
    def collectFirstChild[M<:N:ClassTag]:Option[M] = node.children.collectFirst { case m:M => m }

    def accum(prefix:N => Boolean={n:N => false } , visitFunc:N => List[N], depth:Int= -1, logger:Option[Logging]=None):List[N] = 
      dbgblk(logger, s"accum(depth=$depth)"){
        def accumulate(prev:List[N], n:N) = {
          if (!prev.contains(n)) (prev :+ n) else prev
        }
        new PrefixTraversal[N,List[N]](prefix, visitFunc, accumulate _, Nil, logger).traverseNode((node, depth))
      }

    def accumIn(prefix:N => Boolean, depth:Int= -1, logger:Option[Logging]=None):List[N] = 
      accum(prefix, visitLocalIn _, depth, logger)

    def accumTill[M<:N:ClassTag](visitFunc:N => List[N]=visitLocalIn _, depth:Int= -1, logger:Option[Logging]=None):List[N] = {
      def prefix(n:N) = n match { case `node` => false; case n:M => true; case _ => false }
      accum(prefix _, visitFunc, depth, logger)
    }

    def canReach(target:N, visitFunc:N => List[N], depth:Int= -1, logger:Option[Logging]=None):Boolean = 
      dbgblk(logger, s"canReach($target, depth=$depth)"){
        def prefix(n:N) = n == target
        def accumulate(prev:Boolean, n:N) = prefix(n) || prev
        new PrefixTraversal[N,Boolean](prefix, visitFunc, accumulate _, false, logger).traverseNode((node, depth))
      }

    def areLinealInherited(that:N, logger:Option[Logging]=None):Boolean = 
      dbgblk(logger, s"areLinealInherited($node, $that)") {
        node == that || node.ancestors.contains(that) || that.ancestors.contains(node)
      }
  }

  class EdgeCollector[N<:Node[N],A<:Edge[N,A,B],B<:Edge[N,B,A]](edge:Edge[N,A,B]) {
    def collect[M<:N:ClassTag](visitFunc:Node[N] => List[N], depth:Int = -1, logger:Option[Logging]=None):List[M] = 
      dbgblk(logger, s"collect(${edge.src}.${edge}, depth=$depth)") {
        def prefix(n:N) = n match { case n:M => true; case _ => false }
        def accumulate(prev:List[M], n:N) = {
          if (!prev.contains(n) && prefix(n)) (prev :+ n.asInstanceOf[M]) else prev
        }
        val nodes = edge.neighbors.map { n => (n, depth) }.toList
        new PrefixTraversal[N,List[M]](prefix, visitFunc, accumulate _, Nil, logger).traverseNodes(nodes)
      }

    def collectFirst[M<:N:ClassTag](visitFunc:Node[N] => List[N], depth:Int = -1, logger:Option[Logging]=None):M = {
      dbgblk(logger, s"collectFirst(${edge.src}.${edge}, depth=$depth)") {
        def prefix(n:N) = n match { case n:M => true; case _ => false }
        def accumulate(prev:Option[M], n:N) = {
          if (prev.isEmpty && prefix(n)) Some(n.as[M]) else prev
        }
        val nodes = edge.neighbors.map { n => (n, depth) }.toList
        new PrefixTraversal[N,Option[M]](prefix, visitFunc, accumulate _, None, logger).traverseNodes(nodes).get
      }
    }

    def canReach(target:Edge[N,B,A], visitEdges:Node[N] => List[EN[N]], depth:Int= -1, logger:Option[Logging]=None):Boolean = 
      dbgblk(logger, s"canReach($target, depth=$depth)"){
        def prefix(n:N) = visitEdges(n).exists { _.connected.contains(target) }
        def accumulate(prev:Boolean, n:N) = prefix(n) || prev
        def vf(n:N):List[N] = visitEdges(n).flatMap { _.neighbors }.distinct
        val nodes = edge.neighbors.map { n => (n, depth) }.toList
        edge.isConnectedTo(target) || 
        new PrefixTraversal[N,Boolean](prefix, vf _, accumulate _, false, logger).traverseNodes(nodes)
      }

    def accum(prefix:N => Boolean={n:N => false } , visitFunc:N => List[N], depth:Int= -1, logger:Option[Logging]=None):List[N] = 
      dbgblk(logger, s"accum(depth=$depth)"){
        def accumulate(prev:List[N], n:N) = {
          if (!prev.contains(n)) (prev :+ n) else prev
        }
        val nodes = edge.neighbors.map { n => (n, depth) }.toList
        new PrefixTraversal[N,List[N]](prefix, visitFunc, accumulate _, Nil, logger).traverseNodes(nodes)
      }

    def accumTill[M<:N:ClassTag](visitFunc:N => List[N]=visitLocalIn _, depth:Int= -1, logger:Option[Logging]=None):List[N] = {
      def prefix(n:N) = n match { case n:M => true; case _ => false }
      dbgblk(logger, s"accumTill[${classTag[M]}](depth=$depth)"){
        accum(prefix _, visitFunc, depth, logger)
      }
    }
  }

}
