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
  } 

  /*
   * Use local copy if existed. Otherwise route the done
   * */
  def getDone(current:ComputeUnit, cchain:CounterChain) = {
    if (current.containsCopy(cchain)) {
      current.getCopy(cchain).outer.done
    } else {
      cchain.outer.done
    }
  }

  def getOuterMostCChain(cchains:Iterable[CounterChain]) = {
    cchains.minBy{_.ctrler.ancestors.size} // outer most CounterChain should have least ancesstors
  }

  def swapReadCtr(mem:MultiBuffering) = {
    mem.consumer match {
      case cu:MemoryPipeline if forRead(mem) => readCChainsOf(cu).last
      case cu:MemoryPipeline if forWrite(mem) => writeCChainsOf(cu).last
      case cu:ComputeUnit => cu.localCChain
      case top:Top => 
        throw PIRException(
          s"mem ($mem)'s consumer is top. Shouldn't be multibuffered buffering=${mem.buffering}")
    }
  }

  def swapWriteCtr(mem:MultiBuffering) = {
    mem.producer.asInstanceOf[ComputeUnit].localCChain
  }

  def connectMemoryControl(ctrler:Controller) = ctrler match {
    case cu:ComputeUnit =>
      cu.mems.foreach {
        case mem:MultiBuffering if mem.buffering > 1 =>
          val readCtr = swapReadCtr(mem)
          val writeCtr = swapWriteCtr(mem)
          mem.swapRead.connect(getDone(cu, readCtr))
          mem.swapWrite.connect(getDone(cu, writeCtr))
          dprintln(s"$mem in $ctrler")
          dprintln(s"- swapRead:${mem.swapRead.from}(orig=${readCtr} in ${readCtr.ctrler})")
          dprintln(s"- swapWrite:${mem.swapWrite.from}(orig=${writeCtr} in ${writeCtr.ctrler})")
        case mem:MultiBuffering => 
        case mem:ScalarFIFO =>
          mem.writer.ctrlBox match {
            case wtcb:InnerCtrlBox => mem.enqueueEnable.connect(wtcb.enable)
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
          tk.dec.connect(ccb.done)
          ccb.siblingAndTree.addInput(tk.out)
      }
    }
    // Token back
    val lasts = ctrler.children.filter{_.isLast}
    dprintln(s"$ctrler lasts:[${lasts.mkString(",")}]")
    lasts.foreach { last =>
      (ctrler, ctrler.ctrlBox, last.ctrlBox) match {
        case (parent:Top, pcb:TopCtrlBox, ccb:CtrlBox) =>
          pcb.status.connect(lasts.head.ctrlBox.done)
        case (parent:StreamController, pcb:OuterCtrlBox, ccb:StageCtrlBox) =>
          val tk = pcb.tokenBuffer(last)
          tk.inc.connect(ccb.enable)
          tk.dec.connect(pcb.childrenAndTree.out)
          pcb.childrenAndTree.addInput(tk.out)
        case (parent:Controller, pcb:OuterCtrlBox, ccb:CtrlBox) =>
          val tk = pcb.tokenBuffer(last)
          tk.inc.connect(last.ctrlBox.done)
          tk.dec.connect(pcb.done)
          pcb.childrenAndTree.addInput(tk.out)
      }
    }
  }

  def connectSibling(ctrler:Controller) = {
    // Forward dependency
    (ctrler, ctrler.ctrlBox) match {
      case (cu:MemoryPipeline, cb:MemCtrlBox) =>
        cu.sfifos.foreach { //vector fifo is handled in data path
          case fifo if (forRead(fifo)) =>cb.readFifoAndTree.addInput(fifo.notEmpty)
          case fifo if (forWrite(fifo)) => cb.writeFifoAndTree.addInput(fifo.notEmpty)
        }
      case (cu:ComputeUnit, cb:InnerCtrlBox) if isStreaming(cu) =>
        // FIFO.notEmpty
        cu.sfifos.foreach { fifo => //vector fifo is handled in data path
          cb.fifoAndTree.addInput(fifo.notEmpty)
        }
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
                tk.inc.connect(getDone(cu, swapWriteCtr(mem)))
              }
              tk.dec.connect(cb.done)
              cb.siblingAndTree.addInput(tk.out)
            case (mem, producer:Top) => // No synchronization needed
          }
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
              if (mem.swapRead.isConnected) {
                cd.inc.connect(mem.swapRead.from)
              } else {
                cd.inc.connect(getDone(cu, swapReadCtr(mem)))
              }
              cd.dec.connect(cb.done)
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
        readCChainsOf(ctrler).headOption.foreach { _.inner.en.connect(cb.readEnable) }
        writeCChainsOf(ctrler).headOption.foreach { _.inner.en.connect(cb.writeEnable) }
        chainCChain(readCChainsOf(ctrler))
        chainCChain(writeCChainsOf(ctrler))
      case (ctlrer:MemoryController, cb) =>
      case (ctrler:ComputeUnit, cb:StageCtrlBox) =>
        ctrler.localCChain.inner.en.connect(cb.enable)
        chainCChain(compCChainsOf(ctrler))
      case (ctrler, cb) =>
    }
    //ctrler match {
      //case ctrler:MemoryPipeline =>
      //case ctrler:InnerController =>
        //val cb = ctrler.ctrlBox
        //ctrler.localCChain.inner.en.connect(cb.enable)
        //ctrler.parent match {
          //case parent:StreamController =>
            //cb.enable.connect(cb.andTree.out)
          //case parent =>
            //cb.enable.connect(cb.siblingAndTree.out)
        //}
      //case ctrler:OuterController =>
        //val cb = ctrler.ctrlBox 
        //cb.enable.connect(cb.childrenAndTree)
      //case ctrler:Top =>
    //}
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
