package prism

import prism.util._
import scala.collection.mutable

trait GlobalConfig extends ArgParser

object Config extends GlobalConfig {
  var codegen:Boolean = register("codegen", true) { codegen = _ }

  var debug:Boolean = register("debug", true) { debug = _ }
  var relativeOutDir:String = register("out", "out") { relativeOutDir = _ }
  def outDir = {
    val pir_home = PIR_HOME.getOrElse(throw PIRException(s"Please define PIR_HOME"))
    s"$pir_home$separator$relativeOutDir"
  }
  var verbose:Boolean = register("verbose", false) { verbose = _ }

  lazy val PIR_HOME = sys.env.get("PIR_HOME")
  lazy val SPATIAL_HOME = sys.env.get("SPATIAL_HOME")
}
