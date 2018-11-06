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
    if (args.contains("--help")) {
      config.printUsage
      System.exit(0)
    }
    config.setOption(args.toList)
  }

  def main(args: Array[String]): Unit = {
    var succeed = false
    try {
      setArgs(args)
      loadSession
      reset
      info(s"Output directory set to ${cstr(Console.CYAN,outDir)}")
      initSession
      succeed = runSession
    } catch { 
      case e:Exception =>
        err(e, exception=false)
        handle(e)
    }
    if (!succeed) err(s"Compilation failed", false)
  }
}
