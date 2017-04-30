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

  def name = super.toString
  override def toString = name 

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
  val controlAnalyzer = new ControlAnalyzer()
  val scalMemInsertion = new ScalarMemInsertion() { override def shouldRun = false }
  val multiBufferAnalyzer = new MultiBufferAnalyzer() 
  val fusionTransform = new FusionTransform()
  val scalarBundling = new ScalarBundling() { override def shouldRun = false }
  val memoryAnalyzer = new MemoryAnalyzer()
  val accessAnalyzer = new AccessAnalyzer()
  val optimizer = new Optimizer()
  val ctrlDotPrinter = new CtrlDotGen() { override def shouldRun = false }
  val pirPrinter1 = new PIRPrinter("PIR1.log") 
  val pirPrinter2 = new PIRPrinter("PIR2.log") 
  val pirPrinter3 = new PIRPrinter("PIR3.log") 
  val pirPrinter = new PIRPrinter()
  val irCheck = new IRCheck()
  val pirDataDotGen1 = new PIRDataDotGen("PIR1.dot")
  val pirDataDotGen2 = new PIRDataDotGen("PIR2.dot")
  val pirDataDotGen3 = new PIRDataDotGen("PIR3.dot")
  val pirDataDotGen4 = new PIRDataDotGen("PIR4.dot")
  val pirDataDotGen = new PIRDataDotGen("PIR.dot")
  val livenessAnalyzer = new LiveAnalyzer()
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
  val mapPrinter = new MapPrinter()
  val pirStat = new PIRStat()
  val pirStatLog = new PIRStatLog()
  val contentionAnalysis = new ContentionAnalysis()
  val latencyAnalysis = new LatencyAnalysis()
  val resourceAnalysis = new ResourceAnalysis()

  def mapping:Option[PIRMap] = pirMapping.mapping

  // Graph Construction
  //passes += spadePrinter 
  passes += forwardRef
  passes += controlAnalyzer //set ancesstors, descendents, streamming, pipelining
  passes += scalMemInsertion
  passes += pirPrinter1
  passes += scalarBundling
  passes += memoryAnalyzer
  passes += pirPrinter2
  passes += pirDataDotGen1
  passes += optimizer
  passes += pirDataDotGen2
  passes += fusionTransform 
  passes += pirPrinter3
  passes += controlAnalyzer // set isHead, isTail, length
  passes += pirDataDotGen3
  passes += livenessAnalyzer 
  passes += accessAnalyzer
  passes += multiBufferAnalyzer
  passes += controlAnalyzer // reset isHead, isTail, length
  passes += pirDataDotGen4
  passes += irCheck 
  passes += ctrlAlloc 
  passes += pirDataDotGen
  passes += ctrlDotPrinter 
  passes += pirCtrlDotGen
  passes += ctrlPrinter 
  passes += pirPrinter

  // Mapping
  passes += pirMapping 
  passes += mapPrinter
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

  // Statistics
  passes += resourceAnalysis

  def run = {
    try {
      arch.config
      passes.zipWithIndex.foreach{ case (pass, id) => if (pass.shouldRun) pass.run(id) }
      if (pirMapping.failed) throw PIRException(s"Mapping Failed")
    } catch {
      case e:PIRException => 
        try {
          if (!pirPrinter.hasRun) pirPrinter.run
          if (!mapPrinter.hasRun) mapPrinter.run
          //if (!ctrlDotPrinter.hasRun) ctrlDotPrinter.run
          if (!spadeVecDotPrinter.hasRun) spadeVecDotPrinter.run
          if (!spadeScalDotPrinter.hasRun) spadeScalDotPrinter.run
          if (!spadeCtrlDotPrinter.hasRun) spadeCtrlDotPrinter.run
          if (!ctrlPrinter.hasRun) ctrlPrinter.run
          throw e
        } catch {
          case ne:Throwable => throw e
        }
        throw e
      case e:Throwable => throw e
    }
  }

}

