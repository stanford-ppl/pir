package pir
package pass

import pir.node._
import prism.graph._

trait MemoryAnalyzer extends PIRPass with Transformer {

  val tokenBufferDepth = 32 //TODO

  def insertToken(actx:Context, bctx:Context, actrl:ControlTree, bctrl:ControlTree):TokenRead = {
    dbgblk(s"InsertToken(actx=$actx, bctx=$bctx, actrl=$actrl, bctrl=$bctrl)") {
      val (enq, deq) = compEnqDeq(actrl, bctrl, false, actx, bctx)
      val write = within(actx, actrl) {
        TokenWrite().done(enq)
      }
      dbg(s"add $write")
      within(bctx, bctrl) {
        TokenRead().in(write).done(deq)
      }
    }
  }

  def compEnqDeq(a:ControlTree, b:ControlTree, isFIFO:Boolean, actx:Context, bctx:Context):(PIRNode, PIRNode) = 
  dbgblk(s"compEnqDeq($a, $b, isFIFO=$isFIFO, actx=$actx, bctx=$bctx)"){
    if (a == b || isFIFO) {
      (ctrlValid(a, actx), ctrlValid(b, bctx))
    } else if (a.isAncestorOf(b)) {
      val bAncesstors = (b::b.ancestors)
      val idx = bAncesstors.indexOf(a)
      val ctrl = bAncesstors(idx-1).as[ControlTree]
      (ctrlValid(a, actx), ctrlDone(ctrl, bctx))
    } else if (b.isAncestorOf(a)) {
      compEnqDeq(b,a,isFIFO,bctx, actx) 
    } else {
      val lca = leastCommonAncesstor(a,b).get
      val aAncesstors = (a::a.ancestors)
      val bAncesstors = (b::b.ancestors)
      val aidx = aAncesstors.indexOf(lca)
      val bidx = bAncesstors.indexOf(lca)
      val actrl = aAncesstors(aidx-1).as[ControlTree]
      val bctrl = bAncesstors(bidx-1).as[ControlTree]
      (ctrlDone(actrl, actx), ctrlDone(bctrl, bctx))
    }
  }

  def ctrlValid(ctrl:ControlTree, ctx:Context):PIRNode = {
    ctrl.schedule match {
      case "Streaming" =>
        within(ctx, ctrl) { Const(true) }
      case _ =>
        if (!compiler.hasRun[DependencyDuplication]) {
          // Centralized controller
          ctrl.ctrler.get.valid
        } else {
          // Distributed controller
          assertOne(ctx.collectDown[ControllerValid]().filter { _.ctrl.get == ctrl }, 
            s"ctrlValid with ctrl=$ctrl in $ctx")
        }
    }
  }

  def ctrlDone(ctrl:ControlTree, ctx:Context):PIRNode = {
    if (!compiler.hasRun[DependencyDuplication]) {
      // Centralized controller
      ctrl.ctrler.get.done
    } else {
      // Distributed controller
      assertOne(ctx.collectDown[ControllerDone]().filter { _.ctrl.get == ctrl }, 
        s"ctrlDone with ctrl=$ctrl in $ctx")
    }
  }

}
