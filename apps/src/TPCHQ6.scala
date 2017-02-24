import pir.graph
import pir.graph._
import pir.graph.enums._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.misc._
import pir.PIRApp

object TPCHQ6 extends PIRApp {
  def main(args: String*)(top:Top) = {
    val x1230_0_rd_vector = Vector("x1230_0_rd")
    val x1227_0_rd_vector = Vector("x1227_0_rd")
    val x1207_argin = ArgIn("x1207")
    val x1229_0_rd_vector = Vector("x1229_0_rd")
    val x1296_scalar = Scalar("x1296")
    val x1216_oc = OffChip("x1216")
    val x1228_0_rd_vector = Vector("x1228_0_rd")
    val x1210_oc = OffChip("x1210")
    val x1212_oc = OffChip("x1212")
    val x1214_oc = OffChip("x1214")
    val x1217_argout = ArgOut("x1217")
    val x1254_mc = MemoryController(TileLoad, x1212_oc).parent("x1262")
    val x1286_mc = MemoryController(TileLoad, x1216_oc).parent("x1294")
    val x1270_mc = MemoryController(TileLoad, x1214_oc).parent("x1278")
    val x1238_mc = MemoryController(TileLoad, x1210_oc).parent("x1246")
    val x1332 = Sequential(name = "x1332", parent=top) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1332_unitcc = CounterChain(name = "x1332_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x1328 = MetaPipeline(name = "x1328", parent=x1332) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr1 = (Const("0i").out, CU.scalarIn(stage0, x1207_argin), Const("96i").out) // Counter
      val x1226 = CounterChain(name = "x1226", ctr1)
      var stage: List[Stage] = Nil
    }
    val x1227_dsp0 = MemoryPipeline(name = "x1227_dsp0", parent=x1328) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1298 = CounterChain.copy("x1321", "x1298")
      val x1227_x1299 = SemiFIFO(size = 96, banking = Strided(1)).wtPort(x1238_mc.data).rdPort(x1227_0_rd_vector).rdAddr(x1298(0))
      var stage: List[Stage] = Nil
    }
    val x1228_dsp0 = MemoryPipeline(name = "x1228_dsp0", parent=x1328) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1298 = CounterChain.copy("x1321", "x1298")
      val x1228_x1301 = SemiFIFO(size = 96, banking = Strided(1)).wtPort(x1254_mc.data).rdPort(x1228_0_rd_vector).rdAddr(x1298(0))
      var stage: List[Stage] = Nil
    }
    val x1229_dsp0 = MemoryPipeline(name = "x1229_dsp0", parent=x1328) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1298 = CounterChain.copy("x1321", "x1298")
      val x1229_x1300 = SemiFIFO(size = 96, banking = Strided(1)).wtPort(x1270_mc.data).rdPort(x1229_0_rd_vector).rdAddr(x1298(0))
      var stage: List[Stage] = Nil
    }
    val x1230_dsp0 = MemoryPipeline(name = "x1230_dsp0", parent=x1328) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1298 = CounterChain.copy("x1321", "x1298")
      val x1230_x1302 = SemiFIFO(size = 96, banking = Strided(1)).wtPort(x1286_mc.data).rdPort(x1230_0_rd_vector).rdAddr(x1298(0))
      var stage: List[Stage] = Nil
    }
    val x1246 = StreamController(name = "x1246", parent=x1328) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1246_unitcc = CounterChain(name = "x1246_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x1234 = StreamPipeline(name = "x1234", parent=x1246) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1226 = CounterChain.copy("x1328", "x1226")
      val x1234_unitcc = CounterChain(name = "x1234_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1226(0))), op=Bypass, results=List(CU.scalarOut(stage(1), x1238_mc.ofs)))
      Stage(stage(2), operands=List(Const("96i")), op=Bypass, results=List(CU.scalarOut(stage(2), x1238_mc.len)))
    }
    val x1262 = StreamController(name = "x1262", parent=x1328) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1262_unitcc = CounterChain(name = "x1262_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x1250 = StreamPipeline(name = "x1250", parent=x1262) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1226 = CounterChain.copy("x1328", "x1226")
      val x1250_unitcc = CounterChain(name = "x1250_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1226(0))), op=Bypass, results=List(CU.scalarOut(stage(1), x1254_mc.ofs)))
      Stage(stage(2), operands=List(Const("96i")), op=Bypass, results=List(CU.scalarOut(stage(2), x1254_mc.len)))
    }
    val x1278 = StreamController(name = "x1278", parent=x1328) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1278_unitcc = CounterChain(name = "x1278_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x1266 = StreamPipeline(name = "x1266", parent=x1278) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1226 = CounterChain.copy("x1328", "x1226")
      val x1266_unitcc = CounterChain(name = "x1266_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1226(0))), op=Bypass, results=List(CU.scalarOut(stage(1), x1270_mc.ofs)))
      Stage(stage(2), operands=List(Const("96i")), op=Bypass, results=List(CU.scalarOut(stage(2), x1270_mc.len)))
    }
    val x1294 = StreamController(name = "x1294", parent=x1328) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1294_unitcc = CounterChain(name = "x1294_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x1282 = StreamPipeline(name = "x1282", parent=x1294) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1226 = CounterChain.copy("x1328", "x1226")
      val x1282_unitcc = CounterChain(name = "x1282_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1226(0))), op=Bypass, results=List(CU.scalarOut(stage(1), x1286_mc.ofs)))
      Stage(stage(2), operands=List(Const("96i")), op=Bypass, results=List(CU.scalarOut(stage(2), x1286_mc.len)))
    }
    val x1321 = Pipeline(name = "x1321", parent=x1328) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr140 = CU.temp
      val tr142 = CU.temp
      val tr143 = CU.temp
      val tr144 = CU.temp
      val tr145 = CU.temp
      val tr146 = CU.temp
      val tr147 = CU.temp
      val tr149 = CU.temp
      val tr150 = CU.temp
      val tr151 = CU.temp
      val tr152 = CU.temp
      val ctr6 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x1298 = CounterChain(name = "x1298", ctr6)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(12)
      Stage(stage(1), operands=List(Const("0i"), CU.vecIn(stage(0), x1227_0_rd_vector)), op=FixLt, results=List(CU.temp(stage(1), tr140)))
      Stage(stage(2), operands=List(CU.vecIn(stage(1), x1227_0_rd_vector), Const("9999i")), op=FixLt, results=List(CU.temp(stage(2), tr142)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr140), CU.temp(stage(2), tr142)), op=BitAnd, results=List(CU.temp(stage(3), tr143)))
      Stage(stage(4), operands=List(Const("0i"), CU.vecIn(stage(3), x1229_0_rd_vector)), op=FixLeq, results=List(CU.temp(stage(4), tr144)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr143), CU.temp(stage(4), tr144)), op=BitAnd, results=List(CU.temp(stage(5), tr145)))
      Stage(stage(6), operands=List(CU.vecIn(stage(5), x1229_0_rd_vector), Const("9999i")), op=FixLeq, results=List(CU.temp(stage(6), tr146)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr145), CU.temp(stage(6), tr146)), op=BitAnd, results=List(CU.temp(stage(7), tr147)))
      Stage(stage(8), operands=List(CU.vecIn(stage(7), x1228_0_rd_vector), Const("24i")), op=FixLt, results=List(CU.temp(stage(8), tr149)))
      Stage(stage(9), operands=List(CU.temp(stage(8), tr147), CU.temp(stage(8), tr149)), op=BitAnd, results=List(CU.temp(stage(9), tr150)))
      Stage(stage(10), operands=List(CU.vecIn(stage(9), x1230_0_rd_vector), CU.vecIn(stage(9), x1229_0_rd_vector)), op=FixMul, results=List(CU.temp(stage(10), tr151)))
      Stage(stage(11), operands=List(CU.temp(stage(10), tr150), CU.temp(stage(10), tr151), Const("0i")), op=Mux, results=List(CU.reduce(stage(11)), CU.temp(stage(11), tr152)))
      val (rs1, rr155) = Stage.reduce(op=FixAdd, init=Const("0i"))
      Stage(stage(12), operands=List(rr155), op=Bypass, results=List(CU.scalarOut(stage(12), x1296_scalar)))
    }
    val x1327 = UnitPipeline(name = "x1327", parent=x1328) { implicit CU => 
      val stage0 = CU.emptyStage
      val ar159 = CU.accum(init = Const("0i"))
      val x1327_unitcc = CounterChain(name = "x1327_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x1296_scalar)), op=Bypass, results=List(CU.reduce(stage(1))))
      val (rs1, rr162) = Stage.reduce(op=FixAdd, init=Const("0i"))
      Stage(stage(2), operands=List(CU.accum(stage(2), ar159)), op=Bypass, results=List(CU.scalarOut(stage(2), x1217_argout)))
    }
    
  }
}
