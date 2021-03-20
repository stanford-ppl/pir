package pir
package pass

import pir.pass._
import pir.codegen._
import pir.node._
import prism.graph._

class AckControllerRemoval(implicit compiler:PIR) extends PIRTransformer { self =>

  override def runPass = {
    val ctxs = pirTop.collectDown[Context]().filter { 
      _.follow.get
      /* _.collectDown[PIRNode]().exists {
        _.isInstanceOf[AccumAck]
      } */ 
    }
    ctxs.map { ctx =>
      dbg(s"Check $ctx for ack-context controller removal")
      // TODO: traverse down into ControlBlocks
      val accumAck = ctx.collectDown[PIRNode]().filter { node =>
        dbg(s"\t\t$node")
        node.isInstanceOf[AccumAck]
      }.head.asInstanceOf[AccumAck]
      val ctrlers = ctx.collectDown[Controller]().sortBy { _.getCtrl.ancestors.size }.filter {
        !_.isInstanceOf[TopController]
      }
      val ctrlIdx = ctrlers.reverse.zipWithIndex
      dbg(s"\tAccum ack: $accumAck")
      dbg(s"\tAccum port: ${accumAck.ack}")
      dbg(s"\tAccum port.T: ${accumAck.ack.T}")
      // TODO: rewrite this as a recursive function replacing ControlBlocks within the context
      ctrlIdx.map { case(ctrl, idx) =>
        // if (idx == 0) {
          dbg(s"\tCtrl $idx: $ctrl (${ctrl.getCtrl}, ${ctrl.ctx})")
          ctrl.done.connected.map { d =>
            dbg(s"\t\tDone: $d")
          }
          ctrl.childDone.connected.map { d =>
            dbg(s"\t\tchildDone: $d")
          }
          replaceController(ctrl, idx, accumAck.ack.T.asInstanceOf[BufferRead])
        // }
      }
    }
  }

  override def finPass = {
    super.finPass
    if (config.debug) {
      val irprinter = new PIRIRPrinter(runner.logFile.replace(".log",".ir")) {
        override def dirName = config.logDir
        override lazy val logger = self.logger
      }
      irprinter.run
    }
  }

  def replaceController(ctrl:Controller, idx:Int, follow:BufferRead) = {
    // val newCtrl = within(ctrl.getCtrl, ctrl.parent.get) { stage(FollowController().followToken(follow)) }
    val newCtrl = within(ctrl.getCtrl, ctrl.parent.get) { stage(FollowController()) }
    // if (ctrl.par.hasValue) {
      newCtrl.par(ctrl.par.get)
    // }
    // val newCtrl = within(ctrl.ctx.get) { stage(FollowController().followToken(follow)) }
    // assert(ctrl.tileDone.connected.isEmpty)
    // assert(ctrl.subTileDone.connected.isEmpty)
    ctrl.tileDone.disconnect
    ctrl.subTileDone.disconnect
    assert(ctrl.levelsDone.connected.isEmpty)
    assert(ctrl.laneValid.connected.isEmpty)
    // swapOutput(ctrl.done, newCtrl.done)
    // swapOutput(ctrl.childDone, newCtrl.childDone)
    // val cb = ctrl.getCtrl
    dbg(s"\t\t\tctrl.parentEn: ${ctrl.parentEn.connected}")
    newCtrl.parentEn(ctrl.parentEn.connected)
    ctrl.parentEn.disconnect
    ctrl.childDone.connected.map { d => 
      swapConnection(d, ctrl.childDone, newCtrl.childDone)
      // d.disconnect
      // d(newCtrl.childDone)
    }
    ctrl.done.connected.map { d => 
      swapConnection(d, ctrl.done, newCtrl.done)
      // d.disconnect
      // d(newCtrl.done)
    }
    // cb.ctrler(newCtrl)
    //withGC(true) {
      //free(List(ctrl), List(ctrl))
    //}
    removeNodes(List(ctrl))
  }
}
