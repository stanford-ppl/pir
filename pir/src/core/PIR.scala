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
  lazy val plastisimLinkAnalyzer = new PlastisimLinkAnalyzer()
  lazy val plastisimVCAllocator = new PlastisimVCAllocation()
  lazy val irCheck = new IRCheck()

  /* Transformation */
  lazy val fringeElaboration = new FringeElaboration()
  lazy val constantExpressionEvaluator = new ConstantExpressionEvaluation()
  lazy val deadCodeEliminator = new DeadCodeElimination()
  lazy val unrollingTransformer = new UnrollingTransformer()
  lazy val cuInsertion = new CUInsertion()
  lazy val accessPuller = new AccessPulling()
  lazy val accessLowering = new AccessLowering()
  lazy val igraphPartioner = new IgraphPartioner()
  lazy val routeThroughEliminator = new RouteThroughElimination()
  lazy val contextInsertion = new ContextInsertion()
  lazy val contextMerging = new ContextMerging()
  lazy val controlRegInsertion = new ControlRegInsertion()
  lazy val controlAllocator = new ControlAllocation()
  lazy val controlLowering = new ControlLowering()

  /* Mapping */
  lazy val cuPruning = new CUPruning()
  lazy val cuPlacer = new CUPlacer()

  /* Codegen */
  lazy val cuStats = new CUStatistics()
  lazy val psimConfigCodegen = new PlastisimConfigCodegen()
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
    addPass(enableDot, new PIRIRDotCodegen(s"top4.dot"))
    addPass(deadCodeEliminator)
    addPass(enableDot, new PIRIRDotCodegen(s"top5.dot"))
    addPass(accessLowering).dependsOn(controlPropogator, accessPuller, deadCodeEliminator)
    addPass(enableDot, new PIRIRDotCodegen(s"top6.dot"))
    addPass(deadCodeEliminator)
    addPass(enableDot, new PIRIRDotCodegen(s"top7.dot"))
    addPass(controllerRuntimeAnalyzer).dependsOn(controlPropogator)
    addPass(cuStats)
    addPass(enableDot, new SimpleIRDotCodegen(s"simple1.dot"))

    addPass(enableSplitting, igraphPartioner)
    addPass(enableSplitting && enableDot, new PIRIRDotCodegen(s"top8.dot"))
    addPass(enableSplitting && enableDot, new SimpleIRDotCodegen(s"simple2.dot"))

    addPass(routeThroughEliminator).dependsOn(accessLowering)
    addPass(deadCodeEliminator)
    addPass(memoryAnalyzer).dependsOn(routeThroughEliminator, deadCodeEliminator)
    addPass(enableDot, new ControllerDotCodegen(s"controller.dot")).dependsOn(controlPropogator, memoryAnalyzer)
    addPass(enableDot, new PIRIRDotCodegen(s"top9.dot"))
    addPass(enableDot, new SimpleIRDotCodegen(s"simple3.dot")).dependsOn(routeThroughEliminator)
    addPass(debug, new PIRPrinter(s"IR2.txt"))
    addPass(irCheck)

    addPass(genCtrl, contextInsertion)
    addPass(genCtrl && enableDot, new PIRIRDotCodegen(s"top10.dot"))
    addPass(genCtrl, contextMerging)
    addPass(genCtrl, deadCodeEliminator)
    addPass(genCtrl && enableDot, new PIRIRDotCodegen(s"top11.dot"))

    // Control transformation and analysis
    addPass(genCtrl, controlAllocator) // set accessDoneOf, duplicateCounterChain for accessDoneOf
    addPass(genCtrl, controlRegInsertion) // insert reg for no forward depended contextEn
    addPass(genCtrl, memoryAnalyzer).dependsOn(controlRegInsertion)
    addPass(genCtrl && enableDot, new PIRIRDotCodegen(s"top12.dot"))
    addPass(genCtrl, controlAllocator).dependsOn(memoryAnalyzer) // set accessDoneOf, duplicateCounterChain for accessDoneOf
    addPass(genCtrl, deadCodeEliminator) // Remove redundant counterChains
    addPass(genCtrl && enableDot, new PIRIRDotCodegen(s"top13.dot"))
    addPass(genCtrl, controlLowering).dependsOn(controlAllocator) // Lower ContextEnableOut to ConectEnable. Duplicate parent counter chain if no dependency
    addPass(genCtrl, irCheck)

    session.rerun {

    addPass(cuStats)

    // Simulation analyzer
    addPass(genPlastisim, plastisimLinkAnalyzer).dependsOn(controlLowering)
    addPass(enableDot, new ControllerDotCodegen(s"controller.dot"))
    addPass(enableDot, new PIRIRDotCodegen(s"top.dot"))
    addPass(enableDot, new SimpleIRDotCodegen(s"simple.dot"))
    addPass(debug, new PIRPrinter(s"IR.txt"))

    // Mapping
    //addPass(genPlastisim, plastisimVCAllocator).dependsOn(plastisimLinkAnalyzer)
    addPass(genPlastisim, psimDotCodegen).dependsOn(plastisimLinkAnalyzer)
    addPass(cuPruning)
    addPass(enableMapping, cuPlacer).dependsOn(cuPruning)

    // Post-enableMapping analysis
    addPass(enableMapping && enableDot, new PIRNetworkDotCodegen[Bit](s"control.dot")).dependsOn(cuPlacer)
    addPass(enableMapping && enableDot, new PIRNetworkDotCodegen[Word](s"scalar.dot")).dependsOn(cuPlacer)
    addPass(enableMapping && enableDot, new PIRNetworkDotCodegen[Vector](s"vector.dot")).dependsOn(cuPlacer)

    // Codegen
    addPass(genPlastisim, terminalCSVCodegen)
    addPass(genPlastisim, linkCSVCodegen).dependsOn(plastisimLinkAnalyzer)
    addPass(genPlastisim, psimDotCodegen).dependsOn(cuPlacer, plastisimLinkAnalyzer)
    addPass(genPlastisim, psimConfigCodegen).dependsOn(cuPlacer, plastisimLinkAnalyzer)

     // Simulation

    }
  }

  def handle(e:Exception) = {
    throw e
  }

}
