import pir.graph.{Mux => _, _}
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.util.enums._
import pir.util._
import pir.PIRApp

object GDA extends PIRApp {
  def main(top:Top) = {
    val x2425_oc = OffChip("x2425")
    val x2451_argin = ArgIn("x2451")
    val x2432_b2669_x2439_b2671_s = Scalar("x2432_b2669_x2439_b2671")
    val x2450_x2458_data_v = Vector("x2450_x2458_data")
    val x2473_x2521_s = Scalar("x2473_x2521")
    val x2524_x2576_x2582_s = Scalar("x2524_x2576_x2582")
    val x2472_x2534_x2545_v = Vector("x2472_x2534_x2545")
    val x2467_x2581_s = Scalar("x2467_x2581")
    val x2474_b2676_x2482_b2678_s = Scalar("x2474_b2676_x2482_b2678")
    val x2528_x2553_x2557_v = Vector("x2528_x2553_x2557")
    val x2471_x2535_x2545_s = Scalar("x2471_x2535_x2545")
    val x2587_b2702_x2597_b2704_s = Scalar("x2587_b2702_x2597_b2704")
    val x2417_argin = ArgIn("x2417").bound(360000)
    val x2475_x2484_data_v = Vector("x2475_x2484_data")
    val x2449_b2672_x2456_b2674_s = Scalar("x2449_b2672_x2456_b2674")
    val x2433_x2441_data_v = Vector("x2433_x2441_data")
    val x2587_b2703_x2597_b2705_s = Scalar("x2587_b2703_x2597_b2705")
    val x2529_x2563_x2569_s = Scalar("x2529_x2563_x2569")
    val x2430_x2537_x2545_v = Vector("x2430_x2537_x2545")
    val x2496_x2508_data_v = Vector("x2496_x2508_data")
    val x2476_argin = ArgIn("x2476")
    val x2529_x2556_v = Vector("x2529_x2556")
    val x2431_x2536_x2545_v = Vector("x2431_x2536_x2545")
    val x2590_argin = ArgIn("x2590")
    val x2422_oc = OffChip("x2422")
    val x2474_b2677_x2482_b2679_s = Scalar("x2474_b2677_x2482_b2679")
    val x2420_oc = OffChip("x2420")
    val x2432_b2668_x2439_b2670_s = Scalar("x2432_b2668_x2439_b2670")
    val x2467_x2577_x2582_s = Scalar("x2467_x2577_x2582")
    val x2528_x2552_x2557_s = Scalar("x2528_x2552_x2557")
    val x2434_argin = ArgIn("x2434")
    val x2467_x2602_x2606_v = Vector("x2467_x2602_x2606")
    val x2424_oc = OffChip("x2424")
    val x2528_x2544_v = Vector("x2528_x2544")
    val x2497_argin = ArgIn("x2497")
    val x2495_b2680_x2506_b2682_s = Scalar("x2495_b2680_x2506_b2682")
    val x2524_x2568_s = Scalar("x2524_x2568")
    val x2495_b2681_x2506_b2683_s = Scalar("x2495_b2681_x2506_b2683")
    val x2423_oc = OffChip("x2423")
    val x2524_x2564_x2569_s = Scalar("x2524_x2564_x2569")
    val x2449_b2673_x2456_b2675_s = Scalar("x2449_b2673_x2456_b2675")
    val x2616 = Sequential(name="x2616",parent=top) { implicit CU => 
      val x2616_unit = CounterChain(name = "x2616_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x2430_dsp0 = MemoryPipeline(name="x2430_dsp0",parent="x2616") { implicit CU => 
      val x2446_x2446 = VectorFIFO(size=1).wtPort(x2433_x2441_data_v)
      val x2443 = CounterChain.copy("x2447", "x2443")
      val x2531 = CounterChain.copy("x2545_0", "x2531")
      val x2430_x2537 = SRAM(size=96,banking = Strided(1)).wtPort(x2446_x2446.readPort).rdPort(x2430_x2537_x2545_v).rdAddr(x2531(0)).wtAddr(x2443(0))
    }
    val x2431_dsp0 = MemoryPipeline(name="x2431_dsp0",parent="x2616") { implicit CU => 
      val x2463_x2463 = VectorFIFO(size=1).wtPort(x2450_x2458_data_v)
      val x2460 = CounterChain.copy("x2464", "x2460")
      val x2531 = CounterChain.copy("x2545_0", "x2531")
      val x2431_x2536 = SRAM(size=96,banking = Strided(1)).wtPort(x2463_x2463.readPort).rdPort(x2431_x2536_x2545_v).rdAddr(x2531(0)).wtAddr(x2460(0))
    }
    val x2448 = StreamController(name="x2448",parent=x2616) { implicit CU => 
      val x2448_unit = CounterChain(name = "x2448_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x2440_0 = Pipeline(name="x2440_0",parent=x2448) { implicit CU => 
      val x2434 = ScalarBuffer().wtPort(x2434_argin)
      val x2440_unit = CounterChain(name = "x2440_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(Const(0), CU.load(x2434)), op=FixAdd, results=List(CU.scalarOut(x2432_b2668_x2439_b2670_s)))
      Stage(operands=List(Const(384)), op=Bypass, results=List(CU.scalarOut(x2432_b2669_x2439_b2671_s)))
    }
    val x2441 = MemoryController(name="x2441",parent=x2448,offchip=x2423_oc, mctpe=TileLoad) { implicit CU => 
      val x2432_b2669_x2441 = ScalarFIFO(name="size",size=1).wtPort(x2432_b2669_x2439_b2671_s)
      val x2432_b2668_x2441 = ScalarFIFO(name="offset",size=1).wtPort(x2432_b2668_x2439_b2670_s)
      CU.newVout("data", x2433_x2441_data_v)
    }
    val x2447 = Pipeline(name="x2447",parent=x2448) { implicit CU => 
      val ctr1 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x2443 = CounterChain(name = "x2443", ctr1).iter(6)
    }
    val x2465 = StreamController(name="x2465",parent=x2616) { implicit CU => 
      val x2465_unit = CounterChain(name = "x2465_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x2457_0 = Pipeline(name="x2457_0",parent=x2465) { implicit CU => 
      val x2451 = ScalarBuffer().wtPort(x2451_argin)
      val x2457_unit = CounterChain(name = "x2457_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(Const(0), CU.load(x2451)), op=FixAdd, results=List(CU.scalarOut(x2449_b2672_x2456_b2674_s)))
      Stage(operands=List(Const(384)), op=Bypass, results=List(CU.scalarOut(x2449_b2673_x2456_b2675_s)))
    }
    val x2458 = MemoryController(name="x2458",parent=x2465,offchip=x2424_oc, mctpe=TileLoad) { implicit CU => 
      val x2449_b2673_x2458 = ScalarFIFO(name="size",size=1).wtPort(x2449_b2673_x2456_b2675_s)
      val x2449_b2672_x2458 = ScalarFIFO(name="offset",size=1).wtPort(x2449_b2672_x2456_b2674_s)
      CU.newVout("data", x2450_x2458_data_v)
    }
    val x2464 = Pipeline(name="x2464",parent=x2465) { implicit CU => 
      val ctr2 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x2460 = CounterChain(name = "x2460", ctr2).iter(6)
    }
    val x2467_dsp0 = MemoryPipeline(name="x2467_dsp0",parent="x2584") { implicit CU => 
      val b2706 = CU.temp()
      val b2700 = CU.temp()
      val x2581_x2581 = ScalarFIFO(size=1).wtPort(x2467_x2581_s)
      val x2574 = CounterChain.copy("x2582_0", "x2574")
      val x2586 = CounterChain.copy("x2615", "x2586")
      val x2600 = CounterChain.copy("x2606", "x2600")
      val x2467_x2602 = SRAM(size=9216,banking = Strided(1)).wtPort(x2581_x2581.readPort).rdPort(x2467_x2602_x2606_v)
      WAStage(operands=List(CU.ctr(x2574(0)), Const(96)), op=FixMul, results=List(b2700))
      WAStage(operands=List(b2700, CU.ctr(x2574(1))), op=FixAdd, results=List(x2467_x2602.writeAddr))
      RAStage(operands=List(CU.ctr(x2586(0)), Const(96)), op=FixMul, results=List(b2706))
      RAStage(operands=List(b2706, CU.ctr(x2600(0))), op=FixAdd, results=List(x2467_x2602.readAddr))
    }
    val x2467_dsp1 = MemoryPipeline(name="x2467_dsp1",parent="x2584") { implicit CU => 
      val b2698 = CU.temp()
      val b2700 = CU.temp()
      val x2581_x2581 = ScalarFIFO(size=1).wtPort(x2467_x2581_s)
      val x2574 = CounterChain.copy("x2582_0", "x2574")
      val x2467_x2577 = SRAM(size=9216,banking = NoBanking()).wtPort(x2581_x2581.readPort).rdPort(x2467_x2577_x2582_s)
      WAStage(operands=List(CU.ctr(x2574(0)), Const(96)), op=FixMul, results=List(b2700))
      WAStage(operands=List(b2700, CU.ctr(x2574(1))), op=FixAdd, results=List(x2467_x2577.writeAddr))
      RAStage(operands=List(CU.ctr(x2574(0)), Const(96)), op=FixMul, results=List(b2698))
      RAStage(operands=List(b2698, CU.ctr(x2574(1))), op=FixAdd, results=List(x2467_x2577.readAddr))
    }
    val x2584 = MetaPipeline(name="x2584",parent=x2616) { implicit CU => 
      val x2417_x2468 = ScalarBuffer().wtPort(x2417_argin)
      val ctr3 = Counter(min=Const(0), max=x2417_x2468.load, step=Const(16), par=1) // Counter
      val x2470 = CounterChain(name = "x2470", ctr3).iter(22500)
    }
    val x2471_dsp0 = MemoryPipeline(name="x2471_dsp0",parent="x2584") { implicit CU => 
      val x2490_x2490 = VectorFIFO(size=1).wtPort(x2475_x2484_data_v)
      val x2486 = CounterChain.copy("x2491", "x2486")
      val x2527 = CounterChain.copy("x2571", "x2527")
      val x2471_x2535 = SRAM(size=16,banking = Strided(1)).wtPort(x2490_x2490.readPort).rdPort(x2471_x2535_x2545_s).rdAddr(x2527(0)).wtAddr(x2486(0))
    }
    val x2472_dsp0 = MemoryPipeline(name="x2472_dsp0",parent="x2584") { implicit CU => 
      val b2686 = CU.temp()
      val b2684 = CU.temp()
      val x2515_x2515 = VectorFIFO(size=1).wtPort(x2496_x2508_data_v)
      val x2494 = CounterChain.copy("x2517", "x2494")
      val x2510 = CounterChain.copy("x2516", "x2510")
      val x2527 = CounterChain.copy("x2571", "x2527")
      val x2531 = CounterChain.copy("x2545_0", "x2531")
      val x2472_x2534 = SRAM(size=1536,banking = Strided(1)).wtPort(x2515_x2515.readPort).rdPort(x2472_x2534_x2545_v)
      WAStage(operands=List(CU.ctr(x2494(0)), Const(96)), op=FixMul, results=List(b2684))
      WAStage(operands=List(b2684, CU.ctr(x2510(0))), op=FixAdd, results=List(x2472_x2534.writeAddr))
      RAStage(operands=List(CU.ctr(x2527(0)), Const(96)), op=FixMul, results=List(b2686))
      RAStage(operands=List(b2686, CU.ctr(x2531(0))), op=FixAdd, results=List(x2472_x2534.readAddr))
    }
    val x2492 = StreamController(name="x2492",parent=x2584) { implicit CU => 
      val x2492_unit = CounterChain(name = "x2492_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x2483_0 = Pipeline(name="x2483_0",parent=x2492) { implicit CU => 
      val x2477 = CU.temp()
      val x2476 = ScalarBuffer().wtPort(x2476_argin)
      val x2470 = CounterChain.copy("x2584", "x2470")
      val x2483_unit = CounterChain(name = "x2483_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x2470(0)), Const(4)), op=FixMul, results=List(x2477))
      Stage(operands=List(x2477, CU.load(x2476)), op=FixAdd, results=List(CU.scalarOut(x2474_b2676_x2482_b2678_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x2474_b2677_x2482_b2679_s)))
    }
    val x2484 = MemoryController(name="x2484",parent=x2492,offchip=x2422_oc, mctpe=TileLoad) { implicit CU => 
      val x2474_b2676_x2484 = ScalarFIFO(name="offset",size=1).wtPort(x2474_b2676_x2482_b2678_s)
      val x2474_b2677_x2484 = ScalarFIFO(name="size",size=1).wtPort(x2474_b2677_x2482_b2679_s)
      CU.newVout("data", x2475_x2484_data_v)
    }
    val x2491 = Pipeline(name="x2491",parent=x2492) { implicit CU => 
      val ctr4 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x2486 = CounterChain(name = "x2486", ctr4).iter(1)
    }
    val x2517 = StreamController(name="x2517",parent=x2584) { implicit CU => 
      val ctr5 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x2494 = CounterChain(name = "x2494", ctr5).iter(16)
    }
    val x2507_0 = Pipeline(name="x2507_0",parent=x2517) { implicit CU => 
      val x2498 = CU.temp()
      val x2499 = CU.temp()
      val x2500 = CU.temp()
      val x2497 = ScalarBuffer().wtPort(x2497_argin)
      val x2470 = CounterChain.copy("x2584", "x2470")
      val x2494 = CounterChain.copy("x2517", "x2494")
      val x2507_unit = CounterChain(name = "x2507_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x2470(0)), CU.ctr(x2494(0))), op=FixAdd, results=List(x2498))
      Stage(operands=List(x2498, Const(96)), op=FixMul, results=List(x2499))
      Stage(operands=List(x2499, Const(4)), op=FixMul, results=List(x2500))
      Stage(operands=List(x2500, CU.load(x2497)), op=FixAdd, results=List(CU.scalarOut(x2495_b2680_x2506_b2682_s)))
      Stage(operands=List(Const(384)), op=Bypass, results=List(CU.scalarOut(x2495_b2681_x2506_b2683_s)))
    }
    val x2508 = MemoryController(name="x2508",parent=x2517,offchip=x2420_oc, mctpe=TileLoad) { implicit CU => 
      val x2495_b2681_x2508 = ScalarFIFO(name="size",size=1).wtPort(x2495_b2681_x2506_b2683_s)
      val x2495_b2680_x2508 = ScalarFIFO(name="offset",size=1).wtPort(x2495_b2680_x2506_b2682_s)
      CU.newVout("data", x2496_x2508_data_v)
    }
    val x2516 = Pipeline(name="x2516",parent=x2517) { implicit CU => 
      val ctr6 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x2510 = CounterChain(name = "x2510", ctr6).iter(6)
    }
    val x2522_0 = Pipeline(name="x2522_0",parent=x2584) { implicit CU => 
      val x2519 = CU.temp()
      val x2417_x2518 = ScalarBuffer().wtPort(x2417_argin)
      val x2470 = CounterChain.copy("x2584", "x2470")
      val x2522_unit = CounterChain(name = "x2522_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x2417_x2518), CU.ctr(x2470(0))), op=FixSub, results=List(x2519))
      Stage(operands=List(x2519, Const(16)), op=FixMin, results=List(CU.scalarOut(x2473_x2521_s)))
    }
    val x2524_dsp0 = MemoryPipeline(name="x2524_dsp0",parent="x2571") { implicit CU => 
      val b2694 = CU.temp()
      val b2696 = CU.temp()
      val x2568_x2568 = ScalarFIFO(size=1).wtPort(x2524_x2568_s)
      val x2560 = CounterChain.copy("x2569_0", "x2560")
      val x2574 = CounterChain.copy("x2582_0", "x2574")
      val x2524_x2576 = SRAM(size=9216,banking = NoBanking()).wtPort(x2568_x2568.readPort).rdPort(x2524_x2576_x2582_s)
      WAStage(operands=List(CU.ctr(x2560(0)), Const(96)), op=FixMul, results=List(b2694))
      WAStage(operands=List(b2694, CU.ctr(x2560(1))), op=FixAdd, results=List(x2524_x2576.writeAddr))
      RAStage(operands=List(CU.ctr(x2574(0)), Const(96)), op=FixMul, results=List(b2696))
      RAStage(operands=List(b2696, CU.ctr(x2574(1))), op=FixAdd, results=List(x2524_x2576.readAddr))
    }
    val x2524_dsp1 = MemoryPipeline(name="x2524_dsp1",parent="x2571") { implicit CU => 
      val b2694 = CU.temp()
      val b2692 = CU.temp()
      val x2568_x2568 = ScalarFIFO(size=1).wtPort(x2524_x2568_s)
      val x2560 = CounterChain.copy("x2569_0", "x2560")
      val x2524_x2564 = SRAM(size=9216,banking = NoBanking()).wtPort(x2568_x2568.readPort).rdPort(x2524_x2564_x2569_s)
      WAStage(operands=List(CU.ctr(x2560(0)), Const(96)), op=FixMul, results=List(b2694))
      WAStage(operands=List(b2694, CU.ctr(x2560(1))), op=FixAdd, results=List(x2524_x2564.writeAddr))
      RAStage(operands=List(CU.ctr(x2560(0)), Const(96)), op=FixMul, results=List(b2692))
      RAStage(operands=List(b2692, CU.ctr(x2560(1))), op=FixAdd, results=List(x2524_x2564.readAddr))
    }
    val x2571 = MetaPipeline(name="x2571",parent=x2584) { implicit CU => 
      val x2473_x2525 = ScalarBuffer().wtPort(x2473_x2521_s)
      val ctr7 = Counter(min=Const(0), max=x2473_x2525.load, step=Const(1), par=1) // Counter
      val x2527 = CounterChain(name = "x2527", ctr7).iter(1)
    }
    val x2528_dsp0 = MemoryPipeline(name="x2528_dsp0",parent="x2571") { implicit CU => 
      val x2544_x2544 = VectorFIFO(size=1).wtPort(x2528_x2544_v)
      val x2531 = CounterChain.copy("x2545_0", "x2531")
      val x2548 = CounterChain.copy("x2557_0", "x2548")
      val x2528_x2553 = SRAM(size=96,banking = Strided(1)).wtPort(x2544_x2544.readPort).rdPort(x2528_x2553_x2557_v).rdAddr(x2548(1)).wtAddr(x2531(0))
    }
    val x2528_dsp1 = MemoryPipeline(name="x2528_dsp1",parent="x2571") { implicit CU => 
      val x2544_x2544 = VectorFIFO(size=1).wtPort(x2528_x2544_v)
      val x2531 = CounterChain.copy("x2545_0", "x2531")
      val x2548 = CounterChain.copy("x2557_0", "x2548")
      val x2528_x2552 = SRAM(size=96,banking = Strided(1)).wtPort(x2544_x2544.readPort).rdPort(x2528_x2552_x2557_s).rdAddr(x2548(0)).wtAddr(x2531(0))
    }
    val x2529_dsp0 = MemoryPipeline(name="x2529_dsp0",parent="x2571") { implicit CU => 
      val b2688 = CU.temp()
      val b2690 = CU.temp()
      val x2556_x2556 = VectorFIFO(size=1).wtPort(x2529_x2556_v)
      val x2548 = CounterChain.copy("x2557_0", "x2548")
      val x2560 = CounterChain.copy("x2569_0", "x2560")
      val x2529_x2563 = SRAM(size=9216,banking = Strided(1)).wtPort(x2556_x2556.readPort).rdPort(x2529_x2563_x2569_s)
      WAStage(operands=List(CU.ctr(x2548(0)), Const(96)), op=FixMul, results=List(b2688))
      WAStage(operands=List(b2688, CU.ctr(x2548(1))), op=FixAdd, results=List(x2529_x2563.writeAddr))
      RAStage(operands=List(CU.ctr(x2560(0)), Const(96)), op=FixMul, results=List(b2690))
      RAStage(operands=List(b2690, CU.ctr(x2560(1))), op=FixAdd, results=List(x2529_x2563.readAddr))
    }
    val x2545_0 = Pipeline(name="x2545_0",parent=x2571) { implicit CU => 
      val x2542 = CU.temp()
      val x2541 = CU.temp()
      val x2535_x2535 = ScalarFIFO(size=1).wtPort(x2471_x2535_x2545_s)
      val x2534_x2534 = VectorFIFO(size=1).wtPort(x2472_x2534_x2545_v)
      val x2537_x2537 = VectorFIFO(size=1).wtPort(x2430_x2537_x2545_v)
      val x2536_x2536 = VectorFIFO(size=1).wtPort(x2431_x2536_x2545_v)
      val ctr8 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x2531 = CounterChain(name = "x2531", ctr8).iter(6)
      Stage(operands=List(CU.load(x2535_x2535), Const(1)), op=FixEql, results=List(x2541))
      Stage(operands=List(x2541, CU.load(x2536_x2536), CU.load(x2537_x2537)), op=Mux, results=List(x2542))
      Stage(operands=List(CU.load(x2534_x2534), x2542), op=FixSub, results=List(CU.vecOut(x2528_x2544_v)))
    }
    val x2557_0 = Pipeline(name="x2557_0",parent=x2571) { implicit CU => 
      val x2553_x2553 = VectorFIFO(size=1).wtPort(x2528_x2553_x2557_v)
      val x2552_x2552 = ScalarFIFO(size=1).wtPort(x2528_x2552_x2557_s)
      val ctr9 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val ctr10 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x2548 = CounterChain(name = "x2548", ctr9, ctr10).iter(576)
      Stage(operands=List(CU.load(x2552_x2552), CU.load(x2553_x2553)), op=FixMul, results=List(CU.vecOut(x2529_x2556_v)))
    }
    val x2569_0 = Pipeline(name="x2569_0",parent=x2571) { implicit CU => 
      val x2564_x2564 = ScalarFIFO(size=1).wtPort(x2524_x2564_x2569_s)
      val x2563_x2563 = ScalarFIFO(size=1).wtPort(x2529_x2563_x2569_s)
      val ctr11 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val ctr12 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val x2560 = CounterChain(name = "x2560", ctr11, ctr12).iter(9216)
      Stage(operands=List(CU.load(x2563_x2563), CU.load(x2564_x2564)), op=FixAdd, results=List(CU.scalarOut(x2524_x2568_s)))
    }
    val x2582_0 = Pipeline(name="x2582_0",parent=x2584) { implicit CU => 
      val x2577_x2577 = ScalarFIFO(size=1).wtPort(x2467_x2577_x2582_s)
      val x2576_x2576 = ScalarFIFO(size=1).wtPort(x2524_x2576_x2582_s)
      val ctr13 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val ctr14 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val x2574 = CounterChain(name = "x2574", ctr13, ctr14).iter(9216)
      Stage(operands=List(CU.load(x2576_x2576), CU.load(x2577_x2577)), op=FixAdd, results=List(CU.scalarOut(x2467_x2581_s)))
    }
    val x2615 = StreamController(name="x2615",parent=x2616) { implicit CU => 
      val ctr15 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val x2586 = CounterChain(name = "x2586", ctr15).iter(96)
    }
    val x2607 = Sequential(name="x2607",parent=x2615) { implicit CU => 
      val x2607_unit = CounterChain(name = "x2607_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x2598_0 = Pipeline(name="x2598_0",parent=x2607) { implicit CU => 
      val x2592 = CU.temp()
      val x2591 = CU.temp()
      val x2590 = ScalarBuffer().wtPort(x2590_argin)
      val x2586 = CounterChain.copy("x2615", "x2586")
      val x2598_unit = CounterChain(name = "x2598_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x2586(0)), Const(96)), op=FixMul, results=List(x2591))
      Stage(operands=List(x2591, Const(4)), op=FixMul, results=List(x2592))
      Stage(operands=List(x2592, CU.load(x2590)), op=FixAdd, results=List(CU.scalarOut(x2587_b2702_x2597_b2704_s)))
      Stage(operands=List(Const(384)), op=Bypass, results=List(CU.scalarOut(x2587_b2703_x2597_b2705_s)))
    }
    val x2606 = Pipeline(name="x2606",parent=x2607) { implicit CU => 
      val ctr16 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x2600 = CounterChain(name = "x2600", ctr16).iter(6)
    }
    val x2608 = MemoryController(name="x2608",parent=x2615,offchip=x2425_oc, mctpe=TileStore) { implicit CU => 
      val x2587_b2703_x2608 = ScalarFIFO(name="size",size=1).wtPort(x2587_b2703_x2597_b2705_s)
      val x2587_b2702_x2608 = ScalarFIFO(name="offset",size=1).wtPort(x2587_b2702_x2597_b2704_s)
      val x2588_x2608 = VectorFIFO(name="data",size=1).wtPort(x2467_x2602_x2606_v)
    }
    
  }
}
