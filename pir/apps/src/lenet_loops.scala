import pir._
import pir.node._
import arch._
import prism.enums._

object lenet_loops extends PIRApp {
  def main(implicit design:PIRDesign) = {
    import design.pirmeta._
    val x9248 = DRAM(dims=List(Const(20), Const(1), Const(32))).name("x9248").ctrl(top).srcCtx("lenet_loops.scala:30:26:c0_DRAM") // x9248 = DRAMNew(ArrayBuffer(Const(20), Const(1), Const(32)),Const(0))
    val x9249 = DRAM(dims=List(Const(17), Const(28), Const(32))).name("x9249").ctrl(top).srcCtx("lenet_loops.scala:31:26:i0_DRAM") // x9249 = DRAMNew(ArrayBuffer(Const(17), Const(28), Const(32)),Const(0))
    val x9250 = DRAM(dims=List(Const(32))).name("x9250").ctrl(top).srcCtx("lenet_loops.scala:32:26:c1_DRAM") // x9250 = DRAMNew(ArrayBuffer(Const(32)),Const(0))
    val x9251 = DRAM(dims=List(Const(50), Const(512))).name("x9251").ctrl(top).srcCtx("lenet_loops.scala:33:26:c2_DRAM") // x9251 = DRAMNew(ArrayBuffer(Const(50), Const(512)),Const(0))
    val x9252 = DRAM(dims=List(Const(64))).name("x9252").ctrl(top).srcCtx("lenet_loops.scala:34:26:c3_DRAM") // x9252 = DRAMNew(ArrayBuffer(Const(64)),Const(0))
    val x9253 = DRAM(dims=List(Const(100), Const(4000))).name("x9253").ctrl(top).srcCtx("lenet_loops.scala:35:26:c4_DRAM") // x9253 = DRAMNew(ArrayBuffer(Const(100), Const(4000)),Const(0))
    val x9254 = DRAM(dims=List(Const(512))).name("x9254").ctrl(top).srcCtx("lenet_loops.scala:37:26:c5_DRAM") // x9254 = DRAMNew(ArrayBuffer(Const(512)),Const(0))
    val x9255 = DRAM(dims=List(Const(10), Const(512))).name("x9255").ctrl(top).srcCtx("lenet_loops.scala:38:26:c6_DRAM") // x9255 = DRAMNew(ArrayBuffer(Const(10), Const(512)),Const(0))
    val x9256 = DRAM(dims=List(Const(32))).name("x9256").ctrl(top).srcCtx("lenet_loops.scala:40:26:c7_DRAM") // x9256 = DRAMNew(ArrayBuffer(Const(32)),Const(0))
    val x9257 = DRAM(dims=List(Const(17), Const(32))).name("x9257").ctrl(top).srcCtx("lenet_loops.scala:41:28:tmp5_DRAM") // x9257 = DRAMNew(ArrayBuffer(Const(17), Const(32)),Const(0))
    val x9918 = UnitController(style=SeqPipe, level=OuterControl).name("x9918").ctrl(top).srcCtx("lenet_loops.scala:69:11") // Hwblock(Block(Const(())),false)
    val x9377_d0_b0 = SRAM(size=32, banking=Strided(banks=1, stride=1)).name("x9377_d0_b0").ctrl(x9918).srcCtx("lenet_loops.scala:71:28:c1_SRAM") // x9377 = SRAMNew(ArrayBuffer(Const(32)))
    isAccum(x9377_d0_b0) = false
    bufferDepthOf(x9377_d0_b0) = 1
    val x9395 = UnitController(style=StreamPipe, level=OuterControl).name("x9395").ctrl(x9918).srcCtx("lenet_loops.scala:72:15") // UnitPipe(List(Const(true)),Block(Const(())))
    val b9956 = StreamOut(field="offset").name("b9956").ctrl(x9395).srcCtx("lenet_loops.scala:72:15") // x9378 = StreamOutNew(BurstCmdBus)
    isAccum(b9956) = false
    bufferDepthOf(b9956) = 1
    val b9957 = StreamOut(field="size").name("b9957").ctrl(x9395).srcCtx("lenet_loops.scala:72:15") // x9378 = StreamOutNew(BurstCmdBus)
    isAccum(b9957) = false
    bufferDepthOf(b9957) = 1
    val x9379 = StreamIn(field="data").name("x9379").ctrl(x9395).srcCtx("lenet_loops.scala:72:15") // x9379 = StreamInNew(BurstDataBus())
    isAccum(x9379) = false
    bufferDepthOf(x9379) = 1
    val x9387 = UnitController(style=SeqPipe, level=InnerControl).name("x9387").ctrl(x9395).srcCtx("lenet_loops.scala:72:15") // UnitPipe(List(Const(true)),Block(x9386))
    val x9380 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x9381 = OpDef(op=FixSla, inputs=List(x9380, Const(1))).name("x9381").ctrl(x9387).srcCtx("lenet_loops.scala:72:15") // FixLsh(x9380,Const(1))
    val x9382 = x9381 // FixConvert(x9381,TRUE,_64,_0)
    val x9383 = DramAddress(x9250).name("x9383").ctrl(x9387).srcCtx("lenet_loops.scala:72:15") // GetDRAMAddress(x9250)
    val x9385_x9384 = OpDef(op=FixAdd, inputs=List(x9382, x9383)).name("x9385_x9384").ctrl(x9387).srcCtx("lenet_loops.scala:72:15") // FixAdd(x9382,x9383)
    // x9385 = SimpleStruct(ArrayBuffer((offset,x9384), (size,Const(64)), (isLoad,Const(true))))
    val x9386_b9958_b9956 = WriteMem(b9956, x9385_x9384).name("x9386_b9958_b9956").ctrl(x9387).srcCtx("lenet_loops.scala:72:15") // StreamWrite(x9378,x9385,Const(true))
    val x9386_b9959_b9957 = WriteMem(b9957, Const(64)).name("x9386_b9959_b9957").ctrl(x9387).srcCtx("lenet_loops.scala:72:15") // StreamWrite(x9378,x9385,Const(true))
    val x9388 = FringeDenseLoad(dram=List(x9250), cmdStream=List(b9956, b9957), dataStream=List(x9379)).name("x9388").ctrl(x9395).srcCtx("lenet_loops.scala:72:15") // FringeDenseLoad(x9250,x9378,x9379)
    val x9389 = Counter(min=Const(0), max=Const(32), step=Const(1), par=1).name("x9389").ctrl(x9395).srcCtx("lenet_loops.scala:72:15") // CounterNew(Const(0),Const(32),Const(1),Const(1))
    val x9390 = CounterChain(List(x9389)).name("x9390").ctrl(x9395).srcCtx("lenet_loops.scala:72:15") // CounterChainNew(List(x9389))
    val x9394 = LoopController(style=InnerPipe, level=InnerControl, cchain=x9390).name("x9394").ctrl(x9395).srcCtx("lenet_loops.scala:72:15") // UnrolledForeach(List(Const(true)),x9390,Block(Const(())),List(List(b5604)),List(List(b5605)))
    val b5604 = CounterIter(x9389, None).name("b5604").ctrl(x9394) // b5604
    val b5605 = Const(true).name("b5605").ctrl(x9394) // b5605
    val x9391_x9391 = ReadMem(x9379).name("x9391_x9391").ctrl(x9394).srcCtx("lenet_loops.scala:72:15") // ParStreamRead(x9379,List(b5605))
    val x9392_x9392 = x9391_x9391 // x9392 = VectorApply(x9391,0)
    val x9393 = StoreBanks(List(x9377_d0_b0), List(b5604), x9392_x9392).name("x9393").ctrl(x9394).srcCtx("lenet_loops.scala:72:15") // ParSRAMStore(x9377,List(List(b5604)),List(x9392),List(b5605))
    val x9396_d0_b0 = SRAM(size=64, banking=Strided(banks=1, stride=1)).name("x9396_d0_b0").ctrl(x9918).srcCtx("lenet_loops.scala:74:28:c3_SRAM") // x9396 = SRAMNew(ArrayBuffer(Const(64)))
    isAccum(x9396_d0_b0) = false
    bufferDepthOf(x9396_d0_b0) = 1
    val x9414 = UnitController(style=StreamPipe, level=OuterControl).name("x9414").ctrl(x9918).srcCtx("lenet_loops.scala:75:15") // UnitPipe(List(Const(true)),Block(Const(())))
    val b9960 = StreamOut(field="offset").name("b9960").ctrl(x9414).srcCtx("lenet_loops.scala:75:15") // x9397 = StreamOutNew(BurstCmdBus)
    isAccum(b9960) = false
    bufferDepthOf(b9960) = 1
    val b9961 = StreamOut(field="size").name("b9961").ctrl(x9414).srcCtx("lenet_loops.scala:75:15") // x9397 = StreamOutNew(BurstCmdBus)
    isAccum(b9961) = false
    bufferDepthOf(b9961) = 1
    val x9398 = StreamIn(field="data").name("x9398").ctrl(x9414).srcCtx("lenet_loops.scala:75:15") // x9398 = StreamInNew(BurstDataBus())
    isAccum(x9398) = false
    bufferDepthOf(x9398) = 1
    val x9406 = UnitController(style=SeqPipe, level=InnerControl).name("x9406").ctrl(x9414).srcCtx("lenet_loops.scala:75:15") // UnitPipe(List(Const(true)),Block(x9405))
    val x9399 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x9400 = OpDef(op=FixSla, inputs=List(x9399, Const(1))).name("x9400").ctrl(x9406).srcCtx("lenet_loops.scala:75:15") // FixLsh(x9399,Const(1))
    val x9401 = x9400 // FixConvert(x9400,TRUE,_64,_0)
    val x9402 = DramAddress(x9252).name("x9402").ctrl(x9406).srcCtx("lenet_loops.scala:75:15") // GetDRAMAddress(x9252)
    val x9404_x9403 = OpDef(op=FixAdd, inputs=List(x9401, x9402)).name("x9404_x9403").ctrl(x9406).srcCtx("lenet_loops.scala:75:15") // FixAdd(x9401,x9402)
    // x9404 = SimpleStruct(ArrayBuffer((offset,x9403), (size,Const(128)), (isLoad,Const(true))))
    val x9405_b9962_b9960 = WriteMem(b9960, x9404_x9403).name("x9405_b9962_b9960").ctrl(x9406).srcCtx("lenet_loops.scala:75:15") // StreamWrite(x9397,x9404,Const(true))
    val x9405_b9963_b9961 = WriteMem(b9961, Const(128)).name("x9405_b9963_b9961").ctrl(x9406).srcCtx("lenet_loops.scala:75:15") // StreamWrite(x9397,x9404,Const(true))
    val x9407 = FringeDenseLoad(dram=List(x9252), cmdStream=List(b9960, b9961), dataStream=List(x9398)).name("x9407").ctrl(x9414).srcCtx("lenet_loops.scala:75:15") // FringeDenseLoad(x9252,x9397,x9398)
    val x9408 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1).name("x9408").ctrl(x9414).srcCtx("lenet_loops.scala:75:15") // CounterNew(Const(0),Const(64),Const(1),Const(1))
    val x9409 = CounterChain(List(x9408)).name("x9409").ctrl(x9414).srcCtx("lenet_loops.scala:75:15") // CounterChainNew(List(x9408))
    val x9413 = LoopController(style=InnerPipe, level=InnerControl, cchain=x9409).name("x9413").ctrl(x9414).srcCtx("lenet_loops.scala:75:15") // UnrolledForeach(List(Const(true)),x9409,Block(Const(())),List(List(b5625)),List(List(b5626)))
    val b5625 = CounterIter(x9408, None).name("b5625").ctrl(x9413) // b5625
    val b5626 = Const(true).name("b5626").ctrl(x9413) // b5626
    val x9410_x9410 = ReadMem(x9398).name("x9410_x9410").ctrl(x9413).srcCtx("lenet_loops.scala:75:15") // ParStreamRead(x9398,List(b5626))
    val x9411_x9411 = x9410_x9410 // x9411 = VectorApply(x9410,0)
    val x9412 = StoreBanks(List(x9396_d0_b0), List(b5625), x9411_x9411).name("x9412").ctrl(x9413).srcCtx("lenet_loops.scala:75:15") // ParSRAMStore(x9396,List(List(b5625)),List(x9411),List(b5626))
    val x9415_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=1)).name("x9415_d0_b0").ctrl(x9918).srcCtx("lenet_loops.scala:77:28:c5_SRAM") // x9415 = SRAMNew(ArrayBuffer(Const(512)))
    isAccum(x9415_d0_b0) = false
    bufferDepthOf(x9415_d0_b0) = 1
    val x9433 = UnitController(style=StreamPipe, level=OuterControl).name("x9433").ctrl(x9918).srcCtx("lenet_loops.scala:78:15") // UnitPipe(List(Const(true)),Block(Const(())))
    val b9964 = StreamOut(field="offset").name("b9964").ctrl(x9433).srcCtx("lenet_loops.scala:78:15") // x9416 = StreamOutNew(BurstCmdBus)
    isAccum(b9964) = false
    bufferDepthOf(b9964) = 1
    val b9965 = StreamOut(field="size").name("b9965").ctrl(x9433).srcCtx("lenet_loops.scala:78:15") // x9416 = StreamOutNew(BurstCmdBus)
    isAccum(b9965) = false
    bufferDepthOf(b9965) = 1
    val x9417 = StreamIn(field="data").name("x9417").ctrl(x9433).srcCtx("lenet_loops.scala:78:15") // x9417 = StreamInNew(BurstDataBus())
    isAccum(x9417) = false
    bufferDepthOf(x9417) = 1
    val x9425 = UnitController(style=SeqPipe, level=InnerControl).name("x9425").ctrl(x9433).srcCtx("lenet_loops.scala:78:15") // UnitPipe(List(Const(true)),Block(x9424))
    val x9418 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x9419 = OpDef(op=FixSla, inputs=List(x9418, Const(1))).name("x9419").ctrl(x9425).srcCtx("lenet_loops.scala:78:15") // FixLsh(x9418,Const(1))
    val x9420 = x9419 // FixConvert(x9419,TRUE,_64,_0)
    val x9421 = DramAddress(x9254).name("x9421").ctrl(x9425).srcCtx("lenet_loops.scala:78:15") // GetDRAMAddress(x9254)
    val x9423_x9422 = OpDef(op=FixAdd, inputs=List(x9420, x9421)).name("x9423_x9422").ctrl(x9425).srcCtx("lenet_loops.scala:78:15") // FixAdd(x9420,x9421)
    // x9423 = SimpleStruct(ArrayBuffer((offset,x9422), (size,Const(1024)), (isLoad,Const(true))))
    val x9424_b9966_b9964 = WriteMem(b9964, x9423_x9422).name("x9424_b9966_b9964").ctrl(x9425).srcCtx("lenet_loops.scala:78:15") // StreamWrite(x9416,x9423,Const(true))
    val x9424_b9967_b9965 = WriteMem(b9965, Const(1024)).name("x9424_b9967_b9965").ctrl(x9425).srcCtx("lenet_loops.scala:78:15") // StreamWrite(x9416,x9423,Const(true))
    val x9426 = FringeDenseLoad(dram=List(x9254), cmdStream=List(b9964, b9965), dataStream=List(x9417)).name("x9426").ctrl(x9433).srcCtx("lenet_loops.scala:78:15") // FringeDenseLoad(x9254,x9416,x9417)
    val x9427 = Counter(min=Const(0), max=Const(512), step=Const(1), par=1).name("x9427").ctrl(x9433).srcCtx("lenet_loops.scala:78:15") // CounterNew(Const(0),Const(512),Const(1),Const(1))
    val x9428 = CounterChain(List(x9427)).name("x9428").ctrl(x9433).srcCtx("lenet_loops.scala:78:15") // CounterChainNew(List(x9427))
    val x9432 = LoopController(style=InnerPipe, level=InnerControl, cchain=x9428).name("x9432").ctrl(x9433).srcCtx("lenet_loops.scala:78:15") // UnrolledForeach(List(Const(true)),x9428,Block(Const(())),List(List(b5646)),List(List(b5647)))
    val b5646 = CounterIter(x9427, None).name("b5646").ctrl(x9432) // b5646
    val b5647 = Const(true).name("b5647").ctrl(x9432) // b5647
    val x9429_x9429 = ReadMem(x9417).name("x9429_x9429").ctrl(x9432).srcCtx("lenet_loops.scala:78:15") // ParStreamRead(x9417,List(b5647))
    val x9430_x9430 = x9429_x9429 // x9430 = VectorApply(x9429,0)
    val x9431 = StoreBanks(List(x9415_d0_b0), List(b5646), x9430_x9430).name("x9431").ctrl(x9432).srcCtx("lenet_loops.scala:78:15") // ParSRAMStore(x9415,List(List(b5646)),List(x9430),List(b5647))
    val x9434_d0_b0 = SRAM(size=32, banking=Strided(banks=1, stride=1)).name("x9434_d0_b0").ctrl(x9918).srcCtx("lenet_loops.scala:80:28:c7_SRAM") // x9434 = SRAMNew(ArrayBuffer(Const(32)))
    isAccum(x9434_d0_b0) = false
    bufferDepthOf(x9434_d0_b0) = 1
    val x9452 = UnitController(style=StreamPipe, level=OuterControl).name("x9452").ctrl(x9918).srcCtx("lenet_loops.scala:81:15") // UnitPipe(List(Const(true)),Block(Const(())))
    val b9968 = StreamOut(field="offset").name("b9968").ctrl(x9452).srcCtx("lenet_loops.scala:81:15") // x9435 = StreamOutNew(BurstCmdBus)
    isAccum(b9968) = false
    bufferDepthOf(b9968) = 1
    val b9969 = StreamOut(field="size").name("b9969").ctrl(x9452).srcCtx("lenet_loops.scala:81:15") // x9435 = StreamOutNew(BurstCmdBus)
    isAccum(b9969) = false
    bufferDepthOf(b9969) = 1
    val x9436 = StreamIn(field="data").name("x9436").ctrl(x9452).srcCtx("lenet_loops.scala:81:15") // x9436 = StreamInNew(BurstDataBus())
    isAccum(x9436) = false
    bufferDepthOf(x9436) = 1
    val x9444 = UnitController(style=SeqPipe, level=InnerControl).name("x9444").ctrl(x9452).srcCtx("lenet_loops.scala:81:15") // UnitPipe(List(Const(true)),Block(x9443))
    val x9437 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x9438 = OpDef(op=FixSla, inputs=List(x9437, Const(1))).name("x9438").ctrl(x9444).srcCtx("lenet_loops.scala:81:15") // FixLsh(x9437,Const(1))
    val x9439 = x9438 // FixConvert(x9438,TRUE,_64,_0)
    val x9440 = DramAddress(x9256).name("x9440").ctrl(x9444).srcCtx("lenet_loops.scala:81:15") // GetDRAMAddress(x9256)
    val x9442_x9441 = OpDef(op=FixAdd, inputs=List(x9439, x9440)).name("x9442_x9441").ctrl(x9444).srcCtx("lenet_loops.scala:81:15") // FixAdd(x9439,x9440)
    // x9442 = SimpleStruct(ArrayBuffer((offset,x9441), (size,Const(64)), (isLoad,Const(true))))
    val x9443_b9970_b9968 = WriteMem(b9968, x9442_x9441).name("x9443_b9970_b9968").ctrl(x9444).srcCtx("lenet_loops.scala:81:15") // StreamWrite(x9435,x9442,Const(true))
    val x9443_b9971_b9969 = WriteMem(b9969, Const(64)).name("x9443_b9971_b9969").ctrl(x9444).srcCtx("lenet_loops.scala:81:15") // StreamWrite(x9435,x9442,Const(true))
    val x9445 = FringeDenseLoad(dram=List(x9256), cmdStream=List(b9968, b9969), dataStream=List(x9436)).name("x9445").ctrl(x9452).srcCtx("lenet_loops.scala:81:15") // FringeDenseLoad(x9256,x9435,x9436)
    val x9446 = Counter(min=Const(0), max=Const(32), step=Const(1), par=1).name("x9446").ctrl(x9452).srcCtx("lenet_loops.scala:81:15") // CounterNew(Const(0),Const(32),Const(1),Const(1))
    val x9447 = CounterChain(List(x9446)).name("x9447").ctrl(x9452).srcCtx("lenet_loops.scala:81:15") // CounterChainNew(List(x9446))
    val x9451 = LoopController(style=InnerPipe, level=InnerControl, cchain=x9447).name("x9451").ctrl(x9452).srcCtx("lenet_loops.scala:81:15") // UnrolledForeach(List(Const(true)),x9447,Block(Const(())),List(List(b5667)),List(List(b5668)))
    val b5667 = CounterIter(x9446, None).name("b5667").ctrl(x9451) // b5667
    val b5668 = Const(true).name("b5668").ctrl(x9451) // b5668
    val x9448_x9448 = ReadMem(x9436).name("x9448_x9448").ctrl(x9451).srcCtx("lenet_loops.scala:81:15") // ParStreamRead(x9436,List(b5668))
    val x9449_x9449 = x9448_x9448 // x9449 = VectorApply(x9448,0)
    val x9450 = StoreBanks(List(x9434_d0_b0), List(b5667), x9449_x9449).name("x9450").ctrl(x9451).srcCtx("lenet_loops.scala:81:15") // ParSRAMStore(x9434,List(List(b5667)),List(x9449),List(b5668))
    val x9453 = Counter(min=Const(0), max=Const(17), step=Const(1), par=1).name("x9453").ctrl(x9918).srcCtx("lenet_loops.scala:83:31") // CounterNew(Const(0),Const(17),Const(1),Const(1))
    val x9454 = CounterChain(List(x9453)).name("x9454").ctrl(x9918).srcCtx("lenet_loops.scala:83:46") // CounterChainNew(List(x9453))
    val x9917 = LoopController(style=MetaPipe, level=OuterControl, cchain=x9454).name("x9917").ctrl(x9918).srcCtx("lenet_loops.scala:83:46") // UnrolledForeach(List(Const(true)),x9454,Block(Const(())),List(List(b5676)),List(List(b5677)))
    val b5676 = CounterIter(x9453, Some(0)).name("b5676").ctrl(x9917) // b5676
    val b5677 = Const(true).name("b5677").ctrl(x9917) // b5677
    val x9455_d0_b0 = SRAM(size=896, banking=Strided(banks=1, stride=32)).name("x9455_d0_b0").ctrl(x9917).srcCtx("lenet_loops.scala:86:30:i0_SRAM") // x9455 = SRAMNew(ArrayBuffer(Const(28), Const(32)))
    isAccum(x9455_d0_b0) = false
    bufferDepthOf(x9455_d0_b0) = 2
    val x9457 = UnitController(style=SeqPipe, level=InnerControl).name("x9457").ctrl(x9917).srcCtx("lenet_loops.scala:83:46") // UnitPipe(List(b5677),Block(Const(())))
    val x9456 = OpDef(op=FixAdd, inputs=List(b5676, Const(1))).name("x9456").ctrl(x9457).srcCtx("lenet_loops.scala:87:29") // FixAdd(b5676,Const(1))
    val x9458 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x9458").ctrl(x9917).srcCtx("lenet_loops.scala:87:17") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x9459 = Counter(min=Const(0), max=Const(28), step=Const(1), par=1).name("x9459").ctrl(x9917).srcCtx("lenet_loops.scala:87:17") // CounterNew(Const(0),Const(28),Const(1),Const(1))
    val x9460 = CounterChain(List(x9458,x9459)).name("x9460").ctrl(x9917).srcCtx("lenet_loops.scala:87:17") // CounterChainNew(List(x9458, x9459))
    val x9490 = LoopController(style=StreamPipe, level=OuterControl, cchain=x9460).name("x9490").ctrl(x9917).srcCtx("lenet_loops.scala:87:17") // UnrolledForeach(List(b5677),x9460,Block(Const(())),List(List(b5684), List(b5685)),List(List(b5686), List(b5687)))
    val b5684 = CounterIter(x9458, Some(0)).name("b5684").ctrl(x9490) // b5684
    val b5686 = Const(true).name("b5686").ctrl(x9490) // b5686
    val b5685 = CounterIter(x9459, Some(0)).name("b5685").ctrl(x9490) // b5685
    val b5687 = Const(true).name("b5687").ctrl(x9490) // b5687
    val b9972 = StreamOut(field="offset").name("b9972").ctrl(x9490).srcCtx("lenet_loops.scala:87:17") // x9461 = StreamOutNew(BurstCmdBus)
    isAccum(b9972) = false
    bufferDepthOf(b9972) = 1
    val b9973 = StreamOut(field="size").name("b9973").ctrl(x9490).srcCtx("lenet_loops.scala:87:17") // x9461 = StreamOutNew(BurstCmdBus)
    isAccum(b9973) = false
    bufferDepthOf(b9973) = 1
    val x9462 = StreamIn(field="data").name("x9462").ctrl(x9490).srcCtx("lenet_loops.scala:87:17") // x9462 = StreamInNew(BurstDataBus())
    isAccum(x9462) = false
    bufferDepthOf(x9462) = 1
    val x9479 = UnitController(style=SeqPipe, level=InnerControl).name("x9479").ctrl(x9490).srcCtx("lenet_loops.scala:87:17") // UnitPipe(List(b5686, b5687, b5677),Block(x9478))
    val x9463 = OpDef(op=FixAdd, inputs=List(b5676, b5684)).name("x9463").ctrl(x9479).srcCtx("lenet_loops.scala:87:17") // FixAdd(b5676,b5684)
    val x9464 = x9463 // FixConvert(x9463,TRUE,_32,_0) (Same Type. No op)
    val x9465 = OpDef(op=FixMul, inputs=List(x9464, Const(896))).name("x9465").ctrl(x9479).srcCtx("lenet_loops.scala:87:17") // FixMul(x9464,Const(896))
    val x9466 = b5685 // FixConvert(b5685,TRUE,_32,_0) (Same Type. No op)
    val x9467 = OpDef(op=FixSla, inputs=List(x9466, Const(5))).name("x9467").ctrl(x9479).srcCtx("lenet_loops.scala:87:17") // FixLsh(x9466,Const(5))
    val x9468 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x9469 = OpDef(op=FixAdd, inputs=List(x9465, x9467)).name("x9469").ctrl(x9479).srcCtx("lenet_loops.scala:87:17") // FixAdd(x9465,x9467)
    val x9470 = OpDef(op=FixAdd, inputs=List(x9469, x9468)).name("x9470").ctrl(x9479).srcCtx("lenet_loops.scala:87:17") // FixAdd(x9469,x9468)
    val x9471 = OpDef(op=FixSla, inputs=List(x9470, Const(1))).name("x9471").ctrl(x9479).srcCtx("lenet_loops.scala:87:17") // FixLsh(x9470,Const(1))
    val x9472 = x9471 // FixConvert(x9471,TRUE,_64,_0)
    val x9473 = DramAddress(x9249).name("x9473").ctrl(x9479).srcCtx("lenet_loops.scala:87:17") // GetDRAMAddress(x9249)
    val x9475_x9474 = OpDef(op=FixAdd, inputs=List(x9472, x9473)).name("x9475_x9474").ctrl(x9479).srcCtx("lenet_loops.scala:87:17") // FixAdd(x9472,x9473)
    // x9475 = SimpleStruct(ArrayBuffer((offset,x9474), (size,Const(64)), (isLoad,Const(true))))
    val x9476 = OpDef(op=BitAnd, inputs=List(b5686, b5687)).name("x9476").ctrl(x9479).srcCtx("UnrollingBase.scala:28:66") // And(b5686,b5687)
    val x9477 = OpDef(op=BitAnd, inputs=List(x9476, b5677)).name("x9477").ctrl(x9479).srcCtx("UnrollingBase.scala:28:66") // And(x9476,b5677)
    val x9478_b9974_b9972 = WriteMem(b9972, x9475_x9474).name("x9478_b9974_b9972").ctrl(x9479).srcCtx("lenet_loops.scala:87:17") // StreamWrite(x9461,x9475,x9477)
    val x9478_b9975_b9973 = WriteMem(b9973, Const(64)).name("x9478_b9975_b9973").ctrl(x9479).srcCtx("lenet_loops.scala:87:17") // StreamWrite(x9461,x9475,x9477)
    val x9480 = FringeDenseLoad(dram=List(x9249), cmdStream=List(b9972, b9973), dataStream=List(x9462)).name("x9480").ctrl(x9490).srcCtx("lenet_loops.scala:87:17") // FringeDenseLoad(x9249,x9461,x9462)
    val x9481 = Counter(min=Const(0), max=Const(32), step=Const(1), par=1).name("x9481").ctrl(x9490).srcCtx("lenet_loops.scala:87:17") // CounterNew(Const(0),Const(32),Const(1),Const(1))
    val x9482 = CounterChain(List(x9481)).name("x9482").ctrl(x9490).srcCtx("lenet_loops.scala:87:17") // CounterChainNew(List(x9481))
    val x9489 = LoopController(style=InnerPipe, level=InnerControl, cchain=x9482).name("x9489").ctrl(x9490).srcCtx("lenet_loops.scala:87:17") // UnrolledForeach(List(b5686, b5687, b5677),x9482,Block(Const(())),List(List(b5710)),List(List(b5711)))
    val b5710 = CounterIter(x9481, None).name("b5710").ctrl(x9489) // b5710
    val b5711 = Const(true).name("b5711").ctrl(x9489) // b5711
    val x9483 = OpDef(op=BitAnd, inputs=List(b5711, b5686)).name("x9483").ctrl(x9489).srcCtx("UnrollingBase.scala:28:66") // And(b5711,b5686)
    val x9484 = OpDef(op=BitAnd, inputs=List(b5687, b5677)).name("x9484").ctrl(x9489).srcCtx("UnrollingBase.scala:28:66") // And(b5687,b5677)
    val x9485 = OpDef(op=BitAnd, inputs=List(x9483, x9484)).name("x9485").ctrl(x9489).srcCtx("UnrollingBase.scala:28:66") // And(x9483,x9484)
    val x9486_x9486 = ReadMem(x9462).name("x9486_x9486").ctrl(x9489).srcCtx("lenet_loops.scala:87:17") // ParStreamRead(x9462,List(x9485))
    val x9487_x9487 = x9486_x9486 // x9487 = VectorApply(x9486,0)
    val x9488 = StoreBanks(List(x9455_d0_b0), List(b5685, b5710), x9487_x9487).name("x9488").ctrl(x9489).srcCtx("lenet_loops.scala:87:17") // ParSRAMStore(x9455,List(List(b5685, b5710)),List(x9487),List(x9485))
    val x9491_d0_b0 = SRAM(size=2880, banking=Strided(banks=1, stride=144)).name("x9491_d0_b0").ctrl(x9917).srcCtx("lenet_loops.scala:90:32:tmp1_SRAM") // x9491 = SRAMNew(ArrayBuffer(Const(20), Const(12), Const(12)))
    isAccum(x9491_d0_b0) = false
    bufferDepthOf(x9491_d0_b0) = 2
    val x9492 = Counter(min=Const(0), max=Const(20), step=Const(1), par=1).name("x9492").ctrl(x9917).srcCtx("lenet_loops.scala:91:25") // CounterNew(Const(0),Const(20),Const(1),Const(1))
    val x9493 = CounterChain(List(x9492)).name("x9493").ctrl(x9917).srcCtx("lenet_loops.scala:91:40") // CounterChainNew(List(x9492))
    val x9605 = LoopController(style=MetaPipe, level=OuterControl, cchain=x9493).name("x9605").ctrl(x9917).srcCtx("lenet_loops.scala:91:40") // UnrolledForeach(List(b5677),x9493,Block(Const(())),List(List(b5723)),List(List(b5724)))
    val b5723 = CounterIter(x9492, Some(0)).name("b5723").ctrl(x9605) // b5723
    val b5724 = Const(true).name("b5724").ctrl(x9605) // b5724
    val x9494_d0_b0 = SRAM(size=576, banking=Strided(banks=1, stride=24)).name("x9494_d0_b0").ctrl(x9605).srcCtx("lenet_loops.scala:98:39:tmp1_SRAM_conv") // x9494 = SRAMNew(ArrayBuffer(Const(24), Const(24)))
    isAccum(x9494_d0_b0) = false
    bufferDepthOf(x9494_d0_b0) = 2
    val x9495_d0_b0 = RegFile(size=32, inits=None).name("x9495_d0_b0").ctrl(x9605).srcCtx("lenet_loops.scala:99:33:c0_RF") // x9495 = RegFileNew(ArrayBuffer(Const(32)),None) banking:Strided(banks=1, stride=1)
    isAccum(x9495_d0_b0) = false
    bufferDepthOf(x9495_d0_b0) = 2
    val x9497 = UnitController(style=SeqPipe, level=InnerControl).name("x9497").ctrl(x9605).srcCtx("lenet_loops.scala:91:40") // UnitPipe(List(b5724, b5677),Block(Const(())))
    val x9496 = OpDef(op=FixAdd, inputs=List(b5723, Const(1))).name("x9496").ctrl(x9497).srcCtx("lenet_loops.scala:100:29") // FixAdd(b5723,Const(1))
    val x9498 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x9498").ctrl(x9605).srcCtx("lenet_loops.scala:100:17") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x9499 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x9499").ctrl(x9605).srcCtx("lenet_loops.scala:100:17") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x9500 = CounterChain(List(x9498,x9499)).name("x9500").ctrl(x9605).srcCtx("lenet_loops.scala:100:17") // CounterChainNew(List(x9498, x9499))
    val x9532 = LoopController(style=StreamPipe, level=OuterControl, cchain=x9500).name("x9532").ctrl(x9605).srcCtx("lenet_loops.scala:100:17") // UnrolledForeach(List(b5724, b5677),x9500,Block(Const(())),List(List(b5732), List(b5733)),List(List(b5734), List(b5735)))
    val b5732 = CounterIter(x9498, Some(0)).name("b5732").ctrl(x9532) // b5732
    val b5734 = Const(true).name("b5734").ctrl(x9532) // b5734
    val b5733 = CounterIter(x9499, Some(0)).name("b5733").ctrl(x9532) // b5733
    val b5735 = Const(true).name("b5735").ctrl(x9532) // b5735
    val b9976 = StreamOut(field="offset").name("b9976").ctrl(x9532).srcCtx("lenet_loops.scala:100:17") // x9501 = StreamOutNew(BurstCmdBus)
    isAccum(b9976) = false
    bufferDepthOf(b9976) = 1
    val b9977 = StreamOut(field="size").name("b9977").ctrl(x9532).srcCtx("lenet_loops.scala:100:17") // x9501 = StreamOutNew(BurstCmdBus)
    isAccum(b9977) = false
    bufferDepthOf(b9977) = 1
    val x9502 = StreamIn(field="data").name("x9502").ctrl(x9532).srcCtx("lenet_loops.scala:100:17") // x9502 = StreamInNew(BurstDataBus())
    isAccum(x9502) = false
    bufferDepthOf(x9502) = 1
    val x9520 = UnitController(style=SeqPipe, level=InnerControl).name("x9520").ctrl(x9532).srcCtx("lenet_loops.scala:100:17") // UnitPipe(List(b5734, b5735, b5724, b5677),Block(x9519))
    val x9503 = OpDef(op=FixAdd, inputs=List(b5723, b5732)).name("x9503").ctrl(x9520).srcCtx("lenet_loops.scala:100:17") // FixAdd(b5723,b5732)
    val x9504 = x9503 // FixConvert(x9503,TRUE,_32,_0) (Same Type. No op)
    val x9505 = OpDef(op=FixSla, inputs=List(x9504, Const(5))).name("x9505").ctrl(x9520).srcCtx("lenet_loops.scala:100:17") // FixLsh(x9504,Const(5))
    val x9506 = b5733 // FixConvert(b5733,TRUE,_32,_0) (Same Type. No op)
    val x9507 = OpDef(op=FixSla, inputs=List(x9506, Const(5))).name("x9507").ctrl(x9520).srcCtx("lenet_loops.scala:100:17") // FixLsh(x9506,Const(5))
    val x9508 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x9509 = OpDef(op=FixAdd, inputs=List(x9505, x9507)).name("x9509").ctrl(x9520).srcCtx("lenet_loops.scala:100:17") // FixAdd(x9505,x9507)
    val x9510 = OpDef(op=FixAdd, inputs=List(x9509, x9508)).name("x9510").ctrl(x9520).srcCtx("lenet_loops.scala:100:17") // FixAdd(x9509,x9508)
    val x9511 = OpDef(op=FixSla, inputs=List(x9510, Const(1))).name("x9511").ctrl(x9520).srcCtx("lenet_loops.scala:100:17") // FixLsh(x9510,Const(1))
    val x9512 = x9511 // FixConvert(x9511,TRUE,_64,_0)
    val x9513 = DramAddress(x9248).name("x9513").ctrl(x9520).srcCtx("lenet_loops.scala:100:17") // GetDRAMAddress(x9248)
    val x9515_x9514 = OpDef(op=FixAdd, inputs=List(x9512, x9513)).name("x9515_x9514").ctrl(x9520).srcCtx("lenet_loops.scala:100:17") // FixAdd(x9512,x9513)
    // x9515 = SimpleStruct(ArrayBuffer((offset,x9514), (size,Const(64)), (isLoad,Const(true))))
    val x9516 = OpDef(op=BitAnd, inputs=List(b5734, b5735)).name("x9516").ctrl(x9520).srcCtx("UnrollingBase.scala:28:66") // And(b5734,b5735)
    val x9517 = OpDef(op=BitAnd, inputs=List(b5724, b5677)).name("x9517").ctrl(x9520).srcCtx("UnrollingBase.scala:28:66") // And(b5724,b5677)
    val x9518 = OpDef(op=BitAnd, inputs=List(x9516, x9517)).name("x9518").ctrl(x9520).srcCtx("UnrollingBase.scala:28:66") // And(x9516,x9517)
    val x9519_b9978_b9976 = WriteMem(b9976, x9515_x9514).name("x9519_b9978_b9976").ctrl(x9520).srcCtx("lenet_loops.scala:100:17") // StreamWrite(x9501,x9515,x9518)
    val x9519_b9979_b9977 = WriteMem(b9977, Const(64)).name("x9519_b9979_b9977").ctrl(x9520).srcCtx("lenet_loops.scala:100:17") // StreamWrite(x9501,x9515,x9518)
    val x9521 = FringeDenseLoad(dram=List(x9248), cmdStream=List(b9976, b9977), dataStream=List(x9502)).name("x9521").ctrl(x9532).srcCtx("lenet_loops.scala:100:17") // FringeDenseLoad(x9248,x9501,x9502)
    val x9522 = Counter(min=Const(0), max=Const(32), step=Const(1), par=1).name("x9522").ctrl(x9532).srcCtx("lenet_loops.scala:100:17") // CounterNew(Const(0),Const(32),Const(1),Const(1))
    val x9523 = CounterChain(List(x9522)).name("x9523").ctrl(x9532).srcCtx("lenet_loops.scala:100:17") // CounterChainNew(List(x9522))
    val x9531 = LoopController(style=InnerPipe, level=InnerControl, cchain=x9523).name("x9531").ctrl(x9532).srcCtx("lenet_loops.scala:100:17") // UnrolledForeach(List(b5734, b5735, b5724, b5677),x9523,Block(Const(())),List(List(b5759)),List(List(b5760)))
    val b5759 = CounterIter(x9522, None).name("b5759").ctrl(x9531) // b5759
    val b5760 = Const(true).name("b5760").ctrl(x9531) // b5760
    val x9524 = OpDef(op=BitAnd, inputs=List(b5760, b5734)).name("x9524").ctrl(x9531).srcCtx("UnrollingBase.scala:28:66") // And(b5760,b5734)
    val x9525 = OpDef(op=BitAnd, inputs=List(b5735, b5724)).name("x9525").ctrl(x9531).srcCtx("UnrollingBase.scala:28:66") // And(b5735,b5724)
    val x9526 = OpDef(op=BitAnd, inputs=List(x9524, x9525)).name("x9526").ctrl(x9531).srcCtx("UnrollingBase.scala:28:66") // And(x9524,x9525)
    val x9527 = OpDef(op=BitAnd, inputs=List(x9526, b5677)).name("x9527").ctrl(x9531).srcCtx("UnrollingBase.scala:28:66") // And(x9526,b5677)
    val x9528_x9528 = ReadMem(x9502).name("x9528_x9528").ctrl(x9531).srcCtx("lenet_loops.scala:100:17") // ParStreamRead(x9502,List(x9527))
    val x9529_x9529 = x9528_x9528 // x9529 = VectorApply(x9528,0)
    val x9530 = StoreBanks(List(x9495_d0_b0), List(b5759), x9529_x9529).name("x9530").ctrl(x9531).srcCtx("lenet_loops.scala:100:17") // ParRegFileStore(x9495,List(List(b5759)),List(x9529),List(Const(true)))
    val x9533 = Counter(min=Const(0), max=Const(24), step=Const(1), par=1).name("x9533").ctrl(x9605).srcCtx("lenet_loops.scala:101:42") // CounterNew(Const(0),Const(24),Const(1),Const(1))
    val x9534 = Counter(min=Const(0), max=Const(24), step=Const(1), par=1).name("x9534").ctrl(x9605).srcCtx("lenet_loops.scala:101:21") // CounterNew(Const(0),Const(24),Const(1),Const(1))
    val x9535 = CounterChain(List(x9534,x9533)).name("x9535").ctrl(x9605).srcCtx("lenet_loops.scala:101:49") // CounterChainNew(List(x9534, x9533))
    val x9568 = LoopController(style=MetaPipe, level=OuterControl, cchain=x9535).name("x9568").ctrl(x9605).srcCtx("lenet_loops.scala:101:49") // UnrolledForeach(List(b5724, b5677),x9535,Block(Const(())),List(List(b5773), List(b5774)),List(List(b5775), List(b5776)))
    val b5773 = CounterIter(x9534, Some(0)).name("b5773").ctrl(x9568) // b5773
    val b5775 = Const(true).name("b5775").ctrl(x9568) // b5775
    val b5774 = CounterIter(x9533, Some(0)).name("b5774").ctrl(x9568) // b5774
    val b5776 = Const(true).name("b5776").ctrl(x9568) // b5776
    val x9536_d0 = Reg(init=Some(0.0)).name("x9536_d0").ctrl(x9568).srcCtx("lenet_loops.scala:102:39:window") // x9536 = RegNew(Const(0))
    isAccum(x9536_d0) = false
    bufferDepthOf(x9536_d0) = 2
    val x9536_d1 = Reg(init=Some(0.0)).name("x9536_d1").ctrl(x9568).srcCtx("lenet_loops.scala:102:39:window") // x9536 = RegNew(Const(0))
    isAccum(x9536_d1) = true
    bufferDepthOf(x9536_d1) = 1
    val x9537 = Counter(min=Const(0), max=Const(5), step=Const(1), par=1).name("x9537").ctrl(x9568).srcCtx("lenet_loops.scala:102:79") // CounterNew(Const(0),Const(5),Const(1),Const(1))
    val x9538 = Counter(min=Const(0), max=Const(5), step=Const(1), par=1).name("x9538").ctrl(x9568).srcCtx("lenet_loops.scala:102:61") // CounterNew(Const(0),Const(5),Const(1),Const(1))
    val x9539 = CounterChain(List(x9538,x9537)).name("x9539").ctrl(x9568).srcCtx("lenet_loops.scala:104:14") // CounterChainNew(List(x9538, x9537))
    val x9561 = LoopController(style=InnerPipe, level=InnerControl, cchain=x9539).name("x9561").ctrl(x9568).srcCtx("lenet_loops.scala:104:14") // UnrolledReduce(List(b5775, b5776, b5724, b5677),x9539,x9536,Block((x9536) => Const(())),List(List(b5781), List(b5782)),List(List(b5783), List(b5784)))
    val b5781 = CounterIter(x9538, Some(0)).name("b5781").ctrl(x9561) // b5781
    val b5783 = Const(true).name("b5783").ctrl(x9561) // b5783
    val b5782 = CounterIter(x9537, None).name("b5782").ctrl(x9561) // b5782
    val b5784 = Const(true).name("b5784").ctrl(x9561) // b5784
    val x9540 = OpDef(op=FixAdd, inputs=List(b5773, b5781)).name("x9540").ctrl(x9561).srcCtx("lenet_loops.scala:103:24") // FixAdd(b5773,b5781)
    val x9541 = OpDef(op=FixAdd, inputs=List(b5774, b5782)).name("x9541").ctrl(x9561).srcCtx("lenet_loops.scala:103:28") // FixAdd(b5774,b5782)
    val x9542 = OpDef(op=BitAnd, inputs=List(b5783, b5784)).name("x9542").ctrl(x9561).srcCtx("UnrollingBase.scala:28:66") // And(b5783,b5784)
    val x9543 = OpDef(op=BitAnd, inputs=List(b5775, b5776)).name("x9543").ctrl(x9561).srcCtx("UnrollingBase.scala:28:66") // And(b5775,b5776)
    val x9544 = OpDef(op=BitAnd, inputs=List(b5724, b5677)).name("x9544").ctrl(x9561).srcCtx("UnrollingBase.scala:28:66") // And(b5724,b5677)
    val x9545 = OpDef(op=BitAnd, inputs=List(x9542, x9543)).name("x9545").ctrl(x9561).srcCtx("UnrollingBase.scala:28:66") // And(x9542,x9543)
    val x9546 = OpDef(op=BitAnd, inputs=List(x9545, x9544)).name("x9546").ctrl(x9561).srcCtx("UnrollingBase.scala:28:66") // And(x9545,x9544)
    val x9547 = LoadBanks(List(x9455_d0_b0), List(x9540, x9541)).name("x9547").ctrl(x9561).srcCtx("lenet_loops.scala:103:22") // ParSRAMLoad(x9455,List(List(x9540, x9541)),List(x9546))
    val x9548 = x9547 // x9548 = VectorApply(x9547,0)
    val x9549 = OpDef(op=FixMul, inputs=List(b5781, Const(5))).name("x9549").ctrl(x9561).srcCtx("lenet_loops.scala:103:41") // FixMul(b5781,Const(5))
    val x9550 = OpDef(op=FixAdd, inputs=List(x9549, b5782)).name("x9550").ctrl(x9561).srcCtx("lenet_loops.scala:103:43") // FixAdd(x9549,b5782)
    val x9551 = LoadBanks(List(x9495_d0_b0), List(x9550)).name("x9551").ctrl(x9561).srcCtx("lenet_loops.scala:103:39") // ParRegFileLoad(x9495,List(List(x9550)),List(Const(true)))
    val x9552 = x9551 // x9552 = VectorApply(x9551,0)
    val x9553 = OpDef(op=FixMul, inputs=List(x9548, x9552)).name("x9553").ctrl(x9561).srcCtx("lenet_loops.scala:103:32") // FixMul(x9548,x9552)
    val x9554 = ReadMem(x9536_d1).name("x9554").ctrl(x9561).srcCtx("lenet_loops.scala:104:14") // RegRead(x9536)
    val x9555 = OpDef(op=FixEql, inputs=List(b5781, Const(0))).name("x9555").ctrl(x9561).srcCtx("lenet_loops.scala:104:14") // FixEql(b5781,Const(0))
    val x9556 = OpDef(op=FixEql, inputs=List(b5782, Const(0))).name("x9556").ctrl(x9561).srcCtx("lenet_loops.scala:104:14") // FixEql(b5782,Const(0))
    val x9557 = OpDef(op=BitAnd, inputs=List(x9555, x9556)).name("x9557").ctrl(x9561).srcCtx("lenet_loops.scala:104:14") // And(x9555,x9556)
    val x9558 = ReduceAccumOp(op=FixAdd, input=x9553, accum=x9554).name("x9558").ctrl(x9561).srcCtx("lenet_loops.scala:104:16") // FixAdd(x9553,x9554)
    val x9559 = OpDef(op=BitAnd, inputs=List(x9543, x9544)).name("x9559").ctrl(x9561).srcCtx("UnrollingBase.scala:28:66") // And(x9543,x9544)
    val x9560_x9536_d0 = WriteMem(x9536_d0, x9558).name("x9560_x9536_d0").ctrl(x9561).srcCtx("lenet_loops.scala:104:14") // RegWrite(x9536,x9558,x9559)
    val x9560_x9536_d1 = WriteMem(x9536_d1, x9558).name("x9560_x9536_d1").ctrl(x9561).srcCtx("lenet_loops.scala:104:14") // RegWrite(x9536,x9558,x9559)
    val x9567 = UnitController(style=SeqPipe, level=InnerControl).name("x9567").ctrl(x9568).srcCtx("lenet_loops.scala:101:49") // UnitPipe(List(b5775, b5776, b5724, b5677),Block(Const(())))
    val x9562 = ReadMem(x9536_d0).name("x9562").ctrl(x9567).srcCtx("lenet_loops.scala:105:43") // RegRead(x9536)
    val x9563 = OpDef(op=BitAnd, inputs=List(b5775, b5776)).name("x9563").ctrl(x9567).srcCtx("UnrollingBase.scala:28:66") // And(b5775,b5776)
    val x9564 = OpDef(op=BitAnd, inputs=List(b5724, b5677)).name("x9564").ctrl(x9567).srcCtx("UnrollingBase.scala:28:66") // And(b5724,b5677)
    val x9565 = OpDef(op=BitAnd, inputs=List(x9563, x9564)).name("x9565").ctrl(x9567).srcCtx("UnrollingBase.scala:28:66") // And(x9563,x9564)
    val x9566 = StoreBanks(List(x9494_d0_b0), List(b5773, b5774), x9562).name("x9566").ctrl(x9567).srcCtx("lenet_loops.scala:105:34") // SRAMStore(x9494,ArrayBuffer(Const(24), Const(24)),List(b5773, b5774),Const(0),x9562,x9565)
    val x9569 = Counter(min=Const(0), max=Const(12), step=Const(1), par=1).name("x9569").ctrl(x9605).srcCtx("lenet_loops.scala:107:31") // CounterNew(Const(0),Const(12),Const(1),Const(1))
    val x9570 = Counter(min=Const(0), max=Const(12), step=Const(1), par=1).name("x9570").ctrl(x9605).srcCtx("lenet_loops.scala:107:22") // CounterNew(Const(0),Const(12),Const(1),Const(1))
    val x9571 = CounterChain(List(x9570,x9569)).name("x9571").ctrl(x9605).srcCtx("lenet_loops.scala:107:37") // CounterChainNew(List(x9570, x9569))
    val x9604 = LoopController(style=MetaPipe, level=OuterControl, cchain=x9571).name("x9604").ctrl(x9605).srcCtx("lenet_loops.scala:107:37") // UnrolledForeach(List(b5724, b5677),x9571,Block(Const(())),List(List(b5817), List(b5818)),List(List(b5819), List(b5820)))
    val b5817 = CounterIter(x9570, Some(0)).name("b5817").ctrl(x9604) // b5817
    val b5819 = Const(true).name("b5819").ctrl(x9604) // b5819
    val b5818 = CounterIter(x9569, Some(0)).name("b5818").ctrl(x9604) // b5818
    val b5820 = Const(true).name("b5820").ctrl(x9604) // b5820
    val x9572_d0 = Reg(init=Some(0.0)).name("x9572_d0").ctrl(x9604).srcCtx("lenet_loops.scala:108:36:out") // x9572 = RegNew(Const(0))
    isAccum(x9572_d0) = false
    bufferDepthOf(x9572_d0) = 2
    val x9572_d1 = Reg(init=Some(0.0)).name("x9572_d1").ctrl(x9604).srcCtx("lenet_loops.scala:108:36:out") // x9572 = RegNew(Const(0))
    isAccum(x9572_d1) = true
    bufferDepthOf(x9572_d1) = 1
    val x9573 = Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x9573").ctrl(x9604).srcCtx("lenet_loops.scala:108:57") // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x9574 = Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x9574").ctrl(x9604).srcCtx("lenet_loops.scala:108:49") // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x9575 = CounterChain(List(x9574,x9573)).name("x9575").ctrl(x9604).srcCtx("lenet_loops.scala:110:15") // CounterChainNew(List(x9574, x9573))
    val x9597 = LoopController(style=InnerPipe, level=InnerControl, cchain=x9575).name("x9597").ctrl(x9604).srcCtx("lenet_loops.scala:110:15") // UnrolledReduce(List(b5819, b5820, b5724, b5677),x9575,x9572,Block((x9572) => Const(())),List(List(b5825), List(b5826)),List(List(b5827), List(b5828)))
    val b5825 = CounterIter(x9574, Some(0)).name("b5825").ctrl(x9597) // b5825
    val b5827 = Const(true).name("b5827").ctrl(x9597) // b5827
    val b5826 = CounterIter(x9573, None).name("b5826").ctrl(x9597) // b5826
    val b5828 = Const(true).name("b5828").ctrl(x9597) // b5828
    val x9576 = OpDef(op=FixSla, inputs=List(b5817, Const(1))).name("x9576").ctrl(x9597).srcCtx("lenet_loops.scala:109:44") // FixLsh(b5817,Const(1))
    val x9577 = OpDef(op=FixAdd, inputs=List(x9576, b5825)).name("x9577").ctrl(x9597).srcCtx("lenet_loops.scala:109:47") // FixAdd(x9576,b5825)
    val x9578 = OpDef(op=FixSla, inputs=List(b5818, Const(1))).name("x9578").ctrl(x9597).srcCtx("lenet_loops.scala:109:54") // FixLsh(b5818,Const(1))
    val x9579 = OpDef(op=FixAdd, inputs=List(x9578, b5826)).name("x9579").ctrl(x9597).srcCtx("lenet_loops.scala:109:57") // FixAdd(x9578,b5826)
    val x9580 = OpDef(op=BitAnd, inputs=List(b5827, b5828)).name("x9580").ctrl(x9597).srcCtx("UnrollingBase.scala:28:66") // And(b5827,b5828)
    val x9581 = OpDef(op=BitAnd, inputs=List(b5819, b5820)).name("x9581").ctrl(x9597).srcCtx("UnrollingBase.scala:28:66") // And(b5819,b5820)
    val x9582 = OpDef(op=BitAnd, inputs=List(b5724, b5677)).name("x9582").ctrl(x9597).srcCtx("UnrollingBase.scala:28:66") // And(b5724,b5677)
    val x9583 = OpDef(op=BitAnd, inputs=List(x9580, x9581)).name("x9583").ctrl(x9597).srcCtx("UnrollingBase.scala:28:66") // And(x9580,x9581)
    val x9584 = OpDef(op=BitAnd, inputs=List(x9583, x9582)).name("x9584").ctrl(x9597).srcCtx("UnrollingBase.scala:28:66") // And(x9583,x9582)
    val x9585 = LoadBanks(List(x9494_d0_b0), List(x9577, x9579)).name("x9585").ctrl(x9597).srcCtx("lenet_loops.scala:109:42") // ParSRAMLoad(x9494,List(List(x9577, x9579)),List(x9584))
    val x9586 = x9585 // x9586 = VectorApply(x9585,0)
    val x9587 = LoadBanks(List(x9377_d0_b0), List(b5723)).name("x9587").ctrl(x9597).srcCtx("lenet_loops.scala:109:72") // SRAMLoad(x9377,ArrayBuffer(Const(32)),List(b5723),Const(0),x9584)
    val x9588 = OpDef(op=FixAdd, inputs=List(x9586, x9587)).name("x9588").ctrl(x9597).srcCtx("lenet_loops.scala:109:63") // FixAdd(x9586,x9587)
    val x9589 = OpDef(op=FixMax, inputs=List(Const(0.0), x9588)).name("x9589").ctrl(x9597).srcCtx("lenet_loops.scala:109:18") // Max(Const(0),x9588)
    val x9590 = ReadMem(x9572_d1).name("x9590").ctrl(x9597).srcCtx("lenet_loops.scala:110:15") // RegRead(x9572)
    val x9591 = OpDef(op=FixEql, inputs=List(b5825, Const(0))).name("x9591").ctrl(x9597).srcCtx("lenet_loops.scala:110:15") // FixEql(b5825,Const(0))
    val x9592 = OpDef(op=FixEql, inputs=List(b5826, Const(0))).name("x9592").ctrl(x9597).srcCtx("lenet_loops.scala:110:15") // FixEql(b5826,Const(0))
    val x9593 = OpDef(op=BitAnd, inputs=List(x9591, x9592)).name("x9593").ctrl(x9597).srcCtx("lenet_loops.scala:110:15") // And(x9591,x9592)
    val x9594 = ReduceAccumOp(op=FixMax, input=x9589, accum=x9590).name("x9594").ctrl(x9597).srcCtx("lenet_loops.scala:110:29") // Max(x9589,x9590)
    val x9595 = OpDef(op=BitAnd, inputs=List(x9581, x9582)).name("x9595").ctrl(x9597).srcCtx("UnrollingBase.scala:28:66") // And(x9581,x9582)
    val x9596_x9572_d0 = WriteMem(x9572_d0, x9594).name("x9596_x9572_d0").ctrl(x9597).srcCtx("lenet_loops.scala:110:15") // RegWrite(x9572,x9594,x9595)
    val x9596_x9572_d1 = WriteMem(x9572_d1, x9594).name("x9596_x9572_d1").ctrl(x9597).srcCtx("lenet_loops.scala:110:15") // RegWrite(x9572,x9594,x9595)
    val x9603 = UnitController(style=SeqPipe, level=InnerControl).name("x9603").ctrl(x9604).srcCtx("lenet_loops.scala:107:37") // UnitPipe(List(b5819, b5820, b5724, b5677),Block(Const(())))
    val x9598 = ReadMem(x9572_d0).name("x9598").ctrl(x9603).srcCtx("lenet_loops.scala:111:43") // RegRead(x9572)
    val x9599 = OpDef(op=BitAnd, inputs=List(b5819, b5820)).name("x9599").ctrl(x9603).srcCtx("UnrollingBase.scala:28:66") // And(b5819,b5820)
    val x9600 = OpDef(op=BitAnd, inputs=List(b5724, b5677)).name("x9600").ctrl(x9603).srcCtx("UnrollingBase.scala:28:66") // And(b5724,b5677)
    val x9601 = OpDef(op=BitAnd, inputs=List(x9599, x9600)).name("x9601").ctrl(x9603).srcCtx("UnrollingBase.scala:28:66") // And(x9599,x9600)
    val x9602 = StoreBanks(List(x9491_d0_b0), List(b5723, b5817, b5818), x9598).name("x9602").ctrl(x9603).srcCtx("lenet_loops.scala:111:37") // SRAMStore(x9491,ArrayBuffer(Const(20), Const(12), Const(12)),List(b5723, b5817, b5818),Const(0),x9598,x9601)
    val x9606_d0_b0 = SRAM(size=800, banking=Strided(banks=1, stride=16)).name("x9606_d0_b0").ctrl(x9917).srcCtx("lenet_loops.scala:119:32:tmp2_SRAM") // x9606 = SRAMNew(ArrayBuffer(Const(50), Const(4), Const(4)))
    isAccum(x9606_d0_b0) = false
    bufferDepthOf(x9606_d0_b0) = 2
    val x9607 = Counter(min=Const(0), max=Const(50), step=Const(1), par=1).name("x9607").ctrl(x9917).srcCtx("lenet_loops.scala:120:25") // CounterNew(Const(0),Const(50),Const(1),Const(1))
    val x9608 = CounterChain(List(x9607)).name("x9608").ctrl(x9917).srcCtx("lenet_loops.scala:120:40") // CounterChainNew(List(x9607))
    val x9740 = LoopController(style=MetaPipe, level=OuterControl, cchain=x9608).name("x9740").ctrl(x9917).srcCtx("lenet_loops.scala:120:40") // UnrolledForeach(List(b5677),x9608,Block(Const(())),List(List(b5862)),List(List(b5863)))
    val b5862 = CounterIter(x9607, Some(0)).name("b5862").ctrl(x9740) // b5862
    val b5863 = Const(true).name("b5863").ctrl(x9740) // b5863
    val x9609_d0_b0 = SRAM(size=64, banking=Strided(banks=1, stride=8)).name("x9609_d0_b0").ctrl(x9740).srcCtx("lenet_loops.scala:128:39:tmp2_SRAM_conv") // x9609 = SRAMNew(ArrayBuffer(Const(8), Const(8)))
    isAccum(x9609_d0_b0) = false
    bufferDepthOf(x9609_d0_b0) = 2
    val x9609_d1_b0 = SRAM(size=64, banking=Strided(banks=1, stride=8)).name("x9609_d1_b0").ctrl(x9740).srcCtx("lenet_loops.scala:128:39:tmp2_SRAM_conv") // x9609 = SRAMNew(ArrayBuffer(Const(8), Const(8)))
    isAccum(x9609_d1_b0) = true
    bufferDepthOf(x9609_d1_b0) = 1
    val x9610_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=1)).name("x9610_d0_b0").ctrl(x9740).srcCtx("lenet_loops.scala:129:30:c2_RF") // x9610 = SRAMNew(ArrayBuffer(Const(512)))
    isAccum(x9610_d0_b0) = false
    bufferDepthOf(x9610_d0_b0) = 2
    val x9612 = UnitController(style=SeqPipe, level=InnerControl).name("x9612").ctrl(x9740).srcCtx("lenet_loops.scala:120:40") // UnitPipe(List(b5863, b5677),Block(Const(())))
    val x9611 = OpDef(op=FixAdd, inputs=List(b5862, Const(1))).name("x9611").ctrl(x9612).srcCtx("lenet_loops.scala:130:29") // FixAdd(b5862,Const(1))
    val x9613 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x9613").ctrl(x9740).srcCtx("lenet_loops.scala:130:17") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x9614 = CounterChain(List(x9613)).name("x9614").ctrl(x9740).srcCtx("lenet_loops.scala:130:17") // CounterChainNew(List(x9613))
    val x9641 = LoopController(style=StreamPipe, level=OuterControl, cchain=x9614).name("x9641").ctrl(x9740).srcCtx("lenet_loops.scala:130:17") // UnrolledForeach(List(b5863, b5677),x9614,Block(Const(())),List(List(b5870)),List(List(b5871)))
    val b5870 = CounterIter(x9613, Some(0)).name("b5870").ctrl(x9641) // b5870
    val b5871 = Const(true).name("b5871").ctrl(x9641) // b5871
    val b9980 = StreamOut(field="offset").name("b9980").ctrl(x9641).srcCtx("lenet_loops.scala:130:17") // x9615 = StreamOutNew(BurstCmdBus)
    isAccum(b9980) = false
    bufferDepthOf(b9980) = 1
    val b9981 = StreamOut(field="size").name("b9981").ctrl(x9641).srcCtx("lenet_loops.scala:130:17") // x9615 = StreamOutNew(BurstCmdBus)
    isAccum(b9981) = false
    bufferDepthOf(b9981) = 1
    val x9616 = StreamIn(field="data").name("x9616").ctrl(x9641).srcCtx("lenet_loops.scala:130:17") // x9616 = StreamInNew(BurstDataBus())
    isAccum(x9616) = false
    bufferDepthOf(x9616) = 1
    val x9630 = UnitController(style=SeqPipe, level=InnerControl).name("x9630").ctrl(x9641).srcCtx("lenet_loops.scala:130:17") // UnitPipe(List(b5871, b5863, b5677),Block(x9629))
    val x9617 = OpDef(op=FixAdd, inputs=List(b5862, b5870)).name("x9617").ctrl(x9630).srcCtx("lenet_loops.scala:130:17") // FixAdd(b5862,b5870)
    val x9618 = x9617 // FixConvert(x9617,TRUE,_32,_0) (Same Type. No op)
    val x9619 = OpDef(op=FixSla, inputs=List(x9618, Const(9))).name("x9619").ctrl(x9630).srcCtx("lenet_loops.scala:130:17") // FixLsh(x9618,Const(9))
    val x9620 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x9621 = OpDef(op=FixAdd, inputs=List(x9619, x9620)).name("x9621").ctrl(x9630).srcCtx("lenet_loops.scala:130:17") // FixAdd(x9619,x9620)
    val x9622 = OpDef(op=FixSla, inputs=List(x9621, Const(1))).name("x9622").ctrl(x9630).srcCtx("lenet_loops.scala:130:17") // FixLsh(x9621,Const(1))
    val x9623 = x9622 // FixConvert(x9622,TRUE,_64,_0)
    val x9624 = DramAddress(x9251).name("x9624").ctrl(x9630).srcCtx("lenet_loops.scala:130:17") // GetDRAMAddress(x9251)
    val x9626_x9625 = OpDef(op=FixAdd, inputs=List(x9623, x9624)).name("x9626_x9625").ctrl(x9630).srcCtx("lenet_loops.scala:130:17") // FixAdd(x9623,x9624)
    // x9626 = SimpleStruct(ArrayBuffer((offset,x9625), (size,Const(1024)), (isLoad,Const(true))))
    val x9627 = OpDef(op=BitAnd, inputs=List(b5871, b5863)).name("x9627").ctrl(x9630).srcCtx("UnrollingBase.scala:28:66") // And(b5871,b5863)
    val x9628 = OpDef(op=BitAnd, inputs=List(x9627, b5677)).name("x9628").ctrl(x9630).srcCtx("UnrollingBase.scala:28:66") // And(x9627,b5677)
    val x9629_b9982_b9980 = WriteMem(b9980, x9626_x9625).name("x9629_b9982_b9980").ctrl(x9630).srcCtx("lenet_loops.scala:130:17") // StreamWrite(x9615,x9626,x9628)
    val x9629_b9983_b9981 = WriteMem(b9981, Const(1024)).name("x9629_b9983_b9981").ctrl(x9630).srcCtx("lenet_loops.scala:130:17") // StreamWrite(x9615,x9626,x9628)
    val x9631 = FringeDenseLoad(dram=List(x9251), cmdStream=List(b9980, b9981), dataStream=List(x9616)).name("x9631").ctrl(x9641).srcCtx("lenet_loops.scala:130:17") // FringeDenseLoad(x9251,x9615,x9616)
    val x9632 = Counter(min=Const(0), max=Const(512), step=Const(1), par=1).name("x9632").ctrl(x9641).srcCtx("lenet_loops.scala:130:17") // CounterNew(Const(0),Const(512),Const(1),Const(1))
    val x9633 = CounterChain(List(x9632)).name("x9633").ctrl(x9641).srcCtx("lenet_loops.scala:130:17") // CounterChainNew(List(x9632))
    val x9640 = LoopController(style=InnerPipe, level=InnerControl, cchain=x9633).name("x9640").ctrl(x9641).srcCtx("lenet_loops.scala:130:17") // UnrolledForeach(List(b5871, b5863, b5677),x9633,Block(Const(())),List(List(b5891)),List(List(b5892)))
    val b5891 = CounterIter(x9632, None).name("b5891").ctrl(x9640) // b5891
    val b5892 = Const(true).name("b5892").ctrl(x9640) // b5892
    val x9634 = OpDef(op=BitAnd, inputs=List(b5892, b5871)).name("x9634").ctrl(x9640).srcCtx("UnrollingBase.scala:28:66") // And(b5892,b5871)
    val x9635 = OpDef(op=BitAnd, inputs=List(b5863, b5677)).name("x9635").ctrl(x9640).srcCtx("UnrollingBase.scala:28:66") // And(b5863,b5677)
    val x9636 = OpDef(op=BitAnd, inputs=List(x9634, x9635)).name("x9636").ctrl(x9640).srcCtx("UnrollingBase.scala:28:66") // And(x9634,x9635)
    val x9637_x9637 = ReadMem(x9616).name("x9637_x9637").ctrl(x9640).srcCtx("lenet_loops.scala:130:17") // ParStreamRead(x9616,List(x9636))
    val x9638_x9638 = x9637_x9637 // x9638 = VectorApply(x9637,0)
    val x9639 = StoreBanks(List(x9610_d0_b0), List(b5891), x9638_x9638).name("x9639").ctrl(x9640).srcCtx("lenet_loops.scala:130:17") // ParSRAMStore(x9610,List(List(b5891)),List(x9638),List(x9636))
    val x9642 = Counter(min=Const(0), max=Const(20), step=Const(1), par=1).name("x9642").ctrl(x9740).srcCtx("lenet_loops.scala:131:44") // CounterNew(Const(0),Const(20),Const(1),Const(1))
    val x9643 = CounterChain(List(x9642)).name("x9643").ctrl(x9740).srcCtx("lenet_loops.scala:140:12") // CounterChainNew(List(x9642))
    val x9703 = LoopController(style=MetaPipe, level=OuterControl, cchain=x9643).name("x9703").ctrl(x9740).srcCtx("lenet_loops.scala:140:12") // UnrolledReduce(List(b5863, b5677),x9643,x9609,Block((x9609) => Const(())),List(List(b5906)),List(List(b5907)))
    val b5906 = CounterIter(x9642, Some(0)).name("b5906").ctrl(x9703) // b5906
    val b5907 = Const(true).name("b5907").ctrl(x9703) // b5907
    val x9644_d0_b0 = SRAM(size=64, banking=Strided(banks=1, stride=8)).name("x9644_d0_b0").ctrl(x9703).srcCtx("lenet_loops.scala:132:33:result") // x9644 = SRAMNew(ArrayBuffer(Const(8), Const(8)))
    isAccum(x9644_d0_b0) = false
    bufferDepthOf(x9644_d0_b0) = 2
    val x9645 = Counter(min=Const(0), max=Const(8), step=Const(1), par=1).name("x9645").ctrl(x9703).srcCtx("lenet_loops.scala:133:44") // CounterNew(Const(0),Const(8),Const(1),Const(1))
    val x9646 = Counter(min=Const(0), max=Const(8), step=Const(1), par=1).name("x9646").ctrl(x9703).srcCtx("lenet_loops.scala:133:23") // CounterNew(Const(0),Const(8),Const(1),Const(1))
    val x9647 = CounterChain(List(x9646,x9645)).name("x9647").ctrl(x9703).srcCtx("lenet_loops.scala:133:51") // CounterChainNew(List(x9646, x9645))
    val x9685 = LoopController(style=MetaPipe, level=OuterControl, cchain=x9647).name("x9685").ctrl(x9703).srcCtx("lenet_loops.scala:133:51") // UnrolledForeach(List(b5907, b5863, b5677),x9647,Block(Const(())),List(List(b5912), List(b5913)),List(List(b5914), List(b5915)))
    val b5912 = CounterIter(x9646, Some(0)).name("b5912").ctrl(x9685) // b5912
    val b5914 = Const(true).name("b5914").ctrl(x9685) // b5914
    val b5913 = CounterIter(x9645, Some(0)).name("b5913").ctrl(x9685) // b5913
    val b5915 = Const(true).name("b5915").ctrl(x9685) // b5915
    val x9648_d0 = Reg(init=Some(0.0)).name("x9648_d0").ctrl(x9685).srcCtx("lenet_loops.scala:134:41:window") // x9648 = RegNew(Const(0))
    isAccum(x9648_d0) = false
    bufferDepthOf(x9648_d0) = 2
    val x9648_d1 = Reg(init=Some(0.0)).name("x9648_d1").ctrl(x9685).srcCtx("lenet_loops.scala:134:41:window") // x9648 = RegNew(Const(0))
    isAccum(x9648_d1) = true
    bufferDepthOf(x9648_d1) = 1
    val x9649 = Counter(min=Const(0), max=Const(5), step=Const(1), par=1).name("x9649").ctrl(x9685).srcCtx("lenet_loops.scala:134:81") // CounterNew(Const(0),Const(5),Const(1),Const(1))
    val x9650 = Counter(min=Const(0), max=Const(5), step=Const(1), par=1).name("x9650").ctrl(x9685).srcCtx("lenet_loops.scala:134:63") // CounterNew(Const(0),Const(5),Const(1),Const(1))
    val x9651 = CounterChain(List(x9650,x9649)).name("x9651").ctrl(x9685).srcCtx("lenet_loops.scala:136:16") // CounterChainNew(List(x9650, x9649))
    val x9677 = LoopController(style=InnerPipe, level=InnerControl, cchain=x9651).name("x9677").ctrl(x9685).srcCtx("lenet_loops.scala:136:16") // UnrolledReduce(List(b5914, b5915, b5907, b5863, b5677),x9651,x9648,Block((x9648) => Const(())),List(List(b5920), List(b5921)),List(List(b5922), List(b5923)))
    val b5920 = CounterIter(x9650, Some(0)).name("b5920").ctrl(x9677) // b5920
    val b5922 = Const(true).name("b5922").ctrl(x9677) // b5922
    val b5921 = CounterIter(x9649, None).name("b5921").ctrl(x9677) // b5921
    val b5923 = Const(true).name("b5923").ctrl(x9677) // b5923
    val x9652 = OpDef(op=FixAdd, inputs=List(b5912, b5920)).name("x9652").ctrl(x9677).srcCtx("lenet_loops.scala:135:35") // FixAdd(b5912,b5920)
    val x9653 = OpDef(op=FixAdd, inputs=List(b5913, b5921)).name("x9653").ctrl(x9677).srcCtx("lenet_loops.scala:135:39") // FixAdd(b5913,b5921)
    val x9654 = OpDef(op=BitAnd, inputs=List(b5922, b5923)).name("x9654").ctrl(x9677).srcCtx("UnrollingBase.scala:28:66") // And(b5922,b5923)
    val x9655 = OpDef(op=BitAnd, inputs=List(b5914, b5915)).name("x9655").ctrl(x9677).srcCtx("UnrollingBase.scala:28:66") // And(b5914,b5915)
    val x9656 = OpDef(op=BitAnd, inputs=List(b5907, b5863)).name("x9656").ctrl(x9677).srcCtx("UnrollingBase.scala:28:66") // And(b5907,b5863)
    val x9657 = OpDef(op=BitAnd, inputs=List(x9654, x9655)).name("x9657").ctrl(x9677).srcCtx("UnrollingBase.scala:28:66") // And(x9654,x9655)
    val x9658 = OpDef(op=BitAnd, inputs=List(x9656, b5677)).name("x9658").ctrl(x9677).srcCtx("UnrollingBase.scala:28:66") // And(x9656,b5677)
    val x9659 = OpDef(op=BitAnd, inputs=List(x9657, x9658)).name("x9659").ctrl(x9677).srcCtx("UnrollingBase.scala:28:66") // And(x9657,x9658)
    val x9660 = LoadBanks(List(x9491_d0_b0), List(b5906, x9652, x9653)).name("x9660").ctrl(x9677).srcCtx("lenet_loops.scala:135:26") // ParSRAMLoad(x9491,List(List(b5906, x9652, x9653)),List(x9659))
    val x9661 = x9660 // x9661 = VectorApply(x9660,0)
    val x9662 = OpDef(op=FixMul, inputs=List(b5906, Const(25))).name("x9662").ctrl(x9677).srcCtx("lenet_loops.scala:135:56") // FixMul(b5906,Const(25))
    val x9663 = OpDef(op=FixMul, inputs=List(b5920, Const(5))).name("x9663").ctrl(x9677).srcCtx("lenet_loops.scala:135:63") // FixMul(b5920,Const(5))
    val x9664 = OpDef(op=FixAdd, inputs=List(x9662, x9663)).name("x9664").ctrl(x9677).srcCtx("lenet_loops.scala:135:60") // FixAdd(x9662,x9663)
    val x9665 = OpDef(op=FixAdd, inputs=List(x9664, b5921)).name("x9665").ctrl(x9677).srcCtx("lenet_loops.scala:135:66") // FixAdd(x9664,b5921)
    val x9666 = LoadBanks(List(x9610_d0_b0), List(x9665)).name("x9666").ctrl(x9677).srcCtx("lenet_loops.scala:135:50") // ParSRAMLoad(x9610,List(List(x9665)),List(x9659))
    val x9667 = x9666 // x9667 = VectorApply(x9666,0)
    val x9668 = OpDef(op=FixMul, inputs=List(x9661, x9667)).name("x9668").ctrl(x9677).srcCtx("lenet_loops.scala:135:43") // FixMul(x9661,x9667)
    val x9669 = ReadMem(x9648_d1).name("x9669").ctrl(x9677).srcCtx("lenet_loops.scala:136:16") // RegRead(x9648)
    val x9670 = OpDef(op=FixEql, inputs=List(b5920, Const(0))).name("x9670").ctrl(x9677).srcCtx("lenet_loops.scala:136:16") // FixEql(b5920,Const(0))
    val x9671 = OpDef(op=FixEql, inputs=List(b5921, Const(0))).name("x9671").ctrl(x9677).srcCtx("lenet_loops.scala:136:16") // FixEql(b5921,Const(0))
    val x9672 = OpDef(op=BitAnd, inputs=List(x9670, x9671)).name("x9672").ctrl(x9677).srcCtx("lenet_loops.scala:136:16") // And(x9670,x9671)
    val x9673 = ReduceAccumOp(op=FixAdd, input=x9668, accum=x9669).name("x9673").ctrl(x9677).srcCtx("lenet_loops.scala:136:18") // FixAdd(x9668,x9669)
    val x9674 = OpDef(op=BitAnd, inputs=List(x9655, x9656)).name("x9674").ctrl(x9677).srcCtx("UnrollingBase.scala:28:66") // And(x9655,x9656)
    val x9675 = OpDef(op=BitAnd, inputs=List(x9674, b5677)).name("x9675").ctrl(x9677).srcCtx("UnrollingBase.scala:28:66") // And(x9674,b5677)
    val x9676_x9648_d0 = WriteMem(x9648_d0, x9673).name("x9676_x9648_d0").ctrl(x9677).srcCtx("lenet_loops.scala:136:16") // RegWrite(x9648,x9673,x9675)
    val x9676_x9648_d1 = WriteMem(x9648_d1, x9673).name("x9676_x9648_d1").ctrl(x9677).srcCtx("lenet_loops.scala:136:16") // RegWrite(x9648,x9673,x9675)
    val x9684 = UnitController(style=SeqPipe, level=InnerControl).name("x9684").ctrl(x9685).srcCtx("lenet_loops.scala:133:51") // UnitPipe(List(b5914, b5915, b5907, b5863, b5677),Block(Const(())))
    val x9678 = ReadMem(x9648_d0).name("x9678").ctrl(x9684).srcCtx("lenet_loops.scala:137:37") // RegRead(x9648)
    val x9679 = OpDef(op=BitAnd, inputs=List(b5914, b5915)).name("x9679").ctrl(x9684).srcCtx("UnrollingBase.scala:28:66") // And(b5914,b5915)
    val x9680 = OpDef(op=BitAnd, inputs=List(b5907, b5863)).name("x9680").ctrl(x9684).srcCtx("UnrollingBase.scala:28:66") // And(b5907,b5863)
    val x9681 = OpDef(op=BitAnd, inputs=List(x9679, x9680)).name("x9681").ctrl(x9684).srcCtx("UnrollingBase.scala:28:66") // And(x9679,x9680)
    val x9682 = OpDef(op=BitAnd, inputs=List(x9681, b5677)).name("x9682").ctrl(x9684).srcCtx("UnrollingBase.scala:28:66") // And(x9681,b5677)
    val x9683 = StoreBanks(List(x9644_d0_b0), List(b5912, b5913), x9678).name("x9683").ctrl(x9684).srcCtx("lenet_loops.scala:137:28") // SRAMStore(x9644,ArrayBuffer(Const(8), Const(8)),List(b5912, b5913),Const(0),x9678,x9682)
    val x9686 = Counter(min=Const(0), max=Const(8), step=Const(1), par=1).name("x9686").ctrl(x9703).srcCtx("lenet_loops.scala:140:12") // CounterNew(Const(0),Const(8),Const(1),Const(1))
    val x9687 = Counter(min=Const(0), max=Const(8), step=Const(1), par=1).name("x9687").ctrl(x9703).srcCtx("lenet_loops.scala:140:12") // CounterNew(Const(0),Const(8),Const(1),Const(1))
    val x9688 = CounterChain(List(x9687,x9686)).name("x9688").ctrl(x9703).srcCtx("lenet_loops.scala:140:12") // CounterChainNew(ArrayBuffer(x9687, x9686))
    val x9702 = LoopController(style=InnerPipe, level=InnerControl, cchain=x9688).name("x9702").ctrl(x9703).srcCtx("lenet_loops.scala:140:12") // UnrolledForeach(List(),x9688,Block(Const(())),ArrayBuffer(List(b5958), List(b5959)),ArrayBuffer(List(b5960), List(b5961)))
    val b5958 = CounterIter(x9687, Some(0)).name("b5958").ctrl(x9702) // b5958
    val b5960 = Const(true).name("b5960").ctrl(x9702) // b5960
    val b5959 = CounterIter(x9686, None).name("b5959").ctrl(x9702) // b5959
    val b5961 = Const(true).name("b5961").ctrl(x9702) // b5961
    val x9689 = OpDef(op=BitAnd, inputs=List(b5960, b5961)).name("x9689").ctrl(x9702).srcCtx("UnrollingBase.scala:28:66") // And(b5960,b5961)
    val x9690 = OpDef(op=BitAnd, inputs=List(b5863, b5677)).name("x9690").ctrl(x9702).srcCtx("UnrollingBase.scala:28:66") // And(b5863,b5677)
    val x9691 = OpDef(op=BitAnd, inputs=List(x9689, x9690)).name("x9691").ctrl(x9702).srcCtx("UnrollingBase.scala:28:66") // And(x9689,x9690)
    val x9692 = LoadBanks(List(x9644_d0_b0), List(b5958, b5959)).name("x9692").ctrl(x9702).srcCtx("lenet_loops.scala:140:12") // ParSRAMLoad(x9644,List(ArrayBuffer(b5958, b5959)),List(x9691))
    val x9693 = x9692 // x9693 = VectorApply(x9692,0)
    val x9694 = LoadBanks(List(x9609_d1_b0), List(b5958, b5959)).name("x9694").ctrl(x9702).srcCtx("lenet_loops.scala:140:12") // ParSRAMLoad(x9609,List(ArrayBuffer(b5958, b5959)),List(x9691))
    val x9695 = x9694 // x9695 = VectorApply(x9694,0)
    val x9696 = OpDef(op=BitAnd, inputs=List(b5907, b5863)).name("x9696").ctrl(x9702).srcCtx("lenet_loops.scala:140:12") // And(b5907,b5863)
    val x9697 = OpDef(op=BitAnd, inputs=List(x9696, b5677)).name("x9697").ctrl(x9702).srcCtx("lenet_loops.scala:140:12") // And(x9696,b5677)
    val x9698 = OpDef(op=BitAnd, inputs=List(x9697, x9691)).name("x9698").ctrl(x9702).srcCtx("lenet_loops.scala:140:12") // And(x9697,x9691)
    val x9699 = OpDef(op=FixEql, inputs=List(b5906, Const(0))).name("x9699").ctrl(x9702).srcCtx("lenet_loops.scala:140:12") // FixEql(b5906,Const(0))
    val x9700 = ReduceAccumOp(op=FixAdd, input=x9693, accum=x9695).name("x9700").ctrl(x9702).srcCtx("lenet_loops.scala:140:14") // FixAdd(x9693,x9695)
    val x9701 = StoreBanks(List(x9609_d0_b0, x9609_d1_b0), List(b5958, b5959), x9700).name("x9701").ctrl(x9702).srcCtx("lenet_loops.scala:140:12") // ParSRAMStore(x9609,List(ArrayBuffer(b5958, b5959)),List(x9700),List(x9691))
    val x9704 = Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x9704").ctrl(x9740).srcCtx("lenet_loops.scala:142:29") // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x9705 = Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x9705").ctrl(x9740).srcCtx("lenet_loops.scala:142:21") // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x9706 = CounterChain(List(x9705,x9704)).name("x9706").ctrl(x9740).srcCtx("lenet_loops.scala:142:35") // CounterChainNew(List(x9705, x9704))
    val x9739 = LoopController(style=MetaPipe, level=OuterControl, cchain=x9706).name("x9739").ctrl(x9740).srcCtx("lenet_loops.scala:142:35") // UnrolledForeach(List(b5863, b5677),x9706,Block(Const(())),List(List(b5980), List(b5981)),List(List(b5982), List(b5983)))
    val b5980 = CounterIter(x9705, Some(0)).name("b5980").ctrl(x9739) // b5980
    val b5982 = Const(true).name("b5982").ctrl(x9739) // b5982
    val b5981 = CounterIter(x9704, Some(0)).name("b5981").ctrl(x9739) // b5981
    val b5983 = Const(true).name("b5983").ctrl(x9739) // b5983
    val x9707_d0 = Reg(init=Some(0.0)).name("x9707_d0").ctrl(x9739).srcCtx("lenet_loops.scala:143:36:out") // x9707 = RegNew(Const(0))
    isAccum(x9707_d0) = false
    bufferDepthOf(x9707_d0) = 2
    val x9707_d1 = Reg(init=Some(0.0)).name("x9707_d1").ctrl(x9739).srcCtx("lenet_loops.scala:143:36:out") // x9707 = RegNew(Const(0))
    isAccum(x9707_d1) = true
    bufferDepthOf(x9707_d1) = 1
    val x9708 = Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x9708").ctrl(x9739).srcCtx("lenet_loops.scala:143:57") // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x9709 = Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x9709").ctrl(x9739).srcCtx("lenet_loops.scala:143:49") // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x9710 = CounterChain(List(x9709,x9708)).name("x9710").ctrl(x9739).srcCtx("lenet_loops.scala:145:15") // CounterChainNew(List(x9709, x9708))
    val x9732 = LoopController(style=InnerPipe, level=InnerControl, cchain=x9710).name("x9732").ctrl(x9739).srcCtx("lenet_loops.scala:145:15") // UnrolledReduce(List(b5982, b5983, b5863, b5677),x9710,x9707,Block((x9707) => Const(())),List(List(b5988), List(b5989)),List(List(b5990), List(b5991)))
    val b5988 = CounterIter(x9709, Some(0)).name("b5988").ctrl(x9732) // b5988
    val b5990 = Const(true).name("b5990").ctrl(x9732) // b5990
    val b5989 = CounterIter(x9708, None).name("b5989").ctrl(x9732) // b5989
    val b5991 = Const(true).name("b5991").ctrl(x9732) // b5991
    val x9711 = OpDef(op=FixSla, inputs=List(b5980, Const(1))).name("x9711").ctrl(x9732).srcCtx("lenet_loops.scala:144:44") // FixLsh(b5980,Const(1))
    val x9712 = OpDef(op=FixAdd, inputs=List(x9711, b5988)).name("x9712").ctrl(x9732).srcCtx("lenet_loops.scala:144:47") // FixAdd(x9711,b5988)
    val x9713 = OpDef(op=FixSla, inputs=List(b5981, Const(1))).name("x9713").ctrl(x9732).srcCtx("lenet_loops.scala:144:54") // FixLsh(b5981,Const(1))
    val x9714 = OpDef(op=FixAdd, inputs=List(x9713, b5989)).name("x9714").ctrl(x9732).srcCtx("lenet_loops.scala:144:57") // FixAdd(x9713,b5989)
    val x9715 = OpDef(op=BitAnd, inputs=List(b5990, b5991)).name("x9715").ctrl(x9732).srcCtx("UnrollingBase.scala:28:66") // And(b5990,b5991)
    val x9716 = OpDef(op=BitAnd, inputs=List(b5982, b5983)).name("x9716").ctrl(x9732).srcCtx("UnrollingBase.scala:28:66") // And(b5982,b5983)
    val x9717 = OpDef(op=BitAnd, inputs=List(b5863, b5677)).name("x9717").ctrl(x9732).srcCtx("UnrollingBase.scala:28:66") // And(b5863,b5677)
    val x9718 = OpDef(op=BitAnd, inputs=List(x9715, x9716)).name("x9718").ctrl(x9732).srcCtx("UnrollingBase.scala:28:66") // And(x9715,x9716)
    val x9719 = OpDef(op=BitAnd, inputs=List(x9718, x9717)).name("x9719").ctrl(x9732).srcCtx("UnrollingBase.scala:28:66") // And(x9718,x9717)
    val x9720 = LoadBanks(List(x9609_d0_b0), List(x9712, x9714)).name("x9720").ctrl(x9732).srcCtx("lenet_loops.scala:144:42") // ParSRAMLoad(x9609,List(List(x9712, x9714)),List(x9719))
    val x9721 = x9720 // x9721 = VectorApply(x9720,0)
    val x9722 = LoadBanks(List(x9396_d0_b0), List(b5862)).name("x9722").ctrl(x9732).srcCtx("lenet_loops.scala:144:72") // SRAMLoad(x9396,ArrayBuffer(Const(64)),List(b5862),Const(0),x9719)
    val x9723 = OpDef(op=FixAdd, inputs=List(x9721, x9722)).name("x9723").ctrl(x9732).srcCtx("lenet_loops.scala:144:63") // FixAdd(x9721,x9722)
    val x9724 = OpDef(op=FixMax, inputs=List(Const(0.0), x9723)).name("x9724").ctrl(x9732).srcCtx("lenet_loops.scala:144:18") // Max(Const(0),x9723)
    val x9725 = ReadMem(x9707_d1).name("x9725").ctrl(x9732).srcCtx("lenet_loops.scala:145:15") // RegRead(x9707)
    val x9726 = OpDef(op=FixEql, inputs=List(b5988, Const(0))).name("x9726").ctrl(x9732).srcCtx("lenet_loops.scala:145:15") // FixEql(b5988,Const(0))
    val x9727 = OpDef(op=FixEql, inputs=List(b5989, Const(0))).name("x9727").ctrl(x9732).srcCtx("lenet_loops.scala:145:15") // FixEql(b5989,Const(0))
    val x9728 = OpDef(op=BitAnd, inputs=List(x9726, x9727)).name("x9728").ctrl(x9732).srcCtx("lenet_loops.scala:145:15") // And(x9726,x9727)
    val x9729 = ReduceAccumOp(op=FixMax, input=x9724, accum=x9725).name("x9729").ctrl(x9732).srcCtx("lenet_loops.scala:145:29") // Max(x9724,x9725)
    val x9730 = OpDef(op=BitAnd, inputs=List(x9716, x9717)).name("x9730").ctrl(x9732).srcCtx("UnrollingBase.scala:28:66") // And(x9716,x9717)
    val x9731_x9707_d0 = WriteMem(x9707_d0, x9729).name("x9731_x9707_d0").ctrl(x9732).srcCtx("lenet_loops.scala:145:15") // RegWrite(x9707,x9729,x9730)
    val x9731_x9707_d1 = WriteMem(x9707_d1, x9729).name("x9731_x9707_d1").ctrl(x9732).srcCtx("lenet_loops.scala:145:15") // RegWrite(x9707,x9729,x9730)
    val x9738 = UnitController(style=SeqPipe, level=InnerControl).name("x9738").ctrl(x9739).srcCtx("lenet_loops.scala:142:35") // UnitPipe(List(b5982, b5983, b5863, b5677),Block(Const(())))
    val x9733 = ReadMem(x9707_d0).name("x9733").ctrl(x9738).srcCtx("lenet_loops.scala:146:43") // RegRead(x9707)
    val x9734 = OpDef(op=BitAnd, inputs=List(b5982, b5983)).name("x9734").ctrl(x9738).srcCtx("UnrollingBase.scala:28:66") // And(b5982,b5983)
    val x9735 = OpDef(op=BitAnd, inputs=List(b5863, b5677)).name("x9735").ctrl(x9738).srcCtx("UnrollingBase.scala:28:66") // And(b5863,b5677)
    val x9736 = OpDef(op=BitAnd, inputs=List(x9734, x9735)).name("x9736").ctrl(x9738).srcCtx("UnrollingBase.scala:28:66") // And(x9734,x9735)
    val x9737 = StoreBanks(List(x9606_d0_b0), List(b5862, b5980, b5981), x9733).name("x9737").ctrl(x9738).srcCtx("lenet_loops.scala:146:37") // SRAMStore(x9606,ArrayBuffer(Const(50), Const(4), Const(4)),List(b5862, b5980, b5981),Const(0),x9733,x9736)
    val x9741_d0_b0 = SRAM(size=800, banking=Strided(banks=1, stride=1)).name("x9741_d0_b0").ctrl(x9917).srcCtx("lenet_loops.scala:154:32:tmp3_SRAM") // x9741 = SRAMNew(ArrayBuffer(Const(800)))
    isAccum(x9741_d0_b0) = false
    bufferDepthOf(x9741_d0_b0) = 2
    val x9742 = Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x9742").ctrl(x9917).srcCtx("lenet_loops.scala:155:36") // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x9743 = Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x9743").ctrl(x9917).srcCtx("lenet_loops.scala:155:28") // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x9744 = Counter(min=Const(0), max=Const(50), step=Const(1), par=1).name("x9744").ctrl(x9917).srcCtx("lenet_loops.scala:155:20") // CounterNew(Const(0),Const(50),Const(1),Const(1))
    val x9745 = CounterChain(List(x9744,x9743,x9742)).name("x9745").ctrl(x9917).srcCtx("lenet_loops.scala:155:42") // CounterChainNew(List(x9744, x9743, x9742))
    val x9757 = LoopController(style=InnerPipe, level=InnerControl, cchain=x9745).name("x9757").ctrl(x9917).srcCtx("lenet_loops.scala:155:42") // UnrolledForeach(List(b5677),x9745,Block(Const(())),List(List(b6027), List(b6028), List(b6029)),List(List(b6030), List(b6031), List(b6032)))
    val b6027 = CounterIter(x9744, Some(0)).name("b6027").ctrl(x9757) // b6027
    val b6030 = Const(true).name("b6030").ctrl(x9757) // b6030
    val b6028 = CounterIter(x9743, Some(0)).name("b6028").ctrl(x9757) // b6028
    val b6031 = Const(true).name("b6031").ctrl(x9757) // b6031
    val b6029 = CounterIter(x9742, None).name("b6029").ctrl(x9757) // b6029
    val b6032 = Const(true).name("b6032").ctrl(x9757) // b6032
    val x9746 = OpDef(op=FixMul, inputs=List(b6029, Const(50))).name("x9746").ctrl(x9757).srcCtx("lenet_loops.scala:156:22") // FixMul(b6029,Const(50))
    val x9747 = OpDef(op=FixSla, inputs=List(b6028, Const(2))).name("x9747").ctrl(x9757).srcCtx("lenet_loops.scala:156:29") // FixLsh(b6028,Const(2))
    val x9748 = OpDef(op=FixMul, inputs=List(x9747, Const(50))).name("x9748").ctrl(x9757).srcCtx("lenet_loops.scala:156:31") // FixMul(x9747,Const(50))
    val x9749 = OpDef(op=FixAdd, inputs=List(x9746, x9748)).name("x9749").ctrl(x9757).srcCtx("lenet_loops.scala:156:26") // FixAdd(x9746,x9748)
    val x9750 = OpDef(op=FixAdd, inputs=List(x9749, b6027)).name("x9750").ctrl(x9757).srcCtx("lenet_loops.scala:156:35") // FixAdd(x9749,b6027)
    val x9751 = OpDef(op=BitAnd, inputs=List(b6030, b6031)).name("x9751").ctrl(x9757).srcCtx("UnrollingBase.scala:28:66") // And(b6030,b6031)
    val x9752 = OpDef(op=BitAnd, inputs=List(b6032, b5677)).name("x9752").ctrl(x9757).srcCtx("UnrollingBase.scala:28:66") // And(b6032,b5677)
    val x9753 = OpDef(op=BitAnd, inputs=List(x9751, x9752)).name("x9753").ctrl(x9757).srcCtx("UnrollingBase.scala:28:66") // And(x9751,x9752)
    val x9754 = LoadBanks(List(x9606_d0_b0), List(b6027, b6028, b6029)).name("x9754").ctrl(x9757).srcCtx("lenet_loops.scala:156:51") // ParSRAMLoad(x9606,List(List(b6027, b6028, b6029)),List(x9753))
    val x9755 = x9754 // x9755 = VectorApply(x9754,0)
    val x9756 = StoreBanks(List(x9741_d0_b0), List(x9750), x9755).name("x9756").ctrl(x9757).srcCtx("lenet_loops.scala:156:40") // ParSRAMStore(x9741,List(List(x9750)),List(x9755),List(x9753))
    val x9758_d0_b0 = SRAM(size=500, banking=Strided(banks=1, stride=1)).name("x9758_d0_b0").ctrl(x9917).srcCtx("lenet_loops.scala:160:32:tmp4_SRAM") // x9758 = SRAMNew(ArrayBuffer(Const(500)))
    isAccum(x9758_d0_b0) = false
    bufferDepthOf(x9758_d0_b0) = 2
    val x9759 = Counter(min=Const(0), max=Const(100), step=Const(1), par=1).name("x9759").ctrl(x9917).srcCtx("lenet_loops.scala:161:26") // CounterNew(Const(0),Const(100),Const(1),Const(1))
    val x9760 = CounterChain(List(x9759)).name("x9760").ctrl(x9917).srcCtx("lenet_loops.scala:161:39") // CounterChainNew(List(x9759))
    val x9826 = LoopController(style=MetaPipe, level=OuterControl, cchain=x9760).name("x9826").ctrl(x9917).srcCtx("lenet_loops.scala:161:39") // UnrolledForeach(List(b5677),x9760,Block(Const(())),List(List(b6048)),List(List(b6049)))
    val b6048 = CounterIter(x9759, Some(0)).name("b6048").ctrl(x9826) // b6048
    val b6049 = Const(true).name("b6049").ctrl(x9826) // b6049
    val x9761_d0_b0 = SRAM(size=4000, banking=Strided(banks=16, stride=1)).name("x9761_d0_b0").ctrl(x9826).srcCtx("lenet_loops.scala:162:36:c4_row_SRAM") // x9761 = SRAMNew(ArrayBuffer(Const(4000)))
    isAccum(x9761_d0_b0) = false
    bufferDepthOf(x9761_d0_b0) = 2
    val x9763 = UnitController(style=SeqPipe, level=InnerControl).name("x9763").ctrl(x9826).srcCtx("lenet_loops.scala:161:39") // UnitPipe(List(b6049, b5677),Block(Const(())))
    val x9762 = OpDef(op=FixAdd, inputs=List(b6048, Const(1))).name("x9762").ctrl(x9763).srcCtx("lenet_loops.scala:163:35") // FixAdd(b6048,Const(1))
    val x9764 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x9764").ctrl(x9826).srcCtx("lenet_loops.scala:163:23") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x9765 = CounterChain(List(x9764)).name("x9765").ctrl(x9826).srcCtx("lenet_loops.scala:163:23") // CounterChainNew(List(x9764))
    val x9792 = LoopController(style=StreamPipe, level=OuterControl, cchain=x9765).name("x9792").ctrl(x9826).srcCtx("lenet_loops.scala:163:23") // UnrolledForeach(List(b6049, b5677),x9765,Block(Const(())),List(List(b6055)),List(List(b6056)))
    val b6055 = CounterIter(x9764, Some(0)).name("b6055").ctrl(x9792) // b6055
    val b6056 = Const(true).name("b6056").ctrl(x9792) // b6056
    val b9984 = StreamOut(field="offset").name("b9984").ctrl(x9792).srcCtx("lenet_loops.scala:163:23") // x9766 = StreamOutNew(BurstCmdBus)
    isAccum(b9984) = false
    bufferDepthOf(b9984) = 1
    val b9985 = StreamOut(field="size").name("b9985").ctrl(x9792).srcCtx("lenet_loops.scala:163:23") // x9766 = StreamOutNew(BurstCmdBus)
    isAccum(b9985) = false
    bufferDepthOf(b9985) = 1
    val x9767 = StreamIn(field="data").name("x9767").ctrl(x9792).srcCtx("lenet_loops.scala:163:23") // x9767 = StreamInNew(BurstDataBus())
    isAccum(x9767) = false
    bufferDepthOf(x9767) = 1
    val x9781 = UnitController(style=SeqPipe, level=InnerControl).name("x9781").ctrl(x9792).srcCtx("lenet_loops.scala:163:23") // UnitPipe(List(b6056, b6049, b5677),Block(x9780))
    val x9768 = OpDef(op=FixAdd, inputs=List(b6048, b6055)).name("x9768").ctrl(x9781).srcCtx("lenet_loops.scala:163:23") // FixAdd(b6048,b6055)
    val x9769 = x9768 // FixConvert(x9768,TRUE,_32,_0) (Same Type. No op)
    val x9770 = OpDef(op=FixMul, inputs=List(x9769, Const(4000))).name("x9770").ctrl(x9781).srcCtx("lenet_loops.scala:163:23") // FixMul(x9769,Const(4000))
    val x9771 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x9772 = OpDef(op=FixAdd, inputs=List(x9770, x9771)).name("x9772").ctrl(x9781).srcCtx("lenet_loops.scala:163:23") // FixAdd(x9770,x9771)
    val x9773 = OpDef(op=FixSla, inputs=List(x9772, Const(1))).name("x9773").ctrl(x9781).srcCtx("lenet_loops.scala:163:23") // FixLsh(x9772,Const(1))
    val x9774 = x9773 // FixConvert(x9773,TRUE,_64,_0)
    val x9775 = DramAddress(x9253).name("x9775").ctrl(x9781).srcCtx("lenet_loops.scala:163:23") // GetDRAMAddress(x9253)
    val x9777_x9776 = OpDef(op=FixAdd, inputs=List(x9774, x9775)).name("x9777_x9776").ctrl(x9781).srcCtx("lenet_loops.scala:163:23") // FixAdd(x9774,x9775)
    // x9777 = SimpleStruct(ArrayBuffer((offset,x9776), (size,Const(8000)), (isLoad,Const(true))))
    val x9778 = OpDef(op=BitAnd, inputs=List(b6056, b6049)).name("x9778").ctrl(x9781).srcCtx("UnrollingBase.scala:28:66") // And(b6056,b6049)
    val x9779 = OpDef(op=BitAnd, inputs=List(x9778, b5677)).name("x9779").ctrl(x9781).srcCtx("UnrollingBase.scala:28:66") // And(x9778,b5677)
    val x9780_b9986_b9984 = WriteMem(b9984, x9777_x9776).name("x9780_b9986_b9984").ctrl(x9781).srcCtx("lenet_loops.scala:163:23") // StreamWrite(x9766,x9777,x9779)
    val x9780_b9987_b9985 = WriteMem(b9985, Const(8000)).name("x9780_b9987_b9985").ctrl(x9781).srcCtx("lenet_loops.scala:163:23") // StreamWrite(x9766,x9777,x9779)
    val x9782 = FringeDenseLoad(dram=List(x9253), cmdStream=List(b9984, b9985), dataStream=List(x9767)).name("x9782").ctrl(x9792).srcCtx("lenet_loops.scala:163:23") // FringeDenseLoad(x9253,x9766,x9767)
    val x9783 = Counter(min=Const(0), max=Const(4000), step=Const(1), par=16).name("x9783").ctrl(x9792).srcCtx("lenet_loops.scala:163:23") // CounterNew(Const(0),Const(4000),Const(1),Const(16))
    val x9784 = CounterChain(List(x9783)).name("x9784").ctrl(x9792).srcCtx("lenet_loops.scala:163:23") // CounterChainNew(List(x9783))
    val x9791 = LoopController(style=InnerPipe, level=InnerControl, cchain=x9784).name("x9791").ctrl(x9792).srcCtx("lenet_loops.scala:163:23") // UnrolledForeach(List(b6056, b6049, b5677),x9784,Block(Const(())),List(List(b6076)),List(List(b6077)))
    val b6076 = CounterIter(x9783, None).name("b6076").ctrl(x9791) // b6076
    val b6077 = Const(true).name("b6077").ctrl(x9791) // b6077
    val x9785 = OpDef(op=BitAnd, inputs=List(b6077, b6056)).name("x9785").ctrl(x9791).srcCtx("UnrollingBase.scala:28:66") // And(b6077,b6056)
    val x9786 = OpDef(op=BitAnd, inputs=List(b6049, b5677)).name("x9786").ctrl(x9791).srcCtx("UnrollingBase.scala:28:66") // And(b6049,b5677)
    val x9787 = OpDef(op=BitAnd, inputs=List(x9785, x9786)).name("x9787").ctrl(x9791).srcCtx("UnrollingBase.scala:28:66") // And(x9785,x9786)
    val x9788_x9788 = ReadMem(x9767).name("x9788_x9788").ctrl(x9791).srcCtx("lenet_loops.scala:163:23") // ParStreamRead(x9767,List(x9787))
    val x9789_x9789 = x9788_x9788 // x9789 = VectorApply(x9788,0)
    val x9790 = StoreBanks(List(x9761_d0_b0), List(b6076), x9789_x9789).name("x9790").ctrl(x9791).srcCtx("lenet_loops.scala:163:23") // ParSRAMStore(x9761,List(List(b6076)),List(x9789),List(x9787))
    val x9793 = Counter(min=Const(0), max=Const(5), step=Const(1), par=1).name("x9793").ctrl(x9826).srcCtx("lenet_loops.scala:164:21") // CounterNew(Const(0),Const(5),Const(1),Const(1))
    val x9794 = CounterChain(List(x9793)).name("x9794").ctrl(x9826).srcCtx("lenet_loops.scala:164:26") // CounterChainNew(List(x9793))
    val x9825 = LoopController(style=MetaPipe, level=OuterControl, cchain=x9794).name("x9825").ctrl(x9826).srcCtx("lenet_loops.scala:164:26") // UnrolledForeach(List(b6049, b5677),x9794,Block(Const(())),List(List(b6088)),List(List(b6089)))
    val b6088 = CounterIter(x9793, Some(0)).name("b6088").ctrl(x9825) // b6088
    val b6089 = Const(true).name("b6089").ctrl(x9825) // b6089
    val x9795_d0 = Reg(init=Some(0.0)).name("x9795_d0").ctrl(x9825).srcCtx("lenet_loops.scala:165:37:prod") // x9795 = RegNew(Const(0))
    isAccum(x9795_d0) = false
    bufferDepthOf(x9795_d0) = 2
    val x9795_d1 = Reg(init=Some(0.0)).name("x9795_d1").ctrl(x9825).srcCtx("lenet_loops.scala:165:37:prod") // x9795 = RegNew(Const(0))
    isAccum(x9795_d1) = true
    bufferDepthOf(x9795_d1) = 1
    val x9796 = Counter(min=Const(0), max=Const(800), step=Const(1), par=1).name("x9796").ctrl(x9825).srcCtx("lenet_loops.scala:165:57") // CounterNew(Const(0),Const(800),Const(1),Const(1))
    val x9797 = CounterChain(List(x9796)).name("x9797").ctrl(x9825).srcCtx("lenet_loops.scala:167:14") // CounterChainNew(List(x9796))
    val x9814 = LoopController(style=InnerPipe, level=InnerControl, cchain=x9797).name("x9814").ctrl(x9825).srcCtx("lenet_loops.scala:167:14") // UnrolledReduce(List(b6089, b6049, b5677),x9797,x9795,Block((x9795) => Const(())),List(List(b6093)),List(List(b6094)))
    val b6093 = CounterIter(x9796, None).name("b6093").ctrl(x9814) // b6093
    val b6094 = Const(true).name("b6094").ctrl(x9814) // b6094
    val x9798 = OpDef(op=BitAnd, inputs=List(b6094, b6089)).name("x9798").ctrl(x9814).srcCtx("UnrollingBase.scala:28:66") // And(b6094,b6089)
    val x9799 = OpDef(op=BitAnd, inputs=List(b6049, b5677)).name("x9799").ctrl(x9814).srcCtx("UnrollingBase.scala:28:66") // And(b6049,b5677)
    val x9800 = OpDef(op=BitAnd, inputs=List(x9798, x9799)).name("x9800").ctrl(x9814).srcCtx("UnrollingBase.scala:28:66") // And(x9798,x9799)
    val x9801 = LoadBanks(List(x9741_d0_b0), List(b6093)).name("x9801").ctrl(x9814).srcCtx("lenet_loops.scala:166:24") // ParSRAMLoad(x9741,List(List(b6093)),List(x9800))
    val x9802 = x9801 // x9802 = VectorApply(x9801,0)
    val x9803 = OpDef(op=FixMul, inputs=List(b6088, Const(800))).name("x9803").ctrl(x9814).srcCtx("lenet_loops.scala:166:52") // FixMul(b6088,Const(800))
    val x9804 = OpDef(op=FixAdd, inputs=List(x9803, b6093)).name("x9804").ctrl(x9814).srcCtx("lenet_loops.scala:166:57") // FixAdd(x9803,b6093)
    val x9805 = LoadBanks(List(x9761_d0_b0), List(x9804)).name("x9805").ctrl(x9814).srcCtx("lenet_loops.scala:166:44") // ParSRAMLoad(x9761,List(List(x9804)),List(x9800))
    val x9806 = x9805 // x9806 = VectorApply(x9805,0)
    val x9807 = OpDef(op=FixMul, inputs=List(x9802, x9806)).name("x9807").ctrl(x9814).srcCtx("lenet_loops.scala:166:31") // FixMul(x9802,x9806)
    val x9808 = ReadMem(x9795_d1).name("x9808").ctrl(x9814).srcCtx("lenet_loops.scala:167:14") // RegRead(x9795)
    val x9809 = OpDef(op=FixEql, inputs=List(b6093, Const(0))).name("x9809").ctrl(x9814).srcCtx("lenet_loops.scala:167:14") // FixEql(b6093,Const(0))
    val x9810 = ReduceAccumOp(op=FixAdd, input=x9807, accum=x9808).name("x9810").ctrl(x9814).srcCtx("lenet_loops.scala:167:16") // FixAdd(x9807,x9808)
    val x9811 = OpDef(op=BitAnd, inputs=List(b6089, b6049)).name("x9811").ctrl(x9814).srcCtx("UnrollingBase.scala:28:66") // And(b6089,b6049)
    val x9812 = OpDef(op=BitAnd, inputs=List(x9811, b5677)).name("x9812").ctrl(x9814).srcCtx("UnrollingBase.scala:28:66") // And(x9811,b5677)
    val x9813_x9795_d0 = WriteMem(x9795_d0, x9810).name("x9813_x9795_d0").ctrl(x9814).srcCtx("lenet_loops.scala:167:14") // RegWrite(x9795,x9810,x9812)
    val x9813_x9795_d1 = WriteMem(x9795_d1, x9810).name("x9813_x9795_d1").ctrl(x9814).srcCtx("lenet_loops.scala:167:14") // RegWrite(x9795,x9810,x9812)
    val x9824 = UnitController(style=SeqPipe, level=InnerControl).name("x9824").ctrl(x9825).srcCtx("lenet_loops.scala:164:26") // UnitPipe(List(b6089, b6049, b5677),Block(Const(())))
    val x9815 = OpDef(op=FixMul, inputs=List(b6048, Const(5))).name("x9815").ctrl(x9824).srcCtx("lenet_loops.scala:168:28") // FixMul(b6048,Const(5))
    val x9816 = OpDef(op=FixAdd, inputs=List(x9815, b6088)).name("x9816").ctrl(x9824).srcCtx("lenet_loops.scala:168:31") // FixAdd(x9815,b6088)
    val x9817 = ReadMem(x9795_d0).name("x9817").ctrl(x9824).srcCtx("lenet_loops.scala:168:62") // RegRead(x9795)
    val x9818 = OpDef(op=BitAnd, inputs=List(b6089, b6049)).name("x9818").ctrl(x9824).srcCtx("UnrollingBase.scala:28:66") // And(b6089,b6049)
    val x9819 = OpDef(op=BitAnd, inputs=List(x9818, b5677)).name("x9819").ctrl(x9824).srcCtx("UnrollingBase.scala:28:66") // And(x9818,b5677)
    val x9820 = LoadBanks(List(x9415_d0_b0), List(x9816)).name("x9820").ctrl(x9824).srcCtx("lenet_loops.scala:168:77") // SRAMLoad(x9415,ArrayBuffer(Const(512)),List(x9816),Const(0),x9819)
    val x9821 = OpDef(op=FixAdd, inputs=List(x9817, x9820)).name("x9821").ctrl(x9824).srcCtx("lenet_loops.scala:168:68") // FixAdd(x9817,x9820)
    val x9822 = OpDef(op=FixMax, inputs=List(Const(0.0), x9821)).name("x9822").ctrl(x9824).srcCtx("lenet_loops.scala:168:47") // Max(Const(0),x9821)
    val x9823 = StoreBanks(List(x9758_d0_b0), List(x9816), x9822).name("x9823").ctrl(x9824).srcCtx("lenet_loops.scala:168:42") // SRAMStore(x9758,ArrayBuffer(Const(500)),List(x9816),Const(0),x9822,x9819)
    val x9827_d0_b0 = SRAM(size=32, banking=Strided(banks=1, stride=1)).name("x9827_d0_b0").ctrl(x9917).srcCtx("lenet_loops.scala:175:32:tmp5_SRAM") // x9827 = SRAMNew(ArrayBuffer(Const(32)))
    isAccum(x9827_d0_b0) = false
    bufferDepthOf(x9827_d0_b0) = 2
    val x9828 = Counter(min=Const(0), max=Const(10), step=Const(1), par=1).name("x9828").ctrl(x9917).srcCtx("lenet_loops.scala:176:25") // CounterNew(Const(0),Const(10),Const(1),Const(1))
    val x9829 = CounterChain(List(x9828)).name("x9829").ctrl(x9917).srcCtx("lenet_loops.scala:176:38") // CounterChainNew(List(x9828))
    val x9884 = LoopController(style=MetaPipe, level=OuterControl, cchain=x9829).name("x9884").ctrl(x9917).srcCtx("lenet_loops.scala:176:38") // UnrolledForeach(List(b5677),x9829,Block(Const(())),List(List(b6127)),List(List(b6128)))
    val b6127 = CounterIter(x9828, Some(0)).name("b6127").ctrl(x9884) // b6127
    val b6128 = Const(true).name("b6128").ctrl(x9884) // b6128
    val x9830_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=1)).name("x9830_d0_b0").ctrl(x9884).srcCtx("lenet_loops.scala:177:36:c6_row_SRAM") // x9830 = SRAMNew(ArrayBuffer(Const(512)))
    isAccum(x9830_d0_b0) = false
    bufferDepthOf(x9830_d0_b0) = 2
    val x9832 = UnitController(style=SeqPipe, level=InnerControl).name("x9832").ctrl(x9884).srcCtx("lenet_loops.scala:176:38") // UnitPipe(List(b6128, b5677),Block(Const(())))
    val x9831 = OpDef(op=FixAdd, inputs=List(b6127, Const(1))).name("x9831").ctrl(x9832).srcCtx("lenet_loops.scala:178:35") // FixAdd(b6127,Const(1))
    val x9833 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x9833").ctrl(x9884).srcCtx("lenet_loops.scala:178:23") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x9834 = CounterChain(List(x9833)).name("x9834").ctrl(x9884).srcCtx("lenet_loops.scala:178:23") // CounterChainNew(List(x9833))
    val x9861 = LoopController(style=StreamPipe, level=OuterControl, cchain=x9834).name("x9861").ctrl(x9884).srcCtx("lenet_loops.scala:178:23") // UnrolledForeach(List(b6128, b5677),x9834,Block(Const(())),List(List(b6134)),List(List(b6135)))
    val b6134 = CounterIter(x9833, Some(0)).name("b6134").ctrl(x9861) // b6134
    val b6135 = Const(true).name("b6135").ctrl(x9861) // b6135
    val b9988 = StreamOut(field="offset").name("b9988").ctrl(x9861).srcCtx("lenet_loops.scala:178:23") // x9835 = StreamOutNew(BurstCmdBus)
    isAccum(b9988) = false
    bufferDepthOf(b9988) = 1
    val b9989 = StreamOut(field="size").name("b9989").ctrl(x9861).srcCtx("lenet_loops.scala:178:23") // x9835 = StreamOutNew(BurstCmdBus)
    isAccum(b9989) = false
    bufferDepthOf(b9989) = 1
    val x9836 = StreamIn(field="data").name("x9836").ctrl(x9861).srcCtx("lenet_loops.scala:178:23") // x9836 = StreamInNew(BurstDataBus())
    isAccum(x9836) = false
    bufferDepthOf(x9836) = 1
    val x9850 = UnitController(style=SeqPipe, level=InnerControl).name("x9850").ctrl(x9861).srcCtx("lenet_loops.scala:178:23") // UnitPipe(List(b6135, b6128, b5677),Block(x9849))
    val x9837 = OpDef(op=FixAdd, inputs=List(b6127, b6134)).name("x9837").ctrl(x9850).srcCtx("lenet_loops.scala:178:23") // FixAdd(b6127,b6134)
    val x9838 = x9837 // FixConvert(x9837,TRUE,_32,_0) (Same Type. No op)
    val x9839 = OpDef(op=FixSla, inputs=List(x9838, Const(9))).name("x9839").ctrl(x9850).srcCtx("lenet_loops.scala:178:23") // FixLsh(x9838,Const(9))
    val x9840 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x9841 = OpDef(op=FixAdd, inputs=List(x9839, x9840)).name("x9841").ctrl(x9850).srcCtx("lenet_loops.scala:178:23") // FixAdd(x9839,x9840)
    val x9842 = OpDef(op=FixSla, inputs=List(x9841, Const(1))).name("x9842").ctrl(x9850).srcCtx("lenet_loops.scala:178:23") // FixLsh(x9841,Const(1))
    val x9843 = x9842 // FixConvert(x9842,TRUE,_64,_0)
    val x9844 = DramAddress(x9255).name("x9844").ctrl(x9850).srcCtx("lenet_loops.scala:178:23") // GetDRAMAddress(x9255)
    val x9846_x9845 = OpDef(op=FixAdd, inputs=List(x9843, x9844)).name("x9846_x9845").ctrl(x9850).srcCtx("lenet_loops.scala:178:23") // FixAdd(x9843,x9844)
    // x9846 = SimpleStruct(ArrayBuffer((offset,x9845), (size,Const(1024)), (isLoad,Const(true))))
    val x9847 = OpDef(op=BitAnd, inputs=List(b6135, b6128)).name("x9847").ctrl(x9850).srcCtx("UnrollingBase.scala:28:66") // And(b6135,b6128)
    val x9848 = OpDef(op=BitAnd, inputs=List(x9847, b5677)).name("x9848").ctrl(x9850).srcCtx("UnrollingBase.scala:28:66") // And(x9847,b5677)
    val x9849_b9990_b9988 = WriteMem(b9988, x9846_x9845).name("x9849_b9990_b9988").ctrl(x9850).srcCtx("lenet_loops.scala:178:23") // StreamWrite(x9835,x9846,x9848)
    val x9849_b9991_b9989 = WriteMem(b9989, Const(1024)).name("x9849_b9991_b9989").ctrl(x9850).srcCtx("lenet_loops.scala:178:23") // StreamWrite(x9835,x9846,x9848)
    val x9851 = FringeDenseLoad(dram=List(x9255), cmdStream=List(b9988, b9989), dataStream=List(x9836)).name("x9851").ctrl(x9861).srcCtx("lenet_loops.scala:178:23") // FringeDenseLoad(x9255,x9835,x9836)
    val x9852 = Counter(min=Const(0), max=Const(512), step=Const(1), par=1).name("x9852").ctrl(x9861).srcCtx("lenet_loops.scala:178:23") // CounterNew(Const(0),Const(512),Const(1),Const(1))
    val x9853 = CounterChain(List(x9852)).name("x9853").ctrl(x9861).srcCtx("lenet_loops.scala:178:23") // CounterChainNew(List(x9852))
    val x9860 = LoopController(style=InnerPipe, level=InnerControl, cchain=x9853).name("x9860").ctrl(x9861).srcCtx("lenet_loops.scala:178:23") // UnrolledForeach(List(b6135, b6128, b5677),x9853,Block(Const(())),List(List(b6155)),List(List(b6156)))
    val b6155 = CounterIter(x9852, None).name("b6155").ctrl(x9860) // b6155
    val b6156 = Const(true).name("b6156").ctrl(x9860) // b6156
    val x9854 = OpDef(op=BitAnd, inputs=List(b6156, b6135)).name("x9854").ctrl(x9860).srcCtx("UnrollingBase.scala:28:66") // And(b6156,b6135)
    val x9855 = OpDef(op=BitAnd, inputs=List(b6128, b5677)).name("x9855").ctrl(x9860).srcCtx("UnrollingBase.scala:28:66") // And(b6128,b5677)
    val x9856 = OpDef(op=BitAnd, inputs=List(x9854, x9855)).name("x9856").ctrl(x9860).srcCtx("UnrollingBase.scala:28:66") // And(x9854,x9855)
    val x9857_x9857 = ReadMem(x9836).name("x9857_x9857").ctrl(x9860).srcCtx("lenet_loops.scala:178:23") // ParStreamRead(x9836,List(x9856))
    val x9858_x9858 = x9857_x9857 // x9858 = VectorApply(x9857,0)
    val x9859 = StoreBanks(List(x9830_d0_b0), List(b6155), x9858_x9858).name("x9859").ctrl(x9860).srcCtx("lenet_loops.scala:178:23") // ParSRAMStore(x9830,List(List(b6155)),List(x9858),List(x9856))
    val x9862_d0 = Reg(init=Some(0.0)).name("x9862_d0").ctrl(x9884).srcCtx("lenet_loops.scala:179:35:prod") // x9862 = RegNew(Const(0))
    isAccum(x9862_d0) = false
    bufferDepthOf(x9862_d0) = 2
    val x9862_d1 = Reg(init=Some(0.0)).name("x9862_d1").ctrl(x9884).srcCtx("lenet_loops.scala:179:35:prod") // x9862 = RegNew(Const(0))
    isAccum(x9862_d1) = true
    bufferDepthOf(x9862_d1) = 1
    val x9863 = Counter(min=Const(0), max=Const(500), step=Const(1), par=1).name("x9863").ctrl(x9884).srcCtx("lenet_loops.scala:179:55") // CounterNew(Const(0),Const(500),Const(1),Const(1))
    val x9864 = CounterChain(List(x9863)).name("x9864").ctrl(x9884).srcCtx("lenet_loops.scala:179:108") // CounterChainNew(List(x9863))
    val x9877 = LoopController(style=InnerPipe, level=InnerControl, cchain=x9864).name("x9877").ctrl(x9884).srcCtx("lenet_loops.scala:179:108") // UnrolledReduce(List(b6128, b5677),x9864,x9862,Block((x9862) => Const(())),List(List(b6168)),List(List(b6169)))
    val b6168 = CounterIter(x9863, None).name("b6168").ctrl(x9877) // b6168
    val b6169 = Const(true).name("b6169").ctrl(x9877) // b6169
    val x9865 = OpDef(op=BitAnd, inputs=List(b6169, b6128)).name("x9865").ctrl(x9877).srcCtx("UnrollingBase.scala:28:66") // And(b6169,b6128)
    val x9866 = OpDef(op=BitAnd, inputs=List(x9865, b5677)).name("x9866").ctrl(x9877).srcCtx("UnrollingBase.scala:28:66") // And(x9865,b5677)
    val x9867 = LoadBanks(List(x9758_d0_b0), List(b6168)).name("x9867").ctrl(x9877).srcCtx("lenet_loops.scala:179:80") // ParSRAMLoad(x9758,List(List(b6168)),List(x9866))
    val x9868 = x9867 // x9868 = VectorApply(x9867,0)
    val x9869 = LoadBanks(List(x9830_d0_b0), List(b6168)).name("x9869").ctrl(x9877).srcCtx("lenet_loops.scala:179:100") // ParSRAMLoad(x9830,List(List(b6168)),List(x9866))
    val x9870 = x9869 // x9870 = VectorApply(x9869,0)
    val x9871 = OpDef(op=FixMul, inputs=List(x9868, x9870)).name("x9871").ctrl(x9877).srcCtx("lenet_loops.scala:179:87") // FixMul(x9868,x9870)
    val x9872 = ReadMem(x9862_d1).name("x9872").ctrl(x9877).srcCtx("lenet_loops.scala:179:108") // RegRead(x9862)
    val x9873 = OpDef(op=FixEql, inputs=List(b6168, Const(0))).name("x9873").ctrl(x9877).srcCtx("lenet_loops.scala:179:108") // FixEql(b6168,Const(0))
    val x9874 = ReduceAccumOp(op=FixAdd, input=x9871, accum=x9872).name("x9874").ctrl(x9877).srcCtx("lenet_loops.scala:179:110") // FixAdd(x9871,x9872)
    val x9875 = OpDef(op=BitAnd, inputs=List(b6128, b5677)).name("x9875").ctrl(x9877).srcCtx("UnrollingBase.scala:28:66") // And(b6128,b5677)
    val x9876_x9862_d0 = WriteMem(x9862_d0, x9874).name("x9876_x9862_d0").ctrl(x9877).srcCtx("lenet_loops.scala:179:108") // RegWrite(x9862,x9874,x9875)
    val x9876_x9862_d1 = WriteMem(x9862_d1, x9874).name("x9876_x9862_d1").ctrl(x9877).srcCtx("lenet_loops.scala:179:108") // RegWrite(x9862,x9874,x9875)
    val x9883 = UnitController(style=SeqPipe, level=InnerControl).name("x9883").ctrl(x9884).srcCtx("lenet_loops.scala:176:38") // UnitPipe(List(b6128, b5677),Block(Const(())))
    val x9878 = OpDef(op=BitAnd, inputs=List(b6128, b5677)).name("x9878").ctrl(x9883).srcCtx("UnrollingBase.scala:28:66") // And(b6128,b5677)
    val x9879 = LoadBanks(List(x9434_d0_b0), List(b6127)).name("x9879").ctrl(x9883).srcCtx("lenet_loops.scala:180:50") // SRAMLoad(x9434,ArrayBuffer(Const(32)),List(b6127),Const(0),x9878)
    val x9880 = ReadMem(x9862_d0).name("x9880").ctrl(x9883).srcCtx("lenet_loops.scala:180:35") // RegRead(x9862)
    val x9881 = OpDef(op=FixAdd, inputs=List(x9880, x9879)).name("x9881").ctrl(x9883).srcCtx("lenet_loops.scala:180:41") // FixAdd(x9880,x9879)
    val x9882 = StoreBanks(List(x9827_d0_b0), List(b6127), x9881).name("x9882").ctrl(x9883).srcCtx("lenet_loops.scala:180:28") // SRAMStore(x9827,ArrayBuffer(Const(32)),List(b6127),Const(0),x9881,x9878)
    val x9885 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x9885").ctrl(x9917).srcCtx("lenet_loops.scala:185:37") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x9886 = CounterChain(List(x9885)).name("x9886").ctrl(x9917).srcCtx("lenet_loops.scala:185:37") // CounterChainNew(List(x9885))
    val x9916 = LoopController(style=StreamPipe, level=OuterControl, cchain=x9886).name("x9916").ctrl(x9917).srcCtx("lenet_loops.scala:185:37") // UnrolledForeach(List(b5677),x9886,Block(Const(())),List(List(b6192)),List(List(b6193)))
    val b6192 = CounterIter(x9885, Some(0)).name("b6192").ctrl(x9916) // b6192
    val b6193 = Const(true).name("b6193").ctrl(x9916) // b6193
    val b9992 = StreamOut(field="offset").name("b9992").ctrl(x9916).srcCtx("lenet_loops.scala:185:37") // x9887 = StreamOutNew(BurstCmdBus)
    isAccum(b9992) = false
    bufferDepthOf(b9992) = 1
    val b9993 = StreamOut(field="size").name("b9993").ctrl(x9916).srcCtx("lenet_loops.scala:185:37") // x9887 = StreamOutNew(BurstCmdBus)
    isAccum(b9993) = false
    bufferDepthOf(b9993) = 1
    val x9888 = StreamOut(field="data").name("x9888").ctrl(x9916).srcCtx("lenet_loops.scala:185:37") // x9888 = StreamOutNew(BurstFullDataBus())
    isAccum(x9888) = false
    bufferDepthOf(x9888) = 1
    val x9889 = StreamIn(field="ack").name("x9889").ctrl(x9916).srcCtx("lenet_loops.scala:185:37") // x9889 = StreamInNew(BurstAckBus)
    isAccum(x9889) = false
    bufferDepthOf(x9889) = 1
    val x9902 = UnitController(style=SeqPipe, level=InnerControl).name("x9902").ctrl(x9916).srcCtx("lenet_loops.scala:185:37") // UnitPipe(List(b6193, b5677),Block(x9901))
    val x9890 = OpDef(op=FixAdd, inputs=List(b5676, b6192)).name("x9890").ctrl(x9902).srcCtx("lenet_loops.scala:185:37") // FixAdd(b5676,b6192)
    val x9891 = x9890 // FixConvert(x9890,TRUE,_32,_0) (Same Type. No op)
    val x9892 = OpDef(op=FixSla, inputs=List(x9891, Const(5))).name("x9892").ctrl(x9902).srcCtx("lenet_loops.scala:185:37") // FixLsh(x9891,Const(5))
    val x9893 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x9894 = OpDef(op=FixAdd, inputs=List(x9892, x9893)).name("x9894").ctrl(x9902).srcCtx("lenet_loops.scala:185:37") // FixAdd(x9892,x9893)
    val x9895 = OpDef(op=FixSla, inputs=List(x9894, Const(1))).name("x9895").ctrl(x9902).srcCtx("lenet_loops.scala:185:37") // FixLsh(x9894,Const(1))
    val x9896 = x9895 // FixConvert(x9895,TRUE,_64,_0)
    val x9897 = DramAddress(x9257).name("x9897").ctrl(x9902).srcCtx("lenet_loops.scala:185:37") // GetDRAMAddress(x9257)
    val x9899_x9898 = OpDef(op=FixAdd, inputs=List(x9896, x9897)).name("x9899_x9898").ctrl(x9902).srcCtx("lenet_loops.scala:185:37") // FixAdd(x9896,x9897)
    // x9899 = SimpleStruct(ArrayBuffer((offset,x9898), (size,Const(64)), (isLoad,Const(false))))
    val x9900 = OpDef(op=BitAnd, inputs=List(b6193, b5677)).name("x9900").ctrl(x9902).srcCtx("UnrollingBase.scala:28:66") // And(b6193,b5677)
    val x9901_b9994_b9992 = WriteMem(b9992, x9899_x9898).name("x9901_b9994_b9992").ctrl(x9902).srcCtx("lenet_loops.scala:185:37") // StreamWrite(x9887,x9899,x9900)
    val x9901_b9995_b9993 = WriteMem(b9993, Const(64)).name("x9901_b9995_b9993").ctrl(x9902).srcCtx("lenet_loops.scala:185:37") // StreamWrite(x9887,x9899,x9900)
    val x9903 = Counter(min=Const(0), max=Const(32), step=Const(1), par=1).name("x9903").ctrl(x9916).srcCtx("lenet_loops.scala:185:37") // CounterNew(Const(0),Const(32),Const(1),Const(1))
    val x9904 = CounterChain(List(x9903)).name("x9904").ctrl(x9916).srcCtx("lenet_loops.scala:185:37") // CounterChainNew(List(x9903))
    val x9911 = LoopController(style=InnerPipe, level=InnerControl, cchain=x9904).name("x9911").ctrl(x9916).srcCtx("lenet_loops.scala:185:37") // UnrolledForeach(List(b6193, b5677),x9904,Block(Const(())),List(List(b6212)),List(List(b6213)))
    val b6212 = CounterIter(x9903, None).name("b6212").ctrl(x9911) // b6212
    val b6213 = Const(true).name("b6213").ctrl(x9911) // b6213
    val x9905 = OpDef(op=BitAnd, inputs=List(b6213, b6193)).name("x9905").ctrl(x9911).srcCtx("UnrollingBase.scala:28:66") // And(b6213,b6193)
    val x9906 = OpDef(op=BitAnd, inputs=List(x9905, b5677)).name("x9906").ctrl(x9911).srcCtx("UnrollingBase.scala:28:66") // And(x9905,b5677)
    val x9907 = LoadBanks(List(x9827_d0_b0), List(b6212)).name("x9907").ctrl(x9911).srcCtx("lenet_loops.scala:185:37") // ParSRAMLoad(x9827,List(List(b6212)),List(x9906))
    val x9909_x9908 = x9907 // x9908 = VectorApply(x9907,0)
    // x9909 = SimpleStruct(ArrayBuffer((_1,x9908), (_2,Const(true))))
    val x9910_x9910_x9888 = WriteMem(x9888, x9909_x9908).name("x9910_x9910_x9888").ctrl(x9911).srcCtx("lenet_loops.scala:185:37") // ParStreamWrite(x9888,List(x9909),List(x9906))
    val x9912 = FringeDenseStore(dram=List(x9257), cmdStream=List(b9992, b9993), dataStream=List(x9888), ackStream=List(x9889)).name("x9912").ctrl(x9916).srcCtx("lenet_loops.scala:185:37") // FringeDenseStore(x9257,x9887,x9888,x9889)
    val x9915 = UnitController(style=SeqPipe, level=InnerControl).name("x9915").ctrl(x9916).srcCtx("lenet_loops.scala:185:37") // UnitPipe(List(b6193, b5677),Block(Const(())))
    def split1 = {
    val x9913 = OpDef(op=BitAnd, inputs=List(b6193, b5677)).name("x9913").ctrl(x9915).srcCtx("UnrollingBase.scala:28:66") // And(b6193,b5677)
    val x9914_x9914 = ReadMem(x9889).name("x9914_x9914").ctrl(x9915).srcCtx("lenet_loops.scala:185:37") // StreamRead(x9889,x9913)
    }; split1
    
  }
}
