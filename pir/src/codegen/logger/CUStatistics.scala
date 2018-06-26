package pir
package codegen

import pir.node._
import pir.pass._
import spade.node._
import spade.param._

import prism.codegen.JsonCodegen

class CUStatistics(implicit compiler:PIR) extends PIRCodegen with JsonCodegen {

  val fileName = "stat.json"

  def postSplitting = compiler.session.hasRunAll[IgraphPartioner]
  def isLastRun = runner == compiler.session.runnersOf[CUStatistics].last

  override def dbg(s:Any) = {
    super.dbg(s)
    if (PIRConfig.printStat && isLastRun) {
      info(s"$s")
    }
  }

  def stat[T](list:Iterable[T])(lambda:T => Int ) = {
    val clist = list.map { e => lambda(e) }
    val avg = clist.sum.toFloat / list.size
    (clist.min, avg, clist.max)
  }

  def pct(nom:Int, den:Int) =  if (den == 0) 0 else nom * 100.0f / den
  def fstr(float:Float) = f"$float%.2f"

  val formatter = java.text.NumberFormat.getInstance

  override def runPass =  {
    if (postSplitting) {
      dbg(s"=========== Post-splitting CU Statistics ==================")
    } else {
      dbg(s"=========== Pre-splitting CU Statistics ==================")
    }
    val cus = compiler.top.collectDown[GlobalContainer]()
    cus.foreach(dump)
    val cuMap = cus.groupBy(cuType) // tp -> cus
    printStat(cuMap)
    printUsage(cuMap)
  }

  def printStat(cuMap:Map[Option[String], List[GlobalContainer]]) = {
    val cus = cuMap.values.flatten
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

  def printUsage(cuMap:Map[Option[String], List[GlobalContainer]]) = {
    def cmap(key:String) = cuMap.get(Some(key)).getOrElse(Nil)

    val cellsS = compiler.arch.top.collectDown[Routable]()
    val cusS = cellsS.filter { _.param.isInstanceOf[CUParam] }.size
    val pcusS = cellsS.filter { _.param.isInstanceOf[PCUParam] }.size
    val pmusS = cellsS.filter { _.param.isInstanceOf[PMUParam] }.size
    val mcsS = cellsS.filter { _.param.isInstanceOf[MCParam] }.size
    val pcusP = (cmap("pcu") ++ cmap("scu") ++ cmap("ocu")).size
    val pmusP = (cmap("pmu")).size
    val mcsP = (cmap("dfg") ++ cmap("sfg")).size
    val cusP = (cmap("pcu") ++ cmap("scu") ++ cmap("ocu") ++ cmap("dag")).size

    dbg(s"PCU usage = $pcusP / $pcusS (${fstr(pct(pcusP, pcusS))}%)")
    dbg(s"PMU usage = $pmusP / $pmusS (${fstr(pct(pmusP, pmusS))}%)")
    dbg(s"MC usage = $mcsP / $mcsS (${fstr(pct(mcsP, mcsS))}%)")
    dbg(s"Total usage = $cusP / $cusS (${fstr(pct(cusP, cusS))}%)")
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

