package pir.graph.traversal
import pir.graph._
import pir._
import pir.misc._
import pir.util.enums._
import pir.util._
import pir.codegen.Printer
import pir.util._

import scala.collection.mutable.Set
import scala.collection.mutable.Map
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Queue

class CtrlAlloc(implicit val design: Design) extends Traversal with Printer {

  override val stream = newStream(s"CtrlAlloc.txt")

  override def traverse:Unit = {
    swapAlloc 
    ctrlAlloc
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

  def swapAlloc = {
    design.top.compUnits.foreach { 
      case cu =>
        cu.mems.foreach {
          case mem:MultiBuffering if mem.buffering > 1 =>
            val swapReadDone = mem.consumer match {
              case ccu:MemoryPipeline if (mem.isInstanceOf[ScalarMem]) => // Check used in read or write addr calculation
                val cchains = mem.readPort.to.map{_.src}.collect{ case c:Counter => c.cchain }.toSet
                emitln(s"$mem consumer:${mem.consumer}, mem.readPort(${mem.readPort.to.mkString(",")}), cchains:${cchains.mkString(",")}")
                getOuterMostCChain(cchains)
              case cu:ComputeUnit => cu.localCChain
              case top:Top => throw PIRException(s"mem ($mem)'s consumer is top. Shouldn't be multibuffered buffering=${mem.buffering}")
            }
            cu.ctrlBox.swapRead(mem, getDone(cu, swapReadDone))
            cu.ctrlBox.swapWrite(mem, getDone(cu, mem.producer.asInstanceOf[ComputeUnit].localCChain))
          case mem =>
        }
    }
  }

  def ctrlAlloc = {
    design.top.ctrlers.foreach { cu =>
      cu match {
        case cu:MemoryPipeline =>  // handled ahead and after
        case mc:MemoryController =>
          mc.getCopy(mc.parent.localCChain).inner.en.connect(mc.done)
          if (!mc.isHead) {
            mc.fifos.foreach {
              case sf:ScalarFIFO => sf.enqueueEnable.connect(sf.writer.ctrlBox.asInstanceOf[InnerCtrlBox].enableOut)
              case vf:VectorFIFO =>
            }
          }
        case cu:StreamPipeline =>
          val cb = cu.ctrlBox
          cu.parent match {
            case parent:StreamController =>
              if (cu.isHead) {
                val td = parent.ctrler.ctrlBox.asInstanceOf[OuterCtrlBox].tokenDown
                cb.enable.connect(td)
              } else {
                cb.enable.connect(cb.andTree.out)
                cu.fifos.foreach {
                  case sf:ScalarFIFO => sf.enqueueEnable.connect(sf.writer.ctrlBox.asInstanceOf[InnerCtrlBox].enableOut)
                  case vf:VectorFIFO =>
                }
              }
          }
        case cu:ComputeUnit=>
          val cb = cu.ctrlBox.asInstanceOf[StageCtrlBox]
          val parent = cu.parent
          //println(cu, cu.trueConsumed.map{ m => (m, m.ctrler) })
          cu.trueConsumed.foreach { mem =>
            mem.buffering match {
              case 1 if parent.isInstanceOf[Sequential] | parent.isInstanceOf[Top] =>
              case n if parent.isInstanceOf[MetaPipeline] =>
                if (n < parent.length) {  // less then number of tokens
                  mem.producer match {
                    case cu:ComputeUnit =>
                      val tk = cb.tokenBuffer(mem)
                      tk.inc.connect(mem.ctrler.ctrlBox.swapWrite(mem))
                      cb.siblingAndTree.addInput(tk.out)
                    case top:Top => // No synchronization needed
                  }
                }
            }
            mem match {
              case mem:SRAMOnRead =>
                mem.ctrler.ctrlBox.asInstanceOf[MemCtrlBox].readEn.connect(cb.siblingAndTree.out)
              case _ =>
            }
          }
          cu.trueProduced.foreach { mem =>
            mem.buffering match {
              case 1 if parent.isInstanceOf[Sequential] | parent.isInstanceOf[Top] =>
              case n if parent.isInstanceOf[MetaPipeline] =>
                if (n < parent.length) {  // less then number of tokens
                  mem.consumer match {
                    case cu:ComputeUnit =>
                      mem match {
                        case mem:ScalarBuffer if (false) => // TODO Check scalar hardware buffer size?
                        case mem:OnChipMem =>
                          val cd = cb.creditBuffer(mem, n)
                          cd.inc.connect(mem.ctrler.ctrlBox.swapRead(mem))
                          cb.siblingAndTree.addInput(cd.out)
                      }
                    case top:Top => // No synchronization needed
                  }
                }
              case n =>
                throw PIRException(s"$cu's parent is ${parent} but produced mem:$mem is $n")
            }
            mem match {
              case mem:SRAMOnWrite => // TODO: writeEn is from databus
              case _ =>
            }
          }
          if (cu.isHead) {
            val tk = cb.tokenBuffer(cu.parent)
            tk.inc.connect(parent.ctrler.ctrlBox.asInstanceOf[OuterCtrlBox].tokenDown)
            cb.siblingAndTree.addInput(tk.out)
          }
          cb.enable.connect(cb.siblingAndTree.out)
        case top:Top =>
      }
      cu match {
        case cu@(_:OuterController | _:Top) =>
          val cb = cu.ctrlBox.asInstanceOf[OuterCtrlBox]
          val lasts = cu.children.filter{_.isLast}
          lasts.foreach { last =>
            val tk = cb.tokenBuffer(last)
            tk.inc.connect(last.ctrlBox.done)
            cb.childrenAndTree.addInput(tk.out)
          }
        case _ =>
      }
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
    endInfo("Finishing control logic allocation")
    close
  }
}
