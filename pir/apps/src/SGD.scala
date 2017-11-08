import pir._
import pir.node._
import arch._
import pirc.enums._

object SGD extends PIRApp {
  def main(top:Top) = {
    val x2734_x2734_dsp2_bank0_data_v = Vector("x2734_x2734_dsp2_bank0_data")
    val x2734_x2734_dsp1_bank0_data_v = Vector("x2734_x2734_dsp1_bank0_data")
    val x_da = DRAMAddress("x", "x")
    val N_argin = ArgIn("N")
    val x2734_x2737_wa_v = Vector("x2734_x2737_wa")
    val x2861_b2924_x2871_b2926_s = Scalar("x2861_b2924_x2871_b2926")
    val x2782_b2916_x2799_b2918_s = Scalar("x2782_b2916_x2799_b2918")
    val x2771_x2837_s = Scalar("x2771_x2837")
    val x2734_x2856_wa_v = Vector("x2734_x2856_wa")
    val result_oc = OffChip("result")
    val x2772_x2847_ra_v = Vector("x2772_x2847_ra")
    val x_oc = OffChip("x")
    val x2783_x2801_data_v = Vector("x2783_x2801_data")
    val x2734_x2737_data_v = Vector("x2734_x2737_data")
    val x2734_x2821_ra_v = Vector("x2734_x2821_ra")
    val A_argin = ArgIn("A")
    val x2747_b2911_x2757_b2913_s = Scalar("x2747_b2911_x2757_b2913")
    val E_argin = ArgIn("E")
    val x2772_x2772_dsp0_bank0_data_v = Vector("x2772_x2772_dsp0_bank0_data")
    val x2773_x2778_s = Scalar("x2773_x2778")
    val x2734_x2875_ra_v = Vector("x2734_x2875_ra")
    val x2782_b2915_x2799_b2917_s = Scalar("x2782_b2915_x2799_b2917")
    val x2734_x2856_data_v = Vector("x2734_x2856_data")
    val x2861_b2925_x2871_b2927_s = Scalar("x2861_b2925_x2871_b2927")
    val x2748_x2759_data_v = Vector("x2748_x2759_data")
    val x2772_x2772_dsp1_bank0_data_v = Vector("x2772_x2772_dsp1_bank0_data")
    val x2734_x2845_ra_v = Vector("x2734_x2845_ra")
    val x2734_x2734_dsp0_bank0_data_v = Vector("x2734_x2734_dsp0_bank0_data")
    val y_da = DRAMAddress("y", "y")
    val x2813_x2830_s = Scalar("x2813_x2830")
    val y_oc = OffChip("y")
    val x2747_b2912_x2757_b2914_s = Scalar("x2747_b2912_x2757_b2914")
    val x2733_x2733_dsp0_bank0_data_s = Scalar("x2733_x2733_dsp0_bank0_data")
    val result_da = DRAMAddress("result", "result")
    val x2772_x2819_ra_v = Vector("x2772_x2819_ra")
    val x2733_dsp0_bank0 = MemoryPipeline(name="x2733_dsp0_bank0",parent="top") { implicit CU => 
      val x2766 = VectorFIFO(size=1,name="x2766")
        .store(x2748_x2759_data_v, None, None)
      val x2761 = CounterChain.copy("x2767", "x2761")
      val x2770 = CounterChain.copy("x2858", "x2770")
      val x2733 = SRAM(size=1,name="x2733",banking = Strided(1,16))
        .buffering(1)
        .store(x2766.readPort, Some(x2761(0)), None)
        .load(x2733_x2733_dsp0_bank0_data_s, Some(x2770(0)), None)
    }
    val x2734_dsp0_bank0 = MemoryPipeline(name="x2734_dsp0_bank0",parent="top") { implicit CU => 
      val b2923 = VectorFIFO(size=1,name="b2923")
        .store(x2734_x2856_wa_v, None, None)
      val b2910 = VectorFIFO(size=1,name="b2910")
        .store(x2734_x2737_wa_v, None, None)
      val b2928 = VectorFIFO(size=1,name="b2928")
        .store(x2734_x2875_ra_v, None, None)
      val x2737 = VectorFIFO(size=1,name="x2737")
        .store(x2734_x2737_data_v, None, None)
      val x2856 = VectorFIFO(size=1,name="x2856")
        .store(x2734_x2856_data_v, None, None)
      val x2734 = SRAM(size=1,name="x2734",banking = Strided(1,16))
        .buffering(1)
        .store(x2737.readPort, Some(b2910.readPort), None)
        .store(x2856.readPort, Some(b2923.readPort), None)
        .load(x2734_x2734_dsp0_bank0_data_v, Some(b2928.readPort), None)
    }
    val x2734_dsp1_bank0 = MemoryPipeline(name="x2734_dsp1_bank0",parent="top") { implicit CU => 
      val x2856 = VectorFIFO(size=1,name="x2856")
        .store(x2734_x2856_data_v, None, None)
      val b2923 = VectorFIFO(size=1,name="b2923")
        .store(x2734_x2856_wa_v, None, None)
      val x2737 = VectorFIFO(size=1,name="x2737")
        .store(x2734_x2737_data_v, None, None)
      val b2910 = VectorFIFO(size=1,name="b2910")
        .store(x2734_x2737_wa_v, None, None)
      val b2921 = VectorFIFO(size=1,name="b2921")
        .store(x2734_x2845_ra_v, None, None)
      val x2734 = SRAM(size=1,name="x2734",banking = Strided(1,16))
        .buffering(1)
        .store(x2737.readPort, Some(b2910.readPort), None)
        .store(x2856.readPort, Some(b2923.readPort), None)
        .load(x2734_x2734_dsp1_bank0_data_v, Some(b2921.readPort), None)
    }
    val x2734_dsp2_bank0 = MemoryPipeline(name="x2734_dsp2_bank0",parent="top") { implicit CU => 
      val b2923 = VectorFIFO(size=1,name="b2923")
        .store(x2734_x2856_wa_v, None, None)
      val x2737 = VectorFIFO(size=1,name="x2737")
        .store(x2734_x2737_data_v, None, None)
      val b2920 = VectorFIFO(size=1,name="b2920")
        .store(x2734_x2821_ra_v, None, None)
      val b2910 = VectorFIFO(size=1,name="b2910")
        .store(x2734_x2737_wa_v, None, None)
      val x2856 = VectorFIFO(size=1,name="x2856")
        .store(x2734_x2856_data_v, None, None)
      val x2734 = SRAM(size=1,name="x2734",banking = Strided(1,16))
        .buffering(1)
        .store(x2737.readPort, Some(b2910.readPort), None)
        .store(x2856.readPort, Some(b2923.readPort), None)
        .load(x2734_x2734_dsp2_bank0_data_v, Some(b2920.readPort), None)
    }
    val x2738_0 = Pipeline(name="x2738_0",parent="top") { implicit CU => 
      val ctr1 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x2736 = CounterChain(name = "x2736", ctr1).iter(1)
      Stage(operands=List(Const(0.0)), op=Bypass, results=List(CU.vecOut(x2734_x2737_data_v)))
      Stage(operands=List(x2736(0)), op=Bypass, results=List(CU.vecOut(x2734_x2737_wa_v)))
    }
    val x2860 = Sequential(name="x2860",parent="top") { implicit CU => 
      val x2711 = ScalarBuffer(name="x2711")
        .buffering(1)
        .store(E_argin, None, None)
      val ctr2 = Counter(min=Const(0), max=x2711.readPort, step=Const(1), par=1) // Counter
      val x2741 = CounterChain(name = "x2741", ctr2).iter(1)
    }
    val x2859 = Sequential(name="x2859",parent="x2860") { implicit CU => 
      val x2712 = ScalarBuffer(name="x2712")
        .buffering(1)
        .store(N_argin, None, None)
      val ctr3 = Counter(min=Const(0), max=x2712.readPort, step=Const(16), par=1) // Counter
      val x2744 = CounterChain(name = "x2744", ctr3).iter(1)
    }
    val x2768 = StreamController(name="x2768",parent="x2859") { implicit CU => 
      val x2768_unit = CounterChain(name = "x2768_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x2758_0 = Pipeline(name="x2758_0",parent="x2768") { implicit CU => 
      val x2750 = CU.temp(None).name("x2750")
      val x2752 = ScalarBuffer(name="x2752")
        .buffering(1)
        .store(y_da, None, None)
      val x2744 = CounterChain.copy("x2859", "x2744").iterIdx(0, 0)
      val x2758_unit = CounterChain(name = "x2758_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(x2744(0), Const(2)), op=FixSla, results=List(x2750))
      Stage(operands=List(x2750, x2752), op=FixAdd, results=List(CU.scalarOut(x2747_b2911_x2757_b2913_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x2747_b2912_x2757_b2914_s)))
    }
    val x2759 = MemoryController(name="x2759",parent="x2768",offchip=y_oc, mctpe=TileLoad) { implicit CU => 
      val x2747_b2912 = ScalarFIFO(size=1,name="size")
        .store(x2747_b2912_x2757_b2914_s, None, None)
      val x2747_b2911 = ScalarFIFO(size=1,name="offset")
        .store(x2747_b2911_x2757_b2913_s, None, None)
      CU.newOut("data", x2748_x2759_data_v)
    }
    val x2767 = Pipeline(name="x2767",parent="x2768") { implicit CU => 
      val ctr4 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x2761 = CounterChain(name = "x2761", ctr4).iter(1)
    }
    val x2858 = Sequential(name="x2858",parent="x2859") { implicit CU => 
      val ctr5 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x2770 = CounterChain(name = "x2770", ctr5).iter(16)
    }
    val x2772_dsp1_bank0 = MemoryPipeline(name="x2772_dsp1_bank0",parent="x2858") { implicit CU => 
      val x2810 = VectorFIFO(size=1,name="x2810")
        .store(x2783_x2801_data_v, None, None)
      val b2919 = VectorFIFO(size=1,name="b2919")
        .store(x2772_x2819_ra_v, None, None)
      val x2803 = CounterChain.copy("x2811", "x2803")
      val x2772 = SRAM(size=1,name="x2772",banking = Strided(1,16))
        .buffering(1)
        .store(x2810.readPort, Some(x2803(0)), None)
        .load(x2772_x2772_dsp1_bank0_data_v, Some(b2919.readPort), None)
    }
    val x2772_dsp0_bank0 = MemoryPipeline(name="x2772_dsp0_bank0",parent="x2858") { implicit CU => 
      val x2810 = VectorFIFO(size=1,name="x2810")
        .store(x2783_x2801_data_v, None, None)
      val b2922 = VectorFIFO(size=1,name="b2922")
        .store(x2772_x2847_ra_v, None, None)
      val x2803 = CounterChain.copy("x2811", "x2803")
      val x2772 = SRAM(size=1,name="x2772",banking = Strided(1,16))
        .buffering(1)
        .store(x2810.readPort, Some(x2803(0)), None)
        .load(x2772_x2772_dsp0_bank0_data_v, Some(b2922.readPort), None)
    }
    val x2779_0 = Pipeline(name="x2779_0",parent="x2858") { implicit CU => 
      val x2744 = CounterChain.copy("x2859", "x2744").iterIdx(0, 0)
      val x2770 = CounterChain.copy("x2858", "x2770").iterIdx(0, 0)
      val x2779_unit = CounterChain(name = "x2779_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(x2744(0), x2770(0)), op=FixAdd, results=List(CU.scalarOut(x2773_x2778_s)))
    }
    val x2812 = StreamController(name="x2812",parent="x2858") { implicit CU => 
      val ctr6 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1) // Counter
      val x2781 = CounterChain(name = "x2781", ctr6).iter(1)
    }
    val x2800_0 = Pipeline(name="x2800_0",parent="x2812") { implicit CU => 
      val x2787 = CU.temp(None).name("x2787")
      val x2790 = CU.temp(None).name("x2790")
      val x2789 = CU.temp(None).name("x2789")
      val x2785 = CU.temp(None).name("x2785")
      val x2792 = ScalarBuffer(name="x2792")
        .buffering(1)
        .store(x_da, None, None)
      val x2773 = ScalarBuffer(name="x2773")
        .buffering(1)
        .store(x2773_x2778_s, None, None)
      val x2800_unit = CounterChain(name = "x2800_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      val x2781 = CounterChain.copy("x2812", "x2781").iterIdx(0, 0)
      Stage(operands=List(x2773, x2781(0)), op=FixAdd, results=List(x2785))
      Stage(operands=List(x2785, Const(4)), op=FixSla, results=List(x2787))
      Stage(operands=List(x2787, Const(0)), op=FixAdd, results=List(x2789))
      Stage(operands=List(x2789, Const(2)), op=FixSla, results=List(x2790))
      Stage(operands=List(x2790, x2792), op=FixAdd, results=List(CU.scalarOut(x2782_b2915_x2799_b2917_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x2782_b2916_x2799_b2918_s)))
    }
    val x2801 = MemoryController(name="x2801",parent="x2812",offchip=x_oc, mctpe=TileLoad) { implicit CU => 
      val x2782_b2915 = ScalarFIFO(size=1,name="offset")
        .store(x2782_b2915_x2799_b2917_s, None, None)
      val x2782_b2916 = ScalarFIFO(size=1,name="size")
        .store(x2782_b2916_x2799_b2918_s, None, None)
      CU.newOut("data", x2783_x2801_data_v)
    }
    val x2811 = Pipeline(name="x2811",parent="x2812") { implicit CU => 
      val ctr7 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x2803 = CounterChain(name = "x2803", ctr7).iter(1)
    }
    val x2839 = Sequential(name="x2839",parent="x2858") { implicit CU => 
      val x2839_unit = CounterChain(name = "x2839_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x2831_0 = Pipeline(name="x2831_0",parent="x2839") { implicit CU => 
      val x2819 = VectorFIFO(size=1,name="x2819")
        .store(x2772_x2772_dsp1_bank0_data_v, None, None)
      val x2821 = VectorFIFO(size=1,name="x2821")
        .store(x2734_x2734_dsp2_bank0_data_v, None, None)
      val ctr8 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x2815 = CounterChain(name = "x2815", ctr8).iter(1)
      Stage(operands=List(x2819, x2821), op=FixMul, results=List(CU.reduce))
      val (_, rr264) = Stage.reduce(op=FixAdd, init=Const(0.0), accumParent="x2831_0")
      Stage(operands=List(rr264), op=Bypass, results=List(CU.scalarOut(x2813_x2830_s)))
    }
    val x2819_0 = Pipeline(name="x2819_0",parent="x2839") { implicit CU => 
      val x2815 = CounterChain.copy("x2831_0", "x2815").iterIdx(0, 0)
      Stage(operands=List(x2815(0)), op=Bypass, results=List(CU.vecOut(x2772_x2819_ra_v)))
    }
    val x2821_0 = Pipeline(name="x2821_0",parent="x2839") { implicit CU => 
      val x2815 = CounterChain.copy("x2831_0", "x2815").iterIdx(0, 0)
      Stage(operands=List(x2815(0)), op=Bypass, results=List(CU.vecOut(x2734_x2821_ra_v)))
    }
    val x2838_0 = Pipeline(name="x2838_0",parent="x2839") { implicit CU => 
      val x2834 = ScalarFIFO(size=1,name="x2834")
        .store(x2733_x2733_dsp0_bank0_data_s, None, None)
      val x2813 = ScalarBuffer(name="x2813")
        .buffering(1)
        .store(x2813_x2830_s, None, None)
      val x2838_unit = CounterChain(name = "x2838_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(x2834, x2813), op=FixSub, results=List(CU.scalarOut(x2771_x2837_s)))
    }
    val x2857_0 = Pipeline(name="x2857_0",parent="x2858") { implicit CU => 
      val x2854 = CU.temp(None).name("x2854")
      val x2852 = CU.temp(None).name("x2852")
      val x2771 = ScalarBuffer(name="x2771")
        .buffering(1)
        .store(x2771_x2837_s, None, None)
      val x2845 = VectorFIFO(size=1,name="x2845")
        .store(x2734_x2734_dsp1_bank0_data_v, None, None)
      val x2713 = ScalarBuffer(name="x2713")
        .buffering(1)
        .store(A_argin, None, None)
      val x2847 = VectorFIFO(size=1,name="x2847")
        .store(x2772_x2772_dsp0_bank0_data_v, None, None)
      val ctr9 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x2841 = CounterChain(name = "x2841", ctr9).iter(1)
      Stage(operands=List(x2847, x2771), op=FixMul, results=List(x2852))
      Stage(operands=List(x2852, x2713), op=FixMul, results=List(x2854))
      Stage(operands=List(x2845, x2854), op=FixAdd, results=List(CU.vecOut(x2734_x2856_data_v)))
      Stage(operands=List(x2841(0)), op=Bypass, results=List(CU.vecOut(x2734_x2856_wa_v)))
    }
    val x2845_0 = Pipeline(name="x2845_0",parent="x2858") { implicit CU => 
      val x2841 = CounterChain.copy("x2857_0", "x2841").iterIdx(0, 0)
      Stage(operands=List(x2841(0)), op=Bypass, results=List(CU.vecOut(x2734_x2845_ra_v)))
    }
    val x2847_0 = Pipeline(name="x2847_0",parent="x2858") { implicit CU => 
      val x2841 = CounterChain.copy("x2857_0", "x2841").iterIdx(0, 0)
      Stage(operands=List(x2841(0)), op=Bypass, results=List(CU.vecOut(x2772_x2847_ra_v)))
    }
    val x2883 = StreamController(name="x2883",parent="top") { implicit CU => 
      val x2883_unit = CounterChain(name = "x2883_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x2872_0 = Pipeline(name="x2872_0",parent="x2883") { implicit CU => 
      val x2865 = CU.temp(None).name("x2865")
      val x2867 = ScalarBuffer(name="x2867")
        .buffering(1)
        .store(result_da, None, None)
      val x2872_unit = CounterChain(name = "x2872_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(Const(0), Const(2)), op=FixSla, results=List(x2865))
      Stage(operands=List(x2865, x2867), op=FixAdd, results=List(CU.scalarOut(x2861_b2924_x2871_b2926_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x2861_b2925_x2871_b2927_s)))
    }
    val x2879 = Pipeline(name="x2879",parent="x2883") { implicit CU => 
      val ctr10 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x2874 = CounterChain(name = "x2874", ctr10).iter(1)
    }
    val x2875_0 = Pipeline(name="x2875_0",parent="x2883") { implicit CU => 
      val x2874 = CounterChain.copy("x2879", "x2874").iterIdx(0, 0)
      Stage(operands=List(x2874(0)), op=Bypass, results=List(CU.vecOut(x2734_x2875_ra_v)))
    }
    val x2880 = MemoryController(name="x2880",parent="x2883",offchip=result_oc, mctpe=TileStore) { implicit CU => 
      val x2861_b2924 = ScalarFIFO(size=1,name="offset")
        .store(x2861_b2924_x2871_b2926_s, None, None)
      val x2861_b2925 = ScalarFIFO(size=1,name="size")
        .store(x2861_b2925_x2871_b2927_s, None, None)
      val x2862 = VectorFIFO(size=1,name="data")
        .store(x2734_x2734_dsp0_bank0_data_v, None, None)
    }
    
  }
}
