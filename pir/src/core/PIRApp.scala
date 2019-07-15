package pir

import pir.node._
import pir.pass._
import pir.codegen._
import pir.mapper._
import prism.codegen._
import spade.codegen._

trait PIRApp extends PIR with Logging {
  
  /* Analysis and Transformations*/

  lazy val pirgenStaging = new SpatialPIRGenStaging()
  lazy val deadCodeEliminator = new DeadCodeElimination()
  lazy val rewriter = new RewriteTransformer()
  lazy val memInitLowering = new MemoryInitialLowering()
  lazy val memLowering = new MemoryLowering()
  lazy val contextAnalyzer = new ContextAnalyzer()
  lazy val contextInsertion = new ContextInsertion()
  lazy val depDuplications = new DependencyDuplication()
  lazy val accessCtxCreation = new AccessContextCreation()
  lazy val bufferInsertion = new BufferInsertion()
  lazy val globalInsertion = new GlobalInsertion()
  lazy val graphInit = new GraphInitialization()
  lazy val runtimeAnalyzer = new RuntimeAnalyzer()
  lazy val ctxMerging = new ContextMerging()
  lazy val psimParser = new PlastisimLogParser()
  lazy val sanityCheck = new SanityCheck()
  lazy val modAnalyzer = new ModularAnalysis()
  lazy val debugTransformer = new DebugTransformer()

  /* Mapping */
  lazy val initializer = new TargetInitializer()
  lazy val hardPruner = new HardConstrainPruner()
  lazy val computePruner = new ComputePruner()
  lazy val dagPruner = new DAGPruner()
  lazy val memoryPruner = new MemoryPruner()
  lazy val memoryComputePruner = new MemoryComputePruner()
  lazy val matchPruner = new MatchPruner()
  lazy val placerAndRouter = new PlaceAndRoute()

  /* Codegen */
  lazy val tungstenPIRGen = new TungstenPIRGen()
  lazy val psimConfigGen = new PlastisimConfigGen()
  lazy val prouteLinkGen = new PlastirouteLinkGen()
  lazy val prouteNodeGen = new PlastirouteNodeGen()
  lazy val dramTraceGen = new DRAMTraceCodegen()
  lazy val report = new ResourceReport()
  lazy val igraphGen = new IgraphCodegen()
  //lazy val areaPowerStat = new AreaPowerStat()
  
  /* Simulation */
  lazy val psimRunner = new PlastisimRunner()
  lazy val prouteRunner = new PlastirouteRunner()
  lazy val tstRunner = new TungstenRunner()

  override def initSession = {
    import config._

    addPass(pirgenStaging) ==>
    saveSession(buildPath(config.outDir,"pir0.ckpt"), force=true) ==>
    // ------- Analysis and Transformations --------
    addPass(enableDot, new PIRIRDotGen(s"top1.dot")) ==>
    addPass(enableTrace && genPsim, dramTraceGen) ==>
    addPass(graphInit) ==>
    addPass(enableDot, new PIRIRDotGen(s"top2.dot")) ==>
    addPass(enableDot, new ControlTreeDotGen(s"ctop.dot")) ==>
    addPass(enableDot, new ControlTreeHtmlIRPrinter(s"ctrl.html")) ==>
    addPass(rewriter) ==>
    addPass(deadCodeEliminator) ==>
    addPass(contextInsertion) ==>
    addPass(enableDot, new PIRCtxDotGen(s"simple2.dot")) ==>
    addPass(memInitLowering) ==>
    addPass(enableDot, new PIRIRDotGen(s"top3.dot")) ==>
    addPass(enableDot, new PIRCtxDotGen(s"simple3.dot")) ==>
    addPass(memLowering) ==>
    addPass(enableDot, new PIRIRDotGen(s"top4.dot")) ==>
    addPass(enableDot, new PIRCtxDotGen(s"simple4.dot")) ==>
    addPass(depDuplications).dependsOn(memLowering) ==>
    addPass(enableDot, new PIRIRDotGen(s"top5.dot")) ==>
    addPass(enableDot, new PIRCtxDotGen(s"simple5.dot")) ==>
    addPass(rewriter) ==>
    addPass(deadCodeEliminator) ==>
    //addPass(contextAnalyzer) ==>
    addPass(enableDot, new PIRIRDotGen(s"top6.dot")) ==>
    addPass(enableDot, new PIRCtxDotGen(s"simple6.dot")) ==>
    addPass(globalInsertion) ==>
    saveSession(buildPath(config.outDir,"pir1.ckpt")) ==>
    // ------ Load hardware constrain ----- 
    addPass(initializer) ==>
    addPass(new ParamHtmlIRPrinter(s"param.html", pirenv.spadeParam)) ==>
    addPass(enableDot, new PIRCtxDotGen(s"simple7.dot")) ==>
    addPass(enableDot, new PIRIRDotGen(s"top7.dot")) ==>
    // ------- Mapping  --------
    addPass(enableMapping, hardPruner) ==>
    addPass(enableMapping, memoryPruner) ==>
    addPass(rewriter) ==> // Remove unused shuffle
    addPass(enableMapping, memoryComputePruner) ==>
    addPass(enableMapping, hardPruner) ==> // prune on newly created CUs by memoryComputePruner
    addPass(enableMapping, computePruner) ==>
    addPass(enableMapping, dagPruner) ==>
    addPass(sanityCheck) ==>
    addPass(debugTransformer) ==>
    addPass(enableMapping, matchPruner) ==>
    addPass(modAnalyzer) ==>
    addPass(enableMapping, placerAndRouter) ==>
    addPass(enableDot, new PIRCtxDotGen(s"simple8.dot")) ==>
    addPass(enableDot, new PIRIRDotGen(s"top8.dot")) ==>
    //addPass(enableDot, new PIRNetworkDotGen(s"net.dot"))
    saveSession(buildPath(config.outDir,"pir2.ckpt")) ==>
    // ------- Codegen  --------
    addPass(enableMapping,report) ==>
    addPass(runtimeAnalyzer).dependsOn(placerAndRouter) ==>
    addPass(enableDot, new PIRCtxDotGen(s"simple9.dot")) ==>
    addPass(enableDot, new PIRIRDotGen(s"top9.dot"))
    addPass(enableDot, new PIRGlobalDotGen(s"global.dot"))
    // Igraph
    addPass(enableIgraph, igraphGen).dependsOn(runtimeAnalyzer)
    // Plastiroute
    addPass(genProute, prouteLinkGen).dependsOn(runtimeAnalyzer)
    addPass(genProute, prouteNodeGen).dependsOn(placerAndRouter, runtimeAnalyzer) ==>
    addPass(genTungsten, tungstenPIRGen).dependsOn(placerAndRouter) ==>
    addPass(genProute, prouteRunner)
    // Tungsten
    addPass(genTungsten && runTst, tstRunner).dependsOn(prouteRunner)
    // Plastisim
    addPass(genPsim, psimConfigGen).dependsOn(placerAndRouter, runtimeAnalyzer, prouteLinkGen) ==>
    addPass(genPsim && runPsim, psimRunner).dependsOn(prouteRunner)
    addPass(psimParser)
    addPass(enableDot, new PIRIRDotGen(s"top10.dot"))
    addPass(enableDot, new PIRCtxDotGen(s"simple10.dot"))

    //addPass(areaPowerStat).dependsOn(psimConfigCodegen, cuPlacer)

  }

  def staging(top:Top):Unit

}
