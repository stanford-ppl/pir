package pir

import pir.node._
import prism.graph._

import prism._

trait PIRApp extends PIR with Logging {
  
  //def getArch(name:String) = {
    //val runtimeMirror = universe.runtimeMirror(getClass.getClassLoader)
    //val module = runtimeMirror.staticModule("arch." + name)
    //val obj = runtimeMirror.reflectModule(module)
    //obj.instance.asInstanceOf[Spade]
  //}
  
  override def getDesign = {
    states
  }
  
  override def loadDesign(loaded:Any) = {
    _states = Some(loaded.asInstanceOf[States])
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
            HostRead(mem.name.get).input(MemRead().setMem(mem))
          }
        }
      }
      argOuts.clear
      dramAddrs.clear
      toc(s"New design", "ms")
    }
  }

  def staging(top:Top):Unit

  /* Helper function during staging graph */

  val argOuts = scala.collection.mutable.ListBuffer[Reg]()
  val dramAddrs = scala.collection.mutable.Map[DRAM, Reg]()

  implicit class NodeHelper[T](x:T) {
    def sctx(c:String):T = x.to[PIRNode].fold(x) { xx => xx.srcCtx(c); x }
    def name(c:String):T = x.to[PIRNode].fold(x) { xx => xx.name(c); x }
    def setMem(m:Memory):T = x.to[Access].fold(x) { xx => xx.order := m.accesses.size; xx.mem(m) ; x }
    def sname(c:String):T = x.to[PIRNode].fold(x) { case xx if xx.sname.isEmpty => xx.sname(c); x; case _ => x }
  }

  def create[T<:Controller](schedule:String)(newCtrler: => T):T = {
    val tree = ControlTree(schedule)
    beginState(tree)
    val ctrler = newCtrler.valid(ControllerValid()).done(ControllerDone())
    tree.ctrler(ctrler)
    tree.parent.get.as[ControlTree].ctrler.v.foreach { pctrler =>
      ctrler.parentEn(pctrler.valid.T)
    }
    ctrler
  }

  def dramAddress(dram:DRAM) = {
    val mem = dramAddrs.getOrElseUpdate(dram, {
      val mem = Reg()
      within(pirTop.argFringe, pirTop.hostInCtrl) {
        MemWrite().setMem(mem).data(DRAMAddr(dram).name(dram.sid)) // DRAMDef
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


}

