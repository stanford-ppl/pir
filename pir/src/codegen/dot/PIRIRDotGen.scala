package pir
package codegen

import pir.node._
import prism.codegen._

class PIRIRDotGen(val fileName:String)(implicit design:PIR) extends PIRTraversal with IRDotCodegen {

  implicit class StringHelper(label:String) {
    def append(field:Any):String = label + s"\n$field"
    def append(field:String, value:Any):String = value match {
      case Const(value) => label + s"\n$field=$value"
      case x:PIRNode => label
      case None => label 
      case Some(x) => label + s"\n$field=$x"
      case x => label + s"\n$field=$value"
    }
  }
  override def label(attr:DotAttr, n:N) = {
    var label = n match {
      case n:Counter =>
        super.label(attr, n).setNode.getLabel
        .append("min", n.min.T)
        .append("max", n.max.T)
        .append("step", n.step.T)
        .append("par", n.par)
      case n:OpDef => 
        super.label(attr, n).setNode.getLabel
          .append(n.op)
      //case n:GlobalInput => label += s"\n(from=${n.globalOutput})"
      //case n:GlobalOutput => 
        //label += s"\n(to=${n.out.connected.map(_.src).mkString(",\n")})"
      case n => super.label(attr, n).setNode.getLabel
    }
    label = label
    .append("name", n.as[PIRNode].name.v)
    .append("ctrl", n.as[PIRNode].ctrl.v)
    //n match {
      //case n:PIRNode => 
        //val metas = List(ctrlOf, boundOf, parOf, iterOf, countOf, scaleOf, originOf, srcCtxOf, isAccum, antiDepsOf)
        //metas.foreach { meta =>
          //meta.asK(n).flatMap { k => meta.get(k) }.foreach { v =>
            //label += s"\n(${meta.name}=${quote(v)})"
          //}
        //}
      //case _ =>
    //}
    attr.setNode.label(label).setGraph.label(label)
  }

  //def shape(attr:DotAttr, n:Any) = attr.shape(box)

  override def color(attr:DotAttr, n:N) = n match {
    case n:InputBuffer => attr.fillcolor(gold).style(filled)
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
