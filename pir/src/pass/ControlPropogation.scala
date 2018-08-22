package pir
package pass

import pir.node._

class ControlPropogation(implicit compiler:PIR) extends PIRPass with ControllerSiblingFirstTraversal with prism.traversal.UnitTraversal {
  import pirmeta._

  val forward = false

  def resetController(n:PIRNode, ctrl:Controller):Unit = n match {
    case n:CounterChain =>
      dbg(s"setting ${qtype(n)}.ctrl=$ctrl")
      ctrlOf.removeKey(n)
      ctrlOf(n) = ctrl
      n.counters.foreach(c => resetController(c, ctrl))
    case n:ComputeNode => 
      dbg(s"setting ${qtype(n)}.ctrl=$ctrl")
      ctrlOf.removeKey(n)
      ctrlOf(n) = ctrl
      //n.deps.foreach(d => resetController(d, ctrl))
    case n =>
  }

  override def visitNode(n:N, prev:T):T = {
    n match {
      case n:LoopController => resetController(n.cchain, n)
      case _ =>
    }
    super.visitNode(n, prev)
  }

}

