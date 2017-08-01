import pir.graph.{Mux => _, _}
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.util.enums._
import pir.util._
import pir.PIRApp

object DotProduct extends PIRApp {
  def main(top:Top) = {
    val x2974_b3432_x2982_b3434_s = Scalar("x2974_b3432_x2982_b3434")
    val x2777_x3309_x3317_v = Vector("x2777_x3309_x3317")
    val x2774_x3273_x3281_v = Vector("x2774_x3273_x3281")
    val x2785_x3286_x3293_v = Vector("x2785_x3286_x3293")
    val b_oc = OffChip("b")
    val N_argin = ArgIn("N").bound(1920000)
    val x2810_b3401_x2818_b3403_s = Scalar("x2810_b3401_x2818_b3403")
    val a_oc = OffChip("a")
    val b_da = DRAMAddress("b", "b")
    val x3098_x3107_data_v = Vector("x3098_x3107_data")
    val x2996_b3436_x3004_b3438_s = Scalar("x2996_b3436_x3004_b3438")
    val x3206_x3292_s = Scalar("x3206_x3292")
    val x2778_x3321_x3329_v = Vector("x2778_x3321_x3329")
    val x3037_b3444_x3045_b3446_s = Scalar("x3037_b3444_x3045_b3446")
    val x3179_b3473_x3187_b3475_s = Scalar("x3179_b3473_x3187_b3475")
    val x2933_b3425_x2941_b3427_s = Scalar("x2933_b3425_x2941_b3427")
    val x2776_x3297_x3305_v = Vector("x2776_x3297_x3305")
    val x2780_x3226_x3233_v = Vector("x2780_x3226_x3233")
    val x3015_b3440_x3023_b3442_s = Scalar("x3015_b3440_x3023_b3442")
    val x2791_b3396_x2799_b3398_s = Scalar("x2791_b3396_x2799_b3398")
    val x2775_x3285_x3293_v = Vector("x2775_x3285_x3293")
    val x3079_x3088_data_v = Vector("x3079_x3088_data")
    val x3056_b3449_x3064_b3451_s = Scalar("x3056_b3449_x3064_b3451")
    val a_da = DRAMAddress("a", "a")
    val x3056_b3448_x3064_b3450_s = Scalar("x3056_b3448_x3064_b3450")
    val x2873_b3412_x2881_b3414_s = Scalar("x2873_b3412_x2881_b3414")
    val x2852_x2861_data_v = Vector("x2852_x2861_data")
    val x2792_x2801_data_v = Vector("x2792_x2801_data")
    val x2771_x3237_x3245_v = Vector("x2771_x3237_x3245")
    val x2773_x3261_x3269_v = Vector("x2773_x3261_x3269")
    val x2769_x3213_x3221_v = Vector("x2769_x3213_x3221")
    val x2892_b3417_x2900_b3419_s = Scalar("x2892_b3417_x2900_b3419")
    val x2851_b3409_x2859_b3411_s = Scalar("x2851_b3409_x2859_b3411")
    val x3057_x3066_data_v = Vector("x3057_x3066_data")
    val x2781_x3238_x3245_v = Vector("x2781_x3238_x3245")
    val x2975_x2984_data_v = Vector("x2975_x2984_data")
    val x2915_x2924_data_v = Vector("x2915_x2924_data")
    val x3201_x3232_s = Scalar("x3201_x3232")
    val x2788_x3322_x3329_v = Vector("x2788_x3322_x3329")
    val x3160_b3468_x3168_b3470_s = Scalar("x3160_b3468_x3168_b3470")
    val x2974_b3433_x2982_b3435_s = Scalar("x2974_b3433_x2982_b3435")
    val x2933_b3424_x2941_b3426_s = Scalar("x2933_b3424_x2941_b3426")
    val bus_2869_s = Scalar("bus_2869")
    val x3016_x3025_data_v = Vector("x3016_x3025_data")
    val x3207_x3304_s = Scalar("x3207_x3304")
    val x2762_x3375_argout = ArgOut("x2762_x3375")
    val x2914_b3421_x2922_b3423_s = Scalar("x2914_b3421_x2922_b3423")
    val x2934_x2943_data_v = Vector("x2934_x2943_data")
    val x2791_b3397_x2799_b3399_s = Scalar("x2791_b3397_x2799_b3399")
    val x2832_b3405_x2840_b3407_s = Scalar("x2832_b3405_x2840_b3407")
    val x2893_x2902_data_v = Vector("x2893_x2902_data")
    val bus_2875_s = Scalar("bus_2875")
    val x3208_x3316_s = Scalar("x3208_x3316")
    val x2874_x2883_data_v = Vector("x2874_x2883_data")
    val x2783_x3262_x3269_v = Vector("x2783_x3262_x3269")
    val x3038_x3047_data_v = Vector("x3038_x3047_data")
    val x3204_x3268_s = Scalar("x3204_x3268")
    val x3120_x3129_data_v = Vector("x3120_x3129_data")
    val x2832_b3404_x2840_b3406_s = Scalar("x2832_b3404_x2840_b3406")
    val x2996_b3437_x3004_b3439_s = Scalar("x2996_b3437_x3004_b3439")
    val x3078_b3453_x3086_b3455_s = Scalar("x3078_b3453_x3086_b3455")
    val x2810_b3400_x2818_b3402_s = Scalar("x2810_b3400_x2818_b3402")
    val x2914_b3420_x2922_b3422_s = Scalar("x2914_b3420_x2922_b3422")
    val x2786_x3298_x3305_v = Vector("x2786_x3298_x3305")
    val x3138_b3465_x3146_b3467_s = Scalar("x3138_b3465_x3146_b3467")
    val x3037_b3445_x3045_b3447_s = Scalar("x3037_b3445_x3045_b3447")
    val x2955_b3429_x2963_b3431_s = Scalar("x2955_b3429_x2963_b3431")
    val x3205_x3280_s = Scalar("x3205_x3280")
    val x2851_b3408_x2859_b3410_s = Scalar("x2851_b3408_x2859_b3410")
    val x2997_x3006_data_v = Vector("x2997_x3006_data")
    val x3078_b3452_x3086_b3454_s = Scalar("x3078_b3452_x3086_b3454")
    val x3097_b3457_x3105_b3459_s = Scalar("x3097_b3457_x3105_b3459")
    val x3138_b3464_x3146_b3466_s = Scalar("x3138_b3464_x3146_b3466")
    val x2787_x3310_x3317_v = Vector("x2787_x3310_x3317")
    val x3209_x3328_s = Scalar("x3209_x3328")
    val x2873_b3413_x2881_b3415_s = Scalar("x2873_b3413_x2881_b3415")
    val x3179_b3472_x3187_b3474_s = Scalar("x3179_b3472_x3187_b3474")
    val x2811_x2820_data_v = Vector("x2811_x2820_data")
    val x2770_x3225_x3233_v = Vector("x2770_x3225_x3233")
    val x3202_x3244_s = Scalar("x3202_x3244")
    val x2779_x3214_x3221_v = Vector("x2779_x3214_x3221")
    val x2782_x3250_x3257_v = Vector("x2782_x3250_x3257")
    val x3015_b3441_x3023_b3443_s = Scalar("x3015_b3441_x3023_b3443")
    val x3097_b3456_x3105_b3458_s = Scalar("x3097_b3456_x3105_b3458")
    val x3161_x3170_data_v = Vector("x3161_x3170_data")
    val bus_2865_s = Scalar("bus_2865")
    val x2955_b3428_x2963_b3430_s = Scalar("x2955_b3428_x2963_b3430")
    val x2772_x3249_x3257_v = Vector("x2772_x3249_x3257")
    val x2892_b3416_x2900_b3418_s = Scalar("x2892_b3416_x2900_b3418")
    val x3139_x3148_data_v = Vector("x3139_x3148_data")
    val x3160_b3469_x3168_b3471_s = Scalar("x3160_b3469_x3168_b3471")
    val x3200_x3220_s = Scalar("x3200_x3220")
    val x2833_x2842_data_v = Vector("x2833_x2842_data")
    val x3180_x3189_data_v = Vector("x3180_x3189_data")
    val x3119_b3461_x3127_b3463_s = Scalar("x3119_b3461_x3127_b3463")
    val x3203_x3256_s = Scalar("x3203_x3256")
    val x2784_x3274_x3281_v = Vector("x2784_x3274_x3281")
    val x3119_b3460_x3127_b3462_s = Scalar("x3119_b3460_x3127_b3462")
    val x2956_x2965_data_v = Vector("x2956_x2965_data")
    val x3377 = Sequential(name="x3377",parent=top) { implicit CU => 
      val x3377_unit = CounterChain(name = "x3377_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x3373 = MetaPipeline(name="x3373",parent=x3377) { implicit CU => 
      val x2755_x2766 = ScalarBuffer().wtPort(N_argin)
      val ctr1 = Counter(min=Const(0), max=x2755_x2766.load, step=Const(16), par=10) // Counter
      val x2768 = CounterChain(name = "x2768", ctr1).iter(12000)
    }
    val x2769_dsp0 = MemoryPipeline(name="x2769_dsp0",parent="x3373") { implicit CU => 
      val x2807_x2807 = VectorFIFO(size=1).wtPort(x2792_x2801_data_v)
      val x2803 = CounterChain.copy("x2808", "x2803")
      val x3211 = CounterChain.copy("x3221_0", "x3211")
      val x2769_x3213 = SRAM(size=16,banking = Strided(1)).wtPort(x2807_x2807.readPort).rdPort(x2769_x3213_x3221_v).rdAddr(x3211(0)).wtAddr(x2803(0))
    }
    val x2770_dsp0 = MemoryPipeline(name="x2770_dsp0",parent="x3373") { implicit CU => 
      val x2848_x2848 = VectorFIFO(size=1).wtPort(x2833_x2842_data_v)
      val x2844 = CounterChain.copy("x2849", "x2844")
      val x3223 = CounterChain.copy("x3233_0", "x3223")
      val x2770_x3225 = SRAM(size=16,banking = Strided(1)).wtPort(x2848_x2848.readPort).rdPort(x2770_x3225_x3233_v).rdAddr(x3223(0)).wtAddr(x2844(0))
    }
    val x2771_dsp0 = MemoryPipeline(name="x2771_dsp0",parent="x3373") { implicit CU => 
      val x2889_x2889 = VectorFIFO(size=1).wtPort(x2874_x2883_data_v)
      val x2885 = CounterChain.copy("x2890", "x2885")
      val x3235 = CounterChain.copy("x3245_0", "x3235")
      val x2771_x3237 = SRAM(size=16,banking = Strided(1)).wtPort(x2889_x2889.readPort).rdPort(x2771_x3237_x3245_v).rdAddr(x3235(0)).wtAddr(x2885(0))
    }
    val x2772_dsp0 = MemoryPipeline(name="x2772_dsp0",parent="x3373") { implicit CU => 
      val x2930_x2930 = VectorFIFO(size=1).wtPort(x2915_x2924_data_v)
      val x2926 = CounterChain.copy("x2931", "x2926")
      val x3247 = CounterChain.copy("x3257_0", "x3247")
      val x2772_x3249 = SRAM(size=16,banking = Strided(1)).wtPort(x2930_x2930.readPort).rdPort(x2772_x3249_x3257_v).rdAddr(x3247(0)).wtAddr(x2926(0))
    }
    val x2773_dsp0 = MemoryPipeline(name="x2773_dsp0",parent="x3373") { implicit CU => 
      val x2971_x2971 = VectorFIFO(size=1).wtPort(x2956_x2965_data_v)
      val x2967 = CounterChain.copy("x2972", "x2967")
      val x3259 = CounterChain.copy("x3269_0", "x3259")
      val x2773_x3261 = SRAM(size=16,banking = Strided(1)).wtPort(x2971_x2971.readPort).rdPort(x2773_x3261_x3269_v).rdAddr(x3259(0)).wtAddr(x2967(0))
    }
    val x2774_dsp0 = MemoryPipeline(name="x2774_dsp0",parent="x3373") { implicit CU => 
      val x3012_x3012 = VectorFIFO(size=1).wtPort(x2997_x3006_data_v)
      val x3008 = CounterChain.copy("x3013", "x3008")
      val x3271 = CounterChain.copy("x3281_0", "x3271")
      val x2774_x3273 = SRAM(size=16,banking = Strided(1)).wtPort(x3012_x3012.readPort).rdPort(x2774_x3273_x3281_v).rdAddr(x3271(0)).wtAddr(x3008(0))
    }
    val x2775_dsp0 = MemoryPipeline(name="x2775_dsp0",parent="x3373") { implicit CU => 
      val x3053_x3053 = VectorFIFO(size=1).wtPort(x3038_x3047_data_v)
      val x3049 = CounterChain.copy("x3054", "x3049")
      val x3283 = CounterChain.copy("x3293_0", "x3283")
      val x2775_x3285 = SRAM(size=16,banking = Strided(1)).wtPort(x3053_x3053.readPort).rdPort(x2775_x3285_x3293_v).rdAddr(x3283(0)).wtAddr(x3049(0))
    }
    val x2776_dsp0 = MemoryPipeline(name="x2776_dsp0",parent="x3373") { implicit CU => 
      val x3094_x3094 = VectorFIFO(size=1).wtPort(x3079_x3088_data_v)
      val x3090 = CounterChain.copy("x3095", "x3090")
      val x3295 = CounterChain.copy("x3305_0", "x3295")
      val x2776_x3297 = SRAM(size=16,banking = Strided(1)).wtPort(x3094_x3094.readPort).rdPort(x2776_x3297_x3305_v).rdAddr(x3295(0)).wtAddr(x3090(0))
    }
    val x2777_dsp0 = MemoryPipeline(name="x2777_dsp0",parent="x3373") { implicit CU => 
      val x3135_x3135 = VectorFIFO(size=1).wtPort(x3120_x3129_data_v)
      val x3131 = CounterChain.copy("x3136", "x3131")
      val x3307 = CounterChain.copy("x3317_0", "x3307")
      val x2777_x3309 = SRAM(size=16,banking = Strided(1)).wtPort(x3135_x3135.readPort).rdPort(x2777_x3309_x3317_v).rdAddr(x3307(0)).wtAddr(x3131(0))
    }
    val x2778_dsp0 = MemoryPipeline(name="x2778_dsp0",parent="x3373") { implicit CU => 
      val x3176_x3176 = VectorFIFO(size=1).wtPort(x3161_x3170_data_v)
      val x3172 = CounterChain.copy("x3177", "x3172")
      val x3319 = CounterChain.copy("x3329_0", "x3319")
      val x2778_x3321 = SRAM(size=16,banking = Strided(1)).wtPort(x3176_x3176.readPort).rdPort(x2778_x3321_x3329_v).rdAddr(x3319(0)).wtAddr(x3172(0))
    }
    val x2779_dsp0 = MemoryPipeline(name="x2779_dsp0",parent="x3373") { implicit CU => 
      val x2826_x2826 = VectorFIFO(size=1).wtPort(x2811_x2820_data_v)
      val x2822 = CounterChain.copy("x2827", "x2822")
      val x3211 = CounterChain.copy("x3221_0", "x3211")
      val x2779_x3214 = SRAM(size=16,banking = Strided(1)).wtPort(x2826_x2826.readPort).rdPort(x2779_x3214_x3221_v).rdAddr(x3211(0)).wtAddr(x2822(0))
    }
    val x2780_dsp0 = MemoryPipeline(name="x2780_dsp0",parent="x3373") { implicit CU => 
      val x2867_x2867 = VectorFIFO(size=1).wtPort(x2852_x2861_data_v)
      val x2863 = CounterChain.copy("x2868", "x2863")
      val x3223 = CounterChain.copy("x3233_0", "x3223")
      val x2780_x3226 = SRAM(size=16,banking = Strided(1)).wtPort(x2867_x2867.readPort).rdPort(x2780_x3226_x3233_v).rdAddr(x3223(0)).wtAddr(x2863(0))
    }
    val x2781_dsp0 = MemoryPipeline(name="x2781_dsp0",parent="x3373") { implicit CU => 
      val x2908_x2908 = VectorFIFO(size=1).wtPort(x2893_x2902_data_v)
      val x2904 = CounterChain.copy("x2909", "x2904")
      val x3235 = CounterChain.copy("x3245_0", "x3235")
      val x2781_x3238 = SRAM(size=16,banking = Strided(1)).wtPort(x2908_x2908.readPort).rdPort(x2781_x3238_x3245_v).rdAddr(x3235(0)).wtAddr(x2904(0))
    }
    val x2782_dsp0 = MemoryPipeline(name="x2782_dsp0",parent="x3373") { implicit CU => 
      val x2949_x2949 = VectorFIFO(size=1).wtPort(x2934_x2943_data_v)
      val x2945 = CounterChain.copy("x2950", "x2945")
      val x3247 = CounterChain.copy("x3257_0", "x3247")
      val x2782_x3250 = SRAM(size=16,banking = Strided(1)).wtPort(x2949_x2949.readPort).rdPort(x2782_x3250_x3257_v).rdAddr(x3247(0)).wtAddr(x2945(0))
    }
    val x2783_dsp0 = MemoryPipeline(name="x2783_dsp0",parent="x3373") { implicit CU => 
      val x2990_x2990 = VectorFIFO(size=1).wtPort(x2975_x2984_data_v)
      val x2986 = CounterChain.copy("x2991", "x2986")
      val x3259 = CounterChain.copy("x3269_0", "x3259")
      val x2783_x3262 = SRAM(size=16,banking = Strided(1)).wtPort(x2990_x2990.readPort).rdPort(x2783_x3262_x3269_v).rdAddr(x3259(0)).wtAddr(x2986(0))
    }
    val x2784_dsp0 = MemoryPipeline(name="x2784_dsp0",parent="x3373") { implicit CU => 
      val x3031_x3031 = VectorFIFO(size=1).wtPort(x3016_x3025_data_v)
      val x3027 = CounterChain.copy("x3032", "x3027")
      val x3271 = CounterChain.copy("x3281_0", "x3271")
      val x2784_x3274 = SRAM(size=16,banking = Strided(1)).wtPort(x3031_x3031.readPort).rdPort(x2784_x3274_x3281_v).rdAddr(x3271(0)).wtAddr(x3027(0))
    }
    val x2785_dsp0 = MemoryPipeline(name="x2785_dsp0",parent="x3373") { implicit CU => 
      val x3072_x3072 = VectorFIFO(size=1).wtPort(x3057_x3066_data_v)
      val x3068 = CounterChain.copy("x3073", "x3068")
      val x3283 = CounterChain.copy("x3293_0", "x3283")
      val x2785_x3286 = SRAM(size=16,banking = Strided(1)).wtPort(x3072_x3072.readPort).rdPort(x2785_x3286_x3293_v).rdAddr(x3283(0)).wtAddr(x3068(0))
    }
    val x2786_dsp0 = MemoryPipeline(name="x2786_dsp0",parent="x3373") { implicit CU => 
      val x3113_x3113 = VectorFIFO(size=1).wtPort(x3098_x3107_data_v)
      val x3109 = CounterChain.copy("x3114", "x3109")
      val x3295 = CounterChain.copy("x3305_0", "x3295")
      val x2786_x3298 = SRAM(size=16,banking = Strided(1)).wtPort(x3113_x3113.readPort).rdPort(x2786_x3298_x3305_v).rdAddr(x3295(0)).wtAddr(x3109(0))
    }
    val x2787_dsp0 = MemoryPipeline(name="x2787_dsp0",parent="x3373") { implicit CU => 
      val x3154_x3154 = VectorFIFO(size=1).wtPort(x3139_x3148_data_v)
      val x3150 = CounterChain.copy("x3155", "x3150")
      val x3307 = CounterChain.copy("x3317_0", "x3307")
      val x2787_x3310 = SRAM(size=16,banking = Strided(1)).wtPort(x3154_x3154.readPort).rdPort(x2787_x3310_x3317_v).rdAddr(x3307(0)).wtAddr(x3150(0))
    }
    val x2788_dsp0 = MemoryPipeline(name="x2788_dsp0",parent="x3373") { implicit CU => 
      val x3195_x3195 = VectorFIFO(size=1).wtPort(x3180_x3189_data_v)
      val x3191 = CounterChain.copy("x3196", "x3191")
      val x3319 = CounterChain.copy("x3329_0", "x3319")
      val x2788_x3322 = SRAM(size=16,banking = Strided(1)).wtPort(x3195_x3195.readPort).rdPort(x2788_x3322_x3329_v).rdAddr(x3319(0)).wtAddr(x3191(0))
    }
    val x2809 = StreamController(name="x2809",parent=x3373) { implicit CU => 
      val x2809_unit = CounterChain(name = "x2809_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x2800_0 = Pipeline(name="x2800_0",parent=x2809) { implicit CU => 
      val x2793 = CU.temp
      val x2795 = ScalarBuffer().wtPort(a_da)
      val x2768 = CounterChain.copy("x3373", "x2768")
      val x2800_unit = CounterChain(name = "x2800_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x2768(0)), Const(4)), op=FixMul, results=List(x2793))
      Stage(operands=List(x2793, CU.load(x2795)), op=FixAdd, results=List(CU.scalarOut(x2791_b3396_x2799_b3398_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x2791_b3397_x2799_b3399_s)))
    }
    val x2801 = MemoryController(name="x2801",parent=x2809,offchip=a_oc, mctpe=TileLoad) { implicit CU => 
      val x2791_b3396_x2801 = ScalarFIFO(name="offset",size=1).wtPort(x2791_b3396_x2799_b3398_s)
      val x2791_b3397_x2801 = ScalarFIFO(name="size",size=1).wtPort(x2791_b3397_x2799_b3399_s)
      CU.newVout("data", x2792_x2801_data_v)
    }
    val x2808 = Pipeline(name="x2808",parent=x2809) { implicit CU => 
      val ctr2 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x2803 = CounterChain(name = "x2803", ctr2).iter(1)
    }
    val x2828 = StreamController(name="x2828",parent=x3373) { implicit CU => 
      val x2828_unit = CounterChain(name = "x2828_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x2819_0 = Pipeline(name="x2819_0",parent=x2828) { implicit CU => 
      val x2812 = CU.temp
      val x2814 = ScalarBuffer().wtPort(b_da)
      val x2768 = CounterChain.copy("x3373", "x2768")
      val x2819_unit = CounterChain(name = "x2819_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x2768(0)), Const(4)), op=FixMul, results=List(x2812))
      Stage(operands=List(x2812, CU.load(x2814)), op=FixAdd, results=List(CU.scalarOut(x2810_b3400_x2818_b3402_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x2810_b3401_x2818_b3403_s)))
    }
    val x2820 = MemoryController(name="x2820",parent=x2828,offchip=b_oc, mctpe=TileLoad) { implicit CU => 
      val x2810_b3401_x2820 = ScalarFIFO(name="size",size=1).wtPort(x2810_b3401_x2818_b3403_s)
      val x2810_b3400_x2820 = ScalarFIFO(name="offset",size=1).wtPort(x2810_b3400_x2818_b3402_s)
      CU.newVout("data", x2811_x2820_data_v)
    }
    val x2827 = Pipeline(name="x2827",parent=x2828) { implicit CU => 
      val ctr3 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x2822 = CounterChain(name = "x2822", ctr3).iter(1)
    }
    val x2850 = StreamController(name="x2850",parent=x3373) { implicit CU => 
      val x2850_unit = CounterChain(name = "x2850_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x2841_0 = Pipeline(name="x2841_0",parent=x2850) { implicit CU => 
      val x2834 = CU.temp
      val x2836 = ScalarBuffer().wtPort(a_da)
      val x2768 = CounterChain.copy("x3373", "x2768")
      val x2841_unit = CounterChain(name = "x2841_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x2768(0)), Const(4)), op=FixMul, results=List(x2834))
      Stage(operands=List(x2834, CU.load(x2836)), op=FixAdd, results=List(CU.scalarOut(x2832_b3404_x2840_b3406_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x2832_b3405_x2840_b3407_s)))
    }
    val x2842 = MemoryController(name="x2842",parent=x2850,offchip=a_oc, mctpe=TileLoad) { implicit CU => 
      val x2832_b3405_x2842 = ScalarFIFO(name="size",size=1).wtPort(x2832_b3405_x2840_b3407_s)
      val x2832_b3404_x2842 = ScalarFIFO(name="offset",size=1).wtPort(x2832_b3404_x2840_b3406_s)
      CU.newVout("data", x2833_x2842_data_v)
    }
    val x2849 = Pipeline(name="x2849",parent=x2850) { implicit CU => 
      val ctr4 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x2844 = CounterChain(name = "x2844", ctr4).iter(1)
    }
    val x2869 = StreamController(name="x2869",parent=x3373) { implicit CU => 
      val x2869_unit = CounterChain(name = "x2869_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x2860_0 = Pipeline(name="x2860_0",parent=x2869) { implicit CU => 
      val x2853 = CU.temp
      val x2855 = ScalarBuffer().wtPort(b_da)
      val x2768 = CounterChain.copy("x3373", "x2768")
      val x2860_unit = CounterChain(name = "x2860_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x2768(0)), Const(4)), op=FixMul, results=List(x2853))
      Stage(operands=List(x2853, CU.load(x2855)), op=FixAdd, results=List(CU.scalarOut(x2851_b3408_x2859_b3410_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x2851_b3409_x2859_b3411_s)))
    }
    val x2861 = MemoryController(name="x2861",parent=x2869,offchip=b_oc, mctpe=TileLoad) { implicit CU => 
      val x2851_b3408_x2861 = ScalarFIFO(name="offset",size=1).wtPort(x2851_b3408_x2859_b3410_s)
      val x2851_b3409_x2861 = ScalarFIFO(name="size",size=1).wtPort(x2851_b3409_x2859_b3411_s)
      CU.newVout("data", x2852_x2861_data_v)
    }
    val x2868 = Pipeline(name="x2868",parent=x2869) { implicit CU => 
      val ctr5 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x2863 = CounterChain(name = "x2863", ctr5).iter(1)
    }
    val x2891 = StreamController(name="x2891",parent=x3373) { implicit CU => 
      val x2891_unit = CounterChain(name = "x2891_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x2882_0 = Pipeline(name="x2882_0",parent=x2891) { implicit CU => 
      val x2875 = CU.temp
      val x2877 = ScalarBuffer().wtPort(a_da)
      val x2768 = CounterChain.copy("x3373", "x2768")
      val x2882_unit = CounterChain(name = "x2882_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x2768(0)), Const(4)), op=FixMul, results=List(x2875))
      Stage(operands=List(x2875, CU.load(x2877)), op=FixAdd, results=List(CU.scalarOut(x2873_b3412_x2881_b3414_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x2873_b3413_x2881_b3415_s)))
    }
    val x2883 = MemoryController(name="x2883",parent=x2891,offchip=a_oc, mctpe=TileLoad) { implicit CU => 
      val x2873_b3412_x2883 = ScalarFIFO(name="offset",size=1).wtPort(x2873_b3412_x2881_b3414_s)
      val x2873_b3413_x2883 = ScalarFIFO(name="size",size=1).wtPort(x2873_b3413_x2881_b3415_s)
      CU.newVout("data", x2874_x2883_data_v)
    }
    val x2890 = Pipeline(name="x2890",parent=x2891) { implicit CU => 
      val ctr6 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x2885 = CounterChain(name = "x2885", ctr6).iter(1)
    }
    val x2910 = StreamController(name="x2910",parent=x3373) { implicit CU => 
      val x2910_unit = CounterChain(name = "x2910_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x2901_0 = Pipeline(name="x2901_0",parent=x2910) { implicit CU => 
      val x2894 = CU.temp
      val x2896 = ScalarBuffer().wtPort(b_da)
      val x2768 = CounterChain.copy("x3373", "x2768")
      val x2901_unit = CounterChain(name = "x2901_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x2768(0)), Const(4)), op=FixMul, results=List(x2894))
      Stage(operands=List(x2894, CU.load(x2896)), op=FixAdd, results=List(CU.scalarOut(x2892_b3416_x2900_b3418_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x2892_b3417_x2900_b3419_s)))
    }
    val x2902 = MemoryController(name="x2902",parent=x2910,offchip=b_oc, mctpe=TileLoad) { implicit CU => 
      val x2892_b3417_x2902 = ScalarFIFO(name="size",size=1).wtPort(x2892_b3417_x2900_b3419_s)
      val x2892_b3416_x2902 = ScalarFIFO(name="offset",size=1).wtPort(x2892_b3416_x2900_b3418_s)
      CU.newVout("data", x2893_x2902_data_v)
    }
    val x2909 = Pipeline(name="x2909",parent=x2910) { implicit CU => 
      val ctr7 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x2904 = CounterChain(name = "x2904", ctr7).iter(1)
    }
    val x2932 = StreamController(name="x2932",parent=x3373) { implicit CU => 
      val x2932_unit = CounterChain(name = "x2932_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x2923_0 = Pipeline(name="x2923_0",parent=x2932) { implicit CU => 
      val x2916 = CU.temp
      val x2918 = ScalarBuffer().wtPort(a_da)
      val x2768 = CounterChain.copy("x3373", "x2768")
      val x2923_unit = CounterChain(name = "x2923_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x2768(0)), Const(4)), op=FixMul, results=List(x2916))
      Stage(operands=List(x2916, CU.load(x2918)), op=FixAdd, results=List(CU.scalarOut(x2914_b3420_x2922_b3422_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x2914_b3421_x2922_b3423_s)))
    }
    val x2924 = MemoryController(name="x2924",parent=x2932,offchip=a_oc, mctpe=TileLoad) { implicit CU => 
      val x2914_b3421_x2924 = ScalarFIFO(name="size",size=1).wtPort(x2914_b3421_x2922_b3423_s)
      val x2914_b3420_x2924 = ScalarFIFO(name="offset",size=1).wtPort(x2914_b3420_x2922_b3422_s)
      CU.newVout("data", x2915_x2924_data_v)
    }
    val x2931 = Pipeline(name="x2931",parent=x2932) { implicit CU => 
      val ctr8 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x2926 = CounterChain(name = "x2926", ctr8).iter(1)
    }
    val x2951 = StreamController(name="x2951",parent=x3373) { implicit CU => 
      val x2951_unit = CounterChain(name = "x2951_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x2942_0 = Pipeline(name="x2942_0",parent=x2951) { implicit CU => 
      val x2935 = CU.temp
      val x2937 = ScalarBuffer().wtPort(b_da)
      val x2768 = CounterChain.copy("x3373", "x2768")
      val x2942_unit = CounterChain(name = "x2942_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x2768(0)), Const(4)), op=FixMul, results=List(x2935))
      Stage(operands=List(x2935, CU.load(x2937)), op=FixAdd, results=List(CU.scalarOut(x2933_b3424_x2941_b3426_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x2933_b3425_x2941_b3427_s)))
    }
    val x2943 = MemoryController(name="x2943",parent=x2951,offchip=b_oc, mctpe=TileLoad) { implicit CU => 
      val x2933_b3425_x2943 = ScalarFIFO(name="size",size=1).wtPort(x2933_b3425_x2941_b3427_s)
      val x2933_b3424_x2943 = ScalarFIFO(name="offset",size=1).wtPort(x2933_b3424_x2941_b3426_s)
      CU.newVout("data", x2934_x2943_data_v)
    }
    val x2950 = Pipeline(name="x2950",parent=x2951) { implicit CU => 
      val ctr9 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x2945 = CounterChain(name = "x2945", ctr9).iter(1)
    }
    val x2973 = StreamController(name="x2973",parent=x3373) { implicit CU => 
      val x2973_unit = CounterChain(name = "x2973_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x2964_0 = Pipeline(name="x2964_0",parent=x2973) { implicit CU => 
      val x2957 = CU.temp
      val x2959 = ScalarBuffer().wtPort(a_da)
      val x2768 = CounterChain.copy("x3373", "x2768")
      val x2964_unit = CounterChain(name = "x2964_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x2768(0)), Const(4)), op=FixMul, results=List(x2957))
      Stage(operands=List(x2957, CU.load(x2959)), op=FixAdd, results=List(CU.scalarOut(x2955_b3428_x2963_b3430_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x2955_b3429_x2963_b3431_s)))
    }
    val x2965 = MemoryController(name="x2965",parent=x2973,offchip=a_oc, mctpe=TileLoad) { implicit CU => 
      val x2955_b3429_x2965 = ScalarFIFO(name="size",size=1).wtPort(x2955_b3429_x2963_b3431_s)
      val x2955_b3428_x2965 = ScalarFIFO(name="offset",size=1).wtPort(x2955_b3428_x2963_b3430_s)
      CU.newVout("data", x2956_x2965_data_v)
    }
    val x2972 = Pipeline(name="x2972",parent=x2973) { implicit CU => 
      val ctr10 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x2967 = CounterChain(name = "x2967", ctr10).iter(1)
    }
    val x2992 = StreamController(name="x2992",parent=x3373) { implicit CU => 
      val x2992_unit = CounterChain(name = "x2992_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x2983_0 = Pipeline(name="x2983_0",parent=x2992) { implicit CU => 
      val x2976 = CU.temp
      val x2978 = ScalarBuffer().wtPort(b_da)
      val x2768 = CounterChain.copy("x3373", "x2768")
      val x2983_unit = CounterChain(name = "x2983_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x2768(0)), Const(4)), op=FixMul, results=List(x2976))
      Stage(operands=List(x2976, CU.load(x2978)), op=FixAdd, results=List(CU.scalarOut(x2974_b3432_x2982_b3434_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x2974_b3433_x2982_b3435_s)))
    }
    val x2984 = MemoryController(name="x2984",parent=x2992,offchip=b_oc, mctpe=TileLoad) { implicit CU => 
      val x2974_b3432_x2984 = ScalarFIFO(name="offset",size=1).wtPort(x2974_b3432_x2982_b3434_s)
      val x2974_b3433_x2984 = ScalarFIFO(name="size",size=1).wtPort(x2974_b3433_x2982_b3435_s)
      CU.newVout("data", x2975_x2984_data_v)
    }
    val x2991 = Pipeline(name="x2991",parent=x2992) { implicit CU => 
      val ctr11 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x2986 = CounterChain(name = "x2986", ctr11).iter(1)
    }
    val x3014 = StreamController(name="x3014",parent=x3373) { implicit CU => 
      val x3014_unit = CounterChain(name = "x3014_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x3005_0 = Pipeline(name="x3005_0",parent=x3014) { implicit CU => 
      val x2998 = CU.temp
      val x3000 = ScalarBuffer().wtPort(a_da)
      val x2768 = CounterChain.copy("x3373", "x2768")
      val x3005_unit = CounterChain(name = "x3005_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x2768(0)), Const(4)), op=FixMul, results=List(x2998))
      Stage(operands=List(x2998, CU.load(x3000)), op=FixAdd, results=List(CU.scalarOut(x2996_b3436_x3004_b3438_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x2996_b3437_x3004_b3439_s)))
    }
    val x3006 = MemoryController(name="x3006",parent=x3014,offchip=a_oc, mctpe=TileLoad) { implicit CU => 
      val x2996_b3437_x3006 = ScalarFIFO(name="size",size=1).wtPort(x2996_b3437_x3004_b3439_s)
      val x2996_b3436_x3006 = ScalarFIFO(name="offset",size=1).wtPort(x2996_b3436_x3004_b3438_s)
      CU.newVout("data", x2997_x3006_data_v)
    }
    val x3013 = Pipeline(name="x3013",parent=x3014) { implicit CU => 
      val ctr12 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x3008 = CounterChain(name = "x3008", ctr12).iter(1)
    }
    val x3033 = StreamController(name="x3033",parent=x3373) { implicit CU => 
      val x3033_unit = CounterChain(name = "x3033_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x3024_0 = Pipeline(name="x3024_0",parent=x3033) { implicit CU => 
      val x3017 = CU.temp
      val x3019 = ScalarBuffer().wtPort(b_da)
      val x2768 = CounterChain.copy("x3373", "x2768")
      val x3024_unit = CounterChain(name = "x3024_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x2768(0)), Const(4)), op=FixMul, results=List(x3017))
      Stage(operands=List(x3017, CU.load(x3019)), op=FixAdd, results=List(CU.scalarOut(x3015_b3440_x3023_b3442_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x3015_b3441_x3023_b3443_s)))
    }
    val x3025 = MemoryController(name="x3025",parent=x3033,offchip=b_oc, mctpe=TileLoad) { implicit CU => 
      val x3015_b3441_x3025 = ScalarFIFO(name="size",size=1).wtPort(x3015_b3441_x3023_b3443_s)
      val x3015_b3440_x3025 = ScalarFIFO(name="offset",size=1).wtPort(x3015_b3440_x3023_b3442_s)
      CU.newVout("data", x3016_x3025_data_v)
    }
    val x3032 = Pipeline(name="x3032",parent=x3033) { implicit CU => 
      val ctr13 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x3027 = CounterChain(name = "x3027", ctr13).iter(1)
    }
    val x3055 = StreamController(name="x3055",parent=x3373) { implicit CU => 
      val x3055_unit = CounterChain(name = "x3055_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x3046_0 = Pipeline(name="x3046_0",parent=x3055) { implicit CU => 
      val x3039 = CU.temp
      val x3041 = ScalarBuffer().wtPort(a_da)
      val x2768 = CounterChain.copy("x3373", "x2768")
      val x3046_unit = CounterChain(name = "x3046_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x2768(0)), Const(4)), op=FixMul, results=List(x3039))
      Stage(operands=List(x3039, CU.load(x3041)), op=FixAdd, results=List(CU.scalarOut(x3037_b3444_x3045_b3446_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x3037_b3445_x3045_b3447_s)))
    }
    val x3047 = MemoryController(name="x3047",parent=x3055,offchip=a_oc, mctpe=TileLoad) { implicit CU => 
      val x3037_b3444_x3047 = ScalarFIFO(name="offset",size=1).wtPort(x3037_b3444_x3045_b3446_s)
      val x3037_b3445_x3047 = ScalarFIFO(name="size",size=1).wtPort(x3037_b3445_x3045_b3447_s)
      CU.newVout("data", x3038_x3047_data_v)
    }
    val x3054 = Pipeline(name="x3054",parent=x3055) { implicit CU => 
      val ctr14 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x3049 = CounterChain(name = "x3049", ctr14).iter(1)
    }
    val x3074 = StreamController(name="x3074",parent=x3373) { implicit CU => 
      val x3074_unit = CounterChain(name = "x3074_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x3065_0 = Pipeline(name="x3065_0",parent=x3074) { implicit CU => 
      val x3058 = CU.temp
      val x3060 = ScalarBuffer().wtPort(b_da)
      val x2768 = CounterChain.copy("x3373", "x2768")
      val x3065_unit = CounterChain(name = "x3065_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x2768(0)), Const(4)), op=FixMul, results=List(x3058))
      Stage(operands=List(x3058, CU.load(x3060)), op=FixAdd, results=List(CU.scalarOut(x3056_b3448_x3064_b3450_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x3056_b3449_x3064_b3451_s)))
    }
    val x3066 = MemoryController(name="x3066",parent=x3074,offchip=b_oc, mctpe=TileLoad) { implicit CU => 
      val x3056_b3449_x3066 = ScalarFIFO(name="size",size=1).wtPort(x3056_b3449_x3064_b3451_s)
      val x3056_b3448_x3066 = ScalarFIFO(name="offset",size=1).wtPort(x3056_b3448_x3064_b3450_s)
      CU.newVout("data", x3057_x3066_data_v)
    }
    val x3073 = Pipeline(name="x3073",parent=x3074) { implicit CU => 
      val ctr15 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x3068 = CounterChain(name = "x3068", ctr15).iter(1)
    }
    val x3096 = StreamController(name="x3096",parent=x3373) { implicit CU => 
      val x3096_unit = CounterChain(name = "x3096_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x3087_0 = Pipeline(name="x3087_0",parent=x3096) { implicit CU => 
      val x3080 = CU.temp
      val x3082 = ScalarBuffer().wtPort(a_da)
      val x2768 = CounterChain.copy("x3373", "x2768")
      val x3087_unit = CounterChain(name = "x3087_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x2768(0)), Const(4)), op=FixMul, results=List(x3080))
      Stage(operands=List(x3080, CU.load(x3082)), op=FixAdd, results=List(CU.scalarOut(x3078_b3452_x3086_b3454_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x3078_b3453_x3086_b3455_s)))
    }
    val x3088 = MemoryController(name="x3088",parent=x3096,offchip=a_oc, mctpe=TileLoad) { implicit CU => 
      val x3078_b3453_x3088 = ScalarFIFO(name="size",size=1).wtPort(x3078_b3453_x3086_b3455_s)
      val x3078_b3452_x3088 = ScalarFIFO(name="offset",size=1).wtPort(x3078_b3452_x3086_b3454_s)
      CU.newVout("data", x3079_x3088_data_v)
    }
    val x3095 = Pipeline(name="x3095",parent=x3096) { implicit CU => 
      val ctr16 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x3090 = CounterChain(name = "x3090", ctr16).iter(1)
    }
    val x3115 = StreamController(name="x3115",parent=x3373) { implicit CU => 
      val x3115_unit = CounterChain(name = "x3115_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x3106_0 = Pipeline(name="x3106_0",parent=x3115) { implicit CU => 
      val x3099 = CU.temp
      val x3101 = ScalarBuffer().wtPort(b_da)
      val x2768 = CounterChain.copy("x3373", "x2768")
      val x3106_unit = CounterChain(name = "x3106_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x2768(0)), Const(4)), op=FixMul, results=List(x3099))
      Stage(operands=List(x3099, CU.load(x3101)), op=FixAdd, results=List(CU.scalarOut(x3097_b3456_x3105_b3458_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x3097_b3457_x3105_b3459_s)))
    }
    val x3107 = MemoryController(name="x3107",parent=x3115,offchip=b_oc, mctpe=TileLoad) { implicit CU => 
      val x3097_b3456_x3107 = ScalarFIFO(name="offset",size=1).wtPort(x3097_b3456_x3105_b3458_s)
      val x3097_b3457_x3107 = ScalarFIFO(name="size",size=1).wtPort(x3097_b3457_x3105_b3459_s)
      CU.newVout("data", x3098_x3107_data_v)
    }
    val x3114 = Pipeline(name="x3114",parent=x3115) { implicit CU => 
      val ctr17 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x3109 = CounterChain(name = "x3109", ctr17).iter(1)
    }
    val x3137 = StreamController(name="x3137",parent=x3373) { implicit CU => 
      val x3137_unit = CounterChain(name = "x3137_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x3128_0 = Pipeline(name="x3128_0",parent=x3137) { implicit CU => 
      val x3121 = CU.temp
      val x3123 = ScalarBuffer().wtPort(a_da)
      val x2768 = CounterChain.copy("x3373", "x2768")
      val x3128_unit = CounterChain(name = "x3128_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x2768(0)), Const(4)), op=FixMul, results=List(x3121))
      Stage(operands=List(x3121, CU.load(x3123)), op=FixAdd, results=List(CU.scalarOut(x3119_b3460_x3127_b3462_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x3119_b3461_x3127_b3463_s)))
    }
    val x3129 = MemoryController(name="x3129",parent=x3137,offchip=a_oc, mctpe=TileLoad) { implicit CU => 
      val x3119_b3461_x3129 = ScalarFIFO(name="size",size=1).wtPort(x3119_b3461_x3127_b3463_s)
      val x3119_b3460_x3129 = ScalarFIFO(name="offset",size=1).wtPort(x3119_b3460_x3127_b3462_s)
      CU.newVout("data", x3120_x3129_data_v)
    }
    val x3136 = Pipeline(name="x3136",parent=x3137) { implicit CU => 
      val ctr18 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x3131 = CounterChain(name = "x3131", ctr18).iter(1)
    }
    val x3156 = StreamController(name="x3156",parent=x3373) { implicit CU => 
      val x3156_unit = CounterChain(name = "x3156_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x3147_0 = Pipeline(name="x3147_0",parent=x3156) { implicit CU => 
      val x3140 = CU.temp
      val x3142 = ScalarBuffer().wtPort(b_da)
      val x2768 = CounterChain.copy("x3373", "x2768")
      val x3147_unit = CounterChain(name = "x3147_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x2768(0)), Const(4)), op=FixMul, results=List(x3140))
      Stage(operands=List(x3140, CU.load(x3142)), op=FixAdd, results=List(CU.scalarOut(x3138_b3464_x3146_b3466_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x3138_b3465_x3146_b3467_s)))
    }
    val x3148 = MemoryController(name="x3148",parent=x3156,offchip=b_oc, mctpe=TileLoad) { implicit CU => 
      val x3138_b3465_x3148 = ScalarFIFO(name="size",size=1).wtPort(x3138_b3465_x3146_b3467_s)
      val x3138_b3464_x3148 = ScalarFIFO(name="offset",size=1).wtPort(x3138_b3464_x3146_b3466_s)
      CU.newVout("data", x3139_x3148_data_v)
    }
    val x3155 = Pipeline(name="x3155",parent=x3156) { implicit CU => 
      val ctr19 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x3150 = CounterChain(name = "x3150", ctr19).iter(1)
    }
    val x3178 = StreamController(name="x3178",parent=x3373) { implicit CU => 
      val x3178_unit = CounterChain(name = "x3178_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x3169_0 = Pipeline(name="x3169_0",parent=x3178) { implicit CU => 
      val x3162 = CU.temp
      val x3164 = ScalarBuffer().wtPort(a_da)
      val x2768 = CounterChain.copy("x3373", "x2768")
      val x3169_unit = CounterChain(name = "x3169_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x2768(0)), Const(4)), op=FixMul, results=List(x3162))
      Stage(operands=List(x3162, CU.load(x3164)), op=FixAdd, results=List(CU.scalarOut(x3160_b3468_x3168_b3470_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x3160_b3469_x3168_b3471_s)))
    }
    val x3170 = MemoryController(name="x3170",parent=x3178,offchip=a_oc, mctpe=TileLoad) { implicit CU => 
      val x3160_b3468_x3170 = ScalarFIFO(name="offset",size=1).wtPort(x3160_b3468_x3168_b3470_s)
      val x3160_b3469_x3170 = ScalarFIFO(name="size",size=1).wtPort(x3160_b3469_x3168_b3471_s)
      CU.newVout("data", x3161_x3170_data_v)
    }
    val x3177 = Pipeline(name="x3177",parent=x3178) { implicit CU => 
      val ctr20 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x3172 = CounterChain(name = "x3172", ctr20).iter(1)
    }
    val x3197 = StreamController(name="x3197",parent=x3373) { implicit CU => 
      val x3197_unit = CounterChain(name = "x3197_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x3188_0 = Pipeline(name="x3188_0",parent=x3197) { implicit CU => 
      val x3181 = CU.temp
      val x3183 = ScalarBuffer().wtPort(b_da)
      val x2768 = CounterChain.copy("x3373", "x2768")
      val x3188_unit = CounterChain(name = "x3188_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x2768(0)), Const(4)), op=FixMul, results=List(x3181))
      Stage(operands=List(x3181, CU.load(x3183)), op=FixAdd, results=List(CU.scalarOut(x3179_b3472_x3187_b3474_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x3179_b3473_x3187_b3475_s)))
    }
    val x3189 = MemoryController(name="x3189",parent=x3197,offchip=b_oc, mctpe=TileLoad) { implicit CU => 
      val x3179_b3473_x3189 = ScalarFIFO(name="size",size=1).wtPort(x3179_b3473_x3187_b3475_s)
      val x3179_b3472_x3189 = ScalarFIFO(name="offset",size=1).wtPort(x3179_b3472_x3187_b3474_s)
      CU.newVout("data", x3180_x3189_data_v)
    }
    val x3196 = Pipeline(name="x3196",parent=x3197) { implicit CU => 
      val ctr21 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x3191 = CounterChain(name = "x3191", ctr21).iter(1)
    }
    val x3221_0 = Pipeline(name="x3221_0",parent=x3373) { implicit CU => 
      val x3214_x3214 = VectorFIFO(size=1).wtPort(x2779_x3214_x3221_v)
      val x3213_x3213 = VectorFIFO(size=1).wtPort(x2769_x3213_x3221_v)
      val ctr22 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x3211 = CounterChain(name = "x3211", ctr22).iter(1)
      Stage(operands=List(CU.load(x3213_x3213), CU.load(x3214_x3214)), op=FixMul, results=List(CU.reduce))
      val (_, rr2815) = Stage.reduce(op=FixAdd, init=Const(0), accumParent="x3221_0")
      Stage(operands=List(rr2815), op=Bypass, results=List(CU.scalarOut(x3200_x3220_s)))
    }
    val x3233_0 = Pipeline(name="x3233_0",parent=x3373) { implicit CU => 
      val x3226_x3226 = VectorFIFO(size=1).wtPort(x2780_x3226_x3233_v)
      val x3225_x3225 = VectorFIFO(size=1).wtPort(x2770_x3225_x3233_v)
      val ctr23 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x3223 = CounterChain(name = "x3223", ctr23).iter(1)
      Stage(operands=List(CU.load(x3225_x3225), CU.load(x3226_x3226)), op=FixMul, results=List(CU.reduce))
      val (_, rr2820) = Stage.reduce(op=FixAdd, init=Const(0), accumParent="x3233_0")
      Stage(operands=List(rr2820), op=Bypass, results=List(CU.scalarOut(x3201_x3232_s)))
    }
    val x3245_0 = Pipeline(name="x3245_0",parent=x3373) { implicit CU => 
      val x3238_x3238 = VectorFIFO(size=1).wtPort(x2781_x3238_x3245_v)
      val x3237_x3237 = VectorFIFO(size=1).wtPort(x2771_x3237_x3245_v)
      val ctr24 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x3235 = CounterChain(name = "x3235", ctr24).iter(1)
      Stage(operands=List(CU.load(x3237_x3237), CU.load(x3238_x3238)), op=FixMul, results=List(CU.reduce))
      val (_, rr2825) = Stage.reduce(op=FixAdd, init=Const(0), accumParent="x3245_0")
      Stage(operands=List(rr2825), op=Bypass, results=List(CU.scalarOut(x3202_x3244_s)))
    }
    val x3257_0 = Pipeline(name="x3257_0",parent=x3373) { implicit CU => 
      val x3250_x3250 = VectorFIFO(size=1).wtPort(x2782_x3250_x3257_v)
      val x3249_x3249 = VectorFIFO(size=1).wtPort(x2772_x3249_x3257_v)
      val ctr25 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x3247 = CounterChain(name = "x3247", ctr25).iter(1)
      Stage(operands=List(CU.load(x3249_x3249), CU.load(x3250_x3250)), op=FixMul, results=List(CU.reduce))
      val (_, rr2830) = Stage.reduce(op=FixAdd, init=Const(0), accumParent="x3257_0")
      Stage(operands=List(rr2830), op=Bypass, results=List(CU.scalarOut(x3203_x3256_s)))
    }
    val x3269_0 = Pipeline(name="x3269_0",parent=x3373) { implicit CU => 
      val x3262_x3262 = VectorFIFO(size=1).wtPort(x2783_x3262_x3269_v)
      val x3261_x3261 = VectorFIFO(size=1).wtPort(x2773_x3261_x3269_v)
      val ctr26 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x3259 = CounterChain(name = "x3259", ctr26).iter(1)
      Stage(operands=List(CU.load(x3261_x3261), CU.load(x3262_x3262)), op=FixMul, results=List(CU.reduce))
      val (_, rr2835) = Stage.reduce(op=FixAdd, init=Const(0), accumParent="x3269_0")
      Stage(operands=List(rr2835), op=Bypass, results=List(CU.scalarOut(x3204_x3268_s)))
    }
    val x3281_0 = Pipeline(name="x3281_0",parent=x3373) { implicit CU => 
      val x3274_x3274 = VectorFIFO(size=1).wtPort(x2784_x3274_x3281_v)
      val x3273_x3273 = VectorFIFO(size=1).wtPort(x2774_x3273_x3281_v)
      val ctr27 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x3271 = CounterChain(name = "x3271", ctr27).iter(1)
      Stage(operands=List(CU.load(x3273_x3273), CU.load(x3274_x3274)), op=FixMul, results=List(CU.reduce))
      val (_, rr2840) = Stage.reduce(op=FixAdd, init=Const(0), accumParent="x3281_0")
      Stage(operands=List(rr2840), op=Bypass, results=List(CU.scalarOut(x3205_x3280_s)))
    }
    val x3293_0 = Pipeline(name="x3293_0",parent=x3373) { implicit CU => 
      val x3286_x3286 = VectorFIFO(size=1).wtPort(x2785_x3286_x3293_v)
      val x3285_x3285 = VectorFIFO(size=1).wtPort(x2775_x3285_x3293_v)
      val ctr28 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x3283 = CounterChain(name = "x3283", ctr28).iter(1)
      Stage(operands=List(CU.load(x3285_x3285), CU.load(x3286_x3286)), op=FixMul, results=List(CU.reduce))
      val (_, rr2845) = Stage.reduce(op=FixAdd, init=Const(0), accumParent="x3293_0")
      Stage(operands=List(rr2845), op=Bypass, results=List(CU.scalarOut(x3206_x3292_s)))
    }
    val x3305_0 = Pipeline(name="x3305_0",parent=x3373) { implicit CU => 
      val x3298_x3298 = VectorFIFO(size=1).wtPort(x2786_x3298_x3305_v)
      val x3297_x3297 = VectorFIFO(size=1).wtPort(x2776_x3297_x3305_v)
      val ctr29 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x3295 = CounterChain(name = "x3295", ctr29).iter(1)
      Stage(operands=List(CU.load(x3297_x3297), CU.load(x3298_x3298)), op=FixMul, results=List(CU.reduce))
      val (_, rr2850) = Stage.reduce(op=FixAdd, init=Const(0), accumParent="x3305_0")
      Stage(operands=List(rr2850), op=Bypass, results=List(CU.scalarOut(x3207_x3304_s)))
    }
    val x3317_0 = Pipeline(name="x3317_0",parent=x3373) { implicit CU => 
      val x3310_x3310 = VectorFIFO(size=1).wtPort(x2787_x3310_x3317_v)
      val x3309_x3309 = VectorFIFO(size=1).wtPort(x2777_x3309_x3317_v)
      val ctr30 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x3307 = CounterChain(name = "x3307", ctr30).iter(1)
      Stage(operands=List(CU.load(x3309_x3309), CU.load(x3310_x3310)), op=FixMul, results=List(CU.reduce))
      val (_, rr2855) = Stage.reduce(op=FixAdd, init=Const(0), accumParent="x3317_0")
      Stage(operands=List(rr2855), op=Bypass, results=List(CU.scalarOut(x3208_x3316_s)))
    }
    val x3329_0 = Pipeline(name="x3329_0",parent=x3373) { implicit CU => 
      val x3322_x3322 = VectorFIFO(size=1).wtPort(x2788_x3322_x3329_v)
      val x3321_x3321 = VectorFIFO(size=1).wtPort(x2778_x3321_x3329_v)
      val ctr31 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x3319 = CounterChain(name = "x3319", ctr31).iter(1)
      Stage(operands=List(CU.load(x3321_x3321), CU.load(x3322_x3322)), op=FixMul, results=List(CU.reduce))
      val (_, rr2860) = Stage.reduce(op=FixAdd, init=Const(0), accumParent="x3329_0")
      Stage(operands=List(rr2860), op=Bypass, results=List(CU.scalarOut(x3209_x3328_s)))
    }
    val x3372 = StreamController(name="x3372",parent=x3373) { implicit CU => 
      val x3372_unit = CounterChain(name = "x3372_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x3372_0 = Pipeline(name="x3372_0",parent=x3372) { implicit CU => 
      val x3333 = CU.temp()
      val x3338 = CU.temp()
      val x3205_x3341 = ScalarBuffer().wtPort(x3205_x3280_s)
      val x3202_x3337 = ScalarBuffer().wtPort(x3202_x3244_s)
      val x3201_x3331 = ScalarBuffer().wtPort(x3201_x3232_s)
      val x3200_x3332 = ScalarBuffer().wtPort(x3200_x3220_s)
      val x3204_x3342 = ScalarBuffer().wtPort(x3204_x3268_s)
      val x3203_x3336 = ScalarBuffer().wtPort(x3203_x3256_s)
      Stage(operands=List(CU.load(x3200_x3332), CU.load(x3201_x3331)), op=FixAdd, results=List(x3333))
      Stage(operands=List(CU.load(x3202_x3337), CU.load(x3203_x3336)), op=FixAdd, results=List(x3338))
      Stage(operands=List(x3333, x3338), op=FixAdd, results=List(CU.scalarOut(bus_2865_s)))
      Stage(operands=List(CU.load(x3204_x3342), CU.load(x3205_x3341)), op=FixAdd, results=List(CU.scalarOut(bus_2869_s)))
    }
    val x3372_1 = Pipeline(name="x3372_1",parent=x3372) { implicit CU => 
      val x3353 = CU.temp()
      val x3362 = CU.temp()
      val x3359 = CU.temp()
      val x3348 = CU.temp()
      val x3206_x3347 = ScalarBuffer().wtPort(x3206_x3292_s)
      val x3208_x3352 = ScalarBuffer().wtPort(x3208_x3316_s)
      val x3356 = ScalarFIFO(size=1).wtPort(bus_2865_s)
      val x3209_x3351 = ScalarBuffer().wtPort(x3209_x3328_s)
      val x3207_x3346 = ScalarBuffer().wtPort(x3207_x3304_s)
      val x3343 = ScalarFIFO(size=1).wtPort(bus_2869_s)
      Stage(operands=List(CU.load(x3206_x3347), CU.load(x3207_x3346)), op=FixAdd, results=List(x3348))
      Stage(operands=List(CU.load(x3343), x3348), op=FixAdd, results=List(x3359))
      Stage(operands=List(CU.load(x3356), x3359), op=FixAdd, results=List(x3362))
      Stage(operands=List(CU.load(x3208_x3352), CU.load(x3209_x3351)), op=FixAdd, results=List(x3353))
      Stage(operands=List(x3362, x3353), op=FixAdd, results=List(CU.scalarOut(bus_2875_s)))
    }
    val x3372_2 = Pipeline(name="x3372_2",parent=x3372) { implicit CU => 
      val rr2875 = ScalarFIFO(size=1).wtPort(bus_2875_s)
      Stage(operands=List(CU.load(rr2875)), op=Bypass, results=List(CU.reduce))
      val (_, rr2877) = Stage.reduce(op=FixAdd, init=Const(0), accumParent="x3373")
      Stage(operands=List(rr2877), op=Bypass, results=List(CU.scalarOut(x2762_x3375_argout)))
    }
    
  }
}
