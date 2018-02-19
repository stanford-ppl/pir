package pir.codegen
import pir._
import pir.util._
import pir.pass._
import pir.node._

import pirc._
import pirc.util._
import prism.node._
import prism.traversal._
import prism.codegen._

import sys.process._
import scala.language.postfixOps
import scala.collection.mutable

class SimpleIRDotCodegen(override val fileName:String)(implicit design:PIR) extends PIRIRDotCodegen(fileName) {
  override val horizontal:Boolean = false

  override def color(attr:DotAttr, n:Any) = n match {
    case n:CUContainer if collectDown[SRAM](n).nonEmpty => attr.fillcolor(orange).style(filled)
    //case n:CUContainer if n.controller.isOuterControl => attr.fillcolor(indianred).style(filled) ////TODO
    case n => super.color(attr,n)
  }

  override def emitNode(n:N) = {
    n match {
      case g:Top => emitSubGraph(n)(super.visitNode(n))
      case g:GlobalContainer => emitSingleNode(n); super.visitNode(n)
      case _ => super.visitNode(n)
    }
  }

  override def emitEdge(from:N, to:N) = {
    dbg(s"node:$from -> $to")
    (from, to) match {
      case (from:ArgFringe, to) =>
      case (from, to) => super.emitEdge(from, to)
    }
  }

  override def emitEdge(from:Edge[N], to:Edge[N]):Unit = {
    dbg(s"edge:${from.src}.$from -> ${to.src}.$to")
    super.emitEdge(from, to)
  }
}

