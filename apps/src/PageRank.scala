import pir.graph
import pir.graph._
import pir.graph.enums._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.misc._
import pir.PIRApp

object PageRank extends PIRApp {
  def main(args: String*)(top:Top) = {
    val x3198_b3444_x3215_b3453_scalar = Scalar("x3198_b3444_x3215_b3453")
    val x3334_b3458_x3341_b3463_scalar = Scalar("x3334_b3458_x3341_b3463")
    val x3073_argin = ArgIn("x3073")
    val x3091_b3416_x3097_b3419_scalar = Scalar("x3091_b3416_x3097_b3419")
    val x3171_x3178_scalar = Scalar("x3171_x3178")
    val x3049_oc = OffChip("x3049")
    val x3144_x3194_vector = Vector("x3144_x3194")
    val x3201_argin = ArgIn("x3201")
    val x3048_oc = OffChip("x3048")
    val x3042_argin = ArgIn("x3042")
    val x3070_b3410_x3076_b3413_scalar = Scalar("x3070_b3410_x3076_b3413")
    val x3067_x3087_vector = Vector("x3067_x3087")
    val x3199_b3447_x3218_b3456_scalar = Scalar("x3199_b3447_x3218_b3456")
    val x3199_b3446_x3218_b3455_scalar = Scalar("x3199_b3446_x3218_b3455")
    val x3147_b3431_x3166_b3440_scalar = Scalar("x3147_b3431_x3166_b3440")
    val x3069_x3129_vector = Vector("x3069_x3129")
    val x3112_b3423_x3118_b3426_scalar = Scalar("x3112_b3423_x3118_b3426")
    val x3071_x3077_scalar = Scalar("x3071_x3077")
    val x3145_x3246_vector = Vector("x3145_x3246")
    val x3144_x3274_x3276_vector = Vector("x3144_x3274_x3276")
    val x3070_b3409_x3076_b3412_scalar = Scalar("x3070_b3409_x3076_b3412")
    val x3336_b3461_x3350_b3466_vector = Vector("x3336_b3461_x3350_b3466")
    val x3091_b3417_x3097_b3420_scalar = Scalar("x3091_b3417_x3097_b3420")
    val x3335_x3342_scalar = Scalar("x3335_x3342")
    val x3251_x3305_x3278_vector = Vector("x3251_x3305_x3278")
    val x3146_b3429_x3163_b3438_scalar = Scalar("x3146_b3429_x3163_b3438")
    val x3068_x3108_vector = Vector("x3068_x3108")
    val x3091_b3415_x3097_b3418_scalar = Scalar("x3091_b3415_x3097_b3418")
    val x3069_x3141_x3143_vector = Vector("x3069_x3141_x3143")
    val x3112_b3421_x3118_b3424_scalar = Scalar("x3112_b3421_x3118_b3424")
    val x3067_x3306_x3325_vector = Vector("x3067_x3306_x3325")
    val x3338_argin = ArgIn("x3338")
    val x3251_x3273_x3269_vector = Vector("x3251_x3273_x3269")
    val x3146_b3428_x3163_b3437_scalar = Scalar("x3146_b3428_x3163_b3437")
    val x3278_x3297_vector = Vector("x3278_x3297")
    val x3066_x3307_x3325_vector = Vector("x3066_x3307_x3325")
    val x3269_x3283_x3290_vector = Vector("x3269_x3283_x3290")
    val x3169_x3174_scalar = Scalar("x3169_x3174")
    val x3043_argin = ArgIn("x3043")
    val x3066_x3346_x3351_vector = Vector("x3066_x3346_x3351")
    val x3144_x3256_x3267_vector = Vector("x3144_x3256_x3267")
    val x3094_argin = ArgIn("x3094")
    val x3113_x3119_scalar = Scalar("x3113_x3119")
    val x3066_x3331_vector = Vector("x3066_x3331")
    val x3092_x3098_scalar = Scalar("x3092_x3098")
    val x3115_argin = ArgIn("x3115")
    val x3279_x3289_vector = Vector("x3279_x3289")
    val x3198_b3443_x3215_b3452_scalar = Scalar("x3198_b3443_x3215_b3452")
    val x3269_x3275_vector = Vector("x3269_x3275")
    val x3223_x3230_scalar = Scalar("x3223_x3230")
    val x3147_b3432_x3166_b3441_scalar = Scalar("x3147_b3432_x3166_b3441")
    val x3136_x3139_scalar = Scalar("x3136_x3139")
    val x3198_b3442_x3215_b3451_scalar = Scalar("x3198_b3442_x3215_b3451")
    val x3068_x3138_x3140_vector = Vector("x3068_x3138_x3140")
    val x3278_x3309_x3325_vector = Vector("x3278_x3309_x3325")
    val x3144_x3304_x3325_vector = Vector("x3144_x3304_x3325")
    val x3300_x3324_vector = Vector("x3300_x3324")
    val x3336_b3460_x3350_b3465_vector = Vector("x3336_b3460_x3350_b3465")
    val x3284_argin = ArgIn("x3284")
    val x3147_b3430_x3166_b3439_scalar = Scalar("x3147_b3430_x3166_b3439")
    val x3170_x3176_scalar = Scalar("x3170_x3176")
    val x3145_x3310_x3325_vector = Vector("x3145_x3310_x3325")
    val x3070_b3411_x3076_b3414_scalar = Scalar("x3070_b3411_x3076_b3414")
    val x3050_oc = OffChip("x3050")
    val x3334_b3459_x3341_b3464_scalar = Scalar("x3334_b3459_x3341_b3464")
    val x3149_argin = ArgIn("x3149")
    val x3221_x3226_scalar = Scalar("x3221_x3226")
    val x3222_x3228_scalar = Scalar("x3222_x3228")
    val x3251_x3305_x3325_vector = Vector("x3251_x3305_x3325")
    val x3334_b3457_x3341_b3462_scalar = Scalar("x3334_b3457_x3341_b3462")
    val x3112_b3422_x3118_b3425_scalar = Scalar("x3112_b3422_x3118_b3425")
    val x3199_b3445_x3218_b3454_scalar = Scalar("x3199_b3445_x3218_b3454")
    val x3146_b3427_x3163_b3436_scalar = Scalar("x3146_b3427_x3163_b3436")
    val x3251_x3266_vector = Vector("x3251_x3266")
    val x3054_oc = OffChip("x3054")
    val x3041_argin = ArgIn("x3041")
    val x3137_x3142_scalar = Scalar("x3137_x3142")
    val x3052_oc = OffChip("x3052")
    val x3360 = Sequential(name="x3360",parent=top) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x3359 = Sequential(name="x3359",parent=x3360) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr1 = (Const("0i").out, x3041_x3060.load, Const("1i").out) // Counter
      val x3062 = CounterChain(name = "x3062", ctr1)
      val x3041_x3060 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x3041_argin).rdPort(None)
      var stage: List[Stage] = Nil
    }
    val x3358 = Sequential(name="x3358",parent=x3359) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr2 = (Const("0i").out, x3042_x3063.load, Const("768i").out) // Counter
      val x3065 = CounterChain(name = "x3065", ctr2)
      val x3042_x3063 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x3042_argin).rdPort(None)
      var stage: List[Stage] = Nil
    }
    val x3066_dsp0 = MemoryPipeline(name="x3066_dsp0",parent="x3358") { implicit CU => 
      val stage0 = CU.emptyStage
      val x3135 = CounterChain.copy("x3333", "x3135")
      val x3345 = CounterChain.copy("x3351", "x3345")
      val x3066_x3346 = SRAM(size = 768, banking = Strided(1)).wtPort(x3066_x3331_vector).rdPort(x3066_x3346_x3351_vector)
      var stage: List[Stage] = Nil
    }
    val x3066_dsp1 = MemoryPipeline(name="x3066_dsp1",parent="x3358") { implicit CU => 
      val stage0 = CU.emptyStage
      val x3135 = CounterChain.copy("x3333", "x3135")
      val x3345 = CounterChain.copy("x3351", "x3345")
      val x3066_x3307 = SRAM(size = 768, banking = Strided(1)).wtPort(x3066_x3331_vector).rdPort(x3066_x3346_x3351_vector)
      var stage: List[Stage] = Nil
    }
    val x3067_dsp0 = MemoryPipeline(name="x3067_dsp0",parent="x3358") { implicit CU => 
      val stage0 = CU.emptyStage
      val x3083 = CounterChain.copy("x3088", "x3083")
      val x3135 = CounterChain.copy("x3333", "x3135")
      val x3067_x3306 = SRAM(size = 768, banking = Strided(1)).wtPort(x3067_x3087_vector).rdPort(x3067_x3306_x3325_vector)
      var stage: List[Stage] = Nil
    }
    val x3068_dsp0 = MemoryPipeline(name="x3068_dsp0",parent="x3358") { implicit CU => 
      val stage0 = CU.emptyStage
      val x3104 = CounterChain.copy("x3109", "x3104")
      val x3135 = CounterChain.copy("x3333", "x3135")
      val x3068_x3138 = SRAM(size = 768, banking = Strided(1)).wtPort(x3068_x3108_vector).rdPort(x3068_x3138_x3140_vector)
      var stage: List[Stage] = Nil
    }
    val x3069_dsp0 = MemoryPipeline(name="x3069_dsp0",parent="x3358") { implicit CU => 
      val stage0 = CU.emptyStage
      val x3125 = CounterChain.copy("x3130", "x3125")
      val x3135 = CounterChain.copy("x3333", "x3135")
      val x3069_x3141 = SRAM(size = 768, banking = Strided(1)).wtPort(x3069_x3129_vector).rdPort(x3069_x3141_x3143_vector)
      var stage: List[Stage] = Nil
    }
    val x3090 = Sequential(name="x3090",parent=x3358) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x3078 = UnitPipeline(name="x3078",parent=x3090) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr330 = CU.temp
      val tr331 = CU.temp
      val tr332 = CU.temp
      val tr333 = CU.temp
      val tr334 = CU.temp
      val x3065 = CounterChain.copy("x3358", "x3065")
      val x3073 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x3073_argin).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(6)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x3065(0)), Const("4i")), op=FixMul, results=List(CU.temp(stage(1), tr330)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr330), CU.load(stage(1), x3073)), op=FixAdd, results=List(CU.temp(stage(2), tr331)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr332)), op=Bypass, results=List(CU.scalarOut(stage(3), x3070_b3409_x3076_b3412_scalar)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr333)), op=Bypass, results=List(CU.scalarOut(stage(4), x3070_b3410_x3076_b3413_scalar)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr334)), op=Bypass, results=List(CU.scalarOut(stage(5), x3070_b3411_x3076_b3414_scalar)))
      Stage(stage(6), operands=List(Const("768i")), op=Bypass, results=List(CU.scalarOut(stage(6), x3071_x3077_scalar)))
    }
    val x3079 = Fringe(name="x3079",parent=x3090,dram=x3048_oc, mode=TileLoad) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3070_b3411_x3079 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x3070_b3411_x3076_b3414_scalar).rdPort(None)
      val x3070_b3410_x3079 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x3070_b3410_x3076_b3413_scalar).rdPort(None)
      val x3070_b3409_x3079 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x3070_b3409_x3076_b3412_scalar).rdPort(None)
      var stage: List[Stage] = Nil
    }
    val x3089 = Sequential(name="x3089",parent=x3090) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x3081 = Sequential(name="x3081",parent=x3089) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x3088 = Pipeline(name="x3088",parent=x3089) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr3 = (Const("0i").out, Const("768i").out, Const("1i").out) // Counter
      val x3083 = CounterChain(name = "x3083", ctr3)
      val x3072_x3084 = VectorFIFO(size = 1, banking = Strided(1)).wtPort(None).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x3072_x3084.load), op=Bypass, results=List(CU.vecOut(stage(1), x3067_x3087_vector)))
    }
    val x3111 = Sequential(name="x3111",parent=x3358) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x3099 = UnitPipeline(name="x3099",parent=x3111) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr337 = CU.temp
      val tr338 = CU.temp
      val tr339 = CU.temp
      val tr340 = CU.temp
      val tr341 = CU.temp
      val x3065 = CounterChain.copy("x3358", "x3065")
      val x3094 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x3094_argin).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(6)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x3065(0)), Const("4i")), op=FixMul, results=List(CU.temp(stage(1), tr337)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr337), CU.load(stage(1), x3094)), op=FixAdd, results=List(CU.temp(stage(2), tr338)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr339)), op=Bypass, results=List(CU.scalarOut(stage(3), x3091_b3415_x3097_b3418_scalar)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr340)), op=Bypass, results=List(CU.scalarOut(stage(4), x3091_b3416_x3097_b3419_scalar)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr341)), op=Bypass, results=List(CU.scalarOut(stage(5), x3091_b3417_x3097_b3420_scalar)))
      Stage(stage(6), operands=List(Const("768i")), op=Bypass, results=List(CU.scalarOut(stage(6), x3092_x3098_scalar)))
    }
    val x3100 = Fringe(name="x3100",parent=x3111,dram=x3052_oc, mode=TileLoad) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3091_b3417_x3100 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x3091_b3417_x3097_b3420_scalar).rdPort(None)
      val x3091_b3416_x3100 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x3091_b3416_x3097_b3419_scalar).rdPort(None)
      val x3091_b3415_x3100 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x3091_b3415_x3097_b3418_scalar).rdPort(None)
      var stage: List[Stage] = Nil
    }
    val x3110 = Sequential(name="x3110",parent=x3111) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x3102 = Sequential(name="x3102",parent=x3110) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x3109 = Pipeline(name="x3109",parent=x3110) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr4 = (Const("0i").out, Const("768i").out, Const("1i").out) // Counter
      val x3104 = CounterChain(name = "x3104", ctr4)
      val x3093_x3105 = VectorFIFO(size = 1, banking = Strided(1)).wtPort(None).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x3093_x3105.load), op=Bypass, results=List(CU.vecOut(stage(1), x3068_x3108_vector)))
    }
    val x3132 = Sequential(name="x3132",parent=x3358) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x3120 = UnitPipeline(name="x3120",parent=x3132) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr344 = CU.temp
      val tr345 = CU.temp
      val tr346 = CU.temp
      val tr347 = CU.temp
      val tr348 = CU.temp
      val x3065 = CounterChain.copy("x3358", "x3065")
      val x3115 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x3115_argin).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(6)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x3065(0)), Const("4i")), op=FixMul, results=List(CU.temp(stage(1), tr344)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr344), CU.load(stage(1), x3115)), op=FixAdd, results=List(CU.temp(stage(2), tr345)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr346)), op=Bypass, results=List(CU.scalarOut(stage(3), x3112_b3421_x3118_b3424_scalar)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr347)), op=Bypass, results=List(CU.scalarOut(stage(4), x3112_b3422_x3118_b3425_scalar)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr348)), op=Bypass, results=List(CU.scalarOut(stage(5), x3112_b3423_x3118_b3426_scalar)))
      Stage(stage(6), operands=List(Const("768i")), op=Bypass, results=List(CU.scalarOut(stage(6), x3113_x3119_scalar)))
    }
    val x3121 = Fringe(name="x3121",parent=x3132,dram=x3054_oc, mode=TileLoad) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3112_b3421_x3121 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x3112_b3421_x3118_b3424_scalar).rdPort(None)
      val x3112_b3423_x3121 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x3112_b3423_x3118_b3426_scalar).rdPort(None)
      val x3112_b3422_x3121 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x3112_b3422_x3118_b3425_scalar).rdPort(None)
      var stage: List[Stage] = Nil
    }
    val x3131 = Sequential(name="x3131",parent=x3132) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x3123 = Sequential(name="x3123",parent=x3131) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x3130 = Pipeline(name="x3130",parent=x3131) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr5 = (Const("0i").out, Const("768i").out, Const("1i").out) // Counter
      val x3125 = CounterChain(name = "x3125", ctr5)
      val x3114_x3126 = VectorFIFO(size = 1, banking = Strided(1)).wtPort(None).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x3114_x3126.load), op=Bypass, results=List(CU.vecOut(stage(1), x3069_x3129_vector)))
    }
    val x3333 = Sequential(name="x3333",parent=x3358) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr6 = (Const("0i").out, Const("768i").out, Const("1i").out) // Counter
      val x3135 = CounterChain(name = "x3135", ctr6)
      var stage: List[Stage] = Nil
    }
    val x3140 = UnitPipeline(name="x3140",parent=x3333) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3135 = CounterChain.copy("x3333", "x3135")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x3068_x3138_x3140_vector)), op=Bypass, results=List(CU.scalarOut(stage(1), x3136_x3139_scalar)))
    }
    val x3143 = UnitPipeline(name="x3143",parent=x3333) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3135 = CounterChain.copy("x3333", "x3135")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x3069_x3141_x3143_vector)), op=Bypass, results=List(CU.scalarOut(stage(1), x3137_x3142_scalar)))
    }
    val x3144_dsp0 = MemoryPipeline(name="x3144_dsp0",parent="x3333") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr350 = CU.temp
      val x3065 = CounterChain.copy("x3358", "x3065")
      val x3272 = CounterChain.copy("x3277", "x3272")
      val x3303 = CounterChain.copy("x3325", "x3303")
      val x3135 = CounterChain.copy("x3333", "x3135")
      val x3182 = CounterChain.copy("x3195", "x3182")
      val x3255 = CounterChain.copy("x3268", "x3255")
      val x3169_x3183 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x3169_x3174_scalar).rdPort(None)
      val x3144_x3304 = SRAM(size = 768, banking = Strided(1)).wtPort(x3144_x3194_vector).rdPort(x3144_x3304_x3325_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x3144_x3304))
      Stage(stage(1), operands=List(x3182(0), x3169_x3183.load), op=FixSub, results=List(x3144_x3304.writeAddr, CU.temp(stage(1), tr350)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x3182(0)), CU.load(stage(1), x3169_x3183)), op=FixSub, results=List(CU.temp(stage(2), tr350)))
    }
    val x3144_dsp1 = MemoryPipeline(name="x3144_dsp1",parent="x3333") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr351 = CU.temp
      val x3065 = CounterChain.copy("x3358", "x3065")
      val x3272 = CounterChain.copy("x3277", "x3272")
      val x3303 = CounterChain.copy("x3325", "x3303")
      val x3135 = CounterChain.copy("x3333", "x3135")
      val x3182 = CounterChain.copy("x3195", "x3182")
      val x3255 = CounterChain.copy("x3268", "x3255")
      val x3169_x3183 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x3169_x3174_scalar).rdPort(None)
      val x3144_x3274 = SRAM(size = 768, banking = Strided(1)).wtPort(x3144_x3194_vector).rdPort(x3144_x3304_x3325_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x3144_x3274))
      Stage(stage(1), operands=List(x3182(0), x3169_x3183.load), op=FixSub, results=List(x3144_x3274.writeAddr, CU.temp(stage(1), tr351)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x3182(0)), CU.load(stage(1), x3169_x3183)), op=FixSub, results=List(CU.temp(stage(2), tr351)))
    }
    val x3144_dsp2 = MemoryPipeline(name="x3144_dsp2",parent="x3333") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr352 = CU.temp
      val x3065 = CounterChain.copy("x3358", "x3065")
      val x3272 = CounterChain.copy("x3277", "x3272")
      val x3303 = CounterChain.copy("x3325", "x3303")
      val x3135 = CounterChain.copy("x3333", "x3135")
      val x3182 = CounterChain.copy("x3195", "x3182")
      val x3255 = CounterChain.copy("x3268", "x3255")
      val x3169_x3183 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x3169_x3174_scalar).rdPort(None)
      val x3144_x3256 = SRAM(size = 768, banking = Strided(1)).wtPort(x3144_x3194_vector).rdPort(x3144_x3304_x3325_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x3144_x3256))
      Stage(stage(1), operands=List(x3182(0), x3169_x3183.load), op=FixSub, results=List(x3144_x3256.writeAddr, CU.temp(stage(1), tr352)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x3182(0)), CU.load(stage(1), x3169_x3183)), op=FixSub, results=List(CU.temp(stage(2), tr352)))
    }
    val x3145_dsp0 = MemoryPipeline(name="x3145_dsp0",parent="x3333") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr353 = CU.temp
      val x3234 = CounterChain.copy("x3247", "x3234")
      val x3135 = CounterChain.copy("x3333", "x3135")
      val x3065 = CounterChain.copy("x3358", "x3065")
      val x3303 = CounterChain.copy("x3325", "x3303")
      val x3145_x3310 = SRAM(size = 768, banking = Strided(1)).wtPort(x3145_x3246_vector).rdPort(x3145_x3310_x3325_vector)
      val x3221_x3235 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x3221_x3226_scalar).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x3145_x3310))
      Stage(stage(1), operands=List(x3234(0), x3221_x3235.load), op=FixSub, results=List(x3145_x3310.writeAddr, CU.temp(stage(1), tr353)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x3234(0)), CU.load(stage(1), x3221_x3235)), op=FixSub, results=List(CU.temp(stage(2), tr353)))
    }
    val x3197 = Sequential(name="x3197",parent=x3333) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x3167 = UnitPipeline(name="x3167",parent=x3197) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr355 = CU.temp
      val tr357 = CU.temp
      val tr358 = CU.temp
      val tr359 = CU.temp
      val tr361 = CU.temp
      val tr362 = CU.temp
      val tr363 = CU.temp
      val tr364 = CU.temp
      val tr365 = CU.temp
      val tr366 = CU.temp
      val tr367 = CU.temp
      val tr368 = CU.temp
      val tr369 = CU.temp
      val tr370 = CU.temp
      val tr371 = CU.temp
      val tr372 = CU.temp
      val tr373 = CU.temp
      val x3136_x3150 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x3136_x3139_scalar).rdPort(None)
      val x3137_x3151 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x3137_x3142_scalar).rdPort(None)
      val x3149 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x3149_argin).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(17)
      Stage(stage(1), operands=List(x3136_x3150.load, Const("4i")), op=FixMul, results=List(CU.temp(stage(1), tr355)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr355), Const("16i")), op=FixMod, results=List(CU.temp(stage(2), tr357)))
      Stage(stage(3), operands=List(CU.load(stage(2), x3137_x3151), Const("16i")), op=FixMod, results=List(CU.temp(stage(3), tr358)))
      Stage(stage(4), operands=List(Const("16i"), CU.temp(stage(3), tr358)), op=FixSub, results=List(CU.temp(stage(4), tr359)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr358), Const("0i")), op=FixEql, results=List(CU.temp(stage(5), tr361)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr361), Const("0i"), CU.temp(stage(5), tr359)), op=Mux, results=List(CU.temp(stage(6), tr362)))
      Stage(stage(7), operands=List(CU.load(stage(6), x3137_x3151), CU.temp(stage(6), tr362)), op=FixAdd, results=List(CU.temp(stage(7), tr363)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr357), CU.load(stage(7), x3137_x3151)), op=FixAdd, results=List(CU.temp(stage(8), tr364)))
      Stage(stage(9), operands=List(CU.temp(stage(8), tr355), CU.load(stage(8), x3149)), op=FixAdd, results=List(CU.temp(stage(9), tr368)))
      Stage(stage(10), operands=List(CU.temp(stage(9), tr368), CU.temp(stage(9), tr366)), op=FixSub, results=List(CU.temp(stage(10), tr369)))
      Stage(stage(11), operands=List(CU.temp(stage(10), tr365), Const("4i")), op=FixMul, results=List(CU.temp(stage(11), tr370)))
      Stage(stage(12), operands=List(CU.temp(stage(11), tr371)), op=Bypass, results=List(CU.scalarOut(stage(12), x3146_b3427_x3163_b3436_scalar)))
      Stage(stage(13), operands=List(CU.temp(stage(12), tr372)), op=Bypass, results=List(CU.scalarOut(stage(13), x3146_b3428_x3163_b3437_scalar)))
      Stage(stage(14), operands=List(CU.temp(stage(13), tr373)), op=Bypass, results=List(CU.scalarOut(stage(14), x3146_b3429_x3163_b3438_scalar)))
      Stage(stage(15), operands=List(CU.temp(stage(14), tr365)), op=Bypass, results=List(CU.scalarOut(stage(15), x3147_b3430_x3166_b3439_scalar)))
      Stage(stage(16), operands=List(CU.temp(stage(15), tr366)), op=Bypass, results=List(CU.scalarOut(stage(16), x3147_b3431_x3166_b3440_scalar)))
      Stage(stage(17), operands=List(CU.temp(stage(16), tr367)), op=Bypass, results=List(CU.scalarOut(stage(17), x3147_b3432_x3166_b3441_scalar)))
    }
    val x3168 = Fringe(name="x3168",parent=x3197,dram=x3049_oc, mode=TileLoad) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3146_b3429_x3168 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x3146_b3429_x3163_b3438_scalar).rdPort(None)
      val x3146_b3428_x3168 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x3146_b3428_x3163_b3437_scalar).rdPort(None)
      val x3146_b3427_x3168 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x3146_b3427_x3163_b3436_scalar).rdPort(None)
      var stage: List[Stage] = Nil
    }
    val x3196 = Sequential(name="x3196",parent=x3197) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x3179 = UnitPipeline(name="x3179",parent=x3196) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr374 = CU.temp
      val tr375 = CU.temp
      val tr376 = CU.temp
      val x3147_b3430_x3172_b3433 = ScalarFIFO(size = 16, banking = Strided(1)).wtPort(x3147_b3430_x3166_b3439_scalar).rdPort(None)
      val x3147_b3432_x3172_b3435 = ScalarFIFO(size = 16, banking = Strided(1)).wtPort(x3147_b3432_x3166_b3441_scalar).rdPort(None)
      val x3147_b3431_x3172_b3434 = ScalarFIFO(size = 16, banking = Strided(1)).wtPort(x3147_b3431_x3166_b3440_scalar).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(x3147_b3431_x3172_b3434.load), op=Bypass, results=List(CU.scalarOut(stage(1), x3169_x3174_scalar)))
      Stage(stage(2), operands=List(x3147_b3432_x3172_b3435.load), op=Bypass, results=List(CU.scalarOut(stage(2), x3170_x3176_scalar)))
      Stage(stage(3), operands=List(x3147_b3430_x3172_b3433.load), op=Bypass, results=List(CU.scalarOut(stage(3), x3171_x3178_scalar)))
    }
    val x3195 = Pipeline(name="x3195",parent=x3196) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr7 = (Const("0i").out, x3171_x3180.load, Const("1i").out) // Counter
      val x3182 = CounterChain(name = "x3182", ctr7)
      val x3148_x3185 = VectorFIFO(size = 1, banking = Strided(1)).wtPort(None).rdPort(None)
      val x3169_x3183 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x3169_x3174_scalar).rdPort(None)
      val x3171_x3180 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x3171_x3178_scalar).rdPort(None)
      val x3170_x3184 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x3170_x3176_scalar).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x3148_x3185.load), op=Bypass, results=List(CU.vecOut(stage(1), x3144_x3194_vector)))
    }
    val x3249 = Sequential(name="x3249",parent=x3333) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x3219 = UnitPipeline(name="x3219",parent=x3249) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr378 = CU.temp
      val tr380 = CU.temp
      val tr381 = CU.temp
      val tr382 = CU.temp
      val tr384 = CU.temp
      val tr385 = CU.temp
      val tr386 = CU.temp
      val tr387 = CU.temp
      val tr388 = CU.temp
      val tr389 = CU.temp
      val tr390 = CU.temp
      val tr391 = CU.temp
      val tr392 = CU.temp
      val tr393 = CU.temp
      val tr394 = CU.temp
      val tr395 = CU.temp
      val tr396 = CU.temp
      val x3136_x3202 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x3136_x3139_scalar).rdPort(None)
      val x3201 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x3201_argin).rdPort(None)
      val x3137_x3203 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x3137_x3142_scalar).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(17)
      Stage(stage(1), operands=List(x3136_x3202.load, Const("4i")), op=FixMul, results=List(CU.temp(stage(1), tr378)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr378), Const("16i")), op=FixMod, results=List(CU.temp(stage(2), tr380)))
      Stage(stage(3), operands=List(CU.load(stage(2), x3137_x3203), Const("16i")), op=FixMod, results=List(CU.temp(stage(3), tr381)))
      Stage(stage(4), operands=List(Const("16i"), CU.temp(stage(3), tr381)), op=FixSub, results=List(CU.temp(stage(4), tr382)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr381), Const("0i")), op=FixEql, results=List(CU.temp(stage(5), tr384)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr384), Const("0i"), CU.temp(stage(5), tr382)), op=Mux, results=List(CU.temp(stage(6), tr385)))
      Stage(stage(7), operands=List(CU.load(stage(6), x3137_x3203), CU.temp(stage(6), tr385)), op=FixAdd, results=List(CU.temp(stage(7), tr386)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr380), CU.load(stage(7), x3137_x3203)), op=FixAdd, results=List(CU.temp(stage(8), tr387)))
      Stage(stage(9), operands=List(CU.temp(stage(8), tr378), CU.load(stage(8), x3201)), op=FixAdd, results=List(CU.temp(stage(9), tr391)))
      Stage(stage(10), operands=List(CU.temp(stage(9), tr391), CU.temp(stage(9), tr389)), op=FixSub, results=List(CU.temp(stage(10), tr392)))
      Stage(stage(11), operands=List(CU.temp(stage(10), tr388), Const("4i")), op=FixMul, results=List(CU.temp(stage(11), tr393)))
      Stage(stage(12), operands=List(CU.temp(stage(11), tr394)), op=Bypass, results=List(CU.scalarOut(stage(12), x3198_b3442_x3215_b3451_scalar)))
      Stage(stage(13), operands=List(CU.temp(stage(12), tr395)), op=Bypass, results=List(CU.scalarOut(stage(13), x3198_b3443_x3215_b3452_scalar)))
      Stage(stage(14), operands=List(CU.temp(stage(13), tr396)), op=Bypass, results=List(CU.scalarOut(stage(14), x3198_b3444_x3215_b3453_scalar)))
      Stage(stage(15), operands=List(CU.temp(stage(14), tr388)), op=Bypass, results=List(CU.scalarOut(stage(15), x3199_b3445_x3218_b3454_scalar)))
      Stage(stage(16), operands=List(CU.temp(stage(15), tr389)), op=Bypass, results=List(CU.scalarOut(stage(16), x3199_b3446_x3218_b3455_scalar)))
      Stage(stage(17), operands=List(CU.temp(stage(16), tr390)), op=Bypass, results=List(CU.scalarOut(stage(17), x3199_b3447_x3218_b3456_scalar)))
    }
    val x3220 = Fringe(name="x3220",parent=x3249,dram=x3050_oc, mode=TileLoad) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3198_b3444_x3220 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x3198_b3444_x3215_b3453_scalar).rdPort(None)
      val x3198_b3443_x3220 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x3198_b3443_x3215_b3452_scalar).rdPort(None)
      val x3198_b3442_x3220 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x3198_b3442_x3215_b3451_scalar).rdPort(None)
      var stage: List[Stage] = Nil
    }
    val x3248 = Sequential(name="x3248",parent=x3249) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x3231 = UnitPipeline(name="x3231",parent=x3248) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr397 = CU.temp
      val tr398 = CU.temp
      val tr399 = CU.temp
      val x3199_b3447_x3224_b3450 = ScalarFIFO(size = 16, banking = Strided(1)).wtPort(x3199_b3447_x3218_b3456_scalar).rdPort(None)
      val x3199_b3446_x3224_b3449 = ScalarFIFO(size = 16, banking = Strided(1)).wtPort(x3199_b3446_x3218_b3455_scalar).rdPort(None)
      val x3199_b3445_x3224_b3448 = ScalarFIFO(size = 16, banking = Strided(1)).wtPort(x3199_b3445_x3218_b3454_scalar).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(x3199_b3446_x3224_b3449.load), op=Bypass, results=List(CU.scalarOut(stage(1), x3221_x3226_scalar)))
      Stage(stage(2), operands=List(x3199_b3447_x3224_b3450.load), op=Bypass, results=List(CU.scalarOut(stage(2), x3222_x3228_scalar)))
      Stage(stage(3), operands=List(x3199_b3445_x3224_b3448.load), op=Bypass, results=List(CU.scalarOut(stage(3), x3223_x3230_scalar)))
    }
    val x3247 = Pipeline(name="x3247",parent=x3248) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr8 = (Const("0i").out, x3223_x3232.load, Const("1i").out) // Counter
      val x3234 = CounterChain(name = "x3234", ctr8)
      val x3223_x3232 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x3223_x3230_scalar).rdPort(None)
      val x3222_x3236 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x3222_x3228_scalar).rdPort(None)
      val x3221_x3235 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x3221_x3226_scalar).rdPort(None)
      val x3200_x3237 = VectorFIFO(size = 1, banking = Strided(1)).wtPort(None).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x3200_x3237.load), op=Bypass, results=List(CU.vecOut(stage(1), x3145_x3246_vector)))
    }
    val x3251_dsp0 = MemoryPipeline(name="x3251_dsp0",parent="x3333") { implicit CU => 
      val stage0 = CU.emptyStage
      val x3065 = CounterChain.copy("x3358", "x3065")
      val x3272 = CounterChain.copy("x3277", "x3272")
      val x3303 = CounterChain.copy("x3325", "x3303")
      val x3135 = CounterChain.copy("x3333", "x3135")
      val x3255 = CounterChain.copy("x3268", "x3255")
      val x3251_x3305 = SRAM(size = 768, banking = Strided(1)).wtPort(x3251_x3266_vector).rdPort(x3251_x3305_x3278_vector)
      var stage: List[Stage] = Nil
    }
    val x3251_dsp1 = MemoryPipeline(name="x3251_dsp1",parent="x3333") { implicit CU => 
      val stage0 = CU.emptyStage
      val x3065 = CounterChain.copy("x3358", "x3065")
      val x3272 = CounterChain.copy("x3277", "x3272")
      val x3303 = CounterChain.copy("x3325", "x3303")
      val x3135 = CounterChain.copy("x3333", "x3135")
      val x3255 = CounterChain.copy("x3268", "x3255")
      val x3251_x3273 = SRAM(size = 768, banking = Strided(1)).wtPort(x3251_x3266_vector).rdPort(x3251_x3305_x3278_vector)
      var stage: List[Stage] = Nil
    }
    val x3268 = Sequential(name="x3268",parent=x3333) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr9 = (Const("0i").out, x3137_x3253.load, Const("1i").out) // Counter
      val x3255 = CounterChain(name = "x3255", ctr9)
      val x3137_x3253 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x3137_x3142_scalar).rdPort(None)
      var stage: List[Stage] = Nil
    }
    val x3267 = UnitPipeline(name="x3267",parent=x3268) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr400 = CU.temp
      val tr402 = CU.temp
      val tr403 = CU.temp
      val tr404 = CU.temp
      val tr406 = CU.temp
      val tr409 = CU.temp
      val tr410 = CU.temp
      val ar146 = CU.accum(init = Const("-1i"))
      val x3255 = CounterChain.copy("x3268", "x3255")
      val x3065 = CounterChain.copy("x3358", "x3065")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(7)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x3065(0)), CU.vecIn(stage(0), x3144_x3256_x3267_vector)), op=FixLeq, results=List(CU.temp(stage(1), tr400)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x3065(0)), Const("768i")), op=FixAdd, results=List(CU.temp(stage(2), tr402)))
      Stage(stage(3), operands=List(CU.vecIn(stage(2), x3144_x3256_x3267_vector), CU.temp(stage(2), tr402)), op=FixLt, results=List(CU.temp(stage(3), tr403)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr400), CU.temp(stage(3), tr403)), op=BitAnd, results=List(CU.temp(stage(4), tr404)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr404), CU.accum(stage(5), ar146), Const("-1i")), op=Mux, results=List(CU.vecOut(stage(5), x3251_x3266_vector), CU.temp(stage(5), tr406)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr404), Const("0i"), Const("1i")), op=Mux, results=List(CU.temp(stage(6), tr409)))
      Stage(stage(7), operands=List(CU.accum(stage(6), ar146), CU.temp(stage(6), tr409)), op=FixAdd, results=List(CU.temp(stage(7), tr410)))
    }
    val x3269_dsp0 = MemoryPipeline(name="x3269_dsp0",parent="x3333") { implicit CU => 
      val stage0 = CU.emptyStage
      val x3272 = CounterChain.copy("x3277", "x3272")
      val x3282 = CounterChain.copy("x3290", "x3282")
      val x3269_x3283 = SRAM(size = 768, banking = Strided(1)).wtPort(x3269_x3275_vector).rdPort(x3269_x3283_x3290_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(1, List(x3269_x3283))
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x3251_x3273_x3269_vector)), op=Bypass, results=List(x3269_x3283.writeAddr))
    }
    val x3277 = Sequential(name="x3277",parent=x3333) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr10 = (Const("0i").out, x3137_x3270.load, Const("1i").out) // Counter
      val x3272 = CounterChain(name = "x3272", ctr10)
      val x3137_x3270 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x3137_x3142_scalar).rdPort(None)
      var stage: List[Stage] = Nil
    }
    val x3276 = UnitPipeline(name="x3276",parent=x3277) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3272 = CounterChain.copy("x3277", "x3272")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x3144_x3274_x3276_vector)), op=Bypass, results=List(CU.vecOut(stage(1), x3269_x3275_vector)))
    }
    val x3278_dsp0 = MemoryPipeline(name="x3278_dsp0",parent="x3333") { implicit CU => 
      val stage0 = CU.emptyStage
      val x3293 = CounterChain.copy("x3298", "x3293")
      val x3135 = CounterChain.copy("x3333", "x3135")
      val x3065 = CounterChain.copy("x3358", "x3065")
      val x3303 = CounterChain.copy("x3325", "x3303")
      val x3278_x3309 = SRAM(size = 768, banking = Duplicated()).wtPort(x3278_x3297_vector).rdPort(x3278_x3309_x3325_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: RAStages(1, List(x3278_x3309))
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x3251_x3305_x3278_vector)), op=Bypass, results=List(x3278_x3309.readAddr))
    }
    val x3299 = Sequential(name="x3299",parent=x3333) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x3290 = Pipeline(name="x3290",parent=x3299) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr412 = CU.temp
      val tr413 = CU.temp
      val ctr11 = (Const("0i").out, Const("768i").out, Const("1i").out) // Counter
      val x3282 = CounterChain(name = "x3282", ctr11)
      val x3284 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x3284_argin).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x3269_x3283_x3290_vector), Const("4i")), op=FixMul, results=List(CU.temp(stage(1), tr412)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr412), CU.load(stage(1), x3284)), op=FixAdd, results=List(CU.vecOut(stage(2), x3279_x3289_vector), CU.temp(stage(2), tr413)))
    }
    val x3291 = Fringe(name="x3291",parent=x3299,dram=x3048_oc, mode=Gather) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3279_x3291 = VectorFIFO(size = 1, banking = Strided(1)).wtPort(x3279_x3289_vector).rdPort(None)
      var stage: List[Stage] = Nil
    }
    val x3298 = Pipeline(name="x3298",parent=x3299) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr12 = (Const("0i").out, Const("768i").out, Const("1i").out) // Counter
      val x3293 = CounterChain(name = "x3293", ctr12)
      val x3280_x3294 = VectorFIFO(size = 1, banking = Strided(1)).wtPort(None).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x3280_x3294.load), op=Bypass, results=List(CU.vecOut(stage(1), x3278_x3297_vector)))
    }
    val x3325 = Pipeline(name="x3325",parent=x3333) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr414 = CU.temp
      val tr415 = CU.temp
      val tr416 = CU.temp
      val tr418 = CU.temp
      val tr419 = CU.temp
      val tr420 = CU.temp
      val ar223 = CU.accum(init = Const("0i"))
      val x3135 = CounterChain.copy("x3333", "x3135")
      val x3065 = CounterChain.copy("x3358", "x3065")
      val ctr13 = (Const("0i").out, x3137_x3301.load, Const("1i").out) // Counter
      val x3303 = CounterChain(name = "x3303", ctr13)
      val x3137_x3301 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x3137_x3142_scalar).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(7)
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x3144_x3304_x3325_vector), CU.ctr(stage(0), x3065(0))), op=FixSub, results=List(CU.temp(stage(1), tr414)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr414), CU.ctr(stage(1), x3135(0))), op=FixLeq, results=List(CU.temp(stage(2), tr415)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr415), CU.vecIn(stage(2), x3067_x3306_x3325_vector), CU.vecIn(stage(2), x3066_x3307_x3325_vector)), op=Mux, results=List(CU.temp(stage(3), tr416)))
      Stage(stage(4), operands=List(CU.vecIn(stage(3), x3251_x3305_x3325_vector), Const("-1i")), op=FixEql, results=List(CU.temp(stage(4), tr418)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr418), CU.temp(stage(4), tr416), CU.vecIn(stage(4), x3278_x3309_x3325_vector)), op=Mux, results=List(CU.temp(stage(5), tr419)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr419), CU.vecIn(stage(5), x3145_x3310_x3325_vector)), op=FltDiv, results=List(CU.reduce(stage(6)), CU.temp(stage(6), tr420)))
      val (rs1, rr423) = Stage.reduce(op=FltAdd, init=Const("0i"))
      Stage(stage(7), operands=List(rr423), op=Bypass, results=List(CU.vecOut(stage(7), x3300_x3324_vector)))
    }
    val x3332 = UnitPipeline(name="x3332",parent=x3333) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr424 = CU.temp
      val tr426 = CU.temp
      val tr427 = CU.temp
      val x3135 = CounterChain.copy("x3333", "x3135")
      val x3300_x3327 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x3300_x3324_vector).rdPort(None)
      val x3043_x3326 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x3043_argin).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(x3300_x3327.load, x3043_x3326.load), op=FltMul, results=List(CU.temp(stage(1), tr424)))
      Stage(stage(2), operands=List(Const("1i"), CU.load(stage(1), x3043_x3326)), op=FltSub, results=List(CU.temp(stage(2), tr426)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr424), CU.temp(stage(2), tr426)), op=FltAdd, results=List(CU.vecOut(stage(3), x3066_x3331_vector), CU.temp(stage(3), tr427)))
    }
    val x3357 = Sequential(name="x3357",parent=x3358) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x3352 = Sequential(name="x3352",parent=x3357) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x3343 = UnitPipeline(name="x3343",parent=x3352) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr429 = CU.temp
      val tr430 = CU.temp
      val tr431 = CU.temp
      val tr432 = CU.temp
      val tr433 = CU.temp
      val x3065 = CounterChain.copy("x3358", "x3065")
      val x3338 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x3338_argin).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(6)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x3065(0)), Const("4i")), op=FixMul, results=List(CU.temp(stage(1), tr429)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr429), CU.load(stage(1), x3338)), op=FixAdd, results=List(CU.temp(stage(2), tr430)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr431)), op=Bypass, results=List(CU.scalarOut(stage(3), x3334_b3457_x3341_b3462_scalar)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr432)), op=Bypass, results=List(CU.scalarOut(stage(4), x3334_b3458_x3341_b3463_scalar)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr433)), op=Bypass, results=List(CU.scalarOut(stage(5), x3334_b3459_x3341_b3464_scalar)))
      Stage(stage(6), operands=List(Const("768i")), op=Bypass, results=List(CU.scalarOut(stage(6), x3335_x3342_scalar)))
    }
    val x3351 = Pipeline(name="x3351",parent=x3352) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr435 = CU.temp
      val tr436 = CU.temp
      val ctr14 = (Const("0i").out, Const("768i").out, Const("1i").out) // Counter
      val x3345 = CounterChain(name = "x3345", ctr14)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.temp(stage(0), tr435)), op=Bypass, results=List(CU.vecOut(stage(1), x3336_b3460_x3350_b3465_vector)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr436)), op=Bypass, results=List(CU.vecOut(stage(2), x3336_b3461_x3350_b3466_vector)))
    }
    val x3353 = Fringe(name="x3353",parent=x3357,dram=x3048_oc, mode=TileStore) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3336_b3461_x3353 = VectorFIFO(size = 1, banking = Strided(1)).wtPort(x3336_b3461_x3350_b3466_vector).rdPort(None)
      val x3334_b3458_x3353 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x3334_b3458_x3341_b3463_scalar).rdPort(None)
      val x3336_b3460_x3353 = VectorFIFO(size = 1, banking = Strided(1)).wtPort(x3336_b3460_x3350_b3465_vector).rdPort(None)
      val x3334_b3457_x3353 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x3334_b3457_x3341_b3462_scalar).rdPort(None)
      val x3334_b3459_x3353 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x3334_b3459_x3341_b3464_scalar).rdPort(None)
      var stage: List[Stage] = Nil
    }
    val x3356 = Sequential(name="x3356",parent=x3357) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    
  }
}
