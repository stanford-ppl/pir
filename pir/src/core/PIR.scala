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

  var top:Top = _
  var newTop:pir.newnode.Top = _
  var arch:Spade = _

  def addNode(n:pir.newnode.Node)

  override def reset = {
    super[Collector].reset
    super[PIRMetadata].reset
    super[Design].reset
    clearLogs
    arch = null
    top = null
    newTop = null
  }

  lazy val mappers = ListBuffer[Mapper]()
  lazy val mapperLogger = new Logger {
     override lazy val stream = newStream(PIRConfig.mapperLog)
  }

  /* Analysis */
  lazy val forwardRef = new ForwardRef()
  lazy val controlAnalyzer = new ControlAnalyzer()
  lazy val multiBufferAnalyzer = new MultiBufferAnalyzer() 
  //lazy val memoryAnalyzer = new MemoryAnalyzer()
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
  lazy val pirDataDotGens = ListBuffer[PIRDataDotGen]()
  lazy val pirDataDotGen1 = new PIRDataDotGen("PIR1.dot")
  lazy val pirDataDotGen2 = new PIRDataDotGen("PIR2.dot")
  lazy val pirDataDotGen3 = new PIRDataDotGen("PIR3.dot")
  lazy val pirDataDotGen4 = new PIRDataDotGen("PIR4.dot")
  lazy val pirDataDotGen5 = new PIRDataDotGen("PIR.dot")
  lazy val pirDataDotGen = new PIRDataDotGen("PIR.dot")
  lazy val pirCtrlDotGen = new PIRCtrlDotGen()
  lazy val argDotPrinter = new ArgDotPrinter()
  lazy val ctrDotPrinter = new CtrDotPrinter()
  lazy val plasticineDotPrinters = ListBuffer[PlasticineDotGen] ()
  lazy val plasticineVecDotPrinter = new PlasticineVectorDotPrinter()
  lazy val plasticineScalDotPrinter = new PlasticineScalarDotPrinter()
  lazy val plasticineCtrlDotPrinter = new PlasticineCtrlDotPrinter()
  lazy val mapPrinter = new MapPrinter()
  lazy val pirStat = new PIRStat()
  lazy val pirStatLog = new PIRStatLog()
  //lazy val irCheck = new IRCheck() 


  lazy val deadCodeEliminator = new pir.newnode.DeadCodeElimination()
  lazy val controlPropogator = new pir.newnode.ControlPropogation()
  lazy val cuInsertion = new pir.newnode.CUInsertion()
  lazy val accessPuller = new pir.newnode.AccessPulling()
  lazy val accessLowering = new pir.newnode.AccessLowering()
  lazy val cuStats = new pir.newnode.CUStatistics()
  lazy val irCheck = new pir.newnode.IRCheck()
  lazy val memoryAnalyzer = new pir.newnode.MemoryAnalyzer()
  lazy val routeThroughEliminator = new pir.newnode.RouteThroughElimination()

  var mapping:Option[PIRMap] = None

  override def run = {
    info(s"Configuring spade $arch ...")

    // Pre-mapping Analysis and Transformation 
    //passes += forwardRef
    //passes += controlAnalyzer //set ancesstors, descendents, streamming, pipelining, localCChainOf
    //passes += scalMemInsertion
    //passes += pirPrinter1
    //passes += livenessAnalyzer 
    //passes += pirDataDotGen1
    //passes += accessAnalyzer
    //passes += memoryAnalyzer // set forRead, forWrite, copy accumCC, set accumCounterOf
    //passes += pirPrinter2
    //passes += multiBufferAnalyzer // set producer, consumer, buffering, backpressureOf
    ////passes += memoryAnalyzer    // set forRead, forWrite, swapReadCChainOf, swapWriteCChainOf, 
                                //// duplicateCC, readCChainsOf, writeCChainsOf, compCChainsOf, parOf,
                                //// rparOf, wparOf
    //passes += accessAnalyzer
    //passes += multiBufferAnalyzer // set producer, consumer, buffering, backpressureOf
    //passes += optimizer
    //passes += pirDataDotGen2
    //passes += fusionTransform 
    //passes += pirPrinter3
    //passes += pirDataDotGen3
    //passes += controlAnalyzer // set isHead, isLast
    //passes += pirDataDotGen4
    //passes += controlAnalyzer // set length scusOf
    ////passes += irCheck //TODO
    //passes += ctrlAlloc 
    //passes += pirDataDotGen5
    //passes += ctrlDotPrinter 
    //passes += pirCtrlDotGen
    //passes += pirPrinter

    //// Mapping
    //passes += prescreen
    //passes += pirMapping 
    //passes += mapPrinter
    //passes += plasticineVecDotPrinter 
    //passes += plasticineScalDotPrinter 
    //passes += plasticineCtrlDotPrinter 

    //// Post-mapping analysis
    //passes += pirDataDotGen

    //// Codegen
    //passes += configCodegen 

    //// Simulation
    //passes += simulator

    //// Statistics
    ////passes += contentionAnalyzer
    ////passes += latencyAnalyzer
    //passes += resourceAnalyzer
    //passes += powerAnalyzer 
    ////passes += energyAnalyzer 
    
    addPass(new TestPass())
    addPass(new pir.newnode.TestTraversal)

    addPass(new IRPrinter(s"IR1.txt"))
    addPass(new GlobalIRDotCodegen(s"top1.dot"))

    addPass(deadCodeEliminator)
    addPass(new GlobalIRDotCodegen(s"top2.dot"))

    addPass(controlPropogator)
    addPass(cuInsertion)
    addPass(new GlobalIRDotCodegen(s"top3.dot"))

    addPass(accessPuller)
    addPass(new GlobalIRDotCodegen(s"top4.dot"))

    addPass(deadCodeEliminator)
    addPass(new GlobalIRDotCodegen(s"top5.dot"))

    addPass(accessLowering).dependsOn(controlPropogator, accessPuller, deadCodeEliminator)
    addPass(new GlobalIRDotCodegen(s"top6.dot"))

    addPass(deadCodeEliminator)
    addPass(new GlobalIRDotCodegen(s"top7.dot"))
    addPass(new SimpleIRDotCodegen(s"simple.dot"))

    addPass(memoryAnalyzer)

    addPass(new ControllerDotCodegen(s"ctrl.dot")).dependsOn(controlPropogator)

    addPass(routeThroughEliminator).dependsOn(accessLowering)
    addPass(deadCodeEliminator)
    addPass(new GlobalIRDotCodegen(s"top8.dot"))

    addPass(new IRPrinter(s"IR2.txt"))
    addPass(cuStats)

    addPass(irCheck)

    super.run

    if (PIRConfig.openDot) {
      pirDataDotGens.filter { _.hasRun }.lastOption.foreach { _.open }
      plasticineDotPrinters.filter{_.hasRun}.foreach (_.open)
    }
  }

  def handle(e:Exception) = {
    try {
      if (!pirPrinter.hasRun) pirPrinter.run
      if (PIRConfig.openDot) 
        pirDataDotGens.filter { _.hasRun }.lastOption.foreach { _.open }
      if (!mapPrinter.hasRun) mapPrinter.run
      plasticineDotPrinters.foreach { printer => if (!printer.hasRun) printer.run }
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

