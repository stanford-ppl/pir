package pirc

import pirc.util._

import scala.collection.mutable
import java.io._

trait Design {

  implicit val design:Design = this

  def name = getClass().getSimpleName().replace("$", "")
  override def toString = name
  def outDir = Config.outDir + File.separator + name

  private var nextSym = 0
  def nextId = if (staging) {nextSym += 1; nextSym } else 0

  var staging = true

  var _session:Session = _
  lazy val session = _session

  def reset = { 
    _session = null
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

  def load:Boolean
  def save:Boolean

  def loadDesign:Unit
  def newDesign:Unit
  def saveDesign:Unit

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
