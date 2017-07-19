package pir.codegen

import pir.{Design, Config}
import pir.codegen._
import pir.graph._
import pir.mapper.PIRMap
import pir.util.misc._

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Set
import scala.collection.mutable.Map
import scala.collection.mutable.HashMap
import java.io.File
import scala.reflect.runtime.universe._

trait PIRDotGen extends Codegen with DotCodegen {

  def horizontal:Boolean

  def design:Design

  addPass {
    emitBlock("digraph G") {
      if (horizontal) emitln(s"rankdir=LR")
      emitNode(design.top)
      design.top.ctrlers.foreach { cl => emitInputs (cl) }
    }
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

  def emitVectorInputs(cl:Controller, vins:List[VecIn]):Unit = {
    vins.foreach { vin => 
      val v = vin.vector
      val label = v match {
        case dv:DummyVector => s"$v[\n${dv.scalars.mkString(",\n")}]"
        case _ => s"$v\n(${v.writer})\n(${v.readers.filter{_.ctrler==cl}.mkString(",")})"
      }
      if (v.writer!=null)
        emitEdge(v.writer.ctrler, cl, DotAttr().label(label).style(bold))
    }
  }

  def emitScalarInputs(cl:Controller, sins:List[ScalarIn]):Unit = {
    sins.foreach { sin => 
      val s = sin.scalar
      val label = s"$s\n(${s.writer})\n(${s.readers.filter{_.ctrler==cl}.mkString(",")})"
      if (!s.writerIsEmpty) {
        s.writer.ctrler match {
          case top:Top =>
          case w => emitEdge(w, cl, DotAttr().label(s"$s"))
        }
      } else {
        emitEdge("NotConnected", cl, DotAttr().label(s"$s"))
      }
    }
  }

  def emitCtrlInputs(cl:Controller, cins:List[CtrlInPort]):Unit = {
    def q(cp:CtrlPort) = cp.src match {
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
          case cb:CtrlBox => q(from.asInstanceOf[CtrlPort])
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

class PIRDataDotGen(fn:String)(implicit design:Design) extends PIRDotGen { 
  import pirmeta._

  def shouldRun = Config.debug

  def horizontal:Boolean = false

  override lazy val stream = newStream(fn)

  def this()(implicit design:Design) = {
    this(Config.pirDot)
  }

  def emitInputs(cl:Controller):Unit = {
    emitScalarInputs(cl, cl.sins)
    emitVectorInputs(cl, cl.vins)
  }

  override def quote(n:Any):String = {
    n match {
      case n:Controller => 
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
        s"${super.quote(n)}$head$last$streaming$pipelining$par$rpar$wpar$iter$cycle$totalCycle"
      case n => super.quote(n)
    }
  }

}


class PIRCtrlDotGen(fn:String)(implicit design:Design) extends PIRDotGen { 
  def shouldRun = Config.debug & Config.ctrl

  def horizontal:Boolean = true
  //def horizontal:Boolean = false

  override lazy val stream = newStream(fn)

  def this()(implicit design:Design) = {
    this(Config.pirCtrlDot)
  }

  def emitInputs(cl:Controller):Unit = {
    emitCtrlInputs(cl, cl.cins)
    emitVectorInputs(cl, cl.vins)
  }

}
