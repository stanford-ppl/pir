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

  //val updated = ListBuffer[Val[_]]()

  override def traverse = {
    spade.simulatable.foreach { _.register }
    vcd.foreach { _.emitSignals }
    cycle += 1
    dprintln(s"Starting simulation ...")
    while (!finishSimulation) {
      spade.simulatable.foreach { m => m.outs.foreach { o => o.v.update } }
      vcd.foreach { _.emitSignals }
      cycle += 1
      //updated.clear
      spade.simulatable.foreach { m => m.outs.foreach { o => o.v.clearUpdate } }
    }
  }

  override def finPass = {
    close
    vcd.foreach {_.close}
    super.finPass
  }

  //val uvMap = Map[IO[Bus,_<:Module], BusVal[_]]()
  //val wvMap = Map[IO[Word,_<:Module], WordVal]()
  //val bvMap = Map[IO[Bit,_<:Module], BitVal]()
  //def uv(io:IO[Bus, _<:Module]):BusVal[_] = uvMap.getOrElseUpdate(io, BusVal(io))
  //def wv(io:IO[Word, _<:Module]):WordVal = wvMap.getOrElseUpdate(io, WordVal(io))
  //def bv(io:IO[Bit, _<:Module]):BitVal = bvMap.getOrElseUpdate(io, BitVal(io))
  //def v(io:IO[_<:PortType, _<:Module]):Val[_] = io match {
    //case io if io.isBus => uv(io.asBus) 
    //case io if io.isWord => wv(io.asWord) 
    //case io if io.isBit => bv(io.asBit) 
  //}
  //def euv(io:IO[Bus, _<:Module]):BusVal[_] = {
    //val value = uvMap.getOrElse(io, throw PIRException(s"io=${io} io.src=${io.src} doesn't have val"))
    //value.update
    //value
  //}
  //def ewv(io:IO[Word, _<:Module]):WordVal = {
    //val value = wvMap.getOrElse(io, throw PIRException(s"io=${io} io.src=${io.src} doesn't have val"))
    //value.update
    //value
  //}
  //def ebv(io:IO[Bit, _<:Module]):BitVal = {
    //val value = bvMap.getOrElse(io, throw PIRException(s"io=${io} io.src=${io.src} doesn't have val"))
    //value.update
    //value
  //}
  //def ev(io:IO[_<:PortType, _<:Module]):Val[_] = io match {
    //case io if io.isBus => euv(io.asBus) 
    //case io if io.isWord => ewv(io.asWord) 
    //case io if io.isBit => ebv(io.asBit) 
  //}

}

trait Simulatable extends Module {
  spade.simulatable += this
  def register(implicit sim:Simulator):Unit = {
    import sim._
    val fimap = mapping.fimap
    ins.foreach { in =>
      fimap.get(in).foreach { out => in.v <= out.ev }
    }
  }
}

