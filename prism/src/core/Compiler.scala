package prism

import prism.graph._
import prism.util.{FileManager, ArgLoader}

import scala.collection.mutable

trait Compiler extends FileManager with ArgLoader with Session {

  implicit val compiler:this.type = this

  lazy val config = new Config(this)

  def outDir = config.outDir

  def reset = { 
    if (config.startRunId==0) {
      clearLogs(outDir)
    }
  } 

  def handle(e:Exception):Unit

  def setArgs(inputArgs: Array[String]):Unit = {
    val args = loadArgs(inputArgs)
    info(s"args=[${args.mkString(", ")}]")
    if (args.contains("--help") || args.size < 1) {
      config.printUsage
      System.exit(0)
    }
    args(0) = s"--name=${args(0)}"
    config.setOption(args.toList)
  }

  //var _design:Option[Design] = _
  //def design:Design = _design.get

  ////TODO: move this into pir
  //def initDesign = if (config.load) {
    //try {
      //_design = Some(loadFromFile[Design](config.checkPointPath))
    //} catch {
      //case e@(_:SessionRestoreFailure | _:java.io.InvalidClassException | _:java.io.FileNotFoundException | _:ClassCastException) =>
        //warn(s"Restore design failed: ${e}")
      //case e:Throwable => throw e
    //}
    //if (_design.isEmpty) {
      //info("Creating new design")
      //tic
      //_design = Some(config)
      //toc(s"New design")
    //}
  //}

  //def saveDesign:Unit = if (config.save) saveToFile(design, config.checkPointPath)

  def initSession:Unit = {}

  def main(args: Array[String]): Unit = {
    var succeed = false
    try {
      setArgs(args)
      reset
      info(s"Output directory set to ${cstr(Console.CYAN,outDir)}")
      initSession
      succeed = runSession
    } catch { 
      case e:Exception =>
        err(e, exception=false)
        handle(e)
    }
    if (!succeed) System.exit(1)
  }
}
