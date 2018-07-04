import pir._
import pir.node._
import arch._
import prism.enums._

object NestRed extends PIRApp {
  def main(implicit design:PIRDesign) = {
    import design.pirmeta._
    val x2365 = StreamIn(field="data").name("x2365").ctrl(top).srcCtx("NestedReductionTest.scala:17:50:stream_in1") // x2365 = StreamInNew(GPInput1)
    isAccum(x2365) = false
    bufferDepthOf(x2365) = 1
    countsOf(x2365) = Some(10l)
    val x2366 = StreamIn(field="data").name("x2366").ctrl(top).srcCtx("NestedReductionTest.scala:18:50:stream_in2") // x2366 = StreamInNew(GPInput2)
    isAccum(x2366) = false
    bufferDepthOf(x2366) = 1
    countsOf(x2366) = Some(10l)
    val x2367 = StreamOut(field="data").name("x2367").ctrl(top).srcCtx("NestedReductionTest.scala:20:50:stream_out") // x2367 = StreamOutNew(GPOutput1)
    isAccum(x2367) = false
    bufferDepthOf(x2367) = 1
    // x2368 = Forever() TODO: Unmatched Node
    val x2663 = ForeverController().name("x2663").ctrl(top).srcCtx("NestedReductionTest.scala:23:26") // Hwblock(Block(Const(())),true)
    val x2369_d0_b0 = RegFile(size=2, inits=None).name("x2369_d0_b0").ctrl(x2663).srcCtx("NestedReductionTest.scala:25:53:test_pt") // x2369 = RegFileNew(ArrayBuffer(Const(2)),None) banking:Strided(banks=2, stride=1)
    isAccum(x2369_d0_b0) = false
    bufferDepthOf(x2369_d0_b0) = 1
    val x2369_d1_b0 = RegFile(size=2, inits=None).name("x2369_d1_b0").ctrl(x2663).srcCtx("NestedReductionTest.scala:25:53:test_pt") // x2369 = RegFileNew(ArrayBuffer(Const(2)),None) banking:Strided(banks=2, stride=1)
    isAccum(x2369_d1_b0) = false
    bufferDepthOf(x2369_d1_b0) = 1
    val x2369_d2_b0 = RegFile(size=2, inits=None).name("x2369_d2_b0").ctrl(x2663).srcCtx("NestedReductionTest.scala:25:53:test_pt") // x2369 = RegFileNew(ArrayBuffer(Const(2)),None) banking:Strided(banks=2, stride=1)
    isAccum(x2369_d2_b0) = false
    bufferDepthOf(x2369_d2_b0) = 1
    val x2369_d3_b0 = RegFile(size=2, inits=None).name("x2369_d3_b0").ctrl(x2663).srcCtx("NestedReductionTest.scala:25:53:test_pt") // x2369 = RegFileNew(ArrayBuffer(Const(2)),None) banking:Strided(banks=2, stride=1)
    isAccum(x2369_d3_b0) = false
    bufferDepthOf(x2369_d3_b0) = 1
    val x2369_d4_b0 = RegFile(size=2, inits=None).name("x2369_d4_b0").ctrl(x2663).srcCtx("NestedReductionTest.scala:25:53:test_pt") // x2369 = RegFileNew(ArrayBuffer(Const(2)),None) banking:Strided(banks=2, stride=1)
    isAccum(x2369_d4_b0) = false
    bufferDepthOf(x2369_d4_b0) = 1
    val x2369_d5_b0 = RegFile(size=2, inits=None).name("x2369_d5_b0").ctrl(x2663).srcCtx("NestedReductionTest.scala:25:53:test_pt") // x2369 = RegFileNew(ArrayBuffer(Const(2)),None) banking:Strided(banks=2, stride=1)
    isAccum(x2369_d5_b0) = false
    bufferDepthOf(x2369_d5_b0) = 1
    val x2370 = Reg(init=Some(0.0)).name("x2370").ctrl(x2663).srcCtx("NestedReductionTest.scala:26:36:w") // x2370 = RegNew(Const(0.0))
    isAccum(x2370) = false
    bufferDepthOf(x2370) = 2
    val x2658 = UnitController(style=SeqPipe, level=OuterControl).name("x2658").ctrl(x2663).srcCtx("NestedReductionTest.scala:30:30") // UnitPipe(List(Const(true)),Block(Const(())))
    val x2373 = UnitController(style=SeqPipe, level=InnerControl).name("x2373").ctrl(x2658).srcCtx("NestedReductionTest.scala:33:38") // UnitPipe(List(Const(true)),Block(x2372))
    val x2371_x2371 = ReadMem(x2365).name("x2371_x2371").ctrl(x2373).srcCtx("NestedReductionTest.scala:34:65") // StreamRead(x2365,Const(true))
    val x2372 = StoreBanks(List(x2369_d0_b0, x2369_d5_b0, x2369_d1_b0, x2369_d2_b0, x2369_d3_b0, x2369_d4_b0), List(Const(0)), x2371_x2371).name("x2372").ctrl(x2373).srcCtx("NestedReductionTest.scala:34:52") // RegFileStore(x2369,List(Const(0)),x2371,Const(true))
    val x2376 = UnitController(style=SeqPipe, level=InnerControl).name("x2376").ctrl(x2658).srcCtx("NestedReductionTest.scala:37:38") // UnitPipe(List(Const(true)),Block(x2375))
    val x2374_x2374 = ReadMem(x2366).name("x2374_x2374").ctrl(x2376).srcCtx("NestedReductionTest.scala:39:65") // StreamRead(x2366,Const(true))
    val x2375 = StoreBanks(List(x2369_d0_b0, x2369_d5_b0, x2369_d1_b0, x2369_d2_b0, x2369_d3_b0, x2369_d4_b0), List(Const(1)), x2374_x2374).name("x2375").ctrl(x2376).srcCtx("NestedReductionTest.scala:39:52") // RegFileStore(x2369,List(Const(1)),x2374,Const(true))
    val x2377_d0 = Reg(init=Some(0.0)).name("x2377_d0").ctrl(x2658).srcCtx("NestedReductionTest.scala:46:55") // x2377 = RegNew(Const(0.0))
    isAccum(x2377_d0) = false
    bufferDepthOf(x2377_d0) = 1
    val x2377_d1 = Reg(init=Some(0.0)).name("x2377_d1").ctrl(x2658).srcCtx("NestedReductionTest.scala:46:55") // x2377 = RegNew(Const(0.0))
    isAccum(x2377_d1) = true
    bufferDepthOf(x2377_d1) = 1
    val x2378 = Counter(min=Const(0), max=Const(6), step=Const(1), par=6).name("x2378").ctrl(x2658).srcCtx("NestedReductionTest.scala:46:82") // CounterNew(Const(0),Const(6),Const(1),Const(6))
    val x2379 = CounterChain(List(x2378)).name("x2379").ctrl(x2658).srcCtx("NestedReductionTest.scala:56:34") // CounterChainNew(List(x2378))
    val x2654 = LoopController(style=MetaPipe, level=OuterControl, cchain=x2379).name("x2654").ctrl(x2658).srcCtx("NestedReductionTest.scala:56:34") // UnrolledReduce(List(Const(true)),x2379,x2377,Block((x2377) => Const(())),List(List(b861, b862, b863, b864, b865, b866)),List(List(b867, b868, b869, b870, b871, b872)))
    val b861 = CounterIter(x2378, Some(0)).name("b861").ctrl(x2654) // b861
    val b867 = Const(true).name("b867").ctrl(x2654) // b867
    val b862 = CounterIter(x2378, Some(1)).name("b862").ctrl(x2654) // b862
    val b868 = Const(true).name("b868").ctrl(x2654) // b868
    val b863 = CounterIter(x2378, Some(2)).name("b863").ctrl(x2654) // b863
    val b869 = Const(true).name("b869").ctrl(x2654) // b869
    val b864 = CounterIter(x2378, Some(3)).name("b864").ctrl(x2654) // b864
    val b870 = Const(true).name("b870").ctrl(x2654) // b870
    val b865 = CounterIter(x2378, Some(4)).name("b865").ctrl(x2654) // b865
    val b871 = Const(true).name("b871").ctrl(x2654) // b871
    val b866 = CounterIter(x2378, Some(5)).name("b866").ctrl(x2654) // b866
    val b872 = Const(true).name("b872").ctrl(x2654) // b872
    val x2380 = Reg(init=Some(0.0)).name("x2380").ctrl(x2654).srcCtx("NestedReductionTest.scala:48:46:sq_diff") // x2380 = RegNew(Const(0.0))
    isAccum(x2380) = false
    bufferDepthOf(x2380) = 1
    val x2381 = Reg(init=Some(0.0)).name("x2381").ctrl(x2654).srcCtx("NestedReductionTest.scala:48:46:sq_diff") // x2381 = RegNew(Const(0.0))
    isAccum(x2381) = false
    bufferDepthOf(x2381) = 1
    val x2382 = Reg(init=Some(0.0)).name("x2382").ctrl(x2654).srcCtx("NestedReductionTest.scala:48:46:sq_diff") // x2382 = RegNew(Const(0.0))
    isAccum(x2382) = false
    bufferDepthOf(x2382) = 1
    val x2383 = Reg(init=Some(0.0)).name("x2383").ctrl(x2654).srcCtx("NestedReductionTest.scala:48:46:sq_diff") // x2383 = RegNew(Const(0.0))
    isAccum(x2383) = false
    bufferDepthOf(x2383) = 1
    val x2384 = Reg(init=Some(0.0)).name("x2384").ctrl(x2654).srcCtx("NestedReductionTest.scala:48:46:sq_diff") // x2384 = RegNew(Const(0.0))
    isAccum(x2384) = false
    bufferDepthOf(x2384) = 1
    val x2385 = Reg(init=Some(0.0)).name("x2385").ctrl(x2654).srcCtx("NestedReductionTest.scala:48:46:sq_diff") // x2385 = RegNew(Const(0.0))
    isAccum(x2385) = false
    bufferDepthOf(x2385) = 1
    val x2386_d0 = Reg(init=Some(0.0)).name("x2386_d0").ctrl(x2654).srcCtx("NestedReductionTest.scala:50:69") // x2386 = RegNew(Const(0.0))
    isAccum(x2386_d0) = false
    bufferDepthOf(x2386_d0) = 1
    val x2386_d1 = Reg(init=Some(0.0)).name("x2386_d1").ctrl(x2654).srcCtx("NestedReductionTest.scala:50:69") // x2386 = RegNew(Const(0.0))
    isAccum(x2386_d1) = true
    bufferDepthOf(x2386_d1) = 1
    val x2387_d0 = Reg(init=Some(0.0)).name("x2387_d0").ctrl(x2654).srcCtx("NestedReductionTest.scala:50:69") // x2387 = RegNew(Const(0.0))
    isAccum(x2387_d0) = false
    bufferDepthOf(x2387_d0) = 1
    val x2387_d1 = Reg(init=Some(0.0)).name("x2387_d1").ctrl(x2654).srcCtx("NestedReductionTest.scala:50:69") // x2387 = RegNew(Const(0.0))
    isAccum(x2387_d1) = true
    bufferDepthOf(x2387_d1) = 1
    val x2388_d0 = Reg(init=Some(0.0)).name("x2388_d0").ctrl(x2654).srcCtx("NestedReductionTest.scala:50:69") // x2388 = RegNew(Const(0.0))
    isAccum(x2388_d0) = false
    bufferDepthOf(x2388_d0) = 1
    val x2388_d1 = Reg(init=Some(0.0)).name("x2388_d1").ctrl(x2654).srcCtx("NestedReductionTest.scala:50:69") // x2388 = RegNew(Const(0.0))
    isAccum(x2388_d1) = true
    bufferDepthOf(x2388_d1) = 1
    val x2389_d0 = Reg(init=Some(0.0)).name("x2389_d0").ctrl(x2654).srcCtx("NestedReductionTest.scala:50:69") // x2389 = RegNew(Const(0.0))
    isAccum(x2389_d0) = false
    bufferDepthOf(x2389_d0) = 1
    val x2389_d1 = Reg(init=Some(0.0)).name("x2389_d1").ctrl(x2654).srcCtx("NestedReductionTest.scala:50:69") // x2389 = RegNew(Const(0.0))
    isAccum(x2389_d1) = true
    bufferDepthOf(x2389_d1) = 1
    val x2390_d0 = Reg(init=Some(0.0)).name("x2390_d0").ctrl(x2654).srcCtx("NestedReductionTest.scala:50:69") // x2390 = RegNew(Const(0.0))
    isAccum(x2390_d0) = false
    bufferDepthOf(x2390_d0) = 1
    val x2390_d1 = Reg(init=Some(0.0)).name("x2390_d1").ctrl(x2654).srcCtx("NestedReductionTest.scala:50:69") // x2390 = RegNew(Const(0.0))
    isAccum(x2390_d1) = true
    bufferDepthOf(x2390_d1) = 1
    val x2391_d0 = Reg(init=Some(0.0)).name("x2391_d0").ctrl(x2654).srcCtx("NestedReductionTest.scala:50:69") // x2391 = RegNew(Const(0.0))
    isAccum(x2391_d0) = false
    bufferDepthOf(x2391_d0) = 1
    val x2391_d1 = Reg(init=Some(0.0)).name("x2391_d1").ctrl(x2654).srcCtx("NestedReductionTest.scala:50:69") // x2391 = RegNew(Const(0.0))
    isAccum(x2391_d1) = true
    bufferDepthOf(x2391_d1) = 1
    val x2464 = UnitController(style=ForkJoin, level=OuterControl).name("x2464").ctrl(x2654).srcCtx("UnrollingTransformer.scala:431:43") // ParallelPipe(List(Const(true)),Block(Const(())))
    val x2392 = Counter(min=Const(0), max=Const(2), step=Const(1), par=2).name("x2392").ctrl(x2464).srcCtx("NestedReductionTest.scala:50:102") // CounterNew(Const(0),Const(2),Const(1),Const(2))
    val x2393 = CounterChain(List(x2392)).name("x2393").ctrl(x2464).srcCtx("NestedReductionTest.scala:53:42") // CounterChainNew(List(x2392))
    val x2403 = LoopController(style=InnerPipe, level=InnerControl, cchain=x2393).name("x2403").ctrl(x2464).srcCtx("NestedReductionTest.scala:53:42") // UnrolledReduce(List(b867),x2393,x2386,Block((x2386) => Const(())),List(List(b897)),List(List(b898)))
    val b897 = CounterIter(x2392, None).name("b897").ctrl(x2403) // b897
    val b898 = Const(true).name("b898").ctrl(x2403) // b898
    val x2394 = LoadBanks(List(x2369_d0_b0), List(b897)).name("x2394").ctrl(x2403).srcCtx("NestedReductionTest.scala:51:40") // ParRegFileLoad(x2369,List(List(b897)),List(Const(true)))
    val x2395 = x2394 // x2395 = VectorApply(x2394,0)
    val x2396 = OpDef(op=FltSub, inputs=List(Const(23.5), x2395)).name("x2396").ctrl(x2403).srcCtx("NestedReductionTest.scala:51:31:diff") // FltSub(Const(23.50000),x2395)
    val x2397 = OpDef(op=FltMul, inputs=List(x2396, x2396)).name("x2397").ctrl(x2403).srcCtx("NestedReductionTest.scala:52:18") // FltMul(x2396,x2396)
    val x2398 = OpDef(op=BitAnd, inputs=List(b898, b867)).name("x2398").ctrl(x2403).srcCtx("NestedReductionTest.scala:53:42") // And(b898,b867)
    val x2399 = ReadMem(x2386_d1).name("x2399").ctrl(x2403).srcCtx("NestedReductionTest.scala:53:42") // RegRead(x2386)
    val x2400 = OpDef(op=FixEql, inputs=List(b897, Const(0))).name("x2400").ctrl(x2403).srcCtx("NestedReductionTest.scala:53:42") // FixEql(b897,Const(0))
    val x2401 = ReduceAccumOp(op=FltAdd, input=x2397, accum=x2399).name("x2401").ctrl(x2403).srcCtx("NestedReductionTest.scala:53:44") // FltAdd(x2397,x2399)
    val x2402_x2386_d0 = WriteMem(x2386_d0, x2401).name("x2402_x2386_d0").ctrl(x2403).srcCtx("NestedReductionTest.scala:53:42") // RegWrite(x2386,x2401,b867)
    val x2402_x2386_d1 = WriteMem(x2386_d1, x2401).name("x2402_x2386_d1").ctrl(x2403).srcCtx("NestedReductionTest.scala:53:42") // RegWrite(x2386,x2401,b867)
    val x2404 = Counter(min=Const(0), max=Const(2), step=Const(1), par=2).name("x2404").ctrl(x2464).srcCtx("NestedReductionTest.scala:50:102") // CounterNew(Const(0),Const(2),Const(1),Const(2))
    val x2405 = CounterChain(List(x2404)).name("x2405").ctrl(x2464).srcCtx("NestedReductionTest.scala:53:42") // CounterChainNew(List(x2404))
    val x2415 = LoopController(style=InnerPipe, level=InnerControl, cchain=x2405).name("x2415").ctrl(x2464).srcCtx("NestedReductionTest.scala:53:42") // UnrolledReduce(List(b868),x2405,x2387,Block((x2387) => Const(())),List(List(b909)),List(List(b910)))
    val b909 = CounterIter(x2404, None).name("b909").ctrl(x2415) // b909
    val b910 = Const(true).name("b910").ctrl(x2415) // b910
    val x2406 = LoadBanks(List(x2369_d1_b0), List(b909)).name("x2406").ctrl(x2415).srcCtx("NestedReductionTest.scala:51:40") // ParRegFileLoad(x2369,List(List(b909)),List(Const(true)))
    val x2407 = x2406 // x2407 = VectorApply(x2406,0)
    val x2408 = OpDef(op=FltSub, inputs=List(Const(23.5), x2407)).name("x2408").ctrl(x2415).srcCtx("NestedReductionTest.scala:51:31:diff") // FltSub(Const(23.50000),x2407)
    val x2409 = OpDef(op=FltMul, inputs=List(x2408, x2408)).name("x2409").ctrl(x2415).srcCtx("NestedReductionTest.scala:52:18") // FltMul(x2408,x2408)
    val x2410 = OpDef(op=BitAnd, inputs=List(b910, b868)).name("x2410").ctrl(x2415).srcCtx("NestedReductionTest.scala:53:42") // And(b910,b868)
    val x2411 = ReadMem(x2387_d1).name("x2411").ctrl(x2415).srcCtx("NestedReductionTest.scala:53:42") // RegRead(x2387)
    val x2412 = OpDef(op=FixEql, inputs=List(b909, Const(0))).name("x2412").ctrl(x2415).srcCtx("NestedReductionTest.scala:53:42") // FixEql(b909,Const(0))
    val x2413 = ReduceAccumOp(op=FltAdd, input=x2409, accum=x2411).name("x2413").ctrl(x2415).srcCtx("NestedReductionTest.scala:53:44") // FltAdd(x2409,x2411)
    val x2414_x2387_d0 = WriteMem(x2387_d0, x2413).name("x2414_x2387_d0").ctrl(x2415).srcCtx("NestedReductionTest.scala:53:42") // RegWrite(x2387,x2413,b868)
    val x2414_x2387_d1 = WriteMem(x2387_d1, x2413).name("x2414_x2387_d1").ctrl(x2415).srcCtx("NestedReductionTest.scala:53:42") // RegWrite(x2387,x2413,b868)
    val x2416 = Counter(min=Const(0), max=Const(2), step=Const(1), par=2).name("x2416").ctrl(x2464).srcCtx("NestedReductionTest.scala:50:102") // CounterNew(Const(0),Const(2),Const(1),Const(2))
    val x2417 = CounterChain(List(x2416)).name("x2417").ctrl(x2464).srcCtx("NestedReductionTest.scala:53:42") // CounterChainNew(List(x2416))
    val x2427 = LoopController(style=InnerPipe, level=InnerControl, cchain=x2417).name("x2427").ctrl(x2464).srcCtx("NestedReductionTest.scala:53:42") // UnrolledReduce(List(b869),x2417,x2388,Block((x2388) => Const(())),List(List(b921)),List(List(b922)))
    val b921 = CounterIter(x2416, None).name("b921").ctrl(x2427) // b921
    val b922 = Const(true).name("b922").ctrl(x2427) // b922
    val x2418 = LoadBanks(List(x2369_d2_b0), List(b921)).name("x2418").ctrl(x2427).srcCtx("NestedReductionTest.scala:51:40") // ParRegFileLoad(x2369,List(List(b921)),List(Const(true)))
    val x2419 = x2418 // x2419 = VectorApply(x2418,0)
    val x2420 = OpDef(op=FltSub, inputs=List(Const(23.5), x2419)).name("x2420").ctrl(x2427).srcCtx("NestedReductionTest.scala:51:31:diff") // FltSub(Const(23.50000),x2419)
    val x2421 = OpDef(op=FltMul, inputs=List(x2420, x2420)).name("x2421").ctrl(x2427).srcCtx("NestedReductionTest.scala:52:18") // FltMul(x2420,x2420)
    val x2422 = OpDef(op=BitAnd, inputs=List(b922, b869)).name("x2422").ctrl(x2427).srcCtx("NestedReductionTest.scala:53:42") // And(b922,b869)
    val x2423 = ReadMem(x2388_d1).name("x2423").ctrl(x2427).srcCtx("NestedReductionTest.scala:53:42") // RegRead(x2388)
    val x2424 = OpDef(op=FixEql, inputs=List(b921, Const(0))).name("x2424").ctrl(x2427).srcCtx("NestedReductionTest.scala:53:42") // FixEql(b921,Const(0))
    val x2425 = ReduceAccumOp(op=FltAdd, input=x2421, accum=x2423).name("x2425").ctrl(x2427).srcCtx("NestedReductionTest.scala:53:44") // FltAdd(x2421,x2423)
    val x2426_x2388_d0 = WriteMem(x2388_d0, x2425).name("x2426_x2388_d0").ctrl(x2427).srcCtx("NestedReductionTest.scala:53:42") // RegWrite(x2388,x2425,b869)
    val x2426_x2388_d1 = WriteMem(x2388_d1, x2425).name("x2426_x2388_d1").ctrl(x2427).srcCtx("NestedReductionTest.scala:53:42") // RegWrite(x2388,x2425,b869)
    val x2428 = Counter(min=Const(0), max=Const(2), step=Const(1), par=2).name("x2428").ctrl(x2464).srcCtx("NestedReductionTest.scala:50:102") // CounterNew(Const(0),Const(2),Const(1),Const(2))
    val x2429 = CounterChain(List(x2428)).name("x2429").ctrl(x2464).srcCtx("NestedReductionTest.scala:53:42") // CounterChainNew(List(x2428))
    val x2439 = LoopController(style=InnerPipe, level=InnerControl, cchain=x2429).name("x2439").ctrl(x2464).srcCtx("NestedReductionTest.scala:53:42") // UnrolledReduce(List(b870),x2429,x2389,Block((x2389) => Const(())),List(List(b933)),List(List(b934)))
    val b933 = CounterIter(x2428, None).name("b933").ctrl(x2439) // b933
    val b934 = Const(true).name("b934").ctrl(x2439) // b934
    val x2430 = LoadBanks(List(x2369_d3_b0), List(b933)).name("x2430").ctrl(x2439).srcCtx("NestedReductionTest.scala:51:40") // ParRegFileLoad(x2369,List(List(b933)),List(Const(true)))
    val x2431 = x2430 // x2431 = VectorApply(x2430,0)
    val x2432 = OpDef(op=FltSub, inputs=List(Const(23.5), x2431)).name("x2432").ctrl(x2439).srcCtx("NestedReductionTest.scala:51:31:diff") // FltSub(Const(23.50000),x2431)
    val x2433 = OpDef(op=FltMul, inputs=List(x2432, x2432)).name("x2433").ctrl(x2439).srcCtx("NestedReductionTest.scala:52:18") // FltMul(x2432,x2432)
    val x2434 = OpDef(op=BitAnd, inputs=List(b934, b870)).name("x2434").ctrl(x2439).srcCtx("NestedReductionTest.scala:53:42") // And(b934,b870)
    val x2435 = ReadMem(x2389_d1).name("x2435").ctrl(x2439).srcCtx("NestedReductionTest.scala:53:42") // RegRead(x2389)
    val x2436 = OpDef(op=FixEql, inputs=List(b933, Const(0))).name("x2436").ctrl(x2439).srcCtx("NestedReductionTest.scala:53:42") // FixEql(b933,Const(0))
    val x2437 = ReduceAccumOp(op=FltAdd, input=x2433, accum=x2435).name("x2437").ctrl(x2439).srcCtx("NestedReductionTest.scala:53:44") // FltAdd(x2433,x2435)
    val x2438_x2389_d0 = WriteMem(x2389_d0, x2437).name("x2438_x2389_d0").ctrl(x2439).srcCtx("NestedReductionTest.scala:53:42") // RegWrite(x2389,x2437,b870)
    val x2438_x2389_d1 = WriteMem(x2389_d1, x2437).name("x2438_x2389_d1").ctrl(x2439).srcCtx("NestedReductionTest.scala:53:42") // RegWrite(x2389,x2437,b870)
    val x2440 = Counter(min=Const(0), max=Const(2), step=Const(1), par=2).name("x2440").ctrl(x2464).srcCtx("NestedReductionTest.scala:50:102") // CounterNew(Const(0),Const(2),Const(1),Const(2))
    val x2441 = CounterChain(List(x2440)).name("x2441").ctrl(x2464).srcCtx("NestedReductionTest.scala:53:42") // CounterChainNew(List(x2440))
    val x2451 = LoopController(style=InnerPipe, level=InnerControl, cchain=x2441).name("x2451").ctrl(x2464).srcCtx("NestedReductionTest.scala:53:42") // UnrolledReduce(List(b871),x2441,x2390,Block((x2390) => Const(())),List(List(b945)),List(List(b946)))
    val b945 = CounterIter(x2440, None).name("b945").ctrl(x2451) // b945
    val b946 = Const(true).name("b946").ctrl(x2451) // b946
    val x2442 = LoadBanks(List(x2369_d4_b0), List(b945)).name("x2442").ctrl(x2451).srcCtx("NestedReductionTest.scala:51:40") // ParRegFileLoad(x2369,List(List(b945)),List(Const(true)))
    val x2443 = x2442 // x2443 = VectorApply(x2442,0)
    val x2444 = OpDef(op=FltSub, inputs=List(Const(23.5), x2443)).name("x2444").ctrl(x2451).srcCtx("NestedReductionTest.scala:51:31:diff") // FltSub(Const(23.50000),x2443)
    val x2445 = OpDef(op=FltMul, inputs=List(x2444, x2444)).name("x2445").ctrl(x2451).srcCtx("NestedReductionTest.scala:52:18") // FltMul(x2444,x2444)
    val x2446 = OpDef(op=BitAnd, inputs=List(b946, b871)).name("x2446").ctrl(x2451).srcCtx("NestedReductionTest.scala:53:42") // And(b946,b871)
    val x2447 = ReadMem(x2390_d1).name("x2447").ctrl(x2451).srcCtx("NestedReductionTest.scala:53:42") // RegRead(x2390)
    val x2448 = OpDef(op=FixEql, inputs=List(b945, Const(0))).name("x2448").ctrl(x2451).srcCtx("NestedReductionTest.scala:53:42") // FixEql(b945,Const(0))
    val x2449 = ReduceAccumOp(op=FltAdd, input=x2445, accum=x2447).name("x2449").ctrl(x2451).srcCtx("NestedReductionTest.scala:53:44") // FltAdd(x2445,x2447)
    val x2450_x2390_d0 = WriteMem(x2390_d0, x2449).name("x2450_x2390_d0").ctrl(x2451).srcCtx("NestedReductionTest.scala:53:42") // RegWrite(x2390,x2449,b871)
    val x2450_x2390_d1 = WriteMem(x2390_d1, x2449).name("x2450_x2390_d1").ctrl(x2451).srcCtx("NestedReductionTest.scala:53:42") // RegWrite(x2390,x2449,b871)
    val x2452 = Counter(min=Const(0), max=Const(2), step=Const(1), par=2).name("x2452").ctrl(x2464).srcCtx("NestedReductionTest.scala:50:102") // CounterNew(Const(0),Const(2),Const(1),Const(2))
    val x2453 = CounterChain(List(x2452)).name("x2453").ctrl(x2464).srcCtx("NestedReductionTest.scala:53:42") // CounterChainNew(List(x2452))
    val x2463 = LoopController(style=InnerPipe, level=InnerControl, cchain=x2453).name("x2463").ctrl(x2464).srcCtx("NestedReductionTest.scala:53:42") // UnrolledReduce(List(b872),x2453,x2391,Block((x2391) => Const(())),List(List(b957)),List(List(b958)))
    val b957 = CounterIter(x2452, None).name("b957").ctrl(x2463) // b957
    val b958 = Const(true).name("b958").ctrl(x2463) // b958
    val x2454 = LoadBanks(List(x2369_d5_b0), List(b957)).name("x2454").ctrl(x2463).srcCtx("NestedReductionTest.scala:51:40") // ParRegFileLoad(x2369,List(List(b957)),List(Const(true)))
    val x2455 = x2454 // x2455 = VectorApply(x2454,0)
    val x2456 = OpDef(op=FltSub, inputs=List(Const(23.5), x2455)).name("x2456").ctrl(x2463).srcCtx("NestedReductionTest.scala:51:31:diff") // FltSub(Const(23.50000),x2455)
    val x2457 = OpDef(op=FltMul, inputs=List(x2456, x2456)).name("x2457").ctrl(x2463).srcCtx("NestedReductionTest.scala:52:18") // FltMul(x2456,x2456)
    val x2458 = OpDef(op=BitAnd, inputs=List(b958, b872)).name("x2458").ctrl(x2463).srcCtx("NestedReductionTest.scala:53:42") // And(b958,b872)
    val x2459 = ReadMem(x2391_d1).name("x2459").ctrl(x2463).srcCtx("NestedReductionTest.scala:53:42") // RegRead(x2391)
    val x2460 = OpDef(op=FixEql, inputs=List(b957, Const(0))).name("x2460").ctrl(x2463).srcCtx("NestedReductionTest.scala:53:42") // FixEql(b957,Const(0))
    val x2461 = ReduceAccumOp(op=FltAdd, input=x2457, accum=x2459).name("x2461").ctrl(x2463).srcCtx("NestedReductionTest.scala:53:44") // FltAdd(x2457,x2459)
    val x2462_x2391_d0 = WriteMem(x2391_d0, x2461).name("x2462_x2391_d0").ctrl(x2463).srcCtx("NestedReductionTest.scala:53:42") // RegWrite(x2391,x2461,b872)
    val x2462_x2391_d1 = WriteMem(x2391_d1, x2461).name("x2462_x2391_d1").ctrl(x2463).srcCtx("NestedReductionTest.scala:53:42") // RegWrite(x2391,x2461,b872)
    val x2465 = Reg(init=Some(0.0)).name("x2465").ctrl(x2654).srcCtx("NestedReductionTest.scala:56:34") // x2465 = RegNew(Const(0.0))
    isAccum(x2465) = false
    bufferDepthOf(x2465) = 1
    val x2466 = Reg(init=Some(0.0)).name("x2466").ctrl(x2654).srcCtx("NestedReductionTest.scala:56:34") // x2466 = RegNew(Const(0.0))
    isAccum(x2466) = false
    bufferDepthOf(x2466) = 1
    val x2467 = Reg(init=Some(0.0)).name("x2467").ctrl(x2654).srcCtx("NestedReductionTest.scala:56:34") // x2467 = RegNew(Const(0.0))
    isAccum(x2467) = false
    bufferDepthOf(x2467) = 1
    val x2468 = Reg(init=Some(0.0)).name("x2468").ctrl(x2654).srcCtx("NestedReductionTest.scala:56:34") // x2468 = RegNew(Const(0.0))
    isAccum(x2468) = false
    bufferDepthOf(x2468) = 1
    val x2469 = Reg(init=Some(0.0)).name("x2469").ctrl(x2654).srcCtx("NestedReductionTest.scala:56:34") // x2469 = RegNew(Const(0.0))
    isAccum(x2469) = false
    bufferDepthOf(x2469) = 1
    val x2470 = Reg(init=Some(0.0)).name("x2470").ctrl(x2654).srcCtx("NestedReductionTest.scala:56:34") // x2470 = RegNew(Const(0.0))
    isAccum(x2470) = false
    bufferDepthOf(x2470) = 1
    val x2627 = UnitController(style=ForkJoin, level=OuterControl).name("x2627").ctrl(x2654).srcCtx("UnrollingTransformer.scala:431:43") // ParallelPipe(List(Const(true)),Block(Const(())))
    val x2496 = UnitController(style=SeqPipe, level=InnerControl).name("x2496").ctrl(x2627).srcCtx("NestedReductionTest.scala:56:34") // UnitPipe(List(b867),Block(Const(())))
    val x2471 = ReadMem(x2386_d0).name("x2471").ctrl(x2496).srcCtx("NestedReductionTest.scala:53:42") // RegRead(x2386)
    val x2472_x2380 = WriteMem(x2380, x2471).name("x2472_x2380").ctrl(x2496).srcCtx("NestedReductionTest.scala:50:49") // RegWrite(x2380,x2471,b867)
    val x2473 = ReadMem(x2380).name("x2473").ctrl(x2496).srcCtx("NestedReductionTest.scala:55:73") // RegRead(x2380)
    val x2474 = OpDef(op=FltMul, inputs=List(Const(-0.5), x2473)).name("x2474").ctrl(x2496).srcCtx("NestedReductionTest.scala:55:73") // FltMul(Const(-0.5),x2473)
    val x2475 = OpDef(op=FltLt, inputs=List(x2474, Const(-3.5))).name("x2475").ctrl(x2496).srcCtx("NestedReductionTest.scala:55:59") // FltLt(x2474,Const(-3.50))
    val x2476 = OpDef(op=FltLt, inputs=List(x2474, Const(-1.2))).name("x2476").ctrl(x2496).srcCtx("NestedReductionTest.scala:55:59") // FltLt(x2474,Const(-1.2000000476837158203125))
    val x2477 = OpDef(op=FltMul, inputs=List(x2474, Const(0.1))).name("x2477").ctrl(x2496).srcCtx("NestedReductionTest.scala:55:59") // FltMul(x2474,Const(0.100000001490116119384765625))
    val x2478 = OpDef(op=FltAdd, inputs=List(x2477, Const(0.35))).name("x2478").ctrl(x2496).srcCtx("NestedReductionTest.scala:55:59") // FltAdd(x2477,Const(0.3499999940395355224609375))
    val x2479 = OpDef(op=FltAdd, inputs=List(Const(1.0), x2474)).name("x2479").ctrl(x2496).srcCtx("NestedReductionTest.scala:55:59") // FltAdd(Const(1),x2474)
    val x2480 = OpDef(op=FltMul, inputs=List(x2474, x2474)).name("x2480").ctrl(x2496).srcCtx("NestedReductionTest.scala:55:59") // FltMul(x2474,x2474)
    val x2481 = OpDef(op=FltDiv, inputs=List(x2480, Const(2.0))).name("x2481").ctrl(x2496).srcCtx("NestedReductionTest.scala:55:59") // FltDiv(x2480,Const(2))
    val x2482 = OpDef(op=FltAdd, inputs=List(x2479, x2481)).name("x2482").ctrl(x2496).srcCtx("NestedReductionTest.scala:55:59") // FltAdd(x2479,x2481)
    val x2483 = OpDef(op=FltMul, inputs=List(x2480, x2474)).name("x2483").ctrl(x2496).srcCtx("NestedReductionTest.scala:55:59") // FltMul(x2480,x2474)
    val x2484 = OpDef(op=FltDiv, inputs=List(x2483, Const(6.0))).name("x2484").ctrl(x2496).srcCtx("NestedReductionTest.scala:55:59") // FltDiv(x2483,Const(6.0))
    val x2485 = OpDef(op=FltAdd, inputs=List(x2482, x2484)).name("x2485").ctrl(x2496).srcCtx("NestedReductionTest.scala:55:59") // FltAdd(x2482,x2484)
    val x2486 = OpDef(op=FltMul, inputs=List(x2483, x2474)).name("x2486").ctrl(x2496).srcCtx("NestedReductionTest.scala:55:59") // FltMul(x2483,x2474)
    val x2487 = OpDef(op=FltDiv, inputs=List(x2486, Const(24.0))).name("x2487").ctrl(x2496).srcCtx("NestedReductionTest.scala:55:59") // FltDiv(x2486,Const(24.0))
    val x2488 = OpDef(op=FltAdd, inputs=List(x2485, x2487)).name("x2488").ctrl(x2496).srcCtx("NestedReductionTest.scala:55:59") // FltAdd(x2485,x2487)
    val x2489 = OpDef(op=FltMul, inputs=List(x2486, x2474)).name("x2489").ctrl(x2496).srcCtx("NestedReductionTest.scala:55:59") // FltMul(x2486,x2474)
    val x2490 = OpDef(op=FltDiv, inputs=List(x2489, Const(120.0))).name("x2490").ctrl(x2496).srcCtx("NestedReductionTest.scala:55:59") // FltDiv(x2489,Const(120.000))
    val x2491 = OpDef(op=FltAdd, inputs=List(x2488, x2490)).name("x2491").ctrl(x2496).srcCtx("NestedReductionTest.scala:55:59") // FltAdd(x2488,x2490)
    val x2492 = OpDef(op=MuxOp, inputs=List(x2476, x2478, x2491)).name("x2492").ctrl(x2496).srcCtx("NestedReductionTest.scala:55:59") // Mux(x2476,x2478,x2491)
    val x2493 = OpDef(op=MuxOp, inputs=List(x2475, Const(0.0), x2492)).name("x2493").ctrl(x2496).srcCtx("NestedReductionTest.scala:55:59") // Mux(x2475,Const(0.0),x2492)
    val x2494 = OpDef(op=FltMul, inputs=List(Const(32.5), x2493)).name("x2494").ctrl(x2496).srcCtx("NestedReductionTest.scala:55:47") // FltMul(Const(32.500000),x2493)
    val x2495_x2465 = WriteMem(x2465, x2494).name("x2495_x2465").ctrl(x2496).srcCtx("NestedReductionTest.scala:56:34") // RegWrite(x2465,x2494,b867)
    val x2522 = UnitController(style=SeqPipe, level=InnerControl).name("x2522").ctrl(x2627).srcCtx("NestedReductionTest.scala:56:34") // UnitPipe(List(b868),Block(Const(())))
    val x2497 = ReadMem(x2387_d0).name("x2497").ctrl(x2522).srcCtx("NestedReductionTest.scala:53:42") // RegRead(x2387)
    val x2498_x2381 = WriteMem(x2381, x2497).name("x2498_x2381").ctrl(x2522).srcCtx("NestedReductionTest.scala:50:49") // RegWrite(x2381,x2497,b868)
    val x2499 = ReadMem(x2381).name("x2499").ctrl(x2522).srcCtx("NestedReductionTest.scala:55:73") // RegRead(x2381)
    val x2500 = OpDef(op=FltMul, inputs=List(Const(-0.5), x2499)).name("x2500").ctrl(x2522).srcCtx("NestedReductionTest.scala:55:73") // FltMul(Const(-0.5),x2499)
    val x2501 = OpDef(op=FltLt, inputs=List(x2500, Const(-3.5))).name("x2501").ctrl(x2522).srcCtx("NestedReductionTest.scala:55:59") // FltLt(x2500,Const(-3.50))
    val x2502 = OpDef(op=FltLt, inputs=List(x2500, Const(-1.2))).name("x2502").ctrl(x2522).srcCtx("NestedReductionTest.scala:55:59") // FltLt(x2500,Const(-1.2000000476837158203125))
    val x2503 = OpDef(op=FltMul, inputs=List(x2500, Const(0.1))).name("x2503").ctrl(x2522).srcCtx("NestedReductionTest.scala:55:59") // FltMul(x2500,Const(0.100000001490116119384765625))
    val x2504 = OpDef(op=FltAdd, inputs=List(x2503, Const(0.35))).name("x2504").ctrl(x2522).srcCtx("NestedReductionTest.scala:55:59") // FltAdd(x2503,Const(0.3499999940395355224609375))
    val x2505 = OpDef(op=FltAdd, inputs=List(Const(1.0), x2500)).name("x2505").ctrl(x2522).srcCtx("NestedReductionTest.scala:55:59") // FltAdd(Const(1),x2500)
    val x2506 = OpDef(op=FltMul, inputs=List(x2500, x2500)).name("x2506").ctrl(x2522).srcCtx("NestedReductionTest.scala:55:59") // FltMul(x2500,x2500)
    val x2507 = OpDef(op=FltDiv, inputs=List(x2506, Const(2.0))).name("x2507").ctrl(x2522).srcCtx("NestedReductionTest.scala:55:59") // FltDiv(x2506,Const(2))
    val x2508 = OpDef(op=FltAdd, inputs=List(x2505, x2507)).name("x2508").ctrl(x2522).srcCtx("NestedReductionTest.scala:55:59") // FltAdd(x2505,x2507)
    val x2509 = OpDef(op=FltMul, inputs=List(x2506, x2500)).name("x2509").ctrl(x2522).srcCtx("NestedReductionTest.scala:55:59") // FltMul(x2506,x2500)
    val x2510 = OpDef(op=FltDiv, inputs=List(x2509, Const(6.0))).name("x2510").ctrl(x2522).srcCtx("NestedReductionTest.scala:55:59") // FltDiv(x2509,Const(6.0))
    val x2511 = OpDef(op=FltAdd, inputs=List(x2508, x2510)).name("x2511").ctrl(x2522).srcCtx("NestedReductionTest.scala:55:59") // FltAdd(x2508,x2510)
    val x2512 = OpDef(op=FltMul, inputs=List(x2509, x2500)).name("x2512").ctrl(x2522).srcCtx("NestedReductionTest.scala:55:59") // FltMul(x2509,x2500)
    val x2513 = OpDef(op=FltDiv, inputs=List(x2512, Const(24.0))).name("x2513").ctrl(x2522).srcCtx("NestedReductionTest.scala:55:59") // FltDiv(x2512,Const(24.0))
    val x2514 = OpDef(op=FltAdd, inputs=List(x2511, x2513)).name("x2514").ctrl(x2522).srcCtx("NestedReductionTest.scala:55:59") // FltAdd(x2511,x2513)
    val x2515 = OpDef(op=FltMul, inputs=List(x2512, x2500)).name("x2515").ctrl(x2522).srcCtx("NestedReductionTest.scala:55:59") // FltMul(x2512,x2500)
    val x2516 = OpDef(op=FltDiv, inputs=List(x2515, Const(120.0))).name("x2516").ctrl(x2522).srcCtx("NestedReductionTest.scala:55:59") // FltDiv(x2515,Const(120.000))
    val x2517 = OpDef(op=FltAdd, inputs=List(x2514, x2516)).name("x2517").ctrl(x2522).srcCtx("NestedReductionTest.scala:55:59") // FltAdd(x2514,x2516)
    val x2518 = OpDef(op=MuxOp, inputs=List(x2502, x2504, x2517)).name("x2518").ctrl(x2522).srcCtx("NestedReductionTest.scala:55:59") // Mux(x2502,x2504,x2517)
    val x2519 = OpDef(op=MuxOp, inputs=List(x2501, Const(0.0), x2518)).name("x2519").ctrl(x2522).srcCtx("NestedReductionTest.scala:55:59") // Mux(x2501,Const(0.0),x2518)
    val x2520 = OpDef(op=FltMul, inputs=List(Const(32.5), x2519)).name("x2520").ctrl(x2522).srcCtx("NestedReductionTest.scala:55:47") // FltMul(Const(32.500000),x2519)
    val x2521_x2466 = WriteMem(x2466, x2520).name("x2521_x2466").ctrl(x2522).srcCtx("NestedReductionTest.scala:56:34") // RegWrite(x2466,x2520,b868)
    val x2548 = UnitController(style=SeqPipe, level=InnerControl).name("x2548").ctrl(x2627).srcCtx("NestedReductionTest.scala:56:34") // UnitPipe(List(b869),Block(Const(())))
    val x2523 = ReadMem(x2388_d0).name("x2523").ctrl(x2548).srcCtx("NestedReductionTest.scala:53:42") // RegRead(x2388)
    val x2524_x2382 = WriteMem(x2382, x2523).name("x2524_x2382").ctrl(x2548).srcCtx("NestedReductionTest.scala:50:49") // RegWrite(x2382,x2523,b869)
    val x2525 = ReadMem(x2382).name("x2525").ctrl(x2548).srcCtx("NestedReductionTest.scala:55:73") // RegRead(x2382)
    val x2526 = OpDef(op=FltMul, inputs=List(Const(-0.5), x2525)).name("x2526").ctrl(x2548).srcCtx("NestedReductionTest.scala:55:73") // FltMul(Const(-0.5),x2525)
    val x2527 = OpDef(op=FltLt, inputs=List(x2526, Const(-3.5))).name("x2527").ctrl(x2548).srcCtx("NestedReductionTest.scala:55:59") // FltLt(x2526,Const(-3.50))
    val x2528 = OpDef(op=FltLt, inputs=List(x2526, Const(-1.2))).name("x2528").ctrl(x2548).srcCtx("NestedReductionTest.scala:55:59") // FltLt(x2526,Const(-1.2000000476837158203125))
    val x2529 = OpDef(op=FltMul, inputs=List(x2526, Const(0.1))).name("x2529").ctrl(x2548).srcCtx("NestedReductionTest.scala:55:59") // FltMul(x2526,Const(0.100000001490116119384765625))
    val x2530 = OpDef(op=FltAdd, inputs=List(x2529, Const(0.35))).name("x2530").ctrl(x2548).srcCtx("NestedReductionTest.scala:55:59") // FltAdd(x2529,Const(0.3499999940395355224609375))
    val x2531 = OpDef(op=FltAdd, inputs=List(Const(1.0), x2526)).name("x2531").ctrl(x2548).srcCtx("NestedReductionTest.scala:55:59") // FltAdd(Const(1),x2526)
    val x2532 = OpDef(op=FltMul, inputs=List(x2526, x2526)).name("x2532").ctrl(x2548).srcCtx("NestedReductionTest.scala:55:59") // FltMul(x2526,x2526)
    val x2533 = OpDef(op=FltDiv, inputs=List(x2532, Const(2.0))).name("x2533").ctrl(x2548).srcCtx("NestedReductionTest.scala:55:59") // FltDiv(x2532,Const(2))
    val x2534 = OpDef(op=FltAdd, inputs=List(x2531, x2533)).name("x2534").ctrl(x2548).srcCtx("NestedReductionTest.scala:55:59") // FltAdd(x2531,x2533)
    val x2535 = OpDef(op=FltMul, inputs=List(x2532, x2526)).name("x2535").ctrl(x2548).srcCtx("NestedReductionTest.scala:55:59") // FltMul(x2532,x2526)
    val x2536 = OpDef(op=FltDiv, inputs=List(x2535, Const(6.0))).name("x2536").ctrl(x2548).srcCtx("NestedReductionTest.scala:55:59") // FltDiv(x2535,Const(6.0))
    val x2537 = OpDef(op=FltAdd, inputs=List(x2534, x2536)).name("x2537").ctrl(x2548).srcCtx("NestedReductionTest.scala:55:59") // FltAdd(x2534,x2536)
    val x2538 = OpDef(op=FltMul, inputs=List(x2535, x2526)).name("x2538").ctrl(x2548).srcCtx("NestedReductionTest.scala:55:59") // FltMul(x2535,x2526)
    val x2539 = OpDef(op=FltDiv, inputs=List(x2538, Const(24.0))).name("x2539").ctrl(x2548).srcCtx("NestedReductionTest.scala:55:59") // FltDiv(x2538,Const(24.0))
    val x2540 = OpDef(op=FltAdd, inputs=List(x2537, x2539)).name("x2540").ctrl(x2548).srcCtx("NestedReductionTest.scala:55:59") // FltAdd(x2537,x2539)
    val x2541 = OpDef(op=FltMul, inputs=List(x2538, x2526)).name("x2541").ctrl(x2548).srcCtx("NestedReductionTest.scala:55:59") // FltMul(x2538,x2526)
    val x2542 = OpDef(op=FltDiv, inputs=List(x2541, Const(120.0))).name("x2542").ctrl(x2548).srcCtx("NestedReductionTest.scala:55:59") // FltDiv(x2541,Const(120.000))
    val x2543 = OpDef(op=FltAdd, inputs=List(x2540, x2542)).name("x2543").ctrl(x2548).srcCtx("NestedReductionTest.scala:55:59") // FltAdd(x2540,x2542)
    val x2544 = OpDef(op=MuxOp, inputs=List(x2528, x2530, x2543)).name("x2544").ctrl(x2548).srcCtx("NestedReductionTest.scala:55:59") // Mux(x2528,x2530,x2543)
    val x2545 = OpDef(op=MuxOp, inputs=List(x2527, Const(0.0), x2544)).name("x2545").ctrl(x2548).srcCtx("NestedReductionTest.scala:55:59") // Mux(x2527,Const(0.0),x2544)
    val x2546 = OpDef(op=FltMul, inputs=List(Const(32.5), x2545)).name("x2546").ctrl(x2548).srcCtx("NestedReductionTest.scala:55:47") // FltMul(Const(32.500000),x2545)
    val x2547_x2467 = WriteMem(x2467, x2546).name("x2547_x2467").ctrl(x2548).srcCtx("NestedReductionTest.scala:56:34") // RegWrite(x2467,x2546,b869)
    val x2574 = UnitController(style=SeqPipe, level=InnerControl).name("x2574").ctrl(x2627).srcCtx("NestedReductionTest.scala:56:34") // UnitPipe(List(b870),Block(Const(())))
    val x2549 = ReadMem(x2389_d0).name("x2549").ctrl(x2574).srcCtx("NestedReductionTest.scala:53:42") // RegRead(x2389)
    val x2550_x2383 = WriteMem(x2383, x2549).name("x2550_x2383").ctrl(x2574).srcCtx("NestedReductionTest.scala:50:49") // RegWrite(x2383,x2549,b870)
    val x2551 = ReadMem(x2383).name("x2551").ctrl(x2574).srcCtx("NestedReductionTest.scala:55:73") // RegRead(x2383)
    val x2552 = OpDef(op=FltMul, inputs=List(Const(-0.5), x2551)).name("x2552").ctrl(x2574).srcCtx("NestedReductionTest.scala:55:73") // FltMul(Const(-0.5),x2551)
    val x2553 = OpDef(op=FltLt, inputs=List(x2552, Const(-3.5))).name("x2553").ctrl(x2574).srcCtx("NestedReductionTest.scala:55:59") // FltLt(x2552,Const(-3.50))
    val x2554 = OpDef(op=FltLt, inputs=List(x2552, Const(-1.2))).name("x2554").ctrl(x2574).srcCtx("NestedReductionTest.scala:55:59") // FltLt(x2552,Const(-1.2000000476837158203125))
    val x2555 = OpDef(op=FltMul, inputs=List(x2552, Const(0.1))).name("x2555").ctrl(x2574).srcCtx("NestedReductionTest.scala:55:59") // FltMul(x2552,Const(0.100000001490116119384765625))
    val x2556 = OpDef(op=FltAdd, inputs=List(x2555, Const(0.35))).name("x2556").ctrl(x2574).srcCtx("NestedReductionTest.scala:55:59") // FltAdd(x2555,Const(0.3499999940395355224609375))
    val x2557 = OpDef(op=FltAdd, inputs=List(Const(1.0), x2552)).name("x2557").ctrl(x2574).srcCtx("NestedReductionTest.scala:55:59") // FltAdd(Const(1),x2552)
    val x2558 = OpDef(op=FltMul, inputs=List(x2552, x2552)).name("x2558").ctrl(x2574).srcCtx("NestedReductionTest.scala:55:59") // FltMul(x2552,x2552)
    val x2559 = OpDef(op=FltDiv, inputs=List(x2558, Const(2.0))).name("x2559").ctrl(x2574).srcCtx("NestedReductionTest.scala:55:59") // FltDiv(x2558,Const(2))
    val x2560 = OpDef(op=FltAdd, inputs=List(x2557, x2559)).name("x2560").ctrl(x2574).srcCtx("NestedReductionTest.scala:55:59") // FltAdd(x2557,x2559)
    val x2561 = OpDef(op=FltMul, inputs=List(x2558, x2552)).name("x2561").ctrl(x2574).srcCtx("NestedReductionTest.scala:55:59") // FltMul(x2558,x2552)
    val x2562 = OpDef(op=FltDiv, inputs=List(x2561, Const(6.0))).name("x2562").ctrl(x2574).srcCtx("NestedReductionTest.scala:55:59") // FltDiv(x2561,Const(6.0))
    val x2563 = OpDef(op=FltAdd, inputs=List(x2560, x2562)).name("x2563").ctrl(x2574).srcCtx("NestedReductionTest.scala:55:59") // FltAdd(x2560,x2562)
    val x2564 = OpDef(op=FltMul, inputs=List(x2561, x2552)).name("x2564").ctrl(x2574).srcCtx("NestedReductionTest.scala:55:59") // FltMul(x2561,x2552)
    val x2565 = OpDef(op=FltDiv, inputs=List(x2564, Const(24.0))).name("x2565").ctrl(x2574).srcCtx("NestedReductionTest.scala:55:59") // FltDiv(x2564,Const(24.0))
    val x2566 = OpDef(op=FltAdd, inputs=List(x2563, x2565)).name("x2566").ctrl(x2574).srcCtx("NestedReductionTest.scala:55:59") // FltAdd(x2563,x2565)
    val x2567 = OpDef(op=FltMul, inputs=List(x2564, x2552)).name("x2567").ctrl(x2574).srcCtx("NestedReductionTest.scala:55:59") // FltMul(x2564,x2552)
    val x2568 = OpDef(op=FltDiv, inputs=List(x2567, Const(120.0))).name("x2568").ctrl(x2574).srcCtx("NestedReductionTest.scala:55:59") // FltDiv(x2567,Const(120.000))
    val x2569 = OpDef(op=FltAdd, inputs=List(x2566, x2568)).name("x2569").ctrl(x2574).srcCtx("NestedReductionTest.scala:55:59") // FltAdd(x2566,x2568)
    val x2570 = OpDef(op=MuxOp, inputs=List(x2554, x2556, x2569)).name("x2570").ctrl(x2574).srcCtx("NestedReductionTest.scala:55:59") // Mux(x2554,x2556,x2569)
    val x2571 = OpDef(op=MuxOp, inputs=List(x2553, Const(0.0), x2570)).name("x2571").ctrl(x2574).srcCtx("NestedReductionTest.scala:55:59") // Mux(x2553,Const(0.0),x2570)
    val x2572 = OpDef(op=FltMul, inputs=List(Const(32.5), x2571)).name("x2572").ctrl(x2574).srcCtx("NestedReductionTest.scala:55:47") // FltMul(Const(32.500000),x2571)
    val x2573_x2468 = WriteMem(x2468, x2572).name("x2573_x2468").ctrl(x2574).srcCtx("NestedReductionTest.scala:56:34") // RegWrite(x2468,x2572,b870)
    val x2600 = UnitController(style=SeqPipe, level=InnerControl).name("x2600").ctrl(x2627).srcCtx("NestedReductionTest.scala:56:34") // UnitPipe(List(b871),Block(Const(())))
    val x2575 = ReadMem(x2390_d0).name("x2575").ctrl(x2600).srcCtx("NestedReductionTest.scala:53:42") // RegRead(x2390)
    val x2576_x2384 = WriteMem(x2384, x2575).name("x2576_x2384").ctrl(x2600).srcCtx("NestedReductionTest.scala:50:49") // RegWrite(x2384,x2575,b871)
    val x2577 = ReadMem(x2384).name("x2577").ctrl(x2600).srcCtx("NestedReductionTest.scala:55:73") // RegRead(x2384)
    val x2578 = OpDef(op=FltMul, inputs=List(Const(-0.5), x2577)).name("x2578").ctrl(x2600).srcCtx("NestedReductionTest.scala:55:73") // FltMul(Const(-0.5),x2577)
    val x2579 = OpDef(op=FltLt, inputs=List(x2578, Const(-3.5))).name("x2579").ctrl(x2600).srcCtx("NestedReductionTest.scala:55:59") // FltLt(x2578,Const(-3.50))
    val x2580 = OpDef(op=FltLt, inputs=List(x2578, Const(-1.2))).name("x2580").ctrl(x2600).srcCtx("NestedReductionTest.scala:55:59") // FltLt(x2578,Const(-1.2000000476837158203125))
    val x2581 = OpDef(op=FltMul, inputs=List(x2578, Const(0.1))).name("x2581").ctrl(x2600).srcCtx("NestedReductionTest.scala:55:59") // FltMul(x2578,Const(0.100000001490116119384765625))
    val x2582 = OpDef(op=FltAdd, inputs=List(x2581, Const(0.35))).name("x2582").ctrl(x2600).srcCtx("NestedReductionTest.scala:55:59") // FltAdd(x2581,Const(0.3499999940395355224609375))
    val x2583 = OpDef(op=FltAdd, inputs=List(Const(1.0), x2578)).name("x2583").ctrl(x2600).srcCtx("NestedReductionTest.scala:55:59") // FltAdd(Const(1),x2578)
    val x2584 = OpDef(op=FltMul, inputs=List(x2578, x2578)).name("x2584").ctrl(x2600).srcCtx("NestedReductionTest.scala:55:59") // FltMul(x2578,x2578)
    val x2585 = OpDef(op=FltDiv, inputs=List(x2584, Const(2.0))).name("x2585").ctrl(x2600).srcCtx("NestedReductionTest.scala:55:59") // FltDiv(x2584,Const(2))
    val x2586 = OpDef(op=FltAdd, inputs=List(x2583, x2585)).name("x2586").ctrl(x2600).srcCtx("NestedReductionTest.scala:55:59") // FltAdd(x2583,x2585)
    val x2587 = OpDef(op=FltMul, inputs=List(x2584, x2578)).name("x2587").ctrl(x2600).srcCtx("NestedReductionTest.scala:55:59") // FltMul(x2584,x2578)
    val x2588 = OpDef(op=FltDiv, inputs=List(x2587, Const(6.0))).name("x2588").ctrl(x2600).srcCtx("NestedReductionTest.scala:55:59") // FltDiv(x2587,Const(6.0))
    val x2589 = OpDef(op=FltAdd, inputs=List(x2586, x2588)).name("x2589").ctrl(x2600).srcCtx("NestedReductionTest.scala:55:59") // FltAdd(x2586,x2588)
    val x2590 = OpDef(op=FltMul, inputs=List(x2587, x2578)).name("x2590").ctrl(x2600).srcCtx("NestedReductionTest.scala:55:59") // FltMul(x2587,x2578)
    val x2591 = OpDef(op=FltDiv, inputs=List(x2590, Const(24.0))).name("x2591").ctrl(x2600).srcCtx("NestedReductionTest.scala:55:59") // FltDiv(x2590,Const(24.0))
    val x2592 = OpDef(op=FltAdd, inputs=List(x2589, x2591)).name("x2592").ctrl(x2600).srcCtx("NestedReductionTest.scala:55:59") // FltAdd(x2589,x2591)
    val x2593 = OpDef(op=FltMul, inputs=List(x2590, x2578)).name("x2593").ctrl(x2600).srcCtx("NestedReductionTest.scala:55:59") // FltMul(x2590,x2578)
    val x2594 = OpDef(op=FltDiv, inputs=List(x2593, Const(120.0))).name("x2594").ctrl(x2600).srcCtx("NestedReductionTest.scala:55:59") // FltDiv(x2593,Const(120.000))
    val x2595 = OpDef(op=FltAdd, inputs=List(x2592, x2594)).name("x2595").ctrl(x2600).srcCtx("NestedReductionTest.scala:55:59") // FltAdd(x2592,x2594)
    val x2596 = OpDef(op=MuxOp, inputs=List(x2580, x2582, x2595)).name("x2596").ctrl(x2600).srcCtx("NestedReductionTest.scala:55:59") // Mux(x2580,x2582,x2595)
    val x2597 = OpDef(op=MuxOp, inputs=List(x2579, Const(0.0), x2596)).name("x2597").ctrl(x2600).srcCtx("NestedReductionTest.scala:55:59") // Mux(x2579,Const(0.0),x2596)
    val x2598 = OpDef(op=FltMul, inputs=List(Const(32.5), x2597)).name("x2598").ctrl(x2600).srcCtx("NestedReductionTest.scala:55:47") // FltMul(Const(32.500000),x2597)
    val x2599_x2469 = WriteMem(x2469, x2598).name("x2599_x2469").ctrl(x2600).srcCtx("NestedReductionTest.scala:56:34") // RegWrite(x2469,x2598,b871)
    val x2626 = UnitController(style=SeqPipe, level=InnerControl).name("x2626").ctrl(x2627).srcCtx("NestedReductionTest.scala:56:34") // UnitPipe(List(b872),Block(Const(())))
    val x2601 = ReadMem(x2391_d0).name("x2601").ctrl(x2626).srcCtx("NestedReductionTest.scala:53:42") // RegRead(x2391)
    val x2602_x2385 = WriteMem(x2385, x2601).name("x2602_x2385").ctrl(x2626).srcCtx("NestedReductionTest.scala:50:49") // RegWrite(x2385,x2601,b872)
    val x2603 = ReadMem(x2385).name("x2603").ctrl(x2626).srcCtx("NestedReductionTest.scala:55:73") // RegRead(x2385)
    val x2604 = OpDef(op=FltMul, inputs=List(Const(-0.5), x2603)).name("x2604").ctrl(x2626).srcCtx("NestedReductionTest.scala:55:73") // FltMul(Const(-0.5),x2603)
    val x2605 = OpDef(op=FltLt, inputs=List(x2604, Const(-3.5))).name("x2605").ctrl(x2626).srcCtx("NestedReductionTest.scala:55:59") // FltLt(x2604,Const(-3.50))
    val x2606 = OpDef(op=FltLt, inputs=List(x2604, Const(-1.2))).name("x2606").ctrl(x2626).srcCtx("NestedReductionTest.scala:55:59") // FltLt(x2604,Const(-1.2000000476837158203125))
    val x2607 = OpDef(op=FltMul, inputs=List(x2604, Const(0.1))).name("x2607").ctrl(x2626).srcCtx("NestedReductionTest.scala:55:59") // FltMul(x2604,Const(0.100000001490116119384765625))
    val x2608 = OpDef(op=FltAdd, inputs=List(x2607, Const(0.35))).name("x2608").ctrl(x2626).srcCtx("NestedReductionTest.scala:55:59") // FltAdd(x2607,Const(0.3499999940395355224609375))
    val x2609 = OpDef(op=FltAdd, inputs=List(Const(1.0), x2604)).name("x2609").ctrl(x2626).srcCtx("NestedReductionTest.scala:55:59") // FltAdd(Const(1),x2604)
    val x2610 = OpDef(op=FltMul, inputs=List(x2604, x2604)).name("x2610").ctrl(x2626).srcCtx("NestedReductionTest.scala:55:59") // FltMul(x2604,x2604)
    val x2611 = OpDef(op=FltDiv, inputs=List(x2610, Const(2.0))).name("x2611").ctrl(x2626).srcCtx("NestedReductionTest.scala:55:59") // FltDiv(x2610,Const(2))
    val x2612 = OpDef(op=FltAdd, inputs=List(x2609, x2611)).name("x2612").ctrl(x2626).srcCtx("NestedReductionTest.scala:55:59") // FltAdd(x2609,x2611)
    val x2613 = OpDef(op=FltMul, inputs=List(x2610, x2604)).name("x2613").ctrl(x2626).srcCtx("NestedReductionTest.scala:55:59") // FltMul(x2610,x2604)
    val x2614 = OpDef(op=FltDiv, inputs=List(x2613, Const(6.0))).name("x2614").ctrl(x2626).srcCtx("NestedReductionTest.scala:55:59") // FltDiv(x2613,Const(6.0))
    val x2615 = OpDef(op=FltAdd, inputs=List(x2612, x2614)).name("x2615").ctrl(x2626).srcCtx("NestedReductionTest.scala:55:59") // FltAdd(x2612,x2614)
    val x2616 = OpDef(op=FltMul, inputs=List(x2613, x2604)).name("x2616").ctrl(x2626).srcCtx("NestedReductionTest.scala:55:59") // FltMul(x2613,x2604)
    val x2617 = OpDef(op=FltDiv, inputs=List(x2616, Const(24.0))).name("x2617").ctrl(x2626).srcCtx("NestedReductionTest.scala:55:59") // FltDiv(x2616,Const(24.0))
    val x2618 = OpDef(op=FltAdd, inputs=List(x2615, x2617)).name("x2618").ctrl(x2626).srcCtx("NestedReductionTest.scala:55:59") // FltAdd(x2615,x2617)
    val x2619 = OpDef(op=FltMul, inputs=List(x2616, x2604)).name("x2619").ctrl(x2626).srcCtx("NestedReductionTest.scala:55:59") // FltMul(x2616,x2604)
    val x2620 = OpDef(op=FltDiv, inputs=List(x2619, Const(120.0))).name("x2620").ctrl(x2626).srcCtx("NestedReductionTest.scala:55:59") // FltDiv(x2619,Const(120.000))
    val x2621 = OpDef(op=FltAdd, inputs=List(x2618, x2620)).name("x2621").ctrl(x2626).srcCtx("NestedReductionTest.scala:55:59") // FltAdd(x2618,x2620)
    val x2622 = OpDef(op=MuxOp, inputs=List(x2606, x2608, x2621)).name("x2622").ctrl(x2626).srcCtx("NestedReductionTest.scala:55:59") // Mux(x2606,x2608,x2621)
    val x2623 = OpDef(op=MuxOp, inputs=List(x2605, Const(0.0), x2622)).name("x2623").ctrl(x2626).srcCtx("NestedReductionTest.scala:55:59") // Mux(x2605,Const(0.0),x2622)
    val x2624 = OpDef(op=FltMul, inputs=List(Const(32.5), x2623)).name("x2624").ctrl(x2626).srcCtx("NestedReductionTest.scala:55:47") // FltMul(Const(32.500000),x2623)
    val x2625_x2470 = WriteMem(x2470, x2624).name("x2625_x2470").ctrl(x2626).srcCtx("NestedReductionTest.scala:56:34") // RegWrite(x2470,x2624,b872)
    val x2653 = UnitController(style=SeqPipe, level=InnerControl).name("x2653").ctrl(x2654).srcCtx("NestedReductionTest.scala:56:34") // UnitPipe(List(Const(true)),Block(x2652))
    val x2628 = ReadMem(x2466).name("x2628").ctrl(x2653).srcCtx("NestedReductionTest.scala:56:34") // RegRead(x2466)
    val x2629 = ReadMem(x2465).name("x2629").ctrl(x2653).srcCtx("NestedReductionTest.scala:56:34") // RegRead(x2465)
    val x2630 = OpDef(op=FltAdd, inputs=List(x2629, x2628)).name("x2630").ctrl(x2653).srcCtx("NestedReductionTest.scala:56:36") // FltAdd(x2629,x2628)
    val x2631 = OpDef(op=MuxOp, inputs=List(b868, x2630, x2629)).name("x2631").ctrl(x2653).srcCtx("NestedReductionTest.scala:56:34") // Mux(b868,x2630,x2629)
    val x2632 = OpDef(op=BitOr, inputs=List(b867, b868)).name("x2632").ctrl(x2653).srcCtx("NestedReductionTest.scala:56:34") // Or(b867,b868)
    val x2633 = ReadMem(x2468).name("x2633").ctrl(x2653).srcCtx("NestedReductionTest.scala:56:34") // RegRead(x2468)
    val x2634 = ReadMem(x2467).name("x2634").ctrl(x2653).srcCtx("NestedReductionTest.scala:56:34") // RegRead(x2467)
    val x2635 = OpDef(op=FltAdd, inputs=List(x2634, x2633)).name("x2635").ctrl(x2653).srcCtx("NestedReductionTest.scala:56:36") // FltAdd(x2634,x2633)
    val x2636 = OpDef(op=MuxOp, inputs=List(b870, x2635, x2634)).name("x2636").ctrl(x2653).srcCtx("NestedReductionTest.scala:56:34") // Mux(b870,x2635,x2634)
    val x2637 = OpDef(op=BitOr, inputs=List(b869, b870)).name("x2637").ctrl(x2653).srcCtx("NestedReductionTest.scala:56:34") // Or(b869,b870)
    val x2638 = ReadMem(x2470).name("x2638").ctrl(x2653).srcCtx("NestedReductionTest.scala:56:34") // RegRead(x2470)
    val x2639 = ReadMem(x2469).name("x2639").ctrl(x2653).srcCtx("NestedReductionTest.scala:56:34") // RegRead(x2469)
    val x2640 = OpDef(op=FltAdd, inputs=List(x2639, x2638)).name("x2640").ctrl(x2653).srcCtx("NestedReductionTest.scala:56:36") // FltAdd(x2639,x2638)
    val x2641 = OpDef(op=MuxOp, inputs=List(b872, x2640, x2639)).name("x2641").ctrl(x2653).srcCtx("NestedReductionTest.scala:56:34") // Mux(b872,x2640,x2639)
    val x2642 = OpDef(op=BitOr, inputs=List(b871, b872)).name("x2642").ctrl(x2653).srcCtx("NestedReductionTest.scala:56:34") // Or(b871,b872)
    val x2643 = OpDef(op=FltAdd, inputs=List(x2631, x2636)).name("x2643").ctrl(x2653).srcCtx("NestedReductionTest.scala:56:36") // FltAdd(x2631,x2636)
    val x2644 = OpDef(op=MuxOp, inputs=List(x2637, x2643, x2631)).name("x2644").ctrl(x2653).srcCtx("NestedReductionTest.scala:56:34") // Mux(x2637,x2643,x2631)
    val x2645 = OpDef(op=BitOr, inputs=List(x2632, x2637)).name("x2645").ctrl(x2653).srcCtx("NestedReductionTest.scala:56:34") // Or(x2632,x2637)
    val x2646 = OpDef(op=FltAdd, inputs=List(x2644, x2641)).name("x2646").ctrl(x2653).srcCtx("NestedReductionTest.scala:56:36") // FltAdd(x2644,x2641)
    val x2647 = OpDef(op=MuxOp, inputs=List(x2642, x2646, x2644)).name("x2647").ctrl(x2653).srcCtx("NestedReductionTest.scala:56:34") // Mux(x2642,x2646,x2644)
    val x2648 = OpDef(op=BitOr, inputs=List(x2645, x2642)).name("x2648").ctrl(x2653).srcCtx("NestedReductionTest.scala:56:34") // Or(x2645,x2642)
    val x2649 = ReadMem(x2377_d1).name("x2649").ctrl(x2653).srcCtx("NestedReductionTest.scala:56:34") // RegRead(x2377)
    val x2650 = OpDef(op=FixEql, inputs=List(b861, Const(0))).name("x2650").ctrl(x2653).srcCtx("NestedReductionTest.scala:56:34") // FixEql(b861,Const(0))
    val x2651 = OpDef(op=FltAdd, inputs=List(x2647, x2649)).name("x2651").ctrl(x2653).srcCtx("NestedReductionTest.scala:56:36") // FltAdd(x2647,x2649)
    val x2652_x2377_d0 = WriteMem(x2377_d0, x2651).name("x2652_x2377_d0").ctrl(x2653).srcCtx("NestedReductionTest.scala:56:34") // RegWrite(x2377,x2651,Const(true))
    val x2652_x2377_d1 = WriteMem(x2377_d1, x2651).name("x2652_x2377_d1").ctrl(x2653).srcCtx("NestedReductionTest.scala:56:34") // RegWrite(x2377,x2651,Const(true))
    val x2657 = UnitController(style=SeqPipe, level=InnerControl).name("x2657").ctrl(x2658).srcCtx("NestedReductionTest.scala:30:30") // UnitPipe(List(Const(true)),Block(Const(())))
    val x2655 = ReadMem(x2377_d0).name("x2655").ctrl(x2657).srcCtx("NestedReductionTest.scala:56:34") // RegRead(x2377)
    val x2656_x2370 = WriteMem(x2370, x2655).name("x2656_x2370").ctrl(x2657).srcCtx("NestedReductionTest.scala:46:35") // RegWrite(x2370,x2655,Const(true))
    val x2662 = UnitController(style=SeqPipe, level=InnerControl).name("x2662").ctrl(x2663).srcCtx("NestedReductionTest.scala:59:30") // UnitPipe(List(Const(true)),Block(x2661))
    val x2659 = ReadMem(x2370).name("x2659").ctrl(x2662).srcCtx("NestedReductionTest.scala:60:47") // RegRead(x2370)
    val x2660 = OpDef(op=FltSub, inputs=List(x2659, Const(3.0))).name("x2660").ctrl(x2662).srcCtx("NestedReductionTest.scala:60:49") // FltSub(x2659,Const(3.0))
    val x2661_x2661_x2367 = WriteMem(x2367, x2660).name("x2661_x2661_x2367").ctrl(x2662).srcCtx("NestedReductionTest.scala:60:44") // StreamWrite(x2367,x2660,Const(true))
    
  }
}
