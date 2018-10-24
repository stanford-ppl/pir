package pir

class PIRConfig(compiler:PIR) extends prism.Config(compiler) {

  /* ------------------- Compiler --------------------  */
  register("ctrl", default=true, info="Enable control logic generation")
  register("splitting", default=true, info="Enable splitting")
  register("splitting-algo", default="alias_weighted_igraph", info="splitting algorithm. [weighted_igraph, alias_igraph, alias_weighted_igraph]") 
  register("mapping", default=true, info="Enable mapping")
  register("arch", default="MyDesign", info="Default architecture for mapping")
  register("ag-dce", default=true, info="Enable aggressive dead code elimination")
  register("folded-redacc", default=false, "Fold reduction and accumulate operation into a single stage")

  def arch = option[String]("arch")
  def genCtrl = option[Boolean]("ctrl")
  def enableSplitting = option[Boolean]("splitting")
  def enableMapping = option[Boolean]("mapping")
  def aggressive_dce = option[Boolean]("ag-dce")

  /* ------------------- Plastisim --------------------  */
  register("psim", default=true, info="Enable code generations for plastisim")
  register("run-psim", default=false, info="Launch Plastisim simulation")
  register[Long]("psim-timeout", info="Maximum time out for psim")
  register[String]("psim-out", info="Directory to copy psim files over")
  register[Int]("psim-q", default=1, info="VC slowdown parameter")
  register("trace", default=false, info="Enable trace generation for simulation")

  def genPlastisim = option[Boolean]("psim") && genCtrl && enableCodegen
  def runPlastisim = option[Boolean]("run-psim") && genPlastisim
  def enableTrace = genPlastisim && option[Boolean]("trace")

  /* ------------------- Routing --------------------  */
  register("routing-algo", default="dor", info="If net=[dynamic] - [dor, planed, proute]. Option ignored for other network. dor - dimention order routing. planed - arbitrary source routing, proute - use plastiroute for place and route. If proute is chosen plastiroute will be launched from pir if $PLASTIROUTE_HOME is set") 
  register("routing-cost", default="H-hop", info="Routing cost [hop, balanced, H-hop, H-balanced]. hop - use hop count only for cost in search, balanced - use traffic load + hop count as cost, H-hop: use Manhattan distance as heurisc cost and use hop count for cost. H-balanced: use Manhattan distance as heurisc cost and use hop count and traffic load for cost.") 
  register("proute-algo", default="route_dor_YX", info="Plastiroute routing algorithm") 
  register("proute-q", default=1, info="Maximum number of vc") 
  register("proute-opts", default="-i1000 -p100 -t1 -d100", info="Plastiroute options") 
  register("proute-seed", default=0, info="Plastiroute seed") 
  register("rerun-proute", default=true, info="Run Plastiroute") 
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

  /* ------------------- Debugging --------------------  */
  register("bp-split", default=false, info="Enable break point for splitting")
  register("bp-pr", default=false, info="Enable break point for place and route")
  register("dot", default=true, info="Enable dot codegen")
  register("open", default=false, info="Open dot graph after codegen")
  register("stat", default=false, info="Printing CU statistics")
  register("snapshot", default=false, info="Enable placement snapshot")
  register("snapint", default=10, info="Placement snapshot interval")

  def enableSplitBreakPoint = debug && option[Boolean]("bp-split")
  def enablePlaceAndRouteBreakPoint = debug && option[Boolean]("bp-pr")
  def enableSnapshot = debug && option[Boolean]("snapshot")
  def enableDot:Boolean = enableCodegen && option[Boolean]("dot")
  def openDot:Boolean = enableDot && option[Boolean]("open")
  def printStat = option[Boolean]("stat")
}
