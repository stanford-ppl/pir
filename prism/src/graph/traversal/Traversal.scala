package prism
package graph

import scala.collection.mutable

trait UnitTraversal extends Traversal {
  type T = Unit

  def zero:T = ()

  final override def traverseNode(n:N):T = traverseNode(n, ()) 

  override def visitNode(n:N, prev:T) = visitNode(n)

  def visitNode(n:N):T = super.visitNode(n, zero)
}

trait GraphTraversal extends Traversal {
  type N<:Node[N]
}

trait Traversal extends Logging {
  type N
  type T

  def zero:T

  private val visited = mutable.HashSet[Any]()

  def markVisited(n:Any) = visited += n
  def isVisited(n:Any) = visited.contains(n)

  private var _scope:Option[mutable.HashSet[N]] = None
  def withinScope(n:N) = _scope.fold(true) { _.contains(n) }
  def scope = _scope.get
  def addAndVisitNode(n:N, prev:T) = { 
    _scope.foreach { _ += n }
    markVisitNode(n, prev)
  }

  def resetTraversal = {
    visited.clear
    _scope = None
  }

  def visitFunc(n:N):Stream[N]
  def visitFuncInScope(n:N) = visitFunc(n).filter(withinScope)

  def markVisitNode(n:N, prev:T):T = {
    if (isVisited(n)) return prev
    markVisited(n)
    visitNode(n, prev)
  }

  def visitNode(n:N, prev:T):T = prev

  final def traverseNode(n:N, zero:T):T = traverseNodes(Stream(n), zero)
  def traverseNode(n:N):T = traverseNodes(Stream(n), zero)
  def traverseNodes(ns: => Stream[N], zero:T):T
  def traverseNodes(ns: => Stream[N]):T = traverseNodes(ns, zero)

  final def traverseNodesInScope(scope:Stream[N], ns: => Stream[N], zero:T) = {
    _scope = Some(mutable.HashSet.empty ++ scope)
    val res = traverseNodes(ns, zero)
    _scope = None
    res
  }

}

trait DFSTraversal extends Traversal {

  final def traverseNodes(ns: => Stream[N], zero:T):T = {
    var prev = zero 
    var nexts = ns.filterNot(isVisited)
    while (nexts.nonEmpty) {
      prev = markVisitNode(nexts.head, prev)
      nexts = nexts.filterNot(isVisited)
      if (nexts.isEmpty) // Allow mutable graph
        nexts = ns.filterNot(isVisited)
    }
    prev
  }

  override def visitNode(n:N, prev:T):T = {
    traverseNodes(visitFuncInScope(n), super.visitNode(n, prev))
  }

}

trait BFSTraversal extends Traversal {

  val queue = mutable.Queue[N]()

  override def resetTraversal = {
    super.resetTraversal
    queue.clear
  }

  final def traverseNodes(ns: => Stream[N], zero:T):T = {
    queue ++= ns.filterNot(isScheduled)
    var prev = zero
    while (queue.nonEmpty) {
      val next = queue.dequeue()
      prev = markVisitNode(next, prev)
      if (queue.isEmpty) // Allow mutable graph
        queue ++= ns.filterNot(isScheduled)
    }
    prev
  }

  def isScheduled(n:N) = {
    isVisited(n) || queue.contains(n)
  }

  // no recursive call to traverse(n, zero) to avoid StackOverFlow
  override def visitNode(n:N, prev:T):T = {
    queue ++= visitFuncInScope(n).filterNot(isScheduled)
    prev
  }

}

trait TopologicalTraversal extends GraphTraversal {

  val forward:Boolean
  def visitIn(n:N):Stream[N] = visitGlobalIn(n)
  def visitOut(n:N):Stream[N] = visitGlobalOut(n)
  def depedFunc(n:N):Stream[N] = (if (forward) visitOut(n) else visitIn(n)).filter(withinScope)
  def depFunc(n:N):Stream[N] = (if (forward) visitIn(n) else visitOut(n)).filter(withinScope)
  def isDepFree(n:N) = depFunc(n).forall(isVisited)

  val frontier = mutable.Set[N]()

  override def resetTraversal = {
    super.resetTraversal
    frontier.clear
  }

  def visitFunc(n:N):Stream[N] = visitDepFree(n) 
  //implicit val nct:ClassTag[N]
  //private val cache = Cache((n:N) => visitDepFree(n))
  //def visitFunc(n:N):Stream[N] = cache.memorize(n)
  
  /*
   * Return dependent free nodes by checking whether dependent nodes are free
   * */
  def visitDepFree(n:N):Stream[N] = {
    val unvisited = depedFunc(n).filterNot(isVisited)
    val (depFree, notFree) = unvisited.partition(n => isDepFree(n))
    frontier ++= notFree
    depFree
  }

  def filtering(backup:Stream[N])(filtered: Stream[N] => Stream[N]) = {
    val flist = filtered(backup)
    if (flist.isEmpty) backup else flist
  }

  def selectFrontier(unvisited:Stream[N]) = {
    val filtered = unvisited
      .tryFilter { n => frontier.contains(n) }
    Stream(filtered.minBy { n => depFunc(n).size })
  }

  def scheduleDepFree(nodes:Stream[N]):Stream[N] = {
    val unvisited = nodes.filterNot(isVisited) 
    var depFree = unvisited.filter(isDepFree) 
    if (unvisited.nonEmpty && depFree.isEmpty) {
      var nexts = selectFrontier(unvisited)
      dbg(s"Loop in data flow graph. Breaking loop at $nexts")
      nexts
    } else depFree
  }

  def traverseScope(scope:Stream[N], zero:T) = {
    traverseNodesInScope(scope, scheduleDepFree(scope), zero)
  }

}

trait DFSTopologicalTraversal extends DFSTraversal with TopologicalTraversal
trait BFSTopologicalTraversal extends BFSTraversal with TopologicalTraversal

trait HierarchicalTraversal extends Traversal {
  def top:N
  def traverseScope(n:N, zero:T):T
  def traverseTop = traverseScope(top, zero)
}

trait HierarchicalTopologicalTraversal extends TopologicalTraversal with HierarchicalTraversal

trait TopDownTraversal extends GraphTraversal with HierarchicalTraversal {
  def visitFunc(n:N):Stream[N] = n.children
  def traverseScope(n:N, zero:T):T = traverseNode(n, zero)
}
trait ChildFirstTraversal extends DFSTraversal with TopDownTraversal // Pre-order
trait SiblingFirstTraversal extends BFSTraversal with TopDownTraversal

trait TopDownTopologicalTraversal extends HierarchicalTopologicalTraversal with TopDownTraversal {
  override def visitIn(n:N):Stream[N] = visitLocalIn(n)
  override def visitOut(n:N):Stream[N] = visitLocalOut(n)
  override def visitFunc(n:N):Stream[N] = n match {
    case n if n.children.nonEmpty => scheduleDepFree(n.children)
    case n => visitDepFree(n) // always scheduleDepFree works. But this is faster
  }
}

trait DFSTopDownTopologicalTraversal extends TopDownTopologicalTraversal with ChildFirstTraversal
trait BFSTopDownTopologicalTraversal extends TopDownTopologicalTraversal with SiblingFirstTraversal

trait BottomUpTopologicalTraversal extends HierarchicalTopologicalTraversal {
  override def depedFunc(n:N):Stream[N] = n.parent.toStream ++ super.depedFunc(n)
  override def depFunc(n:N):Stream[N] = n.children ++ super.depFunc(n)
  override def isDepFree(n:N):Boolean = n.children.forall(isVisited) && depFunc(n).forall(isVisited) // performance optimization no need to evaluate depFunc(n) until children are visited

  override def selectFrontier(unvisited:Stream[N]) = {
    val filtered = unvisited
      .tryFilter { n => frontier.contains(n) && n.children.isEmpty }
    Stream(filtered.minBy { n => depFunc(n).size })
  }

  def visitScope(n:N):Stream[N] = n.descendentTree.toStream

  def traverseScope(n:N, zero:T) = {
    val scope = visitScope(n)
    traverseScope(scope, zero)
  }

}

trait DFSBottomUpTopologicalTraversal extends DFSTopologicalTraversal with BottomUpTopologicalTraversal // Post-order
trait BFSBottomUpTopologicalTraversal extends BFSTopologicalTraversal with BottomUpTopologicalTraversal

