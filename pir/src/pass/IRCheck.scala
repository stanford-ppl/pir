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

  def checkMemoryAccess = {
    val cus = collectDown[GlobalContainer](design.newTop)
    cus.foreach { cu =>
      val mems = collectDown[Memory](cu)
      mems.foreach { mem =>
        mem match {
          case mem:ArgIn =>
          case mem:StreamIn =>
          case mem if mem.writers.isEmpty =>
            warn(s"${qtype(mem)} in $cu does not have writer")
          case _ =>
        }
        mem match {
          case mem:ArgOut =>
          case mem:StreamOut =>
          case mem:StreamIn if mem.field == "ack" =>
          case mem if mem.readers.isEmpty =>
            warn(s"${qtype(mem)} in $cu does not have reader")
          case _ =>
        }
      }
    }
  }

  override def runPass(runner:RunPass) =  {
    if (!this.hasRun) checkMemoryAccess
    runner.prevRuns.foreach(_.pass.check)
  }

}

