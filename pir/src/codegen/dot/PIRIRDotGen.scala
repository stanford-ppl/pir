package pir
package codegen

import pir.node._
import prism.codegen._

class PIRIRDotGen(val fileName:String)(implicit design:PIR) extends PIRTraversal with IRDotCodegen { self =>

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
      .append("count", n.count.v.flatten)
      .append("scale", n.scale.v.flatten)
      .append("iter", n.iter.v.flatten) +
      n.srcCtx.v.fold("") { sc => s"\n$sc" }
    }.foldAt(n.to[Counter]) { (q, n) =>
      q.append("min", n.min.T)
      .append("max", n.max.T)
      .append("step", n.step.T)
      .append("par", n.par)
    }.foldAt(n.to[OpDef]) { (q,n) =>
      s"$q\n${n.op}"
    }.foldAt(n.to[Const]) { (q,n) =>
      s"$q\n${n.value}"
    }.foldAt(n.to[Access]) { (q,n) =>
      q.append("port", n.port.v)
    }.foldAt(n.to[LocalOutAccess]) { (q,n) =>
      if (n.initToken.get) { s"$q\ninitToken" } else q
    }.foldAt(n.to[MemoryNode]) { (q,n) =>
      q.append("banks", n.banks.get)
      .append("depth", n.depth.get)
    }.foldAt(n.to[Context]) { (q,n) =>
      val ctrl = n.collectDown[Controller]().map { _.ctrl.get }.maxOptionBy { _.ancestors.size }
      ctrl.fold(q) { ctrl => s"$q\n${ctrl}" }
      .append("active", n.active.v)
      .append("state", n.state.v)
    }
  }

  override def color(attr:DotAttr, n:N) = n match {
    case n:LocalOutAccess => attr.fillcolor(gold).style(filled)
    case n:Memory => attr.fillcolor(chartreuse).style(filled)
    //case n:ContextEnable => attr.fillcolor(orange).style(filled)
    //case n:ContextEnableOut => attr.fillcolor(orange).style(filled)

    case n:Context => 
      val color = zipOption(n.active.v, n.state.v).fold {
        "palevioletred1"
      } { case (active, state) =>
        val expected = n.count.get.get
        if (active < expected) {
          if (state == "STARVE") "firebrick1"
          else if (state == "STALL") "goldenrod1"
          else "palevioletred1"
        } else {
          "palevioletred1"
        }
      }
      attr.setGraph.fillcolor(color).style(filled).setNode.fillcolor(color).style(filled)
    case n:Counter => attr.fillcolor(indianred).style(filled)
    //case n:CUContainer => attr.fillcolor(deepskyblue).style(filled)
    case n:DRAMCommand => attr.setGraph.fillcolor("lightseagreen").style(filled).setNode.fillcolor("lightseagreen").style(filled)
    case n:DRAMFringe => attr.setGraph.fillcolor("lightseagreen").style(filled).setNode.fillcolor("lightseagreen").style(filled)
    //case n:StreamFringe => attr.fillcolor("lightseagreen").style(filled)

    case n:OpNode => attr.fillcolor("mediumorchid1").style(filled)
    case n => super.color(attr, n)
  }

  override def emitEdge(from:E, to:E, attr:DotAttr):Unit = {
    val newAttr = from.src match {
      case from:GlobalOutput if from.vec.v.fold(false) { _ > 1 } => attr.setEdge.style(bold)
      case _ =>  attr
    }
    super.emitEdge(from, to, newAttr)
  }

  val htmlGen = new PIRHtmlIRPrinter(fileName.replace(".dot", "_IR.html")) {
    override lazy val logger = self.logger
    override def dirName = self.dirName
  }

  override def initPass = {
    htmlGen.run
    super.initPass
  }

  override def setAttrs(n:N):DotAttr = {
    super.setAttrs(n).url(htmlGen.fileName + s"#$n")
  }

}

class PIRCtxDotGen(fileName:String)(implicit design:PIR) extends PIRIRDotGen(fileName) {
  override def emitNode(n:N) = n match {
    case _:Context => emitSingleNode(n)
    case n => super.emitNode(n)
  }
  override def quote(n:Any) = {
    super.quote(n).foldAt(n.to[Context]) { (q,n) =>
      val accesses = n.collectDown[Access]()
      val astr = accesses.map(quote)
      if (astr.nonEmpty) s"$q\n${astr.mkString("\n")}" else q
    }
  }

}
