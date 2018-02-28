package pir.pass

import pir._
import pir.node._

import prism._
import prism.util._

import prism.traversal._

import scala.collection.mutable
import scala.reflect._

class IRCheck(implicit compiler:PIR) extends PIRPass {
  import pirmeta._

  type N = PIRNode with Product
  def shouldRun = true

  override def runPass(runner:RunPass[_]) =  {
    val prevRuns = runner.prevRuns
    prevRuns.foreach(_.pass.check(runner))
    val prePasses = prevRuns.map { _.pass }
    val cus = collectDown[GlobalContainer](compiler.top)
    val accessLowerHasRun = runner.session.hasRun[AccessLowering]
    val ctrlAllocHasRun = runner.session.hasRun[ControlAllocation] 
    cus.foreach { cu => 
      if (accessLowerHasRun) {
        if (!ctrlAllocHasRun) {
          checkCUIO[Input, LocalStore](cu)
        } else {
          checkCUIO[Input, GlobalInput](cu)
          checkCUIO[Output, GlobalOutput](cu)
        }
      }
    }
  }

  // All cu's inputs and outputs should go through a memory
  def checkCUIO[IOType<:IO:ClassTag, SrcType:ClassTag](cu:GlobalContainer) = dbgblk(s"checkCUIO($cu)") {
    cu.ios.foreach { 
      case io:IOType =>
        io.src match {
          case node:SrcType =>
          case node =>
            dbg(s"io=${qtype(io.src)}.${io}")
            io.connected.foreach { connected => 
              dbg(s"connected=${connected.src}.$connected")
            }
            err(s"$cu's global input ${io.src}.$io")
        }
      case io =>
    }
  }

}

