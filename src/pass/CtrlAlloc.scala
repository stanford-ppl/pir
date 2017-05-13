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

  override def traverse:Unit = {
    design.top.ctrlers.foreach { ctrler =>
      connectDone(ctrler)
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

  /*
   * Use local copy if existed. Otherwise route the done
   * */
  def getDone(current:ComputeUnit, cchain:CounterChain) = {
    if (current.containsCopy(cchain)) {
      current.getCC(cchain).outer.done
    } else {
      cchain.ctrler.ctrlBox match {
        case cb:StageCtrlBox => cb.done.out
        case cb:MemCtrlBox if (readCChainsOf(cb.ctrler).last == cchain) => cb.readDone.out
        case cb:MemCtrlBox if (writeCChainsOf(cb.ctrler).last == cchain) => cb.writeDone.out
      }
    }
  }

  //def getOuterMostCChain(cchains:Iterable[CounterChain]) = {
    //cchains.minBy{_.ctrler.ancestors.size} // outer most CounterChain should have least ancesstors
  //}

  //def swapReadCC(mem:MultiBuffering) = {
    //mem.consumer match {
      //case cu:MemoryPipeline if forRead(mem) => 
        //readCChainsOf(cu).lastOption.getOrElse(cu.mem.consumer.asCU.localCChain)
      //case cu:MemoryPipeline if forWrite(mem) => 
        //writeCChainsOf(cu).lastOption.getOrElse(cu.mem.producer.asCU.localCChain)
      //case cu:ComputeUnit => cu.localCChain
      //case top:Top => 
        //throw PIRException(
          //s"mem ($mem)'s consumer is top. Shouldn't be multibuffered buffering=${mem.buffering}")
    //}
  //}

  def swapReadCC(mem:MultiBuffering) = {
    mem.consumer match {
      case cu:MemoryPipeline => readCChainsOf(cu).last
      case cu:ComputeUnit => cu.localCChain
    }
  }

  def swapWriteCC(mem:MultiBuffering) = {
    mem.producer match {
      case cu:MemoryPipeline => writeCChainsOf(cu).last
      case cu:ComputeUnit => cu.localCChain
    }
  }

  def connectMemoryControl(ctrler:Controller) = ctrler match {
    case cu:ComputeUnit =>
      cu.mems.foreach {
        case mem:SRAM if mem.buffering > 1 =>
          val cb = mem.ctrler.ctrlBox
          mem.swapRead.connect(cb.readDone.out)
          mem.swapWrite.connect(cb.writeDone.out)
        case mem:ScalarBuffer if mem.buffering > 1=> 
          ctrler.ctrlBox match {
            case cb:MemCtrlBox =>
              mem.swapRead.connect(cb.readDone.out)
              mem.swapWrite.connect(cb.writeDone.out)
            case cb:StageCtrlBox =>
              mem.readPort.to.foreach { _.src match {
                  case ctr:Counter if ctr.cchain.outer.done == cb.done.in.from =>
                    mem.swapRead.connect(cb.done.out)
                    //throw new Exception(s"Unhandled case in hardware!") 
                    // If PCU make copy of ancesstors's counter, in which the counter's bounds are
                    // read from ScalarBuffer, the swapRead has to come from tokenIn but not
                    // local done
                  case ctr:Counter if cu.containsCopy(ctr.cchain) =>
                    assert(false) //TODO: add ctr.done and tokenIn to readPtrInc connection
                    mem.swapRead.connect(getDone(cu, ctr.cchain))
                  case _ => // Connect to local done
                    mem.swapRead.connect(cb.done.out)
                }
              }
              mem.swapWrite.connect(getDone(cu, swapWriteCC(mem)))
          }
        case mem:MultiBuffering => 
        case mem:ScalarFIFO =>
          cu match {
            case cu:MemoryController =>
              mem.writer.ctrlBox match {
                case wtcb:InnerCtrlBox => mem.enqueueEnable.connect(wtcb.en.out)
              }
            case cu:ComputeUnit =>
              // ScalarFIFO enable has to come from tokenIn
              mem.writer.ctrlBox match {
                case wtcb:InnerCtrlBox => mem.enqueueEnable.connect(wtcb.en.out)
              }
          }
          // deqEnable is mapped in CtrlMapper
        case mem:VectorFIFO =>
          // vectorFIFO.enqueueEnable routes through data bus 
          // deqEnable is mapped in CtrlMapper
      }
    case _ =>
  }

  def connectLast(parent:Controller, last:Controller) = {
    (parent, last, parent.ctrlBox, last.ctrlBox) match {
      case (parent:Top, last:ComputeUnit, pcb:TopCtrlBox, ccb:StageCtrlBox) =>
        pcb.status.connect(ccb.done.out)
      case (parent:StreamController, last:MemoryController, pcb:OuterCtrlBox, ccb:MCCtrlBox) =>
        val tk = pcb.tokenBuffer(last)
        tk.inc.connect(ccb.done)
        tk.dec.connect(pcb.childrenAndTree.out)
        pcb.childrenAndTree.addInput(tk.out)
      case (parent:StreamController, last:Pipeline, pcb:OuterCtrlBox, ccb:StageCtrlBox) =>
        val tk = pcb.tokenBuffer(last)
        tk.inc.connect(ccb.en.out)
        tk.dec.connect(pcb.childrenAndTree.out)
        pcb.childrenAndTree.addInput(tk.out)
      case (parent:Controller, last:ComputeUnit, pcb:OuterCtrlBox, ccb:StageCtrlBox) =>
        val tk = pcb.tokenBuffer(last)
        tk.inc.connect(ccb.done.out)
        tk.dec.connect(pcb.done.out)
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
        case (pcb:OuterCtrlBox, ccb:InnerCtrlBox) if isStreaming(head) =>
          ccb.tokenInAndTree.addInput(pcb.tokenDown)
        case (pcb:OuterCtrlBox, ccb:OuterCtrlBox) if isStreaming(head) =>
        case (pcb:OuterCtrlBox, ccb:StageCtrlBox) if isPipelining(head) =>
          val tk = ccb.tokenBuffer(ctrler)
          tk.inc.connect(pcb.tokenDown)
          tk.dec.connect(ccb.done.out)
          ccb.siblingAndTree.addInput(tk.out)
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
    if (midConsumers.size>1) connectTokens(consumer, midConsumers.map(cm => (cm, cm.ctrlBox.asInstanceOf[StageCtrlBox].done.out)))
  }

  //def propogateXbar(cb:CtrlBox, swapCtrl:CtrlInPort) = {
    //cb match {
      //case cb if swapCtrl.isCtrlIn => swapCtrl.from
      //case cb:MemCtrlBox =>
        //if (swapCtrl.from==cb.readDone.out) {
          //cb.readDone.out
        //} else if (swapCtrl.from==cb.writeDone.out) {
          //cb.writeDone.out
        //} else {
          //throw PIRException(s"cb:${cb} swapCtrl is not readDone or writeDone, ${swapCtrl}.from=${swapCtrl.from}")
        //}
      //case cb:StageCtrlBox =>
        //if (swapCtrl.from==cb.done.out) {
          //cb.done.out
        //} else {
          //throw PIRException(s"cb:${cb} swapCtrl is not done, ${swapCtrl}.from=${swapCtrl.from}")
        //}
    //}
  //}

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
                if (mem.swapWrite.isConnected) {
                  Some(mem, mem.swapWrite.from.asCtrl)
                } else {
                  Some(mem, getDone(cu, swapWriteCC(mem)))
                }
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
                case cmcb:StageCtrlBox => cmcb.done.out
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
        ctrler.localCChain.inner.en.connect(cb.en.out)
      case (ctrler:InnerController, cb:InnerCtrlBox) =>
        ctrler.localCChain.inner.en.connect(cb.en.out)
        chainCChain(compCChainsOf(ctrler))
      case (ctrler:OuterController, cb:OuterCtrlBox) =>
        ctrler.localCChain.inner.en.connect(cb.en.out)
      case (ctrler, cb) =>
    }
    ctrler.ctrlBox match {
      case cb:MemCtrlBox =>
        cb.readEn.in.connect(cb.readFifoAndTree.out)
        cb.writeEn.in.connect(cb.writeFifoAndTree.out)
      case cb:OuterCtrlBox =>
        cb.en.in.connect(cb.childrenAndTree.out)
      case cb:InnerCtrlBox if isPipelining(ctrler) =>
        cb.en.in.connect(cb.siblingAndTree.out)
      case cb:InnerCtrlBox if isStreaming(ctrler) =>
        cb.en.in.connect(cb.andTree.out)
      case cb:MCCtrlBox =>
    }
  }

  def connectDone(ctrler:Controller) = {
    (ctrler, ctrler.ctrlBox) match {
      case (ctrler:MemoryPipeline, cb:MemCtrlBox) =>
        val readDone = getDone(ctrler, swapReadCC(ctrler.mem))
        cb.readDone.in.connect(readDone)
        val writeDone = getDone(ctrler, swapWriteCC(ctrler.mem))
        cb.writeDone.in.connect(writeDone)
      case (ctlrer:MemoryController, cb) =>
      case (ctrler:ComputeUnit, cb:StageCtrlBox) =>
        cb.done.in.connect(ctrler.localCChain.outer.done)
      case (ctrler, cb) =>
    }
  }

  override def finPass = {
    design.top.compUnits.foreach {
      case cu:MemoryController =>
      case cu =>
        assert(cu.cchains.nonEmpty, s"$cu's cchain is empty")
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
