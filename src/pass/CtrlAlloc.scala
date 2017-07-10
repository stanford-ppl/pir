package pir.pass
import pir.graph._
import pir._
import pir.util.enums._
import pir.codegen.Logger
import pir.exceptions._
import pir.util.misc._
import pir.util._
import pir.plasticine.util._

import scala.collection.mutable.Set
import scala.collection.mutable.Map
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Queue

class CtrlAlloc(implicit design: Design) extends Pass with Logger {
  import pirmeta._

  def shouldRun = Config.ctrl

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
      connectChildren(ctrler)
    }
  }

  def setPredicate(ctrler:Controller) = {
    setAccumPredicate(ctrler)
    //setFifoPredicate(ctrler)
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
    cu.mbuffers.foreach {
      case mem if mem.buffering > 1 =>
        mem.swapRead.connect(getDone(cu, swapReadCChainOf(mem)))
        mem.swapWrite.connect(getDone(cu, swapWriteCChainOf(mem)))
      case mem =>
    }
    //cu.sfifos.foreach { mem =>
      //mem.writer.ctrlBox match {
        //case cb:StageCtrlBox =>
          //mem.enqueueEnable.connect(cb.enOut)
        //case cb:MemCtrlBox =>
          //mem.enqueueEnable.connect(cb.enOut)
      //}
    //}
    cu.fifos.foreach { mem =>
      cu.ctrlBox match {
        case cb:MemCtrlBox if mem.isVFifo =>
          //mem.dequeueEnable.connect(cb.writeDelay.out)
        case cb:MemCtrlBox if forRead(mem) =>
          mem.dequeueEnable.connect(cb.readEn.out)
        case cb:MemCtrlBox if forWrite(mem) =>
          mem.dequeueEnable.connect(cb.writeEn.out)
        case cb:MCCtrlBox if mem.name.get=="data" =>
          //mem.dequeueEnable.connect(cb.running)
        case cb:StageCtrlBox =>
          mem.dequeueEnable.connect(cb.en.out)
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
    dprintln(s"$OCU_MAX_CIN ${parent.cins}") //TODO fix this for smv
    val lastGroups = if (lasts.size==0) Nil else lasts.grouped(OCU_MAX_CIN - parent.cins.size).toList
    val midParents = lastGroups.map { lasts =>
      val midParent = if (lastGroups.size==1) parent else {
        dprintln(s"parent=$parent lastGroups:$lastGroups")
        val clone = parent.cloneType("collector")
        isHead(clone) = false 
        isLast(clone) = true
        isStreaming(clone) = isStreaming(lasts.head)
        isPipelining(clone) = isPipelining(lasts.head)
        isTailCollector(clone) = true
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

  def connectHeads(ctrler:Controller, heads:List[Controller]) = {
    dprintln(s"$ctrler heads:[${heads.mkString(",")}]")
    heads.foreach { head =>
      (ctrler.ctrlBox, head.ctrlBox) match {
        case (pcb:OuterCtrlBox, hcb:InnerCtrlBox) if isStreaming(head) =>
          hcb.tokenInAndTree.addInput(pcb.enOut)
        case (pcb:OuterCtrlBox, hcb:OuterCtrlBox) if isStreaming(head) =>
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

  def connectChildren(ctrler:Controller) = {
    // Token down
    val heads = ctrler.children.filter{_.isHead}
    connectHeads(ctrler, heads)
    // Token back
    val lasts = ctrler.children.filter{_.isLast}
    connectLasts(ctrler, lasts)
    //emitBlock(s"$ctrler.children") {
      //topoSort(ctrler).foreach { ctrler =>
        //dprintln(s"$ctrler")
      //}
    //}
  }

  def connectToken(consumer:Controller, tk:(Any, CtrlOutPort)):Unit = {
    val cb = consumer.ctrlBox.asInstanceOf[StageCtrlBox]
    val (dep, token) = tk
    val tb = cb.tokenBuffer(dep)
    tb.inc.connect(token)
    tb.dec.connect(cb.done.out)
    cb.siblingAndTree.addInput(tb.out)
  }

  def connectTokens(consumer:Controller, tokens:List[(Any, CtrlOutPort)]):Unit = {
    val tokenGroups = if (tokens.size==0) Nil else tokens.grouped(OCU_MAX_CIN - consumer.cins.size).toList
    val midConsumers = tokenGroups.map { tokens =>
      val midConsumer = if (tokenGroups.size==1) consumer else {
        val clone = consumer.cloneType("splitter")
        isHead(clone) = false
        isLast(clone) = false
        isStreaming(clone) = isStreaming(consumer)
        isPipelining(clone) = isPipelining(consumer)
        isHeadSplitter(clone) = true
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
    (ctrler, ctrler.ctrlBox) match {
      case (cu:MemoryPipeline, cb:MemCtrlBox) =>
        cu.fifos.foreach {
          case fifo if (forRead(fifo)) => cb.readFifoAndTree.addInput(fifo.notEmpty)
          case fifo if (forWrite(fifo)) => cb.writeFifoAndTree.addInput(fifo.notEmpty)
        }
        case (cu:ComputeUnit, cb:InnerCtrlBox) if isStreaming(cu) =>
          // FIFO.notEmpty
          cu.fifos.foreach { fifo => cb.fifoAndTree.addInput(fifo.notEmpty) }
        case (cu:ComputeUnit, cb:OuterCtrlBox) if isStreaming(cu) =>
        case (cu:ComputeUnit, cb:StageCtrlBox) if isPipelining(cu) =>
          // Token
          val tokens = cu.trueConsumed.flatMap { mem =>
            (mem, mem.producer) match {
              case (mem:SRAM, producer:ComputeUnit) => None
                // SRAM no need for token because handled by FIFONotEmpty
              case (mem, producer:ComputeUnit) =>
                Some(mem, getDone(cu, swapWriteCChainOf(mem))) // should always from remote
              case (mem, producer:Top) => None // No synchronization needed
            }
          }
          connectTokens(cu, tokens)
          cb match {
            case cb:InnerCtrlBox =>
              cu.fifos.foreach { fifo => cb.fifoAndTree.addInput(fifo.notEmpty) }
            case _ =>
          }
      case (cu:MemoryController, cb:MCCtrlBox) =>
          cu.fifos.foreach { fifo => cb.fifoAndTree.addInput(fifo.notEmpty) }
      case (cu:Top, cb:TopCtrlBox) =>
    }
    // Backward pressure
    (ctrler, ctrler.ctrlBox) match {
      case (cu:InnerController, cb:InnerCtrlBox) if isStreaming(cu) =>
        // FIFO.notFull
        //dprintln(s"$cu writtenFIFOs:[${cu.writtenFIFOs.mkString(",")}]")
        cu.writtenSFIFOs.foreach { fifo => cb.tokenInAndTree.addInput(fifo.notFull) }
      case (cu:ComputeUnit, cb:StageCtrlBox) if isPipelining(cu) => 
        // Credit
        cu.trueProduced.foreach { mem =>
          mem.consumer match {
            case consumer:ComputeUnit if mem.buffering != 1 & mem.buffering < cu.parent.length =>
              val inc = consumer.ctrlBox match {
                case cmcb:MemCtrlBox if forRead(mem) => cmcb.readDone.out
                case cmcb:MemCtrlBox if forWrite(mem) => cmcb.readDone.out
                case cmcb:StageCtrlBox => cmcb.doneOut
              }
              val cd = cb.creditBuffer(mem, mem.buffering)
              cd.inc.connect(inc)
              cd.dec.connect(cb.done.out)
              cb.siblingAndTree.addInput(cd.out)
            case consumer =>
          }
        }
      case (cu, cb) =>
    }
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
    ctrler.ctrlBox match {
      case cb:MemCtrlBox =>
        //cb.readEn.in.connect(cb.readFifoAndTree.out)
        //cb.writeEn.in.connect(cb.writeFifoAndTree.out)
      case cb:OuterCtrlBox =>
        //cb.en.in.connect(cb.childrenAndTree.out)
      case cb:InnerCtrlBox if isPipelining(ctrler) =>
        cb.en.in.connect(cb.pipeAndTree.out)
      case cb:InnerCtrlBox if isStreaming(ctrler) =>
        cb.en.in.connect(cb.streamAndTree.out)
      case cb:MCCtrlBox =>
        //cb.en.in.connect(cb.fifoAndTree.out)
      case cb:TopCtrlBox =>
    }
  }

  def connectDone(ctrler:Controller) = {
    (ctrler, ctrler.ctrlBox) match {
      case (ctrler:MemoryPipeline, cb:MemCtrlBox) =>
        val readDone = ctrler.getCC(swapReadCChainOf(ctrler.mem)).outer.done
        cb.readDone.in.connect(readDone)
        val writeDone = ctrler.getCC(swapWriteCChainOf(ctrler.mem)).outer.done
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
