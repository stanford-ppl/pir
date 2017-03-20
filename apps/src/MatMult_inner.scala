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
    val x1425_b1528_x1435_b1530_s = Scalar("x1425_b1528_x1435_b1530")
    val x1399_x1409_s = Scalar("x1399_x1409")
    val x1401_argin = ArgIn("x1401")
    val x1368_argin = ArgIn("x1368")
    val x1476_b1543_x1487_b1545_s = Scalar("x1476_b1543_x1487_b1545")
    val x1379_oc = OffChip("x1379")
    val x1394_x1419_v = Vector("x1394_x1419")
    val x1382_oc = OffChip("x1382")
    val x1425_b1529_x1435_b1531_s = Scalar("x1425_b1529_x1435_b1531")
    val x1395_x1458_x1465_v = Vector("x1395_x1458_x1465")
    val x1428_argin = ArgIn("x1428")
    val x1400_x1411_data_v = Vector("x1400_x1411_data")
    val x1477_x1488_s = Scalar("x1477_x1488")
    val x1398_b1523_x1408_b1525_s = Scalar("x1398_b1523_x1408_b1525")
    val x1427_x1438_data_v = Vector("x1427_x1438_data")
    val x1478_x1496_s = Scalar("x1478_x1496")
    val x1395_x1446_v = Vector("x1395_x1446")
    val x1394_x1457_x1465_v = Vector("x1394_x1457_x1465")
    val x1479_x1499_ack_v = Vector("x1479_x1499_ack")
    val x1454_x1464_s = Scalar("x1454_x1464")
    val x1369_argin = ArgIn("x1369")
    val x1476_b1542_x1487_b1544_s = Scalar("x1476_b1542_x1487_b1544")
    val x1390_x1470_v = Vector("x1390_x1470")
    val x1370_argin = ArgIn("x1370")
    val x1398_b1522_x1408_b1524_s = Scalar("x1398_b1522_x1408_b1524")
    val x1480_argin = ArgIn("x1480")
    val x1390_x1466_x1471_v = Vector("x1390_x1466_x1471")
    val x1390_x1492_x1497_v = Vector("x1390_x1492_x1497")
    val x1426_x1436_s = Scalar("x1426_x1436")
    val x1376_oc = OffChip("x1376")
    val x1505 = Sequential(name="x1505",parent=top) { implicit CU => 
      val ctr1 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x1505_unit = CounterChain(name = "x1505_unit", ctr1)
    }
    val x1504 = MetaPipeline(name="x1504",parent=x1505) { implicit CU => 
      val x1369_x1385 =  ScalarBuffer().wtPort(x1369_argin)
      val x1368_x1387 =  ScalarBuffer().wtPort(x1368_argin)
      val ctr2 = Counter(min=Const(0), max=x1368_x1387.load, step=Const(4), par=1) // Counter
      val ctr3 = Counter(min=Const(0), max=x1369_x1385.load, step=Const(64), par=1) // Counter
      val x1389 = CounterChain(name = "x1389", ctr2, ctr3)
    }
    val x1390_dsp0 = MemoryPipeline(name="x1390_dsp0",parent="x1504") { implicit CU => 
      val b1547 = CU.temp
      val b1541 = CU.temp
      val b1546 = CU.temp
      val b1540 = CU.temp
      val x1453 = CounterChain.copy("x1472", "x1453")
      val x1475 = CounterChain.copy("x1503", "x1475")
      val x1491 = CounterChain.copy("x1497", "x1491")
      val x1390_x1492 =  SRAM(size = 256,banking = NoBanking()).wtPort(x1390_x1470_v).rdPort(x1390_x1492_x1497_v)
      var stage: List[Stage] = Nil
      stage = CU.emptyStage +: WAStages(2, List(x1390_x1492))
      Stage(stage(1), operands=List(x1453(0), Const(64)), op=FixMul, results=List(CU.temp(stage(1), b1540)))
      Stage(stage(2), operands=List(CU.temp(stage(1), b1540), CU.ctr(stage(1), x1453(1))), op=FixAdd, results=List(x1390_x1492.writeAddr, CU.temp(stage(2), b1541)))
      stage = CU.emptyStage +: RAStages(2, List(x1390_x1492))
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1475(0)), Const(64)), op=FixMul, results=List(CU.temp(stage(1), b1546)))
      Stage(stage(2), operands=List(CU.temp(stage(1), b1546), CU.ctr(stage(1), x1491(0))), op=FixAdd, results=List(x1390_x1492.readAddr, CU.temp(stage(2), b1547)))
    }
    val x1390_dsp1 = MemoryPipeline(name="x1390_dsp1",parent="x1504") { implicit CU => 
      val b1539 = CU.temp
      val b1538 = CU.temp
      val b1541 = CU.temp
      val b1540 = CU.temp
      val x1453 = CounterChain.copy("x1472", "x1453")
      val x1475 = CounterChain.copy("x1503", "x1475")
      val x1491 = CounterChain.copy("x1497", "x1491")
      val x1390_x1466 =  SRAM(size = 256,banking = NoBanking()).wtPort(x1390_x1470_v).rdPort(x1390_x1466_x1471_v)
      var stage: List[Stage] = Nil
      stage = CU.emptyStage +: WAStages(2, List(x1390_x1466))
      Stage(stage(1), operands=List(x1453(0), Const(64)), op=FixMul, results=List(CU.temp(stage(1), b1540)))
      Stage(stage(2), operands=List(CU.temp(stage(1), b1540), CU.ctr(stage(1), x1453(1))), op=FixAdd, results=List(x1390_x1466.writeAddr, CU.temp(stage(2), b1541)))
      stage = CU.emptyStage +: RAStages(2, List(x1390_x1466))
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1453(0)), Const(64)), op=FixMul, results=List(CU.temp(stage(1), b1538)))
      Stage(stage(2), operands=List(CU.temp(stage(1), b1538), CU.ctr(stage(1), x1453(1))), op=FixAdd, results=List(x1390_x1466.readAddr, CU.temp(stage(2), b1539)))
    }
    val x1473 = MetaPipeline(name="x1473",parent=x1504) { implicit CU => 
      val x1370_x1391 =  ScalarBuffer().wtPort(x1370_argin)
      val ctr4 = Counter(min=Const(0), max=x1370_x1391.load, step=Const(64), par=1) // Counter
      val x1393 = CounterChain(name = "x1393", ctr4)
    }
    val x1394_dsp0 = MemoryPipeline(name="x1394_dsp0",parent="x1473") { implicit CU => 
      val b1527 = CU.temp
      val b1534 = CU.temp
      val b1535 = CU.temp
      val b1526 = CU.temp
      val x1397 = CounterChain.copy("x1422", "x1397")
      val x1415 = CounterChain.copy("x1420", "x1415")
      val x1453 = CounterChain.copy("x1472", "x1453")
      val x1456 = CounterChain.copy("x1465", "x1456")
      val x1394_x1457 =  SRAM(size = 256,banking = NoBanking()).wtPort(x1394_x1419_v).rdPort(x1394_x1457_x1465_v)
      var stage: List[Stage] = Nil
      stage = CU.emptyStage +: WAStages(2, List(x1394_x1457))
      Stage(stage(1), operands=List(x1397(0), Const(64)), op=FixMul, results=List(CU.temp(stage(1), b1526)))
      Stage(stage(2), operands=List(CU.temp(stage(1), b1526), CU.ctr(stage(1), x1415(0))), op=FixAdd, results=List(x1394_x1457.writeAddr, CU.temp(stage(2), b1527)))
      stage = CU.emptyStage +: RAStages(2, List(x1394_x1457))
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1453(0)), Const(64)), op=FixMul, results=List(CU.temp(stage(1), b1534)))
      Stage(stage(2), operands=List(CU.temp(stage(1), b1534), CU.ctr(stage(1), x1456(0))), op=FixAdd, results=List(x1394_x1457.readAddr, CU.temp(stage(2), b1535)))
    }
    val x1395_dsp0 = MemoryPipeline(name="x1395_dsp0",parent="x1473") { implicit CU => 
      val b1536 = CU.temp
      val b1533 = CU.temp
      val b1537 = CU.temp
      val b1532 = CU.temp
      val x1424 = CounterChain.copy("x1449", "x1424")
      val x1442 = CounterChain.copy("x1447", "x1442")
      val x1453 = CounterChain.copy("x1472", "x1453")
      val x1456 = CounterChain.copy("x1465", "x1456")
      val x1395_x1458 =  SRAM(size = 4096,banking = NoBanking()).wtPort(x1395_x1446_v).rdPort(x1395_x1458_x1465_v)
      var stage: List[Stage] = Nil
      stage = CU.emptyStage +: WAStages(2, List(x1395_x1458))
      Stage(stage(1), operands=List(x1424(0), Const(64)), op=FixMul, results=List(CU.temp(stage(1), b1532)))
      Stage(stage(2), operands=List(CU.temp(stage(1), b1532), CU.ctr(stage(1), x1442(0))), op=FixAdd, results=List(x1395_x1458.writeAddr, CU.temp(stage(2), b1533)))
      stage = CU.emptyStage +: RAStages(2, List(x1395_x1458))
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1456(0)), Const(64)), op=FixMul, results=List(CU.temp(stage(1), b1536)))
      Stage(stage(2), operands=List(CU.temp(stage(1), b1536), CU.ctr(stage(1), x1453(1))), op=FixAdd, results=List(x1395_x1458.readAddr, CU.temp(stage(2), b1537)))
    }
    val x1422 = StreamController(name="x1422",parent=x1473) { implicit CU => 
      val ctr5 = Counter(min=Const(0), max=Const(4), step=Const(1), par=1) // Counter
      val x1397 = CounterChain(name = "x1397", ctr5)
    }
    val x1410 = Pipeline(name="x1410",parent=x1422) { implicit CU => 
      val x900 = CU.temp
      val x1404 = CU.temp
      val x1403 = CU.temp
      val x1405 = CU.temp
      val x1406 = CU.temp
      val x1401 =  ScalarBuffer().wtPort(x1401_argin)
      val x1370_x1402 =  ScalarBuffer().wtPort(x1370_argin)
      val x1389 = CounterChain.copy("x1504", "x1389")
      val x1397 = CounterChain.copy("x1422", "x1397")
      val x1393 = CounterChain.copy("x1473", "x1393")
      val ctr6 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x1410_unit = CounterChain(name = "x1410_unit", ctr6)
      var stage: List[Stage] = Nil
      stage = CU.emptyStage +: Stages(7)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1389(0)), CU.ctr(stage(0), x1397(0))), op=FixAdd, results=List(CU.temp(stage(1), x900)))
      Stage(stage(2), operands=List(CU.temp(stage(1), x900), CU.load(stage(1), x1370_x1402)), op=FixMul, results=List(CU.temp(stage(2), x1403)))
      Stage(stage(3), operands=List(CU.temp(stage(2), x1403), CU.ctr(stage(2), x1393(0))), op=FixAdd, results=List(CU.temp(stage(3), x1404)))
      Stage(stage(4), operands=List(CU.temp(stage(3), x1404), Const(4)), op=FixMul, results=List(CU.temp(stage(4), x1405)))
      Stage(stage(5), operands=List(CU.temp(stage(4), x1405), CU.load(stage(4), x1401)), op=FixAdd, results=List(CU.scalarOut(stage(5), x1398_b1522_x1408_b1524_s), CU.temp(stage(5), x1406)))
      Stage(stage(6), operands=List(Const(256)), op=Bypass, results=List(CU.scalarOut(stage(6), x1398_b1523_x1408_b1525_s)))
      Stage(stage(7), operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(stage(7), x1399_x1409_s)))
    }
    val x1411 = MemoryController(name="x1411",parent=x1422,offchip=x1376_oc, mctpe=TileLoad) { implicit CU => 
      CU.mcfifos += "size" ->  ScalarFIFO(size = 1).wtPort(x1398_b1523_x1408_b1525_s)
      CU.mcfifos += "offset" ->  ScalarFIFO(size = 1).wtPort(x1398_b1522_x1408_b1524_s)
      CU.mcvecs += "data" -> x1400_x1411_data_v
    }
    val x1421 = MetaPipeline(name="x1421",parent=x1422) { implicit CU => 
      val ctr7 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x1421_unit = CounterChain(name = "x1421_unit", ctr7)
    }
    val x1413 = Pipeline(name="x1413",parent=x1421) { implicit CU => 
      val x1399_x1412 =  ScalarFIFO(size = 16).wtPort(x1399_x1409_s)
      val ctr8 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x1413_unit = CounterChain(name = "x1413_unit", ctr8)
      var stage: List[Stage] = Nil
    }
    val x1420 = Pipeline(name="x1420",parent=x1421) { implicit CU => 
      val x1400_x1416 =  VectorFIFO(size = 1).wtPort(x1400_x1411_data_v)
      val x1397 = CounterChain.copy("x1422", "x1397")
      val ctr9 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val x1415 = CounterChain(name = "x1415", ctr9)
      var stage: List[Stage] = Nil
      stage = CU.emptyStage +: Stages(1)
      Stage(stage(1), operands=List(x1400_x1416.load), op=Bypass, results=List(CU.vecOut(stage(1), x1394_x1419_v)))
    }
    val x1449 = StreamController(name="x1449",parent=x1473) { implicit CU => 
      val ctr10 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val x1424 = CounterChain(name = "x1424", ctr10)
    }
    val x1437 = Pipeline(name="x1437",parent=x1449) { implicit CU => 
      val x1432 = CU.temp
      val x1431 = CU.temp
      val x1430 = CU.temp
      val x937 = CU.temp
      val x1433 = CU.temp
      val x1369_x1429 =  ScalarBuffer().wtPort(x1369_argin)
      val x1428 =  ScalarBuffer().wtPort(x1428_argin)
      val x1393 = CounterChain.copy("x1473", "x1393")
      val x1424 = CounterChain.copy("x1449", "x1424")
      val x1389 = CounterChain.copy("x1504", "x1389")
      val ctr11 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x1437_unit = CounterChain(name = "x1437_unit", ctr11)
      var stage: List[Stage] = Nil
      stage = CU.emptyStage +: Stages(7)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1393(0)), CU.ctr(stage(0), x1424(0))), op=FixAdd, results=List(CU.temp(stage(1), x937)))
      Stage(stage(2), operands=List(CU.temp(stage(1), x937), CU.load(stage(1), x1369_x1429)), op=FixMul, results=List(CU.temp(stage(2), x1430)))
      Stage(stage(3), operands=List(CU.temp(stage(2), x1430), CU.ctr(stage(2), x1389(1))), op=FixAdd, results=List(CU.temp(stage(3), x1431)))
      Stage(stage(4), operands=List(CU.temp(stage(3), x1431), Const(4)), op=FixMul, results=List(CU.temp(stage(4), x1432)))
      Stage(stage(5), operands=List(CU.temp(stage(4), x1432), CU.load(stage(4), x1428)), op=FixAdd, results=List(CU.scalarOut(stage(5), x1425_b1528_x1435_b1530_s), CU.temp(stage(5), x1433)))
      Stage(stage(6), operands=List(Const(256)), op=Bypass, results=List(CU.scalarOut(stage(6), x1425_b1529_x1435_b1531_s)))
      Stage(stage(7), operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(stage(7), x1426_x1436_s)))
    }
    val x1438 = MemoryController(name="x1438",parent=x1449,offchip=x1379_oc, mctpe=TileLoad) { implicit CU => 
      CU.mcfifos += "offset" ->  ScalarFIFO(size = 1).wtPort(x1425_b1528_x1435_b1530_s)
      CU.mcfifos += "size" ->  ScalarFIFO(size = 1).wtPort(x1425_b1529_x1435_b1531_s)
      CU.mcvecs += "data" -> x1427_x1438_data_v
    }
    val x1448 = MetaPipeline(name="x1448",parent=x1449) { implicit CU => 
      val ctr12 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x1448_unit = CounterChain(name = "x1448_unit", ctr12)
    }
    val x1440 = Pipeline(name="x1440",parent=x1448) { implicit CU => 
      val x1426_x1439 =  ScalarFIFO(size = 16).wtPort(x1426_x1436_s)
      val ctr13 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x1440_unit = CounterChain(name = "x1440_unit", ctr13)
      var stage: List[Stage] = Nil
    }
    val x1447 = Pipeline(name="x1447",parent=x1448) { implicit CU => 
      val x1427_x1443 =  VectorFIFO(size = 1).wtPort(x1427_x1438_data_v)
      val x1424 = CounterChain.copy("x1449", "x1424")
      val ctr14 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val x1442 = CounterChain(name = "x1442", ctr14)
      var stage: List[Stage] = Nil
      stage = CU.emptyStage +: Stages(1)
      Stage(stage(1), operands=List(x1427_x1443.load), op=Bypass, results=List(CU.vecOut(stage(1), x1395_x1446_v)))
    }
    val x1472 = MetaPipeline(name="x1472",parent=x1473) { implicit CU => 
      val ctr15 = Counter(min=Const(0), max=Const(4), step=Const(1), par=1) // Counter
      val ctr16 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val x1453 = CounterChain(name = "x1453", ctr15, ctr16)
    }
    val x1465 = Pipeline(name="x1465",parent=x1472) { implicit CU => 
      val x1462 = CU.temp
      val ar136 = CU.accum(init = Const(0))
      val x1453 = CounterChain.copy("x1472", "x1453")
      val ctr17 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val x1456 = CounterChain(name = "x1456", ctr17)
      var stage: List[Stage] = Nil
      stage = CU.emptyStage +: Stages(2)
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x1394_x1457_x1465_v), CU.vecIn(stage(0), x1395_x1458_x1465_v)), op=FixMul, results=List(CU.reduce(stage(1)), CU.temp(stage(1), x1462)))
      val (rs1, rr289) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(stage(2), operands=List(rr289), op=Bypass, results=List(CU.scalarOut(stage(2), x1454_x1464_s)))
    }
    val x1471 = Pipeline(name="x1471",parent=x1472) { implicit CU => 
      val x1469 = CU.temp
      val x1468 = CU.temp
      val x997 = CU.temp
      val x1454_x1467 =  ScalarBuffer().wtPort(x1454_x1464_s)
      val x1453 = CounterChain.copy("x1472", "x1453")
      val x1393 = CounterChain.copy("x1473", "x1393")
      val ctr18 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x1471_unit = CounterChain(name = "x1471_unit", ctr18)
      var stage: List[Stage] = Nil
      stage = CU.emptyStage +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1393(0)), Const(0)), op=FixEql, results=List(CU.temp(stage(1), x997)))
      Stage(stage(2), operands=List(CU.temp(stage(1), x997), Const(0), CU.vecIn(stage(1), x1390_x1466_x1471_v)), op=Mux, results=List(CU.temp(stage(2), x1468)))
      Stage(stage(3), operands=List(CU.temp(stage(2), x1468), CU.load(stage(2), x1454_x1467)), op=FixAdd, results=List(CU.vecOut(stage(3), x1390_x1470_v), CU.temp(stage(3), x1469)))
    }
    val x1503 = StreamController(name="x1503",parent=x1504) { implicit CU => 
      val ctr19 = Counter(min=Const(0), max=Const(4), step=Const(1), par=1) // Counter
      val x1475 = CounterChain(name = "x1475", ctr19)
    }
    val x1498 = MetaPipeline(name="x1498",parent=x1503) { implicit CU => 
      val ctr20 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x1498_unit = CounterChain(name = "x1498_unit", ctr20)
    }
    val x1489 = Pipeline(name="x1489",parent=x1498) { implicit CU => 
      val x1484 = CU.temp
      val x1014 = CU.temp
      val x1483 = CU.temp
      val x1482 = CU.temp
      val x1485 = CU.temp
      val x1369_x1481 =  ScalarBuffer().wtPort(x1369_argin)
      val x1480 =  ScalarBuffer().wtPort(x1480_argin)
      val x1389 = CounterChain.copy("x1504", "x1389")
      val x1475 = CounterChain.copy("x1503", "x1475")
      val ctr21 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x1489_unit = CounterChain(name = "x1489_unit", ctr21)
      var stage: List[Stage] = Nil
      stage = CU.emptyStage +: Stages(7)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1389(0)), CU.ctr(stage(0), x1475(0))), op=FixAdd, results=List(CU.temp(stage(1), x1014)))
      Stage(stage(2), operands=List(CU.temp(stage(1), x1014), CU.load(stage(1), x1369_x1481)), op=FixMul, results=List(CU.temp(stage(2), x1482)))
      Stage(stage(3), operands=List(CU.temp(stage(2), x1482), CU.ctr(stage(2), x1389(1))), op=FixAdd, results=List(CU.temp(stage(3), x1483)))
      Stage(stage(4), operands=List(CU.temp(stage(3), x1483), Const(4)), op=FixMul, results=List(CU.temp(stage(4), x1484)))
      Stage(stage(5), operands=List(CU.temp(stage(4), x1484), CU.load(stage(4), x1480)), op=FixAdd, results=List(CU.scalarOut(stage(5), x1476_b1542_x1487_b1544_s), CU.temp(stage(5), x1485)))
      Stage(stage(6), operands=List(Const(256)), op=Bypass, results=List(CU.scalarOut(stage(6), x1476_b1543_x1487_b1545_s)))
      Stage(stage(7), operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(stage(7), x1477_x1488_s)))
    }
    val x1497 = Pipeline(name="x1497",parent=x1498) { implicit CU => 
      val x1475 = CounterChain.copy("x1503", "x1475")
      val ctr22 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val x1491 = CounterChain(name = "x1491", ctr22)
      var stage: List[Stage] = Nil
      stage = CU.emptyStage +: Stages(1)
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x1390_x1492_x1497_v)), op=Bypass, results=List(CU.scalarOut(stage(1), x1478_x1496_s)))
    }
    val x1499 = MemoryController(name="x1499",parent=x1503,offchip=x1382_oc, mctpe=TileStore) { implicit CU => 
      CU.mcfifos += "offset" ->  ScalarFIFO(size = 1).wtPort(x1476_b1542_x1487_b1544_s)
      CU.mcfifos += "data" ->  ScalarFIFO(size = 1).wtPort(x1478_x1496_s)
      CU.mcfifos += "size" ->  ScalarFIFO(size = 1).wtPort(x1476_b1543_x1487_b1545_s)
      CU.mcvecs += "ack" -> x1479_x1499_ack_v
    }
    val x1502 = Pipeline(name="x1502",parent=x1503) { implicit CU => 
      val x1479_x1501 =  VectorFIFO(size = 1).wtPort(x1479_x1499_ack_v)
      val x1477_x1500 =  ScalarFIFO(size = 16).wtPort(x1477_x1488_s)
      val ctr23 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x1502_unit = CounterChain(name = "x1502_unit", ctr23)
      var stage: List[Stage] = Nil
    }
    
  }
}
