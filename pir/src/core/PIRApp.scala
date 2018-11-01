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
  
  override def initSession:Unit = {
    if (config.load)  {
      try {
        _pirTop = Some(loadFromFile[Top](config.checkPointPath))
      } catch {
        case e@(_:SessionRestoreFailure | _:java.io.InvalidClassException | _:java.io.FileNotFoundException | _:ClassCastException) =>
          warn(s"Restore design failed: ${e}")
          config.getArgOption[Int]("start-id").get.updateValue(0)
        case e:Throwable => throw e
      }
    }
    if (_pirTop.isEmpty) {
      tic(s"Creating new design ...")
      _pirTop = Some(staging)
      toc(s"New design", "ms")
    }
    super.initSession
  }

  override def runSession = {
    val succeed = super.runSession
    if (config.save) {
      saveToFile(pirTop, config.checkPointPath)
      loadFromFile[Top](config.checkPointPath)
    }
    succeed
  }

  //override def main(args:Array[String]): Unit = {
    //val (top, topCtrl) = staging
    //new prism.codegen.BasicIRDotGen(".", s"top.dot", top).run
    //new prism.codegen.BasicIRDotGen(".", s"ctrl.dot", topCtrl).run
    //val path = buildPath(config.outDir, "top.pir")
    //saveToFile(top, path)
    //val loaded = loadFromFile[Top](path)
    //new prism.codegen.BasicIRDotGen(".", s"loaded.dot", loaded).run
  //}
  
  def staging:Top

}

