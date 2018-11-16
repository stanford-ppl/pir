package pir

import pir.node._
import pir.pass._
import pir.codegen._
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

  /* Codegen */
  lazy val tungstenPIRGen = new TungstenPIRGen()
  lazy val psimConfigGen = new PlastisimConfigGen()
  lazy val prouteLinkGen = new PlastirouteLinkGen()
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

    addPass(genPsim, psimAnalyzer) // Depends on LoadArch
    addPass(genPsim, psimAnalyzer) // Need to run twice to account for cycle in data flow graph

    // ------- Pruning and Partitioning  --------
    // ------- Mapping  --------
    addPass(enableMapping, hardPruner)

    addPass(enableDot, new PIRCtxDotGen(s"simple7.dot"))
    addPass(enableDot, new PIRIRDotGen(s"top7.dot"))
    addPass(new NetworkDotCodegen(s"net.dot", spadeTop))
    addPass(new ParamHtmlIRPrinter(s"param.html", spadeParam))

    addPass(genTungsten, tungstenPIRGen)
    addPass(genPsim, prouteLinkGen).dependsOn(psimAnalyzer)
    addPass(genPsim, psimConfigGen).dependsOn(psimAnalyzer)
    addPass(genPsim && runPsim, psimRunner).dependsOn(psimConfigGen)

    //addPass(cuStats)

    //addPass(enableSplitting, globalPartitioner)
    //addPass(enableSplitting && enableDot, new PIRIRDotCodegen(s"top7.dot"))
    //addPass(enableSplitting && enableDot, new SimpleIRDotCodegen(s"simple2.dot"))

    //addPass(enableDot, new ControllerDotCodegen(s"controller1.dot"))
    //addPass(deadCodeEliminator).dependsOn(routeThroughEliminator)
    //addPass(enableDot, new ControllerDotCodegen(s"controller2.dot")).dependsOn(controlPropogator)
    //addPass(enableDot, new PIRIRDotCodegen(s"top8.dot"))
    //addPass(enableDot, new SimpleIRDotCodegen(s"simple3.dot")).dependsOn(routeThroughEliminator)
    //addPass(debug, new PIRPrinter(s"IR2.txt"))
    //addPass(irCheck).dependsOn(routeThroughEliminator)

    //addPass(genCtrl, contextInsertion).dependsOn(globalPartitioner)
    //addPass(genCtrl && enableDot, new PIRIRDotCodegen(s"top9.dot")).dependsOn(contextInsertion)

    //// Control transformation and analysis
    ////addPass(genCtrl, localMemDuplication)
    //addPass(genCtrl, memoryAnalyzer).dependsOn(contextInsertion)
    //addPass(genCtrl, controlAllocator).dependsOn(contextInsertion)  // set accessDoneOf, duplicateCounterChain for accessDoneOf
    //addPass(genCtrl, controlRegInsertion).dependsOn(controlAllocator) // insert reg for no forward depended contextEn
    //addPass(genCtrl, memoryAnalyzer).dependsOn(controlRegInsertion)
    //addPass(genCtrl && enableDot, new PIRIRDotCodegen(s"top10.dot"))
    //addPass(genCtrl, controlAllocator).dependsOn(memoryAnalyzer) // set accessDoneOf, duplicateCounterChain for accessDoneOf
    //addPass(genCtrl, deadCodeEliminator) // Remove redundant counterChains
    //addPass(genCtrl && enableDot, new PIRIRDotCodegen(s"top11.dot"))
    //addPass(genCtrl, controlLowering).dependsOn(controlAllocator) // Lower ContextEnableOut to ConectEnable
    //addPass(genCtrl && enableDot, new PIRIRDotCodegen(s"top12.dot"))
    //addPass(genCtrl, irCheck)

    //addPass(enableDot, new ControllerPrinter(s"controller.txt"))
    //addPass(cuStats)
    //addPass(enableTrace, psimTraceCodegen).dependsOn(controlLowering)
    //addPass(psimLinkAnalyzer).dependsOn(controlLowering)
    //addPass(psimCountCheck).dependsOn(psimLinkAnalyzer)

    //session.rerun {

    //// Simulation analyzer
    //addPass(psimDotCodegen).dependsOn(psimLinkAnalyzer)
    //addPass(new ControllerDotCodegen(s"controller.dot"))
    //addPass(new PIRIRDotCodegen(s"top.dot"))
    //addPass(new SimpleIRDotCodegen(s"simple.dot"))
    //addPass(debug, new PIRPrinter(s"IR.txt"))

    //// Mapping
    ////addPass(genPlastisim, psimVCAllocator).dependsOn(psimLinkAnalyzer)
    //addPass(cuPruning)
    //addPass(enableMapping, cuPlacer).dependsOn(cuPruning)

    //// Post-enableMapping analysis
    //addPass(cuStats).dependsOn(cuPlacer)
    //addPass(enableMapping, new PIRNetworkDotCodegen[Bit](s"control.dot")).dependsOn(cuPlacer)
    //addPass(enableMapping, new PIRNetworkDotCodegen[Word](s"scalar.dot")).dependsOn(cuPlacer)
    //addPass(enableMapping, new PIRNetworkDotCodegen[Vector](s"vector.dot")).dependsOn(cuPlacer)

    //// Codegen
    //addPass(genPlastisim, terminalCSVCodegen).dependsOn(cuPlacer)
    //addPass(genPlastisim, linkCSVCodegen).dependsOn(terminalCSVCodegen, psimLinkAnalyzer)
    //addPass(genPlastisim && enableMapping, psimPlacementCodegen).dependsOn(cuPlacer,linkCSVCodegen)
    //addPass(genPlastisim, psimDotCodegen).dependsOn(psimLinkAnalyzer)
    //addPass(genPlastisim && enableMapping, psimConfigCodegen).dependsOn(cuPlacer, psimPlacementCodegen, psimTraceCodegen)
    //addPass(runPlastisim, psimDotCodegen)

    //addPass(areaPowerStat).dependsOn(psimConfigCodegen, cuPlacer)

    //}
  }

  def handle(e:Exception) = throw e

}
