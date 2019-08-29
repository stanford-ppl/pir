package pir
package codegen

import pir.node._
import pir.mapper._
import spade.param._
import prism.codegen._
import prism.graph._

trait Report extends PIRPass with prism.codegen.Codegen {
  def sinfo(x:Any) = {
    if (config.printStat) info(s"$x")
    dbg(x)
  }

  override def quote(x:Any) = x match {
    case x:ArgFringeParam => s"ArgFringe"
    case x:PCUParam => s"PCU"
    case x:PMUParam => s"PMU"
    case x:DramAGParam => s"DAG"
    case x:MCParam => s"MC"
    case x:Checkerboard => s"${x.row} x ${x.col} checkerboard"
    case x:AsicPattern => s"asic"
    case x:InfinatePattern => "infinite"
    case x => super.quote(x)
  }

  def pct(nom:Int, den:Int) =  if (den == 0) 0 else nom * 100.0f / den
  def fstr(float:Float) = f"$float%.1f"
  def pctstr(a:Int, b:Int) = s"${a} / ${b} (${fstr(pct(a,b))}%)"

}

class ResourceReport(implicit design:PIR) extends Report with PIRTraversal with CSVCodegen with ChildFirstTraversal with UnitTraversal {

  val fileName = "resource.csv"

  override def runPass = {
    reportUsage(topMap)
  }

  def reportUsage(x:Any):Unit =  x match {
    case Right(m) => reportUsage(m)
    case m:TopMap => m.fields.foreach(reportUsage)
    case m:CUMap =>  reportUsage(m)
    case _ => 
  }

  def reportUsage(x:CUMap) = {
    sinfo(s"Usage: ")
    val cus = spadeTop.cus
    var groups = cus.groupBy { _.params.get }
    groups = groups.filterNot { case (param:ArgFringeParam, _) => true; case _ => false }
    sinfo("")
    sinfo(s"Target: ${quote(spadeParam.pattern)}")
    groups.foreach { case (param, cus) =>
      val (used, notUsed) = cus.partition { cu => x.usedMap.bmap.contains(cu) }
      val usage = fstr(pct(used.size, cus.size))
      val row = newRow
      row(s"Type") = quote(param)
      row(s"Used") = used.size
      row(s"Total") = cus.size
      row(s"Usage") = usage
      sinfo(s"${quote(param)} Usage: (${used.size}/${cus.size}) ${usage} %")
    }
  }

}

