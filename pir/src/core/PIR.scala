package pir

import pir.node._
import pir.pass._
import pir.mapper._
import pir.codegen._
import spade.node.{Bit, Word, Vector}
import PIRConfig._

trait PIR extends Compiler with PIRWorld {

  lazy val pirmeta:PIRMetadata = design.pirmeta
  lazy val spademeta:SpadeMetadata = arch.design.spademeta
  def top:Top = design.top

  val configs = List(Config, SpadeConfig, PIRConfig)

  var arch:Spade = _

  override def reset = {
    super.reset
    arch = null
  }

  //lazy val mappers = ListBuffer[Mapper]()

  /* Analysis */
  lazy val testTraversal = new TestTraversal()
  lazy val memoryAnalyzer = new MemoryAnalyzer()
  lazy val controlPropogator = new ControlPropogation()
  lazy val plastisimLinkAnalyzer = new PlastisimLinkAnalyzer()
  lazy val plastisimVCAllocator = new PlastisimVCAllocation()
  lazy val irCheck = new IRCheck()

  /* Transformation */
  lazy val fringeElaboration = new FringeElaboration()
  lazy val deadCodeEliminator = new DeadCodeElimination()
  lazy val unrollingTransformer = new UnrollingTransformer()
  lazy val cuInsertion = new CUInsertion()
  lazy val accessPuller = new AccessPulling()
  lazy val accessLowering = new AccessLowering()
  lazy val routeThroughEliminator = new RouteThroughElimination()
  lazy val contextInsertion = new ContextInsertion()
  lazy val contextMerging = new ContextMerging()
  lazy val controlAllocator = new ControlAllocation()
  lazy val controlLowering = new ControlLowering()

  /* Mapping */
  lazy val cuPruning = new CUPruning()
  lazy val cuPlacer = new CUPlacer()

  /* Codegen */
  lazy val cuStats = new CUStatistics()
  lazy val plastisimConfigCodegen = new PlastisimConfigCodegen()

  /* Simulator */

  /* Debug */

  //var mapping:Option[PIRMap] = None

  override def initSession = {
    super.initSession
    import session._

    addPass(testTraversal)

    // Data  path transformation and analysis
    addPass(fringeElaboration)
    addPass(debug, new PIRPrinter(s"IR1.txt"))
    addPass(debug, new PIRIRDotCodegen(s"top1.dot"))
    addPass(deadCodeEliminator)
    addPass(irCheck)
    addPass(debug, new PIRIRDotCodegen(s"top2.dot"))
    addPass(controlPropogator)
    addPass(unrollingTransformer).dependsOn(controlPropogator)
    addPass(debug, new PIRIRDotCodegen(s"top3.dot"))
    addPass(cuInsertion)
    addPass(deadCodeEliminator)
    addPass(debug, new PIRIRDotCodegen(s"top4.dot"))
    addPass(accessPuller).dependsOn(cuInsertion)
    addPass(debug, new PIRIRDotCodegen(s"top5.dot"))
    addPass(deadCodeEliminator)
    addPass(debug, new PIRIRDotCodegen(s"top6.dot"))
    addPass(accessLowering).dependsOn(controlPropogator, accessPuller, deadCodeEliminator)
    addPass(debug, new PIRIRDotCodegen(s"top7.dot"))
    addPass(deadCodeEliminator)
    addPass(debug, new PIRIRDotCodegen(s"top8.dot"))
    addPass(routeThroughEliminator).dependsOn(accessLowering)
    addPass(deadCodeEliminator)
    addPass(memoryAnalyzer).dependsOn(routeThroughEliminator, deadCodeEliminator)
    addPass(debug, new ControllerDotCodegen(s"controller.dot")).dependsOn(controlPropogator, memoryAnalyzer)
    addPass(debug, new PIRIRDotCodegen(s"top9.dot"))
    addPass(debug, new SimpleIRDotCodegen(s"simple1.dot"))
    addPass(debug, new PIRPrinter(s"IR2.txt"))
    addPass(irCheck)

    addPass(contextInsertion)
    addPass(debug, new PIRIRDotCodegen(s"top10.dot"))
    addPass(contextMerging)
    addPass(deadCodeEliminator)
    addPass(debug, new PIRIRDotCodegen(s"top11.dot"))

    //// Control transformation and analysis
    addPass(genCtrl, controlAllocator) // set accessDoneOf, duplicateCounterChain for accessDoneOf
    addPass(genCtrl, deadCodeEliminator) // TODO cannot dce counters yet since more duplicated in controlLowering
    addPass(genCtrl && debug, new PIRIRDotCodegen(s"top12.dot"))
    addPass(genCtrl && debug, new SimpleIRDotCodegen(s"simple2.dot"))
    addPass(genCtrl, irCheck)
    addPass(genCtrl, controlLowering).dependsOn(controlAllocator) // Lower ContextEnableOut to ConectEnable. Duplicate parent counter chain if no dependency
    addPass(genCtrl, deadCodeEliminator)
    addPass(genCtrl && debug, new PIRIRDotCodegen(s"top13.dot"))
    addPass(genCtrl && debug, new SimpleIRDotCodegen(s"simple3.dot"))
    addPass(genCtrl && debug, new PIRPrinter(s"IR3.txt"))
    addPass(irCheck)
    addPass(cuStats)

    // Simulation analyzer
    addPass(genPlastisim, plastisimLinkAnalyzer)
    addPass(genPlastisim, plastisimVCAllocator).dependsOn(plastisimLinkAnalyzer)
    addPass(debug, new PIRNetworkDotCodegen[Bit](s"archCtrl.dot"))
    addPass(debug, new PIRIRDotCodegen(s"top.dot"))
    addPass(debug, new ControlDotCodegen(s"top-ctrl.dot"))
    addPass(debug, new SimpleIRDotCodegen(s"simple.dot"))
    addPass(debug, new PIRPrinter(s"IR.txt"))

    // Mapping
    session.rerun {

    addPass(cuPruning)
    addPass(mapping, cuPlacer).dependsOn(cuPruning)

    // Post-mapping analysis
    addPass(debug, new PIRNetworkDotCodegen[Bit](s"control.dot"))
    addPass(debug, new PIRNetworkDotCodegen[Word](s"scalar.dot"))
    addPass(debug, new PIRNetworkDotCodegen[Vector](s"vector.dot"))

    // Codegen
    addPass(genPlastisim, new PlastisimDotCodegen(s"psim.dot")).dependsOn(cuPlacer, plastisimLinkAnalyzer, plastisimVCAllocator)
    addPass(genPlastisim, plastisimConfigCodegen).dependsOn(cuPlacer, plastisimLinkAnalyzer, plastisimVCAllocator)

     // Simulation

     // Statistics

    }
  }

  def handle(e:Exception) = {
    throw e
  }

}

