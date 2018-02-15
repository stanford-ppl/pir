package prism.traversal

import pirc._
import pirc.util._
import prism.node._

import scala.reflect._
import scala.reflect.runtime.universe._
import scala.collection.mutable

trait GraphCollector {

  abstract class TypeCollector[ND<:Node[ND], M<:ND:ClassTag] extends BFSTraversal with GraphUtil {
    type T = List[M]
    type N = (ND, Int)
    val logger:Option[Logging]

    override def isVisited(n:N) = {
      val (node, depth) = n
      visited.contains(node)
    }
    override def visitNode(n:N, prev:T):T = {
      val (node, depth) = n
      visited += node
      node match {
        case node:M if depth > 0 & !prev.contains(node) => prev :+ node 
        case node:M if depth > 0 & prev.contains(node) => prev 
        case _ if depth == 0 => prev 
        case _ => super.visitNode(n, prev)
      }
    }
    override def traverse(n:N, zero:T):T = dbgblk(logger, s"typeCollect($n, depth=${n._2})") {
      val (node, depth) = n
      super.traverse(n, zero)
    }
    def visitFunc(n:N):List[N] = {
      val (node, depth) = n
      vf(node).map { next => (next, depth-1) }
    }
    def vf(n:ND):List[ND]
  }
 
  def collectUp[ND<:Node[ND], M<:ND:ClassTag](n:ND, depth:Int, log:Option[Logging]):List[M] = {
    new TypeCollector[ND, M] {
      override val logger = log
      override def vf(n:ND) = visitUp(n)
    }.traverse((n, depth), Nil)
  }

  def collectDown[ND<:Node[ND], M<:ND:ClassTag](n:ND, depth:Int, log:Option[Logging]):List[M] = {
    new TypeCollector[ND, M] {
      override val logger = log
      override def vf(n:ND) = visitDown(n)
    }.traverse((n, depth), Nil)
  }

  def collectIn[ND<:Node[ND], M<:ND:ClassTag](n:ND, depth:Int, log:Option[Logging]):List[M] = {
    new TypeCollector[ND, M] {
      override val logger = log
      override def vf(n:ND) = visitLocalIn(n)
    }.traverse((n, depth), Nil)
  }

  def collectOut[ND<:Node[ND], M<:ND:ClassTag](n:ND, depth:Int, log:Option[Logging]):List[M] = {
    new TypeCollector[ND, M] {
      override val logger = log
      override def vf(n:ND) = visitLocalOut(n)
    }.traverse((n, depth), Nil)
  }

  abstract class PathCollector[ND<:Node[ND]] extends BFSTraversal with GraphUtil {
    type N = ND
    type T = List[ND]
    val logger:Option[Logging]
    override def visitNode(n:N, prev:T):T = n match {
      case n if prev.contains(n) => prev
      case n => super.visitNode(n, prev:+n)
    }
    override def traverse(n:N, zero:T):T = dbgblk(logger, s"pathCollect($n)") {
      super.traverse(n, zero)
    }
  }

  def accumIn[ND<:Node[ND]](n:ND, log:Option[Logging]):List[ND] = {
    new PathCollector[ND] {
      override val logger = log
      override def visitFunc(n:N) = visitLocalIn(n)
    }.traverse(n, Nil)
  }

  def accumOut[ND<:Node[ND]](n:ND, log:Option[Logging]):List[ND] = {
    new PathCollector[ND] {
      override val logger = log
      override def visitFunc(n:ND) = visitLocalOut(n)
    }.traverse(n, Nil)
  }

  abstract class SearchTraversal[ND<:Node[ND]](target:ND) extends BFSTraversal with GraphUtil {
    type N = ND
    type T = Boolean
    val logger:Option[Logging]
    override def visitNode(n:N, prev:T):T = n match {
      case `target` => true
      case n => super.visitNode(n, prev)
    }
    override def traverse(n:N, zero:T):T = dbgblk(logger, s"search($n)") {
      super.traverse(n, zero)
    }
  }

  def canReachIn[ND<:Node[ND]](n:ND, target:ND, log:Option[Logging]):Boolean = {
    new SearchTraversal[ND](target) {
      override val logger = log
      override def visitFunc(n:N) = visitLocalIn(n)
    }.traverse(n, false)
  }

  def canReachOut[ND<:Node[ND]](n:ND, target:ND, log:Option[Logging]):Boolean = {
    new SearchTraversal[ND](target) {
      override val logger = log
      override def visitFunc(n:N) = visitLocalOut(n)
    }.traverse(n, false)
  }

  def areWeaklyConnected[ND<:Node[ND]](n:ND, target:ND, log:Option[Logging]):Boolean = {
    canReachIn(n, target, log) || canReachOut(n, target, log)
  }

  def areStronglyConnected[ND<:Node[ND]](n:ND, target:ND, log:Option[Logging]):Boolean = {
    canReachIn(n, target, log) && canReachOut(n, target, log)
  }

}
