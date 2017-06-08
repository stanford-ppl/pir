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
import scala.language.existentials
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
        case io:Input =>
          visited += node
          val pio = vimap(io)
          declare(pio, Some(s"${quote(io)}@"))
        case io:Output =>
          visited += node
          vomap(io).foreach { pio => declare(pio, Some(s"${quote(io)}@")) }
        case io:InPort =>
          visited += node
          val pio = if (io.isCtrlIn) vimap.get(io) else ipmap.get(io)
          pio.foreach { pio => declare(pio, Some(s"${quote(io)}@")) }
        case io:OutPort =>
          visited += node
          if (io.isCtrlOut) vomap(io).foreach { pio => declare(pio, Some(s"${quote(io)}@")) }
          else opmap.get(io).foreach { _.foreach { pio => declare(pio, Some(s"${quote(io)}@")) } }
        case node@(_:OnChipMem|_:CtrlBox|_:Delay) => declare(node) { super.visitNode(node) }
        case _ => super.visitNodeNoCheck(node)
      }
    }
  } 

  def declare(n:Node)(finPass: => Unit):Unit = {
    val qt = n match {
      case n:Controller => s"${quote(n)}@${quote(clmap(n))}"
      case n:OnChipMem => s"${quote(n)}@${quote(smmap(n))}"
      case n:Primitive if pmmap.contains(n) => s"${quote(n)}@${quote(pmmap(n))}"
      case n => s"${quote(n)}"
    } 
    emitkv(s"scope module", s"$qt")
    n match {
      case n:Controller =>
        emitkv(s"scope module", "sio")
        (n.sins ++ n.souts).foreach { io => declarator.visitNode(io) }
        emitln(s"$$upscope $$end")
        emitkv(s"scope module", "vio")
        (n.vins ++ n.vouts).foreach { io => declarator.visitNode(io) }
        emitln(s"$$upscope $$end")
        emitkv(s"scope module", "cio")
        (n.cins ++ n.couts).foreach { io => declarator.visitNode(io) }
        emitln(s"$$upscope $$end")
        declarator.visitNode(n.ctrlBox)
        n match {
          case n:ComputeUnit =>
            emitkv(s"scope module", "mems")
            n.mems.foreach(declarator.visitNode)
            emitln(s"$$upscope $$end")
          case _ =>
        }
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
