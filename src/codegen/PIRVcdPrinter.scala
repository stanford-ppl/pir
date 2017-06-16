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

trait PIRVcdDeclarator { self:VcdPrinter =>
  import sim.util._
  val pirDeclarator:PIRVcdDeclarator = this

  private val _tracking = ListBuffer[PIO[PModule]]()
  def track(s:PIO[PModule]):Unit = _tracking += s
  def tracked(s:PIO[PModule]):Boolean = _tracking.contains(s)
  def tracking(declarator:PIRVcdDeclarator):Iterable[PIO[PModule]] = _tracking 
  private val declarator:Traversal = new Traversal {
    override def shouldRun = false
    override def visitNode (node:Node): Unit = {
      if (visited.contains(node)) return
      node match {
        case node:Controller => declare(node) { super.visitNodeNoCheck(node) }
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
        case node:UDCounter => declare(node) { 
          super.visitNode(node)
          declare(pmmap(node).count, None)
        }
        case node:CtrlBox => declare(node) { 
          //super.visitNode(node)
          declarator(spadeDeclarator).visitNode(pmmap(node))
        }
        case node@(_:OnChipMem|_:CounterChain|_:Stage|_:Delay) => 
          declare(node) { super.visitNode(node) }
        case _ => super.visitNodeNoCheck(node)
      }
    }
  } 
  def traverse(self:PIRVcdDeclarator):Unit = declarator.traverse

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
            emitkv(s"scope module", "cchains")
            n.cchains.foreach(declarator.visitNode)
            emitln(s"$$upscope $$end")
            emitkv(s"scope module", "mems")
            n.mems.foreach(declarator.visitNode)
            emitln(s"$$upscope $$end")
            emitkv(s"scope module", "stages")
            n.stages.foreach(declarator.visitNode)
            emitln(s"$$upscope $$end")
          case _ =>
        }
      case _ =>
    }
    finPass
    emitln(s"$$upscope $$end")
  }

}
class PIRVcdPrinter(implicit sim:Simulator, design: Design) extends VcdPrinter {
  override lazy val stream = newStream("sim_pir.vcd") 
  import sim.util._
  implicit def mapping:PIRMap = sim.mapping

  def declareAll = {
    addAll
    traverse(pirDeclarator)
  }

  def addAll = {
    adder.traverse
  }

  val adder = new Traversal {
    override def shouldRun = false
    override def visitNode (node:Node): Unit = {
      if (visited.contains(node)) return
      node match {
        case node:Controller => spadeDeclarator.track(clmap(node))
        case node:Counter => spadeDeclarator.track(ctmap(node))
        case node:CounterChain =>
        //case node:Stage => spadeDeclarator.track(stmap(node))
        case node:OnChipMem => spadeDeclarator.track(smmap(node))
        case node:Input => spadeDeclarator.track(vimap(node))
        case node:Output => spadeDeclarator.track(vomap(node))
        case node:InPort if node.isCtrlIn => spadeDeclarator.track(vimap(node))
        case node:OutPort if node.isCtrlOut => spadeDeclarator.track(vomap(node))
        case node:InPort => 
        case node:OutPort => 
        case node:Primitive if pmmap.contains(node) => spadeDeclarator.track(pmmap(node))
        case node =>
      }
      super.visitNode(node)
    }
  }

  def emitSignals = {
    emitTime
    tracking(pirDeclarator).foreach(emitValue)
  }

  override def quote(n:Any):String = {
    n match {
      case n:Node => super.quote(pir.util.quote(n))
      case n => super.quote(n) 
    }
  }

  override def declare(io:pir.plasticine.graph.IO[_<:pir.plasticine.graph.PortType, _<:PModule], prefix:Option[String]=None) = {
    track(io)
    super.declare(io, prefix)
  }

}
