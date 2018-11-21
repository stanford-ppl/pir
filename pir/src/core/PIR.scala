package pir

import pir.node._
import pir.pass._
import pir.codegen._
import pir.mapper._
import prism.codegen._

import spade.codegen._

trait PIR extends Compiler with PIREnv with PIRNodeUtil {

  override val logExtensions = super.logExtensions ++ List(".py", ".cluster")
  override lazy val config = new PIRConfig(this)

  /* Analysis and Transformations*/

  lazy val deadCodeEliminator = new DeadCodeElimination()
  lazy val memLowering = new MemoryLowering()
  lazy val contextAnalyzer = new ContextAnalyzer()
  lazy val contextInsertion = new ContextInsertion()
  lazy val depDuplications = new DependencyDuplication()
  lazy val validProp = new ValidConstantPropogation()
  lazy val bufferInsertion = new BufferInsertion()
  lazy val globalInsertion = new GlobalInsertion()
  lazy val routeThroughEliminator = new RouteThroughElimination()
  lazy val controlPropogator = new ControlPropogation()
  lazy val psimAnalyzer = new PlastisimAnalyzer()

  /* Mapping */
  lazy val initializer = new TargetInitializer()
  lazy val hardPruner = new HardConstrainPruner()
  lazy val softPruner = new SoftConstrainPruner()
  lazy val dagPruner = new DAGPruner()
  lazy val sramBankPruner = new SRAMBankPruner()
  lazy val sramCapPruner = new SRAMCapacityPruner()
  lazy val matchPruner = new MatchPruner()
  lazy val placerAndRouter = new PlaceAndRoute()

  /* Codegen */
  lazy val tungstenPIRGen = new TungstenPIRGen()
  lazy val psimConfigGen = new PlastisimConfigGen()
  lazy val prouteLinkGen = new PlastirouteLinkGen()
  lazy val prouteNodeGen = new PlastirouteNodeGen()
  lazy val dramTraceGen = new DRAMTraceCodegen()
  lazy val report = new ResourceReport()
  lazy val igraphGen = new IgraphCodegen()
  //lazy val areaPowerStat = new AreaPowerStat()
  
  /* Simulation */
  lazy val psimRunner = new PlastisimRunner()

  override def initSession = {
    super.initSession
    import config._

    // ------- Analysis and Transformations --------
    addPass(enableDot, new ControlTreeDotGen(s"ctop.dot"))
    addPass(enableDot, new ControlTreeHtmlIRPrinter(s"ctrl.html"))
    addPass(enableDot, new PIRIRDotGen(s"top1.dot"))
    addPass(deadCodeEliminator)
    addPass(enableTrace && genPsim, dramTraceGen)
    addPass(controlPropogator)
    addPass(enableDot, new PIRIRDotGen(s"top2.dot"))
    addPass(validProp) ==>
    addPass(routeThroughEliminator)
    addPass(enableDot, new PIRIRDotGen(s"top3.dot"))
    addPass(contextInsertion).dependsOn(routeThroughEliminator)
    addPass(enableDot, new PIRIRDotGen(s"top4.dot"))

    addPass(memLowering).dependsOn(contextInsertion) ==>
    addPass(deadCodeEliminator)
    addPass(enableDot, new PIRIRDotGen(s"top5.dot"))
    addPass(depDuplications).dependsOn(deadCodeEliminator)
    addPass(routeThroughEliminator) ==>
    addPass(deadCodeEliminator) ==>
    addPass(contextAnalyzer) ==>
    addPass(enableDot, new PIRIRDotGen(s"top6.dot"))
    addPass(enableDot, new PIRCtxDotGen(s"simple6.dot"))
    addPass(globalInsertion).dependsOn(contextAnalyzer)
    
    saveSession

    addPass(initializer)
    addPass(enableDot, new ControlTreeDotGen(s"ctop.dot"))
    addPass(config.debug, new ParamHtmlIRPrinter(s"param.html", spadeParam))
    addPass(enableDot, new PIRCtxDotGen(s"simple7.dot"))
    addPass(enableDot, new PIRIRDotGen(s"top7.dot"))

    // ------- Mapping  --------
    addPass(enableMapping, hardPruner).dependsOn(globalInsertion) ==>
    addPass(enableMapping, sramBankPruner) ==>
    addPass(enableMapping, sramCapPruner) ==>
    addPass(enableMapping, softPruner) ==>
    addPass(enableMapping, dagPruner) ==>
    addPass(enableMapping, matchPruner) ==>
    addPass(enableMapping, placerAndRouter) ==>
    addPass(genPsim, psimAnalyzer) ==>
    addPass(genPsim, psimAnalyzer) // Need to run twice to account for cycle in data flow graph
    addPass(enableDot, new PIRCtxDotGen(s"simple8.dot"))
    addPass(enableDot, new PIRIRDotGen(s"top8.dot"))
    addPass(enableDot, new PIRNetworkDotGen(s"net.dot"))
    addPass(enableMapping,report).dependsOn(placerAndRouter)
    
    // ------- Codegen  --------
    addPass(igraphGen).dependsOn(psimAnalyzer)
    addPass(genTungsten, tungstenPIRGen).dependsOn(psimAnalyzer)
    addPass(genPsim, prouteLinkGen).dependsOn(psimAnalyzer)
    addPass(genPsim, prouteNodeGen).dependsOn(placerAndRouter, psimAnalyzer)
    addPass(genPsim, psimConfigGen).dependsOn(psimAnalyzer, prouteLinkGen) ==>
    addPass(genPsim && runPsim, psimRunner)

    //addPass(areaPowerStat).dependsOn(psimConfigCodegen, cuPlacer)

  }

  def handle(e:Throwable):Try[Boolean] = Failure(e)

}
