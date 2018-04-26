package prism

import prism.util._
import scala.collection.mutable

trait GlobalConfig extends ArgParser

object Config extends GlobalConfig {
  var codegen:Boolean = register("codegen", true) { codegen = _ }

  var debug:Boolean = register("debug", true) { debug = _ }
  var outDir:String = register("out", "out") { outDir = _ }
  var verbose:Boolean = register("verbose", false) { verbose = _ }

  lazy val SPATIAL_HOME = sys.env.get("SPATIAL_HOME")
}
