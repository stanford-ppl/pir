package prism

import prism.util._
import scala.collection.mutable

class Config(compiler:Compiler) extends ArgParser {

  def defaultName = compiler.getClass.getSimpleName.replace("$","")

  register("codegen", true, "Enable codegen")
  register("debug", true, "Enable debug")
  register[String]("out", "pir/out", "Output directory for pir compiler.")
  register("verbose", false, "Enter verbose mode")
  register[Int]("start-id", "Runner ID to start with")
  register[Int]("end-id", "Runner ID to stop")
  register[String]("name", defaultName, "name of the application")
  register[String]("check-point-path", "pir/out/checkpoint.pir", "Path for checkpoint")
  register("load", false, "Load checkpoint")
  register("save", true, "Save checkpoint")

  def relativeOutDir:String = option("out")
  def debug:Boolean = option[Boolean]("debug")
  def outDir = getOption[String]("out").getOrElse {
    val pir_home = PIR_HOME.getOrElse(throw PIRException(s"Please define PIR_HOME or provide output directory with --out"))
    s"$pir_home${separator}out$separator$name"
  }
  def name = option[String]("name")
  def startRunId = option[Int]("start-id")
  def endRunId = getOption[Int]("end-id")
  def load = getOption[Int]("start-id").fold(true) { _ != 0 } && option[Boolean]("load")
  def save = option[Boolean]("save")
  def checkPointPath = option[String]("check-point-path")
  def enableCodegen = option[Boolean]("codegen")
  def verbose = option[Boolean]("verbose")

  lazy val PIR_HOME = sys.env.get("PIR_HOME")
  lazy val SPATIAL_HOME = sys.env.get("SPATIAL_HOME")
}
