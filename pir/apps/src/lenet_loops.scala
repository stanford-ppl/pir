import pir._
import pir.node._
import arch._
import prism.enums._

object lenet_loops extends PIRApp {
  def main(implicit design:PIRDesign) = {
    import design.pirmeta._
    val x9253 = withCtrl(design.top.topController) { DRAM(dims=List(Const(20), Const(1), Const(32))).name("x9253").srcCtx("lenet_loops.scala:30:26:c0_DRAM") } // x9253 = DRAMNew(ArrayBuffer(Const(20), Const(1), Const(32)),Const(0))
    val x9254 = withCtrl(design.top.topController) { DRAM(dims=List(Const(17), Const(28), Const(32))).name("x9254").srcCtx("lenet_loops.scala:31:26:i0_DRAM") } // x9254 = DRAMNew(ArrayBuffer(Const(17), Const(28), Const(32)),Const(0))
    val x9255 = withCtrl(design.top.topController) { DRAM(dims=List(Const(32))).name("x9255").srcCtx("lenet_loops.scala:32:26:c1_DRAM") } // x9255 = DRAMNew(ArrayBuffer(Const(32)),Const(0))
    val x9256 = withCtrl(design.top.topController) { DRAM(dims=List(Const(50), Const(512))).name("x9256").srcCtx("lenet_loops.scala:33:26:c2_DRAM") } // x9256 = DRAMNew(ArrayBuffer(Const(50), Const(512)),Const(0))
    val x9257 = withCtrl(design.top.topController) { DRAM(dims=List(Const(64))).name("x9257").srcCtx("lenet_loops.scala:34:26:c3_DRAM") } // x9257 = DRAMNew(ArrayBuffer(Const(64)),Const(0))
    val x9258 = withCtrl(design.top.topController) { DRAM(dims=List(Const(100), Const(4000))).name("x9258").srcCtx("lenet_loops.scala:35:26:c4_DRAM") } // x9258 = DRAMNew(ArrayBuffer(Const(100), Const(4000)),Const(0))
    val x9259 = withCtrl(design.top.topController) { DRAM(dims=List(Const(512))).name("x9259").srcCtx("lenet_loops.scala:37:26:c5_DRAM") } // x9259 = DRAMNew(ArrayBuffer(Const(512)),Const(0))
    val x9260 = withCtrl(design.top.topController) { DRAM(dims=List(Const(10), Const(512))).name("x9260").srcCtx("lenet_loops.scala:38:26:c6_DRAM") } // x9260 = DRAMNew(ArrayBuffer(Const(10), Const(512)),Const(0))
    val x9261 = withCtrl(design.top.topController) { DRAM(dims=List(Const(32))).name("x9261").srcCtx("lenet_loops.scala:40:26:c7_DRAM") } // x9261 = DRAMNew(ArrayBuffer(Const(32)),Const(0))
    val x9262 = withCtrl(design.top.topController) { DRAM(dims=List(Const(17), Const(32))).name("x9262").srcCtx("lenet_loops.scala:41:28:tmp5_DRAM") } // x9262 = DRAMNew(ArrayBuffer(Const(17), Const(32)),Const(0))
    val x9924 = withCtrl(design.top.topController) { UnitController(style=SeqPipe, level=OuterControl).name("x9924").srcCtx("lenet_loops.scala:69:11") } // Hwblock(Block(Const(())),false)
    val x9382_d0_b0 = withCtrl(x9924) { SRAM(size=32, banking=Strided(banks=1, stride=1)).name("x9382_d0_b0").srcCtx("lenet_loops.scala:71:28:c1_SRAM") } // x9382 = SRAMNew(ArrayBuffer(Const(32)))
    isAccum(x9382_d0_b0) = false
    bufferDepthOf(x9382_d0_b0) = 1
    staticDimsOf(x9382_d0_b0) = List(32)
    val x9400 = withCtrl(x9924) { UnitController(style=StreamPipe, level=OuterControl).name("x9400").srcCtx("lenet_loops.scala:72:15") } // UnitPipe(List(Const(true)),Block(Const(())))
    val b9962 = withCtrl(x9400) { StreamOut(field="offset").name("b9962").srcCtx("lenet_loops.scala:72:15") } // x9383 = StreamOutNew(BurstCmdBus)
    isAccum(b9962) = false
    bufferDepthOf(b9962) = 1
    val b9963 = withCtrl(x9400) { StreamOut(field="size").name("b9963").srcCtx("lenet_loops.scala:72:15") } // x9383 = StreamOutNew(BurstCmdBus)
    isAccum(b9963) = false
    bufferDepthOf(b9963) = 1
    val x9384 = withCtrl(x9400) { StreamIn(field="data").name("x9384").srcCtx("lenet_loops.scala:72:15") } // x9384 = StreamInNew(BurstDataBus())
    isAccum(x9384) = false
    bufferDepthOf(x9384) = 1
    val x9392 = withCtrl(x9400) { UnitController(style=SeqPipe, level=InnerControl).name("x9392").srcCtx("lenet_loops.scala:72:15") } // UnitPipe(List(Const(true)),Block(x9391))
    val x9385 = withCtrl(x9392) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x9386 = withCtrl(x9392) { OpDef(op=FixSla, inputs=List(x9385, Const(2))).name("x9386").srcCtx("lenet_loops.scala:72:15") } // FixLsh(x9385,Const(2))
    val x9387 = withCtrl(x9392) { x9386 } // FixConvert(x9386,TRUE,_64,_0)
    val x9388 = withCtrl(x9392) { DramAddress(x9255).name("x9388").srcCtx("lenet_loops.scala:72:15") } // GetDRAMAddress(x9255)
    val x9390_x9389 = withCtrl(x9392) { OpDef(op=FixAdd, inputs=List(x9387, x9388)).name("x9390_x9389").srcCtx("lenet_loops.scala:72:15") } // FixAdd(x9387,x9388)
    // x9390 = SimpleStruct(ArrayBuffer((offset,x9389), (size,Const(128)), (isLoad,Const(true))))
    val x9391_b9964_b9962 = withCtrl(x9392) { WriteMem(b9962, x9390_x9389).name("x9391_b9964_b9962").srcCtx("lenet_loops.scala:72:15") } // StreamWrite(x9383,x9390,Const(true))
    val x9391_b9965_b9963 = withCtrl(x9392) { WriteMem(b9963, Const(128)).name("x9391_b9965_b9963").srcCtx("lenet_loops.scala:72:15") } // StreamWrite(x9383,x9390,Const(true))
    val x9393 = withCtrl(x9400) { FringeDenseLoad(dram=List(x9255), cmdStream=List(b9962, b9963), dataStream=List(x9384)).name("x9393").srcCtx("lenet_loops.scala:72:15") } // FringeDenseLoad(x9255,x9383,x9384)
    val x9394 = withCtrl(x9400) { Counter(min=Const(0), max=Const(32), step=Const(1), par=1).name("x9394").srcCtx("lenet_loops.scala:72:15") } // CounterNew(Const(0),Const(32),Const(1),Const(1))
    val x9395 = withCtrl(x9400) { CounterChain(List(x9394)).name("x9395").srcCtx("lenet_loops.scala:72:15") } // CounterChainNew(List(x9394))
    val x9399 = withCtrl(x9400) { LoopController(style=InnerPipe, level=InnerControl, cchain=x9395).name("x9399").srcCtx("lenet_loops.scala:72:15") } // UnrolledForeach(List(Const(true)),x9395,Block(Const(())),List(List(b5604)),List(List(b5605)))
    val b5604 = withCtrl(x9399) { CounterIter(x9394, None).name("b5604") } // b5604
    val b5605 = withCtrl(x9399) { Const(true).name("b5605") } // b5605
    val x9396_x9396 = withCtrl(x9399) { ReadMem(x9384).name("x9396_x9396").srcCtx("lenet_loops.scala:72:15") } // ParStreamRead(x9384,List(b5605))
    val x9397_x9397 = withCtrl(x9399) { x9396_x9396 } // VectorApply(x9396,0)
    val x9398 = withCtrl(x9399) { StoreBanks(List(List(x9382_d0_b0)), List(b5604), x9397_x9397).name("x9398").srcCtx("lenet_loops.scala:72:15") } // ParSRAMStore(x9382,List(List(b5604)),List(x9397),List(b5605))
    val x9401_d0_b0 = withCtrl(x9924) { SRAM(size=64, banking=Strided(banks=1, stride=1)).name("x9401_d0_b0").srcCtx("lenet_loops.scala:74:28:c3_SRAM") } // x9401 = SRAMNew(ArrayBuffer(Const(64)))
    isAccum(x9401_d0_b0) = false
    bufferDepthOf(x9401_d0_b0) = 1
    staticDimsOf(x9401_d0_b0) = List(64)
    val x9419 = withCtrl(x9924) { UnitController(style=StreamPipe, level=OuterControl).name("x9419").srcCtx("lenet_loops.scala:75:15") } // UnitPipe(List(Const(true)),Block(Const(())))
    val b9966 = withCtrl(x9419) { StreamOut(field="offset").name("b9966").srcCtx("lenet_loops.scala:75:15") } // x9402 = StreamOutNew(BurstCmdBus)
    isAccum(b9966) = false
    bufferDepthOf(b9966) = 1
    val b9967 = withCtrl(x9419) { StreamOut(field="size").name("b9967").srcCtx("lenet_loops.scala:75:15") } // x9402 = StreamOutNew(BurstCmdBus)
    isAccum(b9967) = false
    bufferDepthOf(b9967) = 1
    val x9403 = withCtrl(x9419) { StreamIn(field="data").name("x9403").srcCtx("lenet_loops.scala:75:15") } // x9403 = StreamInNew(BurstDataBus())
    isAccum(x9403) = false
    bufferDepthOf(x9403) = 1
    val x9411 = withCtrl(x9419) { UnitController(style=SeqPipe, level=InnerControl).name("x9411").srcCtx("lenet_loops.scala:75:15") } // UnitPipe(List(Const(true)),Block(x9410))
    val x9404 = withCtrl(x9411) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x9405 = withCtrl(x9411) { OpDef(op=FixSla, inputs=List(x9404, Const(2))).name("x9405").srcCtx("lenet_loops.scala:75:15") } // FixLsh(x9404,Const(2))
    val x9406 = withCtrl(x9411) { x9405 } // FixConvert(x9405,TRUE,_64,_0)
    val x9407 = withCtrl(x9411) { DramAddress(x9257).name("x9407").srcCtx("lenet_loops.scala:75:15") } // GetDRAMAddress(x9257)
    val x9409_x9408 = withCtrl(x9411) { OpDef(op=FixAdd, inputs=List(x9406, x9407)).name("x9409_x9408").srcCtx("lenet_loops.scala:75:15") } // FixAdd(x9406,x9407)
    // x9409 = SimpleStruct(ArrayBuffer((offset,x9408), (size,Const(256)), (isLoad,Const(true))))
    val x9410_b9968_b9966 = withCtrl(x9411) { WriteMem(b9966, x9409_x9408).name("x9410_b9968_b9966").srcCtx("lenet_loops.scala:75:15") } // StreamWrite(x9402,x9409,Const(true))
    val x9410_b9969_b9967 = withCtrl(x9411) { WriteMem(b9967, Const(256)).name("x9410_b9969_b9967").srcCtx("lenet_loops.scala:75:15") } // StreamWrite(x9402,x9409,Const(true))
    val x9412 = withCtrl(x9419) { FringeDenseLoad(dram=List(x9257), cmdStream=List(b9966, b9967), dataStream=List(x9403)).name("x9412").srcCtx("lenet_loops.scala:75:15") } // FringeDenseLoad(x9257,x9402,x9403)
    val x9413 = withCtrl(x9419) { Counter(min=Const(0), max=Const(64), step=Const(1), par=1).name("x9413").srcCtx("lenet_loops.scala:75:15") } // CounterNew(Const(0),Const(64),Const(1),Const(1))
    val x9414 = withCtrl(x9419) { CounterChain(List(x9413)).name("x9414").srcCtx("lenet_loops.scala:75:15") } // CounterChainNew(List(x9413))
    val x9418 = withCtrl(x9419) { LoopController(style=InnerPipe, level=InnerControl, cchain=x9414).name("x9418").srcCtx("lenet_loops.scala:75:15") } // UnrolledForeach(List(Const(true)),x9414,Block(Const(())),List(List(b5625)),List(List(b5626)))
    val b5625 = withCtrl(x9418) { CounterIter(x9413, None).name("b5625") } // b5625
    val b5626 = withCtrl(x9418) { Const(true).name("b5626") } // b5626
    val x9415_x9415 = withCtrl(x9418) { ReadMem(x9403).name("x9415_x9415").srcCtx("lenet_loops.scala:75:15") } // ParStreamRead(x9403,List(b5626))
    val x9416_x9416 = withCtrl(x9418) { x9415_x9415 } // VectorApply(x9415,0)
    val x9417 = withCtrl(x9418) { StoreBanks(List(List(x9401_d0_b0)), List(b5625), x9416_x9416).name("x9417").srcCtx("lenet_loops.scala:75:15") } // ParSRAMStore(x9401,List(List(b5625)),List(x9416),List(b5626))
    val x9420_d0_b0 = withCtrl(x9924) { SRAM(size=512, banking=Strided(banks=1, stride=1)).name("x9420_d0_b0").srcCtx("lenet_loops.scala:77:28:c5_SRAM") } // x9420 = SRAMNew(ArrayBuffer(Const(512)))
    isAccum(x9420_d0_b0) = false
    bufferDepthOf(x9420_d0_b0) = 1
    staticDimsOf(x9420_d0_b0) = List(512)
    val x9438 = withCtrl(x9924) { UnitController(style=StreamPipe, level=OuterControl).name("x9438").srcCtx("lenet_loops.scala:78:15") } // UnitPipe(List(Const(true)),Block(Const(())))
    val b9970 = withCtrl(x9438) { StreamOut(field="offset").name("b9970").srcCtx("lenet_loops.scala:78:15") } // x9421 = StreamOutNew(BurstCmdBus)
    isAccum(b9970) = false
    bufferDepthOf(b9970) = 1
    val b9971 = withCtrl(x9438) { StreamOut(field="size").name("b9971").srcCtx("lenet_loops.scala:78:15") } // x9421 = StreamOutNew(BurstCmdBus)
    isAccum(b9971) = false
    bufferDepthOf(b9971) = 1
    val x9422 = withCtrl(x9438) { StreamIn(field="data").name("x9422").srcCtx("lenet_loops.scala:78:15") } // x9422 = StreamInNew(BurstDataBus())
    isAccum(x9422) = false
    bufferDepthOf(x9422) = 1
    val x9430 = withCtrl(x9438) { UnitController(style=SeqPipe, level=InnerControl).name("x9430").srcCtx("lenet_loops.scala:78:15") } // UnitPipe(List(Const(true)),Block(x9429))
    val x9423 = withCtrl(x9430) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x9424 = withCtrl(x9430) { OpDef(op=FixSla, inputs=List(x9423, Const(2))).name("x9424").srcCtx("lenet_loops.scala:78:15") } // FixLsh(x9423,Const(2))
    val x9425 = withCtrl(x9430) { x9424 } // FixConvert(x9424,TRUE,_64,_0)
    val x9426 = withCtrl(x9430) { DramAddress(x9259).name("x9426").srcCtx("lenet_loops.scala:78:15") } // GetDRAMAddress(x9259)
    val x9428_x9427 = withCtrl(x9430) { OpDef(op=FixAdd, inputs=List(x9425, x9426)).name("x9428_x9427").srcCtx("lenet_loops.scala:78:15") } // FixAdd(x9425,x9426)
    // x9428 = SimpleStruct(ArrayBuffer((offset,x9427), (size,Const(2048)), (isLoad,Const(true))))
    val x9429_b9972_b9970 = withCtrl(x9430) { WriteMem(b9970, x9428_x9427).name("x9429_b9972_b9970").srcCtx("lenet_loops.scala:78:15") } // StreamWrite(x9421,x9428,Const(true))
    val x9429_b9973_b9971 = withCtrl(x9430) { WriteMem(b9971, Const(2048)).name("x9429_b9973_b9971").srcCtx("lenet_loops.scala:78:15") } // StreamWrite(x9421,x9428,Const(true))
    val x9431 = withCtrl(x9438) { FringeDenseLoad(dram=List(x9259), cmdStream=List(b9970, b9971), dataStream=List(x9422)).name("x9431").srcCtx("lenet_loops.scala:78:15") } // FringeDenseLoad(x9259,x9421,x9422)
    val x9432 = withCtrl(x9438) { Counter(min=Const(0), max=Const(512), step=Const(1), par=1).name("x9432").srcCtx("lenet_loops.scala:78:15") } // CounterNew(Const(0),Const(512),Const(1),Const(1))
    val x9433 = withCtrl(x9438) { CounterChain(List(x9432)).name("x9433").srcCtx("lenet_loops.scala:78:15") } // CounterChainNew(List(x9432))
    val x9437 = withCtrl(x9438) { LoopController(style=InnerPipe, level=InnerControl, cchain=x9433).name("x9437").srcCtx("lenet_loops.scala:78:15") } // UnrolledForeach(List(Const(true)),x9433,Block(Const(())),List(List(b5646)),List(List(b5647)))
    val b5646 = withCtrl(x9437) { CounterIter(x9432, None).name("b5646") } // b5646
    val b5647 = withCtrl(x9437) { Const(true).name("b5647") } // b5647
    val x9434_x9434 = withCtrl(x9437) { ReadMem(x9422).name("x9434_x9434").srcCtx("lenet_loops.scala:78:15") } // ParStreamRead(x9422,List(b5647))
    val x9435_x9435 = withCtrl(x9437) { x9434_x9434 } // VectorApply(x9434,0)
    val x9436 = withCtrl(x9437) { StoreBanks(List(List(x9420_d0_b0)), List(b5646), x9435_x9435).name("x9436").srcCtx("lenet_loops.scala:78:15") } // ParSRAMStore(x9420,List(List(b5646)),List(x9435),List(b5647))
    val x9439_d0_b0 = withCtrl(x9924) { SRAM(size=32, banking=Strided(banks=1, stride=1)).name("x9439_d0_b0").srcCtx("lenet_loops.scala:80:28:c7_SRAM") } // x9439 = SRAMNew(ArrayBuffer(Const(32)))
    isAccum(x9439_d0_b0) = false
    bufferDepthOf(x9439_d0_b0) = 1
    staticDimsOf(x9439_d0_b0) = List(32)
    val x9457 = withCtrl(x9924) { UnitController(style=StreamPipe, level=OuterControl).name("x9457").srcCtx("lenet_loops.scala:81:15") } // UnitPipe(List(Const(true)),Block(Const(())))
    val b9974 = withCtrl(x9457) { StreamOut(field="offset").name("b9974").srcCtx("lenet_loops.scala:81:15") } // x9440 = StreamOutNew(BurstCmdBus)
    isAccum(b9974) = false
    bufferDepthOf(b9974) = 1
    val b9975 = withCtrl(x9457) { StreamOut(field="size").name("b9975").srcCtx("lenet_loops.scala:81:15") } // x9440 = StreamOutNew(BurstCmdBus)
    isAccum(b9975) = false
    bufferDepthOf(b9975) = 1
    val x9441 = withCtrl(x9457) { StreamIn(field="data").name("x9441").srcCtx("lenet_loops.scala:81:15") } // x9441 = StreamInNew(BurstDataBus())
    isAccum(x9441) = false
    bufferDepthOf(x9441) = 1
    val x9449 = withCtrl(x9457) { UnitController(style=SeqPipe, level=InnerControl).name("x9449").srcCtx("lenet_loops.scala:81:15") } // UnitPipe(List(Const(true)),Block(x9448))
    val x9442 = withCtrl(x9449) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x9443 = withCtrl(x9449) { OpDef(op=FixSla, inputs=List(x9442, Const(2))).name("x9443").srcCtx("lenet_loops.scala:81:15") } // FixLsh(x9442,Const(2))
    val x9444 = withCtrl(x9449) { x9443 } // FixConvert(x9443,TRUE,_64,_0)
    val x9445 = withCtrl(x9449) { DramAddress(x9261).name("x9445").srcCtx("lenet_loops.scala:81:15") } // GetDRAMAddress(x9261)
    val x9447_x9446 = withCtrl(x9449) { OpDef(op=FixAdd, inputs=List(x9444, x9445)).name("x9447_x9446").srcCtx("lenet_loops.scala:81:15") } // FixAdd(x9444,x9445)
    // x9447 = SimpleStruct(ArrayBuffer((offset,x9446), (size,Const(128)), (isLoad,Const(true))))
    val x9448_b9976_b9974 = withCtrl(x9449) { WriteMem(b9974, x9447_x9446).name("x9448_b9976_b9974").srcCtx("lenet_loops.scala:81:15") } // StreamWrite(x9440,x9447,Const(true))
    val x9448_b9977_b9975 = withCtrl(x9449) { WriteMem(b9975, Const(128)).name("x9448_b9977_b9975").srcCtx("lenet_loops.scala:81:15") } // StreamWrite(x9440,x9447,Const(true))
    val x9450 = withCtrl(x9457) { FringeDenseLoad(dram=List(x9261), cmdStream=List(b9974, b9975), dataStream=List(x9441)).name("x9450").srcCtx("lenet_loops.scala:81:15") } // FringeDenseLoad(x9261,x9440,x9441)
    val x9451 = withCtrl(x9457) { Counter(min=Const(0), max=Const(32), step=Const(1), par=1).name("x9451").srcCtx("lenet_loops.scala:81:15") } // CounterNew(Const(0),Const(32),Const(1),Const(1))
    val x9452 = withCtrl(x9457) { CounterChain(List(x9451)).name("x9452").srcCtx("lenet_loops.scala:81:15") } // CounterChainNew(List(x9451))
    val x9456 = withCtrl(x9457) { LoopController(style=InnerPipe, level=InnerControl, cchain=x9452).name("x9456").srcCtx("lenet_loops.scala:81:15") } // UnrolledForeach(List(Const(true)),x9452,Block(Const(())),List(List(b5667)),List(List(b5668)))
    val b5667 = withCtrl(x9456) { CounterIter(x9451, None).name("b5667") } // b5667
    val b5668 = withCtrl(x9456) { Const(true).name("b5668") } // b5668
    val x9453_x9453 = withCtrl(x9456) { ReadMem(x9441).name("x9453_x9453").srcCtx("lenet_loops.scala:81:15") } // ParStreamRead(x9441,List(b5668))
    val x9454_x9454 = withCtrl(x9456) { x9453_x9453 } // VectorApply(x9453,0)
    val x9455 = withCtrl(x9456) { StoreBanks(List(List(x9439_d0_b0)), List(b5667), x9454_x9454).name("x9455").srcCtx("lenet_loops.scala:81:15") } // ParSRAMStore(x9439,List(List(b5667)),List(x9454),List(b5668))
    val x9458 = withCtrl(x9924) { Counter(min=Const(0), max=Const(17), step=Const(1), par=1).name("x9458").srcCtx("lenet_loops.scala:83:31") } // CounterNew(Const(0),Const(17),Const(1),Const(1))
    val x9459 = withCtrl(x9924) { CounterChain(List(x9458)).name("x9459").srcCtx("lenet_loops.scala:83:46") } // CounterChainNew(List(x9458))
    val x9923 = withCtrl(x9924) { LoopController(style=MetaPipe, level=OuterControl, cchain=x9459).name("x9923").srcCtx("lenet_loops.scala:83:46") } // UnrolledForeach(List(Const(true)),x9459,Block(Const(())),List(List(b5676)),List(List(b5677)))
    val b5676 = withCtrl(x9923) { CounterIter(x9458, Some(0)).name("b5676") } // b5676
    val b5677 = withCtrl(x9923) { Const(true).name("b5677") } // b5677
    val x9460_d0_b0 = withCtrl(x9923) { SRAM(size=896, banking=Strided(banks=1, stride=32)).name("x9460_d0_b0").srcCtx("lenet_loops.scala:86:30:i0_SRAM") } // x9460 = SRAMNew(ArrayBuffer(Const(28), Const(32)))
    isAccum(x9460_d0_b0) = false
    bufferDepthOf(x9460_d0_b0) = 2
    staticDimsOf(x9460_d0_b0) = List(28, 32)
    val x9462 = withCtrl(x9923) { UnitController(style=SeqPipe, level=InnerControl).name("x9462").srcCtx("lenet_loops.scala:83:46") } // UnitPipe(List(b5677),Block(Const(())))
    val x9461 = withCtrl(x9462) { OpDef(op=FixAdd, inputs=List(b5676, Const(1))).name("x9461").srcCtx("lenet_loops.scala:87:29") } // FixAdd(b5676,Const(1))
    val x9463 = withCtrl(x9923) { Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x9463").srcCtx("lenet_loops.scala:87:17") } // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x9464 = withCtrl(x9923) { Counter(min=Const(0), max=Const(28), step=Const(1), par=1).name("x9464").srcCtx("lenet_loops.scala:87:17") } // CounterNew(Const(0),Const(28),Const(1),Const(1))
    val x9465 = withCtrl(x9923) { CounterChain(List(x9463,x9464)).name("x9465").srcCtx("lenet_loops.scala:87:17") } // CounterChainNew(List(x9463, x9464))
    val x9495 = withCtrl(x9923) { LoopController(style=StreamPipe, level=OuterControl, cchain=x9465).name("x9495").srcCtx("lenet_loops.scala:87:17") } // UnrolledForeach(List(b5677),x9465,Block(Const(())),List(List(b5684), List(b5685)),List(List(b5686), List(b5687)))
    val b5684 = withCtrl(x9495) { CounterIter(x9463, Some(0)).name("b5684") } // b5684
    val b5686 = withCtrl(x9495) { Const(true).name("b5686") } // b5686
    val b5685 = withCtrl(x9495) { CounterIter(x9464, Some(0)).name("b5685") } // b5685
    val b5687 = withCtrl(x9495) { Const(true).name("b5687") } // b5687
    val b9978 = withCtrl(x9495) { StreamOut(field="offset").name("b9978").srcCtx("lenet_loops.scala:87:17") } // x9466 = StreamOutNew(BurstCmdBus)
    isAccum(b9978) = false
    bufferDepthOf(b9978) = 1
    val b9979 = withCtrl(x9495) { StreamOut(field="size").name("b9979").srcCtx("lenet_loops.scala:87:17") } // x9466 = StreamOutNew(BurstCmdBus)
    isAccum(b9979) = false
    bufferDepthOf(b9979) = 1
    val x9467 = withCtrl(x9495) { StreamIn(field="data").name("x9467").srcCtx("lenet_loops.scala:87:17") } // x9467 = StreamInNew(BurstDataBus())
    isAccum(x9467) = false
    bufferDepthOf(x9467) = 1
    val x9484 = withCtrl(x9495) { UnitController(style=SeqPipe, level=InnerControl).name("x9484").srcCtx("lenet_loops.scala:87:17") } // UnitPipe(List(b5686, b5687, b5677),Block(x9483))
    val x9468 = withCtrl(x9484) { OpDef(op=FixAdd, inputs=List(b5676, b5684)).name("x9468").srcCtx("lenet_loops.scala:87:17") } // FixAdd(b5676,b5684)
    val x9469 = withCtrl(x9484) { x9468 } // FixConvert(x9468,TRUE,_32,_0) (Same Type. No op)
    val x9470 = withCtrl(x9484) { OpDef(op=FixMul, inputs=List(x9469, Const(896))).name("x9470").srcCtx("lenet_loops.scala:87:17") } // FixMul(x9469,Const(896))
    val x9471 = withCtrl(x9484) { b5685 } // FixConvert(b5685,TRUE,_32,_0) (Same Type. No op)
    val x9472 = withCtrl(x9484) { OpDef(op=FixSla, inputs=List(x9471, Const(5))).name("x9472").srcCtx("lenet_loops.scala:87:17") } // FixLsh(x9471,Const(5))
    val x9473 = withCtrl(x9484) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x9474 = withCtrl(x9484) { OpDef(op=FixAdd, inputs=List(x9470, x9472)).name("x9474").srcCtx("lenet_loops.scala:87:17") } // FixAdd(x9470,x9472)
    val x9475 = withCtrl(x9484) { OpDef(op=FixAdd, inputs=List(x9474, x9473)).name("x9475").srcCtx("lenet_loops.scala:87:17") } // FixAdd(x9474,x9473)
    val x9476 = withCtrl(x9484) { OpDef(op=FixSla, inputs=List(x9475, Const(2))).name("x9476").srcCtx("lenet_loops.scala:87:17") } // FixLsh(x9475,Const(2))
    val x9477 = withCtrl(x9484) { x9476 } // FixConvert(x9476,TRUE,_64,_0)
    val x9478 = withCtrl(x9484) { DramAddress(x9254).name("x9478").srcCtx("lenet_loops.scala:87:17") } // GetDRAMAddress(x9254)
    val x9480_x9479 = withCtrl(x9484) { OpDef(op=FixAdd, inputs=List(x9477, x9478)).name("x9480_x9479").srcCtx("lenet_loops.scala:87:17") } // FixAdd(x9477,x9478)
    // x9480 = SimpleStruct(ArrayBuffer((offset,x9479), (size,Const(128)), (isLoad,Const(true))))
    val x9481 = withCtrl(x9484) { OpDef(op=BitAnd, inputs=List(b5686, b5687)).name("x9481").srcCtx("UnrollingBase.scala:28:66") } // And(b5686,b5687)
    val x9482 = withCtrl(x9484) { OpDef(op=BitAnd, inputs=List(x9481, b5677)).name("x9482").srcCtx("UnrollingBase.scala:28:66") } // And(x9481,b5677)
    val x9483_b9980_b9978 = withCtrl(x9484) { WriteMem(b9978, x9480_x9479).name("x9483_b9980_b9978").srcCtx("lenet_loops.scala:87:17") } // StreamWrite(x9466,x9480,x9482)
    val x9483_b9981_b9979 = withCtrl(x9484) { WriteMem(b9979, Const(128)).name("x9483_b9981_b9979").srcCtx("lenet_loops.scala:87:17") } // StreamWrite(x9466,x9480,x9482)
    val x9485 = withCtrl(x9495) { FringeDenseLoad(dram=List(x9254), cmdStream=List(b9978, b9979), dataStream=List(x9467)).name("x9485").srcCtx("lenet_loops.scala:87:17") } // FringeDenseLoad(x9254,x9466,x9467)
    val x9486 = withCtrl(x9495) { Counter(min=Const(0), max=Const(32), step=Const(1), par=1).name("x9486").srcCtx("lenet_loops.scala:87:17") } // CounterNew(Const(0),Const(32),Const(1),Const(1))
    val x9487 = withCtrl(x9495) { CounterChain(List(x9486)).name("x9487").srcCtx("lenet_loops.scala:87:17") } // CounterChainNew(List(x9486))
    val x9494 = withCtrl(x9495) { LoopController(style=InnerPipe, level=InnerControl, cchain=x9487).name("x9494").srcCtx("lenet_loops.scala:87:17") } // UnrolledForeach(List(b5686, b5687, b5677),x9487,Block(Const(())),List(List(b5710)),List(List(b5711)))
    val b5710 = withCtrl(x9494) { CounterIter(x9486, None).name("b5710") } // b5710
    val b5711 = withCtrl(x9494) { Const(true).name("b5711") } // b5711
    val x9488 = withCtrl(x9494) { OpDef(op=BitAnd, inputs=List(b5711, b5686)).name("x9488").srcCtx("UnrollingBase.scala:28:66") } // And(b5711,b5686)
    val x9489 = withCtrl(x9494) { OpDef(op=BitAnd, inputs=List(b5687, b5677)).name("x9489").srcCtx("UnrollingBase.scala:28:66") } // And(b5687,b5677)
    val x9490 = withCtrl(x9494) { OpDef(op=BitAnd, inputs=List(x9488, x9489)).name("x9490").srcCtx("UnrollingBase.scala:28:66") } // And(x9488,x9489)
    val x9491_x9491 = withCtrl(x9494) { ReadMem(x9467).name("x9491_x9491").srcCtx("lenet_loops.scala:87:17") } // ParStreamRead(x9467,List(x9490))
    val x9492_x9492 = withCtrl(x9494) { x9491_x9491 } // VectorApply(x9491,0)
    val x9493 = withCtrl(x9494) { StoreBanks(List(List(x9460_d0_b0)), List(b5685, b5710), x9492_x9492).name("x9493").srcCtx("lenet_loops.scala:87:17") } // ParSRAMStore(x9460,List(List(b5685, b5710)),List(x9492),List(x9490))
    val x9496_d0_b0 = withCtrl(x9923) { SRAM(size=2880, banking=Strided(banks=1, stride=144)).name("x9496_d0_b0").srcCtx("lenet_loops.scala:90:32:tmp1_SRAM") } // x9496 = SRAMNew(ArrayBuffer(Const(20), Const(12), Const(12)))
    isAccum(x9496_d0_b0) = false
    bufferDepthOf(x9496_d0_b0) = 2
    staticDimsOf(x9496_d0_b0) = List(20, 12, 12)
    val x9497 = withCtrl(x9923) { Counter(min=Const(0), max=Const(20), step=Const(1), par=1).name("x9497").srcCtx("lenet_loops.scala:91:25") } // CounterNew(Const(0),Const(20),Const(1),Const(1))
    val x9498 = withCtrl(x9923) { CounterChain(List(x9497)).name("x9498").srcCtx("lenet_loops.scala:91:40") } // CounterChainNew(List(x9497))
    val x9610 = withCtrl(x9923) { LoopController(style=MetaPipe, level=OuterControl, cchain=x9498).name("x9610").srcCtx("lenet_loops.scala:91:40") } // UnrolledForeach(List(b5677),x9498,Block(Const(())),List(List(b5723)),List(List(b5724)))
    val b5723 = withCtrl(x9610) { CounterIter(x9497, Some(0)).name("b5723") } // b5723
    val b5724 = withCtrl(x9610) { Const(true).name("b5724") } // b5724
    val x9499_d0_b0 = withCtrl(x9610) { SRAM(size=576, banking=Strided(banks=1, stride=24)).name("x9499_d0_b0").srcCtx("lenet_loops.scala:98:39:tmp1_SRAM_conv") } // x9499 = SRAMNew(ArrayBuffer(Const(24), Const(24)))
    isAccum(x9499_d0_b0) = false
    bufferDepthOf(x9499_d0_b0) = 2
    staticDimsOf(x9499_d0_b0) = List(24, 24)
    val x9500_d0_b0 = withCtrl(x9610) { RegFile(size=32, inits=None).name("x9500_d0_b0").srcCtx("lenet_loops.scala:99:33:c0_RF") } // x9500 = RegFileNew(ArrayBuffer(Const(32)),None) banking:Strided(banks=1, stride=1)
    isAccum(x9500_d0_b0) = false
    bufferDepthOf(x9500_d0_b0) = 2
    staticDimsOf(x9500_d0_b0) = List(32)
    val x9502 = withCtrl(x9610) { UnitController(style=SeqPipe, level=InnerControl).name("x9502").srcCtx("lenet_loops.scala:91:40") } // UnitPipe(List(b5724, b5677),Block(Const(())))
    val x9501 = withCtrl(x9502) { OpDef(op=FixAdd, inputs=List(b5723, Const(1))).name("x9501").srcCtx("lenet_loops.scala:100:29") } // FixAdd(b5723,Const(1))
    val x9503 = withCtrl(x9610) { Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x9503").srcCtx("lenet_loops.scala:100:17") } // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x9504 = withCtrl(x9610) { Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x9504").srcCtx("lenet_loops.scala:100:17") } // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x9505 = withCtrl(x9610) { CounterChain(List(x9503,x9504)).name("x9505").srcCtx("lenet_loops.scala:100:17") } // CounterChainNew(List(x9503, x9504))
    val x9537 = withCtrl(x9610) { LoopController(style=StreamPipe, level=OuterControl, cchain=x9505).name("x9537").srcCtx("lenet_loops.scala:100:17") } // UnrolledForeach(List(b5724, b5677),x9505,Block(Const(())),List(List(b5732), List(b5733)),List(List(b5734), List(b5735)))
    val b5732 = withCtrl(x9537) { CounterIter(x9503, Some(0)).name("b5732") } // b5732
    val b5734 = withCtrl(x9537) { Const(true).name("b5734") } // b5734
    val b5733 = withCtrl(x9537) { CounterIter(x9504, Some(0)).name("b5733") } // b5733
    val b5735 = withCtrl(x9537) { Const(true).name("b5735") } // b5735
    val b9982 = withCtrl(x9537) { StreamOut(field="offset").name("b9982").srcCtx("lenet_loops.scala:100:17") } // x9506 = StreamOutNew(BurstCmdBus)
    isAccum(b9982) = false
    bufferDepthOf(b9982) = 1
    val b9983 = withCtrl(x9537) { StreamOut(field="size").name("b9983").srcCtx("lenet_loops.scala:100:17") } // x9506 = StreamOutNew(BurstCmdBus)
    isAccum(b9983) = false
    bufferDepthOf(b9983) = 1
    val x9507 = withCtrl(x9537) { StreamIn(field="data").name("x9507").srcCtx("lenet_loops.scala:100:17") } // x9507 = StreamInNew(BurstDataBus())
    isAccum(x9507) = false
    bufferDepthOf(x9507) = 1
    val x9525 = withCtrl(x9537) { UnitController(style=SeqPipe, level=InnerControl).name("x9525").srcCtx("lenet_loops.scala:100:17") } // UnitPipe(List(b5734, b5735, b5724, b5677),Block(x9524))
    val x9508 = withCtrl(x9525) { OpDef(op=FixAdd, inputs=List(b5723, b5732)).name("x9508").srcCtx("lenet_loops.scala:100:17") } // FixAdd(b5723,b5732)
    val x9509 = withCtrl(x9525) { x9508 } // FixConvert(x9508,TRUE,_32,_0) (Same Type. No op)
    val x9510 = withCtrl(x9525) { OpDef(op=FixSla, inputs=List(x9509, Const(5))).name("x9510").srcCtx("lenet_loops.scala:100:17") } // FixLsh(x9509,Const(5))
    val x9511 = withCtrl(x9525) { b5733 } // FixConvert(b5733,TRUE,_32,_0) (Same Type. No op)
    val x9512 = withCtrl(x9525) { OpDef(op=FixSla, inputs=List(x9511, Const(5))).name("x9512").srcCtx("lenet_loops.scala:100:17") } // FixLsh(x9511,Const(5))
    val x9513 = withCtrl(x9525) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x9514 = withCtrl(x9525) { OpDef(op=FixAdd, inputs=List(x9510, x9512)).name("x9514").srcCtx("lenet_loops.scala:100:17") } // FixAdd(x9510,x9512)
    val x9515 = withCtrl(x9525) { OpDef(op=FixAdd, inputs=List(x9514, x9513)).name("x9515").srcCtx("lenet_loops.scala:100:17") } // FixAdd(x9514,x9513)
    val x9516 = withCtrl(x9525) { OpDef(op=FixSla, inputs=List(x9515, Const(2))).name("x9516").srcCtx("lenet_loops.scala:100:17") } // FixLsh(x9515,Const(2))
    val x9517 = withCtrl(x9525) { x9516 } // FixConvert(x9516,TRUE,_64,_0)
    val x9518 = withCtrl(x9525) { DramAddress(x9253).name("x9518").srcCtx("lenet_loops.scala:100:17") } // GetDRAMAddress(x9253)
    val x9520_x9519 = withCtrl(x9525) { OpDef(op=FixAdd, inputs=List(x9517, x9518)).name("x9520_x9519").srcCtx("lenet_loops.scala:100:17") } // FixAdd(x9517,x9518)
    // x9520 = SimpleStruct(ArrayBuffer((offset,x9519), (size,Const(128)), (isLoad,Const(true))))
    val x9521 = withCtrl(x9525) { OpDef(op=BitAnd, inputs=List(b5734, b5735)).name("x9521").srcCtx("UnrollingBase.scala:28:66") } // And(b5734,b5735)
    val x9522 = withCtrl(x9525) { OpDef(op=BitAnd, inputs=List(b5724, b5677)).name("x9522").srcCtx("UnrollingBase.scala:28:66") } // And(b5724,b5677)
    val x9523 = withCtrl(x9525) { OpDef(op=BitAnd, inputs=List(x9521, x9522)).name("x9523").srcCtx("UnrollingBase.scala:28:66") } // And(x9521,x9522)
    val x9524_b9984_b9982 = withCtrl(x9525) { WriteMem(b9982, x9520_x9519).name("x9524_b9984_b9982").srcCtx("lenet_loops.scala:100:17") } // StreamWrite(x9506,x9520,x9523)
    val x9524_b9985_b9983 = withCtrl(x9525) { WriteMem(b9983, Const(128)).name("x9524_b9985_b9983").srcCtx("lenet_loops.scala:100:17") } // StreamWrite(x9506,x9520,x9523)
    val x9526 = withCtrl(x9537) { FringeDenseLoad(dram=List(x9253), cmdStream=List(b9982, b9983), dataStream=List(x9507)).name("x9526").srcCtx("lenet_loops.scala:100:17") } // FringeDenseLoad(x9253,x9506,x9507)
    val x9527 = withCtrl(x9537) { Counter(min=Const(0), max=Const(32), step=Const(1), par=1).name("x9527").srcCtx("lenet_loops.scala:100:17") } // CounterNew(Const(0),Const(32),Const(1),Const(1))
    val x9528 = withCtrl(x9537) { CounterChain(List(x9527)).name("x9528").srcCtx("lenet_loops.scala:100:17") } // CounterChainNew(List(x9527))
    val x9536 = withCtrl(x9537) { LoopController(style=InnerPipe, level=InnerControl, cchain=x9528).name("x9536").srcCtx("lenet_loops.scala:100:17") } // UnrolledForeach(List(b5734, b5735, b5724, b5677),x9528,Block(Const(())),List(List(b5759)),List(List(b5760)))
    val b5759 = withCtrl(x9536) { CounterIter(x9527, None).name("b5759") } // b5759
    val b5760 = withCtrl(x9536) { Const(true).name("b5760") } // b5760
    val x9529 = withCtrl(x9536) { OpDef(op=BitAnd, inputs=List(b5760, b5734)).name("x9529").srcCtx("UnrollingBase.scala:28:66") } // And(b5760,b5734)
    val x9530 = withCtrl(x9536) { OpDef(op=BitAnd, inputs=List(b5735, b5724)).name("x9530").srcCtx("UnrollingBase.scala:28:66") } // And(b5735,b5724)
    val x9531 = withCtrl(x9536) { OpDef(op=BitAnd, inputs=List(x9529, x9530)).name("x9531").srcCtx("UnrollingBase.scala:28:66") } // And(x9529,x9530)
    val x9532 = withCtrl(x9536) { OpDef(op=BitAnd, inputs=List(x9531, b5677)).name("x9532").srcCtx("UnrollingBase.scala:28:66") } // And(x9531,b5677)
    val x9533_x9533 = withCtrl(x9536) { ReadMem(x9507).name("x9533_x9533").srcCtx("lenet_loops.scala:100:17") } // ParStreamRead(x9507,List(x9532))
    val x9534_x9534 = withCtrl(x9536) { x9533_x9533 } // VectorApply(x9533,0)
    val x9535 = withCtrl(x9536) { StoreBanks(List(List(x9500_d0_b0)), List(b5759), x9534_x9534).name("x9535").srcCtx("lenet_loops.scala:100:17") } // ParRegFileStore(x9500,List(List(b5759)),List(x9534),List(Const(true)))
    val x9538 = withCtrl(x9610) { Counter(min=Const(0), max=Const(24), step=Const(1), par=1).name("x9538").srcCtx("lenet_loops.scala:101:42") } // CounterNew(Const(0),Const(24),Const(1),Const(1))
    val x9539 = withCtrl(x9610) { Counter(min=Const(0), max=Const(24), step=Const(1), par=1).name("x9539").srcCtx("lenet_loops.scala:101:21") } // CounterNew(Const(0),Const(24),Const(1),Const(1))
    val x9540 = withCtrl(x9610) { CounterChain(List(x9539,x9538)).name("x9540").srcCtx("lenet_loops.scala:101:49") } // CounterChainNew(List(x9539, x9538))
    val x9573 = withCtrl(x9610) { LoopController(style=MetaPipe, level=OuterControl, cchain=x9540).name("x9573").srcCtx("lenet_loops.scala:101:49") } // UnrolledForeach(List(b5724, b5677),x9540,Block(Const(())),List(List(b5773), List(b5774)),List(List(b5775), List(b5776)))
    val b5773 = withCtrl(x9573) { CounterIter(x9539, Some(0)).name("b5773") } // b5773
    val b5775 = withCtrl(x9573) { Const(true).name("b5775") } // b5775
    val b5774 = withCtrl(x9573) { CounterIter(x9538, Some(0)).name("b5774") } // b5774
    val b5776 = withCtrl(x9573) { Const(true).name("b5776") } // b5776
    val x9541_d0 = withCtrl(x9573) { Reg(init=Some(0.0)).name("x9541_d0").srcCtx("lenet_loops.scala:102:39:window") } // x9541 = RegNew(Const(0))
    isAccum(x9541_d0) = false
    bufferDepthOf(x9541_d0) = 2
    val x9541_d1 = withCtrl(x9573) { Reg(init=Some(0.0)).name("x9541_d1").srcCtx("lenet_loops.scala:102:39:window") } // x9541 = RegNew(Const(0))
    isAccum(x9541_d1) = true
    bufferDepthOf(x9541_d1) = 1
    val x9542 = withCtrl(x9573) { Counter(min=Const(0), max=Const(5), step=Const(1), par=1).name("x9542").srcCtx("lenet_loops.scala:102:79") } // CounterNew(Const(0),Const(5),Const(1),Const(1))
    val x9543 = withCtrl(x9573) { Counter(min=Const(0), max=Const(5), step=Const(1), par=1).name("x9543").srcCtx("lenet_loops.scala:102:61") } // CounterNew(Const(0),Const(5),Const(1),Const(1))
    val x9544 = withCtrl(x9573) { CounterChain(List(x9543,x9542)).name("x9544").srcCtx("lenet_loops.scala:104:14") } // CounterChainNew(List(x9543, x9542))
    val x9566 = withCtrl(x9573) { LoopController(style=InnerPipe, level=InnerControl, cchain=x9544).name("x9566").srcCtx("lenet_loops.scala:104:14") } // UnrolledReduce(List(b5775, b5776, b5724, b5677),x9544,x9541,Block((x9541) => Const(())),List(List(b5781), List(b5782)),List(List(b5783), List(b5784)))
    val b5781 = withCtrl(x9566) { CounterIter(x9543, Some(0)).name("b5781") } // b5781
    val b5783 = withCtrl(x9566) { Const(true).name("b5783") } // b5783
    val b5782 = withCtrl(x9566) { CounterIter(x9542, None).name("b5782") } // b5782
    val b5784 = withCtrl(x9566) { Const(true).name("b5784") } // b5784
    val x9545 = withCtrl(x9566) { OpDef(op=FixAdd, inputs=List(b5773, b5781)).name("x9545").srcCtx("lenet_loops.scala:103:24") } // FixAdd(b5773,b5781)
    val x9546 = withCtrl(x9566) { OpDef(op=FixAdd, inputs=List(b5774, b5782)).name("x9546").srcCtx("lenet_loops.scala:103:28") } // FixAdd(b5774,b5782)
    val x9547 = withCtrl(x9566) { OpDef(op=BitAnd, inputs=List(b5783, b5784)).name("x9547").srcCtx("UnrollingBase.scala:28:66") } // And(b5783,b5784)
    val x9548 = withCtrl(x9566) { OpDef(op=BitAnd, inputs=List(b5775, b5776)).name("x9548").srcCtx("UnrollingBase.scala:28:66") } // And(b5775,b5776)
    val x9549 = withCtrl(x9566) { OpDef(op=BitAnd, inputs=List(b5724, b5677)).name("x9549").srcCtx("UnrollingBase.scala:28:66") } // And(b5724,b5677)
    val x9550 = withCtrl(x9566) { OpDef(op=BitAnd, inputs=List(x9547, x9548)).name("x9550").srcCtx("UnrollingBase.scala:28:66") } // And(x9547,x9548)
    val x9551 = withCtrl(x9566) { OpDef(op=BitAnd, inputs=List(x9550, x9549)).name("x9551").srcCtx("UnrollingBase.scala:28:66") } // And(x9550,x9549)
    val x9552 = withCtrl(x9566) { LoadBanks(List(x9460_d0_b0), List(x9545, x9546)).name("x9552").srcCtx("lenet_loops.scala:103:22") } // ParSRAMLoad(x9460,List(List(x9545, x9546)),List(x9551))
    val x9553 = withCtrl(x9566) { x9552 } // VectorApply(x9552,0)
    val x9554 = withCtrl(x9566) { OpDef(op=FixMul, inputs=List(b5781, Const(5))).name("x9554").srcCtx("lenet_loops.scala:103:41") } // FixMul(b5781,Const(5))
    val x9555 = withCtrl(x9566) { OpDef(op=FixAdd, inputs=List(x9554, b5782)).name("x9555").srcCtx("lenet_loops.scala:103:43") } // FixAdd(x9554,b5782)
    val x9556 = withCtrl(x9566) { LoadBanks(List(x9500_d0_b0), List(x9555)).name("x9556").srcCtx("lenet_loops.scala:103:39") } // ParRegFileLoad(x9500,List(List(x9555)),List(Const(true)))
    val x9557 = withCtrl(x9566) { x9556 } // VectorApply(x9556,0)
    val x9558 = withCtrl(x9566) { OpDef(op=FixMul, inputs=List(x9553, x9557)).name("x9558").srcCtx("lenet_loops.scala:103:32") } // FixMul(x9553,x9557)
    val x9559 = withCtrl(x9566) { ReadMem(x9541_d1).name("x9559").srcCtx("lenet_loops.scala:104:14") } // RegRead(x9541)
    val x9560 = withCtrl(x9566) { OpDef(op=FixEql, inputs=List(b5781, Const(0))).name("x9560").srcCtx("lenet_loops.scala:104:14") } // FixEql(b5781,Const(0))
    val x9561 = withCtrl(x9566) { OpDef(op=FixEql, inputs=List(b5782, Const(0))).name("x9561").srcCtx("lenet_loops.scala:104:14") } // FixEql(b5782,Const(0))
    val x9562 = withCtrl(x9566) { OpDef(op=BitAnd, inputs=List(x9560, x9561)).name("x9562").srcCtx("lenet_loops.scala:104:14") } // And(x9560,x9561)
    val x9563 = withCtrl(x9566) { ReduceAccumOp(op=FixAdd, input=x9558, accum=x9559).name("x9563").srcCtx("lenet_loops.scala:104:16") } // FixAdd(x9558,x9559)
    val x9564 = withCtrl(x9566) { OpDef(op=BitAnd, inputs=List(x9548, x9549)).name("x9564").srcCtx("UnrollingBase.scala:28:66") } // And(x9548,x9549)
    val x9565_x9541_d0 = withCtrl(x9566) { WriteMem(x9541_d0, x9563).name("x9565_x9541_d0").srcCtx("lenet_loops.scala:104:14") } // RegWrite(x9541,x9563,x9564)
    antiDepsOf(x9565_x9541_d0)=List(x9559)
    val x9565_x9541_d1 = withCtrl(x9566) { WriteMem(x9541_d1, x9563).name("x9565_x9541_d1").srcCtx("lenet_loops.scala:104:14") } // RegWrite(x9541,x9563,x9564)
    antiDepsOf(x9565_x9541_d1)=List(x9559)
    val x9572 = withCtrl(x9573) { UnitController(style=SeqPipe, level=InnerControl).name("x9572").srcCtx("lenet_loops.scala:101:49") } // UnitPipe(List(b5775, b5776, b5724, b5677),Block(Const(())))
    val x9567 = withCtrl(x9572) { ReadMem(x9541_d0).name("x9567").srcCtx("lenet_loops.scala:105:43") } // RegRead(x9541)
    val x9568 = withCtrl(x9572) { OpDef(op=BitAnd, inputs=List(b5775, b5776)).name("x9568").srcCtx("UnrollingBase.scala:28:66") } // And(b5775,b5776)
    val x9569 = withCtrl(x9572) { OpDef(op=BitAnd, inputs=List(b5724, b5677)).name("x9569").srcCtx("UnrollingBase.scala:28:66") } // And(b5724,b5677)
    val x9570 = withCtrl(x9572) { OpDef(op=BitAnd, inputs=List(x9568, x9569)).name("x9570").srcCtx("UnrollingBase.scala:28:66") } // And(x9568,x9569)
    val x9571 = withCtrl(x9572) { StoreBanks(List(List(x9499_d0_b0)), List(b5773, b5774), x9567).name("x9571").srcCtx("lenet_loops.scala:105:34") } // SRAMStore(x9499,ArrayBuffer(Const(24), Const(24)),List(b5773, b5774),Const(0),x9567,x9570)
    val x9574 = withCtrl(x9610) { Counter(min=Const(0), max=Const(12), step=Const(1), par=1).name("x9574").srcCtx("lenet_loops.scala:107:31") } // CounterNew(Const(0),Const(12),Const(1),Const(1))
    val x9575 = withCtrl(x9610) { Counter(min=Const(0), max=Const(12), step=Const(1), par=1).name("x9575").srcCtx("lenet_loops.scala:107:22") } // CounterNew(Const(0),Const(12),Const(1),Const(1))
    val x9576 = withCtrl(x9610) { CounterChain(List(x9575,x9574)).name("x9576").srcCtx("lenet_loops.scala:107:37") } // CounterChainNew(List(x9575, x9574))
    val x9609 = withCtrl(x9610) { LoopController(style=MetaPipe, level=OuterControl, cchain=x9576).name("x9609").srcCtx("lenet_loops.scala:107:37") } // UnrolledForeach(List(b5724, b5677),x9576,Block(Const(())),List(List(b5817), List(b5818)),List(List(b5819), List(b5820)))
    val b5817 = withCtrl(x9609) { CounterIter(x9575, Some(0)).name("b5817") } // b5817
    val b5819 = withCtrl(x9609) { Const(true).name("b5819") } // b5819
    val b5818 = withCtrl(x9609) { CounterIter(x9574, Some(0)).name("b5818") } // b5818
    val b5820 = withCtrl(x9609) { Const(true).name("b5820") } // b5820
    val x9577_d0 = withCtrl(x9609) { Reg(init=Some(0.0)).name("x9577_d0").srcCtx("lenet_loops.scala:108:36:out") } // x9577 = RegNew(Const(0))
    isAccum(x9577_d0) = false
    bufferDepthOf(x9577_d0) = 2
    val x9577_d1 = withCtrl(x9609) { Reg(init=Some(0.0)).name("x9577_d1").srcCtx("lenet_loops.scala:108:36:out") } // x9577 = RegNew(Const(0))
    isAccum(x9577_d1) = true
    bufferDepthOf(x9577_d1) = 1
    val x9578 = withCtrl(x9609) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x9578").srcCtx("lenet_loops.scala:108:57") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x9579 = withCtrl(x9609) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x9579").srcCtx("lenet_loops.scala:108:49") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x9580 = withCtrl(x9609) { CounterChain(List(x9579,x9578)).name("x9580").srcCtx("lenet_loops.scala:110:15") } // CounterChainNew(List(x9579, x9578))
    val x9602 = withCtrl(x9609) { LoopController(style=InnerPipe, level=InnerControl, cchain=x9580).name("x9602").srcCtx("lenet_loops.scala:110:15") } // UnrolledReduce(List(b5819, b5820, b5724, b5677),x9580,x9577,Block((x9577) => Const(())),List(List(b5825), List(b5826)),List(List(b5827), List(b5828)))
    val b5825 = withCtrl(x9602) { CounterIter(x9579, Some(0)).name("b5825") } // b5825
    val b5827 = withCtrl(x9602) { Const(true).name("b5827") } // b5827
    val b5826 = withCtrl(x9602) { CounterIter(x9578, None).name("b5826") } // b5826
    val b5828 = withCtrl(x9602) { Const(true).name("b5828") } // b5828
    val x9581 = withCtrl(x9602) { OpDef(op=FixSla, inputs=List(b5817, Const(1))).name("x9581").srcCtx("lenet_loops.scala:109:44") } // FixLsh(b5817,Const(1))
    val x9582 = withCtrl(x9602) { OpDef(op=FixAdd, inputs=List(x9581, b5825)).name("x9582").srcCtx("lenet_loops.scala:109:47") } // FixAdd(x9581,b5825)
    val x9583 = withCtrl(x9602) { OpDef(op=FixSla, inputs=List(b5818, Const(1))).name("x9583").srcCtx("lenet_loops.scala:109:54") } // FixLsh(b5818,Const(1))
    val x9584 = withCtrl(x9602) { OpDef(op=FixAdd, inputs=List(x9583, b5826)).name("x9584").srcCtx("lenet_loops.scala:109:57") } // FixAdd(x9583,b5826)
    val x9585 = withCtrl(x9602) { OpDef(op=BitAnd, inputs=List(b5827, b5828)).name("x9585").srcCtx("UnrollingBase.scala:28:66") } // And(b5827,b5828)
    val x9586 = withCtrl(x9602) { OpDef(op=BitAnd, inputs=List(b5819, b5820)).name("x9586").srcCtx("UnrollingBase.scala:28:66") } // And(b5819,b5820)
    val x9587 = withCtrl(x9602) { OpDef(op=BitAnd, inputs=List(b5724, b5677)).name("x9587").srcCtx("UnrollingBase.scala:28:66") } // And(b5724,b5677)
    val x9588 = withCtrl(x9602) { OpDef(op=BitAnd, inputs=List(x9585, x9586)).name("x9588").srcCtx("UnrollingBase.scala:28:66") } // And(x9585,x9586)
    val x9589 = withCtrl(x9602) { OpDef(op=BitAnd, inputs=List(x9588, x9587)).name("x9589").srcCtx("UnrollingBase.scala:28:66") } // And(x9588,x9587)
    val x9590 = withCtrl(x9602) { LoadBanks(List(x9499_d0_b0), List(x9582, x9584)).name("x9590").srcCtx("lenet_loops.scala:109:42") } // ParSRAMLoad(x9499,List(List(x9582, x9584)),List(x9589))
    val x9591 = withCtrl(x9602) { x9590 } // VectorApply(x9590,0)
    val x9592 = withCtrl(x9602) { LoadBanks(List(x9382_d0_b0), List(b5723)).name("x9592").srcCtx("lenet_loops.scala:109:72") } // SRAMLoad(x9382,ArrayBuffer(Const(32)),List(b5723),Const(0),x9589)
    val x9593 = withCtrl(x9602) { OpDef(op=FixAdd, inputs=List(x9591, x9592)).name("x9593").srcCtx("lenet_loops.scala:109:63") } // FixAdd(x9591,x9592)
    val x9594 = withCtrl(x9602) { OpDef(op=FixMax, inputs=List(Const(0.0), x9593)).name("x9594").srcCtx("lenet_loops.scala:109:18") } // Max(Const(0),x9593)
    val x9595 = withCtrl(x9602) { ReadMem(x9577_d1).name("x9595").srcCtx("lenet_loops.scala:110:15") } // RegRead(x9577)
    val x9596 = withCtrl(x9602) { OpDef(op=FixEql, inputs=List(b5825, Const(0))).name("x9596").srcCtx("lenet_loops.scala:110:15") } // FixEql(b5825,Const(0))
    val x9597 = withCtrl(x9602) { OpDef(op=FixEql, inputs=List(b5826, Const(0))).name("x9597").srcCtx("lenet_loops.scala:110:15") } // FixEql(b5826,Const(0))
    val x9598 = withCtrl(x9602) { OpDef(op=BitAnd, inputs=List(x9596, x9597)).name("x9598").srcCtx("lenet_loops.scala:110:15") } // And(x9596,x9597)
    val x9599 = withCtrl(x9602) { ReduceAccumOp(op=FixMax, input=x9594, accum=x9595).name("x9599").srcCtx("lenet_loops.scala:110:29") } // Max(x9594,x9595)
    val x9600 = withCtrl(x9602) { OpDef(op=BitAnd, inputs=List(x9586, x9587)).name("x9600").srcCtx("UnrollingBase.scala:28:66") } // And(x9586,x9587)
    val x9601_x9577_d0 = withCtrl(x9602) { WriteMem(x9577_d0, x9599).name("x9601_x9577_d0").srcCtx("lenet_loops.scala:110:15") } // RegWrite(x9577,x9599,x9600)
    antiDepsOf(x9601_x9577_d0)=List(x9595)
    val x9601_x9577_d1 = withCtrl(x9602) { WriteMem(x9577_d1, x9599).name("x9601_x9577_d1").srcCtx("lenet_loops.scala:110:15") } // RegWrite(x9577,x9599,x9600)
    antiDepsOf(x9601_x9577_d1)=List(x9595)
    val x9608 = withCtrl(x9609) { UnitController(style=SeqPipe, level=InnerControl).name("x9608").srcCtx("lenet_loops.scala:107:37") } // UnitPipe(List(b5819, b5820, b5724, b5677),Block(Const(())))
    val x9603 = withCtrl(x9608) { ReadMem(x9577_d0).name("x9603").srcCtx("lenet_loops.scala:111:43") } // RegRead(x9577)
    val x9604 = withCtrl(x9608) { OpDef(op=BitAnd, inputs=List(b5819, b5820)).name("x9604").srcCtx("UnrollingBase.scala:28:66") } // And(b5819,b5820)
    val x9605 = withCtrl(x9608) { OpDef(op=BitAnd, inputs=List(b5724, b5677)).name("x9605").srcCtx("UnrollingBase.scala:28:66") } // And(b5724,b5677)
    val x9606 = withCtrl(x9608) { OpDef(op=BitAnd, inputs=List(x9604, x9605)).name("x9606").srcCtx("UnrollingBase.scala:28:66") } // And(x9604,x9605)
    val x9607 = withCtrl(x9608) { StoreBanks(List(List(x9496_d0_b0)), List(b5723, b5817, b5818), x9603).name("x9607").srcCtx("lenet_loops.scala:111:37") } // SRAMStore(x9496,ArrayBuffer(Const(20), Const(12), Const(12)),List(b5723, b5817, b5818),Const(0),x9603,x9606)
    val x9611_d0_b0 = withCtrl(x9923) { SRAM(size=800, banking=Strided(banks=1, stride=16)).name("x9611_d0_b0").srcCtx("lenet_loops.scala:119:32:tmp2_SRAM") } // x9611 = SRAMNew(ArrayBuffer(Const(50), Const(4), Const(4)))
    isAccum(x9611_d0_b0) = false
    bufferDepthOf(x9611_d0_b0) = 2
    staticDimsOf(x9611_d0_b0) = List(50, 4, 4)
    val x9612 = withCtrl(x9923) { Counter(min=Const(0), max=Const(50), step=Const(1), par=1).name("x9612").srcCtx("lenet_loops.scala:120:25") } // CounterNew(Const(0),Const(50),Const(1),Const(1))
    val x9613 = withCtrl(x9923) { CounterChain(List(x9612)).name("x9613").srcCtx("lenet_loops.scala:120:40") } // CounterChainNew(List(x9612))
    val x9746 = withCtrl(x9923) { LoopController(style=MetaPipe, level=OuterControl, cchain=x9613).name("x9746").srcCtx("lenet_loops.scala:120:40") } // UnrolledForeach(List(b5677),x9613,Block(Const(())),List(List(b5862)),List(List(b5863)))
    val b5862 = withCtrl(x9746) { CounterIter(x9612, Some(0)).name("b5862") } // b5862
    val b5863 = withCtrl(x9746) { Const(true).name("b5863") } // b5863
    val x9614_d0_b0 = withCtrl(x9746) { SRAM(size=64, banking=Strided(banks=1, stride=8)).name("x9614_d0_b0").srcCtx("lenet_loops.scala:128:39:tmp2_SRAM_conv") } // x9614 = SRAMNew(ArrayBuffer(Const(8), Const(8)))
    isAccum(x9614_d0_b0) = false
    bufferDepthOf(x9614_d0_b0) = 2
    staticDimsOf(x9614_d0_b0) = List(8, 8)
    val x9614_d1_b0 = withCtrl(x9746) { SRAM(size=64, banking=Strided(banks=1, stride=8)).name("x9614_d1_b0").srcCtx("lenet_loops.scala:128:39:tmp2_SRAM_conv") } // x9614 = SRAMNew(ArrayBuffer(Const(8), Const(8)))
    isAccum(x9614_d1_b0) = true
    bufferDepthOf(x9614_d1_b0) = 1
    staticDimsOf(x9614_d1_b0) = List(8, 8)
    val x9615_d0_b0 = withCtrl(x9746) { SRAM(size=512, banking=Strided(banks=1, stride=1)).name("x9615_d0_b0").srcCtx("lenet_loops.scala:129:30:c2_RF") } // x9615 = SRAMNew(ArrayBuffer(Const(512)))
    isAccum(x9615_d0_b0) = false
    bufferDepthOf(x9615_d0_b0) = 2
    staticDimsOf(x9615_d0_b0) = List(512)
    val x9617 = withCtrl(x9746) { UnitController(style=SeqPipe, level=InnerControl).name("x9617").srcCtx("lenet_loops.scala:120:40") } // UnitPipe(List(b5863, b5677),Block(Const(())))
    val x9616 = withCtrl(x9617) { OpDef(op=FixAdd, inputs=List(b5862, Const(1))).name("x9616").srcCtx("lenet_loops.scala:130:29") } // FixAdd(b5862,Const(1))
    val x9618 = withCtrl(x9746) { Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x9618").srcCtx("lenet_loops.scala:130:17") } // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x9619 = withCtrl(x9746) { CounterChain(List(x9618)).name("x9619").srcCtx("lenet_loops.scala:130:17") } // CounterChainNew(List(x9618))
    val x9646 = withCtrl(x9746) { LoopController(style=StreamPipe, level=OuterControl, cchain=x9619).name("x9646").srcCtx("lenet_loops.scala:130:17") } // UnrolledForeach(List(b5863, b5677),x9619,Block(Const(())),List(List(b5870)),List(List(b5871)))
    val b5870 = withCtrl(x9646) { CounterIter(x9618, Some(0)).name("b5870") } // b5870
    val b5871 = withCtrl(x9646) { Const(true).name("b5871") } // b5871
    val b9986 = withCtrl(x9646) { StreamOut(field="offset").name("b9986").srcCtx("lenet_loops.scala:130:17") } // x9620 = StreamOutNew(BurstCmdBus)
    isAccum(b9986) = false
    bufferDepthOf(b9986) = 1
    val b9987 = withCtrl(x9646) { StreamOut(field="size").name("b9987").srcCtx("lenet_loops.scala:130:17") } // x9620 = StreamOutNew(BurstCmdBus)
    isAccum(b9987) = false
    bufferDepthOf(b9987) = 1
    val x9621 = withCtrl(x9646) { StreamIn(field="data").name("x9621").srcCtx("lenet_loops.scala:130:17") } // x9621 = StreamInNew(BurstDataBus())
    isAccum(x9621) = false
    bufferDepthOf(x9621) = 1
    val x9635 = withCtrl(x9646) { UnitController(style=SeqPipe, level=InnerControl).name("x9635").srcCtx("lenet_loops.scala:130:17") } // UnitPipe(List(b5871, b5863, b5677),Block(x9634))
    val x9622 = withCtrl(x9635) { OpDef(op=FixAdd, inputs=List(b5862, b5870)).name("x9622").srcCtx("lenet_loops.scala:130:17") } // FixAdd(b5862,b5870)
    val x9623 = withCtrl(x9635) { x9622 } // FixConvert(x9622,TRUE,_32,_0) (Same Type. No op)
    val x9624 = withCtrl(x9635) { OpDef(op=FixSla, inputs=List(x9623, Const(9))).name("x9624").srcCtx("lenet_loops.scala:130:17") } // FixLsh(x9623,Const(9))
    val x9625 = withCtrl(x9635) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x9626 = withCtrl(x9635) { OpDef(op=FixAdd, inputs=List(x9624, x9625)).name("x9626").srcCtx("lenet_loops.scala:130:17") } // FixAdd(x9624,x9625)
    val x9627 = withCtrl(x9635) { OpDef(op=FixSla, inputs=List(x9626, Const(2))).name("x9627").srcCtx("lenet_loops.scala:130:17") } // FixLsh(x9626,Const(2))
    val x9628 = withCtrl(x9635) { x9627 } // FixConvert(x9627,TRUE,_64,_0)
    val x9629 = withCtrl(x9635) { DramAddress(x9256).name("x9629").srcCtx("lenet_loops.scala:130:17") } // GetDRAMAddress(x9256)
    val x9631_x9630 = withCtrl(x9635) { OpDef(op=FixAdd, inputs=List(x9628, x9629)).name("x9631_x9630").srcCtx("lenet_loops.scala:130:17") } // FixAdd(x9628,x9629)
    // x9631 = SimpleStruct(ArrayBuffer((offset,x9630), (size,Const(2048)), (isLoad,Const(true))))
    val x9632 = withCtrl(x9635) { OpDef(op=BitAnd, inputs=List(b5871, b5863)).name("x9632").srcCtx("UnrollingBase.scala:28:66") } // And(b5871,b5863)
    val x9633 = withCtrl(x9635) { OpDef(op=BitAnd, inputs=List(x9632, b5677)).name("x9633").srcCtx("UnrollingBase.scala:28:66") } // And(x9632,b5677)
    val x9634_b9988_b9986 = withCtrl(x9635) { WriteMem(b9986, x9631_x9630).name("x9634_b9988_b9986").srcCtx("lenet_loops.scala:130:17") } // StreamWrite(x9620,x9631,x9633)
    val x9634_b9989_b9987 = withCtrl(x9635) { WriteMem(b9987, Const(2048)).name("x9634_b9989_b9987").srcCtx("lenet_loops.scala:130:17") } // StreamWrite(x9620,x9631,x9633)
    val x9636 = withCtrl(x9646) { FringeDenseLoad(dram=List(x9256), cmdStream=List(b9986, b9987), dataStream=List(x9621)).name("x9636").srcCtx("lenet_loops.scala:130:17") } // FringeDenseLoad(x9256,x9620,x9621)
    val x9637 = withCtrl(x9646) { Counter(min=Const(0), max=Const(512), step=Const(1), par=1).name("x9637").srcCtx("lenet_loops.scala:130:17") } // CounterNew(Const(0),Const(512),Const(1),Const(1))
    val x9638 = withCtrl(x9646) { CounterChain(List(x9637)).name("x9638").srcCtx("lenet_loops.scala:130:17") } // CounterChainNew(List(x9637))
    val x9645 = withCtrl(x9646) { LoopController(style=InnerPipe, level=InnerControl, cchain=x9638).name("x9645").srcCtx("lenet_loops.scala:130:17") } // UnrolledForeach(List(b5871, b5863, b5677),x9638,Block(Const(())),List(List(b5891)),List(List(b5892)))
    val b5891 = withCtrl(x9645) { CounterIter(x9637, None).name("b5891") } // b5891
    val b5892 = withCtrl(x9645) { Const(true).name("b5892") } // b5892
    val x9639 = withCtrl(x9645) { OpDef(op=BitAnd, inputs=List(b5892, b5871)).name("x9639").srcCtx("UnrollingBase.scala:28:66") } // And(b5892,b5871)
    val x9640 = withCtrl(x9645) { OpDef(op=BitAnd, inputs=List(b5863, b5677)).name("x9640").srcCtx("UnrollingBase.scala:28:66") } // And(b5863,b5677)
    val x9641 = withCtrl(x9645) { OpDef(op=BitAnd, inputs=List(x9639, x9640)).name("x9641").srcCtx("UnrollingBase.scala:28:66") } // And(x9639,x9640)
    val x9642_x9642 = withCtrl(x9645) { ReadMem(x9621).name("x9642_x9642").srcCtx("lenet_loops.scala:130:17") } // ParStreamRead(x9621,List(x9641))
    val x9643_x9643 = withCtrl(x9645) { x9642_x9642 } // VectorApply(x9642,0)
    val x9644 = withCtrl(x9645) { StoreBanks(List(List(x9615_d0_b0)), List(b5891), x9643_x9643).name("x9644").srcCtx("lenet_loops.scala:130:17") } // ParSRAMStore(x9615,List(List(b5891)),List(x9643),List(x9641))
    val x9647 = withCtrl(x9746) { Counter(min=Const(0), max=Const(20), step=Const(1), par=1).name("x9647").srcCtx("lenet_loops.scala:131:44") } // CounterNew(Const(0),Const(20),Const(1),Const(1))
    val x9648 = withCtrl(x9746) { CounterChain(List(x9647)).name("x9648").srcCtx("lenet_loops.scala:140:12") } // CounterChainNew(List(x9647))
    val x9709 = withCtrl(x9746) { LoopController(style=MetaPipe, level=OuterControl, cchain=x9648).name("x9709").srcCtx("lenet_loops.scala:140:12") } // UnrolledReduce(List(b5863, b5677),x9648,x9614,Block((x9614) => Const(())),List(List(b5906)),List(List(b5907)))
    val b5906 = withCtrl(x9709) { CounterIter(x9647, Some(0)).name("b5906") } // b5906
    val b5907 = withCtrl(x9709) { Const(true).name("b5907") } // b5907
    val x9649_d0_b0 = withCtrl(x9709) { SRAM(size=64, banking=Strided(banks=1, stride=8)).name("x9649_d0_b0").srcCtx("lenet_loops.scala:132:33:result") } // x9649 = SRAMNew(ArrayBuffer(Const(8), Const(8)))
    isAccum(x9649_d0_b0) = false
    bufferDepthOf(x9649_d0_b0) = 2
    staticDimsOf(x9649_d0_b0) = List(8, 8)
    val x9650 = withCtrl(x9709) { Counter(min=Const(0), max=Const(8), step=Const(1), par=1).name("x9650").srcCtx("lenet_loops.scala:133:44") } // CounterNew(Const(0),Const(8),Const(1),Const(1))
    val x9651 = withCtrl(x9709) { Counter(min=Const(0), max=Const(8), step=Const(1), par=1).name("x9651").srcCtx("lenet_loops.scala:133:23") } // CounterNew(Const(0),Const(8),Const(1),Const(1))
    val x9652 = withCtrl(x9709) { CounterChain(List(x9651,x9650)).name("x9652").srcCtx("lenet_loops.scala:133:51") } // CounterChainNew(List(x9651, x9650))
    val x9690 = withCtrl(x9709) { LoopController(style=MetaPipe, level=OuterControl, cchain=x9652).name("x9690").srcCtx("lenet_loops.scala:133:51") } // UnrolledForeach(List(b5907, b5863, b5677),x9652,Block(Const(())),List(List(b5912), List(b5913)),List(List(b5914), List(b5915)))
    val b5912 = withCtrl(x9690) { CounterIter(x9651, Some(0)).name("b5912") } // b5912
    val b5914 = withCtrl(x9690) { Const(true).name("b5914") } // b5914
    val b5913 = withCtrl(x9690) { CounterIter(x9650, Some(0)).name("b5913") } // b5913
    val b5915 = withCtrl(x9690) { Const(true).name("b5915") } // b5915
    val x9653_d0 = withCtrl(x9690) { Reg(init=Some(0.0)).name("x9653_d0").srcCtx("lenet_loops.scala:134:41:window") } // x9653 = RegNew(Const(0))
    isAccum(x9653_d0) = false
    bufferDepthOf(x9653_d0) = 2
    val x9653_d1 = withCtrl(x9690) { Reg(init=Some(0.0)).name("x9653_d1").srcCtx("lenet_loops.scala:134:41:window") } // x9653 = RegNew(Const(0))
    isAccum(x9653_d1) = true
    bufferDepthOf(x9653_d1) = 1
    val x9654 = withCtrl(x9690) { Counter(min=Const(0), max=Const(5), step=Const(1), par=1).name("x9654").srcCtx("lenet_loops.scala:134:81") } // CounterNew(Const(0),Const(5),Const(1),Const(1))
    val x9655 = withCtrl(x9690) { Counter(min=Const(0), max=Const(5), step=Const(1), par=1).name("x9655").srcCtx("lenet_loops.scala:134:63") } // CounterNew(Const(0),Const(5),Const(1),Const(1))
    val x9656 = withCtrl(x9690) { CounterChain(List(x9655,x9654)).name("x9656").srcCtx("lenet_loops.scala:136:16") } // CounterChainNew(List(x9655, x9654))
    val x9682 = withCtrl(x9690) { LoopController(style=InnerPipe, level=InnerControl, cchain=x9656).name("x9682").srcCtx("lenet_loops.scala:136:16") } // UnrolledReduce(List(b5914, b5915, b5907, b5863, b5677),x9656,x9653,Block((x9653) => Const(())),List(List(b5920), List(b5921)),List(List(b5922), List(b5923)))
    val b5920 = withCtrl(x9682) { CounterIter(x9655, Some(0)).name("b5920") } // b5920
    val b5922 = withCtrl(x9682) { Const(true).name("b5922") } // b5922
    val b5921 = withCtrl(x9682) { CounterIter(x9654, None).name("b5921") } // b5921
    val b5923 = withCtrl(x9682) { Const(true).name("b5923") } // b5923
    val x9657 = withCtrl(x9682) { OpDef(op=FixAdd, inputs=List(b5912, b5920)).name("x9657").srcCtx("lenet_loops.scala:135:35") } // FixAdd(b5912,b5920)
    val x9658 = withCtrl(x9682) { OpDef(op=FixAdd, inputs=List(b5913, b5921)).name("x9658").srcCtx("lenet_loops.scala:135:39") } // FixAdd(b5913,b5921)
    val x9659 = withCtrl(x9682) { OpDef(op=BitAnd, inputs=List(b5922, b5923)).name("x9659").srcCtx("UnrollingBase.scala:28:66") } // And(b5922,b5923)
    val x9660 = withCtrl(x9682) { OpDef(op=BitAnd, inputs=List(b5914, b5915)).name("x9660").srcCtx("UnrollingBase.scala:28:66") } // And(b5914,b5915)
    val x9661 = withCtrl(x9682) { OpDef(op=BitAnd, inputs=List(b5907, b5863)).name("x9661").srcCtx("UnrollingBase.scala:28:66") } // And(b5907,b5863)
    val x9662 = withCtrl(x9682) { OpDef(op=BitAnd, inputs=List(x9659, x9660)).name("x9662").srcCtx("UnrollingBase.scala:28:66") } // And(x9659,x9660)
    val x9663 = withCtrl(x9682) { OpDef(op=BitAnd, inputs=List(x9661, b5677)).name("x9663").srcCtx("UnrollingBase.scala:28:66") } // And(x9661,b5677)
    val x9664 = withCtrl(x9682) { OpDef(op=BitAnd, inputs=List(x9662, x9663)).name("x9664").srcCtx("UnrollingBase.scala:28:66") } // And(x9662,x9663)
    val x9665 = withCtrl(x9682) { LoadBanks(List(x9496_d0_b0), List(b5906, x9657, x9658)).name("x9665").srcCtx("lenet_loops.scala:135:26") } // ParSRAMLoad(x9496,List(List(b5906, x9657, x9658)),List(x9664))
    val x9666 = withCtrl(x9682) { x9665 } // VectorApply(x9665,0)
    val x9667 = withCtrl(x9682) { OpDef(op=FixMul, inputs=List(b5906, Const(25))).name("x9667").srcCtx("lenet_loops.scala:135:56") } // FixMul(b5906,Const(25))
    val x9668 = withCtrl(x9682) { OpDef(op=FixMul, inputs=List(b5920, Const(5))).name("x9668").srcCtx("lenet_loops.scala:135:63") } // FixMul(b5920,Const(5))
    val x9669 = withCtrl(x9682) { OpDef(op=FixAdd, inputs=List(x9667, x9668)).name("x9669").srcCtx("lenet_loops.scala:135:60") } // FixAdd(x9667,x9668)
    val x9670 = withCtrl(x9682) { OpDef(op=FixAdd, inputs=List(x9669, b5921)).name("x9670").srcCtx("lenet_loops.scala:135:66") } // FixAdd(x9669,b5921)
    val x9671 = withCtrl(x9682) { LoadBanks(List(x9615_d0_b0), List(x9670)).name("x9671").srcCtx("lenet_loops.scala:135:50") } // ParSRAMLoad(x9615,List(List(x9670)),List(x9664))
    val x9672 = withCtrl(x9682) { x9671 } // VectorApply(x9671,0)
    val x9673 = withCtrl(x9682) { OpDef(op=FixMul, inputs=List(x9666, x9672)).name("x9673").srcCtx("lenet_loops.scala:135:43") } // FixMul(x9666,x9672)
    val x9674 = withCtrl(x9682) { ReadMem(x9653_d1).name("x9674").srcCtx("lenet_loops.scala:136:16") } // RegRead(x9653)
    val x9675 = withCtrl(x9682) { OpDef(op=FixEql, inputs=List(b5920, Const(0))).name("x9675").srcCtx("lenet_loops.scala:136:16") } // FixEql(b5920,Const(0))
    val x9676 = withCtrl(x9682) { OpDef(op=FixEql, inputs=List(b5921, Const(0))).name("x9676").srcCtx("lenet_loops.scala:136:16") } // FixEql(b5921,Const(0))
    val x9677 = withCtrl(x9682) { OpDef(op=BitAnd, inputs=List(x9675, x9676)).name("x9677").srcCtx("lenet_loops.scala:136:16") } // And(x9675,x9676)
    val x9678 = withCtrl(x9682) { ReduceAccumOp(op=FixAdd, input=x9673, accum=x9674).name("x9678").srcCtx("lenet_loops.scala:136:18") } // FixAdd(x9673,x9674)
    val x9679 = withCtrl(x9682) { OpDef(op=BitAnd, inputs=List(x9660, x9661)).name("x9679").srcCtx("UnrollingBase.scala:28:66") } // And(x9660,x9661)
    val x9680 = withCtrl(x9682) { OpDef(op=BitAnd, inputs=List(x9679, b5677)).name("x9680").srcCtx("UnrollingBase.scala:28:66") } // And(x9679,b5677)
    val x9681_x9653_d0 = withCtrl(x9682) { WriteMem(x9653_d0, x9678).name("x9681_x9653_d0").srcCtx("lenet_loops.scala:136:16") } // RegWrite(x9653,x9678,x9680)
    antiDepsOf(x9681_x9653_d0)=List(x9674)
    val x9681_x9653_d1 = withCtrl(x9682) { WriteMem(x9653_d1, x9678).name("x9681_x9653_d1").srcCtx("lenet_loops.scala:136:16") } // RegWrite(x9653,x9678,x9680)
    antiDepsOf(x9681_x9653_d1)=List(x9674)
    val x9689 = withCtrl(x9690) { UnitController(style=SeqPipe, level=InnerControl).name("x9689").srcCtx("lenet_loops.scala:133:51") } // UnitPipe(List(b5914, b5915, b5907, b5863, b5677),Block(Const(())))
    val x9683 = withCtrl(x9689) { ReadMem(x9653_d0).name("x9683").srcCtx("lenet_loops.scala:137:37") } // RegRead(x9653)
    val x9684 = withCtrl(x9689) { OpDef(op=BitAnd, inputs=List(b5914, b5915)).name("x9684").srcCtx("UnrollingBase.scala:28:66") } // And(b5914,b5915)
    val x9685 = withCtrl(x9689) { OpDef(op=BitAnd, inputs=List(b5907, b5863)).name("x9685").srcCtx("UnrollingBase.scala:28:66") } // And(b5907,b5863)
    val x9686 = withCtrl(x9689) { OpDef(op=BitAnd, inputs=List(x9684, x9685)).name("x9686").srcCtx("UnrollingBase.scala:28:66") } // And(x9684,x9685)
    val x9687 = withCtrl(x9689) { OpDef(op=BitAnd, inputs=List(x9686, b5677)).name("x9687").srcCtx("UnrollingBase.scala:28:66") } // And(x9686,b5677)
    val x9688 = withCtrl(x9689) { StoreBanks(List(List(x9649_d0_b0)), List(b5912, b5913), x9683).name("x9688").srcCtx("lenet_loops.scala:137:28") } // SRAMStore(x9649,ArrayBuffer(Const(8), Const(8)),List(b5912, b5913),Const(0),x9683,x9687)
    val x9691 = withCtrl(x9709) { Counter(min=Const(0), max=Const(8), step=Const(1), par=1).name("x9691").srcCtx("lenet_loops.scala:140:12") } // CounterNew(Const(0),Const(8),Const(1),Const(1))
    val x9692 = withCtrl(x9709) { Counter(min=Const(0), max=Const(8), step=Const(1), par=1).name("x9692").srcCtx("lenet_loops.scala:140:12") } // CounterNew(Const(0),Const(8),Const(1),Const(1))
    def split1 = {
    val x9693 = withCtrl(x9709) { CounterChain(List(x9692,x9691)).name("x9693").srcCtx("lenet_loops.scala:140:12") } // CounterChainNew(ArrayBuffer(x9692, x9691))
    val x9708 = withCtrl(x9709) { LoopController(style=InnerPipe, level=InnerControl, cchain=x9693).name("x9708").srcCtx("lenet_loops.scala:140:12") } // UnrolledForeach(List(),x9693,Block(Const(())),ArrayBuffer(List(b5958), List(b5959)),ArrayBuffer(List(b5960), List(b5961)))
    val b5958 = withCtrl(x9708) { CounterIter(x9692, Some(0)).name("b5958") } // b5958
    val b5960 = withCtrl(x9708) { Const(true).name("b5960") } // b5960
    val b5959 = withCtrl(x9708) { CounterIter(x9691, None).name("b5959") } // b5959
    val b5961 = withCtrl(x9708) { Const(true).name("b5961") } // b5961
    val x9694 = withCtrl(x9708) { OpDef(op=BitAnd, inputs=List(b5960, b5961)).name("x9694").srcCtx("UnrollingBase.scala:28:66") } // And(b5960,b5961)
    val x9695 = withCtrl(x9708) { OpDef(op=BitAnd, inputs=List(b5863, b5677)).name("x9695").srcCtx("UnrollingBase.scala:28:66") } // And(b5863,b5677)
    val x9696 = withCtrl(x9708) { OpDef(op=BitAnd, inputs=List(x9694, x9695)).name("x9696").srcCtx("UnrollingBase.scala:28:66") } // And(x9694,x9695)
    val x9697 = withCtrl(x9708) { LoadBanks(List(x9649_d0_b0), List(b5958, b5959)).name("x9697").srcCtx("lenet_loops.scala:140:12") } // ParSRAMLoad(x9649,List(ArrayBuffer(b5958, b5959)),List(x9696))
    val x9698 = withCtrl(x9708) { x9697 } // VectorApply(x9697,0)
    val x9699 = withCtrl(x9708) { LoadBanks(List(x9614_d1_b0), List(b5958, b5959)).name("x9699").srcCtx("lenet_loops.scala:140:12") } // ParSRAMLoad(x9614,List(ArrayBuffer(b5958, b5959)),List(x9696))
    val x9700 = withCtrl(x9708) { x9699 } // VectorApply(x9699,0)
    val x9701 = withCtrl(x9708) { OpDef(op=BitAnd, inputs=List(b5907, b5863)).name("x9701").srcCtx("lenet_loops.scala:140:12") } // And(b5907,b5863)
    val x9702 = withCtrl(x9708) { OpDef(op=BitAnd, inputs=List(x9701, b5677)).name("x9702").srcCtx("lenet_loops.scala:140:12") } // And(x9701,b5677)
    val x9703 = withCtrl(x9708) { OpDef(op=BitAnd, inputs=List(x9702, x9696)).name("x9703").srcCtx("lenet_loops.scala:140:12") } // And(x9702,x9696)
    val x9704 = withCtrl(x9708) { OpDef(op=FixEql, inputs=List(b5906, Const(0))).name("x9704").srcCtx("lenet_loops.scala:140:12") } // FixEql(b5906,Const(0))
    val x9705 = withCtrl(x9708) { OpDef(op=FixAdd, inputs=List(x9698, x9700)).name("x9705").srcCtx("lenet_loops.scala:140:14") } // FixAdd(x9698,x9700)
    val x9706 = withCtrl(x9708) { OpDef(op=MuxOp, inputs=List(x9704, x9698, x9705)).name("x9706").srcCtx("lenet_loops.scala:140:12") } // Mux(x9704,x9698,x9705)
    val x9707 = withCtrl(x9708) { StoreBanks(List(List(x9614_d0_b0), List(x9614_d1_b0)), List(b5958, b5959), x9706).name("x9707").srcCtx("lenet_loops.scala:140:12") } // ParSRAMStore(x9614,List(ArrayBuffer(b5958, b5959)),List(x9706),List(x9696))
    antiDepsOf(x9707)=List(x9699)
    val x9710 = withCtrl(x9746) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x9710").srcCtx("lenet_loops.scala:142:29") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x9711 = withCtrl(x9746) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x9711").srcCtx("lenet_loops.scala:142:21") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x9712 = withCtrl(x9746) { CounterChain(List(x9711,x9710)).name("x9712").srcCtx("lenet_loops.scala:142:35") } // CounterChainNew(List(x9711, x9710))
    val x9745 = withCtrl(x9746) { LoopController(style=MetaPipe, level=OuterControl, cchain=x9712).name("x9745").srcCtx("lenet_loops.scala:142:35") } // UnrolledForeach(List(b5863, b5677),x9712,Block(Const(())),List(List(b5981), List(b5982)),List(List(b5983), List(b5984)))
    val b5981 = withCtrl(x9745) { CounterIter(x9711, Some(0)).name("b5981") } // b5981
    val b5983 = withCtrl(x9745) { Const(true).name("b5983") } // b5983
    val b5982 = withCtrl(x9745) { CounterIter(x9710, Some(0)).name("b5982") } // b5982
    val b5984 = withCtrl(x9745) { Const(true).name("b5984") } // b5984
    val x9713_d0 = withCtrl(x9745) { Reg(init=Some(0.0)).name("x9713_d0").srcCtx("lenet_loops.scala:143:36:out") } // x9713 = RegNew(Const(0))
    isAccum(x9713_d0) = false
    bufferDepthOf(x9713_d0) = 2
    val x9713_d1 = withCtrl(x9745) { Reg(init=Some(0.0)).name("x9713_d1").srcCtx("lenet_loops.scala:143:36:out") } // x9713 = RegNew(Const(0))
    isAccum(x9713_d1) = true
    bufferDepthOf(x9713_d1) = 1
    val x9714 = withCtrl(x9745) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x9714").srcCtx("lenet_loops.scala:143:57") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x9715 = withCtrl(x9745) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x9715").srcCtx("lenet_loops.scala:143:49") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x9716 = withCtrl(x9745) { CounterChain(List(x9715,x9714)).name("x9716").srcCtx("lenet_loops.scala:145:15") } // CounterChainNew(List(x9715, x9714))
    val x9738 = withCtrl(x9745) { LoopController(style=InnerPipe, level=InnerControl, cchain=x9716).name("x9738").srcCtx("lenet_loops.scala:145:15") } // UnrolledReduce(List(b5983, b5984, b5863, b5677),x9716,x9713,Block((x9713) => Const(())),List(List(b5989), List(b5990)),List(List(b5991), List(b5992)))
    val b5989 = withCtrl(x9738) { CounterIter(x9715, Some(0)).name("b5989") } // b5989
    val b5991 = withCtrl(x9738) { Const(true).name("b5991") } // b5991
    val b5990 = withCtrl(x9738) { CounterIter(x9714, None).name("b5990") } // b5990
    val b5992 = withCtrl(x9738) { Const(true).name("b5992") } // b5992
    val x9717 = withCtrl(x9738) { OpDef(op=FixSla, inputs=List(b5981, Const(1))).name("x9717").srcCtx("lenet_loops.scala:144:44") } // FixLsh(b5981,Const(1))
    val x9718 = withCtrl(x9738) { OpDef(op=FixAdd, inputs=List(x9717, b5989)).name("x9718").srcCtx("lenet_loops.scala:144:47") } // FixAdd(x9717,b5989)
    val x9719 = withCtrl(x9738) { OpDef(op=FixSla, inputs=List(b5982, Const(1))).name("x9719").srcCtx("lenet_loops.scala:144:54") } // FixLsh(b5982,Const(1))
    val x9720 = withCtrl(x9738) { OpDef(op=FixAdd, inputs=List(x9719, b5990)).name("x9720").srcCtx("lenet_loops.scala:144:57") } // FixAdd(x9719,b5990)
    val x9721 = withCtrl(x9738) { OpDef(op=BitAnd, inputs=List(b5991, b5992)).name("x9721").srcCtx("UnrollingBase.scala:28:66") } // And(b5991,b5992)
    val x9722 = withCtrl(x9738) { OpDef(op=BitAnd, inputs=List(b5983, b5984)).name("x9722").srcCtx("UnrollingBase.scala:28:66") } // And(b5983,b5984)
    val x9723 = withCtrl(x9738) { OpDef(op=BitAnd, inputs=List(b5863, b5677)).name("x9723").srcCtx("UnrollingBase.scala:28:66") } // And(b5863,b5677)
    val x9724 = withCtrl(x9738) { OpDef(op=BitAnd, inputs=List(x9721, x9722)).name("x9724").srcCtx("UnrollingBase.scala:28:66") } // And(x9721,x9722)
    val x9725 = withCtrl(x9738) { OpDef(op=BitAnd, inputs=List(x9724, x9723)).name("x9725").srcCtx("UnrollingBase.scala:28:66") } // And(x9724,x9723)
    val x9726 = withCtrl(x9738) { LoadBanks(List(x9614_d0_b0), List(x9718, x9720)).name("x9726").srcCtx("lenet_loops.scala:144:42") } // ParSRAMLoad(x9614,List(List(x9718, x9720)),List(x9725))
    val x9727 = withCtrl(x9738) { x9726 } // VectorApply(x9726,0)
    val x9728 = withCtrl(x9738) { LoadBanks(List(x9401_d0_b0), List(b5862)).name("x9728").srcCtx("lenet_loops.scala:144:72") } // SRAMLoad(x9401,ArrayBuffer(Const(64)),List(b5862),Const(0),x9725)
    val x9729 = withCtrl(x9738) { OpDef(op=FixAdd, inputs=List(x9727, x9728)).name("x9729").srcCtx("lenet_loops.scala:144:63") } // FixAdd(x9727,x9728)
    val x9730 = withCtrl(x9738) { OpDef(op=FixMax, inputs=List(Const(0.0), x9729)).name("x9730").srcCtx("lenet_loops.scala:144:18") } // Max(Const(0),x9729)
    val x9731 = withCtrl(x9738) { ReadMem(x9713_d1).name("x9731").srcCtx("lenet_loops.scala:145:15") } // RegRead(x9713)
    val x9732 = withCtrl(x9738) { OpDef(op=FixEql, inputs=List(b5989, Const(0))).name("x9732").srcCtx("lenet_loops.scala:145:15") } // FixEql(b5989,Const(0))
    val x9733 = withCtrl(x9738) { OpDef(op=FixEql, inputs=List(b5990, Const(0))).name("x9733").srcCtx("lenet_loops.scala:145:15") } // FixEql(b5990,Const(0))
    val x9734 = withCtrl(x9738) { OpDef(op=BitAnd, inputs=List(x9732, x9733)).name("x9734").srcCtx("lenet_loops.scala:145:15") } // And(x9732,x9733)
    val x9735 = withCtrl(x9738) { ReduceAccumOp(op=FixMax, input=x9730, accum=x9731).name("x9735").srcCtx("lenet_loops.scala:145:29") } // Max(x9730,x9731)
    val x9736 = withCtrl(x9738) { OpDef(op=BitAnd, inputs=List(x9722, x9723)).name("x9736").srcCtx("UnrollingBase.scala:28:66") } // And(x9722,x9723)
    val x9737_x9713_d0 = withCtrl(x9738) { WriteMem(x9713_d0, x9735).name("x9737_x9713_d0").srcCtx("lenet_loops.scala:145:15") } // RegWrite(x9713,x9735,x9736)
    antiDepsOf(x9737_x9713_d0)=List(x9731)
    val x9737_x9713_d1 = withCtrl(x9738) { WriteMem(x9713_d1, x9735).name("x9737_x9713_d1").srcCtx("lenet_loops.scala:145:15") } // RegWrite(x9713,x9735,x9736)
    antiDepsOf(x9737_x9713_d1)=List(x9731)
    val x9744 = withCtrl(x9745) { UnitController(style=SeqPipe, level=InnerControl).name("x9744").srcCtx("lenet_loops.scala:142:35") } // UnitPipe(List(b5983, b5984, b5863, b5677),Block(Const(())))
    val x9739 = withCtrl(x9744) { ReadMem(x9713_d0).name("x9739").srcCtx("lenet_loops.scala:146:43") } // RegRead(x9713)
    val x9740 = withCtrl(x9744) { OpDef(op=BitAnd, inputs=List(b5983, b5984)).name("x9740").srcCtx("UnrollingBase.scala:28:66") } // And(b5983,b5984)
    val x9741 = withCtrl(x9744) { OpDef(op=BitAnd, inputs=List(b5863, b5677)).name("x9741").srcCtx("UnrollingBase.scala:28:66") } // And(b5863,b5677)
    val x9742 = withCtrl(x9744) { OpDef(op=BitAnd, inputs=List(x9740, x9741)).name("x9742").srcCtx("UnrollingBase.scala:28:66") } // And(x9740,x9741)
    val x9743 = withCtrl(x9744) { StoreBanks(List(List(x9611_d0_b0)), List(b5862, b5981, b5982), x9739).name("x9743").srcCtx("lenet_loops.scala:146:37") } // SRAMStore(x9611,ArrayBuffer(Const(50), Const(4), Const(4)),List(b5862, b5981, b5982),Const(0),x9739,x9742)
    val x9747_d0_b0 = withCtrl(x9923) { SRAM(size=800, banking=Strided(banks=1, stride=1)).name("x9747_d0_b0").srcCtx("lenet_loops.scala:154:32:tmp3_SRAM") } // x9747 = SRAMNew(ArrayBuffer(Const(800)))
    isAccum(x9747_d0_b0) = false
    bufferDepthOf(x9747_d0_b0) = 2
    staticDimsOf(x9747_d0_b0) = List(800)
    val x9748 = withCtrl(x9923) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x9748").srcCtx("lenet_loops.scala:155:36") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x9749 = withCtrl(x9923) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x9749").srcCtx("lenet_loops.scala:155:28") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x9750 = withCtrl(x9923) { Counter(min=Const(0), max=Const(50), step=Const(1), par=1).name("x9750").srcCtx("lenet_loops.scala:155:20") } // CounterNew(Const(0),Const(50),Const(1),Const(1))
    val x9751 = withCtrl(x9923) { CounterChain(List(x9750,x9749,x9748)).name("x9751").srcCtx("lenet_loops.scala:155:42") } // CounterChainNew(List(x9750, x9749, x9748))
    val x9763 = withCtrl(x9923) { LoopController(style=InnerPipe, level=InnerControl, cchain=x9751).name("x9763").srcCtx("lenet_loops.scala:155:42") } // UnrolledForeach(List(b5677),x9751,Block(Const(())),List(List(b6028), List(b6029), List(b6030)),List(List(b6031), List(b6032), List(b6033)))
    val b6028 = withCtrl(x9763) { CounterIter(x9750, Some(0)).name("b6028") } // b6028
    val b6031 = withCtrl(x9763) { Const(true).name("b6031") } // b6031
    val b6029 = withCtrl(x9763) { CounterIter(x9749, Some(0)).name("b6029") } // b6029
    val b6032 = withCtrl(x9763) { Const(true).name("b6032") } // b6032
    val b6030 = withCtrl(x9763) { CounterIter(x9748, None).name("b6030") } // b6030
    val b6033 = withCtrl(x9763) { Const(true).name("b6033") } // b6033
    val x9752 = withCtrl(x9763) { OpDef(op=FixMul, inputs=List(b6030, Const(50))).name("x9752").srcCtx("lenet_loops.scala:156:22") } // FixMul(b6030,Const(50))
    val x9753 = withCtrl(x9763) { OpDef(op=FixSla, inputs=List(b6029, Const(2))).name("x9753").srcCtx("lenet_loops.scala:156:29") } // FixLsh(b6029,Const(2))
    val x9754 = withCtrl(x9763) { OpDef(op=FixMul, inputs=List(x9753, Const(50))).name("x9754").srcCtx("lenet_loops.scala:156:31") } // FixMul(x9753,Const(50))
    val x9755 = withCtrl(x9763) { OpDef(op=FixAdd, inputs=List(x9752, x9754)).name("x9755").srcCtx("lenet_loops.scala:156:26") } // FixAdd(x9752,x9754)
    val x9756 = withCtrl(x9763) { OpDef(op=FixAdd, inputs=List(x9755, b6028)).name("x9756").srcCtx("lenet_loops.scala:156:35") } // FixAdd(x9755,b6028)
    val x9757 = withCtrl(x9763) { OpDef(op=BitAnd, inputs=List(b6031, b6032)).name("x9757").srcCtx("UnrollingBase.scala:28:66") } // And(b6031,b6032)
    val x9758 = withCtrl(x9763) { OpDef(op=BitAnd, inputs=List(b6033, b5677)).name("x9758").srcCtx("UnrollingBase.scala:28:66") } // And(b6033,b5677)
    val x9759 = withCtrl(x9763) { OpDef(op=BitAnd, inputs=List(x9757, x9758)).name("x9759").srcCtx("UnrollingBase.scala:28:66") } // And(x9757,x9758)
    val x9760 = withCtrl(x9763) { LoadBanks(List(x9611_d0_b0), List(b6028, b6029, b6030)).name("x9760").srcCtx("lenet_loops.scala:156:51") } // ParSRAMLoad(x9611,List(List(b6028, b6029, b6030)),List(x9759))
    val x9761 = withCtrl(x9763) { x9760 } // VectorApply(x9760,0)
    val x9762 = withCtrl(x9763) { StoreBanks(List(List(x9747_d0_b0)), List(x9756), x9761).name("x9762").srcCtx("lenet_loops.scala:156:40") } // ParSRAMStore(x9747,List(List(x9756)),List(x9761),List(x9759))
    val x9764_d0_b0 = withCtrl(x9923) { SRAM(size=500, banking=Strided(banks=1, stride=1)).name("x9764_d0_b0").srcCtx("lenet_loops.scala:160:32:tmp4_SRAM") } // x9764 = SRAMNew(ArrayBuffer(Const(500)))
    isAccum(x9764_d0_b0) = false
    bufferDepthOf(x9764_d0_b0) = 2
    staticDimsOf(x9764_d0_b0) = List(500)
    val x9765 = withCtrl(x9923) { Counter(min=Const(0), max=Const(100), step=Const(1), par=1).name("x9765").srcCtx("lenet_loops.scala:161:26") } // CounterNew(Const(0),Const(100),Const(1),Const(1))
    val x9766 = withCtrl(x9923) { CounterChain(List(x9765)).name("x9766").srcCtx("lenet_loops.scala:161:39") } // CounterChainNew(List(x9765))
    val x9832 = withCtrl(x9923) { LoopController(style=MetaPipe, level=OuterControl, cchain=x9766).name("x9832").srcCtx("lenet_loops.scala:161:39") } // UnrolledForeach(List(b5677),x9766,Block(Const(())),List(List(b6049)),List(List(b6050)))
    val b6049 = withCtrl(x9832) { CounterIter(x9765, Some(0)).name("b6049") } // b6049
    val b6050 = withCtrl(x9832) { Const(true).name("b6050") } // b6050
    val x9767_d0_b0 = withCtrl(x9832) { SRAM(size=4000, banking=Strided(banks=16, stride=1)).name("x9767_d0_b0").srcCtx("lenet_loops.scala:162:36:c4_row_SRAM") } // x9767 = SRAMNew(ArrayBuffer(Const(4000)))
    isAccum(x9767_d0_b0) = false
    bufferDepthOf(x9767_d0_b0) = 2
    staticDimsOf(x9767_d0_b0) = List(4000)
    val x9769 = withCtrl(x9832) { UnitController(style=SeqPipe, level=InnerControl).name("x9769").srcCtx("lenet_loops.scala:161:39") } // UnitPipe(List(b6050, b5677),Block(Const(())))
    val x9768 = withCtrl(x9769) { OpDef(op=FixAdd, inputs=List(b6049, Const(1))).name("x9768").srcCtx("lenet_loops.scala:163:35") } // FixAdd(b6049,Const(1))
    val x9770 = withCtrl(x9832) { Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x9770").srcCtx("lenet_loops.scala:163:23") } // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x9771 = withCtrl(x9832) { CounterChain(List(x9770)).name("x9771").srcCtx("lenet_loops.scala:163:23") } // CounterChainNew(List(x9770))
    val x9798 = withCtrl(x9832) { LoopController(style=StreamPipe, level=OuterControl, cchain=x9771).name("x9798").srcCtx("lenet_loops.scala:163:23") } // UnrolledForeach(List(b6050, b5677),x9771,Block(Const(())),List(List(b6056)),List(List(b6057)))
    val b6056 = withCtrl(x9798) { CounterIter(x9770, Some(0)).name("b6056") } // b6056
    val b6057 = withCtrl(x9798) { Const(true).name("b6057") } // b6057
    val b9990 = withCtrl(x9798) { StreamOut(field="offset").name("b9990").srcCtx("lenet_loops.scala:163:23") } // x9772 = StreamOutNew(BurstCmdBus)
    isAccum(b9990) = false
    bufferDepthOf(b9990) = 1
    val b9991 = withCtrl(x9798) { StreamOut(field="size").name("b9991").srcCtx("lenet_loops.scala:163:23") } // x9772 = StreamOutNew(BurstCmdBus)
    isAccum(b9991) = false
    bufferDepthOf(b9991) = 1
    val x9773 = withCtrl(x9798) { StreamIn(field="data").name("x9773").srcCtx("lenet_loops.scala:163:23") } // x9773 = StreamInNew(BurstDataBus())
    isAccum(x9773) = false
    bufferDepthOf(x9773) = 1
    val x9787 = withCtrl(x9798) { UnitController(style=SeqPipe, level=InnerControl).name("x9787").srcCtx("lenet_loops.scala:163:23") } // UnitPipe(List(b6057, b6050, b5677),Block(x9786))
    val x9774 = withCtrl(x9787) { OpDef(op=FixAdd, inputs=List(b6049, b6056)).name("x9774").srcCtx("lenet_loops.scala:163:23") } // FixAdd(b6049,b6056)
    val x9775 = withCtrl(x9787) { x9774 } // FixConvert(x9774,TRUE,_32,_0) (Same Type. No op)
    val x9776 = withCtrl(x9787) { OpDef(op=FixMul, inputs=List(x9775, Const(4000))).name("x9776").srcCtx("lenet_loops.scala:163:23") } // FixMul(x9775,Const(4000))
    val x9777 = withCtrl(x9787) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x9778 = withCtrl(x9787) { OpDef(op=FixAdd, inputs=List(x9776, x9777)).name("x9778").srcCtx("lenet_loops.scala:163:23") } // FixAdd(x9776,x9777)
    val x9779 = withCtrl(x9787) { OpDef(op=FixSla, inputs=List(x9778, Const(2))).name("x9779").srcCtx("lenet_loops.scala:163:23") } // FixLsh(x9778,Const(2))
    val x9780 = withCtrl(x9787) { x9779 } // FixConvert(x9779,TRUE,_64,_0)
    val x9781 = withCtrl(x9787) { DramAddress(x9258).name("x9781").srcCtx("lenet_loops.scala:163:23") } // GetDRAMAddress(x9258)
    val x9783_x9782 = withCtrl(x9787) { OpDef(op=FixAdd, inputs=List(x9780, x9781)).name("x9783_x9782").srcCtx("lenet_loops.scala:163:23") } // FixAdd(x9780,x9781)
    // x9783 = SimpleStruct(ArrayBuffer((offset,x9782), (size,Const(16000)), (isLoad,Const(true))))
    val x9784 = withCtrl(x9787) { OpDef(op=BitAnd, inputs=List(b6057, b6050)).name("x9784").srcCtx("UnrollingBase.scala:28:66") } // And(b6057,b6050)
    val x9785 = withCtrl(x9787) { OpDef(op=BitAnd, inputs=List(x9784, b5677)).name("x9785").srcCtx("UnrollingBase.scala:28:66") } // And(x9784,b5677)
    val x9786_b9992_b9990 = withCtrl(x9787) { WriteMem(b9990, x9783_x9782).name("x9786_b9992_b9990").srcCtx("lenet_loops.scala:163:23") } // StreamWrite(x9772,x9783,x9785)
    val x9786_b9993_b9991 = withCtrl(x9787) { WriteMem(b9991, Const(16000)).name("x9786_b9993_b9991").srcCtx("lenet_loops.scala:163:23") } // StreamWrite(x9772,x9783,x9785)
    val x9788 = withCtrl(x9798) { FringeDenseLoad(dram=List(x9258), cmdStream=List(b9990, b9991), dataStream=List(x9773)).name("x9788").srcCtx("lenet_loops.scala:163:23") } // FringeDenseLoad(x9258,x9772,x9773)
    val x9789 = withCtrl(x9798) { Counter(min=Const(0), max=Const(4000), step=Const(1), par=16).name("x9789").srcCtx("lenet_loops.scala:163:23") } // CounterNew(Const(0),Const(4000),Const(1),Const(16))
    val x9790 = withCtrl(x9798) { CounterChain(List(x9789)).name("x9790").srcCtx("lenet_loops.scala:163:23") } // CounterChainNew(List(x9789))
    val x9797 = withCtrl(x9798) { LoopController(style=InnerPipe, level=InnerControl, cchain=x9790).name("x9797").srcCtx("lenet_loops.scala:163:23") } // UnrolledForeach(List(b6057, b6050, b5677),x9790,Block(Const(())),List(List(b6077)),List(List(b6078)))
    val b6077 = withCtrl(x9797) { CounterIter(x9789, None).name("b6077") } // b6077
    val b6078 = withCtrl(x9797) { Const(true).name("b6078") } // b6078
    val x9791 = withCtrl(x9797) { OpDef(op=BitAnd, inputs=List(b6078, b6057)).name("x9791").srcCtx("UnrollingBase.scala:28:66") } // And(b6078,b6057)
    val x9792 = withCtrl(x9797) { OpDef(op=BitAnd, inputs=List(b6050, b5677)).name("x9792").srcCtx("UnrollingBase.scala:28:66") } // And(b6050,b5677)
    val x9793 = withCtrl(x9797) { OpDef(op=BitAnd, inputs=List(x9791, x9792)).name("x9793").srcCtx("UnrollingBase.scala:28:66") } // And(x9791,x9792)
    val x9794_x9794 = withCtrl(x9797) { ReadMem(x9773).name("x9794_x9794").srcCtx("lenet_loops.scala:163:23") } // ParStreamRead(x9773,List(x9793))
    val x9795_x9795 = withCtrl(x9797) { x9794_x9794 } // VectorApply(x9794,0)
    val x9796 = withCtrl(x9797) { StoreBanks(List(List(x9767_d0_b0)), List(b6077), x9795_x9795).name("x9796").srcCtx("lenet_loops.scala:163:23") } // ParSRAMStore(x9767,List(List(b6077)),List(x9795),List(x9793))
    val x9799 = withCtrl(x9832) { Counter(min=Const(0), max=Const(5), step=Const(1), par=1).name("x9799").srcCtx("lenet_loops.scala:164:21") } // CounterNew(Const(0),Const(5),Const(1),Const(1))
    val x9800 = withCtrl(x9832) { CounterChain(List(x9799)).name("x9800").srcCtx("lenet_loops.scala:164:26") } // CounterChainNew(List(x9799))
    val x9831 = withCtrl(x9832) { LoopController(style=MetaPipe, level=OuterControl, cchain=x9800).name("x9831").srcCtx("lenet_loops.scala:164:26") } // UnrolledForeach(List(b6050, b5677),x9800,Block(Const(())),List(List(b6089)),List(List(b6090)))
    val b6089 = withCtrl(x9831) { CounterIter(x9799, Some(0)).name("b6089") } // b6089
    val b6090 = withCtrl(x9831) { Const(true).name("b6090") } // b6090
    val x9801_d0 = withCtrl(x9831) { Reg(init=Some(0.0)).name("x9801_d0").srcCtx("lenet_loops.scala:165:37:prod") } // x9801 = RegNew(Const(0))
    isAccum(x9801_d0) = false
    bufferDepthOf(x9801_d0) = 2
    val x9801_d1 = withCtrl(x9831) { Reg(init=Some(0.0)).name("x9801_d1").srcCtx("lenet_loops.scala:165:37:prod") } // x9801 = RegNew(Const(0))
    isAccum(x9801_d1) = true
    bufferDepthOf(x9801_d1) = 1
    val x9802 = withCtrl(x9831) { Counter(min=Const(0), max=Const(800), step=Const(1), par=1).name("x9802").srcCtx("lenet_loops.scala:165:57") } // CounterNew(Const(0),Const(800),Const(1),Const(1))
    val x9803 = withCtrl(x9831) { CounterChain(List(x9802)).name("x9803").srcCtx("lenet_loops.scala:167:14") } // CounterChainNew(List(x9802))
    val x9820 = withCtrl(x9831) { LoopController(style=InnerPipe, level=InnerControl, cchain=x9803).name("x9820").srcCtx("lenet_loops.scala:167:14") } // UnrolledReduce(List(b6090, b6050, b5677),x9803,x9801,Block((x9801) => Const(())),List(List(b6094)),List(List(b6095)))
    val b6094 = withCtrl(x9820) { CounterIter(x9802, None).name("b6094") } // b6094
    val b6095 = withCtrl(x9820) { Const(true).name("b6095") } // b6095
    val x9804 = withCtrl(x9820) { OpDef(op=BitAnd, inputs=List(b6095, b6090)).name("x9804").srcCtx("UnrollingBase.scala:28:66") } // And(b6095,b6090)
    val x9805 = withCtrl(x9820) { OpDef(op=BitAnd, inputs=List(b6050, b5677)).name("x9805").srcCtx("UnrollingBase.scala:28:66") } // And(b6050,b5677)
    val x9806 = withCtrl(x9820) { OpDef(op=BitAnd, inputs=List(x9804, x9805)).name("x9806").srcCtx("UnrollingBase.scala:28:66") } // And(x9804,x9805)
    val x9807 = withCtrl(x9820) { LoadBanks(List(x9747_d0_b0), List(b6094)).name("x9807").srcCtx("lenet_loops.scala:166:24") } // ParSRAMLoad(x9747,List(List(b6094)),List(x9806))
    val x9808 = withCtrl(x9820) { x9807 } // VectorApply(x9807,0)
    val x9809 = withCtrl(x9820) { OpDef(op=FixMul, inputs=List(b6089, Const(800))).name("x9809").srcCtx("lenet_loops.scala:166:52") } // FixMul(b6089,Const(800))
    val x9810 = withCtrl(x9820) { OpDef(op=FixAdd, inputs=List(x9809, b6094)).name("x9810").srcCtx("lenet_loops.scala:166:57") } // FixAdd(x9809,b6094)
    val x9811 = withCtrl(x9820) { LoadBanks(List(x9767_d0_b0), List(x9810)).name("x9811").srcCtx("lenet_loops.scala:166:44") } // ParSRAMLoad(x9767,List(List(x9810)),List(x9806))
    val x9812 = withCtrl(x9820) { x9811 } // VectorApply(x9811,0)
    val x9813 = withCtrl(x9820) { OpDef(op=FixMul, inputs=List(x9808, x9812)).name("x9813").srcCtx("lenet_loops.scala:166:31") } // FixMul(x9808,x9812)
    val x9814 = withCtrl(x9820) { ReadMem(x9801_d1).name("x9814").srcCtx("lenet_loops.scala:167:14") } // RegRead(x9801)
    val x9815 = withCtrl(x9820) { OpDef(op=FixEql, inputs=List(b6094, Const(0))).name("x9815").srcCtx("lenet_loops.scala:167:14") } // FixEql(b6094,Const(0))
    val x9816 = withCtrl(x9820) { ReduceAccumOp(op=FixAdd, input=x9813, accum=x9814).name("x9816").srcCtx("lenet_loops.scala:167:16") } // FixAdd(x9813,x9814)
    val x9817 = withCtrl(x9820) { OpDef(op=BitAnd, inputs=List(b6090, b6050)).name("x9817").srcCtx("UnrollingBase.scala:28:66") } // And(b6090,b6050)
    val x9818 = withCtrl(x9820) { OpDef(op=BitAnd, inputs=List(x9817, b5677)).name("x9818").srcCtx("UnrollingBase.scala:28:66") } // And(x9817,b5677)
    val x9819_x9801_d0 = withCtrl(x9820) { WriteMem(x9801_d0, x9816).name("x9819_x9801_d0").srcCtx("lenet_loops.scala:167:14") } // RegWrite(x9801,x9816,x9818)
    antiDepsOf(x9819_x9801_d0)=List(x9814)
    val x9819_x9801_d1 = withCtrl(x9820) { WriteMem(x9801_d1, x9816).name("x9819_x9801_d1").srcCtx("lenet_loops.scala:167:14") } // RegWrite(x9801,x9816,x9818)
    antiDepsOf(x9819_x9801_d1)=List(x9814)
    val x9830 = withCtrl(x9831) { UnitController(style=SeqPipe, level=InnerControl).name("x9830").srcCtx("lenet_loops.scala:164:26") } // UnitPipe(List(b6090, b6050, b5677),Block(Const(())))
    val x9821 = withCtrl(x9830) { OpDef(op=FixMul, inputs=List(b6049, Const(5))).name("x9821").srcCtx("lenet_loops.scala:168:28") } // FixMul(b6049,Const(5))
    val x9822 = withCtrl(x9830) { OpDef(op=FixAdd, inputs=List(x9821, b6089)).name("x9822").srcCtx("lenet_loops.scala:168:31") } // FixAdd(x9821,b6089)
    val x9823 = withCtrl(x9830) { ReadMem(x9801_d0).name("x9823").srcCtx("lenet_loops.scala:168:62") } // RegRead(x9801)
    val x9824 = withCtrl(x9830) { OpDef(op=BitAnd, inputs=List(b6090, b6050)).name("x9824").srcCtx("UnrollingBase.scala:28:66") } // And(b6090,b6050)
    val x9825 = withCtrl(x9830) { OpDef(op=BitAnd, inputs=List(x9824, b5677)).name("x9825").srcCtx("UnrollingBase.scala:28:66") } // And(x9824,b5677)
    val x9826 = withCtrl(x9830) { LoadBanks(List(x9420_d0_b0), List(x9822)).name("x9826").srcCtx("lenet_loops.scala:168:77") } // SRAMLoad(x9420,ArrayBuffer(Const(512)),List(x9822),Const(0),x9825)
    val x9827 = withCtrl(x9830) { OpDef(op=FixAdd, inputs=List(x9823, x9826)).name("x9827").srcCtx("lenet_loops.scala:168:68") } // FixAdd(x9823,x9826)
    val x9828 = withCtrl(x9830) { OpDef(op=FixMax, inputs=List(Const(0.0), x9827)).name("x9828").srcCtx("lenet_loops.scala:168:47") } // Max(Const(0),x9827)
    val x9829 = withCtrl(x9830) { StoreBanks(List(List(x9764_d0_b0)), List(x9822), x9828).name("x9829").srcCtx("lenet_loops.scala:168:42") } // SRAMStore(x9764,ArrayBuffer(Const(500)),List(x9822),Const(0),x9828,x9825)
    val x9833_d0_b0 = withCtrl(x9923) { SRAM(size=32, banking=Strided(banks=1, stride=1)).name("x9833_d0_b0").srcCtx("lenet_loops.scala:175:32:tmp5_SRAM") } // x9833 = SRAMNew(ArrayBuffer(Const(32)))
    isAccum(x9833_d0_b0) = false
    bufferDepthOf(x9833_d0_b0) = 2
    staticDimsOf(x9833_d0_b0) = List(32)
    val x9834 = withCtrl(x9923) { Counter(min=Const(0), max=Const(10), step=Const(1), par=1).name("x9834").srcCtx("lenet_loops.scala:176:25") } // CounterNew(Const(0),Const(10),Const(1),Const(1))
    val x9835 = withCtrl(x9923) { CounterChain(List(x9834)).name("x9835").srcCtx("lenet_loops.scala:176:38") } // CounterChainNew(List(x9834))
    val x9890 = withCtrl(x9923) { LoopController(style=MetaPipe, level=OuterControl, cchain=x9835).name("x9890").srcCtx("lenet_loops.scala:176:38") } // UnrolledForeach(List(b5677),x9835,Block(Const(())),List(List(b6128)),List(List(b6129)))
    val b6128 = withCtrl(x9890) { CounterIter(x9834, Some(0)).name("b6128") } // b6128
    val b6129 = withCtrl(x9890) { Const(true).name("b6129") } // b6129
    val x9836_d0_b0 = withCtrl(x9890) { SRAM(size=512, banking=Strided(banks=1, stride=1)).name("x9836_d0_b0").srcCtx("lenet_loops.scala:177:36:c6_row_SRAM") } // x9836 = SRAMNew(ArrayBuffer(Const(512)))
    isAccum(x9836_d0_b0) = false
    bufferDepthOf(x9836_d0_b0) = 2
    staticDimsOf(x9836_d0_b0) = List(512)
    val x9838 = withCtrl(x9890) { UnitController(style=SeqPipe, level=InnerControl).name("x9838").srcCtx("lenet_loops.scala:176:38") } // UnitPipe(List(b6129, b5677),Block(Const(())))
    val x9837 = withCtrl(x9838) { OpDef(op=FixAdd, inputs=List(b6128, Const(1))).name("x9837").srcCtx("lenet_loops.scala:178:35") } // FixAdd(b6128,Const(1))
    val x9839 = withCtrl(x9890) { Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x9839").srcCtx("lenet_loops.scala:178:23") } // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x9840 = withCtrl(x9890) { CounterChain(List(x9839)).name("x9840").srcCtx("lenet_loops.scala:178:23") } // CounterChainNew(List(x9839))
    val x9867 = withCtrl(x9890) { LoopController(style=StreamPipe, level=OuterControl, cchain=x9840).name("x9867").srcCtx("lenet_loops.scala:178:23") } // UnrolledForeach(List(b6129, b5677),x9840,Block(Const(())),List(List(b6135)),List(List(b6136)))
    val b6135 = withCtrl(x9867) { CounterIter(x9839, Some(0)).name("b6135") } // b6135
    val b6136 = withCtrl(x9867) { Const(true).name("b6136") } // b6136
    val b9994 = withCtrl(x9867) { StreamOut(field="offset").name("b9994").srcCtx("lenet_loops.scala:178:23") } // x9841 = StreamOutNew(BurstCmdBus)
    isAccum(b9994) = false
    bufferDepthOf(b9994) = 1
    val b9995 = withCtrl(x9867) { StreamOut(field="size").name("b9995").srcCtx("lenet_loops.scala:178:23") } // x9841 = StreamOutNew(BurstCmdBus)
    isAccum(b9995) = false
    bufferDepthOf(b9995) = 1
    val x9842 = withCtrl(x9867) { StreamIn(field="data").name("x9842").srcCtx("lenet_loops.scala:178:23") } // x9842 = StreamInNew(BurstDataBus())
    isAccum(x9842) = false
    bufferDepthOf(x9842) = 1
    val x9856 = withCtrl(x9867) { UnitController(style=SeqPipe, level=InnerControl).name("x9856").srcCtx("lenet_loops.scala:178:23") } // UnitPipe(List(b6136, b6129, b5677),Block(x9855))
    val x9843 = withCtrl(x9856) { OpDef(op=FixAdd, inputs=List(b6128, b6135)).name("x9843").srcCtx("lenet_loops.scala:178:23") } // FixAdd(b6128,b6135)
    val x9844 = withCtrl(x9856) { x9843 } // FixConvert(x9843,TRUE,_32,_0) (Same Type. No op)
    val x9845 = withCtrl(x9856) { OpDef(op=FixSla, inputs=List(x9844, Const(9))).name("x9845").srcCtx("lenet_loops.scala:178:23") } // FixLsh(x9844,Const(9))
    val x9846 = withCtrl(x9856) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x9847 = withCtrl(x9856) { OpDef(op=FixAdd, inputs=List(x9845, x9846)).name("x9847").srcCtx("lenet_loops.scala:178:23") } // FixAdd(x9845,x9846)
    val x9848 = withCtrl(x9856) { OpDef(op=FixSla, inputs=List(x9847, Const(2))).name("x9848").srcCtx("lenet_loops.scala:178:23") } // FixLsh(x9847,Const(2))
    val x9849 = withCtrl(x9856) { x9848 } // FixConvert(x9848,TRUE,_64,_0)
    val x9850 = withCtrl(x9856) { DramAddress(x9260).name("x9850").srcCtx("lenet_loops.scala:178:23") } // GetDRAMAddress(x9260)
    val x9852_x9851 = withCtrl(x9856) { OpDef(op=FixAdd, inputs=List(x9849, x9850)).name("x9852_x9851").srcCtx("lenet_loops.scala:178:23") } // FixAdd(x9849,x9850)
    // x9852 = SimpleStruct(ArrayBuffer((offset,x9851), (size,Const(2048)), (isLoad,Const(true))))
    val x9853 = withCtrl(x9856) { OpDef(op=BitAnd, inputs=List(b6136, b6129)).name("x9853").srcCtx("UnrollingBase.scala:28:66") } // And(b6136,b6129)
    val x9854 = withCtrl(x9856) { OpDef(op=BitAnd, inputs=List(x9853, b5677)).name("x9854").srcCtx("UnrollingBase.scala:28:66") } // And(x9853,b5677)
    val x9855_b9996_b9994 = withCtrl(x9856) { WriteMem(b9994, x9852_x9851).name("x9855_b9996_b9994").srcCtx("lenet_loops.scala:178:23") } // StreamWrite(x9841,x9852,x9854)
    val x9855_b9997_b9995 = withCtrl(x9856) { WriteMem(b9995, Const(2048)).name("x9855_b9997_b9995").srcCtx("lenet_loops.scala:178:23") } // StreamWrite(x9841,x9852,x9854)
    val x9857 = withCtrl(x9867) { FringeDenseLoad(dram=List(x9260), cmdStream=List(b9994, b9995), dataStream=List(x9842)).name("x9857").srcCtx("lenet_loops.scala:178:23") } // FringeDenseLoad(x9260,x9841,x9842)
    val x9858 = withCtrl(x9867) { Counter(min=Const(0), max=Const(512), step=Const(1), par=1).name("x9858").srcCtx("lenet_loops.scala:178:23") } // CounterNew(Const(0),Const(512),Const(1),Const(1))
    val x9859 = withCtrl(x9867) { CounterChain(List(x9858)).name("x9859").srcCtx("lenet_loops.scala:178:23") } // CounterChainNew(List(x9858))
    val x9866 = withCtrl(x9867) { LoopController(style=InnerPipe, level=InnerControl, cchain=x9859).name("x9866").srcCtx("lenet_loops.scala:178:23") } // UnrolledForeach(List(b6136, b6129, b5677),x9859,Block(Const(())),List(List(b6156)),List(List(b6157)))
    val b6156 = withCtrl(x9866) { CounterIter(x9858, None).name("b6156") } // b6156
    val b6157 = withCtrl(x9866) { Const(true).name("b6157") } // b6157
    val x9860 = withCtrl(x9866) { OpDef(op=BitAnd, inputs=List(b6157, b6136)).name("x9860").srcCtx("UnrollingBase.scala:28:66") } // And(b6157,b6136)
    val x9861 = withCtrl(x9866) { OpDef(op=BitAnd, inputs=List(b6129, b5677)).name("x9861").srcCtx("UnrollingBase.scala:28:66") } // And(b6129,b5677)
    val x9862 = withCtrl(x9866) { OpDef(op=BitAnd, inputs=List(x9860, x9861)).name("x9862").srcCtx("UnrollingBase.scala:28:66") } // And(x9860,x9861)
    val x9863_x9863 = withCtrl(x9866) { ReadMem(x9842).name("x9863_x9863").srcCtx("lenet_loops.scala:178:23") } // ParStreamRead(x9842,List(x9862))
    val x9864_x9864 = withCtrl(x9866) { x9863_x9863 } // VectorApply(x9863,0)
    val x9865 = withCtrl(x9866) { StoreBanks(List(List(x9836_d0_b0)), List(b6156), x9864_x9864).name("x9865").srcCtx("lenet_loops.scala:178:23") } // ParSRAMStore(x9836,List(List(b6156)),List(x9864),List(x9862))
    val x9868_d0 = withCtrl(x9890) { Reg(init=Some(0.0)).name("x9868_d0").srcCtx("lenet_loops.scala:179:35:prod") } // x9868 = RegNew(Const(0))
    isAccum(x9868_d0) = false
    bufferDepthOf(x9868_d0) = 2
    val x9868_d1 = withCtrl(x9890) { Reg(init=Some(0.0)).name("x9868_d1").srcCtx("lenet_loops.scala:179:35:prod") } // x9868 = RegNew(Const(0))
    isAccum(x9868_d1) = true
    bufferDepthOf(x9868_d1) = 1
    val x9869 = withCtrl(x9890) { Counter(min=Const(0), max=Const(500), step=Const(1), par=1).name("x9869").srcCtx("lenet_loops.scala:179:55") } // CounterNew(Const(0),Const(500),Const(1),Const(1))
    val x9870 = withCtrl(x9890) { CounterChain(List(x9869)).name("x9870").srcCtx("lenet_loops.scala:179:108") } // CounterChainNew(List(x9869))
    val x9883 = withCtrl(x9890) { LoopController(style=InnerPipe, level=InnerControl, cchain=x9870).name("x9883").srcCtx("lenet_loops.scala:179:108") } // UnrolledReduce(List(b6129, b5677),x9870,x9868,Block((x9868) => Const(())),List(List(b6169)),List(List(b6170)))
    val b6169 = withCtrl(x9883) { CounterIter(x9869, None).name("b6169") } // b6169
    val b6170 = withCtrl(x9883) { Const(true).name("b6170") } // b6170
    val x9871 = withCtrl(x9883) { OpDef(op=BitAnd, inputs=List(b6170, b6129)).name("x9871").srcCtx("UnrollingBase.scala:28:66") } // And(b6170,b6129)
    val x9872 = withCtrl(x9883) { OpDef(op=BitAnd, inputs=List(x9871, b5677)).name("x9872").srcCtx("UnrollingBase.scala:28:66") } // And(x9871,b5677)
    val x9873 = withCtrl(x9883) { LoadBanks(List(x9764_d0_b0), List(b6169)).name("x9873").srcCtx("lenet_loops.scala:179:80") } // ParSRAMLoad(x9764,List(List(b6169)),List(x9872))
    val x9874 = withCtrl(x9883) { x9873 } // VectorApply(x9873,0)
    val x9875 = withCtrl(x9883) { LoadBanks(List(x9836_d0_b0), List(b6169)).name("x9875").srcCtx("lenet_loops.scala:179:100") } // ParSRAMLoad(x9836,List(List(b6169)),List(x9872))
    val x9876 = withCtrl(x9883) { x9875 } // VectorApply(x9875,0)
    val x9877 = withCtrl(x9883) { OpDef(op=FixMul, inputs=List(x9874, x9876)).name("x9877").srcCtx("lenet_loops.scala:179:87") } // FixMul(x9874,x9876)
    val x9878 = withCtrl(x9883) { ReadMem(x9868_d1).name("x9878").srcCtx("lenet_loops.scala:179:108") } // RegRead(x9868)
    val x9879 = withCtrl(x9883) { OpDef(op=FixEql, inputs=List(b6169, Const(0))).name("x9879").srcCtx("lenet_loops.scala:179:108") } // FixEql(b6169,Const(0))
    val x9880 = withCtrl(x9883) { ReduceAccumOp(op=FixAdd, input=x9877, accum=x9878).name("x9880").srcCtx("lenet_loops.scala:179:110") } // FixAdd(x9877,x9878)
    val x9881 = withCtrl(x9883) { OpDef(op=BitAnd, inputs=List(b6129, b5677)).name("x9881").srcCtx("UnrollingBase.scala:28:66") } // And(b6129,b5677)
    val x9882_x9868_d0 = withCtrl(x9883) { WriteMem(x9868_d0, x9880).name("x9882_x9868_d0").srcCtx("lenet_loops.scala:179:108") } // RegWrite(x9868,x9880,x9881)
    antiDepsOf(x9882_x9868_d0)=List(x9878)
    val x9882_x9868_d1 = withCtrl(x9883) { WriteMem(x9868_d1, x9880).name("x9882_x9868_d1").srcCtx("lenet_loops.scala:179:108") } // RegWrite(x9868,x9880,x9881)
    antiDepsOf(x9882_x9868_d1)=List(x9878)
    val x9889 = withCtrl(x9890) { UnitController(style=SeqPipe, level=InnerControl).name("x9889").srcCtx("lenet_loops.scala:176:38") } // UnitPipe(List(b6129, b5677),Block(Const(())))
    val x9884 = withCtrl(x9889) { OpDef(op=BitAnd, inputs=List(b6129, b5677)).name("x9884").srcCtx("UnrollingBase.scala:28:66") } // And(b6129,b5677)
    val x9885 = withCtrl(x9889) { LoadBanks(List(x9439_d0_b0), List(b6128)).name("x9885").srcCtx("lenet_loops.scala:180:50") } // SRAMLoad(x9439,ArrayBuffer(Const(32)),List(b6128),Const(0),x9884)
    val x9886 = withCtrl(x9889) { ReadMem(x9868_d0).name("x9886").srcCtx("lenet_loops.scala:180:35") } // RegRead(x9868)
    val x9887 = withCtrl(x9889) { OpDef(op=FixAdd, inputs=List(x9886, x9885)).name("x9887").srcCtx("lenet_loops.scala:180:41") } // FixAdd(x9886,x9885)
    val x9888 = withCtrl(x9889) { StoreBanks(List(List(x9833_d0_b0)), List(b6128), x9887).name("x9888").srcCtx("lenet_loops.scala:180:28") } // SRAMStore(x9833,ArrayBuffer(Const(32)),List(b6128),Const(0),x9887,x9884)
    val x9891 = withCtrl(x9923) { Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x9891").srcCtx("lenet_loops.scala:185:37") } // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x9892 = withCtrl(x9923) { CounterChain(List(x9891)).name("x9892").srcCtx("lenet_loops.scala:185:37") } // CounterChainNew(List(x9891))
    val x9922 = withCtrl(x9923) { LoopController(style=StreamPipe, level=OuterControl, cchain=x9892).name("x9922").srcCtx("lenet_loops.scala:185:37") } // UnrolledForeach(List(b5677),x9892,Block(Const(())),List(List(b6193)),List(List(b6194)))
    val b6193 = withCtrl(x9922) { CounterIter(x9891, Some(0)).name("b6193") } // b6193
    val b6194 = withCtrl(x9922) { Const(true).name("b6194") } // b6194
    val b9998 = withCtrl(x9922) { StreamOut(field="offset").name("b9998").srcCtx("lenet_loops.scala:185:37") } // x9893 = StreamOutNew(BurstCmdBus)
    isAccum(b9998) = false
    bufferDepthOf(b9998) = 1
    val b9999 = withCtrl(x9922) { StreamOut(field="size").name("b9999").srcCtx("lenet_loops.scala:185:37") } // x9893 = StreamOutNew(BurstCmdBus)
    isAccum(b9999) = false
    bufferDepthOf(b9999) = 1
    val x9894 = withCtrl(x9922) { StreamOut(field="data").name("x9894").srcCtx("lenet_loops.scala:185:37") } // x9894 = StreamOutNew(BurstFullDataBus())
    isAccum(x9894) = false
    bufferDepthOf(x9894) = 1
    val x9895 = withCtrl(x9922) { StreamIn(field="ack").name("x9895").srcCtx("lenet_loops.scala:185:37") } // x9895 = StreamInNew(BurstAckBus)
    isAccum(x9895) = false
    bufferDepthOf(x9895) = 1
    val x9908 = withCtrl(x9922) { UnitController(style=SeqPipe, level=InnerControl).name("x9908").srcCtx("lenet_loops.scala:185:37") } // UnitPipe(List(b6194, b5677),Block(x9907))
    val x9896 = withCtrl(x9908) { OpDef(op=FixAdd, inputs=List(b5676, b6193)).name("x9896").srcCtx("lenet_loops.scala:185:37") } // FixAdd(b5676,b6193)
    val x9897 = withCtrl(x9908) { x9896 } // FixConvert(x9896,TRUE,_32,_0) (Same Type. No op)
    val x9898 = withCtrl(x9908) { OpDef(op=FixSla, inputs=List(x9897, Const(5))).name("x9898").srcCtx("lenet_loops.scala:185:37") } // FixLsh(x9897,Const(5))
    val x9899 = withCtrl(x9908) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x9900 = withCtrl(x9908) { OpDef(op=FixAdd, inputs=List(x9898, x9899)).name("x9900").srcCtx("lenet_loops.scala:185:37") } // FixAdd(x9898,x9899)
    val x9901 = withCtrl(x9908) { OpDef(op=FixSla, inputs=List(x9900, Const(2))).name("x9901").srcCtx("lenet_loops.scala:185:37") } // FixLsh(x9900,Const(2))
    val x9902 = withCtrl(x9908) { x9901 } // FixConvert(x9901,TRUE,_64,_0)
    val x9903 = withCtrl(x9908) { DramAddress(x9262).name("x9903").srcCtx("lenet_loops.scala:185:37") } // GetDRAMAddress(x9262)
    val x9905_x9904 = withCtrl(x9908) { OpDef(op=FixAdd, inputs=List(x9902, x9903)).name("x9905_x9904").srcCtx("lenet_loops.scala:185:37") } // FixAdd(x9902,x9903)
    // x9905 = SimpleStruct(ArrayBuffer((offset,x9904), (size,Const(128)), (isLoad,Const(false))))
    val x9906 = withCtrl(x9908) { OpDef(op=BitAnd, inputs=List(b6194, b5677)).name("x9906").srcCtx("UnrollingBase.scala:28:66") } // And(b6194,b5677)
    val x9907_b10000_b9998 = withCtrl(x9908) { WriteMem(b9998, x9905_x9904).name("x9907_b10000_b9998").srcCtx("lenet_loops.scala:185:37") } // StreamWrite(x9893,x9905,x9906)
    val x9907_b10001_b9999 = withCtrl(x9908) { WriteMem(b9999, Const(128)).name("x9907_b10001_b9999").srcCtx("lenet_loops.scala:185:37") } // StreamWrite(x9893,x9905,x9906)
    val x9909 = withCtrl(x9922) { Counter(min=Const(0), max=Const(32), step=Const(1), par=1).name("x9909").srcCtx("lenet_loops.scala:185:37") } // CounterNew(Const(0),Const(32),Const(1),Const(1))
    val x9910 = withCtrl(x9922) { CounterChain(List(x9909)).name("x9910").srcCtx("lenet_loops.scala:185:37") } // CounterChainNew(List(x9909))
    val x9917 = withCtrl(x9922) { LoopController(style=InnerPipe, level=InnerControl, cchain=x9910).name("x9917").srcCtx("lenet_loops.scala:185:37") } // UnrolledForeach(List(b6194, b5677),x9910,Block(Const(())),List(List(b6213)),List(List(b6214)))
    val b6213 = withCtrl(x9917) { CounterIter(x9909, None).name("b6213") } // b6213
    val b6214 = withCtrl(x9917) { Const(true).name("b6214") } // b6214
    val x9911 = withCtrl(x9917) { OpDef(op=BitAnd, inputs=List(b6214, b6194)).name("x9911").srcCtx("UnrollingBase.scala:28:66") } // And(b6214,b6194)
    val x9912 = withCtrl(x9917) { OpDef(op=BitAnd, inputs=List(x9911, b5677)).name("x9912").srcCtx("UnrollingBase.scala:28:66") } // And(x9911,b5677)
    val x9913 = withCtrl(x9917) { LoadBanks(List(x9833_d0_b0), List(b6213)).name("x9913").srcCtx("lenet_loops.scala:185:37") } // ParSRAMLoad(x9833,List(List(b6213)),List(x9912))
    val x9915_x9914 = withCtrl(x9917) { x9913 } // VectorApply(x9913,0)
    // x9915 = SimpleStruct(ArrayBuffer((_1,x9914), (_2,Const(true))))
    val x9916_x9916_x9894 = withCtrl(x9917) { WriteMem(x9894, x9915_x9914).name("x9916_x9916_x9894").srcCtx("lenet_loops.scala:185:37") } // ParStreamWrite(x9894,List(x9915),List(x9912))
    val x9918 = withCtrl(x9922) { FringeDenseStore(dram=List(x9262), cmdStream=List(b9998, b9999), dataStream=List(x9894), ackStream=List(x9895)).name("x9918").srcCtx("lenet_loops.scala:185:37") } // FringeDenseStore(x9262,x9893,x9894,x9895)
    val x9921 = withCtrl(x9922) { UnitController(style=SeqPipe, level=InnerControl).name("x9921").srcCtx("lenet_loops.scala:185:37") } // UnitPipe(List(b6194, b5677),Block(Const(())))
    val x9919 = withCtrl(x9921) { OpDef(op=BitAnd, inputs=List(b6194, b5677)).name("x9919").srcCtx("UnrollingBase.scala:28:66") } // And(b6194,b5677)
    val x9920_x9920 = withCtrl(x9921) { ReadMem(x9895).name("x9920_x9920").srcCtx("lenet_loops.scala:185:37") } // StreamRead(x9895,x9919)
    }; split1
    
  }
}
