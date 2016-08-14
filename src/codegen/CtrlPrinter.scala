package pir.graph.traversal

import pir._
import pir.codegen.Printer
import pir.PIRMisc._
import pir.plasticine.graph._

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Set
import scala.collection.mutable.Map
import scala.collection.mutable.HashMap
import java.io.File

class CtrlPrinter(implicit design: Design) extends Traversal with Printer {

  override val stream = newStream(Config.ctrlFile) 
  
  override def traverse = {
    emitBlock("OuterComputeUnit") {
      design.top.outerCUs.foreach { cu =>
        emitBlock(s"${cu}") {
          cu.cchains.foreach { cc =>
            emitBlock(s"${cc} ${PIRPrinter.genFields(cc)}") {
              cc.counters.foreach { ctr =>
                emitln(s"${ctr} ${PIRPrinter.genFields(ctr)}")
              } 
            }
          }
        }
      }
    }
    emitBlock("InnerComputeUnit") {
      design.top.innerCUs.foreach { cu =>
        emitBlock(s"${cu}") {
          cu.cchains.foreach { cc =>
            emitBlock(s"${cc} ${PIRPrinter.genFields(cc)}") {
              cc.counters.foreach { ctr =>
                emitln(s"${ctr} ${PIRPrinter.genFields(ctr)}")
              } 
            }
          }
        }
      }
    }
  }

  override def finPass = {
    close
    info(s"Finishing Control Printing in ${getPath}")
  }

}
