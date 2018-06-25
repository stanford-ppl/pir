package pir
package codegen

import pir.node._
import pir.pass._
import spade.node._

import prism.codegen.JsonCodegen

class CUStatistics(implicit compiler:PIR) extends PIRCodegen with JsonCodegen {

  val fileName = "stat.json"

  override def dbg(s:Any) = {
    super.dbg(s)
    if (PIRConfig.printStat) {
      info(s"$s")
    }
  }

  def stat[T](list:Iterable[T])(lambda:T => Int ) = {
    val clist = list.map { e => lambda(e) }
    val avg = clist.sum.toFloat / list.size
    (clist.min, avg, clist.max)
  }

  override def runPass =  {
    if (compiler.session.hasRunAll[IgraphPartioner]) {
      dbg(s"=========== Post-splitting CU Statistics ==================")
    } else {
      dbg(s"=========== Pre-splitting CU Statistics ==================")
    }
    val cus = compiler.top.collectDown[GlobalContainer]()
    cus.foreach(dump)
    val cuMap = cus.groupBy(cuType)
    dbg(s"")
    dbg(s"number of cus=${cus.size}")
    cuMap.foreach { case (cuType, cus) =>
      val key = cuType.getOrElse("cu")
      dbg(s"number of $key = ${cus.size}")
      dbg(s"- fanIn = ${stat(cus) { _.ins.size }}")
      dbg(s"- fanOut = ${stat(cus) { _.outs.size }}")
      dbg(s"- stages = ${stat(cus) { _.collectDown[StageDef]().size }}")
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

