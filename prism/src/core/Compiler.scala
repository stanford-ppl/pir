package prism

import prism.node._
import prism.util.{FileManager, ArgLoader}

import scala.collection.mutable

trait Compiler extends FileManager with ArgLoader {

  implicit val compiler:this.type = this

  def name = getClass().getSimpleName().replace("$", "")
  override def toString = name
  def relativeOutDir = buildPath(Config.relativeOutDir,name)
  def outDir = buildPath(Config.outDir, name)

  var session:Session = _

  def setSession(sess:Session) = session = sess

  def reset = { 
    session = null
    clearLogs(outDir)
  } 

  def handle(e:Exception):Unit

  val sessionPath = s"${outDir}${separator}${name}.sess"

  val configs:List[GlobalConfig]

  def setArgs(inputArgs: Array[String]):Unit = {
    val args = loadArgs(inputArgs)
    info(s"args=[${args.mkString(", ")}]")
    if (args.contains("--help")) {
      configs.foreach(_.printUsage)
      System.exit(0)
    }
    configs.foreach(_.setOption(args.toList))
  }

  type D <: Design
  var design:D = _

  def load:Boolean
  def save:Boolean

  val designPath:String
  def loadDesign = design = loadFromFile[D](designPath)
  def newDesign:Unit
  def saveDesign:Unit = saveToFile(design, designPath)

  def loadSession:Unit = {
    loadDesign
    setSession(loadFromFile[Session](sessionPath))
    initSession
  }

  def newSession:Unit = {
    tic
    newDesign
    toc("New design","ms")
    setSession(new Session())
    initSession
  }

  def initSession:Unit = {}

  def startSession = {
    try {
      if (load) loadSession else newSession
    } catch {
      case e@(_:SessionRestoreFailure | _:java.io.InvalidClassException | _:java.io.FileNotFoundException | _:ClassCastException) if load =>
        warn(s"Restore session failed: ${e}. Creating a new session ...")
        newSession
      case e:Throwable => throw e
    }
  }

  def runSession = session.run

  def main(args: Array[String]): Unit = {
    tic
    try {
      reset
      setArgs(args)
      startSession
      runSession
    } catch { 
      case e:Exception =>
        err(e, exception=false)
        handle(e)
    }
    toc(s"compilation", "s")
  }
}
