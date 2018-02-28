package prism.traversal

import prism._
import prism.util._
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

  def accum[ND<:Node[ND]](n:Node[ND] with ND, visitFunc:ND => List[ND], logger:Option[Logging]=None):List[ND] = {
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
    }.traverse(n, Nil)
  }


  def canReach[ND<:Node[ND]](n:Node[ND] with ND, target:Node[ND] with ND, visitFunc:ND => List[ND], logger:Option[Logging]=None):Boolean = {
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
    }.traverse(n, false)
  }

  def areLinealInherited[ND<:Node[ND]](a:Node[ND], b:Node[ND], logger:Option[Logging]=None):Boolean = {
    dbgblk(logger, s"areLinealInherited($a, $b)") {
      a == b || a.ancestors.contains(b) || b.ancestors.contains(a)
    }
  }

}
