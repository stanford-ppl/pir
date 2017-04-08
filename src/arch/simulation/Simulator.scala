package pir.plasticine.simulation

import pir._
import pir.mapper.PIRMap
import pir.codegen.Logger
import pir.pass.Pass
import pir.plasticine.main._
import pir.plasticine.graph._
import pir.plasticine.traversal._
import pir.exceptions.PIRException

import scala.collection.mutable.Map
import scala.collection.mutable.ListBuffer

class Simulator(implicit design: Design) extends Pass with Logger {

  def shouldRun = Config.simulate && design.mapping.nonEmpty
  implicit val sim:Simulator = this
  val vcd = if (Config.simulate) Some(new VcdPrinter(this)) else None

  lazy val spade = design.arch
  lazy val mapping = design.mapping.get

  override lazy val stream = newStream("sim.log") 

  val period = 1; //ns per cycle
  var cycle = 0
  def finishSimulation:Boolean = {
    cycle >= 10
  } 

  override def initPass = {
    vcd.foreach { vcd => 
      vcd.addAll
      vcd.emitHeader
    }
    super.initPass
  }


  override def traverse = {
    dprintln(s"Registering update functions ...")
    spade.simulatable.foreach { _.register }
    dprintln(s"Default values ...")
    vcd.foreach { _.emitSignals }
    cycle += 1
    dprintln(s"Starting simulation ...")
    while (!finishSimulation) {
      spade.simulatable.foreach { m => m.ios.foreach { o => o.v.update } }
      vcd.foreach { _.emitSignals }
      spade.simulatable.foreach { m => m.ios.foreach { o => o.v.clearUpdate } }
      cycle += 1
    }
  }

  override def finPass = {
    close
    vcd.foreach {_.close}
    super.finPass
  }
}

