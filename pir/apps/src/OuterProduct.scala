import pir._
import pir.node._
import arch._
import pirc.enums._

object OuterProduct extends PIRApp {
  def main(top:Top) = {
      import top.metadata._
    val x2320 = top.argIn(init=0).name("x2320").ctrl(top) // ArgInNew(Const(0))
    val x2321_d0 = top.argIn(init=0).name("x2321_d0").ctrl(top) // ArgInNew(Const(0))
    val x2324 = ReadMem(x2320).name("x2324").ctrl(top) // RegRead(x2320)
    val x2325 = DRAM().name("x2325").ctrl(top) // x2325 = DRAMNew(ArrayBuffer(x2324),Const(0))
    val x2326 = ReadMem(x2321_d0).name("x2326").ctrl(top) // RegRead(x2321)
    val x2327 = DRAM().name("x2327").ctrl(top) // x2327 = DRAMNew(ArrayBuffer(x2326),Const(0))
    val x2328 = ReadMem(x2321_d0).name("x2328").ctrl(top) // RegRead(x2321)
    val x2329 = ReadMem(x2320).name("x2329").ctrl(top) // RegRead(x2320)
    val x2330 = DRAM().name("x2330").ctrl(top) // x2330 = DRAMNew(ArrayBuffer(x2329, x2328),Const(0))
    val x2545 = UnitController(style=SeqPipe, level=OuterControl).name("x2545").ctrl(top) // Hwblock(Block(Const(())),false)
    val x2333 = ReadMem(x2321_d0).name("x2333").ctrl(x2545) // RegRead(x2321)
    val x2334 = Counter(min=Const(0).ctrl(x2545), max=x2333, step=Const(16).ctrl(x2545), par=2).name("x2334").ctrl(x2545) // CounterNew(Const(0),x2333,Const(16),Const(2))
    val x2335 = ReadMem(x2320).name("x2335").ctrl(x2545) // RegRead(x2320)
    val x2336 = Counter(min=Const(0).ctrl(x2545), max=x2335, step=Const(16).ctrl(x2545), par=1).name("x2336").ctrl(x2545) // CounterNew(Const(0),x2335,Const(16),Const(1))
    val x2337 = CounterChain(List(x2336,x2334)).name("x2337").ctrl(x2545) // CounterChainNew(List(x2336, x2334))
    val x2544 = LoopController(style=MetaPipe, level=OuterControl, cchain=x2337).name("x2544").ctrl(x2545) // UnrolledForeach(List(Const(true)),x2337,Block(Const(())),List(List(b1049), List(b1050, b1051)),List(List(b1052), List(b1053, b1054)))
    val b1049 = CounterIter(x2336, Some(0)).ctrl(x2544).name("b1049")
    val b1052 = DummyOp().ctrl(x2544).name("b1052")
    val b1050 = CounterIter(x2334, Some(0)).ctrl(x2544).name("b1050")
    val b1053 = DummyOp().ctrl(x2544).name("b1053")
    val b1051 = CounterIter(x2334, Some(1)).ctrl(x2544).name("b1051")
    val b1054 = DummyOp().ctrl(x2544).name("b1054")
    val x2338_d0_b0 = SRAM(size=1, banking=Strided(banks=16, stride=1)).name("x2338_d0_b0").ctrl(x2544) // x2338 = SRAMNew(ArrayBuffer(Const(16)))
    isAccum(x2338_d0_b0) = false
    val x2339_d0_b0 = SRAM(size=1, banking=Strided(banks=16, stride=1)).name("x2339_d0_b0").ctrl(x2544) // x2339 = SRAMNew(ArrayBuffer(Const(16)))
    isAccum(x2339_d0_b0) = false
    val x2340_d0_b0 = SRAM(size=1, banking=Strided(banks=16, stride=1)).name("x2340_d0_b0").ctrl(x2544) // x2340 = SRAMNew(ArrayBuffer(Const(16)))
    isAccum(x2340_d0_b0) = false
    val x2341_d0_b0 = SRAM(size=1, banking=Strided(banks=16, stride=1)).name("x2341_d0_b0").ctrl(x2544) // x2341 = SRAMNew(ArrayBuffer(Const(16)))
    isAccum(x2341_d0_b0) = false
    val x2342_d0_b0 = SRAM(size=16, banking=Strided(banks=16, stride=1)).name("x2342_d0_b0").ctrl(x2544) // x2342 = SRAMNew(ArrayBuffer(Const(16), Const(16)))
    isAccum(x2342_d0_b0) = false
    val x2343_d0_b0 = SRAM(size=16, banking=Strided(banks=16, stride=1)).name("x2343_d0_b0").ctrl(x2544) // x2343 = SRAMNew(ArrayBuffer(Const(16), Const(16)))
    isAccum(x2343_d0_b0) = false
    val x2345 = UnitController(style=SeqPipe, level=InnerControl).name("x2345").ctrl(x2544) // UnitPipe(List(b1052, b1053),Block(Const(())))
    val x2344 = OpDef(op=FixAdd, inputs=List(b1049, Const(16))).name("x2344").ctrl(x2345) // FixAdd(b1049,Const(16))
    val x2367 = UnitController(style=StreamPipe, level=OuterControl).name("x2367").ctrl(x2544) // UnitPipe(List(b1052, b1053),Block(Const(())))
    val b2562 = StreamOut(field="offset").name("b2562").ctrl(x2367) // x2346 = StreamOutNew(BurstCmdBus)
    val b2563 = StreamOut(field="size").name("b2563").ctrl(x2367) // x2346 = StreamOutNew(BurstCmdBus)
    val x2347 = StreamIn(field="data").name("x2347").ctrl(x2367) // x2347 = StreamInNew(BurstDataBus())
    val x2357 = UnitController(style=SeqPipe, level=InnerControl).name("x2357").ctrl(x2367) // UnitPipe(List(b1052, b1053),Block(x2356))
    val x2348 = b1049 // FixConvert(b1049,TRUE,_32,_0)
    val x2349 = OpDef(op=FixSla, inputs=List(x2348, Const(2))).name("x2349").ctrl(x2357) // FixLsh(x2348,Const(2))
    val x2350 = x2349 // FixConvert(x2349,TRUE,_64,_0)
    val x2351 = top.dramAddress(x2325).name("x2351").ctrl(x2357) // GetDRAMAddress(x2325)
    val x2352 = OpDef(op=FixAdd, inputs=List(x2350, x2351)).name("x2352").ctrl(x2357) // FixAdd(x2350,x2351)
    val x2353 = x2352 // FixConvert(x2352,TRUE,_64,_0)
    // x2354 = SimpleStruct(ArrayBuffer((offset,x2353), (size,Const(64)), (isLoad,Const(true))))
    val x2355 = OpDef(op=BitAnd, inputs=List(b1052, b1053)).name("x2355").ctrl(x2357) // And(b1052,b1053)
    val b2564_b2562 = WriteMem(b2562, x2353).name("b2564_b2562").ctrl(x2357) // StreamWrite(x2346,x2354,x2355)
    val b2565_b2563 = WriteMem(b2563, Const(64)).name("b2565_b2563").ctrl(x2357) // StreamWrite(x2346,x2354,x2355)
    val x2358 = FringeContainer(x2325,b2562,b2563,x2347).name("x2358").ctrl(x2367) // FringeDenseLoad(x2325,x2346,x2347)
    val x2359 = Counter(min=Const(0).ctrl(x2367), max=Const(16).ctrl(x2367), step=Const(1).ctrl(x2367), par=16).name("x2359").ctrl(x2367) // CounterNew(Const(0),Const(16),Const(1),Const(16))
    val x2360 = CounterChain(List(x2359)).name("x2360").ctrl(x2367) // CounterChainNew(List(x2359))
    val x2366 = LoopController(style=InnerPipe, level=InnerControl, cchain=x2360).name("x2366").ctrl(x2367) // UnrolledForeach(List(b1052, b1053),x2360,Block(Const(())),List(List(b1078)),List(List(b1079)))
    val b1078 = CounterIter(x2359, None).ctrl(x2366).name("b1078")
    val b1079 = DummyOp().ctrl(x2366).name("b1079")
    val x2361 = OpDef(op=BitAnd, inputs=List(b1079, b1052)).name("x2361").ctrl(x2366) // And(b1079,b1052)
    val x2362 = OpDef(op=BitAnd, inputs=List(x2361, b1053)).name("x2362").ctrl(x2366) // And(x2361,b1053)
    val x2363 = ReadMem(x2347).name("x2363").ctrl(x2366) // ParStreamRead(x2347,List(x2362))
    val x2364 = x2363 // x2364 = VectorApply(x2363,0)
    val x2365 = StoreBanks(List(x2338_d0_b0), List(b1078), x2364).name("x2365").ctrl(x2366) // ParSRAMStore(x2338,List(List(b1078)),List(x2364),List(x2362))
    val x2369 = UnitController(style=SeqPipe, level=InnerControl).name("x2369").ctrl(x2544) // UnitPipe(List(b1052, b1053),Block(Const(())))
    val x2368 = OpDef(op=FixAdd, inputs=List(b1050, Const(16))).name("x2368").ctrl(x2369) // FixAdd(b1050,Const(16))
    val x2391 = UnitController(style=StreamPipe, level=OuterControl).name("x2391").ctrl(x2544) // UnitPipe(List(b1052, b1053),Block(Const(())))
    val b2566 = StreamOut(field="offset").name("b2566").ctrl(x2391) // x2370 = StreamOutNew(BurstCmdBus)
    val b2567 = StreamOut(field="size").name("b2567").ctrl(x2391) // x2370 = StreamOutNew(BurstCmdBus)
    val x2371 = StreamIn(field="data").name("x2371").ctrl(x2391) // x2371 = StreamInNew(BurstDataBus())
    val x2381 = UnitController(style=SeqPipe, level=InnerControl).name("x2381").ctrl(x2391) // UnitPipe(List(b1052, b1053),Block(x2380))
    val x2372 = b1050 // FixConvert(b1050,TRUE,_32,_0)
    val x2373 = OpDef(op=FixSla, inputs=List(x2372, Const(2))).name("x2373").ctrl(x2381) // FixLsh(x2372,Const(2))
    val x2374 = x2373 // FixConvert(x2373,TRUE,_64,_0)
    val x2375 = top.dramAddress(x2327).name("x2375").ctrl(x2381) // GetDRAMAddress(x2327)
    val x2376 = OpDef(op=FixAdd, inputs=List(x2374, x2375)).name("x2376").ctrl(x2381) // FixAdd(x2374,x2375)
    val x2377 = x2376 // FixConvert(x2376,TRUE,_64,_0)
    // x2378 = SimpleStruct(ArrayBuffer((offset,x2377), (size,Const(64)), (isLoad,Const(true))))
    val x2379 = OpDef(op=BitAnd, inputs=List(b1052, b1053)).name("x2379").ctrl(x2381) // And(b1052,b1053)
    val b2568_b2566 = WriteMem(b2566, x2377).name("b2568_b2566").ctrl(x2381) // StreamWrite(x2370,x2378,x2379)
    val b2569_b2567 = WriteMem(b2567, Const(64)).name("b2569_b2567").ctrl(x2381) // StreamWrite(x2370,x2378,x2379)
    val x2382 = FringeContainer(x2327,b2566,b2567,x2371).name("x2382").ctrl(x2391) // FringeDenseLoad(x2327,x2370,x2371)
    val x2383 = Counter(min=Const(0).ctrl(x2391), max=Const(16).ctrl(x2391), step=Const(1).ctrl(x2391), par=16).name("x2383").ctrl(x2391) // CounterNew(Const(0),Const(16),Const(1),Const(16))
    val x2384 = CounterChain(List(x2383)).name("x2384").ctrl(x2391) // CounterChainNew(List(x2383))
    val x2390 = LoopController(style=InnerPipe, level=InnerControl, cchain=x2384).name("x2390").ctrl(x2391) // UnrolledForeach(List(b1052, b1053),x2384,Block(Const(())),List(List(b1104)),List(List(b1105)))
    val b1104 = CounterIter(x2383, None).ctrl(x2390).name("b1104")
    val b1105 = DummyOp().ctrl(x2390).name("b1105")
    val x2385 = OpDef(op=BitAnd, inputs=List(b1105, b1052)).name("x2385").ctrl(x2390) // And(b1105,b1052)
    val x2386 = OpDef(op=BitAnd, inputs=List(x2385, b1053)).name("x2386").ctrl(x2390) // And(x2385,b1053)
    val x2387 = ReadMem(x2371).name("x2387").ctrl(x2390) // ParStreamRead(x2371,List(x2386))
    val x2388 = x2387 // x2388 = VectorApply(x2387,0)
    val x2389 = StoreBanks(List(x2340_d0_b0), List(b1104), x2388).name("x2389").ctrl(x2390) // ParSRAMStore(x2340,List(List(b1104)),List(x2388),List(x2386))
    val x2393 = UnitController(style=SeqPipe, level=InnerControl).name("x2393").ctrl(x2544) // UnitPipe(List(b1052, b1054),Block(Const(())))
    val x2392 = OpDef(op=FixAdd, inputs=List(b1049, Const(16))).name("x2392").ctrl(x2393) // FixAdd(b1049,Const(16))
    val x2415 = UnitController(style=StreamPipe, level=OuterControl).name("x2415").ctrl(x2544) // UnitPipe(List(b1052, b1054),Block(Const(())))
    val b2570 = StreamOut(field="offset").name("b2570").ctrl(x2415) // x2394 = StreamOutNew(BurstCmdBus)
    val b2571 = StreamOut(field="size").name("b2571").ctrl(x2415) // x2394 = StreamOutNew(BurstCmdBus)
    val x2395 = StreamIn(field="data").name("x2395").ctrl(x2415) // x2395 = StreamInNew(BurstDataBus())
    val x2405 = UnitController(style=SeqPipe, level=InnerControl).name("x2405").ctrl(x2415) // UnitPipe(List(b1052, b1054),Block(x2404))
    val x2396 = b1049 // FixConvert(b1049,TRUE,_32,_0)
    val x2397 = OpDef(op=FixSla, inputs=List(x2396, Const(2))).name("x2397").ctrl(x2405) // FixLsh(x2396,Const(2))
    val x2398 = x2397 // FixConvert(x2397,TRUE,_64,_0)
    val x2399 = top.dramAddress(x2325).name("x2399").ctrl(x2405) // GetDRAMAddress(x2325)
    val x2400 = OpDef(op=FixAdd, inputs=List(x2398, x2399)).name("x2400").ctrl(x2405) // FixAdd(x2398,x2399)
    val x2401 = x2400 // FixConvert(x2400,TRUE,_64,_0)
    // x2402 = SimpleStruct(ArrayBuffer((offset,x2401), (size,Const(64)), (isLoad,Const(true))))
    val x2403 = OpDef(op=BitAnd, inputs=List(b1052, b1054)).name("x2403").ctrl(x2405) // And(b1052,b1054)
    val b2572_b2570 = WriteMem(b2570, x2401).name("b2572_b2570").ctrl(x2405) // StreamWrite(x2394,x2402,x2403)
    val b2573_b2571 = WriteMem(b2571, Const(64)).name("b2573_b2571").ctrl(x2405) // StreamWrite(x2394,x2402,x2403)
    val x2406 = FringeContainer(x2325,b2570,b2571,x2395).name("x2406").ctrl(x2415) // FringeDenseLoad(x2325,x2394,x2395)
    val x2407 = Counter(min=Const(0).ctrl(x2415), max=Const(16).ctrl(x2415), step=Const(1).ctrl(x2415), par=16).name("x2407").ctrl(x2415) // CounterNew(Const(0),Const(16),Const(1),Const(16))
    val x2408 = CounterChain(List(x2407)).name("x2408").ctrl(x2415) // CounterChainNew(List(x2407))
    val x2414 = LoopController(style=InnerPipe, level=InnerControl, cchain=x2408).name("x2414").ctrl(x2415) // UnrolledForeach(List(b1052, b1054),x2408,Block(Const(())),List(List(b1131)),List(List(b1132)))
    val b1131 = CounterIter(x2407, None).ctrl(x2414).name("b1131")
    val b1132 = DummyOp().ctrl(x2414).name("b1132")
    val x2409 = OpDef(op=BitAnd, inputs=List(b1132, b1052)).name("x2409").ctrl(x2414) // And(b1132,b1052)
    val x2410 = OpDef(op=BitAnd, inputs=List(x2409, b1054)).name("x2410").ctrl(x2414) // And(x2409,b1054)
    val x2411 = ReadMem(x2395).name("x2411").ctrl(x2414) // ParStreamRead(x2395,List(x2410))
    val x2412 = x2411 // x2412 = VectorApply(x2411,0)
    val x2413 = StoreBanks(List(x2339_d0_b0), List(b1131), x2412).name("x2413").ctrl(x2414) // ParSRAMStore(x2339,List(List(b1131)),List(x2412),List(x2410))
    val x2417 = UnitController(style=SeqPipe, level=InnerControl).name("x2417").ctrl(x2544) // UnitPipe(List(b1052, b1054),Block(Const(())))
    val x2416 = OpDef(op=FixAdd, inputs=List(b1051, Const(16))).name("x2416").ctrl(x2417) // FixAdd(b1051,Const(16))
    val x2439 = UnitController(style=StreamPipe, level=OuterControl).name("x2439").ctrl(x2544) // UnitPipe(List(b1052, b1054),Block(Const(())))
    val b2574 = StreamOut(field="offset").name("b2574").ctrl(x2439) // x2418 = StreamOutNew(BurstCmdBus)
    val b2575 = StreamOut(field="size").name("b2575").ctrl(x2439) // x2418 = StreamOutNew(BurstCmdBus)
    val x2419 = StreamIn(field="data").name("x2419").ctrl(x2439) // x2419 = StreamInNew(BurstDataBus())
    val x2429 = UnitController(style=SeqPipe, level=InnerControl).name("x2429").ctrl(x2439) // UnitPipe(List(b1052, b1054),Block(x2428))
    val x2420 = b1051 // FixConvert(b1051,TRUE,_32,_0)
    val x2421 = OpDef(op=FixSla, inputs=List(x2420, Const(2))).name("x2421").ctrl(x2429) // FixLsh(x2420,Const(2))
    val x2422 = x2421 // FixConvert(x2421,TRUE,_64,_0)
    val x2423 = top.dramAddress(x2327).name("x2423").ctrl(x2429) // GetDRAMAddress(x2327)
    val x2424 = OpDef(op=FixAdd, inputs=List(x2422, x2423)).name("x2424").ctrl(x2429) // FixAdd(x2422,x2423)
    val x2425 = x2424 // FixConvert(x2424,TRUE,_64,_0)
    // x2426 = SimpleStruct(ArrayBuffer((offset,x2425), (size,Const(64)), (isLoad,Const(true))))
    val x2427 = OpDef(op=BitAnd, inputs=List(b1052, b1054)).name("x2427").ctrl(x2429) // And(b1052,b1054)
    val b2576_b2574 = WriteMem(b2574, x2425).name("b2576_b2574").ctrl(x2429) // StreamWrite(x2418,x2426,x2427)
    val b2577_b2575 = WriteMem(b2575, Const(64)).name("b2577_b2575").ctrl(x2429) // StreamWrite(x2418,x2426,x2427)
    val x2430 = FringeContainer(x2327,b2574,b2575,x2419).name("x2430").ctrl(x2439) // FringeDenseLoad(x2327,x2418,x2419)
    val x2431 = Counter(min=Const(0).ctrl(x2439), max=Const(16).ctrl(x2439), step=Const(1).ctrl(x2439), par=16).name("x2431").ctrl(x2439) // CounterNew(Const(0),Const(16),Const(1),Const(16))
    val x2432 = CounterChain(List(x2431)).name("x2432").ctrl(x2439) // CounterChainNew(List(x2431))
    val x2438 = LoopController(style=InnerPipe, level=InnerControl, cchain=x2432).name("x2438").ctrl(x2439) // UnrolledForeach(List(b1052, b1054),x2432,Block(Const(())),List(List(b1157)),List(List(b1158)))
    val b1157 = CounterIter(x2431, None).ctrl(x2438).name("b1157")
    val b1158 = DummyOp().ctrl(x2438).name("b1158")
    val x2433 = OpDef(op=BitAnd, inputs=List(b1158, b1052)).name("x2433").ctrl(x2438) // And(b1158,b1052)
    val x2434 = OpDef(op=BitAnd, inputs=List(x2433, b1054)).name("x2434").ctrl(x2438) // And(x2433,b1054)
    val x2435 = ReadMem(x2419).name("x2435").ctrl(x2438) // ParStreamRead(x2419,List(x2434))
    val x2436 = x2435 // x2436 = VectorApply(x2435,0)
    val x2437 = StoreBanks(List(x2341_d0_b0), List(b1157), x2436).name("x2437").ctrl(x2438) // ParSRAMStore(x2341,List(List(b1157)),List(x2436),List(x2434))
    val x2440 = Counter(min=Const(0).ctrl(x2544), max=Const(16).ctrl(x2544), step=Const(1).ctrl(x2544), par=16).name("x2440").ctrl(x2544) // CounterNew(Const(0),Const(16),Const(1),Const(16))
    val x2441 = Counter(min=Const(0).ctrl(x2544), max=Const(16).ctrl(x2544), step=Const(1).ctrl(x2544), par=1).name("x2441").ctrl(x2544) // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x2442 = CounterChain(List(x2441,x2440)).name("x2442").ctrl(x2544) // CounterChainNew(List(x2441, x2440))
    val x2451 = LoopController(style=InnerPipe, level=InnerControl, cchain=x2442).name("x2451").ctrl(x2544) // UnrolledForeach(List(b1052, b1053),x2442,Block(Const(())),List(List(b1174), List(b1175)),List(List(b1176), List(b1177)))
    val b1174 = CounterIter(x2441, Some(0)).ctrl(x2451).name("b1174")
    val b1176 = DummyOp().ctrl(x2451).name("b1176")
    val b1175 = CounterIter(x2440, None).ctrl(x2451).name("b1175")
    val b1177 = DummyOp().ctrl(x2451).name("b1177")
    val x2443 = OpDef(op=BitAnd, inputs=List(b1176, b1177)).name("x2443").ctrl(x2451) // And(b1176,b1177)
    val x2444 = OpDef(op=BitAnd, inputs=List(b1052, b1053)).name("x2444").ctrl(x2451) // And(b1052,b1053)
    val x2445 = OpDef(op=BitAnd, inputs=List(x2443, x2444)).name("x2445").ctrl(x2451) // And(x2443,x2444)
    val x2446 = LoadBanks(List(x2338_d0_b0), List(b1174)).name("x2446").ctrl(x2451) // SRAMLoad(x2338,ArrayBuffer(Const(16)),List(b1174),Const(0),x2445)
    val x2447 = LoadBanks(List(x2340_d0_b0), List(b1175)).name("x2447").ctrl(x2451) // ParSRAMLoad(x2340,List(List(b1175)),List(x2445))
    val x2448 = x2447 // x2448 = VectorApply(x2447,0)
    val x2449 = OpDef(op=FixMul, inputs=List(x2446, x2448)).name("x2449").ctrl(x2451) // FixMul(x2446,x2448)
    val x2450 = StoreBanks(List(x2342_d0_b0), List(b1174, b1175), x2449).name("x2450").ctrl(x2451) // ParSRAMStore(x2342,List(List(b1174, b1175)),List(x2449),List(x2445))
    val x2452 = Counter(min=Const(0).ctrl(x2544), max=Const(16).ctrl(x2544), step=Const(1).ctrl(x2544), par=16).name("x2452").ctrl(x2544) // CounterNew(Const(0),Const(16),Const(1),Const(16))
    val x2453 = Counter(min=Const(0).ctrl(x2544), max=Const(16).ctrl(x2544), step=Const(1).ctrl(x2544), par=1).name("x2453").ctrl(x2544) // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x2454 = CounterChain(List(x2453,x2452)).name("x2454").ctrl(x2544) // CounterChainNew(List(x2453, x2452))
    val x2463 = LoopController(style=InnerPipe, level=InnerControl, cchain=x2454).name("x2463").ctrl(x2544) // UnrolledForeach(List(b1052, b1054),x2454,Block(Const(())),List(List(b1187), List(b1188)),List(List(b1189), List(b1190)))
    val b1187 = CounterIter(x2453, Some(0)).ctrl(x2463).name("b1187")
    val b1189 = DummyOp().ctrl(x2463).name("b1189")
    val b1188 = CounterIter(x2452, None).ctrl(x2463).name("b1188")
    val b1190 = DummyOp().ctrl(x2463).name("b1190")
    val x2455 = OpDef(op=BitAnd, inputs=List(b1189, b1190)).name("x2455").ctrl(x2463) // And(b1189,b1190)
    val x2456 = OpDef(op=BitAnd, inputs=List(b1052, b1054)).name("x2456").ctrl(x2463) // And(b1052,b1054)
    val x2457 = OpDef(op=BitAnd, inputs=List(x2455, x2456)).name("x2457").ctrl(x2463) // And(x2455,x2456)
    val x2458 = LoadBanks(List(x2339_d0_b0), List(b1187)).name("x2458").ctrl(x2463) // SRAMLoad(x2339,ArrayBuffer(Const(16)),List(b1187),Const(0),x2457)
    val x2459 = LoadBanks(List(x2341_d0_b0), List(b1188)).name("x2459").ctrl(x2463) // ParSRAMLoad(x2341,List(List(b1188)),List(x2457))
    val x2460 = x2459 // x2460 = VectorApply(x2459,0)
    val x2461 = OpDef(op=FixMul, inputs=List(x2458, x2460)).name("x2461").ctrl(x2463) // FixMul(x2458,x2460)
    val x2462 = StoreBanks(List(x2343_d0_b0), List(b1187, b1188), x2461).name("x2462").ctrl(x2463) // ParSRAMStore(x2343,List(List(b1187, b1188)),List(x2461),List(x2457))
    val x2466 = UnitController(style=SeqPipe, level=InnerControl).name("x2466").ctrl(x2544) // UnitPipe(List(b1052, b1053),Block(Const(())))
    val x2464 = OpDef(op=FixAdd, inputs=List(b1049, Const(16))).name("x2464").ctrl(x2466) // FixAdd(b1049,Const(16))
    val x2465 = OpDef(op=FixAdd, inputs=List(b1050, Const(16))).name("x2465").ctrl(x2466) // FixAdd(b1050,Const(16))
    val x2469 = UnitController(style=SeqPipe, level=InnerControl).name("x2469").ctrl(x2544) // UnitPipe(List(b1052, b1054),Block(Const(())))
    val x2467 = OpDef(op=FixAdd, inputs=List(b1049, Const(16))).name("x2467").ctrl(x2469) // FixAdd(b1049,Const(16))
    val x2468 = OpDef(op=FixAdd, inputs=List(b1051, Const(16))).name("x2468").ctrl(x2469) // FixAdd(b1051,Const(16))
    val x2470 = Counter(min=Const(0).ctrl(x2544), max=Const(16).ctrl(x2544), step=Const(1).ctrl(x2544), par=1).name("x2470").ctrl(x2544) // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x2471 = CounterChain(List(x2470)).name("x2471").ctrl(x2544) // CounterChainNew(List(x2470))
    val x2506 = LoopController(style=StreamPipe, level=OuterControl, cchain=x2471).name("x2506").ctrl(x2544) // UnrolledForeach(List(b1052, b1053),x2471,Block(Const(())),List(List(b1212)),List(List(b1213)))
    val b1212 = CounterIter(x2470, Some(0)).ctrl(x2506).name("b1212")
    val b1213 = DummyOp().ctrl(x2506).name("b1213")
    val b2578 = StreamOut(field="offset").name("b2578").ctrl(x2506) // x2472 = StreamOutNew(BurstCmdBus)
    val b2579 = StreamOut(field="size").name("b2579").ctrl(x2506) // x2472 = StreamOutNew(BurstCmdBus)
    val x2473 = StreamOut(field="data").name("x2473").ctrl(x2506) // x2473 = StreamOutNew(BurstFullDataBus())
    val x2474 = StreamIn(field="ack").name("x2474").ctrl(x2506) // x2474 = StreamInNew(BurstAckBus)
    val x2490 = UnitController(style=SeqPipe, level=InnerControl).name("x2490").ctrl(x2506) // UnitPipe(List(b1213, b1052, b1053),Block(x2489))
    val x2475 = OpDef(op=FixAdd, inputs=List(b1049, b1212)).name("x2475").ctrl(x2490) // FixAdd(b1049,b1212)
    val x2476 = x2475 // FixConvert(x2475,TRUE,_32,_0)
    val x2477 = ReadMem(x2321_d0).name("x2477").ctrl(x2490) // RegRead(x2321)
    val x2478 = OpDef(op=FixMul, inputs=List(x2476, x2477)).name("x2478").ctrl(x2490) // FixMul(x2476,x2477)
    val x2479 = b1050 // FixConvert(b1050,TRUE,_32,_0)
    val x2480 = OpDef(op=FixAdd, inputs=List(x2478, x2479)).name("x2480").ctrl(x2490) // FixAdd(x2478,x2479)
    val x2481 = OpDef(op=FixSla, inputs=List(x2480, Const(2))).name("x2481").ctrl(x2490) // FixLsh(x2480,Const(2))
    val x2482 = x2481 // FixConvert(x2481,TRUE,_64,_0)
    val x2483 = top.dramAddress(x2330).name("x2483").ctrl(x2490) // GetDRAMAddress(x2330)
    val x2484 = OpDef(op=FixAdd, inputs=List(x2482, x2483)).name("x2484").ctrl(x2490) // FixAdd(x2482,x2483)
    val x2485 = x2484 // FixConvert(x2484,TRUE,_64,_0)
    // x2486 = SimpleStruct(ArrayBuffer((offset,x2485), (size,Const(64)), (isLoad,Const(false))))
    val x2487 = OpDef(op=BitAnd, inputs=List(b1213, b1052)).name("x2487").ctrl(x2490) // And(b1213,b1052)
    val x2488 = OpDef(op=BitAnd, inputs=List(x2487, b1053)).name("x2488").ctrl(x2490) // And(x2487,b1053)
    val b2580_b2578 = WriteMem(b2578, x2485).name("b2580_b2578").ctrl(x2490) // StreamWrite(x2472,x2486,x2488)
    val b2581_b2579 = WriteMem(b2579, Const(64)).name("b2581_b2579").ctrl(x2490) // StreamWrite(x2472,x2486,x2488)
    val x2491 = Counter(min=Const(0).ctrl(x2506), max=Const(16).ctrl(x2506), step=Const(1).ctrl(x2506), par=16).name("x2491").ctrl(x2506) // CounterNew(Const(0),Const(16),Const(1),Const(16))
    val x2492 = CounterChain(List(x2491)).name("x2492").ctrl(x2506) // CounterChainNew(List(x2491))
    val x2500 = LoopController(style=InnerPipe, level=InnerControl, cchain=x2492).name("x2500").ctrl(x2506) // UnrolledForeach(List(b1213, b1052, b1053),x2492,Block(Const(())),List(List(b1235)),List(List(b1236)))
    val b1235 = CounterIter(x2491, None).ctrl(x2500).name("b1235")
    val b1236 = DummyOp().ctrl(x2500).name("b1236")
    val x2493 = OpDef(op=BitAnd, inputs=List(b1236, b1213)).name("x2493").ctrl(x2500) // And(b1236,b1213)
    val x2494 = OpDef(op=BitAnd, inputs=List(b1052, b1053)).name("x2494").ctrl(x2500) // And(b1052,b1053)
    val x2495 = OpDef(op=BitAnd, inputs=List(x2493, x2494)).name("x2495").ctrl(x2500) // And(x2493,x2494)
    val x2496 = LoadBanks(List(x2342_d0_b0), List(b1212, b1235)).name("x2496").ctrl(x2500) // ParSRAMLoad(x2342,List(List(b1212, b1235)),List(x2495))
    val x2497 = x2496 // x2497 = VectorApply(x2496,0)
    // x2498 = SimpleStruct(ArrayBuffer((_1,x2497), (_2,Const(true))))
    val x2499_x2473 = WriteMem(x2473, x2497).name("x2499_x2473").ctrl(x2500) // ParStreamWrite(x2473,List(x2498),List(x2495))
    val x2501 = FringeContainer(x2330,b2578,b2579,x2473,x2474).name("x2501").ctrl(x2506) // FringeDenseStore(x2330,x2472,x2473,x2474)
    val x2505 = UnitController(style=SeqPipe, level=InnerControl).name("x2505").ctrl(x2506) // UnitPipe(List(b1213, b1052, b1053),Block(Const(())))
    val x2502 = OpDef(op=BitAnd, inputs=List(b1213, b1052)).name("x2502").ctrl(x2505) // And(b1213,b1052)
    val x2503 = OpDef(op=BitAnd, inputs=List(x2502, b1053)).name("x2503").ctrl(x2505) // And(x2502,b1053)
    val x2504 = ReadMem(x2474).name("x2504").ctrl(x2505) // StreamRead(x2474,x2503)
    val x2507 = Counter(min=Const(0).ctrl(x2544), max=Const(16).ctrl(x2544), step=Const(1).ctrl(x2544), par=1).name("x2507").ctrl(x2544) // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x2508 = CounterChain(List(x2507)).name("x2508").ctrl(x2544) // CounterChainNew(List(x2507))
    val x2543 = LoopController(style=StreamPipe, level=OuterControl, cchain=x2508).name("x2543").ctrl(x2544) // UnrolledForeach(List(b1052, b1054),x2508,Block(Const(())),List(List(b1251)),List(List(b1252)))
    val b1251 = CounterIter(x2507, Some(0)).ctrl(x2543).name("b1251")
    val b1252 = DummyOp().ctrl(x2543).name("b1252")
    val b2582 = StreamOut(field="offset").name("b2582").ctrl(x2543) // x2509 = StreamOutNew(BurstCmdBus)
    val b2583 = StreamOut(field="size").name("b2583").ctrl(x2543) // x2509 = StreamOutNew(BurstCmdBus)
    val x2510 = StreamOut(field="data").name("x2510").ctrl(x2543) // x2510 = StreamOutNew(BurstFullDataBus())
    val x2511 = StreamIn(field="ack").name("x2511").ctrl(x2543) // x2511 = StreamInNew(BurstAckBus)
    val x2527 = UnitController(style=SeqPipe, level=InnerControl).name("x2527").ctrl(x2543) // UnitPipe(List(b1252, b1052, b1054),Block(x2526))
    val x2512 = OpDef(op=FixAdd, inputs=List(b1049, b1251)).name("x2512").ctrl(x2527) // FixAdd(b1049,b1251)
    val x2513 = x2512 // FixConvert(x2512,TRUE,_32,_0)
    val x2514 = ReadMem(x2321_d0).name("x2514").ctrl(x2527) // RegRead(x2321)
    val x2515 = OpDef(op=FixMul, inputs=List(x2513, x2514)).name("x2515").ctrl(x2527) // FixMul(x2513,x2514)
    val x2516 = b1051 // FixConvert(b1051,TRUE,_32,_0)
    val x2517 = OpDef(op=FixAdd, inputs=List(x2515, x2516)).name("x2517").ctrl(x2527) // FixAdd(x2515,x2516)
    val x2518 = OpDef(op=FixSla, inputs=List(x2517, Const(2))).name("x2518").ctrl(x2527) // FixLsh(x2517,Const(2))
    val x2519 = x2518 // FixConvert(x2518,TRUE,_64,_0)
    val x2520 = top.dramAddress(x2330).name("x2520").ctrl(x2527) // GetDRAMAddress(x2330)
    val x2521 = OpDef(op=FixAdd, inputs=List(x2519, x2520)).name("x2521").ctrl(x2527) // FixAdd(x2519,x2520)
    val x2522 = x2521 // FixConvert(x2521,TRUE,_64,_0)
    // x2523 = SimpleStruct(ArrayBuffer((offset,x2522), (size,Const(64)), (isLoad,Const(false))))
    val x2524 = OpDef(op=BitAnd, inputs=List(b1252, b1052)).name("x2524").ctrl(x2527) // And(b1252,b1052)
    val x2525 = OpDef(op=BitAnd, inputs=List(x2524, b1054)).name("x2525").ctrl(x2527) // And(x2524,b1054)
    val b2584_b2582 = WriteMem(b2582, x2522).name("b2584_b2582").ctrl(x2527) // StreamWrite(x2509,x2523,x2525)
    val b2585_b2583 = WriteMem(b2583, Const(64)).name("b2585_b2583").ctrl(x2527) // StreamWrite(x2509,x2523,x2525)
    val x2528 = Counter(min=Const(0).ctrl(x2543), max=Const(16).ctrl(x2543), step=Const(1).ctrl(x2543), par=16).name("x2528").ctrl(x2543) // CounterNew(Const(0),Const(16),Const(1),Const(16))
    val x2529 = CounterChain(List(x2528)).name("x2529").ctrl(x2543) // CounterChainNew(List(x2528))
    val x2537 = LoopController(style=InnerPipe, level=InnerControl, cchain=x2529).name("x2537").ctrl(x2543) // UnrolledForeach(List(b1252, b1052, b1054),x2529,Block(Const(())),List(List(b1274)),List(List(b1275)))
    val b1274 = CounterIter(x2528, None).ctrl(x2537).name("b1274")
    val b1275 = DummyOp().ctrl(x2537).name("b1275")
    val x2530 = OpDef(op=BitAnd, inputs=List(b1275, b1252)).name("x2530").ctrl(x2537) // And(b1275,b1252)
    val x2531 = OpDef(op=BitAnd, inputs=List(b1052, b1054)).name("x2531").ctrl(x2537) // And(b1052,b1054)
    val x2532 = OpDef(op=BitAnd, inputs=List(x2530, x2531)).name("x2532").ctrl(x2537) // And(x2530,x2531)
    val x2533 = LoadBanks(List(x2343_d0_b0), List(b1251, b1274)).name("x2533").ctrl(x2537) // ParSRAMLoad(x2343,List(List(b1251, b1274)),List(x2532))
    val x2534 = x2533 // x2534 = VectorApply(x2533,0)
    // x2535 = SimpleStruct(ArrayBuffer((_1,x2534), (_2,Const(true))))
    val x2536_x2510 = WriteMem(x2510, x2534).name("x2536_x2510").ctrl(x2537) // ParStreamWrite(x2510,List(x2535),List(x2532))
    val x2538 = FringeContainer(x2330,b2582,b2583,x2510,x2511).name("x2538").ctrl(x2543) // FringeDenseStore(x2330,x2509,x2510,x2511)
    val x2542 = UnitController(style=SeqPipe, level=InnerControl).name("x2542").ctrl(x2543) // UnitPipe(List(b1252, b1052, b1054),Block(Const(())))
    val x2539 = OpDef(op=BitAnd, inputs=List(b1252, b1052)).name("x2539").ctrl(x2542) // And(b1252,b1052)
    val x2540 = OpDef(op=BitAnd, inputs=List(x2539, b1054)).name("x2540").ctrl(x2542) // And(x2539,b1054)
    val x2541 = ReadMem(x2511).name("x2541").ctrl(x2542) // StreamRead(x2511,x2540)
    
  }
}
