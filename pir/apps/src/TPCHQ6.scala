import pir._
import pir.node._
import arch._
import pirc.enums._

object TPCHQ6 extends PIRApp {
  def main(top:Top) = {
    val discts_oc = OffChip("discts")
    val x2961_b3156_x2970_b3158_s = Scalar("x2961_b3156_x2970_b3158")
    val bus_375_v = Vector("bus_375")
    val x2869_x2869_dsp0_bank0_data_v = Vector("x2869_x2869_dsp0_bank0_data")
    val x2939_b3150_x2948_b3152_s = Scalar("x2939_b3150_x2948_b3152")
    val x2872_x3077_ra_v = Vector("x2872_x3077_ra")
    val x3042_x3095_s = Scalar("x3042_x3095")
    val dataSize_argin = ArgIn("dataSize")
    val bus_381_v = Vector("bus_381")
    val x2875_x2875_dsp0_bank0_data_v = Vector("x2875_x2875_dsp0_bank0_data")
    val x2871_x2871_dsp0_bank0_data_v = Vector("x2871_x2871_dsp0_bank0_data")
    val x2875_x2956_wa_v = Vector("x2875_x2956_wa")
    val prices_da = DRAMAddress("prices", "prices")
    val x2871_x2916_wa_v = Vector("x2871_x2916_wa")
    val dates_da = DRAMAddress("dates", "dates")
    val x2962_x2972_data_v = Vector("x2962_x2972_data")
    val quants_da = DRAMAddress("quants", "quants")
    val x2879_b3135_x2888_b3137_s = Scalar("x2879_b3135_x2888_b3137")
    val x2872_x2872_dsp0_bank0_data_v = Vector("x2872_x2872_dsp0_bank0_data")
    val x2940_x2950_data_v = Vector("x2940_x2950_data")
    val discts_da = DRAMAddress("discts", "discts")
    val x2961_b3155_x2970_b3157_s = Scalar("x2961_b3155_x2970_b3157")
    val x2869_x2896_wa_v = Vector("x2869_x2896_wa")
    val x2871_x3050_ra_v = Vector("x2871_x3050_ra")
    val x2876_x2876_dsp0_bank0_data_v = Vector("x2876_x2876_dsp0_bank0_data")
    val x3022_x3032_data_v = Vector("x3022_x3032_data")
    val prices_oc = OffChip("prices")
    val x2873_x2873_dsp0_bank0_data_v = Vector("x2873_x2873_dsp0_bank0_data")
    val x2873_x2936_wa_v = Vector("x2873_x2936_wa")
    val x2874_x3075_ra_v = Vector("x2874_x3075_ra")
    val quants_oc = OffChip("quants")
    val x3002_x3012_data_v = Vector("x3002_x3012_data")
    val x2919_b3145_x2928_b3147_s = Scalar("x2919_b3145_x2928_b3147")
    val x2870_x2870_dsp0_bank0_data_v = Vector("x2870_x2870_dsp0_bank0_data")
    val x2860_x3109_argout = ArgOut("x2860_x3109")
    val x2874_x3018_wa_v = Vector("x2874_x3018_wa")
    val x2982_x2992_data_v = Vector("x2982_x2992_data")
    val x2939_b3151_x2948_b3153_s = Scalar("x2939_b3151_x2948_b3153")
    val bus_357_v = Vector("bus_357")
    val x2899_b3140_x2908_b3142_s = Scalar("x2899_b3140_x2908_b3142")
    val x2873_x3048_ra_v = Vector("x2873_x3048_ra")
    val x2875_x3052_ra_v = Vector("x2875_x3052_ra")
    val x3001_b3166_x3010_b3168_s = Scalar("x3001_b3166_x3010_b3168")
    val x2870_x2978_wa_v = Vector("x2870_x2978_wa")
    val x2869_x3046_ra_v = Vector("x2869_x3046_ra")
    val bus_363_v = Vector("bus_363")
    val x3001_b3165_x3010_b3167_s = Scalar("x3001_b3165_x3010_b3167")
    val x2876_x3079_ra_v = Vector("x2876_x3079_ra")
    val x2900_x2910_data_v = Vector("x2900_x2910_data")
    val x2874_x2874_dsp0_bank0_data_v = Vector("x2874_x2874_dsp0_bank0_data")
    val x3021_b3171_x3030_b3173_s = Scalar("x3021_b3171_x3030_b3173")
    val x2899_b3141_x2908_b3143_s = Scalar("x2899_b3141_x2908_b3143")
    val dates_oc = OffChip("dates")
    val x2919_b3146_x2928_b3148_s = Scalar("x2919_b3146_x2928_b3148")
    val x2981_b3160_x2990_b3162_s = Scalar("x2981_b3160_x2990_b3162")
    val x2870_x3073_ra_v = Vector("x2870_x3073_ra")
    val x2879_b3136_x2888_b3138_s = Scalar("x2879_b3136_x2888_b3138")
    val x2920_x2930_data_v = Vector("x2920_x2930_data")
    val x2880_x2890_data_v = Vector("x2880_x2890_data")
    val x2981_b3161_x2990_b3163_s = Scalar("x2981_b3161_x2990_b3163")
    val x3041_x3068_s = Scalar("x3041_x3068")
    val x2872_x2998_wa_v = Vector("x2872_x2998_wa")
    val x3021_b3170_x3030_b3172_s = Scalar("x3021_b3170_x3030_b3172")
    val x2876_x3038_wa_v = Vector("x2876_x3038_wa")
    val x3107 = MetaPipeline(name="x3107",parent="top") { implicit CU => 
      val x2850 = ScalarBuffer(name="x2850").buffering(1).wtPort(dataSize_argin)
      val ctr1 = Counter(min=Const(0), max=x2850.readPort, step=Const(384), par=2) // Counter
      val x2868 = CounterChain(name = "x2868", ctr1).iter(1)
    }
    val x2869_dsp0_bank0 = MemoryPipeline(name="x2869_dsp0_bank0",parent="x3107") { implicit CU => 
      val b3175 = VectorFIFO(size=1,name="b3175").wtPort(x2869_x3046_ra_v)
      val b3139 = VectorFIFO(size=1,name="b3139").wtPort(x2869_x2896_wa_v)
      val x2896 = VectorFIFO(size=1,name="x2896").wtPort(x2880_x2890_data_v)
      val x2869 = SRAM(size=24,name="x2869",banking = Strided(1,16)).buffering(2).wtPort(x2896.readPort).rdPort(x2869_x2869_dsp0_bank0_data_v).rdAddr(b3175.readPort).wtAddr(b3139.readPort).consumer("x3046_0", "x3069").producer("x2897_0", "x2898")
    }
    val x2870_dsp0_bank0 = MemoryPipeline(name="x2870_dsp0_bank0",parent="x3107") { implicit CU => 
      val x2978 = VectorFIFO(size=1,name="x2978").wtPort(x2962_x2972_data_v)
      val b3159 = VectorFIFO(size=1,name="b3159").wtPort(x2870_x2978_wa_v)
      val b3179 = VectorFIFO(size=1,name="b3179").wtPort(x2870_x3073_ra_v)
      val x2870 = SRAM(size=24,name="x2870",banking = Strided(1,16)).buffering(2).wtPort(x2978.readPort).rdPort(x2870_x2870_dsp0_bank0_data_v).rdAddr(b3179.readPort).wtAddr(b3159.readPort).consumer("x3073_0", "x3096").producer("x2979_0", "x2980")
    }
    val x2871_dsp0_bank0 = MemoryPipeline(name="x2871_dsp0_bank0",parent="x3107") { implicit CU => 
      val b3177 = VectorFIFO(size=1,name="b3177").wtPort(x2871_x3050_ra_v)
      val b3144 = VectorFIFO(size=1,name="b3144").wtPort(x2871_x2916_wa_v)
      val x2916 = VectorFIFO(size=1,name="x2916").wtPort(x2900_x2910_data_v)
      val x2871 = SRAM(size=24,name="x2871",banking = Strided(1,16)).buffering(2).wtPort(x2916.readPort).rdPort(x2871_x2871_dsp0_bank0_data_v).rdAddr(b3177.readPort).wtAddr(b3144.readPort).consumer("x3050_0", "x3069").producer("x2917_0", "x2918")
    }
    val x2872_dsp0_bank0 = MemoryPipeline(name="x2872_dsp0_bank0",parent="x3107") { implicit CU => 
      val b3181 = VectorFIFO(size=1,name="b3181").wtPort(x2872_x3077_ra_v)
      val x2998 = VectorFIFO(size=1,name="x2998").wtPort(x2982_x2992_data_v)
      val b3164 = VectorFIFO(size=1,name="b3164").wtPort(x2872_x2998_wa_v)
      val x2872 = SRAM(size=24,name="x2872",banking = Strided(1,16)).buffering(2).wtPort(x2998.readPort).rdPort(x2872_x2872_dsp0_bank0_data_v).rdAddr(b3181.readPort).wtAddr(b3164.readPort).consumer("x3077_0", "x3096").producer("x2999_0", "x3000")
    }
    val x2873_dsp0_bank0 = MemoryPipeline(name="x2873_dsp0_bank0",parent="x3107") { implicit CU => 
      val x2936 = VectorFIFO(size=1,name="x2936").wtPort(x2920_x2930_data_v)
      val b3176 = VectorFIFO(size=1,name="b3176").wtPort(x2873_x3048_ra_v)
      val b3149 = VectorFIFO(size=1,name="b3149").wtPort(x2873_x2936_wa_v)
      val x2873 = SRAM(size=24,name="x2873",banking = Strided(1,16)).buffering(2).wtPort(x2936.readPort).rdPort(x2873_x2873_dsp0_bank0_data_v).rdAddr(b3176.readPort).wtAddr(b3149.readPort).consumer("x3048_0", "x3069").producer("x2937_0", "x2938")
    }
    val x2874_dsp0_bank0 = MemoryPipeline(name="x2874_dsp0_bank0",parent="x3107") { implicit CU => 
      val b3169 = VectorFIFO(size=1,name="b3169").wtPort(x2874_x3018_wa_v)
      val b3180 = VectorFIFO(size=1,name="b3180").wtPort(x2874_x3075_ra_v)
      val x3018 = VectorFIFO(size=1,name="x3018").wtPort(x3002_x3012_data_v)
      val x2874 = SRAM(size=24,name="x2874",banking = Strided(1,16)).buffering(2).wtPort(x3018.readPort).rdPort(x2874_x2874_dsp0_bank0_data_v).rdAddr(b3180.readPort).wtAddr(b3169.readPort).consumer("x3075_0", "x3096").producer("x3019_0", "x3020")
    }
    val x2875_dsp0_bank0 = MemoryPipeline(name="x2875_dsp0_bank0",parent="x3107") { implicit CU => 
      val b3178 = VectorFIFO(size=1,name="b3178").wtPort(x2875_x3052_ra_v)
      val b3154 = VectorFIFO(size=1,name="b3154").wtPort(x2875_x2956_wa_v)
      val x2956 = VectorFIFO(size=1,name="x2956").wtPort(x2940_x2950_data_v)
      val x2875 = SRAM(size=24,name="x2875",banking = Strided(1,16)).buffering(2).wtPort(x2956.readPort).rdPort(x2875_x2875_dsp0_bank0_data_v).rdAddr(b3178.readPort).wtAddr(b3154.readPort).consumer("x3052_0", "x3069").producer("x2957_0", "x2958")
    }
    val x2876_dsp0_bank0 = MemoryPipeline(name="x2876_dsp0_bank0",parent="x3107") { implicit CU => 
      val x3038 = VectorFIFO(size=1,name="x3038").wtPort(x3022_x3032_data_v)
      val b3174 = VectorFIFO(size=1,name="b3174").wtPort(x2876_x3038_wa_v)
      val b3182 = VectorFIFO(size=1,name="b3182").wtPort(x2876_x3079_ra_v)
      val x2876 = SRAM(size=24,name="x2876",banking = Strided(1,16)).buffering(2).wtPort(x3038.readPort).rdPort(x2876_x2876_dsp0_bank0_data_v).rdAddr(b3182.readPort).wtAddr(b3174.readPort).consumer("x3079_0", "x3096").producer("x3039_0", "x3040")
    }
    val x2898 = StreamController(name="x2898",parent="x3107") { implicit CU => 
      val x2898_unit = CounterChain(name = "x2898_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x2889_0 = Pipeline(name="x2889_0",parent="x2898") { implicit CU => 
      val x2882 = CU.temp(None).name("x2882")
      val x2884 = ScalarBuffer(name="x2884").buffering(1).wtPort(dates_da)
      val x2868 = CounterChain.copy("x3107", "x2868").iterIdx(0, 0)
      val x2889_unit = CounterChain(name = "x2889_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(x2868(0), Const(2)), op=FixSla, results=List(x2882))
      Stage(operands=List(x2882, x2884), op=FixAdd, results=List(CU.scalarOut(x2879_b3135_x2888_b3137_s)))
      Stage(operands=List(Const(1536)), op=Bypass, results=List(CU.scalarOut(x2879_b3136_x2888_b3138_s)))
    }
    val x2890 = MemoryController(name="x2890",parent="x2898",offchip=dates_oc, mctpe=TileLoad) { implicit CU => 
      val x2879_b3136 = ScalarFIFO(size=1,name="size").wtPort(x2879_b3136_x2888_b3138_s)
      val x2879_b3135 = ScalarFIFO(size=1,name="offset").wtPort(x2879_b3135_x2888_b3137_s)
      CU.newOut("data", x2880_x2890_data_v)
    }
    val x2897_0 = Pipeline(name="x2897_0",parent="x2898") { implicit CU => 
      val ctr2 = Counter(min=Const(0), max=Const(384), step=Const(1), par=16) // Counter
      val x2892 = CounterChain(name = "x2892", ctr2).iter(24)
      Stage(operands=List(x2892(0)), op=Bypass, results=List(CU.vecOut(x2869_x2896_wa_v)))
    }
    val x2918 = StreamController(name="x2918",parent="x3107") { implicit CU => 
      val x2918_unit = CounterChain(name = "x2918_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x2909_0 = Pipeline(name="x2909_0",parent="x2918") { implicit CU => 
      val x2902 = CU.temp(None).name("x2902")
      val x2904 = ScalarBuffer(name="x2904").buffering(1).wtPort(quants_da)
      val x2868 = CounterChain.copy("x3107", "x2868").iterIdx(0, 0)
      val x2909_unit = CounterChain(name = "x2909_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(x2868(0), Const(2)), op=FixSla, results=List(x2902))
      Stage(operands=List(x2902, x2904), op=FixAdd, results=List(CU.scalarOut(x2899_b3140_x2908_b3142_s)))
      Stage(operands=List(Const(1536)), op=Bypass, results=List(CU.scalarOut(x2899_b3141_x2908_b3143_s)))
    }
    val x2910 = MemoryController(name="x2910",parent="x2918",offchip=quants_oc, mctpe=TileLoad) { implicit CU => 
      val x2899_b3141 = ScalarFIFO(size=1,name="size").wtPort(x2899_b3141_x2908_b3143_s)
      val x2899_b3140 = ScalarFIFO(size=1,name="offset").wtPort(x2899_b3140_x2908_b3142_s)
      CU.newOut("data", x2900_x2910_data_v)
    }
    val x2917_0 = Pipeline(name="x2917_0",parent="x2918") { implicit CU => 
      val ctr3 = Counter(min=Const(0), max=Const(384), step=Const(1), par=16) // Counter
      val x2912 = CounterChain(name = "x2912", ctr3).iter(24)
      Stage(operands=List(x2912(0)), op=Bypass, results=List(CU.vecOut(x2871_x2916_wa_v)))
    }
    val x2938 = StreamController(name="x2938",parent="x3107") { implicit CU => 
      val x2938_unit = CounterChain(name = "x2938_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x2929_0 = Pipeline(name="x2929_0",parent="x2938") { implicit CU => 
      val x2922 = CU.temp(None).name("x2922")
      val x2924 = ScalarBuffer(name="x2924").buffering(1).wtPort(discts_da)
      val x2868 = CounterChain.copy("x3107", "x2868").iterIdx(0, 0)
      val x2929_unit = CounterChain(name = "x2929_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(x2868(0), Const(2)), op=FixSla, results=List(x2922))
      Stage(operands=List(x2922, x2924), op=FixAdd, results=List(CU.scalarOut(x2919_b3145_x2928_b3147_s)))
      Stage(operands=List(Const(1536)), op=Bypass, results=List(CU.scalarOut(x2919_b3146_x2928_b3148_s)))
    }
    val x2930 = MemoryController(name="x2930",parent="x2938",offchip=discts_oc, mctpe=TileLoad) { implicit CU => 
      val x2919_b3145 = ScalarFIFO(size=1,name="offset").wtPort(x2919_b3145_x2928_b3147_s)
      val x2919_b3146 = ScalarFIFO(size=1,name="size").wtPort(x2919_b3146_x2928_b3148_s)
      CU.newOut("data", x2920_x2930_data_v)
    }
    val x2937_0 = Pipeline(name="x2937_0",parent="x2938") { implicit CU => 
      val ctr4 = Counter(min=Const(0), max=Const(384), step=Const(1), par=16) // Counter
      val x2932 = CounterChain(name = "x2932", ctr4).iter(24)
      Stage(operands=List(x2932(0)), op=Bypass, results=List(CU.vecOut(x2873_x2936_wa_v)))
    }
    val x2958 = StreamController(name="x2958",parent="x3107") { implicit CU => 
      val x2958_unit = CounterChain(name = "x2958_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x2949_0 = Pipeline(name="x2949_0",parent="x2958") { implicit CU => 
      val x2942 = CU.temp(None).name("x2942")
      val x2944 = ScalarBuffer(name="x2944").buffering(1).wtPort(prices_da)
      val x2868 = CounterChain.copy("x3107", "x2868").iterIdx(0, 0)
      val x2949_unit = CounterChain(name = "x2949_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(x2868(0), Const(2)), op=FixSla, results=List(x2942))
      Stage(operands=List(x2942, x2944), op=FixAdd, results=List(CU.scalarOut(x2939_b3150_x2948_b3152_s)))
      Stage(operands=List(Const(1536)), op=Bypass, results=List(CU.scalarOut(x2939_b3151_x2948_b3153_s)))
    }
    val x2950 = MemoryController(name="x2950",parent="x2958",offchip=prices_oc, mctpe=TileLoad) { implicit CU => 
      val x2939_b3151 = ScalarFIFO(size=1,name="size").wtPort(x2939_b3151_x2948_b3153_s)
      val x2939_b3150 = ScalarFIFO(size=1,name="offset").wtPort(x2939_b3150_x2948_b3152_s)
      CU.newOut("data", x2940_x2950_data_v)
    }
    val x2957_0 = Pipeline(name="x2957_0",parent="x2958") { implicit CU => 
      val ctr5 = Counter(min=Const(0), max=Const(384), step=Const(1), par=16) // Counter
      val x2952 = CounterChain(name = "x2952", ctr5).iter(24)
      Stage(operands=List(x2952(0)), op=Bypass, results=List(CU.vecOut(x2875_x2956_wa_v)))
    }
    val x2980 = StreamController(name="x2980",parent="x3107") { implicit CU => 
      val x2980_unit = CounterChain(name = "x2980_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x2971_0 = Pipeline(name="x2971_0",parent="x2980") { implicit CU => 
      val x2964 = CU.temp(None).name("x2964")
      val x2966 = ScalarBuffer(name="x2966").buffering(1).wtPort(dates_da)
      val x2868 = CounterChain.copy("x3107", "x2868").iterIdx(0, 1)
      val x2971_unit = CounterChain(name = "x2971_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(x2868(0), Const(2)), op=FixSla, results=List(x2964))
      Stage(operands=List(x2964, x2966), op=FixAdd, results=List(CU.scalarOut(x2961_b3155_x2970_b3157_s)))
      Stage(operands=List(Const(1536)), op=Bypass, results=List(CU.scalarOut(x2961_b3156_x2970_b3158_s)))
    }
    val x2972 = MemoryController(name="x2972",parent="x2980",offchip=dates_oc, mctpe=TileLoad) { implicit CU => 
      val x2961_b3156 = ScalarFIFO(size=1,name="size").wtPort(x2961_b3156_x2970_b3158_s)
      val x2961_b3155 = ScalarFIFO(size=1,name="offset").wtPort(x2961_b3155_x2970_b3157_s)
      CU.newOut("data", x2962_x2972_data_v)
    }
    val x2979_0 = Pipeline(name="x2979_0",parent="x2980") { implicit CU => 
      val ctr6 = Counter(min=Const(0), max=Const(384), step=Const(1), par=16) // Counter
      val x2974 = CounterChain(name = "x2974", ctr6).iter(24)
      Stage(operands=List(x2974(0)), op=Bypass, results=List(CU.vecOut(x2870_x2978_wa_v)))
    }
    val x3000 = StreamController(name="x3000",parent="x3107") { implicit CU => 
      val x3000_unit = CounterChain(name = "x3000_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x2991_0 = Pipeline(name="x2991_0",parent="x3000") { implicit CU => 
      val x2984 = CU.temp(None).name("x2984")
      val x2986 = ScalarBuffer(name="x2986").buffering(1).wtPort(quants_da)
      val x2868 = CounterChain.copy("x3107", "x2868").iterIdx(0, 1)
      val x2991_unit = CounterChain(name = "x2991_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(x2868(0), Const(2)), op=FixSla, results=List(x2984))
      Stage(operands=List(x2984, x2986), op=FixAdd, results=List(CU.scalarOut(x2981_b3160_x2990_b3162_s)))
      Stage(operands=List(Const(1536)), op=Bypass, results=List(CU.scalarOut(x2981_b3161_x2990_b3163_s)))
    }
    val x2992 = MemoryController(name="x2992",parent="x3000",offchip=quants_oc, mctpe=TileLoad) { implicit CU => 
      val x2981_b3160 = ScalarFIFO(size=1,name="offset").wtPort(x2981_b3160_x2990_b3162_s)
      val x2981_b3161 = ScalarFIFO(size=1,name="size").wtPort(x2981_b3161_x2990_b3163_s)
      CU.newOut("data", x2982_x2992_data_v)
    }
    val x2999_0 = Pipeline(name="x2999_0",parent="x3000") { implicit CU => 
      val ctr7 = Counter(min=Const(0), max=Const(384), step=Const(1), par=16) // Counter
      val x2994 = CounterChain(name = "x2994", ctr7).iter(24)
      Stage(operands=List(x2994(0)), op=Bypass, results=List(CU.vecOut(x2872_x2998_wa_v)))
    }
    val x3020 = StreamController(name="x3020",parent="x3107") { implicit CU => 
      val x3020_unit = CounterChain(name = "x3020_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x3011_0 = Pipeline(name="x3011_0",parent="x3020") { implicit CU => 
      val x3004 = CU.temp(None).name("x3004")
      val x3006 = ScalarBuffer(name="x3006").buffering(1).wtPort(discts_da)
      val x2868 = CounterChain.copy("x3107", "x2868").iterIdx(0, 1)
      val x3011_unit = CounterChain(name = "x3011_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(x2868(0), Const(2)), op=FixSla, results=List(x3004))
      Stage(operands=List(x3004, x3006), op=FixAdd, results=List(CU.scalarOut(x3001_b3165_x3010_b3167_s)))
      Stage(operands=List(Const(1536)), op=Bypass, results=List(CU.scalarOut(x3001_b3166_x3010_b3168_s)))
    }
    val x3012 = MemoryController(name="x3012",parent="x3020",offchip=discts_oc, mctpe=TileLoad) { implicit CU => 
      val x3001_b3166 = ScalarFIFO(size=1,name="size").wtPort(x3001_b3166_x3010_b3168_s)
      val x3001_b3165 = ScalarFIFO(size=1,name="offset").wtPort(x3001_b3165_x3010_b3167_s)
      CU.newOut("data", x3002_x3012_data_v)
    }
    val x3019_0 = Pipeline(name="x3019_0",parent="x3020") { implicit CU => 
      val ctr8 = Counter(min=Const(0), max=Const(384), step=Const(1), par=16) // Counter
      val x3014 = CounterChain(name = "x3014", ctr8).iter(24)
      Stage(operands=List(x3014(0)), op=Bypass, results=List(CU.vecOut(x2874_x3018_wa_v)))
    }
    val x3040 = StreamController(name="x3040",parent="x3107") { implicit CU => 
      val x3040_unit = CounterChain(name = "x3040_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x3031_0 = Pipeline(name="x3031_0",parent="x3040") { implicit CU => 
      val x3024 = CU.temp(None).name("x3024")
      val x3026 = ScalarBuffer(name="x3026").buffering(1).wtPort(prices_da)
      val x2868 = CounterChain.copy("x3107", "x2868").iterIdx(0, 1)
      val x3031_unit = CounterChain(name = "x3031_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(x2868(0), Const(2)), op=FixSla, results=List(x3024))
      Stage(operands=List(x3024, x3026), op=FixAdd, results=List(CU.scalarOut(x3021_b3170_x3030_b3172_s)))
      Stage(operands=List(Const(1536)), op=Bypass, results=List(CU.scalarOut(x3021_b3171_x3030_b3173_s)))
    }
    val x3032 = MemoryController(name="x3032",parent="x3040",offchip=prices_oc, mctpe=TileLoad) { implicit CU => 
      val x3021_b3171 = ScalarFIFO(size=1,name="size").wtPort(x3021_b3171_x3030_b3173_s)
      val x3021_b3170 = ScalarFIFO(size=1,name="offset").wtPort(x3021_b3170_x3030_b3172_s)
      CU.newOut("data", x3022_x3032_data_v)
    }
    val x3039_0 = Pipeline(name="x3039_0",parent="x3040") { implicit CU => 
      val ctr9 = Counter(min=Const(0), max=Const(384), step=Const(1), par=16) // Counter
      val x3034 = CounterChain(name = "x3034", ctr9).iter(24)
      Stage(operands=List(x3034(0)), op=Bypass, results=List(CU.vecOut(x2876_x3038_wa_v)))
    }
    val x3069 = StreamController(name="x3069",parent="x3107") { implicit CU => 
      val ctr10 = Counter(min=Const(0), max=Const(384), step=Const(1), par=16) // Counter
      val x3044 = CounterChain(name = "x3044", ctr10).iter(24)
    }
    val x3069_2 = Pipeline(name="x3069_2",parent="x3069") { implicit CU => 
      val rr363 = VectorFIFO(size=1,name="rr363").wtPort(bus_363_v)
      val ctr14 = Counter(min=Const(0), max=Const(1), step=Const(16), par=16) // Counter
      val x3069_2_unit = CounterChain(name = "x3069_2_unit", ctr14).iter(0)
      Stage(operands=List(rr363), op=Bypass, results=List(CU.reduce))
      val (_, rr365) = Stage.reduce(op=FixAdd, init=Const(0), accumParent="x3069")
      Stage(operands=List(rr365), op=Bypass, results=List(CU.scalarOut(x3041_x3068_s)))
    }
    val x3069_0 = Pipeline(name="x3069_0",parent="x3069") { implicit CU => 
      val x3054 = CU.temp(None).name("x3054")
      val x3058 = CU.temp(None).name("x3058")
      val x3057 = CU.temp(None).name("x3057")
      val x3059 = CU.temp(None).name("x3059")
      val x3056 = CU.temp(None).name("x3056")
      val x3055 = CU.temp(None).name("x3055")
      val x3046 = VectorFIFO(size=1,name="x3046").wtPort(x2869_x2869_dsp0_bank0_data_v)
      val x3048 = VectorFIFO(size=1,name="x3048").wtPort(x2873_x2873_dsp0_bank0_data_v)
      val ctr12 = Counter(min=Const(0), max=Const(1), step=Const(16), par=16) // Counter
      val x3069_0_unit = CounterChain(name = "x3069_0_unit", ctr12).iter(0)
      Stage(operands=List(Const(0), x3046), op=FixLt, results=List(x3054))
      Stage(operands=List(x3046, Const(9999)), op=FixLt, results=List(x3055))
      Stage(operands=List(x3054, x3055), op=BitAnd, results=List(x3056))
      Stage(operands=List(Const(0), x3048), op=FixLeq, results=List(x3057))
      Stage(operands=List(x3056, x3057), op=BitAnd, results=List(x3058))
      Stage(operands=List(x3048, Const(9999)), op=FixLeq, results=List(x3059))
      Stage(operands=List(x3058, x3059), op=BitAnd, results=List(CU.vecOut(bus_357_v)))
    }
    val x3069_1 = Pipeline(name="x3069_1",parent="x3069") { implicit CU => 
      val x3062 = CU.temp(None).name("x3062")
      val x3063 = CU.temp(None).name("x3063")
      val x3061 = CU.temp(None).name("x3061")
      val x3060 = VectorFIFO(size=1,name="x3060").wtPort(bus_357_v)
      val x3052 = VectorFIFO(size=1,name="x3052").wtPort(x2875_x2875_dsp0_bank0_data_v)
      val x3048 = VectorFIFO(size=1,name="x3048").wtPort(x2873_x2873_dsp0_bank0_data_v)
      val x3050 = VectorFIFO(size=1,name="x3050").wtPort(x2871_x2871_dsp0_bank0_data_v)
      val ctr13 = Counter(min=Const(0), max=Const(1), step=Const(16), par=16) // Counter
      val x3069_1_unit = CounterChain(name = "x3069_1_unit", ctr13).iter(0)
      Stage(operands=List(x3050, Const(24)), op=FixLt, results=List(x3061))
      Stage(operands=List(x3060, x3061), op=BitAnd, results=List(x3062))
      Stage(operands=List(x3052, x3048), op=FixMul, results=List(x3063))
      Stage(operands=List(x3062, x3063, Const(0)), op=MuxOp, results=List(CU.vecOut(bus_363_v)))
    }
    val x3046_0 = Pipeline(name="x3046_0",parent="x3107") { implicit CU => 
      val x3044 = CounterChain.copy("x3069", "x3044").iterIdx(0, 0)
      Stage(operands=List(x3044(0)), op=Bypass, results=List(CU.vecOut(x2869_x3046_ra_v)))
    }
    val x3048_0 = Pipeline(name="x3048_0",parent="x3107") { implicit CU => 
      val x3044 = CounterChain.copy("x3069", "x3044").iterIdx(0, 0)
      Stage(operands=List(x3044(0)), op=Bypass, results=List(CU.vecOut(x2873_x3048_ra_v)))
    }
    val x3050_0 = Pipeline(name="x3050_0",parent="x3107") { implicit CU => 
      val x3044 = CounterChain.copy("x3069", "x3044").iterIdx(0, 0)
      Stage(operands=List(x3044(0)), op=Bypass, results=List(CU.vecOut(x2871_x3050_ra_v)))
    }
    val x3052_0 = Pipeline(name="x3052_0",parent="x3107") { implicit CU => 
      val x3044 = CounterChain.copy("x3069", "x3044").iterIdx(0, 0)
      Stage(operands=List(x3044(0)), op=Bypass, results=List(CU.vecOut(x2875_x3052_ra_v)))
    }
    val x3096_2 = Pipeline(name="x3096_2",parent="x3096") { implicit CU => 
      val rr381 = VectorFIFO(size=1,name="rr381").wtPort(bus_381_v)
      val ctr17 = Counter(min=Const(0), max=Const(1), step=Const(16), par=16) // Counter
      val x3096_2_unit = CounterChain(name = "x3096_2_unit", ctr17).iter(0)
      Stage(operands=List(rr381), op=Bypass, results=List(CU.reduce))
      val (_, rr383) = Stage.reduce(op=FixAdd, init=Const(0), accumParent="x3096")
      Stage(operands=List(rr383), op=Bypass, results=List(CU.scalarOut(x3042_x3095_s)))
    }
    val x3096 = StreamController(name="x3096",parent="x3107") { implicit CU => 
      val ctr11 = Counter(min=Const(0), max=Const(384), step=Const(1), par=16) // Counter
      val x3071 = CounterChain(name = "x3071", ctr11).iter(24)
    }
    val x3096_0 = Pipeline(name="x3096_0",parent="x3096") { implicit CU => 
      val x3086 = CU.temp(None).name("x3086")
      val x3083 = CU.temp(None).name("x3083")
      val x3084 = CU.temp(None).name("x3084")
      val x3081 = CU.temp(None).name("x3081")
      val x3082 = CU.temp(None).name("x3082")
      val x3085 = CU.temp(None).name("x3085")
      val x3073 = VectorFIFO(size=1,name="x3073").wtPort(x2870_x2870_dsp0_bank0_data_v)
      val x3075 = VectorFIFO(size=1,name="x3075").wtPort(x2874_x2874_dsp0_bank0_data_v)
      val ctr15 = Counter(min=Const(0), max=Const(1), step=Const(16), par=16) // Counter
      val x3096_0_unit = CounterChain(name = "x3096_0_unit", ctr15).iter(0)
      Stage(operands=List(Const(0), x3073), op=FixLt, results=List(x3081))
      Stage(operands=List(x3073, Const(9999)), op=FixLt, results=List(x3082))
      Stage(operands=List(x3081, x3082), op=BitAnd, results=List(x3083))
      Stage(operands=List(Const(0), x3075), op=FixLeq, results=List(x3084))
      Stage(operands=List(x3083, x3084), op=BitAnd, results=List(x3085))
      Stage(operands=List(x3075, Const(9999)), op=FixLeq, results=List(x3086))
      Stage(operands=List(x3085, x3086), op=BitAnd, results=List(CU.vecOut(bus_375_v)))
    }
    val x3096_1 = Pipeline(name="x3096_1",parent="x3096") { implicit CU => 
      val x3089 = CU.temp(None).name("x3089")
      val x3090 = CU.temp(None).name("x3090")
      val x3088 = CU.temp(None).name("x3088")
      val x3087 = VectorFIFO(size=1,name="x3087").wtPort(bus_375_v)
      val x3079 = VectorFIFO(size=1,name="x3079").wtPort(x2876_x2876_dsp0_bank0_data_v)
      val x3075 = VectorFIFO(size=1,name="x3075").wtPort(x2874_x2874_dsp0_bank0_data_v)
      val x3077 = VectorFIFO(size=1,name="x3077").wtPort(x2872_x2872_dsp0_bank0_data_v)
      val ctr16 = Counter(min=Const(0), max=Const(1), step=Const(16), par=16) // Counter
      val x3096_1_unit = CounterChain(name = "x3096_1_unit", ctr16).iter(0)
      Stage(operands=List(x3077, Const(24)), op=FixLt, results=List(x3088))
      Stage(operands=List(x3087, x3088), op=BitAnd, results=List(x3089))
      Stage(operands=List(x3079, x3075), op=FixMul, results=List(x3090))
      Stage(operands=List(x3089, x3090, Const(0)), op=MuxOp, results=List(CU.vecOut(bus_381_v)))
    }
    val x3073_0 = Pipeline(name="x3073_0",parent="x3107") { implicit CU => 
      val x3071 = CounterChain.copy("x3096", "x3071").iterIdx(0, 0)
      Stage(operands=List(x3071(0)), op=Bypass, results=List(CU.vecOut(x2870_x3073_ra_v)))
    }
    val x3075_0 = Pipeline(name="x3075_0",parent="x3107") { implicit CU => 
      val x3071 = CounterChain.copy("x3096", "x3071").iterIdx(0, 0)
      Stage(operands=List(x3071(0)), op=Bypass, results=List(CU.vecOut(x2874_x3075_ra_v)))
    }
    val x3077_0 = Pipeline(name="x3077_0",parent="x3107") { implicit CU => 
      val x3071 = CounterChain.copy("x3096", "x3071").iterIdx(0, 0)
      Stage(operands=List(x3071(0)), op=Bypass, results=List(CU.vecOut(x2872_x3077_ra_v)))
    }
    val x3079_0 = Pipeline(name="x3079_0",parent="x3107") { implicit CU => 
      val x3071 = CounterChain.copy("x3096", "x3071").iterIdx(0, 0)
      Stage(operands=List(x3071(0)), op=Bypass, results=List(CU.vecOut(x2876_x3079_ra_v)))
    }
    val x3106_0 = Pipeline(name="x3106_0",parent="x3107") { implicit CU => 
      val x3042 = ScalarBuffer(name="x3042").buffering(2).wtPort(x3042_x3095_s).consumer("x3106_0", "x3106_0")
      val x3041 = ScalarBuffer(name="x3041").buffering(2).wtPort(x3041_x3068_s).consumer("x3106_0", "x3106_0")
      val x3106_unit = CounterChain(name = "x3106_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(x3041, x3042), op=FixAdd, results=List(CU.reduce))
      val (_, rr388) = Stage.reduce(op=FixAdd, init=Const(0), accumParent="x3107")
      Stage(operands=List(rr388), op=Bypass, results=List(CU.scalarOut(x2860_x3109_argout)))
    }
    
  }
}
