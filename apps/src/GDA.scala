import pir.graph
import pir.graph._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.util.enums._
import pir.util._
import pir.PIRApp

object GDA extends PIRApp {
  def main(args: String*)(top:Top) = {
    val x2481_x2490_data_v = Vector("x2481_x2490_data")
    val x2534_x2560_x2565_v = Vector("x2534_x2560_x2565")
    val x2502_x2514_data_v = Vector("x2502_x2514_data")
    val x2480_b2685_x2488_b2687_s = Scalar("x2480_b2685_x2488_b2687")
    val x2534_x2551_v = Vector("x2534_x2551")
    val x2431_oc = OffChip("x2431")
    val x2439_x2447_data_v = Vector("x2439_x2447_data")
    val x2478_x2540_x2552_v = Vector("x2478_x2540_x2552")
    val x2530_x2584_x2590_v = Vector("x2530_x2584_x2590")
    val x2438_b2677_x2445_b2679_s = Scalar("x2438_b2677_x2445_b2679")
    val x2456_x2464_data_v = Vector("x2456_x2464_data")
    val x2595_b2710_x2605_b2712_s = Scalar("x2595_b2710_x2605_b2712")
    val x2430_oc = OffChip("x2430")
    val x2477_x2541_x2552_v = Vector("x2477_x2541_x2552")
    val x2501_b2688_x2512_b2690_s = Scalar("x2501_b2688_x2512_b2690")
    val x2426_oc = OffChip("x2426")
    val x2440_argin = ArgIn("x2440")
    val x2534_x2559_x2565_v = Vector("x2534_x2559_x2565")
    val x2530_x2572_x2577_v = Vector("x2530_x2572_x2577")
    val x2479_x2527_s = Scalar("x2479_x2527")
    val x2535_x2571_x2577_v = Vector("x2535_x2571_x2577")
    val x2598_argin = ArgIn("x2598")
    val x2428_oc = OffChip("x2428")
    val x2455_b2680_x2462_b2682_s = Scalar("x2455_b2680_x2462_b2682")
    val x2473_x2589_v = Vector("x2473_x2589")
    val x2482_argin = ArgIn("x2482")
    val x2473_x2610_x2614_v = Vector("x2473_x2610_x2614")
    val x2436_x2543_x2552_v = Vector("x2436_x2543_x2552")
    val x2437_x2542_x2552_v = Vector("x2437_x2542_x2552")
    val x2501_b2689_x2512_b2691_s = Scalar("x2501_b2689_x2512_b2691")
    val x2438_b2676_x2445_b2678_s = Scalar("x2438_b2676_x2445_b2678")
    val x2530_x2576_v = Vector("x2530_x2576")
    val x2535_x2564_v = Vector("x2535_x2564")
    val x2457_argin = ArgIn("x2457")
    val x2429_oc = OffChip("x2429")
    val x2455_b2681_x2462_b2683_s = Scalar("x2455_b2681_x2462_b2683")
    val x2503_argin = ArgIn("x2503")
    val x2480_b2684_x2488_b2686_s = Scalar("x2480_b2684_x2488_b2686")
    val x2473_x2585_x2590_v = Vector("x2473_x2585_x2590")
    val x2423_argin = ArgIn("x2423")
    val x2595_b2711_x2605_b2713_s = Scalar("x2595_b2711_x2605_b2713")
    val x2624 = Sequential(name="x2624",parent=top) { implicit CU => 
      val ctr1 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x2624_unit = CounterChain(name = "x2624_unit", ctr1)
    }
    val x2436_dsp0 = MemoryPipeline(name="x2436_dsp0",parent="x2624") { implicit CU => 
      val x2452_x2452 =  VectorFIFO(size=1).wtPort(x2439_x2447_data_v)
      val x2449 = CounterChain.copy("x2453", "x2449")
      val x2537 = CounterChain.copy("x2552", "x2537")
      val x2436_x2543 =  SRAM(size=64,banking = Strided(1)).wtPort(x2452_x2452.readPort).rdPort(x2436_x2543_x2552_v).rdAddr(x2537(0)).wtAddr(x2449(0))
      var stage: List[Stage] = Nil
    }
    val x2437_dsp0 = MemoryPipeline(name="x2437_dsp0",parent="x2624") { implicit CU => 
      val x2469_x2469 =  VectorFIFO(size=1).wtPort(x2456_x2464_data_v)
      val x2466 = CounterChain.copy("x2470", "x2466")
      val x2537 = CounterChain.copy("x2552", "x2537")
      val x2437_x2542 =  SRAM(size=64,banking = Strided(1)).wtPort(x2469_x2469.readPort).rdPort(x2437_x2542_x2552_v).rdAddr(x2537(0)).wtAddr(x2466(0))
      var stage: List[Stage] = Nil
    }
    val x2454 = StreamController(name="x2454",parent=x2624) { implicit CU => 
      val ctr2 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x2454_unit = CounterChain(name = "x2454_unit", ctr2)
    }
    val x2446 = Pipeline(name="x2446",parent=x2454) { implicit CU => 
      val x2440 =  ScalarBuffer().wtPort(x2440_argin)
      val ctr3 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x2446_unit = CounterChain(name = "x2446_unit", ctr3)
      var stage: List[Stage] = Nil
      Stage(operands=List(Const(0), CU.load(x2440)), op=FixAdd, results=List(CU.scalarOut(x2438_b2676_x2445_b2678_s)))
      Stage(operands=List(Const(256)), op=Bypass, results=List(CU.scalarOut(x2438_b2677_x2445_b2679_s)))
    }
    val x2447 = MemoryController(name="x2447",parent=x2454,offchip=x2429_oc, mctpe=TileLoad) { implicit CU => 
      val x2438_b2676_x2447 =  ScalarFIFO(name="offset",size=1).wtPort(x2438_b2676_x2445_b2678_s)
      val x2438_b2677_x2447 =  ScalarFIFO(name="size",size=1).wtPort(x2438_b2677_x2445_b2679_s)
      CU.newVout("data", x2439_x2447_data_v)
    }
    val x2453 = Pipeline(name="x2453",parent=x2454) { implicit CU => 
      val ctr4 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x2449 = CounterChain(name = "x2449", ctr4)
      var stage: List[Stage] = Nil
    }
    val x2471 = StreamController(name="x2471",parent=x2624) { implicit CU => 
      val ctr5 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x2471_unit = CounterChain(name = "x2471_unit", ctr5)
    }
    val x2463 = Pipeline(name="x2463",parent=x2471) { implicit CU => 
      val x2457 =  ScalarBuffer().wtPort(x2457_argin)
      val ctr6 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x2463_unit = CounterChain(name = "x2463_unit", ctr6)
      var stage: List[Stage] = Nil
      Stage(operands=List(Const(0), CU.load(x2457)), op=FixAdd, results=List(CU.scalarOut(x2455_b2680_x2462_b2682_s)))
      Stage(operands=List(Const(256)), op=Bypass, results=List(CU.scalarOut(x2455_b2681_x2462_b2683_s)))
    }
    val x2464 = MemoryController(name="x2464",parent=x2471,offchip=x2430_oc, mctpe=TileLoad) { implicit CU => 
      val x2455_b2681_x2464 =  ScalarFIFO(name="size",size=1).wtPort(x2455_b2681_x2462_b2683_s)
      val x2455_b2680_x2464 =  ScalarFIFO(name="offset",size=1).wtPort(x2455_b2680_x2462_b2682_s)
      CU.newVout("data", x2456_x2464_data_v)
    }
    val x2470 = Pipeline(name="x2470",parent=x2471) { implicit CU => 
      val ctr7 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x2466 = CounterChain(name = "x2466", ctr7)
      var stage: List[Stage] = Nil
    }
    val x2473_dsp0 = MemoryPipeline(name="x2473_dsp0",parent="x2592") { implicit CU => 
      val b2708 = CU.temp
      val b2714 = CU.temp
      val x2589_x2589 =  VectorFIFO(size=1).wtPort(x2473_x2589_v)
      val x2582 = CounterChain.copy("x2590", "x2582")
      val x2594 = CounterChain.copy("x2623", "x2594")
      val x2608 = CounterChain.copy("x2614", "x2608")
      val x2473_x2610 =  SRAM(size=4096,banking = Strided(1)).wtPort(x2589_x2589.readPort).rdPort(x2473_x2610_x2614_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x2582(0)), Const(64)), op=FixMul, results=List(b2708))
      WAStage(operands=List(b2708, CU.ctr(x2582(1))), op=FixAdd, results=List(x2473_x2610.writeAddr))
      RAStage(operands=List(CU.ctr(x2594(0)), Const(64)), op=FixMul, results=List(b2714))
      RAStage(operands=List(b2714, CU.ctr(x2608(0))), op=FixAdd, results=List(x2473_x2610.readAddr))
    }
    val x2473_dsp1 = MemoryPipeline(name="x2473_dsp1",parent="x2592") { implicit CU => 
      val b2706 = CU.temp
      val b2708 = CU.temp
      val x2589_x2589 =  VectorFIFO(size=1).wtPort(x2473_x2589_v)
      val x2582 = CounterChain.copy("x2590", "x2582")
      val x2473_x2585 =  SRAM(size=4096,banking = NoBanking()).wtPort(x2589_x2589.readPort).rdPort(x2473_x2585_x2590_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x2582(0)), Const(64)), op=FixMul, results=List(b2708))
      WAStage(operands=List(b2708, CU.ctr(x2582(1))), op=FixAdd, results=List(x2473_x2585.writeAddr))
      RAStage(operands=List(CU.ctr(x2582(0)), Const(64)), op=FixMul, results=List(b2706))
      RAStage(operands=List(b2706, CU.ctr(x2582(1))), op=FixAdd, results=List(x2473_x2585.readAddr))
    }
    val x2592 = MetaPipeline(name="x2592",parent=x2624) { implicit CU => 
      val x2423_x2474 =  ScalarBuffer().wtPort(x2423_argin)
      val ctr8 = Counter(min=Const(0), max=x2423_x2474.load, step=Const(16), par=1) // Counter
      val x2476 = CounterChain(name = "x2476", ctr8)
    }
    val x2477_dsp0 = MemoryPipeline(name="x2477_dsp0",parent="x2592") { implicit CU => 
      val x2496_x2496 =  VectorFIFO(size=1).wtPort(x2481_x2490_data_v)
      val x2492 = CounterChain.copy("x2497", "x2492")
      val x2533 = CounterChain.copy("x2579", "x2533")
      val x2477_x2541 =  SRAM(size=16,banking = Strided(1)).wtPort(x2496_x2496.readPort).rdPort(x2477_x2541_x2552_v).rdAddr(x2533(0)).wtAddr(x2492(0))
      var stage: List[Stage] = Nil
    }
    val x2478_dsp0 = MemoryPipeline(name="x2478_dsp0",parent="x2592") { implicit CU => 
      val b2694 = CU.temp
      val b2692 = CU.temp
      val x2521_x2521 =  VectorFIFO(size=1).wtPort(x2502_x2514_data_v)
      val x2516 = CounterChain.copy("x2522", "x2516")
      val x2537 = CounterChain.copy("x2552", "x2537")
      val x2533 = CounterChain.copy("x2579", "x2533")
      val x2500 = CounterChain.copy("x2523", "x2500")
      val x2478_x2540 =  SRAM(size=1024,banking = Strided(1)).wtPort(x2521_x2521.readPort).rdPort(x2478_x2540_x2552_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x2500(0)), Const(64)), op=FixMul, results=List(b2692))
      WAStage(operands=List(b2692, CU.ctr(x2516(0))), op=FixAdd, results=List(x2478_x2540.writeAddr))
      RAStage(operands=List(CU.ctr(x2533(0)), Const(64)), op=FixMul, results=List(b2694))
      RAStage(operands=List(b2694, CU.ctr(x2537(0))), op=FixAdd, results=List(x2478_x2540.readAddr))
    }
    val x2498 = StreamController(name="x2498",parent=x2592) { implicit CU => 
      val ctr9 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x2498_unit = CounterChain(name = "x2498_unit", ctr9)
    }
    val x2489 = Pipeline(name="x2489",parent=x2498) { implicit CU => 
      val x2483 = CU.temp
      val x2482 =  ScalarBuffer().wtPort(x2482_argin)
      val x2476 = CounterChain.copy("x2592", "x2476")
      val ctr10 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x2489_unit = CounterChain(name = "x2489_unit", ctr10)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x2476(0)), Const(4)), op=FixMul, results=List(x2483))
      Stage(operands=List(x2483, CU.load(x2482)), op=FixAdd, results=List(CU.scalarOut(x2480_b2684_x2488_b2686_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x2480_b2685_x2488_b2687_s)))
    }
    val x2490 = MemoryController(name="x2490",parent=x2498,offchip=x2428_oc, mctpe=TileLoad) { implicit CU => 
      val x2480_b2685_x2490 =  ScalarFIFO(name="size",size=1).wtPort(x2480_b2685_x2488_b2687_s)
      val x2480_b2684_x2490 =  ScalarFIFO(name="offset",size=1).wtPort(x2480_b2684_x2488_b2686_s)
      CU.newVout("data", x2481_x2490_data_v)
    }
    val x2497 = Pipeline(name="x2497",parent=x2498) { implicit CU => 
      val ctr11 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x2492 = CounterChain(name = "x2492", ctr11)
      var stage: List[Stage] = Nil
    }
    val x2523 = StreamController(name="x2523",parent=x2592) { implicit CU => 
      val ctr12 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x2500 = CounterChain(name = "x2500", ctr12)
    }
    val x2513 = Pipeline(name="x2513",parent=x2523) { implicit CU => 
      val x2506 = CU.temp
      val x2504 = CU.temp
      val x2505 = CU.temp
      val x2503 =  ScalarBuffer().wtPort(x2503_argin)
      val x2476 = CounterChain.copy("x2592", "x2476")
      val x2500 = CounterChain.copy("x2523", "x2500")
      val ctr13 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x2513_unit = CounterChain(name = "x2513_unit", ctr13)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x2476(0)), CU.ctr(x2500(0))), op=FixAdd, results=List(x2504))
      Stage(operands=List(x2504, Const(64)), op=FixMul, results=List(x2505))
      Stage(operands=List(x2505, Const(4)), op=FixMul, results=List(x2506))
      Stage(operands=List(x2506, CU.load(x2503)), op=FixAdd, results=List(CU.scalarOut(x2501_b2688_x2512_b2690_s)))
      Stage(operands=List(Const(256)), op=Bypass, results=List(CU.scalarOut(x2501_b2689_x2512_b2691_s)))
    }
    val x2514 = MemoryController(name="x2514",parent=x2523,offchip=x2426_oc, mctpe=TileLoad) { implicit CU => 
      val x2501_b2688_x2514 =  ScalarFIFO(name="offset",size=1).wtPort(x2501_b2688_x2512_b2690_s)
      val x2501_b2689_x2514 =  ScalarFIFO(name="size",size=1).wtPort(x2501_b2689_x2512_b2691_s)
      CU.newVout("data", x2502_x2514_data_v)
    }
    val x2522 = Pipeline(name="x2522",parent=x2523) { implicit CU => 
      val ctr14 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x2516 = CounterChain(name = "x2516", ctr14)
      var stage: List[Stage] = Nil
    }
    val x2528 = Pipeline(name="x2528",parent=x2592) { implicit CU => 
      val x2525 = CU.temp
      val x2423_x2524 =  ScalarBuffer().wtPort(x2423_argin)
      val x2476 = CounterChain.copy("x2592", "x2476")
      val ctr15 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x2528_unit = CounterChain(name = "x2528_unit", ctr15)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x2423_x2524), CU.ctr(x2476(0))), op=FixSub, results=List(x2525))
      Stage(operands=List(x2525, Const(16)), op=FixMin, results=List(CU.scalarOut(x2479_x2527_s)))
    }
    val x2530_dsp0 = MemoryPipeline(name="x2530_dsp0",parent="x2579") { implicit CU => 
      val b2702 = CU.temp
      val b2704 = CU.temp
      val x2576_x2576 =  VectorFIFO(size=1).wtPort(x2530_x2576_v)
      val x2568 = CounterChain.copy("x2577", "x2568")
      val x2582 = CounterChain.copy("x2590", "x2582")
      val x2530_x2584 =  SRAM(size=4096,banking = NoBanking()).wtPort(x2576_x2576.readPort).rdPort(x2530_x2584_x2590_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x2568(0)), Const(64)), op=FixMul, results=List(b2702))
      WAStage(operands=List(b2702, CU.ctr(x2568(1))), op=FixAdd, results=List(x2530_x2584.writeAddr))
      RAStage(operands=List(CU.ctr(x2582(0)), Const(64)), op=FixMul, results=List(b2704))
      RAStage(operands=List(b2704, CU.ctr(x2582(1))), op=FixAdd, results=List(x2530_x2584.readAddr))
    }
    val x2530_dsp1 = MemoryPipeline(name="x2530_dsp1",parent="x2579") { implicit CU => 
      val b2702 = CU.temp
      val b2700 = CU.temp
      val x2576_x2576 =  VectorFIFO(size=1).wtPort(x2530_x2576_v)
      val x2568 = CounterChain.copy("x2577", "x2568")
      val x2530_x2572 =  SRAM(size=4096,banking = NoBanking()).wtPort(x2576_x2576.readPort).rdPort(x2530_x2572_x2577_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x2568(0)), Const(64)), op=FixMul, results=List(b2702))
      WAStage(operands=List(b2702, CU.ctr(x2568(1))), op=FixAdd, results=List(x2530_x2572.writeAddr))
      RAStage(operands=List(CU.ctr(x2568(0)), Const(64)), op=FixMul, results=List(b2700))
      RAStage(operands=List(b2700, CU.ctr(x2568(1))), op=FixAdd, results=List(x2530_x2572.readAddr))
    }
    val x2579 = MetaPipeline(name="x2579",parent=x2592) { implicit CU => 
      val x2479_x2531 =  ScalarBuffer().wtPort(x2479_x2527_s)
      val ctr16 = Counter(min=Const(0), max=x2479_x2531.load, step=Const(1), par=1) // Counter
      val x2533 = CounterChain(name = "x2533", ctr16)
    }
    val x2534_dsp0 = MemoryPipeline(name="x2534_dsp0",parent="x2579") { implicit CU => 
      val x2551_x2551 =  VectorFIFO(size=1).wtPort(x2534_x2551_v)
      val x2537 = CounterChain.copy("x2552", "x2537")
      val x2555 = CounterChain.copy("x2565", "x2555")
      val x2534_x2560 =  SRAM(size=64,banking = NoBanking()).wtPort(x2551_x2551.readPort).rdPort(x2534_x2560_x2565_v).rdAddr(x2555(1)).wtAddr(x2537(0))
      var stage: List[Stage] = Nil
    }
    val x2534_dsp1 = MemoryPipeline(name="x2534_dsp1",parent="x2579") { implicit CU => 
      val x2551_x2551 =  VectorFIFO(size=1).wtPort(x2534_x2551_v)
      val x2537 = CounterChain.copy("x2552", "x2537")
      val x2555 = CounterChain.copy("x2565", "x2555")
      val x2534_x2559 =  SRAM(size=64,banking = NoBanking()).wtPort(x2551_x2551.readPort).rdPort(x2534_x2559_x2565_v).rdAddr(x2555(0)).wtAddr(x2537(0))
      var stage: List[Stage] = Nil
    }
    val x2535_dsp0 = MemoryPipeline(name="x2535_dsp0",parent="x2579") { implicit CU => 
      val b2698 = CU.temp
      val b2696 = CU.temp
      val x2564_x2564 =  VectorFIFO(size=1).wtPort(x2535_x2564_v)
      val x2555 = CounterChain.copy("x2565", "x2555")
      val x2568 = CounterChain.copy("x2577", "x2568")
      val x2535_x2571 =  SRAM(size=4096,banking = NoBanking()).wtPort(x2564_x2564.readPort).rdPort(x2535_x2571_x2577_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x2555(0)), Const(64)), op=FixMul, results=List(b2696))
      WAStage(operands=List(b2696, CU.ctr(x2555(1))), op=FixAdd, results=List(x2535_x2571.writeAddr))
      RAStage(operands=List(CU.ctr(x2568(0)), Const(64)), op=FixMul, results=List(b2698))
      RAStage(operands=List(b2698, CU.ctr(x2568(1))), op=FixAdd, results=List(x2535_x2571.readAddr))
    }
    val x2552 = Pipeline(name="x2552",parent=x2579) { implicit CU => 
      val x2548 = CU.temp
      val x2549 = CU.temp
      val x2541_x2541 =  VectorFIFO(size=1).wtPort(x2477_x2541_x2552_v)
      val x2543_x2543 =  VectorFIFO(size=1).wtPort(x2436_x2543_x2552_v)
      val x2540_x2540 =  VectorFIFO(size=1).wtPort(x2478_x2540_x2552_v)
      val x2542_x2542 =  VectorFIFO(size=1).wtPort(x2437_x2542_x2552_v)
      val ctr17 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val x2537 = CounterChain(name = "x2537", ctr17)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x2541_x2541), Const(1)), op=FixEql, results=List(x2548))
      Stage(operands=List(x2548, CU.load(x2542_x2542), CU.load(x2543_x2543)), op=Mux, results=List(x2549))
      Stage(operands=List(CU.load(x2540_x2540), x2549), op=FixSub, results=List(CU.vecOut(x2534_x2551_v)))
    }
    val x2565 = Pipeline(name="x2565",parent=x2579) { implicit CU => 
      val x2559_x2559 =  VectorFIFO(size=1).wtPort(x2534_x2559_x2565_v)
      val x2560_x2560 =  VectorFIFO(size=1).wtPort(x2534_x2560_x2565_v)
      val ctr18 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val ctr19 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val x2555 = CounterChain(name = "x2555", ctr18, ctr19)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x2559_x2559), CU.load(x2560_x2560)), op=FixMul, results=List(CU.vecOut(x2535_x2564_v)))
    }
    val x2577 = Pipeline(name="x2577",parent=x2579) { implicit CU => 
      val x2571_x2571 =  VectorFIFO(size=1).wtPort(x2535_x2571_x2577_v)
      val x2572_x2572 =  VectorFIFO(size=1).wtPort(x2530_x2572_x2577_v)
      val ctr20 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val ctr21 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val x2568 = CounterChain(name = "x2568", ctr20, ctr21)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x2571_x2571), CU.load(x2572_x2572)), op=FixAdd, results=List(CU.vecOut(x2530_x2576_v)))
    }
    val x2590 = Pipeline(name="x2590",parent=x2592) { implicit CU => 
      val x2585_x2585 =  VectorFIFO(size=1).wtPort(x2473_x2585_x2590_v)
      val x2584_x2584 =  VectorFIFO(size=1).wtPort(x2530_x2584_x2590_v)
      val ctr22 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val ctr23 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val x2582 = CounterChain(name = "x2582", ctr22, ctr23)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x2584_x2584), CU.load(x2585_x2585)), op=FixAdd, results=List(CU.vecOut(x2473_x2589_v)))
    }
    val x2623 = StreamController(name="x2623",parent=x2624) { implicit CU => 
      val ctr24 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val x2594 = CounterChain(name = "x2594", ctr24)
    }
    val x2615 = Sequential(name="x2615",parent=x2623) { implicit CU => 
      val ctr25 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x2615_unit = CounterChain(name = "x2615_unit", ctr25)
    }
    val x2606 = Pipeline(name="x2606",parent=x2615) { implicit CU => 
      val x2599 = CU.temp
      val x2600 = CU.temp
      val x2598 =  ScalarBuffer().wtPort(x2598_argin)
      val x2594 = CounterChain.copy("x2623", "x2594")
      val ctr26 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x2606_unit = CounterChain(name = "x2606_unit", ctr26)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x2594(0)), Const(64)), op=FixMul, results=List(x2599))
      Stage(operands=List(x2599, Const(4)), op=FixMul, results=List(x2600))
      Stage(operands=List(x2600, CU.load(x2598)), op=FixAdd, results=List(CU.scalarOut(x2595_b2710_x2605_b2712_s)))
      Stage(operands=List(Const(256)), op=Bypass, results=List(CU.scalarOut(x2595_b2711_x2605_b2713_s)))
    }
    val x2614 = Pipeline(name="x2614",parent=x2615) { implicit CU => 
      val ctr27 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x2608 = CounterChain(name = "x2608", ctr27)
      var stage: List[Stage] = Nil
    }
    val x2616 = MemoryController(name="x2616",parent=x2623,offchip=x2431_oc, mctpe=TileStore) { implicit CU => 
      val x2595_b2711_x2616 =  ScalarFIFO(name="size",size=1).wtPort(x2595_b2711_x2605_b2713_s)
      val x2595_b2710_x2616 =  ScalarFIFO(name="offset",size=1).wtPort(x2595_b2710_x2605_b2712_s)
      val x2596_x2616 =  VectorFIFO(name="data",size=1).wtPort(x2473_x2610_x2614_v)
    }
    
  }
}
