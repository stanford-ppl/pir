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
    val x3072_x3079_data_v = Vector("x3072_x3079_data")
    val x3073_argin = ArgIn("x3073")
    val x3171_x3178_s = Scalar("x3171_x3178")
    val x3049_oc = OffChip("x3049")
    val x3144_x3194_v = Vector("x3144_x3194")
    val x3201_argin = ArgIn("x3201")
    val x3048_oc = OffChip("x3048")
    val x3042_argin = ArgIn("x3042")
    val x3067_x3087_v = Vector("x3067_x3087")
    val x3144_x3274_v = Vector("x3144_x3274")
    val x3114_x3121_data_v = Vector("x3114_x3121_data")
    val x3199_b3437_x3218_b3445_s = Scalar("x3199_b3437_x3218_b3445")
    val x3069_x3129_v = Vector("x3069_x3129")
    val x3269_x3283_v = Vector("x3269_x3283")
    val x3144_x3256_v = Vector("x3144_x3256")
    val x3071_x3077_s = Scalar("x3071_x3077")
    val x3145_x3246_v = Vector("x3145_x3246")
    val x3112_b3418_x3118_b3420_s = Scalar("x3112_b3418_x3118_b3420")
    val x3334_b3447_x3341_b3449_s = Scalar("x3334_b3447_x3341_b3449")
    val x3199_b3436_x3218_b3444_s = Scalar("x3199_b3436_x3218_b3444")
    val x3199_b3438_x3218_b3446_s = Scalar("x3199_b3438_x3218_b3446")
    val x3335_x3342_s = Scalar("x3335_x3342")
    val x3091_b3413_x3097_b3415_s = Scalar("x3091_b3413_x3097_b3415")
    val x3068_x3108_v = Vector("x3068_x3108")
    val x3066_x3346_v = Vector("x3066_x3346")
    val x3091_b3414_x3097_b3416_s = Scalar("x3091_b3414_x3097_b3416")
    val x3148_x3168_data_v = Vector("x3148_x3168_data")
    val x3147_b3424_x3166_b3432_s = Scalar("x3147_b3424_x3166_b3432")
    val x3145_x3310_v = Vector("x3145_x3310")
    val x3338_argin = ArgIn("x3338")
    val x3068_x3138_v = Vector("x3068_x3138")
    val x3112_b3417_x3118_b3419_s = Scalar("x3112_b3417_x3118_b3419")
    val x3146_b3422_x3163_b3430_s = Scalar("x3146_b3422_x3163_b3430")
    val x3278_x3297_v = Vector("x3278_x3297")
    val x3067_x3306_v = Vector("x3067_x3306")
    val x3070_b3410_x3076_b3412_s = Scalar("x3070_b3410_x3076_b3412")
    val x3336_x3350_s = Scalar("x3336_x3350")
    val x3070_b3409_x3076_b3411_s = Scalar("x3070_b3409_x3076_b3411")
    val x3147_b3425_x3166_b3433_s = Scalar("x3147_b3425_x3166_b3433")
    val x3169_x3174_s = Scalar("x3169_x3174")
    val x3043_argin = ArgIn("x3043")
    val x3094_argin = ArgIn("x3094")
    val x3113_x3119_s = Scalar("x3113_x3119")
    val x3066_x3331_v = Vector("x3066_x3331")
    val x3198_b3434_x3215_b3442_s = Scalar("x3198_b3434_x3215_b3442")
    val x3334_b3448_x3341_b3450_s = Scalar("x3334_b3448_x3341_b3450")
    val x3092_x3098_s = Scalar("x3092_x3098")
    val x3115_argin = ArgIn("x3115")
    val x3279_x3289_s = Scalar("x3279_x3289")
    val x3251_x3305_v = Vector("x3251_x3305")
    val x3200_x3220_data_v = Vector("x3200_x3220_data")
    val x3251_x3273_v = Vector("x3251_x3273")
    val x3269_x3275_v = Vector("x3269_x3275")
    val x3223_x3230_s = Scalar("x3223_x3230")
    val x3144_x3304_v = Vector("x3144_x3304")
    val x3136_x3139_s = Scalar("x3136_x3139")
    val x3278_x3309_v = Vector("x3278_x3309")
    val x3146_b3421_x3163_b3429_s = Scalar("x3146_b3421_x3163_b3429")
    val x3300_x3324_s = Scalar("x3300_x3324")
    val x3284_argin = ArgIn("x3284")
    val x3280_x3291_data_v = Vector("x3280_x3291_data")
    val x3170_x3176_s = Scalar("x3170_x3176")
    val x3337_x3353_ack_v = Vector("x3337_x3353_ack")
    val x3069_x3141_v = Vector("x3069_x3141")
    val x3050_oc = OffChip("x3050")
    val x3149_argin = ArgIn("x3149")
    val x3221_x3226_s = Scalar("x3221_x3226")
    val x3222_x3228_s = Scalar("x3222_x3228")
    val x3093_x3100_data_v = Vector("x3093_x3100_data")
    val x3147_b3423_x3166_b3431_s = Scalar("x3147_b3423_x3166_b3431")
    val x3198_b3435_x3215_b3443_s = Scalar("x3198_b3435_x3215_b3443")
    val x3251_x3266_v = Vector("x3251_x3266")
    val x3054_oc = OffChip("x3054")
    val x3041_argin = ArgIn("x3041")
    val x3137_x3142_s = Scalar("x3137_x3142")
    val x3066_x3307_v = Vector("x3066_x3307")
    val x3052_oc = OffChip("x3052")
    val x3360 = Sequential(name="x3360",parent=top) { implicit CU => 
      val ctr1 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3360_unit = CounterChain(name = "x3360_unit", ctr1)
    }
    val x3359 = Sequential(name="x3359",parent=x3360) { implicit CU => 
      val x3041_x3060 =  ScalarBuffer().wtPort(x3041_argin)
      val ctr2 = Counter(min=Const(0), max=x3041_x3060.load, step=Const(1), par=1) // Counter
      val x3062 = CounterChain(name = "x3062", ctr2)
    }
    val x3358 = Sequential(name="x3358",parent=x3359) { implicit CU => 
      val x3042_x3063 =  ScalarBuffer().wtPort(x3042_argin)
      val ctr3 = Counter(min=Const(0), max=x3042_x3063.load, step=Const(768), par=1) // Counter
      val x3065 = CounterChain(name = "x3065", ctr3)
    }
    val x3066_dsp0 = MemoryPipeline(name="x3066_dsp0",parent="x3358") { implicit CU => 
      val x3135 = CounterChain.copy("x3333", "x3135")
      val x3345 = CounterChain.copy("x3351", "x3345")
      val x3066_x3346 =  SRAM(size = 768,banking = NoBanking()).wtPort(x3066_x3331_v).rdPort(x3066_x3346_v).rdAddr(x3345(0)).wtAddr(x3135(0))
      var stage: List[Stage] = Nil
    }
    val x3066_dsp1 = MemoryPipeline(name="x3066_dsp1",parent="x3358") { implicit CU => 
      val x3135 = CounterChain.copy("x3333", "x3135")
      val x3345 = CounterChain.copy("x3351", "x3345")
      val x3066_x3307 =  SRAM(size = 768,banking = NoBanking()).wtPort(x3066_x3331_v).rdPort(x3066_x3307_v).rdAddr(x3135(0)).wtAddr(x3135(0))
      var stage: List[Stage] = Nil
    }
    val x3067_dsp0 = MemoryPipeline(name="x3067_dsp0",parent="x3358") { implicit CU => 
      val x3083 = CounterChain.copy("x3088", "x3083")
      val x3135 = CounterChain.copy("x3333", "x3135")
      val x3067_x3306 =  SRAM(size = 768,banking = NoBanking()).wtPort(x3067_x3087_v).rdPort(x3067_x3306_v).rdAddr(x3135(0)).wtAddr(x3083(0))
      var stage: List[Stage] = Nil
    }
    val x3068_dsp0 = MemoryPipeline(name="x3068_dsp0",parent="x3358") { implicit CU => 
      val x3104 = CounterChain.copy("x3109", "x3104")
      val x3135 = CounterChain.copy("x3333", "x3135")
      val x3068_x3138 =  SRAM(size = 768,banking = NoBanking()).wtPort(x3068_x3108_v).rdPort(x3068_x3138_v).rdAddr(x3135(0)).wtAddr(x3104(0))
      var stage: List[Stage] = Nil
    }
    val x3069_dsp0 = MemoryPipeline(name="x3069_dsp0",parent="x3358") { implicit CU => 
      val x3125 = CounterChain.copy("x3130", "x3125")
      val x3135 = CounterChain.copy("x3333", "x3135")
      val x3069_x3141 =  SRAM(size = 768,banking = NoBanking()).wtPort(x3069_x3129_v).rdPort(x3069_x3141_v).rdAddr(x3135(0)).wtAddr(x3125(0))
      var stage: List[Stage] = Nil
    }
    val x3090 = StreamController(name="x3090",parent=x3358) { implicit CU => 
      val ctr4 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3090_unit = CounterChain(name = "x3090_unit", ctr4)
    }
    val x3078 = Pipeline(name="x3078",parent=x3090) { implicit CU => 
      val x1907 = CU.temp
      val x3074 = CU.temp
      val x3073 =  ScalarBuffer().wtPort(x3073_argin)
      val x3065 = CounterChain.copy("x3358", "x3065")
      val ctr5 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3078_unit = CounterChain(name = "x3078_unit", ctr5)
      var stage: List[Stage] = Nil
      stage = CU.emptyStage +: Stages(4)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x3065(0)), Const(4)), op=FixMul, results=List(CU.temp(stage(1), x1907)))
      Stage(stage(2), operands=List(CU.temp(stage(1), x1907), CU.load(stage(1), x3073)), op=FixAdd, results=List(CU.scalarOut(stage(2), x3070_b3409_x3076_b3411_s), CU.temp(stage(2), x3074)))
      Stage(stage(3), operands=List(Const(3072)), op=Bypass, results=List(CU.scalarOut(stage(3), x3070_b3410_x3076_b3412_s)))
      Stage(stage(4), operands=List(Const(768)), op=Bypass, results=List(CU.scalarOut(stage(4), x3071_x3077_s)))
    }
    val x3079 = MemoryController(name="x3079",parent=x3090,offchip=x3048_oc, mctpe=TileLoad) { implicit CU => 
      CU.mcfifos += "size" ->  ScalarFIFO(size = 1).wtPort(x3070_b3410_x3076_b3412_s)
      CU.mcfifos += "offset" ->  ScalarFIFO(size = 1).wtPort(x3070_b3409_x3076_b3411_s)
      CU.mcvecs += "data" -> x3072_x3079_data_v
    }
    val x3089 = MetaPipeline(name="x3089",parent=x3090) { implicit CU => 
      val ctr6 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3089_unit = CounterChain(name = "x3089_unit", ctr6)
    }
    val x3081 = Pipeline(name="x3081",parent=x3089) { implicit CU => 
      val x3071_x3080 =  ScalarFIFO(size = 16).wtPort(x3071_x3077_s)
      val ctr7 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3081_unit = CounterChain(name = "x3081_unit", ctr7)
      var stage: List[Stage] = Nil
    }
    val x3088 = Pipeline(name="x3088",parent=x3089) { implicit CU => 
      val x3072_x3084 =  VectorFIFO(size = 1).wtPort(x3072_x3079_data_v)
      val ctr8 = Counter(min=Const(0), max=Const(768), step=Const(1), par=1) // Counter
      val x3083 = CounterChain(name = "x3083", ctr8)
      var stage: List[Stage] = Nil
      stage = CU.emptyStage +: Stages(1)
      Stage(stage(1), operands=List(x3072_x3084.load), op=Bypass, results=List(CU.vecOut(stage(1), x3067_x3087_v)))
    }
    val x3111 = StreamController(name="x3111",parent=x3358) { implicit CU => 
      val ctr9 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3111_unit = CounterChain(name = "x3111_unit", ctr9)
    }
    val x3099 = Pipeline(name="x3099",parent=x3111) { implicit CU => 
      val x1907 = CU.temp
      val x3095 = CU.temp
      val x3094 =  ScalarBuffer().wtPort(x3094_argin)
      val x3065 = CounterChain.copy("x3358", "x3065")
      val ctr10 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3099_unit = CounterChain(name = "x3099_unit", ctr10)
      var stage: List[Stage] = Nil
      stage = CU.emptyStage +: Stages(4)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x3065(0)), Const(4)), op=FixMul, results=List(CU.temp(stage(1), x1907)))
      Stage(stage(2), operands=List(CU.temp(stage(1), x1907), CU.load(stage(1), x3094)), op=FixAdd, results=List(CU.scalarOut(stage(2), x3091_b3413_x3097_b3415_s), CU.temp(stage(2), x3095)))
      Stage(stage(3), operands=List(Const(3072)), op=Bypass, results=List(CU.scalarOut(stage(3), x3091_b3414_x3097_b3416_s)))
      Stage(stage(4), operands=List(Const(768)), op=Bypass, results=List(CU.scalarOut(stage(4), x3092_x3098_s)))
    }
    val x3100 = MemoryController(name="x3100",parent=x3111,offchip=x3052_oc, mctpe=TileLoad) { implicit CU => 
      CU.mcfifos += "size" ->  ScalarFIFO(size = 1).wtPort(x3091_b3414_x3097_b3416_s)
      CU.mcfifos += "offset" ->  ScalarFIFO(size = 1).wtPort(x3091_b3413_x3097_b3415_s)
      CU.mcvecs += "data" -> x3093_x3100_data_v
    }
    val x3110 = MetaPipeline(name="x3110",parent=x3111) { implicit CU => 
      val ctr11 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3110_unit = CounterChain(name = "x3110_unit", ctr11)
    }
    val x3102 = Pipeline(name="x3102",parent=x3110) { implicit CU => 
      val x3092_x3101 =  ScalarFIFO(size = 16).wtPort(x3092_x3098_s)
      val ctr12 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3102_unit = CounterChain(name = "x3102_unit", ctr12)
      var stage: List[Stage] = Nil
    }
    val x3109 = Pipeline(name="x3109",parent=x3110) { implicit CU => 
      val x3093_x3105 =  VectorFIFO(size = 1).wtPort(x3093_x3100_data_v)
      val ctr13 = Counter(min=Const(0), max=Const(768), step=Const(1), par=1) // Counter
      val x3104 = CounterChain(name = "x3104", ctr13)
      var stage: List[Stage] = Nil
      stage = CU.emptyStage +: Stages(1)
      Stage(stage(1), operands=List(x3093_x3105.load), op=Bypass, results=List(CU.vecOut(stage(1), x3068_x3108_v)))
    }
    val x3132 = StreamController(name="x3132",parent=x3358) { implicit CU => 
      val ctr14 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3132_unit = CounterChain(name = "x3132_unit", ctr14)
    }
    val x3120 = Pipeline(name="x3120",parent=x3132) { implicit CU => 
      val x1907 = CU.temp
      val x3116 = CU.temp
      val x3115 =  ScalarBuffer().wtPort(x3115_argin)
      val x3065 = CounterChain.copy("x3358", "x3065")
      val ctr15 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3120_unit = CounterChain(name = "x3120_unit", ctr15)
      var stage: List[Stage] = Nil
      stage = CU.emptyStage +: Stages(4)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x3065(0)), Const(4)), op=FixMul, results=List(CU.temp(stage(1), x1907)))
      Stage(stage(2), operands=List(CU.temp(stage(1), x1907), CU.load(stage(1), x3115)), op=FixAdd, results=List(CU.scalarOut(stage(2), x3112_b3417_x3118_b3419_s), CU.temp(stage(2), x3116)))
      Stage(stage(3), operands=List(Const(3072)), op=Bypass, results=List(CU.scalarOut(stage(3), x3112_b3418_x3118_b3420_s)))
      Stage(stage(4), operands=List(Const(768)), op=Bypass, results=List(CU.scalarOut(stage(4), x3113_x3119_s)))
    }
    val x3121 = MemoryController(name="x3121",parent=x3132,offchip=x3054_oc, mctpe=TileLoad) { implicit CU => 
      CU.mcfifos += "offset" ->  ScalarFIFO(size = 1).wtPort(x3112_b3417_x3118_b3419_s)
      CU.mcfifos += "size" ->  ScalarFIFO(size = 1).wtPort(x3112_b3418_x3118_b3420_s)
      CU.mcvecs += "data" -> x3114_x3121_data_v
    }
    val x3131 = MetaPipeline(name="x3131",parent=x3132) { implicit CU => 
      val ctr16 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3131_unit = CounterChain(name = "x3131_unit", ctr16)
    }
    val x3123 = Pipeline(name="x3123",parent=x3131) { implicit CU => 
      val x3113_x3122 =  ScalarFIFO(size = 16).wtPort(x3113_x3119_s)
      val ctr17 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3123_unit = CounterChain(name = "x3123_unit", ctr17)
      var stage: List[Stage] = Nil
    }
    val x3130 = Pipeline(name="x3130",parent=x3131) { implicit CU => 
      val x3114_x3126 =  VectorFIFO(size = 1).wtPort(x3114_x3121_data_v)
      val ctr18 = Counter(min=Const(0), max=Const(768), step=Const(1), par=1) // Counter
      val x3125 = CounterChain(name = "x3125", ctr18)
      var stage: List[Stage] = Nil
      stage = CU.emptyStage +: Stages(1)
      Stage(stage(1), operands=List(x3114_x3126.load), op=Bypass, results=List(CU.vecOut(stage(1), x3069_x3129_v)))
    }
    val x3333 = Sequential(name="x3333",parent=x3358) { implicit CU => 
      val ctr19 = Counter(min=Const(0), max=Const(768), step=Const(1), par=1) // Counter
      val x3135 = CounterChain(name = "x3135", ctr19)
    }
    val x3140 = Pipeline(name="x3140",parent=x3333) { implicit CU => 
      val x3135 = CounterChain.copy("x3333", "x3135")
      val ctr20 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3140_unit = CounterChain(name = "x3140_unit", ctr20)
      var stage: List[Stage] = Nil
      stage = CU.emptyStage +: Stages(1)
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x3068_x3138_v)), op=Bypass, results=List(CU.scalarOut(stage(1), x3136_x3139_s)))
    }
    val x3143 = Pipeline(name="x3143",parent=x3333) { implicit CU => 
      val x3135 = CounterChain.copy("x3333", "x3135")
      val ctr21 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3143_unit = CounterChain(name = "x3143_unit", ctr21)
      var stage: List[Stage] = Nil
      stage = CU.emptyStage +: Stages(1)
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x3069_x3141_v)), op=Bypass, results=List(CU.scalarOut(stage(1), x3137_x3142_s)))
    }
    val x3144_dsp0 = MemoryPipeline(name="x3144_dsp0",parent="x3333") { implicit CU => 
      val x3186 = CU.temp
      val x3169_x3183 =  ScalarBuffer().wtPort(x3169_x3174_s)
      val x3182 = CounterChain.copy("x3195", "x3182")
      val x3272 = CounterChain.copy("x3277", "x3272")
      val x3065 = CounterChain.copy("x3358", "x3065")
      val x3303 = CounterChain.copy("x3325", "x3303")
      val x3255 = CounterChain.copy("x3268", "x3255")
      val x3135 = CounterChain.copy("x3333", "x3135")
      val x3144_x3304 =  SRAM(size = 768,banking = NoBanking()).wtPort(x3144_x3194_v).rdPort(x3144_x3304_v).rdAddr(x3303(0))
      var stage: List[Stage] = Nil
      stage = CU.emptyStage +: WAStages(2, List(x3144_x3304))
      Stage(stage(1), operands=List(x3182(0), x3169_x3183.load), op=FixSub, results=List(x3144_x3304.writeAddr, CU.temp(stage(1), x3186)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x3182(0)), CU.load(stage(1), x3169_x3183)), op=FixSub, results=List(CU.temp(stage(2), x3186)))
    }
    val x3144_dsp1 = MemoryPipeline(name="x3144_dsp1",parent="x3333") { implicit CU => 
      val x3186 = CU.temp
      val x3169_x3183 =  ScalarBuffer().wtPort(x3169_x3174_s)
      val x3182 = CounterChain.copy("x3195", "x3182")
      val x3272 = CounterChain.copy("x3277", "x3272")
      val x3065 = CounterChain.copy("x3358", "x3065")
      val x3303 = CounterChain.copy("x3325", "x3303")
      val x3255 = CounterChain.copy("x3268", "x3255")
      val x3135 = CounterChain.copy("x3333", "x3135")
      val x3144_x3274 =  SRAM(size = 768,banking = NoBanking()).wtPort(x3144_x3194_v).rdPort(x3144_x3274_v).rdAddr(x3272(0))
      var stage: List[Stage] = Nil
      stage = CU.emptyStage +: WAStages(2, List(x3144_x3274))
      Stage(stage(1), operands=List(x3182(0), x3169_x3183.load), op=FixSub, results=List(x3144_x3274.writeAddr, CU.temp(stage(1), x3186)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x3182(0)), CU.load(stage(1), x3169_x3183)), op=FixSub, results=List(CU.temp(stage(2), x3186)))
    }
    val x3144_dsp2 = MemoryPipeline(name="x3144_dsp2",parent="x3333") { implicit CU => 
      val x3186 = CU.temp
      val x3169_x3183 =  ScalarBuffer().wtPort(x3169_x3174_s)
      val x3182 = CounterChain.copy("x3195", "x3182")
      val x3272 = CounterChain.copy("x3277", "x3272")
      val x3065 = CounterChain.copy("x3358", "x3065")
      val x3303 = CounterChain.copy("x3325", "x3303")
      val x3255 = CounterChain.copy("x3268", "x3255")
      val x3135 = CounterChain.copy("x3333", "x3135")
      val x3144_x3256 =  SRAM(size = 768,banking = NoBanking()).wtPort(x3144_x3194_v).rdPort(x3144_x3256_v).rdAddr(x3255(0))
      var stage: List[Stage] = Nil
      stage = CU.emptyStage +: WAStages(2, List(x3144_x3256))
      Stage(stage(1), operands=List(x3182(0), x3169_x3183.load), op=FixSub, results=List(x3144_x3256.writeAddr, CU.temp(stage(1), x3186)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x3182(0)), CU.load(stage(1), x3169_x3183)), op=FixSub, results=List(CU.temp(stage(2), x3186)))
    }
    val x3145_dsp0 = MemoryPipeline(name="x3145_dsp0",parent="x3333") { implicit CU => 
      val x3238 = CU.temp
      val x3221_x3235 =  ScalarBuffer().wtPort(x3221_x3226_s)
      val x3234 = CounterChain.copy("x3247", "x3234")
      val x3135 = CounterChain.copy("x3333", "x3135")
      val x3065 = CounterChain.copy("x3358", "x3065")
      val x3303 = CounterChain.copy("x3325", "x3303")
      val x3145_x3310 =  SRAM(size = 768,banking = NoBanking()).wtPort(x3145_x3246_v).rdPort(x3145_x3310_v).rdAddr(x3303(0))
      var stage: List[Stage] = Nil
      stage = CU.emptyStage +: WAStages(2, List(x3145_x3310))
      Stage(stage(1), operands=List(x3234(0), x3221_x3235.load), op=FixSub, results=List(x3145_x3310.writeAddr, CU.temp(stage(1), x3238)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x3234(0)), CU.load(stage(1), x3221_x3235)), op=FixSub, results=List(CU.temp(stage(2), x3238)))
    }
    val x3197 = StreamController(name="x3197",parent=x3333) { implicit CU => 
      val ctr22 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3197_unit = CounterChain(name = "x3197_unit", ctr22)
    }
    val x3167 = Pipeline(name="x3167",parent=x3197) { implicit CU => 
      val x3161 = CU.temp
      val x3157 = CU.temp
      val x3158 = CU.temp
      val x3153 = CU.temp
      val x3156 = CU.temp
      val x3159 = CU.temp
      val x3154 = CU.temp
      val x3160 = CU.temp
      val x3164 = CU.temp
      val x3155 = CU.temp
      val x3152 = CU.temp
      val x3136_x3150 =  ScalarBuffer().wtPort(x3136_x3139_s)
      val x3137_x3151 =  ScalarBuffer().wtPort(x3137_x3142_s)
      val x3149 =  ScalarBuffer().wtPort(x3149_argin)
      val ctr23 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3167_unit = CounterChain(name = "x3167_unit", ctr23)
      var stage: List[Stage] = Nil
      stage = CU.emptyStage +: Stages(11)
      Stage(stage(1), operands=List(x3136_x3150.load, Const(4)), op=FixMul, results=List(CU.temp(stage(1), x3152)))
      Stage(stage(2), operands=List(CU.temp(stage(1), x3152), Const(16)), op=FixMod, results=List(CU.scalarOut(stage(2), x3147_b3424_x3166_b3432_s), CU.temp(stage(2), x3153)))
      Stage(stage(3), operands=List(CU.load(stage(2), x3137_x3151), Const(16)), op=FixMod, results=List(CU.temp(stage(3), x3156)))
      Stage(stage(4), operands=List(Const(16), CU.temp(stage(3), x3156)), op=FixSub, results=List(CU.temp(stage(4), x3157)))
      Stage(stage(5), operands=List(CU.temp(stage(4), x3156), Const(0)), op=FixEql, results=List(CU.temp(stage(5), x3158)))
      Stage(stage(6), operands=List(CU.temp(stage(5), x3158), Const(0), CU.temp(stage(5), x3157)), op=Mux, results=List(CU.temp(stage(6), x3159)))
      Stage(stage(7), operands=List(CU.load(stage(6), x3137_x3151), CU.temp(stage(6), x3159)), op=FixAdd, results=List(CU.scalarOut(stage(7), x3147_b3423_x3166_b3431_s), CU.temp(stage(7), x3160)))
      Stage(stage(8), operands=List(CU.temp(stage(7), x3153), CU.load(stage(7), x3137_x3151)), op=FixAdd, results=List(CU.scalarOut(stage(8), x3147_b3425_x3166_b3433_s), CU.temp(stage(8), x3164)))
      Stage(stage(9), operands=List(CU.temp(stage(8), x3152), CU.load(stage(8), x3149)), op=FixAdd, results=List(CU.temp(stage(9), x3154)))
      Stage(stage(10), operands=List(CU.temp(stage(9), x3154), CU.temp(stage(9), x3153)), op=FixSub, results=List(CU.scalarOut(stage(10), x3146_b3421_x3163_b3429_s), CU.temp(stage(10), x3155)))
      Stage(stage(11), operands=List(CU.temp(stage(10), x3160), Const(4)), op=FixMul, results=List(CU.scalarOut(stage(11), x3146_b3422_x3163_b3430_s), CU.temp(stage(11), x3161)))
    }
    val x3168 = MemoryController(name="x3168",parent=x3197,offchip=x3049_oc, mctpe=TileLoad) { implicit CU => 
      CU.mcfifos += "offset" ->  ScalarFIFO(size = 1).wtPort(x3146_b3421_x3163_b3429_s)
      CU.mcfifos += "size" ->  ScalarFIFO(size = 1).wtPort(x3146_b3422_x3163_b3430_s)
      CU.mcvecs += "data" -> x3148_x3168_data_v
    }
    val x3196 = MetaPipeline(name="x3196",parent=x3197) { implicit CU => 
      val ctr24 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3196_unit = CounterChain(name = "x3196_unit", ctr24)
    }
    val x3179 = Pipeline(name="x3179",parent=x3196) { implicit CU => 
      val x3147_b3423_x3172_b3426 =  ScalarFIFO(size = 16).wtPort(x3147_b3423_x3166_b3431_s)
      val x3147_b3425_x3172_b3428 =  ScalarFIFO(size = 16).wtPort(x3147_b3425_x3166_b3433_s)
      val x3147_b3424_x3172_b3427 =  ScalarFIFO(size = 16).wtPort(x3147_b3424_x3166_b3432_s)
      val ctr25 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3179_unit = CounterChain(name = "x3179_unit", ctr25)
      var stage: List[Stage] = Nil
      stage = CU.emptyStage +: Stages(3)
      Stage(stage(1), operands=List(x3147_b3424_x3172_b3427.load), op=Bypass, results=List(CU.scalarOut(stage(1), x3169_x3174_s)))
      Stage(stage(2), operands=List(x3147_b3425_x3172_b3428.load), op=Bypass, results=List(CU.scalarOut(stage(2), x3170_x3176_s)))
      Stage(stage(3), operands=List(x3147_b3423_x3172_b3426.load), op=Bypass, results=List(CU.scalarOut(stage(3), x3171_x3178_s)))
    }
    val x3195 = Pipeline(name="x3195",parent=x3196) { implicit CU => 
      val x3148_x3185 =  VectorFIFO(size = 1).wtPort(x3148_x3168_data_v)
      val x3169_x3183 =  ScalarBuffer().wtPort(x3169_x3174_s)
      val x3171_x3180 =  ScalarBuffer().wtPort(x3171_x3178_s)
      val x3170_x3184 =  ScalarBuffer().wtPort(x3170_x3176_s)
      val ctr26 = Counter(min=Const(0), max=x3171_x3180.load, step=Const(1), par=1) // Counter
      val x3182 = CounterChain(name = "x3182", ctr26)
      var stage: List[Stage] = Nil
      stage = CU.emptyStage +: Stages(1)
      Stage(stage(1), operands=List(x3148_x3185.load), op=Bypass, results=List(CU.vecOut(stage(1), x3144_x3194_v)))
    }
    val x3249 = StreamController(name="x3249",parent=x3333) { implicit CU => 
      val ctr27 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3249_unit = CounterChain(name = "x3249_unit", ctr27)
    }
    val x3219 = Pipeline(name="x3219",parent=x3249) { implicit CU => 
      val x3204 = CU.temp
      val x3205 = CU.temp
      val x3210 = CU.temp
      val x3208 = CU.temp
      val x3216 = CU.temp
      val x3213 = CU.temp
      val x3212 = CU.temp
      val x3209 = CU.temp
      val x3207 = CU.temp
      val x3211 = CU.temp
      val x3206 = CU.temp
      val x3136_x3202 =  ScalarBuffer().wtPort(x3136_x3139_s)
      val x3201 =  ScalarBuffer().wtPort(x3201_argin)
      val x3137_x3203 =  ScalarBuffer().wtPort(x3137_x3142_s)
      val ctr28 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3219_unit = CounterChain(name = "x3219_unit", ctr28)
      var stage: List[Stage] = Nil
      stage = CU.emptyStage +: Stages(11)
      Stage(stage(1), operands=List(x3136_x3202.load, Const(4)), op=FixMul, results=List(CU.temp(stage(1), x3204)))
      Stage(stage(2), operands=List(CU.temp(stage(1), x3204), Const(16)), op=FixMod, results=List(CU.scalarOut(stage(2), x3199_b3437_x3218_b3445_s), CU.temp(stage(2), x3205)))
      Stage(stage(3), operands=List(CU.load(stage(2), x3137_x3203), Const(16)), op=FixMod, results=List(CU.temp(stage(3), x3208)))
      Stage(stage(4), operands=List(Const(16), CU.temp(stage(3), x3208)), op=FixSub, results=List(CU.temp(stage(4), x3209)))
      Stage(stage(5), operands=List(CU.temp(stage(4), x3208), Const(0)), op=FixEql, results=List(CU.temp(stage(5), x3210)))
      Stage(stage(6), operands=List(CU.temp(stage(5), x3210), Const(0), CU.temp(stage(5), x3209)), op=Mux, results=List(CU.temp(stage(6), x3211)))
      Stage(stage(7), operands=List(CU.load(stage(6), x3137_x3203), CU.temp(stage(6), x3211)), op=FixAdd, results=List(CU.scalarOut(stage(7), x3199_b3436_x3218_b3444_s), CU.temp(stage(7), x3212)))
      Stage(stage(8), operands=List(CU.temp(stage(7), x3205), CU.load(stage(7), x3137_x3203)), op=FixAdd, results=List(CU.scalarOut(stage(8), x3199_b3438_x3218_b3446_s), CU.temp(stage(8), x3216)))
      Stage(stage(9), operands=List(CU.temp(stage(8), x3204), CU.load(stage(8), x3201)), op=FixAdd, results=List(CU.temp(stage(9), x3206)))
      Stage(stage(10), operands=List(CU.temp(stage(9), x3206), CU.temp(stage(9), x3205)), op=FixSub, results=List(CU.scalarOut(stage(10), x3198_b3434_x3215_b3442_s), CU.temp(stage(10), x3207)))
      Stage(stage(11), operands=List(CU.temp(stage(10), x3212), Const(4)), op=FixMul, results=List(CU.scalarOut(stage(11), x3198_b3435_x3215_b3443_s), CU.temp(stage(11), x3213)))
    }
    val x3220 = MemoryController(name="x3220",parent=x3249,offchip=x3050_oc, mctpe=TileLoad) { implicit CU => 
      CU.mcfifos += "size" ->  ScalarFIFO(size = 1).wtPort(x3198_b3435_x3215_b3443_s)
      CU.mcfifos += "offset" ->  ScalarFIFO(size = 1).wtPort(x3198_b3434_x3215_b3442_s)
      CU.mcvecs += "data" -> x3200_x3220_data_v
    }
    val x3248 = MetaPipeline(name="x3248",parent=x3249) { implicit CU => 
      val ctr29 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3248_unit = CounterChain(name = "x3248_unit", ctr29)
    }
    val x3231 = Pipeline(name="x3231",parent=x3248) { implicit CU => 
      val x3199_b3438_x3224_b3441 =  ScalarFIFO(size = 16).wtPort(x3199_b3438_x3218_b3446_s)
      val x3199_b3437_x3224_b3440 =  ScalarFIFO(size = 16).wtPort(x3199_b3437_x3218_b3445_s)
      val x3199_b3436_x3224_b3439 =  ScalarFIFO(size = 16).wtPort(x3199_b3436_x3218_b3444_s)
      val ctr30 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3231_unit = CounterChain(name = "x3231_unit", ctr30)
      var stage: List[Stage] = Nil
      stage = CU.emptyStage +: Stages(3)
      Stage(stage(1), operands=List(x3199_b3437_x3224_b3440.load), op=Bypass, results=List(CU.scalarOut(stage(1), x3221_x3226_s)))
      Stage(stage(2), operands=List(x3199_b3438_x3224_b3441.load), op=Bypass, results=List(CU.scalarOut(stage(2), x3222_x3228_s)))
      Stage(stage(3), operands=List(x3199_b3436_x3224_b3439.load), op=Bypass, results=List(CU.scalarOut(stage(3), x3223_x3230_s)))
    }
    val x3247 = Pipeline(name="x3247",parent=x3248) { implicit CU => 
      val x3223_x3232 =  ScalarBuffer().wtPort(x3223_x3230_s)
      val x3222_x3236 =  ScalarBuffer().wtPort(x3222_x3228_s)
      val x3221_x3235 =  ScalarBuffer().wtPort(x3221_x3226_s)
      val x3200_x3237 =  VectorFIFO(size = 1).wtPort(x3200_x3220_data_v)
      val ctr31 = Counter(min=Const(0), max=x3223_x3232.load, step=Const(1), par=1) // Counter
      val x3234 = CounterChain(name = "x3234", ctr31)
      var stage: List[Stage] = Nil
      stage = CU.emptyStage +: Stages(1)
      Stage(stage(1), operands=List(x3200_x3237.load), op=Bypass, results=List(CU.vecOut(stage(1), x3145_x3246_v)))
    }
    val x3251_dsp0 = MemoryPipeline(name="x3251_dsp0",parent="x3333") { implicit CU => 
      val x3272 = CounterChain.copy("x3277", "x3272")
      val x3065 = CounterChain.copy("x3358", "x3065")
      val x3303 = CounterChain.copy("x3325", "x3303")
      val x3255 = CounterChain.copy("x3268", "x3255")
      val x3135 = CounterChain.copy("x3333", "x3135")
      val x3251_x3305 =  SRAM(size = 768,banking = NoBanking()).wtPort(x3251_x3266_v).rdPort(x3251_x3305_v).rdAddr(x3303(0)).wtAddr(x3255(0))
      var stage: List[Stage] = Nil
    }
    val x3251_dsp1 = MemoryPipeline(name="x3251_dsp1",parent="x3333") { implicit CU => 
      val x3272 = CounterChain.copy("x3277", "x3272")
      val x3065 = CounterChain.copy("x3358", "x3065")
      val x3303 = CounterChain.copy("x3325", "x3303")
      val x3255 = CounterChain.copy("x3268", "x3255")
      val x3135 = CounterChain.copy("x3333", "x3135")
      val x3251_x3273 =  SRAM(size = 768,banking = NoBanking()).wtPort(x3251_x3266_v).rdPort(x3251_x3273_v).rdAddr(x3272(0)).wtAddr(x3255(0))
      var stage: List[Stage] = Nil
    }
    val x3268 = Sequential(name="x3268",parent=x3333) { implicit CU => 
      val x3137_x3253 =  ScalarBuffer().wtPort(x3137_x3142_s)
      val ctr32 = Counter(min=Const(0), max=x3137_x3253.load, step=Const(1), par=1) // Counter
      val x3255 = CounterChain(name = "x3255", ctr32)
    }
    val x3267 = Pipeline(name="x3267",parent=x3268) { implicit CU => 
      val x3258 = CU.temp
      val ar236 = CU.accum(init = Const(-1))
      val x2123 = CU.temp
      val x3261 = CU.temp
      val x3265 = CU.temp
      val x3260 = CU.temp
      val x3262 = CU.temp
      val x3259 = CU.temp
      val x3255 = CounterChain.copy("x3268", "x3255")
      val x3065 = CounterChain.copy("x3358", "x3065")
      val ctr33 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3267_unit = CounterChain(name = "x3267_unit", ctr33)
      var stage: List[Stage] = Nil
      stage = CU.emptyStage +: Stages(7)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x3065(0)), CU.vecIn(stage(0), x3144_x3256_v)), op=FixLeq, results=List(CU.temp(stage(1), x3258)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x3065(0)), Const(768)), op=FixAdd, results=List(CU.temp(stage(2), x2123)))
      Stage(stage(3), operands=List(CU.vecIn(stage(2), x3144_x3256_v), CU.temp(stage(2), x2123)), op=FixLt, results=List(CU.temp(stage(3), x3259)))
      Stage(stage(4), operands=List(CU.temp(stage(3), x3258), CU.temp(stage(3), x3259)), op=BitAnd, results=List(CU.temp(stage(4), x3260)))
      Stage(stage(5), operands=List(CU.temp(stage(4), x3260), CU.accum(stage(5), ar236), Const(-1)), op=Mux, results=List(CU.vecOut(stage(5), x3251_x3266_v), CU.temp(stage(5), x3265)))
      Stage(stage(6), operands=List(CU.temp(stage(5), x3260), Const(0), Const(1)), op=Mux, results=List(CU.temp(stage(6), x3261)))
      Stage(stage(7), operands=List(CU.accum(stage(6), ar236), CU.temp(stage(6), x3261)), op=FixAdd, results=List(CU.temp(stage(7), x3262)))
    }
    val x3269_dsp0 = MemoryPipeline(name="x3269_dsp0",parent="x3333") { implicit CU => 
      val x3272 = CounterChain.copy("x3277", "x3272")
      val x3282 = CounterChain.copy("x3290", "x3282")
      val x3269_x3283 =  SRAM(size = 768,banking = NoBanking()).wtPort(x3269_x3275_v).rdPort(x3269_x3283_v).rdAddr(x3282(0))
      var stage: List[Stage] = Nil
      stage = CU.emptyStage +: WAStages(1, List(x3269_x3283))
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x3251_x3273_v)), op=Bypass, results=List(x3269_x3283.writeAddr))
    }
    val x3277 = Sequential(name="x3277",parent=x3333) { implicit CU => 
      val x3137_x3270 =  ScalarBuffer().wtPort(x3137_x3142_s)
      val ctr34 = Counter(min=Const(0), max=x3137_x3270.load, step=Const(1), par=1) // Counter
      val x3272 = CounterChain(name = "x3272", ctr34)
    }
    val x3276 = Pipeline(name="x3276",parent=x3277) { implicit CU => 
      val x3272 = CounterChain.copy("x3277", "x3272")
      val ctr35 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3276_unit = CounterChain(name = "x3276_unit", ctr35)
      var stage: List[Stage] = Nil
      stage = CU.emptyStage +: Stages(1)
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x3144_x3274_v)), op=Bypass, results=List(CU.vecOut(stage(1), x3269_x3275_v)))
    }
    val x3278_dsp0 = MemoryPipeline(name="x3278_dsp0",parent="x3333") { implicit CU => 
      val x3293 = CounterChain.copy("x3298", "x3293")
      val x3135 = CounterChain.copy("x3333", "x3135")
      val x3065 = CounterChain.copy("x3358", "x3065")
      val x3303 = CounterChain.copy("x3325", "x3303")
      val x3278_x3309 =  SRAM(size = 768,banking = NoBanking()).wtPort(x3278_x3297_v).rdPort(x3278_x3309_v).wtAddr(x3293(0))
      var stage: List[Stage] = Nil
      stage = CU.emptyStage +: RAStages(1, List(x3278_x3309))
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x3251_x3305_v)), op=Bypass, results=List(x3278_x3309.readAddr))
    }
    val x3299 = StreamController(name="x3299",parent=x3333) { implicit CU => 
      val ctr36 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3299_unit = CounterChain(name = "x3299_unit", ctr36)
    }
    val x3290 = Pipeline(name="x3290",parent=x3299) { implicit CU => 
      val x3286 = CU.temp
      val x3287 = CU.temp
      val x3284 =  ScalarBuffer().wtPort(x3284_argin)
      val ctr37 = Counter(min=Const(0), max=Const(768), step=Const(1), par=1) // Counter
      val x3282 = CounterChain(name = "x3282", ctr37)
      var stage: List[Stage] = Nil
      stage = CU.emptyStage +: Stages(2)
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x3269_x3283_v), Const(4)), op=FixMul, results=List(CU.temp(stage(1), x3286)))
      Stage(stage(2), operands=List(CU.temp(stage(1), x3286), CU.load(stage(1), x3284)), op=FixAdd, results=List(CU.scalarOut(stage(2), x3279_x3289_s), CU.temp(stage(2), x3287)))
    }
    val x3291 = MemoryController(name="x3291",parent=x3299,offchip=x3048_oc, mctpe=Gather) { implicit CU => 
      CU.mcfifos += "addr" ->  ScalarFIFO(size = 1).wtPort(x3279_x3289_s)
      CU.mcvecs += "data" -> x3280_x3291_data_v
    }
    val x3298 = Pipeline(name="x3298",parent=x3299) { implicit CU => 
      val x3280_x3294 =  VectorFIFO(size = 1).wtPort(x3280_x3291_data_v)
      val ctr38 = Counter(min=Const(0), max=Const(768), step=Const(1), par=1) // Counter
      val x3293 = CounterChain(name = "x3293", ctr38)
      var stage: List[Stage] = Nil
      stage = CU.emptyStage +: Stages(1)
      Stage(stage(1), operands=List(x3280_x3294.load), op=Bypass, results=List(CU.vecOut(stage(1), x3278_x3297_v)))
    }
    val x3325 = Pipeline(name="x3325",parent=x3333) { implicit CU => 
      val x3321 = CU.temp
      val x3317 = CU.temp
      val x3318 = CU.temp
      val x3319 = CU.temp
      val x3320 = CU.temp
      val x3322 = CU.temp
      val ar321 = CU.accum(init = Const(0))
      val x3137_x3301 =  ScalarBuffer().wtPort(x3137_x3142_s)
      val x3135 = CounterChain.copy("x3333", "x3135")
      val x3065 = CounterChain.copy("x3358", "x3065")
      val ctr39 = Counter(min=Const(0), max=x3137_x3301.load, step=Const(1), par=1) // Counter
      val x3303 = CounterChain(name = "x3303", ctr39)
      var stage: List[Stage] = Nil
      stage = CU.emptyStage +: Stages(7)
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x3144_x3304_v), CU.ctr(stage(0), x3065(0))), op=FixSub, results=List(CU.temp(stage(1), x3317)))
      Stage(stage(2), operands=List(CU.temp(stage(1), x3317), CU.ctr(stage(1), x3135(0))), op=FixLeq, results=List(CU.temp(stage(2), x3318)))
      Stage(stage(3), operands=List(CU.temp(stage(2), x3318), CU.vecIn(stage(2), x3067_x3306_v), CU.vecIn(stage(2), x3066_x3307_v)), op=Mux, results=List(CU.temp(stage(3), x3319)))
      Stage(stage(4), operands=List(CU.vecIn(stage(3), x3251_x3305_v), Const(-1)), op=FixEql, results=List(CU.temp(stage(4), x3320)))
      Stage(stage(5), operands=List(CU.temp(stage(4), x3320), CU.temp(stage(4), x3319), CU.vecIn(stage(4), x3278_x3309_v)), op=Mux, results=List(CU.temp(stage(5), x3321)))
      Stage(stage(6), operands=List(CU.temp(stage(5), x3321), CU.vecIn(stage(5), x3145_x3310_v)), op=FltDiv, results=List(CU.reduce(stage(6)), CU.temp(stage(6), x3322)))
      val (rs1, rr535) = Stage.reduce(op=FltAdd, init=Const(0))
      Stage(stage(7), operands=List(rr535), op=Bypass, results=List(CU.scalarOut(stage(7), x3300_x3324_s)))
    }
    val x3332 = Pipeline(name="x3332",parent=x3333) { implicit CU => 
      val x3329 = CU.temp
      val x3330 = CU.temp
      val x3328 = CU.temp
      val x3300_x3327 =  ScalarBuffer().wtPort(x3300_x3324_s)
      val x3043_x3326 =  ScalarBuffer().wtPort(x3043_argin)
      val x3135 = CounterChain.copy("x3333", "x3135")
      val ctr40 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3332_unit = CounterChain(name = "x3332_unit", ctr40)
      var stage: List[Stage] = Nil
      stage = CU.emptyStage +: Stages(3)
      Stage(stage(1), operands=List(x3300_x3327.load, x3043_x3326.load), op=FltMul, results=List(CU.temp(stage(1), x3328)))
      Stage(stage(2), operands=List(Const(1), CU.load(stage(1), x3043_x3326)), op=FltSub, results=List(CU.temp(stage(2), x3329)))
      Stage(stage(3), operands=List(CU.temp(stage(2), x3328), CU.temp(stage(2), x3329)), op=FltAdd, results=List(CU.vecOut(stage(3), x3066_x3331_v), CU.temp(stage(3), x3330)))
    }
    val x3357 = StreamController(name="x3357",parent=x3358) { implicit CU => 
      val ctr41 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3357_unit = CounterChain(name = "x3357_unit", ctr41)
    }
    val x3352 = MetaPipeline(name="x3352",parent=x3357) { implicit CU => 
      val ctr42 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3352_unit = CounterChain(name = "x3352_unit", ctr42)
    }
    val x3343 = Pipeline(name="x3343",parent=x3352) { implicit CU => 
      val x1907 = CU.temp
      val x3339 = CU.temp
      val x3338 =  ScalarBuffer().wtPort(x3338_argin)
      val x3065 = CounterChain.copy("x3358", "x3065")
      val ctr43 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3343_unit = CounterChain(name = "x3343_unit", ctr43)
      var stage: List[Stage] = Nil
      stage = CU.emptyStage +: Stages(4)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x3065(0)), Const(4)), op=FixMul, results=List(CU.temp(stage(1), x1907)))
      Stage(stage(2), operands=List(CU.temp(stage(1), x1907), CU.load(stage(1), x3338)), op=FixAdd, results=List(CU.scalarOut(stage(2), x3334_b3447_x3341_b3449_s), CU.temp(stage(2), x3339)))
      Stage(stage(3), operands=List(Const(3072)), op=Bypass, results=List(CU.scalarOut(stage(3), x3334_b3448_x3341_b3450_s)))
      Stage(stage(4), operands=List(Const(768)), op=Bypass, results=List(CU.scalarOut(stage(4), x3335_x3342_s)))
    }
    val x3351 = Pipeline(name="x3351",parent=x3352) { implicit CU => 
      val ctr44 = Counter(min=Const(0), max=Const(768), step=Const(1), par=1) // Counter
      val x3345 = CounterChain(name = "x3345", ctr44)
      var stage: List[Stage] = Nil
      stage = CU.emptyStage +: Stages(1)
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x3066_x3346_v)), op=Bypass, results=List(CU.scalarOut(stage(1), x3336_x3350_s)))
    }
    val x3353 = MemoryController(name="x3353",parent=x3357,offchip=x3048_oc, mctpe=TileStore) { implicit CU => 
      CU.mcfifos += "offset" ->  ScalarFIFO(size = 1).wtPort(x3334_b3447_x3341_b3449_s)
      CU.mcfifos += "data" ->  ScalarFIFO(size = 1).wtPort(x3336_x3350_s)
      CU.mcfifos += "size" ->  ScalarFIFO(size = 1).wtPort(x3334_b3448_x3341_b3450_s)
      CU.mcvecs += "ack" -> x3337_x3353_ack_v
    }
    val x3356 = Pipeline(name="x3356",parent=x3357) { implicit CU => 
      val x3335_x3354 =  ScalarFIFO(size = 16).wtPort(x3335_x3342_s)
      val x3337_x3355 =  VectorFIFO(size = 1).wtPort(x3337_x3353_ack_v)
      val ctr45 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3356_unit = CounterChain(name = "x3356_unit", ctr45)
      var stage: List[Stage] = Nil
    }
    
  }
}
