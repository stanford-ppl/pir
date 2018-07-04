import pir._
import pir.node._
import arch._
import prism.enums._

object DotProduct__N_330301440_ts_65536_op_4 extends PIRApp {
  def main(implicit design:PIRDesign) = {
    import design.pirmeta._
    val x2319 = ArgIn(init=0).name("x2319").ctrl(top).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:19:21:size") // ArgInNew(Const(0))
    isAccum(x2319) = false
    bufferDepthOf(x2319) = 1
    boundOf(x2319) = 330301440
    val x2322 = ReadMem(x2319).name("x2322").ctrl(top).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:22:21") // RegRead(x2319)
    val x2323 = DRAM(dims=List(x2322)).name("x2323").ctrl(top).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:22:20:a") // x2323 = DRAMNew(ArrayBuffer(x2322),Const(0))
    val x2324 = ReadMem(x2319).name("x2324").ctrl(top).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:23:21") // RegRead(x2319)
    val x2325 = DRAM(dims=List(x2324)).name("x2325").ctrl(top).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:23:20:b") // x2325 = DRAMNew(ArrayBuffer(x2324),Const(0))
    val x2326 = ArgOut(init=0).name("x2326").ctrl(top).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:24:21:out") // ArgOutNew(Const(0))
    isAccum(x2326) = false
    bufferDepthOf(x2326) = 1
    val x2585 = UnitController(style=SeqPipe, level=OuterControl).name("x2585").ctrl(top).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:28:11") // Hwblock(Block(Const(())),false)
    val x2329_d0 = Reg(init=Some(0)).name("x2329_d0").ctrl(x2585).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:29:27") // x2329 = RegNew(Const(0))
    isAccum(x2329_d0) = false
    bufferDepthOf(x2329_d0) = 1
    val x2329_d1 = Reg(init=Some(0)).name("x2329_d1").ctrl(x2585).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:29:27") // x2329 = RegNew(Const(0))
    isAccum(x2329_d1) = true
    bufferDepthOf(x2329_d1) = 1
    val x2330 = ReadMem(x2319).name("x2330").ctrl(x2585).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:29:38") // RegRead(x2319)
    val x2331 = Counter(min=Const(0), max=x2330, step=Const(65536), par=4).name("x2331").ctrl(x2585).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:29:49") // CounterNew(Const(0),x2330,Const(65536),Const(4))
    val x2332 = CounterChain(List(x2331)).name("x2332").ctrl(x2585).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:37:8") // CounterChainNew(List(x2331))
    val x2581 = LoopController(style=MetaPipe, level=OuterControl, cchain=x2332).name("x2581").ctrl(x2585).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:37:8") // UnrolledReduce(List(Const(true)),x2332,x2329,Block((x2329) => Const(())),List(List(b856, b857, b858, b859)),List(List(b860, b861, b862, b863)))
    val b856 = CounterIter(x2331, Some(0)).name("b856").ctrl(x2581) // b856
    val b860 = Const(true).name("b860").ctrl(x2581) // b860
    val b857 = CounterIter(x2331, Some(1)).name("b857").ctrl(x2581) // b857
    val b861 = Const(true).name("b861").ctrl(x2581) // b861
    val b858 = CounterIter(x2331, Some(2)).name("b858").ctrl(x2581) // b858
    val b862 = Const(true).name("b862").ctrl(x2581) // b862
    val b859 = CounterIter(x2331, Some(3)).name("b859").ctrl(x2581) // b859
    val b863 = Const(true).name("b863").ctrl(x2581) // b863
    val x2333_d0_b0 = SRAM(size=65536, banking=Strided(banks=16, stride=1)).name("x2333_d0_b0").ctrl(x2581).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:30:27:aBlk") // x2333 = SRAMNew(ArrayBuffer(Const(65536)))
    isAccum(x2333_d0_b0) = false
    bufferDepthOf(x2333_d0_b0) = 2
    val x2334_d0_b0 = SRAM(size=65536, banking=Strided(banks=16, stride=1)).name("x2334_d0_b0").ctrl(x2581).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:30:27:aBlk") // x2334 = SRAMNew(ArrayBuffer(Const(65536)))
    isAccum(x2334_d0_b0) = false
    bufferDepthOf(x2334_d0_b0) = 2
    val x2335_d0_b0 = SRAM(size=65536, banking=Strided(banks=16, stride=1)).name("x2335_d0_b0").ctrl(x2581).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:30:27:aBlk") // x2335 = SRAMNew(ArrayBuffer(Const(65536)))
    isAccum(x2335_d0_b0) = false
    bufferDepthOf(x2335_d0_b0) = 2
    val x2336_d0_b0 = SRAM(size=65536, banking=Strided(banks=16, stride=1)).name("x2336_d0_b0").ctrl(x2581).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:30:27:aBlk") // x2336 = SRAMNew(ArrayBuffer(Const(65536)))
    isAccum(x2336_d0_b0) = false
    bufferDepthOf(x2336_d0_b0) = 2
    val x2337_d0_b0 = SRAM(size=65536, banking=Strided(banks=16, stride=1)).name("x2337_d0_b0").ctrl(x2581).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:31:27:bBlk") // x2337 = SRAMNew(ArrayBuffer(Const(65536)))
    isAccum(x2337_d0_b0) = false
    bufferDepthOf(x2337_d0_b0) = 2
    val x2338_d0_b0 = SRAM(size=65536, banking=Strided(banks=16, stride=1)).name("x2338_d0_b0").ctrl(x2581).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:31:27:bBlk") // x2338 = SRAMNew(ArrayBuffer(Const(65536)))
    isAccum(x2338_d0_b0) = false
    bufferDepthOf(x2338_d0_b0) = 2
    val x2339_d0_b0 = SRAM(size=65536, banking=Strided(banks=16, stride=1)).name("x2339_d0_b0").ctrl(x2581).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:31:27:bBlk") // x2339 = SRAMNew(ArrayBuffer(Const(65536)))
    isAccum(x2339_d0_b0) = false
    bufferDepthOf(x2339_d0_b0) = 2
    val x2340_d0_b0 = SRAM(size=65536, banking=Strided(banks=16, stride=1)).name("x2340_d0_b0").ctrl(x2581).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:31:27:bBlk") // x2340 = SRAMNew(ArrayBuffer(Const(65536)))
    isAccum(x2340_d0_b0) = false
    bufferDepthOf(x2340_d0_b0) = 2
    val x2505 = UnitController(style=ForkJoin, level=OuterControl).name("x2505").ctrl(x2581).srcCtx("UnrollingTransformer.scala:431:43") // ParallelPipe(List(Const(true)),Block(Const(())))
    val x2381 = UnitController(style=ForkJoin, level=OuterControl).name("x2381").ctrl(x2505).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:32:18") // ParallelPipe(List(b860),Block(Const(())))
    val x2342 = UnitController(style=SeqPipe, level=InnerControl).name("x2342").ctrl(x2381).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:32:18") // UnitPipe(List(b860),Block(Const(())))
    val x2341 = OpDef(op=FixAdd, inputs=List(b856, Const(65536))).name("x2341").ctrl(x2342).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:33:27") // FixAdd(b856,Const(65536))
    val x2361 = UnitController(style=StreamPipe, level=OuterControl).name("x2361").ctrl(x2381).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:33:16") // UnitPipe(List(b860),Block(Const(())))
    val b2604 = StreamOut(field="offset").name("b2604").ctrl(x2361).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:33:16") // x2343 = StreamOutNew(BurstCmdBus)
    isAccum(b2604) = false
    bufferDepthOf(b2604) = 1
    val b2605 = StreamOut(field="size").name("b2605").ctrl(x2361).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:33:16") // x2343 = StreamOutNew(BurstCmdBus)
    isAccum(b2605) = false
    bufferDepthOf(b2605) = 1
    val x2344 = StreamIn(field="data").name("x2344").ctrl(x2361).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:33:16") // x2344 = StreamInNew(BurstDataBus())
    isAccum(x2344) = false
    bufferDepthOf(x2344) = 1
    val x2352 = UnitController(style=SeqPipe, level=InnerControl).name("x2352").ctrl(x2361).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:33:16") // UnitPipe(List(b860),Block(x2351))
    val x2345 = b856 // FixConvert(b856,TRUE,_32,_0) (Same Type. No op)
    val x2346 = OpDef(op=FixSla, inputs=List(x2345, Const(2))).name("x2346").ctrl(x2352).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:33:16") // FixLsh(x2345,Const(2))
    val x2347 = x2346 // FixConvert(x2346,TRUE,_64,_0)
    val x2348 = DramAddress(x2323).name("x2348").ctrl(x2352).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:33:16") // GetDRAMAddress(x2323)
    val x2350_x2349 = OpDef(op=FixAdd, inputs=List(x2347, x2348)).name("x2350_x2349").ctrl(x2352).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:33:16") // FixAdd(x2347,x2348)
    // x2350 = SimpleStruct(ArrayBuffer((offset,x2349), (size,Const(262144)), (isLoad,Const(true))))
    val x2351_b2606_b2604 = WriteMem(b2604, x2350_x2349).name("x2351_b2606_b2604").ctrl(x2352).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:33:16") // StreamWrite(x2343,x2350,b860)
    val x2351_b2607_b2605 = WriteMem(b2605, Const(262144)).name("x2351_b2607_b2605").ctrl(x2352).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:33:16") // StreamWrite(x2343,x2350,b860)
    val x2353 = FringeDenseLoad(dram=List(x2323), cmdStream=List(b2604, b2605), dataStream=List(x2344)).name("x2353").ctrl(x2361).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:33:16") // FringeDenseLoad(x2323,x2343,x2344)
    val x2354 = Counter(min=Const(0), max=Const(65536), step=Const(1), par=16).name("x2354").ctrl(x2361).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:33:16") // CounterNew(Const(0),Const(65536),Const(1),Const(16))
    val x2355 = CounterChain(List(x2354)).name("x2355").ctrl(x2361).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:33:16") // CounterChainNew(List(x2354))
    val x2360 = LoopController(style=InnerPipe, level=InnerControl, cchain=x2355).name("x2360").ctrl(x2361).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:33:16") // UnrolledForeach(List(b860),x2355,Block(Const(())),List(List(b887)),List(List(b888)))
    val b887 = CounterIter(x2354, None).name("b887").ctrl(x2360) // b887
    val b888 = Const(true).name("b888").ctrl(x2360) // b888
    val x2356 = OpDef(op=BitAnd, inputs=List(b888, b860)).name("x2356").ctrl(x2360).srcCtx("UnrollingBase.scala:28:66") // And(b888,b860)
    val x2357_x2357 = ReadMem(x2344).name("x2357_x2357").ctrl(x2360).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:33:16") // ParStreamRead(x2344,List(x2356))
    val x2358_x2358 = x2357_x2357 // x2358 = VectorApply(x2357,0)
    val x2359 = StoreBanks(List(x2333_d0_b0), List(b887), x2358_x2358).name("x2359").ctrl(x2360).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:33:16") // ParSRAMStore(x2333,List(List(b887)),List(x2358),List(x2356))
    val x2380 = UnitController(style=StreamPipe, level=OuterControl).name("x2380").ctrl(x2381).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:34:16") // UnitPipe(List(b860),Block(Const(())))
    val b2608 = StreamOut(field="offset").name("b2608").ctrl(x2380).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:34:16") // x2362 = StreamOutNew(BurstCmdBus)
    isAccum(b2608) = false
    bufferDepthOf(b2608) = 1
    val b2609 = StreamOut(field="size").name("b2609").ctrl(x2380).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:34:16") // x2362 = StreamOutNew(BurstCmdBus)
    isAccum(b2609) = false
    bufferDepthOf(b2609) = 1
    val x2363 = StreamIn(field="data").name("x2363").ctrl(x2380).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:34:16") // x2363 = StreamInNew(BurstDataBus())
    isAccum(x2363) = false
    bufferDepthOf(x2363) = 1
    val x2371 = UnitController(style=SeqPipe, level=InnerControl).name("x2371").ctrl(x2380).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:34:16") // UnitPipe(List(b860),Block(x2370))
    val x2364 = b856 // FixConvert(b856,TRUE,_32,_0) (Same Type. No op)
    val x2365 = OpDef(op=FixSla, inputs=List(x2364, Const(2))).name("x2365").ctrl(x2371).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:34:16") // FixLsh(x2364,Const(2))
    val x2366 = x2365 // FixConvert(x2365,TRUE,_64,_0)
    val x2367 = DramAddress(x2325).name("x2367").ctrl(x2371).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:34:16") // GetDRAMAddress(x2325)
    val x2369_x2368 = OpDef(op=FixAdd, inputs=List(x2366, x2367)).name("x2369_x2368").ctrl(x2371).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:34:16") // FixAdd(x2366,x2367)
    // x2369 = SimpleStruct(ArrayBuffer((offset,x2368), (size,Const(262144)), (isLoad,Const(true))))
    val x2370_b2610_b2608 = WriteMem(b2608, x2369_x2368).name("x2370_b2610_b2608").ctrl(x2371).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:34:16") // StreamWrite(x2362,x2369,b860)
    val x2370_b2611_b2609 = WriteMem(b2609, Const(262144)).name("x2370_b2611_b2609").ctrl(x2371).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:34:16") // StreamWrite(x2362,x2369,b860)
    val x2372 = FringeDenseLoad(dram=List(x2325), cmdStream=List(b2608, b2609), dataStream=List(x2363)).name("x2372").ctrl(x2380).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:34:16") // FringeDenseLoad(x2325,x2362,x2363)
    val x2373 = Counter(min=Const(0), max=Const(65536), step=Const(1), par=16).name("x2373").ctrl(x2380).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:34:16") // CounterNew(Const(0),Const(65536),Const(1),Const(16))
    val x2374 = CounterChain(List(x2373)).name("x2374").ctrl(x2380).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:34:16") // CounterChainNew(List(x2373))
    val x2379 = LoopController(style=InnerPipe, level=InnerControl, cchain=x2374).name("x2379").ctrl(x2380).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:34:16") // UnrolledForeach(List(b860),x2374,Block(Const(())),List(List(b908)),List(List(b909)))
    val b908 = CounterIter(x2373, None).name("b908").ctrl(x2379) // b908
    val b909 = Const(true).name("b909").ctrl(x2379) // b909
    val x2375 = OpDef(op=BitAnd, inputs=List(b909, b860)).name("x2375").ctrl(x2379).srcCtx("UnrollingBase.scala:28:66") // And(b909,b860)
    val x2376_x2376 = ReadMem(x2363).name("x2376_x2376").ctrl(x2379).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:34:16") // ParStreamRead(x2363,List(x2375))
    val x2377_x2377 = x2376_x2376 // x2377 = VectorApply(x2376,0)
    val x2378 = StoreBanks(List(x2337_d0_b0), List(b908), x2377_x2377).name("x2378").ctrl(x2379).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:34:16") // ParSRAMStore(x2337,List(List(b908)),List(x2377),List(x2375))
    val x2422 = UnitController(style=ForkJoin, level=OuterControl).name("x2422").ctrl(x2505).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:32:18") // ParallelPipe(List(b861),Block(Const(())))
    val x2383 = UnitController(style=SeqPipe, level=InnerControl).name("x2383").ctrl(x2422).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:32:18") // UnitPipe(List(b861),Block(Const(())))
    val x2382 = OpDef(op=FixAdd, inputs=List(b857, Const(65536))).name("x2382").ctrl(x2383).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:33:27") // FixAdd(b857,Const(65536))
    val x2402 = UnitController(style=StreamPipe, level=OuterControl).name("x2402").ctrl(x2422).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:33:16") // UnitPipe(List(b861),Block(Const(())))
    val b2612 = StreamOut(field="offset").name("b2612").ctrl(x2402).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:33:16") // x2384 = StreamOutNew(BurstCmdBus)
    isAccum(b2612) = false
    bufferDepthOf(b2612) = 1
    val b2613 = StreamOut(field="size").name("b2613").ctrl(x2402).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:33:16") // x2384 = StreamOutNew(BurstCmdBus)
    isAccum(b2613) = false
    bufferDepthOf(b2613) = 1
    val x2385 = StreamIn(field="data").name("x2385").ctrl(x2402).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:33:16") // x2385 = StreamInNew(BurstDataBus())
    isAccum(x2385) = false
    bufferDepthOf(x2385) = 1
    val x2393 = UnitController(style=SeqPipe, level=InnerControl).name("x2393").ctrl(x2402).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:33:16") // UnitPipe(List(b861),Block(x2392))
    val x2386 = b857 // FixConvert(b857,TRUE,_32,_0) (Same Type. No op)
    val x2387 = OpDef(op=FixSla, inputs=List(x2386, Const(2))).name("x2387").ctrl(x2393).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:33:16") // FixLsh(x2386,Const(2))
    val x2388 = x2387 // FixConvert(x2387,TRUE,_64,_0)
    val x2389 = DramAddress(x2323).name("x2389").ctrl(x2393).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:33:16") // GetDRAMAddress(x2323)
    val x2391_x2390 = OpDef(op=FixAdd, inputs=List(x2388, x2389)).name("x2391_x2390").ctrl(x2393).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:33:16") // FixAdd(x2388,x2389)
    // x2391 = SimpleStruct(ArrayBuffer((offset,x2390), (size,Const(262144)), (isLoad,Const(true))))
    val x2392_b2614_b2612 = WriteMem(b2612, x2391_x2390).name("x2392_b2614_b2612").ctrl(x2393).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:33:16") // StreamWrite(x2384,x2391,b861)
    val x2392_b2615_b2613 = WriteMem(b2613, Const(262144)).name("x2392_b2615_b2613").ctrl(x2393).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:33:16") // StreamWrite(x2384,x2391,b861)
    val x2394 = FringeDenseLoad(dram=List(x2323), cmdStream=List(b2612, b2613), dataStream=List(x2385)).name("x2394").ctrl(x2402).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:33:16") // FringeDenseLoad(x2323,x2384,x2385)
    val x2395 = Counter(min=Const(0), max=Const(65536), step=Const(1), par=16).name("x2395").ctrl(x2402).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:33:16") // CounterNew(Const(0),Const(65536),Const(1),Const(16))
    val x2396 = CounterChain(List(x2395)).name("x2396").ctrl(x2402).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:33:16") // CounterChainNew(List(x2395))
    val x2401 = LoopController(style=InnerPipe, level=InnerControl, cchain=x2396).name("x2401").ctrl(x2402).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:33:16") // UnrolledForeach(List(b861),x2396,Block(Const(())),List(List(b932)),List(List(b933)))
    val b932 = CounterIter(x2395, None).name("b932").ctrl(x2401) // b932
    val b933 = Const(true).name("b933").ctrl(x2401) // b933
    val x2397 = OpDef(op=BitAnd, inputs=List(b933, b861)).name("x2397").ctrl(x2401).srcCtx("UnrollingBase.scala:28:66") // And(b933,b861)
    val x2398_x2398 = ReadMem(x2385).name("x2398_x2398").ctrl(x2401).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:33:16") // ParStreamRead(x2385,List(x2397))
    val x2399_x2399 = x2398_x2398 // x2399 = VectorApply(x2398,0)
    val x2400 = StoreBanks(List(x2334_d0_b0), List(b932), x2399_x2399).name("x2400").ctrl(x2401).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:33:16") // ParSRAMStore(x2334,List(List(b932)),List(x2399),List(x2397))
    val x2421 = UnitController(style=StreamPipe, level=OuterControl).name("x2421").ctrl(x2422).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:34:16") // UnitPipe(List(b861),Block(Const(())))
    val b2616 = StreamOut(field="offset").name("b2616").ctrl(x2421).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:34:16") // x2403 = StreamOutNew(BurstCmdBus)
    isAccum(b2616) = false
    bufferDepthOf(b2616) = 1
    val b2617 = StreamOut(field="size").name("b2617").ctrl(x2421).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:34:16") // x2403 = StreamOutNew(BurstCmdBus)
    isAccum(b2617) = false
    bufferDepthOf(b2617) = 1
    val x2404 = StreamIn(field="data").name("x2404").ctrl(x2421).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:34:16") // x2404 = StreamInNew(BurstDataBus())
    isAccum(x2404) = false
    bufferDepthOf(x2404) = 1
    val x2412 = UnitController(style=SeqPipe, level=InnerControl).name("x2412").ctrl(x2421).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:34:16") // UnitPipe(List(b861),Block(x2411))
    val x2405 = b857 // FixConvert(b857,TRUE,_32,_0) (Same Type. No op)
    val x2406 = OpDef(op=FixSla, inputs=List(x2405, Const(2))).name("x2406").ctrl(x2412).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:34:16") // FixLsh(x2405,Const(2))
    val x2407 = x2406 // FixConvert(x2406,TRUE,_64,_0)
    val x2408 = DramAddress(x2325).name("x2408").ctrl(x2412).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:34:16") // GetDRAMAddress(x2325)
    val x2410_x2409 = OpDef(op=FixAdd, inputs=List(x2407, x2408)).name("x2410_x2409").ctrl(x2412).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:34:16") // FixAdd(x2407,x2408)
    // x2410 = SimpleStruct(ArrayBuffer((offset,x2409), (size,Const(262144)), (isLoad,Const(true))))
    val x2411_b2618_b2616 = WriteMem(b2616, x2410_x2409).name("x2411_b2618_b2616").ctrl(x2412).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:34:16") // StreamWrite(x2403,x2410,b861)
    val x2411_b2619_b2617 = WriteMem(b2617, Const(262144)).name("x2411_b2619_b2617").ctrl(x2412).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:34:16") // StreamWrite(x2403,x2410,b861)
    val x2413 = FringeDenseLoad(dram=List(x2325), cmdStream=List(b2616, b2617), dataStream=List(x2404)).name("x2413").ctrl(x2421).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:34:16") // FringeDenseLoad(x2325,x2403,x2404)
    val x2414 = Counter(min=Const(0), max=Const(65536), step=Const(1), par=16).name("x2414").ctrl(x2421).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:34:16") // CounterNew(Const(0),Const(65536),Const(1),Const(16))
    val x2415 = CounterChain(List(x2414)).name("x2415").ctrl(x2421).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:34:16") // CounterChainNew(List(x2414))
    val x2420 = LoopController(style=InnerPipe, level=InnerControl, cchain=x2415).name("x2420").ctrl(x2421).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:34:16") // UnrolledForeach(List(b861),x2415,Block(Const(())),List(List(b953)),List(List(b954)))
    val b953 = CounterIter(x2414, None).name("b953").ctrl(x2420) // b953
    val b954 = Const(true).name("b954").ctrl(x2420) // b954
    val x2416 = OpDef(op=BitAnd, inputs=List(b954, b861)).name("x2416").ctrl(x2420).srcCtx("UnrollingBase.scala:28:66") // And(b954,b861)
    val x2417_x2417 = ReadMem(x2404).name("x2417_x2417").ctrl(x2420).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:34:16") // ParStreamRead(x2404,List(x2416))
    val x2418_x2418 = x2417_x2417 // x2418 = VectorApply(x2417,0)
    val x2419 = StoreBanks(List(x2338_d0_b0), List(b953), x2418_x2418).name("x2419").ctrl(x2420).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:34:16") // ParSRAMStore(x2338,List(List(b953)),List(x2418),List(x2416))
    val x2463 = UnitController(style=ForkJoin, level=OuterControl).name("x2463").ctrl(x2505).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:32:18") // ParallelPipe(List(b862),Block(Const(())))
    val x2424 = UnitController(style=SeqPipe, level=InnerControl).name("x2424").ctrl(x2463).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:32:18") // UnitPipe(List(b862),Block(Const(())))
    val x2423 = OpDef(op=FixAdd, inputs=List(b858, Const(65536))).name("x2423").ctrl(x2424).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:33:27") // FixAdd(b858,Const(65536))
    val x2443 = UnitController(style=StreamPipe, level=OuterControl).name("x2443").ctrl(x2463).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:33:16") // UnitPipe(List(b862),Block(Const(())))
    val b2620 = StreamOut(field="offset").name("b2620").ctrl(x2443).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:33:16") // x2425 = StreamOutNew(BurstCmdBus)
    isAccum(b2620) = false
    bufferDepthOf(b2620) = 1
    val b2621 = StreamOut(field="size").name("b2621").ctrl(x2443).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:33:16") // x2425 = StreamOutNew(BurstCmdBus)
    isAccum(b2621) = false
    bufferDepthOf(b2621) = 1
    val x2426 = StreamIn(field="data").name("x2426").ctrl(x2443).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:33:16") // x2426 = StreamInNew(BurstDataBus())
    isAccum(x2426) = false
    bufferDepthOf(x2426) = 1
    val x2434 = UnitController(style=SeqPipe, level=InnerControl).name("x2434").ctrl(x2443).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:33:16") // UnitPipe(List(b862),Block(x2433))
    val x2427 = b858 // FixConvert(b858,TRUE,_32,_0) (Same Type. No op)
    val x2428 = OpDef(op=FixSla, inputs=List(x2427, Const(2))).name("x2428").ctrl(x2434).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:33:16") // FixLsh(x2427,Const(2))
    val x2429 = x2428 // FixConvert(x2428,TRUE,_64,_0)
    val x2430 = DramAddress(x2323).name("x2430").ctrl(x2434).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:33:16") // GetDRAMAddress(x2323)
    val x2432_x2431 = OpDef(op=FixAdd, inputs=List(x2429, x2430)).name("x2432_x2431").ctrl(x2434).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:33:16") // FixAdd(x2429,x2430)
    // x2432 = SimpleStruct(ArrayBuffer((offset,x2431), (size,Const(262144)), (isLoad,Const(true))))
    val x2433_b2622_b2620 = WriteMem(b2620, x2432_x2431).name("x2433_b2622_b2620").ctrl(x2434).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:33:16") // StreamWrite(x2425,x2432,b862)
    val x2433_b2623_b2621 = WriteMem(b2621, Const(262144)).name("x2433_b2623_b2621").ctrl(x2434).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:33:16") // StreamWrite(x2425,x2432,b862)
    val x2435 = FringeDenseLoad(dram=List(x2323), cmdStream=List(b2620, b2621), dataStream=List(x2426)).name("x2435").ctrl(x2443).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:33:16") // FringeDenseLoad(x2323,x2425,x2426)
    val x2436 = Counter(min=Const(0), max=Const(65536), step=Const(1), par=16).name("x2436").ctrl(x2443).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:33:16") // CounterNew(Const(0),Const(65536),Const(1),Const(16))
    val x2437 = CounterChain(List(x2436)).name("x2437").ctrl(x2443).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:33:16") // CounterChainNew(List(x2436))
    val x2442 = LoopController(style=InnerPipe, level=InnerControl, cchain=x2437).name("x2442").ctrl(x2443).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:33:16") // UnrolledForeach(List(b862),x2437,Block(Const(())),List(List(b977)),List(List(b978)))
    val b977 = CounterIter(x2436, None).name("b977").ctrl(x2442) // b977
    val b978 = Const(true).name("b978").ctrl(x2442) // b978
    val x2438 = OpDef(op=BitAnd, inputs=List(b978, b862)).name("x2438").ctrl(x2442).srcCtx("UnrollingBase.scala:28:66") // And(b978,b862)
    val x2439_x2439 = ReadMem(x2426).name("x2439_x2439").ctrl(x2442).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:33:16") // ParStreamRead(x2426,List(x2438))
    val x2440_x2440 = x2439_x2439 // x2440 = VectorApply(x2439,0)
    val x2441 = StoreBanks(List(x2335_d0_b0), List(b977), x2440_x2440).name("x2441").ctrl(x2442).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:33:16") // ParSRAMStore(x2335,List(List(b977)),List(x2440),List(x2438))
    val x2462 = UnitController(style=StreamPipe, level=OuterControl).name("x2462").ctrl(x2463).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:34:16") // UnitPipe(List(b862),Block(Const(())))
    val b2624 = StreamOut(field="offset").name("b2624").ctrl(x2462).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:34:16") // x2444 = StreamOutNew(BurstCmdBus)
    isAccum(b2624) = false
    bufferDepthOf(b2624) = 1
    val b2625 = StreamOut(field="size").name("b2625").ctrl(x2462).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:34:16") // x2444 = StreamOutNew(BurstCmdBus)
    isAccum(b2625) = false
    bufferDepthOf(b2625) = 1
    val x2445 = StreamIn(field="data").name("x2445").ctrl(x2462).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:34:16") // x2445 = StreamInNew(BurstDataBus())
    isAccum(x2445) = false
    bufferDepthOf(x2445) = 1
    val x2453 = UnitController(style=SeqPipe, level=InnerControl).name("x2453").ctrl(x2462).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:34:16") // UnitPipe(List(b862),Block(x2452))
    val x2446 = b858 // FixConvert(b858,TRUE,_32,_0) (Same Type. No op)
    val x2447 = OpDef(op=FixSla, inputs=List(x2446, Const(2))).name("x2447").ctrl(x2453).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:34:16") // FixLsh(x2446,Const(2))
    val x2448 = x2447 // FixConvert(x2447,TRUE,_64,_0)
    val x2449 = DramAddress(x2325).name("x2449").ctrl(x2453).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:34:16") // GetDRAMAddress(x2325)
    val x2451_x2450 = OpDef(op=FixAdd, inputs=List(x2448, x2449)).name("x2451_x2450").ctrl(x2453).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:34:16") // FixAdd(x2448,x2449)
    // x2451 = SimpleStruct(ArrayBuffer((offset,x2450), (size,Const(262144)), (isLoad,Const(true))))
    val x2452_b2626_b2624 = WriteMem(b2624, x2451_x2450).name("x2452_b2626_b2624").ctrl(x2453).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:34:16") // StreamWrite(x2444,x2451,b862)
    val x2452_b2627_b2625 = WriteMem(b2625, Const(262144)).name("x2452_b2627_b2625").ctrl(x2453).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:34:16") // StreamWrite(x2444,x2451,b862)
    val x2454 = FringeDenseLoad(dram=List(x2325), cmdStream=List(b2624, b2625), dataStream=List(x2445)).name("x2454").ctrl(x2462).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:34:16") // FringeDenseLoad(x2325,x2444,x2445)
    val x2455 = Counter(min=Const(0), max=Const(65536), step=Const(1), par=16).name("x2455").ctrl(x2462).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:34:16") // CounterNew(Const(0),Const(65536),Const(1),Const(16))
    val x2456 = CounterChain(List(x2455)).name("x2456").ctrl(x2462).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:34:16") // CounterChainNew(List(x2455))
    val x2461 = LoopController(style=InnerPipe, level=InnerControl, cchain=x2456).name("x2461").ctrl(x2462).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:34:16") // UnrolledForeach(List(b862),x2456,Block(Const(())),List(List(b998)),List(List(b999)))
    val b998 = CounterIter(x2455, None).name("b998").ctrl(x2461) // b998
    val b999 = Const(true).name("b999").ctrl(x2461) // b999
    val x2457 = OpDef(op=BitAnd, inputs=List(b999, b862)).name("x2457").ctrl(x2461).srcCtx("UnrollingBase.scala:28:66") // And(b999,b862)
    val x2458_x2458 = ReadMem(x2445).name("x2458_x2458").ctrl(x2461).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:34:16") // ParStreamRead(x2445,List(x2457))
    val x2459_x2459 = x2458_x2458 // x2459 = VectorApply(x2458,0)
    val x2460 = StoreBanks(List(x2339_d0_b0), List(b998), x2459_x2459).name("x2460").ctrl(x2461).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:34:16") // ParSRAMStore(x2339,List(List(b998)),List(x2459),List(x2457))
    val x2504 = UnitController(style=ForkJoin, level=OuterControl).name("x2504").ctrl(x2505).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:32:18") // ParallelPipe(List(b863),Block(Const(())))
    val x2465 = UnitController(style=SeqPipe, level=InnerControl).name("x2465").ctrl(x2504).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:32:18") // UnitPipe(List(b863),Block(Const(())))
    val x2464 = OpDef(op=FixAdd, inputs=List(b859, Const(65536))).name("x2464").ctrl(x2465).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:33:27") // FixAdd(b859,Const(65536))
    val x2484 = UnitController(style=StreamPipe, level=OuterControl).name("x2484").ctrl(x2504).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:33:16") // UnitPipe(List(b863),Block(Const(())))
    val b2628 = StreamOut(field="offset").name("b2628").ctrl(x2484).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:33:16") // x2466 = StreamOutNew(BurstCmdBus)
    isAccum(b2628) = false
    bufferDepthOf(b2628) = 1
    val b2629 = StreamOut(field="size").name("b2629").ctrl(x2484).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:33:16") // x2466 = StreamOutNew(BurstCmdBus)
    isAccum(b2629) = false
    bufferDepthOf(b2629) = 1
    val x2467 = StreamIn(field="data").name("x2467").ctrl(x2484).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:33:16") // x2467 = StreamInNew(BurstDataBus())
    isAccum(x2467) = false
    bufferDepthOf(x2467) = 1
    val x2475 = UnitController(style=SeqPipe, level=InnerControl).name("x2475").ctrl(x2484).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:33:16") // UnitPipe(List(b863),Block(x2474))
    val x2468 = b859 // FixConvert(b859,TRUE,_32,_0) (Same Type. No op)
    val x2469 = OpDef(op=FixSla, inputs=List(x2468, Const(2))).name("x2469").ctrl(x2475).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:33:16") // FixLsh(x2468,Const(2))
    val x2470 = x2469 // FixConvert(x2469,TRUE,_64,_0)
    val x2471 = DramAddress(x2323).name("x2471").ctrl(x2475).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:33:16") // GetDRAMAddress(x2323)
    val x2473_x2472 = OpDef(op=FixAdd, inputs=List(x2470, x2471)).name("x2473_x2472").ctrl(x2475).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:33:16") // FixAdd(x2470,x2471)
    // x2473 = SimpleStruct(ArrayBuffer((offset,x2472), (size,Const(262144)), (isLoad,Const(true))))
    val x2474_b2630_b2628 = WriteMem(b2628, x2473_x2472).name("x2474_b2630_b2628").ctrl(x2475).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:33:16") // StreamWrite(x2466,x2473,b863)
    val x2474_b2631_b2629 = WriteMem(b2629, Const(262144)).name("x2474_b2631_b2629").ctrl(x2475).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:33:16") // StreamWrite(x2466,x2473,b863)
    val x2476 = FringeDenseLoad(dram=List(x2323), cmdStream=List(b2628, b2629), dataStream=List(x2467)).name("x2476").ctrl(x2484).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:33:16") // FringeDenseLoad(x2323,x2466,x2467)
    val x2477 = Counter(min=Const(0), max=Const(65536), step=Const(1), par=16).name("x2477").ctrl(x2484).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:33:16") // CounterNew(Const(0),Const(65536),Const(1),Const(16))
    val x2478 = CounterChain(List(x2477)).name("x2478").ctrl(x2484).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:33:16") // CounterChainNew(List(x2477))
    val x2483 = LoopController(style=InnerPipe, level=InnerControl, cchain=x2478).name("x2483").ctrl(x2484).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:33:16") // UnrolledForeach(List(b863),x2478,Block(Const(())),List(List(b1022)),List(List(b1023)))
    val b1022 = CounterIter(x2477, None).name("b1022").ctrl(x2483) // b1022
    val b1023 = Const(true).name("b1023").ctrl(x2483) // b1023
    val x2479 = OpDef(op=BitAnd, inputs=List(b1023, b863)).name("x2479").ctrl(x2483).srcCtx("UnrollingBase.scala:28:66") // And(b1023,b863)
    val x2480_x2480 = ReadMem(x2467).name("x2480_x2480").ctrl(x2483).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:33:16") // ParStreamRead(x2467,List(x2479))
    val x2481_x2481 = x2480_x2480 // x2481 = VectorApply(x2480,0)
    val x2482 = StoreBanks(List(x2336_d0_b0), List(b1022), x2481_x2481).name("x2482").ctrl(x2483).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:33:16") // ParSRAMStore(x2336,List(List(b1022)),List(x2481),List(x2479))
    val x2503 = UnitController(style=StreamPipe, level=OuterControl).name("x2503").ctrl(x2504).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:34:16") // UnitPipe(List(b863),Block(Const(())))
    val b2632 = StreamOut(field="offset").name("b2632").ctrl(x2503).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:34:16") // x2485 = StreamOutNew(BurstCmdBus)
    isAccum(b2632) = false
    bufferDepthOf(b2632) = 1
    val b2633 = StreamOut(field="size").name("b2633").ctrl(x2503).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:34:16") // x2485 = StreamOutNew(BurstCmdBus)
    isAccum(b2633) = false
    bufferDepthOf(b2633) = 1
    val x2486 = StreamIn(field="data").name("x2486").ctrl(x2503).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:34:16") // x2486 = StreamInNew(BurstDataBus())
    isAccum(x2486) = false
    bufferDepthOf(x2486) = 1
    val x2494 = UnitController(style=SeqPipe, level=InnerControl).name("x2494").ctrl(x2503).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:34:16") // UnitPipe(List(b863),Block(x2493))
    val x2487 = b859 // FixConvert(b859,TRUE,_32,_0) (Same Type. No op)
    val x2488 = OpDef(op=FixSla, inputs=List(x2487, Const(2))).name("x2488").ctrl(x2494).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:34:16") // FixLsh(x2487,Const(2))
    val x2489 = x2488 // FixConvert(x2488,TRUE,_64,_0)
    val x2490 = DramAddress(x2325).name("x2490").ctrl(x2494).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:34:16") // GetDRAMAddress(x2325)
    val x2492_x2491 = OpDef(op=FixAdd, inputs=List(x2489, x2490)).name("x2492_x2491").ctrl(x2494).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:34:16") // FixAdd(x2489,x2490)
    // x2492 = SimpleStruct(ArrayBuffer((offset,x2491), (size,Const(262144)), (isLoad,Const(true))))
    val x2493_b2634_b2632 = WriteMem(b2632, x2492_x2491).name("x2493_b2634_b2632").ctrl(x2494).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:34:16") // StreamWrite(x2485,x2492,b863)
    val x2493_b2635_b2633 = WriteMem(b2633, Const(262144)).name("x2493_b2635_b2633").ctrl(x2494).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:34:16") // StreamWrite(x2485,x2492,b863)
    val x2495 = FringeDenseLoad(dram=List(x2325), cmdStream=List(b2632, b2633), dataStream=List(x2486)).name("x2495").ctrl(x2503).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:34:16") // FringeDenseLoad(x2325,x2485,x2486)
    val x2496 = Counter(min=Const(0), max=Const(65536), step=Const(1), par=16).name("x2496").ctrl(x2503).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:34:16") // CounterNew(Const(0),Const(65536),Const(1),Const(16))
    val x2497 = CounterChain(List(x2496)).name("x2497").ctrl(x2503).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:34:16") // CounterChainNew(List(x2496))
    val x2502 = LoopController(style=InnerPipe, level=InnerControl, cchain=x2497).name("x2502").ctrl(x2503).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:34:16") // UnrolledForeach(List(b863),x2497,Block(Const(())),List(List(b1043)),List(List(b1044)))
    val b1043 = CounterIter(x2496, None).name("b1043").ctrl(x2502) // b1043
    val b1044 = Const(true).name("b1044").ctrl(x2502) // b1044
    val x2498 = OpDef(op=BitAnd, inputs=List(b1044, b863)).name("x2498").ctrl(x2502).srcCtx("UnrollingBase.scala:28:66") // And(b1044,b863)
    val x2499_x2499 = ReadMem(x2486).name("x2499_x2499").ctrl(x2502).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:34:16") // ParStreamRead(x2486,List(x2498))
    val x2500_x2500 = x2499_x2499 // x2500 = VectorApply(x2499,0)
    val x2501 = StoreBanks(List(x2340_d0_b0), List(b1043), x2500_x2500).name("x2501").ctrl(x2502).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:34:16") // ParSRAMStore(x2340,List(List(b1043)),List(x2500),List(x2498))
    val x2506_d0 = Reg(init=Some(0)).name("x2506_d0").ctrl(x2581).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:36:22") // x2506 = RegNew(Const(0))
    isAccum(x2506_d0) = false
    bufferDepthOf(x2506_d0) = 2
    val x2506_d1 = Reg(init=Some(0)).name("x2506_d1").ctrl(x2581).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:36:22") // x2506 = RegNew(Const(0))
    isAccum(x2506_d1) = true
    bufferDepthOf(x2506_d1) = 1
    val x2507_d0 = Reg(init=Some(0)).name("x2507_d0").ctrl(x2581).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:36:22") // x2507 = RegNew(Const(0))
    isAccum(x2507_d0) = false
    bufferDepthOf(x2507_d0) = 2
    val x2507_d1 = Reg(init=Some(0)).name("x2507_d1").ctrl(x2581).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:36:22") // x2507 = RegNew(Const(0))
    isAccum(x2507_d1) = true
    bufferDepthOf(x2507_d1) = 1
    val x2508_d0 = Reg(init=Some(0)).name("x2508_d0").ctrl(x2581).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:36:22") // x2508 = RegNew(Const(0))
    isAccum(x2508_d0) = false
    bufferDepthOf(x2508_d0) = 2
    val x2508_d1 = Reg(init=Some(0)).name("x2508_d1").ctrl(x2581).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:36:22") // x2508 = RegNew(Const(0))
    isAccum(x2508_d1) = true
    bufferDepthOf(x2508_d1) = 1
    val x2509_d0 = Reg(init=Some(0)).name("x2509_d0").ctrl(x2581).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:36:22") // x2509 = RegNew(Const(0))
    isAccum(x2509_d0) = false
    bufferDepthOf(x2509_d0) = 2
    val x2509_d1 = Reg(init=Some(0)).name("x2509_d1").ctrl(x2581).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:36:22") // x2509 = RegNew(Const(0))
    isAccum(x2509_d1) = true
    bufferDepthOf(x2509_d1) = 1
    val x2562 = UnitController(style=ForkJoin, level=OuterControl).name("x2562").ctrl(x2581).srcCtx("UnrollingTransformer.scala:431:43") // ParallelPipe(List(Const(true)),Block(Const(())))
    val x2510 = Counter(min=Const(0), max=Const(65536), step=Const(1), par=16).name("x2510").ctrl(x2562).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:36:36") // CounterNew(Const(0),Const(65536),Const(1),Const(16))
    val x2511 = CounterChain(List(x2510)).name("x2511").ctrl(x2562).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:36:71") // CounterChainNew(List(x2510))
    val x2522 = LoopController(style=InnerPipe, level=InnerControl, cchain=x2511).name("x2522").ctrl(x2562).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:36:71") // UnrolledReduce(List(b860),x2511,x2506,Block((x2506) => Const(())),List(List(b1065)),List(List(b1066)))
    val b1065 = CounterIter(x2510, None).name("b1065").ctrl(x2522) // b1065
    val b1066 = Const(true).name("b1066").ctrl(x2522) // b1066
    val x2512 = OpDef(op=BitAnd, inputs=List(b1066, b860)).name("x2512").ctrl(x2522).srcCtx("UnrollingBase.scala:28:66") // And(b1066,b860)
    val x2513 = LoadBanks(List(x2333_d0_b0), List(b1065)).name("x2513").ctrl(x2522).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:36:54") // ParSRAMLoad(x2333,List(List(b1065)),List(x2512))
    val x2514 = x2513 // x2514 = VectorApply(x2513,0)
    val x2515 = LoadBanks(List(x2337_d0_b0), List(b1065)).name("x2515").ctrl(x2522).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:36:65") // ParSRAMLoad(x2337,List(List(b1065)),List(x2512))
    val x2516 = x2515 // x2516 = VectorApply(x2515,0)
    val x2517 = OpDef(op=FixMul, inputs=List(x2514, x2516)).name("x2517").ctrl(x2522).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:36:59") // FixMul(x2514,x2516)
    val x2518 = ReadMem(x2506_d1).name("x2518").ctrl(x2522).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:36:71") // RegRead(x2506)
    val x2519 = OpDef(op=FixEql, inputs=List(b1065, Const(0))).name("x2519").ctrl(x2522).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:36:71") // FixEql(b1065,Const(0))
    val x2520 = ReduceAccumOp(op=FixAdd, input=x2517, accum=x2518).name("x2520").ctrl(x2522).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:36:73") // FixAdd(x2517,x2518)
    val x2521_x2506_d0 = WriteMem(x2506_d0, x2520).name("x2521_x2506_d0").ctrl(x2522).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:36:71") // RegWrite(x2506,x2520,b860)
    val x2521_x2506_d1 = WriteMem(x2506_d1, x2520).name("x2521_x2506_d1").ctrl(x2522).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:36:71") // RegWrite(x2506,x2520,b860)
    val x2523 = Counter(min=Const(0), max=Const(65536), step=Const(1), par=16).name("x2523").ctrl(x2562).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:36:36") // CounterNew(Const(0),Const(65536),Const(1),Const(16))
    val x2524 = CounterChain(List(x2523)).name("x2524").ctrl(x2562).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:36:71") // CounterChainNew(List(x2523))
    val x2535 = LoopController(style=InnerPipe, level=InnerControl, cchain=x2524).name("x2535").ctrl(x2562).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:36:71") // UnrolledReduce(List(b861),x2524,x2507,Block((x2507) => Const(())),List(List(b1078)),List(List(b1079)))
    val b1078 = CounterIter(x2523, None).name("b1078").ctrl(x2535) // b1078
    val b1079 = Const(true).name("b1079").ctrl(x2535) // b1079
    val x2525 = OpDef(op=BitAnd, inputs=List(b1079, b861)).name("x2525").ctrl(x2535).srcCtx("UnrollingBase.scala:28:66") // And(b1079,b861)
    val x2526 = LoadBanks(List(x2334_d0_b0), List(b1078)).name("x2526").ctrl(x2535).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:36:54") // ParSRAMLoad(x2334,List(List(b1078)),List(x2525))
    val x2527 = x2526 // x2527 = VectorApply(x2526,0)
    val x2528 = LoadBanks(List(x2338_d0_b0), List(b1078)).name("x2528").ctrl(x2535).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:36:65") // ParSRAMLoad(x2338,List(List(b1078)),List(x2525))
    val x2529 = x2528 // x2529 = VectorApply(x2528,0)
    val x2530 = OpDef(op=FixMul, inputs=List(x2527, x2529)).name("x2530").ctrl(x2535).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:36:59") // FixMul(x2527,x2529)
    val x2531 = ReadMem(x2507_d1).name("x2531").ctrl(x2535).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:36:71") // RegRead(x2507)
    val x2532 = OpDef(op=FixEql, inputs=List(b1078, Const(0))).name("x2532").ctrl(x2535).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:36:71") // FixEql(b1078,Const(0))
    val x2533 = ReduceAccumOp(op=FixAdd, input=x2530, accum=x2531).name("x2533").ctrl(x2535).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:36:73") // FixAdd(x2530,x2531)
    val x2534_x2507_d0 = WriteMem(x2507_d0, x2533).name("x2534_x2507_d0").ctrl(x2535).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:36:71") // RegWrite(x2507,x2533,b861)
    val x2534_x2507_d1 = WriteMem(x2507_d1, x2533).name("x2534_x2507_d1").ctrl(x2535).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:36:71") // RegWrite(x2507,x2533,b861)
    val x2536 = Counter(min=Const(0), max=Const(65536), step=Const(1), par=16).name("x2536").ctrl(x2562).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:36:36") // CounterNew(Const(0),Const(65536),Const(1),Const(16))
    val x2537 = CounterChain(List(x2536)).name("x2537").ctrl(x2562).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:36:71") // CounterChainNew(List(x2536))
    val x2548 = LoopController(style=InnerPipe, level=InnerControl, cchain=x2537).name("x2548").ctrl(x2562).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:36:71") // UnrolledReduce(List(b862),x2537,x2508,Block((x2508) => Const(())),List(List(b1091)),List(List(b1092)))
    val b1091 = CounterIter(x2536, None).name("b1091").ctrl(x2548) // b1091
    val b1092 = Const(true).name("b1092").ctrl(x2548) // b1092
    val x2538 = OpDef(op=BitAnd, inputs=List(b1092, b862)).name("x2538").ctrl(x2548).srcCtx("UnrollingBase.scala:28:66") // And(b1092,b862)
    val x2539 = LoadBanks(List(x2335_d0_b0), List(b1091)).name("x2539").ctrl(x2548).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:36:54") // ParSRAMLoad(x2335,List(List(b1091)),List(x2538))
    val x2540 = x2539 // x2540 = VectorApply(x2539,0)
    val x2541 = LoadBanks(List(x2339_d0_b0), List(b1091)).name("x2541").ctrl(x2548).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:36:65") // ParSRAMLoad(x2339,List(List(b1091)),List(x2538))
    val x2542 = x2541 // x2542 = VectorApply(x2541,0)
    val x2543 = OpDef(op=FixMul, inputs=List(x2540, x2542)).name("x2543").ctrl(x2548).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:36:59") // FixMul(x2540,x2542)
    val x2544 = ReadMem(x2508_d1).name("x2544").ctrl(x2548).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:36:71") // RegRead(x2508)
    val x2545 = OpDef(op=FixEql, inputs=List(b1091, Const(0))).name("x2545").ctrl(x2548).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:36:71") // FixEql(b1091,Const(0))
    val x2546 = ReduceAccumOp(op=FixAdd, input=x2543, accum=x2544).name("x2546").ctrl(x2548).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:36:73") // FixAdd(x2543,x2544)
    val x2547_x2508_d0 = WriteMem(x2508_d0, x2546).name("x2547_x2508_d0").ctrl(x2548).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:36:71") // RegWrite(x2508,x2546,b862)
    val x2547_x2508_d1 = WriteMem(x2508_d1, x2546).name("x2547_x2508_d1").ctrl(x2548).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:36:71") // RegWrite(x2508,x2546,b862)
    val x2549 = Counter(min=Const(0), max=Const(65536), step=Const(1), par=16).name("x2549").ctrl(x2562).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:36:36") // CounterNew(Const(0),Const(65536),Const(1),Const(16))
    val x2550 = CounterChain(List(x2549)).name("x2550").ctrl(x2562).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:36:71") // CounterChainNew(List(x2549))
    val x2561 = LoopController(style=InnerPipe, level=InnerControl, cchain=x2550).name("x2561").ctrl(x2562).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:36:71") // UnrolledReduce(List(b863),x2550,x2509,Block((x2509) => Const(())),List(List(b1104)),List(List(b1105)))
    val b1104 = CounterIter(x2549, None).name("b1104").ctrl(x2561) // b1104
    val b1105 = Const(true).name("b1105").ctrl(x2561) // b1105
    val x2551 = OpDef(op=BitAnd, inputs=List(b1105, b863)).name("x2551").ctrl(x2561).srcCtx("UnrollingBase.scala:28:66") // And(b1105,b863)
    val x2552 = LoadBanks(List(x2336_d0_b0), List(b1104)).name("x2552").ctrl(x2561).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:36:54") // ParSRAMLoad(x2336,List(List(b1104)),List(x2551))
    val x2553 = x2552 // x2553 = VectorApply(x2552,0)
    val x2554 = LoadBanks(List(x2340_d0_b0), List(b1104)).name("x2554").ctrl(x2561).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:36:65") // ParSRAMLoad(x2340,List(List(b1104)),List(x2551))
    val x2555 = x2554 // x2555 = VectorApply(x2554,0)
    val x2556 = OpDef(op=FixMul, inputs=List(x2553, x2555)).name("x2556").ctrl(x2561).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:36:59") // FixMul(x2553,x2555)
    val x2557 = ReadMem(x2509_d1).name("x2557").ctrl(x2561).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:36:71") // RegRead(x2509)
    val x2558 = OpDef(op=FixEql, inputs=List(b1104, Const(0))).name("x2558").ctrl(x2561).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:36:71") // FixEql(b1104,Const(0))
    val x2559 = ReduceAccumOp(op=FixAdd, input=x2556, accum=x2557).name("x2559").ctrl(x2561).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:36:73") // FixAdd(x2556,x2557)
    val x2560_x2509_d0 = WriteMem(x2509_d0, x2559).name("x2560_x2509_d0").ctrl(x2561).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:36:71") // RegWrite(x2509,x2559,b863)
    val x2560_x2509_d1 = WriteMem(x2509_d1, x2559).name("x2560_x2509_d1").ctrl(x2561).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:36:71") // RegWrite(x2509,x2559,b863)
    val x2580 = UnitController(style=SeqPipe, level=InnerControl).name("x2580").ctrl(x2581).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:37:8") // UnitPipe(List(Const(true)),Block(x2579))
    val x2563 = ReadMem(x2507_d0).name("x2563").ctrl(x2580).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:36:71") // RegRead(x2507)
    val x2564 = ReadMem(x2506_d0).name("x2564").ctrl(x2580).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:36:71") // RegRead(x2506)
    val x2565 = OpDef(op=FixAdd, inputs=List(x2564, x2563)).name("x2565").ctrl(x2580).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:37:10") // FixAdd(x2564,x2563)
    val x2566 = OpDef(op=MuxOp, inputs=List(b861, x2565, x2564)).name("x2566").ctrl(x2580).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:37:8") // Mux(b861,x2565,x2564)
    val x2567 = OpDef(op=BitOr, inputs=List(b860, b861)).name("x2567").ctrl(x2580).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:37:8") // Or(b860,b861)
    val x2568 = ReadMem(x2509_d0).name("x2568").ctrl(x2580).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:36:71") // RegRead(x2509)
    val x2569 = ReadMem(x2508_d0).name("x2569").ctrl(x2580).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:36:71") // RegRead(x2508)
    val x2570 = OpDef(op=FixAdd, inputs=List(x2569, x2568)).name("x2570").ctrl(x2580).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:37:10") // FixAdd(x2569,x2568)
    val x2571 = OpDef(op=MuxOp, inputs=List(b863, x2570, x2569)).name("x2571").ctrl(x2580).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:37:8") // Mux(b863,x2570,x2569)
    val x2572 = OpDef(op=BitOr, inputs=List(b862, b863)).name("x2572").ctrl(x2580).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:37:8") // Or(b862,b863)
    val x2573 = OpDef(op=FixAdd, inputs=List(x2566, x2571)).name("x2573").ctrl(x2580).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:37:10") // FixAdd(x2566,x2571)
    val x2574 = OpDef(op=MuxOp, inputs=List(x2572, x2573, x2566)).name("x2574").ctrl(x2580).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:37:8") // Mux(x2572,x2573,x2566)
    val x2575 = OpDef(op=BitOr, inputs=List(x2567, x2572)).name("x2575").ctrl(x2580).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:37:8") // Or(x2567,x2572)
    val x2576 = ReadMem(x2329_d1).name("x2576").ctrl(x2580).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:37:8") // RegRead(x2329)
    val x2577 = OpDef(op=FixEql, inputs=List(b856, Const(0))).name("x2577").ctrl(x2580).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:37:8") // FixEql(b856,Const(0))
    val x2578 = OpDef(op=FixAdd, inputs=List(x2574, x2576)).name("x2578").ctrl(x2580).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:37:10") // FixAdd(x2574,x2576)
    val x2579_x2329_d0 = WriteMem(x2329_d0, x2578).name("x2579_x2329_d0").ctrl(x2580).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:37:8") // RegWrite(x2329,x2578,Const(true))
    val x2579_x2329_d1 = WriteMem(x2329_d1, x2578).name("x2579_x2329_d1").ctrl(x2580).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:37:8") // RegWrite(x2329,x2578,Const(true))
    val x2584 = UnitController(style=SeqPipe, level=InnerControl).name("x2584").ctrl(x2585).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:28:11") // UnitPipe(List(Const(true)),Block(Const(())))
    val x2582 = ReadMem(x2329_d0).name("x2582").ctrl(x2584).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:37:8") // RegRead(x2329)
    val x2583_x2326 = WriteMem(x2326, x2582).name("x2583_x2326").ctrl(x2584).srcCtx("DotProduct__N_330301440_ts_65536_op_4.scala:29:11") // RegWrite(x2326,x2582,Const(true))
    
  }
}
