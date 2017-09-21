package pir.codegen

import pir._
import pir.util.typealias._

import spade._

import scala.collection.mutable.ListBuffer

class ArgDotPrinter(fn:String)(implicit design:PIR) extends DotCodegen { 
  override lazy val stream = newStream(fn)

  def this()(implicit design:PIR) = this(SpadeConfig.spadeArgInOut)

  def print(pcus:List[PCU], ptop:PTop) = {
    emitBlock("digraph G") {
      pcus.foreach { pcu =>
        val recs = ListBuffer[String]()
        recs += s"{${pcu.vins.map(vin => s"<${vin}> ${vin}").mkString(s"|")}}" 
        recs += s"${pcu}"
        recs += s"<${pcu.vout}> ${pcu.vout}"
        val label = recs.mkString("|")
        emitNode(pcu, label, DotAttr().shape(Mrecord))
      }
      
      ArgDotPrinter.print(ptop)(this)
    }
    close
  }
}
object ArgDotPrinter{
  def print(ptop:PTop)(printer:DotCodegen)(implicit design:PIR) = {
    implicit def spade = design.arch
    def quote(n:Any) = printer.quote(n)
    ptop.vins.foreach { vin =>
      vin.fanIns.foreach { vout =>
        spade match {
          case sn:SwitchNetwork =>
            printer.emitEdge(quote(vout), quote(ptop))
          case pn:PointToPointNetwork =>
            printer.emitEdge(quote(vout), quote(ptop))
        }
      }
    }
    ptop.vouts.foreach { vout =>
      vout.fanOuts.foreach { vin =>
        spade match {
          case sn:SwitchNetwork =>
            printer.emitEdge(quote(ptop), quote(vin))
          case pn:PointToPointNetwork =>
            printer.emitEdge(ptop, quote(vin))
        }
      }
    }
  }
}
