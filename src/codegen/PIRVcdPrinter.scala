package pir.codegen

import pir._
import pir.mapper.PIRMap
import pir.pass.Pass
import pir.plasticine.main._
import pir.pass.Traversal
import pir.graph._
import pir.plasticine.util.{quote => _, _}
import pir.util.misc._
import pir.util.typealias._ 
import pir.exceptions.PIRException
import pir.plasticine.simulation._

import scala.language.reflectiveCalls
import scala.collection.mutable.Map
import scala.collection.mutable.ListBuffer

class PIRVcdPrinter(implicit sim:Simulator, design: Design) extends VcdPrinter {
  override lazy val stream = newStream("sim_pir.vcd") 
  import sim.quote
  import sim.mapping._ 
  implicit def mapping:PIRMap = sim.mapping

  val tracking = ListBuffer[PIO[PModule]]()

  def declareAll = {
    declarator.traverse
  }

  val declarator = new Traversal {
    override def shouldRun = false
    override def visitNode (node:Node): Unit = {
      if (visited.contains(node)) return
      node match {
        case node:Controller => declare(node) {
          super.visitNodeNoCheck(node)
        }
        case io@(_:Input | _:CtrlInPort) =>
          visited += node
          val pio = vimap(io)
          declare(pio, Some(s"${quote(io)}@"))
        case io@(_:Output | _:CtrlOutPort) =>
          visited += node
          vomap(io).foreach { pio =>
            declare(pio, Some(s"${quote(io)}@"))
          }
        case _ => super.visitNodeNoCheck(node)
      }
    }
  } 

  def declare(c:Node)(finPass: => Unit):Unit = {
    emitkv(s"scope module", s"${quote(c)}")
    c match {
      case c:Controller =>
        emitkv(s"scope module", "sio")
        (c.sins ++ c.souts).foreach { io => declarator.visitNode(io) }
        emitln(s"$$upscope $$end")
        emitkv(s"scope module", "vio")
        (c.vins ++ c.vouts).foreach { io => declarator.visitNode(io) }
        emitln(s"$$upscope $$end")
        emitkv(s"scope module", "cio")
        (c.cins ++ c.couts).foreach { io => declarator.visitNode(io) }
        emitln(s"$$upscope $$end")
      case _ =>
    }
    finPass
    emitln(s"$$upscope $$end")
  }

  override def declare(io:pir.plasticine.graph.IO[_<:pir.plasticine.graph.PortType, _<:PModule], prefix:Option[String]=None) = {
    tracking += io
    super.declare(io, prefix)
  }

  def emitSignals = {
    emitTime
    tracking.foreach(emitValue)
  }

}
