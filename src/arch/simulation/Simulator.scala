package pir.plasticine.simulation

import pir._
import pir.mapper.PIRMap
import pir.codegen.Printer
import pir.pass.Pass
import pir.plasticine.main._
import pir.plasticine.graph._
import pir.plasticine.traversal._
import pir.exceptions.PIRException

import scala.collection.mutable.Map
import scala.collection.mutable.ListBuffer

class Simulator(implicit design: Design) extends Pass with Printer {

  def shouldRun = Config.simulate && design.mapping.nonEmpty
  implicit val sim:Simulator = this
  val vcd = if (Config.simulate) Some(new VcdPrinter(this)) else None

  val spade = design.arch
  lazy val mapping = design.mapping.get

  override lazy val stream = newStream("sim.log") 

  val period = 1; //ns per cycle
  var cycle = 0
  def finishSimulation:Boolean = {
    cycle == 10
  } 

  override def initPass = {
    vcd.foreach { vcd => 
      vcd.addAll
      vcd.emitHeader
    }
    super.initPass
  }

  val updated = ListBuffer[Val[_]]()
  override def traverse = {
    spade.simulatable.foreach { _.register }
    while (finishSimulation) {
      spade.simulatable.foreach { m => m.outs.foreach { o => v(o).update } }
      cycle += 1
    }
    updated.clear
    vcd.foreach { _.emitSignals }
  }

  override def finPass = {
    close
    vcd.foreach {_.close}
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
        val vin = v(in)
        if (vin.isV(v(out))) throw PIRException(s"Cannot eval $in (tp=${v(in).tp}) to $out (tp=${v(out).tp})")
        vin.set{ sim => sim.ev(out).value }
      }
    }
  }
}

