package pir.codegen

import pir._
import pir.node._
import pirc.util._

import sys.process._
import scala.language.postfixOps

trait PIRDotGen extends Codegen with DotCodegen {

  def horizontal:Boolean

  def design:PIR

  addPass {
    emitBlock("digraph G") {
      if (horizontal) emitln(s"rankdir=LR")
      emitNode(design.top)
      design.top.ctrlers.foreach { cl => emitInputs (cl) }
    }
  }

  def open = {
    s"out/bin/run ${getPath} &".replace(".dot", "") !
  }

  override def finPass = {
    super.finPass
    close
  }

  def emitNode(cl:Controller):Unit = {
    cl match {
      case top:Top => 
        emitSubGraph(top, top) {
          emitNode(top,DotAttr().shape(box).style(dashed).label(quote(top)))
          top.children.foreach { cu => emitNode(cu) }
        }
      case cu:OuterController =>
        emitSubGraph(cu, cu) {
          emitNode(cu,DotAttr().shape(box).style(dashed).label(quote(cu)))
          cu.children.foreach { cu => emitNode(cu) }
        }
      case cu:MemoryPipeline =>
        emitNode(cu, DotAttr().shape(box).style(filled).fillcolor(cyan).label(quote(cu)))
      case cu:InnerController =>
        emitNode(cu, DotAttr().shape(box).style(rounded).label(quote(cu)))
    }
  }

  def emitInputs(cl:Controller):Unit

  def emitGlobalInputs(cl:Controller, gins:Iterable[GlobalInput]):Unit = {
    gins.foreach { in => 
      val attr = DotAttr()
      val v = in.variable
      var label = s"${v.name.get}"
      if (!v.writerIsEmpty) label += s"\n(${v.writer.ctrler}.${v.writer})"
      label += s"\n(${v.readers.filter{_.ctrler==cl}.map{r => s"${cl}.$r"}.mkString(",")})"
      attr.label(label)
      v match {
        case v:Vector => attr.style(bold)
        case v:Scalar =>
        case v:Control => attr.style(dashed)
      }
      if (!v.writerIsEmpty) {
        v.writer.ctrler match {
          case top:Top =>
          case w => emitEdge(w, cl, attr)
        }
      } else {
        emitEdge("NotConnected", cl, attr)
      }
    }
  }

  def emitCtrlInputs(cl:Controller, cins:Iterable[Input]):Unit = {
    def q(cp:IO) = cp.src match {
      case cb:CtrlBox => 
        val attrs = s"${cp}".split("\\.")
        if (attrs.size==2)
          s"${cb.ctrler.name.getOrElse(cb.ctrler.toString)}.${attrs(1)}"
        else
          s"${cp}"
      case _ => s"${cp}"
    }
    cins.groupBy(_.from).foreach { case (from, cis) =>
      if (from != null) {
        val fromcu = from.src match {
          case p:Primitive => p.ctrler
          case cu => cu
        }
        val fromlb = from.src match {
          //case ctr:Counter => s"${fromcu.name.getOrElse(fromcu.toString)}.done"
          case cb:CtrlBox => q(from)
          case _ => s"${from}"
        }
        val tolb = cis.mkString(",\n")
        val label = s"from:$fromlb\nto:$tolb"
        emitEdge(fromcu, cl, DotAttr().label(label).style(dashed))
      }
    }
  }

  override def quote(n:Any):String = {
    super[DotCodegen].quote(n)
  }
}

class PIRDataDotGen(fn:String)(implicit design:PIR) extends PIRDotGen { 
  import pirmeta._

  design.pirDataDotGens += this

  def shouldRun = PIRConfig.debug

  def horizontal:Boolean = false

  override lazy val stream = newStream(fn)

  def this()(implicit design:PIR) = {
    this(PIRConfig.pirDot)
  }

  def emitInputs(cl:Controller):Unit = {
    emitGlobalInputs(cl, (cl.sins ++ cl.vins).collect { case in:GlobalInput => in } )
  }

  override def quote(n:Any):String = {
    n match {
      case n:Controller => 
        val pcl = design.mapping.fold("") { mp => mp.pmmap.get(n).fold(""){ pcl => s"\n${quote(pcl)}" } }
        val head = if (isHead.get(n)==Some(true)) s"\n(HEAD)" else ""
        val last = if (isLast.get(n)==Some(true)) s"\n(LAST)" else ""
        val streaming = if (isStreaming.get(n)==Some(true)) s"\n(Streaming)" else ""
        val pipelining = if (isPipelining.get(n)==Some(true)) s"\n(Pipelining)" else ""
        val par = parOf.get(n).fold("") { p => s"\n(par=$p)" }
        val rpar = rparOf.get(n).fold("") { p => s"\n(rpar=$p)" }
        val wpar = wparOf.get(n).fold("") { p => s"\n(wpar=$p)" }
        val iter = iterOf.get(n).fold("") { c => s"\n(Iter=$c)"}
        val cycle = cycleOf.get(n).fold("") { c => s"\n(Cycle=$c)"}
        val totalCycle = totalCycleOf.get(n).fold("") { c => s"\n(TotalCycle=$c)"}
        val buffering = n match {
          case n:MemoryPipeline =>
            s"\n(buffering=${n.sram.buffering})"
          case n => ""
        }
        s"${super.quote(n)}$pcl$head$last$streaming$pipelining$par$rpar$wpar$buffering$iter$cycle$totalCycle"
      case n => super.quote(n)
    }
  }

}


class PIRCtrlDotGen(fn:String)(implicit design:PIR) extends PIRDotGen { 
  def shouldRun = PIRConfig.debug & PIRConfig.ctrl

  def horizontal:Boolean = true
  //def horizontal:Boolean = false

  override lazy val stream = newStream(fn)

  def this()(implicit design:PIR) = {
    this(PIRConfig.pirCtrlDot)
  }

  def emitInputs(cl:Controller):Unit = {
    emitCtrlInputs(cl, cl.cins)
    emitGlobalInputs(cl, cl.gins)
  }

}
