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
    case n:FringeContainer => super.color(attr, n)
    case n:CUContainer =>
      val mems = collectDown[Memory](n).filter(isRemoteMem)
      if (mems.nonEmpty) super.color(attr, mems.head) else super.color(attr, n)
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

  override def emitEdge(from:Edge[N], to:Edge[N], attr:DotAttr):Unit = {
    dbg(s"edge:${from.src}.$from -> ${to.src}.$to")
    //val label = from.src match {
      //case GlobalOutput(data, valid) => s"${data}"
      //case from => s"${from}"
    //}
    //attr.label(label)
    super.emitEdge(from, to, attr)
  }
}

