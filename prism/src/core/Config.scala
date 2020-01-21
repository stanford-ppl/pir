package prism

import prism.util._
import scala.collection.mutable

class Config(compiler:Compiler) extends ArgParser {

  def cwd: String = System.getProperty("user.dir")
  def setCwd(path:String) = System.setProperty("user.dir", path)

  def defaultName = compiler.getClass.getSimpleName.replace("$","")

  register("codegen", true, "Enable codegen")
  register("debug", false, "Enable debug")
  register("verbose", false, "Enter verbose mode")
  register[String]("path", info="Generated directory from spatial.")
  register[String]("out", info="Output directory for pir compiler.")
  register[String]("log", info="Log directory for pir compiler.")
  register[Int]("start-id", info="Runner ID to start with")
  register[Int]("end-id", info="Runner ID to stop")
  register[String]("name", defaultName, "name of the application")
  register[String]("ckpt", info="Path for checkpoint")
  register("load", false, "Load checkpoint")
  register("save", true, "Save checkpoint")
  register("rundot", default=true, info="Running graphviz after codegen")

  def appDir = getOption[String]("path").getOrElse { cwd }
  def outDir = getOption[String]("out").getOrElse { buildPath(appDir, "pir", "out") }
  def logDir = getOption[String]("log").getOrElse { buildPath(appDir, "pir", "log") }
  def debug:Boolean = option[Boolean]("debug")
  def name = option[String]("name")
  def startRunId = option[Int]("start-id")
  def endRunId = getOption[Int]("end-id")
  def load = getOption[Int]("start-id").fold(true) { _ != 0 } && option[Boolean]("load")
  def save = option[Boolean]("save")
  def checkPointPath = option[String]("ckpt") match {
    case p if p.forall(_.isDigit) => buildPath(outDir, s"pir$p.ckpt")
    case p => p
  }
  def enableCodegen = option[Boolean]("codegen")
  def verbose = option[Boolean]("verbose")
  def enableRunDot = option[Boolean]("rundot")

  lazy val PIR_HOME = sys.env.get("PIR_HOME")
  lazy val SPATIAL_HOME = sys.env.get("SPATIAL_HOME")
}
