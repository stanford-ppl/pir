import pir._
import pir.node._
import arch._
import prism.enums._

object Decoder1Cell extends PIRApp {
  def main(implicit design:PIRDesign) = {
    import design.pirmeta._
    val x9545 = DRAM().name("x9545").ctrl(top).srcCtx("Decoder1Cell.scala:9:24:xDRAM") // x9545 = DRAMNew(ArrayBuffer(Const(1), Const(4), Const(512)),Const(0))
    val x9553 = DRAM().name("x9553").ctrl(top).srcCtx("CellsPar.scala:80:24") // x9553 = DRAMNew(ArrayBuffer(Const(1), Const(512)),Const(0))
    val x9554 = DRAM().name("x9554").ctrl(top).srcCtx("CellsPar.scala:81:24") // x9554 = DRAMNew(ArrayBuffer(Const(1), Const(2048)),Const(0))
    val x9555 = DRAM().name("x9555").ctrl(top).srcCtx("CellsPar.scala:82:24:output_hidden") // x9555 = DRAMNew(ArrayBuffer(Const(1), Const(512)),Const(0))
    val x9581 = DRAM().name("x9581").ctrl(top).srcCtx("CellsPar.scala:90:21:kernel") // x9581 = DRAMNew(ArrayBuffer(Const(1536), Const(512)),Const(0))
    val x9582 = DRAM().name("x9582").ctrl(top).srcCtx("CellsPar.scala:91:21:kernel") // x9582 = DRAMNew(ArrayBuffer(Const(1536), Const(512)),Const(0))
    val x9583 = DRAM().name("x9583").ctrl(top).srcCtx("CellsPar.scala:92:21:kernel") // x9583 = DRAMNew(ArrayBuffer(Const(1536), Const(512)),Const(0))
    val x9584 = DRAM().name("x9584").ctrl(top).srcCtx("CellsPar.scala:93:21:kernel") // x9584 = DRAMNew(ArrayBuffer(Const(1536), Const(512)),Const(0))
    val x9585 = DRAM().name("x9585").ctrl(top).srcCtx("CellsPar.scala:94:25") // x9585 = DRAMNew(ArrayBuffer(Const(1536), Const(2048)),Const(0))
    val x10231 = UnitController(style=SeqPipe, level=OuterControl).name("x10231").ctrl(top).srcCtx("Decoder1Cell.scala:18:11") // Hwblock(Block(Const(())),false)
    val x9629_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x9629_d0_b0").ctrl(x10231).srcCtx("Decoder1Cell.scala:19:66") // x9629 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x9629_d0_b0) = false
    bufferDepthOf(x9629_d0_b0) = 1
    val x9629_d1_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x9629_d1_b0").ctrl(x10231).srcCtx("Decoder1Cell.scala:19:66") // x9629 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x9629_d1_b0) = false
    bufferDepthOf(x9629_d1_b0) = 1
    val x9629_d2_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x9629_d2_b0").ctrl(x10231).srcCtx("Decoder1Cell.scala:19:66") // x9629 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x9629_d2_b0) = false
    bufferDepthOf(x9629_d2_b0) = 1
    val x9629_d3_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x9629_d3_b0").ctrl(x10231).srcCtx("Decoder1Cell.scala:19:66") // x9629 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x9629_d3_b0) = false
    bufferDepthOf(x9629_d3_b0) = 1
    val x9630_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x9630_d0_b0").ctrl(x10231).srcCtx("CellsPar.scala:139:20") // x9630 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x9630_d0_b0) = false
    bufferDepthOf(x9630_d0_b0) = 1
    val x9630_d1_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x9630_d1_b0").ctrl(x10231).srcCtx("CellsPar.scala:139:20") // x9630 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x9630_d1_b0) = false
    bufferDepthOf(x9630_d1_b0) = 1
    val x9630_d2_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x9630_d2_b0").ctrl(x10231).srcCtx("CellsPar.scala:139:20") // x9630 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x9630_d2_b0) = false
    bufferDepthOf(x9630_d2_b0) = 1
    val x9630_d3_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x9630_d3_b0").ctrl(x10231).srcCtx("CellsPar.scala:139:20") // x9630 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x9630_d3_b0) = false
    bufferDepthOf(x9630_d3_b0) = 1
    val x9630_d4_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x9630_d4_b0").ctrl(x10231).srcCtx("CellsPar.scala:139:20") // x9630 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x9630_d4_b0) = false
    bufferDepthOf(x9630_d4_b0) = 1
    val x9631_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x9631_d0_b0").ctrl(x10231).srcCtx("CellsPar.scala:140:20") // x9631 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x9631_d0_b0) = false
    bufferDepthOf(x9631_d0_b0) = 1
    val x9631_d1_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x9631_d1_b0").ctrl(x10231).srcCtx("CellsPar.scala:140:20") // x9631 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x9631_d1_b0) = true
    bufferDepthOf(x9631_d1_b0) = 1
    val x9632_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x9632_d0_b0").ctrl(x10231).srcCtx("CellsPar.scala:141:45:targetSRAM") // x9632 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x9632_d0_b0) = false
    bufferDepthOf(x9632_d0_b0) = 1
    val x9632_d1_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x9632_d1_b0").ctrl(x10231).srcCtx("CellsPar.scala:141:45:targetSRAM") // x9632 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x9632_d1_b0) = true
    bufferDepthOf(x9632_d1_b0) = 1
    val x9633_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x9633_d0_b0").ctrl(x10231).srcCtx("CellsPar.scala:141:79:targetSRAM") // x9633 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x9633_d0_b0) = false
    bufferDepthOf(x9633_d0_b0) = 1
    val x9633_d1_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x9633_d1_b0").ctrl(x10231).srcCtx("CellsPar.scala:141:79:targetSRAM") // x9633 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x9633_d1_b0) = true
    bufferDepthOf(x9633_d1_b0) = 1
    val x9634_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x9634_d0_b0").ctrl(x10231).srcCtx("CellsPar.scala:142:44:targetSRAM") // x9634 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x9634_d0_b0) = false
    bufferDepthOf(x9634_d0_b0) = 1
    val x9634_d1_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x9634_d1_b0").ctrl(x10231).srcCtx("CellsPar.scala:142:44:targetSRAM") // x9634 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x9634_d1_b0) = true
    bufferDepthOf(x9634_d1_b0) = 1
    val x9635_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x9635_d0_b0").ctrl(x10231).srcCtx("CellsPar.scala:142:78:targetSRAM") // x9635 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x9635_d0_b0) = false
    bufferDepthOf(x9635_d0_b0) = 1
    val x9635_d1_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x9635_d1_b0").ctrl(x10231).srcCtx("CellsPar.scala:142:78:targetSRAM") // x9635 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x9635_d1_b0) = true
    bufferDepthOf(x9635_d1_b0) = 1
    val x9636_d0_b0 = SRAM(size=2048, banking=Strided(banks=1, stride=2048)).name("x9636_d0_b0").ctrl(x10231).srcCtx("CellsPar.scala:143:23:bias") // x9636 = SRAMNew(ArrayBuffer(Const(1), Const(2048)))
    isAccum(x9636_d0_b0) = false
    bufferDepthOf(x9636_d0_b0) = 1
    val x9636_d1_b0 = SRAM(size=2048, banking=Strided(banks=1, stride=2048)).name("x9636_d1_b0").ctrl(x10231).srcCtx("CellsPar.scala:143:23:bias") // x9636 = SRAMNew(ArrayBuffer(Const(1), Const(2048)))
    isAccum(x9636_d1_b0) = false
    bufferDepthOf(x9636_d1_b0) = 1
    val x9636_d2_b0 = SRAM(size=2048, banking=Strided(banks=1, stride=2048)).name("x9636_d2_b0").ctrl(x10231).srcCtx("CellsPar.scala:143:23:bias") // x9636 = SRAMNew(ArrayBuffer(Const(1), Const(2048)))
    isAccum(x9636_d2_b0) = false
    bufferDepthOf(x9636_d2_b0) = 1
    val x9636_d3_b0 = SRAM(size=2048, banking=Strided(banks=1, stride=2048)).name("x9636_d3_b0").ctrl(x10231).srcCtx("CellsPar.scala:143:23:bias") // x9636 = SRAMNew(ArrayBuffer(Const(1), Const(2048)))
    isAccum(x9636_d3_b0) = false
    bufferDepthOf(x9636_d3_b0) = 1
    val x9637 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x9637").ctrl(x10231).srcCtx("CellsPar.scala:161:12") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x9638 = CounterChain(List(x9637)).name("x9638").ctrl(x10231).srcCtx("CellsPar.scala:161:12") // CounterChainNew(List(x9637))
    val x9660 = LoopController(style=StreamPipe, level=OuterControl, cchain=x9638).name("x9660").ctrl(x10231).srcCtx("CellsPar.scala:161:12") // UnrolledForeach(List(Const(true)),x9638,Block(Const(())),List(List(b6059)),List(List(b6060)))
    val b6059 = CounterIter(x9637, Some(0)).name("b6059").ctrl(x9660) // b6059
    val b6060 = Const(true).name("b6060").ctrl(x9660) // b6060
    val b10241 = StreamOut(field="offset").name("b10241").ctrl(x9660).srcCtx("CellsPar.scala:161:12") // x9639 = StreamOutNew(BurstCmdBus)
    isAccum(b10241) = false
    bufferDepthOf(b10241) = 1
    val b10242 = StreamOut(field="size").name("b10242").ctrl(x9660).srcCtx("CellsPar.scala:161:12") // x9639 = StreamOutNew(BurstCmdBus)
    isAccum(b10242) = false
    bufferDepthOf(b10242) = 1
    val x9640 = StreamIn(field="data").name("x9640").ctrl(x9660).srcCtx("CellsPar.scala:161:12") // x9640 = StreamInNew(BurstDataBus())
    isAccum(x9640) = false
    bufferDepthOf(x9640) = 1
    val x9651 = UnitController(style=SeqPipe, level=InnerControl).name("x9651").ctrl(x9660).srcCtx("CellsPar.scala:161:12") // UnitPipe(List(b6060),Block(x9650))
    val x9641 = b6059 // FixConvert(b6059,TRUE,_32,_0) (Same Type. No op)
    val x9642 = OpDef(op=FixSla, inputs=List(x9641, Const(11))).name("x9642").ctrl(x9651).srcCtx("CellsPar.scala:161:12") // FixLsh(x9641,Const(11))
    val x9643 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x9644 = OpDef(op=FixAdd, inputs=List(x9642, x9643)).name("x9644").ctrl(x9651).srcCtx("CellsPar.scala:161:12") // FixAdd(x9642,x9643)
    val x9645 = OpDef(op=FixSla, inputs=List(x9644, Const(2))).name("x9645").ctrl(x9651).srcCtx("CellsPar.scala:161:12") // FixLsh(x9644,Const(2))
    val x9646 = x9645 // FixConvert(x9645,TRUE,_64,_0)
    val x9647 = DramAddress(x9554).name("x9647").ctrl(x9651).srcCtx("CellsPar.scala:161:12") // GetDRAMAddress(x9554)
    val x9649_x9648 = OpDef(op=FixAdd, inputs=List(x9646, x9647)).name("x9649_x9648").ctrl(x9651).srcCtx("CellsPar.scala:161:12") // FixAdd(x9646,x9647)
    // x9649 = SimpleStruct(ArrayBuffer((offset,x9648), (size,Const(8192)), (isLoad,Const(true))))
    val x9650_b10243_b10241 = WriteMem(b10241, x9649_x9648).name("x9650_b10243_b10241").ctrl(x9651).srcCtx("CellsPar.scala:161:12") // StreamWrite(x9639,x9649,b6060)
    val x9650_b10244_b10242 = WriteMem(b10242, Const(8192)).name("x9650_b10244_b10242").ctrl(x9651).srcCtx("CellsPar.scala:161:12") // StreamWrite(x9639,x9649,b6060)
    val x9652 = FringeDenseLoad(dram=List(x9554), cmdStream=List(b10241, b10242), dataStream=List(x9640)).name("x9652").ctrl(x9660).srcCtx("CellsPar.scala:161:12") // FringeDenseLoad(x9554,x9639,x9640)
    val x9653 = Counter(min=Const(0), max=Const(2048), step=Const(1), par=1).name("x9653").ctrl(x9660).srcCtx("CellsPar.scala:161:12") // CounterNew(Const(0),Const(2048),Const(1),Const(1))
    val x9654 = CounterChain(List(x9653)).name("x9654").ctrl(x9660).srcCtx("CellsPar.scala:161:12") // CounterChainNew(List(x9653))
    val x9659 = LoopController(style=InnerPipe, level=InnerControl, cchain=x9654).name("x9659").ctrl(x9660).srcCtx("CellsPar.scala:161:12") // UnrolledForeach(List(b6060),x9654,Block(Const(())),List(List(b6077)),List(List(b6078)))
    val b6077 = CounterIter(x9653, None).name("b6077").ctrl(x9659) // b6077
    val b6078 = Const(true).name("b6078").ctrl(x9659) // b6078
    val x9655 = OpDef(op=BitAnd, inputs=List(b6078, b6060)).name("x9655").ctrl(x9659).srcCtx("UnrollingBase.scala:28:66") // And(b6078,b6060)
    val x9656_x9656 = ReadMem(x9640).name("x9656_x9656").ctrl(x9659).srcCtx("CellsPar.scala:161:12") // ParStreamRead(x9640,List(x9655))
    val x9657_x9657 = x9656_x9656 // x9657 = VectorApply(x9656,0)
    val x9658 = StoreBanks(List(x9636_d0_b0, x9636_d1_b0, x9636_d2_b0, x9636_d3_b0), List(b6059, b6077), x9657_x9657).name("x9658").ctrl(x9659).srcCtx("CellsPar.scala:161:12") // ParSRAMStore(x9636,List(List(b6059, b6077)),List(x9657),List(x9655))
    val x9661 = Counter(min=Const(0), max=Const(512), step=Const(1), par=1).name("x9661").ctrl(x10231).srcCtx("CellsPar.scala:162:50") // CounterNew(Const(0),Const(512),Const(1),Const(1))
    val x9662 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x9662").ctrl(x10231).srcCtx("CellsPar.scala:162:27") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x9663 = CounterChain(List(x9662,x9661)).name("x9663").ctrl(x10231).srcCtx("CellsPar.scala:162:63") // CounterChainNew(List(x9662, x9661))
    val x9668 = LoopController(style=MetaPipe, level=OuterControl, cchain=x9663).name("x9668").ctrl(x10231).srcCtx("CellsPar.scala:162:63") // UnrolledForeach(List(Const(true)),x9663,Block(Const(())),List(List(b6088), List(b6089)),List(List(b6090), List(b6091)))
    val b6088 = CounterIter(x9662, Some(0)).name("b6088").ctrl(x9668) // b6088
    val b6090 = Const(true).name("b6090").ctrl(x9668) // b6090
    val b6089 = CounterIter(x9661, Some(0)).name("b6089").ctrl(x9668) // b6089
    val b6091 = Const(true).name("b6091").ctrl(x9668) // b6091
    val x9667 = UnitController(style=SeqPipe, level=InnerControl).name("x9667").ctrl(x9668).srcCtx("CellsPar.scala:163:18") // UnitPipe(List(b6090, b6091),Block(Const(())))
    val x9664 = OpDef(op=BitAnd, inputs=List(b6090, b6091)).name("x9664").ctrl(x9667).srcCtx("UnrollingBase.scala:28:66") // And(b6090,b6091)
    val x9665 = StoreBanks(List(x9631_d0_b0, x9631_d1_b0), List(b6088, b6089), Const(0.0)).name("x9665").ctrl(x9667).srcCtx("CellsPar.scala:164:18") // SRAMStore(x9631,ArrayBuffer(Const(1), Const(512)),List(b6088, b6089),Const(0),Const(0),x9664)
    val x9666 = StoreBanks(List(x9630_d0_b0, x9630_d1_b0, x9630_d2_b0, x9630_d3_b0, x9630_d4_b0), List(b6088, b6089), Const(0.0)).name("x9666").ctrl(x9667).srcCtx("CellsPar.scala:165:18") // SRAMStore(x9630,ArrayBuffer(Const(1), Const(512)),List(b6088, b6089),Const(0),Const(0),x9664)
    val x9669 = Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x9669").ctrl(x10231).srcCtx("Decoder1Cell.scala:31:35") // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x9670 = CounterChain(List(x9669)).name("x9670").ctrl(x10231).srcCtx("Decoder1Cell.scala:31:41") // CounterChainNew(List(x9669))
    val x10174 = LoopController(style=SeqPipe, level=OuterControl, cchain=x9670).name("x10174").ctrl(x10231).srcCtx("Decoder1Cell.scala:31:41") // UnrolledForeach(List(Const(true)),x9670,Block(Const(())),List(List(b6101)),List(List(b6102)))
    val b6101 = CounterIter(x9669, Some(0)).name("b6101").ctrl(x10174) // b6101
    val b6102 = Const(true).name("b6102").ctrl(x10174) // b6102
    val x9672 = UnitController(style=SeqPipe, level=InnerControl).name("x9672").ctrl(x10174).srcCtx("Decoder1Cell.scala:31:41") // UnitPipe(List(b6102),Block(Const(())))
    val x9671 = OpDef(op=FixAdd, inputs=List(b6101, Const(1))).name("x9671").ctrl(x9672).srcCtx("Decoder1Cell.scala:32:34") // FixAdd(b6101,Const(1))
    val x9673 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x9673").ctrl(x10174).srcCtx("Decoder1Cell.scala:32:24") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x9674 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x9674").ctrl(x10174).srcCtx("Decoder1Cell.scala:32:24") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x9675 = CounterChain(List(x9673,x9674)).name("x9675").ctrl(x10174).srcCtx("Decoder1Cell.scala:32:24") // CounterChainNew(List(x9673, x9674))
    val x9705 = LoopController(style=StreamPipe, level=OuterControl, cchain=x9675).name("x9705").ctrl(x10174).srcCtx("Decoder1Cell.scala:32:24") // UnrolledForeach(List(b6102),x9675,Block(Const(())),List(List(b6108), List(b6109)),List(List(b6110), List(b6111)))
    val b6108 = CounterIter(x9673, Some(0)).name("b6108").ctrl(x9705) // b6108
    val b6110 = Const(true).name("b6110").ctrl(x9705) // b6110
    val b6109 = CounterIter(x9674, Some(0)).name("b6109").ctrl(x9705) // b6109
    val b6111 = Const(true).name("b6111").ctrl(x9705) // b6111
    val b10245 = StreamOut(field="offset").name("b10245").ctrl(x9705).srcCtx("Decoder1Cell.scala:32:24") // x9676 = StreamOutNew(BurstCmdBus)
    isAccum(b10245) = false
    bufferDepthOf(b10245) = 1
    val b10246 = StreamOut(field="size").name("b10246").ctrl(x9705).srcCtx("Decoder1Cell.scala:32:24") // x9676 = StreamOutNew(BurstCmdBus)
    isAccum(b10246) = false
    bufferDepthOf(b10246) = 1
    val x9677 = StreamIn(field="data").name("x9677").ctrl(x9705).srcCtx("Decoder1Cell.scala:32:24") // x9677 = StreamInNew(BurstDataBus())
    isAccum(x9677) = false
    bufferDepthOf(x9677) = 1
    val x9694 = UnitController(style=SeqPipe, level=InnerControl).name("x9694").ctrl(x9705).srcCtx("Decoder1Cell.scala:32:24") // UnitPipe(List(b6110, b6111, b6102),Block(x9693))
    val x9678 = OpDef(op=FixAdd, inputs=List(b6101, b6109)).name("x9678").ctrl(x9694).srcCtx("Decoder1Cell.scala:32:24") // FixAdd(b6101,b6109)
    val x9679 = b6108 // FixConvert(b6108,TRUE,_32,_0) (Same Type. No op)
    val x9680 = OpDef(op=FixSla, inputs=List(x9679, Const(11))).name("x9680").ctrl(x9694).srcCtx("Decoder1Cell.scala:32:24") // FixLsh(x9679,Const(11))
    val x9681 = x9678 // FixConvert(x9678,TRUE,_32,_0) (Same Type. No op)
    val x9682 = OpDef(op=FixSla, inputs=List(x9681, Const(9))).name("x9682").ctrl(x9694).srcCtx("Decoder1Cell.scala:32:24") // FixLsh(x9681,Const(9))
    val x9683 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x9684 = OpDef(op=FixAdd, inputs=List(x9680, x9682)).name("x9684").ctrl(x9694).srcCtx("Decoder1Cell.scala:32:24") // FixAdd(x9680,x9682)
    val x9685 = OpDef(op=FixAdd, inputs=List(x9684, x9683)).name("x9685").ctrl(x9694).srcCtx("Decoder1Cell.scala:32:24") // FixAdd(x9684,x9683)
    val x9686 = OpDef(op=FixSla, inputs=List(x9685, Const(2))).name("x9686").ctrl(x9694).srcCtx("Decoder1Cell.scala:32:24") // FixLsh(x9685,Const(2))
    val x9687 = x9686 // FixConvert(x9686,TRUE,_64,_0)
    val x9688 = DramAddress(x9545).name("x9688").ctrl(x9694).srcCtx("Decoder1Cell.scala:32:24") // GetDRAMAddress(x9545)
    val x9690_x9689 = OpDef(op=FixAdd, inputs=List(x9687, x9688)).name("x9690_x9689").ctrl(x9694).srcCtx("Decoder1Cell.scala:32:24") // FixAdd(x9687,x9688)
    // x9690 = SimpleStruct(ArrayBuffer((offset,x9689), (size,Const(2048)), (isLoad,Const(true))))
    val x9691 = OpDef(op=BitAnd, inputs=List(b6110, b6111)).name("x9691").ctrl(x9694).srcCtx("UnrollingBase.scala:28:66") // And(b6110,b6111)
    val x9692 = OpDef(op=BitAnd, inputs=List(x9691, b6102)).name("x9692").ctrl(x9694).srcCtx("UnrollingBase.scala:28:66") // And(x9691,b6102)
    val x9693_b10247_b10245 = WriteMem(b10245, x9690_x9689).name("x9693_b10247_b10245").ctrl(x9694).srcCtx("Decoder1Cell.scala:32:24") // StreamWrite(x9676,x9690,x9692)
    val x9693_b10248_b10246 = WriteMem(b10246, Const(2048)).name("x9693_b10248_b10246").ctrl(x9694).srcCtx("Decoder1Cell.scala:32:24") // StreamWrite(x9676,x9690,x9692)
    val x9695 = FringeDenseLoad(dram=List(x9545), cmdStream=List(b10245, b10246), dataStream=List(x9677)).name("x9695").ctrl(x9705).srcCtx("Decoder1Cell.scala:32:24") // FringeDenseLoad(x9545,x9676,x9677)
    val x9696 = Counter(min=Const(0), max=Const(512), step=Const(1), par=1).name("x9696").ctrl(x9705).srcCtx("Decoder1Cell.scala:32:24") // CounterNew(Const(0),Const(512),Const(1),Const(1))
    val x9697 = CounterChain(List(x9696)).name("x9697").ctrl(x9705).srcCtx("Decoder1Cell.scala:32:24") // CounterChainNew(List(x9696))
    val x9704 = LoopController(style=InnerPipe, level=InnerControl, cchain=x9697).name("x9704").ctrl(x9705).srcCtx("Decoder1Cell.scala:32:24") // UnrolledForeach(List(b6110, b6111, b6102),x9697,Block(Const(())),List(List(b6134)),List(List(b6135)))
    val b6134 = CounterIter(x9696, None).name("b6134").ctrl(x9704) // b6134
    val b6135 = Const(true).name("b6135").ctrl(x9704) // b6135
    val x9698 = OpDef(op=BitAnd, inputs=List(b6135, b6110)).name("x9698").ctrl(x9704).srcCtx("UnrollingBase.scala:28:66") // And(b6135,b6110)
    val x9699 = OpDef(op=BitAnd, inputs=List(b6111, b6102)).name("x9699").ctrl(x9704).srcCtx("UnrollingBase.scala:28:66") // And(b6111,b6102)
    val x9700 = OpDef(op=BitAnd, inputs=List(x9698, x9699)).name("x9700").ctrl(x9704).srcCtx("UnrollingBase.scala:28:66") // And(x9698,x9699)
    val x9701_x9701 = ReadMem(x9677).name("x9701_x9701").ctrl(x9704).srcCtx("Decoder1Cell.scala:32:24") // ParStreamRead(x9677,List(x9700))
    val x9702_x9702 = x9701_x9701 // x9702 = VectorApply(x9701,0)
    val x9703 = StoreBanks(List(x9629_d0_b0, x9629_d1_b0, x9629_d2_b0, x9629_d3_b0), List(b6108, b6134), x9702_x9702).name("x9703").ctrl(x9704).srcCtx("Decoder1Cell.scala:32:24") // ParSRAMStore(x9629,List(List(b6108, b6134)),List(x9702),List(x9700))
    val x9706 = Counter(min=Const(0), max=Const(512), step=Const(16), par=1).name("x9706").ctrl(x10174).srcCtx("CellsPar.scala:190:58") // CounterNew(Const(0),Const(512),Const(16),Const(1))
    val x9707 = Counter(min=Const(0), max=Const(1536), step=Const(16), par=1).name("x9707").ctrl(x10174).srcCtx("CellsPar.scala:190:33") // CounterNew(Const(0),Const(1536),Const(16),Const(1))
    val x9708 = CounterChain(List(x9707,x9706)).name("x9708").ctrl(x10174).srcCtx("CellsPar.scala:190:72") // CounterChainNew(List(x9707, x9706))
    val x9813 = LoopController(style=MetaPipe, level=OuterControl, cchain=x9708).name("x9813").ctrl(x10174).srcCtx("CellsPar.scala:190:72") // UnrolledForeach(List(b6102),x9708,Block(Const(())),List(List(b6147), List(b6148)),List(List(b6149), List(b6150)))
    val b6147 = CounterIter(x9707, Some(0)).name("b6147").ctrl(x9813) // b6147
    val b6149 = Const(true).name("b6149").ctrl(x9813) // b6149
    val b6148 = CounterIter(x9706, Some(0)).name("b6148").ctrl(x9813) // b6148
    val b6150 = Const(true).name("b6150").ctrl(x9813) // b6150
    val x9709_d0_b0 = SRAM(size=256, banking=Strided(banks=1, stride=16)).name("x9709_d0_b0").ctrl(x9813).srcCtx("CellsPar.scala:191:33:tileKernel") // x9709 = SRAMNew(ArrayBuffer(Const(16), Const(16)))
    isAccum(x9709_d0_b0) = false
    bufferDepthOf(x9709_d0_b0) = 2
    val x9712 = UnitController(style=SeqPipe, level=InnerControl).name("x9712").ctrl(x9813).srcCtx("CellsPar.scala:190:72") // UnitPipe(List(b6149, b6150, b6102),Block(Const(())))
    val x9710 = OpDef(op=FixAdd, inputs=List(b6147, Const(16))).name("x9710").ctrl(x9712).srcCtx("CellsPar.scala:192:36") // FixAdd(b6147,Const(16))
    val x9711 = OpDef(op=FixAdd, inputs=List(b6148, Const(16))).name("x9711").ctrl(x9712).srcCtx("CellsPar.scala:192:45") // FixAdd(b6148,Const(16))
    val x9713 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x9713").ctrl(x9813).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x9714 = CounterChain(List(x9713)).name("x9714").ctrl(x9813).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x9713))
    val x9743 = LoopController(style=StreamPipe, level=OuterControl, cchain=x9714).name("x9743").ctrl(x9813).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b6149, b6150, b6102),x9714,Block(Const(())),List(List(b6157)),List(List(b6158)))
    val b6157 = CounterIter(x9713, Some(0)).name("b6157").ctrl(x9743) // b6157
    val b6158 = Const(true).name("b6158").ctrl(x9743) // b6158
    val b10249 = StreamOut(field="offset").name("b10249").ctrl(x9743).srcCtx("CellsPar.scala:192:20") // x9715 = StreamOutNew(BurstCmdBus)
    isAccum(b10249) = false
    bufferDepthOf(b10249) = 1
    val b10250 = StreamOut(field="size").name("b10250").ctrl(x9743).srcCtx("CellsPar.scala:192:20") // x9715 = StreamOutNew(BurstCmdBus)
    isAccum(b10250) = false
    bufferDepthOf(b10250) = 1
    val x9716 = StreamIn(field="data").name("x9716").ctrl(x9743).srcCtx("CellsPar.scala:192:20") // x9716 = StreamInNew(BurstDataBus())
    isAccum(x9716) = false
    bufferDepthOf(x9716) = 1
    val x9731 = UnitController(style=SeqPipe, level=InnerControl).name("x9731").ctrl(x9743).srcCtx("CellsPar.scala:192:20") // UnitPipe(List(b6158, b6149, b6150, b6102),Block(x9730))
    val x9717 = OpDef(op=FixAdd, inputs=List(b6147, b6157)).name("x9717").ctrl(x9731).srcCtx("CellsPar.scala:192:20") // FixAdd(b6147,b6157)
    val x9718 = x9717 // FixConvert(x9717,TRUE,_32,_0) (Same Type. No op)
    val x9719 = OpDef(op=FixSla, inputs=List(x9718, Const(9))).name("x9719").ctrl(x9731).srcCtx("CellsPar.scala:192:20") // FixLsh(x9718,Const(9))
    val x9720 = b6148 // FixConvert(b6148,TRUE,_32,_0) (Same Type. No op)
    val x9721 = OpDef(op=FixAdd, inputs=List(x9719, x9720)).name("x9721").ctrl(x9731).srcCtx("CellsPar.scala:192:20") // FixAdd(x9719,x9720)
    val x9722 = OpDef(op=FixSla, inputs=List(x9721, Const(2))).name("x9722").ctrl(x9731).srcCtx("CellsPar.scala:192:20") // FixLsh(x9721,Const(2))
    val x9723 = x9722 // FixConvert(x9722,TRUE,_64,_0)
    val x9724 = DramAddress(x9581).name("x9724").ctrl(x9731).srcCtx("CellsPar.scala:192:20") // GetDRAMAddress(x9581)
    val x9726_x9725 = OpDef(op=FixAdd, inputs=List(x9723, x9724)).name("x9726_x9725").ctrl(x9731).srcCtx("CellsPar.scala:192:20") // FixAdd(x9723,x9724)
    // x9726 = SimpleStruct(ArrayBuffer((offset,x9725), (size,Const(64)), (isLoad,Const(true))))
    val x9727 = OpDef(op=BitAnd, inputs=List(b6158, b6149)).name("x9727").ctrl(x9731).srcCtx("UnrollingBase.scala:28:66") // And(b6158,b6149)
    val x9728 = OpDef(op=BitAnd, inputs=List(b6150, b6102)).name("x9728").ctrl(x9731).srcCtx("UnrollingBase.scala:28:66") // And(b6150,b6102)
    val x9729 = OpDef(op=BitAnd, inputs=List(x9727, x9728)).name("x9729").ctrl(x9731).srcCtx("UnrollingBase.scala:28:66") // And(x9727,x9728)
    val x9730_b10251_b10249 = WriteMem(b10249, x9726_x9725).name("x9730_b10251_b10249").ctrl(x9731).srcCtx("CellsPar.scala:192:20") // StreamWrite(x9715,x9726,x9729)
    val x9730_b10252_b10250 = WriteMem(b10250, Const(64)).name("x9730_b10252_b10250").ctrl(x9731).srcCtx("CellsPar.scala:192:20") // StreamWrite(x9715,x9726,x9729)
    val x9732 = FringeDenseLoad(dram=List(x9581), cmdStream=List(b10249, b10250), dataStream=List(x9716)).name("x9732").ctrl(x9743).srcCtx("CellsPar.scala:192:20") // FringeDenseLoad(x9581,x9715,x9716)
    val x9733 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x9733").ctrl(x9743).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x9734 = CounterChain(List(x9733)).name("x9734").ctrl(x9743).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x9733))
    val x9742 = LoopController(style=InnerPipe, level=InnerControl, cchain=x9734).name("x9742").ctrl(x9743).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b6158, b6149, b6150, b6102),x9734,Block(Const(())),List(List(b6179)),List(List(b6180)))
    val b6179 = CounterIter(x9733, None).name("b6179").ctrl(x9742) // b6179
    val b6180 = Const(true).name("b6180").ctrl(x9742) // b6180
    val x9735 = OpDef(op=BitAnd, inputs=List(b6180, b6158)).name("x9735").ctrl(x9742).srcCtx("UnrollingBase.scala:28:66") // And(b6180,b6158)
    val x9736 = OpDef(op=BitAnd, inputs=List(b6149, b6150)).name("x9736").ctrl(x9742).srcCtx("UnrollingBase.scala:28:66") // And(b6149,b6150)
    val x9737 = OpDef(op=BitAnd, inputs=List(x9735, x9736)).name("x9737").ctrl(x9742).srcCtx("UnrollingBase.scala:28:66") // And(x9735,x9736)
    val x9738 = OpDef(op=BitAnd, inputs=List(x9737, b6102)).name("x9738").ctrl(x9742).srcCtx("UnrollingBase.scala:28:66") // And(x9737,b6102)
    val x9739_x9739 = ReadMem(x9716).name("x9739_x9739").ctrl(x9742).srcCtx("CellsPar.scala:192:20") // ParStreamRead(x9716,List(x9738))
    val x9740_x9740 = x9739_x9739 // x9740 = VectorApply(x9739,0)
    val x9741 = StoreBanks(List(x9709_d0_b0), List(b6157, b6179), x9740_x9740).name("x9741").ctrl(x9742).srcCtx("CellsPar.scala:192:20") // ParSRAMStore(x9709,List(List(b6157, b6179)),List(x9740),List(x9738))
    val x9744 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x9744").ctrl(x9813).srcCtx("CellsPar.scala:193:34") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x9745 = CounterChain(List(x9744)).name("x9745").ctrl(x9813).srcCtx("CellsPar.scala:193:46") // CounterChainNew(List(x9744))
    val x9812 = LoopController(style=MetaPipe, level=OuterControl, cchain=x9745).name("x9812").ctrl(x9813).srcCtx("CellsPar.scala:193:46") // UnrolledForeach(List(b6149, b6150, b6102),x9745,Block(Const(())),List(List(b6192)),List(List(b6193)))
    val b6192 = CounterIter(x9744, Some(0)).name("b6192").ctrl(x9812) // b6192
    val b6193 = Const(true).name("b6193").ctrl(x9812) // b6193
    val x9746 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x9746").ctrl(x9812).srcCtx("CellsPar.scala:194:28") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x9747 = CounterChain(List(x9746)).name("x9747").ctrl(x9812).srcCtx("CellsPar.scala:194:42") // CounterChainNew(List(x9746))
    val x9811 = LoopController(style=MetaPipe, level=OuterControl, cchain=x9747).name("x9811").ctrl(x9812).srcCtx("CellsPar.scala:194:42") // UnrolledForeach(List(b6193, b6149, b6150, b6102),x9747,Block(Const(())),List(List(b6196)),List(List(b6197)))
    val b6196 = CounterIter(x9746, Some(0)).name("b6196").ctrl(x9811) // b6196
    val b6197 = Const(true).name("b6197").ctrl(x9811) // b6197
    val x9748_d0 = Reg(init=Some(0.0)).name("x9748_d0").ctrl(x9811).srcCtx("CellsPar.scala:195:34:prod") // x9748 = RegNew(Const(0))
    isAccum(x9748_d0) = false
    bufferDepthOf(x9748_d0) = 2
    val x9748_d1 = Reg(init=Some(0.0)).name("x9748_d1").ctrl(x9811).srcCtx("CellsPar.scala:195:34:prod") // x9748 = RegNew(Const(0))
    isAccum(x9748_d1) = true
    bufferDepthOf(x9748_d1) = 1
    val x9749 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x9749").ctrl(x9811).srcCtx("CellsPar.scala:195:43") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x9750 = CounterChain(List(x9749)).name("x9750").ctrl(x9811).srcCtx("CellsPar.scala:207:15") // CounterChainNew(List(x9749))
    val x9776 = LoopController(style=InnerPipe, level=InnerControl, cchain=x9750).name("x9776").ctrl(x9811).srcCtx("CellsPar.scala:207:15") // UnrolledReduce(List(b6197, b6193, b6149, b6150, b6102),x9750,x9748,Block((x9748) => Const(())),List(List(b6201)),List(List(b6202)))
    val b6201 = CounterIter(x9749, None).name("b6201").ctrl(x9776) // b6201
    val b6202 = Const(true).name("b6202").ctrl(x9776) // b6202
    val x9751 = OpDef(op=FixAdd, inputs=List(b6147, b6201)).name("x9751").ctrl(x9776).srcCtx("CellsPar.scala:196:42:reduce_size_offset") // FixAdd(b6147,b6201)
    val x9752 = OpDef(op=BitAnd, inputs=List(b6202, b6197)).name("x9752").ctrl(x9776).srcCtx("UnrollingBase.scala:28:66") // And(b6202,b6197)
    val x9753 = OpDef(op=BitAnd, inputs=List(b6193, b6149)).name("x9753").ctrl(x9776).srcCtx("UnrollingBase.scala:28:66") // And(b6193,b6149)
    val x9754 = OpDef(op=BitAnd, inputs=List(b6150, b6102)).name("x9754").ctrl(x9776).srcCtx("UnrollingBase.scala:28:66") // And(b6150,b6102)
    val x9755 = OpDef(op=BitAnd, inputs=List(x9752, x9753)).name("x9755").ctrl(x9776).srcCtx("UnrollingBase.scala:28:66") // And(x9752,x9753)
    val x9756 = OpDef(op=BitAnd, inputs=List(x9755, x9754)).name("x9756").ctrl(x9776).srcCtx("UnrollingBase.scala:28:66") // And(x9755,x9754)
    val x9757 = LoadBanks(List(x9709_d0_b0), List(b6201, b6196)).name("x9757").ctrl(x9776).srcCtx("CellsPar.scala:203:34:tk") // ParSRAMLoad(x9709,List(List(b6201, b6196)),List(x9756))
    val x9758 = x9757 // x9758 = VectorApply(x9757,0)
    val x9759 = LoadBanks(List(x9629_d3_b0), List(b6192, x9751)).name("x9759").ctrl(x9776).srcCtx("CellsPar.scala:204:28") // ParSRAMLoad(x9629,List(List(b6192, x9751)),List(x9756))
    val x9760 = x9759 // x9760 = VectorApply(x9759,0)
    val x9761 = OpDef(op=FixMul, inputs=List(x9760, x9758)).name("x9761").ctrl(x9776).srcCtx("CellsPar.scala:204:52:tempA") // FixMul(x9760,x9758)
    val x9762 = OpDef(op=FixSub, inputs=List(x9751, Const(512))).name("x9762").ctrl(x9776).srcCtx("CellsPar.scala:205:51") // FixSub(x9751,Const(512))
    val x9763 = LoadBanks(List(x9630_d4_b0), List(b6192, x9762)).name("x9763").ctrl(x9776).srcCtx("CellsPar.scala:205:28") // ParSRAMLoad(x9630,List(List(b6192, x9762)),List(x9756))
    val x9764 = x9763 // x9764 = VectorApply(x9763,0)
    val x9765 = OpDef(op=FixMul, inputs=List(x9764, x9758)).name("x9765").ctrl(x9776).srcCtx("CellsPar.scala:205:67:tempB") // FixMul(x9764,x9758)
    val x9766 = OpDef(op=FixLt, inputs=List(x9751, Const(512))).name("x9766").ctrl(x9776).srcCtx("CellsPar.scala:206:38") // FixLt(x9751,Const(512))
    val x9767 = OpDef(op=MuxOp, inputs=List(x9766, x9761, x9765)).name("x9767").ctrl(x9776).srcCtx("CellsPar.scala:206:18") // Mux(x9766,x9761,x9765)
    val x9768 = ReadMem(x9748_d1).name("x9768").ctrl(x9776).srcCtx("CellsPar.scala:207:15") // RegRead(x9748)
    val x9769 = OpDef(op=FixEql, inputs=List(b6201, Const(0))).name("x9769").ctrl(x9776).srcCtx("CellsPar.scala:207:15") // FixEql(b6201,Const(0))
    val x9770 = ReduceAccumOp(op=FixAdd, input=x9767, accum=x9768).name("x9770").ctrl(x9776).srcCtx("CellsPar.scala:207:17") // FixAdd(x9767,x9768)
    val x9771 = OpDef(op=BitAnd, inputs=List(b6197, b6193)).name("x9771").ctrl(x9776).srcCtx("UnrollingBase.scala:28:66") // And(b6197,b6193)
    val x9772 = OpDef(op=BitAnd, inputs=List(b6149, b6150)).name("x9772").ctrl(x9776).srcCtx("UnrollingBase.scala:28:66") // And(b6149,b6150)
    val x9773 = OpDef(op=BitAnd, inputs=List(x9771, x9772)).name("x9773").ctrl(x9776).srcCtx("UnrollingBase.scala:28:66") // And(x9771,x9772)
    val x9774 = OpDef(op=BitAnd, inputs=List(x9773, b6102)).name("x9774").ctrl(x9776).srcCtx("UnrollingBase.scala:28:66") // And(x9773,b6102)
    val x9775_x9748_d0 = WriteMem(x9748_d0, x9770).name("x9775_x9748_d0").ctrl(x9776).srcCtx("CellsPar.scala:207:15") // RegWrite(x9748,x9770,x9774)
    val x9775_x9748_d1 = WriteMem(x9748_d1, x9770).name("x9775_x9748_d1").ctrl(x9776).srcCtx("CellsPar.scala:207:15") // RegWrite(x9748,x9770,x9774)
    val x9810 = UnitController(style=SeqPipe, level=InnerControl).name("x9810").ctrl(x9811).srcCtx("CellsPar.scala:194:42") // UnitPipe(List(b6197, b6193, b6149, b6150, b6102),Block(Const(())))
    val x9777 = OpDef(op=FixAdd, inputs=List(b6148, b6196)).name("x9777").ctrl(x9810).srcCtx("CellsPar.scala:209:31:colOffset") // FixAdd(b6148,b6196)
    val x9778 = ReadMem(x9748_d0).name("x9778").ctrl(x9810).srcCtx("CellsPar.scala:210:28") // RegRead(x9748)
    val x9779 = OpDef(op=FixEql, inputs=List(b6147, Const(0))).name("x9779").ctrl(x9810).srcCtx("CellsPar.scala:210:42") // FixEql(b6147,Const(0))
    val x9780 = OpDef(op=BitAnd, inputs=List(b6197, b6193)).name("x9780").ctrl(x9810).srcCtx("UnrollingBase.scala:28:66") // And(b6197,b6193)
    val x9781 = OpDef(op=BitAnd, inputs=List(b6149, b6150)).name("x9781").ctrl(x9810).srcCtx("UnrollingBase.scala:28:66") // And(b6149,b6150)
    val x9782 = OpDef(op=BitAnd, inputs=List(x9780, x9781)).name("x9782").ctrl(x9810).srcCtx("UnrollingBase.scala:28:66") // And(x9780,x9781)
    val x9783 = OpDef(op=BitAnd, inputs=List(x9782, b6102)).name("x9783").ctrl(x9810).srcCtx("UnrollingBase.scala:28:66") // And(x9782,b6102)
    val x9784 = LoadBanks(List(x9636_d3_b0), List(Const(0), x9777)).name("x9784").ctrl(x9810).srcCtx("CellsPar.scala:210:52") // SRAMLoad(x9636,ArrayBuffer(Const(1), Const(2048)),List(Const(0), x9777),Const(0),x9783)
    val x9785 = LoadBanks(List(x9632_d1_b0), List(b6192, x9777)).name("x9785").ctrl(x9810).srcCtx("CellsPar.scala:210:91") // SRAMLoad(x9632,ArrayBuffer(Const(1), Const(512)),List(b6192, x9777),Const(0),x9783)
    val x9786 = OpDef(op=MuxOp, inputs=List(x9779, x9784, x9785)).name("x9786").ctrl(x9810).srcCtx("CellsPar.scala:210:39") // Mux(x9779,x9784,x9785)
    val x9787 = OpDef(op=FixAdd, inputs=List(x9778, x9786)).name("x9787").ctrl(x9810).srcCtx("CellsPar.scala:210:34:ele") // FixAdd(x9778,x9786)
    val x9788 = OpDef(op=FixLeq, inputs=List(Const(1520), b6147)).name("x9788").ctrl(x9810).srcCtx("CellsPar.scala:218:46") // FixLeq(Const(1520),b6147)
    // x9789 = FixConvert(Const(0),TRUE,_8,_24) x.tp=FixPt[TRUE,_32,_0] {
    val x9789_int1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("01111111111111111111111111111111"))).name("x9789_int1").ctrl(x9810).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x9789_int2 = OpDef(op=FixSla, inputs=List(x9789_int1, Const(24))).name("x9789_int2").ctrl(x9810).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x9789_frac1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("00000000000000000000000000000000"))).name("x9789_frac1").ctrl(x9810).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x9789_frac2 = OpDef(op=FixSla, inputs=List(x9789_frac1, Const(24))).name("x9789_frac2").ctrl(x9810).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x9789 = OpDef(op=BitOr, inputs=List(x9789_int2, x9789_frac2)).name("x9789").ctrl(x9810).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    // }
    val x9790 = OpDef(op=FixAdd, inputs=List(x9787, x9789)).name("x9790").ctrl(x9810).srcCtx("CellsPar.scala:218:87") // FixAdd(x9787,x9789)
    val x9791 = OpDef(op=FixSra, inputs=List(x9790, Const(1))).name("x9791").ctrl(x9810).srcCtx("CellsPar.scala:29:22") // FixRsh(x9790,Const(1))
    val x9792 = x9791 // FixConvert(x9791,TRUE,_8,_24) (Same Type. No op)
    val x9793 = OpDef(op=FixAbs, inputs=List(x9792)).name("x9793").ctrl(x9810).srcCtx("CellsPar.scala:37:19:absp") // FixAbs(x9792)
    val x9794 = OpDef(op=FixLt, inputs=List(Const(2.5), x9793)).name("x9794").ctrl(x9810).srcCtx("CellsPar.scala:51:23:cond1") // FixLt(Const(2.5),x9793)
    val x9795 = OpDef(op=FixLt, inputs=List(Const(0.5), x9793)).name("x9795").ctrl(x9810).srcCtx("CellsPar.scala:54:35") // FixLt(Const(0.5),x9793)
    val x9796 = OpDef(op=FixLeq, inputs=List(x9793, Const(2.5))).name("x9796").ctrl(x9810).srcCtx("CellsPar.scala:54:52") // FixLeq(x9793,Const(2.5))
    val x9797 = OpDef(op=BitAnd, inputs=List(x9795, x9796)).name("x9797").ctrl(x9810).srcCtx("CellsPar.scala:54:43:cond2") // And(x9795,x9796)
    val x9798 = OpDef(op=FixSra, inputs=List(x9793, Const(2))).name("x9798").ctrl(x9810).srcCtx("CellsPar.scala:56:21:div4") // FixRsh(x9793,Const(2))
    val x9799 = OpDef(op=FixAdd, inputs=List(x9798, Const(0.375))).name("x9799").ctrl(x9810).srcCtx("CellsPar.scala:57:27:div4Offset") // FixAdd(x9798,Const(0.375))
    val x9800 = OpDef(op=MuxOp, inputs=List(x9797, x9799, x9793)).name("x9800").ctrl(x9810).srcCtx("CellsPar.scala:58:20:body2") // Mux(x9797,x9799,x9793)
    val x9801 = OpDef(op=MuxOp, inputs=List(x9794, Const(1.0), x9800)).name("x9801").ctrl(x9810).srcCtx("CellsPar.scala:60:20:absre") // Mux(x9794,Const(1),x9800)
    val x9802 = OpDef(op=FixNeg, inputs=List(x9801)).name("x9802").ctrl(x9810).srcCtx("CellsPar.scala:62:29:mabre") // FixNeg(x9801)
    val x9803 = OpDef(op=FixLt, inputs=List(x9792, Const(0.0))).name("x9803").ctrl(x9810).srcCtx("CellsPar.scala:63:22") // FixLt(x9792,Const(0))
    val x9804 = OpDef(op=MuxOp, inputs=List(x9803, x9802, x9801)).name("x9804").ctrl(x9810).srcCtx("CellsPar.scala:63:17:re") // Mux(x9803,x9802,x9801)
    val x9805 = x9804 // FixConvert(x9804,TRUE,_8,_24) (Same Type. No op)
    val x9806 = OpDef(op=FixAdd, inputs=List(x9805, Const(1.0))).name("x9806").ctrl(x9810).srcCtx("CellsPar.scala:29:33") // FixAdd(x9805,Const(1))
    val x9807 = OpDef(op=FixSra, inputs=List(x9806, Const(1))).name("x9807").ctrl(x9810).srcCtx("CellsPar.scala:29:44") // FixRsh(x9806,Const(1))
    val x9808 = OpDef(op=MuxOp, inputs=List(x9788, x9807, x9787)).name("x9808").ctrl(x9810).srcCtx("CellsPar.scala:218:43") // Mux(x9788,x9807,x9787)
    val x9809 = StoreBanks(List(x9632_d0_b0, x9632_d1_b0), List(b6192, x9777), x9808).name("x9809").ctrl(x9810).srcCtx("CellsPar.scala:218:38") // SRAMStore(x9632,ArrayBuffer(Const(1), Const(512)),List(b6192, x9777),Const(0),x9808,x9783)
    val x9814 = Counter(min=Const(0), max=Const(512), step=Const(16), par=1).name("x9814").ctrl(x10174).srcCtx("CellsPar.scala:190:58") // CounterNew(Const(0),Const(512),Const(16),Const(1))
    val x9815 = Counter(min=Const(0), max=Const(1536), step=Const(16), par=1).name("x9815").ctrl(x10174).srcCtx("CellsPar.scala:190:33") // CounterNew(Const(0),Const(1536),Const(16),Const(1))
    val x9816 = CounterChain(List(x9815,x9814)).name("x9816").ctrl(x10174).srcCtx("CellsPar.scala:190:72") // CounterChainNew(List(x9815, x9814))
    val x9919 = LoopController(style=MetaPipe, level=OuterControl, cchain=x9816).name("x9919").ctrl(x10174).srcCtx("CellsPar.scala:190:72") // UnrolledForeach(List(b6102),x9816,Block(Const(())),List(List(b6269), List(b6270)),List(List(b6271), List(b6272)))
    val b6269 = CounterIter(x9815, Some(0)).name("b6269").ctrl(x9919) // b6269
    val b6271 = Const(true).name("b6271").ctrl(x9919) // b6271
    val b6270 = CounterIter(x9814, Some(0)).name("b6270").ctrl(x9919) // b6270
    val b6272 = Const(true).name("b6272").ctrl(x9919) // b6272
    val x9817_d0_b0 = SRAM(size=256, banking=Strided(banks=1, stride=16)).name("x9817_d0_b0").ctrl(x9919).srcCtx("CellsPar.scala:191:33:tileKernel") // x9817 = SRAMNew(ArrayBuffer(Const(16), Const(16)))
    isAccum(x9817_d0_b0) = false
    bufferDepthOf(x9817_d0_b0) = 2
    val x9820 = UnitController(style=SeqPipe, level=InnerControl).name("x9820").ctrl(x9919).srcCtx("CellsPar.scala:190:72") // UnitPipe(List(b6271, b6272, b6102),Block(Const(())))
    val x9818 = OpDef(op=FixAdd, inputs=List(b6269, Const(16))).name("x9818").ctrl(x9820).srcCtx("CellsPar.scala:192:36") // FixAdd(b6269,Const(16))
    val x9819 = OpDef(op=FixAdd, inputs=List(b6270, Const(16))).name("x9819").ctrl(x9820).srcCtx("CellsPar.scala:192:45") // FixAdd(b6270,Const(16))
    val x9821 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x9821").ctrl(x9919).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x9822 = CounterChain(List(x9821)).name("x9822").ctrl(x9919).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x9821))
    val x9851 = LoopController(style=StreamPipe, level=OuterControl, cchain=x9822).name("x9851").ctrl(x9919).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b6271, b6272, b6102),x9822,Block(Const(())),List(List(b6279)),List(List(b6280)))
    val b6279 = CounterIter(x9821, Some(0)).name("b6279").ctrl(x9851) // b6279
    val b6280 = Const(true).name("b6280").ctrl(x9851) // b6280
    val b10253 = StreamOut(field="offset").name("b10253").ctrl(x9851).srcCtx("CellsPar.scala:192:20") // x9823 = StreamOutNew(BurstCmdBus)
    isAccum(b10253) = false
    bufferDepthOf(b10253) = 1
    val b10254 = StreamOut(field="size").name("b10254").ctrl(x9851).srcCtx("CellsPar.scala:192:20") // x9823 = StreamOutNew(BurstCmdBus)
    isAccum(b10254) = false
    bufferDepthOf(b10254) = 1
    val x9824 = StreamIn(field="data").name("x9824").ctrl(x9851).srcCtx("CellsPar.scala:192:20") // x9824 = StreamInNew(BurstDataBus())
    isAccum(x9824) = false
    bufferDepthOf(x9824) = 1
    val x9839 = UnitController(style=SeqPipe, level=InnerControl).name("x9839").ctrl(x9851).srcCtx("CellsPar.scala:192:20") // UnitPipe(List(b6280, b6271, b6272, b6102),Block(x9838))
    val x9825 = OpDef(op=FixAdd, inputs=List(b6269, b6279)).name("x9825").ctrl(x9839).srcCtx("CellsPar.scala:192:20") // FixAdd(b6269,b6279)
    val x9826 = x9825 // FixConvert(x9825,TRUE,_32,_0) (Same Type. No op)
    val x9827 = OpDef(op=FixSla, inputs=List(x9826, Const(9))).name("x9827").ctrl(x9839).srcCtx("CellsPar.scala:192:20") // FixLsh(x9826,Const(9))
    val x9828 = b6270 // FixConvert(b6270,TRUE,_32,_0) (Same Type. No op)
    val x9829 = OpDef(op=FixAdd, inputs=List(x9827, x9828)).name("x9829").ctrl(x9839).srcCtx("CellsPar.scala:192:20") // FixAdd(x9827,x9828)
    val x9830 = OpDef(op=FixSla, inputs=List(x9829, Const(2))).name("x9830").ctrl(x9839).srcCtx("CellsPar.scala:192:20") // FixLsh(x9829,Const(2))
    val x9831 = x9830 // FixConvert(x9830,TRUE,_64,_0)
    val x9832 = DramAddress(x9582).name("x9832").ctrl(x9839).srcCtx("CellsPar.scala:192:20") // GetDRAMAddress(x9582)
    val x9834_x9833 = OpDef(op=FixAdd, inputs=List(x9831, x9832)).name("x9834_x9833").ctrl(x9839).srcCtx("CellsPar.scala:192:20") // FixAdd(x9831,x9832)
    // x9834 = SimpleStruct(ArrayBuffer((offset,x9833), (size,Const(64)), (isLoad,Const(true))))
    val x9835 = OpDef(op=BitAnd, inputs=List(b6280, b6271)).name("x9835").ctrl(x9839).srcCtx("UnrollingBase.scala:28:66") // And(b6280,b6271)
    val x9836 = OpDef(op=BitAnd, inputs=List(b6272, b6102)).name("x9836").ctrl(x9839).srcCtx("UnrollingBase.scala:28:66") // And(b6272,b6102)
    val x9837 = OpDef(op=BitAnd, inputs=List(x9835, x9836)).name("x9837").ctrl(x9839).srcCtx("UnrollingBase.scala:28:66") // And(x9835,x9836)
    val x9838_b10255_b10253 = WriteMem(b10253, x9834_x9833).name("x9838_b10255_b10253").ctrl(x9839).srcCtx("CellsPar.scala:192:20") // StreamWrite(x9823,x9834,x9837)
    val x9838_b10256_b10254 = WriteMem(b10254, Const(64)).name("x9838_b10256_b10254").ctrl(x9839).srcCtx("CellsPar.scala:192:20") // StreamWrite(x9823,x9834,x9837)
    val x9840 = FringeDenseLoad(dram=List(x9582), cmdStream=List(b10253, b10254), dataStream=List(x9824)).name("x9840").ctrl(x9851).srcCtx("CellsPar.scala:192:20") // FringeDenseLoad(x9582,x9823,x9824)
    val x9841 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x9841").ctrl(x9851).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x9842 = CounterChain(List(x9841)).name("x9842").ctrl(x9851).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x9841))
    val x9850 = LoopController(style=InnerPipe, level=InnerControl, cchain=x9842).name("x9850").ctrl(x9851).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b6280, b6271, b6272, b6102),x9842,Block(Const(())),List(List(b6301)),List(List(b6302)))
    val b6301 = CounterIter(x9841, None).name("b6301").ctrl(x9850) // b6301
    val b6302 = Const(true).name("b6302").ctrl(x9850) // b6302
    val x9843 = OpDef(op=BitAnd, inputs=List(b6302, b6280)).name("x9843").ctrl(x9850).srcCtx("UnrollingBase.scala:28:66") // And(b6302,b6280)
    val x9844 = OpDef(op=BitAnd, inputs=List(b6271, b6272)).name("x9844").ctrl(x9850).srcCtx("UnrollingBase.scala:28:66") // And(b6271,b6272)
    val x9845 = OpDef(op=BitAnd, inputs=List(x9843, x9844)).name("x9845").ctrl(x9850).srcCtx("UnrollingBase.scala:28:66") // And(x9843,x9844)
    val x9846 = OpDef(op=BitAnd, inputs=List(x9845, b6102)).name("x9846").ctrl(x9850).srcCtx("UnrollingBase.scala:28:66") // And(x9845,b6102)
    val x9847_x9847 = ReadMem(x9824).name("x9847_x9847").ctrl(x9850).srcCtx("CellsPar.scala:192:20") // ParStreamRead(x9824,List(x9846))
    val x9848_x9848 = x9847_x9847 // x9848 = VectorApply(x9847,0)
    val x9849 = StoreBanks(List(x9817_d0_b0), List(b6279, b6301), x9848_x9848).name("x9849").ctrl(x9850).srcCtx("CellsPar.scala:192:20") // ParSRAMStore(x9817,List(List(b6279, b6301)),List(x9848),List(x9846))
    val x9852 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x9852").ctrl(x9919).srcCtx("CellsPar.scala:193:34") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x9853 = CounterChain(List(x9852)).name("x9853").ctrl(x9919).srcCtx("CellsPar.scala:193:46") // CounterChainNew(List(x9852))
    val x9918 = LoopController(style=MetaPipe, level=OuterControl, cchain=x9853).name("x9918").ctrl(x9919).srcCtx("CellsPar.scala:193:46") // UnrolledForeach(List(b6271, b6272, b6102),x9853,Block(Const(())),List(List(b6314)),List(List(b6315)))
    val b6314 = CounterIter(x9852, Some(0)).name("b6314").ctrl(x9918) // b6314
    val b6315 = Const(true).name("b6315").ctrl(x9918) // b6315
    val x9854 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x9854").ctrl(x9918).srcCtx("CellsPar.scala:194:28") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x9855 = CounterChain(List(x9854)).name("x9855").ctrl(x9918).srcCtx("CellsPar.scala:194:42") // CounterChainNew(List(x9854))
    val x9917 = LoopController(style=MetaPipe, level=OuterControl, cchain=x9855).name("x9917").ctrl(x9918).srcCtx("CellsPar.scala:194:42") // UnrolledForeach(List(b6315, b6271, b6272, b6102),x9855,Block(Const(())),List(List(b6318)),List(List(b6319)))
    val b6318 = CounterIter(x9854, Some(0)).name("b6318").ctrl(x9917) // b6318
    val b6319 = Const(true).name("b6319").ctrl(x9917) // b6319
    val x9856_d0 = Reg(init=Some(0.0)).name("x9856_d0").ctrl(x9917).srcCtx("CellsPar.scala:195:34:prod") // x9856 = RegNew(Const(0))
    isAccum(x9856_d0) = false
    bufferDepthOf(x9856_d0) = 2
    val x9856_d1 = Reg(init=Some(0.0)).name("x9856_d1").ctrl(x9917).srcCtx("CellsPar.scala:195:34:prod") // x9856 = RegNew(Const(0))
    isAccum(x9856_d1) = true
    bufferDepthOf(x9856_d1) = 1
    val x9857 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x9857").ctrl(x9917).srcCtx("CellsPar.scala:195:43") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x9858 = CounterChain(List(x9857)).name("x9858").ctrl(x9917).srcCtx("CellsPar.scala:207:15") // CounterChainNew(List(x9857))
    val x9884 = LoopController(style=InnerPipe, level=InnerControl, cchain=x9858).name("x9884").ctrl(x9917).srcCtx("CellsPar.scala:207:15") // UnrolledReduce(List(b6319, b6315, b6271, b6272, b6102),x9858,x9856,Block((x9856) => Const(())),List(List(b6323)),List(List(b6324)))
    val b6323 = CounterIter(x9857, None).name("b6323").ctrl(x9884) // b6323
    val b6324 = Const(true).name("b6324").ctrl(x9884) // b6324
    val x9859 = OpDef(op=FixAdd, inputs=List(b6269, b6323)).name("x9859").ctrl(x9884).srcCtx("CellsPar.scala:196:42:reduce_size_offset") // FixAdd(b6269,b6323)
    val x9860 = OpDef(op=BitAnd, inputs=List(b6324, b6319)).name("x9860").ctrl(x9884).srcCtx("UnrollingBase.scala:28:66") // And(b6324,b6319)
    val x9861 = OpDef(op=BitAnd, inputs=List(b6315, b6271)).name("x9861").ctrl(x9884).srcCtx("UnrollingBase.scala:28:66") // And(b6315,b6271)
    val x9862 = OpDef(op=BitAnd, inputs=List(b6272, b6102)).name("x9862").ctrl(x9884).srcCtx("UnrollingBase.scala:28:66") // And(b6272,b6102)
    val x9863 = OpDef(op=BitAnd, inputs=List(x9860, x9861)).name("x9863").ctrl(x9884).srcCtx("UnrollingBase.scala:28:66") // And(x9860,x9861)
    val x9864 = OpDef(op=BitAnd, inputs=List(x9863, x9862)).name("x9864").ctrl(x9884).srcCtx("UnrollingBase.scala:28:66") // And(x9863,x9862)
    val x9865 = LoadBanks(List(x9817_d0_b0), List(b6323, b6318)).name("x9865").ctrl(x9884).srcCtx("CellsPar.scala:203:34:tk") // ParSRAMLoad(x9817,List(List(b6323, b6318)),List(x9864))
    val x9866 = x9865 // x9866 = VectorApply(x9865,0)
    val x9867 = LoadBanks(List(x9629_d2_b0), List(b6314, x9859)).name("x9867").ctrl(x9884).srcCtx("CellsPar.scala:204:28") // ParSRAMLoad(x9629,List(List(b6314, x9859)),List(x9864))
    val x9868 = x9867 // x9868 = VectorApply(x9867,0)
    val x9869 = OpDef(op=FixMul, inputs=List(x9868, x9866)).name("x9869").ctrl(x9884).srcCtx("CellsPar.scala:204:52:tempA") // FixMul(x9868,x9866)
    val x9870 = OpDef(op=FixSub, inputs=List(x9859, Const(512))).name("x9870").ctrl(x9884).srcCtx("CellsPar.scala:205:51") // FixSub(x9859,Const(512))
    val x9871 = LoadBanks(List(x9630_d3_b0), List(b6314, x9870)).name("x9871").ctrl(x9884).srcCtx("CellsPar.scala:205:28") // ParSRAMLoad(x9630,List(List(b6314, x9870)),List(x9864))
    val x9872 = x9871 // x9872 = VectorApply(x9871,0)
    val x9873 = OpDef(op=FixMul, inputs=List(x9872, x9866)).name("x9873").ctrl(x9884).srcCtx("CellsPar.scala:205:67:tempB") // FixMul(x9872,x9866)
    val x9874 = OpDef(op=FixLt, inputs=List(x9859, Const(512))).name("x9874").ctrl(x9884).srcCtx("CellsPar.scala:206:38") // FixLt(x9859,Const(512))
    val x9875 = OpDef(op=MuxOp, inputs=List(x9874, x9869, x9873)).name("x9875").ctrl(x9884).srcCtx("CellsPar.scala:206:18") // Mux(x9874,x9869,x9873)
    val x9876 = ReadMem(x9856_d1).name("x9876").ctrl(x9884).srcCtx("CellsPar.scala:207:15") // RegRead(x9856)
    val x9877 = OpDef(op=FixEql, inputs=List(b6323, Const(0))).name("x9877").ctrl(x9884).srcCtx("CellsPar.scala:207:15") // FixEql(b6323,Const(0))
    val x9878 = ReduceAccumOp(op=FixAdd, input=x9875, accum=x9876).name("x9878").ctrl(x9884).srcCtx("CellsPar.scala:207:17") // FixAdd(x9875,x9876)
    val x9879 = OpDef(op=BitAnd, inputs=List(b6319, b6315)).name("x9879").ctrl(x9884).srcCtx("UnrollingBase.scala:28:66") // And(b6319,b6315)
    val x9880 = OpDef(op=BitAnd, inputs=List(b6271, b6272)).name("x9880").ctrl(x9884).srcCtx("UnrollingBase.scala:28:66") // And(b6271,b6272)
    val x9881 = OpDef(op=BitAnd, inputs=List(x9879, x9880)).name("x9881").ctrl(x9884).srcCtx("UnrollingBase.scala:28:66") // And(x9879,x9880)
    val x9882 = OpDef(op=BitAnd, inputs=List(x9881, b6102)).name("x9882").ctrl(x9884).srcCtx("UnrollingBase.scala:28:66") // And(x9881,b6102)
    val x9883_x9856_d0 = WriteMem(x9856_d0, x9878).name("x9883_x9856_d0").ctrl(x9884).srcCtx("CellsPar.scala:207:15") // RegWrite(x9856,x9878,x9882)
    val x9883_x9856_d1 = WriteMem(x9856_d1, x9878).name("x9883_x9856_d1").ctrl(x9884).srcCtx("CellsPar.scala:207:15") // RegWrite(x9856,x9878,x9882)
    val x9916 = UnitController(style=SeqPipe, level=InnerControl).name("x9916").ctrl(x9917).srcCtx("CellsPar.scala:194:42") // UnitPipe(List(b6319, b6315, b6271, b6272, b6102),Block(Const(())))
    val x9885 = OpDef(op=FixAdd, inputs=List(b6270, b6318)).name("x9885").ctrl(x9916).srcCtx("CellsPar.scala:209:31:colOffset") // FixAdd(b6270,b6318)
    val x9886 = ReadMem(x9856_d0).name("x9886").ctrl(x9916).srcCtx("CellsPar.scala:210:28") // RegRead(x9856)
    val x9887 = OpDef(op=FixEql, inputs=List(b6269, Const(0))).name("x9887").ctrl(x9916).srcCtx("CellsPar.scala:210:42") // FixEql(b6269,Const(0))
    val x9888 = OpDef(op=FixAdd, inputs=List(x9885, Const(512))).name("x9888").ctrl(x9916).srcCtx("CellsPar.scala:210:66") // FixAdd(x9885,Const(512))
    val x9889 = OpDef(op=BitAnd, inputs=List(b6319, b6315)).name("x9889").ctrl(x9916).srcCtx("UnrollingBase.scala:28:66") // And(b6319,b6315)
    val x9890 = OpDef(op=BitAnd, inputs=List(b6271, b6272)).name("x9890").ctrl(x9916).srcCtx("UnrollingBase.scala:28:66") // And(b6271,b6272)
    val x9891 = OpDef(op=BitAnd, inputs=List(x9889, x9890)).name("x9891").ctrl(x9916).srcCtx("UnrollingBase.scala:28:66") // And(x9889,x9890)
    val x9892 = OpDef(op=BitAnd, inputs=List(x9891, b6102)).name("x9892").ctrl(x9916).srcCtx("UnrollingBase.scala:28:66") // And(x9891,b6102)
    val x9893 = LoadBanks(List(x9636_d2_b0), List(Const(0), x9888)).name("x9893").ctrl(x9916).srcCtx("CellsPar.scala:210:52") // SRAMLoad(x9636,ArrayBuffer(Const(1), Const(2048)),List(Const(0), x9888),Const(0),x9892)
    val x9894 = LoadBanks(List(x9633_d1_b0), List(b6314, x9885)).name("x9894").ctrl(x9916).srcCtx("CellsPar.scala:210:91") // SRAMLoad(x9633,ArrayBuffer(Const(1), Const(512)),List(b6314, x9885),Const(0),x9892)
    val x9895 = OpDef(op=MuxOp, inputs=List(x9887, x9893, x9894)).name("x9895").ctrl(x9916).srcCtx("CellsPar.scala:210:39") // Mux(x9887,x9893,x9894)
    val x9896 = OpDef(op=FixAdd, inputs=List(x9886, x9895)).name("x9896").ctrl(x9916).srcCtx("CellsPar.scala:210:34:ele") // FixAdd(x9886,x9895)
    val x9897 = OpDef(op=FixLeq, inputs=List(Const(1520), b6269)).name("x9897").ctrl(x9916).srcCtx("CellsPar.scala:218:46") // FixLeq(Const(1520),b6269)
    // x9898 = FixConvert(Const(0),TRUE,_8,_24) x.tp=FixPt[TRUE,_32,_0] {
    val x9898_int1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("01111111111111111111111111111111"))).name("x9898_int1").ctrl(x9916).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x9898_int2 = OpDef(op=FixSla, inputs=List(x9898_int1, Const(24))).name("x9898_int2").ctrl(x9916).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x9898_frac1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("00000000000000000000000000000000"))).name("x9898_frac1").ctrl(x9916).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x9898_frac2 = OpDef(op=FixSla, inputs=List(x9898_frac1, Const(24))).name("x9898_frac2").ctrl(x9916).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x9898 = OpDef(op=BitOr, inputs=List(x9898_int2, x9898_frac2)).name("x9898").ctrl(x9916).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    // }
    val x9899 = OpDef(op=FixAdd, inputs=List(x9896, x9898)).name("x9899").ctrl(x9916).srcCtx("CellsPar.scala:218:87") // FixAdd(x9896,x9898)
    val x9900 = x9899 // FixConvert(x9899,TRUE,_8,_24) (Same Type. No op)
    val x9901 = OpDef(op=FixAbs, inputs=List(x9900)).name("x9901").ctrl(x9916).srcCtx("CellsPar.scala:37:19:absp") // FixAbs(x9900)
    val x9902 = OpDef(op=FixLt, inputs=List(Const(2.5), x9901)).name("x9902").ctrl(x9916).srcCtx("CellsPar.scala:51:23:cond1") // FixLt(Const(2.5),x9901)
    val x9903 = OpDef(op=FixLt, inputs=List(Const(0.5), x9901)).name("x9903").ctrl(x9916).srcCtx("CellsPar.scala:54:35") // FixLt(Const(0.5),x9901)
    val x9904 = OpDef(op=FixLeq, inputs=List(x9901, Const(2.5))).name("x9904").ctrl(x9916).srcCtx("CellsPar.scala:54:52") // FixLeq(x9901,Const(2.5))
    val x9905 = OpDef(op=BitAnd, inputs=List(x9903, x9904)).name("x9905").ctrl(x9916).srcCtx("CellsPar.scala:54:43:cond2") // And(x9903,x9904)
    val x9906 = OpDef(op=FixSra, inputs=List(x9901, Const(2))).name("x9906").ctrl(x9916).srcCtx("CellsPar.scala:56:21:div4") // FixRsh(x9901,Const(2))
    val x9907 = OpDef(op=FixAdd, inputs=List(x9906, Const(0.375))).name("x9907").ctrl(x9916).srcCtx("CellsPar.scala:57:27:div4Offset") // FixAdd(x9906,Const(0.375))
    val x9908 = OpDef(op=MuxOp, inputs=List(x9905, x9907, x9901)).name("x9908").ctrl(x9916).srcCtx("CellsPar.scala:58:20:body2") // Mux(x9905,x9907,x9901)
    val x9909 = OpDef(op=MuxOp, inputs=List(x9902, Const(1.0), x9908)).name("x9909").ctrl(x9916).srcCtx("CellsPar.scala:60:20:absre") // Mux(x9902,Const(1),x9908)
    val x9910 = OpDef(op=FixNeg, inputs=List(x9909)).name("x9910").ctrl(x9916).srcCtx("CellsPar.scala:62:29:mabre") // FixNeg(x9909)
    val x9911 = OpDef(op=FixLt, inputs=List(x9900, Const(0.0))).name("x9911").ctrl(x9916).srcCtx("CellsPar.scala:63:22") // FixLt(x9900,Const(0))
    val x9912 = OpDef(op=MuxOp, inputs=List(x9911, x9910, x9909)).name("x9912").ctrl(x9916).srcCtx("CellsPar.scala:63:17:re") // Mux(x9911,x9910,x9909)
    val x9913 = x9912 // FixConvert(x9912,TRUE,_8,_24) (Same Type. No op)
    val x9914 = OpDef(op=MuxOp, inputs=List(x9897, x9913, x9896)).name("x9914").ctrl(x9916).srcCtx("CellsPar.scala:218:43") // Mux(x9897,x9913,x9896)
    val x9915 = StoreBanks(List(x9633_d0_b0, x9633_d1_b0), List(b6314, x9885), x9914).name("x9915").ctrl(x9916).srcCtx("CellsPar.scala:218:38") // SRAMStore(x9633,ArrayBuffer(Const(1), Const(512)),List(b6314, x9885),Const(0),x9914,x9892)
    val x9920 = Counter(min=Const(0), max=Const(512), step=Const(16), par=1).name("x9920").ctrl(x10174).srcCtx("CellsPar.scala:190:58") // CounterNew(Const(0),Const(512),Const(16),Const(1))
    val x9921 = Counter(min=Const(0), max=Const(1536), step=Const(16), par=1).name("x9921").ctrl(x10174).srcCtx("CellsPar.scala:190:33") // CounterNew(Const(0),Const(1536),Const(16),Const(1))
    val x9922 = CounterChain(List(x9921,x9920)).name("x9922").ctrl(x10174).srcCtx("CellsPar.scala:190:72") // CounterChainNew(List(x9921, x9920))
    val x10028 = LoopController(style=MetaPipe, level=OuterControl, cchain=x9922).name("x10028").ctrl(x10174).srcCtx("CellsPar.scala:190:72") // UnrolledForeach(List(b6102),x9922,Block(Const(())),List(List(b6389), List(b6390)),List(List(b6391), List(b6392)))
    val b6389 = CounterIter(x9921, Some(0)).name("b6389").ctrl(x10028) // b6389
    val b6391 = Const(true).name("b6391").ctrl(x10028) // b6391
    val b6390 = CounterIter(x9920, Some(0)).name("b6390").ctrl(x10028) // b6390
    val b6392 = Const(true).name("b6392").ctrl(x10028) // b6392
    val x9923_d0_b0 = SRAM(size=256, banking=Strided(banks=1, stride=16)).name("x9923_d0_b0").ctrl(x10028).srcCtx("CellsPar.scala:191:33:tileKernel") // x9923 = SRAMNew(ArrayBuffer(Const(16), Const(16)))
    isAccum(x9923_d0_b0) = false
    bufferDepthOf(x9923_d0_b0) = 2
    val x9926 = UnitController(style=SeqPipe, level=InnerControl).name("x9926").ctrl(x10028).srcCtx("CellsPar.scala:190:72") // UnitPipe(List(b6391, b6392, b6102),Block(Const(())))
    val x9924 = OpDef(op=FixAdd, inputs=List(b6389, Const(16))).name("x9924").ctrl(x9926).srcCtx("CellsPar.scala:192:36") // FixAdd(b6389,Const(16))
    val x9925 = OpDef(op=FixAdd, inputs=List(b6390, Const(16))).name("x9925").ctrl(x9926).srcCtx("CellsPar.scala:192:45") // FixAdd(b6390,Const(16))
    val x9927 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x9927").ctrl(x10028).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x9928 = CounterChain(List(x9927)).name("x9928").ctrl(x10028).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x9927))
    val x9957 = LoopController(style=StreamPipe, level=OuterControl, cchain=x9928).name("x9957").ctrl(x10028).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b6391, b6392, b6102),x9928,Block(Const(())),List(List(b6399)),List(List(b6400)))
    val b6399 = CounterIter(x9927, Some(0)).name("b6399").ctrl(x9957) // b6399
    val b6400 = Const(true).name("b6400").ctrl(x9957) // b6400
    val b10257 = StreamOut(field="offset").name("b10257").ctrl(x9957).srcCtx("CellsPar.scala:192:20") // x9929 = StreamOutNew(BurstCmdBus)
    isAccum(b10257) = false
    bufferDepthOf(b10257) = 1
    val b10258 = StreamOut(field="size").name("b10258").ctrl(x9957).srcCtx("CellsPar.scala:192:20") // x9929 = StreamOutNew(BurstCmdBus)
    isAccum(b10258) = false
    bufferDepthOf(b10258) = 1
    val x9930 = StreamIn(field="data").name("x9930").ctrl(x9957).srcCtx("CellsPar.scala:192:20") // x9930 = StreamInNew(BurstDataBus())
    isAccum(x9930) = false
    bufferDepthOf(x9930) = 1
    val x9945 = UnitController(style=SeqPipe, level=InnerControl).name("x9945").ctrl(x9957).srcCtx("CellsPar.scala:192:20") // UnitPipe(List(b6400, b6391, b6392, b6102),Block(x9944))
    val x9931 = OpDef(op=FixAdd, inputs=List(b6389, b6399)).name("x9931").ctrl(x9945).srcCtx("CellsPar.scala:192:20") // FixAdd(b6389,b6399)
    val x9932 = x9931 // FixConvert(x9931,TRUE,_32,_0) (Same Type. No op)
    val x9933 = OpDef(op=FixSla, inputs=List(x9932, Const(9))).name("x9933").ctrl(x9945).srcCtx("CellsPar.scala:192:20") // FixLsh(x9932,Const(9))
    val x9934 = b6390 // FixConvert(b6390,TRUE,_32,_0) (Same Type. No op)
    val x9935 = OpDef(op=FixAdd, inputs=List(x9933, x9934)).name("x9935").ctrl(x9945).srcCtx("CellsPar.scala:192:20") // FixAdd(x9933,x9934)
    val x9936 = OpDef(op=FixSla, inputs=List(x9935, Const(2))).name("x9936").ctrl(x9945).srcCtx("CellsPar.scala:192:20") // FixLsh(x9935,Const(2))
    val x9937 = x9936 // FixConvert(x9936,TRUE,_64,_0)
    val x9938 = DramAddress(x9583).name("x9938").ctrl(x9945).srcCtx("CellsPar.scala:192:20") // GetDRAMAddress(x9583)
    val x9940_x9939 = OpDef(op=FixAdd, inputs=List(x9937, x9938)).name("x9940_x9939").ctrl(x9945).srcCtx("CellsPar.scala:192:20") // FixAdd(x9937,x9938)
    // x9940 = SimpleStruct(ArrayBuffer((offset,x9939), (size,Const(64)), (isLoad,Const(true))))
    val x9941 = OpDef(op=BitAnd, inputs=List(b6400, b6391)).name("x9941").ctrl(x9945).srcCtx("UnrollingBase.scala:28:66") // And(b6400,b6391)
    val x9942 = OpDef(op=BitAnd, inputs=List(b6392, b6102)).name("x9942").ctrl(x9945).srcCtx("UnrollingBase.scala:28:66") // And(b6392,b6102)
    val x9943 = OpDef(op=BitAnd, inputs=List(x9941, x9942)).name("x9943").ctrl(x9945).srcCtx("UnrollingBase.scala:28:66") // And(x9941,x9942)
    val x9944_b10259_b10257 = WriteMem(b10257, x9940_x9939).name("x9944_b10259_b10257").ctrl(x9945).srcCtx("CellsPar.scala:192:20") // StreamWrite(x9929,x9940,x9943)
    val x9944_b10260_b10258 = WriteMem(b10258, Const(64)).name("x9944_b10260_b10258").ctrl(x9945).srcCtx("CellsPar.scala:192:20") // StreamWrite(x9929,x9940,x9943)
    val x9946 = FringeDenseLoad(dram=List(x9583), cmdStream=List(b10257, b10258), dataStream=List(x9930)).name("x9946").ctrl(x9957).srcCtx("CellsPar.scala:192:20") // FringeDenseLoad(x9583,x9929,x9930)
    val x9947 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x9947").ctrl(x9957).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x9948 = CounterChain(List(x9947)).name("x9948").ctrl(x9957).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x9947))
    val x9956 = LoopController(style=InnerPipe, level=InnerControl, cchain=x9948).name("x9956").ctrl(x9957).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b6400, b6391, b6392, b6102),x9948,Block(Const(())),List(List(b6421)),List(List(b6422)))
    val b6421 = CounterIter(x9947, None).name("b6421").ctrl(x9956) // b6421
    val b6422 = Const(true).name("b6422").ctrl(x9956) // b6422
    val x9949 = OpDef(op=BitAnd, inputs=List(b6422, b6400)).name("x9949").ctrl(x9956).srcCtx("UnrollingBase.scala:28:66") // And(b6422,b6400)
    val x9950 = OpDef(op=BitAnd, inputs=List(b6391, b6392)).name("x9950").ctrl(x9956).srcCtx("UnrollingBase.scala:28:66") // And(b6391,b6392)
    val x9951 = OpDef(op=BitAnd, inputs=List(x9949, x9950)).name("x9951").ctrl(x9956).srcCtx("UnrollingBase.scala:28:66") // And(x9949,x9950)
    val x9952 = OpDef(op=BitAnd, inputs=List(x9951, b6102)).name("x9952").ctrl(x9956).srcCtx("UnrollingBase.scala:28:66") // And(x9951,b6102)
    val x9953_x9953 = ReadMem(x9930).name("x9953_x9953").ctrl(x9956).srcCtx("CellsPar.scala:192:20") // ParStreamRead(x9930,List(x9952))
    val x9954_x9954 = x9953_x9953 // x9954 = VectorApply(x9953,0)
    val x9955 = StoreBanks(List(x9923_d0_b0), List(b6399, b6421), x9954_x9954).name("x9955").ctrl(x9956).srcCtx("CellsPar.scala:192:20") // ParSRAMStore(x9923,List(List(b6399, b6421)),List(x9954),List(x9952))
    val x9958 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x9958").ctrl(x10028).srcCtx("CellsPar.scala:193:34") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x9959 = CounterChain(List(x9958)).name("x9959").ctrl(x10028).srcCtx("CellsPar.scala:193:46") // CounterChainNew(List(x9958))
    val x10027 = LoopController(style=MetaPipe, level=OuterControl, cchain=x9959).name("x10027").ctrl(x10028).srcCtx("CellsPar.scala:193:46") // UnrolledForeach(List(b6391, b6392, b6102),x9959,Block(Const(())),List(List(b6434)),List(List(b6435)))
    val b6434 = CounterIter(x9958, Some(0)).name("b6434").ctrl(x10027) // b6434
    val b6435 = Const(true).name("b6435").ctrl(x10027) // b6435
    val x9960 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x9960").ctrl(x10027).srcCtx("CellsPar.scala:194:28") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x9961 = CounterChain(List(x9960)).name("x9961").ctrl(x10027).srcCtx("CellsPar.scala:194:42") // CounterChainNew(List(x9960))
    val x10026 = LoopController(style=MetaPipe, level=OuterControl, cchain=x9961).name("x10026").ctrl(x10027).srcCtx("CellsPar.scala:194:42") // UnrolledForeach(List(b6435, b6391, b6392, b6102),x9961,Block(Const(())),List(List(b6438)),List(List(b6439)))
    val b6438 = CounterIter(x9960, Some(0)).name("b6438").ctrl(x10026) // b6438
    val b6439 = Const(true).name("b6439").ctrl(x10026) // b6439
    val x9962_d0 = Reg(init=Some(0.0)).name("x9962_d0").ctrl(x10026).srcCtx("CellsPar.scala:195:34:prod") // x9962 = RegNew(Const(0))
    isAccum(x9962_d0) = false
    bufferDepthOf(x9962_d0) = 2
    val x9962_d1 = Reg(init=Some(0.0)).name("x9962_d1").ctrl(x10026).srcCtx("CellsPar.scala:195:34:prod") // x9962 = RegNew(Const(0))
    isAccum(x9962_d1) = true
    bufferDepthOf(x9962_d1) = 1
    val x9963 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x9963").ctrl(x10026).srcCtx("CellsPar.scala:195:43") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x9964 = CounterChain(List(x9963)).name("x9964").ctrl(x10026).srcCtx("CellsPar.scala:207:15") // CounterChainNew(List(x9963))
    val x9990 = LoopController(style=InnerPipe, level=InnerControl, cchain=x9964).name("x9990").ctrl(x10026).srcCtx("CellsPar.scala:207:15") // UnrolledReduce(List(b6439, b6435, b6391, b6392, b6102),x9964,x9962,Block((x9962) => Const(())),List(List(b6443)),List(List(b6444)))
    val b6443 = CounterIter(x9963, None).name("b6443").ctrl(x9990) // b6443
    val b6444 = Const(true).name("b6444").ctrl(x9990) // b6444
    val x9965 = OpDef(op=FixAdd, inputs=List(b6389, b6443)).name("x9965").ctrl(x9990).srcCtx("CellsPar.scala:196:42:reduce_size_offset") // FixAdd(b6389,b6443)
    val x9966 = OpDef(op=BitAnd, inputs=List(b6444, b6439)).name("x9966").ctrl(x9990).srcCtx("UnrollingBase.scala:28:66") // And(b6444,b6439)
    val x9967 = OpDef(op=BitAnd, inputs=List(b6435, b6391)).name("x9967").ctrl(x9990).srcCtx("UnrollingBase.scala:28:66") // And(b6435,b6391)
    val x9968 = OpDef(op=BitAnd, inputs=List(b6392, b6102)).name("x9968").ctrl(x9990).srcCtx("UnrollingBase.scala:28:66") // And(b6392,b6102)
    val x9969 = OpDef(op=BitAnd, inputs=List(x9966, x9967)).name("x9969").ctrl(x9990).srcCtx("UnrollingBase.scala:28:66") // And(x9966,x9967)
    val x9970 = OpDef(op=BitAnd, inputs=List(x9969, x9968)).name("x9970").ctrl(x9990).srcCtx("UnrollingBase.scala:28:66") // And(x9969,x9968)
    val x9971 = LoadBanks(List(x9923_d0_b0), List(b6443, b6438)).name("x9971").ctrl(x9990).srcCtx("CellsPar.scala:203:34:tk") // ParSRAMLoad(x9923,List(List(b6443, b6438)),List(x9970))
    val x9972 = x9971 // x9972 = VectorApply(x9971,0)
    val x9973 = LoadBanks(List(x9629_d1_b0), List(b6434, x9965)).name("x9973").ctrl(x9990).srcCtx("CellsPar.scala:204:28") // ParSRAMLoad(x9629,List(List(b6434, x9965)),List(x9970))
    val x9974 = x9973 // x9974 = VectorApply(x9973,0)
    val x9975 = OpDef(op=FixMul, inputs=List(x9974, x9972)).name("x9975").ctrl(x9990).srcCtx("CellsPar.scala:204:52:tempA") // FixMul(x9974,x9972)
    val x9976 = OpDef(op=FixSub, inputs=List(x9965, Const(512))).name("x9976").ctrl(x9990).srcCtx("CellsPar.scala:205:51") // FixSub(x9965,Const(512))
    val x9977 = LoadBanks(List(x9630_d2_b0), List(b6434, x9976)).name("x9977").ctrl(x9990).srcCtx("CellsPar.scala:205:28") // ParSRAMLoad(x9630,List(List(b6434, x9976)),List(x9970))
    val x9978 = x9977 // x9978 = VectorApply(x9977,0)
    val x9979 = OpDef(op=FixMul, inputs=List(x9978, x9972)).name("x9979").ctrl(x9990).srcCtx("CellsPar.scala:205:67:tempB") // FixMul(x9978,x9972)
    val x9980 = OpDef(op=FixLt, inputs=List(x9965, Const(512))).name("x9980").ctrl(x9990).srcCtx("CellsPar.scala:206:38") // FixLt(x9965,Const(512))
    val x9981 = OpDef(op=MuxOp, inputs=List(x9980, x9975, x9979)).name("x9981").ctrl(x9990).srcCtx("CellsPar.scala:206:18") // Mux(x9980,x9975,x9979)
    val x9982 = ReadMem(x9962_d1).name("x9982").ctrl(x9990).srcCtx("CellsPar.scala:207:15") // RegRead(x9962)
    val x9983 = OpDef(op=FixEql, inputs=List(b6443, Const(0))).name("x9983").ctrl(x9990).srcCtx("CellsPar.scala:207:15") // FixEql(b6443,Const(0))
    val x9984 = ReduceAccumOp(op=FixAdd, input=x9981, accum=x9982).name("x9984").ctrl(x9990).srcCtx("CellsPar.scala:207:17") // FixAdd(x9981,x9982)
    val x9985 = OpDef(op=BitAnd, inputs=List(b6439, b6435)).name("x9985").ctrl(x9990).srcCtx("UnrollingBase.scala:28:66") // And(b6439,b6435)
    val x9986 = OpDef(op=BitAnd, inputs=List(b6391, b6392)).name("x9986").ctrl(x9990).srcCtx("UnrollingBase.scala:28:66") // And(b6391,b6392)
    val x9987 = OpDef(op=BitAnd, inputs=List(x9985, x9986)).name("x9987").ctrl(x9990).srcCtx("UnrollingBase.scala:28:66") // And(x9985,x9986)
    val x9988 = OpDef(op=BitAnd, inputs=List(x9987, b6102)).name("x9988").ctrl(x9990).srcCtx("UnrollingBase.scala:28:66") // And(x9987,b6102)
    val x9989_x9962_d0 = WriteMem(x9962_d0, x9984).name("x9989_x9962_d0").ctrl(x9990).srcCtx("CellsPar.scala:207:15") // RegWrite(x9962,x9984,x9988)
    val x9989_x9962_d1 = WriteMem(x9962_d1, x9984).name("x9989_x9962_d1").ctrl(x9990).srcCtx("CellsPar.scala:207:15") // RegWrite(x9962,x9984,x9988)
    val x10025 = UnitController(style=SeqPipe, level=InnerControl).name("x10025").ctrl(x10026).srcCtx("CellsPar.scala:194:42") // UnitPipe(List(b6439, b6435, b6391, b6392, b6102),Block(Const(())))
    val x9991 = OpDef(op=FixAdd, inputs=List(b6390, b6438)).name("x9991").ctrl(x10025).srcCtx("CellsPar.scala:209:31:colOffset") // FixAdd(b6390,b6438)
    val x9992 = ReadMem(x9962_d0).name("x9992").ctrl(x10025).srcCtx("CellsPar.scala:210:28") // RegRead(x9962)
    val x9993 = OpDef(op=FixEql, inputs=List(b6389, Const(0))).name("x9993").ctrl(x10025).srcCtx("CellsPar.scala:210:42") // FixEql(b6389,Const(0))
    val x9994 = OpDef(op=FixAdd, inputs=List(x9991, Const(1024))).name("x9994").ctrl(x10025).srcCtx("CellsPar.scala:210:66") // FixAdd(x9991,Const(1024))
    val x9995 = OpDef(op=BitAnd, inputs=List(b6439, b6435)).name("x9995").ctrl(x10025).srcCtx("UnrollingBase.scala:28:66") // And(b6439,b6435)
    val x9996 = OpDef(op=BitAnd, inputs=List(b6391, b6392)).name("x9996").ctrl(x10025).srcCtx("UnrollingBase.scala:28:66") // And(b6391,b6392)
    val x9997 = OpDef(op=BitAnd, inputs=List(x9995, x9996)).name("x9997").ctrl(x10025).srcCtx("UnrollingBase.scala:28:66") // And(x9995,x9996)
    val x9998 = OpDef(op=BitAnd, inputs=List(x9997, b6102)).name("x9998").ctrl(x10025).srcCtx("UnrollingBase.scala:28:66") // And(x9997,b6102)
    val x9999 = LoadBanks(List(x9636_d1_b0), List(Const(0), x9994)).name("x9999").ctrl(x10025).srcCtx("CellsPar.scala:210:52") // SRAMLoad(x9636,ArrayBuffer(Const(1), Const(2048)),List(Const(0), x9994),Const(0),x9998)
    val x10000 = LoadBanks(List(x9634_d1_b0), List(b6434, x9991)).name("x10000").ctrl(x10025).srcCtx("CellsPar.scala:210:91") // SRAMLoad(x9634,ArrayBuffer(Const(1), Const(512)),List(b6434, x9991),Const(0),x9998)
    val x10001 = OpDef(op=MuxOp, inputs=List(x9993, x9999, x10000)).name("x10001").ctrl(x10025).srcCtx("CellsPar.scala:210:39") // Mux(x9993,x9999,x10000)
    val x10002 = OpDef(op=FixAdd, inputs=List(x9992, x10001)).name("x10002").ctrl(x10025).srcCtx("CellsPar.scala:210:34:ele") // FixAdd(x9992,x10001)
    val x10003 = OpDef(op=FixLeq, inputs=List(Const(1520), b6389)).name("x10003").ctrl(x10025).srcCtx("CellsPar.scala:218:46") // FixLeq(Const(1520),b6389)
    // x10004 = FixConvert(Const(1),TRUE,_8,_24) x.tp=FixPt[TRUE,_32,_0] {
    val x10004_int1 = OpDef(op=BitAnd, inputs=List(Const(1), Const("01111111111111111111111111111111"))).name("x10004_int1").ctrl(x10025).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(1),TRUE,_8,_24)
    val x10004_int2 = OpDef(op=FixSla, inputs=List(x10004_int1, Const(24))).name("x10004_int2").ctrl(x10025).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(1),TRUE,_8,_24)
    val x10004_frac1 = OpDef(op=BitAnd, inputs=List(Const(1), Const("00000000000000000000000000000000"))).name("x10004_frac1").ctrl(x10025).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(1),TRUE,_8,_24)
    val x10004_frac2 = OpDef(op=FixSla, inputs=List(x10004_frac1, Const(24))).name("x10004_frac2").ctrl(x10025).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(1),TRUE,_8,_24)
    val x10004 = OpDef(op=BitOr, inputs=List(x10004_int2, x10004_frac2)).name("x10004").ctrl(x10025).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(1),TRUE,_8,_24)
    // }
    val x10005 = OpDef(op=FixAdd, inputs=List(x10002, x10004)).name("x10005").ctrl(x10025).srcCtx("CellsPar.scala:218:87") // FixAdd(x10002,x10004)
    val x10006 = OpDef(op=FixSra, inputs=List(x10005, Const(1))).name("x10006").ctrl(x10025).srcCtx("CellsPar.scala:29:22") // FixRsh(x10005,Const(1))
    val x10007 = x10006 // FixConvert(x10006,TRUE,_8,_24) (Same Type. No op)
    val x10008 = OpDef(op=FixAbs, inputs=List(x10007)).name("x10008").ctrl(x10025).srcCtx("CellsPar.scala:37:19:absp") // FixAbs(x10007)
    val x10009 = OpDef(op=FixLt, inputs=List(Const(2.5), x10008)).name("x10009").ctrl(x10025).srcCtx("CellsPar.scala:51:23:cond1") // FixLt(Const(2.5),x10008)
    val x10010 = OpDef(op=FixLt, inputs=List(Const(0.5), x10008)).name("x10010").ctrl(x10025).srcCtx("CellsPar.scala:54:35") // FixLt(Const(0.5),x10008)
    val x10011 = OpDef(op=FixLeq, inputs=List(x10008, Const(2.5))).name("x10011").ctrl(x10025).srcCtx("CellsPar.scala:54:52") // FixLeq(x10008,Const(2.5))
    val x10012 = OpDef(op=BitAnd, inputs=List(x10010, x10011)).name("x10012").ctrl(x10025).srcCtx("CellsPar.scala:54:43:cond2") // And(x10010,x10011)
    val x10013 = OpDef(op=FixSra, inputs=List(x10008, Const(2))).name("x10013").ctrl(x10025).srcCtx("CellsPar.scala:56:21:div4") // FixRsh(x10008,Const(2))
    val x10014 = OpDef(op=FixAdd, inputs=List(x10013, Const(0.375))).name("x10014").ctrl(x10025).srcCtx("CellsPar.scala:57:27:div4Offset") // FixAdd(x10013,Const(0.375))
    val x10015 = OpDef(op=MuxOp, inputs=List(x10012, x10014, x10008)).name("x10015").ctrl(x10025).srcCtx("CellsPar.scala:58:20:body2") // Mux(x10012,x10014,x10008)
    val x10016 = OpDef(op=MuxOp, inputs=List(x10009, Const(1.0), x10015)).name("x10016").ctrl(x10025).srcCtx("CellsPar.scala:60:20:absre") // Mux(x10009,Const(1),x10015)
    val x10017 = OpDef(op=FixNeg, inputs=List(x10016)).name("x10017").ctrl(x10025).srcCtx("CellsPar.scala:62:29:mabre") // FixNeg(x10016)
    val x10018 = OpDef(op=FixLt, inputs=List(x10007, Const(0.0))).name("x10018").ctrl(x10025).srcCtx("CellsPar.scala:63:22") // FixLt(x10007,Const(0))
    val x10019 = OpDef(op=MuxOp, inputs=List(x10018, x10017, x10016)).name("x10019").ctrl(x10025).srcCtx("CellsPar.scala:63:17:re") // Mux(x10018,x10017,x10016)
    val x10020 = x10019 // FixConvert(x10019,TRUE,_8,_24) (Same Type. No op)
    val x10021 = OpDef(op=FixAdd, inputs=List(x10020, Const(1.0))).name("x10021").ctrl(x10025).srcCtx("CellsPar.scala:29:33") // FixAdd(x10020,Const(1))
    val x10022 = OpDef(op=FixSra, inputs=List(x10021, Const(1))).name("x10022").ctrl(x10025).srcCtx("CellsPar.scala:29:44") // FixRsh(x10021,Const(1))
    val x10023 = OpDef(op=MuxOp, inputs=List(x10003, x10022, x10002)).name("x10023").ctrl(x10025).srcCtx("CellsPar.scala:218:43") // Mux(x10003,x10022,x10002)
    val x10024 = StoreBanks(List(x9634_d0_b0, x9634_d1_b0), List(b6434, x9991), x10023).name("x10024").ctrl(x10025).srcCtx("CellsPar.scala:218:38") // SRAMStore(x9634,ArrayBuffer(Const(1), Const(512)),List(b6434, x9991),Const(0),x10023,x9998)
    val x10029 = Counter(min=Const(0), max=Const(512), step=Const(16), par=1).name("x10029").ctrl(x10174).srcCtx("CellsPar.scala:190:58") // CounterNew(Const(0),Const(512),Const(16),Const(1))
    val x10030 = Counter(min=Const(0), max=Const(1536), step=Const(16), par=1).name("x10030").ctrl(x10174).srcCtx("CellsPar.scala:190:33") // CounterNew(Const(0),Const(1536),Const(16),Const(1))
    val x10031 = CounterChain(List(x10030,x10029)).name("x10031").ctrl(x10174).srcCtx("CellsPar.scala:190:72") // CounterChainNew(List(x10030, x10029))
    val x10137 = LoopController(style=MetaPipe, level=OuterControl, cchain=x10031).name("x10137").ctrl(x10174).srcCtx("CellsPar.scala:190:72") // UnrolledForeach(List(b6102),x10031,Block(Const(())),List(List(b6512), List(b6513)),List(List(b6514), List(b6515)))
    val b6512 = CounterIter(x10030, Some(0)).name("b6512").ctrl(x10137) // b6512
    val b6514 = Const(true).name("b6514").ctrl(x10137) // b6514
    val b6513 = CounterIter(x10029, Some(0)).name("b6513").ctrl(x10137) // b6513
    val b6515 = Const(true).name("b6515").ctrl(x10137) // b6515
    val x10032_d0_b0 = SRAM(size=256, banking=Strided(banks=1, stride=16)).name("x10032_d0_b0").ctrl(x10137).srcCtx("CellsPar.scala:191:33:tileKernel") // x10032 = SRAMNew(ArrayBuffer(Const(16), Const(16)))
    isAccum(x10032_d0_b0) = false
    bufferDepthOf(x10032_d0_b0) = 2
    val x10035 = UnitController(style=SeqPipe, level=InnerControl).name("x10035").ctrl(x10137).srcCtx("CellsPar.scala:190:72") // UnitPipe(List(b6514, b6515, b6102),Block(Const(())))
    val x10033 = OpDef(op=FixAdd, inputs=List(b6512, Const(16))).name("x10033").ctrl(x10035).srcCtx("CellsPar.scala:192:36") // FixAdd(b6512,Const(16))
    val x10034 = OpDef(op=FixAdd, inputs=List(b6513, Const(16))).name("x10034").ctrl(x10035).srcCtx("CellsPar.scala:192:45") // FixAdd(b6513,Const(16))
    val x10036 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x10036").ctrl(x10137).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x10037 = CounterChain(List(x10036)).name("x10037").ctrl(x10137).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x10036))
    val x10066 = LoopController(style=StreamPipe, level=OuterControl, cchain=x10037).name("x10066").ctrl(x10137).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b6514, b6515, b6102),x10037,Block(Const(())),List(List(b6522)),List(List(b6523)))
    val b6522 = CounterIter(x10036, Some(0)).name("b6522").ctrl(x10066) // b6522
    val b6523 = Const(true).name("b6523").ctrl(x10066) // b6523
    val b10261 = StreamOut(field="offset").name("b10261").ctrl(x10066).srcCtx("CellsPar.scala:192:20") // x10038 = StreamOutNew(BurstCmdBus)
    isAccum(b10261) = false
    bufferDepthOf(b10261) = 1
    val b10262 = StreamOut(field="size").name("b10262").ctrl(x10066).srcCtx("CellsPar.scala:192:20") // x10038 = StreamOutNew(BurstCmdBus)
    isAccum(b10262) = false
    bufferDepthOf(b10262) = 1
    val x10039 = StreamIn(field="data").name("x10039").ctrl(x10066).srcCtx("CellsPar.scala:192:20") // x10039 = StreamInNew(BurstDataBus())
    isAccum(x10039) = false
    bufferDepthOf(x10039) = 1
    val x10054 = UnitController(style=SeqPipe, level=InnerControl).name("x10054").ctrl(x10066).srcCtx("CellsPar.scala:192:20") // UnitPipe(List(b6523, b6514, b6515, b6102),Block(x10053))
    val x10040 = OpDef(op=FixAdd, inputs=List(b6512, b6522)).name("x10040").ctrl(x10054).srcCtx("CellsPar.scala:192:20") // FixAdd(b6512,b6522)
    val x10041 = x10040 // FixConvert(x10040,TRUE,_32,_0) (Same Type. No op)
    val x10042 = OpDef(op=FixSla, inputs=List(x10041, Const(9))).name("x10042").ctrl(x10054).srcCtx("CellsPar.scala:192:20") // FixLsh(x10041,Const(9))
    val x10043 = b6513 // FixConvert(b6513,TRUE,_32,_0) (Same Type. No op)
    val x10044 = OpDef(op=FixAdd, inputs=List(x10042, x10043)).name("x10044").ctrl(x10054).srcCtx("CellsPar.scala:192:20") // FixAdd(x10042,x10043)
    val x10045 = OpDef(op=FixSla, inputs=List(x10044, Const(2))).name("x10045").ctrl(x10054).srcCtx("CellsPar.scala:192:20") // FixLsh(x10044,Const(2))
    val x10046 = x10045 // FixConvert(x10045,TRUE,_64,_0)
    val x10047 = DramAddress(x9584).name("x10047").ctrl(x10054).srcCtx("CellsPar.scala:192:20") // GetDRAMAddress(x9584)
    val x10049_x10048 = OpDef(op=FixAdd, inputs=List(x10046, x10047)).name("x10049_x10048").ctrl(x10054).srcCtx("CellsPar.scala:192:20") // FixAdd(x10046,x10047)
    // x10049 = SimpleStruct(ArrayBuffer((offset,x10048), (size,Const(64)), (isLoad,Const(true))))
    val x10050 = OpDef(op=BitAnd, inputs=List(b6523, b6514)).name("x10050").ctrl(x10054).srcCtx("UnrollingBase.scala:28:66") // And(b6523,b6514)
    val x10051 = OpDef(op=BitAnd, inputs=List(b6515, b6102)).name("x10051").ctrl(x10054).srcCtx("UnrollingBase.scala:28:66") // And(b6515,b6102)
    val x10052 = OpDef(op=BitAnd, inputs=List(x10050, x10051)).name("x10052").ctrl(x10054).srcCtx("UnrollingBase.scala:28:66") // And(x10050,x10051)
    val x10053_b10263_b10261 = WriteMem(b10261, x10049_x10048).name("x10053_b10263_b10261").ctrl(x10054).srcCtx("CellsPar.scala:192:20") // StreamWrite(x10038,x10049,x10052)
    val x10053_b10264_b10262 = WriteMem(b10262, Const(64)).name("x10053_b10264_b10262").ctrl(x10054).srcCtx("CellsPar.scala:192:20") // StreamWrite(x10038,x10049,x10052)
    val x10055 = FringeDenseLoad(dram=List(x9584), cmdStream=List(b10261, b10262), dataStream=List(x10039)).name("x10055").ctrl(x10066).srcCtx("CellsPar.scala:192:20") // FringeDenseLoad(x9584,x10038,x10039)
    val x10056 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x10056").ctrl(x10066).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x10057 = CounterChain(List(x10056)).name("x10057").ctrl(x10066).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x10056))
    val x10065 = LoopController(style=InnerPipe, level=InnerControl, cchain=x10057).name("x10065").ctrl(x10066).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b6523, b6514, b6515, b6102),x10057,Block(Const(())),List(List(b6544)),List(List(b6545)))
    val b6544 = CounterIter(x10056, None).name("b6544").ctrl(x10065) // b6544
    val b6545 = Const(true).name("b6545").ctrl(x10065) // b6545
    val x10058 = OpDef(op=BitAnd, inputs=List(b6545, b6523)).name("x10058").ctrl(x10065).srcCtx("UnrollingBase.scala:28:66") // And(b6545,b6523)
    val x10059 = OpDef(op=BitAnd, inputs=List(b6514, b6515)).name("x10059").ctrl(x10065).srcCtx("UnrollingBase.scala:28:66") // And(b6514,b6515)
    val x10060 = OpDef(op=BitAnd, inputs=List(x10058, x10059)).name("x10060").ctrl(x10065).srcCtx("UnrollingBase.scala:28:66") // And(x10058,x10059)
    val x10061 = OpDef(op=BitAnd, inputs=List(x10060, b6102)).name("x10061").ctrl(x10065).srcCtx("UnrollingBase.scala:28:66") // And(x10060,b6102)
    val x10062_x10062 = ReadMem(x10039).name("x10062_x10062").ctrl(x10065).srcCtx("CellsPar.scala:192:20") // ParStreamRead(x10039,List(x10061))
    val x10063_x10063 = x10062_x10062 // x10063 = VectorApply(x10062,0)
    val x10064 = StoreBanks(List(x10032_d0_b0), List(b6522, b6544), x10063_x10063).name("x10064").ctrl(x10065).srcCtx("CellsPar.scala:192:20") // ParSRAMStore(x10032,List(List(b6522, b6544)),List(x10063),List(x10061))
    val x10067 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x10067").ctrl(x10137).srcCtx("CellsPar.scala:193:34") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x10068 = CounterChain(List(x10067)).name("x10068").ctrl(x10137).srcCtx("CellsPar.scala:193:46") // CounterChainNew(List(x10067))
    val x10136 = LoopController(style=MetaPipe, level=OuterControl, cchain=x10068).name("x10136").ctrl(x10137).srcCtx("CellsPar.scala:193:46") // UnrolledForeach(List(b6514, b6515, b6102),x10068,Block(Const(())),List(List(b6557)),List(List(b6558)))
    val b6557 = CounterIter(x10067, Some(0)).name("b6557").ctrl(x10136) // b6557
    val b6558 = Const(true).name("b6558").ctrl(x10136) // b6558
    val x10069 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x10069").ctrl(x10136).srcCtx("CellsPar.scala:194:28") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x10070 = CounterChain(List(x10069)).name("x10070").ctrl(x10136).srcCtx("CellsPar.scala:194:42") // CounterChainNew(List(x10069))
    val x10135 = LoopController(style=MetaPipe, level=OuterControl, cchain=x10070).name("x10135").ctrl(x10136).srcCtx("CellsPar.scala:194:42") // UnrolledForeach(List(b6558, b6514, b6515, b6102),x10070,Block(Const(())),List(List(b6561)),List(List(b6562)))
    val b6561 = CounterIter(x10069, Some(0)).name("b6561").ctrl(x10135) // b6561
    val b6562 = Const(true).name("b6562").ctrl(x10135) // b6562
    val x10071_d0 = Reg(init=Some(0.0)).name("x10071_d0").ctrl(x10135).srcCtx("CellsPar.scala:195:34:prod") // x10071 = RegNew(Const(0))
    isAccum(x10071_d0) = false
    bufferDepthOf(x10071_d0) = 2
    val x10071_d1 = Reg(init=Some(0.0)).name("x10071_d1").ctrl(x10135).srcCtx("CellsPar.scala:195:34:prod") // x10071 = RegNew(Const(0))
    isAccum(x10071_d1) = true
    bufferDepthOf(x10071_d1) = 1
    val x10072 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x10072").ctrl(x10135).srcCtx("CellsPar.scala:195:43") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x10073 = CounterChain(List(x10072)).name("x10073").ctrl(x10135).srcCtx("CellsPar.scala:207:15") // CounterChainNew(List(x10072))
    val x10099 = LoopController(style=InnerPipe, level=InnerControl, cchain=x10073).name("x10099").ctrl(x10135).srcCtx("CellsPar.scala:207:15") // UnrolledReduce(List(b6562, b6558, b6514, b6515, b6102),x10073,x10071,Block((x10071) => Const(())),List(List(b6566)),List(List(b6567)))
    val b6566 = CounterIter(x10072, None).name("b6566").ctrl(x10099) // b6566
    val b6567 = Const(true).name("b6567").ctrl(x10099) // b6567
    val x10074 = OpDef(op=FixAdd, inputs=List(b6512, b6566)).name("x10074").ctrl(x10099).srcCtx("CellsPar.scala:196:42:reduce_size_offset") // FixAdd(b6512,b6566)
    val x10075 = OpDef(op=BitAnd, inputs=List(b6567, b6562)).name("x10075").ctrl(x10099).srcCtx("UnrollingBase.scala:28:66") // And(b6567,b6562)
    val x10076 = OpDef(op=BitAnd, inputs=List(b6558, b6514)).name("x10076").ctrl(x10099).srcCtx("UnrollingBase.scala:28:66") // And(b6558,b6514)
    val x10077 = OpDef(op=BitAnd, inputs=List(b6515, b6102)).name("x10077").ctrl(x10099).srcCtx("UnrollingBase.scala:28:66") // And(b6515,b6102)
    val x10078 = OpDef(op=BitAnd, inputs=List(x10075, x10076)).name("x10078").ctrl(x10099).srcCtx("UnrollingBase.scala:28:66") // And(x10075,x10076)
    val x10079 = OpDef(op=BitAnd, inputs=List(x10078, x10077)).name("x10079").ctrl(x10099).srcCtx("UnrollingBase.scala:28:66") // And(x10078,x10077)
    val x10080 = LoadBanks(List(x10032_d0_b0), List(b6566, b6561)).name("x10080").ctrl(x10099).srcCtx("CellsPar.scala:203:34:tk") // ParSRAMLoad(x10032,List(List(b6566, b6561)),List(x10079))
    val x10081 = x10080 // x10081 = VectorApply(x10080,0)
    val x10082 = LoadBanks(List(x9629_d0_b0), List(b6557, x10074)).name("x10082").ctrl(x10099).srcCtx("CellsPar.scala:204:28") // ParSRAMLoad(x9629,List(List(b6557, x10074)),List(x10079))
    val x10083 = x10082 // x10083 = VectorApply(x10082,0)
    val x10084 = OpDef(op=FixMul, inputs=List(x10083, x10081)).name("x10084").ctrl(x10099).srcCtx("CellsPar.scala:204:52:tempA") // FixMul(x10083,x10081)
    val x10085 = OpDef(op=FixSub, inputs=List(x10074, Const(512))).name("x10085").ctrl(x10099).srcCtx("CellsPar.scala:205:51") // FixSub(x10074,Const(512))
    val x10086 = LoadBanks(List(x9630_d1_b0), List(b6557, x10085)).name("x10086").ctrl(x10099).srcCtx("CellsPar.scala:205:28") // ParSRAMLoad(x9630,List(List(b6557, x10085)),List(x10079))
    val x10087 = x10086 // x10087 = VectorApply(x10086,0)
    val x10088 = OpDef(op=FixMul, inputs=List(x10087, x10081)).name("x10088").ctrl(x10099).srcCtx("CellsPar.scala:205:67:tempB") // FixMul(x10087,x10081)
    val x10089 = OpDef(op=FixLt, inputs=List(x10074, Const(512))).name("x10089").ctrl(x10099).srcCtx("CellsPar.scala:206:38") // FixLt(x10074,Const(512))
    val x10090 = OpDef(op=MuxOp, inputs=List(x10089, x10084, x10088)).name("x10090").ctrl(x10099).srcCtx("CellsPar.scala:206:18") // Mux(x10089,x10084,x10088)
    val x10091 = ReadMem(x10071_d1).name("x10091").ctrl(x10099).srcCtx("CellsPar.scala:207:15") // RegRead(x10071)
    val x10092 = OpDef(op=FixEql, inputs=List(b6566, Const(0))).name("x10092").ctrl(x10099).srcCtx("CellsPar.scala:207:15") // FixEql(b6566,Const(0))
    val x10093 = ReduceAccumOp(op=FixAdd, input=x10090, accum=x10091).name("x10093").ctrl(x10099).srcCtx("CellsPar.scala:207:17") // FixAdd(x10090,x10091)
    val x10094 = OpDef(op=BitAnd, inputs=List(b6562, b6558)).name("x10094").ctrl(x10099).srcCtx("UnrollingBase.scala:28:66") // And(b6562,b6558)
    val x10095 = OpDef(op=BitAnd, inputs=List(b6514, b6515)).name("x10095").ctrl(x10099).srcCtx("UnrollingBase.scala:28:66") // And(b6514,b6515)
    val x10096 = OpDef(op=BitAnd, inputs=List(x10094, x10095)).name("x10096").ctrl(x10099).srcCtx("UnrollingBase.scala:28:66") // And(x10094,x10095)
    val x10097 = OpDef(op=BitAnd, inputs=List(x10096, b6102)).name("x10097").ctrl(x10099).srcCtx("UnrollingBase.scala:28:66") // And(x10096,b6102)
    val x10098_x10071_d0 = WriteMem(x10071_d0, x10093).name("x10098_x10071_d0").ctrl(x10099).srcCtx("CellsPar.scala:207:15") // RegWrite(x10071,x10093,x10097)
    val x10098_x10071_d1 = WriteMem(x10071_d1, x10093).name("x10098_x10071_d1").ctrl(x10099).srcCtx("CellsPar.scala:207:15") // RegWrite(x10071,x10093,x10097)
    val x10134 = UnitController(style=SeqPipe, level=InnerControl).name("x10134").ctrl(x10135).srcCtx("CellsPar.scala:194:42") // UnitPipe(List(b6562, b6558, b6514, b6515, b6102),Block(Const(())))
    val x10100 = OpDef(op=FixAdd, inputs=List(b6513, b6561)).name("x10100").ctrl(x10134).srcCtx("CellsPar.scala:209:31:colOffset") // FixAdd(b6513,b6561)
    val x10101 = ReadMem(x10071_d0).name("x10101").ctrl(x10134).srcCtx("CellsPar.scala:210:28") // RegRead(x10071)
    val x10102 = OpDef(op=FixEql, inputs=List(b6512, Const(0))).name("x10102").ctrl(x10134).srcCtx("CellsPar.scala:210:42") // FixEql(b6512,Const(0))
    val x10103 = OpDef(op=FixAdd, inputs=List(x10100, Const(1536))).name("x10103").ctrl(x10134).srcCtx("CellsPar.scala:210:66") // FixAdd(x10100,Const(1536))
    val x10104 = OpDef(op=BitAnd, inputs=List(b6562, b6558)).name("x10104").ctrl(x10134).srcCtx("UnrollingBase.scala:28:66") // And(b6562,b6558)
    val x10105 = OpDef(op=BitAnd, inputs=List(b6514, b6515)).name("x10105").ctrl(x10134).srcCtx("UnrollingBase.scala:28:66") // And(b6514,b6515)
    val x10106 = OpDef(op=BitAnd, inputs=List(x10104, x10105)).name("x10106").ctrl(x10134).srcCtx("UnrollingBase.scala:28:66") // And(x10104,x10105)
    val x10107 = OpDef(op=BitAnd, inputs=List(x10106, b6102)).name("x10107").ctrl(x10134).srcCtx("UnrollingBase.scala:28:66") // And(x10106,b6102)
    val x10108 = LoadBanks(List(x9636_d0_b0), List(Const(0), x10103)).name("x10108").ctrl(x10134).srcCtx("CellsPar.scala:210:52") // SRAMLoad(x9636,ArrayBuffer(Const(1), Const(2048)),List(Const(0), x10103),Const(0),x10107)
    val x10109 = LoadBanks(List(x9635_d1_b0), List(b6557, x10100)).name("x10109").ctrl(x10134).srcCtx("CellsPar.scala:210:91") // SRAMLoad(x9635,ArrayBuffer(Const(1), Const(512)),List(b6557, x10100),Const(0),x10107)
    val x10110 = OpDef(op=MuxOp, inputs=List(x10102, x10108, x10109)).name("x10110").ctrl(x10134).srcCtx("CellsPar.scala:210:39") // Mux(x10102,x10108,x10109)
    val x10111 = OpDef(op=FixAdd, inputs=List(x10101, x10110)).name("x10111").ctrl(x10134).srcCtx("CellsPar.scala:210:34:ele") // FixAdd(x10101,x10110)
    val x10112 = OpDef(op=FixLeq, inputs=List(Const(1520), b6512)).name("x10112").ctrl(x10134).srcCtx("CellsPar.scala:218:46") // FixLeq(Const(1520),b6512)
    // x10113 = FixConvert(Const(0),TRUE,_8,_24) x.tp=FixPt[TRUE,_32,_0] {
    val x10113_int1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("01111111111111111111111111111111"))).name("x10113_int1").ctrl(x10134).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x10113_int2 = OpDef(op=FixSla, inputs=List(x10113_int1, Const(24))).name("x10113_int2").ctrl(x10134).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x10113_frac1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("00000000000000000000000000000000"))).name("x10113_frac1").ctrl(x10134).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x10113_frac2 = OpDef(op=FixSla, inputs=List(x10113_frac1, Const(24))).name("x10113_frac2").ctrl(x10134).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x10113 = OpDef(op=BitOr, inputs=List(x10113_int2, x10113_frac2)).name("x10113").ctrl(x10134).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    // }
    val x10114 = OpDef(op=FixAdd, inputs=List(x10111, x10113)).name("x10114").ctrl(x10134).srcCtx("CellsPar.scala:218:87") // FixAdd(x10111,x10113)
    val x10115 = OpDef(op=FixSra, inputs=List(x10114, Const(1))).name("x10115").ctrl(x10134).srcCtx("CellsPar.scala:29:22") // FixRsh(x10114,Const(1))
    val x10116 = x10115 // FixConvert(x10115,TRUE,_8,_24) (Same Type. No op)
    val x10117 = OpDef(op=FixAbs, inputs=List(x10116)).name("x10117").ctrl(x10134).srcCtx("CellsPar.scala:37:19:absp") // FixAbs(x10116)
    val x10118 = OpDef(op=FixLt, inputs=List(Const(2.5), x10117)).name("x10118").ctrl(x10134).srcCtx("CellsPar.scala:51:23:cond1") // FixLt(Const(2.5),x10117)
    val x10119 = OpDef(op=FixLt, inputs=List(Const(0.5), x10117)).name("x10119").ctrl(x10134).srcCtx("CellsPar.scala:54:35") // FixLt(Const(0.5),x10117)
    val x10120 = OpDef(op=FixLeq, inputs=List(x10117, Const(2.5))).name("x10120").ctrl(x10134).srcCtx("CellsPar.scala:54:52") // FixLeq(x10117,Const(2.5))
    val x10121 = OpDef(op=BitAnd, inputs=List(x10119, x10120)).name("x10121").ctrl(x10134).srcCtx("CellsPar.scala:54:43:cond2") // And(x10119,x10120)
    val x10122 = OpDef(op=FixSra, inputs=List(x10117, Const(2))).name("x10122").ctrl(x10134).srcCtx("CellsPar.scala:56:21:div4") // FixRsh(x10117,Const(2))
    val x10123 = OpDef(op=FixAdd, inputs=List(x10122, Const(0.375))).name("x10123").ctrl(x10134).srcCtx("CellsPar.scala:57:27:div4Offset") // FixAdd(x10122,Const(0.375))
    val x10124 = OpDef(op=MuxOp, inputs=List(x10121, x10123, x10117)).name("x10124").ctrl(x10134).srcCtx("CellsPar.scala:58:20:body2") // Mux(x10121,x10123,x10117)
    val x10125 = OpDef(op=MuxOp, inputs=List(x10118, Const(1.0), x10124)).name("x10125").ctrl(x10134).srcCtx("CellsPar.scala:60:20:absre") // Mux(x10118,Const(1),x10124)
    val x10126 = OpDef(op=FixNeg, inputs=List(x10125)).name("x10126").ctrl(x10134).srcCtx("CellsPar.scala:62:29:mabre") // FixNeg(x10125)
    val x10127 = OpDef(op=FixLt, inputs=List(x10116, Const(0.0))).name("x10127").ctrl(x10134).srcCtx("CellsPar.scala:63:22") // FixLt(x10116,Const(0))
    val x10128 = OpDef(op=MuxOp, inputs=List(x10127, x10126, x10125)).name("x10128").ctrl(x10134).srcCtx("CellsPar.scala:63:17:re") // Mux(x10127,x10126,x10125)
    val x10129 = x10128 // FixConvert(x10128,TRUE,_8,_24) (Same Type. No op)
    val x10130 = OpDef(op=FixAdd, inputs=List(x10129, Const(1.0))).name("x10130").ctrl(x10134).srcCtx("CellsPar.scala:29:33") // FixAdd(x10129,Const(1))
    val x10131 = OpDef(op=FixSra, inputs=List(x10130, Const(1))).name("x10131").ctrl(x10134).srcCtx("CellsPar.scala:29:44") // FixRsh(x10130,Const(1))
    val x10132 = OpDef(op=MuxOp, inputs=List(x10112, x10131, x10111)).name("x10132").ctrl(x10134).srcCtx("CellsPar.scala:218:43") // Mux(x10112,x10131,x10111)
    val x10133 = StoreBanks(List(x9635_d0_b0, x9635_d1_b0), List(b6557, x10100), x10132).name("x10133").ctrl(x10134).srcCtx("CellsPar.scala:218:38") // SRAMStore(x9635,ArrayBuffer(Const(1), Const(512)),List(b6557, x10100),Const(0),x10132,x10107)
    val x10138 = Counter(min=Const(0), max=Const(512), step=Const(1), par=1).name("x10138").ctrl(x10174).srcCtx("CellsPar.scala:243:50") // CounterNew(Const(0),Const(512),Const(1),Const(1))
    val x10139 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x10139").ctrl(x10174).srcCtx("CellsPar.scala:243:27") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x10140 = CounterChain(List(x10139,x10138)).name("x10140").ctrl(x10174).srcCtx("CellsPar.scala:243:62") // CounterChainNew(List(x10139, x10138))
    val x10173 = LoopController(style=InnerPipe, level=InnerControl, cchain=x10140).name("x10173").ctrl(x10174).srcCtx("CellsPar.scala:243:62") // UnrolledForeach(List(b6102),x10140,Block(Const(())),List(List(b6636), List(b6637)),List(List(b6638), List(b6639)))
    val b6636 = CounterIter(x10139, Some(0)).name("b6636").ctrl(x10173) // b6636
    val b6638 = Const(true).name("b6638").ctrl(x10173) // b6638
    val b6637 = CounterIter(x10138, None).name("b6637").ctrl(x10173) // b6637
    val b6639 = Const(true).name("b6639").ctrl(x10173) // b6639
    val x10141 = OpDef(op=BitAnd, inputs=List(b6638, b6639)).name("x10141").ctrl(x10173).srcCtx("UnrollingBase.scala:28:66") // And(b6638,b6639)
    val x10142 = OpDef(op=BitAnd, inputs=List(x10141, b6102)).name("x10142").ctrl(x10173).srcCtx("UnrollingBase.scala:28:66") // And(x10141,b6102)
    val x10143 = LoadBanks(List(x9631_d1_b0), List(b6636, b6637)).name("x10143").ctrl(x10173).srcCtx("CellsPar.scala:244:22") // ParSRAMLoad(x9631,List(List(b6636, b6637)),List(x10142))
    val x10144 = x10143 // x10144 = VectorApply(x10143,0)
    val x10145 = LoadBanks(List(x9634_d0_b0), List(b6636, b6637)).name("x10145").ctrl(x10173).srcCtx("CellsPar.scala:244:34") // ParSRAMLoad(x9634,List(List(b6636, b6637)),List(x10142))
    val x10146 = x10145 // x10146 = VectorApply(x10145,0)
    val x10147 = OpDef(op=FixMul, inputs=List(x10144, x10146)).name("x10147").ctrl(x10173).srcCtx("CellsPar.scala:244:28") // FixMul(x10144,x10146)
    val x10148 = LoadBanks(List(x9632_d0_b0), List(b6636, b6637)).name("x10148").ctrl(x10173).srcCtx("CellsPar.scala:244:46") // ParSRAMLoad(x9632,List(List(b6636, b6637)),List(x10142))
    val x10149 = x10148 // x10149 = VectorApply(x10148,0)
    val x10150 = LoadBanks(List(x9633_d0_b0), List(b6636, b6637)).name("x10150").ctrl(x10173).srcCtx("CellsPar.scala:244:59") // ParSRAMLoad(x9633,List(List(b6636, b6637)),List(x10142))
    val x10151 = x10150 // x10151 = VectorApply(x10150,0)
    val x10152 = OpDef(op=FixMul, inputs=List(x10149, x10151)).name("x10152").ctrl(x10173).srcCtx("CellsPar.scala:244:52") // FixMul(x10149,x10151)
    val x10153 = OpDef(op=FixAdd, inputs=List(x10147, x10152)).name("x10153").ctrl(x10173).srcCtx("CellsPar.scala:244:40:new_c") // FixAdd(x10147,x10152)
    val x10154 = x10153 // FixConvert(x10153,TRUE,_8,_24) (Same Type. No op)
    val x10155 = OpDef(op=FixAbs, inputs=List(x10154)).name("x10155").ctrl(x10173).srcCtx("CellsPar.scala:37:19:absp") // FixAbs(x10154)
    val x10156 = OpDef(op=FixLt, inputs=List(Const(2.5), x10155)).name("x10156").ctrl(x10173).srcCtx("CellsPar.scala:51:23:cond1") // FixLt(Const(2.5),x10155)
    val x10157 = OpDef(op=FixLt, inputs=List(Const(0.5), x10155)).name("x10157").ctrl(x10173).srcCtx("CellsPar.scala:54:35") // FixLt(Const(0.5),x10155)
    val x10158 = OpDef(op=FixLeq, inputs=List(x10155, Const(2.5))).name("x10158").ctrl(x10173).srcCtx("CellsPar.scala:54:52") // FixLeq(x10155,Const(2.5))
    val x10159 = OpDef(op=BitAnd, inputs=List(x10157, x10158)).name("x10159").ctrl(x10173).srcCtx("CellsPar.scala:54:43:cond2") // And(x10157,x10158)
    val x10160 = OpDef(op=FixSra, inputs=List(x10155, Const(2))).name("x10160").ctrl(x10173).srcCtx("CellsPar.scala:56:21:div4") // FixRsh(x10155,Const(2))
    val x10161 = OpDef(op=FixAdd, inputs=List(x10160, Const(0.375))).name("x10161").ctrl(x10173).srcCtx("CellsPar.scala:57:27:div4Offset") // FixAdd(x10160,Const(0.375))
    val x10162 = OpDef(op=MuxOp, inputs=List(x10159, x10161, x10155)).name("x10162").ctrl(x10173).srcCtx("CellsPar.scala:58:20:body2") // Mux(x10159,x10161,x10155)
    val x10163 = OpDef(op=MuxOp, inputs=List(x10156, Const(1.0), x10162)).name("x10163").ctrl(x10173).srcCtx("CellsPar.scala:60:20:absre") // Mux(x10156,Const(1),x10162)
    val x10164 = OpDef(op=FixNeg, inputs=List(x10163)).name("x10164").ctrl(x10173).srcCtx("CellsPar.scala:62:29:mabre") // FixNeg(x10163)
    val x10165 = OpDef(op=FixLt, inputs=List(x10154, Const(0.0))).name("x10165").ctrl(x10173).srcCtx("CellsPar.scala:63:22") // FixLt(x10154,Const(0))
    val x10166 = OpDef(op=MuxOp, inputs=List(x10165, x10164, x10163)).name("x10166").ctrl(x10173).srcCtx("CellsPar.scala:63:17:re") // Mux(x10165,x10164,x10163)
    val x10167 = x10166 // FixConvert(x10166,TRUE,_8,_24) (Same Type. No op)
    val x10168 = LoadBanks(List(x9635_d0_b0), List(b6636, b6637)).name("x10168").ctrl(x10173).srcCtx("CellsPar.scala:245:45") // ParSRAMLoad(x9635,List(List(b6636, b6637)),List(x10142))
    val x10169 = x10168 // x10169 = VectorApply(x10168,0)
    val x10170 = OpDef(op=FixMul, inputs=List(x10167, x10169)).name("x10170").ctrl(x10173).srcCtx("CellsPar.scala:245:39") // FixMul(x10167,x10169)
    val x10171 = StoreBanks(List(x9630_d0_b0, x9630_d1_b0, x9630_d2_b0, x9630_d3_b0, x9630_d4_b0), List(b6636, b6637), x10170).name("x10171").ctrl(x10173).srcCtx("CellsPar.scala:245:16") // ParSRAMStore(x9630,List(List(b6636, b6637)),List(x10170),List(x10142))
    val x10172 = StoreBanks(List(x9631_d0_b0, x9631_d1_b0), List(b6636, b6637), x10153).name("x10172").ctrl(x10173).srcCtx("CellsPar.scala:246:16") // ParSRAMStore(x9631,List(List(b6636, b6637)),List(x10153),List(x10142))
    val x10175 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x10175").ctrl(x10231).srcCtx("CellsPar.scala:175:46") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x10176 = CounterChain(List(x10175)).name("x10176").ctrl(x10231).srcCtx("CellsPar.scala:175:46") // CounterChainNew(List(x10175))
    val x10202 = LoopController(style=StreamPipe, level=OuterControl, cchain=x10176).name("x10202").ctrl(x10231).srcCtx("CellsPar.scala:175:46") // UnrolledForeach(List(Const(true)),x10176,Block(Const(())),List(List(b6676)),List(List(b6677)))
    val b6676 = CounterIter(x10175, Some(0)).name("b6676").ctrl(x10202) // b6676
    def split1 = {
    val b6677 = Const(true).name("b6677").ctrl(x10202) // b6677
    val b10265 = StreamOut(field="offset").name("b10265").ctrl(x10202).srcCtx("CellsPar.scala:175:46") // x10177 = StreamOutNew(BurstCmdBus)
    isAccum(b10265) = false
    bufferDepthOf(b10265) = 1
    val b10266 = StreamOut(field="size").name("b10266").ctrl(x10202).srcCtx("CellsPar.scala:175:46") // x10177 = StreamOutNew(BurstCmdBus)
    isAccum(b10266) = false
    bufferDepthOf(b10266) = 1
    val x10178 = StreamOut(field="data").name("x10178").ctrl(x10202).srcCtx("CellsPar.scala:175:46") // x10178 = StreamOutNew(BurstFullDataBus())
    isAccum(x10178) = false
    bufferDepthOf(x10178) = 1
    val x10179 = StreamIn(field="ack").name("x10179").ctrl(x10202).srcCtx("CellsPar.scala:175:46") // x10179 = StreamInNew(BurstAckBus)
    isAccum(x10179) = false
    bufferDepthOf(x10179) = 1
    val x10190 = UnitController(style=SeqPipe, level=InnerControl).name("x10190").ctrl(x10202).srcCtx("CellsPar.scala:175:46") // UnitPipe(List(b6677),Block(x10189))
    val x10180 = b6676 // FixConvert(b6676,TRUE,_32,_0) (Same Type. No op)
    val x10181 = OpDef(op=FixSla, inputs=List(x10180, Const(9))).name("x10181").ctrl(x10190).srcCtx("CellsPar.scala:175:46") // FixLsh(x10180,Const(9))
    val x10182 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x10183 = OpDef(op=FixAdd, inputs=List(x10181, x10182)).name("x10183").ctrl(x10190).srcCtx("CellsPar.scala:175:46") // FixAdd(x10181,x10182)
    val x10184 = OpDef(op=FixSla, inputs=List(x10183, Const(2))).name("x10184").ctrl(x10190).srcCtx("CellsPar.scala:175:46") // FixLsh(x10183,Const(2))
    val x10185 = x10184 // FixConvert(x10184,TRUE,_64,_0)
    val x10186 = DramAddress(x9553).name("x10186").ctrl(x10190).srcCtx("CellsPar.scala:175:46") // GetDRAMAddress(x9553)
    val x10188_x10187 = OpDef(op=FixAdd, inputs=List(x10185, x10186)).name("x10188_x10187").ctrl(x10190).srcCtx("CellsPar.scala:175:46") // FixAdd(x10185,x10186)
    // x10188 = SimpleStruct(ArrayBuffer((offset,x10187), (size,Const(2048)), (isLoad,Const(false))))
    val x10189_b10267_b10265 = WriteMem(b10265, x10188_x10187).name("x10189_b10267_b10265").ctrl(x10190).srcCtx("CellsPar.scala:175:46") // StreamWrite(x10177,x10188,b6677)
    val x10189_b10268_b10266 = WriteMem(b10266, Const(2048)).name("x10189_b10268_b10266").ctrl(x10190).srcCtx("CellsPar.scala:175:46") // StreamWrite(x10177,x10188,b6677)
    val x10191 = Counter(min=Const(0), max=Const(512), step=Const(1), par=1).name("x10191").ctrl(x10202).srcCtx("CellsPar.scala:175:46") // CounterNew(Const(0),Const(512),Const(1),Const(1))
    val x10192 = CounterChain(List(x10191)).name("x10192").ctrl(x10202).srcCtx("CellsPar.scala:175:46") // CounterChainNew(List(x10191))
    val x10198 = LoopController(style=InnerPipe, level=InnerControl, cchain=x10192).name("x10198").ctrl(x10202).srcCtx("CellsPar.scala:175:46") // UnrolledForeach(List(b6677),x10192,Block(Const(())),List(List(b6694)),List(List(b6695)))
    val b6694 = CounterIter(x10191, None).name("b6694").ctrl(x10198) // b6694
    val b6695 = Const(true).name("b6695").ctrl(x10198) // b6695
    val x10193 = OpDef(op=BitAnd, inputs=List(b6695, b6677)).name("x10193").ctrl(x10198).srcCtx("UnrollingBase.scala:28:66") // And(b6695,b6677)
    val x10194 = LoadBanks(List(x9631_d0_b0), List(b6676, b6694)).name("x10194").ctrl(x10198).srcCtx("CellsPar.scala:175:46") // ParSRAMLoad(x9631,List(List(b6676, b6694)),List(x10193))
    val x10196_x10195 = x10194 // x10195 = VectorApply(x10194,0)
    // x10196 = SimpleStruct(ArrayBuffer((_1,x10195), (_2,Const(true))))
    val x10197_x10197_x10178 = WriteMem(x10178, x10196_x10195).name("x10197_x10197_x10178").ctrl(x10198).srcCtx("CellsPar.scala:175:46") // ParStreamWrite(x10178,List(x10196),List(x10193))
    val x10199 = FringeDenseStore(dram=List(x9553), cmdStream=List(b10265, b10266), dataStream=List(x10178), ackStream=List(x10179)).name("x10199").ctrl(x10202).srcCtx("CellsPar.scala:175:46") // FringeDenseStore(x9553,x10177,x10178,x10179)
    val x10201 = UnitController(style=SeqPipe, level=InnerControl).name("x10201").ctrl(x10202).srcCtx("CellsPar.scala:175:46") // UnitPipe(List(b6677),Block(Const(())))
    val x10200_x10200 = ReadMem(x10179).name("x10200_x10200").ctrl(x10201).srcCtx("CellsPar.scala:175:46") // StreamRead(x10179,b6677)
    val x10203 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x10203").ctrl(x10231).srcCtx("CellsPar.scala:176:46") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x10204 = CounterChain(List(x10203)).name("x10204").ctrl(x10231).srcCtx("CellsPar.scala:176:46") // CounterChainNew(List(x10203))
    val x10230 = LoopController(style=StreamPipe, level=OuterControl, cchain=x10204).name("x10230").ctrl(x10231).srcCtx("CellsPar.scala:176:46") // UnrolledForeach(List(Const(true)),x10204,Block(Const(())),List(List(b6708)),List(List(b6709)))
    val b6708 = CounterIter(x10203, Some(0)).name("b6708").ctrl(x10230) // b6708
    val b6709 = Const(true).name("b6709").ctrl(x10230) // b6709
    val b10269 = StreamOut(field="offset").name("b10269").ctrl(x10230).srcCtx("CellsPar.scala:176:46") // x10205 = StreamOutNew(BurstCmdBus)
    isAccum(b10269) = false
    bufferDepthOf(b10269) = 1
    val b10270 = StreamOut(field="size").name("b10270").ctrl(x10230).srcCtx("CellsPar.scala:176:46") // x10205 = StreamOutNew(BurstCmdBus)
    isAccum(b10270) = false
    bufferDepthOf(b10270) = 1
    val x10206 = StreamOut(field="data").name("x10206").ctrl(x10230).srcCtx("CellsPar.scala:176:46") // x10206 = StreamOutNew(BurstFullDataBus())
    isAccum(x10206) = false
    bufferDepthOf(x10206) = 1
    val x10207 = StreamIn(field="ack").name("x10207").ctrl(x10230).srcCtx("CellsPar.scala:176:46") // x10207 = StreamInNew(BurstAckBus)
    isAccum(x10207) = false
    bufferDepthOf(x10207) = 1
    val x10218 = UnitController(style=SeqPipe, level=InnerControl).name("x10218").ctrl(x10230).srcCtx("CellsPar.scala:176:46") // UnitPipe(List(b6709),Block(x10217))
    val x10208 = b6708 // FixConvert(b6708,TRUE,_32,_0) (Same Type. No op)
    val x10209 = OpDef(op=FixSla, inputs=List(x10208, Const(9))).name("x10209").ctrl(x10218).srcCtx("CellsPar.scala:176:46") // FixLsh(x10208,Const(9))
    val x10210 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x10211 = OpDef(op=FixAdd, inputs=List(x10209, x10210)).name("x10211").ctrl(x10218).srcCtx("CellsPar.scala:176:46") // FixAdd(x10209,x10210)
    val x10212 = OpDef(op=FixSla, inputs=List(x10211, Const(2))).name("x10212").ctrl(x10218).srcCtx("CellsPar.scala:176:46") // FixLsh(x10211,Const(2))
    val x10213 = x10212 // FixConvert(x10212,TRUE,_64,_0)
    val x10214 = DramAddress(x9555).name("x10214").ctrl(x10218).srcCtx("CellsPar.scala:176:46") // GetDRAMAddress(x9555)
    val x10216_x10215 = OpDef(op=FixAdd, inputs=List(x10213, x10214)).name("x10216_x10215").ctrl(x10218).srcCtx("CellsPar.scala:176:46") // FixAdd(x10213,x10214)
    // x10216 = SimpleStruct(ArrayBuffer((offset,x10215), (size,Const(2048)), (isLoad,Const(false))))
    val x10217_b10271_b10269 = WriteMem(b10269, x10216_x10215).name("x10217_b10271_b10269").ctrl(x10218).srcCtx("CellsPar.scala:176:46") // StreamWrite(x10205,x10216,b6709)
    val x10217_b10272_b10270 = WriteMem(b10270, Const(2048)).name("x10217_b10272_b10270").ctrl(x10218).srcCtx("CellsPar.scala:176:46") // StreamWrite(x10205,x10216,b6709)
    val x10219 = Counter(min=Const(0), max=Const(512), step=Const(1), par=1).name("x10219").ctrl(x10230).srcCtx("CellsPar.scala:176:46") // CounterNew(Const(0),Const(512),Const(1),Const(1))
    val x10220 = CounterChain(List(x10219)).name("x10220").ctrl(x10230).srcCtx("CellsPar.scala:176:46") // CounterChainNew(List(x10219))
    val x10226 = LoopController(style=InnerPipe, level=InnerControl, cchain=x10220).name("x10226").ctrl(x10230).srcCtx("CellsPar.scala:176:46") // UnrolledForeach(List(b6709),x10220,Block(Const(())),List(List(b6726)),List(List(b6727)))
    val b6726 = CounterIter(x10219, None).name("b6726").ctrl(x10226) // b6726
    val b6727 = Const(true).name("b6727").ctrl(x10226) // b6727
    val x10221 = OpDef(op=BitAnd, inputs=List(b6727, b6709)).name("x10221").ctrl(x10226).srcCtx("UnrollingBase.scala:28:66") // And(b6727,b6709)
    val x10222 = LoadBanks(List(x9630_d0_b0), List(b6708, b6726)).name("x10222").ctrl(x10226).srcCtx("CellsPar.scala:176:46") // ParSRAMLoad(x9630,List(List(b6708, b6726)),List(x10221))
    val x10224_x10223 = x10222 // x10223 = VectorApply(x10222,0)
    // x10224 = SimpleStruct(ArrayBuffer((_1,x10223), (_2,Const(true))))
    val x10225_x10225_x10206 = WriteMem(x10206, x10224_x10223).name("x10225_x10225_x10206").ctrl(x10226).srcCtx("CellsPar.scala:176:46") // ParStreamWrite(x10206,List(x10224),List(x10221))
    val x10227 = FringeDenseStore(dram=List(x9555), cmdStream=List(b10269, b10270), dataStream=List(x10206), ackStream=List(x10207)).name("x10227").ctrl(x10230).srcCtx("CellsPar.scala:176:46") // FringeDenseStore(x9555,x10205,x10206,x10207)
    val x10229 = UnitController(style=SeqPipe, level=InnerControl).name("x10229").ctrl(x10230).srcCtx("CellsPar.scala:176:46") // UnitPipe(List(b6709),Block(Const(())))
    val x10228_x10228 = ReadMem(x10207).name("x10228_x10228").ctrl(x10229).srcCtx("CellsPar.scala:176:46") // StreamRead(x10207,b6709)
    }; split1
    
  }
}
