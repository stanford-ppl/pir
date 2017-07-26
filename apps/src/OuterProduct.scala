import pir.graph
import pir.graph._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.util.enums._
import pir.util._
import pir.PIRApp

object OuterProduct_op1 extends PIRApp {
  def main(top:Top) = {
    val x1322_b1442_x1331_b1444_s = Scalar("x1322_b1442_x1331_b1444")
    val x1345_b1445_x1354_b1447_s = Scalar("x1345_b1445_x1354_b1447")
    val x1318_x1374_x1378_v = Vector("x1318_x1374_x1378")
    val x1317_x1373_x1378_s = Scalar("x1317_x1373_x1378")
    val sizeA_argin = ArgIn("sizeA").bound(38400)
    val x1319_x1377_v = Vector("x1319_x1377")
    val x1384_b1452_x1399_b1454_s = Scalar("x1384_b1452_x1399_b1454")
    val out_da = DRAMAddress("out", "out")
    val vec2_oc = OffChip("vec2")
    val x1346_x1356_data_v = Vector("x1346_x1356_data")
    val x1384_b1451_x1399_b1453_s = Scalar("x1384_b1451_x1399_b1453")
    val sizeB_argin = ArgIn("sizeB").bound(38400)
    val x1322_b1441_x1331_b1443_s = Scalar("x1322_b1441_x1331_b1443")
    val vec1_da = DRAMAddress("vec1", "vec1")
    val x1323_x1333_data_v = Vector("x1323_x1333_data")
    val vec1_oc = OffChip("vec1")
    val vec2_da = DRAMAddress("vec2", "vec2")
    val out_oc = OffChip("out")
    val x1319_x1406_x1410_v = Vector("x1319_x1406_x1410")
    val x1345_b1446_x1354_b1448_s = Scalar("x1345_b1446_x1354_b1448")
    val x1424 = Sequential(name="x1424",parent=top) { implicit CU => 
      val x1424_unit = CounterChain(name = "x1424_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x1423 = MetaPipeline(name="x1423",parent=x1424) { implicit CU => 
      val x1300_x1312 = ScalarBuffer().wtPort(sizeB_argin)
      val x1299_x1314 = ScalarBuffer().wtPort(sizeA_argin)
      val ctr1 = Counter(min=Const(0), max=x1299_x1314.load, step=Const(16), par=1) // Counter
      val ctr2 = Counter(min=Const(0), max=x1300_x1312.load, step=Const(16), par=1) // Counter
      val x1316 = CounterChain(name = "x1316", ctr1, ctr2).iter(5760000)
    }
    val x1317_dsp0 = MemoryPipeline(name="x1317_dsp0",parent="x1423") { implicit CU => 
      val x1340_x1340 = VectorFIFO(size=1).wtPort(x1323_x1333_data_v)
      val x1335 = CounterChain.copy("x1341", "x1335")
      val x1369 = CounterChain.copy("x1378_0", "x1369")
      val x1317_x1373 = SRAM(size=16,banking = Strided(1)).wtPort(x1340_x1340.readPort).rdPort(x1317_x1373_x1378_s).rdAddr(x1369(0)).wtAddr(x1335(0))
    }
    val x1318_dsp0 = MemoryPipeline(name="x1318_dsp0",parent="x1423") { implicit CU => 
      val x1363_x1363 = VectorFIFO(size=1).wtPort(x1346_x1356_data_v)
      val x1358 = CounterChain.copy("x1364", "x1358")
      val x1369 = CounterChain.copy("x1378_0", "x1369")
      val x1318_x1374 = SRAM(size=16,banking = Strided(1)).wtPort(x1363_x1363.readPort).rdPort(x1318_x1374_x1378_v).rdAddr(x1369(1)).wtAddr(x1358(0))
    }
    val x1319_dsp0 = MemoryPipeline(name="x1319_dsp0",parent="x1423") { implicit CU => 
      val b1455 = CU.temp
      val b1449 = CU.temp
      val x1377_x1377 = VectorFIFO(size=1).wtPort(x1319_x1377_v)
      val x1369 = CounterChain.copy("x1378_0", "x1369")
      val x1383 = CounterChain.copy("x1422", "x1383")
      val x1402 = CounterChain.copy("x1410", "x1402")
      val x1319_x1406 = SRAM(size=256,banking = Strided(1)).wtPort(x1377_x1377.readPort).rdPort(x1319_x1406_x1410_v)
      WAStage(operands=List(CU.ctr(x1369(0)), Const(16)), op=FixMul, results=List(b1449))
      WAStage(operands=List(b1449, CU.ctr(x1369(1))), op=FixAdd, results=List(x1319_x1406.writeAddr))
      RAStage(operands=List(CU.ctr(x1383(0)), Const(16)), op=FixMul, results=List(b1455))
      RAStage(operands=List(b1455, CU.ctr(x1402(0))), op=FixAdd, results=List(x1319_x1406.readAddr))
    }
    val x1342 = StreamController(name="x1342",parent=x1423) { implicit CU => 
      val x1342_unit = CounterChain(name = "x1342_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x1332_0 = Pipeline(name="x1332_0",parent=x1342) { implicit CU => 
      val x1324 = CU.temp
      val x1326 = ScalarBuffer().wtPort(vec1_da)
      val x1316 = CounterChain.copy("x1423", "x1316")
      val x1332_unit = CounterChain(name = "x1332_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x1316(0)), Const(4)), op=FixMul, results=List(x1324))
      Stage(operands=List(x1324, CU.load(x1326)), op=FixAdd, results=List(CU.scalarOut(x1322_b1441_x1331_b1443_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x1322_b1442_x1331_b1444_s)))
    }
    val x1333 = MemoryController(name="x1333",parent=x1342,offchip=vec1_oc, mctpe=TileLoad) { implicit CU => 
      val x1322_b1441_x1333 = ScalarFIFO(name="offset",size=1).wtPort(x1322_b1441_x1331_b1443_s)
      val x1322_b1442_x1333 = ScalarFIFO(name="size",size=1).wtPort(x1322_b1442_x1331_b1444_s)
      CU.newVout("data", x1323_x1333_data_v)
    }
    val x1341 = Pipeline(name="x1341",parent=x1342) { implicit CU => 
      val ctr3 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x1335 = CounterChain(name = "x1335", ctr3).iter(1)
    }
    val x1365 = StreamController(name="x1365",parent=x1423) { implicit CU => 
      val x1365_unit = CounterChain(name = "x1365_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x1355_0 = Pipeline(name="x1355_0",parent=x1365) { implicit CU => 
      val x1347 = CU.temp
      val x1349 = ScalarBuffer().wtPort(vec2_da)
      val x1316 = CounterChain.copy("x1423", "x1316")
      val x1355_unit = CounterChain(name = "x1355_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x1316(1)), Const(4)), op=FixMul, results=List(x1347))
      Stage(operands=List(x1347, CU.load(x1349)), op=FixAdd, results=List(CU.scalarOut(x1345_b1445_x1354_b1447_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x1345_b1446_x1354_b1448_s)))
    }
    val x1356 = MemoryController(name="x1356",parent=x1365,offchip=vec2_oc, mctpe=TileLoad) { implicit CU => 
      val x1345_b1446_x1356 = ScalarFIFO(name="size",size=1).wtPort(x1345_b1446_x1354_b1448_s)
      val x1345_b1445_x1356 = ScalarFIFO(name="offset",size=1).wtPort(x1345_b1445_x1354_b1447_s)
      CU.newVout("data", x1346_x1356_data_v)
    }
    val x1364 = Pipeline(name="x1364",parent=x1365) { implicit CU => 
      val ctr4 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x1358 = CounterChain(name = "x1358", ctr4).iter(1)
    }
    val x1378_0 = Pipeline(name="x1378_0",parent=x1423) { implicit CU => 
      val x1374_x1374 = VectorFIFO(size=1).wtPort(x1318_x1374_x1378_v)
      val x1373_x1373 = ScalarFIFO(size=1).wtPort(x1317_x1373_x1378_s)
      val ctr5 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val ctr6 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x1369 = CounterChain(name = "x1369", ctr5, ctr6).iter(16)
      Stage(operands=List(CU.load(x1373_x1373), CU.load(x1374_x1374)), op=FixMul, results=List(CU.vecOut(x1319_x1377_v)))
    }
    val x1422 = StreamController(name="x1422",parent=x1423) { implicit CU => 
      val ctr7 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x1383 = CounterChain(name = "x1383", ctr7).iter(16)
    }
    val x1411 = Sequential(name="x1411",parent=x1422) { implicit CU => 
      val x1411_unit = CounterChain(name = "x1411_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x1400_0 = Pipeline(name="x1400_0",parent=x1411) { implicit CU => 
      val x1389 = CU.temp
      val x1387 = CU.temp
      val x1390 = CU.temp
      val x1391 = CU.temp
      val x1393 = ScalarBuffer().wtPort(out_da)
      val x1300_x1388 = ScalarBuffer().wtPort(sizeB_argin)
      val x1316 = CounterChain.copy("x1423", "x1316")
      val x1383 = CounterChain.copy("x1422", "x1383")
      val x1400_unit = CounterChain(name = "x1400_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x1316(0)), CU.ctr(x1383(0))), op=FixAdd, results=List(x1387))
      Stage(operands=List(x1387, CU.load(x1300_x1388)), op=FixMul, results=List(x1389))
      Stage(operands=List(x1389, CU.ctr(x1316(1))), op=FixAdd, results=List(x1390))
      Stage(operands=List(x1390, Const(4)), op=FixMul, results=List(x1391))
      Stage(operands=List(x1391, CU.load(x1393)), op=FixAdd, results=List(CU.scalarOut(x1384_b1451_x1399_b1453_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x1384_b1452_x1399_b1454_s)))
    }
    val x1410 = Pipeline(name="x1410",parent=x1411) { implicit CU => 
      val ctr8 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x1402 = CounterChain(name = "x1402", ctr8).iter(1)
    }
    val x1412 = MemoryController(name="x1412",parent=x1422,offchip=out_oc, mctpe=TileStore) { implicit CU => 
      val x1384_b1452_x1412 = ScalarFIFO(name="size",size=1).wtPort(x1384_b1452_x1399_b1454_s)
      val x1384_b1451_x1412 = ScalarFIFO(name="offset",size=1).wtPort(x1384_b1451_x1399_b1453_s)
      val x1385_x1412 = VectorFIFO(name="data",size=1).wtPort(x1319_x1406_x1410_v)
    }
    
  }
}
