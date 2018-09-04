import pir._
import pir.node._
import arch._
import prism.enums._

object LSTMPipeStep extends PIRApp {
  def main(implicit design:PIRDesign) = {
    import design.pirmeta._
    val x7816 = withCtrl(design.top.topController) { DRAM(dims=List(Const(1), Const(8), Const(128))).name("x7816").srcCtx("LSTMPipeStep.scala:168:23:dout") } // x7816 = DRAMNew(ArrayBuffer(Const(1), Const(8), Const(128)),Const(0))
    val x8603 = withCtrl(design.top.topController) { UnitController(style=SeqPipe, level=OuterControl).name("x8603").srcCtx("LSTMPipeStep.scala:169:11") } // Hwblock(Block(Const(())),false)
    val x7817 = withCtrl(x8603) { DRAM(dims=List(Const(2), Const(128))).name("x7817").srcCtx("DataGenerator.scala:156:20:k") } // x7817 = DRAMNew(ArrayBuffer(Const(2), Const(128)),Const(0))
    val x7819_d0_b0 = withCtrl(x8603) { SRAM(size=256, banking=Strided(banks=16, stride=1)).name("x7819_d0_b0").srcCtx("DataGenerator.scala:43:21:c") } // x7819 = SRAMNew(ArrayBuffer(Const(2), Const(128)))
    isAccum(x7819_d0_b0) = false
    bufferDepthOf(x7819_d0_b0) = 1
    staticDimsOf(x7819_d0_b0) = List(2, 128)
    val x7827 = withCtrl(x8603) { UnitController(style=SeqPipe, level=InnerControl).name("x7827").srcCtx("LSTMPipeStep.scala:169:11") } // UnitPipe(List(Const(true)),Block(Const(())))
    val x7828 = withCtrl(x8603) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x7828").srcCtx("DataGenerator.scala:44:8") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x7829 = withCtrl(x8603) { CounterChain(List(x7828)).name("x7829").srcCtx("DataGenerator.scala:44:8") } // CounterChainNew(List(x7828))
    val x7851 = withCtrl(x8603) { LoopController(style=StreamPipe, level=OuterControl, cchain=x7829).name("x7851").srcCtx("DataGenerator.scala:44:8") } // UnrolledForeach(List(Const(true)),x7829,Block(Const(())),List(List(b3685)),List(List(b3686)))
    val b3685 = withCtrl(x7851) { CounterIter(x7828, Some(0)).name("b3685") } // b3685
    val b3686 = withCtrl(x7851) { Const(true).name("b3686") } // b3686
    val b8618 = withCtrl(x7851) { StreamOut(field="offset").name("b8618").srcCtx("DataGenerator.scala:44:8") } // x7830 = StreamOutNew(BurstCmdBus)
    isAccum(b8618) = false
    bufferDepthOf(b8618) = 1
    val b8619 = withCtrl(x7851) { StreamOut(field="size").name("b8619").srcCtx("DataGenerator.scala:44:8") } // x7830 = StreamOutNew(BurstCmdBus)
    isAccum(b8619) = false
    bufferDepthOf(b8619) = 1
    val x7831 = withCtrl(x7851) { StreamIn(field="data").name("x7831").srcCtx("DataGenerator.scala:44:8") } // x7831 = StreamInNew(BurstDataBus())
    isAccum(x7831) = false
    bufferDepthOf(x7831) = 1
    val x7842 = withCtrl(x7851) { UnitController(style=SeqPipe, level=InnerControl).name("x7842").srcCtx("DataGenerator.scala:44:8") } // UnitPipe(List(b3686),Block(x7841))
    val x7832 = withCtrl(x7842) { b3685 } // FixConvert(b3685,TRUE,_32,_0) (Same Type. No op)
    val x7833 = withCtrl(x7842) { OpDef(op=FixSla, inputs=List(x7832, Const(7))).name("x7833").srcCtx("DataGenerator.scala:44:8") } // FixLsh(x7832,Const(7))
    val x7834 = withCtrl(x7842) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x7835 = withCtrl(x7842) { OpDef(op=FixAdd, inputs=List(x7833, x7834)).name("x7835").srcCtx("DataGenerator.scala:44:8") } // FixAdd(x7833,x7834)
    val x7836 = withCtrl(x7842) { OpDef(op=FixSla, inputs=List(x7835, Const(2))).name("x7836").srcCtx("DataGenerator.scala:44:8") } // FixLsh(x7835,Const(2))
    val x7837 = withCtrl(x7842) { x7836 } // FixConvert(x7836,TRUE,_64,_0)
    val x7838 = withCtrl(x7842) { DramAddress(x7817).name("x7838").srcCtx("DataGenerator.scala:44:8") } // GetDRAMAddress(x7817)
    val x7840_x7839 = withCtrl(x7842) { OpDef(op=FixAdd, inputs=List(x7837, x7838)).name("x7840_x7839").srcCtx("DataGenerator.scala:44:8") } // FixAdd(x7837,x7838)
    // x7840 = SimpleStruct(ArrayBuffer((offset,x7839), (size,Const(512)), (isLoad,Const(true))))
    val x7841_b8620_b8618 = withCtrl(x7842) { WriteMem(b8618, x7840_x7839).name("x7841_b8620_b8618").srcCtx("DataGenerator.scala:44:8") } // StreamWrite(x7830,x7840,b3686)
    val x7841_b8621_b8619 = withCtrl(x7842) { WriteMem(b8619, Const(512)).name("x7841_b8621_b8619").srcCtx("DataGenerator.scala:44:8") } // StreamWrite(x7830,x7840,b3686)
    val x7843 = withCtrl(x7851) { FringeDenseLoad(dram=List(x7817), cmdStream=List(b8618, b8619), dataStream=List(x7831)).name("x7843").srcCtx("DataGenerator.scala:44:8") } // FringeDenseLoad(x7817,x7830,x7831)
    val x7844 = withCtrl(x7851) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x7844").srcCtx("DataGenerator.scala:44:8") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x7845 = withCtrl(x7851) { CounterChain(List(x7844)).name("x7845").srcCtx("DataGenerator.scala:44:8") } // CounterChainNew(List(x7844))
    val x7850 = withCtrl(x7851) { LoopController(style=InnerPipe, level=InnerControl, cchain=x7845).name("x7850").srcCtx("DataGenerator.scala:44:8") } // UnrolledForeach(List(b3686),x7845,Block(Const(())),List(List(b3703)),List(List(b3704)))
    val b3703 = withCtrl(x7850) { CounterIter(x7844, None).name("b3703") } // b3703
    val b3704 = withCtrl(x7850) { Const(true).name("b3704") } // b3704
    val x7846 = withCtrl(x7850) { OpDef(op=BitAnd, inputs=List(b3704, b3686)).name("x7846").srcCtx("UnrollingBase.scala:28:66") } // And(b3704,b3686)
    val x7847_x7847 = withCtrl(x7850) { ReadMem(x7831).name("x7847_x7847").srcCtx("DataGenerator.scala:44:8") } // ParStreamRead(x7831,List(x7846))
    val x7848_x7848 = withCtrl(x7850) { x7847_x7847 } // VectorApply(x7847,0)
    val x7849 = withCtrl(x7850) { StoreBanks(List(List(x7819_d0_b0)), List(b3685, b3703), x7848_x7848).name("x7849").srcCtx("DataGenerator.scala:44:8") } // ParSRAMStore(x7819,List(List(b3685, b3703)),List(x7848),List(x7846))
    val x7852 = withCtrl(x8603) { DRAM(dims=List(Const(2), Const(128))).name("x7852").srcCtx("DataGenerator.scala:156:20:k") } // x7852 = DRAMNew(ArrayBuffer(Const(2), Const(128)),Const(0))
    val x7854_d0_b0 = withCtrl(x8603) { SRAM(size=256, banking=Strided(banks=16, stride=1)).name("x7854_d0_b0").srcCtx("DataGenerator.scala:43:21:h") } // x7854 = SRAMNew(ArrayBuffer(Const(2), Const(128)))
    isAccum(x7854_d0_b0) = false
    bufferDepthOf(x7854_d0_b0) = 1
    staticDimsOf(x7854_d0_b0) = List(2, 128)
    val x7854_d1_b0 = withCtrl(x8603) { SRAM(size=256, banking=Strided(banks=16, stride=1)).name("x7854_d1_b0").srcCtx("DataGenerator.scala:43:21:h") } // x7854 = SRAMNew(ArrayBuffer(Const(2), Const(128)))
    isAccum(x7854_d1_b0) = false
    bufferDepthOf(x7854_d1_b0) = 1
    staticDimsOf(x7854_d1_b0) = List(2, 128)
    val x7862 = withCtrl(x8603) { UnitController(style=SeqPipe, level=InnerControl).name("x7862").srcCtx("LSTMPipeStep.scala:169:11") } // UnitPipe(List(Const(true)),Block(Const(())))
    val x7863 = withCtrl(x8603) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x7863").srcCtx("DataGenerator.scala:44:8") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x7864 = withCtrl(x8603) { CounterChain(List(x7863)).name("x7864").srcCtx("DataGenerator.scala:44:8") } // CounterChainNew(List(x7863))
    val x7886 = withCtrl(x8603) { LoopController(style=StreamPipe, level=OuterControl, cchain=x7864).name("x7886").srcCtx("DataGenerator.scala:44:8") } // UnrolledForeach(List(Const(true)),x7864,Block(Const(())),List(List(b3724)),List(List(b3725)))
    val b3724 = withCtrl(x7886) { CounterIter(x7863, Some(0)).name("b3724") } // b3724
    val b3725 = withCtrl(x7886) { Const(true).name("b3725") } // b3725
    val b8622 = withCtrl(x7886) { StreamOut(field="offset").name("b8622").srcCtx("DataGenerator.scala:44:8") } // x7865 = StreamOutNew(BurstCmdBus)
    isAccum(b8622) = false
    bufferDepthOf(b8622) = 1
    val b8623 = withCtrl(x7886) { StreamOut(field="size").name("b8623").srcCtx("DataGenerator.scala:44:8") } // x7865 = StreamOutNew(BurstCmdBus)
    isAccum(b8623) = false
    bufferDepthOf(b8623) = 1
    val x7866 = withCtrl(x7886) { StreamIn(field="data").name("x7866").srcCtx("DataGenerator.scala:44:8") } // x7866 = StreamInNew(BurstDataBus())
    isAccum(x7866) = false
    bufferDepthOf(x7866) = 1
    val x7877 = withCtrl(x7886) { UnitController(style=SeqPipe, level=InnerControl).name("x7877").srcCtx("DataGenerator.scala:44:8") } // UnitPipe(List(b3725),Block(x7876))
    val x7867 = withCtrl(x7877) { b3724 } // FixConvert(b3724,TRUE,_32,_0) (Same Type. No op)
    val x7868 = withCtrl(x7877) { OpDef(op=FixSla, inputs=List(x7867, Const(7))).name("x7868").srcCtx("DataGenerator.scala:44:8") } // FixLsh(x7867,Const(7))
    val x7869 = withCtrl(x7877) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x7870 = withCtrl(x7877) { OpDef(op=FixAdd, inputs=List(x7868, x7869)).name("x7870").srcCtx("DataGenerator.scala:44:8") } // FixAdd(x7868,x7869)
    val x7871 = withCtrl(x7877) { OpDef(op=FixSla, inputs=List(x7870, Const(2))).name("x7871").srcCtx("DataGenerator.scala:44:8") } // FixLsh(x7870,Const(2))
    val x7872 = withCtrl(x7877) { x7871 } // FixConvert(x7871,TRUE,_64,_0)
    val x7873 = withCtrl(x7877) { DramAddress(x7852).name("x7873").srcCtx("DataGenerator.scala:44:8") } // GetDRAMAddress(x7852)
    val x7875_x7874 = withCtrl(x7877) { OpDef(op=FixAdd, inputs=List(x7872, x7873)).name("x7875_x7874").srcCtx("DataGenerator.scala:44:8") } // FixAdd(x7872,x7873)
    // x7875 = SimpleStruct(ArrayBuffer((offset,x7874), (size,Const(512)), (isLoad,Const(true))))
    val x7876_b8624_b8622 = withCtrl(x7877) { WriteMem(b8622, x7875_x7874).name("x7876_b8624_b8622").srcCtx("DataGenerator.scala:44:8") } // StreamWrite(x7865,x7875,b3725)
    val x7876_b8625_b8623 = withCtrl(x7877) { WriteMem(b8623, Const(512)).name("x7876_b8625_b8623").srcCtx("DataGenerator.scala:44:8") } // StreamWrite(x7865,x7875,b3725)
    val x7878 = withCtrl(x7886) { FringeDenseLoad(dram=List(x7852), cmdStream=List(b8622, b8623), dataStream=List(x7866)).name("x7878").srcCtx("DataGenerator.scala:44:8") } // FringeDenseLoad(x7852,x7865,x7866)
    val x7879 = withCtrl(x7886) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x7879").srcCtx("DataGenerator.scala:44:8") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x7880 = withCtrl(x7886) { CounterChain(List(x7879)).name("x7880").srcCtx("DataGenerator.scala:44:8") } // CounterChainNew(List(x7879))
    val x7885 = withCtrl(x7886) { LoopController(style=InnerPipe, level=InnerControl, cchain=x7880).name("x7885").srcCtx("DataGenerator.scala:44:8") } // UnrolledForeach(List(b3725),x7880,Block(Const(())),List(List(b3742)),List(List(b3743)))
    val b3742 = withCtrl(x7885) { CounterIter(x7879, None).name("b3742") } // b3742
    val b3743 = withCtrl(x7885) { Const(true).name("b3743") } // b3743
    val x7881 = withCtrl(x7885) { OpDef(op=BitAnd, inputs=List(b3743, b3725)).name("x7881").srcCtx("UnrollingBase.scala:28:66") } // And(b3743,b3725)
    val x7882_x7882 = withCtrl(x7885) { ReadMem(x7866).name("x7882_x7882").srcCtx("DataGenerator.scala:44:8") } // ParStreamRead(x7866,List(x7881))
    val x7883_x7883 = withCtrl(x7885) { x7882_x7882 } // VectorApply(x7882,0)
    val x7884 = withCtrl(x7885) { StoreBanks(List(List(x7854_d0_b0), List(x7854_d1_b0)), List(b3724, b3742), x7883_x7883).name("x7884").srcCtx("DataGenerator.scala:44:8") } // ParSRAMStore(x7854,List(List(b3724, b3742)),List(x7883),List(x7881))
    val x7887 = withCtrl(x8603) { DRAM(dims=List(Const(2), Const(128), Const(4), Const(128))).name("x7887").srcCtx("DataGenerator.scala:182:20:k") } // x7887 = DRAMNew(ArrayBuffer(Const(2), Const(128), Const(4), Const(128)),Const(0))
    val x7889_d0_b0 = withCtrl(x8603) { SRAM(size=16384, banking=Strided(banks=16, stride=1)).name("x7889_d0_b0").srcCtx("DataGenerator.scala:76:21:wx") } // x7889 = SRAMNew(ArrayBuffer(Const(2), Const(128), Const(4), Const(128)))
    isAccum(x7889_d0_b0) = false
    bufferDepthOf(x7889_d0_b0) = 1
    staticDimsOf(x7889_d0_b0) = List(2, 128, 4, 128)
    val x7889_d0_b1 = withCtrl(x8603) { SRAM(size=16384, banking=Strided(banks=16, stride=1)).name("x7889_d0_b1").srcCtx("DataGenerator.scala:76:21:wx") } // x7889 = SRAMNew(ArrayBuffer(Const(2), Const(128), Const(4), Const(128)))
    isAccum(x7889_d0_b1) = false
    bufferDepthOf(x7889_d0_b1) = 1
    staticDimsOf(x7889_d0_b1) = List(2, 128, 4, 128)
    val x7889_d0_b2 = withCtrl(x8603) { SRAM(size=16384, banking=Strided(banks=16, stride=1)).name("x7889_d0_b2").srcCtx("DataGenerator.scala:76:21:wx") } // x7889 = SRAMNew(ArrayBuffer(Const(2), Const(128), Const(4), Const(128)))
    isAccum(x7889_d0_b2) = false
    bufferDepthOf(x7889_d0_b2) = 1
    staticDimsOf(x7889_d0_b2) = List(2, 128, 4, 128)
    val x7889_d0_b3 = withCtrl(x8603) { SRAM(size=16384, banking=Strided(banks=16, stride=1)).name("x7889_d0_b3").srcCtx("DataGenerator.scala:76:21:wx") } // x7889 = SRAMNew(ArrayBuffer(Const(2), Const(128), Const(4), Const(128)))
    isAccum(x7889_d0_b3) = false
    bufferDepthOf(x7889_d0_b3) = 1
    staticDimsOf(x7889_d0_b3) = List(2, 128, 4, 128)
    val x7889_d0_b4 = withCtrl(x8603) { SRAM(size=16384, banking=Strided(banks=16, stride=1)).name("x7889_d0_b4").srcCtx("DataGenerator.scala:76:21:wx") } // x7889 = SRAMNew(ArrayBuffer(Const(2), Const(128), Const(4), Const(128)))
    isAccum(x7889_d0_b4) = false
    bufferDepthOf(x7889_d0_b4) = 1
    staticDimsOf(x7889_d0_b4) = List(2, 128, 4, 128)
    val x7889_d0_b5 = withCtrl(x8603) { SRAM(size=16384, banking=Strided(banks=16, stride=1)).name("x7889_d0_b5").srcCtx("DataGenerator.scala:76:21:wx") } // x7889 = SRAMNew(ArrayBuffer(Const(2), Const(128), Const(4), Const(128)))
    isAccum(x7889_d0_b5) = false
    bufferDepthOf(x7889_d0_b5) = 1
    staticDimsOf(x7889_d0_b5) = List(2, 128, 4, 128)
    val x7889_d0_b6 = withCtrl(x8603) { SRAM(size=16384, banking=Strided(banks=16, stride=1)).name("x7889_d0_b6").srcCtx("DataGenerator.scala:76:21:wx") } // x7889 = SRAMNew(ArrayBuffer(Const(2), Const(128), Const(4), Const(128)))
    isAccum(x7889_d0_b6) = false
    bufferDepthOf(x7889_d0_b6) = 1
    staticDimsOf(x7889_d0_b6) = List(2, 128, 4, 128)
    val x7889_d0_b7 = withCtrl(x8603) { SRAM(size=16384, banking=Strided(banks=16, stride=1)).name("x7889_d0_b7").srcCtx("DataGenerator.scala:76:21:wx") } // x7889 = SRAMNew(ArrayBuffer(Const(2), Const(128), Const(4), Const(128)))
    isAccum(x7889_d0_b7) = false
    bufferDepthOf(x7889_d0_b7) = 1
    staticDimsOf(x7889_d0_b7) = List(2, 128, 4, 128)
    val x7903 = withCtrl(x8603) { UnitController(style=SeqPipe, level=InnerControl).name("x7903").srcCtx("LSTMPipeStep.scala:169:11") } // UnitPipe(List(Const(true)),Block(Const(())))
    val x7904 = withCtrl(x8603) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x7904").srcCtx("DataGenerator.scala:77:8") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x7905 = withCtrl(x8603) { Counter(min=Const(0), max=Const(128), step=Const(1), par=1).name("x7905").srcCtx("DataGenerator.scala:77:8") } // CounterNew(Const(0),Const(128),Const(1),Const(1))
    val x7906 = withCtrl(x8603) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x7906").srcCtx("DataGenerator.scala:77:8") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x7907 = withCtrl(x8603) { CounterChain(List(x7904,x7905,x7906)).name("x7907").srcCtx("DataGenerator.scala:77:8") } // CounterChainNew(List(x7904, x7905, x7906))
    val x7939 = withCtrl(x8603) { LoopController(style=StreamPipe, level=OuterControl, cchain=x7907).name("x7939").srcCtx("DataGenerator.scala:77:8") } // UnrolledForeach(List(Const(true)),x7907,Block(Const(())),List(List(b3771), List(b3772), List(b3773)),List(List(b3774), List(b3775), List(b3776)))
    val b3771 = withCtrl(x7939) { CounterIter(x7904, Some(0)).name("b3771") } // b3771
    val b3774 = withCtrl(x7939) { Const(true).name("b3774") } // b3774
    val b3772 = withCtrl(x7939) { CounterIter(x7905, Some(0)).name("b3772") } // b3772
    val b3775 = withCtrl(x7939) { Const(true).name("b3775") } // b3775
    val b3773 = withCtrl(x7939) { CounterIter(x7906, Some(0)).name("b3773") } // b3773
    val b3776 = withCtrl(x7939) { Const(true).name("b3776") } // b3776
    val b8626 = withCtrl(x7939) { StreamOut(field="offset").name("b8626").srcCtx("DataGenerator.scala:77:8") } // x7908 = StreamOutNew(BurstCmdBus)
    isAccum(b8626) = false
    bufferDepthOf(b8626) = 1
    val b8627 = withCtrl(x7939) { StreamOut(field="size").name("b8627").srcCtx("DataGenerator.scala:77:8") } // x7908 = StreamOutNew(BurstCmdBus)
    isAccum(b8627) = false
    bufferDepthOf(b8627) = 1
    val x7909 = withCtrl(x7939) { StreamIn(field="data").name("x7909").srcCtx("DataGenerator.scala:77:8") } // x7909 = StreamInNew(BurstDataBus())
    isAccum(x7909) = false
    bufferDepthOf(x7909) = 1
    val x7928 = withCtrl(x7939) { UnitController(style=SeqPipe, level=InnerControl).name("x7928").srcCtx("DataGenerator.scala:77:8") } // UnitPipe(List(b3774, b3775, b3776),Block(x7927))
    val x7910 = withCtrl(x7928) { b3771 } // FixConvert(b3771,TRUE,_32,_0) (Same Type. No op)
    val x7911 = withCtrl(x7928) { OpDef(op=FixSla, inputs=List(x7910, Const(16))).name("x7911").srcCtx("DataGenerator.scala:77:8") } // FixLsh(x7910,Const(16))
    val x7912 = withCtrl(x7928) { b3772 } // FixConvert(b3772,TRUE,_32,_0) (Same Type. No op)
    val x7913 = withCtrl(x7928) { OpDef(op=FixSla, inputs=List(x7912, Const(9))).name("x7913").srcCtx("DataGenerator.scala:77:8") } // FixLsh(x7912,Const(9))
    val x7914 = withCtrl(x7928) { b3773 } // FixConvert(b3773,TRUE,_32,_0) (Same Type. No op)
    val x7915 = withCtrl(x7928) { OpDef(op=FixSla, inputs=List(x7914, Const(7))).name("x7915").srcCtx("DataGenerator.scala:77:8") } // FixLsh(x7914,Const(7))
    val x7916 = withCtrl(x7928) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x7917 = withCtrl(x7928) { OpDef(op=FixAdd, inputs=List(x7911, x7913)).name("x7917").srcCtx("DataGenerator.scala:77:8") } // FixAdd(x7911,x7913)
    val x7918 = withCtrl(x7928) { OpDef(op=FixAdd, inputs=List(x7915, x7916)).name("x7918").srcCtx("DataGenerator.scala:77:8") } // FixAdd(x7915,x7916)
    val x7919 = withCtrl(x7928) { OpDef(op=FixAdd, inputs=List(x7917, x7918)).name("x7919").srcCtx("DataGenerator.scala:77:8") } // FixAdd(x7917,x7918)
    val x7920 = withCtrl(x7928) { OpDef(op=FixSla, inputs=List(x7919, Const(2))).name("x7920").srcCtx("DataGenerator.scala:77:8") } // FixLsh(x7919,Const(2))
    val x7921 = withCtrl(x7928) { x7920 } // FixConvert(x7920,TRUE,_64,_0)
    val x7922 = withCtrl(x7928) { DramAddress(x7887).name("x7922").srcCtx("DataGenerator.scala:77:8") } // GetDRAMAddress(x7887)
    val x7924_x7923 = withCtrl(x7928) { OpDef(op=FixAdd, inputs=List(x7921, x7922)).name("x7924_x7923").srcCtx("DataGenerator.scala:77:8") } // FixAdd(x7921,x7922)
    // x7924 = SimpleStruct(ArrayBuffer((offset,x7923), (size,Const(512)), (isLoad,Const(true))))
    val x7925 = withCtrl(x7928) { OpDef(op=BitAnd, inputs=List(b3774, b3775)).name("x7925").srcCtx("UnrollingBase.scala:28:66") } // And(b3774,b3775)
    val x7926 = withCtrl(x7928) { OpDef(op=BitAnd, inputs=List(x7925, b3776)).name("x7926").srcCtx("UnrollingBase.scala:28:66") } // And(x7925,b3776)
    val x7927_b8628_b8626 = withCtrl(x7928) { WriteMem(b8626, x7924_x7923).name("x7927_b8628_b8626").srcCtx("DataGenerator.scala:77:8") } // StreamWrite(x7908,x7924,x7926)
    val x7927_b8629_b8627 = withCtrl(x7928) { WriteMem(b8627, Const(512)).name("x7927_b8629_b8627").srcCtx("DataGenerator.scala:77:8") } // StreamWrite(x7908,x7924,x7926)
    val x7929 = withCtrl(x7939) { FringeDenseLoad(dram=List(x7887), cmdStream=List(b8626, b8627), dataStream=List(x7909)).name("x7929").srcCtx("DataGenerator.scala:77:8") } // FringeDenseLoad(x7887,x7908,x7909)
    val x7930 = withCtrl(x7939) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x7930").srcCtx("DataGenerator.scala:77:8") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x7931 = withCtrl(x7939) { CounterChain(List(x7930)).name("x7931").srcCtx("DataGenerator.scala:77:8") } // CounterChainNew(List(x7930))
    val x7938 = withCtrl(x7939) { LoopController(style=InnerPipe, level=InnerControl, cchain=x7931).name("x7938").srcCtx("DataGenerator.scala:77:8") } // UnrolledForeach(List(b3774, b3775, b3776),x7931,Block(Const(())),List(List(b3801)),List(List(b3802)))
    val b3801 = withCtrl(x7938) { CounterIter(x7930, None).name("b3801") } // b3801
    val b3802 = withCtrl(x7938) { Const(true).name("b3802") } // b3802
    val x7932 = withCtrl(x7938) { OpDef(op=BitAnd, inputs=List(b3802, b3774)).name("x7932").srcCtx("UnrollingBase.scala:28:66") } // And(b3802,b3774)
    val x7933 = withCtrl(x7938) { OpDef(op=BitAnd, inputs=List(b3775, b3776)).name("x7933").srcCtx("UnrollingBase.scala:28:66") } // And(b3775,b3776)
    val x7934 = withCtrl(x7938) { OpDef(op=BitAnd, inputs=List(x7932, x7933)).name("x7934").srcCtx("UnrollingBase.scala:28:66") } // And(x7932,x7933)
    val x7935_x7935 = withCtrl(x7938) { ReadMem(x7909).name("x7935_x7935").srcCtx("DataGenerator.scala:77:8") } // ParStreamRead(x7909,List(x7934))
    val x7936_x7936 = withCtrl(x7938) { x7935_x7935 } // VectorApply(x7935,0)
    val x7937 = withCtrl(x7938) { StoreBanks(List(List(x7889_d0_b0, x7889_d0_b1, x7889_d0_b2, x7889_d0_b3, x7889_d0_b4, x7889_d0_b5, x7889_d0_b6, x7889_d0_b7)), List(b3771, b3772, b3773, b3801), x7936_x7936).name("x7937").srcCtx("DataGenerator.scala:77:8") } // ParSRAMStore(x7889,List(List(b3771, b3772, b3773, b3801)),List(x7936),List(x7934))
    val x7940 = withCtrl(x8603) { DRAM(dims=List(Const(2), Const(128), Const(4), Const(128))).name("x7940").srcCtx("DataGenerator.scala:182:20:k") } // x7940 = DRAMNew(ArrayBuffer(Const(2), Const(128), Const(4), Const(128)),Const(0))
    val x7942_d0_b0 = withCtrl(x8603) { SRAM(size=16384, banking=Strided(banks=16, stride=1)).name("x7942_d0_b0").srcCtx("DataGenerator.scala:76:21:wh") } // x7942 = SRAMNew(ArrayBuffer(Const(2), Const(128), Const(4), Const(128)))
    isAccum(x7942_d0_b0) = false
    bufferDepthOf(x7942_d0_b0) = 1
    staticDimsOf(x7942_d0_b0) = List(2, 128, 4, 128)
    val x7942_d0_b1 = withCtrl(x8603) { SRAM(size=16384, banking=Strided(banks=16, stride=1)).name("x7942_d0_b1").srcCtx("DataGenerator.scala:76:21:wh") } // x7942 = SRAMNew(ArrayBuffer(Const(2), Const(128), Const(4), Const(128)))
    isAccum(x7942_d0_b1) = false
    bufferDepthOf(x7942_d0_b1) = 1
    staticDimsOf(x7942_d0_b1) = List(2, 128, 4, 128)
    val x7942_d0_b2 = withCtrl(x8603) { SRAM(size=16384, banking=Strided(banks=16, stride=1)).name("x7942_d0_b2").srcCtx("DataGenerator.scala:76:21:wh") } // x7942 = SRAMNew(ArrayBuffer(Const(2), Const(128), Const(4), Const(128)))
    isAccum(x7942_d0_b2) = false
    bufferDepthOf(x7942_d0_b2) = 1
    staticDimsOf(x7942_d0_b2) = List(2, 128, 4, 128)
    val x7942_d0_b3 = withCtrl(x8603) { SRAM(size=16384, banking=Strided(banks=16, stride=1)).name("x7942_d0_b3").srcCtx("DataGenerator.scala:76:21:wh") } // x7942 = SRAMNew(ArrayBuffer(Const(2), Const(128), Const(4), Const(128)))
    isAccum(x7942_d0_b3) = false
    bufferDepthOf(x7942_d0_b3) = 1
    staticDimsOf(x7942_d0_b3) = List(2, 128, 4, 128)
    val x7942_d0_b4 = withCtrl(x8603) { SRAM(size=16384, banking=Strided(banks=16, stride=1)).name("x7942_d0_b4").srcCtx("DataGenerator.scala:76:21:wh") } // x7942 = SRAMNew(ArrayBuffer(Const(2), Const(128), Const(4), Const(128)))
    isAccum(x7942_d0_b4) = false
    bufferDepthOf(x7942_d0_b4) = 1
    staticDimsOf(x7942_d0_b4) = List(2, 128, 4, 128)
    val x7942_d0_b5 = withCtrl(x8603) { SRAM(size=16384, banking=Strided(banks=16, stride=1)).name("x7942_d0_b5").srcCtx("DataGenerator.scala:76:21:wh") } // x7942 = SRAMNew(ArrayBuffer(Const(2), Const(128), Const(4), Const(128)))
    isAccum(x7942_d0_b5) = false
    bufferDepthOf(x7942_d0_b5) = 1
    staticDimsOf(x7942_d0_b5) = List(2, 128, 4, 128)
    val x7942_d0_b6 = withCtrl(x8603) { SRAM(size=16384, banking=Strided(banks=16, stride=1)).name("x7942_d0_b6").srcCtx("DataGenerator.scala:76:21:wh") } // x7942 = SRAMNew(ArrayBuffer(Const(2), Const(128), Const(4), Const(128)))
    isAccum(x7942_d0_b6) = false
    bufferDepthOf(x7942_d0_b6) = 1
    staticDimsOf(x7942_d0_b6) = List(2, 128, 4, 128)
    val x7942_d0_b7 = withCtrl(x8603) { SRAM(size=16384, banking=Strided(banks=16, stride=1)).name("x7942_d0_b7").srcCtx("DataGenerator.scala:76:21:wh") } // x7942 = SRAMNew(ArrayBuffer(Const(2), Const(128), Const(4), Const(128)))
    isAccum(x7942_d0_b7) = false
    bufferDepthOf(x7942_d0_b7) = 1
    staticDimsOf(x7942_d0_b7) = List(2, 128, 4, 128)
    val x7956 = withCtrl(x8603) { UnitController(style=SeqPipe, level=InnerControl).name("x7956").srcCtx("LSTMPipeStep.scala:169:11") } // UnitPipe(List(Const(true)),Block(Const(())))
    val x7957 = withCtrl(x8603) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x7957").srcCtx("DataGenerator.scala:77:8") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x7958 = withCtrl(x8603) { Counter(min=Const(0), max=Const(128), step=Const(1), par=1).name("x7958").srcCtx("DataGenerator.scala:77:8") } // CounterNew(Const(0),Const(128),Const(1),Const(1))
    val x7959 = withCtrl(x8603) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x7959").srcCtx("DataGenerator.scala:77:8") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x7960 = withCtrl(x8603) { CounterChain(List(x7957,x7958,x7959)).name("x7960").srcCtx("DataGenerator.scala:77:8") } // CounterChainNew(List(x7957, x7958, x7959))
    val x7992 = withCtrl(x8603) { LoopController(style=StreamPipe, level=OuterControl, cchain=x7960).name("x7992").srcCtx("DataGenerator.scala:77:8") } // UnrolledForeach(List(Const(true)),x7960,Block(Const(())),List(List(b3832), List(b3833), List(b3834)),List(List(b3835), List(b3836), List(b3837)))
    val b3832 = withCtrl(x7992) { CounterIter(x7957, Some(0)).name("b3832") } // b3832
    val b3835 = withCtrl(x7992) { Const(true).name("b3835") } // b3835
    val b3833 = withCtrl(x7992) { CounterIter(x7958, Some(0)).name("b3833") } // b3833
    val b3836 = withCtrl(x7992) { Const(true).name("b3836") } // b3836
    val b3834 = withCtrl(x7992) { CounterIter(x7959, Some(0)).name("b3834") } // b3834
    val b3837 = withCtrl(x7992) { Const(true).name("b3837") } // b3837
    val b8630 = withCtrl(x7992) { StreamOut(field="offset").name("b8630").srcCtx("DataGenerator.scala:77:8") } // x7961 = StreamOutNew(BurstCmdBus)
    isAccum(b8630) = false
    bufferDepthOf(b8630) = 1
    val b8631 = withCtrl(x7992) { StreamOut(field="size").name("b8631").srcCtx("DataGenerator.scala:77:8") } // x7961 = StreamOutNew(BurstCmdBus)
    isAccum(b8631) = false
    bufferDepthOf(b8631) = 1
    val x7962 = withCtrl(x7992) { StreamIn(field="data").name("x7962").srcCtx("DataGenerator.scala:77:8") } // x7962 = StreamInNew(BurstDataBus())
    isAccum(x7962) = false
    bufferDepthOf(x7962) = 1
    val x7981 = withCtrl(x7992) { UnitController(style=SeqPipe, level=InnerControl).name("x7981").srcCtx("DataGenerator.scala:77:8") } // UnitPipe(List(b3835, b3836, b3837),Block(x7980))
    val x7963 = withCtrl(x7981) { b3832 } // FixConvert(b3832,TRUE,_32,_0) (Same Type. No op)
    val x7964 = withCtrl(x7981) { OpDef(op=FixSla, inputs=List(x7963, Const(16))).name("x7964").srcCtx("DataGenerator.scala:77:8") } // FixLsh(x7963,Const(16))
    val x7965 = withCtrl(x7981) { b3833 } // FixConvert(b3833,TRUE,_32,_0) (Same Type. No op)
    val x7966 = withCtrl(x7981) { OpDef(op=FixSla, inputs=List(x7965, Const(9))).name("x7966").srcCtx("DataGenerator.scala:77:8") } // FixLsh(x7965,Const(9))
    val x7967 = withCtrl(x7981) { b3834 } // FixConvert(b3834,TRUE,_32,_0) (Same Type. No op)
    val x7968 = withCtrl(x7981) { OpDef(op=FixSla, inputs=List(x7967, Const(7))).name("x7968").srcCtx("DataGenerator.scala:77:8") } // FixLsh(x7967,Const(7))
    val x7969 = withCtrl(x7981) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x7970 = withCtrl(x7981) { OpDef(op=FixAdd, inputs=List(x7964, x7966)).name("x7970").srcCtx("DataGenerator.scala:77:8") } // FixAdd(x7964,x7966)
    val x7971 = withCtrl(x7981) { OpDef(op=FixAdd, inputs=List(x7968, x7969)).name("x7971").srcCtx("DataGenerator.scala:77:8") } // FixAdd(x7968,x7969)
    val x7972 = withCtrl(x7981) { OpDef(op=FixAdd, inputs=List(x7970, x7971)).name("x7972").srcCtx("DataGenerator.scala:77:8") } // FixAdd(x7970,x7971)
    val x7973 = withCtrl(x7981) { OpDef(op=FixSla, inputs=List(x7972, Const(2))).name("x7973").srcCtx("DataGenerator.scala:77:8") } // FixLsh(x7972,Const(2))
    val x7974 = withCtrl(x7981) { x7973 } // FixConvert(x7973,TRUE,_64,_0)
    val x7975 = withCtrl(x7981) { DramAddress(x7940).name("x7975").srcCtx("DataGenerator.scala:77:8") } // GetDRAMAddress(x7940)
    val x7977_x7976 = withCtrl(x7981) { OpDef(op=FixAdd, inputs=List(x7974, x7975)).name("x7977_x7976").srcCtx("DataGenerator.scala:77:8") } // FixAdd(x7974,x7975)
    // x7977 = SimpleStruct(ArrayBuffer((offset,x7976), (size,Const(512)), (isLoad,Const(true))))
    val x7978 = withCtrl(x7981) { OpDef(op=BitAnd, inputs=List(b3835, b3836)).name("x7978").srcCtx("UnrollingBase.scala:28:66") } // And(b3835,b3836)
    val x7979 = withCtrl(x7981) { OpDef(op=BitAnd, inputs=List(x7978, b3837)).name("x7979").srcCtx("UnrollingBase.scala:28:66") } // And(x7978,b3837)
    val x7980_b8632_b8630 = withCtrl(x7981) { WriteMem(b8630, x7977_x7976).name("x7980_b8632_b8630").srcCtx("DataGenerator.scala:77:8") } // StreamWrite(x7961,x7977,x7979)
    val x7980_b8633_b8631 = withCtrl(x7981) { WriteMem(b8631, Const(512)).name("x7980_b8633_b8631").srcCtx("DataGenerator.scala:77:8") } // StreamWrite(x7961,x7977,x7979)
    val x7982 = withCtrl(x7992) { FringeDenseLoad(dram=List(x7940), cmdStream=List(b8630, b8631), dataStream=List(x7962)).name("x7982").srcCtx("DataGenerator.scala:77:8") } // FringeDenseLoad(x7940,x7961,x7962)
    val x7983 = withCtrl(x7992) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x7983").srcCtx("DataGenerator.scala:77:8") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x7984 = withCtrl(x7992) { CounterChain(List(x7983)).name("x7984").srcCtx("DataGenerator.scala:77:8") } // CounterChainNew(List(x7983))
    val x7991 = withCtrl(x7992) { LoopController(style=InnerPipe, level=InnerControl, cchain=x7984).name("x7991").srcCtx("DataGenerator.scala:77:8") } // UnrolledForeach(List(b3835, b3836, b3837),x7984,Block(Const(())),List(List(b3862)),List(List(b3863)))
    val b3862 = withCtrl(x7991) { CounterIter(x7983, None).name("b3862") } // b3862
    val b3863 = withCtrl(x7991) { Const(true).name("b3863") } // b3863
    val x7985 = withCtrl(x7991) { OpDef(op=BitAnd, inputs=List(b3863, b3835)).name("x7985").srcCtx("UnrollingBase.scala:28:66") } // And(b3863,b3835)
    val x7986 = withCtrl(x7991) { OpDef(op=BitAnd, inputs=List(b3836, b3837)).name("x7986").srcCtx("UnrollingBase.scala:28:66") } // And(b3836,b3837)
    val x7987 = withCtrl(x7991) { OpDef(op=BitAnd, inputs=List(x7985, x7986)).name("x7987").srcCtx("UnrollingBase.scala:28:66") } // And(x7985,x7986)
    val x7988_x7988 = withCtrl(x7991) { ReadMem(x7962).name("x7988_x7988").srcCtx("DataGenerator.scala:77:8") } // ParStreamRead(x7962,List(x7987))
    val x7989_x7989 = withCtrl(x7991) { x7988_x7988 } // VectorApply(x7988,0)
    val x7990 = withCtrl(x7991) { StoreBanks(List(List(x7942_d0_b0, x7942_d0_b1, x7942_d0_b2, x7942_d0_b3, x7942_d0_b4, x7942_d0_b5, x7942_d0_b6, x7942_d0_b7)), List(b3832, b3833, b3834, b3862), x7989_x7989).name("x7990").srcCtx("DataGenerator.scala:77:8") } // ParSRAMStore(x7942,List(List(b3832, b3833, b3834, b3862)),List(x7989),List(x7987))
    val x7993 = withCtrl(x8603) { DRAM(dims=List(Const(2), Const(4), Const(128))).name("x7993").srcCtx("DataGenerator.scala:168:20:k") } // x7993 = DRAMNew(ArrayBuffer(Const(2), Const(4), Const(128)),Const(0))
    val x7995_d0_b0 = withCtrl(x8603) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x7995_d0_b0").srcCtx("DataGenerator.scala:59:21:b") } // x7995 = SRAMNew(ArrayBuffer(Const(2), Const(4), Const(128)))
    isAccum(x7995_d0_b0) = false
    bufferDepthOf(x7995_d0_b0) = 1
    staticDimsOf(x7995_d0_b0) = List(2, 4, 128)
    val x7995_d0_b1 = withCtrl(x8603) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x7995_d0_b1").srcCtx("DataGenerator.scala:59:21:b") } // x7995 = SRAMNew(ArrayBuffer(Const(2), Const(4), Const(128)))
    isAccum(x7995_d0_b1) = false
    bufferDepthOf(x7995_d0_b1) = 1
    staticDimsOf(x7995_d0_b1) = List(2, 4, 128)
    val x8006 = withCtrl(x8603) { UnitController(style=SeqPipe, level=InnerControl).name("x8006").srcCtx("LSTMPipeStep.scala:169:11") } // UnitPipe(List(Const(true)),Block(Const(())))
    val x8007 = withCtrl(x8603) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x8007").srcCtx("DataGenerator.scala:60:8") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x8008 = withCtrl(x8603) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x8008").srcCtx("DataGenerator.scala:60:8") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x8009 = withCtrl(x8603) { CounterChain(List(x8007,x8008)).name("x8009").srcCtx("DataGenerator.scala:60:8") } // CounterChainNew(List(x8007, x8008))
    val x8036 = withCtrl(x8603) { LoopController(style=StreamPipe, level=OuterControl, cchain=x8009).name("x8036").srcCtx("DataGenerator.scala:60:8") } // UnrolledForeach(List(Const(true)),x8009,Block(Const(())),List(List(b3889), List(b3890)),List(List(b3891), List(b3892)))
    val b3889 = withCtrl(x8036) { CounterIter(x8007, Some(0)).name("b3889") } // b3889
    val b3891 = withCtrl(x8036) { Const(true).name("b3891") } // b3891
    val b3890 = withCtrl(x8036) { CounterIter(x8008, Some(0)).name("b3890") } // b3890
    val b3892 = withCtrl(x8036) { Const(true).name("b3892") } // b3892
    val b8634 = withCtrl(x8036) { StreamOut(field="offset").name("b8634").srcCtx("DataGenerator.scala:60:8") } // x8010 = StreamOutNew(BurstCmdBus)
    isAccum(b8634) = false
    bufferDepthOf(b8634) = 1
    val b8635 = withCtrl(x8036) { StreamOut(field="size").name("b8635").srcCtx("DataGenerator.scala:60:8") } // x8010 = StreamOutNew(BurstCmdBus)
    isAccum(b8635) = false
    bufferDepthOf(b8635) = 1
    val x8011 = withCtrl(x8036) { StreamIn(field="data").name("x8011").srcCtx("DataGenerator.scala:60:8") } // x8011 = StreamInNew(BurstDataBus())
    isAccum(x8011) = false
    bufferDepthOf(x8011) = 1
    val x8026 = withCtrl(x8036) { UnitController(style=SeqPipe, level=InnerControl).name("x8026").srcCtx("DataGenerator.scala:60:8") } // UnitPipe(List(b3891, b3892),Block(x8025))
    val x8012 = withCtrl(x8026) { b3889 } // FixConvert(b3889,TRUE,_32,_0) (Same Type. No op)
    val x8013 = withCtrl(x8026) { OpDef(op=FixSla, inputs=List(x8012, Const(9))).name("x8013").srcCtx("DataGenerator.scala:60:8") } // FixLsh(x8012,Const(9))
    val x8014 = withCtrl(x8026) { b3890 } // FixConvert(b3890,TRUE,_32,_0) (Same Type. No op)
    val x8015 = withCtrl(x8026) { OpDef(op=FixSla, inputs=List(x8014, Const(7))).name("x8015").srcCtx("DataGenerator.scala:60:8") } // FixLsh(x8014,Const(7))
    val x8016 = withCtrl(x8026) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x8017 = withCtrl(x8026) { OpDef(op=FixAdd, inputs=List(x8013, x8015)).name("x8017").srcCtx("DataGenerator.scala:60:8") } // FixAdd(x8013,x8015)
    val x8018 = withCtrl(x8026) { OpDef(op=FixAdd, inputs=List(x8017, x8016)).name("x8018").srcCtx("DataGenerator.scala:60:8") } // FixAdd(x8017,x8016)
    val x8019 = withCtrl(x8026) { OpDef(op=FixSla, inputs=List(x8018, Const(2))).name("x8019").srcCtx("DataGenerator.scala:60:8") } // FixLsh(x8018,Const(2))
    val x8020 = withCtrl(x8026) { x8019 } // FixConvert(x8019,TRUE,_64,_0)
    val x8021 = withCtrl(x8026) { DramAddress(x7993).name("x8021").srcCtx("DataGenerator.scala:60:8") } // GetDRAMAddress(x7993)
    val x8023_x8022 = withCtrl(x8026) { OpDef(op=FixAdd, inputs=List(x8020, x8021)).name("x8023_x8022").srcCtx("DataGenerator.scala:60:8") } // FixAdd(x8020,x8021)
    // x8023 = SimpleStruct(ArrayBuffer((offset,x8022), (size,Const(512)), (isLoad,Const(true))))
    val x8024 = withCtrl(x8026) { OpDef(op=BitAnd, inputs=List(b3891, b3892)).name("x8024").srcCtx("UnrollingBase.scala:28:66") } // And(b3891,b3892)
    val x8025_b8636_b8634 = withCtrl(x8026) { WriteMem(b8634, x8023_x8022).name("x8025_b8636_b8634").srcCtx("DataGenerator.scala:60:8") } // StreamWrite(x8010,x8023,x8024)
    val x8025_b8637_b8635 = withCtrl(x8026) { WriteMem(b8635, Const(512)).name("x8025_b8637_b8635").srcCtx("DataGenerator.scala:60:8") } // StreamWrite(x8010,x8023,x8024)
    val x8027 = withCtrl(x8036) { FringeDenseLoad(dram=List(x7993), cmdStream=List(b8634, b8635), dataStream=List(x8011)).name("x8027").srcCtx("DataGenerator.scala:60:8") } // FringeDenseLoad(x7993,x8010,x8011)
    val x8028 = withCtrl(x8036) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x8028").srcCtx("DataGenerator.scala:60:8") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x8029 = withCtrl(x8036) { CounterChain(List(x8028)).name("x8029").srcCtx("DataGenerator.scala:60:8") } // CounterChainNew(List(x8028))
    val x8035 = withCtrl(x8036) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8029).name("x8035").srcCtx("DataGenerator.scala:60:8") } // UnrolledForeach(List(b3891, b3892),x8029,Block(Const(())),List(List(b3913)),List(List(b3914)))
    val b3913 = withCtrl(x8035) { CounterIter(x8028, None).name("b3913") } // b3913
    val b3914 = withCtrl(x8035) { Const(true).name("b3914") } // b3914
    val x8030 = withCtrl(x8035) { OpDef(op=BitAnd, inputs=List(b3914, b3891)).name("x8030").srcCtx("UnrollingBase.scala:28:66") } // And(b3914,b3891)
    val x8031 = withCtrl(x8035) { OpDef(op=BitAnd, inputs=List(x8030, b3892)).name("x8031").srcCtx("UnrollingBase.scala:28:66") } // And(x8030,b3892)
    val x8032_x8032 = withCtrl(x8035) { ReadMem(x8011).name("x8032_x8032").srcCtx("DataGenerator.scala:60:8") } // ParStreamRead(x8011,List(x8031))
    val x8033_x8033 = withCtrl(x8035) { x8032_x8032 } // VectorApply(x8032,0)
    val x8034 = withCtrl(x8035) { StoreBanks(List(List(x7995_d0_b0, x7995_d0_b1)), List(b3889, b3890, b3913), x8033_x8033).name("x8034").srcCtx("DataGenerator.scala:60:8") } // ParSRAMStore(x7995,List(List(b3889, b3890, b3913)),List(x8033),List(x8031))
    val x8037_d0_b0 = withCtrl(x8603) { SRAM(size=1024, banking=Strided(banks=16, stride=1)).name("x8037_d0_b0").srcCtx("LSTMPipeStep.scala:176:22:x") } // x8037 = SRAMNew(ArrayBuffer(Const(8), Const(128)))
    isAccum(x8037_d0_b0) = false
    bufferDepthOf(x8037_d0_b0) = 2
    staticDimsOf(x8037_d0_b0) = List(8, 128)
    val x8037_d1_b0 = withCtrl(x8603) { SRAM(size=1024, banking=Strided(banks=16, stride=1)).name("x8037_d1_b0").srcCtx("LSTMPipeStep.scala:176:22:x") } // x8037 = SRAMNew(ArrayBuffer(Const(8), Const(128)))
    isAccum(x8037_d1_b0) = false
    bufferDepthOf(x8037_d1_b0) = 1
    staticDimsOf(x8037_d1_b0) = List(8, 128)
    val x8038 = withCtrl(x8603) { DRAM(dims=List(Const(8), Const(8), Const(128))).name("x8038").srcCtx("DataGenerator.scala:168:20:k") } // x8038 = DRAMNew(ArrayBuffer(Const(8), Const(8), Const(128)),Const(0))
    val x8050 = withCtrl(x8603) { UnitController(style=SeqPipe, level=InnerControl).name("x8050").srcCtx("LSTMPipeStep.scala:169:11") } // UnitPipe(List(Const(true)),Block(Const(())))
    val x8051 = withCtrl(x8603) { Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x8051").srcCtx("LSTMPipeStep.scala:177:9") } // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x8052 = withCtrl(x8603) { Counter(min=Const(0), max=Const(8), step=Const(1), par=1).name("x8052").srcCtx("LSTMPipeStep.scala:177:9") } // CounterNew(Const(0),Const(8),Const(1),Const(1))
    val x8053 = withCtrl(x8603) { CounterChain(List(x8051,x8052)).name("x8053").srcCtx("LSTMPipeStep.scala:177:9") } // CounterChainNew(List(x8051, x8052))
    val x8080 = withCtrl(x8603) { LoopController(style=StreamPipe, level=OuterControl, cchain=x8053).name("x8080").srcCtx("LSTMPipeStep.scala:177:9") } // UnrolledForeach(List(Const(true)),x8053,Block(Const(())),List(List(b3939), List(b3940)),List(List(b3941), List(b3942)))
    val b3939 = withCtrl(x8080) { CounterIter(x8051, Some(0)).name("b3939") } // b3939
    val b3941 = withCtrl(x8080) { Const(true).name("b3941") } // b3941
    val b3940 = withCtrl(x8080) { CounterIter(x8052, Some(0)).name("b3940") } // b3940
    val b3942 = withCtrl(x8080) { Const(true).name("b3942") } // b3942
    val b8638 = withCtrl(x8080) { StreamOut(field="offset").name("b8638").srcCtx("LSTMPipeStep.scala:177:9") } // x8054 = StreamOutNew(BurstCmdBus)
    isAccum(b8638) = false
    bufferDepthOf(b8638) = 1
    val b8639 = withCtrl(x8080) { StreamOut(field="size").name("b8639").srcCtx("LSTMPipeStep.scala:177:9") } // x8054 = StreamOutNew(BurstCmdBus)
    isAccum(b8639) = false
    bufferDepthOf(b8639) = 1
    val x8055 = withCtrl(x8080) { StreamIn(field="data").name("x8055").srcCtx("LSTMPipeStep.scala:177:9") } // x8055 = StreamInNew(BurstDataBus())
    isAccum(x8055) = false
    bufferDepthOf(x8055) = 1
    val x8070 = withCtrl(x8080) { UnitController(style=SeqPipe, level=InnerControl).name("x8070").srcCtx("LSTMPipeStep.scala:177:9") } // UnitPipe(List(b3941, b3942),Block(x8069))
    val x8056 = withCtrl(x8070) { b3939 } // FixConvert(b3939,TRUE,_32,_0) (Same Type. No op)
    val x8057 = withCtrl(x8070) { OpDef(op=FixSla, inputs=List(x8056, Const(10))).name("x8057").srcCtx("LSTMPipeStep.scala:177:9") } // FixLsh(x8056,Const(10))
    val x8058 = withCtrl(x8070) { b3940 } // FixConvert(b3940,TRUE,_32,_0) (Same Type. No op)
    val x8059 = withCtrl(x8070) { OpDef(op=FixSla, inputs=List(x8058, Const(7))).name("x8059").srcCtx("LSTMPipeStep.scala:177:9") } // FixLsh(x8058,Const(7))
    val x8060 = withCtrl(x8070) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x8061 = withCtrl(x8070) { OpDef(op=FixAdd, inputs=List(x8057, x8059)).name("x8061").srcCtx("LSTMPipeStep.scala:177:9") } // FixAdd(x8057,x8059)
    val x8062 = withCtrl(x8070) { OpDef(op=FixAdd, inputs=List(x8061, x8060)).name("x8062").srcCtx("LSTMPipeStep.scala:177:9") } // FixAdd(x8061,x8060)
    val x8063 = withCtrl(x8070) { OpDef(op=FixSla, inputs=List(x8062, Const(2))).name("x8063").srcCtx("LSTMPipeStep.scala:177:9") } // FixLsh(x8062,Const(2))
    val x8064 = withCtrl(x8070) { x8063 } // FixConvert(x8063,TRUE,_64,_0)
    val x8065 = withCtrl(x8070) { DramAddress(x8038).name("x8065").srcCtx("LSTMPipeStep.scala:177:9") } // GetDRAMAddress(x8038)
    val x8067_x8066 = withCtrl(x8070) { OpDef(op=FixAdd, inputs=List(x8064, x8065)).name("x8067_x8066").srcCtx("LSTMPipeStep.scala:177:9") } // FixAdd(x8064,x8065)
    // x8067 = SimpleStruct(ArrayBuffer((offset,x8066), (size,Const(512)), (isLoad,Const(true))))
    val x8068 = withCtrl(x8070) { OpDef(op=BitAnd, inputs=List(b3941, b3942)).name("x8068").srcCtx("UnrollingBase.scala:28:66") } // And(b3941,b3942)
    val x8069_b8640_b8638 = withCtrl(x8070) { WriteMem(b8638, x8067_x8066).name("x8069_b8640_b8638").srcCtx("LSTMPipeStep.scala:177:9") } // StreamWrite(x8054,x8067,x8068)
    val x8069_b8641_b8639 = withCtrl(x8070) { WriteMem(b8639, Const(512)).name("x8069_b8641_b8639").srcCtx("LSTMPipeStep.scala:177:9") } // StreamWrite(x8054,x8067,x8068)
    val x8071 = withCtrl(x8080) { FringeDenseLoad(dram=List(x8038), cmdStream=List(b8638, b8639), dataStream=List(x8055)).name("x8071").srcCtx("LSTMPipeStep.scala:177:9") } // FringeDenseLoad(x8038,x8054,x8055)
    val x8072 = withCtrl(x8080) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x8072").srcCtx("LSTMPipeStep.scala:177:9") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x8073 = withCtrl(x8080) { CounterChain(List(x8072)).name("x8073").srcCtx("LSTMPipeStep.scala:177:9") } // CounterChainNew(List(x8072))
    val x8079 = withCtrl(x8080) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8073).name("x8079").srcCtx("LSTMPipeStep.scala:177:9") } // UnrolledForeach(List(b3941, b3942),x8073,Block(Const(())),List(List(b3963)),List(List(b3964)))
    val b3963 = withCtrl(x8079) { CounterIter(x8072, None).name("b3963") } // b3963
    val b3964 = withCtrl(x8079) { Const(true).name("b3964") } // b3964
    val x8074 = withCtrl(x8079) { OpDef(op=BitAnd, inputs=List(b3964, b3941)).name("x8074").srcCtx("UnrollingBase.scala:28:66") } // And(b3964,b3941)
    val x8075 = withCtrl(x8079) { OpDef(op=BitAnd, inputs=List(x8074, b3942)).name("x8075").srcCtx("UnrollingBase.scala:28:66") } // And(x8074,b3942)
    val x8076_x8076 = withCtrl(x8079) { ReadMem(x8055).name("x8076_x8076").srcCtx("LSTMPipeStep.scala:177:9") } // ParStreamRead(x8055,List(x8075))
    val x8077_x8077 = withCtrl(x8079) { x8076_x8076 } // VectorApply(x8076,0)
    val x8078 = withCtrl(x8079) { StoreBanks(List(List(x8037_d0_b0), List(x8037_d1_b0)), List(b3940, b3963), x8077_x8077).name("x8078").srcCtx("LSTMPipeStep.scala:177:9") } // ParSRAMStore(x8037,List(List(b3940, b3963)),List(x8077),List(x8075))
    val x8081 = withCtrl(x8603) { Counter(min=Const(0), max=Const(8), step=Const(1), par=1).name("x8081").srcCtx("LSTMPipeStep.scala:178:28") } // CounterNew(Const(0),Const(8),Const(1),Const(1))
    val x8082 = withCtrl(x8603) { CounterChain(List(x8081)).name("x8082").srcCtx("LSTMPipeStep.scala:178:41") } // CounterChainNew(List(x8081))
    val x8602 = withCtrl(x8603) { LoopController(style=MetaPipe, level=OuterControl, cchain=x8082).name("x8602").srcCtx("LSTMPipeStep.scala:178:41") } // UnrolledForeach(List(Const(true)),x8082,Block(Const(())),List(List(b3974)),List(List(b3975)))
    val b3974 = withCtrl(x8602) { CounterIter(x8081, Some(0)).name("b3974") } // b3974
    val b3975 = withCtrl(x8602) { Const(true).name("b3975") } // b3975
    val x8563 = withCtrl(x8602) { UnitController(style=SeqPipe, level=OuterControl).name("x8563").srcCtx("LSTMPipeStep.scala:16:10") } // UnitPipe(List(b3975),Block(Const(())))
    val x8083 = withCtrl(x8563) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x8083").srcCtx("LSTMPipeStep.scala:17:24") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x8084 = withCtrl(x8563) { CounterChain(List(x8083)).name("x8084").srcCtx("LSTMPipeStep.scala:17:30") } // CounterChainNew(List(x8083))
    val x8562 = withCtrl(x8563) { LoopController(style=MetaPipe, level=OuterControl, cchain=x8084).name("x8562").srcCtx("LSTMPipeStep.scala:17:30") } // UnrolledForeach(List(b3975),x8084,Block(Const(())),List(List(b3978)),List(List(b3979)))
    val b3978 = withCtrl(x8562) { CounterIter(x8083, Some(0)).name("b3978") } // b3978
    val b3979 = withCtrl(x8562) { Const(true).name("b3979") } // b3979
    val x8561 = withCtrl(x8562) { UnitController(style=SeqPipe, level=OuterControl).name("x8561").srcCtx("LSTMPipeStep.scala:18:14") } // UnitPipe(List(b3979, b3975),Block(Const(())))
    val x8085_d0_b0 = withCtrl(x8561) { SRAM(size=256, banking=Strided(banks=16, stride=1)).name("x8085_d0_b0").srcCtx("LSTMPipeStep.scala:19:34:reduceMem") } // x8085 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x8085_d0_b0) = false
    bufferDepthOf(x8085_d0_b0) = 1
    staticDimsOf(x8085_d0_b0) = List(4, 128)
    val x8085_d0_b1 = withCtrl(x8561) { SRAM(size=256, banking=Strided(banks=16, stride=1)).name("x8085_d0_b1").srcCtx("LSTMPipeStep.scala:19:34:reduceMem") } // x8085 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x8085_d0_b1) = false
    bufferDepthOf(x8085_d0_b1) = 1
    staticDimsOf(x8085_d0_b1) = List(4, 128)
    val x8085_d1_b0 = withCtrl(x8561) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x8085_d1_b0").srcCtx("LSTMPipeStep.scala:19:34:reduceMem") } // x8085 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x8085_d1_b0) = true
    bufferDepthOf(x8085_d1_b0) = 1
    staticDimsOf(x8085_d1_b0) = List(4, 128)
    val x8086_d0_b0 = withCtrl(x8561) { SRAM(size=256, banking=Strided(banks=16, stride=1)).name("x8086_d0_b0").srcCtx("LSTMPipeStep.scala:20:32:foldMem") } // x8086 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x8086_d0_b0) = false
    bufferDepthOf(x8086_d0_b0) = 1
    staticDimsOf(x8086_d0_b0) = List(4, 128)
    val x8086_d0_b1 = withCtrl(x8561) { SRAM(size=256, banking=Strided(banks=16, stride=1)).name("x8086_d0_b1").srcCtx("LSTMPipeStep.scala:20:32:foldMem") } // x8086 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x8086_d0_b1) = false
    bufferDepthOf(x8086_d0_b1) = 1
    staticDimsOf(x8086_d0_b1) = List(4, 128)
    val x8087 = withCtrl(x8561) { Counter(min=Const(0), max=Const(128), step=Const(1), par=8).name("x8087").srcCtx("LSTMPipeStep.scala:23:63") } // CounterNew(Const(0),Const(128),Const(1),Const(8))
    val x8088 = withCtrl(x8561) { CounterChain(List(x8087)).name("x8088").srcCtx("LSTMPipeStep.scala:37:14") } // CounterChainNew(List(x8087))
    val x8405 = withCtrl(x8561) { LoopController(style=MetaPipe, level=OuterControl, cchain=x8088).name("x8405").srcCtx("LSTMPipeStep.scala:37:14") } // UnrolledReduce(List(b3979, b3975),x8088,x8085,Block((x8085) => Const(())),List(List(b3987, b3988, b3989, b3990, b3991, b3992, b3993, b3994)),List(List(b3995, b3996, b3997, b3998, b3999, b4000, b4001, b4002)))
    val b3987 = withCtrl(x8405) { CounterIter(x8087, Some(0)).name("b3987") } // b3987
    val b3995 = withCtrl(x8405) { Const(true).name("b3995") } // b3995
    val b3988 = withCtrl(x8405) { CounterIter(x8087, Some(1)).name("b3988") } // b3988
    val b3996 = withCtrl(x8405) { Const(true).name("b3996") } // b3996
    val b3989 = withCtrl(x8405) { CounterIter(x8087, Some(2)).name("b3989") } // b3989
    val b3997 = withCtrl(x8405) { Const(true).name("b3997") } // b3997
    val b3990 = withCtrl(x8405) { CounterIter(x8087, Some(3)).name("b3990") } // b3990
    val b3998 = withCtrl(x8405) { Const(true).name("b3998") } // b3998
    val b3991 = withCtrl(x8405) { CounterIter(x8087, Some(4)).name("b3991") } // b3991
    val b3999 = withCtrl(x8405) { Const(true).name("b3999") } // b3999
    val b3992 = withCtrl(x8405) { CounterIter(x8087, Some(5)).name("b3992") } // b3992
    val b4000 = withCtrl(x8405) { Const(true).name("b4000") } // b4000
    val b3993 = withCtrl(x8405) { CounterIter(x8087, Some(6)).name("b3993") } // b3993
    val b4001 = withCtrl(x8405) { Const(true).name("b4001") } // b4001
    val b3994 = withCtrl(x8405) { CounterIter(x8087, Some(7)).name("b3994") } // b3994
    val b4002 = withCtrl(x8405) { Const(true).name("b4002") } // b4002
    val x8089_d0_b0 = withCtrl(x8405) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x8089_d0_b0").srcCtx("LSTMPipeStep.scala:24:31:re") } // x8089 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x8089_d0_b0) = false
    bufferDepthOf(x8089_d0_b0) = 2
    staticDimsOf(x8089_d0_b0) = List(4, 128)
    val x8090_d0_b0 = withCtrl(x8405) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x8090_d0_b0").srcCtx("LSTMPipeStep.scala:24:31:re") } // x8090 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x8090_d0_b0) = false
    bufferDepthOf(x8090_d0_b0) = 2
    staticDimsOf(x8090_d0_b0) = List(4, 128)
    val x8091_d0_b0 = withCtrl(x8405) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x8091_d0_b0").srcCtx("LSTMPipeStep.scala:24:31:re") } // x8091 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x8091_d0_b0) = false
    bufferDepthOf(x8091_d0_b0) = 2
    staticDimsOf(x8091_d0_b0) = List(4, 128)
    val x8092_d0_b0 = withCtrl(x8405) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x8092_d0_b0").srcCtx("LSTMPipeStep.scala:24:31:re") } // x8092 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x8092_d0_b0) = false
    bufferDepthOf(x8092_d0_b0) = 2
    staticDimsOf(x8092_d0_b0) = List(4, 128)
    val x8093_d0_b0 = withCtrl(x8405) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x8093_d0_b0").srcCtx("LSTMPipeStep.scala:24:31:re") } // x8093 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x8093_d0_b0) = false
    bufferDepthOf(x8093_d0_b0) = 2
    staticDimsOf(x8093_d0_b0) = List(4, 128)
    val x8094_d0_b0 = withCtrl(x8405) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x8094_d0_b0").srcCtx("LSTMPipeStep.scala:24:31:re") } // x8094 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x8094_d0_b0) = false
    bufferDepthOf(x8094_d0_b0) = 2
    staticDimsOf(x8094_d0_b0) = List(4, 128)
    val x8095_d0_b0 = withCtrl(x8405) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x8095_d0_b0").srcCtx("LSTMPipeStep.scala:24:31:re") } // x8095 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x8095_d0_b0) = false
    bufferDepthOf(x8095_d0_b0) = 2
    staticDimsOf(x8095_d0_b0) = List(4, 128)
    val x8096_d0_b0 = withCtrl(x8405) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x8096_d0_b0").srcCtx("LSTMPipeStep.scala:24:31:re") } // x8096 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x8096_d0_b0) = false
    bufferDepthOf(x8096_d0_b0) = 2
    staticDimsOf(x8096_d0_b0) = List(4, 128)
    val x8097 = withCtrl(x8405) { Reg(init=Some(0.0)).name("x8097").srcCtx("LSTMPipeStep.scala:37:14") } // x8097 = RegNew(Const(0))
    isAccum(x8097) = false
    bufferDepthOf(x8097) = 2
    val x8098 = withCtrl(x8405) { Reg(init=Some(0.0)).name("x8098").srcCtx("LSTMPipeStep.scala:37:14") } // x8098 = RegNew(Const(0))
    isAccum(x8098) = false
    bufferDepthOf(x8098) = 2
    val x8099 = withCtrl(x8405) { Reg(init=Some(0.0)).name("x8099").srcCtx("LSTMPipeStep.scala:37:14") } // x8099 = RegNew(Const(0))
    isAccum(x8099) = false
    bufferDepthOf(x8099) = 2
    val x8100 = withCtrl(x8405) { Reg(init=Some(0.0)).name("x8100").srcCtx("LSTMPipeStep.scala:37:14") } // x8100 = RegNew(Const(0))
    isAccum(x8100) = false
    bufferDepthOf(x8100) = 2
    val x8101 = withCtrl(x8405) { Reg(init=Some(0.0)).name("x8101").srcCtx("LSTMPipeStep.scala:37:14") } // x8101 = RegNew(Const(0))
    isAccum(x8101) = false
    bufferDepthOf(x8101) = 2
    val x8102 = withCtrl(x8405) { Reg(init=Some(0.0)).name("x8102").srcCtx("LSTMPipeStep.scala:37:14") } // x8102 = RegNew(Const(0))
    isAccum(x8102) = false
    bufferDepthOf(x8102) = 2
    val x8103 = withCtrl(x8405) { Reg(init=Some(0.0)).name("x8103").srcCtx("LSTMPipeStep.scala:37:14") } // x8103 = RegNew(Const(0))
    isAccum(x8103) = false
    bufferDepthOf(x8103) = 2
    val x8104 = withCtrl(x8405) { Reg(init=Some(0.0)).name("x8104").srcCtx("LSTMPipeStep.scala:37:14") } // x8104 = RegNew(Const(0))
    isAccum(x8104) = false
    bufferDepthOf(x8104) = 2
    val x8105 = withCtrl(x8405) { Reg(init=Some(0.0)).name("x8105").srcCtx("LSTMPipeStep.scala:37:14") } // x8105 = RegNew(Const(0))
    isAccum(x8105) = false
    bufferDepthOf(x8105) = 2
    val x8106 = withCtrl(x8405) { Reg(init=Some(0.0)).name("x8106").srcCtx("LSTMPipeStep.scala:37:14") } // x8106 = RegNew(Const(0))
    isAccum(x8106) = false
    bufferDepthOf(x8106) = 2
    val x8107 = withCtrl(x8405) { Reg(init=Some(0.0)).name("x8107").srcCtx("LSTMPipeStep.scala:37:14") } // x8107 = RegNew(Const(0))
    isAccum(x8107) = false
    bufferDepthOf(x8107) = 2
    val x8108 = withCtrl(x8405) { Reg(init=Some(0.0)).name("x8108").srcCtx("LSTMPipeStep.scala:37:14") } // x8108 = RegNew(Const(0))
    isAccum(x8108) = false
    bufferDepthOf(x8108) = 2
    val x8109 = withCtrl(x8405) { Reg(init=Some(0.0)).name("x8109").srcCtx("LSTMPipeStep.scala:37:14") } // x8109 = RegNew(Const(0))
    isAccum(x8109) = false
    bufferDepthOf(x8109) = 2
    val x8110 = withCtrl(x8405) { Reg(init=Some(0.0)).name("x8110").srcCtx("LSTMPipeStep.scala:37:14") } // x8110 = RegNew(Const(0))
    isAccum(x8110) = false
    bufferDepthOf(x8110) = 2
    val x8111 = withCtrl(x8405) { Reg(init=Some(0.0)).name("x8111").srcCtx("LSTMPipeStep.scala:37:14") } // x8111 = RegNew(Const(0))
    isAccum(x8111) = false
    bufferDepthOf(x8111) = 2
    val x8112 = withCtrl(x8405) { Reg(init=Some(0.0)).name("x8112").srcCtx("LSTMPipeStep.scala:37:14") } // x8112 = RegNew(Const(0))
    isAccum(x8112) = false
    bufferDepthOf(x8112) = 2
    def split1 = {
    val x8169 = withCtrl(x8405) { UnitController(style=ForkJoin, level=OuterControl).name("x8169").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b3979, b3975),Block(Const(())))
    val x8119 = withCtrl(x8169) { UnitController(style=SeqPipe, level=InnerControl).name("x8119").srcCtx("LSTMPipeStep.scala:37:14") } // UnitPipe(List(b3995, b3979, b3975),Block(Const(())))
    val x8113 = withCtrl(x8119) { OpDef(op=BitAnd, inputs=List(b3995, b3979)).name("x8113").srcCtx("UnrollingBase.scala:28:66") } // And(b3995,b3979)
    val x8114 = withCtrl(x8119) { OpDef(op=BitAnd, inputs=List(x8113, b3975)).name("x8114").srcCtx("UnrollingBase.scala:28:66") } // And(x8113,b3975)
    val x8115 = withCtrl(x8119) { LoadBanks(List(x8037_d1_b0), List(b3974, b3987)).name("x8115").srcCtx("LSTMPipeStep.scala:26:27:xEle") } // SRAMLoad(x8037,ArrayBuffer(Const(8), Const(128)),List(b3974, b3987),Const(0),x8114)
    val x8116 = withCtrl(x8119) { LoadBanks(List(x7854_d1_b0), List(b3978, b3987)).name("x8116").srcCtx("LSTMPipeStep.scala:27:27:hEle") } // SRAMLoad(x7854,ArrayBuffer(Const(2), Const(128)),List(b3978, b3987),Const(0),x8114)
    val x8117_x8097 = withCtrl(x8119) { WriteMem(x8097, x8115).name("x8117_x8097").srcCtx("LSTMPipeStep.scala:37:14") } // RegWrite(x8097,x8115,x8114)
    val x8118_x8105 = withCtrl(x8119) { WriteMem(x8105, x8116).name("x8118_x8105").srcCtx("LSTMPipeStep.scala:37:14") } // RegWrite(x8105,x8116,x8114)
    val x8126 = withCtrl(x8169) { UnitController(style=SeqPipe, level=InnerControl).name("x8126").srcCtx("LSTMPipeStep.scala:37:14") } // UnitPipe(List(b3996, b3979, b3975),Block(Const(())))
    val x8120 = withCtrl(x8126) { OpDef(op=BitAnd, inputs=List(b3996, b3979)).name("x8120").srcCtx("UnrollingBase.scala:28:66") } // And(b3996,b3979)
    val x8121 = withCtrl(x8126) { OpDef(op=BitAnd, inputs=List(x8120, b3975)).name("x8121").srcCtx("UnrollingBase.scala:28:66") } // And(x8120,b3975)
    val x8122 = withCtrl(x8126) { LoadBanks(List(x8037_d1_b0), List(b3974, b3988)).name("x8122").srcCtx("LSTMPipeStep.scala:26:27:xEle") } // SRAMLoad(x8037,ArrayBuffer(Const(8), Const(128)),List(b3974, b3988),Const(0),x8121)
    val x8123 = withCtrl(x8126) { LoadBanks(List(x7854_d1_b0), List(b3978, b3988)).name("x8123").srcCtx("LSTMPipeStep.scala:27:27:hEle") } // SRAMLoad(x7854,ArrayBuffer(Const(2), Const(128)),List(b3978, b3988),Const(0),x8121)
    val x8124_x8098 = withCtrl(x8126) { WriteMem(x8098, x8122).name("x8124_x8098").srcCtx("LSTMPipeStep.scala:37:14") } // RegWrite(x8098,x8122,x8121)
    val x8125_x8106 = withCtrl(x8126) { WriteMem(x8106, x8123).name("x8125_x8106").srcCtx("LSTMPipeStep.scala:37:14") } // RegWrite(x8106,x8123,x8121)
    val x8133 = withCtrl(x8169) { UnitController(style=SeqPipe, level=InnerControl).name("x8133").srcCtx("LSTMPipeStep.scala:37:14") } // UnitPipe(List(b3997, b3979, b3975),Block(Const(())))
    val x8127 = withCtrl(x8133) { OpDef(op=BitAnd, inputs=List(b3997, b3979)).name("x8127").srcCtx("UnrollingBase.scala:28:66") } // And(b3997,b3979)
    val x8128 = withCtrl(x8133) { OpDef(op=BitAnd, inputs=List(x8127, b3975)).name("x8128").srcCtx("UnrollingBase.scala:28:66") } // And(x8127,b3975)
    val x8129 = withCtrl(x8133) { LoadBanks(List(x8037_d1_b0), List(b3974, b3989)).name("x8129").srcCtx("LSTMPipeStep.scala:26:27:xEle") } // SRAMLoad(x8037,ArrayBuffer(Const(8), Const(128)),List(b3974, b3989),Const(0),x8128)
    val x8130 = withCtrl(x8133) { LoadBanks(List(x7854_d1_b0), List(b3978, b3989)).name("x8130").srcCtx("LSTMPipeStep.scala:27:27:hEle") } // SRAMLoad(x7854,ArrayBuffer(Const(2), Const(128)),List(b3978, b3989),Const(0),x8128)
    val x8131_x8099 = withCtrl(x8133) { WriteMem(x8099, x8129).name("x8131_x8099").srcCtx("LSTMPipeStep.scala:37:14") } // RegWrite(x8099,x8129,x8128)
    val x8132_x8107 = withCtrl(x8133) { WriteMem(x8107, x8130).name("x8132_x8107").srcCtx("LSTMPipeStep.scala:37:14") } // RegWrite(x8107,x8130,x8128)
    val x8140 = withCtrl(x8169) { UnitController(style=SeqPipe, level=InnerControl).name("x8140").srcCtx("LSTMPipeStep.scala:37:14") } // UnitPipe(List(b3998, b3979, b3975),Block(Const(())))
    val x8134 = withCtrl(x8140) { OpDef(op=BitAnd, inputs=List(b3998, b3979)).name("x8134").srcCtx("UnrollingBase.scala:28:66") } // And(b3998,b3979)
    val x8135 = withCtrl(x8140) { OpDef(op=BitAnd, inputs=List(x8134, b3975)).name("x8135").srcCtx("UnrollingBase.scala:28:66") } // And(x8134,b3975)
    val x8136 = withCtrl(x8140) { LoadBanks(List(x8037_d1_b0), List(b3974, b3990)).name("x8136").srcCtx("LSTMPipeStep.scala:26:27:xEle") } // SRAMLoad(x8037,ArrayBuffer(Const(8), Const(128)),List(b3974, b3990),Const(0),x8135)
    val x8137 = withCtrl(x8140) { LoadBanks(List(x7854_d1_b0), List(b3978, b3990)).name("x8137").srcCtx("LSTMPipeStep.scala:27:27:hEle") } // SRAMLoad(x7854,ArrayBuffer(Const(2), Const(128)),List(b3978, b3990),Const(0),x8135)
    val x8138_x8100 = withCtrl(x8140) { WriteMem(x8100, x8136).name("x8138_x8100").srcCtx("LSTMPipeStep.scala:37:14") } // RegWrite(x8100,x8136,x8135)
    val x8139_x8108 = withCtrl(x8140) { WriteMem(x8108, x8137).name("x8139_x8108").srcCtx("LSTMPipeStep.scala:37:14") } // RegWrite(x8108,x8137,x8135)
    val x8147 = withCtrl(x8169) { UnitController(style=SeqPipe, level=InnerControl).name("x8147").srcCtx("LSTMPipeStep.scala:37:14") } // UnitPipe(List(b3999, b3979, b3975),Block(Const(())))
    val x8141 = withCtrl(x8147) { OpDef(op=BitAnd, inputs=List(b3999, b3979)).name("x8141").srcCtx("UnrollingBase.scala:28:66") } // And(b3999,b3979)
    val x8142 = withCtrl(x8147) { OpDef(op=BitAnd, inputs=List(x8141, b3975)).name("x8142").srcCtx("UnrollingBase.scala:28:66") } // And(x8141,b3975)
    val x8143 = withCtrl(x8147) { LoadBanks(List(x8037_d1_b0), List(b3974, b3991)).name("x8143").srcCtx("LSTMPipeStep.scala:26:27:xEle") } // SRAMLoad(x8037,ArrayBuffer(Const(8), Const(128)),List(b3974, b3991),Const(0),x8142)
    val x8144 = withCtrl(x8147) { LoadBanks(List(x7854_d1_b0), List(b3978, b3991)).name("x8144").srcCtx("LSTMPipeStep.scala:27:27:hEle") } // SRAMLoad(x7854,ArrayBuffer(Const(2), Const(128)),List(b3978, b3991),Const(0),x8142)
    val x8145_x8101 = withCtrl(x8147) { WriteMem(x8101, x8143).name("x8145_x8101").srcCtx("LSTMPipeStep.scala:37:14") } // RegWrite(x8101,x8143,x8142)
    val x8146_x8109 = withCtrl(x8147) { WriteMem(x8109, x8144).name("x8146_x8109").srcCtx("LSTMPipeStep.scala:37:14") } // RegWrite(x8109,x8144,x8142)
    val x8154 = withCtrl(x8169) { UnitController(style=SeqPipe, level=InnerControl).name("x8154").srcCtx("LSTMPipeStep.scala:37:14") } // UnitPipe(List(b4000, b3979, b3975),Block(Const(())))
    val x8148 = withCtrl(x8154) { OpDef(op=BitAnd, inputs=List(b4000, b3979)).name("x8148").srcCtx("UnrollingBase.scala:28:66") } // And(b4000,b3979)
    val x8149 = withCtrl(x8154) { OpDef(op=BitAnd, inputs=List(x8148, b3975)).name("x8149").srcCtx("UnrollingBase.scala:28:66") } // And(x8148,b3975)
    val x8150 = withCtrl(x8154) { LoadBanks(List(x8037_d1_b0), List(b3974, b3992)).name("x8150").srcCtx("LSTMPipeStep.scala:26:27:xEle") } // SRAMLoad(x8037,ArrayBuffer(Const(8), Const(128)),List(b3974, b3992),Const(0),x8149)
    val x8151 = withCtrl(x8154) { LoadBanks(List(x7854_d1_b0), List(b3978, b3992)).name("x8151").srcCtx("LSTMPipeStep.scala:27:27:hEle") } // SRAMLoad(x7854,ArrayBuffer(Const(2), Const(128)),List(b3978, b3992),Const(0),x8149)
    val x8152_x8102 = withCtrl(x8154) { WriteMem(x8102, x8150).name("x8152_x8102").srcCtx("LSTMPipeStep.scala:37:14") } // RegWrite(x8102,x8150,x8149)
    val x8153_x8110 = withCtrl(x8154) { WriteMem(x8110, x8151).name("x8153_x8110").srcCtx("LSTMPipeStep.scala:37:14") } // RegWrite(x8110,x8151,x8149)
    val x8161 = withCtrl(x8169) { UnitController(style=SeqPipe, level=InnerControl).name("x8161").srcCtx("LSTMPipeStep.scala:37:14") } // UnitPipe(List(b4001, b3979, b3975),Block(Const(())))
    val x8155 = withCtrl(x8161) { OpDef(op=BitAnd, inputs=List(b4001, b3979)).name("x8155").srcCtx("UnrollingBase.scala:28:66") } // And(b4001,b3979)
    val x8156 = withCtrl(x8161) { OpDef(op=BitAnd, inputs=List(x8155, b3975)).name("x8156").srcCtx("UnrollingBase.scala:28:66") } // And(x8155,b3975)
    val x8157 = withCtrl(x8161) { LoadBanks(List(x8037_d1_b0), List(b3974, b3993)).name("x8157").srcCtx("LSTMPipeStep.scala:26:27:xEle") } // SRAMLoad(x8037,ArrayBuffer(Const(8), Const(128)),List(b3974, b3993),Const(0),x8156)
    val x8158 = withCtrl(x8161) { LoadBanks(List(x7854_d1_b0), List(b3978, b3993)).name("x8158").srcCtx("LSTMPipeStep.scala:27:27:hEle") } // SRAMLoad(x7854,ArrayBuffer(Const(2), Const(128)),List(b3978, b3993),Const(0),x8156)
    val x8159_x8103 = withCtrl(x8161) { WriteMem(x8103, x8157).name("x8159_x8103").srcCtx("LSTMPipeStep.scala:37:14") } // RegWrite(x8103,x8157,x8156)
    val x8160_x8111 = withCtrl(x8161) { WriteMem(x8111, x8158).name("x8160_x8111").srcCtx("LSTMPipeStep.scala:37:14") } // RegWrite(x8111,x8158,x8156)
    val x8168 = withCtrl(x8169) { UnitController(style=SeqPipe, level=InnerControl).name("x8168").srcCtx("LSTMPipeStep.scala:37:14") } // UnitPipe(List(b4002, b3979, b3975),Block(Const(())))
    val x8162 = withCtrl(x8168) { OpDef(op=BitAnd, inputs=List(b4002, b3979)).name("x8162").srcCtx("UnrollingBase.scala:28:66") } // And(b4002,b3979)
    val x8163 = withCtrl(x8168) { OpDef(op=BitAnd, inputs=List(x8162, b3975)).name("x8163").srcCtx("UnrollingBase.scala:28:66") } // And(x8162,b3975)
    val x8164 = withCtrl(x8168) { LoadBanks(List(x8037_d1_b0), List(b3974, b3994)).name("x8164").srcCtx("LSTMPipeStep.scala:26:27:xEle") } // SRAMLoad(x8037,ArrayBuffer(Const(8), Const(128)),List(b3974, b3994),Const(0),x8163)
    val x8165 = withCtrl(x8168) { LoadBanks(List(x7854_d1_b0), List(b3978, b3994)).name("x8165").srcCtx("LSTMPipeStep.scala:27:27:hEle") } // SRAMLoad(x7854,ArrayBuffer(Const(2), Const(128)),List(b3978, b3994),Const(0),x8163)
    val x8166_x8104 = withCtrl(x8168) { WriteMem(x8104, x8164).name("x8166_x8104").srcCtx("LSTMPipeStep.scala:37:14") } // RegWrite(x8104,x8164,x8163)
    val x8167_x8112 = withCtrl(x8168) { WriteMem(x8112, x8165).name("x8167_x8112").srcCtx("LSTMPipeStep.scala:37:14") } // RegWrite(x8112,x8165,x8163)
    val x8330 = withCtrl(x8405) { UnitController(style=ForkJoin, level=OuterControl).name("x8330").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b3979, b3975),Block(Const(())))
    val x8170 = withCtrl(x8330) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x8170").srcCtx("LSTMPipeStep.scala:28:36") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x8171 = withCtrl(x8330) { CounterChain(List(x8170)).name("x8171").srcCtx("LSTMPipeStep.scala:28:43") } // CounterChainNew(List(x8170))
    val x8189 = withCtrl(x8330) { LoopController(style=MetaPipe, level=OuterControl, cchain=x8171).name("x8189").srcCtx("LSTMPipeStep.scala:28:43") } // UnrolledForeach(List(b3995, b3979, b3975),x8171,Block(Const(())),List(List(b4100)),List(List(b4101)))
    val b4100 = withCtrl(x8189) { CounterIter(x8170, Some(0)).name("b4100") } // b4100
    val b4101 = withCtrl(x8189) { Const(true).name("b4101") } // b4101
    val x8172 = withCtrl(x8189) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x8172").srcCtx("LSTMPipeStep.scala:29:44") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x8173 = withCtrl(x8189) { CounterChain(List(x8172)).name("x8173").srcCtx("LSTMPipeStep.scala:29:58") } // CounterChainNew(List(x8172))
    val x8188 = withCtrl(x8189) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8173).name("x8188").srcCtx("LSTMPipeStep.scala:29:58") } // UnrolledForeach(List(b4101, b3995, b3979, b3975),x8173,Block(Const(())),List(List(b4104)),List(List(b4105)))
    val b4104 = withCtrl(x8188) { CounterIter(x8172, None).name("b4104") } // b4104
    val b4105 = withCtrl(x8188) { Const(true).name("b4105") } // b4105
    val x8174 = withCtrl(x8188) { OpDef(op=BitAnd, inputs=List(b4105, b4101)).name("x8174").srcCtx("UnrollingBase.scala:28:66") } // And(b4105,b4101)
    val x8175 = withCtrl(x8188) { OpDef(op=BitAnd, inputs=List(b3995, b3979)).name("x8175").srcCtx("UnrollingBase.scala:28:66") } // And(b3995,b3979)
    val x8176 = withCtrl(x8188) { OpDef(op=BitAnd, inputs=List(x8174, x8175)).name("x8176").srcCtx("UnrollingBase.scala:28:66") } // And(x8174,x8175)
    val x8177 = withCtrl(x8188) { OpDef(op=BitAnd, inputs=List(x8176, b3975)).name("x8177").srcCtx("UnrollingBase.scala:28:66") } // And(x8176,b3975)
    val x8178 = withCtrl(x8188) { LoadBanks(List(x7889_d0_b0), List(b3978, b3987, b4100, b4104)).name("x8178").srcCtx("LSTMPipeStep.scala:30:31") } // ParSRAMLoad(x7889,List(List(b3978, b3987, b4100, b4104)),List(x8177))
    val x8179 = withCtrl(x8188) { x8178 } // VectorApply(x8178,0)
    val x8180 = withCtrl(x8188) { ReadMem(x8097).name("x8180").srcCtx("LSTMPipeStep.scala:37:14") } // RegRead(x8097)
    val x8181 = withCtrl(x8188) { OpDef(op=FixMul, inputs=List(x8179, x8180)).name("x8181").srcCtx("LSTMPipeStep.scala:30:69:reX") } // FixMul(x8179,x8180)
    val x8182 = withCtrl(x8188) { LoadBanks(List(x7942_d0_b0), List(b3978, b3987, b4100, b4104)).name("x8182").srcCtx("LSTMPipeStep.scala:31:31") } // ParSRAMLoad(x7942,List(List(b3978, b3987, b4100, b4104)),List(x8177))
    val x8183 = withCtrl(x8188) { x8182 } // VectorApply(x8182,0)
    val x8184 = withCtrl(x8188) { ReadMem(x8105).name("x8184").srcCtx("LSTMPipeStep.scala:37:14") } // RegRead(x8105)
    val x8185 = withCtrl(x8188) { OpDef(op=FixMul, inputs=List(x8183, x8184)).name("x8185").srcCtx("LSTMPipeStep.scala:31:69:reH") } // FixMul(x8183,x8184)
    val x8186 = withCtrl(x8188) { OpDef(op=FixAdd, inputs=List(x8181, x8185)).name("x8186").srcCtx("LSTMPipeStep.scala:32:48") } // FixAdd(x8181,x8185)
    val x8187 = withCtrl(x8188) { StoreBanks(List(List(x8089_d0_b0)), List(b4100, b4104), x8186).name("x8187").srcCtx("LSTMPipeStep.scala:32:42") } // ParSRAMStore(x8089,List(List(b4100, b4104)),List(x8186),List(x8177))
    val x8190 = withCtrl(x8330) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x8190").srcCtx("LSTMPipeStep.scala:28:36") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x8191 = withCtrl(x8330) { CounterChain(List(x8190)).name("x8191").srcCtx("LSTMPipeStep.scala:28:43") } // CounterChainNew(List(x8190))
    val x8209 = withCtrl(x8330) { LoopController(style=MetaPipe, level=OuterControl, cchain=x8191).name("x8209").srcCtx("LSTMPipeStep.scala:28:43") } // UnrolledForeach(List(b3996, b3979, b3975),x8191,Block(Const(())),List(List(b4122)),List(List(b4123)))
    val b4122 = withCtrl(x8209) { CounterIter(x8190, Some(0)).name("b4122") } // b4122
    val b4123 = withCtrl(x8209) { Const(true).name("b4123") } // b4123
    val x8192 = withCtrl(x8209) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x8192").srcCtx("LSTMPipeStep.scala:29:44") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x8193 = withCtrl(x8209) { CounterChain(List(x8192)).name("x8193").srcCtx("LSTMPipeStep.scala:29:58") } // CounterChainNew(List(x8192))
    val x8208 = withCtrl(x8209) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8193).name("x8208").srcCtx("LSTMPipeStep.scala:29:58") } // UnrolledForeach(List(b4123, b3996, b3979, b3975),x8193,Block(Const(())),List(List(b4126)),List(List(b4127)))
    val b4126 = withCtrl(x8208) { CounterIter(x8192, None).name("b4126") } // b4126
    val b4127 = withCtrl(x8208) { Const(true).name("b4127") } // b4127
    val x8194 = withCtrl(x8208) { OpDef(op=BitAnd, inputs=List(b4127, b4123)).name("x8194").srcCtx("UnrollingBase.scala:28:66") } // And(b4127,b4123)
    val x8195 = withCtrl(x8208) { OpDef(op=BitAnd, inputs=List(b3996, b3979)).name("x8195").srcCtx("UnrollingBase.scala:28:66") } // And(b3996,b3979)
    val x8196 = withCtrl(x8208) { OpDef(op=BitAnd, inputs=List(x8194, x8195)).name("x8196").srcCtx("UnrollingBase.scala:28:66") } // And(x8194,x8195)
    val x8197 = withCtrl(x8208) { OpDef(op=BitAnd, inputs=List(x8196, b3975)).name("x8197").srcCtx("UnrollingBase.scala:28:66") } // And(x8196,b3975)
    val x8198 = withCtrl(x8208) { LoadBanks(List(x7889_d0_b1), List(b3978, b3988, b4122, b4126)).name("x8198").srcCtx("LSTMPipeStep.scala:30:31") } // ParSRAMLoad(x7889,List(List(b3978, b3988, b4122, b4126)),List(x8197))
    val x8199 = withCtrl(x8208) { x8198 } // VectorApply(x8198,0)
    val x8200 = withCtrl(x8208) { ReadMem(x8098).name("x8200").srcCtx("LSTMPipeStep.scala:37:14") } // RegRead(x8098)
    val x8201 = withCtrl(x8208) { OpDef(op=FixMul, inputs=List(x8199, x8200)).name("x8201").srcCtx("LSTMPipeStep.scala:30:69:reX") } // FixMul(x8199,x8200)
    val x8202 = withCtrl(x8208) { LoadBanks(List(x7942_d0_b1), List(b3978, b3988, b4122, b4126)).name("x8202").srcCtx("LSTMPipeStep.scala:31:31") } // ParSRAMLoad(x7942,List(List(b3978, b3988, b4122, b4126)),List(x8197))
    val x8203 = withCtrl(x8208) { x8202 } // VectorApply(x8202,0)
    val x8204 = withCtrl(x8208) { ReadMem(x8106).name("x8204").srcCtx("LSTMPipeStep.scala:37:14") } // RegRead(x8106)
    val x8205 = withCtrl(x8208) { OpDef(op=FixMul, inputs=List(x8203, x8204)).name("x8205").srcCtx("LSTMPipeStep.scala:31:69:reH") } // FixMul(x8203,x8204)
    val x8206 = withCtrl(x8208) { OpDef(op=FixAdd, inputs=List(x8201, x8205)).name("x8206").srcCtx("LSTMPipeStep.scala:32:48") } // FixAdd(x8201,x8205)
    val x8207 = withCtrl(x8208) { StoreBanks(List(List(x8090_d0_b0)), List(b4122, b4126), x8206).name("x8207").srcCtx("LSTMPipeStep.scala:32:42") } // ParSRAMStore(x8090,List(List(b4122, b4126)),List(x8206),List(x8197))
    val x8210 = withCtrl(x8330) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x8210").srcCtx("LSTMPipeStep.scala:28:36") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x8211 = withCtrl(x8330) { CounterChain(List(x8210)).name("x8211").srcCtx("LSTMPipeStep.scala:28:43") } // CounterChainNew(List(x8210))
    val x8229 = withCtrl(x8330) { LoopController(style=MetaPipe, level=OuterControl, cchain=x8211).name("x8229").srcCtx("LSTMPipeStep.scala:28:43") } // UnrolledForeach(List(b3997, b3979, b3975),x8211,Block(Const(())),List(List(b4144)),List(List(b4145)))
    val b4144 = withCtrl(x8229) { CounterIter(x8210, Some(0)).name("b4144") } // b4144
    val b4145 = withCtrl(x8229) { Const(true).name("b4145") } // b4145
    val x8212 = withCtrl(x8229) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x8212").srcCtx("LSTMPipeStep.scala:29:44") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x8213 = withCtrl(x8229) { CounterChain(List(x8212)).name("x8213").srcCtx("LSTMPipeStep.scala:29:58") } // CounterChainNew(List(x8212))
    val x8228 = withCtrl(x8229) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8213).name("x8228").srcCtx("LSTMPipeStep.scala:29:58") } // UnrolledForeach(List(b4145, b3997, b3979, b3975),x8213,Block(Const(())),List(List(b4148)),List(List(b4149)))
    val b4148 = withCtrl(x8228) { CounterIter(x8212, None).name("b4148") } // b4148
    val b4149 = withCtrl(x8228) { Const(true).name("b4149") } // b4149
    val x8214 = withCtrl(x8228) { OpDef(op=BitAnd, inputs=List(b4149, b4145)).name("x8214").srcCtx("UnrollingBase.scala:28:66") } // And(b4149,b4145)
    val x8215 = withCtrl(x8228) { OpDef(op=BitAnd, inputs=List(b3997, b3979)).name("x8215").srcCtx("UnrollingBase.scala:28:66") } // And(b3997,b3979)
    val x8216 = withCtrl(x8228) { OpDef(op=BitAnd, inputs=List(x8214, x8215)).name("x8216").srcCtx("UnrollingBase.scala:28:66") } // And(x8214,x8215)
    val x8217 = withCtrl(x8228) { OpDef(op=BitAnd, inputs=List(x8216, b3975)).name("x8217").srcCtx("UnrollingBase.scala:28:66") } // And(x8216,b3975)
    val x8218 = withCtrl(x8228) { LoadBanks(List(x7889_d0_b2), List(b3978, b3989, b4144, b4148)).name("x8218").srcCtx("LSTMPipeStep.scala:30:31") } // ParSRAMLoad(x7889,List(List(b3978, b3989, b4144, b4148)),List(x8217))
    val x8219 = withCtrl(x8228) { x8218 } // VectorApply(x8218,0)
    val x8220 = withCtrl(x8228) { ReadMem(x8099).name("x8220").srcCtx("LSTMPipeStep.scala:37:14") } // RegRead(x8099)
    val x8221 = withCtrl(x8228) { OpDef(op=FixMul, inputs=List(x8219, x8220)).name("x8221").srcCtx("LSTMPipeStep.scala:30:69:reX") } // FixMul(x8219,x8220)
    val x8222 = withCtrl(x8228) { LoadBanks(List(x7942_d0_b2), List(b3978, b3989, b4144, b4148)).name("x8222").srcCtx("LSTMPipeStep.scala:31:31") } // ParSRAMLoad(x7942,List(List(b3978, b3989, b4144, b4148)),List(x8217))
    val x8223 = withCtrl(x8228) { x8222 } // VectorApply(x8222,0)
    val x8224 = withCtrl(x8228) { ReadMem(x8107).name("x8224").srcCtx("LSTMPipeStep.scala:37:14") } // RegRead(x8107)
    val x8225 = withCtrl(x8228) { OpDef(op=FixMul, inputs=List(x8223, x8224)).name("x8225").srcCtx("LSTMPipeStep.scala:31:69:reH") } // FixMul(x8223,x8224)
    val x8226 = withCtrl(x8228) { OpDef(op=FixAdd, inputs=List(x8221, x8225)).name("x8226").srcCtx("LSTMPipeStep.scala:32:48") } // FixAdd(x8221,x8225)
    val x8227 = withCtrl(x8228) { StoreBanks(List(List(x8091_d0_b0)), List(b4144, b4148), x8226).name("x8227").srcCtx("LSTMPipeStep.scala:32:42") } // ParSRAMStore(x8091,List(List(b4144, b4148)),List(x8226),List(x8217))
    val x8230 = withCtrl(x8330) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x8230").srcCtx("LSTMPipeStep.scala:28:36") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x8231 = withCtrl(x8330) { CounterChain(List(x8230)).name("x8231").srcCtx("LSTMPipeStep.scala:28:43") } // CounterChainNew(List(x8230))
    val x8249 = withCtrl(x8330) { LoopController(style=MetaPipe, level=OuterControl, cchain=x8231).name("x8249").srcCtx("LSTMPipeStep.scala:28:43") } // UnrolledForeach(List(b3998, b3979, b3975),x8231,Block(Const(())),List(List(b4166)),List(List(b4167)))
    val b4166 = withCtrl(x8249) { CounterIter(x8230, Some(0)).name("b4166") } // b4166
    val b4167 = withCtrl(x8249) { Const(true).name("b4167") } // b4167
    val x8232 = withCtrl(x8249) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x8232").srcCtx("LSTMPipeStep.scala:29:44") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x8233 = withCtrl(x8249) { CounterChain(List(x8232)).name("x8233").srcCtx("LSTMPipeStep.scala:29:58") } // CounterChainNew(List(x8232))
    val x8248 = withCtrl(x8249) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8233).name("x8248").srcCtx("LSTMPipeStep.scala:29:58") } // UnrolledForeach(List(b4167, b3998, b3979, b3975),x8233,Block(Const(())),List(List(b4170)),List(List(b4171)))
    val b4170 = withCtrl(x8248) { CounterIter(x8232, None).name("b4170") } // b4170
    val b4171 = withCtrl(x8248) { Const(true).name("b4171") } // b4171
    val x8234 = withCtrl(x8248) { OpDef(op=BitAnd, inputs=List(b4171, b4167)).name("x8234").srcCtx("UnrollingBase.scala:28:66") } // And(b4171,b4167)
    val x8235 = withCtrl(x8248) { OpDef(op=BitAnd, inputs=List(b3998, b3979)).name("x8235").srcCtx("UnrollingBase.scala:28:66") } // And(b3998,b3979)
    val x8236 = withCtrl(x8248) { OpDef(op=BitAnd, inputs=List(x8234, x8235)).name("x8236").srcCtx("UnrollingBase.scala:28:66") } // And(x8234,x8235)
    val x8237 = withCtrl(x8248) { OpDef(op=BitAnd, inputs=List(x8236, b3975)).name("x8237").srcCtx("UnrollingBase.scala:28:66") } // And(x8236,b3975)
    val x8238 = withCtrl(x8248) { LoadBanks(List(x7889_d0_b3), List(b3978, b3990, b4166, b4170)).name("x8238").srcCtx("LSTMPipeStep.scala:30:31") } // ParSRAMLoad(x7889,List(List(b3978, b3990, b4166, b4170)),List(x8237))
    val x8239 = withCtrl(x8248) { x8238 } // VectorApply(x8238,0)
    val x8240 = withCtrl(x8248) { ReadMem(x8100).name("x8240").srcCtx("LSTMPipeStep.scala:37:14") } // RegRead(x8100)
    val x8241 = withCtrl(x8248) { OpDef(op=FixMul, inputs=List(x8239, x8240)).name("x8241").srcCtx("LSTMPipeStep.scala:30:69:reX") } // FixMul(x8239,x8240)
    val x8242 = withCtrl(x8248) { LoadBanks(List(x7942_d0_b3), List(b3978, b3990, b4166, b4170)).name("x8242").srcCtx("LSTMPipeStep.scala:31:31") } // ParSRAMLoad(x7942,List(List(b3978, b3990, b4166, b4170)),List(x8237))
    val x8243 = withCtrl(x8248) { x8242 } // VectorApply(x8242,0)
    val x8244 = withCtrl(x8248) { ReadMem(x8108).name("x8244").srcCtx("LSTMPipeStep.scala:37:14") } // RegRead(x8108)
    val x8245 = withCtrl(x8248) { OpDef(op=FixMul, inputs=List(x8243, x8244)).name("x8245").srcCtx("LSTMPipeStep.scala:31:69:reH") } // FixMul(x8243,x8244)
    val x8246 = withCtrl(x8248) { OpDef(op=FixAdd, inputs=List(x8241, x8245)).name("x8246").srcCtx("LSTMPipeStep.scala:32:48") } // FixAdd(x8241,x8245)
    val x8247 = withCtrl(x8248) { StoreBanks(List(List(x8092_d0_b0)), List(b4166, b4170), x8246).name("x8247").srcCtx("LSTMPipeStep.scala:32:42") } // ParSRAMStore(x8092,List(List(b4166, b4170)),List(x8246),List(x8237))
    val x8250 = withCtrl(x8330) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x8250").srcCtx("LSTMPipeStep.scala:28:36") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x8251 = withCtrl(x8330) { CounterChain(List(x8250)).name("x8251").srcCtx("LSTMPipeStep.scala:28:43") } // CounterChainNew(List(x8250))
    val x8269 = withCtrl(x8330) { LoopController(style=MetaPipe, level=OuterControl, cchain=x8251).name("x8269").srcCtx("LSTMPipeStep.scala:28:43") } // UnrolledForeach(List(b3999, b3979, b3975),x8251,Block(Const(())),List(List(b4188)),List(List(b4189)))
    val b4188 = withCtrl(x8269) { CounterIter(x8250, Some(0)).name("b4188") } // b4188
    val b4189 = withCtrl(x8269) { Const(true).name("b4189") } // b4189
    val x8252 = withCtrl(x8269) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x8252").srcCtx("LSTMPipeStep.scala:29:44") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x8253 = withCtrl(x8269) { CounterChain(List(x8252)).name("x8253").srcCtx("LSTMPipeStep.scala:29:58") } // CounterChainNew(List(x8252))
    val x8268 = withCtrl(x8269) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8253).name("x8268").srcCtx("LSTMPipeStep.scala:29:58") } // UnrolledForeach(List(b4189, b3999, b3979, b3975),x8253,Block(Const(())),List(List(b4192)),List(List(b4193)))
    val b4192 = withCtrl(x8268) { CounterIter(x8252, None).name("b4192") } // b4192
    val b4193 = withCtrl(x8268) { Const(true).name("b4193") } // b4193
    val x8254 = withCtrl(x8268) { OpDef(op=BitAnd, inputs=List(b4193, b4189)).name("x8254").srcCtx("UnrollingBase.scala:28:66") } // And(b4193,b4189)
    val x8255 = withCtrl(x8268) { OpDef(op=BitAnd, inputs=List(b3999, b3979)).name("x8255").srcCtx("UnrollingBase.scala:28:66") } // And(b3999,b3979)
    val x8256 = withCtrl(x8268) { OpDef(op=BitAnd, inputs=List(x8254, x8255)).name("x8256").srcCtx("UnrollingBase.scala:28:66") } // And(x8254,x8255)
    val x8257 = withCtrl(x8268) { OpDef(op=BitAnd, inputs=List(x8256, b3975)).name("x8257").srcCtx("UnrollingBase.scala:28:66") } // And(x8256,b3975)
    val x8258 = withCtrl(x8268) { LoadBanks(List(x7889_d0_b4), List(b3978, b3991, b4188, b4192)).name("x8258").srcCtx("LSTMPipeStep.scala:30:31") } // ParSRAMLoad(x7889,List(List(b3978, b3991, b4188, b4192)),List(x8257))
    val x8259 = withCtrl(x8268) { x8258 } // VectorApply(x8258,0)
    val x8260 = withCtrl(x8268) { ReadMem(x8101).name("x8260").srcCtx("LSTMPipeStep.scala:37:14") } // RegRead(x8101)
    val x8261 = withCtrl(x8268) { OpDef(op=FixMul, inputs=List(x8259, x8260)).name("x8261").srcCtx("LSTMPipeStep.scala:30:69:reX") } // FixMul(x8259,x8260)
    val x8262 = withCtrl(x8268) { LoadBanks(List(x7942_d0_b4), List(b3978, b3991, b4188, b4192)).name("x8262").srcCtx("LSTMPipeStep.scala:31:31") } // ParSRAMLoad(x7942,List(List(b3978, b3991, b4188, b4192)),List(x8257))
    val x8263 = withCtrl(x8268) { x8262 } // VectorApply(x8262,0)
    val x8264 = withCtrl(x8268) { ReadMem(x8109).name("x8264").srcCtx("LSTMPipeStep.scala:37:14") } // RegRead(x8109)
    val x8265 = withCtrl(x8268) { OpDef(op=FixMul, inputs=List(x8263, x8264)).name("x8265").srcCtx("LSTMPipeStep.scala:31:69:reH") } // FixMul(x8263,x8264)
    val x8266 = withCtrl(x8268) { OpDef(op=FixAdd, inputs=List(x8261, x8265)).name("x8266").srcCtx("LSTMPipeStep.scala:32:48") } // FixAdd(x8261,x8265)
    val x8267 = withCtrl(x8268) { StoreBanks(List(List(x8093_d0_b0)), List(b4188, b4192), x8266).name("x8267").srcCtx("LSTMPipeStep.scala:32:42") } // ParSRAMStore(x8093,List(List(b4188, b4192)),List(x8266),List(x8257))
    val x8270 = withCtrl(x8330) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x8270").srcCtx("LSTMPipeStep.scala:28:36") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x8271 = withCtrl(x8330) { CounterChain(List(x8270)).name("x8271").srcCtx("LSTMPipeStep.scala:28:43") } // CounterChainNew(List(x8270))
    val x8289 = withCtrl(x8330) { LoopController(style=MetaPipe, level=OuterControl, cchain=x8271).name("x8289").srcCtx("LSTMPipeStep.scala:28:43") } // UnrolledForeach(List(b4000, b3979, b3975),x8271,Block(Const(())),List(List(b4210)),List(List(b4211)))
    val b4210 = withCtrl(x8289) { CounterIter(x8270, Some(0)).name("b4210") } // b4210
    val b4211 = withCtrl(x8289) { Const(true).name("b4211") } // b4211
    val x8272 = withCtrl(x8289) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x8272").srcCtx("LSTMPipeStep.scala:29:44") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x8273 = withCtrl(x8289) { CounterChain(List(x8272)).name("x8273").srcCtx("LSTMPipeStep.scala:29:58") } // CounterChainNew(List(x8272))
    val x8288 = withCtrl(x8289) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8273).name("x8288").srcCtx("LSTMPipeStep.scala:29:58") } // UnrolledForeach(List(b4211, b4000, b3979, b3975),x8273,Block(Const(())),List(List(b4214)),List(List(b4215)))
    val b4214 = withCtrl(x8288) { CounterIter(x8272, None).name("b4214") } // b4214
    val b4215 = withCtrl(x8288) { Const(true).name("b4215") } // b4215
    val x8274 = withCtrl(x8288) { OpDef(op=BitAnd, inputs=List(b4215, b4211)).name("x8274").srcCtx("UnrollingBase.scala:28:66") } // And(b4215,b4211)
    val x8275 = withCtrl(x8288) { OpDef(op=BitAnd, inputs=List(b4000, b3979)).name("x8275").srcCtx("UnrollingBase.scala:28:66") } // And(b4000,b3979)
    val x8276 = withCtrl(x8288) { OpDef(op=BitAnd, inputs=List(x8274, x8275)).name("x8276").srcCtx("UnrollingBase.scala:28:66") } // And(x8274,x8275)
    val x8277 = withCtrl(x8288) { OpDef(op=BitAnd, inputs=List(x8276, b3975)).name("x8277").srcCtx("UnrollingBase.scala:28:66") } // And(x8276,b3975)
    val x8278 = withCtrl(x8288) { LoadBanks(List(x7889_d0_b5), List(b3978, b3992, b4210, b4214)).name("x8278").srcCtx("LSTMPipeStep.scala:30:31") } // ParSRAMLoad(x7889,List(List(b3978, b3992, b4210, b4214)),List(x8277))
    val x8279 = withCtrl(x8288) { x8278 } // VectorApply(x8278,0)
    val x8280 = withCtrl(x8288) { ReadMem(x8102).name("x8280").srcCtx("LSTMPipeStep.scala:37:14") } // RegRead(x8102)
    val x8281 = withCtrl(x8288) { OpDef(op=FixMul, inputs=List(x8279, x8280)).name("x8281").srcCtx("LSTMPipeStep.scala:30:69:reX") } // FixMul(x8279,x8280)
    val x8282 = withCtrl(x8288) { LoadBanks(List(x7942_d0_b5), List(b3978, b3992, b4210, b4214)).name("x8282").srcCtx("LSTMPipeStep.scala:31:31") } // ParSRAMLoad(x7942,List(List(b3978, b3992, b4210, b4214)),List(x8277))
    val x8283 = withCtrl(x8288) { x8282 } // VectorApply(x8282,0)
    val x8284 = withCtrl(x8288) { ReadMem(x8110).name("x8284").srcCtx("LSTMPipeStep.scala:37:14") } // RegRead(x8110)
    val x8285 = withCtrl(x8288) { OpDef(op=FixMul, inputs=List(x8283, x8284)).name("x8285").srcCtx("LSTMPipeStep.scala:31:69:reH") } // FixMul(x8283,x8284)
    val x8286 = withCtrl(x8288) { OpDef(op=FixAdd, inputs=List(x8281, x8285)).name("x8286").srcCtx("LSTMPipeStep.scala:32:48") } // FixAdd(x8281,x8285)
    val x8287 = withCtrl(x8288) { StoreBanks(List(List(x8094_d0_b0)), List(b4210, b4214), x8286).name("x8287").srcCtx("LSTMPipeStep.scala:32:42") } // ParSRAMStore(x8094,List(List(b4210, b4214)),List(x8286),List(x8277))
    val x8290 = withCtrl(x8330) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x8290").srcCtx("LSTMPipeStep.scala:28:36") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x8291 = withCtrl(x8330) { CounterChain(List(x8290)).name("x8291").srcCtx("LSTMPipeStep.scala:28:43") } // CounterChainNew(List(x8290))
    val x8309 = withCtrl(x8330) { LoopController(style=MetaPipe, level=OuterControl, cchain=x8291).name("x8309").srcCtx("LSTMPipeStep.scala:28:43") } // UnrolledForeach(List(b4001, b3979, b3975),x8291,Block(Const(())),List(List(b4232)),List(List(b4233)))
    val b4232 = withCtrl(x8309) { CounterIter(x8290, Some(0)).name("b4232") } // b4232
    val b4233 = withCtrl(x8309) { Const(true).name("b4233") } // b4233
    val x8292 = withCtrl(x8309) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x8292").srcCtx("LSTMPipeStep.scala:29:44") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x8293 = withCtrl(x8309) { CounterChain(List(x8292)).name("x8293").srcCtx("LSTMPipeStep.scala:29:58") } // CounterChainNew(List(x8292))
    val x8308 = withCtrl(x8309) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8293).name("x8308").srcCtx("LSTMPipeStep.scala:29:58") } // UnrolledForeach(List(b4233, b4001, b3979, b3975),x8293,Block(Const(())),List(List(b4236)),List(List(b4237)))
    val b4236 = withCtrl(x8308) { CounterIter(x8292, None).name("b4236") } // b4236
    val b4237 = withCtrl(x8308) { Const(true).name("b4237") } // b4237
    val x8294 = withCtrl(x8308) { OpDef(op=BitAnd, inputs=List(b4237, b4233)).name("x8294").srcCtx("UnrollingBase.scala:28:66") } // And(b4237,b4233)
    val x8295 = withCtrl(x8308) { OpDef(op=BitAnd, inputs=List(b4001, b3979)).name("x8295").srcCtx("UnrollingBase.scala:28:66") } // And(b4001,b3979)
    val x8296 = withCtrl(x8308) { OpDef(op=BitAnd, inputs=List(x8294, x8295)).name("x8296").srcCtx("UnrollingBase.scala:28:66") } // And(x8294,x8295)
    val x8297 = withCtrl(x8308) { OpDef(op=BitAnd, inputs=List(x8296, b3975)).name("x8297").srcCtx("UnrollingBase.scala:28:66") } // And(x8296,b3975)
    val x8298 = withCtrl(x8308) { LoadBanks(List(x7889_d0_b6), List(b3978, b3993, b4232, b4236)).name("x8298").srcCtx("LSTMPipeStep.scala:30:31") } // ParSRAMLoad(x7889,List(List(b3978, b3993, b4232, b4236)),List(x8297))
    val x8299 = withCtrl(x8308) { x8298 } // VectorApply(x8298,0)
    val x8300 = withCtrl(x8308) { ReadMem(x8103).name("x8300").srcCtx("LSTMPipeStep.scala:37:14") } // RegRead(x8103)
    val x8301 = withCtrl(x8308) { OpDef(op=FixMul, inputs=List(x8299, x8300)).name("x8301").srcCtx("LSTMPipeStep.scala:30:69:reX") } // FixMul(x8299,x8300)
    val x8302 = withCtrl(x8308) { LoadBanks(List(x7942_d0_b6), List(b3978, b3993, b4232, b4236)).name("x8302").srcCtx("LSTMPipeStep.scala:31:31") } // ParSRAMLoad(x7942,List(List(b3978, b3993, b4232, b4236)),List(x8297))
    val x8303 = withCtrl(x8308) { x8302 } // VectorApply(x8302,0)
    val x8304 = withCtrl(x8308) { ReadMem(x8111).name("x8304").srcCtx("LSTMPipeStep.scala:37:14") } // RegRead(x8111)
    val x8305 = withCtrl(x8308) { OpDef(op=FixMul, inputs=List(x8303, x8304)).name("x8305").srcCtx("LSTMPipeStep.scala:31:69:reH") } // FixMul(x8303,x8304)
    val x8306 = withCtrl(x8308) { OpDef(op=FixAdd, inputs=List(x8301, x8305)).name("x8306").srcCtx("LSTMPipeStep.scala:32:48") } // FixAdd(x8301,x8305)
    val x8307 = withCtrl(x8308) { StoreBanks(List(List(x8095_d0_b0)), List(b4232, b4236), x8306).name("x8307").srcCtx("LSTMPipeStep.scala:32:42") } // ParSRAMStore(x8095,List(List(b4232, b4236)),List(x8306),List(x8297))
    val x8310 = withCtrl(x8330) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x8310").srcCtx("LSTMPipeStep.scala:28:36") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x8311 = withCtrl(x8330) { CounterChain(List(x8310)).name("x8311").srcCtx("LSTMPipeStep.scala:28:43") } // CounterChainNew(List(x8310))
    val x8329 = withCtrl(x8330) { LoopController(style=MetaPipe, level=OuterControl, cchain=x8311).name("x8329").srcCtx("LSTMPipeStep.scala:28:43") } // UnrolledForeach(List(b4002, b3979, b3975),x8311,Block(Const(())),List(List(b4254)),List(List(b4255)))
    val b4254 = withCtrl(x8329) { CounterIter(x8310, Some(0)).name("b4254") } // b4254
    val b4255 = withCtrl(x8329) { Const(true).name("b4255") } // b4255
    val x8312 = withCtrl(x8329) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x8312").srcCtx("LSTMPipeStep.scala:29:44") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x8313 = withCtrl(x8329) { CounterChain(List(x8312)).name("x8313").srcCtx("LSTMPipeStep.scala:29:58") } // CounterChainNew(List(x8312))
    val x8328 = withCtrl(x8329) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8313).name("x8328").srcCtx("LSTMPipeStep.scala:29:58") } // UnrolledForeach(List(b4255, b4002, b3979, b3975),x8313,Block(Const(())),List(List(b4258)),List(List(b4259)))
    val b4258 = withCtrl(x8328) { CounterIter(x8312, None).name("b4258") } // b4258
    val b4259 = withCtrl(x8328) { Const(true).name("b4259") } // b4259
    val x8314 = withCtrl(x8328) { OpDef(op=BitAnd, inputs=List(b4259, b4255)).name("x8314").srcCtx("UnrollingBase.scala:28:66") } // And(b4259,b4255)
    val x8315 = withCtrl(x8328) { OpDef(op=BitAnd, inputs=List(b4002, b3979)).name("x8315").srcCtx("UnrollingBase.scala:28:66") } // And(b4002,b3979)
    val x8316 = withCtrl(x8328) { OpDef(op=BitAnd, inputs=List(x8314, x8315)).name("x8316").srcCtx("UnrollingBase.scala:28:66") } // And(x8314,x8315)
    val x8317 = withCtrl(x8328) { OpDef(op=BitAnd, inputs=List(x8316, b3975)).name("x8317").srcCtx("UnrollingBase.scala:28:66") } // And(x8316,b3975)
    val x8318 = withCtrl(x8328) { LoadBanks(List(x7889_d0_b7), List(b3978, b3994, b4254, b4258)).name("x8318").srcCtx("LSTMPipeStep.scala:30:31") } // ParSRAMLoad(x7889,List(List(b3978, b3994, b4254, b4258)),List(x8317))
    val x8319 = withCtrl(x8328) { x8318 } // VectorApply(x8318,0)
    val x8320 = withCtrl(x8328) { ReadMem(x8104).name("x8320").srcCtx("LSTMPipeStep.scala:37:14") } // RegRead(x8104)
    val x8321 = withCtrl(x8328) { OpDef(op=FixMul, inputs=List(x8319, x8320)).name("x8321").srcCtx("LSTMPipeStep.scala:30:69:reX") } // FixMul(x8319,x8320)
    val x8322 = withCtrl(x8328) { LoadBanks(List(x7942_d0_b7), List(b3978, b3994, b4254, b4258)).name("x8322").srcCtx("LSTMPipeStep.scala:31:31") } // ParSRAMLoad(x7942,List(List(b3978, b3994, b4254, b4258)),List(x8317))
    val x8323 = withCtrl(x8328) { x8322 } // VectorApply(x8322,0)
    val x8324 = withCtrl(x8328) { ReadMem(x8112).name("x8324").srcCtx("LSTMPipeStep.scala:37:14") } // RegRead(x8112)
    val x8325 = withCtrl(x8328) { OpDef(op=FixMul, inputs=List(x8323, x8324)).name("x8325").srcCtx("LSTMPipeStep.scala:31:69:reH") } // FixMul(x8323,x8324)
    val x8326 = withCtrl(x8328) { OpDef(op=FixAdd, inputs=List(x8321, x8325)).name("x8326").srcCtx("LSTMPipeStep.scala:32:48") } // FixAdd(x8321,x8325)
    val x8327 = withCtrl(x8328) { StoreBanks(List(List(x8096_d0_b0)), List(b4254, b4258), x8326).name("x8327").srcCtx("LSTMPipeStep.scala:32:42") } // ParSRAMStore(x8096,List(List(b4254, b4258)),List(x8326),List(x8317))
    val x8331 = withCtrl(x8405) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x8331").srcCtx("LSTMPipeStep.scala:37:14") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x8332 = withCtrl(x8405) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x8332").srcCtx("LSTMPipeStep.scala:37:14") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x8333 = withCtrl(x8405) { CounterChain(List(x8332,x8331)).name("x8333").srcCtx("LSTMPipeStep.scala:37:14") } // CounterChainNew(ArrayBuffer(x8332, x8331))
    val x8404 = withCtrl(x8405) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8333).name("x8404").srcCtx("LSTMPipeStep.scala:37:14") } // UnrolledForeach(List(),x8333,Block(Const(())),ArrayBuffer(List(b4277), List(b4278)),ArrayBuffer(List(b4279), List(b4280)))
    val b4277 = withCtrl(x8404) { CounterIter(x8332, Some(0)).name("b4277") } // b4277
    val b4279 = withCtrl(x8404) { Const(true).name("b4279") } // b4279
    val b4278 = withCtrl(x8404) { CounterIter(x8331, None).name("b4278") } // b4278
    val b4280 = withCtrl(x8404) { Const(true).name("b4280") } // b4280
    val x8334 = withCtrl(x8404) { OpDef(op=BitAnd, inputs=List(b4279, b4280)).name("x8334").srcCtx("UnrollingBase.scala:28:66") } // And(b4279,b4280)
    val x8335 = withCtrl(x8404) { OpDef(op=BitAnd, inputs=List(b3979, b3975)).name("x8335").srcCtx("UnrollingBase.scala:28:66") } // And(b3979,b3975)
    val x8336 = withCtrl(x8404) { OpDef(op=BitAnd, inputs=List(x8334, x8335)).name("x8336").srcCtx("UnrollingBase.scala:28:66") } // And(x8334,x8335)
    val x8337 = withCtrl(x8404) { LoadBanks(List(x8089_d0_b0), List(b4277, b4278)).name("x8337").srcCtx("LSTMPipeStep.scala:37:14") } // ParSRAMLoad(x8089,List(ArrayBuffer(b4277, b4278)),List(x8336))
    val x8338 = withCtrl(x8404) { x8337 } // VectorApply(x8337,0)
    val x8339 = withCtrl(x8404) { LoadBanks(List(x8090_d0_b0), List(b4277, b4278)).name("x8339").srcCtx("LSTMPipeStep.scala:37:14") } // ParSRAMLoad(x8090,List(ArrayBuffer(b4277, b4278)),List(x8336))
    val x8340 = withCtrl(x8404) { x8339 } // VectorApply(x8339,0)
    val x8341 = withCtrl(x8404) { LoadBanks(List(x8091_d0_b0), List(b4277, b4278)).name("x8341").srcCtx("LSTMPipeStep.scala:37:14") } // ParSRAMLoad(x8091,List(ArrayBuffer(b4277, b4278)),List(x8336))
    val x8342 = withCtrl(x8404) { x8341 } // VectorApply(x8341,0)
    val x8343 = withCtrl(x8404) { LoadBanks(List(x8092_d0_b0), List(b4277, b4278)).name("x8343").srcCtx("LSTMPipeStep.scala:37:14") } // ParSRAMLoad(x8092,List(ArrayBuffer(b4277, b4278)),List(x8336))
    val x8344 = withCtrl(x8404) { x8343 } // VectorApply(x8343,0)
    val x8345 = withCtrl(x8404) { LoadBanks(List(x8093_d0_b0), List(b4277, b4278)).name("x8345").srcCtx("LSTMPipeStep.scala:37:14") } // ParSRAMLoad(x8093,List(ArrayBuffer(b4277, b4278)),List(x8336))
    val x8346 = withCtrl(x8404) { x8345 } // VectorApply(x8345,0)
    val x8347 = withCtrl(x8404) { LoadBanks(List(x8094_d0_b0), List(b4277, b4278)).name("x8347").srcCtx("LSTMPipeStep.scala:37:14") } // ParSRAMLoad(x8094,List(ArrayBuffer(b4277, b4278)),List(x8336))
    val x8348 = withCtrl(x8404) { x8347 } // VectorApply(x8347,0)
    val x8349 = withCtrl(x8404) { LoadBanks(List(x8095_d0_b0), List(b4277, b4278)).name("x8349").srcCtx("LSTMPipeStep.scala:37:14") } // ParSRAMLoad(x8095,List(ArrayBuffer(b4277, b4278)),List(x8336))
    val x8350 = withCtrl(x8404) { x8349 } // VectorApply(x8349,0)
    val x8351 = withCtrl(x8404) { LoadBanks(List(x8096_d0_b0), List(b4277, b4278)).name("x8351").srcCtx("LSTMPipeStep.scala:37:14") } // ParSRAMLoad(x8096,List(ArrayBuffer(b4277, b4278)),List(x8336))
    val x8352 = withCtrl(x8404) { x8351 } // VectorApply(x8351,0)
    val x8353 = withCtrl(x8404) { LoadBanks(List(x8085_d1_b0), List(b4277, b4278)).name("x8353").srcCtx("LSTMPipeStep.scala:37:14") } // ParSRAMLoad(x8085,List(ArrayBuffer(b4277, b4278)),List(x8336))
    val x8354 = withCtrl(x8404) { x8353 } // VectorApply(x8353,0)
    val x8355 = withCtrl(x8404) { OpDef(op=BitAnd, inputs=List(b3995, b3979)).name("x8355").srcCtx("LSTMPipeStep.scala:37:14") } // And(b3995,b3979)
    val x8356 = withCtrl(x8404) { OpDef(op=BitAnd, inputs=List(x8355, b3975)).name("x8356").srcCtx("LSTMPipeStep.scala:37:14") } // And(x8355,b3975)
    val x8357 = withCtrl(x8404) { OpDef(op=BitAnd, inputs=List(b3996, b3979)).name("x8357").srcCtx("LSTMPipeStep.scala:37:14") } // And(b3996,b3979)
    val x8358 = withCtrl(x8404) { OpDef(op=BitAnd, inputs=List(x8357, b3975)).name("x8358").srcCtx("LSTMPipeStep.scala:37:14") } // And(x8357,b3975)
    val x8359 = withCtrl(x8404) { OpDef(op=BitAnd, inputs=List(b3997, b3979)).name("x8359").srcCtx("LSTMPipeStep.scala:37:14") } // And(b3997,b3979)
    val x8360 = withCtrl(x8404) { OpDef(op=BitAnd, inputs=List(x8359, b3975)).name("x8360").srcCtx("LSTMPipeStep.scala:37:14") } // And(x8359,b3975)
    val x8361 = withCtrl(x8404) { OpDef(op=BitAnd, inputs=List(b3998, b3979)).name("x8361").srcCtx("LSTMPipeStep.scala:37:14") } // And(b3998,b3979)
    val x8362 = withCtrl(x8404) { OpDef(op=BitAnd, inputs=List(x8361, b3975)).name("x8362").srcCtx("LSTMPipeStep.scala:37:14") } // And(x8361,b3975)
    val x8363 = withCtrl(x8404) { OpDef(op=BitAnd, inputs=List(b3999, b3979)).name("x8363").srcCtx("LSTMPipeStep.scala:37:14") } // And(b3999,b3979)
    val x8364 = withCtrl(x8404) { OpDef(op=BitAnd, inputs=List(x8363, b3975)).name("x8364").srcCtx("LSTMPipeStep.scala:37:14") } // And(x8363,b3975)
    val x8365 = withCtrl(x8404) { OpDef(op=BitAnd, inputs=List(b4000, b3979)).name("x8365").srcCtx("LSTMPipeStep.scala:37:14") } // And(b4000,b3979)
    val x8366 = withCtrl(x8404) { OpDef(op=BitAnd, inputs=List(x8365, b3975)).name("x8366").srcCtx("LSTMPipeStep.scala:37:14") } // And(x8365,b3975)
    val x8367 = withCtrl(x8404) { OpDef(op=BitAnd, inputs=List(b4001, b3979)).name("x8367").srcCtx("LSTMPipeStep.scala:37:14") } // And(b4001,b3979)
    val x8368 = withCtrl(x8404) { OpDef(op=BitAnd, inputs=List(x8367, b3975)).name("x8368").srcCtx("LSTMPipeStep.scala:37:14") } // And(x8367,b3975)
    val x8369 = withCtrl(x8404) { OpDef(op=BitAnd, inputs=List(b4002, b3979)).name("x8369").srcCtx("LSTMPipeStep.scala:37:14") } // And(b4002,b3979)
    val x8370 = withCtrl(x8404) { OpDef(op=BitAnd, inputs=List(x8369, b3975)).name("x8370").srcCtx("LSTMPipeStep.scala:37:14") } // And(x8369,b3975)
    val x8371 = withCtrl(x8404) { OpDef(op=BitAnd, inputs=List(x8356, x8336)).name("x8371").srcCtx("LSTMPipeStep.scala:37:14") } // And(x8356,x8336)
    val x8372 = withCtrl(x8404) { OpDef(op=BitAnd, inputs=List(x8358, x8336)).name("x8372").srcCtx("LSTMPipeStep.scala:37:14") } // And(x8358,x8336)
    val x8373 = withCtrl(x8404) { OpDef(op=BitAnd, inputs=List(x8360, x8336)).name("x8373").srcCtx("LSTMPipeStep.scala:37:14") } // And(x8360,x8336)
    val x8374 = withCtrl(x8404) { OpDef(op=BitAnd, inputs=List(x8362, x8336)).name("x8374").srcCtx("LSTMPipeStep.scala:37:14") } // And(x8362,x8336)
    val x8375 = withCtrl(x8404) { OpDef(op=BitAnd, inputs=List(x8364, x8336)).name("x8375").srcCtx("LSTMPipeStep.scala:37:14") } // And(x8364,x8336)
    val x8376 = withCtrl(x8404) { OpDef(op=BitAnd, inputs=List(x8366, x8336)).name("x8376").srcCtx("LSTMPipeStep.scala:37:14") } // And(x8366,x8336)
    val x8377 = withCtrl(x8404) { OpDef(op=BitAnd, inputs=List(x8368, x8336)).name("x8377").srcCtx("LSTMPipeStep.scala:37:14") } // And(x8368,x8336)
    val x8378 = withCtrl(x8404) { OpDef(op=BitAnd, inputs=List(x8370, x8336)).name("x8378").srcCtx("LSTMPipeStep.scala:37:14") } // And(x8370,x8336)
    val x8379 = withCtrl(x8404) { OpDef(op=FixAdd, inputs=List(x8338, x8340)).name("x8379").srcCtx("LSTMPipeStep.scala:37:16") } // FixAdd(x8338,x8340)
    val x8380 = withCtrl(x8404) { OpDef(op=MuxOp, inputs=List(x8372, x8379, x8338)).name("x8380").srcCtx("LSTMPipeStep.scala:37:14") } // Mux(x8372,x8379,x8338)
    val x8381 = withCtrl(x8404) { OpDef(op=BitOr, inputs=List(x8371, x8372)).name("x8381").srcCtx("LSTMPipeStep.scala:37:14") } // Or(x8371,x8372)
    val x8382 = withCtrl(x8404) { OpDef(op=FixAdd, inputs=List(x8342, x8344)).name("x8382").srcCtx("LSTMPipeStep.scala:37:16") } // FixAdd(x8342,x8344)
    val x8383 = withCtrl(x8404) { OpDef(op=MuxOp, inputs=List(x8374, x8382, x8342)).name("x8383").srcCtx("LSTMPipeStep.scala:37:14") } // Mux(x8374,x8382,x8342)
    val x8384 = withCtrl(x8404) { OpDef(op=BitOr, inputs=List(x8373, x8374)).name("x8384").srcCtx("LSTMPipeStep.scala:37:14") } // Or(x8373,x8374)
    val x8385 = withCtrl(x8404) { OpDef(op=FixAdd, inputs=List(x8346, x8348)).name("x8385").srcCtx("LSTMPipeStep.scala:37:16") } // FixAdd(x8346,x8348)
    val x8386 = withCtrl(x8404) { OpDef(op=MuxOp, inputs=List(x8376, x8385, x8346)).name("x8386").srcCtx("LSTMPipeStep.scala:37:14") } // Mux(x8376,x8385,x8346)
    val x8387 = withCtrl(x8404) { OpDef(op=BitOr, inputs=List(x8375, x8376)).name("x8387").srcCtx("LSTMPipeStep.scala:37:14") } // Or(x8375,x8376)
    val x8388 = withCtrl(x8404) { OpDef(op=FixAdd, inputs=List(x8350, x8352)).name("x8388").srcCtx("LSTMPipeStep.scala:37:16") } // FixAdd(x8350,x8352)
    val x8389 = withCtrl(x8404) { OpDef(op=MuxOp, inputs=List(x8378, x8388, x8350)).name("x8389").srcCtx("LSTMPipeStep.scala:37:14") } // Mux(x8378,x8388,x8350)
    val x8390 = withCtrl(x8404) { OpDef(op=BitOr, inputs=List(x8377, x8378)).name("x8390").srcCtx("LSTMPipeStep.scala:37:14") } // Or(x8377,x8378)
    val x8391 = withCtrl(x8404) { OpDef(op=FixAdd, inputs=List(x8380, x8383)).name("x8391").srcCtx("LSTMPipeStep.scala:37:16") } // FixAdd(x8380,x8383)
    val x8392 = withCtrl(x8404) { OpDef(op=MuxOp, inputs=List(x8384, x8391, x8380)).name("x8392").srcCtx("LSTMPipeStep.scala:37:14") } // Mux(x8384,x8391,x8380)
    val x8393 = withCtrl(x8404) { OpDef(op=BitOr, inputs=List(x8381, x8384)).name("x8393").srcCtx("LSTMPipeStep.scala:37:14") } // Or(x8381,x8384)
    val x8394 = withCtrl(x8404) { OpDef(op=FixAdd, inputs=List(x8386, x8389)).name("x8394").srcCtx("LSTMPipeStep.scala:37:16") } // FixAdd(x8386,x8389)
    val x8395 = withCtrl(x8404) { OpDef(op=MuxOp, inputs=List(x8390, x8394, x8386)).name("x8395").srcCtx("LSTMPipeStep.scala:37:14") } // Mux(x8390,x8394,x8386)
    val x8396 = withCtrl(x8404) { OpDef(op=BitOr, inputs=List(x8387, x8390)).name("x8396").srcCtx("LSTMPipeStep.scala:37:14") } // Or(x8387,x8390)
    val x8397 = withCtrl(x8404) { OpDef(op=FixAdd, inputs=List(x8392, x8395)).name("x8397").srcCtx("LSTMPipeStep.scala:37:16") } // FixAdd(x8392,x8395)
    val x8398 = withCtrl(x8404) { OpDef(op=MuxOp, inputs=List(x8396, x8397, x8392)).name("x8398").srcCtx("LSTMPipeStep.scala:37:14") } // Mux(x8396,x8397,x8392)
    val x8399 = withCtrl(x8404) { OpDef(op=BitOr, inputs=List(x8393, x8396)).name("x8399").srcCtx("LSTMPipeStep.scala:37:14") } // Or(x8393,x8396)
    val x8400 = withCtrl(x8404) { OpDef(op=FixEql, inputs=List(b3987, Const(0))).name("x8400").srcCtx("LSTMPipeStep.scala:37:14") } // FixEql(b3987,Const(0))
    val x8401 = withCtrl(x8404) { OpDef(op=FixAdd, inputs=List(x8398, x8354)).name("x8401").srcCtx("LSTMPipeStep.scala:37:16") } // FixAdd(x8398,x8354)
    val x8402 = withCtrl(x8404) { OpDef(op=MuxOp, inputs=List(x8400, x8398, x8401)).name("x8402").srcCtx("LSTMPipeStep.scala:37:14") } // Mux(x8400,x8398,x8401)
    val x8403 = withCtrl(x8404) { StoreBanks(List(List(x8085_d0_b0, x8085_d0_b1), List(x8085_d1_b0)), List(b4277, b4278), x8402).name("x8403").srcCtx("LSTMPipeStep.scala:37:14") } // ParSRAMStore(x8085,List(ArrayBuffer(b4277, b4278)),List(x8402),List(x8336))
    antiDepsOf(x8403)=List(x8353)
    val x8406 = withCtrl(x8561) { Counter(min=Const(0), max=Const(4), step=Const(1), par=2).name("x8406").srcCtx("LSTMPipeStep.scala:39:34") } // CounterNew(Const(0),Const(4),Const(1),Const(2))
    val x8407 = withCtrl(x8561) { CounterChain(List(x8406)).name("x8407").srcCtx("LSTMPipeStep.scala:39:50") } // CounterChainNew(List(x8406))
    val x8433 = withCtrl(x8561) { LoopController(style=MetaPipe, level=OuterControl, cchain=x8407).name("x8433").srcCtx("LSTMPipeStep.scala:39:50") } // UnrolledForeach(List(b3979, b3975),x8407,Block(Const(())),List(List(b4355, b4356)),List(List(b4357, b4358)))
    val b4355 = withCtrl(x8433) { CounterIter(x8406, Some(0)).name("b4355") } // b4355
    val b4357 = withCtrl(x8433) { Const(true).name("b4357") } // b4357
    val b4356 = withCtrl(x8433) { CounterIter(x8406, Some(1)).name("b4356") } // b4356
    val b4358 = withCtrl(x8433) { Const(true).name("b4358") } // b4358
    val x8432 = withCtrl(x8433) { UnitController(style=ForkJoin, level=OuterControl).name("x8432").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b3979, b3975),Block(Const(())))
    val x8408 = withCtrl(x8432) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x8408").srcCtx("LSTMPipeStep.scala:40:42") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x8409 = withCtrl(x8432) { CounterChain(List(x8408)).name("x8409").srcCtx("LSTMPipeStep.scala:40:56") } // CounterChainNew(List(x8408))
    val x8419 = withCtrl(x8432) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8409).name("x8419").srcCtx("LSTMPipeStep.scala:40:56") } // UnrolledForeach(List(b4357, b3979, b3975),x8409,Block(Const(())),List(List(b4363)),List(List(b4364)))
    val b4363 = withCtrl(x8419) { CounterIter(x8408, None).name("b4363") } // b4363
    val b4364 = withCtrl(x8419) { Const(true).name("b4364") } // b4364
    val x8410 = withCtrl(x8419) { OpDef(op=BitAnd, inputs=List(b4364, b4357)).name("x8410").srcCtx("UnrollingBase.scala:28:66") } // And(b4364,b4357)
    val x8411 = withCtrl(x8419) { OpDef(op=BitAnd, inputs=List(b3979, b3975)).name("x8411").srcCtx("UnrollingBase.scala:28:66") } // And(b3979,b3975)
    val x8412 = withCtrl(x8419) { OpDef(op=BitAnd, inputs=List(x8410, x8411)).name("x8412").srcCtx("UnrollingBase.scala:28:66") } // And(x8410,x8411)
    val x8413 = withCtrl(x8419) { LoadBanks(List(x8085_d0_b0), List(b4355, b4363)).name("x8413").srcCtx("LSTMPipeStep.scala:41:40") } // ParSRAMLoad(x8085,List(List(b4355, b4363)),List(x8412))
    val x8414 = withCtrl(x8419) { x8413 } // VectorApply(x8413,0)
    val x8415 = withCtrl(x8419) { LoadBanks(List(x7995_d0_b0), List(b3978, b4355, b4363)).name("x8415").srcCtx("LSTMPipeStep.scala:41:64") } // ParSRAMLoad(x7995,List(List(b3978, b4355, b4363)),List(x8412))
    val x8416 = withCtrl(x8419) { x8415 } // VectorApply(x8415,0)
    val x8417 = withCtrl(x8419) { OpDef(op=FixAdd, inputs=List(x8414, x8416)).name("x8417").srcCtx("LSTMPipeStep.scala:41:61:foldVal") } // FixAdd(x8414,x8416)
    val x8418 = withCtrl(x8419) { StoreBanks(List(List(x8086_d0_b0)), List(b4355, b4363), x8417).name("x8418").srcCtx("LSTMPipeStep.scala:42:45") } // ParSRAMStore(x8086,List(List(b4355, b4363)),List(x8417),List(x8412))
    val x8420 = withCtrl(x8432) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x8420").srcCtx("LSTMPipeStep.scala:40:42") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x8421 = withCtrl(x8432) { CounterChain(List(x8420)).name("x8421").srcCtx("LSTMPipeStep.scala:40:56") } // CounterChainNew(List(x8420))
    val x8431 = withCtrl(x8432) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8421).name("x8431").srcCtx("LSTMPipeStep.scala:40:56") } // UnrolledForeach(List(b4358, b3979, b3975),x8421,Block(Const(())),List(List(b4375)),List(List(b4376)))
    val b4375 = withCtrl(x8431) { CounterIter(x8420, None).name("b4375") } // b4375
    val b4376 = withCtrl(x8431) { Const(true).name("b4376") } // b4376
    val x8422 = withCtrl(x8431) { OpDef(op=BitAnd, inputs=List(b4376, b4358)).name("x8422").srcCtx("UnrollingBase.scala:28:66") } // And(b4376,b4358)
    val x8423 = withCtrl(x8431) { OpDef(op=BitAnd, inputs=List(b3979, b3975)).name("x8423").srcCtx("UnrollingBase.scala:28:66") } // And(b3979,b3975)
    val x8424 = withCtrl(x8431) { OpDef(op=BitAnd, inputs=List(x8422, x8423)).name("x8424").srcCtx("UnrollingBase.scala:28:66") } // And(x8422,x8423)
    val x8425 = withCtrl(x8431) { LoadBanks(List(x8085_d0_b1), List(b4356, b4375)).name("x8425").srcCtx("LSTMPipeStep.scala:41:40") } // ParSRAMLoad(x8085,List(List(b4356, b4375)),List(x8424))
    val x8426 = withCtrl(x8431) { x8425 } // VectorApply(x8425,0)
    val x8427 = withCtrl(x8431) { LoadBanks(List(x7995_d0_b1), List(b3978, b4356, b4375)).name("x8427").srcCtx("LSTMPipeStep.scala:41:64") } // ParSRAMLoad(x7995,List(List(b3978, b4356, b4375)),List(x8424))
    val x8428 = withCtrl(x8431) { x8427 } // VectorApply(x8427,0)
    val x8429 = withCtrl(x8431) { OpDef(op=FixAdd, inputs=List(x8426, x8428)).name("x8429").srcCtx("LSTMPipeStep.scala:41:61:foldVal") } // FixAdd(x8426,x8428)
    val x8430 = withCtrl(x8431) { StoreBanks(List(List(x8086_d0_b1)), List(b4356, b4375), x8429).name("x8430").srcCtx("LSTMPipeStep.scala:42:45") } // ParSRAMStore(x8086,List(List(b4356, b4375)),List(x8429),List(x8424))
    val x8434 = withCtrl(x8561) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x8434").srcCtx("LSTMPipeStep.scala:48:29") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x8435 = withCtrl(x8561) { CounterChain(List(x8434)).name("x8435").srcCtx("LSTMPipeStep.scala:48:35") } // CounterChainNew(List(x8434))
    val x8560 = withCtrl(x8561) { LoopController(style=MetaPipe, level=OuterControl, cchain=x8435).name("x8560").srcCtx("LSTMPipeStep.scala:48:35") } // UnrolledForeach(List(b3979, b3975),x8435,Block(Const(())),List(List(b4391)),List(List(b4392)))
    val b4391 = withCtrl(x8560) { CounterIter(x8434, Some(0)).name("b4391") } // b4391
    val b4392 = withCtrl(x8560) { Const(true).name("b4392") } // b4392
    val x8436 = withCtrl(x8560) { FIFO(size=16).name("x8436").srcCtx("LSTMPipeStep.scala:49:33:sigQ") } // x8436 = FIFONew(Const(16))
    isAccum(x8436) = false
    bufferDepthOf(x8436) = 2
    val x8437 = withCtrl(x8560) { FIFO(size=16).name("x8437").srcCtx("LSTMPipeStep.scala:50:34:sigQQ") } // x8437 = FIFONew(Const(16))
    isAccum(x8437) = false
    bufferDepthOf(x8437) = 2
    val x8438 = withCtrl(x8560) { FIFO(size=16).name("x8438").srcCtx("LSTMPipeStep.scala:51:39:sigEleMuxQ") } // x8438 = FIFONew(Const(16))
    isAccum(x8438) = false
    bufferDepthOf(x8438) = 2
    val x8439 = withCtrl(x8560) { FIFO(size=16).name("x8439").srcCtx("LSTMPipeStep.scala:53:33:actQ") } // x8439 = FIFONew(Const(16))
    isAccum(x8439) = false
    bufferDepthOf(x8439) = 2
    val x8440 = withCtrl(x8560) { FIFO(size=16).name("x8440").srcCtx("LSTMPipeStep.scala:55:37:hUpdateQ") } // x8440 = FIFONew(Const(16))
    isAccum(x8440) = false
    bufferDepthOf(x8440) = 1
    val x8441 = withCtrl(x8560) { FIFO(size=16).name("x8441").srcCtx("LSTMPipeStep.scala:56:37:hLinearQ") } // x8441 = FIFONew(Const(16))
    isAccum(x8441) = false
    bufferDepthOf(x8441) = 1
    val x8442 = withCtrl(x8560) { FIFO(size=16).name("x8442").srcCtx("LSTMPipeStep.scala:58:37:cUpdateQ") } // x8442 = FIFONew(Const(16))
    isAccum(x8442) = false
    bufferDepthOf(x8442) = 1
    val x8443 = withCtrl(x8560) { FIFO(size=16).name("x8443").srcCtx("LSTMPipeStep.scala:59:35:cLastQ") } // x8443 = FIFONew(Const(16))
    isAccum(x8443) = false
    bufferDepthOf(x8443) = 1
    val x8444 = withCtrl(x8560) { FIFO(size=16).name("x8444").srcCtx("LSTMPipeStep.scala:61:41:cNewMultAddQ") } // x8444 = FIFONew(Const(16))
    isAccum(x8444) = false
    bufferDepthOf(x8444) = 1
    val x8445 = withCtrl(x8560) { FIFO(size=16).name("x8445").srcCtx("LSTMPipeStep.scala:62:42:cNewMultAddQQ") } // x8445 = FIFONew(Const(16))
    isAccum(x8445) = false
    bufferDepthOf(x8445) = 1
    val x8446 = withCtrl(x8560) { FIFO(size=16).name("x8446").srcCtx("LSTMPipeStep.scala:63:38:cNewMultQ") } // x8446 = FIFONew(Const(16))
    isAccum(x8446) = false
    bufferDepthOf(x8446) = 1
    val x8447 = withCtrl(x8560) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x8447").srcCtx("LSTMPipeStep.scala:65:42") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x8448 = withCtrl(x8560) { CounterChain(List(x8447)).name("x8448").srcCtx("LSTMPipeStep.scala:65:56") } // CounterChainNew(List(x8447))
    val x8471 = withCtrl(x8560) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8448).name("x8471").srcCtx("LSTMPipeStep.scala:65:56") } // UnrolledForeach(List(b4392, b3979, b3975),x8448,Block(Const(())),List(List(b4406)),List(List(b4407)))
    val b4406 = withCtrl(x8471) { CounterIter(x8447, None).name("b4406") } // b4406
    val b4407 = withCtrl(x8471) { Const(true).name("b4407") } // b4407
    val x8449 = withCtrl(x8471) { OpDef(op=BitAnd, inputs=List(b4407, b4392)).name("x8449").srcCtx("UnrollingBase.scala:28:66") } // And(b4407,b4392)
    val x8450 = withCtrl(x8471) { OpDef(op=BitAnd, inputs=List(b3979, b3975)).name("x8450").srcCtx("UnrollingBase.scala:28:66") } // And(b3979,b3975)
    val x8451 = withCtrl(x8471) { OpDef(op=BitAnd, inputs=List(x8449, x8450)).name("x8451").srcCtx("UnrollingBase.scala:28:66") } // And(x8449,x8450)
    val x8452 = withCtrl(x8471) { LoadBanks(List(x8086_d0_b0, x8086_d0_b1), List(b4391, b4406)).name("x8452").srcCtx("LSTMPipeStep.scala:66:35:pEle") } // ParSRAMLoad(x8086,List(List(b4391, b4406)),List(x8451))
    val x8453 = withCtrl(x8471) { x8452 } // VectorApply(x8452,0)
    val x8454_d0_b0 = withCtrl(x8471) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x8454_d0_b0").srcCtx("NonLinearity.scala:43:37:lut") } // x8454 = LUTNew(List(1024), Seq(Const(-0.9999997615814208984375),Const(-0.9999997615814208984375),Const(-0.999999701976776123046875)... [1021 more]))
    isAccum(x8454_d0_b0) = false
    bufferDepthOf(x8454_d0_b0) = 1
    staticDimsOf(x8454_d0_b0) = List(1024)
    val x8455 = withCtrl(x8471) { OpDef(op=FixSub, inputs=List(x8453, Const(-8.0))).name("x8455").srcCtx("NonLinearity.scala:44:22") } // FixSub(x8453,Const(-8))
    val x8456 = withCtrl(x8471) { OpDef(op=FixSla, inputs=List(x8455, Const(6))).name("x8456").srcCtx("NonLinearity.scala:44:30") } // FixLsh(x8455,Const(6))
    // x8457 = FixConvert(x8456,TRUE,_32,_0) x.tp=FixPt[TRUE,_8,_24] {
    val x8457 = withCtrl(x8471) { OpDef(op=FixSra, inputs=List(x8456, Const("24"))).name("x8457").srcCtx("NonLinearity.scala:44:41:index") } // FixConvert(x8456,TRUE,_32,_0)
    // }
    val x8458 = withCtrl(x8471) { LoadBanks(List(x8454_d0_b0), List(x8457)).name("x8458").srcCtx("NonLinearity.scala:45:17:sigVal") } // LUTLoad(x8454,List(x8457),x8451)
    val x8459_d0_b0 = withCtrl(x8471) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x8459_d0_b0").srcCtx("NonLinearity.scala:43:37:lut") } // x8459 = LUTNew(List(1024), Seq(Const(-0.9999997615814208984375),Const(-0.9999997615814208984375),Const(-0.999999701976776123046875)... [1021 more]))
    isAccum(x8459_d0_b0) = false
    bufferDepthOf(x8459_d0_b0) = 1
    staticDimsOf(x8459_d0_b0) = List(1024)
    val x8460 = withCtrl(x8471) { LoadBanks(List(x8459_d0_b0), List(x8457)).name("x8460").srcCtx("NonLinearity.scala:45:17:actVal") } // LUTLoad(x8459,List(x8457),x8451)
    val x8461 = withCtrl(x8471) { OpDef(op=FixLt, inputs=List(Const(8.0), x8453)).name("x8461").srcCtx("LSTMPipeStep.scala:70:35:isHigh") } // FixLt(Const(8),x8453)
    val x8462 = withCtrl(x8471) { OpDef(op=FixLt, inputs=List(x8453, Const(-8.0))).name("x8462").srcCtx("LSTMPipeStep.scala:71:34:isLow") } // FixLt(x8453,Const(-8))
    val x8463 = withCtrl(x8471) { OpDef(op=MuxOp, inputs=List(x8462, Const(0.0), x8458)).name("x8463").srcCtx("LSTMPipeStep.scala:73:54") } // Mux(x8462,Const(0),x8458)
    val x8464 = withCtrl(x8471) { OpDef(op=MuxOp, inputs=List(x8461, Const(1.0), x8463)).name("x8464").srcCtx("LSTMPipeStep.scala:73:33:sigEle") } // Mux(x8461,Const(1),x8463)
    val x8465 = withCtrl(x8471) { OpDef(op=MuxOp, inputs=List(x8462, Const(-1.0), x8460)).name("x8465").srcCtx("LSTMPipeStep.scala:74:54") } // Mux(x8462,Const(-1),x8460)
    val x8466 = withCtrl(x8471) { OpDef(op=MuxOp, inputs=List(x8461, Const(1.0), x8465)).name("x8466").srcCtx("LSTMPipeStep.scala:74:33:actEle") } // Mux(x8461,Const(1),x8465)
    val x8467_x8436 = withCtrl(x8471) { WriteMem(x8436, x8464).name("x8467_x8436").srcCtx("LSTMPipeStep.scala:76:25") } // ParFIFOEnq(x8436,List(x8464),List(x8451))
    val x8468_x8437 = withCtrl(x8471) { WriteMem(x8437, x8464).name("x8468_x8437").srcCtx("LSTMPipeStep.scala:77:26") } // ParFIFOEnq(x8437,List(x8464),List(x8451))
    val x8469_x8438 = withCtrl(x8471) { WriteMem(x8438, x8464).name("x8469_x8438").srcCtx("LSTMPipeStep.scala:78:31") } // ParFIFOEnq(x8438,List(x8464),List(x8451))
    val x8470_x8439 = withCtrl(x8471) { WriteMem(x8439, x8466).name("x8470_x8439").srcCtx("LSTMPipeStep.scala:80:25") } // ParFIFOEnq(x8439,List(x8466),List(x8451))
    val x8559 = withCtrl(x8560) { UnitController(style=SeqPipe, level=OuterControl).name("x8559").srcCtx("LSTMPipeStep.scala:85:20") } // UnitPipe(List(b4392, b3979, b3975),Block(Const(())))
    val x8472 = withCtrl(x8559) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x8472").srcCtx("LSTMPipeStep.scala:87:44") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x8473 = withCtrl(x8559) { CounterChain(List(x8472)).name("x8473").srcCtx("LSTMPipeStep.scala:87:58") } // CounterChainNew(List(x8472))
    val x8490 = withCtrl(x8559) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8473).name("x8490").srcCtx("LSTMPipeStep.scala:87:58") } // UnrolledForeach(List(b4392, b3979, b3975),x8473,Block(Const(())),List(List(b4433)),List(List(b4434)))
    val b4433 = withCtrl(x8490) { CounterIter(x8472, None).name("b4433") } // b4433
    val b4434 = withCtrl(x8490) { Const(true).name("b4434") } // b4434
    val x8474 = withCtrl(x8490) { OpDef(op=BitAnd, inputs=List(b4434, b4392)).name("x8474").srcCtx("UnrollingBase.scala:28:66") } // And(b4434,b4392)
    val x8475 = withCtrl(x8490) { OpDef(op=BitAnd, inputs=List(b3979, b3975)).name("x8475").srcCtx("UnrollingBase.scala:28:66") } // And(b3979,b3975)
    val x8476 = withCtrl(x8490) { OpDef(op=BitAnd, inputs=List(x8474, x8475)).name("x8476").srcCtx("UnrollingBase.scala:28:66") } // And(x8474,x8475)
    val x8477 = withCtrl(x8490) { ReadMem(x8436).name("x8477").srcCtx("LSTMPipeStep.scala:88:40:sigEle") } // ParFIFODeq(x8436,List(x8476))
    val x8478 = withCtrl(x8490) { x8477 } // VectorApply(x8477,0)
    val x8479 = withCtrl(x8490) { ReadMem(x8439).name("x8479").srcCtx("LSTMPipeStep.scala:89:40:actEle") } // ParFIFODeq(x8439,List(x8476))
    val x8480 = withCtrl(x8490) { x8479 } // VectorApply(x8479,0)
    val x8481 = withCtrl(x8490) { LoadBanks(List(x7819_d0_b0), List(b3978, b4433)).name("x8481").srcCtx("LSTMPipeStep.scala:91:32:cLast") } // ParSRAMLoad(x7819,List(List(b3978, b4433)),List(x8476))
    val x8482 = withCtrl(x8490) { x8481 } // VectorApply(x8481,0)
    val x8483 = withCtrl(x8490) { OpDef(op=FixMul, inputs=List(x8482, x8480)).name("x8483").srcCtx("LSTMPipeStep.scala:92:40:cNewMult") } // FixMul(x8482,x8480)
    val x8484 = withCtrl(x8490) { OpDef(op=FixMul, inputs=List(x8478, x8482)).name("x8484").srcCtx("LSTMPipeStep.scala:93:44") } // FixMul(x8478,x8482)
    val x8485 = withCtrl(x8490) { OpDef(op=FixAdd, inputs=List(x8484, x8483)).name("x8485").srcCtx("LSTMPipeStep.scala:93:52:cNewMultAdd") } // FixAdd(x8484,x8483)
    val x8486_x8446 = withCtrl(x8490) { WriteMem(x8446, x8483).name("x8486_x8446").srcCtx("LSTMPipeStep.scala:95:32") } // ParFIFOEnq(x8446,List(x8483),List(x8476))
    val x8487_x8444 = withCtrl(x8490) { WriteMem(x8444, x8485).name("x8487_x8444").srcCtx("LSTMPipeStep.scala:96:35") } // ParFIFOEnq(x8444,List(x8485),List(x8476))
    val x8488_x8445 = withCtrl(x8490) { WriteMem(x8445, x8485).name("x8488_x8445").srcCtx("LSTMPipeStep.scala:97:36") } // ParFIFOEnq(x8445,List(x8485),List(x8476))
    val x8489_x8443 = withCtrl(x8490) { WriteMem(x8443, x8482).name("x8489_x8443").srcCtx("LSTMPipeStep.scala:98:29") } // ParFIFOEnq(x8443,List(x8482),List(x8476))
    val x8491 = withCtrl(x8559) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x8491").srcCtx("LSTMPipeStep.scala:101:44") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x8492 = withCtrl(x8559) { CounterChain(List(x8491)).name("x8492").srcCtx("LSTMPipeStep.scala:101:58") } // CounterChainNew(List(x8491))
    val x8511 = withCtrl(x8559) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8492).name("x8511").srcCtx("LSTMPipeStep.scala:101:58") } // UnrolledForeach(List(b4392, b3979, b3975),x8492,Block(Const(())),List(List(b4454)),List(List(b4455)))
    val b4454 = withCtrl(x8511) { CounterIter(x8491, None).name("b4454") } // b4454
    val b4455 = withCtrl(x8511) { Const(true).name("b4455") } // b4455
    val x8493 = withCtrl(x8511) { OpDef(op=BitAnd, inputs=List(b4455, b4392)).name("x8493").srcCtx("UnrollingBase.scala:28:66") } // And(b4455,b4392)
    val x8494 = withCtrl(x8511) { OpDef(op=BitAnd, inputs=List(b3979, b3975)).name("x8494").srcCtx("UnrollingBase.scala:28:66") } // And(b3979,b3975)
    val x8495 = withCtrl(x8511) { OpDef(op=BitAnd, inputs=List(x8493, x8494)).name("x8495").srcCtx("UnrollingBase.scala:28:66") } // And(x8493,x8494)
    val x8496 = withCtrl(x8511) { ReadMem(x8446).name("x8496").srcCtx("LSTMPipeStep.scala:102:47:cNewMult") } // ParFIFODeq(x8446,List(x8495))
    val x8497 = withCtrl(x8511) { x8496 } // VectorApply(x8496,0)
    val x8498 = withCtrl(x8511) { ReadMem(x8438).name("x8498").srcCtx("LSTMPipeStep.scala:103:46:sigEle") } // ParFIFODeq(x8438,List(x8495))
    val x8499 = withCtrl(x8511) { x8498 } // VectorApply(x8498,0)
    val x8500 = withCtrl(x8511) { ReadMem(x8444).name("x8500").srcCtx("LSTMPipeStep.scala:104:53:cNewMultAdd") } // ParFIFODeq(x8444,List(x8495))
    val x8501 = withCtrl(x8511) { x8500 } // VectorApply(x8500,0)
    val x8502 = withCtrl(x8511) { ReadMem(x8443).name("x8502").srcCtx("LSTMPipeStep.scala:105:41:cLast") } // ParFIFODeq(x8443,List(x8495))
    val x8503 = withCtrl(x8511) { x8502 } // VectorApply(x8502,0)
    val x8504 = withCtrl(x8511) { OpDef(op=FixEql, inputs=List(b4391, Const(0))).name("x8504").srcCtx("package.scala:36:42") } // FixEql(b4391,Const(0))
    val x8505 = withCtrl(x8511) { OpDef(op=FixEql, inputs=List(b4391, Const(1))).name("x8505").srcCtx("package.scala:39:42") } // FixEql(b4391,Const(1))
    val x8506 = withCtrl(x8511) { OpDef(op=FixEql, inputs=List(b4391, Const(2))).name("x8506").srcCtx("package.scala:42:42") } // FixEql(b4391,Const(2))
    val x8507 = withCtrl(x8511) { OpDef(op=MuxOp, inputs=List(x8506, x8501, x8503)).name("x8507").srcCtx("LSTMPipeStep.scala:110:38") } // Mux(x8506,x8501,x8503)
    val x8508 = withCtrl(x8511) { OpDef(op=MuxOp, inputs=List(x8505, x8497, x8507)).name("x8508").srcCtx("LSTMPipeStep.scala:109:38") } // Mux(x8505,x8497,x8507)
    val x8509 = withCtrl(x8511) { OpDef(op=MuxOp, inputs=List(x8504, x8499, x8508)).name("x8509").srcCtx("LSTMPipeStep.scala:108:36:cUpdate") } // Mux(x8504,x8499,x8508)
    val x8510_x8442 = withCtrl(x8511) { WriteMem(x8442, x8509).name("x8510_x8442").srcCtx("LSTMPipeStep.scala:113:31") } // ParFIFOEnq(x8442,List(x8509),List(x8495))
    val x8512 = withCtrl(x8559) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x8512").srcCtx("LSTMPipeStep.scala:116:44") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x8513 = withCtrl(x8559) { CounterChain(List(x8512)).name("x8513").srcCtx("LSTMPipeStep.scala:116:58") } // CounterChainNew(List(x8512))
    val x8529 = withCtrl(x8559) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8513).name("x8529").srcCtx("LSTMPipeStep.scala:116:58") } // UnrolledForeach(List(b4392, b3979, b3975),x8513,Block(Const(())),List(List(b4477)),List(List(b4478)))
    val b4477 = withCtrl(x8529) { CounterIter(x8512, None).name("b4477") } // b4477
    val b4478 = withCtrl(x8529) { Const(true).name("b4478") } // b4478
    val x8514 = withCtrl(x8529) { OpDef(op=BitAnd, inputs=List(b4478, b4392)).name("x8514").srcCtx("UnrollingBase.scala:28:66") } // And(b4478,b4392)
    val x8515 = withCtrl(x8529) { OpDef(op=BitAnd, inputs=List(b3979, b3975)).name("x8515").srcCtx("UnrollingBase.scala:28:66") } // And(b3979,b3975)
    val x8516 = withCtrl(x8529) { OpDef(op=BitAnd, inputs=List(x8514, x8515)).name("x8516").srcCtx("UnrollingBase.scala:28:66") } // And(x8514,x8515)
    val x8517 = withCtrl(x8529) { ReadMem(x8445).name("x8517").srcCtx("LSTMPipeStep.scala:117:54:cNewMultAdd") } // ParFIFODeq(x8445,List(x8516))
    val x8518 = withCtrl(x8529) { x8517 } // VectorApply(x8517,0)
    val x8519_d0_b0 = withCtrl(x8529) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x8519_d0_b0").srcCtx("NonLinearity.scala:43:37:lut") } // x8519 = LUTNew(List(1024), Seq(Const(-0.9999997615814208984375),Const(-0.9999997615814208984375),Const(-0.999999701976776123046875)... [1021 more]))
    isAccum(x8519_d0_b0) = false
    bufferDepthOf(x8519_d0_b0) = 1
    staticDimsOf(x8519_d0_b0) = List(1024)
    val x8520 = withCtrl(x8529) { OpDef(op=FixSub, inputs=List(x8518, Const(-8.0))).name("x8520").srcCtx("NonLinearity.scala:44:22") } // FixSub(x8518,Const(-8))
    val x8521 = withCtrl(x8529) { OpDef(op=FixSla, inputs=List(x8520, Const(6))).name("x8521").srcCtx("NonLinearity.scala:44:30") } // FixLsh(x8520,Const(6))
    // x8522 = FixConvert(x8521,TRUE,_32,_0) x.tp=FixPt[TRUE,_8,_24] {
    val x8522 = withCtrl(x8529) { OpDef(op=FixSra, inputs=List(x8521, Const("24"))).name("x8522").srcCtx("NonLinearity.scala:44:41:index") } // FixConvert(x8521,TRUE,_32,_0)
    def split2 = {
    // }
    val x8523 = withCtrl(x8529) { LoadBanks(List(x8519_d0_b0), List(x8522)).name("x8523").srcCtx("NonLinearity.scala:45:17:actValMultAdd") } // LUTLoad(x8519,List(x8522),x8516)
    val x8524 = withCtrl(x8529) { OpDef(op=FixLt, inputs=List(Const(8.0), x8518)).name("x8524").srcCtx("LSTMPipeStep.scala:120:44:isHigh") } // FixLt(Const(8),x8518)
    val x8525 = withCtrl(x8529) { OpDef(op=FixLt, inputs=List(x8518, Const(-8.0))).name("x8525").srcCtx("LSTMPipeStep.scala:121:43:isLow") } // FixLt(x8518,Const(-8))
    val x8526 = withCtrl(x8529) { OpDef(op=MuxOp, inputs=List(x8525, Const(-1.0), x8523)).name("x8526").srcCtx("LSTMPipeStep.scala:122:57") } // Mux(x8525,Const(-1),x8523)
    val x8527 = withCtrl(x8529) { OpDef(op=MuxOp, inputs=List(x8524, Const(1.0), x8526)).name("x8527").srcCtx("LSTMPipeStep.scala:122:36:hLinear") } // Mux(x8524,Const(1),x8526)
    val x8528_x8441 = withCtrl(x8529) { WriteMem(x8441, x8527).name("x8528_x8441").srcCtx("LSTMPipeStep.scala:124:31") } // ParFIFOEnq(x8441,List(x8527),List(x8516))
    val x8530 = withCtrl(x8559) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x8530").srcCtx("LSTMPipeStep.scala:127:44") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x8531 = withCtrl(x8559) { CounterChain(List(x8530)).name("x8531").srcCtx("LSTMPipeStep.scala:127:58") } // CounterChainNew(List(x8530))
    val x8545 = withCtrl(x8559) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8531).name("x8545").srcCtx("LSTMPipeStep.scala:127:58") } // UnrolledForeach(List(b4392, b3979, b3975),x8531,Block(Const(())),List(List(b4497)),List(List(b4498)))
    val b4497 = withCtrl(x8545) { CounterIter(x8530, None).name("b4497") } // b4497
    val b4498 = withCtrl(x8545) { Const(true).name("b4498") } // b4498
    val x8532 = withCtrl(x8545) { OpDef(op=BitAnd, inputs=List(b4498, b4392)).name("x8532").srcCtx("UnrollingBase.scala:28:66") } // And(b4498,b4392)
    val x8533 = withCtrl(x8545) { OpDef(op=BitAnd, inputs=List(b3979, b3975)).name("x8533").srcCtx("UnrollingBase.scala:28:66") } // And(b3979,b3975)
    val x8534 = withCtrl(x8545) { OpDef(op=BitAnd, inputs=List(x8532, x8533)).name("x8534").srcCtx("UnrollingBase.scala:28:66") } // And(x8532,x8533)
    val x8535 = withCtrl(x8545) { ReadMem(x8441).name("x8535").srcCtx("LSTMPipeStep.scala:129:45:hLinear") } // ParFIFODeq(x8441,List(x8534))
    val x8536 = withCtrl(x8545) { x8535 } // VectorApply(x8535,0)
    val x8537 = withCtrl(x8545) { ReadMem(x8437).name("x8537").srcCtx("LSTMPipeStep.scala:130:41:sigEle") } // ParFIFODeq(x8437,List(x8534))
    val x8538 = withCtrl(x8545) { x8537 } // VectorApply(x8537,0)
    val x8539 = withCtrl(x8545) { LoadBanks(List(x7854_d0_b0), List(b3978, b4497)).name("x8539").srcCtx("LSTMPipeStep.scala:132:32:hLast") } // ParSRAMLoad(x7854,List(List(b3978, b4497)),List(x8534))
    val x8540 = withCtrl(x8545) { x8539 } // VectorApply(x8539,0)
    val x8541 = withCtrl(x8545) { OpDef(op=FixMul, inputs=List(x8536, x8538)).name("x8541").srcCtx("LSTMPipeStep.scala:133:38:hNew") } // FixMul(x8536,x8538)
    val x8542 = withCtrl(x8545) { OpDef(op=FixEql, inputs=List(b4391, Const(3))).name("x8542").srcCtx("package.scala:45:42") } // FixEql(b4391,Const(3))
    val x8543 = withCtrl(x8545) { OpDef(op=MuxOp, inputs=List(x8542, x8541, x8540)).name("x8543").srcCtx("LSTMPipeStep.scala:134:36:hUpdate") } // Mux(x8542,x8541,x8540)
    val x8544_x8440 = withCtrl(x8545) { WriteMem(x8440, x8543).name("x8544_x8440").srcCtx("LSTMPipeStep.scala:136:31") } // ParFIFOEnq(x8440,List(x8543),List(x8534))
    val x8546 = withCtrl(x8559) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x8546").srcCtx("LSTMPipeStep.scala:139:44") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x8547 = withCtrl(x8559) { CounterChain(List(x8546)).name("x8547").srcCtx("LSTMPipeStep.scala:139:58") } // CounterChainNew(List(x8546))
    val x8558 = withCtrl(x8559) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8547).name("x8558").srcCtx("LSTMPipeStep.scala:139:58") } // UnrolledForeach(List(b4392, b3979, b3975),x8547,Block(Const(())),List(List(b4515)),List(List(b4516)))
    val b4515 = withCtrl(x8558) { CounterIter(x8546, None).name("b4515") } // b4515
    val b4516 = withCtrl(x8558) { Const(true).name("b4516") } // b4516
    val x8548 = withCtrl(x8558) { OpDef(op=BitAnd, inputs=List(b4516, b4392)).name("x8548").srcCtx("UnrollingBase.scala:28:66") } // And(b4516,b4392)
    val x8549 = withCtrl(x8558) { OpDef(op=BitAnd, inputs=List(b3979, b3975)).name("x8549").srcCtx("UnrollingBase.scala:28:66") } // And(b3979,b3975)
    val x8550 = withCtrl(x8558) { OpDef(op=BitAnd, inputs=List(x8548, x8549)).name("x8550").srcCtx("UnrollingBase.scala:28:66") } // And(x8548,x8549)
    val x8551 = withCtrl(x8558) { ReadMem(x8440).name("x8551").srcCtx("LSTMPipeStep.scala:140:39:hNew") } // ParFIFODeq(x8440,List(x8550))
    val x8552 = withCtrl(x8558) { x8551 } // VectorApply(x8551,0)
    val x8553 = withCtrl(x8558) { ReadMem(x8442).name("x8553").srcCtx("LSTMPipeStep.scala:141:39:cNew") } // ParFIFODeq(x8442,List(x8550))
    val x8554 = withCtrl(x8558) { x8553 } // VectorApply(x8553,0)
    val x8555 = withCtrl(x8558) { StoreBanks(List(List(x7854_d0_b0), List(x7854_d1_b0)), List(b3978, b4515), x8552).name("x8555").srcCtx("LSTMPipeStep.scala:142:42") } // ParSRAMStore(x7854,List(List(b3978, b4515)),List(x8552),List(x8550))
    val x8556 = withCtrl(x8558) { StoreBanks(List(List(x8037_d0_b0), List(x8037_d1_b0)), List(b3974, b4515), x8552).name("x8556").srcCtx("LSTMPipeStep.scala:143:41") } // ParSRAMStore(x8037,List(List(b3974, b4515)),List(x8552),List(x8550))
    val x8557 = withCtrl(x8558) { StoreBanks(List(List(x7819_d0_b0)), List(b3978, b4515), x8554).name("x8557").srcCtx("LSTMPipeStep.scala:144:42") } // ParSRAMStore(x7819,List(List(b3978, b4515)),List(x8554),List(x8550))
    val x8564 = withCtrl(x8602) { Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x8564").srcCtx("LSTMPipeStep.scala:158:56") } // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x8565 = withCtrl(x8602) { Counter(min=Const(0), max=Const(8), step=Const(1), par=1).name("x8565").srcCtx("LSTMPipeStep.scala:158:56") } // CounterNew(Const(0),Const(8),Const(1),Const(1))
    val x8566 = withCtrl(x8602) { CounterChain(List(x8564,x8565)).name("x8566").srcCtx("LSTMPipeStep.scala:158:56") } // CounterChainNew(List(x8564, x8565))
    val x8601 = withCtrl(x8602) { LoopController(style=StreamPipe, level=OuterControl, cchain=x8566).name("x8601").srcCtx("LSTMPipeStep.scala:158:56") } // UnrolledForeach(List(b3975),x8566,Block(Const(())),List(List(b4536), List(b4537)),List(List(b4538), List(b4539)))
    val b4536 = withCtrl(x8601) { CounterIter(x8564, Some(0)).name("b4536") } // b4536
    val b4538 = withCtrl(x8601) { Const(true).name("b4538") } // b4538
    val b4537 = withCtrl(x8601) { CounterIter(x8565, Some(0)).name("b4537") } // b4537
    val b4539 = withCtrl(x8601) { Const(true).name("b4539") } // b4539
    val b8642 = withCtrl(x8601) { StreamOut(field="offset").name("b8642").srcCtx("LSTMPipeStep.scala:158:56") } // x8567 = StreamOutNew(BurstCmdBus)
    isAccum(b8642) = false
    bufferDepthOf(b8642) = 1
    val b8643 = withCtrl(x8601) { StreamOut(field="size").name("b8643").srcCtx("LSTMPipeStep.scala:158:56") } // x8567 = StreamOutNew(BurstCmdBus)
    isAccum(b8643) = false
    bufferDepthOf(b8643) = 1
    val x8568 = withCtrl(x8601) { StreamOut(field="data").name("x8568").srcCtx("LSTMPipeStep.scala:158:56") } // x8568 = StreamOutNew(BurstFullDataBus())
    isAccum(x8568) = false
    bufferDepthOf(x8568) = 1
    val x8569 = withCtrl(x8601) { StreamIn(field="ack").name("x8569").srcCtx("LSTMPipeStep.scala:158:56") } // x8569 = StreamInNew(BurstAckBus)
    isAccum(x8569) = false
    bufferDepthOf(x8569) = 1
    val x8585 = withCtrl(x8601) { UnitController(style=SeqPipe, level=InnerControl).name("x8585").srcCtx("LSTMPipeStep.scala:158:56") } // UnitPipe(List(b4538, b4539, b3975),Block(x8584))
    val x8570 = withCtrl(x8585) { b4536 } // FixConvert(b4536,TRUE,_32,_0) (Same Type. No op)
    val x8571 = withCtrl(x8585) { OpDef(op=FixSla, inputs=List(x8570, Const(10))).name("x8571").srcCtx("LSTMPipeStep.scala:158:56") } // FixLsh(x8570,Const(10))
    val x8572 = withCtrl(x8585) { b4537 } // FixConvert(b4537,TRUE,_32,_0) (Same Type. No op)
    val x8573 = withCtrl(x8585) { OpDef(op=FixSla, inputs=List(x8572, Const(7))).name("x8573").srcCtx("LSTMPipeStep.scala:158:56") } // FixLsh(x8572,Const(7))
    val x8574 = withCtrl(x8585) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x8575 = withCtrl(x8585) { OpDef(op=FixAdd, inputs=List(x8571, x8573)).name("x8575").srcCtx("LSTMPipeStep.scala:158:56") } // FixAdd(x8571,x8573)
    val x8576 = withCtrl(x8585) { OpDef(op=FixAdd, inputs=List(x8575, x8574)).name("x8576").srcCtx("LSTMPipeStep.scala:158:56") } // FixAdd(x8575,x8574)
    val x8577 = withCtrl(x8585) { OpDef(op=FixSla, inputs=List(x8576, Const(2))).name("x8577").srcCtx("LSTMPipeStep.scala:158:56") } // FixLsh(x8576,Const(2))
    val x8578 = withCtrl(x8585) { x8577 } // FixConvert(x8577,TRUE,_64,_0)
    val x8579 = withCtrl(x8585) { DramAddress(x7816).name("x8579").srcCtx("LSTMPipeStep.scala:158:56") } // GetDRAMAddress(x7816)
    val x8581_x8580 = withCtrl(x8585) { OpDef(op=FixAdd, inputs=List(x8578, x8579)).name("x8581_x8580").srcCtx("LSTMPipeStep.scala:158:56") } // FixAdd(x8578,x8579)
    // x8581 = SimpleStruct(ArrayBuffer((offset,x8580), (size,Const(512)), (isLoad,Const(false))))
    val x8582 = withCtrl(x8585) { OpDef(op=BitAnd, inputs=List(b4538, b4539)).name("x8582").srcCtx("UnrollingBase.scala:28:66") } // And(b4538,b4539)
    val x8583 = withCtrl(x8585) { OpDef(op=BitAnd, inputs=List(x8582, b3975)).name("x8583").srcCtx("UnrollingBase.scala:28:66") } // And(x8582,b3975)
    val x8584_b8644_b8642 = withCtrl(x8585) { WriteMem(b8642, x8581_x8580).name("x8584_b8644_b8642").srcCtx("LSTMPipeStep.scala:158:56") } // StreamWrite(x8567,x8581,x8583)
    val x8584_b8645_b8643 = withCtrl(x8585) { WriteMem(b8643, Const(512)).name("x8584_b8645_b8643").srcCtx("LSTMPipeStep.scala:158:56") } // StreamWrite(x8567,x8581,x8583)
    val x8586 = withCtrl(x8601) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x8586").srcCtx("LSTMPipeStep.scala:158:56") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x8587 = withCtrl(x8601) { CounterChain(List(x8586)).name("x8587").srcCtx("LSTMPipeStep.scala:158:56") } // CounterChainNew(List(x8586))
    val x8595 = withCtrl(x8601) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8587).name("x8595").srcCtx("LSTMPipeStep.scala:158:56") } // UnrolledForeach(List(b4538, b4539, b3975),x8587,Block(Const(())),List(List(b4561)),List(List(b4562)))
    val b4561 = withCtrl(x8595) { CounterIter(x8586, None).name("b4561") } // b4561
    val b4562 = withCtrl(x8595) { Const(true).name("b4562") } // b4562
    val x8588 = withCtrl(x8595) { OpDef(op=BitAnd, inputs=List(b4562, b4538)).name("x8588").srcCtx("UnrollingBase.scala:28:66") } // And(b4562,b4538)
    val x8589 = withCtrl(x8595) { OpDef(op=BitAnd, inputs=List(b4539, b3975)).name("x8589").srcCtx("UnrollingBase.scala:28:66") } // And(b4539,b3975)
    val x8590 = withCtrl(x8595) { OpDef(op=BitAnd, inputs=List(x8588, x8589)).name("x8590").srcCtx("UnrollingBase.scala:28:66") } // And(x8588,x8589)
    val x8591 = withCtrl(x8595) { LoadBanks(List(x8037_d0_b0), List(b4537, b4561)).name("x8591").srcCtx("LSTMPipeStep.scala:158:56") } // ParSRAMLoad(x8037,List(List(b4537, b4561)),List(x8590))
    val x8593_x8592 = withCtrl(x8595) { x8591 } // VectorApply(x8591,0)
    // x8593 = SimpleStruct(ArrayBuffer((_1,x8592), (_2,Const(true))))
    val x8594_x8594_x8568 = withCtrl(x8595) { WriteMem(x8568, x8593_x8592).name("x8594_x8594_x8568").srcCtx("LSTMPipeStep.scala:158:56") } // ParStreamWrite(x8568,List(x8593),List(x8590))
    val x8596 = withCtrl(x8601) { FringeDenseStore(dram=List(x7816), cmdStream=List(b8642, b8643), dataStream=List(x8568), ackStream=List(x8569)).name("x8596").srcCtx("LSTMPipeStep.scala:158:56") } // FringeDenseStore(x7816,x8567,x8568,x8569)
    val x8600 = withCtrl(x8601) { UnitController(style=SeqPipe, level=InnerControl).name("x8600").srcCtx("LSTMPipeStep.scala:158:56") } // UnitPipe(List(b4538, b4539, b3975),Block(Const(())))
    val x8597 = withCtrl(x8600) { OpDef(op=BitAnd, inputs=List(b4538, b4539)).name("x8597").srcCtx("UnrollingBase.scala:28:66") } // And(b4538,b4539)
    val x8598 = withCtrl(x8600) { OpDef(op=BitAnd, inputs=List(x8597, b3975)).name("x8598").srcCtx("UnrollingBase.scala:28:66") } // And(x8597,b3975)
    val x8599_x8599 = withCtrl(x8600) { ReadMem(x8569).name("x8599_x8599").srcCtx("LSTMPipeStep.scala:158:56") } // StreamRead(x8569,x8598)
    }; split2
    }; split1
    
  }
}
