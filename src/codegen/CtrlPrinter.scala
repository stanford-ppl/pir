package pir.graph.traversal

import pir._
import pir.codegen.Printer
import pir.misc._
import pir.graph._

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Set
import scala.collection.mutable.Map
import scala.collection.mutable.HashMap
import java.io.File

class CtrlPrinter(implicit design: Design) extends Traversal with Printer {

  override val stream = newStream(Config.ctrlFile) 
  
  def emitCU(cu:Controller) = {
    val fields = s"(ancestors=${cu.ancestors})"
    emitBlock(s"${cu} $fields") {
      cu match {
        case cu:ComputeUnit =>
          cu.cchains.foreach { cc =>
            emitBlock(s"${cc} ${PIRPrinter.genFields(cc)}") {
              cc.counters.foreach { ctr =>
                emitln(s"${ctr} ${PIRPrinter.genFields(ctr)}")
              } 
            }
          }
        case top:Top =>
      }
      cu match {
        case mc:MemoryController =>
          val info = ListBuffer[String]()
          emitln(s"done=[${mc.done.to.mkString(",")}]")
        case cu:InnerController =>
          cu.mems.foreach { mem =>
            val info = ListBuffer[String]()
            mem match { case mem:FIFOOnWrite => info += s"notFull=[${mem.notFull.to.mkString(",")}], enqEn=${mem.enqueueEnable.from}"; case _ => }
            mem match { case mem:FIFOOnRead => info += s"notEmpty=[${mem.notEmpty.to.mkString(",")}], deqEn=${mem.dequeueEnable.from}"; case _ => }
            emitln(s"$mem(${info.mkString(",")})")
          }
        case cu:OuterController =>
        case cu:Top =>
      }
      emitBlock(s"CtrlBox(${PIRPrinter.genFields(cu.ctrlBox)})") {
        cu.ctrlBox.udcounters.foreach { case (ctrler, tb) =>
          val info = ListBuffer[String]()
          info += s"ctrler=${tb.ctrler}"
          info += s"inc=${tb.inc.from}"
          info += s"dec=${tb.dec.from}"
          info += s"init=${tb.init.from}"
          info += s"initVal=${tb.initVal}"
          info += s"out=[${tb.out.to.mkString(",")}]"
          emitln(s"${tb} (${info.mkString(",")})")
        }
        cu.ctrlBox.luts.foreach { lut =>
          val ins = lut.ins.map(_.from).mkString(",")
          val out = lut.out.to.mkString(",")
          emitln(s"${lut}${PIRPrinter.genFields(lut)} ins=[${ins}] outs=[${out}] transFunc=[${lut.transFunc.info}]")
        }
        cu.ctrlBox.andTrees.foreach { at =>
          emitln(s"$at(ins=[${at.ins.map(_.from).mkString(",")}] outs=[${at.out.to.mkString(",")}])")
        }
      }
      emitBlock(s"ctrlIns") {
        cu.ctrlIns.foreach{ in =>
          emitln(s"$in from:${in.from}")
        }
      }
      emitBlock(s"ctrlOuts") {
        cu.ctrlOuts.foreach{ out =>
          emitln(s"$out to:[${out.to.mkString(",")}]")
        }
      }
    }
  }
  override def traverse = {
    design.top.ctrlers.foreach { cu =>
      emitCU(cu)
    }
  }

  override def finPass = {
    close
    endInfo(s"Finishing Control Printing in ${getPath}")
  }

}
