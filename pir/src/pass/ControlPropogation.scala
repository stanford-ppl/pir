package pir
package pass

import pir.node._
import prism.graph._

class ControlPropogation(implicit compiler:PIR) extends PIRPass {
  override def runPass = {
    pirTop.collectDown[Controller]().foreach { ctrler =>
      setCtrl(ctrler)
      ctrler.srcCtx.v.foreach { v => ctrler.ctrl.get.srcCtx := v }
      ctrler.descendents.foreach { d =>
        dbgblk(s"descendents=$d") {
          var inputs = d.accumTill[Memory](visitGlobalIn _)
          inputs = inputs.filterNot { 
            case mem:Memory => true
            case n => n.ancestors.exists { _.isInstanceOf[Controller] }
          }
          dbg(s"inputs=$inputs")
          (d :: inputs).foreach { n =>
            val node = n.as[PIRNode]
            val ctrl = ctrler.ctrl.get
            node.ctrl.reset
            node.ctrl := ctrl
            dbg(s"Resetting $node.ctrl = $ctrl")
          }
        }
      }
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

}

