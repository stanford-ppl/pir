import pir._
import pir.node._
import arch._
import pirc.enums._

object TPCHQ6 extends PIRApp {
  def main(top:Top) = {
    val discts_oc = OffChip("discts")
    val x2644_b2851_x2653_b2853_s = Scalar("x2644_b2851_x2653_b2853")
    val x2625_x2635_data_s = Scalar("x2625_x2635_data")
    val x2582_x2582_dsp0_bank0_data_s = Scalar("x2582_x2582_dsp0_bank0_data")
    val x2576_x2601_wa_s = Scalar("x2576_x2601_wa")
    val x2583_x2778_ra_s = Scalar("x2583_x2778_ra")
    val x2579_x2701_wa_s = Scalar("x2579_x2701_wa")
    val x2581_x2776_ra_s = Scalar("x2581_x2776_ra")
    val x2580_x2580_dsp0_bank0_data_s = Scalar("x2580_x2580_dsp0_bank0_data")
    val x2581_x2721_wa_s = Scalar("x2581_x2721_wa")
    val x2579_x2579_dsp0_bank0_data_s = Scalar("x2579_x2579_dsp0_bank0_data")
    val x2605_x2615_data_s = Scalar("x2605_x2615_data")
    val x2664_b2857_x2673_b2859_s = Scalar("x2664_b2857_x2673_b2859")
    val x2583_x2583_dsp0_bank0_data_s = Scalar("x2583_x2583_dsp0_bank0_data")
    val x2704_b2867_x2713_b2869_s = Scalar("x2704_b2867_x2713_b2869")
    val dataSize_argin = ArgIn("dataSize")
    val x2724_b2871_x2733_b2873_s = Scalar("x2724_b2871_x2733_b2873")
    val x2724_b2872_x2733_b2874_s = Scalar("x2724_b2872_x2733_b2874")
    val x2576_x2749_ra_s = Scalar("x2576_x2749_ra")
    val x2580_x2750_ra_s = Scalar("x2580_x2750_ra")
    val x2577_x2681_wa_s = Scalar("x2577_x2681_wa")
    val x2578_x2578_dsp0_bank0_data_s = Scalar("x2578_x2578_dsp0_bank0_data")
    val x2745_x2796_s = Scalar("x2745_x2796")
    val x2583_x2741_wa_s = Scalar("x2583_x2741_wa")
    val x2577_x2775_ra_s = Scalar("x2577_x2775_ra")
    val x2585_x2595_data_s = Scalar("x2585_x2595_data")
    val x2576_x2576_dsp0_bank0_data_s = Scalar("x2576_x2576_dsp0_bank0_data")
    val x2664_b2856_x2673_b2858_s = Scalar("x2664_b2856_x2673_b2858")
    val prices_oc = OffChip("prices")
    val x2644_b2852_x2653_b2854_s = Scalar("x2644_b2852_x2653_b2854")
    val x2581_x2581_dsp0_bank0_data_s = Scalar("x2581_x2581_dsp0_bank0_data")
    val quants_oc = OffChip("quants")
    val x2604_b2842_x2613_b2844_s = Scalar("x2604_b2842_x2613_b2844")
    val x2578_x2751_ra_s = Scalar("x2578_x2751_ra")
    val x2685_x2695_data_s = Scalar("x2685_x2695_data")
    val x2725_x2735_data_s = Scalar("x2725_x2735_data")
    val quants_da = DRAMAddress("quants", "quants")
    val discts_da = DRAMAddress("discts", "discts")
    val x2705_x2715_data_s = Scalar("x2705_x2715_data")
    val prices_da = DRAMAddress("prices", "prices")
    val x2684_b2861_x2693_b2863_s = Scalar("x2684_b2861_x2693_b2863")
    val x2582_x2752_ra_s = Scalar("x2582_x2752_ra")
    val x2579_x2777_ra_s = Scalar("x2579_x2777_ra")
    val dates_da = DRAMAddress("dates", "dates")
    val x2645_x2655_data_s = Scalar("x2645_x2655_data")
    val x2665_x2675_data_s = Scalar("x2665_x2675_data")
    val x2584_b2836_x2593_b2838_s = Scalar("x2584_b2836_x2593_b2838")
    val x2704_b2866_x2713_b2868_s = Scalar("x2704_b2866_x2713_b2868")
    val x2604_b2841_x2613_b2843_s = Scalar("x2604_b2841_x2613_b2843")
    val dates_oc = OffChip("dates")
    val x2582_x2661_wa_s = Scalar("x2582_x2661_wa")
    val x2684_b2862_x2693_b2864_s = Scalar("x2684_b2862_x2693_b2864")
    val x2744_x2770_s = Scalar("x2744_x2770")
    val x2577_x2577_dsp0_bank0_data_s = Scalar("x2577_x2577_dsp0_bank0_data")
    val x2624_b2847_x2633_b2849_s = Scalar("x2624_b2847_x2633_b2849")
    val x2624_b2846_x2633_b2848_s = Scalar("x2624_b2846_x2633_b2848")
    val x2578_x2621_wa_s = Scalar("x2578_x2621_wa")
    val x2580_x2641_wa_s = Scalar("x2580_x2641_wa")
    val x2567_x2810_argout = ArgOut("x2567_x2810")
    val x2584_b2837_x2593_b2839_s = Scalar("x2584_b2837_x2593_b2839")
    val x2812 = Sequential(name="x2812",parent=top) { implicit CU => 
      val x2812_unit = CounterChain(name = "x2812_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x2808 = MetaPipeline(name="x2808",parent=x2812) { implicit CU => 
      val x2557 = ScalarBuffer(name="x2557").wtPort(dataSize_argin)
      val ctr1 = Counter(min=Const(0), max=x2557.readPort, step=Const(384), par=2) // Counter
      val x2575 = CounterChain(name = "x2575", ctr1).iter(1)
    }
    val x2576_dsp0_bank0 = MemoryPipeline(name="x2576_dsp0_bank0",parent="x2808") { implicit CU => 
      val x2601 = ScalarFIFO(size=1,name="x2601").wtPort(x2585_x2595_data_s)
      val b2840 = ScalarFIFO(size=1,name="b2840").wtPort(x2576_x2601_wa_s)
      val b2876 = ScalarFIFO(size=1,name="b2876").wtPort(x2576_x2749_ra_s)
      val x2576 = SRAM(size=24,name="x2576",banking = Strided(1,16)).wtPort(x2601.readPort).rdPort(x2576_x2576_dsp0_bank0_data_s).rdAddr(b2876.readPort).wtAddr(b2840.readPort)
    }
    val x2577_dsp0_bank0 = MemoryPipeline(name="x2577_dsp0_bank0",parent="x2808") { implicit CU => 
      val x2681 = ScalarFIFO(size=1,name="x2681").wtPort(x2665_x2675_data_s)
      val b2860 = ScalarFIFO(size=1,name="b2860").wtPort(x2577_x2681_wa_s)
      val b2880 = ScalarFIFO(size=1,name="b2880").wtPort(x2577_x2775_ra_s)
      val x2577 = SRAM(size=24,name="x2577",banking = Strided(1,16)).wtPort(x2681.readPort).rdPort(x2577_x2577_dsp0_bank0_data_s).rdAddr(b2880.readPort).wtAddr(b2860.readPort)
    }
    val x2578_dsp0_bank0 = MemoryPipeline(name="x2578_dsp0_bank0",parent="x2808") { implicit CU => 
      val x2621 = ScalarFIFO(size=1,name="x2621").wtPort(x2605_x2615_data_s)
      val b2878 = ScalarFIFO(size=1,name="b2878").wtPort(x2578_x2751_ra_s)
      val b2845 = ScalarFIFO(size=1,name="b2845").wtPort(x2578_x2621_wa_s)
      val x2578 = SRAM(size=24,name="x2578",banking = Strided(1,16)).wtPort(x2621.readPort).rdPort(x2578_x2578_dsp0_bank0_data_s).rdAddr(b2878.readPort).wtAddr(b2845.readPort)
    }
    val x2579_dsp0_bank0 = MemoryPipeline(name="x2579_dsp0_bank0",parent="x2808") { implicit CU => 
      val b2882 = ScalarFIFO(size=1,name="b2882").wtPort(x2579_x2777_ra_s)
      val x2701 = ScalarFIFO(size=1,name="x2701").wtPort(x2685_x2695_data_s)
      val b2865 = ScalarFIFO(size=1,name="b2865").wtPort(x2579_x2701_wa_s)
      val x2579 = SRAM(size=24,name="x2579",banking = Strided(1,16)).wtPort(x2701.readPort).rdPort(x2579_x2579_dsp0_bank0_data_s).rdAddr(b2882.readPort).wtAddr(b2865.readPort)
    }
    val x2580_dsp0_bank0 = MemoryPipeline(name="x2580_dsp0_bank0",parent="x2808") { implicit CU => 
      val x2641 = ScalarFIFO(size=1,name="x2641").wtPort(x2625_x2635_data_s)
      val b2850 = ScalarFIFO(size=1,name="b2850").wtPort(x2580_x2641_wa_s)
      val b2877 = ScalarFIFO(size=1,name="b2877").wtPort(x2580_x2750_ra_s)
      val x2580 = SRAM(size=24,name="x2580",banking = Strided(1,16)).wtPort(x2641.readPort).rdPort(x2580_x2580_dsp0_bank0_data_s).rdAddr(b2877.readPort).wtAddr(b2850.readPort)
    }
    val x2581_dsp0_bank0 = MemoryPipeline(name="x2581_dsp0_bank0",parent="x2808") { implicit CU => 
      val x2721 = ScalarFIFO(size=1,name="x2721").wtPort(x2705_x2715_data_s)
      val b2870 = ScalarFIFO(size=1,name="b2870").wtPort(x2581_x2721_wa_s)
      val b2881 = ScalarFIFO(size=1,name="b2881").wtPort(x2581_x2776_ra_s)
      val x2581 = SRAM(size=24,name="x2581",banking = Strided(1,16)).wtPort(x2721.readPort).rdPort(x2581_x2581_dsp0_bank0_data_s).rdAddr(b2881.readPort).wtAddr(b2870.readPort)
    }
    val x2582_dsp0_bank0 = MemoryPipeline(name="x2582_dsp0_bank0",parent="x2808") { implicit CU => 
      val x2661 = ScalarFIFO(size=1,name="x2661").wtPort(x2645_x2655_data_s)
      val b2879 = ScalarFIFO(size=1,name="b2879").wtPort(x2582_x2752_ra_s)
      val b2855 = ScalarFIFO(size=1,name="b2855").wtPort(x2582_x2661_wa_s)
      val x2582 = SRAM(size=24,name="x2582",banking = Strided(1,16)).wtPort(x2661.readPort).rdPort(x2582_x2582_dsp0_bank0_data_s).rdAddr(b2879.readPort).wtAddr(b2855.readPort)
    }
    val x2583_dsp0_bank0 = MemoryPipeline(name="x2583_dsp0_bank0",parent="x2808") { implicit CU => 
      val x2741 = ScalarFIFO(size=1,name="x2741").wtPort(x2725_x2735_data_s)
      val b2875 = ScalarFIFO(size=1,name="b2875").wtPort(x2583_x2741_wa_s)
      val b2883 = ScalarFIFO(size=1,name="b2883").wtPort(x2583_x2778_ra_s)
      val x2583 = SRAM(size=24,name="x2583",banking = Strided(1,16)).wtPort(x2741.readPort).rdPort(x2583_x2583_dsp0_bank0_data_s).rdAddr(b2883.readPort).wtAddr(b2875.readPort)
    }
    val x2603 = StreamController(name="x2603",parent=x2808) { implicit CU => 
      val x2603_unit = CounterChain(name = "x2603_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x2594 = Pipeline(name="x2594",parent=x2603) { implicit CU => 
      val x2587 = CU.temp(None)
      val x2589 = ScalarBuffer(name="x2589").wtPort(dates_da)
      val x2575 = CounterChain.copy("x2808", "x2575").iterIdx(0, 0)
      val x2594_unit = CounterChain(name = "x2594_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x2575(0)), Const(2)), op=FixSla, results=List(x2587))
      Stage(operands=List(x2587, CU.load(x2589)), op=FixAdd, results=List(CU.scalarOut(x2584_b2836_x2593_b2838_s)))
      Stage(operands=List(Const(1536)), op=Bypass, results=List(CU.scalarOut(x2584_b2837_x2593_b2839_s)))
    }
    val x2595 = MemoryController(name="x2595",parent=x2603,offchip=dates_oc, mctpe=TileLoad) { implicit CU => 
      val x2584_b2837 = ScalarFIFO(size=1,name="size").wtPort(x2584_b2837_x2593_b2839_s)
      val x2584_b2836 = ScalarFIFO(size=1,name="offset").wtPort(x2584_b2836_x2593_b2838_s)
      CU.newSout("data", x2585_x2595_data_s)
    }
    val x2602 = Pipeline(name="x2602",parent=x2603) { implicit CU => 
      val ctr2 = Counter(min=Const(0), max=Const(384), step=Const(1), par=16) // Counter
      val x2597 = CounterChain(name = "x2597", ctr2).iter(24)
    }
    val x2601 = Pipeline(name="x2601",parent=x2603) { implicit CU => 
      val x2597 = CounterChain.copy("x2602", "x2597").iterIdx(0, 0)
      Stage(operands=List(CU.ctr(x2597(0))), op=Bypass, results=List(CU.scalarOut(x2576_x2601_wa_s)))
    }
    val x2623 = StreamController(name="x2623",parent=x2808) { implicit CU => 
      val x2623_unit = CounterChain(name = "x2623_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x2614 = Pipeline(name="x2614",parent=x2623) { implicit CU => 
      val x2607 = CU.temp(None)
      val x2609 = ScalarBuffer(name="x2609").wtPort(quants_da)
      val x2575 = CounterChain.copy("x2808", "x2575").iterIdx(0, 0)
      val x2614_unit = CounterChain(name = "x2614_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x2575(0)), Const(2)), op=FixSla, results=List(x2607))
      Stage(operands=List(x2607, CU.load(x2609)), op=FixAdd, results=List(CU.scalarOut(x2604_b2841_x2613_b2843_s)))
      Stage(operands=List(Const(1536)), op=Bypass, results=List(CU.scalarOut(x2604_b2842_x2613_b2844_s)))
    }
    val x2615 = MemoryController(name="x2615",parent=x2623,offchip=quants_oc, mctpe=TileLoad) { implicit CU => 
      val x2604_b2842 = ScalarFIFO(size=1,name="size").wtPort(x2604_b2842_x2613_b2844_s)
      val x2604_b2841 = ScalarFIFO(size=1,name="offset").wtPort(x2604_b2841_x2613_b2843_s)
      CU.newSout("data", x2605_x2615_data_s)
    }
    val x2622 = Pipeline(name="x2622",parent=x2623) { implicit CU => 
      val ctr3 = Counter(min=Const(0), max=Const(384), step=Const(1), par=16) // Counter
      val x2617 = CounterChain(name = "x2617", ctr3).iter(24)
    }
    val x2621 = Pipeline(name="x2621",parent=x2623) { implicit CU => 
      val x2617 = CounterChain.copy("x2622", "x2617").iterIdx(0, 0)
      Stage(operands=List(CU.ctr(x2617(0))), op=Bypass, results=List(CU.scalarOut(x2578_x2621_wa_s)))
    }
    val x2643 = StreamController(name="x2643",parent=x2808) { implicit CU => 
      val x2643_unit = CounterChain(name = "x2643_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x2634 = Pipeline(name="x2634",parent=x2643) { implicit CU => 
      val x2627 = CU.temp(None)
      val x2629 = ScalarBuffer(name="x2629").wtPort(discts_da)
      val x2575 = CounterChain.copy("x2808", "x2575").iterIdx(0, 0)
      val x2634_unit = CounterChain(name = "x2634_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x2575(0)), Const(2)), op=FixSla, results=List(x2627))
      Stage(operands=List(x2627, CU.load(x2629)), op=FixAdd, results=List(CU.scalarOut(x2624_b2846_x2633_b2848_s)))
      Stage(operands=List(Const(1536)), op=Bypass, results=List(CU.scalarOut(x2624_b2847_x2633_b2849_s)))
    }
    val x2635 = MemoryController(name="x2635",parent=x2643,offchip=discts_oc, mctpe=TileLoad) { implicit CU => 
      val x2624_b2846 = ScalarFIFO(size=1,name="offset").wtPort(x2624_b2846_x2633_b2848_s)
      val x2624_b2847 = ScalarFIFO(size=1,name="size").wtPort(x2624_b2847_x2633_b2849_s)
      CU.newSout("data", x2625_x2635_data_s)
    }
    val x2642 = Pipeline(name="x2642",parent=x2643) { implicit CU => 
      val ctr4 = Counter(min=Const(0), max=Const(384), step=Const(1), par=16) // Counter
      val x2637 = CounterChain(name = "x2637", ctr4).iter(24)
    }
    val x2641 = Pipeline(name="x2641",parent=x2643) { implicit CU => 
      val x2637 = CounterChain.copy("x2642", "x2637").iterIdx(0, 0)
      Stage(operands=List(CU.ctr(x2637(0))), op=Bypass, results=List(CU.scalarOut(x2580_x2641_wa_s)))
    }
    val x2663 = StreamController(name="x2663",parent=x2808) { implicit CU => 
      val x2663_unit = CounterChain(name = "x2663_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x2654 = Pipeline(name="x2654",parent=x2663) { implicit CU => 
      val x2647 = CU.temp(None)
      val x2649 = ScalarBuffer(name="x2649").wtPort(prices_da)
      val x2575 = CounterChain.copy("x2808", "x2575").iterIdx(0, 0)
      val x2654_unit = CounterChain(name = "x2654_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x2575(0)), Const(2)), op=FixSla, results=List(x2647))
      Stage(operands=List(x2647, CU.load(x2649)), op=FixAdd, results=List(CU.scalarOut(x2644_b2851_x2653_b2853_s)))
      Stage(operands=List(Const(1536)), op=Bypass, results=List(CU.scalarOut(x2644_b2852_x2653_b2854_s)))
    }
    val x2655 = MemoryController(name="x2655",parent=x2663,offchip=prices_oc, mctpe=TileLoad) { implicit CU => 
      val x2644_b2852 = ScalarFIFO(size=1,name="size").wtPort(x2644_b2852_x2653_b2854_s)
      val x2644_b2851 = ScalarFIFO(size=1,name="offset").wtPort(x2644_b2851_x2653_b2853_s)
      CU.newSout("data", x2645_x2655_data_s)
    }
    val x2662 = Pipeline(name="x2662",parent=x2663) { implicit CU => 
      val ctr5 = Counter(min=Const(0), max=Const(384), step=Const(1), par=16) // Counter
      val x2657 = CounterChain(name = "x2657", ctr5).iter(24)
    }
    val x2661 = Pipeline(name="x2661",parent=x2663) { implicit CU => 
      val x2657 = CounterChain.copy("x2662", "x2657").iterIdx(0, 0)
      Stage(operands=List(CU.ctr(x2657(0))), op=Bypass, results=List(CU.scalarOut(x2582_x2661_wa_s)))
    }
    val x2683 = StreamController(name="x2683",parent=x2808) { implicit CU => 
      val x2683_unit = CounterChain(name = "x2683_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x2674 = Pipeline(name="x2674",parent=x2683) { implicit CU => 
      val x2667 = CU.temp(None)
      val x2669 = ScalarBuffer(name="x2669").wtPort(dates_da)
      val x2575 = CounterChain.copy("x2808", "x2575").iterIdx(0, 1)
      val x2674_unit = CounterChain(name = "x2674_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x2575(0)), Const(2)), op=FixSla, results=List(x2667))
      Stage(operands=List(x2667, CU.load(x2669)), op=FixAdd, results=List(CU.scalarOut(x2664_b2856_x2673_b2858_s)))
      Stage(operands=List(Const(1536)), op=Bypass, results=List(CU.scalarOut(x2664_b2857_x2673_b2859_s)))
    }
    val x2675 = MemoryController(name="x2675",parent=x2683,offchip=dates_oc, mctpe=TileLoad) { implicit CU => 
      val x2664_b2857 = ScalarFIFO(size=1,name="size").wtPort(x2664_b2857_x2673_b2859_s)
      val x2664_b2856 = ScalarFIFO(size=1,name="offset").wtPort(x2664_b2856_x2673_b2858_s)
      CU.newSout("data", x2665_x2675_data_s)
    }
    val x2682 = Pipeline(name="x2682",parent=x2683) { implicit CU => 
      val ctr6 = Counter(min=Const(0), max=Const(384), step=Const(1), par=16) // Counter
      val x2677 = CounterChain(name = "x2677", ctr6).iter(24)
    }
    val x2681 = Pipeline(name="x2681",parent=x2683) { implicit CU => 
      val x2677 = CounterChain.copy("x2682", "x2677").iterIdx(0, 0)
      Stage(operands=List(CU.ctr(x2677(0))), op=Bypass, results=List(CU.scalarOut(x2577_x2681_wa_s)))
    }
    val x2703 = StreamController(name="x2703",parent=x2808) { implicit CU => 
      val x2703_unit = CounterChain(name = "x2703_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x2694 = Pipeline(name="x2694",parent=x2703) { implicit CU => 
      val x2687 = CU.temp(None)
      val x2689 = ScalarBuffer(name="x2689").wtPort(quants_da)
      val x2575 = CounterChain.copy("x2808", "x2575").iterIdx(0, 1)
      val x2694_unit = CounterChain(name = "x2694_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x2575(0)), Const(2)), op=FixSla, results=List(x2687))
      Stage(operands=List(x2687, CU.load(x2689)), op=FixAdd, results=List(CU.scalarOut(x2684_b2861_x2693_b2863_s)))
      Stage(operands=List(Const(1536)), op=Bypass, results=List(CU.scalarOut(x2684_b2862_x2693_b2864_s)))
    }
    val x2695 = MemoryController(name="x2695",parent=x2703,offchip=quants_oc, mctpe=TileLoad) { implicit CU => 
      val x2684_b2861 = ScalarFIFO(size=1,name="offset").wtPort(x2684_b2861_x2693_b2863_s)
      val x2684_b2862 = ScalarFIFO(size=1,name="size").wtPort(x2684_b2862_x2693_b2864_s)
      CU.newSout("data", x2685_x2695_data_s)
    }
    val x2702 = Pipeline(name="x2702",parent=x2703) { implicit CU => 
      val ctr7 = Counter(min=Const(0), max=Const(384), step=Const(1), par=16) // Counter
      val x2697 = CounterChain(name = "x2697", ctr7).iter(24)
    }
    val x2701 = Pipeline(name="x2701",parent=x2703) { implicit CU => 
      val x2697 = CounterChain.copy("x2702", "x2697").iterIdx(0, 0)
      Stage(operands=List(CU.ctr(x2697(0))), op=Bypass, results=List(CU.scalarOut(x2579_x2701_wa_s)))
    }
    val x2723 = StreamController(name="x2723",parent=x2808) { implicit CU => 
      val x2723_unit = CounterChain(name = "x2723_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x2714 = Pipeline(name="x2714",parent=x2723) { implicit CU => 
      val x2707 = CU.temp(None)
      val x2709 = ScalarBuffer(name="x2709").wtPort(discts_da)
      val x2575 = CounterChain.copy("x2808", "x2575").iterIdx(0, 1)
      val x2714_unit = CounterChain(name = "x2714_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x2575(0)), Const(2)), op=FixSla, results=List(x2707))
      Stage(operands=List(x2707, CU.load(x2709)), op=FixAdd, results=List(CU.scalarOut(x2704_b2866_x2713_b2868_s)))
      Stage(operands=List(Const(1536)), op=Bypass, results=List(CU.scalarOut(x2704_b2867_x2713_b2869_s)))
    }
    val x2715 = MemoryController(name="x2715",parent=x2723,offchip=discts_oc, mctpe=TileLoad) { implicit CU => 
      val x2704_b2867 = ScalarFIFO(size=1,name="size").wtPort(x2704_b2867_x2713_b2869_s)
      val x2704_b2866 = ScalarFIFO(size=1,name="offset").wtPort(x2704_b2866_x2713_b2868_s)
      CU.newSout("data", x2705_x2715_data_s)
    }
    val x2722 = Pipeline(name="x2722",parent=x2723) { implicit CU => 
      val ctr8 = Counter(min=Const(0), max=Const(384), step=Const(1), par=16) // Counter
      val x2717 = CounterChain(name = "x2717", ctr8).iter(24)
    }
    val x2721 = Pipeline(name="x2721",parent=x2723) { implicit CU => 
      val x2717 = CounterChain.copy("x2722", "x2717").iterIdx(0, 0)
      Stage(operands=List(CU.ctr(x2717(0))), op=Bypass, results=List(CU.scalarOut(x2581_x2721_wa_s)))
    }
    val x2743 = StreamController(name="x2743",parent=x2808) { implicit CU => 
      val x2743_unit = CounterChain(name = "x2743_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x2734 = Pipeline(name="x2734",parent=x2743) { implicit CU => 
      val x2727 = CU.temp(None)
      val x2729 = ScalarBuffer(name="x2729").wtPort(prices_da)
      val x2575 = CounterChain.copy("x2808", "x2575").iterIdx(0, 1)
      val x2734_unit = CounterChain(name = "x2734_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x2575(0)), Const(2)), op=FixSla, results=List(x2727))
      Stage(operands=List(x2727, CU.load(x2729)), op=FixAdd, results=List(CU.scalarOut(x2724_b2871_x2733_b2873_s)))
      Stage(operands=List(Const(1536)), op=Bypass, results=List(CU.scalarOut(x2724_b2872_x2733_b2874_s)))
    }
    val x2735 = MemoryController(name="x2735",parent=x2743,offchip=prices_oc, mctpe=TileLoad) { implicit CU => 
      val x2724_b2872 = ScalarFIFO(size=1,name="size").wtPort(x2724_b2872_x2733_b2874_s)
      val x2724_b2871 = ScalarFIFO(size=1,name="offset").wtPort(x2724_b2871_x2733_b2873_s)
      CU.newSout("data", x2725_x2735_data_s)
    }
    val x2742 = Pipeline(name="x2742",parent=x2743) { implicit CU => 
      val ctr9 = Counter(min=Const(0), max=Const(384), step=Const(1), par=16) // Counter
      val x2737 = CounterChain(name = "x2737", ctr9).iter(24)
    }
    val x2741 = Pipeline(name="x2741",parent=x2743) { implicit CU => 
      val x2737 = CounterChain.copy("x2742", "x2737").iterIdx(0, 0)
      Stage(operands=List(CU.ctr(x2737(0))), op=Bypass, results=List(CU.scalarOut(x2583_x2741_wa_s)))
    }
    val x2771 = Pipeline(name="x2771",parent=x2808) { implicit CU => 
      val x2762 = CU.temp(None)
      val x2765 = CU.temp(None)
      val x2764 = CU.temp(None)
      val x2760 = CU.temp(None)
      val x2761 = CU.temp(None)
      val x2744 = CU.temp(Some(0))
      val x2756 = CU.temp(None)
      val x2759 = CU.temp(None)
      val x2755 = CU.temp(None)
      val x2757 = CU.temp(None)
      val x2767 = CU.temp(None)
      val x2750 = ScalarFIFO(size=1,name="x2750").wtPort(x2580_x2580_dsp0_bank0_data_s)
      val x2749 = ScalarFIFO(size=1,name="x2749").wtPort(x2576_x2576_dsp0_bank0_data_s)
      val x2752 = ScalarFIFO(size=1,name="x2752").wtPort(x2582_x2582_dsp0_bank0_data_s)
      val x2751 = ScalarFIFO(size=1,name="x2751").wtPort(x2578_x2578_dsp0_bank0_data_s)
      val ctr10 = Counter(min=Const(0), max=Const(384), step=Const(1), par=16) // Counter
      val x2747 = CounterChain(name = "x2747", ctr10).iter(24)
      Stage(operands=List(Const(0), CU.load(x2749)), op=FixLt, results=List(x2755))
      Stage(operands=List(CU.load(x2749), Const(9999)), op=FixLt, results=List(x2756))
      Stage(operands=List(x2755, x2756), op=BitAnd, results=List(x2757))
      Stage(operands=List(Const(0), CU.load(x2750)), op=FixLeq, results=List(x2759))
      Stage(operands=List(x2757, x2759), op=BitAnd, results=List(x2760))
      Stage(operands=List(CU.load(x2750), Const(9999)), op=FixLeq, results=List(x2761))
      Stage(operands=List(x2760, x2761), op=BitAnd, results=List(x2762))
      Stage(operands=List(CU.load(x2751), Const(24)), op=FixLt, results=List(x2764))
      Stage(operands=List(x2762, x2764), op=BitAnd, results=List(x2765))
      Stage(operands=List(CU.load(x2752), CU.load(x2750)), op=FixMul, results=List(x2767))
      Stage(operands=List(x2765, x2767, Const(0)), op=MuxOp, results=List(CU.reduce))
      val (_, rr384) = Stage.reduce(op=FixAdd, init=Const(0), accumParent="x2771")
      Stage(operands=List(rr384), op=Bypass, results=List(CU.scalarOut(x2744_x2770_s)))
    }
    val x2749 = Pipeline(name="x2749",parent=x2808) { implicit CU => 
      val x2747 = CounterChain.copy("x2771", "x2747").iterIdx(0, 0)
      Stage(operands=List(CU.ctr(x2747(0))), op=Bypass, results=List(CU.scalarOut(x2576_x2749_ra_s)))
    }
    val x2750 = Pipeline(name="x2750",parent=x2808) { implicit CU => 
      val x2747 = CounterChain.copy("x2771", "x2747").iterIdx(0, 0)
      Stage(operands=List(CU.ctr(x2747(0))), op=Bypass, results=List(CU.scalarOut(x2580_x2750_ra_s)))
    }
    val x2751 = Pipeline(name="x2751",parent=x2808) { implicit CU => 
      val x2747 = CounterChain.copy("x2771", "x2747").iterIdx(0, 0)
      Stage(operands=List(CU.ctr(x2747(0))), op=Bypass, results=List(CU.scalarOut(x2578_x2751_ra_s)))
    }
    val x2752 = Pipeline(name="x2752",parent=x2808) { implicit CU => 
      val x2747 = CounterChain.copy("x2771", "x2747").iterIdx(0, 0)
      Stage(operands=List(CU.ctr(x2747(0))), op=Bypass, results=List(CU.scalarOut(x2582_x2752_ra_s)))
    }
    val x2797 = Pipeline(name="x2797",parent=x2808) { implicit CU => 
      val x2783 = CU.temp(None)
      val x2788 = CU.temp(None)
      val x2781 = CU.temp(None)
      val x2745 = CU.temp(Some(0))
      val x2787 = CU.temp(None)
      val x2786 = CU.temp(None)
      val x2791 = CU.temp(None)
      val x2793 = CU.temp(None)
      val x2790 = CU.temp(None)
      val x2785 = CU.temp(None)
      val x2782 = CU.temp(None)
      val x2775 = ScalarFIFO(size=1,name="x2775").wtPort(x2577_x2577_dsp0_bank0_data_s)
      val x2777 = ScalarFIFO(size=1,name="x2777").wtPort(x2579_x2579_dsp0_bank0_data_s)
      val x2776 = ScalarFIFO(size=1,name="x2776").wtPort(x2581_x2581_dsp0_bank0_data_s)
      val x2778 = ScalarFIFO(size=1,name="x2778").wtPort(x2583_x2583_dsp0_bank0_data_s)
      val ctr11 = Counter(min=Const(0), max=Const(384), step=Const(1), par=16) // Counter
      val x2773 = CounterChain(name = "x2773", ctr11).iter(24)
      Stage(operands=List(Const(0), CU.load(x2775)), op=FixLt, results=List(x2781))
      Stage(operands=List(CU.load(x2775), Const(9999)), op=FixLt, results=List(x2782))
      Stage(operands=List(x2781, x2782), op=BitAnd, results=List(x2783))
      Stage(operands=List(Const(0), CU.load(x2776)), op=FixLeq, results=List(x2785))
      Stage(operands=List(x2783, x2785), op=BitAnd, results=List(x2786))
      Stage(operands=List(CU.load(x2776), Const(9999)), op=FixLeq, results=List(x2787))
      Stage(operands=List(x2786, x2787), op=BitAnd, results=List(x2788))
      Stage(operands=List(CU.load(x2777), Const(24)), op=FixLt, results=List(x2790))
      Stage(operands=List(x2788, x2790), op=BitAnd, results=List(x2791))
      Stage(operands=List(CU.load(x2778), CU.load(x2776)), op=FixMul, results=List(x2793))
      Stage(operands=List(x2791, x2793, Const(0)), op=MuxOp, results=List(CU.reduce))
      val (_, rr401) = Stage.reduce(op=FixAdd, init=Const(0), accumParent="x2797")
      Stage(operands=List(rr401), op=Bypass, results=List(CU.scalarOut(x2745_x2796_s)))
    }
    val x2775 = Pipeline(name="x2775",parent=x2808) { implicit CU => 
      val x2773 = CounterChain.copy("x2797", "x2773").iterIdx(0, 0)
      Stage(operands=List(CU.ctr(x2773(0))), op=Bypass, results=List(CU.scalarOut(x2577_x2775_ra_s)))
    }
    val x2776 = Pipeline(name="x2776",parent=x2808) { implicit CU => 
      val x2773 = CounterChain.copy("x2797", "x2773").iterIdx(0, 0)
      Stage(operands=List(CU.ctr(x2773(0))), op=Bypass, results=List(CU.scalarOut(x2581_x2776_ra_s)))
    }
    val x2777 = Pipeline(name="x2777",parent=x2808) { implicit CU => 
      val x2773 = CounterChain.copy("x2797", "x2773").iterIdx(0, 0)
      Stage(operands=List(CU.ctr(x2773(0))), op=Bypass, results=List(CU.scalarOut(x2579_x2777_ra_s)))
    }
    val x2778 = Pipeline(name="x2778",parent=x2808) { implicit CU => 
      val x2773 = CounterChain.copy("x2797", "x2773").iterIdx(0, 0)
      Stage(operands=List(CU.ctr(x2773(0))), op=Bypass, results=List(CU.scalarOut(x2583_x2778_ra_s)))
    }
    val x2807 = Pipeline(name="x2807",parent=x2808) { implicit CU => 
      val x2572 = CU.temp(Some(0))
      val x2744 = ScalarBuffer(name="x2744").wtPort(x2744_x2770_s)
      val x2745 = ScalarBuffer(name="x2745").wtPort(x2745_x2796_s)
      val x2807_unit = CounterChain(name = "x2807_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x2744), CU.load(x2745)), op=FixAdd, results=List(CU.reduce))
      val (_, rr405) = Stage.reduce(op=FixAdd, init=Const(0), accumParent="x2808")
      Stage(operands=List(rr405), op=Bypass, results=List(CU.scalarOut(x2567_x2810_argout)))
    }
    
  }
}
