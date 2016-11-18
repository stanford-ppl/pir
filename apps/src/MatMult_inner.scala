import pir.graph
import pir.graph._
import pir.graph.enums._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.misc._
import pir.PIRApp

object MatMult_innerDesign extends PIRApp {
  def main(args: String*)(top:Top) = {
    val x4752_argin = ArgIn("x4752")
    val x4997_scalar = Scalar("x4997")
    val x5108_scalar = Scalar("x5108")
    val x4759_oc = OffChip("x4759")
    val x4763_oc = OffChip("x4763")
    val x4751_argin = ArgIn("x4751")
    val x4765_oc = OffChip("x4765")
    val x4750_argin = ArgIn("x4750")
    val x5031_mc = MemoryController(TileLoad, x4759_oc)
    val x5081_mc = MemoryController(TileLoad, x4763_oc)
    val x5160_mc = MemoryController(TileStore, x4765_oc)
    val x5166 = Sequential(name = "x5166", parent=top, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x5166_unitcc = CounterChain(name = "x5166_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x5164 = MetaPipeline(name = "x5164", parent=x5166, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr1 = (Const("0i").out, CU.scalarIn(stage0, x4750_argin).out, Const("8i").out) // Counter
      val ctr2 = (Const("0i").out, CU.scalarIn(stage0, x4751_argin).out, Const("192i").out) // Counter
      val x4996 = CounterChain(name = "x4996", ctr1, ctr2)
      var stage: List[Stage] = Nil
    }
    val x5138 = MetaPipeline(name = "x5138", parent=x5164, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr5 = (Const("0i").out, CU.scalarIn(stage0, x4752_argin).out, Const("192i").out) // Counter
      val x5000 = CounterChain(name = "x5000", ctr5)
      var stage: List[Stage] = Nil
    }
    val x5052 = StreamController(name = "x5052", parent=x5138, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr7 = (Const("0i").out, Const("8i").out, Const("1i").out) // Counter
      val x5005 = CounterChain(name = "x5005", ctr7)
      var stage: List[Stage] = Nil
    }
    val x5027_0 = UnitPipeline(name = "x5027_0", parent=x5052, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr226 = CU.temp
      val tr225 = CU.temp
      val tr223 = CU.temp
      val tr222 = CU.temp
      val tr220 = CU.temp
      val tr215 = CU.temp
      val tr213 = CU.temp
      val tr212 = CU.temp
      val tr211 = CU.temp
      val x4996 = CounterChain.copy(x5164, "x4996")
      val x5027_unitcc = CounterChain(name = "x5027_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x5000 = CounterChain.copy(x5138, "x5000")
      val x5005 = CounterChain.copy(x5052, "x5005")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(11)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4996(0)), CU.ctr(stage(0), x5005(0))), op=FixAdd, results=List(CU.temp(stage(1), tr211)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr211), CU.scalarIn(stage(1), x4752_argin)), op=FixMul, results=List(CU.temp(stage(2), tr212)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr212), CU.ctr(stage(2), x5000(0))), op=FixAdd, results=List(CU.temp(stage(3), tr213)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr213), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr215)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr213), CU.temp(stage(4), tr215)), op=FixSub, results=List(CU.scalarOut(stage(5), x5031_mc.ofs)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr215), Const("192i")), op=FixAdd, results=List(CU.temp(stage(6), tr220)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr220), Const("64i")), op=FixMod, results=List(CU.temp(stage(7), tr222)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr220), CU.temp(stage(7), tr222)), op=FixSub, results=List(CU.temp(stage(8), tr223)))
      Stage(stage(9), operands=List(CU.temp(stage(8), tr222), Const("0i")), op=FixNeq, results=List(CU.temp(stage(9), tr225)))
      Stage(stage(10), operands=List(CU.temp(stage(9), tr225), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(10), tr226)))
      Stage(stage(11), operands=List(CU.temp(stage(10), tr223), CU.temp(stage(10), tr226)), op=FixAdd, results=List(CU.scalarOut(stage(11), x5031_mc.len)))
    }
    val x5102 = StreamController(name = "x5102", parent=x5138, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr9 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      val x5055 = CounterChain(name = "x5055", ctr9)
      var stage: List[Stage] = Nil
    }
    val x5077_0 = UnitPipeline(name = "x5077_0", parent=x5102, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr256 = CU.temp
      val tr255 = CU.temp
      val tr253 = CU.temp
      val tr252 = CU.temp
      val tr250 = CU.temp
      val tr245 = CU.temp
      val tr243 = CU.temp
      val tr242 = CU.temp
      val tr241 = CU.temp
      val x4996 = CounterChain.copy(x5164, "x4996")
      val x5055 = CounterChain.copy(x5102, "x5055")
      val x5000 = CounterChain.copy(x5138, "x5000")
      val x5077_unitcc = CounterChain(name = "x5077_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(11)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x5000(0)), CU.ctr(stage(0), x5055(0))), op=FixAdd, results=List(CU.temp(stage(1), tr241)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr241), CU.scalarIn(stage(1), x4751_argin)), op=FixMul, results=List(CU.temp(stage(2), tr242)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr242), CU.ctr(stage(2), x4996(1))), op=FixAdd, results=List(CU.temp(stage(3), tr243)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr243), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr245)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr243), CU.temp(stage(4), tr245)), op=FixSub, results=List(CU.scalarOut(stage(5), x5081_mc.ofs)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr245), Const("192i")), op=FixAdd, results=List(CU.temp(stage(6), tr250)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr250), Const("64i")), op=FixMod, results=List(CU.temp(stage(7), tr252)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr250), CU.temp(stage(7), tr252)), op=FixSub, results=List(CU.temp(stage(8), tr253)))
      Stage(stage(9), operands=List(CU.temp(stage(8), tr252), Const("0i")), op=FixNeq, results=List(CU.temp(stage(9), tr255)))
      Stage(stage(10), operands=List(CU.temp(stage(9), tr255), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(10), tr256)))
      Stage(stage(11), operands=List(CU.temp(stage(10), tr253), CU.temp(stage(10), tr256)), op=FixAdd, results=List(CU.scalarOut(stage(11), x5081_mc.len)))
    }
    val x5136 = MetaPipeline(name = "x5136", parent=x5138, deps=List(x5052, x5102)) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr15 = (Const("0i").out, Const("8i").out, Const("1i").out) // Counter
      val ctr16 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      val x5107 = CounterChain(name = "x5107", ctr15, ctr16)
      var stage: List[Stage] = Nil
    }
    val x5124_0 = Pipeline(name = "x5124_0", parent=x5136, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr278 = CU.temp
      val tr274 = CU.temp
      val ctr19 = (Const("0i").out, Const("192i").out, Const("16i").out) // Counter
      val x5110 = CounterChain(name = "x5110", ctr19)
      val x5107 = CounterChain.copy(x5136, "x5107")
      val x5001_x5113 = SemiFIFO(size = 1536, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x5107(0))).wtPort(x5031_mc.vdata)
      val x5002_x5116 = SemiFIFO(size = 36864, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x5107(0))).wtPort(x5081_mc.vdata)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(6)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x5107(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr274)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr274), CU.ctr(stage(1), x5110(0))), op=FixAdd, results=List(x5001_x5113.readAddr))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x5110(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(3), tr278)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr278), CU.ctr(stage(3), x5107(1))), op=FixAdd, results=List(x5002_x5116.readAddr))
      Stage(stage(5), operands=List(CU.load(stage(4), x5001_x5113), x5002_x5116.load), op=FixMul, results=List(CU.reduce(stage(5))))
      val (rs1, rr285) = Stage.reduce(op=FixAdd, init=Const("0i"))
      Stage(stage(6), operands=List(rr285), op=Bypass, results=List(CU.scalarOut(stage(6), x5108_scalar)))
    }
    val x5134_0 = UnitPipeline(name = "x5134_0", parent=x5136, deps=List(x5124_0)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr297 = CU.temp
      val tr295 = CU.temp
      val tr294 = CU.temp
      val tr289 = CU.temp
      val x5000 = CounterChain.copy(x5138, "x5000")
      val x5134_unitcc = CounterChain(name = "x5134_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x5107 = CounterChain.copy(x5136, "x5107")
      val x4997_x5127 = SRAM(size = 1536, writeCtr = x5134_unitcc(0), banking = NoBanking(), buffering = SingleBuffer())
      val wr299 = CU.wtAddr(x4997_x5127)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(7)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x5107(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr289)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr289), CU.ctr(stage(1), x5107(1))), op=FixAdd, results=List(x4997_x5127.readAddr))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x5000(0)), Const("0i")), op=FixEql, results=List(CU.temp(stage(3), tr294)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr294), Const("0i"), CU.load(stage(3), x4997_x5127)), op=Mux, results=List(CU.temp(stage(4), tr295)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr295), CU.scalarIn(stage(4), x5108_scalar)), op=FixAdd, results=List(CU.scalarOut(stage(5), x4997_scalar), CU.store(stage(5), x4997_x5127)))
      Stage(stage(6), operands=List(CU.ctr(stage(5), x5107(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(6), tr297)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr297), CU.ctr(stage(6), x5107(1))), op=FixAdd, results=List(CU.wtAddr(stage(7), wr299)))
    }
    val x5162 = StreamController(name = "x5162", parent=x5164, deps=List(x5138)) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr11 = (Const("0i").out, Const("8i").out, Const("1i").out) // Counter
      val x5141 = CounterChain(name = "x5141", ctr11)
      var stage: List[Stage] = Nil
    }
    val x5164_leafX = Pipeline(name = "x5164_leafX", parent=x5164, deps=List(x5162)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4996 = CounterChain.copy(x5164, "x4996")
      val x5077_unitcc = CounterChain(name = "x5077_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x5147_0 = UnitPipeline(name = "x5147_0", parent=x5162, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr304 = CU.temp
      val tr303 = CU.temp
      val x4996 = CounterChain.copy(x5164, "x4996")
      val x5141 = CounterChain.copy(x5162, "x5141")
      val x5147_unitcc = CounterChain(name = "x5147_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(4)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4996(0)), CU.ctr(stage(0), x5141(0))), op=FixAdd, results=List(CU.temp(stage(1), tr303)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr303), CU.scalarIn(stage(1), x4751_argin)), op=FixMul, results=List(CU.temp(stage(2), tr304)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr304), CU.ctr(stage(2), x4996(1))), op=FixAdd, results=List(CU.scalarOut(stage(3), x5160_mc.ofs)))
      Stage(stage(4), operands=List(Const("192i")), op=Bypass, results=List(CU.scalarOut(stage(4), x5160_mc.len)))
    }
    val x5158_0 = Pipeline(name = "x5158_0", parent=x5162, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr311 = CU.temp
      val tr309 = CU.temp
      val tr308 = CU.temp
      val x5000 = CounterChain.copy(x5138, "x5000")
      val x5141 = CounterChain.copy(x5162, "x5141")
      val x5134_unitcc = CounterChain.copy(x5134_0, "x5134_unitcc")
      val x5107 = CounterChain.copy(x5136, "x5107")
      val ctr23 = (Const("0i").out, Const("192i").out, Const("16i").out) // Counter
      val x5149 = CounterChain(name = "x5149", ctr23)
      val x4997_x5152 = SRAM(size = 1536, writeCtr = x5134_unitcc(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x5141(0), swapWrite = x5000(0))).wtPort(x4997_scalar)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x4997_x5152))
      Stage(stage(1), operands=List(x5107(0), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr308)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr308), CU.ctr(stage(1), x5107(1))), op=FixAdd, results=List(x4997_x5152.writeAddr, CU.temp(stage(2), tr309)))
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x5141(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr311)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr311), CU.ctr(stage(1), x5149(0))), op=FixAdd, results=List(x4997_x5152.readAddr))
      Stage(stage(3), operands=List(x4997_x5152.load), op=Bypass, results=List(CU.vecOut(stage(3), x5160_mc.vdata)))
    }
    
  }
}
