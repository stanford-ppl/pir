package pir.codegen

import pir.{PIR, Config}
import pir.codegen._
import pir.util._
import pir.util.typealias._
import pir.mapper.{PIRMap}
import pir.exceptions._
import pir.util.misc._
import pir.spade.main._
import pir.spade.node._
import pir.spade.util._
import pir.spade.network._

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Set
import scala.collection.immutable.{Set => ISet}
import scala.collection.mutable.Map
import scala.collection.mutable.HashMap
import java.io.File
import scala.reflect.runtime.universe._
import sys.process._
import scala.language.postfixOps
import scala.language.existentials

class ArgDotPrinter(fn:String)(implicit design:PIR) extends DotCodegen { 
  override lazy val stream = newStream(fn)

  def this()(implicit design:PIR) = this(Config.spadeArgInOut)

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
