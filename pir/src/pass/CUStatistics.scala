package pir.pass

import pir._
import pir.node._

import pirc._
import pirc.util._

import scala.collection.mutable
import scala.reflect._


class CUStatistics(implicit design:PIR) extends PIRPass {

  type N = Node with Product
  type T = Unit

  def shouldRun = true

  override def dbg(s:Any):Unit = {
    info(s"$s")
    super.dbg(s)
  }

  override def runPass =  {
    val cus = collectDown[GlobalContainer](design.newTop)
    val cuMap = cus.groupBy {
      case cu if collectDown[SRAM](cu).nonEmpty => "pmus"
      case cu:ArgFringe => "argFringe"
      case cu:FringeContainer => "fringes"
      case cu if collectDown[StageDef](cu).nonEmpty => "pcus"
      case cu => "ocus"
    }
    dbg(s"number of cus=${cus.size}")
    cuMap.foreach { case (key, cus) =>
      dbg(s"")
      dbg(s"number of $key = ${cus.size}")
      //dbg(s"$key = ${cus.map(qtype)}")
      val fanIns = cus.map { cu => cu.ins.size }
      dbg(s"max fanIn of $key = ${fanIns.max}")
      dbg(s"average fanIn of $key = ${fanIns.sum.toFloat / fanIns.size}")
      val fanOuts = cus.map { cu => cu.outs.size }
      dbg(s"max fanOut of $key = ${fanOuts.max}")
      dbg(s"average fanOut of $key = ${fanOuts.sum.toFloat / fanOuts.size}")
    }
  }

}

