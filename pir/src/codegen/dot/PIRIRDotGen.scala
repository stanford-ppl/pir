package pir
package codegen

import pir.node._
import prism.codegen._
import prism.util._
import prism.graph._

class PIRIRDotGen(fn:String)(implicit design:PIR) extends PIRTraversal with IRDotCodegen { self =>
  def fileName = fn

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
        case v@((e:Int)::rest) => 
          val l = v.as[List[Int]]
          s"$q[${l.min}-${l.max}]"
        case v@((e:Boolean)::rest) => s"$q($e...)"
        case v => s"$q($v)"
      }
    }.foldAt(n.sname.v) { (q, v) => s"$q[$v]" }
      .append("name", n.name.v)
      .append("externAlias", n.externAlias.v)
      .append("ctrl", n.ctrl.v.map { c => c.sname.v.fold(s"$c") { n => s"$c[$n]"} })
      .append("tp", n.tp.v.orElse(if (n.localOuts.size==1) n.localOuts.head.tpMeta.v else None))
      .append("vec", n.vec.v.orElse(if (n.localOuts.size==1) n.localOuts.head.vecMeta.v else None))
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
      var l = q
      if (n.initToken.get) { l += s"\ninitToken" }
      if (n.isSplit.get) { l += s"\n split" }
      l
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
      n.getCtrl.uid.v.fold("") { uid => s"\nuid=[${uid.mkString(",")}], par=${n.getCtrl.par.get}"} +
      n.ctrl.get.srcCtx.v.fold("") { sc => s"\n$sc" } + 
      s"\nop=${n.collectDown[OpNode]().size}"
    }
  }

  override def color(attr:DotAttr, n:N) = n match {
    case n:BufferRead if isRateMatchingFIFO(n) => attr.fillcolor("darkorange").style(filled)
    case n:LocalOutAccess if n.nonBlocking => attr.fillcolor("darkorange").style(filled)
    case n:LocalOutAccess => attr.fillcolor(gold).style(filled)
    case n:Memory => attr.fillcolor(chartreuse).style(filled)
    case n:GlobalInput if n.psimState == Some(".") && n.out.T.map { _.ctx.get}.exists { _.psimState != Some("DONE") } => attr.setNode.fillcolor("firebrick1").style(filled)
    case n:GlobalOutput if n.psimState == Some(".") => attr.setNode.fillcolor("goldenrod1").style(filled)
    case n:Context => 
      val color = zipOption(n.active.v, n.psimState).fold {
        if (n.streaming.get) "deepskyblue1" else "palevioletred1"
      } { case (active, state) =>
        n.count.get match {
          case Finite(expected) if active < expected =>
            if (state == "STARVE") "firebrick1"
            else if (state == "STALL") "goldenrod1"
            else if (state == "BOTH") "darkorange"
            else if (n.streaming.get) "deepskyblue1" 
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
    case n:BlackBox => attr.fillcolor("crimson").style(filled)

    case n:OpNode => attr.fillcolor("mediumorchid1").style(filled)
    case n => super.color(attr, n)
  }

  override def emitEdge(from:EN[N], to:EN[N], attr:DotAttr):Unit = {
    val newAttr = from.src match {
      case from:GlobalOutput if from.vec.v.nonEmpty & isVecLink(from) => attr.setEdge.style(bold)
      case from:GlobalOutput if from.vec.v.nonEmpty & isCtrlLink(from) => attr.setEdge.style(dashed)
      case _ =>  attr.setEdge
    }
    super.emitEdge(from, to, newAttr)
  }

  lazy val htmlGen = new PIRHtmlIRPrinter(fileName.replace(".dot", "_IR.html")) {
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

class PIRTopDotGen(fileName:String)(implicit design:PIR) extends PIRIRDotGen(fileName) {
  override def emitEdge(from:EN[N], to:EN[N], attr:DotAttr):Unit = {
    (from, to) match {
      case (OutputField(from:GlobalOutput, _), InputField(to:GlobalInput,_)) =>
      case (from, to) => super.emitEdge(from, to, attr)
    }
  }
}

class PIRCtxDotGen(fileName:String)(implicit design:PIR) extends PIRIRDotGen(fileName) {
  override def visitFunc(n:N) = n match {
    case n:Context => n.descendents.collect { case n:LocalAccess => n; case n:Access => n; case c:BlackBox => c }.toList
    case n => super.visitFunc(n)
  }
}

class PIRGlobalDotGen(fn:String)(implicit design:PIR) extends PIRIRDotGen(fn) {
  //override def fileName = pirTop.name.get + ".dot"
  //override def dirName = buildPath(config.appDir, "../figs")
  override def dotFile:String = fileName.replace(s".dot", s".html")

  override def color(attr:DotAttr, n:N) = n match {
    case n:ArgFringe => attr.setNode.fillcolor("beige").style(filled)
    case n:MemoryContainer => attr.setNode.fillcolor("limegreen").style(filled)
    case n:ComputeContainer if n.isDAG.get => attr.setNode.fillcolor("orange").style(filled)

    case n:ComputeContainer => attr.setNode.fillcolor("dodgerblue").style(filled)
    case n:DRAMFringe => attr.setNode.fillcolor("lightseagreen").style(filled)
    case n:BlackBoxContainer => attr.setNode.fillcolor("crimson").style(filled)
    case n:Top => super.color(attr,n)
  }

  //override def label(attr:DotAttr, n:N) = n match {
    //case n:ArgFringe => attr.setNode.label("Host")
    //case n:MemoryContainer => attr.setNode.label("PMU")
    //case n:ComputeContainer if n.isDAG.get => attr.setNode.label("DRAM_AG")

    //case n:ComputeContainer => attr.setNode.label("PCU")
    //case n:DRAMFringe => attr.setNode.label("MC")
    //case n:BlackBoxContainer => attr.setNode.label("BB")
    //case n:Top => super.color(attr,n)
  //}
  
  override def emitEdge(from:EN[N], to:EN[N], attr:DotAttr):Unit = {
    (from, to) match {
      case (from@OutputField(fromsrc:GlobalOutput, _), to) if fromsrc.isUnder[ArgFringe] && from.connected.size > 5 => 
      case (from@OutputField(fromsrc:GlobalOutput, _), to@InputField(tosrc:GlobalInput, _)) => 
        var tooltip = s"$fromsrc"
        tooltip += s"\n${fromsrc.in.neighbors.map(quoteSrcCtx).mkString(",")}"
        tooltip += s"\n${tosrc.out.neighbors.map(quoteSrcCtx).mkString(",")}"
        fromsrc.count.v.foreach { c => 
          c match {
            case Finite(c) => tooltip += s"\ncount=$c"
            case Infinite => tooltip += s"\ncount=$c"
            case Unknown =>
          }
        }
        tooltip += s"\ntp=${fromsrc.getTp}".append("vec", fromsrc.vec.v)
        val dst = tosrc + "," + tosrc.out.neighbors.mkString(",")
        var edgeAttr = attr.setEdge.attr("id",dst).attr("label",fromsrc.id).attr("labeltooltip", tooltip)
        if (fromsrc.in.T.isSplit.get) {
          edgeAttr.color("orangered1")
        }
        super.emitEdge(from,to,edgeAttr)
      case _ => super.emitEdge(from,to,attr)
    }
  }

  override def setAttrs(n:N):DotAttr = n match {
    case n:GlobalContainer =>
      val mem = n.collectDown[Memory]().map { quote(_) }
      val bbs = n.collectDown[BlackBox]().map { quote(_) }
      var tooltip = s"$n"
      if (mem.nonEmpty) tooltip += s"\n${mem.mkString(",")}" 
      if (bbs.nonEmpty) tooltip += s"\n${bbs.mkString(",")}"
      val ctxs = n.collectDown[Context]()
      val ctxStr = ctxs.slice(0,3).map { ctx =>
        quote(ctx)
      }
      tooltip += s"\n${ctxStr.mkString("\n")}${if(ctxs.size>3) "\n..." else ""}"
      super.setAttrs(n).attr("tooltip", tooltip)
    case n => super.setAttrs(n)
  }
  
  override def quote(n:Any) = {
    super.quote(n).foldAt(n.to[GlobalContainer]) { (q, n) => 
      val tp = n match {
        case n:ArgFringe => "A"
        case n:ComputeContainer => "C"
        case n:MemoryContainer => "M"
        case n:DRAMFringe => "D"
        case n:BlackBoxContainer => "B"
      }
      var l = s"${tp}${n.id}"
      val mem = n.collectDown[Memory]()
      mem.foreach { mem =>
        mem.name.v.foreach { name => l += s"\n$name" }
      }
      val bbs = n.collectDown[BlackBox]()
      bbs.foreach { bbs =>
        bbs.name.v.foreach { name => l += s"\n$name" }
        bbs.to[Splitter].foreach{ s => l += s"\n$s" }
        bbs.to[LockRMABlock].foreach{ s => l += s"\n$s" }
      }
      if (mem.isEmpty && bbs.isEmpty) {
        n.collectDown[LocalOutAccess]().foreach { mem =>
          mem.name.v.foreach { name => l += s"\n$name" }
        }
      }
      l
    }
  }

  override def emitNode(n:N) = n match {
    case n:Top => visitNode(n)
    case n:GlobalContainer => emitSingleNode(n)
    case n => super.emitNode(n)
  }

}
