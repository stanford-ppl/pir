import pir.graph.{Mux => _, _}
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.util.enums._
import pir.util._
import pir.PIRApp

object Gibbs_Ising2D extends PIRApp {
  def main(top:Top) = {
    val exp_lut_oc = OffChip("exp_lut")
    val x2402_3_s = Scalar("x2402_3")
    val x2447_0_s = Scalar("x2447_0")
    val x2402_x2530_s = Scalar("x2402_x2530")
    val x2403_b2617_x2412_b2619_s = Scalar("x2403_b2617_x2412_b2619")
    val x2401_x2419_s = Scalar("x2401_x2419")
    val bias_dram_oc = OffChip("bias_dram")
    val x2450_b2628_x2462_b2630_s = Scalar("x2450_b2628_x2462_b2630")
    val x2424_b2622_x2436_b2624_s = Scalar("x2424_b2622_x2436_b2624")
    val x2450_b2627_x2462_b2629_s = Scalar("x2450_b2627_x2462_b2629")
    val bus_378_s = Scalar("bus_378")
    val x2402_5_s = Scalar("x2402_5")
    val x2425_x2438_data_v = Vector("x2425_x2438_data")
    val grid_dram_oc = OffChip("grid_dram")
    val bus_385_s = Scalar("bus_385")
    val x2402_1_s = Scalar("x2402_1")
    val x2404_x2414_data_s = Scalar("x2404_x2414_data")
    val x2403_b2618_x2412_b2620_s = Scalar("x2403_b2618_x2412_b2620")
    val exp_posbias_argin = ArgIn("exp_posbias")
    val x2424_b2621_x2436_b2623_s = Scalar("x2424_b2621_x2436_b2623")
    val x2402_x2444_s = Scalar("x2402_x2444")
    val x2536_b2647_x2549_b2649_s = Scalar("x2536_b2647_x2549_b2649")
    val x2537_x2557_v = Vector("x2537_x2557")
    val x2451_x2464_data_v = Vector("x2451_x2464_data")
    val x2402_2_s = Scalar("x2402_2")
    val x2402_4_s = Scalar("x2402_4")
    val x2478_x2480_s = Scalar("x2478_x2480")
    val exp_negbias_argin = ArgIn("exp_negbias")
    val iters_argin = ArgIn("iters")
    val bus_362_s = Scalar("bus_362")
    val exp_lut_da = DRAMAddress("exp_lut", "exp_lut")
    val grid_dram_da = DRAMAddress("grid_dram", "grid_dram")
    val x2447_x2470_s = Scalar("x2447_x2470")
    val x2402_0_s = Scalar("x2402_0")
    val x2401_0_s = Scalar("x2401_0")
    val x2536_b2648_x2549_b2650_s = Scalar("x2536_b2648_x2549_b2650")
    val bias_dram_da = DRAMAddress("bias_dram", "bias_dram")
    val x2564 = Sequential(name="x2564",parent=top) { implicit CU => 
      val x2564_unit = CounterChain(name = "x2564_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x2401_dsp0 = MemoryPipeline(name="x2401_dsp0",parent="x2564") { implicit CU => 
      val x2506 = CU.temp
      val x2491 = CU.temp
      val x2499 = CU.temp
      val x2508 = CU.temp
      val x2485 = CU.temp
      val x2507 = CU.temp
      val x2495 = CU.temp
      val x2505 = CU.temp
      val x2501 = ScalarFIFO(size=1,name="x2501").wtPort(x2402_2_s)
      val x2493 = ScalarFIFO(size=1,name="x2493").wtPort(x2402_4_s)
      val x2489 = ScalarFIFO(size=1,name="x2489").wtPort(x2402_5_s)
      val x2503 = ScalarFIFO(size=1,name="x2503").wtPort(x2402_1_s)
      val x2419 = ScalarFIFO(size=1,name="x2419").wtPort(x2401_x2419_s)
      val x2497 = ScalarFIFO(size=1,name="x2497").wtPort(x2402_3_s)
      val x2416 = CounterChain.copy("x2420_0", "x2416")
      val x2477 = CounterChain.copy("x2532", "x2477")
      val x2484 = CounterChain.copy("x2531", "x2484")
      val x2401 = SRAM(size=9,name="x2401",banking = Strided(1)).wtPort(x2419.readPort).rdPort(x2401_0_s).wtAddr(x2416(0))
      RAStage(operands=List(CU.ctr(x2484(0)), Const(1)), op=FixSub, results=List(x2499))
      RAStage(operands=List(CU.ctr(x2477(0)), Const(1)), op=FixSub, results=List(x2495))
      RAStage(operands=List(CU.ctr(x2477(0)), Const(1)), op=FixAdd, results=List(x2485))
      RAStage(operands=List(CU.ctr(x2484(0)), Const(1)), op=FixAdd, results=List(x2491))
      RAStage(operands=List(CU.load(x2489), CU.load(x2493)), op=FixAdd, results=List(x2505))
      RAStage(operands=List(x2505, CU.load(x2497)), op=FixAdd, results=List(x2506))
      RAStage(operands=List(x2506, CU.load(x2501)), op=FixAdd, results=List(x2507))
      RAStage(operands=List(x2507, CU.load(x2503)), op=FixMul, results=List(x2508))
      RAStage(operands=List(x2508, Const(4)), op=FixAdd, results=List(x2401.readAddr))
    }
    val x2402_dsp0 = MemoryPipeline(name="x2402_dsp0",parent="x2564") { implicit CU => 
      val b2651 = CU.temp
      val b2645 = CU.temp
      val x2444 = ScalarFIFO(size=1,name="x2444").wtPort(x2402_x2444_s)
      val x2530 = ScalarFIFO(size=1,name="x2530").wtPort(x2402_x2530_s)
      val x2535 = CounterChain.copy("x2563", "x2535")
      val x2484 = CounterChain.copy("x2531", "x2484")
      val x2477 = CounterChain.copy("x2532", "x2477")
      val x2552 = CounterChain.copy("x2558_0", "x2552")
      val x2402 = SRAM(size=2048,name="x2402",banking = Strided(1)).wtPort(x2444.readPort).wtPort(x2530.readPort).rdPort(x2402_0_s)
      WAStage(operands=List(CU.ctr(x2477(0)), Const(64)), op=FixMul, results=List(b2645))
      WAStage(operands=List(b2645, CU.ctr(x2484(0))), op=FixAdd, results=List(x2402.writeAddr))
      RAStage(operands=List(CU.ctr(x2535(0)), Const(64)), op=FixMul, results=List(b2651))
      RAStage(operands=List(b2651, CU.ctr(x2552(0))), op=FixAdd, results=List(x2402.readAddr))
    }
    val x2402_dsp1 = MemoryPipeline(name="x2402_dsp1",parent="x2564") { implicit CU => 
      val b2641 = CU.temp
      val b2645 = CU.temp
      val x2444 = ScalarFIFO(size=1,name="x2444").wtPort(x2402_x2444_s)
      val x2530 = ScalarFIFO(size=1,name="x2530").wtPort(x2402_x2530_s)
      val x2484 = CounterChain.copy("x2531", "x2484")
      val x2477 = CounterChain.copy("x2532", "x2477")
      val x2402 = SRAM(size=2048,name="x2402",banking = Strided(1)).wtPort(x2444.readPort).wtPort(x2530.readPort).rdPort(x2402_1_s)
      WAStage(operands=List(CU.ctr(x2477(0)), Const(64)), op=FixMul, results=List(b2645))
      WAStage(operands=List(b2645, CU.ctr(x2484(0))), op=FixAdd, results=List(x2402.writeAddr))
      RAStage(operands=List(CU.ctr(x2477(0)), Const(64)), op=FixMul, results=List(b2641))
      RAStage(operands=List(b2641, CU.ctr(x2484(0))), op=FixAdd, results=List(x2402.readAddr))
    }
    val x2402_dsp2 = MemoryPipeline(name="x2402_dsp2",parent="x2564") { implicit CU => 
      val x2499 = CU.temp
      val x2500 = CU.temp
      val b2639 = CU.temp
      val b2645 = CU.temp
      val x2444 = ScalarFIFO(size=1,name="x2444").wtPort(x2402_x2444_s)
      val x2530 = ScalarFIFO(size=1,name="x2530").wtPort(x2402_x2530_s)
      val x2484 = CounterChain.copy("x2531", "x2484")
      val x2477 = CounterChain.copy("x2532", "x2477")
      val x2402 = SRAM(size=2048,name="x2402",banking = Strided(1)).wtPort(x2444.readPort).wtPort(x2530.readPort).rdPort(x2402_2_s)
      WAStage(operands=List(CU.ctr(x2477(0)), Const(64)), op=FixMul, results=List(b2645))
      WAStage(operands=List(b2645, CU.ctr(x2484(0))), op=FixAdd, results=List(x2402.writeAddr))
      RAStage(operands=List(CU.ctr(x2484(0)), Const(1)), op=FixSub, results=List(x2499))
      RAStage(operands=List(x2499, Const(64)), op=FixMod, results=List(x2500))
      RAStage(operands=List(CU.ctr(x2477(0)), Const(64)), op=FixMul, results=List(b2639))
      RAStage(operands=List(b2639, x2500), op=FixAdd, results=List(x2402.readAddr))
    }
    val x2402_dsp3 = MemoryPipeline(name="x2402_dsp3",parent="x2564") { implicit CU => 
      val x2496 = CU.temp
      val b2637 = CU.temp
      val x2495 = CU.temp
      val b2645 = CU.temp
      val x2444 = ScalarFIFO(size=1,name="x2444").wtPort(x2402_x2444_s)
      val x2530 = ScalarFIFO(size=1,name="x2530").wtPort(x2402_x2530_s)
      val x2484 = CounterChain.copy("x2531", "x2484")
      val x2477 = CounterChain.copy("x2532", "x2477")
      val x2402 = SRAM(size=2048,name="x2402",banking = Strided(1)).wtPort(x2444.readPort).wtPort(x2530.readPort).rdPort(x2402_3_s)
      WAStage(operands=List(CU.ctr(x2477(0)), Const(64)), op=FixMul, results=List(b2645))
      WAStage(operands=List(b2645, CU.ctr(x2484(0))), op=FixAdd, results=List(x2402.writeAddr))
      RAStage(operands=List(CU.ctr(x2477(0)), Const(1)), op=FixSub, results=List(x2495))
      RAStage(operands=List(x2495, Const(32)), op=FixMod, results=List(x2496))
      RAStage(operands=List(x2496, Const(64)), op=FixMul, results=List(b2637))
      RAStage(operands=List(b2637, CU.ctr(x2484(0))), op=FixAdd, results=List(x2402.readAddr))
    }
    val x2402_dsp4 = MemoryPipeline(name="x2402_dsp4",parent="x2564") { implicit CU => 
      val x2491 = CU.temp
      val x2492 = CU.temp
      val b2645 = CU.temp
      val b2635 = CU.temp
      val x2444 = ScalarFIFO(size=1,name="x2444").wtPort(x2402_x2444_s)
      val x2530 = ScalarFIFO(size=1,name="x2530").wtPort(x2402_x2530_s)
      val x2484 = CounterChain.copy("x2531", "x2484")
      val x2477 = CounterChain.copy("x2532", "x2477")
      val x2402 = SRAM(size=2048,name="x2402",banking = Strided(1)).wtPort(x2444.readPort).wtPort(x2530.readPort).rdPort(x2402_4_s)
      WAStage(operands=List(CU.ctr(x2477(0)), Const(64)), op=FixMul, results=List(b2645))
      WAStage(operands=List(b2645, CU.ctr(x2484(0))), op=FixAdd, results=List(x2402.writeAddr))
      RAStage(operands=List(CU.ctr(x2484(0)), Const(1)), op=FixAdd, results=List(x2491))
      RAStage(operands=List(x2491, Const(64)), op=FixMod, results=List(x2492))
      RAStage(operands=List(CU.ctr(x2477(0)), Const(64)), op=FixMul, results=List(b2635))
      RAStage(operands=List(b2635, x2492), op=FixAdd, results=List(x2402.readAddr))
    }
    val x2402_dsp5 = MemoryPipeline(name="x2402_dsp5",parent="x2564") { implicit CU => 
      val x2486 = CU.temp
      val b2633 = CU.temp
      val x2485 = CU.temp
      val b2645 = CU.temp
      val x2444 = ScalarFIFO(size=1,name="x2444").wtPort(x2402_x2444_s)
      val x2530 = ScalarFIFO(size=1,name="x2530").wtPort(x2402_x2530_s)
      val x2484 = CounterChain.copy("x2531", "x2484")
      val x2477 = CounterChain.copy("x2532", "x2477")
      val x2402 = SRAM(size=2048,name="x2402",banking = Strided(1)).wtPort(x2444.readPort).wtPort(x2530.readPort).rdPort(x2402_5_s)
      WAStage(operands=List(CU.ctr(x2477(0)), Const(64)), op=FixMul, results=List(b2645))
      WAStage(operands=List(b2645, CU.ctr(x2484(0))), op=FixAdd, results=List(x2402.writeAddr))
      RAStage(operands=List(CU.ctr(x2477(0)), Const(1)), op=FixAdd, results=List(x2485))
      RAStage(operands=List(x2485, Const(32)), op=FixMod, results=List(x2486))
      RAStage(operands=List(x2486, Const(64)), op=FixMul, results=List(b2633))
      RAStage(operands=List(b2633, CU.ctr(x2484(0))), op=FixAdd, results=List(x2402.readAddr))
    }
    val x2421 = StreamController(name="x2421",parent=x2564) { implicit CU => 
      val x2421_unit = CounterChain(name = "x2421_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x2413_0 = Pipeline(name="x2413_0",parent=x2421) { implicit CU => 
      val x2406 = CU.temp
      val x2408 = ScalarBuffer(name="x2408").wtPort(exp_lut_da)
      val x2413_unit = CounterChain(name = "x2413_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(Const(0), Const(3)), op=FixSla, results=List(x2406))
      Stage(operands=List(x2406, CU.load(x2408)), op=FixAdd, results=List(CU.scalarOut(x2403_b2617_x2412_b2619_s)))
      Stage(operands=List(Const(72)), op=Bypass, results=List(CU.scalarOut(x2403_b2618_x2412_b2620_s)))
    }
    val x2414 = MemoryController(name="x2414",parent=x2421,offchip=exp_lut_oc, mctpe=TileLoad) { implicit CU => 
      val x2403_b2618 = ScalarFIFO(size=1,name="size").wtPort(x2403_b2618_x2412_b2620_s)
      val x2403_b2617 = ScalarFIFO(size=1,name="offset").wtPort(x2403_b2617_x2412_b2619_s)
      CU.newSout("data", x2404_x2414_data_s)
    }
    val x2420_0 = Pipeline(name="x2420_0",parent=x2421) { implicit CU => 
      val x2404 = ScalarFIFO(size=1,name="x2404").wtPort(x2404_x2414_data_s)
      val ctr1 = Counter(min=Const(0), max=Const(9), step=Const(1), par=1) // Counter
      val x2416 = CounterChain(name = "x2416", ctr1).iter(9)
      Stage(operands=List(CU.load(x2404)), op=Bypass, results=List(CU.scalarOut(x2401_x2419_s)))
    }
    val x2446 = StreamController(name="x2446",parent=x2564) { implicit CU => 
      val ctr2 = Counter(min=Const(0), max=Const(32), step=Const(1), par=1) // Counter
      val x2423 = CounterChain(name = "x2423", ctr2).iter(32)
    }
    val x2437_0 = Pipeline(name="x2437_0",parent=x2446) { implicit CU => 
      val x2429 = CU.temp
      val x2427 = CU.temp
      val x2430 = CU.temp
      val x2432 = ScalarBuffer(name="x2432").wtPort(grid_dram_da)
      val x2423 = CounterChain.copy("x2446", "x2423")
      val x2437_unit = CounterChain(name = "x2437_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x2423(0)), Const(6)), op=FixSla, results=List(x2427))
      Stage(operands=List(x2427, Const(0)), op=FixAdd, results=List(x2429))
      Stage(operands=List(x2429, Const(2)), op=FixSla, results=List(x2430))
      Stage(operands=List(x2430, CU.load(x2432)), op=FixAdd, results=List(CU.scalarOut(x2424_b2621_x2436_b2623_s)))
      Stage(operands=List(Const(256)), op=Bypass, results=List(CU.scalarOut(x2424_b2622_x2436_b2624_s)))
    }
    val x2438 = MemoryController(name="x2438",parent=x2446,offchip=grid_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x2424_b2622 = ScalarFIFO(size=1,name="size").wtPort(x2424_b2622_x2436_b2624_s)
      val x2424_b2621 = ScalarFIFO(size=1,name="offset").wtPort(x2424_b2621_x2436_b2623_s)
      CU.newVout("data", x2425_x2438_data_v)
    }
    val x2445_0 = Pipeline(name="x2445_0",parent=x2446) { implicit CU => 
      val x2425 = VectorFIFO(size=1,name="x2425").wtPort(x2425_x2438_data_v)
      val ctr3 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x2440 = CounterChain(name = "x2440", ctr3).iter(4)
      Stage(operands=List(CU.load(x2425)), op=Bypass, results=List(CU.scalarOut(x2402_x2444_s)))
    }
    val x2447_dsp0 = MemoryPipeline(name="x2447_dsp0",parent="x2564") { implicit CU => 
      val b2631 = CU.temp
      val b2643 = CU.temp
      val x2470 = ScalarFIFO(size=1,name="x2470").wtPort(x2447_x2470_s)
      val x2449 = CounterChain.copy("x2472", "x2449")
      val x2466 = CounterChain.copy("x2471_0", "x2466")
      val x2484 = CounterChain.copy("x2531", "x2484")
      val x2477 = CounterChain.copy("x2532", "x2477")
      val x2447 = SRAM(size=2048,name="x2447",banking = Strided(1)).wtPort(x2470.readPort).rdPort(x2447_0_s)
      WAStage(operands=List(CU.ctr(x2449(0)), Const(64)), op=FixMul, results=List(b2631))
      WAStage(operands=List(b2631, CU.ctr(x2466(0))), op=FixAdd, results=List(x2447.writeAddr))
      RAStage(operands=List(CU.ctr(x2477(0)), Const(64)), op=FixMul, results=List(b2643))
      RAStage(operands=List(b2643, CU.ctr(x2484(0))), op=FixAdd, results=List(x2447.readAddr))
    }
    val x2472 = StreamController(name="x2472",parent=x2564) { implicit CU => 
      val ctr4 = Counter(min=Const(0), max=Const(32), step=Const(1), par=1) // Counter
      val x2449 = CounterChain(name = "x2449", ctr4).iter(32)
    }
    val x2463_0 = Pipeline(name="x2463_0",parent=x2472) { implicit CU => 
      val x2456 = CU.temp
      val x2453 = CU.temp
      val x2455 = CU.temp
      val x2458 = ScalarBuffer(name="x2458").wtPort(bias_dram_da)
      val x2449 = CounterChain.copy("x2472", "x2449")
      val x2463_unit = CounterChain(name = "x2463_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x2449(0)), Const(6)), op=FixSla, results=List(x2453))
      Stage(operands=List(x2453, Const(0)), op=FixAdd, results=List(x2455))
      Stage(operands=List(x2455, Const(2)), op=FixSla, results=List(x2456))
      Stage(operands=List(x2456, CU.load(x2458)), op=FixAdd, results=List(CU.scalarOut(x2450_b2627_x2462_b2629_s)))
      Stage(operands=List(Const(256)), op=Bypass, results=List(CU.scalarOut(x2450_b2628_x2462_b2630_s)))
    }
    val x2464 = MemoryController(name="x2464",parent=x2472,offchip=bias_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x2450_b2628 = ScalarFIFO(size=1,name="size").wtPort(x2450_b2628_x2462_b2630_s)
      val x2450_b2627 = ScalarFIFO(size=1,name="offset").wtPort(x2450_b2627_x2462_b2629_s)
      CU.newVout("data", x2451_x2464_data_v)
    }
    val x2471_0 = Pipeline(name="x2471_0",parent=x2472) { implicit CU => 
      val x2451 = VectorFIFO(size=1,name="x2451").wtPort(x2451_x2464_data_v)
      val ctr5 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x2466 = CounterChain(name = "x2466", ctr5).iter(4)
      Stage(operands=List(CU.load(x2451)), op=Bypass, results=List(CU.scalarOut(x2447_x2470_s)))
    }
    val x2533 = MetaPipeline(name="x2533",parent=x2564) { implicit CU => 
      val x2383 = ScalarBuffer(name="x2383").wtPort(iters_argin)
      val ctr6 = Counter(min=Const(0), max=x2383.readPort, step=Const(1), par=1) // Counter
      val x2475 = CounterChain(name = "x2475", ctr6).iter(1)
    }
    val x2532 = MetaPipeline(name="x2532",parent=x2533) { implicit CU => 
      val ctr7 = Counter(min=Const(0), max=Const(32), step=Const(1), par=1) // Counter
      val x2477 = CounterChain(name = "x2477", ctr7).iter(32)
    }
    val x2481_0 = Pipeline(name="x2481_0",parent=x2532) { implicit CU => 
      val x2481_unit = CounterChain(name = "x2481_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(Const(0)), op=Bypass, results=List(CU.scalarOut(x2478_x2480_s)))
    }
    val x2531 = StreamController(name="x2531",parent=x2532) { implicit CU => 
      val x2478 = ScalarBuffer(name="x2478").wtPort(x2478_x2480_s)
      val ctr8 = Counter(min=x2478.readPort, max=Const(64), step=Const(1), par=1) // Counter
      val x2484 = CounterChain(name = "x2484", ctr8).iter(64)
    }
    val x2531_0 = Pipeline(name="x2531_0",parent=x2531) { implicit CU => 
      val x2524 = CU.temp
      val x2523 = CU.temp
      val x2477 = CounterChain.copy("x2532", "x2477")
      val x2484 = CounterChain.copy("x2531", "x2484")
      Stage(operands=List(Const(0), CU.ctr(x2484(0))), op=FixLt, results=List(x2523))
      Stage(operands=List(CU.ctr(x2484(0)), Const(64)), op=FixLt, results=List(x2524))
      Stage(operands=List(x2523, x2524), op=BitAnd, results=List(CU.scalarOut(bus_362_s)))
      Stage(operands=List(CU.ctr(x2484(0)), Const(1)), op=FixSub, results=List())
      Stage(operands=List(CU.ctr(x2477(0)), Const(1)), op=FixSub, results=List())
      Stage(operands=List(CU.ctr(x2477(0)), Const(1)), op=FixAdd, results=List())
    }
    val x2531_1 = Pipeline(name="x2531_1",parent=x2531) { implicit CU => 
      val x2506 = CU.temp
      val x2507 = CU.temp
      val x2505 = CU.temp
      val x2501 = ScalarFIFO(size=1,name="x2501").wtPort(x2402_2_s)
      val x2493 = ScalarFIFO(size=1,name="x2493").wtPort(x2402_4_s)
      val x2497 = ScalarFIFO(size=1,name="x2497").wtPort(x2402_3_s)
      val x2503 = ScalarFIFO(size=1,name="x2503").wtPort(x2402_1_s)
      val x2489 = ScalarFIFO(size=1,name="x2489").wtPort(x2402_5_s)
      val x2512 = ScalarFIFO(size=1,name="x2512").wtPort(x2447_0_s)
      val x2484 = CounterChain.copy("x2531", "x2484")
      Stage(operands=List(CU.ctr(x2484(0)), Const(1)), op=FixAdd, results=List())
      Stage(operands=List(CU.load(x2489), CU.load(x2493)), op=FixAdd, results=List(x2505))
      Stage(operands=List(x2505, CU.load(x2497)), op=FixAdd, results=List(x2506))
      Stage(operands=List(x2506, CU.load(x2501)), op=FixAdd, results=List(x2507))
      Stage(operands=List(x2507, CU.load(x2503)), op=FixMul, results=List())
      Stage(operands=List(CU.load(x2512), CU.load(x2503)), op=FixMul, results=List(CU.scalarOut(bus_378_s)))
    }
    val x2531_2 = Pipeline(name="x2531_2",parent=x2531) { implicit CU => 
      val x2521 = CU.temp
      val x2522 = CU.temp
      val x2519 = CU.temp
      val x2515 = CU.temp
      val x2518 = CU.temp
      val x2385 = ScalarBuffer(name="x2385").wtPort(exp_posbias_argin)
      val x2510 = ScalarFIFO(size=1,name="x2510").wtPort(x2401_0_s)
      val x2384 = ScalarBuffer(name="x2384").wtPort(exp_negbias_argin)
      val x2514 = ScalarFIFO(size=1,name="x2514").wtPort(bus_378_s)
      Stage(operands=List(CU.load(x2514), Const(0)), op=FixLt, results=List(x2515))
      Stage(operands=List(x2515, CU.load(x2385), CU.load(x2384)), op=Mux, results=List(x2518))
      Stage(operands=List(CU.load(x2510), x2518), op=FixMul, results=List(x2519))
      Stage(operands=List(Const(1), x2519), op=FixLt, results=List(x2521))
      Stage(operands=List(x2521, Const(1), Const(1)), op=Mux, results=List(x2522))
      Stage(operands=List(x2522, Const(1)), op=FixEql, results=List(CU.scalarOut(bus_385_s)))
    }
    val x2531_3 = Pipeline(name="x2531_3",parent=x2531) { implicit CU => 
      val x2527 = CU.temp
      val x2528 = CU.temp
      val x2525 = ScalarFIFO(size=1,name="x2525").wtPort(bus_362_s)
      val x2526 = ScalarFIFO(size=1,name="x2526").wtPort(bus_385_s)
      val x2503 = ScalarFIFO(size=1,name="x2503").wtPort(x2402_1_s)
      Stage(operands=List(CU.load(x2525), CU.load(x2526)), op=BitAnd, results=List(x2527))
      Stage(operands=List(CU.load(x2503)), op=FixNeg, results=List(x2528))
      Stage(operands=List(x2527, x2528, CU.load(x2503)), op=Mux, results=List(CU.scalarOut(x2402_x2530_s)))
    }
    val x2563 = StreamController(name="x2563",parent=x2564) { implicit CU => 
      val ctr9 = Counter(min=Const(0), max=Const(32), step=Const(1), par=1) // Counter
      val x2535 = CounterChain(name = "x2535", ctr9).iter(32)
    }
    val x2559 = Sequential(name="x2559",parent=x2563) { implicit CU => 
      val x2559_unit = CounterChain(name = "x2559_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x2550_0 = Pipeline(name="x2550_0",parent=x2559) { implicit CU => 
      val x2542 = CU.temp
      val x2543 = CU.temp
      val x2540 = CU.temp
      val x2545 = ScalarBuffer(name="x2545").wtPort(grid_dram_da)
      val x2535 = CounterChain.copy("x2563", "x2535")
      val x2550_unit = CounterChain(name = "x2550_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x2535(0)), Const(6)), op=FixSla, results=List(x2540))
      Stage(operands=List(x2540, Const(0)), op=FixAdd, results=List(x2542))
      Stage(operands=List(x2542, Const(2)), op=FixSla, results=List(x2543))
      Stage(operands=List(x2543, CU.load(x2545)), op=FixAdd, results=List(CU.scalarOut(x2536_b2647_x2549_b2649_s)))
      Stage(operands=List(Const(256)), op=Bypass, results=List(CU.scalarOut(x2536_b2648_x2549_b2650_s)))
    }
    val x2558_0 = Pipeline(name="x2558_0",parent=x2559) { implicit CU => 
      val x2554 = ScalarFIFO(size=1,name="x2554").wtPort(x2402_0_s)
      val ctr10 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x2552 = CounterChain(name = "x2552", ctr10).iter(4)
      Stage(operands=List(CU.load(x2554)), op=Bypass, results=List(CU.vecOut(x2537_x2557_v)))
    }
    val x2560 = MemoryController(name="x2560",parent=x2563,offchip=grid_dram_oc, mctpe=TileStore) { implicit CU => 
      val x2536_b2648 = ScalarFIFO(size=1,name="size").wtPort(x2536_b2648_x2549_b2650_s)
      val x2537 = VectorFIFO(size=1,name="data").wtPort(x2537_x2557_v)
      val x2536_b2647 = ScalarFIFO(size=1,name="offset").wtPort(x2536_b2647_x2549_b2649_s)
    }
    
  }
}
