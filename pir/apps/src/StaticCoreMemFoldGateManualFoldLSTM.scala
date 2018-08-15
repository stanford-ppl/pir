import pir._
import pir.node._
import arch._
import prism.enums._

object StaticCoreMemFoldGateManualFoldLSTM extends PIRApp {
  def main(implicit design:PIRDesign) = {
    import design.pirmeta._
    val x5755 = withCtrl(design.top.topController) { DRAM(dims=List(Const(8), Const(128))).name("x5755").srcCtx("DataGenerator.scala:151:20:xInit") } // x5755 = DRAMNew(ArrayBuffer(Const(8), Const(128)),Const(0))
    val x5762 = withCtrl(design.top.topController) { DRAM(dims=List(Const(2), Const(128))).name("x5762").srcCtx("DataGenerator.scala:151:20:cInit") } // x5762 = DRAMNew(ArrayBuffer(Const(2), Const(128)),Const(0))
    val x5769 = withCtrl(design.top.topController) { DRAM(dims=List(Const(2), Const(128))).name("x5769").srcCtx("DataGenerator.scala:151:20:hInit") } // x5769 = DRAMNew(ArrayBuffer(Const(2), Const(128)),Const(0))
    val x5776 = withCtrl(design.top.topController) { DRAM(dims=List(Const(2), Const(4), Const(128))).name("x5776").srcCtx("DataGenerator.scala:163:20:bInit") } // x5776 = DRAMNew(ArrayBuffer(Const(2), Const(4), Const(128)),Const(0))
    val x5786 = withCtrl(design.top.topController) { DRAM(dims=List(Const(2), Const(128), Const(4), Const(128))).name("x5786").srcCtx("DataGenerator.scala:177:20:wxInit") } // x5786 = DRAMNew(ArrayBuffer(Const(2), Const(128), Const(4), Const(128)),Const(0))
    val x5799 = withCtrl(design.top.topController) { DRAM(dims=List(Const(2), Const(128), Const(4), Const(128))).name("x5799").srcCtx("DataGenerator.scala:177:20:whInit") } // x5799 = DRAMNew(ArrayBuffer(Const(2), Const(128), Const(4), Const(128)),Const(0))
    val x5812 = withCtrl(design.top.topController) { DRAM(dims=List(Const(8), Const(128))).name("x5812").srcCtx("IOs.scala:153:22:out") } // x5812 = DRAMNew(ArrayBuffer(Const(8), Const(128)),Const(0))
    val x6242 = withCtrl(design.top.topController) { UnitController(style=SeqPipe, level=OuterControl).name("x6242").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:15:11") } // Hwblock(Block(Const(())),false)
    val x5813_d0_b0 = withCtrl(x6242) { SRAM(size=1024, banking=Strided(banks=16, stride=1)).name("x5813_d0_b0").srcCtx("DataGenerator.scala:38:21:x") } // x5813 = SRAMNew(ArrayBuffer(Const(8), Const(128)))
    isAccum(x5813_d0_b0) = false
    bufferDepthOf(x5813_d0_b0) = 1
    staticDimsOf(x5813_d0_b0) = List(8, 128)
    val x5813_d1_b0 = withCtrl(x6242) { SRAM(size=1024, banking=Strided(banks=16, stride=1)).name("x5813_d1_b0").srcCtx("DataGenerator.scala:38:21:x") } // x5813 = SRAMNew(ArrayBuffer(Const(8), Const(128)))
    isAccum(x5813_d1_b0) = false
    bufferDepthOf(x5813_d1_b0) = 1
    staticDimsOf(x5813_d1_b0) = List(8, 128)
    val x5814 = withCtrl(x6242) { Counter(min=Const(0), max=Const(8), step=Const(1), par=1).name("x5814").srcCtx("DataGenerator.scala:39:8") } // CounterNew(Const(0),Const(8),Const(1),Const(1))
    val x5815 = withCtrl(x6242) { CounterChain(List(x5814)).name("x5815").srcCtx("DataGenerator.scala:39:8") } // CounterChainNew(List(x5814))
    val x5837 = withCtrl(x6242) { LoopController(style=StreamPipe, level=OuterControl, cchain=x5815).name("x5837").srcCtx("DataGenerator.scala:39:8") } // UnrolledForeach(List(Const(true)),x5815,Block(Const(())),List(List(b3250)),List(List(b3251)))
    val b3250 = withCtrl(x5837) { CounterIter(x5814, Some(0)).name("b3250") } // b3250
    val b3251 = withCtrl(x5837) { Const(true).name("b3251") } // b3251
    val b6253 = withCtrl(x5837) { StreamOut(field="offset").name("b6253").srcCtx("DataGenerator.scala:39:8") } // x5816 = StreamOutNew(BurstCmdBus)
    isAccum(b6253) = false
    bufferDepthOf(b6253) = 1
    val b6254 = withCtrl(x5837) { StreamOut(field="size").name("b6254").srcCtx("DataGenerator.scala:39:8") } // x5816 = StreamOutNew(BurstCmdBus)
    isAccum(b6254) = false
    bufferDepthOf(b6254) = 1
    val x5817 = withCtrl(x5837) { StreamIn(field="data").name("x5817").srcCtx("DataGenerator.scala:39:8") } // x5817 = StreamInNew(BurstDataBus())
    isAccum(x5817) = false
    bufferDepthOf(x5817) = 1
    val x5828 = withCtrl(x5837) { UnitController(style=SeqPipe, level=InnerControl).name("x5828").srcCtx("DataGenerator.scala:39:8") } // UnitPipe(List(b3251),Block(x5827))
    val x5818 = withCtrl(x5828) { b3250 } // FixConvert(b3250,TRUE,_32,_0) (Same Type. No op)
    val x5819 = withCtrl(x5828) { OpDef(op=FixSla, inputs=List(x5818, Const(7))).name("x5819").srcCtx("DataGenerator.scala:39:8") } // FixLsh(x5818,Const(7))
    val x5820 = withCtrl(x5828) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x5821 = withCtrl(x5828) { OpDef(op=FixAdd, inputs=List(x5819, x5820)).name("x5821").srcCtx("DataGenerator.scala:39:8") } // FixAdd(x5819,x5820)
    val x5822 = withCtrl(x5828) { OpDef(op=FixSla, inputs=List(x5821, Const(2))).name("x5822").srcCtx("DataGenerator.scala:39:8") } // FixLsh(x5821,Const(2))
    val x5823 = withCtrl(x5828) { x5822 } // FixConvert(x5822,TRUE,_64,_0)
    val x5824 = withCtrl(x5828) { DramAddress(x5755).name("x5824").srcCtx("DataGenerator.scala:39:8") } // GetDRAMAddress(x5755)
    val x5826_x5825 = withCtrl(x5828) { OpDef(op=FixAdd, inputs=List(x5823, x5824)).name("x5826_x5825").srcCtx("DataGenerator.scala:39:8") } // FixAdd(x5823,x5824)
    // x5826 = SimpleStruct(ArrayBuffer((offset,x5825), (size,Const(512)), (isLoad,Const(true))))
    val x5827_b6255_b6253 = withCtrl(x5828) { WriteMem(b6253, x5826_x5825).name("x5827_b6255_b6253").srcCtx("DataGenerator.scala:39:8") } // StreamWrite(x5816,x5826,b3251)
    val x5827_b6256_b6254 = withCtrl(x5828) { WriteMem(b6254, Const(512)).name("x5827_b6256_b6254").srcCtx("DataGenerator.scala:39:8") } // StreamWrite(x5816,x5826,b3251)
    val x5829 = withCtrl(x5837) { FringeDenseLoad(dram=List(x5755), cmdStream=List(b6253, b6254), dataStream=List(x5817)).name("x5829").srcCtx("DataGenerator.scala:39:8") } // FringeDenseLoad(x5755,x5816,x5817)
    val x5830 = withCtrl(x5837) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x5830").srcCtx("DataGenerator.scala:39:8") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x5831 = withCtrl(x5837) { CounterChain(List(x5830)).name("x5831").srcCtx("DataGenerator.scala:39:8") } // CounterChainNew(List(x5830))
    val x5836 = withCtrl(x5837) { LoopController(style=InnerPipe, level=InnerControl, cchain=x5831).name("x5836").srcCtx("DataGenerator.scala:39:8") } // UnrolledForeach(List(b3251),x5831,Block(Const(())),List(List(b3268)),List(List(b3269)))
    val b3268 = withCtrl(x5836) { CounterIter(x5830, None).name("b3268") } // b3268
    val b3269 = withCtrl(x5836) { Const(true).name("b3269") } // b3269
    val x5832 = withCtrl(x5836) { OpDef(op=BitAnd, inputs=List(b3269, b3251)).name("x5832").srcCtx("UnrollingBase.scala:28:66") } // And(b3269,b3251)
    val x5833_x5833 = withCtrl(x5836) { ReadMem(x5817).name("x5833_x5833").srcCtx("DataGenerator.scala:39:8") } // ParStreamRead(x5817,List(x5832))
    val x5834_x5834 = withCtrl(x5836) { x5833_x5833 } // VectorApply(x5833,0)
    val x5835 = withCtrl(x5836) { StoreBanks(List(List(x5813_d0_b0), List(x5813_d1_b0)), List(b3250, b3268), x5834_x5834).name("x5835").srcCtx("DataGenerator.scala:39:8") } // ParSRAMStore(x5813,List(List(b3250, b3268)),List(x5834),List(x5832))
    val x5838_d0_b0 = withCtrl(x6242) { SRAM(size=256, banking=Strided(banks=16, stride=1)).name("x5838_d0_b0").srcCtx("DataGenerator.scala:38:21:c") } // x5838 = SRAMNew(ArrayBuffer(Const(2), Const(128)))
    isAccum(x5838_d0_b0) = false
    bufferDepthOf(x5838_d0_b0) = 1
    staticDimsOf(x5838_d0_b0) = List(2, 128)
    val x5839 = withCtrl(x6242) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x5839").srcCtx("DataGenerator.scala:39:8") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x5840 = withCtrl(x6242) { CounterChain(List(x5839)).name("x5840").srcCtx("DataGenerator.scala:39:8") } // CounterChainNew(List(x5839))
    val x5862 = withCtrl(x6242) { LoopController(style=StreamPipe, level=OuterControl, cchain=x5840).name("x5862").srcCtx("DataGenerator.scala:39:8") } // UnrolledForeach(List(Const(true)),x5840,Block(Const(())),List(List(b3279)),List(List(b3280)))
    val b3279 = withCtrl(x5862) { CounterIter(x5839, Some(0)).name("b3279") } // b3279
    val b3280 = withCtrl(x5862) { Const(true).name("b3280") } // b3280
    val b6257 = withCtrl(x5862) { StreamOut(field="offset").name("b6257").srcCtx("DataGenerator.scala:39:8") } // x5841 = StreamOutNew(BurstCmdBus)
    isAccum(b6257) = false
    bufferDepthOf(b6257) = 1
    val b6258 = withCtrl(x5862) { StreamOut(field="size").name("b6258").srcCtx("DataGenerator.scala:39:8") } // x5841 = StreamOutNew(BurstCmdBus)
    isAccum(b6258) = false
    bufferDepthOf(b6258) = 1
    val x5842 = withCtrl(x5862) { StreamIn(field="data").name("x5842").srcCtx("DataGenerator.scala:39:8") } // x5842 = StreamInNew(BurstDataBus())
    isAccum(x5842) = false
    bufferDepthOf(x5842) = 1
    val x5853 = withCtrl(x5862) { UnitController(style=SeqPipe, level=InnerControl).name("x5853").srcCtx("DataGenerator.scala:39:8") } // UnitPipe(List(b3280),Block(x5852))
    val x5843 = withCtrl(x5853) { b3279 } // FixConvert(b3279,TRUE,_32,_0) (Same Type. No op)
    val x5844 = withCtrl(x5853) { OpDef(op=FixSla, inputs=List(x5843, Const(7))).name("x5844").srcCtx("DataGenerator.scala:39:8") } // FixLsh(x5843,Const(7))
    val x5845 = withCtrl(x5853) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x5846 = withCtrl(x5853) { OpDef(op=FixAdd, inputs=List(x5844, x5845)).name("x5846").srcCtx("DataGenerator.scala:39:8") } // FixAdd(x5844,x5845)
    val x5847 = withCtrl(x5853) { OpDef(op=FixSla, inputs=List(x5846, Const(2))).name("x5847").srcCtx("DataGenerator.scala:39:8") } // FixLsh(x5846,Const(2))
    val x5848 = withCtrl(x5853) { x5847 } // FixConvert(x5847,TRUE,_64,_0)
    val x5849 = withCtrl(x5853) { DramAddress(x5762).name("x5849").srcCtx("DataGenerator.scala:39:8") } // GetDRAMAddress(x5762)
    val x5851_x5850 = withCtrl(x5853) { OpDef(op=FixAdd, inputs=List(x5848, x5849)).name("x5851_x5850").srcCtx("DataGenerator.scala:39:8") } // FixAdd(x5848,x5849)
    // x5851 = SimpleStruct(ArrayBuffer((offset,x5850), (size,Const(512)), (isLoad,Const(true))))
    val x5852_b6259_b6257 = withCtrl(x5853) { WriteMem(b6257, x5851_x5850).name("x5852_b6259_b6257").srcCtx("DataGenerator.scala:39:8") } // StreamWrite(x5841,x5851,b3280)
    val x5852_b6260_b6258 = withCtrl(x5853) { WriteMem(b6258, Const(512)).name("x5852_b6260_b6258").srcCtx("DataGenerator.scala:39:8") } // StreamWrite(x5841,x5851,b3280)
    val x5854 = withCtrl(x5862) { FringeDenseLoad(dram=List(x5762), cmdStream=List(b6257, b6258), dataStream=List(x5842)).name("x5854").srcCtx("DataGenerator.scala:39:8") } // FringeDenseLoad(x5762,x5841,x5842)
    val x5855 = withCtrl(x5862) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x5855").srcCtx("DataGenerator.scala:39:8") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x5856 = withCtrl(x5862) { CounterChain(List(x5855)).name("x5856").srcCtx("DataGenerator.scala:39:8") } // CounterChainNew(List(x5855))
    val x5861 = withCtrl(x5862) { LoopController(style=InnerPipe, level=InnerControl, cchain=x5856).name("x5861").srcCtx("DataGenerator.scala:39:8") } // UnrolledForeach(List(b3280),x5856,Block(Const(())),List(List(b3297)),List(List(b3298)))
    val b3297 = withCtrl(x5861) { CounterIter(x5855, None).name("b3297") } // b3297
    val b3298 = withCtrl(x5861) { Const(true).name("b3298") } // b3298
    val x5857 = withCtrl(x5861) { OpDef(op=BitAnd, inputs=List(b3298, b3280)).name("x5857").srcCtx("UnrollingBase.scala:28:66") } // And(b3298,b3280)
    val x5858_x5858 = withCtrl(x5861) { ReadMem(x5842).name("x5858_x5858").srcCtx("DataGenerator.scala:39:8") } // ParStreamRead(x5842,List(x5857))
    val x5859_x5859 = withCtrl(x5861) { x5858_x5858 } // VectorApply(x5858,0)
    val x5860 = withCtrl(x5861) { StoreBanks(List(List(x5838_d0_b0)), List(b3279, b3297), x5859_x5859).name("x5860").srcCtx("DataGenerator.scala:39:8") } // ParSRAMStore(x5838,List(List(b3279, b3297)),List(x5859),List(x5857))
    val x5863_d0_b0 = withCtrl(x6242) { SRAM(size=256, banking=Strided(banks=16, stride=1)).name("x5863_d0_b0").srcCtx("DataGenerator.scala:38:21:h") } // x5863 = SRAMNew(ArrayBuffer(Const(2), Const(128)))
    isAccum(x5863_d0_b0) = false
    bufferDepthOf(x5863_d0_b0) = 1
    staticDimsOf(x5863_d0_b0) = List(2, 128)
    val x5863_d1_b0 = withCtrl(x6242) { SRAM(size=256, banking=Strided(banks=16, stride=1)).name("x5863_d1_b0").srcCtx("DataGenerator.scala:38:21:h") } // x5863 = SRAMNew(ArrayBuffer(Const(2), Const(128)))
    isAccum(x5863_d1_b0) = false
    bufferDepthOf(x5863_d1_b0) = 1
    staticDimsOf(x5863_d1_b0) = List(2, 128)
    val x5864 = withCtrl(x6242) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x5864").srcCtx("DataGenerator.scala:39:8") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x5865 = withCtrl(x6242) { CounterChain(List(x5864)).name("x5865").srcCtx("DataGenerator.scala:39:8") } // CounterChainNew(List(x5864))
    val x5887 = withCtrl(x6242) { LoopController(style=StreamPipe, level=OuterControl, cchain=x5865).name("x5887").srcCtx("DataGenerator.scala:39:8") } // UnrolledForeach(List(Const(true)),x5865,Block(Const(())),List(List(b3308)),List(List(b3309)))
    val b3308 = withCtrl(x5887) { CounterIter(x5864, Some(0)).name("b3308") } // b3308
    val b3309 = withCtrl(x5887) { Const(true).name("b3309") } // b3309
    val b6261 = withCtrl(x5887) { StreamOut(field="offset").name("b6261").srcCtx("DataGenerator.scala:39:8") } // x5866 = StreamOutNew(BurstCmdBus)
    isAccum(b6261) = false
    bufferDepthOf(b6261) = 1
    val b6262 = withCtrl(x5887) { StreamOut(field="size").name("b6262").srcCtx("DataGenerator.scala:39:8") } // x5866 = StreamOutNew(BurstCmdBus)
    isAccum(b6262) = false
    bufferDepthOf(b6262) = 1
    val x5867 = withCtrl(x5887) { StreamIn(field="data").name("x5867").srcCtx("DataGenerator.scala:39:8") } // x5867 = StreamInNew(BurstDataBus())
    isAccum(x5867) = false
    bufferDepthOf(x5867) = 1
    val x5878 = withCtrl(x5887) { UnitController(style=SeqPipe, level=InnerControl).name("x5878").srcCtx("DataGenerator.scala:39:8") } // UnitPipe(List(b3309),Block(x5877))
    val x5868 = withCtrl(x5878) { b3308 } // FixConvert(b3308,TRUE,_32,_0) (Same Type. No op)
    val x5869 = withCtrl(x5878) { OpDef(op=FixSla, inputs=List(x5868, Const(7))).name("x5869").srcCtx("DataGenerator.scala:39:8") } // FixLsh(x5868,Const(7))
    val x5870 = withCtrl(x5878) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x5871 = withCtrl(x5878) { OpDef(op=FixAdd, inputs=List(x5869, x5870)).name("x5871").srcCtx("DataGenerator.scala:39:8") } // FixAdd(x5869,x5870)
    val x5872 = withCtrl(x5878) { OpDef(op=FixSla, inputs=List(x5871, Const(2))).name("x5872").srcCtx("DataGenerator.scala:39:8") } // FixLsh(x5871,Const(2))
    val x5873 = withCtrl(x5878) { x5872 } // FixConvert(x5872,TRUE,_64,_0)
    val x5874 = withCtrl(x5878) { DramAddress(x5769).name("x5874").srcCtx("DataGenerator.scala:39:8") } // GetDRAMAddress(x5769)
    val x5876_x5875 = withCtrl(x5878) { OpDef(op=FixAdd, inputs=List(x5873, x5874)).name("x5876_x5875").srcCtx("DataGenerator.scala:39:8") } // FixAdd(x5873,x5874)
    // x5876 = SimpleStruct(ArrayBuffer((offset,x5875), (size,Const(512)), (isLoad,Const(true))))
    val x5877_b6263_b6261 = withCtrl(x5878) { WriteMem(b6261, x5876_x5875).name("x5877_b6263_b6261").srcCtx("DataGenerator.scala:39:8") } // StreamWrite(x5866,x5876,b3309)
    val x5877_b6264_b6262 = withCtrl(x5878) { WriteMem(b6262, Const(512)).name("x5877_b6264_b6262").srcCtx("DataGenerator.scala:39:8") } // StreamWrite(x5866,x5876,b3309)
    val x5879 = withCtrl(x5887) { FringeDenseLoad(dram=List(x5769), cmdStream=List(b6261, b6262), dataStream=List(x5867)).name("x5879").srcCtx("DataGenerator.scala:39:8") } // FringeDenseLoad(x5769,x5866,x5867)
    val x5880 = withCtrl(x5887) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x5880").srcCtx("DataGenerator.scala:39:8") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x5881 = withCtrl(x5887) { CounterChain(List(x5880)).name("x5881").srcCtx("DataGenerator.scala:39:8") } // CounterChainNew(List(x5880))
    val x5886 = withCtrl(x5887) { LoopController(style=InnerPipe, level=InnerControl, cchain=x5881).name("x5886").srcCtx("DataGenerator.scala:39:8") } // UnrolledForeach(List(b3309),x5881,Block(Const(())),List(List(b3326)),List(List(b3327)))
    val b3326 = withCtrl(x5886) { CounterIter(x5880, None).name("b3326") } // b3326
    val b3327 = withCtrl(x5886) { Const(true).name("b3327") } // b3327
    val x5882 = withCtrl(x5886) { OpDef(op=BitAnd, inputs=List(b3327, b3309)).name("x5882").srcCtx("UnrollingBase.scala:28:66") } // And(b3327,b3309)
    val x5883_x5883 = withCtrl(x5886) { ReadMem(x5867).name("x5883_x5883").srcCtx("DataGenerator.scala:39:8") } // ParStreamRead(x5867,List(x5882))
    val x5884_x5884 = withCtrl(x5886) { x5883_x5883 } // VectorApply(x5883,0)
    val x5885 = withCtrl(x5886) { StoreBanks(List(List(x5863_d0_b0), List(x5863_d1_b0)), List(b3308, b3326), x5884_x5884).name("x5885").srcCtx("DataGenerator.scala:39:8") } // ParSRAMStore(x5863,List(List(b3308, b3326)),List(x5884),List(x5882))
    val x5888_d0_b0 = withCtrl(x6242) { SRAM(size=32768, banking=Strided(banks=16, stride=1)).name("x5888_d0_b0").srcCtx("DataGenerator.scala:71:21:wx") } // x5888 = SRAMNew(ArrayBuffer(Const(2), Const(128), Const(4), Const(128)))
    isAccum(x5888_d0_b0) = false
    bufferDepthOf(x5888_d0_b0) = 1
    staticDimsOf(x5888_d0_b0) = List(2, 128, 4, 128)
    val x5888_d0_b1 = withCtrl(x6242) { SRAM(size=32768, banking=Strided(banks=16, stride=1)).name("x5888_d0_b1").srcCtx("DataGenerator.scala:71:21:wx") } // x5888 = SRAMNew(ArrayBuffer(Const(2), Const(128), Const(4), Const(128)))
    isAccum(x5888_d0_b1) = false
    bufferDepthOf(x5888_d0_b1) = 1
    staticDimsOf(x5888_d0_b1) = List(2, 128, 4, 128)
    val x5888_d0_b2 = withCtrl(x6242) { SRAM(size=32768, banking=Strided(banks=16, stride=1)).name("x5888_d0_b2").srcCtx("DataGenerator.scala:71:21:wx") } // x5888 = SRAMNew(ArrayBuffer(Const(2), Const(128), Const(4), Const(128)))
    isAccum(x5888_d0_b2) = false
    bufferDepthOf(x5888_d0_b2) = 1
    staticDimsOf(x5888_d0_b2) = List(2, 128, 4, 128)
    val x5888_d0_b3 = withCtrl(x6242) { SRAM(size=32768, banking=Strided(banks=16, stride=1)).name("x5888_d0_b3").srcCtx("DataGenerator.scala:71:21:wx") } // x5888 = SRAMNew(ArrayBuffer(Const(2), Const(128), Const(4), Const(128)))
    isAccum(x5888_d0_b3) = false
    bufferDepthOf(x5888_d0_b3) = 1
    staticDimsOf(x5888_d0_b3) = List(2, 128, 4, 128)
    val x5889 = withCtrl(x6242) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x5889").srcCtx("DataGenerator.scala:72:8") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x5890 = withCtrl(x6242) { Counter(min=Const(0), max=Const(128), step=Const(1), par=1).name("x5890").srcCtx("DataGenerator.scala:72:8") } // CounterNew(Const(0),Const(128),Const(1),Const(1))
    val x5891 = withCtrl(x6242) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x5891").srcCtx("DataGenerator.scala:72:8") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x5892 = withCtrl(x6242) { CounterChain(List(x5889,x5890,x5891)).name("x5892").srcCtx("DataGenerator.scala:72:8") } // CounterChainNew(List(x5889, x5890, x5891))
    val x5924 = withCtrl(x6242) { LoopController(style=StreamPipe, level=OuterControl, cchain=x5892).name("x5924").srcCtx("DataGenerator.scala:72:8") } // UnrolledForeach(List(Const(true)),x5892,Block(Const(())),List(List(b3339), List(b3340), List(b3341)),List(List(b3342), List(b3343), List(b3344)))
    val b3339 = withCtrl(x5924) { CounterIter(x5889, Some(0)).name("b3339") } // b3339
    val b3342 = withCtrl(x5924) { Const(true).name("b3342") } // b3342
    val b3340 = withCtrl(x5924) { CounterIter(x5890, Some(0)).name("b3340") } // b3340
    val b3343 = withCtrl(x5924) { Const(true).name("b3343") } // b3343
    val b3341 = withCtrl(x5924) { CounterIter(x5891, Some(0)).name("b3341") } // b3341
    val b3344 = withCtrl(x5924) { Const(true).name("b3344") } // b3344
    val b6265 = withCtrl(x5924) { StreamOut(field="offset").name("b6265").srcCtx("DataGenerator.scala:72:8") } // x5893 = StreamOutNew(BurstCmdBus)
    isAccum(b6265) = false
    bufferDepthOf(b6265) = 1
    val b6266 = withCtrl(x5924) { StreamOut(field="size").name("b6266").srcCtx("DataGenerator.scala:72:8") } // x5893 = StreamOutNew(BurstCmdBus)
    isAccum(b6266) = false
    bufferDepthOf(b6266) = 1
    val x5894 = withCtrl(x5924) { StreamIn(field="data").name("x5894").srcCtx("DataGenerator.scala:72:8") } // x5894 = StreamInNew(BurstDataBus())
    isAccum(x5894) = false
    bufferDepthOf(x5894) = 1
    val x5913 = withCtrl(x5924) { UnitController(style=SeqPipe, level=InnerControl).name("x5913").srcCtx("DataGenerator.scala:72:8") } // UnitPipe(List(b3342, b3343, b3344),Block(x5912))
    val x5895 = withCtrl(x5913) { b3339 } // FixConvert(b3339,TRUE,_32,_0) (Same Type. No op)
    val x5896 = withCtrl(x5913) { OpDef(op=FixSla, inputs=List(x5895, Const(16))).name("x5896").srcCtx("DataGenerator.scala:72:8") } // FixLsh(x5895,Const(16))
    val x5897 = withCtrl(x5913) { b3340 } // FixConvert(b3340,TRUE,_32,_0) (Same Type. No op)
    val x5898 = withCtrl(x5913) { OpDef(op=FixSla, inputs=List(x5897, Const(9))).name("x5898").srcCtx("DataGenerator.scala:72:8") } // FixLsh(x5897,Const(9))
    val x5899 = withCtrl(x5913) { b3341 } // FixConvert(b3341,TRUE,_32,_0) (Same Type. No op)
    val x5900 = withCtrl(x5913) { OpDef(op=FixSla, inputs=List(x5899, Const(7))).name("x5900").srcCtx("DataGenerator.scala:72:8") } // FixLsh(x5899,Const(7))
    val x5901 = withCtrl(x5913) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x5902 = withCtrl(x5913) { OpDef(op=FixAdd, inputs=List(x5896, x5898)).name("x5902").srcCtx("DataGenerator.scala:72:8") } // FixAdd(x5896,x5898)
    val x5903 = withCtrl(x5913) { OpDef(op=FixAdd, inputs=List(x5900, x5901)).name("x5903").srcCtx("DataGenerator.scala:72:8") } // FixAdd(x5900,x5901)
    val x5904 = withCtrl(x5913) { OpDef(op=FixAdd, inputs=List(x5902, x5903)).name("x5904").srcCtx("DataGenerator.scala:72:8") } // FixAdd(x5902,x5903)
    val x5905 = withCtrl(x5913) { OpDef(op=FixSla, inputs=List(x5904, Const(2))).name("x5905").srcCtx("DataGenerator.scala:72:8") } // FixLsh(x5904,Const(2))
    val x5906 = withCtrl(x5913) { x5905 } // FixConvert(x5905,TRUE,_64,_0)
    val x5907 = withCtrl(x5913) { DramAddress(x5786).name("x5907").srcCtx("DataGenerator.scala:72:8") } // GetDRAMAddress(x5786)
    val x5909_x5908 = withCtrl(x5913) { OpDef(op=FixAdd, inputs=List(x5906, x5907)).name("x5909_x5908").srcCtx("DataGenerator.scala:72:8") } // FixAdd(x5906,x5907)
    // x5909 = SimpleStruct(ArrayBuffer((offset,x5908), (size,Const(512)), (isLoad,Const(true))))
    val x5910 = withCtrl(x5913) { OpDef(op=BitAnd, inputs=List(b3342, b3343)).name("x5910").srcCtx("UnrollingBase.scala:28:66") } // And(b3342,b3343)
    val x5911 = withCtrl(x5913) { OpDef(op=BitAnd, inputs=List(x5910, b3344)).name("x5911").srcCtx("UnrollingBase.scala:28:66") } // And(x5910,b3344)
    val x5912_b6267_b6265 = withCtrl(x5913) { WriteMem(b6265, x5909_x5908).name("x5912_b6267_b6265").srcCtx("DataGenerator.scala:72:8") } // StreamWrite(x5893,x5909,x5911)
    val x5912_b6268_b6266 = withCtrl(x5913) { WriteMem(b6266, Const(512)).name("x5912_b6268_b6266").srcCtx("DataGenerator.scala:72:8") } // StreamWrite(x5893,x5909,x5911)
    val x5914 = withCtrl(x5924) { FringeDenseLoad(dram=List(x5786), cmdStream=List(b6265, b6266), dataStream=List(x5894)).name("x5914").srcCtx("DataGenerator.scala:72:8") } // FringeDenseLoad(x5786,x5893,x5894)
    val x5915 = withCtrl(x5924) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x5915").srcCtx("DataGenerator.scala:72:8") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x5916 = withCtrl(x5924) { CounterChain(List(x5915)).name("x5916").srcCtx("DataGenerator.scala:72:8") } // CounterChainNew(List(x5915))
    val x5923 = withCtrl(x5924) { LoopController(style=InnerPipe, level=InnerControl, cchain=x5916).name("x5923").srcCtx("DataGenerator.scala:72:8") } // UnrolledForeach(List(b3342, b3343, b3344),x5916,Block(Const(())),List(List(b3369)),List(List(b3370)))
    val b3369 = withCtrl(x5923) { CounterIter(x5915, None).name("b3369") } // b3369
    val b3370 = withCtrl(x5923) { Const(true).name("b3370") } // b3370
    val x5917 = withCtrl(x5923) { OpDef(op=BitAnd, inputs=List(b3370, b3342)).name("x5917").srcCtx("UnrollingBase.scala:28:66") } // And(b3370,b3342)
    val x5918 = withCtrl(x5923) { OpDef(op=BitAnd, inputs=List(b3343, b3344)).name("x5918").srcCtx("UnrollingBase.scala:28:66") } // And(b3343,b3344)
    val x5919 = withCtrl(x5923) { OpDef(op=BitAnd, inputs=List(x5917, x5918)).name("x5919").srcCtx("UnrollingBase.scala:28:66") } // And(x5917,x5918)
    val x5920_x5920 = withCtrl(x5923) { ReadMem(x5894).name("x5920_x5920").srcCtx("DataGenerator.scala:72:8") } // ParStreamRead(x5894,List(x5919))
    val x5921_x5921 = withCtrl(x5923) { x5920_x5920 } // VectorApply(x5920,0)
    val x5922 = withCtrl(x5923) { StoreBanks(List(List(x5888_d0_b0, x5888_d0_b1, x5888_d0_b2, x5888_d0_b3)), List(b3339, b3340, b3341, b3369), x5921_x5921).name("x5922").srcCtx("DataGenerator.scala:72:8") } // ParSRAMStore(x5888,List(List(b3339, b3340, b3341, b3369)),List(x5921),List(x5919))
    val x5925_d0_b0 = withCtrl(x6242) { SRAM(size=32768, banking=Strided(banks=16, stride=1)).name("x5925_d0_b0").srcCtx("DataGenerator.scala:71:21:wh") } // x5925 = SRAMNew(ArrayBuffer(Const(2), Const(128), Const(4), Const(128)))
    isAccum(x5925_d0_b0) = false
    bufferDepthOf(x5925_d0_b0) = 1
    staticDimsOf(x5925_d0_b0) = List(2, 128, 4, 128)
    val x5925_d0_b1 = withCtrl(x6242) { SRAM(size=32768, banking=Strided(banks=16, stride=1)).name("x5925_d0_b1").srcCtx("DataGenerator.scala:71:21:wh") } // x5925 = SRAMNew(ArrayBuffer(Const(2), Const(128), Const(4), Const(128)))
    isAccum(x5925_d0_b1) = false
    bufferDepthOf(x5925_d0_b1) = 1
    staticDimsOf(x5925_d0_b1) = List(2, 128, 4, 128)
    val x5925_d0_b2 = withCtrl(x6242) { SRAM(size=32768, banking=Strided(banks=16, stride=1)).name("x5925_d0_b2").srcCtx("DataGenerator.scala:71:21:wh") } // x5925 = SRAMNew(ArrayBuffer(Const(2), Const(128), Const(4), Const(128)))
    isAccum(x5925_d0_b2) = false
    bufferDepthOf(x5925_d0_b2) = 1
    staticDimsOf(x5925_d0_b2) = List(2, 128, 4, 128)
    val x5925_d0_b3 = withCtrl(x6242) { SRAM(size=32768, banking=Strided(banks=16, stride=1)).name("x5925_d0_b3").srcCtx("DataGenerator.scala:71:21:wh") } // x5925 = SRAMNew(ArrayBuffer(Const(2), Const(128), Const(4), Const(128)))
    isAccum(x5925_d0_b3) = false
    bufferDepthOf(x5925_d0_b3) = 1
    staticDimsOf(x5925_d0_b3) = List(2, 128, 4, 128)
    val x5926 = withCtrl(x6242) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x5926").srcCtx("DataGenerator.scala:72:8") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x5927 = withCtrl(x6242) { Counter(min=Const(0), max=Const(128), step=Const(1), par=1).name("x5927").srcCtx("DataGenerator.scala:72:8") } // CounterNew(Const(0),Const(128),Const(1),Const(1))
    val x5928 = withCtrl(x6242) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x5928").srcCtx("DataGenerator.scala:72:8") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x5929 = withCtrl(x6242) { CounterChain(List(x5926,x5927,x5928)).name("x5929").srcCtx("DataGenerator.scala:72:8") } // CounterChainNew(List(x5926, x5927, x5928))
    val x5961 = withCtrl(x6242) { LoopController(style=StreamPipe, level=OuterControl, cchain=x5929).name("x5961").srcCtx("DataGenerator.scala:72:8") } // UnrolledForeach(List(Const(true)),x5929,Block(Const(())),List(List(b3384), List(b3385), List(b3386)),List(List(b3387), List(b3388), List(b3389)))
    val b3384 = withCtrl(x5961) { CounterIter(x5926, Some(0)).name("b3384") } // b3384
    val b3387 = withCtrl(x5961) { Const(true).name("b3387") } // b3387
    val b3385 = withCtrl(x5961) { CounterIter(x5927, Some(0)).name("b3385") } // b3385
    val b3388 = withCtrl(x5961) { Const(true).name("b3388") } // b3388
    val b3386 = withCtrl(x5961) { CounterIter(x5928, Some(0)).name("b3386") } // b3386
    val b3389 = withCtrl(x5961) { Const(true).name("b3389") } // b3389
    val b6269 = withCtrl(x5961) { StreamOut(field="offset").name("b6269").srcCtx("DataGenerator.scala:72:8") } // x5930 = StreamOutNew(BurstCmdBus)
    isAccum(b6269) = false
    bufferDepthOf(b6269) = 1
    val b6270 = withCtrl(x5961) { StreamOut(field="size").name("b6270").srcCtx("DataGenerator.scala:72:8") } // x5930 = StreamOutNew(BurstCmdBus)
    isAccum(b6270) = false
    bufferDepthOf(b6270) = 1
    val x5931 = withCtrl(x5961) { StreamIn(field="data").name("x5931").srcCtx("DataGenerator.scala:72:8") } // x5931 = StreamInNew(BurstDataBus())
    isAccum(x5931) = false
    bufferDepthOf(x5931) = 1
    val x5950 = withCtrl(x5961) { UnitController(style=SeqPipe, level=InnerControl).name("x5950").srcCtx("DataGenerator.scala:72:8") } // UnitPipe(List(b3387, b3388, b3389),Block(x5949))
    val x5932 = withCtrl(x5950) { b3384 } // FixConvert(b3384,TRUE,_32,_0) (Same Type. No op)
    val x5933 = withCtrl(x5950) { OpDef(op=FixSla, inputs=List(x5932, Const(16))).name("x5933").srcCtx("DataGenerator.scala:72:8") } // FixLsh(x5932,Const(16))
    val x5934 = withCtrl(x5950) { b3385 } // FixConvert(b3385,TRUE,_32,_0) (Same Type. No op)
    val x5935 = withCtrl(x5950) { OpDef(op=FixSla, inputs=List(x5934, Const(9))).name("x5935").srcCtx("DataGenerator.scala:72:8") } // FixLsh(x5934,Const(9))
    val x5936 = withCtrl(x5950) { b3386 } // FixConvert(b3386,TRUE,_32,_0) (Same Type. No op)
    val x5937 = withCtrl(x5950) { OpDef(op=FixSla, inputs=List(x5936, Const(7))).name("x5937").srcCtx("DataGenerator.scala:72:8") } // FixLsh(x5936,Const(7))
    val x5938 = withCtrl(x5950) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x5939 = withCtrl(x5950) { OpDef(op=FixAdd, inputs=List(x5933, x5935)).name("x5939").srcCtx("DataGenerator.scala:72:8") } // FixAdd(x5933,x5935)
    val x5940 = withCtrl(x5950) { OpDef(op=FixAdd, inputs=List(x5937, x5938)).name("x5940").srcCtx("DataGenerator.scala:72:8") } // FixAdd(x5937,x5938)
    val x5941 = withCtrl(x5950) { OpDef(op=FixAdd, inputs=List(x5939, x5940)).name("x5941").srcCtx("DataGenerator.scala:72:8") } // FixAdd(x5939,x5940)
    val x5942 = withCtrl(x5950) { OpDef(op=FixSla, inputs=List(x5941, Const(2))).name("x5942").srcCtx("DataGenerator.scala:72:8") } // FixLsh(x5941,Const(2))
    val x5943 = withCtrl(x5950) { x5942 } // FixConvert(x5942,TRUE,_64,_0)
    val x5944 = withCtrl(x5950) { DramAddress(x5799).name("x5944").srcCtx("DataGenerator.scala:72:8") } // GetDRAMAddress(x5799)
    val x5946_x5945 = withCtrl(x5950) { OpDef(op=FixAdd, inputs=List(x5943, x5944)).name("x5946_x5945").srcCtx("DataGenerator.scala:72:8") } // FixAdd(x5943,x5944)
    // x5946 = SimpleStruct(ArrayBuffer((offset,x5945), (size,Const(512)), (isLoad,Const(true))))
    val x5947 = withCtrl(x5950) { OpDef(op=BitAnd, inputs=List(b3387, b3388)).name("x5947").srcCtx("UnrollingBase.scala:28:66") } // And(b3387,b3388)
    val x5948 = withCtrl(x5950) { OpDef(op=BitAnd, inputs=List(x5947, b3389)).name("x5948").srcCtx("UnrollingBase.scala:28:66") } // And(x5947,b3389)
    val x5949_b6271_b6269 = withCtrl(x5950) { WriteMem(b6269, x5946_x5945).name("x5949_b6271_b6269").srcCtx("DataGenerator.scala:72:8") } // StreamWrite(x5930,x5946,x5948)
    val x5949_b6272_b6270 = withCtrl(x5950) { WriteMem(b6270, Const(512)).name("x5949_b6272_b6270").srcCtx("DataGenerator.scala:72:8") } // StreamWrite(x5930,x5946,x5948)
    val x5951 = withCtrl(x5961) { FringeDenseLoad(dram=List(x5799), cmdStream=List(b6269, b6270), dataStream=List(x5931)).name("x5951").srcCtx("DataGenerator.scala:72:8") } // FringeDenseLoad(x5799,x5930,x5931)
    val x5952 = withCtrl(x5961) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x5952").srcCtx("DataGenerator.scala:72:8") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x5953 = withCtrl(x5961) { CounterChain(List(x5952)).name("x5953").srcCtx("DataGenerator.scala:72:8") } // CounterChainNew(List(x5952))
    val x5960 = withCtrl(x5961) { LoopController(style=InnerPipe, level=InnerControl, cchain=x5953).name("x5960").srcCtx("DataGenerator.scala:72:8") } // UnrolledForeach(List(b3387, b3388, b3389),x5953,Block(Const(())),List(List(b3414)),List(List(b3415)))
    val b3414 = withCtrl(x5960) { CounterIter(x5952, None).name("b3414") } // b3414
    val b3415 = withCtrl(x5960) { Const(true).name("b3415") } // b3415
    val x5954 = withCtrl(x5960) { OpDef(op=BitAnd, inputs=List(b3415, b3387)).name("x5954").srcCtx("UnrollingBase.scala:28:66") } // And(b3415,b3387)
    val x5955 = withCtrl(x5960) { OpDef(op=BitAnd, inputs=List(b3388, b3389)).name("x5955").srcCtx("UnrollingBase.scala:28:66") } // And(b3388,b3389)
    val x5956 = withCtrl(x5960) { OpDef(op=BitAnd, inputs=List(x5954, x5955)).name("x5956").srcCtx("UnrollingBase.scala:28:66") } // And(x5954,x5955)
    val x5957_x5957 = withCtrl(x5960) { ReadMem(x5931).name("x5957_x5957").srcCtx("DataGenerator.scala:72:8") } // ParStreamRead(x5931,List(x5956))
    val x5958_x5958 = withCtrl(x5960) { x5957_x5957 } // VectorApply(x5957,0)
    val x5959 = withCtrl(x5960) { StoreBanks(List(List(x5925_d0_b0, x5925_d0_b1, x5925_d0_b2, x5925_d0_b3)), List(b3384, b3385, b3386, b3414), x5958_x5958).name("x5959").srcCtx("DataGenerator.scala:72:8") } // ParSRAMStore(x5925,List(List(b3384, b3385, b3386, b3414)),List(x5958),List(x5956))
    val x5962_d0_b0 = withCtrl(x6242) { SRAM(size=1024, banking=Strided(banks=16, stride=1)).name("x5962_d0_b0").srcCtx("DataGenerator.scala:54:21:b") } // x5962 = SRAMNew(ArrayBuffer(Const(2), Const(4), Const(128)))
    isAccum(x5962_d0_b0) = false
    bufferDepthOf(x5962_d0_b0) = 1
    staticDimsOf(x5962_d0_b0) = List(2, 4, 128)
    val x5963 = withCtrl(x6242) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x5963").srcCtx("DataGenerator.scala:55:8") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x5964 = withCtrl(x6242) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x5964").srcCtx("DataGenerator.scala:55:8") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x5965 = withCtrl(x6242) { CounterChain(List(x5963,x5964)).name("x5965").srcCtx("DataGenerator.scala:55:8") } // CounterChainNew(List(x5963, x5964))
    val x5992 = withCtrl(x6242) { LoopController(style=StreamPipe, level=OuterControl, cchain=x5965).name("x5992").srcCtx("DataGenerator.scala:55:8") } // UnrolledForeach(List(Const(true)),x5965,Block(Const(())),List(List(b3428), List(b3429)),List(List(b3430), List(b3431)))
    val b3428 = withCtrl(x5992) { CounterIter(x5963, Some(0)).name("b3428") } // b3428
    val b3430 = withCtrl(x5992) { Const(true).name("b3430") } // b3430
    val b3429 = withCtrl(x5992) { CounterIter(x5964, Some(0)).name("b3429") } // b3429
    val b3431 = withCtrl(x5992) { Const(true).name("b3431") } // b3431
    val b6273 = withCtrl(x5992) { StreamOut(field="offset").name("b6273").srcCtx("DataGenerator.scala:55:8") } // x5966 = StreamOutNew(BurstCmdBus)
    isAccum(b6273) = false
    bufferDepthOf(b6273) = 1
    val b6274 = withCtrl(x5992) { StreamOut(field="size").name("b6274").srcCtx("DataGenerator.scala:55:8") } // x5966 = StreamOutNew(BurstCmdBus)
    isAccum(b6274) = false
    bufferDepthOf(b6274) = 1
    val x5967 = withCtrl(x5992) { StreamIn(field="data").name("x5967").srcCtx("DataGenerator.scala:55:8") } // x5967 = StreamInNew(BurstDataBus())
    isAccum(x5967) = false
    bufferDepthOf(x5967) = 1
    val x5982 = withCtrl(x5992) { UnitController(style=SeqPipe, level=InnerControl).name("x5982").srcCtx("DataGenerator.scala:55:8") } // UnitPipe(List(b3430, b3431),Block(x5981))
    val x5968 = withCtrl(x5982) { b3428 } // FixConvert(b3428,TRUE,_32,_0) (Same Type. No op)
    val x5969 = withCtrl(x5982) { OpDef(op=FixSla, inputs=List(x5968, Const(9))).name("x5969").srcCtx("DataGenerator.scala:55:8") } // FixLsh(x5968,Const(9))
    val x5970 = withCtrl(x5982) { b3429 } // FixConvert(b3429,TRUE,_32,_0) (Same Type. No op)
    val x5971 = withCtrl(x5982) { OpDef(op=FixSla, inputs=List(x5970, Const(7))).name("x5971").srcCtx("DataGenerator.scala:55:8") } // FixLsh(x5970,Const(7))
    val x5972 = withCtrl(x5982) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x5973 = withCtrl(x5982) { OpDef(op=FixAdd, inputs=List(x5969, x5971)).name("x5973").srcCtx("DataGenerator.scala:55:8") } // FixAdd(x5969,x5971)
    val x5974 = withCtrl(x5982) { OpDef(op=FixAdd, inputs=List(x5973, x5972)).name("x5974").srcCtx("DataGenerator.scala:55:8") } // FixAdd(x5973,x5972)
    val x5975 = withCtrl(x5982) { OpDef(op=FixSla, inputs=List(x5974, Const(2))).name("x5975").srcCtx("DataGenerator.scala:55:8") } // FixLsh(x5974,Const(2))
    val x5976 = withCtrl(x5982) { x5975 } // FixConvert(x5975,TRUE,_64,_0)
    val x5977 = withCtrl(x5982) { DramAddress(x5776).name("x5977").srcCtx("DataGenerator.scala:55:8") } // GetDRAMAddress(x5776)
    val x5979_x5978 = withCtrl(x5982) { OpDef(op=FixAdd, inputs=List(x5976, x5977)).name("x5979_x5978").srcCtx("DataGenerator.scala:55:8") } // FixAdd(x5976,x5977)
    // x5979 = SimpleStruct(ArrayBuffer((offset,x5978), (size,Const(512)), (isLoad,Const(true))))
    val x5980 = withCtrl(x5982) { OpDef(op=BitAnd, inputs=List(b3430, b3431)).name("x5980").srcCtx("UnrollingBase.scala:28:66") } // And(b3430,b3431)
    val x5981_b6275_b6273 = withCtrl(x5982) { WriteMem(b6273, x5979_x5978).name("x5981_b6275_b6273").srcCtx("DataGenerator.scala:55:8") } // StreamWrite(x5966,x5979,x5980)
    val x5981_b6276_b6274 = withCtrl(x5982) { WriteMem(b6274, Const(512)).name("x5981_b6276_b6274").srcCtx("DataGenerator.scala:55:8") } // StreamWrite(x5966,x5979,x5980)
    val x5983 = withCtrl(x5992) { FringeDenseLoad(dram=List(x5776), cmdStream=List(b6273, b6274), dataStream=List(x5967)).name("x5983").srcCtx("DataGenerator.scala:55:8") } // FringeDenseLoad(x5776,x5966,x5967)
    val x5984 = withCtrl(x5992) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x5984").srcCtx("DataGenerator.scala:55:8") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x5985 = withCtrl(x5992) { CounterChain(List(x5984)).name("x5985").srcCtx("DataGenerator.scala:55:8") } // CounterChainNew(List(x5984))
    val x5991 = withCtrl(x5992) { LoopController(style=InnerPipe, level=InnerControl, cchain=x5985).name("x5991").srcCtx("DataGenerator.scala:55:8") } // UnrolledForeach(List(b3430, b3431),x5985,Block(Const(())),List(List(b3452)),List(List(b3453)))
    val b3452 = withCtrl(x5991) { CounterIter(x5984, None).name("b3452") } // b3452
    val b3453 = withCtrl(x5991) { Const(true).name("b3453") } // b3453
    val x5986 = withCtrl(x5991) { OpDef(op=BitAnd, inputs=List(b3453, b3430)).name("x5986").srcCtx("UnrollingBase.scala:28:66") } // And(b3453,b3430)
    val x5987 = withCtrl(x5991) { OpDef(op=BitAnd, inputs=List(x5986, b3431)).name("x5987").srcCtx("UnrollingBase.scala:28:66") } // And(x5986,b3431)
    val x5988_x5988 = withCtrl(x5991) { ReadMem(x5967).name("x5988_x5988").srcCtx("DataGenerator.scala:55:8") } // ParStreamRead(x5967,List(x5987))
    val x5989_x5989 = withCtrl(x5991) { x5988_x5988 } // VectorApply(x5988,0)
    val x5990 = withCtrl(x5991) { StoreBanks(List(List(x5962_d0_b0)), List(b3428, b3429, b3452), x5989_x5989).name("x5990").srcCtx("DataGenerator.scala:55:8") } // ParSRAMStore(x5962,List(List(b3428, b3429, b3452)),List(x5989),List(x5987))
    val x5993 = withCtrl(x6242) { Counter(min=Const(0), max=Const(8), step=Const(1), par=1).name("x5993").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:17:28") } // CounterNew(Const(0),Const(8),Const(1),Const(1))
    val x5994 = withCtrl(x6242) { CounterChain(List(x5993)).name("x5994").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:17:41") } // CounterChainNew(List(x5993))
    val x6213 = withCtrl(x6242) { LoopController(style=MetaPipe, level=OuterControl, cchain=x5994).name("x6213").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:17:41") } // UnrolledForeach(List(Const(true)),x5994,Block(Const(())),List(List(b3463)),List(List(b3464)))
    val b3463 = withCtrl(x6213) { CounterIter(x5993, Some(0)).name("b3463") } // b3463
    val b3464 = withCtrl(x6213) { Const(true).name("b3464") } // b3464
    val x5995 = withCtrl(x6213) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x5995").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:18:31") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x5996 = withCtrl(x6213) { CounterChain(List(x5995)).name("x5996").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:18:45") } // CounterChainNew(List(x5995))
    val x6212 = withCtrl(x6213) { LoopController(style=MetaPipe, level=OuterControl, cchain=x5996).name("x6212").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:18:45") } // UnrolledForeach(List(b3464),x5996,Block(Const(())),List(List(b3467)),List(List(b3468)))
    val b3467 = withCtrl(x6212) { CounterIter(x5995, Some(0)).name("b3467") } // b3467
    val b3468 = withCtrl(x6212) { Const(true).name("b3468") } // b3468
    val x5997_d0_b0 = withCtrl(x6212) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x5997_d0_b0").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:25:32:foldMem") } // x5997 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x5997_d0_b0) = false
    bufferDepthOf(x5997_d0_b0) = 1
    staticDimsOf(x5997_d0_b0) = List(4, 128)
    val x5997_d1_b0 = withCtrl(x6212) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x5997_d1_b0").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:25:32:foldMem") } // x5997 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x5997_d1_b0) = true
    bufferDepthOf(x5997_d1_b0) = 1
    staticDimsOf(x5997_d1_b0) = List(4, 128)
    val x6211 = withCtrl(x6212) { UnitController(style=SeqPipe, level=OuterControl).name("x6211").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:26:16") } // UnitPipe(List(b3468, b3464),Block(Const(())))
    val x5998 = withCtrl(x6211) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x5998").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:27:34") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x5999 = withCtrl(x6211) { CounterChain(List(x5998)).name("x5999").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:27:47") } // CounterChainNew(List(x5998))
    val x6009 = withCtrl(x6211) { LoopController(style=MetaPipe, level=OuterControl, cchain=x5999).name("x6009").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:27:47") } // UnrolledForeach(List(b3468, b3464),x5999,Block(Const(())),List(List(b3472)),List(List(b3473)))
    val b3472 = withCtrl(x6009) { CounterIter(x5998, Some(0)).name("b3472") } // b3472
    val b3473 = withCtrl(x6009) { Const(true).name("b3473") } // b3473
    val x6000 = withCtrl(x6009) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6000").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:28:42") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6001 = withCtrl(x6009) { CounterChain(List(x6000)).name("x6001").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:28:56") } // CounterChainNew(List(x6000))
    val x6008 = withCtrl(x6009) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6001).name("x6008").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:28:56") } // UnrolledForeach(List(b3473, b3468, b3464),x6001,Block(Const(())),List(List(b3476)),List(List(b3477)))
    val b3476 = withCtrl(x6008) { CounterIter(x6000, None).name("b3476") } // b3476
    val b3477 = withCtrl(x6008) { Const(true).name("b3477") } // b3477
    val x6002 = withCtrl(x6008) { OpDef(op=BitAnd, inputs=List(b3477, b3473)).name("x6002").srcCtx("UnrollingBase.scala:28:66") } // And(b3477,b3473)
    val x6003 = withCtrl(x6008) { OpDef(op=BitAnd, inputs=List(b3468, b3464)).name("x6003").srcCtx("UnrollingBase.scala:28:66") } // And(b3468,b3464)
    val x6004 = withCtrl(x6008) { OpDef(op=BitAnd, inputs=List(x6002, x6003)).name("x6004").srcCtx("UnrollingBase.scala:28:66") } // And(x6002,x6003)
    val x6005 = withCtrl(x6008) { LoadBanks(List(x5962_d0_b0), List(b3467, b3472, b3476)).name("x6005").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:29:50") } // ParSRAMLoad(x5962,List(List(b3467, b3472, b3476)),List(x6004))
    val x6006 = withCtrl(x6008) { x6005 } // VectorApply(x6005,0)
    val x6007 = withCtrl(x6008) { StoreBanks(List(List(x5997_d0_b0), List(x5997_d1_b0)), List(b3472, b3476), x6006).name("x6007").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:29:45") } // ParSRAMStore(x5997,List(List(b3472, b3476)),List(x6006),List(x6004))
    val x6010 = withCtrl(x6211) { Counter(min=Const(0), max=Const(128), step=Const(1), par=1).name("x6010").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:37:61") } // CounterNew(Const(0),Const(128),Const(1),Const(1))
    val x6011 = withCtrl(x6211) { CounterChain(List(x6010)).name("x6011").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:50:14") } // CounterChainNew(List(x6010))
    val x6111 = withCtrl(x6211) { LoopController(style=MetaPipe, level=OuterControl, cchain=x6011).name("x6111").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:50:14") } // UnrolledReduce(List(b3468, b3464),x6011,x5997,Block((x5997) => Const(())),List(List(b3491)),List(List(b3492)))
    val b3491 = withCtrl(x6111) { CounterIter(x6010, Some(0)).name("b3491") } // b3491
    val b3492 = withCtrl(x6111) { Const(true).name("b3492") } // b3492
    val x6012_d0_b0 = withCtrl(x6111) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x6012_d0_b0").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:38:31:re") } // x6012 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x6012_d0_b0) = false
    bufferDepthOf(x6012_d0_b0) = 2
    staticDimsOf(x6012_d0_b0) = List(4, 128)
    val x6012_d0_b1 = withCtrl(x6111) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x6012_d0_b1").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:38:31:re") } // x6012 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x6012_d0_b1) = false
    bufferDepthOf(x6012_d0_b1) = 2
    staticDimsOf(x6012_d0_b1) = List(4, 128)
    val x6012_d0_b2 = withCtrl(x6111) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x6012_d0_b2").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:38:31:re") } // x6012 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x6012_d0_b2) = false
    bufferDepthOf(x6012_d0_b2) = 2
    staticDimsOf(x6012_d0_b2) = List(4, 128)
    val x6012_d0_b3 = withCtrl(x6111) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x6012_d0_b3").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:38:31:re") } // x6012 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x6012_d0_b3) = false
    bufferDepthOf(x6012_d0_b3) = 2
    staticDimsOf(x6012_d0_b3) = List(4, 128)
    val x6013_d0 = withCtrl(x6111) { Reg(init=Some(0.0)).name("x6013_d0").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:50:14") } // x6013 = RegNew(Const(0))
    isAccum(x6013_d0) = false
    bufferDepthOf(x6013_d0) = 2
    val x6013_d1 = withCtrl(x6111) { Reg(init=Some(0.0)).name("x6013_d1").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:50:14") } // x6013 = RegNew(Const(0))
    isAccum(x6013_d1) = false
    bufferDepthOf(x6013_d1) = 2
    val x6013_d2 = withCtrl(x6111) { Reg(init=Some(0.0)).name("x6013_d2").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:50:14") } // x6013 = RegNew(Const(0))
    isAccum(x6013_d2) = false
    bufferDepthOf(x6013_d2) = 2
    val x6013_d3 = withCtrl(x6111) { Reg(init=Some(0.0)).name("x6013_d3").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:50:14") } // x6013 = RegNew(Const(0))
    isAccum(x6013_d3) = false
    bufferDepthOf(x6013_d3) = 2
    val x6014_d0 = withCtrl(x6111) { Reg(init=Some(0.0)).name("x6014_d0").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:50:14") } // x6014 = RegNew(Const(0))
    isAccum(x6014_d0) = false
    bufferDepthOf(x6014_d0) = 2
    val x6014_d1 = withCtrl(x6111) { Reg(init=Some(0.0)).name("x6014_d1").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:50:14") } // x6014 = RegNew(Const(0))
    isAccum(x6014_d1) = false
    bufferDepthOf(x6014_d1) = 2
    val x6014_d2 = withCtrl(x6111) { Reg(init=Some(0.0)).name("x6014_d2").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:50:14") } // x6014 = RegNew(Const(0))
    isAccum(x6014_d2) = false
    bufferDepthOf(x6014_d2) = 2
    val x6014_d3 = withCtrl(x6111) { Reg(init=Some(0.0)).name("x6014_d3").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:50:14") } // x6014 = RegNew(Const(0))
    isAccum(x6014_d3) = false
    bufferDepthOf(x6014_d3) = 2
    val x6021 = withCtrl(x6111) { UnitController(style=SeqPipe, level=InnerControl).name("x6021").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:50:14") } // UnitPipe(List(b3492, b3468, b3464),Block(Const(())))
    val x6015 = withCtrl(x6021) { OpDef(op=BitAnd, inputs=List(b3492, b3468)).name("x6015").srcCtx("UnrollingBase.scala:28:66") } // And(b3492,b3468)
    val x6016 = withCtrl(x6021) { OpDef(op=BitAnd, inputs=List(x6015, b3464)).name("x6016").srcCtx("UnrollingBase.scala:28:66") } // And(x6015,b3464)
    val x6017 = withCtrl(x6021) { LoadBanks(List(x5813_d1_b0), List(b3463, b3491)).name("x6017").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:39:29:xEle") } // SRAMLoad(x5813,ArrayBuffer(Const(8), Const(128)),List(b3463, b3491),Const(0),x6016)
    val x6018 = withCtrl(x6021) { LoadBanks(List(x5863_d1_b0), List(b3467, b3491)).name("x6018").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:40:29:hEle") } // SRAMLoad(x5863,ArrayBuffer(Const(2), Const(128)),List(b3467, b3491),Const(0),x6016)
    val x6019_x6013_d0 = withCtrl(x6021) { WriteMem(x6013_d0, x6017).name("x6019_x6013_d0").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:50:14") } // RegWrite(x6013,x6017,x6016)
    val x6019_x6013_d1 = withCtrl(x6021) { WriteMem(x6013_d1, x6017).name("x6019_x6013_d1").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:50:14") } // RegWrite(x6013,x6017,x6016)
    val x6019_x6013_d2 = withCtrl(x6021) { WriteMem(x6013_d2, x6017).name("x6019_x6013_d2").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:50:14") } // RegWrite(x6013,x6017,x6016)
    val x6019_x6013_d3 = withCtrl(x6021) { WriteMem(x6013_d3, x6017).name("x6019_x6013_d3").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:50:14") } // RegWrite(x6013,x6017,x6016)
    val x6020_x6014_d0 = withCtrl(x6021) { WriteMem(x6014_d0, x6018).name("x6020_x6014_d0").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:50:14") } // RegWrite(x6014,x6018,x6016)
    val x6020_x6014_d1 = withCtrl(x6021) { WriteMem(x6014_d1, x6018).name("x6020_x6014_d1").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:50:14") } // RegWrite(x6014,x6018,x6016)
    val x6020_x6014_d2 = withCtrl(x6021) { WriteMem(x6014_d2, x6018).name("x6020_x6014_d2").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:50:14") } // RegWrite(x6014,x6018,x6016)
    val x6020_x6014_d3 = withCtrl(x6021) { WriteMem(x6014_d3, x6018).name("x6020_x6014_d3").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:50:14") } // RegWrite(x6014,x6018,x6016)
    val x6022 = withCtrl(x6111) { Counter(min=Const(0), max=Const(4), step=Const(1), par=4).name("x6022").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:41:36") } // CounterNew(Const(0),Const(4),Const(1),Const(4))
    val x6023 = withCtrl(x6111) { CounterChain(List(x6022)).name("x6023").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:41:43") } // CounterChainNew(List(x6022))
    val x6093 = withCtrl(x6111) { LoopController(style=MetaPipe, level=OuterControl, cchain=x6023).name("x6093").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:41:43") } // UnrolledForeach(List(b3492, b3468, b3464),x6023,Block(Const(())),List(List(b3505, b3506, b3507, b3508)),List(List(b3509, b3510, b3511, b3512)))
    val b3505 = withCtrl(x6093) { CounterIter(x6022, Some(0)).name("b3505") } // b3505
    val b3509 = withCtrl(x6093) { Const(true).name("b3509") } // b3509
    val b3506 = withCtrl(x6093) { CounterIter(x6022, Some(1)).name("b3506") } // b3506
    val b3510 = withCtrl(x6093) { Const(true).name("b3510") } // b3510
    val b3507 = withCtrl(x6093) { CounterIter(x6022, Some(2)).name("b3507") } // b3507
    val b3511 = withCtrl(x6093) { Const(true).name("b3511") } // b3511
    val b3508 = withCtrl(x6093) { CounterIter(x6022, Some(3)).name("b3508") } // b3508
    val b3512 = withCtrl(x6093) { Const(true).name("b3512") } // b3512
    val x6092 = withCtrl(x6093) { UnitController(style=ForkJoin, level=OuterControl).name("x6092").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b3492, b3468, b3464),Block(Const(())))
    val x6024 = withCtrl(x6092) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6024").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:42:44") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6025 = withCtrl(x6092) { CounterChain(List(x6024)).name("x6025").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:42:58") } // CounterChainNew(List(x6024))
    val x6040 = withCtrl(x6092) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6025).name("x6040").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:42:58") } // UnrolledForeach(List(b3509, b3492, b3468, b3464),x6025,Block(Const(())),List(List(b3521)),List(List(b3522)))
    val b3521 = withCtrl(x6040) { CounterIter(x6024, None).name("b3521") } // b3521
    val b3522 = withCtrl(x6040) { Const(true).name("b3522") } // b3522
    val x6026 = withCtrl(x6040) { OpDef(op=BitAnd, inputs=List(b3522, b3509)).name("x6026").srcCtx("UnrollingBase.scala:28:66") } // And(b3522,b3509)
    val x6027 = withCtrl(x6040) { OpDef(op=BitAnd, inputs=List(b3492, b3468)).name("x6027").srcCtx("UnrollingBase.scala:28:66") } // And(b3492,b3468)
    val x6028 = withCtrl(x6040) { OpDef(op=BitAnd, inputs=List(x6026, x6027)).name("x6028").srcCtx("UnrollingBase.scala:28:66") } // And(x6026,x6027)
    val x6029 = withCtrl(x6040) { OpDef(op=BitAnd, inputs=List(x6028, b3464)).name("x6029").srcCtx("UnrollingBase.scala:28:66") } // And(x6028,b3464)
    val x6030 = withCtrl(x6040) { LoadBanks(List(x5888_d0_b0), List(b3467, b3491, b3505, b3521)).name("x6030").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:43:33") } // ParSRAMLoad(x5888,List(List(b3467, b3491, b3505, b3521)),List(x6029))
    val x6031 = withCtrl(x6040) { x6030 } // VectorApply(x6030,0)
    val x6032 = withCtrl(x6040) { ReadMem(x6013_d0).name("x6032").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:50:14") } // RegRead(x6013)
    val x6033 = withCtrl(x6040) { OpDef(op=FixMul, inputs=List(x6031, x6032)).name("x6033").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:43:71:reX") } // FixMul(x6031,x6032)
    val x6034 = withCtrl(x6040) { LoadBanks(List(x5925_d0_b0), List(b3467, b3491, b3505, b3521)).name("x6034").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:44:33") } // ParSRAMLoad(x5925,List(List(b3467, b3491, b3505, b3521)),List(x6029))
    val x6035 = withCtrl(x6040) { x6034 } // VectorApply(x6034,0)
    val x6036 = withCtrl(x6040) { ReadMem(x6014_d0).name("x6036").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:50:14") } // RegRead(x6014)
    val x6037 = withCtrl(x6040) { OpDef(op=FixMul, inputs=List(x6035, x6036)).name("x6037").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:44:71:reH") } // FixMul(x6035,x6036)
    val x6038 = withCtrl(x6040) { OpDef(op=FixAdd, inputs=List(x6033, x6037)).name("x6038").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:45:48") } // FixAdd(x6033,x6037)
    val x6039 = withCtrl(x6040) { StoreBanks(List(List(x6012_d0_b0)), List(b3505, b3521), x6038).name("x6039").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:45:42") } // ParSRAMStore(x6012,List(List(b3505, b3521)),List(x6038),List(x6029))
    val x6041 = withCtrl(x6092) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6041").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:42:44") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6042 = withCtrl(x6092) { CounterChain(List(x6041)).name("x6042").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:42:58") } // CounterChainNew(List(x6041))
    val x6057 = withCtrl(x6092) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6042).name("x6057").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:42:58") } // UnrolledForeach(List(b3510, b3492, b3468, b3464),x6042,Block(Const(())),List(List(b3538)),List(List(b3539)))
    val b3538 = withCtrl(x6057) { CounterIter(x6041, None).name("b3538") } // b3538
    val b3539 = withCtrl(x6057) { Const(true).name("b3539") } // b3539
    val x6043 = withCtrl(x6057) { OpDef(op=BitAnd, inputs=List(b3539, b3510)).name("x6043").srcCtx("UnrollingBase.scala:28:66") } // And(b3539,b3510)
    val x6044 = withCtrl(x6057) { OpDef(op=BitAnd, inputs=List(b3492, b3468)).name("x6044").srcCtx("UnrollingBase.scala:28:66") } // And(b3492,b3468)
    val x6045 = withCtrl(x6057) { OpDef(op=BitAnd, inputs=List(x6043, x6044)).name("x6045").srcCtx("UnrollingBase.scala:28:66") } // And(x6043,x6044)
    val x6046 = withCtrl(x6057) { OpDef(op=BitAnd, inputs=List(x6045, b3464)).name("x6046").srcCtx("UnrollingBase.scala:28:66") } // And(x6045,b3464)
    val x6047 = withCtrl(x6057) { LoadBanks(List(x5888_d0_b1), List(b3467, b3491, b3506, b3538)).name("x6047").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:43:33") } // ParSRAMLoad(x5888,List(List(b3467, b3491, b3506, b3538)),List(x6046))
    val x6048 = withCtrl(x6057) { x6047 } // VectorApply(x6047,0)
    val x6049 = withCtrl(x6057) { ReadMem(x6013_d1).name("x6049").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:50:14") } // RegRead(x6013)
    val x6050 = withCtrl(x6057) { OpDef(op=FixMul, inputs=List(x6048, x6049)).name("x6050").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:43:71:reX") } // FixMul(x6048,x6049)
    val x6051 = withCtrl(x6057) { LoadBanks(List(x5925_d0_b1), List(b3467, b3491, b3506, b3538)).name("x6051").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:44:33") } // ParSRAMLoad(x5925,List(List(b3467, b3491, b3506, b3538)),List(x6046))
    val x6052 = withCtrl(x6057) { x6051 } // VectorApply(x6051,0)
    val x6053 = withCtrl(x6057) { ReadMem(x6014_d1).name("x6053").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:50:14") } // RegRead(x6014)
    val x6054 = withCtrl(x6057) { OpDef(op=FixMul, inputs=List(x6052, x6053)).name("x6054").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:44:71:reH") } // FixMul(x6052,x6053)
    val x6055 = withCtrl(x6057) { OpDef(op=FixAdd, inputs=List(x6050, x6054)).name("x6055").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:45:48") } // FixAdd(x6050,x6054)
    val x6056 = withCtrl(x6057) { StoreBanks(List(List(x6012_d0_b1)), List(b3506, b3538), x6055).name("x6056").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:45:42") } // ParSRAMStore(x6012,List(List(b3506, b3538)),List(x6055),List(x6046))
    val x6058 = withCtrl(x6092) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6058").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:42:44") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6059 = withCtrl(x6092) { CounterChain(List(x6058)).name("x6059").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:42:58") } // CounterChainNew(List(x6058))
    val x6074 = withCtrl(x6092) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6059).name("x6074").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:42:58") } // UnrolledForeach(List(b3511, b3492, b3468, b3464),x6059,Block(Const(())),List(List(b3555)),List(List(b3556)))
    val b3555 = withCtrl(x6074) { CounterIter(x6058, None).name("b3555") } // b3555
    val b3556 = withCtrl(x6074) { Const(true).name("b3556") } // b3556
    val x6060 = withCtrl(x6074) { OpDef(op=BitAnd, inputs=List(b3556, b3511)).name("x6060").srcCtx("UnrollingBase.scala:28:66") } // And(b3556,b3511)
    val x6061 = withCtrl(x6074) { OpDef(op=BitAnd, inputs=List(b3492, b3468)).name("x6061").srcCtx("UnrollingBase.scala:28:66") } // And(b3492,b3468)
    val x6062 = withCtrl(x6074) { OpDef(op=BitAnd, inputs=List(x6060, x6061)).name("x6062").srcCtx("UnrollingBase.scala:28:66") } // And(x6060,x6061)
    val x6063 = withCtrl(x6074) { OpDef(op=BitAnd, inputs=List(x6062, b3464)).name("x6063").srcCtx("UnrollingBase.scala:28:66") } // And(x6062,b3464)
    val x6064 = withCtrl(x6074) { LoadBanks(List(x5888_d0_b2), List(b3467, b3491, b3507, b3555)).name("x6064").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:43:33") } // ParSRAMLoad(x5888,List(List(b3467, b3491, b3507, b3555)),List(x6063))
    val x6065 = withCtrl(x6074) { x6064 } // VectorApply(x6064,0)
    val x6066 = withCtrl(x6074) { ReadMem(x6013_d2).name("x6066").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:50:14") } // RegRead(x6013)
    val x6067 = withCtrl(x6074) { OpDef(op=FixMul, inputs=List(x6065, x6066)).name("x6067").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:43:71:reX") } // FixMul(x6065,x6066)
    val x6068 = withCtrl(x6074) { LoadBanks(List(x5925_d0_b2), List(b3467, b3491, b3507, b3555)).name("x6068").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:44:33") } // ParSRAMLoad(x5925,List(List(b3467, b3491, b3507, b3555)),List(x6063))
    val x6069 = withCtrl(x6074) { x6068 } // VectorApply(x6068,0)
    val x6070 = withCtrl(x6074) { ReadMem(x6014_d2).name("x6070").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:50:14") } // RegRead(x6014)
    val x6071 = withCtrl(x6074) { OpDef(op=FixMul, inputs=List(x6069, x6070)).name("x6071").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:44:71:reH") } // FixMul(x6069,x6070)
    val x6072 = withCtrl(x6074) { OpDef(op=FixAdd, inputs=List(x6067, x6071)).name("x6072").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:45:48") } // FixAdd(x6067,x6071)
    val x6073 = withCtrl(x6074) { StoreBanks(List(List(x6012_d0_b2)), List(b3507, b3555), x6072).name("x6073").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:45:42") } // ParSRAMStore(x6012,List(List(b3507, b3555)),List(x6072),List(x6063))
    val x6075 = withCtrl(x6092) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6075").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:42:44") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6076 = withCtrl(x6092) { CounterChain(List(x6075)).name("x6076").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:42:58") } // CounterChainNew(List(x6075))
    val x6091 = withCtrl(x6092) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6076).name("x6091").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:42:58") } // UnrolledForeach(List(b3512, b3492, b3468, b3464),x6076,Block(Const(())),List(List(b3572)),List(List(b3573)))
    val b3572 = withCtrl(x6091) { CounterIter(x6075, None).name("b3572") } // b3572
    val b3573 = withCtrl(x6091) { Const(true).name("b3573") } // b3573
    val x6077 = withCtrl(x6091) { OpDef(op=BitAnd, inputs=List(b3573, b3512)).name("x6077").srcCtx("UnrollingBase.scala:28:66") } // And(b3573,b3512)
    val x6078 = withCtrl(x6091) { OpDef(op=BitAnd, inputs=List(b3492, b3468)).name("x6078").srcCtx("UnrollingBase.scala:28:66") } // And(b3492,b3468)
    val x6079 = withCtrl(x6091) { OpDef(op=BitAnd, inputs=List(x6077, x6078)).name("x6079").srcCtx("UnrollingBase.scala:28:66") } // And(x6077,x6078)
    val x6080 = withCtrl(x6091) { OpDef(op=BitAnd, inputs=List(x6079, b3464)).name("x6080").srcCtx("UnrollingBase.scala:28:66") } // And(x6079,b3464)
    val x6081 = withCtrl(x6091) { LoadBanks(List(x5888_d0_b3), List(b3467, b3491, b3508, b3572)).name("x6081").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:43:33") } // ParSRAMLoad(x5888,List(List(b3467, b3491, b3508, b3572)),List(x6080))
    val x6082 = withCtrl(x6091) { x6081 } // VectorApply(x6081,0)
    val x6083 = withCtrl(x6091) { ReadMem(x6013_d3).name("x6083").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:50:14") } // RegRead(x6013)
    val x6084 = withCtrl(x6091) { OpDef(op=FixMul, inputs=List(x6082, x6083)).name("x6084").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:43:71:reX") } // FixMul(x6082,x6083)
    val x6085 = withCtrl(x6091) { LoadBanks(List(x5925_d0_b3), List(b3467, b3491, b3508, b3572)).name("x6085").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:44:33") } // ParSRAMLoad(x5925,List(List(b3467, b3491, b3508, b3572)),List(x6080))
    val x6086 = withCtrl(x6091) { x6085 } // VectorApply(x6085,0)
    val x6087 = withCtrl(x6091) { ReadMem(x6014_d3).name("x6087").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:50:14") } // RegRead(x6014)
    val x6088 = withCtrl(x6091) { OpDef(op=FixMul, inputs=List(x6086, x6087)).name("x6088").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:44:71:reH") } // FixMul(x6086,x6087)
    val x6089 = withCtrl(x6091) { OpDef(op=FixAdd, inputs=List(x6084, x6088)).name("x6089").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:45:48") } // FixAdd(x6084,x6088)
    val x6090 = withCtrl(x6091) { StoreBanks(List(List(x6012_d0_b3)), List(b3508, b3572), x6089).name("x6090").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:45:42") } // ParSRAMStore(x6012,List(List(b3508, b3572)),List(x6089),List(x6080))
    def split1 = {
    val x6094 = withCtrl(x6111) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6094").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:50:14") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6095 = withCtrl(x6111) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x6095").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:50:14") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x6096 = withCtrl(x6111) { CounterChain(List(x6095,x6094)).name("x6096").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:50:14") } // CounterChainNew(ArrayBuffer(x6095, x6094))
    val x6110 = withCtrl(x6111) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6096).name("x6110").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:50:14") } // UnrolledForeach(List(),x6096,Block(Const(())),ArrayBuffer(List(b3591), List(b3592)),ArrayBuffer(List(b3593), List(b3594)))
    val b3591 = withCtrl(x6110) { CounterIter(x6095, Some(0)).name("b3591") } // b3591
    val b3593 = withCtrl(x6110) { Const(true).name("b3593") } // b3593
    val b3592 = withCtrl(x6110) { CounterIter(x6094, None).name("b3592") } // b3592
    val b3594 = withCtrl(x6110) { Const(true).name("b3594") } // b3594
    val x6097 = withCtrl(x6110) { OpDef(op=BitAnd, inputs=List(b3593, b3594)).name("x6097").srcCtx("UnrollingBase.scala:28:66") } // And(b3593,b3594)
    val x6098 = withCtrl(x6110) { OpDef(op=BitAnd, inputs=List(b3468, b3464)).name("x6098").srcCtx("UnrollingBase.scala:28:66") } // And(b3468,b3464)
    val x6099 = withCtrl(x6110) { OpDef(op=BitAnd, inputs=List(x6097, x6098)).name("x6099").srcCtx("UnrollingBase.scala:28:66") } // And(x6097,x6098)
    val x6100 = withCtrl(x6110) { LoadBanks(List(x6012_d0_b0, x6012_d0_b1, x6012_d0_b2, x6012_d0_b3), List(b3591, b3592)).name("x6100").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:50:14") } // ParSRAMLoad(x6012,List(ArrayBuffer(b3591, b3592)),List(x6099))
    val x6101 = withCtrl(x6110) { x6100 } // VectorApply(x6100,0)
    val x6102 = withCtrl(x6110) { LoadBanks(List(x5997_d1_b0), List(b3591, b3592)).name("x6102").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:50:14") } // ParSRAMLoad(x5997,List(ArrayBuffer(b3591, b3592)),List(x6099))
    val x6103 = withCtrl(x6110) { x6102 } // VectorApply(x6102,0)
    val x6104 = withCtrl(x6110) { OpDef(op=BitAnd, inputs=List(b3492, b3468)).name("x6104").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:50:14") } // And(b3492,b3468)
    val x6105 = withCtrl(x6110) { OpDef(op=BitAnd, inputs=List(x6104, b3464)).name("x6105").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:50:14") } // And(x6104,b3464)
    val x6106 = withCtrl(x6110) { OpDef(op=BitAnd, inputs=List(x6105, x6099)).name("x6106").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:50:14") } // And(x6105,x6099)
    val x6107 = withCtrl(x6110) { OpDef(op=FixEql, inputs=List(b3491, Const(0))).name("x6107").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:50:14") } // FixEql(b3491,Const(0))
    val x6108 = withCtrl(x6110) { OpDef(op=FixAdd, inputs=List(x6101, x6103)).name("x6108").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:50:16") } // FixAdd(x6101,x6103)
    val x6109 = withCtrl(x6110) { StoreBanks(List(List(x5997_d0_b0), List(x5997_d1_b0)), List(b3591, b3592), x6108).name("x6109").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:50:14") } // ParSRAMStore(x5997,List(ArrayBuffer(b3591, b3592)),List(x6108),List(x6099))
    antiDepsOf(x6109)=List(x6102)
    val x6112 = withCtrl(x6211) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x6112").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:64:29") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x6113 = withCtrl(x6211) { CounterChain(List(x6112)).name("x6113").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:64:35") } // CounterChainNew(List(x6112))
    val x6210 = withCtrl(x6211) { LoopController(style=MetaPipe, level=OuterControl, cchain=x6113).name("x6210").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:64:35") } // UnrolledForeach(List(b3468, b3464),x6113,Block(Const(())),List(List(b3612)),List(List(b3613)))
    val b3612 = withCtrl(x6210) { CounterIter(x6112, Some(0)).name("b3612") } // b3612
    val b3613 = withCtrl(x6210) { Const(true).name("b3613") } // b3613
    val x6114 = withCtrl(x6210) { FIFO(size=16).name("x6114").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:66:33:sigQ") } // x6114 = FIFONew(Const(16))
    isAccum(x6114) = false
    bufferDepthOf(x6114) = 2
    val x6115 = withCtrl(x6210) { FIFO(size=16).name("x6115").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:67:37:sigBiasQ") } // x6115 = FIFONew(Const(16))
    isAccum(x6115) = false
    bufferDepthOf(x6115) = 2
    val x6116 = withCtrl(x6210) { FIFO(size=16).name("x6116").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:68:33:actQ") } // x6116 = FIFONew(Const(16))
    isAccum(x6116) = false
    bufferDepthOf(x6116) = 2
    val x6117 = withCtrl(x6210) { FIFO(size=16).name("x6117").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:71:37:hUpdateQ") } // x6117 = FIFONew(Const(16))
    isAccum(x6117) = false
    bufferDepthOf(x6117) = 1
    val x6118 = withCtrl(x6210) { FIFO(size=16).name("x6118").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:72:37:cUpdateQ") } // x6118 = FIFONew(Const(16))
    isAccum(x6118) = false
    bufferDepthOf(x6118) = 1
    val x6119 = withCtrl(x6210) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6119").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:74:42") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6120 = withCtrl(x6210) { CounterChain(List(x6119)).name("x6120").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:74:56") } // CounterChainNew(List(x6119))
    val x6154 = withCtrl(x6210) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6120).name("x6154").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:74:56") } // UnrolledForeach(List(b3613, b3468, b3464),x6120,Block(Const(())),List(List(b3621)),List(List(b3622)))
    val b3621 = withCtrl(x6154) { CounterIter(x6119, None).name("b3621") } // b3621
    val b3622 = withCtrl(x6154) { Const(true).name("b3622") } // b3622
    val x6121 = withCtrl(x6154) { OpDef(op=BitAnd, inputs=List(b3622, b3613)).name("x6121").srcCtx("UnrollingBase.scala:28:66") } // And(b3622,b3613)
    val x6122 = withCtrl(x6154) { OpDef(op=BitAnd, inputs=List(b3468, b3464)).name("x6122").srcCtx("UnrollingBase.scala:28:66") } // And(b3468,b3464)
    val x6123 = withCtrl(x6154) { OpDef(op=BitAnd, inputs=List(x6121, x6122)).name("x6123").srcCtx("UnrollingBase.scala:28:66") } // And(x6121,x6122)
    val x6124 = withCtrl(x6154) { LoadBanks(List(x5997_d0_b0), List(b3612, b3621)).name("x6124").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:78:35:pEle") } // ParSRAMLoad(x5997,List(List(b3612, b3621)),List(x6123))
    val x6125 = withCtrl(x6154) { x6124 } // VectorApply(x6124,0)
    val x6126 = withCtrl(x6154) { OpDef(op=FixLt, inputs=List(x6125, Const(-2.5))).name("x6126").srcCtx("NonLinearity.scala:72:12") } // FixLt(x6125,Const(-2.5))
    val x6127 = withCtrl(x6154) { OpDef(op=FixLeq, inputs=List(Const(2.5), x6125)).name("x6127").srcCtx("NonLinearity.scala:72:34") } // FixLeq(Const(2.5),x6125)
    val x6128 = withCtrl(x6154) { OpDef(op=FixMul, inputs=List(Const(0.19999999), x6125)).name("x6128").srcCtx("NonLinearity.scala:72:56") } // FixMul(Const(0.199999988079071044921875),x6125)
    val x6129 = withCtrl(x6154) { OpDef(op=FixAdd, inputs=List(x6128, Const(0.5))).name("x6129").srcCtx("NonLinearity.scala:72:61") } // FixAdd(x6128,Const(0.5))
    val x6130 = withCtrl(x6154) { OpDef(op=MuxOp, inputs=List(x6127, Const(1.0), x6129)).name("x6130").srcCtx("NonLinearity.scala:72:30") } // Mux(x6127,Const(1),x6129)
    val x6131 = withCtrl(x6154) { OpDef(op=MuxOp, inputs=List(x6126, Const(0.0), x6130)).name("x6131").srcCtx("NonLinearity.scala:72:8:sigEle") } // Mux(x6126,Const(0),x6130)
    val x6132 = withCtrl(x6154) { OpDef(op=FixAdd, inputs=List(x6125, Const(1.0))).name("x6132").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:80:47") } // FixAdd(x6125,Const(1))
    val x6133 = withCtrl(x6154) { OpDef(op=FixLt, inputs=List(x6132, Const(-2.5))).name("x6133").srcCtx("NonLinearity.scala:72:12") } // FixLt(x6132,Const(-2.5))
    val x6134 = withCtrl(x6154) { OpDef(op=FixLeq, inputs=List(Const(2.5), x6132)).name("x6134").srcCtx("NonLinearity.scala:72:34") } // FixLeq(Const(2.5),x6132)
    val x6135 = withCtrl(x6154) { OpDef(op=FixMul, inputs=List(Const(0.19999999), x6132)).name("x6135").srcCtx("NonLinearity.scala:72:56") } // FixMul(Const(0.199999988079071044921875),x6132)
    val x6136 = withCtrl(x6154) { OpDef(op=FixAdd, inputs=List(x6135, Const(0.5))).name("x6136").srcCtx("NonLinearity.scala:72:61") } // FixAdd(x6135,Const(0.5))
    val x6137 = withCtrl(x6154) { OpDef(op=MuxOp, inputs=List(x6134, Const(1.0), x6136)).name("x6137").srcCtx("NonLinearity.scala:72:30") } // Mux(x6134,Const(1),x6136)
    val x6138 = withCtrl(x6154) { OpDef(op=MuxOp, inputs=List(x6133, Const(0.0), x6137)).name("x6138").srcCtx("NonLinearity.scala:72:8:sigBiasEle") } // Mux(x6133,Const(0),x6137)
    val x6139 = withCtrl(x6154) { OpDef(op=FixAbs, inputs=List(x6125)).name("x6139").srcCtx("NonLinearity.scala:51:20:absin") } // FixAbs(x6125)
    val x6140 = withCtrl(x6154) { OpDef(op=FixSra, inputs=List(x6139, Const(2))).name("x6140").srcCtx("NonLinearity.scala:52:22:div4") } // FixRsh(x6139,Const(2))
    val x6141 = withCtrl(x6154) { OpDef(op=FixAdd, inputs=List(x6140, Const(0.375))).name("x6141").srcCtx("NonLinearity.scala:53:19:li") } // FixAdd(x6140,Const(0.375))
    val x6142 = withCtrl(x6154) { OpDef(op=FixLt, inputs=List(Const(2.5), x6139)).name("x6142").srcCtx("NonLinearity.scala:54:28") } // FixLt(Const(2.5),x6139)
    val x6143 = withCtrl(x6154) { OpDef(op=FixLt, inputs=List(Const(0.5), x6139)).name("x6143").srcCtx("NonLinearity.scala:55:14") } // FixLt(Const(0.5),x6139)
    val x6144 = withCtrl(x6154) { OpDef(op=FixLt, inputs=List(x6139, Const(2.5))).name("x6144").srcCtx("NonLinearity.scala:55:31") } // FixLt(x6139,Const(2.5))
    val x6145 = withCtrl(x6154) { OpDef(op=BitAnd, inputs=List(x6143, x6144)).name("x6145").srcCtx("NonLinearity.scala:55:22") } // And(x6143,x6144)
    val x6146 = withCtrl(x6154) { OpDef(op=MuxOp, inputs=List(x6145, x6141, x6139)).name("x6146").srcCtx("NonLinearity.scala:55:10") } // Mux(x6145,x6141,x6139)
    val x6147 = withCtrl(x6154) { OpDef(op=MuxOp, inputs=List(x6142, Const(1.0), x6146)).name("x6147").srcCtx("NonLinearity.scala:54:21:absout") } // Mux(x6142,Const(1),x6146)
    val x6148 = withCtrl(x6154) { OpDef(op=FixNeg, inputs=List(x6147)).name("x6148").srcCtx("NonLinearity.scala:58:23:negout") } // FixNeg(x6147)
    val x6149 = withCtrl(x6154) { OpDef(op=FixLt, inputs=List(x6125, Const(0.0))).name("x6149").srcCtx("NonLinearity.scala:59:12") } // FixLt(x6125,Const(0))
    val x6150 = withCtrl(x6154) { OpDef(op=MuxOp, inputs=List(x6149, x6148, x6147)).name("x6150").srcCtx("NonLinearity.scala:59:8:actEle") } // Mux(x6149,x6148,x6147)
    val x6151_x6114 = withCtrl(x6154) { WriteMem(x6114, x6131).name("x6151_x6114").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:83:25") } // ParFIFOEnq(x6114,List(x6131),List(x6123))
    val x6152_x6115 = withCtrl(x6154) { WriteMem(x6115, x6138).name("x6152_x6115").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:85:29") } // ParFIFOEnq(x6115,List(x6138),List(x6123))
    val x6153_x6116 = withCtrl(x6154) { WriteMem(x6116, x6150).name("x6153_x6116").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:86:25") } // ParFIFOEnq(x6116,List(x6150),List(x6123))
    val x6209 = withCtrl(x6210) { UnitController(style=SeqPipe, level=OuterControl).name("x6209").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:89:20") } // UnitPipe(List(b3613, b3468, b3464),Block(Const(())))
    val x6155 = withCtrl(x6209) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6155").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:90:44") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6156 = withCtrl(x6209) { CounterChain(List(x6155)).name("x6156").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:90:58") } // CounterChainNew(List(x6155))
    val x6195 = withCtrl(x6209) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6156).name("x6195").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:90:58") } // UnrolledForeach(List(b3613, b3468, b3464),x6156,Block(Const(())),List(List(b3659)),List(List(b3660)))
    val b3659 = withCtrl(x6195) { CounterIter(x6155, None).name("b3659") } // b3659
    val b3660 = withCtrl(x6195) { Const(true).name("b3660") } // b3660
    val x6157 = withCtrl(x6195) { OpDef(op=BitAnd, inputs=List(b3660, b3613)).name("x6157").srcCtx("UnrollingBase.scala:28:66") } // And(b3660,b3613)
    val x6158 = withCtrl(x6195) { OpDef(op=BitAnd, inputs=List(b3468, b3464)).name("x6158").srcCtx("UnrollingBase.scala:28:66") } // And(b3468,b3464)
    val x6159 = withCtrl(x6195) { OpDef(op=BitAnd, inputs=List(x6157, x6158)).name("x6159").srcCtx("UnrollingBase.scala:28:66") } // And(x6157,x6158)
    val x6160 = withCtrl(x6195) { ReadMem(x6114).name("x6160").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:91:40:sigEle") } // ParFIFODeq(x6114,List(x6159))
    val x6161 = withCtrl(x6195) { x6160 } // VectorApply(x6160,0)
    val x6162 = withCtrl(x6195) { ReadMem(x6115).name("x6162").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:92:48:sigBiasEle") } // ParFIFODeq(x6115,List(x6159))
    val x6163 = withCtrl(x6195) { x6162 } // VectorApply(x6162,0)
    val x6164 = withCtrl(x6195) { ReadMem(x6116).name("x6164").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:93:40:actEle") } // ParFIFODeq(x6116,List(x6159))
    val x6165 = withCtrl(x6195) { x6164 } // VectorApply(x6164,0)
    val x6166 = withCtrl(x6195) { LoadBanks(List(x5838_d0_b0), List(b3467, b3659)).name("x6166").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:95:34:cLast") } // ParSRAMLoad(x5838,List(List(b3467, b3659)),List(x6159))
    val x6167 = withCtrl(x6195) { x6166 } // VectorApply(x6166,0)
    val x6168 = withCtrl(x6195) { OpDef(op=FixAdd, inputs=List(x6167, x6165)).name("x6168").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:96:40:cNewMult") } // FixAdd(x6167,x6165)
    val x6169 = withCtrl(x6195) { OpDef(op=FixMul, inputs=List(x6163, x6167)).name("x6169").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:99:48") } // FixMul(x6163,x6167)
    val x6170 = withCtrl(x6195) { OpDef(op=FixAdd, inputs=List(x6169, x6167)).name("x6170").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:99:56:cNewMultAdd") } // FixAdd(x6169,x6167)
    val x6171 = withCtrl(x6195) { OpDef(op=FixEql, inputs=List(b3612, Const(0))).name("x6171").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:138:38") } // FixEql(b3612,Const(0))
    val x6172 = withCtrl(x6195) { OpDef(op=FixEql, inputs=List(b3612, Const(2))).name("x6172").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:144:38") } // FixEql(b3612,Const(2))
    val x6173 = withCtrl(x6195) { OpDef(op=MuxOp, inputs=List(x6172, x6170, x6167)).name("x6173").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:104:28") } // Mux(x6172,x6170,x6167)
    val x6174 = withCtrl(x6195) { OpDef(op=MuxOp, inputs=List(x6171, x6168, x6173)).name("x6174").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:103:26") } // Mux(x6171,x6168,x6173)
    val x6175 = withCtrl(x6195) { OpDef(op=MuxOp, inputs=List(x6171, x6161, x6174)).name("x6175").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:102:24") } // Mux(x6171,x6161,x6174)
    val x6176_x6118 = withCtrl(x6195) { WriteMem(x6118, x6175).name("x6176_x6118").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:101:32") } // ParFIFOEnq(x6118,List(x6175),List(x6159))
    val x6177 = withCtrl(x6195) { OpDef(op=FixAbs, inputs=List(x6170)).name("x6177").srcCtx("NonLinearity.scala:51:20:absin") } // FixAbs(x6170)
    val x6178 = withCtrl(x6195) { OpDef(op=FixSra, inputs=List(x6177, Const(2))).name("x6178").srcCtx("NonLinearity.scala:52:22:div4") } // FixRsh(x6177,Const(2))
    val x6179 = withCtrl(x6195) { OpDef(op=FixAdd, inputs=List(x6178, Const(0.375))).name("x6179").srcCtx("NonLinearity.scala:53:19:li") } // FixAdd(x6178,Const(0.375))
    val x6180 = withCtrl(x6195) { OpDef(op=FixLt, inputs=List(Const(2.5), x6177)).name("x6180").srcCtx("NonLinearity.scala:54:28") } // FixLt(Const(2.5),x6177)
    val x6181 = withCtrl(x6195) { OpDef(op=FixLt, inputs=List(Const(0.5), x6177)).name("x6181").srcCtx("NonLinearity.scala:55:14") } // FixLt(Const(0.5),x6177)
    val x6182 = withCtrl(x6195) { OpDef(op=FixLt, inputs=List(x6177, Const(2.5))).name("x6182").srcCtx("NonLinearity.scala:55:31") } // FixLt(x6177,Const(2.5))
    val x6183 = withCtrl(x6195) { OpDef(op=BitAnd, inputs=List(x6181, x6182)).name("x6183").srcCtx("NonLinearity.scala:55:22") } // And(x6181,x6182)
    val x6184 = withCtrl(x6195) { OpDef(op=MuxOp, inputs=List(x6183, x6179, x6177)).name("x6184").srcCtx("NonLinearity.scala:55:10") } // Mux(x6183,x6179,x6177)
    val x6185 = withCtrl(x6195) { OpDef(op=MuxOp, inputs=List(x6180, Const(1.0), x6184)).name("x6185").srcCtx("NonLinearity.scala:54:21:absout") } // Mux(x6180,Const(1),x6184)
    val x6186 = withCtrl(x6195) { OpDef(op=FixNeg, inputs=List(x6185)).name("x6186").srcCtx("NonLinearity.scala:58:23:negout") } // FixNeg(x6185)
    val x6187 = withCtrl(x6195) { OpDef(op=FixLt, inputs=List(x6170, Const(0.0))).name("x6187").srcCtx("NonLinearity.scala:59:12") } // FixLt(x6170,Const(0))
    val x6188 = withCtrl(x6195) { OpDef(op=MuxOp, inputs=List(x6187, x6186, x6185)).name("x6188").srcCtx("NonLinearity.scala:59:8") } // Mux(x6187,x6186,x6185)
    val x6189 = withCtrl(x6195) { OpDef(op=FixAdd, inputs=List(x6188, x6161)).name("x6189").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:112:54:hNew") } // FixAdd(x6188,x6161)
    val x6190 = withCtrl(x6195) { LoadBanks(List(x5863_d0_b0), List(b3467, b3659)).name("x6190").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:113:34:hLast") } // ParSRAMLoad(x5863,List(List(b3467, b3659)),List(x6159))
    val x6191 = withCtrl(x6195) { x6190 } // VectorApply(x6190,0)
    val x6192 = withCtrl(x6195) { OpDef(op=FixEql, inputs=List(b3612, Const(3))).name("x6192").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:147:38") } // FixEql(b3612,Const(3))
    val x6193 = withCtrl(x6195) { OpDef(op=MuxOp, inputs=List(x6192, x6189, x6191)).name("x6193").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:115:35:update") } // Mux(x6192,x6189,x6191)
    val x6194_x6117 = withCtrl(x6195) { WriteMem(x6117, x6193).name("x6194_x6117").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:116:31") } // ParFIFOEnq(x6117,List(x6193),List(x6159))
    val x6196 = withCtrl(x6209) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6196").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:119:44") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6197 = withCtrl(x6209) { CounterChain(List(x6196)).name("x6197").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:119:58") } // CounterChainNew(List(x6196))
    val x6208 = withCtrl(x6209) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6197).name("x6208").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:119:58") } // UnrolledForeach(List(b3613, b3468, b3464),x6197,Block(Const(())),List(List(b3702)),List(List(b3703)))
    val b3702 = withCtrl(x6208) { CounterIter(x6196, None).name("b3702") } // b3702
    val b3703 = withCtrl(x6208) { Const(true).name("b3703") } // b3703
    val x6198 = withCtrl(x6208) { OpDef(op=BitAnd, inputs=List(b3703, b3613)).name("x6198").srcCtx("UnrollingBase.scala:28:66") } // And(b3703,b3613)
    val x6199 = withCtrl(x6208) { OpDef(op=BitAnd, inputs=List(b3468, b3464)).name("x6199").srcCtx("UnrollingBase.scala:28:66") } // And(b3468,b3464)
    val x6200 = withCtrl(x6208) { OpDef(op=BitAnd, inputs=List(x6198, x6199)).name("x6200").srcCtx("UnrollingBase.scala:28:66") } // And(x6198,x6199)
    val x6201 = withCtrl(x6208) { ReadMem(x6117).name("x6201").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:120:41:update") } // ParFIFODeq(x6117,List(x6200))
    val x6202 = withCtrl(x6208) { x6201 } // VectorApply(x6201,0)
    val x6203 = withCtrl(x6208) { StoreBanks(List(List(x5863_d0_b0), List(x5863_d1_b0)), List(b3467, b3702), x6202).name("x6203").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:121:44") } // ParSRAMStore(x5863,List(List(b3467, b3702)),List(x6202),List(x6200))
    val x6204 = withCtrl(x6208) { StoreBanks(List(List(x5813_d0_b0), List(x5813_d1_b0)), List(b3463, b3702), x6202).name("x6204").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:122:43") } // ParSRAMStore(x5813,List(List(b3463, b3702)),List(x6202),List(x6200))
    val x6205 = withCtrl(x6208) { ReadMem(x6118).name("x6205").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:123:55") } // ParFIFODeq(x6118,List(x6200))
    val x6206 = withCtrl(x6208) { x6205 } // VectorApply(x6205,0)
    val x6207 = withCtrl(x6208) { StoreBanks(List(List(x5838_d0_b0)), List(b3467, b3702), x6206).name("x6207").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:123:44") } // ParSRAMStore(x5838,List(List(b3467, b3702)),List(x6206),List(x6200))
    val x6214 = withCtrl(x6242) { Counter(min=Const(0), max=Const(8), step=Const(1), par=1).name("x6214").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:131:49") } // CounterNew(Const(0),Const(8),Const(1),Const(1))
    val x6215 = withCtrl(x6242) { CounterChain(List(x6214)).name("x6215").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:131:49") } // CounterChainNew(List(x6214))
    val x6241 = withCtrl(x6242) { LoopController(style=StreamPipe, level=OuterControl, cchain=x6215).name("x6241").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:131:49") } // UnrolledForeach(List(Const(true)),x6215,Block(Const(())),List(List(b3722)),List(List(b3723)))
    val b3722 = withCtrl(x6241) { CounterIter(x6214, Some(0)).name("b3722") } // b3722
    val b3723 = withCtrl(x6241) { Const(true).name("b3723") } // b3723
    val b6277 = withCtrl(x6241) { StreamOut(field="offset").name("b6277").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:131:49") } // x6216 = StreamOutNew(BurstCmdBus)
    isAccum(b6277) = false
    bufferDepthOf(b6277) = 1
    val b6278 = withCtrl(x6241) { StreamOut(field="size").name("b6278").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:131:49") } // x6216 = StreamOutNew(BurstCmdBus)
    isAccum(b6278) = false
    bufferDepthOf(b6278) = 1
    val x6217 = withCtrl(x6241) { StreamOut(field="data").name("x6217").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:131:49") } // x6217 = StreamOutNew(BurstFullDataBus())
    isAccum(x6217) = false
    bufferDepthOf(x6217) = 1
    val x6218 = withCtrl(x6241) { StreamIn(field="ack").name("x6218").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:131:49") } // x6218 = StreamInNew(BurstAckBus)
    isAccum(x6218) = false
    bufferDepthOf(x6218) = 1
    val x6229 = withCtrl(x6241) { UnitController(style=SeqPipe, level=InnerControl).name("x6229").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:131:49") } // UnitPipe(List(b3723),Block(x6228))
    val x6219 = withCtrl(x6229) { b3722 } // FixConvert(b3722,TRUE,_32,_0) (Same Type. No op)
    val x6220 = withCtrl(x6229) { OpDef(op=FixSla, inputs=List(x6219, Const(7))).name("x6220").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:131:49") } // FixLsh(x6219,Const(7))
    val x6221 = withCtrl(x6229) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x6222 = withCtrl(x6229) { OpDef(op=FixAdd, inputs=List(x6220, x6221)).name("x6222").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:131:49") } // FixAdd(x6220,x6221)
    val x6223 = withCtrl(x6229) { OpDef(op=FixSla, inputs=List(x6222, Const(2))).name("x6223").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:131:49") } // FixLsh(x6222,Const(2))
    val x6224 = withCtrl(x6229) { x6223 } // FixConvert(x6223,TRUE,_64,_0)
    val x6225 = withCtrl(x6229) { DramAddress(x5812).name("x6225").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:131:49") } // GetDRAMAddress(x5812)
    val x6227_x6226 = withCtrl(x6229) { OpDef(op=FixAdd, inputs=List(x6224, x6225)).name("x6227_x6226").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:131:49") } // FixAdd(x6224,x6225)
    // x6227 = SimpleStruct(ArrayBuffer((offset,x6226), (size,Const(512)), (isLoad,Const(false))))
    val x6228_b6279_b6277 = withCtrl(x6229) { WriteMem(b6277, x6227_x6226).name("x6228_b6279_b6277").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:131:49") } // StreamWrite(x6216,x6227,b3723)
    val x6228_b6280_b6278 = withCtrl(x6229) { WriteMem(b6278, Const(512)).name("x6228_b6280_b6278").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:131:49") } // StreamWrite(x6216,x6227,b3723)
    val x6230 = withCtrl(x6241) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6230").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:131:49") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6231 = withCtrl(x6241) { CounterChain(List(x6230)).name("x6231").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:131:49") } // CounterChainNew(List(x6230))
    val x6237 = withCtrl(x6241) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6231).name("x6237").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:131:49") } // UnrolledForeach(List(b3723),x6231,Block(Const(())),List(List(b3740)),List(List(b3741)))
    val b3740 = withCtrl(x6237) { CounterIter(x6230, None).name("b3740") } // b3740
    val b3741 = withCtrl(x6237) { Const(true).name("b3741") } // b3741
    val x6232 = withCtrl(x6237) { OpDef(op=BitAnd, inputs=List(b3741, b3723)).name("x6232").srcCtx("UnrollingBase.scala:28:66") } // And(b3741,b3723)
    val x6233 = withCtrl(x6237) { LoadBanks(List(x5813_d0_b0), List(b3722, b3740)).name("x6233").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:131:49") } // ParSRAMLoad(x5813,List(List(b3722, b3740)),List(x6232))
    val x6235_x6234 = withCtrl(x6237) { x6233 } // VectorApply(x6233,0)
    // x6235 = SimpleStruct(ArrayBuffer((_1,x6234), (_2,Const(true))))
    val x6236_x6236_x6217 = withCtrl(x6237) { WriteMem(x6217, x6235_x6234).name("x6236_x6236_x6217").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:131:49") } // ParStreamWrite(x6217,List(x6235),List(x6232))
    val x6238 = withCtrl(x6241) { FringeDenseStore(dram=List(x5812), cmdStream=List(b6277, b6278), dataStream=List(x6217), ackStream=List(x6218)).name("x6238").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:131:49") } // FringeDenseStore(x5812,x6216,x6217,x6218)
    val x6240 = withCtrl(x6241) { UnitController(style=SeqPipe, level=InnerControl).name("x6240").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:131:49") } // UnitPipe(List(b3723),Block(Const(())))
    val x6239_x6239 = withCtrl(x6240) { ReadMem(x6218).name("x6239_x6239").srcCtx("CoreMemFoldGateManualFoldLSTM.scala:131:49") } // StreamRead(x6218,b3723)
    }; split1
    
  }
}
