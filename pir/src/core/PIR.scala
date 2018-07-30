package pir

import pir.node._
import pir.pass._
import pir.mapper._
import pir.codegen._
import PIRConfig._

trait PIR extends Compiler with PIRWorld {

  def top:Top = design.top
  def pirmeta:PIRMetadata = design.pirmeta
  def spademeta:SpadeMetadata = arch.design.spademeta
  def designP:PIRDesign = design

  override val logExtensions = super.logExtensions ++ List(".py", ".cluster")

  val configs = List(Config, SpadeConfig, PIRConfig)

  var arch:Spade = _

  override def reset = {
    super.reset
    arch = null
  }

  /* Analysis */
  lazy val testTraversal = new TestTraversal()
  lazy val memoryAnalyzer = new MemoryAnalyzer()
  lazy val controlPropogator = new ControlPropogation()
  lazy val controllerRuntimeAnalyzer = new ControllerRuntimeAnalyzer()
  lazy val psimLinkAnalyzer = new PlastisimLinkAnalyzer()
  lazy val psimCountCheck = new PlastisimCountCheck()
  lazy val psimVCAllocator = new PlastisimVCAllocation()
  lazy val irCheck = new IRCheck()

  /* Transformation */
  lazy val fringeElaboration = new FringeElaboration()
  lazy val constantExpressionEvaluator = new ConstantExpressionEvaluation()
  lazy val deadCodeEliminator = new DeadCodeElimination()
  lazy val unrollingTransformer = new UnrollingTransformer()
  lazy val cuInsertion = new CUInsertion()
  lazy val accessPuller = new AccessPulling()
  lazy val accessLowering = new AccessLowering()
  lazy val bankedAccessMerging = new BankedAccessMerging()
  lazy val igraphPartioner = new IgraphPartioner()
  lazy val routeThroughEliminator = new RouteThroughElimination()
  lazy val contextInsertion = new ContextInsertion()
  lazy val controlRegInsertion = new ControlRegInsertion()
  lazy val controlAllocator = new ControlAllocation()
  lazy val controlLowering = new ControlLowering()
  lazy val localMemDuplication = new LocalMemDuplication()

  /* Mapping */
  lazy val cuPruning = new CUPruning()
  lazy val cuPlacer = new CUPlacer()

  /* Codegen */
  lazy val cuStats = new CUStatistics()
  lazy val psimConfigCodegen = new PlastisimConfigCodegen()
  lazy val psimPlacementCodegen = new PlastisimPlacementCodegen()
  lazy val psimTraceCodegen = new PlastisimTraceCodegen()
  lazy val psimDotCodegen = new PlastisimDotCodegen(s"psim.dot")
  lazy val terminalCSVCodegen = new TerminalCSVCodegen()
  lazy val linkCSVCodegen = new LinkCSVCodegen()

  /* Simulator */

  /* Debug */

  override def initSession = {
    val sess = session
    import sess._

    addPass(testTraversal)

    // Data  path transformation and analysis
    addPass(controlPropogator)
    addPass(fringeElaboration).dependsOn(controlPropogator)
    addPass(enableDot, new PIRIRDotCodegen(s"top1.dot"))
    addPass(deadCodeEliminator).dependsOn(fringeElaboration)
    addPass(constantExpressionEvaluator)
    addPass(controlPropogator)
    addPass(irCheck).dependsOn(deadCodeEliminator)
    addPass(debug, new PIRPrinter(s"IR1.txt"))
    addPass(enableDot, new PIRIRDotCodegen(s"top2.dot"))
    addPass(unrollingTransformer).dependsOn(controlPropogator)
    addPass(enableDot, new PIRIRDotCodegen(s"top3.dot"))
    addPass(cuInsertion)
    addPass(accessPuller).dependsOn(cuInsertion)
    addPass(deadCodeEliminator)
    addPass(enableDot, new PIRIRDotCodegen(s"top4.dot"))
    addPass(enableDot, new SimpleIRDotCodegen(s"simple1.dot"))
    addPass(accessLowering).dependsOn(controlPropogator, accessPuller, deadCodeEliminator)
    addPass(enableDot, new PIRIRDotCodegen(s"top5.dot"))

    addPass(bankedAccessMerging).dependsOn(controlPropogator, accessPuller, deadCodeEliminator)
    addPass(enableDot, new PIRIRDotCodegen(s"top6.dot"))
    addPass(memoryAnalyzer)
    addPass(controllerRuntimeAnalyzer).dependsOn(controlPropogator, memoryAnalyzer)
    addPass(cuStats)

    addPass(enableSplitting, igraphPartioner)
    addPass(enableSplitting && enableDot, new PIRIRDotCodegen(s"top7.dot"))
    addPass(enableSplitting && enableDot, new SimpleIRDotCodegen(s"simple2.dot"))

    addPass(enableDot, new ControllerDotCodegen(s"controller1.dot"))
    addPass(routeThroughEliminator).dependsOn(accessLowering).dependsOn(igraphPartioner)
    addPass(deadCodeEliminator).dependsOn(routeThroughEliminator)
    addPass(enableDot, new ControllerDotCodegen(s"controller2.dot")).dependsOn(controlPropogator)
    addPass(enableDot, new PIRIRDotCodegen(s"top8.dot"))
    addPass(enableDot, new SimpleIRDotCodegen(s"simple3.dot")).dependsOn(routeThroughEliminator)
    addPass(debug, new PIRPrinter(s"IR2.txt"))
    addPass(irCheck).dependsOn(routeThroughEliminator)

    addPass(genCtrl, contextInsertion).dependsOn(igraphPartioner)
    addPass(genCtrl && enableDot, new PIRIRDotCodegen(s"top9.dot")).dependsOn(contextInsertion)

    // Control transformation and analysis
    //addPass(genCtrl, localMemDuplication)
    addPass(genCtrl, memoryAnalyzer).dependsOn(contextInsertion)
    addPass(genCtrl, controlAllocator).dependsOn(contextInsertion)  // set accessDoneOf, duplicateCounterChain for accessDoneOf
    addPass(genCtrl, controlRegInsertion).dependsOn(controlAllocator) // insert reg for no forward depended contextEn
    addPass(genCtrl, memoryAnalyzer).dependsOn(controlRegInsertion)
    addPass(genCtrl && enableDot, new PIRIRDotCodegen(s"top10.dot"))
    addPass(genCtrl, controlAllocator).dependsOn(memoryAnalyzer) // set accessDoneOf, duplicateCounterChain for accessDoneOf
    addPass(genCtrl, deadCodeEliminator) // Remove redundant counterChains
    addPass(genCtrl && enableDot, new PIRIRDotCodegen(s"top11.dot"))
    addPass(genCtrl, controlLowering).dependsOn(controlAllocator) // Lower ContextEnableOut to ConectEnable
    addPass(genCtrl && enableDot, new PIRIRDotCodegen(s"top12.dot"))
    addPass(genCtrl, irCheck)

    addPass(enableDot, new ControllerPrinter(s"controller.txt"))
    addPass(cuStats)

    session.rerun {

    // Simulation analyzer
    addPass(enableTrace, psimTraceCodegen).dependsOn(controlLowering)
    addPass(psimLinkAnalyzer).dependsOn(controlLowering)
    addPass(psimDotCodegen).dependsOn(psimLinkAnalyzer)
    addPass(psimCountCheck).dependsOn(psimLinkAnalyzer)
    addPass(enableDot, new ControllerDotCodegen(s"controller.dot"))
    addPass(enableDot, new PIRIRDotCodegen(s"top.dot"))
    addPass(enableDot, new SimpleIRDotCodegen(s"simple.dot"))
    addPass(debug, new PIRPrinter(s"IR.txt"))

    // Mapping
    //addPass(genPlastisim, psimVCAllocator).dependsOn(psimLinkAnalyzer)
    addPass(cuPruning)
    addPass(enableMapping, cuPlacer).dependsOn(cuPruning)

    // Post-enableMapping analysis
    addPass(enableMapping, new PIRNetworkDotCodegen[Bit](s"control.dot")).dependsOn(cuPlacer)
    addPass(enableMapping, new PIRNetworkDotCodegen[Word](s"scalar.dot")).dependsOn(cuPlacer)
    addPass(enableMapping, new PIRNetworkDotCodegen[Vector](s"vector.dot")).dependsOn(cuPlacer)

    // Codegen
    addPass(genPlastisim, terminalCSVCodegen).dependsOn(cuPlacer)
    addPass(genPlastisim, linkCSVCodegen).dependsOn(terminalCSVCodegen, psimLinkAnalyzer)
    addPass(genPlastisim && enableMapping, psimPlacementCodegen).dependsOn(cuPlacer,linkCSVCodegen)
    addPass(genPlastisim, psimDotCodegen).dependsOn(psimLinkAnalyzer)
    addPass(genPlastisim && enableMapping, psimConfigCodegen).dependsOn(cuPlacer, psimPlacementCodegen, psimTraceCodegen)
    addPass(runPlastisim, psimDotCodegen)

     // Simulation

    }
  }

  def handle(e:Exception) = {
    throw e
  }

}
