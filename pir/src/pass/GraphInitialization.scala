package pir
package pass

import pir.node._
import prism.graph._
import spade.param._

import scala.collection.mutable

class GraphInitialization(implicit compiler:PIR) extends PIRTraversal with SiblingFirstTraversal with UnitTraversal with PIRTransformer {

  override def runPass = {
    withGC(false) {
      super.runPass
    }
  }

  override def finPass = {
    super.finPass
    pirTop.topCtrl.descendentTree.foreach { setUID }
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
    n.to[DRAMCommand].foreach { n =>
      n.name(n.dram.sid)
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
          out.setTp(fifo.tp.v)
          if (!n.isInstanceOf[DRAMCommand]) {
            out.setVec(fifo.banks.get.head)
          }
        }
      }
    }
    n.to[HostRead].foreach { n =>
      n.sname.mirror(n.collectFirst[Memory](visitGlobalIn _).sname)
    }

    // Handle disabled load store from unaligned parallelization
    //n.to[FringeCommand].foreach { n =>
      //val reads = n.collectIn[MemRead]()
      //val writes = n.collectOut[MemWrite]()
      //(reads ++ writes).foreach { access =>
        //val setters = access match {
          //case read:MemRead => read.mem.T.inAccesses
          //case write:MemWrite => write.mem.T.outAccesses
        //}
        //setters.foreach { setter => 
          //val ctrlEns = access.getCtrl.ancestorTree.view.flatMap { c =>
            //c.ctrler.v.view.flatMap { ctrler =>
              //ctrler.en.T.collect { case v:CounterValid => v.out }
            //}
          //}.toSet[Output[PIRNode]]
          //setter.en(ctrlEns)
        //}
      //}
    //}

    // Add laneValids to enable of memory access
    def connectLaneValid(access:Access):Unit = {
      val ctrl = access.getCtrl
      if (ctrl.isLeaf) {
        ctrl.ctrler.v.foreach { 
          case ctrler:LoopController =>
            access.mem.T match {
              case mem:Reg if ctrler.par.get > 1 | access.isInnerReduceOp.get => return
              case _ => 
                dbg(s"Connect $access to laneValid")
                access.en(ctrler.laneValid)
            }
          case _ =>
        }
      }
    }

    // Add loop valid related enables. 
    n.to[Access].foreach { access =>
      val connectToBB = access match {
        case access:WriteAccess => access.data.T.isInstanceOf[BlackBox]
        case access:ReadAccess => access.out.T.exists{ _.isInstanceOf[BlackBox] }
      }
      if (!connectToBB) {
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
      connectLaneValid(access)
    }

    n.to[OpDef].foreach { 
      case n@OpDef(FixDiv | FltDiv) => 
        n.out.vecMeta.reset
        n.vec.reset
        stage(n.addInput(n.getCtrl.ctrler.get.laneValid))
      case _ =>
    }

    n.to[DRAMStoreCommand].foreach { n =>
      val ack = n.ack.T.as[MemWrite].mem.T.outAccesses.head
      within(pirTop,ack.getCtrl) {
        stage(AccumAck().ack(ack))
      }
    }

    n match {
      case n@(_:PrintIf | _:AssertIf | _:ExitIf) =>
        n.tp.reset
        n.tp := Bool
        if (config.enableSimDebug) {
          val write = within(n.parent.get, n.getCtrl) { stage(MemWrite().data(n.as[Def].out)) }
          argOut(write)
        }
      case _ =>
    }

    // Convert reduction operation
    // Spaital IR:
    // accum.write (reduce(mux(dummy, input, initOrInput), accum.read))
    // Mux can be eliminated if input == initOrInput
    n.to[MemWrite].foreach { writer =>
      if (writer.isInnerReduceOp.get && writer.mem.T.isInnerAccum.get) {
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
              val mapping = mirrorAll(reduceOps, mapping=mutable.Map[IR,IR](init->reader.out, op.out->input))
              (Some(mapping(reduceOps.last)), input)
            case op@OpDef(_) =>
              (None, op.inputs(0).singleConnected.get)
          }
          val identity = writer.mem.T.inits.v.getOrElse(bug(s"No identity value for reduction ${quoteSrcCtx(writer)}"))
          dbg(s"init=${dquote(init)}")
          dbg(s"input=${dquote(input)}")
          val accumOp = within(readerParent, readerCtrl) {
            val firstIter = writer.getCtrl.ctrler.get.to[LoopController].map { _ .firstIter }
            stage(RegAccumOp(reduceOps, identity).in(input).en(writer.en.connected).first(firstIter).init(init))
          }
          val redOp = reduceOps.last.as[DefNode[PIRNode]]
          if (redOp.output.get.neighbors.collect { case w:MemWrite => true }.size > 1) {
            // 1. 
            // val acc1 = redOp(input, acc1) // isInnerAccum
            // val acc2 = redOp(input, acc1)
            disconnect(writer, redOp)
            swapOutput(reduceOps.last.as[DefNode[PIRNode]].output.get, accumOp.out)
          } else {
            // 2. 
            // val acc1 = redOp(input, acc1) // isInnerAccum
            // val ... = acc1.read
            swapConnection(writer.data, redOp.output.get, accumOp.out)
          }
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

  // Setting uid based on controller enable doesn't work for controllers that are fully unrolled
  //def setUID(ctrl:ControlTree) = {
    //val cuid = ctrl.ctrler.v.fold[List[Int]](Nil) { _.en.neighbors.collect { case v:CounterValid => v }
      //.groupBy { _.getCtrl }.toList.sortBy { _._1.ancestors.size } // Outer most to inner most
      //.flatMap { case (pctrl, vs) => 
        //val ps = vs.sortBy { _.counter.T.idx.get }.map { case CounterValid(List(i)) => i }
        //dbg(s"$ctrl: $pctrl[${ps.mkString(",")}]")
        //ps
      //}
    //}
    //val puid = ctrl.parent.map { _.uid.get }.getOrElse(Nil)
    //val uid = puid ++ cuid
    //dbg(s"$ctrl.uid=[${uid.mkString(",")}]")
    //ctrl.uid := uid
  //}

  // UID is the unrolled ids of outer loop controllers
  def setUID(ctrl:ControlTree) = {
    val parallels = ctrl.ancestors.filter { _.schedule == ForkJoin }
    val uid = parallels.toList.map { parallel =>
      parallel.children.indexWhere { child =>
        ctrl.isDescendentOf(child) || child == ctrl
      }
    }
    ctrl.uid := uid
  }

}

