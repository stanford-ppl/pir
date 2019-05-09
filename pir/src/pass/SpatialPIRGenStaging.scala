package pir
package pass

import pir.node._
import prism.graph._
import prism.util._

class SpatialPIRGenStaging(implicit compiler:PIRApp) extends PIRPass {

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
      val topCtrler = stage(createCtrl("Sequenced") { TopController() })
      top.topCtrl = topCtrler.ctrl.get
      topCtrl.ctrler := topCtrler
      top.argFringe = stage(ArgFringe())
      within(argFringe) {
        val hostInCtrler = stage(createCtrl("Sequenced") { HostInController() })
        top.hostInCtrl = hostInCtrler.ctrl.get
        endState[Ctrl]
      }
      compiler.staging(top)
      within(argFringe) {
        val hostOutCtrler = stage(createCtrl("Sequenced") { HostOutController() })
        top.hostOutCtrl = hostOutCtrler.ctrl.get
        argOuts.foreach { mem =>
          stage(HostRead().input(MemRead().setMem(mem)))
        }
        endState[Ctrl]
      }
      streamOuts.foreach { streamOut =>
        within(ControlTree("Streaming")) {
          stage(FringeStreamRead().name.mirror(streamOut.name).stream(MemRead().setMem(streamOut).out))
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

  val argOuts = scala.collection.mutable.ListBuffer[Reg]()
  val streamOuts = scala.collection.mutable.ListBuffer[FIFO]()
  val dramAddrs = scala.collection.mutable.Map[DRAM, Reg]()

  implicit class NodeHelper[T](x:T) {
    def sctx(c:String):T = x.to[PIRNode].fold(x) { xx => xx.srcCtx(c); x }
    def name(c:String):T = x.to[PIRNode].fold(x) { xx => xx.name(c); x }
  }

  val nameSpace = scala.collection.mutable.Map[String,Any]()
  def lookup[T](name:String) = nameSpace(name).asInstanceOf[T]
  def save[T](name:String, x:T) = { 
    nameSpace(name) = x; 
    x.to[PIRNode].foreach { x =>
      if (x.sname.isEmpty) x.sname(name)
    }
    x.to[DRAM].foreach { x => x.sname(name) }
    x.to[PIRNode].foreach { x => stage(x) }
    x
  }

  def createCtrl[T<:Controller](schedule:String)(newCtrler: => T):T = {
    val tree = ControlTree(schedule)
    beginState(tree)
    val ctrler = newCtrler
    val par = ctrler.par.getOrElseUpdate { 
      ctrler match {
        case ctrler:LoopController => ctrler.cchain.T.map { _.par }.product
        case ctrler => 1
      }
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

  def argOut() = {
    within(pirTop.argFringe) {
      val mem = stage(Reg())
      argOuts += mem
      mem
    }
  }

  def streamIn(fifo:FIFO) = {
    within(ControlTree("Streaming")) {
      val sw = stage(FringeStreamWrite().name.mirror(fifo.name))
      stage(MemWrite().setMem(fifo).data(sw.stream))
      sw
    }
  }

  def streamOut(fifo:FIFO) = {
    streamOuts += fifo
  }

}
