package pir
package pass

import pir.node._
import prism.graph._

class GraphInitialization(implicit compiler:PIR) extends PIRTraversal with SiblingFirstTraversal with UnitTraversal {

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
    n.to[Def].foreach { _.getVec }
    n.to[Access].foreach { _.getVec }
    n.to[DRAMAddr].foreach { n =>
      val read = n.collect[MemRead](visitFunc=visitGlobalOut _).head
      n.tp.mirror(read.tp)
      read.mem.T.tp.mirror(read.tp)
    }
    n.to[HostWrite].foreach { n =>
      val mem = n.collectFirst[Memory](visitFunc=visitGlobalOut _)
      n.tp.mirror(mem.tp)
      n.sname.mirror(mem.sname)
    }
    n.to[InAccess].foreach { n =>
      n.tp.mirror(n.mem.T.tp)
    }
    n.to[DRAMLoadCommand].foreach { n =>
      val data = n.data.collectFirst[FIFO](visitGlobalOut _)
      n.data.setVec(data.banks.get.head)
      n.data.setTp(data.tp.v)
    }
    n.to[HostRead].foreach { n =>
      n.sname.mirror(n.collectFirst[Memory](visitGlobalIn _).sname)
    }
    if (config.enableSimDebug) {
      n.to[PrintIf].foreach { n =>
        n.tp.reset
        n.tp := Bool
        val reg = within(pirTop.argFringe) { stage(Reg().depth(1).tp(Bool)) }
        within(n.parent.get, n.getCtrl) { stage(MemWrite().data(n.out).mem(reg).order(0)) }
        within(pirTop, pirTop.hostOutCtrl) {
          val read = stage(MemRead().mem(reg).order(1))
          stage(HostRead().input(read))
        }
      }
    }
    super.visitNode(n)
  } 

  override def compVec(n:IR):Option[Int] = dbgblk(s"compVec($n)") {
    n match {
      case _:Def | _:Access =>
        super.compVec(n).orElse { n.to[PIRNode].flatMap{ _.getCtrl.inferVec } }
      case _ => super.compVec(n)
    }
  }

}

