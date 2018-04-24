package pir.codegen

import pir.node._
import spade.node._

class SimpleIRDotCodegen(override val fileName:String)(implicit compiler:PIR) extends PIRIRDotCodegen(fileName) {
  override val horizontal:Boolean = false

  override def color(attr:DotAttr, n:Any) = n match {
    case n:DramFringe => attr.fillcolor("lightseagreen").style(filled)
    case n:CUContainer if isPMU(n) => attr.fillcolor(chartreuse).style(filled)
    case n:CUContainer if isDAG(n) => attr.fillcolor("deeppink").style(filled)
    case n:CUContainer if isSCU(n) => attr.fillcolor("gold").style(filled)
    case n:CUContainer if isOCU(n) => attr.fillcolor("darkorange1").style(filled)
    case n:CUContainer if isPCU(n) => attr.fillcolor("deepskyblue").style(filled)
    case n => super.color(attr,n)
  }

  override def emitNode(n:N) = {
    n match {
      case g:Design => emitSubGraph(n)(super.visitNode(n))
      case g:GlobalContainer => emitSingleNode(n); super.visitNode(n)
      case _ => super.visitNode(n)
    }
  }

  override def emitEdge(from:prism.node.Edge[N], to:prism.node.Edge[N], attr:DotAttr):Unit = {
    dbg(s"edge:${from.src}.$from -> ${to.src}.$to")
    (from.src, to.src) match {
      case (from:GlobalOutput, to:GlobalInput) =>
        val fromPinType = pinTypeOf(from, logger=Some(this))
        val toPinType = pinTypeOf(to, logger=Some(this))
        dbg(s"from:$fromPinType, to:$toPinType")
        assert(fromPinType == toPinType)
        fromPinType match {
          case tp if isBit(tp) => attr.set("style", "dashed").set("color","red")
          case tp if isWord(tp) => attr.set("style", "solid")
          case tp if isVector(tp) => attr.set("style", "bold").set("color","sienna")
        }
      case _ =>
    }
    super.emitEdge(from, to, attr)
  }

}

