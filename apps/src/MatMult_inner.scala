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
    val x1361_scalar = Scalar("x1361")
    val x1298_oc = OffChip("x1298")
    val x1286_argin = ArgIn("x1286")
    val x1308_0_rd_vector = Vector("x1308_0_rd")
    val x1308_1_rd_vector = Vector("x1308_1_rd")
    val x1314_0_rd_vector = Vector("x1314_0_rd")
    val x1313_0_rd_vector = Vector("x1313_0_rd")
    val x1284_argin = ArgIn("x1284")
    val x1285_argin = ArgIn("x1285")
    val x1308_1_wt_vector = Vector("x1308_1_wt")
    val x1308_0_wt_vector = Vector("x1308_0_wt")
    val x1292_oc = OffChip("x1292")
    val x1295_oc = OffChip("x1295")
    val x1400_mc = MemoryController(TileStore, x1298_oc).parent("x1401")
    val x1327_mc = MemoryController(TileLoad, x1292_oc).parent("x1335")
    val x1348_mc = MemoryController(TileLoad, x1295_oc).parent("x1356")
    val x1403 = Sequential(name = "x1403", parent=top) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1403_unitcc = CounterChain(name = "x1403_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x1402 = MetaPipeline(name = "x1402", parent=x1403) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr1 = (Const("0i").out, CU.scalarIn(stage0, x1284_argin), Const("4i").out) // Counter
      val ctr2 = (Const("0i").out, CU.scalarIn(stage0, x1285_argin), Const("64i").out) // Counter
      val x1307 = CounterChain(name = "x1307", ctr1, ctr2)
      var stage: List[Stage] = Nil
    }
    val x1308_dsp0 = MemoryPipeline(name = "x1308_dsp0", parent="x1402") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr261 = CU.temp
      val tr262 = CU.temp
      val tr264 = CU.temp
      val tr265 = CU.temp
      val x1383 = CounterChain.copy("x1401", "x1383")
      val x1378_unitcc = CounterChain.copy("x1378", "x1378_unitcc")
      val x1360 = CounterChain.copy("x1379", "x1360")
      val x1391 = CounterChain.copy("x1396", "x1391")
      val x1312 = CounterChain.copy("x1380", "x1312")
      val x1308_x1392 = SRAM(size = 256, banking = Strided(1)).wtPort(x1308_0_wt_vector).rdPort(x1308_0_rd_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x1308_x1392))
      Stage(stage(1), operands=List(x1360(0), Const("64i")), op=FixMul, results=List(CU.temp(stage(1), tr261)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr261), CU.ctr(stage(1), x1360(1))), op=FixAdd, results=List(x1308_x1392.writeAddr, CU.temp(stage(2), tr262)))
      stage = stage0 +: RAStages(2, List(x1308_x1392))
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1383(0)), Const("64i")), op=FixMul, results=List(CU.temp(stage(1), tr264)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr264), CU.ctr(stage(1), x1391(0))), op=FixAdd, results=List(x1308_x1392.readAddr, CU.temp(stage(2), tr265)))
    }
    val x1308_dsp1 = MemoryPipeline(name = "x1308_dsp1", parent="x1379") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr268 = CU.temp
      val tr269 = CU.temp
      val tr271 = CU.temp
      val tr272 = CU.temp
      val x1378_unitcc = CounterChain.copy("x1378", "x1378_unitcc")
      val x1360 = CounterChain.copy("x1379", "x1360")
      val x1308_x1373 = SRAM(size = 256, banking = Strided(1)).wtPort(x1308_1_wt_vector).rdPort(x1308_1_rd_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x1308_x1373))
      Stage(stage(1), operands=List(x1360(0), Const("64i")), op=FixMul, results=List(CU.temp(stage(1), tr268)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr268), CU.ctr(stage(1), x1360(1))), op=FixAdd, results=List(x1308_x1373.writeAddr, CU.temp(stage(2), tr269)))
      stage = stage0 +: RAStages(2, List(x1308_x1373))
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1360(0)), Const("64i")), op=FixMul, results=List(CU.temp(stage(1), tr271)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr271), CU.ctr(stage(1), x1360(1))), op=FixAdd, results=List(x1308_x1373.readAddr, CU.temp(stage(2), tr272)))
    }
    val x1380 = MetaPipeline(name = "x1380", parent=x1402) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr3 = (Const("0i").out, CU.scalarIn(stage0, x1286_argin), Const("64i").out) // Counter
      val x1312 = CounterChain(name = "x1312", ctr3)
      var stage: List[Stage] = Nil
    }
    val x1313_dsp0 = MemoryPipeline(name = "x1313_dsp0", parent="x1380") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr276 = CU.temp
      val tr277 = CU.temp
      val x1360 = CounterChain.copy("x1379", "x1360")
      val x1363 = CounterChain.copy("x1372", "x1363")
      val x1313_x1364 = SemiFIFO(size = 256, banking = Strided(1)).wtPort(x1327_mc.data).rdPort(x1313_0_rd_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: RAStages(2, List(x1313_x1364))
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1360(0)), Const("64i")), op=FixMul, results=List(CU.temp(stage(1), tr276)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr276), CU.ctr(stage(1), x1363(0))), op=FixAdd, results=List(x1313_x1364.readAddr, CU.temp(stage(2), tr277)))
    }
    val x1314_dsp0 = MemoryPipeline(name = "x1314_dsp0", parent="x1380") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr280 = CU.temp
      val tr281 = CU.temp
      val x1360 = CounterChain.copy("x1379", "x1360")
      val x1363 = CounterChain.copy("x1372", "x1363")
      val x1314_x1365 = SemiFIFO(size = 4096, banking = Strided(1)).wtPort(x1348_mc.data).rdPort(x1314_0_rd_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: RAStages(2, List(x1314_x1365))
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1363(0)), Const("64i")), op=FixMul, results=List(CU.temp(stage(1), tr280)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr280), CU.ctr(stage(1), x1360(1))), op=FixAdd, results=List(x1314_x1365.readAddr, CU.temp(stage(2), tr281)))
    }
    val x1335 = StreamController(name = "x1335", parent=x1380) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr6 = (Const("0i").out, Const("4i").out, Const("1i").out) // Counter
      val x1317 = CounterChain(name = "x1317", ctr6)
      var stage: List[Stage] = Nil
    }
    val x1323 = StreamPipeline(name = "x1323", parent=x1335) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr284 = CU.temp
      val tr285 = CU.temp
      val tr286 = CU.temp
      val x1323_unitcc = CounterChain(name = "x1323_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x1312 = CounterChain.copy("x1380", "x1312")
      val x1317 = CounterChain.copy("x1335", "x1317")
      val x1307 = CounterChain.copy("x1402", "x1307")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(4)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1307(0)), CU.ctr(stage(0), x1317(0))), op=FixAdd, results=List(CU.temp(stage(1), tr284)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr284), CU.scalarIn(stage(1), x1286_argin)), op=FixMul, results=List(CU.temp(stage(2), tr285)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr285), CU.ctr(stage(2), x1312(0))), op=FixAdd, results=List(CU.scalarOut(stage(3), x1327_mc.ofs), CU.temp(stage(3), tr286)))
      Stage(stage(4), operands=List(Const("64i")), op=Bypass, results=List(CU.scalarOut(stage(4), x1327_mc.len)))
    }
    val x1356 = StreamController(name = "x1356", parent=x1380) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr7 = (Const("0i").out, Const("64i").out, Const("1i").out) // Counter
      val x1338 = CounterChain(name = "x1338", ctr7)
      var stage: List[Stage] = Nil
    }
    val x1344 = StreamPipeline(name = "x1344", parent=x1356) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr297 = CU.temp
      val tr298 = CU.temp
      val tr299 = CU.temp
      val x1338 = CounterChain.copy("x1356", "x1338")
      val x1344_unitcc = CounterChain(name = "x1344_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x1312 = CounterChain.copy("x1380", "x1312")
      val x1307 = CounterChain.copy("x1402", "x1307")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(4)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1312(0)), CU.ctr(stage(0), x1338(0))), op=FixAdd, results=List(CU.temp(stage(1), tr297)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr297), CU.scalarIn(stage(1), x1285_argin)), op=FixMul, results=List(CU.temp(stage(2), tr298)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr298), CU.ctr(stage(2), x1307(1))), op=FixAdd, results=List(CU.scalarOut(stage(3), x1348_mc.ofs), CU.temp(stage(3), tr299)))
      Stage(stage(4), operands=List(Const("64i")), op=Bypass, results=List(CU.scalarOut(stage(4), x1348_mc.len)))
    }
    val x1379 = MetaPipeline(name = "x1379", parent=x1380) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr4 = (Const("0i").out, Const("4i").out, Const("1i").out) // Counter
      val ctr5 = (Const("0i").out, Const("64i").out, Const("1i").out) // Counter
      val x1360 = CounterChain(name = "x1360", ctr4, ctr5)
      var stage: List[Stage] = Nil
    }
    val x1372 = Pipeline(name = "x1372", parent=x1379) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr311 = CU.temp
      val ctr10 = (Const("0i").out, Const("64i").out, Const("1i").out) // Counter
      val x1363 = CounterChain(name = "x1363", ctr10)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x1313_0_rd_vector), CU.vecIn(stage(0), x1314_0_rd_vector)), op=FixMul, results=List(CU.reduce(stage(1)), CU.temp(stage(1), tr311)))
      val (rs1, rr314) = Stage.reduce(op=FixAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr314), op=Bypass, results=List(CU.scalarOut(stage(2), x1361_scalar)))
    }
    val x1378 = UnitPipeline(name = "x1378", parent=x1379) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr319 = CU.temp
      val tr320 = CU.temp
      val tr321 = CU.temp
      val x1378_unitcc = CounterChain(name = "x1378_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x1312 = CounterChain.copy("x1380", "x1312")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1312(0)), Const("0i")), op=FixEql, results=List(CU.temp(stage(1), tr319)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr319), Const("0i"), CU.vecIn(stage(1), x1308_1_rd_vector)), op=Mux, results=List(CU.temp(stage(2), tr320)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr320), CU.scalarIn(stage(2), x1361_scalar)), op=FixAdd, results=List(CU.vecOut(stage(3), x1308_1_wt_vector), CU.vecOut(stage(3), x1308_0_wt_vector), CU.temp(stage(3), tr321)))
    }
    val x1401 = StreamController(name = "x1401", parent=x1402) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr11 = (Const("0i").out, Const("4i").out, Const("1i").out) // Counter
      val x1383 = CounterChain(name = "x1383", ctr11)
      var stage: List[Stage] = Nil
    }
    val x1389 = StreamPipeline(name = "x1389", parent=x1401) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr325 = CU.temp
      val tr326 = CU.temp
      val tr327 = CU.temp
      val x1307 = CounterChain.copy("x1402", "x1307")
      val x1383 = CounterChain.copy("x1401", "x1383")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(4)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1307(0)), CU.ctr(stage(0), x1383(0))), op=FixAdd, results=List(CU.temp(stage(1), tr325)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr325), CU.scalarIn(stage(1), x1285_argin)), op=FixMul, results=List(CU.temp(stage(2), tr326)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr326), CU.ctr(stage(2), x1307(1))), op=FixAdd, results=List(CU.scalarOut(stage(3), x1400_mc.ofs), CU.temp(stage(3), tr327)))
      Stage(stage(4), operands=List(Const("64i")), op=Bypass, results=List(CU.scalarOut(stage(4), x1400_mc.len)))
    }
    val x1396 = StreamPipeline(name = "x1396", parent=x1401) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr12 = (Const("0i").out, Const("64i").out, Const("1i").out) // Counter
      val x1391 = CounterChain(name = "x1391", ctr12)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x1308_0_rd_vector)), op=Bypass, results=List(CU.vecOut(stage(1), x1400_mc.data)))
    }
    
  }
}
