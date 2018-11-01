package pir
package pass

import pir.node._
import prism.graph._

trait MemoryAnalyzer extends PIRPass with Transformer {

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
    // Centralized controller
    ctrl.ctrler.get.valid
    // Distributed controller
    //ctx.collectDown[Controller]().filter { _.ctrl.get == ctrl }.headOption.getOrElse {
    //}.valid
  }

  def ctrlDone(ctrl:ControlTree, ctx:Context):PIRNode = {
      // Centralized controller
      ctrl.ctrler.get.done
      // Distributed controller
    //ctx.collectDown[Controller]().filter { _.ctrl.get == ctrl }.headOption.getOrElse {
    //}.done
  }

}
