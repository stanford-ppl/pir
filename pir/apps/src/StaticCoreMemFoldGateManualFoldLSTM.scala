import pir._
import pir.node._
import arch._
import prism.enums._

object StaticCoreMemFoldGateManualFoldLSTM extends PIRApp {
  def main(implicit design:PIRDesign) = {
    import design.pirmeta._
    val x5483 = withCtrl(design.top.topController) { DRAM(dims=List(Const(8), Const(128))).name("x5483").srcCtx("DataGenerator.scala:151:20:xInit") } // x5483 = DRAMNew(ArrayBuffer(Const(8), Const(128)),Const(0))
    val x5490 = withCtrl(design.top.topController) { DRAM(dims=List(Const(2), Const(128))).name("x5490").srcCtx("DataGenerator.scala:151:20:cInit") } // x5490 = DRAMNew(ArrayBuffer(Const(2), Const(128)),Const(0))
    val x5497 = withCtrl(design.top.topController) { DRAM(dims=List(Const(2), Const(128))).name("x5497").srcCtx("DataGenerator.scala:151:20:hInit") } // x5497 = DRAMNew(ArrayBuffer(Const(2), Const(128)),Const(0))
    val x5504 = withCtrl(design.top.topController) { DRAM(dims=List(Const(2), Const(4), Const(128))).name("x5504").srcCtx("DataGenerator.scala:163:20:bInit") } // x5504 = DRAMNew(ArrayBuffer(Const(2), Const(4), Const(128)),Const(0))
    val x5514 = withCtrl(design.top.topController) { DRAM(dims=List(Const(2), Const(128), Const(4), Const(128))).name("x5514").srcCtx("DataGenerator.scala:177:20:wxInit") } // x5514 = DRAMNew(ArrayBuffer(Const(2), Const(128), Const(4), Const(128)),Const(0))
    val x5527 = withCtrl(design.top.topController) { DRAM(dims=List(Const(2), Const(128), Const(4), Const(128))).name("x5527").srcCtx("DataGenerator.scala:177:20:whInit") } // x5527 = DRAMNew(ArrayBuffer(Const(2), Const(128), Const(4), Const(128)),Const(0))
    val x5540 = withCtrl(design.top.topController) { DRAM(dims=List(Const(8), Const(128))).name("x5540").srcCtx("IOs.scala:153:22:out") } // x5540 = DRAMNew(ArrayBuffer(Const(8), Const(128)),Const(0))
    val x5918 = withCtrl(design.top.topController) { UnitController(style=SeqPipe, level=OuterControl).name("x5918").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:15:11") } // Hwblock(Block(Const(())),false)
    val x5541_d0_b0 = withCtrl(x5918) { SRAM(size=1024, banking=Strided(banks=16, stride=1)).name("x5541_d0_b0").srcCtx("DataGenerator.scala:38:21:x") } // x5541 = SRAMNew(ArrayBuffer(Const(8), Const(128)))
    isAccum(x5541_d0_b0) = false
    bufferDepthOf(x5541_d0_b0) = 1
    staticDimsOf(x5541_d0_b0) = List(8, 128)
    val x5541_d1_b0 = withCtrl(x5918) { SRAM(size=1024, banking=Strided(banks=16, stride=1)).name("x5541_d1_b0").srcCtx("DataGenerator.scala:38:21:x") } // x5541 = SRAMNew(ArrayBuffer(Const(8), Const(128)))
    isAccum(x5541_d1_b0) = false
    bufferDepthOf(x5541_d1_b0) = 1
    staticDimsOf(x5541_d1_b0) = List(8, 128)
    val x5542 = withCtrl(x5918) { Counter(min=Const(0), max=Const(8), step=Const(1), par=1).name("x5542").srcCtx("DataGenerator.scala:39:8") } // CounterNew(Const(0),Const(8),Const(1),Const(1))
    val x5543 = withCtrl(x5918) { CounterChain(List(x5542)).name("x5543").srcCtx("DataGenerator.scala:39:8") } // CounterChainNew(List(x5542))
    val x5565 = withCtrl(x5918) { LoopController(style=StreamPipe, level=OuterControl, cchain=x5543).name("x5565").srcCtx("DataGenerator.scala:39:8") } // UnrolledForeach(List(Const(true)),x5543,Block(Const(())),List(List(b3250)),List(List(b3251)))
    val b3250 = withCtrl(x5565) { CounterIter(x5542, Some(0)).name("b3250") } // b3250
    val b3251 = withCtrl(x5565) { Const(true).name("b3251") } // b3251
    val b5929 = withCtrl(x5565) { StreamOut(field="offset").name("b5929").srcCtx("DataGenerator.scala:39:8") } // x5544 = StreamOutNew(BurstCmdBus)
    isAccum(b5929) = false
    bufferDepthOf(b5929) = 1
    val b5930 = withCtrl(x5565) { StreamOut(field="size").name("b5930").srcCtx("DataGenerator.scala:39:8") } // x5544 = StreamOutNew(BurstCmdBus)
    isAccum(b5930) = false
    bufferDepthOf(b5930) = 1
    val x5545 = withCtrl(x5565) { StreamIn(field="data").name("x5545").srcCtx("DataGenerator.scala:39:8") } // x5545 = StreamInNew(BurstDataBus())
    isAccum(x5545) = false
    bufferDepthOf(x5545) = 1
    val x5556 = withCtrl(x5565) { UnitController(style=SeqPipe, level=InnerControl).name("x5556").srcCtx("DataGenerator.scala:39:8") } // UnitPipe(List(b3251),Block(x5555))
    val x5546 = withCtrl(x5556) { b3250 } // FixConvert(b3250,TRUE,_32,_0) (Same Type. No op)
    val x5547 = withCtrl(x5556) { OpDef(op=FixSla, inputs=List(x5546, Const(7))).name("x5547").srcCtx("DataGenerator.scala:39:8") } // FixLsh(x5546,Const(7))
    val x5548 = withCtrl(x5556) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x5549 = withCtrl(x5556) { OpDef(op=FixAdd, inputs=List(x5547, x5548)).name("x5549").srcCtx("DataGenerator.scala:39:8") } // FixAdd(x5547,x5548)
    val x5550 = withCtrl(x5556) { OpDef(op=FixSla, inputs=List(x5549, Const(2))).name("x5550").srcCtx("DataGenerator.scala:39:8") } // FixLsh(x5549,Const(2))
    val x5551 = withCtrl(x5556) { x5550 } // FixConvert(x5550,TRUE,_64,_0)
    val x5552 = withCtrl(x5556) { DramAddress(x5483).name("x5552").srcCtx("DataGenerator.scala:39:8") } // GetDRAMAddress(x5483)
    val x5554_x5553 = withCtrl(x5556) { OpDef(op=FixAdd, inputs=List(x5551, x5552)).name("x5554_x5553").srcCtx("DataGenerator.scala:39:8") } // FixAdd(x5551,x5552)
    // x5554 = SimpleStruct(ArrayBuffer((offset,x5553), (size,Const(512)), (isLoad,Const(true))))
    val x5555_b5931_b5929 = withCtrl(x5556) { WriteMem(b5929, x5554_x5553).name("x5555_b5931_b5929").srcCtx("DataGenerator.scala:39:8") } // StreamWrite(x5544,x5554,b3251)
    val x5555_b5932_b5930 = withCtrl(x5556) { WriteMem(b5930, Const(512)).name("x5555_b5932_b5930").srcCtx("DataGenerator.scala:39:8") } // StreamWrite(x5544,x5554,b3251)
    val x5557 = withCtrl(x5565) { FringeDenseLoad(dram=List(x5483), cmdStream=List(b5929, b5930), dataStream=List(x5545)).name("x5557").srcCtx("DataGenerator.scala:39:8") } // FringeDenseLoad(x5483,x5544,x5545)
    val x5558 = withCtrl(x5565) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x5558").srcCtx("DataGenerator.scala:39:8") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x5559 = withCtrl(x5565) { CounterChain(List(x5558)).name("x5559").srcCtx("DataGenerator.scala:39:8") } // CounterChainNew(List(x5558))
    val x5564 = withCtrl(x5565) { LoopController(style=InnerPipe, level=InnerControl, cchain=x5559).name("x5564").srcCtx("DataGenerator.scala:39:8") } // UnrolledForeach(List(b3251),x5559,Block(Const(())),List(List(b3268)),List(List(b3269)))
    val b3268 = withCtrl(x5564) { CounterIter(x5558, None).name("b3268") } // b3268
    val b3269 = withCtrl(x5564) { Const(true).name("b3269") } // b3269
    val x5560 = withCtrl(x5564) { OpDef(op=BitAnd, inputs=List(b3269, b3251)).name("x5560").srcCtx("UnrollingBase.scala:28:66") } // And(b3269,b3251)
    val x5561_x5561 = withCtrl(x5564) { ReadMem(x5545).name("x5561_x5561").srcCtx("DataGenerator.scala:39:8") } // ParStreamRead(x5545,List(x5560))
    val x5562_x5562 = withCtrl(x5564) { x5561_x5561 } // VectorApply(x5561,0)
    val x5563 = withCtrl(x5564) { StoreBanks(List(List(x5541_d0_b0), List(x5541_d1_b0)), List(b3250, b3268), x5562_x5562).name("x5563").srcCtx("DataGenerator.scala:39:8") } // ParSRAMStore(x5541,List(List(b3250, b3268)),List(x5562),List(x5560))
    val x5566_d0_b0 = withCtrl(x5918) { SRAM(size=256, banking=Strided(banks=16, stride=1)).name("x5566_d0_b0").srcCtx("DataGenerator.scala:38:21:c") } // x5566 = SRAMNew(ArrayBuffer(Const(2), Const(128)))
    isAccum(x5566_d0_b0) = false
    bufferDepthOf(x5566_d0_b0) = 1
    staticDimsOf(x5566_d0_b0) = List(2, 128)
    val x5567 = withCtrl(x5918) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x5567").srcCtx("DataGenerator.scala:39:8") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x5568 = withCtrl(x5918) { CounterChain(List(x5567)).name("x5568").srcCtx("DataGenerator.scala:39:8") } // CounterChainNew(List(x5567))
    val x5590 = withCtrl(x5918) { LoopController(style=StreamPipe, level=OuterControl, cchain=x5568).name("x5590").srcCtx("DataGenerator.scala:39:8") } // UnrolledForeach(List(Const(true)),x5568,Block(Const(())),List(List(b3279)),List(List(b3280)))
    val b3279 = withCtrl(x5590) { CounterIter(x5567, Some(0)).name("b3279") } // b3279
    val b3280 = withCtrl(x5590) { Const(true).name("b3280") } // b3280
    val b5933 = withCtrl(x5590) { StreamOut(field="offset").name("b5933").srcCtx("DataGenerator.scala:39:8") } // x5569 = StreamOutNew(BurstCmdBus)
    isAccum(b5933) = false
    bufferDepthOf(b5933) = 1
    val b5934 = withCtrl(x5590) { StreamOut(field="size").name("b5934").srcCtx("DataGenerator.scala:39:8") } // x5569 = StreamOutNew(BurstCmdBus)
    isAccum(b5934) = false
    bufferDepthOf(b5934) = 1
    val x5570 = withCtrl(x5590) { StreamIn(field="data").name("x5570").srcCtx("DataGenerator.scala:39:8") } // x5570 = StreamInNew(BurstDataBus())
    isAccum(x5570) = false
    bufferDepthOf(x5570) = 1
    val x5581 = withCtrl(x5590) { UnitController(style=SeqPipe, level=InnerControl).name("x5581").srcCtx("DataGenerator.scala:39:8") } // UnitPipe(List(b3280),Block(x5580))
    val x5571 = withCtrl(x5581) { b3279 } // FixConvert(b3279,TRUE,_32,_0) (Same Type. No op)
    val x5572 = withCtrl(x5581) { OpDef(op=FixSla, inputs=List(x5571, Const(7))).name("x5572").srcCtx("DataGenerator.scala:39:8") } // FixLsh(x5571,Const(7))
    val x5573 = withCtrl(x5581) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x5574 = withCtrl(x5581) { OpDef(op=FixAdd, inputs=List(x5572, x5573)).name("x5574").srcCtx("DataGenerator.scala:39:8") } // FixAdd(x5572,x5573)
    val x5575 = withCtrl(x5581) { OpDef(op=FixSla, inputs=List(x5574, Const(2))).name("x5575").srcCtx("DataGenerator.scala:39:8") } // FixLsh(x5574,Const(2))
    val x5576 = withCtrl(x5581) { x5575 } // FixConvert(x5575,TRUE,_64,_0)
    val x5577 = withCtrl(x5581) { DramAddress(x5490).name("x5577").srcCtx("DataGenerator.scala:39:8") } // GetDRAMAddress(x5490)
    val x5579_x5578 = withCtrl(x5581) { OpDef(op=FixAdd, inputs=List(x5576, x5577)).name("x5579_x5578").srcCtx("DataGenerator.scala:39:8") } // FixAdd(x5576,x5577)
    // x5579 = SimpleStruct(ArrayBuffer((offset,x5578), (size,Const(512)), (isLoad,Const(true))))
    val x5580_b5935_b5933 = withCtrl(x5581) { WriteMem(b5933, x5579_x5578).name("x5580_b5935_b5933").srcCtx("DataGenerator.scala:39:8") } // StreamWrite(x5569,x5579,b3280)
    val x5580_b5936_b5934 = withCtrl(x5581) { WriteMem(b5934, Const(512)).name("x5580_b5936_b5934").srcCtx("DataGenerator.scala:39:8") } // StreamWrite(x5569,x5579,b3280)
    val x5582 = withCtrl(x5590) { FringeDenseLoad(dram=List(x5490), cmdStream=List(b5933, b5934), dataStream=List(x5570)).name("x5582").srcCtx("DataGenerator.scala:39:8") } // FringeDenseLoad(x5490,x5569,x5570)
    val x5583 = withCtrl(x5590) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x5583").srcCtx("DataGenerator.scala:39:8") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x5584 = withCtrl(x5590) { CounterChain(List(x5583)).name("x5584").srcCtx("DataGenerator.scala:39:8") } // CounterChainNew(List(x5583))
    val x5589 = withCtrl(x5590) { LoopController(style=InnerPipe, level=InnerControl, cchain=x5584).name("x5589").srcCtx("DataGenerator.scala:39:8") } // UnrolledForeach(List(b3280),x5584,Block(Const(())),List(List(b3297)),List(List(b3298)))
    val b3297 = withCtrl(x5589) { CounterIter(x5583, None).name("b3297") } // b3297
    val b3298 = withCtrl(x5589) { Const(true).name("b3298") } // b3298
    val x5585 = withCtrl(x5589) { OpDef(op=BitAnd, inputs=List(b3298, b3280)).name("x5585").srcCtx("UnrollingBase.scala:28:66") } // And(b3298,b3280)
    val x5586_x5586 = withCtrl(x5589) { ReadMem(x5570).name("x5586_x5586").srcCtx("DataGenerator.scala:39:8") } // ParStreamRead(x5570,List(x5585))
    val x5587_x5587 = withCtrl(x5589) { x5586_x5586 } // VectorApply(x5586,0)
    val x5588 = withCtrl(x5589) { StoreBanks(List(List(x5566_d0_b0)), List(b3279, b3297), x5587_x5587).name("x5588").srcCtx("DataGenerator.scala:39:8") } // ParSRAMStore(x5566,List(List(b3279, b3297)),List(x5587),List(x5585))
    val x5591_d0_b0 = withCtrl(x5918) { SRAM(size=256, banking=Strided(banks=16, stride=1)).name("x5591_d0_b0").srcCtx("DataGenerator.scala:38:21:h") } // x5591 = SRAMNew(ArrayBuffer(Const(2), Const(128)))
    isAccum(x5591_d0_b0) = false
    bufferDepthOf(x5591_d0_b0) = 1
    staticDimsOf(x5591_d0_b0) = List(2, 128)
    val x5591_d1_b0 = withCtrl(x5918) { SRAM(size=256, banking=Strided(banks=16, stride=1)).name("x5591_d1_b0").srcCtx("DataGenerator.scala:38:21:h") } // x5591 = SRAMNew(ArrayBuffer(Const(2), Const(128)))
    isAccum(x5591_d1_b0) = false
    bufferDepthOf(x5591_d1_b0) = 1
    staticDimsOf(x5591_d1_b0) = List(2, 128)
    val x5592 = withCtrl(x5918) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x5592").srcCtx("DataGenerator.scala:39:8") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x5593 = withCtrl(x5918) { CounterChain(List(x5592)).name("x5593").srcCtx("DataGenerator.scala:39:8") } // CounterChainNew(List(x5592))
    val x5615 = withCtrl(x5918) { LoopController(style=StreamPipe, level=OuterControl, cchain=x5593).name("x5615").srcCtx("DataGenerator.scala:39:8") } // UnrolledForeach(List(Const(true)),x5593,Block(Const(())),List(List(b3308)),List(List(b3309)))
    val b3308 = withCtrl(x5615) { CounterIter(x5592, Some(0)).name("b3308") } // b3308
    val b3309 = withCtrl(x5615) { Const(true).name("b3309") } // b3309
    val b5937 = withCtrl(x5615) { StreamOut(field="offset").name("b5937").srcCtx("DataGenerator.scala:39:8") } // x5594 = StreamOutNew(BurstCmdBus)
    isAccum(b5937) = false
    bufferDepthOf(b5937) = 1
    val b5938 = withCtrl(x5615) { StreamOut(field="size").name("b5938").srcCtx("DataGenerator.scala:39:8") } // x5594 = StreamOutNew(BurstCmdBus)
    isAccum(b5938) = false
    bufferDepthOf(b5938) = 1
    val x5595 = withCtrl(x5615) { StreamIn(field="data").name("x5595").srcCtx("DataGenerator.scala:39:8") } // x5595 = StreamInNew(BurstDataBus())
    isAccum(x5595) = false
    bufferDepthOf(x5595) = 1
    val x5606 = withCtrl(x5615) { UnitController(style=SeqPipe, level=InnerControl).name("x5606").srcCtx("DataGenerator.scala:39:8") } // UnitPipe(List(b3309),Block(x5605))
    val x5596 = withCtrl(x5606) { b3308 } // FixConvert(b3308,TRUE,_32,_0) (Same Type. No op)
    val x5597 = withCtrl(x5606) { OpDef(op=FixSla, inputs=List(x5596, Const(7))).name("x5597").srcCtx("DataGenerator.scala:39:8") } // FixLsh(x5596,Const(7))
    val x5598 = withCtrl(x5606) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x5599 = withCtrl(x5606) { OpDef(op=FixAdd, inputs=List(x5597, x5598)).name("x5599").srcCtx("DataGenerator.scala:39:8") } // FixAdd(x5597,x5598)
    val x5600 = withCtrl(x5606) { OpDef(op=FixSla, inputs=List(x5599, Const(2))).name("x5600").srcCtx("DataGenerator.scala:39:8") } // FixLsh(x5599,Const(2))
    val x5601 = withCtrl(x5606) { x5600 } // FixConvert(x5600,TRUE,_64,_0)
    val x5602 = withCtrl(x5606) { DramAddress(x5497).name("x5602").srcCtx("DataGenerator.scala:39:8") } // GetDRAMAddress(x5497)
    val x5604_x5603 = withCtrl(x5606) { OpDef(op=FixAdd, inputs=List(x5601, x5602)).name("x5604_x5603").srcCtx("DataGenerator.scala:39:8") } // FixAdd(x5601,x5602)
    // x5604 = SimpleStruct(ArrayBuffer((offset,x5603), (size,Const(512)), (isLoad,Const(true))))
    val x5605_b5939_b5937 = withCtrl(x5606) { WriteMem(b5937, x5604_x5603).name("x5605_b5939_b5937").srcCtx("DataGenerator.scala:39:8") } // StreamWrite(x5594,x5604,b3309)
    val x5605_b5940_b5938 = withCtrl(x5606) { WriteMem(b5938, Const(512)).name("x5605_b5940_b5938").srcCtx("DataGenerator.scala:39:8") } // StreamWrite(x5594,x5604,b3309)
    val x5607 = withCtrl(x5615) { FringeDenseLoad(dram=List(x5497), cmdStream=List(b5937, b5938), dataStream=List(x5595)).name("x5607").srcCtx("DataGenerator.scala:39:8") } // FringeDenseLoad(x5497,x5594,x5595)
    val x5608 = withCtrl(x5615) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x5608").srcCtx("DataGenerator.scala:39:8") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x5609 = withCtrl(x5615) { CounterChain(List(x5608)).name("x5609").srcCtx("DataGenerator.scala:39:8") } // CounterChainNew(List(x5608))
    val x5614 = withCtrl(x5615) { LoopController(style=InnerPipe, level=InnerControl, cchain=x5609).name("x5614").srcCtx("DataGenerator.scala:39:8") } // UnrolledForeach(List(b3309),x5609,Block(Const(())),List(List(b3326)),List(List(b3327)))
    val b3326 = withCtrl(x5614) { CounterIter(x5608, None).name("b3326") } // b3326
    val b3327 = withCtrl(x5614) { Const(true).name("b3327") } // b3327
    val x5610 = withCtrl(x5614) { OpDef(op=BitAnd, inputs=List(b3327, b3309)).name("x5610").srcCtx("UnrollingBase.scala:28:66") } // And(b3327,b3309)
    val x5611_x5611 = withCtrl(x5614) { ReadMem(x5595).name("x5611_x5611").srcCtx("DataGenerator.scala:39:8") } // ParStreamRead(x5595,List(x5610))
    val x5612_x5612 = withCtrl(x5614) { x5611_x5611 } // VectorApply(x5611,0)
    val x5613 = withCtrl(x5614) { StoreBanks(List(List(x5591_d0_b0), List(x5591_d1_b0)), List(b3308, b3326), x5612_x5612).name("x5613").srcCtx("DataGenerator.scala:39:8") } // ParSRAMStore(x5591,List(List(b3308, b3326)),List(x5612),List(x5610))
    val x5616_d0_b0 = withCtrl(x5918) { SRAM(size=131072, banking=Strided(banks=16, stride=1)).name("x5616_d0_b0").srcCtx("DataGenerator.scala:71:21:wx") } // x5616 = SRAMNew(ArrayBuffer(Const(2), Const(128), Const(4), Const(128)))
    isAccum(x5616_d0_b0) = false
    bufferDepthOf(x5616_d0_b0) = 1
    staticDimsOf(x5616_d0_b0) = List(2, 128, 4, 128)
    val x5617 = withCtrl(x5918) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x5617").srcCtx("DataGenerator.scala:72:8") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x5618 = withCtrl(x5918) { Counter(min=Const(0), max=Const(128), step=Const(1), par=1).name("x5618").srcCtx("DataGenerator.scala:72:8") } // CounterNew(Const(0),Const(128),Const(1),Const(1))
    val x5619 = withCtrl(x5918) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x5619").srcCtx("DataGenerator.scala:72:8") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x5620 = withCtrl(x5918) { CounterChain(List(x5617,x5618,x5619)).name("x5620").srcCtx("DataGenerator.scala:72:8") } // CounterChainNew(List(x5617, x5618, x5619))
    val x5652 = withCtrl(x5918) { LoopController(style=StreamPipe, level=OuterControl, cchain=x5620).name("x5652").srcCtx("DataGenerator.scala:72:8") } // UnrolledForeach(List(Const(true)),x5620,Block(Const(())),List(List(b3339), List(b3340), List(b3341)),List(List(b3342), List(b3343), List(b3344)))
    val b3339 = withCtrl(x5652) { CounterIter(x5617, Some(0)).name("b3339") } // b3339
    val b3342 = withCtrl(x5652) { Const(true).name("b3342") } // b3342
    val b3340 = withCtrl(x5652) { CounterIter(x5618, Some(0)).name("b3340") } // b3340
    val b3343 = withCtrl(x5652) { Const(true).name("b3343") } // b3343
    val b3341 = withCtrl(x5652) { CounterIter(x5619, Some(0)).name("b3341") } // b3341
    val b3344 = withCtrl(x5652) { Const(true).name("b3344") } // b3344
    val b5941 = withCtrl(x5652) { StreamOut(field="offset").name("b5941").srcCtx("DataGenerator.scala:72:8") } // x5621 = StreamOutNew(BurstCmdBus)
    isAccum(b5941) = false
    bufferDepthOf(b5941) = 1
    val b5942 = withCtrl(x5652) { StreamOut(field="size").name("b5942").srcCtx("DataGenerator.scala:72:8") } // x5621 = StreamOutNew(BurstCmdBus)
    isAccum(b5942) = false
    bufferDepthOf(b5942) = 1
    val x5622 = withCtrl(x5652) { StreamIn(field="data").name("x5622").srcCtx("DataGenerator.scala:72:8") } // x5622 = StreamInNew(BurstDataBus())
    isAccum(x5622) = false
    bufferDepthOf(x5622) = 1
    val x5641 = withCtrl(x5652) { UnitController(style=SeqPipe, level=InnerControl).name("x5641").srcCtx("DataGenerator.scala:72:8") } // UnitPipe(List(b3342, b3343, b3344),Block(x5640))
    val x5623 = withCtrl(x5641) { b3339 } // FixConvert(b3339,TRUE,_32,_0) (Same Type. No op)
    val x5624 = withCtrl(x5641) { OpDef(op=FixSla, inputs=List(x5623, Const(16))).name("x5624").srcCtx("DataGenerator.scala:72:8") } // FixLsh(x5623,Const(16))
    val x5625 = withCtrl(x5641) { b3340 } // FixConvert(b3340,TRUE,_32,_0) (Same Type. No op)
    val x5626 = withCtrl(x5641) { OpDef(op=FixSla, inputs=List(x5625, Const(9))).name("x5626").srcCtx("DataGenerator.scala:72:8") } // FixLsh(x5625,Const(9))
    val x5627 = withCtrl(x5641) { b3341 } // FixConvert(b3341,TRUE,_32,_0) (Same Type. No op)
    val x5628 = withCtrl(x5641) { OpDef(op=FixSla, inputs=List(x5627, Const(7))).name("x5628").srcCtx("DataGenerator.scala:72:8") } // FixLsh(x5627,Const(7))
    val x5629 = withCtrl(x5641) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x5630 = withCtrl(x5641) { OpDef(op=FixAdd, inputs=List(x5624, x5626)).name("x5630").srcCtx("DataGenerator.scala:72:8") } // FixAdd(x5624,x5626)
    val x5631 = withCtrl(x5641) { OpDef(op=FixAdd, inputs=List(x5628, x5629)).name("x5631").srcCtx("DataGenerator.scala:72:8") } // FixAdd(x5628,x5629)
    val x5632 = withCtrl(x5641) { OpDef(op=FixAdd, inputs=List(x5630, x5631)).name("x5632").srcCtx("DataGenerator.scala:72:8") } // FixAdd(x5630,x5631)
    val x5633 = withCtrl(x5641) { OpDef(op=FixSla, inputs=List(x5632, Const(2))).name("x5633").srcCtx("DataGenerator.scala:72:8") } // FixLsh(x5632,Const(2))
    val x5634 = withCtrl(x5641) { x5633 } // FixConvert(x5633,TRUE,_64,_0)
    val x5635 = withCtrl(x5641) { DramAddress(x5514).name("x5635").srcCtx("DataGenerator.scala:72:8") } // GetDRAMAddress(x5514)
    val x5637_x5636 = withCtrl(x5641) { OpDef(op=FixAdd, inputs=List(x5634, x5635)).name("x5637_x5636").srcCtx("DataGenerator.scala:72:8") } // FixAdd(x5634,x5635)
    // x5637 = SimpleStruct(ArrayBuffer((offset,x5636), (size,Const(512)), (isLoad,Const(true))))
    val x5638 = withCtrl(x5641) { OpDef(op=BitAnd, inputs=List(b3342, b3343)).name("x5638").srcCtx("UnrollingBase.scala:28:66") } // And(b3342,b3343)
    val x5639 = withCtrl(x5641) { OpDef(op=BitAnd, inputs=List(x5638, b3344)).name("x5639").srcCtx("UnrollingBase.scala:28:66") } // And(x5638,b3344)
    val x5640_b5943_b5941 = withCtrl(x5641) { WriteMem(b5941, x5637_x5636).name("x5640_b5943_b5941").srcCtx("DataGenerator.scala:72:8") } // StreamWrite(x5621,x5637,x5639)
    val x5640_b5944_b5942 = withCtrl(x5641) { WriteMem(b5942, Const(512)).name("x5640_b5944_b5942").srcCtx("DataGenerator.scala:72:8") } // StreamWrite(x5621,x5637,x5639)
    val x5642 = withCtrl(x5652) { FringeDenseLoad(dram=List(x5514), cmdStream=List(b5941, b5942), dataStream=List(x5622)).name("x5642").srcCtx("DataGenerator.scala:72:8") } // FringeDenseLoad(x5514,x5621,x5622)
    val x5643 = withCtrl(x5652) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x5643").srcCtx("DataGenerator.scala:72:8") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x5644 = withCtrl(x5652) { CounterChain(List(x5643)).name("x5644").srcCtx("DataGenerator.scala:72:8") } // CounterChainNew(List(x5643))
    val x5651 = withCtrl(x5652) { LoopController(style=InnerPipe, level=InnerControl, cchain=x5644).name("x5651").srcCtx("DataGenerator.scala:72:8") } // UnrolledForeach(List(b3342, b3343, b3344),x5644,Block(Const(())),List(List(b3369)),List(List(b3370)))
    val b3369 = withCtrl(x5651) { CounterIter(x5643, None).name("b3369") } // b3369
    val b3370 = withCtrl(x5651) { Const(true).name("b3370") } // b3370
    val x5645 = withCtrl(x5651) { OpDef(op=BitAnd, inputs=List(b3370, b3342)).name("x5645").srcCtx("UnrollingBase.scala:28:66") } // And(b3370,b3342)
    val x5646 = withCtrl(x5651) { OpDef(op=BitAnd, inputs=List(b3343, b3344)).name("x5646").srcCtx("UnrollingBase.scala:28:66") } // And(b3343,b3344)
    val x5647 = withCtrl(x5651) { OpDef(op=BitAnd, inputs=List(x5645, x5646)).name("x5647").srcCtx("UnrollingBase.scala:28:66") } // And(x5645,x5646)
    val x5648_x5648 = withCtrl(x5651) { ReadMem(x5622).name("x5648_x5648").srcCtx("DataGenerator.scala:72:8") } // ParStreamRead(x5622,List(x5647))
    val x5649_x5649 = withCtrl(x5651) { x5648_x5648 } // VectorApply(x5648,0)
    val x5650 = withCtrl(x5651) { StoreBanks(List(List(x5616_d0_b0)), List(b3339, b3340, b3341, b3369), x5649_x5649).name("x5650").srcCtx("DataGenerator.scala:72:8") } // ParSRAMStore(x5616,List(List(b3339, b3340, b3341, b3369)),List(x5649),List(x5647))
    val x5653_d0_b0 = withCtrl(x5918) { SRAM(size=131072, banking=Strided(banks=16, stride=1)).name("x5653_d0_b0").srcCtx("DataGenerator.scala:71:21:wh") } // x5653 = SRAMNew(ArrayBuffer(Const(2), Const(128), Const(4), Const(128)))
    isAccum(x5653_d0_b0) = false
    bufferDepthOf(x5653_d0_b0) = 1
    staticDimsOf(x5653_d0_b0) = List(2, 128, 4, 128)
    val x5654 = withCtrl(x5918) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x5654").srcCtx("DataGenerator.scala:72:8") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x5655 = withCtrl(x5918) { Counter(min=Const(0), max=Const(128), step=Const(1), par=1).name("x5655").srcCtx("DataGenerator.scala:72:8") } // CounterNew(Const(0),Const(128),Const(1),Const(1))
    val x5656 = withCtrl(x5918) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x5656").srcCtx("DataGenerator.scala:72:8") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x5657 = withCtrl(x5918) { CounterChain(List(x5654,x5655,x5656)).name("x5657").srcCtx("DataGenerator.scala:72:8") } // CounterChainNew(List(x5654, x5655, x5656))
    val x5689 = withCtrl(x5918) { LoopController(style=StreamPipe, level=OuterControl, cchain=x5657).name("x5689").srcCtx("DataGenerator.scala:72:8") } // UnrolledForeach(List(Const(true)),x5657,Block(Const(())),List(List(b3384), List(b3385), List(b3386)),List(List(b3387), List(b3388), List(b3389)))
    val b3384 = withCtrl(x5689) { CounterIter(x5654, Some(0)).name("b3384") } // b3384
    val b3387 = withCtrl(x5689) { Const(true).name("b3387") } // b3387
    val b3385 = withCtrl(x5689) { CounterIter(x5655, Some(0)).name("b3385") } // b3385
    val b3388 = withCtrl(x5689) { Const(true).name("b3388") } // b3388
    val b3386 = withCtrl(x5689) { CounterIter(x5656, Some(0)).name("b3386") } // b3386
    val b3389 = withCtrl(x5689) { Const(true).name("b3389") } // b3389
    val b5945 = withCtrl(x5689) { StreamOut(field="offset").name("b5945").srcCtx("DataGenerator.scala:72:8") } // x5658 = StreamOutNew(BurstCmdBus)
    isAccum(b5945) = false
    bufferDepthOf(b5945) = 1
    val b5946 = withCtrl(x5689) { StreamOut(field="size").name("b5946").srcCtx("DataGenerator.scala:72:8") } // x5658 = StreamOutNew(BurstCmdBus)
    isAccum(b5946) = false
    bufferDepthOf(b5946) = 1
    val x5659 = withCtrl(x5689) { StreamIn(field="data").name("x5659").srcCtx("DataGenerator.scala:72:8") } // x5659 = StreamInNew(BurstDataBus())
    isAccum(x5659) = false
    bufferDepthOf(x5659) = 1
    val x5678 = withCtrl(x5689) { UnitController(style=SeqPipe, level=InnerControl).name("x5678").srcCtx("DataGenerator.scala:72:8") } // UnitPipe(List(b3387, b3388, b3389),Block(x5677))
    val x5660 = withCtrl(x5678) { b3384 } // FixConvert(b3384,TRUE,_32,_0) (Same Type. No op)
    val x5661 = withCtrl(x5678) { OpDef(op=FixSla, inputs=List(x5660, Const(16))).name("x5661").srcCtx("DataGenerator.scala:72:8") } // FixLsh(x5660,Const(16))
    val x5662 = withCtrl(x5678) { b3385 } // FixConvert(b3385,TRUE,_32,_0) (Same Type. No op)
    val x5663 = withCtrl(x5678) { OpDef(op=FixSla, inputs=List(x5662, Const(9))).name("x5663").srcCtx("DataGenerator.scala:72:8") } // FixLsh(x5662,Const(9))
    val x5664 = withCtrl(x5678) { b3386 } // FixConvert(b3386,TRUE,_32,_0) (Same Type. No op)
    val x5665 = withCtrl(x5678) { OpDef(op=FixSla, inputs=List(x5664, Const(7))).name("x5665").srcCtx("DataGenerator.scala:72:8") } // FixLsh(x5664,Const(7))
    val x5666 = withCtrl(x5678) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x5667 = withCtrl(x5678) { OpDef(op=FixAdd, inputs=List(x5661, x5663)).name("x5667").srcCtx("DataGenerator.scala:72:8") } // FixAdd(x5661,x5663)
    val x5668 = withCtrl(x5678) { OpDef(op=FixAdd, inputs=List(x5665, x5666)).name("x5668").srcCtx("DataGenerator.scala:72:8") } // FixAdd(x5665,x5666)
    val x5669 = withCtrl(x5678) { OpDef(op=FixAdd, inputs=List(x5667, x5668)).name("x5669").srcCtx("DataGenerator.scala:72:8") } // FixAdd(x5667,x5668)
    val x5670 = withCtrl(x5678) { OpDef(op=FixSla, inputs=List(x5669, Const(2))).name("x5670").srcCtx("DataGenerator.scala:72:8") } // FixLsh(x5669,Const(2))
    val x5671 = withCtrl(x5678) { x5670 } // FixConvert(x5670,TRUE,_64,_0)
    val x5672 = withCtrl(x5678) { DramAddress(x5527).name("x5672").srcCtx("DataGenerator.scala:72:8") } // GetDRAMAddress(x5527)
    val x5674_x5673 = withCtrl(x5678) { OpDef(op=FixAdd, inputs=List(x5671, x5672)).name("x5674_x5673").srcCtx("DataGenerator.scala:72:8") } // FixAdd(x5671,x5672)
    // x5674 = SimpleStruct(ArrayBuffer((offset,x5673), (size,Const(512)), (isLoad,Const(true))))
    val x5675 = withCtrl(x5678) { OpDef(op=BitAnd, inputs=List(b3387, b3388)).name("x5675").srcCtx("UnrollingBase.scala:28:66") } // And(b3387,b3388)
    val x5676 = withCtrl(x5678) { OpDef(op=BitAnd, inputs=List(x5675, b3389)).name("x5676").srcCtx("UnrollingBase.scala:28:66") } // And(x5675,b3389)
    val x5677_b5947_b5945 = withCtrl(x5678) { WriteMem(b5945, x5674_x5673).name("x5677_b5947_b5945").srcCtx("DataGenerator.scala:72:8") } // StreamWrite(x5658,x5674,x5676)
    val x5677_b5948_b5946 = withCtrl(x5678) { WriteMem(b5946, Const(512)).name("x5677_b5948_b5946").srcCtx("DataGenerator.scala:72:8") } // StreamWrite(x5658,x5674,x5676)
    val x5679 = withCtrl(x5689) { FringeDenseLoad(dram=List(x5527), cmdStream=List(b5945, b5946), dataStream=List(x5659)).name("x5679").srcCtx("DataGenerator.scala:72:8") } // FringeDenseLoad(x5527,x5658,x5659)
    val x5680 = withCtrl(x5689) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x5680").srcCtx("DataGenerator.scala:72:8") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x5681 = withCtrl(x5689) { CounterChain(List(x5680)).name("x5681").srcCtx("DataGenerator.scala:72:8") } // CounterChainNew(List(x5680))
    val x5688 = withCtrl(x5689) { LoopController(style=InnerPipe, level=InnerControl, cchain=x5681).name("x5688").srcCtx("DataGenerator.scala:72:8") } // UnrolledForeach(List(b3387, b3388, b3389),x5681,Block(Const(())),List(List(b3414)),List(List(b3415)))
    val b3414 = withCtrl(x5688) { CounterIter(x5680, None).name("b3414") } // b3414
    val b3415 = withCtrl(x5688) { Const(true).name("b3415") } // b3415
    val x5682 = withCtrl(x5688) { OpDef(op=BitAnd, inputs=List(b3415, b3387)).name("x5682").srcCtx("UnrollingBase.scala:28:66") } // And(b3415,b3387)
    val x5683 = withCtrl(x5688) { OpDef(op=BitAnd, inputs=List(b3388, b3389)).name("x5683").srcCtx("UnrollingBase.scala:28:66") } // And(b3388,b3389)
    val x5684 = withCtrl(x5688) { OpDef(op=BitAnd, inputs=List(x5682, x5683)).name("x5684").srcCtx("UnrollingBase.scala:28:66") } // And(x5682,x5683)
    val x5685_x5685 = withCtrl(x5688) { ReadMem(x5659).name("x5685_x5685").srcCtx("DataGenerator.scala:72:8") } // ParStreamRead(x5659,List(x5684))
    val x5686_x5686 = withCtrl(x5688) { x5685_x5685 } // VectorApply(x5685,0)
    val x5687 = withCtrl(x5688) { StoreBanks(List(List(x5653_d0_b0)), List(b3384, b3385, b3386, b3414), x5686_x5686).name("x5687").srcCtx("DataGenerator.scala:72:8") } // ParSRAMStore(x5653,List(List(b3384, b3385, b3386, b3414)),List(x5686),List(x5684))
    val x5690_d0_b0 = withCtrl(x5918) { SRAM(size=1024, banking=Strided(banks=16, stride=1)).name("x5690_d0_b0").srcCtx("DataGenerator.scala:54:21:b") } // x5690 = SRAMNew(ArrayBuffer(Const(2), Const(4), Const(128)))
    isAccum(x5690_d0_b0) = false
    bufferDepthOf(x5690_d0_b0) = 1
    staticDimsOf(x5690_d0_b0) = List(2, 4, 128)
    val x5691 = withCtrl(x5918) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x5691").srcCtx("DataGenerator.scala:55:8") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x5692 = withCtrl(x5918) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x5692").srcCtx("DataGenerator.scala:55:8") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x5693 = withCtrl(x5918) { CounterChain(List(x5691,x5692)).name("x5693").srcCtx("DataGenerator.scala:55:8") } // CounterChainNew(List(x5691, x5692))
    val x5720 = withCtrl(x5918) { LoopController(style=StreamPipe, level=OuterControl, cchain=x5693).name("x5720").srcCtx("DataGenerator.scala:55:8") } // UnrolledForeach(List(Const(true)),x5693,Block(Const(())),List(List(b3428), List(b3429)),List(List(b3430), List(b3431)))
    val b3428 = withCtrl(x5720) { CounterIter(x5691, Some(0)).name("b3428") } // b3428
    val b3430 = withCtrl(x5720) { Const(true).name("b3430") } // b3430
    val b3429 = withCtrl(x5720) { CounterIter(x5692, Some(0)).name("b3429") } // b3429
    val b3431 = withCtrl(x5720) { Const(true).name("b3431") } // b3431
    val b5949 = withCtrl(x5720) { StreamOut(field="offset").name("b5949").srcCtx("DataGenerator.scala:55:8") } // x5694 = StreamOutNew(BurstCmdBus)
    isAccum(b5949) = false
    bufferDepthOf(b5949) = 1
    val b5950 = withCtrl(x5720) { StreamOut(field="size").name("b5950").srcCtx("DataGenerator.scala:55:8") } // x5694 = StreamOutNew(BurstCmdBus)
    isAccum(b5950) = false
    bufferDepthOf(b5950) = 1
    val x5695 = withCtrl(x5720) { StreamIn(field="data").name("x5695").srcCtx("DataGenerator.scala:55:8") } // x5695 = StreamInNew(BurstDataBus())
    isAccum(x5695) = false
    bufferDepthOf(x5695) = 1
    val x5710 = withCtrl(x5720) { UnitController(style=SeqPipe, level=InnerControl).name("x5710").srcCtx("DataGenerator.scala:55:8") } // UnitPipe(List(b3430, b3431),Block(x5709))
    val x5696 = withCtrl(x5710) { b3428 } // FixConvert(b3428,TRUE,_32,_0) (Same Type. No op)
    val x5697 = withCtrl(x5710) { OpDef(op=FixSla, inputs=List(x5696, Const(9))).name("x5697").srcCtx("DataGenerator.scala:55:8") } // FixLsh(x5696,Const(9))
    val x5698 = withCtrl(x5710) { b3429 } // FixConvert(b3429,TRUE,_32,_0) (Same Type. No op)
    val x5699 = withCtrl(x5710) { OpDef(op=FixSla, inputs=List(x5698, Const(7))).name("x5699").srcCtx("DataGenerator.scala:55:8") } // FixLsh(x5698,Const(7))
    val x5700 = withCtrl(x5710) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x5701 = withCtrl(x5710) { OpDef(op=FixAdd, inputs=List(x5697, x5699)).name("x5701").srcCtx("DataGenerator.scala:55:8") } // FixAdd(x5697,x5699)
    val x5702 = withCtrl(x5710) { OpDef(op=FixAdd, inputs=List(x5701, x5700)).name("x5702").srcCtx("DataGenerator.scala:55:8") } // FixAdd(x5701,x5700)
    val x5703 = withCtrl(x5710) { OpDef(op=FixSla, inputs=List(x5702, Const(2))).name("x5703").srcCtx("DataGenerator.scala:55:8") } // FixLsh(x5702,Const(2))
    val x5704 = withCtrl(x5710) { x5703 } // FixConvert(x5703,TRUE,_64,_0)
    val x5705 = withCtrl(x5710) { DramAddress(x5504).name("x5705").srcCtx("DataGenerator.scala:55:8") } // GetDRAMAddress(x5504)
    val x5707_x5706 = withCtrl(x5710) { OpDef(op=FixAdd, inputs=List(x5704, x5705)).name("x5707_x5706").srcCtx("DataGenerator.scala:55:8") } // FixAdd(x5704,x5705)
    // x5707 = SimpleStruct(ArrayBuffer((offset,x5706), (size,Const(512)), (isLoad,Const(true))))
    val x5708 = withCtrl(x5710) { OpDef(op=BitAnd, inputs=List(b3430, b3431)).name("x5708").srcCtx("UnrollingBase.scala:28:66") } // And(b3430,b3431)
    val x5709_b5951_b5949 = withCtrl(x5710) { WriteMem(b5949, x5707_x5706).name("x5709_b5951_b5949").srcCtx("DataGenerator.scala:55:8") } // StreamWrite(x5694,x5707,x5708)
    val x5709_b5952_b5950 = withCtrl(x5710) { WriteMem(b5950, Const(512)).name("x5709_b5952_b5950").srcCtx("DataGenerator.scala:55:8") } // StreamWrite(x5694,x5707,x5708)
    val x5711 = withCtrl(x5720) { FringeDenseLoad(dram=List(x5504), cmdStream=List(b5949, b5950), dataStream=List(x5695)).name("x5711").srcCtx("DataGenerator.scala:55:8") } // FringeDenseLoad(x5504,x5694,x5695)
    val x5712 = withCtrl(x5720) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x5712").srcCtx("DataGenerator.scala:55:8") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x5713 = withCtrl(x5720) { CounterChain(List(x5712)).name("x5713").srcCtx("DataGenerator.scala:55:8") } // CounterChainNew(List(x5712))
    val x5719 = withCtrl(x5720) { LoopController(style=InnerPipe, level=InnerControl, cchain=x5713).name("x5719").srcCtx("DataGenerator.scala:55:8") } // UnrolledForeach(List(b3430, b3431),x5713,Block(Const(())),List(List(b3452)),List(List(b3453)))
    val b3452 = withCtrl(x5719) { CounterIter(x5712, None).name("b3452") } // b3452
    val b3453 = withCtrl(x5719) { Const(true).name("b3453") } // b3453
    val x5714 = withCtrl(x5719) { OpDef(op=BitAnd, inputs=List(b3453, b3430)).name("x5714").srcCtx("UnrollingBase.scala:28:66") } // And(b3453,b3430)
    val x5715 = withCtrl(x5719) { OpDef(op=BitAnd, inputs=List(x5714, b3431)).name("x5715").srcCtx("UnrollingBase.scala:28:66") } // And(x5714,b3431)
    val x5716_x5716 = withCtrl(x5719) { ReadMem(x5695).name("x5716_x5716").srcCtx("DataGenerator.scala:55:8") } // ParStreamRead(x5695,List(x5715))
    val x5717_x5717 = withCtrl(x5719) { x5716_x5716 } // VectorApply(x5716,0)
    val x5718 = withCtrl(x5719) { StoreBanks(List(List(x5690_d0_b0)), List(b3428, b3429, b3452), x5717_x5717).name("x5718").srcCtx("DataGenerator.scala:55:8") } // ParSRAMStore(x5690,List(List(b3428, b3429, b3452)),List(x5717),List(x5715))
    val x5721 = withCtrl(x5918) { Counter(min=Const(0), max=Const(8), step=Const(1), par=1).name("x5721").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:17:28") } // CounterNew(Const(0),Const(8),Const(1),Const(1))
    val x5722 = withCtrl(x5918) { CounterChain(List(x5721)).name("x5722").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:17:41") } // CounterChainNew(List(x5721))
    val x5889 = withCtrl(x5918) { LoopController(style=MetaPipe, level=OuterControl, cchain=x5722).name("x5889").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:17:41") } // UnrolledForeach(List(Const(true)),x5722,Block(Const(())),List(List(b3463)),List(List(b3464)))
    val b3463 = withCtrl(x5889) { CounterIter(x5721, Some(0)).name("b3463") } // b3463
    val b3464 = withCtrl(x5889) { Const(true).name("b3464") } // b3464
    val x5723 = withCtrl(x5889) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x5723").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:18:31") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x5724 = withCtrl(x5889) { CounterChain(List(x5723)).name("x5724").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:18:45") } // CounterChainNew(List(x5723))
    val x5888 = withCtrl(x5889) { LoopController(style=MetaPipe, level=OuterControl, cchain=x5724).name("x5888").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:18:45") } // UnrolledForeach(List(b3464),x5724,Block(Const(())),List(List(b3467)),List(List(b3468)))
    val b3467 = withCtrl(x5888) { CounterIter(x5723, Some(0)).name("b3467") } // b3467
    val b3468 = withCtrl(x5888) { Const(true).name("b3468") } // b3468
    val x5725_d0_b0 = withCtrl(x5888) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x5725_d0_b0").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:25:32:foldMem") } // x5725 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x5725_d0_b0) = false
    bufferDepthOf(x5725_d0_b0) = 1
    staticDimsOf(x5725_d0_b0) = List(4, 128)
    val x5725_d1_b0 = withCtrl(x5888) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x5725_d1_b0").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:25:32:foldMem") } // x5725 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x5725_d1_b0) = true
    bufferDepthOf(x5725_d1_b0) = 1
    staticDimsOf(x5725_d1_b0) = List(4, 128)
    val x5887 = withCtrl(x5888) { UnitController(style=SeqPipe, level=OuterControl).name("x5887").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:26:16") } // UnitPipe(List(b3468, b3464),Block(Const(())))
    val x5726 = withCtrl(x5887) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x5726").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:27:34") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x5727 = withCtrl(x5887) { CounterChain(List(x5726)).name("x5727").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:27:47") } // CounterChainNew(List(x5726))
    val x5737 = withCtrl(x5887) { LoopController(style=MetaPipe, level=OuterControl, cchain=x5727).name("x5737").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:27:47") } // UnrolledForeach(List(b3468, b3464),x5727,Block(Const(())),List(List(b3472)),List(List(b3473)))
    val b3472 = withCtrl(x5737) { CounterIter(x5726, Some(0)).name("b3472") } // b3472
    val b3473 = withCtrl(x5737) { Const(true).name("b3473") } // b3473
    val x5728 = withCtrl(x5737) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x5728").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:28:42") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x5729 = withCtrl(x5737) { CounterChain(List(x5728)).name("x5729").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:28:56") } // CounterChainNew(List(x5728))
    val x5736 = withCtrl(x5737) { LoopController(style=InnerPipe, level=InnerControl, cchain=x5729).name("x5736").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:28:56") } // UnrolledForeach(List(b3473, b3468, b3464),x5729,Block(Const(())),List(List(b3476)),List(List(b3477)))
    val b3476 = withCtrl(x5736) { CounterIter(x5728, None).name("b3476") } // b3476
    val b3477 = withCtrl(x5736) { Const(true).name("b3477") } // b3477
    val x5730 = withCtrl(x5736) { OpDef(op=BitAnd, inputs=List(b3477, b3473)).name("x5730").srcCtx("UnrollingBase.scala:28:66") } // And(b3477,b3473)
    val x5731 = withCtrl(x5736) { OpDef(op=BitAnd, inputs=List(b3468, b3464)).name("x5731").srcCtx("UnrollingBase.scala:28:66") } // And(b3468,b3464)
    val x5732 = withCtrl(x5736) { OpDef(op=BitAnd, inputs=List(x5730, x5731)).name("x5732").srcCtx("UnrollingBase.scala:28:66") } // And(x5730,x5731)
    val x5733 = withCtrl(x5736) { LoadBanks(List(x5690_d0_b0), List(b3467, b3472, b3476)).name("x5733").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:29:50") } // ParSRAMLoad(x5690,List(List(b3467, b3472, b3476)),List(x5732))
    val x5734 = withCtrl(x5736) { x5733 } // VectorApply(x5733,0)
    val x5735 = withCtrl(x5736) { StoreBanks(List(List(x5725_d0_b0), List(x5725_d1_b0)), List(b3472, b3476), x5734).name("x5735").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:29:45") } // ParSRAMStore(x5725,List(List(b3472, b3476)),List(x5734),List(x5732))
    val x5738 = withCtrl(x5887) { Counter(min=Const(0), max=Const(128), step=Const(1), par=1).name("x5738").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:37:61") } // CounterNew(Const(0),Const(128),Const(1),Const(1))
    val x5739 = withCtrl(x5887) { CounterChain(List(x5738)).name("x5739").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:50:14") } // CounterChainNew(List(x5738))
    val x5787 = withCtrl(x5887) { LoopController(style=MetaPipe, level=OuterControl, cchain=x5739).name("x5787").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:50:14") } // UnrolledReduce(List(b3468, b3464),x5739,x5725,Block((x5725) => Const(())),List(List(b3491)),List(List(b3492)))
    val b3491 = withCtrl(x5787) { CounterIter(x5738, Some(0)).name("b3491") } // b3491
    val b3492 = withCtrl(x5787) { Const(true).name("b3492") } // b3492
    val x5740_d0_b0 = withCtrl(x5787) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x5740_d0_b0").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:38:31:re") } // x5740 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x5740_d0_b0) = false
    bufferDepthOf(x5740_d0_b0) = 2
    staticDimsOf(x5740_d0_b0) = List(4, 128)
    val x5741 = withCtrl(x5787) { Reg(init=Some(0.0)).name("x5741").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:50:14") } // x5741 = RegNew(Const(0))
    isAccum(x5741) = false
    bufferDepthOf(x5741) = 2
    val x5742 = withCtrl(x5787) { Reg(init=Some(0.0)).name("x5742").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:50:14") } // x5742 = RegNew(Const(0))
    isAccum(x5742) = false
    bufferDepthOf(x5742) = 2
    val x5749 = withCtrl(x5787) { UnitController(style=SeqPipe, level=InnerControl).name("x5749").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:50:14") } // UnitPipe(List(b3492, b3468, b3464),Block(Const(())))
    val x5743 = withCtrl(x5749) { OpDef(op=BitAnd, inputs=List(b3492, b3468)).name("x5743").srcCtx("UnrollingBase.scala:28:66") } // And(b3492,b3468)
    val x5744 = withCtrl(x5749) { OpDef(op=BitAnd, inputs=List(x5743, b3464)).name("x5744").srcCtx("UnrollingBase.scala:28:66") } // And(x5743,b3464)
    val x5745 = withCtrl(x5749) { LoadBanks(List(x5541_d1_b0), List(b3463, b3491)).name("x5745").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:39:29:xEle") } // SRAMLoad(x5541,ArrayBuffer(Const(8), Const(128)),List(b3463, b3491),Const(0),x5744)
    val x5746 = withCtrl(x5749) { LoadBanks(List(x5591_d1_b0), List(b3467, b3491)).name("x5746").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:40:29:hEle") } // SRAMLoad(x5591,ArrayBuffer(Const(2), Const(128)),List(b3467, b3491),Const(0),x5744)
    val x5747_x5741 = withCtrl(x5749) { WriteMem(x5741, x5745).name("x5747_x5741").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:50:14") } // RegWrite(x5741,x5745,x5744)
    val x5748_x5742 = withCtrl(x5749) { WriteMem(x5742, x5746).name("x5748_x5742").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:50:14") } // RegWrite(x5742,x5746,x5744)
    val x5750 = withCtrl(x5787) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x5750").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:41:36") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x5751 = withCtrl(x5787) { CounterChain(List(x5750)).name("x5751").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:41:49") } // CounterChainNew(List(x5750))
    val x5769 = withCtrl(x5787) { LoopController(style=MetaPipe, level=OuterControl, cchain=x5751).name("x5769").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:41:49") } // UnrolledForeach(List(b3492, b3468, b3464),x5751,Block(Const(())),List(List(b3505)),List(List(b3506)))
    val b3505 = withCtrl(x5769) { CounterIter(x5750, Some(0)).name("b3505") } // b3505
    val b3506 = withCtrl(x5769) { Const(true).name("b3506") } // b3506
    val x5752 = withCtrl(x5769) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x5752").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:42:44") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x5753 = withCtrl(x5769) { CounterChain(List(x5752)).name("x5753").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:42:58") } // CounterChainNew(List(x5752))
    val x5768 = withCtrl(x5769) { LoopController(style=InnerPipe, level=InnerControl, cchain=x5753).name("x5768").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:42:58") } // UnrolledForeach(List(b3506, b3492, b3468, b3464),x5753,Block(Const(())),List(List(b3509)),List(List(b3510)))
    val b3509 = withCtrl(x5768) { CounterIter(x5752, None).name("b3509") } // b3509
    val b3510 = withCtrl(x5768) { Const(true).name("b3510") } // b3510
    val x5754 = withCtrl(x5768) { OpDef(op=BitAnd, inputs=List(b3510, b3506)).name("x5754").srcCtx("UnrollingBase.scala:28:66") } // And(b3510,b3506)
    val x5755 = withCtrl(x5768) { OpDef(op=BitAnd, inputs=List(b3492, b3468)).name("x5755").srcCtx("UnrollingBase.scala:28:66") } // And(b3492,b3468)
    val x5756 = withCtrl(x5768) { OpDef(op=BitAnd, inputs=List(x5754, x5755)).name("x5756").srcCtx("UnrollingBase.scala:28:66") } // And(x5754,x5755)
    val x5757 = withCtrl(x5768) { OpDef(op=BitAnd, inputs=List(x5756, b3464)).name("x5757").srcCtx("UnrollingBase.scala:28:66") } // And(x5756,b3464)
    val x5758 = withCtrl(x5768) { LoadBanks(List(x5616_d0_b0), List(b3467, b3491, b3505, b3509)).name("x5758").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:43:33") } // ParSRAMLoad(x5616,List(List(b3467, b3491, b3505, b3509)),List(x5757))
    val x5759 = withCtrl(x5768) { x5758 } // VectorApply(x5758,0)
    val x5760 = withCtrl(x5768) { ReadMem(x5741).name("x5760").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:50:14") } // RegRead(x5741)
    val x5761 = withCtrl(x5768) { OpDef(op=FixMul, inputs=List(x5759, x5760)).name("x5761").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:43:71:reX") } // FixMul(x5759,x5760)
    val x5762 = withCtrl(x5768) { LoadBanks(List(x5653_d0_b0), List(b3467, b3491, b3505, b3509)).name("x5762").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:44:33") } // ParSRAMLoad(x5653,List(List(b3467, b3491, b3505, b3509)),List(x5757))
    val x5763 = withCtrl(x5768) { x5762 } // VectorApply(x5762,0)
    val x5764 = withCtrl(x5768) { ReadMem(x5742).name("x5764").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:50:14") } // RegRead(x5742)
    val x5765 = withCtrl(x5768) { OpDef(op=FixMul, inputs=List(x5763, x5764)).name("x5765").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:44:71:reH") } // FixMul(x5763,x5764)
    val x5766 = withCtrl(x5768) { OpDef(op=FixAdd, inputs=List(x5761, x5765)).name("x5766").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:45:48") } // FixAdd(x5761,x5765)
    val x5767 = withCtrl(x5768) { StoreBanks(List(List(x5740_d0_b0)), List(b3505, b3509), x5766).name("x5767").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:45:42") } // ParSRAMStore(x5740,List(List(b3505, b3509)),List(x5766),List(x5757))
    val x5770 = withCtrl(x5787) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x5770").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:50:14") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x5771 = withCtrl(x5787) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x5771").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:50:14") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x5772 = withCtrl(x5787) { CounterChain(List(x5771,x5770)).name("x5772").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:50:14") } // CounterChainNew(ArrayBuffer(x5771, x5770))
    val x5786 = withCtrl(x5787) { LoopController(style=InnerPipe, level=InnerControl, cchain=x5772).name("x5786").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:50:14") } // UnrolledForeach(List(),x5772,Block(Const(())),ArrayBuffer(List(b3527), List(b3528)),ArrayBuffer(List(b3529), List(b3530)))
    val b3527 = withCtrl(x5786) { CounterIter(x5771, Some(0)).name("b3527") } // b3527
    val b3529 = withCtrl(x5786) { Const(true).name("b3529") } // b3529
    val b3528 = withCtrl(x5786) { CounterIter(x5770, None).name("b3528") } // b3528
    val b3530 = withCtrl(x5786) { Const(true).name("b3530") } // b3530
    val x5773 = withCtrl(x5786) { OpDef(op=BitAnd, inputs=List(b3529, b3530)).name("x5773").srcCtx("UnrollingBase.scala:28:66") } // And(b3529,b3530)
    val x5774 = withCtrl(x5786) { OpDef(op=BitAnd, inputs=List(b3468, b3464)).name("x5774").srcCtx("UnrollingBase.scala:28:66") } // And(b3468,b3464)
    val x5775 = withCtrl(x5786) { OpDef(op=BitAnd, inputs=List(x5773, x5774)).name("x5775").srcCtx("UnrollingBase.scala:28:66") } // And(x5773,x5774)
    val x5776 = withCtrl(x5786) { LoadBanks(List(x5740_d0_b0), List(b3527, b3528)).name("x5776").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:50:14") } // ParSRAMLoad(x5740,List(ArrayBuffer(b3527, b3528)),List(x5775))
    val x5777 = withCtrl(x5786) { x5776 } // VectorApply(x5776,0)
    val x5778 = withCtrl(x5786) { LoadBanks(List(x5725_d1_b0), List(b3527, b3528)).name("x5778").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:50:14") } // ParSRAMLoad(x5725,List(ArrayBuffer(b3527, b3528)),List(x5775))
    val x5779 = withCtrl(x5786) { x5778 } // VectorApply(x5778,0)
    val x5780 = withCtrl(x5786) { OpDef(op=BitAnd, inputs=List(b3492, b3468)).name("x5780").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:50:14") } // And(b3492,b3468)
    val x5781 = withCtrl(x5786) { OpDef(op=BitAnd, inputs=List(x5780, b3464)).name("x5781").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:50:14") } // And(x5780,b3464)
    val x5782 = withCtrl(x5786) { OpDef(op=BitAnd, inputs=List(x5781, x5775)).name("x5782").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:50:14") } // And(x5781,x5775)
    val x5783 = withCtrl(x5786) { OpDef(op=FixEql, inputs=List(b3491, Const(0))).name("x5783").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:50:14") } // FixEql(b3491,Const(0))
    val x5784 = withCtrl(x5786) { OpDef(op=FixAdd, inputs=List(x5777, x5779)).name("x5784").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:50:16") } // FixAdd(x5777,x5779)
    val x5785 = withCtrl(x5786) { StoreBanks(List(List(x5725_d0_b0), List(x5725_d1_b0)), List(b3527, b3528), x5784).name("x5785").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:50:14") } // ParSRAMStore(x5725,List(ArrayBuffer(b3527, b3528)),List(x5784),List(x5775))
    antiDepsOf(x5785)=List(x5778)
    val x5788 = withCtrl(x5887) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x5788").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:64:29") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x5789 = withCtrl(x5887) { CounterChain(List(x5788)).name("x5789").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:64:35") } // CounterChainNew(List(x5788))
    val x5886 = withCtrl(x5887) { LoopController(style=MetaPipe, level=OuterControl, cchain=x5789).name("x5886").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:64:35") } // UnrolledForeach(List(b3468, b3464),x5789,Block(Const(())),List(List(b3548)),List(List(b3549)))
    val b3548 = withCtrl(x5886) { CounterIter(x5788, Some(0)).name("b3548") } // b3548
    val b3549 = withCtrl(x5886) { Const(true).name("b3549") } // b3549
    val x5790 = withCtrl(x5886) { FIFO(size=16).name("x5790").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:66:33:sigQ") } // x5790 = FIFONew(Const(16))
    isAccum(x5790) = false
    bufferDepthOf(x5790) = 2
    val x5791 = withCtrl(x5886) { FIFO(size=16).name("x5791").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:67:37:sigBiasQ") } // x5791 = FIFONew(Const(16))
    isAccum(x5791) = false
    bufferDepthOf(x5791) = 2
    val x5792 = withCtrl(x5886) { FIFO(size=16).name("x5792").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:68:33:actQ") } // x5792 = FIFONew(Const(16))
    isAccum(x5792) = false
    bufferDepthOf(x5792) = 2
    val x5793 = withCtrl(x5886) { FIFO(size=16).name("x5793").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:71:37:hUpdateQ") } // x5793 = FIFONew(Const(16))
    isAccum(x5793) = false
    bufferDepthOf(x5793) = 1
    val x5794 = withCtrl(x5886) { FIFO(size=16).name("x5794").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:72:37:cUpdateQ") } // x5794 = FIFONew(Const(16))
    isAccum(x5794) = false
    bufferDepthOf(x5794) = 1
    val x5795 = withCtrl(x5886) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x5795").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:74:42") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x5796 = withCtrl(x5886) { CounterChain(List(x5795)).name("x5796").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:74:56") } // CounterChainNew(List(x5795))
    val x5830 = withCtrl(x5886) { LoopController(style=InnerPipe, level=InnerControl, cchain=x5796).name("x5830").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:74:56") } // UnrolledForeach(List(b3549, b3468, b3464),x5796,Block(Const(())),List(List(b3557)),List(List(b3558)))
    val b3557 = withCtrl(x5830) { CounterIter(x5795, None).name("b3557") } // b3557
    val b3558 = withCtrl(x5830) { Const(true).name("b3558") } // b3558
    val x5797 = withCtrl(x5830) { OpDef(op=BitAnd, inputs=List(b3558, b3549)).name("x5797").srcCtx("UnrollingBase.scala:28:66") } // And(b3558,b3549)
    val x5798 = withCtrl(x5830) { OpDef(op=BitAnd, inputs=List(b3468, b3464)).name("x5798").srcCtx("UnrollingBase.scala:28:66") } // And(b3468,b3464)
    val x5799 = withCtrl(x5830) { OpDef(op=BitAnd, inputs=List(x5797, x5798)).name("x5799").srcCtx("UnrollingBase.scala:28:66") } // And(x5797,x5798)
    val x5800 = withCtrl(x5830) { LoadBanks(List(x5725_d0_b0), List(b3548, b3557)).name("x5800").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:78:35:pEle") } // ParSRAMLoad(x5725,List(List(b3548, b3557)),List(x5799))
    val x5801 = withCtrl(x5830) { x5800 } // VectorApply(x5800,0)
    val x5802 = withCtrl(x5830) { OpDef(op=FixLt, inputs=List(x5801, Const(-2.5))).name("x5802").srcCtx("NonLinearity.scala:72:12") } // FixLt(x5801,Const(-2.5))
    val x5803 = withCtrl(x5830) { OpDef(op=FixLeq, inputs=List(Const(2.5), x5801)).name("x5803").srcCtx("NonLinearity.scala:72:34") } // FixLeq(Const(2.5),x5801)
    val x5804 = withCtrl(x5830) { OpDef(op=FixMul, inputs=List(Const(0.19999999), x5801)).name("x5804").srcCtx("NonLinearity.scala:72:56") } // FixMul(Const(0.199999988079071044921875),x5801)
    val x5805 = withCtrl(x5830) { OpDef(op=FixAdd, inputs=List(x5804, Const(0.5))).name("x5805").srcCtx("NonLinearity.scala:72:61") } // FixAdd(x5804,Const(0.5))
    val x5806 = withCtrl(x5830) { OpDef(op=MuxOp, inputs=List(x5803, Const(1.0), x5805)).name("x5806").srcCtx("NonLinearity.scala:72:30") } // Mux(x5803,Const(1),x5805)
    val x5807 = withCtrl(x5830) { OpDef(op=MuxOp, inputs=List(x5802, Const(0.0), x5806)).name("x5807").srcCtx("NonLinearity.scala:72:8:sigEle") } // Mux(x5802,Const(0),x5806)
    val x5808 = withCtrl(x5830) { OpDef(op=FixAdd, inputs=List(x5801, Const(1.0))).name("x5808").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:80:47") } // FixAdd(x5801,Const(1))
    val x5809 = withCtrl(x5830) { OpDef(op=FixLt, inputs=List(x5808, Const(-2.5))).name("x5809").srcCtx("NonLinearity.scala:72:12") } // FixLt(x5808,Const(-2.5))
    val x5810 = withCtrl(x5830) { OpDef(op=FixLeq, inputs=List(Const(2.5), x5808)).name("x5810").srcCtx("NonLinearity.scala:72:34") } // FixLeq(Const(2.5),x5808)
    val x5811 = withCtrl(x5830) { OpDef(op=FixMul, inputs=List(Const(0.19999999), x5808)).name("x5811").srcCtx("NonLinearity.scala:72:56") } // FixMul(Const(0.199999988079071044921875),x5808)
    val x5812 = withCtrl(x5830) { OpDef(op=FixAdd, inputs=List(x5811, Const(0.5))).name("x5812").srcCtx("NonLinearity.scala:72:61") } // FixAdd(x5811,Const(0.5))
    val x5813 = withCtrl(x5830) { OpDef(op=MuxOp, inputs=List(x5810, Const(1.0), x5812)).name("x5813").srcCtx("NonLinearity.scala:72:30") } // Mux(x5810,Const(1),x5812)
    val x5814 = withCtrl(x5830) { OpDef(op=MuxOp, inputs=List(x5809, Const(0.0), x5813)).name("x5814").srcCtx("NonLinearity.scala:72:8:sigBiasEle") } // Mux(x5809,Const(0),x5813)
    val x5815 = withCtrl(x5830) { OpDef(op=FixAbs, inputs=List(x5801)).name("x5815").srcCtx("NonLinearity.scala:51:20:absin") } // FixAbs(x5801)
    val x5816 = withCtrl(x5830) { OpDef(op=FixSra, inputs=List(x5815, Const(2))).name("x5816").srcCtx("NonLinearity.scala:52:22:div4") } // FixRsh(x5815,Const(2))
    val x5817 = withCtrl(x5830) { OpDef(op=FixAdd, inputs=List(x5816, Const(0.375))).name("x5817").srcCtx("NonLinearity.scala:53:19:li") } // FixAdd(x5816,Const(0.375))
    val x5818 = withCtrl(x5830) { OpDef(op=FixLt, inputs=List(Const(2.5), x5815)).name("x5818").srcCtx("NonLinearity.scala:54:28") } // FixLt(Const(2.5),x5815)
    val x5819 = withCtrl(x5830) { OpDef(op=FixLt, inputs=List(Const(0.5), x5815)).name("x5819").srcCtx("NonLinearity.scala:55:14") } // FixLt(Const(0.5),x5815)
    val x5820 = withCtrl(x5830) { OpDef(op=FixLt, inputs=List(x5815, Const(2.5))).name("x5820").srcCtx("NonLinearity.scala:55:31") } // FixLt(x5815,Const(2.5))
    val x5821 = withCtrl(x5830) { OpDef(op=BitAnd, inputs=List(x5819, x5820)).name("x5821").srcCtx("NonLinearity.scala:55:22") } // And(x5819,x5820)
    val x5822 = withCtrl(x5830) { OpDef(op=MuxOp, inputs=List(x5821, x5817, x5815)).name("x5822").srcCtx("NonLinearity.scala:55:10") } // Mux(x5821,x5817,x5815)
    val x5823 = withCtrl(x5830) { OpDef(op=MuxOp, inputs=List(x5818, Const(1.0), x5822)).name("x5823").srcCtx("NonLinearity.scala:54:21:absout") } // Mux(x5818,Const(1),x5822)
    val x5824 = withCtrl(x5830) { OpDef(op=FixNeg, inputs=List(x5823)).name("x5824").srcCtx("NonLinearity.scala:58:23:negout") } // FixNeg(x5823)
    val x5825 = withCtrl(x5830) { OpDef(op=FixLt, inputs=List(x5801, Const(0.0))).name("x5825").srcCtx("NonLinearity.scala:59:12") } // FixLt(x5801,Const(0))
    val x5826 = withCtrl(x5830) { OpDef(op=MuxOp, inputs=List(x5825, x5824, x5823)).name("x5826").srcCtx("NonLinearity.scala:59:8:actEle") } // Mux(x5825,x5824,x5823)
    val x5827_x5790 = withCtrl(x5830) { WriteMem(x5790, x5807).name("x5827_x5790").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:83:25") } // ParFIFOEnq(x5790,List(x5807),List(x5799))
    val x5828_x5791 = withCtrl(x5830) { WriteMem(x5791, x5814).name("x5828_x5791").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:85:29") } // ParFIFOEnq(x5791,List(x5814),List(x5799))
    val x5829_x5792 = withCtrl(x5830) { WriteMem(x5792, x5826).name("x5829_x5792").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:86:25") } // ParFIFOEnq(x5792,List(x5826),List(x5799))
    val x5885 = withCtrl(x5886) { UnitController(style=SeqPipe, level=OuterControl).name("x5885").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:89:20") } // UnitPipe(List(b3549, b3468, b3464),Block(Const(())))
    val x5831 = withCtrl(x5885) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x5831").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:90:44") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x5832 = withCtrl(x5885) { CounterChain(List(x5831)).name("x5832").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:90:58") } // CounterChainNew(List(x5831))
    val x5871 = withCtrl(x5885) { LoopController(style=InnerPipe, level=InnerControl, cchain=x5832).name("x5871").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:90:58") } // UnrolledForeach(List(b3549, b3468, b3464),x5832,Block(Const(())),List(List(b3595)),List(List(b3596)))
    val b3595 = withCtrl(x5871) { CounterIter(x5831, None).name("b3595") } // b3595
    val b3596 = withCtrl(x5871) { Const(true).name("b3596") } // b3596
    val x5833 = withCtrl(x5871) { OpDef(op=BitAnd, inputs=List(b3596, b3549)).name("x5833").srcCtx("UnrollingBase.scala:28:66") } // And(b3596,b3549)
    val x5834 = withCtrl(x5871) { OpDef(op=BitAnd, inputs=List(b3468, b3464)).name("x5834").srcCtx("UnrollingBase.scala:28:66") } // And(b3468,b3464)
    val x5835 = withCtrl(x5871) { OpDef(op=BitAnd, inputs=List(x5833, x5834)).name("x5835").srcCtx("UnrollingBase.scala:28:66") } // And(x5833,x5834)
    val x5836 = withCtrl(x5871) { ReadMem(x5790).name("x5836").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:91:40:sigEle") } // ParFIFODeq(x5790,List(x5835))
    val x5837 = withCtrl(x5871) { x5836 } // VectorApply(x5836,0)
    val x5838 = withCtrl(x5871) { ReadMem(x5791).name("x5838").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:92:48:sigBiasEle") } // ParFIFODeq(x5791,List(x5835))
    val x5839 = withCtrl(x5871) { x5838 } // VectorApply(x5838,0)
    val x5840 = withCtrl(x5871) { ReadMem(x5792).name("x5840").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:93:40:actEle") } // ParFIFODeq(x5792,List(x5835))
    val x5841 = withCtrl(x5871) { x5840 } // VectorApply(x5840,0)
    val x5842 = withCtrl(x5871) { LoadBanks(List(x5566_d0_b0), List(b3467, b3595)).name("x5842").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:95:34:cLast") } // ParSRAMLoad(x5566,List(List(b3467, b3595)),List(x5835))
    val x5843 = withCtrl(x5871) { x5842 } // VectorApply(x5842,0)
    val x5844 = withCtrl(x5871) { OpDef(op=FixAdd, inputs=List(x5843, x5841)).name("x5844").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:96:40:cNewMult") } // FixAdd(x5843,x5841)
    val x5845 = withCtrl(x5871) { OpDef(op=FixMul, inputs=List(x5839, x5843)).name("x5845").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:99:48") } // FixMul(x5839,x5843)
    val x5846 = withCtrl(x5871) { OpDef(op=FixAdd, inputs=List(x5845, x5843)).name("x5846").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:99:56:cNewMultAdd") } // FixAdd(x5845,x5843)
    val x5847 = withCtrl(x5871) { OpDef(op=FixEql, inputs=List(b3548, Const(0))).name("x5847").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:138:38") } // FixEql(b3548,Const(0))
    val x5848 = withCtrl(x5871) { OpDef(op=FixEql, inputs=List(b3548, Const(2))).name("x5848").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:144:38") } // FixEql(b3548,Const(2))
    val x5849 = withCtrl(x5871) { OpDef(op=MuxOp, inputs=List(x5848, x5846, x5843)).name("x5849").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:104:28") } // Mux(x5848,x5846,x5843)
    val x5850 = withCtrl(x5871) { OpDef(op=MuxOp, inputs=List(x5847, x5844, x5849)).name("x5850").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:103:26") } // Mux(x5847,x5844,x5849)
    val x5851 = withCtrl(x5871) { OpDef(op=MuxOp, inputs=List(x5847, x5837, x5850)).name("x5851").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:102:24") } // Mux(x5847,x5837,x5850)
    val x5852_x5794 = withCtrl(x5871) { WriteMem(x5794, x5851).name("x5852_x5794").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:101:32") } // ParFIFOEnq(x5794,List(x5851),List(x5835))
    val x5853 = withCtrl(x5871) { OpDef(op=FixAbs, inputs=List(x5846)).name("x5853").srcCtx("NonLinearity.scala:51:20:absin") } // FixAbs(x5846)
    val x5854 = withCtrl(x5871) { OpDef(op=FixSra, inputs=List(x5853, Const(2))).name("x5854").srcCtx("NonLinearity.scala:52:22:div4") } // FixRsh(x5853,Const(2))
    val x5855 = withCtrl(x5871) { OpDef(op=FixAdd, inputs=List(x5854, Const(0.375))).name("x5855").srcCtx("NonLinearity.scala:53:19:li") } // FixAdd(x5854,Const(0.375))
    val x5856 = withCtrl(x5871) { OpDef(op=FixLt, inputs=List(Const(2.5), x5853)).name("x5856").srcCtx("NonLinearity.scala:54:28") } // FixLt(Const(2.5),x5853)
    val x5857 = withCtrl(x5871) { OpDef(op=FixLt, inputs=List(Const(0.5), x5853)).name("x5857").srcCtx("NonLinearity.scala:55:14") } // FixLt(Const(0.5),x5853)
    val x5858 = withCtrl(x5871) { OpDef(op=FixLt, inputs=List(x5853, Const(2.5))).name("x5858").srcCtx("NonLinearity.scala:55:31") } // FixLt(x5853,Const(2.5))
    val x5859 = withCtrl(x5871) { OpDef(op=BitAnd, inputs=List(x5857, x5858)).name("x5859").srcCtx("NonLinearity.scala:55:22") } // And(x5857,x5858)
    val x5860 = withCtrl(x5871) { OpDef(op=MuxOp, inputs=List(x5859, x5855, x5853)).name("x5860").srcCtx("NonLinearity.scala:55:10") } // Mux(x5859,x5855,x5853)
    val x5861 = withCtrl(x5871) { OpDef(op=MuxOp, inputs=List(x5856, Const(1.0), x5860)).name("x5861").srcCtx("NonLinearity.scala:54:21:absout") } // Mux(x5856,Const(1),x5860)
    val x5862 = withCtrl(x5871) { OpDef(op=FixNeg, inputs=List(x5861)).name("x5862").srcCtx("NonLinearity.scala:58:23:negout") } // FixNeg(x5861)
    val x5863 = withCtrl(x5871) { OpDef(op=FixLt, inputs=List(x5846, Const(0.0))).name("x5863").srcCtx("NonLinearity.scala:59:12") } // FixLt(x5846,Const(0))
    val x5864 = withCtrl(x5871) { OpDef(op=MuxOp, inputs=List(x5863, x5862, x5861)).name("x5864").srcCtx("NonLinearity.scala:59:8") } // Mux(x5863,x5862,x5861)
    val x5865 = withCtrl(x5871) { OpDef(op=FixAdd, inputs=List(x5864, x5837)).name("x5865").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:112:54:hNew") } // FixAdd(x5864,x5837)
    val x5866 = withCtrl(x5871) { LoadBanks(List(x5591_d0_b0), List(b3467, b3595)).name("x5866").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:113:34:hLast") } // ParSRAMLoad(x5591,List(List(b3467, b3595)),List(x5835))
    val x5867 = withCtrl(x5871) { x5866 } // VectorApply(x5866,0)
    val x5868 = withCtrl(x5871) { OpDef(op=FixEql, inputs=List(b3548, Const(3))).name("x5868").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:147:38") } // FixEql(b3548,Const(3))
    val x5869 = withCtrl(x5871) { OpDef(op=MuxOp, inputs=List(x5868, x5865, x5867)).name("x5869").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:115:35:update") } // Mux(x5868,x5865,x5867)
    val x5870_x5793 = withCtrl(x5871) { WriteMem(x5793, x5869).name("x5870_x5793").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:116:31") } // ParFIFOEnq(x5793,List(x5869),List(x5835))
    def split1 = {
    val x5872 = withCtrl(x5885) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x5872").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:119:44") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x5873 = withCtrl(x5885) { CounterChain(List(x5872)).name("x5873").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:119:58") } // CounterChainNew(List(x5872))
    val x5884 = withCtrl(x5885) { LoopController(style=InnerPipe, level=InnerControl, cchain=x5873).name("x5884").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:119:58") } // UnrolledForeach(List(b3549, b3468, b3464),x5873,Block(Const(())),List(List(b3638)),List(List(b3639)))
    val b3638 = withCtrl(x5884) { CounterIter(x5872, None).name("b3638") } // b3638
    val b3639 = withCtrl(x5884) { Const(true).name("b3639") } // b3639
    val x5874 = withCtrl(x5884) { OpDef(op=BitAnd, inputs=List(b3639, b3549)).name("x5874").srcCtx("UnrollingBase.scala:28:66") } // And(b3639,b3549)
    val x5875 = withCtrl(x5884) { OpDef(op=BitAnd, inputs=List(b3468, b3464)).name("x5875").srcCtx("UnrollingBase.scala:28:66") } // And(b3468,b3464)
    val x5876 = withCtrl(x5884) { OpDef(op=BitAnd, inputs=List(x5874, x5875)).name("x5876").srcCtx("UnrollingBase.scala:28:66") } // And(x5874,x5875)
    val x5877 = withCtrl(x5884) { ReadMem(x5793).name("x5877").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:120:41:update") } // ParFIFODeq(x5793,List(x5876))
    val x5878 = withCtrl(x5884) { x5877 } // VectorApply(x5877,0)
    val x5879 = withCtrl(x5884) { StoreBanks(List(List(x5591_d0_b0), List(x5591_d1_b0)), List(b3467, b3638), x5878).name("x5879").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:121:44") } // ParSRAMStore(x5591,List(List(b3467, b3638)),List(x5878),List(x5876))
    val x5880 = withCtrl(x5884) { StoreBanks(List(List(x5541_d0_b0), List(x5541_d1_b0)), List(b3463, b3638), x5878).name("x5880").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:122:43") } // ParSRAMStore(x5541,List(List(b3463, b3638)),List(x5878),List(x5876))
    val x5881 = withCtrl(x5884) { ReadMem(x5794).name("x5881").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:123:55") } // ParFIFODeq(x5794,List(x5876))
    val x5882 = withCtrl(x5884) { x5881 } // VectorApply(x5881,0)
    val x5883 = withCtrl(x5884) { StoreBanks(List(List(x5566_d0_b0)), List(b3467, b3638), x5882).name("x5883").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:123:44") } // ParSRAMStore(x5566,List(List(b3467, b3638)),List(x5882),List(x5876))
    val x5890 = withCtrl(x5918) { Counter(min=Const(0), max=Const(8), step=Const(1), par=1).name("x5890").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:131:49") } // CounterNew(Const(0),Const(8),Const(1),Const(1))
    val x5891 = withCtrl(x5918) { CounterChain(List(x5890)).name("x5891").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:131:49") } // CounterChainNew(List(x5890))
    val x5917 = withCtrl(x5918) { LoopController(style=StreamPipe, level=OuterControl, cchain=x5891).name("x5917").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:131:49") } // UnrolledForeach(List(Const(true)),x5891,Block(Const(())),List(List(b3658)),List(List(b3659)))
    val b3658 = withCtrl(x5917) { CounterIter(x5890, Some(0)).name("b3658") } // b3658
    val b3659 = withCtrl(x5917) { Const(true).name("b3659") } // b3659
    val b5953 = withCtrl(x5917) { StreamOut(field="offset").name("b5953").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:131:49") } // x5892 = StreamOutNew(BurstCmdBus)
    isAccum(b5953) = false
    bufferDepthOf(b5953) = 1
    val b5954 = withCtrl(x5917) { StreamOut(field="size").name("b5954").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:131:49") } // x5892 = StreamOutNew(BurstCmdBus)
    isAccum(b5954) = false
    bufferDepthOf(b5954) = 1
    val x5893 = withCtrl(x5917) { StreamOut(field="data").name("x5893").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:131:49") } // x5893 = StreamOutNew(BurstFullDataBus())
    isAccum(x5893) = false
    bufferDepthOf(x5893) = 1
    val x5894 = withCtrl(x5917) { StreamIn(field="ack").name("x5894").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:131:49") } // x5894 = StreamInNew(BurstAckBus)
    isAccum(x5894) = false
    bufferDepthOf(x5894) = 1
    val x5905 = withCtrl(x5917) { UnitController(style=SeqPipe, level=InnerControl).name("x5905").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:131:49") } // UnitPipe(List(b3659),Block(x5904))
    val x5895 = withCtrl(x5905) { b3658 } // FixConvert(b3658,TRUE,_32,_0) (Same Type. No op)
    val x5896 = withCtrl(x5905) { OpDef(op=FixSla, inputs=List(x5895, Const(7))).name("x5896").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:131:49") } // FixLsh(x5895,Const(7))
    val x5897 = withCtrl(x5905) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x5898 = withCtrl(x5905) { OpDef(op=FixAdd, inputs=List(x5896, x5897)).name("x5898").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:131:49") } // FixAdd(x5896,x5897)
    val x5899 = withCtrl(x5905) { OpDef(op=FixSla, inputs=List(x5898, Const(2))).name("x5899").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:131:49") } // FixLsh(x5898,Const(2))
    val x5900 = withCtrl(x5905) { x5899 } // FixConvert(x5899,TRUE,_64,_0)
    val x5901 = withCtrl(x5905) { DramAddress(x5540).name("x5901").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:131:49") } // GetDRAMAddress(x5540)
    val x5903_x5902 = withCtrl(x5905) { OpDef(op=FixAdd, inputs=List(x5900, x5901)).name("x5903_x5902").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:131:49") } // FixAdd(x5900,x5901)
    // x5903 = SimpleStruct(ArrayBuffer((offset,x5902), (size,Const(512)), (isLoad,Const(false))))
    val x5904_b5955_b5953 = withCtrl(x5905) { WriteMem(b5953, x5903_x5902).name("x5904_b5955_b5953").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:131:49") } // StreamWrite(x5892,x5903,b3659)
    val x5904_b5956_b5954 = withCtrl(x5905) { WriteMem(b5954, Const(512)).name("x5904_b5956_b5954").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:131:49") } // StreamWrite(x5892,x5903,b3659)
    val x5906 = withCtrl(x5917) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x5906").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:131:49") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x5907 = withCtrl(x5917) { CounterChain(List(x5906)).name("x5907").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:131:49") } // CounterChainNew(List(x5906))
    val x5913 = withCtrl(x5917) { LoopController(style=InnerPipe, level=InnerControl, cchain=x5907).name("x5913").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:131:49") } // UnrolledForeach(List(b3659),x5907,Block(Const(())),List(List(b3676)),List(List(b3677)))
    val b3676 = withCtrl(x5913) { CounterIter(x5906, None).name("b3676") } // b3676
    val b3677 = withCtrl(x5913) { Const(true).name("b3677") } // b3677
    val x5908 = withCtrl(x5913) { OpDef(op=BitAnd, inputs=List(b3677, b3659)).name("x5908").srcCtx("UnrollingBase.scala:28:66") } // And(b3677,b3659)
    val x5909 = withCtrl(x5913) { LoadBanks(List(x5541_d0_b0), List(b3658, b3676)).name("x5909").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:131:49") } // ParSRAMLoad(x5541,List(List(b3658, b3676)),List(x5908))
    val x5911_x5910 = withCtrl(x5913) { x5909 } // VectorApply(x5909,0)
    // x5911 = SimpleStruct(ArrayBuffer((_1,x5910), (_2,Const(true))))
    val x5912_x5912_x5893 = withCtrl(x5913) { WriteMem(x5893, x5911_x5910).name("x5912_x5912_x5893").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:131:49") } // ParStreamWrite(x5893,List(x5911),List(x5908))
    val x5914 = withCtrl(x5917) { FringeDenseStore(dram=List(x5540), cmdStream=List(b5953, b5954), dataStream=List(x5893), ackStream=List(x5894)).name("x5914").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:131:49") } // FringeDenseStore(x5540,x5892,x5893,x5894)
    val x5916 = withCtrl(x5917) { UnitController(style=SeqPipe, level=InnerControl).name("x5916").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:131:49") } // UnitPipe(List(b3659),Block(Const(())))
    val x5915_x5915 = withCtrl(x5916) { ReadMem(x5894).name("x5915_x5915").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:131:49") } // StreamRead(x5894,b3659)
    }; split1
    
  }
}
