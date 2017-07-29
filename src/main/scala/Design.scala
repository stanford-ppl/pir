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
  val pirmeta:PIRMetadata = this

  def arch:Spade
  var top:Top = _

  override def reset = {
    super[Collector].reset
    super[PIRMetadata].reset
    passes.foreach(_.reset)
    top = null
  }

  val mappers = ListBuffer[Mapper]()
  val mapperLogger = new Logger {
    override lazy val stream = newStream(Config.mapperLog)
  }

  /* Compiler Passes */
  val passes = ListBuffer[Pass]()

  /* Analysis */
  val forwardRef = new ForwardRef()
  val controlAnalyzer = new ControlAnalyzer()
  val multiBufferAnalyzer = new MultiBufferAnalyzer() 
  val memoryAnalyzer = new MemoryAnalyzer()
  val accessAnalyzer = new AccessAnalyzer()
  val livenessAnalyzer = new LiveAnalyzer()
  val fifoAnalyzer = new FIFOAnalyzer()
  val contentionAnalyzer = new ContentionAnalysis()
  val latencyAnalyzer = new LatencyAnalysis()
  val resourceAnalyzer = new ResourceAnalysis()
  val powerAnalyzer = new PowerAnalyzer()
  val energyAnalyzer = new EnergyAnalyzer()
  val delayAnalyzer = new DelayAnalyzer()

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
  val pisaCodegen = new PisaCodegen()
  val configCodegen = new ConfigCodegen()

  /* Simulator */
  val simulator = new Simulator()

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
  val spadeVecDotPrinter = new CUVectorDotPrinter()
  val spadeScalDotPrinter = new CUScalarDotPrinter()
  val spadeCtrlDotPrinter = new CUCtrlDotPrinter()
  val mapPrinter = new MapPrinter()
  val pirStat = new PIRStat()
  val pirStatLog = new PIRStatLog()
  val irCheck = new IRCheck() 

  def mapping:Option[PIRMap] = pirMapping.mapping

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
  passes += controlAnalyzer // set isHead, isLast, length
  passes += pirDataDotGen4
  //passes += irCheck //TODO
  passes += ctrlAlloc 
  passes += pirDataDotGen5
  passes += ctrlDotPrinter 
  passes += pirCtrlDotGen
  passes += pirPrinter

  // Mapping
  passes += pirMapping 
  passes += mapPrinter
  passes += spadeVecDotPrinter 
  passes += spadeScalDotPrinter 
  passes += spadeCtrlDotPrinter 

  // Post-mapping analysis
  passes += pirDataDotGen
  passes += delayAnalyzer
  passes += fifoAnalyzer

  // Codegen
  passes += spadeNetworkCodegen 
  passes += spadeParamCodegen 
  passes += pisaCodegen 
  passes += configCodegen 

  // Simulation
  passes += simulator

  // Statistics
  passes += contentionAnalyzer
  //passes += latencyAnalyzer
  passes += resourceAnalyzer
  passes += powerAnalyzer 
  //passes += energyAnalyzer 

  def run = {
    try {
      info(s"Configuring spade $arch ...")
      passes.zipWithIndex.foreach{ case (pass, id) => if (pass.shouldRun) pass.run(id) }
      passes.foreach { _.checkRanAll }
    } catch {
      case e:Exception => 
        errmsg(e)
        if (!pirPrinter.hasRun) pirPrinter.run
        if (!mapPrinter.hasRun) mapPrinter.run
        if (!spadeVecDotPrinter.hasRun) spadeVecDotPrinter.run
        if (!spadeScalDotPrinter.hasRun) spadeScalDotPrinter.run
        if (!spadeCtrlDotPrinter.hasRun) spadeCtrlDotPrinter.run
        throw e
    }
  }

}

