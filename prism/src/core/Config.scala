package prism

import prism.util._
import scala.collection.mutable

trait GlobalConfig extends ArgParser

object Config extends GlobalConfig {
  register("codegen", true, "Enable codegen")
  register("debug", true, "Enable debug")
  register("out", "out", "Relative output directory for pir compiler. Relative to $PIR_HOME")
  register("verbose", false, "Enter verbose mode")

  def relativeOutDir:String = option("out")
  def debug:Boolean = option[Boolean]("debug")
  def outDir = {
    val pir_home = PIR_HOME.getOrElse(throw PIRException(s"Please define PIR_HOME"))
    s"$pir_home$separator$relativeOutDir"
  }

  lazy val PIR_HOME = sys.env.get("PIR_HOME")
  lazy val SPATIAL_HOME = sys.env.get("SPATIAL_HOME")
}
