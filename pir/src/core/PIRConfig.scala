package pir

object PIRConfig extends prism.GlobalConfig {

  register("arch", default="SMeshCB4x4", info="Default architecture for mapping")
  register("ctrl", default=true, info="Enable control logic generation")
  register("splitting", default=true, info="Enable splitting")
  register("mapping", default=true, info="Enable mapping")
  register("ag-dce", default=true, info="Enable aggressive dead code elimination")
  register("psim", default=true, info="Enable generation to plastisim")
  register("trace", default=false, info="Enable trace generation")

  // --- Routing ----
  register("routing-algo", default="planed", info="If arch is dynamic, algo can be [dor, planed]") 
  register("routing-cost", default="H-hop", info="Routing cost [hop, balanced, H-hop, H-balanced]") 

  // --- debug -----
  register("save-pir", default=false, info="Save IR into a file") 
  register("load-pir", default=false, info="Load IR from a file")
  register("bp", default=false, info="Enable break point")
  register("dot", default=true, info="Enable dot codegen")
  register("open", default=false, info="Open dot graph after codegen")
  register("stat", default=false, info="Printing CU statistics")
  register("snapshot", default=false, info="Enable placement snapshot")
  register("snapint", default=10, info="Placement snapshot interval")

  def arch = option[String]("arch")
  def saveDesign = option[Boolean]("save-pir")
  def loadDesign = option[Boolean]("load-pir")
  def genCtrl = option[Boolean]("ctrl")
  def enableSplitting = option[Boolean]("splitting")
  def enableMapping = option[Boolean]("mapping")
  def enableCodegen = Config.option[Boolean]("codegen")
  def aggressive_dce = option[Boolean]("ag-dce")
  def genPlastisim = option[Boolean]("psim") && genCtrl && enableCodegen
  def loadTrace = option[Boolean]("trace")
  def printStat = option[Boolean]("stat")

  // ---- Routing ----
  def enableHopCountCost = option[String]("routing-cost") match {
    case "hop" => true
    case "balanced" => false
    case "H-hop" => true
    case "H-balanced" => false
  }

  def enableBalancedCost = option[String]("routing-cost") match {
    case "hop" => false
    case "balanced" => true
    case "H-hop" => false
    case "H-balanced" => true
  }

  def enableHeuristicCost = option[String]("routing-cost") match {
    case "hop" => false
    case "balanced" => false
    case "H-hop" => true
    case "H-balanced" => true
  }
  def routingAlgo = option[String]("routing-algo")


  // Debugging
  def verbose = Config.option[Boolean]("verbose")
  def debug:Boolean = Config.option[Boolean]("debug")
  def enableBreakPoint = debug && option[Boolean]("bp")
  def enableSnapshot = debug && option[Boolean]("snapshot")
  def enableDot:Boolean = enableCodegen && option[Boolean]("dot")
  def openDot:Boolean = enableDot && option[Boolean]("open")
}
