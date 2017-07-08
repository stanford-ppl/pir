import pir.graph
import pir.graph._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.util.enums._
import pir.util._
import pir.PIRApp

object OuterProduct extends PIRApp {
  override val arch = SN(2,3)
  def main(top:Top) = {
    val x1224_b1338_x1233_b1340_s = Scalar("x1224_b1338_x1233_b1340")
    val x1203_argin = ArgIn("x1203").bound(16)
    val x1245_b1342_x1254_b1344_s = Scalar("x1245_b1342_x1254_b1344")
    val x1221_x1273_x1278_s = Scalar("x1221_x1273_x1278")
    val x1223_x1277_v = Vector("x1223_x1277")
    val x1246_x1256_data_v = Vector("x1246_x1256_data")
    val x1281_b1347_x1296_b1349_s = Scalar("x1281_b1347_x1296_b1349")
    val x1223_x1303_x1307_v = Vector("x1223_x1303_x1307")
    val x1281_b1348_x1296_b1350_s = Scalar("x1281_b1348_x1296_b1350")
    val x1210_oc = OffChip("x1210")
    val x1208_oc = OffChip("x1208")
    val x1284_argin = ArgIn("x1284")
    val x1224_b1337_x1233_b1339_s = Scalar("x1224_b1337_x1233_b1339")
    val x1245_b1341_x1254_b1343_s = Scalar("x1245_b1341_x1254_b1343")
    val x1226_argin = ArgIn("x1226")
    val x1204_argin = ArgIn("x1204").bound(16)
    val x1225_x1235_data_v = Vector("x1225_x1235_data")
    val x1222_x1274_x1278_v = Vector("x1222_x1274_x1278")
    val x1213_oc = OffChip("x1213")
    val x1247_argin = ArgIn("x1247")
    val x1320 = Sequential(name="x1320",parent=top) { implicit CU => 
      val x1320_unit = CounterChain(name = "x1320_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x1319 = MetaPipeline(name="x1319",parent=x1320) { implicit CU => 
      val x1204_x1216 = ScalarBuffer().wtPort(x1204_argin)
      val x1203_x1218 = ScalarBuffer().wtPort(x1203_argin)
      val ctr1 = Counter(min=Const(0), max=x1203_x1218.load, step=Const(16), par=1) // Counter
      val ctr2 = Counter(min=Const(0), max=x1204_x1216.load, step=Const(16), par=1) // Counter
      val x1220 = CounterChain(name = "x1220", ctr1, ctr2).iter(1)
    }
    val x1221_dsp0 = MemoryPipeline(name="x1221_dsp0",parent="x1319") { implicit CU => 
      val x1242_x1242 = VectorFIFO(size=1).wtPort(x1225_x1235_data_v)
      val x1237 = CounterChain.copy("x1243", "x1237")
      val x1269 = CounterChain.copy("x1278_0", "x1269")
      val x1221_x1273 = SRAM(size=16,banking = Strided(1)).wtPort(x1242_x1242.readPort).rdPort(x1221_x1273_x1278_s).rdAddr(x1269(0)).wtAddr(x1237(0))
    }
    val x1222_dsp0 = MemoryPipeline(name="x1222_dsp0",parent="x1319") { implicit CU => 
      val x1263_x1263 = VectorFIFO(size=1).wtPort(x1246_x1256_data_v)
      val x1258 = CounterChain.copy("x1264", "x1258")
      val x1269 = CounterChain.copy("x1278_0", "x1269")
      val x1222_x1274 = SRAM(size=16,banking = Strided(1)).wtPort(x1263_x1263.readPort).rdPort(x1222_x1274_x1278_v).rdAddr(x1269(1)).wtAddr(x1258(0))
    }
    val x1223_dsp0 = MemoryPipeline(name="x1223_dsp0",parent="x1319") { implicit CU => 
      val b1345 = CU.temp
      val b1351 = CU.temp
      val x1277_x1277 = VectorFIFO(size=1).wtPort(x1223_x1277_v)
      val x1269 = CounterChain.copy("x1278_0", "x1269")
      val x1280 = CounterChain.copy("x1318", "x1280")
      val x1299 = CounterChain.copy("x1307", "x1299")
      val x1223_x1303 = SRAM(size=256,banking = Strided(1)).wtPort(x1277_x1277.readPort).rdPort(x1223_x1303_x1307_v)
      WAStage(operands=List(CU.ctr(x1269(0)), Const(16)), op=FixMul, results=List(b1345))
      WAStage(operands=List(b1345, CU.ctr(x1269(1))), op=FixAdd, results=List(x1223_x1303.writeAddr))
      RAStage(operands=List(CU.ctr(x1280(0)), Const(16)), op=FixMul, results=List(b1351))
      RAStage(operands=List(b1351, CU.ctr(x1299(0))), op=FixAdd, results=List(x1223_x1303.readAddr))
    }
    val x1244 = StreamController(name="x1244",parent=x1319) { implicit CU => 
      val x1244_unit = CounterChain(name = "x1244_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x1234_0 = Pipeline(name="x1234_0",parent=x1244) { implicit CU => 
      val x1227 = CU.temp
      val x1226 = ScalarBuffer().wtPort(x1226_argin)
      val x1220 = CounterChain.copy("x1319", "x1220")
      val x1234_unit = CounterChain(name = "x1234_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x1220(0)), Const(4)), op=FixMul, results=List(x1227))
      Stage(operands=List(x1227, CU.load(x1226)), op=FixAdd, results=List(CU.scalarOut(x1224_b1337_x1233_b1339_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x1224_b1338_x1233_b1340_s)))
    }
    val x1235 = MemoryController(name="x1235",parent=x1244,offchip=x1208_oc, mctpe=TileLoad) { implicit CU => 
      val x1224_b1338_x1235 = ScalarFIFO(name="size",size=1).wtPort(x1224_b1338_x1233_b1340_s)
      val x1224_b1337_x1235 = ScalarFIFO(name="offset",size=1).wtPort(x1224_b1337_x1233_b1339_s)
      CU.newVout("data", x1225_x1235_data_v)
    }
    val x1243 = Pipeline(name="x1243",parent=x1244) { implicit CU => 
      val ctr3 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x1237 = CounterChain(name = "x1237", ctr3).iter(1)
    }
    val x1265 = StreamController(name="x1265",parent=x1319) { implicit CU => 
      val x1265_unit = CounterChain(name = "x1265_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x1255_0 = Pipeline(name="x1255_0",parent=x1265) { implicit CU => 
      val x1248 = CU.temp
      val x1247 = ScalarBuffer().wtPort(x1247_argin)
      val x1220 = CounterChain.copy("x1319", "x1220")
      val x1255_unit = CounterChain(name = "x1255_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x1220(1)), Const(4)), op=FixMul, results=List(x1248))
      Stage(operands=List(x1248, CU.load(x1247)), op=FixAdd, results=List(CU.scalarOut(x1245_b1341_x1254_b1343_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x1245_b1342_x1254_b1344_s)))
    }
    val x1256 = MemoryController(name="x1256",parent=x1265,offchip=x1210_oc, mctpe=TileLoad) { implicit CU => 
      val x1245_b1342_x1256 = ScalarFIFO(name="size",size=1).wtPort(x1245_b1342_x1254_b1344_s)
      val x1245_b1341_x1256 = ScalarFIFO(name="offset",size=1).wtPort(x1245_b1341_x1254_b1343_s)
      CU.newVout("data", x1246_x1256_data_v)
    }
    val x1264 = Pipeline(name="x1264",parent=x1265) { implicit CU => 
      val ctr4 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x1258 = CounterChain(name = "x1258", ctr4).iter(1)
    }
    val x1278_0 = Pipeline(name="x1278_0",parent=x1319) { implicit CU => 
      val x1273_x1273 = ScalarFIFO(size=1).wtPort(x1221_x1273_x1278_s)
      val x1274_x1274 = VectorFIFO(size=1).wtPort(x1222_x1274_x1278_v)
      val ctr5 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val ctr6 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x1269 = CounterChain(name = "x1269", ctr5, ctr6).iter(16)
      Stage(operands=List(CU.load(x1273_x1273), CU.load(x1274_x1274)), op=FixMul, results=List(CU.vecOut(x1223_x1277_v)))
    }
    val x1318 = StreamController(name="x1318",parent=x1319) { implicit CU => 
      val ctr7 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x1280 = CounterChain(name = "x1280", ctr7).iter(16)
    }
    val x1297_0 = Pipeline(name="x1297_0",parent=x1318) { implicit CU => 
      val x1286 = CU.temp
      val x1288 = CU.temp
      val x1287 = CU.temp
      val x1289 = CU.temp
      val x1204_x1285 = ScalarBuffer().wtPort(x1204_argin)
      val x1284 = ScalarBuffer().wtPort(x1284_argin)
      val x1220 = CounterChain.copy("x1319", "x1220")
      val x1280 = CounterChain.copy("x1318", "x1280")
      val x1297_unit = CounterChain(name = "x1297_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x1220(0)), CU.ctr(x1280(0))), op=FixAdd, results=List(x1286))
      Stage(operands=List(x1286, CU.load(x1204_x1285)), op=FixMul, results=List(x1287))
      Stage(operands=List(x1287, CU.ctr(x1220(1))), op=FixAdd, results=List(x1288))
      Stage(operands=List(x1288, Const(4)), op=FixMul, results=List(x1289))
      Stage(operands=List(x1289, CU.load(x1284)), op=FixAdd, results=List(CU.scalarOut(x1281_b1347_x1296_b1349_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x1281_b1348_x1296_b1350_s)))
    }
    val x1307 = Pipeline(name="x1307",parent=x1318) { implicit CU => 
      val ctr8 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x1299 = CounterChain(name = "x1299", ctr8).iter(1)
    }
    val x1309 = MemoryController(name="x1309",parent=x1318,offchip=x1213_oc, mctpe=TileStore) { implicit CU => 
      val x1282_x1309 = VectorFIFO(name="data",size=1).wtPort(x1223_x1303_x1307_v)
      val x1281_b1348_x1309 = ScalarFIFO(name="size",size=1).wtPort(x1281_b1348_x1296_b1350_s)
      val x1281_b1347_x1309 = ScalarFIFO(name="offset",size=1).wtPort(x1281_b1347_x1296_b1349_s)
    }
    
  }
}
