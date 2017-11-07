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
    val a_oc = OffChip("a")
    val a_da = DRAMAddress("a", "a")
    val x1490_x1500_data_v = Vector("x1490_x1500_data")
    val b_da = DRAMAddress("b", "b")
    val x1465_x1465_dsp0_bank0_data_v = Vector("x1465_x1465_dsp0_bank0_data")
    val x1489_b1556_x1498_b1558_s = Scalar("x1489_b1556_x1498_b1558")
    val x1469_b1553_x1478_b1555_s = Scalar("x1469_b1553_x1478_b1555")
    val x1470_x1480_data_v = Vector("x1470_x1480_data")
    val x1466_x1466_dsp0_bank0_data_v = Vector("x1466_x1466_dsp0_bank0_data")
    val N_argin = ArgIn("N").bound(1920000)
    val x1458_x1531_argout = ArgOut("x1458_x1531")
    val x1529 = MetaPipeline(name="x1529",parent="top") { implicit CU => 
      val x1451 = ScalarBuffer(name="x1451")
        .buffering(1)
        .store(N_argin, None, None)
      val ctr1 = Counter(min=Const(0), max=x1451.readPort, step=Const(16), par=1) // Counter
      val x1464 = CounterChain(name = "x1464", ctr1).iter(120000)
    }
    val x1465_dsp0_bank0 = MemoryPipeline(name="x1465_dsp0_bank0",parent="x1529") { implicit CU => 
      val x1486 = VectorFIFO(size=1,name="x1486")
        .store(x1470_x1480_data_v, None, None)
      val x1482 = CounterChain.copy("x1487", "x1482")
      val x1511 = CounterChain.copy("x1522_0", "x1511")
      val x1465 = SRAM(size=1,name="x1465",banking = Strided(1,16))
        .buffering(2)
        .store(x1486.readPort, Some(x1482(0)), Some("x1488"))
        .load(x1465_x1465_dsp0_bank0_data_v, Some(x1511(0)), Some("x1522_0"))
    }
    val x1466_dsp0_bank0 = MemoryPipeline(name="x1466_dsp0_bank0",parent="x1529") { implicit CU => 
      val x1506 = VectorFIFO(size=1,name="x1506")
        .store(x1490_x1500_data_v, None, None)
      val x1502 = CounterChain.copy("x1507", "x1502")
      val x1511 = CounterChain.copy("x1522_0", "x1511")
      val x1466 = SRAM(size=1,name="x1466",banking = Strided(1,16))
        .buffering(2)
        .store(x1506.readPort, Some(x1502(0)), Some("x1508"))
        .load(x1466_x1466_dsp0_bank0_data_v, Some(x1511(0)), Some("x1522_0"))
    }
    val x1488 = StreamController(name="x1488",parent="x1529") { implicit CU => 
      val x1488_unit = CounterChain(name = "x1488_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x1479_0 = Pipeline(name="x1479_0",parent="x1488") { implicit CU => 
      val x1472 = CU.temp(None).name("x1472")
      val x1474 = ScalarBuffer(name="x1474")
        .buffering(1)
        .store(a_da, None, None)
      val x1464 = CounterChain.copy("x1529", "x1464").iterIdx(0, 0)
      val x1479_unit = CounterChain(name = "x1479_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(x1464(0), Const(2)), op=FixSla, results=List(x1472))
      Stage(operands=List(x1472, x1474), op=FixAdd, results=List(CU.scalarOut(x1469_b1552_x1478_b1554_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x1469_b1553_x1478_b1555_s)))
    }
    val x1480 = MemoryController(name="x1480",parent="x1488",offchip=a_oc, mctpe=TileLoad) { implicit CU => 
      val x1469_b1553 = ScalarFIFO(size=1,name="size")
        .store(x1469_b1553_x1478_b1555_s, None, None)
      val x1469_b1552 = ScalarFIFO(size=1,name="offset")
        .store(x1469_b1552_x1478_b1554_s, None, None)
      CU.newOut("data", x1470_x1480_data_v)
    }
    val x1487 = Pipeline(name="x1487",parent="x1488") { implicit CU => 
      val ctr2 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x1482 = CounterChain(name = "x1482", ctr2).iter(1)
    }
    val x1508 = StreamController(name="x1508",parent="x1529") { implicit CU => 
      val x1508_unit = CounterChain(name = "x1508_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x1499_0 = Pipeline(name="x1499_0",parent="x1508") { implicit CU => 
      val x1492 = CU.temp(None).name("x1492")
      val x1494 = ScalarBuffer(name="x1494")
        .buffering(1)
        .store(b_da, None, None)
      val x1464 = CounterChain.copy("x1529", "x1464").iterIdx(0, 0)
      val x1499_unit = CounterChain(name = "x1499_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(x1464(0), Const(2)), op=FixSla, results=List(x1492))
      Stage(operands=List(x1492, x1494), op=FixAdd, results=List(CU.scalarOut(x1489_b1556_x1498_b1558_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x1489_b1557_x1498_b1559_s)))
    }
    val x1500 = MemoryController(name="x1500",parent="x1508",offchip=b_oc, mctpe=TileLoad) { implicit CU => 
      val x1489_b1557 = ScalarFIFO(size=1,name="size")
        .store(x1489_b1557_x1498_b1559_s, None, None)
      val x1489_b1556 = ScalarFIFO(size=1,name="offset")
        .store(x1489_b1556_x1498_b1558_s, None, None)
      CU.newOut("data", x1490_x1500_data_v)
    }
    val x1507 = Pipeline(name="x1507",parent="x1508") { implicit CU => 
      val ctr3 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x1502 = CounterChain(name = "x1502", ctr3).iter(1)
    }
    val x1522_0 = Pipeline(name="x1522_0",parent="x1529") { implicit CU => 
      val x1515 = VectorFIFO(size=1,name="x1515")
        .store(x1466_x1466_dsp0_bank0_data_v, None, None)
      val x1513 = VectorFIFO(size=1,name="x1513")
        .store(x1465_x1465_dsp0_bank0_data_v, None, None)
      val ctr4 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x1511 = CounterChain(name = "x1511", ctr4).iter(1)
      Stage(operands=List(x1513, x1515), op=FixMul, results=List(CU.reduce))
      val (_, rr93) = Stage.reduce(op=FixAdd, init=Const(0), accumParent="x1522_0")
      Stage(operands=List(rr93), op=Bypass, results=List(CU.scalarOut(x1509_x1521_s)))
    }
    val x1528_0 = Pipeline(name="x1528_0",parent="x1529") { implicit CU => 
      val x1509 = ScalarBuffer(name="x1509")
        .buffering(2)
        .store(x1509_x1521_s, None, Some("x1522_0"))
      val x1528_unit = CounterChain(name = "x1528_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(x1509), op=Bypass, results=List(CU.reduce))
      val (_, rr97) = Stage.reduce(op=FixAdd, init=Const(0), accumParent="x1529")
      Stage(operands=List(rr97), op=Bypass, results=List(CU.scalarOut(x1458_x1531_argout)))
    }
    
  }
}
