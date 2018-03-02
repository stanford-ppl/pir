package prism

import prism.util._

import scala.collection.mutable
import java.io._

trait Compiler {

  implicit val compiler:this.type = this

  def name = getClass().getSimpleName().replace("$", "")
  override def toString = name
  def outDir = Config.outDir + File.separator + name

  var _session:Session = _
  lazy val session = _session

  def reset = { 
    _session = null
    clearLogs(outDir)
  } 

  def handle(e:Exception):Unit

  val sessionPath = s"${outDir}${File.separator}${name}.sess"

  val configs:List[GlobalConfig]

  def setArgs(args: Array[String]):Unit = {
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

  def initDesign = if (load) loadDesign else newDesign

  def initSession = {
    try {
      _session = if (load) loadFromFile[Session](sessionPath) else new Session() // Load session
    } catch {
      case e:SessionRestoreFailure =>
        warn(s"Restore session failed: ${e}. Creating a new session ...")
        _session = new Session()
      case e:Throwable => throw e
    }
  }

  def runSession = {
    session.run
  }

  def main(args: Array[String]): Unit = {
    info(s"args=[${args.mkString(", ")}]")
    try {
      reset
      setArgs(args)
      initDesign
      initSession
      runSession
      if (save) saveDesign
      if (save) session.saveSession(sessionPath)
    } catch { 
      case e:Exception =>
        errmsg(e)
        handle(e)
    }
  }
}
