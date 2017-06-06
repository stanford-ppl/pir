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

class Simulator(implicit design: Design) extends Pass with Logger {

  def shouldRun = Config.simulate && design.mapping.nonEmpty
  implicit val sim:Simulator = this
  val vcds:List[VcdPrinter] = if (Config.simulate) List(new PIRVcdPrinter, new SpadeVcdPrinter) else Nil

  override def debug = Config.verbose

  lazy val mapping = design.mapping.get
  var inSimulation = false 

  override lazy val stream = newStream("sim.log") 

  val period = 1; //ns per cycle
  var cycle = 0
  var rst = false
  def finishSimulation:Boolean = {
    spade.top.ctrlBox.status.vAt(3).isHigh.getOrElse(false) || cycle >= 50
  } 

  override def initPass = {
    vcds.foreach { vcds => 
      vcds.emitHeader
    }
    super.initPass
    tic
  }

  override def traverse = {
    dprintln(s"\n\nRegistering update functions ...")
    spade.simulatable.foreach { s => s.register; s.check }
    info(s"# ios simulated: ${spade.simulatable.map(_.ios.size).sum}")
    dprintln(s"\n\nDefault values ...")
    vcds.foreach { _.emitSignals }
    cycle += 1
    dprintln(s"\n\nStarting simulation ...")
    inSimulation = true
    while (!finishSimulation) {
      rst = if (cycle == 1) true else false
      spade.simulatable.foreach { m => m.updateModule }
      vcds.foreach { _.emitSignals }
      spade.simulatable.foreach { m => m.clearModule }
      cycle += 1
    }
    inSimulation = false
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
        s"${n.typeStr}[${n.index}]".replace(s"${pir.plasticine.util.quote(n.pne)}", quote(n.pne))
      case n:NetworkElement => coordOf.get(n).fold(s"$n") { case (x,y) => s"${n.typeStr}[$x,$y]" }
      case n:IO[_,_] =>  
        s"${pir.plasticine.util.quote(n)}".replace(s"${pir.plasticine.util.quote(n.src)}", quote(n.src))
      case n:Value if n.parent.nonEmpty => s"${quote(n.parent.get)}.$n"
      case n:PortType => s"${quote(n.io)}.$n"
      case n:Node => pir.plasticine.util.quote(n)
      case n => s"$n"
    }
  }

}

