import pir._
import pir.node._
import arch._
import prism.enums._

object DotProduct extends PIRApp {
  def main(implicit design:PIRDesign) = {
    import design.pirmeta._
    val x1369 = ArgIn(init=0).name("x1369").ctrl(top).srcCtx("DotProduct.scala:24:21") // ArgInNew(Const(0))
    isAccum(x1369) = false
    bufferDepthOf(x1369) = 1
    boundOf(x1369) = 1024
    val x1372 = ReadMem(x1369).name("x1372").ctrl(top).srcCtx("DotProduct.scala:27:21") // RegRead(x1369)
    val x1373 = DRAM().name("x1373").ctrl(top).srcCtx("DotProduct.scala:27:20") // x1373 = DRAMNew(ArrayBuffer(x1372),Const(0))
    val x1374 = ReadMem(x1369).name("x1374").ctrl(top).srcCtx("DotProduct.scala:28:21") // RegRead(x1369)
    val x1375 = DRAM().name("x1375").ctrl(top).srcCtx("DotProduct.scala:28:20") // x1375 = DRAMNew(ArrayBuffer(x1374),Const(0))
    val x1376 = ArgOut(init=0).name("x1376").ctrl(top).srcCtx("DotProduct.scala:29:21") // ArgOutNew(Const(0))
    isAccum(x1376) = false
    bufferDepthOf(x1376) = 1
    val x1449 = UnitController(style=SeqPipe, level=OuterControl).name("x1449").ctrl(top).srcCtx("DotProduct.scala:33:11") // Hwblock(Block(Const(())),false)
    val x1379_d0 = Reg(init=Some(0)).name("x1379_d0").ctrl(x1449).srcCtx("DotProduct.scala:34:27") // x1379 = RegNew(Const(0))
    isAccum(x1379_d0) = false
    bufferDepthOf(x1379_d0) = 1
    val x1379_d1 = Reg(init=Some(0)).name("x1379_d1").ctrl(x1449).srcCtx("DotProduct.scala:34:27") // x1379 = RegNew(Const(0))
    isAccum(x1379_d1) = true
    bufferDepthOf(x1379_d1) = 1
    val x1380 = ReadMem(x1369).name("x1380").ctrl(x1449).srcCtx("DotProduct.scala:34:38") // RegRead(x1369)
    val x1381 = Counter(min=Const(0), max=x1380, step=Const(32), par=1).name("x1381").ctrl(x1449).srcCtx("DotProduct.scala:34:48") // CounterNew(Const(0),x1380,Const(32),Const(1))
    val x1382 = CounterChain(List(x1381)).name("x1382").ctrl(x1449).srcCtx("DotProduct.scala:46:8") // CounterChainNew(List(x1381))
    val x1445 = LoopController(style=MetaPipe, level=OuterControl, cchain=x1382).name("x1445").ctrl(x1449).srcCtx("DotProduct.scala:46:8") // UnrolledReduce(List(Const(true)),x1382,x1379,Block((x1379) => Const(())),List(List(b856)),List(List(b857)))
    val b856 = CounterIter(x1381, Some(0)).name("b856").ctrl(x1445) // b856
    val b857 = Const(true).name("b857").ctrl(x1445) // b857
    val x1383_d0_b0 = SRAM(size=2, banking=Strided(banks=16, stride=1)).name("x1383_d0_b0").ctrl(x1445).srcCtx("DotProduct.scala:37:27") // x1383 = SRAMNew(ArrayBuffer(Const(32)))
    isAccum(x1383_d0_b0) = false
    bufferDepthOf(x1383_d0_b0) = 2
    val x1384_d0_b0 = SRAM(size=2, banking=Strided(banks=16, stride=1)).name("x1384_d0_b0").ctrl(x1445).srcCtx("DotProduct.scala:38:27") // x1384 = SRAMNew(ArrayBuffer(Const(32)))
    isAccum(x1384_d0_b0) = false
    bufferDepthOf(x1384_d0_b0) = 2
    val x1386 = UnitController(style=SeqPipe, level=InnerControl).name("x1386").ctrl(x1445).srcCtx("DotProduct.scala:39:18") // UnitPipe(List(b857),Block(Const(())))
    val x1385 = OpDef(op=FixAdd, inputs=List(b856, Const(32))).name("x1385").ctrl(x1386).srcCtx("DotProduct.scala:42:27") // FixAdd(b856,Const(32))
    val x1405 = UnitController(style=StreamPipe, level=OuterControl).name("x1405").ctrl(x1445).srcCtx("DotProduct.scala:42:16") // UnitPipe(List(b857),Block(Const(())))
    val b1468 = StreamOut(field="offset").name("b1468").ctrl(x1405).srcCtx("DotProduct.scala:42:16") // x1387 = StreamOutNew(BurstCmdBus)
    isAccum(b1468) = false
    bufferDepthOf(b1468) = 1
    val b1469 = StreamOut(field="size").name("b1469").ctrl(x1405).srcCtx("DotProduct.scala:42:16") // x1387 = StreamOutNew(BurstCmdBus)
    isAccum(b1469) = false
    bufferDepthOf(b1469) = 1
    val x1388 = StreamIn(field="data").name("x1388").ctrl(x1405).srcCtx("DotProduct.scala:42:16") // x1388 = StreamInNew(BurstDataBus())
    isAccum(x1388) = false
    bufferDepthOf(x1388) = 1
    val x1396 = UnitController(style=SeqPipe, level=InnerControl).name("x1396").ctrl(x1405).srcCtx("DotProduct.scala:42:16") // UnitPipe(List(b857),Block(x1395))
    val x1389 = b856 // FixConvert(b856,TRUE,_32,_0) (Same Type. No op)
    val x1390 = OpDef(op=FixSla, inputs=List(x1389, Const(2))).name("x1390").ctrl(x1396).srcCtx("DotProduct.scala:42:16") // FixLsh(x1389,Const(2))
    val x1391 = x1390 // FixConvert(x1390,TRUE,_64,_0)
    val x1392 = DramAddress(x1373).name("x1392").ctrl(x1396).srcCtx("DotProduct.scala:42:16") // GetDRAMAddress(x1373)
    val x1394_x1393 = OpDef(op=FixAdd, inputs=List(x1391, x1392)).name("x1394_x1393").ctrl(x1396).srcCtx("DotProduct.scala:42:16") // FixAdd(x1391,x1392)
    // x1394 = SimpleStruct(ArrayBuffer((offset,x1393), (size,Const(128)), (isLoad,Const(true))))
    val x1395_b1470_b1468 = WriteMem(b1468, x1394_x1393).name("x1395_b1470_b1468").ctrl(x1396).srcCtx("DotProduct.scala:42:16") // StreamWrite(x1387,x1394,b857)
    val x1395_b1471_b1469 = WriteMem(b1469, Const(128)).name("x1395_b1471_b1469").ctrl(x1396).srcCtx("DotProduct.scala:42:16") // StreamWrite(x1387,x1394,b857)
    val x1397 = FringeDenseLoad(dram=List(x1373), cmdStream=List(b1468, b1469), dataStream=List(x1388)).name("x1397").ctrl(x1405).srcCtx("DotProduct.scala:42:16") // FringeDenseLoad(x1373,x1387,x1388)
    val x1398 = Counter(min=Const(0), max=Const(32), step=Const(1), par=16).name("x1398").ctrl(x1405).srcCtx("DotProduct.scala:42:16") // CounterNew(Const(0),Const(32),Const(1),Const(16))
    val x1399 = CounterChain(List(x1398)).name("x1399").ctrl(x1405).srcCtx("DotProduct.scala:42:16") // CounterChainNew(List(x1398))
    val x1404 = LoopController(style=InnerPipe, level=InnerControl, cchain=x1399).name("x1404").ctrl(x1405).srcCtx("DotProduct.scala:42:16") // UnrolledForeach(List(b857),x1399,Block(Const(())),List(List(b875)),List(List(b876)))
    val b875 = CounterIter(x1398, None).name("b875").ctrl(x1404) // b875
    val b876 = Const(true).name("b876").ctrl(x1404) // b876
    val x1400 = OpDef(op=BitAnd, inputs=List(b876, b857)).name("x1400").ctrl(x1404).srcCtx("UnrollingBase.scala:28:66") // And(b876,b857)
    val x1401_x1401 = ReadMem(x1388).name("x1401_x1401").ctrl(x1404).srcCtx("DotProduct.scala:42:16") // ParStreamRead(x1388,List(x1400))
    val x1402_x1402 = x1401_x1401 // x1402 = VectorApply(x1401,0)
    val x1403 = StoreBanks(List(x1383_d0_b0), List(b875), x1402_x1402).name("x1403").ctrl(x1404).srcCtx("DotProduct.scala:42:16") // ParSRAMStore(x1383,List(List(b875)),List(x1402),List(x1400))
    val x1424 = UnitController(style=StreamPipe, level=OuterControl).name("x1424").ctrl(x1445).srcCtx("DotProduct.scala:43:16") // UnitPipe(List(b857),Block(Const(())))
    val b1472 = StreamOut(field="offset").name("b1472").ctrl(x1424).srcCtx("DotProduct.scala:43:16") // x1406 = StreamOutNew(BurstCmdBus)
    isAccum(b1472) = false
    bufferDepthOf(b1472) = 1
    val b1473 = StreamOut(field="size").name("b1473").ctrl(x1424).srcCtx("DotProduct.scala:43:16") // x1406 = StreamOutNew(BurstCmdBus)
    isAccum(b1473) = false
    bufferDepthOf(b1473) = 1
    val x1407 = StreamIn(field="data").name("x1407").ctrl(x1424).srcCtx("DotProduct.scala:43:16") // x1407 = StreamInNew(BurstDataBus())
    isAccum(x1407) = false
    bufferDepthOf(x1407) = 1
    val x1415 = UnitController(style=SeqPipe, level=InnerControl).name("x1415").ctrl(x1424).srcCtx("DotProduct.scala:43:16") // UnitPipe(List(b857),Block(x1414))
    val x1408 = b856 // FixConvert(b856,TRUE,_32,_0) (Same Type. No op)
    val x1409 = OpDef(op=FixSla, inputs=List(x1408, Const(2))).name("x1409").ctrl(x1415).srcCtx("DotProduct.scala:43:16") // FixLsh(x1408,Const(2))
    val x1410 = x1409 // FixConvert(x1409,TRUE,_64,_0)
    val x1411 = DramAddress(x1375).name("x1411").ctrl(x1415).srcCtx("DotProduct.scala:43:16") // GetDRAMAddress(x1375)
    val x1413_x1412 = OpDef(op=FixAdd, inputs=List(x1410, x1411)).name("x1413_x1412").ctrl(x1415).srcCtx("DotProduct.scala:43:16") // FixAdd(x1410,x1411)
    // x1413 = SimpleStruct(ArrayBuffer((offset,x1412), (size,Const(128)), (isLoad,Const(true))))
    val x1414_b1474_b1472 = WriteMem(b1472, x1413_x1412).name("x1414_b1474_b1472").ctrl(x1415).srcCtx("DotProduct.scala:43:16") // StreamWrite(x1406,x1413,b857)
    val x1414_b1475_b1473 = WriteMem(b1473, Const(128)).name("x1414_b1475_b1473").ctrl(x1415).srcCtx("DotProduct.scala:43:16") // StreamWrite(x1406,x1413,b857)
    val x1416 = FringeDenseLoad(dram=List(x1375), cmdStream=List(b1472, b1473), dataStream=List(x1407)).name("x1416").ctrl(x1424).srcCtx("DotProduct.scala:43:16") // FringeDenseLoad(x1375,x1406,x1407)
    val x1417 = Counter(min=Const(0), max=Const(32), step=Const(1), par=16).name("x1417").ctrl(x1424).srcCtx("DotProduct.scala:43:16") // CounterNew(Const(0),Const(32),Const(1),Const(16))
    val x1418 = CounterChain(List(x1417)).name("x1418").ctrl(x1424).srcCtx("DotProduct.scala:43:16") // CounterChainNew(List(x1417))
    val x1423 = LoopController(style=InnerPipe, level=InnerControl, cchain=x1418).name("x1423").ctrl(x1424).srcCtx("DotProduct.scala:43:16") // UnrolledForeach(List(b857),x1418,Block(Const(())),List(List(b896)),List(List(b897)))
    val b896 = CounterIter(x1417, None).name("b896").ctrl(x1423) // b896
    val b897 = Const(true).name("b897").ctrl(x1423) // b897
    val x1419 = OpDef(op=BitAnd, inputs=List(b897, b857)).name("x1419").ctrl(x1423).srcCtx("UnrollingBase.scala:28:66") // And(b897,b857)
    val x1420_x1420 = ReadMem(x1407).name("x1420_x1420").ctrl(x1423).srcCtx("DotProduct.scala:43:16") // ParStreamRead(x1407,List(x1419))
    val x1421_x1421 = x1420_x1420 // x1421 = VectorApply(x1420,0)
    val x1422 = StoreBanks(List(x1384_d0_b0), List(b896), x1421_x1421).name("x1422").ctrl(x1423).srcCtx("DotProduct.scala:43:16") // ParSRAMStore(x1384,List(List(b896)),List(x1421),List(x1419))
    val x1425_d0 = Reg(init=Some(0)).name("x1425_d0").ctrl(x1445).srcCtx("DotProduct.scala:45:22") // x1425 = RegNew(Const(0))
    isAccum(x1425_d0) = false
    bufferDepthOf(x1425_d0) = 2
    val x1425_d1 = Reg(init=Some(0)).name("x1425_d1").ctrl(x1445).srcCtx("DotProduct.scala:45:22") // x1425 = RegNew(Const(0))
    isAccum(x1425_d1) = true
    bufferDepthOf(x1425_d1) = 1
    val x1426 = Counter(min=Const(0), max=Const(32), step=Const(1), par=16).name("x1426").ctrl(x1445).srcCtx("DotProduct.scala:45:35") // CounterNew(Const(0),Const(32),Const(1),Const(16))
    val x1427 = CounterChain(List(x1426)).name("x1427").ctrl(x1445).srcCtx("DotProduct.scala:45:70") // CounterChainNew(List(x1426))
    val x1438 = LoopController(style=InnerPipe, level=InnerControl, cchain=x1427).name("x1438").ctrl(x1445).srcCtx("DotProduct.scala:45:70") // UnrolledReduce(List(b857),x1427,x1425,Block((x1425) => Const(())),List(List(b908)),List(List(b909)))
    val b908 = CounterIter(x1426, None).name("b908").ctrl(x1438) // b908
    val b909 = Const(true).name("b909").ctrl(x1438) // b909
    val x1428 = OpDef(op=BitAnd, inputs=List(b909, b857)).name("x1428").ctrl(x1438).srcCtx("UnrollingBase.scala:28:66") // And(b909,b857)
    val x1429 = LoadBanks(List(x1383_d0_b0), List(b908)).name("x1429").ctrl(x1438).srcCtx("DotProduct.scala:45:53") // ParSRAMLoad(x1383,List(List(b908)),List(x1428))
    val x1430 = x1429 // x1430 = VectorApply(x1429,0)
    val x1431 = LoadBanks(List(x1384_d0_b0), List(b908)).name("x1431").ctrl(x1438).srcCtx("DotProduct.scala:45:64") // ParSRAMLoad(x1384,List(List(b908)),List(x1428))
    val x1432 = x1431 // x1432 = VectorApply(x1431,0)
    val x1433 = OpDef(op=FixMul, inputs=List(x1430, x1432)).name("x1433").ctrl(x1438).srcCtx("DotProduct.scala:45:58") // FixMul(x1430,x1432)
    val x1434 = ReadMem(x1425_d1).name("x1434").ctrl(x1438).srcCtx("DotProduct.scala:45:70") // RegRead(x1425)
    val x1435 = OpDef(op=FixEql, inputs=List(b908, Const(0))).name("x1435").ctrl(x1438).srcCtx("DotProduct.scala:45:70") // FixEql(b908,Const(0))
    val x1436 = ReduceAccumOp(op=FixAdd, input=x1433, accum=x1434).name("x1436").ctrl(x1438).srcCtx("DotProduct.scala:45:72") // FixAdd(x1433,x1434)
    val x1437_x1425_d0 = WriteMem(x1425_d0, x1436).name("x1437_x1425_d0").ctrl(x1438).srcCtx("DotProduct.scala:45:70") // RegWrite(x1425,x1436,b857)
    val x1437_x1425_d1 = WriteMem(x1425_d1, x1436).name("x1437_x1425_d1").ctrl(x1438).srcCtx("DotProduct.scala:45:70") // RegWrite(x1425,x1436,b857)
    val x1444 = UnitController(style=SeqPipe, level=InnerControl).name("x1444").ctrl(x1445).srcCtx("DotProduct.scala:46:8") // UnitPipe(List(Const(true)),Block(x1443))
    val x1439 = ReadMem(x1379_d1).name("x1439").ctrl(x1444).srcCtx("DotProduct.scala:46:8") // RegRead(x1379)
    val x1440 = OpDef(op=FixEql, inputs=List(b856, Const(0))).name("x1440").ctrl(x1444).srcCtx("DotProduct.scala:46:8") // FixEql(b856,Const(0))
    val x1441 = ReadMem(x1425_d0).name("x1441").ctrl(x1444).srcCtx("DotProduct.scala:45:70") // RegRead(x1425)
    val x1442 = ReduceAccumOp(op=FixAdd, input=x1441, accum=x1439).name("x1442").ctrl(x1444).srcCtx("DotProduct.scala:46:10") // FixAdd(x1441,x1439)
    val x1443_x1379_d0 = WriteMem(x1379_d0, x1442).name("x1443_x1379_d0").ctrl(x1444).srcCtx("DotProduct.scala:46:8") // RegWrite(x1379,x1442,Const(true))
    val x1443_x1379_d1 = WriteMem(x1379_d1, x1442).name("x1443_x1379_d1").ctrl(x1444).srcCtx("DotProduct.scala:46:8") // RegWrite(x1379,x1442,Const(true))
    val x1448 = UnitController(style=SeqPipe, level=InnerControl).name("x1448").ctrl(x1449).srcCtx("DotProduct.scala:33:11") // UnitPipe(List(Const(true)),Block(Const(())))
    val x1446 = ReadMem(x1379_d0).name("x1446").ctrl(x1448).srcCtx("DotProduct.scala:46:8") // RegRead(x1379)
    val x1447_x1376 = WriteMem(x1376, x1446).name("x1447_x1376").ctrl(x1448).srcCtx("DotProduct.scala:34:11") // RegWrite(x1376,x1446,Const(true))
    
  }
}
