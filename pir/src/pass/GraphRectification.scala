package pir
package pass

import pir.node._
import prism.graph._

class GraphRectification(implicit compiler:PIR) extends PIRTraversal with SiblingFirstTraversal with UnitTraversal {

  override def visitNode(n:N) = n match {
    case n:Controller =>
      setCtrl(n)
      n.srcCtx.v.foreach { v => n.ctrl.get.srcCtx := v }
      n.descendents.foreach { d =>
        dbgblk(s"descendents=$d") {
          var inputs = d.accumTill[Memory](visitGlobalIn _)
          inputs = inputs.filterNot { 
            case mem:Memory => true
            case n => n.ancestors.exists { _.isInstanceOf[Controller] }
          }
          dbg(s"inputs=$inputs")
          (d :: inputs).foreach { n =>
            val node = n
            val ctrl = n.ctrl.get
            node.ctrl.reset
            node.ctrl := ctrl
            dbg(s"Resetting $node.ctrl = $ctrl")
          }
        }
      }
      super.visitNode(n)
    case n =>
      super.visitNode(n)
  }

  def setCtrl(ctrler:Controller) = {
    val ctrl = ctrler.ctrl.get
    val par = ctrler match {
      case ctrler:LoopController => ctrler.cchain.T.map { _.par }.product
      case ctrler => 1
    }
    ctrl.par := par
    dbg(s"$ctrl.par = $par")
  }

}

