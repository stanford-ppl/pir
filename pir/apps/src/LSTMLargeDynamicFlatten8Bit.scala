import pir._
import pir.node._
import arch._
import prism.enums._

object LSTMLargeDynamicFlatten8Bit extends PIRApp {
  def main(implicit design:PIRDesign) = {
    import design.pirmeta._
    val x8556 = withCtrl(design.top.topController) { DRAM(dims=List(Const(8), Const(2), Const(128))).name("x8556").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:161:33:dout") } // x8556 = DRAMNew(ArrayBuffer(Const(8), Const(2), Const(128)),Const(0))
    val x8557 = withCtrl(design.top.topController) { DRAM(dims=List(Const(8), Const(2), Const(128))).name("x8557").srcCtx("DataGenerator.scala:168:20:xInit") } // x8557 = DRAMNew(ArrayBuffer(Const(8), Const(2), Const(128)),Const(0))
    val x8567 = withCtrl(design.top.topController) { DRAM(dims=List(Const(8), Const(2), Const(128))).name("x8567").srcCtx("DataGenerator.scala:168:20:cInit") } // x8567 = DRAMNew(ArrayBuffer(Const(8), Const(2), Const(128)),Const(0))
    val x8577 = withCtrl(design.top.topController) { DRAM(dims=List(Const(8), Const(2), Const(128))).name("x8577").srcCtx("DataGenerator.scala:168:20:hInit") } // x8577 = DRAMNew(ArrayBuffer(Const(8), Const(2), Const(128)),Const(0))
    val x8587 = withCtrl(design.top.topController) { DRAM(dims=List(Const(2), Const(4), Const(2), Const(128))).name("x8587").srcCtx("DataGenerator.scala:182:20:bInit") } // x8587 = DRAMNew(ArrayBuffer(Const(2), Const(4), Const(2), Const(128)),Const(0))
    val x8600 = withCtrl(design.top.topController) { DRAM(dims=List(Const(2), Const(1024), Const(4), Const(1024))).name("x8600").srcCtx("DataGenerator.scala:182:20:wxInit") } // x8600 = DRAMNew(ArrayBuffer(Const(2), Const(1024), Const(4), Const(1024)),Const(0))
    val x8613 = withCtrl(design.top.topController) { DRAM(dims=List(Const(2), Const(1024), Const(4), Const(1024))).name("x8613").srcCtx("DataGenerator.scala:182:20:whInit") } // x8613 = DRAMNew(ArrayBuffer(Const(2), Const(1024), Const(4), Const(1024)),Const(0))
    val x9458 = withCtrl(design.top.topController) { UnitController(style=SeqPipe, level=OuterControl).name("x9458").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:172:11") } // Hwblock(Block(Const(())),false)
    val x8626_d0_b0 = withCtrl(x9458) { SRAM(size=2048, banking=Strided(banks=16, stride=1)).name("x8626_d0_b0").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:173:32:x") } // x8626 = SRAMNew(ArrayBuffer(Const(8), Const(2), Const(128)))
    isAccum(x8626_d0_b0) = false
    bufferDepthOf(x8626_d0_b0) = 1
    staticDimsOf(x8626_d0_b0) = List(8, 2, 128)
    val x8626_d1_b0 = withCtrl(x9458) { SRAM(size=2048, banking=Strided(banks=16, stride=1)).name("x8626_d1_b0").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:173:32:x") } // x8626 = SRAMNew(ArrayBuffer(Const(8), Const(2), Const(128)))
    isAccum(x8626_d1_b0) = false
    bufferDepthOf(x8626_d1_b0) = 3
    staticDimsOf(x8626_d1_b0) = List(8, 2, 128)
    val x8626_d2_b0 = withCtrl(x9458) { SRAM(size=2048, banking=Strided(banks=16, stride=1)).name("x8626_d2_b0").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:173:32:x") } // x8626 = SRAMNew(ArrayBuffer(Const(8), Const(2), Const(128)))
    isAccum(x8626_d2_b0) = false
    bufferDepthOf(x8626_d2_b0) = 3
    staticDimsOf(x8626_d2_b0) = List(8, 2, 128)
    val x8626_d3_b0 = withCtrl(x9458) { SRAM(size=2048, banking=Strided(banks=16, stride=1)).name("x8626_d3_b0").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:173:32:x") } // x8626 = SRAMNew(ArrayBuffer(Const(8), Const(2), Const(128)))
    isAccum(x8626_d3_b0) = false
    bufferDepthOf(x8626_d3_b0) = 3
    staticDimsOf(x8626_d3_b0) = List(8, 2, 128)
    val x8626_d4_b0 = withCtrl(x9458) { SRAM(size=2048, banking=Strided(banks=16, stride=1)).name("x8626_d4_b0").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:173:32:x") } // x8626 = SRAMNew(ArrayBuffer(Const(8), Const(2), Const(128)))
    isAccum(x8626_d4_b0) = false
    bufferDepthOf(x8626_d4_b0) = 3
    staticDimsOf(x8626_d4_b0) = List(8, 2, 128)
    val x8627_d0_b0 = withCtrl(x9458) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x8627_d0_b0").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:174:32:c") } // x8627 = SRAMNew(ArrayBuffer(Const(2), Const(2), Const(128)))
    isAccum(x8627_d0_b0) = false
    bufferDepthOf(x8627_d0_b0) = 1
    staticDimsOf(x8627_d0_b0) = List(2, 2, 128)
    val x8628_d0_b0 = withCtrl(x9458) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x8628_d0_b0").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:175:32:h") } // x8628 = SRAMNew(ArrayBuffer(Const(2), Const(2), Const(128)))
    isAccum(x8628_d0_b0) = false
    bufferDepthOf(x8628_d0_b0) = 1
    staticDimsOf(x8628_d0_b0) = List(2, 2, 128)
    val x8628_d1_b0 = withCtrl(x9458) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x8628_d1_b0").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:175:32:h") } // x8628 = SRAMNew(ArrayBuffer(Const(2), Const(2), Const(128)))
    isAccum(x8628_d1_b0) = false
    bufferDepthOf(x8628_d1_b0) = 3
    staticDimsOf(x8628_d1_b0) = List(2, 2, 128)
    val x8628_d2_b0 = withCtrl(x9458) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x8628_d2_b0").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:175:32:h") } // x8628 = SRAMNew(ArrayBuffer(Const(2), Const(2), Const(128)))
    isAccum(x8628_d2_b0) = false
    bufferDepthOf(x8628_d2_b0) = 3
    staticDimsOf(x8628_d2_b0) = List(2, 2, 128)
    val x8628_d3_b0 = withCtrl(x9458) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x8628_d3_b0").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:175:32:h") } // x8628 = SRAMNew(ArrayBuffer(Const(2), Const(2), Const(128)))
    isAccum(x8628_d3_b0) = false
    bufferDepthOf(x8628_d3_b0) = 3
    staticDimsOf(x8628_d3_b0) = List(2, 2, 128)
    val x8628_d4_b0 = withCtrl(x9458) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x8628_d4_b0").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:175:32:h") } // x8628 = SRAMNew(ArrayBuffer(Const(2), Const(2), Const(128)))
    isAccum(x8628_d4_b0) = false
    bufferDepthOf(x8628_d4_b0) = 3
    staticDimsOf(x8628_d4_b0) = List(2, 2, 128)
    val x8629_d0_b0 = withCtrl(x9458) { SRAM(size=2048, banking=Strided(banks=16, stride=1)).name("x8629_d0_b0").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:176:32:b") } // x8629 = SRAMNew(ArrayBuffer(Const(2), Const(4), Const(2), Const(128)))
    isAccum(x8629_d0_b0) = false
    bufferDepthOf(x8629_d0_b0) = 1
    staticDimsOf(x8629_d0_b0) = List(2, 4, 2, 128)
    val x8630 = withCtrl(x9458) { Counter(min=Const(0), max=Const(8), step=Const(1), par=1).name("x8630").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:178:9") } // CounterNew(Const(0),Const(8),Const(1),Const(1))
    val x8631 = withCtrl(x9458) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x8631").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:178:9") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x8632 = withCtrl(x9458) { CounterChain(List(x8630,x8631)).name("x8632").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:178:9") } // CounterChainNew(List(x8630, x8631))
    val x8659 = withCtrl(x9458) { LoopController(style=StreamPipe, level=OuterControl, cchain=x8632).name("x8659").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:178:9") } // UnrolledForeach(List(Const(true)),x8632,Block(Const(())),List(List(b3900), List(b3901)),List(List(b3902), List(b3903)))
    val b3900 = withCtrl(x8659) { CounterIter(x8630, Some(0)).name("b3900") } // b3900
    val b3902 = withCtrl(x8659) { Const(true).name("b3902") } // b3902
    val b3901 = withCtrl(x8659) { CounterIter(x8631, Some(0)).name("b3901") } // b3901
    val b3903 = withCtrl(x8659) { Const(true).name("b3903") } // b3903
    val b9473 = withCtrl(x8659) { StreamOut(field="offset").name("b9473").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:178:9") } // x8633 = StreamOutNew(BurstCmdBus)
    isAccum(b9473) = false
    bufferDepthOf(b9473) = 1
    val b9474 = withCtrl(x8659) { StreamOut(field="size").name("b9474").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:178:9") } // x8633 = StreamOutNew(BurstCmdBus)
    isAccum(b9474) = false
    bufferDepthOf(b9474) = 1
    val x8634 = withCtrl(x8659) { StreamIn(field="data").name("x8634").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:178:9") } // x8634 = StreamInNew(BurstDataBus())
    isAccum(x8634) = false
    bufferDepthOf(x8634) = 1
    val x8649 = withCtrl(x8659) { UnitController(style=SeqPipe, level=InnerControl).name("x8649").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:178:9") } // UnitPipe(List(b3902, b3903),Block(x8648))
    val x8635 = withCtrl(x8649) { b3900 } // FixConvert(b3900,TRUE,_32,_0) (Same Type. No op)
    val x8636 = withCtrl(x8649) { OpDef(op=FixSla, inputs=List(x8635, Const(8))).name("x8636").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:178:9") } // FixLsh(x8635,Const(8))
    val x8637 = withCtrl(x8649) { b3901 } // FixConvert(b3901,TRUE,_32,_0) (Same Type. No op)
    val x8638 = withCtrl(x8649) { OpDef(op=FixSla, inputs=List(x8637, Const(7))).name("x8638").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:178:9") } // FixLsh(x8637,Const(7))
    val x8639 = withCtrl(x8649) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x8640 = withCtrl(x8649) { OpDef(op=FixAdd, inputs=List(x8636, x8638)).name("x8640").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:178:9") } // FixAdd(x8636,x8638)
    val x8641 = withCtrl(x8649) { OpDef(op=FixAdd, inputs=List(x8640, x8639)).name("x8641").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:178:9") } // FixAdd(x8640,x8639)
    val x8642 = withCtrl(x8649) { OpDef(op=FixSla, inputs=List(x8641, Const(2))).name("x8642").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:178:9") } // FixLsh(x8641,Const(2))
    val x8643 = withCtrl(x8649) { x8642 } // FixConvert(x8642,TRUE,_64,_0)
    val x8644 = withCtrl(x8649) { DramAddress(x8557).name("x8644").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:178:9") } // GetDRAMAddress(x8557)
    val x8646_x8645 = withCtrl(x8649) { OpDef(op=FixAdd, inputs=List(x8643, x8644)).name("x8646_x8645").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:178:9") } // FixAdd(x8643,x8644)
    // x8646 = SimpleStruct(ArrayBuffer((offset,x8645), (size,Const(512)), (isLoad,Const(true))))
    val x8647 = withCtrl(x8649) { OpDef(op=BitAnd, inputs=List(b3902, b3903)).name("x8647").srcCtx("UnrollingBase.scala:28:66") } // And(b3902,b3903)
    val x8648_b9475_b9473 = withCtrl(x8649) { WriteMem(b9473, x8646_x8645).name("x8648_b9475_b9473").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:178:9") } // StreamWrite(x8633,x8646,x8647)
    val x8648_b9476_b9474 = withCtrl(x8649) { WriteMem(b9474, Const(512)).name("x8648_b9476_b9474").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:178:9") } // StreamWrite(x8633,x8646,x8647)
    val x8650 = withCtrl(x8659) { FringeDenseLoad(dram=List(x8557), cmdStream=List(b9473, b9474), dataStream=List(x8634)).name("x8650").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:178:9") } // FringeDenseLoad(x8557,x8633,x8634)
    val x8651 = withCtrl(x8659) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x8651").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:178:9") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x8652 = withCtrl(x8659) { CounterChain(List(x8651)).name("x8652").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:178:9") } // CounterChainNew(List(x8651))
    val x8658 = withCtrl(x8659) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8652).name("x8658").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:178:9") } // UnrolledForeach(List(b3902, b3903),x8652,Block(Const(())),List(List(b3924)),List(List(b3925)))
    val b3924 = withCtrl(x8658) { CounterIter(x8651, None).name("b3924") } // b3924
    val b3925 = withCtrl(x8658) { Const(true).name("b3925") } // b3925
    val x8653 = withCtrl(x8658) { OpDef(op=BitAnd, inputs=List(b3925, b3902)).name("x8653").srcCtx("UnrollingBase.scala:28:66") } // And(b3925,b3902)
    val x8654 = withCtrl(x8658) { OpDef(op=BitAnd, inputs=List(x8653, b3903)).name("x8654").srcCtx("UnrollingBase.scala:28:66") } // And(x8653,b3903)
    val x8655_x8655 = withCtrl(x8658) { ReadMem(x8634).name("x8655_x8655").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:178:9") } // ParStreamRead(x8634,List(x8654))
    val x8656_x8656 = withCtrl(x8658) { x8655_x8655 } // VectorApply(x8655,0)
    val x8657 = withCtrl(x8658) { StoreBanks(List(List(x8626_d0_b0), List(x8626_d1_b0), List(x8626_d2_b0), List(x8626_d3_b0), List(x8626_d4_b0)), List(b3900, b3901, b3924), x8656_x8656).name("x8657").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:178:9") } // ParSRAMStore(x8626,List(List(b3900, b3901, b3924)),List(x8656),List(x8654))
    val x8660 = withCtrl(x9458) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x8660").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:179:9") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x8661 = withCtrl(x9458) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x8661").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:179:9") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x8662 = withCtrl(x9458) { CounterChain(List(x8660,x8661)).name("x8662").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:179:9") } // CounterChainNew(List(x8660, x8661))
    val x8689 = withCtrl(x9458) { LoopController(style=StreamPipe, level=OuterControl, cchain=x8662).name("x8689").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:179:9") } // UnrolledForeach(List(Const(true)),x8662,Block(Const(())),List(List(b3936), List(b3937)),List(List(b3938), List(b3939)))
    val b3936 = withCtrl(x8689) { CounterIter(x8660, Some(0)).name("b3936") } // b3936
    val b3938 = withCtrl(x8689) { Const(true).name("b3938") } // b3938
    val b3937 = withCtrl(x8689) { CounterIter(x8661, Some(0)).name("b3937") } // b3937
    val b3939 = withCtrl(x8689) { Const(true).name("b3939") } // b3939
    val b9477 = withCtrl(x8689) { StreamOut(field="offset").name("b9477").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:179:9") } // x8663 = StreamOutNew(BurstCmdBus)
    isAccum(b9477) = false
    bufferDepthOf(b9477) = 1
    val b9478 = withCtrl(x8689) { StreamOut(field="size").name("b9478").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:179:9") } // x8663 = StreamOutNew(BurstCmdBus)
    isAccum(b9478) = false
    bufferDepthOf(b9478) = 1
    val x8664 = withCtrl(x8689) { StreamIn(field="data").name("x8664").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:179:9") } // x8664 = StreamInNew(BurstDataBus())
    isAccum(x8664) = false
    bufferDepthOf(x8664) = 1
    val x8679 = withCtrl(x8689) { UnitController(style=SeqPipe, level=InnerControl).name("x8679").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:179:9") } // UnitPipe(List(b3938, b3939),Block(x8678))
    val x8665 = withCtrl(x8679) { b3936 } // FixConvert(b3936,TRUE,_32,_0) (Same Type. No op)
    val x8666 = withCtrl(x8679) { OpDef(op=FixSla, inputs=List(x8665, Const(8))).name("x8666").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:179:9") } // FixLsh(x8665,Const(8))
    val x8667 = withCtrl(x8679) { b3937 } // FixConvert(b3937,TRUE,_32,_0) (Same Type. No op)
    val x8668 = withCtrl(x8679) { OpDef(op=FixSla, inputs=List(x8667, Const(7))).name("x8668").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:179:9") } // FixLsh(x8667,Const(7))
    val x8669 = withCtrl(x8679) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x8670 = withCtrl(x8679) { OpDef(op=FixAdd, inputs=List(x8666, x8668)).name("x8670").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:179:9") } // FixAdd(x8666,x8668)
    val x8671 = withCtrl(x8679) { OpDef(op=FixAdd, inputs=List(x8670, x8669)).name("x8671").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:179:9") } // FixAdd(x8670,x8669)
    val x8672 = withCtrl(x8679) { OpDef(op=FixSla, inputs=List(x8671, Const(2))).name("x8672").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:179:9") } // FixLsh(x8671,Const(2))
    val x8673 = withCtrl(x8679) { x8672 } // FixConvert(x8672,TRUE,_64,_0)
    val x8674 = withCtrl(x8679) { DramAddress(x8567).name("x8674").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:179:9") } // GetDRAMAddress(x8567)
    val x8676_x8675 = withCtrl(x8679) { OpDef(op=FixAdd, inputs=List(x8673, x8674)).name("x8676_x8675").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:179:9") } // FixAdd(x8673,x8674)
    // x8676 = SimpleStruct(ArrayBuffer((offset,x8675), (size,Const(512)), (isLoad,Const(true))))
    val x8677 = withCtrl(x8679) { OpDef(op=BitAnd, inputs=List(b3938, b3939)).name("x8677").srcCtx("UnrollingBase.scala:28:66") } // And(b3938,b3939)
    val x8678_b9479_b9477 = withCtrl(x8679) { WriteMem(b9477, x8676_x8675).name("x8678_b9479_b9477").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:179:9") } // StreamWrite(x8663,x8676,x8677)
    val x8678_b9480_b9478 = withCtrl(x8679) { WriteMem(b9478, Const(512)).name("x8678_b9480_b9478").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:179:9") } // StreamWrite(x8663,x8676,x8677)
    val x8680 = withCtrl(x8689) { FringeDenseLoad(dram=List(x8567), cmdStream=List(b9477, b9478), dataStream=List(x8664)).name("x8680").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:179:9") } // FringeDenseLoad(x8567,x8663,x8664)
    val x8681 = withCtrl(x8689) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x8681").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:179:9") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x8682 = withCtrl(x8689) { CounterChain(List(x8681)).name("x8682").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:179:9") } // CounterChainNew(List(x8681))
    val x8688 = withCtrl(x8689) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8682).name("x8688").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:179:9") } // UnrolledForeach(List(b3938, b3939),x8682,Block(Const(())),List(List(b3960)),List(List(b3961)))
    val b3960 = withCtrl(x8688) { CounterIter(x8681, None).name("b3960") } // b3960
    val b3961 = withCtrl(x8688) { Const(true).name("b3961") } // b3961
    val x8683 = withCtrl(x8688) { OpDef(op=BitAnd, inputs=List(b3961, b3938)).name("x8683").srcCtx("UnrollingBase.scala:28:66") } // And(b3961,b3938)
    val x8684 = withCtrl(x8688) { OpDef(op=BitAnd, inputs=List(x8683, b3939)).name("x8684").srcCtx("UnrollingBase.scala:28:66") } // And(x8683,b3939)
    val x8685_x8685 = withCtrl(x8688) { ReadMem(x8664).name("x8685_x8685").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:179:9") } // ParStreamRead(x8664,List(x8684))
    val x8686_x8686 = withCtrl(x8688) { x8685_x8685 } // VectorApply(x8685,0)
    val x8687 = withCtrl(x8688) { StoreBanks(List(List(x8627_d0_b0)), List(b3936, b3937, b3960), x8686_x8686).name("x8687").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:179:9") } // ParSRAMStore(x8627,List(List(b3936, b3937, b3960)),List(x8686),List(x8684))
    val x8690 = withCtrl(x9458) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x8690").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:180:9") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x8691 = withCtrl(x9458) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x8691").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:180:9") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x8692 = withCtrl(x9458) { CounterChain(List(x8690,x8691)).name("x8692").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:180:9") } // CounterChainNew(List(x8690, x8691))
    val x8719 = withCtrl(x9458) { LoopController(style=StreamPipe, level=OuterControl, cchain=x8692).name("x8719").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:180:9") } // UnrolledForeach(List(Const(true)),x8692,Block(Const(())),List(List(b3972), List(b3973)),List(List(b3974), List(b3975)))
    val b3972 = withCtrl(x8719) { CounterIter(x8690, Some(0)).name("b3972") } // b3972
    val b3974 = withCtrl(x8719) { Const(true).name("b3974") } // b3974
    val b3973 = withCtrl(x8719) { CounterIter(x8691, Some(0)).name("b3973") } // b3973
    val b3975 = withCtrl(x8719) { Const(true).name("b3975") } // b3975
    val b9481 = withCtrl(x8719) { StreamOut(field="offset").name("b9481").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:180:9") } // x8693 = StreamOutNew(BurstCmdBus)
    isAccum(b9481) = false
    bufferDepthOf(b9481) = 1
    val b9482 = withCtrl(x8719) { StreamOut(field="size").name("b9482").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:180:9") } // x8693 = StreamOutNew(BurstCmdBus)
    isAccum(b9482) = false
    bufferDepthOf(b9482) = 1
    val x8694 = withCtrl(x8719) { StreamIn(field="data").name("x8694").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:180:9") } // x8694 = StreamInNew(BurstDataBus())
    isAccum(x8694) = false
    bufferDepthOf(x8694) = 1
    val x8709 = withCtrl(x8719) { UnitController(style=SeqPipe, level=InnerControl).name("x8709").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:180:9") } // UnitPipe(List(b3974, b3975),Block(x8708))
    val x8695 = withCtrl(x8709) { b3972 } // FixConvert(b3972,TRUE,_32,_0) (Same Type. No op)
    val x8696 = withCtrl(x8709) { OpDef(op=FixSla, inputs=List(x8695, Const(8))).name("x8696").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:180:9") } // FixLsh(x8695,Const(8))
    val x8697 = withCtrl(x8709) { b3973 } // FixConvert(b3973,TRUE,_32,_0) (Same Type. No op)
    val x8698 = withCtrl(x8709) { OpDef(op=FixSla, inputs=List(x8697, Const(7))).name("x8698").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:180:9") } // FixLsh(x8697,Const(7))
    val x8699 = withCtrl(x8709) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x8700 = withCtrl(x8709) { OpDef(op=FixAdd, inputs=List(x8696, x8698)).name("x8700").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:180:9") } // FixAdd(x8696,x8698)
    val x8701 = withCtrl(x8709) { OpDef(op=FixAdd, inputs=List(x8700, x8699)).name("x8701").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:180:9") } // FixAdd(x8700,x8699)
    val x8702 = withCtrl(x8709) { OpDef(op=FixSla, inputs=List(x8701, Const(2))).name("x8702").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:180:9") } // FixLsh(x8701,Const(2))
    val x8703 = withCtrl(x8709) { x8702 } // FixConvert(x8702,TRUE,_64,_0)
    val x8704 = withCtrl(x8709) { DramAddress(x8577).name("x8704").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:180:9") } // GetDRAMAddress(x8577)
    val x8706_x8705 = withCtrl(x8709) { OpDef(op=FixAdd, inputs=List(x8703, x8704)).name("x8706_x8705").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:180:9") } // FixAdd(x8703,x8704)
    // x8706 = SimpleStruct(ArrayBuffer((offset,x8705), (size,Const(512)), (isLoad,Const(true))))
    val x8707 = withCtrl(x8709) { OpDef(op=BitAnd, inputs=List(b3974, b3975)).name("x8707").srcCtx("UnrollingBase.scala:28:66") } // And(b3974,b3975)
    val x8708_b9483_b9481 = withCtrl(x8709) { WriteMem(b9481, x8706_x8705).name("x8708_b9483_b9481").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:180:9") } // StreamWrite(x8693,x8706,x8707)
    val x8708_b9484_b9482 = withCtrl(x8709) { WriteMem(b9482, Const(512)).name("x8708_b9484_b9482").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:180:9") } // StreamWrite(x8693,x8706,x8707)
    val x8710 = withCtrl(x8719) { FringeDenseLoad(dram=List(x8577), cmdStream=List(b9481, b9482), dataStream=List(x8694)).name("x8710").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:180:9") } // FringeDenseLoad(x8577,x8693,x8694)
    val x8711 = withCtrl(x8719) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x8711").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:180:9") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x8712 = withCtrl(x8719) { CounterChain(List(x8711)).name("x8712").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:180:9") } // CounterChainNew(List(x8711))
    val x8718 = withCtrl(x8719) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8712).name("x8718").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:180:9") } // UnrolledForeach(List(b3974, b3975),x8712,Block(Const(())),List(List(b3996)),List(List(b3997)))
    val b3996 = withCtrl(x8718) { CounterIter(x8711, None).name("b3996") } // b3996
    val b3997 = withCtrl(x8718) { Const(true).name("b3997") } // b3997
    val x8713 = withCtrl(x8718) { OpDef(op=BitAnd, inputs=List(b3997, b3974)).name("x8713").srcCtx("UnrollingBase.scala:28:66") } // And(b3997,b3974)
    val x8714 = withCtrl(x8718) { OpDef(op=BitAnd, inputs=List(x8713, b3975)).name("x8714").srcCtx("UnrollingBase.scala:28:66") } // And(x8713,b3975)
    val x8715_x8715 = withCtrl(x8718) { ReadMem(x8694).name("x8715_x8715").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:180:9") } // ParStreamRead(x8694,List(x8714))
    val x8716_x8716 = withCtrl(x8718) { x8715_x8715 } // VectorApply(x8715,0)
    val x8717 = withCtrl(x8718) { StoreBanks(List(List(x8628_d0_b0), List(x8628_d1_b0), List(x8628_d2_b0), List(x8628_d3_b0), List(x8628_d4_b0)), List(b3972, b3973, b3996), x8716_x8716).name("x8717").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:180:9") } // ParSRAMStore(x8628,List(List(b3972, b3973, b3996)),List(x8716),List(x8714))
    val x8720 = withCtrl(x9458) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x8720").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:181:9") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x8721 = withCtrl(x9458) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x8721").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:181:9") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x8722 = withCtrl(x9458) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x8722").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:181:9") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x8723 = withCtrl(x9458) { CounterChain(List(x8720,x8721,x8722)).name("x8723").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:181:9") } // CounterChainNew(List(x8720, x8721, x8722))
    val x8755 = withCtrl(x9458) { LoopController(style=StreamPipe, level=OuterControl, cchain=x8723).name("x8755").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:181:9") } // UnrolledForeach(List(Const(true)),x8723,Block(Const(())),List(List(b4009), List(b4010), List(b4011)),List(List(b4012), List(b4013), List(b4014)))
    val b4009 = withCtrl(x8755) { CounterIter(x8720, Some(0)).name("b4009") } // b4009
    val b4012 = withCtrl(x8755) { Const(true).name("b4012") } // b4012
    val b4010 = withCtrl(x8755) { CounterIter(x8721, Some(0)).name("b4010") } // b4010
    val b4013 = withCtrl(x8755) { Const(true).name("b4013") } // b4013
    val b4011 = withCtrl(x8755) { CounterIter(x8722, Some(0)).name("b4011") } // b4011
    val b4014 = withCtrl(x8755) { Const(true).name("b4014") } // b4014
    val b9485 = withCtrl(x8755) { StreamOut(field="offset").name("b9485").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:181:9") } // x8724 = StreamOutNew(BurstCmdBus)
    isAccum(b9485) = false
    bufferDepthOf(b9485) = 1
    val b9486 = withCtrl(x8755) { StreamOut(field="size").name("b9486").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:181:9") } // x8724 = StreamOutNew(BurstCmdBus)
    isAccum(b9486) = false
    bufferDepthOf(b9486) = 1
    val x8725 = withCtrl(x8755) { StreamIn(field="data").name("x8725").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:181:9") } // x8725 = StreamInNew(BurstDataBus())
    isAccum(x8725) = false
    bufferDepthOf(x8725) = 1
    val x8744 = withCtrl(x8755) { UnitController(style=SeqPipe, level=InnerControl).name("x8744").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:181:9") } // UnitPipe(List(b4012, b4013, b4014),Block(x8743))
    val x8726 = withCtrl(x8744) { b4009 } // FixConvert(b4009,TRUE,_32,_0) (Same Type. No op)
    val x8727 = withCtrl(x8744) { OpDef(op=FixSla, inputs=List(x8726, Const(10))).name("x8727").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:181:9") } // FixLsh(x8726,Const(10))
    val x8728 = withCtrl(x8744) { b4010 } // FixConvert(b4010,TRUE,_32,_0) (Same Type. No op)
    val x8729 = withCtrl(x8744) { OpDef(op=FixSla, inputs=List(x8728, Const(8))).name("x8729").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:181:9") } // FixLsh(x8728,Const(8))
    val x8730 = withCtrl(x8744) { b4011 } // FixConvert(b4011,TRUE,_32,_0) (Same Type. No op)
    val x8731 = withCtrl(x8744) { OpDef(op=FixSla, inputs=List(x8730, Const(7))).name("x8731").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:181:9") } // FixLsh(x8730,Const(7))
    val x8732 = withCtrl(x8744) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x8733 = withCtrl(x8744) { OpDef(op=FixAdd, inputs=List(x8727, x8729)).name("x8733").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:181:9") } // FixAdd(x8727,x8729)
    val x8734 = withCtrl(x8744) { OpDef(op=FixAdd, inputs=List(x8731, x8732)).name("x8734").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:181:9") } // FixAdd(x8731,x8732)
    val x8735 = withCtrl(x8744) { OpDef(op=FixAdd, inputs=List(x8733, x8734)).name("x8735").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:181:9") } // FixAdd(x8733,x8734)
    val x8736 = withCtrl(x8744) { OpDef(op=FixSla, inputs=List(x8735, Const(2))).name("x8736").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:181:9") } // FixLsh(x8735,Const(2))
    val x8737 = withCtrl(x8744) { x8736 } // FixConvert(x8736,TRUE,_64,_0)
    val x8738 = withCtrl(x8744) { DramAddress(x8587).name("x8738").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:181:9") } // GetDRAMAddress(x8587)
    val x8740_x8739 = withCtrl(x8744) { OpDef(op=FixAdd, inputs=List(x8737, x8738)).name("x8740_x8739").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:181:9") } // FixAdd(x8737,x8738)
    // x8740 = SimpleStruct(ArrayBuffer((offset,x8739), (size,Const(512)), (isLoad,Const(true))))
    val x8741 = withCtrl(x8744) { OpDef(op=BitAnd, inputs=List(b4012, b4013)).name("x8741").srcCtx("UnrollingBase.scala:28:66") } // And(b4012,b4013)
    val x8742 = withCtrl(x8744) { OpDef(op=BitAnd, inputs=List(x8741, b4014)).name("x8742").srcCtx("UnrollingBase.scala:28:66") } // And(x8741,b4014)
    val x8743_b9487_b9485 = withCtrl(x8744) { WriteMem(b9485, x8740_x8739).name("x8743_b9487_b9485").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:181:9") } // StreamWrite(x8724,x8740,x8742)
    val x8743_b9488_b9486 = withCtrl(x8744) { WriteMem(b9486, Const(512)).name("x8743_b9488_b9486").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:181:9") } // StreamWrite(x8724,x8740,x8742)
    val x8745 = withCtrl(x8755) { FringeDenseLoad(dram=List(x8587), cmdStream=List(b9485, b9486), dataStream=List(x8725)).name("x8745").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:181:9") } // FringeDenseLoad(x8587,x8724,x8725)
    val x8746 = withCtrl(x8755) { Counter(min=Const(0), max=Const(128), step=Const(1), par=1).name("x8746").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:181:9") } // CounterNew(Const(0),Const(128),Const(1),Const(1))
    val x8747 = withCtrl(x8755) { CounterChain(List(x8746)).name("x8747").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:181:9") } // CounterChainNew(List(x8746))
    val x8754 = withCtrl(x8755) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8747).name("x8754").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:181:9") } // UnrolledForeach(List(b4012, b4013, b4014),x8747,Block(Const(())),List(List(b4039)),List(List(b4040)))
    val b4039 = withCtrl(x8754) { CounterIter(x8746, None).name("b4039") } // b4039
    val b4040 = withCtrl(x8754) { Const(true).name("b4040") } // b4040
    val x8748 = withCtrl(x8754) { OpDef(op=BitAnd, inputs=List(b4040, b4012)).name("x8748").srcCtx("UnrollingBase.scala:28:66") } // And(b4040,b4012)
    val x8749 = withCtrl(x8754) { OpDef(op=BitAnd, inputs=List(b4013, b4014)).name("x8749").srcCtx("UnrollingBase.scala:28:66") } // And(b4013,b4014)
    val x8750 = withCtrl(x8754) { OpDef(op=BitAnd, inputs=List(x8748, x8749)).name("x8750").srcCtx("UnrollingBase.scala:28:66") } // And(x8748,x8749)
    val x8751_x8751 = withCtrl(x8754) { ReadMem(x8725).name("x8751_x8751").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:181:9") } // ParStreamRead(x8725,List(x8750))
    val x8752_x8752 = withCtrl(x8754) { x8751_x8751 } // VectorApply(x8751,0)
    val x8753 = withCtrl(x8754) { StoreBanks(List(List(x8629_d0_b0)), List(b4009, b4010, b4011, b4039), x8752_x8752).name("x8753").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:181:9") } // ParSRAMStore(x8629,List(List(b4009, b4010, b4011, b4039)),List(x8752),List(x8750))
    val x8756 = withCtrl(x9458) { Counter(min=Const(0), max=Const(8), step=Const(1), par=1).name("x8756").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:183:23") } // CounterNew(Const(0),Const(8),Const(1),Const(1))
    val x8757 = withCtrl(x9458) { CounterChain(List(x8756)).name("x8757").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:183:29") } // CounterChainNew(List(x8756))
    val x9422 = withCtrl(x9458) { LoopController(style=MetaPipe, level=OuterControl, cchain=x8757).name("x9422").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:183:29") } // UnrolledForeach(List(Const(true)),x8757,Block(Const(())),List(List(b4051)),List(List(b4052)))
    val b4051 = withCtrl(x9422) { CounterIter(x8756, Some(0)).name("b4051") } // b4051
    val b4052 = withCtrl(x9422) { Const(true).name("b4052") } // b4052
    val x8758 = withCtrl(x9422) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x8758").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:184:26") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x8759 = withCtrl(x9422) { CounterChain(List(x8758)).name("x8759").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:184:32") } // CounterChainNew(List(x8758))
    val x9421 = withCtrl(x9422) { LoopController(style=MetaPipe, level=OuterControl, cchain=x8759).name("x9421").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:184:32") } // UnrolledForeach(List(b4052),x8759,Block(Const(())),List(List(b4055)),List(List(b4056)))
    val b4055 = withCtrl(x9421) { CounterIter(x8758, Some(0)).name("b4055") } // b4055
    val b4056 = withCtrl(x9421) { Const(true).name("b4056") } // b4056
    val x8760 = withCtrl(x9421) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x8760").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:186:38") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x8761 = withCtrl(x9421) { CounterChain(List(x8760)).name("x8761").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:186:45") } // CounterChainNew(List(x8760))
    val x9420 = withCtrl(x9421) { LoopController(style=MetaPipe, level=OuterControl, cchain=x8761).name("x9420").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:186:45") } // UnrolledForeach(List(b4056, b4052),x8761,Block(Const(())),List(List(b4059)),List(List(b4060)))
    val b4059 = withCtrl(x9420) { CounterIter(x8760, Some(0)).name("b4059") } // b4059
    val b4060 = withCtrl(x9420) { Const(true).name("b4060") } // b4060
    val x8762_d0_b0 = withCtrl(x9420) { SRAM(size=32768, banking=Strided(banks=16, stride=1)).name("x8762_d0_b0").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:188:39:wx") } // x8762 = SRAMNew(ArrayBuffer(Const(1024), Const(4), Const(128)))
    isAccum(x8762_d0_b0) = false
    bufferDepthOf(x8762_d0_b0) = 3
    staticDimsOf(x8762_d0_b0) = List(1024, 4, 128)
    val x8762_d0_b1 = withCtrl(x9420) { SRAM(size=32768, banking=Strided(banks=16, stride=1)).name("x8762_d0_b1").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:188:39:wx") } // x8762 = SRAMNew(ArrayBuffer(Const(1024), Const(4), Const(128)))
    isAccum(x8762_d0_b1) = false
    bufferDepthOf(x8762_d0_b1) = 3
    staticDimsOf(x8762_d0_b1) = List(1024, 4, 128)
    val x8762_d0_b2 = withCtrl(x9420) { SRAM(size=32768, banking=Strided(banks=16, stride=1)).name("x8762_d0_b2").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:188:39:wx") } // x8762 = SRAMNew(ArrayBuffer(Const(1024), Const(4), Const(128)))
    isAccum(x8762_d0_b2) = false
    bufferDepthOf(x8762_d0_b2) = 3
    staticDimsOf(x8762_d0_b2) = List(1024, 4, 128)
    val x8762_d0_b3 = withCtrl(x9420) { SRAM(size=32768, banking=Strided(banks=16, stride=1)).name("x8762_d0_b3").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:188:39:wx") } // x8762 = SRAMNew(ArrayBuffer(Const(1024), Const(4), Const(128)))
    isAccum(x8762_d0_b3) = false
    bufferDepthOf(x8762_d0_b3) = 3
    staticDimsOf(x8762_d0_b3) = List(1024, 4, 128)
    val x8762_d0_b4 = withCtrl(x9420) { SRAM(size=32768, banking=Strided(banks=16, stride=1)).name("x8762_d0_b4").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:188:39:wx") } // x8762 = SRAMNew(ArrayBuffer(Const(1024), Const(4), Const(128)))
    isAccum(x8762_d0_b4) = false
    bufferDepthOf(x8762_d0_b4) = 3
    staticDimsOf(x8762_d0_b4) = List(1024, 4, 128)
    val x8762_d0_b5 = withCtrl(x9420) { SRAM(size=32768, banking=Strided(banks=16, stride=1)).name("x8762_d0_b5").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:188:39:wx") } // x8762 = SRAMNew(ArrayBuffer(Const(1024), Const(4), Const(128)))
    isAccum(x8762_d0_b5) = false
    bufferDepthOf(x8762_d0_b5) = 3
    staticDimsOf(x8762_d0_b5) = List(1024, 4, 128)
    val x8762_d0_b6 = withCtrl(x9420) { SRAM(size=32768, banking=Strided(banks=16, stride=1)).name("x8762_d0_b6").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:188:39:wx") } // x8762 = SRAMNew(ArrayBuffer(Const(1024), Const(4), Const(128)))
    isAccum(x8762_d0_b6) = false
    bufferDepthOf(x8762_d0_b6) = 3
    staticDimsOf(x8762_d0_b6) = List(1024, 4, 128)
    val x8762_d0_b7 = withCtrl(x9420) { SRAM(size=32768, banking=Strided(banks=16, stride=1)).name("x8762_d0_b7").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:188:39:wx") } // x8762 = SRAMNew(ArrayBuffer(Const(1024), Const(4), Const(128)))
    isAccum(x8762_d0_b7) = false
    bufferDepthOf(x8762_d0_b7) = 3
    staticDimsOf(x8762_d0_b7) = List(1024, 4, 128)
    val x8762_d0_b8 = withCtrl(x9420) { SRAM(size=32768, banking=Strided(banks=16, stride=1)).name("x8762_d0_b8").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:188:39:wx") } // x8762 = SRAMNew(ArrayBuffer(Const(1024), Const(4), Const(128)))
    isAccum(x8762_d0_b8) = false
    bufferDepthOf(x8762_d0_b8) = 3
    staticDimsOf(x8762_d0_b8) = List(1024, 4, 128)
    val x8762_d0_b9 = withCtrl(x9420) { SRAM(size=32768, banking=Strided(banks=16, stride=1)).name("x8762_d0_b9").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:188:39:wx") } // x8762 = SRAMNew(ArrayBuffer(Const(1024), Const(4), Const(128)))
    isAccum(x8762_d0_b9) = false
    bufferDepthOf(x8762_d0_b9) = 3
    staticDimsOf(x8762_d0_b9) = List(1024, 4, 128)
    val x8762_d0_b10 = withCtrl(x9420) { SRAM(size=32768, banking=Strided(banks=16, stride=1)).name("x8762_d0_b10").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:188:39:wx") } // x8762 = SRAMNew(ArrayBuffer(Const(1024), Const(4), Const(128)))
    isAccum(x8762_d0_b10) = false
    bufferDepthOf(x8762_d0_b10) = 3
    staticDimsOf(x8762_d0_b10) = List(1024, 4, 128)
    val x8762_d0_b11 = withCtrl(x9420) { SRAM(size=32768, banking=Strided(banks=16, stride=1)).name("x8762_d0_b11").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:188:39:wx") } // x8762 = SRAMNew(ArrayBuffer(Const(1024), Const(4), Const(128)))
    isAccum(x8762_d0_b11) = false
    bufferDepthOf(x8762_d0_b11) = 3
    staticDimsOf(x8762_d0_b11) = List(1024, 4, 128)
    val x8762_d0_b12 = withCtrl(x9420) { SRAM(size=32768, banking=Strided(banks=16, stride=1)).name("x8762_d0_b12").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:188:39:wx") } // x8762 = SRAMNew(ArrayBuffer(Const(1024), Const(4), Const(128)))
    isAccum(x8762_d0_b12) = false
    bufferDepthOf(x8762_d0_b12) = 3
    staticDimsOf(x8762_d0_b12) = List(1024, 4, 128)
    val x8762_d0_b13 = withCtrl(x9420) { SRAM(size=32768, banking=Strided(banks=16, stride=1)).name("x8762_d0_b13").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:188:39:wx") } // x8762 = SRAMNew(ArrayBuffer(Const(1024), Const(4), Const(128)))
    isAccum(x8762_d0_b13) = false
    bufferDepthOf(x8762_d0_b13) = 3
    staticDimsOf(x8762_d0_b13) = List(1024, 4, 128)
    val x8762_d0_b14 = withCtrl(x9420) { SRAM(size=32768, banking=Strided(banks=16, stride=1)).name("x8762_d0_b14").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:188:39:wx") } // x8762 = SRAMNew(ArrayBuffer(Const(1024), Const(4), Const(128)))
    isAccum(x8762_d0_b14) = false
    bufferDepthOf(x8762_d0_b14) = 3
    staticDimsOf(x8762_d0_b14) = List(1024, 4, 128)
    val x8762_d0_b15 = withCtrl(x9420) { SRAM(size=32768, banking=Strided(banks=16, stride=1)).name("x8762_d0_b15").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:188:39:wx") } // x8762 = SRAMNew(ArrayBuffer(Const(1024), Const(4), Const(128)))
    isAccum(x8762_d0_b15) = false
    bufferDepthOf(x8762_d0_b15) = 3
    staticDimsOf(x8762_d0_b15) = List(1024, 4, 128)
    val x8763_d0_b0 = withCtrl(x9420) { SRAM(size=32768, banking=Strided(banks=16, stride=1)).name("x8763_d0_b0").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:189:39:wh") } // x8763 = SRAMNew(ArrayBuffer(Const(1024), Const(4), Const(128)))
    isAccum(x8763_d0_b0) = false
    bufferDepthOf(x8763_d0_b0) = 2
    staticDimsOf(x8763_d0_b0) = List(1024, 4, 128)
    val x8763_d0_b1 = withCtrl(x9420) { SRAM(size=32768, banking=Strided(banks=16, stride=1)).name("x8763_d0_b1").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:189:39:wh") } // x8763 = SRAMNew(ArrayBuffer(Const(1024), Const(4), Const(128)))
    isAccum(x8763_d0_b1) = false
    bufferDepthOf(x8763_d0_b1) = 2
    staticDimsOf(x8763_d0_b1) = List(1024, 4, 128)
    val x8763_d0_b2 = withCtrl(x9420) { SRAM(size=32768, banking=Strided(banks=16, stride=1)).name("x8763_d0_b2").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:189:39:wh") } // x8763 = SRAMNew(ArrayBuffer(Const(1024), Const(4), Const(128)))
    isAccum(x8763_d0_b2) = false
    bufferDepthOf(x8763_d0_b2) = 2
    staticDimsOf(x8763_d0_b2) = List(1024, 4, 128)
    val x8763_d0_b3 = withCtrl(x9420) { SRAM(size=32768, banking=Strided(banks=16, stride=1)).name("x8763_d0_b3").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:189:39:wh") } // x8763 = SRAMNew(ArrayBuffer(Const(1024), Const(4), Const(128)))
    isAccum(x8763_d0_b3) = false
    bufferDepthOf(x8763_d0_b3) = 2
    staticDimsOf(x8763_d0_b3) = List(1024, 4, 128)
    val x8763_d0_b4 = withCtrl(x9420) { SRAM(size=32768, banking=Strided(banks=16, stride=1)).name("x8763_d0_b4").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:189:39:wh") } // x8763 = SRAMNew(ArrayBuffer(Const(1024), Const(4), Const(128)))
    isAccum(x8763_d0_b4) = false
    bufferDepthOf(x8763_d0_b4) = 2
    staticDimsOf(x8763_d0_b4) = List(1024, 4, 128)
    val x8763_d0_b5 = withCtrl(x9420) { SRAM(size=32768, banking=Strided(banks=16, stride=1)).name("x8763_d0_b5").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:189:39:wh") } // x8763 = SRAMNew(ArrayBuffer(Const(1024), Const(4), Const(128)))
    isAccum(x8763_d0_b5) = false
    bufferDepthOf(x8763_d0_b5) = 2
    staticDimsOf(x8763_d0_b5) = List(1024, 4, 128)
    val x8763_d0_b6 = withCtrl(x9420) { SRAM(size=32768, banking=Strided(banks=16, stride=1)).name("x8763_d0_b6").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:189:39:wh") } // x8763 = SRAMNew(ArrayBuffer(Const(1024), Const(4), Const(128)))
    isAccum(x8763_d0_b6) = false
    bufferDepthOf(x8763_d0_b6) = 2
    staticDimsOf(x8763_d0_b6) = List(1024, 4, 128)
    val x8763_d0_b7 = withCtrl(x9420) { SRAM(size=32768, banking=Strided(banks=16, stride=1)).name("x8763_d0_b7").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:189:39:wh") } // x8763 = SRAMNew(ArrayBuffer(Const(1024), Const(4), Const(128)))
    isAccum(x8763_d0_b7) = false
    bufferDepthOf(x8763_d0_b7) = 2
    staticDimsOf(x8763_d0_b7) = List(1024, 4, 128)
    val x8763_d0_b8 = withCtrl(x9420) { SRAM(size=32768, banking=Strided(banks=16, stride=1)).name("x8763_d0_b8").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:189:39:wh") } // x8763 = SRAMNew(ArrayBuffer(Const(1024), Const(4), Const(128)))
    isAccum(x8763_d0_b8) = false
    bufferDepthOf(x8763_d0_b8) = 2
    staticDimsOf(x8763_d0_b8) = List(1024, 4, 128)
    val x8763_d0_b9 = withCtrl(x9420) { SRAM(size=32768, banking=Strided(banks=16, stride=1)).name("x8763_d0_b9").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:189:39:wh") } // x8763 = SRAMNew(ArrayBuffer(Const(1024), Const(4), Const(128)))
    isAccum(x8763_d0_b9) = false
    bufferDepthOf(x8763_d0_b9) = 2
    staticDimsOf(x8763_d0_b9) = List(1024, 4, 128)
    val x8763_d0_b10 = withCtrl(x9420) { SRAM(size=32768, banking=Strided(banks=16, stride=1)).name("x8763_d0_b10").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:189:39:wh") } // x8763 = SRAMNew(ArrayBuffer(Const(1024), Const(4), Const(128)))
    isAccum(x8763_d0_b10) = false
    bufferDepthOf(x8763_d0_b10) = 2
    staticDimsOf(x8763_d0_b10) = List(1024, 4, 128)
    val x8763_d0_b11 = withCtrl(x9420) { SRAM(size=32768, banking=Strided(banks=16, stride=1)).name("x8763_d0_b11").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:189:39:wh") } // x8763 = SRAMNew(ArrayBuffer(Const(1024), Const(4), Const(128)))
    isAccum(x8763_d0_b11) = false
    bufferDepthOf(x8763_d0_b11) = 2
    staticDimsOf(x8763_d0_b11) = List(1024, 4, 128)
    val x8763_d0_b12 = withCtrl(x9420) { SRAM(size=32768, banking=Strided(banks=16, stride=1)).name("x8763_d0_b12").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:189:39:wh") } // x8763 = SRAMNew(ArrayBuffer(Const(1024), Const(4), Const(128)))
    isAccum(x8763_d0_b12) = false
    bufferDepthOf(x8763_d0_b12) = 2
    staticDimsOf(x8763_d0_b12) = List(1024, 4, 128)
    val x8763_d0_b13 = withCtrl(x9420) { SRAM(size=32768, banking=Strided(banks=16, stride=1)).name("x8763_d0_b13").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:189:39:wh") } // x8763 = SRAMNew(ArrayBuffer(Const(1024), Const(4), Const(128)))
    isAccum(x8763_d0_b13) = false
    bufferDepthOf(x8763_d0_b13) = 2
    staticDimsOf(x8763_d0_b13) = List(1024, 4, 128)
    val x8763_d0_b14 = withCtrl(x9420) { SRAM(size=32768, banking=Strided(banks=16, stride=1)).name("x8763_d0_b14").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:189:39:wh") } // x8763 = SRAMNew(ArrayBuffer(Const(1024), Const(4), Const(128)))
    isAccum(x8763_d0_b14) = false
    bufferDepthOf(x8763_d0_b14) = 2
    staticDimsOf(x8763_d0_b14) = List(1024, 4, 128)
    val x8763_d0_b15 = withCtrl(x9420) { SRAM(size=32768, banking=Strided(banks=16, stride=1)).name("x8763_d0_b15").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:189:39:wh") } // x8763 = SRAMNew(ArrayBuffer(Const(1024), Const(4), Const(128)))
    isAccum(x8763_d0_b15) = false
    bufferDepthOf(x8763_d0_b15) = 2
    staticDimsOf(x8763_d0_b15) = List(1024, 4, 128)
    val x8764_d0 = withCtrl(x9420) { Reg(init=Some(0)).name("x8764_d0").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:186:45") } // x8764 = RegNew(Const(0))
    isAccum(x8764_d0) = false
    bufferDepthOf(x8764_d0) = 3
    val x8764_d1 = withCtrl(x9420) { Reg(init=Some(0)).name("x8764_d1").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:186:45") } // x8764 = RegNew(Const(0))
    isAccum(x8764_d1) = false
    bufferDepthOf(x8764_d1) = 2
    val x8771 = withCtrl(x9420) { UnitController(style=SeqPipe, level=InnerControl).name("x8771").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:186:45") } // UnitPipe(List(b4060, b4056, b4052),Block(Const(())))
    val x8765 = withCtrl(x8771) { OpDef(op=FixSla, inputs=List(b4059, Const(7))).name("x8765").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:192:23:x$21") } // FixLsh(b4059,Const(7))
    val x8766 = withCtrl(x8771) { OpDef(op=FixAdd, inputs=List(x8765, Const(128))).name("x8766").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:192:76") } // FixAdd(x8765,Const(128))
    val x8767 = withCtrl(x8771) { OpDef(op=FixAdd, inputs=List(b4055, Const(1))).name("x8767").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:191:27") } // FixAdd(b4055,Const(1))
    val x8768 = withCtrl(x8771) { OpDef(op=BitAnd, inputs=List(b4060, b4056)).name("x8768").srcCtx("UnrollingBase.scala:28:66") } // And(b4060,b4056)
    val x8769 = withCtrl(x8771) { OpDef(op=BitAnd, inputs=List(x8768, b4052)).name("x8769").srcCtx("UnrollingBase.scala:28:66") } // And(x8768,b4052)
    val x8770_x8764_d0 = withCtrl(x8771) { WriteMem(x8764_d0, x8765).name("x8770_x8764_d0").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:186:45") } // RegWrite(x8764,x8765,x8769)
    val x8770_x8764_d1 = withCtrl(x8771) { WriteMem(x8764_d1, x8765).name("x8770_x8764_d1").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:186:45") } // RegWrite(x8764,x8765,x8769)
    val x8772 = withCtrl(x9420) { Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x8772").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:191:16") } // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x8773 = withCtrl(x9420) { Counter(min=Const(0), max=Const(1024), step=Const(1), par=1).name("x8773").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:191:16") } // CounterNew(Const(0),Const(1024),Const(1),Const(1))
    val x8774 = withCtrl(x9420) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x8774").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:191:16") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x8775 = withCtrl(x9420) { CounterChain(List(x8772,x8773,x8774)).name("x8775").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:191:16") } // CounterChainNew(List(x8772, x8773, x8774))
    val x8815 = withCtrl(x9420) { LoopController(style=StreamPipe, level=OuterControl, cchain=x8775).name("x8815").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:191:16") } // UnrolledForeach(List(b4060, b4056, b4052),x8775,Block(Const(())),List(List(b4075), List(b4076), List(b4077)),List(List(b4078), List(b4079), List(b4080)))
    val b4075 = withCtrl(x8815) { CounterIter(x8772, Some(0)).name("b4075") } // b4075
    val b4078 = withCtrl(x8815) { Const(true).name("b4078") } // b4078
    val b4076 = withCtrl(x8815) { CounterIter(x8773, Some(0)).name("b4076") } // b4076
    val b4079 = withCtrl(x8815) { Const(true).name("b4079") } // b4079
    val b4077 = withCtrl(x8815) { CounterIter(x8774, Some(0)).name("b4077") } // b4077
    val b4080 = withCtrl(x8815) { Const(true).name("b4080") } // b4080
    val b9489 = withCtrl(x8815) { StreamOut(field="offset").name("b9489").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:191:16") } // x8776 = StreamOutNew(BurstCmdBus)
    isAccum(b9489) = false
    bufferDepthOf(b9489) = 1
    val b9490 = withCtrl(x8815) { StreamOut(field="size").name("b9490").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:191:16") } // x8776 = StreamOutNew(BurstCmdBus)
    isAccum(b9490) = false
    bufferDepthOf(b9490) = 1
    val x8777 = withCtrl(x8815) { StreamIn(field="data").name("x8777").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:191:16") } // x8777 = StreamInNew(BurstDataBus())
    isAccum(x8777) = false
    bufferDepthOf(x8777) = 1
    val x8801 = withCtrl(x8815) { UnitController(style=SeqPipe, level=InnerControl).name("x8801").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:191:16") } // UnitPipe(List(b4078, b4079, b4080, b4060, b4056, b4052),Block(x8800))
    val x8778 = withCtrl(x8801) { OpDef(op=FixAdd, inputs=List(b4055, b4075)).name("x8778").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:191:16") } // FixAdd(b4055,b4075)
    val x8779 = withCtrl(x8801) { x8778 } // FixConvert(x8778,TRUE,_32,_0) (Same Type. No op)
    val x8780 = withCtrl(x8801) { OpDef(op=FixSla, inputs=List(x8779, Const(22))).name("x8780").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:191:16") } // FixLsh(x8779,Const(22))
    val x8781 = withCtrl(x8801) { b4076 } // FixConvert(b4076,TRUE,_32,_0) (Same Type. No op)
    val x8782 = withCtrl(x8801) { OpDef(op=FixSla, inputs=List(x8781, Const(12))).name("x8782").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:191:16") } // FixLsh(x8781,Const(12))
    val x8783 = withCtrl(x8801) { b4077 } // FixConvert(b4077,TRUE,_32,_0) (Same Type. No op)
    val x8784 = withCtrl(x8801) { OpDef(op=FixSla, inputs=List(x8783, Const(10))).name("x8784").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:191:16") } // FixLsh(x8783,Const(10))
    val x8785 = withCtrl(x8801) { ReadMem(x8764_d1).name("x8785").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:186:45") } // RegRead(x8764)
    val x8786 = withCtrl(x8801) { x8785 } // FixConvert(x8785,TRUE,_32,_0) (Same Type. No op)
    val x8787 = withCtrl(x8801) { OpDef(op=FixAdd, inputs=List(x8780, x8782)).name("x8787").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:191:16") } // FixAdd(x8780,x8782)
    val x8788 = withCtrl(x8801) { OpDef(op=FixAdd, inputs=List(x8784, x8786)).name("x8788").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:191:16") } // FixAdd(x8784,x8786)
    val x8789 = withCtrl(x8801) { OpDef(op=FixAdd, inputs=List(x8787, x8788)).name("x8789").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:191:16") } // FixAdd(x8787,x8788)
    val x8790 = withCtrl(x8801) { OpDef(op=FixSla, inputs=List(x8789, Const(2))).name("x8790").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:191:16") } // FixLsh(x8789,Const(2))
    val x8791 = withCtrl(x8801) { x8790 } // FixConvert(x8790,TRUE,_64,_0)
    val x8792 = withCtrl(x8801) { DramAddress(x8600).name("x8792").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:191:16") } // GetDRAMAddress(x8600)
    val x8794_x8793 = withCtrl(x8801) { OpDef(op=FixAdd, inputs=List(x8791, x8792)).name("x8794_x8793").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:191:16") } // FixAdd(x8791,x8792)
    // x8794 = SimpleStruct(ArrayBuffer((offset,x8793), (size,Const(512)), (isLoad,Const(true))))
    val x8795 = withCtrl(x8801) { OpDef(op=BitAnd, inputs=List(b4078, b4079)).name("x8795").srcCtx("UnrollingBase.scala:28:66") } // And(b4078,b4079)
    val x8796 = withCtrl(x8801) { OpDef(op=BitAnd, inputs=List(b4080, b4060)).name("x8796").srcCtx("UnrollingBase.scala:28:66") } // And(b4080,b4060)
    val x8797 = withCtrl(x8801) { OpDef(op=BitAnd, inputs=List(b4056, b4052)).name("x8797").srcCtx("UnrollingBase.scala:28:66") } // And(b4056,b4052)
    val x8798 = withCtrl(x8801) { OpDef(op=BitAnd, inputs=List(x8795, x8796)).name("x8798").srcCtx("UnrollingBase.scala:28:66") } // And(x8795,x8796)
    val x8799 = withCtrl(x8801) { OpDef(op=BitAnd, inputs=List(x8798, x8797)).name("x8799").srcCtx("UnrollingBase.scala:28:66") } // And(x8798,x8797)
    val x8800_b9491_b9489 = withCtrl(x8801) { WriteMem(b9489, x8794_x8793).name("x8800_b9491_b9489").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:191:16") } // StreamWrite(x8776,x8794,x8799)
    val x8800_b9492_b9490 = withCtrl(x8801) { WriteMem(b9490, Const(512)).name("x8800_b9492_b9490").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:191:16") } // StreamWrite(x8776,x8794,x8799)
    val x8802 = withCtrl(x8815) { FringeDenseLoad(dram=List(x8600), cmdStream=List(b9489, b9490), dataStream=List(x8777)).name("x8802").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:191:16") } // FringeDenseLoad(x8600,x8776,x8777)
    val x8803 = withCtrl(x8815) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x8803").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:191:16") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x8804 = withCtrl(x8815) { CounterChain(List(x8803)).name("x8804").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:191:16") } // CounterChainNew(List(x8803))
    val x8814 = withCtrl(x8815) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8804).name("x8814").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:191:16") } // UnrolledForeach(List(b4078, b4079, b4080, b4060, b4056, b4052),x8804,Block(Const(())),List(List(b4110)),List(List(b4111)))
    val b4110 = withCtrl(x8814) { CounterIter(x8803, None).name("b4110") } // b4110
    val b4111 = withCtrl(x8814) { Const(true).name("b4111") } // b4111
    val x8805 = withCtrl(x8814) { OpDef(op=BitAnd, inputs=List(b4111, b4078)).name("x8805").srcCtx("UnrollingBase.scala:28:66") } // And(b4111,b4078)
    val x8806 = withCtrl(x8814) { OpDef(op=BitAnd, inputs=List(b4079, b4080)).name("x8806").srcCtx("UnrollingBase.scala:28:66") } // And(b4079,b4080)
    val x8807 = withCtrl(x8814) { OpDef(op=BitAnd, inputs=List(b4060, b4056)).name("x8807").srcCtx("UnrollingBase.scala:28:66") } // And(b4060,b4056)
    val x8808 = withCtrl(x8814) { OpDef(op=BitAnd, inputs=List(x8805, x8806)).name("x8808").srcCtx("UnrollingBase.scala:28:66") } // And(x8805,x8806)
    val x8809 = withCtrl(x8814) { OpDef(op=BitAnd, inputs=List(x8807, b4052)).name("x8809").srcCtx("UnrollingBase.scala:28:66") } // And(x8807,b4052)
    val x8810 = withCtrl(x8814) { OpDef(op=BitAnd, inputs=List(x8808, x8809)).name("x8810").srcCtx("UnrollingBase.scala:28:66") } // And(x8808,x8809)
    val x8811_x8811 = withCtrl(x8814) { ReadMem(x8777).name("x8811_x8811").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:191:16") } // ParStreamRead(x8777,List(x8810))
    val x8812_x8812 = withCtrl(x8814) { x8811_x8811 } // VectorApply(x8811,0)
    val x8813 = withCtrl(x8814) { StoreBanks(List(List(x8762_d0_b0, x8762_d0_b1, x8762_d0_b2, x8762_d0_b3, x8762_d0_b4, x8762_d0_b5, x8762_d0_b6, x8762_d0_b7, x8762_d0_b8, x8762_d0_b9, x8762_d0_b10, x8762_d0_b11, x8762_d0_b12, x8762_d0_b13, x8762_d0_b14, x8762_d0_b15)), List(b4076, b4077, b4110), x8812_x8812).name("x8813").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:191:16") } // ParSRAMStore(x8762,List(List(b4076, b4077, b4110)),List(x8812),List(x8810))
    val x8816 = withCtrl(x9420) { Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x8816").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:193:16") } // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x8817 = withCtrl(x9420) { Counter(min=Const(0), max=Const(1024), step=Const(1), par=1).name("x8817").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:193:16") } // CounterNew(Const(0),Const(1024),Const(1),Const(1))
    val x8818 = withCtrl(x9420) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x8818").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:193:16") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x8819 = withCtrl(x9420) { CounterChain(List(x8816,x8817,x8818)).name("x8819").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:193:16") } // CounterChainNew(List(x8816, x8817, x8818))
    val x8859 = withCtrl(x9420) { LoopController(style=StreamPipe, level=OuterControl, cchain=x8819).name("x8859").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:193:16") } // UnrolledForeach(List(b4060, b4056, b4052),x8819,Block(Const(())),List(List(b4127), List(b4128), List(b4129)),List(List(b4130), List(b4131), List(b4132)))
    val b4127 = withCtrl(x8859) { CounterIter(x8816, Some(0)).name("b4127") } // b4127
    val b4130 = withCtrl(x8859) { Const(true).name("b4130") } // b4130
    val b4128 = withCtrl(x8859) { CounterIter(x8817, Some(0)).name("b4128") } // b4128
    val b4131 = withCtrl(x8859) { Const(true).name("b4131") } // b4131
    val b4129 = withCtrl(x8859) { CounterIter(x8818, Some(0)).name("b4129") } // b4129
    val b4132 = withCtrl(x8859) { Const(true).name("b4132") } // b4132
    val b9493 = withCtrl(x8859) { StreamOut(field="offset").name("b9493").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:193:16") } // x8820 = StreamOutNew(BurstCmdBus)
    isAccum(b9493) = false
    bufferDepthOf(b9493) = 1
    val b9494 = withCtrl(x8859) { StreamOut(field="size").name("b9494").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:193:16") } // x8820 = StreamOutNew(BurstCmdBus)
    isAccum(b9494) = false
    bufferDepthOf(b9494) = 1
    val x8821 = withCtrl(x8859) { StreamIn(field="data").name("x8821").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:193:16") } // x8821 = StreamInNew(BurstDataBus())
    isAccum(x8821) = false
    bufferDepthOf(x8821) = 1
    val x8845 = withCtrl(x8859) { UnitController(style=SeqPipe, level=InnerControl).name("x8845").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:193:16") } // UnitPipe(List(b4130, b4131, b4132, b4060, b4056, b4052),Block(x8844))
    val x8822 = withCtrl(x8845) { OpDef(op=FixAdd, inputs=List(b4055, b4127)).name("x8822").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:193:16") } // FixAdd(b4055,b4127)
    val x8823 = withCtrl(x8845) { x8822 } // FixConvert(x8822,TRUE,_32,_0) (Same Type. No op)
    val x8824 = withCtrl(x8845) { OpDef(op=FixSla, inputs=List(x8823, Const(22))).name("x8824").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:193:16") } // FixLsh(x8823,Const(22))
    val x8825 = withCtrl(x8845) { b4128 } // FixConvert(b4128,TRUE,_32,_0) (Same Type. No op)
    val x8826 = withCtrl(x8845) { OpDef(op=FixSla, inputs=List(x8825, Const(12))).name("x8826").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:193:16") } // FixLsh(x8825,Const(12))
    val x8827 = withCtrl(x8845) { b4129 } // FixConvert(b4129,TRUE,_32,_0) (Same Type. No op)
    val x8828 = withCtrl(x8845) { OpDef(op=FixSla, inputs=List(x8827, Const(10))).name("x8828").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:193:16") } // FixLsh(x8827,Const(10))
    val x8829 = withCtrl(x8845) { ReadMem(x8764_d0).name("x8829").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:186:45") } // RegRead(x8764)
    val x8830 = withCtrl(x8845) { x8829 } // FixConvert(x8829,TRUE,_32,_0) (Same Type. No op)
    val x8831 = withCtrl(x8845) { OpDef(op=FixAdd, inputs=List(x8824, x8826)).name("x8831").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:193:16") } // FixAdd(x8824,x8826)
    val x8832 = withCtrl(x8845) { OpDef(op=FixAdd, inputs=List(x8828, x8830)).name("x8832").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:193:16") } // FixAdd(x8828,x8830)
    val x8833 = withCtrl(x8845) { OpDef(op=FixAdd, inputs=List(x8831, x8832)).name("x8833").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:193:16") } // FixAdd(x8831,x8832)
    val x8834 = withCtrl(x8845) { OpDef(op=FixSla, inputs=List(x8833, Const(2))).name("x8834").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:193:16") } // FixLsh(x8833,Const(2))
    val x8835 = withCtrl(x8845) { x8834 } // FixConvert(x8834,TRUE,_64,_0)
    val x8836 = withCtrl(x8845) { DramAddress(x8613).name("x8836").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:193:16") } // GetDRAMAddress(x8613)
    val x8838_x8837 = withCtrl(x8845) { OpDef(op=FixAdd, inputs=List(x8835, x8836)).name("x8838_x8837").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:193:16") } // FixAdd(x8835,x8836)
    // x8838 = SimpleStruct(ArrayBuffer((offset,x8837), (size,Const(512)), (isLoad,Const(true))))
    val x8839 = withCtrl(x8845) { OpDef(op=BitAnd, inputs=List(b4130, b4131)).name("x8839").srcCtx("UnrollingBase.scala:28:66") } // And(b4130,b4131)
    val x8840 = withCtrl(x8845) { OpDef(op=BitAnd, inputs=List(b4132, b4060)).name("x8840").srcCtx("UnrollingBase.scala:28:66") } // And(b4132,b4060)
    val x8841 = withCtrl(x8845) { OpDef(op=BitAnd, inputs=List(b4056, b4052)).name("x8841").srcCtx("UnrollingBase.scala:28:66") } // And(b4056,b4052)
    val x8842 = withCtrl(x8845) { OpDef(op=BitAnd, inputs=List(x8839, x8840)).name("x8842").srcCtx("UnrollingBase.scala:28:66") } // And(x8839,x8840)
    val x8843 = withCtrl(x8845) { OpDef(op=BitAnd, inputs=List(x8842, x8841)).name("x8843").srcCtx("UnrollingBase.scala:28:66") } // And(x8842,x8841)
    val x8844_b9495_b9493 = withCtrl(x8845) { WriteMem(b9493, x8838_x8837).name("x8844_b9495_b9493").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:193:16") } // StreamWrite(x8820,x8838,x8843)
    def split1 = {
    val x8844_b9496_b9494 = withCtrl(x8845) { WriteMem(b9494, Const(512)).name("x8844_b9496_b9494").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:193:16") } // StreamWrite(x8820,x8838,x8843)
    val x8846 = withCtrl(x8859) { FringeDenseLoad(dram=List(x8613), cmdStream=List(b9493, b9494), dataStream=List(x8821)).name("x8846").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:193:16") } // FringeDenseLoad(x8613,x8820,x8821)
    val x8847 = withCtrl(x8859) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x8847").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:193:16") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x8848 = withCtrl(x8859) { CounterChain(List(x8847)).name("x8848").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:193:16") } // CounterChainNew(List(x8847))
    val x8858 = withCtrl(x8859) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8848).name("x8858").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:193:16") } // UnrolledForeach(List(b4130, b4131, b4132, b4060, b4056, b4052),x8848,Block(Const(())),List(List(b4162)),List(List(b4163)))
    val b4162 = withCtrl(x8858) { CounterIter(x8847, None).name("b4162") } // b4162
    val b4163 = withCtrl(x8858) { Const(true).name("b4163") } // b4163
    val x8849 = withCtrl(x8858) { OpDef(op=BitAnd, inputs=List(b4163, b4130)).name("x8849").srcCtx("UnrollingBase.scala:28:66") } // And(b4163,b4130)
    val x8850 = withCtrl(x8858) { OpDef(op=BitAnd, inputs=List(b4131, b4132)).name("x8850").srcCtx("UnrollingBase.scala:28:66") } // And(b4131,b4132)
    val x8851 = withCtrl(x8858) { OpDef(op=BitAnd, inputs=List(b4060, b4056)).name("x8851").srcCtx("UnrollingBase.scala:28:66") } // And(b4060,b4056)
    val x8852 = withCtrl(x8858) { OpDef(op=BitAnd, inputs=List(x8849, x8850)).name("x8852").srcCtx("UnrollingBase.scala:28:66") } // And(x8849,x8850)
    val x8853 = withCtrl(x8858) { OpDef(op=BitAnd, inputs=List(x8851, b4052)).name("x8853").srcCtx("UnrollingBase.scala:28:66") } // And(x8851,b4052)
    val x8854 = withCtrl(x8858) { OpDef(op=BitAnd, inputs=List(x8852, x8853)).name("x8854").srcCtx("UnrollingBase.scala:28:66") } // And(x8852,x8853)
    val x8855_x8855 = withCtrl(x8858) { ReadMem(x8821).name("x8855_x8855").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:193:16") } // ParStreamRead(x8821,List(x8854))
    val x8856_x8856 = withCtrl(x8858) { x8855_x8855 } // VectorApply(x8855,0)
    val x8857 = withCtrl(x8858) { StoreBanks(List(List(x8763_d0_b0, x8763_d0_b1, x8763_d0_b2, x8763_d0_b3, x8763_d0_b4, x8763_d0_b5, x8763_d0_b6, x8763_d0_b7, x8763_d0_b8, x8763_d0_b9, x8763_d0_b10, x8763_d0_b11, x8763_d0_b12, x8763_d0_b13, x8763_d0_b14, x8763_d0_b15)), List(b4128, b4129, b4162), x8856_x8856).name("x8857").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:193:16") } // ParSRAMStore(x8763,List(List(b4128, b4129, b4162)),List(x8856),List(x8854))
    val x8860_d0_b0 = withCtrl(x9420) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x8860_d0_b0").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:26:38:reduceMem") } // x8860 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x8860_d0_b0) = false
    bufferDepthOf(x8860_d0_b0) = 2
    staticDimsOf(x8860_d0_b0) = List(4, 128)
    val x8860_d1_b0 = withCtrl(x9420) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x8860_d1_b0").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:26:38:reduceMem") } // x8860 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x8860_d1_b0) = true
    bufferDepthOf(x8860_d1_b0) = 1
    staticDimsOf(x8860_d1_b0) = List(4, 128)
    val x8861_d0_b0 = withCtrl(x9420) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x8861_d0_b0").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:27:36:foldMem") } // x8861 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x8861_d0_b0) = false
    bufferDepthOf(x8861_d0_b0) = 2
    staticDimsOf(x8861_d0_b0) = List(4, 128)
    val x8862 = withCtrl(x9420) { Counter(min=Const(0), max=Const(1024), step=Const(1), par=4).name("x8862").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:32:53") } // CounterNew(Const(0),Const(1024),Const(1),Const(4))
    val x8863 = withCtrl(x9420) { CounterChain(List(x8862)).name("x8863").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // CounterChainNew(List(x8862))
    val x9270 = withCtrl(x9420) { LoopController(style=MetaPipe, level=OuterControl, cchain=x8863).name("x9270").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // UnrolledReduce(List(b4060, b4056, b4052),x8863,x8860,Block((x8860) => Const(())),List(List(b4182, b4183, b4184, b4185)),List(List(b4186, b4187, b4188, b4189)))
    val b4182 = withCtrl(x9270) { CounterIter(x8862, Some(0)).name("b4182") } // b4182
    val b4186 = withCtrl(x9270) { Const(true).name("b4186") } // b4186
    val b4183 = withCtrl(x9270) { CounterIter(x8862, Some(1)).name("b4183") } // b4183
    val b4187 = withCtrl(x9270) { Const(true).name("b4187") } // b4187
    val b4184 = withCtrl(x9270) { CounterIter(x8862, Some(2)).name("b4184") } // b4184
    val b4188 = withCtrl(x9270) { Const(true).name("b4188") } // b4188
    val b4185 = withCtrl(x9270) { CounterIter(x8862, Some(3)).name("b4185") } // b4185
    val b4189 = withCtrl(x9270) { Const(true).name("b4189") } // b4189
    val x8864_d0_b0 = withCtrl(x9270) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x8864_d0_b0").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:33:45:innerReduceMem") } // x8864 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x8864_d0_b0) = false
    bufferDepthOf(x8864_d0_b0) = 2
    staticDimsOf(x8864_d0_b0) = List(4, 128)
    val x8864_d0_b1 = withCtrl(x9270) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x8864_d0_b1").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:33:45:innerReduceMem") } // x8864 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x8864_d0_b1) = false
    bufferDepthOf(x8864_d0_b1) = 2
    staticDimsOf(x8864_d0_b1) = List(4, 128)
    val x8864_d0_b2 = withCtrl(x9270) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x8864_d0_b2").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:33:45:innerReduceMem") } // x8864 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x8864_d0_b2) = false
    bufferDepthOf(x8864_d0_b2) = 2
    staticDimsOf(x8864_d0_b2) = List(4, 128)
    val x8864_d0_b3 = withCtrl(x9270) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x8864_d0_b3").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:33:45:innerReduceMem") } // x8864 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x8864_d0_b3) = false
    bufferDepthOf(x8864_d0_b3) = 2
    staticDimsOf(x8864_d0_b3) = List(4, 128)
    val x8865_d0_b0 = withCtrl(x9270) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x8865_d0_b0").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:33:45:innerReduceMem") } // x8865 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x8865_d0_b0) = false
    bufferDepthOf(x8865_d0_b0) = 2
    staticDimsOf(x8865_d0_b0) = List(4, 128)
    val x8865_d0_b1 = withCtrl(x9270) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x8865_d0_b1").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:33:45:innerReduceMem") } // x8865 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x8865_d0_b1) = false
    bufferDepthOf(x8865_d0_b1) = 2
    staticDimsOf(x8865_d0_b1) = List(4, 128)
    val x8865_d0_b2 = withCtrl(x9270) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x8865_d0_b2").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:33:45:innerReduceMem") } // x8865 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x8865_d0_b2) = false
    bufferDepthOf(x8865_d0_b2) = 2
    staticDimsOf(x8865_d0_b2) = List(4, 128)
    val x8865_d0_b3 = withCtrl(x9270) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x8865_d0_b3").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:33:45:innerReduceMem") } // x8865 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x8865_d0_b3) = false
    bufferDepthOf(x8865_d0_b3) = 2
    staticDimsOf(x8865_d0_b3) = List(4, 128)
    val x8866_d0_b0 = withCtrl(x9270) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x8866_d0_b0").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:33:45:innerReduceMem") } // x8866 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x8866_d0_b0) = false
    bufferDepthOf(x8866_d0_b0) = 2
    staticDimsOf(x8866_d0_b0) = List(4, 128)
    val x8866_d0_b1 = withCtrl(x9270) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x8866_d0_b1").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:33:45:innerReduceMem") } // x8866 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x8866_d0_b1) = false
    bufferDepthOf(x8866_d0_b1) = 2
    staticDimsOf(x8866_d0_b1) = List(4, 128)
    val x8866_d0_b2 = withCtrl(x9270) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x8866_d0_b2").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:33:45:innerReduceMem") } // x8866 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x8866_d0_b2) = false
    bufferDepthOf(x8866_d0_b2) = 2
    staticDimsOf(x8866_d0_b2) = List(4, 128)
    val x8866_d0_b3 = withCtrl(x9270) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x8866_d0_b3").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:33:45:innerReduceMem") } // x8866 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x8866_d0_b3) = false
    bufferDepthOf(x8866_d0_b3) = 2
    staticDimsOf(x8866_d0_b3) = List(4, 128)
    val x8867_d0_b0 = withCtrl(x9270) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x8867_d0_b0").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:33:45:innerReduceMem") } // x8867 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x8867_d0_b0) = false
    bufferDepthOf(x8867_d0_b0) = 2
    staticDimsOf(x8867_d0_b0) = List(4, 128)
    val x8867_d0_b1 = withCtrl(x9270) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x8867_d0_b1").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:33:45:innerReduceMem") } // x8867 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x8867_d0_b1) = false
    bufferDepthOf(x8867_d0_b1) = 2
    staticDimsOf(x8867_d0_b1) = List(4, 128)
    val x8867_d0_b2 = withCtrl(x9270) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x8867_d0_b2").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:33:45:innerReduceMem") } // x8867 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x8867_d0_b2) = false
    bufferDepthOf(x8867_d0_b2) = 2
    staticDimsOf(x8867_d0_b2) = List(4, 128)
    val x8867_d0_b3 = withCtrl(x9270) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x8867_d0_b3").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:33:45:innerReduceMem") } // x8867 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x8867_d0_b3) = false
    bufferDepthOf(x8867_d0_b3) = 2
    staticDimsOf(x8867_d0_b3) = List(4, 128)
    val x8868_d0 = withCtrl(x9270) { Reg(init=Some(0.0)).name("x8868_d0").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // x8868 = RegNew(Const(0))
    isAccum(x8868_d0) = false
    bufferDepthOf(x8868_d0) = 2
    val x8868_d1 = withCtrl(x9270) { Reg(init=Some(0.0)).name("x8868_d1").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // x8868 = RegNew(Const(0))
    isAccum(x8868_d1) = false
    bufferDepthOf(x8868_d1) = 2
    val x8868_d2 = withCtrl(x9270) { Reg(init=Some(0.0)).name("x8868_d2").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // x8868 = RegNew(Const(0))
    isAccum(x8868_d2) = false
    bufferDepthOf(x8868_d2) = 2
    val x8868_d3 = withCtrl(x9270) { Reg(init=Some(0.0)).name("x8868_d3").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // x8868 = RegNew(Const(0))
    isAccum(x8868_d3) = false
    bufferDepthOf(x8868_d3) = 2
    val x8869_d0 = withCtrl(x9270) { Reg(init=Some(0.0)).name("x8869_d0").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // x8869 = RegNew(Const(0))
    isAccum(x8869_d0) = false
    bufferDepthOf(x8869_d0) = 2
    val x8869_d1 = withCtrl(x9270) { Reg(init=Some(0.0)).name("x8869_d1").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // x8869 = RegNew(Const(0))
    isAccum(x8869_d1) = false
    bufferDepthOf(x8869_d1) = 2
    val x8869_d2 = withCtrl(x9270) { Reg(init=Some(0.0)).name("x8869_d2").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // x8869 = RegNew(Const(0))
    isAccum(x8869_d2) = false
    bufferDepthOf(x8869_d2) = 2
    val x8869_d3 = withCtrl(x9270) { Reg(init=Some(0.0)).name("x8869_d3").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // x8869 = RegNew(Const(0))
    isAccum(x8869_d3) = false
    bufferDepthOf(x8869_d3) = 2
    val x8870_d0 = withCtrl(x9270) { Reg(init=Some(0.0)).name("x8870_d0").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // x8870 = RegNew(Const(0))
    isAccum(x8870_d0) = false
    bufferDepthOf(x8870_d0) = 2
    val x8870_d1 = withCtrl(x9270) { Reg(init=Some(0.0)).name("x8870_d1").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // x8870 = RegNew(Const(0))
    isAccum(x8870_d1) = false
    bufferDepthOf(x8870_d1) = 2
    val x8870_d2 = withCtrl(x9270) { Reg(init=Some(0.0)).name("x8870_d2").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // x8870 = RegNew(Const(0))
    isAccum(x8870_d2) = false
    bufferDepthOf(x8870_d2) = 2
    val x8870_d3 = withCtrl(x9270) { Reg(init=Some(0.0)).name("x8870_d3").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // x8870 = RegNew(Const(0))
    isAccum(x8870_d3) = false
    bufferDepthOf(x8870_d3) = 2
    val x8871_d0 = withCtrl(x9270) { Reg(init=Some(0.0)).name("x8871_d0").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // x8871 = RegNew(Const(0))
    isAccum(x8871_d0) = false
    bufferDepthOf(x8871_d0) = 2
    val x8871_d1 = withCtrl(x9270) { Reg(init=Some(0.0)).name("x8871_d1").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // x8871 = RegNew(Const(0))
    isAccum(x8871_d1) = false
    bufferDepthOf(x8871_d1) = 2
    val x8871_d2 = withCtrl(x9270) { Reg(init=Some(0.0)).name("x8871_d2").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // x8871 = RegNew(Const(0))
    isAccum(x8871_d2) = false
    bufferDepthOf(x8871_d2) = 2
    val x8871_d3 = withCtrl(x9270) { Reg(init=Some(0.0)).name("x8871_d3").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // x8871 = RegNew(Const(0))
    isAccum(x8871_d3) = false
    bufferDepthOf(x8871_d3) = 2
    val x8872_d0 = withCtrl(x9270) { Reg(init=Some(0.0)).name("x8872_d0").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // x8872 = RegNew(Const(0))
    isAccum(x8872_d0) = false
    bufferDepthOf(x8872_d0) = 2
    val x8872_d1 = withCtrl(x9270) { Reg(init=Some(0.0)).name("x8872_d1").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // x8872 = RegNew(Const(0))
    isAccum(x8872_d1) = false
    bufferDepthOf(x8872_d1) = 2
    val x8872_d2 = withCtrl(x9270) { Reg(init=Some(0.0)).name("x8872_d2").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // x8872 = RegNew(Const(0))
    isAccum(x8872_d2) = false
    bufferDepthOf(x8872_d2) = 2
    val x8872_d3 = withCtrl(x9270) { Reg(init=Some(0.0)).name("x8872_d3").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // x8872 = RegNew(Const(0))
    isAccum(x8872_d3) = false
    bufferDepthOf(x8872_d3) = 2
    val x8873_d0 = withCtrl(x9270) { Reg(init=Some(0.0)).name("x8873_d0").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // x8873 = RegNew(Const(0))
    isAccum(x8873_d0) = false
    bufferDepthOf(x8873_d0) = 2
    val x8873_d1 = withCtrl(x9270) { Reg(init=Some(0.0)).name("x8873_d1").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // x8873 = RegNew(Const(0))
    isAccum(x8873_d1) = false
    bufferDepthOf(x8873_d1) = 2
    val x8873_d2 = withCtrl(x9270) { Reg(init=Some(0.0)).name("x8873_d2").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // x8873 = RegNew(Const(0))
    isAccum(x8873_d2) = false
    bufferDepthOf(x8873_d2) = 2
    val x8873_d3 = withCtrl(x9270) { Reg(init=Some(0.0)).name("x8873_d3").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // x8873 = RegNew(Const(0))
    isAccum(x8873_d3) = false
    bufferDepthOf(x8873_d3) = 2
    val x8874_d0 = withCtrl(x9270) { Reg(init=Some(0.0)).name("x8874_d0").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // x8874 = RegNew(Const(0))
    isAccum(x8874_d0) = false
    bufferDepthOf(x8874_d0) = 2
    val x8874_d1 = withCtrl(x9270) { Reg(init=Some(0.0)).name("x8874_d1").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // x8874 = RegNew(Const(0))
    isAccum(x8874_d1) = false
    bufferDepthOf(x8874_d1) = 2
    val x8874_d2 = withCtrl(x9270) { Reg(init=Some(0.0)).name("x8874_d2").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // x8874 = RegNew(Const(0))
    isAccum(x8874_d2) = false
    bufferDepthOf(x8874_d2) = 2
    val x8874_d3 = withCtrl(x9270) { Reg(init=Some(0.0)).name("x8874_d3").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // x8874 = RegNew(Const(0))
    isAccum(x8874_d3) = false
    bufferDepthOf(x8874_d3) = 2
    val x8875_d0 = withCtrl(x9270) { Reg(init=Some(0.0)).name("x8875_d0").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // x8875 = RegNew(Const(0))
    isAccum(x8875_d0) = false
    bufferDepthOf(x8875_d0) = 2
    val x8875_d1 = withCtrl(x9270) { Reg(init=Some(0.0)).name("x8875_d1").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // x8875 = RegNew(Const(0))
    isAccum(x8875_d1) = false
    bufferDepthOf(x8875_d1) = 2
    val x8875_d2 = withCtrl(x9270) { Reg(init=Some(0.0)).name("x8875_d2").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // x8875 = RegNew(Const(0))
    isAccum(x8875_d2) = false
    bufferDepthOf(x8875_d2) = 2
    val x8875_d3 = withCtrl(x9270) { Reg(init=Some(0.0)).name("x8875_d3").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // x8875 = RegNew(Const(0))
    isAccum(x8875_d3) = false
    bufferDepthOf(x8875_d3) = 2
    val x8920 = withCtrl(x9270) { UnitController(style=ForkJoin, level=OuterControl).name("x8920").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b4060, b4056, b4052),Block(Const(())))
    val x8886 = withCtrl(x8920) { UnitController(style=SeqPipe, level=InnerControl).name("x8886").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // UnitPipe(List(b4186, b4060, b4056, b4052),Block(Const(())))
    val x8876 = withCtrl(x8886) { OpDef(op=FixSra, inputs=List(b4182, Const(1))).name("x8876").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:34:38:iReduceTile") } // FixRsh(b4182,Const(1))
    val x8877 = withCtrl(x8886) { OpDef(op=FixSla, inputs=List(x8876, Const(7))).name("x8877").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:35:58") } // FixLsh(x8876,Const(7))
    val x8878 = withCtrl(x8886) { OpDef(op=FixSub, inputs=List(b4182, x8877)).name("x8878").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:35:44:iReduceTileOffset") } // FixSub(b4182,x8877)
    val x8879 = withCtrl(x8886) { OpDef(op=BitAnd, inputs=List(b4186, b4060)).name("x8879").srcCtx("UnrollingBase.scala:28:66") } // And(b4186,b4060)
    val x8880 = withCtrl(x8886) { OpDef(op=BitAnd, inputs=List(b4056, b4052)).name("x8880").srcCtx("UnrollingBase.scala:28:66") } // And(b4056,b4052)
    val x8881 = withCtrl(x8886) { OpDef(op=BitAnd, inputs=List(x8879, x8880)).name("x8881").srcCtx("UnrollingBase.scala:28:66") } // And(x8879,x8880)
    val x8882 = withCtrl(x8886) { LoadBanks(List(x8626_d1_b0), List(b4051, x8876, x8878)).name("x8882").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:36:28:xCandidate") } // SRAMLoad(x8626,ArrayBuffer(Const(8), Const(2), Const(128)),List(b4051, x8876, x8878),Const(0),x8881)
    val x8883 = withCtrl(x8886) { LoadBanks(List(x8628_d1_b0), List(b4055, x8876, x8878)).name("x8883").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:37:28:hCandidate") } // SRAMLoad(x8628,ArrayBuffer(Const(2), Const(2), Const(128)),List(b4055, x8876, x8878),Const(0),x8881)
    val x8884_x8868_d0 = withCtrl(x8886) { WriteMem(x8868_d0, x8882).name("x8884_x8868_d0").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // RegWrite(x8868,x8882,x8881)
    val x8884_x8868_d1 = withCtrl(x8886) { WriteMem(x8868_d1, x8882).name("x8884_x8868_d1").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // RegWrite(x8868,x8882,x8881)
    val x8884_x8868_d2 = withCtrl(x8886) { WriteMem(x8868_d2, x8882).name("x8884_x8868_d2").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // RegWrite(x8868,x8882,x8881)
    val x8884_x8868_d3 = withCtrl(x8886) { WriteMem(x8868_d3, x8882).name("x8884_x8868_d3").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // RegWrite(x8868,x8882,x8881)
    val x8885_x8872_d0 = withCtrl(x8886) { WriteMem(x8872_d0, x8883).name("x8885_x8872_d0").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // RegWrite(x8872,x8883,x8881)
    val x8885_x8872_d1 = withCtrl(x8886) { WriteMem(x8872_d1, x8883).name("x8885_x8872_d1").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // RegWrite(x8872,x8883,x8881)
    val x8885_x8872_d2 = withCtrl(x8886) { WriteMem(x8872_d2, x8883).name("x8885_x8872_d2").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // RegWrite(x8872,x8883,x8881)
    val x8885_x8872_d3 = withCtrl(x8886) { WriteMem(x8872_d3, x8883).name("x8885_x8872_d3").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // RegWrite(x8872,x8883,x8881)
    val x8897 = withCtrl(x8920) { UnitController(style=SeqPipe, level=InnerControl).name("x8897").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // UnitPipe(List(b4187, b4060, b4056, b4052),Block(Const(())))
    val x8887 = withCtrl(x8897) { OpDef(op=FixSra, inputs=List(b4183, Const(1))).name("x8887").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:34:38:iReduceTile") } // FixRsh(b4183,Const(1))
    val x8888 = withCtrl(x8897) { OpDef(op=FixSla, inputs=List(x8887, Const(7))).name("x8888").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:35:58") } // FixLsh(x8887,Const(7))
    val x8889 = withCtrl(x8897) { OpDef(op=FixSub, inputs=List(b4183, x8888)).name("x8889").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:35:44:iReduceTileOffset") } // FixSub(b4183,x8888)
    val x8890 = withCtrl(x8897) { OpDef(op=BitAnd, inputs=List(b4187, b4060)).name("x8890").srcCtx("UnrollingBase.scala:28:66") } // And(b4187,b4060)
    val x8891 = withCtrl(x8897) { OpDef(op=BitAnd, inputs=List(b4056, b4052)).name("x8891").srcCtx("UnrollingBase.scala:28:66") } // And(b4056,b4052)
    val x8892 = withCtrl(x8897) { OpDef(op=BitAnd, inputs=List(x8890, x8891)).name("x8892").srcCtx("UnrollingBase.scala:28:66") } // And(x8890,x8891)
    val x8893 = withCtrl(x8897) { LoadBanks(List(x8626_d2_b0), List(b4051, x8887, x8889)).name("x8893").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:36:28:xCandidate") } // SRAMLoad(x8626,ArrayBuffer(Const(8), Const(2), Const(128)),List(b4051, x8887, x8889),Const(0),x8892)
    val x8894 = withCtrl(x8897) { LoadBanks(List(x8628_d2_b0), List(b4055, x8887, x8889)).name("x8894").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:37:28:hCandidate") } // SRAMLoad(x8628,ArrayBuffer(Const(2), Const(2), Const(128)),List(b4055, x8887, x8889),Const(0),x8892)
    val x8895_x8869_d0 = withCtrl(x8897) { WriteMem(x8869_d0, x8893).name("x8895_x8869_d0").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // RegWrite(x8869,x8893,x8892)
    val x8895_x8869_d1 = withCtrl(x8897) { WriteMem(x8869_d1, x8893).name("x8895_x8869_d1").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // RegWrite(x8869,x8893,x8892)
    val x8895_x8869_d2 = withCtrl(x8897) { WriteMem(x8869_d2, x8893).name("x8895_x8869_d2").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // RegWrite(x8869,x8893,x8892)
    val x8895_x8869_d3 = withCtrl(x8897) { WriteMem(x8869_d3, x8893).name("x8895_x8869_d3").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // RegWrite(x8869,x8893,x8892)
    val x8896_x8873_d0 = withCtrl(x8897) { WriteMem(x8873_d0, x8894).name("x8896_x8873_d0").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // RegWrite(x8873,x8894,x8892)
    val x8896_x8873_d1 = withCtrl(x8897) { WriteMem(x8873_d1, x8894).name("x8896_x8873_d1").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // RegWrite(x8873,x8894,x8892)
    val x8896_x8873_d2 = withCtrl(x8897) { WriteMem(x8873_d2, x8894).name("x8896_x8873_d2").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // RegWrite(x8873,x8894,x8892)
    val x8896_x8873_d3 = withCtrl(x8897) { WriteMem(x8873_d3, x8894).name("x8896_x8873_d3").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // RegWrite(x8873,x8894,x8892)
    val x8908 = withCtrl(x8920) { UnitController(style=SeqPipe, level=InnerControl).name("x8908").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // UnitPipe(List(b4188, b4060, b4056, b4052),Block(Const(())))
    val x8898 = withCtrl(x8908) { OpDef(op=FixSra, inputs=List(b4184, Const(1))).name("x8898").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:34:38:iReduceTile") } // FixRsh(b4184,Const(1))
    val x8899 = withCtrl(x8908) { OpDef(op=FixSla, inputs=List(x8898, Const(7))).name("x8899").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:35:58") } // FixLsh(x8898,Const(7))
    val x8900 = withCtrl(x8908) { OpDef(op=FixSub, inputs=List(b4184, x8899)).name("x8900").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:35:44:iReduceTileOffset") } // FixSub(b4184,x8899)
    val x8901 = withCtrl(x8908) { OpDef(op=BitAnd, inputs=List(b4188, b4060)).name("x8901").srcCtx("UnrollingBase.scala:28:66") } // And(b4188,b4060)
    val x8902 = withCtrl(x8908) { OpDef(op=BitAnd, inputs=List(b4056, b4052)).name("x8902").srcCtx("UnrollingBase.scala:28:66") } // And(b4056,b4052)
    val x8903 = withCtrl(x8908) { OpDef(op=BitAnd, inputs=List(x8901, x8902)).name("x8903").srcCtx("UnrollingBase.scala:28:66") } // And(x8901,x8902)
    val x8904 = withCtrl(x8908) { LoadBanks(List(x8626_d3_b0), List(b4051, x8898, x8900)).name("x8904").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:36:28:xCandidate") } // SRAMLoad(x8626,ArrayBuffer(Const(8), Const(2), Const(128)),List(b4051, x8898, x8900),Const(0),x8903)
    val x8905 = withCtrl(x8908) { LoadBanks(List(x8628_d3_b0), List(b4055, x8898, x8900)).name("x8905").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:37:28:hCandidate") } // SRAMLoad(x8628,ArrayBuffer(Const(2), Const(2), Const(128)),List(b4055, x8898, x8900),Const(0),x8903)
    val x8906_x8870_d0 = withCtrl(x8908) { WriteMem(x8870_d0, x8904).name("x8906_x8870_d0").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // RegWrite(x8870,x8904,x8903)
    val x8906_x8870_d1 = withCtrl(x8908) { WriteMem(x8870_d1, x8904).name("x8906_x8870_d1").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // RegWrite(x8870,x8904,x8903)
    val x8906_x8870_d2 = withCtrl(x8908) { WriteMem(x8870_d2, x8904).name("x8906_x8870_d2").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // RegWrite(x8870,x8904,x8903)
    val x8906_x8870_d3 = withCtrl(x8908) { WriteMem(x8870_d3, x8904).name("x8906_x8870_d3").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // RegWrite(x8870,x8904,x8903)
    val x8907_x8874_d0 = withCtrl(x8908) { WriteMem(x8874_d0, x8905).name("x8907_x8874_d0").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // RegWrite(x8874,x8905,x8903)
    val x8907_x8874_d1 = withCtrl(x8908) { WriteMem(x8874_d1, x8905).name("x8907_x8874_d1").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // RegWrite(x8874,x8905,x8903)
    val x8907_x8874_d2 = withCtrl(x8908) { WriteMem(x8874_d2, x8905).name("x8907_x8874_d2").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // RegWrite(x8874,x8905,x8903)
    val x8907_x8874_d3 = withCtrl(x8908) { WriteMem(x8874_d3, x8905).name("x8907_x8874_d3").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // RegWrite(x8874,x8905,x8903)
    val x8919 = withCtrl(x8920) { UnitController(style=SeqPipe, level=InnerControl).name("x8919").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // UnitPipe(List(b4189, b4060, b4056, b4052),Block(Const(())))
    val x8909 = withCtrl(x8919) { OpDef(op=FixSra, inputs=List(b4185, Const(1))).name("x8909").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:34:38:iReduceTile") } // FixRsh(b4185,Const(1))
    val x8910 = withCtrl(x8919) { OpDef(op=FixSla, inputs=List(x8909, Const(7))).name("x8910").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:35:58") } // FixLsh(x8909,Const(7))
    val x8911 = withCtrl(x8919) { OpDef(op=FixSub, inputs=List(b4185, x8910)).name("x8911").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:35:44:iReduceTileOffset") } // FixSub(b4185,x8910)
    val x8912 = withCtrl(x8919) { OpDef(op=BitAnd, inputs=List(b4189, b4060)).name("x8912").srcCtx("UnrollingBase.scala:28:66") } // And(b4189,b4060)
    val x8913 = withCtrl(x8919) { OpDef(op=BitAnd, inputs=List(b4056, b4052)).name("x8913").srcCtx("UnrollingBase.scala:28:66") } // And(b4056,b4052)
    val x8914 = withCtrl(x8919) { OpDef(op=BitAnd, inputs=List(x8912, x8913)).name("x8914").srcCtx("UnrollingBase.scala:28:66") } // And(x8912,x8913)
    val x8915 = withCtrl(x8919) { LoadBanks(List(x8626_d4_b0), List(b4051, x8909, x8911)).name("x8915").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:36:28:xCandidate") } // SRAMLoad(x8626,ArrayBuffer(Const(8), Const(2), Const(128)),List(b4051, x8909, x8911),Const(0),x8914)
    val x8916 = withCtrl(x8919) { LoadBanks(List(x8628_d4_b0), List(b4055, x8909, x8911)).name("x8916").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:37:28:hCandidate") } // SRAMLoad(x8628,ArrayBuffer(Const(2), Const(2), Const(128)),List(b4055, x8909, x8911),Const(0),x8914)
    val x8917_x8871_d0 = withCtrl(x8919) { WriteMem(x8871_d0, x8915).name("x8917_x8871_d0").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // RegWrite(x8871,x8915,x8914)
    val x8917_x8871_d1 = withCtrl(x8919) { WriteMem(x8871_d1, x8915).name("x8917_x8871_d1").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // RegWrite(x8871,x8915,x8914)
    val x8917_x8871_d2 = withCtrl(x8919) { WriteMem(x8871_d2, x8915).name("x8917_x8871_d2").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // RegWrite(x8871,x8915,x8914)
    val x8917_x8871_d3 = withCtrl(x8919) { WriteMem(x8871_d3, x8915).name("x8917_x8871_d3").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // RegWrite(x8871,x8915,x8914)
    val x8918_x8875_d0 = withCtrl(x8919) { WriteMem(x8875_d0, x8916).name("x8918_x8875_d0").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // RegWrite(x8875,x8916,x8914)
    val x8918_x8875_d1 = withCtrl(x8919) { WriteMem(x8875_d1, x8916).name("x8918_x8875_d1").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // RegWrite(x8875,x8916,x8914)
    val x8918_x8875_d2 = withCtrl(x8919) { WriteMem(x8875_d2, x8916).name("x8918_x8875_d2").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // RegWrite(x8875,x8916,x8914)
    val x8918_x8875_d3 = withCtrl(x8919) { WriteMem(x8875_d3, x8916).name("x8918_x8875_d3").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // RegWrite(x8875,x8916,x8914)
    val x9225 = withCtrl(x9270) { UnitController(style=ForkJoin, level=OuterControl).name("x9225").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b4060, b4056, b4052),Block(Const(())))
    val x8921 = withCtrl(x9225) { Counter(min=Const(0), max=Const(4), step=Const(1), par=4).name("x8921").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:39:28") } // CounterNew(Const(0),Const(4),Const(1),Const(4))
    val x8922 = withCtrl(x9225) { CounterChain(List(x8921)).name("x8922").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:39:35") } // CounterChainNew(List(x8921))
    val x8996 = withCtrl(x9225) { LoopController(style=MetaPipe, level=OuterControl, cchain=x8922).name("x8996").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:39:35") } // UnrolledForeach(List(b4186, b4060, b4056, b4052),x8922,Block(Const(())),List(List(b4255, b4256, b4257, b4258)),List(List(b4259, b4260, b4261, b4262)))
    val b4255 = withCtrl(x8996) { CounterIter(x8921, Some(0)).name("b4255") } // b4255
    val b4259 = withCtrl(x8996) { Const(true).name("b4259") } // b4259
    val b4256 = withCtrl(x8996) { CounterIter(x8921, Some(1)).name("b4256") } // b4256
    val b4260 = withCtrl(x8996) { Const(true).name("b4260") } // b4260
    val b4257 = withCtrl(x8996) { CounterIter(x8921, Some(2)).name("b4257") } // b4257
    val b4261 = withCtrl(x8996) { Const(true).name("b4261") } // b4261
    val b4258 = withCtrl(x8996) { CounterIter(x8921, Some(3)).name("b4258") } // b4258
    val b4262 = withCtrl(x8996) { Const(true).name("b4262") } // b4262
    val x8995 = withCtrl(x8996) { UnitController(style=ForkJoin, level=OuterControl).name("x8995").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b4186, b4060, b4056, b4052),Block(Const(())))
    val x8923 = withCtrl(x8995) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x8923").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:43") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x8924 = withCtrl(x8995) { CounterChain(List(x8923)).name("x8924").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:57") } // CounterChainNew(List(x8923))
    val x8940 = withCtrl(x8995) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8924).name("x8940").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:57") } // UnrolledForeach(List(b4259, b4186, b4060, b4056, b4052),x8924,Block(Const(())),List(List(b4271)),List(List(b4272)))
    val b4271 = withCtrl(x8940) { CounterIter(x8923, None).name("b4271") } // b4271
    val b4272 = withCtrl(x8940) { Const(true).name("b4272") } // b4272
    val x8925 = withCtrl(x8940) { OpDef(op=BitAnd, inputs=List(b4272, b4259)).name("x8925").srcCtx("UnrollingBase.scala:28:66") } // And(b4272,b4259)
    val x8926 = withCtrl(x8940) { OpDef(op=BitAnd, inputs=List(b4186, b4060)).name("x8926").srcCtx("UnrollingBase.scala:28:66") } // And(b4186,b4060)
    val x8927 = withCtrl(x8940) { OpDef(op=BitAnd, inputs=List(b4056, b4052)).name("x8927").srcCtx("UnrollingBase.scala:28:66") } // And(b4056,b4052)
    val x8928 = withCtrl(x8940) { OpDef(op=BitAnd, inputs=List(x8925, x8926)).name("x8928").srcCtx("UnrollingBase.scala:28:66") } // And(x8925,x8926)
    val x8929 = withCtrl(x8940) { OpDef(op=BitAnd, inputs=List(x8928, x8927)).name("x8929").srcCtx("UnrollingBase.scala:28:66") } // And(x8928,x8927)
    val x8930 = withCtrl(x8940) { LoadBanks(List(x8762_d0_b0), List(b4182, b4255, b4271)).name("x8930").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:41:26") } // ParSRAMLoad(x8762,List(List(b4182, b4255, b4271)),List(x8929))
    val x8931 = withCtrl(x8940) { x8930 } // VectorApply(x8930,0)
    val x8932 = withCtrl(x8940) { ReadMem(x8868_d0).name("x8932").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // RegRead(x8868)
    val x8933 = withCtrl(x8940) { OpDef(op=FixMul, inputs=List(x8931, x8932)).name("x8933").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:41:56:reX") } // FixMul(x8931,x8932)
    val x8934 = withCtrl(x8940) { LoadBanks(List(x8763_d0_b0), List(b4182, b4255, b4271)).name("x8934").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:42:26") } // ParSRAMLoad(x8763,List(List(b4182, b4255, b4271)),List(x8929))
    val x8935 = withCtrl(x8940) { x8934 } // VectorApply(x8934,0)
    val x8936 = withCtrl(x8940) { ReadMem(x8872_d0).name("x8936").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // RegRead(x8872)
    val x8937 = withCtrl(x8940) { OpDef(op=FixMul, inputs=List(x8935, x8936)).name("x8937").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:42:56:reH") } // FixMul(x8935,x8936)
    val x8938 = withCtrl(x8940) { OpDef(op=FixAdd, inputs=List(x8933, x8937)).name("x8938").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:43:52") } // FixAdd(x8933,x8937)
    val x8939 = withCtrl(x8940) { StoreBanks(List(List(x8864_d0_b0)), List(b4255, b4271), x8938).name("x8939").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:43:46") } // ParSRAMStore(x8864,List(List(b4255, b4271)),List(x8938),List(x8929))
    val x8941 = withCtrl(x8995) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x8941").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:43") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x8942 = withCtrl(x8995) { CounterChain(List(x8941)).name("x8942").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:57") } // CounterChainNew(List(x8941))
    val x8958 = withCtrl(x8995) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8942).name("x8958").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:57") } // UnrolledForeach(List(b4260, b4186, b4060, b4056, b4052),x8942,Block(Const(())),List(List(b4289)),List(List(b4290)))
    val b4289 = withCtrl(x8958) { CounterIter(x8941, None).name("b4289") } // b4289
    val b4290 = withCtrl(x8958) { Const(true).name("b4290") } // b4290
    val x8943 = withCtrl(x8958) { OpDef(op=BitAnd, inputs=List(b4290, b4260)).name("x8943").srcCtx("UnrollingBase.scala:28:66") } // And(b4290,b4260)
    val x8944 = withCtrl(x8958) { OpDef(op=BitAnd, inputs=List(b4186, b4060)).name("x8944").srcCtx("UnrollingBase.scala:28:66") } // And(b4186,b4060)
    val x8945 = withCtrl(x8958) { OpDef(op=BitAnd, inputs=List(b4056, b4052)).name("x8945").srcCtx("UnrollingBase.scala:28:66") } // And(b4056,b4052)
    val x8946 = withCtrl(x8958) { OpDef(op=BitAnd, inputs=List(x8943, x8944)).name("x8946").srcCtx("UnrollingBase.scala:28:66") } // And(x8943,x8944)
    val x8947 = withCtrl(x8958) { OpDef(op=BitAnd, inputs=List(x8946, x8945)).name("x8947").srcCtx("UnrollingBase.scala:28:66") } // And(x8946,x8945)
    val x8948 = withCtrl(x8958) { LoadBanks(List(x8762_d0_b1), List(b4182, b4256, b4289)).name("x8948").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:41:26") } // ParSRAMLoad(x8762,List(List(b4182, b4256, b4289)),List(x8947))
    val x8949 = withCtrl(x8958) { x8948 } // VectorApply(x8948,0)
    val x8950 = withCtrl(x8958) { ReadMem(x8868_d1).name("x8950").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // RegRead(x8868)
    val x8951 = withCtrl(x8958) { OpDef(op=FixMul, inputs=List(x8949, x8950)).name("x8951").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:41:56:reX") } // FixMul(x8949,x8950)
    val x8952 = withCtrl(x8958) { LoadBanks(List(x8763_d0_b1), List(b4182, b4256, b4289)).name("x8952").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:42:26") } // ParSRAMLoad(x8763,List(List(b4182, b4256, b4289)),List(x8947))
    val x8953 = withCtrl(x8958) { x8952 } // VectorApply(x8952,0)
    val x8954 = withCtrl(x8958) { ReadMem(x8872_d1).name("x8954").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // RegRead(x8872)
    val x8955 = withCtrl(x8958) { OpDef(op=FixMul, inputs=List(x8953, x8954)).name("x8955").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:42:56:reH") } // FixMul(x8953,x8954)
    val x8956 = withCtrl(x8958) { OpDef(op=FixAdd, inputs=List(x8951, x8955)).name("x8956").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:43:52") } // FixAdd(x8951,x8955)
    val x8957 = withCtrl(x8958) { StoreBanks(List(List(x8864_d0_b1)), List(b4256, b4289), x8956).name("x8957").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:43:46") } // ParSRAMStore(x8864,List(List(b4256, b4289)),List(x8956),List(x8947))
    val x8959 = withCtrl(x8995) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x8959").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:43") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x8960 = withCtrl(x8995) { CounterChain(List(x8959)).name("x8960").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:57") } // CounterChainNew(List(x8959))
    val x8976 = withCtrl(x8995) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8960).name("x8976").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:57") } // UnrolledForeach(List(b4261, b4186, b4060, b4056, b4052),x8960,Block(Const(())),List(List(b4307)),List(List(b4308)))
    val b4307 = withCtrl(x8976) { CounterIter(x8959, None).name("b4307") } // b4307
    val b4308 = withCtrl(x8976) { Const(true).name("b4308") } // b4308
    val x8961 = withCtrl(x8976) { OpDef(op=BitAnd, inputs=List(b4308, b4261)).name("x8961").srcCtx("UnrollingBase.scala:28:66") } // And(b4308,b4261)
    val x8962 = withCtrl(x8976) { OpDef(op=BitAnd, inputs=List(b4186, b4060)).name("x8962").srcCtx("UnrollingBase.scala:28:66") } // And(b4186,b4060)
    val x8963 = withCtrl(x8976) { OpDef(op=BitAnd, inputs=List(b4056, b4052)).name("x8963").srcCtx("UnrollingBase.scala:28:66") } // And(b4056,b4052)
    val x8964 = withCtrl(x8976) { OpDef(op=BitAnd, inputs=List(x8961, x8962)).name("x8964").srcCtx("UnrollingBase.scala:28:66") } // And(x8961,x8962)
    val x8965 = withCtrl(x8976) { OpDef(op=BitAnd, inputs=List(x8964, x8963)).name("x8965").srcCtx("UnrollingBase.scala:28:66") } // And(x8964,x8963)
    val x8966 = withCtrl(x8976) { LoadBanks(List(x8762_d0_b2), List(b4182, b4257, b4307)).name("x8966").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:41:26") } // ParSRAMLoad(x8762,List(List(b4182, b4257, b4307)),List(x8965))
    val x8967 = withCtrl(x8976) { x8966 } // VectorApply(x8966,0)
    val x8968 = withCtrl(x8976) { ReadMem(x8868_d2).name("x8968").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // RegRead(x8868)
    val x8969 = withCtrl(x8976) { OpDef(op=FixMul, inputs=List(x8967, x8968)).name("x8969").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:41:56:reX") } // FixMul(x8967,x8968)
    val x8970 = withCtrl(x8976) { LoadBanks(List(x8763_d0_b2), List(b4182, b4257, b4307)).name("x8970").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:42:26") } // ParSRAMLoad(x8763,List(List(b4182, b4257, b4307)),List(x8965))
    val x8971 = withCtrl(x8976) { x8970 } // VectorApply(x8970,0)
    val x8972 = withCtrl(x8976) { ReadMem(x8872_d2).name("x8972").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // RegRead(x8872)
    val x8973 = withCtrl(x8976) { OpDef(op=FixMul, inputs=List(x8971, x8972)).name("x8973").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:42:56:reH") } // FixMul(x8971,x8972)
    val x8974 = withCtrl(x8976) { OpDef(op=FixAdd, inputs=List(x8969, x8973)).name("x8974").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:43:52") } // FixAdd(x8969,x8973)
    val x8975 = withCtrl(x8976) { StoreBanks(List(List(x8864_d0_b2)), List(b4257, b4307), x8974).name("x8975").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:43:46") } // ParSRAMStore(x8864,List(List(b4257, b4307)),List(x8974),List(x8965))
    val x8977 = withCtrl(x8995) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x8977").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:43") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x8978 = withCtrl(x8995) { CounterChain(List(x8977)).name("x8978").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:57") } // CounterChainNew(List(x8977))
    val x8994 = withCtrl(x8995) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8978).name("x8994").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:57") } // UnrolledForeach(List(b4262, b4186, b4060, b4056, b4052),x8978,Block(Const(())),List(List(b4325)),List(List(b4326)))
    val b4325 = withCtrl(x8994) { CounterIter(x8977, None).name("b4325") } // b4325
    val b4326 = withCtrl(x8994) { Const(true).name("b4326") } // b4326
    val x8979 = withCtrl(x8994) { OpDef(op=BitAnd, inputs=List(b4326, b4262)).name("x8979").srcCtx("UnrollingBase.scala:28:66") } // And(b4326,b4262)
    val x8980 = withCtrl(x8994) { OpDef(op=BitAnd, inputs=List(b4186, b4060)).name("x8980").srcCtx("UnrollingBase.scala:28:66") } // And(b4186,b4060)
    val x8981 = withCtrl(x8994) { OpDef(op=BitAnd, inputs=List(b4056, b4052)).name("x8981").srcCtx("UnrollingBase.scala:28:66") } // And(b4056,b4052)
    val x8982 = withCtrl(x8994) { OpDef(op=BitAnd, inputs=List(x8979, x8980)).name("x8982").srcCtx("UnrollingBase.scala:28:66") } // And(x8979,x8980)
    val x8983 = withCtrl(x8994) { OpDef(op=BitAnd, inputs=List(x8982, x8981)).name("x8983").srcCtx("UnrollingBase.scala:28:66") } // And(x8982,x8981)
    val x8984 = withCtrl(x8994) { LoadBanks(List(x8762_d0_b3), List(b4182, b4258, b4325)).name("x8984").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:41:26") } // ParSRAMLoad(x8762,List(List(b4182, b4258, b4325)),List(x8983))
    val x8985 = withCtrl(x8994) { x8984 } // VectorApply(x8984,0)
    val x8986 = withCtrl(x8994) { ReadMem(x8868_d3).name("x8986").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // RegRead(x8868)
    val x8987 = withCtrl(x8994) { OpDef(op=FixMul, inputs=List(x8985, x8986)).name("x8987").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:41:56:reX") } // FixMul(x8985,x8986)
    val x8988 = withCtrl(x8994) { LoadBanks(List(x8763_d0_b3), List(b4182, b4258, b4325)).name("x8988").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:42:26") } // ParSRAMLoad(x8763,List(List(b4182, b4258, b4325)),List(x8983))
    val x8989 = withCtrl(x8994) { x8988 } // VectorApply(x8988,0)
    val x8990 = withCtrl(x8994) { ReadMem(x8872_d3).name("x8990").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // RegRead(x8872)
    val x8991 = withCtrl(x8994) { OpDef(op=FixMul, inputs=List(x8989, x8990)).name("x8991").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:42:56:reH") } // FixMul(x8989,x8990)
    val x8992 = withCtrl(x8994) { OpDef(op=FixAdd, inputs=List(x8987, x8991)).name("x8992").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:43:52") } // FixAdd(x8987,x8991)
    val x8993 = withCtrl(x8994) { StoreBanks(List(List(x8864_d0_b3)), List(b4258, b4325), x8992).name("x8993").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:43:46") } // ParSRAMStore(x8864,List(List(b4258, b4325)),List(x8992),List(x8983))
    val x8997 = withCtrl(x9225) { Counter(min=Const(0), max=Const(4), step=Const(1), par=4).name("x8997").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:39:28") } // CounterNew(Const(0),Const(4),Const(1),Const(4))
    val x8998 = withCtrl(x9225) { CounterChain(List(x8997)).name("x8998").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:39:35") } // CounterChainNew(List(x8997))
    val x9072 = withCtrl(x9225) { LoopController(style=MetaPipe, level=OuterControl, cchain=x8998).name("x9072").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:39:35") } // UnrolledForeach(List(b4187, b4060, b4056, b4052),x8998,Block(Const(())),List(List(b4345, b4346, b4347, b4348)),List(List(b4349, b4350, b4351, b4352)))
    val b4345 = withCtrl(x9072) { CounterIter(x8997, Some(0)).name("b4345") } // b4345
    val b4349 = withCtrl(x9072) { Const(true).name("b4349") } // b4349
    val b4346 = withCtrl(x9072) { CounterIter(x8997, Some(1)).name("b4346") } // b4346
    val b4350 = withCtrl(x9072) { Const(true).name("b4350") } // b4350
    val b4347 = withCtrl(x9072) { CounterIter(x8997, Some(2)).name("b4347") } // b4347
    val b4351 = withCtrl(x9072) { Const(true).name("b4351") } // b4351
    val b4348 = withCtrl(x9072) { CounterIter(x8997, Some(3)).name("b4348") } // b4348
    val b4352 = withCtrl(x9072) { Const(true).name("b4352") } // b4352
    val x9071 = withCtrl(x9072) { UnitController(style=ForkJoin, level=OuterControl).name("x9071").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b4187, b4060, b4056, b4052),Block(Const(())))
    val x8999 = withCtrl(x9071) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x8999").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:43") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x9000 = withCtrl(x9071) { CounterChain(List(x8999)).name("x9000").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:57") } // CounterChainNew(List(x8999))
    val x9016 = withCtrl(x9071) { LoopController(style=InnerPipe, level=InnerControl, cchain=x9000).name("x9016").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:57") } // UnrolledForeach(List(b4349, b4187, b4060, b4056, b4052),x9000,Block(Const(())),List(List(b4361)),List(List(b4362)))
    val b4361 = withCtrl(x9016) { CounterIter(x8999, None).name("b4361") } // b4361
    val b4362 = withCtrl(x9016) { Const(true).name("b4362") } // b4362
    val x9001 = withCtrl(x9016) { OpDef(op=BitAnd, inputs=List(b4362, b4349)).name("x9001").srcCtx("UnrollingBase.scala:28:66") } // And(b4362,b4349)
    val x9002 = withCtrl(x9016) { OpDef(op=BitAnd, inputs=List(b4187, b4060)).name("x9002").srcCtx("UnrollingBase.scala:28:66") } // And(b4187,b4060)
    val x9003 = withCtrl(x9016) { OpDef(op=BitAnd, inputs=List(b4056, b4052)).name("x9003").srcCtx("UnrollingBase.scala:28:66") } // And(b4056,b4052)
    val x9004 = withCtrl(x9016) { OpDef(op=BitAnd, inputs=List(x9001, x9002)).name("x9004").srcCtx("UnrollingBase.scala:28:66") } // And(x9001,x9002)
    val x9005 = withCtrl(x9016) { OpDef(op=BitAnd, inputs=List(x9004, x9003)).name("x9005").srcCtx("UnrollingBase.scala:28:66") } // And(x9004,x9003)
    val x9006 = withCtrl(x9016) { LoadBanks(List(x8762_d0_b4), List(b4183, b4345, b4361)).name("x9006").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:41:26") } // ParSRAMLoad(x8762,List(List(b4183, b4345, b4361)),List(x9005))
    val x9007 = withCtrl(x9016) { x9006 } // VectorApply(x9006,0)
    val x9008 = withCtrl(x9016) { ReadMem(x8869_d0).name("x9008").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // RegRead(x8869)
    val x9009 = withCtrl(x9016) { OpDef(op=FixMul, inputs=List(x9007, x9008)).name("x9009").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:41:56:reX") } // FixMul(x9007,x9008)
    val x9010 = withCtrl(x9016) { LoadBanks(List(x8763_d0_b4), List(b4183, b4345, b4361)).name("x9010").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:42:26") } // ParSRAMLoad(x8763,List(List(b4183, b4345, b4361)),List(x9005))
    val x9011 = withCtrl(x9016) { x9010 } // VectorApply(x9010,0)
    val x9012 = withCtrl(x9016) { ReadMem(x8873_d0).name("x9012").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // RegRead(x8873)
    val x9013 = withCtrl(x9016) { OpDef(op=FixMul, inputs=List(x9011, x9012)).name("x9013").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:42:56:reH") } // FixMul(x9011,x9012)
    val x9014 = withCtrl(x9016) { OpDef(op=FixAdd, inputs=List(x9009, x9013)).name("x9014").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:43:52") } // FixAdd(x9009,x9013)
    val x9015 = withCtrl(x9016) { StoreBanks(List(List(x8865_d0_b0)), List(b4345, b4361), x9014).name("x9015").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:43:46") } // ParSRAMStore(x8865,List(List(b4345, b4361)),List(x9014),List(x9005))
    val x9017 = withCtrl(x9071) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x9017").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:43") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x9018 = withCtrl(x9071) { CounterChain(List(x9017)).name("x9018").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:57") } // CounterChainNew(List(x9017))
    val x9034 = withCtrl(x9071) { LoopController(style=InnerPipe, level=InnerControl, cchain=x9018).name("x9034").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:57") } // UnrolledForeach(List(b4350, b4187, b4060, b4056, b4052),x9018,Block(Const(())),List(List(b4379)),List(List(b4380)))
    val b4379 = withCtrl(x9034) { CounterIter(x9017, None).name("b4379") } // b4379
    val b4380 = withCtrl(x9034) { Const(true).name("b4380") } // b4380
    val x9019 = withCtrl(x9034) { OpDef(op=BitAnd, inputs=List(b4380, b4350)).name("x9019").srcCtx("UnrollingBase.scala:28:66") } // And(b4380,b4350)
    val x9020 = withCtrl(x9034) { OpDef(op=BitAnd, inputs=List(b4187, b4060)).name("x9020").srcCtx("UnrollingBase.scala:28:66") } // And(b4187,b4060)
    val x9021 = withCtrl(x9034) { OpDef(op=BitAnd, inputs=List(b4056, b4052)).name("x9021").srcCtx("UnrollingBase.scala:28:66") } // And(b4056,b4052)
    val x9022 = withCtrl(x9034) { OpDef(op=BitAnd, inputs=List(x9019, x9020)).name("x9022").srcCtx("UnrollingBase.scala:28:66") } // And(x9019,x9020)
    val x9023 = withCtrl(x9034) { OpDef(op=BitAnd, inputs=List(x9022, x9021)).name("x9023").srcCtx("UnrollingBase.scala:28:66") } // And(x9022,x9021)
    val x9024 = withCtrl(x9034) { LoadBanks(List(x8762_d0_b5), List(b4183, b4346, b4379)).name("x9024").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:41:26") } // ParSRAMLoad(x8762,List(List(b4183, b4346, b4379)),List(x9023))
    val x9025 = withCtrl(x9034) { x9024 } // VectorApply(x9024,0)
    val x9026 = withCtrl(x9034) { ReadMem(x8869_d1).name("x9026").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // RegRead(x8869)
    val x9027 = withCtrl(x9034) { OpDef(op=FixMul, inputs=List(x9025, x9026)).name("x9027").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:41:56:reX") } // FixMul(x9025,x9026)
    val x9028 = withCtrl(x9034) { LoadBanks(List(x8763_d0_b5), List(b4183, b4346, b4379)).name("x9028").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:42:26") } // ParSRAMLoad(x8763,List(List(b4183, b4346, b4379)),List(x9023))
    val x9029 = withCtrl(x9034) { x9028 } // VectorApply(x9028,0)
    val x9030 = withCtrl(x9034) { ReadMem(x8873_d1).name("x9030").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // RegRead(x8873)
    val x9031 = withCtrl(x9034) { OpDef(op=FixMul, inputs=List(x9029, x9030)).name("x9031").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:42:56:reH") } // FixMul(x9029,x9030)
    val x9032 = withCtrl(x9034) { OpDef(op=FixAdd, inputs=List(x9027, x9031)).name("x9032").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:43:52") } // FixAdd(x9027,x9031)
    val x9033 = withCtrl(x9034) { StoreBanks(List(List(x8865_d0_b1)), List(b4346, b4379), x9032).name("x9033").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:43:46") } // ParSRAMStore(x8865,List(List(b4346, b4379)),List(x9032),List(x9023))
    val x9035 = withCtrl(x9071) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x9035").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:43") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x9036 = withCtrl(x9071) { CounterChain(List(x9035)).name("x9036").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:57") } // CounterChainNew(List(x9035))
    val x9052 = withCtrl(x9071) { LoopController(style=InnerPipe, level=InnerControl, cchain=x9036).name("x9052").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:57") } // UnrolledForeach(List(b4351, b4187, b4060, b4056, b4052),x9036,Block(Const(())),List(List(b4397)),List(List(b4398)))
    val b4397 = withCtrl(x9052) { CounterIter(x9035, None).name("b4397") } // b4397
    val b4398 = withCtrl(x9052) { Const(true).name("b4398") } // b4398
    val x9037 = withCtrl(x9052) { OpDef(op=BitAnd, inputs=List(b4398, b4351)).name("x9037").srcCtx("UnrollingBase.scala:28:66") } // And(b4398,b4351)
    val x9038 = withCtrl(x9052) { OpDef(op=BitAnd, inputs=List(b4187, b4060)).name("x9038").srcCtx("UnrollingBase.scala:28:66") } // And(b4187,b4060)
    val x9039 = withCtrl(x9052) { OpDef(op=BitAnd, inputs=List(b4056, b4052)).name("x9039").srcCtx("UnrollingBase.scala:28:66") } // And(b4056,b4052)
    val x9040 = withCtrl(x9052) { OpDef(op=BitAnd, inputs=List(x9037, x9038)).name("x9040").srcCtx("UnrollingBase.scala:28:66") } // And(x9037,x9038)
    val x9041 = withCtrl(x9052) { OpDef(op=BitAnd, inputs=List(x9040, x9039)).name("x9041").srcCtx("UnrollingBase.scala:28:66") } // And(x9040,x9039)
    val x9042 = withCtrl(x9052) { LoadBanks(List(x8762_d0_b6), List(b4183, b4347, b4397)).name("x9042").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:41:26") } // ParSRAMLoad(x8762,List(List(b4183, b4347, b4397)),List(x9041))
    val x9043 = withCtrl(x9052) { x9042 } // VectorApply(x9042,0)
    val x9044 = withCtrl(x9052) { ReadMem(x8869_d2).name("x9044").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // RegRead(x8869)
    val x9045 = withCtrl(x9052) { OpDef(op=FixMul, inputs=List(x9043, x9044)).name("x9045").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:41:56:reX") } // FixMul(x9043,x9044)
    val x9046 = withCtrl(x9052) { LoadBanks(List(x8763_d0_b6), List(b4183, b4347, b4397)).name("x9046").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:42:26") } // ParSRAMLoad(x8763,List(List(b4183, b4347, b4397)),List(x9041))
    val x9047 = withCtrl(x9052) { x9046 } // VectorApply(x9046,0)
    val x9048 = withCtrl(x9052) { ReadMem(x8873_d2).name("x9048").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // RegRead(x8873)
    val x9049 = withCtrl(x9052) { OpDef(op=FixMul, inputs=List(x9047, x9048)).name("x9049").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:42:56:reH") } // FixMul(x9047,x9048)
    val x9050 = withCtrl(x9052) { OpDef(op=FixAdd, inputs=List(x9045, x9049)).name("x9050").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:43:52") } // FixAdd(x9045,x9049)
    val x9051 = withCtrl(x9052) { StoreBanks(List(List(x8865_d0_b2)), List(b4347, b4397), x9050).name("x9051").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:43:46") } // ParSRAMStore(x8865,List(List(b4347, b4397)),List(x9050),List(x9041))
    val x9053 = withCtrl(x9071) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x9053").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:43") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x9054 = withCtrl(x9071) { CounterChain(List(x9053)).name("x9054").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:57") } // CounterChainNew(List(x9053))
    val x9070 = withCtrl(x9071) { LoopController(style=InnerPipe, level=InnerControl, cchain=x9054).name("x9070").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:57") } // UnrolledForeach(List(b4352, b4187, b4060, b4056, b4052),x9054,Block(Const(())),List(List(b4415)),List(List(b4416)))
    val b4415 = withCtrl(x9070) { CounterIter(x9053, None).name("b4415") } // b4415
    val b4416 = withCtrl(x9070) { Const(true).name("b4416") } // b4416
    val x9055 = withCtrl(x9070) { OpDef(op=BitAnd, inputs=List(b4416, b4352)).name("x9055").srcCtx("UnrollingBase.scala:28:66") } // And(b4416,b4352)
    val x9056 = withCtrl(x9070) { OpDef(op=BitAnd, inputs=List(b4187, b4060)).name("x9056").srcCtx("UnrollingBase.scala:28:66") } // And(b4187,b4060)
    val x9057 = withCtrl(x9070) { OpDef(op=BitAnd, inputs=List(b4056, b4052)).name("x9057").srcCtx("UnrollingBase.scala:28:66") } // And(b4056,b4052)
    val x9058 = withCtrl(x9070) { OpDef(op=BitAnd, inputs=List(x9055, x9056)).name("x9058").srcCtx("UnrollingBase.scala:28:66") } // And(x9055,x9056)
    val x9059 = withCtrl(x9070) { OpDef(op=BitAnd, inputs=List(x9058, x9057)).name("x9059").srcCtx("UnrollingBase.scala:28:66") } // And(x9058,x9057)
    val x9060 = withCtrl(x9070) { LoadBanks(List(x8762_d0_b7), List(b4183, b4348, b4415)).name("x9060").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:41:26") } // ParSRAMLoad(x8762,List(List(b4183, b4348, b4415)),List(x9059))
    val x9061 = withCtrl(x9070) { x9060 } // VectorApply(x9060,0)
    val x9062 = withCtrl(x9070) { ReadMem(x8869_d3).name("x9062").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // RegRead(x8869)
    val x9063 = withCtrl(x9070) { OpDef(op=FixMul, inputs=List(x9061, x9062)).name("x9063").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:41:56:reX") } // FixMul(x9061,x9062)
    val x9064 = withCtrl(x9070) { LoadBanks(List(x8763_d0_b7), List(b4183, b4348, b4415)).name("x9064").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:42:26") } // ParSRAMLoad(x8763,List(List(b4183, b4348, b4415)),List(x9059))
    val x9065 = withCtrl(x9070) { x9064 } // VectorApply(x9064,0)
    val x9066 = withCtrl(x9070) { ReadMem(x8873_d3).name("x9066").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // RegRead(x8873)
    val x9067 = withCtrl(x9070) { OpDef(op=FixMul, inputs=List(x9065, x9066)).name("x9067").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:42:56:reH") } // FixMul(x9065,x9066)
    val x9068 = withCtrl(x9070) { OpDef(op=FixAdd, inputs=List(x9063, x9067)).name("x9068").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:43:52") } // FixAdd(x9063,x9067)
    val x9069 = withCtrl(x9070) { StoreBanks(List(List(x8865_d0_b3)), List(b4348, b4415), x9068).name("x9069").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:43:46") } // ParSRAMStore(x8865,List(List(b4348, b4415)),List(x9068),List(x9059))
    val x9073 = withCtrl(x9225) { Counter(min=Const(0), max=Const(4), step=Const(1), par=4).name("x9073").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:39:28") } // CounterNew(Const(0),Const(4),Const(1),Const(4))
    val x9074 = withCtrl(x9225) { CounterChain(List(x9073)).name("x9074").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:39:35") } // CounterChainNew(List(x9073))
    val x9148 = withCtrl(x9225) { LoopController(style=MetaPipe, level=OuterControl, cchain=x9074).name("x9148").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:39:35") } // UnrolledForeach(List(b4188, b4060, b4056, b4052),x9074,Block(Const(())),List(List(b4435, b4436, b4437, b4438)),List(List(b4439, b4440, b4441, b4442)))
    val b4435 = withCtrl(x9148) { CounterIter(x9073, Some(0)).name("b4435") } // b4435
    val b4439 = withCtrl(x9148) { Const(true).name("b4439") } // b4439
    val b4436 = withCtrl(x9148) { CounterIter(x9073, Some(1)).name("b4436") } // b4436
    val b4440 = withCtrl(x9148) { Const(true).name("b4440") } // b4440
    val b4437 = withCtrl(x9148) { CounterIter(x9073, Some(2)).name("b4437") } // b4437
    val b4441 = withCtrl(x9148) { Const(true).name("b4441") } // b4441
    val b4438 = withCtrl(x9148) { CounterIter(x9073, Some(3)).name("b4438") } // b4438
    val b4442 = withCtrl(x9148) { Const(true).name("b4442") } // b4442
    val x9147 = withCtrl(x9148) { UnitController(style=ForkJoin, level=OuterControl).name("x9147").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b4188, b4060, b4056, b4052),Block(Const(())))
    val x9075 = withCtrl(x9147) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x9075").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:43") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x9076 = withCtrl(x9147) { CounterChain(List(x9075)).name("x9076").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:57") } // CounterChainNew(List(x9075))
    val x9092 = withCtrl(x9147) { LoopController(style=InnerPipe, level=InnerControl, cchain=x9076).name("x9092").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:57") } // UnrolledForeach(List(b4439, b4188, b4060, b4056, b4052),x9076,Block(Const(())),List(List(b4451)),List(List(b4452)))
    val b4451 = withCtrl(x9092) { CounterIter(x9075, None).name("b4451") } // b4451
    val b4452 = withCtrl(x9092) { Const(true).name("b4452") } // b4452
    val x9077 = withCtrl(x9092) { OpDef(op=BitAnd, inputs=List(b4452, b4439)).name("x9077").srcCtx("UnrollingBase.scala:28:66") } // And(b4452,b4439)
    val x9078 = withCtrl(x9092) { OpDef(op=BitAnd, inputs=List(b4188, b4060)).name("x9078").srcCtx("UnrollingBase.scala:28:66") } // And(b4188,b4060)
    val x9079 = withCtrl(x9092) { OpDef(op=BitAnd, inputs=List(b4056, b4052)).name("x9079").srcCtx("UnrollingBase.scala:28:66") } // And(b4056,b4052)
    val x9080 = withCtrl(x9092) { OpDef(op=BitAnd, inputs=List(x9077, x9078)).name("x9080").srcCtx("UnrollingBase.scala:28:66") } // And(x9077,x9078)
    val x9081 = withCtrl(x9092) { OpDef(op=BitAnd, inputs=List(x9080, x9079)).name("x9081").srcCtx("UnrollingBase.scala:28:66") } // And(x9080,x9079)
    val x9082 = withCtrl(x9092) { LoadBanks(List(x8762_d0_b8), List(b4184, b4435, b4451)).name("x9082").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:41:26") } // ParSRAMLoad(x8762,List(List(b4184, b4435, b4451)),List(x9081))
    val x9083 = withCtrl(x9092) { x9082 } // VectorApply(x9082,0)
    val x9084 = withCtrl(x9092) { ReadMem(x8870_d0).name("x9084").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // RegRead(x8870)
    val x9085 = withCtrl(x9092) { OpDef(op=FixMul, inputs=List(x9083, x9084)).name("x9085").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:41:56:reX") } // FixMul(x9083,x9084)
    val x9086 = withCtrl(x9092) { LoadBanks(List(x8763_d0_b8), List(b4184, b4435, b4451)).name("x9086").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:42:26") } // ParSRAMLoad(x8763,List(List(b4184, b4435, b4451)),List(x9081))
    val x9087 = withCtrl(x9092) { x9086 } // VectorApply(x9086,0)
    val x9088 = withCtrl(x9092) { ReadMem(x8874_d0).name("x9088").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // RegRead(x8874)
    val x9089 = withCtrl(x9092) { OpDef(op=FixMul, inputs=List(x9087, x9088)).name("x9089").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:42:56:reH") } // FixMul(x9087,x9088)
    val x9090 = withCtrl(x9092) { OpDef(op=FixAdd, inputs=List(x9085, x9089)).name("x9090").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:43:52") } // FixAdd(x9085,x9089)
    val x9091 = withCtrl(x9092) { StoreBanks(List(List(x8866_d0_b0)), List(b4435, b4451), x9090).name("x9091").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:43:46") } // ParSRAMStore(x8866,List(List(b4435, b4451)),List(x9090),List(x9081))
    val x9093 = withCtrl(x9147) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x9093").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:43") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x9094 = withCtrl(x9147) { CounterChain(List(x9093)).name("x9094").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:57") } // CounterChainNew(List(x9093))
    val x9110 = withCtrl(x9147) { LoopController(style=InnerPipe, level=InnerControl, cchain=x9094).name("x9110").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:57") } // UnrolledForeach(List(b4440, b4188, b4060, b4056, b4052),x9094,Block(Const(())),List(List(b4469)),List(List(b4470)))
    val b4469 = withCtrl(x9110) { CounterIter(x9093, None).name("b4469") } // b4469
    val b4470 = withCtrl(x9110) { Const(true).name("b4470") } // b4470
    val x9095 = withCtrl(x9110) { OpDef(op=BitAnd, inputs=List(b4470, b4440)).name("x9095").srcCtx("UnrollingBase.scala:28:66") } // And(b4470,b4440)
    val x9096 = withCtrl(x9110) { OpDef(op=BitAnd, inputs=List(b4188, b4060)).name("x9096").srcCtx("UnrollingBase.scala:28:66") } // And(b4188,b4060)
    val x9097 = withCtrl(x9110) { OpDef(op=BitAnd, inputs=List(b4056, b4052)).name("x9097").srcCtx("UnrollingBase.scala:28:66") } // And(b4056,b4052)
    val x9098 = withCtrl(x9110) { OpDef(op=BitAnd, inputs=List(x9095, x9096)).name("x9098").srcCtx("UnrollingBase.scala:28:66") } // And(x9095,x9096)
    val x9099 = withCtrl(x9110) { OpDef(op=BitAnd, inputs=List(x9098, x9097)).name("x9099").srcCtx("UnrollingBase.scala:28:66") } // And(x9098,x9097)
    val x9100 = withCtrl(x9110) { LoadBanks(List(x8762_d0_b9), List(b4184, b4436, b4469)).name("x9100").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:41:26") } // ParSRAMLoad(x8762,List(List(b4184, b4436, b4469)),List(x9099))
    val x9101 = withCtrl(x9110) { x9100 } // VectorApply(x9100,0)
    val x9102 = withCtrl(x9110) { ReadMem(x8870_d1).name("x9102").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // RegRead(x8870)
    val x9103 = withCtrl(x9110) { OpDef(op=FixMul, inputs=List(x9101, x9102)).name("x9103").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:41:56:reX") } // FixMul(x9101,x9102)
    val x9104 = withCtrl(x9110) { LoadBanks(List(x8763_d0_b9), List(b4184, b4436, b4469)).name("x9104").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:42:26") } // ParSRAMLoad(x8763,List(List(b4184, b4436, b4469)),List(x9099))
    val x9105 = withCtrl(x9110) { x9104 } // VectorApply(x9104,0)
    def split2 = {
    val x9106 = withCtrl(x9110) { ReadMem(x8874_d1).name("x9106").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // RegRead(x8874)
    val x9107 = withCtrl(x9110) { OpDef(op=FixMul, inputs=List(x9105, x9106)).name("x9107").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:42:56:reH") } // FixMul(x9105,x9106)
    val x9108 = withCtrl(x9110) { OpDef(op=FixAdd, inputs=List(x9103, x9107)).name("x9108").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:43:52") } // FixAdd(x9103,x9107)
    val x9109 = withCtrl(x9110) { StoreBanks(List(List(x8866_d0_b1)), List(b4436, b4469), x9108).name("x9109").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:43:46") } // ParSRAMStore(x8866,List(List(b4436, b4469)),List(x9108),List(x9099))
    val x9111 = withCtrl(x9147) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x9111").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:43") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x9112 = withCtrl(x9147) { CounterChain(List(x9111)).name("x9112").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:57") } // CounterChainNew(List(x9111))
    val x9128 = withCtrl(x9147) { LoopController(style=InnerPipe, level=InnerControl, cchain=x9112).name("x9128").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:57") } // UnrolledForeach(List(b4441, b4188, b4060, b4056, b4052),x9112,Block(Const(())),List(List(b4487)),List(List(b4488)))
    val b4487 = withCtrl(x9128) { CounterIter(x9111, None).name("b4487") } // b4487
    val b4488 = withCtrl(x9128) { Const(true).name("b4488") } // b4488
    val x9113 = withCtrl(x9128) { OpDef(op=BitAnd, inputs=List(b4488, b4441)).name("x9113").srcCtx("UnrollingBase.scala:28:66") } // And(b4488,b4441)
    val x9114 = withCtrl(x9128) { OpDef(op=BitAnd, inputs=List(b4188, b4060)).name("x9114").srcCtx("UnrollingBase.scala:28:66") } // And(b4188,b4060)
    val x9115 = withCtrl(x9128) { OpDef(op=BitAnd, inputs=List(b4056, b4052)).name("x9115").srcCtx("UnrollingBase.scala:28:66") } // And(b4056,b4052)
    val x9116 = withCtrl(x9128) { OpDef(op=BitAnd, inputs=List(x9113, x9114)).name("x9116").srcCtx("UnrollingBase.scala:28:66") } // And(x9113,x9114)
    val x9117 = withCtrl(x9128) { OpDef(op=BitAnd, inputs=List(x9116, x9115)).name("x9117").srcCtx("UnrollingBase.scala:28:66") } // And(x9116,x9115)
    val x9118 = withCtrl(x9128) { LoadBanks(List(x8762_d0_b10), List(b4184, b4437, b4487)).name("x9118").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:41:26") } // ParSRAMLoad(x8762,List(List(b4184, b4437, b4487)),List(x9117))
    val x9119 = withCtrl(x9128) { x9118 } // VectorApply(x9118,0)
    val x9120 = withCtrl(x9128) { ReadMem(x8870_d2).name("x9120").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // RegRead(x8870)
    val x9121 = withCtrl(x9128) { OpDef(op=FixMul, inputs=List(x9119, x9120)).name("x9121").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:41:56:reX") } // FixMul(x9119,x9120)
    val x9122 = withCtrl(x9128) { LoadBanks(List(x8763_d0_b10), List(b4184, b4437, b4487)).name("x9122").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:42:26") } // ParSRAMLoad(x8763,List(List(b4184, b4437, b4487)),List(x9117))
    val x9123 = withCtrl(x9128) { x9122 } // VectorApply(x9122,0)
    val x9124 = withCtrl(x9128) { ReadMem(x8874_d2).name("x9124").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // RegRead(x8874)
    val x9125 = withCtrl(x9128) { OpDef(op=FixMul, inputs=List(x9123, x9124)).name("x9125").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:42:56:reH") } // FixMul(x9123,x9124)
    val x9126 = withCtrl(x9128) { OpDef(op=FixAdd, inputs=List(x9121, x9125)).name("x9126").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:43:52") } // FixAdd(x9121,x9125)
    val x9127 = withCtrl(x9128) { StoreBanks(List(List(x8866_d0_b2)), List(b4437, b4487), x9126).name("x9127").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:43:46") } // ParSRAMStore(x8866,List(List(b4437, b4487)),List(x9126),List(x9117))
    val x9129 = withCtrl(x9147) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x9129").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:43") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x9130 = withCtrl(x9147) { CounterChain(List(x9129)).name("x9130").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:57") } // CounterChainNew(List(x9129))
    val x9146 = withCtrl(x9147) { LoopController(style=InnerPipe, level=InnerControl, cchain=x9130).name("x9146").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:57") } // UnrolledForeach(List(b4442, b4188, b4060, b4056, b4052),x9130,Block(Const(())),List(List(b4505)),List(List(b4506)))
    val b4505 = withCtrl(x9146) { CounterIter(x9129, None).name("b4505") } // b4505
    val b4506 = withCtrl(x9146) { Const(true).name("b4506") } // b4506
    val x9131 = withCtrl(x9146) { OpDef(op=BitAnd, inputs=List(b4506, b4442)).name("x9131").srcCtx("UnrollingBase.scala:28:66") } // And(b4506,b4442)
    val x9132 = withCtrl(x9146) { OpDef(op=BitAnd, inputs=List(b4188, b4060)).name("x9132").srcCtx("UnrollingBase.scala:28:66") } // And(b4188,b4060)
    val x9133 = withCtrl(x9146) { OpDef(op=BitAnd, inputs=List(b4056, b4052)).name("x9133").srcCtx("UnrollingBase.scala:28:66") } // And(b4056,b4052)
    val x9134 = withCtrl(x9146) { OpDef(op=BitAnd, inputs=List(x9131, x9132)).name("x9134").srcCtx("UnrollingBase.scala:28:66") } // And(x9131,x9132)
    val x9135 = withCtrl(x9146) { OpDef(op=BitAnd, inputs=List(x9134, x9133)).name("x9135").srcCtx("UnrollingBase.scala:28:66") } // And(x9134,x9133)
    val x9136 = withCtrl(x9146) { LoadBanks(List(x8762_d0_b11), List(b4184, b4438, b4505)).name("x9136").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:41:26") } // ParSRAMLoad(x8762,List(List(b4184, b4438, b4505)),List(x9135))
    val x9137 = withCtrl(x9146) { x9136 } // VectorApply(x9136,0)
    val x9138 = withCtrl(x9146) { ReadMem(x8870_d3).name("x9138").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // RegRead(x8870)
    val x9139 = withCtrl(x9146) { OpDef(op=FixMul, inputs=List(x9137, x9138)).name("x9139").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:41:56:reX") } // FixMul(x9137,x9138)
    val x9140 = withCtrl(x9146) { LoadBanks(List(x8763_d0_b11), List(b4184, b4438, b4505)).name("x9140").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:42:26") } // ParSRAMLoad(x8763,List(List(b4184, b4438, b4505)),List(x9135))
    val x9141 = withCtrl(x9146) { x9140 } // VectorApply(x9140,0)
    val x9142 = withCtrl(x9146) { ReadMem(x8874_d3).name("x9142").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // RegRead(x8874)
    val x9143 = withCtrl(x9146) { OpDef(op=FixMul, inputs=List(x9141, x9142)).name("x9143").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:42:56:reH") } // FixMul(x9141,x9142)
    val x9144 = withCtrl(x9146) { OpDef(op=FixAdd, inputs=List(x9139, x9143)).name("x9144").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:43:52") } // FixAdd(x9139,x9143)
    val x9145 = withCtrl(x9146) { StoreBanks(List(List(x8866_d0_b3)), List(b4438, b4505), x9144).name("x9145").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:43:46") } // ParSRAMStore(x8866,List(List(b4438, b4505)),List(x9144),List(x9135))
    val x9149 = withCtrl(x9225) { Counter(min=Const(0), max=Const(4), step=Const(1), par=4).name("x9149").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:39:28") } // CounterNew(Const(0),Const(4),Const(1),Const(4))
    val x9150 = withCtrl(x9225) { CounterChain(List(x9149)).name("x9150").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:39:35") } // CounterChainNew(List(x9149))
    val x9224 = withCtrl(x9225) { LoopController(style=MetaPipe, level=OuterControl, cchain=x9150).name("x9224").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:39:35") } // UnrolledForeach(List(b4189, b4060, b4056, b4052),x9150,Block(Const(())),List(List(b4525, b4526, b4527, b4528)),List(List(b4529, b4530, b4531, b4532)))
    val b4525 = withCtrl(x9224) { CounterIter(x9149, Some(0)).name("b4525") } // b4525
    val b4529 = withCtrl(x9224) { Const(true).name("b4529") } // b4529
    val b4526 = withCtrl(x9224) { CounterIter(x9149, Some(1)).name("b4526") } // b4526
    val b4530 = withCtrl(x9224) { Const(true).name("b4530") } // b4530
    val b4527 = withCtrl(x9224) { CounterIter(x9149, Some(2)).name("b4527") } // b4527
    val b4531 = withCtrl(x9224) { Const(true).name("b4531") } // b4531
    val b4528 = withCtrl(x9224) { CounterIter(x9149, Some(3)).name("b4528") } // b4528
    val b4532 = withCtrl(x9224) { Const(true).name("b4532") } // b4532
    val x9223 = withCtrl(x9224) { UnitController(style=ForkJoin, level=OuterControl).name("x9223").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b4189, b4060, b4056, b4052),Block(Const(())))
    val x9151 = withCtrl(x9223) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x9151").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:43") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x9152 = withCtrl(x9223) { CounterChain(List(x9151)).name("x9152").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:57") } // CounterChainNew(List(x9151))
    val x9168 = withCtrl(x9223) { LoopController(style=InnerPipe, level=InnerControl, cchain=x9152).name("x9168").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:57") } // UnrolledForeach(List(b4529, b4189, b4060, b4056, b4052),x9152,Block(Const(())),List(List(b4541)),List(List(b4542)))
    val b4541 = withCtrl(x9168) { CounterIter(x9151, None).name("b4541") } // b4541
    val b4542 = withCtrl(x9168) { Const(true).name("b4542") } // b4542
    val x9153 = withCtrl(x9168) { OpDef(op=BitAnd, inputs=List(b4542, b4529)).name("x9153").srcCtx("UnrollingBase.scala:28:66") } // And(b4542,b4529)
    val x9154 = withCtrl(x9168) { OpDef(op=BitAnd, inputs=List(b4189, b4060)).name("x9154").srcCtx("UnrollingBase.scala:28:66") } // And(b4189,b4060)
    val x9155 = withCtrl(x9168) { OpDef(op=BitAnd, inputs=List(b4056, b4052)).name("x9155").srcCtx("UnrollingBase.scala:28:66") } // And(b4056,b4052)
    val x9156 = withCtrl(x9168) { OpDef(op=BitAnd, inputs=List(x9153, x9154)).name("x9156").srcCtx("UnrollingBase.scala:28:66") } // And(x9153,x9154)
    val x9157 = withCtrl(x9168) { OpDef(op=BitAnd, inputs=List(x9156, x9155)).name("x9157").srcCtx("UnrollingBase.scala:28:66") } // And(x9156,x9155)
    val x9158 = withCtrl(x9168) { LoadBanks(List(x8762_d0_b12), List(b4185, b4525, b4541)).name("x9158").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:41:26") } // ParSRAMLoad(x8762,List(List(b4185, b4525, b4541)),List(x9157))
    val x9159 = withCtrl(x9168) { x9158 } // VectorApply(x9158,0)
    val x9160 = withCtrl(x9168) { ReadMem(x8871_d0).name("x9160").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // RegRead(x8871)
    val x9161 = withCtrl(x9168) { OpDef(op=FixMul, inputs=List(x9159, x9160)).name("x9161").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:41:56:reX") } // FixMul(x9159,x9160)
    val x9162 = withCtrl(x9168) { LoadBanks(List(x8763_d0_b12), List(b4185, b4525, b4541)).name("x9162").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:42:26") } // ParSRAMLoad(x8763,List(List(b4185, b4525, b4541)),List(x9157))
    val x9163 = withCtrl(x9168) { x9162 } // VectorApply(x9162,0)
    val x9164 = withCtrl(x9168) { ReadMem(x8875_d0).name("x9164").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // RegRead(x8875)
    val x9165 = withCtrl(x9168) { OpDef(op=FixMul, inputs=List(x9163, x9164)).name("x9165").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:42:56:reH") } // FixMul(x9163,x9164)
    val x9166 = withCtrl(x9168) { OpDef(op=FixAdd, inputs=List(x9161, x9165)).name("x9166").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:43:52") } // FixAdd(x9161,x9165)
    val x9167 = withCtrl(x9168) { StoreBanks(List(List(x8867_d0_b0)), List(b4525, b4541), x9166).name("x9167").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:43:46") } // ParSRAMStore(x8867,List(List(b4525, b4541)),List(x9166),List(x9157))
    val x9169 = withCtrl(x9223) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x9169").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:43") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x9170 = withCtrl(x9223) { CounterChain(List(x9169)).name("x9170").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:57") } // CounterChainNew(List(x9169))
    val x9186 = withCtrl(x9223) { LoopController(style=InnerPipe, level=InnerControl, cchain=x9170).name("x9186").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:57") } // UnrolledForeach(List(b4530, b4189, b4060, b4056, b4052),x9170,Block(Const(())),List(List(b4559)),List(List(b4560)))
    val b4559 = withCtrl(x9186) { CounterIter(x9169, None).name("b4559") } // b4559
    val b4560 = withCtrl(x9186) { Const(true).name("b4560") } // b4560
    val x9171 = withCtrl(x9186) { OpDef(op=BitAnd, inputs=List(b4560, b4530)).name("x9171").srcCtx("UnrollingBase.scala:28:66") } // And(b4560,b4530)
    val x9172 = withCtrl(x9186) { OpDef(op=BitAnd, inputs=List(b4189, b4060)).name("x9172").srcCtx("UnrollingBase.scala:28:66") } // And(b4189,b4060)
    val x9173 = withCtrl(x9186) { OpDef(op=BitAnd, inputs=List(b4056, b4052)).name("x9173").srcCtx("UnrollingBase.scala:28:66") } // And(b4056,b4052)
    val x9174 = withCtrl(x9186) { OpDef(op=BitAnd, inputs=List(x9171, x9172)).name("x9174").srcCtx("UnrollingBase.scala:28:66") } // And(x9171,x9172)
    val x9175 = withCtrl(x9186) { OpDef(op=BitAnd, inputs=List(x9174, x9173)).name("x9175").srcCtx("UnrollingBase.scala:28:66") } // And(x9174,x9173)
    val x9176 = withCtrl(x9186) { LoadBanks(List(x8762_d0_b13), List(b4185, b4526, b4559)).name("x9176").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:41:26") } // ParSRAMLoad(x8762,List(List(b4185, b4526, b4559)),List(x9175))
    val x9177 = withCtrl(x9186) { x9176 } // VectorApply(x9176,0)
    val x9178 = withCtrl(x9186) { ReadMem(x8871_d1).name("x9178").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // RegRead(x8871)
    val x9179 = withCtrl(x9186) { OpDef(op=FixMul, inputs=List(x9177, x9178)).name("x9179").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:41:56:reX") } // FixMul(x9177,x9178)
    val x9180 = withCtrl(x9186) { LoadBanks(List(x8763_d0_b13), List(b4185, b4526, b4559)).name("x9180").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:42:26") } // ParSRAMLoad(x8763,List(List(b4185, b4526, b4559)),List(x9175))
    val x9181 = withCtrl(x9186) { x9180 } // VectorApply(x9180,0)
    val x9182 = withCtrl(x9186) { ReadMem(x8875_d1).name("x9182").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // RegRead(x8875)
    val x9183 = withCtrl(x9186) { OpDef(op=FixMul, inputs=List(x9181, x9182)).name("x9183").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:42:56:reH") } // FixMul(x9181,x9182)
    val x9184 = withCtrl(x9186) { OpDef(op=FixAdd, inputs=List(x9179, x9183)).name("x9184").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:43:52") } // FixAdd(x9179,x9183)
    val x9185 = withCtrl(x9186) { StoreBanks(List(List(x8867_d0_b1)), List(b4526, b4559), x9184).name("x9185").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:43:46") } // ParSRAMStore(x8867,List(List(b4526, b4559)),List(x9184),List(x9175))
    val x9187 = withCtrl(x9223) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x9187").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:43") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x9188 = withCtrl(x9223) { CounterChain(List(x9187)).name("x9188").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:57") } // CounterChainNew(List(x9187))
    val x9204 = withCtrl(x9223) { LoopController(style=InnerPipe, level=InnerControl, cchain=x9188).name("x9204").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:57") } // UnrolledForeach(List(b4531, b4189, b4060, b4056, b4052),x9188,Block(Const(())),List(List(b4577)),List(List(b4578)))
    val b4577 = withCtrl(x9204) { CounterIter(x9187, None).name("b4577") } // b4577
    val b4578 = withCtrl(x9204) { Const(true).name("b4578") } // b4578
    val x9189 = withCtrl(x9204) { OpDef(op=BitAnd, inputs=List(b4578, b4531)).name("x9189").srcCtx("UnrollingBase.scala:28:66") } // And(b4578,b4531)
    val x9190 = withCtrl(x9204) { OpDef(op=BitAnd, inputs=List(b4189, b4060)).name("x9190").srcCtx("UnrollingBase.scala:28:66") } // And(b4189,b4060)
    val x9191 = withCtrl(x9204) { OpDef(op=BitAnd, inputs=List(b4056, b4052)).name("x9191").srcCtx("UnrollingBase.scala:28:66") } // And(b4056,b4052)
    val x9192 = withCtrl(x9204) { OpDef(op=BitAnd, inputs=List(x9189, x9190)).name("x9192").srcCtx("UnrollingBase.scala:28:66") } // And(x9189,x9190)
    val x9193 = withCtrl(x9204) { OpDef(op=BitAnd, inputs=List(x9192, x9191)).name("x9193").srcCtx("UnrollingBase.scala:28:66") } // And(x9192,x9191)
    val x9194 = withCtrl(x9204) { LoadBanks(List(x8762_d0_b14), List(b4185, b4527, b4577)).name("x9194").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:41:26") } // ParSRAMLoad(x8762,List(List(b4185, b4527, b4577)),List(x9193))
    val x9195 = withCtrl(x9204) { x9194 } // VectorApply(x9194,0)
    val x9196 = withCtrl(x9204) { ReadMem(x8871_d2).name("x9196").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // RegRead(x8871)
    val x9197 = withCtrl(x9204) { OpDef(op=FixMul, inputs=List(x9195, x9196)).name("x9197").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:41:56:reX") } // FixMul(x9195,x9196)
    val x9198 = withCtrl(x9204) { LoadBanks(List(x8763_d0_b14), List(b4185, b4527, b4577)).name("x9198").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:42:26") } // ParSRAMLoad(x8763,List(List(b4185, b4527, b4577)),List(x9193))
    val x9199 = withCtrl(x9204) { x9198 } // VectorApply(x9198,0)
    val x9200 = withCtrl(x9204) { ReadMem(x8875_d2).name("x9200").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // RegRead(x8875)
    val x9201 = withCtrl(x9204) { OpDef(op=FixMul, inputs=List(x9199, x9200)).name("x9201").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:42:56:reH") } // FixMul(x9199,x9200)
    val x9202 = withCtrl(x9204) { OpDef(op=FixAdd, inputs=List(x9197, x9201)).name("x9202").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:43:52") } // FixAdd(x9197,x9201)
    val x9203 = withCtrl(x9204) { StoreBanks(List(List(x8867_d0_b2)), List(b4527, b4577), x9202).name("x9203").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:43:46") } // ParSRAMStore(x8867,List(List(b4527, b4577)),List(x9202),List(x9193))
    val x9205 = withCtrl(x9223) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x9205").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:43") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x9206 = withCtrl(x9223) { CounterChain(List(x9205)).name("x9206").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:57") } // CounterChainNew(List(x9205))
    val x9222 = withCtrl(x9223) { LoopController(style=InnerPipe, level=InnerControl, cchain=x9206).name("x9222").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:57") } // UnrolledForeach(List(b4532, b4189, b4060, b4056, b4052),x9206,Block(Const(())),List(List(b4595)),List(List(b4596)))
    val b4595 = withCtrl(x9222) { CounterIter(x9205, None).name("b4595") } // b4595
    val b4596 = withCtrl(x9222) { Const(true).name("b4596") } // b4596
    val x9207 = withCtrl(x9222) { OpDef(op=BitAnd, inputs=List(b4596, b4532)).name("x9207").srcCtx("UnrollingBase.scala:28:66") } // And(b4596,b4532)
    val x9208 = withCtrl(x9222) { OpDef(op=BitAnd, inputs=List(b4189, b4060)).name("x9208").srcCtx("UnrollingBase.scala:28:66") } // And(b4189,b4060)
    val x9209 = withCtrl(x9222) { OpDef(op=BitAnd, inputs=List(b4056, b4052)).name("x9209").srcCtx("UnrollingBase.scala:28:66") } // And(b4056,b4052)
    val x9210 = withCtrl(x9222) { OpDef(op=BitAnd, inputs=List(x9207, x9208)).name("x9210").srcCtx("UnrollingBase.scala:28:66") } // And(x9207,x9208)
    val x9211 = withCtrl(x9222) { OpDef(op=BitAnd, inputs=List(x9210, x9209)).name("x9211").srcCtx("UnrollingBase.scala:28:66") } // And(x9210,x9209)
    val x9212 = withCtrl(x9222) { LoadBanks(List(x8762_d0_b15), List(b4185, b4528, b4595)).name("x9212").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:41:26") } // ParSRAMLoad(x8762,List(List(b4185, b4528, b4595)),List(x9211))
    val x9213 = withCtrl(x9222) { x9212 } // VectorApply(x9212,0)
    val x9214 = withCtrl(x9222) { ReadMem(x8871_d3).name("x9214").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // RegRead(x8871)
    val x9215 = withCtrl(x9222) { OpDef(op=FixMul, inputs=List(x9213, x9214)).name("x9215").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:41:56:reX") } // FixMul(x9213,x9214)
    val x9216 = withCtrl(x9222) { LoadBanks(List(x8763_d0_b15), List(b4185, b4528, b4595)).name("x9216").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:42:26") } // ParSRAMLoad(x8763,List(List(b4185, b4528, b4595)),List(x9211))
    val x9217 = withCtrl(x9222) { x9216 } // VectorApply(x9216,0)
    val x9218 = withCtrl(x9222) { ReadMem(x8875_d3).name("x9218").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // RegRead(x8875)
    val x9219 = withCtrl(x9222) { OpDef(op=FixMul, inputs=List(x9217, x9218)).name("x9219").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:42:56:reH") } // FixMul(x9217,x9218)
    val x9220 = withCtrl(x9222) { OpDef(op=FixAdd, inputs=List(x9215, x9219)).name("x9220").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:43:52") } // FixAdd(x9215,x9219)
    val x9221 = withCtrl(x9222) { StoreBanks(List(List(x8867_d0_b3)), List(b4528, b4595), x9220).name("x9221").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:43:46") } // ParSRAMStore(x8867,List(List(b4528, b4595)),List(x9220),List(x9211))
    val x9226 = withCtrl(x9270) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x9226").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x9227 = withCtrl(x9270) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x9227").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x9228 = withCtrl(x9270) { CounterChain(List(x9227,x9226)).name("x9228").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // CounterChainNew(ArrayBuffer(x9227, x9226))
    val x9269 = withCtrl(x9270) { LoopController(style=InnerPipe, level=InnerControl, cchain=x9228).name("x9269").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // UnrolledForeach(List(),x9228,Block(Const(())),ArrayBuffer(List(b4616), List(b4617)),ArrayBuffer(List(b4618), List(b4619)))
    val b4616 = withCtrl(x9269) { CounterIter(x9227, Some(0)).name("b4616") } // b4616
    val b4618 = withCtrl(x9269) { Const(true).name("b4618") } // b4618
    val b4617 = withCtrl(x9269) { CounterIter(x9226, None).name("b4617") } // b4617
    val b4619 = withCtrl(x9269) { Const(true).name("b4619") } // b4619
    val x9229 = withCtrl(x9269) { OpDef(op=BitAnd, inputs=List(b4618, b4619)).name("x9229").srcCtx("UnrollingBase.scala:28:66") } // And(b4618,b4619)
    val x9230 = withCtrl(x9269) { OpDef(op=BitAnd, inputs=List(b4060, b4056)).name("x9230").srcCtx("UnrollingBase.scala:28:66") } // And(b4060,b4056)
    val x9231 = withCtrl(x9269) { OpDef(op=BitAnd, inputs=List(x9229, x9230)).name("x9231").srcCtx("UnrollingBase.scala:28:66") } // And(x9229,x9230)
    val x9232 = withCtrl(x9269) { OpDef(op=BitAnd, inputs=List(x9231, b4052)).name("x9232").srcCtx("UnrollingBase.scala:28:66") } // And(x9231,b4052)
    val x9233 = withCtrl(x9269) { LoadBanks(List(x8864_d0_b0, x8864_d0_b1, x8864_d0_b2, x8864_d0_b3), List(b4616, b4617)).name("x9233").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // ParSRAMLoad(x8864,List(ArrayBuffer(b4616, b4617)),List(x9232))
    val x9234 = withCtrl(x9269) { x9233 } // VectorApply(x9233,0)
    val x9235 = withCtrl(x9269) { LoadBanks(List(x8865_d0_b0, x8865_d0_b1, x8865_d0_b2, x8865_d0_b3), List(b4616, b4617)).name("x9235").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // ParSRAMLoad(x8865,List(ArrayBuffer(b4616, b4617)),List(x9232))
    val x9236 = withCtrl(x9269) { x9235 } // VectorApply(x9235,0)
    val x9237 = withCtrl(x9269) { LoadBanks(List(x8866_d0_b0, x8866_d0_b1, x8866_d0_b2, x8866_d0_b3), List(b4616, b4617)).name("x9237").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // ParSRAMLoad(x8866,List(ArrayBuffer(b4616, b4617)),List(x9232))
    val x9238 = withCtrl(x9269) { x9237 } // VectorApply(x9237,0)
    val x9239 = withCtrl(x9269) { LoadBanks(List(x8867_d0_b0, x8867_d0_b1, x8867_d0_b2, x8867_d0_b3), List(b4616, b4617)).name("x9239").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // ParSRAMLoad(x8867,List(ArrayBuffer(b4616, b4617)),List(x9232))
    val x9240 = withCtrl(x9269) { x9239 } // VectorApply(x9239,0)
    val x9241 = withCtrl(x9269) { LoadBanks(List(x8860_d1_b0), List(b4616, b4617)).name("x9241").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // ParSRAMLoad(x8860,List(ArrayBuffer(b4616, b4617)),List(x9232))
    val x9242 = withCtrl(x9269) { x9241 } // VectorApply(x9241,0)
    val x9243 = withCtrl(x9269) { OpDef(op=BitAnd, inputs=List(b4186, b4060)).name("x9243").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // And(b4186,b4060)
    val x9244 = withCtrl(x9269) { OpDef(op=BitAnd, inputs=List(b4056, b4052)).name("x9244").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // And(b4056,b4052)
    val x9245 = withCtrl(x9269) { OpDef(op=BitAnd, inputs=List(x9243, x9244)).name("x9245").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // And(x9243,x9244)
    val x9246 = withCtrl(x9269) { OpDef(op=BitAnd, inputs=List(b4187, b4060)).name("x9246").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // And(b4187,b4060)
    val x9247 = withCtrl(x9269) { OpDef(op=BitAnd, inputs=List(x9246, x9244)).name("x9247").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // And(x9246,x9244)
    val x9248 = withCtrl(x9269) { OpDef(op=BitAnd, inputs=List(b4188, b4060)).name("x9248").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // And(b4188,b4060)
    val x9249 = withCtrl(x9269) { OpDef(op=BitAnd, inputs=List(x9248, x9244)).name("x9249").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // And(x9248,x9244)
    val x9250 = withCtrl(x9269) { OpDef(op=BitAnd, inputs=List(b4189, b4060)).name("x9250").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // And(b4189,b4060)
    val x9251 = withCtrl(x9269) { OpDef(op=BitAnd, inputs=List(x9250, x9244)).name("x9251").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // And(x9250,x9244)
    val x9252 = withCtrl(x9269) { OpDef(op=BitAnd, inputs=List(x9245, x9232)).name("x9252").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // And(x9245,x9232)
    val x9253 = withCtrl(x9269) { OpDef(op=BitAnd, inputs=List(x9247, x9232)).name("x9253").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // And(x9247,x9232)
    val x9254 = withCtrl(x9269) { OpDef(op=BitAnd, inputs=List(x9249, x9232)).name("x9254").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // And(x9249,x9232)
    val x9255 = withCtrl(x9269) { OpDef(op=BitAnd, inputs=List(x9251, x9232)).name("x9255").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // And(x9251,x9232)
    val x9256 = withCtrl(x9269) { OpDef(op=FixAdd, inputs=List(x9234, x9236)).name("x9256").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:8") } // FixAdd(x9234,x9236)
    val x9257 = withCtrl(x9269) { OpDef(op=MuxOp, inputs=List(x9253, x9256, x9234)).name("x9257").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // Mux(x9253,x9256,x9234)
    val x9258 = withCtrl(x9269) { OpDef(op=BitOr, inputs=List(x9252, x9253)).name("x9258").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // Or(x9252,x9253)
    val x9259 = withCtrl(x9269) { OpDef(op=FixAdd, inputs=List(x9238, x9240)).name("x9259").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:8") } // FixAdd(x9238,x9240)
    val x9260 = withCtrl(x9269) { OpDef(op=MuxOp, inputs=List(x9255, x9259, x9238)).name("x9260").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // Mux(x9255,x9259,x9238)
    val x9261 = withCtrl(x9269) { OpDef(op=BitOr, inputs=List(x9254, x9255)).name("x9261").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // Or(x9254,x9255)
    val x9262 = withCtrl(x9269) { OpDef(op=FixAdd, inputs=List(x9257, x9260)).name("x9262").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:8") } // FixAdd(x9257,x9260)
    val x9263 = withCtrl(x9269) { OpDef(op=MuxOp, inputs=List(x9261, x9262, x9257)).name("x9263").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // Mux(x9261,x9262,x9257)
    val x9264 = withCtrl(x9269) { OpDef(op=BitOr, inputs=List(x9258, x9261)).name("x9264").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // Or(x9258,x9261)
    val x9265 = withCtrl(x9269) { OpDef(op=FixEql, inputs=List(b4182, Const(0))).name("x9265").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // FixEql(b4182,Const(0))
    val x9266 = withCtrl(x9269) { OpDef(op=FixAdd, inputs=List(x9263, x9242)).name("x9266").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:8") } // FixAdd(x9263,x9242)
    val x9267 = withCtrl(x9269) { OpDef(op=MuxOp, inputs=List(x9265, x9263, x9266)).name("x9267").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // Mux(x9265,x9263,x9266)
    val x9268 = withCtrl(x9269) { StoreBanks(List(List(x8860_d0_b0), List(x8860_d1_b0)), List(b4616, b4617), x9267).name("x9268").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:48:6") } // ParSRAMStore(x8860,List(ArrayBuffer(b4616, b4617)),List(x9267),List(x9232))
    antiDepsOf(x9268)=List(x9241)
    val x9271 = withCtrl(x9420) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x9271").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:51:26") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x9272 = withCtrl(x9420) { CounterChain(List(x9271)).name("x9272").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:51:33") } // CounterChainNew(List(x9271))
    val x9286 = withCtrl(x9420) { LoopController(style=MetaPipe, level=OuterControl, cchain=x9272).name("x9286").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:51:33") } // UnrolledForeach(List(b4060, b4056, b4052),x9272,Block(Const(())),List(List(b4664)),List(List(b4665)))
    val b4664 = withCtrl(x9286) { CounterIter(x9271, Some(0)).name("b4664") } // b4664
    val b4665 = withCtrl(x9286) { Const(true).name("b4665") } // b4665
    val x9273 = withCtrl(x9286) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x9273").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:52:41") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x9274 = withCtrl(x9286) { CounterChain(List(x9273)).name("x9274").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:52:55") } // CounterChainNew(List(x9273))
    val x9285 = withCtrl(x9286) { LoopController(style=InnerPipe, level=InnerControl, cchain=x9274).name("x9285").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:52:55") } // UnrolledForeach(List(b4665, b4060, b4056, b4052),x9274,Block(Const(())),List(List(b4668)),List(List(b4669)))
    val b4668 = withCtrl(x9285) { CounterIter(x9273, None).name("b4668") } // b4668
    val b4669 = withCtrl(x9285) { Const(true).name("b4669") } // b4669
    val x9275 = withCtrl(x9285) { OpDef(op=BitAnd, inputs=List(b4669, b4665)).name("x9275").srcCtx("UnrollingBase.scala:28:66") } // And(b4669,b4665)
    val x9276 = withCtrl(x9285) { OpDef(op=BitAnd, inputs=List(b4060, b4056)).name("x9276").srcCtx("UnrollingBase.scala:28:66") } // And(b4060,b4056)
    val x9277 = withCtrl(x9285) { OpDef(op=BitAnd, inputs=List(x9275, x9276)).name("x9277").srcCtx("UnrollingBase.scala:28:66") } // And(x9275,x9276)
    val x9278 = withCtrl(x9285) { OpDef(op=BitAnd, inputs=List(x9277, b4052)).name("x9278").srcCtx("UnrollingBase.scala:28:66") } // And(x9277,b4052)
    val x9279 = withCtrl(x9285) { LoadBanks(List(x8860_d0_b0), List(b4664, b4668)).name("x9279").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:53:32") } // ParSRAMLoad(x8860,List(List(b4664, b4668)),List(x9278))
    val x9280 = withCtrl(x9285) { x9279 } // VectorApply(x9279,0)
    val x9281 = withCtrl(x9285) { LoadBanks(List(x8629_d0_b0), List(b4055, b4664, b4059, b4668)).name("x9281").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:53:56") } // ParSRAMLoad(x8629,List(List(b4055, b4664, b4059, b4668)),List(x9278))
    val x9282 = withCtrl(x9285) { x9281 } // VectorApply(x9281,0)
    val x9283 = withCtrl(x9285) { OpDef(op=FixAdd, inputs=List(x9280, x9282)).name("x9283").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:53:53:foldVal") } // FixAdd(x9280,x9282)
    val x9284 = withCtrl(x9285) { StoreBanks(List(List(x8861_d0_b0)), List(b4664, b4668), x9283).name("x9284").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:54:37") } // ParSRAMStore(x8861,List(List(b4664, b4668)),List(x9283),List(x9278))
    val x9287 = withCtrl(x9420) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x9287").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:59:21") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x9288 = withCtrl(x9420) { CounterChain(List(x9287)).name("x9288").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:59:27") } // CounterChainNew(List(x9287))
    val x9419 = withCtrl(x9420) { LoopController(style=MetaPipe, level=OuterControl, cchain=x9288).name("x9419").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:59:27") } // UnrolledForeach(List(b4060, b4056, b4052),x9288,Block(Const(())),List(List(b4684)),List(List(b4685)))
    val b4684 = withCtrl(x9419) { CounterIter(x9287, Some(0)).name("b4684") } // b4684
    val b4685 = withCtrl(x9419) { Const(true).name("b4685") } // b4685
    val x9289 = withCtrl(x9419) { FIFO(size=16).name("x9289").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:60:25:sigQ") } // x9289 = FIFONew(Const(16))
    isAccum(x9289) = false
    bufferDepthOf(x9289) = 2
    val x9290 = withCtrl(x9419) { FIFO(size=16).name("x9290").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:61:26:sigQQ") } // x9290 = FIFONew(Const(16))
    isAccum(x9290) = false
    bufferDepthOf(x9290) = 2
    val x9291 = withCtrl(x9419) { FIFO(size=16).name("x9291").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:62:31:sigEleMuxQ") } // x9291 = FIFONew(Const(16))
    isAccum(x9291) = false
    bufferDepthOf(x9291) = 2
    val x9292 = withCtrl(x9419) { FIFO(size=16).name("x9292").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:63:26:tanhQ") } // x9292 = FIFONew(Const(16))
    isAccum(x9292) = false
    bufferDepthOf(x9292) = 2
    val x9293 = withCtrl(x9419) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x9293").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:65:41") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x9294 = withCtrl(x9419) { CounterChain(List(x9293)).name("x9294").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:65:55") } // CounterChainNew(List(x9293))
    val x9318 = withCtrl(x9419) { LoopController(style=InnerPipe, level=InnerControl, cchain=x9294).name("x9318").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:65:55") } // UnrolledForeach(List(b4685, b4060, b4056, b4052),x9294,Block(Const(())),List(List(b4692)),List(List(b4693)))
    val b4692 = withCtrl(x9318) { CounterIter(x9293, None).name("b4692") } // b4692
    val b4693 = withCtrl(x9318) { Const(true).name("b4693") } // b4693
    val x9295 = withCtrl(x9318) { OpDef(op=BitAnd, inputs=List(b4693, b4685)).name("x9295").srcCtx("UnrollingBase.scala:28:66") } // And(b4693,b4685)
    val x9296 = withCtrl(x9318) { OpDef(op=BitAnd, inputs=List(b4060, b4056)).name("x9296").srcCtx("UnrollingBase.scala:28:66") } // And(b4060,b4056)
    val x9297 = withCtrl(x9318) { OpDef(op=BitAnd, inputs=List(x9295, x9296)).name("x9297").srcCtx("UnrollingBase.scala:28:66") } // And(x9295,x9296)
    val x9298 = withCtrl(x9318) { OpDef(op=BitAnd, inputs=List(x9297, b4052)).name("x9298").srcCtx("UnrollingBase.scala:28:66") } // And(x9297,b4052)
    val x9299 = withCtrl(x9318) { LoadBanks(List(x8861_d0_b0), List(b4684, b4692)).name("x9299").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:66:27:pEle") } // ParSRAMLoad(x8861,List(List(b4684, b4692)),List(x9298))
    val x9300 = withCtrl(x9318) { x9299 } // VectorApply(x9299,0)
    val x9301_d0_b0 = withCtrl(x9318) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x9301_d0_b0").srcCtx("NonLinearity.scala:43:37:lut") } // x9301 = LUTNew(List(1024), Seq(Const(-0.9999997615814208984375),Const(-0.9999997615814208984375),Const(-0.999999701976776123046875)... [1021 more]))
    isAccum(x9301_d0_b0) = false
    bufferDepthOf(x9301_d0_b0) = 1
    staticDimsOf(x9301_d0_b0) = List(1024)
    val x9302 = withCtrl(x9318) { OpDef(op=FixSub, inputs=List(x9300, Const(-8.0))).name("x9302").srcCtx("NonLinearity.scala:44:22") } // FixSub(x9300,Const(-8))
    val x9303 = withCtrl(x9318) { OpDef(op=FixSla, inputs=List(x9302, Const(6))).name("x9303").srcCtx("NonLinearity.scala:44:30") } // FixLsh(x9302,Const(6))
    // x9304 = FixConvert(x9303,TRUE,_32,_0) x.tp=FixPt[TRUE,_8,_24] {
    val x9304 = withCtrl(x9318) { OpDef(op=FixSra, inputs=List(x9303, Const("24"))).name("x9304").srcCtx("NonLinearity.scala:44:41:index") } // FixConvert(x9303,TRUE,_32,_0)
    // }
    val x9305 = withCtrl(x9318) { LoadBanks(List(x9301_d0_b0), List(x9304)).name("x9305").srcCtx("NonLinearity.scala:45:17:sigVal") } // LUTLoad(x9301,List(x9304),x9298)
    val x9306_d0_b0 = withCtrl(x9318) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x9306_d0_b0").srcCtx("NonLinearity.scala:43:37:lut") } // x9306 = LUTNew(List(1024), Seq(Const(-0.9999997615814208984375),Const(-0.9999997615814208984375),Const(-0.999999701976776123046875)... [1021 more]))
    isAccum(x9306_d0_b0) = false
    bufferDepthOf(x9306_d0_b0) = 1
    staticDimsOf(x9306_d0_b0) = List(1024)
    val x9307 = withCtrl(x9318) { LoadBanks(List(x9306_d0_b0), List(x9304)).name("x9307").srcCtx("NonLinearity.scala:45:17:tanhVal") } // LUTLoad(x9306,List(x9304),x9298)
    val x9308 = withCtrl(x9318) { OpDef(op=FixLt, inputs=List(Const(8.0), x9300)).name("x9308").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:70:27:isHigh") } // FixLt(Const(8),x9300)
    val x9309 = withCtrl(x9318) { OpDef(op=FixLt, inputs=List(x9300, Const(-8.0))).name("x9309").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:71:26:isLow") } // FixLt(x9300,Const(-8))
    val x9310 = withCtrl(x9318) { OpDef(op=MuxOp, inputs=List(x9309, Const(0.0), x9305)).name("x9310").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:73:46") } // Mux(x9309,Const(0),x9305)
    val x9311 = withCtrl(x9318) { OpDef(op=MuxOp, inputs=List(x9308, Const(1.0), x9310)).name("x9311").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:73:25:sigEle") } // Mux(x9308,Const(1),x9310)
    val x9312 = withCtrl(x9318) { OpDef(op=MuxOp, inputs=List(x9309, Const(-1.0), x9307)).name("x9312").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:74:47") } // Mux(x9309,Const(-1),x9307)
    val x9313 = withCtrl(x9318) { OpDef(op=MuxOp, inputs=List(x9308, Const(1.0), x9312)).name("x9313").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:74:26:tanhEle") } // Mux(x9308,Const(1),x9312)
    val x9314_x9289 = withCtrl(x9318) { WriteMem(x9289, x9311).name("x9314_x9289").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:76:17") } // ParFIFOEnq(x9289,List(x9311),List(x9298))
    val x9315_x9290 = withCtrl(x9318) { WriteMem(x9290, x9311).name("x9315_x9290").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:77:18") } // ParFIFOEnq(x9290,List(x9311),List(x9298))
    val x9316_x9291 = withCtrl(x9318) { WriteMem(x9291, x9311).name("x9316_x9291").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:78:23") } // ParFIFOEnq(x9291,List(x9311),List(x9298))
    val x9317_x9292 = withCtrl(x9318) { WriteMem(x9292, x9313).name("x9317_x9292").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:80:18") } // ParFIFOEnq(x9292,List(x9313),List(x9298))
    val x9418 = withCtrl(x9419) { UnitController(style=SeqPipe, level=OuterControl).name("x9418").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:83:12") } // UnitPipe(List(b4685, b4060, b4056, b4052),Block(Const(())))
    val x9319 = withCtrl(x9418) { FIFO(size=16).name("x9319").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:86:29:cLastQ") } // x9319 = FIFONew(Const(16))
    isAccum(x9319) = false
    bufferDepthOf(x9319) = 1
    val x9320 = withCtrl(x9418) { FIFO(size=16).name("x9320").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:87:35:cNewMultAddQ") } // x9320 = FIFONew(Const(16))
    isAccum(x9320) = false
    bufferDepthOf(x9320) = 1
    val x9321 = withCtrl(x9418) { FIFO(size=16).name("x9321").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:88:36:cNewMultAddQQ") } // x9321 = FIFONew(Const(16))
    isAccum(x9321) = false
    bufferDepthOf(x9321) = 1
    val x9322 = withCtrl(x9418) { FIFO(size=16).name("x9322").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:89:32:cNewMultQ") } // x9322 = FIFONew(Const(16))
    isAccum(x9322) = false
    bufferDepthOf(x9322) = 1
    val x9323 = withCtrl(x9418) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x9323").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:91:43") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x9324 = withCtrl(x9418) { CounterChain(List(x9323)).name("x9324").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:91:57") } // CounterChainNew(List(x9323))
    val x9342 = withCtrl(x9418) { LoopController(style=InnerPipe, level=InnerControl, cchain=x9324).name("x9342").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:91:57") } // UnrolledForeach(List(b4685, b4060, b4056, b4052),x9324,Block(Const(())),List(List(b4724)),List(List(b4725)))
    val b4724 = withCtrl(x9342) { CounterIter(x9323, None).name("b4724") } // b4724
    val b4725 = withCtrl(x9342) { Const(true).name("b4725") } // b4725
    val x9325 = withCtrl(x9342) { OpDef(op=BitAnd, inputs=List(b4725, b4685)).name("x9325").srcCtx("UnrollingBase.scala:28:66") } // And(b4725,b4685)
    val x9326 = withCtrl(x9342) { OpDef(op=BitAnd, inputs=List(b4060, b4056)).name("x9326").srcCtx("UnrollingBase.scala:28:66") } // And(b4060,b4056)
    val x9327 = withCtrl(x9342) { OpDef(op=BitAnd, inputs=List(x9325, x9326)).name("x9327").srcCtx("UnrollingBase.scala:28:66") } // And(x9325,x9326)
    val x9328 = withCtrl(x9342) { OpDef(op=BitAnd, inputs=List(x9327, b4052)).name("x9328").srcCtx("UnrollingBase.scala:28:66") } // And(x9327,b4052)
    val x9329 = withCtrl(x9342) { ReadMem(x9289).name("x9329").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:92:32:sigEle") } // ParFIFODeq(x9289,List(x9328))
    val x9330 = withCtrl(x9342) { x9329 } // VectorApply(x9329,0)
    val x9331 = withCtrl(x9342) { ReadMem(x9292).name("x9331").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:93:34:tanhEle") } // ParFIFODeq(x9292,List(x9328))
    val x9332 = withCtrl(x9342) { x9331 } // VectorApply(x9331,0)
    val x9333 = withCtrl(x9342) { LoadBanks(List(x8627_d0_b0), List(b4055, b4059, b4724)).name("x9333").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:95:24:cLast") } // ParSRAMLoad(x8627,List(List(b4055, b4059, b4724)),List(x9328))
    val x9334 = withCtrl(x9342) { x9333 } // VectorApply(x9333,0)
    val x9335 = withCtrl(x9342) { OpDef(op=FixMul, inputs=List(x9334, x9332)).name("x9335").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:96:32:cNewMult") } // FixMul(x9334,x9332)
    val x9336 = withCtrl(x9342) { OpDef(op=FixMul, inputs=List(x9330, x9334)).name("x9336").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:97:36") } // FixMul(x9330,x9334)
    val x9337 = withCtrl(x9342) { OpDef(op=FixAdd, inputs=List(x9336, x9335)).name("x9337").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:97:44:cNewMultAdd") } // FixAdd(x9336,x9335)
    val x9338_x9322 = withCtrl(x9342) { WriteMem(x9322, x9335).name("x9338_x9322").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:99:24") } // ParFIFOEnq(x9322,List(x9335),List(x9328))
    val x9339_x9320 = withCtrl(x9342) { WriteMem(x9320, x9337).name("x9339_x9320").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:100:27") } // ParFIFOEnq(x9320,List(x9337),List(x9328))
    val x9340_x9321 = withCtrl(x9342) { WriteMem(x9321, x9337).name("x9340_x9321").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:101:28") } // ParFIFOEnq(x9321,List(x9337),List(x9328))
    val x9341_x9319 = withCtrl(x9342) { WriteMem(x9319, x9334).name("x9341_x9319").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:102:21") } // ParFIFOEnq(x9319,List(x9334),List(x9328))
    val x9343 = withCtrl(x9418) { FIFO(size=16).name("x9343").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:106:31:cUpdateQ") } // x9343 = FIFONew(Const(16))
    isAccum(x9343) = false
    bufferDepthOf(x9343) = 1
    val x9344 = withCtrl(x9418) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x9344").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:107:43") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x9345 = withCtrl(x9418) { CounterChain(List(x9344)).name("x9345").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:107:57") } // CounterChainNew(List(x9344))
    val x9365 = withCtrl(x9418) { LoopController(style=InnerPipe, level=InnerControl, cchain=x9345).name("x9365").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:107:57") } // UnrolledForeach(List(b4685, b4060, b4056, b4052),x9345,Block(Const(())),List(List(b4747)),List(List(b4748)))
    val b4747 = withCtrl(x9365) { CounterIter(x9344, None).name("b4747") } // b4747
    val b4748 = withCtrl(x9365) { Const(true).name("b4748") } // b4748
    val x9346 = withCtrl(x9365) { OpDef(op=BitAnd, inputs=List(b4748, b4685)).name("x9346").srcCtx("UnrollingBase.scala:28:66") } // And(b4748,b4685)
    val x9347 = withCtrl(x9365) { OpDef(op=BitAnd, inputs=List(b4060, b4056)).name("x9347").srcCtx("UnrollingBase.scala:28:66") } // And(b4060,b4056)
    val x9348 = withCtrl(x9365) { OpDef(op=BitAnd, inputs=List(x9346, x9347)).name("x9348").srcCtx("UnrollingBase.scala:28:66") } // And(x9346,x9347)
    val x9349 = withCtrl(x9365) { OpDef(op=BitAnd, inputs=List(x9348, b4052)).name("x9349").srcCtx("UnrollingBase.scala:28:66") } // And(x9348,b4052)
    val x9350 = withCtrl(x9365) { ReadMem(x9322).name("x9350").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:108:39:cNewMult") } // ParFIFODeq(x9322,List(x9349))
    val x9351 = withCtrl(x9365) { x9350 } // VectorApply(x9350,0)
    val x9352 = withCtrl(x9365) { ReadMem(x9291).name("x9352").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:109:38:sigEle") } // ParFIFODeq(x9291,List(x9349))
    val x9353 = withCtrl(x9365) { x9352 } // VectorApply(x9352,0)
    val x9354 = withCtrl(x9365) { ReadMem(x9320).name("x9354").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:110:45:cNewMultAdd") } // ParFIFODeq(x9320,List(x9349))
    val x9355 = withCtrl(x9365) { x9354 } // VectorApply(x9354,0)
    val x9356 = withCtrl(x9365) { ReadMem(x9319).name("x9356").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:111:33:cLast") } // ParFIFODeq(x9319,List(x9349))
    val x9357 = withCtrl(x9365) { x9356 } // VectorApply(x9356,0)
    val x9358 = withCtrl(x9365) { OpDef(op=FixEql, inputs=List(b4684, Const(0))).name("x9358").srcCtx("package.scala:56:42") } // FixEql(b4684,Const(0))
    val x9359 = withCtrl(x9365) { OpDef(op=FixEql, inputs=List(b4684, Const(1))).name("x9359").srcCtx("package.scala:59:42") } // FixEql(b4684,Const(1))
    val x9360 = withCtrl(x9365) { OpDef(op=FixEql, inputs=List(b4684, Const(2))).name("x9360").srcCtx("package.scala:62:42") } // FixEql(b4684,Const(2))
    val x9361 = withCtrl(x9365) { OpDef(op=MuxOp, inputs=List(x9360, x9355, x9357)).name("x9361").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:115:30") } // Mux(x9360,x9355,x9357)
    val x9362 = withCtrl(x9365) { OpDef(op=MuxOp, inputs=List(x9359, x9351, x9361)).name("x9362").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:114:30") } // Mux(x9359,x9351,x9361)
    val x9363 = withCtrl(x9365) { OpDef(op=MuxOp, inputs=List(x9358, x9353, x9362)).name("x9363").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:113:28:cUpdate") } // Mux(x9358,x9353,x9362)
    val x9364_x9343 = withCtrl(x9365) { WriteMem(x9343, x9363).name("x9364_x9343").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:118:23") } // ParFIFOEnq(x9343,List(x9363),List(x9349))
    val x9366 = withCtrl(x9418) { FIFO(size=16).name("x9366").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:122:31:hLinearQ") } // x9366 = FIFONew(Const(16))
    isAccum(x9366) = false
    bufferDepthOf(x9366) = 1
    val x9367 = withCtrl(x9418) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x9367").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:123:43") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x9368 = withCtrl(x9418) { CounterChain(List(x9367)).name("x9368").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:123:57") } // CounterChainNew(List(x9367))
    val x9385 = withCtrl(x9418) { LoopController(style=InnerPipe, level=InnerControl, cchain=x9368).name("x9385").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:123:57") } // UnrolledForeach(List(b4685, b4060, b4056, b4052),x9368,Block(Const(())),List(List(b4772)),List(List(b4773)))
    val b4772 = withCtrl(x9385) { CounterIter(x9367, None).name("b4772") } // b4772
    val b4773 = withCtrl(x9385) { Const(true).name("b4773") } // b4773
    val x9369 = withCtrl(x9385) { OpDef(op=BitAnd, inputs=List(b4773, b4685)).name("x9369").srcCtx("UnrollingBase.scala:28:66") } // And(b4773,b4685)
    val x9370 = withCtrl(x9385) { OpDef(op=BitAnd, inputs=List(b4060, b4056)).name("x9370").srcCtx("UnrollingBase.scala:28:66") } // And(b4060,b4056)
    val x9371 = withCtrl(x9385) { OpDef(op=BitAnd, inputs=List(x9369, x9370)).name("x9371").srcCtx("UnrollingBase.scala:28:66") } // And(x9369,x9370)
    val x9372 = withCtrl(x9385) { OpDef(op=BitAnd, inputs=List(x9371, b4052)).name("x9372").srcCtx("UnrollingBase.scala:28:66") } // And(x9371,b4052)
    val x9373 = withCtrl(x9385) { ReadMem(x9321).name("x9373").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:124:46:cNewMultAdd") } // ParFIFODeq(x9321,List(x9372))
    val x9374 = withCtrl(x9385) { x9373 } // VectorApply(x9373,0)
    val x9375_d0_b0 = withCtrl(x9385) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x9375_d0_b0").srcCtx("NonLinearity.scala:43:37:lut") } // x9375 = LUTNew(List(1024), Seq(Const(-0.9999997615814208984375),Const(-0.9999997615814208984375),Const(-0.999999701976776123046875)... [1021 more]))
    isAccum(x9375_d0_b0) = false
    bufferDepthOf(x9375_d0_b0) = 1
    staticDimsOf(x9375_d0_b0) = List(1024)
    val x9376 = withCtrl(x9385) { OpDef(op=FixSub, inputs=List(x9374, Const(-8.0))).name("x9376").srcCtx("NonLinearity.scala:44:22") } // FixSub(x9374,Const(-8))
    val x9377 = withCtrl(x9385) { OpDef(op=FixSla, inputs=List(x9376, Const(6))).name("x9377").srcCtx("NonLinearity.scala:44:30") } // FixLsh(x9376,Const(6))
    // x9378 = FixConvert(x9377,TRUE,_32,_0) x.tp=FixPt[TRUE,_8,_24] {
    val x9378 = withCtrl(x9385) { OpDef(op=FixSra, inputs=List(x9377, Const("24"))).name("x9378").srcCtx("NonLinearity.scala:44:41:index") } // FixConvert(x9377,TRUE,_32,_0)
    // }
    val x9379 = withCtrl(x9385) { LoadBanks(List(x9375_d0_b0), List(x9378)).name("x9379").srcCtx("NonLinearity.scala:45:17:tanhValMultAdd") } // LUTLoad(x9375,List(x9378),x9372)
    val x9380 = withCtrl(x9385) { OpDef(op=FixLt, inputs=List(Const(8.0), x9374)).name("x9380").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:126:36:isHigh") } // FixLt(Const(8),x9374)
    val x9381 = withCtrl(x9385) { OpDef(op=FixLt, inputs=List(x9374, Const(-8.0))).name("x9381").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:127:35:isLow") } // FixLt(x9374,Const(-8))
    val x9382 = withCtrl(x9385) { OpDef(op=MuxOp, inputs=List(x9381, Const(-1.0), x9379)).name("x9382").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:128:49") } // Mux(x9381,Const(-1),x9379)
    val x9383 = withCtrl(x9385) { OpDef(op=MuxOp, inputs=List(x9380, Const(1.0), x9382)).name("x9383").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:128:28:hLinear") } // Mux(x9380,Const(1),x9382)
    val x9384_x9366 = withCtrl(x9385) { WriteMem(x9366, x9383).name("x9384_x9366").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:130:23") } // ParFIFOEnq(x9366,List(x9383),List(x9372))
    val x9386 = withCtrl(x9418) { FIFO(size=16).name("x9386").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:134:31:hUpdateQ") } // x9386 = FIFONew(Const(16))
    isAccum(x9386) = false
    bufferDepthOf(x9386) = 1
    val x9387 = withCtrl(x9418) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x9387").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:135:43") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x9388 = withCtrl(x9418) { CounterChain(List(x9387)).name("x9388").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:135:57") } // CounterChainNew(List(x9387))
    val x9403 = withCtrl(x9418) { LoopController(style=InnerPipe, level=InnerControl, cchain=x9388).name("x9403").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:135:57") } // UnrolledForeach(List(b4685, b4060, b4056, b4052),x9388,Block(Const(())),List(List(b4794)),List(List(b4795)))
    val b4794 = withCtrl(x9403) { CounterIter(x9387, None).name("b4794") } // b4794
    val b4795 = withCtrl(x9403) { Const(true).name("b4795") } // b4795
    val x9389 = withCtrl(x9403) { OpDef(op=BitAnd, inputs=List(b4795, b4685)).name("x9389").srcCtx("UnrollingBase.scala:28:66") } // And(b4795,b4685)
    val x9390 = withCtrl(x9403) { OpDef(op=BitAnd, inputs=List(b4060, b4056)).name("x9390").srcCtx("UnrollingBase.scala:28:66") } // And(b4060,b4056)
    val x9391 = withCtrl(x9403) { OpDef(op=BitAnd, inputs=List(x9389, x9390)).name("x9391").srcCtx("UnrollingBase.scala:28:66") } // And(x9389,x9390)
    val x9392 = withCtrl(x9403) { OpDef(op=BitAnd, inputs=List(x9391, b4052)).name("x9392").srcCtx("UnrollingBase.scala:28:66") } // And(x9391,b4052)
    val x9393 = withCtrl(x9403) { ReadMem(x9366).name("x9393").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:136:37:hLinear") } // ParFIFODeq(x9366,List(x9392))
    val x9394 = withCtrl(x9403) { x9393 } // VectorApply(x9393,0)
    val x9395 = withCtrl(x9403) { ReadMem(x9290).name("x9395").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:137:33:sigEle") } // ParFIFODeq(x9290,List(x9392))
    val x9396 = withCtrl(x9403) { x9395 } // VectorApply(x9395,0)
    val x9397 = withCtrl(x9403) { LoadBanks(List(x8628_d0_b0), List(b4055, b4059, b4794)).name("x9397").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:139:24:hLast") } // ParSRAMLoad(x8628,List(List(b4055, b4059, b4794)),List(x9392))
    val x9398 = withCtrl(x9403) { x9397 } // VectorApply(x9397,0)
    val x9399 = withCtrl(x9403) { OpDef(op=FixMul, inputs=List(x9394, x9396)).name("x9399").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:140:30:hNew") } // FixMul(x9394,x9396)
    val x9400 = withCtrl(x9403) { OpDef(op=FixEql, inputs=List(b4684, Const(3))).name("x9400").srcCtx("package.scala:65:42") } // FixEql(b4684,Const(3))
    val x9401 = withCtrl(x9403) { OpDef(op=MuxOp, inputs=List(x9400, x9399, x9398)).name("x9401").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:141:28:hUpdate") } // Mux(x9400,x9399,x9398)
    val x9402_x9386 = withCtrl(x9403) { WriteMem(x9386, x9401).name("x9402_x9386").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:143:23") } // ParFIFOEnq(x9386,List(x9401),List(x9392))
    val x9404 = withCtrl(x9418) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x9404").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:148:43") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x9405 = withCtrl(x9418) { CounterChain(List(x9404)).name("x9405").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:148:57") } // CounterChainNew(List(x9404))
    val x9417 = withCtrl(x9418) { LoopController(style=InnerPipe, level=InnerControl, cchain=x9405).name("x9417").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:148:57") } // UnrolledForeach(List(b4685, b4060, b4056, b4052),x9405,Block(Const(())),List(List(b4813)),List(List(b4814)))
    val b4813 = withCtrl(x9417) { CounterIter(x9404, None).name("b4813") } // b4813
    val b4814 = withCtrl(x9417) { Const(true).name("b4814") } // b4814
    val x9406 = withCtrl(x9417) { OpDef(op=BitAnd, inputs=List(b4814, b4685)).name("x9406").srcCtx("UnrollingBase.scala:28:66") } // And(b4814,b4685)
    val x9407 = withCtrl(x9417) { OpDef(op=BitAnd, inputs=List(b4060, b4056)).name("x9407").srcCtx("UnrollingBase.scala:28:66") } // And(b4060,b4056)
    val x9408 = withCtrl(x9417) { OpDef(op=BitAnd, inputs=List(x9406, x9407)).name("x9408").srcCtx("UnrollingBase.scala:28:66") } // And(x9406,x9407)
    val x9409 = withCtrl(x9417) { OpDef(op=BitAnd, inputs=List(x9408, b4052)).name("x9409").srcCtx("UnrollingBase.scala:28:66") } // And(x9408,b4052)
    val x9410 = withCtrl(x9417) { ReadMem(x9386).name("x9410").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:149:34:hNew") } // ParFIFODeq(x9386,List(x9409))
    val x9411 = withCtrl(x9417) { x9410 } // VectorApply(x9410,0)
    val x9412 = withCtrl(x9417) { ReadMem(x9343).name("x9412").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:150:34:cNew") } // ParFIFODeq(x9343,List(x9409))
    val x9413 = withCtrl(x9417) { x9412 } // VectorApply(x9412,0)
    val x9414 = withCtrl(x9417) { StoreBanks(List(List(x8628_d0_b0), List(x8628_d1_b0), List(x8628_d2_b0), List(x8628_d3_b0), List(x8628_d4_b0)), List(b4055, b4059, b4813), x9411).name("x9414").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:151:43") } // ParSRAMStore(x8628,List(List(b4055, b4059, b4813)),List(x9411),List(x9409))
    val x9415 = withCtrl(x9417) { StoreBanks(List(List(x8626_d0_b0), List(x8626_d1_b0), List(x8626_d2_b0), List(x8626_d3_b0), List(x8626_d4_b0)), List(b4051, b4059, b4813), x9411).name("x9415").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:152:42") } // ParSRAMStore(x8626,List(List(b4051, b4059, b4813)),List(x9411),List(x9409))
    val x9416 = withCtrl(x9417) { StoreBanks(List(List(x8627_d0_b0)), List(b4055, b4059, b4813), x9413).name("x9416").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:153:43") } // ParSRAMStore(x8627,List(List(b4055, b4059, b4813)),List(x9413),List(x9409))
    val x9423 = withCtrl(x9458) { Counter(min=Const(0), max=Const(8), step=Const(1), par=1).name("x9423").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:201:70") } // CounterNew(Const(0),Const(8),Const(1),Const(1))
    val x9424 = withCtrl(x9458) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x9424").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:201:70") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x9425 = withCtrl(x9458) { CounterChain(List(x9423,x9424)).name("x9425").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:201:70") } // CounterChainNew(List(x9423, x9424))
    val x9457 = withCtrl(x9458) { LoopController(style=StreamPipe, level=OuterControl, cchain=x9425).name("x9457").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:201:70") } // UnrolledForeach(List(Const(true)),x9425,Block(Const(())),List(List(b4835), List(b4836)),List(List(b4837), List(b4838)))
    val b4835 = withCtrl(x9457) { CounterIter(x9423, Some(0)).name("b4835") } // b4835
    val b4837 = withCtrl(x9457) { Const(true).name("b4837") } // b4837
    val b4836 = withCtrl(x9457) { CounterIter(x9424, Some(0)).name("b4836") } // b4836
    val b4838 = withCtrl(x9457) { Const(true).name("b4838") } // b4838
    val b9497 = withCtrl(x9457) { StreamOut(field="offset").name("b9497").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:201:70") } // x9426 = StreamOutNew(BurstCmdBus)
    isAccum(b9497) = false
    bufferDepthOf(b9497) = 1
    val b9498 = withCtrl(x9457) { StreamOut(field="size").name("b9498").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:201:70") } // x9426 = StreamOutNew(BurstCmdBus)
    isAccum(b9498) = false
    bufferDepthOf(b9498) = 1
    val x9427 = withCtrl(x9457) { StreamOut(field="data").name("x9427").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:201:70") } // x9427 = StreamOutNew(BurstFullDataBus())
    isAccum(x9427) = false
    bufferDepthOf(x9427) = 1
    val x9428 = withCtrl(x9457) { StreamIn(field="ack").name("x9428").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:201:70") } // x9428 = StreamInNew(BurstAckBus)
    isAccum(x9428) = false
    bufferDepthOf(x9428) = 1
    val x9443 = withCtrl(x9457) { UnitController(style=SeqPipe, level=InnerControl).name("x9443").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:201:70") } // UnitPipe(List(b4837, b4838),Block(x9442))
    val x9429 = withCtrl(x9443) { b4835 } // FixConvert(b4835,TRUE,_32,_0) (Same Type. No op)
    val x9430 = withCtrl(x9443) { OpDef(op=FixSla, inputs=List(x9429, Const(8))).name("x9430").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:201:70") } // FixLsh(x9429,Const(8))
    val x9431 = withCtrl(x9443) { b4836 } // FixConvert(b4836,TRUE,_32,_0) (Same Type. No op)
    val x9432 = withCtrl(x9443) { OpDef(op=FixSla, inputs=List(x9431, Const(7))).name("x9432").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:201:70") } // FixLsh(x9431,Const(7))
    val x9433 = withCtrl(x9443) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x9434 = withCtrl(x9443) { OpDef(op=FixAdd, inputs=List(x9430, x9432)).name("x9434").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:201:70") } // FixAdd(x9430,x9432)
    val x9435 = withCtrl(x9443) { OpDef(op=FixAdd, inputs=List(x9434, x9433)).name("x9435").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:201:70") } // FixAdd(x9434,x9433)
    val x9436 = withCtrl(x9443) { OpDef(op=FixSla, inputs=List(x9435, Const(2))).name("x9436").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:201:70") } // FixLsh(x9435,Const(2))
    val x9437 = withCtrl(x9443) { x9436 } // FixConvert(x9436,TRUE,_64,_0)
    val x9438 = withCtrl(x9443) { DramAddress(x8556).name("x9438").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:201:70") } // GetDRAMAddress(x8556)
    val x9440_x9439 = withCtrl(x9443) { OpDef(op=FixAdd, inputs=List(x9437, x9438)).name("x9440_x9439").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:201:70") } // FixAdd(x9437,x9438)
    // x9440 = SimpleStruct(ArrayBuffer((offset,x9439), (size,Const(512)), (isLoad,Const(false))))
    val x9441 = withCtrl(x9443) { OpDef(op=BitAnd, inputs=List(b4837, b4838)).name("x9441").srcCtx("UnrollingBase.scala:28:66") } // And(b4837,b4838)
    val x9442_b9499_b9497 = withCtrl(x9443) { WriteMem(b9497, x9440_x9439).name("x9442_b9499_b9497").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:201:70") } // StreamWrite(x9426,x9440,x9441)
    val x9442_b9500_b9498 = withCtrl(x9443) { WriteMem(b9498, Const(512)).name("x9442_b9500_b9498").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:201:70") } // StreamWrite(x9426,x9440,x9441)
    val x9444 = withCtrl(x9457) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x9444").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:201:70") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x9445 = withCtrl(x9457) { CounterChain(List(x9444)).name("x9445").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:201:70") } // CounterChainNew(List(x9444))
    val x9452 = withCtrl(x9457) { LoopController(style=InnerPipe, level=InnerControl, cchain=x9445).name("x9452").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:201:70") } // UnrolledForeach(List(b4837, b4838),x9445,Block(Const(())),List(List(b4859)),List(List(b4860)))
    val b4859 = withCtrl(x9452) { CounterIter(x9444, None).name("b4859") } // b4859
    val b4860 = withCtrl(x9452) { Const(true).name("b4860") } // b4860
    val x9446 = withCtrl(x9452) { OpDef(op=BitAnd, inputs=List(b4860, b4837)).name("x9446").srcCtx("UnrollingBase.scala:28:66") } // And(b4860,b4837)
    val x9447 = withCtrl(x9452) { OpDef(op=BitAnd, inputs=List(x9446, b4838)).name("x9447").srcCtx("UnrollingBase.scala:28:66") } // And(x9446,b4838)
    val x9448 = withCtrl(x9452) { LoadBanks(List(x8626_d0_b0), List(b4835, b4836, b4859)).name("x9448").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:201:70") } // ParSRAMLoad(x8626,List(List(b4835, b4836, b4859)),List(x9447))
    val x9450_x9449 = withCtrl(x9452) { x9448 } // VectorApply(x9448,0)
    // x9450 = SimpleStruct(ArrayBuffer((_1,x9449), (_2,Const(true))))
    val x9451_x9451_x9427 = withCtrl(x9452) { WriteMem(x9427, x9450_x9449).name("x9451_x9451_x9427").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:201:70") } // ParStreamWrite(x9427,List(x9450),List(x9447))
    val x9453 = withCtrl(x9457) { FringeDenseStore(dram=List(x8556), cmdStream=List(b9497, b9498), dataStream=List(x9427), ackStream=List(x9428)).name("x9453").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:201:70") } // FringeDenseStore(x8556,x9426,x9427,x9428)
    val x9456 = withCtrl(x9457) { UnitController(style=SeqPipe, level=InnerControl).name("x9456").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:201:70") } // UnitPipe(List(b4837, b4838),Block(Const(())))
    val x9454 = withCtrl(x9456) { OpDef(op=BitAnd, inputs=List(b4837, b4838)).name("x9454").srcCtx("UnrollingBase.scala:28:66") } // And(b4837,b4838)
    val x9455_x9455 = withCtrl(x9456) { ReadMem(x9428).name("x9455_x9455").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:201:70") } // StreamRead(x9428,x9454)
    }; split2
    }; split1
    
  }
}
