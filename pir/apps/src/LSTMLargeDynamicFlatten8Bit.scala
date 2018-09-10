import pir._
import pir.node._
import arch._
import prism.enums._

object LSTMLargeDynamicFlatten8Bit extends PIRApp {
  def main(implicit design:PIRDesign) = {
    import design.pirmeta._
    val x7581 = withCtrl(design.top.topController) { DRAM(dims=List(Const(8), Const(1024))).name("x7581").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:151:33:dout") } // x7581 = DRAMNew(ArrayBuffer(Const(8), Const(1024)),Const(0))
    val x7582 = withCtrl(design.top.topController) { DRAM(dims=List(Const(8), Const(1024))).name("x7582").srcCtx("DataGenerator.scala:156:20:xInit") } // x7582 = DRAMNew(ArrayBuffer(Const(8), Const(1024)),Const(0))
    val x7589 = withCtrl(design.top.topController) { DRAM(dims=List(Const(8), Const(1024))).name("x7589").srcCtx("DataGenerator.scala:156:20:cInit") } // x7589 = DRAMNew(ArrayBuffer(Const(8), Const(1024)),Const(0))
    val x7596 = withCtrl(design.top.topController) { DRAM(dims=List(Const(8), Const(1024))).name("x7596").srcCtx("DataGenerator.scala:156:20:hInit") } // x7596 = DRAMNew(ArrayBuffer(Const(8), Const(1024)),Const(0))
    val x7603 = withCtrl(design.top.topController) { DRAM(dims=List(Const(2), Const(4), Const(1024))).name("x7603").srcCtx("DataGenerator.scala:168:20:bInit") } // x7603 = DRAMNew(ArrayBuffer(Const(2), Const(4), Const(1024)),Const(0))
    val x7613 = withCtrl(design.top.topController) { DRAM(dims=List(Const(2), Const(1024), Const(4), Const(1024))).name("x7613").srcCtx("DataGenerator.scala:182:20:wxInit") } // x7613 = DRAMNew(ArrayBuffer(Const(2), Const(1024), Const(4), Const(1024)),Const(0))
    val x7626 = withCtrl(design.top.topController) { DRAM(dims=List(Const(2), Const(1024), Const(4), Const(1024))).name("x7626").srcCtx("DataGenerator.scala:182:20:whInit") } // x7626 = DRAMNew(ArrayBuffer(Const(2), Const(1024), Const(4), Const(1024)),Const(0))
    val x8384 = withCtrl(design.top.topController) { UnitController(style=SeqPipe, level=OuterControl).name("x8384").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:162:11") } // Hwblock(Block(Const(())),false)
    val x7639_d0_b0 = withCtrl(x8384) { SRAM(size=8192, banking=Strided(banks=64, stride=1)).name("x7639_d0_b0").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:163:32:x") } // x7639 = SRAMNew(ArrayBuffer(Const(8), Const(1024)))
    isAccum(x7639_d0_b0) = false
    bufferDepthOf(x7639_d0_b0) = 1
    staticDimsOf(x7639_d0_b0) = List(8, 1024)
    val x7639_d1_b0 = withCtrl(x8384) { SRAM(size=8192, banking=Strided(banks=64, stride=1)).name("x7639_d1_b0").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:163:32:x") } // x7639 = SRAMNew(ArrayBuffer(Const(8), Const(1024)))
    isAccum(x7639_d1_b0) = false
    bufferDepthOf(x7639_d1_b0) = 3
    staticDimsOf(x7639_d1_b0) = List(8, 1024)
    val x7640_d0_b0 = withCtrl(x8384) { SRAM(size=2048, banking=Strided(banks=64, stride=1)).name("x7640_d0_b0").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:164:32:c") } // x7640 = SRAMNew(ArrayBuffer(Const(2), Const(1024)))
    isAccum(x7640_d0_b0) = false
    bufferDepthOf(x7640_d0_b0) = 1
    staticDimsOf(x7640_d0_b0) = List(2, 1024)
    val x7641_d0_b0 = withCtrl(x8384) { SRAM(size=2048, banking=Strided(banks=64, stride=1)).name("x7641_d0_b0").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:165:32:h") } // x7641 = SRAMNew(ArrayBuffer(Const(2), Const(1024)))
    isAccum(x7641_d0_b0) = false
    bufferDepthOf(x7641_d0_b0) = 1
    staticDimsOf(x7641_d0_b0) = List(2, 1024)
    val x7641_d1_b0 = withCtrl(x8384) { SRAM(size=2048, banking=Strided(banks=64, stride=1)).name("x7641_d1_b0").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:165:32:h") } // x7641 = SRAMNew(ArrayBuffer(Const(2), Const(1024)))
    isAccum(x7641_d1_b0) = false
    bufferDepthOf(x7641_d1_b0) = 3
    staticDimsOf(x7641_d1_b0) = List(2, 1024)
    val x7642_d0_b0 = withCtrl(x8384) { SRAM(size=8192, banking=Strided(banks=64, stride=1)).name("x7642_d0_b0").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:166:32:b") } // x7642 = SRAMNew(ArrayBuffer(Const(2), Const(4), Const(1024)))
    isAccum(x7642_d0_b0) = false
    bufferDepthOf(x7642_d0_b0) = 1
    staticDimsOf(x7642_d0_b0) = List(2, 4, 1024)
    val x7643 = withCtrl(x8384) { Counter(min=Const(0), max=Const(8), step=Const(1), par=1).name("x7643").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:168:9") } // CounterNew(Const(0),Const(8),Const(1),Const(1))
    val x7644 = withCtrl(x8384) { CounterChain(List(x7643)).name("x7644").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:168:9") } // CounterChainNew(List(x7643))
    val x7666 = withCtrl(x8384) { LoopController(style=StreamPipe, level=OuterControl, cchain=x7644).name("x7666").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:168:9") } // UnrolledForeach(List(Const(true)),x7644,Block(Const(())),List(List(b3439)),List(List(b3440)))
    val b3439 = withCtrl(x7666) { CounterIter(x7643, Some(0)).name("b3439") } // b3439
    val b3440 = withCtrl(x7666) { Const(true).name("b3440") } // b3440
    val b8395 = withCtrl(x7666) { StreamOut(field="offset").name("b8395").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:168:9") } // x7645 = StreamOutNew(BurstCmdBus)
    isAccum(b8395) = false
    bufferDepthOf(b8395) = 1
    val b8396 = withCtrl(x7666) { StreamOut(field="size").name("b8396").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:168:9") } // x7645 = StreamOutNew(BurstCmdBus)
    isAccum(b8396) = false
    bufferDepthOf(b8396) = 1
    val x7646 = withCtrl(x7666) { StreamIn(field="data").name("x7646").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:168:9") } // x7646 = StreamInNew(BurstDataBus())
    isAccum(x7646) = false
    bufferDepthOf(x7646) = 1
    val x7657 = withCtrl(x7666) { UnitController(style=SeqPipe, level=InnerControl).name("x7657").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:168:9") } // UnitPipe(List(b3440),Block(x7656))
    val x7647 = withCtrl(x7657) { b3439 } // FixConvert(b3439,TRUE,_32,_0) (Same Type. No op)
    val x7648 = withCtrl(x7657) { OpDef(op=FixSla, inputs=List(x7647, Const(10))).name("x7648").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:168:9") } // FixLsh(x7647,Const(10))
    val x7649 = withCtrl(x7657) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x7650 = withCtrl(x7657) { OpDef(op=FixAdd, inputs=List(x7648, x7649)).name("x7650").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:168:9") } // FixAdd(x7648,x7649)
    val x7651 = withCtrl(x7657) { OpDef(op=FixSla, inputs=List(x7650, Const(2))).name("x7651").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:168:9") } // FixLsh(x7650,Const(2))
    val x7652 = withCtrl(x7657) { x7651 } // FixConvert(x7651,TRUE,_64,_0)
    val x7653 = withCtrl(x7657) { DramAddress(x7582).name("x7653").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:168:9") } // GetDRAMAddress(x7582)
    val x7655_x7654 = withCtrl(x7657) { OpDef(op=FixAdd, inputs=List(x7652, x7653)).name("x7655_x7654").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:168:9") } // FixAdd(x7652,x7653)
    // x7655 = SimpleStruct(ArrayBuffer((offset,x7654), (size,Const(4096)), (isLoad,Const(true))))
    val x7656_b8397_b8395 = withCtrl(x7657) { WriteMem(b8395, x7655_x7654).name("x7656_b8397_b8395").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:168:9") } // StreamWrite(x7645,x7655,b3440)
    val x7656_b8398_b8396 = withCtrl(x7657) { WriteMem(b8396, Const(4096)).name("x7656_b8398_b8396").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:168:9") } // StreamWrite(x7645,x7655,b3440)
    val x7658 = withCtrl(x7666) { FringeDenseLoad(dram=List(x7582), cmdStream=List(b8395, b8396), dataStream=List(x7646)).name("x7658").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:168:9") } // FringeDenseLoad(x7582,x7645,x7646)
    val x7659 = withCtrl(x7666) { Counter(min=Const(0), max=Const(1024), step=Const(1), par=64).name("x7659").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:168:9") } // CounterNew(Const(0),Const(1024),Const(1),Const(64))
    val x7660 = withCtrl(x7666) { CounterChain(List(x7659)).name("x7660").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:168:9") } // CounterChainNew(List(x7659))
    val x7665 = withCtrl(x7666) { LoopController(style=InnerPipe, level=InnerControl, cchain=x7660).name("x7665").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:168:9") } // UnrolledForeach(List(b3440),x7660,Block(Const(())),List(List(b3457)),List(List(b3458)))
    val b3457 = withCtrl(x7665) { CounterIter(x7659, None).name("b3457") } // b3457
    val b3458 = withCtrl(x7665) { Const(true).name("b3458") } // b3458
    val x7661 = withCtrl(x7665) { OpDef(op=BitAnd, inputs=List(b3458, b3440)).name("x7661").srcCtx("UnrollingBase.scala:28:66") } // And(b3458,b3440)
    val x7662_x7662 = withCtrl(x7665) { ReadMem(x7646).name("x7662_x7662").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:168:9") } // ParStreamRead(x7646,List(x7661))
    val x7663_x7663 = withCtrl(x7665) { x7662_x7662 } // VectorApply(x7662,0)
    val x7664 = withCtrl(x7665) { StoreBanks(List(List(x7639_d0_b0), List(x7639_d1_b0)), List(b3439, b3457), x7663_x7663).name("x7664").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:168:9") } // ParSRAMStore(x7639,List(List(b3439, b3457)),List(x7663),List(x7661))
    val x7667 = withCtrl(x8384) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x7667").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:169:9") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x7668 = withCtrl(x8384) { CounterChain(List(x7667)).name("x7668").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:169:9") } // CounterChainNew(List(x7667))
    val x7690 = withCtrl(x8384) { LoopController(style=StreamPipe, level=OuterControl, cchain=x7668).name("x7690").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:169:9") } // UnrolledForeach(List(Const(true)),x7668,Block(Const(())),List(List(b3467)),List(List(b3468)))
    val b3467 = withCtrl(x7690) { CounterIter(x7667, Some(0)).name("b3467") } // b3467
    val b3468 = withCtrl(x7690) { Const(true).name("b3468") } // b3468
    val b8399 = withCtrl(x7690) { StreamOut(field="offset").name("b8399").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:169:9") } // x7669 = StreamOutNew(BurstCmdBus)
    isAccum(b8399) = false
    bufferDepthOf(b8399) = 1
    val b8400 = withCtrl(x7690) { StreamOut(field="size").name("b8400").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:169:9") } // x7669 = StreamOutNew(BurstCmdBus)
    isAccum(b8400) = false
    bufferDepthOf(b8400) = 1
    val x7670 = withCtrl(x7690) { StreamIn(field="data").name("x7670").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:169:9") } // x7670 = StreamInNew(BurstDataBus())
    isAccum(x7670) = false
    bufferDepthOf(x7670) = 1
    val x7681 = withCtrl(x7690) { UnitController(style=SeqPipe, level=InnerControl).name("x7681").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:169:9") } // UnitPipe(List(b3468),Block(x7680))
    val x7671 = withCtrl(x7681) { b3467 } // FixConvert(b3467,TRUE,_32,_0) (Same Type. No op)
    val x7672 = withCtrl(x7681) { OpDef(op=FixSla, inputs=List(x7671, Const(10))).name("x7672").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:169:9") } // FixLsh(x7671,Const(10))
    val x7673 = withCtrl(x7681) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x7674 = withCtrl(x7681) { OpDef(op=FixAdd, inputs=List(x7672, x7673)).name("x7674").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:169:9") } // FixAdd(x7672,x7673)
    val x7675 = withCtrl(x7681) { OpDef(op=FixSla, inputs=List(x7674, Const(2))).name("x7675").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:169:9") } // FixLsh(x7674,Const(2))
    val x7676 = withCtrl(x7681) { x7675 } // FixConvert(x7675,TRUE,_64,_0)
    val x7677 = withCtrl(x7681) { DramAddress(x7589).name("x7677").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:169:9") } // GetDRAMAddress(x7589)
    val x7679_x7678 = withCtrl(x7681) { OpDef(op=FixAdd, inputs=List(x7676, x7677)).name("x7679_x7678").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:169:9") } // FixAdd(x7676,x7677)
    // x7679 = SimpleStruct(ArrayBuffer((offset,x7678), (size,Const(4096)), (isLoad,Const(true))))
    val x7680_b8401_b8399 = withCtrl(x7681) { WriteMem(b8399, x7679_x7678).name("x7680_b8401_b8399").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:169:9") } // StreamWrite(x7669,x7679,b3468)
    val x7680_b8402_b8400 = withCtrl(x7681) { WriteMem(b8400, Const(4096)).name("x7680_b8402_b8400").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:169:9") } // StreamWrite(x7669,x7679,b3468)
    val x7682 = withCtrl(x7690) { FringeDenseLoad(dram=List(x7589), cmdStream=List(b8399, b8400), dataStream=List(x7670)).name("x7682").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:169:9") } // FringeDenseLoad(x7589,x7669,x7670)
    val x7683 = withCtrl(x7690) { Counter(min=Const(0), max=Const(1024), step=Const(1), par=64).name("x7683").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:169:9") } // CounterNew(Const(0),Const(1024),Const(1),Const(64))
    val x7684 = withCtrl(x7690) { CounterChain(List(x7683)).name("x7684").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:169:9") } // CounterChainNew(List(x7683))
    val x7689 = withCtrl(x7690) { LoopController(style=InnerPipe, level=InnerControl, cchain=x7684).name("x7689").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:169:9") } // UnrolledForeach(List(b3468),x7684,Block(Const(())),List(List(b3485)),List(List(b3486)))
    val b3485 = withCtrl(x7689) { CounterIter(x7683, None).name("b3485") } // b3485
    val b3486 = withCtrl(x7689) { Const(true).name("b3486") } // b3486
    val x7685 = withCtrl(x7689) { OpDef(op=BitAnd, inputs=List(b3486, b3468)).name("x7685").srcCtx("UnrollingBase.scala:28:66") } // And(b3486,b3468)
    val x7686_x7686 = withCtrl(x7689) { ReadMem(x7670).name("x7686_x7686").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:169:9") } // ParStreamRead(x7670,List(x7685))
    val x7687_x7687 = withCtrl(x7689) { x7686_x7686 } // VectorApply(x7686,0)
    val x7688 = withCtrl(x7689) { StoreBanks(List(List(x7640_d0_b0)), List(b3467, b3485), x7687_x7687).name("x7688").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:169:9") } // ParSRAMStore(x7640,List(List(b3467, b3485)),List(x7687),List(x7685))
    val x7691 = withCtrl(x8384) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x7691").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:170:9") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x7692 = withCtrl(x8384) { CounterChain(List(x7691)).name("x7692").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:170:9") } // CounterChainNew(List(x7691))
    val x7714 = withCtrl(x8384) { LoopController(style=StreamPipe, level=OuterControl, cchain=x7692).name("x7714").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:170:9") } // UnrolledForeach(List(Const(true)),x7692,Block(Const(())),List(List(b3495)),List(List(b3496)))
    val b3495 = withCtrl(x7714) { CounterIter(x7691, Some(0)).name("b3495") } // b3495
    val b3496 = withCtrl(x7714) { Const(true).name("b3496") } // b3496
    val b8403 = withCtrl(x7714) { StreamOut(field="offset").name("b8403").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:170:9") } // x7693 = StreamOutNew(BurstCmdBus)
    isAccum(b8403) = false
    bufferDepthOf(b8403) = 1
    val b8404 = withCtrl(x7714) { StreamOut(field="size").name("b8404").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:170:9") } // x7693 = StreamOutNew(BurstCmdBus)
    isAccum(b8404) = false
    bufferDepthOf(b8404) = 1
    val x7694 = withCtrl(x7714) { StreamIn(field="data").name("x7694").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:170:9") } // x7694 = StreamInNew(BurstDataBus())
    isAccum(x7694) = false
    bufferDepthOf(x7694) = 1
    val x7705 = withCtrl(x7714) { UnitController(style=SeqPipe, level=InnerControl).name("x7705").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:170:9") } // UnitPipe(List(b3496),Block(x7704))
    val x7695 = withCtrl(x7705) { b3495 } // FixConvert(b3495,TRUE,_32,_0) (Same Type. No op)
    val x7696 = withCtrl(x7705) { OpDef(op=FixSla, inputs=List(x7695, Const(10))).name("x7696").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:170:9") } // FixLsh(x7695,Const(10))
    val x7697 = withCtrl(x7705) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x7698 = withCtrl(x7705) { OpDef(op=FixAdd, inputs=List(x7696, x7697)).name("x7698").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:170:9") } // FixAdd(x7696,x7697)
    val x7699 = withCtrl(x7705) { OpDef(op=FixSla, inputs=List(x7698, Const(2))).name("x7699").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:170:9") } // FixLsh(x7698,Const(2))
    val x7700 = withCtrl(x7705) { x7699 } // FixConvert(x7699,TRUE,_64,_0)
    val x7701 = withCtrl(x7705) { DramAddress(x7596).name("x7701").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:170:9") } // GetDRAMAddress(x7596)
    val x7703_x7702 = withCtrl(x7705) { OpDef(op=FixAdd, inputs=List(x7700, x7701)).name("x7703_x7702").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:170:9") } // FixAdd(x7700,x7701)
    // x7703 = SimpleStruct(ArrayBuffer((offset,x7702), (size,Const(4096)), (isLoad,Const(true))))
    val x7704_b8405_b8403 = withCtrl(x7705) { WriteMem(b8403, x7703_x7702).name("x7704_b8405_b8403").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:170:9") } // StreamWrite(x7693,x7703,b3496)
    val x7704_b8406_b8404 = withCtrl(x7705) { WriteMem(b8404, Const(4096)).name("x7704_b8406_b8404").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:170:9") } // StreamWrite(x7693,x7703,b3496)
    val x7706 = withCtrl(x7714) { FringeDenseLoad(dram=List(x7596), cmdStream=List(b8403, b8404), dataStream=List(x7694)).name("x7706").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:170:9") } // FringeDenseLoad(x7596,x7693,x7694)
    val x7707 = withCtrl(x7714) { Counter(min=Const(0), max=Const(1024), step=Const(1), par=64).name("x7707").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:170:9") } // CounterNew(Const(0),Const(1024),Const(1),Const(64))
    val x7708 = withCtrl(x7714) { CounterChain(List(x7707)).name("x7708").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:170:9") } // CounterChainNew(List(x7707))
    val x7713 = withCtrl(x7714) { LoopController(style=InnerPipe, level=InnerControl, cchain=x7708).name("x7713").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:170:9") } // UnrolledForeach(List(b3496),x7708,Block(Const(())),List(List(b3513)),List(List(b3514)))
    val b3513 = withCtrl(x7713) { CounterIter(x7707, None).name("b3513") } // b3513
    val b3514 = withCtrl(x7713) { Const(true).name("b3514") } // b3514
    val x7709 = withCtrl(x7713) { OpDef(op=BitAnd, inputs=List(b3514, b3496)).name("x7709").srcCtx("UnrollingBase.scala:28:66") } // And(b3514,b3496)
    val x7710_x7710 = withCtrl(x7713) { ReadMem(x7694).name("x7710_x7710").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:170:9") } // ParStreamRead(x7694,List(x7709))
    val x7711_x7711 = withCtrl(x7713) { x7710_x7710 } // VectorApply(x7710,0)
    val x7712 = withCtrl(x7713) { StoreBanks(List(List(x7641_d0_b0), List(x7641_d1_b0)), List(b3495, b3513), x7711_x7711).name("x7712").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:170:9") } // ParSRAMStore(x7641,List(List(b3495, b3513)),List(x7711),List(x7709))
    val x7715 = withCtrl(x8384) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x7715").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:171:9") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x7716 = withCtrl(x8384) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x7716").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:171:9") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x7717 = withCtrl(x8384) { CounterChain(List(x7715,x7716)).name("x7717").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:171:9") } // CounterChainNew(List(x7715, x7716))
    val x7744 = withCtrl(x8384) { LoopController(style=StreamPipe, level=OuterControl, cchain=x7717).name("x7744").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:171:9") } // UnrolledForeach(List(Const(true)),x7717,Block(Const(())),List(List(b3524), List(b3525)),List(List(b3526), List(b3527)))
    val b3524 = withCtrl(x7744) { CounterIter(x7715, Some(0)).name("b3524") } // b3524
    val b3526 = withCtrl(x7744) { Const(true).name("b3526") } // b3526
    val b3525 = withCtrl(x7744) { CounterIter(x7716, Some(0)).name("b3525") } // b3525
    val b3527 = withCtrl(x7744) { Const(true).name("b3527") } // b3527
    val b8407 = withCtrl(x7744) { StreamOut(field="offset").name("b8407").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:171:9") } // x7718 = StreamOutNew(BurstCmdBus)
    isAccum(b8407) = false
    bufferDepthOf(b8407) = 1
    val b8408 = withCtrl(x7744) { StreamOut(field="size").name("b8408").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:171:9") } // x7718 = StreamOutNew(BurstCmdBus)
    isAccum(b8408) = false
    bufferDepthOf(b8408) = 1
    val x7719 = withCtrl(x7744) { StreamIn(field="data").name("x7719").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:171:9") } // x7719 = StreamInNew(BurstDataBus())
    isAccum(x7719) = false
    bufferDepthOf(x7719) = 1
    val x7734 = withCtrl(x7744) { UnitController(style=SeqPipe, level=InnerControl).name("x7734").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:171:9") } // UnitPipe(List(b3526, b3527),Block(x7733))
    val x7720 = withCtrl(x7734) { b3524 } // FixConvert(b3524,TRUE,_32,_0) (Same Type. No op)
    val x7721 = withCtrl(x7734) { OpDef(op=FixSla, inputs=List(x7720, Const(12))).name("x7721").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:171:9") } // FixLsh(x7720,Const(12))
    val x7722 = withCtrl(x7734) { b3525 } // FixConvert(b3525,TRUE,_32,_0) (Same Type. No op)
    val x7723 = withCtrl(x7734) { OpDef(op=FixSla, inputs=List(x7722, Const(10))).name("x7723").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:171:9") } // FixLsh(x7722,Const(10))
    val x7724 = withCtrl(x7734) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x7725 = withCtrl(x7734) { OpDef(op=FixAdd, inputs=List(x7721, x7723)).name("x7725").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:171:9") } // FixAdd(x7721,x7723)
    val x7726 = withCtrl(x7734) { OpDef(op=FixAdd, inputs=List(x7725, x7724)).name("x7726").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:171:9") } // FixAdd(x7725,x7724)
    val x7727 = withCtrl(x7734) { OpDef(op=FixSla, inputs=List(x7726, Const(2))).name("x7727").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:171:9") } // FixLsh(x7726,Const(2))
    val x7728 = withCtrl(x7734) { x7727 } // FixConvert(x7727,TRUE,_64,_0)
    val x7729 = withCtrl(x7734) { DramAddress(x7603).name("x7729").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:171:9") } // GetDRAMAddress(x7603)
    val x7731_x7730 = withCtrl(x7734) { OpDef(op=FixAdd, inputs=List(x7728, x7729)).name("x7731_x7730").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:171:9") } // FixAdd(x7728,x7729)
    // x7731 = SimpleStruct(ArrayBuffer((offset,x7730), (size,Const(4096)), (isLoad,Const(true))))
    val x7732 = withCtrl(x7734) { OpDef(op=BitAnd, inputs=List(b3526, b3527)).name("x7732").srcCtx("UnrollingBase.scala:28:66") } // And(b3526,b3527)
    val x7733_b8409_b8407 = withCtrl(x7734) { WriteMem(b8407, x7731_x7730).name("x7733_b8409_b8407").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:171:9") } // StreamWrite(x7718,x7731,x7732)
    val x7733_b8410_b8408 = withCtrl(x7734) { WriteMem(b8408, Const(4096)).name("x7733_b8410_b8408").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:171:9") } // StreamWrite(x7718,x7731,x7732)
    val x7735 = withCtrl(x7744) { FringeDenseLoad(dram=List(x7603), cmdStream=List(b8407, b8408), dataStream=List(x7719)).name("x7735").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:171:9") } // FringeDenseLoad(x7603,x7718,x7719)
    val x7736 = withCtrl(x7744) { Counter(min=Const(0), max=Const(1024), step=Const(1), par=64).name("x7736").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:171:9") } // CounterNew(Const(0),Const(1024),Const(1),Const(64))
    val x7737 = withCtrl(x7744) { CounterChain(List(x7736)).name("x7737").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:171:9") } // CounterChainNew(List(x7736))
    val x7743 = withCtrl(x7744) { LoopController(style=InnerPipe, level=InnerControl, cchain=x7737).name("x7743").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:171:9") } // UnrolledForeach(List(b3526, b3527),x7737,Block(Const(())),List(List(b3548)),List(List(b3549)))
    val b3548 = withCtrl(x7743) { CounterIter(x7736, None).name("b3548") } // b3548
    val b3549 = withCtrl(x7743) { Const(true).name("b3549") } // b3549
    val x7738 = withCtrl(x7743) { OpDef(op=BitAnd, inputs=List(b3549, b3526)).name("x7738").srcCtx("UnrollingBase.scala:28:66") } // And(b3549,b3526)
    val x7739 = withCtrl(x7743) { OpDef(op=BitAnd, inputs=List(x7738, b3527)).name("x7739").srcCtx("UnrollingBase.scala:28:66") } // And(x7738,b3527)
    val x7740_x7740 = withCtrl(x7743) { ReadMem(x7719).name("x7740_x7740").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:171:9") } // ParStreamRead(x7719,List(x7739))
    val x7741_x7741 = withCtrl(x7743) { x7740_x7740 } // VectorApply(x7740,0)
    val x7742 = withCtrl(x7743) { StoreBanks(List(List(x7642_d0_b0)), List(b3524, b3525, b3548), x7741_x7741).name("x7742").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:171:9") } // ParSRAMStore(x7642,List(List(b3524, b3525, b3548)),List(x7741),List(x7739))
    val x7745 = withCtrl(x8384) { Counter(min=Const(0), max=Const(8), step=Const(1), par=1).name("x7745").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:173:23") } // CounterNew(Const(0),Const(8),Const(1),Const(1))
    val x7746 = withCtrl(x8384) { CounterChain(List(x7745)).name("x7746").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:173:29") } // CounterChainNew(List(x7745))
    val x8355 = withCtrl(x8384) { LoopController(style=MetaPipe, level=OuterControl, cchain=x7746).name("x8355").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:173:29") } // UnrolledForeach(List(Const(true)),x7746,Block(Const(())),List(List(b3559)),List(List(b3560)))
    val b3559 = withCtrl(x8355) { CounterIter(x7745, Some(0)).name("b3559") } // b3559
    val b3560 = withCtrl(x8355) { Const(true).name("b3560") } // b3560
    val x7747 = withCtrl(x8355) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x7747").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:174:26") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x7748 = withCtrl(x8355) { CounterChain(List(x7747)).name("x7748").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:174:32") } // CounterChainNew(List(x7747))
    val x8354 = withCtrl(x8355) { LoopController(style=MetaPipe, level=OuterControl, cchain=x7748).name("x8354").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:174:32") } // UnrolledForeach(List(b3560),x7748,Block(Const(())),List(List(b3563)),List(List(b3564)))
    val b3563 = withCtrl(x8354) { CounterIter(x7747, Some(0)).name("b3563") } // b3563
    val b3564 = withCtrl(x8354) { Const(true).name("b3564") } // b3564
    val x7749_d0_b0 = withCtrl(x8354) { SRAM(size=262144, banking=Strided(banks=64, stride=1)).name("x7749_d0_b0").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:176:37:wx") } // x7749 = SRAMNew(ArrayBuffer(Const(1024), Const(4), Const(1024)))
    isAccum(x7749_d0_b0) = false
    bufferDepthOf(x7749_d0_b0) = 3
    staticDimsOf(x7749_d0_b0) = List(1024, 4, 1024)
    val x7749_d0_b1 = withCtrl(x8354) { SRAM(size=262144, banking=Strided(banks=64, stride=1)).name("x7749_d0_b1").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:176:37:wx") } // x7749 = SRAMNew(ArrayBuffer(Const(1024), Const(4), Const(1024)))
    isAccum(x7749_d0_b1) = false
    bufferDepthOf(x7749_d0_b1) = 3
    staticDimsOf(x7749_d0_b1) = List(1024, 4, 1024)
    val x7749_d0_b2 = withCtrl(x8354) { SRAM(size=262144, banking=Strided(banks=64, stride=1)).name("x7749_d0_b2").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:176:37:wx") } // x7749 = SRAMNew(ArrayBuffer(Const(1024), Const(4), Const(1024)))
    isAccum(x7749_d0_b2) = false
    bufferDepthOf(x7749_d0_b2) = 3
    staticDimsOf(x7749_d0_b2) = List(1024, 4, 1024)
    val x7749_d0_b3 = withCtrl(x8354) { SRAM(size=262144, banking=Strided(banks=64, stride=1)).name("x7749_d0_b3").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:176:37:wx") } // x7749 = SRAMNew(ArrayBuffer(Const(1024), Const(4), Const(1024)))
    isAccum(x7749_d0_b3) = false
    bufferDepthOf(x7749_d0_b3) = 3
    staticDimsOf(x7749_d0_b3) = List(1024, 4, 1024)
    val x7749_d0_b4 = withCtrl(x8354) { SRAM(size=262144, banking=Strided(banks=64, stride=1)).name("x7749_d0_b4").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:176:37:wx") } // x7749 = SRAMNew(ArrayBuffer(Const(1024), Const(4), Const(1024)))
    isAccum(x7749_d0_b4) = false
    bufferDepthOf(x7749_d0_b4) = 3
    staticDimsOf(x7749_d0_b4) = List(1024, 4, 1024)
    val x7749_d0_b5 = withCtrl(x8354) { SRAM(size=262144, banking=Strided(banks=64, stride=1)).name("x7749_d0_b5").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:176:37:wx") } // x7749 = SRAMNew(ArrayBuffer(Const(1024), Const(4), Const(1024)))
    isAccum(x7749_d0_b5) = false
    bufferDepthOf(x7749_d0_b5) = 3
    staticDimsOf(x7749_d0_b5) = List(1024, 4, 1024)
    val x7749_d0_b6 = withCtrl(x8354) { SRAM(size=262144, banking=Strided(banks=64, stride=1)).name("x7749_d0_b6").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:176:37:wx") } // x7749 = SRAMNew(ArrayBuffer(Const(1024), Const(4), Const(1024)))
    isAccum(x7749_d0_b6) = false
    bufferDepthOf(x7749_d0_b6) = 3
    staticDimsOf(x7749_d0_b6) = List(1024, 4, 1024)
    val x7749_d0_b7 = withCtrl(x8354) { SRAM(size=262144, banking=Strided(banks=64, stride=1)).name("x7749_d0_b7").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:176:37:wx") } // x7749 = SRAMNew(ArrayBuffer(Const(1024), Const(4), Const(1024)))
    isAccum(x7749_d0_b7) = false
    bufferDepthOf(x7749_d0_b7) = 3
    staticDimsOf(x7749_d0_b7) = List(1024, 4, 1024)
    val x7749_d0_b8 = withCtrl(x8354) { SRAM(size=262144, banking=Strided(banks=64, stride=1)).name("x7749_d0_b8").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:176:37:wx") } // x7749 = SRAMNew(ArrayBuffer(Const(1024), Const(4), Const(1024)))
    isAccum(x7749_d0_b8) = false
    bufferDepthOf(x7749_d0_b8) = 3
    staticDimsOf(x7749_d0_b8) = List(1024, 4, 1024)
    val x7749_d0_b9 = withCtrl(x8354) { SRAM(size=262144, banking=Strided(banks=64, stride=1)).name("x7749_d0_b9").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:176:37:wx") } // x7749 = SRAMNew(ArrayBuffer(Const(1024), Const(4), Const(1024)))
    isAccum(x7749_d0_b9) = false
    bufferDepthOf(x7749_d0_b9) = 3
    staticDimsOf(x7749_d0_b9) = List(1024, 4, 1024)
    val x7749_d0_b10 = withCtrl(x8354) { SRAM(size=262144, banking=Strided(banks=64, stride=1)).name("x7749_d0_b10").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:176:37:wx") } // x7749 = SRAMNew(ArrayBuffer(Const(1024), Const(4), Const(1024)))
    isAccum(x7749_d0_b10) = false
    bufferDepthOf(x7749_d0_b10) = 3
    staticDimsOf(x7749_d0_b10) = List(1024, 4, 1024)
    val x7749_d0_b11 = withCtrl(x8354) { SRAM(size=262144, banking=Strided(banks=64, stride=1)).name("x7749_d0_b11").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:176:37:wx") } // x7749 = SRAMNew(ArrayBuffer(Const(1024), Const(4), Const(1024)))
    isAccum(x7749_d0_b11) = false
    bufferDepthOf(x7749_d0_b11) = 3
    staticDimsOf(x7749_d0_b11) = List(1024, 4, 1024)
    val x7749_d0_b12 = withCtrl(x8354) { SRAM(size=262144, banking=Strided(banks=64, stride=1)).name("x7749_d0_b12").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:176:37:wx") } // x7749 = SRAMNew(ArrayBuffer(Const(1024), Const(4), Const(1024)))
    isAccum(x7749_d0_b12) = false
    bufferDepthOf(x7749_d0_b12) = 3
    staticDimsOf(x7749_d0_b12) = List(1024, 4, 1024)
    val x7749_d0_b13 = withCtrl(x8354) { SRAM(size=262144, banking=Strided(banks=64, stride=1)).name("x7749_d0_b13").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:176:37:wx") } // x7749 = SRAMNew(ArrayBuffer(Const(1024), Const(4), Const(1024)))
    isAccum(x7749_d0_b13) = false
    bufferDepthOf(x7749_d0_b13) = 3
    staticDimsOf(x7749_d0_b13) = List(1024, 4, 1024)
    val x7749_d0_b14 = withCtrl(x8354) { SRAM(size=262144, banking=Strided(banks=64, stride=1)).name("x7749_d0_b14").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:176:37:wx") } // x7749 = SRAMNew(ArrayBuffer(Const(1024), Const(4), Const(1024)))
    isAccum(x7749_d0_b14) = false
    bufferDepthOf(x7749_d0_b14) = 3
    staticDimsOf(x7749_d0_b14) = List(1024, 4, 1024)
    val x7749_d0_b15 = withCtrl(x8354) { SRAM(size=262144, banking=Strided(banks=64, stride=1)).name("x7749_d0_b15").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:176:37:wx") } // x7749 = SRAMNew(ArrayBuffer(Const(1024), Const(4), Const(1024)))
    isAccum(x7749_d0_b15) = false
    bufferDepthOf(x7749_d0_b15) = 3
    staticDimsOf(x7749_d0_b15) = List(1024, 4, 1024)
    val x7750_d0_b0 = withCtrl(x8354) { SRAM(size=262144, banking=Strided(banks=64, stride=1)).name("x7750_d0_b0").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:177:37:wh") } // x7750 = SRAMNew(ArrayBuffer(Const(1024), Const(4), Const(1024)))
    isAccum(x7750_d0_b0) = false
    bufferDepthOf(x7750_d0_b0) = 2
    staticDimsOf(x7750_d0_b0) = List(1024, 4, 1024)
    val x7750_d0_b1 = withCtrl(x8354) { SRAM(size=262144, banking=Strided(banks=64, stride=1)).name("x7750_d0_b1").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:177:37:wh") } // x7750 = SRAMNew(ArrayBuffer(Const(1024), Const(4), Const(1024)))
    isAccum(x7750_d0_b1) = false
    bufferDepthOf(x7750_d0_b1) = 2
    staticDimsOf(x7750_d0_b1) = List(1024, 4, 1024)
    val x7750_d0_b2 = withCtrl(x8354) { SRAM(size=262144, banking=Strided(banks=64, stride=1)).name("x7750_d0_b2").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:177:37:wh") } // x7750 = SRAMNew(ArrayBuffer(Const(1024), Const(4), Const(1024)))
    isAccum(x7750_d0_b2) = false
    bufferDepthOf(x7750_d0_b2) = 2
    staticDimsOf(x7750_d0_b2) = List(1024, 4, 1024)
    val x7750_d0_b3 = withCtrl(x8354) { SRAM(size=262144, banking=Strided(banks=64, stride=1)).name("x7750_d0_b3").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:177:37:wh") } // x7750 = SRAMNew(ArrayBuffer(Const(1024), Const(4), Const(1024)))
    isAccum(x7750_d0_b3) = false
    bufferDepthOf(x7750_d0_b3) = 2
    staticDimsOf(x7750_d0_b3) = List(1024, 4, 1024)
    val x7750_d0_b4 = withCtrl(x8354) { SRAM(size=262144, banking=Strided(banks=64, stride=1)).name("x7750_d0_b4").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:177:37:wh") } // x7750 = SRAMNew(ArrayBuffer(Const(1024), Const(4), Const(1024)))
    isAccum(x7750_d0_b4) = false
    bufferDepthOf(x7750_d0_b4) = 2
    staticDimsOf(x7750_d0_b4) = List(1024, 4, 1024)
    val x7750_d0_b5 = withCtrl(x8354) { SRAM(size=262144, banking=Strided(banks=64, stride=1)).name("x7750_d0_b5").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:177:37:wh") } // x7750 = SRAMNew(ArrayBuffer(Const(1024), Const(4), Const(1024)))
    isAccum(x7750_d0_b5) = false
    bufferDepthOf(x7750_d0_b5) = 2
    staticDimsOf(x7750_d0_b5) = List(1024, 4, 1024)
    val x7750_d0_b6 = withCtrl(x8354) { SRAM(size=262144, banking=Strided(banks=64, stride=1)).name("x7750_d0_b6").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:177:37:wh") } // x7750 = SRAMNew(ArrayBuffer(Const(1024), Const(4), Const(1024)))
    isAccum(x7750_d0_b6) = false
    bufferDepthOf(x7750_d0_b6) = 2
    staticDimsOf(x7750_d0_b6) = List(1024, 4, 1024)
    val x7750_d0_b7 = withCtrl(x8354) { SRAM(size=262144, banking=Strided(banks=64, stride=1)).name("x7750_d0_b7").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:177:37:wh") } // x7750 = SRAMNew(ArrayBuffer(Const(1024), Const(4), Const(1024)))
    isAccum(x7750_d0_b7) = false
    bufferDepthOf(x7750_d0_b7) = 2
    staticDimsOf(x7750_d0_b7) = List(1024, 4, 1024)
    val x7750_d0_b8 = withCtrl(x8354) { SRAM(size=262144, banking=Strided(banks=64, stride=1)).name("x7750_d0_b8").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:177:37:wh") } // x7750 = SRAMNew(ArrayBuffer(Const(1024), Const(4), Const(1024)))
    isAccum(x7750_d0_b8) = false
    bufferDepthOf(x7750_d0_b8) = 2
    staticDimsOf(x7750_d0_b8) = List(1024, 4, 1024)
    val x7750_d0_b9 = withCtrl(x8354) { SRAM(size=262144, banking=Strided(banks=64, stride=1)).name("x7750_d0_b9").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:177:37:wh") } // x7750 = SRAMNew(ArrayBuffer(Const(1024), Const(4), Const(1024)))
    isAccum(x7750_d0_b9) = false
    bufferDepthOf(x7750_d0_b9) = 2
    staticDimsOf(x7750_d0_b9) = List(1024, 4, 1024)
    val x7750_d0_b10 = withCtrl(x8354) { SRAM(size=262144, banking=Strided(banks=64, stride=1)).name("x7750_d0_b10").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:177:37:wh") } // x7750 = SRAMNew(ArrayBuffer(Const(1024), Const(4), Const(1024)))
    isAccum(x7750_d0_b10) = false
    bufferDepthOf(x7750_d0_b10) = 2
    staticDimsOf(x7750_d0_b10) = List(1024, 4, 1024)
    val x7750_d0_b11 = withCtrl(x8354) { SRAM(size=262144, banking=Strided(banks=64, stride=1)).name("x7750_d0_b11").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:177:37:wh") } // x7750 = SRAMNew(ArrayBuffer(Const(1024), Const(4), Const(1024)))
    isAccum(x7750_d0_b11) = false
    bufferDepthOf(x7750_d0_b11) = 2
    staticDimsOf(x7750_d0_b11) = List(1024, 4, 1024)
    val x7750_d0_b12 = withCtrl(x8354) { SRAM(size=262144, banking=Strided(banks=64, stride=1)).name("x7750_d0_b12").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:177:37:wh") } // x7750 = SRAMNew(ArrayBuffer(Const(1024), Const(4), Const(1024)))
    isAccum(x7750_d0_b12) = false
    bufferDepthOf(x7750_d0_b12) = 2
    staticDimsOf(x7750_d0_b12) = List(1024, 4, 1024)
    val x7750_d0_b13 = withCtrl(x8354) { SRAM(size=262144, banking=Strided(banks=64, stride=1)).name("x7750_d0_b13").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:177:37:wh") } // x7750 = SRAMNew(ArrayBuffer(Const(1024), Const(4), Const(1024)))
    isAccum(x7750_d0_b13) = false
    bufferDepthOf(x7750_d0_b13) = 2
    staticDimsOf(x7750_d0_b13) = List(1024, 4, 1024)
    val x7750_d0_b14 = withCtrl(x8354) { SRAM(size=262144, banking=Strided(banks=64, stride=1)).name("x7750_d0_b14").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:177:37:wh") } // x7750 = SRAMNew(ArrayBuffer(Const(1024), Const(4), Const(1024)))
    isAccum(x7750_d0_b14) = false
    bufferDepthOf(x7750_d0_b14) = 2
    staticDimsOf(x7750_d0_b14) = List(1024, 4, 1024)
    val x7750_d0_b15 = withCtrl(x8354) { SRAM(size=262144, banking=Strided(banks=64, stride=1)).name("x7750_d0_b15").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:177:37:wh") } // x7750 = SRAMNew(ArrayBuffer(Const(1024), Const(4), Const(1024)))
    isAccum(x7750_d0_b15) = false
    bufferDepthOf(x7750_d0_b15) = 2
    staticDimsOf(x7750_d0_b15) = List(1024, 4, 1024)
    val x7752 = withCtrl(x8354) { UnitController(style=SeqPipe, level=InnerControl).name("x7752").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:174:32") } // UnitPipe(List(b3564, b3560),Block(Const(())))
    val x7751 = withCtrl(x7752) { OpDef(op=FixAdd, inputs=List(b3563, Const(1))).name("x7751").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:179:25") } // FixAdd(b3563,Const(1))
    val x7753 = withCtrl(x8354) { Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x7753").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:179:14") } // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x7754 = withCtrl(x8354) { Counter(min=Const(0), max=Const(1024), step=Const(1), par=1).name("x7754").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:179:14") } // CounterNew(Const(0),Const(1024),Const(1),Const(1))
    val x7755 = withCtrl(x8354) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x7755").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:179:14") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x7756 = withCtrl(x8354) { CounterChain(List(x7753,x7754,x7755)).name("x7756").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:179:14") } // CounterChainNew(List(x7753, x7754, x7755))
    val x7793 = withCtrl(x8354) { LoopController(style=StreamPipe, level=OuterControl, cchain=x7756).name("x7793").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:179:14") } // UnrolledForeach(List(b3564, b3560),x7756,Block(Const(())),List(List(b3573), List(b3574), List(b3575)),List(List(b3576), List(b3577), List(b3578)))
    val b3573 = withCtrl(x7793) { CounterIter(x7753, Some(0)).name("b3573") } // b3573
    val b3576 = withCtrl(x7793) { Const(true).name("b3576") } // b3576
    val b3574 = withCtrl(x7793) { CounterIter(x7754, Some(0)).name("b3574") } // b3574
    val b3577 = withCtrl(x7793) { Const(true).name("b3577") } // b3577
    val b3575 = withCtrl(x7793) { CounterIter(x7755, Some(0)).name("b3575") } // b3575
    val b3578 = withCtrl(x7793) { Const(true).name("b3578") } // b3578
    val b8411 = withCtrl(x7793) { StreamOut(field="offset").name("b8411").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:179:14") } // x7757 = StreamOutNew(BurstCmdBus)
    isAccum(b8411) = false
    bufferDepthOf(b8411) = 1
    val b8412 = withCtrl(x7793) { StreamOut(field="size").name("b8412").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:179:14") } // x7757 = StreamOutNew(BurstCmdBus)
    isAccum(b8412) = false
    bufferDepthOf(b8412) = 1
    val x7758 = withCtrl(x7793) { StreamIn(field="data").name("x7758").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:179:14") } // x7758 = StreamInNew(BurstDataBus())
    isAccum(x7758) = false
    bufferDepthOf(x7758) = 1
    val x7780 = withCtrl(x7793) { UnitController(style=SeqPipe, level=InnerControl).name("x7780").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:179:14") } // UnitPipe(List(b3576, b3577, b3578, b3564, b3560),Block(x7779))
    val x7759 = withCtrl(x7780) { OpDef(op=FixAdd, inputs=List(b3563, b3573)).name("x7759").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:179:14") } // FixAdd(b3563,b3573)
    val x7760 = withCtrl(x7780) { x7759 } // FixConvert(x7759,TRUE,_32,_0) (Same Type. No op)
    val x7761 = withCtrl(x7780) { OpDef(op=FixSla, inputs=List(x7760, Const(22))).name("x7761").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:179:14") } // FixLsh(x7760,Const(22))
    val x7762 = withCtrl(x7780) { b3574 } // FixConvert(b3574,TRUE,_32,_0) (Same Type. No op)
    val x7763 = withCtrl(x7780) { OpDef(op=FixSla, inputs=List(x7762, Const(12))).name("x7763").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:179:14") } // FixLsh(x7762,Const(12))
    val x7764 = withCtrl(x7780) { b3575 } // FixConvert(b3575,TRUE,_32,_0) (Same Type. No op)
    val x7765 = withCtrl(x7780) { OpDef(op=FixSla, inputs=List(x7764, Const(10))).name("x7765").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:179:14") } // FixLsh(x7764,Const(10))
    val x7766 = withCtrl(x7780) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x7767 = withCtrl(x7780) { OpDef(op=FixAdd, inputs=List(x7761, x7763)).name("x7767").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:179:14") } // FixAdd(x7761,x7763)
    val x7768 = withCtrl(x7780) { OpDef(op=FixAdd, inputs=List(x7765, x7766)).name("x7768").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:179:14") } // FixAdd(x7765,x7766)
    val x7769 = withCtrl(x7780) { OpDef(op=FixAdd, inputs=List(x7767, x7768)).name("x7769").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:179:14") } // FixAdd(x7767,x7768)
    val x7770 = withCtrl(x7780) { OpDef(op=FixSla, inputs=List(x7769, Const(2))).name("x7770").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:179:14") } // FixLsh(x7769,Const(2))
    val x7771 = withCtrl(x7780) { x7770 } // FixConvert(x7770,TRUE,_64,_0)
    val x7772 = withCtrl(x7780) { DramAddress(x7613).name("x7772").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:179:14") } // GetDRAMAddress(x7613)
    val x7774_x7773 = withCtrl(x7780) { OpDef(op=FixAdd, inputs=List(x7771, x7772)).name("x7774_x7773").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:179:14") } // FixAdd(x7771,x7772)
    // x7774 = SimpleStruct(ArrayBuffer((offset,x7773), (size,Const(4096)), (isLoad,Const(true))))
    val x7775 = withCtrl(x7780) { OpDef(op=BitAnd, inputs=List(b3576, b3577)).name("x7775").srcCtx("UnrollingBase.scala:28:66") } // And(b3576,b3577)
    val x7776 = withCtrl(x7780) { OpDef(op=BitAnd, inputs=List(b3578, b3564)).name("x7776").srcCtx("UnrollingBase.scala:28:66") } // And(b3578,b3564)
    val x7777 = withCtrl(x7780) { OpDef(op=BitAnd, inputs=List(x7775, x7776)).name("x7777").srcCtx("UnrollingBase.scala:28:66") } // And(x7775,x7776)
    val x7778 = withCtrl(x7780) { OpDef(op=BitAnd, inputs=List(x7777, b3560)).name("x7778").srcCtx("UnrollingBase.scala:28:66") } // And(x7777,b3560)
    val x7779_b8413_b8411 = withCtrl(x7780) { WriteMem(b8411, x7774_x7773).name("x7779_b8413_b8411").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:179:14") } // StreamWrite(x7757,x7774,x7778)
    val x7779_b8414_b8412 = withCtrl(x7780) { WriteMem(b8412, Const(4096)).name("x7779_b8414_b8412").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:179:14") } // StreamWrite(x7757,x7774,x7778)
    val x7781 = withCtrl(x7793) { FringeDenseLoad(dram=List(x7613), cmdStream=List(b8411, b8412), dataStream=List(x7758)).name("x7781").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:179:14") } // FringeDenseLoad(x7613,x7757,x7758)
    val x7782 = withCtrl(x7793) { Counter(min=Const(0), max=Const(1024), step=Const(1), par=64).name("x7782").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:179:14") } // CounterNew(Const(0),Const(1024),Const(1),Const(64))
    val x7783 = withCtrl(x7793) { CounterChain(List(x7782)).name("x7783").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:179:14") } // CounterChainNew(List(x7782))
    val x7792 = withCtrl(x7793) { LoopController(style=InnerPipe, level=InnerControl, cchain=x7783).name("x7792").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:179:14") } // UnrolledForeach(List(b3576, b3577, b3578, b3564, b3560),x7783,Block(Const(())),List(List(b3606)),List(List(b3607)))
    val b3606 = withCtrl(x7792) { CounterIter(x7782, None).name("b3606") } // b3606
    val b3607 = withCtrl(x7792) { Const(true).name("b3607") } // b3607
    val x7784 = withCtrl(x7792) { OpDef(op=BitAnd, inputs=List(b3607, b3576)).name("x7784").srcCtx("UnrollingBase.scala:28:66") } // And(b3607,b3576)
    val x7785 = withCtrl(x7792) { OpDef(op=BitAnd, inputs=List(b3577, b3578)).name("x7785").srcCtx("UnrollingBase.scala:28:66") } // And(b3577,b3578)
    val x7786 = withCtrl(x7792) { OpDef(op=BitAnd, inputs=List(b3564, b3560)).name("x7786").srcCtx("UnrollingBase.scala:28:66") } // And(b3564,b3560)
    val x7787 = withCtrl(x7792) { OpDef(op=BitAnd, inputs=List(x7784, x7785)).name("x7787").srcCtx("UnrollingBase.scala:28:66") } // And(x7784,x7785)
    val x7788 = withCtrl(x7792) { OpDef(op=BitAnd, inputs=List(x7787, x7786)).name("x7788").srcCtx("UnrollingBase.scala:28:66") } // And(x7787,x7786)
    val x7789_x7789 = withCtrl(x7792) { ReadMem(x7758).name("x7789_x7789").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:179:14") } // ParStreamRead(x7758,List(x7788))
    val x7790_x7790 = withCtrl(x7792) { x7789_x7789 } // VectorApply(x7789,0)
    val x7791 = withCtrl(x7792) { StoreBanks(List(List(x7749_d0_b0, x7749_d0_b1, x7749_d0_b2, x7749_d0_b3, x7749_d0_b4, x7749_d0_b5, x7749_d0_b6, x7749_d0_b7, x7749_d0_b8, x7749_d0_b9, x7749_d0_b10, x7749_d0_b11, x7749_d0_b12, x7749_d0_b13, x7749_d0_b14, x7749_d0_b15)), List(b3574, b3575, b3606), x7790_x7790).name("x7791").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:179:14") } // ParSRAMStore(x7749,List(List(b3574, b3575, b3606)),List(x7790),List(x7788))
    val x7794 = withCtrl(x8354) { Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x7794").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:180:14") } // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x7795 = withCtrl(x8354) { Counter(min=Const(0), max=Const(1024), step=Const(1), par=1).name("x7795").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:180:14") } // CounterNew(Const(0),Const(1024),Const(1),Const(1))
    val x7796 = withCtrl(x8354) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x7796").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:180:14") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x7797 = withCtrl(x8354) { CounterChain(List(x7794,x7795,x7796)).name("x7797").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:180:14") } // CounterChainNew(List(x7794, x7795, x7796))
    val x7834 = withCtrl(x8354) { LoopController(style=StreamPipe, level=OuterControl, cchain=x7797).name("x7834").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:180:14") } // UnrolledForeach(List(b3564, b3560),x7797,Block(Const(())),List(List(b3622), List(b3623), List(b3624)),List(List(b3625), List(b3626), List(b3627)))
    val b3622 = withCtrl(x7834) { CounterIter(x7794, Some(0)).name("b3622") } // b3622
    val b3625 = withCtrl(x7834) { Const(true).name("b3625") } // b3625
    val b3623 = withCtrl(x7834) { CounterIter(x7795, Some(0)).name("b3623") } // b3623
    val b3626 = withCtrl(x7834) { Const(true).name("b3626") } // b3626
    val b3624 = withCtrl(x7834) { CounterIter(x7796, Some(0)).name("b3624") } // b3624
    val b3627 = withCtrl(x7834) { Const(true).name("b3627") } // b3627
    val b8415 = withCtrl(x7834) { StreamOut(field="offset").name("b8415").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:180:14") } // x7798 = StreamOutNew(BurstCmdBus)
    isAccum(b8415) = false
    bufferDepthOf(b8415) = 1
    val b8416 = withCtrl(x7834) { StreamOut(field="size").name("b8416").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:180:14") } // x7798 = StreamOutNew(BurstCmdBus)
    isAccum(b8416) = false
    bufferDepthOf(b8416) = 1
    val x7799 = withCtrl(x7834) { StreamIn(field="data").name("x7799").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:180:14") } // x7799 = StreamInNew(BurstDataBus())
    isAccum(x7799) = false
    bufferDepthOf(x7799) = 1
    val x7821 = withCtrl(x7834) { UnitController(style=SeqPipe, level=InnerControl).name("x7821").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:180:14") } // UnitPipe(List(b3625, b3626, b3627, b3564, b3560),Block(x7820))
    val x7800 = withCtrl(x7821) { OpDef(op=FixAdd, inputs=List(b3563, b3622)).name("x7800").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:180:14") } // FixAdd(b3563,b3622)
    val x7801 = withCtrl(x7821) { x7800 } // FixConvert(x7800,TRUE,_32,_0) (Same Type. No op)
    val x7802 = withCtrl(x7821) { OpDef(op=FixSla, inputs=List(x7801, Const(22))).name("x7802").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:180:14") } // FixLsh(x7801,Const(22))
    val x7803 = withCtrl(x7821) { b3623 } // FixConvert(b3623,TRUE,_32,_0) (Same Type. No op)
    val x7804 = withCtrl(x7821) { OpDef(op=FixSla, inputs=List(x7803, Const(12))).name("x7804").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:180:14") } // FixLsh(x7803,Const(12))
    val x7805 = withCtrl(x7821) { b3624 } // FixConvert(b3624,TRUE,_32,_0) (Same Type. No op)
    val x7806 = withCtrl(x7821) { OpDef(op=FixSla, inputs=List(x7805, Const(10))).name("x7806").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:180:14") } // FixLsh(x7805,Const(10))
    val x7807 = withCtrl(x7821) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x7808 = withCtrl(x7821) { OpDef(op=FixAdd, inputs=List(x7802, x7804)).name("x7808").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:180:14") } // FixAdd(x7802,x7804)
    val x7809 = withCtrl(x7821) { OpDef(op=FixAdd, inputs=List(x7806, x7807)).name("x7809").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:180:14") } // FixAdd(x7806,x7807)
    val x7810 = withCtrl(x7821) { OpDef(op=FixAdd, inputs=List(x7808, x7809)).name("x7810").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:180:14") } // FixAdd(x7808,x7809)
    val x7811 = withCtrl(x7821) { OpDef(op=FixSla, inputs=List(x7810, Const(2))).name("x7811").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:180:14") } // FixLsh(x7810,Const(2))
    val x7812 = withCtrl(x7821) { x7811 } // FixConvert(x7811,TRUE,_64,_0)
    val x7813 = withCtrl(x7821) { DramAddress(x7626).name("x7813").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:180:14") } // GetDRAMAddress(x7626)
    val x7815_x7814 = withCtrl(x7821) { OpDef(op=FixAdd, inputs=List(x7812, x7813)).name("x7815_x7814").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:180:14") } // FixAdd(x7812,x7813)
    // x7815 = SimpleStruct(ArrayBuffer((offset,x7814), (size,Const(4096)), (isLoad,Const(true))))
    val x7816 = withCtrl(x7821) { OpDef(op=BitAnd, inputs=List(b3625, b3626)).name("x7816").srcCtx("UnrollingBase.scala:28:66") } // And(b3625,b3626)
    val x7817 = withCtrl(x7821) { OpDef(op=BitAnd, inputs=List(b3627, b3564)).name("x7817").srcCtx("UnrollingBase.scala:28:66") } // And(b3627,b3564)
    val x7818 = withCtrl(x7821) { OpDef(op=BitAnd, inputs=List(x7816, x7817)).name("x7818").srcCtx("UnrollingBase.scala:28:66") } // And(x7816,x7817)
    val x7819 = withCtrl(x7821) { OpDef(op=BitAnd, inputs=List(x7818, b3560)).name("x7819").srcCtx("UnrollingBase.scala:28:66") } // And(x7818,b3560)
    val x7820_b8417_b8415 = withCtrl(x7821) { WriteMem(b8415, x7815_x7814).name("x7820_b8417_b8415").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:180:14") } // StreamWrite(x7798,x7815,x7819)
    val x7820_b8418_b8416 = withCtrl(x7821) { WriteMem(b8416, Const(4096)).name("x7820_b8418_b8416").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:180:14") } // StreamWrite(x7798,x7815,x7819)
    val x7822 = withCtrl(x7834) { FringeDenseLoad(dram=List(x7626), cmdStream=List(b8415, b8416), dataStream=List(x7799)).name("x7822").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:180:14") } // FringeDenseLoad(x7626,x7798,x7799)
    val x7823 = withCtrl(x7834) { Counter(min=Const(0), max=Const(1024), step=Const(1), par=64).name("x7823").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:180:14") } // CounterNew(Const(0),Const(1024),Const(1),Const(64))
    val x7824 = withCtrl(x7834) { CounterChain(List(x7823)).name("x7824").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:180:14") } // CounterChainNew(List(x7823))
    val x7833 = withCtrl(x7834) { LoopController(style=InnerPipe, level=InnerControl, cchain=x7824).name("x7833").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:180:14") } // UnrolledForeach(List(b3625, b3626, b3627, b3564, b3560),x7824,Block(Const(())),List(List(b3655)),List(List(b3656)))
    val b3655 = withCtrl(x7833) { CounterIter(x7823, None).name("b3655") } // b3655
    val b3656 = withCtrl(x7833) { Const(true).name("b3656") } // b3656
    val x7825 = withCtrl(x7833) { OpDef(op=BitAnd, inputs=List(b3656, b3625)).name("x7825").srcCtx("UnrollingBase.scala:28:66") } // And(b3656,b3625)
    val x7826 = withCtrl(x7833) { OpDef(op=BitAnd, inputs=List(b3626, b3627)).name("x7826").srcCtx("UnrollingBase.scala:28:66") } // And(b3626,b3627)
    val x7827 = withCtrl(x7833) { OpDef(op=BitAnd, inputs=List(b3564, b3560)).name("x7827").srcCtx("UnrollingBase.scala:28:66") } // And(b3564,b3560)
    val x7828 = withCtrl(x7833) { OpDef(op=BitAnd, inputs=List(x7825, x7826)).name("x7828").srcCtx("UnrollingBase.scala:28:66") } // And(x7825,x7826)
    val x7829 = withCtrl(x7833) { OpDef(op=BitAnd, inputs=List(x7828, x7827)).name("x7829").srcCtx("UnrollingBase.scala:28:66") } // And(x7828,x7827)
    val x7830_x7830 = withCtrl(x7833) { ReadMem(x7799).name("x7830_x7830").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:180:14") } // ParStreamRead(x7799,List(x7829))
    val x7831_x7831 = withCtrl(x7833) { x7830_x7830 } // VectorApply(x7830,0)
    val x7832 = withCtrl(x7833) { StoreBanks(List(List(x7750_d0_b0, x7750_d0_b1, x7750_d0_b2, x7750_d0_b3, x7750_d0_b4, x7750_d0_b5, x7750_d0_b6, x7750_d0_b7, x7750_d0_b8, x7750_d0_b9, x7750_d0_b10, x7750_d0_b11, x7750_d0_b12, x7750_d0_b13, x7750_d0_b14, x7750_d0_b15)), List(b3623, b3624, b3655), x7831_x7831).name("x7832").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:180:14") } // ParSRAMStore(x7750,List(List(b3623, b3624, b3655)),List(x7831),List(x7829))
    val x7835_d0_b0 = withCtrl(x8354) { SRAM(size=4096, banking=Strided(banks=64, stride=1)).name("x7835_d0_b0").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:25:38:reduceMem") } // x7835 = SRAMNew(ArrayBuffer(Const(4), Const(1024)))
    isAccum(x7835_d0_b0) = false
    bufferDepthOf(x7835_d0_b0) = 2
    staticDimsOf(x7835_d0_b0) = List(4, 1024)
    val x7835_d1_b0 = withCtrl(x8354) { SRAM(size=4096, banking=Strided(banks=64, stride=1)).name("x7835_d1_b0").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:25:38:reduceMem") } // x7835 = SRAMNew(ArrayBuffer(Const(4), Const(1024)))
    isAccum(x7835_d1_b0) = true
    bufferDepthOf(x7835_d1_b0) = 1
    staticDimsOf(x7835_d1_b0) = List(4, 1024)
    val x7836_d0_b0 = withCtrl(x8354) { SRAM(size=4096, banking=Strided(banks=64, stride=1)).name("x7836_d0_b0").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:26:36:foldMem") } // x7836 = SRAMNew(ArrayBuffer(Const(4), Const(1024)))
    isAccum(x7836_d0_b0) = false
    bufferDepthOf(x7836_d0_b0) = 2
    staticDimsOf(x7836_d0_b0) = List(4, 1024)
    val x7837 = withCtrl(x8354) { Counter(min=Const(0), max=Const(1024), step=Const(1), par=4).name("x7837").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:27:58") } // CounterNew(Const(0),Const(1024),Const(1),Const(4))
    val x7838 = withCtrl(x8354) { CounterChain(List(x7837)).name("x7838").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // CounterChainNew(List(x7837))
    val x8211 = withCtrl(x8354) { LoopController(style=MetaPipe, level=OuterControl, cchain=x7838).name("x8211").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // UnrolledReduce(List(b3564, b3560),x7838,x7835,Block((x7835) => Const(())),List(List(b3674, b3675, b3676, b3677)),List(List(b3678, b3679, b3680, b3681)))
    val b3674 = withCtrl(x8211) { CounterIter(x7837, Some(0)).name("b3674") } // b3674
    val b3678 = withCtrl(x8211) { Const(true).name("b3678") } // b3678
    val b3675 = withCtrl(x8211) { CounterIter(x7837, Some(1)).name("b3675") } // b3675
    val b3679 = withCtrl(x8211) { Const(true).name("b3679") } // b3679
    val b3676 = withCtrl(x8211) { CounterIter(x7837, Some(2)).name("b3676") } // b3676
    val b3680 = withCtrl(x8211) { Const(true).name("b3680") } // b3680
    val b3677 = withCtrl(x8211) { CounterIter(x7837, Some(3)).name("b3677") } // b3677
    val b3681 = withCtrl(x8211) { Const(true).name("b3681") } // b3681
    val x7839_d0_b0 = withCtrl(x8211) { SRAM(size=1024, banking=Strided(banks=64, stride=1)).name("x7839_d0_b0").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:28:37:memBuf") } // x7839 = SRAMNew(ArrayBuffer(Const(4), Const(1024)))
    isAccum(x7839_d0_b0) = false
    bufferDepthOf(x7839_d0_b0) = 2
    staticDimsOf(x7839_d0_b0) = List(4, 1024)
    val x7839_d0_b1 = withCtrl(x8211) { SRAM(size=1024, banking=Strided(banks=64, stride=1)).name("x7839_d0_b1").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:28:37:memBuf") } // x7839 = SRAMNew(ArrayBuffer(Const(4), Const(1024)))
    isAccum(x7839_d0_b1) = false
    bufferDepthOf(x7839_d0_b1) = 2
    staticDimsOf(x7839_d0_b1) = List(4, 1024)
    val x7839_d0_b2 = withCtrl(x8211) { SRAM(size=1024, banking=Strided(banks=64, stride=1)).name("x7839_d0_b2").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:28:37:memBuf") } // x7839 = SRAMNew(ArrayBuffer(Const(4), Const(1024)))
    isAccum(x7839_d0_b2) = false
    bufferDepthOf(x7839_d0_b2) = 2
    staticDimsOf(x7839_d0_b2) = List(4, 1024)
    val x7839_d0_b3 = withCtrl(x8211) { SRAM(size=1024, banking=Strided(banks=64, stride=1)).name("x7839_d0_b3").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:28:37:memBuf") } // x7839 = SRAMNew(ArrayBuffer(Const(4), Const(1024)))
    isAccum(x7839_d0_b3) = false
    bufferDepthOf(x7839_d0_b3) = 2
    staticDimsOf(x7839_d0_b3) = List(4, 1024)
    val x7840_d0_b0 = withCtrl(x8211) { SRAM(size=1024, banking=Strided(banks=64, stride=1)).name("x7840_d0_b0").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:28:37:memBuf") } // x7840 = SRAMNew(ArrayBuffer(Const(4), Const(1024)))
    isAccum(x7840_d0_b0) = false
    bufferDepthOf(x7840_d0_b0) = 2
    staticDimsOf(x7840_d0_b0) = List(4, 1024)
    val x7840_d0_b1 = withCtrl(x8211) { SRAM(size=1024, banking=Strided(banks=64, stride=1)).name("x7840_d0_b1").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:28:37:memBuf") } // x7840 = SRAMNew(ArrayBuffer(Const(4), Const(1024)))
    isAccum(x7840_d0_b1) = false
    bufferDepthOf(x7840_d0_b1) = 2
    staticDimsOf(x7840_d0_b1) = List(4, 1024)
    val x7840_d0_b2 = withCtrl(x8211) { SRAM(size=1024, banking=Strided(banks=64, stride=1)).name("x7840_d0_b2").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:28:37:memBuf") } // x7840 = SRAMNew(ArrayBuffer(Const(4), Const(1024)))
    isAccum(x7840_d0_b2) = false
    bufferDepthOf(x7840_d0_b2) = 2
    staticDimsOf(x7840_d0_b2) = List(4, 1024)
    val x7840_d0_b3 = withCtrl(x8211) { SRAM(size=1024, banking=Strided(banks=64, stride=1)).name("x7840_d0_b3").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:28:37:memBuf") } // x7840 = SRAMNew(ArrayBuffer(Const(4), Const(1024)))
    isAccum(x7840_d0_b3) = false
    bufferDepthOf(x7840_d0_b3) = 2
    staticDimsOf(x7840_d0_b3) = List(4, 1024)
    val x7841_d0_b0 = withCtrl(x8211) { SRAM(size=1024, banking=Strided(banks=64, stride=1)).name("x7841_d0_b0").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:28:37:memBuf") } // x7841 = SRAMNew(ArrayBuffer(Const(4), Const(1024)))
    isAccum(x7841_d0_b0) = false
    bufferDepthOf(x7841_d0_b0) = 2
    staticDimsOf(x7841_d0_b0) = List(4, 1024)
    val x7841_d0_b1 = withCtrl(x8211) { SRAM(size=1024, banking=Strided(banks=64, stride=1)).name("x7841_d0_b1").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:28:37:memBuf") } // x7841 = SRAMNew(ArrayBuffer(Const(4), Const(1024)))
    isAccum(x7841_d0_b1) = false
    bufferDepthOf(x7841_d0_b1) = 2
    staticDimsOf(x7841_d0_b1) = List(4, 1024)
    def split1 = {
    val x7841_d0_b2 = withCtrl(x8211) { SRAM(size=1024, banking=Strided(banks=64, stride=1)).name("x7841_d0_b2").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:28:37:memBuf") } // x7841 = SRAMNew(ArrayBuffer(Const(4), Const(1024)))
    isAccum(x7841_d0_b2) = false
    bufferDepthOf(x7841_d0_b2) = 2
    staticDimsOf(x7841_d0_b2) = List(4, 1024)
    val x7841_d0_b3 = withCtrl(x8211) { SRAM(size=1024, banking=Strided(banks=64, stride=1)).name("x7841_d0_b3").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:28:37:memBuf") } // x7841 = SRAMNew(ArrayBuffer(Const(4), Const(1024)))
    isAccum(x7841_d0_b3) = false
    bufferDepthOf(x7841_d0_b3) = 2
    staticDimsOf(x7841_d0_b3) = List(4, 1024)
    val x7842_d0_b0 = withCtrl(x8211) { SRAM(size=1024, banking=Strided(banks=64, stride=1)).name("x7842_d0_b0").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:28:37:memBuf") } // x7842 = SRAMNew(ArrayBuffer(Const(4), Const(1024)))
    isAccum(x7842_d0_b0) = false
    bufferDepthOf(x7842_d0_b0) = 2
    staticDimsOf(x7842_d0_b0) = List(4, 1024)
    val x7842_d0_b1 = withCtrl(x8211) { SRAM(size=1024, banking=Strided(banks=64, stride=1)).name("x7842_d0_b1").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:28:37:memBuf") } // x7842 = SRAMNew(ArrayBuffer(Const(4), Const(1024)))
    isAccum(x7842_d0_b1) = false
    bufferDepthOf(x7842_d0_b1) = 2
    staticDimsOf(x7842_d0_b1) = List(4, 1024)
    val x7842_d0_b2 = withCtrl(x8211) { SRAM(size=1024, banking=Strided(banks=64, stride=1)).name("x7842_d0_b2").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:28:37:memBuf") } // x7842 = SRAMNew(ArrayBuffer(Const(4), Const(1024)))
    isAccum(x7842_d0_b2) = false
    bufferDepthOf(x7842_d0_b2) = 2
    staticDimsOf(x7842_d0_b2) = List(4, 1024)
    val x7842_d0_b3 = withCtrl(x8211) { SRAM(size=1024, banking=Strided(banks=64, stride=1)).name("x7842_d0_b3").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:28:37:memBuf") } // x7842 = SRAMNew(ArrayBuffer(Const(4), Const(1024)))
    isAccum(x7842_d0_b3) = false
    bufferDepthOf(x7842_d0_b3) = 2
    staticDimsOf(x7842_d0_b3) = List(4, 1024)
    val x7843_d0 = withCtrl(x8211) { Reg(init=Some(0.0)).name("x7843_d0").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // x7843 = RegNew(Const(0))
    isAccum(x7843_d0) = false
    bufferDepthOf(x7843_d0) = 2
    val x7843_d1 = withCtrl(x8211) { Reg(init=Some(0.0)).name("x7843_d1").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // x7843 = RegNew(Const(0))
    isAccum(x7843_d1) = false
    bufferDepthOf(x7843_d1) = 2
    val x7843_d2 = withCtrl(x8211) { Reg(init=Some(0.0)).name("x7843_d2").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // x7843 = RegNew(Const(0))
    isAccum(x7843_d2) = false
    bufferDepthOf(x7843_d2) = 2
    val x7843_d3 = withCtrl(x8211) { Reg(init=Some(0.0)).name("x7843_d3").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // x7843 = RegNew(Const(0))
    isAccum(x7843_d3) = false
    bufferDepthOf(x7843_d3) = 2
    val x7844_d0 = withCtrl(x8211) { Reg(init=Some(0.0)).name("x7844_d0").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // x7844 = RegNew(Const(0))
    isAccum(x7844_d0) = false
    bufferDepthOf(x7844_d0) = 2
    val x7844_d1 = withCtrl(x8211) { Reg(init=Some(0.0)).name("x7844_d1").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // x7844 = RegNew(Const(0))
    isAccum(x7844_d1) = false
    bufferDepthOf(x7844_d1) = 2
    val x7844_d2 = withCtrl(x8211) { Reg(init=Some(0.0)).name("x7844_d2").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // x7844 = RegNew(Const(0))
    isAccum(x7844_d2) = false
    bufferDepthOf(x7844_d2) = 2
    val x7844_d3 = withCtrl(x8211) { Reg(init=Some(0.0)).name("x7844_d3").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // x7844 = RegNew(Const(0))
    isAccum(x7844_d3) = false
    bufferDepthOf(x7844_d3) = 2
    val x7845_d0 = withCtrl(x8211) { Reg(init=Some(0.0)).name("x7845_d0").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // x7845 = RegNew(Const(0))
    isAccum(x7845_d0) = false
    bufferDepthOf(x7845_d0) = 2
    val x7845_d1 = withCtrl(x8211) { Reg(init=Some(0.0)).name("x7845_d1").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // x7845 = RegNew(Const(0))
    isAccum(x7845_d1) = false
    bufferDepthOf(x7845_d1) = 2
    val x7845_d2 = withCtrl(x8211) { Reg(init=Some(0.0)).name("x7845_d2").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // x7845 = RegNew(Const(0))
    isAccum(x7845_d2) = false
    bufferDepthOf(x7845_d2) = 2
    val x7845_d3 = withCtrl(x8211) { Reg(init=Some(0.0)).name("x7845_d3").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // x7845 = RegNew(Const(0))
    isAccum(x7845_d3) = false
    bufferDepthOf(x7845_d3) = 2
    val x7846_d0 = withCtrl(x8211) { Reg(init=Some(0.0)).name("x7846_d0").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // x7846 = RegNew(Const(0))
    isAccum(x7846_d0) = false
    bufferDepthOf(x7846_d0) = 2
    val x7846_d1 = withCtrl(x8211) { Reg(init=Some(0.0)).name("x7846_d1").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // x7846 = RegNew(Const(0))
    isAccum(x7846_d1) = false
    bufferDepthOf(x7846_d1) = 2
    val x7846_d2 = withCtrl(x8211) { Reg(init=Some(0.0)).name("x7846_d2").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // x7846 = RegNew(Const(0))
    isAccum(x7846_d2) = false
    bufferDepthOf(x7846_d2) = 2
    val x7846_d3 = withCtrl(x8211) { Reg(init=Some(0.0)).name("x7846_d3").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // x7846 = RegNew(Const(0))
    isAccum(x7846_d3) = false
    bufferDepthOf(x7846_d3) = 2
    val x7847_d0 = withCtrl(x8211) { Reg(init=Some(0.0)).name("x7847_d0").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // x7847 = RegNew(Const(0))
    isAccum(x7847_d0) = false
    bufferDepthOf(x7847_d0) = 2
    val x7847_d1 = withCtrl(x8211) { Reg(init=Some(0.0)).name("x7847_d1").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // x7847 = RegNew(Const(0))
    isAccum(x7847_d1) = false
    bufferDepthOf(x7847_d1) = 2
    val x7847_d2 = withCtrl(x8211) { Reg(init=Some(0.0)).name("x7847_d2").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // x7847 = RegNew(Const(0))
    isAccum(x7847_d2) = false
    bufferDepthOf(x7847_d2) = 2
    val x7847_d3 = withCtrl(x8211) { Reg(init=Some(0.0)).name("x7847_d3").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // x7847 = RegNew(Const(0))
    isAccum(x7847_d3) = false
    bufferDepthOf(x7847_d3) = 2
    val x7848_d0 = withCtrl(x8211) { Reg(init=Some(0.0)).name("x7848_d0").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // x7848 = RegNew(Const(0))
    isAccum(x7848_d0) = false
    bufferDepthOf(x7848_d0) = 2
    val x7848_d1 = withCtrl(x8211) { Reg(init=Some(0.0)).name("x7848_d1").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // x7848 = RegNew(Const(0))
    isAccum(x7848_d1) = false
    bufferDepthOf(x7848_d1) = 2
    val x7848_d2 = withCtrl(x8211) { Reg(init=Some(0.0)).name("x7848_d2").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // x7848 = RegNew(Const(0))
    isAccum(x7848_d2) = false
    bufferDepthOf(x7848_d2) = 2
    val x7848_d3 = withCtrl(x8211) { Reg(init=Some(0.0)).name("x7848_d3").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // x7848 = RegNew(Const(0))
    isAccum(x7848_d3) = false
    bufferDepthOf(x7848_d3) = 2
    val x7849_d0 = withCtrl(x8211) { Reg(init=Some(0.0)).name("x7849_d0").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // x7849 = RegNew(Const(0))
    isAccum(x7849_d0) = false
    bufferDepthOf(x7849_d0) = 2
    val x7849_d1 = withCtrl(x8211) { Reg(init=Some(0.0)).name("x7849_d1").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // x7849 = RegNew(Const(0))
    isAccum(x7849_d1) = false
    bufferDepthOf(x7849_d1) = 2
    val x7849_d2 = withCtrl(x8211) { Reg(init=Some(0.0)).name("x7849_d2").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // x7849 = RegNew(Const(0))
    isAccum(x7849_d2) = false
    bufferDepthOf(x7849_d2) = 2
    val x7849_d3 = withCtrl(x8211) { Reg(init=Some(0.0)).name("x7849_d3").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // x7849 = RegNew(Const(0))
    isAccum(x7849_d3) = false
    bufferDepthOf(x7849_d3) = 2
    val x7850_d0 = withCtrl(x8211) { Reg(init=Some(0.0)).name("x7850_d0").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // x7850 = RegNew(Const(0))
    isAccum(x7850_d0) = false
    bufferDepthOf(x7850_d0) = 2
    val x7850_d1 = withCtrl(x8211) { Reg(init=Some(0.0)).name("x7850_d1").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // x7850 = RegNew(Const(0))
    isAccum(x7850_d1) = false
    bufferDepthOf(x7850_d1) = 2
    val x7850_d2 = withCtrl(x8211) { Reg(init=Some(0.0)).name("x7850_d2").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // x7850 = RegNew(Const(0))
    isAccum(x7850_d2) = false
    bufferDepthOf(x7850_d2) = 2
    val x7850_d3 = withCtrl(x8211) { Reg(init=Some(0.0)).name("x7850_d3").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // x7850 = RegNew(Const(0))
    isAccum(x7850_d3) = false
    bufferDepthOf(x7850_d3) = 2
    val x7879 = withCtrl(x8211) { UnitController(style=ForkJoin, level=OuterControl).name("x7879").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b3564, b3560),Block(Const(())))
    val x7857 = withCtrl(x7879) { UnitController(style=SeqPipe, level=InnerControl).name("x7857").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // UnitPipe(List(b3678, b3564, b3560),Block(Const(())))
    val x7851 = withCtrl(x7857) { OpDef(op=BitAnd, inputs=List(b3678, b3564)).name("x7851").srcCtx("UnrollingBase.scala:28:66") } // And(b3678,b3564)
    val x7852 = withCtrl(x7857) { OpDef(op=BitAnd, inputs=List(x7851, b3560)).name("x7852").srcCtx("UnrollingBase.scala:28:66") } // And(x7851,b3560)
    val x7853 = withCtrl(x7857) { LoadBanks(List(x7639_d1_b0), List(b3559, b3674)).name("x7853").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:29:28:xCandidate") } // SRAMLoad(x7639,ArrayBuffer(Const(8), Const(1024)),List(b3559, b3674),Const(0),x7852)
    val x7854 = withCtrl(x7857) { LoadBanks(List(x7641_d1_b0), List(b3563, b3674)).name("x7854").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:30:28:hCandidate") } // SRAMLoad(x7641,ArrayBuffer(Const(2), Const(1024)),List(b3563, b3674),Const(0),x7852)
    val x7855_x7843_d0 = withCtrl(x7857) { WriteMem(x7843_d0, x7853).name("x7855_x7843_d0").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // RegWrite(x7843,x7853,x7852)
    val x7855_x7843_d1 = withCtrl(x7857) { WriteMem(x7843_d1, x7853).name("x7855_x7843_d1").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // RegWrite(x7843,x7853,x7852)
    val x7855_x7843_d2 = withCtrl(x7857) { WriteMem(x7843_d2, x7853).name("x7855_x7843_d2").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // RegWrite(x7843,x7853,x7852)
    val x7855_x7843_d3 = withCtrl(x7857) { WriteMem(x7843_d3, x7853).name("x7855_x7843_d3").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // RegWrite(x7843,x7853,x7852)
    val x7856_x7847_d0 = withCtrl(x7857) { WriteMem(x7847_d0, x7854).name("x7856_x7847_d0").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // RegWrite(x7847,x7854,x7852)
    val x7856_x7847_d1 = withCtrl(x7857) { WriteMem(x7847_d1, x7854).name("x7856_x7847_d1").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // RegWrite(x7847,x7854,x7852)
    val x7856_x7847_d2 = withCtrl(x7857) { WriteMem(x7847_d2, x7854).name("x7856_x7847_d2").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // RegWrite(x7847,x7854,x7852)
    val x7856_x7847_d3 = withCtrl(x7857) { WriteMem(x7847_d3, x7854).name("x7856_x7847_d3").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // RegWrite(x7847,x7854,x7852)
    val x7864 = withCtrl(x7879) { UnitController(style=SeqPipe, level=InnerControl).name("x7864").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // UnitPipe(List(b3679, b3564, b3560),Block(Const(())))
    val x7858 = withCtrl(x7864) { OpDef(op=BitAnd, inputs=List(b3679, b3564)).name("x7858").srcCtx("UnrollingBase.scala:28:66") } // And(b3679,b3564)
    val x7859 = withCtrl(x7864) { OpDef(op=BitAnd, inputs=List(x7858, b3560)).name("x7859").srcCtx("UnrollingBase.scala:28:66") } // And(x7858,b3560)
    val x7860 = withCtrl(x7864) { LoadBanks(List(x7639_d1_b0), List(b3559, b3675)).name("x7860").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:29:28:xCandidate") } // SRAMLoad(x7639,ArrayBuffer(Const(8), Const(1024)),List(b3559, b3675),Const(0),x7859)
    val x7861 = withCtrl(x7864) { LoadBanks(List(x7641_d1_b0), List(b3563, b3675)).name("x7861").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:30:28:hCandidate") } // SRAMLoad(x7641,ArrayBuffer(Const(2), Const(1024)),List(b3563, b3675),Const(0),x7859)
    val x7862_x7844_d0 = withCtrl(x7864) { WriteMem(x7844_d0, x7860).name("x7862_x7844_d0").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // RegWrite(x7844,x7860,x7859)
    val x7862_x7844_d1 = withCtrl(x7864) { WriteMem(x7844_d1, x7860).name("x7862_x7844_d1").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // RegWrite(x7844,x7860,x7859)
    val x7862_x7844_d2 = withCtrl(x7864) { WriteMem(x7844_d2, x7860).name("x7862_x7844_d2").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // RegWrite(x7844,x7860,x7859)
    val x7862_x7844_d3 = withCtrl(x7864) { WriteMem(x7844_d3, x7860).name("x7862_x7844_d3").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // RegWrite(x7844,x7860,x7859)
    val x7863_x7848_d0 = withCtrl(x7864) { WriteMem(x7848_d0, x7861).name("x7863_x7848_d0").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // RegWrite(x7848,x7861,x7859)
    val x7863_x7848_d1 = withCtrl(x7864) { WriteMem(x7848_d1, x7861).name("x7863_x7848_d1").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // RegWrite(x7848,x7861,x7859)
    val x7863_x7848_d2 = withCtrl(x7864) { WriteMem(x7848_d2, x7861).name("x7863_x7848_d2").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // RegWrite(x7848,x7861,x7859)
    val x7863_x7848_d3 = withCtrl(x7864) { WriteMem(x7848_d3, x7861).name("x7863_x7848_d3").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // RegWrite(x7848,x7861,x7859)
    val x7871 = withCtrl(x7879) { UnitController(style=SeqPipe, level=InnerControl).name("x7871").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // UnitPipe(List(b3680, b3564, b3560),Block(Const(())))
    val x7865 = withCtrl(x7871) { OpDef(op=BitAnd, inputs=List(b3680, b3564)).name("x7865").srcCtx("UnrollingBase.scala:28:66") } // And(b3680,b3564)
    val x7866 = withCtrl(x7871) { OpDef(op=BitAnd, inputs=List(x7865, b3560)).name("x7866").srcCtx("UnrollingBase.scala:28:66") } // And(x7865,b3560)
    val x7867 = withCtrl(x7871) { LoadBanks(List(x7639_d1_b0), List(b3559, b3676)).name("x7867").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:29:28:xCandidate") } // SRAMLoad(x7639,ArrayBuffer(Const(8), Const(1024)),List(b3559, b3676),Const(0),x7866)
    val x7868 = withCtrl(x7871) { LoadBanks(List(x7641_d1_b0), List(b3563, b3676)).name("x7868").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:30:28:hCandidate") } // SRAMLoad(x7641,ArrayBuffer(Const(2), Const(1024)),List(b3563, b3676),Const(0),x7866)
    val x7869_x7845_d0 = withCtrl(x7871) { WriteMem(x7845_d0, x7867).name("x7869_x7845_d0").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // RegWrite(x7845,x7867,x7866)
    val x7869_x7845_d1 = withCtrl(x7871) { WriteMem(x7845_d1, x7867).name("x7869_x7845_d1").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // RegWrite(x7845,x7867,x7866)
    val x7869_x7845_d2 = withCtrl(x7871) { WriteMem(x7845_d2, x7867).name("x7869_x7845_d2").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // RegWrite(x7845,x7867,x7866)
    val x7869_x7845_d3 = withCtrl(x7871) { WriteMem(x7845_d3, x7867).name("x7869_x7845_d3").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // RegWrite(x7845,x7867,x7866)
    val x7870_x7849_d0 = withCtrl(x7871) { WriteMem(x7849_d0, x7868).name("x7870_x7849_d0").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // RegWrite(x7849,x7868,x7866)
    val x7870_x7849_d1 = withCtrl(x7871) { WriteMem(x7849_d1, x7868).name("x7870_x7849_d1").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // RegWrite(x7849,x7868,x7866)
    val x7870_x7849_d2 = withCtrl(x7871) { WriteMem(x7849_d2, x7868).name("x7870_x7849_d2").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // RegWrite(x7849,x7868,x7866)
    val x7870_x7849_d3 = withCtrl(x7871) { WriteMem(x7849_d3, x7868).name("x7870_x7849_d3").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // RegWrite(x7849,x7868,x7866)
    val x7878 = withCtrl(x7879) { UnitController(style=SeqPipe, level=InnerControl).name("x7878").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // UnitPipe(List(b3681, b3564, b3560),Block(Const(())))
    val x7872 = withCtrl(x7878) { OpDef(op=BitAnd, inputs=List(b3681, b3564)).name("x7872").srcCtx("UnrollingBase.scala:28:66") } // And(b3681,b3564)
    val x7873 = withCtrl(x7878) { OpDef(op=BitAnd, inputs=List(x7872, b3560)).name("x7873").srcCtx("UnrollingBase.scala:28:66") } // And(x7872,b3560)
    val x7874 = withCtrl(x7878) { LoadBanks(List(x7639_d1_b0), List(b3559, b3677)).name("x7874").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:29:28:xCandidate") } // SRAMLoad(x7639,ArrayBuffer(Const(8), Const(1024)),List(b3559, b3677),Const(0),x7873)
    val x7875 = withCtrl(x7878) { LoadBanks(List(x7641_d1_b0), List(b3563, b3677)).name("x7875").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:30:28:hCandidate") } // SRAMLoad(x7641,ArrayBuffer(Const(2), Const(1024)),List(b3563, b3677),Const(0),x7873)
    val x7876_x7846_d0 = withCtrl(x7878) { WriteMem(x7846_d0, x7874).name("x7876_x7846_d0").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // RegWrite(x7846,x7874,x7873)
    val x7876_x7846_d1 = withCtrl(x7878) { WriteMem(x7846_d1, x7874).name("x7876_x7846_d1").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // RegWrite(x7846,x7874,x7873)
    val x7876_x7846_d2 = withCtrl(x7878) { WriteMem(x7846_d2, x7874).name("x7876_x7846_d2").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // RegWrite(x7846,x7874,x7873)
    val x7876_x7846_d3 = withCtrl(x7878) { WriteMem(x7846_d3, x7874).name("x7876_x7846_d3").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // RegWrite(x7846,x7874,x7873)
    val x7877_x7850_d0 = withCtrl(x7878) { WriteMem(x7850_d0, x7875).name("x7877_x7850_d0").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // RegWrite(x7850,x7875,x7873)
    val x7877_x7850_d1 = withCtrl(x7878) { WriteMem(x7850_d1, x7875).name("x7877_x7850_d1").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // RegWrite(x7850,x7875,x7873)
    val x7877_x7850_d2 = withCtrl(x7878) { WriteMem(x7850_d2, x7875).name("x7877_x7850_d2").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // RegWrite(x7850,x7875,x7873)
    val x7877_x7850_d3 = withCtrl(x7878) { WriteMem(x7850_d3, x7875).name("x7877_x7850_d3").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // RegWrite(x7850,x7875,x7873)
    val x8168 = withCtrl(x8211) { UnitController(style=ForkJoin, level=OuterControl).name("x8168").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b3564, b3560),Block(Const(())))
    val x7880 = withCtrl(x8168) { Counter(min=Const(0), max=Const(4), step=Const(1), par=4).name("x7880").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:31:28") } // CounterNew(Const(0),Const(4),Const(1),Const(4))
    val x7881 = withCtrl(x8168) { CounterChain(List(x7880)).name("x7881").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:31:41") } // CounterChainNew(List(x7880))
    val x7951 = withCtrl(x8168) { LoopController(style=MetaPipe, level=OuterControl, cchain=x7881).name("x7951").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:31:41") } // UnrolledForeach(List(b3678, b3564, b3560),x7881,Block(Const(())),List(List(b3731, b3732, b3733, b3734)),List(List(b3735, b3736, b3737, b3738)))
    val b3731 = withCtrl(x7951) { CounterIter(x7880, Some(0)).name("b3731") } // b3731
    val b3735 = withCtrl(x7951) { Const(true).name("b3735") } // b3735
    val b3732 = withCtrl(x7951) { CounterIter(x7880, Some(1)).name("b3732") } // b3732
    val b3736 = withCtrl(x7951) { Const(true).name("b3736") } // b3736
    val b3733 = withCtrl(x7951) { CounterIter(x7880, Some(2)).name("b3733") } // b3733
    val b3737 = withCtrl(x7951) { Const(true).name("b3737") } // b3737
    val b3734 = withCtrl(x7951) { CounterIter(x7880, Some(3)).name("b3734") } // b3734
    val b3738 = withCtrl(x7951) { Const(true).name("b3738") } // b3738
    val x7950 = withCtrl(x7951) { UnitController(style=ForkJoin, level=OuterControl).name("x7950").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b3678, b3564, b3560),Block(Const(())))
    val x7882 = withCtrl(x7950) { Counter(min=Const(0), max=Const(1024), step=Const(1), par=64).name("x7882").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:32:36") } // CounterNew(Const(0),Const(1024),Const(1),Const(64))
    val x7883 = withCtrl(x7950) { CounterChain(List(x7882)).name("x7883").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:32:54") } // CounterChainNew(List(x7882))
    val x7898 = withCtrl(x7950) { LoopController(style=InnerPipe, level=InnerControl, cchain=x7883).name("x7898").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:32:54") } // UnrolledForeach(List(b3735, b3678, b3564, b3560),x7883,Block(Const(())),List(List(b3747)),List(List(b3748)))
    val b3747 = withCtrl(x7898) { CounterIter(x7882, None).name("b3747") } // b3747
    val b3748 = withCtrl(x7898) { Const(true).name("b3748") } // b3748
    val x7884 = withCtrl(x7898) { OpDef(op=BitAnd, inputs=List(b3748, b3735)).name("x7884").srcCtx("UnrollingBase.scala:28:66") } // And(b3748,b3735)
    val x7885 = withCtrl(x7898) { OpDef(op=BitAnd, inputs=List(b3678, b3564)).name("x7885").srcCtx("UnrollingBase.scala:28:66") } // And(b3678,b3564)
    val x7886 = withCtrl(x7898) { OpDef(op=BitAnd, inputs=List(x7884, x7885)).name("x7886").srcCtx("UnrollingBase.scala:28:66") } // And(x7884,x7885)
    val x7887 = withCtrl(x7898) { OpDef(op=BitAnd, inputs=List(x7886, b3560)).name("x7887").srcCtx("UnrollingBase.scala:28:66") } // And(x7886,b3560)
    val x7888 = withCtrl(x7898) { LoadBanks(List(x7749_d0_b0), List(b3674, b3731, b3747)).name("x7888").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:33:30") } // ParSRAMLoad(x7749,List(List(b3674, b3731, b3747)),List(x7887))
    val x7889 = withCtrl(x7898) { x7888 } // VectorApply(x7888,0)
    val x7890 = withCtrl(x7898) { ReadMem(x7843_d0).name("x7890").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // RegRead(x7843)
    val x7891 = withCtrl(x7898) { OpDef(op=FixMul, inputs=List(x7889, x7890)).name("x7891").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:33:60:xResult") } // FixMul(x7889,x7890)
    val x7892 = withCtrl(x7898) { LoadBanks(List(x7750_d0_b0), List(b3674, b3731, b3747)).name("x7892").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:34:30") } // ParSRAMLoad(x7750,List(List(b3674, b3731, b3747)),List(x7887))
    val x7893 = withCtrl(x7898) { x7892 } // VectorApply(x7892,0)
    val x7894 = withCtrl(x7898) { ReadMem(x7847_d0).name("x7894").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // RegRead(x7847)
    val x7895 = withCtrl(x7898) { OpDef(op=FixMul, inputs=List(x7893, x7894)).name("x7895").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:34:60:hResult") } // FixMul(x7893,x7894)
    val x7896 = withCtrl(x7898) { OpDef(op=FixAdd, inputs=List(x7891, x7895)).name("x7896").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:35:48") } // FixAdd(x7891,x7895)
    val x7897 = withCtrl(x7898) { StoreBanks(List(List(x7839_d0_b0)), List(b3731, b3747), x7896).name("x7897").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:35:38") } // ParSRAMStore(x7839,List(List(b3731, b3747)),List(x7896),List(x7887))
    val x7899 = withCtrl(x7950) { Counter(min=Const(0), max=Const(1024), step=Const(1), par=64).name("x7899").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:32:36") } // CounterNew(Const(0),Const(1024),Const(1),Const(64))
    val x7900 = withCtrl(x7950) { CounterChain(List(x7899)).name("x7900").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:32:54") } // CounterChainNew(List(x7899))
    val x7915 = withCtrl(x7950) { LoopController(style=InnerPipe, level=InnerControl, cchain=x7900).name("x7915").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:32:54") } // UnrolledForeach(List(b3736, b3678, b3564, b3560),x7900,Block(Const(())),List(List(b3764)),List(List(b3765)))
    val b3764 = withCtrl(x7915) { CounterIter(x7899, None).name("b3764") } // b3764
    val b3765 = withCtrl(x7915) { Const(true).name("b3765") } // b3765
    val x7901 = withCtrl(x7915) { OpDef(op=BitAnd, inputs=List(b3765, b3736)).name("x7901").srcCtx("UnrollingBase.scala:28:66") } // And(b3765,b3736)
    val x7902 = withCtrl(x7915) { OpDef(op=BitAnd, inputs=List(b3678, b3564)).name("x7902").srcCtx("UnrollingBase.scala:28:66") } // And(b3678,b3564)
    val x7903 = withCtrl(x7915) { OpDef(op=BitAnd, inputs=List(x7901, x7902)).name("x7903").srcCtx("UnrollingBase.scala:28:66") } // And(x7901,x7902)
    val x7904 = withCtrl(x7915) { OpDef(op=BitAnd, inputs=List(x7903, b3560)).name("x7904").srcCtx("UnrollingBase.scala:28:66") } // And(x7903,b3560)
    val x7905 = withCtrl(x7915) { LoadBanks(List(x7749_d0_b1), List(b3674, b3732, b3764)).name("x7905").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:33:30") } // ParSRAMLoad(x7749,List(List(b3674, b3732, b3764)),List(x7904))
    val x7906 = withCtrl(x7915) { x7905 } // VectorApply(x7905,0)
    val x7907 = withCtrl(x7915) { ReadMem(x7843_d1).name("x7907").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // RegRead(x7843)
    val x7908 = withCtrl(x7915) { OpDef(op=FixMul, inputs=List(x7906, x7907)).name("x7908").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:33:60:xResult") } // FixMul(x7906,x7907)
    val x7909 = withCtrl(x7915) { LoadBanks(List(x7750_d0_b1), List(b3674, b3732, b3764)).name("x7909").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:34:30") } // ParSRAMLoad(x7750,List(List(b3674, b3732, b3764)),List(x7904))
    val x7910 = withCtrl(x7915) { x7909 } // VectorApply(x7909,0)
    val x7911 = withCtrl(x7915) { ReadMem(x7847_d1).name("x7911").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // RegRead(x7847)
    val x7912 = withCtrl(x7915) { OpDef(op=FixMul, inputs=List(x7910, x7911)).name("x7912").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:34:60:hResult") } // FixMul(x7910,x7911)
    val x7913 = withCtrl(x7915) { OpDef(op=FixAdd, inputs=List(x7908, x7912)).name("x7913").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:35:48") } // FixAdd(x7908,x7912)
    val x7914 = withCtrl(x7915) { StoreBanks(List(List(x7839_d0_b1)), List(b3732, b3764), x7913).name("x7914").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:35:38") } // ParSRAMStore(x7839,List(List(b3732, b3764)),List(x7913),List(x7904))
    val x7916 = withCtrl(x7950) { Counter(min=Const(0), max=Const(1024), step=Const(1), par=64).name("x7916").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:32:36") } // CounterNew(Const(0),Const(1024),Const(1),Const(64))
    val x7917 = withCtrl(x7950) { CounterChain(List(x7916)).name("x7917").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:32:54") } // CounterChainNew(List(x7916))
    val x7932 = withCtrl(x7950) { LoopController(style=InnerPipe, level=InnerControl, cchain=x7917).name("x7932").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:32:54") } // UnrolledForeach(List(b3737, b3678, b3564, b3560),x7917,Block(Const(())),List(List(b3781)),List(List(b3782)))
    val b3781 = withCtrl(x7932) { CounterIter(x7916, None).name("b3781") } // b3781
    val b3782 = withCtrl(x7932) { Const(true).name("b3782") } // b3782
    val x7918 = withCtrl(x7932) { OpDef(op=BitAnd, inputs=List(b3782, b3737)).name("x7918").srcCtx("UnrollingBase.scala:28:66") } // And(b3782,b3737)
    val x7919 = withCtrl(x7932) { OpDef(op=BitAnd, inputs=List(b3678, b3564)).name("x7919").srcCtx("UnrollingBase.scala:28:66") } // And(b3678,b3564)
    val x7920 = withCtrl(x7932) { OpDef(op=BitAnd, inputs=List(x7918, x7919)).name("x7920").srcCtx("UnrollingBase.scala:28:66") } // And(x7918,x7919)
    val x7921 = withCtrl(x7932) { OpDef(op=BitAnd, inputs=List(x7920, b3560)).name("x7921").srcCtx("UnrollingBase.scala:28:66") } // And(x7920,b3560)
    val x7922 = withCtrl(x7932) { LoadBanks(List(x7749_d0_b2), List(b3674, b3733, b3781)).name("x7922").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:33:30") } // ParSRAMLoad(x7749,List(List(b3674, b3733, b3781)),List(x7921))
    val x7923 = withCtrl(x7932) { x7922 } // VectorApply(x7922,0)
    val x7924 = withCtrl(x7932) { ReadMem(x7843_d2).name("x7924").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // RegRead(x7843)
    val x7925 = withCtrl(x7932) { OpDef(op=FixMul, inputs=List(x7923, x7924)).name("x7925").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:33:60:xResult") } // FixMul(x7923,x7924)
    val x7926 = withCtrl(x7932) { LoadBanks(List(x7750_d0_b2), List(b3674, b3733, b3781)).name("x7926").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:34:30") } // ParSRAMLoad(x7750,List(List(b3674, b3733, b3781)),List(x7921))
    val x7927 = withCtrl(x7932) { x7926 } // VectorApply(x7926,0)
    val x7928 = withCtrl(x7932) { ReadMem(x7847_d2).name("x7928").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // RegRead(x7847)
    val x7929 = withCtrl(x7932) { OpDef(op=FixMul, inputs=List(x7927, x7928)).name("x7929").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:34:60:hResult") } // FixMul(x7927,x7928)
    val x7930 = withCtrl(x7932) { OpDef(op=FixAdd, inputs=List(x7925, x7929)).name("x7930").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:35:48") } // FixAdd(x7925,x7929)
    val x7931 = withCtrl(x7932) { StoreBanks(List(List(x7839_d0_b2)), List(b3733, b3781), x7930).name("x7931").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:35:38") } // ParSRAMStore(x7839,List(List(b3733, b3781)),List(x7930),List(x7921))
    val x7933 = withCtrl(x7950) { Counter(min=Const(0), max=Const(1024), step=Const(1), par=64).name("x7933").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:32:36") } // CounterNew(Const(0),Const(1024),Const(1),Const(64))
    val x7934 = withCtrl(x7950) { CounterChain(List(x7933)).name("x7934").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:32:54") } // CounterChainNew(List(x7933))
    val x7949 = withCtrl(x7950) { LoopController(style=InnerPipe, level=InnerControl, cchain=x7934).name("x7949").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:32:54") } // UnrolledForeach(List(b3738, b3678, b3564, b3560),x7934,Block(Const(())),List(List(b3798)),List(List(b3799)))
    val b3798 = withCtrl(x7949) { CounterIter(x7933, None).name("b3798") } // b3798
    val b3799 = withCtrl(x7949) { Const(true).name("b3799") } // b3799
    val x7935 = withCtrl(x7949) { OpDef(op=BitAnd, inputs=List(b3799, b3738)).name("x7935").srcCtx("UnrollingBase.scala:28:66") } // And(b3799,b3738)
    val x7936 = withCtrl(x7949) { OpDef(op=BitAnd, inputs=List(b3678, b3564)).name("x7936").srcCtx("UnrollingBase.scala:28:66") } // And(b3678,b3564)
    val x7937 = withCtrl(x7949) { OpDef(op=BitAnd, inputs=List(x7935, x7936)).name("x7937").srcCtx("UnrollingBase.scala:28:66") } // And(x7935,x7936)
    val x7938 = withCtrl(x7949) { OpDef(op=BitAnd, inputs=List(x7937, b3560)).name("x7938").srcCtx("UnrollingBase.scala:28:66") } // And(x7937,b3560)
    val x7939 = withCtrl(x7949) { LoadBanks(List(x7749_d0_b3), List(b3674, b3734, b3798)).name("x7939").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:33:30") } // ParSRAMLoad(x7749,List(List(b3674, b3734, b3798)),List(x7938))
    val x7940 = withCtrl(x7949) { x7939 } // VectorApply(x7939,0)
    val x7941 = withCtrl(x7949) { ReadMem(x7843_d3).name("x7941").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // RegRead(x7843)
    val x7942 = withCtrl(x7949) { OpDef(op=FixMul, inputs=List(x7940, x7941)).name("x7942").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:33:60:xResult") } // FixMul(x7940,x7941)
    val x7943 = withCtrl(x7949) { LoadBanks(List(x7750_d0_b3), List(b3674, b3734, b3798)).name("x7943").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:34:30") } // ParSRAMLoad(x7750,List(List(b3674, b3734, b3798)),List(x7938))
    val x7944 = withCtrl(x7949) { x7943 } // VectorApply(x7943,0)
    val x7945 = withCtrl(x7949) { ReadMem(x7847_d3).name("x7945").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // RegRead(x7847)
    val x7946 = withCtrl(x7949) { OpDef(op=FixMul, inputs=List(x7944, x7945)).name("x7946").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:34:60:hResult") } // FixMul(x7944,x7945)
    val x7947 = withCtrl(x7949) { OpDef(op=FixAdd, inputs=List(x7942, x7946)).name("x7947").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:35:48") } // FixAdd(x7942,x7946)
    val x7948 = withCtrl(x7949) { StoreBanks(List(List(x7839_d0_b3)), List(b3734, b3798), x7947).name("x7948").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:35:38") } // ParSRAMStore(x7839,List(List(b3734, b3798)),List(x7947),List(x7938))
    val x7952 = withCtrl(x8168) { Counter(min=Const(0), max=Const(4), step=Const(1), par=4).name("x7952").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:31:28") } // CounterNew(Const(0),Const(4),Const(1),Const(4))
    val x7953 = withCtrl(x8168) { CounterChain(List(x7952)).name("x7953").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:31:41") } // CounterChainNew(List(x7952))
    val x8023 = withCtrl(x8168) { LoopController(style=MetaPipe, level=OuterControl, cchain=x7953).name("x8023").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:31:41") } // UnrolledForeach(List(b3679, b3564, b3560),x7953,Block(Const(())),List(List(b3817, b3818, b3819, b3820)),List(List(b3821, b3822, b3823, b3824)))
    val b3817 = withCtrl(x8023) { CounterIter(x7952, Some(0)).name("b3817") } // b3817
    val b3821 = withCtrl(x8023) { Const(true).name("b3821") } // b3821
    val b3818 = withCtrl(x8023) { CounterIter(x7952, Some(1)).name("b3818") } // b3818
    val b3822 = withCtrl(x8023) { Const(true).name("b3822") } // b3822
    val b3819 = withCtrl(x8023) { CounterIter(x7952, Some(2)).name("b3819") } // b3819
    val b3823 = withCtrl(x8023) { Const(true).name("b3823") } // b3823
    val b3820 = withCtrl(x8023) { CounterIter(x7952, Some(3)).name("b3820") } // b3820
    val b3824 = withCtrl(x8023) { Const(true).name("b3824") } // b3824
    val x8022 = withCtrl(x8023) { UnitController(style=ForkJoin, level=OuterControl).name("x8022").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b3679, b3564, b3560),Block(Const(())))
    val x7954 = withCtrl(x8022) { Counter(min=Const(0), max=Const(1024), step=Const(1), par=64).name("x7954").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:32:36") } // CounterNew(Const(0),Const(1024),Const(1),Const(64))
    val x7955 = withCtrl(x8022) { CounterChain(List(x7954)).name("x7955").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:32:54") } // CounterChainNew(List(x7954))
    val x7970 = withCtrl(x8022) { LoopController(style=InnerPipe, level=InnerControl, cchain=x7955).name("x7970").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:32:54") } // UnrolledForeach(List(b3821, b3679, b3564, b3560),x7955,Block(Const(())),List(List(b3833)),List(List(b3834)))
    val b3833 = withCtrl(x7970) { CounterIter(x7954, None).name("b3833") } // b3833
    val b3834 = withCtrl(x7970) { Const(true).name("b3834") } // b3834
    val x7956 = withCtrl(x7970) { OpDef(op=BitAnd, inputs=List(b3834, b3821)).name("x7956").srcCtx("UnrollingBase.scala:28:66") } // And(b3834,b3821)
    val x7957 = withCtrl(x7970) { OpDef(op=BitAnd, inputs=List(b3679, b3564)).name("x7957").srcCtx("UnrollingBase.scala:28:66") } // And(b3679,b3564)
    val x7958 = withCtrl(x7970) { OpDef(op=BitAnd, inputs=List(x7956, x7957)).name("x7958").srcCtx("UnrollingBase.scala:28:66") } // And(x7956,x7957)
    val x7959 = withCtrl(x7970) { OpDef(op=BitAnd, inputs=List(x7958, b3560)).name("x7959").srcCtx("UnrollingBase.scala:28:66") } // And(x7958,b3560)
    val x7960 = withCtrl(x7970) { LoadBanks(List(x7749_d0_b4), List(b3675, b3817, b3833)).name("x7960").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:33:30") } // ParSRAMLoad(x7749,List(List(b3675, b3817, b3833)),List(x7959))
    val x7961 = withCtrl(x7970) { x7960 } // VectorApply(x7960,0)
    val x7962 = withCtrl(x7970) { ReadMem(x7844_d0).name("x7962").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // RegRead(x7844)
    val x7963 = withCtrl(x7970) { OpDef(op=FixMul, inputs=List(x7961, x7962)).name("x7963").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:33:60:xResult") } // FixMul(x7961,x7962)
    val x7964 = withCtrl(x7970) { LoadBanks(List(x7750_d0_b4), List(b3675, b3817, b3833)).name("x7964").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:34:30") } // ParSRAMLoad(x7750,List(List(b3675, b3817, b3833)),List(x7959))
    val x7965 = withCtrl(x7970) { x7964 } // VectorApply(x7964,0)
    val x7966 = withCtrl(x7970) { ReadMem(x7848_d0).name("x7966").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // RegRead(x7848)
    val x7967 = withCtrl(x7970) { OpDef(op=FixMul, inputs=List(x7965, x7966)).name("x7967").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:34:60:hResult") } // FixMul(x7965,x7966)
    val x7968 = withCtrl(x7970) { OpDef(op=FixAdd, inputs=List(x7963, x7967)).name("x7968").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:35:48") } // FixAdd(x7963,x7967)
    val x7969 = withCtrl(x7970) { StoreBanks(List(List(x7840_d0_b0)), List(b3817, b3833), x7968).name("x7969").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:35:38") } // ParSRAMStore(x7840,List(List(b3817, b3833)),List(x7968),List(x7959))
    val x7971 = withCtrl(x8022) { Counter(min=Const(0), max=Const(1024), step=Const(1), par=64).name("x7971").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:32:36") } // CounterNew(Const(0),Const(1024),Const(1),Const(64))
    val x7972 = withCtrl(x8022) { CounterChain(List(x7971)).name("x7972").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:32:54") } // CounterChainNew(List(x7971))
    val x7987 = withCtrl(x8022) { LoopController(style=InnerPipe, level=InnerControl, cchain=x7972).name("x7987").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:32:54") } // UnrolledForeach(List(b3822, b3679, b3564, b3560),x7972,Block(Const(())),List(List(b3850)),List(List(b3851)))
    val b3850 = withCtrl(x7987) { CounterIter(x7971, None).name("b3850") } // b3850
    val b3851 = withCtrl(x7987) { Const(true).name("b3851") } // b3851
    val x7973 = withCtrl(x7987) { OpDef(op=BitAnd, inputs=List(b3851, b3822)).name("x7973").srcCtx("UnrollingBase.scala:28:66") } // And(b3851,b3822)
    val x7974 = withCtrl(x7987) { OpDef(op=BitAnd, inputs=List(b3679, b3564)).name("x7974").srcCtx("UnrollingBase.scala:28:66") } // And(b3679,b3564)
    val x7975 = withCtrl(x7987) { OpDef(op=BitAnd, inputs=List(x7973, x7974)).name("x7975").srcCtx("UnrollingBase.scala:28:66") } // And(x7973,x7974)
    val x7976 = withCtrl(x7987) { OpDef(op=BitAnd, inputs=List(x7975, b3560)).name("x7976").srcCtx("UnrollingBase.scala:28:66") } // And(x7975,b3560)
    val x7977 = withCtrl(x7987) { LoadBanks(List(x7749_d0_b5), List(b3675, b3818, b3850)).name("x7977").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:33:30") } // ParSRAMLoad(x7749,List(List(b3675, b3818, b3850)),List(x7976))
    val x7978 = withCtrl(x7987) { x7977 } // VectorApply(x7977,0)
    val x7979 = withCtrl(x7987) { ReadMem(x7844_d1).name("x7979").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // RegRead(x7844)
    val x7980 = withCtrl(x7987) { OpDef(op=FixMul, inputs=List(x7978, x7979)).name("x7980").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:33:60:xResult") } // FixMul(x7978,x7979)
    val x7981 = withCtrl(x7987) { LoadBanks(List(x7750_d0_b5), List(b3675, b3818, b3850)).name("x7981").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:34:30") } // ParSRAMLoad(x7750,List(List(b3675, b3818, b3850)),List(x7976))
    val x7982 = withCtrl(x7987) { x7981 } // VectorApply(x7981,0)
    val x7983 = withCtrl(x7987) { ReadMem(x7848_d1).name("x7983").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // RegRead(x7848)
    val x7984 = withCtrl(x7987) { OpDef(op=FixMul, inputs=List(x7982, x7983)).name("x7984").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:34:60:hResult") } // FixMul(x7982,x7983)
    val x7985 = withCtrl(x7987) { OpDef(op=FixAdd, inputs=List(x7980, x7984)).name("x7985").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:35:48") } // FixAdd(x7980,x7984)
    val x7986 = withCtrl(x7987) { StoreBanks(List(List(x7840_d0_b1)), List(b3818, b3850), x7985).name("x7986").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:35:38") } // ParSRAMStore(x7840,List(List(b3818, b3850)),List(x7985),List(x7976))
    val x7988 = withCtrl(x8022) { Counter(min=Const(0), max=Const(1024), step=Const(1), par=64).name("x7988").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:32:36") } // CounterNew(Const(0),Const(1024),Const(1),Const(64))
    val x7989 = withCtrl(x8022) { CounterChain(List(x7988)).name("x7989").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:32:54") } // CounterChainNew(List(x7988))
    val x8004 = withCtrl(x8022) { LoopController(style=InnerPipe, level=InnerControl, cchain=x7989).name("x8004").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:32:54") } // UnrolledForeach(List(b3823, b3679, b3564, b3560),x7989,Block(Const(())),List(List(b3867)),List(List(b3868)))
    val b3867 = withCtrl(x8004) { CounterIter(x7988, None).name("b3867") } // b3867
    val b3868 = withCtrl(x8004) { Const(true).name("b3868") } // b3868
    val x7990 = withCtrl(x8004) { OpDef(op=BitAnd, inputs=List(b3868, b3823)).name("x7990").srcCtx("UnrollingBase.scala:28:66") } // And(b3868,b3823)
    val x7991 = withCtrl(x8004) { OpDef(op=BitAnd, inputs=List(b3679, b3564)).name("x7991").srcCtx("UnrollingBase.scala:28:66") } // And(b3679,b3564)
    val x7992 = withCtrl(x8004) { OpDef(op=BitAnd, inputs=List(x7990, x7991)).name("x7992").srcCtx("UnrollingBase.scala:28:66") } // And(x7990,x7991)
    val x7993 = withCtrl(x8004) { OpDef(op=BitAnd, inputs=List(x7992, b3560)).name("x7993").srcCtx("UnrollingBase.scala:28:66") } // And(x7992,b3560)
    val x7994 = withCtrl(x8004) { LoadBanks(List(x7749_d0_b6), List(b3675, b3819, b3867)).name("x7994").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:33:30") } // ParSRAMLoad(x7749,List(List(b3675, b3819, b3867)),List(x7993))
    val x7995 = withCtrl(x8004) { x7994 } // VectorApply(x7994,0)
    val x7996 = withCtrl(x8004) { ReadMem(x7844_d2).name("x7996").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // RegRead(x7844)
    val x7997 = withCtrl(x8004) { OpDef(op=FixMul, inputs=List(x7995, x7996)).name("x7997").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:33:60:xResult") } // FixMul(x7995,x7996)
    val x7998 = withCtrl(x8004) { LoadBanks(List(x7750_d0_b6), List(b3675, b3819, b3867)).name("x7998").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:34:30") } // ParSRAMLoad(x7750,List(List(b3675, b3819, b3867)),List(x7993))
    val x7999 = withCtrl(x8004) { x7998 } // VectorApply(x7998,0)
    val x8000 = withCtrl(x8004) { ReadMem(x7848_d2).name("x8000").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // RegRead(x7848)
    val x8001 = withCtrl(x8004) { OpDef(op=FixMul, inputs=List(x7999, x8000)).name("x8001").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:34:60:hResult") } // FixMul(x7999,x8000)
    val x8002 = withCtrl(x8004) { OpDef(op=FixAdd, inputs=List(x7997, x8001)).name("x8002").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:35:48") } // FixAdd(x7997,x8001)
    val x8003 = withCtrl(x8004) { StoreBanks(List(List(x7840_d0_b2)), List(b3819, b3867), x8002).name("x8003").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:35:38") } // ParSRAMStore(x7840,List(List(b3819, b3867)),List(x8002),List(x7993))
    val x8005 = withCtrl(x8022) { Counter(min=Const(0), max=Const(1024), step=Const(1), par=64).name("x8005").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:32:36") } // CounterNew(Const(0),Const(1024),Const(1),Const(64))
    val x8006 = withCtrl(x8022) { CounterChain(List(x8005)).name("x8006").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:32:54") } // CounterChainNew(List(x8005))
    val x8021 = withCtrl(x8022) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8006).name("x8021").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:32:54") } // UnrolledForeach(List(b3824, b3679, b3564, b3560),x8006,Block(Const(())),List(List(b3884)),List(List(b3885)))
    val b3884 = withCtrl(x8021) { CounterIter(x8005, None).name("b3884") } // b3884
    val b3885 = withCtrl(x8021) { Const(true).name("b3885") } // b3885
    val x8007 = withCtrl(x8021) { OpDef(op=BitAnd, inputs=List(b3885, b3824)).name("x8007").srcCtx("UnrollingBase.scala:28:66") } // And(b3885,b3824)
    val x8008 = withCtrl(x8021) { OpDef(op=BitAnd, inputs=List(b3679, b3564)).name("x8008").srcCtx("UnrollingBase.scala:28:66") } // And(b3679,b3564)
    val x8009 = withCtrl(x8021) { OpDef(op=BitAnd, inputs=List(x8007, x8008)).name("x8009").srcCtx("UnrollingBase.scala:28:66") } // And(x8007,x8008)
    val x8010 = withCtrl(x8021) { OpDef(op=BitAnd, inputs=List(x8009, b3560)).name("x8010").srcCtx("UnrollingBase.scala:28:66") } // And(x8009,b3560)
    val x8011 = withCtrl(x8021) { LoadBanks(List(x7749_d0_b7), List(b3675, b3820, b3884)).name("x8011").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:33:30") } // ParSRAMLoad(x7749,List(List(b3675, b3820, b3884)),List(x8010))
    val x8012 = withCtrl(x8021) { x8011 } // VectorApply(x8011,0)
    val x8013 = withCtrl(x8021) { ReadMem(x7844_d3).name("x8013").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // RegRead(x7844)
    val x8014 = withCtrl(x8021) { OpDef(op=FixMul, inputs=List(x8012, x8013)).name("x8014").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:33:60:xResult") } // FixMul(x8012,x8013)
    val x8015 = withCtrl(x8021) { LoadBanks(List(x7750_d0_b7), List(b3675, b3820, b3884)).name("x8015").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:34:30") } // ParSRAMLoad(x7750,List(List(b3675, b3820, b3884)),List(x8010))
    val x8016 = withCtrl(x8021) { x8015 } // VectorApply(x8015,0)
    val x8017 = withCtrl(x8021) { ReadMem(x7848_d3).name("x8017").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // RegRead(x7848)
    val x8018 = withCtrl(x8021) { OpDef(op=FixMul, inputs=List(x8016, x8017)).name("x8018").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:34:60:hResult") } // FixMul(x8016,x8017)
    val x8019 = withCtrl(x8021) { OpDef(op=FixAdd, inputs=List(x8014, x8018)).name("x8019").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:35:48") } // FixAdd(x8014,x8018)
    val x8020 = withCtrl(x8021) { StoreBanks(List(List(x7840_d0_b3)), List(b3820, b3884), x8019).name("x8020").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:35:38") } // ParSRAMStore(x7840,List(List(b3820, b3884)),List(x8019),List(x8010))
    val x8024 = withCtrl(x8168) { Counter(min=Const(0), max=Const(4), step=Const(1), par=4).name("x8024").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:31:28") } // CounterNew(Const(0),Const(4),Const(1),Const(4))
    val x8025 = withCtrl(x8168) { CounterChain(List(x8024)).name("x8025").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:31:41") } // CounterChainNew(List(x8024))
    val x8095 = withCtrl(x8168) { LoopController(style=MetaPipe, level=OuterControl, cchain=x8025).name("x8095").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:31:41") } // UnrolledForeach(List(b3680, b3564, b3560),x8025,Block(Const(())),List(List(b3903, b3904, b3905, b3906)),List(List(b3907, b3908, b3909, b3910)))
    val b3903 = withCtrl(x8095) { CounterIter(x8024, Some(0)).name("b3903") } // b3903
    val b3907 = withCtrl(x8095) { Const(true).name("b3907") } // b3907
    val b3904 = withCtrl(x8095) { CounterIter(x8024, Some(1)).name("b3904") } // b3904
    val b3908 = withCtrl(x8095) { Const(true).name("b3908") } // b3908
    val b3905 = withCtrl(x8095) { CounterIter(x8024, Some(2)).name("b3905") } // b3905
    val b3909 = withCtrl(x8095) { Const(true).name("b3909") } // b3909
    val b3906 = withCtrl(x8095) { CounterIter(x8024, Some(3)).name("b3906") } // b3906
    val b3910 = withCtrl(x8095) { Const(true).name("b3910") } // b3910
    val x8094 = withCtrl(x8095) { UnitController(style=ForkJoin, level=OuterControl).name("x8094").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b3680, b3564, b3560),Block(Const(())))
    val x8026 = withCtrl(x8094) { Counter(min=Const(0), max=Const(1024), step=Const(1), par=64).name("x8026").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:32:36") } // CounterNew(Const(0),Const(1024),Const(1),Const(64))
    val x8027 = withCtrl(x8094) { CounterChain(List(x8026)).name("x8027").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:32:54") } // CounterChainNew(List(x8026))
    val x8042 = withCtrl(x8094) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8027).name("x8042").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:32:54") } // UnrolledForeach(List(b3907, b3680, b3564, b3560),x8027,Block(Const(())),List(List(b3919)),List(List(b3920)))
    val b3919 = withCtrl(x8042) { CounterIter(x8026, None).name("b3919") } // b3919
    val b3920 = withCtrl(x8042) { Const(true).name("b3920") } // b3920
    val x8028 = withCtrl(x8042) { OpDef(op=BitAnd, inputs=List(b3920, b3907)).name("x8028").srcCtx("UnrollingBase.scala:28:66") } // And(b3920,b3907)
    val x8029 = withCtrl(x8042) { OpDef(op=BitAnd, inputs=List(b3680, b3564)).name("x8029").srcCtx("UnrollingBase.scala:28:66") } // And(b3680,b3564)
    val x8030 = withCtrl(x8042) { OpDef(op=BitAnd, inputs=List(x8028, x8029)).name("x8030").srcCtx("UnrollingBase.scala:28:66") } // And(x8028,x8029)
    val x8031 = withCtrl(x8042) { OpDef(op=BitAnd, inputs=List(x8030, b3560)).name("x8031").srcCtx("UnrollingBase.scala:28:66") } // And(x8030,b3560)
    val x8032 = withCtrl(x8042) { LoadBanks(List(x7749_d0_b8), List(b3676, b3903, b3919)).name("x8032").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:33:30") } // ParSRAMLoad(x7749,List(List(b3676, b3903, b3919)),List(x8031))
    val x8033 = withCtrl(x8042) { x8032 } // VectorApply(x8032,0)
    val x8034 = withCtrl(x8042) { ReadMem(x7845_d0).name("x8034").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // RegRead(x7845)
    val x8035 = withCtrl(x8042) { OpDef(op=FixMul, inputs=List(x8033, x8034)).name("x8035").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:33:60:xResult") } // FixMul(x8033,x8034)
    val x8036 = withCtrl(x8042) { LoadBanks(List(x7750_d0_b8), List(b3676, b3903, b3919)).name("x8036").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:34:30") } // ParSRAMLoad(x7750,List(List(b3676, b3903, b3919)),List(x8031))
    val x8037 = withCtrl(x8042) { x8036 } // VectorApply(x8036,0)
    val x8038 = withCtrl(x8042) { ReadMem(x7849_d0).name("x8038").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // RegRead(x7849)
    val x8039 = withCtrl(x8042) { OpDef(op=FixMul, inputs=List(x8037, x8038)).name("x8039").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:34:60:hResult") } // FixMul(x8037,x8038)
    val x8040 = withCtrl(x8042) { OpDef(op=FixAdd, inputs=List(x8035, x8039)).name("x8040").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:35:48") } // FixAdd(x8035,x8039)
    val x8041 = withCtrl(x8042) { StoreBanks(List(List(x7841_d0_b0)), List(b3903, b3919), x8040).name("x8041").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:35:38") } // ParSRAMStore(x7841,List(List(b3903, b3919)),List(x8040),List(x8031))
    val x8043 = withCtrl(x8094) { Counter(min=Const(0), max=Const(1024), step=Const(1), par=64).name("x8043").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:32:36") } // CounterNew(Const(0),Const(1024),Const(1),Const(64))
    val x8044 = withCtrl(x8094) { CounterChain(List(x8043)).name("x8044").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:32:54") } // CounterChainNew(List(x8043))
    val x8059 = withCtrl(x8094) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8044).name("x8059").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:32:54") } // UnrolledForeach(List(b3908, b3680, b3564, b3560),x8044,Block(Const(())),List(List(b3936)),List(List(b3937)))
    val b3936 = withCtrl(x8059) { CounterIter(x8043, None).name("b3936") } // b3936
    val b3937 = withCtrl(x8059) { Const(true).name("b3937") } // b3937
    val x8045 = withCtrl(x8059) { OpDef(op=BitAnd, inputs=List(b3937, b3908)).name("x8045").srcCtx("UnrollingBase.scala:28:66") } // And(b3937,b3908)
    val x8046 = withCtrl(x8059) { OpDef(op=BitAnd, inputs=List(b3680, b3564)).name("x8046").srcCtx("UnrollingBase.scala:28:66") } // And(b3680,b3564)
    val x8047 = withCtrl(x8059) { OpDef(op=BitAnd, inputs=List(x8045, x8046)).name("x8047").srcCtx("UnrollingBase.scala:28:66") } // And(x8045,x8046)
    val x8048 = withCtrl(x8059) { OpDef(op=BitAnd, inputs=List(x8047, b3560)).name("x8048").srcCtx("UnrollingBase.scala:28:66") } // And(x8047,b3560)
    val x8049 = withCtrl(x8059) { LoadBanks(List(x7749_d0_b9), List(b3676, b3904, b3936)).name("x8049").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:33:30") } // ParSRAMLoad(x7749,List(List(b3676, b3904, b3936)),List(x8048))
    val x8050 = withCtrl(x8059) { x8049 } // VectorApply(x8049,0)
    val x8051 = withCtrl(x8059) { ReadMem(x7845_d1).name("x8051").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // RegRead(x7845)
    val x8052 = withCtrl(x8059) { OpDef(op=FixMul, inputs=List(x8050, x8051)).name("x8052").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:33:60:xResult") } // FixMul(x8050,x8051)
    val x8053 = withCtrl(x8059) { LoadBanks(List(x7750_d0_b9), List(b3676, b3904, b3936)).name("x8053").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:34:30") } // ParSRAMLoad(x7750,List(List(b3676, b3904, b3936)),List(x8048))
    val x8054 = withCtrl(x8059) { x8053 } // VectorApply(x8053,0)
    val x8055 = withCtrl(x8059) { ReadMem(x7849_d1).name("x8055").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // RegRead(x7849)
    val x8056 = withCtrl(x8059) { OpDef(op=FixMul, inputs=List(x8054, x8055)).name("x8056").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:34:60:hResult") } // FixMul(x8054,x8055)
    val x8057 = withCtrl(x8059) { OpDef(op=FixAdd, inputs=List(x8052, x8056)).name("x8057").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:35:48") } // FixAdd(x8052,x8056)
    val x8058 = withCtrl(x8059) { StoreBanks(List(List(x7841_d0_b1)), List(b3904, b3936), x8057).name("x8058").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:35:38") } // ParSRAMStore(x7841,List(List(b3904, b3936)),List(x8057),List(x8048))
    val x8060 = withCtrl(x8094) { Counter(min=Const(0), max=Const(1024), step=Const(1), par=64).name("x8060").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:32:36") } // CounterNew(Const(0),Const(1024),Const(1),Const(64))
    val x8061 = withCtrl(x8094) { CounterChain(List(x8060)).name("x8061").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:32:54") } // CounterChainNew(List(x8060))
    val x8076 = withCtrl(x8094) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8061).name("x8076").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:32:54") } // UnrolledForeach(List(b3909, b3680, b3564, b3560),x8061,Block(Const(())),List(List(b3953)),List(List(b3954)))
    val b3953 = withCtrl(x8076) { CounterIter(x8060, None).name("b3953") } // b3953
    val b3954 = withCtrl(x8076) { Const(true).name("b3954") } // b3954
    val x8062 = withCtrl(x8076) { OpDef(op=BitAnd, inputs=List(b3954, b3909)).name("x8062").srcCtx("UnrollingBase.scala:28:66") } // And(b3954,b3909)
    val x8063 = withCtrl(x8076) { OpDef(op=BitAnd, inputs=List(b3680, b3564)).name("x8063").srcCtx("UnrollingBase.scala:28:66") } // And(b3680,b3564)
    val x8064 = withCtrl(x8076) { OpDef(op=BitAnd, inputs=List(x8062, x8063)).name("x8064").srcCtx("UnrollingBase.scala:28:66") } // And(x8062,x8063)
    val x8065 = withCtrl(x8076) { OpDef(op=BitAnd, inputs=List(x8064, b3560)).name("x8065").srcCtx("UnrollingBase.scala:28:66") } // And(x8064,b3560)
    val x8066 = withCtrl(x8076) { LoadBanks(List(x7749_d0_b10), List(b3676, b3905, b3953)).name("x8066").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:33:30") } // ParSRAMLoad(x7749,List(List(b3676, b3905, b3953)),List(x8065))
    val x8067 = withCtrl(x8076) { x8066 } // VectorApply(x8066,0)
    val x8068 = withCtrl(x8076) { ReadMem(x7845_d2).name("x8068").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // RegRead(x7845)
    val x8069 = withCtrl(x8076) { OpDef(op=FixMul, inputs=List(x8067, x8068)).name("x8069").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:33:60:xResult") } // FixMul(x8067,x8068)
    val x8070 = withCtrl(x8076) { LoadBanks(List(x7750_d0_b10), List(b3676, b3905, b3953)).name("x8070").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:34:30") } // ParSRAMLoad(x7750,List(List(b3676, b3905, b3953)),List(x8065))
    val x8071 = withCtrl(x8076) { x8070 } // VectorApply(x8070,0)
    val x8072 = withCtrl(x8076) { ReadMem(x7849_d2).name("x8072").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // RegRead(x7849)
    val x8073 = withCtrl(x8076) { OpDef(op=FixMul, inputs=List(x8071, x8072)).name("x8073").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:34:60:hResult") } // FixMul(x8071,x8072)
    val x8074 = withCtrl(x8076) { OpDef(op=FixAdd, inputs=List(x8069, x8073)).name("x8074").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:35:48") } // FixAdd(x8069,x8073)
    val x8075 = withCtrl(x8076) { StoreBanks(List(List(x7841_d0_b2)), List(b3905, b3953), x8074).name("x8075").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:35:38") } // ParSRAMStore(x7841,List(List(b3905, b3953)),List(x8074),List(x8065))
    val x8077 = withCtrl(x8094) { Counter(min=Const(0), max=Const(1024), step=Const(1), par=64).name("x8077").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:32:36") } // CounterNew(Const(0),Const(1024),Const(1),Const(64))
    val x8078 = withCtrl(x8094) { CounterChain(List(x8077)).name("x8078").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:32:54") } // CounterChainNew(List(x8077))
    val x8093 = withCtrl(x8094) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8078).name("x8093").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:32:54") } // UnrolledForeach(List(b3910, b3680, b3564, b3560),x8078,Block(Const(())),List(List(b3970)),List(List(b3971)))
    val b3970 = withCtrl(x8093) { CounterIter(x8077, None).name("b3970") } // b3970
    val b3971 = withCtrl(x8093) { Const(true).name("b3971") } // b3971
    val x8079 = withCtrl(x8093) { OpDef(op=BitAnd, inputs=List(b3971, b3910)).name("x8079").srcCtx("UnrollingBase.scala:28:66") } // And(b3971,b3910)
    val x8080 = withCtrl(x8093) { OpDef(op=BitAnd, inputs=List(b3680, b3564)).name("x8080").srcCtx("UnrollingBase.scala:28:66") } // And(b3680,b3564)
    val x8081 = withCtrl(x8093) { OpDef(op=BitAnd, inputs=List(x8079, x8080)).name("x8081").srcCtx("UnrollingBase.scala:28:66") } // And(x8079,x8080)
    val x8082 = withCtrl(x8093) { OpDef(op=BitAnd, inputs=List(x8081, b3560)).name("x8082").srcCtx("UnrollingBase.scala:28:66") } // And(x8081,b3560)
    val x8083 = withCtrl(x8093) { LoadBanks(List(x7749_d0_b11), List(b3676, b3906, b3970)).name("x8083").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:33:30") } // ParSRAMLoad(x7749,List(List(b3676, b3906, b3970)),List(x8082))
    val x8084 = withCtrl(x8093) { x8083 } // VectorApply(x8083,0)
    val x8085 = withCtrl(x8093) { ReadMem(x7845_d3).name("x8085").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // RegRead(x7845)
    val x8086 = withCtrl(x8093) { OpDef(op=FixMul, inputs=List(x8084, x8085)).name("x8086").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:33:60:xResult") } // FixMul(x8084,x8085)
    val x8087 = withCtrl(x8093) { LoadBanks(List(x7750_d0_b11), List(b3676, b3906, b3970)).name("x8087").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:34:30") } // ParSRAMLoad(x7750,List(List(b3676, b3906, b3970)),List(x8082))
    val x8088 = withCtrl(x8093) { x8087 } // VectorApply(x8087,0)
    val x8089 = withCtrl(x8093) { ReadMem(x7849_d3).name("x8089").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // RegRead(x7849)
    val x8090 = withCtrl(x8093) { OpDef(op=FixMul, inputs=List(x8088, x8089)).name("x8090").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:34:60:hResult") } // FixMul(x8088,x8089)
    val x8091 = withCtrl(x8093) { OpDef(op=FixAdd, inputs=List(x8086, x8090)).name("x8091").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:35:48") } // FixAdd(x8086,x8090)
    val x8092 = withCtrl(x8093) { StoreBanks(List(List(x7841_d0_b3)), List(b3906, b3970), x8091).name("x8092").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:35:38") } // ParSRAMStore(x7841,List(List(b3906, b3970)),List(x8091),List(x8082))
    val x8096 = withCtrl(x8168) { Counter(min=Const(0), max=Const(4), step=Const(1), par=4).name("x8096").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:31:28") } // CounterNew(Const(0),Const(4),Const(1),Const(4))
    val x8097 = withCtrl(x8168) { CounterChain(List(x8096)).name("x8097").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:31:41") } // CounterChainNew(List(x8096))
    val x8167 = withCtrl(x8168) { LoopController(style=MetaPipe, level=OuterControl, cchain=x8097).name("x8167").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:31:41") } // UnrolledForeach(List(b3681, b3564, b3560),x8097,Block(Const(())),List(List(b3989, b3990, b3991, b3992)),List(List(b3993, b3994, b3995, b3996)))
    val b3989 = withCtrl(x8167) { CounterIter(x8096, Some(0)).name("b3989") } // b3989
    val b3993 = withCtrl(x8167) { Const(true).name("b3993") } // b3993
    val b3990 = withCtrl(x8167) { CounterIter(x8096, Some(1)).name("b3990") } // b3990
    val b3994 = withCtrl(x8167) { Const(true).name("b3994") } // b3994
    val b3991 = withCtrl(x8167) { CounterIter(x8096, Some(2)).name("b3991") } // b3991
    val b3995 = withCtrl(x8167) { Const(true).name("b3995") } // b3995
    val b3992 = withCtrl(x8167) { CounterIter(x8096, Some(3)).name("b3992") } // b3992
    val b3996 = withCtrl(x8167) { Const(true).name("b3996") } // b3996
    val x8166 = withCtrl(x8167) { UnitController(style=ForkJoin, level=OuterControl).name("x8166").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b3681, b3564, b3560),Block(Const(())))
    val x8098 = withCtrl(x8166) { Counter(min=Const(0), max=Const(1024), step=Const(1), par=64).name("x8098").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:32:36") } // CounterNew(Const(0),Const(1024),Const(1),Const(64))
    val x8099 = withCtrl(x8166) { CounterChain(List(x8098)).name("x8099").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:32:54") } // CounterChainNew(List(x8098))
    val x8114 = withCtrl(x8166) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8099).name("x8114").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:32:54") } // UnrolledForeach(List(b3993, b3681, b3564, b3560),x8099,Block(Const(())),List(List(b4005)),List(List(b4006)))
    val b4005 = withCtrl(x8114) { CounterIter(x8098, None).name("b4005") } // b4005
    val b4006 = withCtrl(x8114) { Const(true).name("b4006") } // b4006
    val x8100 = withCtrl(x8114) { OpDef(op=BitAnd, inputs=List(b4006, b3993)).name("x8100").srcCtx("UnrollingBase.scala:28:66") } // And(b4006,b3993)
    val x8101 = withCtrl(x8114) { OpDef(op=BitAnd, inputs=List(b3681, b3564)).name("x8101").srcCtx("UnrollingBase.scala:28:66") } // And(b3681,b3564)
    val x8102 = withCtrl(x8114) { OpDef(op=BitAnd, inputs=List(x8100, x8101)).name("x8102").srcCtx("UnrollingBase.scala:28:66") } // And(x8100,x8101)
    val x8103 = withCtrl(x8114) { OpDef(op=BitAnd, inputs=List(x8102, b3560)).name("x8103").srcCtx("UnrollingBase.scala:28:66") } // And(x8102,b3560)
    val x8104 = withCtrl(x8114) { LoadBanks(List(x7749_d0_b12), List(b3677, b3989, b4005)).name("x8104").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:33:30") } // ParSRAMLoad(x7749,List(List(b3677, b3989, b4005)),List(x8103))
    val x8105 = withCtrl(x8114) { x8104 } // VectorApply(x8104,0)
    val x8106 = withCtrl(x8114) { ReadMem(x7846_d0).name("x8106").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // RegRead(x7846)
    val x8107 = withCtrl(x8114) { OpDef(op=FixMul, inputs=List(x8105, x8106)).name("x8107").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:33:60:xResult") } // FixMul(x8105,x8106)
    val x8108 = withCtrl(x8114) { LoadBanks(List(x7750_d0_b12), List(b3677, b3989, b4005)).name("x8108").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:34:30") } // ParSRAMLoad(x7750,List(List(b3677, b3989, b4005)),List(x8103))
    val x8109 = withCtrl(x8114) { x8108 } // VectorApply(x8108,0)
    val x8110 = withCtrl(x8114) { ReadMem(x7850_d0).name("x8110").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // RegRead(x7850)
    val x8111 = withCtrl(x8114) { OpDef(op=FixMul, inputs=List(x8109, x8110)).name("x8111").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:34:60:hResult") } // FixMul(x8109,x8110)
    val x8112 = withCtrl(x8114) { OpDef(op=FixAdd, inputs=List(x8107, x8111)).name("x8112").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:35:48") } // FixAdd(x8107,x8111)
    val x8113 = withCtrl(x8114) { StoreBanks(List(List(x7842_d0_b0)), List(b3989, b4005), x8112).name("x8113").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:35:38") } // ParSRAMStore(x7842,List(List(b3989, b4005)),List(x8112),List(x8103))
    val x8115 = withCtrl(x8166) { Counter(min=Const(0), max=Const(1024), step=Const(1), par=64).name("x8115").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:32:36") } // CounterNew(Const(0),Const(1024),Const(1),Const(64))
    val x8116 = withCtrl(x8166) { CounterChain(List(x8115)).name("x8116").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:32:54") } // CounterChainNew(List(x8115))
    val x8131 = withCtrl(x8166) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8116).name("x8131").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:32:54") } // UnrolledForeach(List(b3994, b3681, b3564, b3560),x8116,Block(Const(())),List(List(b4022)),List(List(b4023)))
    val b4022 = withCtrl(x8131) { CounterIter(x8115, None).name("b4022") } // b4022
    val b4023 = withCtrl(x8131) { Const(true).name("b4023") } // b4023
    val x8117 = withCtrl(x8131) { OpDef(op=BitAnd, inputs=List(b4023, b3994)).name("x8117").srcCtx("UnrollingBase.scala:28:66") } // And(b4023,b3994)
    val x8118 = withCtrl(x8131) { OpDef(op=BitAnd, inputs=List(b3681, b3564)).name("x8118").srcCtx("UnrollingBase.scala:28:66") } // And(b3681,b3564)
    val x8119 = withCtrl(x8131) { OpDef(op=BitAnd, inputs=List(x8117, x8118)).name("x8119").srcCtx("UnrollingBase.scala:28:66") } // And(x8117,x8118)
    val x8120 = withCtrl(x8131) { OpDef(op=BitAnd, inputs=List(x8119, b3560)).name("x8120").srcCtx("UnrollingBase.scala:28:66") } // And(x8119,b3560)
    val x8121 = withCtrl(x8131) { LoadBanks(List(x7749_d0_b13), List(b3677, b3990, b4022)).name("x8121").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:33:30") } // ParSRAMLoad(x7749,List(List(b3677, b3990, b4022)),List(x8120))
    val x8122 = withCtrl(x8131) { x8121 } // VectorApply(x8121,0)
    val x8123 = withCtrl(x8131) { ReadMem(x7846_d1).name("x8123").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // RegRead(x7846)
    val x8124 = withCtrl(x8131) { OpDef(op=FixMul, inputs=List(x8122, x8123)).name("x8124").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:33:60:xResult") } // FixMul(x8122,x8123)
    val x8125 = withCtrl(x8131) { LoadBanks(List(x7750_d0_b13), List(b3677, b3990, b4022)).name("x8125").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:34:30") } // ParSRAMLoad(x7750,List(List(b3677, b3990, b4022)),List(x8120))
    val x8126 = withCtrl(x8131) { x8125 } // VectorApply(x8125,0)
    val x8127 = withCtrl(x8131) { ReadMem(x7850_d1).name("x8127").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // RegRead(x7850)
    val x8128 = withCtrl(x8131) { OpDef(op=FixMul, inputs=List(x8126, x8127)).name("x8128").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:34:60:hResult") } // FixMul(x8126,x8127)
    val x8129 = withCtrl(x8131) { OpDef(op=FixAdd, inputs=List(x8124, x8128)).name("x8129").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:35:48") } // FixAdd(x8124,x8128)
    val x8130 = withCtrl(x8131) { StoreBanks(List(List(x7842_d0_b1)), List(b3990, b4022), x8129).name("x8130").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:35:38") } // ParSRAMStore(x7842,List(List(b3990, b4022)),List(x8129),List(x8120))
    val x8132 = withCtrl(x8166) { Counter(min=Const(0), max=Const(1024), step=Const(1), par=64).name("x8132").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:32:36") } // CounterNew(Const(0),Const(1024),Const(1),Const(64))
    val x8133 = withCtrl(x8166) { CounterChain(List(x8132)).name("x8133").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:32:54") } // CounterChainNew(List(x8132))
    val x8148 = withCtrl(x8166) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8133).name("x8148").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:32:54") } // UnrolledForeach(List(b3995, b3681, b3564, b3560),x8133,Block(Const(())),List(List(b4039)),List(List(b4040)))
    val b4039 = withCtrl(x8148) { CounterIter(x8132, None).name("b4039") } // b4039
    val b4040 = withCtrl(x8148) { Const(true).name("b4040") } // b4040
    val x8134 = withCtrl(x8148) { OpDef(op=BitAnd, inputs=List(b4040, b3995)).name("x8134").srcCtx("UnrollingBase.scala:28:66") } // And(b4040,b3995)
    val x8135 = withCtrl(x8148) { OpDef(op=BitAnd, inputs=List(b3681, b3564)).name("x8135").srcCtx("UnrollingBase.scala:28:66") } // And(b3681,b3564)
    val x8136 = withCtrl(x8148) { OpDef(op=BitAnd, inputs=List(x8134, x8135)).name("x8136").srcCtx("UnrollingBase.scala:28:66") } // And(x8134,x8135)
    val x8137 = withCtrl(x8148) { OpDef(op=BitAnd, inputs=List(x8136, b3560)).name("x8137").srcCtx("UnrollingBase.scala:28:66") } // And(x8136,b3560)
    val x8138 = withCtrl(x8148) { LoadBanks(List(x7749_d0_b14), List(b3677, b3991, b4039)).name("x8138").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:33:30") } // ParSRAMLoad(x7749,List(List(b3677, b3991, b4039)),List(x8137))
    val x8139 = withCtrl(x8148) { x8138 } // VectorApply(x8138,0)
    val x8140 = withCtrl(x8148) { ReadMem(x7846_d2).name("x8140").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // RegRead(x7846)
    val x8141 = withCtrl(x8148) { OpDef(op=FixMul, inputs=List(x8139, x8140)).name("x8141").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:33:60:xResult") } // FixMul(x8139,x8140)
    def split2 = {
    val x8142 = withCtrl(x8148) { LoadBanks(List(x7750_d0_b14), List(b3677, b3991, b4039)).name("x8142").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:34:30") } // ParSRAMLoad(x7750,List(List(b3677, b3991, b4039)),List(x8137))
    val x8143 = withCtrl(x8148) { x8142 } // VectorApply(x8142,0)
    val x8144 = withCtrl(x8148) { ReadMem(x7850_d2).name("x8144").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // RegRead(x7850)
    val x8145 = withCtrl(x8148) { OpDef(op=FixMul, inputs=List(x8143, x8144)).name("x8145").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:34:60:hResult") } // FixMul(x8143,x8144)
    val x8146 = withCtrl(x8148) { OpDef(op=FixAdd, inputs=List(x8141, x8145)).name("x8146").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:35:48") } // FixAdd(x8141,x8145)
    val x8147 = withCtrl(x8148) { StoreBanks(List(List(x7842_d0_b2)), List(b3991, b4039), x8146).name("x8147").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:35:38") } // ParSRAMStore(x7842,List(List(b3991, b4039)),List(x8146),List(x8137))
    val x8149 = withCtrl(x8166) { Counter(min=Const(0), max=Const(1024), step=Const(1), par=64).name("x8149").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:32:36") } // CounterNew(Const(0),Const(1024),Const(1),Const(64))
    val x8150 = withCtrl(x8166) { CounterChain(List(x8149)).name("x8150").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:32:54") } // CounterChainNew(List(x8149))
    val x8165 = withCtrl(x8166) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8150).name("x8165").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:32:54") } // UnrolledForeach(List(b3996, b3681, b3564, b3560),x8150,Block(Const(())),List(List(b4056)),List(List(b4057)))
    val b4056 = withCtrl(x8165) { CounterIter(x8149, None).name("b4056") } // b4056
    val b4057 = withCtrl(x8165) { Const(true).name("b4057") } // b4057
    val x8151 = withCtrl(x8165) { OpDef(op=BitAnd, inputs=List(b4057, b3996)).name("x8151").srcCtx("UnrollingBase.scala:28:66") } // And(b4057,b3996)
    val x8152 = withCtrl(x8165) { OpDef(op=BitAnd, inputs=List(b3681, b3564)).name("x8152").srcCtx("UnrollingBase.scala:28:66") } // And(b3681,b3564)
    val x8153 = withCtrl(x8165) { OpDef(op=BitAnd, inputs=List(x8151, x8152)).name("x8153").srcCtx("UnrollingBase.scala:28:66") } // And(x8151,x8152)
    val x8154 = withCtrl(x8165) { OpDef(op=BitAnd, inputs=List(x8153, b3560)).name("x8154").srcCtx("UnrollingBase.scala:28:66") } // And(x8153,b3560)
    val x8155 = withCtrl(x8165) { LoadBanks(List(x7749_d0_b15), List(b3677, b3992, b4056)).name("x8155").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:33:30") } // ParSRAMLoad(x7749,List(List(b3677, b3992, b4056)),List(x8154))
    val x8156 = withCtrl(x8165) { x8155 } // VectorApply(x8155,0)
    val x8157 = withCtrl(x8165) { ReadMem(x7846_d3).name("x8157").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // RegRead(x7846)
    val x8158 = withCtrl(x8165) { OpDef(op=FixMul, inputs=List(x8156, x8157)).name("x8158").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:33:60:xResult") } // FixMul(x8156,x8157)
    val x8159 = withCtrl(x8165) { LoadBanks(List(x7750_d0_b15), List(b3677, b3992, b4056)).name("x8159").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:34:30") } // ParSRAMLoad(x7750,List(List(b3677, b3992, b4056)),List(x8154))
    val x8160 = withCtrl(x8165) { x8159 } // VectorApply(x8159,0)
    val x8161 = withCtrl(x8165) { ReadMem(x7850_d3).name("x8161").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // RegRead(x7850)
    val x8162 = withCtrl(x8165) { OpDef(op=FixMul, inputs=List(x8160, x8161)).name("x8162").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:34:60:hResult") } // FixMul(x8160,x8161)
    val x8163 = withCtrl(x8165) { OpDef(op=FixAdd, inputs=List(x8158, x8162)).name("x8163").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:35:48") } // FixAdd(x8158,x8162)
    val x8164 = withCtrl(x8165) { StoreBanks(List(List(x7842_d0_b3)), List(b3992, b4056), x8163).name("x8164").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:35:38") } // ParSRAMStore(x7842,List(List(b3992, b4056)),List(x8163),List(x8154))
    val x8169 = withCtrl(x8211) { Counter(min=Const(0), max=Const(1024), step=Const(1), par=64).name("x8169").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // CounterNew(Const(0),Const(1024),Const(1),Const(64))
    val x8170 = withCtrl(x8211) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x8170").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x8171 = withCtrl(x8211) { CounterChain(List(x8170,x8169)).name("x8171").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // CounterChainNew(ArrayBuffer(x8170, x8169))
    val x8210 = withCtrl(x8211) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8171).name("x8210").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // UnrolledForeach(List(),x8171,Block(Const(())),ArrayBuffer(List(b4076), List(b4077)),ArrayBuffer(List(b4078), List(b4079)))
    val b4076 = withCtrl(x8210) { CounterIter(x8170, Some(0)).name("b4076") } // b4076
    val b4078 = withCtrl(x8210) { Const(true).name("b4078") } // b4078
    val b4077 = withCtrl(x8210) { CounterIter(x8169, None).name("b4077") } // b4077
    val b4079 = withCtrl(x8210) { Const(true).name("b4079") } // b4079
    val x8172 = withCtrl(x8210) { OpDef(op=BitAnd, inputs=List(b4078, b4079)).name("x8172").srcCtx("UnrollingBase.scala:28:66") } // And(b4078,b4079)
    val x8173 = withCtrl(x8210) { OpDef(op=BitAnd, inputs=List(b3564, b3560)).name("x8173").srcCtx("UnrollingBase.scala:28:66") } // And(b3564,b3560)
    val x8174 = withCtrl(x8210) { OpDef(op=BitAnd, inputs=List(x8172, x8173)).name("x8174").srcCtx("UnrollingBase.scala:28:66") } // And(x8172,x8173)
    val x8175 = withCtrl(x8210) { LoadBanks(List(x7839_d0_b0, x7839_d0_b1, x7839_d0_b2, x7839_d0_b3), List(b4076, b4077)).name("x8175").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // ParSRAMLoad(x7839,List(ArrayBuffer(b4076, b4077)),List(x8174))
    val x8176 = withCtrl(x8210) { x8175 } // VectorApply(x8175,0)
    val x8177 = withCtrl(x8210) { LoadBanks(List(x7840_d0_b0, x7840_d0_b1, x7840_d0_b2, x7840_d0_b3), List(b4076, b4077)).name("x8177").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // ParSRAMLoad(x7840,List(ArrayBuffer(b4076, b4077)),List(x8174))
    val x8178 = withCtrl(x8210) { x8177 } // VectorApply(x8177,0)
    val x8179 = withCtrl(x8210) { LoadBanks(List(x7841_d0_b0, x7841_d0_b1, x7841_d0_b2, x7841_d0_b3), List(b4076, b4077)).name("x8179").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // ParSRAMLoad(x7841,List(ArrayBuffer(b4076, b4077)),List(x8174))
    val x8180 = withCtrl(x8210) { x8179 } // VectorApply(x8179,0)
    val x8181 = withCtrl(x8210) { LoadBanks(List(x7842_d0_b0, x7842_d0_b1, x7842_d0_b2, x7842_d0_b3), List(b4076, b4077)).name("x8181").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // ParSRAMLoad(x7842,List(ArrayBuffer(b4076, b4077)),List(x8174))
    val x8182 = withCtrl(x8210) { x8181 } // VectorApply(x8181,0)
    val x8183 = withCtrl(x8210) { LoadBanks(List(x7835_d1_b0), List(b4076, b4077)).name("x8183").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // ParSRAMLoad(x7835,List(ArrayBuffer(b4076, b4077)),List(x8174))
    val x8184 = withCtrl(x8210) { x8183 } // VectorApply(x8183,0)
    val x8185 = withCtrl(x8210) { OpDef(op=BitAnd, inputs=List(b3678, b3564)).name("x8185").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // And(b3678,b3564)
    val x8186 = withCtrl(x8210) { OpDef(op=BitAnd, inputs=List(x8185, b3560)).name("x8186").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // And(x8185,b3560)
    val x8187 = withCtrl(x8210) { OpDef(op=BitAnd, inputs=List(b3679, b3564)).name("x8187").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // And(b3679,b3564)
    val x8188 = withCtrl(x8210) { OpDef(op=BitAnd, inputs=List(x8187, b3560)).name("x8188").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // And(x8187,b3560)
    val x8189 = withCtrl(x8210) { OpDef(op=BitAnd, inputs=List(b3680, b3564)).name("x8189").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // And(b3680,b3564)
    val x8190 = withCtrl(x8210) { OpDef(op=BitAnd, inputs=List(x8189, b3560)).name("x8190").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // And(x8189,b3560)
    val x8191 = withCtrl(x8210) { OpDef(op=BitAnd, inputs=List(b3681, b3564)).name("x8191").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // And(b3681,b3564)
    val x8192 = withCtrl(x8210) { OpDef(op=BitAnd, inputs=List(x8191, b3560)).name("x8192").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // And(x8191,b3560)
    val x8193 = withCtrl(x8210) { OpDef(op=BitAnd, inputs=List(x8186, x8174)).name("x8193").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // And(x8186,x8174)
    val x8194 = withCtrl(x8210) { OpDef(op=BitAnd, inputs=List(x8188, x8174)).name("x8194").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // And(x8188,x8174)
    val x8195 = withCtrl(x8210) { OpDef(op=BitAnd, inputs=List(x8190, x8174)).name("x8195").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // And(x8190,x8174)
    val x8196 = withCtrl(x8210) { OpDef(op=BitAnd, inputs=List(x8192, x8174)).name("x8196").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // And(x8192,x8174)
    val x8197 = withCtrl(x8210) { OpDef(op=FixAdd, inputs=List(x8176, x8178)).name("x8197").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:8") } // FixAdd(x8176,x8178)
    val x8198 = withCtrl(x8210) { OpDef(op=MuxOp, inputs=List(x8194, x8197, x8176)).name("x8198").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // Mux(x8194,x8197,x8176)
    val x8199 = withCtrl(x8210) { OpDef(op=BitOr, inputs=List(x8193, x8194)).name("x8199").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // Or(x8193,x8194)
    val x8200 = withCtrl(x8210) { OpDef(op=FixAdd, inputs=List(x8180, x8182)).name("x8200").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:8") } // FixAdd(x8180,x8182)
    val x8201 = withCtrl(x8210) { OpDef(op=MuxOp, inputs=List(x8196, x8200, x8180)).name("x8201").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // Mux(x8196,x8200,x8180)
    val x8202 = withCtrl(x8210) { OpDef(op=BitOr, inputs=List(x8195, x8196)).name("x8202").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // Or(x8195,x8196)
    val x8203 = withCtrl(x8210) { OpDef(op=FixAdd, inputs=List(x8198, x8201)).name("x8203").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:8") } // FixAdd(x8198,x8201)
    val x8204 = withCtrl(x8210) { OpDef(op=MuxOp, inputs=List(x8202, x8203, x8198)).name("x8204").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // Mux(x8202,x8203,x8198)
    val x8205 = withCtrl(x8210) { OpDef(op=BitOr, inputs=List(x8199, x8202)).name("x8205").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // Or(x8199,x8202)
    val x8206 = withCtrl(x8210) { OpDef(op=FixEql, inputs=List(b3674, Const(0))).name("x8206").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // FixEql(b3674,Const(0))
    val x8207 = withCtrl(x8210) { OpDef(op=FixAdd, inputs=List(x8204, x8184)).name("x8207").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:8") } // FixAdd(x8204,x8184)
    val x8208 = withCtrl(x8210) { OpDef(op=MuxOp, inputs=List(x8206, x8204, x8207)).name("x8208").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // Mux(x8206,x8204,x8207)
    val x8209 = withCtrl(x8210) { StoreBanks(List(List(x7835_d0_b0), List(x7835_d1_b0)), List(b4076, b4077), x8208).name("x8209").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:40:6") } // ParSRAMStore(x7835,List(ArrayBuffer(b4076, b4077)),List(x8208),List(x8174))
    antiDepsOf(x8209)=List(x8183)
    val x8212 = withCtrl(x8354) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x8212").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:42:26") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x8213 = withCtrl(x8354) { CounterChain(List(x8212)).name("x8213").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:42:33") } // CounterChainNew(List(x8212))
    val x8226 = withCtrl(x8354) { LoopController(style=MetaPipe, level=OuterControl, cchain=x8213).name("x8226").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:42:33") } // UnrolledForeach(List(b3564, b3560),x8213,Block(Const(())),List(List(b4122)),List(List(b4123)))
    val b4122 = withCtrl(x8226) { CounterIter(x8212, Some(0)).name("b4122") } // b4122
    val b4123 = withCtrl(x8226) { Const(true).name("b4123") } // b4123
    val x8214 = withCtrl(x8226) { Counter(min=Const(0), max=Const(1024), step=Const(1), par=64).name("x8214").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:43:34") } // CounterNew(Const(0),Const(1024),Const(1),Const(64))
    val x8215 = withCtrl(x8226) { CounterChain(List(x8214)).name("x8215").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:43:52") } // CounterChainNew(List(x8214))
    val x8225 = withCtrl(x8226) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8215).name("x8225").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:43:52") } // UnrolledForeach(List(b4123, b3564, b3560),x8215,Block(Const(())),List(List(b4126)),List(List(b4127)))
    val b4126 = withCtrl(x8225) { CounterIter(x8214, None).name("b4126") } // b4126
    val b4127 = withCtrl(x8225) { Const(true).name("b4127") } // b4127
    val x8216 = withCtrl(x8225) { OpDef(op=BitAnd, inputs=List(b4127, b4123)).name("x8216").srcCtx("UnrollingBase.scala:28:66") } // And(b4127,b4123)
    val x8217 = withCtrl(x8225) { OpDef(op=BitAnd, inputs=List(b3564, b3560)).name("x8217").srcCtx("UnrollingBase.scala:28:66") } // And(b3564,b3560)
    val x8218 = withCtrl(x8225) { OpDef(op=BitAnd, inputs=List(x8216, x8217)).name("x8218").srcCtx("UnrollingBase.scala:28:66") } // And(x8216,x8217)
    val x8219 = withCtrl(x8225) { LoadBanks(List(x7835_d0_b0), List(b4122, b4126)).name("x8219").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:44:32") } // ParSRAMLoad(x7835,List(List(b4122, b4126)),List(x8218))
    val x8220 = withCtrl(x8225) { x8219 } // VectorApply(x8219,0)
    val x8221 = withCtrl(x8225) { LoadBanks(List(x7642_d0_b0), List(b3563, b4122, b4126)).name("x8221").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:44:56") } // ParSRAMLoad(x7642,List(List(b3563, b4122, b4126)),List(x8218))
    val x8222 = withCtrl(x8225) { x8221 } // VectorApply(x8221,0)
    val x8223 = withCtrl(x8225) { OpDef(op=FixAdd, inputs=List(x8220, x8222)).name("x8223").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:44:53:foldVal") } // FixAdd(x8220,x8222)
    val x8224 = withCtrl(x8225) { StoreBanks(List(List(x7836_d0_b0)), List(b4122, b4126), x8223).name("x8224").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:45:37") } // ParSRAMStore(x7836,List(List(b4122, b4126)),List(x8223),List(x8218))
    val x8227 = withCtrl(x8354) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x8227").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:49:21") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x8228 = withCtrl(x8354) { CounterChain(List(x8227)).name("x8228").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:49:27") } // CounterChainNew(List(x8227))
    val x8353 = withCtrl(x8354) { LoopController(style=MetaPipe, level=OuterControl, cchain=x8228).name("x8353").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:49:27") } // UnrolledForeach(List(b3564, b3560),x8228,Block(Const(())),List(List(b4141)),List(List(b4142)))
    val b4141 = withCtrl(x8353) { CounterIter(x8227, Some(0)).name("b4141") } // b4141
    val b4142 = withCtrl(x8353) { Const(true).name("b4142") } // b4142
    val x8229 = withCtrl(x8353) { FIFO(size=16).name("x8229").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:50:25:sigQ") } // x8229 = FIFONew(Const(16))
    isAccum(x8229) = false
    bufferDepthOf(x8229) = 2
    val x8230 = withCtrl(x8353) { FIFO(size=16).name("x8230").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:51:26:sigQQ") } // x8230 = FIFONew(Const(16))
    isAccum(x8230) = false
    bufferDepthOf(x8230) = 2
    val x8231 = withCtrl(x8353) { FIFO(size=16).name("x8231").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:52:31:sigEleMuxQ") } // x8231 = FIFONew(Const(16))
    isAccum(x8231) = false
    bufferDepthOf(x8231) = 2
    val x8232 = withCtrl(x8353) { FIFO(size=16).name("x8232").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:53:26:tanhQ") } // x8232 = FIFONew(Const(16))
    isAccum(x8232) = false
    bufferDepthOf(x8232) = 2
    val x8233 = withCtrl(x8353) { Counter(min=Const(0), max=Const(1024), step=Const(1), par=64).name("x8233").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:55:34") } // CounterNew(Const(0),Const(1024),Const(1),Const(64))
    val x8234 = withCtrl(x8353) { CounterChain(List(x8233)).name("x8234").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:55:52") } // CounterChainNew(List(x8233))
    val x8257 = withCtrl(x8353) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8234).name("x8257").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:55:52") } // UnrolledForeach(List(b4142, b3564, b3560),x8234,Block(Const(())),List(List(b4149)),List(List(b4150)))
    val b4149 = withCtrl(x8257) { CounterIter(x8233, None).name("b4149") } // b4149
    val b4150 = withCtrl(x8257) { Const(true).name("b4150") } // b4150
    val x8235 = withCtrl(x8257) { OpDef(op=BitAnd, inputs=List(b4150, b4142)).name("x8235").srcCtx("UnrollingBase.scala:28:66") } // And(b4150,b4142)
    val x8236 = withCtrl(x8257) { OpDef(op=BitAnd, inputs=List(b3564, b3560)).name("x8236").srcCtx("UnrollingBase.scala:28:66") } // And(b3564,b3560)
    val x8237 = withCtrl(x8257) { OpDef(op=BitAnd, inputs=List(x8235, x8236)).name("x8237").srcCtx("UnrollingBase.scala:28:66") } // And(x8235,x8236)
    val x8238 = withCtrl(x8257) { LoadBanks(List(x7836_d0_b0), List(b4141, b4149)).name("x8238").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:56:27:pEle") } // ParSRAMLoad(x7836,List(List(b4141, b4149)),List(x8237))
    val x8239 = withCtrl(x8257) { x8238 } // VectorApply(x8238,0)
    val x8240_d0_b0 = withCtrl(x8257) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x8240_d0_b0").srcCtx("NonLinearity.scala:43:37:lut") } // x8240 = LUTNew(List(1024), Seq(Const(-0.9999997615814208984375),Const(-0.9999997615814208984375),Const(-0.999999701976776123046875)... [1021 more]))
    isAccum(x8240_d0_b0) = false
    bufferDepthOf(x8240_d0_b0) = 1
    staticDimsOf(x8240_d0_b0) = List(1024)
    val x8241 = withCtrl(x8257) { OpDef(op=FixSub, inputs=List(x8239, Const(-8.0))).name("x8241").srcCtx("NonLinearity.scala:44:22") } // FixSub(x8239,Const(-8))
    val x8242 = withCtrl(x8257) { OpDef(op=FixSla, inputs=List(x8241, Const(6))).name("x8242").srcCtx("NonLinearity.scala:44:30") } // FixLsh(x8241,Const(6))
    // x8243 = FixConvert(x8242,TRUE,_32,_0) x.tp=FixPt[TRUE,_8,_24] {
    val x8243 = withCtrl(x8257) { OpDef(op=FixSra, inputs=List(x8242, Const("24"))).name("x8243").srcCtx("NonLinearity.scala:44:41:index") } // FixConvert(x8242,TRUE,_32,_0)
    // }
    val x8244 = withCtrl(x8257) { LoadBanks(List(x8240_d0_b0), List(x8243)).name("x8244").srcCtx("NonLinearity.scala:45:17:sigVal") } // LUTLoad(x8240,List(x8243),x8237)
    val x8245_d0_b0 = withCtrl(x8257) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x8245_d0_b0").srcCtx("NonLinearity.scala:43:37:lut") } // x8245 = LUTNew(List(1024), Seq(Const(-0.9999997615814208984375),Const(-0.9999997615814208984375),Const(-0.999999701976776123046875)... [1021 more]))
    isAccum(x8245_d0_b0) = false
    bufferDepthOf(x8245_d0_b0) = 1
    staticDimsOf(x8245_d0_b0) = List(1024)
    val x8246 = withCtrl(x8257) { LoadBanks(List(x8245_d0_b0), List(x8243)).name("x8246").srcCtx("NonLinearity.scala:45:17:tanhVal") } // LUTLoad(x8245,List(x8243),x8237)
    val x8247 = withCtrl(x8257) { OpDef(op=FixLt, inputs=List(Const(8.0), x8239)).name("x8247").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:60:27:isHigh") } // FixLt(Const(8),x8239)
    val x8248 = withCtrl(x8257) { OpDef(op=FixLt, inputs=List(x8239, Const(-8.0))).name("x8248").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:61:26:isLow") } // FixLt(x8239,Const(-8))
    val x8249 = withCtrl(x8257) { OpDef(op=MuxOp, inputs=List(x8248, Const(0.0), x8244)).name("x8249").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:63:46") } // Mux(x8248,Const(0),x8244)
    val x8250 = withCtrl(x8257) { OpDef(op=MuxOp, inputs=List(x8247, Const(1.0), x8249)).name("x8250").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:63:25:sigEle") } // Mux(x8247,Const(1),x8249)
    val x8251 = withCtrl(x8257) { OpDef(op=MuxOp, inputs=List(x8248, Const(-1.0), x8246)).name("x8251").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:64:47") } // Mux(x8248,Const(-1),x8246)
    val x8252 = withCtrl(x8257) { OpDef(op=MuxOp, inputs=List(x8247, Const(1.0), x8251)).name("x8252").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:64:26:tanhEle") } // Mux(x8247,Const(1),x8251)
    val x8253_x8229 = withCtrl(x8257) { WriteMem(x8229, x8250).name("x8253_x8229").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:66:17") } // ParFIFOEnq(x8229,List(x8250),List(x8237))
    val x8254_x8230 = withCtrl(x8257) { WriteMem(x8230, x8250).name("x8254_x8230").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:67:18") } // ParFIFOEnq(x8230,List(x8250),List(x8237))
    val x8255_x8231 = withCtrl(x8257) { WriteMem(x8231, x8250).name("x8255_x8231").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:68:23") } // ParFIFOEnq(x8231,List(x8250),List(x8237))
    val x8256_x8232 = withCtrl(x8257) { WriteMem(x8232, x8252).name("x8256_x8232").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:70:18") } // ParFIFOEnq(x8232,List(x8252),List(x8237))
    val x8352 = withCtrl(x8353) { UnitController(style=SeqPipe, level=OuterControl).name("x8352").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:73:12") } // UnitPipe(List(b4142, b3564, b3560),Block(Const(())))
    val x8258 = withCtrl(x8352) { FIFO(size=16).name("x8258").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:75:29:cLastQ") } // x8258 = FIFONew(Const(16))
    isAccum(x8258) = false
    bufferDepthOf(x8258) = 1
    val x8259 = withCtrl(x8352) { FIFO(size=16).name("x8259").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:76:35:cNewMultAddQ") } // x8259 = FIFONew(Const(16))
    isAccum(x8259) = false
    bufferDepthOf(x8259) = 1
    val x8260 = withCtrl(x8352) { FIFO(size=16).name("x8260").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:77:36:cNewMultAddQQ") } // x8260 = FIFONew(Const(16))
    isAccum(x8260) = false
    bufferDepthOf(x8260) = 1
    val x8261 = withCtrl(x8352) { FIFO(size=16).name("x8261").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:78:32:cNewMultQ") } // x8261 = FIFONew(Const(16))
    isAccum(x8261) = false
    bufferDepthOf(x8261) = 1
    val x8262 = withCtrl(x8352) { Counter(min=Const(0), max=Const(1024), step=Const(1), par=64).name("x8262").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:80:36") } // CounterNew(Const(0),Const(1024),Const(1),Const(64))
    val x8263 = withCtrl(x8352) { CounterChain(List(x8262)).name("x8263").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:80:54") } // CounterChainNew(List(x8262))
    val x8280 = withCtrl(x8352) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8263).name("x8280").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:80:54") } // UnrolledForeach(List(b4142, b3564, b3560),x8263,Block(Const(())),List(List(b4180)),List(List(b4181)))
    val b4180 = withCtrl(x8280) { CounterIter(x8262, None).name("b4180") } // b4180
    val b4181 = withCtrl(x8280) { Const(true).name("b4181") } // b4181
    val x8264 = withCtrl(x8280) { OpDef(op=BitAnd, inputs=List(b4181, b4142)).name("x8264").srcCtx("UnrollingBase.scala:28:66") } // And(b4181,b4142)
    val x8265 = withCtrl(x8280) { OpDef(op=BitAnd, inputs=List(b3564, b3560)).name("x8265").srcCtx("UnrollingBase.scala:28:66") } // And(b3564,b3560)
    val x8266 = withCtrl(x8280) { OpDef(op=BitAnd, inputs=List(x8264, x8265)).name("x8266").srcCtx("UnrollingBase.scala:28:66") } // And(x8264,x8265)
    val x8267 = withCtrl(x8280) { ReadMem(x8229).name("x8267").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:81:32:sigEle") } // ParFIFODeq(x8229,List(x8266))
    val x8268 = withCtrl(x8280) { x8267 } // VectorApply(x8267,0)
    val x8269 = withCtrl(x8280) { ReadMem(x8232).name("x8269").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:82:34:tanhEle") } // ParFIFODeq(x8232,List(x8266))
    val x8270 = withCtrl(x8280) { x8269 } // VectorApply(x8269,0)
    val x8271 = withCtrl(x8280) { LoadBanks(List(x7640_d0_b0), List(b3563, b4180)).name("x8271").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:84:24:cLast") } // ParSRAMLoad(x7640,List(List(b3563, b4180)),List(x8266))
    val x8272 = withCtrl(x8280) { x8271 } // VectorApply(x8271,0)
    val x8273 = withCtrl(x8280) { OpDef(op=FixMul, inputs=List(x8272, x8270)).name("x8273").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:85:32:cNewMult") } // FixMul(x8272,x8270)
    val x8274 = withCtrl(x8280) { OpDef(op=FixMul, inputs=List(x8268, x8272)).name("x8274").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:86:36") } // FixMul(x8268,x8272)
    val x8275 = withCtrl(x8280) { OpDef(op=FixAdd, inputs=List(x8274, x8273)).name("x8275").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:86:44:cNewMultAdd") } // FixAdd(x8274,x8273)
    val x8276_x8261 = withCtrl(x8280) { WriteMem(x8261, x8273).name("x8276_x8261").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:88:24") } // ParFIFOEnq(x8261,List(x8273),List(x8266))
    val x8277_x8259 = withCtrl(x8280) { WriteMem(x8259, x8275).name("x8277_x8259").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:89:27") } // ParFIFOEnq(x8259,List(x8275),List(x8266))
    val x8278_x8260 = withCtrl(x8280) { WriteMem(x8260, x8275).name("x8278_x8260").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:90:28") } // ParFIFOEnq(x8260,List(x8275),List(x8266))
    val x8279_x8258 = withCtrl(x8280) { WriteMem(x8258, x8272).name("x8279_x8258").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:91:21") } // ParFIFOEnq(x8258,List(x8272),List(x8266))
    val x8281 = withCtrl(x8352) { FIFO(size=16).name("x8281").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:95:31:cUpdateQ") } // x8281 = FIFONew(Const(16))
    isAccum(x8281) = false
    bufferDepthOf(x8281) = 1
    val x8282 = withCtrl(x8352) { Counter(min=Const(0), max=Const(1024), step=Const(1), par=64).name("x8282").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:96:36") } // CounterNew(Const(0),Const(1024),Const(1),Const(64))
    val x8283 = withCtrl(x8352) { CounterChain(List(x8282)).name("x8283").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:96:54") } // CounterChainNew(List(x8282))
    val x8302 = withCtrl(x8352) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8283).name("x8302").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:96:54") } // UnrolledForeach(List(b4142, b3564, b3560),x8283,Block(Const(())),List(List(b4202)),List(List(b4203)))
    val b4202 = withCtrl(x8302) { CounterIter(x8282, None).name("b4202") } // b4202
    val b4203 = withCtrl(x8302) { Const(true).name("b4203") } // b4203
    val x8284 = withCtrl(x8302) { OpDef(op=BitAnd, inputs=List(b4203, b4142)).name("x8284").srcCtx("UnrollingBase.scala:28:66") } // And(b4203,b4142)
    val x8285 = withCtrl(x8302) { OpDef(op=BitAnd, inputs=List(b3564, b3560)).name("x8285").srcCtx("UnrollingBase.scala:28:66") } // And(b3564,b3560)
    val x8286 = withCtrl(x8302) { OpDef(op=BitAnd, inputs=List(x8284, x8285)).name("x8286").srcCtx("UnrollingBase.scala:28:66") } // And(x8284,x8285)
    val x8287 = withCtrl(x8302) { ReadMem(x8261).name("x8287").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:97:39:cNewMult") } // ParFIFODeq(x8261,List(x8286))
    val x8288 = withCtrl(x8302) { x8287 } // VectorApply(x8287,0)
    val x8289 = withCtrl(x8302) { ReadMem(x8231).name("x8289").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:98:38:sigEle") } // ParFIFODeq(x8231,List(x8286))
    val x8290 = withCtrl(x8302) { x8289 } // VectorApply(x8289,0)
    val x8291 = withCtrl(x8302) { ReadMem(x8259).name("x8291").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:99:45:cNewMultAdd") } // ParFIFODeq(x8259,List(x8286))
    val x8292 = withCtrl(x8302) { x8291 } // VectorApply(x8291,0)
    val x8293 = withCtrl(x8302) { ReadMem(x8258).name("x8293").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:100:33:cLast") } // ParFIFODeq(x8258,List(x8286))
    val x8294 = withCtrl(x8302) { x8293 } // VectorApply(x8293,0)
    val x8295 = withCtrl(x8302) { OpDef(op=FixEql, inputs=List(b4141, Const(0))).name("x8295").srcCtx("package.scala:58:42") } // FixEql(b4141,Const(0))
    val x8296 = withCtrl(x8302) { OpDef(op=FixEql, inputs=List(b4141, Const(1))).name("x8296").srcCtx("package.scala:61:42") } // FixEql(b4141,Const(1))
    val x8297 = withCtrl(x8302) { OpDef(op=FixEql, inputs=List(b4141, Const(2))).name("x8297").srcCtx("package.scala:64:42") } // FixEql(b4141,Const(2))
    val x8298 = withCtrl(x8302) { OpDef(op=MuxOp, inputs=List(x8297, x8292, x8294)).name("x8298").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:104:30") } // Mux(x8297,x8292,x8294)
    val x8299 = withCtrl(x8302) { OpDef(op=MuxOp, inputs=List(x8296, x8288, x8298)).name("x8299").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:103:30") } // Mux(x8296,x8288,x8298)
    val x8300 = withCtrl(x8302) { OpDef(op=MuxOp, inputs=List(x8295, x8290, x8299)).name("x8300").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:102:28:cUpdate") } // Mux(x8295,x8290,x8299)
    val x8301_x8281 = withCtrl(x8302) { WriteMem(x8281, x8300).name("x8301_x8281").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:107:23") } // ParFIFOEnq(x8281,List(x8300),List(x8286))
    val x8303 = withCtrl(x8352) { FIFO(size=16).name("x8303").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:111:31:hLinearQ") } // x8303 = FIFONew(Const(16))
    isAccum(x8303) = false
    bufferDepthOf(x8303) = 1
    val x8304 = withCtrl(x8352) { Counter(min=Const(0), max=Const(1024), step=Const(1), par=64).name("x8304").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:112:36") } // CounterNew(Const(0),Const(1024),Const(1),Const(64))
    val x8305 = withCtrl(x8352) { CounterChain(List(x8304)).name("x8305").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:112:54") } // CounterChainNew(List(x8304))
    val x8321 = withCtrl(x8352) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8305).name("x8321").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:112:54") } // UnrolledForeach(List(b4142, b3564, b3560),x8305,Block(Const(())),List(List(b4226)),List(List(b4227)))
    val b4226 = withCtrl(x8321) { CounterIter(x8304, None).name("b4226") } // b4226
    val b4227 = withCtrl(x8321) { Const(true).name("b4227") } // b4227
    val x8306 = withCtrl(x8321) { OpDef(op=BitAnd, inputs=List(b4227, b4142)).name("x8306").srcCtx("UnrollingBase.scala:28:66") } // And(b4227,b4142)
    val x8307 = withCtrl(x8321) { OpDef(op=BitAnd, inputs=List(b3564, b3560)).name("x8307").srcCtx("UnrollingBase.scala:28:66") } // And(b3564,b3560)
    val x8308 = withCtrl(x8321) { OpDef(op=BitAnd, inputs=List(x8306, x8307)).name("x8308").srcCtx("UnrollingBase.scala:28:66") } // And(x8306,x8307)
    val x8309 = withCtrl(x8321) { ReadMem(x8260).name("x8309").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:113:46:cNewMultAdd") } // ParFIFODeq(x8260,List(x8308))
    val x8310 = withCtrl(x8321) { x8309 } // VectorApply(x8309,0)
    val x8311_d0_b0 = withCtrl(x8321) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x8311_d0_b0").srcCtx("NonLinearity.scala:43:37:lut") } // x8311 = LUTNew(List(1024), Seq(Const(-0.9999997615814208984375),Const(-0.9999997615814208984375),Const(-0.999999701976776123046875)... [1021 more]))
    isAccum(x8311_d0_b0) = false
    bufferDepthOf(x8311_d0_b0) = 1
    staticDimsOf(x8311_d0_b0) = List(1024)
    val x8312 = withCtrl(x8321) { OpDef(op=FixSub, inputs=List(x8310, Const(-8.0))).name("x8312").srcCtx("NonLinearity.scala:44:22") } // FixSub(x8310,Const(-8))
    val x8313 = withCtrl(x8321) { OpDef(op=FixSla, inputs=List(x8312, Const(6))).name("x8313").srcCtx("NonLinearity.scala:44:30") } // FixLsh(x8312,Const(6))
    // x8314 = FixConvert(x8313,TRUE,_32,_0) x.tp=FixPt[TRUE,_8,_24] {
    val x8314 = withCtrl(x8321) { OpDef(op=FixSra, inputs=List(x8313, Const("24"))).name("x8314").srcCtx("NonLinearity.scala:44:41:index") } // FixConvert(x8313,TRUE,_32,_0)
    // }
    val x8315 = withCtrl(x8321) { LoadBanks(List(x8311_d0_b0), List(x8314)).name("x8315").srcCtx("NonLinearity.scala:45:17:tanhValMultAdd") } // LUTLoad(x8311,List(x8314),x8308)
    val x8316 = withCtrl(x8321) { OpDef(op=FixLt, inputs=List(Const(8.0), x8310)).name("x8316").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:115:36:isHigh") } // FixLt(Const(8),x8310)
    val x8317 = withCtrl(x8321) { OpDef(op=FixLt, inputs=List(x8310, Const(-8.0))).name("x8317").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:116:35:isLow") } // FixLt(x8310,Const(-8))
    val x8318 = withCtrl(x8321) { OpDef(op=MuxOp, inputs=List(x8317, Const(-1.0), x8315)).name("x8318").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:117:49") } // Mux(x8317,Const(-1),x8315)
    val x8319 = withCtrl(x8321) { OpDef(op=MuxOp, inputs=List(x8316, Const(1.0), x8318)).name("x8319").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:117:28:hLinear") } // Mux(x8316,Const(1),x8318)
    val x8320_x8303 = withCtrl(x8321) { WriteMem(x8303, x8319).name("x8320_x8303").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:119:23") } // ParFIFOEnq(x8303,List(x8319),List(x8308))
    val x8322 = withCtrl(x8352) { FIFO(size=16).name("x8322").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:123:31:hUpdateQ") } // x8322 = FIFONew(Const(16))
    isAccum(x8322) = false
    bufferDepthOf(x8322) = 1
    val x8323 = withCtrl(x8352) { Counter(min=Const(0), max=Const(1024), step=Const(1), par=64).name("x8323").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:124:36") } // CounterNew(Const(0),Const(1024),Const(1),Const(64))
    val x8324 = withCtrl(x8352) { CounterChain(List(x8323)).name("x8324").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:124:54") } // CounterChainNew(List(x8323))
    val x8338 = withCtrl(x8352) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8324).name("x8338").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:124:54") } // UnrolledForeach(List(b4142, b3564, b3560),x8324,Block(Const(())),List(List(b4247)),List(List(b4248)))
    val b4247 = withCtrl(x8338) { CounterIter(x8323, None).name("b4247") } // b4247
    val b4248 = withCtrl(x8338) { Const(true).name("b4248") } // b4248
    val x8325 = withCtrl(x8338) { OpDef(op=BitAnd, inputs=List(b4248, b4142)).name("x8325").srcCtx("UnrollingBase.scala:28:66") } // And(b4248,b4142)
    val x8326 = withCtrl(x8338) { OpDef(op=BitAnd, inputs=List(b3564, b3560)).name("x8326").srcCtx("UnrollingBase.scala:28:66") } // And(b3564,b3560)
    val x8327 = withCtrl(x8338) { OpDef(op=BitAnd, inputs=List(x8325, x8326)).name("x8327").srcCtx("UnrollingBase.scala:28:66") } // And(x8325,x8326)
    val x8328 = withCtrl(x8338) { ReadMem(x8303).name("x8328").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:125:37:hLinear") } // ParFIFODeq(x8303,List(x8327))
    val x8329 = withCtrl(x8338) { x8328 } // VectorApply(x8328,0)
    val x8330 = withCtrl(x8338) { ReadMem(x8230).name("x8330").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:126:33:sigEle") } // ParFIFODeq(x8230,List(x8327))
    val x8331 = withCtrl(x8338) { x8330 } // VectorApply(x8330,0)
    val x8332 = withCtrl(x8338) { LoadBanks(List(x7641_d0_b0), List(b3563, b4247)).name("x8332").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:128:24:hLast") } // ParSRAMLoad(x7641,List(List(b3563, b4247)),List(x8327))
    val x8333 = withCtrl(x8338) { x8332 } // VectorApply(x8332,0)
    val x8334 = withCtrl(x8338) { OpDef(op=FixMul, inputs=List(x8329, x8331)).name("x8334").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:129:30:hNew") } // FixMul(x8329,x8331)
    val x8335 = withCtrl(x8338) { OpDef(op=FixEql, inputs=List(b4141, Const(3))).name("x8335").srcCtx("package.scala:67:42") } // FixEql(b4141,Const(3))
    val x8336 = withCtrl(x8338) { OpDef(op=MuxOp, inputs=List(x8335, x8334, x8333)).name("x8336").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:130:28:hUpdate") } // Mux(x8335,x8334,x8333)
    val x8337_x8322 = withCtrl(x8338) { WriteMem(x8322, x8336).name("x8337_x8322").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:132:23") } // ParFIFOEnq(x8322,List(x8336),List(x8327))
    val x8339 = withCtrl(x8352) { Counter(min=Const(0), max=Const(1024), step=Const(1), par=64).name("x8339").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:137:36") } // CounterNew(Const(0),Const(1024),Const(1),Const(64))
    val x8340 = withCtrl(x8352) { CounterChain(List(x8339)).name("x8340").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:137:54") } // CounterChainNew(List(x8339))
    val x8351 = withCtrl(x8352) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8340).name("x8351").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:137:54") } // UnrolledForeach(List(b4142, b3564, b3560),x8340,Block(Const(())),List(List(b4265)),List(List(b4266)))
    val b4265 = withCtrl(x8351) { CounterIter(x8339, None).name("b4265") } // b4265
    val b4266 = withCtrl(x8351) { Const(true).name("b4266") } // b4266
    val x8341 = withCtrl(x8351) { OpDef(op=BitAnd, inputs=List(b4266, b4142)).name("x8341").srcCtx("UnrollingBase.scala:28:66") } // And(b4266,b4142)
    val x8342 = withCtrl(x8351) { OpDef(op=BitAnd, inputs=List(b3564, b3560)).name("x8342").srcCtx("UnrollingBase.scala:28:66") } // And(b3564,b3560)
    val x8343 = withCtrl(x8351) { OpDef(op=BitAnd, inputs=List(x8341, x8342)).name("x8343").srcCtx("UnrollingBase.scala:28:66") } // And(x8341,x8342)
    val x8344 = withCtrl(x8351) { ReadMem(x8322).name("x8344").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:138:34:hNew") } // ParFIFODeq(x8322,List(x8343))
    val x8345 = withCtrl(x8351) { x8344 } // VectorApply(x8344,0)
    val x8346 = withCtrl(x8351) { ReadMem(x8281).name("x8346").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:139:34:cNew") } // ParFIFODeq(x8281,List(x8343))
    val x8347 = withCtrl(x8351) { x8346 } // VectorApply(x8346,0)
    val x8348 = withCtrl(x8351) { StoreBanks(List(List(x7641_d0_b0), List(x7641_d1_b0)), List(b3563, b4265), x8345).name("x8348").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:140:34") } // ParSRAMStore(x7641,List(List(b3563, b4265)),List(x8345),List(x8343))
    val x8349 = withCtrl(x8351) { StoreBanks(List(List(x7639_d0_b0), List(x7639_d1_b0)), List(b3559, b4265), x8345).name("x8349").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:141:33") } // ParSRAMStore(x7639,List(List(b3559, b4265)),List(x8345),List(x8343))
    val x8350 = withCtrl(x8351) { StoreBanks(List(List(x7640_d0_b0)), List(b3563, b4265), x8347).name("x8350").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:142:34") } // ParSRAMStore(x7640,List(List(b3563, b4265)),List(x8347),List(x8343))
    val x8356 = withCtrl(x8384) { Counter(min=Const(0), max=Const(8), step=Const(1), par=1).name("x8356").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:186:54") } // CounterNew(Const(0),Const(8),Const(1),Const(1))
    val x8357 = withCtrl(x8384) { CounterChain(List(x8356)).name("x8357").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:186:54") } // CounterChainNew(List(x8356))
    val x8383 = withCtrl(x8384) { LoopController(style=StreamPipe, level=OuterControl, cchain=x8357).name("x8383").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:186:54") } // UnrolledForeach(List(Const(true)),x8357,Block(Const(())),List(List(b4284)),List(List(b4285)))
    val b4284 = withCtrl(x8383) { CounterIter(x8356, Some(0)).name("b4284") } // b4284
    val b4285 = withCtrl(x8383) { Const(true).name("b4285") } // b4285
    val b8419 = withCtrl(x8383) { StreamOut(field="offset").name("b8419").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:186:54") } // x8358 = StreamOutNew(BurstCmdBus)
    isAccum(b8419) = false
    bufferDepthOf(b8419) = 1
    val b8420 = withCtrl(x8383) { StreamOut(field="size").name("b8420").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:186:54") } // x8358 = StreamOutNew(BurstCmdBus)
    isAccum(b8420) = false
    bufferDepthOf(b8420) = 1
    val x8359 = withCtrl(x8383) { StreamOut(field="data").name("x8359").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:186:54") } // x8359 = StreamOutNew(BurstFullDataBus())
    isAccum(x8359) = false
    bufferDepthOf(x8359) = 1
    val x8360 = withCtrl(x8383) { StreamIn(field="ack").name("x8360").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:186:54") } // x8360 = StreamInNew(BurstAckBus)
    isAccum(x8360) = false
    bufferDepthOf(x8360) = 1
    val x8371 = withCtrl(x8383) { UnitController(style=SeqPipe, level=InnerControl).name("x8371").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:186:54") } // UnitPipe(List(b4285),Block(x8370))
    val x8361 = withCtrl(x8371) { b4284 } // FixConvert(b4284,TRUE,_32,_0) (Same Type. No op)
    val x8362 = withCtrl(x8371) { OpDef(op=FixSla, inputs=List(x8361, Const(10))).name("x8362").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:186:54") } // FixLsh(x8361,Const(10))
    val x8363 = withCtrl(x8371) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x8364 = withCtrl(x8371) { OpDef(op=FixAdd, inputs=List(x8362, x8363)).name("x8364").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:186:54") } // FixAdd(x8362,x8363)
    val x8365 = withCtrl(x8371) { OpDef(op=FixSla, inputs=List(x8364, Const(2))).name("x8365").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:186:54") } // FixLsh(x8364,Const(2))
    val x8366 = withCtrl(x8371) { x8365 } // FixConvert(x8365,TRUE,_64,_0)
    val x8367 = withCtrl(x8371) { DramAddress(x7581).name("x8367").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:186:54") } // GetDRAMAddress(x7581)
    val x8369_x8368 = withCtrl(x8371) { OpDef(op=FixAdd, inputs=List(x8366, x8367)).name("x8369_x8368").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:186:54") } // FixAdd(x8366,x8367)
    // x8369 = SimpleStruct(ArrayBuffer((offset,x8368), (size,Const(4096)), (isLoad,Const(false))))
    val x8370_b8421_b8419 = withCtrl(x8371) { WriteMem(b8419, x8369_x8368).name("x8370_b8421_b8419").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:186:54") } // StreamWrite(x8358,x8369,b4285)
    val x8370_b8422_b8420 = withCtrl(x8371) { WriteMem(b8420, Const(4096)).name("x8370_b8422_b8420").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:186:54") } // StreamWrite(x8358,x8369,b4285)
    val x8372 = withCtrl(x8383) { Counter(min=Const(0), max=Const(1024), step=Const(1), par=64).name("x8372").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:186:54") } // CounterNew(Const(0),Const(1024),Const(1),Const(64))
    val x8373 = withCtrl(x8383) { CounterChain(List(x8372)).name("x8373").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:186:54") } // CounterChainNew(List(x8372))
    val x8379 = withCtrl(x8383) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8373).name("x8379").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:186:54") } // UnrolledForeach(List(b4285),x8373,Block(Const(())),List(List(b4302)),List(List(b4303)))
    val b4302 = withCtrl(x8379) { CounterIter(x8372, None).name("b4302") } // b4302
    val b4303 = withCtrl(x8379) { Const(true).name("b4303") } // b4303
    val x8374 = withCtrl(x8379) { OpDef(op=BitAnd, inputs=List(b4303, b4285)).name("x8374").srcCtx("UnrollingBase.scala:28:66") } // And(b4303,b4285)
    val x8375 = withCtrl(x8379) { LoadBanks(List(x7639_d0_b0), List(b4284, b4302)).name("x8375").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:186:54") } // ParSRAMLoad(x7639,List(List(b4284, b4302)),List(x8374))
    val x8377_x8376 = withCtrl(x8379) { x8375 } // VectorApply(x8375,0)
    // x8377 = SimpleStruct(ArrayBuffer((_1,x8376), (_2,Const(true))))
    val x8378_x8378_x8359 = withCtrl(x8379) { WriteMem(x8359, x8377_x8376).name("x8378_x8378_x8359").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:186:54") } // ParStreamWrite(x8359,List(x8377),List(x8374))
    val x8380 = withCtrl(x8383) { FringeDenseStore(dram=List(x7581), cmdStream=List(b8419, b8420), dataStream=List(x8359), ackStream=List(x8360)).name("x8380").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:186:54") } // FringeDenseStore(x7581,x8358,x8359,x8360)
    val x8382 = withCtrl(x8383) { UnitController(style=SeqPipe, level=InnerControl).name("x8382").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:186:54") } // UnitPipe(List(b4285),Block(Const(())))
    val x8381_x8381 = withCtrl(x8382) { ReadMem(x8360).name("x8381_x8381").srcCtx("LSTMLargeDynamicFlatten8Bit.scala:186:54") } // StreamRead(x8360,b4285)
    }; split2
    }; split1
    
  }
}
