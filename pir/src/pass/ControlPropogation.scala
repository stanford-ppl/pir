package pir
package pass

import pir.node._
import prism.graph._

class ControlPropogation(implicit compiler:PIR) extends PIRPass {
  override def runPass = {
    pirTop.collectDown[Controller]().foreach { controller =>
      controller.descendents.foreach { d =>
        dbgblk(s"descendents=$d") {
          dbg(s"globalIn = ${visitGlobalIn(d)}")
          var inputs = d.accumTill[Memory](visitGlobalIn _)
          inputs = inputs.filterNot { 
            case mem:Memory => true
            case n => n.ancestors.exists { _.isInstanceOf[Controller] }
          }
          dbg(s"inputs=$inputs")
          (d :: inputs).foreach { n =>
            val node = n.as[PIRNode]
            val ctrl = controller.ctrl.get
            node.ctrl.reset
            node.ctrl := ctrl
            dbg(s"Resetting $node.ctrl = $ctrl")
          }
        }
      }
    }
  }

}

