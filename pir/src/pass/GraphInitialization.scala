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
    n.to[LoopController].foreach { n =>
      n.stopWhen.T.foreach { n =>
        n.to[MemRead].foreach { read =>
          val mem = read.mem.T
          within(mem.parent.get) { 
            val newMem = FIFO().mirrorMetas(mem)
            mem.accesses.sortBy{_.order.get}.foreach { a =>
              a.mem.disconnect
              a.setMem(newMem)
            }
            stage(newMem)
          }
          removeNodes(List(mem))
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
    // Spatial IR: 
    // with init
    // accum.write(reduce(input, mux(isFirst, init, accum.read)))
    // without init
    // accum.write(mux(isFirst, input, reduce(input, accum.read)))
    //
    // val read = accum.read
    // val reduceOp = func(read, input)
    //n.to[Memory].foreach { n =>
      //if (n.isInnerAccum.get && n.inAccesses.nonEmpty) {
        //val writer = assertOne(n.inAccesses, s"writer of inner accum $n").as[MemWrite]
        //val reader = assertOne(n.outAccesses, s"reader of inner accum $n")
        //val mux = writer.data.T match {
          //case m@OpDef(Mux) => Some(m)
          //case m:RegAccumOp => None
          //case mux => throw PIRException(s"inner accum ($n)'s writer data is not Mux $mux")
        //}
        //mux.foreach { mux =>
          //dbgblk(s"InnerAccum($n)"){
            //// RegAccumOp will empty inAccesses 
            //var reduceOps = reader.accum(
              //prefix={ case OpDef(Mux) => true; case _ => false }, 
              //visitFunc=visitGlobalOut _
            //)
            //reduceOps = reduceOps.filterNot { case x:Access => true; case OpDef(Mux) => true; case _ => false }
            //dbg(s"reduceOps:$reduceOps")
            //val newOp = within(mux.parent.get, mux.getCtrl) {
              //val in = reduceOps.head.localIns.flatMap { _.connected }.filterNot { _ == reader.out }
              //val first = mux.inputs(0).singleConnected.get
              //val en = writer.en.connected
              //stage(RegAccumOp(reduceOps).in(in).en(en).first(first))
            //}
            //val writer2 = assertOne(mux.out.T.filterNot { _ == writer }, s"writer2 for after mux $mux").asInstanceOf[MemWrite]
            //swapConnection(writer2.data, mux.out, newOp.out)
          //}
        //}
      //}
    //}
    
    
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
            stage(RegAccumOp(reduceOps).in(input).en(writer.en.connected).first(firstIter).init(init))
          }
          disconnect(writer, reduceOps.last)
          swapOutput(reduceOps.last.as[DefNode[PIRNode]].output.get, accumOp.out)
          removeNodes(reduceOps :+ writer :+ reader :+ writer.mem.T)
        }
      }
    }
    super.visitNode(n)
  } 

}

