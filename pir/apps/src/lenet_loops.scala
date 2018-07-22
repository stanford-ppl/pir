import pir._
import pir.node._
import arch._
import prism.enums._

object lenet_loops extends PIRApp {
  def main(implicit design:PIRDesign) = {
    import design.pirmeta._
    val x9557 = withCtrl(design.top.topController) { DRAM(dims=List(Const(20), Const(5), Const(16))).name("x9557").srcCtx("lenet_loops.scala:30:26:c0_DRAM") } // x9557 = DRAMNew(ArrayBuffer(Const(20), Const(5), Const(16)),Const(0))
    val x9558 = withCtrl(design.top.topController) { DRAM(dims=List(Const(3), Const(28), Const(32))).name("x9558").srcCtx("lenet_loops.scala:31:26:i0_DRAM") } // x9558 = DRAMNew(ArrayBuffer(Const(3), Const(28), Const(32)),Const(0))
    val x9559 = withCtrl(design.top.topController) { DRAM(dims=List(Const(32))).name("x9559").srcCtx("lenet_loops.scala:32:26:c1_DRAM") } // x9559 = DRAMNew(ArrayBuffer(Const(32)),Const(0))
    val x9560 = withCtrl(design.top.topController) { DRAM(dims=List(Const(50), Const(5), Const(16))).name("x9560").srcCtx("lenet_loops.scala:33:26:c2_DRAM") } // x9560 = DRAMNew(ArrayBuffer(Const(50), Const(5), Const(16)),Const(0))
    val x9561 = withCtrl(design.top.topController) { DRAM(dims=List(Const(64))).name("x9561").srcCtx("lenet_loops.scala:34:26:c3_DRAM") } // x9561 = DRAMNew(ArrayBuffer(Const(64)),Const(0))
    val x9562 = withCtrl(design.top.topController) { DRAM(dims=List(Const(100), Const(4), Const(4), Const(4), Const(64))).name("x9562").srcCtx("lenet_loops.scala:35:26:c4_DRAM") } // x9562 = DRAMNew(ArrayBuffer(Const(100), Const(4), Const(4), Const(4), Const(64)),Const(0))
    val x9563 = withCtrl(design.top.topController) { DRAM(dims=List(Const(100), Const(5))).name("x9563").srcCtx("lenet_loops.scala:37:26:c5_DRAM") } // x9563 = DRAMNew(ArrayBuffer(Const(100), Const(5)),Const(0))
    val x9564 = withCtrl(design.top.topController) { DRAM(dims=List(Const(10), Const(100), Const(5))).name("x9564").srcCtx("lenet_loops.scala:38:26:c6_DRAM") } // x9564 = DRAMNew(ArrayBuffer(Const(10), Const(100), Const(5)),Const(0))
    val x9565 = withCtrl(design.top.topController) { DRAM(dims=List(Const(32))).name("x9565").srcCtx("lenet_loops.scala:40:26:c7_DRAM") } // x9565 = DRAMNew(ArrayBuffer(Const(32)),Const(0))
    val x9566 = withCtrl(design.top.topController) { DRAM(dims=List(Const(3), Const(32))).name("x9566").srcCtx("lenet_loops.scala:41:28:tmp5_DRAM") } // x9566 = DRAMNew(ArrayBuffer(Const(3), Const(32)),Const(0))
    val x10327 = withCtrl(design.top.topController) { UnitController(style=SeqPipe, level=OuterControl).name("x10327").srcCtx("lenet_loops.scala:69:11") } // Hwblock(Block(Const(())),false)
    val x9676_d0_b0 = withCtrl(x10327) { SRAM(size=32, banking=Strided(banks=16, stride=1)).name("x9676_d0_b0").srcCtx("lenet_loops.scala:71:28:c1_SRAM") } // x9676 = SRAMNew(ArrayBuffer(Const(32)))
    isAccum(x9676_d0_b0) = false
    bufferDepthOf(x9676_d0_b0) = 1
    staticDimsOf(x9676_d0_b0) = List(32)
    val x9694 = withCtrl(x10327) { UnitController(style=StreamPipe, level=OuterControl).name("x9694").srcCtx("lenet_loops.scala:72:15") } // UnitPipe(List(Const(true)),Block(Const(())))
    val b10365 = withCtrl(x9694) { StreamOut(field="offset").name("b10365").srcCtx("lenet_loops.scala:72:15") } // x9677 = StreamOutNew(BurstCmdBus)
    isAccum(b10365) = false
    bufferDepthOf(b10365) = 1
    val b10366 = withCtrl(x9694) { StreamOut(field="size").name("b10366").srcCtx("lenet_loops.scala:72:15") } // x9677 = StreamOutNew(BurstCmdBus)
    isAccum(b10366) = false
    bufferDepthOf(b10366) = 1
    val x9678 = withCtrl(x9694) { StreamIn(field="data").name("x9678").srcCtx("lenet_loops.scala:72:15") } // x9678 = StreamInNew(BurstDataBus())
    isAccum(x9678) = false
    bufferDepthOf(x9678) = 1
    val x9686 = withCtrl(x9694) { UnitController(style=SeqPipe, level=InnerControl).name("x9686").srcCtx("lenet_loops.scala:72:15") } // UnitPipe(List(Const(true)),Block(x9685))
    val x9679 = withCtrl(x9686) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x9680 = withCtrl(x9686) { OpDef(op=FixSla, inputs=List(x9679, Const(2))).name("x9680").srcCtx("lenet_loops.scala:72:15") } // FixLsh(x9679,Const(2))
    val x9681 = withCtrl(x9686) { x9680 } // FixConvert(x9680,TRUE,_64,_0)
    val x9682 = withCtrl(x9686) { DramAddress(x9559).name("x9682").srcCtx("lenet_loops.scala:72:15") } // GetDRAMAddress(x9559)
    val x9684_x9683 = withCtrl(x9686) { OpDef(op=FixAdd, inputs=List(x9681, x9682)).name("x9684_x9683").srcCtx("lenet_loops.scala:72:15") } // FixAdd(x9681,x9682)
    // x9684 = SimpleStruct(ArrayBuffer((offset,x9683), (size,Const(128)), (isLoad,Const(true))))
    val x9685_b10367_b10365 = withCtrl(x9686) { WriteMem(b10365, x9684_x9683).name("x9685_b10367_b10365").srcCtx("lenet_loops.scala:72:15") } // StreamWrite(x9677,x9684,Const(true))
    val x9685_b10368_b10366 = withCtrl(x9686) { WriteMem(b10366, Const(128)).name("x9685_b10368_b10366").srcCtx("lenet_loops.scala:72:15") } // StreamWrite(x9677,x9684,Const(true))
    val x9687 = withCtrl(x9694) { FringeDenseLoad(dram=List(x9559), cmdStream=List(b10365, b10366), dataStream=List(x9678)).name("x9687").srcCtx("lenet_loops.scala:72:15") } // FringeDenseLoad(x9559,x9677,x9678)
    val x9688 = withCtrl(x9694) { Counter(min=Const(0), max=Const(32), step=Const(1), par=16).name("x9688").srcCtx("lenet_loops.scala:72:15") } // CounterNew(Const(0),Const(32),Const(1),Const(16))
    val x9689 = withCtrl(x9694) { CounterChain(List(x9688)).name("x9689").srcCtx("lenet_loops.scala:72:15") } // CounterChainNew(List(x9688))
    val x9693 = withCtrl(x9694) { LoopController(style=InnerPipe, level=InnerControl, cchain=x9689).name("x9693").srcCtx("lenet_loops.scala:72:15") } // UnrolledForeach(List(Const(true)),x9689,Block(Const(())),List(List(b5363)),List(List(b5364)))
    val b5363 = withCtrl(x9693) { CounterIter(x9688, None).name("b5363") } // b5363
    val b5364 = withCtrl(x9693) { Const(true).name("b5364") } // b5364
    val x9690_x9690 = withCtrl(x9693) { ReadMem(x9678).name("x9690_x9690").srcCtx("lenet_loops.scala:72:15") } // ParStreamRead(x9678,List(b5364))
    val x9691_x9691 = withCtrl(x9693) { x9690_x9690 } // VectorApply(x9690,0)
    val x9692 = withCtrl(x9693) { StoreBanks(List(List(x9676_d0_b0)), List(b5363), x9691_x9691).name("x9692").srcCtx("lenet_loops.scala:72:15") } // ParSRAMStore(x9676,List(List(b5363)),List(x9691),List(b5364))
    val x9695_d0_b0 = withCtrl(x10327) { SRAM(size=64, banking=Strided(banks=16, stride=1)).name("x9695_d0_b0").srcCtx("lenet_loops.scala:74:28:c3_SRAM") } // x9695 = SRAMNew(ArrayBuffer(Const(64)))
    isAccum(x9695_d0_b0) = false
    bufferDepthOf(x9695_d0_b0) = 1
    staticDimsOf(x9695_d0_b0) = List(64)
    val x9713 = withCtrl(x10327) { UnitController(style=StreamPipe, level=OuterControl).name("x9713").srcCtx("lenet_loops.scala:75:15") } // UnitPipe(List(Const(true)),Block(Const(())))
    val b10369 = withCtrl(x9713) { StreamOut(field="offset").name("b10369").srcCtx("lenet_loops.scala:75:15") } // x9696 = StreamOutNew(BurstCmdBus)
    isAccum(b10369) = false
    bufferDepthOf(b10369) = 1
    val b10370 = withCtrl(x9713) { StreamOut(field="size").name("b10370").srcCtx("lenet_loops.scala:75:15") } // x9696 = StreamOutNew(BurstCmdBus)
    isAccum(b10370) = false
    bufferDepthOf(b10370) = 1
    val x9697 = withCtrl(x9713) { StreamIn(field="data").name("x9697").srcCtx("lenet_loops.scala:75:15") } // x9697 = StreamInNew(BurstDataBus())
    isAccum(x9697) = false
    bufferDepthOf(x9697) = 1
    val x9705 = withCtrl(x9713) { UnitController(style=SeqPipe, level=InnerControl).name("x9705").srcCtx("lenet_loops.scala:75:15") } // UnitPipe(List(Const(true)),Block(x9704))
    val x9698 = withCtrl(x9705) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x9699 = withCtrl(x9705) { OpDef(op=FixSla, inputs=List(x9698, Const(2))).name("x9699").srcCtx("lenet_loops.scala:75:15") } // FixLsh(x9698,Const(2))
    val x9700 = withCtrl(x9705) { x9699 } // FixConvert(x9699,TRUE,_64,_0)
    val x9701 = withCtrl(x9705) { DramAddress(x9561).name("x9701").srcCtx("lenet_loops.scala:75:15") } // GetDRAMAddress(x9561)
    val x9703_x9702 = withCtrl(x9705) { OpDef(op=FixAdd, inputs=List(x9700, x9701)).name("x9703_x9702").srcCtx("lenet_loops.scala:75:15") } // FixAdd(x9700,x9701)
    // x9703 = SimpleStruct(ArrayBuffer((offset,x9702), (size,Const(256)), (isLoad,Const(true))))
    val x9704_b10371_b10369 = withCtrl(x9705) { WriteMem(b10369, x9703_x9702).name("x9704_b10371_b10369").srcCtx("lenet_loops.scala:75:15") } // StreamWrite(x9696,x9703,Const(true))
    val x9704_b10372_b10370 = withCtrl(x9705) { WriteMem(b10370, Const(256)).name("x9704_b10372_b10370").srcCtx("lenet_loops.scala:75:15") } // StreamWrite(x9696,x9703,Const(true))
    val x9706 = withCtrl(x9713) { FringeDenseLoad(dram=List(x9561), cmdStream=List(b10369, b10370), dataStream=List(x9697)).name("x9706").srcCtx("lenet_loops.scala:75:15") } // FringeDenseLoad(x9561,x9696,x9697)
    val x9707 = withCtrl(x9713) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x9707").srcCtx("lenet_loops.scala:75:15") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x9708 = withCtrl(x9713) { CounterChain(List(x9707)).name("x9708").srcCtx("lenet_loops.scala:75:15") } // CounterChainNew(List(x9707))
    val x9712 = withCtrl(x9713) { LoopController(style=InnerPipe, level=InnerControl, cchain=x9708).name("x9712").srcCtx("lenet_loops.scala:75:15") } // UnrolledForeach(List(Const(true)),x9708,Block(Const(())),List(List(b5384)),List(List(b5385)))
    val b5384 = withCtrl(x9712) { CounterIter(x9707, None).name("b5384") } // b5384
    val b5385 = withCtrl(x9712) { Const(true).name("b5385") } // b5385
    val x9709_x9709 = withCtrl(x9712) { ReadMem(x9697).name("x9709_x9709").srcCtx("lenet_loops.scala:75:15") } // ParStreamRead(x9697,List(b5385))
    val x9710_x9710 = withCtrl(x9712) { x9709_x9709 } // VectorApply(x9709,0)
    val x9711 = withCtrl(x9712) { StoreBanks(List(List(x9695_d0_b0)), List(b5384), x9710_x9710).name("x9711").srcCtx("lenet_loops.scala:75:15") } // ParSRAMStore(x9695,List(List(b5384)),List(x9710),List(b5385))
    val x9714_d0_b0 = withCtrl(x10327) { SRAM(size=640, banking=Strided(banks=16, stride=1)).name("x9714_d0_b0").srcCtx("lenet_loops.scala:77:28:c5_SRAM") } // x9714 = SRAMNew(ArrayBuffer(Const(5), Const(128)))
    isAccum(x9714_d0_b0) = false
    bufferDepthOf(x9714_d0_b0) = 1
    staticDimsOf(x9714_d0_b0) = List(5, 128)
    val x9715 = withCtrl(x10327) { Counter(min=Const(0), max=Const(5), step=Const(1), par=1).name("x9715").srcCtx("lenet_loops.scala:78:15") } // CounterNew(Const(0),Const(5),Const(1),Const(1))
    val x9716 = withCtrl(x10327) { CounterChain(List(x9715)).name("x9716").srcCtx("lenet_loops.scala:78:15") } // CounterChainNew(List(x9715))
    val x9738 = withCtrl(x10327) { LoopController(style=StreamPipe, level=OuterControl, cchain=x9716).name("x9738").srcCtx("lenet_loops.scala:78:15") } // UnrolledForeach(List(Const(true)),x9716,Block(Const(())),List(List(b5394)),List(List(b5395)))
    val b5394 = withCtrl(x9738) { CounterIter(x9715, Some(0)).name("b5394") } // b5394
    val b5395 = withCtrl(x9738) { Const(true).name("b5395") } // b5395
    val b10373 = withCtrl(x9738) { StreamOut(field="offset").name("b10373").srcCtx("lenet_loops.scala:78:15") } // x9717 = StreamOutNew(BurstCmdBus)
    isAccum(b10373) = false
    bufferDepthOf(b10373) = 1
    val b10374 = withCtrl(x9738) { StreamOut(field="size").name("b10374").srcCtx("lenet_loops.scala:78:15") } // x9717 = StreamOutNew(BurstCmdBus)
    isAccum(b10374) = false
    bufferDepthOf(b10374) = 1
    val x9718 = withCtrl(x9738) { StreamIn(field="data").name("x9718").srcCtx("lenet_loops.scala:78:15") } // x9718 = StreamInNew(BurstDataBus())
    isAccum(x9718) = false
    bufferDepthOf(x9718) = 1
    val x9729 = withCtrl(x9738) { UnitController(style=SeqPipe, level=InnerControl).name("x9729").srcCtx("lenet_loops.scala:78:15") } // UnitPipe(List(b5395),Block(x9728))
    val x9719 = withCtrl(x9729) { b5394 } // FixConvert(b5394,TRUE,_32,_0) (Same Type. No op)
    val x9720 = withCtrl(x9729) { OpDef(op=FixMul, inputs=List(x9719, Const(5))).name("x9720").srcCtx("lenet_loops.scala:78:15") } // FixMul(x9719,Const(5))
    val x9721 = withCtrl(x9729) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x9722 = withCtrl(x9729) { OpDef(op=FixAdd, inputs=List(x9720, x9721)).name("x9722").srcCtx("lenet_loops.scala:78:15") } // FixAdd(x9720,x9721)
    val x9723 = withCtrl(x9729) { OpDef(op=FixSla, inputs=List(x9722, Const(2))).name("x9723").srcCtx("lenet_loops.scala:78:15") } // FixLsh(x9722,Const(2))
    val x9724 = withCtrl(x9729) { x9723 } // FixConvert(x9723,TRUE,_64,_0)
    val x9725 = withCtrl(x9729) { DramAddress(x9563).name("x9725").srcCtx("lenet_loops.scala:78:15") } // GetDRAMAddress(x9563)
    val x9727_x9726 = withCtrl(x9729) { OpDef(op=FixAdd, inputs=List(x9724, x9725)).name("x9727_x9726").srcCtx("lenet_loops.scala:78:15") } // FixAdd(x9724,x9725)
    // x9727 = SimpleStruct(ArrayBuffer((offset,x9726), (size,Const(512)), (isLoad,Const(true))))
    val x9728_b10375_b10373 = withCtrl(x9729) { WriteMem(b10373, x9727_x9726).name("x9728_b10375_b10373").srcCtx("lenet_loops.scala:78:15") } // StreamWrite(x9717,x9727,b5395)
    val x9728_b10376_b10374 = withCtrl(x9729) { WriteMem(b10374, Const(512)).name("x9728_b10376_b10374").srcCtx("lenet_loops.scala:78:15") } // StreamWrite(x9717,x9727,b5395)
    val x9730 = withCtrl(x9738) { FringeDenseLoad(dram=List(x9563), cmdStream=List(b10373, b10374), dataStream=List(x9718)).name("x9730").srcCtx("lenet_loops.scala:78:15") } // FringeDenseLoad(x9563,x9717,x9718)
    val x9731 = withCtrl(x9738) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x9731").srcCtx("lenet_loops.scala:78:15") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x9732 = withCtrl(x9738) { CounterChain(List(x9731)).name("x9732").srcCtx("lenet_loops.scala:78:15") } // CounterChainNew(List(x9731))
    val x9737 = withCtrl(x9738) { LoopController(style=InnerPipe, level=InnerControl, cchain=x9732).name("x9737").srcCtx("lenet_loops.scala:78:15") } // UnrolledForeach(List(b5395),x9732,Block(Const(())),List(List(b5412)),List(List(b5413)))
    val b5412 = withCtrl(x9737) { CounterIter(x9731, None).name("b5412") } // b5412
    val b5413 = withCtrl(x9737) { Const(true).name("b5413") } // b5413
    val x9733 = withCtrl(x9737) { OpDef(op=BitAnd, inputs=List(b5413, b5395)).name("x9733").srcCtx("UnrollingBase.scala:28:66") } // And(b5413,b5395)
    val x9734_x9734 = withCtrl(x9737) { ReadMem(x9718).name("x9734_x9734").srcCtx("lenet_loops.scala:78:15") } // ParStreamRead(x9718,List(x9733))
    val x9735_x9735 = withCtrl(x9737) { x9734_x9734 } // VectorApply(x9734,0)
    val x9736 = withCtrl(x9737) { StoreBanks(List(List(x9714_d0_b0)), List(b5394, b5412), x9735_x9735).name("x9736").srcCtx("lenet_loops.scala:78:15") } // ParSRAMStore(x9714,List(List(b5394, b5412)),List(x9735),List(x9733))
    val x9739_d0_b0 = withCtrl(x10327) { SRAM(size=32, banking=Strided(banks=16, stride=1)).name("x9739_d0_b0").srcCtx("lenet_loops.scala:80:28:c7_SRAM") } // x9739 = SRAMNew(ArrayBuffer(Const(32)))
    isAccum(x9739_d0_b0) = false
    bufferDepthOf(x9739_d0_b0) = 1
    staticDimsOf(x9739_d0_b0) = List(32)
    val x9757 = withCtrl(x10327) { UnitController(style=StreamPipe, level=OuterControl).name("x9757").srcCtx("lenet_loops.scala:81:15") } // UnitPipe(List(Const(true)),Block(Const(())))
    val b10377 = withCtrl(x9757) { StreamOut(field="offset").name("b10377").srcCtx("lenet_loops.scala:81:15") } // x9740 = StreamOutNew(BurstCmdBus)
    isAccum(b10377) = false
    bufferDepthOf(b10377) = 1
    val b10378 = withCtrl(x9757) { StreamOut(field="size").name("b10378").srcCtx("lenet_loops.scala:81:15") } // x9740 = StreamOutNew(BurstCmdBus)
    isAccum(b10378) = false
    bufferDepthOf(b10378) = 1
    val x9741 = withCtrl(x9757) { StreamIn(field="data").name("x9741").srcCtx("lenet_loops.scala:81:15") } // x9741 = StreamInNew(BurstDataBus())
    isAccum(x9741) = false
    bufferDepthOf(x9741) = 1
    val x9749 = withCtrl(x9757) { UnitController(style=SeqPipe, level=InnerControl).name("x9749").srcCtx("lenet_loops.scala:81:15") } // UnitPipe(List(Const(true)),Block(x9748))
    val x9742 = withCtrl(x9749) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x9743 = withCtrl(x9749) { OpDef(op=FixSla, inputs=List(x9742, Const(2))).name("x9743").srcCtx("lenet_loops.scala:81:15") } // FixLsh(x9742,Const(2))
    val x9744 = withCtrl(x9749) { x9743 } // FixConvert(x9743,TRUE,_64,_0)
    val x9745 = withCtrl(x9749) { DramAddress(x9565).name("x9745").srcCtx("lenet_loops.scala:81:15") } // GetDRAMAddress(x9565)
    val x9747_x9746 = withCtrl(x9749) { OpDef(op=FixAdd, inputs=List(x9744, x9745)).name("x9747_x9746").srcCtx("lenet_loops.scala:81:15") } // FixAdd(x9744,x9745)
    // x9747 = SimpleStruct(ArrayBuffer((offset,x9746), (size,Const(128)), (isLoad,Const(true))))
    val x9748_b10379_b10377 = withCtrl(x9749) { WriteMem(b10377, x9747_x9746).name("x9748_b10379_b10377").srcCtx("lenet_loops.scala:81:15") } // StreamWrite(x9740,x9747,Const(true))
    val x9748_b10380_b10378 = withCtrl(x9749) { WriteMem(b10378, Const(128)).name("x9748_b10380_b10378").srcCtx("lenet_loops.scala:81:15") } // StreamWrite(x9740,x9747,Const(true))
    val x9750 = withCtrl(x9757) { FringeDenseLoad(dram=List(x9565), cmdStream=List(b10377, b10378), dataStream=List(x9741)).name("x9750").srcCtx("lenet_loops.scala:81:15") } // FringeDenseLoad(x9565,x9740,x9741)
    val x9751 = withCtrl(x9757) { Counter(min=Const(0), max=Const(32), step=Const(1), par=16).name("x9751").srcCtx("lenet_loops.scala:81:15") } // CounterNew(Const(0),Const(32),Const(1),Const(16))
    val x9752 = withCtrl(x9757) { CounterChain(List(x9751)).name("x9752").srcCtx("lenet_loops.scala:81:15") } // CounterChainNew(List(x9751))
    val x9756 = withCtrl(x9757) { LoopController(style=InnerPipe, level=InnerControl, cchain=x9752).name("x9756").srcCtx("lenet_loops.scala:81:15") } // UnrolledForeach(List(Const(true)),x9752,Block(Const(())),List(List(b5434)),List(List(b5435)))
    val b5434 = withCtrl(x9756) { CounterIter(x9751, None).name("b5434") } // b5434
    val b5435 = withCtrl(x9756) { Const(true).name("b5435") } // b5435
    val x9753_x9753 = withCtrl(x9756) { ReadMem(x9741).name("x9753_x9753").srcCtx("lenet_loops.scala:81:15") } // ParStreamRead(x9741,List(b5435))
    val x9754_x9754 = withCtrl(x9756) { x9753_x9753 } // VectorApply(x9753,0)
    val x9755 = withCtrl(x9756) { StoreBanks(List(List(x9739_d0_b0)), List(b5434), x9754_x9754).name("x9755").srcCtx("lenet_loops.scala:81:15") } // ParSRAMStore(x9739,List(List(b5434)),List(x9754),List(b5435))
    val x9758 = withCtrl(x10327) { Counter(min=Const(0), max=Const(3), step=Const(1), par=1).name("x9758").srcCtx("lenet_loops.scala:83:31") } // CounterNew(Const(0),Const(3),Const(1),Const(1))
    val x9759 = withCtrl(x10327) { CounterChain(List(x9758)).name("x9759").srcCtx("lenet_loops.scala:83:46") } // CounterChainNew(List(x9758))
    val x10326 = withCtrl(x10327) { LoopController(style=MetaPipe, level=OuterControl, cchain=x9759).name("x10326").srcCtx("lenet_loops.scala:83:46") } // UnrolledForeach(List(Const(true)),x9759,Block(Const(())),List(List(b5443)),List(List(b5444)))
    val b5443 = withCtrl(x10326) { CounterIter(x9758, Some(0)).name("b5443") } // b5443
    val b5444 = withCtrl(x10326) { Const(true).name("b5444") } // b5444
    val x9760_d0_b0 = withCtrl(x10326) { SRAM(size=896, banking=Strided(banks=16, stride=1)).name("x9760_d0_b0").srcCtx("lenet_loops.scala:86:30:i0_SRAM") } // x9760 = SRAMNew(ArrayBuffer(Const(28), Const(32)))
    isAccum(x9760_d0_b0) = false
    bufferDepthOf(x9760_d0_b0) = 3
    staticDimsOf(x9760_d0_b0) = List(28, 32)
    val x9762 = withCtrl(x10326) { UnitController(style=SeqPipe, level=InnerControl).name("x9762").srcCtx("lenet_loops.scala:83:46") } // UnitPipe(List(b5444),Block(Const(())))
    val x9761 = withCtrl(x9762) { OpDef(op=FixAdd, inputs=List(b5443, Const(1))).name("x9761").srcCtx("lenet_loops.scala:87:29") } // FixAdd(b5443,Const(1))
    val x9763 = withCtrl(x10326) { Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x9763").srcCtx("lenet_loops.scala:87:17") } // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x9764 = withCtrl(x10326) { Counter(min=Const(0), max=Const(28), step=Const(1), par=1).name("x9764").srcCtx("lenet_loops.scala:87:17") } // CounterNew(Const(0),Const(28),Const(1),Const(1))
    val x9765 = withCtrl(x10326) { CounterChain(List(x9763,x9764)).name("x9765").srcCtx("lenet_loops.scala:87:17") } // CounterChainNew(List(x9763, x9764))
    val x9795 = withCtrl(x10326) { LoopController(style=StreamPipe, level=OuterControl, cchain=x9765).name("x9795").srcCtx("lenet_loops.scala:87:17") } // UnrolledForeach(List(b5444),x9765,Block(Const(())),List(List(b5451), List(b5452)),List(List(b5453), List(b5454)))
    val b5451 = withCtrl(x9795) { CounterIter(x9763, Some(0)).name("b5451") } // b5451
    val b5453 = withCtrl(x9795) { Const(true).name("b5453") } // b5453
    val b5452 = withCtrl(x9795) { CounterIter(x9764, Some(0)).name("b5452") } // b5452
    val b5454 = withCtrl(x9795) { Const(true).name("b5454") } // b5454
    val b10381 = withCtrl(x9795) { StreamOut(field="offset").name("b10381").srcCtx("lenet_loops.scala:87:17") } // x9766 = StreamOutNew(BurstCmdBus)
    isAccum(b10381) = false
    bufferDepthOf(b10381) = 1
    val b10382 = withCtrl(x9795) { StreamOut(field="size").name("b10382").srcCtx("lenet_loops.scala:87:17") } // x9766 = StreamOutNew(BurstCmdBus)
    isAccum(b10382) = false
    bufferDepthOf(b10382) = 1
    val x9767 = withCtrl(x9795) { StreamIn(field="data").name("x9767").srcCtx("lenet_loops.scala:87:17") } // x9767 = StreamInNew(BurstDataBus())
    isAccum(x9767) = false
    bufferDepthOf(x9767) = 1
    val x9784 = withCtrl(x9795) { UnitController(style=SeqPipe, level=InnerControl).name("x9784").srcCtx("lenet_loops.scala:87:17") } // UnitPipe(List(b5453, b5454, b5444),Block(x9783))
    val x9768 = withCtrl(x9784) { OpDef(op=FixAdd, inputs=List(b5443, b5451)).name("x9768").srcCtx("lenet_loops.scala:87:17") } // FixAdd(b5443,b5451)
    val x9769 = withCtrl(x9784) { x9768 } // FixConvert(x9768,TRUE,_32,_0) (Same Type. No op)
    val x9770 = withCtrl(x9784) { OpDef(op=FixMul, inputs=List(x9769, Const(896))).name("x9770").srcCtx("lenet_loops.scala:87:17") } // FixMul(x9769,Const(896))
    val x9771 = withCtrl(x9784) { b5452 } // FixConvert(b5452,TRUE,_32,_0) (Same Type. No op)
    val x9772 = withCtrl(x9784) { OpDef(op=FixSla, inputs=List(x9771, Const(5))).name("x9772").srcCtx("lenet_loops.scala:87:17") } // FixLsh(x9771,Const(5))
    val x9773 = withCtrl(x9784) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x9774 = withCtrl(x9784) { OpDef(op=FixAdd, inputs=List(x9770, x9772)).name("x9774").srcCtx("lenet_loops.scala:87:17") } // FixAdd(x9770,x9772)
    val x9775 = withCtrl(x9784) { OpDef(op=FixAdd, inputs=List(x9774, x9773)).name("x9775").srcCtx("lenet_loops.scala:87:17") } // FixAdd(x9774,x9773)
    val x9776 = withCtrl(x9784) { OpDef(op=FixSla, inputs=List(x9775, Const(2))).name("x9776").srcCtx("lenet_loops.scala:87:17") } // FixLsh(x9775,Const(2))
    val x9777 = withCtrl(x9784) { x9776 } // FixConvert(x9776,TRUE,_64,_0)
    val x9778 = withCtrl(x9784) { DramAddress(x9558).name("x9778").srcCtx("lenet_loops.scala:87:17") } // GetDRAMAddress(x9558)
    val x9780_x9779 = withCtrl(x9784) { OpDef(op=FixAdd, inputs=List(x9777, x9778)).name("x9780_x9779").srcCtx("lenet_loops.scala:87:17") } // FixAdd(x9777,x9778)
    // x9780 = SimpleStruct(ArrayBuffer((offset,x9779), (size,Const(128)), (isLoad,Const(true))))
    val x9781 = withCtrl(x9784) { OpDef(op=BitAnd, inputs=List(b5453, b5454)).name("x9781").srcCtx("UnrollingBase.scala:28:66") } // And(b5453,b5454)
    val x9782 = withCtrl(x9784) { OpDef(op=BitAnd, inputs=List(x9781, b5444)).name("x9782").srcCtx("UnrollingBase.scala:28:66") } // And(x9781,b5444)
    val x9783_b10383_b10381 = withCtrl(x9784) { WriteMem(b10381, x9780_x9779).name("x9783_b10383_b10381").srcCtx("lenet_loops.scala:87:17") } // StreamWrite(x9766,x9780,x9782)
    val x9783_b10384_b10382 = withCtrl(x9784) { WriteMem(b10382, Const(128)).name("x9783_b10384_b10382").srcCtx("lenet_loops.scala:87:17") } // StreamWrite(x9766,x9780,x9782)
    val x9785 = withCtrl(x9795) { FringeDenseLoad(dram=List(x9558), cmdStream=List(b10381, b10382), dataStream=List(x9767)).name("x9785").srcCtx("lenet_loops.scala:87:17") } // FringeDenseLoad(x9558,x9766,x9767)
    val x9786 = withCtrl(x9795) { Counter(min=Const(0), max=Const(32), step=Const(1), par=16).name("x9786").srcCtx("lenet_loops.scala:87:17") } // CounterNew(Const(0),Const(32),Const(1),Const(16))
    val x9787 = withCtrl(x9795) { CounterChain(List(x9786)).name("x9787").srcCtx("lenet_loops.scala:87:17") } // CounterChainNew(List(x9786))
    val x9794 = withCtrl(x9795) { LoopController(style=InnerPipe, level=InnerControl, cchain=x9787).name("x9794").srcCtx("lenet_loops.scala:87:17") } // UnrolledForeach(List(b5453, b5454, b5444),x9787,Block(Const(())),List(List(b5477)),List(List(b5478)))
    val b5477 = withCtrl(x9794) { CounterIter(x9786, None).name("b5477") } // b5477
    val b5478 = withCtrl(x9794) { Const(true).name("b5478") } // b5478
    val x9788 = withCtrl(x9794) { OpDef(op=BitAnd, inputs=List(b5478, b5453)).name("x9788").srcCtx("UnrollingBase.scala:28:66") } // And(b5478,b5453)
    val x9789 = withCtrl(x9794) { OpDef(op=BitAnd, inputs=List(b5454, b5444)).name("x9789").srcCtx("UnrollingBase.scala:28:66") } // And(b5454,b5444)
    val x9790 = withCtrl(x9794) { OpDef(op=BitAnd, inputs=List(x9788, x9789)).name("x9790").srcCtx("UnrollingBase.scala:28:66") } // And(x9788,x9789)
    val x9791_x9791 = withCtrl(x9794) { ReadMem(x9767).name("x9791_x9791").srcCtx("lenet_loops.scala:87:17") } // ParStreamRead(x9767,List(x9790))
    val x9792_x9792 = withCtrl(x9794) { x9791_x9791 } // VectorApply(x9791,0)
    val x9793 = withCtrl(x9794) { StoreBanks(List(List(x9760_d0_b0)), List(b5452, b5477), x9792_x9792).name("x9793").srcCtx("lenet_loops.scala:87:17") } // ParSRAMStore(x9760,List(List(b5452, b5477)),List(x9792),List(x9790))
    val x9796_d0_b0 = withCtrl(x10326) { SRAM(size=2880, banking=Strided(banks=1, stride=1)).name("x9796_d0_b0").srcCtx("lenet_loops.scala:90:32:tmp1_SRAM") } // x9796 = SRAMNew(ArrayBuffer(Const(20), Const(12), Const(12)))
    isAccum(x9796_d0_b0) = false
    bufferDepthOf(x9796_d0_b0) = 3
    staticDimsOf(x9796_d0_b0) = List(20, 12, 12)
    val x9796_d1_b0 = withCtrl(x10326) { SRAM(size=2880, banking=Strided(banks=1, stride=1)).name("x9796_d1_b0").srcCtx("lenet_loops.scala:90:32:tmp1_SRAM") } // x9796 = SRAMNew(ArrayBuffer(Const(20), Const(12), Const(12)))
    isAccum(x9796_d1_b0) = false
    bufferDepthOf(x9796_d1_b0) = 3
    staticDimsOf(x9796_d1_b0) = List(20, 12, 12)
    val x9797_d0_b0 = withCtrl(x10326) { SRAM(size=1600, banking=Strided(banks=16, stride=1)).name("x9797_d0_b0").srcCtx("lenet_loops.scala:91:28:c0_RF") } // x9797 = SRAMNew(ArrayBuffer(Const(20), Const(5), Const(16)))
    isAccum(x9797_d0_b0) = false
    bufferDepthOf(x9797_d0_b0) = 2
    staticDimsOf(x9797_d0_b0) = List(20, 5, 16)
    val x9798 = withCtrl(x10326) { Counter(min=Const(0), max=Const(20), step=Const(1), par=1).name("x9798").srcCtx("lenet_loops.scala:92:15") } // CounterNew(Const(0),Const(20),Const(1),Const(1))
    val x9799 = withCtrl(x10326) { Counter(min=Const(0), max=Const(5), step=Const(1), par=1).name("x9799").srcCtx("lenet_loops.scala:92:15") } // CounterNew(Const(0),Const(5),Const(1),Const(1))
    val x9800 = withCtrl(x10326) { CounterChain(List(x9798,x9799)).name("x9800").srcCtx("lenet_loops.scala:92:15") } // CounterChainNew(List(x9798, x9799))
    val x9829 = withCtrl(x10326) { LoopController(style=StreamPipe, level=OuterControl, cchain=x9800).name("x9829").srcCtx("lenet_loops.scala:92:15") } // UnrolledForeach(List(b5444),x9800,Block(Const(())),List(List(b5492), List(b5493)),List(List(b5494), List(b5495)))
    val b5492 = withCtrl(x9829) { CounterIter(x9798, Some(0)).name("b5492") } // b5492
    val b5494 = withCtrl(x9829) { Const(true).name("b5494") } // b5494
    val b5493 = withCtrl(x9829) { CounterIter(x9799, Some(0)).name("b5493") } // b5493
    val b5495 = withCtrl(x9829) { Const(true).name("b5495") } // b5495
    val b10385 = withCtrl(x9829) { StreamOut(field="offset").name("b10385").srcCtx("lenet_loops.scala:92:15") } // x9801 = StreamOutNew(BurstCmdBus)
    isAccum(b10385) = false
    bufferDepthOf(b10385) = 1
    val b10386 = withCtrl(x9829) { StreamOut(field="size").name("b10386").srcCtx("lenet_loops.scala:92:15") } // x9801 = StreamOutNew(BurstCmdBus)
    isAccum(b10386) = false
    bufferDepthOf(b10386) = 1
    val x9802 = withCtrl(x9829) { StreamIn(field="data").name("x9802").srcCtx("lenet_loops.scala:92:15") } // x9802 = StreamInNew(BurstDataBus())
    isAccum(x9802) = false
    bufferDepthOf(x9802) = 1
    val x9818 = withCtrl(x9829) { UnitController(style=SeqPipe, level=InnerControl).name("x9818").srcCtx("lenet_loops.scala:92:15") } // UnitPipe(List(b5494, b5495, b5444),Block(x9817))
    val x9803 = withCtrl(x9818) { b5492 } // FixConvert(b5492,TRUE,_32,_0) (Same Type. No op)
    val x9804 = withCtrl(x9818) { OpDef(op=FixMul, inputs=List(x9803, Const(80))).name("x9804").srcCtx("lenet_loops.scala:92:15") } // FixMul(x9803,Const(80))
    val x9805 = withCtrl(x9818) { b5493 } // FixConvert(b5493,TRUE,_32,_0) (Same Type. No op)
    val x9806 = withCtrl(x9818) { OpDef(op=FixSla, inputs=List(x9805, Const(4))).name("x9806").srcCtx("lenet_loops.scala:92:15") } // FixLsh(x9805,Const(4))
    val x9807 = withCtrl(x9818) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x9808 = withCtrl(x9818) { OpDef(op=FixAdd, inputs=List(x9804, x9806)).name("x9808").srcCtx("lenet_loops.scala:92:15") } // FixAdd(x9804,x9806)
    val x9809 = withCtrl(x9818) { OpDef(op=FixAdd, inputs=List(x9808, x9807)).name("x9809").srcCtx("lenet_loops.scala:92:15") } // FixAdd(x9808,x9807)
    val x9810 = withCtrl(x9818) { OpDef(op=FixSla, inputs=List(x9809, Const(2))).name("x9810").srcCtx("lenet_loops.scala:92:15") } // FixLsh(x9809,Const(2))
    val x9811 = withCtrl(x9818) { x9810 } // FixConvert(x9810,TRUE,_64,_0)
    val x9812 = withCtrl(x9818) { DramAddress(x9557).name("x9812").srcCtx("lenet_loops.scala:92:15") } // GetDRAMAddress(x9557)
    val x9814_x9813 = withCtrl(x9818) { OpDef(op=FixAdd, inputs=List(x9811, x9812)).name("x9814_x9813").srcCtx("lenet_loops.scala:92:15") } // FixAdd(x9811,x9812)
    // x9814 = SimpleStruct(ArrayBuffer((offset,x9813), (size,Const(64)), (isLoad,Const(true))))
    val x9815 = withCtrl(x9818) { OpDef(op=BitAnd, inputs=List(b5494, b5495)).name("x9815").srcCtx("UnrollingBase.scala:28:66") } // And(b5494,b5495)
    val x9816 = withCtrl(x9818) { OpDef(op=BitAnd, inputs=List(x9815, b5444)).name("x9816").srcCtx("UnrollingBase.scala:28:66") } // And(x9815,b5444)
    val x9817_b10387_b10385 = withCtrl(x9818) { WriteMem(b10385, x9814_x9813).name("x9817_b10387_b10385").srcCtx("lenet_loops.scala:92:15") } // StreamWrite(x9801,x9814,x9816)
    val x9817_b10388_b10386 = withCtrl(x9818) { WriteMem(b10386, Const(64)).name("x9817_b10388_b10386").srcCtx("lenet_loops.scala:92:15") } // StreamWrite(x9801,x9814,x9816)
    val x9819 = withCtrl(x9829) { FringeDenseLoad(dram=List(x9557), cmdStream=List(b10385, b10386), dataStream=List(x9802)).name("x9819").srcCtx("lenet_loops.scala:92:15") } // FringeDenseLoad(x9557,x9801,x9802)
    val x9820 = withCtrl(x9829) { Counter(min=Const(0), max=Const(16), step=Const(1), par=16).name("x9820").srcCtx("lenet_loops.scala:92:15") } // CounterNew(Const(0),Const(16),Const(1),Const(16))
    val x9821 = withCtrl(x9829) { CounterChain(List(x9820)).name("x9821").srcCtx("lenet_loops.scala:92:15") } // CounterChainNew(List(x9820))
    val x9828 = withCtrl(x9829) { LoopController(style=InnerPipe, level=InnerControl, cchain=x9821).name("x9828").srcCtx("lenet_loops.scala:92:15") } // UnrolledForeach(List(b5494, b5495, b5444),x9821,Block(Const(())),List(List(b5517)),List(List(b5518)))
    val b5517 = withCtrl(x9828) { CounterIter(x9820, None).name("b5517") } // b5517
    val b5518 = withCtrl(x9828) { Const(true).name("b5518") } // b5518
    val x9822 = withCtrl(x9828) { OpDef(op=BitAnd, inputs=List(b5518, b5494)).name("x9822").srcCtx("UnrollingBase.scala:28:66") } // And(b5518,b5494)
    val x9823 = withCtrl(x9828) { OpDef(op=BitAnd, inputs=List(b5495, b5444)).name("x9823").srcCtx("UnrollingBase.scala:28:66") } // And(b5495,b5444)
    val x9824 = withCtrl(x9828) { OpDef(op=BitAnd, inputs=List(x9822, x9823)).name("x9824").srcCtx("UnrollingBase.scala:28:66") } // And(x9822,x9823)
    val x9825_x9825 = withCtrl(x9828) { ReadMem(x9802).name("x9825_x9825").srcCtx("lenet_loops.scala:92:15") } // ParStreamRead(x9802,List(x9824))
    val x9826_x9826 = withCtrl(x9828) { x9825_x9825 } // VectorApply(x9825,0)
    val x9827 = withCtrl(x9828) { StoreBanks(List(List(x9797_d0_b0)), List(b5492, b5493, b5517), x9826_x9826).name("x9827").srcCtx("lenet_loops.scala:92:15") } // ParSRAMStore(x9797,List(List(b5492, b5493, b5517)),List(x9826),List(x9824))
    val x9830 = withCtrl(x10326) { Counter(min=Const(0), max=Const(20), step=Const(1), par=1).name("x9830").srcCtx("lenet_loops.scala:93:25") } // CounterNew(Const(0),Const(20),Const(1),Const(1))
    val x9831 = withCtrl(x10326) { CounterChain(List(x9830)).name("x9831").srcCtx("lenet_loops.scala:93:40") } // CounterChainNew(List(x9830))
    val x9910 = withCtrl(x10326) { LoopController(style=MetaPipe, level=OuterControl, cchain=x9831).name("x9910").srcCtx("lenet_loops.scala:93:40") } // UnrolledForeach(List(b5444),x9831,Block(Const(())),List(List(b5529)),List(List(b5530)))
    val b5529 = withCtrl(x9910) { CounterIter(x9830, Some(0)).name("b5529") } // b5529
    val b5530 = withCtrl(x9910) { Const(true).name("b5530") } // b5530
    val x9832_d0_b0 = withCtrl(x9910) { SRAM(size=576, banking=Strided(banks=2, stride=1)).name("x9832_d0_b0").srcCtx("lenet_loops.scala:100:39:tmp1_SRAM_conv") } // x9832 = SRAMNew(ArrayBuffer(Const(12), Const(2), Const(12), Const(2)))
    isAccum(x9832_d0_b0) = false
    bufferDepthOf(x9832_d0_b0) = 2
    staticDimsOf(x9832_d0_b0) = List(12, 2, 12, 2)
    val x9833 = withCtrl(x9910) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x9833").srcCtx("lenet_loops.scala:101:38") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x9834 = withCtrl(x9910) { Counter(min=Const(0), max=Const(24), step=Const(2), par=1).name("x9834").srcCtx("lenet_loops.scala:101:30") } // CounterNew(Const(0),Const(24),Const(2),Const(1))
    val x9835 = withCtrl(x9910) { CounterChain(List(x9834,x9833)).name("x9835").srcCtx("lenet_loops.scala:101:44") } // CounterChainNew(List(x9834, x9833))
    val x9877 = withCtrl(x9910) { LoopController(style=MetaPipe, level=OuterControl, cchain=x9835).name("x9877").srcCtx("lenet_loops.scala:101:44") } // UnrolledForeach(List(b5530, b5444),x9835,Block(Const(())),List(List(b5535), List(b5536)),List(List(b5537), List(b5538)))
    val b5535 = withCtrl(x9877) { CounterIter(x9834, Some(0)).name("b5535") } // b5535
    val b5537 = withCtrl(x9877) { Const(true).name("b5537") } // b5537
    val b5536 = withCtrl(x9877) { CounterIter(x9833, Some(0)).name("b5536") } // b5536
    val b5538 = withCtrl(x9877) { Const(true).name("b5538") } // b5538
    val x9836 = withCtrl(x9877) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x9836").srcCtx("lenet_loops.scala:102:40") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x9837 = withCtrl(x9877) { Counter(min=Const(0), max=Const(24), step=Const(2), par=1).name("x9837").srcCtx("lenet_loops.scala:102:32") } // CounterNew(Const(0),Const(24),Const(2),Const(1))
    val x9838 = withCtrl(x9877) { CounterChain(List(x9837,x9836)).name("x9838").srcCtx("lenet_loops.scala:102:46") } // CounterChainNew(List(x9837, x9836))
    val x9876 = withCtrl(x9877) { LoopController(style=MetaPipe, level=OuterControl, cchain=x9838).name("x9876").srcCtx("lenet_loops.scala:102:46") } // UnrolledForeach(List(b5537, b5538, b5530, b5444),x9838,Block(Const(())),List(List(b5542), List(b5543)),List(List(b5544), List(b5545)))
    val b5542 = withCtrl(x9876) { CounterIter(x9837, Some(0)).name("b5542") } // b5542
    val b5544 = withCtrl(x9876) { Const(true).name("b5544") } // b5544
    val b5543 = withCtrl(x9876) { CounterIter(x9836, Some(0)).name("b5543") } // b5543
    val b5545 = withCtrl(x9876) { Const(true).name("b5545") } // b5545
    val x9839_d0 = withCtrl(x9876) { Reg(init=Some(0.0)).name("x9839_d0").srcCtx("lenet_loops.scala:103:41:window") } // x9839 = RegNew(Const(0))
    isAccum(x9839_d0) = false
    bufferDepthOf(x9839_d0) = 2
    val x9839_d1 = withCtrl(x9876) { Reg(init=Some(0.0)).name("x9839_d1").srcCtx("lenet_loops.scala:103:41:window") } // x9839 = RegNew(Const(0))
    isAccum(x9839_d1) = true
    bufferDepthOf(x9839_d1) = 1
    val x9840 = withCtrl(x9876) { Counter(min=Const(0), max=Const(5), step=Const(1), par=5).name("x9840").srcCtx("lenet_loops.scala:103:75") } // CounterNew(Const(0),Const(5),Const(1),Const(5))
    val x9841 = withCtrl(x9876) { Counter(min=Const(0), max=Const(5), step=Const(1), par=1).name("x9841").srcCtx("lenet_loops.scala:103:54") } // CounterNew(Const(0),Const(5),Const(1),Const(1))
    val x9842 = withCtrl(x9876) { CounterChain(List(x9841,x9840)).name("x9842").srcCtx("lenet_loops.scala:105:16") } // CounterChainNew(List(x9841, x9840))
    val x9867 = withCtrl(x9876) { LoopController(style=InnerPipe, level=InnerControl, cchain=x9842).name("x9867").srcCtx("lenet_loops.scala:105:16") } // UnrolledReduce(List(b5544, b5545, b5537, b5538, b5530, b5444),x9842,x9839,Block((x9839) => Const(())),List(List(b5550), List(b5551)),List(List(b5552), List(b5553)))
    val b5550 = withCtrl(x9867) { CounterIter(x9841, Some(0)).name("b5550") } // b5550
    val b5552 = withCtrl(x9867) { Const(true).name("b5552") } // b5552
    val b5551 = withCtrl(x9867) { CounterIter(x9840, None).name("b5551") } // b5551
    val b5553 = withCtrl(x9867) { Const(true).name("b5553") } // b5553
    val x9843 = withCtrl(x9867) { OpDef(op=FixAdd, inputs=List(b5535, b5536)).name("x9843").srcCtx("lenet_loops.scala:104:27") } // FixAdd(b5535,b5536)
    val x9844 = withCtrl(x9867) { OpDef(op=FixAdd, inputs=List(x9843, b5550)).name("x9844").srcCtx("lenet_loops.scala:104:30") } // FixAdd(x9843,b5550)
    val x9845 = withCtrl(x9867) { OpDef(op=FixAdd, inputs=List(b5542, b5543)).name("x9845").srcCtx("lenet_loops.scala:104:35") } // FixAdd(b5542,b5543)
    val x9846 = withCtrl(x9867) { OpDef(op=FixAdd, inputs=List(x9845, b5551)).name("x9846").srcCtx("lenet_loops.scala:104:38") } // FixAdd(x9845,b5551)
    val x9847 = withCtrl(x9867) { OpDef(op=BitAnd, inputs=List(b5552, b5553)).name("x9847").srcCtx("UnrollingBase.scala:28:66") } // And(b5552,b5553)
    val x9848 = withCtrl(x9867) { OpDef(op=BitAnd, inputs=List(b5544, b5545)).name("x9848").srcCtx("UnrollingBase.scala:28:66") } // And(b5544,b5545)
    val x9849 = withCtrl(x9867) { OpDef(op=BitAnd, inputs=List(b5537, b5538)).name("x9849").srcCtx("UnrollingBase.scala:28:66") } // And(b5537,b5538)
    val x9850 = withCtrl(x9867) { OpDef(op=BitAnd, inputs=List(b5530, b5444)).name("x9850").srcCtx("UnrollingBase.scala:28:66") } // And(b5530,b5444)
    val x9851 = withCtrl(x9867) { OpDef(op=BitAnd, inputs=List(x9847, x9848)).name("x9851").srcCtx("UnrollingBase.scala:28:66") } // And(x9847,x9848)
    val x9852 = withCtrl(x9867) { OpDef(op=BitAnd, inputs=List(x9849, x9850)).name("x9852").srcCtx("UnrollingBase.scala:28:66") } // And(x9849,x9850)
    val x9853 = withCtrl(x9867) { OpDef(op=BitAnd, inputs=List(x9851, x9852)).name("x9853").srcCtx("UnrollingBase.scala:28:66") } // And(x9851,x9852)
    val x9854 = withCtrl(x9867) { LoadBanks(List(x9760_d0_b0), List(x9844, x9846)).name("x9854").srcCtx("lenet_loops.scala:104:24") } // ParSRAMLoad(x9760,List(List(x9844, x9846)),List(x9853))
    val x9855 = withCtrl(x9867) { x9854 } // VectorApply(x9854,0)
    val x9856 = withCtrl(x9867) { LoadBanks(List(x9797_d0_b0), List(b5529, b5550, b5551)).name("x9856").srcCtx("lenet_loops.scala:104:49") } // ParSRAMLoad(x9797,List(List(b5529, b5550, b5551)),List(x9853))
    val x9857 = withCtrl(x9867) { x9856 } // VectorApply(x9856,0)
    val x9858 = withCtrl(x9867) { OpDef(op=FixMul, inputs=List(x9855, x9857)).name("x9858").srcCtx("lenet_loops.scala:104:42") } // FixMul(x9855,x9857)
    val x9859 = withCtrl(x9867) { ReadMem(x9839_d1).name("x9859").srcCtx("lenet_loops.scala:105:16") } // RegRead(x9839)
    val x9860 = withCtrl(x9867) { OpDef(op=FixEql, inputs=List(b5550, Const(0))).name("x9860").srcCtx("lenet_loops.scala:105:16") } // FixEql(b5550,Const(0))
    val x9861 = withCtrl(x9867) { OpDef(op=FixEql, inputs=List(b5551, Const(0))).name("x9861").srcCtx("lenet_loops.scala:105:16") } // FixEql(b5551,Const(0))
    val x9862 = withCtrl(x9867) { OpDef(op=BitAnd, inputs=List(x9860, x9861)).name("x9862").srcCtx("lenet_loops.scala:105:16") } // And(x9860,x9861)
    val x9863 = withCtrl(x9867) { ReduceAccumOp(op=FixAdd, input=x9858, accum=x9859).name("x9863").srcCtx("lenet_loops.scala:105:18") } // FixAdd(x9858,x9859)
    val x9864 = withCtrl(x9867) { OpDef(op=BitAnd, inputs=List(x9848, x9849)).name("x9864").srcCtx("UnrollingBase.scala:28:66") } // And(x9848,x9849)
    val x9865 = withCtrl(x9867) { OpDef(op=BitAnd, inputs=List(x9864, x9850)).name("x9865").srcCtx("UnrollingBase.scala:28:66") } // And(x9864,x9850)
    val x9866_x9839_d0 = withCtrl(x9867) { WriteMem(x9839_d0, x9863).name("x9866_x9839_d0").srcCtx("lenet_loops.scala:105:16") } // RegWrite(x9839,x9863,x9865)
    antiDepsOf(x9866_x9839_d0)=List(x9859)
    val x9866_x9839_d1 = withCtrl(x9867) { WriteMem(x9839_d1, x9863).name("x9866_x9839_d1").srcCtx("lenet_loops.scala:105:16") } // RegWrite(x9839,x9863,x9865)
    antiDepsOf(x9866_x9839_d1)=List(x9859)
    val x9875 = withCtrl(x9876) { UnitController(style=SeqPipe, level=InnerControl).name("x9875").srcCtx("lenet_loops.scala:102:46") } // UnitPipe(List(b5544, b5545, b5537, b5538, b5530, b5444),Block(Const(())))
    val x9868 = withCtrl(x9875) { ReadMem(x9839_d0).name("x9868").srcCtx("lenet_loops.scala:106:52") } // RegRead(x9839)
    val x9869 = withCtrl(x9875) { OpDef(op=BitAnd, inputs=List(b5544, b5545)).name("x9869").srcCtx("UnrollingBase.scala:28:66") } // And(b5544,b5545)
    val x9870 = withCtrl(x9875) { OpDef(op=BitAnd, inputs=List(b5537, b5538)).name("x9870").srcCtx("UnrollingBase.scala:28:66") } // And(b5537,b5538)
    val x9871 = withCtrl(x9875) { OpDef(op=BitAnd, inputs=List(b5530, b5444)).name("x9871").srcCtx("UnrollingBase.scala:28:66") } // And(b5530,b5444)
    val x9872 = withCtrl(x9875) { OpDef(op=BitAnd, inputs=List(x9869, x9870)).name("x9872").srcCtx("UnrollingBase.scala:28:66") } // And(x9869,x9870)
    val x9873 = withCtrl(x9875) { OpDef(op=BitAnd, inputs=List(x9872, x9871)).name("x9873").srcCtx("UnrollingBase.scala:28:66") } // And(x9872,x9871)
    val x9874 = withCtrl(x9875) { StoreBanks(List(List(x9832_d0_b0)), List(b5535, b5536, b5542, b5543), x9868).name("x9874").srcCtx("lenet_loops.scala:106:43") } // SRAMStore(x9832,ArrayBuffer(Const(12), Const(2), Const(12), Const(2)),List(b5535, b5536, b5542, b5543),Const(0),x9868,x9873)
    val x9878 = withCtrl(x9910) { Counter(min=Const(0), max=Const(12), step=Const(1), par=1).name("x9878").srcCtx("lenet_loops.scala:109:31") } // CounterNew(Const(0),Const(12),Const(1),Const(1))
    val x9879 = withCtrl(x9910) { Counter(min=Const(0), max=Const(12), step=Const(1), par=1).name("x9879").srcCtx("lenet_loops.scala:109:22") } // CounterNew(Const(0),Const(12),Const(1),Const(1))
    val x9880 = withCtrl(x9910) { CounterChain(List(x9879,x9878)).name("x9880").srcCtx("lenet_loops.scala:109:37") } // CounterChainNew(List(x9879, x9878))
    val x9909 = withCtrl(x9910) { LoopController(style=MetaPipe, level=OuterControl, cchain=x9880).name("x9909").srcCtx("lenet_loops.scala:109:37") } // UnrolledForeach(List(b5530, b5444),x9880,Block(Const(())),List(List(b5592), List(b5593)),List(List(b5594), List(b5595)))
    val b5592 = withCtrl(x9909) { CounterIter(x9879, Some(0)).name("b5592") } // b5592
    val b5594 = withCtrl(x9909) { Const(true).name("b5594") } // b5594
    val b5593 = withCtrl(x9909) { CounterIter(x9878, Some(0)).name("b5593") } // b5593
    val b5595 = withCtrl(x9909) { Const(true).name("b5595") } // b5595
    val x9881_d0 = withCtrl(x9909) { Reg(init=Some(0.0)).name("x9881_d0").srcCtx("lenet_loops.scala:110:36:out") } // x9881 = RegNew(Const(0))
    isAccum(x9881_d0) = false
    bufferDepthOf(x9881_d0) = 2
    val x9881_d1 = withCtrl(x9909) { Reg(init=Some(0.0)).name("x9881_d1").srcCtx("lenet_loops.scala:110:36:out") } // x9881 = RegNew(Const(0))
    isAccum(x9881_d1) = true
    bufferDepthOf(x9881_d1) = 1
    val x9882 = withCtrl(x9909) { Counter(min=Const(0), max=Const(2), step=Const(1), par=2).name("x9882").srcCtx("lenet_loops.scala:110:62") } // CounterNew(Const(0),Const(2),Const(1),Const(2))
    val x9883 = withCtrl(x9909) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x9883").srcCtx("lenet_loops.scala:110:49") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x9884 = withCtrl(x9909) { CounterChain(List(x9883,x9882)).name("x9884").srcCtx("lenet_loops.scala:112:15") } // CounterChainNew(List(x9883, x9882))
    val x9902 = withCtrl(x9909) { LoopController(style=InnerPipe, level=InnerControl, cchain=x9884).name("x9902").srcCtx("lenet_loops.scala:112:15") } // UnrolledReduce(List(b5594, b5595, b5530, b5444),x9884,x9881,Block((x9881) => Const(())),List(List(b5600), List(b5601)),List(List(b5602), List(b5603)))
    val b5600 = withCtrl(x9902) { CounterIter(x9883, Some(0)).name("b5600") } // b5600
    val b5602 = withCtrl(x9902) { Const(true).name("b5602") } // b5602
    val b5601 = withCtrl(x9902) { CounterIter(x9882, None).name("b5601") } // b5601
    val b5603 = withCtrl(x9902) { Const(true).name("b5603") } // b5603
    val x9885 = withCtrl(x9902) { OpDef(op=BitAnd, inputs=List(b5602, b5603)).name("x9885").srcCtx("UnrollingBase.scala:28:66") } // And(b5602,b5603)
    val x9886 = withCtrl(x9902) { OpDef(op=BitAnd, inputs=List(b5594, b5595)).name("x9886").srcCtx("UnrollingBase.scala:28:66") } // And(b5594,b5595)
    val x9887 = withCtrl(x9902) { OpDef(op=BitAnd, inputs=List(b5530, b5444)).name("x9887").srcCtx("UnrollingBase.scala:28:66") } // And(b5530,b5444)
    val x9888 = withCtrl(x9902) { OpDef(op=BitAnd, inputs=List(x9885, x9886)).name("x9888").srcCtx("UnrollingBase.scala:28:66") } // And(x9885,x9886)
    val x9889 = withCtrl(x9902) { OpDef(op=BitAnd, inputs=List(x9888, x9887)).name("x9889").srcCtx("UnrollingBase.scala:28:66") } // And(x9888,x9887)
    val x9890 = withCtrl(x9902) { LoadBanks(List(x9832_d0_b0), List(b5592, b5600, b5593, b5601)).name("x9890").srcCtx("lenet_loops.scala:111:42") } // ParSRAMLoad(x9832,List(List(b5592, b5600, b5593, b5601)),List(x9889))
    val x9891 = withCtrl(x9902) { x9890 } // VectorApply(x9890,0)
    val x9892 = withCtrl(x9902) { LoadBanks(List(x9676_d0_b0), List(b5529)).name("x9892").srcCtx("lenet_loops.scala:111:63") } // SRAMLoad(x9676,ArrayBuffer(Const(32)),List(b5529),Const(0),x9889)
    val x9893 = withCtrl(x9902) { OpDef(op=FixAdd, inputs=List(x9891, x9892)).name("x9893").srcCtx("lenet_loops.scala:111:54") } // FixAdd(x9891,x9892)
    val x9894 = withCtrl(x9902) { OpDef(op=FixMax, inputs=List(Const(0.0), x9893)).name("x9894").srcCtx("lenet_loops.scala:111:18") } // Max(Const(0),x9893)
    val x9895 = withCtrl(x9902) { ReadMem(x9881_d1).name("x9895").srcCtx("lenet_loops.scala:112:15") } // RegRead(x9881)
    val x9896 = withCtrl(x9902) { OpDef(op=FixEql, inputs=List(b5600, Const(0))).name("x9896").srcCtx("lenet_loops.scala:112:15") } // FixEql(b5600,Const(0))
    val x9897 = withCtrl(x9902) { OpDef(op=FixEql, inputs=List(b5601, Const(0))).name("x9897").srcCtx("lenet_loops.scala:112:15") } // FixEql(b5601,Const(0))
    val x9898 = withCtrl(x9902) { OpDef(op=BitAnd, inputs=List(x9896, x9897)).name("x9898").srcCtx("lenet_loops.scala:112:15") } // And(x9896,x9897)
    val x9899 = withCtrl(x9902) { ReduceAccumOp(op=FixMax, input=x9894, accum=x9895).name("x9899").srcCtx("lenet_loops.scala:112:29") } // Max(x9894,x9895)
    val x9900 = withCtrl(x9902) { OpDef(op=BitAnd, inputs=List(x9886, x9887)).name("x9900").srcCtx("UnrollingBase.scala:28:66") } // And(x9886,x9887)
    val x9901_x9881_d0 = withCtrl(x9902) { WriteMem(x9881_d0, x9899).name("x9901_x9881_d0").srcCtx("lenet_loops.scala:112:15") } // RegWrite(x9881,x9899,x9900)
    antiDepsOf(x9901_x9881_d0)=List(x9895)
    val x9901_x9881_d1 = withCtrl(x9902) { WriteMem(x9881_d1, x9899).name("x9901_x9881_d1").srcCtx("lenet_loops.scala:112:15") } // RegWrite(x9881,x9899,x9900)
    antiDepsOf(x9901_x9881_d1)=List(x9895)
    val x9908 = withCtrl(x9909) { UnitController(style=SeqPipe, level=InnerControl).name("x9908").srcCtx("lenet_loops.scala:109:37") } // UnitPipe(List(b5594, b5595, b5530, b5444),Block(Const(())))
    val x9903 = withCtrl(x9908) { ReadMem(x9881_d0).name("x9903").srcCtx("lenet_loops.scala:113:43") } // RegRead(x9881)
    val x9904 = withCtrl(x9908) { OpDef(op=BitAnd, inputs=List(b5594, b5595)).name("x9904").srcCtx("UnrollingBase.scala:28:66") } // And(b5594,b5595)
    val x9905 = withCtrl(x9908) { OpDef(op=BitAnd, inputs=List(b5530, b5444)).name("x9905").srcCtx("UnrollingBase.scala:28:66") } // And(b5530,b5444)
    val x9906 = withCtrl(x9908) { OpDef(op=BitAnd, inputs=List(x9904, x9905)).name("x9906").srcCtx("UnrollingBase.scala:28:66") } // And(x9904,x9905)
    val x9907 = withCtrl(x9908) { StoreBanks(List(List(x9796_d0_b0), List(x9796_d1_b0)), List(b5529, b5592, b5593), x9903).name("x9907").srcCtx("lenet_loops.scala:113:37") } // SRAMStore(x9796,ArrayBuffer(Const(20), Const(12), Const(12)),List(b5529, b5592, b5593),Const(0),x9903,x9906)
    val x9911_d0_b0 = withCtrl(x10326) { SRAM(size=800, banking=Strided(banks=2, stride=16)).name("x9911_d0_b0").srcCtx("lenet_loops.scala:121:32:tmp2_SRAM") } // x9911 = SRAMNew(ArrayBuffer(Const(50), Const(4), Const(4)))
    isAccum(x9911_d0_b0) = false
    bufferDepthOf(x9911_d0_b0) = 2
    staticDimsOf(x9911_d0_b0) = List(50, 4, 4)
    val x9912_d0_b0 = withCtrl(x10326) { SRAM(size=4000, banking=Strided(banks=16, stride=1)).name("x9912_d0_b0").srcCtx("lenet_loops.scala:122:28:c2_RF") } // x9912 = SRAMNew(ArrayBuffer(Const(50), Const(5), Const(16)))
    isAccum(x9912_d0_b0) = false
    bufferDepthOf(x9912_d0_b0) = 2
    staticDimsOf(x9912_d0_b0) = List(50, 5, 16)
    val x9912_d1_b0 = withCtrl(x10326) { SRAM(size=4000, banking=Strided(banks=16, stride=1)).name("x9912_d1_b0").srcCtx("lenet_loops.scala:122:28:c2_RF") } // x9912 = SRAMNew(ArrayBuffer(Const(50), Const(5), Const(16)))
    isAccum(x9912_d1_b0) = false
    bufferDepthOf(x9912_d1_b0) = 2
    staticDimsOf(x9912_d1_b0) = List(50, 5, 16)
    val x9913 = withCtrl(x10326) { Counter(min=Const(0), max=Const(50), step=Const(1), par=1).name("x9913").srcCtx("lenet_loops.scala:123:15") } // CounterNew(Const(0),Const(50),Const(1),Const(1))
    val x9914 = withCtrl(x10326) { Counter(min=Const(0), max=Const(5), step=Const(1), par=1).name("x9914").srcCtx("lenet_loops.scala:123:15") } // CounterNew(Const(0),Const(5),Const(1),Const(1))
    val x9915 = withCtrl(x10326) { CounterChain(List(x9913,x9914)).name("x9915").srcCtx("lenet_loops.scala:123:15") } // CounterChainNew(List(x9913, x9914))
    val x9944 = withCtrl(x10326) { LoopController(style=StreamPipe, level=OuterControl, cchain=x9915).name("x9944").srcCtx("lenet_loops.scala:123:15") } // UnrolledForeach(List(b5444),x9915,Block(Const(())),List(List(b5635), List(b5636)),List(List(b5637), List(b5638)))
    val b5635 = withCtrl(x9944) { CounterIter(x9913, Some(0)).name("b5635") } // b5635
    val b5637 = withCtrl(x9944) { Const(true).name("b5637") } // b5637
    val b5636 = withCtrl(x9944) { CounterIter(x9914, Some(0)).name("b5636") } // b5636
    val b5638 = withCtrl(x9944) { Const(true).name("b5638") } // b5638
    val b10389 = withCtrl(x9944) { StreamOut(field="offset").name("b10389").srcCtx("lenet_loops.scala:123:15") } // x9916 = StreamOutNew(BurstCmdBus)
    isAccum(b10389) = false
    bufferDepthOf(b10389) = 1
    val b10390 = withCtrl(x9944) { StreamOut(field="size").name("b10390").srcCtx("lenet_loops.scala:123:15") } // x9916 = StreamOutNew(BurstCmdBus)
    isAccum(b10390) = false
    bufferDepthOf(b10390) = 1
    val x9917 = withCtrl(x9944) { StreamIn(field="data").name("x9917").srcCtx("lenet_loops.scala:123:15") } // x9917 = StreamInNew(BurstDataBus())
    isAccum(x9917) = false
    bufferDepthOf(x9917) = 1
    val x9933 = withCtrl(x9944) { UnitController(style=SeqPipe, level=InnerControl).name("x9933").srcCtx("lenet_loops.scala:123:15") } // UnitPipe(List(b5637, b5638, b5444),Block(x9932))
    val x9918 = withCtrl(x9933) { b5635 } // FixConvert(b5635,TRUE,_32,_0) (Same Type. No op)
    val x9919 = withCtrl(x9933) { OpDef(op=FixMul, inputs=List(x9918, Const(80))).name("x9919").srcCtx("lenet_loops.scala:123:15") } // FixMul(x9918,Const(80))
    val x9920 = withCtrl(x9933) { b5636 } // FixConvert(b5636,TRUE,_32,_0) (Same Type. No op)
    val x9921 = withCtrl(x9933) { OpDef(op=FixSla, inputs=List(x9920, Const(4))).name("x9921").srcCtx("lenet_loops.scala:123:15") } // FixLsh(x9920,Const(4))
    val x9922 = withCtrl(x9933) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x9923 = withCtrl(x9933) { OpDef(op=FixAdd, inputs=List(x9919, x9921)).name("x9923").srcCtx("lenet_loops.scala:123:15") } // FixAdd(x9919,x9921)
    val x9924 = withCtrl(x9933) { OpDef(op=FixAdd, inputs=List(x9923, x9922)).name("x9924").srcCtx("lenet_loops.scala:123:15") } // FixAdd(x9923,x9922)
    val x9925 = withCtrl(x9933) { OpDef(op=FixSla, inputs=List(x9924, Const(2))).name("x9925").srcCtx("lenet_loops.scala:123:15") } // FixLsh(x9924,Const(2))
    val x9926 = withCtrl(x9933) { x9925 } // FixConvert(x9925,TRUE,_64,_0)
    val x9927 = withCtrl(x9933) { DramAddress(x9560).name("x9927").srcCtx("lenet_loops.scala:123:15") } // GetDRAMAddress(x9560)
    val x9929_x9928 = withCtrl(x9933) { OpDef(op=FixAdd, inputs=List(x9926, x9927)).name("x9929_x9928").srcCtx("lenet_loops.scala:123:15") } // FixAdd(x9926,x9927)
    // x9929 = SimpleStruct(ArrayBuffer((offset,x9928), (size,Const(64)), (isLoad,Const(true))))
    val x9930 = withCtrl(x9933) { OpDef(op=BitAnd, inputs=List(b5637, b5638)).name("x9930").srcCtx("UnrollingBase.scala:28:66") } // And(b5637,b5638)
    val x9931 = withCtrl(x9933) { OpDef(op=BitAnd, inputs=List(x9930, b5444)).name("x9931").srcCtx("UnrollingBase.scala:28:66") } // And(x9930,b5444)
    val x9932_b10391_b10389 = withCtrl(x9933) { WriteMem(b10389, x9929_x9928).name("x9932_b10391_b10389").srcCtx("lenet_loops.scala:123:15") } // StreamWrite(x9916,x9929,x9931)
    val x9932_b10392_b10390 = withCtrl(x9933) { WriteMem(b10390, Const(64)).name("x9932_b10392_b10390").srcCtx("lenet_loops.scala:123:15") } // StreamWrite(x9916,x9929,x9931)
    val x9934 = withCtrl(x9944) { FringeDenseLoad(dram=List(x9560), cmdStream=List(b10389, b10390), dataStream=List(x9917)).name("x9934").srcCtx("lenet_loops.scala:123:15") } // FringeDenseLoad(x9560,x9916,x9917)
    val x9935 = withCtrl(x9944) { Counter(min=Const(0), max=Const(16), step=Const(1), par=16).name("x9935").srcCtx("lenet_loops.scala:123:15") } // CounterNew(Const(0),Const(16),Const(1),Const(16))
    val x9936 = withCtrl(x9944) { CounterChain(List(x9935)).name("x9936").srcCtx("lenet_loops.scala:123:15") } // CounterChainNew(List(x9935))
    val x9943 = withCtrl(x9944) { LoopController(style=InnerPipe, level=InnerControl, cchain=x9936).name("x9943").srcCtx("lenet_loops.scala:123:15") } // UnrolledForeach(List(b5637, b5638, b5444),x9936,Block(Const(())),List(List(b5660)),List(List(b5661)))
    val b5660 = withCtrl(x9943) { CounterIter(x9935, None).name("b5660") } // b5660
    val b5661 = withCtrl(x9943) { Const(true).name("b5661") } // b5661
    val x9937 = withCtrl(x9943) { OpDef(op=BitAnd, inputs=List(b5661, b5637)).name("x9937").srcCtx("UnrollingBase.scala:28:66") } // And(b5661,b5637)
    val x9938 = withCtrl(x9943) { OpDef(op=BitAnd, inputs=List(b5638, b5444)).name("x9938").srcCtx("UnrollingBase.scala:28:66") } // And(b5638,b5444)
    val x9939 = withCtrl(x9943) { OpDef(op=BitAnd, inputs=List(x9937, x9938)).name("x9939").srcCtx("UnrollingBase.scala:28:66") } // And(x9937,x9938)
    val x9940_x9940 = withCtrl(x9943) { ReadMem(x9917).name("x9940_x9940").srcCtx("lenet_loops.scala:123:15") } // ParStreamRead(x9917,List(x9939))
    val x9941_x9941 = withCtrl(x9943) { x9940_x9940 } // VectorApply(x9940,0)
    val x9942 = withCtrl(x9943) { StoreBanks(List(List(x9912_d0_b0), List(x9912_d1_b0)), List(b5635, b5636, b5660), x9941_x9941).name("x9942").srcCtx("lenet_loops.scala:123:15") } // ParSRAMStore(x9912,List(List(b5635, b5636, b5660)),List(x9941),List(x9939))
    val x9945 = withCtrl(x10326) { Counter(min=Const(0), max=Const(50), step=Const(1), par=2).name("x9945").srcCtx("lenet_loops.scala:124:25") } // CounterNew(Const(0),Const(50),Const(1),Const(2))
    val x9946 = withCtrl(x10326) { CounterChain(List(x9945)).name("x9946").srcCtx("lenet_loops.scala:124:40") } // CounterChainNew(List(x9945))
    val x10141 = withCtrl(x10326) { LoopController(style=MetaPipe, level=OuterControl, cchain=x9946).name("x10141").srcCtx("lenet_loops.scala:124:40") } // UnrolledForeach(List(b5444),x9946,Block(Const(())),List(List(b5672, b5673)),List(List(b5674, b5675)))
    val b5672 = withCtrl(x10141) { CounterIter(x9945, Some(0)).name("b5672") } // b5672
    val b5674 = withCtrl(x10141) { Const(true).name("b5674") } // b5674
    val b5673 = withCtrl(x10141) { CounterIter(x9945, Some(1)).name("b5673") } // b5673
    val b5675 = withCtrl(x10141) { Const(true).name("b5675") } // b5675
    val x9947_d0_b0 = withCtrl(x10141) { SRAM(size=64, banking=Strided(banks=1, stride=8)).name("x9947_d0_b0").srcCtx("lenet_loops.scala:132:39:tmp2_SRAM_conv") } // x9947 = SRAMNew(ArrayBuffer(Const(8), Const(8)))
    isAccum(x9947_d0_b0) = false
    bufferDepthOf(x9947_d0_b0) = 2
    staticDimsOf(x9947_d0_b0) = List(8, 8)
    val x9947_d1_b0 = withCtrl(x10141) { SRAM(size=64, banking=Strided(banks=1, stride=8)).name("x9947_d1_b0").srcCtx("lenet_loops.scala:132:39:tmp2_SRAM_conv") } // x9947 = SRAMNew(ArrayBuffer(Const(8), Const(8)))
    isAccum(x9947_d1_b0) = true
    bufferDepthOf(x9947_d1_b0) = 1
    staticDimsOf(x9947_d1_b0) = List(8, 8)
    val x9948_d0_b0 = withCtrl(x10141) { SRAM(size=64, banking=Strided(banks=1, stride=8)).name("x9948_d0_b0").srcCtx("lenet_loops.scala:132:39:tmp2_SRAM_conv") } // x9948 = SRAMNew(ArrayBuffer(Const(8), Const(8)))
    isAccum(x9948_d0_b0) = false
    bufferDepthOf(x9948_d0_b0) = 2
    staticDimsOf(x9948_d0_b0) = List(8, 8)
    val x9948_d1_b0 = withCtrl(x10141) { SRAM(size=64, banking=Strided(banks=1, stride=8)).name("x9948_d1_b0").srcCtx("lenet_loops.scala:132:39:tmp2_SRAM_conv") } // x9948 = SRAMNew(ArrayBuffer(Const(8), Const(8)))
    isAccum(x9948_d1_b0) = true
    bufferDepthOf(x9948_d1_b0) = 1
    staticDimsOf(x9948_d1_b0) = List(8, 8)
    val x10067 = withCtrl(x10141) { UnitController(style=ForkJoin, level=OuterControl).name("x10067").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b5444),Block(Const(())))
    val x9949 = withCtrl(x10067) { Counter(min=Const(0), max=Const(20), step=Const(1), par=1).name("x9949").srcCtx("lenet_loops.scala:133:44") } // CounterNew(Const(0),Const(20),Const(1),Const(1))
    val x9950 = withCtrl(x10067) { CounterChain(List(x9949)).name("x9950").srcCtx("lenet_loops.scala:142:12") } // CounterChainNew(List(x9949))
    val x10007 = withCtrl(x10067) { LoopController(style=MetaPipe, level=OuterControl, cchain=x9950).name("x10007").srcCtx("lenet_loops.scala:142:12") } // UnrolledReduce(List(b5674, b5444),x9950,x9947,Block((x9947) => Const(())),List(List(b5688)),List(List(b5689)))
    val b5688 = withCtrl(x10007) { CounterIter(x9949, Some(0)).name("b5688") } // b5688
    val b5689 = withCtrl(x10007) { Const(true).name("b5689") } // b5689
    val x9951_d0_b0 = withCtrl(x10007) { SRAM(size=64, banking=Strided(banks=1, stride=8)).name("x9951_d0_b0").srcCtx("lenet_loops.scala:134:33:result") } // x9951 = SRAMNew(ArrayBuffer(Const(8), Const(8)))
    isAccum(x9951_d0_b0) = false
    bufferDepthOf(x9951_d0_b0) = 2
    staticDimsOf(x9951_d0_b0) = List(8, 8)
    val x9952 = withCtrl(x10007) { Counter(min=Const(0), max=Const(8), step=Const(1), par=1).name("x9952").srcCtx("lenet_loops.scala:135:44") } // CounterNew(Const(0),Const(8),Const(1),Const(1))
    val x9953 = withCtrl(x10007) { Counter(min=Const(0), max=Const(8), step=Const(1), par=1).name("x9953").srcCtx("lenet_loops.scala:135:23") } // CounterNew(Const(0),Const(8),Const(1),Const(1))
    val x9954 = withCtrl(x10007) { CounterChain(List(x9953,x9952)).name("x9954").srcCtx("lenet_loops.scala:135:51") } // CounterChainNew(List(x9953, x9952))
    val x9988 = withCtrl(x10007) { LoopController(style=MetaPipe, level=OuterControl, cchain=x9954).name("x9988").srcCtx("lenet_loops.scala:135:51") } // UnrolledForeach(List(b5689, b5674, b5444),x9954,Block(Const(())),List(List(b5694), List(b5695)),List(List(b5696), List(b5697)))
    val b5694 = withCtrl(x9988) { CounterIter(x9953, Some(0)).name("b5694") } // b5694
    val b5696 = withCtrl(x9988) { Const(true).name("b5696") } // b5696
    val b5695 = withCtrl(x9988) { CounterIter(x9952, Some(0)).name("b5695") } // b5695
    val b5697 = withCtrl(x9988) { Const(true).name("b5697") } // b5697
    val x9955_d0 = withCtrl(x9988) { Reg(init=Some(0.0)).name("x9955_d0").srcCtx("lenet_loops.scala:136:41:window") } // x9955 = RegNew(Const(0))
    isAccum(x9955_d0) = false
    bufferDepthOf(x9955_d0) = 2
    val x9955_d1 = withCtrl(x9988) { Reg(init=Some(0.0)).name("x9955_d1").srcCtx("lenet_loops.scala:136:41:window") } // x9955 = RegNew(Const(0))
    isAccum(x9955_d1) = true
    bufferDepthOf(x9955_d1) = 1
    val x9956 = withCtrl(x9988) { Counter(min=Const(0), max=Const(5), step=Const(1), par=5).name("x9956").srcCtx("lenet_loops.scala:136:81") } // CounterNew(Const(0),Const(5),Const(1),Const(5))
    val x9957 = withCtrl(x9988) { Counter(min=Const(0), max=Const(5), step=Const(1), par=1).name("x9957").srcCtx("lenet_loops.scala:136:63") } // CounterNew(Const(0),Const(5),Const(1),Const(1))
    val x9958 = withCtrl(x9988) { CounterChain(List(x9957,x9956)).name("x9958").srcCtx("lenet_loops.scala:138:16") } // CounterChainNew(List(x9957, x9956))
    val x9980 = withCtrl(x9988) { LoopController(style=InnerPipe, level=InnerControl, cchain=x9958).name("x9980").srcCtx("lenet_loops.scala:138:16") } // UnrolledReduce(List(b5696, b5697, b5689, b5674, b5444),x9958,x9955,Block((x9955) => Const(())),List(List(b5702), List(b5703)),List(List(b5704), List(b5705)))
    val b5702 = withCtrl(x9980) { CounterIter(x9957, Some(0)).name("b5702") } // b5702
    val b5704 = withCtrl(x9980) { Const(true).name("b5704") } // b5704
    val b5703 = withCtrl(x9980) { CounterIter(x9956, None).name("b5703") } // b5703
    val b5705 = withCtrl(x9980) { Const(true).name("b5705") } // b5705
    val x9959 = withCtrl(x9980) { OpDef(op=FixAdd, inputs=List(b5694, b5702)).name("x9959").srcCtx("lenet_loops.scala:137:35") } // FixAdd(b5694,b5702)
    val x9960 = withCtrl(x9980) { OpDef(op=FixAdd, inputs=List(b5695, b5703)).name("x9960").srcCtx("lenet_loops.scala:137:39") } // FixAdd(b5695,b5703)
    val x9961 = withCtrl(x9980) { OpDef(op=BitAnd, inputs=List(b5704, b5705)).name("x9961").srcCtx("UnrollingBase.scala:28:66") } // And(b5704,b5705)
    def split1 = {
    val x9962 = withCtrl(x9980) { OpDef(op=BitAnd, inputs=List(b5696, b5697)).name("x9962").srcCtx("UnrollingBase.scala:28:66") } // And(b5696,b5697)
    val x9963 = withCtrl(x9980) { OpDef(op=BitAnd, inputs=List(b5689, b5674)).name("x9963").srcCtx("UnrollingBase.scala:28:66") } // And(b5689,b5674)
    val x9964 = withCtrl(x9980) { OpDef(op=BitAnd, inputs=List(x9961, x9962)).name("x9964").srcCtx("UnrollingBase.scala:28:66") } // And(x9961,x9962)
    val x9965 = withCtrl(x9980) { OpDef(op=BitAnd, inputs=List(x9963, b5444)).name("x9965").srcCtx("UnrollingBase.scala:28:66") } // And(x9963,b5444)
    val x9966 = withCtrl(x9980) { OpDef(op=BitAnd, inputs=List(x9964, x9965)).name("x9966").srcCtx("UnrollingBase.scala:28:66") } // And(x9964,x9965)
    val x9967 = withCtrl(x9980) { LoadBanks(List(x9796_d0_b0), List(b5688, x9959, x9960)).name("x9967").srcCtx("lenet_loops.scala:137:26") } // ParSRAMLoad(x9796,List(List(b5688, x9959, x9960)),List(x9966))
    val x9968 = withCtrl(x9980) { x9967 } // VectorApply(x9967,0)
    val x9969 = withCtrl(x9980) { LoadBanks(List(x9912_d0_b0), List(b5688, b5702, b5703)).name("x9969").srcCtx("lenet_loops.scala:137:50") } // ParSRAMLoad(x9912,List(List(b5688, b5702, b5703)),List(x9966))
    val x9970 = withCtrl(x9980) { x9969 } // VectorApply(x9969,0)
    val x9971 = withCtrl(x9980) { OpDef(op=FixMul, inputs=List(x9968, x9970)).name("x9971").srcCtx("lenet_loops.scala:137:43") } // FixMul(x9968,x9970)
    val x9972 = withCtrl(x9980) { ReadMem(x9955_d1).name("x9972").srcCtx("lenet_loops.scala:138:16") } // RegRead(x9955)
    val x9973 = withCtrl(x9980) { OpDef(op=FixEql, inputs=List(b5702, Const(0))).name("x9973").srcCtx("lenet_loops.scala:138:16") } // FixEql(b5702,Const(0))
    val x9974 = withCtrl(x9980) { OpDef(op=FixEql, inputs=List(b5703, Const(0))).name("x9974").srcCtx("lenet_loops.scala:138:16") } // FixEql(b5703,Const(0))
    val x9975 = withCtrl(x9980) { OpDef(op=BitAnd, inputs=List(x9973, x9974)).name("x9975").srcCtx("lenet_loops.scala:138:16") } // And(x9973,x9974)
    val x9976 = withCtrl(x9980) { ReduceAccumOp(op=FixAdd, input=x9971, accum=x9972).name("x9976").srcCtx("lenet_loops.scala:138:18") } // FixAdd(x9971,x9972)
    val x9977 = withCtrl(x9980) { OpDef(op=BitAnd, inputs=List(x9962, x9963)).name("x9977").srcCtx("UnrollingBase.scala:28:66") } // And(x9962,x9963)
    val x9978 = withCtrl(x9980) { OpDef(op=BitAnd, inputs=List(x9977, b5444)).name("x9978").srcCtx("UnrollingBase.scala:28:66") } // And(x9977,b5444)
    val x9979_x9955_d0 = withCtrl(x9980) { WriteMem(x9955_d0, x9976).name("x9979_x9955_d0").srcCtx("lenet_loops.scala:138:16") } // RegWrite(x9955,x9976,x9978)
    antiDepsOf(x9979_x9955_d0)=List(x9972)
    val x9979_x9955_d1 = withCtrl(x9980) { WriteMem(x9955_d1, x9976).name("x9979_x9955_d1").srcCtx("lenet_loops.scala:138:16") } // RegWrite(x9955,x9976,x9978)
    antiDepsOf(x9979_x9955_d1)=List(x9972)
    val x9987 = withCtrl(x9988) { UnitController(style=SeqPipe, level=InnerControl).name("x9987").srcCtx("lenet_loops.scala:135:51") } // UnitPipe(List(b5696, b5697, b5689, b5674, b5444),Block(Const(())))
    val x9981 = withCtrl(x9987) { ReadMem(x9955_d0).name("x9981").srcCtx("lenet_loops.scala:139:37") } // RegRead(x9955)
    val x9982 = withCtrl(x9987) { OpDef(op=BitAnd, inputs=List(b5696, b5697)).name("x9982").srcCtx("UnrollingBase.scala:28:66") } // And(b5696,b5697)
    val x9983 = withCtrl(x9987) { OpDef(op=BitAnd, inputs=List(b5689, b5674)).name("x9983").srcCtx("UnrollingBase.scala:28:66") } // And(b5689,b5674)
    val x9984 = withCtrl(x9987) { OpDef(op=BitAnd, inputs=List(x9982, x9983)).name("x9984").srcCtx("UnrollingBase.scala:28:66") } // And(x9982,x9983)
    val x9985 = withCtrl(x9987) { OpDef(op=BitAnd, inputs=List(x9984, b5444)).name("x9985").srcCtx("UnrollingBase.scala:28:66") } // And(x9984,b5444)
    val x9986 = withCtrl(x9987) { StoreBanks(List(List(x9951_d0_b0)), List(b5694, b5695), x9981).name("x9986").srcCtx("lenet_loops.scala:139:28") } // SRAMStore(x9951,ArrayBuffer(Const(8), Const(8)),List(b5694, b5695),Const(0),x9981,x9985)
    val x9989 = withCtrl(x10007) { Counter(min=Const(0), max=Const(8), step=Const(1), par=1).name("x9989").srcCtx("lenet_loops.scala:142:12") } // CounterNew(Const(0),Const(8),Const(1),Const(1))
    val x9990 = withCtrl(x10007) { Counter(min=Const(0), max=Const(8), step=Const(1), par=1).name("x9990").srcCtx("lenet_loops.scala:142:12") } // CounterNew(Const(0),Const(8),Const(1),Const(1))
    val x9991 = withCtrl(x10007) { CounterChain(List(x9990,x9989)).name("x9991").srcCtx("lenet_loops.scala:142:12") } // CounterChainNew(ArrayBuffer(x9990, x9989))
    val x10006 = withCtrl(x10007) { LoopController(style=InnerPipe, level=InnerControl, cchain=x9991).name("x10006").srcCtx("lenet_loops.scala:142:12") } // UnrolledForeach(List(),x9991,Block(Const(())),ArrayBuffer(List(b5736), List(b5737)),ArrayBuffer(List(b5738), List(b5739)))
    val b5736 = withCtrl(x10006) { CounterIter(x9990, Some(0)).name("b5736") } // b5736
    val b5738 = withCtrl(x10006) { Const(true).name("b5738") } // b5738
    val b5737 = withCtrl(x10006) { CounterIter(x9989, None).name("b5737") } // b5737
    val b5739 = withCtrl(x10006) { Const(true).name("b5739") } // b5739
    val x9992 = withCtrl(x10006) { OpDef(op=BitAnd, inputs=List(b5738, b5739)).name("x9992").srcCtx("UnrollingBase.scala:28:66") } // And(b5738,b5739)
    val x9993 = withCtrl(x10006) { OpDef(op=BitAnd, inputs=List(b5674, b5444)).name("x9993").srcCtx("UnrollingBase.scala:28:66") } // And(b5674,b5444)
    val x9994 = withCtrl(x10006) { OpDef(op=BitAnd, inputs=List(x9992, x9993)).name("x9994").srcCtx("UnrollingBase.scala:28:66") } // And(x9992,x9993)
    val x9995 = withCtrl(x10006) { LoadBanks(List(x9951_d0_b0), List(b5736, b5737)).name("x9995").srcCtx("lenet_loops.scala:142:12") } // ParSRAMLoad(x9951,List(ArrayBuffer(b5736, b5737)),List(x9994))
    val x9996 = withCtrl(x10006) { x9995 } // VectorApply(x9995,0)
    val x9997 = withCtrl(x10006) { LoadBanks(List(x9947_d1_b0), List(b5736, b5737)).name("x9997").srcCtx("lenet_loops.scala:142:12") } // ParSRAMLoad(x9947,List(ArrayBuffer(b5736, b5737)),List(x9994))
    val x9998 = withCtrl(x10006) { x9997 } // VectorApply(x9997,0)
    val x9999 = withCtrl(x10006) { OpDef(op=BitAnd, inputs=List(b5689, b5674)).name("x9999").srcCtx("lenet_loops.scala:142:12") } // And(b5689,b5674)
    val x10000 = withCtrl(x10006) { OpDef(op=BitAnd, inputs=List(x9999, b5444)).name("x10000").srcCtx("lenet_loops.scala:142:12") } // And(x9999,b5444)
    val x10001 = withCtrl(x10006) { OpDef(op=BitAnd, inputs=List(x10000, x9994)).name("x10001").srcCtx("lenet_loops.scala:142:12") } // And(x10000,x9994)
    val x10002 = withCtrl(x10006) { OpDef(op=FixEql, inputs=List(b5688, Const(0))).name("x10002").srcCtx("lenet_loops.scala:142:12") } // FixEql(b5688,Const(0))
    val x10003 = withCtrl(x10006) { OpDef(op=FixAdd, inputs=List(x9996, x9998)).name("x10003").srcCtx("lenet_loops.scala:142:14") } // FixAdd(x9996,x9998)
    val x10004 = withCtrl(x10006) { OpDef(op=MuxOp, inputs=List(x10002, x9996, x10003)).name("x10004").srcCtx("lenet_loops.scala:142:12") } // Mux(x10002,x9996,x10003)
    val x10005 = withCtrl(x10006) { StoreBanks(List(List(x9947_d0_b0), List(x9947_d1_b0)), List(b5736, b5737), x10004).name("x10005").srcCtx("lenet_loops.scala:142:12") } // ParSRAMStore(x9947,List(ArrayBuffer(b5736, b5737)),List(x10004),List(x9994))
    antiDepsOf(x10005)=List(x9997)
    val x10008 = withCtrl(x10067) { Counter(min=Const(0), max=Const(20), step=Const(1), par=1).name("x10008").srcCtx("lenet_loops.scala:133:44") } // CounterNew(Const(0),Const(20),Const(1),Const(1))
    val x10009 = withCtrl(x10067) { CounterChain(List(x10008)).name("x10009").srcCtx("lenet_loops.scala:142:12") } // CounterChainNew(List(x10008))
    val x10066 = withCtrl(x10067) { LoopController(style=MetaPipe, level=OuterControl, cchain=x10009).name("x10066").srcCtx("lenet_loops.scala:142:12") } // UnrolledReduce(List(b5675, b5444),x10009,x9948,Block((x9948) => Const(())),List(List(b5756)),List(List(b5757)))
    val b5756 = withCtrl(x10066) { CounterIter(x10008, Some(0)).name("b5756") } // b5756
    val b5757 = withCtrl(x10066) { Const(true).name("b5757") } // b5757
    val x10010_d0_b0 = withCtrl(x10066) { SRAM(size=64, banking=Strided(banks=1, stride=8)).name("x10010_d0_b0").srcCtx("lenet_loops.scala:134:33:result") } // x10010 = SRAMNew(ArrayBuffer(Const(8), Const(8)))
    isAccum(x10010_d0_b0) = false
    bufferDepthOf(x10010_d0_b0) = 2
    staticDimsOf(x10010_d0_b0) = List(8, 8)
    val x10011 = withCtrl(x10066) { Counter(min=Const(0), max=Const(8), step=Const(1), par=1).name("x10011").srcCtx("lenet_loops.scala:135:44") } // CounterNew(Const(0),Const(8),Const(1),Const(1))
    val x10012 = withCtrl(x10066) { Counter(min=Const(0), max=Const(8), step=Const(1), par=1).name("x10012").srcCtx("lenet_loops.scala:135:23") } // CounterNew(Const(0),Const(8),Const(1),Const(1))
    val x10013 = withCtrl(x10066) { CounterChain(List(x10012,x10011)).name("x10013").srcCtx("lenet_loops.scala:135:51") } // CounterChainNew(List(x10012, x10011))
    val x10047 = withCtrl(x10066) { LoopController(style=MetaPipe, level=OuterControl, cchain=x10013).name("x10047").srcCtx("lenet_loops.scala:135:51") } // UnrolledForeach(List(b5757, b5675, b5444),x10013,Block(Const(())),List(List(b5762), List(b5763)),List(List(b5764), List(b5765)))
    val b5762 = withCtrl(x10047) { CounterIter(x10012, Some(0)).name("b5762") } // b5762
    val b5764 = withCtrl(x10047) { Const(true).name("b5764") } // b5764
    val b5763 = withCtrl(x10047) { CounterIter(x10011, Some(0)).name("b5763") } // b5763
    val b5765 = withCtrl(x10047) { Const(true).name("b5765") } // b5765
    val x10014_d0 = withCtrl(x10047) { Reg(init=Some(0.0)).name("x10014_d0").srcCtx("lenet_loops.scala:136:41:window") } // x10014 = RegNew(Const(0))
    isAccum(x10014_d0) = false
    bufferDepthOf(x10014_d0) = 2
    val x10014_d1 = withCtrl(x10047) { Reg(init=Some(0.0)).name("x10014_d1").srcCtx("lenet_loops.scala:136:41:window") } // x10014 = RegNew(Const(0))
    isAccum(x10014_d1) = true
    bufferDepthOf(x10014_d1) = 1
    val x10015 = withCtrl(x10047) { Counter(min=Const(0), max=Const(5), step=Const(1), par=5).name("x10015").srcCtx("lenet_loops.scala:136:81") } // CounterNew(Const(0),Const(5),Const(1),Const(5))
    val x10016 = withCtrl(x10047) { Counter(min=Const(0), max=Const(5), step=Const(1), par=1).name("x10016").srcCtx("lenet_loops.scala:136:63") } // CounterNew(Const(0),Const(5),Const(1),Const(1))
    val x10017 = withCtrl(x10047) { CounterChain(List(x10016,x10015)).name("x10017").srcCtx("lenet_loops.scala:138:16") } // CounterChainNew(List(x10016, x10015))
    val x10039 = withCtrl(x10047) { LoopController(style=InnerPipe, level=InnerControl, cchain=x10017).name("x10039").srcCtx("lenet_loops.scala:138:16") } // UnrolledReduce(List(b5764, b5765, b5757, b5675, b5444),x10017,x10014,Block((x10014) => Const(())),List(List(b5770), List(b5771)),List(List(b5772), List(b5773)))
    val b5770 = withCtrl(x10039) { CounterIter(x10016, Some(0)).name("b5770") } // b5770
    val b5772 = withCtrl(x10039) { Const(true).name("b5772") } // b5772
    val b5771 = withCtrl(x10039) { CounterIter(x10015, None).name("b5771") } // b5771
    val b5773 = withCtrl(x10039) { Const(true).name("b5773") } // b5773
    val x10018 = withCtrl(x10039) { OpDef(op=FixAdd, inputs=List(b5762, b5770)).name("x10018").srcCtx("lenet_loops.scala:137:35") } // FixAdd(b5762,b5770)
    val x10019 = withCtrl(x10039) { OpDef(op=FixAdd, inputs=List(b5763, b5771)).name("x10019").srcCtx("lenet_loops.scala:137:39") } // FixAdd(b5763,b5771)
    val x10020 = withCtrl(x10039) { OpDef(op=BitAnd, inputs=List(b5772, b5773)).name("x10020").srcCtx("UnrollingBase.scala:28:66") } // And(b5772,b5773)
    val x10021 = withCtrl(x10039) { OpDef(op=BitAnd, inputs=List(b5764, b5765)).name("x10021").srcCtx("UnrollingBase.scala:28:66") } // And(b5764,b5765)
    val x10022 = withCtrl(x10039) { OpDef(op=BitAnd, inputs=List(b5757, b5675)).name("x10022").srcCtx("UnrollingBase.scala:28:66") } // And(b5757,b5675)
    val x10023 = withCtrl(x10039) { OpDef(op=BitAnd, inputs=List(x10020, x10021)).name("x10023").srcCtx("UnrollingBase.scala:28:66") } // And(x10020,x10021)
    val x10024 = withCtrl(x10039) { OpDef(op=BitAnd, inputs=List(x10022, b5444)).name("x10024").srcCtx("UnrollingBase.scala:28:66") } // And(x10022,b5444)
    val x10025 = withCtrl(x10039) { OpDef(op=BitAnd, inputs=List(x10023, x10024)).name("x10025").srcCtx("UnrollingBase.scala:28:66") } // And(x10023,x10024)
    val x10026 = withCtrl(x10039) { LoadBanks(List(x9796_d1_b0), List(b5756, x10018, x10019)).name("x10026").srcCtx("lenet_loops.scala:137:26") } // ParSRAMLoad(x9796,List(List(b5756, x10018, x10019)),List(x10025))
    val x10027 = withCtrl(x10039) { x10026 } // VectorApply(x10026,0)
    val x10028 = withCtrl(x10039) { LoadBanks(List(x9912_d1_b0), List(b5756, b5770, b5771)).name("x10028").srcCtx("lenet_loops.scala:137:50") } // ParSRAMLoad(x9912,List(List(b5756, b5770, b5771)),List(x10025))
    val x10029 = withCtrl(x10039) { x10028 } // VectorApply(x10028,0)
    val x10030 = withCtrl(x10039) { OpDef(op=FixMul, inputs=List(x10027, x10029)).name("x10030").srcCtx("lenet_loops.scala:137:43") } // FixMul(x10027,x10029)
    val x10031 = withCtrl(x10039) { ReadMem(x10014_d1).name("x10031").srcCtx("lenet_loops.scala:138:16") } // RegRead(x10014)
    val x10032 = withCtrl(x10039) { OpDef(op=FixEql, inputs=List(b5770, Const(0))).name("x10032").srcCtx("lenet_loops.scala:138:16") } // FixEql(b5770,Const(0))
    val x10033 = withCtrl(x10039) { OpDef(op=FixEql, inputs=List(b5771, Const(0))).name("x10033").srcCtx("lenet_loops.scala:138:16") } // FixEql(b5771,Const(0))
    val x10034 = withCtrl(x10039) { OpDef(op=BitAnd, inputs=List(x10032, x10033)).name("x10034").srcCtx("lenet_loops.scala:138:16") } // And(x10032,x10033)
    val x10035 = withCtrl(x10039) { ReduceAccumOp(op=FixAdd, input=x10030, accum=x10031).name("x10035").srcCtx("lenet_loops.scala:138:18") } // FixAdd(x10030,x10031)
    val x10036 = withCtrl(x10039) { OpDef(op=BitAnd, inputs=List(x10021, x10022)).name("x10036").srcCtx("UnrollingBase.scala:28:66") } // And(x10021,x10022)
    val x10037 = withCtrl(x10039) { OpDef(op=BitAnd, inputs=List(x10036, b5444)).name("x10037").srcCtx("UnrollingBase.scala:28:66") } // And(x10036,b5444)
    val x10038_x10014_d0 = withCtrl(x10039) { WriteMem(x10014_d0, x10035).name("x10038_x10014_d0").srcCtx("lenet_loops.scala:138:16") } // RegWrite(x10014,x10035,x10037)
    antiDepsOf(x10038_x10014_d0)=List(x10031)
    val x10038_x10014_d1 = withCtrl(x10039) { WriteMem(x10014_d1, x10035).name("x10038_x10014_d1").srcCtx("lenet_loops.scala:138:16") } // RegWrite(x10014,x10035,x10037)
    antiDepsOf(x10038_x10014_d1)=List(x10031)
    val x10046 = withCtrl(x10047) { UnitController(style=SeqPipe, level=InnerControl).name("x10046").srcCtx("lenet_loops.scala:135:51") } // UnitPipe(List(b5764, b5765, b5757, b5675, b5444),Block(Const(())))
    val x10040 = withCtrl(x10046) { ReadMem(x10014_d0).name("x10040").srcCtx("lenet_loops.scala:139:37") } // RegRead(x10014)
    val x10041 = withCtrl(x10046) { OpDef(op=BitAnd, inputs=List(b5764, b5765)).name("x10041").srcCtx("UnrollingBase.scala:28:66") } // And(b5764,b5765)
    val x10042 = withCtrl(x10046) { OpDef(op=BitAnd, inputs=List(b5757, b5675)).name("x10042").srcCtx("UnrollingBase.scala:28:66") } // And(b5757,b5675)
    val x10043 = withCtrl(x10046) { OpDef(op=BitAnd, inputs=List(x10041, x10042)).name("x10043").srcCtx("UnrollingBase.scala:28:66") } // And(x10041,x10042)
    val x10044 = withCtrl(x10046) { OpDef(op=BitAnd, inputs=List(x10043, b5444)).name("x10044").srcCtx("UnrollingBase.scala:28:66") } // And(x10043,b5444)
    val x10045 = withCtrl(x10046) { StoreBanks(List(List(x10010_d0_b0)), List(b5762, b5763), x10040).name("x10045").srcCtx("lenet_loops.scala:139:28") } // SRAMStore(x10010,ArrayBuffer(Const(8), Const(8)),List(b5762, b5763),Const(0),x10040,x10044)
    val x10048 = withCtrl(x10066) { Counter(min=Const(0), max=Const(8), step=Const(1), par=1).name("x10048").srcCtx("lenet_loops.scala:142:12") } // CounterNew(Const(0),Const(8),Const(1),Const(1))
    val x10049 = withCtrl(x10066) { Counter(min=Const(0), max=Const(8), step=Const(1), par=1).name("x10049").srcCtx("lenet_loops.scala:142:12") } // CounterNew(Const(0),Const(8),Const(1),Const(1))
    val x10050 = withCtrl(x10066) { CounterChain(List(x10049,x10048)).name("x10050").srcCtx("lenet_loops.scala:142:12") } // CounterChainNew(ArrayBuffer(x10049, x10048))
    val x10065 = withCtrl(x10066) { LoopController(style=InnerPipe, level=InnerControl, cchain=x10050).name("x10065").srcCtx("lenet_loops.scala:142:12") } // UnrolledForeach(List(),x10050,Block(Const(())),ArrayBuffer(List(b5804), List(b5805)),ArrayBuffer(List(b5806), List(b5807)))
    val b5804 = withCtrl(x10065) { CounterIter(x10049, Some(0)).name("b5804") } // b5804
    val b5806 = withCtrl(x10065) { Const(true).name("b5806") } // b5806
    val b5805 = withCtrl(x10065) { CounterIter(x10048, None).name("b5805") } // b5805
    val b5807 = withCtrl(x10065) { Const(true).name("b5807") } // b5807
    val x10051 = withCtrl(x10065) { OpDef(op=BitAnd, inputs=List(b5806, b5807)).name("x10051").srcCtx("UnrollingBase.scala:28:66") } // And(b5806,b5807)
    val x10052 = withCtrl(x10065) { OpDef(op=BitAnd, inputs=List(b5675, b5444)).name("x10052").srcCtx("UnrollingBase.scala:28:66") } // And(b5675,b5444)
    val x10053 = withCtrl(x10065) { OpDef(op=BitAnd, inputs=List(x10051, x10052)).name("x10053").srcCtx("UnrollingBase.scala:28:66") } // And(x10051,x10052)
    val x10054 = withCtrl(x10065) { LoadBanks(List(x10010_d0_b0), List(b5804, b5805)).name("x10054").srcCtx("lenet_loops.scala:142:12") } // ParSRAMLoad(x10010,List(ArrayBuffer(b5804, b5805)),List(x10053))
    val x10055 = withCtrl(x10065) { x10054 } // VectorApply(x10054,0)
    val x10056 = withCtrl(x10065) { LoadBanks(List(x9948_d1_b0), List(b5804, b5805)).name("x10056").srcCtx("lenet_loops.scala:142:12") } // ParSRAMLoad(x9948,List(ArrayBuffer(b5804, b5805)),List(x10053))
    val x10057 = withCtrl(x10065) { x10056 } // VectorApply(x10056,0)
    val x10058 = withCtrl(x10065) { OpDef(op=BitAnd, inputs=List(b5757, b5675)).name("x10058").srcCtx("lenet_loops.scala:142:12") } // And(b5757,b5675)
    val x10059 = withCtrl(x10065) { OpDef(op=BitAnd, inputs=List(x10058, b5444)).name("x10059").srcCtx("lenet_loops.scala:142:12") } // And(x10058,b5444)
    val x10060 = withCtrl(x10065) { OpDef(op=BitAnd, inputs=List(x10059, x10053)).name("x10060").srcCtx("lenet_loops.scala:142:12") } // And(x10059,x10053)
    val x10061 = withCtrl(x10065) { OpDef(op=FixEql, inputs=List(b5756, Const(0))).name("x10061").srcCtx("lenet_loops.scala:142:12") } // FixEql(b5756,Const(0))
    val x10062 = withCtrl(x10065) { OpDef(op=FixAdd, inputs=List(x10055, x10057)).name("x10062").srcCtx("lenet_loops.scala:142:14") } // FixAdd(x10055,x10057)
    val x10063 = withCtrl(x10065) { OpDef(op=MuxOp, inputs=List(x10061, x10055, x10062)).name("x10063").srcCtx("lenet_loops.scala:142:12") } // Mux(x10061,x10055,x10062)
    val x10064 = withCtrl(x10065) { StoreBanks(List(List(x9948_d0_b0), List(x9948_d1_b0)), List(b5804, b5805), x10063).name("x10064").srcCtx("lenet_loops.scala:142:12") } // ParSRAMStore(x9948,List(ArrayBuffer(b5804, b5805)),List(x10063),List(x10053))
    antiDepsOf(x10064)=List(x10056)
    val x10140 = withCtrl(x10141) { UnitController(style=ForkJoin, level=OuterControl).name("x10140").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b5444),Block(Const(())))
    val x10068 = withCtrl(x10140) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x10068").srcCtx("lenet_loops.scala:144:29") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x10069 = withCtrl(x10140) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x10069").srcCtx("lenet_loops.scala:144:21") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x10070 = withCtrl(x10140) { CounterChain(List(x10069,x10068)).name("x10070").srcCtx("lenet_loops.scala:144:35") } // CounterChainNew(List(x10069, x10068))
    val x10103 = withCtrl(x10140) { LoopController(style=MetaPipe, level=OuterControl, cchain=x10070).name("x10103").srcCtx("lenet_loops.scala:144:35") } // UnrolledForeach(List(b5674, b5444),x10070,Block(Const(())),List(List(b5831), List(b5832)),List(List(b5833), List(b5834)))
    val b5831 = withCtrl(x10103) { CounterIter(x10069, Some(0)).name("b5831") } // b5831
    val b5833 = withCtrl(x10103) { Const(true).name("b5833") } // b5833
    val b5832 = withCtrl(x10103) { CounterIter(x10068, Some(0)).name("b5832") } // b5832
    val b5834 = withCtrl(x10103) { Const(true).name("b5834") } // b5834
    val x10071_d0 = withCtrl(x10103) { Reg(init=Some(0.0)).name("x10071_d0").srcCtx("lenet_loops.scala:145:36:out") } // x10071 = RegNew(Const(0))
    isAccum(x10071_d0) = false
    bufferDepthOf(x10071_d0) = 2
    val x10071_d1 = withCtrl(x10103) { Reg(init=Some(0.0)).name("x10071_d1").srcCtx("lenet_loops.scala:145:36:out") } // x10071 = RegNew(Const(0))
    isAccum(x10071_d1) = true
    bufferDepthOf(x10071_d1) = 1
    val x10072 = withCtrl(x10103) { Counter(min=Const(0), max=Const(2), step=Const(2), par=1).name("x10072").srcCtx("lenet_loops.scala:145:67") } // CounterNew(Const(0),Const(2),Const(2),Const(1))
    val x10073 = withCtrl(x10103) { Counter(min=Const(0), max=Const(2), step=Const(2), par=1).name("x10073").srcCtx("lenet_loops.scala:145:54") } // CounterNew(Const(0),Const(2),Const(2),Const(1))
    val x10074 = withCtrl(x10103) { CounterChain(List(x10073,x10072)).name("x10074").srcCtx("lenet_loops.scala:147:15") } // CounterChainNew(List(x10073, x10072))
    val x10096 = withCtrl(x10103) { LoopController(style=InnerPipe, level=InnerControl, cchain=x10074).name("x10096").srcCtx("lenet_loops.scala:147:15") } // UnrolledReduce(List(b5833, b5834, b5674, b5444),x10074,x10071,Block((x10071) => Const(())),List(List(b5839), List(b5840)),List(List(b5841), List(b5842)))
    val b5839 = withCtrl(x10096) { CounterIter(x10073, Some(0)).name("b5839") } // b5839
    val b5841 = withCtrl(x10096) { Const(true).name("b5841") } // b5841
    val b5840 = withCtrl(x10096) { CounterIter(x10072, None).name("b5840") } // b5840
    val b5842 = withCtrl(x10096) { Const(true).name("b5842") } // b5842
    val x10075 = withCtrl(x10096) { OpDef(op=FixSla, inputs=List(b5831, Const(1))).name("x10075").srcCtx("lenet_loops.scala:146:44") } // FixLsh(b5831,Const(1))
    val x10076 = withCtrl(x10096) { OpDef(op=FixAdd, inputs=List(x10075, b5839)).name("x10076").srcCtx("lenet_loops.scala:146:47") } // FixAdd(x10075,b5839)
    val x10077 = withCtrl(x10096) { OpDef(op=FixSla, inputs=List(b5832, Const(1))).name("x10077").srcCtx("lenet_loops.scala:146:54") } // FixLsh(b5832,Const(1))
    val x10078 = withCtrl(x10096) { OpDef(op=FixAdd, inputs=List(x10077, b5840)).name("x10078").srcCtx("lenet_loops.scala:146:57") } // FixAdd(x10077,b5840)
    val x10079 = withCtrl(x10096) { OpDef(op=BitAnd, inputs=List(b5841, b5842)).name("x10079").srcCtx("UnrollingBase.scala:28:66") } // And(b5841,b5842)
    val x10080 = withCtrl(x10096) { OpDef(op=BitAnd, inputs=List(b5833, b5834)).name("x10080").srcCtx("UnrollingBase.scala:28:66") } // And(b5833,b5834)
    val x10081 = withCtrl(x10096) { OpDef(op=BitAnd, inputs=List(b5674, b5444)).name("x10081").srcCtx("UnrollingBase.scala:28:66") } // And(b5674,b5444)
    val x10082 = withCtrl(x10096) { OpDef(op=BitAnd, inputs=List(x10079, x10080)).name("x10082").srcCtx("UnrollingBase.scala:28:66") } // And(x10079,x10080)
    val x10083 = withCtrl(x10096) { OpDef(op=BitAnd, inputs=List(x10082, x10081)).name("x10083").srcCtx("UnrollingBase.scala:28:66") } // And(x10082,x10081)
    val x10084 = withCtrl(x10096) { LoadBanks(List(x9947_d0_b0), List(x10076, x10078)).name("x10084").srcCtx("lenet_loops.scala:146:42") } // ParSRAMLoad(x9947,List(List(x10076, x10078)),List(x10083))
    val x10085 = withCtrl(x10096) { x10084 } // VectorApply(x10084,0)
    val x10086 = withCtrl(x10096) { LoadBanks(List(x9695_d0_b0), List(b5672)).name("x10086").srcCtx("lenet_loops.scala:146:72") } // SRAMLoad(x9695,ArrayBuffer(Const(64)),List(b5672),Const(0),x10083)
    val x10087 = withCtrl(x10096) { OpDef(op=FixAdd, inputs=List(x10085, x10086)).name("x10087").srcCtx("lenet_loops.scala:146:63") } // FixAdd(x10085,x10086)
    val x10088 = withCtrl(x10096) { OpDef(op=FixMax, inputs=List(Const(0.0), x10087)).name("x10088").srcCtx("lenet_loops.scala:146:18") } // Max(Const(0),x10087)
    val x10089 = withCtrl(x10096) { ReadMem(x10071_d1).name("x10089").srcCtx("lenet_loops.scala:147:15") } // RegRead(x10071)
    val x10090 = withCtrl(x10096) { OpDef(op=FixEql, inputs=List(b5839, Const(0))).name("x10090").srcCtx("lenet_loops.scala:147:15") } // FixEql(b5839,Const(0))
    val x10091 = withCtrl(x10096) { OpDef(op=FixEql, inputs=List(b5840, Const(0))).name("x10091").srcCtx("lenet_loops.scala:147:15") } // FixEql(b5840,Const(0))
    val x10092 = withCtrl(x10096) { OpDef(op=BitAnd, inputs=List(x10090, x10091)).name("x10092").srcCtx("lenet_loops.scala:147:15") } // And(x10090,x10091)
    val x10093 = withCtrl(x10096) { ReduceAccumOp(op=FixMax, input=x10088, accum=x10089).name("x10093").srcCtx("lenet_loops.scala:147:29") } // Max(x10088,x10089)
    val x10094 = withCtrl(x10096) { OpDef(op=BitAnd, inputs=List(x10080, x10081)).name("x10094").srcCtx("UnrollingBase.scala:28:66") } // And(x10080,x10081)
    val x10095_x10071_d0 = withCtrl(x10096) { WriteMem(x10071_d0, x10093).name("x10095_x10071_d0").srcCtx("lenet_loops.scala:147:15") } // RegWrite(x10071,x10093,x10094)
    antiDepsOf(x10095_x10071_d0)=List(x10089)
    val x10095_x10071_d1 = withCtrl(x10096) { WriteMem(x10071_d1, x10093).name("x10095_x10071_d1").srcCtx("lenet_loops.scala:147:15") } // RegWrite(x10071,x10093,x10094)
    antiDepsOf(x10095_x10071_d1)=List(x10089)
    val x10102 = withCtrl(x10103) { UnitController(style=SeqPipe, level=InnerControl).name("x10102").srcCtx("lenet_loops.scala:144:35") } // UnitPipe(List(b5833, b5834, b5674, b5444),Block(Const(())))
    val x10097 = withCtrl(x10102) { ReadMem(x10071_d0).name("x10097").srcCtx("lenet_loops.scala:148:43") } // RegRead(x10071)
    val x10098 = withCtrl(x10102) { OpDef(op=BitAnd, inputs=List(b5833, b5834)).name("x10098").srcCtx("UnrollingBase.scala:28:66") } // And(b5833,b5834)
    val x10099 = withCtrl(x10102) { OpDef(op=BitAnd, inputs=List(b5674, b5444)).name("x10099").srcCtx("UnrollingBase.scala:28:66") } // And(b5674,b5444)
    val x10100 = withCtrl(x10102) { OpDef(op=BitAnd, inputs=List(x10098, x10099)).name("x10100").srcCtx("UnrollingBase.scala:28:66") } // And(x10098,x10099)
    val x10101 = withCtrl(x10102) { StoreBanks(List(List(x9911_d0_b0)), List(b5672, b5831, b5832), x10097).name("x10101").srcCtx("lenet_loops.scala:148:37") } // SRAMStore(x9911,ArrayBuffer(Const(50), Const(4), Const(4)),List(b5672, b5831, b5832),Const(0),x10097,x10100)
    val x10104 = withCtrl(x10140) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x10104").srcCtx("lenet_loops.scala:144:29") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x10105 = withCtrl(x10140) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x10105").srcCtx("lenet_loops.scala:144:21") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x10106 = withCtrl(x10140) { CounterChain(List(x10105,x10104)).name("x10106").srcCtx("lenet_loops.scala:144:35") } // CounterChainNew(List(x10105, x10104))
    val x10139 = withCtrl(x10140) { LoopController(style=MetaPipe, level=OuterControl, cchain=x10106).name("x10139").srcCtx("lenet_loops.scala:144:35") } // UnrolledForeach(List(b5675, b5444),x10106,Block(Const(())),List(List(b5872), List(b5873)),List(List(b5874), List(b5875)))
    val b5872 = withCtrl(x10139) { CounterIter(x10105, Some(0)).name("b5872") } // b5872
    val b5874 = withCtrl(x10139) { Const(true).name("b5874") } // b5874
    val b5873 = withCtrl(x10139) { CounterIter(x10104, Some(0)).name("b5873") } // b5873
    val b5875 = withCtrl(x10139) { Const(true).name("b5875") } // b5875
    val x10107_d0 = withCtrl(x10139) { Reg(init=Some(0.0)).name("x10107_d0").srcCtx("lenet_loops.scala:145:36:out") } // x10107 = RegNew(Const(0))
    isAccum(x10107_d0) = false
    bufferDepthOf(x10107_d0) = 2
    val x10107_d1 = withCtrl(x10139) { Reg(init=Some(0.0)).name("x10107_d1").srcCtx("lenet_loops.scala:145:36:out") } // x10107 = RegNew(Const(0))
    isAccum(x10107_d1) = true
    bufferDepthOf(x10107_d1) = 1
    val x10108 = withCtrl(x10139) { Counter(min=Const(0), max=Const(2), step=Const(2), par=1).name("x10108").srcCtx("lenet_loops.scala:145:67") } // CounterNew(Const(0),Const(2),Const(2),Const(1))
    val x10109 = withCtrl(x10139) { Counter(min=Const(0), max=Const(2), step=Const(2), par=1).name("x10109").srcCtx("lenet_loops.scala:145:54") } // CounterNew(Const(0),Const(2),Const(2),Const(1))
    val x10110 = withCtrl(x10139) { CounterChain(List(x10109,x10108)).name("x10110").srcCtx("lenet_loops.scala:147:15") } // CounterChainNew(List(x10109, x10108))
    val x10132 = withCtrl(x10139) { LoopController(style=InnerPipe, level=InnerControl, cchain=x10110).name("x10132").srcCtx("lenet_loops.scala:147:15") } // UnrolledReduce(List(b5874, b5875, b5675, b5444),x10110,x10107,Block((x10107) => Const(())),List(List(b5880), List(b5881)),List(List(b5882), List(b5883)))
    val b5880 = withCtrl(x10132) { CounterIter(x10109, Some(0)).name("b5880") } // b5880
    val b5882 = withCtrl(x10132) { Const(true).name("b5882") } // b5882
    val b5881 = withCtrl(x10132) { CounterIter(x10108, None).name("b5881") } // b5881
    val b5883 = withCtrl(x10132) { Const(true).name("b5883") } // b5883
    val x10111 = withCtrl(x10132) { OpDef(op=FixSla, inputs=List(b5872, Const(1))).name("x10111").srcCtx("lenet_loops.scala:146:44") } // FixLsh(b5872,Const(1))
    val x10112 = withCtrl(x10132) { OpDef(op=FixAdd, inputs=List(x10111, b5880)).name("x10112").srcCtx("lenet_loops.scala:146:47") } // FixAdd(x10111,b5880)
    val x10113 = withCtrl(x10132) { OpDef(op=FixSla, inputs=List(b5873, Const(1))).name("x10113").srcCtx("lenet_loops.scala:146:54") } // FixLsh(b5873,Const(1))
    val x10114 = withCtrl(x10132) { OpDef(op=FixAdd, inputs=List(x10113, b5881)).name("x10114").srcCtx("lenet_loops.scala:146:57") } // FixAdd(x10113,b5881)
    val x10115 = withCtrl(x10132) { OpDef(op=BitAnd, inputs=List(b5882, b5883)).name("x10115").srcCtx("UnrollingBase.scala:28:66") } // And(b5882,b5883)
    val x10116 = withCtrl(x10132) { OpDef(op=BitAnd, inputs=List(b5874, b5875)).name("x10116").srcCtx("UnrollingBase.scala:28:66") } // And(b5874,b5875)
    val x10117 = withCtrl(x10132) { OpDef(op=BitAnd, inputs=List(b5675, b5444)).name("x10117").srcCtx("UnrollingBase.scala:28:66") } // And(b5675,b5444)
    val x10118 = withCtrl(x10132) { OpDef(op=BitAnd, inputs=List(x10115, x10116)).name("x10118").srcCtx("UnrollingBase.scala:28:66") } // And(x10115,x10116)
    val x10119 = withCtrl(x10132) { OpDef(op=BitAnd, inputs=List(x10118, x10117)).name("x10119").srcCtx("UnrollingBase.scala:28:66") } // And(x10118,x10117)
    val x10120 = withCtrl(x10132) { LoadBanks(List(x9948_d0_b0), List(x10112, x10114)).name("x10120").srcCtx("lenet_loops.scala:146:42") } // ParSRAMLoad(x9948,List(List(x10112, x10114)),List(x10119))
    val x10121 = withCtrl(x10132) { x10120 } // VectorApply(x10120,0)
    val x10122 = withCtrl(x10132) { LoadBanks(List(x9695_d0_b0), List(b5673)).name("x10122").srcCtx("lenet_loops.scala:146:72") } // SRAMLoad(x9695,ArrayBuffer(Const(64)),List(b5673),Const(0),x10119)
    val x10123 = withCtrl(x10132) { OpDef(op=FixAdd, inputs=List(x10121, x10122)).name("x10123").srcCtx("lenet_loops.scala:146:63") } // FixAdd(x10121,x10122)
    val x10124 = withCtrl(x10132) { OpDef(op=FixMax, inputs=List(Const(0.0), x10123)).name("x10124").srcCtx("lenet_loops.scala:146:18") } // Max(Const(0),x10123)
    val x10125 = withCtrl(x10132) { ReadMem(x10107_d1).name("x10125").srcCtx("lenet_loops.scala:147:15") } // RegRead(x10107)
    val x10126 = withCtrl(x10132) { OpDef(op=FixEql, inputs=List(b5880, Const(0))).name("x10126").srcCtx("lenet_loops.scala:147:15") } // FixEql(b5880,Const(0))
    val x10127 = withCtrl(x10132) { OpDef(op=FixEql, inputs=List(b5881, Const(0))).name("x10127").srcCtx("lenet_loops.scala:147:15") } // FixEql(b5881,Const(0))
    val x10128 = withCtrl(x10132) { OpDef(op=BitAnd, inputs=List(x10126, x10127)).name("x10128").srcCtx("lenet_loops.scala:147:15") } // And(x10126,x10127)
    val x10129 = withCtrl(x10132) { ReduceAccumOp(op=FixMax, input=x10124, accum=x10125).name("x10129").srcCtx("lenet_loops.scala:147:29") } // Max(x10124,x10125)
    val x10130 = withCtrl(x10132) { OpDef(op=BitAnd, inputs=List(x10116, x10117)).name("x10130").srcCtx("UnrollingBase.scala:28:66") } // And(x10116,x10117)
    val x10131_x10107_d0 = withCtrl(x10132) { WriteMem(x10107_d0, x10129).name("x10131_x10107_d0").srcCtx("lenet_loops.scala:147:15") } // RegWrite(x10107,x10129,x10130)
    antiDepsOf(x10131_x10107_d0)=List(x10125)
    val x10131_x10107_d1 = withCtrl(x10132) { WriteMem(x10107_d1, x10129).name("x10131_x10107_d1").srcCtx("lenet_loops.scala:147:15") } // RegWrite(x10107,x10129,x10130)
    antiDepsOf(x10131_x10107_d1)=List(x10125)
    val x10138 = withCtrl(x10139) { UnitController(style=SeqPipe, level=InnerControl).name("x10138").srcCtx("lenet_loops.scala:144:35") } // UnitPipe(List(b5874, b5875, b5675, b5444),Block(Const(())))
    val x10133 = withCtrl(x10138) { ReadMem(x10107_d0).name("x10133").srcCtx("lenet_loops.scala:148:43") } // RegRead(x10107)
    val x10134 = withCtrl(x10138) { OpDef(op=BitAnd, inputs=List(b5874, b5875)).name("x10134").srcCtx("UnrollingBase.scala:28:66") } // And(b5874,b5875)
    val x10135 = withCtrl(x10138) { OpDef(op=BitAnd, inputs=List(b5675, b5444)).name("x10135").srcCtx("UnrollingBase.scala:28:66") } // And(b5675,b5444)
    val x10136 = withCtrl(x10138) { OpDef(op=BitAnd, inputs=List(x10134, x10135)).name("x10136").srcCtx("UnrollingBase.scala:28:66") } // And(x10134,x10135)
    val x10137 = withCtrl(x10138) { StoreBanks(List(List(x9911_d0_b0)), List(b5673, b5872, b5873), x10133).name("x10137").srcCtx("lenet_loops.scala:148:37") } // SRAMStore(x9911,ArrayBuffer(Const(50), Const(4), Const(4)),List(b5673, b5872, b5873),Const(0),x10133,x10136)
    val x10142_d0_b0 = withCtrl(x10326) { SRAM(size=640, banking=Strided(banks=1, stride=128)).name("x10142_d0_b0").srcCtx("lenet_loops.scala:162:32:tmp4_SRAM") } // x10142 = SRAMNew(ArrayBuffer(Const(5), Const(128)))
    isAccum(x10142_d0_b0) = false
    bufferDepthOf(x10142_d0_b0) = 3
    staticDimsOf(x10142_d0_b0) = List(5, 128)
    val x10143 = withCtrl(x10326) { Counter(min=Const(0), max=Const(128), step=Const(1), par=1).name("x10143").srcCtx("lenet_loops.scala:163:26") } // CounterNew(Const(0),Const(128),Const(1),Const(1))
    val x10144 = withCtrl(x10326) { CounterChain(List(x10143)).name("x10144").srcCtx("lenet_loops.scala:163:39") } // CounterChainNew(List(x10143))
    val x10231 = withCtrl(x10326) { LoopController(style=MetaPipe, level=OuterControl, cchain=x10144).name("x10231").srcCtx("lenet_loops.scala:163:39") } // UnrolledForeach(List(b5444),x10144,Block(Const(())),List(List(b5918)),List(List(b5919)))
    val b5918 = withCtrl(x10231) { CounterIter(x10143, Some(0)).name("b5918") } // b5918
    val b5919 = withCtrl(x10231) { Const(true).name("b5919") } // b5919
    val x10145_d0_b0 = withCtrl(x10231) { SRAM(size=5120, banking=Strided(banks=16, stride=1)).name("x10145_d0_b0").srcCtx("lenet_loops.scala:164:36:c4_row_SRAM") } // x10145 = SRAMNew(ArrayBuffer(Const(5), Const(4), Const(4), Const(64)))
    isAccum(x10145_d0_b0) = false
    bufferDepthOf(x10145_d0_b0) = 2
    staticDimsOf(x10145_d0_b0) = List(5, 4, 4, 64)
    val x10147 = withCtrl(x10231) { UnitController(style=SeqPipe, level=InnerControl).name("x10147").srcCtx("lenet_loops.scala:163:39") } // UnitPipe(List(b5919, b5444),Block(Const(())))
    val x10146 = withCtrl(x10147) { OpDef(op=FixAdd, inputs=List(b5918, Const(1))).name("x10146").srcCtx("lenet_loops.scala:165:35") } // FixAdd(b5918,Const(1))
    val x10148 = withCtrl(x10231) { Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x10148").srcCtx("lenet_loops.scala:165:23") } // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x10149 = withCtrl(x10231) { Counter(min=Const(0), max=Const(5), step=Const(1), par=1).name("x10149").srcCtx("lenet_loops.scala:165:23") } // CounterNew(Const(0),Const(5),Const(1),Const(1))
    val x10150 = withCtrl(x10231) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x10150").srcCtx("lenet_loops.scala:165:23") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x10151 = withCtrl(x10231) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x10151").srcCtx("lenet_loops.scala:165:23") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x10152 = withCtrl(x10231) { CounterChain(List(x10148,x10149,x10150,x10151)).name("x10152").srcCtx("lenet_loops.scala:165:23") } // CounterChainNew(List(x10148, x10149, x10150, x10151))
    val x10194 = withCtrl(x10231) { LoopController(style=StreamPipe, level=OuterControl, cchain=x10152).name("x10194").srcCtx("lenet_loops.scala:165:23") } // UnrolledForeach(List(b5919, b5444),x10152,Block(Const(())),List(List(b5928), List(b5929), List(b5930), List(b5931)),List(List(b5932), List(b5933), List(b5934), List(b5935)))
    val b5928 = withCtrl(x10194) { CounterIter(x10148, Some(0)).name("b5928") } // b5928
    val b5932 = withCtrl(x10194) { Const(true).name("b5932") } // b5932
    val b5929 = withCtrl(x10194) { CounterIter(x10149, Some(0)).name("b5929") } // b5929
    val b5933 = withCtrl(x10194) { Const(true).name("b5933") } // b5933
    val b5930 = withCtrl(x10194) { CounterIter(x10150, Some(0)).name("b5930") } // b5930
    val b5934 = withCtrl(x10194) { Const(true).name("b5934") } // b5934
    val b5931 = withCtrl(x10194) { CounterIter(x10151, Some(0)).name("b5931") } // b5931
    val b5935 = withCtrl(x10194) { Const(true).name("b5935") } // b5935
    val b10393 = withCtrl(x10194) { StreamOut(field="offset").name("b10393").srcCtx("lenet_loops.scala:165:23") } // x10153 = StreamOutNew(BurstCmdBus)
    isAccum(b10393) = false
    bufferDepthOf(b10393) = 1
    val b10394 = withCtrl(x10194) { StreamOut(field="size").name("b10394").srcCtx("lenet_loops.scala:165:23") } // x10153 = StreamOutNew(BurstCmdBus)
    isAccum(b10394) = false
    bufferDepthOf(b10394) = 1
    val x10154 = withCtrl(x10194) { StreamIn(field="data").name("x10154").srcCtx("lenet_loops.scala:165:23") } // x10154 = StreamInNew(BurstDataBus())
    isAccum(x10154) = false
    bufferDepthOf(x10154) = 1
    val x10180 = withCtrl(x10194) { UnitController(style=SeqPipe, level=InnerControl).name("x10180").srcCtx("lenet_loops.scala:165:23") } // UnitPipe(List(b5932, b5933, b5934, b5935, b5919, b5444),Block(x10179))
    val x10155 = withCtrl(x10180) { OpDef(op=FixAdd, inputs=List(b5918, b5928)).name("x10155").srcCtx("lenet_loops.scala:165:23") } // FixAdd(b5918,b5928)
    val x10156 = withCtrl(x10180) { x10155 } // FixConvert(x10155,TRUE,_32,_0) (Same Type. No op)
    val x10157 = withCtrl(x10180) { OpDef(op=FixSla, inputs=List(x10156, Const(12))).name("x10157").srcCtx("lenet_loops.scala:165:23") } // FixLsh(x10156,Const(12))
    val x10158 = withCtrl(x10180) { b5929 } // FixConvert(b5929,TRUE,_32,_0) (Same Type. No op)
    val x10159 = withCtrl(x10180) { OpDef(op=FixSla, inputs=List(x10158, Const(10))).name("x10159").srcCtx("lenet_loops.scala:165:23") } // FixLsh(x10158,Const(10))
    val x10160 = withCtrl(x10180) { b5930 } // FixConvert(b5930,TRUE,_32,_0) (Same Type. No op)
    val x10161 = withCtrl(x10180) { OpDef(op=FixSla, inputs=List(x10160, Const(8))).name("x10161").srcCtx("lenet_loops.scala:165:23") } // FixLsh(x10160,Const(8))
    val x10162 = withCtrl(x10180) { b5931 } // FixConvert(b5931,TRUE,_32,_0) (Same Type. No op)
    val x10163 = withCtrl(x10180) { OpDef(op=FixSla, inputs=List(x10162, Const(6))).name("x10163").srcCtx("lenet_loops.scala:165:23") } // FixLsh(x10162,Const(6))
    val x10164 = withCtrl(x10180) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x10165 = withCtrl(x10180) { OpDef(op=FixAdd, inputs=List(x10157, x10159)).name("x10165").srcCtx("lenet_loops.scala:165:23") } // FixAdd(x10157,x10159)
    val x10166 = withCtrl(x10180) { OpDef(op=FixAdd, inputs=List(x10161, x10163)).name("x10166").srcCtx("lenet_loops.scala:165:23") } // FixAdd(x10161,x10163)
    val x10167 = withCtrl(x10180) { OpDef(op=FixAdd, inputs=List(x10165, x10166)).name("x10167").srcCtx("lenet_loops.scala:165:23") } // FixAdd(x10165,x10166)
    val x10168 = withCtrl(x10180) { OpDef(op=FixAdd, inputs=List(x10167, x10164)).name("x10168").srcCtx("lenet_loops.scala:165:23") } // FixAdd(x10167,x10164)
    val x10169 = withCtrl(x10180) { OpDef(op=FixSla, inputs=List(x10168, Const(2))).name("x10169").srcCtx("lenet_loops.scala:165:23") } // FixLsh(x10168,Const(2))
    val x10170 = withCtrl(x10180) { x10169 } // FixConvert(x10169,TRUE,_64,_0)
    val x10171 = withCtrl(x10180) { DramAddress(x9562).name("x10171").srcCtx("lenet_loops.scala:165:23") } // GetDRAMAddress(x9562)
    val x10173_x10172 = withCtrl(x10180) { OpDef(op=FixAdd, inputs=List(x10170, x10171)).name("x10173_x10172").srcCtx("lenet_loops.scala:165:23") } // FixAdd(x10170,x10171)
    // x10173 = SimpleStruct(ArrayBuffer((offset,x10172), (size,Const(256)), (isLoad,Const(true))))
    val x10174 = withCtrl(x10180) { OpDef(op=BitAnd, inputs=List(b5932, b5933)).name("x10174").srcCtx("UnrollingBase.scala:28:66") } // And(b5932,b5933)
    val x10175 = withCtrl(x10180) { OpDef(op=BitAnd, inputs=List(b5934, b5935)).name("x10175").srcCtx("UnrollingBase.scala:28:66") } // And(b5934,b5935)
    val x10176 = withCtrl(x10180) { OpDef(op=BitAnd, inputs=List(b5919, b5444)).name("x10176").srcCtx("UnrollingBase.scala:28:66") } // And(b5919,b5444)
    val x10177 = withCtrl(x10180) { OpDef(op=BitAnd, inputs=List(x10174, x10175)).name("x10177").srcCtx("UnrollingBase.scala:28:66") } // And(x10174,x10175)
    val x10178 = withCtrl(x10180) { OpDef(op=BitAnd, inputs=List(x10177, x10176)).name("x10178").srcCtx("UnrollingBase.scala:28:66") } // And(x10177,x10176)
    val x10179_b10395_b10393 = withCtrl(x10180) { WriteMem(b10393, x10173_x10172).name("x10179_b10395_b10393").srcCtx("lenet_loops.scala:165:23") } // StreamWrite(x10153,x10173,x10178)
    val x10179_b10396_b10394 = withCtrl(x10180) { WriteMem(b10394, Const(256)).name("x10179_b10396_b10394").srcCtx("lenet_loops.scala:165:23") } // StreamWrite(x10153,x10173,x10178)
    val x10181 = withCtrl(x10194) { FringeDenseLoad(dram=List(x9562), cmdStream=List(b10393, b10394), dataStream=List(x10154)).name("x10181").srcCtx("lenet_loops.scala:165:23") } // FringeDenseLoad(x9562,x10153,x10154)
    val x10182 = withCtrl(x10194) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x10182").srcCtx("lenet_loops.scala:165:23") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x10183 = withCtrl(x10194) { CounterChain(List(x10182)).name("x10183").srcCtx("lenet_loops.scala:165:23") } // CounterChainNew(List(x10182))
    val x10193 = withCtrl(x10194) { LoopController(style=InnerPipe, level=InnerControl, cchain=x10183).name("x10193").srcCtx("lenet_loops.scala:165:23") } // UnrolledForeach(List(b5932, b5933, b5934, b5935, b5919, b5444),x10183,Block(Const(())),List(List(b5967)),List(List(b5968)))
    val b5967 = withCtrl(x10193) { CounterIter(x10182, None).name("b5967") } // b5967
    val b5968 = withCtrl(x10193) { Const(true).name("b5968") } // b5968
    val x10184 = withCtrl(x10193) { OpDef(op=BitAnd, inputs=List(b5968, b5932)).name("x10184").srcCtx("UnrollingBase.scala:28:66") } // And(b5968,b5932)
    val x10185 = withCtrl(x10193) { OpDef(op=BitAnd, inputs=List(b5933, b5934)).name("x10185").srcCtx("UnrollingBase.scala:28:66") } // And(b5933,b5934)
    val x10186 = withCtrl(x10193) { OpDef(op=BitAnd, inputs=List(b5935, b5919)).name("x10186").srcCtx("UnrollingBase.scala:28:66") } // And(b5935,b5919)
    val x10187 = withCtrl(x10193) { OpDef(op=BitAnd, inputs=List(x10184, x10185)).name("x10187").srcCtx("UnrollingBase.scala:28:66") } // And(x10184,x10185)
    val x10188 = withCtrl(x10193) { OpDef(op=BitAnd, inputs=List(x10186, b5444)).name("x10188").srcCtx("UnrollingBase.scala:28:66") } // And(x10186,b5444)
    val x10189 = withCtrl(x10193) { OpDef(op=BitAnd, inputs=List(x10187, x10188)).name("x10189").srcCtx("UnrollingBase.scala:28:66") } // And(x10187,x10188)
    val x10190_x10190 = withCtrl(x10193) { ReadMem(x10154).name("x10190_x10190").srcCtx("lenet_loops.scala:165:23") } // ParStreamRead(x10154,List(x10189))
    val x10191_x10191 = withCtrl(x10193) { x10190_x10190 } // VectorApply(x10190,0)
    val x10192 = withCtrl(x10193) { StoreBanks(List(List(x10145_d0_b0)), List(b5929, b5930, b5931, b5967), x10191_x10191).name("x10192").srcCtx("lenet_loops.scala:165:23") } // ParSRAMStore(x10145,List(List(b5929, b5930, b5931, b5967)),List(x10191),List(x10189))
    val x10195 = withCtrl(x10231) { Counter(min=Const(0), max=Const(5), step=Const(1), par=1).name("x10195").srcCtx("lenet_loops.scala:166:21") } // CounterNew(Const(0),Const(5),Const(1),Const(1))
    val x10196 = withCtrl(x10231) { CounterChain(List(x10195)).name("x10196").srcCtx("lenet_loops.scala:166:26") } // CounterChainNew(List(x10195))
    val x10230 = withCtrl(x10231) { LoopController(style=MetaPipe, level=OuterControl, cchain=x10196).name("x10230").srcCtx("lenet_loops.scala:166:26") } // UnrolledForeach(List(b5919, b5444),x10196,Block(Const(())),List(List(b5982)),List(List(b5983)))
    val b5982 = withCtrl(x10230) { CounterIter(x10195, Some(0)).name("b5982") } // b5982
    val b5983 = withCtrl(x10230) { Const(true).name("b5983") } // b5983
    val x10197_d0 = withCtrl(x10230) { Reg(init=Some(0.0)).name("x10197_d0").srcCtx("lenet_loops.scala:167:37:prod") } // x10197 = RegNew(Const(0))
    isAccum(x10197_d0) = false
    bufferDepthOf(x10197_d0) = 2
    val x10197_d1 = withCtrl(x10230) { Reg(init=Some(0.0)).name("x10197_d1").srcCtx("lenet_loops.scala:167:37:prod") } // x10197 = RegNew(Const(0))
    isAccum(x10197_d1) = true
    bufferDepthOf(x10197_d1) = 1
    val x10198 = withCtrl(x10230) { Counter(min=Const(0), max=Const(4), step=Const(1), par=4).name("x10198").srcCtx("lenet_loops.scala:167:72") } // CounterNew(Const(0),Const(4),Const(1),Const(4))
    val x10199 = withCtrl(x10230) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x10199").srcCtx("lenet_loops.scala:167:59") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x10200 = withCtrl(x10230) { Counter(min=Const(0), max=Const(50), step=Const(1), par=1).name("x10200").srcCtx("lenet_loops.scala:167:51") } // CounterNew(Const(0),Const(50),Const(1),Const(1))
    val x10201 = withCtrl(x10230) { CounterChain(List(x10200,x10199,x10198)).name("x10201").srcCtx("lenet_loops.scala:170:14") } // CounterChainNew(List(x10200, x10199, x10198))
    val x10221 = withCtrl(x10230) { LoopController(style=InnerPipe, level=InnerControl, cchain=x10201).name("x10221").srcCtx("lenet_loops.scala:170:14") } // UnrolledReduce(List(b5983, b5919, b5444),x10201,x10197,Block((x10197) => Const(())),List(List(b5989), List(b5990), List(b5991)),List(List(b5992), List(b5993), List(b5994)))
    val b5989 = withCtrl(x10221) { CounterIter(x10200, Some(0)).name("b5989") } // b5989
    val b5992 = withCtrl(x10221) { Const(true).name("b5992") } // b5992
    val b5990 = withCtrl(x10221) { CounterIter(x10199, Some(0)).name("b5990") } // b5990
    val b5993 = withCtrl(x10221) { Const(true).name("b5993") } // b5993
    val b5991 = withCtrl(x10221) { CounterIter(x10198, None).name("b5991") } // b5991
    val b5994 = withCtrl(x10221) { Const(true).name("b5994") } // b5994
    val x10202 = withCtrl(x10221) { OpDef(op=BitAnd, inputs=List(b5992, b5993)).name("x10202").srcCtx("UnrollingBase.scala:28:66") } // And(b5992,b5993)
    val x10203 = withCtrl(x10221) { OpDef(op=BitAnd, inputs=List(b5994, b5983)).name("x10203").srcCtx("UnrollingBase.scala:28:66") } // And(b5994,b5983)
    val x10204 = withCtrl(x10221) { OpDef(op=BitAnd, inputs=List(b5919, b5444)).name("x10204").srcCtx("UnrollingBase.scala:28:66") } // And(b5919,b5444)
    val x10205 = withCtrl(x10221) { OpDef(op=BitAnd, inputs=List(x10202, x10203)).name("x10205").srcCtx("UnrollingBase.scala:28:66") } // And(x10202,x10203)
    val x10206 = withCtrl(x10221) { OpDef(op=BitAnd, inputs=List(x10205, x10204)).name("x10206").srcCtx("UnrollingBase.scala:28:66") } // And(x10205,x10204)
    val x10207 = withCtrl(x10221) { LoadBanks(List(x9911_d0_b0), List(b5990, b5990, b5989)).name("x10207").srcCtx("lenet_loops.scala:169:24") } // SRAMLoad(x9911,ArrayBuffer(Const(50), Const(4), Const(4)),List(b5990, b5990, b5989),Const(0),x10206)
    val x10208 = withCtrl(x10221) { LoadBanks(List(x10145_d0_b0), List(b5982, b5989, b5990, b5991)).name("x10208").srcCtx("lenet_loops.scala:169:45") } // ParSRAMLoad(x10145,List(List(b5982, b5989, b5990, b5991)),List(x10206))
    val x10209 = withCtrl(x10221) { x10208 } // VectorApply(x10208,0)
    val x10210 = withCtrl(x10221) { OpDef(op=FixMul, inputs=List(x10207, x10209)).name("x10210").srcCtx("lenet_loops.scala:169:32") } // FixMul(x10207,x10209)
    val x10211 = withCtrl(x10221) { ReadMem(x10197_d1).name("x10211").srcCtx("lenet_loops.scala:170:14") } // RegRead(x10197)
    val x10212 = withCtrl(x10221) { OpDef(op=FixEql, inputs=List(b5989, Const(0))).name("x10212").srcCtx("lenet_loops.scala:170:14") } // FixEql(b5989,Const(0))
    val x10213 = withCtrl(x10221) { OpDef(op=FixEql, inputs=List(b5990, Const(0))).name("x10213").srcCtx("lenet_loops.scala:170:14") } // FixEql(b5990,Const(0))
    val x10214 = withCtrl(x10221) { OpDef(op=FixEql, inputs=List(b5991, Const(0))).name("x10214").srcCtx("lenet_loops.scala:170:14") } // FixEql(b5991,Const(0))
    val x10215 = withCtrl(x10221) { OpDef(op=BitAnd, inputs=List(x10212, x10213)).name("x10215").srcCtx("lenet_loops.scala:170:14") } // And(x10212,x10213)
    val x10216 = withCtrl(x10221) { OpDef(op=BitAnd, inputs=List(x10215, x10214)).name("x10216").srcCtx("lenet_loops.scala:170:14") } // And(x10215,x10214)
    val x10217 = withCtrl(x10221) { ReduceAccumOp(op=FixAdd, input=x10210, accum=x10211).name("x10217").srcCtx("lenet_loops.scala:170:16") } // FixAdd(x10210,x10211)
    val x10218 = withCtrl(x10221) { OpDef(op=BitAnd, inputs=List(b5983, b5919)).name("x10218").srcCtx("UnrollingBase.scala:28:66") } // And(b5983,b5919)
    val x10219 = withCtrl(x10221) { OpDef(op=BitAnd, inputs=List(x10218, b5444)).name("x10219").srcCtx("UnrollingBase.scala:28:66") } // And(x10218,b5444)
    val x10220_x10197_d0 = withCtrl(x10221) { WriteMem(x10197_d0, x10217).name("x10220_x10197_d0").srcCtx("lenet_loops.scala:170:14") } // RegWrite(x10197,x10217,x10219)
    antiDepsOf(x10220_x10197_d0)=List(x10211)
    val x10220_x10197_d1 = withCtrl(x10221) { WriteMem(x10197_d1, x10217).name("x10220_x10197_d1").srcCtx("lenet_loops.scala:170:14") } // RegWrite(x10197,x10217,x10219)
    antiDepsOf(x10220_x10197_d1)=List(x10211)
    val x10229 = withCtrl(x10230) { UnitController(style=SeqPipe, level=InnerControl).name("x10229").srcCtx("lenet_loops.scala:166:26") } // UnitPipe(List(b5983, b5919, b5444),Block(Const(())))
    val x10222 = withCtrl(x10229) { OpDef(op=BitAnd, inputs=List(b5983, b5919)).name("x10222").srcCtx("UnrollingBase.scala:28:66") } // And(b5983,b5919)
    val x10223 = withCtrl(x10229) { OpDef(op=BitAnd, inputs=List(x10222, b5444)).name("x10223").srcCtx("UnrollingBase.scala:28:66") } // And(x10222,b5444)
    val x10224 = withCtrl(x10229) { LoadBanks(List(x9714_d0_b0), List(b5982, b5918)).name("x10224").srcCtx("lenet_loops.scala:171:73") } // SRAMLoad(x9714,ArrayBuffer(Const(5), Const(128)),List(b5982, b5918),Const(0),x10223)
    val x10225 = withCtrl(x10229) { ReadMem(x10197_d0).name("x10225").srcCtx("lenet_loops.scala:171:58") } // RegRead(x10197)
    val x10226 = withCtrl(x10229) { OpDef(op=FixAdd, inputs=List(x10225, x10224)).name("x10226").srcCtx("lenet_loops.scala:171:64") } // FixAdd(x10225,x10224)
    val x10227 = withCtrl(x10229) { OpDef(op=FixMax, inputs=List(Const(0.0), x10226)).name("x10227").srcCtx("lenet_loops.scala:171:43") } // Max(Const(0),x10226)
    val x10228 = withCtrl(x10229) { StoreBanks(List(List(x10142_d0_b0)), List(b5982, b5918), x10227).name("x10228").srcCtx("lenet_loops.scala:171:38") } // SRAMStore(x10142,ArrayBuffer(Const(5), Const(128)),List(b5982, b5918),Const(0),x10227,x10223)
    val x10232_d0_b0 = withCtrl(x10326) { SRAM(size=32, banking=Strided(banks=16, stride=1)).name("x10232_d0_b0").srcCtx("lenet_loops.scala:178:32:tmp5_SRAM") } // x10232 = SRAMNew(ArrayBuffer(Const(32)))
    isAccum(x10232_d0_b0) = false
    bufferDepthOf(x10232_d0_b0) = 2
    staticDimsOf(x10232_d0_b0) = List(32)
    val x10233_d0_b0 = withCtrl(x10326) { SRAM(size=6400, banking=Strided(banks=16, stride=1)).name("x10233_d0_b0").srcCtx("lenet_loops.scala:179:34:c6_row_SRAM") } // x10233 = SRAMNew(ArrayBuffer(Const(10), Const(5), Const(128)))
    isAccum(x10233_d0_b0) = false
    bufferDepthOf(x10233_d0_b0) = 2
    staticDimsOf(x10233_d0_b0) = List(10, 5, 128)
    val x10234 = withCtrl(x10326) { Counter(min=Const(0), max=Const(10), step=Const(1), par=1).name("x10234").srcCtx("lenet_loops.scala:180:21") } // CounterNew(Const(0),Const(10),Const(1),Const(1))
    val x10235 = withCtrl(x10326) { Counter(min=Const(0), max=Const(5), step=Const(1), par=1).name("x10235").srcCtx("lenet_loops.scala:180:21") } // CounterNew(Const(0),Const(5),Const(1),Const(1))
    val x10236 = withCtrl(x10326) { CounterChain(List(x10234,x10235)).name("x10236").srcCtx("lenet_loops.scala:180:21") } // CounterChainNew(List(x10234, x10235))
    val x10265 = withCtrl(x10326) { LoopController(style=StreamPipe, level=OuterControl, cchain=x10236).name("x10265").srcCtx("lenet_loops.scala:180:21") } // UnrolledForeach(List(b5444),x10236,Block(Const(())),List(List(b6030), List(b6031)),List(List(b6032), List(b6033)))
    val b6030 = withCtrl(x10265) { CounterIter(x10234, Some(0)).name("b6030") } // b6030
    val b6032 = withCtrl(x10265) { Const(true).name("b6032") } // b6032
    val b6031 = withCtrl(x10265) { CounterIter(x10235, Some(0)).name("b6031") } // b6031
    val b6033 = withCtrl(x10265) { Const(true).name("b6033") } // b6033
    val b10397 = withCtrl(x10265) { StreamOut(field="offset").name("b10397").srcCtx("lenet_loops.scala:180:21") } // x10237 = StreamOutNew(BurstCmdBus)
    isAccum(b10397) = false
    bufferDepthOf(b10397) = 1
    val b10398 = withCtrl(x10265) { StreamOut(field="size").name("b10398").srcCtx("lenet_loops.scala:180:21") } // x10237 = StreamOutNew(BurstCmdBus)
    isAccum(b10398) = false
    bufferDepthOf(b10398) = 1
    val x10238 = withCtrl(x10265) { StreamIn(field="data").name("x10238").srcCtx("lenet_loops.scala:180:21") } // x10238 = StreamInNew(BurstDataBus())
    isAccum(x10238) = false
    bufferDepthOf(x10238) = 1
    val x10254 = withCtrl(x10265) { UnitController(style=SeqPipe, level=InnerControl).name("x10254").srcCtx("lenet_loops.scala:180:21") } // UnitPipe(List(b6032, b6033, b5444),Block(x10253))
    val x10239 = withCtrl(x10254) { b6030 } // FixConvert(b6030,TRUE,_32,_0) (Same Type. No op)
    val x10240 = withCtrl(x10254) { OpDef(op=FixMul, inputs=List(x10239, Const(500))).name("x10240").srcCtx("lenet_loops.scala:180:21") } // FixMul(x10239,Const(500))
    val x10241 = withCtrl(x10254) { b6031 } // FixConvert(b6031,TRUE,_32,_0) (Same Type. No op)
    val x10242 = withCtrl(x10254) { OpDef(op=FixMul, inputs=List(x10241, Const(5))).name("x10242").srcCtx("lenet_loops.scala:180:21") } // FixMul(x10241,Const(5))
    val x10243 = withCtrl(x10254) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x10244 = withCtrl(x10254) { OpDef(op=FixAdd, inputs=List(x10240, x10242)).name("x10244").srcCtx("lenet_loops.scala:180:21") } // FixAdd(x10240,x10242)
    val x10245 = withCtrl(x10254) { OpDef(op=FixAdd, inputs=List(x10244, x10243)).name("x10245").srcCtx("lenet_loops.scala:180:21") } // FixAdd(x10244,x10243)
    val x10246 = withCtrl(x10254) { OpDef(op=FixSla, inputs=List(x10245, Const(2))).name("x10246").srcCtx("lenet_loops.scala:180:21") } // FixLsh(x10245,Const(2))
    val x10247 = withCtrl(x10254) { x10246 } // FixConvert(x10246,TRUE,_64,_0)
    val x10248 = withCtrl(x10254) { DramAddress(x9564).name("x10248").srcCtx("lenet_loops.scala:180:21") } // GetDRAMAddress(x9564)
    val x10250_x10249 = withCtrl(x10254) { OpDef(op=FixAdd, inputs=List(x10247, x10248)).name("x10250_x10249").srcCtx("lenet_loops.scala:180:21") } // FixAdd(x10247,x10248)
    // x10250 = SimpleStruct(ArrayBuffer((offset,x10249), (size,Const(512)), (isLoad,Const(true))))
    val x10251 = withCtrl(x10254) { OpDef(op=BitAnd, inputs=List(b6032, b6033)).name("x10251").srcCtx("UnrollingBase.scala:28:66") } // And(b6032,b6033)
    val x10252 = withCtrl(x10254) { OpDef(op=BitAnd, inputs=List(x10251, b5444)).name("x10252").srcCtx("UnrollingBase.scala:28:66") } // And(x10251,b5444)
    val x10253_b10399_b10397 = withCtrl(x10254) { WriteMem(b10397, x10250_x10249).name("x10253_b10399_b10397").srcCtx("lenet_loops.scala:180:21") } // StreamWrite(x10237,x10250,x10252)
    val x10253_b10400_b10398 = withCtrl(x10254) { WriteMem(b10398, Const(512)).name("x10253_b10400_b10398").srcCtx("lenet_loops.scala:180:21") } // StreamWrite(x10237,x10250,x10252)
    val x10255 = withCtrl(x10265) { FringeDenseLoad(dram=List(x9564), cmdStream=List(b10397, b10398), dataStream=List(x10238)).name("x10255").srcCtx("lenet_loops.scala:180:21") } // FringeDenseLoad(x9564,x10237,x10238)
    val x10256 = withCtrl(x10265) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x10256").srcCtx("lenet_loops.scala:180:21") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x10257 = withCtrl(x10265) { CounterChain(List(x10256)).name("x10257").srcCtx("lenet_loops.scala:180:21") } // CounterChainNew(List(x10256))
    val x10264 = withCtrl(x10265) { LoopController(style=InnerPipe, level=InnerControl, cchain=x10257).name("x10264").srcCtx("lenet_loops.scala:180:21") } // UnrolledForeach(List(b6032, b6033, b5444),x10257,Block(Const(())),List(List(b6055)),List(List(b6056)))
    val b6055 = withCtrl(x10264) { CounterIter(x10256, None).name("b6055") } // b6055
    val b6056 = withCtrl(x10264) { Const(true).name("b6056") } // b6056
    val x10258 = withCtrl(x10264) { OpDef(op=BitAnd, inputs=List(b6056, b6032)).name("x10258").srcCtx("UnrollingBase.scala:28:66") } // And(b6056,b6032)
    val x10259 = withCtrl(x10264) { OpDef(op=BitAnd, inputs=List(b6033, b5444)).name("x10259").srcCtx("UnrollingBase.scala:28:66") } // And(b6033,b5444)
    val x10260 = withCtrl(x10264) { OpDef(op=BitAnd, inputs=List(x10258, x10259)).name("x10260").srcCtx("UnrollingBase.scala:28:66") } // And(x10258,x10259)
    val x10261_x10261 = withCtrl(x10264) { ReadMem(x10238).name("x10261_x10261").srcCtx("lenet_loops.scala:180:21") } // ParStreamRead(x10238,List(x10260))
    val x10262_x10262 = withCtrl(x10264) { x10261_x10261 } // VectorApply(x10261,0)
    val x10263 = withCtrl(x10264) { StoreBanks(List(List(x10233_d0_b0)), List(b6030, b6031, b6055), x10262_x10262).name("x10263").srcCtx("lenet_loops.scala:180:21") } // ParSRAMStore(x10233,List(List(b6030, b6031, b6055)),List(x10262),List(x10260))
    val x10266 = withCtrl(x10326) { Counter(min=Const(0), max=Const(10), step=Const(1), par=1).name("x10266").srcCtx("lenet_loops.scala:181:25") } // CounterNew(Const(0),Const(10),Const(1),Const(1))
    val x10267 = withCtrl(x10326) { CounterChain(List(x10266)).name("x10267").srcCtx("lenet_loops.scala:181:38") } // CounterChainNew(List(x10266))
    val x10293 = withCtrl(x10326) { LoopController(style=MetaPipe, level=OuterControl, cchain=x10267).name("x10293").srcCtx("lenet_loops.scala:181:38") } // UnrolledForeach(List(b5444),x10267,Block(Const(())),List(List(b6067)),List(List(b6068)))
    val b6067 = withCtrl(x10293) { CounterIter(x10266, Some(0)).name("b6067") } // b6067
    val b6068 = withCtrl(x10293) { Const(true).name("b6068") } // b6068
    val x10268_d0 = withCtrl(x10293) { Reg(init=Some(0.0)).name("x10268_d0").srcCtx("lenet_loops.scala:182:35:prod") } // x10268 = RegNew(Const(0))
    isAccum(x10268_d0) = false
    bufferDepthOf(x10268_d0) = 2
    val x10268_d1 = withCtrl(x10293) { Reg(init=Some(0.0)).name("x10268_d1").srcCtx("lenet_loops.scala:182:35:prod") } // x10268 = RegNew(Const(0))
    isAccum(x10268_d1) = true
    bufferDepthOf(x10268_d1) = 1
    val x10269 = withCtrl(x10293) { Counter(min=Const(0), max=Const(128), step=Const(16), par=1).name("x10269").srcCtx("lenet_loops.scala:182:58") } // CounterNew(Const(0),Const(128),Const(16),Const(1))
    val x10270 = withCtrl(x10293) { Counter(min=Const(0), max=Const(5), step=Const(1), par=1).name("x10270").srcCtx("lenet_loops.scala:182:48") } // CounterNew(Const(0),Const(5),Const(1),Const(1))
    val x10271 = withCtrl(x10293) { CounterChain(List(x10270,x10269)).name("x10271").srcCtx("lenet_loops.scala:184:12") } // CounterChainNew(List(x10270, x10269))
    val x10286 = withCtrl(x10293) { LoopController(style=InnerPipe, level=InnerControl, cchain=x10271).name("x10286").srcCtx("lenet_loops.scala:184:12") } // UnrolledReduce(List(b6068, b5444),x10271,x10268,Block((x10268) => Const(())),List(List(b6073), List(b6074)),List(List(b6075), List(b6076)))
    val b6073 = withCtrl(x10286) { CounterIter(x10270, Some(0)).name("b6073") } // b6073
    val b6075 = withCtrl(x10286) { Const(true).name("b6075") } // b6075
    val b6074 = withCtrl(x10286) { CounterIter(x10269, None).name("b6074") } // b6074
    val b6076 = withCtrl(x10286) { Const(true).name("b6076") } // b6076
    val x10272 = withCtrl(x10286) { OpDef(op=BitAnd, inputs=List(b6075, b6076)).name("x10272").srcCtx("UnrollingBase.scala:28:66") } // And(b6075,b6076)
    val x10273 = withCtrl(x10286) { OpDef(op=BitAnd, inputs=List(b6068, b5444)).name("x10273").srcCtx("UnrollingBase.scala:28:66") } // And(b6068,b5444)
    val x10274 = withCtrl(x10286) { OpDef(op=BitAnd, inputs=List(x10272, x10273)).name("x10274").srcCtx("UnrollingBase.scala:28:66") } // And(x10272,x10273)
    val x10275 = withCtrl(x10286) { LoadBanks(List(x10142_d0_b0), List(b6073, b6074)).name("x10275").srcCtx("lenet_loops.scala:183:22") } // ParSRAMLoad(x10142,List(List(b6073, b6074)),List(x10274))
    val x10276 = withCtrl(x10286) { x10275 } // VectorApply(x10275,0)
    val x10277 = withCtrl(x10286) { LoadBanks(List(x10233_d0_b0), List(b6067, b6073, b6074)).name("x10277").srcCtx("lenet_loops.scala:183:47") } // ParSRAMLoad(x10233,List(List(b6067, b6073, b6074)),List(x10274))
    val x10278 = withCtrl(x10286) { x10277 } // VectorApply(x10277,0)
    val x10279 = withCtrl(x10286) { OpDef(op=FixMul, inputs=List(x10276, x10278)).name("x10279").srcCtx("lenet_loops.scala:183:34") } // FixMul(x10276,x10278)
    val x10280 = withCtrl(x10286) { ReadMem(x10268_d1).name("x10280").srcCtx("lenet_loops.scala:184:12") } // RegRead(x10268)
    val x10281 = withCtrl(x10286) { OpDef(op=FixEql, inputs=List(b6073, Const(0))).name("x10281").srcCtx("lenet_loops.scala:184:12") } // FixEql(b6073,Const(0))
    val x10282 = withCtrl(x10286) { OpDef(op=FixEql, inputs=List(b6074, Const(0))).name("x10282").srcCtx("lenet_loops.scala:184:12") } // FixEql(b6074,Const(0))
    val x10283 = withCtrl(x10286) { OpDef(op=BitAnd, inputs=List(x10281, x10282)).name("x10283").srcCtx("lenet_loops.scala:184:12") } // And(x10281,x10282)
    val x10284 = withCtrl(x10286) { ReduceAccumOp(op=FixAdd, input=x10279, accum=x10280).name("x10284").srcCtx("lenet_loops.scala:184:14") } // FixAdd(x10279,x10280)
    val x10285_x10268_d0 = withCtrl(x10286) { WriteMem(x10268_d0, x10284).name("x10285_x10268_d0").srcCtx("lenet_loops.scala:184:12") } // RegWrite(x10268,x10284,x10273)
    antiDepsOf(x10285_x10268_d0)=List(x10280)
    val x10285_x10268_d1 = withCtrl(x10286) { WriteMem(x10268_d1, x10284).name("x10285_x10268_d1").srcCtx("lenet_loops.scala:184:12") } // RegWrite(x10268,x10284,x10273)
    antiDepsOf(x10285_x10268_d1)=List(x10280)
    val x10292 = withCtrl(x10293) { UnitController(style=SeqPipe, level=InnerControl).name("x10292").srcCtx("lenet_loops.scala:181:38") } // UnitPipe(List(b6068, b5444),Block(Const(())))
    val x10287 = withCtrl(x10292) { OpDef(op=BitAnd, inputs=List(b6068, b5444)).name("x10287").srcCtx("UnrollingBase.scala:28:66") } // And(b6068,b5444)
    val x10288 = withCtrl(x10292) { LoadBanks(List(x9739_d0_b0), List(b6067)).name("x10288").srcCtx("lenet_loops.scala:185:50") } // SRAMLoad(x9739,ArrayBuffer(Const(32)),List(b6067),Const(0),x10287)
    val x10289 = withCtrl(x10292) { ReadMem(x10268_d0).name("x10289").srcCtx("lenet_loops.scala:185:35") } // RegRead(x10268)
    val x10290 = withCtrl(x10292) { OpDef(op=FixAdd, inputs=List(x10289, x10288)).name("x10290").srcCtx("lenet_loops.scala:185:41") } // FixAdd(x10289,x10288)
    val x10291 = withCtrl(x10292) { StoreBanks(List(List(x10232_d0_b0)), List(b6067), x10290).name("x10291").srcCtx("lenet_loops.scala:185:28") } // SRAMStore(x10232,ArrayBuffer(Const(32)),List(b6067),Const(0),x10290,x10287)
    val x10294 = withCtrl(x10326) { Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x10294").srcCtx("lenet_loops.scala:190:44") } // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x10295 = withCtrl(x10326) { CounterChain(List(x10294)).name("x10295").srcCtx("lenet_loops.scala:190:44") } // CounterChainNew(List(x10294))
    val x10325 = withCtrl(x10326) { LoopController(style=StreamPipe, level=OuterControl, cchain=x10295).name("x10325").srcCtx("lenet_loops.scala:190:44") } // UnrolledForeach(List(b5444),x10295,Block(Const(())),List(List(b6101)),List(List(b6102)))
    val b6101 = withCtrl(x10325) { CounterIter(x10294, Some(0)).name("b6101") } // b6101
    val b6102 = withCtrl(x10325) { Const(true).name("b6102") } // b6102
    val b10401 = withCtrl(x10325) { StreamOut(field="offset").name("b10401").srcCtx("lenet_loops.scala:190:44") } // x10296 = StreamOutNew(BurstCmdBus)
    isAccum(b10401) = false
    bufferDepthOf(b10401) = 1
    val b10402 = withCtrl(x10325) { StreamOut(field="size").name("b10402").srcCtx("lenet_loops.scala:190:44") } // x10296 = StreamOutNew(BurstCmdBus)
    isAccum(b10402) = false
    bufferDepthOf(b10402) = 1
    val x10297 = withCtrl(x10325) { StreamOut(field="data").name("x10297").srcCtx("lenet_loops.scala:190:44") } // x10297 = StreamOutNew(BurstFullDataBus())
    isAccum(x10297) = false
    bufferDepthOf(x10297) = 1
    val x10298 = withCtrl(x10325) { StreamIn(field="ack").name("x10298").srcCtx("lenet_loops.scala:190:44") } // x10298 = StreamInNew(BurstAckBus)
    isAccum(x10298) = false
    bufferDepthOf(x10298) = 1
    val x10311 = withCtrl(x10325) { UnitController(style=SeqPipe, level=InnerControl).name("x10311").srcCtx("lenet_loops.scala:190:44") } // UnitPipe(List(b6102, b5444),Block(x10310))
    val x10299 = withCtrl(x10311) { OpDef(op=FixAdd, inputs=List(b5443, b6101)).name("x10299").srcCtx("lenet_loops.scala:190:44") } // FixAdd(b5443,b6101)
    val x10300 = withCtrl(x10311) { x10299 } // FixConvert(x10299,TRUE,_32,_0) (Same Type. No op)
    val x10301 = withCtrl(x10311) { OpDef(op=FixSla, inputs=List(x10300, Const(5))).name("x10301").srcCtx("lenet_loops.scala:190:44") } // FixLsh(x10300,Const(5))
    val x10302 = withCtrl(x10311) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x10303 = withCtrl(x10311) { OpDef(op=FixAdd, inputs=List(x10301, x10302)).name("x10303").srcCtx("lenet_loops.scala:190:44") } // FixAdd(x10301,x10302)
    val x10304 = withCtrl(x10311) { OpDef(op=FixSla, inputs=List(x10303, Const(2))).name("x10304").srcCtx("lenet_loops.scala:190:44") } // FixLsh(x10303,Const(2))
    val x10305 = withCtrl(x10311) { x10304 } // FixConvert(x10304,TRUE,_64,_0)
    val x10306 = withCtrl(x10311) { DramAddress(x9566).name("x10306").srcCtx("lenet_loops.scala:190:44") } // GetDRAMAddress(x9566)
    val x10308_x10307 = withCtrl(x10311) { OpDef(op=FixAdd, inputs=List(x10305, x10306)).name("x10308_x10307").srcCtx("lenet_loops.scala:190:44") } // FixAdd(x10305,x10306)
    // x10308 = SimpleStruct(ArrayBuffer((offset,x10307), (size,Const(128)), (isLoad,Const(false))))
    val x10309 = withCtrl(x10311) { OpDef(op=BitAnd, inputs=List(b6102, b5444)).name("x10309").srcCtx("UnrollingBase.scala:28:66") } // And(b6102,b5444)
    val x10310_b10403_b10401 = withCtrl(x10311) { WriteMem(b10401, x10308_x10307).name("x10310_b10403_b10401").srcCtx("lenet_loops.scala:190:44") } // StreamWrite(x10296,x10308,x10309)
    val x10310_b10404_b10402 = withCtrl(x10311) { WriteMem(b10402, Const(128)).name("x10310_b10404_b10402").srcCtx("lenet_loops.scala:190:44") } // StreamWrite(x10296,x10308,x10309)
    val x10312 = withCtrl(x10325) { Counter(min=Const(0), max=Const(32), step=Const(1), par=16).name("x10312").srcCtx("lenet_loops.scala:190:44") } // CounterNew(Const(0),Const(32),Const(1),Const(16))
    def split2 = {
    val x10313 = withCtrl(x10325) { CounterChain(List(x10312)).name("x10313").srcCtx("lenet_loops.scala:190:44") } // CounterChainNew(List(x10312))
    val x10320 = withCtrl(x10325) { LoopController(style=InnerPipe, level=InnerControl, cchain=x10313).name("x10320").srcCtx("lenet_loops.scala:190:44") } // UnrolledForeach(List(b6102, b5444),x10313,Block(Const(())),List(List(b6121)),List(List(b6122)))
    val b6121 = withCtrl(x10320) { CounterIter(x10312, None).name("b6121") } // b6121
    val b6122 = withCtrl(x10320) { Const(true).name("b6122") } // b6122
    val x10314 = withCtrl(x10320) { OpDef(op=BitAnd, inputs=List(b6122, b6102)).name("x10314").srcCtx("UnrollingBase.scala:28:66") } // And(b6122,b6102)
    val x10315 = withCtrl(x10320) { OpDef(op=BitAnd, inputs=List(x10314, b5444)).name("x10315").srcCtx("UnrollingBase.scala:28:66") } // And(x10314,b5444)
    val x10316 = withCtrl(x10320) { LoadBanks(List(x10232_d0_b0), List(b6121)).name("x10316").srcCtx("lenet_loops.scala:190:44") } // ParSRAMLoad(x10232,List(List(b6121)),List(x10315))
    val x10318_x10317 = withCtrl(x10320) { x10316 } // VectorApply(x10316,0)
    // x10318 = SimpleStruct(ArrayBuffer((_1,x10317), (_2,Const(true))))
    val x10319_x10319_x10297 = withCtrl(x10320) { WriteMem(x10297, x10318_x10317).name("x10319_x10319_x10297").srcCtx("lenet_loops.scala:190:44") } // ParStreamWrite(x10297,List(x10318),List(x10315))
    val x10321 = withCtrl(x10325) { FringeDenseStore(dram=List(x9566), cmdStream=List(b10401, b10402), dataStream=List(x10297), ackStream=List(x10298)).name("x10321").srcCtx("lenet_loops.scala:190:44") } // FringeDenseStore(x9566,x10296,x10297,x10298)
    val x10324 = withCtrl(x10325) { UnitController(style=SeqPipe, level=InnerControl).name("x10324").srcCtx("lenet_loops.scala:190:44") } // UnitPipe(List(b6102, b5444),Block(Const(())))
    val x10322 = withCtrl(x10324) { OpDef(op=BitAnd, inputs=List(b6102, b5444)).name("x10322").srcCtx("UnrollingBase.scala:28:66") } // And(b6102,b5444)
    val x10323_x10323 = withCtrl(x10324) { ReadMem(x10298).name("x10323_x10323").srcCtx("lenet_loops.scala:190:44") } // StreamRead(x10298,x10322)
    }; split2
    }; split1
    
  }
}
