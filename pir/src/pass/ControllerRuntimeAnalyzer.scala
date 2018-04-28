package pir.pass
import pir.node._

class ControllerRuntimeAnalyzer(implicit design:PIR) extends PIRPass with ControllerTraversal with prism.traversal.UnitTraversal {
  import pirmeta._

  override def runPass = {
    traverseNode(design.design.top.topController)
  }

  override def visitNode(n:N) = dbgblk(s"visitNode($n)") {
    val iters = getItersOf(n)
    val counts = getCountsOf(n)
    itersOf.info(n).foreach(dbg)
    countsOf.info(n).foreach(dbg)
    super.visitNode(n)
  }
}
