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

  ///* Analysis */
  lazy val controlPropogator = new ControlPropogation()
  lazy val psimAnalyzer = new PlastisimAnalyzer()
  //lazy val psimLinkAnalyzer = new PlastisimLinkAnalyzer()
  //lazy val psimCountCheck = new PlastisimCountCheck()
  //lazy val psimVCAllocator = new PlastisimVCAllocation()

  ///* Transformation */
  lazy val deadCodeEliminator = new DeadCodeElimination()
  lazy val memLowering = new MemoryLowering()
  lazy val contextAnalyzer = new ContextAnalyzer()
  lazy val contextInsertion = new ContextInsertion()
  lazy val depDuplications = new DependencyDuplication()
  lazy val validProp = new ValidConstantPropogation()
  lazy val bufferInsertion = new BufferInsertion()
  lazy val globalInsertion = new GlobalInsertion()
  lazy val routeThroughEliminator = new RouteThroughElimination()
  //lazy val globalPartitioner = new GlobalPartionerCake()
  //lazy val routeThroughEliminator = new RouteThroughElimination()
  //lazy val controlRegInsertion = new ControlRegInsertion()
  //lazy val controlAllocator = new ControlAllocation()
  //lazy val controlLowering = new ControlLowering()
  //lazy val localMemDuplication = new LocalMemDuplication()

  ///* Mapping */
  lazy val initializer = new TargetInitializer()
  //lazy val cuPruning = new CUPruning()
  //lazy val cuPlacer = new CUPlacer()
  lazy val hardPruner = new HardConstrainPruner()
  lazy val dagPruner = new DAGPruner()
  lazy val pmuPruner = new PMUPruner()
  lazy val matchPruner = new MatchPruner()
  lazy val placerAndRouter = new PlaceAndRoute()

  /* Codegen */
  lazy val tungstenPIRGen = new TungstenPIRGen()
  lazy val psimConfigGen = new PlastisimConfigGen()
  lazy val prouteLinkGen = new PlastirouteLinkGen()
  lazy val prouteNodeGen = new PlastirouteNodeGen()
  lazy val dramTraceGen = new DRAMTraceCodegen()
  //lazy val cuStats = new CUStatistics()
  //lazy val psimConfigCodegen = new PlastisimConfigCodegen()
  //lazy val psimPlacementCodegen = new PlastisimPlacementCodegen()
  //lazy val psimTraceCodegen = new PlastisimTraceCodegen()
  //lazy val psimDotCodegen = new PlastisimDotCodegen(s"psim.dot")
  //lazy val terminalCSVCodegen = new TerminalCSVCodegen()
  //lazy val linkCSVCodegen = new LinkCSVCodegen()
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
    addPass(validProp)
    addPass(routeThroughEliminator).dependsOn(validProp)
    addPass(enableDot, new PIRIRDotGen(s"top3.dot"))
    addPass(contextInsertion).dependsOn(routeThroughEliminator)
    addPass(enableDot, new PIRIRDotGen(s"top4.dot"))

    addPass(memLowering).dependsOn(contextInsertion)
    addPass(deadCodeEliminator)
    addPass(enableDot, new PIRIRDotGen(s"top5.dot"))
    addPass(depDuplications).dependsOn(memLowering)
    addPass(routeThroughEliminator).dependsOn(memLowering)
    addPass(deadCodeEliminator)
    addPass(contextAnalyzer)
    addPass(enableDot, new PIRIRDotGen(s"top6.dot"))
    addPass(enableDot, new PIRCtxDotGen(s"simple6.dot"))
    addPass(globalInsertion)
    
    saveSession

    addPass(initializer)
    addPass(genPsim, psimAnalyzer).dependsOn(initializer)
    addPass(genPsim, psimAnalyzer).dependsOn(initializer) // Need to run twice to account for cycle in data flow graph

    addPass(enableDot, new PIRCtxDotGen(s"simple7.dot"))
    addPass(enableDot, new PIRIRDotGen(s"top7.dot"))
    addPass(enableDot, new PIRNetworkDotGen(s"net.dot"))
    addPass(enableDot, new ParamHtmlIRPrinter(s"param.html", spadeParam))
    addPass(enableDot, new ControlTreeDotGen(s"ctop.dot"))

    // ------- Mapping  --------
    addPass(enableMapping, hardPruner)
    //addPass(enableMapping, pmuPruner).dependsOn(hardPruner)
    addPass(enableMapping, dagPruner).dependsOn(hardPruner)//.dependsOn(pmuPruner)
    addPass(enableMapping, matchPruner).dependsOn(dagPruner)
    addPass(enableMapping, placerAndRouter).dependsOn(matchPruner)

    //addPass(cuStats)
    
    // ------- Codegen  --------
    addPass(genTungsten, tungstenPIRGen)
    addPass(genPsim, prouteLinkGen).dependsOn(psimAnalyzer)
    addPass(genPsim, prouteNodeGen).dependsOn(psimAnalyzer)
    addPass(genPsim, psimConfigGen).dependsOn(psimAnalyzer)
    addPass(genPsim && runPsim, psimRunner).dependsOn(psimConfigGen)

    //addPass(areaPowerStat).dependsOn(psimConfigCodegen, cuPlacer)

  }

  def handle(e:Exception) = throw e

}
