package pir

import pir.node._
import pir.pass._
import pir.mapper._
import pir.codegen._

import spade._

import pirc._
import pirc.util._

import scala.collection.mutable.ListBuffer

trait PIR extends Design with PIRMetadata with Collector {

  implicit def design: PIR = this
  val pirmeta:PIRMetadata = this
  val configs = List(Config, SpadeConfig, PIRConfig)

  def arch:Spade
  var top:Top = _

  override def reset = {
    super[Collector].reset
    super[PIRMetadata].reset
    super[Design].reset
    arch.reset
    top = null
  }

  lazy val mappers = ListBuffer[Mapper]()
  lazy val mapperLogger = new Logger {
     override lazy val stream = newStream(PIRConfig.mapperLog)
  }

  /* Analysis */
  lazy val forwardRef = new ForwardRef()
  lazy val controlAnalyzer = new ControlAnalyzer()
  lazy val multiBufferAnalyzer = new MultiBufferAnalyzer() 
  lazy val memoryAnalyzer = new MemoryAnalyzer()
  lazy val accessAnalyzer = new AccessAnalyzer()
  lazy val livenessAnalyzer = new LiveAnalyzer()
  lazy val contentionAnalyzer = new ContentionAnalysis()
  lazy val latencyAnalyzer = new LatencyAnalysis()
  lazy val resourceAnalyzer = new ResourceAnalysis()
  lazy val powerAnalyzer = new PowerAnalyzer()
  lazy val energyAnalyzer = new EnergyAnalyzer()
  lazy val prescreen = new ResourcePrescreen()

  /* Transformation */
  lazy val ctrlAlloc = new CtrlAlloc()
  lazy val scalMemInsertion = new ScalarMemInsertion() { override def shouldRun = false }
  lazy val fusionTransform = new FusionTransform()
  lazy val scalarBundling = new ScalarBundling() { override def shouldRun = false }
  lazy val optimizer = new Optimizer()

  /* Mapping */
  lazy val pirMapping = new PIRMapping()

  /* Codegen */
  lazy val configCodegen = new ConfigCodegen()

  /* Simulator */
  lazy val simulator = new SpadeSimulator()

  /* Debug */
  lazy val ctrlDotPrinter = new CtrlDotGen() { override def shouldRun = false }
  lazy val pirPrinter1 = new PIRPrinter("PIR1.log") 
  lazy val pirPrinter2 = new PIRPrinter("PIR2.log") 
  lazy val pirPrinter3 = new PIRPrinter("PIR3.log") 
  lazy val pirPrinter = new PIRPrinter()
  lazy val pirDataDotGen1 = new PIRDataDotGen("PIR1.dot")
  lazy val pirDataDotGen2 = new PIRDataDotGen("PIR2.dot")
  lazy val pirDataDotGen3 = new PIRDataDotGen("PIR3.dot")
  lazy val pirDataDotGen4 = new PIRDataDotGen("PIR4.dot")
  lazy val pirDataDotGen5 = new PIRDataDotGen("PIR.dot")
  lazy val pirDataDotGen = new PIRDataDotGen("PIR.dot", PIRConfig.openDot)
  lazy val pirCtrlDotGen = new PIRCtrlDotGen()
  lazy val argDotPrinter = new ArgDotPrinter()
  lazy val ctrDotPrinter = new CtrDotPrinter()
  lazy val plasticineVecDotPrinter = new PlasticineVectorDotPrinter()
  lazy val plasticineScalDotPrinter = new PlasticineScalarDotPrinter()
  lazy val plasticineCtrlDotPrinter = new PlasticineCtrlDotPrinter()
  lazy val mapPrinter = new MapPrinter()
  lazy val pirStat = new PIRStat()
  lazy val pirStatLog = new PIRStatLog()
  lazy val irCheck = new IRCheck() 

  var mapping:Option[PIRMap] = None

  override def run = {
    info(s"Configuring spade $arch ...")

    // Pre-mapping Analysis and Transformation 
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
    passes += plasticineVecDotPrinter 
    passes += plasticineScalDotPrinter 
    passes += plasticineCtrlDotPrinter 

    // Post-mapping analysis
    passes += pirDataDotGen

    // Codegen
    passes += configCodegen 

    // Simulation
    passes += simulator

    // Statistics
    passes += contentionAnalyzer
    //passes += latencyAnalyzer
    passes += resourceAnalyzer
    passes += powerAnalyzer 
    //passes += energyAnalyzer 

    super.run
  }

  def handle(e:Exception) = {
    if (!pirPrinter.hasRun) pirPrinter.run
    if (!mapPrinter.hasRun) mapPrinter.run
    if (!plasticineVecDotPrinter.hasRun) plasticineVecDotPrinter.run
    if (!plasticineScalDotPrinter.hasRun) plasticineScalDotPrinter.run
    if (!plasticineCtrlDotPrinter.hasRun) plasticineCtrlDotPrinter.run
    throw e
  }
}

