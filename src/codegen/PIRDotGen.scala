package pir.graph.traversal

import pir.{Design, Config}
import pir.codegen._
import pir.misc._
import pir.graph._
import pir.graph.mapper.PIRMap

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Set
import scala.collection.mutable.Map
import scala.collection.mutable.HashMap
import java.io.File
import scala.reflect.runtime.universe._

class PIRNetworkDotGen(fileName:String)(implicit design:Design) extends Traversal with DotCodegen { 
  override val stream = newStream(fileName)
  def this()(implicit design:Design) = {
    this(Config.pirNetworkDot)
  }

  def traverse:Unit = {
    emitBlock("digraph G") {
      emitNodes
    }
  }

  def finPass = {
    close
  }

  def emitInputs(cl:Controller) = {
    //cl.sinMap.foreach { case (s, sin) => emitEdge(s.writer.ctrler, cl, s"$s")}
    cl.vinMap.foreach { case (v, vin) => 
      val label = v match {
        case dv:DummyVector => s"$v[\n${dv.scalars.mkString(",\n")}]"
        case _ => s"$v"
      }
      emitEdge(v.writer.ctrler, cl, label)
    }
  }

  def emitNodes:Unit = {
    emitController(design.top)
    design.top.ctrlers.foreach { cl => emitInputs (cl) }
  }

  def emitController(cl:Controller):Unit = PIRNetworkDotGen.emitController(cl)(this)
  // Not used
  def emitNodes(cus:List[ComputeUnit]) = {
    cus.foreach { _ match {
        case cu:InnerController =>
          emitNode(cu, cu, DotAttr().shape(box).style(rounded))
          cu.sinMap.foreach { case (s, sin) => emitEdge(s.writer.ctrler, cu, s"$s")}
          cu.vinMap.foreach { case (v, vin) => emitEdge(v.writer.ctrler, cu, s"$v")}
        case cu:OuterController =>
          cu.sinMap.foreach { case (s, sin) => 
            emitEdge(s.writer.ctrler, cu.inner, s"$s")
          }
        //TODO
        //case top:Top => emitNode(top, top, DotAttr().shape(box).style(rounded))
        //case mc:MemoryController => emitNode(mc, mc, DotAttr().shape(box).style(rounded))
      } 
    }
  }

}

object PIRNetworkDotGen {
  def emitController(cl:Controller)(implicit printer:DotCodegen):Unit = {
    import printer._
    cl match {
      case top:Top => 
        emitSubGraph(top, top) {
          emitNode(top, top, DotAttr().shape(box).style(dashed))
          top.children.foreach { cu => emitController(cu) }
        }
      case cu:OuterController =>
        emitSubGraph(cu, cu) {
          cu.children.foreach { cu => emitController(cu) }
        }
      case cu:InnerController =>
        cu match {
          case tt:TileTransfer => emitController(tt.memctrl)
          case _ =>
        }
        emitSubGraph(cu, DotAttr().label(cu).style(rounded)) {
          cu.locals.foreach { cu =>
            emitNode(cu, cu, DotAttr().shape(box).style(dashed))
          }
        }
      case mc:MemoryController => 
        emitNode(mc, mc, DotAttr().shape(box).style(dashed))
    }
  }
}

class PIRCtrlNetworkDotGen(fileName:String)(implicit design:Design) extends Traversal with DotCodegen { 
  override val stream = newStream(fileName)
  def this()(implicit design:Design) = {
    this(Config.pirCtrlNetworkDot)
  }

  def traverse:Unit = {
    emitBlock("digraph G") {
      emitNodes
    }
  }

  def finPass = {
    close
  }

  def emitNodes:Unit = {
    emitController(design.top)
    design.top.ctrlers.foreach { cl => emitInputs (cl) }
  }

  def emitController(cl:Controller):Unit = PIRNetworkDotGen.emitController(cl)(this)

  def emitInputs(cl:Controller) = {
    val cins = cl match {
      case top:Top => top.ctrlIns
      case cu:ComputeUnit => cu.ctrlBox.getCtrlIns
      case mc:MemoryController => mc.ctrlIns
    }
    cins.foreach { ci =>
      val fromcu = ci.from.src match {
        case p:Primitive => p.ctrler
        case cu => cu
      }
      val label = s"from:${ci.from}\nto:$ci"
      emitEdge(fromcu, cl, label)
    }
  }

}
