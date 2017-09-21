package pir.pass

import pir._
import pir.node._
import pir.util._

import spade.util._

import pirc._
import pirc.util._
import pirc.enums._

import scala.collection.mutable._

class CtrlAlloc(implicit design: PIR) extends Pass with Logger {
  import pirmeta._

  def shouldRun = PIRConfig.ctrl

  override lazy val stream = newStream(s"CtrlAlloc.log")

  addPass(canRun=design.memoryAnalyzer.hasRun, runCount=1) {
    design.top.ctrlers.foreach { ctrler =>
      connectDone(ctrler)
      setPredicate(ctrler)
    }
    design.top.ctrlers.foreach { ctrler =>
      connectEnable(ctrler)
      connectMemoryControl(ctrler)
    }
    topoSort(design.top).reverse.foreach { ctrler =>
      connectSibling(ctrler)
      connectHeads(ctrler)
      connectLasts(ctrler)
    }
    //connectLastsRec(design.top)
  }

  def setPredicate(ctrler:Controller) = {
    setAccumPredicate(ctrler)
    setFifoPredicate(ctrler)
  }

  def setFifoPredicate(ctrler:Controller) = {
    ctrler match {
      case cu:Pipeline =>
        val lookUp = Map[Reg, FIFO]()
        def addPredicate(mem:FIFO, sel:InPort) = {
          sel.from.src match {
            case PipeReg(stage, reg) => lookUp += reg -> mem
            case _ => throw PIRException(s"Not supported format for FIFO Predicate in ${ctrler}")
          }
        }
        cu.stages.reverseIterator.foreach { stage =>
          stage.fu.foreach { fu =>
            fu.op match {
              case MuxOp =>
                val sel::data = fu.operands
                data.foreach { 
                  _.from.src match {
                    case PipeReg(stage, LoadPR(mem:FIFO)) => addPredicate(mem, sel)
                    case mem:FIFO => addPredicate(mem, sel)
                    case _ =>
                  }
                }
              case _ =>
            }
            fu.out.to.foreach {
              _.src match {
                case PipeReg(stage, reg) if lookUp.contains(reg) =>
                  var const:Option[Int] = None
                  var counter:Option[Counter] = None
                  fu.operands.foreach {
                    _.from.src match {
                      case Const(c:Int) => const = Some(c)
                      case PipeReg(stage, CtrPR(ctr)) => counter = Some(ctr)
                      case ctr:Counter => counter = Some(ctr)
                      case x => throw PIRException(s"Not supported operand $x for FIFO predication in ${ctrler}")
                    }
                  }
                  if (const.isEmpty || counter.isEmpty) {
                    throw PIRException(s"Not supported operand for FIFO predication in ${ctrler} const=$const counter=$counter")
                  } else {
                    fu.op match {
                      case op:FixOp if fixCompOps.contains(op) => 
                        val predUnit = cu.ctrlBox.setFifoPredicate(counter.get, op, const.get)
                        val fifo = lookUp(reg)
                        fifo.predicate.connect(predUnit.out)
                      case op => throw PIRException(s"Unsupported op for FIFO predication $op")
                    }
                  }
                case _ =>
              }
            }
          }
        }
      case _ =>
    }
  }

  def setAccumPredicate(cu:Controller) = {
    (cu, cu.ctrlBox) match {
      case (cu:InnerController, cb:InnerCtrlBox) =>
        assert(cu.accumRegs.size<=1, s"Assume $cu can only have a single accumulator at the moment")
        cu.accumRegs.headOption match {
          case None => //out.v := false
          case Some(acc) => 
            val ctr = accumCounterOf(acc)
            cb.setAccumPredicate(ctr, FixEql, 0)
        }
      case (cu, cb) =>
    }
  }

  /*
   * Use local copy if existed. Otherwise route the done
   * */
  def getDone(current:ComputeUnit, cchain:CounterChain) = {
    if (current.containsCopy(cchain)) {
      val lcchain = current.getCC(cchain)
      current.ctrlBox match {
        case cb:StageCtrlBox => current.getCC(lcchain).outer.done
        case cb:MemCtrlBox if (readCChainsOf(cb.ctrler).last == lcchain) => cb.readDone.out
        case cb:MemCtrlBox if (writeCChainsOf(cb.ctrler).last == lcchain) => cb.writeDone.out
      }
    } else {
      cchain.ctrler.ctrlBox match {
        case cb:StageCtrlBox => cb.doneOut
        case cb:MemCtrlBox if (readCChainsOf(cb.ctrler).last == cchain) => cb.readDone.out
        case cb:MemCtrlBox if (writeCChainsOf(cb.ctrler).last == cchain) => cb.writeDone.out
      }
    }
  }

  def connectMemoryControl(ctrler:Controller) = ctrler match { case cu:ComputeUnit =>
    cu.mems.foreach { mem =>
      (mem, cu.ctrlBox) match {
        case (mem:FIFO, cb:MemCtrlBox) if mem.readPort.to.exists{ in => cb.ctrler.sram.writePortMux.ins.contains(in) } =>
          //mem.enqueueEnable.connect(mem.writePort.valid)
          //mem.inc.connect(mem.writePort.valid)
          mem.dequeueEnable.connect(cb.writeEnDelay.out)
          mem.dec.connect(cb.writeEn.out)
        case (mem:FIFO, cb:MemCtrlBox) if forRead(mem) =>
          //mem.enqueueEnable.connect(mem.writePort.valid)
          //mem.inc.connect(mem.writePort.valid)
          mem.dequeueEnable.connect(cb.readEn.out)
          mem.dec.connect(cb.readEn.out)
        case (mem:FIFO, cb:MemCtrlBox) if forWrite(mem) =>
          //mem.enqueueEnable.connect(mem.writePort.valid)
          //mem.inc.connect(mem.writePort.valid)
          mem.dequeueEnable.connect(cb.writeEn.out)
          mem.dec.connect(cb.writeEn.out)
        case (mem:FIFO, cb:MCCtrlBox) if mem.name.get=="data" =>
          //mem.enqueueEnable.connect(mem.writePort.valid)
          //mem.inc.connect(mem.writePort.valid)
          mem.dequeueEnable.connect(cb.running)
          mem.dec.connect(cb.running)
        case (mem:FIFO, cb:StageCtrlBox) =>
          //mem.enqueueEnable.connect(mem.writePort.valid)
          //mem.inc.connect(mem.writePort.valid)
          mem.dequeueEnable.connect(cb.en.out)
          mem.dec.connect(cb.en.out)
        case (mem:SRAM, cb:MemCtrlBox) =>
          mem.enqueueEnable.connect(cb.writeDoneDelay.out)
          //mem.inc.connect(cb.writeDone.out)
          mem.dequeueEnable.connect(cb.readDoneDelay.out)
          mem.dec.connect(cb.readDone.out)
        case (mem:MultiBuffer, cb) =>
          //mem.inc.connect(mem.writePort.valid)
          //mem.enqueueEnable.connect(mem.writePort.valid)
          swapReadCChainOf.get(mem).foreach { cc => 
            mem.dequeueEnable.connect(getDone(cu, cc))
            mem.dec.connect(getDone(cu, cc))
          }
      }
    }
    case _ =>
  }

  def connectLast(parent:Controller, last:Controller) = {
    (parent, last, parent.ctrlBox, last.ctrlBox) match {
      case (parent:Top, last:ComputeUnit, pcb:TopCtrlBox, ccb:StageCtrlBox) =>
        pcb.status.connect(ccb.doneOut)
      case (parent:StreamController, last:MemoryController, pcb:OuterCtrlBox, ccb:MCCtrlBox) =>
        val tk = pcb.tokenBuffer(last)
        tk.inc.connect(ccb.doneOut)
        tk.dec.connect(pcb.childrenAndTree.out)
        pcb.childrenAndTree.addInput(tk.out)
      case (parent:StreamController, last:Pipeline, pcb:OuterCtrlBox, ccb:StageCtrlBox) =>
        val tk = pcb.tokenBuffer(last)
        tk.inc.connect(ccb.doneOut)
        tk.dec.connect(pcb.childrenAndTree.out)
        pcb.childrenAndTree.addInput(tk.out)
      case (parent:Controller, last:ComputeUnit, pcb:OuterCtrlBox, ccb:StageCtrlBox) =>
        val tk = pcb.tokenBuffer(last)
        tk.inc.connect(ccb.doneOut)
        tk.dec.connect(pcb.childrenAndTree.out)
        pcb.childrenAndTree.addInput(tk.out)
    }
  }

  def connectLasts(parent:Controller, lasts:List[Controller]):Unit = {
    if (lasts.isEmpty) return
    dprintln(s"OCU_MAX_CIN=$OCU_MAX_CIN parent.cins=${parent.cins.size}") //TODO fix this for smv
    val lastGroups = lasts.grouped(OCU_MAX_CIN - parent.cins.size).toList
    val midParents = lastGroups.map { lasts =>
      val midParent = if (lastGroups.size==1) parent else {
        dprintln(s"parent=$parent lastGroups:$lastGroups")
        val clone = parent.cloneType("collector")
        localCChainOf(clone) = CounterChain.dummy(clone.asCU, design)
        isHead(clone) = false 
        isLast(clone) = true
        isStreaming(clone) = isStreaming(lasts.head)
        isPipelining(clone) = isPipelining(lasts.head)
        isTailCollector(clone) = true
        parOf(clone) = parOf(parent)
        ancestorsOf(clone) = clone :: ancestorsOf(parent)
        descendentsOf(clone) = List(clone)
        clone.asCU.parent(parent)
        connectDone(clone)
        connectEnable(clone)
        clone
      }
      dprintln(s"$parent midParent:$midParent lasts:[${lasts.mkString(",")}]")
      lasts.foreach { last =>
        connectLast(midParent, last)
      }
      midParent
    }
    if (midParents.size>1) connectLasts(parent, midParents)
  }

  def connectHeads(ctrler:Controller) = {
    //val heads = ctrler.children.filter{_.isHead}
    val heads = ctrler.children.filter { case c:OuterController => true; case c:InnerController => c.isHead }
    dprintln(s"$ctrler heads:[${heads.mkString(",")}]")
    heads.foreach { head =>
      (ctrler.ctrlBox, head.ctrlBox) match {
        case (pcb:OuterCtrlBox, hcb:StageCtrlBox) if isStreaming(head) =>
          val tk = hcb.tokenBuffer(ctrler)
          tk.inc.connect(pcb.enOut)
          tk.dec.connect(hcb.en.out)
          hcb.siblingAndTree.addInput(tk.out)
        case (pcb:OuterCtrlBox, hcb:StageCtrlBox) if isPipelining(head) =>
          val tk = hcb.tokenBuffer(ctrler)
          tk.inc.connect(pcb.enOut)
          tk.dec.connect(hcb.done.out)
          hcb.siblingAndTree.addInput(tk.out)
        case (pcb:TopCtrlBox, hcb:StageCtrlBox) if isPipelining(head) =>
          val tk = hcb.tokenBuffer(ctrler)
          tk.inc.connect(pcb.command)
          tk.dec.connect(hcb.done.out)
          hcb.siblingAndTree.addInput(tk.out)
      }
    }
  }

  def connectLasts(ctrler:Controller):Unit = {
    val lasts = ctrler.children.filter{_.isLast}
    connectLasts(ctrler, lasts)
  }

  def connectLastsRec(ctrler:Controller):Unit = {
    val lasts = ctrler.children.filter{_.isLast}
    connectLasts(ctrler, lasts)

    val seqs = ctrler.children.filter{ child => child.isSeq && !lasts.contains(child) }
    seqs.foreach(connectLasts)
    lasts.foreach(connectLastsRec)
  }

  def connectToken(consumer:Controller, tk:(Any, OutPort)):Unit = {
    val cb = consumer.ctrlBox.asInstanceOf[StageCtrlBox]
    val (dep, token) = tk
    val tb = cb.tokenBuffer(dep)
    tb.inc.connect(token)
    tb.dec.connect(cb.done.out)
    cb.siblingAndTree.addInput(tb.out)
  }

  def connectTokens(consumer:Controller, tokens:List[(Any, OutPort)]):Unit = {
    val tokenGroups = if (tokens.size==0) Nil else tokens.grouped(OCU_MAX_CIN - consumer.cins.size).toList
    val midConsumers = tokenGroups.map { tokens =>
      val midConsumer = if (tokenGroups.size==1) consumer else {
        val clone = consumer.cloneType("splitter")
        localCChainOf(clone) = CounterChain.dummy(clone.asCU, design)
        isHead(clone) = false
        isLast(clone) = false
        isStreaming(clone) = isStreaming(consumer)
        isPipelining(clone) = isPipelining(consumer)
        isHeadSplitter(clone) = true
        parOf(clone) = parOf(consumer)
        val _ :: ancestors = ancestorsOf(consumer)
        ancestorsOf(clone) = clone :: ancestors
        descendentsOf(clone) = List(clone)
        clone.asCU.parent(consumer.asCU.parent)
        connectDone(clone)
        connectEnable(clone)
        clone
      }
      tokens.foreach { token =>
        connectToken(midConsumer, token)
      }
      midConsumer
    }
    if (midConsumers.size>1) 
      connectTokens(consumer, midConsumers.map(cm => (cm, cm.ctrlBox.asInstanceOf[StageCtrlBox].doneOut)))
  }

  def connectSibling(ctrler:Controller) = {
    // Forward dependency
    // - FIFO.notEmpty + Mbuffer.valid
    (ctrler, ctrler.ctrlBox) match {
      case (cu:MemoryPipeline, cb:MemCtrlBox) =>
        cu.lmems.foreach {
          case mem if (forRead(mem)) => cb.readFifoAndTree.addInput(mem.notEmpty)
          case mem if (forWrite(mem)) => cb.writeFifoAndTree.addInput(mem.notEmpty)
        }
        cb.readFifoAndTree.addInput(cu.sram.notEmpty)
        cb.writeFifoAndTree.addInput(cu.sram.notFull)
      case (cu:ComputeUnit, cb:InnerCtrlBox) =>
        cu.lmems.foreach { mem => cb.fifoAndTree.addInput(mem.notEmpty) }
      case (cu:MemoryController, cb:MCCtrlBox) =>
        cu.lmems.foreach { mem => cb.fifoAndTree.addInput(mem.notEmpty) }
      case _ =>
    }
    // - Token
    //(ctrler, ctrler.ctrlBox) match {
      //case (cu:ComputeUnit, cb:OuterCtrlBox) if isStreaming(cu) =>
      //case (cu:ComputeUnit, cb:StageCtrlBox) if isPipelining(cu) =>
        //// Token
        //val tokens = cu.trueConsumed.flatMap { mem =>
          //(mem, mem.producer) match {
            //case (mem:SRAM, producer:ComputeUnit) => None
              //// SRAM no need for token because handled by FIFONotEmpty
            //case (mem, producer:ComputeUnit) =>
              //Some(mem, getDone(cu, swapWriteCChainOf(mem))) // should always from remote
            //case (mem, producer:Top) => None // No synchronization needed
          //}
        //}
        //connectTokens(cu, tokens)
      //case _ =>
    //}
    // Backward pressure
    // - FIFO.notFull
    (ctrler, ctrler.ctrlBox) match {
      case (cu:ComputeUnit, cb:InnerCtrlBox) =>
        cu.writtenMems.filter{ mem => backPressureOf(mem) }.foreach { mem => 
          cb.tokenInAndTree.addInput(mem.notFull)
        }
      case (cu:ComputeUnit, cb:MemCtrlBox) =>
        cu.writtenMems.filter{ mem => backPressureOf(mem) }.foreach { mem => 
          cb.tokenInAndTree.addInput(mem.notFull)
        }
      case (cu, cb) =>
    }
    // - Credit
    //(ctrler, ctrler.ctrlBox) match {
      //case (cu:ComputeUnit, cb:StageCtrlBox) if isPipelining(cu) => 
        //cu.trueProduced.foreach { mem =>
          //mem.consumer match {
            //case consumer:ComputeUnit if mem.buffering != 1 & mem.buffering < cu.parent.length =>
              //val inc = consumer.ctrlBox match {
                //case cmcb:MemCtrlBox if forRead(mem) => cmcb.readDone.out
                //case cmcb:MemCtrlBox if forWrite(mem) => cmcb.readDone.out
                //case cmcb:StageCtrlBox => cmcb.doneOut
              //}
              //val cd = cb.creditBuffer(mem, mem.buffering)
              //cd.inc.connect(inc)
              //cd.dec.connect(cb.done.out)
              //cb.siblingAndTree.addInput(cd.out)
            //case consumer =>
          //}
        //}
      //case (cu, cb) =>
    //}
  }

  def chainCChain(cchains:List[CounterChain]) = {
    val chained = ListBuffer[CounterChain]()
    cchains.zipWithIndex.foreach { case (cc, i) =>
      if (i!=0) {
        cc.inner.en.connect(cchains(i-1).outer.done)
      }
    }
  }

  def connectEnable(ctrler:Controller) = {
    (ctrler, ctrler.ctrlBox) match {
      case (ctrler:MemoryPipeline, cb:MemCtrlBox) =>
        readCChainsOf(ctrler).headOption.foreach { _.inner.en.connect(cb.readEn.out) }
        writeCChainsOf(ctrler).headOption.foreach { _.inner.en.connect(cb.writeEn.out) }
        chainCChain(readCChainsOf(ctrler))
        chainCChain(writeCChainsOf(ctrler))
      case (ctlrer:MemoryController, cb) =>
      case (ctrler:ComputeUnit, cb:StageCtrlBox) if isHeadSplitter(ctrler) | isTailCollector(ctrler) =>
        localCChainOf(ctrler).inner.en.connect(cb.en.out)
      case (ctrler:InnerController, cb:InnerCtrlBox) =>
        localCChainOf(ctrler).inner.en.connect(cb.en.out)
        chainCChain(compCChainsOf(ctrler))
      case (ctrler:OuterController, cb:OuterCtrlBox) =>
        localCChainOf(ctrler).inner.en.connect(cb.en.out)
      case (ctrler, cb) =>
    }
  }

  def connectDone(ctrler:Controller) = {
    (ctrler, ctrler.ctrlBox) match {
      case (ctrler:MemoryPipeline, cb:MemCtrlBox) =>
        //TODO HACK for case when readCC and writeCC are the same set of counter chain. There will be two copy of the
        //swapReadCChain in the same controller. the duplicate's original is also swapReadCChain.
        //However CU.getCC will only return the original copy 
        val readCC = ctrler.cchains.filter { cc => cc.original == swapReadCChainOf(ctrler.sram) && forRead(cc) }.head
        val readDone = readCC.outer.done
        cb.readDone.in.connect(readDone)
        val writeDone = ctrler.getCC(swapWriteCChainOf(ctrler.sram)).outer.done
        cb.writeDone.in.connect(writeDone)
      case (ctlrer:MemoryController, cb) =>
      case (ctrler:ComputeUnit, cb:StageCtrlBox) =>
        cb.done.in.connect(localCChainOf(ctrler).outer.done)
      case (ctrler, cb) =>
    }
  }

  override def finPass = {
    design.top.compUnits.foreach {
      case cu:MemoryController =>
      case cu => assert(cu.cchains.nonEmpty, s"$cu's cchain is empty")
    }
    //design.top.compUnits.foreach { cu =>
      //cu match {
        //case cu:OuterController =>
          //cu.cchains.foreach{ cc =>
            //if (cc.inner.en.isConnected || cc.outer.done.isConnected)
              //throw PIRException(s"Outer controller shouldn't connect inner most counter enable or outer most counter done signals ${cu}")
          //}
        //case cu:InnerController =>
          //cu.cchains.flatMap{ _.counters }.foreach { ctr =>
            //if (!ctr.en.isConnected) 
              //throw PIRException(s"${ctr}'s en in ${ctr.cchain} in ${ctr.ctrler} is not connected")
          //}
      //}
    //}
    super.finPass
    close
  }
}
