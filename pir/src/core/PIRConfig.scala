package pir

object PIRConfig extends prism.GlobalConfig {
  import Config._

  var arch:String = register("arch", default="SMeshCB4x4", info="Default architecture for mapping") { arch = _ }
  var saveDesign:Boolean = register("save-pir", default=true, info="Save IR into a file") { saveDesign = _ }
  var loadDesign:Boolean = register("load-pir", default=false, info="Load IR from a file") { loadDesign = _ }

  var genDot:Boolean = register("dot", default=true) { genDot = _ }
  var mapping:Boolean = register("mapping", default=true) { mapping = _ }
  var ctrl:Boolean = register("ctrl", default=true) { ctrl = _ }
  var aggressive_dce:Boolean = register("ag-dce", default=true) { aggressive_dce = _ }

  def codegen = Config.codegen
  def verbose = Config.verbose

  def debug:Boolean = Config.debug
  var debugMapper:Boolean = debug && register("dbg-mapper", true) { debugMapper = _ }
  var debugRouting:Boolean = debugMapper && register("dbg-routing", true) { debugRouting = _ }
  var routingVerbosity:Int = register("verbosity-routing", 0) { routingVerbosity = _ }
  var breakPoint:Boolean = debug && register("bp", false) { breakPoint = _ }

  var openDot:Boolean = register("open", false) { openDot = _ }

}
