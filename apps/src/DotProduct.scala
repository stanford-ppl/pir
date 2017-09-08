import pir.graph.{Mux =>_, _}
import pir.codegen._
import pir.spade.config._
import pir.Design
import pir.util.enums._
import pir.util._
import pir.PIRApp

object DotProduct extends PIRApp {
  def main(top:Top) = {
    val x1338_b1435_x1347_b1437_s = Scalar("x1338_b1435_x1347_b1437")
    val b_oc = OffChip("b")
    val x1338_b1436_x1347_b1438_s = Scalar("x1338_b1436_x1347_b1438")
    val x1295_b1426_x1304_b1428_s = Scalar("x1295_b1426_x1304_b1428")
    val a_oc = OffChip("a")
    val x1319_x1329_data_s = Scalar("x1319_x1329_data")
    val x1269_x1292_wa_s = Scalar("x1269_x1292_wa")
    val x1269_x1269_dsp0_bank0_data_s = Scalar("x1269_x1269_dsp0_bank0_data")
    val x1271_x1271_dsp0_bank0_data_s = Scalar("x1271_x1271_dsp0_bank0_data")
    val x1270_x1335_wa_s = Scalar("x1270_x1335_wa")
    val x1295_b1425_x1304_b1427_s = Scalar("x1295_b1425_x1304_b1427")
    val b_da = DRAMAddress("b", "b")
    val x1272_x1272_dsp0_bank0_data_s = Scalar("x1272_x1272_dsp0_bank0_data")
    val x1270_x1270_dsp0_bank0_data_s = Scalar("x1270_x1270_dsp0_bank0_data")
    val x1318_b1430_x1327_b1432_s = Scalar("x1318_b1430_x1327_b1432")
    val x1275_b1420_x1284_b1422_s = Scalar("x1275_b1420_x1284_b1422")
    val x1269_x1365_ra_s = Scalar("x1269_x1365_ra")
    val x1318_b1431_x1327_b1433_s = Scalar("x1318_b1431_x1327_b1433")
    val x1360_x1372_s = Scalar("x1360_x1372")
    val x1276_x1286_data_s = Scalar("x1276_x1286_data")
    val x1270_x1377_ra_s = Scalar("x1270_x1377_ra")
    val x1271_x1366_ra_s = Scalar("x1271_x1366_ra")
    val x1272_x1355_wa_s = Scalar("x1272_x1355_wa")
    val a_da = DRAMAddress("a", "a")
    val x1272_x1378_ra_s = Scalar("x1272_x1378_ra")
    val x1339_x1349_data_s = Scalar("x1339_x1349_data")
    val x1361_x1384_s = Scalar("x1361_x1384")
    val x1271_x1312_wa_s = Scalar("x1271_x1312_wa")
    val x1262_x1399_argout = ArgOut("x1262_x1399")
    val x1275_b1421_x1284_b1423_s = Scalar("x1275_b1421_x1284_b1423")
    val x1296_x1306_data_s = Scalar("x1296_x1306_data")
    val N_argin = ArgIn("N").bound(1920000)
    val x1401 = Sequential(name="x1401",parent=top) { implicit CU => 
      val x1401_unit = CounterChain(name = "x1401_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x1397 = MetaPipeline(name="x1397",parent=x1401) { implicit CU => 
      val x1255 = ScalarBuffer(name="x1255").wtPort(N_argin)
      val ctr1 = Counter(min=Const(0), max=x1255.readPort, step=Const(16), par=2) // Counter
      val x1268 = CounterChain(name = "x1268", ctr1).iter(60000)
    }
    val x1269_dsp0_bank0 = MemoryPipeline(name="x1269_dsp0_bank0",parent="x1397") { implicit CU => 
      val b1440 = ScalarFIFO(size=1,name="b1440").wtPort(x1269_x1365_ra_s)
      val b1424 = ScalarFIFO(size=1,name="b1424").wtPort(x1269_x1292_wa_s)
      val x1292 = ScalarFIFO(size=1,name="x1292").wtPort(x1276_x1286_data_s)
      val x1269 = SRAM(size=1,name="x1269",banking = Strided(1,16)).wtPort(x1292.readPort).rdPort(x1269_x1269_dsp0_bank0_data_s).rdAddr(b1440.readPort).wtAddr(b1424.readPort)
    }
    val x1270_dsp0_bank0 = MemoryPipeline(name="x1270_dsp0_bank0",parent="x1397") { implicit CU => 
      val b1434 = ScalarFIFO(size=1,name="b1434").wtPort(x1270_x1335_wa_s)
      val x1335 = ScalarFIFO(size=1,name="x1335").wtPort(x1319_x1329_data_s)
      val b1442 = ScalarFIFO(size=1,name="b1442").wtPort(x1270_x1377_ra_s)
      val x1270 = SRAM(size=1,name="x1270",banking = Strided(1,16)).wtPort(x1335.readPort).rdPort(x1270_x1270_dsp0_bank0_data_s).rdAddr(b1442.readPort).wtAddr(b1434.readPort)
    }
    val x1271_dsp0_bank0 = MemoryPipeline(name="x1271_dsp0_bank0",parent="x1397") { implicit CU => 
      val b1429 = ScalarFIFO(size=1,name="b1429").wtPort(x1271_x1312_wa_s)
      val b1441 = ScalarFIFO(size=1,name="b1441").wtPort(x1271_x1366_ra_s)
      val x1312 = ScalarFIFO(size=1,name="x1312").wtPort(x1296_x1306_data_s)
      val x1271 = SRAM(size=1,name="x1271",banking = Strided(1,16)).wtPort(x1312.readPort).rdPort(x1271_x1271_dsp0_bank0_data_s).rdAddr(b1441.readPort).wtAddr(b1429.readPort)
    }
    val x1272_dsp0_bank0 = MemoryPipeline(name="x1272_dsp0_bank0",parent="x1397") { implicit CU => 
      val b1443 = ScalarFIFO(size=1,name="b1443").wtPort(x1272_x1378_ra_s)
      val b1439 = ScalarFIFO(size=1,name="b1439").wtPort(x1272_x1355_wa_s)
      val x1355 = ScalarFIFO(size=1,name="x1355").wtPort(x1339_x1349_data_s)
      val x1272 = SRAM(size=1,name="x1272",banking = Strided(1,16)).wtPort(x1355.readPort).rdPort(x1272_x1272_dsp0_bank0_data_s).rdAddr(b1443.readPort).wtAddr(b1439.readPort)
    }
    val x1294 = StreamController(name="x1294",parent=x1397) { implicit CU => 
      val x1294_unit = CounterChain(name = "x1294_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x1285 = Pipeline(name="x1285",parent=x1294) { implicit CU => 
      val x1278 = CU.temp(None)
      val x1280 = ScalarBuffer(name="x1280").wtPort(a_da)
      val x1268 = CounterChain.copy("x1397", "x1268").iterIdx(0, 0)
      val x1285_unit = CounterChain(name = "x1285_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x1268(0)), Const(2)), op=FixSla, results=List(x1278))
      Stage(operands=List(x1278, CU.load(x1280)), op=FixAdd, results=List(CU.scalarOut(x1275_b1420_x1284_b1422_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x1275_b1421_x1284_b1423_s)))
    }
    val x1286 = MemoryController(name="x1286",parent=x1294,offchip=a_oc, mctpe=TileLoad) { implicit CU => 
      val x1275_b1420 = ScalarFIFO(size=1,name="offset").wtPort(x1275_b1420_x1284_b1422_s)
      val x1275_b1421 = ScalarFIFO(size=1,name="size").wtPort(x1275_b1421_x1284_b1423_s)
      CU.newSout("data", x1276_x1286_data_s)
    }
    val x1293 = Pipeline(name="x1293",parent=x1294) { implicit CU => 
      val ctr2 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x1288 = CounterChain(name = "x1288", ctr2).iter(1)
    }
    val x1292 = Pipeline(name="x1292",parent=x1294) { implicit CU => 
      val x1288 = CounterChain.copy("x1293", "x1288").iterIdx(0, 0)
      Stage(operands=List(CU.ctr(x1288(0))), op=Bypass, results=List(CU.scalarOut(x1269_x1292_wa_s)))
    }
    val x1314 = StreamController(name="x1314",parent=x1397) { implicit CU => 
      val x1314_unit = CounterChain(name = "x1314_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x1305 = Pipeline(name="x1305",parent=x1314) { implicit CU => 
      val x1298 = CU.temp(None)
      val x1300 = ScalarBuffer(name="x1300").wtPort(b_da)
      val x1268 = CounterChain.copy("x1397", "x1268").iterIdx(0, 0)
      val x1305_unit = CounterChain(name = "x1305_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x1268(0)), Const(2)), op=FixSla, results=List(x1298))
      Stage(operands=List(x1298, CU.load(x1300)), op=FixAdd, results=List(CU.scalarOut(x1295_b1425_x1304_b1427_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x1295_b1426_x1304_b1428_s)))
    }
    val x1306 = MemoryController(name="x1306",parent=x1314,offchip=b_oc, mctpe=TileLoad) { implicit CU => 
      val x1295_b1425 = ScalarFIFO(size=1,name="offset").wtPort(x1295_b1425_x1304_b1427_s)
      val x1295_b1426 = ScalarFIFO(size=1,name="size").wtPort(x1295_b1426_x1304_b1428_s)
      CU.newSout("data", x1296_x1306_data_s)
    }
    val x1313 = Pipeline(name="x1313",parent=x1314) { implicit CU => 
      val ctr3 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x1308 = CounterChain(name = "x1308", ctr3).iter(1)
    }
    val x1312 = Pipeline(name="x1312",parent=x1314) { implicit CU => 
      val x1308 = CounterChain.copy("x1313", "x1308").iterIdx(0, 0)
      Stage(operands=List(CU.ctr(x1308(0))), op=Bypass, results=List(CU.scalarOut(x1271_x1312_wa_s)))
    }
    val x1337 = StreamController(name="x1337",parent=x1397) { implicit CU => 
      val x1337_unit = CounterChain(name = "x1337_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x1328 = Pipeline(name="x1328",parent=x1337) { implicit CU => 
      val x1321 = CU.temp(None)
      val x1323 = ScalarBuffer(name="x1323").wtPort(a_da)
      val x1268 = CounterChain.copy("x1397", "x1268").iterIdx(0, 1)
      val x1328_unit = CounterChain(name = "x1328_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x1268(0)), Const(2)), op=FixSla, results=List(x1321))
      Stage(operands=List(x1321, CU.load(x1323)), op=FixAdd, results=List(CU.scalarOut(x1318_b1430_x1327_b1432_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x1318_b1431_x1327_b1433_s)))
    }
    val x1329 = MemoryController(name="x1329",parent=x1337,offchip=a_oc, mctpe=TileLoad) { implicit CU => 
      val x1318_b1431 = ScalarFIFO(size=1,name="size").wtPort(x1318_b1431_x1327_b1433_s)
      val x1318_b1430 = ScalarFIFO(size=1,name="offset").wtPort(x1318_b1430_x1327_b1432_s)
      CU.newSout("data", x1319_x1329_data_s)
    }
    val x1336 = Pipeline(name="x1336",parent=x1337) { implicit CU => 
      val ctr4 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x1331 = CounterChain(name = "x1331", ctr4).iter(1)
    }
    val x1335 = Pipeline(name="x1335",parent=x1337) { implicit CU => 
      val x1331 = CounterChain.copy("x1336", "x1331").iterIdx(0, 0)
      Stage(operands=List(CU.ctr(x1331(0))), op=Bypass, results=List(CU.scalarOut(x1270_x1335_wa_s)))
    }
    val x1357 = StreamController(name="x1357",parent=x1397) { implicit CU => 
      val x1357_unit = CounterChain(name = "x1357_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x1348 = Pipeline(name="x1348",parent=x1357) { implicit CU => 
      val x1341 = CU.temp(None)
      val x1343 = ScalarBuffer(name="x1343").wtPort(b_da)
      val x1268 = CounterChain.copy("x1397", "x1268").iterIdx(0, 1)
      val x1348_unit = CounterChain(name = "x1348_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x1268(0)), Const(2)), op=FixSla, results=List(x1341))
      Stage(operands=List(x1341, CU.load(x1343)), op=FixAdd, results=List(CU.scalarOut(x1338_b1435_x1347_b1437_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x1338_b1436_x1347_b1438_s)))
    }
    val x1349 = MemoryController(name="x1349",parent=x1357,offchip=b_oc, mctpe=TileLoad) { implicit CU => 
      val x1338_b1436 = ScalarFIFO(size=1,name="size").wtPort(x1338_b1436_x1347_b1438_s)
      val x1338_b1435 = ScalarFIFO(size=1,name="offset").wtPort(x1338_b1435_x1347_b1437_s)
      CU.newSout("data", x1339_x1349_data_s)
    }
    val x1356 = Pipeline(name="x1356",parent=x1357) { implicit CU => 
      val ctr5 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x1351 = CounterChain(name = "x1351", ctr5).iter(1)
    }
    val x1355 = Pipeline(name="x1355",parent=x1357) { implicit CU => 
      val x1351 = CounterChain.copy("x1356", "x1351").iterIdx(0, 0)
      Stage(operands=List(CU.ctr(x1351(0))), op=Bypass, results=List(CU.scalarOut(x1272_x1355_wa_s)))
    }
    val x1373 = Pipeline(name="x1373",parent=x1397) { implicit CU => 
      val x1360 = CU.temp(Some(0))
      val x1366 = ScalarFIFO(size=1,name="x1366").wtPort(x1271_x1271_dsp0_bank0_data_s)
      val x1365 = ScalarFIFO(size=1,name="x1365").wtPort(x1269_x1269_dsp0_bank0_data_s)
      val ctr6 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x1363 = CounterChain(name = "x1363", ctr6).iter(1)
      Stage(operands=List(CU.load(x1365), CU.load(x1366)), op=FixMul, results=List(CU.reduce))
      val (_, rr220) = Stage.reduce(op=FixAdd, init=Const(0), accumParent="x1373")
      Stage(operands=List(rr220), op=Bypass, results=List(CU.scalarOut(x1360_x1372_s)))
    }
    val x1365 = Pipeline(name="x1365",parent=x1397) { implicit CU => 
      val x1363 = CounterChain.copy("x1373", "x1363").iterIdx(0, 0)
      Stage(operands=List(CU.ctr(x1363(0))), op=Bypass, results=List(CU.scalarOut(x1269_x1365_ra_s)))
    }
    val x1366 = Pipeline(name="x1366",parent=x1397) { implicit CU => 
      val x1363 = CounterChain.copy("x1373", "x1363").iterIdx(0, 0)
      Stage(operands=List(CU.ctr(x1363(0))), op=Bypass, results=List(CU.scalarOut(x1271_x1366_ra_s)))
    }
    val x1385 = Pipeline(name="x1385",parent=x1397) { implicit CU => 
      val x1361 = CU.temp(Some(0))
      val x1378 = ScalarFIFO(size=1,name="x1378").wtPort(x1272_x1272_dsp0_bank0_data_s)
      val x1377 = ScalarFIFO(size=1,name="x1377").wtPort(x1270_x1270_dsp0_bank0_data_s)
      val ctr7 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x1375 = CounterChain(name = "x1375", ctr7).iter(1)
      Stage(operands=List(CU.load(x1377), CU.load(x1378)), op=FixMul, results=List(CU.reduce))
      val (_, rr225) = Stage.reduce(op=FixAdd, init=Const(0), accumParent="x1385")
      Stage(operands=List(rr225), op=Bypass, results=List(CU.scalarOut(x1361_x1384_s)))
    }
    val x1377 = Pipeline(name="x1377",parent=x1397) { implicit CU => 
      val x1375 = CounterChain.copy("x1385", "x1375").iterIdx(0, 0)
      Stage(operands=List(CU.ctr(x1375(0))), op=Bypass, results=List(CU.scalarOut(x1270_x1377_ra_s)))
    }
    val x1378 = Pipeline(name="x1378",parent=x1397) { implicit CU => 
      val x1375 = CounterChain.copy("x1385", "x1375").iterIdx(0, 0)
      Stage(operands=List(CU.ctr(x1375(0))), op=Bypass, results=List(CU.scalarOut(x1272_x1378_ra_s)))
    }
    val x1396 = Pipeline(name="x1396",parent=x1397) { implicit CU => 
      val x1265 = CU.temp(Some(0))
      val x1360 = ScalarBuffer(name="x1360").wtPort(x1360_x1372_s)
      val x1361 = ScalarBuffer(name="x1361").wtPort(x1361_x1384_s)
      val x1396_unit = CounterChain(name = "x1396_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x1360), CU.load(x1361)), op=FixAdd, results=List(CU.reduce))
      val (_, rr229) = Stage.reduce(op=FixAdd, init=Const(0), accumParent="x1397")
      Stage(operands=List(rr229), op=Bypass, results=List(CU.scalarOut(x1262_x1399_argout)))
    }
    
  }
}
