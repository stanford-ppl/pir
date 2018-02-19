package pir.pass

import pir._
import pir.node._

import pirc._
import pirc.util._

import prism.traversal._

import scala.collection.mutable
import scala.reflect._

class IRCheck(implicit design:PIR) extends PIRPass {
  import pirmeta._

  type N = PIRNode with Product
  def shouldRun = true

  override def runPass(runner:RunPass) =  {
    val prevRuns = runner.prevRuns
    prevRuns.foreach(_.pass.check(runner))
    val prePasses = prevRuns.map { _.pass }
    val cus = collectDown[GlobalContainer](design.top)
    val accessLowerHasRun = runner.prevHasRun[AccessLowering]
    val ctrlAllocHasRun = runner.prevHasRun[ControlAllocation] 
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
            dbg(s"io=${qtype(io.src)}${io}")
            io.connected.foreach { out => dbg(s"out=$out out.src=${out.src}") }
            err(s"$cu's global input ${io.src}.$io")
        }
      case io =>
    }
  }

}

