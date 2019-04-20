package pir
package pass

import pir.node._
import prism.graph._

class GraphRectification(implicit compiler:PIR) extends PIRTraversal with SiblingFirstTraversal with UnitTraversal {

  override def visitNode(n:N) = {
    n.to[Controller].foreach { n =>
      n.srcCtx.v.foreach { v => n.ctrl.get.srcCtx := v }
      n.sname.v.foreach { v => n.ctrl.get.sname := v }
      n.descendents.foreach { d =>
        val ctrl = n.ctrl.get
        d.ctrl.reset
        d.ctrl := ctrl
        dbg(s"Resetting $d.ctrl = $ctrl")
      }
    }
    n.to[RegAccumOp].foreach { n =>
      n.en(n.getCtrl.ctrler.get.valid)
    }
    n.to[InAccess].foreach { n =>
      if (n.ctrl.get.schedule != "Streaming") {
        n.getCtrl.ctrler.v.foreach { ctrler =>
          n.en(ctrler.valid)
        }
      }
    }
    n.to[Def].foreach { _.getVec }
    n.to[Access].foreach { _.getVec }
    n.to[DRAMAddr].foreach { n =>
      val read = n.collect[MemRead](visitFunc=visitGlobalOut _).head
      n.tp.mirror(read.tp)
      read.mem.T.tp.mirror(read.tp)
    }
    n.to[HostWrite].foreach { n =>
      val mem = n.collect[Memory](visitFunc=visitGlobalOut _).head
      n.tp.mirror(mem.tp)
      n.sname.mirror(mem.sname)
    }
    n.to[InAccess].foreach { n =>
      n.tp.mirror(n.mem.T.tp)
    }
    super.visitNode(n)
  } 

  override def compVec(n:Any):Option[Int] = {
    super.compVec(n).orElse { n.to[PIRNode].flatMap{ _.getCtrl.inferVec } }
  }

}

