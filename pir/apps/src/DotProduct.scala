import pir._
import pir.node._
import arch._
import prism.enums._

object DotProduct extends PIRApp {
  def main(implicit design:PIRDesign) = {
    import design.pirmeta._
    val x1449 = top.argFringe.argIn(init=0).name("x1449").ctrl(top) // ArgInNew(Const(0))
    boundOf(x1449) = 1024
    val x1452 = ReadMem(x1449).name("x1452").ctrl(top) // RegRead(x1449)
    val x1453 = DRAM().name("x1453").ctrl(top) // x1453 = DRAMNew(ArrayBuffer(x1452),Const(0))
    val x1454 = ReadMem(x1449).name("x1454").ctrl(top) // RegRead(x1449)
    val x1455 = DRAM().name("x1455").ctrl(top) // x1455 = DRAMNew(ArrayBuffer(x1454),Const(0))
    val x1456 = top.argFringe.argOut(init=0).name("x1456").ctrl(top) // ArgOutNew(Const(0))
    val x1531 = UnitController(style=SeqPipe, level=OuterControl).name("x1531").ctrl(top) // Hwblock(Block(Const(())),false)
    val x1459_d0 = Reg(init=Some(0)).name("x1459_d0").ctrl(x1531) // x1459 = RegNew(Const(0))
    isAccum(x1459_d0) = false
    bufferDepthOf(x1459_d0) = 1
    val x1459_d1 = Reg(init=Some(0)).name("x1459_d1").ctrl(x1531) // x1459 = RegNew(Const(0))
    isAccum(x1459_d1) = true
    bufferDepthOf(x1459_d1) = 1
    val x1460 = ReadMem(x1449).name("x1460").ctrl(x1531) // RegRead(x1449)
    val x1461 = Counter(min=Const(0), max=x1460, step=Const(32), par=1).name("x1461").ctrl(x1531) // CounterNew(Const(0),x1460,Const(32),Const(1))
    val x1462 = CounterChain(List(x1461)).name("x1462").ctrl(x1531) // CounterChainNew(List(x1461))
    val x1527 = LoopController(style=MetaPipe, level=OuterControl, cchain=x1462).name("x1527").ctrl(x1531) // UnrolledReduce(List(Const(true)),x1462,x1459,Block((x1459) => Const(())),List(List(b926)),List(List(b927)))
    val b926 = CounterIter(x1461, Some(0)).ctrl(x1527).name("b926")
    val b927 = DummyOp().ctrl(x1527).name("b927")
    val x1463_d0_b0 = SRAM(size=2, banking=Strided(banks=16, stride=1)).name("x1463_d0_b0").ctrl(x1527) // x1463 = SRAMNew(ArrayBuffer(Const(32)))
    isAccum(x1463_d0_b0) = false
    bufferDepthOf(x1463_d0_b0) = 2
    val x1464_d0_b0 = SRAM(size=2, banking=Strided(banks=16, stride=1)).name("x1464_d0_b0").ctrl(x1527) // x1464 = SRAMNew(ArrayBuffer(Const(32)))
    isAccum(x1464_d0_b0) = false
    bufferDepthOf(x1464_d0_b0) = 2
    val x1466 = UnitController(style=SeqPipe, level=InnerControl).name("x1466").ctrl(x1527) // UnitPipe(List(b927),Block(Const(())))
    val x1465 = OpDef(op=FixAdd, inputs=List(b926, Const(32))).name("x1465").ctrl(x1466) // FixAdd(b926,Const(32))
    val x1486 = UnitController(style=StreamPipe, level=OuterControl).name("x1486").ctrl(x1527) // UnitPipe(List(b927),Block(Const(())))
    val b1550 = StreamOut(field="offset").name("b1550").ctrl(x1486) // x1467 = StreamOutNew(BurstCmdBus)
    val b1551 = StreamOut(field="size").name("b1551").ctrl(x1486) // x1467 = StreamOutNew(BurstCmdBus)
    val x1468 = StreamIn(field="data").name("x1468").ctrl(x1486) // x1468 = StreamInNew(BurstDataBus())
    val x1477 = UnitController(style=SeqPipe, level=InnerControl).name("x1477").ctrl(x1486) // UnitPipe(List(b927),Block(x1476))
    val x1469 = b926 // FixConvert(b926,TRUE,_32,_0)
    val x1470 = OpDef(op=FixSla, inputs=List(x1469, Const(2))).name("x1470").ctrl(x1477) // FixLsh(x1469,Const(2))
    val x1471 = x1470 // FixConvert(x1470,TRUE,_64,_0)
    val x1472 = top.argFringe.dramAddress(x1453).name("x1472").ctrl(x1477) // GetDRAMAddress(x1453)
    val x1473 = OpDef(op=FixAdd, inputs=List(x1471, x1472)).name("x1473").ctrl(x1477) // FixAdd(x1471,x1472)
    val x1475_x1474 = x1473 // FixConvert(x1473,TRUE,_64,_0)
    // x1475 = SimpleStruct(ArrayBuffer((offset,x1474), (size,Const(128)), (isLoad,Const(true))))
    val b1552_b1550 = WriteMem(b1550, x1475_x1474).name("b1552_b1550").ctrl(x1477) // StreamWrite(x1467,x1475,b927)
    val b1553_b1551 = WriteMem(b1551, Const(128)).name("b1553_b1551").ctrl(x1477) // StreamWrite(x1467,x1475,b927)
    val x1478 = FringeDenseLoad(dram=List(x1453), cmdStream=List(b1550, b1551), dataStream=List(x1468)).name("x1478").ctrl(x1486) // FringeDenseLoad(x1453,x1467,x1468)
    val x1479 = Counter(min=Const(0), max=Const(32), step=Const(1), par=16).name("x1479").ctrl(x1486) // CounterNew(Const(0),Const(32),Const(1),Const(16))
    val x1480 = CounterChain(List(x1479)).name("x1480").ctrl(x1486) // CounterChainNew(List(x1479))
    val x1485 = LoopController(style=InnerPipe, level=InnerControl, cchain=x1480).name("x1485").ctrl(x1486) // UnrolledForeach(List(b927),x1480,Block(Const(())),List(List(b946)),List(List(b947)))
    val b946 = CounterIter(x1479, None).ctrl(x1485).name("b946")
    val b947 = DummyOp().ctrl(x1485).name("b947")
    val x1481 = OpDef(op=BitAnd, inputs=List(b947, b927)).name("x1481").ctrl(x1485) // And(b947,b927)
    val x1482_x1482 = ReadMem(x1468).name("x1482").ctrl(x1485) // ParStreamRead(x1468,List(x1481))
    val x1483_x1483 = x1482_x1482 // x1483 = VectorApply(x1482,0)
    val x1484 = StoreBanks(List(x1463_d0_b0), List(b946), x1483_x1483).name("x1484").ctrl(x1485) // ParSRAMStore(x1463,List(List(b946)),List(x1483),List(x1481))
    val x1506 = UnitController(style=StreamPipe, level=OuterControl).name("x1506").ctrl(x1527) // UnitPipe(List(b927),Block(Const(())))
    val b1554 = StreamOut(field="offset").name("b1554").ctrl(x1506) // x1487 = StreamOutNew(BurstCmdBus)
    val b1555 = StreamOut(field="size").name("b1555").ctrl(x1506) // x1487 = StreamOutNew(BurstCmdBus)
    val x1488 = StreamIn(field="data").name("x1488").ctrl(x1506) // x1488 = StreamInNew(BurstDataBus())
    val x1497 = UnitController(style=SeqPipe, level=InnerControl).name("x1497").ctrl(x1506) // UnitPipe(List(b927),Block(x1496))
    val x1489 = b926 // FixConvert(b926,TRUE,_32,_0)
    val x1490 = OpDef(op=FixSla, inputs=List(x1489, Const(2))).name("x1490").ctrl(x1497) // FixLsh(x1489,Const(2))
    val x1491 = x1490 // FixConvert(x1490,TRUE,_64,_0)
    val x1492 = top.argFringe.dramAddress(x1455).name("x1492").ctrl(x1497) // GetDRAMAddress(x1455)
    val x1493 = OpDef(op=FixAdd, inputs=List(x1491, x1492)).name("x1493").ctrl(x1497) // FixAdd(x1491,x1492)
    val x1495_x1494 = x1493 // FixConvert(x1493,TRUE,_64,_0)
    // x1495 = SimpleStruct(ArrayBuffer((offset,x1494), (size,Const(128)), (isLoad,Const(true))))
    val b1556_b1554 = WriteMem(b1554, x1495_x1494).name("b1556_b1554").ctrl(x1497) // StreamWrite(x1487,x1495,b927)
    val b1557_b1555 = WriteMem(b1555, Const(128)).name("b1557_b1555").ctrl(x1497) // StreamWrite(x1487,x1495,b927)
    val x1498 = FringeDenseLoad(dram=List(x1455), cmdStream=List(b1554, b1555), dataStream=List(x1488)).name("x1498").ctrl(x1506) // FringeDenseLoad(x1455,x1487,x1488)
    val x1499 = Counter(min=Const(0), max=Const(32), step=Const(1), par=16).name("x1499").ctrl(x1506) // CounterNew(Const(0),Const(32),Const(1),Const(16))
    val x1500 = CounterChain(List(x1499)).name("x1500").ctrl(x1506) // CounterChainNew(List(x1499))
    val x1505 = LoopController(style=InnerPipe, level=InnerControl, cchain=x1500).name("x1505").ctrl(x1506) // UnrolledForeach(List(b927),x1500,Block(Const(())),List(List(b968)),List(List(b969)))
    val b968 = CounterIter(x1499, None).ctrl(x1505).name("b968")
    val b969 = DummyOp().ctrl(x1505).name("b969")
    val x1501 = OpDef(op=BitAnd, inputs=List(b969, b927)).name("x1501").ctrl(x1505) // And(b969,b927)
    val x1502_x1502 = ReadMem(x1488).name("x1502").ctrl(x1505) // ParStreamRead(x1488,List(x1501))
    val x1503_x1503 = x1502_x1502 // x1503 = VectorApply(x1502,0)
    val x1504 = StoreBanks(List(x1464_d0_b0), List(b968), x1503_x1503).name("x1504").ctrl(x1505) // ParSRAMStore(x1464,List(List(b968)),List(x1503),List(x1501))
    val x1507_d0 = Reg(init=Some(0)).name("x1507_d0").ctrl(x1527) // x1507 = RegNew(Const(0))
    isAccum(x1507_d0) = false
    bufferDepthOf(x1507_d0) = 2
    val x1507_d1 = Reg(init=Some(0)).name("x1507_d1").ctrl(x1527) // x1507 = RegNew(Const(0))
    isAccum(x1507_d1) = true
    bufferDepthOf(x1507_d1) = 1
    val x1508 = Counter(min=Const(0), max=Const(32), step=Const(1), par=16).name("x1508").ctrl(x1527) // CounterNew(Const(0),Const(32),Const(1),Const(16))
    val x1509 = CounterChain(List(x1508)).name("x1509").ctrl(x1527) // CounterChainNew(List(x1508))
    val x1520 = LoopController(style=InnerPipe, level=InnerControl, cchain=x1509).name("x1520").ctrl(x1527) // UnrolledReduce(List(b927),x1509,x1507,Block((x1507) => Const(())),List(List(b980)),List(List(b981)))
    val b980 = CounterIter(x1508, None).ctrl(x1520).name("b980")
    val b981 = DummyOp().ctrl(x1520).name("b981")
    val x1510 = OpDef(op=BitAnd, inputs=List(b981, b927)).name("x1510").ctrl(x1520) // And(b981,b927)
    val x1511 = LoadBanks(List(x1463_d0_b0), List(b980)).name("x1511").ctrl(x1520) // ParSRAMLoad(x1463,List(List(b980)),List(x1510))
    val x1512 = x1511 // x1512 = VectorApply(x1511,0)
    val x1513 = LoadBanks(List(x1464_d0_b0), List(b980)).name("x1513").ctrl(x1520) // ParSRAMLoad(x1464,List(List(b980)),List(x1510))
    val x1514 = x1513 // x1514 = VectorApply(x1513,0)
    val x1515 = OpDef(op=FixMul, inputs=List(x1512, x1514)).name("x1515").ctrl(x1520) // FixMul(x1512,x1514)
    val x1516 = ReadMem(x1507_d1).name("x1516").ctrl(x1520) // RegRead(x1507)
    val x1517 = OpDef(op=FixEql, inputs=List(b980, Const(0))).name("x1517").ctrl(x1520) // FixEql(b980,Const(0))
    val x1518 = ReduceAccumOp(op=FixAdd, input=x1515, accum=x1516).name("x1518").ctrl(x1520) // FixAdd(x1515,x1516)
    val x1519_x1507_d0 = WriteMem(x1507_d0, x1518).name("x1519_x1507_d0").ctrl(x1520) // RegWrite(x1507,x1518,b927)
    val x1519_x1507_d1 = WriteMem(x1507_d1, x1518).name("x1519_x1507_d1").ctrl(x1520) // RegWrite(x1507,x1518,b927)
    val x1526 = UnitController(style=SeqPipe, level=InnerControl).name("x1526").ctrl(x1527) // UnitPipe(List(Const(true)),Block(x1525))
    val x1521 = ReadMem(x1459_d1).name("x1521").ctrl(x1526) // RegRead(x1459)
    val x1522 = OpDef(op=FixEql, inputs=List(b926, Const(0))).name("x1522").ctrl(x1526) // FixEql(b926,Const(0))
    val x1523 = ReadMem(x1507_d0).name("x1523").ctrl(x1526) // RegRead(x1507)
    val x1524 = ReduceAccumOp(op=FixAdd, input=x1523, accum=x1521).name("x1524").ctrl(x1526) // FixAdd(x1523,x1521)
    val x1525_x1459_d0 = WriteMem(x1459_d0, x1524).name("x1525_x1459_d0").ctrl(x1526) // RegWrite(x1459,x1524,Const(true))
    val x1525_x1459_d1 = WriteMem(x1459_d1, x1524).name("x1525_x1459_d1").ctrl(x1526) // RegWrite(x1459,x1524,Const(true))
    val x1530 = UnitController(style=SeqPipe, level=InnerControl).name("x1530").ctrl(x1531) // UnitPipe(List(Const(true)),Block(Const(())))
    val x1528 = ReadMem(x1459_d0).name("x1528").ctrl(x1530) // RegRead(x1459)
    val x1529_x1456 = WriteMem(x1456, x1528).name("x1529_x1456").ctrl(x1530) // RegWrite(x1456,x1528,Const(true))
    
  }
}
