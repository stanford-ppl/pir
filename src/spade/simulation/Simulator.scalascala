package pir.spade.simulation

import pir._
import pir.mapper.PIRMap
import pir.codegen.{Logger,VcdPrinter, SpadeVcdPrinter, PIRVcdPrinter}
import pir.pass.Pass
import pir.util.misc._
import pir.util.PIRMetadata
import pir.spade.main._
import pir.spade.node._
import pir.spade.traversal._
import pir.spade.util.SpadeMetadata

import scala.collection.mutable.Map
import scala.collection.mutable.ListBuffer

trait SimUtil extends Logger {
  def quote(n:Any):String
  implicit def mapping:PIRMap
  def fimap = mapping.fimap
  def pmmap = mapping.pmmap
  def vimap = mapping.vimap
  def vomap = mapping.vomap
  def ipmap = mapping.ipmap
  def opmap = mapping.opmap
  def rtmap = mapping.rtmap
  def rcmap = mapping.rcmap
  def cfmap = mapping.cfmap
  def rst:Boolean
  def cycle:Int
}

class Simulator()(implicit design:PIR) extends SimUtil with Logger {

  var mapping:PIRMap = _
  implicit def spade = design.arch
  val spademeta:SpadeMetadata = spade
  implicit val sim:Simulator = this
  lazy val vcds:List[VcdPrinter] = 
    if (Config.simulate && Config.waveform) List(new PIRVcdPrinter, new SpadeVcdPrinter) else Nil

  override def debug = Config.verbose

  lazy val util:SimUtil = this

  var _inSimulation = false 
  def inSimulation = _inSimulation
  var _inRegistration = false 
  def inRegistration = _inRegistration

  override lazy val stream = newStream("sim.log")(design)

  val period = 1; //ns per cycle
  var cycle = 0
  var rst = false

  var timeOut = false
  var done = false

  def finishSimulation:Boolean = {
    if (spade.top.ctrlBox.status.vAt(3).isHigh.getOrElse(false)) { done = true; true }
    else if (cycle >= Config.simulationTimeOut) { 
      timeOut = true
      warn(s"Simulation time out at #${cycle}!")
      true 
    }
    else false
  } 

  def reset = {
    rst = false
    timeOut = false
    done = false
    _inSimulation = false
    _inRegistration = false
    cycle = 0
    spade.simulatable.foreach { m => m.reset }
  }

  def initPass = {
    vcds.foreach { vcds => vcds.emitHeader }
    tic
  }

  def register = {
    dprintln(s"\n\nRegistering update functions ...")
    _inRegistration = true
    spade.simulatable.foreach { s => s.registerAll; s.zeroModule }
    spade.simulatable.foreach { s => s.updateModule; }
    spade.simulatable.foreach { s => s.clearModule; s.zeroModule }
    _inRegistration = false
    spade.simulatable.foreach { s => s.check }
    info(s"# ios simulated: ${spade.simulatable.map(_.ios.size).sum}")
  }

  def simulate = {
    dprintln(s"\n\nStarting simulation ...")
    _inSimulation = true
    while (!finishSimulation) {
      rst = if (cycle == 1) true else false
      spade.simulatable.foreach(_.updateModule)
      vcds.foreach(_.emitSignals)
      spade.simulatable.foreach(_.clearModule)
      cycle += 1
    }
    _inSimulation = false
  }

  def run(mapping:PIRMap) = {
    this.mapping = mapping
    register
    dprintln(s"\n\nDefault values ...")
    vcds.foreach { _.emitSignals }
    cycle += 1
    simulate
  }

  def finPass = {
    close
    vcds.foreach { _.close }
    toc("Simulation","s")
  }

  override def quote(n:Any):String = {
    import spademeta._
    n match {
      case n:Stage => s"st[${n.index}]"
      case n:FuncUnit => s"${quote(n.stage)}.$n"
      case PipeReg(stage, reg) => s"${quote(stage)}.${quote(reg)}"
      case n:ArchReg => s"reg[${n.index}]"
      case n:Primitive if indexOf.get(n).nonEmpty => 
        s"${n.typeStr}[${n.index}]".replace(s"${pir.spade.util.quote(n.prt)}", quote(n.prt))
      case n:Routable => coordOf.get(n).fold(s"$n") { case (x,y) => s"${n.typeStr}[$x,$y]" }
      case n:IO[_,_] =>  
        var q = pir.spade.util.quote(n).replace(pir.spade.util.quote(n.src), quote(n.src))
        n.src match {
          case n:Primitive => q = q.replace(pir.spade.util.quote(n.prt), quote(n.prt))
          case _ =>
        }
        q
      case n:Value if n.parent.nonEmpty => s"${quote(n.parent.get)}.$n"
      case n:PortType => s"${quote(n.io)}.$n"
      case n:Node => pir.spade.util.quote(n)
      case n => s"$n"
    }
  }

}

