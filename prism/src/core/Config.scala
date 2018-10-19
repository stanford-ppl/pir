package prism

import prism.util._
import scala.collection.mutable

class Config(compiler:Compiler) extends ArgParser {

  register("codegen", true, "Enable codegen")
  register("debug", true, "Enable debug")
  register[String]("out", "Output directory for pir compiler.")
  register("verbose", false, "Enter verbose mode")
  register("start-runid", default=0, "Runner ID to start with")
  register[String]("name", compiler.getClass.getSimpleName.replace("$",""), "name of the application")

  def relativeOutDir:String = option("out")
  def debug:Boolean = option[Boolean]("debug")
  def outDir = getOption[String]("out").getOrElse {
    val pir_home = PIR_HOME.getOrElse(throw PIRException(s"Please define PIR_HOME or provide output directory with --out"))
    s"$pir_home${separator}out$separator$name"
  }
  def name = option[String]("name")
  def load = option[Boolean]("load")
  def save = option[Boolean]("save")
  def checkPointPath = option[String]("check-point-path")
  def startRunId = option[Int]("startRunId")

  lazy val PIR_HOME = sys.env.get("PIR_HOME")
  lazy val SPATIAL_HOME = sys.env.get("SPATIAL_HOME")
}
