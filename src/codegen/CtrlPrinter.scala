package pir.codegen

import pir._
import pir.util._
import pir.util.misc._
import pir.graph._

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Set
import scala.collection.mutable.Map
import scala.collection.mutable.HashMap
import java.io.File

class CtrlPrinter(implicit design: Design) extends Codegen {
  def shouldRun = Config.debug && Config.ctrl

  override lazy val stream = newStream(Config.ctrlFile) 
  
  def emitCU(cu:Controller) = {
    val fields = s"(ancestors=${cu.ancestors})"
    emitBlock(s"${cu} $fields") {
      cu match {
        case cu:ComputeUnit =>
          cu.cchains.foreach { cc =>
            emitBlock(s"${cc} ${genFields(cc)}") {
              cc.counters.foreach { ctr =>
                emitln(s"${ctr} ${genFields(ctr)}")
              } 
            }
          }
          cu.mems.foreach { mem =>
            val info = ListBuffer[String]()
            mem match { case mem:FIFOOnWrite => info += s"notFull=[${mem.notFull.to.mkString(",")}], enqEn=${mem.enqueueEnable.from}"; case _ => }
            mem match { case mem:FIFOOnRead => info += s"notEmpty=[${mem.notEmpty.to.mkString(",")}], deqEn=${mem.dequeueEnable.from}"; case _ => }
            emitln(s"$mem(${info.mkString(",")})")
          }
        case top:Top =>
      }
      emitBlock(s"${cu.ctrlBox}(${genFields(cu.ctrlBox)})") {
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
        //cu.ctrlBox.luts.foreach { lut =>
          //val ins = lut.ins.map(_.from).mkString(",")
          //val out = lut.out.to.mkString(",")
          //emitln(s"${lut}${genFields(lut)} ins=[${ins}] outs=[${out}] transFunc=[${lut.transFunc.info}]")
        //}
        cu.ctrlBox.andTrees.foreach { at =>
          emitln(s"$at(ins=[${at.ins.map(_.from).mkString(",")}] outs=[${at.out.to.mkString(",")}])")
        }
        cu.ctrlBox match {
          case cb:MemCtrlBox =>
            emitln(s"${cb.writeDone.in} ${cb.writeDone.in.from}")
            emitln(s"${cb.writeDone.out} ${cb.writeDone.out.to}")
            emitln(s"${cb.readDone.in} ${cb.readDone.in.from}")
            emitln(s"${cb.readDone.out} ${cb.readDone.out.to}")
          case cb =>
        }
      }
      emitBlock(s"ctrlIns") {
        cu.cins.foreach{ in =>
          emitln(s"$in from:${in.from}")
        }
      }
      emitBlock(s"ctrlOuts") {
        cu.couts.foreach{ out =>
          emitln(s"$out to:[${out.to.mkString(",")}]")
        }
      }
    }
  }
  
  def genFields(n:Node):String = {
    val fields = ListBuffer[String]()
    fields ++= PIRPrinter.genFields(n)
    n match {
      case n:Counter => s"isInner=${n.isInner}"
      case n =>
    }
    fields.mkString(",")
  }

  addPass(canRun=design.ctrlAlloc.hasRun) {
    design.top.ctrlers.foreach { cu =>
      emitCU(cu)
    }
  }

  override def finPass = {
    close
    endInfo(s"Finishing Control Printing in ${getPath}")
  }

}
