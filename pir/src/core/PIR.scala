package pir

import pir.node._
import pir.pass._
import pir.mapper._
import pir.codegen._

import spade._

import pirc._
import pirc.util._

import scala.collection.mutable.ListBuffer

trait PIR extends Design {

  override implicit val design: PIR = this
  lazy val pirmeta:PIRMetadata = top.metadata

  val configs = List(Config, SpadeConfig, PIRConfig)

  var top:Top = _
  var arch:Spade = _

  override def reset = {
    super.reset
    clearLogs(outDir)
    arch = null
    top = null
  }

  //lazy val mappers = ListBuffer[Mapper]()
  lazy val mapperLogger = new Logger {
     override lazy val stream = newStream(PIRConfig.mapperLog)
  }

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

  /* Codegen */
  lazy val cuStats = new CUStatistics()

  /* Simulator */

  /* Debug */

  //var mapping:Option[PIRMap] = None

  override def initSession = {
    super.initSession
    import session._

    addPass(new TestTraversal)

    // Data path transformation and analysis
    addPass(new IRPrinter(s"IR1.txt"))
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
    addPass(memoryAnalyzer)
    addPass(new PIRIRDotCodegen(s"top8.dot"))
    addPass(new ControllerDotCodegen(s"ctrl.dot")).dependsOn(controlPropogator, memoryAnalyzer)
    addPass(routeThroughEliminator).dependsOn(accessLowering)
    addPass(deadCodeEliminator)
    addPass(new PIRIRDotCodegen(s"top9.dot"))

    addPass(new SimpleIRDotCodegen(s"simple1.dot"))
    addPass(new IRPrinter(s"IR2.txt"))
    //addPass(cuStats)
    addPass(irCheck)

    // Control transformation and analysis
    addPass(contextInsertion)
    addPass(new PIRIRDotCodegen(s"top10.dot"))
    addPass(contextMerging)
    addPass(new PIRIRDotCodegen(s"top11.dot"))
    addPass(controlAllocator) // set accessDoneOf, make copy to counter chains
    addPass(accessControlLowering).dependsOn(controlAllocator) // Lower access and counter to EnabledAccess and Enabled counters
    addPass(deadCodeEliminator) // Remove unused memories and counters
    addPass(new PIRIRDotCodegen(s"top12.dot"))
    addPass(controlLowering).dependsOn(accessControlLowering, deadCodeEliminator) // Generate context enable dependencies
    addPass(deadCodeEliminator)
    addPass(new PIRIRDotCodegen(s"top13.dot"))
    addPass(new ControlDotCodegen(s"control1.dot"))
    addPass(new SimpleIRDotCodegen(s"simple2.dot"))
    addPass(new IRPrinter(s"IR3.txt"))
    addPass(irCheck)

    // Mapping

    // Post-mapping analysis

    // Codegen

    // Simulation

    // Statistics

  }

  def handle(e:Exception) = {
    try {
      arch.handle(e)
    } catch {
      case he:Exception =>
        errmsg(s"Original Exception")
        e.printStackTrace
        errmsg(s"Exception during handling")
        //he.printStackTrace
    }
  }

}

