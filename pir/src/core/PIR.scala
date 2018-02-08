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

  implicit def design: PIR = this
  val configs = List(Config, SpadeConfig, PIRConfig)

  var newTop:Top = _
  var arch:Spade = _

  def addNode(n:Node)

  override def reset = {
    super.reset
    clearLogs
    arch = null
    newTop = null
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
  lazy val cuInsertion = new CUInsertion()
  lazy val accessPuller = new AccessPulling()
  lazy val accessLowering = new AccessLowering()
  lazy val routeThroughEliminator = new RouteThroughElimination()

  /* Mapping */

  /* Codegen */
  lazy val cuStats = new CUStatistics()

  /* Simulator */

  /* Debug */

  //var mapping:Option[PIRMap] = None

  override def run = {
    info(s"Configuring spade $arch ...")

    addPass(new TestTraversal)

    // Pre-mapping Analysis and Transformation 
    
    addPass(new IRPrinter(s"IR1.txt"))
    addPass(new PIRIRDotCodegen(s"top1.dot"))
    addPass(deadCodeEliminator)
    addPass(new PIRIRDotCodegen(s"top2.dot"))
    addPass(controlPropogator)
    addPass(cuInsertion)
    addPass(new PIRIRDotCodegen(s"top3.dot"))
    addPass(accessPuller)
    addPass(new PIRIRDotCodegen(s"top4.dot"))
    addPass(deadCodeEliminator)
    addPass(new PIRIRDotCodegen(s"top5.dot"))
    addPass(accessLowering).dependsOn(controlPropogator, accessPuller, deadCodeEliminator)
    addPass(new PIRIRDotCodegen(s"top6.dot"))
    addPass(deadCodeEliminator)
    addPass(new PIRIRDotCodegen(s"top7.dot"))
    addPass(new SimpleIRDotCodegen(s"simple.dot"))
    addPass(memoryAnalyzer)
    addPass(new ControllerDotCodegen(s"ctrl.dot")).dependsOn(controlPropogator)
    addPass(routeThroughEliminator).dependsOn(accessLowering)
    addPass(deadCodeEliminator)
    addPass(new PIRIRDotCodegen(s"top8.dot"))
    addPass(new IRPrinter(s"IR2.txt"))
    addPass(cuStats)
    addPass(irCheck)

    // Mapping

    // Post-mapping analysis

    // Codegen

    // Simulation

    // Statistics

    super.run

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

