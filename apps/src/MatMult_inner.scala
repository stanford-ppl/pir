import pir.graph
import pir.graph._
import pir.graph.enums._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.misc._
import pir.PIRApp

object MatMult_inner extends PIRApp {
  def main(args: String*)(top:Top) = {
    val x1230_0_rd_vector = Vector("x1230_0_rd")
    val x1225_1_wt_vector = Vector("x1225_1_wt")
    val x1229_0_rd_vector = Vector("x1229_0_rd")
    val x1225_0_wt_vector = Vector("x1225_0_wt")
    val x1214_oc = OffChip("x1214")
    val x1225_0_rd_vector = Vector("x1225_0_rd")
    val x1205_argin = ArgIn("x1205")
    val x1275_scalar = Scalar("x1275")
    val x1211_oc = OffChip("x1211")
    val x1204_argin = ArgIn("x1204")
    val x1203_argin = ArgIn("x1203")
    val x1217_oc = OffChip("x1217")
    val x1225_1_rd_vector = Vector("x1225_1_rd")
    val x1262_mc = MemoryController(TileLoad, x1214_oc).parent("x1270")
    val x1313_mc = MemoryController(TileStore, x1217_oc).parent("x1314")
    val x1242_mc = MemoryController(TileLoad, x1211_oc).parent("x1250")
    val x1316 = Sequential(name = "x1316", parent=top) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1316_unitcc = CounterChain(name = "x1316_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x1315 = MetaPipeline(name = "x1315", parent=x1316) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr1 = (Const("0i").out, CU.scalarIn(stage0, x1203_argin), Const("4i").out) // Counter
      val ctr2 = (Const("0i").out, CU.scalarIn(stage0, x1204_argin), Const("64i").out) // Counter
      val x1224 = CounterChain(name = "x1224", ctr1, ctr2)
      var stage: List[Stage] = Nil
    }
    val x1225_dsp0 = MemoryPipeline(name = "x1225_dsp0", parent=x1315) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr71 = CU.temp
      val tr72 = CU.temp
      val tr76 = CU.temp
      val tr79 = CU.temp
      val x1274 = CounterChain.copy("x1293", "x1274")
      val x1297 = CounterChain.copy("x1314", "x1297")
      val x1305 = CounterChain.copy("x1310", "x1305")
      val x1225_x1306 = SRAM(size = 256, banking = Strided(1)).wtPort(x1225_0_wt_vector).rdPort(x1225_0_rd_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x1225_x1306))
      Stage(stage(1), operands=List(x1274(0), Const("64i")), op=FixMul, results=List(CU.temp(stage(1), tr71)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr71), CU.ctr(stage(1), x1274(1))), op=FixAdd, results=List(x1225_x1306.writeAddr, CU.temp(stage(2), tr72)))
      stage = stage0 +: RAStages(2, List(x1225_x1306))
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1297(0)), Const("64i")), op=FixMul, results=List(CU.temp(stage(1), tr76)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr76), CU.ctr(stage(1), x1305(0))), op=FixAdd, results=List(x1225_x1306.readAddr, CU.temp(stage(2), tr79)))
    }
    val x1225_dsp1 = MemoryPipeline(name = "x1225_dsp1", parent=x1315) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr86 = CU.temp
      val tr87 = CU.temp
      val tr89 = CU.temp
      val tr90 = CU.temp
      val x1274 = CounterChain.copy("x1293", "x1274")
      val x1225_x1287 = SRAM(size = 256, banking = Strided(1)).wtPort(x1225_1_wt_vector).rdPort(x1225_1_rd_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x1225_x1287))
      Stage(stage(1), operands=List(x1274(0), Const("64i")), op=FixMul, results=List(CU.temp(stage(1), tr86)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr86), CU.ctr(stage(1), x1274(1))), op=FixAdd, results=List(x1225_x1287.writeAddr, CU.temp(stage(2), tr87)))
      stage = stage0 +: RAStages(2, List(x1225_x1287))
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1274(0)), Const("64i")), op=FixMul, results=List(CU.temp(stage(1), tr89)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr89), CU.ctr(stage(1), x1274(1))), op=FixAdd, results=List(x1225_x1287.readAddr, CU.temp(stage(2), tr90)))
    }
    val x1294 = MetaPipeline(name = "x1294", parent=x1315) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr3 = (Const("0i").out, CU.scalarIn(stage0, x1205_argin), Const("64i").out) // Counter
      val x1228 = CounterChain(name = "x1228", ctr3)
      var stage: List[Stage] = Nil
    }
    val x1229_dsp0 = MemoryPipeline(name = "x1229_dsp0", parent=x1294) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr98 = CU.temp
      val tr101 = CU.temp
      val x1274 = CounterChain.copy("x1293", "x1274")
      val x1277 = CounterChain.copy("x1286", "x1277")
      val x1229_x1278 = SemiFIFO(size = 256, banking = Strided(1)).wtPort(x1242_mc.data).rdPort(x1229_0_rd_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: RAStages(2, List(x1229_x1278))
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1274(0)), Const("64i")), op=FixMul, results=List(CU.temp(stage(1), tr98)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr98), CU.ctr(stage(1), x1277(0))), op=FixAdd, results=List(x1229_x1278.readAddr, CU.temp(stage(2), tr101)))
    }
    val x1230_dsp0 = MemoryPipeline(name = "x1230_dsp0", parent=x1294) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr106 = CU.temp
      val tr111 = CU.temp
      val x1277 = CounterChain.copy("x1286", "x1277")
      val x1274 = CounterChain.copy("x1293", "x1274")
      val x1230_x1279 = SemiFIFO(size = 4096, banking = Strided(1)).wtPort(x1262_mc.data).rdPort(x1230_0_rd_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: RAStages(2, List(x1230_x1279))
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1277(0)), Const("64i")), op=FixMul, results=List(CU.temp(stage(1), tr106)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr106), CU.ctr(stage(1), x1274(1))), op=FixAdd, results=List(x1230_x1279.readAddr, CU.temp(stage(2), tr111)))
    }
    val x1250 = StreamController(name = "x1250", parent=x1294) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr4 = (Const("0i").out, Const("4i").out, Const("1i").out) // Counter
      val x1233 = CounterChain(name = "x1233", ctr4)
      var stage: List[Stage] = Nil
    }
    val x1239 = StreamPipeline(name = "x1239", parent=x1250) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr120 = CU.temp
      val tr121 = CU.temp
      val tr124 = CU.temp
      val x1239_unitcc = CounterChain(name = "x1239_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x1224 = CounterChain.copy("x1315", "x1224")
      val x1233 = CounterChain.copy("x1250", "x1233")
      val x1228 = CounterChain.copy("x1294", "x1228")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(4)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1224(0)), CU.ctr(stage(0), x1233(0))), op=FixAdd, results=List(CU.temp(stage(1), tr120)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr120), CU.scalarIn(stage(1), x1205_argin)), op=FixMul, results=List(CU.temp(stage(2), tr121)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr121), CU.ctr(stage(2), x1228(0))), op=FixAdd, results=List(CU.scalarOut(stage(3), x1242_mc.ofs), CU.temp(stage(3), tr124)))
      Stage(stage(4), operands=List(Const("64i")), op=Bypass, results=List(CU.scalarOut(stage(4), x1242_mc.len)))
    }
    val x1270 = StreamController(name = "x1270", parent=x1294) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr6 = (Const("0i").out, Const("64i").out, Const("1i").out) // Counter
      val x1253 = CounterChain(name = "x1253", ctr6)
      var stage: List[Stage] = Nil
    }
    val x1259 = StreamPipeline(name = "x1259", parent=x1270) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr147 = CU.temp
      val tr148 = CU.temp
      val tr153 = CU.temp
      val x1259_unitcc = CounterChain(name = "x1259_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x1228 = CounterChain.copy("x1294", "x1228")
      val x1253 = CounterChain.copy("x1270", "x1253")
      val x1224 = CounterChain.copy("x1315", "x1224")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(4)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1228(0)), CU.ctr(stage(0), x1253(0))), op=FixAdd, results=List(CU.temp(stage(1), tr147)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr147), CU.scalarIn(stage(1), x1204_argin)), op=FixMul, results=List(CU.temp(stage(2), tr148)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr148), CU.ctr(stage(2), x1224(1))), op=FixAdd, results=List(CU.scalarOut(stage(3), x1262_mc.ofs), CU.temp(stage(3), tr153)))
      Stage(stage(4), operands=List(Const("64i")), op=Bypass, results=List(CU.scalarOut(stage(4), x1262_mc.len)))
    }
    val x1293 = MetaPipeline(name = "x1293", parent=x1294) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr8 = (Const("0i").out, Const("4i").out, Const("1i").out) // Counter
      val ctr9 = (Const("0i").out, Const("64i").out, Const("1i").out) // Counter
      val x1274 = CounterChain(name = "x1274", ctr8, ctr9)
      var stage: List[Stage] = Nil
    }
    val x1286 = Pipeline(name = "x1286", parent=x1293) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr173 = CU.temp
      val ctr10 = (Const("0i").out, Const("64i").out, Const("1i").out) // Counter
      val x1277 = CounterChain(name = "x1277", ctr10)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x1229_0_rd_vector), CU.vecIn(stage(0), x1230_0_rd_vector)), op=FixMul, results=List(CU.reduce(stage(1)), CU.temp(stage(1), tr173)))
      val (rs1, rr176) = Stage.reduce(op=FixAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr176), op=Bypass, results=List(CU.scalarOut(stage(2), x1275_scalar)))
    }
    val x1292 = UnitPipeline(name = "x1292", parent=x1293) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr183 = CU.temp
      val tr184 = CU.temp
      val tr185 = CU.temp
      val x1292_unitcc = CounterChain(name = "x1292_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x1228 = CounterChain.copy("x1294", "x1228")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1228(0)), Const("0i")), op=FixEql, results=List(CU.temp(stage(1), tr183)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr183), Const("0i"), CU.vecIn(stage(1), x1225_1_rd_vector)), op=Mux, results=List(CU.temp(stage(2), tr184)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr184), CU.scalarIn(stage(2), x1275_scalar)), op=FixAdd, results=List(CU.vecOut(stage(3), x1225_1_wt_vector), CU.vecOut(stage(3), x1225_0_wt_vector), CU.temp(stage(3), tr185)))
    }
    val x1314 = StreamController(name = "x1314", parent=x1315) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr11 = (Const("0i").out, Const("4i").out, Const("1i").out) // Counter
      val x1297 = CounterChain(name = "x1297", ctr11)
      var stage: List[Stage] = Nil
    }
    val x1303 = StreamPipeline(name = "x1303", parent=x1314) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr195 = CU.temp
      val tr196 = CU.temp
      val tr197 = CU.temp
      val x1303_unitcc = CounterChain(name = "x1303_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x1224 = CounterChain.copy("x1315", "x1224")
      val x1297 = CounterChain.copy("x1314", "x1297")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(4)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1224(0)), CU.ctr(stage(0), x1297(0))), op=FixAdd, results=List(CU.temp(stage(1), tr195)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr195), CU.scalarIn(stage(1), x1204_argin)), op=FixMul, results=List(CU.temp(stage(2), tr196)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr196), CU.ctr(stage(2), x1224(1))), op=FixAdd, results=List(CU.scalarOut(stage(3), x1313_mc.ofs), CU.temp(stage(3), tr197)))
      Stage(stage(4), operands=List(Const("64i")), op=Bypass, results=List(CU.scalarOut(stage(4), x1313_mc.len)))
    }
    val x1310 = StreamPipeline(name = "x1310", parent=x1314) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr12 = (Const("0i").out, Const("64i").out, Const("1i").out) // Counter
      val x1305 = CounterChain(name = "x1305", ctr12)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x1225_0_rd_vector)), op=Bypass, results=List(CU.vecOut(stage(1), x1313_mc.data)))
    }
    
  }
}
