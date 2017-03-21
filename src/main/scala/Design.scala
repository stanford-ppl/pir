package pir

import graph._
import pass._
import mapper._
import pir.codegen._
import plasticine.config._
import pir.util._
import pir.exceptions._
import pir.util.misc._
import pir.plasticine.main._
import pir.plasticine.simulation._

//import analysis._

import scala.util.{Try, Success, Failure}

import scala.language.implicitConversions
import scala.collection.mutable.Queue
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.HashMap
import scala.collection.mutable.Stack
import scala.collection.mutable.{Set,Map}
import java.nio.file.{Paths, Files}
import scala.io.Source

trait Design extends PIRMetadata with Collector {

  implicit def design: Design = this
  val arch:Spade
  var top:Top = _

  override def reset = {
    super.reset
    passes.foreach(_.reset)
    top = null
  }

  val mappers = ListBuffer[Mapper]()
  val mapperLogger = new Logger {
    override val stream = newStream(Config.mapperLog)
  }

  /* Compiler Passes */
  lazy val passes = ListBuffer[Pass]()
  lazy val spadePrinter = new SpadePrinter()
  lazy val forwardRef = new ForwardRef()
  lazy val scalMemInsertion = new ScalarMemInsertion()
  lazy val multiBufferAnalysis = new MultiBufferAnalysis()
  lazy val fusionTransform = new FusionTransform()
  lazy val ctrlDotPrinter = new CtrlDotGen()
  lazy val pirPrinter1 = new PIRPrinter("PIR_orig.txt") 
  lazy val pirPrinter2 = new PIRPrinter()
  lazy val pirDataDotGen = new PIRDataDotGen()
  lazy val liveness = new LiveAnalysis()
  lazy val ctrlAlloc = new CtrlAlloc()
  lazy val pirCtrlDotGen = new PIRCtrlDotGen()
  lazy val pirMapping = new PIRMapping()
  lazy val argDotPrinter = new ArgDotPrinter()
  lazy val ctrDotPrinter = new CtrDotPrinter()
  lazy val spadeVecDotPrinter = new CUVectorDotPrinter()
  lazy val spadeScalDotPrinter = new CUScalarDotPrinter()
  lazy val spadeCtrlDotPrinter = new CUCtrlDotPrinter()
  lazy val ctrlPrinter = new CtrlPrinter()
  lazy val spadeCodegen = new SpadeCodegen()
  lazy val simulator = new Simulator()

  lazy val mapping:Option[PIRMap] = if (pirMapping.hasRun && pirMapping.succeeded) Some(pirMapping.mapping) else None

  // Graph Construction
  passes += spadePrinter 
  passes += forwardRef
  //passes += scalMemInsertion
  passes += pirPrinter1
  //passes += fusionTransform 
  //passes += new ScalarBundling()
  passes += multiBufferAnalysis 
  passes += pirDataDotGen
  passes += liveness 
  //passes += new IRCheck()
  passes += ctrlAlloc 
  //if (Config.debug && Config.ctrl) passes += ctrlDotPrinter 
  passes += pirCtrlDotGen
  passes += ctrlPrinter 
  passes += pirPrinter2

  // Mapping
  passes += pirMapping 
  passes += spadeVecDotPrinter 
  passes += spadeScalDotPrinter 
  passes += spadeCtrlDotPrinter 

  // Codegen
  passes += spadeCodegen 
  //if (Config.mapping && Config.genPisa) passes += new PisaCodegen()

  // Simulation
  passes += simulator

  def run = {
    try {
      passes.foreach{ pass => if (pass.shouldRun) pass.run }
      if (pirMapping.failed) throw PIRException(s"Mapping Failed")
    } catch {
      case e:PIRException => 
        try {
          if (!pirPrinter2.hasRun) pirPrinter2.run
          //if (!ctrlDotPrinter.hasRun) ctrlDotPrinter.run
          if (!spadeVecDotPrinter.hasRun) spadeVecDotPrinter.run
          if (!spadeScalDotPrinter.hasRun) spadeScalDotPrinter.run
          if (!spadeCtrlDotPrinter.hasRun) spadeCtrlDotPrinter.run
          if (!ctrlPrinter.hasRun) ctrlPrinter.run
          throw e
        } catch {
          case ne:Throwable => throw e
        }
      case e:Throwable => throw e
    }
    if (Config.debug) DebugLogger.close
  }

}

