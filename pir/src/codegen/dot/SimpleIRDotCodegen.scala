package pir.codegen
import pir._
import pir.util._
import pir.pass._
import pir.node._

import prism._
import prism.util._
import prism.node._
import prism.traversal._
import prism.codegen._

import sys.process._
import scala.language.postfixOps
import scala.collection.mutable

class SimpleIRDotCodegen(override val fileName:String)(implicit compiler:PIR) extends PIRIRDotCodegen(fileName) {
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
      case g:Design => emitSubGraph(n)(super.visitNode(n))
      case g:GlobalContainer => emitSingleNode(n); super.visitNode(n)
      case _ => super.visitNode(n)
    }
  }

  override def emitEdge(from:Edge[N], to:Edge[N], attr:DotAttr):Unit = {
    dbg(s"edge:${from.src}.$from -> ${to.src}.$to")
    (from.src, to.src) match {
      case (from:GlobalOutput, to:GlobalInput) =>
        val fromBundleType = bundleTypeOf(from, logger=Some(this))
        val toBundleType = bundleTypeOf(to, logger=Some(this))
        dbg(s"from:$fromBundleType, to:$toBundleType")
        assert(fromBundleType == toBundleType)
        val style = fromBundleType match {
          case _:Bit => attr.set("style", "dashed").set("color","red")
          case _:Word => attr.set("style", "solid")
          case _:Vector => attr.set("style", "bold").set("color","sienna")
        }
      case _ =>
    }
    super.emitEdge(from, to, attr)
  }

}

