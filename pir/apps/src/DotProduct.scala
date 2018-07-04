import pir._
import pir.node._
import arch._
import prism.enums._

object DotProduct extends PIRApp {
  def main(implicit design:PIRDesign) = {
    import design.pirmeta._
    val x1370 = ArgIn(init=0).name("x1370").ctrl(top).srcCtx("DotProduct.scala:23:21:size") // ArgInNew(Const(0))
    isAccum(x1370) = false
    bufferDepthOf(x1370) = 1
    boundOf(x1370) = 1024
    val x1373 = ReadMem(x1370).name("x1373").ctrl(top).srcCtx("DotProduct.scala:26:21") // RegRead(x1370)
    val x1374 = DRAM(dims=List(x1373)).name("x1374").ctrl(top).srcCtx("DotProduct.scala:26:20:a") // x1374 = DRAMNew(ArrayBuffer(x1373),Const(0))
    val x1375 = ReadMem(x1370).name("x1375").ctrl(top).srcCtx("DotProduct.scala:27:21") // RegRead(x1370)
    val x1376 = DRAM(dims=List(x1375)).name("x1376").ctrl(top).srcCtx("DotProduct.scala:27:20:b") // x1376 = DRAMNew(ArrayBuffer(x1375),Const(0))
    val x1377 = ArgOut(init=0).name("x1377").ctrl(top).srcCtx("DotProduct.scala:28:21:out") // ArgOutNew(Const(0))
    isAccum(x1377) = false
    bufferDepthOf(x1377) = 1
    val x1451 = UnitController(style=SeqPipe, level=OuterControl).name("x1451").ctrl(top).srcCtx("DotProduct.scala:32:11") // Hwblock(Block(Const(())),false)
    val x1380_d0 = Reg(init=Some(0)).name("x1380_d0").ctrl(x1451).srcCtx("DotProduct.scala:33:27") // x1380 = RegNew(Const(0))
    isAccum(x1380_d0) = false
    bufferDepthOf(x1380_d0) = 1
    val x1380_d1 = Reg(init=Some(0)).name("x1380_d1").ctrl(x1451).srcCtx("DotProduct.scala:33:27") // x1380 = RegNew(Const(0))
    isAccum(x1380_d1) = true
    bufferDepthOf(x1380_d1) = 1
    val x1381 = ReadMem(x1370).name("x1381").ctrl(x1451).srcCtx("DotProduct.scala:33:38") // RegRead(x1370)
    val x1382 = Counter(min=Const(0), max=x1381, step=Const(32), par=1).name("x1382").ctrl(x1451).srcCtx("DotProduct.scala:33:49") // CounterNew(Const(0),x1381,Const(32),Const(1))
    val x1383 = CounterChain(List(x1382)).name("x1383").ctrl(x1451).srcCtx("DotProduct.scala:41:8") // CounterChainNew(List(x1382))
    val x1447 = LoopController(style=MetaPipe, level=OuterControl, cchain=x1383).name("x1447").ctrl(x1451).srcCtx("DotProduct.scala:41:8") // UnrolledReduce(List(Const(true)),x1383,x1380,Block((x1380) => Const(())),List(List(b856)),List(List(b857)))
    val b856 = CounterIter(x1382, Some(0)).name("b856").ctrl(x1447) // b856
    val b857 = Const(true).name("b857").ctrl(x1447) // b857
    val x1384_d0_b0 = SRAM(size=32, banking=Strided(banks=16, stride=1)).name("x1384_d0_b0").ctrl(x1447).srcCtx("DotProduct.scala:34:27:aBlk") // x1384 = SRAMNew(ArrayBuffer(Const(32)))
    isAccum(x1384_d0_b0) = false
    bufferDepthOf(x1384_d0_b0) = 2
    val x1385_d0_b0 = SRAM(size=32, banking=Strided(banks=16, stride=1)).name("x1385_d0_b0").ctrl(x1447).srcCtx("DotProduct.scala:35:27:bBlk") // x1385 = SRAMNew(ArrayBuffer(Const(32)))
    isAccum(x1385_d0_b0) = false
    bufferDepthOf(x1385_d0_b0) = 2
    val x1426 = UnitController(style=ForkJoin, level=OuterControl).name("x1426").ctrl(x1447).srcCtx("DotProduct.scala:36:18") // ParallelPipe(List(b857),Block(Const(())))
    val x1387 = UnitController(style=SeqPipe, level=InnerControl).name("x1387").ctrl(x1426).srcCtx("DotProduct.scala:36:18") // UnitPipe(List(b857),Block(Const(())))
    val x1386 = OpDef(op=FixAdd, inputs=List(b856, Const(32))).name("x1386").ctrl(x1387).srcCtx("DotProduct.scala:37:27") // FixAdd(b856,Const(32))
    val x1406 = UnitController(style=StreamPipe, level=OuterControl).name("x1406").ctrl(x1426).srcCtx("DotProduct.scala:37:16") // UnitPipe(List(b857),Block(Const(())))
    val b1470 = StreamOut(field="offset").name("b1470").ctrl(x1406).srcCtx("DotProduct.scala:37:16") // x1388 = StreamOutNew(BurstCmdBus)
    isAccum(b1470) = false
    bufferDepthOf(b1470) = 1
    val b1471 = StreamOut(field="size").name("b1471").ctrl(x1406).srcCtx("DotProduct.scala:37:16") // x1388 = StreamOutNew(BurstCmdBus)
    isAccum(b1471) = false
    bufferDepthOf(b1471) = 1
    val x1389 = StreamIn(field="data").name("x1389").ctrl(x1406).srcCtx("DotProduct.scala:37:16") // x1389 = StreamInNew(BurstDataBus())
    isAccum(x1389) = false
    bufferDepthOf(x1389) = 1
    val x1397 = UnitController(style=SeqPipe, level=InnerControl).name("x1397").ctrl(x1406).srcCtx("DotProduct.scala:37:16") // UnitPipe(List(b857),Block(x1396))
    val x1390 = b856 // FixConvert(b856,TRUE,_32,_0) (Same Type. No op)
    val x1391 = OpDef(op=FixSla, inputs=List(x1390, Const(2))).name("x1391").ctrl(x1397).srcCtx("DotProduct.scala:37:16") // FixLsh(x1390,Const(2))
    val x1392 = x1391 // FixConvert(x1391,TRUE,_64,_0)
    val x1393 = DramAddress(x1374).name("x1393").ctrl(x1397).srcCtx("DotProduct.scala:37:16") // GetDRAMAddress(x1374)
    val x1395_x1394 = OpDef(op=FixAdd, inputs=List(x1392, x1393)).name("x1395_x1394").ctrl(x1397).srcCtx("DotProduct.scala:37:16") // FixAdd(x1392,x1393)
    // x1395 = SimpleStruct(ArrayBuffer((offset,x1394), (size,Const(128)), (isLoad,Const(true))))
    val x1396_b1472_b1470 = WriteMem(b1470, x1395_x1394).name("x1396_b1472_b1470").ctrl(x1397).srcCtx("DotProduct.scala:37:16") // StreamWrite(x1388,x1395,b857)
    val x1396_b1473_b1471 = WriteMem(b1471, Const(128)).name("x1396_b1473_b1471").ctrl(x1397).srcCtx("DotProduct.scala:37:16") // StreamWrite(x1388,x1395,b857)
    val x1398 = FringeDenseLoad(dram=List(x1374), cmdStream=List(b1470, b1471), dataStream=List(x1389)).name("x1398").ctrl(x1406).srcCtx("DotProduct.scala:37:16") // FringeDenseLoad(x1374,x1388,x1389)
    val x1399 = Counter(min=Const(0), max=Const(32), step=Const(1), par=16).name("x1399").ctrl(x1406).srcCtx("DotProduct.scala:37:16") // CounterNew(Const(0),Const(32),Const(1),Const(16))
    val x1400 = CounterChain(List(x1399)).name("x1400").ctrl(x1406).srcCtx("DotProduct.scala:37:16") // CounterChainNew(List(x1399))
    val x1405 = LoopController(style=InnerPipe, level=InnerControl, cchain=x1400).name("x1405").ctrl(x1406).srcCtx("DotProduct.scala:37:16") // UnrolledForeach(List(b857),x1400,Block(Const(())),List(List(b875)),List(List(b876)))
    val b875 = CounterIter(x1399, None).name("b875").ctrl(x1405) // b875
    val b876 = Const(true).name("b876").ctrl(x1405) // b876
    val x1401 = OpDef(op=BitAnd, inputs=List(b876, b857)).name("x1401").ctrl(x1405).srcCtx("UnrollingBase.scala:28:66") // And(b876,b857)
    val x1402_x1402 = ReadMem(x1389).name("x1402_x1402").ctrl(x1405).srcCtx("DotProduct.scala:37:16") // ParStreamRead(x1389,List(x1401))
    val x1403_x1403 = x1402_x1402 // x1403 = VectorApply(x1402,0)
    val x1404 = StoreBanks(List(x1384_d0_b0), List(b875), x1403_x1403).name("x1404").ctrl(x1405).srcCtx("DotProduct.scala:37:16") // ParSRAMStore(x1384,List(List(b875)),List(x1403),List(x1401))
    val x1425 = UnitController(style=StreamPipe, level=OuterControl).name("x1425").ctrl(x1426).srcCtx("DotProduct.scala:38:16") // UnitPipe(List(b857),Block(Const(())))
    val b1474 = StreamOut(field="offset").name("b1474").ctrl(x1425).srcCtx("DotProduct.scala:38:16") // x1407 = StreamOutNew(BurstCmdBus)
    isAccum(b1474) = false
    bufferDepthOf(b1474) = 1
    val b1475 = StreamOut(field="size").name("b1475").ctrl(x1425).srcCtx("DotProduct.scala:38:16") // x1407 = StreamOutNew(BurstCmdBus)
    isAccum(b1475) = false
    bufferDepthOf(b1475) = 1
    val x1408 = StreamIn(field="data").name("x1408").ctrl(x1425).srcCtx("DotProduct.scala:38:16") // x1408 = StreamInNew(BurstDataBus())
    isAccum(x1408) = false
    bufferDepthOf(x1408) = 1
    val x1416 = UnitController(style=SeqPipe, level=InnerControl).name("x1416").ctrl(x1425).srcCtx("DotProduct.scala:38:16") // UnitPipe(List(b857),Block(x1415))
    val x1409 = b856 // FixConvert(b856,TRUE,_32,_0) (Same Type. No op)
    val x1410 = OpDef(op=FixSla, inputs=List(x1409, Const(2))).name("x1410").ctrl(x1416).srcCtx("DotProduct.scala:38:16") // FixLsh(x1409,Const(2))
    val x1411 = x1410 // FixConvert(x1410,TRUE,_64,_0)
    val x1412 = DramAddress(x1376).name("x1412").ctrl(x1416).srcCtx("DotProduct.scala:38:16") // GetDRAMAddress(x1376)
    val x1414_x1413 = OpDef(op=FixAdd, inputs=List(x1411, x1412)).name("x1414_x1413").ctrl(x1416).srcCtx("DotProduct.scala:38:16") // FixAdd(x1411,x1412)
    // x1414 = SimpleStruct(ArrayBuffer((offset,x1413), (size,Const(128)), (isLoad,Const(true))))
    val x1415_b1476_b1474 = WriteMem(b1474, x1414_x1413).name("x1415_b1476_b1474").ctrl(x1416).srcCtx("DotProduct.scala:38:16") // StreamWrite(x1407,x1414,b857)
    val x1415_b1477_b1475 = WriteMem(b1475, Const(128)).name("x1415_b1477_b1475").ctrl(x1416).srcCtx("DotProduct.scala:38:16") // StreamWrite(x1407,x1414,b857)
    val x1417 = FringeDenseLoad(dram=List(x1376), cmdStream=List(b1474, b1475), dataStream=List(x1408)).name("x1417").ctrl(x1425).srcCtx("DotProduct.scala:38:16") // FringeDenseLoad(x1376,x1407,x1408)
    val x1418 = Counter(min=Const(0), max=Const(32), step=Const(1), par=16).name("x1418").ctrl(x1425).srcCtx("DotProduct.scala:38:16") // CounterNew(Const(0),Const(32),Const(1),Const(16))
    val x1419 = CounterChain(List(x1418)).name("x1419").ctrl(x1425).srcCtx("DotProduct.scala:38:16") // CounterChainNew(List(x1418))
    val x1424 = LoopController(style=InnerPipe, level=InnerControl, cchain=x1419).name("x1424").ctrl(x1425).srcCtx("DotProduct.scala:38:16") // UnrolledForeach(List(b857),x1419,Block(Const(())),List(List(b896)),List(List(b897)))
    val b896 = CounterIter(x1418, None).name("b896").ctrl(x1424) // b896
    val b897 = Const(true).name("b897").ctrl(x1424) // b897
    val x1420 = OpDef(op=BitAnd, inputs=List(b897, b857)).name("x1420").ctrl(x1424).srcCtx("UnrollingBase.scala:28:66") // And(b897,b857)
    val x1421_x1421 = ReadMem(x1408).name("x1421_x1421").ctrl(x1424).srcCtx("DotProduct.scala:38:16") // ParStreamRead(x1408,List(x1420))
    val x1422_x1422 = x1421_x1421 // x1422 = VectorApply(x1421,0)
    val x1423 = StoreBanks(List(x1385_d0_b0), List(b896), x1422_x1422).name("x1423").ctrl(x1424).srcCtx("DotProduct.scala:38:16") // ParSRAMStore(x1385,List(List(b896)),List(x1422),List(x1420))
    val x1427_d0 = Reg(init=Some(0)).name("x1427_d0").ctrl(x1447).srcCtx("DotProduct.scala:40:22") // x1427 = RegNew(Const(0))
    isAccum(x1427_d0) = false
    bufferDepthOf(x1427_d0) = 2
    val x1427_d1 = Reg(init=Some(0)).name("x1427_d1").ctrl(x1447).srcCtx("DotProduct.scala:40:22") // x1427 = RegNew(Const(0))
    isAccum(x1427_d1) = true
    bufferDepthOf(x1427_d1) = 1
    val x1428 = Counter(min=Const(0), max=Const(32), step=Const(1), par=16).name("x1428").ctrl(x1447).srcCtx("DotProduct.scala:40:36") // CounterNew(Const(0),Const(32),Const(1),Const(16))
    val x1429 = CounterChain(List(x1428)).name("x1429").ctrl(x1447).srcCtx("DotProduct.scala:40:71") // CounterChainNew(List(x1428))
    val x1440 = LoopController(style=InnerPipe, level=InnerControl, cchain=x1429).name("x1440").ctrl(x1447).srcCtx("DotProduct.scala:40:71") // UnrolledReduce(List(b857),x1429,x1427,Block((x1427) => Const(())),List(List(b908)),List(List(b909)))
    val b908 = CounterIter(x1428, None).name("b908").ctrl(x1440) // b908
    val b909 = Const(true).name("b909").ctrl(x1440) // b909
    val x1430 = OpDef(op=BitAnd, inputs=List(b909, b857)).name("x1430").ctrl(x1440).srcCtx("UnrollingBase.scala:28:66") // And(b909,b857)
    val x1431 = LoadBanks(List(x1384_d0_b0), List(b908)).name("x1431").ctrl(x1440).srcCtx("DotProduct.scala:40:54") // ParSRAMLoad(x1384,List(List(b908)),List(x1430))
    val x1432 = x1431 // x1432 = VectorApply(x1431,0)
    val x1433 = LoadBanks(List(x1385_d0_b0), List(b908)).name("x1433").ctrl(x1440).srcCtx("DotProduct.scala:40:65") // ParSRAMLoad(x1385,List(List(b908)),List(x1430))
    val x1434 = x1433 // x1434 = VectorApply(x1433,0)
    val x1435 = OpDef(op=FixMul, inputs=List(x1432, x1434)).name("x1435").ctrl(x1440).srcCtx("DotProduct.scala:40:59") // FixMul(x1432,x1434)
    val x1436 = ReadMem(x1427_d1).name("x1436").ctrl(x1440).srcCtx("DotProduct.scala:40:71") // RegRead(x1427)
    val x1437 = OpDef(op=FixEql, inputs=List(b908, Const(0))).name("x1437").ctrl(x1440).srcCtx("DotProduct.scala:40:71") // FixEql(b908,Const(0))
    val x1438 = ReduceAccumOp(op=FixAdd, input=x1435, accum=x1436).name("x1438").ctrl(x1440).srcCtx("DotProduct.scala:40:73") // FixAdd(x1435,x1436)
    val x1439_x1427_d0 = WriteMem(x1427_d0, x1438).name("x1439_x1427_d0").ctrl(x1440).srcCtx("DotProduct.scala:40:71") // RegWrite(x1427,x1438,b857)
    val x1439_x1427_d1 = WriteMem(x1427_d1, x1438).name("x1439_x1427_d1").ctrl(x1440).srcCtx("DotProduct.scala:40:71") // RegWrite(x1427,x1438,b857)
    val x1446 = UnitController(style=SeqPipe, level=InnerControl).name("x1446").ctrl(x1447).srcCtx("DotProduct.scala:41:8") // UnitPipe(List(Const(true)),Block(x1445))
    val x1441 = ReadMem(x1380_d1).name("x1441").ctrl(x1446).srcCtx("DotProduct.scala:41:8") // RegRead(x1380)
    val x1442 = OpDef(op=FixEql, inputs=List(b856, Const(0))).name("x1442").ctrl(x1446).srcCtx("DotProduct.scala:41:8") // FixEql(b856,Const(0))
    val x1443 = ReadMem(x1427_d0).name("x1443").ctrl(x1446).srcCtx("DotProduct.scala:40:71") // RegRead(x1427)
    val x1444 = OpDef(op=FixAdd, inputs=List(x1443, x1441)).name("x1444").ctrl(x1446).srcCtx("DotProduct.scala:41:10") // FixAdd(x1443,x1441)
    val x1445_x1380_d0 = WriteMem(x1380_d0, x1444).name("x1445_x1380_d0").ctrl(x1446).srcCtx("DotProduct.scala:41:8") // RegWrite(x1380,x1444,Const(true))
    val x1445_x1380_d1 = WriteMem(x1380_d1, x1444).name("x1445_x1380_d1").ctrl(x1446).srcCtx("DotProduct.scala:41:8") // RegWrite(x1380,x1444,Const(true))
    val x1450 = UnitController(style=SeqPipe, level=InnerControl).name("x1450").ctrl(x1451).srcCtx("DotProduct.scala:32:11") // UnitPipe(List(Const(true)),Block(Const(())))
    val x1448 = ReadMem(x1380_d0).name("x1448").ctrl(x1450).srcCtx("DotProduct.scala:41:8") // RegRead(x1380)
    val x1449_x1377 = WriteMem(x1377, x1448).name("x1449_x1377").ctrl(x1450).srcCtx("DotProduct.scala:33:11") // RegWrite(x1377,x1448,Const(true))
    
  }
}
