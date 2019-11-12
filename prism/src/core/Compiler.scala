package prism

import prism.graph._
import prism.util.{FileIOUtil, ArgLoader}

import scala.collection.mutable

trait Compiler extends FileIOUtil with ArgLoader with Session with Logging {

  implicit val compiler:this.type = this

  lazy val config = new Config(this)

  def reset = { 
    if (config.startRunId==0) {
      clearLogs(config.outDir)
    }
  } 

  def handle(e:Throwable):Try[Boolean]
  def handle:Unit = {}

  def setArgs(inputArgs: Array[String]):Unit = {
    val args = loadArgs(inputArgs)
    info(s"args=[${args.mkString(", ")}]")
    if (args.contains("--help")) {
      config.printUsage
      System.exit(0)
    }
    config.setOption(args.toList, config.outDir)
  }

  def main(args: Array[String]): Unit = {
    setArgs(args)
    tic
    Try {
      loadSession
      reset
      info(s"Output directory set to ${cstr(Console.CYAN,config.outDir)}")
      initSession
      runSession
    }.recoverWith { case e:Throwable =>
      err[Unit](e, exception=false)
      handle(e)
    } match {
      case Success(true) =>
        info(s"Compilation succeed in ${toc("s")}s")
      case Success(false) =>
        err[Unit](s"Compilation failed in ${toc("s")}s", false)
        System.exit(-1)
      case Failure(e) =>
        err[Unit](s"Compilation failed in ${toc("s")}s", false)
        throw e
    }
  }

  def show(msg:String, show:Boolean=config.verbose) = {
    if (show) print(msg)
    dbg(msg)
  }

  def showln(msg:String, show:Boolean=config.verbose) = {
    if (show) println(msg)
    dbg(msg)
  }

}
