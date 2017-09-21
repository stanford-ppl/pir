package pir.pass
import pir.node._
import pir._
import pir.util._
import pirc.exceptions._
import pirc.util._
import pir.codegen.Logger

import scala.collection.mutable._

class Optimizer(implicit design: PIR) extends Pass with Logger {
  def shouldRun = true
  import pirmeta._
  override lazy val stream = newStream(s"Optimizer.log")

  addPass(canRun=design.memoryAnalyzer.hasRun || !PIRConfig.ctrl) {
    // No longer need info by dummy CUs
    design.top.compUnits.foreach { cu =>
      if (cu.children.isEmpty && cu.stages.isEmpty && cu.mems.isEmpty && cu.sins.isEmpty && cu.vins.isEmpty) {
        dprintln(s"Find dummy CU $cu")
        design.removeNode(cu)
      }
    }
    dprintln(s"ctrlers:${design.top.ctrlers.mkString(",")}")
  } 

}
