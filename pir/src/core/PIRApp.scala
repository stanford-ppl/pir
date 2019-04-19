package pir

import pir.node._
import pir.pass._
import pir.codegen._
import pir.mapper._
import prism.codegen._
import prism.util._
import spade.codegen._

trait PIRApp extends PIR with Logging {
  
  /* Analysis and Transformations*/

  lazy val deadCodeEliminator = new DeadCodeElimination()
  lazy val constProp = new ConstantPropogation()
  lazy val memLowering = new MemoryLowering()
  lazy val contextAnalyzer = new ContextAnalyzer()
  lazy val contextInsertion = new ContextInsertion()
  lazy val depDuplications = new DependencyDuplication()
  lazy val accessCtxCreation = new AccessContextCreation()
  lazy val bufferInsertion = new BufferInsertion()
  lazy val globalInsertion = new GlobalInsertion()
  lazy val graphCorrector = new GraphRectification()
  lazy val psimAnalyzer = new PlastisimAnalyzer()
  lazy val ctxMerging = new ContextMerging()
  lazy val psimParser = new PlastisimLogParser()
  lazy val sanityCheck = new SanityCheck()

  /* Mapping */
  lazy val initializer = new TargetInitializer()
  lazy val hardPruner = new HardConstrainPruner()
  lazy val computePruner = new ComputePruner()
  lazy val dagPruner = new DAGPruner()
  lazy val memoryPruner = new MemoryPruner()
  lazy val memoryComputePruner = new MemoryComputePruner()
  lazy val matchPruner = new MatchPruner()
  lazy val placerAndRouter = new PlaceAndRoute()

  /* Codegen */
  lazy val tungstenPIRGen = new TungstenPIRGen()
  lazy val psimConfigGen = new PlastisimConfigGen()
  lazy val prouteLinkGen = new PlastirouteLinkGen()
  lazy val prouteNodeGen = new PlastirouteNodeGen()
  lazy val dramTraceGen = new DRAMTraceCodegen()
  lazy val report = new ResourceReport()
  lazy val igraphGen = new IgraphCodegen()
  //lazy val areaPowerStat = new AreaPowerStat()
  
  /* Simulation */
  lazy val psimRunner = new PlastisimRunner()
  lazy val prouteRunner = new PlastirouteRunner()
  lazy val tstRunner = new TungstenRunner()

  override def initSession = {
    import config._

    saveSession(buildPath(config.outDir,"pir0.ckpt"))

    // ------- Analysis and Transformations --------
    addPass(enableDot, new PIRIRDotGen(s"top1.dot"))
    addPass(enableTrace && genPsim, dramTraceGen)
    addPass(graphCorrector) ==>
    addPass(enableDot, new PIRIRDotGen(s"top2.dot"))
    addPass(enableDot, new ControlTreeDotGen(s"ctop.dot"))
    addPass(enableDot, new ControlTreeHtmlIRPrinter(s"ctrl.html"))
    addPass(constProp).dependsOn(graphCorrector) ==>
    addPass(deadCodeEliminator) ==>
    addPass(contextInsertion) ==>
    addPass(enableDot, new PIRIRDotGen(s"top3.dot")) ==>
    addPass(enableDot, new PIRCtxDotGen(s"simple3.dot"))
    addPass(memLowering).dependsOn(contextInsertion)
    addPass(enableDot, new PIRIRDotGen(s"top4.dot")) ==>
    addPass(enableDot, new PIRCtxDotGen(s"simple4.dot"))
    addPass(depDuplications).dependsOn(memLowering)
    addPass(enableDot, new PIRIRDotGen(s"top5.dot")) ==>
    addPass(enableDot, new PIRCtxDotGen(s"simple5.dot"))
    addPass(accessCtxCreation).dependsOn(depDuplications) ==>
    addPass(constProp) ==>
    addPass(deadCodeEliminator) ==>
    //addPass(contextAnalyzer) ==>
    addPass(enableDot, new PIRIRDotGen(s"top6.dot")) ==>
    addPass(enableDot, new PIRCtxDotGen(s"simple6.dot"))
    addPass(globalInsertion).dependsOn(deadCodeEliminator)
    
    saveSession(buildPath(config.outDir,"pir1.ckpt")).dependsOn(globalInsertion)

    addPass(initializer).dependsOn(globalInsertion) ==>
    addPass(new ParamHtmlIRPrinter(s"param.html", spadeParam))
    addPass(enableDot, new PIRCtxDotGen(s"simple7.dot")) ==>
    addPass(enableDot, new PIRIRDotGen(s"top7.dot"))

    // ------- Mapping  --------
    addPass(enableMapping, hardPruner).dependsOn(initializer) ==>
    addPass(enableMapping, memoryPruner) ==>
    addPass(constProp) ==> // Remove unused shuffle
    addPass(deadCodeEliminator) ==>
    addPass(enableMapping, memoryComputePruner) ==>
    addPass(enableMapping, hardPruner) ==> // prune on newly created CUs by memoryComputePruner
    addPass(enableMapping, computePruner) ==>
    addPass(enableMapping, dagPruner) ==>
    addPass(sanityCheck) ==>
    addPass(enableMapping, matchPruner) ==>
    addPass(enableMapping, placerAndRouter) ==>
    addPass(enableDot, new PIRCtxDotGen(s"simple8.dot")) ==>
    addPass(enableDot, new PIRIRDotGen(s"top8.dot"))

    //addPass(enableDot, new PIRNetworkDotGen(s"net.dot"))
    addPass(enableMapping,report).dependsOn(matchPruner) ==>
    saveSession(buildPath(config.outDir,"pir2.ckpt"))
    
    // ------- Codegen  --------
    addPass(psimAnalyzer).dependsOn(placerAndRouter) ==>
    addPass(enableDot, new PIRCtxDotGen(s"simple9.dot")) ==>
    addPass(enableDot, new PIRIRDotGen(s"top9.dot"))
    // Igraph
    addPass(enableIgraph, igraphGen).dependsOn(psimAnalyzer)
    // Plastiroute
    addPass(genProute, prouteLinkGen).dependsOn(psimAnalyzer)
    addPass(genProute, prouteNodeGen).dependsOn(placerAndRouter, psimAnalyzer) ==>
    addPass(genProute, prouteRunner)
    // Tungsten
    addPass(genTungsten, tungstenPIRGen).dependsOn(placerAndRouter) ==>
    addPass(genTungsten && runTst, tstRunner).dependsOn(prouteRunner)
    // Plastisim
    addPass(genPsim, psimConfigGen).dependsOn(placerAndRouter, psimAnalyzer, prouteLinkGen) ==>
    addPass(genPsim && runPsim, psimRunner).dependsOn(prouteRunner)
    addPass(psimParser)
    addPass(enableDot, new PIRIRDotGen(s"top10.dot"))
    addPass(enableDot, new PIRCtxDotGen(s"simple10.dot"))

    //addPass(areaPowerStat).dependsOn(psimConfigCodegen, cuPlacer)

  }

  override def loadSession:Unit = {
    super.loadSession
    if (_states.isEmpty) {
      createNewState
      tic
      val top = Top()
      states.pirTop = top
      import top._
      within(top) {
        val topCtrler = createCtrl("Sequenced") { TopController() }
        top.topCtrl = topCtrler.ctrl.get
        topCtrl.ctrler := topCtrler
        top.argFringe = ArgFringe()
        within(argFringe) {
          val hostInCtrler = createCtrl("Sequenced") { HostInController() }
          top.hostInCtrl = hostInCtrler.ctrl.get
          endState[Ctrl]
        }
        staging(top)
        within(argFringe) {
          val hostOutCtrler = createCtrl("Sequenced") { HostOutController() }
          top.hostOutCtrl = hostOutCtrler.ctrl.get
          argOuts.foreach { mem =>
            HostRead(mem.name.v.getOrElse(mem.sname.get)).input(MemRead().setMem(mem))
          }
          endState[Ctrl]
        }
        streamOuts.foreach { streamOut =>
          within(ControlTree("Streaming")) {
            FringeStreamRead().name(streamOut.name.v).stream(MemRead().setMem(streamOut).out)
          }
        }
        endState[Ctrl]
      }
      argOuts.clear
      streamOuts.clear
      dramAddrs.clear
      nameSpace.clear
      toc(s"New design", "ms")
    }
    setAnnotation(pirTop)
  }

  def setAnnotation(top:Top) = {
    config.getOption[String]("count").foreach { v =>
      val streamCommands = top.collectDown[StreamCommand]()
      val nameMap = streamCommands.map { stream =>
        stream.name.v.getOrElse(stream.sname.get) -> stream
      }.toMap
      v.split(",").toList.sliding(2,2).foreach { 
        case List(key,value) =>
          nameMap.get(key).fold {
            warn(s"No stream with name $key")
          } { stream =>
            stream.count.reset
            info(s"Annotate $key.count=$value")
            stream.count(Finite(value.toLong))
          }
        case List(key) =>
          info(s"Malformat on count annotation. Parsed $key with no value")
        case Nil =>
      }
    }
  }

  def staging(top:Top):Unit

  /* Helper function during staging graph */

  val argOuts = scala.collection.mutable.ListBuffer[Reg]()
  val streamOuts = scala.collection.mutable.ListBuffer[FIFO]()
  val dramAddrs = scala.collection.mutable.Map[DRAM, Reg]()

  implicit class NodeHelper[T](x:T) {
    def sctx(c:String):T = x.to[PIRNode].fold(x) { xx => xx.srcCtx(c); x }
    def name(c:String):T = x.to[PIRNode].fold(x) { xx => xx.name(c); x }
    def setMem(m:Memory):T = x.to[Access].fold(x) { xx => xx.order := m.accesses.size; xx.mem(m) ; x }
  }

  val nameSpace = scala.collection.mutable.Map[String,Any]()
  def lookup[T](name:String) = nameSpace(name).asInstanceOf[T]
  def save[T](name:String, x:T) = { 
    nameSpace(name) = x; 
    x.to[PIRNode].foreach { x =>
      if (x.sname.isEmpty) x.sname(name)
    }
    x.to[DRAM].foreach { x => x.sname(name) }
    x
  }

  def createCtrl[T<:Controller](schedule:String)(newCtrler: => T):T = {
    val tree = ControlTree(schedule)
    beginState(tree)
    val ctrler = newCtrler
    val par = ctrler match {
      case ctrler:LoopController => ctrler.cchain.T.map { _.par }.product
      case ctrler => 1
    }
    tree.par := par
    tree.ctrler(ctrler)
    tree.parent.foreach { parent =>
      parent.ctrler.v.foreach { pctrler =>
        ctrler.parentEn(pctrler.valid)
      }
    }
    ctrler
  }

  def dramAddress(dram:DRAM) = {
    val mem = dramAddrs.getOrElseUpdate(dram, {
      val mem = Reg()
      within(pirTop.argFringe, pirTop.hostInCtrl) {
        val dramAddr = DRAMAddr(dram).name(dram.sid)
        MemWrite().setMem(mem).data(dramAddr) // DRAMDef
      }
      mem
    })
    MemRead().setMem(mem)
  }
  
  def argIn(name:String) = {
    val mem = Reg().name(name)
    within(pirTop.argFringe, pirTop.hostInCtrl) {
      MemWrite().setMem(mem).data(HostWrite(name).name(name))
    }
    mem
  }

  def argOut() = {
    within(pirTop.argFringe) {
      val mem = Reg()
      argOuts += mem
      mem
    }
  }

  def streamIn(fifo:FIFO) = {
    within(ControlTree("Streaming")) {
      FringeStreamWrite().name(fifo.name.v).stream(MemWrite().setMem(fifo).data)
    }
  }

  def streamOut(fifo:FIFO) = {
    streamOuts += fifo
  }

}

