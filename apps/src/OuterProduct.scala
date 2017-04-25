import pir.graph
import pir.graph._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.util.enums._
import pir.util._
import pir.PIRApp

object OuterProduct extends PIRApp {
  override val arch = SN_4x4
  def main(args: String*)(top:Top) = {
    val x1216_x1225_data_v = Vector("x1216_x1225_data")
    val x1213_x1263_x1268_v = Vector("x1213_x1263_x1268")
    val x1217_argin = ArgIn("x1217")
    val x1237_argin = ArgIn("x1237")
    val x1235_b1329_x1243_b1331_s = Scalar("x1235_b1329_x1243_b1331")
    val x1215_b1326_x1223_b1328_s = Scalar("x1215_b1326_x1223_b1328")
    val x1271_b1335_x1284_b1337_s = Scalar("x1271_b1335_x1284_b1337")
    val x1235_b1330_x1243_b1332_s = Scalar("x1235_b1330_x1243_b1332")
    val x1274_argin = ArgIn("x1274")
    val x1214_x1267_v = Vector("x1214_x1267")
    val x1204_oc = OffChip("x1204")
    val x1215_b1325_x1223_b1327_s = Scalar("x1215_b1325_x1223_b1327")
    val x1214_x1291_x1295_v = Vector("x1214_x1291_x1295")
    val x1212_x1262_x1268_v = Vector("x1212_x1262_x1268")
    val x1271_b1336_x1284_b1338_s = Scalar("x1271_b1336_x1284_b1338")
    val x1201_oc = OffChip("x1201")
    val x1194_argin = ArgIn("x1194")
    val x1236_x1245_data_v = Vector("x1236_x1245_data")
    val x1195_argin = ArgIn("x1195")
    val x1199_oc = OffChip("x1199")
    val x1308 = Sequential(name="x1308",parent=top) { implicit CU => 
      val ctr1 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x1308_unit = CounterChain(name = "x1308_unit", ctr1)
    }
    val x1307 = MetaPipeline(name="x1307",parent=x1308) { implicit CU => 
      val x1195_x1207 =  ScalarBuffer().wtPort(x1195_argin)
      val x1194_x1209 =  ScalarBuffer().wtPort(x1194_argin)
      val ctr2 = Counter(min=Const(0), max=x1194_x1209.load, step=Const(64), par=1) // Counter
      val ctr3 = Counter(min=Const(0), max=x1195_x1207.load, step=Const(64), par=1) // Counter
      val x1211 = CounterChain(name = "x1211", ctr2, ctr3)
    }
    val x1212_dsp0 = MemoryPipeline(name="x1212_dsp0",parent="x1307") { implicit CU => 
      val x1232_x1232 =  VectorFIFO(size=1).wtPort(x1216_x1225_data_v)
      val x1227 = CounterChain.copy("x1233", "x1227")
      val x1258 = CounterChain.copy("x1268", "x1258")
      val x1212_x1262 =  SRAM(size=64,banking = Strided(1)).wtPort(x1232_x1232.readPort).rdPort(x1212_x1262_x1268_v).rdAddr(x1258(0)).wtAddr(x1227(0))
      var stage: List[Stage] = Nil
    }
    val x1213_dsp0 = MemoryPipeline(name="x1213_dsp0",parent="x1307") { implicit CU => 
      val x1252_x1252 =  VectorFIFO(size=1).wtPort(x1236_x1245_data_v)
      val x1247 = CounterChain.copy("x1253", "x1247")
      val x1258 = CounterChain.copy("x1268", "x1258")
      val x1213_x1263 =  SRAM(size=64,banking = Strided(1)).wtPort(x1252_x1252.readPort).rdPort(x1213_x1263_x1268_v).rdAddr(x1258(1)).wtAddr(x1247(0))
      var stage: List[Stage] = Nil
    }
    val x1214_dsp0 = MemoryPipeline(name="x1214_dsp0",parent="x1307") { implicit CU => 
      val b1333 = CU.temp
      val b1339 = CU.temp
      val x1267_x1267 =  VectorFIFO(size=1).wtPort(x1214_x1267_v)
      val x1258 = CounterChain.copy("x1268", "x1258")
      val x1270 = CounterChain.copy("x1306", "x1270")
      val x1287 = CounterChain.copy("x1295", "x1287")
      val x1214_x1291 =  SRAM(size=4096,banking = Strided(1)).wtPort(x1267_x1267.readPort).rdPort(x1214_x1291_x1295_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x1258(0)), Const(64)), op=FixMul, results=List(b1333))
      WAStage(operands=List(b1333, CU.ctr(x1258(1))), op=FixAdd, results=List(x1214_x1291.writeAddr))
      RAStage(operands=List(CU.ctr(x1270(0)), Const(64)), op=FixMul, results=List(b1339))
      RAStage(operands=List(b1339, CU.ctr(x1287(0))), op=FixAdd, results=List(x1214_x1291.readAddr))
    }
    val x1234 = StreamController(name="x1234",parent=x1307) { implicit CU => 
      val ctr4 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x1234_unit = CounterChain(name = "x1234_unit", ctr4)
    }
    val x1224 = Pipeline(name="x1224",parent=x1234) { implicit CU => 
      val x1218 = CU.temp
      val x1217 =  ScalarBuffer().wtPort(x1217_argin)
      val x1211 = CounterChain.copy("x1307", "x1211")
      val ctr5 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x1224_unit = CounterChain(name = "x1224_unit", ctr5)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x1211(0)), Const(4)), op=FixMul, results=List(x1218))
      Stage(operands=List(x1218, CU.load(x1217)), op=FixAdd, results=List(CU.scalarOut(x1215_b1325_x1223_b1327_s)))
      Stage(operands=List(Const(256)), op=Bypass, results=List(CU.scalarOut(x1215_b1326_x1223_b1328_s)))
    }
    val x1225 = MemoryController(name="x1225",parent=x1234,offchip=x1199_oc, mctpe=TileLoad) { implicit CU => 
      val x1215_b1326_x1225 =  ScalarFIFO(name="size",size=1).wtPort(x1215_b1326_x1223_b1328_s)
      val x1215_b1325_x1225 =  ScalarFIFO(name="offset",size=1).wtPort(x1215_b1325_x1223_b1327_s)
      CU.newVout("data", x1216_x1225_data_v)
    }
    val x1233 = Pipeline(name="x1233",parent=x1234) { implicit CU => 
      val ctr6 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x1227 = CounterChain(name = "x1227", ctr6)
      var stage: List[Stage] = Nil
    }
    val x1254 = StreamController(name="x1254",parent=x1307) { implicit CU => 
      val ctr7 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x1254_unit = CounterChain(name = "x1254_unit", ctr7)
    }
    val x1244 = Pipeline(name="x1244",parent=x1254) { implicit CU => 
      val x1238 = CU.temp
      val x1237 =  ScalarBuffer().wtPort(x1237_argin)
      val x1211 = CounterChain.copy("x1307", "x1211")
      val ctr8 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x1244_unit = CounterChain(name = "x1244_unit", ctr8)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x1211(1)), Const(4)), op=FixMul, results=List(x1238))
      Stage(operands=List(x1238, CU.load(x1237)), op=FixAdd, results=List(CU.scalarOut(x1235_b1329_x1243_b1331_s)))
      Stage(operands=List(Const(256)), op=Bypass, results=List(CU.scalarOut(x1235_b1330_x1243_b1332_s)))
    }
    val x1245 = MemoryController(name="x1245",parent=x1254,offchip=x1201_oc, mctpe=TileLoad) { implicit CU => 
      val x1235_b1330_x1245 =  ScalarFIFO(name="size",size=1).wtPort(x1235_b1330_x1243_b1332_s)
      val x1235_b1329_x1245 =  ScalarFIFO(name="offset",size=1).wtPort(x1235_b1329_x1243_b1331_s)
      CU.newVout("data", x1236_x1245_data_v)
    }
    val x1253 = Pipeline(name="x1253",parent=x1254) { implicit CU => 
      val ctr9 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x1247 = CounterChain(name = "x1247", ctr9)
      var stage: List[Stage] = Nil
    }
    val x1268 = Pipeline(name="x1268",parent=x1307) { implicit CU => 
      val x1213_x1263 =  VectorFIFO(size=1).wtPort(x1213_x1263_x1268_v)
      val x1212_x1262 =  VectorFIFO(size=1).wtPort(x1212_x1262_x1268_v)
      val ctr10 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val ctr11 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val x1258 = CounterChain(name = "x1258", ctr10, ctr11)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x1212_x1262), CU.load(x1213_x1263)), op=FixMul, results=List(CU.vecOut(x1214_x1267_v)))
    }
    val x1306 = StreamController(name="x1306",parent=x1307) { implicit CU => 
      val ctr12 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val x1270 = CounterChain(name = "x1270", ctr12)
    }
    val x1296 = Sequential(name="x1296",parent=x1306) { implicit CU => 
      val ctr13 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x1296_unit = CounterChain(name = "x1296_unit", ctr13)
    }
    val x1285 = Pipeline(name="x1285",parent=x1296) { implicit CU => 
      val x1278 = CU.temp
      val x1277 = CU.temp
      val x1279 = CU.temp
      val x1276 = CU.temp
      val x1195_x1275 =  ScalarBuffer().wtPort(x1195_argin)
      val x1274 =  ScalarBuffer().wtPort(x1274_argin)
      val x1211 = CounterChain.copy("x1307", "x1211")
      val x1270 = CounterChain.copy("x1306", "x1270")
      val ctr14 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x1285_unit = CounterChain(name = "x1285_unit", ctr14)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x1211(0)), CU.ctr(x1270(0))), op=FixAdd, results=List(x1276))
      Stage(operands=List(x1276, CU.load(x1195_x1275)), op=FixMul, results=List(x1277))
      Stage(operands=List(x1277, CU.ctr(x1211(1))), op=FixAdd, results=List(x1278))
      Stage(operands=List(x1278, Const(4)), op=FixMul, results=List(x1279))
      Stage(operands=List(x1279, CU.load(x1274)), op=FixAdd, results=List(CU.scalarOut(x1271_b1335_x1284_b1337_s)))
      Stage(operands=List(Const(256)), op=Bypass, results=List(CU.scalarOut(x1271_b1336_x1284_b1338_s)))
    }
    val x1295 = Pipeline(name="x1295",parent=x1296) { implicit CU => 
      val ctr15 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x1287 = CounterChain(name = "x1287", ctr15)
      var stage: List[Stage] = Nil
    }
    val x1297 = MemoryController(name="x1297",parent=x1306,offchip=x1204_oc, mctpe=TileStore) { implicit CU => 
      val x1271_b1336_x1297 =  ScalarFIFO(name="size",size=1).wtPort(x1271_b1336_x1284_b1338_s)
      val x1271_b1335_x1297 =  ScalarFIFO(name="offset",size=1).wtPort(x1271_b1335_x1284_b1337_s)
      val x1272_x1297 =  VectorFIFO(name="data",size=1).wtPort(x1214_x1291_x1295_v)
    }
    
  }
}
