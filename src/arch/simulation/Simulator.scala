package pir.plasticine.simulation

import pir._
import pir.mapper.PIRMap
import pir.codegen.Logger
import pir.pass.Pass
import pir.util.PIRMetadata
import pir.plasticine.main._
import pir.plasticine.graph._
import pir.plasticine.traversal._

import scala.collection.mutable.Map
import scala.collection.mutable.ListBuffer

class Simulator(implicit design: Design) extends Pass with Logger {

  def shouldRun = Config.simulate && design.mapping.nonEmpty
  implicit val sim:Simulator = this
  val vcd = if (Config.simulate) Some(new VcdPrinter) else None

  override def debug = Config.verbose

  lazy val mapping = design.mapping.get
  var inSimulation = false 

  override lazy val stream = newStream("sim.log") 

  val period = 1; //ns per cycle
  var cycle = 0
  var rst = false
  def finishSimulation:Boolean = {
    cycle >= 20
  } 

  override def initPass = {
    vcd.foreach { vcd => 
      vcd.addAll
      vcd.emitHeader
    }
    super.initPass
  }


  override def traverse = {
    dprintln(s"\n\nRegistering update functions ...")
    spade.simulatable.foreach { s => s.register; s.check }
    dprintln(s"\n\nDefault values ...")
    vcd.foreach { _.emitSignals }
    cycle += 1
    dprintln(s"\n\nStarting simulation ...")
    inSimulation = true
    while (!finishSimulation) {
      rst = if (cycle == 1) true else false
      spade.simulatable.foreach { m => m.ios.foreach { o => o.update } }
      vcd.foreach { _.emitSignals }
      spade.simulatable.foreach { m => m.ios.foreach { o => o.clearUpdate } }
      cycle += 1
    }
    inSimulation = false
  }

  override def finPass = {
    close
    vcd.foreach { _.close }
    super.finPass
  }

  override def quote(n:Any):String = {
    import spademeta._
    n match {
      case PipeReg(stage, reg) => s"pr(${quote(stage)},${quote(reg)})"
      case n:ArchReg => s"$n"
      case n:Primitive if indexOf.get(n).nonEmpty => s"${n.typeStr}[${n.index}]"
      case n:NetworkElement => coordOf.get(n).fold(s"$n") { case (x,y) => s"${n.typeStr}[$x,$y]" }
      case n:GlobalIO[_,_] => 
        s"${quote(n.src)}.${n.typeStr}${indexOf.get(n).fold("") { idx => s"[$idx]" }}"
      case n:IO[_,_] if n.src.isInstanceOf[GlobalIO[_,_]] => 
        s"${quote(n.src)}.${n.typeStr}${indexOf.get(n).fold("") { idx => s"[$idx]" }}"
      case n:PortType => s"${quote(n.io)}.$n"
      case n:Node => pir.plasticine.util.quote(n)
      case n => s"$n"
    }
  }

}

