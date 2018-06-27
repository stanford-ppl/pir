import pir._
import pir.node._
import arch._
import prism.enums._

object LogReg extends PIRApp {
  def main(implicit design:PIRDesign) = {
    import design.pirmeta._
    val x3573 = ArgIn(init=0).name("x3573").ctrl(top).srcCtx("LogReg.scala:25:22:iters") // ArgInNew(Const(0))
    isAccum(x3573) = false
    bufferDepthOf(x3573) = 1
    boundOf(x3573) = 1
    val x3574 = ArgIn(init=0).name("x3574").ctrl(top).srcCtx("LogReg.scala:26:22:N") // ArgInNew(Const(0))
    isAccum(x3574) = false
    bufferDepthOf(x3574) = 1
    boundOf(x3574) = 1024
    val x3577 = ReadMem(x3574).name("x3577").ctrl(top).srcCtx("LogReg.scala:34:21") // RegRead(x3574)
    val x3578 = DRAM(dims=List(x3577, Const(192))).name("x3578").ctrl(top).srcCtx("LogReg.scala:34:20:x") // x3578 = DRAMNew(ArrayBuffer(x3577, Const(192)),Const(0.0))
    val x3579 = ReadMem(x3574).name("x3579").ctrl(top).srcCtx("LogReg.scala:35:21") // RegRead(x3574)
    val x3580 = DRAM(dims=List(x3579)).name("x3580").ctrl(top).srcCtx("LogReg.scala:35:20:y") // x3580 = DRAMNew(ArrayBuffer(x3579),Const(0.0))
    val x3581 = DRAM(dims=List(Const(192))).name("x3581").ctrl(top).srcCtx("LogReg.scala:36:24:theta") // x3581 = DRAMNew(ArrayBuffer(Const(192)),Const(0.0))
    val x3777 = UnitController(style=SeqPipe, level=OuterControl).name("x3777").ctrl(top).srcCtx("LogReg.scala:75:11") // Hwblock(Block(Const(())),false)
    val x3587_d0_b0 = SRAM(size=192, banking=Strided(banks=16, stride=1)).name("x3587_d0_b0").ctrl(x3777).srcCtx("LogReg.scala:76:27:btheta") // x3587 = SRAMNew(ArrayBuffer(Const(192)))
    isAccum(x3587_d0_b0) = false
    bufferDepthOf(x3587_d0_b0) = 1
    val x3587_d1_b0 = SRAM(size=192, banking=Strided(banks=16, stride=1)).name("x3587_d1_b0").ctrl(x3777).srcCtx("LogReg.scala:76:27:btheta") // x3587 = SRAMNew(ArrayBuffer(Const(192)))
    isAccum(x3587_d1_b0) = true
    bufferDepthOf(x3587_d1_b0) = 1
    val x3587_d2_b0 = SRAM(size=192, banking=Strided(banks=16, stride=1)).name("x3587_d2_b0").ctrl(x3777).srcCtx("LogReg.scala:76:27:btheta") // x3587 = SRAMNew(ArrayBuffer(Const(192)))
    isAccum(x3587_d2_b0) = false
    bufferDepthOf(x3587_d2_b0) = 1
    val x3605 = UnitController(style=StreamPipe, level=OuterControl).name("x3605").ctrl(x3777).srcCtx("LogReg.scala:78:14") // UnitPipe(List(Const(true)),Block(Const(())))
    val b3847 = StreamOut(field="offset").name("b3847").ctrl(x3605).srcCtx("LogReg.scala:78:14") // x3588 = StreamOutNew(BurstCmdBus)
    isAccum(b3847) = false
    bufferDepthOf(b3847) = 1
    val b3848 = StreamOut(field="size").name("b3848").ctrl(x3605).srcCtx("LogReg.scala:78:14") // x3588 = StreamOutNew(BurstCmdBus)
    isAccum(b3848) = false
    bufferDepthOf(b3848) = 1
    val x3589 = StreamIn(field="data").name("x3589").ctrl(x3605).srcCtx("LogReg.scala:78:14") // x3589 = StreamInNew(BurstDataBus())
    isAccum(x3589) = false
    bufferDepthOf(x3589) = 1
    val x3597 = UnitController(style=SeqPipe, level=InnerControl).name("x3597").ctrl(x3605).srcCtx("LogReg.scala:78:14") // UnitPipe(List(Const(true)),Block(x3596))
    val x3590 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x3591 = OpDef(op=FixSla, inputs=List(x3590, Const(2))).name("x3591").ctrl(x3597).srcCtx("LogReg.scala:78:14") // FixLsh(x3590,Const(2))
    val x3592 = x3591 // FixConvert(x3591,TRUE,_64,_0)
    val x3593 = DramAddress(x3581).name("x3593").ctrl(x3597).srcCtx("LogReg.scala:78:14") // GetDRAMAddress(x3581)
    val x3595_x3594 = OpDef(op=FixAdd, inputs=List(x3592, x3593)).name("x3595_x3594").ctrl(x3597).srcCtx("LogReg.scala:78:14") // FixAdd(x3592,x3593)
    // x3595 = SimpleStruct(ArrayBuffer((offset,x3594), (size,Const(768)), (isLoad,Const(true))))
    val x3596_b3849_b3847 = WriteMem(b3847, x3595_x3594).name("x3596_b3849_b3847").ctrl(x3597).srcCtx("LogReg.scala:78:14") // StreamWrite(x3588,x3595,Const(true))
    val x3596_b3850_b3848 = WriteMem(b3848, Const(768)).name("x3596_b3850_b3848").ctrl(x3597).srcCtx("LogReg.scala:78:14") // StreamWrite(x3588,x3595,Const(true))
    val x3598 = FringeDenseLoad(dram=List(x3581), cmdStream=List(b3847, b3848), dataStream=List(x3589)).name("x3598").ctrl(x3605).srcCtx("LogReg.scala:78:14") // FringeDenseLoad(x3581,x3588,x3589)
    val x3599 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16).name("x3599").ctrl(x3605).srcCtx("LogReg.scala:78:14") // CounterNew(Const(0),Const(192),Const(1),Const(16))
    val x3600 = CounterChain(List(x3599)).name("x3600").ctrl(x3605).srcCtx("LogReg.scala:78:14") // CounterChainNew(List(x3599))
    val x3604 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3600).name("x3604").ctrl(x3605).srcCtx("LogReg.scala:78:14") // UnrolledForeach(List(Const(true)),x3600,Block(Const(())),List(List(b2180)),List(List(b2181)))
    val b2180 = CounterIter(x3599, None).name("b2180").ctrl(x3604) // b2180
    val b2181 = Const(true).name("b2181").ctrl(x3604) // b2181
    val x3601_x3601 = ReadMem(x3589).name("x3601_x3601").ctrl(x3604).srcCtx("LogReg.scala:78:14") // ParStreamRead(x3589,List(b2181))
    val x3602_x3602 = x3601_x3601 // x3602 = VectorApply(x3601,0)
    val x3603 = StoreBanks(List(x3587_d0_b0, x3587_d1_b0, x3587_d2_b0), List(b2180), x3602_x3602).name("x3603").ctrl(x3604).srcCtx("LogReg.scala:78:14") // ParSRAMStore(x3587,List(List(b2180)),List(x3602),List(b2181))
    val x3606 = ReadMem(x3573).name("x3606").ctrl(x3777).srcCtx("LogReg.scala:80:26") // RegRead(x3573)
    val x3607 = Counter(min=Const(0), max=x3606, step=Const(1), par=1).name("x3607").ctrl(x3777).srcCtx("LogReg.scala:80:32") // CounterNew(Const(0),x3606,Const(1),Const(1))
    val x3608 = CounterChain(List(x3607)).name("x3608").ctrl(x3777).srcCtx("LogReg.scala:80:38") // CounterChainNew(List(x3607))
    val x3754 = LoopController(style=SeqPipe, level=OuterControl, cchain=x3608).name("x3754").ctrl(x3777).srcCtx("LogReg.scala:80:38") // UnrolledForeach(List(Const(true)),x3608,Block(Const(())),List(List(b2190)),List(List(b2191)))
    val b2190 = CounterIter(x3607, Some(0)).name("b2190").ctrl(x3754) // b2190
    val b2191 = Const(true).name("b2191").ctrl(x3754) // b2191
    val x3609_d0_b0 = SRAM(size=192, banking=Strided(banks=16, stride=1)).name("x3609_d0_b0").ctrl(x3754).srcCtx("LogReg.scala:82:37:grad") // x3609 = SRAMNew(ArrayBuffer(Const(192)))
    isAccum(x3609_d0_b0) = false
    bufferDepthOf(x3609_d0_b0) = 1
    val x3609_d1_b0 = SRAM(size=192, banking=Strided(banks=16, stride=1)).name("x3609_d1_b0").ctrl(x3754).srcCtx("LogReg.scala:82:37:grad") // x3609 = SRAMNew(ArrayBuffer(Const(192)))
    isAccum(x3609_d1_b0) = true
    bufferDepthOf(x3609_d1_b0) = 1
    val x3610 = ReadMem(x3574).name("x3610").ctrl(x3754).srcCtx("LogReg.scala:82:49") // RegRead(x3574)
    val x3611 = Counter(min=Const(0), max=x3610, step=Const(64), par=1).name("x3611").ctrl(x3754).srcCtx("LogReg.scala:82:57") // CounterNew(Const(0),x3610,Const(64),Const(1))
    val x3612 = CounterChain(List(x3611)).name("x3612").ctrl(x3754).srcCtx("LogReg.scala:101:11") // CounterChainNew(List(x3611))
    val x3742 = LoopController(style=MetaPipe, level=OuterControl, cchain=x3612).name("x3742").ctrl(x3754).srcCtx("LogReg.scala:101:11") // UnrolledReduce(List(b2191),x3612,x3609,Block((x3609) => Const(())),List(List(b2198)),List(List(b2199)))
    val b2198 = CounterIter(x3611, Some(0)).name("b2198").ctrl(x3742) // b2198
    val b2199 = Const(true).name("b2199").ctrl(x3742) // b2199
    val x3613_d0_b0 = SRAM(size=12288, banking=Strided(banks=16, stride=1)).name("x3613_d0_b0").ctrl(x3742).srcCtx("LogReg.scala:83:30:xTile") // x3613 = SRAMNew(ArrayBuffer(Const(64), Const(192)))
    isAccum(x3613_d0_b0) = false
    bufferDepthOf(x3613_d0_b0) = 2
    val x3613_d1_b0 = SRAM(size=12288, banking=Strided(banks=16, stride=1)).name("x3613_d1_b0").ctrl(x3742).srcCtx("LogReg.scala:83:30:xTile") // x3613 = SRAMNew(ArrayBuffer(Const(64), Const(192)))
    isAccum(x3613_d1_b0) = false
    bufferDepthOf(x3613_d1_b0) = 2
    val x3614_d0_b0 = SRAM(size=64, banking=Strided(banks=16, stride=1)).name("x3614_d0_b0").ctrl(x3742).srcCtx("LogReg.scala:84:30:yTile") // x3614 = SRAMNew(ArrayBuffer(Const(64)))
    isAccum(x3614_d0_b0) = false
    bufferDepthOf(x3614_d0_b0) = 2
    val x3667 = UnitController(style=ForkJoin, level=OuterControl).name("x3667").ctrl(x3742).srcCtx("LogReg.scala:85:20") // ParallelPipe(List(b2199, b2191),Block(Const(())))
    val x3616 = UnitController(style=SeqPipe, level=InnerControl).name("x3616").ctrl(x3667).srcCtx("LogReg.scala:85:20") // UnitPipe(List(b2199, b2191),Block(Const(())))
    val x3615 = OpDef(op=FixAdd, inputs=List(b2198, Const(64))).name("x3615").ctrl(x3616).srcCtx("LogReg.scala:86:30") // FixAdd(b2198,Const(64))
    val x3617 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1).name("x3617").ctrl(x3667).srcCtx("LogReg.scala:86:19") // CounterNew(Const(0),Const(64),Const(1),Const(1))
    val x3618 = CounterChain(List(x3617)).name("x3618").ctrl(x3667).srcCtx("LogReg.scala:86:19") // CounterChainNew(List(x3617))
    val x3645 = LoopController(style=StreamPipe, level=OuterControl, cchain=x3618).name("x3645").ctrl(x3667).srcCtx("LogReg.scala:86:19") // UnrolledForeach(List(b2199, b2191),x3618,Block(Const(())),List(List(b2206)),List(List(b2207)))
    val b2206 = CounterIter(x3617, Some(0)).name("b2206").ctrl(x3645) // b2206
    val b2207 = Const(true).name("b2207").ctrl(x3645) // b2207
    val b3851 = StreamOut(field="offset").name("b3851").ctrl(x3645).srcCtx("LogReg.scala:86:19") // x3619 = StreamOutNew(BurstCmdBus)
    isAccum(b3851) = false
    bufferDepthOf(b3851) = 1
    val b3852 = StreamOut(field="size").name("b3852").ctrl(x3645).srcCtx("LogReg.scala:86:19") // x3619 = StreamOutNew(BurstCmdBus)
    isAccum(b3852) = false
    bufferDepthOf(b3852) = 1
    val x3620 = StreamIn(field="data").name("x3620").ctrl(x3645).srcCtx("LogReg.scala:86:19") // x3620 = StreamInNew(BurstDataBus())
    isAccum(x3620) = false
    bufferDepthOf(x3620) = 1
    val x3634 = UnitController(style=SeqPipe, level=InnerControl).name("x3634").ctrl(x3645).srcCtx("LogReg.scala:86:19") // UnitPipe(List(b2207, b2199, b2191),Block(x3633))
    val x3621 = OpDef(op=FixAdd, inputs=List(b2198, b2206)).name("x3621").ctrl(x3634).srcCtx("LogReg.scala:86:19") // FixAdd(b2198,b2206)
    val x3622 = x3621 // FixConvert(x3621,TRUE,_32,_0) (Same Type. No op)
    val x3623 = OpDef(op=FixMul, inputs=List(x3622, Const(192))).name("x3623").ctrl(x3634).srcCtx("LogReg.scala:86:19") // FixMul(x3622,Const(192))
    val x3624 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x3625 = OpDef(op=FixAdd, inputs=List(x3623, x3624)).name("x3625").ctrl(x3634).srcCtx("LogReg.scala:86:19") // FixAdd(x3623,x3624)
    val x3626 = OpDef(op=FixSla, inputs=List(x3625, Const(2))).name("x3626").ctrl(x3634).srcCtx("LogReg.scala:86:19") // FixLsh(x3625,Const(2))
    val x3627 = x3626 // FixConvert(x3626,TRUE,_64,_0)
    val x3628 = DramAddress(x3578).name("x3628").ctrl(x3634).srcCtx("LogReg.scala:86:19") // GetDRAMAddress(x3578)
    val x3630_x3629 = OpDef(op=FixAdd, inputs=List(x3627, x3628)).name("x3630_x3629").ctrl(x3634).srcCtx("LogReg.scala:86:19") // FixAdd(x3627,x3628)
    // x3630 = SimpleStruct(ArrayBuffer((offset,x3629), (size,Const(768)), (isLoad,Const(true))))
    val x3631 = OpDef(op=BitAnd, inputs=List(b2207, b2199)).name("x3631").ctrl(x3634).srcCtx("UnrollingBase.scala:28:66") // And(b2207,b2199)
    val x3632 = OpDef(op=BitAnd, inputs=List(x3631, b2191)).name("x3632").ctrl(x3634).srcCtx("UnrollingBase.scala:28:66") // And(x3631,b2191)
    val x3633_b3853_b3851 = WriteMem(b3851, x3630_x3629).name("x3633_b3853_b3851").ctrl(x3634).srcCtx("LogReg.scala:86:19") // StreamWrite(x3619,x3630,x3632)
    val x3633_b3854_b3852 = WriteMem(b3852, Const(768)).name("x3633_b3854_b3852").ctrl(x3634).srcCtx("LogReg.scala:86:19") // StreamWrite(x3619,x3630,x3632)
    val x3635 = FringeDenseLoad(dram=List(x3578), cmdStream=List(b3851, b3852), dataStream=List(x3620)).name("x3635").ctrl(x3645).srcCtx("LogReg.scala:86:19") // FringeDenseLoad(x3578,x3619,x3620)
    val x3636 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16).name("x3636").ctrl(x3645).srcCtx("LogReg.scala:86:19") // CounterNew(Const(0),Const(192),Const(1),Const(16))
    val x3637 = CounterChain(List(x3636)).name("x3637").ctrl(x3645).srcCtx("LogReg.scala:86:19") // CounterChainNew(List(x3636))
    val x3644 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3637).name("x3644").ctrl(x3645).srcCtx("LogReg.scala:86:19") // UnrolledForeach(List(b2207, b2199, b2191),x3637,Block(Const(())),List(List(b2227)),List(List(b2228)))
    val b2227 = CounterIter(x3636, None).name("b2227").ctrl(x3644) // b2227
    val b2228 = Const(true).name("b2228").ctrl(x3644) // b2228
    val x3638 = OpDef(op=BitAnd, inputs=List(b2228, b2207)).name("x3638").ctrl(x3644).srcCtx("UnrollingBase.scala:28:66") // And(b2228,b2207)
    val x3639 = OpDef(op=BitAnd, inputs=List(b2199, b2191)).name("x3639").ctrl(x3644).srcCtx("UnrollingBase.scala:28:66") // And(b2199,b2191)
    val x3640 = OpDef(op=BitAnd, inputs=List(x3638, x3639)).name("x3640").ctrl(x3644).srcCtx("UnrollingBase.scala:28:66") // And(x3638,x3639)
    val x3641_x3641 = ReadMem(x3620).name("x3641_x3641").ctrl(x3644).srcCtx("LogReg.scala:86:19") // ParStreamRead(x3620,List(x3640))
    val x3642_x3642 = x3641_x3641 // x3642 = VectorApply(x3641,0)
    val x3643 = StoreBanks(List(x3613_d0_b0, x3613_d1_b0), List(b2206, b2227), x3642_x3642).name("x3643").ctrl(x3644).srcCtx("LogReg.scala:86:19") // ParSRAMStore(x3613,List(List(b2206, b2227)),List(x3642),List(x3640))
    val x3666 = UnitController(style=StreamPipe, level=OuterControl).name("x3666").ctrl(x3667).srcCtx("LogReg.scala:87:19") // UnitPipe(List(b2199, b2191),Block(Const(())))
    val b3855 = StreamOut(field="offset").name("b3855").ctrl(x3666).srcCtx("LogReg.scala:87:19") // x3646 = StreamOutNew(BurstCmdBus)
    isAccum(b3855) = false
    bufferDepthOf(b3855) = 1
    val b3856 = StreamOut(field="size").name("b3856").ctrl(x3666).srcCtx("LogReg.scala:87:19") // x3646 = StreamOutNew(BurstCmdBus)
    isAccum(b3856) = false
    bufferDepthOf(b3856) = 1
    val x3647 = StreamIn(field="data").name("x3647").ctrl(x3666).srcCtx("LogReg.scala:87:19") // x3647 = StreamInNew(BurstDataBus())
    isAccum(x3647) = false
    bufferDepthOf(x3647) = 1
    val x3656 = UnitController(style=SeqPipe, level=InnerControl).name("x3656").ctrl(x3666).srcCtx("LogReg.scala:87:19") // UnitPipe(List(b2199, b2191),Block(x3655))
    val x3648 = b2198 // FixConvert(b2198,TRUE,_32,_0) (Same Type. No op)
    val x3649 = OpDef(op=FixSla, inputs=List(x3648, Const(2))).name("x3649").ctrl(x3656).srcCtx("LogReg.scala:87:19") // FixLsh(x3648,Const(2))
    val x3650 = x3649 // FixConvert(x3649,TRUE,_64,_0)
    val x3651 = DramAddress(x3580).name("x3651").ctrl(x3656).srcCtx("LogReg.scala:87:19") // GetDRAMAddress(x3580)
    val x3653_x3652 = OpDef(op=FixAdd, inputs=List(x3650, x3651)).name("x3653_x3652").ctrl(x3656).srcCtx("LogReg.scala:87:19") // FixAdd(x3650,x3651)
    // x3653 = SimpleStruct(ArrayBuffer((offset,x3652), (size,Const(256)), (isLoad,Const(true))))
    val x3654 = OpDef(op=BitAnd, inputs=List(b2199, b2191)).name("x3654").ctrl(x3656).srcCtx("UnrollingBase.scala:28:66") // And(b2199,b2191)
    val x3655_b3857_b3855 = WriteMem(b3855, x3653_x3652).name("x3655_b3857_b3855").ctrl(x3656).srcCtx("LogReg.scala:87:19") // StreamWrite(x3646,x3653,x3654)
    val x3655_b3858_b3856 = WriteMem(b3856, Const(256)).name("x3655_b3858_b3856").ctrl(x3656).srcCtx("LogReg.scala:87:19") // StreamWrite(x3646,x3653,x3654)
    val x3657 = FringeDenseLoad(dram=List(x3580), cmdStream=List(b3855, b3856), dataStream=List(x3647)).name("x3657").ctrl(x3666).srcCtx("LogReg.scala:87:19") // FringeDenseLoad(x3580,x3646,x3647)
    val x3658 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x3658").ctrl(x3666).srcCtx("LogReg.scala:87:19") // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x3659 = CounterChain(List(x3658)).name("x3659").ctrl(x3666).srcCtx("LogReg.scala:87:19") // CounterChainNew(List(x3658))
    val x3665 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3659).name("x3665").ctrl(x3666).srcCtx("LogReg.scala:87:19") // UnrolledForeach(List(b2199, b2191),x3659,Block(Const(())),List(List(b2251)),List(List(b2252)))
    val b2251 = CounterIter(x3658, None).name("b2251").ctrl(x3665) // b2251
    val b2252 = Const(true).name("b2252").ctrl(x3665) // b2252
    val x3660 = OpDef(op=BitAnd, inputs=List(b2252, b2199)).name("x3660").ctrl(x3665).srcCtx("UnrollingBase.scala:28:66") // And(b2252,b2199)
    val x3661 = OpDef(op=BitAnd, inputs=List(x3660, b2191)).name("x3661").ctrl(x3665).srcCtx("UnrollingBase.scala:28:66") // And(x3660,b2191)
    val x3662_x3662 = ReadMem(x3647).name("x3662_x3662").ctrl(x3665).srcCtx("LogReg.scala:87:19") // ParStreamRead(x3647,List(x3661))
    val x3663_x3663 = x3662_x3662 // x3663 = VectorApply(x3662,0)
    val x3664 = StoreBanks(List(x3614_d0_b0), List(b2251), x3663_x3663).name("x3664").ctrl(x3665).srcCtx("LogReg.scala:87:19") // ParSRAMStore(x3614,List(List(b2251)),List(x3663),List(x3661))
    val x3668_d0_b0 = SRAM(size=192, banking=Strided(banks=16, stride=1)).name("x3668_d0_b0").ctrl(x3742).srcCtx("LogReg.scala:89:28") // x3668 = SRAMNew(ArrayBuffer(Const(192)))
    isAccum(x3668_d0_b0) = false
    bufferDepthOf(x3668_d0_b0) = 2
    val x3668_d1_b0 = SRAM(size=192, banking=Strided(banks=16, stride=1)).name("x3668_d1_b0").ctrl(x3742).srcCtx("LogReg.scala:89:28") // x3668 = SRAMNew(ArrayBuffer(Const(192)))
    isAccum(x3668_d1_b0) = true
    bufferDepthOf(x3668_d1_b0) = 1
    val x3669 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1).name("x3669").ctrl(x3742).srcCtx("LogReg.scala:89:48") // CounterNew(Const(0),Const(64),Const(1),Const(1))
    val x3670 = CounterChain(List(x3669)).name("x3670").ctrl(x3742).srcCtx("LogReg.scala:100:13") // CounterChainNew(List(x3669))
    val x3728 = LoopController(style=MetaPipe, level=OuterControl, cchain=x3670).name("x3728").ctrl(x3742).srcCtx("LogReg.scala:100:13") // UnrolledReduce(List(b2199, b2191),x3670,x3668,Block((x3668) => Const(())),List(List(b2266)),List(List(b2267)))
    val b2266 = CounterIter(x3669, Some(0)).name("b2266").ctrl(x3728) // b2266
    val b2267 = Const(true).name("b2267").ctrl(x3728) // b2267
    val x3671_d0 = Reg(init=Some(0.0)).name("x3671_d0").ctrl(x3728).srcCtx("LogReg.scala:90:33:dot") // x3671 = RegNew(Const(0.0))
    isAccum(x3671_d0) = false
    bufferDepthOf(x3671_d0) = 2
    val x3671_d1 = Reg(init=Some(0.0)).name("x3671_d1").ctrl(x3728).srcCtx("LogReg.scala:90:33:dot") // x3671 = RegNew(Const(0.0))
    isAccum(x3671_d1) = true
    bufferDepthOf(x3671_d1) = 1
    val x3672 = Counter(min=Const(0), max=Const(192), step=Const(1), par=1).name("x3672").ctrl(x3728).srcCtx("LogReg.scala:90:40") // CounterNew(Const(0),Const(192),Const(1),Const(1))
    val x3673 = CounterChain(List(x3672)).name("x3673").ctrl(x3728).srcCtx("LogReg.scala:92:15") // CounterChainNew(List(x3672))
    val x3688 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3673).name("x3688").ctrl(x3728).srcCtx("LogReg.scala:92:15") // UnrolledReduce(List(b2267, b2199, b2191),x3673,x3671,Block((x3671) => Const(())),List(List(b2271)),List(List(b2272)))
    val b2271 = CounterIter(x3672, None).name("b2271").ctrl(x3688) // b2271
    val b2272 = Const(true).name("b2272").ctrl(x3688) // b2272
    val x3674 = OpDef(op=BitAnd, inputs=List(b2272, b2267)).name("x3674").ctrl(x3688).srcCtx("UnrollingBase.scala:28:66") // And(b2272,b2267)
    val x3675 = OpDef(op=BitAnd, inputs=List(b2199, b2191)).name("x3675").ctrl(x3688).srcCtx("UnrollingBase.scala:28:66") // And(b2199,b2191)
    val x3676 = OpDef(op=BitAnd, inputs=List(x3674, x3675)).name("x3676").ctrl(x3688).srcCtx("UnrollingBase.scala:28:66") // And(x3674,x3675)
    val x3677 = LoadBanks(List(x3613_d1_b0), List(b2266, b2271)).name("x3677").ctrl(x3688).srcCtx("LogReg.scala:91:20") // ParSRAMLoad(x3613,List(List(b2266, b2271)),List(x3676))
    val x3678 = x3677 // x3678 = VectorApply(x3677,0)
    val x3679 = LoadBanks(List(x3587_d2_b0), List(b2271)).name("x3679").ctrl(x3688).srcCtx("LogReg.scala:91:35") // ParSRAMLoad(x3587,List(List(b2271)),List(x3676))
    val x3680 = x3679 // x3680 = VectorApply(x3679,0)
    val x3681 = OpDef(op=FltMul, inputs=List(x3678, x3680)).name("x3681").ctrl(x3688).srcCtx("LogReg.scala:91:27") // FltMul(x3678,x3680)
    val x3682 = ReadMem(x3671_d1).name("x3682").ctrl(x3688).srcCtx("LogReg.scala:92:15") // RegRead(x3671)
    val x3683 = OpDef(op=FixEql, inputs=List(b2271, Const(0))).name("x3683").ctrl(x3688).srcCtx("LogReg.scala:92:15") // FixEql(b2271,Const(0))
    val x3684 = ReduceAccumOp(op=FltAdd, input=x3681, accum=x3682).name("x3684").ctrl(x3688).srcCtx("LogReg.scala:92:19") // FltAdd(x3681,x3682)
    val x3685 = OpDef(op=BitAnd, inputs=List(b2267, b2199)).name("x3685").ctrl(x3688).srcCtx("UnrollingBase.scala:28:66") // And(b2267,b2199)
    val x3686 = OpDef(op=BitAnd, inputs=List(x3685, b2191)).name("x3686").ctrl(x3688).srcCtx("UnrollingBase.scala:28:66") // And(x3685,b2191)
    val x3687_x3671_d0 = WriteMem(x3671_d0, x3684).name("x3687_x3671_d0").ctrl(x3688).srcCtx("LogReg.scala:92:15") // RegWrite(x3671,x3684,x3686)
    val x3687_x3671_d1 = WriteMem(x3671_d1, x3684).name("x3687_x3671_d1").ctrl(x3688).srcCtx("LogReg.scala:92:15") // RegWrite(x3671,x3684,x3686)
    val x3689 = Reg(init=Some(0.0)).name("x3689").ctrl(x3728).srcCtx("LogReg.scala:93:26:sub") // x3689 = RegNew(Const(0.0))
    isAccum(x3689) = false
    bufferDepthOf(x3689) = 2
    val x3700 = UnitController(style=SeqPipe, level=InnerControl).name("x3700").ctrl(x3728).srcCtx("LogReg.scala:94:18") // UnitPipe(List(b2267, b2199, b2191),Block(x3699))
    val x3690 = OpDef(op=BitAnd, inputs=List(b2267, b2199)).name("x3690").ctrl(x3700).srcCtx("UnrollingBase.scala:28:66") // And(b2267,b2199)
    val x3691 = OpDef(op=BitAnd, inputs=List(x3690, b2191)).name("x3691").ctrl(x3700).srcCtx("UnrollingBase.scala:28:66") // And(x3690,b2191)
    val x3692 = LoadBanks(List(x3614_d0_b0), List(b2266)).name("x3692").ctrl(x3700).srcCtx("LogReg.scala:95:27") // SRAMLoad(x3614,ArrayBuffer(Const(64)),List(b2266),Const(0),x3691)
    val x3693 = ReadMem(x3671_d0).name("x3693").ctrl(x3700).srcCtx("LogReg.scala:95:45") // RegRead(x3671)
    val x3694 = OpDef(op=FltNeg, inputs=List(x3693)).name("x3694").ctrl(x3700).srcCtx("LogReg.scala:21:47") // FltNeg(x3693)
    val x3695 = OpDef(op=FltExp, inputs=List(x3694)).name("x3695").ctrl(x3700).srcCtx("LogReg.scala:21:46") // FltExp(x3694)
    val x3696 = OpDef(op=FltAdd, inputs=List(x3695, Const(1.0))).name("x3696").ctrl(x3700).srcCtx("LogReg.scala:21:51") // FltAdd(x3695,Const(1))
    val x3697 = OpDef(op=FltDiv, inputs=List(Const(1.0), x3696)).name("x3697").ctrl(x3700).srcCtx("LogReg.scala:21:41") // FltDiv(Const(1),x3696)
    val x3698 = OpDef(op=FltSub, inputs=List(x3692, x3697)).name("x3698").ctrl(x3700).srcCtx("LogReg.scala:95:32") // FltSub(x3692,x3697)
    val x3699_x3689 = WriteMem(x3689, x3698).name("x3699_x3689").ctrl(x3700).srcCtx("LogReg.scala:95:19") // RegWrite(x3689,x3698,x3691)
    val x3701_d0_b0 = SRAM(size=192, banking=Strided(banks=16, stride=1)).name("x3701_d0_b0").ctrl(x3728).srcCtx("LogReg.scala:97:34:gradRow") // x3701 = SRAMNew(ArrayBuffer(Const(192)))
    isAccum(x3701_d0_b0) = false
    bufferDepthOf(x3701_d0_b0) = 2
    val x3702 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16).name("x3702").ctrl(x3728).srcCtx("LogReg.scala:98:28") // CounterNew(Const(0),Const(192),Const(1),Const(16))
    val x3703 = CounterChain(List(x3702)).name("x3703").ctrl(x3728).srcCtx("LogReg.scala:98:36") // CounterChainNew(List(x3702))
    val x3712 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3703).name("x3712").ctrl(x3728).srcCtx("LogReg.scala:98:36") // UnrolledForeach(List(b2267, b2199, b2191),x3703,Block(Const(())),List(List(b2303)),List(List(b2304)))
    val b2303 = CounterIter(x3702, None).name("b2303").ctrl(x3712) // b2303
    val b2304 = Const(true).name("b2304").ctrl(x3712) // b2304
    val x3704 = OpDef(op=BitAnd, inputs=List(b2304, b2267)).name("x3704").ctrl(x3712).srcCtx("UnrollingBase.scala:28:66") // And(b2304,b2267)
    val x3705 = OpDef(op=BitAnd, inputs=List(b2199, b2191)).name("x3705").ctrl(x3712).srcCtx("UnrollingBase.scala:28:66") // And(b2199,b2191)
    val x3706 = OpDef(op=BitAnd, inputs=List(x3704, x3705)).name("x3706").ctrl(x3712).srcCtx("UnrollingBase.scala:28:66") // And(x3704,x3705)
    val x3707 = LoadBanks(List(x3613_d0_b0), List(b2266, b2303)).name("x3707").ctrl(x3712).srcCtx("LogReg.scala:98:61") // ParSRAMLoad(x3613,List(List(b2266, b2303)),List(x3706))
    val x3708 = x3707 // x3708 = VectorApply(x3707,0)
    val x3709 = ReadMem(x3689).name("x3709").ctrl(x3712).srcCtx("LogReg.scala:98:75") // RegRead(x3689)
    val x3710 = OpDef(op=FltMul, inputs=List(x3708, x3709)).name("x3710").ctrl(x3712).srcCtx("LogReg.scala:98:69") // FltMul(x3708,x3709)
    val x3711 = StoreBanks(List(x3701_d0_b0), List(b2303), x3710).name("x3711").ctrl(x3712).srcCtx("LogReg.scala:98:54") // ParSRAMStore(x3701,List(List(b2303)),List(x3710),List(x3706))
    val x3713 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16).name("x3713").ctrl(x3728).srcCtx("LogReg.scala:100:13") // CounterNew(Const(0),Const(192),Const(1),Const(16))
    val x3714 = CounterChain(List(x3713)).name("x3714").ctrl(x3728).srcCtx("LogReg.scala:100:13") // CounterChainNew(ArrayBuffer(x3713))
    val x3727 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3714).name("x3727").ctrl(x3728).srcCtx("LogReg.scala:100:13") // UnrolledForeach(List(),x3714,Block(Const(())),ArrayBuffer(List(b2314)),ArrayBuffer(List(b2315)))
    val b2314 = CounterIter(x3713, None).name("b2314").ctrl(x3727) // b2314
    val b2315 = Const(true).name("b2315").ctrl(x3727) // b2315
    val x3715 = OpDef(op=BitAnd, inputs=List(b2315, b2199)).name("x3715").ctrl(x3727).srcCtx("UnrollingBase.scala:28:66") // And(b2315,b2199)
    val x3716 = OpDef(op=BitAnd, inputs=List(x3715, b2191)).name("x3716").ctrl(x3727).srcCtx("UnrollingBase.scala:28:66") // And(x3715,b2191)
    val x3717 = LoadBanks(List(x3701_d0_b0), List(b2314)).name("x3717").ctrl(x3727).srcCtx("LogReg.scala:100:13") // ParSRAMLoad(x3701,List(ArrayBuffer(b2314)),List(x3716))
    val x3718 = x3717 // x3718 = VectorApply(x3717,0)
    val x3719 = LoadBanks(List(x3668_d1_b0), List(b2314)).name("x3719").ctrl(x3727).srcCtx("LogReg.scala:100:13") // ParSRAMLoad(x3668,List(ArrayBuffer(b2314)),List(x3716))
    val x3720 = x3719 // x3720 = VectorApply(x3719,0)
    val x3721 = OpDef(op=BitAnd, inputs=List(b2267, b2199)).name("x3721").ctrl(x3727).srcCtx("LogReg.scala:100:13") // And(b2267,b2199)
    val x3722 = OpDef(op=BitAnd, inputs=List(x3721, b2191)).name("x3722").ctrl(x3727).srcCtx("LogReg.scala:100:13") // And(x3721,b2191)
    val x3723 = OpDef(op=BitAnd, inputs=List(x3722, x3716)).name("x3723").ctrl(x3727).srcCtx("LogReg.scala:100:13") // And(x3722,x3716)
    val x3724 = OpDef(op=FixEql, inputs=List(b2266, Const(0))).name("x3724").ctrl(x3727).srcCtx("LogReg.scala:100:13") // FixEql(b2266,Const(0))
    val x3725 = ReduceAccumOp(op=FltAdd, input=x3718, accum=x3720).name("x3725").ctrl(x3727).srcCtx("LogReg.scala:100:17") // FltAdd(x3718,x3720)
    val x3726 = StoreBanks(List(x3668_d0_b0, x3668_d1_b0), List(b2314), x3725).name("x3726").ctrl(x3727).srcCtx("LogReg.scala:100:13") // ParSRAMStore(x3668,List(ArrayBuffer(b2314)),List(x3725),List(x3716))
    val x3729 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16).name("x3729").ctrl(x3742).srcCtx("LogReg.scala:101:11") // CounterNew(Const(0),Const(192),Const(1),Const(16))
    val x3730 = CounterChain(List(x3729)).name("x3730").ctrl(x3742).srcCtx("LogReg.scala:101:11") // CounterChainNew(ArrayBuffer(x3729))
    val x3741 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3730).name("x3741").ctrl(x3742).srcCtx("LogReg.scala:101:11") // UnrolledForeach(List(),x3730,Block(Const(())),ArrayBuffer(List(b2330)),ArrayBuffer(List(b2331)))
    val b2330 = CounterIter(x3729, None).name("b2330").ctrl(x3741) // b2330
    val b2331 = Const(true).name("b2331").ctrl(x3741) // b2331
    val x3731 = OpDef(op=BitAnd, inputs=List(b2331, b2191)).name("x3731").ctrl(x3741).srcCtx("UnrollingBase.scala:28:66") // And(b2331,b2191)
    val x3732 = LoadBanks(List(x3668_d0_b0), List(b2330)).name("x3732").ctrl(x3741).srcCtx("LogReg.scala:101:11") // ParSRAMLoad(x3668,List(ArrayBuffer(b2330)),List(x3731))
    val x3733 = x3732 // x3733 = VectorApply(x3732,0)
    val x3734 = LoadBanks(List(x3609_d1_b0), List(b2330)).name("x3734").ctrl(x3741).srcCtx("LogReg.scala:101:11") // ParSRAMLoad(x3609,List(ArrayBuffer(b2330)),List(x3731))
    val x3735 = x3734 // x3735 = VectorApply(x3734,0)
    val x3736 = OpDef(op=BitAnd, inputs=List(b2199, b2191)).name("x3736").ctrl(x3741).srcCtx("LogReg.scala:101:11") // And(b2199,b2191)
    val x3737 = OpDef(op=BitAnd, inputs=List(x3736, x3731)).name("x3737").ctrl(x3741).srcCtx("LogReg.scala:101:11") // And(x3736,x3731)
    val x3738 = OpDef(op=FixEql, inputs=List(b2198, Const(0))).name("x3738").ctrl(x3741).srcCtx("LogReg.scala:101:11") // FixEql(b2198,Const(0))
    val x3739 = ReduceAccumOp(op=FltAdd, input=x3733, accum=x3735).name("x3739").ctrl(x3741).srcCtx("LogReg.scala:101:15") // FltAdd(x3733,x3735)
    val x3740 = StoreBanks(List(x3609_d0_b0, x3609_d1_b0), List(b2330), x3739).name("x3740").ctrl(x3741).srcCtx("LogReg.scala:101:11") // ParSRAMStore(x3609,List(ArrayBuffer(b2330)),List(x3739),List(x3731))
    val x3743 = Counter(min=Const(0), max=Const(192), step=Const(1), par=1).name("x3743").ctrl(x3754).srcCtx("LogReg.scala:103:19") // CounterNew(Const(0),Const(192),Const(1),Const(1))
    val x3744 = CounterChain(List(x3743)).name("x3744").ctrl(x3754).srcCtx("LogReg.scala:103:25") // CounterChainNew(List(x3743))
    val x3753 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3744).name("x3753").ctrl(x3754).srcCtx("LogReg.scala:103:25") // UnrolledForeach(List(b2191),x3744,Block(Const(())),List(List(b2346)),List(List(b2347)))
    val b2346 = CounterIter(x3743, None).name("b2346").ctrl(x3753) // b2346
    val b2347 = Const(true).name("b2347").ctrl(x3753) // b2347
    val x3745 = OpDef(op=BitAnd, inputs=List(b2347, b2191)).name("x3745").ctrl(x3753).srcCtx("UnrollingBase.scala:28:66") // And(b2347,b2191)
    val x3746 = LoadBanks(List(x3587_d1_b0), List(b2346)).name("x3746").ctrl(x3753).srcCtx("LogReg.scala:103:50") // ParSRAMLoad(x3587,List(List(b2346)),List(x3745))
    val x3747 = x3746 // x3747 = VectorApply(x3746,0)
    val x3748 = LoadBanks(List(x3609_d0_b0), List(b2346)).name("x3748").ctrl(x3753).srcCtx("LogReg.scala:103:60") // ParSRAMLoad(x3609,List(List(b2346)),List(x3745))
    val x3749 = x3748 // x3749 = VectorApply(x3748,0)
    val x3750 = OpDef(op=FltMul, inputs=List(x3749, Const(1.0E-7))).name("x3750").ctrl(x3753).srcCtx("LogReg.scala:103:64") // FltMul(x3749,Const(0.0000001000000011686097423080354928970337))
    val x3751 = OpDef(op=FltAdd, inputs=List(x3747, x3750)).name("x3751").ctrl(x3753).srcCtx("LogReg.scala:103:54") // FltAdd(x3747,x3750)
    val x3752 = StoreBanks(List(x3587_d0_b0, x3587_d1_b0, x3587_d2_b0), List(b2346), x3751).name("x3752").ctrl(x3753).srcCtx("LogReg.scala:103:42") // ParSRAMStore(x3587,List(List(b2346)),List(x3751),List(x3745))
    val x3776 = UnitController(style=StreamPipe, level=OuterControl).name("x3776").ctrl(x3777).srcCtx("LogReg.scala:107:26") // UnitPipe(List(Const(true)),Block(Const(())))
    val b3859 = StreamOut(field="offset").name("b3859").ctrl(x3776).srcCtx("LogReg.scala:107:26") // x3755 = StreamOutNew(BurstCmdBus)
    isAccum(b3859) = false
    bufferDepthOf(b3859) = 1
    val b3860 = StreamOut(field="size").name("b3860").ctrl(x3776).srcCtx("LogReg.scala:107:26") // x3755 = StreamOutNew(BurstCmdBus)
    isAccum(b3860) = false
    bufferDepthOf(b3860) = 1
    val x3756 = StreamOut(field="data").name("x3756").ctrl(x3776).srcCtx("LogReg.scala:107:26") // x3756 = StreamOutNew(BurstFullDataBus())
    isAccum(x3756) = false
    bufferDepthOf(x3756) = 1
    val x3757 = StreamIn(field="ack").name("x3757").ctrl(x3776).srcCtx("LogReg.scala:107:26") // x3757 = StreamInNew(BurstAckBus)
    isAccum(x3757) = false
    bufferDepthOf(x3757) = 1
    val x3765 = UnitController(style=SeqPipe, level=InnerControl).name("x3765").ctrl(x3776).srcCtx("LogReg.scala:107:26") // UnitPipe(List(Const(true)),Block(x3764))
    val x3758 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x3759 = OpDef(op=FixSla, inputs=List(x3758, Const(2))).name("x3759").ctrl(x3765).srcCtx("LogReg.scala:107:26") // FixLsh(x3758,Const(2))
    val x3760 = x3759 // FixConvert(x3759,TRUE,_64,_0)
    val x3761 = DramAddress(x3581).name("x3761").ctrl(x3765).srcCtx("LogReg.scala:107:26") // GetDRAMAddress(x3581)
    val x3763_x3762 = OpDef(op=FixAdd, inputs=List(x3760, x3761)).name("x3763_x3762").ctrl(x3765).srcCtx("LogReg.scala:107:26") // FixAdd(x3760,x3761)
    // x3763 = SimpleStruct(ArrayBuffer((offset,x3762), (size,Const(768)), (isLoad,Const(false))))
    val x3764_b3861_b3859 = WriteMem(b3859, x3763_x3762).name("x3764_b3861_b3859").ctrl(x3765).srcCtx("LogReg.scala:107:26") // StreamWrite(x3755,x3763,Const(true))
    val x3764_b3862_b3860 = WriteMem(b3860, Const(768)).name("x3764_b3862_b3860").ctrl(x3765).srcCtx("LogReg.scala:107:26") // StreamWrite(x3755,x3763,Const(true))
    val x3766 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16).name("x3766").ctrl(x3776).srcCtx("LogReg.scala:107:26") // CounterNew(Const(0),Const(192),Const(1),Const(16))
    val x3767 = CounterChain(List(x3766)).name("x3767").ctrl(x3776).srcCtx("LogReg.scala:107:26") // CounterChainNew(List(x3766))
    val x3772 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3767).name("x3772").ctrl(x3776).srcCtx("LogReg.scala:107:26") // UnrolledForeach(List(Const(true)),x3767,Block(Const(())),List(List(b2371)),List(List(b2372)))
    val b2371 = CounterIter(x3766, None).name("b2371").ctrl(x3772) // b2371
    val b2372 = Const(true).name("b2372").ctrl(x3772) // b2372
    val x3768 = LoadBanks(List(x3587_d0_b0), List(b2371)).name("x3768").ctrl(x3772).srcCtx("LogReg.scala:107:26") // ParSRAMLoad(x3587,List(List(b2371)),List(b2372))
    val x3770_x3769 = x3768 // x3769 = VectorApply(x3768,0)
    // x3770 = SimpleStruct(ArrayBuffer((_1,x3769), (_2,Const(true))))
    val x3771_x3771_x3756 = WriteMem(x3756, x3770_x3769).name("x3771_x3771_x3756").ctrl(x3772).srcCtx("LogReg.scala:107:26") // ParStreamWrite(x3756,List(x3770),List(b2372))
    val x3773 = FringeDenseStore(dram=List(x3581), cmdStream=List(b3859, b3860), dataStream=List(x3756), ackStream=List(x3757)).name("x3773").ctrl(x3776).srcCtx("LogReg.scala:107:26") // FringeDenseStore(x3581,x3755,x3756,x3757)
    val x3775 = UnitController(style=SeqPipe, level=InnerControl).name("x3775").ctrl(x3776).srcCtx("LogReg.scala:107:26") // UnitPipe(List(Const(true)),Block(Const(())))
    val x3774_x3774 = ReadMem(x3757).name("x3774_x3774").ctrl(x3775).srcCtx("LogReg.scala:107:26") // StreamRead(x3757,Const(true))
    
  }
}
