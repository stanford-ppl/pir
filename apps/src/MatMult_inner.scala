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
    val x1358_argin = ArgIn("x1358")
    val x1414_b1520_x1425_b1523_scalar = Scalar("x1414_b1520_x1425_b1523")
    val x1367_oc = OffChip("x1367")
    val x1356_argin = ArgIn("x1356")
    val x1357_argin = ArgIn("x1357")
    val x1415_x1426_scalar = Scalar("x1415_x1426")
    val x1417_argin = ArgIn("x1417")
    val x1414_b1522_x1425_b1525_scalar = Scalar("x1414_b1522_x1425_b1525")
    val x1378_x1456_x1461_vector = Vector("x1378_x1456_x1461")
    val x1468_b1539_x1486_b1546_vector = Vector("x1468_b1539_x1486_b1546")
    val x1386_b1514_x1397_b1517_scalar = Scalar("x1386_b1514_x1397_b1517")
    val x1378_x1460_vector = Vector("x1378_x1460")
    val x1468_b1540_x1486_b1547_vector = Vector("x1468_b1540_x1486_b1547")
    val x1378_x1482_x1487_vector = Vector("x1378_x1482_x1487")
    val x1383_x1448_x1455_vector = Vector("x1383_x1448_x1455")
    val x1466_b1536_x1477_b1541_scalar = Scalar("x1466_b1536_x1477_b1541")
    val x1470_argin = ArgIn("x1470")
    val x1467_x1478_scalar = Scalar("x1467_x1478")
    val x1386_b1512_x1397_b1515_scalar = Scalar("x1386_b1512_x1397_b1515")
    val x1386_b1513_x1397_b1516_scalar = Scalar("x1386_b1513_x1397_b1516")
    val x1466_b1538_x1477_b1543_scalar = Scalar("x1466_b1538_x1477_b1543")
    val x1383_x1436_vector = Vector("x1383_x1436")
    val x1382_x1408_vector = Vector("x1382_x1408")
    val x1444_x1454_vector = Vector("x1444_x1454")
    val x1389_argin = ArgIn("x1389")
    val x1387_x1398_scalar = Scalar("x1387_x1398")
    val x1370_oc = OffChip("x1370")
    val x1364_oc = OffChip("x1364")
    val x1414_b1521_x1425_b1524_scalar = Scalar("x1414_b1521_x1425_b1524")
    val x1382_x1447_x1455_vector = Vector("x1382_x1447_x1455")
    val x1466_b1537_x1477_b1542_scalar = Scalar("x1466_b1537_x1477_b1542")
    val x1495 = Sequential(name="x1495",parent=top) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x1494 = MetaPipeline(name="x1494",parent=x1495) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr1 = (Const("0i").out, x1356_x1375.load, Const("4i").out) // Counter
      val ctr2 = (Const("0i").out, x1357_x1373.load, Const("64i").out) // Counter
      val x1377 = CounterChain(name = "x1377", ctr1, ctr2)
      val x1357_x1373 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x1357_argin).rdPort(None)
      val x1356_x1375 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x1356_argin).rdPort(None)
      var stage: List[Stage] = Nil
    }
    val x1378_dsp0 = MemoryPipeline(name="x1378_dsp0",parent="x1494") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr188 = CU.temp
      val tr189 = CU.temp
      val tr191 = CU.temp
      val tr192 = CU.temp
      val x1443 = CounterChain.copy("x1462", "x1443")
      val x1465 = CounterChain.copy("x1493", "x1465")
      val x1481 = CounterChain.copy("x1487", "x1481")
      val x1378_x1482 = SRAM(size = 256, banking = Strided(1)).wtPort(x1378_x1460_vector).rdPort(x1378_x1482_x1487_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x1378_x1482))
      Stage(stage(1), operands=List(x1443(0), Const("64i")), op=FixMul, results=List(CU.temp(stage(1), tr188)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr188), CU.ctr(stage(1), x1443(1))), op=FixAdd, results=List(x1378_x1482.writeAddr, CU.temp(stage(2), tr189)))
      stage = stage0 +: RAStages(2, List(x1378_x1482))
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1465(0)), Const("64i")), op=FixMul, results=List(CU.temp(stage(1), tr191)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr191), CU.ctr(stage(1), x1481(0))), op=FixAdd, results=List(x1378_x1482.readAddr, CU.temp(stage(2), tr192)))
    }
    val x1378_dsp1 = MemoryPipeline(name="x1378_dsp1",parent="x1494") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr195 = CU.temp
      val tr196 = CU.temp
      val tr198 = CU.temp
      val tr199 = CU.temp
      val x1443 = CounterChain.copy("x1462", "x1443")
      val x1465 = CounterChain.copy("x1493", "x1465")
      val x1481 = CounterChain.copy("x1487", "x1481")
      val x1378_x1456 = SRAM(size = 256, banking = Strided(1)).wtPort(x1378_x1460_vector).rdPort(x1378_x1482_x1487_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x1378_x1456))
      Stage(stage(1), operands=List(x1443(0), Const("64i")), op=FixMul, results=List(CU.temp(stage(1), tr195)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr195), CU.ctr(stage(1), x1443(1))), op=FixAdd, results=List(x1378_x1456.writeAddr, CU.temp(stage(2), tr196)))
      stage = stage0 +: RAStages(2, List(x1378_x1456))
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1465(0)), Const("64i")), op=FixMul, results=List(CU.temp(stage(1), tr198)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr198), CU.ctr(stage(1), x1481(0))), op=FixAdd, results=List(x1378_x1456.readAddr, CU.temp(stage(2), tr199)))
    }
    val x1463 = MetaPipeline(name="x1463",parent=x1494) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr3 = (Const("0i").out, x1358_x1379.load, Const("64i").out) // Counter
      val x1381 = CounterChain(name = "x1381", ctr3)
      val x1358_x1379 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x1358_argin).rdPort(None)
      var stage: List[Stage] = Nil
    }
    val x1382_dsp0 = MemoryPipeline(name="x1382_dsp0",parent="x1463") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr202 = CU.temp
      val tr203 = CU.temp
      val tr205 = CU.temp
      val tr206 = CU.temp
      val x1385 = CounterChain.copy("x1411", "x1385")
      val x1404 = CounterChain.copy("x1409", "x1404")
      val x1443 = CounterChain.copy("x1462", "x1443")
      val x1446 = CounterChain.copy("x1455", "x1446")
      val x1382_x1447 = SRAM(size = 256, banking = Strided(1)).wtPort(x1382_x1408_vector).rdPort(x1382_x1447_x1455_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x1382_x1447))
      Stage(stage(1), operands=List(x1385(0), Const("64i")), op=FixMul, results=List(CU.temp(stage(1), tr202)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr202), CU.ctr(stage(1), x1404(0))), op=FixAdd, results=List(x1382_x1447.writeAddr, CU.temp(stage(2), tr203)))
      stage = stage0 +: RAStages(2, List(x1382_x1447))
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1443(0)), Const("64i")), op=FixMul, results=List(CU.temp(stage(1), tr205)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr205), CU.ctr(stage(1), x1446(0))), op=FixAdd, results=List(x1382_x1447.readAddr, CU.temp(stage(2), tr206)))
    }
    val x1383_dsp0 = MemoryPipeline(name="x1383_dsp0",parent="x1463") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr209 = CU.temp
      val tr210 = CU.temp
      val tr212 = CU.temp
      val tr213 = CU.temp
      val x1413 = CounterChain.copy("x1439", "x1413")
      val x1432 = CounterChain.copy("x1437", "x1432")
      val x1443 = CounterChain.copy("x1462", "x1443")
      val x1446 = CounterChain.copy("x1455", "x1446")
      val x1383_x1448 = SRAM(size = 4096, banking = Strided(1)).wtPort(x1383_x1436_vector).rdPort(x1383_x1448_x1455_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x1383_x1448))
      Stage(stage(1), operands=List(x1413(0), Const("64i")), op=FixMul, results=List(CU.temp(stage(1), tr209)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr209), CU.ctr(stage(1), x1432(0))), op=FixAdd, results=List(x1383_x1448.writeAddr, CU.temp(stage(2), tr210)))
      stage = stage0 +: RAStages(2, List(x1383_x1448))
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1446(0)), Const("64i")), op=FixMul, results=List(CU.temp(stage(1), tr212)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr212), CU.ctr(stage(1), x1443(1))), op=FixAdd, results=List(x1383_x1448.readAddr, CU.temp(stage(2), tr213)))
    }
    val x1411 = StreamController(name="x1411",parent=x1463) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr4 = (Const("0i").out, Const("4i").out, Const("1i").out) // Counter
      val x1385 = CounterChain(name = "x1385", ctr4)
      var stage: List[Stage] = Nil
    }
    val x1399 = UnitPipeline(name="x1399",parent=x1411) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr215 = CU.temp
      val tr216 = CU.temp
      val tr217 = CU.temp
      val tr219 = CU.temp
      val tr220 = CU.temp
      val tr221 = CU.temp
      val tr222 = CU.temp
      val tr223 = CU.temp
      val tr224 = CU.temp
      val x1377 = CounterChain.copy("x1494", "x1377")
      val x1385 = CounterChain.copy("x1411", "x1385")
      val x1381 = CounterChain.copy("x1463", "x1381")
      val x1389 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x1389_argin).rdPort(None)
      val x1358_x1390 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x1358_argin).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(10)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1377(0)), CU.ctr(stage(0), x1385(0))), op=FixAdd, results=List(CU.temp(stage(1), tr215)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr215), CU.load(stage(1), x1358_x1390)), op=FixMul, results=List(CU.temp(stage(2), tr216)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr216), CU.ctr(stage(2), x1381(0))), op=FixAdd, results=List(CU.temp(stage(3), tr217)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr217), Const("4i")), op=FixMul, results=List(CU.temp(stage(4), tr219)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr219), CU.load(stage(4), x1389)), op=FixAdd, results=List(CU.temp(stage(5), tr220)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr220), Const("4i")), op=FixMul, results=List(CU.temp(stage(6), tr221)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr222)), op=Bypass, results=List(CU.scalarOut(stage(7), x1386_b1512_x1397_b1515_scalar)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr223)), op=Bypass, results=List(CU.scalarOut(stage(8), x1386_b1513_x1397_b1516_scalar)))
      Stage(stage(9), operands=List(CU.temp(stage(8), tr224)), op=Bypass, results=List(CU.scalarOut(stage(9), x1386_b1514_x1397_b1517_scalar)))
      Stage(stage(10), operands=List(Const("64i")), op=Bypass, results=List(CU.scalarOut(stage(10), x1387_x1398_scalar)))
    }
    val x1400 = Fringe(name="x1400",parent=x1411,dram=x1364_oc, mode=TileLoad) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1386_b1512_x1400 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x1386_b1512_x1397_b1515_scalar).rdPort(None)
      val x1386_b1514_x1400 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x1386_b1514_x1397_b1517_scalar).rdPort(None)
      val x1386_b1513_x1400 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x1386_b1513_x1397_b1516_scalar).rdPort(None)
      var stage: List[Stage] = Nil
    }
    val x1410 = Sequential(name="x1410",parent=x1411) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x1402 = Sequential(name="x1402",parent=x1410) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x1409 = Pipeline(name="x1409",parent=x1410) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1385 = CounterChain.copy("x1411", "x1385")
      val ctr5 = (Const("0i").out, Const("64i").out, Const("1i").out) // Counter
      val x1404 = CounterChain(name = "x1404", ctr5)
      val x1388_x1405 = VectorFIFO(size = 1, banking = Strided(1)).wtPort(None).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x1388_x1405.load), op=Bypass, results=List(CU.vecOut(stage(1), x1382_x1408_vector)))
    }
    val x1439 = StreamController(name="x1439",parent=x1463) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr6 = (Const("0i").out, Const("64i").out, Const("1i").out) // Counter
      val x1413 = CounterChain(name = "x1413", ctr6)
      var stage: List[Stage] = Nil
    }
    val x1427 = UnitPipeline(name="x1427",parent=x1439) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr226 = CU.temp
      val tr227 = CU.temp
      val tr228 = CU.temp
      val tr230 = CU.temp
      val tr231 = CU.temp
      val tr232 = CU.temp
      val tr233 = CU.temp
      val tr234 = CU.temp
      val tr235 = CU.temp
      val x1381 = CounterChain.copy("x1463", "x1381")
      val x1413 = CounterChain.copy("x1439", "x1413")
      val x1377 = CounterChain.copy("x1494", "x1377")
      val x1357_x1418 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x1357_argin).rdPort(None)
      val x1417 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x1417_argin).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(10)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1381(0)), CU.ctr(stage(0), x1413(0))), op=FixAdd, results=List(CU.temp(stage(1), tr226)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr226), CU.load(stage(1), x1357_x1418)), op=FixMul, results=List(CU.temp(stage(2), tr227)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr227), CU.ctr(stage(2), x1377(1))), op=FixAdd, results=List(CU.temp(stage(3), tr228)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr228), Const("4i")), op=FixMul, results=List(CU.temp(stage(4), tr230)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr230), CU.load(stage(4), x1417)), op=FixAdd, results=List(CU.temp(stage(5), tr231)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr231), Const("4i")), op=FixMul, results=List(CU.temp(stage(6), tr232)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr233)), op=Bypass, results=List(CU.scalarOut(stage(7), x1414_b1520_x1425_b1523_scalar)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr234)), op=Bypass, results=List(CU.scalarOut(stage(8), x1414_b1521_x1425_b1524_scalar)))
      Stage(stage(9), operands=List(CU.temp(stage(8), tr235)), op=Bypass, results=List(CU.scalarOut(stage(9), x1414_b1522_x1425_b1525_scalar)))
      Stage(stage(10), operands=List(Const("64i")), op=Bypass, results=List(CU.scalarOut(stage(10), x1415_x1426_scalar)))
    }
    val x1428 = Fringe(name="x1428",parent=x1439,dram=x1367_oc, mode=TileLoad) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1414_b1521_x1428 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x1414_b1521_x1425_b1524_scalar).rdPort(None)
      val x1414_b1520_x1428 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x1414_b1520_x1425_b1523_scalar).rdPort(None)
      val x1414_b1522_x1428 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x1414_b1522_x1425_b1525_scalar).rdPort(None)
      var stage: List[Stage] = Nil
    }
    val x1438 = Sequential(name="x1438",parent=x1439) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x1430 = Sequential(name="x1430",parent=x1438) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x1437 = Pipeline(name="x1437",parent=x1438) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1413 = CounterChain.copy("x1439", "x1413")
      val ctr7 = (Const("0i").out, Const("64i").out, Const("1i").out) // Counter
      val x1432 = CounterChain(name = "x1432", ctr7)
      val x1416_x1433 = VectorFIFO(size = 1, banking = Strided(1)).wtPort(None).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x1416_x1433.load), op=Bypass, results=List(CU.vecOut(stage(1), x1383_x1436_vector)))
    }
    val x1462 = MetaPipeline(name="x1462",parent=x1463) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr8 = (Const("0i").out, Const("4i").out, Const("1i").out) // Counter
      val ctr9 = (Const("0i").out, Const("64i").out, Const("1i").out) // Counter
      val x1443 = CounterChain(name = "x1443", ctr8, ctr9)
      var stage: List[Stage] = Nil
    }
    val x1455 = Pipeline(name="x1455",parent=x1462) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr237 = CU.temp
      val ar94 = CU.accum(init = Const("0i"))
      val x1443 = CounterChain.copy("x1462", "x1443")
      val ctr10 = (Const("0i").out, Const("64i").out, Const("1i").out) // Counter
      val x1446 = CounterChain(name = "x1446", ctr10)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x1382_x1447_x1455_vector), CU.vecIn(stage(0), x1383_x1448_x1455_vector)), op=FixMul, results=List(CU.reduce(stage(1)), CU.temp(stage(1), tr237)))
      val (rs1, rr240) = Stage.reduce(op=FixAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr240), op=Bypass, results=List(CU.vecOut(stage(2), x1444_x1454_vector)))
    }
    val x1461 = UnitPipeline(name="x1461",parent=x1462) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr242 = CU.temp
      val tr243 = CU.temp
      val tr244 = CU.temp
      val x1443 = CounterChain.copy("x1462", "x1443")
      val x1381 = CounterChain.copy("x1463", "x1381")
      val x1444_x1457 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x1444_x1454_vector).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1381(0)), Const("0i")), op=FixEql, results=List(CU.temp(stage(1), tr242)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr242), Const("0i"), CU.vecIn(stage(1), x1378_x1456_x1461_vector)), op=Mux, results=List(CU.temp(stage(2), tr243)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr243), CU.load(stage(2), x1444_x1457)), op=FixAdd, results=List(CU.vecOut(stage(3), x1378_x1460_vector), CU.temp(stage(3), tr244)))
    }
    val x1493 = StreamController(name="x1493",parent=x1494) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr11 = (Const("0i").out, Const("4i").out, Const("1i").out) // Counter
      val x1465 = CounterChain(name = "x1465", ctr11)
      var stage: List[Stage] = Nil
    }
    val x1488 = Sequential(name="x1488",parent=x1493) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x1479 = UnitPipeline(name="x1479",parent=x1488) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr245 = CU.temp
      val tr246 = CU.temp
      val tr247 = CU.temp
      val tr248 = CU.temp
      val tr250 = CU.temp
      val tr251 = CU.temp
      val tr252 = CU.temp
      val tr253 = CU.temp
      val x1377 = CounterChain.copy("x1494", "x1377")
      val x1465 = CounterChain.copy("x1493", "x1465")
      val x1357_x1471 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x1357_argin).rdPort(None)
      val x1470 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x1470_argin).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(9)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1377(0)), CU.ctr(stage(0), x1465(0))), op=FixAdd, results=List(CU.temp(stage(1), tr245)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr245), CU.load(stage(1), x1357_x1471)), op=FixMul, results=List(CU.temp(stage(2), tr246)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr246), CU.ctr(stage(2), x1377(1))), op=FixAdd, results=List(CU.temp(stage(3), tr247)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr247), CU.load(stage(3), x1470)), op=FixAdd, results=List(CU.temp(stage(4), tr248)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr248), Const("4i")), op=FixMul, results=List(CU.temp(stage(5), tr250)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr251)), op=Bypass, results=List(CU.scalarOut(stage(6), x1466_b1536_x1477_b1541_scalar)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr252)), op=Bypass, results=List(CU.scalarOut(stage(7), x1466_b1537_x1477_b1542_scalar)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr253)), op=Bypass, results=List(CU.scalarOut(stage(8), x1466_b1538_x1477_b1543_scalar)))
      Stage(stage(9), operands=List(Const("64i")), op=Bypass, results=List(CU.scalarOut(stage(9), x1467_x1478_scalar)))
    }
    val x1487 = Pipeline(name="x1487",parent=x1488) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr255 = CU.temp
      val tr256 = CU.temp
      val x1465 = CounterChain.copy("x1493", "x1465")
      val ctr12 = (Const("0i").out, Const("64i").out, Const("1i").out) // Counter
      val x1481 = CounterChain(name = "x1481", ctr12)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.temp(stage(0), tr255)), op=Bypass, results=List(CU.vecOut(stage(1), x1468_b1539_x1486_b1546_vector)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr256)), op=Bypass, results=List(CU.vecOut(stage(2), x1468_b1540_x1486_b1547_vector)))
    }
    val x1489 = Fringe(name="x1489",parent=x1493,dram=x1370_oc, mode=TileStore) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1468_b1539_x1489 = VectorFIFO(size = 1, banking = Strided(1)).wtPort(x1468_b1539_x1486_b1546_vector).rdPort(None)
      val x1468_b1540_x1489 = VectorFIFO(size = 1, banking = Strided(1)).wtPort(x1468_b1540_x1486_b1547_vector).rdPort(None)
      val x1466_b1538_x1489 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x1466_b1538_x1477_b1543_scalar).rdPort(None)
      val x1466_b1537_x1489 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x1466_b1537_x1477_b1542_scalar).rdPort(None)
      val x1466_b1536_x1489 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x1466_b1536_x1477_b1541_scalar).rdPort(None)
      var stage: List[Stage] = Nil
    }
    val x1492 = Sequential(name="x1492",parent=x1493) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    
  }
}
