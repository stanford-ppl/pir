import pir.graph.{Mux =>_, _}
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.util.enums._
import pir.util._
import pir.PIRApp

object Gibbs_Ising2D extends PIRApp {
  def main(top:Top) = {
    val bus_606_s = Scalar("bus_606")
    val x2594_x2781_s = Scalar("x2594_x2781")
    val exp_lut_oc = OffChip("exp_lut")
    val grid_dram_da = DRAMAddress("grid_dram", "grid_dram")
    val bus_655_s = Scalar("bus_655")
    val x2596_x2606_data_s = Scalar("x2596_x2606_data")
    val x2594_x2731_s = Scalar("x2594_x2731")
    val bias_dram_da = DRAMAddress("bias_dram", "bias_dram")
    val x2642_b2880_x2654_b2882_s = Scalar("x2642_b2880_x2654_b2882")
    val x2788_b2913_x2801_b2915_s = Scalar("x2788_b2913_x2801_b2915")
    val bias_dram_oc = OffChip("bias_dram")
    val x2594_6_s = Scalar("x2594_6")
    val x2594_3_s = Scalar("x2594_3")
    val x2595_b2870_x2604_b2872_s = Scalar("x2595_b2870_x2604_b2872")
    val exp_posbias_argin = ArgIn("exp_posbias")
    val x2593_0_s = Scalar("x2593_0")
    val grid_dram_oc = OffChip("grid_dram")
    val x2594_7_s = Scalar("x2594_7")
    val x2594_2_s = Scalar("x2594_2")
    val x2594_5_s = Scalar("x2594_5")
    val x2594_x2636_s = Scalar("x2594_x2636")
    val x2594_1_s = Scalar("x2594_1")
    val x2616_b2873_x2628_b2875_s = Scalar("x2616_b2873_x2628_b2875")
    val x2639_0_s = Scalar("x2639_0")
    val exp_lut_da = DRAMAddress("exp_lut", "exp_lut")
    val x2595_b2869_x2604_b2871_s = Scalar("x2595_b2869_x2604_b2871")
    val exp_negbias_argin = ArgIn("exp_negbias")
    val x2670_x2675_s = Scalar("x2670_x2675")
    val x2593_1_s = Scalar("x2593_1")
    val x2788_b2914_x2801_b2916_s = Scalar("x2788_b2914_x2801_b2916")
    val x2617_x2630_data_v = Vector("x2617_x2630_data")
    val iters_argin = ArgIn("iters")
    val x2593_x2611_s = Scalar("x2593_x2611")
    val x2789_x2809_v = Vector("x2789_x2809")
    val bus_622_s = Scalar("bus_622")
    val bus_639_s = Scalar("bus_639")
    val bus_662_s = Scalar("bus_662")
    val x2594_4_s = Scalar("x2594_4")
    val x2643_x2656_data_v = Vector("x2643_x2656_data")
    val x2594_0_s = Scalar("x2594_0")
    val x2639_x2662_s = Scalar("x2639_x2662")
    val x2671_x2680_s = Scalar("x2671_x2680")
    val x2616_b2874_x2628_b2876_s = Scalar("x2616_b2874_x2628_b2876")
    val bus_629_s = Scalar("bus_629")
    val x2642_b2879_x2654_b2881_s = Scalar("x2642_b2879_x2654_b2881")
    val x2816 = Sequential(name="x2816",parent=top) { implicit CU => 
      val x2816_unit = CounterChain(name = "x2816_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x2593_dsp0 = MemoryPipeline(name="x2593_dsp0",parent="x2816") { implicit CU => 
      val x2706 = CU.temp
      val x2708 = CU.temp
      val x2686 = CU.temp
      val x2707 = CU.temp
      val x2696 = CU.temp
      val x2709 = CU.temp
      val x2692 = CU.temp
      val x2700 = CU.temp
      val x2702 = ScalarFIFO(size=1,name="x2702").wtPort(x2594_2_s)
      val x2611 = ScalarFIFO(size=1,name="x2611").wtPort(x2593_x2611_s)
      val x2698 = ScalarFIFO(size=1,name="x2698").wtPort(x2594_3_s)
      val x2694 = ScalarFIFO(size=1,name="x2694").wtPort(x2594_5_s)
      val x2704 = ScalarFIFO(size=1,name="x2704").wtPort(x2594_1_s)
      val x2690 = ScalarFIFO(size=1,name="x2690").wtPort(x2594_6_s)
      val x2608 = CounterChain.copy("x2612_0", "x2608")
      val x2669 = CounterChain.copy("x2784", "x2669")
      val x2685 = CounterChain.copy("x2732", "x2685")
      val x2593 = SRAM(size=9,name="x2593",banking = Strided(1)).wtPort(x2611.readPort).rdPort(x2593_0_s).wtAddr(x2608(0))
      RAStage(operands=List(CU.ctr(x2685(0)), Const(1)), op=FixSub, results=List(x2700))
      RAStage(operands=List(CU.ctr(x2669(0)), Const(1)), op=FixSub, results=List(x2696))
      RAStage(operands=List(CU.ctr(x2669(0)), Const(1)), op=FixAdd, results=List(x2686))
      RAStage(operands=List(CU.ctr(x2685(0)), Const(1)), op=FixAdd, results=List(x2692))
      RAStage(operands=List(CU.load(x2690), CU.load(x2694)), op=FixAdd, results=List(x2706))
      RAStage(operands=List(x2706, CU.load(x2698)), op=FixAdd, results=List(x2707))
      RAStage(operands=List(x2707, CU.load(x2702)), op=FixAdd, results=List(x2708))
      RAStage(operands=List(x2708, CU.load(x2704)), op=FixMul, results=List(x2709))
      RAStage(operands=List(x2709, Const(4)), op=FixAdd, results=List(x2593.readAddr))
    }
    val x2593_dsp1 = MemoryPipeline(name="x2593_dsp1",parent="x2816") { implicit CU => 
      val x2742 = CU.temp
      val x2758 = CU.temp
      val x2756 = CU.temp
      val x2736 = CU.temp
      val x2759 = CU.temp
      val x2757 = CU.temp
      val x2746 = CU.temp
      val x2750 = CU.temp
      val x2752 = ScalarFIFO(size=1,name="x2752").wtPort(x2594_2_s)
      val x2744 = ScalarFIFO(size=1,name="x2744").wtPort(x2594_5_s)
      val x2740 = ScalarFIFO(size=1,name="x2740").wtPort(x2594_7_s)
      val x2748 = ScalarFIFO(size=1,name="x2748").wtPort(x2594_4_s)
      val x2754 = ScalarFIFO(size=1,name="x2754").wtPort(x2594_1_s)
      val x2611 = ScalarFIFO(size=1,name="x2611").wtPort(x2593_x2611_s)
      val x2608 = CounterChain.copy("x2612_0", "x2608")
      val x2669 = CounterChain.copy("x2784", "x2669")
      val x2735 = CounterChain.copy("x2782", "x2735")
      val x2593 = SRAM(size=9,name="x2593",banking = Strided(1)).wtPort(x2611.readPort).rdPort(x2593_1_s).wtAddr(x2608(0))
      RAStage(operands=List(CU.ctr(x2735(0)), Const(1)), op=FixSub, results=List(x2750))
      RAStage(operands=List(CU.ctr(x2669(0)), Const(1)), op=FixSub, results=List(x2746))
      RAStage(operands=List(CU.ctr(x2669(0)), Const(1)), op=FixAdd, results=List(x2736))
      RAStage(operands=List(CU.ctr(x2735(0)), Const(1)), op=FixAdd, results=List(x2742))
      RAStage(operands=List(CU.load(x2740), CU.load(x2744)), op=FixAdd, results=List(x2756))
      RAStage(operands=List(x2756, CU.load(x2748)), op=FixAdd, results=List(x2757))
      RAStage(operands=List(x2757, CU.load(x2752)), op=FixAdd, results=List(x2758))
      RAStage(operands=List(x2758, CU.load(x2754)), op=FixMul, results=List(x2759))
      RAStage(operands=List(x2759, Const(4)), op=FixAdd, results=List(x2593.readAddr))
    }
    val x2594_dsp0 = MemoryPipeline(name="x2594_dsp0",parent="x2816") { implicit CU => 
      val b2917 = CU.temp
      val b2911 = CU.temp
      val x2636 = ScalarFIFO(size=1,name="x2636").wtPort(x2594_x2636_s)
      val x2731 = ScalarFIFO(size=1,name="x2731").wtPort(x2594_x2731_s)
      val x2781 = ScalarFIFO(size=1,name="x2781").wtPort(x2594_x2781_s)
      val x2804 = CounterChain.copy("x2810_0", "x2804")
      val x2669 = CounterChain.copy("x2784", "x2669")
      val x2787 = CounterChain.copy("x2815", "x2787")
      val x2735 = CounterChain.copy("x2782", "x2735")
      val x2594 = SRAM(size=2048,name="x2594",banking = Strided(1)).wtPort(x2636.readPort).wtPort(x2731.readPort).wtPort(x2781.readPort).rdPort(x2594_0_s)
      WAStage(operands=List(CU.ctr(x2669(0)), Const(64)), op=FixMul, results=List(b2911))
      WAStage(operands=List(b2911, CU.ctr(x2735(0))), op=FixAdd, results=List(x2594.writeAddr))
      RAStage(operands=List(CU.ctr(x2787(0)), Const(64)), op=FixMul, results=List(b2917))
      RAStage(operands=List(b2917, CU.ctr(x2804(0))), op=FixAdd, results=List(x2594.readAddr))
    }
    val x2594_dsp1 = MemoryPipeline(name="x2594_dsp1",parent="x2816") { implicit CU => 
      val b2907 = CU.temp
      val b2911 = CU.temp
      val x2636 = ScalarFIFO(size=1,name="x2636").wtPort(x2594_x2636_s)
      val x2731 = ScalarFIFO(size=1,name="x2731").wtPort(x2594_x2731_s)
      val x2781 = ScalarFIFO(size=1,name="x2781").wtPort(x2594_x2781_s)
      val x2669 = CounterChain.copy("x2784", "x2669")
      val x2735 = CounterChain.copy("x2782", "x2735")
      val x2594 = SRAM(size=2048,name="x2594",banking = Strided(1)).wtPort(x2636.readPort).wtPort(x2731.readPort).wtPort(x2781.readPort).rdPort(x2594_1_s)
      WAStage(operands=List(CU.ctr(x2669(0)), Const(64)), op=FixMul, results=List(b2911))
      WAStage(operands=List(b2911, CU.ctr(x2735(0))), op=FixAdd, results=List(x2594.writeAddr))
      RAStage(operands=List(CU.ctr(x2669(0)), Const(64)), op=FixMul, results=List(b2907))
      RAStage(operands=List(b2907, CU.ctr(x2735(0))), op=FixAdd, results=List(x2594.readAddr))
    }
    val x2594_dsp2 = MemoryPipeline(name="x2594_dsp2",parent="x2816") { implicit CU => 
      val x2751 = CU.temp
      val b2905 = CU.temp
      val b2911 = CU.temp
      val x2750 = CU.temp
      val x2636 = ScalarFIFO(size=1,name="x2636").wtPort(x2594_x2636_s)
      val x2731 = ScalarFIFO(size=1,name="x2731").wtPort(x2594_x2731_s)
      val x2781 = ScalarFIFO(size=1,name="x2781").wtPort(x2594_x2781_s)
      val x2669 = CounterChain.copy("x2784", "x2669")
      val x2735 = CounterChain.copy("x2782", "x2735")
      val x2594 = SRAM(size=2048,name="x2594",banking = Strided(1)).wtPort(x2636.readPort).wtPort(x2731.readPort).wtPort(x2781.readPort).rdPort(x2594_2_s)
      WAStage(operands=List(CU.ctr(x2669(0)), Const(64)), op=FixMul, results=List(b2911))
      WAStage(operands=List(b2911, CU.ctr(x2735(0))), op=FixAdd, results=List(x2594.writeAddr))
      RAStage(operands=List(CU.ctr(x2735(0)), Const(1)), op=FixSub, results=List(x2750))
      RAStage(operands=List(x2750, Const(64)), op=FixMod, results=List(x2751))
      RAStage(operands=List(CU.ctr(x2669(0)), Const(64)), op=FixMul, results=List(b2905))
      RAStage(operands=List(b2905, x2751), op=FixAdd, results=List(x2594.readAddr))
    }
    val x2594_dsp3 = MemoryPipeline(name="x2594_dsp3",parent="x2816") { implicit CU => 
      val x2696 = CU.temp
      val x2697 = CU.temp
      val b2911 = CU.temp
      val b2889 = CU.temp
      val x2636 = ScalarFIFO(size=1,name="x2636").wtPort(x2594_x2636_s)
      val x2731 = ScalarFIFO(size=1,name="x2731").wtPort(x2594_x2731_s)
      val x2781 = ScalarFIFO(size=1,name="x2781").wtPort(x2594_x2781_s)
      val x2685 = CounterChain.copy("x2732", "x2685")
      val x2669 = CounterChain.copy("x2784", "x2669")
      val x2735 = CounterChain.copy("x2782", "x2735")
      val x2594 = SRAM(size=2048,name="x2594",banking = Strided(1)).wtPort(x2636.readPort).wtPort(x2731.readPort).wtPort(x2781.readPort).rdPort(x2594_3_s)
      WAStage(operands=List(CU.ctr(x2669(0)), Const(64)), op=FixMul, results=List(b2911))
      WAStage(operands=List(b2911, CU.ctr(x2735(0))), op=FixAdd, results=List(x2594.writeAddr))
      RAStage(operands=List(CU.ctr(x2669(0)), Const(1)), op=FixSub, results=List(x2696))
      RAStage(operands=List(x2696, Const(32)), op=FixMod, results=List(x2697))
      RAStage(operands=List(x2697, Const(64)), op=FixMul, results=List(b2889))
      RAStage(operands=List(b2889, CU.ctr(x2685(0))), op=FixAdd, results=List(x2594.readAddr))
    }
    val x2594_dsp4 = MemoryPipeline(name="x2594_dsp4",parent="x2816") { implicit CU => 
      val x2747 = CU.temp
      val x2746 = CU.temp
      val b2911 = CU.temp
      val b2903 = CU.temp
      val x2636 = ScalarFIFO(size=1,name="x2636").wtPort(x2594_x2636_s)
      val x2731 = ScalarFIFO(size=1,name="x2731").wtPort(x2594_x2731_s)
      val x2781 = ScalarFIFO(size=1,name="x2781").wtPort(x2594_x2781_s)
      val x2669 = CounterChain.copy("x2784", "x2669")
      val x2735 = CounterChain.copy("x2782", "x2735")
      val x2594 = SRAM(size=2048,name="x2594",banking = Strided(1)).wtPort(x2636.readPort).wtPort(x2731.readPort).wtPort(x2781.readPort).rdPort(x2594_4_s)
      WAStage(operands=List(CU.ctr(x2669(0)), Const(64)), op=FixMul, results=List(b2911))
      WAStage(operands=List(b2911, CU.ctr(x2735(0))), op=FixAdd, results=List(x2594.writeAddr))
      RAStage(operands=List(CU.ctr(x2669(0)), Const(1)), op=FixSub, results=List(x2746))
      RAStage(operands=List(x2746, Const(32)), op=FixMod, results=List(x2747))
      RAStage(operands=List(x2747, Const(64)), op=FixMul, results=List(b2903))
      RAStage(operands=List(b2903, CU.ctr(x2735(0))), op=FixAdd, results=List(x2594.readAddr))
    }
    val x2594_dsp5 = MemoryPipeline(name="x2594_dsp5",parent="x2816") { implicit CU => 
      val b2901 = CU.temp
      val x2742 = CU.temp
      val x2743 = CU.temp
      val b2911 = CU.temp
      val x2636 = ScalarFIFO(size=1,name="x2636").wtPort(x2594_x2636_s)
      val x2731 = ScalarFIFO(size=1,name="x2731").wtPort(x2594_x2731_s)
      val x2781 = ScalarFIFO(size=1,name="x2781").wtPort(x2594_x2781_s)
      val x2669 = CounterChain.copy("x2784", "x2669")
      val x2735 = CounterChain.copy("x2782", "x2735")
      val x2594 = SRAM(size=2048,name="x2594",banking = Strided(1)).wtPort(x2636.readPort).wtPort(x2731.readPort).wtPort(x2781.readPort).rdPort(x2594_5_s)
      WAStage(operands=List(CU.ctr(x2669(0)), Const(64)), op=FixMul, results=List(b2911))
      WAStage(operands=List(b2911, CU.ctr(x2735(0))), op=FixAdd, results=List(x2594.writeAddr))
      RAStage(operands=List(CU.ctr(x2735(0)), Const(1)), op=FixAdd, results=List(x2742))
      RAStage(operands=List(x2742, Const(64)), op=FixMod, results=List(x2743))
      RAStage(operands=List(CU.ctr(x2669(0)), Const(64)), op=FixMul, results=List(b2901))
      RAStage(operands=List(b2901, x2743), op=FixAdd, results=List(x2594.readAddr))
    }
    val x2594_dsp6 = MemoryPipeline(name="x2594_dsp6",parent="x2816") { implicit CU => 
      val x2686 = CU.temp
      val x2687 = CU.temp
      val b2885 = CU.temp
      val b2911 = CU.temp
      val x2636 = ScalarFIFO(size=1,name="x2636").wtPort(x2594_x2636_s)
      val x2731 = ScalarFIFO(size=1,name="x2731").wtPort(x2594_x2731_s)
      val x2781 = ScalarFIFO(size=1,name="x2781").wtPort(x2594_x2781_s)
      val x2685 = CounterChain.copy("x2732", "x2685")
      val x2669 = CounterChain.copy("x2784", "x2669")
      val x2735 = CounterChain.copy("x2782", "x2735")
      val x2594 = SRAM(size=2048,name="x2594",banking = Strided(1)).wtPort(x2636.readPort).wtPort(x2731.readPort).wtPort(x2781.readPort).rdPort(x2594_6_s)
      WAStage(operands=List(CU.ctr(x2669(0)), Const(64)), op=FixMul, results=List(b2911))
      WAStage(operands=List(b2911, CU.ctr(x2735(0))), op=FixAdd, results=List(x2594.writeAddr))
      RAStage(operands=List(CU.ctr(x2669(0)), Const(1)), op=FixAdd, results=List(x2686))
      RAStage(operands=List(x2686, Const(32)), op=FixMod, results=List(x2687))
      RAStage(operands=List(x2687, Const(64)), op=FixMul, results=List(b2885))
      RAStage(operands=List(b2885, CU.ctr(x2685(0))), op=FixAdd, results=List(x2594.readAddr))
    }
    val x2594_dsp7 = MemoryPipeline(name="x2594_dsp7",parent="x2816") { implicit CU => 
      val b2899 = CU.temp
      val x2736 = CU.temp
      val x2737 = CU.temp
      val b2911 = CU.temp
      val x2636 = ScalarFIFO(size=1,name="x2636").wtPort(x2594_x2636_s)
      val x2731 = ScalarFIFO(size=1,name="x2731").wtPort(x2594_x2731_s)
      val x2781 = ScalarFIFO(size=1,name="x2781").wtPort(x2594_x2781_s)
      val x2669 = CounterChain.copy("x2784", "x2669")
      val x2735 = CounterChain.copy("x2782", "x2735")
      val x2594 = SRAM(size=2048,name="x2594",banking = Strided(1)).wtPort(x2636.readPort).wtPort(x2731.readPort).wtPort(x2781.readPort).rdPort(x2594_7_s)
      WAStage(operands=List(CU.ctr(x2669(0)), Const(64)), op=FixMul, results=List(b2911))
      WAStage(operands=List(b2911, CU.ctr(x2735(0))), op=FixAdd, results=List(x2594.writeAddr))
      RAStage(operands=List(CU.ctr(x2669(0)), Const(1)), op=FixAdd, results=List(x2736))
      RAStage(operands=List(x2736, Const(32)), op=FixMod, results=List(x2737))
      RAStage(operands=List(x2737, Const(64)), op=FixMul, results=List(b2899))
      RAStage(operands=List(b2899, CU.ctr(x2735(0))), op=FixAdd, results=List(x2594.readAddr))
    }
    val x2613 = StreamController(name="x2613",parent=x2816) { implicit CU => 
      val x2613_unit = CounterChain(name = "x2613_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x2605_0 = Pipeline(name="x2605_0",parent=x2613) { implicit CU => 
      val x2598 = CU.temp
      val x2600 = ScalarBuffer(name="x2600").wtPort(exp_lut_da)
      val x2605_unit = CounterChain(name = "x2605_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(Const(0), Const(3)), op=FixSla, results=List(x2598))
      Stage(operands=List(x2598, CU.load(x2600)), op=FixAdd, results=List(CU.scalarOut(x2595_b2869_x2604_b2871_s)))
      Stage(operands=List(Const(72)), op=Bypass, results=List(CU.scalarOut(x2595_b2870_x2604_b2872_s)))
    }
    val x2606 = MemoryController(name="x2606",parent=x2613,offchip=exp_lut_oc, mctpe=TileLoad) { implicit CU => 
      val x2595_b2870 = ScalarFIFO(size=1,name="size").wtPort(x2595_b2870_x2604_b2872_s)
      val x2595_b2869 = ScalarFIFO(size=1,name="offset").wtPort(x2595_b2869_x2604_b2871_s)
      CU.newSout("data", x2596_x2606_data_s)
    }
    val x2612_0 = Pipeline(name="x2612_0",parent=x2613) { implicit CU => 
      val x2596 = ScalarFIFO(size=1,name="x2596").wtPort(x2596_x2606_data_s)
      val ctr1 = Counter(min=Const(0), max=Const(9), step=Const(1), par=1) // Counter
      val x2608 = CounterChain(name = "x2608", ctr1).iter(9)
      Stage(operands=List(CU.load(x2596)), op=Bypass, results=List(CU.scalarOut(x2593_x2611_s)))
    }
    val x2638 = StreamController(name="x2638",parent=x2816) { implicit CU => 
      val ctr2 = Counter(min=Const(0), max=Const(32), step=Const(1), par=1) // Counter
      val x2615 = CounterChain(name = "x2615", ctr2).iter(32)
    }
    val x2629_0 = Pipeline(name="x2629_0",parent=x2638) { implicit CU => 
      val x2622 = CU.temp
      val x2619 = CU.temp
      val x2621 = CU.temp
      val x2624 = ScalarBuffer(name="x2624").wtPort(grid_dram_da)
      val x2615 = CounterChain.copy("x2638", "x2615")
      val x2629_unit = CounterChain(name = "x2629_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x2615(0)), Const(6)), op=FixSla, results=List(x2619))
      Stage(operands=List(x2619, Const(0)), op=FixAdd, results=List(x2621))
      Stage(operands=List(x2621, Const(2)), op=FixSla, results=List(x2622))
      Stage(operands=List(x2622, CU.load(x2624)), op=FixAdd, results=List(CU.scalarOut(x2616_b2873_x2628_b2875_s)))
      Stage(operands=List(Const(256)), op=Bypass, results=List(CU.scalarOut(x2616_b2874_x2628_b2876_s)))
    }
    val x2630 = MemoryController(name="x2630",parent=x2638,offchip=grid_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x2616_b2873 = ScalarFIFO(size=1,name="offset").wtPort(x2616_b2873_x2628_b2875_s)
      val x2616_b2874 = ScalarFIFO(size=1,name="size").wtPort(x2616_b2874_x2628_b2876_s)
      CU.newVout("data", x2617_x2630_data_v)
    }
    val x2637_0 = Pipeline(name="x2637_0",parent=x2638) { implicit CU => 
      val x2617 = VectorFIFO(size=1,name="x2617").wtPort(x2617_x2630_data_v)
      val ctr3 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x2632 = CounterChain(name = "x2632", ctr3).iter(4)
      Stage(operands=List(CU.load(x2617)), op=Bypass, results=List(CU.scalarOut(x2594_x2636_s)))
    }
    val x2639_dsp0 = MemoryPipeline(name="x2639_dsp0",parent="x2816") { implicit CU => 
      val b2909 = CU.temp
      val b2883 = CU.temp
      val x2662 = ScalarFIFO(size=1,name="x2662").wtPort(x2639_x2662_s)
      val x2641 = CounterChain.copy("x2664", "x2641")
      val x2669 = CounterChain.copy("x2784", "x2669")
      val x2658 = CounterChain.copy("x2663_0", "x2658")
      val x2735 = CounterChain.copy("x2782", "x2735")
      val x2639 = SRAM(size=2048,name="x2639",banking = Strided(1)).wtPort(x2662.readPort).rdPort(x2639_0_s)
      WAStage(operands=List(CU.ctr(x2641(0)), Const(64)), op=FixMul, results=List(b2883))
      WAStage(operands=List(b2883, CU.ctr(x2658(0))), op=FixAdd, results=List(x2639.writeAddr))
      RAStage(operands=List(CU.ctr(x2669(0)), Const(64)), op=FixMul, results=List(b2909))
      RAStage(operands=List(b2909, CU.ctr(x2735(0))), op=FixAdd, results=List(x2639.readAddr))
    }
    val x2664 = StreamController(name="x2664",parent=x2816) { implicit CU => 
      val ctr4 = Counter(min=Const(0), max=Const(32), step=Const(1), par=1) // Counter
      val x2641 = CounterChain(name = "x2641", ctr4).iter(32)
    }
    val x2655_0 = Pipeline(name="x2655_0",parent=x2664) { implicit CU => 
      val x2647 = CU.temp
      val x2645 = CU.temp
      val x2648 = CU.temp
      val x2650 = ScalarBuffer(name="x2650").wtPort(bias_dram_da)
      val x2641 = CounterChain.copy("x2664", "x2641")
      val x2655_unit = CounterChain(name = "x2655_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x2641(0)), Const(6)), op=FixSla, results=List(x2645))
      Stage(operands=List(x2645, Const(0)), op=FixAdd, results=List(x2647))
      Stage(operands=List(x2647, Const(2)), op=FixSla, results=List(x2648))
      Stage(operands=List(x2648, CU.load(x2650)), op=FixAdd, results=List(CU.scalarOut(x2642_b2879_x2654_b2881_s)))
      Stage(operands=List(Const(256)), op=Bypass, results=List(CU.scalarOut(x2642_b2880_x2654_b2882_s)))
    }
    val x2656 = MemoryController(name="x2656",parent=x2664,offchip=bias_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x2642_b2879 = ScalarFIFO(size=1,name="offset").wtPort(x2642_b2879_x2654_b2881_s)
      val x2642_b2880 = ScalarFIFO(size=1,name="size").wtPort(x2642_b2880_x2654_b2882_s)
      CU.newVout("data", x2643_x2656_data_v)
    }
    val x2663_0 = Pipeline(name="x2663_0",parent=x2664) { implicit CU => 
      val x2643 = VectorFIFO(size=1,name="x2643").wtPort(x2643_x2656_data_v)
      val ctr5 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x2658 = CounterChain(name = "x2658", ctr5).iter(4)
      Stage(operands=List(CU.load(x2643)), op=Bypass, results=List(CU.scalarOut(x2639_x2662_s)))
    }
    val x2785 = MetaPipeline(name="x2785",parent=x2816) { implicit CU => 
      val x2575 = ScalarBuffer(name="x2575").wtPort(iters_argin)
      val ctr6 = Counter(min=Const(0), max=x2575.readPort, step=Const(1), par=1) // Counter
      val x2667 = CounterChain(name = "x2667", ctr6).iter(1)
    }
    val x2784 = MetaPipeline(name="x2784",parent=x2785) { implicit CU => 
      val ctr7 = Counter(min=Const(0), max=Const(32), step=Const(1), par=2) // Counter
      val x2669 = CounterChain(name = "x2669", ctr7).iter(16)
    }
    val x2676_0 = Pipeline(name="x2676_0",parent=x2784) { implicit CU => 
      val x2672 = CU.temp
      val x2669 = CounterChain.copy("x2784", "x2669")
      val x2676_unit = CounterChain(name = "x2676_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x2669(0)), Const(2)), op=FixMod, results=List(x2672))
      Stage(operands=List(x2672), op=FixNeg, results=List(CU.scalarOut(x2670_x2675_s)))
    }
    val x2681_0 = Pipeline(name="x2681_0",parent=x2784) { implicit CU => 
      val x2677 = CU.temp
      val x2669 = CounterChain.copy("x2784", "x2669")
      val x2681_unit = CounterChain(name = "x2681_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x2669(0)), Const(2)), op=FixMod, results=List(x2677))
      Stage(operands=List(x2677), op=FixNeg, results=List(CU.scalarOut(x2671_x2680_s)))
    }
    val x2732 = StreamController(name="x2732",parent=x2784) { implicit CU => 
      val x2670 = ScalarBuffer(name="x2670").wtPort(x2670_x2675_s)
      val ctr8 = Counter(min=x2670.readPort, max=Const(64), step=Const(1), par=1) // Counter
      val x2685 = CounterChain(name = "x2685", ctr8).iter(64)
    }
    val x2732_0 = Pipeline(name="x2732_0",parent=x2732) { implicit CU => 
      val x2724 = CU.temp
      val x2725 = CU.temp
      val x2669 = CounterChain.copy("x2784", "x2669")
      val x2685 = CounterChain.copy("x2732", "x2685")
      Stage(operands=List(Const(0), CU.ctr(x2685(0))), op=FixLt, results=List(x2724))
      Stage(operands=List(CU.ctr(x2685(0)), Const(64)), op=FixLt, results=List(x2725))
      Stage(operands=List(x2724, x2725), op=BitAnd, results=List(CU.scalarOut(bus_606_s)))
      Stage(operands=List(CU.ctr(x2685(0)), Const(1)), op=FixSub, results=List())
      Stage(operands=List(CU.ctr(x2669(0)), Const(1)), op=FixSub, results=List())
      Stage(operands=List(CU.ctr(x2669(0)), Const(1)), op=FixAdd, results=List())
    }
    val x2732_1 = Pipeline(name="x2732_1",parent=x2732) { implicit CU => 
      val x2706 = CU.temp
      val x2708 = CU.temp
      val x2707 = CU.temp
      val x2702 = ScalarFIFO(size=1,name="x2702").wtPort(x2594_2_s)
      val x2704 = ScalarFIFO(size=1,name="x2704").wtPort(x2594_1_s)
      val x2694 = ScalarFIFO(size=1,name="x2694").wtPort(x2594_5_s)
      val x2698 = ScalarFIFO(size=1,name="x2698").wtPort(x2594_3_s)
      val x2690 = ScalarFIFO(size=1,name="x2690").wtPort(x2594_6_s)
      val x2713 = ScalarFIFO(size=1,name="x2713").wtPort(x2639_0_s)
      val x2685 = CounterChain.copy("x2732", "x2685")
      Stage(operands=List(CU.ctr(x2685(0)), Const(1)), op=FixAdd, results=List())
      Stage(operands=List(CU.load(x2690), CU.load(x2694)), op=FixAdd, results=List(x2706))
      Stage(operands=List(x2706, CU.load(x2698)), op=FixAdd, results=List(x2707))
      Stage(operands=List(x2707, CU.load(x2702)), op=FixAdd, results=List(x2708))
      Stage(operands=List(x2708, CU.load(x2704)), op=FixMul, results=List())
      Stage(operands=List(CU.load(x2713), CU.load(x2704)), op=FixMul, results=List(CU.scalarOut(bus_622_s)))
    }
    val x2732_2 = Pipeline(name="x2732_2",parent=x2732) { implicit CU => 
      val x2722 = CU.temp
      val x2719 = CU.temp
      val x2716 = CU.temp
      val x2723 = CU.temp
      val x2720 = CU.temp
      val x2715 = ScalarFIFO(size=1,name="x2715").wtPort(bus_622_s)
      val x2577 = ScalarBuffer(name="x2577").wtPort(exp_posbias_argin)
      val x2711 = ScalarFIFO(size=1,name="x2711").wtPort(x2593_0_s)
      val x2576 = ScalarBuffer(name="x2576").wtPort(exp_negbias_argin)
      Stage(operands=List(CU.load(x2715), Const(0)), op=FixLt, results=List(x2716))
      Stage(operands=List(x2716, CU.load(x2577), CU.load(x2576)), op=Mux, results=List(x2719))
      Stage(operands=List(CU.load(x2711), x2719), op=FixMul, results=List(x2720))
      Stage(operands=List(Const(1), x2720), op=FixLt, results=List(x2722))
      Stage(operands=List(x2722, Const(1), Const(1)), op=Mux, results=List(x2723))
      Stage(operands=List(x2723, Const(1)), op=FixEql, results=List(CU.scalarOut(bus_629_s)))
    }
    val x2732_3 = Pipeline(name="x2732_3",parent=x2732) { implicit CU => 
      val x2728 = CU.temp
      val x2729 = CU.temp
      val x2726 = ScalarFIFO(size=1,name="x2726").wtPort(bus_606_s)
      val x2704 = ScalarFIFO(size=1,name="x2704").wtPort(x2594_1_s)
      val x2727 = ScalarFIFO(size=1,name="x2727").wtPort(bus_629_s)
      Stage(operands=List(CU.load(x2726), CU.load(x2727)), op=BitAnd, results=List(x2728))
      Stage(operands=List(CU.load(x2704)), op=FixNeg, results=List(x2729))
      Stage(operands=List(x2728, x2729, CU.load(x2704)), op=Mux, results=List(CU.scalarOut(x2594_x2731_s)))
    }
    val x2782 = StreamController(name="x2782",parent=x2784) { implicit CU => 
      val x2671 = ScalarBuffer(name="x2671").wtPort(x2671_x2680_s)
      val ctr9 = Counter(min=x2671.readPort, max=Const(64), step=Const(1), par=1) // Counter
      val x2735 = CounterChain(name = "x2735", ctr9).iter(64)
    }
    val x2782_0 = Pipeline(name="x2782_0",parent=x2782) { implicit CU => 
      val x2774 = CU.temp
      val x2775 = CU.temp
      val x2669 = CounterChain.copy("x2784", "x2669")
      val x2735 = CounterChain.copy("x2782", "x2735")
      Stage(operands=List(Const(0), CU.ctr(x2735(0))), op=FixLt, results=List(x2774))
      Stage(operands=List(CU.ctr(x2735(0)), Const(64)), op=FixLt, results=List(x2775))
      Stage(operands=List(x2774, x2775), op=BitAnd, results=List(CU.scalarOut(bus_639_s)))
      Stage(operands=List(CU.ctr(x2735(0)), Const(1)), op=FixSub, results=List())
      Stage(operands=List(CU.ctr(x2669(0)), Const(1)), op=FixSub, results=List())
      Stage(operands=List(CU.ctr(x2669(0)), Const(1)), op=FixAdd, results=List())
    }
    val x2782_1 = Pipeline(name="x2782_1",parent=x2782) { implicit CU => 
      val x2758 = CU.temp
      val x2756 = CU.temp
      val x2757 = CU.temp
      val x2744 = ScalarFIFO(size=1,name="x2744").wtPort(x2594_5_s)
      val x2740 = ScalarFIFO(size=1,name="x2740").wtPort(x2594_7_s)
      val x2754 = ScalarFIFO(size=1,name="x2754").wtPort(x2594_1_s)
      val x2763 = ScalarFIFO(size=1,name="x2763").wtPort(x2639_0_s)
      val x2752 = ScalarFIFO(size=1,name="x2752").wtPort(x2594_2_s)
      val x2748 = ScalarFIFO(size=1,name="x2748").wtPort(x2594_4_s)
      val x2735 = CounterChain.copy("x2782", "x2735")
      Stage(operands=List(CU.ctr(x2735(0)), Const(1)), op=FixAdd, results=List())
      Stage(operands=List(CU.load(x2740), CU.load(x2744)), op=FixAdd, results=List(x2756))
      Stage(operands=List(x2756, CU.load(x2748)), op=FixAdd, results=List(x2757))
      Stage(operands=List(x2757, CU.load(x2752)), op=FixAdd, results=List(x2758))
      Stage(operands=List(x2758, CU.load(x2754)), op=FixMul, results=List())
      Stage(operands=List(CU.load(x2763), CU.load(x2754)), op=FixMul, results=List(CU.scalarOut(bus_655_s)))
    }
    val x2782_2 = Pipeline(name="x2782_2",parent=x2782) { implicit CU => 
      val x2772 = CU.temp
      val x2770 = CU.temp
      val x2773 = CU.temp
      val x2766 = CU.temp
      val x2769 = CU.temp
      val x2577 = ScalarBuffer(name="x2577").wtPort(exp_posbias_argin)
      val x2765 = ScalarFIFO(size=1,name="x2765").wtPort(bus_655_s)
      val x2576 = ScalarBuffer(name="x2576").wtPort(exp_negbias_argin)
      val x2761 = ScalarFIFO(size=1,name="x2761").wtPort(x2593_1_s)
      Stage(operands=List(CU.load(x2765), Const(0)), op=FixLt, results=List(x2766))
      Stage(operands=List(x2766, CU.load(x2577), CU.load(x2576)), op=Mux, results=List(x2769))
      Stage(operands=List(CU.load(x2761), x2769), op=FixMul, results=List(x2770))
      Stage(operands=List(Const(1), x2770), op=FixLt, results=List(x2772))
      Stage(operands=List(x2772, Const(1), Const(1)), op=Mux, results=List(x2773))
      Stage(operands=List(x2773, Const(1)), op=FixEql, results=List(CU.scalarOut(bus_662_s)))
    }
    val x2782_3 = Pipeline(name="x2782_3",parent=x2782) { implicit CU => 
      val x2778 = CU.temp
      val x2779 = CU.temp
      val x2776 = ScalarFIFO(size=1,name="x2776").wtPort(bus_639_s)
      val x2777 = ScalarFIFO(size=1,name="x2777").wtPort(bus_662_s)
      val x2754 = ScalarFIFO(size=1,name="x2754").wtPort(x2594_1_s)
      Stage(operands=List(CU.load(x2776), CU.load(x2777)), op=BitAnd, results=List(x2778))
      Stage(operands=List(CU.load(x2754)), op=FixNeg, results=List(x2779))
      Stage(operands=List(x2778, x2779, CU.load(x2754)), op=Mux, results=List(CU.scalarOut(x2594_x2781_s)))
    }
    val x2815 = StreamController(name="x2815",parent=x2816) { implicit CU => 
      val ctr10 = Counter(min=Const(0), max=Const(32), step=Const(1), par=1) // Counter
      val x2787 = CounterChain(name = "x2787", ctr10).iter(32)
    }
    val x2811 = Sequential(name="x2811",parent=x2815) { implicit CU => 
      val x2811_unit = CounterChain(name = "x2811_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x2802_0 = Pipeline(name="x2802_0",parent=x2811) { implicit CU => 
      val x2795 = CU.temp
      val x2794 = CU.temp
      val x2792 = CU.temp
      val x2797 = ScalarBuffer(name="x2797").wtPort(grid_dram_da)
      val x2787 = CounterChain.copy("x2815", "x2787")
      val x2802_unit = CounterChain(name = "x2802_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x2787(0)), Const(6)), op=FixSla, results=List(x2792))
      Stage(operands=List(x2792, Const(0)), op=FixAdd, results=List(x2794))
      Stage(operands=List(x2794, Const(2)), op=FixSla, results=List(x2795))
      Stage(operands=List(x2795, CU.load(x2797)), op=FixAdd, results=List(CU.scalarOut(x2788_b2913_x2801_b2915_s)))
      Stage(operands=List(Const(256)), op=Bypass, results=List(CU.scalarOut(x2788_b2914_x2801_b2916_s)))
    }
    val x2810_0 = Pipeline(name="x2810_0",parent=x2811) { implicit CU => 
      val x2806 = ScalarFIFO(size=1,name="x2806").wtPort(x2594_0_s)
      val ctr11 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x2804 = CounterChain(name = "x2804", ctr11).iter(4)
      Stage(operands=List(CU.load(x2806)), op=Bypass, results=List(CU.vecOut(x2789_x2809_v)))
    }
    val x2812 = MemoryController(name="x2812",parent=x2815,offchip=grid_dram_oc, mctpe=TileStore) { implicit CU => 
      val x2789 = VectorFIFO(size=1,name="data").wtPort(x2789_x2809_v)
      val x2788_b2914 = ScalarFIFO(size=1,name="size").wtPort(x2788_b2914_x2801_b2916_s)
      val x2788_b2913 = ScalarFIFO(size=1,name="offset").wtPort(x2788_b2913_x2801_b2915_s)
    }
    
  }
}
