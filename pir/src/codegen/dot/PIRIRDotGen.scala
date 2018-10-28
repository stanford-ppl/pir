package pir
package codegen

import pir.node._
import prism.codegen._

class PIRIRDotGen(val fileName:String)(implicit design:PIR) extends PIRTraversal with IRDotCodegen {

  implicit class PIRStringHelper(label:String) {
    def append(field:String, value:Any):String = value match {
      case Const(value) => label + s"\n$field=$value"
      case x:PIRNode => label
      case None => label 
      case Some(x) => label + s"\n$field=$x"
      case x => label + s"\n$field=$value"
    }
  }

  override def quote(n:Any) = {
    super.quote(n).foldAt(n.as[PIRNode]) { (q, n) =>
      q.foldAt(n.sname.v) { (q, v) => s"$q[$v]" }
      .append("name", n.name.v)
      .append("ctrl", n.ctrl.v)
    }.foldAt(n.as[Counter]) { (q, n) =>
      q.append("min", n.min.T)
      .append("max", n.max.T)
      .append("step", n.step.T)
      .append("par", n.par)
    }.foldAt(n.as[OpDef]) { (q,n) =>
      s"$q\n${n.op}"
    }
  }

  //def shape(attr:DotAttr, n:Any) = attr.shape(box)

  override def color(attr:DotAttr, n:N) = n match {
    case n:BufferRead => attr.fillcolor(gold).style(filled)
    case n:Memory => attr.fillcolor(chartreuse).style(filled)
    //case n:ContextEnable => attr.fillcolor(orange).style(filled)
    //case n:ContextEnableOut => attr.fillcolor(orange).style(filled)

    case n:Context => attr.setGraph.fillcolor(palevioletred).style(filled)
    case n:Counter => attr.fillcolor(indianred).style(filled)
    //case n:CUContainer => attr.fillcolor(deepskyblue).style(filled)
    case n:DRAMFringe => attr.fillcolor("lightseagreen").style(filled)
    //case n:StreamFringe => attr.fillcolor("lightseagreen").style(filled)

    case n:OpDef => attr.fillcolor("mediumorchid1").style(filled)
    case n => super.color(attr, n)
  }

  //def usedByCounter(n:PIRNode) = {
    //n.collect[Primitive](visitFunc=n.visitGlobalOut, depth=2).filter(isCounter).nonEmpty
  //}

  //override def emitNode(n:N) = {
    //n match {
      ////case n:Const[_] if usedByCounter(n) => super.visitNode(n)
      //case n:High =>
      //case n:Low =>
      //case n:Primitive => emitSingleNode(n); super.visitNode(n)
      //case n => super.emitNode(n) 
    //}
  //}

  //def areLocal(a:N, b:N) = {
    //val cuA = a.collectUp[GlobalContainer]().headOption
    //val cuB = b.collectUp[GlobalContainer]().headOption
    //cuA == cuB
  //}

  //override def emitEdge(from:N, to:N, attr:DotAttr) = {
    //(from, to) match {
      //case (from:ArgInDef, to) if !areLocal(from, to) =>
      //case (from:TokenInDef, to) if !areLocal(from, to) =>
      //case (from, to:ArgIn) if !areLocal(from, to) =>
      //case (from, to:TokenIn) if !areLocal(from, to) =>
      //case (from:GlobalOutput, to:GlobalInput) =>
      //case (from, to) => super.emitEdge(from, to, attr)
    //}
  //}

}
