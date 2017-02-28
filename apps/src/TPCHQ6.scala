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
    val x1236_scalar = Scalar("x1236")
    val x1157_oc = OffChip("x1157")
    val x1172_0_rd_vector = Vector("x1172_0_rd")
    val x1152_argin = ArgIn("x1152")
    val x1162_argout = ArgOut("x1162")
    val x1155_oc = OffChip("x1155")
    val x1173_0_rd_vector = Vector("x1173_0_rd")
    val x1159_oc = OffChip("x1159")
    val x1174_0_rd_vector = Vector("x1174_0_rd")
    val x1171_0_rd_vector = Vector("x1171_0_rd")
    val x1161_oc = OffChip("x1161")
    val x1226_mc = MemoryController(TileLoad, x1161_oc).parent("x1234")
    val x1181_mc = MemoryController(TileLoad, x1155_oc).parent("x1189")
    val x1196_mc = MemoryController(TileLoad, x1157_oc).parent("x1204")
    val x1211_mc = MemoryController(TileLoad, x1159_oc).parent("x1219")
    val x1271 = Sequential(name = "x1271", parent=top) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1271_unitcc = CounterChain(name = "x1271_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x1267 = MetaPipeline(name = "x1267", parent=x1271) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr1 = (Const("0i").out, CU.scalarIn(stage0, x1152_argin), Const("96i").out) // Counter
      val x1170 = CounterChain(name = "x1170", ctr1)
      var stage: List[Stage] = Nil
    }
    val x1171_dsp0 = MemoryPipeline(name = "x1171_dsp0") { implicit CU => 
      val stage0 = CU.emptyStage
      val x1238 = CounterChain.copy("x1261", "x1238")
      val x1171_x1239 = SemiFIFO(size = 96, banking = Strided(1)).wtPort(x1181_mc.data).rdPort(x1171_0_rd_vector).rdAddr(x1238(0))
      var stage: List[Stage] = Nil
    }
    val x1172_dsp0 = MemoryPipeline(name = "x1172_dsp0") { implicit CU => 
      val stage0 = CU.emptyStage
      val x1238 = CounterChain.copy("x1261", "x1238")
      val x1172_x1241 = SemiFIFO(size = 96, banking = Strided(1)).wtPort(x1196_mc.data).rdPort(x1172_0_rd_vector).rdAddr(x1238(0))
      var stage: List[Stage] = Nil
    }
    val x1173_dsp0 = MemoryPipeline(name = "x1173_dsp0") { implicit CU => 
      val stage0 = CU.emptyStage
      val x1238 = CounterChain.copy("x1261", "x1238")
      val x1173_x1240 = SemiFIFO(size = 96, banking = Strided(1)).wtPort(x1211_mc.data).rdPort(x1173_0_rd_vector).rdAddr(x1238(0))
      var stage: List[Stage] = Nil
    }
    val x1174_dsp0 = MemoryPipeline(name = "x1174_dsp0") { implicit CU => 
      val stage0 = CU.emptyStage
      val x1238 = CounterChain.copy("x1261", "x1238")
      val x1174_x1242 = SemiFIFO(size = 96, banking = Strided(1)).wtPort(x1226_mc.data).rdPort(x1174_0_rd_vector).rdAddr(x1238(0))
      var stage: List[Stage] = Nil
    }
    val x1189 = StreamController(name = "x1189", parent=x1267) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1189_unitcc = CounterChain(name = "x1189_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x1178 = StreamPipeline(name = "x1178", parent=x1189) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1170 = CounterChain.copy("x1267", "x1170")
      val x1178_unitcc = CounterChain(name = "x1178_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1170(0))), op=Bypass, results=List(CU.scalarOut(stage(1), x1181_mc.ofs)))
      Stage(stage(2), operands=List(Const("96i")), op=Bypass, results=List(CU.scalarOut(stage(2), x1181_mc.len)))
    }
    val x1204 = StreamController(name = "x1204", parent=x1267) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1204_unitcc = CounterChain(name = "x1204_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x1193 = StreamPipeline(name = "x1193", parent=x1204) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1170 = CounterChain.copy("x1267", "x1170")
      val x1193_unitcc = CounterChain(name = "x1193_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1170(0))), op=Bypass, results=List(CU.scalarOut(stage(1), x1196_mc.ofs)))
      Stage(stage(2), operands=List(Const("96i")), op=Bypass, results=List(CU.scalarOut(stage(2), x1196_mc.len)))
    }
    val x1219 = StreamController(name = "x1219", parent=x1267) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1219_unitcc = CounterChain(name = "x1219_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x1208 = StreamPipeline(name = "x1208", parent=x1219) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1170 = CounterChain.copy("x1267", "x1170")
      val x1208_unitcc = CounterChain(name = "x1208_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1170(0))), op=Bypass, results=List(CU.scalarOut(stage(1), x1211_mc.ofs)))
      Stage(stage(2), operands=List(Const("96i")), op=Bypass, results=List(CU.scalarOut(stage(2), x1211_mc.len)))
    }
    val x1234 = StreamController(name = "x1234", parent=x1267) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1234_unitcc = CounterChain(name = "x1234_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x1223 = StreamPipeline(name = "x1223", parent=x1234) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1170 = CounterChain.copy("x1267", "x1170")
      val x1223_unitcc = CounterChain(name = "x1223_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1170(0))), op=Bypass, results=List(CU.scalarOut(stage(1), x1226_mc.ofs)))
      Stage(stage(2), operands=List(Const("96i")), op=Bypass, results=List(CU.scalarOut(stage(2), x1226_mc.len)))
    }
    val x1261 = Pipeline(name = "x1261", parent=x1267) { implicit CU => 
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
      val x1238 = CounterChain(name = "x1238", ctr6)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(12)
      Stage(stage(1), operands=List(Const("0i"), CU.vecIn(stage(0), x1171_0_rd_vector)), op=FixLt, results=List(CU.temp(stage(1), tr140)))
      Stage(stage(2), operands=List(CU.vecIn(stage(1), x1171_0_rd_vector), Const("9999i")), op=FixLt, results=List(CU.temp(stage(2), tr142)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr140), CU.temp(stage(2), tr142)), op=BitAnd, results=List(CU.temp(stage(3), tr143)))
      Stage(stage(4), operands=List(Const("0i"), CU.vecIn(stage(3), x1173_0_rd_vector)), op=FixLeq, results=List(CU.temp(stage(4), tr144)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr143), CU.temp(stage(4), tr144)), op=BitAnd, results=List(CU.temp(stage(5), tr145)))
      Stage(stage(6), operands=List(CU.vecIn(stage(5), x1173_0_rd_vector), Const("9999i")), op=FixLeq, results=List(CU.temp(stage(6), tr146)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr145), CU.temp(stage(6), tr146)), op=BitAnd, results=List(CU.temp(stage(7), tr147)))
      Stage(stage(8), operands=List(CU.vecIn(stage(7), x1172_0_rd_vector), Const("24i")), op=FixLt, results=List(CU.temp(stage(8), tr149)))
      Stage(stage(9), operands=List(CU.temp(stage(8), tr147), CU.temp(stage(8), tr149)), op=BitAnd, results=List(CU.temp(stage(9), tr150)))
      Stage(stage(10), operands=List(CU.vecIn(stage(9), x1174_0_rd_vector), CU.vecIn(stage(9), x1173_0_rd_vector)), op=FixMul, results=List(CU.temp(stage(10), tr151)))
      Stage(stage(11), operands=List(CU.temp(stage(10), tr150), CU.temp(stage(10), tr151), Const("0i")), op=Mux, results=List(CU.reduce(stage(11)), CU.temp(stage(11), tr152)))
      val (rs1, rr155) = Stage.reduce(op=FixAdd, init=Const("0i"))
      Stage(stage(12), operands=List(rr155), op=Bypass, results=List(CU.scalarOut(stage(12), x1236_scalar)))
    }
    val x1266 = UnitPipeline(name = "x1266", parent=x1267) { implicit CU => 
      val stage0 = CU.emptyStage
      val ar159 = CU.accum(init = Const("0i"))
      val x1266_unitcc = CounterChain(name = "x1266_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x1236_scalar)), op=Bypass, results=List(CU.reduce(stage(1))))
      val (rs1, rr162) = Stage.reduce(op=FixAdd, init=Const("0i"))
      Stage(stage(2), operands=List(CU.accum(stage(2), ar159)), op=Bypass, results=List(CU.scalarOut(stage(2), x1162_argout)))
    }
    
  }
}
