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
    val x1341_b1480_x1348_b1483_scalar = Scalar("x1341_b1480_x1348_b1483")
    val x1408_x1415_scalar = Scalar("x1408_x1415")
    val x1333_x1459_scalar = Scalar("x1333_x1459")
    val x1328_x1463_argout = ArgOut("x1328_x1463")
    val x1339_x1434_x1455_vector = Vector("x1339_x1434_x1455")
    val x1337_x1359_vector = Vector("x1337_x1359")
    val x1363_b1486_x1370_b1489_scalar = Scalar("x1363_b1486_x1370_b1489")
    val x1385_b1494_x1392_b1497_scalar = Scalar("x1385_b1494_x1392_b1497")
    val x1385_b1493_x1392_b1496_scalar = Scalar("x1385_b1493_x1392_b1496")
    val x1341_b1481_x1348_b1484_scalar = Scalar("x1341_b1481_x1348_b1484")
    val x1366_argin = ArgIn("x1366")
    val x1344_argin = ArgIn("x1344")
    val x1407_b1499_x1414_b1502_scalar = Scalar("x1407_b1499_x1414_b1502")
    val x1388_argin = ArgIn("x1388")
    val x1327_oc = OffChip("x1327")
    val x1338_x1381_vector = Vector("x1338_x1381")
    val x1407_b1498_x1414_b1501_scalar = Scalar("x1407_b1498_x1414_b1501")
    val x1363_b1487_x1370_b1490_scalar = Scalar("x1363_b1487_x1370_b1490")
    val x1337_x1433_x1455_vector = Vector("x1337_x1433_x1455")
    val x1339_x1403_vector = Vector("x1339_x1403")
    val x1410_argin = ArgIn("x1410")
    val x1363_b1488_x1370_b1491_scalar = Scalar("x1363_b1488_x1370_b1491")
    val x1318_argin = ArgIn("x1318")
    val x1386_x1393_scalar = Scalar("x1386_x1393")
    val x1323_oc = OffChip("x1323")
    val x1430_x1454_vector = Vector("x1430_x1454")
    val x1364_x1371_scalar = Scalar("x1364_x1371")
    val x1342_x1349_scalar = Scalar("x1342_x1349")
    val x1341_b1482_x1348_b1485_scalar = Scalar("x1341_b1482_x1348_b1485")
    val x1338_x1435_x1455_vector = Vector("x1338_x1435_x1455")
    val x1340_x1436_x1455_vector = Vector("x1340_x1436_x1455")
    val x1385_b1492_x1392_b1495_scalar = Scalar("x1385_b1492_x1392_b1495")
    val x1407_b1500_x1414_b1503_scalar = Scalar("x1407_b1500_x1414_b1503")
    val x1325_oc = OffChip("x1325")
    val x1340_x1425_vector = Vector("x1340_x1425")
    val x1321_oc = OffChip("x1321")
    val x1465 = Sequential(name="x1465",parent=top) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x1461 = MetaPipeline(name="x1461",parent=x1465) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr1 = (Const("0i").out, x1318_x1334.load, Const("96i").out) // Counter
      val x1336 = CounterChain(name = "x1336", ctr1)
      val x1318_x1334 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x1318_argin).rdPort(None)
      var stage: List[Stage] = Nil
    }
    val x1337_dsp0 = MemoryPipeline(name="x1337_dsp0",parent="x1461") { implicit CU => 
      val stage0 = CU.emptyStage
      val x1355 = CounterChain.copy("x1360", "x1355")
      val x1432 = CounterChain.copy("x1455", "x1432")
      val x1337_x1433 = SRAM(size = 96, banking = Strided(1)).wtPort(x1337_x1359_vector).rdPort(x1337_x1433_x1455_vector).rdAddr(x1432(0)).wtAddr(x1355(0))
      var stage: List[Stage] = Nil
    }
    val x1338_dsp0 = MemoryPipeline(name="x1338_dsp0",parent="x1461") { implicit CU => 
      val stage0 = CU.emptyStage
      val x1377 = CounterChain.copy("x1382", "x1377")
      val x1432 = CounterChain.copy("x1455", "x1432")
      val x1338_x1435 = SRAM(size = 96, banking = Strided(1)).wtPort(x1338_x1381_vector).rdPort(x1338_x1435_x1455_vector).rdAddr(x1432(0)).wtAddr(x1377(0))
      var stage: List[Stage] = Nil
    }
    val x1339_dsp0 = MemoryPipeline(name="x1339_dsp0",parent="x1461") { implicit CU => 
      val stage0 = CU.emptyStage
      val x1399 = CounterChain.copy("x1404", "x1399")
      val x1432 = CounterChain.copy("x1455", "x1432")
      val x1339_x1434 = SRAM(size = 96, banking = Strided(1)).wtPort(x1339_x1403_vector).rdPort(x1339_x1434_x1455_vector).rdAddr(x1432(0)).wtAddr(x1399(0))
      var stage: List[Stage] = Nil
    }
    val x1340_dsp0 = MemoryPipeline(name="x1340_dsp0",parent="x1461") { implicit CU => 
      val stage0 = CU.emptyStage
      val x1421 = CounterChain.copy("x1426", "x1421")
      val x1432 = CounterChain.copy("x1455", "x1432")
      val x1340_x1436 = SRAM(size = 96, banking = Strided(1)).wtPort(x1340_x1425_vector).rdPort(x1340_x1436_x1455_vector).rdAddr(x1432(0)).wtAddr(x1421(0))
      var stage: List[Stage] = Nil
    }
    val x1362 = Sequential(name="x1362",parent=x1461) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x1350 = UnitPipeline(name="x1350",parent=x1362) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr113 = CU.temp
      val tr114 = CU.temp
      val tr115 = CU.temp
      val tr116 = CU.temp
      val tr117 = CU.temp
      val tr118 = CU.temp
      val x1336 = CounterChain.copy("x1461", "x1336")
      val x1344 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x1344_argin).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(7)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1336(0)), Const("4i")), op=FixMul, results=List(CU.temp(stage(1), tr113)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr113), CU.load(stage(1), x1344)), op=FixAdd, results=List(CU.temp(stage(2), tr114)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr114), Const("4i")), op=FixMul, results=List(CU.temp(stage(3), tr115)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr116)), op=Bypass, results=List(CU.scalarOut(stage(4), x1341_b1480_x1348_b1483_scalar)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr117)), op=Bypass, results=List(CU.scalarOut(stage(5), x1341_b1481_x1348_b1484_scalar)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr118)), op=Bypass, results=List(CU.scalarOut(stage(6), x1341_b1482_x1348_b1485_scalar)))
      Stage(stage(7), operands=List(Const("96i")), op=Bypass, results=List(CU.scalarOut(stage(7), x1342_x1349_scalar)))
    }
    val x1351 = Fringe(name="x1351",parent=x1362,dram=x1321_oc, mode=TileLoad) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1341_b1482_x1351 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x1341_b1482_x1348_b1485_scalar).rdPort(None)
      val x1341_b1481_x1351 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x1341_b1481_x1348_b1484_scalar).rdPort(None)
      val x1341_b1480_x1351 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x1341_b1480_x1348_b1483_scalar).rdPort(None)
      var stage: List[Stage] = Nil
    }
    val x1361 = Sequential(name="x1361",parent=x1362) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x1353 = Sequential(name="x1353",parent=x1361) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x1360 = Pipeline(name="x1360",parent=x1361) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr2 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x1355 = CounterChain(name = "x1355", ctr2)
      val x1343_x1356 = VectorFIFO(size = 1, banking = Strided(1)).wtPort(None).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x1343_x1356.load), op=Bypass, results=List(CU.vecOut(stage(1), x1337_x1359_vector)))
    }
    val x1384 = Sequential(name="x1384",parent=x1461) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x1372 = UnitPipeline(name="x1372",parent=x1384) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr121 = CU.temp
      val tr122 = CU.temp
      val tr123 = CU.temp
      val tr124 = CU.temp
      val tr125 = CU.temp
      val tr126 = CU.temp
      val x1336 = CounterChain.copy("x1461", "x1336")
      val x1366 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x1366_argin).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(7)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1336(0)), Const("4i")), op=FixMul, results=List(CU.temp(stage(1), tr121)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr121), CU.load(stage(1), x1366)), op=FixAdd, results=List(CU.temp(stage(2), tr122)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr122), Const("4i")), op=FixMul, results=List(CU.temp(stage(3), tr123)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr124)), op=Bypass, results=List(CU.scalarOut(stage(4), x1363_b1486_x1370_b1489_scalar)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr125)), op=Bypass, results=List(CU.scalarOut(stage(5), x1363_b1487_x1370_b1490_scalar)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr126)), op=Bypass, results=List(CU.scalarOut(stage(6), x1363_b1488_x1370_b1491_scalar)))
      Stage(stage(7), operands=List(Const("96i")), op=Bypass, results=List(CU.scalarOut(stage(7), x1364_x1371_scalar)))
    }
    val x1373 = Fringe(name="x1373",parent=x1384,dram=x1323_oc, mode=TileLoad) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1363_b1488_x1373 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x1363_b1488_x1370_b1491_scalar).rdPort(None)
      val x1363_b1487_x1373 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x1363_b1487_x1370_b1490_scalar).rdPort(None)
      val x1363_b1486_x1373 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x1363_b1486_x1370_b1489_scalar).rdPort(None)
      var stage: List[Stage] = Nil
    }
    val x1383 = Sequential(name="x1383",parent=x1384) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x1375 = Sequential(name="x1375",parent=x1383) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x1382 = Pipeline(name="x1382",parent=x1383) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr3 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x1377 = CounterChain(name = "x1377", ctr3)
      val x1365_x1378 = VectorFIFO(size = 1, banking = Strided(1)).wtPort(None).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x1365_x1378.load), op=Bypass, results=List(CU.vecOut(stage(1), x1338_x1381_vector)))
    }
    val x1406 = Sequential(name="x1406",parent=x1461) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x1394 = UnitPipeline(name="x1394",parent=x1406) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr129 = CU.temp
      val tr130 = CU.temp
      val tr131 = CU.temp
      val tr132 = CU.temp
      val tr133 = CU.temp
      val tr134 = CU.temp
      val x1336 = CounterChain.copy("x1461", "x1336")
      val x1388 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x1388_argin).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(7)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1336(0)), Const("4i")), op=FixMul, results=List(CU.temp(stage(1), tr129)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr129), CU.load(stage(1), x1388)), op=FixAdd, results=List(CU.temp(stage(2), tr130)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr130), Const("4i")), op=FixMul, results=List(CU.temp(stage(3), tr131)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr132)), op=Bypass, results=List(CU.scalarOut(stage(4), x1385_b1492_x1392_b1495_scalar)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr133)), op=Bypass, results=List(CU.scalarOut(stage(5), x1385_b1493_x1392_b1496_scalar)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr134)), op=Bypass, results=List(CU.scalarOut(stage(6), x1385_b1494_x1392_b1497_scalar)))
      Stage(stage(7), operands=List(Const("96i")), op=Bypass, results=List(CU.scalarOut(stage(7), x1386_x1393_scalar)))
    }
    val x1395 = Fringe(name="x1395",parent=x1406,dram=x1325_oc, mode=TileLoad) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1385_b1492_x1395 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x1385_b1492_x1392_b1495_scalar).rdPort(None)
      val x1385_b1494_x1395 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x1385_b1494_x1392_b1497_scalar).rdPort(None)
      val x1385_b1493_x1395 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x1385_b1493_x1392_b1496_scalar).rdPort(None)
      var stage: List[Stage] = Nil
    }
    val x1405 = Sequential(name="x1405",parent=x1406) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x1397 = Sequential(name="x1397",parent=x1405) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x1404 = Pipeline(name="x1404",parent=x1405) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr4 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x1399 = CounterChain(name = "x1399", ctr4)
      val x1387_x1400 = VectorFIFO(size = 1, banking = Strided(1)).wtPort(None).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x1387_x1400.load), op=Bypass, results=List(CU.vecOut(stage(1), x1339_x1403_vector)))
    }
    val x1428 = Sequential(name="x1428",parent=x1461) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x1416 = UnitPipeline(name="x1416",parent=x1428) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr137 = CU.temp
      val tr138 = CU.temp
      val tr139 = CU.temp
      val tr140 = CU.temp
      val tr141 = CU.temp
      val tr142 = CU.temp
      val x1336 = CounterChain.copy("x1461", "x1336")
      val x1410 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x1410_argin).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(7)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1336(0)), Const("4i")), op=FixMul, results=List(CU.temp(stage(1), tr137)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr137), CU.load(stage(1), x1410)), op=FixAdd, results=List(CU.temp(stage(2), tr138)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr138), Const("4i")), op=FixMul, results=List(CU.temp(stage(3), tr139)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr140)), op=Bypass, results=List(CU.scalarOut(stage(4), x1407_b1498_x1414_b1501_scalar)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr141)), op=Bypass, results=List(CU.scalarOut(stage(5), x1407_b1499_x1414_b1502_scalar)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr142)), op=Bypass, results=List(CU.scalarOut(stage(6), x1407_b1500_x1414_b1503_scalar)))
      Stage(stage(7), operands=List(Const("96i")), op=Bypass, results=List(CU.scalarOut(stage(7), x1408_x1415_scalar)))
    }
    val x1417 = Fringe(name="x1417",parent=x1428,dram=x1327_oc, mode=TileLoad) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1407_b1500_x1417 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x1407_b1500_x1414_b1503_scalar).rdPort(None)
      val x1407_b1499_x1417 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x1407_b1499_x1414_b1502_scalar).rdPort(None)
      val x1407_b1498_x1417 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x1407_b1498_x1414_b1501_scalar).rdPort(None)
      var stage: List[Stage] = Nil
    }
    val x1427 = Sequential(name="x1427",parent=x1428) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x1419 = Sequential(name="x1419",parent=x1427) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x1426 = Pipeline(name="x1426",parent=x1427) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr5 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x1421 = CounterChain(name = "x1421", ctr5)
      val x1409_x1422 = VectorFIFO(size = 1, banking = Strided(1)).wtPort(None).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x1409_x1422.load), op=Bypass, results=List(CU.vecOut(stage(1), x1340_x1425_vector)))
    }
    val x1455 = Pipeline(name="x1455",parent=x1461) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr144 = CU.temp
      val tr146 = CU.temp
      val tr147 = CU.temp
      val tr148 = CU.temp
      val tr149 = CU.temp
      val tr150 = CU.temp
      val tr151 = CU.temp
      val tr153 = CU.temp
      val tr154 = CU.temp
      val tr155 = CU.temp
      val tr156 = CU.temp
      val ar77 = CU.accum(init = Const("0i"))
      val ctr6 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x1432 = CounterChain(name = "x1432", ctr6)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(12)
      Stage(stage(1), operands=List(Const("0i"), CU.vecIn(stage(0), x1337_x1433_x1455_vector)), op=FixLt, results=List(CU.temp(stage(1), tr144)))
      Stage(stage(2), operands=List(CU.vecIn(stage(1), x1337_x1433_x1455_vector), Const("9999i")), op=FixLt, results=List(CU.temp(stage(2), tr146)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr144), CU.temp(stage(2), tr146)), op=BitAnd, results=List(CU.temp(stage(3), tr147)))
      Stage(stage(4), operands=List(Const("0i"), CU.vecIn(stage(3), x1339_x1434_x1455_vector)), op=FixLeq, results=List(CU.temp(stage(4), tr148)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr147), CU.temp(stage(4), tr148)), op=BitAnd, results=List(CU.temp(stage(5), tr149)))
      Stage(stage(6), operands=List(CU.vecIn(stage(5), x1339_x1434_x1455_vector), Const("9999i")), op=FixLeq, results=List(CU.temp(stage(6), tr150)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr149), CU.temp(stage(6), tr150)), op=BitAnd, results=List(CU.temp(stage(7), tr151)))
      Stage(stage(8), operands=List(CU.vecIn(stage(7), x1338_x1435_x1455_vector), Const("24i")), op=FixLt, results=List(CU.temp(stage(8), tr153)))
      Stage(stage(9), operands=List(CU.temp(stage(8), tr151), CU.temp(stage(8), tr153)), op=BitAnd, results=List(CU.temp(stage(9), tr154)))
      Stage(stage(10), operands=List(CU.vecIn(stage(9), x1340_x1436_x1455_vector), CU.vecIn(stage(9), x1339_x1434_x1455_vector)), op=FixMul, results=List(CU.temp(stage(10), tr155)))
      Stage(stage(11), operands=List(CU.temp(stage(10), tr154), CU.temp(stage(10), tr155), Const("0i")), op=Mux, results=List(CU.reduce(stage(11)), CU.temp(stage(11), tr156)))
      val (rs1, rr159) = Stage.reduce(op=FixAdd, init=Const("0i"))
      Stage(stage(12), operands=List(rr159), op=Bypass, results=List(CU.vecOut(stage(12), x1430_x1454_vector)))
    }
    val x1460 = UnitPipeline(name="x1460",parent=x1461) { implicit CU => 
      val stage0 = CU.emptyStage
      val ar2 = CU.accum(init = Const("0i"))
      val x1430_x1457 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x1430_x1454_vector).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x1430_x1457.load), op=Bypass, results=List(CU.reduce(stage(1))))
      val (rs1, rr162) = Stage.reduce(op=FixAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr162), op=Bypass, results=List(CU.scalarOut(stage(2), x1333_x1459_scalar)))
    }
    val x1464 = UnitPipeline(name="x1464",parent=x1465) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1333_x1462 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x1333_x1459_scalar).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x1333_x1462.load), op=Bypass, results=List(CU.scalarOut(stage(1), x1328_x1463_argout)))
    }
    
  }
}
