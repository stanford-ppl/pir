package pir
package pass

import pir.node._

trait ControllerTraversal extends prism.traversal.Traversal with PIRNodeUtil with Logging {
  type N = Controller
}

trait ControllerTopologicalTraversal extends ControllerTraversal with prism.traversal.TopologicalTraversal {
  implicit val designP:PIRDesign
  import designP.pirmeta._

  def visitGlobalIn(n:N):List[N] = {
    inMemsOf(n).flatMap { m => inAccessesOf(m).map { a => ctrlOf(a)} }.toSet.toList
  }

  def visitGlobalOut(n:N):List[N] = {
    outMemsOf(n).flatMap { m => outAccessesOf(m).map { a => ctrlOf(a)} }.toSet.toList
  }

  def visitLocalIn(n:N):List[N] = visitGlobalIn(n).flatMap { c => n.matchLevel(c) }.toSet.toList

  def visitLocalOut(n:N):List[N] = visitGlobalOut(n).flatMap { c => n.matchLevel(c) }.toSet.toList

  override def visitIn(n:N):List[N] = visitGlobalIn(n)
  override def visitOut(n:N):List[N] = visitGlobalOut(n)

  // Breaking loop with program order
  override def selectFrontier(unvisited:List[N]) = {
    dbg(s"unvisited=$unvisited")
    var breakingPoints = unvisited
    breakingPoints = filtering(breakingPoints){ 
      frontier.filter {
        case n if isVisited(n) => false
        case _ => true
      }.toList
    }
    breakingPoints = filtering(breakingPoints){ // using program order if frontier is empty
      if (forward) {
        List(unvisited.minBy { _.id })
      } else {
        List(unvisited.maxBy { _.id })
      }
    }
    breakingPoints
  }

}

trait ControllerHierarchicalTraversal extends ControllerTraversal with prism.traversal.HierarchicalTraversal {
  implicit val designP:PIRDesign
  def top = designP.top.topController
}

trait ControllerSiblingFirstTraversal extends ControllerHierarchicalTraversal with prism.traversal.SiblingFirstTraversal
trait ControllerChildFirstTraversal extends ControllerHierarchicalTraversal with prism.traversal.ChildFirstTraversal
trait ControllerTopDownTopologicalTraversal extends ControllerTopologicalTraversal with ControllerHierarchicalTraversal with prism.traversal.TopDownTopologicalTraversal {
  override def visitIn(n:N):List[N] = visitLocalIn(n)
  override def visitOut(n:N):List[N] = visitLocalOut(n)
}
