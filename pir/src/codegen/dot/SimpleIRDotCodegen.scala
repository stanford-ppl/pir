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
      case g:Top => emitSubGraph(n)(traverse(n))
      case g:GlobalContainer => emitSingleNode(n); traverse(n)
      case _ => traverse(n)
    }
  }
}

