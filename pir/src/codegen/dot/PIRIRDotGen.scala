package pir
package codegen

import pir.node._
import prism.codegen._
import prism.util._
import prism.graph._

trait PIRIRDotGen extends PIRTraversal with IRDotCodegen { self =>

  implicit class PIRStringHelper(label:String) {
    def append(field:String, value:Any):String = value match {
      case Some(Const(value)) => label + s"\n$field=$value"
      case x:PIRNode => label
      case None => label 
      case Some(x) => label + s"\n$field=$x"
      case x => label + s"\n$field=$value"
    }
    def append(value:Any):String = value match {
      case Some(v) => s"$label\n$v"
      case None => label
      case v => s"$label\n$v"
    } 
      
  }

  def quoteTp(n:PIRNode) = {
    val tp = n.tp.v.orElse(if (n.localOuts.size==1) n.localOuts.head.tpMeta.v else None)
    val vec = n.vec.v.orElse(if (n.localOuts.size==1) n.localOuts.head.vecMeta.v else None)
    if (tp.isEmpty && vec.isEmpty) None else 
    Some(tp.map { tp => tp.toString }.getOrElse("") + vec.map { vec => s"<${vec}>" }.getOrElse(""))
  }

  override def quote(n:Any) = {
    super.quote(n).foldAt(n.to[PIRNode]) { (q, n) =>
      q
    .foldAt(n.to[Delay]) { (q,n) =>
      s"$q(${n.cycle})"
    }.foldAt(n.to[Const]) { (q,n) =>
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
      .append(n.ctrl.v.map { c => c.sname.v.fold(s"$c") { n => s"$c[$n]"} })
      .append(n.bbCtrl.v.map { c => 
        c match {
          case c if n.controlShadowed.get =>
            c.sname.v.fold(s"$c") { n => s"BB: $c[$n]"} 
          case _ =>
            ""
        }
      })
      .append(quoteTp(n))
      .append("delay", n.delay.v)
      .append("count", n.count.v)
      .append("scale", n.scale.v)
      .append("iter", n.iter.v)
    }.foldAt(n.to[StridedCounter]) { (q, n) =>
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
      if (n.initToken.get>0) { l += s"\ninitToken=${n.initToken.get}" }
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

  override def emitEdge(from:EN[N], to:EN[N], attr:DotAttr, f_sub:String, t_sub:String):Unit = {
    val newAttr = from.src match {
      case from:GlobalOutput if from.out.vecMeta.v.nonEmpty & isVecLink(from) => attr.setEdge.style(bold)
      case from:GlobalOutput if from.out.vecMeta.v.nonEmpty & isCtrlLink(from) => attr.setEdge.style(dashed)
      case _ =>  attr.setEdge
    }
    super.emitEdge(from, to, newAttr, f_sub, t_sub)
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
    val attr = super.setAttrs(n).url(htmlGen.fileName + s"#$n")
    n match {
      case n:PIRNode if n.isLeaf =>
        attr.setNode.attr("tooltip", quoteToolTip(n)) 
      case n:PIRNode =>
        attr.setGraph.attr("tooltip", quoteToolTip(n)) 
      case n => attr
    }
  }

  def quoteToolTip(n:PIRNode) = {
    var tooltip = new StringBuilder()
    tooltip ++= qdef(n)
    n.metadata.values.foreach { metadata =>
      metadata.v.foreach { v =>
        tooltip ++= s"\n${metadata.name} = $v"
      }
    }
    tooltip.toString
  }

}

class PIRTopDotGen(val fileName:String)(implicit design:PIR) extends PIRIRDotGen {
  override def emitEdge(from:EN[N], to:EN[N], attr:DotAttr, f_sub:String, t_sub:String):Unit = {
    (from, to) match {
      case (OutputField(from:GlobalOutput, _), InputField(to:GlobalInput,_)) =>
      case (from, to) => super.emitEdge(from, to, attr, f_sub, t_sub)
    }
  }

}

class PIRCtxDotGen(val fileName:String)(implicit design:PIR) extends PIRIRDotGen {
  override def visitFunc(n:N) = n match {
    case n:Context => n.descendents.collect { case n:LocalAccess => n; case n:Access => n; case c:BlackBox => c }.toList
    case n => super.visitFunc(n)
  }
}

class PIRGlobalDotGen(val fileName:String, noBackEdge:Boolean=false)(implicit design:PIR) extends PIRIRDotGen {
  var backEdges = Set.empty[(Output[PIRNode], Input[PIRNode])]
  override def initPass = {
    super.initPass
    if (noBackEdge)
      backEdges = analyzeBackEdge
  }
  //override def fileName = pirTop.name.get + ".dot"
  //override def dirName = buildPath(config.appDir, "../figs")
  override def dotFile:String = fileName.replace(s".dot", s".html")

  override def color(attr:DotAttr, n:N) = n match {
    case n:ArgFringe => attr.setNode.fillcolor("beige").style(filled)
    case n:MemoryContainer => attr.setNode.fillcolor("limegreen").style(filled)
    case n:ComputeContainer if n.isDAG.get => attr.setNode.fillcolor("orange").style(filled)
    case n:ComputeContainer if n.collectDown[BlackBox]().nonEmpty => attr.setNode.fillcolor("crimson").style(filled)
    case n:ComputeContainer if n.collectDown[OpNode]().size==0 => attr.setNode.fillcolor("gold").style(filled)
    case n:ComputeContainer => attr.setNode.fillcolor("dodgerblue").style(filled)
    case n:DRAMFringe => attr.setNode.fillcolor("lightseagreen").style(filled)
    case n:BlackBoxContainer => attr.setNode.fillcolor("crimson").style(filled)
    case n => super.color(attr,n)
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
  
  override def emitEdge(from:EN[N], to:EN[N], attr:DotAttr, f_sub_in:String, t_sub_in:String):Unit = {
    if (noBackEdge && backEdges.contains(from->to)) return

    // TODO: specialize these to avoid false matches
    val f_sub_new = from match {
      case from@OutputField(fromsrc:GlobalOutput, _) if fromsrc.isExtern.get => {
        val from = fromsrc.in.T
        val from_ctx = from.collectUp[Context]().head.collectDown[SparseParBlock]().head
        val from_bb = from.as[BufferWrite].data.connected.head
        val (id,lane) = from_ctx.portMap(from_bb)

        // s"_EXTERN_${from}_${from_ctx}_${from_bb}_${id}_${lane}"
        s"_${id}_${lane}"
      }
      case _ => ""
    }
    val f_sub = s"${f_sub_new}${f_sub_in}"

    val t_sub_new = to match {
      case to@InputField(tosrc:GlobalInput, _) if tosrc.isExtern.get => {
        val to = tosrc.out.T.head
        val to_ctx = to.collectUp[Context]().head.collectDown[SparseParBlock]().head
        val to_bb = to.as[BufferRead].out.connected.head
        val (id,lane) = to_ctx.portMap(to_bb)

        // s"_EXTERN_${from}_${from_ctx}_${from_bb}_${id}_${lane}"
        s"_${id}_${lane}"
      }
      case _ => ""
    }
    val t_sub = s"${t_sub_new}${t_sub_in}"



    (from, to) match {
      case (from@OutputField(fromsrc:GlobalOutput, _), to) if fromsrc.isUnder[ArgFringe] && !config.enableDotArgIn => 
      case (from@OutputField(fromsrc:GlobalOutput, _), to@InputField(tosrc:GlobalInput, _)) => 
        var tooltip = s"${fromsrc}${fromsrc.externAlias.v.fold("") { a => s"($a)" }}"
        tooltip += s"\n${tosrc}${tosrc.externAlias.v.fold("") { a => s"($a)" }}"
        tooltip += s"\n${fromsrc.in.neighbors.map(quoteSrcCtx(_,"\n")).mkString(",")}"
        tooltip += s"\n${tosrc.out.neighbors.map(quoteSrcCtx(_,"\n")).mkString(",")}"
        fromsrc.count.v.foreach { c => 
          c match {
            case Finite(c) => tooltip += s"\ncount=$c"
            case Infinite => tooltip += s"\ncount=$c"
            case Symbol(c,syms) => tooltip += s"\ncount=[$c,$syms]"
            case Unknown =>
          }
        }
        tooltip += s"\ntp=${fromsrc.getTp}".append("vec", fromsrc.in.singleConnected.flatMap{ _.vecMeta.v })
        val dst = tosrc + "," + tosrc.out.neighbors.mkString(",")
        var edgeAttr = attr.setEdge.attr("id",dst).attr("label",fromsrc.id).attr("labeltooltip", tooltip)
        if (fromsrc.in.T.isSplit.get) {
          edgeAttr.color("orangered1")
        }
        super.emitEdge(from,to,edgeAttr, f_sub, t_sub)
      case (from,to) => super.emitEdge(from,to,attr.setEdge.attr("label",from.src.id), f_sub, t_sub)
    }
  }

  override def setAttrs(n:N):DotAttr = n match {
    case n:GlobalContainer =>
      val mem = n.collectDown[Memory]().map { quoteToolTip(_) }
      val bbs = n.collectDown[BlackBox]().map { quoteToolTip(_) }
      var tooltip = quoteToolTip(n)
      if (mem.nonEmpty) tooltip += s"\n${mem.mkString(",")}" 
      if (bbs.nonEmpty) tooltip += s"\n${bbs.mkString(",")}"
      val ctxs = n.collectDown[Context]()
      val ctxStr = ctxs.slice(0,3).map { ctx =>
        quoteToolTip(ctx)
      }
      tooltip += s"\n${ctxStr.mkString("\n")}${if(ctxs.size>3) "\n..." else ""}"
      super.setAttrs(n).setNode.attr("tooltip", tooltip)
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
        case n => n.getClass.getSimpleName
      }
      var l = s"${tp}${n.id}"
      n.name.v.foreach { name => l += s"\n$name" }
      val mem = n.collectDown[Memory]()
      mem.foreach { mem =>
        mem.name.v.foreach { name => l += s"\n$name" }
      }
      val bbs = n.collectDown[BlackBox]()
      bbs.foreach { bbs =>
        bbs.name.v.foreach { name => l += s"\n$name" }
        bbs.to[Splitter].foreach{ s => l += s"\n$s" }
        bbs.to[LockRMWBlock].foreach{ s => l += s"\n$s" }
      }
      if (mem.isEmpty && bbs.isEmpty) {
        n.collectDown[LocalOutAccess]().foreach { mem =>
          mem.name.v.foreach { name => l += s"\n$name" }
        }
      }
      l
      .append("delay", n.delay.v)
    }
  }

  override def emitNode(n:N) = n match {
    case n:Top => visitNode(n)
    case n:BlackBoxContainer if n.collectDown[SparseParBlock]().length != 0 => {
      assert(n.collectDown[SparseParBlock]().length == 1)
      val b = n.collectDown[SparseParBlock]().head
      b.readPorts.foreach { case (a, ports) =>
        (0 until ports.size).foreach { p =>
          emitSingleNode(n, s"_${a}_${p}")
        }
      }
      b.writePorts.foreach { case (a, ports) =>
        (0 until ports.size).foreach { p =>
          emitSingleNode(n, s"_${a}_${p}")
        }
      }
      b.rmwPorts.foreach { case (a, ports) =>
        (0 until ports.size).foreach { p =>
          emitSingleNode(n, s"_${a}_${p}")
        }
      }
      // emitSingleNode(n)
    }
    case n:GlobalContainer => emitSingleNode(n)
    case n => super.emitNode(n)
  }

}
