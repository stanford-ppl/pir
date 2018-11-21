package prism

import prism.graph._
import prism.util.{FileManager, ArgLoader}

import scala.collection.mutable

trait Compiler extends FileManager with ArgLoader with Session with Logging {

  implicit val compiler:this.type = this

  lazy val config = new Config(this)

  def outDir = config.outDir

  def reset = { 
    if (config.startRunId==0) {
      clearLogs(outDir)
    }
  } 

  def handle(e:Throwable):Try[Boolean]

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
    withLog(outDir, s"compiler.log") {
      tic
      Try {
        setArgs(args)
        loadSession
        reset
        info(s"Output directory set to ${cstr(Console.CYAN,outDir)}")
        initSession
        runSession
      }.recoverWith { case e:Throwable =>
        err(e, exception=false)
        handle(e)
      } match {
        case Success(true) =>
          info(s"Compilation succeed in ${toc("s")}s")
        case Success(false) =>
          err(s"Compilation failed in ${toc("s")}s", false)
        case Failure(e) =>
          err(s"Compilation failed in ${toc("s")}s", false)
          throw e
      }
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
