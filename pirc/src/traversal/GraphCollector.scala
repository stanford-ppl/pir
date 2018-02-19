package prism.traversal

import pirc._
import pirc.util._
import prism.node._

import scala.reflect._
import scala.reflect.runtime.universe._
import scala.collection.mutable

trait GraphCollector extends GraphUtil {

  class TypeCollector[ND<:Node[ND], M<:ND:ClassTag](logger:Option[Logging], vf:ND => List[ND]) extends BFSTraversal with GraphUtil {
    type T = List[M]
    type N = (ND, Int)

    override def isVisited(n:N) = {
      val (node, depth) = n
      visited.contains(node)
    }
    // depth = -1 is infinate depth
    override def visitNode(n:N, prev:T):T = dbgblk(logger, s"typeCollect($n, depth=${n._2})") {
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
 
  def collect[ND<:Node[ND], M<:ND:ClassTag](n:ND, depth:Int, visitFunc:ND => List[ND], logger:Option[Logging]):List[M] = {
    new TypeCollector[ND, M](logger, visitFunc).traverse((n, depth), Nil)
  }

  abstract class PathCollector[ND<:Node[ND]](logger:Option[Logging]) extends BFSTraversal with GraphUtil {
    type N = ND
    type T = List[ND]
    override def visitNode(n:N, prev:T):T = n match {
      case n if prev.contains(n) => prev
      case n => super.visitNode(n, prev:+n)
    }
    override def traverse(n:N, zero:T):T = dbgblk(logger, s"pathCollect($n)") {
      super.traverse(n, zero)
    }
  }

  def accumIn[ND<:Node[ND]](n:Node[ND] with ND, logger:Option[Logging]):List[ND] = {
    new PathCollector[ND](logger) {
      override def visitFunc(n:N) = visitLocalIn(n)
    }.traverse(n, Nil)
  }

  def accumOut[ND<:Node[ND]](n:Node[ND] with ND, logger:Option[Logging]):List[ND] = {
    new PathCollector[ND](logger) {
      override def visitFunc(n:ND) = visitLocalOut(n)
    }.traverse(n, Nil)
  }

  class SearchTraversal[ND<:Node[ND]](target:Node[ND], logger:Option[Logging], vf:ND => List[ND]) extends BFSTraversal with GraphUtil {
    type N = ND
    type T = Boolean
    override def visitNode(n:N, prev:T):T = dbgblk(logger, s"search($n, target=$target)") {
      n match {
        case `target` => true
        case n => super.visitNode(n, prev)
      }
    }
    def visitFunc(n:N):List[ND] = vf(n)
  }

  def canReachIn[ND<:Node[ND]](n:Node[ND] with ND, target:Node[ND] with ND, logger:Option[Logging]=None):Boolean = {
    new SearchTraversal[ND](target, logger, visitLocalIn _).traverse(n, false)
  }

  def canReachOut[ND<:Node[ND]](n:Node[ND] with ND, target:Node[ND] with ND, logger:Option[Logging]=None):Boolean = {
    new SearchTraversal[ND](target, logger, visitLocalOut _).traverse(n, false)
  }

  def areWeaklyConnected[ND<:Node[ND]](n:Node[ND] with ND, target:Node[ND] with ND, logger:Option[Logging]=None):Boolean = {
    dbgblk(logger, s"areWeaklyConnected($n, $target)") {
      canReachIn(n, target, logger) || canReachOut(n, target, logger)
    }
  }

  def areStronglyConnected[ND<:Node[ND]](n:Node[ND] with ND, target:Node[ND] with ND, logger:Option[Logging]=None):Boolean = {
    dbgblk(logger, s"areStronglyConnected($n, $target)") {
      canReachIn(n, target, logger) && canReachOut(n, target, logger)
    }
  }

  def areLinealInherited[ND<:Node[ND]](a:Node[ND], b:Node[ND], logger:Option[Logging]=None):Boolean = {
    dbgblk(logger, s"areLinealInherited($a, $b)") {
      a == b || a.ancestors.contains(b) || b.ancestors.contains(a)
    }
  }

}
