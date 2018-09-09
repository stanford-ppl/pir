import pir._
import pir.node._
import arch._
import prism.enums._

object DBLSTM_256_1_1_4 extends PIRApp {
  def main(implicit design:PIRDesign) = {
    import design.pirmeta._
    val x3597 = withCtrl(design.top.topController) { DRAM(dims=List(Const(256))).name("x3597").srcCtx("DeepBenchLSTM256.scala:57:23:dout") } // x3597 = DRAMNew(ArrayBuffer(Const(256)),Const(0))
    val x3904 = withCtrl(design.top.topController) { UnitController(style=SeqPipe, level=OuterControl).name("x3904").srcCtx("DeepBenchLSTM256.scala:59:11") } // Hwblock(Block(Const(())),false)
    val x3598_d0_b0 = withCtrl(x3904) { LUT(inits=Nil, banking=Strided(banks=16, stride=1)).name("x3598_d0_b0").srcCtx("DataGenerator.scala:248:40:wh") } // x3598 = LUTNew(List(256, 4, 256), Seq(Const(0),Const(0),Const(0)... [262141 more]))
    isAccum(x3598_d0_b0) = false
    bufferDepthOf(x3598_d0_b0) = 1
    staticDimsOf(x3598_d0_b0) = List(256, 4, 256)
    val x3598_d0_b1 = withCtrl(x3904) { LUT(inits=Nil, banking=Strided(banks=16, stride=1)).name("x3598_d0_b1").srcCtx("DataGenerator.scala:248:40:wh") } // x3598 = LUTNew(List(256, 4, 256), Seq(Const(0),Const(0),Const(0)... [262141 more]))
    isAccum(x3598_d0_b1) = false
    bufferDepthOf(x3598_d0_b1) = 1
    staticDimsOf(x3598_d0_b1) = List(256, 4, 256)
    val x3598_d0_b2 = withCtrl(x3904) { LUT(inits=Nil, banking=Strided(banks=16, stride=1)).name("x3598_d0_b2").srcCtx("DataGenerator.scala:248:40:wh") } // x3598 = LUTNew(List(256, 4, 256), Seq(Const(0),Const(0),Const(0)... [262141 more]))
    isAccum(x3598_d0_b2) = false
    bufferDepthOf(x3598_d0_b2) = 1
    staticDimsOf(x3598_d0_b2) = List(256, 4, 256)
    val x3598_d0_b3 = withCtrl(x3904) { LUT(inits=Nil, banking=Strided(banks=16, stride=1)).name("x3598_d0_b3").srcCtx("DataGenerator.scala:248:40:wh") } // x3598 = LUTNew(List(256, 4, 256), Seq(Const(0),Const(0),Const(0)... [262141 more]))
    isAccum(x3598_d0_b3) = false
    bufferDepthOf(x3598_d0_b3) = 1
    staticDimsOf(x3598_d0_b3) = List(256, 4, 256)
    val x3599_d0_b0 = withCtrl(x3904) { LUT(inits=Nil, banking=Strided(banks=16, stride=1)).name("x3599_d0_b0").srcCtx("DataGenerator.scala:248:40:wxt0") } // x3599 = LUTNew(List(64, 4, 256), Seq(Const(0),Const(0),Const(0)... [65533 more]))
    isAccum(x3599_d0_b0) = false
    bufferDepthOf(x3599_d0_b0) = 1
    staticDimsOf(x3599_d0_b0) = List(64, 4, 256)
    val x3600_d0_b0 = withCtrl(x3904) { LUT(inits=Nil, banking=Strided(banks=16, stride=1)).name("x3600_d0_b0").srcCtx("DataGenerator.scala:248:40:wxt1") } // x3600 = LUTNew(List(64, 4, 256), Seq(Const(0),Const(0),Const(0)... [65533 more]))
    isAccum(x3600_d0_b0) = false
    bufferDepthOf(x3600_d0_b0) = 1
    staticDimsOf(x3600_d0_b0) = List(64, 4, 256)
    val x3601_d0_b0 = withCtrl(x3904) { LUT(inits=Nil, banking=Strided(banks=16, stride=1)).name("x3601_d0_b0").srcCtx("DataGenerator.scala:248:40:wxt2") } // x3601 = LUTNew(List(64, 4, 256), Seq(Const(0),Const(0),Const(0)... [65533 more]))
    isAccum(x3601_d0_b0) = false
    bufferDepthOf(x3601_d0_b0) = 1
    staticDimsOf(x3601_d0_b0) = List(64, 4, 256)
    val x3602_d0_b0 = withCtrl(x3904) { SRAM(size=256, banking=Strided(banks=16, stride=1)).name("x3602_d0_b0").srcCtx("DeepBenchLSTM256.scala:86:25:cBuf") } // x3602 = SRAMNew(ArrayBuffer(Const(256)))
    isAccum(x3602_d0_b0) = false
    bufferDepthOf(x3602_d0_b0) = 1
    staticDimsOf(x3602_d0_b0) = List(256)
    val x3603_d0_b0 = withCtrl(x3904) { SRAM(size=256, banking=Strided(banks=16, stride=1)).name("x3603_d0_b0").srcCtx("DeepBenchLSTM256.scala:87:25:hBuf") } // x3603 = SRAMNew(ArrayBuffer(Const(256)))
    isAccum(x3603_d0_b0) = false
    bufferDepthOf(x3603_d0_b0) = 1
    staticDimsOf(x3603_d0_b0) = List(256)
    val x3603_d1_b0 = withCtrl(x3904) { SRAM(size=256, banking=Strided(banks=16, stride=1)).name("x3603_d1_b0").srcCtx("DeepBenchLSTM256.scala:87:25:hBuf") } // x3603 = SRAMNew(ArrayBuffer(Const(256)))
    isAccum(x3603_d1_b0) = false
    bufferDepthOf(x3603_d1_b0) = 1
    staticDimsOf(x3603_d1_b0) = List(256)
    val x3604 = withCtrl(x3904) { Counter(min=Const(0), max=Const(150), step=Const(1), par=1).name("x3604").srcCtx("DeepBenchLSTM256.scala:90:34") } // CounterNew(Const(0),Const(150),Const(1),Const(1))
    val x3605 = withCtrl(x3904) { CounterChain(List(x3604)).name("x3605").srcCtx("DeepBenchLSTM256.scala:90:40") } // CounterChainNew(List(x3604))
    val x3881 = withCtrl(x3904) { LoopController(style=SeqPipe, level=OuterControl, cchain=x3605).name("x3881").srcCtx("DeepBenchLSTM256.scala:90:40") } // UnrolledForeach(List(Const(true)),x3605,Block(Const(())),List(List(b1968)),List(List(b1969)))
    val b1968 = withCtrl(x3881) { CounterIter(x3604, Some(0)).name("b1968") } // b1968
    val b1969 = withCtrl(x3881) { Const(true).name("b1969") } // b1969
    val x3606_d0_b0 = withCtrl(x3881) { SRAM(size=1024, banking=Strided(banks=16, stride=1)).name("x3606_d0_b0").srcCtx("DeepBenchLSTM256.scala:91:32:reduceMem") } // x3606 = SRAMNew(ArrayBuffer(Const(4), Const(256)))
    isAccum(x3606_d0_b0) = false
    bufferDepthOf(x3606_d0_b0) = 1
    staticDimsOf(x3606_d0_b0) = List(4, 256)
    val x3606_d1_b0 = withCtrl(x3881) { SRAM(size=1024, banking=Strided(banks=16, stride=1)).name("x3606_d1_b0").srcCtx("DeepBenchLSTM256.scala:91:32:reduceMem") } // x3606 = SRAMNew(ArrayBuffer(Const(4), Const(256)))
    isAccum(x3606_d1_b0) = true
    bufferDepthOf(x3606_d1_b0) = 1
    staticDimsOf(x3606_d1_b0) = List(4, 256)
    val x3607_d0_b0 = withCtrl(x3881) { SRAM(size=1024, banking=Strided(banks=16, stride=1)).name("x3607_d0_b0").srcCtx("DeepBenchLSTM256.scala:92:30:foldMem") } // x3607 = SRAMNew(ArrayBuffer(Const(4), Const(256)))
    isAccum(x3607_d0_b0) = false
    bufferDepthOf(x3607_d0_b0) = 1
    staticDimsOf(x3607_d0_b0) = List(4, 256)
    val x3608_d0 = withCtrl(x3881) { Reg(init=Some(false)).name("x3608_d0").srcCtx("DeepBenchLSTM256.scala:90:40") } // x3608 = RegNew(Const(false))
    isAccum(x3608_d0) = false
    bufferDepthOf(x3608_d0) = 1
    val x3608_d1 = withCtrl(x3881) { Reg(init=Some(false)).name("x3608_d1").srcCtx("DeepBenchLSTM256.scala:90:40") } // x3608 = RegNew(Const(false))
    isAccum(x3608_d1) = false
    bufferDepthOf(x3608_d1) = 1
    val x3608_d2 = withCtrl(x3881) { Reg(init=Some(false)).name("x3608_d2").srcCtx("DeepBenchLSTM256.scala:90:40") } // x3608 = RegNew(Const(false))
    isAccum(x3608_d2) = false
    bufferDepthOf(x3608_d2) = 1
    val x3608_d3 = withCtrl(x3881) { Reg(init=Some(false)).name("x3608_d3").srcCtx("DeepBenchLSTM256.scala:90:40") } // x3608 = RegNew(Const(false))
    isAccum(x3608_d3) = false
    bufferDepthOf(x3608_d3) = 1
    val x3608_d4 = withCtrl(x3881) { Reg(init=Some(false)).name("x3608_d4").srcCtx("DeepBenchLSTM256.scala:90:40") } // x3608 = RegNew(Const(false))
    isAccum(x3608_d4) = false
    bufferDepthOf(x3608_d4) = 1
    val x3609 = withCtrl(x3881) { Reg(init=Some(false)).name("x3609").srcCtx("DeepBenchLSTM256.scala:90:40") } // x3609 = RegNew(Const(false))
    isAccum(x3609) = false
    bufferDepthOf(x3609) = 1
    val x3610 = withCtrl(x3881) { Reg(init=Some(false)).name("x3610").srcCtx("DeepBenchLSTM256.scala:90:40") } // x3610 = RegNew(Const(false))
    isAccum(x3610) = false
    bufferDepthOf(x3610) = 1
    val x3611 = withCtrl(x3881) { Reg(init=Some(0)).name("x3611").srcCtx("DeepBenchLSTM256.scala:90:40") } // x3611 = RegNew(Const(0))
    isAccum(x3611) = false
    bufferDepthOf(x3611) = 1
    val x3623 = withCtrl(x3881) { UnitController(style=SeqPipe, level=InnerControl).name("x3623").srcCtx("DeepBenchLSTM256.scala:90:40") } // UnitPipe(List(b1969),Block(Const(())))
    val x3612 = withCtrl(x3623) { OpDef(op=FixEql, inputs=List(b1968, Const(0))).name("x3612").srcCtx("DeepBenchLSTM256.scala:93:33:isInitStep") } // FixEql(b1968,Const(0))
    val x3613 = withCtrl(x3623) { OpDef(op=FixLt, inputs=List(b1968, Const(64))).name("x3613").srcCtx("VecMatMultBiasAdd.scala:154:28:isFstRange") } // FixLt(b1968,Const(64))
    val x3614 = withCtrl(x3623) { OpDef(op=FixLt, inputs=List(Const(64), b1968)).name("x3614").srcCtx("VecMatMultBiasAdd.scala:155:28:isLstRange") } // FixLt(Const(64),b1968)
    val x3615 = withCtrl(x3623) { OpDef(op=FixSub, inputs=List(b1968, Const(64))).name("x3615").srcCtx("VecMatMultBiasAdd.scala:157:24:midIdx") } // FixSub(b1968,Const(64))
    val x3616 = withCtrl(x3623) { OpDef(op=FixSub, inputs=List(b1968, Const(128))).name("x3616").srcCtx("VecMatMultBiasAdd.scala:158:24:lstIdx") } // FixSub(b1968,Const(128))
    val x3617 = withCtrl(x3623) { OpDef(op=MuxOp, inputs=List(x3614, x3616, x3615)).name("x3617").srcCtx("VecMatMultBiasAdd.scala:160:30") } // Mux(x3614,x3616,x3615)
    val x3618 = withCtrl(x3623) { OpDef(op=MuxOp, inputs=List(x3613, b1968, x3617)).name("x3618").srcCtx("VecMatMultBiasAdd.scala:159:25:iTileStep") } // Mux(x3613,b1968,x3617)
    val x3619_x3608_d0 = withCtrl(x3623) { WriteMem(x3608_d0, x3612).name("x3619_x3608_d0").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegWrite(x3608,x3612,b1969)
    val x3619_x3608_d1 = withCtrl(x3623) { WriteMem(x3608_d1, x3612).name("x3619_x3608_d1").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegWrite(x3608,x3612,b1969)
    val x3619_x3608_d2 = withCtrl(x3623) { WriteMem(x3608_d2, x3612).name("x3619_x3608_d2").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegWrite(x3608,x3612,b1969)
    val x3619_x3608_d3 = withCtrl(x3623) { WriteMem(x3608_d3, x3612).name("x3619_x3608_d3").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegWrite(x3608,x3612,b1969)
    val x3619_x3608_d4 = withCtrl(x3623) { WriteMem(x3608_d4, x3612).name("x3619_x3608_d4").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegWrite(x3608,x3612,b1969)
    val x3620_x3609 = withCtrl(x3623) { WriteMem(x3609, x3613).name("x3620_x3609").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegWrite(x3609,x3613,b1969)
    val x3621_x3610 = withCtrl(x3623) { WriteMem(x3610, x3614).name("x3621_x3610").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegWrite(x3610,x3614,b1969)
    val x3622_x3611 = withCtrl(x3623) { WriteMem(x3611, x3618).name("x3622_x3611").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegWrite(x3611,x3618,b1969)
    val x3624 = withCtrl(x3881) { Counter(min=Const(0), max=Const(256), step=Const(1), par=4).name("x3624").srcCtx("VecMatMultBiasAdd.scala:165:59") } // CounterNew(Const(0),Const(256),Const(1),Const(4))
    val x3625 = withCtrl(x3881) { CounterChain(List(x3624)).name("x3625").srcCtx("VecMatMultBiasAdd.scala:179:6") } // CounterChainNew(List(x3624))
    val x3749 = withCtrl(x3881) { LoopController(style=MetaPipe, level=OuterControl, cchain=x3625).name("x3749").srcCtx("VecMatMultBiasAdd.scala:179:6") } // UnrolledReduce(List(b1969),x3625,x3606,Block((x3606) => Const(())),List(List(b1993, b1994, b1995, b1996)),List(List(b1997, b1998, b1999, b2000)))
    val b1993 = withCtrl(x3749) { CounterIter(x3624, Some(0)).name("b1993") } // b1993
    val b1997 = withCtrl(x3749) { Const(true).name("b1997") } // b1997
    val b1994 = withCtrl(x3749) { CounterIter(x3624, Some(1)).name("b1994") } // b1994
    val b1998 = withCtrl(x3749) { Const(true).name("b1998") } // b1998
    val b1995 = withCtrl(x3749) { CounterIter(x3624, Some(2)).name("b1995") } // b1995
    val b1999 = withCtrl(x3749) { Const(true).name("b1999") } // b1999
    val b1996 = withCtrl(x3749) { CounterIter(x3624, Some(3)).name("b1996") } // b1996
    val b2000 = withCtrl(x3749) { Const(true).name("b2000") } // b2000
    val x3626_d0_b0 = withCtrl(x3749) { SRAM(size=1024, banking=Strided(banks=16, stride=1)).name("x3626_d0_b0").srcCtx("VecMatMultBiasAdd.scala:166:23:re") } // x3626 = SRAMNew(ArrayBuffer(Const(4), Const(256)))
    isAccum(x3626_d0_b0) = false
    bufferDepthOf(x3626_d0_b0) = 2
    staticDimsOf(x3626_d0_b0) = List(4, 256)
    val x3627_d0_b0 = withCtrl(x3749) { SRAM(size=1024, banking=Strided(banks=16, stride=1)).name("x3627_d0_b0").srcCtx("VecMatMultBiasAdd.scala:166:23:re") } // x3627 = SRAMNew(ArrayBuffer(Const(4), Const(256)))
    isAccum(x3627_d0_b0) = false
    bufferDepthOf(x3627_d0_b0) = 2
    staticDimsOf(x3627_d0_b0) = List(4, 256)
    val x3628_d0_b0 = withCtrl(x3749) { SRAM(size=1024, banking=Strided(banks=16, stride=1)).name("x3628_d0_b0").srcCtx("VecMatMultBiasAdd.scala:166:23:re") } // x3628 = SRAMNew(ArrayBuffer(Const(4), Const(256)))
    isAccum(x3628_d0_b0) = false
    bufferDepthOf(x3628_d0_b0) = 2
    staticDimsOf(x3628_d0_b0) = List(4, 256)
    val x3629_d0_b0 = withCtrl(x3749) { SRAM(size=1024, banking=Strided(banks=16, stride=1)).name("x3629_d0_b0").srcCtx("VecMatMultBiasAdd.scala:166:23:re") } // x3629 = SRAMNew(ArrayBuffer(Const(4), Const(256)))
    isAccum(x3629_d0_b0) = false
    bufferDepthOf(x3629_d0_b0) = 2
    staticDimsOf(x3629_d0_b0) = List(4, 256)
    val x3630 = withCtrl(x3749) { Reg(init=Some(0.0)).name("x3630").srcCtx("VecMatMultBiasAdd.scala:179:6") } // x3630 = RegNew(Const(0))
    isAccum(x3630) = false
    bufferDepthOf(x3630) = 2
    val x3631 = withCtrl(x3749) { Reg(init=Some(0.0)).name("x3631").srcCtx("VecMatMultBiasAdd.scala:179:6") } // x3631 = RegNew(Const(0))
    isAccum(x3631) = false
    bufferDepthOf(x3631) = 2
    val x3632 = withCtrl(x3749) { Reg(init=Some(0.0)).name("x3632").srcCtx("VecMatMultBiasAdd.scala:179:6") } // x3632 = RegNew(Const(0))
    isAccum(x3632) = false
    bufferDepthOf(x3632) = 2
    val x3633 = withCtrl(x3749) { Reg(init=Some(0.0)).name("x3633").srcCtx("VecMatMultBiasAdd.scala:179:6") } // x3633 = RegNew(Const(0))
    isAccum(x3633) = false
    bufferDepthOf(x3633) = 2
    val x3658 = withCtrl(x3749) { UnitController(style=ForkJoin, level=OuterControl).name("x3658").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b1969),Block(Const(())))
    val x3639 = withCtrl(x3658) { UnitController(style=SeqPipe, level=InnerControl).name("x3639").srcCtx("VecMatMultBiasAdd.scala:179:6") } // UnitPipe(List(b1997, b1969),Block(Const(())))
    val x3634 = withCtrl(x3639) { OpDef(op=BitAnd, inputs=List(b1997, b1969)).name("x3634").srcCtx("UnrollingBase.scala:28:66") } // And(b1997,b1969)
    val x3635 = withCtrl(x3639) { LoadBanks(List(x3603_d1_b0), List(b1993)).name("x3635").srcCtx("VecMatMultBiasAdd.scala:168:34") } // SRAMLoad(x3603,ArrayBuffer(Const(256)),List(b1993),Const(0),x3634)
    val x3636 = withCtrl(x3639) { ReadMem(x3608_d1).name("x3636").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegRead(x3608)
    val x3637 = withCtrl(x3639) { OpDef(op=MuxOp, inputs=List(x3636, Const(0.0), x3635)).name("x3637").srcCtx("VecMatMultBiasAdd.scala:167:21:hEle") } // Mux(x3636,Const(0),x3635)
    val x3638_x3630 = withCtrl(x3639) { WriteMem(x3630, x3637).name("x3638_x3630").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegWrite(x3630,x3637,x3634)
    val x3645 = withCtrl(x3658) { UnitController(style=SeqPipe, level=InnerControl).name("x3645").srcCtx("VecMatMultBiasAdd.scala:179:6") } // UnitPipe(List(b1998, b1969),Block(Const(())))
    val x3640 = withCtrl(x3645) { OpDef(op=BitAnd, inputs=List(b1998, b1969)).name("x3640").srcCtx("UnrollingBase.scala:28:66") } // And(b1998,b1969)
    val x3641 = withCtrl(x3645) { LoadBanks(List(x3603_d1_b0), List(b1994)).name("x3641").srcCtx("VecMatMultBiasAdd.scala:168:34") } // SRAMLoad(x3603,ArrayBuffer(Const(256)),List(b1994),Const(0),x3640)
    val x3642 = withCtrl(x3645) { ReadMem(x3608_d2).name("x3642").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegRead(x3608)
    val x3643 = withCtrl(x3645) { OpDef(op=MuxOp, inputs=List(x3642, Const(0.0), x3641)).name("x3643").srcCtx("VecMatMultBiasAdd.scala:167:21:hEle") } // Mux(x3642,Const(0),x3641)
    val x3644_x3631 = withCtrl(x3645) { WriteMem(x3631, x3643).name("x3644_x3631").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegWrite(x3631,x3643,x3640)
    val x3651 = withCtrl(x3658) { UnitController(style=SeqPipe, level=InnerControl).name("x3651").srcCtx("VecMatMultBiasAdd.scala:179:6") } // UnitPipe(List(b1999, b1969),Block(Const(())))
    val x3646 = withCtrl(x3651) { OpDef(op=BitAnd, inputs=List(b1999, b1969)).name("x3646").srcCtx("UnrollingBase.scala:28:66") } // And(b1999,b1969)
    val x3647 = withCtrl(x3651) { LoadBanks(List(x3603_d1_b0), List(b1995)).name("x3647").srcCtx("VecMatMultBiasAdd.scala:168:34") } // SRAMLoad(x3603,ArrayBuffer(Const(256)),List(b1995),Const(0),x3646)
    val x3648 = withCtrl(x3651) { ReadMem(x3608_d3).name("x3648").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegRead(x3608)
    val x3649 = withCtrl(x3651) { OpDef(op=MuxOp, inputs=List(x3648, Const(0.0), x3647)).name("x3649").srcCtx("VecMatMultBiasAdd.scala:167:21:hEle") } // Mux(x3648,Const(0),x3647)
    val x3650_x3632 = withCtrl(x3651) { WriteMem(x3632, x3649).name("x3650_x3632").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegWrite(x3632,x3649,x3646)
    val x3657 = withCtrl(x3658) { UnitController(style=SeqPipe, level=InnerControl).name("x3657").srcCtx("VecMatMultBiasAdd.scala:179:6") } // UnitPipe(List(b2000, b1969),Block(Const(())))
    val x3652 = withCtrl(x3657) { OpDef(op=BitAnd, inputs=List(b2000, b1969)).name("x3652").srcCtx("UnrollingBase.scala:28:66") } // And(b2000,b1969)
    val x3653 = withCtrl(x3657) { LoadBanks(List(x3603_d1_b0), List(b1996)).name("x3653").srcCtx("VecMatMultBiasAdd.scala:168:34") } // SRAMLoad(x3603,ArrayBuffer(Const(256)),List(b1996),Const(0),x3652)
    val x3654 = withCtrl(x3657) { ReadMem(x3608_d4).name("x3654").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegRead(x3608)
    val x3655 = withCtrl(x3657) { OpDef(op=MuxOp, inputs=List(x3654, Const(0.0), x3653)).name("x3655").srcCtx("VecMatMultBiasAdd.scala:167:21:hEle") } // Mux(x3654,Const(0),x3653)
    val x3656_x3633 = withCtrl(x3657) { WriteMem(x3633, x3655).name("x3656_x3633").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegWrite(x3633,x3655,x3652)
    val x3711 = withCtrl(x3749) { UnitController(style=ForkJoin, level=OuterControl).name("x3711").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b1969),Block(Const(())))
    val x3659 = withCtrl(x3711) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x3659").srcCtx("VecMatMultBiasAdd.scala:171:28") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x3660 = withCtrl(x3711) { CounterChain(List(x3659)).name("x3660").srcCtx("VecMatMultBiasAdd.scala:171:41") } // CounterChainNew(List(x3659))
    val x3671 = withCtrl(x3711) { LoopController(style=MetaPipe, level=OuterControl, cchain=x3660).name("x3671").srcCtx("VecMatMultBiasAdd.scala:171:41") } // UnrolledForeach(List(b1997, b1969),x3660,Block(Const(())),List(List(b2042)),List(List(b2043)))
    val b2042 = withCtrl(x3671) { CounterIter(x3659, Some(0)).name("b2042") } // b2042
    val b2043 = withCtrl(x3671) { Const(true).name("b2043") } // b2043
    val x3661 = withCtrl(x3671) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x3661").srcCtx("VecMatMultBiasAdd.scala:172:36") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x3662 = withCtrl(x3671) { CounterChain(List(x3661)).name("x3662").srcCtx("VecMatMultBiasAdd.scala:172:50") } // CounterChainNew(List(x3661))
    val x3670 = withCtrl(x3671) { LoopController(style=InnerPipe, level=InnerControl, cchain=x3662).name("x3670").srcCtx("VecMatMultBiasAdd.scala:172:50") } // UnrolledForeach(List(b2043, b1997, b1969),x3662,Block(Const(())),List(List(b2046)),List(List(b2047)))
    val b2046 = withCtrl(x3670) { CounterIter(x3661, None).name("b2046") } // b2046
    val b2047 = withCtrl(x3670) { Const(true).name("b2047") } // b2047
    val x3663 = withCtrl(x3670) { OpDef(op=BitAnd, inputs=List(b2047, b2043)).name("x3663").srcCtx("UnrollingBase.scala:28:66") } // And(b2047,b2043)
    val x3664 = withCtrl(x3670) { OpDef(op=BitAnd, inputs=List(b1997, b1969)).name("x3664").srcCtx("UnrollingBase.scala:28:66") } // And(b1997,b1969)
    val x3665 = withCtrl(x3670) { OpDef(op=BitAnd, inputs=List(x3663, x3664)).name("x3665").srcCtx("UnrollingBase.scala:28:66") } // And(x3663,x3664)
    val x3666 = withCtrl(x3670) { LoadBanks(List(x3598_d0_b0), List(b1993, b2042, b2046)).name("x3666").srcCtx("VecMatMultBiasAdd.scala:173:25:whEle") } // LUTLoad(x3598,List(b1993, b2042, b2046),x3665)
    val x3667 = withCtrl(x3670) { ReadMem(x3630).name("x3667").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegRead(x3630)
    val x3668 = withCtrl(x3670) { OpDef(op=FixMul, inputs=List(x3666, x3667)).name("x3668").srcCtx("VecMatMultBiasAdd.scala:174:42") } // FixMul(x3666,x3667)
    val x3669 = withCtrl(x3670) { StoreBanks(List(List(x3626_d0_b0)), List(b2042, b2046), x3668).name("x3669").srcCtx("VecMatMultBiasAdd.scala:174:34") } // ParSRAMStore(x3626,List(List(b2042, b2046)),List(x3668),List(x3665))
    val x3672 = withCtrl(x3711) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x3672").srcCtx("VecMatMultBiasAdd.scala:171:28") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x3673 = withCtrl(x3711) { CounterChain(List(x3672)).name("x3673").srcCtx("VecMatMultBiasAdd.scala:171:41") } // CounterChainNew(List(x3672))
    val x3684 = withCtrl(x3711) { LoopController(style=MetaPipe, level=OuterControl, cchain=x3673).name("x3684").srcCtx("VecMatMultBiasAdd.scala:171:41") } // UnrolledForeach(List(b1998, b1969),x3673,Block(Const(())),List(List(b2057)),List(List(b2058)))
    val b2057 = withCtrl(x3684) { CounterIter(x3672, Some(0)).name("b2057") } // b2057
    val b2058 = withCtrl(x3684) { Const(true).name("b2058") } // b2058
    val x3674 = withCtrl(x3684) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x3674").srcCtx("VecMatMultBiasAdd.scala:172:36") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x3675 = withCtrl(x3684) { CounterChain(List(x3674)).name("x3675").srcCtx("VecMatMultBiasAdd.scala:172:50") } // CounterChainNew(List(x3674))
    val x3683 = withCtrl(x3684) { LoopController(style=InnerPipe, level=InnerControl, cchain=x3675).name("x3683").srcCtx("VecMatMultBiasAdd.scala:172:50") } // UnrolledForeach(List(b2058, b1998, b1969),x3675,Block(Const(())),List(List(b2061)),List(List(b2062)))
    val b2061 = withCtrl(x3683) { CounterIter(x3674, None).name("b2061") } // b2061
    val b2062 = withCtrl(x3683) { Const(true).name("b2062") } // b2062
    val x3676 = withCtrl(x3683) { OpDef(op=BitAnd, inputs=List(b2062, b2058)).name("x3676").srcCtx("UnrollingBase.scala:28:66") } // And(b2062,b2058)
    val x3677 = withCtrl(x3683) { OpDef(op=BitAnd, inputs=List(b1998, b1969)).name("x3677").srcCtx("UnrollingBase.scala:28:66") } // And(b1998,b1969)
    val x3678 = withCtrl(x3683) { OpDef(op=BitAnd, inputs=List(x3676, x3677)).name("x3678").srcCtx("UnrollingBase.scala:28:66") } // And(x3676,x3677)
    val x3679 = withCtrl(x3683) { LoadBanks(List(x3598_d0_b1), List(b1994, b2057, b2061)).name("x3679").srcCtx("VecMatMultBiasAdd.scala:173:25:whEle") } // LUTLoad(x3598,List(b1994, b2057, b2061),x3678)
    val x3680 = withCtrl(x3683) { ReadMem(x3631).name("x3680").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegRead(x3631)
    val x3681 = withCtrl(x3683) { OpDef(op=FixMul, inputs=List(x3679, x3680)).name("x3681").srcCtx("VecMatMultBiasAdd.scala:174:42") } // FixMul(x3679,x3680)
    val x3682 = withCtrl(x3683) { StoreBanks(List(List(x3627_d0_b0)), List(b2057, b2061), x3681).name("x3682").srcCtx("VecMatMultBiasAdd.scala:174:34") } // ParSRAMStore(x3627,List(List(b2057, b2061)),List(x3681),List(x3678))
    val x3685 = withCtrl(x3711) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x3685").srcCtx("VecMatMultBiasAdd.scala:171:28") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x3686 = withCtrl(x3711) { CounterChain(List(x3685)).name("x3686").srcCtx("VecMatMultBiasAdd.scala:171:41") } // CounterChainNew(List(x3685))
    val x3697 = withCtrl(x3711) { LoopController(style=MetaPipe, level=OuterControl, cchain=x3686).name("x3697").srcCtx("VecMatMultBiasAdd.scala:171:41") } // UnrolledForeach(List(b1999, b1969),x3686,Block(Const(())),List(List(b2072)),List(List(b2073)))
    val b2072 = withCtrl(x3697) { CounterIter(x3685, Some(0)).name("b2072") } // b2072
    val b2073 = withCtrl(x3697) { Const(true).name("b2073") } // b2073
    val x3687 = withCtrl(x3697) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x3687").srcCtx("VecMatMultBiasAdd.scala:172:36") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x3688 = withCtrl(x3697) { CounterChain(List(x3687)).name("x3688").srcCtx("VecMatMultBiasAdd.scala:172:50") } // CounterChainNew(List(x3687))
    val x3696 = withCtrl(x3697) { LoopController(style=InnerPipe, level=InnerControl, cchain=x3688).name("x3696").srcCtx("VecMatMultBiasAdd.scala:172:50") } // UnrolledForeach(List(b2073, b1999, b1969),x3688,Block(Const(())),List(List(b2076)),List(List(b2077)))
    val b2076 = withCtrl(x3696) { CounterIter(x3687, None).name("b2076") } // b2076
    val b2077 = withCtrl(x3696) { Const(true).name("b2077") } // b2077
    val x3689 = withCtrl(x3696) { OpDef(op=BitAnd, inputs=List(b2077, b2073)).name("x3689").srcCtx("UnrollingBase.scala:28:66") } // And(b2077,b2073)
    val x3690 = withCtrl(x3696) { OpDef(op=BitAnd, inputs=List(b1999, b1969)).name("x3690").srcCtx("UnrollingBase.scala:28:66") } // And(b1999,b1969)
    val x3691 = withCtrl(x3696) { OpDef(op=BitAnd, inputs=List(x3689, x3690)).name("x3691").srcCtx("UnrollingBase.scala:28:66") } // And(x3689,x3690)
    val x3692 = withCtrl(x3696) { LoadBanks(List(x3598_d0_b2), List(b1995, b2072, b2076)).name("x3692").srcCtx("VecMatMultBiasAdd.scala:173:25:whEle") } // LUTLoad(x3598,List(b1995, b2072, b2076),x3691)
    val x3693 = withCtrl(x3696) { ReadMem(x3632).name("x3693").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegRead(x3632)
    val x3694 = withCtrl(x3696) { OpDef(op=FixMul, inputs=List(x3692, x3693)).name("x3694").srcCtx("VecMatMultBiasAdd.scala:174:42") } // FixMul(x3692,x3693)
    val x3695 = withCtrl(x3696) { StoreBanks(List(List(x3628_d0_b0)), List(b2072, b2076), x3694).name("x3695").srcCtx("VecMatMultBiasAdd.scala:174:34") } // ParSRAMStore(x3628,List(List(b2072, b2076)),List(x3694),List(x3691))
    val x3698 = withCtrl(x3711) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x3698").srcCtx("VecMatMultBiasAdd.scala:171:28") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x3699 = withCtrl(x3711) { CounterChain(List(x3698)).name("x3699").srcCtx("VecMatMultBiasAdd.scala:171:41") } // CounterChainNew(List(x3698))
    val x3710 = withCtrl(x3711) { LoopController(style=MetaPipe, level=OuterControl, cchain=x3699).name("x3710").srcCtx("VecMatMultBiasAdd.scala:171:41") } // UnrolledForeach(List(b2000, b1969),x3699,Block(Const(())),List(List(b2087)),List(List(b2088)))
    val b2087 = withCtrl(x3710) { CounterIter(x3698, Some(0)).name("b2087") } // b2087
    val b2088 = withCtrl(x3710) { Const(true).name("b2088") } // b2088
    val x3700 = withCtrl(x3710) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x3700").srcCtx("VecMatMultBiasAdd.scala:172:36") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x3701 = withCtrl(x3710) { CounterChain(List(x3700)).name("x3701").srcCtx("VecMatMultBiasAdd.scala:172:50") } // CounterChainNew(List(x3700))
    val x3709 = withCtrl(x3710) { LoopController(style=InnerPipe, level=InnerControl, cchain=x3701).name("x3709").srcCtx("VecMatMultBiasAdd.scala:172:50") } // UnrolledForeach(List(b2088, b2000, b1969),x3701,Block(Const(())),List(List(b2091)),List(List(b2092)))
    val b2091 = withCtrl(x3709) { CounterIter(x3700, None).name("b2091") } // b2091
    val b2092 = withCtrl(x3709) { Const(true).name("b2092") } // b2092
    val x3702 = withCtrl(x3709) { OpDef(op=BitAnd, inputs=List(b2092, b2088)).name("x3702").srcCtx("UnrollingBase.scala:28:66") } // And(b2092,b2088)
    val x3703 = withCtrl(x3709) { OpDef(op=BitAnd, inputs=List(b2000, b1969)).name("x3703").srcCtx("UnrollingBase.scala:28:66") } // And(b2000,b1969)
    val x3704 = withCtrl(x3709) { OpDef(op=BitAnd, inputs=List(x3702, x3703)).name("x3704").srcCtx("UnrollingBase.scala:28:66") } // And(x3702,x3703)
    val x3705 = withCtrl(x3709) { LoadBanks(List(x3598_d0_b3), List(b1996, b2087, b2091)).name("x3705").srcCtx("VecMatMultBiasAdd.scala:173:25:whEle") } // LUTLoad(x3598,List(b1996, b2087, b2091),x3704)
    val x3706 = withCtrl(x3709) { ReadMem(x3633).name("x3706").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegRead(x3633)
    val x3707 = withCtrl(x3709) { OpDef(op=FixMul, inputs=List(x3705, x3706)).name("x3707").srcCtx("VecMatMultBiasAdd.scala:174:42") } // FixMul(x3705,x3706)
    val x3708 = withCtrl(x3709) { StoreBanks(List(List(x3629_d0_b0)), List(b2087, b2091), x3707).name("x3708").srcCtx("VecMatMultBiasAdd.scala:174:34") } // ParSRAMStore(x3629,List(List(b2087, b2091)),List(x3707),List(x3704))
    val x3712 = withCtrl(x3749) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x3712").srcCtx("VecMatMultBiasAdd.scala:179:6") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x3713 = withCtrl(x3749) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x3713").srcCtx("VecMatMultBiasAdd.scala:179:6") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x3714 = withCtrl(x3749) { CounterChain(List(x3713,x3712)).name("x3714").srcCtx("VecMatMultBiasAdd.scala:179:6") } // CounterChainNew(ArrayBuffer(x3713, x3712))
    val x3748 = withCtrl(x3749) { LoopController(style=InnerPipe, level=InnerControl, cchain=x3714).name("x3748").srcCtx("VecMatMultBiasAdd.scala:179:6") } // UnrolledForeach(List(),x3714,Block(Const(())),ArrayBuffer(List(b2103), List(b2104)),ArrayBuffer(List(b2105), List(b2106)))
    val b2103 = withCtrl(x3748) { CounterIter(x3713, Some(0)).name("b2103") } // b2103
    val b2105 = withCtrl(x3748) { Const(true).name("b2105") } // b2105
    val b2104 = withCtrl(x3748) { CounterIter(x3712, None).name("b2104") } // b2104
    val b2106 = withCtrl(x3748) { Const(true).name("b2106") } // b2106
    val x3715 = withCtrl(x3748) { OpDef(op=BitAnd, inputs=List(b2105, b2106)).name("x3715").srcCtx("UnrollingBase.scala:28:66") } // And(b2105,b2106)
    val x3716 = withCtrl(x3748) { OpDef(op=BitAnd, inputs=List(x3715, b1969)).name("x3716").srcCtx("UnrollingBase.scala:28:66") } // And(x3715,b1969)
    val x3717 = withCtrl(x3748) { LoadBanks(List(x3626_d0_b0), List(b2103, b2104)).name("x3717").srcCtx("VecMatMultBiasAdd.scala:179:6") } // ParSRAMLoad(x3626,List(ArrayBuffer(b2103, b2104)),List(x3716))
    val x3718 = withCtrl(x3748) { x3717 } // VectorApply(x3717,0)
    val x3719 = withCtrl(x3748) { LoadBanks(List(x3627_d0_b0), List(b2103, b2104)).name("x3719").srcCtx("VecMatMultBiasAdd.scala:179:6") } // ParSRAMLoad(x3627,List(ArrayBuffer(b2103, b2104)),List(x3716))
    val x3720 = withCtrl(x3748) { x3719 } // VectorApply(x3719,0)
    val x3721 = withCtrl(x3748) { LoadBanks(List(x3628_d0_b0), List(b2103, b2104)).name("x3721").srcCtx("VecMatMultBiasAdd.scala:179:6") } // ParSRAMLoad(x3628,List(ArrayBuffer(b2103, b2104)),List(x3716))
    val x3722 = withCtrl(x3748) { x3721 } // VectorApply(x3721,0)
    val x3723 = withCtrl(x3748) { LoadBanks(List(x3629_d0_b0), List(b2103, b2104)).name("x3723").srcCtx("VecMatMultBiasAdd.scala:179:6") } // ParSRAMLoad(x3629,List(ArrayBuffer(b2103, b2104)),List(x3716))
    val x3724 = withCtrl(x3748) { x3723 } // VectorApply(x3723,0)
    val x3725 = withCtrl(x3748) { LoadBanks(List(x3606_d1_b0), List(b2103, b2104)).name("x3725").srcCtx("VecMatMultBiasAdd.scala:179:6") } // ParSRAMLoad(x3606,List(ArrayBuffer(b2103, b2104)),List(x3716))
    val x3726 = withCtrl(x3748) { x3725 } // VectorApply(x3725,0)
    val x3727 = withCtrl(x3748) { OpDef(op=BitAnd, inputs=List(b1997, b1969)).name("x3727").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(b1997,b1969)
    val x3728 = withCtrl(x3748) { OpDef(op=BitAnd, inputs=List(b1998, b1969)).name("x3728").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(b1998,b1969)
    val x3729 = withCtrl(x3748) { OpDef(op=BitAnd, inputs=List(b1999, b1969)).name("x3729").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(b1999,b1969)
    val x3730 = withCtrl(x3748) { OpDef(op=BitAnd, inputs=List(b2000, b1969)).name("x3730").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(b2000,b1969)
    val x3731 = withCtrl(x3748) { OpDef(op=BitAnd, inputs=List(x3727, x3716)).name("x3731").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(x3727,x3716)
    val x3732 = withCtrl(x3748) { OpDef(op=BitAnd, inputs=List(x3728, x3716)).name("x3732").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(x3728,x3716)
    val x3733 = withCtrl(x3748) { OpDef(op=BitAnd, inputs=List(x3729, x3716)).name("x3733").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(x3729,x3716)
    val x3734 = withCtrl(x3748) { OpDef(op=BitAnd, inputs=List(x3730, x3716)).name("x3734").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(x3730,x3716)
    val x3735 = withCtrl(x3748) { OpDef(op=FixAdd, inputs=List(x3718, x3720)).name("x3735").srcCtx("VecMatMultBiasAdd.scala:179:8") } // FixAdd(x3718,x3720)
    val x3736 = withCtrl(x3748) { OpDef(op=MuxOp, inputs=List(x3732, x3735, x3718)).name("x3736").srcCtx("VecMatMultBiasAdd.scala:179:6") } // Mux(x3732,x3735,x3718)
    val x3737 = withCtrl(x3748) { OpDef(op=BitOr, inputs=List(x3731, x3732)).name("x3737").srcCtx("VecMatMultBiasAdd.scala:179:6") } // Or(x3731,x3732)
    val x3738 = withCtrl(x3748) { OpDef(op=FixAdd, inputs=List(x3722, x3724)).name("x3738").srcCtx("VecMatMultBiasAdd.scala:179:8") } // FixAdd(x3722,x3724)
    val x3739 = withCtrl(x3748) { OpDef(op=MuxOp, inputs=List(x3734, x3738, x3722)).name("x3739").srcCtx("VecMatMultBiasAdd.scala:179:6") } // Mux(x3734,x3738,x3722)
    val x3740 = withCtrl(x3748) { OpDef(op=BitOr, inputs=List(x3733, x3734)).name("x3740").srcCtx("VecMatMultBiasAdd.scala:179:6") } // Or(x3733,x3734)
    val x3741 = withCtrl(x3748) { OpDef(op=FixAdd, inputs=List(x3736, x3739)).name("x3741").srcCtx("VecMatMultBiasAdd.scala:179:8") } // FixAdd(x3736,x3739)
    val x3742 = withCtrl(x3748) { OpDef(op=MuxOp, inputs=List(x3740, x3741, x3736)).name("x3742").srcCtx("VecMatMultBiasAdd.scala:179:6") } // Mux(x3740,x3741,x3736)
    val x3743 = withCtrl(x3748) { OpDef(op=BitOr, inputs=List(x3737, x3740)).name("x3743").srcCtx("VecMatMultBiasAdd.scala:179:6") } // Or(x3737,x3740)
    val x3744 = withCtrl(x3748) { OpDef(op=FixEql, inputs=List(b1993, Const(0))).name("x3744").srcCtx("VecMatMultBiasAdd.scala:179:6") } // FixEql(b1993,Const(0))
    val x3745 = withCtrl(x3748) { OpDef(op=FixAdd, inputs=List(x3742, x3726)).name("x3745").srcCtx("VecMatMultBiasAdd.scala:179:8") } // FixAdd(x3742,x3726)
    val x3746 = withCtrl(x3748) { OpDef(op=MuxOp, inputs=List(x3744, x3742, x3745)).name("x3746").srcCtx("VecMatMultBiasAdd.scala:179:6") } // Mux(x3744,x3742,x3745)
    val x3747 = withCtrl(x3748) { StoreBanks(List(List(x3606_d0_b0), List(x3606_d1_b0)), List(b2103, b2104), x3746).name("x3747").srcCtx("VecMatMultBiasAdd.scala:179:6") } // ParSRAMStore(x3606,List(ArrayBuffer(b2103, b2104)),List(x3746),List(x3716))
    antiDepsOf(x3747)=List(x3725)
    val x3750 = withCtrl(x3881) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x3750").srcCtx("VecMatMultBiasAdd.scala:181:26") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x3751 = withCtrl(x3881) { CounterChain(List(x3750)).name("x3751").srcCtx("VecMatMultBiasAdd.scala:181:42") } // CounterChainNew(List(x3750))
    val x3769 = withCtrl(x3881) { LoopController(style=MetaPipe, level=OuterControl, cchain=x3751).name("x3769").srcCtx("VecMatMultBiasAdd.scala:181:42") } // UnrolledForeach(List(b1969),x3751,Block(Const(())),List(List(b2144)),List(List(b2145)))
    val b2144 = withCtrl(x3769) { CounterIter(x3750, Some(0)).name("b2144") } // b2144
    val b2145 = withCtrl(x3769) { Const(true).name("b2145") } // b2145
    val x3752 = withCtrl(x3769) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x3752").srcCtx("VecMatMultBiasAdd.scala:182:34") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x3753 = withCtrl(x3769) { CounterChain(List(x3752)).name("x3753").srcCtx("VecMatMultBiasAdd.scala:182:48") } // CounterChainNew(List(x3752))
    val x3768 = withCtrl(x3769) { LoopController(style=InnerPipe, level=InnerControl, cchain=x3753).name("x3768").srcCtx("VecMatMultBiasAdd.scala:182:48") } // UnrolledForeach(List(b2145, b1969),x3753,Block(Const(())),List(List(b2148)),List(List(b2149)))
    val b2148 = withCtrl(x3768) { CounterIter(x3752, None).name("b2148") } // b2148
    val b2149 = withCtrl(x3768) { Const(true).name("b2149") } // b2149
    val x3754 = withCtrl(x3768) { ReadMem(x3611).name("x3754").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegRead(x3611)
    val x3755 = withCtrl(x3768) { OpDef(op=BitAnd, inputs=List(b2149, b2145)).name("x3755").srcCtx("UnrollingBase.scala:28:66") } // And(b2149,b2145)
    val x3756 = withCtrl(x3768) { OpDef(op=BitAnd, inputs=List(x3755, b1969)).name("x3756").srcCtx("UnrollingBase.scala:28:66") } // And(x3755,b1969)
    val x3757 = withCtrl(x3768) { LoadBanks(List(x3599_d0_b0), List(x3754, b2144, b2148)).name("x3757").srcCtx("VecMatMultBiasAdd.scala:183:28:wxt0Ele") } // LUTLoad(x3599,List(x3754, b2144, b2148),x3756)
    val x3758 = withCtrl(x3768) { LoadBanks(List(x3600_d0_b0), List(x3754, b2144, b2148)).name("x3758").srcCtx("VecMatMultBiasAdd.scala:186:28:wxt1Ele") } // LUTLoad(x3600,List(x3754, b2144, b2148),x3756)
    val x3759 = withCtrl(x3768) { LoadBanks(List(x3601_d0_b0), List(x3754, b2144, b2148)).name("x3759").srcCtx("VecMatMultBiasAdd.scala:189:28:wxt2Ele") } // LUTLoad(x3601,List(x3754, b2144, b2148),x3756)
    val x3760 = withCtrl(x3768) { ReadMem(x3610).name("x3760").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegRead(x3610)
    val x3761 = withCtrl(x3768) { OpDef(op=MuxOp, inputs=List(x3760, x3759, x3758)).name("x3761").srcCtx("VecMatMultBiasAdd.scala:193:36") } // Mux(x3760,x3759,x3758)
    val x3762 = withCtrl(x3768) { ReadMem(x3609).name("x3762").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegRead(x3609)
    val x3763 = withCtrl(x3768) { OpDef(op=MuxOp, inputs=List(x3762, x3757, x3761)).name("x3763").srcCtx("VecMatMultBiasAdd.scala:192:26:wxtEle") } // Mux(x3762,x3757,x3761)
    val x3764 = withCtrl(x3768) { LoadBanks(List(x3606_d0_b0), List(b2144, b2148)).name("x3764").srcCtx("VecMatMultBiasAdd.scala:198:32") } // ParSRAMLoad(x3606,List(List(b2144, b2148)),List(x3756))
    val x3765 = withCtrl(x3768) { x3764 } // VectorApply(x3764,0)
    val x3766 = withCtrl(x3768) { OpDef(op=FixAdd, inputs=List(x3765, x3763)).name("x3766").srcCtx("VecMatMultBiasAdd.scala:198:53:foldVal") } // FixAdd(x3765,x3763)
    val x3767 = withCtrl(x3768) { StoreBanks(List(List(x3607_d0_b0)), List(b2144, b2148), x3766).name("x3767").srcCtx("VecMatMultBiasAdd.scala:199:37") } // ParSRAMStore(x3607,List(List(b2144, b2148)),List(x3766),List(x3756))
    val x3770 = withCtrl(x3881) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x3770").srcCtx("GateMetaPipe.scala:143:21") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x3771 = withCtrl(x3881) { CounterChain(List(x3770)).name("x3771").srcCtx("GateMetaPipe.scala:143:27") } // CounterChainNew(List(x3770))
    val x3880 = withCtrl(x3881) { LoopController(style=MetaPipe, level=OuterControl, cchain=x3771).name("x3880").srcCtx("GateMetaPipe.scala:143:27") } // UnrolledForeach(List(b1969),x3771,Block(Const(())),List(List(b2168)),List(List(b2169)))
    val b2168 = withCtrl(x3880) { CounterIter(x3770, Some(0)).name("b2168") } // b2168
    val b2169 = withCtrl(x3880) { Const(true).name("b2169") } // b2169
    val x3772 = withCtrl(x3880) { FIFO(size=64).name("x3772").srcCtx("GateMetaPipe.scala:144:25:sigQ") } // x3772 = FIFONew(Const(64))
    isAccum(x3772) = false
    bufferDepthOf(x3772) = 2
    val x3773 = withCtrl(x3880) { FIFO(size=64).name("x3773").srcCtx("GateMetaPipe.scala:145:26:sigQQ") } // x3773 = FIFONew(Const(64))
    isAccum(x3773) = false
    bufferDepthOf(x3773) = 2
    val x3774 = withCtrl(x3880) { FIFO(size=64).name("x3774").srcCtx("GateMetaPipe.scala:146:31:sigEleMuxQ") } // x3774 = FIFONew(Const(64))
    isAccum(x3774) = false
    bufferDepthOf(x3774) = 2
    val x3775 = withCtrl(x3880) { FIFO(size=64).name("x3775").srcCtx("GateMetaPipe.scala:147:27:tanhQ") } // x3775 = FIFONew(Const(64))
    isAccum(x3775) = false
    bufferDepthOf(x3775) = 2
    val x3776 = withCtrl(x3880) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x3776").srcCtx("GateMetaPipe.scala:149:34") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x3777 = withCtrl(x3880) { CounterChain(List(x3776)).name("x3777").srcCtx("GateMetaPipe.scala:149:48") } // CounterChainNew(List(x3776))
    val x3799 = withCtrl(x3880) { LoopController(style=InnerPipe, level=InnerControl, cchain=x3777).name("x3799").srcCtx("GateMetaPipe.scala:149:48") } // UnrolledForeach(List(b2169, b1969),x3777,Block(Const(())),List(List(b2176)),List(List(b2177)))
    val b2176 = withCtrl(x3799) { CounterIter(x3776, None).name("b2176") } // b2176
    val b2177 = withCtrl(x3799) { Const(true).name("b2177") } // b2177
    val x3778 = withCtrl(x3799) { OpDef(op=BitAnd, inputs=List(b2177, b2169)).name("x3778").srcCtx("UnrollingBase.scala:28:66") } // And(b2177,b2169)
    val x3779 = withCtrl(x3799) { OpDef(op=BitAnd, inputs=List(x3778, b1969)).name("x3779").srcCtx("UnrollingBase.scala:28:66") } // And(x3778,b1969)
    val x3780 = withCtrl(x3799) { LoadBanks(List(x3607_d0_b0), List(b2168, b2176)).name("x3780").srcCtx("GateMetaPipe.scala:150:27:pEle") } // ParSRAMLoad(x3607,List(List(b2168, b2176)),List(x3779))
    val x3781 = withCtrl(x3799) { x3780 } // VectorApply(x3780,0)
    val x3782_d0_b0 = withCtrl(x3799) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x3782_d0_b0").srcCtx("NonLinearity.scala:43:37:lut") } // x3782 = LUTNew(List(1024), Seq(Const(-0.9999997615814208984375),Const(-0.9999997615814208984375),Const(-0.999999701976776123046875)... [1021 more]))
    isAccum(x3782_d0_b0) = false
    bufferDepthOf(x3782_d0_b0) = 1
    staticDimsOf(x3782_d0_b0) = List(1024)
    val x3783 = withCtrl(x3799) { OpDef(op=FixSub, inputs=List(x3781, Const(-8.0))).name("x3783").srcCtx("NonLinearity.scala:44:22") } // FixSub(x3781,Const(-8))
    val x3784 = withCtrl(x3799) { OpDef(op=FixSla, inputs=List(x3783, Const(6))).name("x3784").srcCtx("NonLinearity.scala:44:30") } // FixLsh(x3783,Const(6))
    // x3785 = FixConvert(x3784,TRUE,_32,_0) x.tp=FixPt[TRUE,_8,_24] {
    val x3785 = withCtrl(x3799) { OpDef(op=FixSra, inputs=List(x3784, Const("24"))).name("x3785").srcCtx("NonLinearity.scala:44:41:index") } // FixConvert(x3784,TRUE,_32,_0)
    // }
    val x3786 = withCtrl(x3799) { LoadBanks(List(x3782_d0_b0), List(x3785)).name("x3786").srcCtx("NonLinearity.scala:45:17:sigVal") } // LUTLoad(x3782,List(x3785),x3779)
    val x3787_d0_b0 = withCtrl(x3799) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x3787_d0_b0").srcCtx("NonLinearity.scala:43:37:lut") } // x3787 = LUTNew(List(1024), Seq(Const(-0.9999997615814208984375),Const(-0.9999997615814208984375),Const(-0.999999701976776123046875)... [1021 more]))
    isAccum(x3787_d0_b0) = false
    bufferDepthOf(x3787_d0_b0) = 1
    staticDimsOf(x3787_d0_b0) = List(1024)
    val x3788 = withCtrl(x3799) { LoadBanks(List(x3787_d0_b0), List(x3785)).name("x3788").srcCtx("NonLinearity.scala:45:17:tanhVal") } // LUTLoad(x3787,List(x3785),x3779)
    val x3789 = withCtrl(x3799) { OpDef(op=FixLt, inputs=List(Const(8.0), x3781)).name("x3789").srcCtx("GateMetaPipe.scala:154:27:isHigh") } // FixLt(Const(8),x3781)
    val x3790 = withCtrl(x3799) { OpDef(op=FixLt, inputs=List(x3781, Const(-8.0))).name("x3790").srcCtx("GateMetaPipe.scala:155:26:isLow") } // FixLt(x3781,Const(-8))
    val x3791 = withCtrl(x3799) { OpDef(op=MuxOp, inputs=List(x3790, Const(0.0), x3786)).name("x3791").srcCtx("GateMetaPipe.scala:157:46") } // Mux(x3790,Const(0),x3786)
    val x3792 = withCtrl(x3799) { OpDef(op=MuxOp, inputs=List(x3789, Const(1.0), x3791)).name("x3792").srcCtx("GateMetaPipe.scala:157:25:sigEle") } // Mux(x3789,Const(1),x3791)
    val x3793 = withCtrl(x3799) { OpDef(op=MuxOp, inputs=List(x3790, Const(-1.0), x3788)).name("x3793").srcCtx("GateMetaPipe.scala:158:47") } // Mux(x3790,Const(-1),x3788)
    val x3794 = withCtrl(x3799) { OpDef(op=MuxOp, inputs=List(x3789, Const(1.0), x3793)).name("x3794").srcCtx("GateMetaPipe.scala:158:26:tanhEle") } // Mux(x3789,Const(1),x3793)
    val x3795_x3772 = withCtrl(x3799) { WriteMem(x3772, x3792).name("x3795_x3772").srcCtx("GateMetaPipe.scala:160:17") } // ParFIFOEnq(x3772,List(x3792),List(x3779))
    val x3796_x3773 = withCtrl(x3799) { WriteMem(x3773, x3792).name("x3796_x3773").srcCtx("GateMetaPipe.scala:161:18") } // ParFIFOEnq(x3773,List(x3792),List(x3779))
    val x3797_x3774 = withCtrl(x3799) { WriteMem(x3774, x3792).name("x3797_x3774").srcCtx("GateMetaPipe.scala:162:23") } // ParFIFOEnq(x3774,List(x3792),List(x3779))
    val x3798_x3775 = withCtrl(x3799) { WriteMem(x3775, x3794).name("x3798_x3775").srcCtx("GateMetaPipe.scala:164:18") } // ParFIFOEnq(x3775,List(x3794),List(x3779))
    val x3879 = withCtrl(x3880) { UnitController(style=SeqPipe, level=OuterControl).name("x3879").srcCtx("GateMetaPipe.scala:167:12") } // UnitPipe(List(b2169, b1969),Block(Const(())))
    val x3800 = withCtrl(x3879) { FIFO(size=64).name("x3800").srcCtx("GateMetaPipe.scala:169:29:cLastQ") } // x3800 = FIFONew(Const(64))
    isAccum(x3800) = false
    bufferDepthOf(x3800) = 1
    val x3801 = withCtrl(x3879) { FIFO(size=64).name("x3801").srcCtx("GateMetaPipe.scala:170:35:cNewMultAddQ") } // x3801 = FIFONew(Const(64))
    isAccum(x3801) = false
    bufferDepthOf(x3801) = 1
    val x3802 = withCtrl(x3879) { FIFO(size=64).name("x3802").srcCtx("GateMetaPipe.scala:171:36:cNewMultAddQQ") } // x3802 = FIFONew(Const(64))
    isAccum(x3802) = false
    bufferDepthOf(x3802) = 1
    val x3803 = withCtrl(x3879) { FIFO(size=64).name("x3803").srcCtx("GateMetaPipe.scala:172:32:cNewMultQ") } // x3803 = FIFONew(Const(64))
    isAccum(x3803) = false
    bufferDepthOf(x3803) = 1
    val x3804 = withCtrl(x3879) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x3804").srcCtx("GateMetaPipe.scala:174:36") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x3805 = withCtrl(x3879) { CounterChain(List(x3804)).name("x3805").srcCtx("GateMetaPipe.scala:174:50") } // CounterChainNew(List(x3804))
    val x3825 = withCtrl(x3879) { LoopController(style=InnerPipe, level=InnerControl, cchain=x3805).name("x3825").srcCtx("GateMetaPipe.scala:174:50") } // UnrolledForeach(List(b2169, b1969),x3805,Block(Const(())),List(List(b2206)),List(List(b2207)))
    val b2206 = withCtrl(x3825) { CounterIter(x3804, None).name("b2206") } // b2206
    val b2207 = withCtrl(x3825) { Const(true).name("b2207") } // b2207
    val x3806 = withCtrl(x3825) { OpDef(op=BitAnd, inputs=List(b2207, b2169)).name("x3806").srcCtx("UnrollingBase.scala:28:66") } // And(b2207,b2169)
    val x3807 = withCtrl(x3825) { OpDef(op=BitAnd, inputs=List(x3806, b1969)).name("x3807").srcCtx("UnrollingBase.scala:28:66") } // And(x3806,b1969)
    val x3808 = withCtrl(x3825) { ReadMem(x3772).name("x3808").srcCtx("GateMetaPipe.scala:175:32:sigEle") } // ParFIFODeq(x3772,List(x3807))
    val x3809 = withCtrl(x3825) { x3808 } // VectorApply(x3808,0)
    val x3810 = withCtrl(x3825) { ReadMem(x3775).name("x3810").srcCtx("GateMetaPipe.scala:176:34:tanhEle") } // ParFIFODeq(x3775,List(x3807))
    val x3811 = withCtrl(x3825) { x3810 } // VectorApply(x3810,0)
    val x3812 = withCtrl(x3825) { OpDef(op=FixEql, inputs=List(b2168, Const(0))).name("x3812").srcCtx("package.scala:110:40") } // FixEql(b2168,Const(0))
    val x3813 = withCtrl(x3825) { ReadMem(x3608_d0).name("x3813").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegRead(x3608)
    val x3814 = withCtrl(x3825) { OpDef(op=BitAnd, inputs=List(x3813, x3812)).name("x3814").srcCtx("GateMetaPipe.scala:177:36:isInitC") } // And(x3813,x3812)
    val x3815 = withCtrl(x3825) { LoadBanks(List(x3602_d0_b0), List(b2206)).name("x3815").srcCtx("GateMetaPipe.scala:179:35") } // ParSRAMLoad(x3602,List(List(b2206)),List(x3807))
    val x3816 = withCtrl(x3825) { x3815 } // VectorApply(x3815,0)
    val x3817 = withCtrl(x3825) { OpDef(op=MuxOp, inputs=List(x3814, Const(0.0), x3816)).name("x3817").srcCtx("GateMetaPipe.scala:178:26:cLast") } // Mux(x3814,Const(0),x3816)
    val x3818 = withCtrl(x3825) { OpDef(op=FixMul, inputs=List(x3817, x3811)).name("x3818").srcCtx("GateMetaPipe.scala:181:32:cNewMult") } // FixMul(x3817,x3811)
    val x3819 = withCtrl(x3825) { OpDef(op=FixMul, inputs=List(x3809, x3817)).name("x3819").srcCtx("GateMetaPipe.scala:182:36") } // FixMul(x3809,x3817)
    val x3820 = withCtrl(x3825) { OpDef(op=FixAdd, inputs=List(x3819, x3818)).name("x3820").srcCtx("GateMetaPipe.scala:182:44:cNewMultAdd") } // FixAdd(x3819,x3818)
    val x3821_x3803 = withCtrl(x3825) { WriteMem(x3803, x3818).name("x3821_x3803").srcCtx("GateMetaPipe.scala:184:24") } // ParFIFOEnq(x3803,List(x3818),List(x3807))
    val x3822_x3801 = withCtrl(x3825) { WriteMem(x3801, x3820).name("x3822_x3801").srcCtx("GateMetaPipe.scala:185:27") } // ParFIFOEnq(x3801,List(x3820),List(x3807))
    val x3823_x3802 = withCtrl(x3825) { WriteMem(x3802, x3820).name("x3823_x3802").srcCtx("GateMetaPipe.scala:186:28") } // ParFIFOEnq(x3802,List(x3820),List(x3807))
    val x3824_x3800 = withCtrl(x3825) { WriteMem(x3800, x3817).name("x3824_x3800").srcCtx("GateMetaPipe.scala:187:21") } // ParFIFOEnq(x3800,List(x3817),List(x3807))
    val x3826 = withCtrl(x3879) { FIFO(size=64).name("x3826").srcCtx("GateMetaPipe.scala:190:31:cUpdateQ") } // x3826 = FIFONew(Const(64))
    isAccum(x3826) = false
    bufferDepthOf(x3826) = 1
    val x3827 = withCtrl(x3879) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x3827").srcCtx("GateMetaPipe.scala:191:36") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x3828 = withCtrl(x3879) { CounterChain(List(x3827)).name("x3828").srcCtx("GateMetaPipe.scala:191:50") } // CounterChainNew(List(x3827))
    val x3846 = withCtrl(x3879) { LoopController(style=InnerPipe, level=InnerControl, cchain=x3828).name("x3846").srcCtx("GateMetaPipe.scala:191:50") } // UnrolledForeach(List(b2169, b1969),x3828,Block(Const(())),List(List(b2231)),List(List(b2232)))
    val b2231 = withCtrl(x3846) { CounterIter(x3827, None).name("b2231") } // b2231
    val b2232 = withCtrl(x3846) { Const(true).name("b2232") } // b2232
    val x3829 = withCtrl(x3846) { OpDef(op=BitAnd, inputs=List(b2232, b2169)).name("x3829").srcCtx("UnrollingBase.scala:28:66") } // And(b2232,b2169)
    val x3830 = withCtrl(x3846) { OpDef(op=BitAnd, inputs=List(x3829, b1969)).name("x3830").srcCtx("UnrollingBase.scala:28:66") } // And(x3829,b1969)
    val x3831 = withCtrl(x3846) { ReadMem(x3803).name("x3831").srcCtx("GateMetaPipe.scala:192:39:cNewMult") } // ParFIFODeq(x3803,List(x3830))
    val x3832 = withCtrl(x3846) { x3831 } // VectorApply(x3831,0)
    val x3833 = withCtrl(x3846) { ReadMem(x3774).name("x3833").srcCtx("GateMetaPipe.scala:193:38:sigEle") } // ParFIFODeq(x3774,List(x3830))
    val x3834 = withCtrl(x3846) { x3833 } // VectorApply(x3833,0)
    val x3835 = withCtrl(x3846) { ReadMem(x3801).name("x3835").srcCtx("GateMetaPipe.scala:194:45:cNewMultAdd") } // ParFIFODeq(x3801,List(x3830))
    val x3836 = withCtrl(x3846) { x3835 } // VectorApply(x3835,0)
    val x3837 = withCtrl(x3846) { ReadMem(x3800).name("x3837").srcCtx("GateMetaPipe.scala:195:33:cLast") } // ParFIFODeq(x3800,List(x3830))
    val x3838 = withCtrl(x3846) { x3837 } // VectorApply(x3837,0)
    val x3839 = withCtrl(x3846) { OpDef(op=FixEql, inputs=List(b2168, Const(0))).name("x3839").srcCtx("package.scala:110:40") } // FixEql(b2168,Const(0))
    val x3840 = withCtrl(x3846) { OpDef(op=FixEql, inputs=List(b2168, Const(1))).name("x3840").srcCtx("package.scala:113:40") } // FixEql(b2168,Const(1))
    val x3841 = withCtrl(x3846) { OpDef(op=FixEql, inputs=List(b2168, Const(2))).name("x3841").srcCtx("package.scala:116:40") } // FixEql(b2168,Const(2))
    val x3842 = withCtrl(x3846) { OpDef(op=MuxOp, inputs=List(x3841, x3836, x3838)).name("x3842").srcCtx("GateMetaPipe.scala:200:40") } // Mux(x3841,x3836,x3838)
    val x3843 = withCtrl(x3846) { OpDef(op=MuxOp, inputs=List(x3840, x3832, x3842)).name("x3843").srcCtx("GateMetaPipe.scala:199:36") } // Mux(x3840,x3832,x3842)
    val x3844 = withCtrl(x3846) { OpDef(op=MuxOp, inputs=List(x3839, x3834, x3843)).name("x3844").srcCtx("GateMetaPipe.scala:198:28:cUpdate") } // Mux(x3839,x3834,x3843)
    val x3845_x3826 = withCtrl(x3846) { WriteMem(x3826, x3844).name("x3845_x3826").srcCtx("GateMetaPipe.scala:206:23") } // ParFIFOEnq(x3826,List(x3844),List(x3830))
    val x3847 = withCtrl(x3879) { FIFO(size=64).name("x3847").srcCtx("GateMetaPipe.scala:209:31:hLinearQ") } // x3847 = FIFONew(Const(64))
    isAccum(x3847) = false
    bufferDepthOf(x3847) = 1
    val x3848 = withCtrl(x3879) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x3848").srcCtx("GateMetaPipe.scala:210:36") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x3849 = withCtrl(x3879) { CounterChain(List(x3848)).name("x3849").srcCtx("GateMetaPipe.scala:210:50") } // CounterChainNew(List(x3848))
    val x3864 = withCtrl(x3879) { LoopController(style=InnerPipe, level=InnerControl, cchain=x3849).name("x3864").srcCtx("GateMetaPipe.scala:210:50") } // UnrolledForeach(List(b2169, b1969),x3849,Block(Const(())),List(List(b2254)),List(List(b2255)))
    val b2254 = withCtrl(x3864) { CounterIter(x3848, None).name("b2254") } // b2254
    val b2255 = withCtrl(x3864) { Const(true).name("b2255") } // b2255
    val x3850 = withCtrl(x3864) { OpDef(op=BitAnd, inputs=List(b2255, b2169)).name("x3850").srcCtx("UnrollingBase.scala:28:66") } // And(b2255,b2169)
    val x3851 = withCtrl(x3864) { OpDef(op=BitAnd, inputs=List(x3850, b1969)).name("x3851").srcCtx("UnrollingBase.scala:28:66") } // And(x3850,b1969)
    val x3852 = withCtrl(x3864) { ReadMem(x3802).name("x3852").srcCtx("GateMetaPipe.scala:211:46:cNewMultAdd") } // ParFIFODeq(x3802,List(x3851))
    val x3853 = withCtrl(x3864) { x3852 } // VectorApply(x3852,0)
    val x3854_d0_b0 = withCtrl(x3864) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x3854_d0_b0").srcCtx("NonLinearity.scala:43:37:lut") } // x3854 = LUTNew(List(1024), Seq(Const(-0.9999997615814208984375),Const(-0.9999997615814208984375),Const(-0.999999701976776123046875)... [1021 more]))
    isAccum(x3854_d0_b0) = false
    bufferDepthOf(x3854_d0_b0) = 1
    staticDimsOf(x3854_d0_b0) = List(1024)
    val x3855 = withCtrl(x3864) { OpDef(op=FixSub, inputs=List(x3853, Const(-8.0))).name("x3855").srcCtx("NonLinearity.scala:44:22") } // FixSub(x3853,Const(-8))
    val x3856 = withCtrl(x3864) { OpDef(op=FixSla, inputs=List(x3855, Const(6))).name("x3856").srcCtx("NonLinearity.scala:44:30") } // FixLsh(x3855,Const(6))
    // x3857 = FixConvert(x3856,TRUE,_32,_0) x.tp=FixPt[TRUE,_8,_24] {
    val x3857 = withCtrl(x3864) { OpDef(op=FixSra, inputs=List(x3856, Const("24"))).name("x3857").srcCtx("NonLinearity.scala:44:41:index") } // FixConvert(x3856,TRUE,_32,_0)
    // }
    val x3858 = withCtrl(x3864) { LoadBanks(List(x3854_d0_b0), List(x3857)).name("x3858").srcCtx("NonLinearity.scala:45:17:actValMultAdd") } // LUTLoad(x3854,List(x3857),x3851)
    val x3859 = withCtrl(x3864) { OpDef(op=FixLt, inputs=List(Const(8.0), x3853)).name("x3859").srcCtx("GateMetaPipe.scala:214:36:isHigh") } // FixLt(Const(8),x3853)
    val x3860 = withCtrl(x3864) { OpDef(op=FixLt, inputs=List(x3853, Const(-8.0))).name("x3860").srcCtx("GateMetaPipe.scala:215:35:isLow") } // FixLt(x3853,Const(-8))
    val x3861 = withCtrl(x3864) { OpDef(op=MuxOp, inputs=List(x3860, Const(-1.0), x3858)).name("x3861").srcCtx("GateMetaPipe.scala:217:33") } // Mux(x3860,Const(-1),x3858)
    val x3862 = withCtrl(x3864) { OpDef(op=MuxOp, inputs=List(x3859, Const(1.0), x3861)).name("x3862").srcCtx("GateMetaPipe.scala:216:28:hLinear") } // Mux(x3859,Const(1),x3861)
    val x3863_x3847 = withCtrl(x3864) { WriteMem(x3847, x3862).name("x3863_x3847").srcCtx("GateMetaPipe.scala:222:23") } // ParFIFOEnq(x3847,List(x3862),List(x3851))
    val x3865 = withCtrl(x3879) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x3865").srcCtx("GateMetaPipe.scala:225:36") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x3866 = withCtrl(x3879) { CounterChain(List(x3865)).name("x3866").srcCtx("GateMetaPipe.scala:225:50") } // CounterChainNew(List(x3865))
    val x3878 = withCtrl(x3879) { LoopController(style=InnerPipe, level=InnerControl, cchain=x3866).name("x3878").srcCtx("GateMetaPipe.scala:225:50") } // UnrolledForeach(List(b2169, b1969),x3866,Block(Const(())),List(List(b2273)),List(List(b2274)))
    val b2273 = withCtrl(x3878) { CounterIter(x3865, None).name("b2273") } // b2273
    val b2274 = withCtrl(x3878) { Const(true).name("b2274") } // b2274
    val x3867 = withCtrl(x3878) { OpDef(op=BitAnd, inputs=List(b2274, b2169)).name("x3867").srcCtx("UnrollingBase.scala:28:66") } // And(b2274,b2169)
    val x3868 = withCtrl(x3878) { OpDef(op=BitAnd, inputs=List(x3867, b1969)).name("x3868").srcCtx("UnrollingBase.scala:28:66") } // And(x3867,b1969)
    val x3869 = withCtrl(x3878) { ReadMem(x3826).name("x3869").srcCtx("GateMetaPipe.scala:229:34:cNew") } // ParFIFODeq(x3826,List(x3868))
    val x3870 = withCtrl(x3878) { x3869 } // VectorApply(x3869,0)
    val x3871 = withCtrl(x3878) { ReadMem(x3847).name("x3871").srcCtx("GateMetaPipe.scala:230:37:hLinear") } // ParFIFODeq(x3847,List(x3868))
    val x3872 = withCtrl(x3878) { x3871 } // VectorApply(x3871,0)
    val x3873 = withCtrl(x3878) { ReadMem(x3773).name("x3873").srcCtx("GateMetaPipe.scala:231:33:sigEle") } // ParFIFODeq(x3773,List(x3868))
    val x3874 = withCtrl(x3878) { x3873 } // VectorApply(x3873,0)
    val x3875 = withCtrl(x3878) { OpDef(op=FixMul, inputs=List(x3872, x3874)).name("x3875").srcCtx("GateMetaPipe.scala:232:30:hNew") } // FixMul(x3872,x3874)
    val x3876 = withCtrl(x3878) { StoreBanks(List(List(x3603_d0_b0), List(x3603_d1_b0)), List(b2273), x3875).name("x3876").srcCtx("GateMetaPipe.scala:234:29") } // ParSRAMStore(x3603,List(List(b2273)),List(x3875),List(x3868))
    val x3877 = withCtrl(x3878) { StoreBanks(List(List(x3602_d0_b0)), List(b2273), x3870).name("x3877").srcCtx("GateMetaPipe.scala:235:29") } // ParSRAMStore(x3602,List(List(b2273)),List(x3870),List(x3868))
    val x3903 = withCtrl(x3904) { UnitController(style=StreamPipe, level=OuterControl).name("x3903").srcCtx("DeepBenchLSTM256.scala:119:39") } // UnitPipe(List(Const(true)),Block(Const(())))
    val b3915 = withCtrl(x3903) { StreamOut(field="offset").name("b3915").srcCtx("DeepBenchLSTM256.scala:119:39") } // x3882 = StreamOutNew(BurstCmdBus)
    isAccum(b3915) = false
    bufferDepthOf(b3915) = 1
    val b3916 = withCtrl(x3903) { StreamOut(field="size").name("b3916").srcCtx("DeepBenchLSTM256.scala:119:39") } // x3882 = StreamOutNew(BurstCmdBus)
    isAccum(b3916) = false
    bufferDepthOf(b3916) = 1
    val x3883 = withCtrl(x3903) { StreamOut(field="data").name("x3883").srcCtx("DeepBenchLSTM256.scala:119:39") } // x3883 = StreamOutNew(BurstFullDataBus())
    isAccum(x3883) = false
    bufferDepthOf(x3883) = 1
    val x3884 = withCtrl(x3903) { StreamIn(field="ack").name("x3884").srcCtx("DeepBenchLSTM256.scala:119:39") } // x3884 = StreamInNew(BurstAckBus)
    isAccum(x3884) = false
    bufferDepthOf(x3884) = 1
    val x3892 = withCtrl(x3903) { UnitController(style=SeqPipe, level=InnerControl).name("x3892").srcCtx("DeepBenchLSTM256.scala:119:39") } // UnitPipe(List(Const(true)),Block(x3891))
    val x3885 = withCtrl(x3892) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x3886 = withCtrl(x3892) { OpDef(op=FixSla, inputs=List(x3885, Const(2))).name("x3886").srcCtx("DeepBenchLSTM256.scala:119:39") } // FixLsh(x3885,Const(2))
    val x3887 = withCtrl(x3892) { x3886 } // FixConvert(x3886,TRUE,_64,_0)
    val x3888 = withCtrl(x3892) { DramAddress(x3597).name("x3888").srcCtx("DeepBenchLSTM256.scala:119:39") } // GetDRAMAddress(x3597)
    val x3890_x3889 = withCtrl(x3892) { OpDef(op=FixAdd, inputs=List(x3887, x3888)).name("x3890_x3889").srcCtx("DeepBenchLSTM256.scala:119:39") } // FixAdd(x3887,x3888)
    // x3890 = SimpleStruct(ArrayBuffer((offset,x3889), (size,Const(1024)), (isLoad,Const(false))))
    val x3891_b3917_b3915 = withCtrl(x3892) { WriteMem(b3915, x3890_x3889).name("x3891_b3917_b3915").srcCtx("DeepBenchLSTM256.scala:119:39") } // StreamWrite(x3882,x3890,Const(true))
    val x3891_b3918_b3916 = withCtrl(x3892) { WriteMem(b3916, Const(1024)).name("x3891_b3918_b3916").srcCtx("DeepBenchLSTM256.scala:119:39") } // StreamWrite(x3882,x3890,Const(true))
    val x3893 = withCtrl(x3903) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x3893").srcCtx("DeepBenchLSTM256.scala:119:39") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x3894 = withCtrl(x3903) { CounterChain(List(x3893)).name("x3894").srcCtx("DeepBenchLSTM256.scala:119:39") } // CounterChainNew(List(x3893))
    val x3899 = withCtrl(x3903) { LoopController(style=InnerPipe, level=InnerControl, cchain=x3894).name("x3899").srcCtx("DeepBenchLSTM256.scala:119:39") } // UnrolledForeach(List(Const(true)),x3894,Block(Const(())),List(List(b2303)),List(List(b2304)))
    val b2303 = withCtrl(x3899) { CounterIter(x3893, None).name("b2303") } // b2303
    val b2304 = withCtrl(x3899) { Const(true).name("b2304") } // b2304
    val x3895 = withCtrl(x3899) { LoadBanks(List(x3603_d0_b0), List(b2303)).name("x3895").srcCtx("DeepBenchLSTM256.scala:119:39") } // ParSRAMLoad(x3603,List(List(b2303)),List(b2304))
    val x3897_x3896 = withCtrl(x3899) { x3895 } // VectorApply(x3895,0)
    // x3897 = SimpleStruct(ArrayBuffer((_1,x3896), (_2,Const(true))))
    val x3898_x3898_x3883 = withCtrl(x3899) { WriteMem(x3883, x3897_x3896).name("x3898_x3898_x3883").srcCtx("DeepBenchLSTM256.scala:119:39") } // ParStreamWrite(x3883,List(x3897),List(b2304))
    val x3900 = withCtrl(x3903) { FringeDenseStore(dram=List(x3597), cmdStream=List(b3915, b3916), dataStream=List(x3883), ackStream=List(x3884)).name("x3900").srcCtx("DeepBenchLSTM256.scala:119:39") } // FringeDenseStore(x3597,x3882,x3883,x3884)
    val x3902 = withCtrl(x3903) { UnitController(style=SeqPipe, level=InnerControl).name("x3902").srcCtx("DeepBenchLSTM256.scala:119:39") } // UnitPipe(List(Const(true)),Block(Const(())))
    val x3901_x3901 = withCtrl(x3902) { ReadMem(x3884).name("x3901_x3901").srcCtx("DeepBenchLSTM256.scala:119:39") } // StreamRead(x3884,Const(true))
    
  }
}
