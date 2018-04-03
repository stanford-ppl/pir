package pir.pass
import prism.traversal._

class TestTraversal(implicit compiler:PIR) extends PIRTraversal with BFSBottomUpTopologicalTraversal with UnitTraversal {
//class TestTraversal(implicit compiler:PIR) extends PIRTraversal with DFSTopDownTopologicalTraversal with UnitTraversal {
  import pirmeta._

  override def shouldRun = true

  val forward = false

  override def runPass =  {
    tic
    traverseNode(compiler.top)
    toc("TestPass", "ms")

    val top = compiler.top
    val allNodes = top::top.descendents
    val unvisited = allNodes.filterNot(isVisited)
    assert(unvisited.isEmpty, s"not all nodes are visited! unvisited=${unvisited}")
  }

  override def visitNode(n:N) = {
    dbg(s"Visiting ${qdef(n)}")
    super.visitNode(n)
  }

}

