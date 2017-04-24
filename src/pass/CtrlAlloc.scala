package pir.pass
import pir.graph._
import pir._
import pir.util.enums._
import pir.codegen.Logger
import pir.exceptions._
import pir.util.misc._
import pir.util._

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
      connectEnable(ctrler)
      connectMemoryControl(ctrler)
    }
    design.top.ctrlers.foreach { ctrler =>
      connectChildren(ctrler)
      connectSibling(ctrler)
    }
    design.top.ctrlers.foreach { ctrler =>
      connectDone(ctrler)
    }
  } 

  /*
   * Use local copy if existed. Otherwise route the done
   * */
  def getDone(current:ComputeUnit, cchain:CounterChain) = {
    if (current.containsCopy(cchain)) {
      current.getCopy(cchain).outer.done
    } else {
      cchain.ctrler.ctrlBox match {
        case cb:StageCtrlBox => cb.done.out
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

  def swapWriteCC(mem:MultiBuffering) = {
    mem.producer.asInstanceOf[ComputeUnit].localCChain
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
                  case ctr:Counter if ctr.cchain.isCopy =>
                    throw new Exception(s"Unhandled case in hardware!") 
                    // If PCU make copy of ancesstors's counter, in which the counter's bounds are
                    // read from ScalarBuffer, the swapRead has to come from tokenIn but not
                    // local done
                  case _ =>
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
              throw new Exception(s"Unhandled case in hardware!") 
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

  def connectChildren(ctrler:Controller) = {
    // Token down
    val heads = ctrler.children.filter{_.isHead}
    dprintln(s"$ctrler heads:[${heads.mkString(",")}]")
    heads.foreach { head =>
      (ctrler.ctrlBox, head.ctrlBox) match {
        case (pcb:OuterCtrlBox, ccb:InnerCtrlBox) if isStreaming(head) =>
          ccb.tokenInAndTree.addInput(pcb.tokenDown)
        case (pcb:OuterCtrlBox, ccb:StageCtrlBox) if isPipelining(head) =>
          val tk = ccb.tokenBuffer(ctrler)
          tk.inc.connect(pcb.tokenDown)
          tk.dec.connect(ccb.done.out)
          ccb.siblingAndTree.addInput(tk.out)
      }
    }
    // Token back
    val lasts = ctrler.children.filter{_.isLast}
    dprintln(s"$ctrler lasts:[${lasts.mkString(",")}]")
    lasts.foreach { last =>
      (ctrler, ctrler.ctrlBox, last.ctrlBox) match {
        case (parent:Top, pcb:TopCtrlBox, ccb:StageCtrlBox) =>
          pcb.status.connect(ccb.done.out)
        case (parent:StreamController, pcb:OuterCtrlBox, ccb:StageCtrlBox) =>
          val tk = pcb.tokenBuffer(last)
          tk.inc.connect(ccb.en.out)
          tk.dec.connect(pcb.childrenAndTree.out)
          pcb.childrenAndTree.addInput(tk.out)
        case (parent:Controller, pcb:OuterCtrlBox, ccb:StageCtrlBox) =>
          val tk = pcb.tokenBuffer(last)
          tk.inc.connect(ccb.done.out)
          tk.dec.connect(pcb.done.out)
          pcb.childrenAndTree.addInput(tk.out)
      }
    }
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
          cu.trueConsumed.foreach { mem =>
            (mem, mem.producer) match {
              case (mem:SRAM, producer:ComputeUnit) => 
                // SRAM no need for token because handled by FIFONotEmpty
              case (mem, producer:ComputeUnit) =>
                val tk = cb.tokenBuffer(mem)
                if (mem.swapWrite.isConnected) {
                  tk.inc.connect(mem.swapWrite.from)
                } else {
                  tk.inc.connect(getDone(cu, swapWriteCC(mem)))
                }
                tk.dec.connect(cb.done.out)
                cb.siblingAndTree.addInput(tk.out)
              case (mem, producer:Top) => // No synchronization needed
            }
          }
          cb match {
            case cb:InnerCtrlBox =>
              cu.fifos.foreach { fifo => cb.fifoAndTree.addInput(fifo.notEmpty) }
            case _ =>
          }
      case (cu:Top, cb) =>
    }
    // Backward pressure
    (ctrler, ctrler.ctrlBox) match {
      case (cu:ComputeUnit, cb:StageCtrlBox) if isStreaming(cu) =>
        // FIFO.notFull
        //dprintln(s"$cu writtenFIFOs:[${cu.writtenFIFOs.mkString(",")}]")
        //cu.writtenSFIFOs.foreach { fifo =>
        //cb.tokenInAndTree.addInput(fifo.notFull)
        //}
      case (cu:ComputeUnit, cb:StageCtrlBox) if isPipelining(cu) => 
        // Credit
        cu.trueProduced.foreach { mem =>
          mem.consumer match {
            case consumer:ComputeUnit if mem.buffering != 1 & mem.buffering < cu.parent.length =>
              val cd = cb.creditBuffer(mem, mem.buffering)
              cd.inc.connect(mem.swapRead.from)
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
      case (ctrler:ComputeUnit, cb:StageCtrlBox) =>
        ctrler.localCChain.inner.en.connect(cb.en.out)
        chainCChain(compCChainsOf(ctrler))
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
    }
  }

  def connectDone(ctrler:Controller) = {
    (ctrler, ctrler.ctrlBox) match {
      case (ctrler:MemoryPipeline, cb:MemCtrlBox) =>
        val readDone = getDone(ctrler, ctrler.mem.consumer.asInstanceOf[ComputeUnit].localCChain)
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
