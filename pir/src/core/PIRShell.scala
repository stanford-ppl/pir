package pir

import prism.graph._
import pir.node._
import pir.pass._
import pir.codegen._
import pir.mapper._
import prism.codegen._
import spade.codegen._

object PIRShell extends PIR with Logging {
  
  lazy val igraphGen = new IgraphCodegen()
  lazy val psimRunner = new PlastisimRunner()
  lazy val psimParser = new PlastisimLogParser()
  lazy val ctxMerging = new ContextMerging()

  override def loadSession = {
    import config._
    getArgOption[Boolean]("load").foreach { _.updateValue(true) }
    getArgOption[Boolean]("debug").foreach { _.updateValue(true) }
    getArgOption[Boolean]("dot").foreach { _.updateValue(true) }
    getArgOption[String]("out").foreach { _.updateValue("shell/") }
    val start = getArgOption[Int]("start-id").flatMap { _.getValue }.getOrElse(-1)
    super.loadSession
    getArgOption[Int]("start-id").foreach { _.updateValue(start) }
  }

  override def initSession = {
    super.initSession
    import config._

    // ------- Analysis and Transformations --------
    addPass(enableDot, new ControlTreeDotGen(s"ctop.dot"))
    addPass(enableDot, new ControlTreeHtmlIRPrinter(s"ctrl.html"))
    addPass(enableDot, new PIRCtxDotGen(s"simple.dot"))
    addPass(enableDot, new PIRIRDotGen(s"top.dot"))
    addPass(genPsim, ctxMerging)
    
    // ------- Codegen  --------
    //addPass(igraphGen)
    addPass(runPsim, psimRunner)
    addPass(loadPsim.nonEmpty, psimParser)
    addPass(loadPsim.nonEmpty, new PIRCtxDotGen(s"simple.dot"))

    //addPass(areaPowerStat).dependsOn(psimConfigCodegen, cuPlacer)

  }

}

