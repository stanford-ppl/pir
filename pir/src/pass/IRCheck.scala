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
    runner.prevRuns.foreach(_.pass.check)
  }

}

