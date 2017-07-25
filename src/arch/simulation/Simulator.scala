package pir.plasticine.simulation

import pir._
import pir.mapper.PIRMap
import pir.codegen.{Logger,VcdPrinter, SpadeVcdPrinter, PIRVcdPrinter}
import pir.pass.Pass
import pir.util.misc._
import pir.util.PIRMetadata
import pir.plasticine.main._
import pir.plasticine.graph._
import pir.plasticine.traversal._

import scala.collection.mutable.Map
import scala.collection.mutable.ListBuffer

trait SimUtil extends Logger {
  def quote(n:Any):String
  implicit def mapping:PIRMap
  def fimap = mapping.fimap
  def clmap = mapping.clmap
  def pmmap = mapping.pmmap
  def vimap = mapping.vimap
  def vomap = mapping.vomap
  def stmap = mapping.stmap
  def ipmap = mapping.ipmap
  def opmap = mapping.opmap
  def smmap = mapping.smmap
  def ctmap = mapping.ctmap
  def rcmap = mapping.rcmap
  def rtmap = mapping.rtmap
  def pirmeta:PIRMetadata
  def rst:Boolean
  def cycle:Int
}

class Simulator(implicit design: Design) extends Pass with Logger with SimUtil {

  def shouldRun = Config.simulate && design.pirMapping.succeeded
  implicit val sim:Simulator = this
  lazy val vcds:List[VcdPrinter] = 
    if (Config.simulate && Config.waveform) List(new PIRVcdPrinter, new SpadeVcdPrinter) else Nil

  override def debug = Config.verbose

  implicit lazy val mapping = design.mapping.get
  lazy val util:SimUtil = this

  var _inSimulation = false 
  def inSimulation = _inSimulation
  var _inRegistration = false 
  def inRegistration = _inRegistration

  override lazy val stream = newStream("sim.log") 

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

  override def reset = {
    super.reset
    rst = false
    timeOut = false
    done = false
    _inSimulation = false
    _inRegistration = false
    cycle = 0
    spade.simulatable.foreach { m => m.reset }
  }

  override def initPass = {
    vcds.foreach { vcds => vcds.emitHeader }
    super.initPass
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

  addPass {
    register
    dprintln(s"\n\nDefault values ...")
    vcds.foreach { _.emitSignals }
    cycle += 1
    simulate
  }

  override def finPass = {
    close
    vcds.foreach { _.close }
    super.finPass
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
        s"${n.typeStr}[${n.index}]".replace(s"${pir.plasticine.util.quote(n.prt)}", quote(n.prt))
      case n:Routable => coordOf.get(n).fold(s"$n") { case (x,y) => s"${n.typeStr}[$x,$y]" }
      case n:IO[_,_] =>  
        var q = pir.plasticine.util.quote(n).replace(pir.plasticine.util.quote(n.src), quote(n.src))
        n.src match {
          case n:Primitive => q = q.replace(pir.plasticine.util.quote(n.prt), quote(n.prt))
          case _ =>
        }
        q
      case n:Value if n.parent.nonEmpty => s"${quote(n.parent.get)}.$n"
      case n:PortType => s"${quote(n.io)}.$n"
      case n:Node => pir.plasticine.util.quote(n)
      case n => s"$n"
    }
  }

}

