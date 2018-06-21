import pir._
import pir.node._
import arch._
import prism.enums._

object LogReg extends PIRApp {
  def main(implicit design:PIRDesign) = {
    import design.pirmeta._
    val x3572 = ArgIn(init=0).name("x3572").ctrl(top).srcCtx("LogReg.scala:25:22:iters") // ArgInNew(Const(0))
    isAccum(x3572) = false
    bufferDepthOf(x3572) = 1
    boundOf(x3572) = 1
    val x3573 = ArgIn(init=0).name("x3573").ctrl(top).srcCtx("LogReg.scala:26:22:N") // ArgInNew(Const(0))
    isAccum(x3573) = false
    bufferDepthOf(x3573) = 1
    boundOf(x3573) = 1024
    val x3576 = ReadMem(x3573).name("x3576").ctrl(top).srcCtx("LogReg.scala:34:21") // RegRead(x3573)
    val x3577 = DRAM().name("x3577").ctrl(top).srcCtx("LogReg.scala:34:20:x") // x3577 = DRAMNew(ArrayBuffer(x3576, Const(192)),Const(0.0))
    val x3578 = ReadMem(x3573).name("x3578").ctrl(top).srcCtx("LogReg.scala:35:21") // RegRead(x3573)
    val x3579 = DRAM().name("x3579").ctrl(top).srcCtx("LogReg.scala:35:20:y") // x3579 = DRAMNew(ArrayBuffer(x3578),Const(0.0))
    val x3580 = DRAM().name("x3580").ctrl(top).srcCtx("LogReg.scala:36:24:theta") // x3580 = DRAMNew(ArrayBuffer(Const(192)),Const(0.0))
    val x3775 = UnitController(style=SeqPipe, level=OuterControl).name("x3775").ctrl(top).srcCtx("LogReg.scala:75:11") // Hwblock(Block(Const(())),false)
    val x3586_d0_b0 = SRAM(size=12, banking=Strided(banks=16, stride=1)).name("x3586_d0_b0").ctrl(x3775).srcCtx("LogReg.scala:76:27:btheta") // x3586 = SRAMNew(ArrayBuffer(Const(192)))
    isAccum(x3586_d0_b0) = false
    bufferDepthOf(x3586_d0_b0) = 1
    val x3586_d1_b0 = SRAM(size=12, banking=Strided(banks=16, stride=1)).name("x3586_d1_b0").ctrl(x3775).srcCtx("LogReg.scala:76:27:btheta") // x3586 = SRAMNew(ArrayBuffer(Const(192)))
    isAccum(x3586_d1_b0) = true
    bufferDepthOf(x3586_d1_b0) = 1
    val x3586_d2_b0 = SRAM(size=12, banking=Strided(banks=16, stride=1)).name("x3586_d2_b0").ctrl(x3775).srcCtx("LogReg.scala:76:27:btheta") // x3586 = SRAMNew(ArrayBuffer(Const(192)))
    isAccum(x3586_d2_b0) = false
    bufferDepthOf(x3586_d2_b0) = 1
    val x3604 = UnitController(style=StreamPipe, level=OuterControl).name("x3604").ctrl(x3775).srcCtx("LogReg.scala:78:14") // UnitPipe(List(Const(true)),Block(Const(())))
    val b3845 = StreamOut(field="offset").name("b3845").ctrl(x3604).srcCtx("LogReg.scala:78:14") // x3587 = StreamOutNew(BurstCmdBus)
    isAccum(b3845) = false
    bufferDepthOf(b3845) = 1
    val b3846 = StreamOut(field="size").name("b3846").ctrl(x3604).srcCtx("LogReg.scala:78:14") // x3587 = StreamOutNew(BurstCmdBus)
    isAccum(b3846) = false
    bufferDepthOf(b3846) = 1
    val x3588 = StreamIn(field="data").name("x3588").ctrl(x3604).srcCtx("LogReg.scala:78:14") // x3588 = StreamInNew(BurstDataBus())
    isAccum(x3588) = false
    bufferDepthOf(x3588) = 1
    val x3596 = UnitController(style=SeqPipe, level=InnerControl).name("x3596").ctrl(x3604).srcCtx("LogReg.scala:78:14") // UnitPipe(List(Const(true)),Block(x3595))
    val x3589 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x3590 = OpDef(op=FixSla, inputs=List(x3589, Const(2))).name("x3590").ctrl(x3596).srcCtx("LogReg.scala:78:14") // FixLsh(x3589,Const(2))
    val x3591 = x3590 // FixConvert(x3590,TRUE,_64,_0)
    val x3592 = DramAddress(x3580).name("x3592").ctrl(x3596).srcCtx("LogReg.scala:78:14") // GetDRAMAddress(x3580)
    val x3594_x3593 = OpDef(op=FixAdd, inputs=List(x3591, x3592)).name("x3594_x3593").ctrl(x3596).srcCtx("LogReg.scala:78:14") // FixAdd(x3591,x3592)
    // x3594 = SimpleStruct(ArrayBuffer((offset,x3593), (size,Const(768)), (isLoad,Const(true))))
    val x3595_b3847_b3845 = WriteMem(b3845, x3594_x3593).name("x3595_b3847_b3845").ctrl(x3596).srcCtx("LogReg.scala:78:14") // StreamWrite(x3587,x3594,Const(true))
    val x3595_b3848_b3846 = WriteMem(b3846, Const(768)).name("x3595_b3848_b3846").ctrl(x3596).srcCtx("LogReg.scala:78:14") // StreamWrite(x3587,x3594,Const(true))
    val x3597 = FringeDenseLoad(dram=List(x3580), cmdStream=List(b3845, b3846), dataStream=List(x3588)).name("x3597").ctrl(x3604).srcCtx("LogReg.scala:78:14") // FringeDenseLoad(x3580,x3587,x3588)
    val x3598 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16).name("x3598").ctrl(x3604).srcCtx("LogReg.scala:78:14") // CounterNew(Const(0),Const(192),Const(1),Const(16))
    val x3599 = CounterChain(List(x3598)).name("x3599").ctrl(x3604).srcCtx("LogReg.scala:78:14") // CounterChainNew(List(x3598))
    val x3603 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3599).name("x3603").ctrl(x3604).srcCtx("LogReg.scala:78:14") // UnrolledForeach(List(Const(true)),x3599,Block(Const(())),List(List(b2180)),List(List(b2181)))
    val b2180 = CounterIter(x3598, None).name("b2180").ctrl(x3603) // b2180
    val b2181 = Const(true).name("b2181").ctrl(x3603) // b2181
    val x3600_x3600 = ReadMem(x3588).name("x3600_x3600").ctrl(x3603).srcCtx("LogReg.scala:78:14") // ParStreamRead(x3588,List(b2181))
    val x3601_x3601 = x3600_x3600 // x3601 = VectorApply(x3600,0)
    val x3602 = StoreBanks(List(x3586_d0_b0, x3586_d1_b0, x3586_d2_b0), List(b2180), x3601_x3601).name("x3602").ctrl(x3603).srcCtx("LogReg.scala:78:14") // ParSRAMStore(x3586,List(List(b2180)),List(x3601),List(b2181))
    val x3605 = ReadMem(x3572).name("x3605").ctrl(x3775).srcCtx("LogReg.scala:80:26") // RegRead(x3572)
    val x3606 = Counter(min=Const(0), max=x3605, step=Const(1), par=1).name("x3606").ctrl(x3775).srcCtx("LogReg.scala:80:32") // CounterNew(Const(0),x3605,Const(1),Const(1))
    val x3607 = CounterChain(List(x3606)).name("x3607").ctrl(x3775).srcCtx("LogReg.scala:80:38") // CounterChainNew(List(x3606))
    val x3752 = LoopController(style=SeqPipe, level=OuterControl, cchain=x3607).name("x3752").ctrl(x3775).srcCtx("LogReg.scala:80:38") // UnrolledForeach(List(Const(true)),x3607,Block(Const(())),List(List(b2190)),List(List(b2191)))
    val b2190 = CounterIter(x3606, Some(0)).name("b2190").ctrl(x3752) // b2190
    val b2191 = Const(true).name("b2191").ctrl(x3752) // b2191
    val x3608_d0_b0 = SRAM(size=12, banking=Strided(banks=16, stride=1)).name("x3608_d0_b0").ctrl(x3752).srcCtx("LogReg.scala:82:37:grad") // x3608 = SRAMNew(ArrayBuffer(Const(192)))
    isAccum(x3608_d0_b0) = false
    bufferDepthOf(x3608_d0_b0) = 1
    val x3608_d1_b0 = SRAM(size=12, banking=Strided(banks=16, stride=1)).name("x3608_d1_b0").ctrl(x3752).srcCtx("LogReg.scala:82:37:grad") // x3608 = SRAMNew(ArrayBuffer(Const(192)))
    isAccum(x3608_d1_b0) = true
    bufferDepthOf(x3608_d1_b0) = 1
    val x3609 = ReadMem(x3573).name("x3609").ctrl(x3752).srcCtx("LogReg.scala:82:49") // RegRead(x3573)
    val x3610 = Counter(min=Const(0), max=x3609, step=Const(64), par=1).name("x3610").ctrl(x3752).srcCtx("LogReg.scala:82:57") // CounterNew(Const(0),x3609,Const(64),Const(1))
    val x3611 = CounterChain(List(x3610)).name("x3611").ctrl(x3752).srcCtx("LogReg.scala:101:11") // CounterChainNew(List(x3610))
    val x3740 = LoopController(style=MetaPipe, level=OuterControl, cchain=x3611).name("x3740").ctrl(x3752).srcCtx("LogReg.scala:101:11") // UnrolledReduce(List(b2191),x3611,x3608,Block((x3608) => Const(())),List(List(b2198)),List(List(b2199)))
    val b2198 = CounterIter(x3610, Some(0)).name("b2198").ctrl(x3740) // b2198
    val b2199 = Const(true).name("b2199").ctrl(x3740) // b2199
    val x3612_d0_b0 = SRAM(size=768, banking=Strided(banks=16, stride=1)).name("x3612_d0_b0").ctrl(x3740).srcCtx("LogReg.scala:83:30:xTile") // x3612 = SRAMNew(ArrayBuffer(Const(64), Const(192)))
    isAccum(x3612_d0_b0) = false
    bufferDepthOf(x3612_d0_b0) = 2
    val x3612_d1_b0 = SRAM(size=768, banking=Strided(banks=16, stride=1)).name("x3612_d1_b0").ctrl(x3740).srcCtx("LogReg.scala:83:30:xTile") // x3612 = SRAMNew(ArrayBuffer(Const(64), Const(192)))
    isAccum(x3612_d1_b0) = false
    bufferDepthOf(x3612_d1_b0) = 2
    val x3613_d0_b0 = SRAM(size=4, banking=Strided(banks=16, stride=1)).name("x3613_d0_b0").ctrl(x3740).srcCtx("LogReg.scala:84:30:yTile") // x3613 = SRAMNew(ArrayBuffer(Const(64)))
    isAccum(x3613_d0_b0) = false
    bufferDepthOf(x3613_d0_b0) = 2
    val x3615 = UnitController(style=SeqPipe, level=InnerControl).name("x3615").ctrl(x3740).srcCtx("LogReg.scala:85:20") // UnitPipe(List(b2199, b2191),Block(Const(())))
    val x3614 = OpDef(op=FixAdd, inputs=List(b2198, Const(64))).name("x3614").ctrl(x3615).srcCtx("LogReg.scala:86:30") // FixAdd(b2198,Const(64))
    val x3616 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1).name("x3616").ctrl(x3740).srcCtx("LogReg.scala:86:19") // CounterNew(Const(0),Const(64),Const(1),Const(1))
    val x3617 = CounterChain(List(x3616)).name("x3617").ctrl(x3740).srcCtx("LogReg.scala:86:19") // CounterChainNew(List(x3616))
    val x3644 = LoopController(style=StreamPipe, level=OuterControl, cchain=x3617).name("x3644").ctrl(x3740).srcCtx("LogReg.scala:86:19") // UnrolledForeach(List(b2199, b2191),x3617,Block(Const(())),List(List(b2206)),List(List(b2207)))
    val b2206 = CounterIter(x3616, Some(0)).name("b2206").ctrl(x3644) // b2206
    val b2207 = Const(true).name("b2207").ctrl(x3644) // b2207
    val b3849 = StreamOut(field="offset").name("b3849").ctrl(x3644).srcCtx("LogReg.scala:86:19") // x3618 = StreamOutNew(BurstCmdBus)
    isAccum(b3849) = false
    bufferDepthOf(b3849) = 1
    val b3850 = StreamOut(field="size").name("b3850").ctrl(x3644).srcCtx("LogReg.scala:86:19") // x3618 = StreamOutNew(BurstCmdBus)
    isAccum(b3850) = false
    bufferDepthOf(b3850) = 1
    val x3619 = StreamIn(field="data").name("x3619").ctrl(x3644).srcCtx("LogReg.scala:86:19") // x3619 = StreamInNew(BurstDataBus())
    isAccum(x3619) = false
    bufferDepthOf(x3619) = 1
    val x3633 = UnitController(style=SeqPipe, level=InnerControl).name("x3633").ctrl(x3644).srcCtx("LogReg.scala:86:19") // UnitPipe(List(b2207, b2199, b2191),Block(x3632))
    val x3620 = OpDef(op=FixAdd, inputs=List(b2198, b2206)).name("x3620").ctrl(x3633).srcCtx("LogReg.scala:86:19") // FixAdd(b2198,b2206)
    val x3621 = x3620 // FixConvert(x3620,TRUE,_32,_0) (Same Type. No op)
    val x3622 = OpDef(op=FixMul, inputs=List(x3621, Const(192))).name("x3622").ctrl(x3633).srcCtx("LogReg.scala:86:19") // FixMul(x3621,Const(192))
    val x3623 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x3624 = OpDef(op=FixAdd, inputs=List(x3622, x3623)).name("x3624").ctrl(x3633).srcCtx("LogReg.scala:86:19") // FixAdd(x3622,x3623)
    val x3625 = OpDef(op=FixSla, inputs=List(x3624, Const(2))).name("x3625").ctrl(x3633).srcCtx("LogReg.scala:86:19") // FixLsh(x3624,Const(2))
    val x3626 = x3625 // FixConvert(x3625,TRUE,_64,_0)
    val x3627 = DramAddress(x3577).name("x3627").ctrl(x3633).srcCtx("LogReg.scala:86:19") // GetDRAMAddress(x3577)
    val x3629_x3628 = OpDef(op=FixAdd, inputs=List(x3626, x3627)).name("x3629_x3628").ctrl(x3633).srcCtx("LogReg.scala:86:19") // FixAdd(x3626,x3627)
    // x3629 = SimpleStruct(ArrayBuffer((offset,x3628), (size,Const(768)), (isLoad,Const(true))))
    val x3630 = OpDef(op=BitAnd, inputs=List(b2207, b2199)).name("x3630").ctrl(x3633).srcCtx("UnrollingBase.scala:28:66") // And(b2207,b2199)
    val x3631 = OpDef(op=BitAnd, inputs=List(x3630, b2191)).name("x3631").ctrl(x3633).srcCtx("UnrollingBase.scala:28:66") // And(x3630,b2191)
    val x3632_b3851_b3849 = WriteMem(b3849, x3629_x3628).name("x3632_b3851_b3849").ctrl(x3633).srcCtx("LogReg.scala:86:19") // StreamWrite(x3618,x3629,x3631)
    val x3632_b3852_b3850 = WriteMem(b3850, Const(768)).name("x3632_b3852_b3850").ctrl(x3633).srcCtx("LogReg.scala:86:19") // StreamWrite(x3618,x3629,x3631)
    val x3634 = FringeDenseLoad(dram=List(x3577), cmdStream=List(b3849, b3850), dataStream=List(x3619)).name("x3634").ctrl(x3644).srcCtx("LogReg.scala:86:19") // FringeDenseLoad(x3577,x3618,x3619)
    val x3635 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16).name("x3635").ctrl(x3644).srcCtx("LogReg.scala:86:19") // CounterNew(Const(0),Const(192),Const(1),Const(16))
    val x3636 = CounterChain(List(x3635)).name("x3636").ctrl(x3644).srcCtx("LogReg.scala:86:19") // CounterChainNew(List(x3635))
    val x3643 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3636).name("x3643").ctrl(x3644).srcCtx("LogReg.scala:86:19") // UnrolledForeach(List(b2207, b2199, b2191),x3636,Block(Const(())),List(List(b2227)),List(List(b2228)))
    val b2227 = CounterIter(x3635, None).name("b2227").ctrl(x3643) // b2227
    val b2228 = Const(true).name("b2228").ctrl(x3643) // b2228
    val x3637 = OpDef(op=BitAnd, inputs=List(b2228, b2207)).name("x3637").ctrl(x3643).srcCtx("UnrollingBase.scala:28:66") // And(b2228,b2207)
    val x3638 = OpDef(op=BitAnd, inputs=List(b2199, b2191)).name("x3638").ctrl(x3643).srcCtx("UnrollingBase.scala:28:66") // And(b2199,b2191)
    val x3639 = OpDef(op=BitAnd, inputs=List(x3637, x3638)).name("x3639").ctrl(x3643).srcCtx("UnrollingBase.scala:28:66") // And(x3637,x3638)
    val x3640_x3640 = ReadMem(x3619).name("x3640_x3640").ctrl(x3643).srcCtx("LogReg.scala:86:19") // ParStreamRead(x3619,List(x3639))
    val x3641_x3641 = x3640_x3640 // x3641 = VectorApply(x3640,0)
    val x3642 = StoreBanks(List(x3612_d0_b0, x3612_d1_b0), List(b2206, b2227), x3641_x3641).name("x3642").ctrl(x3643).srcCtx("LogReg.scala:86:19") // ParSRAMStore(x3612,List(List(b2206, b2227)),List(x3641),List(x3639))
    val x3665 = UnitController(style=StreamPipe, level=OuterControl).name("x3665").ctrl(x3740).srcCtx("LogReg.scala:87:19") // UnitPipe(List(b2199, b2191),Block(Const(())))
    val b3853 = StreamOut(field="offset").name("b3853").ctrl(x3665).srcCtx("LogReg.scala:87:19") // x3645 = StreamOutNew(BurstCmdBus)
    isAccum(b3853) = false
    bufferDepthOf(b3853) = 1
    val b3854 = StreamOut(field="size").name("b3854").ctrl(x3665).srcCtx("LogReg.scala:87:19") // x3645 = StreamOutNew(BurstCmdBus)
    isAccum(b3854) = false
    bufferDepthOf(b3854) = 1
    val x3646 = StreamIn(field="data").name("x3646").ctrl(x3665).srcCtx("LogReg.scala:87:19") // x3646 = StreamInNew(BurstDataBus())
    isAccum(x3646) = false
    bufferDepthOf(x3646) = 1
    val x3655 = UnitController(style=SeqPipe, level=InnerControl).name("x3655").ctrl(x3665).srcCtx("LogReg.scala:87:19") // UnitPipe(List(b2199, b2191),Block(x3654))
    val x3647 = b2198 // FixConvert(b2198,TRUE,_32,_0) (Same Type. No op)
    val x3648 = OpDef(op=FixSla, inputs=List(x3647, Const(2))).name("x3648").ctrl(x3655).srcCtx("LogReg.scala:87:19") // FixLsh(x3647,Const(2))
    val x3649 = x3648 // FixConvert(x3648,TRUE,_64,_0)
    val x3650 = DramAddress(x3579).name("x3650").ctrl(x3655).srcCtx("LogReg.scala:87:19") // GetDRAMAddress(x3579)
    val x3652_x3651 = OpDef(op=FixAdd, inputs=List(x3649, x3650)).name("x3652_x3651").ctrl(x3655).srcCtx("LogReg.scala:87:19") // FixAdd(x3649,x3650)
    // x3652 = SimpleStruct(ArrayBuffer((offset,x3651), (size,Const(256)), (isLoad,Const(true))))
    val x3653 = OpDef(op=BitAnd, inputs=List(b2199, b2191)).name("x3653").ctrl(x3655).srcCtx("UnrollingBase.scala:28:66") // And(b2199,b2191)
    val x3654_b3855_b3853 = WriteMem(b3853, x3652_x3651).name("x3654_b3855_b3853").ctrl(x3655).srcCtx("LogReg.scala:87:19") // StreamWrite(x3645,x3652,x3653)
    val x3654_b3856_b3854 = WriteMem(b3854, Const(256)).name("x3654_b3856_b3854").ctrl(x3655).srcCtx("LogReg.scala:87:19") // StreamWrite(x3645,x3652,x3653)
    val x3656 = FringeDenseLoad(dram=List(x3579), cmdStream=List(b3853, b3854), dataStream=List(x3646)).name("x3656").ctrl(x3665).srcCtx("LogReg.scala:87:19") // FringeDenseLoad(x3579,x3645,x3646)
    val x3657 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x3657").ctrl(x3665).srcCtx("LogReg.scala:87:19") // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x3658 = CounterChain(List(x3657)).name("x3658").ctrl(x3665).srcCtx("LogReg.scala:87:19") // CounterChainNew(List(x3657))
    val x3664 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3658).name("x3664").ctrl(x3665).srcCtx("LogReg.scala:87:19") // UnrolledForeach(List(b2199, b2191),x3658,Block(Const(())),List(List(b2251)),List(List(b2252)))
    val b2251 = CounterIter(x3657, None).name("b2251").ctrl(x3664) // b2251
    val b2252 = Const(true).name("b2252").ctrl(x3664) // b2252
    val x3659 = OpDef(op=BitAnd, inputs=List(b2252, b2199)).name("x3659").ctrl(x3664).srcCtx("UnrollingBase.scala:28:66") // And(b2252,b2199)
    val x3660 = OpDef(op=BitAnd, inputs=List(x3659, b2191)).name("x3660").ctrl(x3664).srcCtx("UnrollingBase.scala:28:66") // And(x3659,b2191)
    val x3661_x3661 = ReadMem(x3646).name("x3661_x3661").ctrl(x3664).srcCtx("LogReg.scala:87:19") // ParStreamRead(x3646,List(x3660))
    val x3662_x3662 = x3661_x3661 // x3662 = VectorApply(x3661,0)
    val x3663 = StoreBanks(List(x3613_d0_b0), List(b2251), x3662_x3662).name("x3663").ctrl(x3664).srcCtx("LogReg.scala:87:19") // ParSRAMStore(x3613,List(List(b2251)),List(x3662),List(x3660))
    val x3666_d0_b0 = SRAM(size=12, banking=Strided(banks=16, stride=1)).name("x3666_d0_b0").ctrl(x3740).srcCtx("LogReg.scala:89:28") // x3666 = SRAMNew(ArrayBuffer(Const(192)))
    isAccum(x3666_d0_b0) = false
    bufferDepthOf(x3666_d0_b0) = 2
    val x3666_d1_b0 = SRAM(size=12, banking=Strided(banks=16, stride=1)).name("x3666_d1_b0").ctrl(x3740).srcCtx("LogReg.scala:89:28") // x3666 = SRAMNew(ArrayBuffer(Const(192)))
    isAccum(x3666_d1_b0) = true
    bufferDepthOf(x3666_d1_b0) = 1
    val x3667 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1).name("x3667").ctrl(x3740).srcCtx("LogReg.scala:89:48") // CounterNew(Const(0),Const(64),Const(1),Const(1))
    val x3668 = CounterChain(List(x3667)).name("x3668").ctrl(x3740).srcCtx("LogReg.scala:100:13") // CounterChainNew(List(x3667))
    val x3726 = LoopController(style=MetaPipe, level=OuterControl, cchain=x3668).name("x3726").ctrl(x3740).srcCtx("LogReg.scala:100:13") // UnrolledReduce(List(b2199, b2191),x3668,x3666,Block((x3666) => Const(())),List(List(b2266)),List(List(b2267)))
    val b2266 = CounterIter(x3667, Some(0)).name("b2266").ctrl(x3726) // b2266
    val b2267 = Const(true).name("b2267").ctrl(x3726) // b2267
    val x3669_d0 = Reg(init=Some(0.0)).name("x3669_d0").ctrl(x3726).srcCtx("LogReg.scala:90:33:dot") // x3669 = RegNew(Const(0.0))
    isAccum(x3669_d0) = false
    bufferDepthOf(x3669_d0) = 2
    val x3669_d1 = Reg(init=Some(0.0)).name("x3669_d1").ctrl(x3726).srcCtx("LogReg.scala:90:33:dot") // x3669 = RegNew(Const(0.0))
    isAccum(x3669_d1) = true
    bufferDepthOf(x3669_d1) = 1
    val x3670 = Counter(min=Const(0), max=Const(192), step=Const(1), par=1).name("x3670").ctrl(x3726).srcCtx("LogReg.scala:90:40") // CounterNew(Const(0),Const(192),Const(1),Const(1))
    val x3671 = CounterChain(List(x3670)).name("x3671").ctrl(x3726).srcCtx("LogReg.scala:92:15") // CounterChainNew(List(x3670))
    val x3686 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3671).name("x3686").ctrl(x3726).srcCtx("LogReg.scala:92:15") // UnrolledReduce(List(b2267, b2199, b2191),x3671,x3669,Block((x3669) => Const(())),List(List(b2271)),List(List(b2272)))
    val b2271 = CounterIter(x3670, None).name("b2271").ctrl(x3686) // b2271
    val b2272 = Const(true).name("b2272").ctrl(x3686) // b2272
    val x3672 = OpDef(op=BitAnd, inputs=List(b2272, b2267)).name("x3672").ctrl(x3686).srcCtx("UnrollingBase.scala:28:66") // And(b2272,b2267)
    val x3673 = OpDef(op=BitAnd, inputs=List(b2199, b2191)).name("x3673").ctrl(x3686).srcCtx("UnrollingBase.scala:28:66") // And(b2199,b2191)
    val x3674 = OpDef(op=BitAnd, inputs=List(x3672, x3673)).name("x3674").ctrl(x3686).srcCtx("UnrollingBase.scala:28:66") // And(x3672,x3673)
    val x3675 = LoadBanks(List(x3612_d1_b0), List(b2266, b2271)).name("x3675").ctrl(x3686).srcCtx("LogReg.scala:91:20") // ParSRAMLoad(x3612,List(List(b2266, b2271)),List(x3674))
    val x3676 = x3675 // x3676 = VectorApply(x3675,0)
    val x3677 = LoadBanks(List(x3586_d2_b0), List(b2271)).name("x3677").ctrl(x3686).srcCtx("LogReg.scala:91:35") // ParSRAMLoad(x3586,List(List(b2271)),List(x3674))
    val x3678 = x3677 // x3678 = VectorApply(x3677,0)
    val x3679 = OpDef(op=FltMul, inputs=List(x3676, x3678)).name("x3679").ctrl(x3686).srcCtx("LogReg.scala:91:27") // FltMul(x3676,x3678)
    val x3680 = ReadMem(x3669_d1).name("x3680").ctrl(x3686).srcCtx("LogReg.scala:92:15") // RegRead(x3669)
    val x3681 = OpDef(op=FixEql, inputs=List(b2271, Const(0))).name("x3681").ctrl(x3686).srcCtx("LogReg.scala:92:15") // FixEql(b2271,Const(0))
    val x3682 = ReduceAccumOp(op=FltAdd, input=x3679, accum=x3680).name("x3682").ctrl(x3686).srcCtx("LogReg.scala:92:19") // FltAdd(x3679,x3680)
    val x3683 = OpDef(op=BitAnd, inputs=List(b2267, b2199)).name("x3683").ctrl(x3686).srcCtx("UnrollingBase.scala:28:66") // And(b2267,b2199)
    val x3684 = OpDef(op=BitAnd, inputs=List(x3683, b2191)).name("x3684").ctrl(x3686).srcCtx("UnrollingBase.scala:28:66") // And(x3683,b2191)
    val x3685_x3669_d0 = WriteMem(x3669_d0, x3682).name("x3685_x3669_d0").ctrl(x3686).srcCtx("LogReg.scala:92:15") // RegWrite(x3669,x3682,x3684)
    val x3685_x3669_d1 = WriteMem(x3669_d1, x3682).name("x3685_x3669_d1").ctrl(x3686).srcCtx("LogReg.scala:92:15") // RegWrite(x3669,x3682,x3684)
    val x3687 = Reg(init=Some(0.0)).name("x3687").ctrl(x3726).srcCtx("LogReg.scala:93:26:sub") // x3687 = RegNew(Const(0.0))
    isAccum(x3687) = false
    bufferDepthOf(x3687) = 2
    val x3698 = UnitController(style=SeqPipe, level=InnerControl).name("x3698").ctrl(x3726).srcCtx("LogReg.scala:94:18") // UnitPipe(List(b2267, b2199, b2191),Block(x3697))
    val x3688 = OpDef(op=BitAnd, inputs=List(b2267, b2199)).name("x3688").ctrl(x3698).srcCtx("UnrollingBase.scala:28:66") // And(b2267,b2199)
    val x3689 = OpDef(op=BitAnd, inputs=List(x3688, b2191)).name("x3689").ctrl(x3698).srcCtx("UnrollingBase.scala:28:66") // And(x3688,b2191)
    val x3690 = LoadBanks(List(x3613_d0_b0), List(b2266)).name("x3690").ctrl(x3698).srcCtx("LogReg.scala:95:27") // SRAMLoad(x3613,ArrayBuffer(Const(64)),List(b2266),Const(0),x3689)
    val x3691 = ReadMem(x3669_d0).name("x3691").ctrl(x3698).srcCtx("LogReg.scala:95:45") // RegRead(x3669)
    val x3692 = OpDef(op=FltNeg, inputs=List(x3691)).name("x3692").ctrl(x3698).srcCtx("LogReg.scala:21:47") // FltNeg(x3691)
    val x3693 = OpDef(op=FltExp, inputs=List(x3692)).name("x3693").ctrl(x3698).srcCtx("LogReg.scala:21:46") // FltExp(x3692)
    val x3694 = OpDef(op=FltAdd, inputs=List(x3693, Const(1.0))).name("x3694").ctrl(x3698).srcCtx("LogReg.scala:21:51") // FltAdd(x3693,Const(1))
    val x3695 = OpDef(op=FltDiv, inputs=List(Const(1.0), x3694)).name("x3695").ctrl(x3698).srcCtx("LogReg.scala:21:41") // FltDiv(Const(1),x3694)
    val x3696 = OpDef(op=FltSub, inputs=List(x3690, x3695)).name("x3696").ctrl(x3698).srcCtx("LogReg.scala:95:32") // FltSub(x3690,x3695)
    val x3697_x3687 = WriteMem(x3687, x3696).name("x3697_x3687").ctrl(x3698).srcCtx("LogReg.scala:95:19") // RegWrite(x3687,x3696,x3689)
    val x3699_d0_b0 = SRAM(size=12, banking=Strided(banks=16, stride=1)).name("x3699_d0_b0").ctrl(x3726).srcCtx("LogReg.scala:97:34:gradRow") // x3699 = SRAMNew(ArrayBuffer(Const(192)))
    isAccum(x3699_d0_b0) = false
    bufferDepthOf(x3699_d0_b0) = 2
    val x3700 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16).name("x3700").ctrl(x3726).srcCtx("LogReg.scala:98:28") // CounterNew(Const(0),Const(192),Const(1),Const(16))
    val x3701 = CounterChain(List(x3700)).name("x3701").ctrl(x3726).srcCtx("LogReg.scala:98:36") // CounterChainNew(List(x3700))
    val x3710 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3701).name("x3710").ctrl(x3726).srcCtx("LogReg.scala:98:36") // UnrolledForeach(List(b2267, b2199, b2191),x3701,Block(Const(())),List(List(b2303)),List(List(b2304)))
    val b2303 = CounterIter(x3700, None).name("b2303").ctrl(x3710) // b2303
    val b2304 = Const(true).name("b2304").ctrl(x3710) // b2304
    val x3702 = OpDef(op=BitAnd, inputs=List(b2304, b2267)).name("x3702").ctrl(x3710).srcCtx("UnrollingBase.scala:28:66") // And(b2304,b2267)
    val x3703 = OpDef(op=BitAnd, inputs=List(b2199, b2191)).name("x3703").ctrl(x3710).srcCtx("UnrollingBase.scala:28:66") // And(b2199,b2191)
    val x3704 = OpDef(op=BitAnd, inputs=List(x3702, x3703)).name("x3704").ctrl(x3710).srcCtx("UnrollingBase.scala:28:66") // And(x3702,x3703)
    val x3705 = LoadBanks(List(x3612_d0_b0), List(b2266, b2303)).name("x3705").ctrl(x3710).srcCtx("LogReg.scala:98:61") // ParSRAMLoad(x3612,List(List(b2266, b2303)),List(x3704))
    val x3706 = x3705 // x3706 = VectorApply(x3705,0)
    val x3707 = ReadMem(x3687).name("x3707").ctrl(x3710).srcCtx("LogReg.scala:98:75") // RegRead(x3687)
    val x3708 = OpDef(op=FltMul, inputs=List(x3706, x3707)).name("x3708").ctrl(x3710).srcCtx("LogReg.scala:98:69") // FltMul(x3706,x3707)
    val x3709 = StoreBanks(List(x3699_d0_b0), List(b2303), x3708).name("x3709").ctrl(x3710).srcCtx("LogReg.scala:98:54") // ParSRAMStore(x3699,List(List(b2303)),List(x3708),List(x3704))
    val x3711 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16).name("x3711").ctrl(x3726).srcCtx("LogReg.scala:100:13") // CounterNew(Const(0),Const(192),Const(1),Const(16))
    val x3712 = CounterChain(List(x3711)).name("x3712").ctrl(x3726).srcCtx("LogReg.scala:100:13") // CounterChainNew(ArrayBuffer(x3711))
    val x3725 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3712).name("x3725").ctrl(x3726).srcCtx("LogReg.scala:100:13") // UnrolledForeach(List(),x3712,Block(Const(())),ArrayBuffer(List(b2314)),ArrayBuffer(List(b2315)))
    val b2314 = CounterIter(x3711, None).name("b2314").ctrl(x3725) // b2314
    val b2315 = Const(true).name("b2315").ctrl(x3725) // b2315
    val x3713 = OpDef(op=BitAnd, inputs=List(b2315, b2199)).name("x3713").ctrl(x3725).srcCtx("UnrollingBase.scala:28:66") // And(b2315,b2199)
    val x3714 = OpDef(op=BitAnd, inputs=List(x3713, b2191)).name("x3714").ctrl(x3725).srcCtx("UnrollingBase.scala:28:66") // And(x3713,b2191)
    val x3715 = LoadBanks(List(x3699_d0_b0), List(b2314)).name("x3715").ctrl(x3725).srcCtx("LogReg.scala:100:13") // ParSRAMLoad(x3699,List(ArrayBuffer(b2314)),List(x3714))
    val x3716 = x3715 // x3716 = VectorApply(x3715,0)
    val x3717 = LoadBanks(List(x3666_d1_b0), List(b2314)).name("x3717").ctrl(x3725).srcCtx("LogReg.scala:100:13") // ParSRAMLoad(x3666,List(ArrayBuffer(b2314)),List(x3714))
    val x3718 = x3717 // x3718 = VectorApply(x3717,0)
    val x3719 = OpDef(op=BitAnd, inputs=List(b2267, b2199)).name("x3719").ctrl(x3725).srcCtx("LogReg.scala:100:13") // And(b2267,b2199)
    val x3720 = OpDef(op=BitAnd, inputs=List(x3719, b2191)).name("x3720").ctrl(x3725).srcCtx("LogReg.scala:100:13") // And(x3719,b2191)
    val x3721 = OpDef(op=BitAnd, inputs=List(x3720, x3714)).name("x3721").ctrl(x3725).srcCtx("LogReg.scala:100:13") // And(x3720,x3714)
    val x3722 = OpDef(op=FixEql, inputs=List(b2266, Const(0))).name("x3722").ctrl(x3725).srcCtx("LogReg.scala:100:13") // FixEql(b2266,Const(0))
    val x3723 = ReduceAccumOp(op=FltAdd, input=x3716, accum=x3718).name("x3723").ctrl(x3725).srcCtx("LogReg.scala:100:17") // FltAdd(x3716,x3718)
    val x3724 = StoreBanks(List(x3666_d0_b0, x3666_d1_b0), List(b2314), x3723).name("x3724").ctrl(x3725).srcCtx("LogReg.scala:100:13") // ParSRAMStore(x3666,List(ArrayBuffer(b2314)),List(x3723),List(x3714))
    val x3727 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16).name("x3727").ctrl(x3740).srcCtx("LogReg.scala:101:11") // CounterNew(Const(0),Const(192),Const(1),Const(16))
    val x3728 = CounterChain(List(x3727)).name("x3728").ctrl(x3740).srcCtx("LogReg.scala:101:11") // CounterChainNew(ArrayBuffer(x3727))
    val x3739 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3728).name("x3739").ctrl(x3740).srcCtx("LogReg.scala:101:11") // UnrolledForeach(List(),x3728,Block(Const(())),ArrayBuffer(List(b2330)),ArrayBuffer(List(b2331)))
    val b2330 = CounterIter(x3727, None).name("b2330").ctrl(x3739) // b2330
    val b2331 = Const(true).name("b2331").ctrl(x3739) // b2331
    val x3729 = OpDef(op=BitAnd, inputs=List(b2331, b2191)).name("x3729").ctrl(x3739).srcCtx("UnrollingBase.scala:28:66") // And(b2331,b2191)
    val x3730 = LoadBanks(List(x3666_d0_b0), List(b2330)).name("x3730").ctrl(x3739).srcCtx("LogReg.scala:101:11") // ParSRAMLoad(x3666,List(ArrayBuffer(b2330)),List(x3729))
    val x3731 = x3730 // x3731 = VectorApply(x3730,0)
    val x3732 = LoadBanks(List(x3608_d1_b0), List(b2330)).name("x3732").ctrl(x3739).srcCtx("LogReg.scala:101:11") // ParSRAMLoad(x3608,List(ArrayBuffer(b2330)),List(x3729))
    val x3733 = x3732 // x3733 = VectorApply(x3732,0)
    val x3734 = OpDef(op=BitAnd, inputs=List(b2199, b2191)).name("x3734").ctrl(x3739).srcCtx("LogReg.scala:101:11") // And(b2199,b2191)
    val x3735 = OpDef(op=BitAnd, inputs=List(x3734, x3729)).name("x3735").ctrl(x3739).srcCtx("LogReg.scala:101:11") // And(x3734,x3729)
    val x3736 = OpDef(op=FixEql, inputs=List(b2198, Const(0))).name("x3736").ctrl(x3739).srcCtx("LogReg.scala:101:11") // FixEql(b2198,Const(0))
    val x3737 = ReduceAccumOp(op=FltAdd, input=x3731, accum=x3733).name("x3737").ctrl(x3739).srcCtx("LogReg.scala:101:15") // FltAdd(x3731,x3733)
    val x3738 = StoreBanks(List(x3608_d0_b0, x3608_d1_b0), List(b2330), x3737).name("x3738").ctrl(x3739).srcCtx("LogReg.scala:101:11") // ParSRAMStore(x3608,List(ArrayBuffer(b2330)),List(x3737),List(x3729))
    val x3741 = Counter(min=Const(0), max=Const(192), step=Const(1), par=1).name("x3741").ctrl(x3752).srcCtx("LogReg.scala:103:19") // CounterNew(Const(0),Const(192),Const(1),Const(1))
    val x3742 = CounterChain(List(x3741)).name("x3742").ctrl(x3752).srcCtx("LogReg.scala:103:25") // CounterChainNew(List(x3741))
    val x3751 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3742).name("x3751").ctrl(x3752).srcCtx("LogReg.scala:103:25") // UnrolledForeach(List(b2191),x3742,Block(Const(())),List(List(b2346)),List(List(b2347)))
    val b2346 = CounterIter(x3741, None).name("b2346").ctrl(x3751) // b2346
    val b2347 = Const(true).name("b2347").ctrl(x3751) // b2347
    val x3743 = OpDef(op=BitAnd, inputs=List(b2347, b2191)).name("x3743").ctrl(x3751).srcCtx("UnrollingBase.scala:28:66") // And(b2347,b2191)
    val x3744 = LoadBanks(List(x3586_d1_b0), List(b2346)).name("x3744").ctrl(x3751).srcCtx("LogReg.scala:103:50") // ParSRAMLoad(x3586,List(List(b2346)),List(x3743))
    val x3745 = x3744 // x3745 = VectorApply(x3744,0)
    val x3746 = LoadBanks(List(x3608_d0_b0), List(b2346)).name("x3746").ctrl(x3751).srcCtx("LogReg.scala:103:60") // ParSRAMLoad(x3608,List(List(b2346)),List(x3743))
    val x3747 = x3746 // x3747 = VectorApply(x3746,0)
    val x3748 = OpDef(op=FltMul, inputs=List(x3747, Const(1.0E-7))).name("x3748").ctrl(x3751).srcCtx("LogReg.scala:103:64") // FltMul(x3747,Const(0.0000001000000011686097423080354928970337))
    val x3749 = OpDef(op=FltAdd, inputs=List(x3745, x3748)).name("x3749").ctrl(x3751).srcCtx("LogReg.scala:103:54") // FltAdd(x3745,x3748)
    val x3750 = StoreBanks(List(x3586_d0_b0, x3586_d1_b0, x3586_d2_b0), List(b2346), x3749).name("x3750").ctrl(x3751).srcCtx("LogReg.scala:103:42") // ParSRAMStore(x3586,List(List(b2346)),List(x3749),List(x3743))
    val x3774 = UnitController(style=StreamPipe, level=OuterControl).name("x3774").ctrl(x3775).srcCtx("LogReg.scala:107:26") // UnitPipe(List(Const(true)),Block(Const(())))
    val b3857 = StreamOut(field="offset").name("b3857").ctrl(x3774).srcCtx("LogReg.scala:107:26") // x3753 = StreamOutNew(BurstCmdBus)
    isAccum(b3857) = false
    bufferDepthOf(b3857) = 1
    val b3858 = StreamOut(field="size").name("b3858").ctrl(x3774).srcCtx("LogReg.scala:107:26") // x3753 = StreamOutNew(BurstCmdBus)
    isAccum(b3858) = false
    bufferDepthOf(b3858) = 1
    val x3754 = StreamOut(field="data").name("x3754").ctrl(x3774).srcCtx("LogReg.scala:107:26") // x3754 = StreamOutNew(BurstFullDataBus())
    isAccum(x3754) = false
    bufferDepthOf(x3754) = 1
    val x3755 = StreamIn(field="ack").name("x3755").ctrl(x3774).srcCtx("LogReg.scala:107:26") // x3755 = StreamInNew(BurstAckBus)
    isAccum(x3755) = false
    bufferDepthOf(x3755) = 1
    val x3763 = UnitController(style=SeqPipe, level=InnerControl).name("x3763").ctrl(x3774).srcCtx("LogReg.scala:107:26") // UnitPipe(List(Const(true)),Block(x3762))
    val x3756 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x3757 = OpDef(op=FixSla, inputs=List(x3756, Const(2))).name("x3757").ctrl(x3763).srcCtx("LogReg.scala:107:26") // FixLsh(x3756,Const(2))
    val x3758 = x3757 // FixConvert(x3757,TRUE,_64,_0)
    val x3759 = DramAddress(x3580).name("x3759").ctrl(x3763).srcCtx("LogReg.scala:107:26") // GetDRAMAddress(x3580)
    val x3761_x3760 = OpDef(op=FixAdd, inputs=List(x3758, x3759)).name("x3761_x3760").ctrl(x3763).srcCtx("LogReg.scala:107:26") // FixAdd(x3758,x3759)
    // x3761 = SimpleStruct(ArrayBuffer((offset,x3760), (size,Const(768)), (isLoad,Const(false))))
    val x3762_b3859_b3857 = WriteMem(b3857, x3761_x3760).name("x3762_b3859_b3857").ctrl(x3763).srcCtx("LogReg.scala:107:26") // StreamWrite(x3753,x3761,Const(true))
    val x3762_b3860_b3858 = WriteMem(b3858, Const(768)).name("x3762_b3860_b3858").ctrl(x3763).srcCtx("LogReg.scala:107:26") // StreamWrite(x3753,x3761,Const(true))
    val x3764 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16).name("x3764").ctrl(x3774).srcCtx("LogReg.scala:107:26") // CounterNew(Const(0),Const(192),Const(1),Const(16))
    val x3765 = CounterChain(List(x3764)).name("x3765").ctrl(x3774).srcCtx("LogReg.scala:107:26") // CounterChainNew(List(x3764))
    val x3770 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3765).name("x3770").ctrl(x3774).srcCtx("LogReg.scala:107:26") // UnrolledForeach(List(Const(true)),x3765,Block(Const(())),List(List(b2371)),List(List(b2372)))
    val b2371 = CounterIter(x3764, None).name("b2371").ctrl(x3770) // b2371
    val b2372 = Const(true).name("b2372").ctrl(x3770) // b2372
    val x3766 = LoadBanks(List(x3586_d0_b0), List(b2371)).name("x3766").ctrl(x3770).srcCtx("LogReg.scala:107:26") // ParSRAMLoad(x3586,List(List(b2371)),List(b2372))
    val x3768_x3767 = x3766 // x3767 = VectorApply(x3766,0)
    // x3768 = SimpleStruct(ArrayBuffer((_1,x3767), (_2,Const(true))))
    val x3769_x3769_x3754 = WriteMem(x3754, x3768_x3767).name("x3769_x3769_x3754").ctrl(x3770).srcCtx("LogReg.scala:107:26") // ParStreamWrite(x3754,List(x3768),List(b2372))
    val x3771 = FringeDenseStore(dram=List(x3580), cmdStream=List(b3857, b3858), dataStream=List(x3754), ackStream=List(x3755)).name("x3771").ctrl(x3774).srcCtx("LogReg.scala:107:26") // FringeDenseStore(x3580,x3753,x3754,x3755)
    val x3773 = UnitController(style=SeqPipe, level=InnerControl).name("x3773").ctrl(x3774).srcCtx("LogReg.scala:107:26") // UnitPipe(List(Const(true)),Block(Const(())))
    val x3772_x3772 = ReadMem(x3755).name("x3772_x3772").ctrl(x3773).srcCtx("LogReg.scala:107:26") // StreamRead(x3755,Const(true))
    
  }
}
