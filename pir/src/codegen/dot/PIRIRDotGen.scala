package pir
package codegen

import pir.node._
import prism.codegen._

class PIRIRDotGen(val fileName:String)(implicit design:PIR) extends PIRTraversal with IRDotCodegen { self =>

  override def clearGen = {}

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
    super.quote(n).foldAt(n.to[PIRNode]) { (q, n) =>
      q.foldAt(n.sname.v) { (q, v) => s"$q[$v]" }
      .append("name", n.name.v)
      .append("ctrl", n.ctrl.v)
    }.foldAt(n.to[Counter]) { (q, n) =>
      q.append("min", n.min.T)
      .append("max", n.max.T)
      .append("step", n.step.T)
      .append("par", n.par)
    }.foldAt(n.to[OpDef]) { (q,n) =>
      s"$q\n${n.op}"
    }.foldAt(n.to[Context]) { (q,n) =>
      s"$q\nctrls:\n${n.collectDown[Controller]().map { _.ctrl.get }.mkString("\n")}"
    }
  }

  override def color(attr:DotAttr, n:N) = n match {
    case n:BufferRead => attr.fillcolor(gold).style(filled)
    case n:Memory => attr.fillcolor(chartreuse).style(filled)
    //case n:ContextEnable => attr.fillcolor(orange).style(filled)
    //case n:ContextEnableOut => attr.fillcolor(orange).style(filled)

    case n:Context => attr.setGraph.fillcolor(palevioletred).style(filled).setNode.fillcolor(palevioletred).style(filled)
    case n:Counter => attr.fillcolor(indianred).style(filled)
    //case n:CUContainer => attr.fillcolor(deepskyblue).style(filled)
    case n:DRAMFringe => attr.fillcolor("lightseagreen").style(filled)
    //case n:StreamFringe => attr.fillcolor("lightseagreen").style(filled)

    case n:OpDef => attr.fillcolor("mediumorchid1").style(filled)
    case n => super.color(attr, n)
  }

  val htmlGen = new PIRHtmlIRPrinter(fileName.replace(".dot", "_IR.html")) {
    override lazy val logger = self.logger
    override def dirName = self.dirName
    override def clearGen = {}
  }

  override def initPass = {
    htmlGen.run
    super.initPass
  }

  override def setAttrs(n:N):DotAttr = {
    super.setAttrs(n).url(htmlGen.fileName + s"#$n")
  }

}

class PIRSimpleDotGen[A:ClassTag](fileName:String)(implicit design:PIR) extends PIRIRDotGen(fileName) {
  override def emitNode(n:N) = n match {
    case _:A => emitSingleNode(n)
    case n => super.emitNode(n)
  }
}
