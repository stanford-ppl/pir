import pir.graph
import pir.graph._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.util.enums._
import pir.util._
import pir.PIRApp

object DotProduct extends PIRApp {
  def main(top:Top) = {
    val x1255_b1397_x1263_b1399_s = Scalar("x1255_b1397_x1263_b1399")
    val x1297_x1306_data_v = Vector("x1297_x1306_data")
    val x1315_b1409_x1323_b1411_s = Scalar("x1315_b1409_x1323_b1411")
    val x1315_b1408_x1323_b1410_s = Scalar("x1315_b1408_x1323_b1410")
    val x1252_x1354_x1361_v = Vector("x1252_x1354_x1361")
    val b_oc = OffChip("b")
    val x1250_x1353_x1361_v = Vector("x1250_x1353_x1361")
    val x1251_x1342_x1349_v = Vector("x1251_x1342_x1349")
    val x1316_x1325_data_v = Vector("x1316_x1325_data")
    val a_oc = OffChip("a")
    val x1255_b1396_x1263_b1398_s = Scalar("x1255_b1396_x1263_b1398")
    val N_argin = ArgIn("N").bound(1920000)
    val x1296_b1404_x1304_b1406_s = Scalar("x1296_b1404_x1304_b1406")
    val x1337_x1360_s = Scalar("x1337_x1360")
    val x1296_b1405_x1304_b1407_s = Scalar("x1296_b1405_x1304_b1407")
    val a_da = DRAMAddress("a", "a")
    val x1249_x1341_x1349_v = Vector("x1249_x1341_x1349")
    val x1336_x1348_s = Scalar("x1336_x1348")
    val x1274_b1400_x1282_b1402_s = Scalar("x1274_b1400_x1282_b1402")
    val x1274_b1401_x1282_b1403_s = Scalar("x1274_b1401_x1282_b1403")
    val b_da = DRAMAddress("b", "b")
    val x1256_x1265_data_v = Vector("x1256_x1265_data")
    val x1242_x1375_argout = ArgOut("x1242_x1375")
    val x1275_x1284_data_v = Vector("x1275_x1284_data")
    val x1377 = Sequential(name="x1377",parent=top) { implicit CU => 
      val x1377_unit = CounterChain(name = "x1377_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x1373 = MetaPipeline(name="x1373",parent=x1377) { implicit CU => 
      val x1235_x1246 = ScalarBuffer().wtPort(N_argin)
      val ctr1 = Counter(min=Const(0), max=x1235_x1246.load, step=Const(16), par=2) // Counter
      val x1248 = CounterChain(name = "x1248", ctr1).iter(60000)
    }
    val x1249_dsp0 = MemoryPipeline(name="x1249_dsp0",parent="x1373") { implicit CU => 
      val x1271_x1271 = VectorFIFO(size=1).wtPort(x1256_x1265_data_v)
      val x1267 = CounterChain.copy("x1272", "x1267")
      val x1339 = CounterChain.copy("x1349_0", "x1339")
      val x1249_x1341 = SRAM(size=16,banking = Strided(1)).wtPort(x1271_x1271.readPort).rdPort(x1249_x1341_x1349_v).rdAddr(x1339(0)).wtAddr(x1267(0))
    }
    val x1250_dsp0 = MemoryPipeline(name="x1250_dsp0",parent="x1373") { implicit CU => 
      val x1312_x1312 = VectorFIFO(size=1).wtPort(x1297_x1306_data_v)
      val x1308 = CounterChain.copy("x1313", "x1308")
      val x1351 = CounterChain.copy("x1361_0", "x1351")
      val x1250_x1353 = SRAM(size=16,banking = Strided(1)).wtPort(x1312_x1312.readPort).rdPort(x1250_x1353_x1361_v).rdAddr(x1351(0)).wtAddr(x1308(0))
    }
    val x1251_dsp0 = MemoryPipeline(name="x1251_dsp0",parent="x1373") { implicit CU => 
      val x1290_x1290 = VectorFIFO(size=1).wtPort(x1275_x1284_data_v)
      val x1286 = CounterChain.copy("x1291", "x1286")
      val x1339 = CounterChain.copy("x1349_0", "x1339")
      val x1251_x1342 = SRAM(size=16,banking = Strided(1)).wtPort(x1290_x1290.readPort).rdPort(x1251_x1342_x1349_v).rdAddr(x1339(0)).wtAddr(x1286(0))
    }
    val x1252_dsp0 = MemoryPipeline(name="x1252_dsp0",parent="x1373") { implicit CU => 
      val x1331_x1331 = VectorFIFO(size=1).wtPort(x1316_x1325_data_v)
      val x1327 = CounterChain.copy("x1332", "x1327")
      val x1351 = CounterChain.copy("x1361_0", "x1351")
      val x1252_x1354 = SRAM(size=16,banking = Strided(1)).wtPort(x1331_x1331.readPort).rdPort(x1252_x1354_x1361_v).rdAddr(x1351(0)).wtAddr(x1327(0))
    }
    val x1273 = StreamController(name="x1273",parent=x1373) { implicit CU => 
      val x1273_unit = CounterChain(name = "x1273_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x1264_0 = Pipeline(name="x1264_0",parent=x1273) { implicit CU => 
      val x1257 = CU.temp
      val x1259 = ScalarBuffer().wtPort(a_da)
      val x1248 = CounterChain.copy("x1373", "x1248")
      val x1264_unit = CounterChain(name = "x1264_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x1248(0)), Const(4)), op=FixMul, results=List(x1257))
      Stage(operands=List(x1257, CU.load(x1259)), op=FixAdd, results=List(CU.scalarOut(x1255_b1396_x1263_b1398_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x1255_b1397_x1263_b1399_s)))
    }
    val x1265 = MemoryController(name="x1265",parent=x1273,offchip=a_oc, mctpe=TileLoad) { implicit CU => 
      val x1255_b1396_x1265 = ScalarFIFO(name="offset",size=1).wtPort(x1255_b1396_x1263_b1398_s)
      val x1255_b1397_x1265 = ScalarFIFO(name="size",size=1).wtPort(x1255_b1397_x1263_b1399_s)
      CU.newVout("data", x1256_x1265_data_v)
    }
    val x1272 = Pipeline(name="x1272",parent=x1273) { implicit CU => 
      val ctr2 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x1267 = CounterChain(name = "x1267", ctr2).iter(1)
    }
    val x1292 = StreamController(name="x1292",parent=x1373) { implicit CU => 
      val x1292_unit = CounterChain(name = "x1292_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x1283_0 = Pipeline(name="x1283_0",parent=x1292) { implicit CU => 
      val x1276 = CU.temp
      val x1278 = ScalarBuffer().wtPort(b_da)
      val x1248 = CounterChain.copy("x1373", "x1248")
      val x1283_unit = CounterChain(name = "x1283_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x1248(0)), Const(4)), op=FixMul, results=List(x1276))
      Stage(operands=List(x1276, CU.load(x1278)), op=FixAdd, results=List(CU.scalarOut(x1274_b1400_x1282_b1402_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x1274_b1401_x1282_b1403_s)))
    }
    val x1284 = MemoryController(name="x1284",parent=x1292,offchip=b_oc, mctpe=TileLoad) { implicit CU => 
      val x1274_b1401_x1284 = ScalarFIFO(name="size",size=1).wtPort(x1274_b1401_x1282_b1403_s)
      val x1274_b1400_x1284 = ScalarFIFO(name="offset",size=1).wtPort(x1274_b1400_x1282_b1402_s)
      CU.newVout("data", x1275_x1284_data_v)
    }
    val x1291 = Pipeline(name="x1291",parent=x1292) { implicit CU => 
      val ctr3 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x1286 = CounterChain(name = "x1286", ctr3).iter(1)
    }
    val x1314 = StreamController(name="x1314",parent=x1373) { implicit CU => 
      val x1314_unit = CounterChain(name = "x1314_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x1305_0 = Pipeline(name="x1305_0",parent=x1314) { implicit CU => 
      val x1298 = CU.temp
      val x1300 = ScalarBuffer().wtPort(a_da)
      val x1248 = CounterChain.copy("x1373", "x1248")
      val x1305_unit = CounterChain(name = "x1305_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x1248(0)), Const(4)), op=FixMul, results=List(x1298))
      Stage(operands=List(x1298, CU.load(x1300)), op=FixAdd, results=List(CU.scalarOut(x1296_b1404_x1304_b1406_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x1296_b1405_x1304_b1407_s)))
    }
    val x1306 = MemoryController(name="x1306",parent=x1314,offchip=a_oc, mctpe=TileLoad) { implicit CU => 
      val x1296_b1405_x1306 = ScalarFIFO(name="size",size=1).wtPort(x1296_b1405_x1304_b1407_s)
      val x1296_b1404_x1306 = ScalarFIFO(name="offset",size=1).wtPort(x1296_b1404_x1304_b1406_s)
      CU.newVout("data", x1297_x1306_data_v)
    }
    val x1313 = Pipeline(name="x1313",parent=x1314) { implicit CU => 
      val ctr4 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x1308 = CounterChain(name = "x1308", ctr4).iter(1)
    }
    val x1333 = StreamController(name="x1333",parent=x1373) { implicit CU => 
      val x1333_unit = CounterChain(name = "x1333_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x1324_0 = Pipeline(name="x1324_0",parent=x1333) { implicit CU => 
      val x1317 = CU.temp
      val x1319 = ScalarBuffer().wtPort(b_da)
      val x1248 = CounterChain.copy("x1373", "x1248")
      val x1324_unit = CounterChain(name = "x1324_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x1248(0)), Const(4)), op=FixMul, results=List(x1317))
      Stage(operands=List(x1317, CU.load(x1319)), op=FixAdd, results=List(CU.scalarOut(x1315_b1408_x1323_b1410_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x1315_b1409_x1323_b1411_s)))
    }
    val x1325 = MemoryController(name="x1325",parent=x1333,offchip=b_oc, mctpe=TileLoad) { implicit CU => 
      val x1315_b1408_x1325 = ScalarFIFO(name="offset",size=1).wtPort(x1315_b1408_x1323_b1410_s)
      val x1315_b1409_x1325 = ScalarFIFO(name="size",size=1).wtPort(x1315_b1409_x1323_b1411_s)
      CU.newVout("data", x1316_x1325_data_v)
    }
    val x1332 = Pipeline(name="x1332",parent=x1333) { implicit CU => 
      val ctr5 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x1327 = CounterChain(name = "x1327", ctr5).iter(1)
    }
    val x1349_0 = Pipeline(name="x1349_0",parent=x1373) { implicit CU => 
      val x1342_x1342 = VectorFIFO(size=1).wtPort(x1251_x1342_x1349_v)
      val x1341_x1341 = VectorFIFO(size=1).wtPort(x1249_x1341_x1349_v)
      val ctr6 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x1339 = CounterChain(name = "x1339", ctr6).iter(1)
      Stage(operands=List(CU.load(x1341_x1341), CU.load(x1342_x1342)), op=FixMul, results=List(CU.reduce))
      val (_, rr255) = Stage.reduce(op=FixAdd, init=Const(0), accumParent="x1349_0")
      Stage(operands=List(rr255), op=Bypass, results=List(CU.scalarOut(x1336_x1348_s)))
    }
    val x1361_0 = Pipeline(name="x1361_0",parent=x1373) { implicit CU => 
      val x1354_x1354 = VectorFIFO(size=1).wtPort(x1252_x1354_x1361_v)
      val x1353_x1353 = VectorFIFO(size=1).wtPort(x1250_x1353_x1361_v)
      val ctr7 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x1351 = CounterChain(name = "x1351", ctr7).iter(1)
      Stage(operands=List(CU.load(x1353_x1353), CU.load(x1354_x1354)), op=FixMul, results=List(CU.reduce))
      val (_, rr260) = Stage.reduce(op=FixAdd, init=Const(0), accumParent="x1361_0")
      Stage(operands=List(rr260), op=Bypass, results=List(CU.scalarOut(x1337_x1360_s)))
    }
    val x1372_0 = Pipeline(name="x1372_0",parent=x1373) { implicit CU => 
      val x1336_x1364 = ScalarBuffer().wtPort(x1336_x1348_s)
      val x1337_x1363 = ScalarBuffer().wtPort(x1337_x1360_s)
      val x1372_unit = CounterChain(name = "x1372_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x1336_x1364), CU.load(x1337_x1363)), op=FixAdd, results=List(CU.reduce))
      val (_, rr264) = Stage.reduce(op=FixAdd, init=Const(0), accumParent="x1373")
      Stage(operands=List(rr264), op=Bypass, results=List(CU.scalarOut(x1242_x1375_argout)))
    }
    
  }
}
