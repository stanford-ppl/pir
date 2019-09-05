package pir
package pass

import pir.node._
import prism.graph._
import spade.param._

import scala.collection.mutable

class GraphInitialization(implicit compiler:PIR) extends PIRTraversal with SiblingFirstTraversal with UnitTraversal with PIRTransformer {

  override def finPass = {
    super.finPass
    pirTop.topCtrl.descendentTree.foreach { setUID }
    pirTop.collectChildren[Controller].foreach { ctrler =>
      ctrler.en.neighbors.collect { case v:CounterValid => v }.foreach { v =>
        disconnect(ctrler.en, v)
      }
    }
  }

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
    //n.to[LoopController].foreach { n =>
      //n.stopWhen.T.foreach { read =>
        //read.to[MemRead].foreach { read =>
          //read.ctrl.reset
          //read.ctrl(n.getCtrl.parent.get)
          //val mem = read.mem.T
          //mem.nonBlocking := true
        //}
      //}
    //}
    //n.to[LoopController].foreach { n =>
      //n.stopWhen.T.foreach { read =>
        //var writer = read.as[MemRead].mem.T.inAccesses.head.as[MemWrite]
        //var data = writer.data.singleConnected.get
        //data.src match {
          //case read:MemRead =>
            //writer = read.as[MemRead].mem.T.inAccesses.head.as[MemWrite]
            //data = writer.data.singleConnected.get
        //}
        //val ens = writer.en.connected
        //var stop = within(writer.getCtrl, writer.parent.get) {
          //(data +: ens).reduce[Output[PIRNode]]{ case (a,b) =>
            //stage(OpDef(FixAnd).addInput(a,b)).out
          //}
        //}
        //n.stopWhen.disconnect
        //if (writer.getCtrl != n.getCtrl) {
          //val write = within(writer.getCtrl, writer.parent.get) {
            //stage(MemWrite().data(stop))
          //}
          //val fifo = within(pirTop) { stage(Reg().depth(2).addAccess(write)) }
          //stop = within(n.getCtrl, n.parent.get) {
            //stage(MemRead().setMem(fifo)).out
          //}
        //}
        //n.stopWhen(stop)
      //}
    //}
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

    n.to[FringeDenseStore].foreach { n =>
      // Transfer write enable on store valid and store data to valid
      val vwrite = n.valid.T.as[MemRead].mem.T.inAccesses.head.as[MemWrite]
      if (vwrite.en.isConnected) {
        within(vwrite.parent.get, vwrite.getCtrl) {
          val vdata = vwrite.data.singleConnected.get
          val vs = (vdata::vwrite.en.connected++n.getCtrl.ctrler.get.en.connected).toSet
          val v = vs.reduce[Output[PIRNode]]{ case (v1,v2) =>
            stage(OpDef(And).addInput(v1,v2)).out
          }
          vwrite.data.disconnect
          vwrite.data(v)
          vwrite.en.disconnect
          n.data.T.as[MemRead].mem.T.inAccesses.head.as[MemWrite].en.disconnect
        }
      }
    }

    // TODO: proper handle of FIFO enables
    // Remove loop lane valid dependent enable
    n.to[MemRead].foreach { read =>
      read.en.T.foreach {
        case v:CounterValid => read.en.disconnectFrom(v.out)
        case _ =>
      }
    }

    n.to[MemWrite].foreach { write =>
      if (write.mem.T.isFIFO) {
        write.en.T.foreach {
          case v:CounterValid => write.en.disconnectFrom(v.out)
          case _ =>
        }
      }
    }


    n.to[DRAMStoreCommand].foreach { n =>
      val write = within(pirTop, n.getCtrl) {
        val ack = n.ack.T.as[MemWrite].mem.T.outAccesses.head
        within(ack.getCtrl) {
          stage(MemWrite().data(ack))
        }
      }
      argOut(write).name(s"${n}_ack")
    }
    
    n.to[BankedAccess].foreach { access =>
      val ctrl = access.getCtrl
      ctrl.ancestorTree.foreach { c =>
        c.ctrler.v.foreach { ctrler =>
          val ens = ctrler.en.T.collect { case v:CounterValid => v }
          ens.foreach { en =>
            if (!access.en.isConnectedTo(en.out)) {
              access.en(en.out)
            }
          }
        }
      }
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
          val readerParent = reader.parent.get
          val readerCtrl = reader.getCtrl
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
          val accumOp = within(readerParent, readerCtrl) {
            val firstIter = writer.getCtrl.ctrler.get.to[LoopController].map { _ .firstIter }
            val en = readerCtrl.ctrler.get.valid
            stage(RegAccumOp(reduceOps).in(input).en(writer.en.connected, en).first(firstIter).init(init))
          }
          disconnect(writer, reduceOps.last)
          swapOutput(reduceOps.last.as[DefNode[PIRNode]].output.get, accumOp.out)
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

  // UID is the unrolled ids of outer loop controllers
  def setUID(ctrl:ControlTree) = {
    val cuid = ctrl.ctrler.v.fold[List[Int]](Nil) { _.en.neighbors.collect { case v:CounterValid => v }
      .groupBy { _.getCtrl }.toList.sortBy { _._1.ancestors.size } // Outer most to inner most
      .flatMap { case (pctrl, vs) => 
        val ps = vs.sortBy { _.counter.T.idx.get }.map { case CounterValid(List(i)) => i }
        dbg(s"$ctrl: $pctrl[${ps.mkString(",")}]")
        ps
      }
    }
    val puid = ctrl.parent.map { _.uid.get }.getOrElse(Nil)
    val uid = puid ++ cuid
    dbg(s"$ctrl.uid=[${uid.mkString(",")}]")
    ctrl.uid := uid
  }

}

