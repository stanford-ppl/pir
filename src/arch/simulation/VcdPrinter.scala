package pir.plasticine.simulation

import pir._
import pir.mapper.PIRMap
import pir.codegen.Printer
import pir.pass.Pass
import pir.plasticine.main._
import pir.plasticine.graph._
import pir.plasticine.traversal._
import pir.exceptions.PIRException
import pir.plasticine.util._

import scala.collection.mutable.Map
import scala.collection.mutable.ListBuffer

class VcdPrinter(sim:Simulator)(implicit design: Design) extends Printer {
  override lazy val stream = newStream("sim.vcd") 
  implicit lazy val spade:Spade = design.arch

  val tracking = ListBuffer[Simulatable]()

  def end = {
    emitln(s"$$end")
  }

  def emitkv(key:String, value:String) = {
    emitln(s"$$$key  $value  $$end")
  }

  def emitVar(tpe:String, bitWidth:Int, id:String, name:String) = {
    emitkv("var", s"$tpe $bitWidth $id $name")
  }

  def emitHeader = {
    val now = java.util.Calendar.getInstance().getTime() 
    val format = new java.text.SimpleDateFormat("MMM dd yyyy, hh:mm:ss")
    emitkv("date", format.format(now))
    emitkv("version", "Spade Simulator")
    emitkv("timescale", s"${sim.period} ns")
    emitModules
    emitln("enddefinitions $end")
    emitln("$dumpvars")
    emitSignals
  }

  val declarator = new Traversal {
    override def visitNode (node:Node): Unit = {
      node match {
        case node:Primitive =>
        case node:Module => emitModule(node)
        case _ =>
      }
      super.visitNode(node)
    }
    override def traverse(implicit spade: Spade):Unit = {
      visitNode(spade.top)
      emitkv(s"scope module", "pcus")
      spade.pcus.foreach(visitNode)
      emitln(s"$$upscope $$end")
      emitkv(s"scope module", "mcus")
      spade.mcus.foreach(visitNode)
      emitln(s"$$upscope $$end")
      emitkv(s"scope module", "scus")
      spade.scus.foreach(visitNode)
      emitln(s"$$upscope $$end")
      emitkv(s"scope module", "ocus")
      spade.ocus.foreach(visitNode)
      emitln(s"$$upscope $$end")
      emitkv(s"scope module", "mcs")
      spade.mcs.foreach(visitNode)
      emitln(s"$$upscope $$end")
    } 
  } 

  def emitModules = {
    declarator.traverse
  }

  def emitModule(m:Module) = {
    emitkv(s"scope module", s"${quote(m)}")
    m match {
      case m:NetworkElement if tracking.contains(m) =>
        emitkv(s"scope module", "sio")
        m.scalarIO.ios.foreach { io => emitIO(io) }
        emitln(s"$$upscope $$end")
        emitkv(s"scope module", "vio")
        m.vectorIO.ios.foreach { io => emitIO(io) }
        emitln(s"$$upscope $$end")
        emitkv(s"scope module", "cio")
        m.ctrlIO.ios.foreach { io => emitIO(io) }
        emitln(s"$$upscope $$end")
      case m:Simulatable if tracking.contains(m) => m.ios.foreach { io => emitIO(io) }
      case _ =>
    }
    emitln(s"$$upscope $$end")
  }

  def emitIO(io:IO[_<:PortType,_<:Module]) = {
    sim.v(io) match {
      case v@BusVal(io) =>
        io.tp match {
          case Bus(busWidth, Word(wordWidth)) =>
            (0 until busWidth).foreach { i => emitVar("integer", wordWidth, s"${io}_$i", s"${io}_$i") }
          case Bus(busWidth, Bit()) =>
            emitVar("wire", busWidth, s"$io", s"$io")
        }
      case v@WordVal(io) =>
        io.tp match {
          case Word(wordWidth) if wordWidth == 1 =>
            emitVar("wire", wordWidth, s"$io", s"$io")
          case Word(wordWidth) if wordWidth <= 32 =>
            emitVar("integer", wordWidth, s"$io", s"$io")
        } 
      case v@BitVal(io) =>
        val Bit() = io.tp
        emitVar("wire", 1, s"$io", s"$io")
    }
  }

  def emitTime = {
    emitln(s"#${sim.cycle}")
  }
  def emitSignals = {
    emitTime
    tracking.foreach { m =>
      m.ios.foreach { io =>
        sim.v(io) match {
          case v@BusVal(io) =>
            io.tp match {
              case Bus(busWidth, Word(wordWidth)) =>
                val value:Array[Option[Float]] = v.value
                value.zipWithIndex.foreach { case (vv, i) => emitln(s"${vv.getOrElse("x")}${io}_$i") }
              case Bus(busWidth, Bit()) =>
                var value = "b"
                v.value.foreach { vv => value += vv.getOrElse("x") }
                emitln(s"$value $io")
            }
          case v@WordVal(io) =>
            emitln(s"${v.value.getOrElse("x")}${io}")
          case v@BitVal(io) =>
            emitln(s"${v.value.getOrElse("bx")} ${io}")
        }
      }
    }
  }

  val adder = new Traversal {
    override def visitNode (node:Node): Unit = {
      node match {
        case node:Simulatable if !tracking.contains(node) && isMapped(node) => tracking += node
        case _ =>
      }
      super.visitNode(node)
    }
  } 

  def addModule(m:Simulatable) = {
    adder.visitNode(m)
  }

  def addAll = {
    adder.traverse
  }

}
