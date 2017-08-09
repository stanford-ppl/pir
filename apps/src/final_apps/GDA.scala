import pir.graph.{Mux =>_, _}
import pir.codegen._
import pir.spade.config._
import pir.Design
import pir.util.enums._
import pir.util._
import pir.PIRApp

object GDA extends PIRApp {
  def main(top:Top) = {
    val y_da = DRAMAddress("y", "y")
    val mu0_da = DRAMAddress("mu0", "mu0")
    val x2571_b2761_x2585_b2763_s = Scalar("x2571_b2761_x2585_b2763")
    val x2549_b2757_x2558_b2759_s = Scalar("x2549_b2757_x2558_b2759")
    val R_argin = ArgIn("R").bound(360000)
    val x2549_b2756_x2558_b2758_s = Scalar("x2549_b2756_x2558_b2758")
    val sigma_oc = OffChip("sigma")
    val x2520_b2753_x2529_b2755_s = Scalar("x2520_b2753_x2529_b2755")
    val x2540_x2664_s = Scalar("x2540_x2664")
    val x2571_b2760_x2585_b2762_s = Scalar("x2571_b2760_x2585_b2762")
    val x2607_x2607_dsp0_bank0_s = Scalar("x2607_x2607_dsp0_bank0")
    val x2540_x2540_dsp1_bank0_s = Scalar("x2540_x2540_dsp1_bank0")
    val x2520_b2752_x2529_b2754_s = Scalar("x2520_b2752_x2529_b2754")
    val x2607_x2623_s = Scalar("x2607_x2623")
    val x2521_x2531_data_s = Scalar("x2521_x2531_data")
    val x2572_x2587_data_s = Scalar("x2572_x2587_data")
    val x2500_x2500_dsp0_bank0_s = Scalar("x2500_x2500_dsp0_bank0")
    val x2607_x2607_dsp1_bank0_s = Scalar("x2607_x2607_dsp1_bank0")
    val x_oc = OffChip("x")
    val x2501_b2748_x2510_b2750_s = Scalar("x2501_b2748_x2510_b2750")
    val x2540_x2540_dsp0_bank0_s = Scalar("x2540_x2540_dsp0_bank0")
    val x2546_x2600_s = Scalar("x2546_x2600")
    val x2545_x2545_dsp0_bank0_s = Scalar("x2545_x2545_dsp0_bank0")
    val x2502_x2512_data_s = Scalar("x2502_x2512_data")
    val x2608_x2608_dsp0_bank0_s = Scalar("x2608_x2608_dsp0_bank0")
    val x2499_x2499_dsp0_bank0_s = Scalar("x2499_x2499_dsp0_bank0")
    val mu1_da = DRAMAddress("mu1", "mu1")
    val x2608_x2635_s = Scalar("x2608_x2635")
    val mu1_oc = OffChip("mu1")
    val x2550_x2560_data_s = Scalar("x2550_x2560_data")
    val x2501_b2749_x2510_b2751_s = Scalar("x2501_b2749_x2510_b2751")
    val sigma_da = DRAMAddress("sigma", "sigma")
    val x2669_b2783_x2682_b2785_s = Scalar("x2669_b2783_x2682_b2785")
    val x2544_x2544_dsp0_bank0_s = Scalar("x2544_x2544_dsp0_bank0")
    val x2603_x2650_s = Scalar("x2603_x2650")
    val mu0_oc = OffChip("mu0")
    val y_oc = OffChip("y")
    val x_da = DRAMAddress("x", "x")
    val x2669_b2782_x2682_b2784_s = Scalar("x2669_b2782_x2682_b2784")
    val x2603_x2603_dsp0_bank0_s = Scalar("x2603_x2603_dsp0_bank0")
    val x2603_x2603_dsp1_bank0_s = Scalar("x2603_x2603_dsp1_bank0")
    val x2696 = Sequential(name="x2696",parent=top) { implicit CU => 
      val x2696_unit = CounterChain(name = "x2696_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x2499_dsp0_bank0 = MemoryPipeline(name="x2499_dsp0_bank0",parent="x2696") { implicit CU => 
      val x2517 = ScalarFIFO(size=1,name="x2517").wtPort(x2502_x2512_data_s)
      val x2514 = CounterChain.copy("x2518", "x2514")
      val x2610 = CounterChain.copy("x2624_0", "x2610")
      val x2499 = SRAM(size=96,name="x2499",banking = Strided(1,16)).wtPort(x2517.readPort).rdPort(x2499_x2499_dsp0_bank0_s).rdAddr(x2610(0)).wtAddr(x2514(0))
    }
    val x2500_dsp0_bank0 = MemoryPipeline(name="x2500_dsp0_bank0",parent="x2696") { implicit CU => 
      val x2536 = ScalarFIFO(size=1,name="x2536").wtPort(x2521_x2531_data_s)
      val x2533 = CounterChain.copy("x2537", "x2533")
      val x2610 = CounterChain.copy("x2624_0", "x2610")
      val x2500 = SRAM(size=96,name="x2500",banking = Strided(1,16)).wtPort(x2536.readPort).rdPort(x2500_x2500_dsp0_bank0_s).rdAddr(x2610(0)).wtAddr(x2533(0))
    }
    val x2519 = StreamController(name="x2519",parent=x2696) { implicit CU => 
      val x2519_unit = CounterChain(name = "x2519_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x2511_0 = Pipeline(name="x2511_0",parent=x2519) { implicit CU => 
      val x2504 = CU.temp(None)
      val x2506 = ScalarBuffer(name="x2506").wtPort(mu0_da)
      val x2511_unit = CounterChain(name = "x2511_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(Const(0), Const(2)), op=FixSla, results=List(x2504))
      Stage(operands=List(x2504, CU.load(x2506)), op=FixAdd, results=List(CU.scalarOut(x2501_b2748_x2510_b2750_s)))
      Stage(operands=List(Const(384)), op=Bypass, results=List(CU.scalarOut(x2501_b2749_x2510_b2751_s)))
    }
    val x2512 = MemoryController(name="x2512",parent=x2519,offchip=mu0_oc, mctpe=TileLoad) { implicit CU => 
      val x2501_b2748 = ScalarFIFO(size=1,name="offset").wtPort(x2501_b2748_x2510_b2750_s)
      val x2501_b2749 = ScalarFIFO(size=1,name="size").wtPort(x2501_b2749_x2510_b2751_s)
      CU.newSout("data", x2502_x2512_data_s)
    }
    val x2518 = Pipeline(name="x2518",parent=x2519) { implicit CU => 
      val ctr1 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x2514 = CounterChain(name = "x2514", ctr1).iter(6)
    }
    val x2538 = StreamController(name="x2538",parent=x2696) { implicit CU => 
      val x2538_unit = CounterChain(name = "x2538_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x2530_0 = Pipeline(name="x2530_0",parent=x2538) { implicit CU => 
      val x2523 = CU.temp(None)
      val x2525 = ScalarBuffer(name="x2525").wtPort(mu1_da)
      val x2530_unit = CounterChain(name = "x2530_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(Const(0), Const(2)), op=FixSla, results=List(x2523))
      Stage(operands=List(x2523, CU.load(x2525)), op=FixAdd, results=List(CU.scalarOut(x2520_b2752_x2529_b2754_s)))
      Stage(operands=List(Const(384)), op=Bypass, results=List(CU.scalarOut(x2520_b2753_x2529_b2755_s)))
    }
    val x2531 = MemoryController(name="x2531",parent=x2538,offchip=mu1_oc, mctpe=TileLoad) { implicit CU => 
      val x2520_b2753 = ScalarFIFO(size=1,name="size").wtPort(x2520_b2753_x2529_b2755_s)
      val x2520_b2752 = ScalarFIFO(size=1,name="offset").wtPort(x2520_b2752_x2529_b2754_s)
      CU.newSout("data", x2521_x2531_data_s)
    }
    val x2537 = Pipeline(name="x2537",parent=x2538) { implicit CU => 
      val ctr2 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x2533 = CounterChain(name = "x2533", ctr2).iter(6)
    }
    val x2540_dsp0_bank0 = MemoryPipeline(name="x2540_dsp0_bank0",parent="x2666") { implicit CU => 
      val b2786 = CU.temp(None)
      val b2780 = CU.temp(None)
      val x2664 = ScalarFIFO(size=1,name="x2664").wtPort(x2540_x2664_s)
      val x2655 = CounterChain.copy("x2665_0", "x2655")
      val x2668 = CounterChain.copy("x2695", "x2668")
      val x2685 = CounterChain.copy("x2691", "x2685")
      val x2540 = SRAM(size=9216,name="x2540",banking = Strided(1,16)).wtPort(x2664.readPort).rdPort(x2540_x2540_dsp0_bank0_s)
      WAStage(operands=List(CU.ctr(x2655(0)), Const(96)), op=FixMul, results=List(b2780))
      WAStage(operands=List(b2780, CU.ctr(x2655(1))), op=FixAdd, results=List(x2540.writeAddr))
      RAStage(operands=List(CU.ctr(x2668(0)), Const(96)), op=FixMul, results=List(b2786))
      RAStage(operands=List(b2786, CU.ctr(x2685(0))), op=FixAdd, results=List(x2540.readAddr))
    }
    val x2540_dsp1_bank0 = MemoryPipeline(name="x2540_dsp1_bank0",parent="x2666") { implicit CU => 
      val b2778 = CU.temp(None)
      val b2780 = CU.temp(None)
      val x2664 = ScalarFIFO(size=1,name="x2664").wtPort(x2540_x2664_s)
      val x2655 = CounterChain.copy("x2665_0", "x2655")
      val x2540 = SRAM(size=9216,name="x2540",banking = NoBanking()).wtPort(x2664.readPort).rdPort(x2540_x2540_dsp1_bank0_s)
      WAStage(operands=List(CU.ctr(x2655(0)), Const(96)), op=FixMul, results=List(b2780))
      WAStage(operands=List(b2780, CU.ctr(x2655(1))), op=FixAdd, results=List(x2540.writeAddr))
      RAStage(operands=List(CU.ctr(x2655(0)), Const(96)), op=FixMul, results=List(b2778))
      RAStage(operands=List(b2778, CU.ctr(x2655(1))), op=FixAdd, results=List(x2540.readAddr))
    }
    val x2666 = MetaPipeline(name="x2666",parent=x2696) { implicit CU => 
      val x2486 = ScalarBuffer(name="x2486").wtPort(R_argin)
      val ctr3 = Counter(min=Const(0), max=x2486.readPort, step=Const(16), par=1) // Counter
      val x2543 = CounterChain(name = "x2543", ctr3).iter(1)
    }
    val x2544_dsp0_bank0 = MemoryPipeline(name="x2544_dsp0_bank0",parent="x2666") { implicit CU => 
      val x2566 = ScalarFIFO(size=1,name="x2566").wtPort(x2550_x2560_data_s)
      val x2562 = CounterChain.copy("x2567", "x2562")
      val x2606 = CounterChain.copy("x2652", "x2606")
      val x2544 = SRAM(size=16,name="x2544",banking = Strided(1,16)).wtPort(x2566.readPort).rdPort(x2544_x2544_dsp0_bank0_s).rdAddr(x2606(0)).wtAddr(x2562(0))
    }
    val x2545_dsp0_bank0 = MemoryPipeline(name="x2545_dsp0_bank0",parent="x2666") { implicit CU => 
      val b2764 = CU.temp(None)
      val b2766 = CU.temp(None)
      val x2594 = ScalarFIFO(size=1,name="x2594").wtPort(x2572_x2587_data_s)
      val x2570 = CounterChain.copy("x2596", "x2570")
      val x2610 = CounterChain.copy("x2624_0", "x2610")
      val x2589 = CounterChain.copy("x2595", "x2589")
      val x2606 = CounterChain.copy("x2652", "x2606")
      val x2545 = SRAM(size=1536,name="x2545",banking = Strided(1,16)).wtPort(x2594.readPort).rdPort(x2545_x2545_dsp0_bank0_s)
      WAStage(operands=List(CU.ctr(x2570(0)), Const(96)), op=FixMul, results=List(b2764))
      WAStage(operands=List(b2764, CU.ctr(x2589(0))), op=FixAdd, results=List(x2545.writeAddr))
      RAStage(operands=List(CU.ctr(x2606(0)), Const(96)), op=FixMul, results=List(b2766))
      RAStage(operands=List(b2766, CU.ctr(x2610(0))), op=FixAdd, results=List(x2545.readAddr))
    }
    val x2568 = StreamController(name="x2568",parent=x2666) { implicit CU => 
      val x2568_unit = CounterChain(name = "x2568_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x2559_0 = Pipeline(name="x2559_0",parent=x2568) { implicit CU => 
      val x2552 = CU.temp(None)
      val x2554 = ScalarBuffer(name="x2554").wtPort(y_da)
      val x2543 = CounterChain.copy("x2666", "x2543")
      val x2559_unit = CounterChain(name = "x2559_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x2543(0)), Const(2)), op=FixSla, results=List(x2552))
      Stage(operands=List(x2552, CU.load(x2554)), op=FixAdd, results=List(CU.scalarOut(x2549_b2756_x2558_b2758_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x2549_b2757_x2558_b2759_s)))
    }
    val x2560 = MemoryController(name="x2560",parent=x2568,offchip=y_oc, mctpe=TileLoad) { implicit CU => 
      val x2549_b2757 = ScalarFIFO(size=1,name="size").wtPort(x2549_b2757_x2558_b2759_s)
      val x2549_b2756 = ScalarFIFO(size=1,name="offset").wtPort(x2549_b2756_x2558_b2758_s)
      CU.newSout("data", x2550_x2560_data_s)
    }
    val x2567 = Pipeline(name="x2567",parent=x2568) { implicit CU => 
      val ctr4 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x2562 = CounterChain(name = "x2562", ctr4).iter(1)
    }
    val x2596 = StreamController(name="x2596",parent=x2666) { implicit CU => 
      val ctr5 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x2570 = CounterChain(name = "x2570", ctr5).iter(16)
    }
    val x2586_0 = Pipeline(name="x2586_0",parent=x2596) { implicit CU => 
      val x2577 = CU.temp(None)
      val x2573 = CU.temp(None)
      val x2578 = CU.temp(None)
      val x2575 = CU.temp(None)
      val x2580 = ScalarBuffer(name="x2580").wtPort(x_da)
      val x2543 = CounterChain.copy("x2666", "x2543")
      val x2570 = CounterChain.copy("x2596", "x2570")
      val x2586_unit = CounterChain(name = "x2586_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x2543(0)), CU.ctr(x2570(0))), op=FixAdd, results=List(x2573))
      Stage(operands=List(x2573, Const(96)), op=FixMul, results=List(x2575))
      Stage(operands=List(x2575, Const(0)), op=FixAdd, results=List(x2577))
      Stage(operands=List(x2577, Const(2)), op=FixSla, results=List(x2578))
      Stage(operands=List(x2578, CU.load(x2580)), op=FixAdd, results=List(CU.scalarOut(x2571_b2760_x2585_b2762_s)))
      Stage(operands=List(Const(384)), op=Bypass, results=List(CU.scalarOut(x2571_b2761_x2585_b2763_s)))
    }
    val x2587 = MemoryController(name="x2587",parent=x2596,offchip=x_oc, mctpe=TileLoad) { implicit CU => 
      val x2571_b2761 = ScalarFIFO(size=1,name="size").wtPort(x2571_b2761_x2585_b2763_s)
      val x2571_b2760 = ScalarFIFO(size=1,name="offset").wtPort(x2571_b2760_x2585_b2762_s)
      CU.newSout("data", x2572_x2587_data_s)
    }
    val x2595 = Pipeline(name="x2595",parent=x2596) { implicit CU => 
      val ctr6 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x2589 = CounterChain(name = "x2589", ctr6).iter(6)
    }
    val x2601_0 = Pipeline(name="x2601_0",parent=x2666) { implicit CU => 
      val x2598 = CU.temp(None)
      val x2486 = ScalarBuffer(name="x2486").wtPort(R_argin)
      val x2543 = CounterChain.copy("x2666", "x2543")
      val x2601_unit = CounterChain(name = "x2601_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x2486), CU.ctr(x2543(0))), op=FixSub, results=List(x2598))
      Stage(operands=List(x2598, Const(16)), op=FixMin, results=List(CU.scalarOut(x2546_x2600_s)))
    }
    val x2603_dsp0_bank0 = MemoryPipeline(name="x2603_dsp0_bank0",parent="x2652") { implicit CU => 
      val b2772 = CU.temp(None)
      val b2774 = CU.temp(None)
      val x2650 = ScalarFIFO(size=1,name="x2650").wtPort(x2603_x2650_s)
      val x2639 = CounterChain.copy("x2651_0", "x2639")
      val x2603 = SRAM(size=9216,name="x2603",banking = NoBanking()).wtPort(x2650.readPort).rdPort(x2603_x2603_dsp0_bank0_s)
      WAStage(operands=List(CU.ctr(x2639(0)), Const(96)), op=FixMul, results=List(b2774))
      WAStage(operands=List(b2774, CU.ctr(x2639(1))), op=FixAdd, results=List(x2603.writeAddr))
      RAStage(operands=List(CU.ctr(x2639(0)), Const(96)), op=FixMul, results=List(b2772))
      RAStage(operands=List(b2772, CU.ctr(x2639(1))), op=FixAdd, results=List(x2603.readAddr))
    }
    val x2603_dsp1_bank0 = MemoryPipeline(name="x2603_dsp1_bank0",parent="x2652") { implicit CU => 
      val b2776 = CU.temp(None)
      val b2774 = CU.temp(None)
      val x2650 = ScalarFIFO(size=1,name="x2650").wtPort(x2603_x2650_s)
      val x2639 = CounterChain.copy("x2651_0", "x2639")
      val x2655 = CounterChain.copy("x2665_0", "x2655")
      val x2603 = SRAM(size=9216,name="x2603",banking = NoBanking()).wtPort(x2650.readPort).rdPort(x2603_x2603_dsp1_bank0_s)
      WAStage(operands=List(CU.ctr(x2639(0)), Const(96)), op=FixMul, results=List(b2774))
      WAStage(operands=List(b2774, CU.ctr(x2639(1))), op=FixAdd, results=List(x2603.writeAddr))
      RAStage(operands=List(CU.ctr(x2655(0)), Const(96)), op=FixMul, results=List(b2776))
      RAStage(operands=List(b2776, CU.ctr(x2655(1))), op=FixAdd, results=List(x2603.readAddr))
    }
    val x2652 = MetaPipeline(name="x2652",parent=x2666) { implicit CU => 
      val x2546 = ScalarBuffer(name="x2546").wtPort(x2546_x2600_s)
      val ctr7 = Counter(min=Const(0), max=x2546.readPort, step=Const(1), par=1) // Counter
      val x2606 = CounterChain(name = "x2606", ctr7).iter(1)
    }
    val x2607_dsp0_bank0 = MemoryPipeline(name="x2607_dsp0_bank0",parent="x2652") { implicit CU => 
      val x2623 = ScalarFIFO(size=1,name="x2623").wtPort(x2607_x2623_s)
      val x2610 = CounterChain.copy("x2624_0", "x2610")
      val x2627 = CounterChain.copy("x2636_0", "x2627")
      val x2607 = SRAM(size=96,name="x2607",banking = Strided(1,16)).wtPort(x2623.readPort).rdPort(x2607_x2607_dsp0_bank0_s).rdAddr(x2627(0)).wtAddr(x2610(0))
    }
    val x2607_dsp1_bank0 = MemoryPipeline(name="x2607_dsp1_bank0",parent="x2652") { implicit CU => 
      val x2623 = ScalarFIFO(size=1,name="x2623").wtPort(x2607_x2623_s)
      val x2610 = CounterChain.copy("x2624_0", "x2610")
      val x2627 = CounterChain.copy("x2636_0", "x2627")
      val x2607 = SRAM(size=96,name="x2607",banking = Strided(1,16)).wtPort(x2623.readPort).rdPort(x2607_x2607_dsp1_bank0_s).rdAddr(x2627(1)).wtAddr(x2610(0))
    }
    val x2608_dsp0_bank0 = MemoryPipeline(name="x2608_dsp0_bank0",parent="x2652") { implicit CU => 
      val b2770 = CU.temp(None)
      val b2768 = CU.temp(None)
      val x2635 = ScalarFIFO(size=1,name="x2635").wtPort(x2608_x2635_s)
      val x2627 = CounterChain.copy("x2636_0", "x2627")
      val x2639 = CounterChain.copy("x2651_0", "x2639")
      val x2608 = SRAM(size=9216,name="x2608",banking = Strided(1,16)).wtPort(x2635.readPort).rdPort(x2608_x2608_dsp0_bank0_s)
      WAStage(operands=List(CU.ctr(x2627(0)), Const(96)), op=FixMul, results=List(b2768))
      WAStage(operands=List(b2768, CU.ctr(x2627(1))), op=FixAdd, results=List(x2608.writeAddr))
      RAStage(operands=List(CU.ctr(x2639(0)), Const(96)), op=FixMul, results=List(b2770))
      RAStage(operands=List(b2770, CU.ctr(x2639(1))), op=FixAdd, results=List(x2608.readAddr))
    }
    val x2624_0 = Pipeline(name="x2624_0",parent=x2652) { implicit CU => 
      val x2621 = CU.temp(None)
      val x2616 = CU.temp(None)
      val x2619 = ScalarFIFO(size=1,name="x2619").wtPort(x2499_x2499_dsp0_bank0_s)
      val x2613 = ScalarFIFO(size=1,name="x2613").wtPort(x2545_x2545_dsp0_bank0_s)
      val x2615 = ScalarFIFO(size=1,name="x2615").wtPort(x2544_x2544_dsp0_bank0_s)
      val x2617 = ScalarFIFO(size=1,name="x2617").wtPort(x2500_x2500_dsp0_bank0_s)
      val ctr8 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x2610 = CounterChain(name = "x2610", ctr8).iter(6)
      Stage(operands=List(CU.load(x2615), Const(1)), op=FixEql, results=List(x2616))
      Stage(operands=List(x2616, CU.load(x2617), CU.load(x2619)), op=Mux, results=List(x2621))
      Stage(operands=List(CU.load(x2613), x2621), op=FixSub, results=List(CU.scalarOut(x2607_x2623_s)))
    }
    val x2636_0 = Pipeline(name="x2636_0",parent=x2652) { implicit CU => 
      val x2631 = ScalarFIFO(size=1,name="x2631").wtPort(x2607_x2607_dsp0_bank0_s)
      val x2632 = ScalarFIFO(size=1,name="x2632").wtPort(x2607_x2607_dsp1_bank0_s)
      val ctr9 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val ctr10 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x2627 = CounterChain(name = "x2627", ctr9, ctr10).iter(576)
      Stage(operands=List(CU.load(x2631), CU.load(x2632)), op=FixMul, results=List(CU.scalarOut(x2608_x2635_s)))
    }
    val x2651_0 = Pipeline(name="x2651_0",parent=x2652) { implicit CU => 
      val x2642 = ScalarFIFO(size=1,name="x2642").wtPort(x2608_x2608_dsp0_bank0_s)
      val x2644 = ScalarFIFO(size=1,name="x2644").wtPort(x2603_x2603_dsp0_bank0_s)
      val ctr11 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val ctr12 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val x2639 = CounterChain(name = "x2639", ctr11, ctr12).iter(9216)
      Stage(operands=List(CU.load(x2642), CU.load(x2644)), op=FixAdd, results=List(CU.scalarOut(x2603_x2650_s)))
    }
    val x2665_0 = Pipeline(name="x2665_0",parent=x2666) { implicit CU => 
      val x2657 = ScalarFIFO(size=1,name="x2657").wtPort(x2603_x2603_dsp1_bank0_s)
      val x2659 = ScalarFIFO(size=1,name="x2659").wtPort(x2540_x2540_dsp1_bank0_s)
      val ctr13 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val ctr14 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val x2655 = CounterChain(name = "x2655", ctr13, ctr14).iter(9216)
      Stage(operands=List(CU.load(x2657), CU.load(x2659)), op=FixAdd, results=List(CU.scalarOut(x2540_x2664_s)))
    }
    val x2695 = StreamController(name="x2695",parent=x2696) { implicit CU => 
      val ctr15 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val x2668 = CounterChain(name = "x2668", ctr15).iter(96)
    }
    val x2683_0 = Pipeline(name="x2683_0",parent=x2695) { implicit CU => 
      val x2673 = CU.temp(None)
      val x2675 = CU.temp(None)
      val x2676 = CU.temp(None)
      val x2678 = ScalarBuffer(name="x2678").wtPort(sigma_da)
      val x2668 = CounterChain.copy("x2695", "x2668")
      val x2683_unit = CounterChain(name = "x2683_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x2668(0)), Const(96)), op=FixMul, results=List(x2673))
      Stage(operands=List(x2673, Const(0)), op=FixAdd, results=List(x2675))
      Stage(operands=List(x2675, Const(2)), op=FixSla, results=List(x2676))
      Stage(operands=List(x2676, CU.load(x2678)), op=FixAdd, results=List(CU.scalarOut(x2669_b2782_x2682_b2784_s)))
      Stage(operands=List(Const(384)), op=Bypass, results=List(CU.scalarOut(x2669_b2783_x2682_b2785_s)))
    }
    val x2691 = Pipeline(name="x2691",parent=x2695) { implicit CU => 
      val ctr16 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x2685 = CounterChain(name = "x2685", ctr16).iter(6)
    }
    val x2692 = MemoryController(name="x2692",parent=x2695,offchip=sigma_oc, mctpe=TileStore) { implicit CU => 
      val x2670 = ScalarFIFO(size=1,name="data").wtPort(x2540_x2540_dsp0_bank0_s)
      val x2669_b2783 = ScalarFIFO(size=1,name="size").wtPort(x2669_b2783_x2682_b2785_s)
      val x2669_b2782 = ScalarFIFO(size=1,name="offset").wtPort(x2669_b2782_x2682_b2784_s)
    }
    
  }
}
