package pir.plasticine.simulation

import pir._
import pir.mapper.PIRMap
import pir.codegen.Printer
import pir.pass._
import pir.plasticine.main._
import pir.plasticine.graph._

import scala.collection.mutable.Map
import scala.collection.mutable.ListBuffer

class Simulator(implicit design: Design) extends Pass with Printer {
  implicit val sim:Simulator = this
  lazy val spade = design.arch
  lazy val mapping = design.mapping

  override val stream = newStream("sim.log") 

  val period = 1; //ns per cycle
  var cycle = 0
  def finishSimulation:Boolean = {
    cycle == 10
  } 

  val updated = ListBuffer[Val[_]]()
  override def traverse = {
    spade.simulatable.foreach { _.register }
    while (finishSimulation) {
      spade.simulatable.foreach { m => m.outs.foreach { o => v(o).update } }
      cycle += 1
    }
    updated.clear
  }

  override def finPass = {
    close
    super.finPass
  }

  val valMap = Map[IO[_<:PortType,_<:Module], Val[_]]()
  def v(io:IO[_<:PortType, _<:Module]):Val[_] = valMap(io)
  def ev(io:IO[_<:PortType, _<:Module]):Val[_] = {
    val value = v(io)
    if (!updated.contains(io)) value.update
    value
  }
}

trait Simulatable extends Module {
  spade.simulatable += this
  def register(implicit sim:Simulator):Unit = {
    import sim._
    ios.foreach {
      case io:Bus => valMap += io -> BusVal(io.asBus)
      case io:Word => valMap += io-> WordVal(io.asWord)
      case io:Bit => valMap += io -> BitVal(io.asBit)
    }
    val fimap = mapping.fimap
    ins.foreach { in =>
      fimap.get(in).foreach { out =>
        v(in).set{ sim => sim.ev(out).value }
      }
    }
  }
}

class VcdPrinter(sim:Simulator) extends Printer {
  override val stream = newStream("sim.vcd") 

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
  }

  def emitModule(m:Simulatable) = {
    emitkv(s"module", s"$m")
    m.ios.foreach { io =>
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
            case Word(wordWidth) if wordWidth < 32 =>
              emitVar("integer", wordWidth, s"$io", s"$io")
          } 
        case v@BitVal(io) =>
          val Bit() = io.tp
          emitVar("wire", 1, s"$io", s"$io")
      }
    }
    emitln(s"$$upscope $end")
  }

}
