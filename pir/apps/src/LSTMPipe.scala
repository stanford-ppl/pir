import pir._
import pir.node._
import arch._
import prism.enums._

object LSTMPipe extends PIRApp {
  def main(implicit design:PIRDesign) = {
    import design.pirmeta._
    val x5586 = withCtrl(design.top.topController) { DRAM(dims=List(Const(8), Const(128))).name("x5586").srcCtx("LSTMPipe.scala:24:23:dout") } // x5586 = DRAMNew(ArrayBuffer(Const(8), Const(128)),Const(0))
    val x5587 = withCtrl(design.top.topController) { DRAM(dims=List(Const(8), Const(128))).name("x5587").srcCtx("DataGenerator.scala:156:20:xInit") } // x5587 = DRAMNew(ArrayBuffer(Const(8), Const(128)),Const(0))
    val x5594 = withCtrl(design.top.topController) { DRAM(dims=List(Const(2), Const(128))).name("x5594").srcCtx("DataGenerator.scala:156:20:cInit") } // x5594 = DRAMNew(ArrayBuffer(Const(2), Const(128)),Const(0))
    val x5601 = withCtrl(design.top.topController) { DRAM(dims=List(Const(2), Const(128))).name("x5601").srcCtx("DataGenerator.scala:156:20:hInit") } // x5601 = DRAMNew(ArrayBuffer(Const(2), Const(128)),Const(0))
    val x5608 = withCtrl(design.top.topController) { DRAM(dims=List(Const(2), Const(128), Const(4), Const(128))).name("x5608").srcCtx("DataGenerator.scala:182:20:wxInit") } // x5608 = DRAMNew(ArrayBuffer(Const(2), Const(128), Const(4), Const(128)),Const(0))
    val x5621 = withCtrl(design.top.topController) { DRAM(dims=List(Const(2), Const(128), Const(4), Const(128))).name("x5621").srcCtx("DataGenerator.scala:182:20:whInit") } // x5621 = DRAMNew(ArrayBuffer(Const(2), Const(128), Const(4), Const(128)),Const(0))
    val x5634 = withCtrl(design.top.topController) { DRAM(dims=List(Const(2), Const(4), Const(128))).name("x5634").srcCtx("DataGenerator.scala:168:20:bInit") } // x5634 = DRAMNew(ArrayBuffer(Const(2), Const(4), Const(128)),Const(0))
    val x6031 = withCtrl(design.top.topController) { UnitController(style=SeqPipe, level=OuterControl).name("x6031").srcCtx("LSTMPipe.scala:34:11") } // Hwblock(Block(Const(())),false)
    val x5644_d0_b0 = withCtrl(x6031) { SRAM(size=1024, banking=Strided(banks=16, stride=1)).name("x5644_d0_b0").srcCtx("DataGenerator.scala:43:21:x") } // x5644 = SRAMNew(ArrayBuffer(Const(8), Const(128)))
    isAccum(x5644_d0_b0) = false
    bufferDepthOf(x5644_d0_b0) = 1
    staticDimsOf(x5644_d0_b0) = List(8, 128)
    val x5644_d1_b0 = withCtrl(x6031) { SRAM(size=1024, banking=Strided(banks=16, stride=1)).name("x5644_d1_b0").srcCtx("DataGenerator.scala:43:21:x") } // x5644 = SRAMNew(ArrayBuffer(Const(8), Const(128)))
    isAccum(x5644_d1_b0) = false
    bufferDepthOf(x5644_d1_b0) = 4
    staticDimsOf(x5644_d1_b0) = List(8, 128)
    val x5645 = withCtrl(x6031) { Counter(min=Const(0), max=Const(8), step=Const(1), par=1).name("x5645").srcCtx("DataGenerator.scala:44:8") } // CounterNew(Const(0),Const(8),Const(1),Const(1))
    val x5646 = withCtrl(x6031) { CounterChain(List(x5645)).name("x5646").srcCtx("DataGenerator.scala:44:8") } // CounterChainNew(List(x5645))
    val x5668 = withCtrl(x6031) { LoopController(style=StreamPipe, level=OuterControl, cchain=x5646).name("x5668").srcCtx("DataGenerator.scala:44:8") } // UnrolledForeach(List(Const(true)),x5646,Block(Const(())),List(List(b3296)),List(List(b3297)))
    val b3296 = withCtrl(x5668) { CounterIter(x5645, Some(0)).name("b3296") } // b3296
    val b3297 = withCtrl(x5668) { Const(true).name("b3297") } // b3297
    val b6042 = withCtrl(x5668) { StreamOut(field="offset").name("b6042").srcCtx("DataGenerator.scala:44:8") } // x5647 = StreamOutNew(BurstCmdBus)
    isAccum(b6042) = false
    bufferDepthOf(b6042) = 1
    val b6043 = withCtrl(x5668) { StreamOut(field="size").name("b6043").srcCtx("DataGenerator.scala:44:8") } // x5647 = StreamOutNew(BurstCmdBus)
    isAccum(b6043) = false
    bufferDepthOf(b6043) = 1
    val x5648 = withCtrl(x5668) { StreamIn(field="data").name("x5648").srcCtx("DataGenerator.scala:44:8") } // x5648 = StreamInNew(BurstDataBus())
    isAccum(x5648) = false
    bufferDepthOf(x5648) = 1
    val x5659 = withCtrl(x5668) { UnitController(style=SeqPipe, level=InnerControl).name("x5659").srcCtx("DataGenerator.scala:44:8") } // UnitPipe(List(b3297),Block(x5658))
    val x5649 = withCtrl(x5659) { b3296 } // FixConvert(b3296,TRUE,_32,_0) (Same Type. No op)
    val x5650 = withCtrl(x5659) { OpDef(op=FixSla, inputs=List(x5649, Const(7))).name("x5650").srcCtx("DataGenerator.scala:44:8") } // FixLsh(x5649,Const(7))
    val x5651 = withCtrl(x5659) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x5652 = withCtrl(x5659) { OpDef(op=FixAdd, inputs=List(x5650, x5651)).name("x5652").srcCtx("DataGenerator.scala:44:8") } // FixAdd(x5650,x5651)
    val x5653 = withCtrl(x5659) { OpDef(op=FixSla, inputs=List(x5652, Const(2))).name("x5653").srcCtx("DataGenerator.scala:44:8") } // FixLsh(x5652,Const(2))
    val x5654 = withCtrl(x5659) { x5653 } // FixConvert(x5653,TRUE,_64,_0)
    val x5655 = withCtrl(x5659) { DramAddress(x5587).name("x5655").srcCtx("DataGenerator.scala:44:8") } // GetDRAMAddress(x5587)
    val x5657_x5656 = withCtrl(x5659) { OpDef(op=FixAdd, inputs=List(x5654, x5655)).name("x5657_x5656").srcCtx("DataGenerator.scala:44:8") } // FixAdd(x5654,x5655)
    // x5657 = SimpleStruct(ArrayBuffer((offset,x5656), (size,Const(512)), (isLoad,Const(true))))
    val x5658_b6044_b6042 = withCtrl(x5659) { WriteMem(b6042, x5657_x5656).name("x5658_b6044_b6042").srcCtx("DataGenerator.scala:44:8") } // StreamWrite(x5647,x5657,b3297)
    val x5658_b6045_b6043 = withCtrl(x5659) { WriteMem(b6043, Const(512)).name("x5658_b6045_b6043").srcCtx("DataGenerator.scala:44:8") } // StreamWrite(x5647,x5657,b3297)
    val x5660 = withCtrl(x5668) { FringeDenseLoad(dram=List(x5587), cmdStream=List(b6042, b6043), dataStream=List(x5648)).name("x5660").srcCtx("DataGenerator.scala:44:8") } // FringeDenseLoad(x5587,x5647,x5648)
    val x5661 = withCtrl(x5668) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x5661").srcCtx("DataGenerator.scala:44:8") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x5662 = withCtrl(x5668) { CounterChain(List(x5661)).name("x5662").srcCtx("DataGenerator.scala:44:8") } // CounterChainNew(List(x5661))
    val x5667 = withCtrl(x5668) { LoopController(style=InnerPipe, level=InnerControl, cchain=x5662).name("x5667").srcCtx("DataGenerator.scala:44:8") } // UnrolledForeach(List(b3297),x5662,Block(Const(())),List(List(b3314)),List(List(b3315)))
    val b3314 = withCtrl(x5667) { CounterIter(x5661, None).name("b3314") } // b3314
    val b3315 = withCtrl(x5667) { Const(true).name("b3315") } // b3315
    val x5663 = withCtrl(x5667) { OpDef(op=BitAnd, inputs=List(b3315, b3297)).name("x5663").srcCtx("UnrollingBase.scala:28:66") } // And(b3315,b3297)
    val x5664_x5664 = withCtrl(x5667) { ReadMem(x5648).name("x5664_x5664").srcCtx("DataGenerator.scala:44:8") } // ParStreamRead(x5648,List(x5663))
    val x5665_x5665 = withCtrl(x5667) { x5664_x5664 } // VectorApply(x5664,0)
    val x5666 = withCtrl(x5667) { StoreBanks(List(List(x5644_d0_b0), List(x5644_d1_b0)), List(b3296, b3314), x5665_x5665).name("x5666").srcCtx("DataGenerator.scala:44:8") } // ParSRAMStore(x5644,List(List(b3296, b3314)),List(x5665),List(x5663))
    val x5669_d0_b0 = withCtrl(x6031) { SRAM(size=256, banking=Strided(banks=16, stride=1)).name("x5669_d0_b0").srcCtx("DataGenerator.scala:43:21:c") } // x5669 = SRAMNew(ArrayBuffer(Const(2), Const(128)))
    isAccum(x5669_d0_b0) = false
    bufferDepthOf(x5669_d0_b0) = 2
    staticDimsOf(x5669_d0_b0) = List(2, 128)
    val x5669_d1_b0 = withCtrl(x6031) { SRAM(size=256, banking=Strided(banks=16, stride=1)).name("x5669_d1_b0").srcCtx("DataGenerator.scala:43:21:c") } // x5669 = SRAMNew(ArrayBuffer(Const(2), Const(128)))
    isAccum(x5669_d1_b0) = false
    bufferDepthOf(x5669_d1_b0) = 2
    staticDimsOf(x5669_d1_b0) = List(2, 128)
    val x5670 = withCtrl(x6031) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x5670").srcCtx("DataGenerator.scala:44:8") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x5671 = withCtrl(x6031) { CounterChain(List(x5670)).name("x5671").srcCtx("DataGenerator.scala:44:8") } // CounterChainNew(List(x5670))
    val x5693 = withCtrl(x6031) { LoopController(style=StreamPipe, level=OuterControl, cchain=x5671).name("x5693").srcCtx("DataGenerator.scala:44:8") } // UnrolledForeach(List(Const(true)),x5671,Block(Const(())),List(List(b3325)),List(List(b3326)))
    val b3325 = withCtrl(x5693) { CounterIter(x5670, Some(0)).name("b3325") } // b3325
    val b3326 = withCtrl(x5693) { Const(true).name("b3326") } // b3326
    val b6046 = withCtrl(x5693) { StreamOut(field="offset").name("b6046").srcCtx("DataGenerator.scala:44:8") } // x5672 = StreamOutNew(BurstCmdBus)
    isAccum(b6046) = false
    bufferDepthOf(b6046) = 1
    val b6047 = withCtrl(x5693) { StreamOut(field="size").name("b6047").srcCtx("DataGenerator.scala:44:8") } // x5672 = StreamOutNew(BurstCmdBus)
    isAccum(b6047) = false
    bufferDepthOf(b6047) = 1
    val x5673 = withCtrl(x5693) { StreamIn(field="data").name("x5673").srcCtx("DataGenerator.scala:44:8") } // x5673 = StreamInNew(BurstDataBus())
    isAccum(x5673) = false
    bufferDepthOf(x5673) = 1
    val x5684 = withCtrl(x5693) { UnitController(style=SeqPipe, level=InnerControl).name("x5684").srcCtx("DataGenerator.scala:44:8") } // UnitPipe(List(b3326),Block(x5683))
    val x5674 = withCtrl(x5684) { b3325 } // FixConvert(b3325,TRUE,_32,_0) (Same Type. No op)
    val x5675 = withCtrl(x5684) { OpDef(op=FixSla, inputs=List(x5674, Const(7))).name("x5675").srcCtx("DataGenerator.scala:44:8") } // FixLsh(x5674,Const(7))
    val x5676 = withCtrl(x5684) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x5677 = withCtrl(x5684) { OpDef(op=FixAdd, inputs=List(x5675, x5676)).name("x5677").srcCtx("DataGenerator.scala:44:8") } // FixAdd(x5675,x5676)
    val x5678 = withCtrl(x5684) { OpDef(op=FixSla, inputs=List(x5677, Const(2))).name("x5678").srcCtx("DataGenerator.scala:44:8") } // FixLsh(x5677,Const(2))
    val x5679 = withCtrl(x5684) { x5678 } // FixConvert(x5678,TRUE,_64,_0)
    val x5680 = withCtrl(x5684) { DramAddress(x5594).name("x5680").srcCtx("DataGenerator.scala:44:8") } // GetDRAMAddress(x5594)
    val x5682_x5681 = withCtrl(x5684) { OpDef(op=FixAdd, inputs=List(x5679, x5680)).name("x5682_x5681").srcCtx("DataGenerator.scala:44:8") } // FixAdd(x5679,x5680)
    // x5682 = SimpleStruct(ArrayBuffer((offset,x5681), (size,Const(512)), (isLoad,Const(true))))
    val x5683_b6048_b6046 = withCtrl(x5684) { WriteMem(b6046, x5682_x5681).name("x5683_b6048_b6046").srcCtx("DataGenerator.scala:44:8") } // StreamWrite(x5672,x5682,b3326)
    val x5683_b6049_b6047 = withCtrl(x5684) { WriteMem(b6047, Const(512)).name("x5683_b6049_b6047").srcCtx("DataGenerator.scala:44:8") } // StreamWrite(x5672,x5682,b3326)
    val x5685 = withCtrl(x5693) { FringeDenseLoad(dram=List(x5594), cmdStream=List(b6046, b6047), dataStream=List(x5673)).name("x5685").srcCtx("DataGenerator.scala:44:8") } // FringeDenseLoad(x5594,x5672,x5673)
    val x5686 = withCtrl(x5693) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x5686").srcCtx("DataGenerator.scala:44:8") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x5687 = withCtrl(x5693) { CounterChain(List(x5686)).name("x5687").srcCtx("DataGenerator.scala:44:8") } // CounterChainNew(List(x5686))
    val x5692 = withCtrl(x5693) { LoopController(style=InnerPipe, level=InnerControl, cchain=x5687).name("x5692").srcCtx("DataGenerator.scala:44:8") } // UnrolledForeach(List(b3326),x5687,Block(Const(())),List(List(b3343)),List(List(b3344)))
    val b3343 = withCtrl(x5692) { CounterIter(x5686, None).name("b3343") } // b3343
    val b3344 = withCtrl(x5692) { Const(true).name("b3344") } // b3344
    val x5688 = withCtrl(x5692) { OpDef(op=BitAnd, inputs=List(b3344, b3326)).name("x5688").srcCtx("UnrollingBase.scala:28:66") } // And(b3344,b3326)
    val x5689_x5689 = withCtrl(x5692) { ReadMem(x5673).name("x5689_x5689").srcCtx("DataGenerator.scala:44:8") } // ParStreamRead(x5673,List(x5688))
    val x5690_x5690 = withCtrl(x5692) { x5689_x5689 } // VectorApply(x5689,0)
    val x5691 = withCtrl(x5692) { StoreBanks(List(List(x5669_d0_b0), List(x5669_d1_b0)), List(b3325, b3343), x5690_x5690).name("x5691").srcCtx("DataGenerator.scala:44:8") } // ParSRAMStore(x5669,List(List(b3325, b3343)),List(x5690),List(x5688))
    val x5694_d0_b0 = withCtrl(x6031) { SRAM(size=256, banking=Strided(banks=16, stride=1)).name("x5694_d0_b0").srcCtx("DataGenerator.scala:43:21:h") } // x5694 = SRAMNew(ArrayBuffer(Const(2), Const(128)))
    isAccum(x5694_d0_b0) = false
    bufferDepthOf(x5694_d0_b0) = 4
    staticDimsOf(x5694_d0_b0) = List(2, 128)
    val x5695 = withCtrl(x6031) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x5695").srcCtx("DataGenerator.scala:44:8") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x5696 = withCtrl(x6031) { CounterChain(List(x5695)).name("x5696").srcCtx("DataGenerator.scala:44:8") } // CounterChainNew(List(x5695))
    val x5718 = withCtrl(x6031) { LoopController(style=StreamPipe, level=OuterControl, cchain=x5696).name("x5718").srcCtx("DataGenerator.scala:44:8") } // UnrolledForeach(List(Const(true)),x5696,Block(Const(())),List(List(b3354)),List(List(b3355)))
    val b3354 = withCtrl(x5718) { CounterIter(x5695, Some(0)).name("b3354") } // b3354
    val b3355 = withCtrl(x5718) { Const(true).name("b3355") } // b3355
    val b6050 = withCtrl(x5718) { StreamOut(field="offset").name("b6050").srcCtx("DataGenerator.scala:44:8") } // x5697 = StreamOutNew(BurstCmdBus)
    isAccum(b6050) = false
    bufferDepthOf(b6050) = 1
    val b6051 = withCtrl(x5718) { StreamOut(field="size").name("b6051").srcCtx("DataGenerator.scala:44:8") } // x5697 = StreamOutNew(BurstCmdBus)
    isAccum(b6051) = false
    bufferDepthOf(b6051) = 1
    val x5698 = withCtrl(x5718) { StreamIn(field="data").name("x5698").srcCtx("DataGenerator.scala:44:8") } // x5698 = StreamInNew(BurstDataBus())
    isAccum(x5698) = false
    bufferDepthOf(x5698) = 1
    val x5709 = withCtrl(x5718) { UnitController(style=SeqPipe, level=InnerControl).name("x5709").srcCtx("DataGenerator.scala:44:8") } // UnitPipe(List(b3355),Block(x5708))
    val x5699 = withCtrl(x5709) { b3354 } // FixConvert(b3354,TRUE,_32,_0) (Same Type. No op)
    val x5700 = withCtrl(x5709) { OpDef(op=FixSla, inputs=List(x5699, Const(7))).name("x5700").srcCtx("DataGenerator.scala:44:8") } // FixLsh(x5699,Const(7))
    val x5701 = withCtrl(x5709) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x5702 = withCtrl(x5709) { OpDef(op=FixAdd, inputs=List(x5700, x5701)).name("x5702").srcCtx("DataGenerator.scala:44:8") } // FixAdd(x5700,x5701)
    val x5703 = withCtrl(x5709) { OpDef(op=FixSla, inputs=List(x5702, Const(2))).name("x5703").srcCtx("DataGenerator.scala:44:8") } // FixLsh(x5702,Const(2))
    val x5704 = withCtrl(x5709) { x5703 } // FixConvert(x5703,TRUE,_64,_0)
    val x5705 = withCtrl(x5709) { DramAddress(x5601).name("x5705").srcCtx("DataGenerator.scala:44:8") } // GetDRAMAddress(x5601)
    val x5707_x5706 = withCtrl(x5709) { OpDef(op=FixAdd, inputs=List(x5704, x5705)).name("x5707_x5706").srcCtx("DataGenerator.scala:44:8") } // FixAdd(x5704,x5705)
    // x5707 = SimpleStruct(ArrayBuffer((offset,x5706), (size,Const(512)), (isLoad,Const(true))))
    val x5708_b6052_b6050 = withCtrl(x5709) { WriteMem(b6050, x5707_x5706).name("x5708_b6052_b6050").srcCtx("DataGenerator.scala:44:8") } // StreamWrite(x5697,x5707,b3355)
    val x5708_b6053_b6051 = withCtrl(x5709) { WriteMem(b6051, Const(512)).name("x5708_b6053_b6051").srcCtx("DataGenerator.scala:44:8") } // StreamWrite(x5697,x5707,b3355)
    val x5710 = withCtrl(x5718) { FringeDenseLoad(dram=List(x5601), cmdStream=List(b6050, b6051), dataStream=List(x5698)).name("x5710").srcCtx("DataGenerator.scala:44:8") } // FringeDenseLoad(x5601,x5697,x5698)
    val x5711 = withCtrl(x5718) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x5711").srcCtx("DataGenerator.scala:44:8") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x5712 = withCtrl(x5718) { CounterChain(List(x5711)).name("x5712").srcCtx("DataGenerator.scala:44:8") } // CounterChainNew(List(x5711))
    val x5717 = withCtrl(x5718) { LoopController(style=InnerPipe, level=InnerControl, cchain=x5712).name("x5717").srcCtx("DataGenerator.scala:44:8") } // UnrolledForeach(List(b3355),x5712,Block(Const(())),List(List(b3372)),List(List(b3373)))
    val b3372 = withCtrl(x5717) { CounterIter(x5711, None).name("b3372") } // b3372
    val b3373 = withCtrl(x5717) { Const(true).name("b3373") } // b3373
    val x5713 = withCtrl(x5717) { OpDef(op=BitAnd, inputs=List(b3373, b3355)).name("x5713").srcCtx("UnrollingBase.scala:28:66") } // And(b3373,b3355)
    val x5714_x5714 = withCtrl(x5717) { ReadMem(x5698).name("x5714_x5714").srcCtx("DataGenerator.scala:44:8") } // ParStreamRead(x5698,List(x5713))
    val x5715_x5715 = withCtrl(x5717) { x5714_x5714 } // VectorApply(x5714,0)
    val x5716 = withCtrl(x5717) { StoreBanks(List(List(x5694_d0_b0)), List(b3354, b3372), x5715_x5715).name("x5716").srcCtx("DataGenerator.scala:44:8") } // ParSRAMStore(x5694,List(List(b3354, b3372)),List(x5715),List(x5713))
    val x5719_d0_b0 = withCtrl(x6031) { SRAM(size=131072, banking=Strided(banks=16, stride=1)).name("x5719_d0_b0").srcCtx("DataGenerator.scala:76:21:wx") } // x5719 = SRAMNew(ArrayBuffer(Const(2), Const(128), Const(4), Const(128)))
    isAccum(x5719_d0_b0) = false
    bufferDepthOf(x5719_d0_b0) = 1
    staticDimsOf(x5719_d0_b0) = List(2, 128, 4, 128)
    val x5720 = withCtrl(x6031) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x5720").srcCtx("DataGenerator.scala:77:8") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x5721 = withCtrl(x6031) { Counter(min=Const(0), max=Const(128), step=Const(1), par=1).name("x5721").srcCtx("DataGenerator.scala:77:8") } // CounterNew(Const(0),Const(128),Const(1),Const(1))
    val x5722 = withCtrl(x6031) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x5722").srcCtx("DataGenerator.scala:77:8") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x5723 = withCtrl(x6031) { CounterChain(List(x5720,x5721,x5722)).name("x5723").srcCtx("DataGenerator.scala:77:8") } // CounterChainNew(List(x5720, x5721, x5722))
    val x5755 = withCtrl(x6031) { LoopController(style=StreamPipe, level=OuterControl, cchain=x5723).name("x5755").srcCtx("DataGenerator.scala:77:8") } // UnrolledForeach(List(Const(true)),x5723,Block(Const(())),List(List(b3385), List(b3386), List(b3387)),List(List(b3388), List(b3389), List(b3390)))
    val b3385 = withCtrl(x5755) { CounterIter(x5720, Some(0)).name("b3385") } // b3385
    val b3388 = withCtrl(x5755) { Const(true).name("b3388") } // b3388
    val b3386 = withCtrl(x5755) { CounterIter(x5721, Some(0)).name("b3386") } // b3386
    val b3389 = withCtrl(x5755) { Const(true).name("b3389") } // b3389
    val b3387 = withCtrl(x5755) { CounterIter(x5722, Some(0)).name("b3387") } // b3387
    val b3390 = withCtrl(x5755) { Const(true).name("b3390") } // b3390
    val b6054 = withCtrl(x5755) { StreamOut(field="offset").name("b6054").srcCtx("DataGenerator.scala:77:8") } // x5724 = StreamOutNew(BurstCmdBus)
    isAccum(b6054) = false
    bufferDepthOf(b6054) = 1
    val b6055 = withCtrl(x5755) { StreamOut(field="size").name("b6055").srcCtx("DataGenerator.scala:77:8") } // x5724 = StreamOutNew(BurstCmdBus)
    isAccum(b6055) = false
    bufferDepthOf(b6055) = 1
    val x5725 = withCtrl(x5755) { StreamIn(field="data").name("x5725").srcCtx("DataGenerator.scala:77:8") } // x5725 = StreamInNew(BurstDataBus())
    isAccum(x5725) = false
    bufferDepthOf(x5725) = 1
    val x5744 = withCtrl(x5755) { UnitController(style=SeqPipe, level=InnerControl).name("x5744").srcCtx("DataGenerator.scala:77:8") } // UnitPipe(List(b3388, b3389, b3390),Block(x5743))
    val x5726 = withCtrl(x5744) { b3385 } // FixConvert(b3385,TRUE,_32,_0) (Same Type. No op)
    val x5727 = withCtrl(x5744) { OpDef(op=FixSla, inputs=List(x5726, Const(16))).name("x5727").srcCtx("DataGenerator.scala:77:8") } // FixLsh(x5726,Const(16))
    val x5728 = withCtrl(x5744) { b3386 } // FixConvert(b3386,TRUE,_32,_0) (Same Type. No op)
    val x5729 = withCtrl(x5744) { OpDef(op=FixSla, inputs=List(x5728, Const(9))).name("x5729").srcCtx("DataGenerator.scala:77:8") } // FixLsh(x5728,Const(9))
    val x5730 = withCtrl(x5744) { b3387 } // FixConvert(b3387,TRUE,_32,_0) (Same Type. No op)
    val x5731 = withCtrl(x5744) { OpDef(op=FixSla, inputs=List(x5730, Const(7))).name("x5731").srcCtx("DataGenerator.scala:77:8") } // FixLsh(x5730,Const(7))
    val x5732 = withCtrl(x5744) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x5733 = withCtrl(x5744) { OpDef(op=FixAdd, inputs=List(x5727, x5729)).name("x5733").srcCtx("DataGenerator.scala:77:8") } // FixAdd(x5727,x5729)
    val x5734 = withCtrl(x5744) { OpDef(op=FixAdd, inputs=List(x5731, x5732)).name("x5734").srcCtx("DataGenerator.scala:77:8") } // FixAdd(x5731,x5732)
    val x5735 = withCtrl(x5744) { OpDef(op=FixAdd, inputs=List(x5733, x5734)).name("x5735").srcCtx("DataGenerator.scala:77:8") } // FixAdd(x5733,x5734)
    val x5736 = withCtrl(x5744) { OpDef(op=FixSla, inputs=List(x5735, Const(2))).name("x5736").srcCtx("DataGenerator.scala:77:8") } // FixLsh(x5735,Const(2))
    val x5737 = withCtrl(x5744) { x5736 } // FixConvert(x5736,TRUE,_64,_0)
    val x5738 = withCtrl(x5744) { DramAddress(x5608).name("x5738").srcCtx("DataGenerator.scala:77:8") } // GetDRAMAddress(x5608)
    val x5740_x5739 = withCtrl(x5744) { OpDef(op=FixAdd, inputs=List(x5737, x5738)).name("x5740_x5739").srcCtx("DataGenerator.scala:77:8") } // FixAdd(x5737,x5738)
    // x5740 = SimpleStruct(ArrayBuffer((offset,x5739), (size,Const(512)), (isLoad,Const(true))))
    val x5741 = withCtrl(x5744) { OpDef(op=BitAnd, inputs=List(b3388, b3389)).name("x5741").srcCtx("UnrollingBase.scala:28:66") } // And(b3388,b3389)
    val x5742 = withCtrl(x5744) { OpDef(op=BitAnd, inputs=List(x5741, b3390)).name("x5742").srcCtx("UnrollingBase.scala:28:66") } // And(x5741,b3390)
    val x5743_b6056_b6054 = withCtrl(x5744) { WriteMem(b6054, x5740_x5739).name("x5743_b6056_b6054").srcCtx("DataGenerator.scala:77:8") } // StreamWrite(x5724,x5740,x5742)
    val x5743_b6057_b6055 = withCtrl(x5744) { WriteMem(b6055, Const(512)).name("x5743_b6057_b6055").srcCtx("DataGenerator.scala:77:8") } // StreamWrite(x5724,x5740,x5742)
    val x5745 = withCtrl(x5755) { FringeDenseLoad(dram=List(x5608), cmdStream=List(b6054, b6055), dataStream=List(x5725)).name("x5745").srcCtx("DataGenerator.scala:77:8") } // FringeDenseLoad(x5608,x5724,x5725)
    val x5746 = withCtrl(x5755) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x5746").srcCtx("DataGenerator.scala:77:8") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x5747 = withCtrl(x5755) { CounterChain(List(x5746)).name("x5747").srcCtx("DataGenerator.scala:77:8") } // CounterChainNew(List(x5746))
    val x5754 = withCtrl(x5755) { LoopController(style=InnerPipe, level=InnerControl, cchain=x5747).name("x5754").srcCtx("DataGenerator.scala:77:8") } // UnrolledForeach(List(b3388, b3389, b3390),x5747,Block(Const(())),List(List(b3415)),List(List(b3416)))
    val b3415 = withCtrl(x5754) { CounterIter(x5746, None).name("b3415") } // b3415
    val b3416 = withCtrl(x5754) { Const(true).name("b3416") } // b3416
    val x5748 = withCtrl(x5754) { OpDef(op=BitAnd, inputs=List(b3416, b3388)).name("x5748").srcCtx("UnrollingBase.scala:28:66") } // And(b3416,b3388)
    val x5749 = withCtrl(x5754) { OpDef(op=BitAnd, inputs=List(b3389, b3390)).name("x5749").srcCtx("UnrollingBase.scala:28:66") } // And(b3389,b3390)
    val x5750 = withCtrl(x5754) { OpDef(op=BitAnd, inputs=List(x5748, x5749)).name("x5750").srcCtx("UnrollingBase.scala:28:66") } // And(x5748,x5749)
    val x5751_x5751 = withCtrl(x5754) { ReadMem(x5725).name("x5751_x5751").srcCtx("DataGenerator.scala:77:8") } // ParStreamRead(x5725,List(x5750))
    val x5752_x5752 = withCtrl(x5754) { x5751_x5751 } // VectorApply(x5751,0)
    val x5753 = withCtrl(x5754) { StoreBanks(List(List(x5719_d0_b0)), List(b3385, b3386, b3387, b3415), x5752_x5752).name("x5753").srcCtx("DataGenerator.scala:77:8") } // ParSRAMStore(x5719,List(List(b3385, b3386, b3387, b3415)),List(x5752),List(x5750))
    val x5756_d0_b0 = withCtrl(x6031) { SRAM(size=131072, banking=Strided(banks=16, stride=1)).name("x5756_d0_b0").srcCtx("DataGenerator.scala:76:21:wh") } // x5756 = SRAMNew(ArrayBuffer(Const(2), Const(128), Const(4), Const(128)))
    isAccum(x5756_d0_b0) = false
    bufferDepthOf(x5756_d0_b0) = 1
    staticDimsOf(x5756_d0_b0) = List(2, 128, 4, 128)
    val x5757 = withCtrl(x6031) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x5757").srcCtx("DataGenerator.scala:77:8") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x5758 = withCtrl(x6031) { Counter(min=Const(0), max=Const(128), step=Const(1), par=1).name("x5758").srcCtx("DataGenerator.scala:77:8") } // CounterNew(Const(0),Const(128),Const(1),Const(1))
    val x5759 = withCtrl(x6031) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x5759").srcCtx("DataGenerator.scala:77:8") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x5760 = withCtrl(x6031) { CounterChain(List(x5757,x5758,x5759)).name("x5760").srcCtx("DataGenerator.scala:77:8") } // CounterChainNew(List(x5757, x5758, x5759))
    val x5792 = withCtrl(x6031) { LoopController(style=StreamPipe, level=OuterControl, cchain=x5760).name("x5792").srcCtx("DataGenerator.scala:77:8") } // UnrolledForeach(List(Const(true)),x5760,Block(Const(())),List(List(b3430), List(b3431), List(b3432)),List(List(b3433), List(b3434), List(b3435)))
    val b3430 = withCtrl(x5792) { CounterIter(x5757, Some(0)).name("b3430") } // b3430
    val b3433 = withCtrl(x5792) { Const(true).name("b3433") } // b3433
    val b3431 = withCtrl(x5792) { CounterIter(x5758, Some(0)).name("b3431") } // b3431
    val b3434 = withCtrl(x5792) { Const(true).name("b3434") } // b3434
    val b3432 = withCtrl(x5792) { CounterIter(x5759, Some(0)).name("b3432") } // b3432
    val b3435 = withCtrl(x5792) { Const(true).name("b3435") } // b3435
    val b6058 = withCtrl(x5792) { StreamOut(field="offset").name("b6058").srcCtx("DataGenerator.scala:77:8") } // x5761 = StreamOutNew(BurstCmdBus)
    isAccum(b6058) = false
    bufferDepthOf(b6058) = 1
    val b6059 = withCtrl(x5792) { StreamOut(field="size").name("b6059").srcCtx("DataGenerator.scala:77:8") } // x5761 = StreamOutNew(BurstCmdBus)
    isAccum(b6059) = false
    bufferDepthOf(b6059) = 1
    val x5762 = withCtrl(x5792) { StreamIn(field="data").name("x5762").srcCtx("DataGenerator.scala:77:8") } // x5762 = StreamInNew(BurstDataBus())
    isAccum(x5762) = false
    bufferDepthOf(x5762) = 1
    val x5781 = withCtrl(x5792) { UnitController(style=SeqPipe, level=InnerControl).name("x5781").srcCtx("DataGenerator.scala:77:8") } // UnitPipe(List(b3433, b3434, b3435),Block(x5780))
    val x5763 = withCtrl(x5781) { b3430 } // FixConvert(b3430,TRUE,_32,_0) (Same Type. No op)
    val x5764 = withCtrl(x5781) { OpDef(op=FixSla, inputs=List(x5763, Const(16))).name("x5764").srcCtx("DataGenerator.scala:77:8") } // FixLsh(x5763,Const(16))
    val x5765 = withCtrl(x5781) { b3431 } // FixConvert(b3431,TRUE,_32,_0) (Same Type. No op)
    val x5766 = withCtrl(x5781) { OpDef(op=FixSla, inputs=List(x5765, Const(9))).name("x5766").srcCtx("DataGenerator.scala:77:8") } // FixLsh(x5765,Const(9))
    val x5767 = withCtrl(x5781) { b3432 } // FixConvert(b3432,TRUE,_32,_0) (Same Type. No op)
    val x5768 = withCtrl(x5781) { OpDef(op=FixSla, inputs=List(x5767, Const(7))).name("x5768").srcCtx("DataGenerator.scala:77:8") } // FixLsh(x5767,Const(7))
    val x5769 = withCtrl(x5781) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x5770 = withCtrl(x5781) { OpDef(op=FixAdd, inputs=List(x5764, x5766)).name("x5770").srcCtx("DataGenerator.scala:77:8") } // FixAdd(x5764,x5766)
    val x5771 = withCtrl(x5781) { OpDef(op=FixAdd, inputs=List(x5768, x5769)).name("x5771").srcCtx("DataGenerator.scala:77:8") } // FixAdd(x5768,x5769)
    val x5772 = withCtrl(x5781) { OpDef(op=FixAdd, inputs=List(x5770, x5771)).name("x5772").srcCtx("DataGenerator.scala:77:8") } // FixAdd(x5770,x5771)
    val x5773 = withCtrl(x5781) { OpDef(op=FixSla, inputs=List(x5772, Const(2))).name("x5773").srcCtx("DataGenerator.scala:77:8") } // FixLsh(x5772,Const(2))
    val x5774 = withCtrl(x5781) { x5773 } // FixConvert(x5773,TRUE,_64,_0)
    val x5775 = withCtrl(x5781) { DramAddress(x5621).name("x5775").srcCtx("DataGenerator.scala:77:8") } // GetDRAMAddress(x5621)
    val x5777_x5776 = withCtrl(x5781) { OpDef(op=FixAdd, inputs=List(x5774, x5775)).name("x5777_x5776").srcCtx("DataGenerator.scala:77:8") } // FixAdd(x5774,x5775)
    // x5777 = SimpleStruct(ArrayBuffer((offset,x5776), (size,Const(512)), (isLoad,Const(true))))
    val x5778 = withCtrl(x5781) { OpDef(op=BitAnd, inputs=List(b3433, b3434)).name("x5778").srcCtx("UnrollingBase.scala:28:66") } // And(b3433,b3434)
    val x5779 = withCtrl(x5781) { OpDef(op=BitAnd, inputs=List(x5778, b3435)).name("x5779").srcCtx("UnrollingBase.scala:28:66") } // And(x5778,b3435)
    val x5780_b6060_b6058 = withCtrl(x5781) { WriteMem(b6058, x5777_x5776).name("x5780_b6060_b6058").srcCtx("DataGenerator.scala:77:8") } // StreamWrite(x5761,x5777,x5779)
    val x5780_b6061_b6059 = withCtrl(x5781) { WriteMem(b6059, Const(512)).name("x5780_b6061_b6059").srcCtx("DataGenerator.scala:77:8") } // StreamWrite(x5761,x5777,x5779)
    val x5782 = withCtrl(x5792) { FringeDenseLoad(dram=List(x5621), cmdStream=List(b6058, b6059), dataStream=List(x5762)).name("x5782").srcCtx("DataGenerator.scala:77:8") } // FringeDenseLoad(x5621,x5761,x5762)
    val x5783 = withCtrl(x5792) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x5783").srcCtx("DataGenerator.scala:77:8") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x5784 = withCtrl(x5792) { CounterChain(List(x5783)).name("x5784").srcCtx("DataGenerator.scala:77:8") } // CounterChainNew(List(x5783))
    val x5791 = withCtrl(x5792) { LoopController(style=InnerPipe, level=InnerControl, cchain=x5784).name("x5791").srcCtx("DataGenerator.scala:77:8") } // UnrolledForeach(List(b3433, b3434, b3435),x5784,Block(Const(())),List(List(b3460)),List(List(b3461)))
    val b3460 = withCtrl(x5791) { CounterIter(x5783, None).name("b3460") } // b3460
    val b3461 = withCtrl(x5791) { Const(true).name("b3461") } // b3461
    val x5785 = withCtrl(x5791) { OpDef(op=BitAnd, inputs=List(b3461, b3433)).name("x5785").srcCtx("UnrollingBase.scala:28:66") } // And(b3461,b3433)
    val x5786 = withCtrl(x5791) { OpDef(op=BitAnd, inputs=List(b3434, b3435)).name("x5786").srcCtx("UnrollingBase.scala:28:66") } // And(b3434,b3435)
    val x5787 = withCtrl(x5791) { OpDef(op=BitAnd, inputs=List(x5785, x5786)).name("x5787").srcCtx("UnrollingBase.scala:28:66") } // And(x5785,x5786)
    val x5788_x5788 = withCtrl(x5791) { ReadMem(x5762).name("x5788_x5788").srcCtx("DataGenerator.scala:77:8") } // ParStreamRead(x5762,List(x5787))
    val x5789_x5789 = withCtrl(x5791) { x5788_x5788 } // VectorApply(x5788,0)
    val x5790 = withCtrl(x5791) { StoreBanks(List(List(x5756_d0_b0)), List(b3430, b3431, b3432, b3460), x5789_x5789).name("x5790").srcCtx("DataGenerator.scala:77:8") } // ParSRAMStore(x5756,List(List(b3430, b3431, b3432, b3460)),List(x5789),List(x5787))
    val x5793_d0_b0 = withCtrl(x6031) { SRAM(size=1024, banking=Strided(banks=16, stride=1)).name("x5793_d0_b0").srcCtx("DataGenerator.scala:59:21:b") } // x5793 = SRAMNew(ArrayBuffer(Const(2), Const(4), Const(128)))
    isAccum(x5793_d0_b0) = false
    bufferDepthOf(x5793_d0_b0) = 1
    staticDimsOf(x5793_d0_b0) = List(2, 4, 128)
    val x5794 = withCtrl(x6031) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x5794").srcCtx("DataGenerator.scala:60:8") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x5795 = withCtrl(x6031) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x5795").srcCtx("DataGenerator.scala:60:8") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x5796 = withCtrl(x6031) { CounterChain(List(x5794,x5795)).name("x5796").srcCtx("DataGenerator.scala:60:8") } // CounterChainNew(List(x5794, x5795))
    val x5823 = withCtrl(x6031) { LoopController(style=StreamPipe, level=OuterControl, cchain=x5796).name("x5823").srcCtx("DataGenerator.scala:60:8") } // UnrolledForeach(List(Const(true)),x5796,Block(Const(())),List(List(b3474), List(b3475)),List(List(b3476), List(b3477)))
    val b3474 = withCtrl(x5823) { CounterIter(x5794, Some(0)).name("b3474") } // b3474
    val b3476 = withCtrl(x5823) { Const(true).name("b3476") } // b3476
    val b3475 = withCtrl(x5823) { CounterIter(x5795, Some(0)).name("b3475") } // b3475
    val b3477 = withCtrl(x5823) { Const(true).name("b3477") } // b3477
    val b6062 = withCtrl(x5823) { StreamOut(field="offset").name("b6062").srcCtx("DataGenerator.scala:60:8") } // x5797 = StreamOutNew(BurstCmdBus)
    isAccum(b6062) = false
    bufferDepthOf(b6062) = 1
    val b6063 = withCtrl(x5823) { StreamOut(field="size").name("b6063").srcCtx("DataGenerator.scala:60:8") } // x5797 = StreamOutNew(BurstCmdBus)
    isAccum(b6063) = false
    bufferDepthOf(b6063) = 1
    val x5798 = withCtrl(x5823) { StreamIn(field="data").name("x5798").srcCtx("DataGenerator.scala:60:8") } // x5798 = StreamInNew(BurstDataBus())
    isAccum(x5798) = false
    bufferDepthOf(x5798) = 1
    val x5813 = withCtrl(x5823) { UnitController(style=SeqPipe, level=InnerControl).name("x5813").srcCtx("DataGenerator.scala:60:8") } // UnitPipe(List(b3476, b3477),Block(x5812))
    val x5799 = withCtrl(x5813) { b3474 } // FixConvert(b3474,TRUE,_32,_0) (Same Type. No op)
    val x5800 = withCtrl(x5813) { OpDef(op=FixSla, inputs=List(x5799, Const(9))).name("x5800").srcCtx("DataGenerator.scala:60:8") } // FixLsh(x5799,Const(9))
    val x5801 = withCtrl(x5813) { b3475 } // FixConvert(b3475,TRUE,_32,_0) (Same Type. No op)
    val x5802 = withCtrl(x5813) { OpDef(op=FixSla, inputs=List(x5801, Const(7))).name("x5802").srcCtx("DataGenerator.scala:60:8") } // FixLsh(x5801,Const(7))
    val x5803 = withCtrl(x5813) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x5804 = withCtrl(x5813) { OpDef(op=FixAdd, inputs=List(x5800, x5802)).name("x5804").srcCtx("DataGenerator.scala:60:8") } // FixAdd(x5800,x5802)
    val x5805 = withCtrl(x5813) { OpDef(op=FixAdd, inputs=List(x5804, x5803)).name("x5805").srcCtx("DataGenerator.scala:60:8") } // FixAdd(x5804,x5803)
    val x5806 = withCtrl(x5813) { OpDef(op=FixSla, inputs=List(x5805, Const(2))).name("x5806").srcCtx("DataGenerator.scala:60:8") } // FixLsh(x5805,Const(2))
    val x5807 = withCtrl(x5813) { x5806 } // FixConvert(x5806,TRUE,_64,_0)
    val x5808 = withCtrl(x5813) { DramAddress(x5634).name("x5808").srcCtx("DataGenerator.scala:60:8") } // GetDRAMAddress(x5634)
    val x5810_x5809 = withCtrl(x5813) { OpDef(op=FixAdd, inputs=List(x5807, x5808)).name("x5810_x5809").srcCtx("DataGenerator.scala:60:8") } // FixAdd(x5807,x5808)
    // x5810 = SimpleStruct(ArrayBuffer((offset,x5809), (size,Const(512)), (isLoad,Const(true))))
    val x5811 = withCtrl(x5813) { OpDef(op=BitAnd, inputs=List(b3476, b3477)).name("x5811").srcCtx("UnrollingBase.scala:28:66") } // And(b3476,b3477)
    val x5812_b6064_b6062 = withCtrl(x5813) { WriteMem(b6062, x5810_x5809).name("x5812_b6064_b6062").srcCtx("DataGenerator.scala:60:8") } // StreamWrite(x5797,x5810,x5811)
    val x5812_b6065_b6063 = withCtrl(x5813) { WriteMem(b6063, Const(512)).name("x5812_b6065_b6063").srcCtx("DataGenerator.scala:60:8") } // StreamWrite(x5797,x5810,x5811)
    val x5814 = withCtrl(x5823) { FringeDenseLoad(dram=List(x5634), cmdStream=List(b6062, b6063), dataStream=List(x5798)).name("x5814").srcCtx("DataGenerator.scala:60:8") } // FringeDenseLoad(x5634,x5797,x5798)
    val x5815 = withCtrl(x5823) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x5815").srcCtx("DataGenerator.scala:60:8") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x5816 = withCtrl(x5823) { CounterChain(List(x5815)).name("x5816").srcCtx("DataGenerator.scala:60:8") } // CounterChainNew(List(x5815))
    val x5822 = withCtrl(x5823) { LoopController(style=InnerPipe, level=InnerControl, cchain=x5816).name("x5822").srcCtx("DataGenerator.scala:60:8") } // UnrolledForeach(List(b3476, b3477),x5816,Block(Const(())),List(List(b3498)),List(List(b3499)))
    val b3498 = withCtrl(x5822) { CounterIter(x5815, None).name("b3498") } // b3498
    val b3499 = withCtrl(x5822) { Const(true).name("b3499") } // b3499
    val x5817 = withCtrl(x5822) { OpDef(op=BitAnd, inputs=List(b3499, b3476)).name("x5817").srcCtx("UnrollingBase.scala:28:66") } // And(b3499,b3476)
    val x5818 = withCtrl(x5822) { OpDef(op=BitAnd, inputs=List(x5817, b3477)).name("x5818").srcCtx("UnrollingBase.scala:28:66") } // And(x5817,b3477)
    val x5819_x5819 = withCtrl(x5822) { ReadMem(x5798).name("x5819_x5819").srcCtx("DataGenerator.scala:60:8") } // ParStreamRead(x5798,List(x5818))
    val x5820_x5820 = withCtrl(x5822) { x5819_x5819 } // VectorApply(x5819,0)
    val x5821 = withCtrl(x5822) { StoreBanks(List(List(x5793_d0_b0)), List(b3474, b3475, b3498), x5820_x5820).name("x5821").srcCtx("DataGenerator.scala:60:8") } // ParSRAMStore(x5793,List(List(b3474, b3475, b3498)),List(x5820),List(x5818))
    val x5824 = withCtrl(x6031) { Counter(min=Const(0), max=Const(8), step=Const(1), par=1).name("x5824").srcCtx("LSTMPipe.scala:42:28") } // CounterNew(Const(0),Const(8),Const(1),Const(1))
    val x5825 = withCtrl(x6031) { CounterChain(List(x5824)).name("x5825").srcCtx("LSTMPipe.scala:42:41") } // CounterChainNew(List(x5824))
    val x6002 = withCtrl(x6031) { LoopController(style=MetaPipe, level=OuterControl, cchain=x5825).name("x6002").srcCtx("LSTMPipe.scala:42:41") } // UnrolledForeach(List(Const(true)),x5825,Block(Const(())),List(List(b3509)),List(List(b3510)))
    val b3509 = withCtrl(x6002) { CounterIter(x5824, Some(0)).name("b3509") } // b3509
    val b3510 = withCtrl(x6002) { Const(true).name("b3510") } // b3510
    val x5826 = withCtrl(x6002) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x5826").srcCtx("LSTMPipe.scala:43:31") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x5827 = withCtrl(x6002) { CounterChain(List(x5826)).name("x5827").srcCtx("LSTMPipe.scala:43:45") } // CounterChainNew(List(x5826))
    val x6001 = withCtrl(x6002) { LoopController(style=MetaPipe, level=OuterControl, cchain=x5827).name("x6001").srcCtx("LSTMPipe.scala:43:45") } // UnrolledForeach(List(b3510),x5827,Block(Const(())),List(List(b3513)),List(List(b3514)))
    val b3513 = withCtrl(x6001) { CounterIter(x5826, Some(0)).name("b3513") } // b3513
    val b3514 = withCtrl(x6001) { Const(true).name("b3514") } // b3514
    val x5828_d0_b0 = withCtrl(x6001) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x5828_d0_b0").srcCtx("LSTMPipe.scala:46:34:reduceMem") } // x5828 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x5828_d0_b0) = false
    bufferDepthOf(x5828_d0_b0) = 2
    staticDimsOf(x5828_d0_b0) = List(4, 128)
    val x5828_d1_b0 = withCtrl(x6001) { SRAM(size=512, banking=Strided(banks=1, stride=128)).name("x5828_d1_b0").srcCtx("LSTMPipe.scala:46:34:reduceMem") } // x5828 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x5828_d1_b0) = true
    bufferDepthOf(x5828_d1_b0) = 1
    staticDimsOf(x5828_d1_b0) = List(4, 128)
    val x5829_d0_b0 = withCtrl(x6001) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x5829_d0_b0").srcCtx("LSTMPipe.scala:47:32:foldMem") } // x5829 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x5829_d0_b0) = false
    bufferDepthOf(x5829_d0_b0) = 2
    staticDimsOf(x5829_d0_b0) = List(4, 128)
    val x5830 = withCtrl(x6001) { Counter(min=Const(0), max=Const(128), step=Const(1), par=1).name("x5830").srcCtx("LSTMPipe.scala:49:79") } // CounterNew(Const(0),Const(128),Const(1),Const(1))
    val x5831 = withCtrl(x6001) { CounterChain(List(x5830)).name("x5831").srcCtx("LSTMPipe.scala:62:12") } // CounterChainNew(List(x5830))
    val x5880 = withCtrl(x6001) { LoopController(style=MetaPipe, level=OuterControl, cchain=x5831).name("x5880").srcCtx("LSTMPipe.scala:62:12") } // UnrolledReduce(List(b3514, b3510),x5831,x5828,Block((x5828) => Const(())),List(List(b3522)),List(List(b3523)))
    val b3522 = withCtrl(x5880) { CounterIter(x5830, Some(0)).name("b3522") } // b3522
    val b3523 = withCtrl(x5880) { Const(true).name("b3523") } // b3523
    val x5832_d0_b0 = withCtrl(x5880) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x5832_d0_b0").srcCtx("LSTMPipe.scala:50:29:re") } // x5832 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x5832_d0_b0) = false
    bufferDepthOf(x5832_d0_b0) = 2
    staticDimsOf(x5832_d0_b0) = List(4, 128)
    val x5833 = withCtrl(x5880) { Reg(init=Some(0.0)).name("x5833").srcCtx("LSTMPipe.scala:62:12") } // x5833 = RegNew(Const(0))
    isAccum(x5833) = false
    bufferDepthOf(x5833) = 2
    val x5834 = withCtrl(x5880) { Reg(init=Some(0.0)).name("x5834").srcCtx("LSTMPipe.scala:62:12") } // x5834 = RegNew(Const(0))
    isAccum(x5834) = false
    bufferDepthOf(x5834) = 2
    val x5841 = withCtrl(x5880) { UnitController(style=SeqPipe, level=InnerControl).name("x5841").srcCtx("LSTMPipe.scala:62:12") } // UnitPipe(List(b3523, b3514, b3510),Block(Const(())))
    val x5835 = withCtrl(x5841) { OpDef(op=BitAnd, inputs=List(b3523, b3514)).name("x5835").srcCtx("UnrollingBase.scala:28:66") } // And(b3523,b3514)
    val x5836 = withCtrl(x5841) { OpDef(op=BitAnd, inputs=List(x5835, b3510)).name("x5836").srcCtx("UnrollingBase.scala:28:66") } // And(x5835,b3510)
    val x5837 = withCtrl(x5841) { LoadBanks(List(x5644_d1_b0), List(b3509, b3522)).name("x5837").srcCtx("LSTMPipe.scala:51:25:xEle") } // SRAMLoad(x5644,ArrayBuffer(Const(8), Const(128)),List(b3509, b3522),Const(0),x5836)
    val x5838 = withCtrl(x5841) { LoadBanks(List(x5694_d0_b0), List(b3513, b3522)).name("x5838").srcCtx("LSTMPipe.scala:52:25:hEle") } // SRAMLoad(x5694,ArrayBuffer(Const(2), Const(128)),List(b3513, b3522),Const(0),x5836)
    val x5839_x5833 = withCtrl(x5841) { WriteMem(x5833, x5837).name("x5839_x5833").srcCtx("LSTMPipe.scala:62:12") } // RegWrite(x5833,x5837,x5836)
    val x5840_x5834 = withCtrl(x5841) { WriteMem(x5834, x5838).name("x5840_x5834").srcCtx("LSTMPipe.scala:62:12") } // RegWrite(x5834,x5838,x5836)
    val x5842 = withCtrl(x5880) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x5842").srcCtx("LSTMPipe.scala:53:29") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x5843 = withCtrl(x5880) { CounterChain(List(x5842)).name("x5843").srcCtx("LSTMPipe.scala:53:35") } // CounterChainNew(List(x5842))
    val x5861 = withCtrl(x5880) { LoopController(style=MetaPipe, level=OuterControl, cchain=x5843).name("x5861").srcCtx("LSTMPipe.scala:53:35") } // UnrolledForeach(List(b3523, b3514, b3510),x5843,Block(Const(())),List(List(b3536)),List(List(b3537)))
    val b3536 = withCtrl(x5861) { CounterIter(x5842, Some(0)).name("b3536") } // b3536
    val b3537 = withCtrl(x5861) { Const(true).name("b3537") } // b3537
    val x5844 = withCtrl(x5861) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x5844").srcCtx("LSTMPipe.scala:54:42") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x5845 = withCtrl(x5861) { CounterChain(List(x5844)).name("x5845").srcCtx("LSTMPipe.scala:54:56") } // CounterChainNew(List(x5844))
    val x5860 = withCtrl(x5861) { LoopController(style=InnerPipe, level=InnerControl, cchain=x5845).name("x5860").srcCtx("LSTMPipe.scala:54:56") } // UnrolledForeach(List(b3537, b3523, b3514, b3510),x5845,Block(Const(())),List(List(b3540)),List(List(b3541)))
    val b3540 = withCtrl(x5860) { CounterIter(x5844, None).name("b3540") } // b3540
    val b3541 = withCtrl(x5860) { Const(true).name("b3541") } // b3541
    val x5846 = withCtrl(x5860) { OpDef(op=BitAnd, inputs=List(b3541, b3537)).name("x5846").srcCtx("UnrollingBase.scala:28:66") } // And(b3541,b3537)
    val x5847 = withCtrl(x5860) { OpDef(op=BitAnd, inputs=List(b3523, b3514)).name("x5847").srcCtx("UnrollingBase.scala:28:66") } // And(b3523,b3514)
    val x5848 = withCtrl(x5860) { OpDef(op=BitAnd, inputs=List(x5846, x5847)).name("x5848").srcCtx("UnrollingBase.scala:28:66") } // And(x5846,x5847)
    val x5849 = withCtrl(x5860) { OpDef(op=BitAnd, inputs=List(x5848, b3510)).name("x5849").srcCtx("UnrollingBase.scala:28:66") } // And(x5848,b3510)
    val x5850 = withCtrl(x5860) { LoadBanks(List(x5719_d0_b0), List(b3513, b3522, b3536, b3540)).name("x5850").srcCtx("LSTMPipe.scala:55:29") } // ParSRAMLoad(x5719,List(List(b3513, b3522, b3536, b3540)),List(x5849))
    val x5851 = withCtrl(x5860) { x5850 } // VectorApply(x5850,0)
    val x5852 = withCtrl(x5860) { ReadMem(x5833).name("x5852").srcCtx("LSTMPipe.scala:62:12") } // RegRead(x5833)
    val x5853 = withCtrl(x5860) { OpDef(op=FixMul, inputs=List(x5851, x5852)).name("x5853").srcCtx("LSTMPipe.scala:55:67:reX") } // FixMul(x5851,x5852)
    val x5854 = withCtrl(x5860) { LoadBanks(List(x5756_d0_b0), List(b3513, b3522, b3536, b3540)).name("x5854").srcCtx("LSTMPipe.scala:56:29") } // ParSRAMLoad(x5756,List(List(b3513, b3522, b3536, b3540)),List(x5849))
    val x5855 = withCtrl(x5860) { x5854 } // VectorApply(x5854,0)
    val x5856 = withCtrl(x5860) { ReadMem(x5834).name("x5856").srcCtx("LSTMPipe.scala:62:12") } // RegRead(x5834)
    val x5857 = withCtrl(x5860) { OpDef(op=FixMul, inputs=List(x5855, x5856)).name("x5857").srcCtx("LSTMPipe.scala:56:67:reH") } // FixMul(x5855,x5856)
    val x5858 = withCtrl(x5860) { OpDef(op=FixAdd, inputs=List(x5853, x5857)).name("x5858").srcCtx("LSTMPipe.scala:57:46") } // FixAdd(x5853,x5857)
    val x5859 = withCtrl(x5860) { StoreBanks(List(List(x5832_d0_b0)), List(b3536, b3540), x5858).name("x5859").srcCtx("LSTMPipe.scala:57:40") } // ParSRAMStore(x5832,List(List(b3536, b3540)),List(x5858),List(x5849))
    val x5862 = withCtrl(x5880) { Counter(min=Const(0), max=Const(128), step=Const(1), par=1).name("x5862").srcCtx("LSTMPipe.scala:62:12") } // CounterNew(Const(0),Const(128),Const(1),Const(1))
    val x5863 = withCtrl(x5880) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x5863").srcCtx("LSTMPipe.scala:62:12") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x5864 = withCtrl(x5880) { CounterChain(List(x5863,x5862)).name("x5864").srcCtx("LSTMPipe.scala:62:12") } // CounterChainNew(ArrayBuffer(x5863, x5862))
    val x5879 = withCtrl(x5880) { LoopController(style=InnerPipe, level=InnerControl, cchain=x5864).name("x5879").srcCtx("LSTMPipe.scala:62:12") } // UnrolledForeach(List(),x5864,Block(Const(())),ArrayBuffer(List(b3558), List(b3559)),ArrayBuffer(List(b3560), List(b3561)))
    val b3558 = withCtrl(x5879) { CounterIter(x5863, Some(0)).name("b3558") } // b3558
    val b3560 = withCtrl(x5879) { Const(true).name("b3560") } // b3560
    val b3559 = withCtrl(x5879) { CounterIter(x5862, None).name("b3559") } // b3559
    val b3561 = withCtrl(x5879) { Const(true).name("b3561") } // b3561
    val x5865 = withCtrl(x5879) { OpDef(op=BitAnd, inputs=List(b3560, b3561)).name("x5865").srcCtx("UnrollingBase.scala:28:66") } // And(b3560,b3561)
    val x5866 = withCtrl(x5879) { OpDef(op=BitAnd, inputs=List(b3514, b3510)).name("x5866").srcCtx("UnrollingBase.scala:28:66") } // And(b3514,b3510)
    val x5867 = withCtrl(x5879) { OpDef(op=BitAnd, inputs=List(x5865, x5866)).name("x5867").srcCtx("UnrollingBase.scala:28:66") } // And(x5865,x5866)
    val x5868 = withCtrl(x5879) { LoadBanks(List(x5832_d0_b0), List(b3558, b3559)).name("x5868").srcCtx("LSTMPipe.scala:62:12") } // ParSRAMLoad(x5832,List(ArrayBuffer(b3558, b3559)),List(x5867))
    val x5869 = withCtrl(x5879) { x5868 } // VectorApply(x5868,0)
    val x5870 = withCtrl(x5879) { LoadBanks(List(x5828_d1_b0), List(b3558, b3559)).name("x5870").srcCtx("LSTMPipe.scala:62:12") } // ParSRAMLoad(x5828,List(ArrayBuffer(b3558, b3559)),List(x5867))
    val x5871 = withCtrl(x5879) { x5870 } // VectorApply(x5870,0)
    val x5872 = withCtrl(x5879) { OpDef(op=BitAnd, inputs=List(b3523, b3514)).name("x5872").srcCtx("LSTMPipe.scala:62:12") } // And(b3523,b3514)
    val x5873 = withCtrl(x5879) { OpDef(op=BitAnd, inputs=List(x5872, b3510)).name("x5873").srcCtx("LSTMPipe.scala:62:12") } // And(x5872,b3510)
    val x5874 = withCtrl(x5879) { OpDef(op=BitAnd, inputs=List(x5873, x5867)).name("x5874").srcCtx("LSTMPipe.scala:62:12") } // And(x5873,x5867)
    val x5875 = withCtrl(x5879) { OpDef(op=FixEql, inputs=List(b3522, Const(0))).name("x5875").srcCtx("LSTMPipe.scala:62:12") } // FixEql(b3522,Const(0))
    val x5876 = withCtrl(x5879) { OpDef(op=FixAdd, inputs=List(x5869, x5871)).name("x5876").srcCtx("LSTMPipe.scala:62:14") } // FixAdd(x5869,x5871)
    val x5877 = withCtrl(x5879) { OpDef(op=MuxOp, inputs=List(x5875, x5869, x5876)).name("x5877").srcCtx("LSTMPipe.scala:62:12") } // Mux(x5875,x5869,x5876)
    val x5878 = withCtrl(x5879) { StoreBanks(List(List(x5828_d0_b0), List(x5828_d1_b0)), List(b3558, b3559), x5877).name("x5878").srcCtx("LSTMPipe.scala:62:12") } // ParSRAMStore(x5828,List(ArrayBuffer(b3558, b3559)),List(x5877),List(x5867))
    antiDepsOf(x5878)=List(x5870)
    val x5881 = withCtrl(x6001) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x5881").srcCtx("LSTMPipe.scala:65:32") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x5882 = withCtrl(x6001) { CounterChain(List(x5881)).name("x5882").srcCtx("LSTMPipe.scala:65:45") } // CounterChainNew(List(x5881))
    val x5895 = withCtrl(x6001) { LoopController(style=MetaPipe, level=OuterControl, cchain=x5882).name("x5895").srcCtx("LSTMPipe.scala:65:45") } // UnrolledForeach(List(b3514, b3510),x5882,Block(Const(())),List(List(b3580)),List(List(b3581)))
    val b3580 = withCtrl(x5895) { CounterIter(x5881, Some(0)).name("b3580") } // b3580
    val b3581 = withCtrl(x5895) { Const(true).name("b3581") } // b3581
    val x5883 = withCtrl(x5895) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x5883").srcCtx("LSTMPipe.scala:66:40") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x5884 = withCtrl(x5895) { CounterChain(List(x5883)).name("x5884").srcCtx("LSTMPipe.scala:66:54") } // CounterChainNew(List(x5883))
    val x5894 = withCtrl(x5895) { LoopController(style=InnerPipe, level=InnerControl, cchain=x5884).name("x5894").srcCtx("LSTMPipe.scala:66:54") } // UnrolledForeach(List(b3581, b3514, b3510),x5884,Block(Const(())),List(List(b3584)),List(List(b3585)))
    val b3584 = withCtrl(x5894) { CounterIter(x5883, None).name("b3584") } // b3584
    val b3585 = withCtrl(x5894) { Const(true).name("b3585") } // b3585
    val x5885 = withCtrl(x5894) { OpDef(op=BitAnd, inputs=List(b3585, b3581)).name("x5885").srcCtx("UnrollingBase.scala:28:66") } // And(b3585,b3581)
    val x5886 = withCtrl(x5894) { OpDef(op=BitAnd, inputs=List(b3514, b3510)).name("x5886").srcCtx("UnrollingBase.scala:28:66") } // And(b3514,b3510)
    val x5887 = withCtrl(x5894) { OpDef(op=BitAnd, inputs=List(x5885, x5886)).name("x5887").srcCtx("UnrollingBase.scala:28:66") } // And(x5885,x5886)
    val x5888 = withCtrl(x5894) { LoadBanks(List(x5828_d0_b0), List(b3580, b3584)).name("x5888").srcCtx("LSTMPipe.scala:67:48") } // ParSRAMLoad(x5828,List(List(b3580, b3584)),List(x5887))
    val x5889 = withCtrl(x5894) { x5888 } // VectorApply(x5888,0)
    val x5890 = withCtrl(x5894) { LoadBanks(List(x5793_d0_b0), List(b3513, b3580, b3584)).name("x5890").srcCtx("LSTMPipe.scala:67:66") } // ParSRAMLoad(x5793,List(List(b3513, b3580, b3584)),List(x5887))
    val x5891 = withCtrl(x5894) { x5890 } // VectorApply(x5890,0)
    val x5892 = withCtrl(x5894) { OpDef(op=FixAdd, inputs=List(x5889, x5891)).name("x5892").srcCtx("LSTMPipe.scala:67:63") } // FixAdd(x5889,x5891)
    val x5893 = withCtrl(x5894) { StoreBanks(List(List(x5829_d0_b0)), List(b3580, b3584), x5892).name("x5893").srcCtx("LSTMPipe.scala:67:37") } // ParSRAMStore(x5829,List(List(b3580, b3584)),List(x5892),List(x5887))
    val x5896_d0_b0 = withCtrl(x6001) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x5896_d0_b0").srcCtx("LSTMPipe.scala:72:29:cbuf") } // x5896 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x5896_d0_b0) = false
    bufferDepthOf(x5896_d0_b0) = 2
    staticDimsOf(x5896_d0_b0) = List(128)
    val x5896_d1_b0 = withCtrl(x6001) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x5896_d1_b0").srcCtx("LSTMPipe.scala:72:29:cbuf") } // x5896 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x5896_d1_b0) = true
    bufferDepthOf(x5896_d1_b0) = 1
    staticDimsOf(x5896_d1_b0) = List(128)
    val x5897_d0_b0 = withCtrl(x6001) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x5897_d0_b0").srcCtx("LSTMPipe.scala:73:29:hbuf") } // x5897 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x5897_d0_b0) = false
    bufferDepthOf(x5897_d0_b0) = 2
    staticDimsOf(x5897_d0_b0) = List(128)
    val x5897_d1_b0 = withCtrl(x6001) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x5897_d1_b0").srcCtx("LSTMPipe.scala:73:29:hbuf") } // x5897 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x5897_d1_b0) = true
    bufferDepthOf(x5897_d1_b0) = 1
    staticDimsOf(x5897_d1_b0) = List(128)
    val x5898 = withCtrl(x6001) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x5898").srcCtx("LSTMPipe.scala:75:27") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x5899 = withCtrl(x6001) { CounterChain(List(x5898)).name("x5899").srcCtx("LSTMPipe.scala:75:33") } // CounterChainNew(List(x5898))
    val x5988 = withCtrl(x6001) { LoopController(style=MetaPipe, level=OuterControl, cchain=x5899).name("x5988").srcCtx("LSTMPipe.scala:75:33") } // UnrolledForeach(List(b3514, b3510),x5899,Block(Const(())),List(List(b3601)),List(List(b3602)))
    val b3601 = withCtrl(x5988) { CounterIter(x5898, Some(0)).name("b3601") } // b3601
    val b3602 = withCtrl(x5988) { Const(true).name("b3602") } // b3602
    val x5900 = withCtrl(x5988) { FIFO(size=16).name("x5900").srcCtx("LSTMPipe.scala:76:31:sigQ") } // x5900 = FIFONew(Const(16))
    isAccum(x5900) = false
    bufferDepthOf(x5900) = 2
    val x5901 = withCtrl(x5988) { FIFO(size=16).name("x5901").srcCtx("LSTMPipe.scala:77:31:actQ") } // x5901 = FIFONew(Const(16))
    isAccum(x5901) = false
    bufferDepthOf(x5901) = 2
    val x5902 = withCtrl(x5988) { FIFO(size=16).name("x5902").srcCtx("LSTMPipe.scala:78:34:sigEleQ") } // x5902 = FIFONew(Const(16))
    isAccum(x5902) = false
    bufferDepthOf(x5902) = 2
    val x5903 = withCtrl(x5988) { FIFO(size=16).name("x5903").srcCtx("LSTMPipe.scala:79:39:cNewMultAddQ") } // x5903 = FIFONew(Const(16))
    isAccum(x5903) = false
    bufferDepthOf(x5903) = 2
    val x5904 = withCtrl(x5988) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x5904").srcCtx("LSTMPipe.scala:81:40") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x5905 = withCtrl(x5988) { CounterChain(List(x5904)).name("x5905").srcCtx("LSTMPipe.scala:81:54") } // CounterChainNew(List(x5904))
    val x5933 = withCtrl(x5988) { LoopController(style=InnerPipe, level=InnerControl, cchain=x5905).name("x5933").srcCtx("LSTMPipe.scala:81:54") } // UnrolledForeach(List(b3602, b3514, b3510),x5905,Block(Const(())),List(List(b3609)),List(List(b3610)))
    val b3609 = withCtrl(x5933) { CounterIter(x5904, None).name("b3609") } // b3609
    val b3610 = withCtrl(x5933) { Const(true).name("b3610") } // b3610
    val x5906 = withCtrl(x5933) { OpDef(op=BitAnd, inputs=List(b3610, b3602)).name("x5906").srcCtx("UnrollingBase.scala:28:66") } // And(b3610,b3602)
    val x5907 = withCtrl(x5933) { OpDef(op=BitAnd, inputs=List(b3514, b3510)).name("x5907").srcCtx("UnrollingBase.scala:28:66") } // And(b3514,b3510)
    val x5908 = withCtrl(x5933) { OpDef(op=BitAnd, inputs=List(x5906, x5907)).name("x5908").srcCtx("UnrollingBase.scala:28:66") } // And(x5906,x5907)
    val x5909 = withCtrl(x5933) { LoadBanks(List(x5669_d1_b0), List(b3513, b3609)).name("x5909").srcCtx("LSTMPipe.scala:82:27:cEle") } // ParSRAMLoad(x5669,List(List(b3513, b3609)),List(x5908))
    val x5910 = withCtrl(x5933) { x5909 } // VectorApply(x5909,0)
    val x5911 = withCtrl(x5933) { LoadBanks(List(x5829_d0_b0), List(b3601, b3609)).name("x5911").srcCtx("LSTMPipe.scala:83:33:pEle") } // ParSRAMLoad(x5829,List(List(b3601, b3609)),List(x5908))
    val x5912 = withCtrl(x5933) { x5911 } // VectorApply(x5911,0)
    val x5913 = withCtrl(x5933) { OpDef(op=FixLt, inputs=List(x5912, Const(-2.5))).name("x5913").srcCtx("NonLinearity.scala:94:12") } // FixLt(x5912,Const(-2.5))
    val x5914 = withCtrl(x5933) { OpDef(op=FixLeq, inputs=List(Const(2.5), x5912)).name("x5914").srcCtx("NonLinearity.scala:94:34") } // FixLeq(Const(2.5),x5912)
    val x5915 = withCtrl(x5933) { OpDef(op=FixMul, inputs=List(Const(0.19999999), x5912)).name("x5915").srcCtx("NonLinearity.scala:94:56") } // FixMul(Const(0.199999988079071044921875),x5912)
    val x5916 = withCtrl(x5933) { OpDef(op=FixAdd, inputs=List(x5915, Const(0.5))).name("x5916").srcCtx("NonLinearity.scala:94:61") } // FixAdd(x5915,Const(0.5))
    val x5917 = withCtrl(x5933) { OpDef(op=MuxOp, inputs=List(x5914, Const(1.0), x5916)).name("x5917").srcCtx("NonLinearity.scala:94:30") } // Mux(x5914,Const(1),x5916)
    val x5918 = withCtrl(x5933) { OpDef(op=MuxOp, inputs=List(x5913, Const(0.0), x5917)).name("x5918").srcCtx("NonLinearity.scala:94:8:sigEle") } // Mux(x5913,Const(0),x5917)
    val x5919 = withCtrl(x5933) { OpDef(op=FixAbs, inputs=List(x5912)).name("x5919").srcCtx("NonLinearity.scala:73:20:absin") } // FixAbs(x5912)
    val x5920 = withCtrl(x5933) { OpDef(op=FixSra, inputs=List(x5919, Const(2))).name("x5920").srcCtx("NonLinearity.scala:74:22:div4") } // FixRsh(x5919,Const(2))
    val x5921 = withCtrl(x5933) { OpDef(op=FixAdd, inputs=List(x5920, Const(0.375))).name("x5921").srcCtx("NonLinearity.scala:75:19:li") } // FixAdd(x5920,Const(0.375))
    val x5922 = withCtrl(x5933) { OpDef(op=FixLt, inputs=List(Const(2.5), x5919)).name("x5922").srcCtx("NonLinearity.scala:76:28") } // FixLt(Const(2.5),x5919)
    val x5923 = withCtrl(x5933) { OpDef(op=FixLt, inputs=List(Const(0.5), x5919)).name("x5923").srcCtx("NonLinearity.scala:77:14") } // FixLt(Const(0.5),x5919)
    val x5924 = withCtrl(x5933) { OpDef(op=FixLt, inputs=List(x5919, Const(2.5))).name("x5924").srcCtx("NonLinearity.scala:77:31") } // FixLt(x5919,Const(2.5))
    val x5925 = withCtrl(x5933) { OpDef(op=BitAnd, inputs=List(x5923, x5924)).name("x5925").srcCtx("NonLinearity.scala:77:22") } // And(x5923,x5924)
    val x5926 = withCtrl(x5933) { OpDef(op=MuxOp, inputs=List(x5925, x5921, x5919)).name("x5926").srcCtx("NonLinearity.scala:77:10") } // Mux(x5925,x5921,x5919)
    val x5927 = withCtrl(x5933) { OpDef(op=MuxOp, inputs=List(x5922, Const(1.0), x5926)).name("x5927").srcCtx("NonLinearity.scala:76:21:absout") } // Mux(x5922,Const(1),x5926)
    val x5928 = withCtrl(x5933) { OpDef(op=FixNeg, inputs=List(x5927)).name("x5928").srcCtx("NonLinearity.scala:80:23:negout") } // FixNeg(x5927)
    val x5929 = withCtrl(x5933) { OpDef(op=FixLt, inputs=List(x5912, Const(0.0))).name("x5929").srcCtx("NonLinearity.scala:81:12") } // FixLt(x5912,Const(0))
    val x5930 = withCtrl(x5933) { OpDef(op=MuxOp, inputs=List(x5929, x5928, x5927)).name("x5930").srcCtx("NonLinearity.scala:81:8:actEle") } // Mux(x5929,x5928,x5927)
    val x5931_x5900 = withCtrl(x5933) { WriteMem(x5900, x5918).name("x5931_x5900").srcCtx("LSTMPipe.scala:88:23") } // ParFIFOEnq(x5900,List(x5918),List(x5908))
    val x5932_x5901 = withCtrl(x5933) { WriteMem(x5901, x5930).name("x5932_x5901").srcCtx("LSTMPipe.scala:89:23") } // ParFIFOEnq(x5901,List(x5930),List(x5908))
    val x5934 = withCtrl(x5988) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x5934").srcCtx("LSTMPipe.scala:92:40") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x5935 = withCtrl(x5988) { CounterChain(List(x5934)).name("x5935").srcCtx("LSTMPipe.scala:92:54") } // CounterChainNew(List(x5934))
    val x5959 = withCtrl(x5988) { LoopController(style=InnerPipe, level=InnerControl, cchain=x5935).name("x5959").srcCtx("LSTMPipe.scala:92:54") } // UnrolledForeach(List(b3602, b3514, b3510),x5935,Block(Const(())),List(List(b3641)),List(List(b3642)))
    val b3641 = withCtrl(x5959) { CounterIter(x5934, None).name("b3641") } // b3641
    val b3642 = withCtrl(x5959) { Const(true).name("b3642") } // b3642
    val x5936 = withCtrl(x5959) { OpDef(op=BitAnd, inputs=List(b3642, b3602)).name("x5936").srcCtx("UnrollingBase.scala:28:66") } // And(b3642,b3602)
    val x5937 = withCtrl(x5959) { OpDef(op=BitAnd, inputs=List(b3514, b3510)).name("x5937").srcCtx("UnrollingBase.scala:28:66") } // And(b3514,b3510)
    val x5938 = withCtrl(x5959) { OpDef(op=BitAnd, inputs=List(x5936, x5937)).name("x5938").srcCtx("UnrollingBase.scala:28:66") } // And(x5936,x5937)
    val x5939 = withCtrl(x5959) { ReadMem(x5900).name("x5939").srcCtx("LSTMPipe.scala:93:36:sigEle") } // ParFIFODeq(x5900,List(x5938))
    val x5940 = withCtrl(x5959) { x5939 } // VectorApply(x5939,0)
    val x5941 = withCtrl(x5959) { ReadMem(x5901).name("x5941").srcCtx("LSTMPipe.scala:94:36:actEle") } // ParFIFODeq(x5901,List(x5938))
    val x5942 = withCtrl(x5959) { x5941 } // VectorApply(x5941,0)
    val x5943 = withCtrl(x5959) { LoadBanks(List(x5896_d1_b0), List(b3641)).name("x5943").srcCtx("LSTMPipe.scala:96:31:cLast") } // ParSRAMLoad(x5896,List(List(b3641)),List(x5938))
    val x5944 = withCtrl(x5959) { x5943 } // VectorApply(x5943,0)
    val x5945 = withCtrl(x5959) { OpDef(op=FixMul, inputs=List(x5944, x5942)).name("x5945").srcCtx("LSTMPipe.scala:97:36:cNewMult") } // FixMul(x5944,x5942)
    val x5946 = withCtrl(x5959) { LoadBanks(List(x5669_d0_b0), List(b3513, b3641)).name("x5946").srcCtx("LSTMPipe.scala:98:43") } // ParSRAMLoad(x5669,List(List(b3513, b3641)),List(x5938))
    val x5947 = withCtrl(x5959) { x5946 } // VectorApply(x5946,0)
    val x5948 = withCtrl(x5959) { OpDef(op=FixMul, inputs=List(x5940, x5947)).name("x5948").srcCtx("LSTMPipe.scala:98:40") } // FixMul(x5940,x5947)
    val x5949 = withCtrl(x5959) { OpDef(op=FixAdd, inputs=List(x5948, x5944)).name("x5949").srcCtx("LSTMPipe.scala:98:65:cNewMultAdd") } // FixAdd(x5948,x5944)
    val x5950 = withCtrl(x5959) { OpDef(op=FixEql, inputs=List(b3601, Const(0))).name("x5950").srcCtx("LSTMPipe.scala:16:40") } // FixEql(b3601,Const(0))
    val x5951 = withCtrl(x5959) { OpDef(op=FixEql, inputs=List(b3601, Const(1))).name("x5951").srcCtx("LSTMPipe.scala:18:40") } // FixEql(b3601,Const(1))
    val x5952 = withCtrl(x5959) { OpDef(op=FixEql, inputs=List(b3601, Const(2))).name("x5952").srcCtx("LSTMPipe.scala:20:40") } // FixEql(b3601,Const(2))
    val x5953 = withCtrl(x5959) { OpDef(op=MuxOp, inputs=List(x5952, x5949, x5944)).name("x5953").srcCtx("LSTMPipe.scala:103:24") } // Mux(x5952,x5949,x5944)
    val x5954 = withCtrl(x5959) { OpDef(op=MuxOp, inputs=List(x5951, x5945, x5953)).name("x5954").srcCtx("LSTMPipe.scala:102:22") } // Mux(x5951,x5945,x5953)
    val x5955 = withCtrl(x5959) { OpDef(op=MuxOp, inputs=List(x5950, x5940, x5954)).name("x5955").srcCtx("LSTMPipe.scala:101:20") } // Mux(x5950,x5940,x5954)
    val x5956 = withCtrl(x5959) { StoreBanks(List(List(x5896_d0_b0), List(x5896_d1_b0)), List(b3641), x5955).name("x5956").srcCtx("LSTMPipe.scala:100:33") } // ParSRAMStore(x5896,List(List(b3641)),List(x5955),List(x5938))
    antiDepsOf(x5956)=List(x5943)
    val x5957_x5902 = withCtrl(x5959) { WriteMem(x5902, x5940).name("x5957_x5902").srcCtx("LSTMPipe.scala:107:26") } // ParFIFOEnq(x5902,List(x5940),List(x5938))
    val x5958_x5903 = withCtrl(x5959) { WriteMem(x5903, x5949).name("x5958_x5903").srcCtx("LSTMPipe.scala:108:31") } // ParFIFOEnq(x5903,List(x5949),List(x5938))
    val x5960 = withCtrl(x5988) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x5960").srcCtx("LSTMPipe.scala:111:40") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    def split1 = {
    val x5961 = withCtrl(x5988) { CounterChain(List(x5960)).name("x5961").srcCtx("LSTMPipe.scala:111:54") } // CounterChainNew(List(x5960))
    val x5987 = withCtrl(x5988) { LoopController(style=InnerPipe, level=InnerControl, cchain=x5961).name("x5987").srcCtx("LSTMPipe.scala:111:54") } // UnrolledForeach(List(b3602, b3514, b3510),x5961,Block(Const(())),List(List(b3669)),List(List(b3670)))
    val b3669 = withCtrl(x5987) { CounterIter(x5960, None).name("b3669") } // b3669
    val b3670 = withCtrl(x5987) { Const(true).name("b3670") } // b3670
    val x5962 = withCtrl(x5987) { OpDef(op=BitAnd, inputs=List(b3670, b3602)).name("x5962").srcCtx("UnrollingBase.scala:28:66") } // And(b3670,b3602)
    val x5963 = withCtrl(x5987) { OpDef(op=BitAnd, inputs=List(b3514, b3510)).name("x5963").srcCtx("UnrollingBase.scala:28:66") } // And(b3514,b3510)
    val x5964 = withCtrl(x5987) { OpDef(op=BitAnd, inputs=List(x5962, x5963)).name("x5964").srcCtx("UnrollingBase.scala:28:66") } // And(x5962,x5963)
    val x5965 = withCtrl(x5987) { ReadMem(x5902).name("x5965").srcCtx("LSTMPipe.scala:112:39:sigEle") } // ParFIFODeq(x5902,List(x5964))
    val x5966 = withCtrl(x5987) { x5965 } // VectorApply(x5965,0)
    val x5967 = withCtrl(x5987) { ReadMem(x5903).name("x5967").srcCtx("LSTMPipe.scala:113:49:cNewMultAdd") } // ParFIFODeq(x5903,List(x5964))
    val x5968 = withCtrl(x5987) { x5967 } // VectorApply(x5967,0)
    val x5969 = withCtrl(x5987) { OpDef(op=FixAbs, inputs=List(x5968)).name("x5969").srcCtx("NonLinearity.scala:73:20:absin") } // FixAbs(x5968)
    val x5970 = withCtrl(x5987) { OpDef(op=FixSra, inputs=List(x5969, Const(2))).name("x5970").srcCtx("NonLinearity.scala:74:22:div4") } // FixRsh(x5969,Const(2))
    val x5971 = withCtrl(x5987) { OpDef(op=FixAdd, inputs=List(x5970, Const(0.375))).name("x5971").srcCtx("NonLinearity.scala:75:19:li") } // FixAdd(x5970,Const(0.375))
    val x5972 = withCtrl(x5987) { OpDef(op=FixLt, inputs=List(Const(2.5), x5969)).name("x5972").srcCtx("NonLinearity.scala:76:28") } // FixLt(Const(2.5),x5969)
    val x5973 = withCtrl(x5987) { OpDef(op=FixLt, inputs=List(Const(0.5), x5969)).name("x5973").srcCtx("NonLinearity.scala:77:14") } // FixLt(Const(0.5),x5969)
    val x5974 = withCtrl(x5987) { OpDef(op=FixLt, inputs=List(x5969, Const(2.5))).name("x5974").srcCtx("NonLinearity.scala:77:31") } // FixLt(x5969,Const(2.5))
    val x5975 = withCtrl(x5987) { OpDef(op=BitAnd, inputs=List(x5973, x5974)).name("x5975").srcCtx("NonLinearity.scala:77:22") } // And(x5973,x5974)
    val x5976 = withCtrl(x5987) { OpDef(op=MuxOp, inputs=List(x5975, x5971, x5969)).name("x5976").srcCtx("NonLinearity.scala:77:10") } // Mux(x5975,x5971,x5969)
    val x5977 = withCtrl(x5987) { OpDef(op=MuxOp, inputs=List(x5972, Const(1.0), x5976)).name("x5977").srcCtx("NonLinearity.scala:76:21:absout") } // Mux(x5972,Const(1),x5976)
    val x5978 = withCtrl(x5987) { OpDef(op=FixNeg, inputs=List(x5977)).name("x5978").srcCtx("NonLinearity.scala:80:23:negout") } // FixNeg(x5977)
    val x5979 = withCtrl(x5987) { OpDef(op=FixLt, inputs=List(x5968, Const(0.0))).name("x5979").srcCtx("NonLinearity.scala:81:12") } // FixLt(x5968,Const(0))
    val x5980 = withCtrl(x5987) { OpDef(op=MuxOp, inputs=List(x5979, x5978, x5977)).name("x5980").srcCtx("NonLinearity.scala:81:8") } // Mux(x5979,x5978,x5977)
    val x5981 = withCtrl(x5987) { OpDef(op=FixAdd, inputs=List(x5980, x5966)).name("x5981").srcCtx("LSTMPipe.scala:115:50:hNew") } // FixAdd(x5980,x5966)
    val x5982 = withCtrl(x5987) { LoadBanks(List(x5897_d1_b0), List(b3669)).name("x5982").srcCtx("LSTMPipe.scala:116:31:hLast") } // ParSRAMLoad(x5897,List(List(b3669)),List(x5964))
    val x5983 = withCtrl(x5987) { x5982 } // VectorApply(x5982,0)
    val x5984 = withCtrl(x5987) { OpDef(op=FixEql, inputs=List(b3601, Const(3))).name("x5984").srcCtx("LSTMPipe.scala:22:40") } // FixEql(b3601,Const(3))
    val x5985 = withCtrl(x5987) { OpDef(op=MuxOp, inputs=List(x5984, x5981, x5983)).name("x5985").srcCtx("LSTMPipe.scala:118:31:update") } // Mux(x5984,x5981,x5983)
    val x5986 = withCtrl(x5987) { StoreBanks(List(List(x5897_d0_b0), List(x5897_d1_b0)), List(b3669), x5985).name("x5986").srcCtx("LSTMPipe.scala:119:33") } // ParSRAMStore(x5897,List(List(b3669)),List(x5985),List(x5964))
    antiDepsOf(x5986)=List(x5982)
    val x5989 = withCtrl(x6001) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x5989").srcCtx("LSTMPipe.scala:123:38") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x5990 = withCtrl(x6001) { CounterChain(List(x5989)).name("x5990").srcCtx("LSTMPipe.scala:123:52") } // CounterChainNew(List(x5989))
    val x6000 = withCtrl(x6001) { LoopController(style=InnerPipe, level=InnerControl, cchain=x5990).name("x6000").srcCtx("LSTMPipe.scala:123:52") } // UnrolledForeach(List(b3514, b3510),x5990,Block(Const(())),List(List(b3700)),List(List(b3701)))
    val b3700 = withCtrl(x6000) { CounterIter(x5989, None).name("b3700") } // b3700
    val b3701 = withCtrl(x6000) { Const(true).name("b3701") } // b3701
    val x5991 = withCtrl(x6000) { OpDef(op=BitAnd, inputs=List(b3701, b3514)).name("x5991").srcCtx("UnrollingBase.scala:28:66") } // And(b3701,b3514)
    val x5992 = withCtrl(x6000) { OpDef(op=BitAnd, inputs=List(x5991, b3510)).name("x5992").srcCtx("UnrollingBase.scala:28:66") } // And(x5991,b3510)
    val x5993 = withCtrl(x6000) { LoadBanks(List(x5897_d0_b0), List(b3700)).name("x5993").srcCtx("LSTMPipe.scala:124:42") } // ParSRAMLoad(x5897,List(List(b3700)),List(x5992))
    val x5994 = withCtrl(x6000) { x5993 } // VectorApply(x5993,0)
    val x5995 = withCtrl(x6000) { StoreBanks(List(List(x5694_d0_b0)), List(b3513, b3700), x5994).name("x5995").srcCtx("LSTMPipe.scala:124:36") } // ParSRAMStore(x5694,List(List(b3513, b3700)),List(x5994),List(x5992))
    val x5996 = withCtrl(x6000) { StoreBanks(List(List(x5644_d0_b0), List(x5644_d1_b0)), List(b3509, b3700), x5994).name("x5996").srcCtx("LSTMPipe.scala:125:35") } // ParSRAMStore(x5644,List(List(b3509, b3700)),List(x5994),List(x5992))
    val x5997 = withCtrl(x6000) { LoadBanks(List(x5896_d0_b0), List(b3700)).name("x5997").srcCtx("LSTMPipe.scala:126:42") } // ParSRAMLoad(x5896,List(List(b3700)),List(x5992))
    val x5998 = withCtrl(x6000) { x5997 } // VectorApply(x5997,0)
    val x5999 = withCtrl(x6000) { StoreBanks(List(List(x5669_d0_b0), List(x5669_d1_b0)), List(b3513, b3700), x5998).name("x5999").srcCtx("LSTMPipe.scala:126:36") } // ParSRAMStore(x5669,List(List(b3513, b3700)),List(x5998),List(x5992))
    val x6003 = withCtrl(x6031) { Counter(min=Const(0), max=Const(8), step=Const(1), par=1).name("x6003").srcCtx("LSTMPipe.scala:131:47") } // CounterNew(Const(0),Const(8),Const(1),Const(1))
    val x6004 = withCtrl(x6031) { CounterChain(List(x6003)).name("x6004").srcCtx("LSTMPipe.scala:131:47") } // CounterChainNew(List(x6003))
    val x6030 = withCtrl(x6031) { LoopController(style=StreamPipe, level=OuterControl, cchain=x6004).name("x6030").srcCtx("LSTMPipe.scala:131:47") } // UnrolledForeach(List(Const(true)),x6004,Block(Const(())),List(List(b3716)),List(List(b3717)))
    val b3716 = withCtrl(x6030) { CounterIter(x6003, Some(0)).name("b3716") } // b3716
    val b3717 = withCtrl(x6030) { Const(true).name("b3717") } // b3717
    val b6066 = withCtrl(x6030) { StreamOut(field="offset").name("b6066").srcCtx("LSTMPipe.scala:131:47") } // x6005 = StreamOutNew(BurstCmdBus)
    isAccum(b6066) = false
    bufferDepthOf(b6066) = 1
    val b6067 = withCtrl(x6030) { StreamOut(field="size").name("b6067").srcCtx("LSTMPipe.scala:131:47") } // x6005 = StreamOutNew(BurstCmdBus)
    isAccum(b6067) = false
    bufferDepthOf(b6067) = 1
    val x6006 = withCtrl(x6030) { StreamOut(field="data").name("x6006").srcCtx("LSTMPipe.scala:131:47") } // x6006 = StreamOutNew(BurstFullDataBus())
    isAccum(x6006) = false
    bufferDepthOf(x6006) = 1
    val x6007 = withCtrl(x6030) { StreamIn(field="ack").name("x6007").srcCtx("LSTMPipe.scala:131:47") } // x6007 = StreamInNew(BurstAckBus)
    isAccum(x6007) = false
    bufferDepthOf(x6007) = 1
    val x6018 = withCtrl(x6030) { UnitController(style=SeqPipe, level=InnerControl).name("x6018").srcCtx("LSTMPipe.scala:131:47") } // UnitPipe(List(b3717),Block(x6017))
    val x6008 = withCtrl(x6018) { b3716 } // FixConvert(b3716,TRUE,_32,_0) (Same Type. No op)
    val x6009 = withCtrl(x6018) { OpDef(op=FixSla, inputs=List(x6008, Const(7))).name("x6009").srcCtx("LSTMPipe.scala:131:47") } // FixLsh(x6008,Const(7))
    val x6010 = withCtrl(x6018) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x6011 = withCtrl(x6018) { OpDef(op=FixAdd, inputs=List(x6009, x6010)).name("x6011").srcCtx("LSTMPipe.scala:131:47") } // FixAdd(x6009,x6010)
    val x6012 = withCtrl(x6018) { OpDef(op=FixSla, inputs=List(x6011, Const(2))).name("x6012").srcCtx("LSTMPipe.scala:131:47") } // FixLsh(x6011,Const(2))
    val x6013 = withCtrl(x6018) { x6012 } // FixConvert(x6012,TRUE,_64,_0)
    val x6014 = withCtrl(x6018) { DramAddress(x5586).name("x6014").srcCtx("LSTMPipe.scala:131:47") } // GetDRAMAddress(x5586)
    val x6016_x6015 = withCtrl(x6018) { OpDef(op=FixAdd, inputs=List(x6013, x6014)).name("x6016_x6015").srcCtx("LSTMPipe.scala:131:47") } // FixAdd(x6013,x6014)
    // x6016 = SimpleStruct(ArrayBuffer((offset,x6015), (size,Const(512)), (isLoad,Const(false))))
    val x6017_b6068_b6066 = withCtrl(x6018) { WriteMem(b6066, x6016_x6015).name("x6017_b6068_b6066").srcCtx("LSTMPipe.scala:131:47") } // StreamWrite(x6005,x6016,b3717)
    val x6017_b6069_b6067 = withCtrl(x6018) { WriteMem(b6067, Const(512)).name("x6017_b6069_b6067").srcCtx("LSTMPipe.scala:131:47") } // StreamWrite(x6005,x6016,b3717)
    val x6019 = withCtrl(x6030) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6019").srcCtx("LSTMPipe.scala:131:47") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6020 = withCtrl(x6030) { CounterChain(List(x6019)).name("x6020").srcCtx("LSTMPipe.scala:131:47") } // CounterChainNew(List(x6019))
    val x6026 = withCtrl(x6030) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6020).name("x6026").srcCtx("LSTMPipe.scala:131:47") } // UnrolledForeach(List(b3717),x6020,Block(Const(())),List(List(b3734)),List(List(b3735)))
    val b3734 = withCtrl(x6026) { CounterIter(x6019, None).name("b3734") } // b3734
    val b3735 = withCtrl(x6026) { Const(true).name("b3735") } // b3735
    val x6021 = withCtrl(x6026) { OpDef(op=BitAnd, inputs=List(b3735, b3717)).name("x6021").srcCtx("UnrollingBase.scala:28:66") } // And(b3735,b3717)
    val x6022 = withCtrl(x6026) { LoadBanks(List(x5644_d0_b0), List(b3716, b3734)).name("x6022").srcCtx("LSTMPipe.scala:131:47") } // ParSRAMLoad(x5644,List(List(b3716, b3734)),List(x6021))
    val x6024_x6023 = withCtrl(x6026) { x6022 } // VectorApply(x6022,0)
    // x6024 = SimpleStruct(ArrayBuffer((_1,x6023), (_2,Const(true))))
    val x6025_x6025_x6006 = withCtrl(x6026) { WriteMem(x6006, x6024_x6023).name("x6025_x6025_x6006").srcCtx("LSTMPipe.scala:131:47") } // ParStreamWrite(x6006,List(x6024),List(x6021))
    val x6027 = withCtrl(x6030) { FringeDenseStore(dram=List(x5586), cmdStream=List(b6066, b6067), dataStream=List(x6006), ackStream=List(x6007)).name("x6027").srcCtx("LSTMPipe.scala:131:47") } // FringeDenseStore(x5586,x6005,x6006,x6007)
    val x6029 = withCtrl(x6030) { UnitController(style=SeqPipe, level=InnerControl).name("x6029").srcCtx("LSTMPipe.scala:131:47") } // UnitPipe(List(b3717),Block(Const(())))
    val x6028_x6028 = withCtrl(x6029) { ReadMem(x6007).name("x6028_x6028").srcCtx("LSTMPipe.scala:131:47") } // StreamRead(x6007,b3717)
    }; split1
    
  }
}
