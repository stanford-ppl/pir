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
      case GlobalInput(gout) => label += s"\n(from=${gout})"
      case n:GlobalOutput => label += s"\n(to=${n.out.to.map(_.src)})"
      case n =>
    }
    n match {
      case n:PIRNode => 
        ctrlOf.get(n).foreach { ctrl => label += s"\n(${quote(ctrl)})" }
        topCtrlOf.get(n).foreach { ctrl => label += s"\n(topCtrl=${quote(ctrl)})" }
      case _ =>
    }
    attr.label(label)
  }

  //def shape(attr:DotAttr, n:Any) = attr.shape(box)

  override def color(attr:DotAttr, n:Any) = n match {
    case n:RetimingFIFO => attr.fillcolor(gold).style(filled)
    case n:Memory if isFIFO(n) => attr.fillcolor(gold).style(filled)
    case n:Memory if isReg(n) => attr.fillcolor(limegreen).style(filled)
    case n:Memory if isRemoteMem(n) => attr.fillcolor(chartreuse).style(filled)
    case n:ContextEnable => attr.fillcolor(orange).style(filled)

    case n:ComputeContext => attr.fillcolor(palevioletred).style(filled)
    case n:Counter => attr.fillcolor(indianred).style(filled)
    case n:CUContainer => attr.fillcolor(deepskyblue).style(filled)
    case n:FringeContainer => attr.fillcolor("lightseagreen").style(filled)
    case n => super.color(attr, n)
  }

  override def emitNode(n:N) = {
    n match {
      case n:Const[_] if collectOut[Counter](n).isEmpty => super.visitNode(n)
      case n:Primitive => emitSingleNode(n); super.visitNode(n)
      case n => super.emitNode(n) 
    }
  }

  def areLocal(a:N, b:N) = {
    val cuA = collectUp[GlobalContainer](a).headOption
    val cuB = collectUp[GlobalContainer](b).headOption
    cuA == cuB
  }

  override def emitEdge(from:N, to:N, attr:DotAttr) = {
    (from, to) match {
      case (from:ArgInDef, to) if !areLocal(from, to) =>
      case (from, to:ArgIn) if !areLocal(from, to) =>
      case (from:GlobalOutput, to:GlobalInput) =>
      case (from, to) => super.emitEdge(from, to, attr)
    }
  }

}
