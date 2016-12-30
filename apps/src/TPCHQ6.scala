import pir.graph
import pir.graph._
import pir.graph.enums._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.misc._
import pir.PIRApp

object TPCHQ6Design extends PIRApp {
  def main(args: String*)(top:Top) = {
    val x3272_oc = OffChip("x3272")
    val x3470_scalar = Scalar("x3470")
    val x3271_oc = OffChip("x3271")
    val x3270_oc = OffChip("x3270")
    val bus_216_vector = Vector("bus_216")
    val x3269_oc = OffChip("x3269")
    val x3273_argout = ArgOut("x3273")
    val x3264_argin = ArgIn("x3264")
    val bus_205_vector = Vector("bus_205")
    val x3446_mc = MemoryController(TileLoad, x3272_oc)
    val x3311_mc = MemoryController(TileLoad, x3269_oc)
    val x3356_mc = MemoryController(TileLoad, x3270_oc)
    val x3401_mc = MemoryController(TileLoad, x3271_oc)
    val x3518 = Sequential(name = "x3518", parent=top, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3518_unitcc = CounterChain(name = "x3518_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x3512 = MetaPipeline(name = "x3512", parent=x3518, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr1 = (Const("0i").out, CU.scalarIn(stage0, x3264_argin).out, Const("8000i").out) // Counter
      val x3281 = CounterChain(name = "x3281", ctr1)
      var stage: List[Stage] = Nil
    }
    val x3332 = StreamController(name = "x3332", parent=x3512, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3332_unitcc = CounterChain(name = "x3332_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x3307_0 = UnitPipeline(name = "x3307_0", parent=x3332, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr105 = CU.temp
      val tr104 = CU.temp
      val tr102 = CU.temp
      val tr101 = CU.temp
      val tr99 = CU.temp
      val tr94 = CU.temp
      val x3281 = CounterChain.copy(x3512, "x3281")
      val x3307_unitcc = CounterChain(name = "x3307_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x3281(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr94)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x3281(0)), CU.temp(stage(1), tr94)), op=FixSub, results=List(CU.scalarOut(stage(2), x3311_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr94), Const("8000i")), op=FixAdd, results=List(CU.temp(stage(3), tr99)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr99), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr101)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr99), CU.temp(stage(4), tr101)), op=FixSub, results=List(CU.temp(stage(5), tr102)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr101), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr104)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr104), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr105)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr102), CU.temp(stage(7), tr105)), op=FixAdd, results=List(CU.scalarOut(stage(8), x3311_mc.len)))
    }
    val x3377 = StreamController(name = "x3377", parent=x3512, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3377_unitcc = CounterChain(name = "x3377_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x3352_0 = UnitPipeline(name = "x3352_0", parent=x3377, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr128 = CU.temp
      val tr127 = CU.temp
      val tr125 = CU.temp
      val tr124 = CU.temp
      val tr122 = CU.temp
      val tr117 = CU.temp
      val x3281 = CounterChain.copy(x3512, "x3281")
      val x3352_unitcc = CounterChain(name = "x3352_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x3281(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr117)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x3281(0)), CU.temp(stage(1), tr117)), op=FixSub, results=List(CU.scalarOut(stage(2), x3356_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr117), Const("8000i")), op=FixAdd, results=List(CU.temp(stage(3), tr122)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr122), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr124)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr122), CU.temp(stage(4), tr124)), op=FixSub, results=List(CU.temp(stage(5), tr125)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr124), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr127)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr127), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr128)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr125), CU.temp(stage(7), tr128)), op=FixAdd, results=List(CU.scalarOut(stage(8), x3356_mc.len)))
    }
    val x3422 = StreamController(name = "x3422", parent=x3512, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3422_unitcc = CounterChain(name = "x3422_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x3397_0 = UnitPipeline(name = "x3397_0", parent=x3422, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr151 = CU.temp
      val tr150 = CU.temp
      val tr148 = CU.temp
      val tr147 = CU.temp
      val tr145 = CU.temp
      val tr140 = CU.temp
      val x3281 = CounterChain.copy(x3512, "x3281")
      val x3397_unitcc = CounterChain(name = "x3397_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x3281(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr140)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x3281(0)), CU.temp(stage(1), tr140)), op=FixSub, results=List(CU.scalarOut(stage(2), x3401_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr140), Const("8000i")), op=FixAdd, results=List(CU.temp(stage(3), tr145)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr145), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr147)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr145), CU.temp(stage(4), tr147)), op=FixSub, results=List(CU.temp(stage(5), tr148)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr147), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr150)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr150), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr151)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr148), CU.temp(stage(7), tr151)), op=FixAdd, results=List(CU.scalarOut(stage(8), x3401_mc.len)))
    }
    val x3467 = StreamController(name = "x3467", parent=x3512, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3467_unitcc = CounterChain(name = "x3467_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x3442_0 = UnitPipeline(name = "x3442_0", parent=x3467, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr174 = CU.temp
      val tr173 = CU.temp
      val tr171 = CU.temp
      val tr170 = CU.temp
      val tr168 = CU.temp
      val tr163 = CU.temp
      val x3281 = CounterChain.copy(x3512, "x3281")
      val x3442_unitcc = CounterChain(name = "x3442_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x3281(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr163)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x3281(0)), CU.temp(stage(1), tr163)), op=FixSub, results=List(CU.scalarOut(stage(2), x3446_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr163), Const("8000i")), op=FixAdd, results=List(CU.temp(stage(3), tr168)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr168), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr170)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr168), CU.temp(stage(4), tr170)), op=FixSub, results=List(CU.temp(stage(5), tr171)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr170), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr173)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr173), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr174)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr171), CU.temp(stage(7), tr174)), op=FixAdd, results=List(CU.scalarOut(stage(8), x3446_mc.len)))
    }
    val x3504 = StreamController(name = "x3504", parent=x3512, deps=List(x3332, x3377, x3422, x3467)) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr5 = (Const("0i").out, Const("8000i").out, Const("1i").out) // Counter
      val x3472 = CounterChain(name = "x3472", ctr5)
      var stage: List[Stage] = Nil
    }
    val x3504_0 = StreamPipeline(name = "x3504_0", parent=x3504, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr204 = CU.temp
      val tr202 = CU.temp
      val x3472 = CounterChain.copy(x3504, "x3472")
      val x3332_unitcc = CounterChain.copy(x3332,"x3332_unitcc")
      val x3284_x3475 = SemiFIFO(size = 8000, banking = Strided(1), buffering = MultiBuffer(2, swapWrite=x3332_unitcc(0),swapRead = x3472(0))).wtPort(x3311_mc.dataOut).rdAddr(x3472(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(Const("0i"), x3284_x3475.load), op=FixLt, results=List(CU.temp(stage(1), tr202)))
      Stage(stage(2), operands=List(CU.load(stage(1), x3284_x3475), Const("9999i")), op=FixLt, results=List(CU.temp(stage(2), tr204)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr202), CU.temp(stage(2), tr204)), op=BitAnd, results=List(CU.vecOut(stage(3), bus_205_vector)))
    }
    val x3504_1 = StreamPipeline(name = "x3504_1", parent=x3504, deps=List(x3504_0)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr214 = CU.temp
      val tr213 = CU.temp
      val tr212 = CU.temp
      val tr210 = CU.temp
      val tr209 = CU.temp
      val tr207 = CU.temp
      val tr206 = CU.temp
      val x3472 = CounterChain.copy(x3504, "x3472")
      val x3285_x3481 = SemiFIFO(size = 8000, banking = Strided(1), buffering = SingleBuffer()).wtPort(x3356_mc.dataOut).rdAddr(x3472(0))
      val x3286_x3478 = SemiFIFO(size = 8000, banking = Strided(1), buffering = SingleBuffer()).wtPort(x3401_mc.dataOut).rdAddr(x3472(0))
      val x3287_x3484 = SemiFIFO(size = 8000, banking = Strided(1), buffering = SingleBuffer()).wtPort(x3446_mc.dataOut).rdAddr(x3472(0))
      val bus_205_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_205_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(Const("0i"), x3286_x3478.load), op=FixLeq, results=List(CU.temp(stage(1), tr206)))
      Stage(stage(2), operands=List(bus_205_fifo.load, CU.temp(stage(1), tr206)), op=BitAnd, results=List(CU.temp(stage(2), tr207)))
      Stage(stage(3), operands=List(CU.load(stage(2), x3286_x3478), Const("9999i")), op=FixLeq, results=List(CU.temp(stage(3), tr209)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr207), CU.temp(stage(3), tr209)), op=BitAnd, results=List(CU.temp(stage(4), tr210)))
      Stage(stage(5), operands=List(CU.load(stage(4), x3285_x3481), Const("24i")), op=FixLt, results=List(CU.temp(stage(5), tr212)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr210), CU.temp(stage(5), tr212)), op=BitAnd, results=List(CU.temp(stage(6), tr213)))
      Stage(stage(7), operands=List(CU.load(stage(6), x3287_x3484), CU.load(stage(6), x3286_x3478)), op=FixMul, results=List(CU.temp(stage(7), tr214)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr213), CU.temp(stage(7), tr214), Const("0i")), op=Mux, results=List(CU.vecOut(stage(8), bus_216_vector)))
    }
    val x3504_2 = StreamPipeline(name = "x3504_2", parent=x3504, deps=List(x3504_1)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3472 = CounterChain.copy(x3504, "x3472")
      val bus_216_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_216_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(bus_216_fifo.load), op=Bypass, results=List(CU.reduce(stage(1))))
      val (rs1, rr218) = Stage.reduce(op=FixAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr218), op=Bypass, results=List(CU.scalarOut(stage(2), x3470_scalar)))
    }
    val x3510_0 = UnitPipeline(name = "x3510_0", parent=x3512, deps=List(x3504)) { implicit CU => 
      val stage0 = CU.emptyStage
      val ar222 = CU.accum(init = Const("0i"))
      val x3510_unitcc = CounterChain(name = "x3510_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x3470_scalar), CU.accum(stage(1), ar222)), op=FixAdd, results=List(CU.scalarOut(stage(1), x3273_argout), CU.accum(stage(1), ar222)))
    }
    
  }
}
