package prism
package graph

import scala.collection.mutable

trait UnitTraversal extends Traversal {
  type T = Unit

  def zero:T = ()

  final def traverseNode(n:N):T = traverseNode(n, ()) 

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

  var _scope:Option[List[N]] = None
  def withinScope(n:N) = _scope.map { _.contains(n) }.getOrElse(true)
  def scope = _scope.get
  def addAndVisitNode(n:N, prev:T) = { 
    _scope = Some(n::_scope.get)
    markVisitNode(n, prev)
  }

  def resetTraversal = {
    visited.clear
    _scope = None
  }

  def visitFunc(n:N):List[N]
  def visitFuncInScope(n:N) = visitFunc(n).filter(withinScope)

  def markVisitNode(n:N, prev:T):T = {
    if (isVisited(n)) return prev
    markVisited(n)
    visitNode(n, prev)
  }

  def visitNode(n:N, prev:T):T = prev

  final def traverseNode(n:N, zero:T):T = traverseNodes(List(n), zero)
  def traverseNodes(ns: => List[N], zero:T):T

  final def traverseNodesInScope(scope:List[N], ns: => List[N], zero:T) = {
    _scope = Some(scope)
    val res = traverseNodes(ns, zero)
    _scope = None
    res
  }

}

trait DFSTraversal extends Traversal {

  final def traverseNodes(ns: => List[N], zero:T):T = {
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

  final def traverseNodes(ns: => List[N], zero:T):T = {
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
  def visitIn(n:N):List[N] = visitGlobalIn(n)
  def visitOut(n:N):List[N] = visitGlobalOut(n)
  def depedFunc(n:N):List[N] = (if (forward) visitOut(n) else visitIn(n)).filter(withinScope)
  def depFunc(n:N):List[N] = (if (forward) visitIn(n) else visitOut(n)).filter(withinScope)
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
    val (depFree, notFree) = unvisited.partition(n => isDepFree(n))
    frontier ++= notFree
    depFree
  }

  def filtering(backup:List[N])(filtered: List[N] => List[N]) = {
    val flist = filtered(backup)
    if (flist.isEmpty) backup else flist
  }

  def selectFrontier(unvisited:List[N]) = {
    val filtered = unvisited
      .tryFilter { n => frontier.contains(n) }
    List(filtered.minBy { n => depFunc(n).size })
  }

  def scheduleDepFree(nodes:List[N]):List[N] = {
    val unvisited = nodes.filterNot(isVisited) 
    var depFree = unvisited.filter(isDepFree) 
    if (unvisited.nonEmpty && depFree.isEmpty) {
      var nexts = selectFrontier(unvisited)
      dbg(s"Loop in data flow graph. Breaking loop at $nexts")
      nexts
    } else depFree
  }

  def traverseScope(scope:List[N], zero:T) = {
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
  def visitFunc(n:N):List[N] = n.children
  def traverseScope(n:N, zero:T):T = traverseNode(n, zero)
}
trait ChildFirstTraversal extends DFSTraversal with TopDownTraversal
trait SiblingFirstTraversal extends BFSTraversal with TopDownTraversal

trait TopDownTopologicalTraversal extends HierarchicalTopologicalTraversal with TopDownTraversal {
  override def visitIn(n:N):List[N] = visitLocalIn(n)
  override def visitOut(n:N):List[N] = visitLocalOut(n)
  override def visitFunc(n:N):List[N] = n match {
    case n if n.children.nonEmpty => scheduleDepFree(n.children)
    case n => visitDepFree(n) // always scheduleDepFree works. But this is faster
  }
}

trait DFSTopDownTopologicalTraversal extends TopDownTopologicalTraversal with ChildFirstTraversal
trait BFSTopDownTopologicalTraversal extends TopDownTopologicalTraversal with SiblingFirstTraversal

trait BottomUpTopologicalTraversal extends HierarchicalTopologicalTraversal {
  override def depedFunc(n:N):List[N] = n.parent.toList ++ super.depedFunc(n)
  override def depFunc(n:N):List[N] = n.children ++ super.depFunc(n)
  override def isDepFree(n:N):Boolean = n.children.forall(isVisited) && depFunc(n).forall(isVisited) // performance optimization no need to evaluate depFunc(n) until children are visited

  override def selectFrontier(unvisited:List[N]) = {
    val filtered = unvisited
      .tryFilter { n => frontier.contains(n) && n.children.isEmpty }
    List(filtered.minBy { n => depFunc(n).size })
  }

  def visitScope(n:N):List[N] = n::n.descendents

  def traverseScope(n:N, zero:T) = {
    val scope = visitScope(n)
    traverseScope(scope, zero)
  }

}

trait DFSBottomUpTopologicalTraversal extends DFSTopologicalTraversal with BottomUpTopologicalTraversal
trait BFSBottomUpTopologicalTraversal extends BFSTopologicalTraversal with BottomUpTopologicalTraversal

