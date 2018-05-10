package pir
package codegen

import pir.node._
import spade.node._

import prism.codegen.JsonCodegen

class CUStatistics(implicit compiler:PIR) extends PIRCodegen with JsonCodegen {

  val fileName = "stat.json"

  override def runPass =  {
    val cus = compiler.top.collectDown[GlobalContainer]()
    cus.foreach(dump)
    val cuMap = cus.groupBy(cuType)
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

  def dump(cu:GlobalContainer) = {
    val ios = cu.collectDown[GlobalIO]().toList
    val (ins,outs) = ios.partition(_.isInstanceOf[GlobalInput])
    val cins = ins.filter { io => isBit(io) }
    val sins = ins.filter { io => isWord(io) }
    val vins = ins.filter { io => isVector(io) }
    val couts = outs.filter { io => isBit(io) }
    val souts = outs.filter { io => isWord(io) }
    val vouts = outs.filter { io => isVector(io) }
    val stages = cu.collectDown[StageDef]()
    val reduction = stages.exists(isReduceOp)
    emitMap(cu){ implicit ms =>
      emitPair("tp", cuType(cu).get)
      emitPair("stages", stages.size)
      emitPair("hasReduce", reduction)
      emitPair("cins", cins.size)
      emitPair("sins", sins.size)
      emitPair("vins", vins.size)
      emitPair("couts", couts.size)
      emitPair("souts", souts.size)
      emitPair("vouts", vouts.size)
    }
  }

}

