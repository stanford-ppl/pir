package pir

object PIRConfig extends prism.GlobalConfig {
  register("arch", default="SMeshCB4x4", info="Default architecture for mapping")
  register("save-pir", default=false, info="Save IR into a file") 
  register("load-pir", default=false, info="Load IR from a file")
  register("ctrl", default=true, info="Enable control logic generation")
  register("splitting", default=true, info="Enable splitting")
  register("mapping", default=true, info="Enable mapping")
  register("ag-dce", default=true, info="Enable aggressive dead code elimination")
  register("psim", default=true, info="Enable generation to plastisim")
  register("trace", default=false, info="Enable trace generation")
  register("bp", default=false, info="Enable break point")
  register("open", default=false, info="Open dot graph after codegen")

  def arch = option[String]("arch")
  def saveDesign = option[Boolean]("save-pir")
  def loadDesign = option[Boolean]("load-pir")
  def genCtrl = option[Boolean]("ctrl")
  def enableSplitting = option[Boolean]("splitting") && arch != "Asic"
  def enableMapping = option[Boolean]("splitting") && arch != "Asic"
  def enableCodegen = Config.option[Boolean]("codegen")
  def aggressive_dce = option[Boolean]("ag-dce")
  def genPlastisim = option[Boolean]("psim") && genCtrl && enableCodegen
  def loadTrace = option[Boolean]("trace")

  //// Debugging
  def verbose = Config.option[Boolean]("verbose")
  def debug:Boolean = Config.option[Boolean]("debug")
  def enableBreakPoint = debug && option[Boolean]("bp")
  def openDot:Boolean = enableCodegen && option[Boolean]("open")
}
