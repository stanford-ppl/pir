package prism.traversal

import prism.node._

trait GraphCollector[ND<:Node[ND]] extends GraphUtil { self:ND =>

  class TypeCollector[M<:ND:ClassTag](logger:Option[Logging], vf:ND => List[ND]) extends BFSTraversal with GraphUtil {
    type T = List[M]
    type N = (ND, Int)

    override def isVisited(n:N) = {
      val (node, depth) = n
      visited.contains(node)
    }
    // depth = -1 is infinate depth
    override def visitNode(n:N, prev:T):T = dbgblk(logger, s"collect($n, depth=${n._2})") {
      val (node, depth) = n
      visited += node
      node match {
        case node:M if (depth > 0 || depth < 0) & !prev.contains(node) => prev :+ node 
        case node:M if (depth > 0 || depth < 0) => prev 
        case _ if depth == 0 => prev 
        case _ => super.visitNode(n, prev)
      }
    }
    override def traverse(n:N, zero:T):T = {
      val (node, depth) = n
      super.traverse(n, zero)
    }
    def visitFunc(n:N):List[N] = {
      val (node, depth) = n
      vf(node).map { next => (next, depth-1) }
    }
  }
 
  def collect[M<:ND:ClassTag](visitFunc:ND => List[ND], depth:Int = -1, logger:Option[Logging]=None):List[M] = {
    new TypeCollector[M](logger, visitFunc).traverse((this, depth), Nil)
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

  def accum(visitFunc:ND => List[ND], logger:Option[Logging]=None):List[ND] = {
    val vf = visitFunc
    new BFSTraversal with GraphUtil {
      type N = ND
      type T = List[ND]
      override def visitNode(n:N, prev:T):T = n match {
        case n if prev.contains(n) => prev
        case n => super.visitNode(n, prev:+n)
      }
      override def traverse(n:N, zero:T):T = dbgblk(logger, s"pathCollect($n)") {
        super.traverse(n, zero)
      }
      def visitFunc(n:N):List[ND] = vf(n)
    }.traverse(this, Nil)
  }


  def canReach(target:Node[ND] with ND, visitFunc:ND => List[ND], logger:Option[Logging]=None):Boolean = {
    val vf = visitFunc
    new BFSTraversal with GraphUtil {
      type N = ND
      type T = Boolean
      override def visitNode(n:N, prev:T):T = dbgblk(logger, s"canReach($n, target=$target)") {
        n match {
          case `target` => true
          case n => super.visitNode(n, prev)
        }
      }
      def visitFunc(n:N):List[ND] = vf(n)
    }.traverse(this, false)
  }

  def areLinealInherited(that:Node[ND], logger:Option[Logging]=None):Boolean = {
    dbgblk(logger, s"areLinealInherited($this, $that)") {
      this == that || this.ancestors.contains(that) || that.ancestors.contains(this)
    }
  }

}
