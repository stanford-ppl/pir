package prism

import prism.util._
import scala.collection.mutable

trait GlobalConfig extends ArgParser

object Config extends GlobalConfig {
  register("codegen", true, "Enable codegen")
  register("debug", true, "Enable debug")
  register[String]("out", "Output directory for pir compiler.")
  register("verbose", false, "Enter verbose mode")
  register("start-runid", default=0, "Runner ID to start with")

  def relativeOutDir:String = option("out")
  def debug:Boolean = option[Boolean]("debug")
  def outDir = getOption[String]("out")

  lazy val PIR_HOME = sys.env.get("PIR_HOME")
  lazy val SPATIAL_HOME = sys.env.get("SPATIAL_HOME")
}
