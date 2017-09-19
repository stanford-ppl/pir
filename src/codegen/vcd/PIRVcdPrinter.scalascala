package pir.codegen

import pir._
import pir.mapper.PIRMap
import pir.spade.main._
import pir.pass.Traversal
import pir.node._
import pir.spade.util.{quote => _, _}
import pir.util.misc._
import pir.util.typealias._ 
import pir.util.PIRMetadata
import pir.exceptions.PIRException
import pir.spade.simulation._

import scala.language.reflectiveCalls
import scala.language.existentials
import scala.collection.mutable.Map
import scala.collection.mutable.ListBuffer

trait PIRVcdDeclarator { self:PIRVcdPrinter =>
  import sim.util.{quote => _, _}
  val pirDeclarator:PIRVcdDeclarator = this

  private val _tracking = ListBuffer[PIO[PModule]]()
  def track(s:PIO[PModule]):Unit = if (!_tracking.contains(s)) _tracking += s
  def tracked(s:PIO[PModule]):Boolean = _tracking.contains(s)
  def tracking(declarator:PIRVcdDeclarator):Iterable[PIO[PModule]] = _tracking 
  private val declarator:Traversal = new Traversal {
    override def shouldRun = false
    override def visitNode (node:Node): Unit = {
      if (visited.contains(node)) return
      node match {
        case node:Controller => declare(node) { super.visitNodeNoCheck(node) }
        case n:Variable =>
          visited += node
          val pio = vomap(n.writer).head
          declare(pio, Some(s"$n"))
        case io:Input =>
          visited += node
          val pio = vimap(io)
          declare(pio, Some(s"${self.quote(io)}"))
        case io:Output =>
          visited += node
          val pio = vomap(io).head
          declare(pio, Some(s"${self.quote(io)}"))
        case io:InPort =>
          visited += node
          val pio = if (io.isGlobal) vimap.get(io) else ipmap.get(io)
          pio.foreach { pio => declare(pio, Some(s"${self.quote(io)}")) }
        case io:OutPort =>
          visited += node
          if (io.isGlobal) vomap(io).foreach { pio => declare(pio, Some(s"${self.quote(io)}")) }
          else opmap.get(io).foreach { pios => declare(pios.head, Some(s"${self.quote(io)}")) }
        case node:UDCounter => declare(node) { 
          super.visitNode(node)
          declare(pmmap(node).count, None)
        }
        case node@(_:CtrlBox) => declare(node) { 
          declarator(spadeDeclarator).visitNode(pmmap(node))
          super.visitNode(node)
        }
        case node@(_:OnChipMem) => declare(node) { 
          declarator(spadeDeclarator).visitNode(pmmap(node))
          super.visitNode(node)
        }
        case node@(_:CounterChain|_:Stage) => 
          declare(node) { super.visitNode(node) }
        case node:Delay =>
          visitNode(node.out)
        case _ => super.visitNodeNoCheck(node)
      }
    }
  } 
  def traverse(self:PIRVcdDeclarator):Unit = declarator.traverse

  def declare(n:Node)(finPass: => Unit):Unit = {
    val qt = n match {
      //case n:Controller => s"${quote(n)}@${quote(pmmap(n))}"
      //case n:OnChipMem => s"${quote(n)}@${quote(pmmap(n))}"
      //case n:Primitive if pmmap.contains(n) => s"${quote(n)}@${quote(pmmap(n))}"
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
          case n:Top =>
            //emitkv(s"scope module", "scalars")
            //n.scalars.foreach { n => declarator.visitNode(n) }
            //emitln(s"$$upscope $$end")
            //emitkv(s"scope module", "vectors")
            //n.vectors.foreach { n => declarator.visitNode(n) }
            //emitln(s"$$upscope $$end")
          case _ =>
        }
      case _ =>
    }
    finPass
    emitln(s"$$upscope $$end")
  }

}
class PIRVcdPrinter(implicit sim:Simulator, val design: PIR) extends VcdPrinter with PIRVcdDeclarator {
  override lazy val stream = newStream("sim_pir.vcd") 
  import sim.util._
  val pirmeta:PIRMetadata = design
  implicit def mapping:PIRMap = sim.mapping

  def declareAll = {
    traverse(pirDeclarator)
  }

  override def tracked(s:Simulatable):Boolean = true 

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

  override def declare(io:pir.spade.node.IO[_<:pir.spade.node.PortType, _<:PModule], prefix:Option[String]=None) = {
    track(io)
    super.declare(io, prefix)
  }

}
