package pir

import pir.node._
import pir.pass._
import pir.mapper._
import pir.codegen._
import pir.util._

import spade.util._
import spade.main._
import spade.simulation._

import pirc.exceptions._
import pirc.util._
import pirc._

import scala.util.{Try, Success, Failure}

import scala.language.implicitConversions
import scala.collection.mutable.Queue
import scala.collection.mutable.HashMap
import scala.collection.mutable.Stack
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.{Set,Map}
import java.nio.file.{Paths, Files}
import scala.io.Source

trait PIR extends Design with PIRMetadata with Collector {

  implicit def design: PIR = this
  val pirmeta:PIRMetadata = this

  def arch:Spade
  var top:Top = _

  override def reset = {
    super[Collector].reset
    super[PIRMetadata].reset
    super[Design].reset
    top = null
  }

  /* Analysis */
  val forwardRef = new ForwardRef()
  val controlAnalyzer = new ControlAnalyzer()
  val multiBufferAnalyzer = new MultiBufferAnalyzer() 
  val memoryAnalyzer = new MemoryAnalyzer()
  val accessAnalyzer = new AccessAnalyzer()
  val livenessAnalyzer = new LiveAnalyzer()
  val contentionAnalyzer = new ContentionAnalysis()
  val latencyAnalyzer = new LatencyAnalysis()
  val resourceAnalyzer = new ResourceAnalysis()
  val powerAnalyzer = new PowerAnalyzer()
  val energyAnalyzer = new EnergyAnalyzer()
  val prescreen = new ResourcePrescreen()

  /* Transformation */
  val ctrlAlloc = new CtrlAlloc()
  val scalMemInsertion = new ScalarMemInsertion() { override def shouldRun = false }
  val fusionTransform = new FusionTransform()
  val scalarBundling = new ScalarBundling() { override def shouldRun = false }
  val optimizer = new Optimizer()

  /* Mapping */
  val pirMapping = new PIRMapping()

  /* Codegen */
  val spadeNetworkCodegen = new SpadeNetworkCodegen()
  val spadeParamCodegen = new SpadeParamCodegen()
  val configCodegen = new ConfigCodegen()

  /* Simulator */
  val simulator = new SpadeSimulator()

  /* Debug */
  val spadePrinter = new SpadePrinter()
  val ctrlDotPrinter = new CtrlDotGen() { override def shouldRun = false }
  val pirPrinter1 = new PIRPrinter("PIR1.log") 
  val pirPrinter2 = new PIRPrinter("PIR2.log") 
  val pirPrinter3 = new PIRPrinter("PIR3.log") 
  val pirPrinter = new PIRPrinter()
  val pirDataDotGen1 = new PIRDataDotGen("PIR1.dot")
  val pirDataDotGen2 = new PIRDataDotGen("PIR2.dot")
  val pirDataDotGen3 = new PIRDataDotGen("PIR3.dot")
  val pirDataDotGen4 = new PIRDataDotGen("PIR4.dot")
  val pirDataDotGen5 = new PIRDataDotGen("PIR.dot")
  val pirDataDotGen = new PIRDataDotGen("PIR.dot")
  val pirCtrlDotGen = new PIRCtrlDotGen()
  val argDotPrinter = new ArgDotPrinter()
  val ctrDotPrinter = new CtrDotPrinter()
  val spadeVecDotPrinter = new SpadeVectorDotPrinter()
  val spadeScalDotPrinter = new SpadeScalarDotPrinter()
  val spadeCtrlDotPrinter = new SpadeCtrlDotPrinter()
  val mapPrinter = new MapPrinter()
  val pirStat = new PIRStat()
  val pirStatLog = new PIRStatLog()
  val irCheck = new IRCheck() 

  var mapping:Option[PIRMap] = None

  val mappers = ListBuffer[Mapper]()
  val mapperLogger = new Logger {
    override lazy val stream = newStream(Config.mapperLog)
  }

  // Pre-mapping Analysis and Transformation 
  //passes += spadePrinter 
  passes += forwardRef
  passes += controlAnalyzer //set ancesstors, descendents, streamming, pipelining, localCChainOf
  passes += scalMemInsertion
  passes += pirPrinter1
  passes += scalarBundling
  passes += memoryAnalyzer // set forRead, forWrite, copy accumCC, set accumCounterOf
  passes += pirPrinter2
  passes += pirDataDotGen1
  passes += accessAnalyzer
  passes += multiBufferAnalyzer // set producer, consumer, buffering, backpressureOf
  passes += memoryAnalyzer    // set forRead, forWrite, swapReadCChainOf, swapWriteCChainOf, 
                              // duplicateCC, readCChainsOf, writeCChainsOf, compCChainsOf, parOf,
                              // rparOf, wparOf
  passes += accessAnalyzer
  passes += multiBufferAnalyzer // set producer, consumer, buffering, backpressureOf
  passes += livenessAnalyzer 
  passes += optimizer
  passes += pirDataDotGen2
  passes += fusionTransform 
  passes += pirPrinter3
  passes += pirDataDotGen3
  passes += controlAnalyzer // set isHead, isLast
  passes += pirDataDotGen4
  passes += controlAnalyzer // set length scusOf
  //passes += irCheck //TODO
  passes += ctrlAlloc 
  passes += pirDataDotGen5
  passes += ctrlDotPrinter 
  passes += pirCtrlDotGen
  passes += pirPrinter

  // Mapping
  passes += prescreen
  passes += pirMapping 
  passes += mapPrinter
  passes += spadeVecDotPrinter 
  passes += spadeScalDotPrinter 
  passes += spadeCtrlDotPrinter 

  // Post-mapping analysis
  passes += pirDataDotGen

  // Codegen
  passes += spadeNetworkCodegen 
  passes += spadeParamCodegen 
  passes += configCodegen 

  // Simulation
  passes += simulator

  // Statistics
  passes += contentionAnalyzer
  //passes += latencyAnalyzer
  passes += resourceAnalyzer
  passes += powerAnalyzer 
  //passes += energyAnalyzer 

  override def run = {
    info(s"Configuring spade $arch ...")
    super.run
  }

  def handle(e:Exception) = {
    if (!pirPrinter.hasRun) pirPrinter.run
    if (!mapPrinter.hasRun) mapPrinter.run
    if (!spadeVecDotPrinter.hasRun) spadeVecDotPrinter.run
    if (!spadeScalDotPrinter.hasRun) spadeScalDotPrinter.run
    if (!spadeCtrlDotPrinter.hasRun) spadeCtrlDotPrinter.run
    throw e
  }
}

