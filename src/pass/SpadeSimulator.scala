package pir.pass

import pir._
import pir.spade.simulation.Simulator

class SpadeSimulator(implicit design: Design) extends Pass {

  def shouldRun = Config.simulate && design.pirMapping.succeeded
  lazy val simulator = new Simulator(design.mapping.get)

  override def reset = {
    super.reset
    simulator.reset
  }

  override def initPass = {
    super.initPass
    simulator.initPass
  }

  addPass {
    simulator.run
  }

  override def finPass = {
    simulator.finPass
  }
}

