import pir.graph
import pir.graph._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.util.enums._
import pir.util._
import pir.PIRApp

object TPCHQ6 extends PIRApp {
  def main(args: String*)(top:Top) = {
    val x2816_b3160_x2824_b3162_s = Scalar("x2816_b3160_x2824_b3162")
    val x2721_argin = ArgIn("x2721")
    val x2683_argin = ArgIn("x2683")
    val x2681_b3132_x2689_b3134_s = Scalar("x2681_b3132_x2689_b3134")
    val x2779_argin = ArgIn("x2779")
    val x2973_x3053_s = Scalar("x2973_x3053")
    val x2760_argin = ArgIn("x2760")
    val x2627_argin = ArgIn("x2627")
    val x2702_argin = ArgIn("x2702")
    val x2854_b3168_x2862_b3170_s = Scalar("x2854_b3168_x2862_b3170")
    val x2719_b3140_x2727_b3142_s = Scalar("x2719_b3140_x2727_b3142")
    val x2912_b3181_x2920_b3183_s = Scalar("x2912_b3181_x2920_b3183")
    val bus_827_v = Vector("bus_827")
    val x2740_x2749_data_v = Vector("x2740_x2749_data")
    val x2659_x3008_x3028_v = Vector("x2659_x3008_x3028")
    val x2894_x2903_data_v = Vector("x2894_x2903_data")
    val x2681_b3133_x2689_b3135_s = Scalar("x2681_b3133_x2689_b3135")
    val x2662_b3128_x2670_b3130_s = Scalar("x2662_b3128_x2670_b3130")
    val x2817_x2826_data_v = Vector("x2817_x2826_data")
    val x2660_x3035_x3055_v = Vector("x2660_x3035_x3055")
    val x2739_b3144_x2747_b3146_s = Scalar("x2739_b3144_x2747_b3146")
    val x2657_x3060_x3082_v = Vector("x2657_x3060_x3082")
    val bus_824_v = Vector("bus_824")
    val x2837_argin = ArgIn("x2837")
    val bus_810_v = Vector("bus_810")
    val x2649_x3059_x3082_v = Vector("x2649_x3059_x3082")
    val x2630_oc = OffChip("x2630")
    val x2778_x2787_data_v = Vector("x2778_x2787_data")
    val x2662_b3129_x2670_b3131_s = Scalar("x2662_b3129_x2670_b3131")
    val x2875_argin = ArgIn("x2875")
    val x2797_x2806_data_v = Vector("x2797_x2806_data")
    val x2758_b3149_x2766_b3151_s = Scalar("x2758_b3149_x2766_b3151")
    val x2873_b3173_x2881_b3175_s = Scalar("x2873_b3173_x2881_b3175")
    val bus_802_v = Vector("bus_802")
    val bus_803_v = Vector("bus_803")
    val x2664_argin = ArgIn("x2664")
    val x2739_b3145_x2747_b3147_s = Scalar("x2739_b3145_x2747_b3147")
    val x2931_b3184_x2939_b3186_s = Scalar("x2931_b3184_x2939_b3186")
    val bus_785_v = Vector("bus_785")
    val x2796_b3157_x2804_b3159_s = Scalar("x2796_b3157_x2804_b3159")
    val x2854_b3169_x2862_b3171_s = Scalar("x2854_b3169_x2862_b3171")
    val x2818_argin = ArgIn("x2818")
    val x2952_argin = ArgIn("x2952")
    val x2646_x2978_x3001_v = Vector("x2646_x2978_x3001")
    val x2971_x2999_s = Scalar("x2971_x2999")
    val x2632_oc = OffChip("x2632")
    val x2647_x3005_x3028_v = Vector("x2647_x3005_x3028")
    val x2855_x2864_data_v = Vector("x2855_x2864_data")
    val x2654_x2979_x3001_v = Vector("x2654_x2979_x3001")
    val x2655_x3006_x3028_v = Vector("x2655_x3006_x3028")
    val bus_837_v = Vector("bus_837")
    val x2912_b3180_x2920_b3182_s = Scalar("x2912_b3180_x2920_b3182")
    val x2682_x2691_data_v = Vector("x2682_x2691_data")
    val x2796_b3156_x2804_b3158_s = Scalar("x2796_b3156_x2804_b3158")
    val x2895_argin = ArgIn("x2895")
    val x2648_x3032_x3055_v = Vector("x2648_x3032_x3055")
    val x2650_x2980_x3001_v = Vector("x2650_x2980_x3001")
    val x2701_x2710_data_v = Vector("x2701_x2710_data")
    val x2835_b3165_x2843_b3167_s = Scalar("x2835_b3165_x2843_b3167")
    val x2874_x2883_data_v = Vector("x2874_x2883_data")
    val x2658_x2981_x3001_v = Vector("x2658_x2981_x3001")
    val x2914_argin = ArgIn("x2914")
    val bus_790_v = Vector("bus_790")
    val x2652_x3034_x3055_v = Vector("x2652_x3034_x3055")
    val x2759_x2768_data_v = Vector("x2759_x2768_data")
    val x2974_x3080_s = Scalar("x2974_x3080")
    val x2836_x2845_data_v = Vector("x2836_x2845_data")
    val x2656_x3033_x3055_v = Vector("x2656_x3033_x3055")
    val bus_793_v = Vector("bus_793")
    val bus_851_s = Scalar("bus_851")
    val x2700_b3137_x2708_b3139_s = Scalar("x2700_b3137_x2708_b3139")
    val x2835_b3164_x2843_b3166_s = Scalar("x2835_b3164_x2843_b3166")
    val x2893_b3176_x2901_b3178_s = Scalar("x2893_b3176_x2901_b3178")
    val x2777_b3153_x2785_b3155_s = Scalar("x2777_b3153_x2785_b3155")
    val x2816_b3161_x2824_b3163_s = Scalar("x2816_b3161_x2824_b3163")
    val x2893_b3177_x2901_b3179_s = Scalar("x2893_b3177_x2901_b3179")
    val x2951_x2960_data_v = Vector("x2951_x2960_data")
    val x2637_x3102_argout = ArgOut("x2637_x3102")
    val x2758_b3148_x2766_b3150_s = Scalar("x2758_b3148_x2766_b3150")
    val bus_786_v = Vector("bus_786")
    val bus_841_v = Vector("bus_841")
    val x2700_b3136_x2708_b3138_s = Scalar("x2700_b3136_x2708_b3138")
    val x2651_x3007_x3028_v = Vector("x2651_x3007_x3028")
    val x2913_x2922_data_v = Vector("x2913_x2922_data")
    val x2634_oc = OffChip("x2634")
    val x2873_b3172_x2881_b3174_s = Scalar("x2873_b3172_x2881_b3174")
    val x2950_b3189_x2958_b3191_s = Scalar("x2950_b3189_x2958_b3191")
    val bus_836_v = Vector("bus_836")
    val x2950_b3188_x2958_b3190_s = Scalar("x2950_b3188_x2958_b3190")
    val bus_844_v = Vector("bus_844")
    val x2636_oc = OffChip("x2636")
    val bus_820_v = Vector("bus_820")
    val x2720_x2729_data_v = Vector("x2720_x2729_data")
    val x2661_x3062_x3082_v = Vector("x2661_x3062_x3082")
    val x2932_x2941_data_v = Vector("x2932_x2941_data")
    val x2798_argin = ArgIn("x2798")
    val x2653_x3061_x3082_v = Vector("x2653_x3061_x3082")
    val x2719_b3141_x2727_b3143_s = Scalar("x2719_b3141_x2727_b3143")
    val x2931_b3185_x2939_b3187_s = Scalar("x2931_b3185_x2939_b3187")
    val x2663_x2672_data_v = Vector("x2663_x2672_data")
    val x2933_argin = ArgIn("x2933")
    val x2856_argin = ArgIn("x2856")
    val x2741_argin = ArgIn("x2741")
    val x2777_b3152_x2785_b3154_s = Scalar("x2777_b3152_x2785_b3154")
    val bus_807_v = Vector("bus_807")
    val x2972_x3026_s = Scalar("x2972_x3026")
    val bus_819_v = Vector("bus_819")
    val x3104 = Sequential(name="x3104",parent=top) { implicit CU => 
    }
    val x3100 = MetaPipeline(name="x3100",parent=x3104) { implicit CU => 
      val x2627_x2643 =  ScalarBuffer().wtPort(x2627_argin)
      val ctr1 = Counter(min=Const(0), max=x2627_x2643.load, step=Const(4000), par=4) // Counter
      val x2645 = CounterChain(name = "x2645", ctr1).iter(1)
    }
    val x2646_dsp0 = MemoryPipeline(name="x2646_dsp0",parent="x3100") { implicit CU => 
      val x2678_x2678 =  VectorFIFO(size=1).wtPort(x2663_x2672_data_v)
      val x2674 = CounterChain.copy("x2679", "x2674")
      val x2976 = CounterChain.copy("x3001", "x2976")
      val x2646_x2978 =  SRAM(size=4000,banking = Strided(1)).wtPort(x2678_x2678.readPort).rdPort(x2646_x2978_x3001_v).rdAddr(x2976(0)).wtAddr(x2674(0))
      var stage: List[Stage] = Nil
    }
    val x2647_dsp0 = MemoryPipeline(name="x2647_dsp0",parent="x3100") { implicit CU => 
      val x2755_x2755 =  VectorFIFO(size=1).wtPort(x2740_x2749_data_v)
      val x2751 = CounterChain.copy("x2756", "x2751")
      val x3003 = CounterChain.copy("x3028", "x3003")
      val x2647_x3005 =  SRAM(size=4000,banking = Strided(1)).wtPort(x2755_x2755.readPort).rdPort(x2647_x3005_x3028_v).rdAddr(x3003(0)).wtAddr(x2751(0))
      var stage: List[Stage] = Nil
    }
    val x2648_dsp0 = MemoryPipeline(name="x2648_dsp0",parent="x3100") { implicit CU => 
      val x2832_x2832 =  VectorFIFO(size=1).wtPort(x2817_x2826_data_v)
      val x2828 = CounterChain.copy("x2833", "x2828")
      val x3030 = CounterChain.copy("x3055", "x3030")
      val x2648_x3032 =  SRAM(size=4000,banking = Strided(1)).wtPort(x2832_x2832.readPort).rdPort(x2648_x3032_x3055_v).rdAddr(x3030(0)).wtAddr(x2828(0))
      var stage: List[Stage] = Nil
    }
    val x2649_dsp0 = MemoryPipeline(name="x2649_dsp0",parent="x3100") { implicit CU => 
      val x2909_x2909 =  VectorFIFO(size=1).wtPort(x2894_x2903_data_v)
      val x2905 = CounterChain.copy("x2910", "x2905")
      val x3057 = CounterChain.copy("x3082", "x3057")
      val x2649_x3059 =  SRAM(size=4000,banking = Strided(1)).wtPort(x2909_x2909.readPort).rdPort(x2649_x3059_x3082_v).rdAddr(x3057(0)).wtAddr(x2905(0))
      var stage: List[Stage] = Nil
    }
    val x2650_dsp0 = MemoryPipeline(name="x2650_dsp0",parent="x3100") { implicit CU => 
      val x2697_x2697 =  VectorFIFO(size=1).wtPort(x2682_x2691_data_v)
      val x2693 = CounterChain.copy("x2698", "x2693")
      val x2976 = CounterChain.copy("x3001", "x2976")
      val x2650_x2980 =  SRAM(size=4000,banking = Strided(1)).wtPort(x2697_x2697.readPort).rdPort(x2650_x2980_x3001_v).rdAddr(x2976(0)).wtAddr(x2693(0))
      var stage: List[Stage] = Nil
    }
    val x2651_dsp0 = MemoryPipeline(name="x2651_dsp0",parent="x3100") { implicit CU => 
      val x2774_x2774 =  VectorFIFO(size=1).wtPort(x2759_x2768_data_v)
      val x2770 = CounterChain.copy("x2775", "x2770")
      val x3003 = CounterChain.copy("x3028", "x3003")
      val x2651_x3007 =  SRAM(size=4000,banking = Strided(1)).wtPort(x2774_x2774.readPort).rdPort(x2651_x3007_x3028_v).rdAddr(x3003(0)).wtAddr(x2770(0))
      var stage: List[Stage] = Nil
    }
    val x2652_dsp0 = MemoryPipeline(name="x2652_dsp0",parent="x3100") { implicit CU => 
      val x2851_x2851 =  VectorFIFO(size=1).wtPort(x2836_x2845_data_v)
      val x2847 = CounterChain.copy("x2852", "x2847")
      val x3030 = CounterChain.copy("x3055", "x3030")
      val x2652_x3034 =  SRAM(size=4000,banking = Strided(1)).wtPort(x2851_x2851.readPort).rdPort(x2652_x3034_x3055_v).rdAddr(x3030(0)).wtAddr(x2847(0))
      var stage: List[Stage] = Nil
    }
    val x2653_dsp0 = MemoryPipeline(name="x2653_dsp0",parent="x3100") { implicit CU => 
      val x2928_x2928 =  VectorFIFO(size=1).wtPort(x2913_x2922_data_v)
      val x2924 = CounterChain.copy("x2929", "x2924")
      val x3057 = CounterChain.copy("x3082", "x3057")
      val x2653_x3061 =  SRAM(size=4000,banking = Strided(1)).wtPort(x2928_x2928.readPort).rdPort(x2653_x3061_x3082_v).rdAddr(x3057(0)).wtAddr(x2924(0))
      var stage: List[Stage] = Nil
    }
    val x2654_dsp0 = MemoryPipeline(name="x2654_dsp0",parent="x3100") { implicit CU => 
      val x2716_x2716 =  VectorFIFO(size=1).wtPort(x2701_x2710_data_v)
      val x2712 = CounterChain.copy("x2717", "x2712")
      val x2976 = CounterChain.copy("x3001", "x2976")
      val x2654_x2979 =  SRAM(size=4000,banking = Strided(1)).wtPort(x2716_x2716.readPort).rdPort(x2654_x2979_x3001_v).rdAddr(x2976(0)).wtAddr(x2712(0))
      var stage: List[Stage] = Nil
    }
    val x2655_dsp0 = MemoryPipeline(name="x2655_dsp0",parent="x3100") { implicit CU => 
      val x2793_x2793 =  VectorFIFO(size=1).wtPort(x2778_x2787_data_v)
      val x2789 = CounterChain.copy("x2794", "x2789")
      val x3003 = CounterChain.copy("x3028", "x3003")
      val x2655_x3006 =  SRAM(size=4000,banking = Strided(1)).wtPort(x2793_x2793.readPort).rdPort(x2655_x3006_x3028_v).rdAddr(x3003(0)).wtAddr(x2789(0))
      var stage: List[Stage] = Nil
    }
    val x2656_dsp0 = MemoryPipeline(name="x2656_dsp0",parent="x3100") { implicit CU => 
      val x2870_x2870 =  VectorFIFO(size=1).wtPort(x2855_x2864_data_v)
      val x2866 = CounterChain.copy("x2871", "x2866")
      val x3030 = CounterChain.copy("x3055", "x3030")
      val x2656_x3033 =  SRAM(size=4000,banking = Strided(1)).wtPort(x2870_x2870.readPort).rdPort(x2656_x3033_x3055_v).rdAddr(x3030(0)).wtAddr(x2866(0))
      var stage: List[Stage] = Nil
    }
    val x2657_dsp0 = MemoryPipeline(name="x2657_dsp0",parent="x3100") { implicit CU => 
      val x2947_x2947 =  VectorFIFO(size=1).wtPort(x2932_x2941_data_v)
      val x2943 = CounterChain.copy("x2948", "x2943")
      val x3057 = CounterChain.copy("x3082", "x3057")
      val x2657_x3060 =  SRAM(size=4000,banking = Strided(1)).wtPort(x2947_x2947.readPort).rdPort(x2657_x3060_x3082_v).rdAddr(x3057(0)).wtAddr(x2943(0))
      var stage: List[Stage] = Nil
    }
    val x2658_dsp0 = MemoryPipeline(name="x2658_dsp0",parent="x3100") { implicit CU => 
      val x2735_x2735 =  VectorFIFO(size=1).wtPort(x2720_x2729_data_v)
      val x2731 = CounterChain.copy("x2736", "x2731")
      val x2976 = CounterChain.copy("x3001", "x2976")
      val x2658_x2981 =  SRAM(size=4000,banking = Strided(1)).wtPort(x2735_x2735.readPort).rdPort(x2658_x2981_x3001_v).rdAddr(x2976(0)).wtAddr(x2731(0))
      var stage: List[Stage] = Nil
    }
    val x2659_dsp0 = MemoryPipeline(name="x2659_dsp0",parent="x3100") { implicit CU => 
      val x2812_x2812 =  VectorFIFO(size=1).wtPort(x2797_x2806_data_v)
      val x2808 = CounterChain.copy("x2813", "x2808")
      val x3003 = CounterChain.copy("x3028", "x3003")
      val x2659_x3008 =  SRAM(size=4000,banking = Strided(1)).wtPort(x2812_x2812.readPort).rdPort(x2659_x3008_x3028_v).rdAddr(x3003(0)).wtAddr(x2808(0))
      var stage: List[Stage] = Nil
    }
    val x2660_dsp0 = MemoryPipeline(name="x2660_dsp0",parent="x3100") { implicit CU => 
      val x2889_x2889 =  VectorFIFO(size=1).wtPort(x2874_x2883_data_v)
      val x2885 = CounterChain.copy("x2890", "x2885")
      val x3030 = CounterChain.copy("x3055", "x3030")
      val x2660_x3035 =  SRAM(size=4000,banking = Strided(1)).wtPort(x2889_x2889.readPort).rdPort(x2660_x3035_x3055_v).rdAddr(x3030(0)).wtAddr(x2885(0))
      var stage: List[Stage] = Nil
    }
    val x2661_dsp0 = MemoryPipeline(name="x2661_dsp0",parent="x3100") { implicit CU => 
      val x2966_x2966 =  VectorFIFO(size=1).wtPort(x2951_x2960_data_v)
      val x2962 = CounterChain.copy("x2967", "x2962")
      val x3057 = CounterChain.copy("x3082", "x3057")
      val x2661_x3062 =  SRAM(size=4000,banking = Strided(1)).wtPort(x2966_x2966.readPort).rdPort(x2661_x3062_x3082_v).rdAddr(x3057(0)).wtAddr(x2962(0))
      var stage: List[Stage] = Nil
    }
    val x2680 = StreamController(name="x2680",parent=x3100) { implicit CU => 
    }
    val x2671_0 = Pipeline(name="x2671_0",parent=x2680) { implicit CU => 
      val x2665 = CU.temp
      val x2664 =  ScalarBuffer().wtPort(x2664_argin)
      val x2645 = CounterChain.copy("x3100", "x2645")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x2645(0)), Const(4)), op=FixMul, results=List(x2665))
      Stage(operands=List(x2665, CU.load(x2664)), op=FixAdd, results=List(CU.scalarOut(x2662_b3128_x2670_b3130_s)))
      Stage(operands=List(Const(16000)), op=Bypass, results=List(CU.scalarOut(x2662_b3129_x2670_b3131_s)))
    }
    val x2672 = MemoryController(name="x2672",parent=x2680,offchip=x2630_oc, mctpe=TileLoad) { implicit CU => 
      val x2662_b3129_x2672 =  ScalarFIFO(name="size",size=1).wtPort(x2662_b3129_x2670_b3131_s)
      val x2662_b3128_x2672 =  ScalarFIFO(name="offset",size=1).wtPort(x2662_b3128_x2670_b3130_s)
      CU.newVout("data", x2663_x2672_data_v)
    }
    val x2679 = Pipeline(name="x2679",parent=x2680) { implicit CU => 
      val ctr2 = Counter(min=Const(0), max=Const(4000), step=Const(1), par=16) // Counter
      val x2674 = CounterChain(name = "x2674", ctr2).iter(250)
      var stage: List[Stage] = Nil
    }
    val x2699 = StreamController(name="x2699",parent=x3100) { implicit CU => 
    }
    val x2690_0 = Pipeline(name="x2690_0",parent=x2699) { implicit CU => 
      val x2684 = CU.temp
      val x2683 =  ScalarBuffer().wtPort(x2683_argin)
      val x2645 = CounterChain.copy("x3100", "x2645")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x2645(0)), Const(4)), op=FixMul, results=List(x2684))
      Stage(operands=List(x2684, CU.load(x2683)), op=FixAdd, results=List(CU.scalarOut(x2681_b3132_x2689_b3134_s)))
      Stage(operands=List(Const(16000)), op=Bypass, results=List(CU.scalarOut(x2681_b3133_x2689_b3135_s)))
    }
    val x2691 = MemoryController(name="x2691",parent=x2699,offchip=x2632_oc, mctpe=TileLoad) { implicit CU => 
      val x2681_b3133_x2691 =  ScalarFIFO(name="size",size=1).wtPort(x2681_b3133_x2689_b3135_s)
      val x2681_b3132_x2691 =  ScalarFIFO(name="offset",size=1).wtPort(x2681_b3132_x2689_b3134_s)
      CU.newVout("data", x2682_x2691_data_v)
    }
    val x2698 = Pipeline(name="x2698",parent=x2699) { implicit CU => 
      val ctr3 = Counter(min=Const(0), max=Const(4000), step=Const(1), par=16) // Counter
      val x2693 = CounterChain(name = "x2693", ctr3).iter(250)
      var stage: List[Stage] = Nil
    }
    val x2718 = StreamController(name="x2718",parent=x3100) { implicit CU => 
    }
    val x2709_0 = Pipeline(name="x2709_0",parent=x2718) { implicit CU => 
      val x2703 = CU.temp
      val x2702 =  ScalarBuffer().wtPort(x2702_argin)
      val x2645 = CounterChain.copy("x3100", "x2645")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x2645(0)), Const(4)), op=FixMul, results=List(x2703))
      Stage(operands=List(x2703, CU.load(x2702)), op=FixAdd, results=List(CU.scalarOut(x2700_b3136_x2708_b3138_s)))
      Stage(operands=List(Const(16000)), op=Bypass, results=List(CU.scalarOut(x2700_b3137_x2708_b3139_s)))
    }
    val x2710 = MemoryController(name="x2710",parent=x2718,offchip=x2634_oc, mctpe=TileLoad) { implicit CU => 
      val x2700_b3136_x2710 =  ScalarFIFO(name="offset",size=1).wtPort(x2700_b3136_x2708_b3138_s)
      val x2700_b3137_x2710 =  ScalarFIFO(name="size",size=1).wtPort(x2700_b3137_x2708_b3139_s)
      CU.newVout("data", x2701_x2710_data_v)
    }
    val x2717 = Pipeline(name="x2717",parent=x2718) { implicit CU => 
      val ctr4 = Counter(min=Const(0), max=Const(4000), step=Const(1), par=16) // Counter
      val x2712 = CounterChain(name = "x2712", ctr4).iter(250)
      var stage: List[Stage] = Nil
    }
    val x2737 = StreamController(name="x2737",parent=x3100) { implicit CU => 
    }
    val x2728_0 = Pipeline(name="x2728_0",parent=x2737) { implicit CU => 
      val x2722 = CU.temp
      val x2721 =  ScalarBuffer().wtPort(x2721_argin)
      val x2645 = CounterChain.copy("x3100", "x2645")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x2645(0)), Const(4)), op=FixMul, results=List(x2722))
      Stage(operands=List(x2722, CU.load(x2721)), op=FixAdd, results=List(CU.scalarOut(x2719_b3140_x2727_b3142_s)))
      Stage(operands=List(Const(16000)), op=Bypass, results=List(CU.scalarOut(x2719_b3141_x2727_b3143_s)))
    }
    val x2729 = MemoryController(name="x2729",parent=x2737,offchip=x2636_oc, mctpe=TileLoad) { implicit CU => 
      val x2719_b3141_x2729 =  ScalarFIFO(name="size",size=1).wtPort(x2719_b3141_x2727_b3143_s)
      val x2719_b3140_x2729 =  ScalarFIFO(name="offset",size=1).wtPort(x2719_b3140_x2727_b3142_s)
      CU.newVout("data", x2720_x2729_data_v)
    }
    val x2736 = Pipeline(name="x2736",parent=x2737) { implicit CU => 
      val ctr5 = Counter(min=Const(0), max=Const(4000), step=Const(1), par=16) // Counter
      val x2731 = CounterChain(name = "x2731", ctr5).iter(250)
      var stage: List[Stage] = Nil
    }
    val x2757 = StreamController(name="x2757",parent=x3100) { implicit CU => 
    }
    val x2748_0 = Pipeline(name="x2748_0",parent=x2757) { implicit CU => 
      val x2742 = CU.temp
      val x2741 =  ScalarBuffer().wtPort(x2741_argin)
      val x2645 = CounterChain.copy("x3100", "x2645")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x2645(0)), Const(4)), op=FixMul, results=List(x2742))
      Stage(operands=List(x2742, CU.load(x2741)), op=FixAdd, results=List(CU.scalarOut(x2739_b3144_x2747_b3146_s)))
      Stage(operands=List(Const(16000)), op=Bypass, results=List(CU.scalarOut(x2739_b3145_x2747_b3147_s)))
    }
    val x2749 = MemoryController(name="x2749",parent=x2757,offchip=x2630_oc, mctpe=TileLoad) { implicit CU => 
      val x2739_b3145_x2749 =  ScalarFIFO(name="size",size=1).wtPort(x2739_b3145_x2747_b3147_s)
      val x2739_b3144_x2749 =  ScalarFIFO(name="offset",size=1).wtPort(x2739_b3144_x2747_b3146_s)
      CU.newVout("data", x2740_x2749_data_v)
    }
    val x2756 = Pipeline(name="x2756",parent=x2757) { implicit CU => 
      val ctr6 = Counter(min=Const(0), max=Const(4000), step=Const(1), par=16) // Counter
      val x2751 = CounterChain(name = "x2751", ctr6).iter(250)
      var stage: List[Stage] = Nil
    }
    val x2776 = StreamController(name="x2776",parent=x3100) { implicit CU => 
    }
    val x2767_0 = Pipeline(name="x2767_0",parent=x2776) { implicit CU => 
      val x2761 = CU.temp
      val x2760 =  ScalarBuffer().wtPort(x2760_argin)
      val x2645 = CounterChain.copy("x3100", "x2645")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x2645(0)), Const(4)), op=FixMul, results=List(x2761))
      Stage(operands=List(x2761, CU.load(x2760)), op=FixAdd, results=List(CU.scalarOut(x2758_b3148_x2766_b3150_s)))
      Stage(operands=List(Const(16000)), op=Bypass, results=List(CU.scalarOut(x2758_b3149_x2766_b3151_s)))
    }
    val x2768 = MemoryController(name="x2768",parent=x2776,offchip=x2632_oc, mctpe=TileLoad) { implicit CU => 
      val x2758_b3148_x2768 =  ScalarFIFO(name="offset",size=1).wtPort(x2758_b3148_x2766_b3150_s)
      val x2758_b3149_x2768 =  ScalarFIFO(name="size",size=1).wtPort(x2758_b3149_x2766_b3151_s)
      CU.newVout("data", x2759_x2768_data_v)
    }
    val x2775 = Pipeline(name="x2775",parent=x2776) { implicit CU => 
      val ctr7 = Counter(min=Const(0), max=Const(4000), step=Const(1), par=16) // Counter
      val x2770 = CounterChain(name = "x2770", ctr7).iter(250)
      var stage: List[Stage] = Nil
    }
    val x2795 = StreamController(name="x2795",parent=x3100) { implicit CU => 
    }
    val x2786_0 = Pipeline(name="x2786_0",parent=x2795) { implicit CU => 
      val x2780 = CU.temp
      val x2779 =  ScalarBuffer().wtPort(x2779_argin)
      val x2645 = CounterChain.copy("x3100", "x2645")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x2645(0)), Const(4)), op=FixMul, results=List(x2780))
      Stage(operands=List(x2780, CU.load(x2779)), op=FixAdd, results=List(CU.scalarOut(x2777_b3152_x2785_b3154_s)))
      Stage(operands=List(Const(16000)), op=Bypass, results=List(CU.scalarOut(x2777_b3153_x2785_b3155_s)))
    }
    val x2787 = MemoryController(name="x2787",parent=x2795,offchip=x2634_oc, mctpe=TileLoad) { implicit CU => 
      val x2777_b3153_x2787 =  ScalarFIFO(name="size",size=1).wtPort(x2777_b3153_x2785_b3155_s)
      val x2777_b3152_x2787 =  ScalarFIFO(name="offset",size=1).wtPort(x2777_b3152_x2785_b3154_s)
      CU.newVout("data", x2778_x2787_data_v)
    }
    val x2794 = Pipeline(name="x2794",parent=x2795) { implicit CU => 
      val ctr8 = Counter(min=Const(0), max=Const(4000), step=Const(1), par=16) // Counter
      val x2789 = CounterChain(name = "x2789", ctr8).iter(250)
      var stage: List[Stage] = Nil
    }
    val x2814 = StreamController(name="x2814",parent=x3100) { implicit CU => 
    }
    val x2805_0 = Pipeline(name="x2805_0",parent=x2814) { implicit CU => 
      val x2799 = CU.temp
      val x2798 =  ScalarBuffer().wtPort(x2798_argin)
      val x2645 = CounterChain.copy("x3100", "x2645")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x2645(0)), Const(4)), op=FixMul, results=List(x2799))
      Stage(operands=List(x2799, CU.load(x2798)), op=FixAdd, results=List(CU.scalarOut(x2796_b3156_x2804_b3158_s)))
      Stage(operands=List(Const(16000)), op=Bypass, results=List(CU.scalarOut(x2796_b3157_x2804_b3159_s)))
    }
    val x2806 = MemoryController(name="x2806",parent=x2814,offchip=x2636_oc, mctpe=TileLoad) { implicit CU => 
      val x2796_b3157_x2806 =  ScalarFIFO(name="size",size=1).wtPort(x2796_b3157_x2804_b3159_s)
      val x2796_b3156_x2806 =  ScalarFIFO(name="offset",size=1).wtPort(x2796_b3156_x2804_b3158_s)
      CU.newVout("data", x2797_x2806_data_v)
    }
    val x2813 = Pipeline(name="x2813",parent=x2814) { implicit CU => 
      val ctr9 = Counter(min=Const(0), max=Const(4000), step=Const(1), par=16) // Counter
      val x2808 = CounterChain(name = "x2808", ctr9).iter(250)
      var stage: List[Stage] = Nil
    }
    val x2834 = StreamController(name="x2834",parent=x3100) { implicit CU => 
    }
    val x2825_0 = Pipeline(name="x2825_0",parent=x2834) { implicit CU => 
      val x2819 = CU.temp
      val x2818 =  ScalarBuffer().wtPort(x2818_argin)
      val x2645 = CounterChain.copy("x3100", "x2645")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x2645(0)), Const(4)), op=FixMul, results=List(x2819))
      Stage(operands=List(x2819, CU.load(x2818)), op=FixAdd, results=List(CU.scalarOut(x2816_b3160_x2824_b3162_s)))
      Stage(operands=List(Const(16000)), op=Bypass, results=List(CU.scalarOut(x2816_b3161_x2824_b3163_s)))
    }
    val x2826 = MemoryController(name="x2826",parent=x2834,offchip=x2630_oc, mctpe=TileLoad) { implicit CU => 
      val x2816_b3160_x2826 =  ScalarFIFO(name="offset",size=1).wtPort(x2816_b3160_x2824_b3162_s)
      val x2816_b3161_x2826 =  ScalarFIFO(name="size",size=1).wtPort(x2816_b3161_x2824_b3163_s)
      CU.newVout("data", x2817_x2826_data_v)
    }
    val x2833 = Pipeline(name="x2833",parent=x2834) { implicit CU => 
      val ctr10 = Counter(min=Const(0), max=Const(4000), step=Const(1), par=16) // Counter
      val x2828 = CounterChain(name = "x2828", ctr10).iter(250)
      var stage: List[Stage] = Nil
    }
    val x2853 = StreamController(name="x2853",parent=x3100) { implicit CU => 
    }
    val x2844_0 = Pipeline(name="x2844_0",parent=x2853) { implicit CU => 
      val x2838 = CU.temp
      val x2837 =  ScalarBuffer().wtPort(x2837_argin)
      val x2645 = CounterChain.copy("x3100", "x2645")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x2645(0)), Const(4)), op=FixMul, results=List(x2838))
      Stage(operands=List(x2838, CU.load(x2837)), op=FixAdd, results=List(CU.scalarOut(x2835_b3164_x2843_b3166_s)))
      Stage(operands=List(Const(16000)), op=Bypass, results=List(CU.scalarOut(x2835_b3165_x2843_b3167_s)))
    }
    val x2845 = MemoryController(name="x2845",parent=x2853,offchip=x2632_oc, mctpe=TileLoad) { implicit CU => 
      val x2835_b3165_x2845 =  ScalarFIFO(name="size",size=1).wtPort(x2835_b3165_x2843_b3167_s)
      val x2835_b3164_x2845 =  ScalarFIFO(name="offset",size=1).wtPort(x2835_b3164_x2843_b3166_s)
      CU.newVout("data", x2836_x2845_data_v)
    }
    val x2852 = Pipeline(name="x2852",parent=x2853) { implicit CU => 
      val ctr11 = Counter(min=Const(0), max=Const(4000), step=Const(1), par=16) // Counter
      val x2847 = CounterChain(name = "x2847", ctr11).iter(250)
      var stage: List[Stage] = Nil
    }
    val x2872 = StreamController(name="x2872",parent=x3100) { implicit CU => 
    }
    val x2863_0 = Pipeline(name="x2863_0",parent=x2872) { implicit CU => 
      val x2857 = CU.temp
      val x2856 =  ScalarBuffer().wtPort(x2856_argin)
      val x2645 = CounterChain.copy("x3100", "x2645")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x2645(0)), Const(4)), op=FixMul, results=List(x2857))
      Stage(operands=List(x2857, CU.load(x2856)), op=FixAdd, results=List(CU.scalarOut(x2854_b3168_x2862_b3170_s)))
      Stage(operands=List(Const(16000)), op=Bypass, results=List(CU.scalarOut(x2854_b3169_x2862_b3171_s)))
    }
    val x2864 = MemoryController(name="x2864",parent=x2872,offchip=x2634_oc, mctpe=TileLoad) { implicit CU => 
      val x2854_b3169_x2864 =  ScalarFIFO(name="size",size=1).wtPort(x2854_b3169_x2862_b3171_s)
      val x2854_b3168_x2864 =  ScalarFIFO(name="offset",size=1).wtPort(x2854_b3168_x2862_b3170_s)
      CU.newVout("data", x2855_x2864_data_v)
    }
    val x2871 = Pipeline(name="x2871",parent=x2872) { implicit CU => 
      val ctr12 = Counter(min=Const(0), max=Const(4000), step=Const(1), par=16) // Counter
      val x2866 = CounterChain(name = "x2866", ctr12).iter(250)
      var stage: List[Stage] = Nil
    }
    val x2891 = StreamController(name="x2891",parent=x3100) { implicit CU => 
    }
    val x2882_0 = Pipeline(name="x2882_0",parent=x2891) { implicit CU => 
      val x2876 = CU.temp
      val x2875 =  ScalarBuffer().wtPort(x2875_argin)
      val x2645 = CounterChain.copy("x3100", "x2645")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x2645(0)), Const(4)), op=FixMul, results=List(x2876))
      Stage(operands=List(x2876, CU.load(x2875)), op=FixAdd, results=List(CU.scalarOut(x2873_b3172_x2881_b3174_s)))
      Stage(operands=List(Const(16000)), op=Bypass, results=List(CU.scalarOut(x2873_b3173_x2881_b3175_s)))
    }
    val x2883 = MemoryController(name="x2883",parent=x2891,offchip=x2636_oc, mctpe=TileLoad) { implicit CU => 
      val x2873_b3172_x2883 =  ScalarFIFO(name="offset",size=1).wtPort(x2873_b3172_x2881_b3174_s)
      val x2873_b3173_x2883 =  ScalarFIFO(name="size",size=1).wtPort(x2873_b3173_x2881_b3175_s)
      CU.newVout("data", x2874_x2883_data_v)
    }
    val x2890 = Pipeline(name="x2890",parent=x2891) { implicit CU => 
      val ctr13 = Counter(min=Const(0), max=Const(4000), step=Const(1), par=16) // Counter
      val x2885 = CounterChain(name = "x2885", ctr13).iter(250)
      var stage: List[Stage] = Nil
    }
    val x2911 = StreamController(name="x2911",parent=x3100) { implicit CU => 
    }
    val x2902_0 = Pipeline(name="x2902_0",parent=x2911) { implicit CU => 
      val x2896 = CU.temp
      val x2895 =  ScalarBuffer().wtPort(x2895_argin)
      val x2645 = CounterChain.copy("x3100", "x2645")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x2645(0)), Const(4)), op=FixMul, results=List(x2896))
      Stage(operands=List(x2896, CU.load(x2895)), op=FixAdd, results=List(CU.scalarOut(x2893_b3176_x2901_b3178_s)))
      Stage(operands=List(Const(16000)), op=Bypass, results=List(CU.scalarOut(x2893_b3177_x2901_b3179_s)))
    }
    val x2903 = MemoryController(name="x2903",parent=x2911,offchip=x2630_oc, mctpe=TileLoad) { implicit CU => 
      val x2893_b3177_x2903 =  ScalarFIFO(name="size",size=1).wtPort(x2893_b3177_x2901_b3179_s)
      val x2893_b3176_x2903 =  ScalarFIFO(name="offset",size=1).wtPort(x2893_b3176_x2901_b3178_s)
      CU.newVout("data", x2894_x2903_data_v)
    }
    val x2910 = Pipeline(name="x2910",parent=x2911) { implicit CU => 
      val ctr14 = Counter(min=Const(0), max=Const(4000), step=Const(1), par=16) // Counter
      val x2905 = CounterChain(name = "x2905", ctr14).iter(250)
      var stage: List[Stage] = Nil
    }
    val x2930 = StreamController(name="x2930",parent=x3100) { implicit CU => 
    }
    val x2921_0 = Pipeline(name="x2921_0",parent=x2930) { implicit CU => 
      val x2915 = CU.temp
      val x2914 =  ScalarBuffer().wtPort(x2914_argin)
      val x2645 = CounterChain.copy("x3100", "x2645")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x2645(0)), Const(4)), op=FixMul, results=List(x2915))
      Stage(operands=List(x2915, CU.load(x2914)), op=FixAdd, results=List(CU.scalarOut(x2912_b3180_x2920_b3182_s)))
      Stage(operands=List(Const(16000)), op=Bypass, results=List(CU.scalarOut(x2912_b3181_x2920_b3183_s)))
    }
    val x2922 = MemoryController(name="x2922",parent=x2930,offchip=x2632_oc, mctpe=TileLoad) { implicit CU => 
      val x2912_b3181_x2922 =  ScalarFIFO(name="size",size=1).wtPort(x2912_b3181_x2920_b3183_s)
      val x2912_b3180_x2922 =  ScalarFIFO(name="offset",size=1).wtPort(x2912_b3180_x2920_b3182_s)
      CU.newVout("data", x2913_x2922_data_v)
    }
    val x2929 = Pipeline(name="x2929",parent=x2930) { implicit CU => 
      val ctr15 = Counter(min=Const(0), max=Const(4000), step=Const(1), par=16) // Counter
      val x2924 = CounterChain(name = "x2924", ctr15).iter(250)
      var stage: List[Stage] = Nil
    }
    val x2949 = StreamController(name="x2949",parent=x3100) { implicit CU => 
    }
    val x2940_0 = Pipeline(name="x2940_0",parent=x2949) { implicit CU => 
      val x2934 = CU.temp
      val x2933 =  ScalarBuffer().wtPort(x2933_argin)
      val x2645 = CounterChain.copy("x3100", "x2645")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x2645(0)), Const(4)), op=FixMul, results=List(x2934))
      Stage(operands=List(x2934, CU.load(x2933)), op=FixAdd, results=List(CU.scalarOut(x2931_b3184_x2939_b3186_s)))
      Stage(operands=List(Const(16000)), op=Bypass, results=List(CU.scalarOut(x2931_b3185_x2939_b3187_s)))
    }
    val x2941 = MemoryController(name="x2941",parent=x2949,offchip=x2634_oc, mctpe=TileLoad) { implicit CU => 
      val x2931_b3184_x2941 =  ScalarFIFO(name="offset",size=1).wtPort(x2931_b3184_x2939_b3186_s)
      val x2931_b3185_x2941 =  ScalarFIFO(name="size",size=1).wtPort(x2931_b3185_x2939_b3187_s)
      CU.newVout("data", x2932_x2941_data_v)
    }
    val x2948 = Pipeline(name="x2948",parent=x2949) { implicit CU => 
      val ctr16 = Counter(min=Const(0), max=Const(4000), step=Const(1), par=16) // Counter
      val x2943 = CounterChain(name = "x2943", ctr16).iter(250)
      var stage: List[Stage] = Nil
    }
    val x2968 = StreamController(name="x2968",parent=x3100) { implicit CU => 
    }
    val x2959_0 = Pipeline(name="x2959_0",parent=x2968) { implicit CU => 
      val x2953 = CU.temp
      val x2952 =  ScalarBuffer().wtPort(x2952_argin)
      val x2645 = CounterChain.copy("x3100", "x2645")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x2645(0)), Const(4)), op=FixMul, results=List(x2953))
      Stage(operands=List(x2953, CU.load(x2952)), op=FixAdd, results=List(CU.scalarOut(x2950_b3188_x2958_b3190_s)))
      Stage(operands=List(Const(16000)), op=Bypass, results=List(CU.scalarOut(x2950_b3189_x2958_b3191_s)))
    }
    val x2960 = MemoryController(name="x2960",parent=x2968,offchip=x2636_oc, mctpe=TileLoad) { implicit CU => 
      val x2950_b3189_x2960 =  ScalarFIFO(name="size",size=1).wtPort(x2950_b3189_x2958_b3191_s)
      val x2950_b3188_x2960 =  ScalarFIFO(name="offset",size=1).wtPort(x2950_b3188_x2958_b3190_s)
      CU.newVout("data", x2951_x2960_data_v)
    }
    val x2967 = Pipeline(name="x2967",parent=x2968) { implicit CU => 
      val ctr17 = Counter(min=Const(0), max=Const(4000), step=Const(1), par=16) // Counter
      val x2962 = CounterChain(name = "x2962", ctr17).iter(250)
      var stage: List[Stage] = Nil
    }
    val x3001 = StreamController(name="x3001",parent=x3100) { implicit CU => 
      val ctr18 = Counter(min=Const(0), max=Const(4000), step=Const(1), par=16) // Counter
      val x2976 = CounterChain(name = "x2976", ctr18).iter(250)
    }
    val x3001_0 = Pipeline(name="x3001_0",parent=x3001) { implicit CU => 
      val x2986 = CU.temp
      val x2984 = CU.temp
      val x2988 = CU.temp
      val x2985 = CU.temp
      val x2978_x2978 =  VectorFIFO(size=1).wtPort(x2646_x2978_x3001_v)
      val x2979_x2979 =  VectorFIFO(size=1).wtPort(x2654_x2979_x3001_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(Const(0), CU.load(x2978_x2978)), op=FixLt, results=List(x2984))
      Stage(operands=List(CU.load(x2978_x2978), Const(9999)), op=FixLt, results=List(x2985))
      Stage(operands=List(x2984, x2985), op=BitAnd, results=List(x2986))
      Stage(operands=List(Const(0), CU.load(x2979_x2979)), op=FixLeq, results=List(x2988))
      Stage(operands=List(x2986, x2988), op=BitAnd, results=List(CU.vecOut(bus_785_v)))
      Stage(operands=List(CU.load(x2979_x2979), Const(9999)), op=FixLeq, results=List(CU.vecOut(bus_786_v)))
    }
    val x3001_1 = Pipeline(name="x3001_1",parent=x3001) { implicit CU => 
      val x2993 = CU.temp
      val x2991 = CU.temp
      val x2980_x2980 =  VectorFIFO(size=1).wtPort(x2650_x2980_x3001_v)
      val x2989 =  VectorFIFO(size=1).wtPort(bus_785_v)
      val x2990 =  VectorFIFO(size=1).wtPort(bus_786_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x2989), CU.load(x2990)), op=BitAnd, results=List(x2991))
      Stage(operands=List(CU.load(x2980_x2980), Const(24)), op=FixLt, results=List(x2993))
      Stage(operands=List(x2991, x2993), op=BitAnd, results=List(CU.vecOut(bus_790_v)))
    }
    val x3001_2 = Pipeline(name="x3001_2",parent=x3001) { implicit CU => 
      val x2996 = CU.temp
      val x2981_x2981 =  VectorFIFO(size=1).wtPort(x2658_x2981_x3001_v)
      val x2994 =  VectorFIFO(size=1).wtPort(bus_790_v)
      val x2979_x2979 =  VectorFIFO(size=1).wtPort(x2654_x2979_x3001_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x2981_x2981), CU.load(x2979_x2979)), op=FixMul, results=List(x2996))
      Stage(operands=List(CU.load(x2994), x2996, Const(0)), op=Mux, results=List(CU.vecOut(bus_793_v)))
    }
    val x3001_3 = Pipeline(name="x3001_3",parent=x3001) { implicit CU => 
      val rr793 =  VectorFIFO(size=1).wtPort(bus_793_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(rr793)), op=Bypass, results=List(CU.reduce))
      val (_, rr795) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(operands=List(rr795), op=Bypass, results=List(CU.scalarOut(x2971_x2999_s)))
    }
    val x3028 = StreamController(name="x3028",parent=x3100) { implicit CU => 
      val ctr19 = Counter(min=Const(0), max=Const(4000), step=Const(1), par=16) // Counter
      val x3003 = CounterChain(name = "x3003", ctr19).iter(250)
    }
    val x3028_0 = Pipeline(name="x3028_0",parent=x3028) { implicit CU => 
      val x3013 = CU.temp
      val x3015 = CU.temp
      val x3011 = CU.temp
      val x3012 = CU.temp
      val x3005_x3005 =  VectorFIFO(size=1).wtPort(x2647_x3005_x3028_v)
      val x3006_x3006 =  VectorFIFO(size=1).wtPort(x2655_x3006_x3028_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(Const(0), CU.load(x3005_x3005)), op=FixLt, results=List(x3011))
      Stage(operands=List(CU.load(x3005_x3005), Const(9999)), op=FixLt, results=List(x3012))
      Stage(operands=List(x3011, x3012), op=BitAnd, results=List(x3013))
      Stage(operands=List(Const(0), CU.load(x3006_x3006)), op=FixLeq, results=List(x3015))
      Stage(operands=List(x3013, x3015), op=BitAnd, results=List(CU.vecOut(bus_802_v)))
      Stage(operands=List(CU.load(x3006_x3006), Const(9999)), op=FixLeq, results=List(CU.vecOut(bus_803_v)))
    }
    val x3028_1 = Pipeline(name="x3028_1",parent=x3028) { implicit CU => 
      val x3020 = CU.temp
      val x3018 = CU.temp
      val x3007_x3007 =  VectorFIFO(size=1).wtPort(x2651_x3007_x3028_v)
      val x3016 =  VectorFIFO(size=1).wtPort(bus_802_v)
      val x3017 =  VectorFIFO(size=1).wtPort(bus_803_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3016), CU.load(x3017)), op=BitAnd, results=List(x3018))
      Stage(operands=List(CU.load(x3007_x3007), Const(24)), op=FixLt, results=List(x3020))
      Stage(operands=List(x3018, x3020), op=BitAnd, results=List(CU.vecOut(bus_807_v)))
    }
    val x3028_2 = Pipeline(name="x3028_2",parent=x3028) { implicit CU => 
      val x3023 = CU.temp
      val x3008_x3008 =  VectorFIFO(size=1).wtPort(x2659_x3008_x3028_v)
      val x3021 =  VectorFIFO(size=1).wtPort(bus_807_v)
      val x3006_x3006 =  VectorFIFO(size=1).wtPort(x2655_x3006_x3028_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3008_x3008), CU.load(x3006_x3006)), op=FixMul, results=List(x3023))
      Stage(operands=List(CU.load(x3021), x3023, Const(0)), op=Mux, results=List(CU.vecOut(bus_810_v)))
    }
    val x3028_3 = Pipeline(name="x3028_3",parent=x3028) { implicit CU => 
      val rr810 =  VectorFIFO(size=1).wtPort(bus_810_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(rr810)), op=Bypass, results=List(CU.reduce))
      val (_, rr812) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(operands=List(rr812), op=Bypass, results=List(CU.scalarOut(x2972_x3026_s)))
    }
    val x3055 = StreamController(name="x3055",parent=x3100) { implicit CU => 
      val ctr20 = Counter(min=Const(0), max=Const(4000), step=Const(1), par=16) // Counter
      val x3030 = CounterChain(name = "x3030", ctr20).iter(250)
    }
    val x3055_0 = Pipeline(name="x3055_0",parent=x3055) { implicit CU => 
      val x3040 = CU.temp
      val x3039 = CU.temp
      val x3042 = CU.temp
      val x3038 = CU.temp
      val x3033_x3033 =  VectorFIFO(size=1).wtPort(x2656_x3033_x3055_v)
      val x3032_x3032 =  VectorFIFO(size=1).wtPort(x2648_x3032_x3055_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(Const(0), CU.load(x3032_x3032)), op=FixLt, results=List(x3038))
      Stage(operands=List(CU.load(x3032_x3032), Const(9999)), op=FixLt, results=List(x3039))
      Stage(operands=List(x3038, x3039), op=BitAnd, results=List(x3040))
      Stage(operands=List(Const(0), CU.load(x3033_x3033)), op=FixLeq, results=List(x3042))
      Stage(operands=List(x3040, x3042), op=BitAnd, results=List(CU.vecOut(bus_819_v)))
      Stage(operands=List(CU.load(x3033_x3033), Const(9999)), op=FixLeq, results=List(CU.vecOut(bus_820_v)))
    }
    val x3055_1 = Pipeline(name="x3055_1",parent=x3055) { implicit CU => 
      val x3047 = CU.temp
      val x3045 = CU.temp
      val x3044 =  VectorFIFO(size=1).wtPort(bus_820_v)
      val x3034_x3034 =  VectorFIFO(size=1).wtPort(x2652_x3034_x3055_v)
      val x3043 =  VectorFIFO(size=1).wtPort(bus_819_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3043), CU.load(x3044)), op=BitAnd, results=List(x3045))
      Stage(operands=List(CU.load(x3034_x3034), Const(24)), op=FixLt, results=List(x3047))
      Stage(operands=List(x3045, x3047), op=BitAnd, results=List(CU.vecOut(bus_824_v)))
    }
    val x3055_2 = Pipeline(name="x3055_2",parent=x3055) { implicit CU => 
      val x3050 = CU.temp
      val x3035_x3035 =  VectorFIFO(size=1).wtPort(x2660_x3035_x3055_v)
      val x3033_x3033 =  VectorFIFO(size=1).wtPort(x2656_x3033_x3055_v)
      val x3048 =  VectorFIFO(size=1).wtPort(bus_824_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3035_x3035), CU.load(x3033_x3033)), op=FixMul, results=List(x3050))
      Stage(operands=List(CU.load(x3048), x3050, Const(0)), op=Mux, results=List(CU.vecOut(bus_827_v)))
    }
    val x3055_3 = Pipeline(name="x3055_3",parent=x3055) { implicit CU => 
      val rr827 =  VectorFIFO(size=1).wtPort(bus_827_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(rr827)), op=Bypass, results=List(CU.reduce))
      val (_, rr829) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(operands=List(rr829), op=Bypass, results=List(CU.scalarOut(x2973_x3053_s)))
    }
    val x3082 = StreamController(name="x3082",parent=x3100) { implicit CU => 
      val ctr21 = Counter(min=Const(0), max=Const(4000), step=Const(1), par=16) // Counter
      val x3057 = CounterChain(name = "x3057", ctr21).iter(250)
    }
    val x3082_0 = Pipeline(name="x3082_0",parent=x3082) { implicit CU => 
      val x3067 = CU.temp
      val x3066 = CU.temp
      val x3069 = CU.temp
      val x3065 = CU.temp
      val x3060_x3060 =  VectorFIFO(size=1).wtPort(x2657_x3060_x3082_v)
      val x3059_x3059 =  VectorFIFO(size=1).wtPort(x2649_x3059_x3082_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(Const(0), CU.load(x3059_x3059)), op=FixLt, results=List(x3065))
      Stage(operands=List(CU.load(x3059_x3059), Const(9999)), op=FixLt, results=List(x3066))
      Stage(operands=List(x3065, x3066), op=BitAnd, results=List(x3067))
      Stage(operands=List(Const(0), CU.load(x3060_x3060)), op=FixLeq, results=List(x3069))
      Stage(operands=List(x3067, x3069), op=BitAnd, results=List(CU.vecOut(bus_836_v)))
      Stage(operands=List(CU.load(x3060_x3060), Const(9999)), op=FixLeq, results=List(CU.vecOut(bus_837_v)))
    }
    val x3082_1 = Pipeline(name="x3082_1",parent=x3082) { implicit CU => 
      val x3072 = CU.temp
      val x3074 = CU.temp
      val x3071 =  VectorFIFO(size=1).wtPort(bus_837_v)
      val x3061_x3061 =  VectorFIFO(size=1).wtPort(x2653_x3061_x3082_v)
      val x3070 =  VectorFIFO(size=1).wtPort(bus_836_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3070), CU.load(x3071)), op=BitAnd, results=List(x3072))
      Stage(operands=List(CU.load(x3061_x3061), Const(24)), op=FixLt, results=List(x3074))
      Stage(operands=List(x3072, x3074), op=BitAnd, results=List(CU.vecOut(bus_841_v)))
    }
    val x3082_2 = Pipeline(name="x3082_2",parent=x3082) { implicit CU => 
      val x3077 = CU.temp
      val x3062_x3062 =  VectorFIFO(size=1).wtPort(x2661_x3062_x3082_v)
      val x3075 =  VectorFIFO(size=1).wtPort(bus_841_v)
      val x3060_x3060 =  VectorFIFO(size=1).wtPort(x2657_x3060_x3082_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3062_x3062), CU.load(x3060_x3060)), op=FixMul, results=List(x3077))
      Stage(operands=List(CU.load(x3075), x3077, Const(0)), op=Mux, results=List(CU.vecOut(bus_844_v)))
    }
    val x3082_3 = Pipeline(name="x3082_3",parent=x3082) { implicit CU => 
      val rr844 =  VectorFIFO(size=1).wtPort(bus_844_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(rr844)), op=Bypass, results=List(CU.reduce))
      val (_, rr846) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(operands=List(rr846), op=Bypass, results=List(CU.scalarOut(x2974_x3080_s)))
    }
    val x3098 = StreamController(name="x3098",parent=x3100) { implicit CU => 
    }
    val x3098_0 = Pipeline(name="x3098_0",parent=x3098) { implicit CU => 
      val x3092 = CU.temp
      val x3089 = CU.temp
      val x2972_x3085 =  ScalarBuffer().wtPort(x2972_x3026_s)
      val x2971_x3086 =  ScalarBuffer().wtPort(x2971_x2999_s)
      val x2974_x3087 =  ScalarBuffer().wtPort(x2974_x3080_s)
      val x2973_x3088 =  ScalarBuffer().wtPort(x2973_x3053_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x2971_x3086), CU.load(x2972_x3085)), op=FixAdd, results=List(x3089))
      Stage(operands=List(CU.load(x2973_x3088), CU.load(x2974_x3087)), op=FixAdd, results=List(x3092))
      Stage(operands=List(x3089, x3092), op=FixAdd, results=List(CU.scalarOut(bus_851_s)))
    }
    val x3098_1 = Pipeline(name="x3098_1",parent=x3098) { implicit CU => 
      val rr851 =  ScalarFIFO(size=1).wtPort(bus_851_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(rr851)), op=Bypass, results=List(CU.reduce))
      val (_, rr853) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(operands=List(rr853), op=Bypass, results=List(CU.scalarOut(x2637_x3102_argout)))
    }
    
  }
}
