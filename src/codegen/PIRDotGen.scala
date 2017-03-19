package pir.graph.traversal

import pir.{Design, Config}
import pir.codegen._
import pir.misc._
import pir.graph._
import pir.mapper.PIRMap

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Set
import scala.collection.mutable.Map
import scala.collection.mutable.HashMap
import java.io.File
import scala.reflect.runtime.universe._

trait PIRDotGen extends Traversal with DotCodegen {

  val design:Design

  def traverse:Unit = {
    emitBlock("digraph G") {
      emitNode(design.top)
      design.top.ctrlers.foreach { cl => emitInputs (cl) }
    }
  }

  override def finPass = {
    endInfo(s"Finishing ${this.getClass.getSimpleName}")
    close
  }

  def emitNode(cl:Controller):Unit = {
    cl match {
      case top:Top => 
        emitSubGraph(top, top) {
          emitNode(top, top, DotAttr().shape(box).style(dashed))
          top.children.foreach { cu => emitNode(cu) }
        }
      case cu:OuterController =>
        emitSubGraph(cu, cu) {
          emitNode(cu, cu, DotAttr().shape(box).style(dashed))
          cu.children.foreach { cu => emitNode(cu) }
        }
      case cu:MemoryPipeline =>
        emitNode(cu, cu, DotAttr().shape(box).style(filled).fillcolor(cyan))
      case cu:InnerController =>
        emitNode(cu, cu, DotAttr().shape(box).style(rounded))
    }
  }

  def emitInputs(cl:Controller):Unit

  def emitDataInputs(cl:Controller) = {
    cl.sinMap.foreach { case (s, sin) => 
      s.writer.ctrler match {
        case top:Top =>
        case w => emitEdge(w, cl, DotAttr().label(s"$s"))
      }
    }
    cl.vinMap.foreach { case (v, vin) => 
      val label = v match {
        case dv:DummyVector => s"$v[\n${dv.scalars.mkString(",\n")}]"
        case _ => s"$v"
      }
      emitEdge(v.writer.ctrler, cl, DotAttr().label(label).style(bold))
    }
  }

  def emitCtrlInputs(cl:Controller):Unit = {
    def q(cp:CtrlPort) = cp.src match {
      case cb:CtrlBox => 
        val attrs = s"${cp}".split("\\.")
        if (attrs.size==2)
          s"${cb.ctrler.name.getOrElse(cb.ctrler.toString)}.${attrs(1)}"
        else
          s"${cp}"
      case _ => s"${cp}"
    }
    val ins = cl.ctrlBox.ctrlIns
    ins.groupBy(_.from).foreach { case (from, cis) =>
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

}

class PIRDataDotGen(fileName:String)(implicit val design:Design) extends PIRDotGen { 

  override val stream = newStream(fileName)

  def this()(implicit design:Design) = {
    this(Config.pirDot)
  }

  def emitInputs(cl:Controller):Unit = {
    emitDataInputs(cl)
    //emitCtrlInputs(cl)
  }

}


class PIRCtrlDotGen(fileName:String)(implicit val design:Design) extends PIRDotGen { 

  override val stream = newStream(fileName)

  def this()(implicit design:Design) = {
    this(Config.pirCtrlDot)
  }

  def emitInputs(cl:Controller):Unit = {
    emitCtrlInputs(cl)
  }

}
