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
    val x3173_oc = OffChip("x3173")
    val x3259_scalar = Scalar("x3259")
    val x3257_vector = Vector("x3257")
    val x3164_argin = ArgIn("x3164")
    val x3169_oc = OffChip("x3169")
    val x3171_oc = OffChip("x3171")
    val x3165_argin = ArgIn("x3165")
    val x3258_scalar = Scalar("x3258")
    val x3409_mc = MemoryController(TileStore, x3173_oc)
    val x3328_mc = MemoryController(TileLoad, x3171_oc)
    val x3283_mc = MemoryController(TileLoad, x3169_oc)
    val x3415 = Sequential(name = "x3415", parent=top, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3415_unitcc = CounterChain(name = "x3415_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x3413 = MetaPipeline(name = "x3413", parent=x3415, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr1 = (Const("0i").out, CU.scalarIn(stage0, x3164_argin).out, Const("192i").out) // Counter
      val ctr2 = (Const("0i").out, CU.scalarIn(stage0, x3165_argin).out, Const("384i").out) // Counter
      val x3254 = CounterChain(name = "x3254", ctr1, ctr2)
      var stage: List[Stage] = Nil
    }
    val x3304 = StreamController(name = "x3304", parent=x3413, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3304_unitcc = CounterChain(name = "x3304_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x3279_0 = UnitPipeline(name = "x3279_0", parent=x3304, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr149 = CU.temp
      val tr148 = CU.temp
      val tr146 = CU.temp
      val tr145 = CU.temp
      val tr143 = CU.temp
      val tr138 = CU.temp
      val x3254 = CounterChain.copy(x3413, "x3254")
      val x3279_unitcc = CounterChain(name = "x3279_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x3254(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr138)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x3254(0)), CU.temp(stage(1), tr138)), op=FixSub, results=List(CU.scalarOut(stage(2), x3283_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr138), Const("192i")), op=FixAdd, results=List(CU.temp(stage(3), tr143)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr143), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr145)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr143), CU.temp(stage(4), tr145)), op=FixSub, results=List(CU.temp(stage(5), tr146)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr145), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr148)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr148), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr149)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr146), CU.temp(stage(7), tr149)), op=FixAdd, results=List(CU.scalarOut(stage(8), x3283_mc.len)))
    }
    val x3349 = StreamController(name = "x3349", parent=x3413, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3349_unitcc = CounterChain(name = "x3349_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x3324_0 = UnitPipeline(name = "x3324_0", parent=x3349, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr173 = CU.temp
      val tr172 = CU.temp
      val tr170 = CU.temp
      val tr169 = CU.temp
      val tr167 = CU.temp
      val tr162 = CU.temp
      val x3254 = CounterChain.copy(x3413, "x3254")
      val x3324_unitcc = CounterChain(name = "x3324_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x3254(1)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr162)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x3254(1)), CU.temp(stage(1), tr162)), op=FixSub, results=List(CU.scalarOut(stage(2), x3328_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr162), Const("384i")), op=FixAdd, results=List(CU.temp(stage(3), tr167)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr167), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr169)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr167), CU.temp(stage(4), tr169)), op=FixSub, results=List(CU.temp(stage(5), tr170)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr169), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr172)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr172), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr173)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr170), CU.temp(stage(7), tr173)), op=FixAdd, results=List(CU.scalarOut(stage(8), x3328_mc.len)))
    }
    val x3355_0 = UnitPipeline(name = "x3355_0", parent=x3413, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr186 = CU.temp
      val x3254 = CounterChain.copy(x3413, "x3254")
      val x3355_unitcc = CounterChain(name = "x3355_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x3164_argin), CU.ctr(stage(0), x3254(0))), op=FixSub, results=List(CU.temp(stage(1), tr186)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr186), Const("192i")), op=FltMin, results=List(CU.scalarOut(stage(2), x3258_scalar)))
    }
    val x3361_0 = UnitPipeline(name = "x3361_0", parent=x3413, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr191 = CU.temp
      val x3254 = CounterChain.copy(x3413, "x3254")
      val x3361_unitcc = CounterChain(name = "x3361_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x3165_argin), CU.ctr(stage(0), x3254(1))), op=FixSub, results=List(CU.temp(stage(1), tr191)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr191), Const("384i")), op=FltMin, results=List(CU.scalarOut(stage(2), x3409_mc.len), CU.scalarOut(stage(2), x3259_scalar)))
    }
    val x3387_0 = Pipeline(name = "x3387_0", parent=x3413, deps=List(x3304, x3349, x3355_0)) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr7 = (Const("0i").out, CU.scalarIn(stage0, x3258_scalar).out, Const("1i").out) // Counter
      val ctr8 = (Const("0i").out, CU.scalarIn(stage0, x3259_scalar).out, Const("16i").out) // Counter
      val x3371 = CounterChain(name = "x3371", ctr7, ctr8)
      val x3255_x3374 = SemiFIFO(size = 192, banking = Strided(1), buffering = MultiBuffer(2, swapWrite = x3371(0), swapRead = x3371(0))).wtPort(x3283_mc.dataOut).rdAddr(x3371(0))
      val x3256_x3377 = SemiFIFO(size = 384, banking = Strided(1), buffering = MultiBuffer(2, swapWrite = x3371(0), swapRead = x3371(0))).wtPort(x3328_mc.dataOut).rdAddr(x3371(1))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x3255_x3374.load, x3256_x3377.load), op=FixMul, results=List(CU.vecOut(stage(1), x3257_vector)))
    }
    val x3411 = StreamController(name = "x3411", parent=x3413, deps=List(x3387_0, x3361_0)) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr5 = (Const("0i").out, CU.scalarIn(stage0, x3258_scalar).out, Const("1i").out) // Counter
      val x3390 = CounterChain(name = "x3390", ctr5)
      var stage: List[Stage] = Nil
    }
    val x3396_0 = UnitPipeline(name = "x3396_0", parent=x3411, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr213 = CU.temp
      val tr212 = CU.temp
      val x3254 = CounterChain.copy(x3413, "x3254")
      val x3390 = CounterChain.copy(x3411, "x3390")
      val x3396_unitcc = CounterChain(name = "x3396_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x3254(0)), CU.ctr(stage(0), x3390(0))), op=FixAdd, results=List(CU.temp(stage(1), tr212)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr212), CU.scalarIn(stage(1), x3165_argin)), op=FixMul, results=List(CU.temp(stage(2), tr213)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr213), CU.ctr(stage(2), x3254(1))), op=FixAdd, results=List(CU.scalarOut(stage(3), x3409_mc.ofs)))
    }
    val x3407_0 = Pipeline(name = "x3407_0", parent=x3411, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr221 = CU.temp
      val x3390 = CounterChain.copy(x3411, "x3390")
      val x3371 = CounterChain.copy(x3387_0, "x3371")
      val ctr15 = (Const("0i").out, CU.scalarIn(stage0, x3259_scalar).out, Const("16i").out) // Counter
      val x3398 = CounterChain(name = "x3398", ctr15)
      val x3257_x3401 = SRAM(size = 73728, writeCtr = x3371(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x3390(0), swapWrite = x3371(0))).wtPort(x3257_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x3257_x3401))
      val tr217 = CU.temp 
      Stage(stage(1), operands=List(x3371(0), Const("384i")), op=FixMul, results=List(CU.temp(stage(1), tr217)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr217), CU.ctr(stage(1), x3371(1))), op=FixAdd, results=List(x3257_x3401.writeAddr))
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x3390(0)), Const("384i")), op=FixMul, results=List(CU.temp(stage(1), tr221)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr221), CU.ctr(stage(1), x3398(0))), op=FixAdd, results=List(x3257_x3401.readAddr))
      Stage(stage(3), operands=List(x3257_x3401.load), op=Bypass, results=List(CU.vecOut(stage(3), x3409_mc.vdata)))
    }
    
  }
}
