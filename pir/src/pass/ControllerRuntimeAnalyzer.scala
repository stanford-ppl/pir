package pir
package pass

import pir.node._

class ControllerRuntimeAnalyzer(implicit design:PIR) extends PIRPass with ControllerSiblingFirstTraversal with prism.traversal.UnitTraversal {
  import pirmeta._

  override def visitNode(n:N) = dbgblk(s"visitNode($n)") {
    n match {
      case n:ForeverController => 
      case n =>
        getItersOf(n)
        itersOf.info(n).foreach(dbg)
    }
    getCountsOf(n)
    countsOf.info(n).foreach(dbg)
    super.visitNode(n)
  }
}
