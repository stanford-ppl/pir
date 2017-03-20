package pir.plasticine.simulation

import pir._
import pir.mapper.PIRMap
import pir.codegen.Printer
import pir.graph.traversal._
import pir.plasticine.main._
import pir.plasticine.graph._

import scala.collection.mutable.Map
import scala.collection.mutable.ListBuffer

class Simulator(implicit design: Design) extends Traversal with Printer {
  implicit val sim:Simulator = this
  lazy val spade = design.arch
  lazy val mapping = design.mapping

  override val stream = newStream("sim.log") 

  val period = 1; //ns per cycle
  var cycle = 0
  def finishSimulation:Boolean = {
    cycle == 10
  } 

  val updated = ListBuffer[Val[_,_]]()
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

  val busValueMap = Map[Bus, BusVal]()
  val portValueMap = Map[Port, PortVal]()
  val wireValueMap = Map[Wire, WireVal]()
  def v(io:IO[_<:LinkType, _<:Module]) = io match {
    case io:Bus => busValueMap(io)
    case io:Port => portValueMap(io)
    case io:Wire => wireValueMap(io)
  }
  def ev(io:IO[_<:LinkType, _<:Module]) = {
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
      case io:Bus => busValueMap += io -> BusVal(io.asInstanceOf[IO[Bus, Module]])
      case io:Port => portValueMap += io -> PortVal(io.asInstanceOf[IO[Port, Module]])
      case io:Wire => wireValueMap += io -> WireVal(io.asInstanceOf[IO[Wire, Module]])
    }
    val fbmap = mapping.fbmap
    ins.foreach {
      case in:InBus[_] => 
        fbmap.get(in).foreach { ob =>
          v(in).set{ sim => sim.ev(ob).value }
        }
      case _ =>
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
          (0 until io.asInstanceOf[Bus].busWidth).foreach { i =>
          }
        case v@PortVal(io) =>
        case v@WireVal(io) =>
      }
    }
    emitln(s"$$upscope $end")
  }

}
