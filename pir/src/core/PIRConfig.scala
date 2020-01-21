package pir

class PIRConfig(compiler:Compiler) extends spade.SpadeConfig(compiler) {

  /* ------------------- Compiler --------------------  */
  register("split", default=true, info="Enable splitting")
  register("split-algo", default="dfs", info="Splitting algorithm. [dfs, bfs, gurobi, cvxpy]") 
  register("gurobi-init-algo", info="Warm start algorithm for gurobi. [dfs, bfs]") 
  register("split-forward", default=false, info="Forward splitting traversal direction") 
  register("split-thread", default=1, info="Number of threads for external splitter") 
  register("merge", default=false, info="Enable merging")
  register("merge-algo", default="bfs", info="Merging algorithm. [dfs, bfs, gurobi]")
  register("merge-forward", default=false, info="Forward merging traversal direction") 

  // Optimizations
  register("ag-dce", default=true, info="Enable aggressive dead code elimination")
  register("dupra", default=false, info="Duplicate read address calculation in receiver CU")
  register("bcread", default=true, info="Enable broadcast read")
  register("mdone", default=true, info="Generate done from merged access")
  register("constprop", default=true, info="Enable constant propogation")
  register("pracc", default=true, info="Enable pipeline register accumulation lowering")
  register("sr", default=true, info="Enable strength reduction")
  register("lrange", default=true, info="Enable loop range analysis")
  register("rtelm", default=true, info="Enable route through elimination")

  register("retime-local", default=false, info="Enable local retiming")
  register("retime-glob", default=false, info="Enable global retiming")
  register("retime-exout", default=false, info="Enable retiming of external output")
  register("retime-buffer-only", default=false, info="Only allow using input buffers of CUs for retiming")

  register("mapping", default=true, info="Enable mapping")
  register("arch", default="MyDesign", info="Default architecture for mapping")
  register("stat", default=false, info="Printing statistics")
  register("igraph", default=false, info="Enable igraph codegen")
  register("dedicated-dag", default=false, info="Force DRAM AG are only used to map DRAM Address Calculation")
  register("module", default=false, info="Generate the app as a module")

  register[String]("spatial-home", default=sys.env.get("SPATIAL_HOME"), info="Spatial Home")
  register[String]("pir-home", default=sys.env.get("PIR_HOME"), info="PIR Home")

  def arch = option[String]("arch")
  def dupReadAddr = option[Boolean]("dupra")
  def mergeDone = option[Boolean]("mdone")
  def enableLocalRetiming = option[Boolean]("retime-local")
  def enableGlobalRetiming = option[Boolean]("retime-glob")
  def enableRetimeExout = option[Boolean]("retime-exout")
  def enableConstProp = option[Boolean]("constprop")
  def enablePipeAccum = option[Boolean]("pracc")
  def enableStrengthReduce = option[Boolean]("sr")
  def enableRangeAnalysis = option[Boolean]("lrange")
  def retimeBufferOnly = option[Boolean]("retime-buffer-only")
  def enableMapping = option[Boolean]("mapping")
  def enableSplitting = option[Boolean]("split") && enableMapping
  def enableBroadcastRead = option[Boolean]("bcread")
  def splitAlgo = option[String]("split-algo")
  def gurobiInitAlgo = getOption[String]("gurobi-init-algo")
  def splitThread = option[Int]("split-thread")
  def splitForward = option[Boolean]("split-forward")
  def enableMerging = option[Boolean]("merge") && enableMapping
  def mergeAlgo = option[String]("merge-algo")
  def mergeForward = option[Boolean]("merge-forward")
  def deadicatedDAG = option[Boolean]("dedicated-dag")
  def enableRouteElim = option[Boolean]("rtelm")
  def aggressive_dce = option[Boolean]("ag-dce")
  def printStat = option[Boolean]("stat")
  def enableIgraph = option[Boolean]("igraph")
  def graphDir = buildPath(appDir, "graph")
  def mergeDir = buildPath(appDir, "merge")
  def splitDir = buildPath(appDir, "split")
  def asModule = enableCodegen && option[Boolean]("module")
  def spatialHome = getOption[String]("spatial-home")
  def pirHome = getOption[String]("pir-home") orElse spatialHome.map { buildPath(_,"pir") }

  /* ------------------- Routing --------------------  */
  register("routing-algo", default="dor", info="If net=[dynamic] - [dor, planed, proute]. Option ignored for other network. dor - dimention order routing. planed - arbitrary source routing, proute - use plastiroute for place and route. If proute is chosen plastiroute will be launched from pir if $PLASTIROUTE_HOME is set") 
  register("routing-cost", default="H-hop", info="Routing cost [hop, balanced, H-hop, H-balanced]. hop - use hop count only for cost in search, balanced - use traffic load + hop count as cost, H-hop: use Manhattan distance as heurisc cost and use hop count for cost. H-balanced: use Manhattan distance as heurisc cost and use hop count and traffic load for cost.") 
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

  /* ------------------- Plastisim --------------------  */
  register[String]("psim-home", default=sys.env.get("PLASTISIM_HOME"), info="Plastisim Home")
  register("psim", default=false, info="Enable code generations for plastisim")
  register("run-psim", default=false, info="Launch Plastisim simulation")
  register[String]("load-psim", info="Path to load psim log")
  register[Long]("psim-timeout", info="Maximum time out for psim")
  register[String]("psim-out", info="Directory to copy psim files over")
  register[Int]("psim-q", default=1, info="VC slowdown parameter")
  register("trace", default=false, info="Enable trace generation for simulation")
  register[String]("count", info="Annotation on count values")

  def genPsim = option[Boolean]("psim") && enableCodegen
  def runPsim = option[Boolean]("run-psim") && genPsim
  def loadPsim = getOption[String]("load-psim")
  def enableTrace = genPsim && option[Boolean]("trace")
  def psimHome = getOption[String]("psim-home") orElse pirHome.map { buildPath(_,"plastisim") }
  def psimOut = getOption[String]("psim-out").getOrElse { buildPath(appDir, s"plastisim") }
  def psimConfigName = "psim.conf"
  def psimConfigPath = buildPath(psimOut, psimConfigName)
  def traceName = "gen_trace.scala"
  def tracePath = buildPath(psimOut, "trace")
  def psimLog = buildPath(appDir, "psim.log")

  /* ------------------- Plastiroute --------------------  */
  register[String]("proute-home", default=sys.env.get("PLASTIROUTE_HOME"), info="Plastiroute Home")
  register("proute-algo", default="route_min_directed_valient", info="Plastiroute routing algorithm") 
  register("proute-q", default=4, info="Maximum number of vc") 
  register("proute-opts", default="-i100 -p100 -t1 -d100", info="Plastiroute options") 
  register("proute-seed", default=0, info="Plastiroute seed") 
  register[String]("proutesh", info="Path to customized proute command") 
  register("run-proute", default=false, info="Run Plastiroute") 
  register[String]("module-prefix", info="Prefix to top module path")
  register[String]("extern-prefix", info="Prefix to external module path")
  def prouteHome = getOption[String]("proute-home") orElse pirHome.map { buildPath(_,"plastiroute") }
  def proutesh = getOption[String]("proutesh")
  def genProute = genPsim || genTungsten
  def runproute = option[Boolean]("run-proute") || runPsim || runTst 
  def proutePlaceName = "final.place"
  def proutePlacePath = buildPath(psimOut, proutePlaceName)
  def iroutePlacePath = buildPath(psimOut, "ideal.place")
  def prouteOutLinkName = "outlink_pir.csv"
  def prouteInLinkName = "inlink_pir.csv"
  def prouteLinkName = "link_pir.csv"
  def prouteLinkPath = buildPath(psimOut, prouteLinkName)
  def prouteNodeName = "node_pir.csv"
  def prouteNodePath = buildPath(psimOut, prouteNodeName)
  def prouteSummaryName = "summary.csv"
  def prouteSummaryPath = buildPath(psimOut, prouteSummaryName)
  def prouteLog = buildPath(appDir, "proute.log")
  def modulePrefix = getOption[String]("module-prefix")
  def externPrefix = getOption[String]("extern-prefix")
  def moduleName = modulePrefix.map { _.split("/").last }

  /* ------------------- Tungsten --------------------  */
  register[String]("tungsten-home", default=sys.env.get("TUNGSTEN_HOME"), info="Tungsten Home")
  register("run-tst", default=false, info="Launch Tungsten simulation")
  register("tungsten", default=true, info="Enable tungsten codegen")
  register("debug-tst", default=false, info="Enable debug print in simulation in tungsten")
  def genTungsten = enableCodegen && option[Boolean]("tungsten")
  def runTst = option[Boolean]("run-tst")
  def tstOut = buildPath(appDir, "tungsten")
  def tstHome = getOption[String]("tungsten-home") orElse pirHome.map { buildPath(_,"tungsten") }
  def getTstHome = tstHome.getOrElse(err(s"tungsten-home is not set"))
  def tstLog = buildPath(appDir, "runtst.log")
  def enableSimDebug = option[Boolean]("debug-tst")

  /* ------------------- Debugging --------------------  */
  register("dot", default=false, info="Enable dot codegen")
  register("vdot", default=false, info="Enable verbose dot codegen")
  register("fast", default=false, info="Disable debugging to make running fast")

  def fast:Boolean = option[Boolean]("fast")
  override def save = !fast & super.save
  //override def debug = !fast & super.debug
  def enableDot:Boolean = enableCodegen && option[Boolean]("dot")
  def enableVerboseDot:Boolean = enableDot && option[Boolean]("vdot") && !fast
}
