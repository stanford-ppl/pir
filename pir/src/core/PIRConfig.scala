package pir

object PIRConfig extends prism.GlobalConfig {
  import Config._

  var arch:String = register("arch", default="SMeshCB4x4", info="Default architecture for mapping") { arch = _ }
  var saveDesign:Boolean = register("save-pir", default=false, info="Save IR into a file") { saveDesign = _ }
  var loadDesign:Boolean = register("load-pir", default=false, info="Load IR from a file") { loadDesign = _ }

  def enableCodegen = Config.codegen
  def verbose = Config.verbose

  var enableSplitting:Boolean = register("splitting", default=true) { enableSplitting = _ }
  var genCtrl:Boolean = register("ctrl", default=true) { genCtrl = _ }
  var mapping:Boolean = register("mapping", default=true) { mapping = _ }
  var aggressive_dce:Boolean = register("ag-dce", default=true) { aggressive_dce = _ }
  var genPlastisim:Boolean = register("psim", default=true) { v => genPlastisim = v && genCtrl && enableCodegen }
  var loadTrace:Boolean = register("trace", default=false) { v => loadTrace = v && genPlastisim }

  def debug:Boolean = Config.debug
  var routingVerbosity:Int = register("verbosity-routing", 0) { v => routingVerbosity = if (debug) v else 0 }
  var breakPoint:Boolean = debug && register("bp", false) { v => breakPoint = v && debug }
  var openDot:Boolean = register("open", false) { v => openDot = v && enableCodegen }

}
