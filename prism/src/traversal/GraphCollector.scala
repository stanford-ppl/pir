package prism
package traversal

import prism.node._

trait GraphCollector[ND<:Node[ND]] extends GraphUtil { self:ND =>

  /*
   * Traverse the graph until hit node satisfying prefix or depth = 0
   * Accumulate the result based on accumulate function
   * */
  class PrefixTraversal[NT](
    prefix:ND => Boolean, 
    vf:ND => List[ND], 
    accumulate:(NT, ND) => NT, 
    val zero:NT,
    logger:Option[Logging]
  ) extends DFSTraversal with GraphUtil {
    type T = NT
    type N = (ND, Int)

    override def isVisited(n:N) = {
      val (node, depth) = n
      visited.contains(node)
    }
    // depth = -1 is infinate depth
    def withinDepth(depth:Int) = (depth > 0 || depth < 0)

    override def visitNode(n:N, prev:T):T = dbgblk(logger, s"visitNode($n, depth=${n._2})") {
      val (node, depth) = n
      visited += node
      node match {
        case _ if withinDepth(depth) & prefix(node) => accumulate(prev, node)
        case _ if withinDepth(depth) => accumulate(prev, node); super.visitNode(n, prev)
        case _ => prev 
      }
    }

    def visitFunc(n:N):List[N] = {
      val (node, depth) = n
      vf(node).map { next => (next, depth-1) }
    }
  }

  def filter(prefix:ND => Boolean, visitFunc:ND => List[ND], depth:Int = -1, logger:Option[Logging]=None):List[ND] = 
    dbgblk(logger, s"filter($this, depth=$depth)") {
      def accumulate(prev:List[ND], n:ND) = {
        if (!prev.contains(n) && prefix(n)) (prev :+ n) else prev
      }
      new PrefixTraversal[List[ND]](prefix, visitFunc, accumulate _, Nil, logger).traverseNode((this, depth), Nil)
    }
 
  def collect[M<:ND:ClassTag](visitFunc:ND => List[ND], depth:Int = -1, logger:Option[Logging]=None):List[M] = 
    dbgblk(logger, s"collect($this, depth=$depth)") {
      def prefix(n:ND) = n match { case `self` => false; case n:M => true; case _ => false }
      def accumulate(prev:List[M], n:ND) = {
        if (!prev.contains(n) && prefix(n)) (prev :+ n.asInstanceOf[M]) else prev
      }
      new PrefixTraversal[List[M]](prefix, visitFunc, accumulate _, Nil, logger).traverseNode((this, depth), Nil)
    }

  def collectUp[M<:ND:ClassTag](depth:Int= -1, logger:Option[Logging]=None):List[M] =
    collect[M](visitUp _, depth, logger)

  def collectDown[M<:ND:ClassTag:TypeTag](depth:Int= -1, logger:Option[Logging]=None):List[M] = 
    collect[M](visitDown _, depth, logger)

  def collectIn[M<:ND:ClassTag](depth:Int= -1, logger:Option[Logging]=None):List[M] = 
    collect[M](visitLocalIn _, depth, logger)

  def collectOut[M<:ND:ClassTag](depth:Int= -1, logger:Option[Logging]=None):List[M] = 
    collect[M](visitLocalOut _, depth, logger)

  def collectPeer[M<:ND:ClassTag](depth:Int= -1, logger:Option[Logging]=None):List[M] =  {
    collect[M](visitPeer _, depth, logger)
  }

  def accum(prefix:ND => Boolean, visitFunc:ND => List[ND], depth:Int= -1, logger:Option[Logging]=None):List[ND] = 
    dbgblk(logger, s"accum(depth=$depth)"){
      def accumulate(prev:List[ND], n:ND) = {
        if (!prev.contains(n)) (prev :+ n) else prev
      }
      new PrefixTraversal[List[ND]](prefix, visitFunc, accumulate _, Nil, logger).traverseNode((this, depth), Nil)
    }

  def canReach(target:Node[ND], visitFunc:ND => List[ND], depth:Int= -1, logger:Option[Logging]=None):Boolean = 
    dbgblk(logger, s"canReach($target, depth=$depth)"){
      def prefix(n:ND) = n == target
      def accumulate(prev:Boolean, n:ND) = prefix(n) || prev
      new PrefixTraversal[Boolean](prefix, visitFunc, accumulate _, false, logger).traverseNode((this, depth), false)
    }

  def areLinealInherited(that:Node[ND], logger:Option[Logging]=None):Boolean = 
    dbgblk(logger, s"areLinealInherited($this, $that)") {
      this == that || this.ancestors.contains(that) || that.ancestors.contains(this)
    }

}
