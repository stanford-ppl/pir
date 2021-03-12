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
  lazy val rmwSplitter = new RMWSplitter()
  lazy val scanDup = new ScanLongOpSplitter()
  lazy val scanLowering = new ScanLowering()
  lazy val memLowering = new MemoryLowering()
  lazy val dramBarrierInsertion = new DRAMBarrierInsertion()
  lazy val contextAnalyzer = new ContextAnalyzer()
  lazy val contextInsertion = new ContextInsertion()
  lazy val splitterTransformer = new SplitterTransformer()
  lazy val blkboxLowering = new BlackBoxLowering()
  lazy val depDuplications = new DependencyDuplication()
  lazy val bufferInsertion = new BufferInsertion()
  lazy val globalInsertion = new GlobalInsertion()
  lazy val graphPreprocess = new GraphPreprocessing()
  lazy val runtimeAnalyzer = new RuntimeAnalyzer()
  lazy val ctxMerging = new ContextMerging()
  lazy val psimParser = new PlastisimLogParser()
  lazy val sanityCheck = new SanityCheck()
  lazy val modAnalyzer = new ModularAnalysis()
  lazy val debugTransformer = new DebugTransformer()
  lazy val ctrlBlockInsert = new ControlBlockInsertion()

  /* Mapping */
  lazy val targetInitializer = new TargetInitializer()
  lazy val mappingInitializer = new MappingInitializer()
  lazy val hardPruner = new HardConstrainPruner()
  lazy val computePruner = new ComputePruner()
  lazy val dagPruner = new DAGPruner()
  lazy val mergeBufferPruner = new MergeBufferPruner()
  lazy val memoryPruner = new MemoryPruner()
  lazy val memoryComputePruner = new MemoryComputePruner()
  lazy val globalMerger = new GlobalMerger()
  lazy val matchPruner = new MatchPruner()
  lazy val placerAndRouter = new PlaceAndRoute()

  /* Codegen */
  lazy val tungstenPIRGen = new TungstenPIRGen()
  lazy val psimConfigGen = new PlastisimConfigGen()
  lazy val prouteLinkGen = new PlastirouteLinkGen()
  lazy val prouteNodeGen = new PlastirouteNodeGen()
  lazy val dramTraceGen = new DRAMTraceCodegen()
  lazy val resReport = new ResourceReport()
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
    addPass(enableVerboseDot, new PIRTopDotGen(s"top1.dot")) ==>
    addPass(enableTrace && genPsim, dramTraceGen) ==>
    addPass(graphPreprocess) ==>
    addPass(enableVerboseDot, new PIRTopDotGen(s"top2.dot")) ==>
    addPass(enableDot, new ControlTreeDotGen(s"ctop.dot")) ==>
    addPass(enableVerboseDot, new ControlTreeHtmlIRPrinter(s"ctrl.html")) ==>
    addPass(rewriter) ==>
    addPass(deadCodeEliminator) ==>
    addPass(contextInsertion) ==>
    addPass(splitterTransformer) ==>
    addPass(blkboxLowering) ==>
    addPass(enableVerboseDot, new PIRTopDotGen(s"top3.dot")) ==>
    addPass(enableVerboseDot, new PIRCtxDotGen(s"ctx3.dot")) ==>
    addPass(targetInitializer) ==>
    addPass(new ParamHtmlIRPrinter(s"param.html", pirenv.spadeParam)) ==>
    addPass(rmwSplitter) ==>
    // addPass(scanDup) ==>
    //addPass() ==>
    addPass(memLowering).dependsOn(targetInitializer) ==>
    addPass(dramBarrierInsertion) ==>
    addPass(enableVerboseDot, new PIRTopDotGen(s"top4.dot")) ==>
    addPass(enableVerboseDot, new PIRCtxDotGen(s"ctx4.dot")) ==>
    addPass(depDuplications).dependsOn(memLowering) ==>
    addPass(scanLowering) ==>
    addPass(enableVerboseDot, new PIRTopDotGen(s"top5.dot")) ==>
    addPass(enableVerboseDot, new PIRCtxDotGen(s"ctx5.dot")) ==>
    addPass(rewriter) ==>
    addPass(deadCodeEliminator) ==>
    addPass(enableVerboseDot, new PIRTopDotGen(s"top6.dot")) ==>
    addPass(enableVerboseDot, new PIRCtxDotGen(s"ctx6.dot")) ==>
    addPass(globalInsertion) ==>
    saveSession(buildPath(config.outDir,"pir1.ckpt")) ==>
    addPass(enableMapping,new ProgramReport("program_alloc.json")) ==>
    addPass(enableVerboseDot, new PIRGlobalDotGen(s"global7.dot")) ==>
    addPass(enableVerboseDot, new PIRCtxDotGen(s"ctx7.dot")) ==>
    addPass(enableVerboseDot, new PIRTopDotGen(s"top7.dot")) ==>
    addPass(sanityCheck) ==>
    // ------- Mapping  --------
    addPass(enableMapping, mappingInitializer) ==>
    addPass(enableMapping, hardPruner) ==>
    addPass(enableMapping, memoryPruner) ==>
    addPass(rewriter) ==> // Remove unused shuffle
    addPass(enableVerboseDot, new PIRGlobalDotGen(s"global8.dot")) ==>
    addPass(enableMapping, memoryComputePruner) ==>
    addPass(enableVerboseDot, new PIRGlobalDotGen(s"global9.dot")) ==>
    addPass(enableMapping, mergeBufferPruner) ==>
    addPass(enableMapping, hardPruner) ==> // prune on newly created CUs by memoryComputePruner
    addPass(enableMapping, computePruner) ==>
    addPass(enableVerboseDot, new PIRGlobalDotGen(s"global10.dot")) ==>
    addPass(enableMapping, hardPruner) ==>
    addPass(enableMapping,new ProgramReport("program_split.json")) ==>
    addPass(enableMerging, globalMerger) ==>
    addPass(enableVerboseDot, new PIRGlobalDotGen(s"global11.dot")) ==>
    addPass(enableMapping, dagPruner) ==>
    addPass(enableMapping, matchPruner) ==>
    addPass(ctrlBlockInsert) ==>
    addPass(sanityCheck) ==>
    addPass(modAnalyzer) ==>
    addPass(enableMapping, placerAndRouter) ==>
    addPass(enableVerboseDot, new PIRCtxDotGen(s"ctx8.dot")) ==>
    addPass(enableVerboseDot, new PIRTopDotGen(s"top8.dot")) ==>
    //addPass(enableVerboseDot, new PIRNetworkDotGen(s"net.dot"))
    saveSession(buildPath(config.outDir,"pir2.ckpt")) ==>
    // ------- Codegen  --------
    addPass(enableMapping,new ProgramReport("program.json")) ==>
    addPass(enableMapping,resReport) ==>
    // addPass(enableDot, new PIRGlobalDotGen(s"global.dot")) ==>
    addPass(enableRuntimeAnalysis, runtimeAnalyzer).dependsOn(placerAndRouter) ==>
    addPass(enableDot, new PIRGlobalDotGen(s"global.dot")) ==>
    addPass(enableVerboseDot, new PIRCtxDotGen(s"ctx.dot")) ==>
    addPass(enableVerboseDot, new PIRTopDotGen(s"top.dot")) ==>
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
    //addPass(psimParser)
    //addPass(enableDot, new PIRTopDotGen(s"top10.dot"))
    //addPass(enableDot, new PIRCtxDotGen(s"ctx10.dot"))

    //addPass(areaPowerStat).dependsOn(psimConfigCodegen, cuPlacer)

  }

  def staging(top:Top):Unit

}
