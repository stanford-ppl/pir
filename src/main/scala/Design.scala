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
    override lazy val stream = newStream(Config.mapperLog)
  }

  /* Compiler Passes */
  val passes = ListBuffer[Pass]()
  val spadePrinter = new SpadePrinter()
  val forwardRef = new ForwardRef()
  val scalMemInsertion = new ScalarMemInsertion() { override def shouldRun = false }
  val multiBufferAnalysis = new MultiBufferAnalysis() 
  val fusionTransform = new FusionTransform() { override def shouldRun = false }
  val scalarBundling = new ScalarBundling() { override def shouldRun = false }
  val memoryAnalysis = new MemoryAnalysis()
  val ctrlDotPrinter = new CtrlDotGen() { override def shouldRun = false }
  val pirPrinter1 = new PIRPrinter("PIR_orig.txt") 
  val pirPrinter2 = new PIRPrinter()
  val irCheck = new IRCheck() { }
  val pirDataDotGen = new PIRDataDotGen()
  val liveness = new LiveAnalysis()
  val ctrlAlloc = new CtrlAlloc()
  val pirCtrlDotGen = new PIRCtrlDotGen()
  val pirMapping = new PIRMapping()
  val argDotPrinter = new ArgDotPrinter()
  val ctrDotPrinter = new CtrDotPrinter()
  val spadeVecDotPrinter = new CUVectorDotPrinter()
  val spadeScalDotPrinter = new CUScalarDotPrinter()
  val spadeCtrlDotPrinter = new CUCtrlDotPrinter()
  val ctrlPrinter = new CtrlPrinter()
  val spadeNetworkCodegen = new SpadeNetworkCodegen()
  val spadeParamCodegen = new SpadeParamCodegen()
  val pisaCodegen = new PisaCodegen()
  val configCodegen = new ConfigCodegen()
  val simulator = new Simulator()

  lazy val mapping:Option[PIRMap] = if (pirMapping.hasRun && pirMapping.succeeded) Some(pirMapping.mapping) else None

  // Graph Construction
  passes += spadePrinter 
  passes += forwardRef
  passes += scalMemInsertion
  passes += pirPrinter1
  passes += fusionTransform 
  passes += scalarBundling
  passes += multiBufferAnalysis 
  passes += memoryAnalysis
  passes += pirDataDotGen
  passes += liveness 
  passes += irCheck 
  passes += ctrlAlloc 
  passes += ctrlDotPrinter 
  passes += pirCtrlDotGen
  passes += ctrlPrinter 
  passes += pirPrinter2

  // Mapping
  passes += pirMapping 
  passes += spadeVecDotPrinter 
  passes += spadeScalDotPrinter 
  passes += spadeCtrlDotPrinter 

  // Codegen
  passes += spadeNetworkCodegen 
  passes += spadeParamCodegen 
  passes += pisaCodegen 
  passes += configCodegen 

  // Simulation
  passes += simulator

  def run = {
    try {
      arch.config
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

