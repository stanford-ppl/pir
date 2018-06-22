import pir._
import pir.node._
import arch._
import prism.enums._

object lenetUnit extends PIRApp {
  def main(implicit design:PIRDesign) = {
    import design.pirmeta._
    val x5705 = DRAM().name("x5705").ctrl(top).srcCtx("lenetUnit.scala:22:26:c0_DRAM") // x5705 = DRAMNew(ArrayBuffer(Const(20), Const(1), Const(32)),Const(0))
    val x5706 = DRAM().name("x5706").ctrl(top).srcCtx("lenetUnit.scala:23:26:i0_DRAM") // x5706 = DRAMNew(ArrayBuffer(Const(17), Const(28), Const(32)),Const(0))
    val x5707 = DRAM().name("x5707").ctrl(top).srcCtx("lenetUnit.scala:24:26:c1_DRAM") // x5707 = DRAMNew(ArrayBuffer(Const(32)),Const(0))
    val x5708 = DRAM().name("x5708").ctrl(top).srcCtx("lenetUnit.scala:25:26:c2_DRAM") // x5708 = DRAMNew(ArrayBuffer(Const(50), Const(512)),Const(0))
    val x5709 = DRAM().name("x5709").ctrl(top).srcCtx("lenetUnit.scala:26:26:c3_DRAM") // x5709 = DRAMNew(ArrayBuffer(Const(64)),Const(0))
    val x5710 = DRAM().name("x5710").ctrl(top).srcCtx("lenetUnit.scala:27:26:c4_DRAM") // x5710 = DRAMNew(ArrayBuffer(Const(100), Const(4000)),Const(0))
    val x5711 = DRAM().name("x5711").ctrl(top).srcCtx("lenetUnit.scala:29:26:c5_DRAM") // x5711 = DRAMNew(ArrayBuffer(Const(512)),Const(0))
    val x5712 = DRAM().name("x5712").ctrl(top).srcCtx("lenetUnit.scala:30:26:c6_DRAM") // x5712 = DRAMNew(ArrayBuffer(Const(10), Const(512)),Const(0))
    val x5713 = DRAM().name("x5713").ctrl(top).srcCtx("lenetUnit.scala:32:26:c7_DRAM") // x5713 = DRAMNew(ArrayBuffer(Const(32)),Const(0))
    val x5714 = DRAM().name("x5714").ctrl(top).srcCtx("lenetUnit.scala:33:28:tmp5_DRAM") // x5714 = DRAMNew(ArrayBuffer(Const(17), Const(32)),Const(0))
    val x6081 = UnitController(style=SeqPipe, level=OuterControl).name("x6081").ctrl(top).srcCtx("lenetUnit.scala:61:11") // Hwblock(Block(Const(())),false)
    val x5834_d0_b0 = SRAM(size=32, banking=Strided(banks=1, stride=1)).name("x5834_d0_b0").ctrl(x6081).srcCtx("lenetUnit.scala:63:28:c1_SRAM") // x5834 = SRAMNew(ArrayBuffer(Const(32)))
    isAccum(x5834_d0_b0) = false
    bufferDepthOf(x5834_d0_b0) = 1
    val x5852 = UnitController(style=StreamPipe, level=OuterControl).name("x5852").ctrl(x6081).srcCtx("lenetUnit.scala:64:15") // UnitPipe(List(Const(true)),Block(Const(())))
    val b6119 = StreamOut(field="offset").name("b6119").ctrl(x5852).srcCtx("lenetUnit.scala:64:15") // x5835 = StreamOutNew(BurstCmdBus)
    isAccum(b6119) = false
    bufferDepthOf(b6119) = 1
    val b6120 = StreamOut(field="size").name("b6120").ctrl(x5852).srcCtx("lenetUnit.scala:64:15") // x5835 = StreamOutNew(BurstCmdBus)
    isAccum(b6120) = false
    bufferDepthOf(b6120) = 1
    val x5836 = StreamIn(field="data").name("x5836").ctrl(x5852).srcCtx("lenetUnit.scala:64:15") // x5836 = StreamInNew(BurstDataBus())
    isAccum(x5836) = false
    bufferDepthOf(x5836) = 1
    val x5844 = UnitController(style=SeqPipe, level=InnerControl).name("x5844").ctrl(x5852).srcCtx("lenetUnit.scala:64:15") // UnitPipe(List(Const(true)),Block(x5843))
    val x5837 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x5838 = OpDef(op=FixSla, inputs=List(x5837, Const(1))).name("x5838").ctrl(x5844).srcCtx("lenetUnit.scala:64:15") // FixLsh(x5837,Const(1))
    val x5839 = x5838 // FixConvert(x5838,TRUE,_64,_0)
    val x5840 = DramAddress(x5707).name("x5840").ctrl(x5844).srcCtx("lenetUnit.scala:64:15") // GetDRAMAddress(x5707)
    val x5842_x5841 = OpDef(op=FixAdd, inputs=List(x5839, x5840)).name("x5842_x5841").ctrl(x5844).srcCtx("lenetUnit.scala:64:15") // FixAdd(x5839,x5840)
    // x5842 = SimpleStruct(ArrayBuffer((offset,x5841), (size,Const(64)), (isLoad,Const(true))))
    val x5843_b6121_b6119 = WriteMem(b6119, x5842_x5841).name("x5843_b6121_b6119").ctrl(x5844).srcCtx("lenetUnit.scala:64:15") // StreamWrite(x5835,x5842,Const(true))
    val x5843_b6122_b6120 = WriteMem(b6120, Const(64)).name("x5843_b6122_b6120").ctrl(x5844).srcCtx("lenetUnit.scala:64:15") // StreamWrite(x5835,x5842,Const(true))
    val x5845 = FringeDenseLoad(dram=List(x5707), cmdStream=List(b6119, b6120), dataStream=List(x5836)).name("x5845").ctrl(x5852).srcCtx("lenetUnit.scala:64:15") // FringeDenseLoad(x5707,x5835,x5836)
    val x5846 = Counter(min=Const(0), max=Const(32), step=Const(1), par=1).name("x5846").ctrl(x5852).srcCtx("lenetUnit.scala:64:15") // CounterNew(Const(0),Const(32),Const(1),Const(1))
    val x5847 = CounterChain(List(x5846)).name("x5847").ctrl(x5852).srcCtx("lenetUnit.scala:64:15") // CounterChainNew(List(x5846))
    val x5851 = LoopController(style=InnerPipe, level=InnerControl, cchain=x5847).name("x5851").ctrl(x5852).srcCtx("lenetUnit.scala:64:15") // UnrolledForeach(List(Const(true)),x5847,Block(Const(())),List(List(b3607)),List(List(b3608)))
    val b3607 = CounterIter(x5846, None).name("b3607").ctrl(x5851) // b3607
    val b3608 = Const(true).name("b3608").ctrl(x5851) // b3608
    val x5848_x5848 = ReadMem(x5836).name("x5848_x5848").ctrl(x5851).srcCtx("lenetUnit.scala:64:15") // ParStreamRead(x5836,List(b3608))
    val x5849_x5849 = x5848_x5848 // x5849 = VectorApply(x5848,0)
    val x5850 = StoreBanks(List(x5834_d0_b0), List(b3607), x5849_x5849).name("x5850").ctrl(x5851).srcCtx("lenetUnit.scala:64:15") // ParSRAMStore(x5834,List(List(b3607)),List(x5849),List(b3608))
    val x5853 = Counter(min=Const(0), max=Const(17), step=Const(1), par=1).name("x5853").ctrl(x6081).srcCtx("lenetUnit.scala:75:31") // CounterNew(Const(0),Const(17),Const(1),Const(1))
    val x5854 = CounterChain(List(x5853)).name("x5854").ctrl(x6081).srcCtx("lenetUnit.scala:75:38") // CounterChainNew(List(x5853))
    val x6080 = LoopController(style=MetaPipe, level=OuterControl, cchain=x5854).name("x6080").ctrl(x6081).srcCtx("lenetUnit.scala:75:38") // UnrolledForeach(List(Const(true)),x5854,Block(Const(())),List(List(b3616)),List(List(b3617)))
    val b3616 = CounterIter(x5853, Some(0)).name("b3616").ctrl(x6080) // b3616
    val b3617 = Const(true).name("b3617").ctrl(x6080) // b3617
    val x5855_d0_b0 = SRAM(size=896, banking=Strided(banks=1, stride=32)).name("x5855_d0_b0").ctrl(x6080).srcCtx("lenetUnit.scala:78:30:i0_SRAM") // x5855 = SRAMNew(ArrayBuffer(Const(28), Const(32)))
    isAccum(x5855_d0_b0) = false
    bufferDepthOf(x5855_d0_b0) = 2
    val x5857 = UnitController(style=SeqPipe, level=InnerControl).name("x5857").ctrl(x6080).srcCtx("lenetUnit.scala:75:38") // UnitPipe(List(b3617),Block(Const(())))
    val x5856 = OpDef(op=FixAdd, inputs=List(b3616, Const(1))).name("x5856").ctrl(x5857).srcCtx("lenetUnit.scala:79:29") // FixAdd(b3616,Const(1))
    val x5858 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x5858").ctrl(x6080).srcCtx("lenetUnit.scala:79:17") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x5859 = Counter(min=Const(0), max=Const(28), step=Const(1), par=1).name("x5859").ctrl(x6080).srcCtx("lenetUnit.scala:79:17") // CounterNew(Const(0),Const(28),Const(1),Const(1))
    val x5860 = CounterChain(List(x5858,x5859)).name("x5860").ctrl(x6080).srcCtx("lenetUnit.scala:79:17") // CounterChainNew(List(x5858, x5859))
    val x5890 = LoopController(style=StreamPipe, level=OuterControl, cchain=x5860).name("x5890").ctrl(x6080).srcCtx("lenetUnit.scala:79:17") // UnrolledForeach(List(b3617),x5860,Block(Const(())),List(List(b3624), List(b3625)),List(List(b3626), List(b3627)))
    val b3624 = CounterIter(x5858, Some(0)).name("b3624").ctrl(x5890) // b3624
    val b3626 = Const(true).name("b3626").ctrl(x5890) // b3626
    val b3625 = CounterIter(x5859, Some(0)).name("b3625").ctrl(x5890) // b3625
    val b3627 = Const(true).name("b3627").ctrl(x5890) // b3627
    val b6123 = StreamOut(field="offset").name("b6123").ctrl(x5890).srcCtx("lenetUnit.scala:79:17") // x5861 = StreamOutNew(BurstCmdBus)
    isAccum(b6123) = false
    bufferDepthOf(b6123) = 1
    val b6124 = StreamOut(field="size").name("b6124").ctrl(x5890).srcCtx("lenetUnit.scala:79:17") // x5861 = StreamOutNew(BurstCmdBus)
    isAccum(b6124) = false
    bufferDepthOf(b6124) = 1
    val x5862 = StreamIn(field="data").name("x5862").ctrl(x5890).srcCtx("lenetUnit.scala:79:17") // x5862 = StreamInNew(BurstDataBus())
    isAccum(x5862) = false
    bufferDepthOf(x5862) = 1
    val x5879 = UnitController(style=SeqPipe, level=InnerControl).name("x5879").ctrl(x5890).srcCtx("lenetUnit.scala:79:17") // UnitPipe(List(b3626, b3627, b3617),Block(x5878))
    val x5863 = OpDef(op=FixAdd, inputs=List(b3616, b3624)).name("x5863").ctrl(x5879).srcCtx("lenetUnit.scala:79:17") // FixAdd(b3616,b3624)
    val x5864 = x5863 // FixConvert(x5863,TRUE,_32,_0) (Same Type. No op)
    val x5865 = OpDef(op=FixMul, inputs=List(x5864, Const(896))).name("x5865").ctrl(x5879).srcCtx("lenetUnit.scala:79:17") // FixMul(x5864,Const(896))
    val x5866 = b3625 // FixConvert(b3625,TRUE,_32,_0) (Same Type. No op)
    val x5867 = OpDef(op=FixSla, inputs=List(x5866, Const(5))).name("x5867").ctrl(x5879).srcCtx("lenetUnit.scala:79:17") // FixLsh(x5866,Const(5))
    val x5868 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x5869 = OpDef(op=FixAdd, inputs=List(x5865, x5867)).name("x5869").ctrl(x5879).srcCtx("lenetUnit.scala:79:17") // FixAdd(x5865,x5867)
    val x5870 = OpDef(op=FixAdd, inputs=List(x5869, x5868)).name("x5870").ctrl(x5879).srcCtx("lenetUnit.scala:79:17") // FixAdd(x5869,x5868)
    val x5871 = OpDef(op=FixSla, inputs=List(x5870, Const(1))).name("x5871").ctrl(x5879).srcCtx("lenetUnit.scala:79:17") // FixLsh(x5870,Const(1))
    val x5872 = x5871 // FixConvert(x5871,TRUE,_64,_0)
    val x5873 = DramAddress(x5706).name("x5873").ctrl(x5879).srcCtx("lenetUnit.scala:79:17") // GetDRAMAddress(x5706)
    val x5875_x5874 = OpDef(op=FixAdd, inputs=List(x5872, x5873)).name("x5875_x5874").ctrl(x5879).srcCtx("lenetUnit.scala:79:17") // FixAdd(x5872,x5873)
    // x5875 = SimpleStruct(ArrayBuffer((offset,x5874), (size,Const(64)), (isLoad,Const(true))))
    val x5876 = OpDef(op=BitAnd, inputs=List(b3626, b3627)).name("x5876").ctrl(x5879).srcCtx("UnrollingBase.scala:28:66") // And(b3626,b3627)
    val x5877 = OpDef(op=BitAnd, inputs=List(x5876, b3617)).name("x5877").ctrl(x5879).srcCtx("UnrollingBase.scala:28:66") // And(x5876,b3617)
    val x5878_b6125_b6123 = WriteMem(b6123, x5875_x5874).name("x5878_b6125_b6123").ctrl(x5879).srcCtx("lenetUnit.scala:79:17") // StreamWrite(x5861,x5875,x5877)
    val x5878_b6126_b6124 = WriteMem(b6124, Const(64)).name("x5878_b6126_b6124").ctrl(x5879).srcCtx("lenetUnit.scala:79:17") // StreamWrite(x5861,x5875,x5877)
    val x5880 = FringeDenseLoad(dram=List(x5706), cmdStream=List(b6123, b6124), dataStream=List(x5862)).name("x5880").ctrl(x5890).srcCtx("lenetUnit.scala:79:17") // FringeDenseLoad(x5706,x5861,x5862)
    val x5881 = Counter(min=Const(0), max=Const(32), step=Const(1), par=1).name("x5881").ctrl(x5890).srcCtx("lenetUnit.scala:79:17") // CounterNew(Const(0),Const(32),Const(1),Const(1))
    val x5882 = CounterChain(List(x5881)).name("x5882").ctrl(x5890).srcCtx("lenetUnit.scala:79:17") // CounterChainNew(List(x5881))
    val x5889 = LoopController(style=InnerPipe, level=InnerControl, cchain=x5882).name("x5889").ctrl(x5890).srcCtx("lenetUnit.scala:79:17") // UnrolledForeach(List(b3626, b3627, b3617),x5882,Block(Const(())),List(List(b3650)),List(List(b3651)))
    val b3650 = CounterIter(x5881, None).name("b3650").ctrl(x5889) // b3650
    val b3651 = Const(true).name("b3651").ctrl(x5889) // b3651
    val x5883 = OpDef(op=BitAnd, inputs=List(b3651, b3626)).name("x5883").ctrl(x5889).srcCtx("UnrollingBase.scala:28:66") // And(b3651,b3626)
    val x5884 = OpDef(op=BitAnd, inputs=List(b3627, b3617)).name("x5884").ctrl(x5889).srcCtx("UnrollingBase.scala:28:66") // And(b3627,b3617)
    val x5885 = OpDef(op=BitAnd, inputs=List(x5883, x5884)).name("x5885").ctrl(x5889).srcCtx("UnrollingBase.scala:28:66") // And(x5883,x5884)
    val x5886_x5886 = ReadMem(x5862).name("x5886_x5886").ctrl(x5889).srcCtx("lenetUnit.scala:79:17") // ParStreamRead(x5862,List(x5885))
    val x5887_x5887 = x5886_x5886 // x5887 = VectorApply(x5886,0)
    val x5888 = StoreBanks(List(x5855_d0_b0), List(b3625, b3650), x5887_x5887).name("x5888").ctrl(x5889).srcCtx("lenetUnit.scala:79:17") // ParSRAMStore(x5855,List(List(b3625, b3650)),List(x5887),List(x5885))
    val x5891_d0_b0 = SRAM(size=2880, banking=Strided(banks=1, stride=144)).name("x5891_d0_b0").ctrl(x6080).srcCtx("lenetUnit.scala:82:32:tmp1_SRAM") // x5891 = SRAMNew(ArrayBuffer(Const(20), Const(12), Const(12)))
    isAccum(x5891_d0_b0) = false
    bufferDepthOf(x5891_d0_b0) = 2
    val x5892 = Counter(min=Const(0), max=Const(20), step=Const(1), par=1).name("x5892").ctrl(x6080).srcCtx("lenetUnit.scala:83:25") // CounterNew(Const(0),Const(20),Const(1),Const(1))
    val x5893 = CounterChain(List(x5892)).name("x5893").ctrl(x6080).srcCtx("lenetUnit.scala:83:32") // CounterChainNew(List(x5892))
    val x6005 = LoopController(style=MetaPipe, level=OuterControl, cchain=x5893).name("x6005").ctrl(x6080).srcCtx("lenetUnit.scala:83:32") // UnrolledForeach(List(b3617),x5893,Block(Const(())),List(List(b3663)),List(List(b3664)))
    val b3663 = CounterIter(x5892, Some(0)).name("b3663").ctrl(x6005) // b3663
    val b3664 = Const(true).name("b3664").ctrl(x6005) // b3664
    val x5894_d0_b0 = RegFile(size=32, inits=None).name("x5894_d0_b0").ctrl(x6005).srcCtx("lenetUnit.scala:91:33:c0_RF") // x5894 = RegFileNew(ArrayBuffer(Const(32)),None) banking:Strided(banks=1, stride=1)
    isAccum(x5894_d0_b0) = false
    bufferDepthOf(x5894_d0_b0) = 2
    val x5896 = UnitController(style=SeqPipe, level=InnerControl).name("x5896").ctrl(x6005).srcCtx("lenetUnit.scala:83:32") // UnitPipe(List(b3664, b3617),Block(Const(())))
    val x5895 = OpDef(op=FixAdd, inputs=List(b3663, Const(1))).name("x5895").ctrl(x5896).srcCtx("lenetUnit.scala:92:29") // FixAdd(b3663,Const(1))
    val x5897 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x5897").ctrl(x6005).srcCtx("lenetUnit.scala:92:17") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x5898 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x5898").ctrl(x6005).srcCtx("lenetUnit.scala:92:17") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x5899 = CounterChain(List(x5897,x5898)).name("x5899").ctrl(x6005).srcCtx("lenetUnit.scala:92:17") // CounterChainNew(List(x5897, x5898))
    val x5931 = LoopController(style=StreamPipe, level=OuterControl, cchain=x5899).name("x5931").ctrl(x6005).srcCtx("lenetUnit.scala:92:17") // UnrolledForeach(List(b3664, b3617),x5899,Block(Const(())),List(List(b3671), List(b3672)),List(List(b3673), List(b3674)))
    val b3671 = CounterIter(x5897, Some(0)).name("b3671").ctrl(x5931) // b3671
    val b3673 = Const(true).name("b3673").ctrl(x5931) // b3673
    val b3672 = CounterIter(x5898, Some(0)).name("b3672").ctrl(x5931) // b3672
    val b3674 = Const(true).name("b3674").ctrl(x5931) // b3674
    val b6127 = StreamOut(field="offset").name("b6127").ctrl(x5931).srcCtx("lenetUnit.scala:92:17") // x5900 = StreamOutNew(BurstCmdBus)
    isAccum(b6127) = false
    bufferDepthOf(b6127) = 1
    val b6128 = StreamOut(field="size").name("b6128").ctrl(x5931).srcCtx("lenetUnit.scala:92:17") // x5900 = StreamOutNew(BurstCmdBus)
    isAccum(b6128) = false
    bufferDepthOf(b6128) = 1
    val x5901 = StreamIn(field="data").name("x5901").ctrl(x5931).srcCtx("lenetUnit.scala:92:17") // x5901 = StreamInNew(BurstDataBus())
    isAccum(x5901) = false
    bufferDepthOf(x5901) = 1
    val x5919 = UnitController(style=SeqPipe, level=InnerControl).name("x5919").ctrl(x5931).srcCtx("lenetUnit.scala:92:17") // UnitPipe(List(b3673, b3674, b3664, b3617),Block(x5918))
    val x5902 = OpDef(op=FixAdd, inputs=List(b3663, b3671)).name("x5902").ctrl(x5919).srcCtx("lenetUnit.scala:92:17") // FixAdd(b3663,b3671)
    val x5903 = x5902 // FixConvert(x5902,TRUE,_32,_0) (Same Type. No op)
    val x5904 = OpDef(op=FixSla, inputs=List(x5903, Const(5))).name("x5904").ctrl(x5919).srcCtx("lenetUnit.scala:92:17") // FixLsh(x5903,Const(5))
    val x5905 = b3672 // FixConvert(b3672,TRUE,_32,_0) (Same Type. No op)
    val x5906 = OpDef(op=FixSla, inputs=List(x5905, Const(5))).name("x5906").ctrl(x5919).srcCtx("lenetUnit.scala:92:17") // FixLsh(x5905,Const(5))
    val x5907 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x5908 = OpDef(op=FixAdd, inputs=List(x5904, x5906)).name("x5908").ctrl(x5919).srcCtx("lenetUnit.scala:92:17") // FixAdd(x5904,x5906)
    val x5909 = OpDef(op=FixAdd, inputs=List(x5908, x5907)).name("x5909").ctrl(x5919).srcCtx("lenetUnit.scala:92:17") // FixAdd(x5908,x5907)
    val x5910 = OpDef(op=FixSla, inputs=List(x5909, Const(1))).name("x5910").ctrl(x5919).srcCtx("lenetUnit.scala:92:17") // FixLsh(x5909,Const(1))
    val x5911 = x5910 // FixConvert(x5910,TRUE,_64,_0)
    val x5912 = DramAddress(x5705).name("x5912").ctrl(x5919).srcCtx("lenetUnit.scala:92:17") // GetDRAMAddress(x5705)
    val x5914_x5913 = OpDef(op=FixAdd, inputs=List(x5911, x5912)).name("x5914_x5913").ctrl(x5919).srcCtx("lenetUnit.scala:92:17") // FixAdd(x5911,x5912)
    // x5914 = SimpleStruct(ArrayBuffer((offset,x5913), (size,Const(64)), (isLoad,Const(true))))
    val x5915 = OpDef(op=BitAnd, inputs=List(b3673, b3674)).name("x5915").ctrl(x5919).srcCtx("UnrollingBase.scala:28:66") // And(b3673,b3674)
    val x5916 = OpDef(op=BitAnd, inputs=List(b3664, b3617)).name("x5916").ctrl(x5919).srcCtx("UnrollingBase.scala:28:66") // And(b3664,b3617)
    val x5917 = OpDef(op=BitAnd, inputs=List(x5915, x5916)).name("x5917").ctrl(x5919).srcCtx("UnrollingBase.scala:28:66") // And(x5915,x5916)
    val x5918_b6129_b6127 = WriteMem(b6127, x5914_x5913).name("x5918_b6129_b6127").ctrl(x5919).srcCtx("lenetUnit.scala:92:17") // StreamWrite(x5900,x5914,x5917)
    val x5918_b6130_b6128 = WriteMem(b6128, Const(64)).name("x5918_b6130_b6128").ctrl(x5919).srcCtx("lenetUnit.scala:92:17") // StreamWrite(x5900,x5914,x5917)
    val x5920 = FringeDenseLoad(dram=List(x5705), cmdStream=List(b6127, b6128), dataStream=List(x5901)).name("x5920").ctrl(x5931).srcCtx("lenetUnit.scala:92:17") // FringeDenseLoad(x5705,x5900,x5901)
    val x5921 = Counter(min=Const(0), max=Const(32), step=Const(1), par=1).name("x5921").ctrl(x5931).srcCtx("lenetUnit.scala:92:17") // CounterNew(Const(0),Const(32),Const(1),Const(1))
    val x5922 = CounterChain(List(x5921)).name("x5922").ctrl(x5931).srcCtx("lenetUnit.scala:92:17") // CounterChainNew(List(x5921))
    val x5930 = LoopController(style=InnerPipe, level=InnerControl, cchain=x5922).name("x5930").ctrl(x5931).srcCtx("lenetUnit.scala:92:17") // UnrolledForeach(List(b3673, b3674, b3664, b3617),x5922,Block(Const(())),List(List(b3698)),List(List(b3699)))
    val b3698 = CounterIter(x5921, None).name("b3698").ctrl(x5930) // b3698
    val b3699 = Const(true).name("b3699").ctrl(x5930) // b3699
    val x5923 = OpDef(op=BitAnd, inputs=List(b3699, b3673)).name("x5923").ctrl(x5930).srcCtx("UnrollingBase.scala:28:66") // And(b3699,b3673)
    val x5924 = OpDef(op=BitAnd, inputs=List(b3674, b3664)).name("x5924").ctrl(x5930).srcCtx("UnrollingBase.scala:28:66") // And(b3674,b3664)
    val x5925 = OpDef(op=BitAnd, inputs=List(x5923, x5924)).name("x5925").ctrl(x5930).srcCtx("UnrollingBase.scala:28:66") // And(x5923,x5924)
    val x5926 = OpDef(op=BitAnd, inputs=List(x5925, b3617)).name("x5926").ctrl(x5930).srcCtx("UnrollingBase.scala:28:66") // And(x5925,b3617)
    val x5927_x5927 = ReadMem(x5901).name("x5927_x5927").ctrl(x5930).srcCtx("lenetUnit.scala:92:17") // ParStreamRead(x5901,List(x5926))
    val x5928_x5928 = x5927_x5927 // x5928 = VectorApply(x5927,0)
    val x5929 = StoreBanks(List(x5894_d0_b0), List(b3698), x5928_x5928).name("x5929").ctrl(x5930).srcCtx("lenetUnit.scala:92:17") // ParRegFileStore(x5894,List(List(b3698)),List(x5928),List(Const(true)))
    val x5932 = Counter(min=Const(0), max=Const(12), step=Const(1), par=1).name("x5932").ctrl(x6005).srcCtx("lenetUnit.scala:107:31") // CounterNew(Const(0),Const(12),Const(1),Const(1))
    val x5933 = Counter(min=Const(0), max=Const(12), step=Const(1), par=1).name("x5933").ctrl(x6005).srcCtx("lenetUnit.scala:107:22") // CounterNew(Const(0),Const(12),Const(1),Const(1))
    val x5934 = CounterChain(List(x5933,x5932)).name("x5934").ctrl(x6005).srcCtx("lenetUnit.scala:107:37") // CounterChainNew(List(x5933, x5932))
    val x6004 = LoopController(style=MetaPipe, level=OuterControl, cchain=x5934).name("x6004").ctrl(x6005).srcCtx("lenetUnit.scala:107:37") // UnrolledForeach(List(b3664, b3617),x5934,Block(Const(())),List(List(b3712), List(b3713)),List(List(b3714), List(b3715)))
    val b3712 = CounterIter(x5933, Some(0)).name("b3712").ctrl(x6004) // b3712
    val b3714 = Const(true).name("b3714").ctrl(x6004) // b3714
    val b3713 = CounterIter(x5932, Some(0)).name("b3713").ctrl(x6004) // b3713
    val b3715 = Const(true).name("b3715").ctrl(x6004) // b3715
    val x5935_d0 = Reg(init=Some(0.0)).name("x5935_d0").ctrl(x6004).srcCtx("lenetUnit.scala:109:36:out") // x5935 = RegNew(Const(0))
    isAccum(x5935_d0) = false
    bufferDepthOf(x5935_d0) = 2
    val x5935_d1 = Reg(init=Some(0.0)).name("x5935_d1").ctrl(x6004).srcCtx("lenetUnit.scala:109:36:out") // x5935 = RegNew(Const(0))
    isAccum(x5935_d1) = true
    bufferDepthOf(x5935_d1) = 1
    val x5936 = Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x5936").ctrl(x6004).srcCtx("lenetUnit.scala:109:57") // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x5937 = Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x5937").ctrl(x6004).srcCtx("lenetUnit.scala:109:49") // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x5938 = CounterChain(List(x5937,x5936)).name("x5938").ctrl(x6004).srcCtx("lenetUnit.scala:116:15") // CounterChainNew(List(x5937, x5936))
    val x5997 = LoopController(style=MetaPipe, level=OuterControl, cchain=x5938).name("x5997").ctrl(x6004).srcCtx("lenetUnit.scala:116:15") // UnrolledReduce(List(b3714, b3715, b3664, b3617),x5938,x5935,Block((x5935) => Const(())),List(List(b3720), List(b3721)),List(List(b3722), List(b3723)))
    val b3720 = CounterIter(x5937, Some(0)).name("b3720").ctrl(x5997) // b3720
    val b3722 = Const(true).name("b3722").ctrl(x5997) // b3722
    val b3721 = CounterIter(x5936, Some(0)).name("b3721").ctrl(x5997) // b3721
    val b3723 = Const(true).name("b3723").ctrl(x5997) // b3723
    val x5939_d0 = Reg(init=Some(0.0)).name("x5939_d0").ctrl(x5997).srcCtx("lenetUnit.scala:110:41:window") // x5939 = RegNew(Const(0))
    isAccum(x5939_d0) = false
    bufferDepthOf(x5939_d0) = 2
    val x5939_d1 = Reg(init=Some(0.0)).name("x5939_d1").ctrl(x5997).srcCtx("lenetUnit.scala:110:41:window") // x5939 = RegNew(Const(0))
    isAccum(x5939_d1) = true
    bufferDepthOf(x5939_d1) = 1
    val x5940 = Counter(min=Const(0), max=Const(5), step=Const(1), par=1).name("x5940").ctrl(x5997).srcCtx("lenetUnit.scala:110:81") // CounterNew(Const(0),Const(5),Const(1),Const(1))
    val x5941 = Counter(min=Const(0), max=Const(5), step=Const(1), par=1).name("x5941").ctrl(x5997).srcCtx("lenetUnit.scala:110:63") // CounterNew(Const(0),Const(5),Const(1),Const(1))
    val x5942 = CounterChain(List(x5941,x5940)).name("x5942").ctrl(x5997).srcCtx("lenetUnit.scala:114:16") // CounterChainNew(List(x5941, x5940))
    val x5970 = LoopController(style=InnerPipe, level=InnerControl, cchain=x5942).name("x5970").ctrl(x5997).srcCtx("lenetUnit.scala:114:16") // UnrolledReduce(List(b3722, b3723, b3714, b3715, b3664, b3617),x5942,x5939,Block((x5939) => Const(())),List(List(b3728), List(b3729)),List(List(b3730), List(b3731)))
    val b3728 = CounterIter(x5941, Some(0)).name("b3728").ctrl(x5970) // b3728
    val b3730 = Const(true).name("b3730").ctrl(x5970) // b3730
    val b3729 = CounterIter(x5940, None).name("b3729").ctrl(x5970) // b3729
    val b3731 = Const(true).name("b3731").ctrl(x5970) // b3731
    val x5943 = OpDef(op=FixSla, inputs=List(b3712, Const(1))).name("x5943").ctrl(x5970).srcCtx("lenetUnit.scala:111:26") // FixLsh(b3712,Const(1))
    val x5944 = OpDef(op=FixAdd, inputs=List(x5943, b3720)).name("x5944").ctrl(x5970).srcCtx("lenetUnit.scala:111:29:r") // FixAdd(x5943,b3720)
    val x5945 = OpDef(op=FixSla, inputs=List(b3713, Const(1))).name("x5945").ctrl(x5970).srcCtx("lenetUnit.scala:112:26") // FixLsh(b3713,Const(1))
    val x5946 = OpDef(op=FixAdd, inputs=List(x5945, b3721)).name("x5946").ctrl(x5970).srcCtx("lenetUnit.scala:112:29:c") // FixAdd(x5945,b3721)
    val x5947 = OpDef(op=FixAdd, inputs=List(x5944, b3728)).name("x5947").ctrl(x5970).srcCtx("lenetUnit.scala:113:26") // FixAdd(x5944,b3728)
    val x5948 = OpDef(op=FixAdd, inputs=List(x5946, b3729)).name("x5948").ctrl(x5970).srcCtx("lenetUnit.scala:113:31") // FixAdd(x5946,b3729)
    val x5949 = OpDef(op=BitAnd, inputs=List(b3730, b3731)).name("x5949").ctrl(x5970).srcCtx("UnrollingBase.scala:28:66") // And(b3730,b3731)
    val x5950 = OpDef(op=BitAnd, inputs=List(b3722, b3723)).name("x5950").ctrl(x5970).srcCtx("UnrollingBase.scala:28:66") // And(b3722,b3723)
    val x5951 = OpDef(op=BitAnd, inputs=List(b3714, b3715)).name("x5951").ctrl(x5970).srcCtx("UnrollingBase.scala:28:66") // And(b3714,b3715)
    val x5952 = OpDef(op=BitAnd, inputs=List(b3664, b3617)).name("x5952").ctrl(x5970).srcCtx("UnrollingBase.scala:28:66") // And(b3664,b3617)
    val x5953 = OpDef(op=BitAnd, inputs=List(x5949, x5950)).name("x5953").ctrl(x5970).srcCtx("UnrollingBase.scala:28:66") // And(x5949,x5950)
    val x5954 = OpDef(op=BitAnd, inputs=List(x5951, x5952)).name("x5954").ctrl(x5970).srcCtx("UnrollingBase.scala:28:66") // And(x5951,x5952)
    val x5955 = OpDef(op=BitAnd, inputs=List(x5953, x5954)).name("x5955").ctrl(x5970).srcCtx("UnrollingBase.scala:28:66") // And(x5953,x5954)
    val x5956 = LoadBanks(List(x5855_d0_b0), List(x5947, x5948)).name("x5956").ctrl(x5970).srcCtx("lenetUnit.scala:113:24") // ParSRAMLoad(x5855,List(List(x5947, x5948)),List(x5955))
    val x5957 = x5956 // x5957 = VectorApply(x5956,0)
    val x5958 = OpDef(op=FixMul, inputs=List(b3728, Const(5))).name("x5958").ctrl(x5970).srcCtx("lenetUnit.scala:113:46") // FixMul(b3728,Const(5))
    val x5959 = OpDef(op=FixAdd, inputs=List(x5958, b3713)).name("x5959").ctrl(x5970).srcCtx("lenetUnit.scala:113:48") // FixAdd(x5958,b3713)
    val x5960 = LoadBanks(List(x5894_d0_b0), List(x5959)).name("x5960").ctrl(x5970).srcCtx("lenetUnit.scala:113:43") // RegFileLoad(x5894,List(x5959),x5955)
    val x5961 = OpDef(op=FixMul, inputs=List(x5957, x5960)).name("x5961").ctrl(x5970).srcCtx("lenetUnit.scala:113:36") // FixMul(x5957,x5960)
    val x5962 = ReadMem(x5939_d1).name("x5962").ctrl(x5970).srcCtx("lenetUnit.scala:114:16") // RegRead(x5939)
    val x5963 = OpDef(op=FixEql, inputs=List(b3728, Const(0))).name("x5963").ctrl(x5970).srcCtx("lenetUnit.scala:114:16") // FixEql(b3728,Const(0))
    val x5964 = OpDef(op=FixEql, inputs=List(b3729, Const(0))).name("x5964").ctrl(x5970).srcCtx("lenetUnit.scala:114:16") // FixEql(b3729,Const(0))
    val x5965 = OpDef(op=BitAnd, inputs=List(x5963, x5964)).name("x5965").ctrl(x5970).srcCtx("lenetUnit.scala:114:16") // And(x5963,x5964)
    val x5966 = ReduceAccumOp(op=FixAdd, input=x5961, accum=x5962).name("x5966").ctrl(x5970).srcCtx("lenetUnit.scala:114:18") // FixAdd(x5961,x5962)
    val x5967 = OpDef(op=BitAnd, inputs=List(x5950, x5951)).name("x5967").ctrl(x5970).srcCtx("UnrollingBase.scala:28:66") // And(x5950,x5951)
    val x5968 = OpDef(op=BitAnd, inputs=List(x5967, x5952)).name("x5968").ctrl(x5970).srcCtx("UnrollingBase.scala:28:66") // And(x5967,x5952)
    val x5969_x5939_d0 = WriteMem(x5939_d0, x5966).name("x5969_x5939_d0").ctrl(x5970).srcCtx("lenetUnit.scala:114:16") // RegWrite(x5939,x5966,x5968)
    val x5969_x5939_d1 = WriteMem(x5939_d1, x5966).name("x5969_x5939_d1").ctrl(x5970).srcCtx("lenetUnit.scala:114:16") // RegWrite(x5939,x5966,x5968)
    val x5971 = Reg(init=Some(0.0)).name("x5971").ctrl(x5997).srcCtx("lenetUnit.scala:116:15") // x5971 = RegNew(Const(0))
    isAccum(x5971) = false
    bufferDepthOf(x5971) = 2
    val x5982 = UnitController(style=SeqPipe, level=InnerControl).name("x5982").ctrl(x5997).srcCtx("lenetUnit.scala:116:15") // UnitPipe(List(b3722, b3723, b3714, b3715, b3664, b3617),Block(Const(())))
    val x5972 = OpDef(op=BitAnd, inputs=List(b3722, b3723)).name("x5972").ctrl(x5982).srcCtx("UnrollingBase.scala:28:66") // And(b3722,b3723)
    val x5973 = OpDef(op=BitAnd, inputs=List(b3714, b3715)).name("x5973").ctrl(x5982).srcCtx("UnrollingBase.scala:28:66") // And(b3714,b3715)
    val x5974 = OpDef(op=BitAnd, inputs=List(b3664, b3617)).name("x5974").ctrl(x5982).srcCtx("UnrollingBase.scala:28:66") // And(b3664,b3617)
    val x5975 = OpDef(op=BitAnd, inputs=List(x5972, x5973)).name("x5975").ctrl(x5982).srcCtx("UnrollingBase.scala:28:66") // And(x5972,x5973)
    val x5976 = OpDef(op=BitAnd, inputs=List(x5975, x5974)).name("x5976").ctrl(x5982).srcCtx("UnrollingBase.scala:28:66") // And(x5975,x5974)
    val x5977 = LoadBanks(List(x5834_d0_b0), List(b3663)).name("x5977").ctrl(x5982).srcCtx("lenetUnit.scala:115:50") // SRAMLoad(x5834,ArrayBuffer(Const(32)),List(b3663),Const(0),x5976)
    val x5978 = ReadMem(x5939_d0).name("x5978").ctrl(x5982).srcCtx("lenetUnit.scala:115:35") // RegRead(x5939)
    val x5979 = OpDef(op=FixAdd, inputs=List(x5978, x5977)).name("x5979").ctrl(x5982).srcCtx("lenetUnit.scala:115:41") // FixAdd(x5978,x5977)
    val x5980 = OpDef(op=FixMax, inputs=List(Const(0.0), x5979)).name("x5980").ctrl(x5982).srcCtx("lenetUnit.scala:115:18") // Max(Const(0),x5979)
    val x5981_x5971 = WriteMem(x5971, x5980).name("x5981_x5971").ctrl(x5982).srcCtx("lenetUnit.scala:116:15") // RegWrite(x5971,x5980,x5976)
    val x5996 = UnitController(style=SeqPipe, level=InnerControl).name("x5996").ctrl(x5997).srcCtx("lenetUnit.scala:116:15") // UnitPipe(List(b3714, b3715, b3664, b3617),Block(x5995))
    val x5983 = OpDef(op=BitAnd, inputs=List(b3722, b3723)).name("x5983").ctrl(x5996).srcCtx("lenetUnit.scala:116:15") // And(b3722,b3723)
    val x5984 = OpDef(op=BitAnd, inputs=List(b3714, b3715)).name("x5984").ctrl(x5996).srcCtx("lenetUnit.scala:116:15") // And(b3714,b3715)
    val x5985 = OpDef(op=BitAnd, inputs=List(b3664, b3617)).name("x5985").ctrl(x5996).srcCtx("lenetUnit.scala:116:15") // And(b3664,b3617)
    val x5986 = OpDef(op=BitAnd, inputs=List(x5983, x5984)).name("x5986").ctrl(x5996).srcCtx("lenetUnit.scala:116:15") // And(x5983,x5984)
    val x5987 = OpDef(op=BitAnd, inputs=List(x5986, x5985)).name("x5987").ctrl(x5996).srcCtx("lenetUnit.scala:116:15") // And(x5986,x5985)
    val x5988 = ReadMem(x5935_d1).name("x5988").ctrl(x5996).srcCtx("lenetUnit.scala:116:15") // RegRead(x5935)
    val x5989 = OpDef(op=FixEql, inputs=List(b3720, Const(0))).name("x5989").ctrl(x5996).srcCtx("lenetUnit.scala:116:15") // FixEql(b3720,Const(0))
    val x5990 = OpDef(op=FixEql, inputs=List(b3721, Const(0))).name("x5990").ctrl(x5996).srcCtx("lenetUnit.scala:116:15") // FixEql(b3721,Const(0))
    val x5991 = OpDef(op=BitAnd, inputs=List(x5989, x5990)).name("x5991").ctrl(x5996).srcCtx("lenetUnit.scala:116:15") // And(x5989,x5990)
    val x5992 = ReadMem(x5971).name("x5992").ctrl(x5996).srcCtx("lenetUnit.scala:116:15") // RegRead(x5971)
    val x5993 = ReduceAccumOp(op=FixMax, input=x5992, accum=x5988).name("x5993").ctrl(x5996).srcCtx("lenetUnit.scala:116:29") // Max(x5992,x5988)
    val x5994 = OpDef(op=BitAnd, inputs=List(x5984, x5985)).name("x5994").ctrl(x5996).srcCtx("UnrollingBase.scala:28:66") // And(x5984,x5985)
    val x5995_x5935_d0 = WriteMem(x5935_d0, x5993).name("x5995_x5935_d0").ctrl(x5996).srcCtx("lenetUnit.scala:116:15") // RegWrite(x5935,x5993,x5994)
    val x5995_x5935_d1 = WriteMem(x5935_d1, x5993).name("x5995_x5935_d1").ctrl(x5996).srcCtx("lenetUnit.scala:116:15") // RegWrite(x5935,x5993,x5994)
    val x6003 = UnitController(style=SeqPipe, level=InnerControl).name("x6003").ctrl(x6004).srcCtx("lenetUnit.scala:107:37") // UnitPipe(List(b3714, b3715, b3664, b3617),Block(Const(())))
    val x5998 = ReadMem(x5935_d0).name("x5998").ctrl(x6003).srcCtx("lenetUnit.scala:117:43") // RegRead(x5935)
    val x5999 = OpDef(op=BitAnd, inputs=List(b3714, b3715)).name("x5999").ctrl(x6003).srcCtx("UnrollingBase.scala:28:66") // And(b3714,b3715)
    val x6000 = OpDef(op=BitAnd, inputs=List(b3664, b3617)).name("x6000").ctrl(x6003).srcCtx("UnrollingBase.scala:28:66") // And(b3664,b3617)
    val x6001 = OpDef(op=BitAnd, inputs=List(x5999, x6000)).name("x6001").ctrl(x6003).srcCtx("UnrollingBase.scala:28:66") // And(x5999,x6000)
    val x6002 = StoreBanks(List(x5891_d0_b0), List(b3663, b3712, b3713), x5998).name("x6002").ctrl(x6003).srcCtx("lenetUnit.scala:117:37") // SRAMStore(x5891,ArrayBuffer(Const(20), Const(12), Const(12)),List(b3663, b3712, b3713),Const(0),x5998,x6001)
    val x6006 = DRAM().name("x6006").ctrl(x6080).srcCtx("lenetUnit.scala:125:31:tmp_DRAM") // x6006 = DRAMNew(ArrayBuffer(Const(20), Const(12), Const(12)),Const(0))
    val x6007 = Counter(min=Const(0), max=Const(20), step=Const(1), par=1).name("x6007").ctrl(x6080).srcCtx("lenetUnit.scala:126:39") // CounterNew(Const(0),Const(20),Const(1),Const(1))
    val x6008 = Counter(min=Const(0), max=Const(12), step=Const(1), par=1).name("x6008").ctrl(x6080).srcCtx("lenetUnit.scala:126:39") // CounterNew(Const(0),Const(12),Const(1),Const(1))
    val x6009 = CounterChain(List(x6007,x6008)).name("x6009").ctrl(x6080).srcCtx("lenetUnit.scala:126:39") // CounterChainNew(List(x6007, x6008))
    val x6079 = LoopController(style=StreamPipe, level=OuterControl, cchain=x6009).name("x6079").ctrl(x6080).srcCtx("lenetUnit.scala:126:39") // UnrolledForeach(List(b3617),x6009,Block(Const(())),List(List(b3799), List(b3800)),List(List(b3801), List(b3802)))
    val b3799 = CounterIter(x6007, Some(0)).name("b3799").ctrl(x6079) // b3799
    val b3801 = Const(true).name("b3801").ctrl(x6079) // b3801
    val b3800 = CounterIter(x6008, Some(0)).name("b3800").ctrl(x6079) // b3800
    val b3802 = Const(true).name("b3802").ctrl(x6079) // b3802
    val b6131 = StreamOut(field="offset").name("b6131").ctrl(x6079).srcCtx("lenetUnit.scala:126:39") // x6010 = StreamOutNew(BurstCmdBus)
    isAccum(b6131) = false
    bufferDepthOf(b6131) = 1
    val b6132 = StreamOut(field="size").name("b6132").ctrl(x6079).srcCtx("lenetUnit.scala:126:39") // x6010 = StreamOutNew(BurstCmdBus)
    isAccum(b6132) = false
    bufferDepthOf(b6132) = 1
    val x6011 = StreamOut(field="data").name("x6011").ctrl(x6079).srcCtx("lenetUnit.scala:126:39") // x6011 = StreamOutNew(BurstFullDataBus())
    isAccum(x6011) = false
    bufferDepthOf(x6011) = 1
    val x6012 = StreamIn(field="ack").name("x6012").ctrl(x6079).srcCtx("lenetUnit.scala:126:39") // x6012 = StreamInNew(BurstAckBus)
    isAccum(x6012) = false
    bufferDepthOf(x6012) = 1
    val x6078 = UnitController(style=SeqPipe, level=OuterControl).name("x6078").ctrl(x6079).srcCtx("lenetUnit.scala:126:39") // UnitPipe(List(b3801, b3802, b3617),Block(Const(())))
    val x6072 = UnitController(style=SeqPipe, level=OuterControl).name("x6072").ctrl(x6078).srcCtx("lenetUnit.scala:126:39") // UnitPipe(List(b3801, b3802, b3617),Block(Const(())))
    val x6013 = Reg(init=Some(0)).name("x6013").ctrl(x6072).srcCtx("lenetUnit.scala:126:39") // x6013 = RegNew(Const(0))
    isAccum(x6013) = false
    bufferDepthOf(x6013) = 1
    val x6014 = Reg(init=Some(0)).name("x6014").ctrl(x6072).srcCtx("lenetUnit.scala:126:39") // x6014 = RegNew(Const(0))
    isAccum(x6014) = false
    bufferDepthOf(x6014) = 1
    val x6015 = Reg(init=Some(0)).name("x6015").ctrl(x6072).srcCtx("lenetUnit.scala:126:39") // x6015 = RegNew(Const(0))
    isAccum(x6015) = false
    bufferDepthOf(x6015) = 1
    val x6052 = UnitController(style=SeqPipe, level=InnerControl).name("x6052").ctrl(x6072).srcCtx("lenetUnit.scala:126:39") // UnitPipe(List(b3801, b3802, b3617),Block(x6051))
    val x6016 = b3799 // FixConvert(b3799,TRUE,_32,_0) (Same Type. No op)
    val x6017 = OpDef(op=FixMul, inputs=List(x6016, Const(144))).name("x6017").ctrl(x6052).srcCtx("lenetUnit.scala:126:39") // FixMul(x6016,Const(144))
    val x6018 = b3800 // FixConvert(b3800,TRUE,_32,_0) (Same Type. No op)
    val x6019 = OpDef(op=FixMul, inputs=List(x6018, Const(12))).name("x6019").ctrl(x6052).srcCtx("lenetUnit.scala:126:39") // FixMul(x6018,Const(12))
    val x6020 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x6021 = OpDef(op=FixAdd, inputs=List(x6017, x6019)).name("x6021").ctrl(x6052).srcCtx("lenetUnit.scala:126:39") // FixAdd(x6017,x6019)
    val x6022 = OpDef(op=FixAdd, inputs=List(x6021, x6020)).name("x6022").ctrl(x6052).srcCtx("lenetUnit.scala:126:39") // FixAdd(x6021,x6020)
    val x6023 = OpDef(op=FixSla, inputs=List(x6022, Const(1))).name("x6023").ctrl(x6052).srcCtx("lenetUnit.scala:126:39") // FixLsh(x6022,Const(1))
    val x6024 = x6023 // x6024 = DataAsBits(x6023)
    val x6025 = OpDef(op=BitAnd, inputs=List(x6024, Const(31))).name("x6025").ctrl(x6052).srcCtx("lenetUnit.scala:126:39") // VectorSlice(x6024,5,0) strMask=00000000000000000000000000011111
    val x6026 = x6025 // x6026 = BitsAsData(x6025,FixPt[TRUE,_32,_0])
    val x6027 = OpDef(op=FixSub, inputs=List(x6023, x6026)).name("x6027").ctrl(x6052).srcCtx("lenetUnit.scala:126:39") // FixSub(x6023,x6026)
    val x6028 = OpDef(op=FixAdd, inputs=List(x6023, Const(24))).name("x6028").ctrl(x6052).srcCtx("lenetUnit.scala:126:39") // FixAdd(x6023,Const(24))
    val x6029 = x6028 // x6029 = DataAsBits(x6028)
    val x6030 = OpDef(op=BitAnd, inputs=List(x6029, Const(31))).name("x6030").ctrl(x6052).srcCtx("lenetUnit.scala:126:39") // VectorSlice(x6029,5,0) strMask=00000000000000000000000000011111
    val x6031 = x6030 // x6031 = BitsAsData(x6030,FixPt[TRUE,_32,_0])
    val x6032 = OpDef(op=FixEql, inputs=List(x6031, Const(0))).name("x6032").ctrl(x6052).srcCtx("lenetUnit.scala:126:39") // FixEql(x6031,Const(0))
    val x6033 = OpDef(op=FixSub, inputs=List(Const(64), x6031)).name("x6033").ctrl(x6052).srcCtx("lenetUnit.scala:126:39") // FixSub(Const(64),x6031)
    val x6034 = OpDef(op=MuxOp, inputs=List(x6032, Const(0), x6033)).name("x6034").ctrl(x6052).srcCtx("lenetUnit.scala:126:39") // Mux(x6032,Const(0),x6033)
    val x6035 = OpDef(op=FixSra, inputs=List(x6026, Const(1))).name("x6035").ctrl(x6052).srcCtx("lenetUnit.scala:126:39") // FixRsh(x6026,Const(1))
    val x6036 = OpDef(op=FixSra, inputs=List(x6034, Const(1))).name("x6036").ctrl(x6052).srcCtx("lenetUnit.scala:126:39") // FixRsh(x6034,Const(1))
    val x6037 = OpDef(op=FixAdd, inputs=List(x6035, Const(12))).name("x6037").ctrl(x6052).srcCtx("lenetUnit.scala:126:39") // FixAdd(x6035,Const(12))
    val x6038 = OpDef(op=FixAdd, inputs=List(Const(12), x6035)).name("x6038").ctrl(x6052).srcCtx("lenetUnit.scala:126:39") // FixAdd(Const(12),x6035)
    val x6039 = OpDef(op=FixAdd, inputs=List(x6038, x6036)).name("x6039").ctrl(x6052).srcCtx("lenetUnit.scala:126:39") // FixAdd(x6038,x6036)
    val x6040 = OpDef(op=FixAdd, inputs=List(Const(24), x6026)).name("x6040").ctrl(x6052).srcCtx("lenetUnit.scala:126:39") // FixAdd(Const(24),x6026)
    val x6045_x6041 = OpDef(op=FixAdd, inputs=List(x6040, x6034)).name("x6045_x6041").ctrl(x6052).srcCtx("lenetUnit.scala:126:39") // FixAdd(x6040,x6034)
    val x6042 = x6027 // FixConvert(x6027,TRUE,_64,_0)
    val x6043 = DramAddress(x6006).name("x6043").ctrl(x6052).srcCtx("lenetUnit.scala:126:39") // GetDRAMAddress(x6006)
    val x6045_x6044 = OpDef(op=FixAdd, inputs=List(x6042, x6043)).name("x6045_x6044").ctrl(x6052).srcCtx("lenetUnit.scala:126:39") // FixAdd(x6042,x6043)
    // x6045 = SimpleStruct(ArrayBuffer((offset,x6044), (size,x6041), (isLoad,Const(false))))
    val x6046 = OpDef(op=BitAnd, inputs=List(b3801, b3802)).name("x6046").ctrl(x6052).srcCtx("UnrollingBase.scala:28:66") // And(b3801,b3802)
    val x6047 = OpDef(op=BitAnd, inputs=List(x6046, b3617)).name("x6047").ctrl(x6052).srcCtx("UnrollingBase.scala:28:66") // And(x6046,b3617)
    val x6048_b6133_b6131 = WriteMem(b6131, x6045_x6044).name("x6048_b6133_b6131").ctrl(x6052).srcCtx("lenetUnit.scala:126:39") // StreamWrite(x6010,x6045,x6047)
    val x6048_b6134_b6132 = WriteMem(b6132, x6045_x6041).name("x6048_b6134_b6132").ctrl(x6052).srcCtx("lenetUnit.scala:126:39") // StreamWrite(x6010,x6045,x6047)
    val x6049_x6013 = WriteMem(x6013, x6035).name("x6049_x6013").ctrl(x6052).srcCtx("lenetUnit.scala:126:39") // RegWrite(x6013,x6035,x6047)
    val x6050_x6014 = WriteMem(x6014, x6037).name("x6050_x6014").ctrl(x6052).srcCtx("lenetUnit.scala:126:39") // RegWrite(x6014,x6037,x6047)
    val x6051_x6015 = WriteMem(x6015, x6039).name("x6051_x6015").ctrl(x6052).srcCtx("lenetUnit.scala:126:39") // RegWrite(x6015,x6039,x6047)
    val x6053 = ReadMem(x6015).name("x6053").ctrl(x6072).srcCtx("lenetUnit.scala:126:39") // RegRead(x6015)
    val x6054 = Counter(min=Const(0), max=x6053, step=Const(1), par=1).name("x6054").ctrl(x6072).srcCtx("lenetUnit.scala:126:39") // CounterNew(Const(0),x6053,Const(1),Const(1))
    val x6055 = CounterChain(List(x6054)).name("x6055").ctrl(x6072).srcCtx("lenetUnit.scala:126:39") // CounterChainNew(List(x6054))
    val x6071 = LoopController(style=InnerPipe, level=InnerControl, cchain=x6055).name("x6071").ctrl(x6072).srcCtx("lenetUnit.scala:126:39") // UnrolledForeach(List(b3801, b3802, b3617),x6055,Block(Const(())),List(List(b3845)),List(List(b3846)))
    val b3845 = CounterIter(x6054, None).name("b3845").ctrl(x6071) // b3845
    val b3846 = Const(true).name("b3846").ctrl(x6071) // b3846
    val x6056 = ReadMem(x6013).name("x6056").ctrl(x6071).srcCtx("lenetUnit.scala:126:39") // RegRead(x6013)
    val x6057 = OpDef(op=FixLeq, inputs=List(x6056, b3845)).name("x6057").ctrl(x6071).srcCtx("lenetUnit.scala:126:39") // FixLeq(x6056,b3845)
    val x6058 = ReadMem(x6014).name("x6058").ctrl(x6071).srcCtx("lenetUnit.scala:126:39") // RegRead(x6014)
    val x6059 = OpDef(op=FixLt, inputs=List(b3845, x6058)).name("x6059").ctrl(x6071).srcCtx("lenetUnit.scala:126:39") // FixLt(b3845,x6058)
    val x6069_x6060 = OpDef(op=BitAnd, inputs=List(x6057, x6059)).name("x6069_x6060").ctrl(x6071).srcCtx("lenetUnit.scala:126:39") // And(x6057,x6059)
    val x6061 = OpDef(op=FixSub, inputs=List(b3845, x6056)).name("x6061").ctrl(x6071).srcCtx("lenetUnit.scala:126:39") // FixSub(b3845,x6056)
    val x6062 = OpDef(op=BitAnd, inputs=List(b3846, b3801)).name("x6062").ctrl(x6071).srcCtx("UnrollingBase.scala:28:66") // And(b3846,b3801)
    val x6063 = OpDef(op=BitAnd, inputs=List(b3802, b3617)).name("x6063").ctrl(x6071).srcCtx("UnrollingBase.scala:28:66") // And(b3802,b3617)
    val x6064 = OpDef(op=BitAnd, inputs=List(x6062, x6063)).name("x6064").ctrl(x6071).srcCtx("UnrollingBase.scala:28:66") // And(x6062,x6063)
    val x6065 = OpDef(op=BitAnd, inputs=List(x6069_x6060, x6064)).name("x6065").ctrl(x6071).srcCtx("UnrollingTransformer.scala:270:41") // And(x6060,x6064)
    val x6066 = LoadBanks(List(x5891_d0_b0), List(b3799, b3800, x6061)).name("x6066").ctrl(x6071).srcCtx("lenetUnit.scala:126:39") // ParSRAMLoad(x5891,List(List(b3799, b3800, x6061)),List(x6065))
    val x6067 = x6066 // x6067 = VectorApply(x6066,0)
    val x6069_x6068 = OpDef(op=MuxOp, inputs=List(x6069_x6060, x6067, Const(0.0))).name("x6069_x6068").ctrl(x6071).srcCtx("lenetUnit.scala:126:39") // Mux(x6060,x6067,Const(0))
    // x6069 = SimpleStruct(ArrayBuffer((_1,x6068), (_2,x6060)))
    val x6070_x6070_x6011 = WriteMem(x6011, x6069_x6068).name("x6070_x6070_x6011").ctrl(x6071).srcCtx("lenetUnit.scala:126:39") // ParStreamWrite(x6011,List(x6069),List(x6064))
    val x6073 = FringeDenseStore(dram=List(x6006), cmdStream=List(b6131, b6132), dataStream=List(x6011), ackStream=List(x6012)).name("x6073").ctrl(x6078).srcCtx("lenetUnit.scala:126:39") // FringeDenseStore(x6006,x6010,x6011,x6012)
    val x6077 = UnitController(style=SeqPipe, level=InnerControl).name("x6077").ctrl(x6078).srcCtx("lenetUnit.scala:126:39") // UnitPipe(List(b3801, b3802, b3617),Block(Const(())))
    val x6074 = OpDef(op=BitAnd, inputs=List(b3801, b3802)).name("x6074").ctrl(x6077).srcCtx("UnrollingBase.scala:28:66") // And(b3801,b3802)
    val x6075 = OpDef(op=BitAnd, inputs=List(x6074, b3617)).name("x6075").ctrl(x6077).srcCtx("UnrollingBase.scala:28:66") // And(x6074,b3617)
    val x6076_x6076 = ReadMem(x6012).name("x6076_x6076").ctrl(x6077).srcCtx("lenetUnit.scala:126:39") // StreamRead(x6012,x6075)
    
  }
}
