package prism
package traversal

import prism.node._

import scala.collection.mutable

trait Traversal extends Pass with GraphTraversal {
  override def reset = { super.reset; resetTraversal }
  override def initPass = { 
    super.initPass
    resetTraversal
  }
}

trait UnitTraversal extends GraphTraversal {
  type T = Unit

  override def visitNode(n:N, prev:T):T = visitNode(n)
  def visitNode(n:N):T = super.visitNode(n, ())

  def traverseNode(n:N):T = traverseNode(n, ()) 

  def traverse(n:N):T = traverse(n,())

}

trait GraphTraversal {
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

  def traverse(ns: => List[N], zero:T):T = {
    queue ++= ns.filterNot(isScheduled)
    var prev = zero
    while (queue.nonEmpty) {
      prev = traverse(prev)
      queue ++= ns.filterNot(isScheduled)
    }
    prev
  }

  def isScheduled(n:N) = {
    isVisited(n) || queue.contains(n)
  }

  def traverse(zero:T):T = {
    var prev = zero
    while (queue.nonEmpty) {
      val next = queue.dequeue()
      prev = markVisitNode(next, prev)
    }
    return prev
  }

  // no recursive call to traverse(n, zero) to avoid StackOverFlow
  override def visitNode(n:N, prev:T):T = {
    queue ++= visitFunc(n).filterNot(isScheduled)
    prev
  }

  def traverseNode(n:N, prev:T):T = traverse(markVisitNode(n, prev))

}

trait TopologicalTraversal extends GraphTraversal with GraphUtil {
  override type N <:Node[N]
  val forward:Boolean
  def visitIn(n:N):List[N] = visitGlobalIn(n)
  def visitOut(n:N):List[N] = visitGlobalOut(n)
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
    val (depFree, notFree) = unvisited.partition(isDepFree)
    frontier ++= notFree
    depFree
  }

  def filtering(backup:List[N])(filtered: => List[N]) = {
    val flist = filtered
    if (flist.isEmpty) backup else flist
  }

  def selectFrontier(unvisited:List[N]) = {
    var breakingPoints = unvisited
    breakingPoints = filtering(breakingPoints){ 
      frontier.filter {
        case n if isVisited(n) => false
        case _ => true
      }.toList
    }
    breakingPoints = filtering(breakingPoints){ 
      breakingPoints.filter {
        case n if depedFunc(n).filterNot{_.isInstanceOf[SubGraph[_]]}.isEmpty => false
        case _ => true
      }.toList
    }
    breakingPoints = List(breakingPoints.map( n => (depFunc(n).size, n) ).minBy(_._1)._2)
    breakingPoints
  }

  def scheduleDepFree(nodes:List[N]):List[N] = {
    val unvisited = nodes.filterNot(isVisited) 
    var depFree = unvisited.filter(isDepFree) 
    if (unvisited.nonEmpty && depFree.isEmpty) {
      dbgs(s"Loop in Data flow graph.")
      var nexts = selectFrontier(unvisited)
      dbgs(s"Breaking loop at $nexts")
      nexts
    } else depFree
  }

  def visitScope(n:N):List[N] = (n::n.descendents).filter { _.children.isEmpty }

  def traverseScope(n:N, zero:T) = {
    traverse(scheduleDepFree(visitScope(n)), zero)
  }
}

trait DFSTopologicalTraversal extends DFSTraversal with TopologicalTraversal {
  override def traverseNode(n:N, zero:T) = traverseScope(n, zero)
}
trait BFSTopologicalTraversal extends BFSTraversal with TopologicalTraversal {
  override def traverseNode(n:N, zero:T) = traverseScope(n, zero)
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
  override def visitIn(n:N):List[N] = visitLocalIn(n)
  override def visitOut(n:N):List[N] = visitLocalOut(n)
  override def visitFunc(n:N):List[N] = n match {
    case n:SubGraph[N] => scheduleDepFree(n.children)
    case _:Atom[_] => visitDepFree(n)
  }
}

trait DFSTopDownTopologicalTraversal extends TopDownTopologicalTraversal with ChildFirstTraversal
trait BFSTopDownTopDownTopologicalTraversal extends TopDownTopologicalTraversal with SiblingFirstTraversal

trait BottomUpTopologicalTraversal extends TopologicalTraversal with GraphUtil {
  override def depedFunc(n:N):List[N] = n.parent.toList ++ super.depedFunc(n)
  override def depFunc(n:N):List[N] = n.children ++ super.depFunc(n)
  override def isDepFree(n:N):Boolean = n.children.forall(isVisited) && depFunc(n).forall(isVisited) // performance optimization no need to evaluate depFunc(n) until children are visited

  override def selectFrontier(unvisited:List[N]) = {
    var breakingPoints = unvisited
    breakingPoints = filtering(breakingPoints){ 
      frontier.filter {
        case n if isVisited(n) => false
        case n:SubGraph[_] => false
        case _ => true
      }.toList
    }
    breakingPoints = filtering(breakingPoints){ 
      breakingPoints.filter {
        case n:SubGraph[_] => false
        case n if depedFunc(n).filterNot{_.isInstanceOf[SubGraph[_]]}.isEmpty => false
        case _ => true
      }.toList
    }
    breakingPoints = List(breakingPoints.map( n => (depFunc(n).size, n) ).minBy(_._1)._2)
    breakingPoints
  }
}

trait DFSBottomUpTopologicalTraversal extends DFSTopologicalTraversal with BottomUpTopologicalTraversal {
  override def traverseNode(n:N, zero:T) = traverseScope(n, zero)
}

trait BFSBottomUpTopologicalTraversal extends BFSTopologicalTraversal with BottomUpTopologicalTraversal {
  override def traverseNode(n:N, zero:T) = traverseScope(n, zero)
}

