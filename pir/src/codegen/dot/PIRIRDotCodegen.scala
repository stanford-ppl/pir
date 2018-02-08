package pir.codegen
import pir._
import pir.util._
import pir.pass._
import pir.node._

import pirc._
import pirc.util._
import prism.traversal._
import prism.codegen._

import sys.process._
import scala.language.postfixOps
import scala.collection.mutable


class PIRIRDotCodegen(val fileName:String)(implicit design:PIR) extends PIRCodegen with IRDotCodegen {

  import pirmeta._

  override def quote(n:Any):String = qtype(n)

  override def label(attr:DotAttr, n:Any) = {
    var label = quote(n) 
    n match {
      case n:Counter =>
        val fields = n.fieldNames.zip(n.productIterator.toList).flatMap { 
          case (field, Const(v)) => Some(s"$field=$v")
          case _ => None
        }
        label += s"\n(${fields.mkString(",")})"
      case n:OpDef => label += s"\n(${n.op})"
      case n:StreamIn => label += s"\n(${n.field})"
      case n:StreamOut => label +=s"\n(${n.field})"
      case n =>
    }
    n match {
      case n:Node => ctrlOf.get(n).foreach { ctrl => label += s"\n(${quote(ctrl)})" }
      case _ =>
    }
    attr.label(label)
  }

  //def shape(attr:DotAttr, n:Any) = attr.shape(box)

  override def color(attr:DotAttr, n:Any) = n match {
    case n:SRAM => attr.fillcolor(orange).style(filled)
    case n:RetimingFIFO => attr.fillcolor(gold).style(filled)
    case n:FIFO => attr.fillcolor(gold).style(filled)
    case n:StreamIn => attr.fillcolor(gold).style(filled)
    case n:StreamOut => attr.fillcolor(gold).style(filled)
    case n:Reg => attr.fillcolor(limegreen).style(filled)
    case n:ArgIn => attr.fillcolor(limegreen).style(filled)
    case n:ArgOut => attr.fillcolor(limegreen).style(filled)
    case n:Counter => attr.fillcolor(indianred).style(filled)
    case n:CUContainer => attr.fillcolor(deepskyblue).style(filled)
    case n:FringeContainer => attr.fillcolor(chartreuse).style(filled)
    case n => super.color(attr, n)
  }

  override def emitNode(n:N) = {
    n match {
      case n:Const[_] if collectOut[Counter](n).isEmpty =>
      case n:Primitive => emitSingleNode(n)
      case n => super.emitNode(n) 
    }
  }

  override def emitEdge(from:N, to:N) = {
    (from, to) match {
      case (from:ArgInDef, to) if from.parent != to.parent =>
      case (from, to:ArgIn) if from.parent != to.parent =>
      case (from, to) => super.emitEdge(from, to)
    }
  }

}
