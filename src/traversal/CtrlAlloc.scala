package pir.graph.traversal
import pir.graph._
import pir._
import pir.misc._
import pir.graph.enums._
import pir.graph.mapper.PIRException

import scala.collection.mutable.Set
import scala.collection.mutable.Map
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Queue

class CtrlAlloc(implicit val design: Design) extends Traversal{

  override def traverse:Unit = {
    swapAlloc 
    ctrlAlloc
    enableRouting
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

  def swapAlloc = {
    design.top.compUnits.foreach { cu =>
      cu.mems.foreach {
        case mem:MultiBuffering if mem.buffering > 1 =>
          cu.ctrlBox.swapRead(mem, getDone(cu, mem.consumer.asInstanceOf[ComputeUnit].localCChain))
          cu.ctrlBox.swapWrite(mem, getDone(cu, mem.producer.asInstanceOf[ComputeUnit].localCChain))
        case mem =>
      }
    }
  }

  def ctrlAlloc = {
    design.top.ctrlers.foreach { cu =>
      cu match {
        case cu:MemoryPipeline =>  // handled ahead and after
        case cu:StreamPipeline =>
          val cb = cu.ctrlBox.asInstanceOf[StageCtrlBox]
          cu.parent match {
            case parent:StreamController =>
              if (cu.isHead) {
                //TODO
                val tk = cb.tokenBuffer(cu.parent)
                tk.inc.connect(parent.ctrler.ctrlBox.asInstanceOf[OuterCtrlBox].tokenDown)
                cb.siblingAndTree.addInput(tk.out)
              } else {
              }
          }
        case cu:ComputeUnit=>
          val cb = cu.ctrlBox.asInstanceOf[StageCtrlBox]
          val parent = cu.parent
          cu.consumed.foreach { mem =>
            mem.producer match {
              case cu:ComputeUnit =>
                val tk = cb.tokenBuffer(mem)
                tk.inc.connect(mem.ctrler.ctrlBox.swapWrite(mem))
                cb.siblingAndTree.addInput(tk.out)
              case top:Top => // No synchronization needed
            }
          }
          cu.produced.foreach { mem =>
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

  def enableRouting = {
    design.top.memCUs.foreach { cu =>
      cu.mem match {
        case mem:SRAMOnRead =>
          assert(mem.readers.size==1)
          cu.ctrlBox.readEn.connect(mem.readers.head.ctrlBox.enable.from)
        case mem:FIFOOnRead =>
          throw PIRException(s"Currently no support for MCU being FIFO")
      }
      cu.mem match {
        case mem:SRAMOnWrite =>
          cu.ctrlBox.writeEn.connect(mem.writer.ctrlBox.enable.from)
        case mem:FIFOOnWrite =>
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
    info("Finishing control logic allocation")
  }
}
