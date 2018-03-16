package pir

import pir.node._
import pir.pass._
import pir.mapper._
import pir.codegen._

import spade._

import prism._
import prism.util._

import scala.collection.mutable.ListBuffer

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
  lazy val memoryAnalyzer = new MemoryAnalyzer()
  lazy val controlPropogator = new ControlPropogation()
  lazy val irCheck = new IRCheck()

  /* Transformation */
  lazy val deadCodeEliminator = new DeadCodeElimination()
  lazy val unrollingTransformer = new UnrollingTransformer()
  lazy val cuInsertion = new CUInsertion()
  lazy val accessPuller = new AccessPulling()
  lazy val accessLowering = new AccessLowering()
  lazy val routeThroughEliminator = new RouteThroughElimination()
  lazy val contextInsertion = new ContextInsertion()
  lazy val contextMerging = new ContextMerging()
  lazy val controlAllocator = new ControlAllocation()
  lazy val accessControlLowering = new AccessControlLowering()
  lazy val controlLowering = new ControlLowering()

  /* Mapping */
  lazy val cuPruning = new CUPruning()
  lazy val dynamicCUPlacer = new DynamicCUPlacer()

  /* Codegen */
  lazy val cuStats = new CUStatistics()
  lazy val plastisimConfigCodegen = new PlastisimConfigCodegen()

  /* Simulator */

  /* Debug */

  //var mapping:Option[PIRMap] = None

  override def initSession = {
    super.initSession
    import session._

    addPass(new TestTraversal)

    // Data  path transformation and analysis
    addPass(new DramStoreRegInsertion())
    addPass(new PIRPrinter(s"IR1.txt"))
    addPass(new PIRIRDotCodegen(s"top1.dot"))
    addPass(deadCodeEliminator)
    addPass(irCheck)
    addPass(new PIRIRDotCodegen(s"top2.dot"))
    addPass(controlPropogator)
    addPass(unrollingTransformer).dependsOn(controlPropogator)
    addPass(cuInsertion)
    addPass(deadCodeEliminator)
    addPass(new PIRIRDotCodegen(s"top3.dot"))
    addPass(accessPuller).dependsOn(cuInsertion)
    addPass(new PIRIRDotCodegen(s"top4.dot"))
    addPass(deadCodeEliminator)
    addPass(new PIRIRDotCodegen(s"top5.dot"))
    addPass(accessLowering).dependsOn(controlPropogator, accessPuller, deadCodeEliminator)
    addPass(new PIRIRDotCodegen(s"top6.dot"))
    addPass(deadCodeEliminator)
    addPass(new PIRIRDotCodegen(s"top7.dot"))
    addPass(new PIRIRDotCodegen(s"top8.dot"))
    addPass(routeThroughEliminator).dependsOn(accessLowering)
    addPass(deadCodeEliminator)
    addPass(memoryAnalyzer).dependsOn(routeThroughEliminator, deadCodeEliminator)
    addPass(new ControllerDotCodegen(s"ctrl.dot")).dependsOn(controlPropogator, memoryAnalyzer)
    addPass(new PIRIRDotCodegen(s"top9.dot"))

    addPass(new SimpleIRDotCodegen(s"simple1.dot"))
    addPass(new PIRPrinter(s"IR2.txt"))
    addPass(irCheck)

    // Control transformation and analysis
    addPass(contextInsertion)
    addPass(new PIRIRDotCodegen(s"top10.dot"))
    addPass(contextMerging)
    addPass(controlAllocator) // set accessDoneOf, duplicateCounterChain for accessDoneOf
    addPass(new PIRIRDotCodegen(s"top11.dot"))
    addPass(controlLowering).dependsOn(controlAllocator) // Lower ContextEnableOut to ConectEnable. Duplicate parent counter chain if no dependency
    addPass(accessControlLowering).dependsOn(controlAllocator, controlLowering) // Lower access and counter to EnabledAccess and EnabledCounters
    addPass(new PIRIRDotCodegen(s"top12.dot"))
    addPass(deadCodeEliminator)
    addPass(new PIRIRDotCodegen(s"top13.dot"))
    addPass(new ControlDotCodegen(s"control1.dot"))
    addPass(new SimpleIRDotCodegen(s"simple2.dot"))
    addPass(new PIRPrinter(s"IR3.txt"))
    addPass(irCheck)
    addPass(cuStats)

    // Mapping
    addPass(dynamicCUPlacer)
    addPass(cuPruning)

    // Post-mapping analysis

    // Codegen
    addPass(plastisimConfigCodegen).dependsOn(dynamicCUPlacer)

    // Simulation

    // Statistics

  }

  def handle(e:Exception) = {
    throw e
  }

}



