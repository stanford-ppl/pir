import pir._
import pir.node._
import arch._
import pirc.enums._

object DotProduct extends PIRApp {
  def main(top:Top) = {
    val x1469_b1552_x1478_b1554_s = Scalar("x1469_b1552_x1478_b1554")
    val x1509_x1521_s = Scalar("x1509_x1521")
    val b_oc = OffChip("b")
    val x1489_b1557_x1498_b1559_s = Scalar("x1489_b1557_x1498_b1559")
    val x1466_x1466_dsp0_bank0_data_s = Scalar("x1466_x1466_dsp0_bank0_data")
    val a_oc = OffChip("a")
    val a_da = DRAMAddress("a", "a")
    val x1465_x1513_ra_s = Scalar("x1465_x1513_ra")
    val x1470_x1480_data_s = Scalar("x1470_x1480_data")
    val b_da = DRAMAddress("b", "b")
    val x1466_x1515_ra_s = Scalar("x1466_x1515_ra")
    val x1465_x1465_dsp0_bank0_data_s = Scalar("x1465_x1465_dsp0_bank0_data")
    val x1469_b1553_x1478_b1555_s = Scalar("x1469_b1553_x1478_b1555")
    val x1465_x1486_wa_s = Scalar("x1465_x1486_wa")
    val x1466_x1506_wa_s = Scalar("x1466_x1506_wa")
    val N_argin = ArgIn("N").bound(1920000)
    val x1489_b1558_x1498_b1560_s = Scalar("x1489_b1558_x1498_b1560")
    val x1490_x1500_data_s = Scalar("x1490_x1500_data")
    val x1458_x1531_argout = ArgOut("x1458_x1531")
    val x1529 = MetaPipeline(name="x1529",parent="top") { implicit CU => 
      val x1451 = ScalarBuffer(name="x1451").buffering(1).wtPort(N_argin)
      val ctr1 = Counter(min=Const(0), max=x1451.readPort, step=Const(16), par=1) // Counter
      val x1464 = CounterChain(name = "x1464", ctr1).iter(120000)
    }
    val x1465_dsp0_bank0 = MemoryPipeline(name="x1465_dsp0_bank0",parent="x1529") { implicit CU => 
      val b1562 = ScalarFIFO(size=1,name="b1562").wtPort(x1465_x1513_ra_s)
      val b1556 = ScalarFIFO(size=1,name="b1556").wtPort(x1465_x1486_wa_s)
      val x1486 = ScalarFIFO(size=1,name="x1486").wtPort(x1470_x1480_data_s)
      val x1465 = SRAM(size=1,name="x1465",banking = Strided(1,16)).buffering(2).wtPort(x1486.readPort).rdPort(x1465_x1465_dsp0_bank0_data_s).rdAddr(b1562.readPort).wtAddr(b1556.readPort).consumer("x1513", "x1522").producer("x1487", "x1488")
    }
    val x1466_dsp0_bank0 = MemoryPipeline(name="x1466_dsp0_bank0",parent="x1529") { implicit CU => 
      val b1563 = ScalarFIFO(size=1,name="b1563").wtPort(x1466_x1515_ra_s)
      val x1506 = ScalarFIFO(size=1,name="x1506").wtPort(x1490_x1500_data_s)
      val b1561 = ScalarFIFO(size=1,name="b1561").wtPort(x1466_x1506_wa_s)
      val x1466 = SRAM(size=1,name="x1466",banking = Strided(1,16)).buffering(2).wtPort(x1506.readPort).rdPort(x1466_x1466_dsp0_bank0_data_s).rdAddr(b1563.readPort).wtAddr(b1561.readPort).consumer("x1515", "x1522").producer("x1507", "x1508")
    }
    val x1488 = StreamController(name="x1488",parent="x1529") { implicit CU => 
      val x1488_unit = CounterChain(name = "x1488_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x1479 = Pipeline(name="x1479",parent="x1488") { implicit CU => 
      val x1472 = CU.temp(None)
      val x1474 = ScalarBuffer(name="x1474").buffering(1).wtPort(a_da)
      val x1464 = CounterChain.copy("x1529", "x1464").iterIdx(0, 0)
      val x1479_unit = CounterChain(name = "x1479_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x1464(0)), Const(2)), op=FixSla, results=List(x1472))
      Stage(operands=List(x1472, CU.load(x1474)), op=FixAdd, results=List(CU.scalarOut(x1469_b1552_x1478_b1554_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x1469_b1553_x1478_b1555_s)))
    }
    val x1480 = MemoryController(name="x1480",parent="x1488",offchip=a_oc, mctpe=TileLoad) { implicit CU => 
      val x1469_b1553 = ScalarFIFO(size=1,name="size").wtPort(x1469_b1553_x1478_b1555_s)
      val x1469_b1552 = ScalarFIFO(size=1,name="offset").wtPort(x1469_b1552_x1478_b1554_s)
      CU.newOut("data", x1470_x1480_data_s)
    }
    val x1487 = Pipeline(name="x1487",parent="x1488") { implicit CU => 
      val ctr2 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x1482 = CounterChain(name = "x1482", ctr2).iter(1)
      Stage(operands=List(CU.ctr(x1482(0))), op=Bypass, results=List(CU.scalarOut(x1465_x1486_wa_s)))
    }
    val x1508 = StreamController(name="x1508",parent="x1529") { implicit CU => 
      val x1508_unit = CounterChain(name = "x1508_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x1499 = Pipeline(name="x1499",parent="x1508") { implicit CU => 
      val x1492 = CU.temp(None)
      val x1494 = ScalarBuffer(name="x1494").buffering(1).wtPort(b_da)
      val x1464 = CounterChain.copy("x1529", "x1464").iterIdx(0, 0)
      val x1499_unit = CounterChain(name = "x1499_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x1464(0)), Const(2)), op=FixSla, results=List(x1492))
      Stage(operands=List(x1492, CU.load(x1494)), op=FixAdd, results=List(CU.scalarOut(x1489_b1557_x1498_b1559_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x1489_b1558_x1498_b1560_s)))
    }
    val x1500 = MemoryController(name="x1500",parent="x1508",offchip=b_oc, mctpe=TileLoad) { implicit CU => 
      val x1489_b1557 = ScalarFIFO(size=1,name="offset").wtPort(x1489_b1557_x1498_b1559_s)
      val x1489_b1558 = ScalarFIFO(size=1,name="size").wtPort(x1489_b1558_x1498_b1560_s)
      CU.newOut("data", x1490_x1500_data_s)
    }
    val x1507 = Pipeline(name="x1507",parent="x1508") { implicit CU => 
      val ctr3 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x1502 = CounterChain(name = "x1502", ctr3).iter(1)
      Stage(operands=List(CU.ctr(x1502(0))), op=Bypass, results=List(CU.scalarOut(x1466_x1506_wa_s)))
    }
    val x1522 = Pipeline(name="x1522",parent="x1529") { implicit CU => 
      val x1509 = CU.temp(Some(0))
      val x1515 = ScalarFIFO(size=1,name="x1515").wtPort(x1466_x1466_dsp0_bank0_data_s)
      val x1513 = ScalarFIFO(size=1,name="x1513").wtPort(x1465_x1465_dsp0_bank0_data_s)
      val ctr4 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x1511 = CounterChain(name = "x1511", ctr4).iter(1)
      Stage(operands=List(CU.load(x1513), CU.load(x1515)), op=FixMul, results=List(CU.reduce))
      val (_, rr93) = Stage.reduce(op=FixAdd, init=Const(0), accumParent="x1522")
      Stage(operands=List(rr93), op=Bypass, results=List(CU.scalarOut(x1509_x1521_s)))
    }
    val x1513 = Pipeline(name="x1513",parent="x1529") { implicit CU => 
      val x1511 = CounterChain.copy("x1522", "x1511").iterIdx(0, 0)
      Stage(operands=List(CU.ctr(x1511(0))), op=Bypass, results=List(CU.scalarOut(x1465_x1513_ra_s)))
    }
    val x1515 = Pipeline(name="x1515",parent="x1529") { implicit CU => 
      val x1511 = CounterChain.copy("x1522", "x1511").iterIdx(0, 0)
      Stage(operands=List(CU.ctr(x1511(0))), op=Bypass, results=List(CU.scalarOut(x1466_x1515_ra_s)))
    }
    val x1528 = Pipeline(name="x1528",parent="x1529") { implicit CU => 
      val x1461 = CU.temp(Some(0))
      val x1509 = ScalarBuffer(name="x1509").buffering(2).wtPort(x1509_x1521_s).consumer("x1528", "x1528").producer("x1522", "x1522")
      val x1528_unit = CounterChain(name = "x1528_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x1509)), op=Bypass, results=List(CU.reduce))
      val (_, rr97) = Stage.reduce(op=FixAdd, init=Const(0), accumParent="x1529")
      Stage(operands=List(rr97), op=Bypass, results=List(CU.scalarOut(x1458_x1531_argout)))
    }
    
  }
}
