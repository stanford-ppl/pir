package prism

import prism.util._
import scala.collection.mutable

class Config(compiler:Compiler) extends ArgParser {

  var cwd: String = new java.io.File(".").getAbsolutePath

  def defaultName = compiler.getClass.getSimpleName.replace("$","")

  register("codegen", true, "Enable codegen")
  register("debug", false, "Enable debug")
  register[String]("out", s"${System.getProperty("user.dir")}${separator}pir/out", "Output directory for pir compiler.")
  register("verbose", false, "Enter verbose mode")
  register[Int]("start-id", info="Runner ID to start with")
  register[Int]("end-id", info="Runner ID to stop")
  register[String]("name", defaultName, "name of the application")
  register[String]("ckpt", "pir/out/pir.ckpt", "Path for checkpoint")
  register("load", false, "Load checkpoint")
  register("save", true, "Save checkpoint")

  def relativeOutDir:String = option("out")
  def debug:Boolean = option[Boolean]("debug")
  def outDir = option[String]("out")
  def name = option[String]("name")
  def startRunId = option[Int]("start-id")
  def endRunId = getOption[Int]("end-id")
  def load = getOption[Int]("start-id").fold(true) { _ != 0 } && option[Boolean]("load")
  def save = option[Boolean]("save")
  def checkPointPath = option[String]("ckpt") match {
    case p if p.forall(_.isDigit) => s"pir/out/pir$p.ckpt"
    case p => p
  }
  def enableCodegen = option[Boolean]("codegen")
  def verbose = option[Boolean]("verbose")

  lazy val PIR_HOME = sys.env.get("PIR_HOME")
  lazy val SPATIAL_HOME = sys.env.get("SPATIAL_HOME")
}
