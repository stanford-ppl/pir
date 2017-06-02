package pir.codegen

import pir._
import pir.mapper.PIRMap
import pir.pass.Pass
import pir.plasticine.main._
import pir.plasticine.graph._
import pir.plasticine.traversal._
import pir.plasticine.util.{quote => _, _}
import pir.util.misc._
import pir.exceptions.PIRException
import pir.plasticine.simulation._

import scala.collection.mutable.Map
import scala.collection.mutable.ListBuffer

class SpadeVcdPrinter(implicit sim:Simulator, design: Design) extends VcdPrinter {
  override lazy val stream = newStream("sim.vcd") 
  import sim.quote
  import sim.mapping._ 
  implicit def mapping:PIRMap = sim.mapping

  val tracking = ListBuffer[Simulatable]()

  def declareAll = {
    addAll
    declarator.traverse
  }

  val declarator = new Traversal {
    override def visitNode (node:Node): Unit = {
      if (visited.contains(node)) return
      node match {
        case node:ComputeUnit => declare(node) {
          declare("srams") { node.srams.foreach(visitNode) }
          declare("ctrs") { node.ctrs.foreach(visitNode) }
          declare("sbufs") { node.sbufs.foreach(visitNode) }
          declare("vbufs") { node.vbufs.foreach(visitNode) }
          declare("stages") { node.stages.foreach(visitNode) }
          super.visitNode(node)
        }
        case node:CtrlBox => declare(node) { super.visitNode(node) }
        case node:Primitive if (!tracking.contains(node)) => super.visitNode(node)
        case node:Module => declare(node) { super.visitNode(node) }
        case _ => super.visitNode(node)
      }
    }
    override def traverse(implicit spade: Spade):Unit = {
      visitNode(spade.top)
      declare("pcus") { spade.pcus.foreach(visitNode) }
      declare("mcus") { spade.mcus.foreach(visitNode) }
      declare("scus") { spade.scus.foreach(visitNode) }
      declare("ocus") { spade.ocus.foreach(visitNode) }
      declare("mcs") { spade.mcs.foreach(visitNode) }
      spade match {
        case spade:SwitchNetwork => 
          declare("sbs") { spade.sbs.foreach(visitNode) }
        case _ =>
      }
    } 
  } 

  def declare(m:Module)(finPass: => Unit):Unit = {
    emitkv(s"scope module", s"${quote(m)}")
    m match {
      case m:NetworkElement if tracking.contains(m) =>
        emitkv(s"scope module", "sio")
        m.scalarIO.ios.foreach { io => declare(io) }
        emitln(s"$$upscope $$end")
        emitkv(s"scope module", "vio")
        m.vectorIO.ios.foreach { io => declare(io) }
        emitln(s"$$upscope $$end")
        emitkv(s"scope module", "cio")
        m.ctrlIO.ios.foreach { io => declare(io) }
        emitln(s"$$upscope $$end")
      case m:Simulatable if tracking.contains(m) => m.ios.foreach { io => declare(io) }
      case _ =>
    }
    finPass
    emitln(s"$$upscope $$end")
  }

  def emitSignals = {
    emitTime
    tracking.foreach { m =>
      m.ios.foreach { io => emitValue(io) }
    }
  }

  val adder = new Traversal {
    override def visitNode (node:Node): Unit = {
      node match {
        case node:GlobalIO[_,_] =>
        case node:Simulatable if !tracking.contains(node) && isMapped(node) => 
          sim.dprintln(s"tracking ${sim.quote(node)}")
          tracking += node
        case _ =>
      }
      super.visitNode(node)
    }
  } 

  def addModule(m:Simulatable) = {
    adder.visitNode(m)
  }

  def addAll = {
    sim.dprintln(s"${sim.mapping.ctmap.keys}")
    adder.traverse
    val sb = spade.asInstanceOf[SwitchNetwork].sbs.head
    val out = sb.ctrlIO.outs.head
  }

}
