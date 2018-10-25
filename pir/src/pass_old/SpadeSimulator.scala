package pir
package pass

class SpadeSimulator(implicit compiler:PIR) extends PIRPass {

  implicit val arch:Spade = compiler.arch

  //implicit lazy val simulator = new Simulator()(arch, compiler)
  override def reset = {
    super.reset
    //simulator.reset
  }

  override def initPass = {
    super.initPass
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

}

