package prism.traversal

import pirc._
import pirc.util._
import prism.node._
import prism.codegen.Logging

import scala.reflect._
import scala.reflect.runtime.universe._
import scala.collection.mutable

trait Traversal extends GraphTraversal with Pass {
  override def reset = { super.reset; resetTraversal }
  override def initPass = { super.initPass; resetTraversal }
}

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

}

trait UnitTraversal extends GraphTraversal {
  type T = Unit

  override def visitNode(n:N, prev:T):T = visitNode(n)
  def visitNode(n:N):T = super.visitNode(n, ())

  def traverseNode(n:N):T = traverseNode(n, ()) 

  def traverse(n:N):T = traverse(n,())

}

trait GraphTraversal extends Memorization {
  type N
  type T

  val visited = mutable.ListBuffer[Any]()

  def isVisited(n:N) = visited.contains(n)

  def resetTraversal = {
    visited.clear
  }

  def visitFunc(n:N):List[N]

  def markVisitNode(n:N, prev:T):T = {
    if (isVisited(n)) return prev
    visited += n
    visitNode(n, prev)
  }

  def visitNode(n:N, prev:T):T = prev

  def traverseNode(n:N, prev:T):T

  def traverse(n:N, zero:T):T
  def traverse(ns: => List[N], zero:T):T

  def dbgs(s:String) = {
    this match {
      case self:Logging => self.dbg(s)
      case _ =>
    }
  }
}


trait DFSTraversal extends GraphTraversal {
  override def traverse(n:N, zero:T):T = {
    traverse(visitFunc(n), zero)
  }

  override def traverse(ns: => List[N], zero:T):T = {
    var prev = zero 
    var nexts = ns.filterNot(isVisited)
    // Cannot use fold left because graph might be changing while traversing
    while (nexts.nonEmpty) {
      prev = markVisitNode(nexts.head, prev)
      nexts = nexts.filterNot(isVisited)
      if (nexts.isEmpty) nexts = ns.filterNot(isVisited)
    }
    prev
  }

  def traverseNode(n:N, prev:T):T = markVisitNode(n, prev)

  override def visitNode(n:N, prev:T):T = {
    traverse(n, super.visitNode(n, prev))
  }

}

trait BFSTraversal extends GraphTraversal {

  val queue = mutable.Queue[N]()

  override def resetTraversal = {
    super.resetTraversal
    queue.clear
  }

  def traverse(n:N, zero:T):T = {
    traverse(visitFunc(n), zero)
  }

  def isScheduled(n:N) = {
    isVisited(n) || queue.contains(n)
  }

  def traverse(ns: => List[N], zero:T):T = {
    queue ++= ns.filterNot(isScheduled)
    var prev = zero
    while (queue.nonEmpty) {
      val next = queue.dequeue()
      prev = markVisitNode(next, prev)
      queue ++= visitFunc(next).filterNot(isScheduled)
      if (queue.isEmpty) queue ++= ns.filterNot(isScheduled)
    }
    return prev
  }

  def traverseNode(n:N, prev:T):T = traverse(n, markVisitNode(n, prev))

}

trait TopologicalTraversal extends GraphTraversal {
  val forward:Boolean
  def visitIn(n:N):List[N]
  def visitOut(n:N):List[N]
  def depedFunc(n:N):List[N] = if (forward) visitOut(n) else visitIn(n)
  def depFunc(n:N):List[N] = if (forward) visitIn(n) else visitOut(n)
  def isDepFree(n:N) = depFunc(n).forall(isVisited)

  val frontier = mutable.Set[N]()

  override def resetTraversal = {
    super.resetTraversal
    frontier.clear
  }

  def visitFunc(n:N):List[N] = visitDepFree(n) 
  //implicit val nct:ClassTag[N]
  //private val cache = Cache((n:N) => visitDepFree(n))
  //def visitFunc(n:N):List[N] = cache.memorize(n)
  
  /*
   * Return dependent free nodes by checking whether dependent nodes are free
   * */
  def visitDepFree(n:N):List[N] = {
    val unvisited = depedFunc(n).filterNot(isVisited)
    frontier ++= unvisited
    unvisited.filter(isDepFree) 
  }

  def selectFrontier = {
    var breakingPoints = frontier.filter {
      case n if isVisited(n) => false
      case n:SubGraph[_] => false
      case n if depFunc(n).exists(isVisited) => true
      case _ => false
    }.toList
    breakingPoints = breakingPoints.sortBy { n => depFunc(n).filter(isVisited).size }
    breakingPoints
  }

  def scheduleDepFree(nodes:List[N]):List[N] = {
    val unvisited = nodes.filterNot(isVisited) 
    var depFree = unvisited.filter(isDepFree) 
    if (unvisited.nonEmpty && depFree.isEmpty) {
      dbgs(s"Loop in Data flow graph.")
      var nexts = selectFrontier
      dbgs(s"frontier = $nexts")
      if (nexts.isEmpty) nexts = List(unvisited.map( n => (depFunc(n).size, n) ).minBy(_._1)._2)
      dbgs(s"Breaking loop at $nexts")
      nexts
    } else depFree
  }

}

trait TopDownTraversal extends GraphTraversal {
  override type N <:Node[N]
  def visitFunc(n:N):List[N] = n match {
    case n:SubGraph[_] => n.children.asInstanceOf[List[N]]
    case n:Atom[_] => Nil
  }
}
trait ChildFirstTraversal extends DFSTraversal with TopDownTraversal {
  override def visitNode(n:N, zero:T):T = {
    assert(!n.children.exists(isVisited), s"children of $n is visited before visit the parent in ChildFirstTraversal. children visited=${n.children.filter(isVisited)}")
    val res = super.visitNode(n, zero)
    assert(!n.children.exists(c => !isVisited(c)), s"Not all children of $n is visited after ChildFirstTraversal ${n.children.filterNot(isVisited)}")
    res
  }
}
trait SiblingFirstTraversal extends BFSTraversal with TopDownTraversal

trait TopDownTopologicalTraversal extends TopologicalTraversal with TopDownTraversal with GraphUtil {
  def visitIn(n:N):List[N] = visitLocalIn(n)
  def visitOut(n:N):List[N] = visitLocalOut(n)
  override def visitFunc(n:N):List[N] = n match {
    case n:SubGraph[N] => scheduleDepFree(n.children)
    case _:Atom[_] => visitDepFree(n)
  }
}

trait DFSTopDownTopologicalTraversal extends TopDownTopologicalTraversal with ChildFirstTraversal
trait BFSTopDownTopDownTopologicalTraversal extends TopDownTopologicalTraversal with SiblingFirstTraversal

trait BottomUpTopologicalTraversal extends TopologicalTraversal with GraphUtil {
  override type N <:Node[N]
  def visitIn(n:N):List[N] = visitGlobalIn(n)
  def visitOut(n:N):List[N] = visitGlobalOut(n)
  override def depedFunc(n:N):List[N] = n.parent.toList ++ super.depedFunc(n)
  override def depFunc(n:N):List[N] = n.children ++ super.depFunc(n)
  override def isDepFree(n:N):Boolean = n.children.forall(isVisited) && super.depFunc(n).forall(isVisited)

  def traverseScope(n:N, zero:T) = {
    val allNodes = n::n.descendents
    traverse(scheduleDepFree(allNodes), zero)
  }
}

trait DFSBottomUpTopologicalTraversal extends BottomUpTopologicalTraversal with DFSTraversal {
  override def traverseNode(n:N, zero:T) = traverseScope(n, zero)
}

trait BFSBottomUpTopologicalTraversal extends BottomUpTopologicalTraversal with BFSTraversal {
  override def traverseNode(n:N, zero:T) = traverseScope(n, zero)
}

