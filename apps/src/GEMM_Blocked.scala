import pir.graph.{Mux =>_, _}
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.util.enums._
import pir.util._
import pir.PIRApp

object GEMM_Blocked extends PIRApp {
  def main(top:Top) = {
    val x2385_x2398_data_s = Scalar("x2385_x2398_data")
    val c_dram_da = DRAMAddress("c_dram", "c_dram")
    val x2384_b2586_x2396_b2588_s = Scalar("x2384_b2586_x2396_b2588")
    val x2384_b2587_x2396_b2589_s = Scalar("x2384_b2587_x2396_b2589")
    val x2417_x2433_data_s = Scalar("x2417_x2433_data")
    val x2512_b2608_x2525_b2610_s = Scalar("x2512_b2608_x2525_b2610")
    val x2380_0_s = Scalar("x2380_0")
    val x2416_b2593_x2431_b2595_s = Scalar("x2416_b2593_x2431_b2595")
    val x2451_b2598_x2467_b2600_s = Scalar("x2451_b2598_x2467_b2600")
    val a_dram_oc = OffChip("a_dram")
    val x2381_1_s = Scalar("x2381_1")
    val x2381_x2504_s = Scalar("x2381_x2504")
    val x2512_b2609_x2525_b2611_s = Scalar("x2512_b2609_x2525_b2611")
    val b_dram_oc = OffChip("b_dram")
    val x2452_x2469_data_s = Scalar("x2452_x2469_data")
    val x2451_b2599_x2467_b2601_s = Scalar("x2451_b2599_x2467_b2601")
    val x2381_0_s = Scalar("x2381_0")
    val x2416_b2592_x2431_b2594_s = Scalar("x2416_b2592_x2431_b2594")
    val a_dram_da = DRAMAddress("a_dram", "a_dram")
    val x2379_0_s = Scalar("x2379_0")
    val b_dram_da = DRAMAddress("b_dram", "b_dram")
    val c_dram_oc = OffChip("c_dram")
    val x2539 = Sequential(name="x2539",parent=top) { implicit CU => 
      val x2539_unit = CounterChain(name = "x2539_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x2379_dsp0 = MemoryPipeline(name="x2379_dsp0",parent="x2539") { implicit CU => 
      val x2478 = ScalarFIFO(size=1,name="x2478").wtPort(x2452_x2469_data_s)
      val x2482 = CounterChain.copy("x2506", "x2482")
      val x2471 = CounterChain.copy("x2479", "x2471")
      val x2379 = SRAM(size=16,name="x2379",banking = Strided(1)).wtPort(x2478.readPort).rdPort(x2379_0_s).rdAddr(x2482(0)).wtAddr(x2471(0))
    }
    val x2380_dsp0 = MemoryPipeline(name="x2380_dsp0",parent="x2539") { implicit CU => 
      val b2604 = CU.temp(None)
      val b2596 = CU.temp(None)
      val x2441 = ScalarFIFO(size=1,name="x2441").wtPort(x2417_x2433_data_s)
      val x2415 = CounterChain.copy("x2443", "x2415")
      val x2435 = CounterChain.copy("x2442", "x2435")
      val x2491 = CounterChain.copy("x2505_0", "x2491")
      val x2482 = CounterChain.copy("x2506", "x2482")
      val x2380 = SRAM(size=256,name="x2380",banking = Strided(1)).wtPort(x2441.readPort).rdPort(x2380_0_s)
      WAStage(operands=List(CU.ctr(x2415(0)), Const(16)), op=FixMul, results=List(b2596))
      WAStage(operands=List(b2596, CU.ctr(x2435(0))), op=FixAdd, results=List(x2380.writeAddr))
      RAStage(operands=List(CU.ctr(x2482(0)), Const(16)), op=FixMul, results=List(b2604))
      RAStage(operands=List(b2604, CU.ctr(x2491(0))), op=FixAdd, results=List(x2380.readAddr))
    }
    val x2381_dsp0 = MemoryPipeline(name="x2381_dsp0",parent="x2539") { implicit CU => 
      val x2492 = CU.temp(None)
      val b2612 = CU.temp(None)
      val b2606 = CU.temp(None)
      val x2504 = ScalarFIFO(size=1,name="x2504").wtPort(x2381_x2504_s)
      val x2404 = ScalarFIFO(size=1,name="x2404").wtPort(x2385_x2398_data_s)
      val x2528 = CounterChain.copy("x2534", "x2528")
      val x2445 = CounterChain.copy("x2507", "x2445")
      val x2491 = CounterChain.copy("x2505_0", "x2491")
      val x2511 = CounterChain.copy("x2538", "x2511")
      val x2408 = CounterChain.copy("x2509", "x2408")
      val x2381 = SRAM(size=4096,name="x2381",banking = Strided(1)).wtPort(x2404.readPort).wtPort(x2504.readPort).rdPort(x2381_0_s)
      WAStage(operands=List(CU.ctr(x2491(0)), CU.ctr(x2408(0))), op=FixAdd, results=List(x2492))
      WAStage(operands=List(CU.ctr(x2445(0)), Const(64)), op=FixMul, results=List(b2606))
      WAStage(operands=List(b2606, x2492), op=FixAdd, results=List(x2381.writeAddr))
      RAStage(operands=List(CU.ctr(x2511(0)), Const(64)), op=FixMul, results=List(b2612))
      RAStage(operands=List(b2612, CU.ctr(x2528(0))), op=FixAdd, results=List(x2381.readAddr))
    }
    val x2381_dsp1 = MemoryPipeline(name="x2381_dsp1",parent="x2539") { implicit CU => 
      val x2492 = CU.temp(None)
      val b2606 = CU.temp(None)
      val b2602 = CU.temp(None)
      val x2504 = ScalarFIFO(size=1,name="x2504").wtPort(x2381_x2504_s)
      val x2404 = ScalarFIFO(size=1,name="x2404").wtPort(x2385_x2398_data_s)
      val x2445 = CounterChain.copy("x2507", "x2445")
      val x2491 = CounterChain.copy("x2505_0", "x2491")
      val x2408 = CounterChain.copy("x2509", "x2408")
      val x2381 = SRAM(size=4096,name="x2381",banking = Strided(1)).wtPort(x2404.readPort).wtPort(x2504.readPort).rdPort(x2381_1_s)
      WAStage(operands=List(CU.ctr(x2491(0)), CU.ctr(x2408(0))), op=FixAdd, results=List(x2492))
      WAStage(operands=List(CU.ctr(x2445(0)), Const(64)), op=FixMul, results=List(b2606))
      WAStage(operands=List(b2606, x2492), op=FixAdd, results=List(x2381.writeAddr))
      RAStage(operands=List(CU.ctr(x2491(0)), CU.ctr(x2408(0))), op=FixAdd, results=List(x2492))
      RAStage(operands=List(CU.ctr(x2445(0)), Const(64)), op=FixMul, results=List(b2602))
      RAStage(operands=List(b2602, x2492), op=FixAdd, results=List(x2381.readAddr))
    }
    val x2406 = StreamController(name="x2406",parent=x2539) { implicit CU => 
      val ctr1 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val x2383 = CounterChain(name = "x2383", ctr1).iter(64)
    }
    val x2397_0 = Pipeline(name="x2397_0",parent=x2406) { implicit CU => 
      val x2387 = CU.temp(None)
      val x2389 = CU.temp(None)
      val x2390 = CU.temp(None)
      val x2392 = ScalarBuffer(name="x2392").wtPort(c_dram_da)
      val x2383 = CounterChain.copy("x2406", "x2383")
      val x2397_unit = CounterChain(name = "x2397_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x2383(0)), Const(6)), op=FixSla, results=List(x2387))
      Stage(operands=List(x2387, Const(0)), op=FixAdd, results=List(x2389))
      Stage(operands=List(x2389, Const(2)), op=FixSla, results=List(x2390))
      Stage(operands=List(x2390, CU.load(x2392)), op=FixAdd, results=List(CU.scalarOut(x2384_b2586_x2396_b2588_s)))
      Stage(operands=List(Const(256)), op=Bypass, results=List(CU.scalarOut(x2384_b2587_x2396_b2589_s)))
    }
    val x2398 = MemoryController(name="x2398",parent=x2406,offchip=c_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x2384_b2586 = ScalarFIFO(size=1,name="offset").wtPort(x2384_b2586_x2396_b2588_s)
      val x2384_b2587 = ScalarFIFO(size=1,name="size").wtPort(x2384_b2587_x2396_b2589_s)
      CU.newSout("data", x2385_x2398_data_s)
    }
    val x2509 = MetaPipeline(name="x2509",parent=x2539) { implicit CU => 
      val ctr3 = Counter(min=Const(0), max=Const(64), step=Const(16), par=1) // Counter
      val x2408 = CounterChain(name = "x2408", ctr3).iter(4)
    }
    val x2508 = MetaPipeline(name="x2508",parent=x2509) { implicit CU => 
      val ctr4 = Counter(min=Const(0), max=Const(64), step=Const(16), par=1) // Counter
      val x2410 = CounterChain(name = "x2410", ctr4).iter(4)
    }
    val x2443 = StreamController(name="x2443",parent=x2508) { implicit CU => 
      val ctr5 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x2415 = CounterChain(name = "x2415", ctr5).iter(16)
    }
    val x2432_0 = Pipeline(name="x2432_0",parent=x2443) { implicit CU => 
      val x2423 = CU.temp(None)
      val x2422 = CU.temp(None)
      val x2418 = CU.temp(None)
      val x2420 = CU.temp(None)
      val x2425 = ScalarBuffer(name="x2425").wtPort(b_dram_da)
      val x2410 = CounterChain.copy("x2508", "x2410")
      val x2415 = CounterChain.copy("x2443", "x2415")
      val x2408 = CounterChain.copy("x2509", "x2408")
      val x2432_unit = CounterChain(name = "x2432_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x2410(0)), CU.ctr(x2415(0))), op=FixAdd, results=List(x2418))
      Stage(operands=List(x2418, Const(6)), op=FixSla, results=List(x2420))
      Stage(operands=List(x2420, CU.ctr(x2408(0))), op=FixAdd, results=List(x2422))
      Stage(operands=List(x2422, Const(2)), op=FixSla, results=List(x2423))
      Stage(operands=List(x2423, CU.load(x2425)), op=FixAdd, results=List(CU.scalarOut(x2416_b2592_x2431_b2594_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x2416_b2593_x2431_b2595_s)))
    }
    val x2433 = MemoryController(name="x2433",parent=x2443,offchip=b_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x2416_b2592 = ScalarFIFO(size=1,name="offset").wtPort(x2416_b2592_x2431_b2594_s)
      val x2416_b2593 = ScalarFIFO(size=1,name="size").wtPort(x2416_b2593_x2431_b2595_s)
      CU.newSout("data", x2417_x2433_data_s)
    }
    val x2442 = Pipeline(name="x2442",parent=x2443) { implicit CU => 
      val ctr6 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x2435 = CounterChain(name = "x2435", ctr6).iter(1)
    }
    val x2507 = MetaPipeline(name="x2507",parent=x2508) { implicit CU => 
      val ctr7 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val x2445 = CounterChain(name = "x2445", ctr7).iter(64)
    }
    val x2480 = StreamController(name="x2480",parent=x2507) { implicit CU => 
      val ctr8 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1) // Counter
      val x2450 = CounterChain(name = "x2450", ctr8).iter(1)
    }
    val x2468_0 = Pipeline(name="x2468_0",parent=x2480) { implicit CU => 
      val x2455 = CU.temp(None)
      val x2453 = CU.temp(None)
      val x2457 = CU.temp(None)
      val x2458 = CU.temp(None)
      val x2460 = ScalarBuffer(name="x2460").wtPort(a_dram_da)
      val x2445 = CounterChain.copy("x2507", "x2445")
      val x2410 = CounterChain.copy("x2508", "x2410")
      val x2450 = CounterChain.copy("x2480", "x2450")
      val x2468_unit = CounterChain(name = "x2468_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x2445(0)), CU.ctr(x2450(0))), op=FixAdd, results=List(x2453))
      Stage(operands=List(x2453, Const(6)), op=FixSla, results=List(x2455))
      Stage(operands=List(x2455, CU.ctr(x2410(0))), op=FixAdd, results=List(x2457))
      Stage(operands=List(x2457, Const(2)), op=FixSla, results=List(x2458))
      Stage(operands=List(x2458, CU.load(x2460)), op=FixAdd, results=List(CU.scalarOut(x2451_b2598_x2467_b2600_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x2451_b2599_x2467_b2601_s)))
    }
    val x2469 = MemoryController(name="x2469",parent=x2480,offchip=a_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x2451_b2598 = ScalarFIFO(size=1,name="offset").wtPort(x2451_b2598_x2467_b2600_s)
      val x2451_b2599 = ScalarFIFO(size=1,name="size").wtPort(x2451_b2599_x2467_b2601_s)
      CU.newSout("data", x2452_x2469_data_s)
    }
    val x2479 = Pipeline(name="x2479",parent=x2480) { implicit CU => 
      val ctr9 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x2471 = CounterChain(name = "x2471", ctr9).iter(16)
    }
    val x2506 = MetaPipeline(name="x2506",parent=x2507) { implicit CU => 
      val ctr10 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x2482 = CounterChain(name = "x2482", ctr10).iter(16)
    }
    val x2505_0 = Pipeline(name="x2505_0",parent=x2506) { implicit CU => 
      val x2502 = CU.temp(None)
      val x2499 = ScalarFIFO(size=1,name="x2499").wtPort(x2380_0_s)
      val x2483 = ScalarBuffer(name="x2483").wtPort(x2379_0_s)
      val x2497 = ScalarFIFO(size=1,name="x2497").wtPort(x2381_1_s)
      val ctr11 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x2491 = CounterChain(name = "x2491", ctr11).iter(1)
      Stage(operands=List(CU.load(x2499), CU.load(x2483)), op=FixMul, results=List(x2502))
      Stage(operands=List(CU.load(x2497), x2502), op=FixAdd, results=List(CU.scalarOut(x2381_x2504_s)))
    }
    val x2538 = StreamController(name="x2538",parent=x2539) { implicit CU => 
      val ctr12 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val x2511 = CounterChain(name = "x2511", ctr12).iter(64)
    }
    val x2526_0 = Pipeline(name="x2526_0",parent=x2538) { implicit CU => 
      val x2516 = CU.temp(None)
      val x2518 = CU.temp(None)
      val x2519 = CU.temp(None)
      val x2521 = ScalarBuffer(name="x2521").wtPort(c_dram_da)
      val x2511 = CounterChain.copy("x2538", "x2511")
      val x2526_unit = CounterChain(name = "x2526_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x2511(0)), Const(6)), op=FixSla, results=List(x2516))
      Stage(operands=List(x2516, Const(0)), op=FixAdd, results=List(x2518))
      Stage(operands=List(x2518, Const(2)), op=FixSla, results=List(x2519))
      Stage(operands=List(x2519, CU.load(x2521)), op=FixAdd, results=List(CU.scalarOut(x2512_b2608_x2525_b2610_s)))
      Stage(operands=List(Const(256)), op=Bypass, results=List(CU.scalarOut(x2512_b2609_x2525_b2611_s)))
    }
    val x2534 = Pipeline(name="x2534",parent=x2538) { implicit CU => 
      val ctr13 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x2528 = CounterChain(name = "x2528", ctr13).iter(4)
    }
    val x2535 = MemoryController(name="x2535",parent=x2538,offchip=c_dram_oc, mctpe=TileStore) { implicit CU => 
      val x2512_b2609 = ScalarFIFO(size=1,name="size").wtPort(x2512_b2609_x2525_b2611_s)
      val x2513 = ScalarFIFO(size=1,name="data").wtPort(x2381_0_s)
      val x2512_b2608 = ScalarFIFO(size=1,name="offset").wtPort(x2512_b2608_x2525_b2610_s)
    }
    
  }
}
