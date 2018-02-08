package pir.pass

import pir._
import pir.codegen._

import spade._
import spade.codegen._

class SpadeSimulator(implicit design: PIR) extends PIRPass {

  implicit val arch:Spade = design.arch

  def shouldRun = SpadeConfig.simulate //&& design.pirMapping.succeeded
  implicit lazy val simulator = new Simulator()(arch, design)
  override def reset = {
    super.reset
    simulator.reset
  }

  override def initPass = {
    super.initPass
    design match {
      case design:PIRApp => design.parseArgIns
      case _ =>
    }
    if (SpadeConfig.simulate && SpadeConfig.waveform) {
      //simulator.vcds += new PIRVcdPrinter(design.mapping.get)
      simulator.vcds += new SpadeVcdPrinter
    }
    //simulator.mapping = design.mapping.get
    simulator.initPass
  }

  override def runPass {
    simulator.run
  }

  override def finPass = {
    simulator.finPass
  }
}

