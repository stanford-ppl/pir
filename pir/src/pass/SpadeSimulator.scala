package pir.pass

import pir._
import pir.codegen._

import spade._
import spade.codegen._

class SpadeSimulator(implicit design: PIR) extends Pass {

  def shouldRun = SpadeConfig.simulate && design.pirMapping.succeeded
  implicit lazy val simulator = new Simulator()

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
      simulator.vcds += new PIRVcdPrinter(design.mapping.get)
      simulator.vcds += new SpadeVcdPrinter
    }
    simulator.mapping = design.mapping.get
    simulator.initPass
  }

  addPass {
    simulator.run
  }

  override def finPass = {
    simulator.finPass
  }
}

