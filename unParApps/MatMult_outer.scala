import pir.graph
import pir.graph._
import pir.graph.enums._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.misc._
import pir.PIRApp

object MatMult_outerDesign extends PIRApp {
  def main(args: String*)(top:Top) = {
    val x5940_vector = Vector("x5940")
    val x5592_oc = OffChip("x5592")
    val x5579_argin = ArgIn("x5579")
    val x5588_oc = OffChip("x5588")
    val x5580_argin = ArgIn("x5580")
    val x5595_oc = OffChip("x5595")
    val x6053_vector = Vector("x6053")
    val x5581_argin = ArgIn("x5581")
    val x6024_mc = MemoryController(TileLoad, x5592_oc)
    val x5974_mc = MemoryController(TileLoad, x5588_oc)
    val x6115_mc = MemoryController(TileStore, x5595_oc)
    val x6121 = Sequential(name = "x6121", parent=top, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x6121_unitcc = CounterChain(name = "x6121_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x6119 = MetaPipeline(name = "x6119", parent=x6121, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr1 = (Const("0i").out, CU.scalarIn(stage0, x5579_argin).out, Const("8i").out) // Counter
      val ctr2 = (Const("0i").out, CU.scalarIn(stage0, x5580_argin).out, Const("192i").out) // Counter
      val x5939 = CounterChain(name = "x5939", ctr1, ctr2)
      var stage: List[Stage] = Nil
    }
    val x6093 = MetaPipeline(name = "x6093", parent=x6119, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr5 = (Const("0i").out, CU.scalarIn(stage0, x5581_argin).out, Const("192i").out) // Counter
      val x5943 = CounterChain(name = "x5943", ctr5)
      var stage: List[Stage] = Nil
    }
    val x5995 = StreamController(name = "x5995", parent=x6093, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr7 = (Const("0i").out, Const("8i").out, Const("1i").out) // Counter
      val x5948 = CounterChain(name = "x5948", ctr7)
      var stage: List[Stage] = Nil
    }
    val x5970_0 = UnitPipeline(name = "x5970_0", parent=x5995, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr265 = CU.temp
      val tr264 = CU.temp
      val tr262 = CU.temp
      val tr261 = CU.temp
      val tr259 = CU.temp
      val tr254 = CU.temp
      val tr252 = CU.temp
      val tr251 = CU.temp
      val tr250 = CU.temp
      val x5943 = CounterChain.copy(x6093, "x5943")
      val x5970_unitcc = CounterChain(name = "x5970_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x5948 = CounterChain.copy(x5995, "x5948")
      val x5939 = CounterChain.copy(x6119, "x5939")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(11)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x5939(0)), CU.ctr(stage(0), x5948(0))), op=FixAdd, results=List(CU.temp(stage(1), tr250)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr250), CU.scalarIn(stage(1), x5581_argin)), op=FixMul, results=List(CU.temp(stage(2), tr251)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr251), CU.ctr(stage(2), x5943(0))), op=FixAdd, results=List(CU.temp(stage(3), tr252)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr252), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr254)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr252), CU.temp(stage(4), tr254)), op=FixSub, results=List(CU.scalarOut(stage(5), x5974_mc.ofs)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr254), Const("192i")), op=FixAdd, results=List(CU.temp(stage(6), tr259)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr259), Const("64i")), op=FixMod, results=List(CU.temp(stage(7), tr261)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr259), CU.temp(stage(7), tr261)), op=FixSub, results=List(CU.temp(stage(8), tr262)))
      Stage(stage(9), operands=List(CU.temp(stage(8), tr261), Const("0i")), op=FixNeq, results=List(CU.temp(stage(9), tr264)))
      Stage(stage(10), operands=List(CU.temp(stage(9), tr264), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(10), tr265)))
      Stage(stage(11), operands=List(CU.temp(stage(10), tr262), CU.temp(stage(10), tr265)), op=FixAdd, results=List(CU.scalarOut(stage(11), x5974_mc.len)))
    }
    val x6045 = StreamController(name = "x6045", parent=x6093, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr9 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      val x5998 = CounterChain(name = "x5998", ctr9)
      var stage: List[Stage] = Nil
    }
    val x6020_0 = UnitPipeline(name = "x6020_0", parent=x6045, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr295 = CU.temp
      val tr294 = CU.temp
      val tr292 = CU.temp
      val tr291 = CU.temp
      val tr289 = CU.temp
      val tr284 = CU.temp
      val tr282 = CU.temp
      val tr281 = CU.temp
      val tr280 = CU.temp
      val x5943 = CounterChain.copy(x6093, "x5943")
      val x5998 = CounterChain.copy(x6045, "x5998")
      val x6020_unitcc = CounterChain(name = "x6020_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x5939 = CounterChain.copy(x6119, "x5939")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(11)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x5943(0)), CU.ctr(stage(0), x5998(0))), op=FixAdd, results=List(CU.temp(stage(1), tr280)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr280), CU.scalarIn(stage(1), x5580_argin)), op=FixMul, results=List(CU.temp(stage(2), tr281)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr281), CU.ctr(stage(2), x5939(1))), op=FixAdd, results=List(CU.temp(stage(3), tr282)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr282), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr284)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr282), CU.temp(stage(4), tr284)), op=FixSub, results=List(CU.scalarOut(stage(5), x6024_mc.ofs)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr284), Const("192i")), op=FixAdd, results=List(CU.temp(stage(6), tr289)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr289), Const("64i")), op=FixMod, results=List(CU.temp(stage(7), tr291)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr289), CU.temp(stage(7), tr291)), op=FixSub, results=List(CU.temp(stage(8), tr292)))
      Stage(stage(9), operands=List(CU.temp(stage(8), tr291), Const("0i")), op=FixNeq, results=List(CU.temp(stage(9), tr294)))
      Stage(stage(10), operands=List(CU.temp(stage(9), tr294), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(10), tr295)))
      Stage(stage(11), operands=List(CU.temp(stage(10), tr292), CU.temp(stage(10), tr295)), op=FixAdd, results=List(CU.scalarOut(stage(11), x6024_mc.len)))
    }
    val x6091 = MetaPipeline(name = "x6091", parent=x6093, deps=List(x5995, x6045)) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr17 = (Const("0i").out, Const("8i").out, Const("1i").out) // Counter
      val ctr18 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      val x6052 = CounterChain(name = "x6052", ctr17, ctr18)
      val ctr15 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      val x6049 = CounterChain(name = "x6049", ctr15)
      var stage: List[Stage] = Nil
    }
    val x6072_0 = Pipeline(name = "x6072_0", parent=x6091, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr317 = CU.temp
      val tr313 = CU.temp
      val x6049 = CounterChain.copy(x6091, "x6049")
      val x6052 = CounterChain.copy(x6091, "x6052")
      val ctr19 = (Const("0i").out, Const("8i").out, Const("1i").out) // Counter
      val ctr20 = (Const("0i").out, Const("192i").out, Const("16i").out) // Counter
      val x6056 = CounterChain(name = "x6056", ctr19, ctr20)
      val x5945_x6062 = SemiFIFO(size = 36864, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x6052(0))).wtPort(x6024_mc.vdata)
      val x5944_x6059 = SemiFIFO(size = 1536, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x6052(0))).wtPort(x5974_mc.vdata)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(5)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x6056(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr313)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr313), CU.ctr(stage(1), x6049(0))), op=FixAdd, results=List(x5944_x6059.readAddr))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x6049(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(3), tr317)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr317), CU.ctr(stage(3), x6056(1))), op=FixAdd, results=List(x5945_x6062.readAddr))
      Stage(stage(5), operands=List(CU.load(stage(4), x5944_x6059), x5945_x6062.load), op=FixMul, results=List(CU.vecOut(stage(5), x6053_vector)))
    }
    val x6089_0 = Pipeline(name = "x6089_0", parent=x6091, deps=List(x6072_0)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr346 = CU.temp
      val tr335 = CU.temp
      val tr331 = CU.temp
      val tr329 = CU.temp
      val tr328 = CU.temp
      val x60521 = CounterChain.copy(x6091, "x6052")
      val ctr25 = (Const("0i").out, Const("8i").out, Const("1i").out) // Counter
      val ctr26 = (Const("0i").out, Const("192i").out, Const("16i").out) // Counter
      val x6052 = CounterChain(name = "x6052", ctr25, ctr26)
      val x6056 = CounterChain.copy(x6072_0, "x6056")
      val x6053_x6075 = SRAM(size = 1536, writeCtr = x6056(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x6052(0), swapWrite = x6056(0))).wtPort(x6053_vector)
      val x5940_x6078 = SRAM(size = 1536, writeCtr = x6052(0), banking = Strided(1), buffering = SingleBuffer())
      val wr348 = CU.wtAddr(x5940_x6078)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x6053_x6075))
      Stage(stage(1), operands=List(x6056(0), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr328)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr328), CU.ctr(stage(1), x6056(1))), op=FixAdd, results=List(x6053_x6075.writeAddr, CU.temp(stage(2), tr329)))
      stage = stage0 +: Stages(7)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x6052(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr331)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr331), CU.ctr(stage(1), x6052(1))), op=FixAdd, results=List(x6053_x6075.readAddr))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x6052(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(3), tr335)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr335), CU.ctr(stage(3), x6052(1))), op=FixAdd, results=List(x5940_x6078.readAddr))
      Stage(stage(5), operands=List(CU.load(stage(4), x6053_x6075), x5940_x6078.load), op=FixAdd, results=List(CU.vecOut(stage(5), x5940_vector), CU.store(stage(5), x5940_x6078)))
      Stage(stage(6), operands=List(CU.ctr(stage(5), x6052(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(6), tr346)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr346), CU.ctr(stage(6), x6052(1))), op=FixAdd, results=List(CU.wtAddr(stage(7), wr348)))
    }
    val x6117 = StreamController(name = "x6117", parent=x6119, deps=List(x6093)) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr11 = (Const("0i").out, Const("8i").out, Const("1i").out) // Counter
      val x6096 = CounterChain(name = "x6096", ctr11)
      var stage: List[Stage] = Nil
    }
    val x6119_leafX = Pipeline(name = "x6119_leafX", parent=x6119, deps=List(x6117)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x5939 = CounterChain.copy(x6119, "x5939")
      var stage: List[Stage] = Nil
    }
    val x6102_0 = UnitPipeline(name = "x6102_0", parent=x6117, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr353 = CU.temp
      val tr352 = CU.temp
      val x5939 = CounterChain.copy(x6119, "x5939")
      val x6096 = CounterChain.copy(x6117, "x6096")
      val x6102_unitcc = CounterChain(name = "x6102_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(4)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x5939(0)), CU.ctr(stage(0), x6096(0))), op=FixAdd, results=List(CU.temp(stage(1), tr352)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr352), CU.scalarIn(stage(1), x5580_argin)), op=FixMul, results=List(CU.temp(stage(2), tr353)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr353), CU.ctr(stage(2), x5939(1))), op=FixAdd, results=List(CU.scalarOut(stage(3), x6115_mc.ofs)))
      Stage(stage(4), operands=List(Const("192i")), op=Bypass, results=List(CU.scalarOut(stage(4), x6115_mc.len)))
    }
    val x6113_0 = Pipeline(name = "x6113_0", parent=x6117, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr360 = CU.temp
      val tr358 = CU.temp
      val tr357 = CU.temp
      val x60521 = CounterChain.copy(x6089_0, "x6052")
      val x5943 = CounterChain.copy(x6093, "x5943")
      val x6052 = CounterChain.copy(x6091, "x6052")
      val ctr27 = (Const("0i").out, Const("192i").out, Const("16i").out) // Counter
      val x6104 = CounterChain(name = "x6104", ctr27)
      val x6096 = CounterChain.copy(x6117, "x6096")
      val x5940_x6107 = SRAM(size = 1536, writeCtr = x6052(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x6096(0), swapWrite = x5943(0))).wtPort(x5940_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x5940_x6107))
      Stage(stage(1), operands=List(x6052(0), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr357)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr357), CU.ctr(stage(1), x6052(1))), op=FixAdd, results=List(x5940_x6107.writeAddr, CU.temp(stage(2), tr358)))
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x6096(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr360)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr360), CU.ctr(stage(1), x6104(0))), op=FixAdd, results=List(x5940_x6107.readAddr))
      Stage(stage(3), operands=List(x5940_x6107.load), op=Bypass, results=List(CU.vecOut(stage(3), x6115_mc.vdata)))
    }
    
  }
}
