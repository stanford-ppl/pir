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
  lazy val bufferInsertion = new BufferInsertion()
  lazy val globalInsertion = new GlobalInsertion()
  lazy val controlPropogator = new ControlPropogation()
  lazy val psimAnalyzer = new PlastisimAnalyzer()
  lazy val ctxMerging = new ContextMerging()
  lazy val psimParser = new PlastisimLogParser()

  /* Mapping */
  lazy val initializer = new TargetInitializer()
  lazy val hardPruner = new HardConstrainPruner()
  lazy val computePruner = new ComputePruner()
  lazy val dagPruner = new DAGPruner()
  lazy val memoryPruner = new MemoryPruner()
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

  override def initSession = {
    import config._

    // ------- Analysis and Transformations --------
    addPass(enableDot, new ControlTreeDotGen(s"ctop.dot"))
    addPass(enableDot, new ControlTreeHtmlIRPrinter(s"ctrl.html"))
    addPass(enableDot, new PIRIRDotGen(s"top1.dot"))
    addPass(enableTrace && genPsim, dramTraceGen)
    addPass(controlPropogator)
    addPass(enableDot, new PIRIRDotGen(s"top2.dot"))
    addPass(constProp) ==>
    addPass(deadCodeEliminator) ==>
    addPass(enableDot, new PIRIRDotGen(s"top3.dot"))
    addPass(contextInsertion).dependsOn(controlPropogator) ==>
    addPass(enableDot, new PIRIRDotGen(s"top4.dot"))
    addPass(enableDot, new PIRCtxDotGen(s"simple4.dot"))
    addPass(memLowering).dependsOn(contextInsertion) ==>
    addPass(enableDot, new PIRIRDotGen(s"top5.dot"))
    addPass(enableDot, new PIRCtxDotGen(s"simple5.dot"))
    addPass(depDuplications).dependsOn(memLowering)
    addPass(constProp) ==>
    addPass(deadCodeEliminator) ==>
    //addPass(contextAnalyzer) ==>
    addPass(enableDot, new PIRIRDotGen(s"top6.dot"))
    addPass(enableDot, new PIRCtxDotGen(s"simple6.dot"))
    addPass(globalInsertion).dependsOn(deadCodeEliminator)
    
    saveSession("pir/out/pir.ckpt")

    addPass(initializer)
    addPass(enableDot, new ControlTreeDotGen(s"ctop.dot"))
    addPass(new ParamHtmlIRPrinter(s"param.html", spadeParam))
    addPass(enableDot, new PIRCtxDotGen(s"simple7.dot"))
    addPass(enableDot, new PIRIRDotGen(s"top7.dot"))

    // ------- Mapping  --------
    addPass(enableMapping, hardPruner).dependsOn(globalInsertion) ==>
    addPass(enableMapping, memoryPruner) ==>
    addPass(enableMapping, computePruner) ==>
    addPass(enableMapping, dagPruner) ==>
    addPass(enableMapping, matchPruner) ==>
    addPass(enableMapping, placerAndRouter) ==>
    addPass(enableDot, new PIRCtxDotGen(s"simple8.dot"))
    addPass(genPsim, psimAnalyzer).dependsOn(placerAndRouter)
    //addPass(genPsim, psimAnalyzer) //==> // Need to run twice to account for cycle in data flow graph
    //addPass(genPsim, ctxMerging)

    addPass(enableDot, new PIRCtxDotGen(s"simple9.dot"))
    addPass(enableDot, new PIRIRDotGen(s"top9.dot"))
    //addPass(enableDot, new PIRNetworkDotGen(s"net.dot"))
    addPass(enableMapping,report)//.dependsOn(ctxMerging)
    saveSession("pir/out/pir1.ckpt")
    
    // ------- Codegen  --------
    addPass(enableIgraph, igraphGen).dependsOn(psimAnalyzer)
    addPass(genTungsten, tungstenPIRGen).dependsOn(psimAnalyzer)
    addPass(genPsim, prouteLinkGen).dependsOn(psimAnalyzer)
    addPass(genPsim, prouteNodeGen).dependsOn(placerAndRouter, psimAnalyzer)
    addPass(genPsim, psimConfigGen).dependsOn(placerAndRouter, psimAnalyzer, prouteLinkGen) ==>
    addPass(genPsim && runPsim, psimRunner)
    addPass(psimParser)
    addPass(enableDot, new PIRCtxDotGen(s"simple9.dot"))

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
      within(top, topCtrl) {
        top.hostInCtrl = ControlTree("Sequenced")
        top.argFringe = ArgFringe()
        within(argFringe, hostInCtrl) {
          hostInCtrl.ctrler(HostInController().valid(ControllerValid()).done(ControllerDone()))
        }
        staging(top)
        top.hostOutCtrl = ControlTree("Sequenced")
        within(argFringe, hostOutCtrl) {
          top.hostOutCtrl.ctrler(HostOutController().valid(ControllerValid()).done(ControllerDone()))
          argOuts.foreach { mem =>
            HostRead(mem.name.v.getOrElse(mem.sname.get)).input(MemRead().setMem(mem))
          }
        }
        streamOuts.foreach { streamOut =>
          within(ControlTree("Streaming")) {
            FringeStreamRead().name(streamOut.name.v).stream(MemRead().setMem(streamOut).out)
          }
        }
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
    //x.to[DRAMCommand].foreach { x => initDRAMCommand(x) }
    x
  }

  def create[T<:Controller](schedule:String)(newCtrler: => T):T = {
    val tree = ControlTree(schedule)
    beginState(tree)
    val ctrler = newCtrler.valid(ControllerValid()).done(ControllerDone())
    tree.ctrler(ctrler)
    tree.parent.get.ctrler.v.foreach { pctrler =>
      ctrler.parentEn(pctrler.valid.T)
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

  //def initDRAMCommand(n:DRAMCommand) = {
    //val ctrl = ControlTree("Pipelined")
    //(n.neighbors :+ n).foreach { x =>
      //val n = x.as[PIRNode]
      //n.ctrl.reset
      //n.ctrl(ctrl)
    //}
  //}

}

