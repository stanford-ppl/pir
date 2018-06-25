package pir
package pass

class TestTraversal(implicit compiler:PIR) extends PIRTraversal with BFSBottomUpTopologicalTraversal with UnitTraversal {
//class TestTraversal(implicit compiler:PIR) extends PIRTraversal with DFSTopDownTopologicalTraversal with UnitTraversal {
  import pirmeta._

  val forward = false

  override def runPass =  {
    val scope = top :: top.descendents
    traverseTop
    val unvisited = scope.filterNot(isVisited)

    dbg(s" ============ unvisited ============== ")
    unvisited.foreach { n =>
      dbg(qdef(n))
    }
    assert(unvisited.isEmpty, s"not all nodes are visited! ")
  }

  override def visitNode(n:N) = {
    dbg(s"Visiting ${qdef(n)}")
    super.visitNode(n)
  }

}

