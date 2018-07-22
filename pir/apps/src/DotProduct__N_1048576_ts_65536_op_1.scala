import pir._
import pir.node._
import arch._
import prism.enums._

object DotProduct__N_1048576_ts_65536_op_1 extends PIRApp {
  def main(implicit design:PIRDesign) = {
    import design.pirmeta._
    val x1370 = withCtrl(design.top.topController) { ArgIn(init=0).name("x1370").srcCtx("DotProduct__N_1048576_ts_65536_op_1.scala:19:21:size") } // ArgInNew(Const(0))
    isAccum(x1370) = false
    bufferDepthOf(x1370) = 1
    boundOf(x1370) = 1048576
    val x1373 = withCtrl(design.top.topController) { ReadMem(x1370).name("x1373").srcCtx("DotProduct__N_1048576_ts_65536_op_1.scala:22:21") } // RegRead(x1370)
    val x1374 = withCtrl(design.top.topController) { DRAM(dims=List(x1373)).name("x1374").srcCtx("DotProduct__N_1048576_ts_65536_op_1.scala:22:20:a") } // x1374 = DRAMNew(ArrayBuffer(x1373),Const(0))
    val x1375 = withCtrl(design.top.topController) { ReadMem(x1370).name("x1375").srcCtx("DotProduct__N_1048576_ts_65536_op_1.scala:23:21") } // RegRead(x1370)
    val x1376 = withCtrl(design.top.topController) { DRAM(dims=List(x1375)).name("x1376").srcCtx("DotProduct__N_1048576_ts_65536_op_1.scala:23:20:b") } // x1376 = DRAMNew(ArrayBuffer(x1375),Const(0))
    val x1377 = withCtrl(design.top.topController) { ArgOut(init=0).name("x1377").srcCtx("DotProduct__N_1048576_ts_65536_op_1.scala:24:21:out") } // ArgOutNew(Const(0))
    isAccum(x1377) = false
    bufferDepthOf(x1377) = 1
    val x1451 = withCtrl(design.top.topController) { UnitController(style=SeqPipe, level=OuterControl).name("x1451").srcCtx("DotProduct__N_1048576_ts_65536_op_1.scala:28:11") } // Hwblock(Block(Const(())),false)
    val x1380_d0 = withCtrl(x1451) { Reg(init=Some(0)).name("x1380_d0").srcCtx("DotProduct__N_1048576_ts_65536_op_1.scala:29:27") } // x1380 = RegNew(Const(0))
    isAccum(x1380_d0) = false
    bufferDepthOf(x1380_d0) = 1
    val x1380_d1 = withCtrl(x1451) { Reg(init=Some(0)).name("x1380_d1").srcCtx("DotProduct__N_1048576_ts_65536_op_1.scala:29:27") } // x1380 = RegNew(Const(0))
    isAccum(x1380_d1) = true
    bufferDepthOf(x1380_d1) = 1
    val x1381 = withCtrl(x1451) { ReadMem(x1370).name("x1381").srcCtx("DotProduct__N_1048576_ts_65536_op_1.scala:29:38") } // RegRead(x1370)
    val x1382 = withCtrl(x1451) { Counter(min=Const(0), max=x1381, step=Const(65536), par=1).name("x1382").srcCtx("DotProduct__N_1048576_ts_65536_op_1.scala:29:49") } // CounterNew(Const(0),x1381,Const(65536),Const(1))
    val x1383 = withCtrl(x1451) { CounterChain(List(x1382)).name("x1383").srcCtx("DotProduct__N_1048576_ts_65536_op_1.scala:37:8") } // CounterChainNew(List(x1382))
    val x1447 = withCtrl(x1451) { LoopController(style=MetaPipe, level=OuterControl, cchain=x1383).name("x1447").srcCtx("DotProduct__N_1048576_ts_65536_op_1.scala:37:8") } // UnrolledReduce(List(Const(true)),x1383,x1380,Block((x1380) => Const(())),List(List(b856)),List(List(b857)))
    val b856 = withCtrl(x1447) { CounterIter(x1382, Some(0)).name("b856") } // b856
    val b857 = withCtrl(x1447) { Const(true).name("b857") } // b857
    val x1384_d0_b0 = withCtrl(x1447) { SRAM(size=65536, banking=Strided(banks=16, stride=1)).name("x1384_d0_b0").srcCtx("DotProduct__N_1048576_ts_65536_op_1.scala:30:27:aBlk") } // x1384 = SRAMNew(ArrayBuffer(Const(65536)))
    isAccum(x1384_d0_b0) = false
    bufferDepthOf(x1384_d0_b0) = 2
    staticDimsOf(x1384_d0_b0) = List(65536)
    val x1385_d0_b0 = withCtrl(x1447) { SRAM(size=65536, banking=Strided(banks=16, stride=1)).name("x1385_d0_b0").srcCtx("DotProduct__N_1048576_ts_65536_op_1.scala:31:27:bBlk") } // x1385 = SRAMNew(ArrayBuffer(Const(65536)))
    isAccum(x1385_d0_b0) = false
    bufferDepthOf(x1385_d0_b0) = 2
    staticDimsOf(x1385_d0_b0) = List(65536)
    val x1426 = withCtrl(x1447) { UnitController(style=ForkJoin, level=OuterControl).name("x1426").srcCtx("DotProduct__N_1048576_ts_65536_op_1.scala:32:18") } // ParallelPipe(List(b857),Block(Const(())))
    val x1387 = withCtrl(x1426) { UnitController(style=SeqPipe, level=InnerControl).name("x1387").srcCtx("DotProduct__N_1048576_ts_65536_op_1.scala:32:18") } // UnitPipe(List(b857),Block(Const(())))
    val x1386 = withCtrl(x1387) { OpDef(op=FixAdd, inputs=List(b856, Const(65536))).name("x1386").srcCtx("DotProduct__N_1048576_ts_65536_op_1.scala:33:27") } // FixAdd(b856,Const(65536))
    val x1406 = withCtrl(x1426) { UnitController(style=StreamPipe, level=OuterControl).name("x1406").srcCtx("DotProduct__N_1048576_ts_65536_op_1.scala:33:16") } // UnitPipe(List(b857),Block(Const(())))
    val b1470 = withCtrl(x1406) { StreamOut(field="offset").name("b1470").srcCtx("DotProduct__N_1048576_ts_65536_op_1.scala:33:16") } // x1388 = StreamOutNew(BurstCmdBus)
    isAccum(b1470) = false
    bufferDepthOf(b1470) = 1
    val b1471 = withCtrl(x1406) { StreamOut(field="size").name("b1471").srcCtx("DotProduct__N_1048576_ts_65536_op_1.scala:33:16") } // x1388 = StreamOutNew(BurstCmdBus)
    isAccum(b1471) = false
    bufferDepthOf(b1471) = 1
    val x1389 = withCtrl(x1406) { StreamIn(field="data").name("x1389").srcCtx("DotProduct__N_1048576_ts_65536_op_1.scala:33:16") } // x1389 = StreamInNew(BurstDataBus())
    isAccum(x1389) = false
    bufferDepthOf(x1389) = 1
    val x1397 = withCtrl(x1406) { UnitController(style=SeqPipe, level=InnerControl).name("x1397").srcCtx("DotProduct__N_1048576_ts_65536_op_1.scala:33:16") } // UnitPipe(List(b857),Block(x1396))
    val x1390 = withCtrl(x1397) { b856 } // FixConvert(b856,TRUE,_32,_0) (Same Type. No op)
    val x1391 = withCtrl(x1397) { OpDef(op=FixSla, inputs=List(x1390, Const(2))).name("x1391").srcCtx("DotProduct__N_1048576_ts_65536_op_1.scala:33:16") } // FixLsh(x1390,Const(2))
    val x1392 = withCtrl(x1397) { x1391 } // FixConvert(x1391,TRUE,_64,_0)
    val x1393 = withCtrl(x1397) { DramAddress(x1374).name("x1393").srcCtx("DotProduct__N_1048576_ts_65536_op_1.scala:33:16") } // GetDRAMAddress(x1374)
    val x1395_x1394 = withCtrl(x1397) { OpDef(op=FixAdd, inputs=List(x1392, x1393)).name("x1395_x1394").srcCtx("DotProduct__N_1048576_ts_65536_op_1.scala:33:16") } // FixAdd(x1392,x1393)
    // x1395 = SimpleStruct(ArrayBuffer((offset,x1394), (size,Const(262144)), (isLoad,Const(true))))
    val x1396_b1472_b1470 = withCtrl(x1397) { WriteMem(b1470, x1395_x1394).name("x1396_b1472_b1470").srcCtx("DotProduct__N_1048576_ts_65536_op_1.scala:33:16") } // StreamWrite(x1388,x1395,b857)
    val x1396_b1473_b1471 = withCtrl(x1397) { WriteMem(b1471, Const(262144)).name("x1396_b1473_b1471").srcCtx("DotProduct__N_1048576_ts_65536_op_1.scala:33:16") } // StreamWrite(x1388,x1395,b857)
    val x1398 = withCtrl(x1406) { FringeDenseLoad(dram=List(x1374), cmdStream=List(b1470, b1471), dataStream=List(x1389)).name("x1398").srcCtx("DotProduct__N_1048576_ts_65536_op_1.scala:33:16") } // FringeDenseLoad(x1374,x1388,x1389)
    val x1399 = withCtrl(x1406) { Counter(min=Const(0), max=Const(65536), step=Const(1), par=16).name("x1399").srcCtx("DotProduct__N_1048576_ts_65536_op_1.scala:33:16") } // CounterNew(Const(0),Const(65536),Const(1),Const(16))
    val x1400 = withCtrl(x1406) { CounterChain(List(x1399)).name("x1400").srcCtx("DotProduct__N_1048576_ts_65536_op_1.scala:33:16") } // CounterChainNew(List(x1399))
    val x1405 = withCtrl(x1406) { LoopController(style=InnerPipe, level=InnerControl, cchain=x1400).name("x1405").srcCtx("DotProduct__N_1048576_ts_65536_op_1.scala:33:16") } // UnrolledForeach(List(b857),x1400,Block(Const(())),List(List(b875)),List(List(b876)))
    val b875 = withCtrl(x1405) { CounterIter(x1399, None).name("b875") } // b875
    val b876 = withCtrl(x1405) { Const(true).name("b876") } // b876
    val x1401 = withCtrl(x1405) { OpDef(op=BitAnd, inputs=List(b876, b857)).name("x1401").srcCtx("UnrollingBase.scala:28:66") } // And(b876,b857)
    val x1402_x1402 = withCtrl(x1405) { ReadMem(x1389).name("x1402_x1402").srcCtx("DotProduct__N_1048576_ts_65536_op_1.scala:33:16") } // ParStreamRead(x1389,List(x1401))
    val x1403_x1403 = withCtrl(x1405) { x1402_x1402 } // VectorApply(x1402,0)
    val x1404 = withCtrl(x1405) { StoreBanks(List(List(x1384_d0_b0)), List(b875), x1403_x1403).name("x1404").srcCtx("DotProduct__N_1048576_ts_65536_op_1.scala:33:16") } // ParSRAMStore(x1384,List(List(b875)),List(x1403),List(x1401))
    val x1425 = withCtrl(x1426) { UnitController(style=StreamPipe, level=OuterControl).name("x1425").srcCtx("DotProduct__N_1048576_ts_65536_op_1.scala:34:16") } // UnitPipe(List(b857),Block(Const(())))
    val b1474 = withCtrl(x1425) { StreamOut(field="offset").name("b1474").srcCtx("DotProduct__N_1048576_ts_65536_op_1.scala:34:16") } // x1407 = StreamOutNew(BurstCmdBus)
    isAccum(b1474) = false
    bufferDepthOf(b1474) = 1
    val b1475 = withCtrl(x1425) { StreamOut(field="size").name("b1475").srcCtx("DotProduct__N_1048576_ts_65536_op_1.scala:34:16") } // x1407 = StreamOutNew(BurstCmdBus)
    isAccum(b1475) = false
    bufferDepthOf(b1475) = 1
    val x1408 = withCtrl(x1425) { StreamIn(field="data").name("x1408").srcCtx("DotProduct__N_1048576_ts_65536_op_1.scala:34:16") } // x1408 = StreamInNew(BurstDataBus())
    isAccum(x1408) = false
    bufferDepthOf(x1408) = 1
    val x1416 = withCtrl(x1425) { UnitController(style=SeqPipe, level=InnerControl).name("x1416").srcCtx("DotProduct__N_1048576_ts_65536_op_1.scala:34:16") } // UnitPipe(List(b857),Block(x1415))
    val x1409 = withCtrl(x1416) { b856 } // FixConvert(b856,TRUE,_32,_0) (Same Type. No op)
    val x1410 = withCtrl(x1416) { OpDef(op=FixSla, inputs=List(x1409, Const(2))).name("x1410").srcCtx("DotProduct__N_1048576_ts_65536_op_1.scala:34:16") } // FixLsh(x1409,Const(2))
    val x1411 = withCtrl(x1416) { x1410 } // FixConvert(x1410,TRUE,_64,_0)
    val x1412 = withCtrl(x1416) { DramAddress(x1376).name("x1412").srcCtx("DotProduct__N_1048576_ts_65536_op_1.scala:34:16") } // GetDRAMAddress(x1376)
    val x1414_x1413 = withCtrl(x1416) { OpDef(op=FixAdd, inputs=List(x1411, x1412)).name("x1414_x1413").srcCtx("DotProduct__N_1048576_ts_65536_op_1.scala:34:16") } // FixAdd(x1411,x1412)
    // x1414 = SimpleStruct(ArrayBuffer((offset,x1413), (size,Const(262144)), (isLoad,Const(true))))
    val x1415_b1476_b1474 = withCtrl(x1416) { WriteMem(b1474, x1414_x1413).name("x1415_b1476_b1474").srcCtx("DotProduct__N_1048576_ts_65536_op_1.scala:34:16") } // StreamWrite(x1407,x1414,b857)
    val x1415_b1477_b1475 = withCtrl(x1416) { WriteMem(b1475, Const(262144)).name("x1415_b1477_b1475").srcCtx("DotProduct__N_1048576_ts_65536_op_1.scala:34:16") } // StreamWrite(x1407,x1414,b857)
    val x1417 = withCtrl(x1425) { FringeDenseLoad(dram=List(x1376), cmdStream=List(b1474, b1475), dataStream=List(x1408)).name("x1417").srcCtx("DotProduct__N_1048576_ts_65536_op_1.scala:34:16") } // FringeDenseLoad(x1376,x1407,x1408)
    val x1418 = withCtrl(x1425) { Counter(min=Const(0), max=Const(65536), step=Const(1), par=16).name("x1418").srcCtx("DotProduct__N_1048576_ts_65536_op_1.scala:34:16") } // CounterNew(Const(0),Const(65536),Const(1),Const(16))
    val x1419 = withCtrl(x1425) { CounterChain(List(x1418)).name("x1419").srcCtx("DotProduct__N_1048576_ts_65536_op_1.scala:34:16") } // CounterChainNew(List(x1418))
    val x1424 = withCtrl(x1425) { LoopController(style=InnerPipe, level=InnerControl, cchain=x1419).name("x1424").srcCtx("DotProduct__N_1048576_ts_65536_op_1.scala:34:16") } // UnrolledForeach(List(b857),x1419,Block(Const(())),List(List(b896)),List(List(b897)))
    val b896 = withCtrl(x1424) { CounterIter(x1418, None).name("b896") } // b896
    val b897 = withCtrl(x1424) { Const(true).name("b897") } // b897
    val x1420 = withCtrl(x1424) { OpDef(op=BitAnd, inputs=List(b897, b857)).name("x1420").srcCtx("UnrollingBase.scala:28:66") } // And(b897,b857)
    val x1421_x1421 = withCtrl(x1424) { ReadMem(x1408).name("x1421_x1421").srcCtx("DotProduct__N_1048576_ts_65536_op_1.scala:34:16") } // ParStreamRead(x1408,List(x1420))
    val x1422_x1422 = withCtrl(x1424) { x1421_x1421 } // VectorApply(x1421,0)
    val x1423 = withCtrl(x1424) { StoreBanks(List(List(x1385_d0_b0)), List(b896), x1422_x1422).name("x1423").srcCtx("DotProduct__N_1048576_ts_65536_op_1.scala:34:16") } // ParSRAMStore(x1385,List(List(b896)),List(x1422),List(x1420))
    val x1427_d0 = withCtrl(x1447) { Reg(init=Some(0)).name("x1427_d0").srcCtx("DotProduct__N_1048576_ts_65536_op_1.scala:36:22") } // x1427 = RegNew(Const(0))
    isAccum(x1427_d0) = false
    bufferDepthOf(x1427_d0) = 2
    val x1427_d1 = withCtrl(x1447) { Reg(init=Some(0)).name("x1427_d1").srcCtx("DotProduct__N_1048576_ts_65536_op_1.scala:36:22") } // x1427 = RegNew(Const(0))
    isAccum(x1427_d1) = true
    bufferDepthOf(x1427_d1) = 1
    val x1428 = withCtrl(x1447) { Counter(min=Const(0), max=Const(65536), step=Const(1), par=16).name("x1428").srcCtx("DotProduct__N_1048576_ts_65536_op_1.scala:36:36") } // CounterNew(Const(0),Const(65536),Const(1),Const(16))
    val x1429 = withCtrl(x1447) { CounterChain(List(x1428)).name("x1429").srcCtx("DotProduct__N_1048576_ts_65536_op_1.scala:36:71") } // CounterChainNew(List(x1428))
    val x1440 = withCtrl(x1447) { LoopController(style=InnerPipe, level=InnerControl, cchain=x1429).name("x1440").srcCtx("DotProduct__N_1048576_ts_65536_op_1.scala:36:71") } // UnrolledReduce(List(b857),x1429,x1427,Block((x1427) => Const(())),List(List(b908)),List(List(b909)))
    val b908 = withCtrl(x1440) { CounterIter(x1428, None).name("b908") } // b908
    val b909 = withCtrl(x1440) { Const(true).name("b909") } // b909
    val x1430 = withCtrl(x1440) { OpDef(op=BitAnd, inputs=List(b909, b857)).name("x1430").srcCtx("UnrollingBase.scala:28:66") } // And(b909,b857)
    val x1431 = withCtrl(x1440) { LoadBanks(List(x1384_d0_b0), List(b908)).name("x1431").srcCtx("DotProduct__N_1048576_ts_65536_op_1.scala:36:54") } // ParSRAMLoad(x1384,List(List(b908)),List(x1430))
    val x1432 = withCtrl(x1440) { x1431 } // VectorApply(x1431,0)
    val x1433 = withCtrl(x1440) { LoadBanks(List(x1385_d0_b0), List(b908)).name("x1433").srcCtx("DotProduct__N_1048576_ts_65536_op_1.scala:36:65") } // ParSRAMLoad(x1385,List(List(b908)),List(x1430))
    val x1434 = withCtrl(x1440) { x1433 } // VectorApply(x1433,0)
    val x1435 = withCtrl(x1440) { OpDef(op=FixMul, inputs=List(x1432, x1434)).name("x1435").srcCtx("DotProduct__N_1048576_ts_65536_op_1.scala:36:59") } // FixMul(x1432,x1434)
    val x1436 = withCtrl(x1440) { ReadMem(x1427_d1).name("x1436").srcCtx("DotProduct__N_1048576_ts_65536_op_1.scala:36:71") } // RegRead(x1427)
    val x1437 = withCtrl(x1440) { OpDef(op=FixEql, inputs=List(b908, Const(0))).name("x1437").srcCtx("DotProduct__N_1048576_ts_65536_op_1.scala:36:71") } // FixEql(b908,Const(0))
    val x1438 = withCtrl(x1440) { ReduceAccumOp(op=FixAdd, input=x1435, accum=x1436).name("x1438").srcCtx("DotProduct__N_1048576_ts_65536_op_1.scala:36:73") } // FixAdd(x1435,x1436)
    val x1439_x1427_d0 = withCtrl(x1440) { WriteMem(x1427_d0, x1438).name("x1439_x1427_d0").srcCtx("DotProduct__N_1048576_ts_65536_op_1.scala:36:71") } // RegWrite(x1427,x1438,b857)
    antiDepsOf(x1439_x1427_d0)=List(x1436)
    val x1439_x1427_d1 = withCtrl(x1440) { WriteMem(x1427_d1, x1438).name("x1439_x1427_d1").srcCtx("DotProduct__N_1048576_ts_65536_op_1.scala:36:71") } // RegWrite(x1427,x1438,b857)
    antiDepsOf(x1439_x1427_d1)=List(x1436)
    val x1446 = withCtrl(x1447) { UnitController(style=SeqPipe, level=InnerControl).name("x1446").srcCtx("DotProduct__N_1048576_ts_65536_op_1.scala:37:8") } // UnitPipe(List(Const(true)),Block(x1445))
    val x1441 = withCtrl(x1446) { ReadMem(x1380_d1).name("x1441").srcCtx("DotProduct__N_1048576_ts_65536_op_1.scala:37:8") } // RegRead(x1380)
    val x1442 = withCtrl(x1446) { OpDef(op=FixEql, inputs=List(b856, Const(0))).name("x1442").srcCtx("DotProduct__N_1048576_ts_65536_op_1.scala:37:8") } // FixEql(b856,Const(0))
    val x1443 = withCtrl(x1446) { ReadMem(x1427_d0).name("x1443").srcCtx("DotProduct__N_1048576_ts_65536_op_1.scala:36:71") } // RegRead(x1427)
    val x1444 = withCtrl(x1446) { OpDef(op=FixAdd, inputs=List(x1443, x1441)).name("x1444").srcCtx("DotProduct__N_1048576_ts_65536_op_1.scala:37:10") } // FixAdd(x1443,x1441)
    val x1445_x1380_d0 = withCtrl(x1446) { WriteMem(x1380_d0, x1444).name("x1445_x1380_d0").srcCtx("DotProduct__N_1048576_ts_65536_op_1.scala:37:8") } // RegWrite(x1380,x1444,Const(true))
    antiDepsOf(x1445_x1380_d0)=List(x1441)
    val x1445_x1380_d1 = withCtrl(x1446) { WriteMem(x1380_d1, x1444).name("x1445_x1380_d1").srcCtx("DotProduct__N_1048576_ts_65536_op_1.scala:37:8") } // RegWrite(x1380,x1444,Const(true))
    antiDepsOf(x1445_x1380_d1)=List(x1441)
    val x1450 = withCtrl(x1451) { UnitController(style=SeqPipe, level=InnerControl).name("x1450").srcCtx("DotProduct__N_1048576_ts_65536_op_1.scala:28:11") } // UnitPipe(List(Const(true)),Block(Const(())))
    val x1448 = withCtrl(x1450) { ReadMem(x1380_d0).name("x1448").srcCtx("DotProduct__N_1048576_ts_65536_op_1.scala:37:8") } // RegRead(x1380)
    val x1449_x1377 = withCtrl(x1450) { WriteMem(x1377, x1448).name("x1449_x1377").srcCtx("DotProduct__N_1048576_ts_65536_op_1.scala:29:11") } // RegWrite(x1377,x1448,Const(true))
    
  }
}
