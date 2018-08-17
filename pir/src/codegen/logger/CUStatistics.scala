package pir
package codegen

import pir.node._
import pir.pass._
import pir.mapper._
import spade.node._
import spade.param._

import prism.codegen.JsonCodegen

class CUStatistics(implicit compiler:PIR) extends PIRCodegen with JsonCodegen with TypeUtil {
  import pirmeta._
  import PIRConfig._

  val fileName = "stat.json"

  def isLastRun = runner == compiler.session.runnersOf[CUStatistics].last
  def postSplitting = compiler.session.hasRunAll[IgraphPartioner]
  def postMapping = compiler.session.hasRunAll[CUPlacer]

  def sinfo(s:Any) = {
    dbg(s)
    if (printStat && isLastRun) {
      info(s"$s")
    }
  }

  def stat[T](list:Iterable[T])(lambda:T => Int ) = {
    val clist = list.map { e => lambda(e) }
    val avg = clist.sum.toFloat / list.size
    (fstr(clist.min), fstr(avg), fstr(clist.max))
  }

  def pct(nom:Int, den:Int) =  if (den == 0) 0 else nom * 100.0f / den
  def fstr(float:Float) = f"$float%.2f"
  def pctstr(a:Int, b:Int) = s"${a} / ${b} (${fstr(pct(a,b))}%)"

  val formatter = java.text.NumberFormat.getInstance

  override def runPass =  {
    if (postSplitting) {
      sinfo(s"=========== Post-splitting CU Statistics ==================")
    } else if (postMapping) {
      sinfo(s"=========== Post-mapping CU Statistics ==================")
    } else {
      sinfo(s"=========== Pre-splitting CU Statistics ==================")
    }
    val cus = compiler.top.collectDown[GlobalContainer]()
    cus.foreach(dump)
    val cuMap = cus.groupBy(cuType) // tp -> cus
    printCUStat(cuMap)
    if (postMapping) printUsage(cuMap)
  }

  def inputsP(cuP:GlobalContainer) = cuP.ins.groupBy { _.from.src }.map { case (src, ins) => ins.head.src }
  def outputsP(cuP:GlobalContainer) = cuP.outs.map { _.src }

  def printCUStat(cuMap:Map[Option[String], List[GlobalContainer]]) = {
    val cus = cuMap.values.flatten

    if (verbose) {
      sinfo(s"number of cus=${cus.size}")
      cuMap.foreach { case (cuType, cus) =>
        val key = cuType.getOrElse("cu")
        sinfo(s"number of $key = ${cus.size}")
        val cin = stat(cus) { cu => inputsP(cu).filter { io => isBit(io) }.size }
        val cout = stat(cus) { cu => outputsP(cu).filter { io => isBit(io) }.size }
        val sin = stat(cus) { cu => inputsP(cu).filter { io => isWord(io) }.size }
        val sout = stat(cus) { cu => outputsP(cu).filter { io => isWord(io) }.size }
        val vin = stat(cus) { cu => inputsP(cu).filter { io => isVector(io) }.size }
        val vout = stat(cus) { cu => outputsP(cu).filter { io => isVector(io) }.size }
        val stages = stat(cus) { _.collectDown[StageDef]().size }
        sinfo(s"- cin $cin sin $sin vin $vin")
        sinfo(s"- cout $cout sout $sout vout $vout")
        sinfo(s"- stages $stages")
      }
    }

    def cmap(key:String) = cuMap.get(Some(key)).getOrElse(Nil)

    val pcusP = (cmap("pcu") ++ cmap("scu") ++ cmap("ocu")).size
    val pmusP = (cmap("pmu")).size
    val mcsP = (cmap("dfg") ++ cmap("sfg")).size
    val cusP = (cmap("pcu") ++ cmap("scu") ++ cmap("ocu") ++ cmap("dag")).size

    sinfo(s"compute CU = $pcusP")
    sinfo(s"memory CU = $pmusP")
    sinfo(s"mc CU = $mcsP")
    sinfo(s"Total = $cusP")
  }

  def isComputeCU(cuP:GlobalContainer) = {
    List("pcu", "scu", "ocu").contains(cuType(cuP).get)
  }

  def printUsage(cuMap:Map[Option[String], List[GlobalContainer]]):Unit = {
    if (isAsic(designS)) return
    val cumap = pirMap.right.get.cumap

    val cellsS = compiler.arch.top.collectDown[Routable]()
    val pcusS = cellsS.filter { _.param.isInstanceOf[PCUParam] }
    val pmusS = cellsS.filter { _.param.isInstanceOf[PMUParam] }
    val mcsS = cellsS.filter { _.param.isInstanceOf[MCParam] }
    val agsS = cellsS.filter { _.param.isInstanceOf[DramAGParam] }
    
    val groups:Map[Parameter, List[GlobalContainer]] = cumap.usedMap.bmap.map.groupBy { case (cuS, cuP) => cuS.param }.map { case (param, groups) =>
      param -> groups.map { _._2 }.toList
    }
    def cusOf(param:Parameter) = groups.getOrElse(param, Nil)

    sinfo(s"PCU = ${pctstr(cusOf(pcusS.head.param).size, pcusS.size)}")
    sinfo(s"PMU-comp = ${pctstr(cusOf(pmusS.head.param).filter(isComputeCU).size, pmusS.size)}")
    sinfo(s"PMU-mem = ${pctstr(cusOf(pmusS.head.param).filter(cuP => cuType(cuP).get=="pmu").size, pmusS.size)}")
    sinfo(s"AG-ag = ${pctstr(cusOf(agsS.head.param).filter(cuP => cuType(cuP).get=="dag").size, agsS.size)}")
    sinfo(s"AG-comp = ${pctstr(cusOf(agsS.head.param).filter(isComputeCU).size, agsS.size)}")
    sinfo(s"MC = ${pctstr(cusOf(mcsS.head.param).size, mcsS.size)}")
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

