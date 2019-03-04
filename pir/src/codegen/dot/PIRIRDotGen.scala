package pir
package codegen

import pir.node._
import prism.codegen._
import prism.util._

class PIRIRDotGen(val fileName:String)(implicit design:PIR) extends PIRTraversal with IRDotCodegen { self =>

  implicit class PIRStringHelper(label:String) {
    def append(field:String, value:Any):String = value match {
      case Some(Const(value)) => label + s"\n$field=$value"
      case x:PIRNode => label
      case None => label 
      case Some(x) => label + s"\n$field=$x"
      case x => label + s"\n$field=$value"
    }
  }

  override def quote(n:Any) = {
    super.quote(n).foldAt(n.to[PIRNode]) { (q, n) =>
      q
    .foldAt(n.to[Const]) { (q,n) =>
      n.value match {
        case v:List[_] if v.size > 1 => 
          val l = v.as[List[Int]]
          s"$q[${l.min}-${l.max}]"
        case v => s"$q($v)"
      }
    }.foldAt(n.sname.v) { (q, v) => s"$q[$v]" }
      .append("name", n.name.v)
      .append("ctrl", n.ctrl.v)
      .append("count", n.count.v)
      .append("scale", n.scale.v)
      .append("iter", n.iter.v) +
      n.srcCtx.v.fold("") { sc => s"\n$sc" }
    }.foldAt(n.to[Counter]) { (q, n) =>
      q.append("min", n.min.T)
      .append("max", n.max.T)
      .append("step", n.step.T)
      .append("par", n.par)
    }.foldAt(n.to[OpDef]) { (q,n) =>
      s"$q\n${n.op}"
    }.foldAt(n.to[Access]) { (q,n) =>
      q.append("port", n.port.v)
    }.foldAt(n.to[LocalOutAccess]) { (q,n) =>
      if (n.initToken.get) { s"$q\ninitToken" } else q
    }.foldAt(n.to[MemoryNode]) { (q,n) =>
      q.append("banks", n.banks.get)
      .append("depth", n.depth.get)
    }.foldAt(n.to[GlobalOutput]) { (q,n) =>
      s"${q}\nto:\n${n.out.T.mkString("\n")}"
    }.foldAt(n.to[GlobalInput]) { (q,n) =>
      s"${q}\nfrom:\n${n.in.T}"
    }.foldAt(n.to[Context]) { (q,n) =>
      q
      .append("active", n.active.v)
      .append("state", n.psimState)
      .append("activeRate", n.activeRate) + 
      n.ctrl.get.srcCtx.v.fold("") { sc => s"\n$sc" }
    }
  }

  override def color(attr:DotAttr, n:N) = n match {
    case n:LocalOutAccess => attr.fillcolor(gold).style(filled)
    case n:Memory => attr.fillcolor(chartreuse).style(filled)
    case n:GlobalInput if n.psimState == Some(".") && n.out.T.map { _.ctx.get}.exists { _.psimState != Some("DONE") } => attr.setNode.fillcolor("firebrick1").style(filled)
    case n:GlobalOutput if n.psimState == Some(".") => attr.setNode.fillcolor("goldenrod1").style(filled)
    case n:Context => 
      val color = zipOption(n.active.v, n.psimState).fold {
        "palevioletred1"
      } { case (active, state) =>
        n.count.get match {
          case Finite(expected) if active < expected =>
            if (state == "STARVE") "firebrick1"
            else if (state == "STALL") "goldenrod1"
            else if (state == "BOTH") "darkorange"
            else "palevioletred1"
          case _ =>
            val G = Math.round((1 - n.activeRate.get / 100) * (255 - 50) + 50).toInt
            var HG = G.toHexString
            while (HG.size < 2) HG = "0" + HG
            s"#00${HG}00"
        }
      }
      attr.setGraph.fillcolor(color).style(filled).setNode.fillcolor(color).style(filled)
    case n:Counter => attr.fillcolor(indianred).style(filled)
    //case n:CUContainer => attr.fillcolor(deepskyblue).style(filled)
    case n:DRAMCommand => attr.setGraph.fillcolor("lightseagreen").style(filled).setNode.fillcolor("lightseagreen").style(filled)
    case n:StreamCommand => attr.setGraph.fillcolor("lightseagreen").style(filled).setNode.fillcolor("lightseagreen").style(filled)
    case n:DRAMFringe => attr.setGraph.fillcolor("lightseagreen").style(filled).setNode.fillcolor("lightseagreen").style(filled)
    //case n:StreamFringe => attr.setGraph.fillcolor("lightseagreen").style(filled).setNode.fillcolor("lightseagreen").style(filled)

    case n:OpNode => attr.fillcolor("mediumorchid1").style(filled)
    case n => super.color(attr, n)
  }

  override def emitEdge(from:EN[N], to:EN[N], attr:DotAttr):Unit = {
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
  override def visitFunc(n:N) = n match {
    case n:Context => n.children.collect { case n:LocalAccess => n; case n:Access => n; case c:FringeCommand => c }
    case n => super.visitFunc(n)
  }
  //override def emitNode(n:N) = n match {
    //case _:Context => emitSingleNode(n)
    //case n => super.emitNode(n)
  //}
  //override def quote(n:Any) = {
    //super.quote(n).foldAt(n.to[Context]) { (q,n) =>
      //val cs = n.children.collect { case c:Access => c; case c:FringeCommand => c }
      //val cstr = cs.map(quote)
      //if (cstr.nonEmpty) s"$q\n${cstr.mkString("\n")}" else q
    //}
  //}

}
