package pir.pass

import pir._
import pir.codegen._

import spade._
import spade.codegen._

import prism._

class SpadeSimulator(implicit compiler:PIR) extends PIRPass {

  implicit val arch:Spade = compiler.arch

  def shouldRun = SpadeConfig.simulate //&& compiler.pirMapping.succeeded
  //implicit lazy val simulator = new Simulator()(arch, compiler)
  override def reset = {
    super.reset
    //simulator.reset
  }

  override def initPass(runner:RunPass[_]) = {
    super.initPass(runner)
    compiler match {
      case compiler:PIRApp => compiler.parseArgIns
      case _ =>
    }
    //if (SpadeConfig.simulate && SpadeConfig.waveform) {
      ////simulator.vcds += new PIRVcdPrinter(compiler.mapping.get)
      //simulator.vcds += new SpadeVcdPrinter
    //}
    ////simulator.mapping = compiler.mapping.get
    //simulator.initPass
  }

  override def runPass {
    //simulator.run
  }

  override def finPass(runner:RunPass[_]) = {
    super.finPass(runner)
  }
}

