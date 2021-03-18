package pir
package pass

import pir.node._
import prism.graph._

class AckControllerRemoval(implicit compiler:PIR) extends PIRPass {

  override def runPass = {
    val ctxs = pirTop.collectDown[Context]().filter { 
      _.follow.get
      /* _.collectDown[PIRNode]().exists {
        _.isInstanceOf[AccumAck]
      } */ 
    }
    ctxs.map { ctx =>
      dbg(s"Check $ctx for ack-context controller removal")
      val accumAck = ctx.collectDown[PIRNode]().filter { node =>
        dbg(s"\t\t$node")
        node.isInstanceOf[AccumAck]
      }.head.asInstanceOf[AccumAck]
      val ctrlers = ctx.collectDown[Controller]().sortBy { _.getCtrl.ancestors.size }.filter {
        !_.isInstanceOf[TopController]
      }
      val ctrlIdx = ctrlers.reverse.zipWithIndex.reverse
      dbg(s"\tAccum ack: $accumAck")
      dbg(s"\tAccum port: ${accumAck.ack}")
      dbg(s"\tAccum port.T: ${accumAck.ack.T}")
      ctrlIdx.map { case(ctrl, idx) =>
        dbg(s"\tCtrl $idx: $ctrl")
      }
    }
  }

  def replaceController(ctrl:Controller, idx:Int) = {
  }
}
