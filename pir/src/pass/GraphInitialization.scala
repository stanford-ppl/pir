package pir
package pass

import pir.node._
import prism.graph._
import spade.param._

import scala.collection.mutable

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
      n.en.disconnect
      // TODO: migrate this enable signal to write enable of all memory and read enable of sram
    }
    //n.to[LoopController].foreach { n =>
      //n.stopWhen.T.foreach { n =>
        //n.to[MemRead].foreach { read =>
          //val mem = read.mem.T
          //within(mem.parent.get) { 
            //val newMem = FIFO().mirrorMetas(mem)
            //mem.accesses.sortBy{_.order.get}.foreach { a =>
              //a.mem.disconnect
              //a.setMem(newMem)
            //}
            //stage(newMem)
          //}
          //removeNodes(List(mem))
        //}
      //}
    //}
    n.to[LoopController].foreach { n =>
      n.stopWhen.T.foreach { n =>
        n.to[MemRead].foreach { read =>
          val mem = read.mem.T
          mem.nonBlocking := true
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
      val mem = n.collectFirst[Memory](visitFunc=visitGlobalOut _)
      n.tp.mirror(mem.tp)
      n.sname.mirror(mem.sname)
    }
    n.to[InAccess].foreach { n =>
      n.tp.mirror(n.mem.T.tp)
    }
    n.to[FringeCommand].foreach { n =>
      n.localOuts.foreach { out =>
        if (out.isConnected) {
          val fifo = out.collectFirst[FIFO]()
          out.setVec(fifo.banks.get.head)
          out.setTp(fifo.tp.v)
        }
      }
    }
    n.to[HostRead].foreach { n =>
      n.sname.mirror(n.collectFirst[Memory](visitGlobalIn _).sname)
    }

    //n.to[FringeSparseStore].foreach { n =>
      //// hack to get range of scatter
      //val addr = n.addr.T.as[MemRead].mem.T.inAccesses.head
      //val ctr = assertOne(addr.getCtrl.ctrler.get.as[LoopController].cchain, s"$n loop cchain")
      //val max = ctr.max.T.get
      //max match {
        //case max:Const if max.getCtrl == n.getCtrl=>
          //max.ctrl.reset
          //max.ctrl(addr.getCtrl)
        //case _ =>
      //}
      //n.range(max)
    //}

    n.to[DRAMStoreCommand].foreach { n =>
      val write = within(pirTop, n.getCtrl) {
        val ack = n.ack.T.as[MemWrite].mem.T.outAccesses.head
        within(ack.getCtrl) {
          stage(MemWrite().data(ack))
        }
      }
      argOut(write).name(s"${n}_ack")
    }

    if (config.enableSimDebug) {
      n.to[PrintIf].foreach { n =>
        n.tp.reset
        n.tp := Bool
        val write = within(n.parent.get, n.getCtrl) { stage(MemWrite().data(n.out)) }
        argOut(write)
      }
    }
    
    // Convert reduction operation
    // Spaital IR:
    // accum.write (reduce(mux(dummy, input, initOrInput), accum.read))
    // Mux can be eliminated if input == initOrInput
    n.to[InAccess].foreach { writer =>
      if (writer.isInnerReduceOp.get && writer.mem.isInnerAccum.get) {
        dbgblk(s"Transforming Reduce Op $writer") {
          var reduceOps = writer.accum(visitFunc = { case n:PIRNode => 
              visitGlobalIn(n).filter { _.isInnerReduceOp.get }
            }
          ).filterNot { _ == writer }.reverse
          if (reduceOps.size < 2) {
            err(s"Unexpected reduce op for writer $writer: ${reduceOps}")
          }
          val reader = reduceOps.head.as[OutAccess]
          reduceOps = reduceOps.tail
          dbg(s"reader=$reader")
          dbg(s"reduceOps=$reduceOps")
          val (init, input) = reduceOps.head match {
            case op@OpDef(Mux) =>
              reduceOps = reduceOps.tail
              val init = op.inputs(2).singleConnected.get
              val input = op.inputs(1).singleConnected.get
              // init = reduce(input, init)
              val mapping = mirrorAll(reduceOps, mapping=mutable.Map[IR,IR](init->reader.out, op.out->input))
              (Some(mapping(reduceOps.last)), input)
            case op@OpDef(_) =>
              (None, op.inputs(0).singleConnected.get)
          }
          dbg(s"init=${dquote(init)}")
          dbg(s"input=${dquote(input)}")
          val accumOp = within(reader.parent.get, reader.getCtrl) {
            val firstIter = writer.getCtrl.ctrler.get.to[LoopController].map { _ .firstIter }
            val en = reader.getCtrl.ctrler.get.valid
            stage(RegAccumOp(reduceOps).in(input).en(writer.en.connected, en).first(firstIter).init(init))
          }
          disconnect(writer, reduceOps.last)
          swapOutput(reduceOps.last.as[DefNode[PIRNode]].output.get, accumOp.out)
          removeNodes(reduceOps :+ writer :+ reader :+ writer.mem.T)
        }
      }
    }
    super.visitNode(n)
  } 

  def argOut(write:MemWrite) = {
    val reg = within(pirTop.argFringe, pirTop.topCtrl) { 
      val reg = Reg().depth(1)
      write.setMem(reg)
      stage(reg)
    }
    within(pirTop.argFringe, pirTop.hostOutCtrl) {
      val read = stage(MemRead().setMem(reg))
      stage(HostRead().input(read))
    }
    reg
  }

  def createSeqCtrler = {
    val tree = ControlTree(Sequenced)
    val ctrler = within(tree) { UnitController().srcCtx("GraphInitialization") }
    tree.par := 1
    tree.ctrler(ctrler)
    tree.parent.foreach { parent =>
      parent.ctrler.v.foreach { pctrler =>
        ctrler.parentEn(pctrler.valid)
      }
    }
    ctrler
  }

}

