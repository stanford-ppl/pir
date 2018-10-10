import pir._
import pir.node._
import arch._
import prism.enums._

object DotProduct extends PIRApp {
  def main(implicit design:PIRDesign) = {
    import design.pirmeta._
    implicit class CtxHelper[T<:IR](x:T) { def ctx(c:String):T = {srcCtxOf(x) = c; x} }
    def withCtx[T<:IR](x:T, ctx:String):T = { srcCtxOf(x) = ctx; x}
    def withAcc[T<:Memory](x:T, accum:Boolean) = { isAccum(x) = accum; x }
    def withBD[T<:Memory](x:T, depth:Int) = { bufferDepthOf(x) = depth; x }
    def withCount[T](x:T, count:Long) = { countOf(x) = Some(count); x }
    def withDims[T<:Memory](x:T, dims:List[Int]) = { staticDimsOf(x) = dims; x }
    def withBound[T<:Memory](x:T, bound:Any) = { boundOf(x) = bound; x }
    def withFile[T<:Memory](x:T, path:String) = { fileNameOf(x) = path; x }
    def withDeps(x:PIRNode, deps:List[PIRNode]) = { antiDepsOf(x) = deps; x }
    val nameSpace = scala.collection.mutable.Map[String,Any]()
    def lookup[T](name:String) = nameSpace(name).asInstanceOf[T]
    def withName[T<:IR](x:T, name:String):T = { if (!nameOf.contains(x)) nameOf(x) = name; nameSpace += name -> x; x}
    def split1 = {
    // DefRhs(LhsMem(x1370,0,None),ArgIn,WrappedArray((init,0)))
    val x1370 = withCtrl(design.top.topController) { withBound(withAcc(withBD(withName(ArgIn(init=0).ctx("DotProduct.scala:18:21:size"), "x1370"), 1), false), 1024) } // ArgInNew(Const(0))
    // DefRhs(LhsSym(x1373,None),ReadMem,WrappedArray(LhsMem(x1370,0,None)))
    val x1373 = withCtrl(design.top.topController) { withName(withCtx(ReadMem(x1370), "DotProduct.scala:21:21"), "x1373") } // RegRead(x1370)
    // DefRhs(LhsSym(x1374,None),DRAM,WrappedArray((dims,List(x1373))))
    val x1374 = withCtrl(design.top.topController) { withName(withCtx(DRAM(dims=List(x1373)), "DotProduct.scala:21:20:a"), "x1374") } // DRAMNew(ArrayBuffer(x1373),Const(0))
    // DefRhs(LhsSym(x1375,None),ReadMem,WrappedArray(LhsMem(x1370,0,None)))
    val x1375 = withCtrl(design.top.topController) { withName(withCtx(ReadMem(x1370), "DotProduct.scala:22:21"), "x1375") } // RegRead(x1370)
    // DefRhs(LhsSym(x1376,None),DRAM,WrappedArray((dims,List(x1375))))
    val x1376 = withCtrl(design.top.topController) { withName(withCtx(DRAM(dims=List(x1375)), "DotProduct.scala:22:20:b"), "x1376") } // DRAMNew(ArrayBuffer(x1375),Const(0))
    // DefRhs(LhsMem(x1377,0,None),ArgOut,WrappedArray((init,0)))
    }; split1
    def split2 = {
    val x1377 = withCtrl(design.top.topController) { withAcc(withBD(withName(withCtx(ArgOut(init=0), "DotProduct.scala:23:21:out"), "x1377"), 1), false) } // ArgOutNew(Const(0))
    // DefRhs(LhsSym(x1451,None),UnitController,WrappedArray((style,SeqPipe), (level,OuterControl)))
    val x1451 = withCtrl(design.top.topController) { withName(withCtx(UnitController(style=SeqPipe,level=OuterControl), "DotProduct.scala:27:11"), "x1451") } // Hwblock(Block(Const(())),false)
    // DefRhs(LhsMem(x1380,0,None),Reg,WrappedArray((init,Some(0))))
    val x1380_d0 = withCtrl(x1451) { withAcc(withBD(withName(withCtx(Reg(init=Some(0)), "DotProduct.scala:28:27"), "x1380_d0"), 1), false) } // RegNew(Const(0))
    // DefRhs(LhsMem(x1380,1,None),Reg,WrappedArray((init,Some(0))))
    val x1380_d1 = withCtrl(x1451) { withAcc(withBD(withName(withCtx(Reg(init=Some(0)), "DotProduct.scala:28:27"), "x1380_d1"), 1), true) } // RegNew(Const(0))
    // DefRhs(LhsSym(x1381,None),ReadMem,WrappedArray(LhsMem(x1370,0,None)))
    val x1381 = withCtrl(x1451) { withName(withCtx(ReadMem(lookup[ArgIn]("x1370")), "DotProduct.scala:28:38"), "x1381") } // RegRead(x1370)
    // DefRhs(LhsSym(x1382,None),Counter,WrappedArray((min,Const(0)), (max,x1381), (step,Const(64)), (par,1)))
    val x1382 = withCtrl(x1451) { withName(withCtx(Counter(min=Const(0),max=x1381,step=Const(64),par=1), "DotProduct.scala:28:49"), "x1382") } // CounterNew(Const(0),x1381,Const(64),Const(1))
    }; split2
    def split3 = {
    // DefRhs(LhsSym(x1383,None),CounterChain,WrappedArray(List(x1382)))
    val x1383 = withCtrl(lookup[UnitController]("x1451")) { withName(withCtx(CounterChain(List(lookup[Counter]("x1382"))), "DotProduct.scala:36:8"), "x1383") } // CounterChainNew(List(x1382))
    // DefRhs(LhsSym(x1447,None),LoopController,WrappedArray((style,MetaPipe), (level,OuterControl), (cchain,x1383)))
    val x1447 = withCtrl(lookup[UnitController]("x1451")) { withName(withCtx(LoopController(style=MetaPipe,level=OuterControl,cchain=x1383), "DotProduct.scala:36:8"), "x1447") } // UnrolledReduce(List(Const(true)),x1383,x1380,Block((x1380) => Const(())),List(List(b856)),List(List(b857)))
    // DefRhs(LhsSym(b856,None),CounterIter,WrappedArray(x1382, Some(0)))
    val b856 = withCtrl(x1447) { withName(CounterIter(lookup[Counter]("x1382"),Some(0)), "b856") } // 
    // DefRhs(LhsSym(b857,None),Const[Boolean],WrappedArray(true))
    val b857 = withCtrl(x1447) { withName(Const[Boolean](true), "b857") } // 
    // DefRhs(LhsMem(x1384,0,Some(0)),SRAM,WrappedArray((size,64), (banking,Strided(banks=16, stride=1))))
    val x1384_d0_b0 = withCtrl(x1447) { withDims(withAcc(withBD(withName(withCtx(SRAM(size=64,banking=Strided(banks=16, stride=1)), "DotProduct.scala:29:27:aBlk"), "x1384_d0_b0"), 2), false), List(64)) } // SRAMNew(ArrayBuffer(Const(64)))
    // DefRhs(LhsMem(x1385,0,Some(0)),SRAM,WrappedArray((size,64), (banking,Strided(banks=16, stride=1))))
    }; split3
    def split4 = {
    val x1385_d0_b0 = withCtrl(lookup[LoopController]("x1447")) { withDims(withAcc(withBD(withName(withCtx(SRAM(size=64,banking=Strided(banks=16, stride=1)), "DotProduct.scala:30:27:bBlk"), "x1385_d0_b0"), 2), false), List(64)) } // SRAMNew(ArrayBuffer(Const(64)))
    // DefRhs(LhsSym(x1426,None),UnitController,WrappedArray((style,ForkJoin), (level,OuterControl)))
    val x1426 = withCtrl(lookup[LoopController]("x1447")) { withName(withCtx(UnitController(style=ForkJoin,level=OuterControl), "DotProduct.scala:31:18"), "x1426") } // ParallelPipe(List(b857),Block(Const(())))
    // DefRhs(LhsSym(x1387,None),UnitController,WrappedArray((style,SeqPipe), (level,InnerControl)))
    val x1387 = withCtrl(x1426) { withName(withCtx(UnitController(style=SeqPipe,level=InnerControl), "DotProduct.scala:31:18"), "x1387") } // UnitPipe(List(b857),Block(Const(())))
    // DefRhs(LhsSym(x1386,None),OpDef,WrappedArray((op,FixAdd), (inputs,List(b856, Const(64)))))
    val x1386 = withCtrl(x1387) { withName(withCtx(OpDef(op=FixAdd,inputs=List(lookup[CounterIter]("b856"), Const(64))), "DotProduct.scala:32:27"), "x1386") } // FixAdd(b856,Const(64))
    // DefRhs(LhsSym(x1406,None),UnitController,WrappedArray((style,StreamPipe), (level,OuterControl)))
    val x1406 = withCtrl(x1426) { withName(withCtx(UnitController(style=StreamPipe,level=OuterControl), "DotProduct.scala:32:16"), "x1406") } // UnitPipe(List(b857),Block(Const(())))
    // DefRhs(LhsMem(b1470,0,None),StreamOut,WrappedArray((field,"offset")))
    val b1470 = withCtrl(x1406) { withAcc(withBD(withName(withCtx(StreamOut(field="offset"), "DotProduct.scala:32:16"), "b1470"), 1), false) } // StreamOutNew(BurstCmdBus)
    }; split4
    def split5 = {
    // DefRhs(LhsMem(b1471,0,None),StreamOut,WrappedArray((field,"size")))
    val b1471 = withCtrl(lookup[UnitController]("x1406")) { withAcc(withBD(withName(withCtx(StreamOut(field="size"), "DotProduct.scala:32:16"), "b1471"), 1), false) } // StreamOutNew(BurstCmdBus)
    // DefRhs(LhsMem(x1389,0,None),StreamIn,WrappedArray((field,"data")))
    val x1389 = withCtrl(lookup[UnitController]("x1406")) { withAcc(withBD(withName(withCtx(StreamIn(field="data"), "DotProduct.scala:32:16"), "x1389"), 1), false) } // StreamInNew(BurstDataBus())
    // DefRhs(LhsSym(x1397,None),UnitController,WrappedArray((style,SeqPipe), (level,InnerControl)))
    val x1397 = withCtrl(lookup[UnitController]("x1406")) { withName(withCtx(UnitController(style=SeqPipe,level=InnerControl), "DotProduct.scala:32:16"), "x1397") } // UnitPipe(List(b857),Block(x1396))
    // AliasRhs(LhsSym(x1390,None),LhsSym(b856,None))
    val x1390 = withName(lookup[CounterIter]("b856"), "x1390") // FixConvert(b856,TRUE,_32,_0) (Same Type. No op)
    // DefRhs(LhsSym(x1391,None),OpDef,WrappedArray((op,FixSla), (inputs,List(x1390, Const(2)))))
    val x1391 = withCtrl(x1397) { withName(withCtx(OpDef(op=FixSla,inputs=List(x1390, Const(2))), "DotProduct.scala:32:16"), "x1391") } // FixLsh(x1390,Const(2))
    // AliasRhs(LhsSym(x1392,None),LhsSym(x1391,None))
    }; split5
    def split6 = {
    val x1392 = withName(lookup[OpDef]("x1391"), "x1392") // FixConvert(x1391,TRUE,_64,_0)
    // DefRhs(LhsMem(x1393,0,None),DramAddress,WrappedArray((dram,x1374)))
    val x1393 = withCtrl(lookup[UnitController]("x1397")) { withName(withCtx(DramAddress(dram=lookup[DRAM]("x1374")), "DotProduct.scala:32:16"), "x1393") } // GetDRAMAddress(x1374)
    // DefRhs(LhsSym(x1394,None),OpDef,WrappedArray((op,FixAdd), (inputs,List(x1392, x1393))))
    val x1394 = withCtrl(lookup[UnitController]("x1397")) { withName(withCtx(OpDef(op=FixAdd,inputs=List(lookup[OpDef]("x1392"), x1393)), "DotProduct.scala:32:16"), "x1394") } // SimpleStruct(ArrayBuffer((offset,x1394), (size,Const(256)), (isLoad,Const(true))))
    // x1395 = SimpleStruct(ArrayBuffer((offset,x1394), (size,Const(256)), (isLoad,Const(true))))
    // DefRhs(LhsSym(b1472,Some(b1470)),WriteMem,WrappedArray(LhsMem(b1470,0,None), x1394))
    val b1472_b1470 = withCtrl(lookup[UnitController]("x1397")) { withName(withCtx(WriteMem(lookup[StreamOut]("b1470"),x1394), "DotProduct.scala:32:16"), "b1472_b1470") } // StreamWrite(x1388,x1395,b857)
    // DefRhs(LhsSym(b1473,Some(b1471)),WriteMem,WrappedArray(LhsMem(b1471,0,None), Const(256)))
    val b1473_b1471 = withCtrl(lookup[UnitController]("x1397")) { withName(withCtx(WriteMem(lookup[StreamOut]("b1471"),Const(256)), "DotProduct.scala:32:16"), "b1473_b1471") } // StreamWrite(x1388,x1395,b857)
    // DefRhs(LhsSym(x1398,None),FringeDenseLoad,WrappedArray((dram,List(x1374)), (cmdStream,List(b1470, b1471)), (dataStream,List(x1389))))
    }; split6
    def split7 = {
    val x1398 = withCtrl(lookup[UnitController]("x1406")) { withName(withCtx(FringeDenseLoad(dram=List(lookup[DRAM]("x1374")),cmdStream=List(lookup[StreamOut]("b1470"), lookup[StreamOut]("b1471")),dataStream=List(lookup[StreamIn]("x1389"))), "DotProduct.scala:32:16"), "x1398") } // FringeDenseLoad(x1374,x1388,x1389)
    // DefRhs(LhsSym(x1399,None),Counter,WrappedArray((min,Const(0)), (max,Const(64)), (step,Const(1)), (par,16)))
    val x1399 = withCtrl(lookup[UnitController]("x1406")) { withName(withCtx(Counter(min=Const(0),max=Const(64),step=Const(1),par=16), "DotProduct.scala:32:16"), "x1399") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    // DefRhs(LhsSym(x1400,None),CounterChain,WrappedArray(List(x1399)))
    val x1400 = withCtrl(lookup[UnitController]("x1406")) { withName(withCtx(CounterChain(List(x1399)), "DotProduct.scala:32:16"), "x1400") } // CounterChainNew(List(x1399))
    // DefRhs(LhsSym(x1405,None),LoopController,WrappedArray((style,InnerPipe), (level,InnerControl), (cchain,x1400)))
    val x1405 = withCtrl(lookup[UnitController]("x1406")) { withName(withCtx(LoopController(style=InnerPipe,level=InnerControl,cchain=x1400), "DotProduct.scala:32:16"), "x1405") } // UnrolledForeach(List(b857),x1400,Block(Const(())),List(List(b875)),List(List(b876)))
    // DefRhs(LhsSym(b875,None),CounterIter,WrappedArray(x1399, None))
    val b875 = withCtrl(x1405) { withName(CounterIter(x1399,None), "b875") } // 
    // DefRhs(LhsSym(b876,None),Const[Boolean],WrappedArray(true))
    val b876 = withCtrl(x1405) { withName(Const[Boolean](true), "b876") } // 
    }; split7
    def split8 = {
    // DefRhs(LhsSym(x1401,None),OpDef,WrappedArray((op,BitAnd), (inputs,List(b876, b857))))
    val x1401 = withCtrl(lookup[LoopController]("x1405")) { withName(withCtx(OpDef(op=BitAnd,inputs=List(lookup[Const[Boolean]]("b876"), lookup[Const[Boolean]]("b857"))), "UnrollingBase.scala:28:66"), "x1401") } // And(b876,b857)
    // DefRhs(LhsSym(x1402,None),ReadMem,WrappedArray(LhsMem(x1389,0,None)))
    val x1402 = withCtrl(lookup[LoopController]("x1405")) { withName(withCtx(ReadMem(lookup[StreamIn]("x1389")), "DotProduct.scala:32:16"), "x1402") } // ParStreamRead(x1389,List(x1401))
    // AliasRhs(LhsSym(x1403,None),LhsSym(x1402,None))
    val x1403 = withName(x1402, "x1403") // VectorApply(x1402,0)
    // DefRhs(LhsSym(x1404,None),StoreBanks,WrappedArray(List(List(LhsMem(x1384,0,Some(0)))), List(b875), x1403))
    val x1404 = withCtrl(lookup[LoopController]("x1405")) { withName(withCtx(StoreBanks(List(List(lookup[SRAM]("x1384_d0_b0"))),List(lookup[CounterIter]("b875")),x1403), "DotProduct.scala:32:16"), "x1404") } // ParSRAMStore(x1384,List(List(b875)),List(x1403),List(x1401))
    // DefRhs(LhsSym(x1425,None),UnitController,WrappedArray((style,StreamPipe), (level,OuterControl)))
    val x1425 = withCtrl(lookup[UnitController]("x1426")) { withName(withCtx(UnitController(style=StreamPipe,level=OuterControl), "DotProduct.scala:33:16"), "x1425") } // UnitPipe(List(b857),Block(Const(())))
    // DefRhs(LhsMem(b1474,0,None),StreamOut,WrappedArray((field,"offset")))
    }; split8
    def split9 = {
    val b1474 = withCtrl(lookup[UnitController]("x1425")) { withAcc(withBD(withName(withCtx(StreamOut(field="offset"), "DotProduct.scala:33:16"), "b1474"), 1), false) } // StreamOutNew(BurstCmdBus)
    // DefRhs(LhsMem(b1475,0,None),StreamOut,WrappedArray((field,"size")))
    val b1475 = withCtrl(lookup[UnitController]("x1425")) { withAcc(withBD(withName(withCtx(StreamOut(field="size"), "DotProduct.scala:33:16"), "b1475"), 1), false) } // StreamOutNew(BurstCmdBus)
    // DefRhs(LhsMem(x1408,0,None),StreamIn,WrappedArray((field,"data")))
    val x1408 = withCtrl(lookup[UnitController]("x1425")) { withAcc(withBD(withName(withCtx(StreamIn(field="data"), "DotProduct.scala:33:16"), "x1408"), 1), false) } // StreamInNew(BurstDataBus())
    // DefRhs(LhsSym(x1416,None),UnitController,WrappedArray((style,SeqPipe), (level,InnerControl)))
    val x1416 = withCtrl(lookup[UnitController]("x1425")) { withName(withCtx(UnitController(style=SeqPipe,level=InnerControl), "DotProduct.scala:33:16"), "x1416") } // UnitPipe(List(b857),Block(x1415))
    // AliasRhs(LhsSym(x1409,None),LhsSym(b856,None))
    val x1409 = withName(lookup[CounterIter]("b856"), "x1409") // FixConvert(b856,TRUE,_32,_0) (Same Type. No op)
    // DefRhs(LhsSym(x1410,None),OpDef,WrappedArray((op,FixSla), (inputs,List(x1409, Const(2)))))
    val x1410 = withCtrl(x1416) { withName(withCtx(OpDef(op=FixSla,inputs=List(x1409, Const(2))), "DotProduct.scala:33:16"), "x1410") } // FixLsh(x1409,Const(2))
    }; split9
    def split10 = {
    // AliasRhs(LhsSym(x1411,None),LhsSym(x1410,None))
    val x1411 = withName(lookup[OpDef]("x1410"), "x1411") // FixConvert(x1410,TRUE,_64,_0)
    // DefRhs(LhsMem(x1412,0,None),DramAddress,WrappedArray((dram,x1376)))
    val x1412 = withCtrl(lookup[UnitController]("x1416")) { withName(withCtx(DramAddress(dram=lookup[DRAM]("x1376")), "DotProduct.scala:33:16"), "x1412") } // GetDRAMAddress(x1376)
    // DefRhs(LhsSym(x1413,None),OpDef,WrappedArray((op,FixAdd), (inputs,List(x1411, x1412))))
    val x1413 = withCtrl(lookup[UnitController]("x1416")) { withName(withCtx(OpDef(op=FixAdd,inputs=List(x1411, x1412)), "DotProduct.scala:33:16"), "x1413") } // SimpleStruct(ArrayBuffer((offset,x1413), (size,Const(256)), (isLoad,Const(true))))
    // x1414 = SimpleStruct(ArrayBuffer((offset,x1413), (size,Const(256)), (isLoad,Const(true))))
    // DefRhs(LhsSym(b1476,Some(b1474)),WriteMem,WrappedArray(LhsMem(b1474,0,None), x1413))
    val b1476_b1474 = withCtrl(lookup[UnitController]("x1416")) { withName(withCtx(WriteMem(lookup[StreamOut]("b1474"),x1413), "DotProduct.scala:33:16"), "b1476_b1474") } // StreamWrite(x1407,x1414,b857)
    // DefRhs(LhsSym(b1477,Some(b1475)),WriteMem,WrappedArray(LhsMem(b1475,0,None), Const(256)))
    val b1477_b1475 = withCtrl(lookup[UnitController]("x1416")) { withName(withCtx(WriteMem(lookup[StreamOut]("b1475"),Const(256)), "DotProduct.scala:33:16"), "b1477_b1475") } // StreamWrite(x1407,x1414,b857)
    }; split10
    def split11 = {
    // DefRhs(LhsSym(x1417,None),FringeDenseLoad,WrappedArray((dram,List(x1376)), (cmdStream,List(b1474, b1475)), (dataStream,List(x1408))))
    val x1417 = withCtrl(lookup[UnitController]("x1425")) { withName(withCtx(FringeDenseLoad(dram=List(lookup[DRAM]("x1376")),cmdStream=List(lookup[StreamOut]("b1474"), lookup[StreamOut]("b1475")),dataStream=List(lookup[StreamIn]("x1408"))), "DotProduct.scala:33:16"), "x1417") } // FringeDenseLoad(x1376,x1407,x1408)
    // DefRhs(LhsSym(x1418,None),Counter,WrappedArray((min,Const(0)), (max,Const(64)), (step,Const(1)), (par,16)))
    val x1418 = withCtrl(lookup[UnitController]("x1425")) { withName(withCtx(Counter(min=Const(0),max=Const(64),step=Const(1),par=16), "DotProduct.scala:33:16"), "x1418") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    // DefRhs(LhsSym(x1419,None),CounterChain,WrappedArray(List(x1418)))
    val x1419 = withCtrl(lookup[UnitController]("x1425")) { withName(withCtx(CounterChain(List(x1418)), "DotProduct.scala:33:16"), "x1419") } // CounterChainNew(List(x1418))
    // DefRhs(LhsSym(x1424,None),LoopController,WrappedArray((style,InnerPipe), (level,InnerControl), (cchain,x1419)))
    val x1424 = withCtrl(lookup[UnitController]("x1425")) { withName(withCtx(LoopController(style=InnerPipe,level=InnerControl,cchain=x1419), "DotProduct.scala:33:16"), "x1424") } // UnrolledForeach(List(b857),x1419,Block(Const(())),List(List(b896)),List(List(b897)))
    // DefRhs(LhsSym(b896,None),CounterIter,WrappedArray(x1418, None))
    val b896 = withCtrl(x1424) { withName(CounterIter(x1418,None), "b896") } // 
    // DefRhs(LhsSym(b897,None),Const[Boolean],WrappedArray(true))
    }; split11
    def split12 = {
    val b897 = withCtrl(lookup[LoopController]("x1424")) { withName(Const[Boolean](true), "b897") } // 
    // DefRhs(LhsSym(x1420,None),OpDef,WrappedArray((op,BitAnd), (inputs,List(b897, b857))))
    val x1420 = withCtrl(lookup[LoopController]("x1424")) { withName(withCtx(OpDef(op=BitAnd,inputs=List(lookup[Const[Boolean]]("b897"), lookup[Const[Boolean]]("b857"))), "UnrollingBase.scala:28:66"), "x1420") } // And(b897,b857)
    // DefRhs(LhsSym(x1421,None),ReadMem,WrappedArray(LhsMem(x1408,0,None)))
    val x1421 = withCtrl(lookup[LoopController]("x1424")) { withName(withCtx(ReadMem(lookup[StreamIn]("x1408")), "DotProduct.scala:33:16"), "x1421") } // ParStreamRead(x1408,List(x1420))
    // AliasRhs(LhsSym(x1422,None),LhsSym(x1421,None))
    val x1422 = withName(x1421, "x1422") // VectorApply(x1421,0)
    // DefRhs(LhsSym(x1423,None),StoreBanks,WrappedArray(List(List(LhsMem(x1385,0,Some(0)))), List(b896), x1422))
    val x1423 = withCtrl(lookup[LoopController]("x1424")) { withName(withCtx(StoreBanks(List(List(lookup[SRAM]("x1385_d0_b0"))),List(lookup[CounterIter]("b896")),x1422), "DotProduct.scala:33:16"), "x1423") } // ParSRAMStore(x1385,List(List(b896)),List(x1422),List(x1420))
    // DefRhs(LhsMem(x1427,0,None),Reg,WrappedArray((init,Some(0))))
    val x1427_d0 = withCtrl(lookup[LoopController]("x1447")) { withAcc(withBD(withName(withCtx(Reg(init=Some(0)), "DotProduct.scala:35:22"), "x1427_d0"), 2), false) } // RegNew(Const(0))
    }; split12
    def split13 = {
    // DefRhs(LhsMem(x1427,1,None),Reg,WrappedArray((init,Some(0))))
    val x1427_d1 = withCtrl(lookup[LoopController]("x1447")) { withAcc(withBD(withName(withCtx(Reg(init=Some(0)), "DotProduct.scala:35:22"), "x1427_d1"), 1), true) } // RegNew(Const(0))
    // DefRhs(LhsSym(x1428,None),Counter,WrappedArray((min,Const(0)), (max,Const(64)), (step,Const(1)), (par,16)))
    val x1428 = withCtrl(lookup[LoopController]("x1447")) { withName(withCtx(Counter(min=Const(0),max=Const(64),step=Const(1),par=16), "DotProduct.scala:35:36"), "x1428") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    // DefRhs(LhsSym(x1429,None),CounterChain,WrappedArray(List(x1428)))
    val x1429 = withCtrl(lookup[LoopController]("x1447")) { withName(withCtx(CounterChain(List(x1428)), "DotProduct.scala:35:71"), "x1429") } // CounterChainNew(List(x1428))
    // DefRhs(LhsSym(x1440,None),LoopController,WrappedArray((style,InnerPipe), (level,InnerControl), (cchain,x1429)))
    val x1440 = withCtrl(lookup[LoopController]("x1447")) { withName(withCtx(LoopController(style=InnerPipe,level=InnerControl,cchain=x1429), "DotProduct.scala:35:71"), "x1440") } // UnrolledReduce(List(b857),x1429,x1427,Block((x1427) => Const(())),List(List(b908)),List(List(b909)))
    // DefRhs(LhsSym(b908,None),CounterIter,WrappedArray(x1428, None))
    val b908 = withCtrl(x1440) { withName(CounterIter(x1428,None), "b908") } // 
    // DefRhs(LhsSym(b909,None),Const[Boolean],WrappedArray(true))
    }; split13
    def split14 = {
    val b909 = withCtrl(lookup[LoopController]("x1440")) { withName(Const[Boolean](true), "b909") } // 
    // DefRhs(LhsSym(x1430,None),OpDef,WrappedArray((op,BitAnd), (inputs,List(b909, b857))))
    val x1430 = withCtrl(lookup[LoopController]("x1440")) { withName(withCtx(OpDef(op=BitAnd,inputs=List(lookup[Const[Boolean]]("b909"), lookup[Const[Boolean]]("b857"))), "UnrollingBase.scala:28:66"), "x1430") } // And(b909,b857)
    // DefRhs(LhsSym(x1431,None),LoadBanks,WrappedArray(List(LhsMem(x1384,0,Some(0))), List(b908)))
    val x1431 = withCtrl(lookup[LoopController]("x1440")) { withName(withCtx(LoadBanks(List(lookup[SRAM]("x1384_d0_b0")),List(lookup[CounterIter]("b908"))), "DotProduct.scala:35:54"), "x1431") } // ParSRAMLoad(x1384,List(List(b908)),List(x1430))
    // AliasRhs(LhsSym(x1432,None),LhsSym(x1431,None))
    val x1432 = withName(x1431, "x1432") // VectorApply(x1431,0)
    // DefRhs(LhsSym(x1433,None),LoadBanks,WrappedArray(List(LhsMem(x1385,0,Some(0))), List(b908)))
    val x1433 = withCtrl(lookup[LoopController]("x1440")) { withName(withCtx(LoadBanks(List(lookup[SRAM]("x1385_d0_b0")),List(lookup[CounterIter]("b908"))), "DotProduct.scala:35:65"), "x1433") } // ParSRAMLoad(x1385,List(List(b908)),List(x1430))
    // AliasRhs(LhsSym(x1434,None),LhsSym(x1433,None))
    val x1434 = withName(x1433, "x1434") // VectorApply(x1433,0)
    }; split14
    def split15 = {
    // DefRhs(LhsSym(x1435,None),OpDef,WrappedArray((op,FixMul), (inputs,List(x1432, x1434))))
    val x1435 = withCtrl(lookup[LoopController]("x1440")) { withName(withCtx(OpDef(op=FixMul,inputs=List(lookup[LoadBanks]("x1432"), lookup[LoadBanks]("x1434"))), "DotProduct.scala:35:59"), "x1435") } // FixMul(x1432,x1434)
    // DefRhs(LhsSym(x1436,None),ReadMem,WrappedArray(LhsMem(x1427,1,None)))
    val x1436 = withCtrl(lookup[LoopController]("x1440")) { withName(withCtx(ReadMem(lookup[Reg]("x1427_d1")), "DotProduct.scala:35:71"), "x1436") } // RegRead(x1427)
    // DefRhs(LhsSym(x1437,None),OpDef,WrappedArray((op,FixEql), (inputs,List(b908, Const(0)))))
    val x1437 = withCtrl(lookup[LoopController]("x1440")) { withName(withCtx(OpDef(op=FixEql,inputs=List(lookup[CounterIter]("b908"), Const(0))), "DotProduct.scala:35:71"), "x1437") } // FixEql(b908,Const(0))
    // DefRhs(LhsSym(x1438,None),ReduceAccumOp,WrappedArray((op,FixAdd), (input,x1435), (accum,x1436)))
    val x1438 = withCtrl(lookup[LoopController]("x1440")) { withName(withCtx(ReduceAccumOp(op=FixAdd,input=x1435,accum=x1436), "DotProduct.scala:35:73"), "x1438") } // FixAdd(x1435,x1436)
    // DefRhs(LhsSym(x1439,Some(x1427_d0)),WriteMem,WrappedArray(LhsMem(x1427,0,None), x1438))
    val x1439_x1427_d0 = withCtrl(lookup[LoopController]("x1440")) { withDeps(withName(withCtx(WriteMem(lookup[Reg]("x1427_d0"),x1438), "DotProduct.scala:35:71"), "x1439_x1427_d0"), List(x1436)) } // RegWrite(x1427,x1438,b857)
    // DefRhs(LhsSym(x1439,Some(x1427_d1)),WriteMem,WrappedArray(LhsMem(x1427,1,None), x1438))
    }; split15
    def split16 = {
    val x1439_x1427_d1 = withCtrl(lookup[LoopController]("x1440")) { withDeps(withName(withCtx(WriteMem(lookup[Reg]("x1427_d1"),lookup[ReduceAccumOp]("x1438")), "DotProduct.scala:35:71"), "x1439_x1427_d1"), List(lookup[ReadMem]("x1436"))) } // RegWrite(x1427,x1438,b857)
    // DefRhs(LhsSym(x1446,None),UnitController,WrappedArray((style,SeqPipe), (level,InnerControl)))
    val x1446 = withCtrl(lookup[LoopController]("x1447")) { withName(withCtx(UnitController(style=SeqPipe,level=InnerControl), "DotProduct.scala:36:8"), "x1446") } // UnitPipe(List(Const(true)),Block(x1445))
    // DefRhs(LhsSym(x1441,None),ReadMem,WrappedArray(LhsMem(x1380,1,None)))
    val x1441 = withCtrl(x1446) { withName(withCtx(ReadMem(lookup[Reg]("x1380_d1")), "DotProduct.scala:36:8"), "x1441") } // RegRead(x1380)
    // DefRhs(LhsSym(x1442,None),OpDef,WrappedArray((op,FixEql), (inputs,List(b856, Const(0)))))
    val x1442 = withCtrl(x1446) { withName(withCtx(OpDef(op=FixEql,inputs=List(lookup[CounterIter]("b856"), Const(0))), "DotProduct.scala:36:8"), "x1442") } // FixEql(b856,Const(0))
    // DefRhs(LhsSym(x1443,None),ReadMem,WrappedArray(LhsMem(x1427,0,None)))
    val x1443 = withCtrl(x1446) { withName(withCtx(ReadMem(lookup[Reg]("x1427_d0")), "DotProduct.scala:35:71"), "x1443") } // RegRead(x1427)
    // DefRhs(LhsSym(x1444,None),OpDef,WrappedArray((op,FixAdd), (inputs,List(x1443, x1441))))
    val x1444 = withCtrl(x1446) { withName(withCtx(OpDef(op=FixAdd,inputs=List(x1443, x1441)), "DotProduct.scala:36:10"), "x1444") } // FixAdd(x1443,x1441)
    }; split16
    def split17 = {
    // DefRhs(LhsSym(x1445,Some(x1380_d0)),WriteMem,WrappedArray(LhsMem(x1380,0,None), x1444))
    val x1445_x1380_d0 = withCtrl(lookup[UnitController]("x1446")) { withDeps(withName(withCtx(WriteMem(lookup[Reg]("x1380_d0"),lookup[OpDef]("x1444")), "DotProduct.scala:36:8"), "x1445_x1380_d0"), List(lookup[ReadMem]("x1441"))) } // RegWrite(x1380,x1444,Const(true))
    // DefRhs(LhsSym(x1445,Some(x1380_d1)),WriteMem,WrappedArray(LhsMem(x1380,1,None), x1444))
    val x1445_x1380_d1 = withCtrl(lookup[UnitController]("x1446")) { withDeps(withName(withCtx(WriteMem(lookup[Reg]("x1380_d1"),lookup[OpDef]("x1444")), "DotProduct.scala:36:8"), "x1445_x1380_d1"), List(lookup[ReadMem]("x1441"))) } // RegWrite(x1380,x1444,Const(true))
    // DefRhs(LhsSym(x1450,None),UnitController,WrappedArray((style,SeqPipe), (level,InnerControl)))
    val x1450 = withCtrl(lookup[UnitController]("x1451")) { withName(withCtx(UnitController(style=SeqPipe,level=InnerControl), "DotProduct.scala:27:11"), "x1450") } // UnitPipe(List(Const(true)),Block(Const(())))
    // DefRhs(LhsSym(x1448,None),ReadMem,WrappedArray(LhsMem(x1380,0,None)))
    val x1448 = withCtrl(x1450) { withName(withCtx(ReadMem(lookup[Reg]("x1380_d0")), "DotProduct.scala:36:8"), "x1448") } // RegRead(x1380)
    // DefRhs(LhsSym(x1449,Some(x1377)),WriteMem,WrappedArray(LhsMem(x1377,0,None), x1448))
    val x1449_x1377 = withCtrl(x1450) { withName(withCtx(WriteMem(lookup[ArgOut]("x1377"),x1448), "DotProduct.scala:28:11"), "x1449_x1377") } // RegWrite(x1377,x1448,Const(true))
    }; split17
    
  }
}
