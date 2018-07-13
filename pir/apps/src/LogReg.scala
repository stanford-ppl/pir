import pir._
import pir.node._
import arch._
import prism.enums._

object LogReg extends PIRApp {
  def main(implicit design:PIRDesign) = {
    import design.pirmeta._
    val x3583 = ArgIn(init=0).name("x3583").ctrl(top).srcCtx("LogReg.scala:25:22:iters") // ArgInNew(Const(0))
    isAccum(x3583) = false
    bufferDepthOf(x3583) = 1
    boundOf(x3583) = 1
    val x3584 = ArgIn(init=0).name("x3584").ctrl(top).srcCtx("LogReg.scala:26:22:N") // ArgInNew(Const(0))
    isAccum(x3584) = false
    bufferDepthOf(x3584) = 1
    boundOf(x3584) = 1024
    val x3587 = ReadMem(x3584).name("x3587").ctrl(top).srcCtx("LogReg.scala:30:21") // RegRead(x3584)
    val x3588 = DRAM(dims=List(x3587, Const(128))).name("x3588").ctrl(top).srcCtx("LogReg.scala:30:20:x") // x3588 = DRAMNew(ArrayBuffer(x3587, Const(128)),Const(0.0))
    val x3589 = ReadMem(x3584).name("x3589").ctrl(top).srcCtx("LogReg.scala:31:21") // RegRead(x3584)
    val x3590 = DRAM(dims=List(x3589)).name("x3590").ctrl(top).srcCtx("LogReg.scala:31:20:y") // x3590 = DRAMNew(ArrayBuffer(x3589),Const(0.0))
    val x3591 = DRAM(dims=List(Const(128))).name("x3591").ctrl(top).srcCtx("LogReg.scala:32:24:theta") // x3591 = DRAMNew(ArrayBuffer(Const(128)),Const(0.0))
    val x3789 = UnitController(style=SeqPipe, level=OuterControl).name("x3789").ctrl(top).srcCtx("LogReg.scala:38:11") // Hwblock(Block(Const(())),false)
    val x3597_d0_b0 = SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x3597_d0_b0").ctrl(x3789).srcCtx("LogReg.scala:39:27:btheta") // x3597 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x3597_d0_b0) = false
    bufferDepthOf(x3597_d0_b0) = 1
    staticDimsOf(x3597_d0_b0) = List(128)
    val x3597_d1_b0 = SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x3597_d1_b0").ctrl(x3789).srcCtx("LogReg.scala:39:27:btheta") // x3597 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x3597_d1_b0) = true
    bufferDepthOf(x3597_d1_b0) = 1
    staticDimsOf(x3597_d1_b0) = List(128)
    val x3597_d2_b0 = SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x3597_d2_b0").ctrl(x3789).srcCtx("LogReg.scala:39:27:btheta") // x3597 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x3597_d2_b0) = false
    bufferDepthOf(x3597_d2_b0) = 1
    staticDimsOf(x3597_d2_b0) = List(128)
    val x3615 = UnitController(style=StreamPipe, level=OuterControl).name("x3615").ctrl(x3789).srcCtx("LogReg.scala:41:14") // UnitPipe(List(Const(true)),Block(Const(())))
    val b3859 = StreamOut(field="offset").name("b3859").ctrl(x3615).srcCtx("LogReg.scala:41:14") // x3598 = StreamOutNew(BurstCmdBus)
    isAccum(b3859) = false
    bufferDepthOf(b3859) = 1
    val b3860 = StreamOut(field="size").name("b3860").ctrl(x3615).srcCtx("LogReg.scala:41:14") // x3598 = StreamOutNew(BurstCmdBus)
    isAccum(b3860) = false
    bufferDepthOf(b3860) = 1
    val x3599 = StreamIn(field="data").name("x3599").ctrl(x3615).srcCtx("LogReg.scala:41:14") // x3599 = StreamInNew(BurstDataBus())
    isAccum(x3599) = false
    bufferDepthOf(x3599) = 1
    val x3607 = UnitController(style=SeqPipe, level=InnerControl).name("x3607").ctrl(x3615).srcCtx("LogReg.scala:41:14") // UnitPipe(List(Const(true)),Block(x3606))
    val x3600 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x3601 = OpDef(op=FixSla, inputs=List(x3600, Const(2))).name("x3601").ctrl(x3607).srcCtx("LogReg.scala:41:14") // FixLsh(x3600,Const(2))
    val x3602 = x3601 // FixConvert(x3601,TRUE,_64,_0)
    val x3603 = DramAddress(x3591).name("x3603").ctrl(x3607).srcCtx("LogReg.scala:41:14") // GetDRAMAddress(x3591)
    val x3605_x3604 = OpDef(op=FixAdd, inputs=List(x3602, x3603)).name("x3605_x3604").ctrl(x3607).srcCtx("LogReg.scala:41:14") // FixAdd(x3602,x3603)
    // x3605 = SimpleStruct(ArrayBuffer((offset,x3604), (size,Const(512)), (isLoad,Const(true))))
    val x3606_b3861_b3859 = WriteMem(b3859, x3605_x3604).name("x3606_b3861_b3859").ctrl(x3607).srcCtx("LogReg.scala:41:14") // StreamWrite(x3598,x3605,Const(true))
    val x3606_b3862_b3860 = WriteMem(b3860, Const(512)).name("x3606_b3862_b3860").ctrl(x3607).srcCtx("LogReg.scala:41:14") // StreamWrite(x3598,x3605,Const(true))
    val x3608 = FringeDenseLoad(dram=List(x3591), cmdStream=List(b3859, b3860), dataStream=List(x3599)).name("x3608").ctrl(x3615).srcCtx("LogReg.scala:41:14") // FringeDenseLoad(x3591,x3598,x3599)
    val x3609 = Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x3609").ctrl(x3615).srcCtx("LogReg.scala:41:14") // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x3610 = CounterChain(List(x3609)).name("x3610").ctrl(x3615).srcCtx("LogReg.scala:41:14") // CounterChainNew(List(x3609))
    val x3614 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3610).name("x3614").ctrl(x3615).srcCtx("LogReg.scala:41:14") // UnrolledForeach(List(Const(true)),x3610,Block(Const(())),List(List(b2180)),List(List(b2181)))
    val b2180 = CounterIter(x3609, None).name("b2180").ctrl(x3614) // b2180
    val b2181 = Const(true).name("b2181").ctrl(x3614) // b2181
    val x3611_x3611 = ReadMem(x3599).name("x3611_x3611").ctrl(x3614).srcCtx("LogReg.scala:41:14") // ParStreamRead(x3599,List(b2181))
    val x3612_x3612 = x3611_x3611 // x3612 = VectorApply(x3611,0)
    val x3613 = StoreBanks(List(List(x3597_d0_b0), List(x3597_d1_b0), List(x3597_d2_b0)), List(b2180), x3612_x3612).name("x3613").ctrl(x3614).srcCtx("LogReg.scala:41:14") // ParSRAMStore(x3597,List(List(b2180)),List(x3612),List(b2181))
    val x3616 = ReadMem(x3583).name("x3616").ctrl(x3789).srcCtx("LogReg.scala:43:26") // RegRead(x3583)
    val x3617 = Counter(min=Const(0), max=x3616, step=Const(1), par=1).name("x3617").ctrl(x3789).srcCtx("LogReg.scala:43:32") // CounterNew(Const(0),x3616,Const(1),Const(1))
    val x3618 = CounterChain(List(x3617)).name("x3618").ctrl(x3789).srcCtx("LogReg.scala:43:38") // CounterChainNew(List(x3617))
    val x3766 = LoopController(style=SeqPipe, level=OuterControl, cchain=x3618).name("x3766").ctrl(x3789).srcCtx("LogReg.scala:43:38") // UnrolledForeach(List(Const(true)),x3618,Block(Const(())),List(List(b2190)),List(List(b2191)))
    val b2190 = CounterIter(x3617, Some(0)).name("b2190").ctrl(x3766) // b2190
    val b2191 = Const(true).name("b2191").ctrl(x3766) // b2191
    val x3619_d0_b0 = SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x3619_d0_b0").ctrl(x3766).srcCtx("LogReg.scala:45:37:grad") // x3619 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x3619_d0_b0) = false
    bufferDepthOf(x3619_d0_b0) = 1
    staticDimsOf(x3619_d0_b0) = List(128)
    val x3619_d1_b0 = SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x3619_d1_b0").ctrl(x3766).srcCtx("LogReg.scala:45:37:grad") // x3619 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x3619_d1_b0) = true
    bufferDepthOf(x3619_d1_b0) = 1
    staticDimsOf(x3619_d1_b0) = List(128)
    val x3620 = ReadMem(x3584).name("x3620").ctrl(x3766).srcCtx("LogReg.scala:45:49") // RegRead(x3584)
    val x3621 = Counter(min=Const(0), max=x3620, step=Const(64), par=1).name("x3621").ctrl(x3766).srcCtx("LogReg.scala:45:57") // CounterNew(Const(0),x3620,Const(64),Const(1))
    val x3622 = CounterChain(List(x3621)).name("x3622").ctrl(x3766).srcCtx("LogReg.scala:64:11") // CounterChainNew(List(x3621))
    val x3754 = LoopController(style=MetaPipe, level=OuterControl, cchain=x3622).name("x3754").ctrl(x3766).srcCtx("LogReg.scala:64:11") // UnrolledReduce(List(b2191),x3622,x3619,Block((x3619) => Const(())),List(List(b2198)),List(List(b2199)))
    val b2198 = CounterIter(x3621, Some(0)).name("b2198").ctrl(x3754) // b2198
    val b2199 = Const(true).name("b2199").ctrl(x3754) // b2199
    val x3623_d0_b0 = SRAM(size=8192, banking=Strided(banks=16, stride=1)).name("x3623_d0_b0").ctrl(x3754).srcCtx("LogReg.scala:46:30:xTile") // x3623 = SRAMNew(ArrayBuffer(Const(64), Const(128)))
    isAccum(x3623_d0_b0) = false
    bufferDepthOf(x3623_d0_b0) = 2
    staticDimsOf(x3623_d0_b0) = List(64, 128)
    val x3623_d1_b0 = SRAM(size=8192, banking=Strided(banks=16, stride=1)).name("x3623_d1_b0").ctrl(x3754).srcCtx("LogReg.scala:46:30:xTile") // x3623 = SRAMNew(ArrayBuffer(Const(64), Const(128)))
    isAccum(x3623_d1_b0) = false
    bufferDepthOf(x3623_d1_b0) = 2
    staticDimsOf(x3623_d1_b0) = List(64, 128)
    val x3624_d0_b0 = SRAM(size=64, banking=Strided(banks=16, stride=1)).name("x3624_d0_b0").ctrl(x3754).srcCtx("LogReg.scala:47:30:yTile") // x3624 = SRAMNew(ArrayBuffer(Const(64)))
    isAccum(x3624_d0_b0) = false
    bufferDepthOf(x3624_d0_b0) = 2
    staticDimsOf(x3624_d0_b0) = List(64)
    val x3677 = UnitController(style=ForkJoin, level=OuterControl).name("x3677").ctrl(x3754).srcCtx("LogReg.scala:48:20") // ParallelPipe(List(b2199, b2191),Block(Const(())))
    val x3626 = UnitController(style=SeqPipe, level=InnerControl).name("x3626").ctrl(x3677).srcCtx("LogReg.scala:48:20") // UnitPipe(List(b2199, b2191),Block(Const(())))
    val x3625 = OpDef(op=FixAdd, inputs=List(b2198, Const(64))).name("x3625").ctrl(x3626).srcCtx("LogReg.scala:49:30") // FixAdd(b2198,Const(64))
    val x3627 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1).name("x3627").ctrl(x3677).srcCtx("LogReg.scala:49:19") // CounterNew(Const(0),Const(64),Const(1),Const(1))
    val x3628 = CounterChain(List(x3627)).name("x3628").ctrl(x3677).srcCtx("LogReg.scala:49:19") // CounterChainNew(List(x3627))
    val x3655 = LoopController(style=StreamPipe, level=OuterControl, cchain=x3628).name("x3655").ctrl(x3677).srcCtx("LogReg.scala:49:19") // UnrolledForeach(List(b2199, b2191),x3628,Block(Const(())),List(List(b2206)),List(List(b2207)))
    val b2206 = CounterIter(x3627, Some(0)).name("b2206").ctrl(x3655) // b2206
    val b2207 = Const(true).name("b2207").ctrl(x3655) // b2207
    val b3863 = StreamOut(field="offset").name("b3863").ctrl(x3655).srcCtx("LogReg.scala:49:19") // x3629 = StreamOutNew(BurstCmdBus)
    isAccum(b3863) = false
    bufferDepthOf(b3863) = 1
    val b3864 = StreamOut(field="size").name("b3864").ctrl(x3655).srcCtx("LogReg.scala:49:19") // x3629 = StreamOutNew(BurstCmdBus)
    isAccum(b3864) = false
    bufferDepthOf(b3864) = 1
    val x3630 = StreamIn(field="data").name("x3630").ctrl(x3655).srcCtx("LogReg.scala:49:19") // x3630 = StreamInNew(BurstDataBus())
    isAccum(x3630) = false
    bufferDepthOf(x3630) = 1
    val x3644 = UnitController(style=SeqPipe, level=InnerControl).name("x3644").ctrl(x3655).srcCtx("LogReg.scala:49:19") // UnitPipe(List(b2207, b2199, b2191),Block(x3643))
    val x3631 = OpDef(op=FixAdd, inputs=List(b2198, b2206)).name("x3631").ctrl(x3644).srcCtx("LogReg.scala:49:19") // FixAdd(b2198,b2206)
    val x3632 = x3631 // FixConvert(x3631,TRUE,_32,_0) (Same Type. No op)
    val x3633 = OpDef(op=FixSla, inputs=List(x3632, Const(7))).name("x3633").ctrl(x3644).srcCtx("LogReg.scala:49:19") // FixLsh(x3632,Const(7))
    val x3634 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x3635 = OpDef(op=FixAdd, inputs=List(x3633, x3634)).name("x3635").ctrl(x3644).srcCtx("LogReg.scala:49:19") // FixAdd(x3633,x3634)
    val x3636 = OpDef(op=FixSla, inputs=List(x3635, Const(2))).name("x3636").ctrl(x3644).srcCtx("LogReg.scala:49:19") // FixLsh(x3635,Const(2))
    val x3637 = x3636 // FixConvert(x3636,TRUE,_64,_0)
    val x3638 = DramAddress(x3588).name("x3638").ctrl(x3644).srcCtx("LogReg.scala:49:19") // GetDRAMAddress(x3588)
    val x3640_x3639 = OpDef(op=FixAdd, inputs=List(x3637, x3638)).name("x3640_x3639").ctrl(x3644).srcCtx("LogReg.scala:49:19") // FixAdd(x3637,x3638)
    // x3640 = SimpleStruct(ArrayBuffer((offset,x3639), (size,Const(512)), (isLoad,Const(true))))
    val x3641 = OpDef(op=BitAnd, inputs=List(b2207, b2199)).name("x3641").ctrl(x3644).srcCtx("UnrollingBase.scala:28:66") // And(b2207,b2199)
    val x3642 = OpDef(op=BitAnd, inputs=List(x3641, b2191)).name("x3642").ctrl(x3644).srcCtx("UnrollingBase.scala:28:66") // And(x3641,b2191)
    val x3643_b3865_b3863 = WriteMem(b3863, x3640_x3639).name("x3643_b3865_b3863").ctrl(x3644).srcCtx("LogReg.scala:49:19") // StreamWrite(x3629,x3640,x3642)
    val x3643_b3866_b3864 = WriteMem(b3864, Const(512)).name("x3643_b3866_b3864").ctrl(x3644).srcCtx("LogReg.scala:49:19") // StreamWrite(x3629,x3640,x3642)
    val x3645 = FringeDenseLoad(dram=List(x3588), cmdStream=List(b3863, b3864), dataStream=List(x3630)).name("x3645").ctrl(x3655).srcCtx("LogReg.scala:49:19") // FringeDenseLoad(x3588,x3629,x3630)
    val x3646 = Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x3646").ctrl(x3655).srcCtx("LogReg.scala:49:19") // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x3647 = CounterChain(List(x3646)).name("x3647").ctrl(x3655).srcCtx("LogReg.scala:49:19") // CounterChainNew(List(x3646))
    val x3654 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3647).name("x3654").ctrl(x3655).srcCtx("LogReg.scala:49:19") // UnrolledForeach(List(b2207, b2199, b2191),x3647,Block(Const(())),List(List(b2227)),List(List(b2228)))
    val b2227 = CounterIter(x3646, None).name("b2227").ctrl(x3654) // b2227
    val b2228 = Const(true).name("b2228").ctrl(x3654) // b2228
    val x3648 = OpDef(op=BitAnd, inputs=List(b2228, b2207)).name("x3648").ctrl(x3654).srcCtx("UnrollingBase.scala:28:66") // And(b2228,b2207)
    val x3649 = OpDef(op=BitAnd, inputs=List(b2199, b2191)).name("x3649").ctrl(x3654).srcCtx("UnrollingBase.scala:28:66") // And(b2199,b2191)
    val x3650 = OpDef(op=BitAnd, inputs=List(x3648, x3649)).name("x3650").ctrl(x3654).srcCtx("UnrollingBase.scala:28:66") // And(x3648,x3649)
    val x3651_x3651 = ReadMem(x3630).name("x3651_x3651").ctrl(x3654).srcCtx("LogReg.scala:49:19") // ParStreamRead(x3630,List(x3650))
    val x3652_x3652 = x3651_x3651 // x3652 = VectorApply(x3651,0)
    val x3653 = StoreBanks(List(List(x3623_d0_b0), List(x3623_d1_b0)), List(b2206, b2227), x3652_x3652).name("x3653").ctrl(x3654).srcCtx("LogReg.scala:49:19") // ParSRAMStore(x3623,List(List(b2206, b2227)),List(x3652),List(x3650))
    val x3676 = UnitController(style=StreamPipe, level=OuterControl).name("x3676").ctrl(x3677).srcCtx("LogReg.scala:50:19") // UnitPipe(List(b2199, b2191),Block(Const(())))
    val b3867 = StreamOut(field="offset").name("b3867").ctrl(x3676).srcCtx("LogReg.scala:50:19") // x3656 = StreamOutNew(BurstCmdBus)
    isAccum(b3867) = false
    bufferDepthOf(b3867) = 1
    val b3868 = StreamOut(field="size").name("b3868").ctrl(x3676).srcCtx("LogReg.scala:50:19") // x3656 = StreamOutNew(BurstCmdBus)
    isAccum(b3868) = false
    bufferDepthOf(b3868) = 1
    val x3657 = StreamIn(field="data").name("x3657").ctrl(x3676).srcCtx("LogReg.scala:50:19") // x3657 = StreamInNew(BurstDataBus())
    isAccum(x3657) = false
    bufferDepthOf(x3657) = 1
    val x3666 = UnitController(style=SeqPipe, level=InnerControl).name("x3666").ctrl(x3676).srcCtx("LogReg.scala:50:19") // UnitPipe(List(b2199, b2191),Block(x3665))
    val x3658 = b2198 // FixConvert(b2198,TRUE,_32,_0) (Same Type. No op)
    val x3659 = OpDef(op=FixSla, inputs=List(x3658, Const(2))).name("x3659").ctrl(x3666).srcCtx("LogReg.scala:50:19") // FixLsh(x3658,Const(2))
    val x3660 = x3659 // FixConvert(x3659,TRUE,_64,_0)
    val x3661 = DramAddress(x3590).name("x3661").ctrl(x3666).srcCtx("LogReg.scala:50:19") // GetDRAMAddress(x3590)
    val x3663_x3662 = OpDef(op=FixAdd, inputs=List(x3660, x3661)).name("x3663_x3662").ctrl(x3666).srcCtx("LogReg.scala:50:19") // FixAdd(x3660,x3661)
    // x3663 = SimpleStruct(ArrayBuffer((offset,x3662), (size,Const(256)), (isLoad,Const(true))))
    val x3664 = OpDef(op=BitAnd, inputs=List(b2199, b2191)).name("x3664").ctrl(x3666).srcCtx("UnrollingBase.scala:28:66") // And(b2199,b2191)
    val x3665_b3869_b3867 = WriteMem(b3867, x3663_x3662).name("x3665_b3869_b3867").ctrl(x3666).srcCtx("LogReg.scala:50:19") // StreamWrite(x3656,x3663,x3664)
    val x3665_b3870_b3868 = WriteMem(b3868, Const(256)).name("x3665_b3870_b3868").ctrl(x3666).srcCtx("LogReg.scala:50:19") // StreamWrite(x3656,x3663,x3664)
    val x3667 = FringeDenseLoad(dram=List(x3590), cmdStream=List(b3867, b3868), dataStream=List(x3657)).name("x3667").ctrl(x3676).srcCtx("LogReg.scala:50:19") // FringeDenseLoad(x3590,x3656,x3657)
    val x3668 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x3668").ctrl(x3676).srcCtx("LogReg.scala:50:19") // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x3669 = CounterChain(List(x3668)).name("x3669").ctrl(x3676).srcCtx("LogReg.scala:50:19") // CounterChainNew(List(x3668))
    val x3675 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3669).name("x3675").ctrl(x3676).srcCtx("LogReg.scala:50:19") // UnrolledForeach(List(b2199, b2191),x3669,Block(Const(())),List(List(b2251)),List(List(b2252)))
    val b2251 = CounterIter(x3668, None).name("b2251").ctrl(x3675) // b2251
    val b2252 = Const(true).name("b2252").ctrl(x3675) // b2252
    val x3670 = OpDef(op=BitAnd, inputs=List(b2252, b2199)).name("x3670").ctrl(x3675).srcCtx("UnrollingBase.scala:28:66") // And(b2252,b2199)
    val x3671 = OpDef(op=BitAnd, inputs=List(x3670, b2191)).name("x3671").ctrl(x3675).srcCtx("UnrollingBase.scala:28:66") // And(x3670,b2191)
    val x3672_x3672 = ReadMem(x3657).name("x3672_x3672").ctrl(x3675).srcCtx("LogReg.scala:50:19") // ParStreamRead(x3657,List(x3671))
    val x3673_x3673 = x3672_x3672 // x3673 = VectorApply(x3672,0)
    val x3674 = StoreBanks(List(List(x3624_d0_b0)), List(b2251), x3673_x3673).name("x3674").ctrl(x3675).srcCtx("LogReg.scala:50:19") // ParSRAMStore(x3624,List(List(b2251)),List(x3673),List(x3671))
    val x3678_d0_b0 = SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x3678_d0_b0").ctrl(x3754).srcCtx("LogReg.scala:52:28") // x3678 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x3678_d0_b0) = false
    bufferDepthOf(x3678_d0_b0) = 2
    staticDimsOf(x3678_d0_b0) = List(128)
    val x3678_d1_b0 = SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x3678_d1_b0").ctrl(x3754).srcCtx("LogReg.scala:52:28") // x3678 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x3678_d1_b0) = true
    bufferDepthOf(x3678_d1_b0) = 1
    staticDimsOf(x3678_d1_b0) = List(128)
    val x3679 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1).name("x3679").ctrl(x3754).srcCtx("LogReg.scala:52:48") // CounterNew(Const(0),Const(64),Const(1),Const(1))
    val x3680 = CounterChain(List(x3679)).name("x3680").ctrl(x3754).srcCtx("LogReg.scala:63:13") // CounterChainNew(List(x3679))
    val x3739 = LoopController(style=MetaPipe, level=OuterControl, cchain=x3680).name("x3739").ctrl(x3754).srcCtx("LogReg.scala:63:13") // UnrolledReduce(List(b2199, b2191),x3680,x3678,Block((x3678) => Const(())),List(List(b2266)),List(List(b2267)))
    val b2266 = CounterIter(x3679, Some(0)).name("b2266").ctrl(x3739) // b2266
    val b2267 = Const(true).name("b2267").ctrl(x3739) // b2267
    val x3681_d0 = Reg(init=Some(0.0)).name("x3681_d0").ctrl(x3739).srcCtx("LogReg.scala:53:33:dot") // x3681 = RegNew(Const(0.0))
    isAccum(x3681_d0) = false
    bufferDepthOf(x3681_d0) = 2
    val x3681_d1 = Reg(init=Some(0.0)).name("x3681_d1").ctrl(x3739).srcCtx("LogReg.scala:53:33:dot") // x3681 = RegNew(Const(0.0))
    isAccum(x3681_d1) = true
    bufferDepthOf(x3681_d1) = 1
    val x3682 = Counter(min=Const(0), max=Const(128), step=Const(1), par=1).name("x3682").ctrl(x3739).srcCtx("LogReg.scala:53:40") // CounterNew(Const(0),Const(128),Const(1),Const(1))
    val x3683 = CounterChain(List(x3682)).name("x3683").ctrl(x3739).srcCtx("LogReg.scala:55:15") // CounterChainNew(List(x3682))
    val x3698 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3683).name("x3698").ctrl(x3739).srcCtx("LogReg.scala:55:15") // UnrolledReduce(List(b2267, b2199, b2191),x3683,x3681,Block((x3681) => Const(())),List(List(b2271)),List(List(b2272)))
    val b2271 = CounterIter(x3682, None).name("b2271").ctrl(x3698) // b2271
    val b2272 = Const(true).name("b2272").ctrl(x3698) // b2272
    val x3684 = OpDef(op=BitAnd, inputs=List(b2272, b2267)).name("x3684").ctrl(x3698).srcCtx("UnrollingBase.scala:28:66") // And(b2272,b2267)
    val x3685 = OpDef(op=BitAnd, inputs=List(b2199, b2191)).name("x3685").ctrl(x3698).srcCtx("UnrollingBase.scala:28:66") // And(b2199,b2191)
    val x3686 = OpDef(op=BitAnd, inputs=List(x3684, x3685)).name("x3686").ctrl(x3698).srcCtx("UnrollingBase.scala:28:66") // And(x3684,x3685)
    val x3687 = LoadBanks(List(x3623_d1_b0), List(b2266, b2271)).name("x3687").ctrl(x3698).srcCtx("LogReg.scala:54:20") // ParSRAMLoad(x3623,List(List(b2266, b2271)),List(x3686))
    val x3688 = x3687 // x3688 = VectorApply(x3687,0)
    val x3689 = LoadBanks(List(x3597_d2_b0), List(b2271)).name("x3689").ctrl(x3698).srcCtx("LogReg.scala:54:35") // ParSRAMLoad(x3597,List(List(b2271)),List(x3686))
    val x3690 = x3689 // x3690 = VectorApply(x3689,0)
    val x3691 = OpDef(op=FltMul, inputs=List(x3688, x3690)).name("x3691").ctrl(x3698).srcCtx("LogReg.scala:54:27") // FltMul(x3688,x3690)
    val x3692 = ReadMem(x3681_d1).name("x3692").ctrl(x3698).srcCtx("LogReg.scala:55:15") // RegRead(x3681)
    val x3693 = OpDef(op=FixEql, inputs=List(b2271, Const(0))).name("x3693").ctrl(x3698).srcCtx("LogReg.scala:55:15") // FixEql(b2271,Const(0))
    val x3694 = ReduceAccumOp(op=FltAdd, input=x3691, accum=x3692).name("x3694").ctrl(x3698).srcCtx("LogReg.scala:55:19") // FltAdd(x3691,x3692)
    val x3695 = OpDef(op=BitAnd, inputs=List(b2267, b2199)).name("x3695").ctrl(x3698).srcCtx("UnrollingBase.scala:28:66") // And(b2267,b2199)
    val x3696 = OpDef(op=BitAnd, inputs=List(x3695, b2191)).name("x3696").ctrl(x3698).srcCtx("UnrollingBase.scala:28:66") // And(x3695,b2191)
    val x3697_x3681_d0 = WriteMem(x3681_d0, x3694).name("x3697_x3681_d0").ctrl(x3698).srcCtx("LogReg.scala:55:15") // RegWrite(x3681,x3694,x3696)
    antiDepsOf(x3697_x3681_d0)=List(x3692)
    val x3697_x3681_d1 = WriteMem(x3681_d1, x3694).name("x3697_x3681_d1").ctrl(x3698).srcCtx("LogReg.scala:55:15") // RegWrite(x3681,x3694,x3696)
    antiDepsOf(x3697_x3681_d1)=List(x3692)
    val x3699 = Reg(init=Some(0.0)).name("x3699").ctrl(x3739).srcCtx("LogReg.scala:56:26:sub") // x3699 = RegNew(Const(0.0))
    isAccum(x3699) = false
    bufferDepthOf(x3699) = 2
    val x3710 = UnitController(style=SeqPipe, level=InnerControl).name("x3710").ctrl(x3739).srcCtx("LogReg.scala:57:18") // UnitPipe(List(b2267, b2199, b2191),Block(x3709))
    val x3700 = OpDef(op=BitAnd, inputs=List(b2267, b2199)).name("x3700").ctrl(x3710).srcCtx("UnrollingBase.scala:28:66") // And(b2267,b2199)
    val x3701 = OpDef(op=BitAnd, inputs=List(x3700, b2191)).name("x3701").ctrl(x3710).srcCtx("UnrollingBase.scala:28:66") // And(x3700,b2191)
    val x3702 = LoadBanks(List(x3624_d0_b0), List(b2266)).name("x3702").ctrl(x3710).srcCtx("LogReg.scala:58:27") // SRAMLoad(x3624,ArrayBuffer(Const(64)),List(b2266),Const(0),x3701)
    val x3703 = ReadMem(x3681_d0).name("x3703").ctrl(x3710).srcCtx("LogReg.scala:58:45") // RegRead(x3681)
    val x3704 = OpDef(op=FltNeg, inputs=List(x3703)).name("x3704").ctrl(x3710).srcCtx("LogReg.scala:21:47") // FltNeg(x3703)
    val x3705 = OpDef(op=FltExp, inputs=List(x3704)).name("x3705").ctrl(x3710).srcCtx("LogReg.scala:21:46") // FltExp(x3704)
    val x3706 = OpDef(op=FltAdd, inputs=List(x3705, Const(1.0))).name("x3706").ctrl(x3710).srcCtx("LogReg.scala:21:51") // FltAdd(x3705,Const(1))
    val x3707 = OpDef(op=FltDiv, inputs=List(Const(1.0), x3706)).name("x3707").ctrl(x3710).srcCtx("LogReg.scala:21:41") // FltDiv(Const(1),x3706)
    val x3708 = OpDef(op=FltSub, inputs=List(x3702, x3707)).name("x3708").ctrl(x3710).srcCtx("LogReg.scala:58:32") // FltSub(x3702,x3707)
    val x3709_x3699 = WriteMem(x3699, x3708).name("x3709_x3699").ctrl(x3710).srcCtx("LogReg.scala:58:19") // RegWrite(x3699,x3708,x3701)
    val x3711_d0_b0 = SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x3711_d0_b0").ctrl(x3739).srcCtx("LogReg.scala:60:34:gradRow") // x3711 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x3711_d0_b0) = false
    bufferDepthOf(x3711_d0_b0) = 2
    staticDimsOf(x3711_d0_b0) = List(128)
    val x3712 = Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x3712").ctrl(x3739).srcCtx("LogReg.scala:61:28") // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x3713 = CounterChain(List(x3712)).name("x3713").ctrl(x3739).srcCtx("LogReg.scala:61:36") // CounterChainNew(List(x3712))
    val x3722 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3713).name("x3722").ctrl(x3739).srcCtx("LogReg.scala:61:36") // UnrolledForeach(List(b2267, b2199, b2191),x3713,Block(Const(())),List(List(b2303)),List(List(b2304)))
    val b2303 = CounterIter(x3712, None).name("b2303").ctrl(x3722) // b2303
    val b2304 = Const(true).name("b2304").ctrl(x3722) // b2304
    val x3714 = OpDef(op=BitAnd, inputs=List(b2304, b2267)).name("x3714").ctrl(x3722).srcCtx("UnrollingBase.scala:28:66") // And(b2304,b2267)
    val x3715 = OpDef(op=BitAnd, inputs=List(b2199, b2191)).name("x3715").ctrl(x3722).srcCtx("UnrollingBase.scala:28:66") // And(b2199,b2191)
    val x3716 = OpDef(op=BitAnd, inputs=List(x3714, x3715)).name("x3716").ctrl(x3722).srcCtx("UnrollingBase.scala:28:66") // And(x3714,x3715)
    val x3717 = LoadBanks(List(x3623_d0_b0), List(b2266, b2303)).name("x3717").ctrl(x3722).srcCtx("LogReg.scala:61:61") // ParSRAMLoad(x3623,List(List(b2266, b2303)),List(x3716))
    val x3718 = x3717 // x3718 = VectorApply(x3717,0)
    val x3719 = ReadMem(x3699).name("x3719").ctrl(x3722).srcCtx("LogReg.scala:61:75") // RegRead(x3699)
    val x3720 = OpDef(op=FltMul, inputs=List(x3718, x3719)).name("x3720").ctrl(x3722).srcCtx("LogReg.scala:61:69") // FltMul(x3718,x3719)
    val x3721 = StoreBanks(List(List(x3711_d0_b0)), List(b2303), x3720).name("x3721").ctrl(x3722).srcCtx("LogReg.scala:61:54") // ParSRAMStore(x3711,List(List(b2303)),List(x3720),List(x3716))
    val x3723 = Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x3723").ctrl(x3739).srcCtx("LogReg.scala:63:13") // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x3724 = CounterChain(List(x3723)).name("x3724").ctrl(x3739).srcCtx("LogReg.scala:63:13") // CounterChainNew(ArrayBuffer(x3723))
    val x3738 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3724).name("x3738").ctrl(x3739).srcCtx("LogReg.scala:63:13") // UnrolledForeach(List(),x3724,Block(Const(())),ArrayBuffer(List(b2314)),ArrayBuffer(List(b2315)))
    val b2314 = CounterIter(x3723, None).name("b2314").ctrl(x3738) // b2314
    val b2315 = Const(true).name("b2315").ctrl(x3738) // b2315
    val x3725 = OpDef(op=BitAnd, inputs=List(b2315, b2199)).name("x3725").ctrl(x3738).srcCtx("UnrollingBase.scala:28:66") // And(b2315,b2199)
    val x3726 = OpDef(op=BitAnd, inputs=List(x3725, b2191)).name("x3726").ctrl(x3738).srcCtx("UnrollingBase.scala:28:66") // And(x3725,b2191)
    val x3727 = LoadBanks(List(x3711_d0_b0), List(b2314)).name("x3727").ctrl(x3738).srcCtx("LogReg.scala:63:13") // ParSRAMLoad(x3711,List(ArrayBuffer(b2314)),List(x3726))
    val x3728 = x3727 // x3728 = VectorApply(x3727,0)
    val x3729 = LoadBanks(List(x3678_d1_b0), List(b2314)).name("x3729").ctrl(x3738).srcCtx("LogReg.scala:63:13") // ParSRAMLoad(x3678,List(ArrayBuffer(b2314)),List(x3726))
    val x3730 = x3729 // x3730 = VectorApply(x3729,0)
    val x3731 = OpDef(op=BitAnd, inputs=List(b2267, b2199)).name("x3731").ctrl(x3738).srcCtx("LogReg.scala:63:13") // And(b2267,b2199)
    val x3732 = OpDef(op=BitAnd, inputs=List(x3731, b2191)).name("x3732").ctrl(x3738).srcCtx("LogReg.scala:63:13") // And(x3731,b2191)
    val x3733 = OpDef(op=BitAnd, inputs=List(x3732, x3726)).name("x3733").ctrl(x3738).srcCtx("LogReg.scala:63:13") // And(x3732,x3726)
    val x3734 = OpDef(op=FixEql, inputs=List(b2266, Const(0))).name("x3734").ctrl(x3738).srcCtx("LogReg.scala:63:13") // FixEql(b2266,Const(0))
    val x3735 = OpDef(op=FltAdd, inputs=List(x3728, x3730)).name("x3735").ctrl(x3738).srcCtx("LogReg.scala:63:17") // FltAdd(x3728,x3730)
    val x3736 = OpDef(op=MuxOp, inputs=List(x3734, x3728, x3735)).name("x3736").ctrl(x3738).srcCtx("LogReg.scala:63:13") // Mux(x3734,x3728,x3735)
    val x3737 = StoreBanks(List(List(x3678_d0_b0), List(x3678_d1_b0)), List(b2314), x3736).name("x3737").ctrl(x3738).srcCtx("LogReg.scala:63:13") // ParSRAMStore(x3678,List(ArrayBuffer(b2314)),List(x3736),List(x3726))
    antiDepsOf(x3737)=List(x3729)
    val x3740 = Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x3740").ctrl(x3754).srcCtx("LogReg.scala:64:11") // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x3741 = CounterChain(List(x3740)).name("x3741").ctrl(x3754).srcCtx("LogReg.scala:64:11") // CounterChainNew(ArrayBuffer(x3740))
    val x3753 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3741).name("x3753").ctrl(x3754).srcCtx("LogReg.scala:64:11") // UnrolledForeach(List(),x3741,Block(Const(())),ArrayBuffer(List(b2331)),ArrayBuffer(List(b2332)))
    val b2331 = CounterIter(x3740, None).name("b2331").ctrl(x3753) // b2331
    val b2332 = Const(true).name("b2332").ctrl(x3753) // b2332
    val x3742 = OpDef(op=BitAnd, inputs=List(b2332, b2191)).name("x3742").ctrl(x3753).srcCtx("UnrollingBase.scala:28:66") // And(b2332,b2191)
    val x3743 = LoadBanks(List(x3678_d0_b0), List(b2331)).name("x3743").ctrl(x3753).srcCtx("LogReg.scala:64:11") // ParSRAMLoad(x3678,List(ArrayBuffer(b2331)),List(x3742))
    val x3744 = x3743 // x3744 = VectorApply(x3743,0)
    val x3745 = LoadBanks(List(x3619_d1_b0), List(b2331)).name("x3745").ctrl(x3753).srcCtx("LogReg.scala:64:11") // ParSRAMLoad(x3619,List(ArrayBuffer(b2331)),List(x3742))
    val x3746 = x3745 // x3746 = VectorApply(x3745,0)
    val x3747 = OpDef(op=BitAnd, inputs=List(b2199, b2191)).name("x3747").ctrl(x3753).srcCtx("LogReg.scala:64:11") // And(b2199,b2191)
    val x3748 = OpDef(op=BitAnd, inputs=List(x3747, x3742)).name("x3748").ctrl(x3753).srcCtx("LogReg.scala:64:11") // And(x3747,x3742)
    val x3749 = OpDef(op=FixEql, inputs=List(b2198, Const(0))).name("x3749").ctrl(x3753).srcCtx("LogReg.scala:64:11") // FixEql(b2198,Const(0))
    val x3750 = OpDef(op=FltAdd, inputs=List(x3744, x3746)).name("x3750").ctrl(x3753).srcCtx("LogReg.scala:64:15") // FltAdd(x3744,x3746)
    val x3751 = OpDef(op=MuxOp, inputs=List(x3749, x3744, x3750)).name("x3751").ctrl(x3753).srcCtx("LogReg.scala:64:11") // Mux(x3749,x3744,x3750)
    val x3752 = StoreBanks(List(List(x3619_d0_b0), List(x3619_d1_b0)), List(b2331), x3751).name("x3752").ctrl(x3753).srcCtx("LogReg.scala:64:11") // ParSRAMStore(x3619,List(ArrayBuffer(b2331)),List(x3751),List(x3742))
    antiDepsOf(x3752)=List(x3745)
    val x3755 = Counter(min=Const(0), max=Const(128), step=Const(1), par=1).name("x3755").ctrl(x3766).srcCtx("LogReg.scala:66:19") // CounterNew(Const(0),Const(128),Const(1),Const(1))
    val x3756 = CounterChain(List(x3755)).name("x3756").ctrl(x3766).srcCtx("LogReg.scala:66:25") // CounterChainNew(List(x3755))
    val x3765 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3756).name("x3765").ctrl(x3766).srcCtx("LogReg.scala:66:25") // UnrolledForeach(List(b2191),x3756,Block(Const(())),List(List(b2348)),List(List(b2349)))
    val b2348 = CounterIter(x3755, None).name("b2348").ctrl(x3765) // b2348
    val b2349 = Const(true).name("b2349").ctrl(x3765) // b2349
    val x3757 = OpDef(op=BitAnd, inputs=List(b2349, b2191)).name("x3757").ctrl(x3765).srcCtx("UnrollingBase.scala:28:66") // And(b2349,b2191)
    val x3758 = LoadBanks(List(x3597_d1_b0), List(b2348)).name("x3758").ctrl(x3765).srcCtx("LogReg.scala:66:50") // ParSRAMLoad(x3597,List(List(b2348)),List(x3757))
    val x3759 = x3758 // x3759 = VectorApply(x3758,0)
    val x3760 = LoadBanks(List(x3619_d0_b0), List(b2348)).name("x3760").ctrl(x3765).srcCtx("LogReg.scala:66:60") // ParSRAMLoad(x3619,List(List(b2348)),List(x3757))
    val x3761 = x3760 // x3761 = VectorApply(x3760,0)
    val x3762 = OpDef(op=FltMul, inputs=List(x3761, Const(1.0E-7))).name("x3762").ctrl(x3765).srcCtx("LogReg.scala:66:64") // FltMul(x3761,Const(0.0000001000000011686097423080354928970337))
    val x3763 = OpDef(op=FltAdd, inputs=List(x3759, x3762)).name("x3763").ctrl(x3765).srcCtx("LogReg.scala:66:54") // FltAdd(x3759,x3762)
    val x3764 = StoreBanks(List(List(x3597_d0_b0), List(x3597_d1_b0), List(x3597_d2_b0)), List(b2348), x3763).name("x3764").ctrl(x3765).srcCtx("LogReg.scala:66:42") // ParSRAMStore(x3597,List(List(b2348)),List(x3763),List(x3757))
    antiDepsOf(x3764)=List(x3758)
    val x3788 = UnitController(style=StreamPipe, level=OuterControl).name("x3788").ctrl(x3789).srcCtx("LogReg.scala:70:26") // UnitPipe(List(Const(true)),Block(Const(())))
    val b3871 = StreamOut(field="offset").name("b3871").ctrl(x3788).srcCtx("LogReg.scala:70:26") // x3767 = StreamOutNew(BurstCmdBus)
    isAccum(b3871) = false
    bufferDepthOf(b3871) = 1
    val b3872 = StreamOut(field="size").name("b3872").ctrl(x3788).srcCtx("LogReg.scala:70:26") // x3767 = StreamOutNew(BurstCmdBus)
    isAccum(b3872) = false
    bufferDepthOf(b3872) = 1
    val x3768 = StreamOut(field="data").name("x3768").ctrl(x3788).srcCtx("LogReg.scala:70:26") // x3768 = StreamOutNew(BurstFullDataBus())
    isAccum(x3768) = false
    bufferDepthOf(x3768) = 1
    val x3769 = StreamIn(field="ack").name("x3769").ctrl(x3788).srcCtx("LogReg.scala:70:26") // x3769 = StreamInNew(BurstAckBus)
    isAccum(x3769) = false
    bufferDepthOf(x3769) = 1
    val x3777 = UnitController(style=SeqPipe, level=InnerControl).name("x3777").ctrl(x3788).srcCtx("LogReg.scala:70:26") // UnitPipe(List(Const(true)),Block(x3776))
    val x3770 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x3771 = OpDef(op=FixSla, inputs=List(x3770, Const(2))).name("x3771").ctrl(x3777).srcCtx("LogReg.scala:70:26") // FixLsh(x3770,Const(2))
    val x3772 = x3771 // FixConvert(x3771,TRUE,_64,_0)
    val x3773 = DramAddress(x3591).name("x3773").ctrl(x3777).srcCtx("LogReg.scala:70:26") // GetDRAMAddress(x3591)
    val x3775_x3774 = OpDef(op=FixAdd, inputs=List(x3772, x3773)).name("x3775_x3774").ctrl(x3777).srcCtx("LogReg.scala:70:26") // FixAdd(x3772,x3773)
    // x3775 = SimpleStruct(ArrayBuffer((offset,x3774), (size,Const(512)), (isLoad,Const(false))))
    val x3776_b3873_b3871 = WriteMem(b3871, x3775_x3774).name("x3776_b3873_b3871").ctrl(x3777).srcCtx("LogReg.scala:70:26") // StreamWrite(x3767,x3775,Const(true))
    val x3776_b3874_b3872 = WriteMem(b3872, Const(512)).name("x3776_b3874_b3872").ctrl(x3777).srcCtx("LogReg.scala:70:26") // StreamWrite(x3767,x3775,Const(true))
    val x3778 = Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x3778").ctrl(x3788).srcCtx("LogReg.scala:70:26") // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x3779 = CounterChain(List(x3778)).name("x3779").ctrl(x3788).srcCtx("LogReg.scala:70:26") // CounterChainNew(List(x3778))
    val x3784 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3779).name("x3784").ctrl(x3788).srcCtx("LogReg.scala:70:26") // UnrolledForeach(List(Const(true)),x3779,Block(Const(())),List(List(b2373)),List(List(b2374)))
    val b2373 = CounterIter(x3778, None).name("b2373").ctrl(x3784) // b2373
    val b2374 = Const(true).name("b2374").ctrl(x3784) // b2374
    val x3780 = LoadBanks(List(x3597_d0_b0), List(b2373)).name("x3780").ctrl(x3784).srcCtx("LogReg.scala:70:26") // ParSRAMLoad(x3597,List(List(b2373)),List(b2374))
    val x3782_x3781 = x3780 // x3781 = VectorApply(x3780,0)
    // x3782 = SimpleStruct(ArrayBuffer((_1,x3781), (_2,Const(true))))
    val x3783_x3783_x3768 = WriteMem(x3768, x3782_x3781).name("x3783_x3783_x3768").ctrl(x3784).srcCtx("LogReg.scala:70:26") // ParStreamWrite(x3768,List(x3782),List(b2374))
    val x3785 = FringeDenseStore(dram=List(x3591), cmdStream=List(b3871, b3872), dataStream=List(x3768), ackStream=List(x3769)).name("x3785").ctrl(x3788).srcCtx("LogReg.scala:70:26") // FringeDenseStore(x3591,x3767,x3768,x3769)
    val x3787 = UnitController(style=SeqPipe, level=InnerControl).name("x3787").ctrl(x3788).srcCtx("LogReg.scala:70:26") // UnitPipe(List(Const(true)),Block(Const(())))
    val x3786_x3786 = ReadMem(x3769).name("x3786_x3786").ctrl(x3787).srcCtx("LogReg.scala:70:26") // StreamRead(x3769,Const(true))
    
  }
}
