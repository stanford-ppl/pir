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
    val x2678_oc = OffChip("x2678")
    val x2817_x2869_x2875_v = Vector("x2817_x2869_x2875")
    val x2707_argin = ArgIn("x2707")
    val x2732_x2757_data_v = Vector("x2732_x2757_data")
    val x2706_x2714_data_v = Vector("x2706_x2714_data")
    val x2688_b2961_x2695_b2963_s = Scalar("x2688_b2961_x2695_b2963")
    val x2689_x2697_data_v = Vector("x2689_x2697_data")
    val x2760_x2767_s = Scalar("x2760_x2767")
    val x2688_b2962_x2695_b2964_s = Scalar("x2688_b2962_x2695_b2964")
    val x2731_b2971_x2755_b2979_s = Scalar("x2731_b2971_x2755_b2979")
    val x2817_x2857_x2862_v = Vector("x2817_x2857_x2862")
    val x2681_oc = OffChip("x2681")
    val x2821_x2837_v = Vector("x2821_x2837")
    val x2673_argin = ArgIn("x2673")
    val x2817_x2861_v = Vector("x2817_x2861")
    val x2883_argin = ArgIn("x2883")
    val x2821_x2845_x2850_v = Vector("x2821_x2845_x2850")
    val x2822_x2849_v = Vector("x2822_x2849")
    val x2727_x2828_x2838_v = Vector("x2727_x2828_x2838")
    val x2690_argin = ArgIn("x2690")
    val bus_367_s = Scalar("bus_367")
    val x2759_x2765_s = Scalar("x2759_x2765")
    val x2730_b2970_x2748_b2978_s = Scalar("x2730_b2970_x2748_b2978")
    val x2733_argin = ArgIn("x2733")
    val x2880_b3005_x2890_b3007_s = Scalar("x2880_b3005_x2890_b3007")
    val bus_375_s = Scalar("bus_375")
    val x2705_b2966_x2712_b2968_s = Scalar("x2705_b2966_x2712_b2968")
    val x2788_b2982_x2799_b2984_s = Scalar("x2788_b2982_x2799_b2984")
    val x2730_b2969_x2748_b2977_s = Scalar("x2730_b2969_x2748_b2977")
    val x2789_x2801_data_v = Vector("x2789_x2801_data")
    val bus_382_s = Scalar("bus_382")
    val x2723_x2870_x2875_v = Vector("x2723_x2870_x2875")
    val x2731_b2972_x2755_b2980_s = Scalar("x2731_b2972_x2755_b2980")
    val x2679_oc = OffChip("x2679")
    val x2731_b2973_x2755_b2981_s = Scalar("x2731_b2973_x2755_b2981")
    val x2788_b2983_x2799_b2985_s = Scalar("x2788_b2983_x2799_b2985")
    val x2728_x2827_x2838_v = Vector("x2728_x2827_x2838")
    val x2758_x2763_s = Scalar("x2758_x2763")
    val bus_364_s = Scalar("bus_364")
    val x2729_x2814_s = Scalar("x2729_x2814")
    val bus_366_s = Scalar("bus_366")
    val x2676_oc = OffChip("x2676")
    val x2723_x2874_v = Vector("x2723_x2874")
    val x2705_b2965_x2712_b2967_s = Scalar("x2705_b2965_x2712_b2967")
    val x2680_oc = OffChip("x2680")
    val x2880_b3004_x2890_b3006_s = Scalar("x2880_b3004_x2890_b3006")
    val x2821_x2846_x2850_v = Vector("x2821_x2846_x2850")
    val bus_380_s = Scalar("bus_380")
    val x2790_argin = ArgIn("x2790")
    val x2822_x2856_x2862_v = Vector("x2822_x2856_x2862")
    val x2723_x2895_x2899_v = Vector("x2723_x2895_x2899")
    val x2687_x2829_x2838_v = Vector("x2687_x2829_x2838")
    val x2686_x2830_x2838_v = Vector("x2686_x2830_x2838")
    val x2909 = Sequential(name="x2909",parent=top) { implicit CU => 
    }
    val x2686_dsp0 = MemoryPipeline(name="x2686_dsp0",parent="x2909") { implicit CU => 
      val x2702_x2702 =  VectorFIFO(size=1).wtPort(x2689_x2697_data_v)
      val x2699 = CounterChain.copy("x2703", "x2699")
      val x2824 = CounterChain.copy("x2838_0", "x2824")
      val x2686_x2830 =  SRAM(size=96,banking = Strided(1)).wtPort(x2702_x2702.readPort).rdPort(x2686_x2830_x2838_v).rdAddr(x2824(0)).wtAddr(x2699(0))
      var stage: List[Stage] = Nil
    }
    val x2687_dsp0 = MemoryPipeline(name="x2687_dsp0",parent="x2909") { implicit CU => 
      val x2719_x2719 =  VectorFIFO(size=1).wtPort(x2706_x2714_data_v)
      val x2716 = CounterChain.copy("x2720", "x2716")
      val x2824 = CounterChain.copy("x2838_0", "x2824")
      val x2687_x2829 =  SRAM(size=96,banking = Strided(1)).wtPort(x2719_x2719.readPort).rdPort(x2687_x2829_x2838_v).rdAddr(x2824(0)).wtAddr(x2716(0))
      var stage: List[Stage] = Nil
    }
    val x2704 = StreamController(name="x2704",parent=x2909) { implicit CU => 
    }
    val x2696_0 = Pipeline(name="x2696_0",parent=x2704) { implicit CU => 
      val x2690 =  ScalarBuffer().wtPort(x2690_argin)
      var stage: List[Stage] = Nil
      Stage(operands=List(Const(0), CU.load(x2690)), op=FixAdd, results=List(CU.scalarOut(x2688_b2961_x2695_b2963_s)))
      Stage(operands=List(Const(384)), op=Bypass, results=List(CU.scalarOut(x2688_b2962_x2695_b2964_s)))
    }
    val x2697 = MemoryController(name="x2697",parent=x2704,offchip=x2679_oc, mctpe=TileLoad) { implicit CU => 
      val x2688_b2962_x2697 =  ScalarFIFO(name="size",size=1).wtPort(x2688_b2962_x2695_b2964_s)
      val x2688_b2961_x2697 =  ScalarFIFO(name="offset",size=1).wtPort(x2688_b2961_x2695_b2963_s)
      CU.newVout("data", x2689_x2697_data_v)
    }
    val x2703 = Pipeline(name="x2703",parent=x2704) { implicit CU => 
      val ctr1 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x2699 = CounterChain(name = "x2699", ctr1).iter(6)
      var stage: List[Stage] = Nil
    }
    val x2721 = StreamController(name="x2721",parent=x2909) { implicit CU => 
    }
    val x2713_0 = Pipeline(name="x2713_0",parent=x2721) { implicit CU => 
      val x2707 =  ScalarBuffer().wtPort(x2707_argin)
      var stage: List[Stage] = Nil
      Stage(operands=List(Const(0), CU.load(x2707)), op=FixAdd, results=List(CU.scalarOut(x2705_b2965_x2712_b2967_s)))
      Stage(operands=List(Const(384)), op=Bypass, results=List(CU.scalarOut(x2705_b2966_x2712_b2968_s)))
    }
    val x2714 = MemoryController(name="x2714",parent=x2721,offchip=x2680_oc, mctpe=TileLoad) { implicit CU => 
      val x2705_b2966_x2714 =  ScalarFIFO(name="size",size=1).wtPort(x2705_b2966_x2712_b2968_s)
      val x2705_b2965_x2714 =  ScalarFIFO(name="offset",size=1).wtPort(x2705_b2965_x2712_b2967_s)
      CU.newVout("data", x2706_x2714_data_v)
    }
    val x2720 = Pipeline(name="x2720",parent=x2721) { implicit CU => 
      val ctr2 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x2716 = CounterChain(name = "x2716", ctr2).iter(6)
      var stage: List[Stage] = Nil
    }
    val x2723_dsp0 = MemoryPipeline(name="x2723_dsp0",parent="x2877") { implicit CU => 
      val b3008 = CU.temp
      val b3002 = CU.temp
      val x2874_x2874 =  VectorFIFO(size=1).wtPort(x2723_x2874_v)
      val x2867 = CounterChain.copy("x2875_0", "x2867")
      val x2879 = CounterChain.copy("x2908", "x2879")
      val x2893 = CounterChain.copy("x2899", "x2893")
      val x2723_x2895 =  SRAM(size=9216,banking = Strided(1)).wtPort(x2874_x2874.readPort).rdPort(x2723_x2895_x2899_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x2867(0)), Const(96)), op=FixMul, results=List(b3002))
      WAStage(operands=List(b3002, CU.ctr(x2867(1))), op=FixAdd, results=List(x2723_x2895.writeAddr))
      RAStage(operands=List(CU.ctr(x2879(0)), Const(96)), op=FixMul, results=List(b3008))
      RAStage(operands=List(b3008, CU.ctr(x2893(0))), op=FixAdd, results=List(x2723_x2895.readAddr))
    }
    val x2723_dsp1 = MemoryPipeline(name="x2723_dsp1",parent="x2877") { implicit CU => 
      val b3000 = CU.temp
      val b3002 = CU.temp
      val x2874_x2874 =  VectorFIFO(size=1).wtPort(x2723_x2874_v)
      val x2867 = CounterChain.copy("x2875_0", "x2867")
      val x2723_x2870 =  SRAM(size=9216,banking = NoBanking()).wtPort(x2874_x2874.readPort).rdPort(x2723_x2870_x2875_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x2867(0)), Const(96)), op=FixMul, results=List(b3002))
      WAStage(operands=List(b3002, CU.ctr(x2867(1))), op=FixAdd, results=List(x2723_x2870.writeAddr))
      RAStage(operands=List(CU.ctr(x2867(0)), Const(96)), op=FixMul, results=List(b3000))
      RAStage(operands=List(b3000, CU.ctr(x2867(1))), op=FixAdd, results=List(x2723_x2870.readAddr))
    }
    val x2877 = MetaPipeline(name="x2877",parent=x2909) { implicit CU => 
      val x2673_x2724 =  ScalarBuffer().wtPort(x2673_argin)
      val ctr3 = Counter(min=Const(0), max=x2673_x2724.load, step=Const(20), par=1) // Counter
      val x2726 = CounterChain(name = "x2726", ctr3).iter(360000)
    }
    val x2727_dsp0 = MemoryPipeline(name="x2727_dsp0",parent="x2877") { implicit CU => 
      val x2782_x2782 =  VectorFIFO(size=1).wtPort(x2732_x2757_data_v)
      val x2758_x2772 =  ScalarBuffer().wtPort(x2758_x2763_s)
      val x2771 = CounterChain.copy("x2783", "x2771")
      val x2820 = CounterChain.copy("x2864", "x2820")
      val x2727_x2828 =  SRAM(size=20,banking = Strided(1)).wtPort(x2782_x2782.readPort).rdPort(x2727_x2828_x2838_v).rdAddr(x2820(0))
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x2771(0)), CU.load(x2758_x2772)), op=FixSub, results=List(x2727_x2828.writeAddr))
      WAStage(operands=List(CU.ctr(x2771(0)), CU.load(x2758_x2772)), op=FixSub, results=List())
    }
    val x2728_dsp0 = MemoryPipeline(name="x2728_dsp0",parent="x2877") { implicit CU => 
      val b2986 = CU.temp
      val b2988 = CU.temp
      val x2808_x2808 =  VectorFIFO(size=1).wtPort(x2789_x2801_data_v)
      val x2824 = CounterChain.copy("x2838_0", "x2824")
      val x2803 = CounterChain.copy("x2809", "x2803")
      val x2820 = CounterChain.copy("x2864", "x2820")
      val x2787 = CounterChain.copy("x2810", "x2787")
      val x2728_x2827 =  SRAM(size=1920,banking = Strided(1)).wtPort(x2808_x2808.readPort).rdPort(x2728_x2827_x2838_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x2787(0)), Const(96)), op=FixMul, results=List(b2986))
      WAStage(operands=List(b2986, CU.ctr(x2803(0))), op=FixAdd, results=List(x2728_x2827.writeAddr))
      RAStage(operands=List(CU.ctr(x2820(0)), Const(96)), op=FixMul, results=List(b2988))
      RAStage(operands=List(b2988, CU.ctr(x2824(0))), op=FixAdd, results=List(x2728_x2827.readAddr))
    }
    val x2785 = StreamController(name="x2785",parent=x2877) { implicit CU => 
    }
    val x2756 = StreamController(name="x2756",parent=x2785) { implicit CU => 
    }
    val x2756_0 = Pipeline(name="x2756_0",parent=x2756) { implicit CU => 
      val x2734 = CU.temp
      val x2740 = CU.temp
      val x2726 = CounterChain.copy("x2877", "x2726")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x2726(0)), Const(4)), op=FixMul, results=List(x2734, CU.scalarOut(bus_364_s)))
      Stage(operands=List(x2734, Const(64)), op=FixMod, results=List(x2740, CU.scalarOut(bus_366_s)))
      Stage(operands=List(x2740, Const(4)), op=FixDiv, results=List(CU.scalarOut(bus_367_s), CU.scalarOut(x2731_b2972_x2755_b2980_s)))
    }
    val x2756_1 = Pipeline(name="x2756_1",parent=x2756) { implicit CU => 
      val x2751 = CU.temp
      val x2736 = CU.temp
      val x2738 = CU.temp
      val x2739 = CU.temp
      val x2752 = CU.temp
      val x2735 = CU.temp
      val x2737 = CU.temp
      val x2734 =  ScalarFIFO(size=1).wtPort(bus_364_s)
      val x2740 =  ScalarFIFO(size=1).wtPort(bus_366_s)
      val x2749 =  ScalarFIFO(size=1).wtPort(bus_367_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x2734), Const(80)), op=FixAdd, results=List(x2735))
      Stage(operands=List(x2735, Const(4)), op=FixDiv, results=List(CU.scalarOut(x2731_b2973_x2755_b2981_s)))
      Stage(operands=List(x2735, Const(64)), op=FixMod, results=List(x2736))
      Stage(operands=List(x2736, Const(0)), op=FixEql, results=List(x2737))
      Stage(operands=List(Const(64), x2736), op=FixSub, results=List(x2738))
      Stage(operands=List(x2737, Const(0), x2738), op=Mux, results=List(x2739, CU.scalarOut(bus_375_s)))
      Stage(operands=List(x2739, Const(4)), op=FixDiv, results=List(x2751))
      Stage(operands=List(Const(20), CU.load(x2749)), op=FixAdd, results=List(x2752))
      Stage(operands=List(x2752, x2751), op=FixAdd, results=List(CU.scalarOut(x2731_b2971_x2755_b2979_s)))
      Stage(operands=List(Const(80), CU.load(x2740)), op=FixAdd, results=List(CU.scalarOut(bus_380_s)))
    }
    val x2756_2 = Pipeline(name="x2756_2",parent=x2756) { implicit CU => 
      val x2734 =  ScalarFIFO(size=1).wtPort(bus_364_s)
      val x2740 =  ScalarFIFO(size=1).wtPort(bus_366_s)
      val x2741 =  ScalarFIFO(size=1).wtPort(bus_380_s)
      val x2739 =  ScalarFIFO(size=1).wtPort(bus_375_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x2741), CU.load(x2739)), op=FixAdd, results=List(CU.scalarOut(x2730_b2970_x2748_b2978_s)))
      Stage(operands=List(CU.load(x2734), CU.load(x2740)), op=FixSub, results=List(CU.scalarOut(bus_382_s)))
    }
    val x2756_3 = Pipeline(name="x2756_3",parent=x2756) { implicit CU => 
      val x2733 =  ScalarBuffer().wtPort(x2733_argin)
      val x2743 =  ScalarFIFO(size=1).wtPort(bus_382_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x2743), CU.load(x2733)), op=FixAdd, results=List(CU.scalarOut(x2730_b2969_x2748_b2977_s)))
    }
    val x2757 = MemoryController(name="x2757",parent=x2785,offchip=x2678_oc, mctpe=TileLoad) { implicit CU => 
      val x2730_b2969_x2757 =  ScalarFIFO(name="offset",size=1).wtPort(x2730_b2969_x2748_b2977_s)
      val x2730_b2970_x2757 =  ScalarFIFO(name="size",size=1).wtPort(x2730_b2970_x2748_b2978_s)
      CU.newVout("data", x2732_x2757_data_v)
    }
    val x2784 = Sequential(name="x2784",parent=x2785) { implicit CU => 
    }
    val x2768_0 = Pipeline(name="x2768_0",parent=x2784) { implicit CU => 
      val x2731_b2972_x2761_b2975 =  ScalarFIFO(size=16).wtPort(x2731_b2972_x2755_b2980_s)
      val x2731_b2971_x2761_b2974 =  ScalarFIFO(size=16).wtPort(x2731_b2971_x2755_b2979_s)
      val x2731_b2973_x2761_b2976 =  ScalarFIFO(size=16).wtPort(x2731_b2973_x2755_b2981_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x2731_b2972_x2761_b2975)), op=Bypass, results=List(CU.scalarOut(x2758_x2763_s)))
      Stage(operands=List(CU.load(x2731_b2973_x2761_b2976)), op=Bypass, results=List(CU.scalarOut(x2759_x2765_s)))
      Stage(operands=List(CU.load(x2731_b2971_x2761_b2974)), op=Bypass, results=List(CU.scalarOut(x2760_x2767_s)))
    }
    val x2783 = Pipeline(name="x2783",parent=x2784) { implicit CU => 
      val x2759_x2773 =  ScalarBuffer().wtPort(x2759_x2765_s)
      val x2758_x2772 =  ScalarBuffer().wtPort(x2758_x2763_s)
      val x2760_x2769 =  ScalarBuffer().wtPort(x2760_x2767_s)
      val ctr4 = Counter(min=Const(0), max=x2760_x2769.load, step=Const(1), par=16) // Counter
      val x2771 = CounterChain(name = "x2771", ctr4).iter(1)
      var stage: List[Stage] = Nil
    }
    val x2810 = StreamController(name="x2810",parent=x2877) { implicit CU => 
      val ctr5 = Counter(min=Const(0), max=Const(20), step=Const(1), par=1) // Counter
      val x2787 = CounterChain(name = "x2787", ctr5).iter(20)
    }
    val x2800_0 = Pipeline(name="x2800_0",parent=x2810) { implicit CU => 
      val x2793 = CU.temp
      val x2792 = CU.temp
      val x2791 = CU.temp
      val x2790 =  ScalarBuffer().wtPort(x2790_argin)
      val x2726 = CounterChain.copy("x2877", "x2726")
      val x2787 = CounterChain.copy("x2810", "x2787")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x2726(0)), CU.ctr(x2787(0))), op=FixAdd, results=List(x2791))
      Stage(operands=List(x2791, Const(96)), op=FixMul, results=List(x2792))
      Stage(operands=List(x2792, Const(4)), op=FixMul, results=List(x2793))
      Stage(operands=List(x2793, CU.load(x2790)), op=FixAdd, results=List(CU.scalarOut(x2788_b2982_x2799_b2984_s)))
      Stage(operands=List(Const(384)), op=Bypass, results=List(CU.scalarOut(x2788_b2983_x2799_b2985_s)))
    }
    val x2801 = MemoryController(name="x2801",parent=x2810,offchip=x2676_oc, mctpe=TileLoad) { implicit CU => 
      val x2788_b2983_x2801 =  ScalarFIFO(name="size",size=1).wtPort(x2788_b2983_x2799_b2985_s)
      val x2788_b2982_x2801 =  ScalarFIFO(name="offset",size=1).wtPort(x2788_b2982_x2799_b2984_s)
      CU.newVout("data", x2789_x2801_data_v)
    }
    val x2809 = Pipeline(name="x2809",parent=x2810) { implicit CU => 
      val ctr6 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x2803 = CounterChain(name = "x2803", ctr6).iter(6)
      var stage: List[Stage] = Nil
    }
    val x2815_0 = Pipeline(name="x2815_0",parent=x2877) { implicit CU => 
      val x2812 = CU.temp
      val x2673_x2811 =  ScalarBuffer().wtPort(x2673_argin)
      val x2726 = CounterChain.copy("x2877", "x2726")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x2673_x2811), CU.ctr(x2726(0))), op=FixSub, results=List(x2812))
      Stage(operands=List(x2812, Const(20)), op=FixMin, results=List(CU.scalarOut(x2729_x2814_s)))
    }
    val x2817_dsp0 = MemoryPipeline(name="x2817_dsp0",parent="x2864") { implicit CU => 
      val b2996 = CU.temp
      val b2998 = CU.temp
      val x2861_x2861 =  VectorFIFO(size=1).wtPort(x2817_x2861_v)
      val x2853 = CounterChain.copy("x2862_0", "x2853")
      val x2867 = CounterChain.copy("x2875_0", "x2867")
      val x2817_x2869 =  SRAM(size=9216,banking = Strided(1)).wtPort(x2861_x2861.readPort).rdPort(x2817_x2869_x2875_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x2853(0)), Const(96)), op=FixMul, results=List(b2996))
      WAStage(operands=List(b2996, CU.ctr(x2853(1))), op=FixAdd, results=List(x2817_x2869.writeAddr))
      RAStage(operands=List(CU.ctr(x2867(0)), Const(96)), op=FixMul, results=List(b2998))
      RAStage(operands=List(b2998, CU.ctr(x2867(1))), op=FixAdd, results=List(x2817_x2869.readAddr))
    }
    val x2817_dsp1 = MemoryPipeline(name="x2817_dsp1",parent="x2864") { implicit CU => 
      val b2994 = CU.temp
      val b2996 = CU.temp
      val x2861_x2861 =  VectorFIFO(size=1).wtPort(x2817_x2861_v)
      val x2853 = CounterChain.copy("x2862_0", "x2853")
      val x2817_x2857 =  SRAM(size=9216,banking = Strided(1)).wtPort(x2861_x2861.readPort).rdPort(x2817_x2857_x2862_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x2853(0)), Const(96)), op=FixMul, results=List(b2996))
      WAStage(operands=List(b2996, CU.ctr(x2853(1))), op=FixAdd, results=List(x2817_x2857.writeAddr))
      RAStage(operands=List(CU.ctr(x2853(0)), Const(96)), op=FixMul, results=List(b2994))
      RAStage(operands=List(b2994, CU.ctr(x2853(1))), op=FixAdd, results=List(x2817_x2857.readAddr))
    }
    val x2864 = MetaPipeline(name="x2864",parent=x2877) { implicit CU => 
      val x2729_x2818 =  ScalarBuffer().wtPort(x2729_x2814_s)
      val ctr7 = Counter(min=Const(0), max=x2729_x2818.load, step=Const(1), par=1) // Counter
      val x2820 = CounterChain(name = "x2820", ctr7).iter(1)
    }
    val x2821_dsp0 = MemoryPipeline(name="x2821_dsp0",parent="x2864") { implicit CU => 
      val x2837_x2837 =  VectorFIFO(size=1).wtPort(x2821_x2837_v)
      val x2824 = CounterChain.copy("x2838_0", "x2824")
      val x2841 = CounterChain.copy("x2850_0", "x2841")
      val x2821_x2846 =  SRAM(size=96,banking = Strided(1)).wtPort(x2837_x2837.readPort).rdPort(x2821_x2846_x2850_v).rdAddr(x2841(1)).wtAddr(x2824(0))
      var stage: List[Stage] = Nil
    }
    val x2821_dsp1 = MemoryPipeline(name="x2821_dsp1",parent="x2864") { implicit CU => 
      val x2837_x2837 =  VectorFIFO(size=1).wtPort(x2821_x2837_v)
      val x2824 = CounterChain.copy("x2838_0", "x2824")
      val x2841 = CounterChain.copy("x2850_0", "x2841")
      val x2821_x2845 =  SRAM(size=96,banking = Strided(1)).wtPort(x2837_x2837.readPort).rdPort(x2821_x2845_x2850_v).rdAddr(x2841(0)).wtAddr(x2824(0))
      var stage: List[Stage] = Nil
    }
    val x2822_dsp0 = MemoryPipeline(name="x2822_dsp0",parent="x2864") { implicit CU => 
      val b2990 = CU.temp
      val b2992 = CU.temp
      val x2849_x2849 =  VectorFIFO(size=1).wtPort(x2822_x2849_v)
      val x2841 = CounterChain.copy("x2850_0", "x2841")
      val x2853 = CounterChain.copy("x2862_0", "x2853")
      val x2822_x2856 =  SRAM(size=9216,banking = Strided(1)).wtPort(x2849_x2849.readPort).rdPort(x2822_x2856_x2862_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x2841(0)), Const(96)), op=FixMul, results=List(b2990))
      WAStage(operands=List(b2990, CU.ctr(x2841(1))), op=FixAdd, results=List(x2822_x2856.writeAddr))
      RAStage(operands=List(CU.ctr(x2853(0)), Const(96)), op=FixMul, results=List(b2992))
      RAStage(operands=List(b2992, CU.ctr(x2853(1))), op=FixAdd, results=List(x2822_x2856.readAddr))
    }
    val x2838_0 = Pipeline(name="x2838_0",parent=x2864) { implicit CU => 
      val x2835 = CU.temp
      val x2834 = CU.temp
      val x2828_x2828 =  VectorFIFO(size=1).wtPort(x2727_x2828_x2838_v)
      val x2827_x2827 =  VectorFIFO(size=1).wtPort(x2728_x2827_x2838_v)
      val x2830_x2830 =  VectorFIFO(size=1).wtPort(x2686_x2830_x2838_v)
      val x2829_x2829 =  VectorFIFO(size=1).wtPort(x2687_x2829_x2838_v)
      val ctr8 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x2824 = CounterChain(name = "x2824", ctr8).iter(6)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x2828_x2828), Const(1)), op=FixEql, results=List(x2834))
      Stage(operands=List(x2834, CU.load(x2829_x2829), CU.load(x2830_x2830)), op=Mux, results=List(x2835))
      Stage(operands=List(CU.load(x2827_x2827), x2835), op=FixSub, results=List(CU.vecOut(x2821_x2837_v)))
    }
    val x2850_0 = Pipeline(name="x2850_0",parent=x2864) { implicit CU => 
      val x2846_x2846 =  VectorFIFO(size=1).wtPort(x2821_x2846_x2850_v)
      val x2845_x2845 =  VectorFIFO(size=1).wtPort(x2821_x2845_x2850_v)
      val ctr9 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val ctr10 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x2841 = CounterChain(name = "x2841", ctr9, ctr10).iter(576)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x2845_x2845), CU.load(x2846_x2846)), op=FixMul, results=List(CU.vecOut(x2822_x2849_v)))
    }
    val x2862_0 = Pipeline(name="x2862_0",parent=x2864) { implicit CU => 
      val x2857_x2857 =  VectorFIFO(size=1).wtPort(x2817_x2857_x2862_v)
      val x2856_x2856 =  VectorFIFO(size=1).wtPort(x2822_x2856_x2862_v)
      val ctr11 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val ctr12 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x2853 = CounterChain(name = "x2853", ctr11, ctr12).iter(36)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x2856_x2856), CU.load(x2857_x2857)), op=FixAdd, results=List(CU.vecOut(x2817_x2861_v)))
    }
    val x2875_0 = Pipeline(name="x2875_0",parent=x2877) { implicit CU => 
      val x2870_x2870 =  VectorFIFO(size=1).wtPort(x2723_x2870_x2875_v)
      val x2869_x2869 =  VectorFIFO(size=1).wtPort(x2817_x2869_x2875_v)
      val ctr13 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val ctr14 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val x2867 = CounterChain(name = "x2867", ctr13, ctr14).iter(9216)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x2869_x2869), CU.load(x2870_x2870)), op=FixAdd, results=List(CU.vecOut(x2723_x2874_v)))
    }
    val x2908 = StreamController(name="x2908",parent=x2909) { implicit CU => 
      val ctr15 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val x2879 = CounterChain(name = "x2879", ctr15).iter(96)
    }
    val x2900 = Sequential(name="x2900",parent=x2908) { implicit CU => 
    }
    val x2891_0 = Pipeline(name="x2891_0",parent=x2900) { implicit CU => 
      val x2884 = CU.temp
      val x2885 = CU.temp
      val x2883 =  ScalarBuffer().wtPort(x2883_argin)
      val x2879 = CounterChain.copy("x2908", "x2879")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x2879(0)), Const(96)), op=FixMul, results=List(x2884))
      Stage(operands=List(x2884, Const(4)), op=FixMul, results=List(x2885))
      Stage(operands=List(x2885, CU.load(x2883)), op=FixAdd, results=List(CU.scalarOut(x2880_b3004_x2890_b3006_s)))
      Stage(operands=List(Const(384)), op=Bypass, results=List(CU.scalarOut(x2880_b3005_x2890_b3007_s)))
    }
    val x2899 = Pipeline(name="x2899",parent=x2900) { implicit CU => 
      val ctr16 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x2893 = CounterChain(name = "x2893", ctr16).iter(6)
      var stage: List[Stage] = Nil
    }
    val x2901 = MemoryController(name="x2901",parent=x2908,offchip=x2681_oc, mctpe=TileStore) { implicit CU => 
      val x2880_b3005_x2901 =  ScalarFIFO(name="size",size=1).wtPort(x2880_b3005_x2890_b3007_s)
      val x2880_b3004_x2901 =  ScalarFIFO(name="offset",size=1).wtPort(x2880_b3004_x2890_b3006_s)
      val x2881_x2901 =  VectorFIFO(name="data",size=1).wtPort(x2723_x2895_x2899_v)
    }
    
  }
}
