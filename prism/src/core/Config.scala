package prism

import scala.collection.mutable

trait GlobalConfig extends ArgParser

object Config extends GlobalConfig {
  var codegen:Boolean = register("codegen", false) { codegen = _ }

  var debug:Boolean = register("debug", true) { debug = _ }
  var debugCodegen:Boolean = debug && register("debug-codegen", true) { debugCodegen = _ }
  var outDir:String = register("out", "out") { outDir = _ }
  var verbose:Boolean = register("verbose", false) { verbose = _ }

  lazy val SPATIAL_HOME:String = {
    sys.env.get("SPATIAL_HOME").getOrElse(s"Please set SPATIAL_HOME enviroment variable!")
  }

}
