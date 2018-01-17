import pir._
import pir.newnode._
import arch._
import pirc.enums._

object DotProduct extends PIRApp {
  def main(top:Top) = {
    val x1451 = top.argIn(init=Const(0).ctrl(top)).name("x1451").ctrl(top) // ArgInNew(Const(0))
    val x1454 = LoadDef(List(x1451), None).name("x1454").ctrl(top) // RegRead(x1451)
    val x1455 = DRAM().name("x1455").ctrl(top) // x1455 = DRAMNew(ArrayBuffer(x1454),Const(0))
    val x1456 = LoadDef(List(x1451), None).name("x1456").ctrl(top) // RegRead(x1451)
    val x1457 = DRAM().name("x1457").ctrl(top) // x1457 = DRAMNew(ArrayBuffer(x1456),Const(0))
    val x1458 = top.argOut(init=Const(0).ctrl(top)).name("x1458").ctrl(top) // ArgOutNew(Const(0))
    val x1533 = Controller(style=SeqPipe, level=OuterControl, cchain=CounterChain.unit).name("x1533").ctrl(top) // Hwblock(Block(Const(())),false)
    val x1461_d0 = Reg(init=Const(0).ctrl(x1533)).name("x1461_d0").ctrl(x1533) // x1461 = RegNew(Const(0))
    val x1461_d1 = Reg(init=Const(0).ctrl(x1533)).name("x1461_d1").ctrl(x1533) // x1461 = RegNew(Const(0))
    val x1462 = LoadDef(List(x1451), None).name("x1462").ctrl(x1533) // RegRead(x1451)
    val x1463 = Counter(min=Const(0).ctrl(x1533), max=x1462, step=Const(16).ctrl(x1533), par=1).name("x1463").ctrl(x1533) // CounterNew(Const(0),x1462,Const(16),Const(1))
    val x1464 = CounterChain(List(x1463)).name("x1464").ctrl(x1533) // CounterChainNew(List(x1463))
    val x1529 = Controller(style=MetaPipe, level=OuterControl, cchain=x1464).name("x1529").ctrl(x1533) // UnrolledReduce(List(Const(true)),x1464,x1461,Block((x1461) => Const(())),List(List(b928)),List(List(b929)))
    val b928 = IterDef(x1463, Some(0)).ctrl(x1533).name("b928")
    val b929 = DummyDef().ctrl(x1533).name("b929")
    val x1465_d0_b0 = SRAM(size=1, banking=Strided(banks=16, stride=1)).name("x1465_d0_b0").ctrl(x1529) // x1465 = SRAMNew(ArrayBuffer(Const(16)))
    val x1466_d0_b0 = SRAM(size=1, banking=Strided(banks=16, stride=1)).name("x1466_d0_b0").ctrl(x1529) // x1466 = SRAMNew(ArrayBuffer(Const(16)))
    val x1468 = Controller(style=SeqPipe, level=InnerControl, cchain=CounterChain.unit).name("x1468").ctrl(x1529) // UnitPipe(List(b929),Block(Const(())))
    val x1467 = OpDef(op=FixAdd, inputs=List(b928, Const(16).ctrl(x1468))).name("x1467").ctrl(x1468) // FixAdd(b928,Const(16))
    val x1488 = Controller(style=StreamPipe, level=OuterControl, cchain=CounterChain.unit).name("x1488").ctrl(x1529) // UnitPipe(List(b929),Block(Const(())))
    val b1552 = StreamOut(field="offset").name("b1552").ctrl(x1488) // x1469 = StreamOutNew(BurstCmdBus)
    val b1553 = StreamOut(field="size").name("b1553").ctrl(x1488) // x1469 = StreamOutNew(BurstCmdBus)
    val b1554 = StreamIn(field="data").name("b1554").ctrl(x1488) // x1470 = StreamInNew(BurstDataBus())
    val x1479 = Controller(style=SeqPipe, level=InnerControl, cchain=CounterChain.unit).name("x1479").ctrl(x1488) // UnitPipe(List(b929),Block(x1478))
    val x1471 = b928 // FixConvert(b928)
    val x1472 = OpDef(op=FixSla, inputs=List(x1471, Const(2).ctrl(x1479))).name("x1472").ctrl(x1479) // FixLsh(x1471,Const(2))
    val x1473 = x1472 // FixConvert(x1472)
    val x1474 = top.dramAddress(x1455).name("x1474").ctrl(x1479) // GetDRAMAddress(x1455)
    val x1475 = OpDef(op=FixAdd, inputs=List(x1473, x1474)).name("x1475").ctrl(x1479) // FixAdd(x1473,x1474)
    val x1476 = x1475 // FixConvert(x1475)
    // x1477 = SimpleStruct(ArrayBuffer((offset,x1476), (size,Const(64)), (isLoad,Const(true))))
    val b1555 = StoreDef(List(b1552), None, x1476).name("b1555").ctrl(x1479) // StreamWrite(x1469,x1477,b929)
    val b1556 = StoreDef(List(b1553), None, Const(64)).name("b1556").ctrl(x1479) // StreamWrite(x1469,x1477,b929)
    val x1480 = CUContainer(x1455,b1552,b1553,b1554).name("x1480").ctrl(x1488) // FringeDenseLoad(x1455,x1469,x1470)
    val x1481 = Counter(min=Const(0).ctrl(x1488), max=Const(16).ctrl(x1488), step=Const(1).ctrl(x1488), par=16).name("x1481").ctrl(x1488) // CounterNew(Const(0),Const(16),Const(1),Const(16))
    val x1482 = CounterChain(List(x1481)).name("x1482").ctrl(x1488) // CounterChainNew(List(x1481))
    val x1487 = Controller(style=InnerPipe, level=InnerControl, cchain=x1482).name("x1487").ctrl(x1488) // UnrolledForeach(List(b929),x1482,Block(Const(())),List(List(b948)),List(List(b949)))
    val b948 = IterDef(x1481, None).ctrl(x1488).name("b948")
    val b949 = DummyDef().ctrl(x1488).name("b949")
    val x1483 = OpDef(op=BitAnd, inputs=List(b949, b929)).name("x1483").ctrl(x1487) // And(b949,b929)
    val b1557 = LoadDef(List(b1554), None).name("b1557").ctrl(x1487) // ParStreamRead(x1470,List(x1483))
    val b1558 = b1557 // x1485 = VectorApply(x1484,0)
    val x1486 = StoreDef(List(x1465_d0_b0), Some(List(b948)), b1558).name("x1486").ctrl(x1487) // ParSRAMStore(x1465,List(List(b948)),List(x1485),List(x1483))
    val x1508 = Controller(style=StreamPipe, level=OuterControl, cchain=CounterChain.unit).name("x1508").ctrl(x1529) // UnitPipe(List(b929),Block(Const(())))
    val b1559 = StreamOut(field="offset").name("b1559").ctrl(x1508) // x1489 = StreamOutNew(BurstCmdBus)
    val b1560 = StreamOut(field="size").name("b1560").ctrl(x1508) // x1489 = StreamOutNew(BurstCmdBus)
    val b1561 = StreamIn(field="data").name("b1561").ctrl(x1508) // x1490 = StreamInNew(BurstDataBus())
    val x1499 = Controller(style=SeqPipe, level=InnerControl, cchain=CounterChain.unit).name("x1499").ctrl(x1508) // UnitPipe(List(b929),Block(x1498))
    val x1491 = b928 // FixConvert(b928)
    val x1492 = OpDef(op=FixSla, inputs=List(x1491, Const(2).ctrl(x1499))).name("x1492").ctrl(x1499) // FixLsh(x1491,Const(2))
    val x1493 = x1492 // FixConvert(x1492)
    val x1494 = top.dramAddress(x1457).name("x1494").ctrl(x1499) // GetDRAMAddress(x1457)
    val x1495 = OpDef(op=FixAdd, inputs=List(x1493, x1494)).name("x1495").ctrl(x1499) // FixAdd(x1493,x1494)
    val x1496 = x1495 // FixConvert(x1495)
    // x1497 = SimpleStruct(ArrayBuffer((offset,x1496), (size,Const(64)), (isLoad,Const(true))))
    val b1562 = StoreDef(List(b1559), None, x1496).name("b1562").ctrl(x1499) // StreamWrite(x1489,x1497,b929)
    val b1563 = StoreDef(List(b1560), None, Const(64)).name("b1563").ctrl(x1499) // StreamWrite(x1489,x1497,b929)
    val x1500 = CUContainer(x1457,b1559,b1560,b1561).name("x1500").ctrl(x1508) // FringeDenseLoad(x1457,x1489,x1490)
    val x1501 = Counter(min=Const(0).ctrl(x1508), max=Const(16).ctrl(x1508), step=Const(1).ctrl(x1508), par=16).name("x1501").ctrl(x1508) // CounterNew(Const(0),Const(16),Const(1),Const(16))
    val x1502 = CounterChain(List(x1501)).name("x1502").ctrl(x1508) // CounterChainNew(List(x1501))
    val x1507 = Controller(style=InnerPipe, level=InnerControl, cchain=x1502).name("x1507").ctrl(x1508) // UnrolledForeach(List(b929),x1502,Block(Const(())),List(List(b970)),List(List(b971)))
    val b970 = IterDef(x1501, None).ctrl(x1508).name("b970")
    val b971 = DummyDef().ctrl(x1508).name("b971")
    val x1503 = OpDef(op=BitAnd, inputs=List(b971, b929)).name("x1503").ctrl(x1507) // And(b971,b929)
    val b1564 = LoadDef(List(b1561), None).name("b1564").ctrl(x1507) // ParStreamRead(x1490,List(x1503))
    val b1565 = b1564 // x1505 = VectorApply(x1504,0)
    val x1506 = StoreDef(List(x1466_d0_b0), Some(List(b970)), b1565).name("x1506").ctrl(x1507) // ParSRAMStore(x1466,List(List(b970)),List(x1505),List(x1503))
    val x1509_d0 = Reg(init=Const(0).ctrl(x1529)).name("x1509_d0").ctrl(x1529) // x1509 = RegNew(Const(0))
    val x1509_d1 = Reg(init=Const(0).ctrl(x1529)).name("x1509_d1").ctrl(x1529) // x1509 = RegNew(Const(0))
    val x1510 = Counter(min=Const(0).ctrl(x1529), max=Const(16).ctrl(x1529), step=Const(1).ctrl(x1529), par=16).name("x1510").ctrl(x1529) // CounterNew(Const(0),Const(16),Const(1),Const(16))
    val x1511 = CounterChain(List(x1510)).name("x1511").ctrl(x1529) // CounterChainNew(List(x1510))
    val x1522 = Controller(style=InnerPipe, level=InnerControl, cchain=x1511).name("x1522").ctrl(x1529) // UnrolledReduce(List(b929),x1511,x1509,Block((x1509) => Const(())),List(List(b982)),List(List(b983)))
    val b982 = IterDef(x1510, None).ctrl(x1529).name("b982")
    val b983 = DummyDef().ctrl(x1529).name("b983")
    val x1512 = OpDef(op=BitAnd, inputs=List(b983, b929)).name("x1512").ctrl(x1522) // And(b983,b929)
    val x1513 = LoadDef(List(x1465_d0_b0), Some(List(b982))).name("x1513").ctrl(x1522) // ParSRAMLoad(x1465,List(List(b982)),List(x1512))
    val x1514 = x1513 // x1514 = VectorApply(x1513,0)
    val x1515 = LoadDef(List(x1466_d0_b0), Some(List(b982))).name("x1515").ctrl(x1522) // ParSRAMLoad(x1466,List(List(b982)),List(x1512))
    val x1516 = x1515 // x1516 = VectorApply(x1515,0)
    val x1517 = OpDef(op=FixMul, inputs=List(x1514, x1516)).name("x1517").ctrl(x1522) // FixMul(x1514,x1516)
    val x1518 = LoadDef(List(x1509_d1), None).name("x1518").ctrl(x1522) // RegRead(x1509)
    val x1519 = OpDef(op=FixEql, inputs=List(b982, Const(0).ctrl(x1522))).name("x1519").ctrl(x1522) // FixEql(b982,Const(0))
    val x1517_0 = ReduceDef(op=FixAdd, input=x1517).name("x1517_0").ctrl(x1522) // FixAdd(x1517,x1518)
    val x1517_1 = ReduceDef(op=FixAdd, input=x1517_0).name("x1517_1").ctrl(x1522) // FixAdd(x1517,x1518)
    val x1517_2 = ReduceDef(op=FixAdd, input=x1517_1).name("x1517_2").ctrl(x1522) // FixAdd(x1517,x1518)
    val x1517_3 = ReduceDef(op=FixAdd, input=x1517_2).name("x1517_3").ctrl(x1522) // FixAdd(x1517,x1518)
    val x1520 = AccumDef(op=FixAdd, input=x1517_3, accum=x1518).name("x1520").ctrl(x1522) // FixAdd(x1517,x1518)
    val x1521 = StoreDef(List(x1509_d0, x1509_d1), None, x1520).name("x1521").ctrl(x1522) // RegWrite(x1509,x1520,b929)
    val x1528 = Controller(style=SeqPipe, level=InnerControl, cchain=CounterChain.unit).name("x1528").ctrl(x1529) // UnitPipe(List(Const(true)),Block(x1527))
    val x1523 = LoadDef(List(x1461_d1), None).name("x1523").ctrl(x1528) // RegRead(x1461)
    val x1524 = OpDef(op=FixEql, inputs=List(b928, Const(0).ctrl(x1528))).name("x1524").ctrl(x1528) // FixEql(b928,Const(0))
    val x1525 = LoadDef(List(x1509_d0), None).name("x1525").ctrl(x1528) // RegRead(x1509)
    val x1526 = AccumDef(op=FixAdd, input=x1525, accum=x1523).name("x1526").ctrl(x1528) // FixAdd(x1525,x1523)
    val x1527 = StoreDef(List(x1461_d0, x1461_d1), None, x1526).name("x1527").ctrl(x1528) // RegWrite(x1461,x1526,Const(true))
    val x1532 = Controller(style=SeqPipe, level=InnerControl, cchain=CounterChain.unit).name("x1532").ctrl(x1533) // UnitPipe(List(Const(true)),Block(Const(())))
    val x1530 = LoadDef(List(x1461_d0), None).name("x1530").ctrl(x1532) // RegRead(x1461)
    val x1531 = StoreDef(List(x1458), None, x1530).name("x1531").ctrl(x1532) // RegWrite(x1458,x1530,Const(true))
    
  }
}
