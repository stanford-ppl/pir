import pir._
import pir.node._
import arch._
import prism.enums._

object DotProduct extends PIRApp {
  def main(implicit design:PIRDesign) = {
    import design.pirmeta._
    val x1387 = ArgIn(init=0).name("x1387").ctrl(top) // ArgInNew(Const(0))
    boundOf(x1387) = 1024
    val x1390 = ReadMem(x1387).name("x1390").ctrl(top) // RegRead(x1387)
    val x1391 = DRAM().name("x1391").ctrl(top) // x1391 = DRAMNew(ArrayBuffer(x1390),Const(0))
    val x1392 = ReadMem(x1387).name("x1392").ctrl(top) // RegRead(x1387)
    val x1393 = DRAM().name("x1393").ctrl(top) // x1393 = DRAMNew(ArrayBuffer(x1392),Const(0))
    val x1394 = ArgOut(init=0).name("x1394").ctrl(top) // ArgOutNew(Const(0))
    val x1469 = UnitController(style=SeqPipe, level=OuterControl).name("x1469").ctrl(top) // Hwblock(Block(Const(())),false)
    val x1397_d0 = Reg(init=Some(0)).name("x1397_d0").ctrl(x1469) // x1397 = RegNew(Const(0))
    isAccum(x1397_d0) = false
    bufferDepthOf(x1397_d0) = 1
    val x1397_d1 = Reg(init=Some(0)).name("x1397_d1").ctrl(x1469) // x1397 = RegNew(Const(0))
    isAccum(x1397_d1) = true
    bufferDepthOf(x1397_d1) = 1
    val x1398 = ReadMem(x1387).name("x1398").ctrl(x1469) // RegRead(x1387)
    val x1399 = Counter(min=Const(0), max=x1398, step=Const(32), par=1).name("x1399").ctrl(x1469) // CounterNew(Const(0),x1398,Const(32),Const(1))
    val x1400 = CounterChain(List(x1399)).name("x1400").ctrl(x1469) // CounterChainNew(List(x1399))
    val x1465 = LoopController(style=MetaPipe, level=OuterControl, cchain=x1400).name("x1465").ctrl(x1469) // UnrolledReduce(List(Const(true)),x1400,x1397,Block((x1397) => Const(())),List(List(b864)),List(List(b865)))
    val b864 = CounterIter(x1399, Some(0)).ctrl(x1465).name("b864")
    val b865 = DummyOp().ctrl(x1465).name("b865")
    val x1401_d0_b0 = SRAM(size=2, banking=Strided(banks=16, stride=1)).name("x1401_d0_b0").ctrl(x1465) // x1401 = SRAMNew(ArrayBuffer(Const(32)))
    isAccum(x1401_d0_b0) = false
    bufferDepthOf(x1401_d0_b0) = 2
    val x1402_d0_b0 = SRAM(size=2, banking=Strided(banks=16, stride=1)).name("x1402_d0_b0").ctrl(x1465) // x1402 = SRAMNew(ArrayBuffer(Const(32)))
    isAccum(x1402_d0_b0) = false
    bufferDepthOf(x1402_d0_b0) = 2
    val x1404 = UnitController(style=SeqPipe, level=InnerControl).name("x1404").ctrl(x1465) // UnitPipe(List(b865),Block(Const(())))
    val x1403 = OpDef(op=FixAdd, inputs=List(b864, Const(32))).name("x1403").ctrl(x1404) // FixAdd(b864,Const(32))
    val x1424 = UnitController(style=StreamPipe, level=OuterControl).name("x1424").ctrl(x1465) // UnitPipe(List(b865),Block(Const(())))
    val b1488 = StreamOut(field="offset").name("b1488").ctrl(x1424) // x1405 = StreamOutNew(BurstCmdBus)
    val b1489 = StreamOut(field="size").name("b1489").ctrl(x1424) // x1405 = StreamOutNew(BurstCmdBus)
    val x1406 = StreamIn(field="data").name("x1406").ctrl(x1424) // x1406 = StreamInNew(BurstDataBus())
    val x1415 = UnitController(style=SeqPipe, level=InnerControl).name("x1415").ctrl(x1424) // UnitPipe(List(b865),Block(x1414))
    val x1407 = b864 // FixConvert(b864,TRUE,_32,_0)
    val x1408 = OpDef(op=FixSla, inputs=List(x1407, Const(2))).name("x1408").ctrl(x1415) // FixLsh(x1407,Const(2))
    val x1409 = x1408 // FixConvert(x1408,TRUE,_64,_0)
    val x1410 = DramAddress(x1391).name("x1410").ctrl(x1415) // GetDRAMAddress(x1391)
    val x1411 = OpDef(op=FixAdd, inputs=List(x1409, x1410)).name("x1411").ctrl(x1415) // FixAdd(x1409,x1410)
    val x1413_x1412 = x1411 // FixConvert(x1411,TRUE,_64,_0)
    // x1413 = SimpleStruct(ArrayBuffer((offset,x1412), (size,Const(128)), (isLoad,Const(true))))
    val b1490_b1488 = WriteMem(b1488, x1413_x1412).name("b1490_b1488").ctrl(x1415) // StreamWrite(x1405,x1413,b865)
    val b1491_b1489 = WriteMem(b1489, Const(128)).name("b1491_b1489").ctrl(x1415) // StreamWrite(x1405,x1413,b865)
    val x1416 = FringeDenseLoad(dram=List(x1391), cmdStream=List(b1488, b1489), dataStream=List(x1406)).name("x1416").ctrl(x1424) // FringeDenseLoad(x1391,x1405,x1406)
    val x1417 = Counter(min=Const(0), max=Const(32), step=Const(1), par=16).name("x1417").ctrl(x1424) // CounterNew(Const(0),Const(32),Const(1),Const(16))
    val x1418 = CounterChain(List(x1417)).name("x1418").ctrl(x1424) // CounterChainNew(List(x1417))
    val x1423 = LoopController(style=InnerPipe, level=InnerControl, cchain=x1418).name("x1423").ctrl(x1424) // UnrolledForeach(List(b865),x1418,Block(Const(())),List(List(b884)),List(List(b885)))
    val b884 = CounterIter(x1417, None).ctrl(x1423).name("b884")
    val b885 = DummyOp().ctrl(x1423).name("b885")
    val x1419 = OpDef(op=BitAnd, inputs=List(b885, b865)).name("x1419").ctrl(x1423) // And(b885,b865)
    val x1420_x1420 = ReadMem(x1406).name("x1420").ctrl(x1423) // ParStreamRead(x1406,List(x1419))
    val x1421_x1421 = x1420_x1420 // x1421 = VectorApply(x1420,0)
    val x1422 = StoreBanks(List(x1401_d0_b0), List(b884), x1421_x1421).name("x1422").ctrl(x1423) // ParSRAMStore(x1401,List(List(b884)),List(x1421),List(x1419))
    val x1444 = UnitController(style=StreamPipe, level=OuterControl).name("x1444").ctrl(x1465) // UnitPipe(List(b865),Block(Const(())))
    val b1492 = StreamOut(field="offset").name("b1492").ctrl(x1444) // x1425 = StreamOutNew(BurstCmdBus)
    val b1493 = StreamOut(field="size").name("b1493").ctrl(x1444) // x1425 = StreamOutNew(BurstCmdBus)
    val x1426 = StreamIn(field="data").name("x1426").ctrl(x1444) // x1426 = StreamInNew(BurstDataBus())
    val x1435 = UnitController(style=SeqPipe, level=InnerControl).name("x1435").ctrl(x1444) // UnitPipe(List(b865),Block(x1434))
    val x1427 = b864 // FixConvert(b864,TRUE,_32,_0)
    val x1428 = OpDef(op=FixSla, inputs=List(x1427, Const(2))).name("x1428").ctrl(x1435) // FixLsh(x1427,Const(2))
    val x1429 = x1428 // FixConvert(x1428,TRUE,_64,_0)
    val x1430 = DramAddress(x1393).name("x1430").ctrl(x1435) // GetDRAMAddress(x1393)
    val x1431 = OpDef(op=FixAdd, inputs=List(x1429, x1430)).name("x1431").ctrl(x1435) // FixAdd(x1429,x1430)
    val x1433_x1432 = x1431 // FixConvert(x1431,TRUE,_64,_0)
    // x1433 = SimpleStruct(ArrayBuffer((offset,x1432), (size,Const(128)), (isLoad,Const(true))))
    val b1494_b1492 = WriteMem(b1492, x1433_x1432).name("b1494_b1492").ctrl(x1435) // StreamWrite(x1425,x1433,b865)
    val b1495_b1493 = WriteMem(b1493, Const(128)).name("b1495_b1493").ctrl(x1435) // StreamWrite(x1425,x1433,b865)
    val x1436 = FringeDenseLoad(dram=List(x1393), cmdStream=List(b1492, b1493), dataStream=List(x1426)).name("x1436").ctrl(x1444) // FringeDenseLoad(x1393,x1425,x1426)
    val x1437 = Counter(min=Const(0), max=Const(32), step=Const(1), par=16).name("x1437").ctrl(x1444) // CounterNew(Const(0),Const(32),Const(1),Const(16))
    val x1438 = CounterChain(List(x1437)).name("x1438").ctrl(x1444) // CounterChainNew(List(x1437))
    val x1443 = LoopController(style=InnerPipe, level=InnerControl, cchain=x1438).name("x1443").ctrl(x1444) // UnrolledForeach(List(b865),x1438,Block(Const(())),List(List(b906)),List(List(b907)))
    val b906 = CounterIter(x1437, None).ctrl(x1443).name("b906")
    val b907 = DummyOp().ctrl(x1443).name("b907")
    val x1439 = OpDef(op=BitAnd, inputs=List(b907, b865)).name("x1439").ctrl(x1443) // And(b907,b865)
    val x1440_x1440 = ReadMem(x1426).name("x1440").ctrl(x1443) // ParStreamRead(x1426,List(x1439))
    val x1441_x1441 = x1440_x1440 // x1441 = VectorApply(x1440,0)
    val x1442 = StoreBanks(List(x1402_d0_b0), List(b906), x1441_x1441).name("x1442").ctrl(x1443) // ParSRAMStore(x1402,List(List(b906)),List(x1441),List(x1439))
    val x1445_d0 = Reg(init=Some(0)).name("x1445_d0").ctrl(x1465) // x1445 = RegNew(Const(0))
    isAccum(x1445_d0) = false
    bufferDepthOf(x1445_d0) = 2
    val x1445_d1 = Reg(init=Some(0)).name("x1445_d1").ctrl(x1465) // x1445 = RegNew(Const(0))
    isAccum(x1445_d1) = true
    bufferDepthOf(x1445_d1) = 1
    val x1446 = Counter(min=Const(0), max=Const(32), step=Const(1), par=16).name("x1446").ctrl(x1465) // CounterNew(Const(0),Const(32),Const(1),Const(16))
    val x1447 = CounterChain(List(x1446)).name("x1447").ctrl(x1465) // CounterChainNew(List(x1446))
    val x1458 = LoopController(style=InnerPipe, level=InnerControl, cchain=x1447).name("x1458").ctrl(x1465) // UnrolledReduce(List(b865),x1447,x1445,Block((x1445) => Const(())),List(List(b918)),List(List(b919)))
    val b918 = CounterIter(x1446, None).ctrl(x1458).name("b918")
    val b919 = DummyOp().ctrl(x1458).name("b919")
    val x1448 = OpDef(op=BitAnd, inputs=List(b919, b865)).name("x1448").ctrl(x1458) // And(b919,b865)
    val x1449 = LoadBanks(List(x1401_d0_b0), List(b918)).name("x1449").ctrl(x1458) // ParSRAMLoad(x1401,List(List(b918)),List(x1448))
    val x1450 = x1449 // x1450 = VectorApply(x1449,0)
    val x1451 = LoadBanks(List(x1402_d0_b0), List(b918)).name("x1451").ctrl(x1458) // ParSRAMLoad(x1402,List(List(b918)),List(x1448))
    val x1452 = x1451 // x1452 = VectorApply(x1451,0)
    val x1453 = OpDef(op=FixMul, inputs=List(x1450, x1452)).name("x1453").ctrl(x1458) // FixMul(x1450,x1452)
    val x1454 = ReadMem(x1445_d1).name("x1454").ctrl(x1458) // RegRead(x1445)
    val x1455 = OpDef(op=FixEql, inputs=List(b918, Const(0))).name("x1455").ctrl(x1458) // FixEql(b918,Const(0))
    val x1456 = ReduceAccumOp(op=FixAdd, input=x1453, accum=x1454).name("x1456").ctrl(x1458) // FixAdd(x1453,x1454)
    val x1457_x1445_d0 = WriteMem(x1445_d0, x1456).name("x1457_x1445_d0").ctrl(x1458) // RegWrite(x1445,x1456,b865)
    val x1457_x1445_d1 = WriteMem(x1445_d1, x1456).name("x1457_x1445_d1").ctrl(x1458) // RegWrite(x1445,x1456,b865)
    val x1464 = UnitController(style=SeqPipe, level=InnerControl).name("x1464").ctrl(x1465) // UnitPipe(List(Const(true)),Block(x1463))
    val x1459 = ReadMem(x1397_d1).name("x1459").ctrl(x1464) // RegRead(x1397)
    val x1460 = OpDef(op=FixEql, inputs=List(b864, Const(0))).name("x1460").ctrl(x1464) // FixEql(b864,Const(0))
    val x1461 = ReadMem(x1445_d0).name("x1461").ctrl(x1464) // RegRead(x1445)
    val x1462 = ReduceAccumOp(op=FixAdd, input=x1461, accum=x1459).name("x1462").ctrl(x1464) // FixAdd(x1461,x1459)
    val x1463_x1397_d0 = WriteMem(x1397_d0, x1462).name("x1463_x1397_d0").ctrl(x1464) // RegWrite(x1397,x1462,Const(true))
    val x1463_x1397_d1 = WriteMem(x1397_d1, x1462).name("x1463_x1397_d1").ctrl(x1464) // RegWrite(x1397,x1462,Const(true))
    val x1468 = UnitController(style=SeqPipe, level=InnerControl).name("x1468").ctrl(x1469) // UnitPipe(List(Const(true)),Block(Const(())))
    val x1466 = ReadMem(x1397_d0).name("x1466").ctrl(x1468) // RegRead(x1397)
    val x1467_x1394 = WriteMem(x1394, x1466).name("x1467_x1394").ctrl(x1468) // RegWrite(x1394,x1466,Const(true))
    
  }
}
