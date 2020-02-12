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
    processControllers(n)

    processNameType(n)

    processLaneValids(n)

    processAsyncControl(n) // after processLaneValids

    processDRAMStore(n)
    
    processDebugging(n)

    processReduction(n)

    n.to[LockMem].foreach { mem =>
      if (mem.isDRAM) addLive(mem)
    }

    super.visitNode(n)
  } 

  def processNameType(n:N) = {
    n match {
      case n:DRAMCommand =>
        n.name(n.dram.sid)
      case n:HostWrite =>
        val mem = n.collectFirst[Memory](visitFunc=visitGlobalOut _)
        n.tp.mirror(mem.tp)
        n.sname.mirror(mem.sname)
      case n:HostRead =>
        n.sname.mirror(n.collectFirst[Memory](visitGlobalIn _).sname)
      case n:InAccess =>
        n.tp.mirror(n.mem.T.tp)
      case n:FringeCommand =>
        n.localOuts.foreach { out =>
          if (out.isConnected) {
            val fifo = out.collectFirst[FIFO]()
            out.setTp(fifo.tp.v)
            if (!n.isInstanceOf[DRAMCommand]) {
              out.setVec(fifo.banks.get.head)
            }
          }
        }
      case _ =>
    }
  }

  def processReduction(n:N) = {
    //TODO: this should be moved to rewrite transformer
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
  }

  def processControllers(n:N) = {
    n.to[Controller].foreach { n =>
      n.srcCtx.v.foreach { v => n.ctrl.get.srcCtx := v }
      n.progorder.v.foreach { v =>
        n.getCtrl.progorder := v
      }
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
  }

  /*
   * If a controller is asynchronous streaming, it means the controller enabled pure by its IO
   * dependencies and is not synchronized with its outer controller. We can remove the outer
   * controller as data dependency from this controller and all enables of the accesses under this 
   * controller that reference outer controllers will be removed
   * Currently we use spatial Stream.Forever to represents async controller
   * */
  def processAsyncControl(n:N) = {
    n match {
      case n:Controller if n.getCtrl.isAsync =>
        dbgblk(s"processAsyncControl($n)") {
          n.parentEn.disconnect
          n.en.disconnect
          n.to[LoopController].foreach { n =>
            n.stopWhen.disconnect
          }
        }
      case n:Access =>
        val (head, tail) = n.getCtrl.ancestorTree.span { !_.isAsync }
        tail.headOption.foreach { async =>
          dbgblk(s"processAsyncControl($n)") {
            val toSync = head :+ async
            val noSync = tail.tail.toSet
            dbg(s"noSync=${noSync}")
            n.en.connected.foreach { en =>
              dbg(s"${dquote(en.src)} ${en.src.getCtrl}")
              if (noSync.contains(en.src.getCtrl)) {
                n.en.disconnectFrom(en)
                dbg(s"disconnect ${dquote(n.en)} from ${dquote(en)}")
              }
            }
          }
        }
      case _ =>
    }
  }

  def processDRAMStore(n:N) = {
    n.to[DRAMStoreCommand].foreach { n =>
      val ack = n.ack.T.as[MemWrite].mem.T.outAccesses.head
      within(pirTop,ack.getCtrl) {
        stage(AccumAck().ack(ack))
      }
    }
  }

  /*
   * Create dependency between IO statement with host to force simulation stop after these
   * statements are completed.
   * */
  def processDebugging(n:N) = {
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
  }

  def processLaneValids(n:N) = {
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
        case access:RMWAccess => access.input.T.isInstanceOf[BlackBox] 
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

    // Add lane valid as an operand to division to prevent divide by zero when lane is invalid
    n.to[OpDef].foreach { 
      case n@OpDef(FixDiv | FltDiv) => 
        n.out.vecMeta.reset
        n.vec.reset
        stage(n.addInput(n.getCtrl.ctrler.get.laneValid))
      case _ =>
    }

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

