import pir.graph
import pir.graph._
import pir.graph.enums._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.misc._
import pir.PIRApp

object OuterProductDesign extends PIRApp {
  def main(args: String*)(top:Top) = {
    val x3167_oc = OffChip("x3167")
    val x3162_argin = ArgIn("x3162")
    val x3254_x3375_data_vector = Vector("x3254_x3375_data")
    val x3255_vector = Vector("x3255")
    val x3257_scalar = Scalar("x3257")
    val x3169_oc = OffChip("x3169")
    val x3253_x3372_data_vector = Vector("x3253_x3372_data")
    val x3171_oc = OffChip("x3171")
    val x3163_argin = ArgIn("x3163")
    val x3256_scalar = Scalar("x3256")
    val x3407_mc = MemoryController(TileStore, x3171_oc)
    val x3281_mc = MemoryController(TileLoad, x3167_oc)
    val x3326_mc = MemoryController(TileLoad, x3169_oc)
    val x3413 = Sequential(name = "x3413", parent=top, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3413_unitcc = CounterChain(name = "x3413_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x3411 = MetaPipeline(name = "x3411", parent=x3413, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr1 = (Const("0i").out, CU.scalarIn(stage0, x3162_argin).out, Const("64i").out) // Counter
      val ctr2 = (Const("0i").out, CU.scalarIn(stage0, x3163_argin).out, Const("64i").out) // Counter
      val x3252 = CounterChain(name = "x3252", ctr1, ctr2)
      var stage: List[Stage] = Nil
    }
    val x3302 = StreamController(name = "x3302", parent=x3411, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3302_unitcc = CounterChain(name = "x3302_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x3277_0 = UnitPipeline(name = "x3277_0", parent=x3302, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr147 = CU.temp
      val tr146 = CU.temp
      val tr144 = CU.temp
      val tr143 = CU.temp
      val tr141 = CU.temp
      val tr137 = CU.temp
      val x3252 = CounterChain.copy(x3411, "x3252")
      val x3277_unitcc = CounterChain(name = "x3277_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x3252(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr137)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x3252(0)), CU.temp(stage(1), tr137)), op=FixSub, results=List(CU.scalarOut(stage(2), x3281_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr137), Const("64i")), op=FixAdd, results=List(CU.temp(stage(3), tr141)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr141), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr143)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr141), CU.temp(stage(4), tr143)), op=FixSub, results=List(CU.temp(stage(5), tr144)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr143), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr146)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr146), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr147)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr144), CU.temp(stage(7), tr147)), op=FixAdd, results=List(CU.scalarOut(stage(8), x3281_mc.len)))
    }
    val x3347 = StreamController(name = "x3347", parent=x3411, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3347_unitcc = CounterChain(name = "x3347_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x3322_0 = UnitPipeline(name = "x3322_0", parent=x3347, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr170 = CU.temp
      val tr169 = CU.temp
      val tr167 = CU.temp
      val tr166 = CU.temp
      val tr164 = CU.temp
      val tr160 = CU.temp
      val x3252 = CounterChain.copy(x3411, "x3252")
      val x3322_unitcc = CounterChain(name = "x3322_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x3252(1)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr160)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x3252(1)), CU.temp(stage(1), tr160)), op=FixSub, results=List(CU.scalarOut(stage(2), x3326_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr160), Const("64i")), op=FixAdd, results=List(CU.temp(stage(3), tr164)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr164), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr166)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr164), CU.temp(stage(4), tr166)), op=FixSub, results=List(CU.temp(stage(5), tr167)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr166), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr169)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr169), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr170)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr167), CU.temp(stage(7), tr170)), op=FixAdd, results=List(CU.scalarOut(stage(8), x3326_mc.len)))
    }
    val x3353_0 = UnitPipeline(name = "x3353_0", parent=x3411, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr183 = CU.temp
      val x3252 = CounterChain.copy(x3411, "x3252")
      val x3353_unitcc = CounterChain(name = "x3353_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x3162_argin), CU.ctr(stage(0), x3252(0))), op=FixSub, results=List(CU.temp(stage(1), tr183)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr183), Const("64i")), op=FixMin, results=List(CU.scalarOut(stage(2), x3256_scalar)))
    }
    val x3359_0 = UnitPipeline(name = "x3359_0", parent=x3411, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr188 = CU.temp
      val x3252 = CounterChain.copy(x3411, "x3252")
      val x3359_unitcc = CounterChain(name = "x3359_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x3163_argin), CU.ctr(stage(0), x3252(1))), op=FixSub, results=List(CU.temp(stage(1), tr188)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr188), Const("64i")), op=FixMin, results=List(CU.scalarOut(stage(2), x3407_mc.len), CU.scalarOut(stage(2), x3257_scalar)))
    }
    val x3385 = StreamController(name = "x3385", parent=x3411, deps=List(x3302, x3347, x3353_0, x3359_0)) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr7 = (Const("0i").out, CU.scalarIn(stage0, x3256_scalar).out, Const("1i").out) // Counter
      val ctr8 = (Const("0i").out, CU.scalarIn(stage0, x3257_scalar).out, Const("1i").out) // Counter
      val x3369 = CounterChain(name = "x3369", ctr7, ctr8)
      var stage: List[Stage] = Nil
    }
    val x3385_0 = StreamPipeline(name = "x3385_0", parent=x3385, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3369 = CounterChain.copy(x3385, "x3369")
      val x3252 = CounterChain.copy(x3411, "x3252")
      val x3253_x3372 = SemiFIFO(size = 64, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x3369(0), swapWrite = x3252(0))).wtPort(x3281_mc.vdata).rdAddr(x3369(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x3253_x3372.load), op=Bypass, results=List(CU.vecOut(stage(1), x3253_x3372_data_vector)))
    }
    val x3385_1 = StreamPipeline(name = "x3385_1", parent=x3385, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3369 = CounterChain.copy(x3385, "x3369")
      val x3252 = CounterChain.copy(x3411, "x3252")
      val x3254_x3375 = SemiFIFO(size = 64, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x3369(0), swapWrite = x3252(0))).wtPort(x3326_mc.vdata).rdAddr(x3369(1))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x3254_x3375.load), op=Bypass, results=List(CU.vecOut(stage(1), x3254_x3375_data_vector)))
    }
    val x3385_2 = StreamPipeline(name = "x3385_2", parent=x3385, deps=List(x3385_0, x3385_1)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3369 = CounterChain.copy(x3385, "x3369")
      val x3253_x3372_data_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x3253_x3372_data_vector)
      val x3254_x3375_data_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x3254_x3375_data_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x3253_x3372_data_fifo.load, x3254_x3375_data_fifo.load), op=FixMul, results=List(CU.vecOut(stage(1), x3255_vector)))
    }
    val x3409 = StreamController(name = "x3409", parent=x3411, deps=List(x3385)) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr5 = (Const("0i").out, CU.scalarIn(stage0, x3256_scalar).out, Const("1i").out) // Counter
      val x3388 = CounterChain(name = "x3388", ctr5)
      var stage: List[Stage] = Nil
    }
    val x3411_leafX = Pipeline(name = "x3411_leafX", parent=x3411, deps=List(x3409)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3394_unitcc = CounterChain(name = "x3394_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x3394_0 = UnitPipeline(name = "x3394_0", parent=x3409, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr210 = CU.temp
      val tr209 = CU.temp
      val x3252 = CounterChain.copy(x3411, "x3252")
      val x3388 = CounterChain.copy(x3409, "x3388")
      val x3394_unitcc = CounterChain(name = "x3394_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x3252(0)), CU.ctr(stage(0), x3388(0))), op=FixAdd, results=List(CU.temp(stage(1), tr209)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr209), CU.scalarIn(stage(1), x3163_argin)), op=FixMul, results=List(CU.temp(stage(2), tr210)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr210), CU.ctr(stage(2), x3252(1))), op=FixAdd, results=List(CU.scalarOut(stage(3), x3407_mc.ofs)))
    }
    val x3405_0 = Pipeline(name = "x3405_0", parent=x3409, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr218 = CU.temp
      val tr215 = CU.temp
      val tr214 = CU.temp
      val x3369 = CounterChain.copy(x3385, "x3369")
      val ctr15 = (Const("0i").out, CU.scalarIn(stage0, x3257_scalar).out, Const("16i").out) // Counter
      val x3396 = CounterChain(name = "x3396", ctr15)
      val x3388 = CounterChain.copy(x3409, "x3388")
      val x3255_x3399 = SRAM(size = 4096, writeCtr = x3369(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x3388(0), swapWrite = x3369(0))).wtPort(x3255_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x3255_x3399))
      Stage(stage(1), operands=List(x3369(0), Const("64i")), op=FixMul, results=List(CU.temp(stage(1), tr214)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr214), CU.ctr(stage(1), x3369(1))), op=FixAdd, results=List(x3255_x3399.writeAddr, CU.temp(stage(2), tr215)))
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x3388(0)), Const("64i")), op=FixMul, results=List(CU.temp(stage(1), tr218)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr218), CU.ctr(stage(1), x3396(0))), op=FixAdd, results=List(x3255_x3399.readAddr))
      Stage(stage(3), operands=List(x3255_x3399.load), op=Bypass, results=List(CU.vecOut(stage(3), x3407_mc.vdata)))
    }
    
  }
}
