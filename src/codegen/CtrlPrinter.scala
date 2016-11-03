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
  
  def emitCU(cu:ComputeUnit)(block: => Any) = {
    val fields = cu match {
      case cu:InnerController => s"(ancestors=${cu.ancestors})"
      case cu:OuterController => s""
    }
    emitBlock(s"${cu} $fields") {
      cu.cchains.foreach { cc =>
        emitBlock(s"${cc} ${PIRPrinter.genFields(cc)}") {
          cc.counters.foreach { ctr =>
            emitln(s"${ctr} ${PIRPrinter.genFields(ctr)}")
          } 
        }
      }
      val (tins, touts) = cu match {
        case inner:InnerController =>
          (s"tokenIns=[${inner.ctrlIns.map(_.from).mkString(",")}]", s"tokenOuts=[${inner.ctrlOuts.mkString(",")}]")
        case outer:OuterController => 
          (s"tokenIns=[${outer.ctrlBox.ctrlIns.map(_.from).mkString(",")}]", 
            s"tokenOuts=[${outer.ctrlBox.ctrlOuts.mkString(",")}]")
      }
      emitBlock(s"CtrlBox(${PIRPrinter.genFields(cu.ctrlBox)}) ${tins} ${touts}") {
        cu.ctrlBox.udcounters.foreach { case (ctrler, tb) =>
          val info = ListBuffer[String]()
          info += s"ctrler=${tb.ctrler}"
          info += s"inc=${tb.inc.from}"
          info += s"dec=${tb.dec.from}"
          info += s"init=${tb.init.from}"
          info += s"out=[${tb.out.to.mkString(",")}]"
          emitln(s"${tb} (${info.mkString(",")})")
        }
        cu.ctrlBox.luts.foreach { lut =>
          val ins = lut.ins.map(_.from).mkString(",")
          val out = lut.out.to.mkString(",")
          emitln(s"${lut}${PIRPrinter.genFields(lut)} ins=[${ins}] outs=[${out}]")
        }
      }
      block
    }
  }
  override def traverse = {
    design.top.innerCUs.foreach { cu =>
      def block = {
        emitBlock(s"outerCUs") {
          cu.outers.foreach { outer =>
            emitCU(outer){}
          }
        }
      }
      emitCU(cu)(block)
    }
  }

  override def finPass = {
    close
    info(s"Finishing Control Printing in ${getPath}")
  }

}
