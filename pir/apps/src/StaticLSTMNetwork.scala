import pir._
import pir.node._
import arch._
import prism.enums._

object StaticLSTMNetwork extends PIRApp {
  def main(implicit design:PIRDesign) = {
    import design.pirmeta._
    val x6742 = withCtrl(design.top.topController) { DRAM(dims=List(Const(8), Const(128))).name("x6742").srcCtx("StaticLSTMNetwork.scala:46:20:xInit") } // x6742 = DRAMNew(ArrayBuffer(Const(8), Const(128)),Const(0))
    val x6751 = withCtrl(design.top.topController) { DRAM(dims=List(Const(2), Const(128))).name("x6751").srcCtx("StaticLSTMNetwork.scala:46:20:cInit") } // x6751 = DRAMNew(ArrayBuffer(Const(2), Const(128)),Const(0))
    val x6760 = withCtrl(design.top.topController) { DRAM(dims=List(Const(2), Const(128))).name("x6760").srcCtx("StaticLSTMNetwork.scala:46:20:hInit") } // x6760 = DRAMNew(ArrayBuffer(Const(2), Const(128)),Const(0))
    val x6769 = withCtrl(design.top.topController) { DRAM(dims=List(Const(2), Const(4), Const(128))).name("x6769").srcCtx("StaticLSTMNetwork.scala:63:20:bInit") } // x6769 = DRAMNew(ArrayBuffer(Const(2), Const(4), Const(128)),Const(0))
    val x6777 = withCtrl(design.top.topController) { DRAM(dims=List(Const(2), Const(4), Const(2), Const(128), Const(128))).name("x6777").srcCtx("StaticLSTMNetwork.scala:81:20:wInit") } // x6777 = DRAMNew(ArrayBuffer(Const(2), Const(4), Const(2), Const(128), Const(128)),Const(0))
    val x6785 = withCtrl(design.top.topController) { DRAM(dims=List(Const(8), Const(128))).name("x6785").srcCtx("StaticLSTMNetwork.scala:291:22:out") } // x6785 = DRAMNew(ArrayBuffer(Const(8), Const(128)),Const(0))
    val x7239 = withCtrl(design.top.topController) { UnitController(style=SeqPipe, level=OuterControl).name("x7239").srcCtx("StaticLSTMNetwork.scala:220:11") } // Hwblock(Block(Const(())),false)
    val x6786_d0_b0 = withCtrl(x7239) { SRAM(size=1024, banking=Strided(banks=16, stride=1)).name("x6786_d0_b0").srcCtx("StaticLSTMNetwork.scala:110:21:x") } // x6786 = SRAMNew(ArrayBuffer(Const(8), Const(128)))
    isAccum(x6786_d0_b0) = false
    bufferDepthOf(x6786_d0_b0) = 1
    staticDimsOf(x6786_d0_b0) = List(8, 128)
    val x6786_d1_b0 = withCtrl(x7239) { SRAM(size=1024, banking=Strided(banks=16, stride=1)).name("x6786_d1_b0").srcCtx("StaticLSTMNetwork.scala:110:21:x") } // x6786 = SRAMNew(ArrayBuffer(Const(8), Const(128)))
    isAccum(x6786_d1_b0) = false
    bufferDepthOf(x6786_d1_b0) = 2
    staticDimsOf(x6786_d1_b0) = List(8, 128)
    val x6786_d2_b0 = withCtrl(x7239) { SRAM(size=1024, banking=Strided(banks=16, stride=1)).name("x6786_d2_b0").srcCtx("StaticLSTMNetwork.scala:110:21:x") } // x6786 = SRAMNew(ArrayBuffer(Const(8), Const(128)))
    isAccum(x6786_d2_b0) = false
    bufferDepthOf(x6786_d2_b0) = 2
    staticDimsOf(x6786_d2_b0) = List(8, 128)
    val x6786_d3_b0 = withCtrl(x7239) { SRAM(size=1024, banking=Strided(banks=16, stride=1)).name("x6786_d3_b0").srcCtx("StaticLSTMNetwork.scala:110:21:x") } // x6786 = SRAMNew(ArrayBuffer(Const(8), Const(128)))
    isAccum(x6786_d3_b0) = false
    bufferDepthOf(x6786_d3_b0) = 2
    staticDimsOf(x6786_d3_b0) = List(8, 128)
    val x6786_d4_b0 = withCtrl(x7239) { SRAM(size=1024, banking=Strided(banks=16, stride=1)).name("x6786_d4_b0").srcCtx("StaticLSTMNetwork.scala:110:21:x") } // x6786 = SRAMNew(ArrayBuffer(Const(8), Const(128)))
    isAccum(x6786_d4_b0) = false
    bufferDepthOf(x6786_d4_b0) = 2
    staticDimsOf(x6786_d4_b0) = List(8, 128)
    val x6787 = withCtrl(x7239) { Counter(min=Const(0), max=Const(8), step=Const(1), par=1).name("x6787").srcCtx("StaticLSTMNetwork.scala:111:8") } // CounterNew(Const(0),Const(8),Const(1),Const(1))
    val x6788 = withCtrl(x7239) { CounterChain(List(x6787)).name("x6788").srcCtx("StaticLSTMNetwork.scala:111:8") } // CounterChainNew(List(x6787))
    val x6810 = withCtrl(x7239) { LoopController(style=StreamPipe, level=OuterControl, cchain=x6788).name("x6810").srcCtx("StaticLSTMNetwork.scala:111:8") } // UnrolledForeach(List(Const(true)),x6788,Block(Const(())),List(List(b4195)),List(List(b4196)))
    val b4195 = withCtrl(x6810) { CounterIter(x6787, Some(0)).name("b4195") } // b4195
    val b4196 = withCtrl(x6810) { Const(true).name("b4196") } // b4196
    val b7250 = withCtrl(x6810) { StreamOut(field="offset").name("b7250").srcCtx("StaticLSTMNetwork.scala:111:8") } // x6789 = StreamOutNew(BurstCmdBus)
    isAccum(b7250) = false
    bufferDepthOf(b7250) = 1
    val b7251 = withCtrl(x6810) { StreamOut(field="size").name("b7251").srcCtx("StaticLSTMNetwork.scala:111:8") } // x6789 = StreamOutNew(BurstCmdBus)
    isAccum(b7251) = false
    bufferDepthOf(b7251) = 1
    val x6790 = withCtrl(x6810) { StreamIn(field="data").name("x6790").srcCtx("StaticLSTMNetwork.scala:111:8") } // x6790 = StreamInNew(BurstDataBus())
    isAccum(x6790) = false
    bufferDepthOf(x6790) = 1
    val x6801 = withCtrl(x6810) { UnitController(style=SeqPipe, level=InnerControl).name("x6801").srcCtx("StaticLSTMNetwork.scala:111:8") } // UnitPipe(List(b4196),Block(x6800))
    val x6791 = withCtrl(x6801) { b4195 } // FixConvert(b4195,TRUE,_32,_0) (Same Type. No op)
    val x6792 = withCtrl(x6801) { OpDef(op=FixSla, inputs=List(x6791, Const(7))).name("x6792").srcCtx("StaticLSTMNetwork.scala:111:8") } // FixLsh(x6791,Const(7))
    val x6793 = withCtrl(x6801) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x6794 = withCtrl(x6801) { OpDef(op=FixAdd, inputs=List(x6792, x6793)).name("x6794").srcCtx("StaticLSTMNetwork.scala:111:8") } // FixAdd(x6792,x6793)
    val x6795 = withCtrl(x6801) { OpDef(op=FixSla, inputs=List(x6794, Const(2))).name("x6795").srcCtx("StaticLSTMNetwork.scala:111:8") } // FixLsh(x6794,Const(2))
    val x6796 = withCtrl(x6801) { x6795 } // FixConvert(x6795,TRUE,_64,_0)
    val x6797 = withCtrl(x6801) { DramAddress(x6742).name("x6797").srcCtx("StaticLSTMNetwork.scala:111:8") } // GetDRAMAddress(x6742)
    val x6799_x6798 = withCtrl(x6801) { OpDef(op=FixAdd, inputs=List(x6796, x6797)).name("x6799_x6798").srcCtx("StaticLSTMNetwork.scala:111:8") } // FixAdd(x6796,x6797)
    // x6799 = SimpleStruct(ArrayBuffer((offset,x6798), (size,Const(512)), (isLoad,Const(true))))
    val x6800_b7252_b7250 = withCtrl(x6801) { WriteMem(b7250, x6799_x6798).name("x6800_b7252_b7250").srcCtx("StaticLSTMNetwork.scala:111:8") } // StreamWrite(x6789,x6799,b4196)
    val x6800_b7253_b7251 = withCtrl(x6801) { WriteMem(b7251, Const(512)).name("x6800_b7253_b7251").srcCtx("StaticLSTMNetwork.scala:111:8") } // StreamWrite(x6789,x6799,b4196)
    val x6802 = withCtrl(x6810) { FringeDenseLoad(dram=List(x6742), cmdStream=List(b7250, b7251), dataStream=List(x6790)).name("x6802").srcCtx("StaticLSTMNetwork.scala:111:8") } // FringeDenseLoad(x6742,x6789,x6790)
    val x6803 = withCtrl(x6810) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6803").srcCtx("StaticLSTMNetwork.scala:111:8") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6804 = withCtrl(x6810) { CounterChain(List(x6803)).name("x6804").srcCtx("StaticLSTMNetwork.scala:111:8") } // CounterChainNew(List(x6803))
    val x6809 = withCtrl(x6810) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6804).name("x6809").srcCtx("StaticLSTMNetwork.scala:111:8") } // UnrolledForeach(List(b4196),x6804,Block(Const(())),List(List(b4213)),List(List(b4214)))
    val b4213 = withCtrl(x6809) { CounterIter(x6803, None).name("b4213") } // b4213
    val b4214 = withCtrl(x6809) { Const(true).name("b4214") } // b4214
    val x6805 = withCtrl(x6809) { OpDef(op=BitAnd, inputs=List(b4214, b4196)).name("x6805").srcCtx("UnrollingBase.scala:28:66") } // And(b4214,b4196)
    val x6806_x6806 = withCtrl(x6809) { ReadMem(x6790).name("x6806_x6806").srcCtx("StaticLSTMNetwork.scala:111:8") } // ParStreamRead(x6790,List(x6805))
    val x6807_x6807 = withCtrl(x6809) { x6806_x6806 } // VectorApply(x6806,0)
    val x6808 = withCtrl(x6809) { StoreBanks(List(List(x6786_d0_b0), List(x6786_d1_b0), List(x6786_d2_b0), List(x6786_d3_b0), List(x6786_d4_b0)), List(b4195, b4213), x6807_x6807).name("x6808").srcCtx("StaticLSTMNetwork.scala:111:8") } // ParSRAMStore(x6786,List(List(b4195, b4213)),List(x6807),List(x6805))
    val x6811_d0_b0 = withCtrl(x7239) { SRAM(size=256, banking=Strided(banks=16, stride=1)).name("x6811_d0_b0").srcCtx("StaticLSTMNetwork.scala:110:21:c") } // x6811 = SRAMNew(ArrayBuffer(Const(2), Const(128)))
    isAccum(x6811_d0_b0) = true
    bufferDepthOf(x6811_d0_b0) = 1
    staticDimsOf(x6811_d0_b0) = List(2, 128)
    val x6812 = withCtrl(x7239) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x6812").srcCtx("StaticLSTMNetwork.scala:111:8") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x6813 = withCtrl(x7239) { CounterChain(List(x6812)).name("x6813").srcCtx("StaticLSTMNetwork.scala:111:8") } // CounterChainNew(List(x6812))
    val x6835 = withCtrl(x7239) { LoopController(style=StreamPipe, level=OuterControl, cchain=x6813).name("x6835").srcCtx("StaticLSTMNetwork.scala:111:8") } // UnrolledForeach(List(Const(true)),x6813,Block(Const(())),List(List(b4224)),List(List(b4225)))
    val b4224 = withCtrl(x6835) { CounterIter(x6812, Some(0)).name("b4224") } // b4224
    val b4225 = withCtrl(x6835) { Const(true).name("b4225") } // b4225
    val b7254 = withCtrl(x6835) { StreamOut(field="offset").name("b7254").srcCtx("StaticLSTMNetwork.scala:111:8") } // x6814 = StreamOutNew(BurstCmdBus)
    isAccum(b7254) = false
    bufferDepthOf(b7254) = 1
    val b7255 = withCtrl(x6835) { StreamOut(field="size").name("b7255").srcCtx("StaticLSTMNetwork.scala:111:8") } // x6814 = StreamOutNew(BurstCmdBus)
    isAccum(b7255) = false
    bufferDepthOf(b7255) = 1
    val x6815 = withCtrl(x6835) { StreamIn(field="data").name("x6815").srcCtx("StaticLSTMNetwork.scala:111:8") } // x6815 = StreamInNew(BurstDataBus())
    isAccum(x6815) = false
    bufferDepthOf(x6815) = 1
    val x6826 = withCtrl(x6835) { UnitController(style=SeqPipe, level=InnerControl).name("x6826").srcCtx("StaticLSTMNetwork.scala:111:8") } // UnitPipe(List(b4225),Block(x6825))
    val x6816 = withCtrl(x6826) { b4224 } // FixConvert(b4224,TRUE,_32,_0) (Same Type. No op)
    val x6817 = withCtrl(x6826) { OpDef(op=FixSla, inputs=List(x6816, Const(7))).name("x6817").srcCtx("StaticLSTMNetwork.scala:111:8") } // FixLsh(x6816,Const(7))
    val x6818 = withCtrl(x6826) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x6819 = withCtrl(x6826) { OpDef(op=FixAdd, inputs=List(x6817, x6818)).name("x6819").srcCtx("StaticLSTMNetwork.scala:111:8") } // FixAdd(x6817,x6818)
    val x6820 = withCtrl(x6826) { OpDef(op=FixSla, inputs=List(x6819, Const(2))).name("x6820").srcCtx("StaticLSTMNetwork.scala:111:8") } // FixLsh(x6819,Const(2))
    val x6821 = withCtrl(x6826) { x6820 } // FixConvert(x6820,TRUE,_64,_0)
    val x6822 = withCtrl(x6826) { DramAddress(x6751).name("x6822").srcCtx("StaticLSTMNetwork.scala:111:8") } // GetDRAMAddress(x6751)
    val x6824_x6823 = withCtrl(x6826) { OpDef(op=FixAdd, inputs=List(x6821, x6822)).name("x6824_x6823").srcCtx("StaticLSTMNetwork.scala:111:8") } // FixAdd(x6821,x6822)
    // x6824 = SimpleStruct(ArrayBuffer((offset,x6823), (size,Const(512)), (isLoad,Const(true))))
    val x6825_b7256_b7254 = withCtrl(x6826) { WriteMem(b7254, x6824_x6823).name("x6825_b7256_b7254").srcCtx("StaticLSTMNetwork.scala:111:8") } // StreamWrite(x6814,x6824,b4225)
    val x6825_b7257_b7255 = withCtrl(x6826) { WriteMem(b7255, Const(512)).name("x6825_b7257_b7255").srcCtx("StaticLSTMNetwork.scala:111:8") } // StreamWrite(x6814,x6824,b4225)
    val x6827 = withCtrl(x6835) { FringeDenseLoad(dram=List(x6751), cmdStream=List(b7254, b7255), dataStream=List(x6815)).name("x6827").srcCtx("StaticLSTMNetwork.scala:111:8") } // FringeDenseLoad(x6751,x6814,x6815)
    val x6828 = withCtrl(x6835) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6828").srcCtx("StaticLSTMNetwork.scala:111:8") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6829 = withCtrl(x6835) { CounterChain(List(x6828)).name("x6829").srcCtx("StaticLSTMNetwork.scala:111:8") } // CounterChainNew(List(x6828))
    val x6834 = withCtrl(x6835) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6829).name("x6834").srcCtx("StaticLSTMNetwork.scala:111:8") } // UnrolledForeach(List(b4225),x6829,Block(Const(())),List(List(b4242)),List(List(b4243)))
    val b4242 = withCtrl(x6834) { CounterIter(x6828, None).name("b4242") } // b4242
    val b4243 = withCtrl(x6834) { Const(true).name("b4243") } // b4243
    val x6830 = withCtrl(x6834) { OpDef(op=BitAnd, inputs=List(b4243, b4225)).name("x6830").srcCtx("UnrollingBase.scala:28:66") } // And(b4243,b4225)
    val x6831_x6831 = withCtrl(x6834) { ReadMem(x6815).name("x6831_x6831").srcCtx("StaticLSTMNetwork.scala:111:8") } // ParStreamRead(x6815,List(x6830))
    val x6832_x6832 = withCtrl(x6834) { x6831_x6831 } // VectorApply(x6831,0)
    val x6833 = withCtrl(x6834) { StoreBanks(List(List(x6811_d0_b0)), List(b4224, b4242), x6832_x6832).name("x6833").srcCtx("StaticLSTMNetwork.scala:111:8") } // ParSRAMStore(x6811,List(List(b4224, b4242)),List(x6832),List(x6830))
    val x6836_d0_b0 = withCtrl(x7239) { SRAM(size=256, banking=Strided(banks=16, stride=1)).name("x6836_d0_b0").srcCtx("StaticLSTMNetwork.scala:110:21:h") } // x6836 = SRAMNew(ArrayBuffer(Const(2), Const(128)))
    isAccum(x6836_d0_b0) = false
    bufferDepthOf(x6836_d0_b0) = 2
    staticDimsOf(x6836_d0_b0) = List(2, 128)
    val x6836_d1_b0 = withCtrl(x7239) { SRAM(size=256, banking=Strided(banks=16, stride=1)).name("x6836_d1_b0").srcCtx("StaticLSTMNetwork.scala:110:21:h") } // x6836 = SRAMNew(ArrayBuffer(Const(2), Const(128)))
    isAccum(x6836_d1_b0) = false
    bufferDepthOf(x6836_d1_b0) = 2
    staticDimsOf(x6836_d1_b0) = List(2, 128)
    val x6836_d2_b0 = withCtrl(x7239) { SRAM(size=256, banking=Strided(banks=16, stride=1)).name("x6836_d2_b0").srcCtx("StaticLSTMNetwork.scala:110:21:h") } // x6836 = SRAMNew(ArrayBuffer(Const(2), Const(128)))
    isAccum(x6836_d2_b0) = false
    bufferDepthOf(x6836_d2_b0) = 2
    staticDimsOf(x6836_d2_b0) = List(2, 128)
    val x6836_d3_b0 = withCtrl(x7239) { SRAM(size=256, banking=Strided(banks=16, stride=1)).name("x6836_d3_b0").srcCtx("StaticLSTMNetwork.scala:110:21:h") } // x6836 = SRAMNew(ArrayBuffer(Const(2), Const(128)))
    isAccum(x6836_d3_b0) = false
    bufferDepthOf(x6836_d3_b0) = 2
    staticDimsOf(x6836_d3_b0) = List(2, 128)
    val x6837 = withCtrl(x7239) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x6837").srcCtx("StaticLSTMNetwork.scala:111:8") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x6838 = withCtrl(x7239) { CounterChain(List(x6837)).name("x6838").srcCtx("StaticLSTMNetwork.scala:111:8") } // CounterChainNew(List(x6837))
    val x6860 = withCtrl(x7239) { LoopController(style=StreamPipe, level=OuterControl, cchain=x6838).name("x6860").srcCtx("StaticLSTMNetwork.scala:111:8") } // UnrolledForeach(List(Const(true)),x6838,Block(Const(())),List(List(b4253)),List(List(b4254)))
    val b4253 = withCtrl(x6860) { CounterIter(x6837, Some(0)).name("b4253") } // b4253
    val b4254 = withCtrl(x6860) { Const(true).name("b4254") } // b4254
    val b7258 = withCtrl(x6860) { StreamOut(field="offset").name("b7258").srcCtx("StaticLSTMNetwork.scala:111:8") } // x6839 = StreamOutNew(BurstCmdBus)
    isAccum(b7258) = false
    bufferDepthOf(b7258) = 1
    val b7259 = withCtrl(x6860) { StreamOut(field="size").name("b7259").srcCtx("StaticLSTMNetwork.scala:111:8") } // x6839 = StreamOutNew(BurstCmdBus)
    isAccum(b7259) = false
    bufferDepthOf(b7259) = 1
    val x6840 = withCtrl(x6860) { StreamIn(field="data").name("x6840").srcCtx("StaticLSTMNetwork.scala:111:8") } // x6840 = StreamInNew(BurstDataBus())
    isAccum(x6840) = false
    bufferDepthOf(x6840) = 1
    val x6851 = withCtrl(x6860) { UnitController(style=SeqPipe, level=InnerControl).name("x6851").srcCtx("StaticLSTMNetwork.scala:111:8") } // UnitPipe(List(b4254),Block(x6850))
    val x6841 = withCtrl(x6851) { b4253 } // FixConvert(b4253,TRUE,_32,_0) (Same Type. No op)
    val x6842 = withCtrl(x6851) { OpDef(op=FixSla, inputs=List(x6841, Const(7))).name("x6842").srcCtx("StaticLSTMNetwork.scala:111:8") } // FixLsh(x6841,Const(7))
    val x6843 = withCtrl(x6851) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x6844 = withCtrl(x6851) { OpDef(op=FixAdd, inputs=List(x6842, x6843)).name("x6844").srcCtx("StaticLSTMNetwork.scala:111:8") } // FixAdd(x6842,x6843)
    val x6845 = withCtrl(x6851) { OpDef(op=FixSla, inputs=List(x6844, Const(2))).name("x6845").srcCtx("StaticLSTMNetwork.scala:111:8") } // FixLsh(x6844,Const(2))
    val x6846 = withCtrl(x6851) { x6845 } // FixConvert(x6845,TRUE,_64,_0)
    val x6847 = withCtrl(x6851) { DramAddress(x6760).name("x6847").srcCtx("StaticLSTMNetwork.scala:111:8") } // GetDRAMAddress(x6760)
    val x6849_x6848 = withCtrl(x6851) { OpDef(op=FixAdd, inputs=List(x6846, x6847)).name("x6849_x6848").srcCtx("StaticLSTMNetwork.scala:111:8") } // FixAdd(x6846,x6847)
    // x6849 = SimpleStruct(ArrayBuffer((offset,x6848), (size,Const(512)), (isLoad,Const(true))))
    val x6850_b7260_b7258 = withCtrl(x6851) { WriteMem(b7258, x6849_x6848).name("x6850_b7260_b7258").srcCtx("StaticLSTMNetwork.scala:111:8") } // StreamWrite(x6839,x6849,b4254)
    val x6850_b7261_b7259 = withCtrl(x6851) { WriteMem(b7259, Const(512)).name("x6850_b7261_b7259").srcCtx("StaticLSTMNetwork.scala:111:8") } // StreamWrite(x6839,x6849,b4254)
    val x6852 = withCtrl(x6860) { FringeDenseLoad(dram=List(x6760), cmdStream=List(b7258, b7259), dataStream=List(x6840)).name("x6852").srcCtx("StaticLSTMNetwork.scala:111:8") } // FringeDenseLoad(x6760,x6839,x6840)
    val x6853 = withCtrl(x6860) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6853").srcCtx("StaticLSTMNetwork.scala:111:8") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6854 = withCtrl(x6860) { CounterChain(List(x6853)).name("x6854").srcCtx("StaticLSTMNetwork.scala:111:8") } // CounterChainNew(List(x6853))
    val x6859 = withCtrl(x6860) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6854).name("x6859").srcCtx("StaticLSTMNetwork.scala:111:8") } // UnrolledForeach(List(b4254),x6854,Block(Const(())),List(List(b4271)),List(List(b4272)))
    val b4271 = withCtrl(x6859) { CounterIter(x6853, None).name("b4271") } // b4271
    val b4272 = withCtrl(x6859) { Const(true).name("b4272") } // b4272
    val x6855 = withCtrl(x6859) { OpDef(op=BitAnd, inputs=List(b4272, b4254)).name("x6855").srcCtx("UnrollingBase.scala:28:66") } // And(b4272,b4254)
    val x6856_x6856 = withCtrl(x6859) { ReadMem(x6840).name("x6856_x6856").srcCtx("StaticLSTMNetwork.scala:111:8") } // ParStreamRead(x6840,List(x6855))
    val x6857_x6857 = withCtrl(x6859) { x6856_x6856 } // VectorApply(x6856,0)
    val x6858 = withCtrl(x6859) { StoreBanks(List(List(x6836_d0_b0), List(x6836_d1_b0), List(x6836_d2_b0), List(x6836_d3_b0)), List(b4253, b4271), x6857_x6857).name("x6858").srcCtx("StaticLSTMNetwork.scala:111:8") } // ParSRAMStore(x6836,List(List(b4253, b4271)),List(x6857),List(x6855))
    val x6861_d0_b0 = withCtrl(x7239) { SRAM(size=262144, banking=Strided(banks=16, stride=128)).name("x6861_d0_b0").srcCtx("StaticLSTMNetwork.scala:161:21:w") } // x6861 = SRAMNew(ArrayBuffer(Const(2), Const(4), Const(2), Const(128), Const(128)))
    isAccum(x6861_d0_b0) = false
    bufferDepthOf(x6861_d0_b0) = 1
    staticDimsOf(x6861_d0_b0) = List(2, 4, 2, 128, 128)
    val x6861_d1_b0 = withCtrl(x7239) { SRAM(size=262144, banking=Strided(banks=16, stride=128)).name("x6861_d1_b0").srcCtx("StaticLSTMNetwork.scala:161:21:w") } // x6861 = SRAMNew(ArrayBuffer(Const(2), Const(4), Const(2), Const(128), Const(128)))
    isAccum(x6861_d1_b0) = false
    bufferDepthOf(x6861_d1_b0) = 1
    staticDimsOf(x6861_d1_b0) = List(2, 4, 2, 128, 128)
    val x6861_d2_b0 = withCtrl(x7239) { SRAM(size=262144, banking=Strided(banks=16, stride=128)).name("x6861_d2_b0").srcCtx("StaticLSTMNetwork.scala:161:21:w") } // x6861 = SRAMNew(ArrayBuffer(Const(2), Const(4), Const(2), Const(128), Const(128)))
    isAccum(x6861_d2_b0) = false
    bufferDepthOf(x6861_d2_b0) = 1
    staticDimsOf(x6861_d2_b0) = List(2, 4, 2, 128, 128)
    val x6861_d3_b0 = withCtrl(x7239) { SRAM(size=262144, banking=Strided(banks=16, stride=128)).name("x6861_d3_b0").srcCtx("StaticLSTMNetwork.scala:161:21:w") } // x6861 = SRAMNew(ArrayBuffer(Const(2), Const(4), Const(2), Const(128), Const(128)))
    isAccum(x6861_d3_b0) = false
    bufferDepthOf(x6861_d3_b0) = 1
    staticDimsOf(x6861_d3_b0) = List(2, 4, 2, 128, 128)
    val x6861_d4_b0 = withCtrl(x7239) { SRAM(size=262144, banking=Strided(banks=16, stride=128)).name("x6861_d4_b0").srcCtx("StaticLSTMNetwork.scala:161:21:w") } // x6861 = SRAMNew(ArrayBuffer(Const(2), Const(4), Const(2), Const(128), Const(128)))
    isAccum(x6861_d4_b0) = false
    bufferDepthOf(x6861_d4_b0) = 1
    staticDimsOf(x6861_d4_b0) = List(2, 4, 2, 128, 128)
    val x6861_d5_b0 = withCtrl(x7239) { SRAM(size=262144, banking=Strided(banks=16, stride=128)).name("x6861_d5_b0").srcCtx("StaticLSTMNetwork.scala:161:21:w") } // x6861 = SRAMNew(ArrayBuffer(Const(2), Const(4), Const(2), Const(128), Const(128)))
    isAccum(x6861_d5_b0) = false
    bufferDepthOf(x6861_d5_b0) = 1
    staticDimsOf(x6861_d5_b0) = List(2, 4, 2, 128, 128)
    val x6861_d6_b0 = withCtrl(x7239) { SRAM(size=262144, banking=Strided(banks=16, stride=128)).name("x6861_d6_b0").srcCtx("StaticLSTMNetwork.scala:161:21:w") } // x6861 = SRAMNew(ArrayBuffer(Const(2), Const(4), Const(2), Const(128), Const(128)))
    isAccum(x6861_d6_b0) = false
    bufferDepthOf(x6861_d6_b0) = 1
    staticDimsOf(x6861_d6_b0) = List(2, 4, 2, 128, 128)
    val x6861_d7_b0 = withCtrl(x7239) { SRAM(size=262144, banking=Strided(banks=16, stride=128)).name("x6861_d7_b0").srcCtx("StaticLSTMNetwork.scala:161:21:w") } // x6861 = SRAMNew(ArrayBuffer(Const(2), Const(4), Const(2), Const(128), Const(128)))
    isAccum(x6861_d7_b0) = false
    bufferDepthOf(x6861_d7_b0) = 1
    staticDimsOf(x6861_d7_b0) = List(2, 4, 2, 128, 128)
    val x6862 = withCtrl(x7239) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x6862").srcCtx("StaticLSTMNetwork.scala:162:8") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x6863 = withCtrl(x7239) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x6863").srcCtx("StaticLSTMNetwork.scala:162:8") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x6864 = withCtrl(x7239) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x6864").srcCtx("StaticLSTMNetwork.scala:162:8") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x6865 = withCtrl(x7239) { Counter(min=Const(0), max=Const(128), step=Const(1), par=1).name("x6865").srcCtx("StaticLSTMNetwork.scala:162:8") } // CounterNew(Const(0),Const(128),Const(1),Const(1))
    val x6866 = withCtrl(x7239) { CounterChain(List(x6862,x6863,x6864,x6865)).name("x6866").srcCtx("StaticLSTMNetwork.scala:162:8") } // CounterChainNew(List(x6862, x6863, x6864, x6865))
    val x6903 = withCtrl(x7239) { LoopController(style=StreamPipe, level=OuterControl, cchain=x6866).name("x6903").srcCtx("StaticLSTMNetwork.scala:162:8") } // UnrolledForeach(List(Const(true)),x6866,Block(Const(())),List(List(b4285), List(b4286), List(b4287), List(b4288)),List(List(b4289), List(b4290), List(b4291), List(b4292)))
    val b4285 = withCtrl(x6903) { CounterIter(x6862, Some(0)).name("b4285") } // b4285
    val b4289 = withCtrl(x6903) { Const(true).name("b4289") } // b4289
    val b4286 = withCtrl(x6903) { CounterIter(x6863, Some(0)).name("b4286") } // b4286
    val b4290 = withCtrl(x6903) { Const(true).name("b4290") } // b4290
    val b4287 = withCtrl(x6903) { CounterIter(x6864, Some(0)).name("b4287") } // b4287
    val b4291 = withCtrl(x6903) { Const(true).name("b4291") } // b4291
    val b4288 = withCtrl(x6903) { CounterIter(x6865, Some(0)).name("b4288") } // b4288
    val b4292 = withCtrl(x6903) { Const(true).name("b4292") } // b4292
    val b7262 = withCtrl(x6903) { StreamOut(field="offset").name("b7262").srcCtx("StaticLSTMNetwork.scala:162:8") } // x6867 = StreamOutNew(BurstCmdBus)
    isAccum(b7262) = false
    bufferDepthOf(b7262) = 1
    val b7263 = withCtrl(x6903) { StreamOut(field="size").name("b7263").srcCtx("StaticLSTMNetwork.scala:162:8") } // x6867 = StreamOutNew(BurstCmdBus)
    isAccum(b7263) = false
    bufferDepthOf(b7263) = 1
    val x6868 = withCtrl(x6903) { StreamIn(field="data").name("x6868").srcCtx("StaticLSTMNetwork.scala:162:8") } // x6868 = StreamInNew(BurstDataBus())
    isAccum(x6868) = false
    bufferDepthOf(x6868) = 1
    val x6891 = withCtrl(x6903) { UnitController(style=SeqPipe, level=InnerControl).name("x6891").srcCtx("StaticLSTMNetwork.scala:162:8") } // UnitPipe(List(b4289, b4290, b4291, b4292),Block(x6890))
    val x6869 = withCtrl(x6891) { b4285 } // FixConvert(b4285,TRUE,_32,_0) (Same Type. No op)
    val x6870 = withCtrl(x6891) { OpDef(op=FixSla, inputs=List(x6869, Const(17))).name("x6870").srcCtx("StaticLSTMNetwork.scala:162:8") } // FixLsh(x6869,Const(17))
    val x6871 = withCtrl(x6891) { b4286 } // FixConvert(b4286,TRUE,_32,_0) (Same Type. No op)
    val x6872 = withCtrl(x6891) { OpDef(op=FixSla, inputs=List(x6871, Const(15))).name("x6872").srcCtx("StaticLSTMNetwork.scala:162:8") } // FixLsh(x6871,Const(15))
    val x6873 = withCtrl(x6891) { b4287 } // FixConvert(b4287,TRUE,_32,_0) (Same Type. No op)
    val x6874 = withCtrl(x6891) { OpDef(op=FixSla, inputs=List(x6873, Const(14))).name("x6874").srcCtx("StaticLSTMNetwork.scala:162:8") } // FixLsh(x6873,Const(14))
    val x6875 = withCtrl(x6891) { b4288 } // FixConvert(b4288,TRUE,_32,_0) (Same Type. No op)
    val x6876 = withCtrl(x6891) { OpDef(op=FixSla, inputs=List(x6875, Const(7))).name("x6876").srcCtx("StaticLSTMNetwork.scala:162:8") } // FixLsh(x6875,Const(7))
    val x6877 = withCtrl(x6891) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x6878 = withCtrl(x6891) { OpDef(op=FixAdd, inputs=List(x6870, x6872)).name("x6878").srcCtx("StaticLSTMNetwork.scala:162:8") } // FixAdd(x6870,x6872)
    val x6879 = withCtrl(x6891) { OpDef(op=FixAdd, inputs=List(x6874, x6876)).name("x6879").srcCtx("StaticLSTMNetwork.scala:162:8") } // FixAdd(x6874,x6876)
    val x6880 = withCtrl(x6891) { OpDef(op=FixAdd, inputs=List(x6878, x6879)).name("x6880").srcCtx("StaticLSTMNetwork.scala:162:8") } // FixAdd(x6878,x6879)
    val x6881 = withCtrl(x6891) { OpDef(op=FixAdd, inputs=List(x6880, x6877)).name("x6881").srcCtx("StaticLSTMNetwork.scala:162:8") } // FixAdd(x6880,x6877)
    val x6882 = withCtrl(x6891) { OpDef(op=FixSla, inputs=List(x6881, Const(2))).name("x6882").srcCtx("StaticLSTMNetwork.scala:162:8") } // FixLsh(x6881,Const(2))
    val x6883 = withCtrl(x6891) { x6882 } // FixConvert(x6882,TRUE,_64,_0)
    val x6884 = withCtrl(x6891) { DramAddress(x6777).name("x6884").srcCtx("StaticLSTMNetwork.scala:162:8") } // GetDRAMAddress(x6777)
    val x6886_x6885 = withCtrl(x6891) { OpDef(op=FixAdd, inputs=List(x6883, x6884)).name("x6886_x6885").srcCtx("StaticLSTMNetwork.scala:162:8") } // FixAdd(x6883,x6884)
    // x6886 = SimpleStruct(ArrayBuffer((offset,x6885), (size,Const(512)), (isLoad,Const(true))))
    val x6887 = withCtrl(x6891) { OpDef(op=BitAnd, inputs=List(b4289, b4290)).name("x6887").srcCtx("UnrollingBase.scala:28:66") } // And(b4289,b4290)
    val x6888 = withCtrl(x6891) { OpDef(op=BitAnd, inputs=List(b4291, b4292)).name("x6888").srcCtx("UnrollingBase.scala:28:66") } // And(b4291,b4292)
    val x6889 = withCtrl(x6891) { OpDef(op=BitAnd, inputs=List(x6887, x6888)).name("x6889").srcCtx("UnrollingBase.scala:28:66") } // And(x6887,x6888)
    val x6890_b7264_b7262 = withCtrl(x6891) { WriteMem(b7262, x6886_x6885).name("x6890_b7264_b7262").srcCtx("StaticLSTMNetwork.scala:162:8") } // StreamWrite(x6867,x6886,x6889)
    val x6890_b7265_b7263 = withCtrl(x6891) { WriteMem(b7263, Const(512)).name("x6890_b7265_b7263").srcCtx("StaticLSTMNetwork.scala:162:8") } // StreamWrite(x6867,x6886,x6889)
    val x6892 = withCtrl(x6903) { FringeDenseLoad(dram=List(x6777), cmdStream=List(b7262, b7263), dataStream=List(x6868)).name("x6892").srcCtx("StaticLSTMNetwork.scala:162:8") } // FringeDenseLoad(x6777,x6867,x6868)
    val x6893 = withCtrl(x6903) { Counter(min=Const(0), max=Const(128), step=Const(1), par=1).name("x6893").srcCtx("StaticLSTMNetwork.scala:162:8") } // CounterNew(Const(0),Const(128),Const(1),Const(1))
    val x6894 = withCtrl(x6903) { CounterChain(List(x6893)).name("x6894").srcCtx("StaticLSTMNetwork.scala:162:8") } // CounterChainNew(List(x6893))
    val x6902 = withCtrl(x6903) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6894).name("x6902").srcCtx("StaticLSTMNetwork.scala:162:8") } // UnrolledForeach(List(b4289, b4290, b4291, b4292),x6894,Block(Const(())),List(List(b4321)),List(List(b4322)))
    val b4321 = withCtrl(x6902) { CounterIter(x6893, None).name("b4321") } // b4321
    val b4322 = withCtrl(x6902) { Const(true).name("b4322") } // b4322
    val x6895 = withCtrl(x6902) { OpDef(op=BitAnd, inputs=List(b4322, b4289)).name("x6895").srcCtx("UnrollingBase.scala:28:66") } // And(b4322,b4289)
    val x6896 = withCtrl(x6902) { OpDef(op=BitAnd, inputs=List(b4290, b4291)).name("x6896").srcCtx("UnrollingBase.scala:28:66") } // And(b4290,b4291)
    val x6897 = withCtrl(x6902) { OpDef(op=BitAnd, inputs=List(x6895, x6896)).name("x6897").srcCtx("UnrollingBase.scala:28:66") } // And(x6895,x6896)
    val x6898 = withCtrl(x6902) { OpDef(op=BitAnd, inputs=List(x6897, b4292)).name("x6898").srcCtx("UnrollingBase.scala:28:66") } // And(x6897,b4292)
    val x6899_x6899 = withCtrl(x6902) { ReadMem(x6868).name("x6899_x6899").srcCtx("StaticLSTMNetwork.scala:162:8") } // ParStreamRead(x6868,List(x6898))
    val x6900_x6900 = withCtrl(x6902) { x6899_x6899 } // VectorApply(x6899,0)
    val x6901 = withCtrl(x6902) { StoreBanks(List(List(x6861_d0_b0), List(x6861_d5_b0), List(x6861_d1_b0), List(x6861_d6_b0), List(x6861_d2_b0), List(x6861_d7_b0), List(x6861_d3_b0), List(x6861_d4_b0)), List(b4285, b4286, b4287, b4288, b4321), x6900_x6900).name("x6901").srcCtx("StaticLSTMNetwork.scala:162:8") } // ParSRAMStore(x6861,List(List(b4285, b4286, b4287, b4288, b4321)),List(x6900),List(x6898))
    val x6904_d0_b0 = withCtrl(x7239) { SRAM(size=1024, banking=Strided(banks=16, stride=1)).name("x6904_d0_b0").srcCtx("StaticLSTMNetwork.scala:126:21:b") } // x6904 = SRAMNew(ArrayBuffer(Const(2), Const(4), Const(128)))
    isAccum(x6904_d0_b0) = false
    bufferDepthOf(x6904_d0_b0) = 1
    staticDimsOf(x6904_d0_b0) = List(2, 4, 128)
    val x6904_d1_b0 = withCtrl(x7239) { SRAM(size=1024, banking=Strided(banks=16, stride=1)).name("x6904_d1_b0").srcCtx("StaticLSTMNetwork.scala:126:21:b") } // x6904 = SRAMNew(ArrayBuffer(Const(2), Const(4), Const(128)))
    isAccum(x6904_d1_b0) = false
    bufferDepthOf(x6904_d1_b0) = 1
    staticDimsOf(x6904_d1_b0) = List(2, 4, 128)
    val x6904_d2_b0 = withCtrl(x7239) { SRAM(size=1024, banking=Strided(banks=16, stride=1)).name("x6904_d2_b0").srcCtx("StaticLSTMNetwork.scala:126:21:b") } // x6904 = SRAMNew(ArrayBuffer(Const(2), Const(4), Const(128)))
    isAccum(x6904_d2_b0) = false
    bufferDepthOf(x6904_d2_b0) = 1
    staticDimsOf(x6904_d2_b0) = List(2, 4, 128)
    val x6904_d3_b0 = withCtrl(x7239) { SRAM(size=1024, banking=Strided(banks=16, stride=1)).name("x6904_d3_b0").srcCtx("StaticLSTMNetwork.scala:126:21:b") } // x6904 = SRAMNew(ArrayBuffer(Const(2), Const(4), Const(128)))
    isAccum(x6904_d3_b0) = false
    bufferDepthOf(x6904_d3_b0) = 1
    staticDimsOf(x6904_d3_b0) = List(2, 4, 128)
    val x6905 = withCtrl(x7239) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x6905").srcCtx("StaticLSTMNetwork.scala:127:8") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x6906 = withCtrl(x7239) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x6906").srcCtx("StaticLSTMNetwork.scala:127:8") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x6907 = withCtrl(x7239) { CounterChain(List(x6905,x6906)).name("x6907").srcCtx("StaticLSTMNetwork.scala:127:8") } // CounterChainNew(List(x6905, x6906))
    val x6934 = withCtrl(x7239) { LoopController(style=StreamPipe, level=OuterControl, cchain=x6907).name("x6934").srcCtx("StaticLSTMNetwork.scala:127:8") } // UnrolledForeach(List(Const(true)),x6907,Block(Const(())),List(List(b4336), List(b4337)),List(List(b4338), List(b4339)))
    val b4336 = withCtrl(x6934) { CounterIter(x6905, Some(0)).name("b4336") } // b4336
    val b4338 = withCtrl(x6934) { Const(true).name("b4338") } // b4338
    val b4337 = withCtrl(x6934) { CounterIter(x6906, Some(0)).name("b4337") } // b4337
    val b4339 = withCtrl(x6934) { Const(true).name("b4339") } // b4339
    val b7266 = withCtrl(x6934) { StreamOut(field="offset").name("b7266").srcCtx("StaticLSTMNetwork.scala:127:8") } // x6908 = StreamOutNew(BurstCmdBus)
    isAccum(b7266) = false
    bufferDepthOf(b7266) = 1
    val b7267 = withCtrl(x6934) { StreamOut(field="size").name("b7267").srcCtx("StaticLSTMNetwork.scala:127:8") } // x6908 = StreamOutNew(BurstCmdBus)
    isAccum(b7267) = false
    bufferDepthOf(b7267) = 1
    val x6909 = withCtrl(x6934) { StreamIn(field="data").name("x6909").srcCtx("StaticLSTMNetwork.scala:127:8") } // x6909 = StreamInNew(BurstDataBus())
    isAccum(x6909) = false
    bufferDepthOf(x6909) = 1
    val x6924 = withCtrl(x6934) { UnitController(style=SeqPipe, level=InnerControl).name("x6924").srcCtx("StaticLSTMNetwork.scala:127:8") } // UnitPipe(List(b4338, b4339),Block(x6923))
    val x6910 = withCtrl(x6924) { b4336 } // FixConvert(b4336,TRUE,_32,_0) (Same Type. No op)
    val x6911 = withCtrl(x6924) { OpDef(op=FixSla, inputs=List(x6910, Const(9))).name("x6911").srcCtx("StaticLSTMNetwork.scala:127:8") } // FixLsh(x6910,Const(9))
    val x6912 = withCtrl(x6924) { b4337 } // FixConvert(b4337,TRUE,_32,_0) (Same Type. No op)
    val x6913 = withCtrl(x6924) { OpDef(op=FixSla, inputs=List(x6912, Const(7))).name("x6913").srcCtx("StaticLSTMNetwork.scala:127:8") } // FixLsh(x6912,Const(7))
    val x6914 = withCtrl(x6924) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x6915 = withCtrl(x6924) { OpDef(op=FixAdd, inputs=List(x6911, x6913)).name("x6915").srcCtx("StaticLSTMNetwork.scala:127:8") } // FixAdd(x6911,x6913)
    val x6916 = withCtrl(x6924) { OpDef(op=FixAdd, inputs=List(x6915, x6914)).name("x6916").srcCtx("StaticLSTMNetwork.scala:127:8") } // FixAdd(x6915,x6914)
    val x6917 = withCtrl(x6924) { OpDef(op=FixSla, inputs=List(x6916, Const(2))).name("x6917").srcCtx("StaticLSTMNetwork.scala:127:8") } // FixLsh(x6916,Const(2))
    val x6918 = withCtrl(x6924) { x6917 } // FixConvert(x6917,TRUE,_64,_0)
    val x6919 = withCtrl(x6924) { DramAddress(x6769).name("x6919").srcCtx("StaticLSTMNetwork.scala:127:8") } // GetDRAMAddress(x6769)
    val x6921_x6920 = withCtrl(x6924) { OpDef(op=FixAdd, inputs=List(x6918, x6919)).name("x6921_x6920").srcCtx("StaticLSTMNetwork.scala:127:8") } // FixAdd(x6918,x6919)
    // x6921 = SimpleStruct(ArrayBuffer((offset,x6920), (size,Const(512)), (isLoad,Const(true))))
    val x6922 = withCtrl(x6924) { OpDef(op=BitAnd, inputs=List(b4338, b4339)).name("x6922").srcCtx("UnrollingBase.scala:28:66") } // And(b4338,b4339)
    val x6923_b7268_b7266 = withCtrl(x6924) { WriteMem(b7266, x6921_x6920).name("x6923_b7268_b7266").srcCtx("StaticLSTMNetwork.scala:127:8") } // StreamWrite(x6908,x6921,x6922)
    val x6923_b7269_b7267 = withCtrl(x6924) { WriteMem(b7267, Const(512)).name("x6923_b7269_b7267").srcCtx("StaticLSTMNetwork.scala:127:8") } // StreamWrite(x6908,x6921,x6922)
    val x6925 = withCtrl(x6934) { FringeDenseLoad(dram=List(x6769), cmdStream=List(b7266, b7267), dataStream=List(x6909)).name("x6925").srcCtx("StaticLSTMNetwork.scala:127:8") } // FringeDenseLoad(x6769,x6908,x6909)
    val x6926 = withCtrl(x6934) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6926").srcCtx("StaticLSTMNetwork.scala:127:8") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6927 = withCtrl(x6934) { CounterChain(List(x6926)).name("x6927").srcCtx("StaticLSTMNetwork.scala:127:8") } // CounterChainNew(List(x6926))
    val x6933 = withCtrl(x6934) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6927).name("x6933").srcCtx("StaticLSTMNetwork.scala:127:8") } // UnrolledForeach(List(b4338, b4339),x6927,Block(Const(())),List(List(b4360)),List(List(b4361)))
    val b4360 = withCtrl(x6933) { CounterIter(x6926, None).name("b4360") } // b4360
    val b4361 = withCtrl(x6933) { Const(true).name("b4361") } // b4361
    val x6928 = withCtrl(x6933) { OpDef(op=BitAnd, inputs=List(b4361, b4338)).name("x6928").srcCtx("UnrollingBase.scala:28:66") } // And(b4361,b4338)
    val x6929 = withCtrl(x6933) { OpDef(op=BitAnd, inputs=List(x6928, b4339)).name("x6929").srcCtx("UnrollingBase.scala:28:66") } // And(x6928,b4339)
    val x6930_x6930 = withCtrl(x6933) { ReadMem(x6909).name("x6930_x6930").srcCtx("StaticLSTMNetwork.scala:127:8") } // ParStreamRead(x6909,List(x6929))
    val x6931_x6931 = withCtrl(x6933) { x6930_x6930 } // VectorApply(x6930,0)
    val x6932 = withCtrl(x6933) { StoreBanks(List(List(x6904_d0_b0), List(x6904_d1_b0), List(x6904_d2_b0), List(x6904_d3_b0)), List(b4336, b4337, b4360), x6931_x6931).name("x6932").srcCtx("StaticLSTMNetwork.scala:127:8") } // ParSRAMStore(x6904,List(List(b4336, b4337, b4360)),List(x6931),List(x6929))
    val x6935 = withCtrl(x7239) { Counter(min=Const(0), max=Const(8), step=Const(1), par=1).name("x6935").srcCtx("StaticLSTMNetwork.scala:227:28") } // CounterNew(Const(0),Const(8),Const(1),Const(1))
    val x6936 = withCtrl(x7239) { CounterChain(List(x6935)).name("x6936").srcCtx("StaticLSTMNetwork.scala:227:41") } // CounterChainNew(List(x6935))
    val x7210 = withCtrl(x7239) { LoopController(style=MetaPipe, level=OuterControl, cchain=x6936).name("x7210").srcCtx("StaticLSTMNetwork.scala:227:41") } // UnrolledForeach(List(Const(true)),x6936,Block(Const(())),List(List(b4371)),List(List(b4372)))
    val b4371 = withCtrl(x7210) { CounterIter(x6935, Some(0)).name("b4371") } // b4371
    val b4372 = withCtrl(x7210) { Const(true).name("b4372") } // b4372
    val x6937 = withCtrl(x7210) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x6937").srcCtx("StaticLSTMNetwork.scala:228:31") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x6938 = withCtrl(x7210) { CounterChain(List(x6937)).name("x6938").srcCtx("StaticLSTMNetwork.scala:228:45") } // CounterChainNew(List(x6937))
    val x7209 = withCtrl(x7210) { LoopController(style=MetaPipe, level=OuterControl, cchain=x6938).name("x7209").srcCtx("StaticLSTMNetwork.scala:228:45") } // UnrolledForeach(List(b4372),x6938,Block(Const(())),List(List(b4375)),List(List(b4376)))
    val b4375 = withCtrl(x7209) { CounterIter(x6937, Some(0)).name("b4375") } // b4375
    val b4376 = withCtrl(x7209) { Const(true).name("b4376") } // b4376
    val x6939 = withCtrl(x7209) { Counter(min=Const(0), max=Const(128), step=Const(1), par=1).name("x6939").srcCtx("StaticLSTMNetwork.scala:231:35") } // CounterNew(Const(0),Const(128),Const(1),Const(1))
    val x6940 = withCtrl(x7209) { CounterChain(List(x6939)).name("x6940").srcCtx("StaticLSTMNetwork.scala:231:66") } // CounterChainNew(List(x6939))
    val x7208 = withCtrl(x7209) { LoopController(style=MetaPipe, level=OuterControl, cchain=x6940).name("x7208").srcCtx("StaticLSTMNetwork.scala:231:66") } // UnrolledForeach(List(b4376, b4372),x6940,Block(Const(())),List(List(b4379)),List(List(b4380)))
    val b4379 = withCtrl(x7208) { CounterIter(x6939, Some(0)).name("b4379") } // b4379
    val b4380 = withCtrl(x7208) { Const(true).name("b4380") } // b4380
    val x6941 = withCtrl(x7208) { Reg(init=Some(0.0)).name("x6941").srcCtx("StaticLSTMNetwork.scala:232:27:i") } // x6941 = RegNew(Const(0))
    isAccum(x6941) = false
    bufferDepthOf(x6941) = 2
    val x6942 = withCtrl(x7208) { Reg(init=Some(0.0)).name("x6942").srcCtx("StaticLSTMNetwork.scala:233:27:j") } // x6942 = RegNew(Const(0))
    isAccum(x6942) = false
    bufferDepthOf(x6942) = 2
    val x6943 = withCtrl(x7208) { Reg(init=Some(0.0)).name("x6943").srcCtx("StaticLSTMNetwork.scala:234:27:f") } // x6943 = RegNew(Const(0))
    isAccum(x6943) = false
    bufferDepthOf(x6943) = 2
    val x6944 = withCtrl(x7208) { Reg(init=Some(0.0)).name("x6944").srcCtx("StaticLSTMNetwork.scala:235:27:o") } // x6944 = RegNew(Const(0))
    isAccum(x6944) = false
    bufferDepthOf(x6944) = 2
    val x7176 = withCtrl(x7208) { UnitController(style=ForkJoin, level=OuterControl).name("x7176").srcCtx("StaticLSTMNetwork.scala:259:22") } // ParallelPipe(List(b4380, b4376, b4372),Block(Const(())))
    val x6945_d0 = withCtrl(x7176) { Reg(init=Some(0.0)).name("x6945_d0").srcCtx("StaticLSTMNetwork.scala:238:38:xReduceReg") } // x6945 = RegNew(Const(0))
    isAccum(x6945_d0) = false
    bufferDepthOf(x6945_d0) = 1
    val x6945_d1 = withCtrl(x7176) { Reg(init=Some(0.0)).name("x6945_d1").srcCtx("StaticLSTMNetwork.scala:238:38:xReduceReg") } // x6945 = RegNew(Const(0))
    isAccum(x6945_d1) = true
    bufferDepthOf(x6945_d1) = 1
    val x6946_d0 = withCtrl(x7176) { Reg(init=Some(0.0)).name("x6946_d0").srcCtx("StaticLSTMNetwork.scala:239:38:hReduceReg") } // x6946 = RegNew(Const(0))
    isAccum(x6946_d0) = false
    bufferDepthOf(x6946_d0) = 1
    val x6946_d1 = withCtrl(x7176) { Reg(init=Some(0.0)).name("x6946_d1").srcCtx("StaticLSTMNetwork.scala:239:38:hReduceReg") } // x6946 = RegNew(Const(0))
    isAccum(x6946_d1) = true
    bufferDepthOf(x6946_d1) = 1
    val x6981 = withCtrl(x7176) { UnitController(style=ForkJoin, level=OuterControl).name("x6981").srcCtx("StaticLSTMNetwork.scala:241:24") } // ParallelPipe(List(b4380, b4376, b4372),Block(Const(())))
    val x6947 = withCtrl(x6981) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6947").srcCtx("StaticLSTMNetwork.scala:242:54") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6948 = withCtrl(x6981) { CounterChain(List(x6947)).name("x6948").srcCtx("StaticLSTMNetwork.scala:246:18") } // CounterChainNew(List(x6947))
    val x6963 = withCtrl(x6981) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6948).name("x6963").srcCtx("StaticLSTMNetwork.scala:246:18") } // UnrolledReduce(List(b4380, b4376, b4372),x6948,x6945,Block((x6945) => Const(())),List(List(b4389)),List(List(b4390)))
    val b4389 = withCtrl(x6963) { CounterIter(x6947, None).name("b4389") } // b4389
    val b4390 = withCtrl(x6963) { Const(true).name("b4390") } // b4390
    val x6949 = withCtrl(x6963) { OpDef(op=BitAnd, inputs=List(b4390, b4380)).name("x6949").srcCtx("UnrollingBase.scala:28:66") } // And(b4390,b4380)
    val x6950 = withCtrl(x6963) { OpDef(op=BitAnd, inputs=List(b4376, b4372)).name("x6950").srcCtx("UnrollingBase.scala:28:66") } // And(b4376,b4372)
    val x6951 = withCtrl(x6963) { OpDef(op=BitAnd, inputs=List(x6949, x6950)).name("x6951").srcCtx("UnrollingBase.scala:28:66") } // And(x6949,x6950)
    val x6952 = withCtrl(x6963) { LoadBanks(List(x6786_d4_b0), List(b4371, b4389)).name("x6952").srcCtx("StaticLSTMNetwork.scala:243:31:xEle") } // ParSRAMLoad(x6786,List(List(b4371, b4389)),List(x6951))
    val x6953 = withCtrl(x6963) { x6952 } // VectorApply(x6952,0)
    val x6954 = withCtrl(x6963) { LoadBanks(List(x6861_d7_b0), List(b4375, Const(0), Const(0), b4389, b4379)).name("x6954").srcCtx("StaticLSTMNetwork.scala:244:42") } // ParSRAMLoad(x6861,List(List(b4375, Const(0), Const(0), b4389, b4379)),List(x6951))
    val x6955 = withCtrl(x6963) { x6954 } // VectorApply(x6954,0)
    val x6956 = withCtrl(x6963) { OpDef(op=FixMul, inputs=List(x6953, x6955)).name("x6956").srcCtx("StaticLSTMNetwork.scala:244:39:xProduct") } // FixMul(x6953,x6955)
    val x6957 = withCtrl(x6963) { ReadMem(x6945_d1).name("x6957").srcCtx("StaticLSTMNetwork.scala:246:18") } // RegRead(x6945)
    val x6958 = withCtrl(x6963) { OpDef(op=FixEql, inputs=List(b4389, Const(0))).name("x6958").srcCtx("StaticLSTMNetwork.scala:246:18") } // FixEql(b4389,Const(0))
    val x6959 = withCtrl(x6963) { ReduceAccumOp(op=FixAdd, input=x6956, accum=x6957).name("x6959").srcCtx("StaticLSTMNetwork.scala:246:20") } // FixAdd(x6956,x6957)
    val x6960 = withCtrl(x6963) { OpDef(op=BitAnd, inputs=List(b4380, b4376)).name("x6960").srcCtx("UnrollingBase.scala:28:66") } // And(b4380,b4376)
    val x6961 = withCtrl(x6963) { OpDef(op=BitAnd, inputs=List(x6960, b4372)).name("x6961").srcCtx("UnrollingBase.scala:28:66") } // And(x6960,b4372)
    val x6962_x6945_d0 = withCtrl(x6963) { WriteMem(x6945_d0, x6959).name("x6962_x6945_d0").srcCtx("StaticLSTMNetwork.scala:246:18") } // RegWrite(x6945,x6959,x6961)
    antiDepsOf(x6962_x6945_d0)=List(x6957)
    val x6962_x6945_d1 = withCtrl(x6963) { WriteMem(x6945_d1, x6959).name("x6962_x6945_d1").srcCtx("StaticLSTMNetwork.scala:246:18") } // RegWrite(x6945,x6959,x6961)
    antiDepsOf(x6962_x6945_d1)=List(x6957)
    val x6964 = withCtrl(x6981) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6964").srcCtx("StaticLSTMNetwork.scala:248:54") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6965 = withCtrl(x6981) { CounterChain(List(x6964)).name("x6965").srcCtx("StaticLSTMNetwork.scala:252:18") } // CounterChainNew(List(x6964))
    val x6980 = withCtrl(x6981) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6965).name("x6980").srcCtx("StaticLSTMNetwork.scala:252:18") } // UnrolledReduce(List(b4380, b4376, b4372),x6965,x6946,Block((x6946) => Const(())),List(List(b4408)),List(List(b4409)))
    val b4408 = withCtrl(x6980) { CounterIter(x6964, None).name("b4408") } // b4408
    val b4409 = withCtrl(x6980) { Const(true).name("b4409") } // b4409
    val x6966 = withCtrl(x6980) { OpDef(op=BitAnd, inputs=List(b4409, b4380)).name("x6966").srcCtx("UnrollingBase.scala:28:66") } // And(b4409,b4380)
    val x6967 = withCtrl(x6980) { OpDef(op=BitAnd, inputs=List(b4376, b4372)).name("x6967").srcCtx("UnrollingBase.scala:28:66") } // And(b4376,b4372)
    val x6968 = withCtrl(x6980) { OpDef(op=BitAnd, inputs=List(x6966, x6967)).name("x6968").srcCtx("UnrollingBase.scala:28:66") } // And(x6966,x6967)
    val x6969 = withCtrl(x6980) { LoadBanks(List(x6836_d3_b0), List(b4375, b4408)).name("x6969").srcCtx("StaticLSTMNetwork.scala:249:31:hEle") } // ParSRAMLoad(x6836,List(List(b4375, b4408)),List(x6968))
    val x6970 = withCtrl(x6980) { x6969 } // VectorApply(x6969,0)
    val x6971 = withCtrl(x6980) { LoadBanks(List(x6861_d6_b0), List(b4375, Const(0), Const(0), b4408, b4379)).name("x6971").srcCtx("StaticLSTMNetwork.scala:250:42") } // ParSRAMLoad(x6861,List(List(b4375, Const(0), Const(0), b4408, b4379)),List(x6968))
    val x6972 = withCtrl(x6980) { x6971 } // VectorApply(x6971,0)
    val x6973 = withCtrl(x6980) { OpDef(op=FixMul, inputs=List(x6970, x6972)).name("x6973").srcCtx("StaticLSTMNetwork.scala:250:39:hProduct") } // FixMul(x6970,x6972)
    val x6974 = withCtrl(x6980) { ReadMem(x6946_d1).name("x6974").srcCtx("StaticLSTMNetwork.scala:252:18") } // RegRead(x6946)
    val x6975 = withCtrl(x6980) { OpDef(op=FixEql, inputs=List(b4408, Const(0))).name("x6975").srcCtx("StaticLSTMNetwork.scala:252:18") } // FixEql(b4408,Const(0))
    val x6976 = withCtrl(x6980) { ReduceAccumOp(op=FixAdd, input=x6973, accum=x6974).name("x6976").srcCtx("StaticLSTMNetwork.scala:252:20") } // FixAdd(x6973,x6974)
    val x6977 = withCtrl(x6980) { OpDef(op=BitAnd, inputs=List(b4380, b4376)).name("x6977").srcCtx("UnrollingBase.scala:28:66") } // And(b4380,b4376)
    val x6978 = withCtrl(x6980) { OpDef(op=BitAnd, inputs=List(x6977, b4372)).name("x6978").srcCtx("UnrollingBase.scala:28:66") } // And(x6977,b4372)
    val x6979_x6946_d0 = withCtrl(x6980) { WriteMem(x6946_d0, x6976).name("x6979_x6946_d0").srcCtx("StaticLSTMNetwork.scala:252:18") } // RegWrite(x6946,x6976,x6978)
    antiDepsOf(x6979_x6946_d0)=List(x6974)
    val x6979_x6946_d1 = withCtrl(x6980) { WriteMem(x6946_d1, x6976).name("x6979_x6946_d1").srcCtx("StaticLSTMNetwork.scala:252:18") } // RegWrite(x6946,x6976,x6978)
    antiDepsOf(x6979_x6946_d1)=List(x6974)
    val x6982_d0 = withCtrl(x7176) { Reg(init=Some(0.0)).name("x6982_d0").srcCtx("StaticLSTMNetwork.scala:238:38:xReduceReg") } // x6982 = RegNew(Const(0))
    isAccum(x6982_d0) = false
    bufferDepthOf(x6982_d0) = 1
    val x6982_d1 = withCtrl(x7176) { Reg(init=Some(0.0)).name("x6982_d1").srcCtx("StaticLSTMNetwork.scala:238:38:xReduceReg") } // x6982 = RegNew(Const(0))
    isAccum(x6982_d1) = true
    bufferDepthOf(x6982_d1) = 1
    val x6983_d0 = withCtrl(x7176) { Reg(init=Some(0.0)).name("x6983_d0").srcCtx("StaticLSTMNetwork.scala:239:38:hReduceReg") } // x6983 = RegNew(Const(0))
    isAccum(x6983_d0) = false
    bufferDepthOf(x6983_d0) = 1
    val x6983_d1 = withCtrl(x7176) { Reg(init=Some(0.0)).name("x6983_d1").srcCtx("StaticLSTMNetwork.scala:239:38:hReduceReg") } // x6983 = RegNew(Const(0))
    isAccum(x6983_d1) = true
    bufferDepthOf(x6983_d1) = 1
    val x7002 = withCtrl(x7176) { UnitController(style=SeqPipe, level=InnerControl).name("x7002").srcCtx("StaticLSTMNetwork.scala:259:22") } // UnitPipe(List(b4380, b4376, b4372),Block(Const(())))
    val x6984 = withCtrl(x7002) { ReadMem(x6946_d0).name("x6984").srcCtx("StaticLSTMNetwork.scala:255:58") } // RegRead(x6946)
    val x6985 = withCtrl(x7002) { ReadMem(x6945_d0).name("x6985").srcCtx("StaticLSTMNetwork.scala:255:39") } // RegRead(x6945)
    val x6986 = withCtrl(x7002) { OpDef(op=FixAdd, inputs=List(x6985, x6984)).name("x6986").srcCtx("StaticLSTMNetwork.scala:255:45") } // FixAdd(x6985,x6984)
    val x6987 = withCtrl(x7002) { OpDef(op=BitAnd, inputs=List(b4380, b4376)).name("x6987").srcCtx("UnrollingBase.scala:28:66") } // And(b4380,b4376)
    val x6988 = withCtrl(x7002) { OpDef(op=BitAnd, inputs=List(x6987, b4372)).name("x6988").srcCtx("UnrollingBase.scala:28:66") } // And(x6987,b4372)
    val x6989 = withCtrl(x7002) { LoadBanks(List(x6904_d3_b0), List(b4375, Const(0), b4379)).name("x6989").srcCtx("StaticLSTMNetwork.scala:255:67") } // SRAMLoad(x6904,ArrayBuffer(Const(2), Const(4), Const(128)),List(b4375, Const(0), b4379),Const(0),x6988)
    val x6990 = withCtrl(x7002) { OpDef(op=FixAdd, inputs=List(x6986, x6989)).name("x6990").srcCtx("StaticLSTMNetwork.scala:255:64:result") } // FixAdd(x6986,x6989)
    val x6991 = withCtrl(x7002) { OpDef(op=FixLt, inputs=List(x6990, Const(-2.5))).name("x6991").srcCtx("StaticLSTMNetwork.scala:200:21") } // FixLt(x6990,Const(-2.5))
    val x6992 = withCtrl(x7002) { OpDef(op=BitNot, inputs=List(x6991)).name("x6992").srcCtx("StaticLSTMNetwork.scala:200:14") } // Not(x6991)
    val x6993 = withCtrl(x7002) { OpDef(op=FixLeq, inputs=List(Const(2.5), x6990)).name("x6993").srcCtx("StaticLSTMNetwork.scala:200:49") } // FixLeq(Const(2.5),x6990)
    val x6994 = withCtrl(x7002) { OpDef(op=BitAnd, inputs=List(x6993, x6992)).name("x6994").srcCtx("StaticLSTMNetwork.scala:200:14") } // And(x6993,x6992)
    val x6995 = withCtrl(x7002) { OpDef(op=BitNot, inputs=List(x6993)).name("x6995").srcCtx("StaticLSTMNetwork.scala:200:14") } // Not(x6993)
    val x6996 = withCtrl(x7002) { OpDef(op=BitAnd, inputs=List(x6995, x6992)).name("x6996").srcCtx("StaticLSTMNetwork.scala:200:14") } // And(x6995,x6992)
    val x6997 = withCtrl(x7002) { OpDef(op=FixMul, inputs=List(Const(0.19999999), x6990)).name("x6997").srcCtx("StaticLSTMNetwork.scala:200:78") } // FixMul(Const(0.199999988079071044921875),x6990)
    val x6998 = withCtrl(x7002) { OpDef(op=FixAdd, inputs=List(x6997, Const(0.5))).name("x6998").srcCtx("StaticLSTMNetwork.scala:200:83") } // FixAdd(x6997,Const(0.5))
    val x6999 = withCtrl(x7002) { OpDef(op=MuxOp, inputs=List(x6994, Const(1.0), x6998)).name("x6999").srcCtx("StaticLSTMNetwork.scala:200:14") } // Mux(x6994,Const(1),x6998)
    val x7000 = withCtrl(x7002) { OpDef(op=MuxOp, inputs=List(x6991, Const(0.0), x6999)).name("x7000").srcCtx("StaticLSTMNetwork.scala:200:14") } // Mux(x6991,Const(0),x6999)
    val x7001_x6941 = withCtrl(x7002) { WriteMem(x6941, x7000).name("x7001_x6941").srcCtx("StaticLSTMNetwork.scala:260:17") } // RegWrite(x6941,x7000,x6988)
    val x7037 = withCtrl(x7176) { UnitController(style=ForkJoin, level=OuterControl).name("x7037").srcCtx("StaticLSTMNetwork.scala:241:24") } // ParallelPipe(List(b4380, b4376, b4372),Block(Const(())))
    val x7003 = withCtrl(x7037) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x7003").srcCtx("StaticLSTMNetwork.scala:242:54") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x7004 = withCtrl(x7037) { CounterChain(List(x7003)).name("x7004").srcCtx("StaticLSTMNetwork.scala:246:18") } // CounterChainNew(List(x7003))
    val x7019 = withCtrl(x7037) { LoopController(style=InnerPipe, level=InnerControl, cchain=x7004).name("x7019").srcCtx("StaticLSTMNetwork.scala:246:18") } // UnrolledReduce(List(b4380, b4376, b4372),x7004,x6982,Block((x6982) => Const(())),List(List(b4449)),List(List(b4450)))
    val b4449 = withCtrl(x7019) { CounterIter(x7003, None).name("b4449") } // b4449
    val b4450 = withCtrl(x7019) { Const(true).name("b4450") } // b4450
    val x7005 = withCtrl(x7019) { OpDef(op=BitAnd, inputs=List(b4450, b4380)).name("x7005").srcCtx("UnrollingBase.scala:28:66") } // And(b4450,b4380)
    val x7006 = withCtrl(x7019) { OpDef(op=BitAnd, inputs=List(b4376, b4372)).name("x7006").srcCtx("UnrollingBase.scala:28:66") } // And(b4376,b4372)
    val x7007 = withCtrl(x7019) { OpDef(op=BitAnd, inputs=List(x7005, x7006)).name("x7007").srcCtx("UnrollingBase.scala:28:66") } // And(x7005,x7006)
    val x7008 = withCtrl(x7019) { LoadBanks(List(x6786_d3_b0), List(b4371, b4449)).name("x7008").srcCtx("StaticLSTMNetwork.scala:243:31:xEle") } // ParSRAMLoad(x6786,List(List(b4371, b4449)),List(x7007))
    val x7009 = withCtrl(x7019) { x7008 } // VectorApply(x7008,0)
    val x7010 = withCtrl(x7019) { LoadBanks(List(x6861_d5_b0), List(b4375, Const(1), Const(0), b4449, b4379)).name("x7010").srcCtx("StaticLSTMNetwork.scala:244:42") } // ParSRAMLoad(x6861,List(List(b4375, Const(1), Const(0), b4449, b4379)),List(x7007))
    val x7011 = withCtrl(x7019) { x7010 } // VectorApply(x7010,0)
    val x7012 = withCtrl(x7019) { OpDef(op=FixMul, inputs=List(x7009, x7011)).name("x7012").srcCtx("StaticLSTMNetwork.scala:244:39:xProduct") } // FixMul(x7009,x7011)
    val x7013 = withCtrl(x7019) { ReadMem(x6982_d1).name("x7013").srcCtx("StaticLSTMNetwork.scala:246:18") } // RegRead(x6982)
    val x7014 = withCtrl(x7019) { OpDef(op=FixEql, inputs=List(b4449, Const(0))).name("x7014").srcCtx("StaticLSTMNetwork.scala:246:18") } // FixEql(b4449,Const(0))
    val x7015 = withCtrl(x7019) { ReduceAccumOp(op=FixAdd, input=x7012, accum=x7013).name("x7015").srcCtx("StaticLSTMNetwork.scala:246:20") } // FixAdd(x7012,x7013)
    val x7016 = withCtrl(x7019) { OpDef(op=BitAnd, inputs=List(b4380, b4376)).name("x7016").srcCtx("UnrollingBase.scala:28:66") } // And(b4380,b4376)
    val x7017 = withCtrl(x7019) { OpDef(op=BitAnd, inputs=List(x7016, b4372)).name("x7017").srcCtx("UnrollingBase.scala:28:66") } // And(x7016,b4372)
    val x7018_x6982_d0 = withCtrl(x7019) { WriteMem(x6982_d0, x7015).name("x7018_x6982_d0").srcCtx("StaticLSTMNetwork.scala:246:18") } // RegWrite(x6982,x7015,x7017)
    antiDepsOf(x7018_x6982_d0)=List(x7013)
    val x7018_x6982_d1 = withCtrl(x7019) { WriteMem(x6982_d1, x7015).name("x7018_x6982_d1").srcCtx("StaticLSTMNetwork.scala:246:18") } // RegWrite(x6982,x7015,x7017)
    antiDepsOf(x7018_x6982_d1)=List(x7013)
    val x7020 = withCtrl(x7037) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x7020").srcCtx("StaticLSTMNetwork.scala:248:54") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x7021 = withCtrl(x7037) { CounterChain(List(x7020)).name("x7021").srcCtx("StaticLSTMNetwork.scala:252:18") } // CounterChainNew(List(x7020))
    val x7036 = withCtrl(x7037) { LoopController(style=InnerPipe, level=InnerControl, cchain=x7021).name("x7036").srcCtx("StaticLSTMNetwork.scala:252:18") } // UnrolledReduce(List(b4380, b4376, b4372),x7021,x6983,Block((x6983) => Const(())),List(List(b4468)),List(List(b4469)))
    val b4468 = withCtrl(x7036) { CounterIter(x7020, None).name("b4468") } // b4468
    val b4469 = withCtrl(x7036) { Const(true).name("b4469") } // b4469
    val x7022 = withCtrl(x7036) { OpDef(op=BitAnd, inputs=List(b4469, b4380)).name("x7022").srcCtx("UnrollingBase.scala:28:66") } // And(b4469,b4380)
    val x7023 = withCtrl(x7036) { OpDef(op=BitAnd, inputs=List(b4376, b4372)).name("x7023").srcCtx("UnrollingBase.scala:28:66") } // And(b4376,b4372)
    val x7024 = withCtrl(x7036) { OpDef(op=BitAnd, inputs=List(x7022, x7023)).name("x7024").srcCtx("UnrollingBase.scala:28:66") } // And(x7022,x7023)
    val x7025 = withCtrl(x7036) { LoadBanks(List(x6836_d2_b0), List(b4375, b4468)).name("x7025").srcCtx("StaticLSTMNetwork.scala:249:31:hEle") } // ParSRAMLoad(x6836,List(List(b4375, b4468)),List(x7024))
    val x7026 = withCtrl(x7036) { x7025 } // VectorApply(x7025,0)
    val x7027 = withCtrl(x7036) { LoadBanks(List(x6861_d4_b0), List(b4375, Const(1), Const(0), b4468, b4379)).name("x7027").srcCtx("StaticLSTMNetwork.scala:250:42") } // ParSRAMLoad(x6861,List(List(b4375, Const(1), Const(0), b4468, b4379)),List(x7024))
    val x7028 = withCtrl(x7036) { x7027 } // VectorApply(x7027,0)
    val x7029 = withCtrl(x7036) { OpDef(op=FixMul, inputs=List(x7026, x7028)).name("x7029").srcCtx("StaticLSTMNetwork.scala:250:39:hProduct") } // FixMul(x7026,x7028)
    val x7030 = withCtrl(x7036) { ReadMem(x6983_d1).name("x7030").srcCtx("StaticLSTMNetwork.scala:252:18") } // RegRead(x6983)
    val x7031 = withCtrl(x7036) { OpDef(op=FixEql, inputs=List(b4468, Const(0))).name("x7031").srcCtx("StaticLSTMNetwork.scala:252:18") } // FixEql(b4468,Const(0))
    val x7032 = withCtrl(x7036) { ReduceAccumOp(op=FixAdd, input=x7029, accum=x7030).name("x7032").srcCtx("StaticLSTMNetwork.scala:252:20") } // FixAdd(x7029,x7030)
    val x7033 = withCtrl(x7036) { OpDef(op=BitAnd, inputs=List(b4380, b4376)).name("x7033").srcCtx("UnrollingBase.scala:28:66") } // And(b4380,b4376)
    val x7034 = withCtrl(x7036) { OpDef(op=BitAnd, inputs=List(x7033, b4372)).name("x7034").srcCtx("UnrollingBase.scala:28:66") } // And(x7033,b4372)
    val x7035_x6983_d0 = withCtrl(x7036) { WriteMem(x6983_d0, x7032).name("x7035_x6983_d0").srcCtx("StaticLSTMNetwork.scala:252:18") } // RegWrite(x6983,x7032,x7034)
    antiDepsOf(x7035_x6983_d0)=List(x7030)
    val x7035_x6983_d1 = withCtrl(x7036) { WriteMem(x6983_d1, x7032).name("x7035_x6983_d1").srcCtx("StaticLSTMNetwork.scala:252:18") } // RegWrite(x6983,x7032,x7034)
    antiDepsOf(x7035_x6983_d1)=List(x7030)
    val x7038_d0 = withCtrl(x7176) { Reg(init=Some(0.0)).name("x7038_d0").srcCtx("StaticLSTMNetwork.scala:238:38:xReduceReg") } // x7038 = RegNew(Const(0))
    isAccum(x7038_d0) = false
    bufferDepthOf(x7038_d0) = 1
    val x7038_d1 = withCtrl(x7176) { Reg(init=Some(0.0)).name("x7038_d1").srcCtx("StaticLSTMNetwork.scala:238:38:xReduceReg") } // x7038 = RegNew(Const(0))
    isAccum(x7038_d1) = true
    bufferDepthOf(x7038_d1) = 1
    val x7039_d0 = withCtrl(x7176) { Reg(init=Some(0.0)).name("x7039_d0").srcCtx("StaticLSTMNetwork.scala:239:38:hReduceReg") } // x7039 = RegNew(Const(0))
    isAccum(x7039_d0) = false
    bufferDepthOf(x7039_d0) = 1
    val x7039_d1 = withCtrl(x7176) { Reg(init=Some(0.0)).name("x7039_d1").srcCtx("StaticLSTMNetwork.scala:239:38:hReduceReg") } // x7039 = RegNew(Const(0))
    isAccum(x7039_d1) = true
    bufferDepthOf(x7039_d1) = 1
    val x7064 = withCtrl(x7176) { UnitController(style=SeqPipe, level=InnerControl).name("x7064").srcCtx("StaticLSTMNetwork.scala:259:22") } // UnitPipe(List(b4380, b4376, b4372),Block(Const(())))
    val x7040 = withCtrl(x7064) { ReadMem(x6983_d0).name("x7040").srcCtx("StaticLSTMNetwork.scala:255:58") } // RegRead(x6983)
    val x7041 = withCtrl(x7064) { ReadMem(x6982_d0).name("x7041").srcCtx("StaticLSTMNetwork.scala:255:39") } // RegRead(x6982)
    val x7042 = withCtrl(x7064) { OpDef(op=FixAdd, inputs=List(x7041, x7040)).name("x7042").srcCtx("StaticLSTMNetwork.scala:255:45") } // FixAdd(x7041,x7040)
    val x7043 = withCtrl(x7064) { OpDef(op=BitAnd, inputs=List(b4380, b4376)).name("x7043").srcCtx("UnrollingBase.scala:28:66") } // And(b4380,b4376)
    val x7044 = withCtrl(x7064) { OpDef(op=BitAnd, inputs=List(x7043, b4372)).name("x7044").srcCtx("UnrollingBase.scala:28:66") } // And(x7043,b4372)
    val x7045 = withCtrl(x7064) { LoadBanks(List(x6904_d2_b0), List(b4375, Const(1), b4379)).name("x7045").srcCtx("StaticLSTMNetwork.scala:255:67") } // SRAMLoad(x6904,ArrayBuffer(Const(2), Const(4), Const(128)),List(b4375, Const(1), b4379),Const(0),x7044)
    val x7046 = withCtrl(x7064) { OpDef(op=FixAdd, inputs=List(x7042, x7045)).name("x7046").srcCtx("StaticLSTMNetwork.scala:255:64:result") } // FixAdd(x7042,x7045)
    val x7047 = withCtrl(x7064) { OpDef(op=FixAbs, inputs=List(x7046)).name("x7047").srcCtx("StaticLSTMNetwork.scala:179:20:absin") } // FixAbs(x7046)
    val x7048 = withCtrl(x7064) { OpDef(op=FixSra, inputs=List(x7047, Const(2))).name("x7048").srcCtx("StaticLSTMNetwork.scala:180:22:div4") } // FixRsh(x7047,Const(2))
    val x7049 = withCtrl(x7064) { OpDef(op=FixAdd, inputs=List(x7048, Const(0.375))).name("x7049").srcCtx("StaticLSTMNetwork.scala:181:19:li") } // FixAdd(x7048,Const(0.375))
    val x7050 = withCtrl(x7064) { OpDef(op=FixLt, inputs=List(Const(2.5), x7047)).name("x7050").srcCtx("StaticLSTMNetwork.scala:182:28") } // FixLt(Const(2.5),x7047)
    val x7051 = withCtrl(x7064) { OpDef(op=BitNot, inputs=List(x7050)).name("x7051").srcCtx("StaticLSTMNetwork.scala:182:18") } // Not(x7050)
    val x7052 = withCtrl(x7064) { OpDef(op=FixLt, inputs=List(Const(0.5), x7047)).name("x7052").srcCtx("StaticLSTMNetwork.scala:183:19") } // FixLt(Const(0.5),x7047)
    val x7053 = withCtrl(x7064) { OpDef(op=FixLt, inputs=List(x7047, Const(2.5))).name("x7053").srcCtx("StaticLSTMNetwork.scala:183:36") } // FixLt(x7047,Const(2.5))
    val x7054 = withCtrl(x7064) { OpDef(op=BitAnd, inputs=List(x7052, x7053)).name("x7054").srcCtx("StaticLSTMNetwork.scala:183:27") } // And(x7052,x7053)
    val x7055 = withCtrl(x7064) { OpDef(op=BitAnd, inputs=List(x7054, x7051)).name("x7055").srcCtx("StaticLSTMNetwork.scala:182:18") } // And(x7054,x7051)
    val x7056 = withCtrl(x7064) { OpDef(op=BitNot, inputs=List(x7054)).name("x7056").srcCtx("StaticLSTMNetwork.scala:182:18") } // Not(x7054)
    val x7057 = withCtrl(x7064) { OpDef(op=BitAnd, inputs=List(x7056, x7051)).name("x7057").srcCtx("StaticLSTMNetwork.scala:182:18") } // And(x7056,x7051)
    val x7058 = withCtrl(x7064) { OpDef(op=MuxOp, inputs=List(x7055, x7049, x7047)).name("x7058").srcCtx("StaticLSTMNetwork.scala:182:18") } // Mux(x7055,x7049,x7047)
    val x7059 = withCtrl(x7064) { OpDef(op=MuxOp, inputs=List(x7050, Const(1.0), x7058)).name("x7059").srcCtx("StaticLSTMNetwork.scala:182:18") } // Mux(x7050,Const(1),x7058)
    def split1 = {
    val x7060 = withCtrl(x7064) { OpDef(op=FixNeg, inputs=List(x7059)).name("x7060").srcCtx("StaticLSTMNetwork.scala:185:23:negout") } // FixNeg(x7059)
    val x7061 = withCtrl(x7064) { OpDef(op=FixLt, inputs=List(x7046, Const(0.0))).name("x7061").srcCtx("StaticLSTMNetwork.scala:186:21") } // FixLt(x7046,Const(0))
    val x7062 = withCtrl(x7064) { OpDef(op=MuxOp, inputs=List(x7061, x7060, x7059)).name("x7062").srcCtx("StaticLSTMNetwork.scala:186:17:re") } // Mux(x7061,x7060,x7059)
    val x7063_x6942 = withCtrl(x7064) { WriteMem(x6942, x7062).name("x7063_x6942").srcCtx("StaticLSTMNetwork.scala:261:17") } // RegWrite(x6942,x7062,x7044)
    val x7099 = withCtrl(x7176) { UnitController(style=ForkJoin, level=OuterControl).name("x7099").srcCtx("StaticLSTMNetwork.scala:241:24") } // ParallelPipe(List(b4380, b4376, b4372),Block(Const(())))
    val x7065 = withCtrl(x7099) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x7065").srcCtx("StaticLSTMNetwork.scala:242:54") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x7066 = withCtrl(x7099) { CounterChain(List(x7065)).name("x7066").srcCtx("StaticLSTMNetwork.scala:246:18") } // CounterChainNew(List(x7065))
    val x7081 = withCtrl(x7099) { LoopController(style=InnerPipe, level=InnerControl, cchain=x7066).name("x7081").srcCtx("StaticLSTMNetwork.scala:246:18") } // UnrolledReduce(List(b4380, b4376, b4372),x7066,x7038,Block((x7038) => Const(())),List(List(b4515)),List(List(b4516)))
    val b4515 = withCtrl(x7081) { CounterIter(x7065, None).name("b4515") } // b4515
    val b4516 = withCtrl(x7081) { Const(true).name("b4516") } // b4516
    val x7067 = withCtrl(x7081) { OpDef(op=BitAnd, inputs=List(b4516, b4380)).name("x7067").srcCtx("UnrollingBase.scala:28:66") } // And(b4516,b4380)
    val x7068 = withCtrl(x7081) { OpDef(op=BitAnd, inputs=List(b4376, b4372)).name("x7068").srcCtx("UnrollingBase.scala:28:66") } // And(b4376,b4372)
    val x7069 = withCtrl(x7081) { OpDef(op=BitAnd, inputs=List(x7067, x7068)).name("x7069").srcCtx("UnrollingBase.scala:28:66") } // And(x7067,x7068)
    val x7070 = withCtrl(x7081) { LoadBanks(List(x6786_d2_b0), List(b4371, b4515)).name("x7070").srcCtx("StaticLSTMNetwork.scala:243:31:xEle") } // ParSRAMLoad(x6786,List(List(b4371, b4515)),List(x7069))
    val x7071 = withCtrl(x7081) { x7070 } // VectorApply(x7070,0)
    val x7072 = withCtrl(x7081) { LoadBanks(List(x6861_d3_b0), List(b4375, Const(2), Const(0), b4515, b4379)).name("x7072").srcCtx("StaticLSTMNetwork.scala:244:42") } // ParSRAMLoad(x6861,List(List(b4375, Const(2), Const(0), b4515, b4379)),List(x7069))
    val x7073 = withCtrl(x7081) { x7072 } // VectorApply(x7072,0)
    val x7074 = withCtrl(x7081) { OpDef(op=FixMul, inputs=List(x7071, x7073)).name("x7074").srcCtx("StaticLSTMNetwork.scala:244:39:xProduct") } // FixMul(x7071,x7073)
    val x7075 = withCtrl(x7081) { ReadMem(x7038_d1).name("x7075").srcCtx("StaticLSTMNetwork.scala:246:18") } // RegRead(x7038)
    val x7076 = withCtrl(x7081) { OpDef(op=FixEql, inputs=List(b4515, Const(0))).name("x7076").srcCtx("StaticLSTMNetwork.scala:246:18") } // FixEql(b4515,Const(0))
    val x7077 = withCtrl(x7081) { ReduceAccumOp(op=FixAdd, input=x7074, accum=x7075).name("x7077").srcCtx("StaticLSTMNetwork.scala:246:20") } // FixAdd(x7074,x7075)
    val x7078 = withCtrl(x7081) { OpDef(op=BitAnd, inputs=List(b4380, b4376)).name("x7078").srcCtx("UnrollingBase.scala:28:66") } // And(b4380,b4376)
    val x7079 = withCtrl(x7081) { OpDef(op=BitAnd, inputs=List(x7078, b4372)).name("x7079").srcCtx("UnrollingBase.scala:28:66") } // And(x7078,b4372)
    val x7080_x7038_d0 = withCtrl(x7081) { WriteMem(x7038_d0, x7077).name("x7080_x7038_d0").srcCtx("StaticLSTMNetwork.scala:246:18") } // RegWrite(x7038,x7077,x7079)
    antiDepsOf(x7080_x7038_d0)=List(x7075)
    val x7080_x7038_d1 = withCtrl(x7081) { WriteMem(x7038_d1, x7077).name("x7080_x7038_d1").srcCtx("StaticLSTMNetwork.scala:246:18") } // RegWrite(x7038,x7077,x7079)
    antiDepsOf(x7080_x7038_d1)=List(x7075)
    val x7082 = withCtrl(x7099) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x7082").srcCtx("StaticLSTMNetwork.scala:248:54") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x7083 = withCtrl(x7099) { CounterChain(List(x7082)).name("x7083").srcCtx("StaticLSTMNetwork.scala:252:18") } // CounterChainNew(List(x7082))
    val x7098 = withCtrl(x7099) { LoopController(style=InnerPipe, level=InnerControl, cchain=x7083).name("x7098").srcCtx("StaticLSTMNetwork.scala:252:18") } // UnrolledReduce(List(b4380, b4376, b4372),x7083,x7039,Block((x7039) => Const(())),List(List(b4534)),List(List(b4535)))
    val b4534 = withCtrl(x7098) { CounterIter(x7082, None).name("b4534") } // b4534
    val b4535 = withCtrl(x7098) { Const(true).name("b4535") } // b4535
    val x7084 = withCtrl(x7098) { OpDef(op=BitAnd, inputs=List(b4535, b4380)).name("x7084").srcCtx("UnrollingBase.scala:28:66") } // And(b4535,b4380)
    val x7085 = withCtrl(x7098) { OpDef(op=BitAnd, inputs=List(b4376, b4372)).name("x7085").srcCtx("UnrollingBase.scala:28:66") } // And(b4376,b4372)
    val x7086 = withCtrl(x7098) { OpDef(op=BitAnd, inputs=List(x7084, x7085)).name("x7086").srcCtx("UnrollingBase.scala:28:66") } // And(x7084,x7085)
    val x7087 = withCtrl(x7098) { LoadBanks(List(x6836_d1_b0), List(b4375, b4534)).name("x7087").srcCtx("StaticLSTMNetwork.scala:249:31:hEle") } // ParSRAMLoad(x6836,List(List(b4375, b4534)),List(x7086))
    val x7088 = withCtrl(x7098) { x7087 } // VectorApply(x7087,0)
    val x7089 = withCtrl(x7098) { LoadBanks(List(x6861_d2_b0), List(b4375, Const(2), Const(0), b4534, b4379)).name("x7089").srcCtx("StaticLSTMNetwork.scala:250:42") } // ParSRAMLoad(x6861,List(List(b4375, Const(2), Const(0), b4534, b4379)),List(x7086))
    val x7090 = withCtrl(x7098) { x7089 } // VectorApply(x7089,0)
    val x7091 = withCtrl(x7098) { OpDef(op=FixMul, inputs=List(x7088, x7090)).name("x7091").srcCtx("StaticLSTMNetwork.scala:250:39:hProduct") } // FixMul(x7088,x7090)
    val x7092 = withCtrl(x7098) { ReadMem(x7039_d1).name("x7092").srcCtx("StaticLSTMNetwork.scala:252:18") } // RegRead(x7039)
    val x7093 = withCtrl(x7098) { OpDef(op=FixEql, inputs=List(b4534, Const(0))).name("x7093").srcCtx("StaticLSTMNetwork.scala:252:18") } // FixEql(b4534,Const(0))
    val x7094 = withCtrl(x7098) { ReduceAccumOp(op=FixAdd, input=x7091, accum=x7092).name("x7094").srcCtx("StaticLSTMNetwork.scala:252:20") } // FixAdd(x7091,x7092)
    val x7095 = withCtrl(x7098) { OpDef(op=BitAnd, inputs=List(b4380, b4376)).name("x7095").srcCtx("UnrollingBase.scala:28:66") } // And(b4380,b4376)
    val x7096 = withCtrl(x7098) { OpDef(op=BitAnd, inputs=List(x7095, b4372)).name("x7096").srcCtx("UnrollingBase.scala:28:66") } // And(x7095,b4372)
    val x7097_x7039_d0 = withCtrl(x7098) { WriteMem(x7039_d0, x7094).name("x7097_x7039_d0").srcCtx("StaticLSTMNetwork.scala:252:18") } // RegWrite(x7039,x7094,x7096)
    antiDepsOf(x7097_x7039_d0)=List(x7092)
    val x7097_x7039_d1 = withCtrl(x7098) { WriteMem(x7039_d1, x7094).name("x7097_x7039_d1").srcCtx("StaticLSTMNetwork.scala:252:18") } // RegWrite(x7039,x7094,x7096)
    antiDepsOf(x7097_x7039_d1)=List(x7092)
    val x7100_d0 = withCtrl(x7176) { Reg(init=Some(0.0)).name("x7100_d0").srcCtx("StaticLSTMNetwork.scala:238:38:xReduceReg") } // x7100 = RegNew(Const(0))
    isAccum(x7100_d0) = false
    bufferDepthOf(x7100_d0) = 1
    val x7100_d1 = withCtrl(x7176) { Reg(init=Some(0.0)).name("x7100_d1").srcCtx("StaticLSTMNetwork.scala:238:38:xReduceReg") } // x7100 = RegNew(Const(0))
    isAccum(x7100_d1) = true
    bufferDepthOf(x7100_d1) = 1
    val x7101_d0 = withCtrl(x7176) { Reg(init=Some(0.0)).name("x7101_d0").srcCtx("StaticLSTMNetwork.scala:239:38:hReduceReg") } // x7101 = RegNew(Const(0))
    isAccum(x7101_d0) = false
    bufferDepthOf(x7101_d0) = 1
    val x7101_d1 = withCtrl(x7176) { Reg(init=Some(0.0)).name("x7101_d1").srcCtx("StaticLSTMNetwork.scala:239:38:hReduceReg") } // x7101 = RegNew(Const(0))
    isAccum(x7101_d1) = true
    bufferDepthOf(x7101_d1) = 1
    val x7121 = withCtrl(x7176) { UnitController(style=SeqPipe, level=InnerControl).name("x7121").srcCtx("StaticLSTMNetwork.scala:259:22") } // UnitPipe(List(b4380, b4376, b4372),Block(Const(())))
    val x7102 = withCtrl(x7121) { ReadMem(x7039_d0).name("x7102").srcCtx("StaticLSTMNetwork.scala:255:58") } // RegRead(x7039)
    val x7103 = withCtrl(x7121) { ReadMem(x7038_d0).name("x7103").srcCtx("StaticLSTMNetwork.scala:255:39") } // RegRead(x7038)
    val x7104 = withCtrl(x7121) { OpDef(op=FixAdd, inputs=List(x7103, x7102)).name("x7104").srcCtx("StaticLSTMNetwork.scala:255:45") } // FixAdd(x7103,x7102)
    val x7105 = withCtrl(x7121) { OpDef(op=BitAnd, inputs=List(b4380, b4376)).name("x7105").srcCtx("UnrollingBase.scala:28:66") } // And(b4380,b4376)
    val x7106 = withCtrl(x7121) { OpDef(op=BitAnd, inputs=List(x7105, b4372)).name("x7106").srcCtx("UnrollingBase.scala:28:66") } // And(x7105,b4372)
    val x7107 = withCtrl(x7121) { LoadBanks(List(x6904_d1_b0), List(b4375, Const(2), b4379)).name("x7107").srcCtx("StaticLSTMNetwork.scala:255:67") } // SRAMLoad(x6904,ArrayBuffer(Const(2), Const(4), Const(128)),List(b4375, Const(2), b4379),Const(0),x7106)
    val x7108 = withCtrl(x7121) { OpDef(op=FixAdd, inputs=List(x7104, x7107)).name("x7108").srcCtx("StaticLSTMNetwork.scala:255:64:result") } // FixAdd(x7104,x7107)
    val x7109 = withCtrl(x7121) { OpDef(op=FixAdd, inputs=List(x7108, Const(1.0))).name("x7109").srcCtx("StaticLSTMNetwork.scala:262:42") } // FixAdd(x7108,Const(1))
    val x7110 = withCtrl(x7121) { OpDef(op=FixLt, inputs=List(x7109, Const(-2.5))).name("x7110").srcCtx("StaticLSTMNetwork.scala:200:21") } // FixLt(x7109,Const(-2.5))
    val x7111 = withCtrl(x7121) { OpDef(op=BitNot, inputs=List(x7110)).name("x7111").srcCtx("StaticLSTMNetwork.scala:200:14") } // Not(x7110)
    val x7112 = withCtrl(x7121) { OpDef(op=FixLeq, inputs=List(Const(2.5), x7109)).name("x7112").srcCtx("StaticLSTMNetwork.scala:200:49") } // FixLeq(Const(2.5),x7109)
    val x7113 = withCtrl(x7121) { OpDef(op=BitAnd, inputs=List(x7112, x7111)).name("x7113").srcCtx("StaticLSTMNetwork.scala:200:14") } // And(x7112,x7111)
    val x7114 = withCtrl(x7121) { OpDef(op=BitNot, inputs=List(x7112)).name("x7114").srcCtx("StaticLSTMNetwork.scala:200:14") } // Not(x7112)
    val x7115 = withCtrl(x7121) { OpDef(op=BitAnd, inputs=List(x7114, x7111)).name("x7115").srcCtx("StaticLSTMNetwork.scala:200:14") } // And(x7114,x7111)
    val x7116 = withCtrl(x7121) { OpDef(op=FixMul, inputs=List(Const(0.19999999), x7109)).name("x7116").srcCtx("StaticLSTMNetwork.scala:200:78") } // FixMul(Const(0.199999988079071044921875),x7109)
    val x7117 = withCtrl(x7121) { OpDef(op=FixAdd, inputs=List(x7116, Const(0.5))).name("x7117").srcCtx("StaticLSTMNetwork.scala:200:83") } // FixAdd(x7116,Const(0.5))
    val x7118 = withCtrl(x7121) { OpDef(op=MuxOp, inputs=List(x7113, Const(1.0), x7117)).name("x7118").srcCtx("StaticLSTMNetwork.scala:200:14") } // Mux(x7113,Const(1),x7117)
    val x7119 = withCtrl(x7121) { OpDef(op=MuxOp, inputs=List(x7110, Const(0.0), x7118)).name("x7119").srcCtx("StaticLSTMNetwork.scala:200:14") } // Mux(x7110,Const(0),x7118)
    val x7120_x6943 = withCtrl(x7121) { WriteMem(x6943, x7119).name("x7120_x6943").srcCtx("StaticLSTMNetwork.scala:262:17") } // RegWrite(x6943,x7119,x7106)
    val x7156 = withCtrl(x7176) { UnitController(style=ForkJoin, level=OuterControl).name("x7156").srcCtx("StaticLSTMNetwork.scala:241:24") } // ParallelPipe(List(b4380, b4376, b4372),Block(Const(())))
    val x7122 = withCtrl(x7156) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x7122").srcCtx("StaticLSTMNetwork.scala:242:54") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x7123 = withCtrl(x7156) { CounterChain(List(x7122)).name("x7123").srcCtx("StaticLSTMNetwork.scala:246:18") } // CounterChainNew(List(x7122))
    val x7138 = withCtrl(x7156) { LoopController(style=InnerPipe, level=InnerControl, cchain=x7123).name("x7138").srcCtx("StaticLSTMNetwork.scala:246:18") } // UnrolledReduce(List(b4380, b4376, b4372),x7123,x7100,Block((x7100) => Const(())),List(List(b4576)),List(List(b4577)))
    val b4576 = withCtrl(x7138) { CounterIter(x7122, None).name("b4576") } // b4576
    val b4577 = withCtrl(x7138) { Const(true).name("b4577") } // b4577
    val x7124 = withCtrl(x7138) { OpDef(op=BitAnd, inputs=List(b4577, b4380)).name("x7124").srcCtx("UnrollingBase.scala:28:66") } // And(b4577,b4380)
    val x7125 = withCtrl(x7138) { OpDef(op=BitAnd, inputs=List(b4376, b4372)).name("x7125").srcCtx("UnrollingBase.scala:28:66") } // And(b4376,b4372)
    val x7126 = withCtrl(x7138) { OpDef(op=BitAnd, inputs=List(x7124, x7125)).name("x7126").srcCtx("UnrollingBase.scala:28:66") } // And(x7124,x7125)
    val x7127 = withCtrl(x7138) { LoadBanks(List(x6786_d1_b0), List(b4371, b4576)).name("x7127").srcCtx("StaticLSTMNetwork.scala:243:31:xEle") } // ParSRAMLoad(x6786,List(List(b4371, b4576)),List(x7126))
    val x7128 = withCtrl(x7138) { x7127 } // VectorApply(x7127,0)
    val x7129 = withCtrl(x7138) { LoadBanks(List(x6861_d1_b0), List(b4375, Const(3), Const(0), b4576, b4379)).name("x7129").srcCtx("StaticLSTMNetwork.scala:244:42") } // ParSRAMLoad(x6861,List(List(b4375, Const(3), Const(0), b4576, b4379)),List(x7126))
    val x7130 = withCtrl(x7138) { x7129 } // VectorApply(x7129,0)
    val x7131 = withCtrl(x7138) { OpDef(op=FixMul, inputs=List(x7128, x7130)).name("x7131").srcCtx("StaticLSTMNetwork.scala:244:39:xProduct") } // FixMul(x7128,x7130)
    val x7132 = withCtrl(x7138) { ReadMem(x7100_d1).name("x7132").srcCtx("StaticLSTMNetwork.scala:246:18") } // RegRead(x7100)
    val x7133 = withCtrl(x7138) { OpDef(op=FixEql, inputs=List(b4576, Const(0))).name("x7133").srcCtx("StaticLSTMNetwork.scala:246:18") } // FixEql(b4576,Const(0))
    val x7134 = withCtrl(x7138) { ReduceAccumOp(op=FixAdd, input=x7131, accum=x7132).name("x7134").srcCtx("StaticLSTMNetwork.scala:246:20") } // FixAdd(x7131,x7132)
    val x7135 = withCtrl(x7138) { OpDef(op=BitAnd, inputs=List(b4380, b4376)).name("x7135").srcCtx("UnrollingBase.scala:28:66") } // And(b4380,b4376)
    val x7136 = withCtrl(x7138) { OpDef(op=BitAnd, inputs=List(x7135, b4372)).name("x7136").srcCtx("UnrollingBase.scala:28:66") } // And(x7135,b4372)
    val x7137_x7100_d0 = withCtrl(x7138) { WriteMem(x7100_d0, x7134).name("x7137_x7100_d0").srcCtx("StaticLSTMNetwork.scala:246:18") } // RegWrite(x7100,x7134,x7136)
    antiDepsOf(x7137_x7100_d0)=List(x7132)
    val x7137_x7100_d1 = withCtrl(x7138) { WriteMem(x7100_d1, x7134).name("x7137_x7100_d1").srcCtx("StaticLSTMNetwork.scala:246:18") } // RegWrite(x7100,x7134,x7136)
    antiDepsOf(x7137_x7100_d1)=List(x7132)
    val x7139 = withCtrl(x7156) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x7139").srcCtx("StaticLSTMNetwork.scala:248:54") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x7140 = withCtrl(x7156) { CounterChain(List(x7139)).name("x7140").srcCtx("StaticLSTMNetwork.scala:252:18") } // CounterChainNew(List(x7139))
    val x7155 = withCtrl(x7156) { LoopController(style=InnerPipe, level=InnerControl, cchain=x7140).name("x7155").srcCtx("StaticLSTMNetwork.scala:252:18") } // UnrolledReduce(List(b4380, b4376, b4372),x7140,x7101,Block((x7101) => Const(())),List(List(b4595)),List(List(b4596)))
    val b4595 = withCtrl(x7155) { CounterIter(x7139, None).name("b4595") } // b4595
    val b4596 = withCtrl(x7155) { Const(true).name("b4596") } // b4596
    val x7141 = withCtrl(x7155) { OpDef(op=BitAnd, inputs=List(b4596, b4380)).name("x7141").srcCtx("UnrollingBase.scala:28:66") } // And(b4596,b4380)
    val x7142 = withCtrl(x7155) { OpDef(op=BitAnd, inputs=List(b4376, b4372)).name("x7142").srcCtx("UnrollingBase.scala:28:66") } // And(b4376,b4372)
    val x7143 = withCtrl(x7155) { OpDef(op=BitAnd, inputs=List(x7141, x7142)).name("x7143").srcCtx("UnrollingBase.scala:28:66") } // And(x7141,x7142)
    val x7144 = withCtrl(x7155) { LoadBanks(List(x6836_d0_b0), List(b4375, b4595)).name("x7144").srcCtx("StaticLSTMNetwork.scala:249:31:hEle") } // ParSRAMLoad(x6836,List(List(b4375, b4595)),List(x7143))
    val x7145 = withCtrl(x7155) { x7144 } // VectorApply(x7144,0)
    val x7146 = withCtrl(x7155) { LoadBanks(List(x6861_d0_b0), List(b4375, Const(3), Const(0), b4595, b4379)).name("x7146").srcCtx("StaticLSTMNetwork.scala:250:42") } // ParSRAMLoad(x6861,List(List(b4375, Const(3), Const(0), b4595, b4379)),List(x7143))
    val x7147 = withCtrl(x7155) { x7146 } // VectorApply(x7146,0)
    val x7148 = withCtrl(x7155) { OpDef(op=FixMul, inputs=List(x7145, x7147)).name("x7148").srcCtx("StaticLSTMNetwork.scala:250:39:hProduct") } // FixMul(x7145,x7147)
    val x7149 = withCtrl(x7155) { ReadMem(x7101_d1).name("x7149").srcCtx("StaticLSTMNetwork.scala:252:18") } // RegRead(x7101)
    val x7150 = withCtrl(x7155) { OpDef(op=FixEql, inputs=List(b4595, Const(0))).name("x7150").srcCtx("StaticLSTMNetwork.scala:252:18") } // FixEql(b4595,Const(0))
    val x7151 = withCtrl(x7155) { ReduceAccumOp(op=FixAdd, input=x7148, accum=x7149).name("x7151").srcCtx("StaticLSTMNetwork.scala:252:20") } // FixAdd(x7148,x7149)
    val x7152 = withCtrl(x7155) { OpDef(op=BitAnd, inputs=List(b4380, b4376)).name("x7152").srcCtx("UnrollingBase.scala:28:66") } // And(b4380,b4376)
    val x7153 = withCtrl(x7155) { OpDef(op=BitAnd, inputs=List(x7152, b4372)).name("x7153").srcCtx("UnrollingBase.scala:28:66") } // And(x7152,b4372)
    val x7154_x7101_d0 = withCtrl(x7155) { WriteMem(x7101_d0, x7151).name("x7154_x7101_d0").srcCtx("StaticLSTMNetwork.scala:252:18") } // RegWrite(x7101,x7151,x7153)
    antiDepsOf(x7154_x7101_d0)=List(x7149)
    val x7154_x7101_d1 = withCtrl(x7155) { WriteMem(x7101_d1, x7151).name("x7154_x7101_d1").srcCtx("StaticLSTMNetwork.scala:252:18") } // RegWrite(x7101,x7151,x7153)
    antiDepsOf(x7154_x7101_d1)=List(x7149)
    val x7175 = withCtrl(x7176) { UnitController(style=SeqPipe, level=InnerControl).name("x7175").srcCtx("StaticLSTMNetwork.scala:259:22") } // UnitPipe(List(b4380, b4376, b4372),Block(Const(())))
    val x7157 = withCtrl(x7175) { ReadMem(x7101_d0).name("x7157").srcCtx("StaticLSTMNetwork.scala:255:58") } // RegRead(x7101)
    val x7158 = withCtrl(x7175) { ReadMem(x7100_d0).name("x7158").srcCtx("StaticLSTMNetwork.scala:255:39") } // RegRead(x7100)
    val x7159 = withCtrl(x7175) { OpDef(op=FixAdd, inputs=List(x7158, x7157)).name("x7159").srcCtx("StaticLSTMNetwork.scala:255:45") } // FixAdd(x7158,x7157)
    val x7160 = withCtrl(x7175) { OpDef(op=BitAnd, inputs=List(b4380, b4376)).name("x7160").srcCtx("UnrollingBase.scala:28:66") } // And(b4380,b4376)
    val x7161 = withCtrl(x7175) { OpDef(op=BitAnd, inputs=List(x7160, b4372)).name("x7161").srcCtx("UnrollingBase.scala:28:66") } // And(x7160,b4372)
    val x7162 = withCtrl(x7175) { LoadBanks(List(x6904_d0_b0), List(b4375, Const(3), b4379)).name("x7162").srcCtx("StaticLSTMNetwork.scala:255:67") } // SRAMLoad(x6904,ArrayBuffer(Const(2), Const(4), Const(128)),List(b4375, Const(3), b4379),Const(0),x7161)
    val x7163 = withCtrl(x7175) { OpDef(op=FixAdd, inputs=List(x7159, x7162)).name("x7163").srcCtx("StaticLSTMNetwork.scala:255:64:result") } // FixAdd(x7159,x7162)
    val x7164 = withCtrl(x7175) { OpDef(op=FixLt, inputs=List(x7163, Const(-2.5))).name("x7164").srcCtx("StaticLSTMNetwork.scala:200:21") } // FixLt(x7163,Const(-2.5))
    val x7165 = withCtrl(x7175) { OpDef(op=BitNot, inputs=List(x7164)).name("x7165").srcCtx("StaticLSTMNetwork.scala:200:14") } // Not(x7164)
    val x7166 = withCtrl(x7175) { OpDef(op=FixLeq, inputs=List(Const(2.5), x7163)).name("x7166").srcCtx("StaticLSTMNetwork.scala:200:49") } // FixLeq(Const(2.5),x7163)
    val x7167 = withCtrl(x7175) { OpDef(op=BitAnd, inputs=List(x7166, x7165)).name("x7167").srcCtx("StaticLSTMNetwork.scala:200:14") } // And(x7166,x7165)
    val x7168 = withCtrl(x7175) { OpDef(op=BitNot, inputs=List(x7166)).name("x7168").srcCtx("StaticLSTMNetwork.scala:200:14") } // Not(x7166)
    val x7169 = withCtrl(x7175) { OpDef(op=BitAnd, inputs=List(x7168, x7165)).name("x7169").srcCtx("StaticLSTMNetwork.scala:200:14") } // And(x7168,x7165)
    val x7170 = withCtrl(x7175) { OpDef(op=FixMul, inputs=List(Const(0.19999999), x7163)).name("x7170").srcCtx("StaticLSTMNetwork.scala:200:78") } // FixMul(Const(0.199999988079071044921875),x7163)
    val x7171 = withCtrl(x7175) { OpDef(op=FixAdd, inputs=List(x7170, Const(0.5))).name("x7171").srcCtx("StaticLSTMNetwork.scala:200:83") } // FixAdd(x7170,Const(0.5))
    val x7172 = withCtrl(x7175) { OpDef(op=MuxOp, inputs=List(x7167, Const(1.0), x7171)).name("x7172").srcCtx("StaticLSTMNetwork.scala:200:14") } // Mux(x7167,Const(1),x7171)
    val x7173 = withCtrl(x7175) { OpDef(op=MuxOp, inputs=List(x7164, Const(0.0), x7172)).name("x7173").srcCtx("StaticLSTMNetwork.scala:200:14") } // Mux(x7164,Const(0),x7172)
    val x7174_x6944 = withCtrl(x7175) { WriteMem(x6944, x7173).name("x7174_x6944").srcCtx("StaticLSTMNetwork.scala:263:17") } // RegWrite(x6944,x7173,x7161)
    val x7207 = withCtrl(x7208) { UnitController(style=SeqPipe, level=InnerControl).name("x7207").srcCtx("StaticLSTMNetwork.scala:231:66") } // UnitPipe(List(b4380, b4376, b4372),Block(Const(())))
    val x7177 = withCtrl(x7207) { OpDef(op=BitAnd, inputs=List(b4380, b4376)).name("x7177").srcCtx("UnrollingBase.scala:28:66") } // And(b4380,b4376)
    val x7178 = withCtrl(x7207) { OpDef(op=BitAnd, inputs=List(x7177, b4372)).name("x7178").srcCtx("UnrollingBase.scala:28:66") } // And(x7177,b4372)
    val x7179 = withCtrl(x7207) { LoadBanks(List(x6811_d0_b0), List(b4375, b4379)).name("x7179").srcCtx("StaticLSTMNetwork.scala:266:25:cEle") } // SRAMLoad(x6811,ArrayBuffer(Const(2), Const(128)),List(b4375, b4379),Const(0),x7178)
    val x7180 = withCtrl(x7207) { ReadMem(x6943).name("x7180").srcCtx("StaticLSTMNetwork.scala:267:36") } // RegRead(x6943)
    val x7181 = withCtrl(x7207) { OpDef(op=FixMul, inputs=List(x7179, x7180)).name("x7181").srcCtx("StaticLSTMNetwork.scala:267:32") } // FixMul(x7179,x7180)
    val x7182 = withCtrl(x7207) { ReadMem(x6941).name("x7182").srcCtx("StaticLSTMNetwork.scala:267:46") } // RegRead(x6941)
    val x7183 = withCtrl(x7207) { ReadMem(x6942).name("x7183").srcCtx("StaticLSTMNetwork.scala:267:56") } // RegRead(x6942)
    val x7184 = withCtrl(x7207) { OpDef(op=FixMul, inputs=List(x7182, x7183)).name("x7184").srcCtx("StaticLSTMNetwork.scala:267:52") } // FixMul(x7182,x7183)
    val x7185 = withCtrl(x7207) { OpDef(op=FixAdd, inputs=List(x7181, x7184)).name("x7185").srcCtx("StaticLSTMNetwork.scala:267:42:cEleNew") } // FixAdd(x7181,x7184)
    val x7186 = withCtrl(x7207) { OpDef(op=FixAbs, inputs=List(x7185)).name("x7186").srcCtx("StaticLSTMNetwork.scala:179:20:absin") } // FixAbs(x7185)
    val x7187 = withCtrl(x7207) { OpDef(op=FixSra, inputs=List(x7186, Const(2))).name("x7187").srcCtx("StaticLSTMNetwork.scala:180:22:div4") } // FixRsh(x7186,Const(2))
    val x7188 = withCtrl(x7207) { OpDef(op=FixAdd, inputs=List(x7187, Const(0.375))).name("x7188").srcCtx("StaticLSTMNetwork.scala:181:19:li") } // FixAdd(x7187,Const(0.375))
    val x7189 = withCtrl(x7207) { OpDef(op=FixLt, inputs=List(Const(2.5), x7186)).name("x7189").srcCtx("StaticLSTMNetwork.scala:182:28") } // FixLt(Const(2.5),x7186)
    val x7190 = withCtrl(x7207) { OpDef(op=BitNot, inputs=List(x7189)).name("x7190").srcCtx("StaticLSTMNetwork.scala:182:18") } // Not(x7189)
    val x7191 = withCtrl(x7207) { OpDef(op=FixLt, inputs=List(Const(0.5), x7186)).name("x7191").srcCtx("StaticLSTMNetwork.scala:183:19") } // FixLt(Const(0.5),x7186)
    val x7192 = withCtrl(x7207) { OpDef(op=FixLt, inputs=List(x7186, Const(2.5))).name("x7192").srcCtx("StaticLSTMNetwork.scala:183:36") } // FixLt(x7186,Const(2.5))
    val x7193 = withCtrl(x7207) { OpDef(op=BitAnd, inputs=List(x7191, x7192)).name("x7193").srcCtx("StaticLSTMNetwork.scala:183:27") } // And(x7191,x7192)
    val x7194 = withCtrl(x7207) { OpDef(op=BitAnd, inputs=List(x7193, x7190)).name("x7194").srcCtx("StaticLSTMNetwork.scala:182:18") } // And(x7193,x7190)
    val x7195 = withCtrl(x7207) { OpDef(op=BitNot, inputs=List(x7193)).name("x7195").srcCtx("StaticLSTMNetwork.scala:182:18") } // Not(x7193)
    val x7196 = withCtrl(x7207) { OpDef(op=BitAnd, inputs=List(x7195, x7190)).name("x7196").srcCtx("StaticLSTMNetwork.scala:182:18") } // And(x7195,x7190)
    val x7197 = withCtrl(x7207) { OpDef(op=MuxOp, inputs=List(x7194, x7188, x7186)).name("x7197").srcCtx("StaticLSTMNetwork.scala:182:18") } // Mux(x7194,x7188,x7186)
    val x7198 = withCtrl(x7207) { OpDef(op=MuxOp, inputs=List(x7189, Const(1.0), x7197)).name("x7198").srcCtx("StaticLSTMNetwork.scala:182:18") } // Mux(x7189,Const(1),x7197)
    val x7199 = withCtrl(x7207) { OpDef(op=FixNeg, inputs=List(x7198)).name("x7199").srcCtx("StaticLSTMNetwork.scala:185:23:negout") } // FixNeg(x7198)
    val x7200 = withCtrl(x7207) { OpDef(op=FixLt, inputs=List(x7185, Const(0.0))).name("x7200").srcCtx("StaticLSTMNetwork.scala:186:21") } // FixLt(x7185,Const(0))
    val x7201 = withCtrl(x7207) { OpDef(op=MuxOp, inputs=List(x7200, x7199, x7198)).name("x7201").srcCtx("StaticLSTMNetwork.scala:186:17:re") } // Mux(x7200,x7199,x7198)
    val x7202 = withCtrl(x7207) { ReadMem(x6944).name("x7202").srcCtx("StaticLSTMNetwork.scala:268:51") } // RegRead(x6944)
    val x7203 = withCtrl(x7207) { OpDef(op=FixAdd, inputs=List(x7201, x7202)).name("x7203").srcCtx("StaticLSTMNetwork.scala:268:47:hEleNew") } // FixAdd(x7201,x7202)
    val x7204 = withCtrl(x7207) { StoreBanks(List(List(x6811_d0_b0)), List(b4375, b4379), x7185).name("x7204").srcCtx("StaticLSTMNetwork.scala:269:33") } // SRAMStore(x6811,ArrayBuffer(Const(2), Const(128)),List(b4375, b4379),Const(0),x7185,x7178)
    antiDepsOf(x7204)=List(x7179)
    val x7205 = withCtrl(x7207) { StoreBanks(List(List(x6836_d0_b0), List(x6836_d1_b0), List(x6836_d2_b0), List(x6836_d3_b0)), List(b4375, b4379), x7203).name("x7205").srcCtx("StaticLSTMNetwork.scala:270:33") } // SRAMStore(x6836,ArrayBuffer(Const(2), Const(128)),List(b4375, b4379),Const(0),x7203,x7178)
    val x7206 = withCtrl(x7207) { StoreBanks(List(List(x6786_d0_b0), List(x6786_d1_b0), List(x6786_d2_b0), List(x6786_d3_b0), List(x6786_d4_b0)), List(b4371, b4379), x7203).name("x7206").srcCtx("StaticLSTMNetwork.scala:273:32") } // SRAMStore(x6786,ArrayBuffer(Const(8), Const(128)),List(b4371, b4379),Const(0),x7203,x7178)
    val x7211 = withCtrl(x7239) { Counter(min=Const(0), max=Const(8), step=Const(1), par=1).name("x7211").srcCtx("StaticLSTMNetwork.scala:278:49") } // CounterNew(Const(0),Const(8),Const(1),Const(1))
    val x7212 = withCtrl(x7239) { CounterChain(List(x7211)).name("x7212").srcCtx("StaticLSTMNetwork.scala:278:49") } // CounterChainNew(List(x7211))
    val x7238 = withCtrl(x7239) { LoopController(style=StreamPipe, level=OuterControl, cchain=x7212).name("x7238").srcCtx("StaticLSTMNetwork.scala:278:49") } // UnrolledForeach(List(Const(true)),x7212,Block(Const(())),List(List(b4669)),List(List(b4670)))
    val b4669 = withCtrl(x7238) { CounterIter(x7211, Some(0)).name("b4669") } // b4669
    val b4670 = withCtrl(x7238) { Const(true).name("b4670") } // b4670
    val b7270 = withCtrl(x7238) { StreamOut(field="offset").name("b7270").srcCtx("StaticLSTMNetwork.scala:278:49") } // x7213 = StreamOutNew(BurstCmdBus)
    isAccum(b7270) = false
    bufferDepthOf(b7270) = 1
    val b7271 = withCtrl(x7238) { StreamOut(field="size").name("b7271").srcCtx("StaticLSTMNetwork.scala:278:49") } // x7213 = StreamOutNew(BurstCmdBus)
    isAccum(b7271) = false
    bufferDepthOf(b7271) = 1
    val x7214 = withCtrl(x7238) { StreamOut(field="data").name("x7214").srcCtx("StaticLSTMNetwork.scala:278:49") } // x7214 = StreamOutNew(BurstFullDataBus())
    isAccum(x7214) = false
    bufferDepthOf(x7214) = 1
    val x7215 = withCtrl(x7238) { StreamIn(field="ack").name("x7215").srcCtx("StaticLSTMNetwork.scala:278:49") } // x7215 = StreamInNew(BurstAckBus)
    isAccum(x7215) = false
    bufferDepthOf(x7215) = 1
    val x7226 = withCtrl(x7238) { UnitController(style=SeqPipe, level=InnerControl).name("x7226").srcCtx("StaticLSTMNetwork.scala:278:49") } // UnitPipe(List(b4670),Block(x7225))
    val x7216 = withCtrl(x7226) { b4669 } // FixConvert(b4669,TRUE,_32,_0) (Same Type. No op)
    val x7217 = withCtrl(x7226) { OpDef(op=FixSla, inputs=List(x7216, Const(7))).name("x7217").srcCtx("StaticLSTMNetwork.scala:278:49") } // FixLsh(x7216,Const(7))
    val x7218 = withCtrl(x7226) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x7219 = withCtrl(x7226) { OpDef(op=FixAdd, inputs=List(x7217, x7218)).name("x7219").srcCtx("StaticLSTMNetwork.scala:278:49") } // FixAdd(x7217,x7218)
    val x7220 = withCtrl(x7226) { OpDef(op=FixSla, inputs=List(x7219, Const(2))).name("x7220").srcCtx("StaticLSTMNetwork.scala:278:49") } // FixLsh(x7219,Const(2))
    val x7221 = withCtrl(x7226) { x7220 } // FixConvert(x7220,TRUE,_64,_0)
    val x7222 = withCtrl(x7226) { DramAddress(x6785).name("x7222").srcCtx("StaticLSTMNetwork.scala:278:49") } // GetDRAMAddress(x6785)
    val x7224_x7223 = withCtrl(x7226) { OpDef(op=FixAdd, inputs=List(x7221, x7222)).name("x7224_x7223").srcCtx("StaticLSTMNetwork.scala:278:49") } // FixAdd(x7221,x7222)
    // x7224 = SimpleStruct(ArrayBuffer((offset,x7223), (size,Const(512)), (isLoad,Const(false))))
    val x7225_b7272_b7270 = withCtrl(x7226) { WriteMem(b7270, x7224_x7223).name("x7225_b7272_b7270").srcCtx("StaticLSTMNetwork.scala:278:49") } // StreamWrite(x7213,x7224,b4670)
    val x7225_b7273_b7271 = withCtrl(x7226) { WriteMem(b7271, Const(512)).name("x7225_b7273_b7271").srcCtx("StaticLSTMNetwork.scala:278:49") } // StreamWrite(x7213,x7224,b4670)
    val x7227 = withCtrl(x7238) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x7227").srcCtx("StaticLSTMNetwork.scala:278:49") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x7228 = withCtrl(x7238) { CounterChain(List(x7227)).name("x7228").srcCtx("StaticLSTMNetwork.scala:278:49") } // CounterChainNew(List(x7227))
    val x7234 = withCtrl(x7238) { LoopController(style=InnerPipe, level=InnerControl, cchain=x7228).name("x7234").srcCtx("StaticLSTMNetwork.scala:278:49") } // UnrolledForeach(List(b4670),x7228,Block(Const(())),List(List(b4687)),List(List(b4688)))
    val b4687 = withCtrl(x7234) { CounterIter(x7227, None).name("b4687") } // b4687
    val b4688 = withCtrl(x7234) { Const(true).name("b4688") } // b4688
    val x7229 = withCtrl(x7234) { OpDef(op=BitAnd, inputs=List(b4688, b4670)).name("x7229").srcCtx("UnrollingBase.scala:28:66") } // And(b4688,b4670)
    val x7230 = withCtrl(x7234) { LoadBanks(List(x6786_d0_b0), List(b4669, b4687)).name("x7230").srcCtx("StaticLSTMNetwork.scala:278:49") } // ParSRAMLoad(x6786,List(List(b4669, b4687)),List(x7229))
    val x7232_x7231 = withCtrl(x7234) { x7230 } // VectorApply(x7230,0)
    // x7232 = SimpleStruct(ArrayBuffer((_1,x7231), (_2,Const(true))))
    val x7233_x7233_x7214 = withCtrl(x7234) { WriteMem(x7214, x7232_x7231).name("x7233_x7233_x7214").srcCtx("StaticLSTMNetwork.scala:278:49") } // ParStreamWrite(x7214,List(x7232),List(x7229))
    val x7235 = withCtrl(x7238) { FringeDenseStore(dram=List(x6785), cmdStream=List(b7270, b7271), dataStream=List(x7214), ackStream=List(x7215)).name("x7235").srcCtx("StaticLSTMNetwork.scala:278:49") } // FringeDenseStore(x6785,x7213,x7214,x7215)
    val x7237 = withCtrl(x7238) { UnitController(style=SeqPipe, level=InnerControl).name("x7237").srcCtx("StaticLSTMNetwork.scala:278:49") } // UnitPipe(List(b4670),Block(Const(())))
    val x7236_x7236 = withCtrl(x7237) { ReadMem(x7215).name("x7236_x7236").srcCtx("StaticLSTMNetwork.scala:278:49") } // StreamRead(x7215,b4670)
    }; split1
    
  }
}
