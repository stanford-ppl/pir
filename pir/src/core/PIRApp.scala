package pir

import pir.node._
import prism.graph._

import prism._

trait PIRApp extends PIR with Logging {
  
  //def dramDefault = arch.top.dram.dramDefault

  //def setDram(start:Int, array:Iterable[AnyVal]) = {
    //array.zipWithIndex.foreach { case (a, i) => dramDefault(start + i) = a }
  //}

  //override def loadDesign = {
    //super.loadDesign
    //arch = getArch(PIRConfig.arch)
    //info(s"Configuring spade $arch ...")
    //arch.newDesign
  //}

  //def newDesign = {
    //design = new PIRDesign()
    //withLog(compiler.outDir, "main.log", append=false) {
      //withParentCtrl(design.top, design.top.topController) {
        //main(design)
      //}
    //}
    //info(s"Finishing graph construction for ${this}")
    //arch = getArch(PIRConfig.arch)
    //info(s"Configuring spade $arch ...")
    //arch.newDesign
  //}

  //override def saveDesign = {
    //val savedPmap = design.pirmeta.pirMap
    //design.pirmeta.pirMap = Right(PIRMap.empty) // Clear mapping before saving
    //super.saveDesign
    //design.pirmeta.pirMap = savedPmap
  //}

  //def getArch(name:String) = {
    //val runtimeMirror = universe.runtimeMirror(getClass.getClassLoader)
    //val module = runtimeMirror.staticModule("arch." + name)
    //val obj = runtimeMirror.reflectModule(module)
    //obj.instance.asInstanceOf[Spade]
  //}
  
  override def loadDesign(loaded:Any) = {
    _pirTop = Some(loaded.asInstanceOf[Top])
  }

  override def loadSession = {
    super.loadSession
    if (_pirTop.isEmpty) {
      tic(s"Creating new design ...")
      _pirTop = Some(staging)
      toc(s"New design", "ms")
    }
  }

  override def getDesign = _pirTop.get
  
  def staging:Top = {
    val top = Top()
    within(top) {
      within(ArgFringe()) {
        within(top.hostInCtrl) {
          top.hostInCtrl.ctrler(HostInController().valid(ControllerValid()).done(ControllerDone()))
          HostWrite()
        }
        within(top.hostOutCtrl) {
          top.hostOutCtrl.ctrler(HostOutController().valid(ControllerValid()).done(ControllerDone()))
          HostRead()

        }
      }
    }
    beginState(top)
    beginState(top.topCtrl)
    staging(top)
    endState(top)
    endState(top.topCtrl)
    top
  }

  def staging(top:Top):Unit

}

