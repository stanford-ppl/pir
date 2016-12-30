import pir.graph
import pir.graph._
import pir.graph.enums._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.misc._
import pir.PIRApp

object BFSDesign extends PIRApp {
  def main(args: String*)(top:Top) = {
    val x2981_scalar = Scalar("x2981")
    val x2832_oc = OffChip("x2832")
    val x2881_scalar = Scalar("x2881")
    val x2833_oc = OffChip("x2833")
    val x2879_vector = Vector("x2879")
    val x2983_scalar = Scalar("x2983")
    val x2972_scalar = Scalar("x2972")
    val x2984_scalar = Scalar("x2984")
    val x2980_scalar = Scalar("x2980")
    val x2831_oc = OffChip("x2831")
    val x2982_scalar = Scalar("x2982")
    val x2875_vector = Vector("x2875")
    val x3057_vector = Vector("x3057")
    val x2830_oc = OffChip("x2830")
    val x2878_vector = Vector("x2878")
    val x3124_mc = MemoryController(Scatter, x2833_oc)
    val x2903_mc = MemoryController(TileLoad, x2832_oc)
    val x2947_mc = MemoryController(TileLoad, x2831_oc)
    val x3033_mc = MemoryController(TileLoad, x2830_oc)
    val x3134 = Sequential(name = "x3134", parent=top, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3134_unitcc = CounterChain(name = "x3134_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x2925 = Sequential(name = "x2925", parent=x3134, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x2925_unitcc = CounterChain(name = "x2925_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x2923 = StreamController(name = "x2923", parent=x2925, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x2923_unitcc = CounterChain(name = "x2923_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x2899_0 = UnitPipeline(name = "x2899_0", parent=x2923, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr190 = CU.temp
      val tr189 = CU.temp
      val tr188 = CU.temp
      val tr187 = CU.temp
      val x2899_unitcc = CounterChain(name = "x2899_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(6)
      Stage(stage(1), operands=List(Const("8000i"), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr187)))
      Stage(stage(2), operands=List(Const("8000i"), CU.temp(stage(1), tr187)), op=FixSub, results=List(CU.temp(stage(2), tr188)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr187), Const("0i")), op=FixNeq, results=List(CU.temp(stage(3), tr189)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr189), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(4), tr190)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr188), CU.temp(stage(4), tr190)), op=FixAdd, results=List(CU.scalarOut(stage(5), x2903_mc.len)))
      Stage(stage(6), operands=List(Const("0i")), op=Bypass, results=List(CU.scalarOut(stage(6), x2903_mc.ofs)))
    }
    val x2969 = Sequential(name = "x2969", parent=x3134, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x2969_unitcc = CounterChain(name = "x2969_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x2967 = StreamController(name = "x2967", parent=x2969, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x2967_unitcc = CounterChain(name = "x2967_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x2943_0 = UnitPipeline(name = "x2943_0", parent=x2967, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr212 = CU.temp
      val tr211 = CU.temp
      val tr210 = CU.temp
      val tr209 = CU.temp
      val x2943_unitcc = CounterChain(name = "x2943_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(6)
      Stage(stage(1), operands=List(Const("8000i"), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr209)))
      Stage(stage(2), operands=List(Const("8000i"), CU.temp(stage(1), tr209)), op=FixSub, results=List(CU.temp(stage(2), tr210)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr209), Const("0i")), op=FixNeq, results=List(CU.temp(stage(3), tr211)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr211), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(4), tr212)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr210), CU.temp(stage(4), tr212)), op=FixAdd, results=List(CU.scalarOut(stage(5), x2947_mc.len)))
      Stage(stage(6), operands=List(Const("0i")), op=Bypass, results=List(CU.scalarOut(stage(6), x2947_mc.ofs)))
    }
    val x3132 = Sequential(name = "x3132", parent=x3134, deps=List(x2925, x2969)) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr3 = (Const("0i").out, Const("4i").out, Const("1i").out) // Counter
      val x2974 = CounterChain(name = "x2974", ctr3)
      var stage: List[Stage] = Nil
    }
    val x3132_leaf = UnitPipeline(name = "x3132_leaf", parent=x3132, deps=List("x3123_0", "x3130_0")) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3054_unitcc = CounterChain(name = "x3054_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x3098 = MetaPipeline(name = "x3098", parent=x3132, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr5 = (Const("0i").out, CU.scalarIn(stage0, x2972_scalar).out, Const("1i").out) // Counter
      val x2977 = CounterChain(name = "x2977", ctr5)
      var stage: List[Stage] = Nil
    }
    val x2992_0 = UnitPipeline(name = "x2992_0", parent=x3098, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3103 = CounterChain.copy("x3114_0", "x3103")
      val x2992_unitcc = CounterChain(name = "x2992_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x2977 = CounterChain.copy(x3098, "x2977")
      val x2878_x2986 = SRAM(size = 8000, writeCtr = x3103(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x2878_vector).rdAddr(x2977(0)).wtAddr(x3103(0))
      val x2878_x2989 = SRAM(size = 8000, writeCtr = x3103(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x2878_vector).wtAddr(x3103(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(x2878_x2986.load), op=Bypass, results=List(CU.scalarOut(stage(1), x2983_scalar)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x2977(0)), Const("1i")), op=FixSub, results=List(x2878_x2989.readAddr))
      Stage(stage(3), operands=List(x2878_x2989.load), op=Bypass, results=List(CU.scalarOut(stage(3), x2984_scalar)))
    }
    val x3005 = StreamController(name = "x3005", parent=x3098, deps=List(x2992_0)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3005_unitcc = CounterChain(name = "x3005_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x3005_0 = StreamPipeline(name = "x3005_0", parent=x3005, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3005_unitcc = CounterChain.copy(x3005, "x3005_unitcc")
      val x2877_x2995 = SemiFIFO(size = 8000, banking = Strided(1), buffering = SingleBuffer()).wtPort(x2903_mc.vdata)
      val x2876_x2998 = SemiFIFO(size = 8000, banking = Strided(1), buffering = SingleBuffer()).wtPort(x2947_mc.vdata)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(4)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x2983_scalar)), op=Bypass, results=List(x2877_x2995.readAddr))
      Stage(stage(2), operands=List(x2877_x2995.load), op=Bypass, results=List(CU.scalarOut(stage(2), x2981_scalar)))
      Stage(stage(3), operands=List(CU.scalarIn(stage(2), x2983_scalar)), op=Bypass, results=List(x2876_x2998.readAddr))
      Stage(stage(4), operands=List(x2876_x2998.load), op=Bypass, results=List(CU.scalarOut(stage(4), x2980_scalar)))
    }
    val x3005_1 = StreamPipeline(name = "x3005_1", parent=x3005, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3005_unitcc = CounterChain.copy(x3005, "x3005_unitcc")
      val x2876_x3002 = SemiFIFO(size = 8000, banking = Strided(1), buffering = SingleBuffer()).wtPort(x2947_mc.vdata)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x2984_scalar)), op=Bypass, results=List(x2876_x3002.readAddr))
      Stage(stage(2), operands=List(x2876_x3002.load), op=Bypass, results=List(CU.scalarOut(stage(2), x2982_scalar)))
    }
    val x3005_leaf = StreamPipeline(name = "x3005_leaf", parent=x3005, deps=List(x3005_0, x3005_1)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3005_unitcc = CounterChain.copy(x3005, "x3005_unitcc")
      var stage: List[Stage] = Nil
    }
    val x3056 = Sequential(name = "x3056", parent=x3098, deps=List(x3005)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3056_unitcc = CounterChain(name = "x3056_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x3054 = StreamController(name = "x3054", parent=x3056, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3054_unitcc = CounterChain(name = "x3054_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x3029_0 = UnitPipeline(name = "x3029_0", parent=x3054, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr266 = CU.temp
      val tr265 = CU.temp
      val tr263 = CU.temp
      val tr262 = CU.temp
      val tr260 = CU.temp
      val tr256 = CU.temp
      val x3029_unitcc = CounterChain(name = "x3029_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x2981_scalar), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr256)))
      Stage(stage(2), operands=List(CU.scalarIn(stage(1), x2981_scalar), CU.temp(stage(1), tr256)), op=FixSub, results=List(CU.scalarOut(stage(2), x3033_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr256), CU.scalarIn(stage(2), x2980_scalar)), op=FixAdd, results=List(CU.temp(stage(3), tr260)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr260), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr262)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr260), CU.temp(stage(4), tr262)), op=FixSub, results=List(CU.temp(stage(5), tr263)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr262), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr265)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr265), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr266)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr263), CU.temp(stage(7), tr266)), op=FixAdd, results=List(CU.scalarOut(stage(8), x3033_mc.len)))
    }
    val x3073_0 = Pipeline(name = "x3073_0", parent=x3098, deps=List(x3056)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr283 = CU.temp
      val tr282 = CU.temp
      val tr281 = CU.temp
      val x2977 = CounterChain.copy(x3098, "x2977")
      val ctr13 = (Const("0i").out, CU.scalarIn(stage0, x2980_scalar).out, Const("16i").out) // Counter
      val x3061 = CounterChain(name = "x3061", ctr13)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(4)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x2977(0)), Const("0i")), op=FixEql, results=List(CU.temp(stage(1), tr281)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr281), Const("0i"), CU.scalarIn(stage(1), x2982_scalar)), op=Mux, results=List(CU.temp(stage(2), tr282)))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x3061(0)), CU.scalarIn(stage(2), x2881_scalar)), op=FixAdd, results=List(CU.temp(stage(3), tr283)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr283), CU.temp(stage(3), tr282)), op=FixAdd, results=List(CU.vecOut(stage(4), x3057_vector)))
    }
    val x3090_0 = Pipeline(name = "x3090_0", parent=x3098, deps=List(x3073_0)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3061 = CounterChain.copy(x3073_0, "x3061")
      val ctr11 = (Const("0i").out, CU.scalarIn(stage0, x2980_scalar).out, Const("16i").out) // Counter
      val x3075 = CounterChain(name = "x3075", ctr11)
      val x2880_x3081 = SemiFIFO(size = 8000, banking = Strided(1), buffering = MultiBuffer(3, swapRead = x3075(0))).wtPort(x3033_mc.vdata).rdAddr(x3075(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x2880_x3081.load), op=Bypass, results=List(CU.vecOut(stage(1), x2875_vector)))
    }
    val x3096_0 = UnitPipeline(name = "x3096_0", parent=x3098, deps=List(x3090_0)) { implicit CU => 
      val stage0 = CU.emptyStage
      val ar301 = CU.accum(init = Const("0i"))
      val x3096_unitcc = CounterChain(name = "x3096_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x2980_scalar), CU.accum(stage(1), ar301)), op=FixAdd, results=List(CU.scalarOut(stage(1), x2881_scalar), CU.accum(stage(1), ar301)))
    }
    val x3114_0 = Pipeline(name = "x3114_0", parent=x3132, deps=List(x3098)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3061 = CounterChain.copy(x3073_0, "x3061")
      val x3075 = CounterChain.copy(x3090_0, "x3075")
      val ctr15 = (Const("0i").out, CU.scalarIn(stage0, x2881_scalar).out, Const("16i").out) // Counter
      val x3103 = CounterChain(name = "x3103", ctr15)
      val x2875_x3106 = SRAM(size = 8000, writeCtr = x3075(0), banking = Duplicated(), buffering = SingleBuffer()).wtPort(x2875_vector).rdAddr(x3103(0))
      val x3057_x3078_x3114 = SRAM(size = 8000, writeCtr = x3061(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x3075(0), swapWrite = x3061(0))).wtPort(x3057_vector).rdAddr(x3075(0)).wtAddr(x3061(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(1, List(x2875_x3106))
      Stage(stage(1), operands=List(x3057_x3078_x3114.load), op=Bypass, results=List(x2875_x3106.writeAddr))
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x2875_x3106.load), op=Bypass, results=List(CU.vecOut(stage(1), x2878_vector)))
    }
    val x3123_0 = Pipeline(name = "x3123_0", parent=x3132, deps=List(x3114_0)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x2974 = CounterChain.copy(x3132, "x2974")
      val ctr17 = (Const("0i").out, CU.scalarIn(stage0, x2881_scalar).out, Const("16i").out) // Counter
      val x3116 = CounterChain(name = "x3116", ctr17)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x2974(0)), Const("1i")), op=FixAdd, results=List(CU.vecOut(stage(1), x2879_vector)))
    }
    val x3124 = StreamController(name = "x3124", parent=x3132, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr19 = (Const("0i").out, CU.scalarIn(stage0, x2881_scalar).out, Const("1i").out) // Counter
      val x3124_cc = CounterChain(name = "x3124_cc", ctr19)
      var stage: List[Stage] = Nil
    }
    val x3124_0 = StreamPipeline(name = "x3124_0", parent=x3124, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3103 = CounterChain.copy(x3114_0, "x3103")
      val x3124_cc = CounterChain.copy(x3124, "x3124_cc")
      val x2878_x3124 = SRAM(size = 8000, writeCtr = x3103(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x2878_vector).rdAddr(x3124_cc(0)).wtAddr(x3103(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x2878_x3124.load), op=Bypass, results=List(CU.vecOut(stage(1), x3124_mc.addrs)))
    }
    val x3124_1 = StreamPipeline(name = "x3124_1", parent=x3124, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3116 = CounterChain.copy(x3123_0, "x3116")
      val x3124_cc = CounterChain.copy(x3124, "x3124_cc")
      val x2879_x3124 = SRAM(size = 8000, writeCtr = x3116(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x2879_vector).rdAddr(x3124_cc(0)).wtAddr(x3116(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x2879_x3124.load), op=Bypass, results=List(CU.vecOut(stage(1), x3124_mc.vdata)))
    }
    val x3130_0 = UnitPipeline(name = "x3130_0", parent=x3132, deps=List(x3124)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3130_unitcc = CounterChain(name = "x3130_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x2881_scalar)), op=Bypass, results=List(CU.scalarOut(stage(1), x2972_scalar)))
    }
    
  }
}
