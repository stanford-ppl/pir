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
    val cus = collectDown[GlobalContainer](design.newTop)
    val accessLowerHasRun = runner.prevHasRun[AccessLowering]
    cus.foreach { cu => 
      if (accessLowerHasRun) checkCUIO(cu)
    }
  }

  // All cu's inputs and outputs should go through a memory
  def checkCUIO(cu:GlobalContainer) = dbgblk(s"checkCUIO($cu)") {
    cu.ins.foreach { in =>
      dbg(s"in=${qtype(in.src)}${in}")
      in.src match {
        case node:LocalStore =>
        case node =>
          dbg(s"$cu's global input ${in.src}.$in")
          in.connected.foreach { out =>
            dbg(s"out=$out out.src=${out.src}")
          }
          err(s"$cu's global input ${in.src}.$in")
      }
    }
    cu.outs.foreach { out =>
      dbg(s"in=${qtype(out.src)}${out}")
      //out.src match {
        //case node:LocalStore =>
        //case node =>
          //dbg(s"$cu's global output $out.src = $node")
          //out.connected.foreach { in =>
            //dbg(s"in=$in in.src=${in.src}")
          //}
          //err(s"$cu's global output $out.src = $node")
      //}
    }
  }

}

