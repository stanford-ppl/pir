package pir
package pass

import pir.node._
import prism.graph._
import prism.util._

class SpatialPIRGenStaging(implicit compiler:PIRApp) extends PIRTransformer {

  override def runPass = {
    if (compiler.pirenv._states.isEmpty) stageGraph
    setAnnotation(pirTop)
  }

  def stageGraph = {
    compiler.pirenv.createNewState
    tic
    val top = Top()
    states.pirTop = top
    import top._
    within(top) {
      val topCtrler = stage(createCtrl(Sequenced) { TopController() })
      top.topCtrl = topCtrler.ctrl.get
      topCtrl.ctrler := topCtrler
      top.argFringe = stage(ArgFringe())
      within(argFringe) {
        val hostInCtrler = stage(createCtrl(Sequenced) { HostInController() })
        top.hostInCtrl = hostInCtrler.ctrl.get
        endState[Ctrl]
      }
      compiler.staging(top)
      topCtrl.children.filterNot { _ == hostInCtrl }.foreach { accelTopCtrl =>
        pirTop.accelTopCtrl = accelTopCtrl
      }
      within(argFringe) {
        val hostOutCtrler = stage(createCtrl(Sequenced) { HostOutController() })
        top.hostOutCtrl = hostOutCtrler.ctrl.get
        argOuts.foreach { processArgOut }
        argOuts.clear
        endState[Ctrl]
      }
      streamOuts.foreach { processStreamOut }
      streamOuts.clear
      endState[Ctrl]
    }
    dramAddrs.clear
    nameSpace.clear
    toc(s"New design", "ms")
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

  /* Helper function during staging graph */

  implicit class NodeHelper[T](x:T) {
    def sctx(c:String):T = x.to[PIRNode].fold(x) { xx => xx.srcCtx(c); x }
    def name(c:String):T = x.to[PIRNode].fold(x) { xx => xx.name(c); x }
    def progorder(c:Int):T = x.to[PIRNode].fold(x) { xx => xx.progorder(c); x }
  }

  val nameSpace = scala.collection.mutable.Map[String,Any]()
  def lookup[T](name:String) = nameSpace(name).asInstanceOf[T]
  def save[T](name:String, x:T) = { 
    nameSpace(name) = x; 
    x.to[PIRNode].foreach { x => if (x.sname.isEmpty) x.sname(name) }
    x match {
      case n:DRAM => n.sname(name)
      case n:Counter => analyzeCounterRange(n)
      case n:LoopController => analyzeLoopRange(n)
      case _ =>
    }
    x.to[PIRNode].foreach { x => stage(x) }
    x
  }

  def createCtrl[T<:Controller](schedule:CtrlSchedule)(newCtrler: => T):T = {
    val tree = ControlTree(schedule)
    beginState(tree)
    val ctrler = newCtrler
    val par = ctrler.par.getOrElseUpdate { 
      ctrler match {
        case ctrler:LoopController => ctrler.cchain.T.map { _.par }.product
        case ctrler:SplitController => tree.parent.get.par.get
        case ctrler => 1
      }
    }
    tree.par := par
    tree.ctrler(ctrler)
    tree.isLoop := ctrler.isInstanceOf[LoopController]
    tree.isForever := ctrler.isForever && ctrler.to[LoopController].fold(true) { !_.stopWhen.isConnected }
    tree.parent.foreach { parent =>
      parent.ctrler.v.foreach { pctrler =>
        ctrler.parentEn(pctrler.childDone)
      }
    }
    ctrler
  }

  val dramAddrs = scala.collection.mutable.Map[DRAM, Reg]()
  def dramAddress(dram:DRAM) = {
    val mem = dramAddrs.getOrElseUpdate(dram, {
      val mem = stage(Reg())
      within(pirTop.argFringe, pirTop.hostInCtrl) {
        val dramAddr = stage(DRAMAddr(dram).name(dram.sid))
        stage(MemWrite().setMem(mem).data(dramAddr)) // DRAMDef
      }
      mem
    })
    stage(MemRead().setMem(mem))
  }
  
  def argIn(name:String) = {
    val mem = stage(Reg().name(name))
    within(pirTop.argFringe, pirTop.hostInCtrl) {
      stage(MemWrite().setMem(mem).data(stage(HostWrite().name(name))))
    }
    mem
  }

  def hostIO():Reg = {
    err(s"Plasticine doesn't support HostIO")
  }

  val argOuts = scala.collection.mutable.ListBuffer[Reg]()
  def argOut() = {
    within(pirTop.argFringe) {
      val mem = stage(Reg())
      argOuts += mem
      mem
    }
  }
  def processArgOut(mem:Reg) = {
    stage(HostRead().input(MemRead().setMem(mem)))
  }

  def streamIn(fifos:List[FIFO], bus:Bus) = {
    val name = longestCommonSubstring(fifos.flatMap { _.name.v }).map { _.strip("_") }
    bus match {
      case DRAMBus =>
      case bus =>
        within(ControlTree(Streaming)) {
          val sw = stage(FringeStreamWrite(bus))
          name.foreach { name => sw.name(name) }
          val data = fifos.map { fifo =>
            stage(MemWrite().setMem(fifo).presetVec(fifo.banks.get.head).tp.mirror(fifo.tp)).data
          }
          val count = assertUnify(fifos, s"$sw.count")(_.count.v).get
          count.foreach { c => sw.count(c) }
          fifos.foreach { _.count.reset }
          sw.addStreams(data)
          sw
        }
    }
  }

  val streamOuts = scala.collection.mutable.ListBuffer[(List[FIFO],Bus)]()
  def streamOut(fifos:List[FIFO], bus:Bus) = {
    streamOuts += ((fifos, bus))
  }
  def processStreamOut(streamOut:(List[FIFO], Bus)) = {
    val (fifos, bus) = streamOut
    val name = longestCommonSubstring(fifos.flatMap { _.name.v }).map { _.strip("_") }
    bus match {
      case DRAMBus =>
      case bus =>
        within(ControlTree(Streaming)) {
          val reads = fifos.map { fifo =>
            stage(MemRead().setMem(fifo).presetVec(fifo.banks.get.head)).out
          }
          val sr = stage(FringeStreamRead(bus).addStreams(reads))
          name.foreach { name => sr.name(name) }
          if (bus.withLastBit && !config.asModule) {
            val writer = stage(MemWrite().tp(Bool).data(sr.lastBit))
            within(pirTop.argFringe) {
              val lastBit = stage(FIFO().addAccess(writer).tp(Bool).banks(List(1)).name(s"${name.getOrElse(sr.toString)}_lastBit"))
              within(pirTop.hostOutCtrl) {
                val read = stage(MemRead().setMem(lastBit))
                stage(HostRead().input(read))
              }
            }
          }
        }
    }
  }

  def analyzeCounterRange(n:Counter) = dbgblk(s"analyzeCounterRange($n)") {
    val min = n.min.T
    val step = n.step.T
    val max = n.max.T
    val par = n.par
    val (constValids, constIters) = (n.isForever, min, step, max) match {
      case (false, Some(Const(min:Int)), Some(Const(step:Int)), Some(Const(max:Int))) if (max <= min && step > 0) | (max >= min && step < 0) =>
        dbg(s"Loop will not run.")
        (List.fill(par)(Some(false)), List.fill(par)(Some(min)))
      case (false, Some(Const(min:Int)), Some(Const(step:Int)), Some(Const(max:Int))) =>
        var bound = ((max - min) /! step) % par
        if (bound == 0) {
          bound = par
        }
        val fullyUnrolled = ((max - min) /! step) <= par
        dbg(s"Constant loop bounds min=$min, step=$step, max=$max, par=$par bound=$bound. fullyUnrolled=$fullyUnrolled")
        (List.tabulate(par) { i => 
          if (i < bound) Some(true) 
          else if (i >= max) Some(false)
          else None
        },
        List.tabulate(par) { i => if (fullyUnrolled) Some(min + step*i) else None })
      case (false, Some(Const(min:Int)), _, Some(Const(max:Int))) if max > min =>
        dbg(s"None constant loop bounds min=$min, step=$step, max=$max, par=$par")
        (List.tabulate(par) { i => 
          if (i == 0) Some(true) 
          else if (i >= max) Some(false)
          else None
        }, 
        List.tabulate(par) { i => None })
      case (true, _, _, _) => // forever counter
        (List.tabulate(par) { i => Some(true) }, List.tabulate(par) { i => None })
      case _ =>
        (List.fill(par) { None }, List.fill(par) { None })
    }
    dbg(s"$n.constValids=[${constValids.map { _.getOrElse("unknown") }.mkString(",")}]")
    dbg(s"$n.constIters=[${constIters.map { _.getOrElse("unknown") }.mkString(",")}]")
    n.constValids := constValids
    n.constIters := constIters
  }

  def analyzeLoopRange(n:LoopController) = dbgblk(s"analyzeLoopRange($n)"){
    val laneValids = n.cchain.T.foldLeft[List[Option[Boolean]]](Nil) { 
      case (Nil, ctr) => List.tabulate(ctr.par) { i => ctr.constValids.get(i) }
      case (prev, ctr) => 
        prev.flatMap { valid => 
          (0 until ctr.par).map { i => 
            zipMap(valid, ctr.constValids.get(i)) { _ && _ }
          }
        }
    }
    dbg(s"$n.laneValids=[${laneValids.map { _.getOrElse("unknown") }.mkString(",")}]")
    n.constLaneValids := laneValids
  }

}
