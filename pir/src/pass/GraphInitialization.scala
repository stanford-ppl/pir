package pir
package pass

import pir.node._
import prism.graph._
import spade.param._

class GraphInitialization(implicit compiler:PIR) extends PIRTraversal with SiblingFirstTraversal with UnitTraversal with PIRTransformer {

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
    n.to[DRAMStoreCommand].foreach { n =>
      val data = n.data.collectFirst[FIFO](visitGlobalIn _)
      n.data.setVec(data.banks.get.head)
      n.data.setTp(data.tp.v)
      val ack = n.ack.collectFirst[FIFO](visitGlobalIn _)
      n.ack.setVec(1)
      n.ack.setTp(Bool)
    }
    n.to[DRAMSparseCommand].foreach { n =>
      n.addr.setVec(n.data.as[IR].getVec)
    }
    n.to[HostRead].foreach { n =>
      n.sname.mirror(n.collectFirst[Memory](visitGlobalIn _).sname)
    }
    if (config.enableSimDebug) {
      n.to[PrintIf].foreach { n =>
        n.tp.reset
        n.tp := Bool
        val reg = within(pirTop.argFringe, pirTop.topCtrl) { stage(Reg().depth(1).tp(Bool)) }
        within(n.parent.get, n.getCtrl) { stage(MemWrite().data(n.out).setMem(reg)) }
        within(pirTop.argFringe, pirTop.hostOutCtrl) {
          val read = stage(MemRead().setMem(reg))
          stage(HostRead().input(read))
        }
      }
    }
    // Convert reduction not expressed as RegAccumOp to RegAccumOp
    // val read = accum.read
    // val reduceOp = func(read, input)
    n.to[Memory].foreach { n =>
      if (n.isInnerAccum.get && n.inAccesses.nonEmpty) {
        val writer = assertOne(n.inAccesses, s"writer of inner accum $n").as[MemWrite]
        val reader = assertOne(n.outAccesses, s"reader of inner accum $n")
        val mux = writer.data.T match {
          case m@OpDef(Mux) => Some(m)
          case m:RegAccumOp => None
          case mux => throw PIRException(s"inner accum ($n)'s writer data is not Mux $mux")
        }
        mux.foreach { mux =>
          dbgblk(s"InnerAccum($n)"){
            // RegAccumOp will empty inAccesses 
            var reduceOps = reader.accum(
              prefix={ case OpDef(Mux) => true; case _ => false }, 
              visitFunc=visitGlobalOut _
            )
            reduceOps = reduceOps.filterNot { case x:Access => true; case OpDef(Mux) => true; case _ => false }
            dbg(s"reduceOps:$reduceOps")
            val newOp = within(mux.parent.get, mux.getCtrl) {
              val in = reduceOps.head.localIns.flatMap { _.connected }.filterNot { _ == reader.out }
              val first = mux.input.connected.head
              val en = writer.en.connected
              stage(RegAccumOp(reduceOps).in(in).en(en).first(first))
            }
            val writer2 = assertOne(mux.out.T.filterNot { _ == writer }, s"writer2 for after mux $mux").asInstanceOf[MemWrite]
            swapConnection(writer2.data, mux.out, newOp.out)
          }
        }
      }
    }
    super.visitNode(n)
  } 

  //override def compVec(n:IR):Option[Int] = dbgblk(s"compVec($n)") {
    //n match {
      //case _:Def | _:Access =>
        //super.compVec(n).orElse { n.to[PIRNode].flatMap{ _.getCtrl.inferVec } }
      //case _ => super.compVec(n)
    //}
  //}

}

