import pir._
import pir.node._
import arch._
import prism.enums._

object OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024 extends PIRApp {
  def main(implicit design:PIRDesign) = {
    import design.pirmeta._
    val x3626 = ArgIn(init=0).name("x3626").ctrl(top).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:21:22:sizeA") // ArgInNew(Const(0))
    isAccum(x3626) = false
    bufferDepthOf(x3626) = 1
    boundOf(x3626) = 256
    val x3627_d0 = ArgIn(init=0).name("x3627_d0").ctrl(top).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:22:22:sizeB") // ArgInNew(Const(0))
    isAccum(x3627_d0) = false
    bufferDepthOf(x3627_d0) = 1
    boundOf(x3627_d0) = 24576
    val x3630 = ReadMem(x3626).name("x3630").ctrl(top).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:26:24") // RegRead(x3626)
    val x3631 = DRAM(dims=List(x3630)).name("x3631").ctrl(top).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:26:23:vec1") // x3631 = DRAMNew(ArrayBuffer(x3630),Const(0))
    val x3632 = ReadMem(x3627_d0).name("x3632").ctrl(top).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:27:24") // RegRead(x3627)
    val x3633 = DRAM(dims=List(x3632)).name("x3633").ctrl(top).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:27:23:vec2") // x3633 = DRAMNew(ArrayBuffer(x3632),Const(0))
    val x3634 = ReadMem(x3627_d0).name("x3634").ctrl(top).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:28:30") // RegRead(x3627)
    val x3635 = ReadMem(x3626).name("x3635").ctrl(top).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:28:23") // RegRead(x3626)
    val x3636 = DRAM(dims=List(x3635, x3634)).name("x3636").ctrl(top).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:28:22:out") // x3636 = DRAMNew(ArrayBuffer(x3635, x3634),Const(0))
    val x4124 = UnitController(style=SeqPipe, level=OuterControl).name("x4124").ctrl(top).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:33:11") // Hwblock(Block(Const(())),false)
    val x3639 = ReadMem(x3626).name("x3639").ctrl(x4124).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:34:15") // RegRead(x3626)
    val x3640 = Counter(min=Const(0), max=x3639, step=Const(64), par=1).name("x3640").ctrl(x4124).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:34:28") // CounterNew(Const(0),x3639,Const(64),Const(1))
    val x3641 = CounterChain(List(x3640)).name("x3641").ctrl(x4124).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:34:36") // CounterChainNew(List(x3640))
    val x4123 = LoopController(style=MetaPipe, level=OuterControl, cchain=x3641).name("x4123").ctrl(x4124).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:34:36") // UnrolledForeach(List(Const(true)),x3641,Block(Const(())),List(List(b966)),List(List(b967)))
    val b966 = CounterIter(x3640, Some(0)).name("b966").ctrl(x4123) // b966
    val b967 = Const(true).name("b967").ctrl(x4123) // b967
    val x3642_d0_b0 = SRAM(size=64, banking=Strided(banks=16, stride=1)).name("x3642_d0_b0").ctrl(x4123).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:35:25:b1") // x3642 = SRAMNew(ArrayBuffer(Const(64)))
    isAccum(x3642_d0_b0) = false
    bufferDepthOf(x3642_d0_b0) = 2
    val x3642_d1_b0 = SRAM(size=64, banking=Strided(banks=16, stride=1)).name("x3642_d1_b0").ctrl(x4123).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:35:25:b1") // x3642 = SRAMNew(ArrayBuffer(Const(64)))
    isAccum(x3642_d1_b0) = false
    bufferDepthOf(x3642_d1_b0) = 2
    val x3642_d2_b0 = SRAM(size=64, banking=Strided(banks=16, stride=1)).name("x3642_d2_b0").ctrl(x4123).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:35:25:b1") // x3642 = SRAMNew(ArrayBuffer(Const(64)))
    isAccum(x3642_d2_b0) = false
    bufferDepthOf(x3642_d2_b0) = 2
    val x3642_d3_b0 = SRAM(size=64, banking=Strided(banks=16, stride=1)).name("x3642_d3_b0").ctrl(x4123).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:35:25:b1") // x3642 = SRAMNew(ArrayBuffer(Const(64)))
    isAccum(x3642_d3_b0) = false
    bufferDepthOf(x3642_d3_b0) = 2
    val x3642_d4_b0 = SRAM(size=64, banking=Strided(banks=16, stride=1)).name("x3642_d4_b0").ctrl(x4123).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:35:25:b1") // x3642 = SRAMNew(ArrayBuffer(Const(64)))
    isAccum(x3642_d4_b0) = false
    bufferDepthOf(x3642_d4_b0) = 2
    val x3642_d5_b0 = SRAM(size=64, banking=Strided(banks=16, stride=1)).name("x3642_d5_b0").ctrl(x4123).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:35:25:b1") // x3642 = SRAMNew(ArrayBuffer(Const(64)))
    isAccum(x3642_d5_b0) = false
    bufferDepthOf(x3642_d5_b0) = 2
    val x3644 = UnitController(style=SeqPipe, level=InnerControl).name("x3644").ctrl(x4123).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:34:36") // UnitPipe(List(b967),Block(Const(())))
    val x3643 = OpDef(op=FixAdd, inputs=List(b966, Const(64))).name("x3643").ctrl(x3644).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:36:26") // FixAdd(b966,Const(64))
    val x3663 = UnitController(style=StreamPipe, level=OuterControl).name("x3663").ctrl(x4123).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:36:12") // UnitPipe(List(b967),Block(Const(())))
    val b4141 = StreamOut(field="offset").name("b4141").ctrl(x3663).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:36:12") // x3645 = StreamOutNew(BurstCmdBus)
    isAccum(b4141) = false
    bufferDepthOf(b4141) = 1
    val b4142 = StreamOut(field="size").name("b4142").ctrl(x3663).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:36:12") // x3645 = StreamOutNew(BurstCmdBus)
    isAccum(b4142) = false
    bufferDepthOf(b4142) = 1
    val x3646 = StreamIn(field="data").name("x3646").ctrl(x3663).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:36:12") // x3646 = StreamInNew(BurstDataBus())
    isAccum(x3646) = false
    bufferDepthOf(x3646) = 1
    val x3654 = UnitController(style=SeqPipe, level=InnerControl).name("x3654").ctrl(x3663).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:36:12") // UnitPipe(List(b967),Block(x3653))
    val x3647 = b966 // FixConvert(b966,TRUE,_32,_0) (Same Type. No op)
    val x3648 = OpDef(op=FixSla, inputs=List(x3647, Const(2))).name("x3648").ctrl(x3654).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:36:12") // FixLsh(x3647,Const(2))
    val x3649 = x3648 // FixConvert(x3648,TRUE,_64,_0)
    val x3650 = DramAddress(x3631).name("x3650").ctrl(x3654).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:36:12") // GetDRAMAddress(x3631)
    val x3652_x3651 = OpDef(op=FixAdd, inputs=List(x3649, x3650)).name("x3652_x3651").ctrl(x3654).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:36:12") // FixAdd(x3649,x3650)
    // x3652 = SimpleStruct(ArrayBuffer((offset,x3651), (size,Const(256)), (isLoad,Const(true))))
    val x3653_b4143_b4141 = WriteMem(b4141, x3652_x3651).name("x3653_b4143_b4141").ctrl(x3654).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:36:12") // StreamWrite(x3645,x3652,b967)
    val x3653_b4144_b4142 = WriteMem(b4142, Const(256)).name("x3653_b4144_b4142").ctrl(x3654).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:36:12") // StreamWrite(x3645,x3652,b967)
    val x3655 = FringeDenseLoad(dram=List(x3631), cmdStream=List(b4141, b4142), dataStream=List(x3646)).name("x3655").ctrl(x3663).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:36:12") // FringeDenseLoad(x3631,x3645,x3646)
    val x3656 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x3656").ctrl(x3663).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:36:12") // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x3657 = CounterChain(List(x3656)).name("x3657").ctrl(x3663).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:36:12") // CounterChainNew(List(x3656))
    val x3662 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3657).name("x3662").ctrl(x3663).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:36:12") // UnrolledForeach(List(b967),x3657,Block(Const(())),List(List(b984)),List(List(b985)))
    val b984 = CounterIter(x3656, None).name("b984").ctrl(x3662) // b984
    val b985 = Const(true).name("b985").ctrl(x3662) // b985
    val x3658 = OpDef(op=BitAnd, inputs=List(b985, b967)).name("x3658").ctrl(x3662).srcCtx("UnrollingBase.scala:28:66") // And(b985,b967)
    val x3659_x3659 = ReadMem(x3646).name("x3659_x3659").ctrl(x3662).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:36:12") // ParStreamRead(x3646,List(x3658))
    val x3660_x3660 = x3659_x3659 // x3660 = VectorApply(x3659,0)
    val x3661 = StoreBanks(List(x3642_d0_b0, x3642_d5_b0, x3642_d1_b0, x3642_d2_b0, x3642_d3_b0, x3642_d4_b0), List(b984), x3660_x3660).name("x3661").ctrl(x3662).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:36:12") // ParSRAMStore(x3642,List(List(b984)),List(x3660),List(x3658))
    val x3664 = ReadMem(x3627_d0).name("x3664").ctrl(x4123).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:37:17") // RegRead(x3627)
    val x3665 = Counter(min=Const(0), max=x3664, step=Const(1024), par=6).name("x3665").ctrl(x4123).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:37:30") // CounterNew(Const(0),x3664,Const(1024),Const(6))
    val x3666 = CounterChain(List(x3665)).name("x3666").ctrl(x4123).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:37:39") // CounterChainNew(List(x3665))
    val x4122 = LoopController(style=MetaPipe, level=OuterControl, cchain=x3666).name("x4122").ctrl(x4123).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:37:39") // UnrolledForeach(List(b967),x3666,Block(Const(())),List(List(b995, b996, b997, b998, b999, b1000)),List(List(b1001, b1002, b1003, b1004, b1005, b1006)))
    val b995 = CounterIter(x3665, Some(0)).name("b995").ctrl(x4122) // b995
    val b1001 = Const(true).name("b1001").ctrl(x4122) // b1001
    val b996 = CounterIter(x3665, Some(1)).name("b996").ctrl(x4122) // b996
    val b1002 = Const(true).name("b1002").ctrl(x4122) // b1002
    val b997 = CounterIter(x3665, Some(2)).name("b997").ctrl(x4122) // b997
    val b1003 = Const(true).name("b1003").ctrl(x4122) // b1003
    val b998 = CounterIter(x3665, Some(3)).name("b998").ctrl(x4122) // b998
    val b1004 = Const(true).name("b1004").ctrl(x4122) // b1004
    val b999 = CounterIter(x3665, Some(4)).name("b999").ctrl(x4122) // b999
    val b1005 = Const(true).name("b1005").ctrl(x4122) // b1005
    val b1000 = CounterIter(x3665, Some(5)).name("b1000").ctrl(x4122) // b1000
    val b1006 = Const(true).name("b1006").ctrl(x4122) // b1006
    val x3667_d0_b0 = SRAM(size=1024, banking=Strided(banks=16, stride=1)).name("x3667_d0_b0").ctrl(x4122).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:38:27:b2") // x3667 = SRAMNew(ArrayBuffer(Const(1024)))
    isAccum(x3667_d0_b0) = false
    bufferDepthOf(x3667_d0_b0) = 2
    val x3668_d0_b0 = SRAM(size=1024, banking=Strided(banks=16, stride=1)).name("x3668_d0_b0").ctrl(x4122).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:38:27:b2") // x3668 = SRAMNew(ArrayBuffer(Const(1024)))
    isAccum(x3668_d0_b0) = false
    bufferDepthOf(x3668_d0_b0) = 2
    val x3669_d0_b0 = SRAM(size=1024, banking=Strided(banks=16, stride=1)).name("x3669_d0_b0").ctrl(x4122).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:38:27:b2") // x3669 = SRAMNew(ArrayBuffer(Const(1024)))
    isAccum(x3669_d0_b0) = false
    bufferDepthOf(x3669_d0_b0) = 2
    val x3670_d0_b0 = SRAM(size=1024, banking=Strided(banks=16, stride=1)).name("x3670_d0_b0").ctrl(x4122).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:38:27:b2") // x3670 = SRAMNew(ArrayBuffer(Const(1024)))
    isAccum(x3670_d0_b0) = false
    bufferDepthOf(x3670_d0_b0) = 2
    val x3671_d0_b0 = SRAM(size=1024, banking=Strided(banks=16, stride=1)).name("x3671_d0_b0").ctrl(x4122).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:38:27:b2") // x3671 = SRAMNew(ArrayBuffer(Const(1024)))
    isAccum(x3671_d0_b0) = false
    bufferDepthOf(x3671_d0_b0) = 2
    val x3672_d0_b0 = SRAM(size=1024, banking=Strided(banks=16, stride=1)).name("x3672_d0_b0").ctrl(x4122).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:38:27:b2") // x3672 = SRAMNew(ArrayBuffer(Const(1024)))
    isAccum(x3672_d0_b0) = false
    bufferDepthOf(x3672_d0_b0) = 2
    val x3685 = UnitController(style=ForkJoin, level=OuterControl).name("x3685").ctrl(x4122).srcCtx("UnrollingTransformer.scala:431:43") // ParallelPipe(List(b967),Block(Const(())))
    val x3674 = UnitController(style=SeqPipe, level=InnerControl).name("x3674").ctrl(x3685).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:37:39") // UnitPipe(List(b1001, b967),Block(Const(())))
    val x3673 = OpDef(op=FixAdd, inputs=List(b995, Const(1024))).name("x3673").ctrl(x3674).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:28") // FixAdd(b995,Const(1024))
    val x3676 = UnitController(style=SeqPipe, level=InnerControl).name("x3676").ctrl(x3685).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:37:39") // UnitPipe(List(b1002, b967),Block(Const(())))
    val x3675 = OpDef(op=FixAdd, inputs=List(b996, Const(1024))).name("x3675").ctrl(x3676).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:28") // FixAdd(b996,Const(1024))
    val x3678 = UnitController(style=SeqPipe, level=InnerControl).name("x3678").ctrl(x3685).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:37:39") // UnitPipe(List(b1003, b967),Block(Const(())))
    val x3677 = OpDef(op=FixAdd, inputs=List(b997, Const(1024))).name("x3677").ctrl(x3678).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:28") // FixAdd(b997,Const(1024))
    val x3680 = UnitController(style=SeqPipe, level=InnerControl).name("x3680").ctrl(x3685).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:37:39") // UnitPipe(List(b1004, b967),Block(Const(())))
    val x3679 = OpDef(op=FixAdd, inputs=List(b998, Const(1024))).name("x3679").ctrl(x3680).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:28") // FixAdd(b998,Const(1024))
    val x3682 = UnitController(style=SeqPipe, level=InnerControl).name("x3682").ctrl(x3685).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:37:39") // UnitPipe(List(b1005, b967),Block(Const(())))
    val x3681 = OpDef(op=FixAdd, inputs=List(b999, Const(1024))).name("x3681").ctrl(x3682).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:28") // FixAdd(b999,Const(1024))
    val x3684 = UnitController(style=SeqPipe, level=InnerControl).name("x3684").ctrl(x3685).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:37:39") // UnitPipe(List(b1006, b967),Block(Const(())))
    val x3683 = OpDef(op=FixAdd, inputs=List(b1000, Const(1024))).name("x3683").ctrl(x3684).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:28") // FixAdd(b1000,Const(1024))
    val x3812 = UnitController(style=ForkJoin, level=OuterControl).name("x3812").ctrl(x4122).srcCtx("UnrollingTransformer.scala:431:43") // ParallelPipe(List(b967),Block(Const(())))
    val x3706 = UnitController(style=StreamPipe, level=OuterControl).name("x3706").ctrl(x3812).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // UnitPipe(List(b1001, b967),Block(Const(())))
    val b4145 = StreamOut(field="offset").name("b4145").ctrl(x3706).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // x3686 = StreamOutNew(BurstCmdBus)
    isAccum(b4145) = false
    bufferDepthOf(b4145) = 1
    val b4146 = StreamOut(field="size").name("b4146").ctrl(x3706).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // x3686 = StreamOutNew(BurstCmdBus)
    isAccum(b4146) = false
    bufferDepthOf(b4146) = 1
    val x3687 = StreamIn(field="data").name("x3687").ctrl(x3706).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // x3687 = StreamInNew(BurstDataBus())
    isAccum(x3687) = false
    bufferDepthOf(x3687) = 1
    val x3696 = UnitController(style=SeqPipe, level=InnerControl).name("x3696").ctrl(x3706).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // UnitPipe(List(b1001, b967),Block(x3695))
    val x3688 = b995 // FixConvert(b995,TRUE,_32,_0) (Same Type. No op)
    val x3689 = OpDef(op=FixSla, inputs=List(x3688, Const(2))).name("x3689").ctrl(x3696).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // FixLsh(x3688,Const(2))
    val x3690 = x3689 // FixConvert(x3689,TRUE,_64,_0)
    val x3691 = DramAddress(x3633).name("x3691").ctrl(x3696).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // GetDRAMAddress(x3633)
    val x3693_x3692 = OpDef(op=FixAdd, inputs=List(x3690, x3691)).name("x3693_x3692").ctrl(x3696).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // FixAdd(x3690,x3691)
    // x3693 = SimpleStruct(ArrayBuffer((offset,x3692), (size,Const(4096)), (isLoad,Const(true))))
    val x3694 = OpDef(op=BitAnd, inputs=List(b1001, b967)).name("x3694").ctrl(x3696).srcCtx("UnrollingBase.scala:28:66") // And(b1001,b967)
    val x3695_b4147_b4145 = WriteMem(b4145, x3693_x3692).name("x3695_b4147_b4145").ctrl(x3696).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // StreamWrite(x3686,x3693,x3694)
    val x3695_b4148_b4146 = WriteMem(b4146, Const(4096)).name("x3695_b4148_b4146").ctrl(x3696).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // StreamWrite(x3686,x3693,x3694)
    val x3697 = FringeDenseLoad(dram=List(x3633), cmdStream=List(b4145, b4146), dataStream=List(x3687)).name("x3697").ctrl(x3706).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // FringeDenseLoad(x3633,x3686,x3687)
    val x3698 = Counter(min=Const(0), max=Const(1024), step=Const(1), par=16).name("x3698").ctrl(x3706).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // CounterNew(Const(0),Const(1024),Const(1),Const(16))
    val x3699 = CounterChain(List(x3698)).name("x3699").ctrl(x3706).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // CounterChainNew(List(x3698))
    val x3705 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3699).name("x3705").ctrl(x3706).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // UnrolledForeach(List(b1001, b967),x3699,Block(Const(())),List(List(b1040)),List(List(b1041)))
    val b1040 = CounterIter(x3698, None).name("b1040").ctrl(x3705) // b1040
    val b1041 = Const(true).name("b1041").ctrl(x3705) // b1041
    val x3700 = OpDef(op=BitAnd, inputs=List(b1041, b1001)).name("x3700").ctrl(x3705).srcCtx("UnrollingBase.scala:28:66") // And(b1041,b1001)
    val x3701 = OpDef(op=BitAnd, inputs=List(x3700, b967)).name("x3701").ctrl(x3705).srcCtx("UnrollingBase.scala:28:66") // And(x3700,b967)
    val x3702_x3702 = ReadMem(x3687).name("x3702_x3702").ctrl(x3705).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // ParStreamRead(x3687,List(x3701))
    val x3703_x3703 = x3702_x3702 // x3703 = VectorApply(x3702,0)
    val x3704 = StoreBanks(List(x3667_d0_b0), List(b1040), x3703_x3703).name("x3704").ctrl(x3705).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // ParSRAMStore(x3667,List(List(b1040)),List(x3703),List(x3701))
    val x3727 = UnitController(style=StreamPipe, level=OuterControl).name("x3727").ctrl(x3812).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // UnitPipe(List(b1002, b967),Block(Const(())))
    val b4149 = StreamOut(field="offset").name("b4149").ctrl(x3727).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // x3707 = StreamOutNew(BurstCmdBus)
    isAccum(b4149) = false
    bufferDepthOf(b4149) = 1
    val b4150 = StreamOut(field="size").name("b4150").ctrl(x3727).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // x3707 = StreamOutNew(BurstCmdBus)
    isAccum(b4150) = false
    bufferDepthOf(b4150) = 1
    val x3708 = StreamIn(field="data").name("x3708").ctrl(x3727).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // x3708 = StreamInNew(BurstDataBus())
    isAccum(x3708) = false
    bufferDepthOf(x3708) = 1
    val x3717 = UnitController(style=SeqPipe, level=InnerControl).name("x3717").ctrl(x3727).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // UnitPipe(List(b1002, b967),Block(x3716))
    val x3709 = b996 // FixConvert(b996,TRUE,_32,_0) (Same Type. No op)
    val x3710 = OpDef(op=FixSla, inputs=List(x3709, Const(2))).name("x3710").ctrl(x3717).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // FixLsh(x3709,Const(2))
    val x3711 = x3710 // FixConvert(x3710,TRUE,_64,_0)
    val x3712 = DramAddress(x3633).name("x3712").ctrl(x3717).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // GetDRAMAddress(x3633)
    val x3714_x3713 = OpDef(op=FixAdd, inputs=List(x3711, x3712)).name("x3714_x3713").ctrl(x3717).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // FixAdd(x3711,x3712)
    // x3714 = SimpleStruct(ArrayBuffer((offset,x3713), (size,Const(4096)), (isLoad,Const(true))))
    val x3715 = OpDef(op=BitAnd, inputs=List(b1002, b967)).name("x3715").ctrl(x3717).srcCtx("UnrollingBase.scala:28:66") // And(b1002,b967)
    val x3716_b4151_b4149 = WriteMem(b4149, x3714_x3713).name("x3716_b4151_b4149").ctrl(x3717).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // StreamWrite(x3707,x3714,x3715)
    val x3716_b4152_b4150 = WriteMem(b4150, Const(4096)).name("x3716_b4152_b4150").ctrl(x3717).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // StreamWrite(x3707,x3714,x3715)
    val x3718 = FringeDenseLoad(dram=List(x3633), cmdStream=List(b4149, b4150), dataStream=List(x3708)).name("x3718").ctrl(x3727).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // FringeDenseLoad(x3633,x3707,x3708)
    val x3719 = Counter(min=Const(0), max=Const(1024), step=Const(1), par=16).name("x3719").ctrl(x3727).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // CounterNew(Const(0),Const(1024),Const(1),Const(16))
    val x3720 = CounterChain(List(x3719)).name("x3720").ctrl(x3727).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // CounterChainNew(List(x3719))
    val x3726 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3720).name("x3726").ctrl(x3727).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // UnrolledForeach(List(b1002, b967),x3720,Block(Const(())),List(List(b1063)),List(List(b1064)))
    val b1063 = CounterIter(x3719, None).name("b1063").ctrl(x3726) // b1063
    val b1064 = Const(true).name("b1064").ctrl(x3726) // b1064
    val x3721 = OpDef(op=BitAnd, inputs=List(b1064, b1002)).name("x3721").ctrl(x3726).srcCtx("UnrollingBase.scala:28:66") // And(b1064,b1002)
    val x3722 = OpDef(op=BitAnd, inputs=List(x3721, b967)).name("x3722").ctrl(x3726).srcCtx("UnrollingBase.scala:28:66") // And(x3721,b967)
    val x3723_x3723 = ReadMem(x3708).name("x3723_x3723").ctrl(x3726).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // ParStreamRead(x3708,List(x3722))
    val x3724_x3724 = x3723_x3723 // x3724 = VectorApply(x3723,0)
    val x3725 = StoreBanks(List(x3668_d0_b0), List(b1063), x3724_x3724).name("x3725").ctrl(x3726).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // ParSRAMStore(x3668,List(List(b1063)),List(x3724),List(x3722))
    val x3748 = UnitController(style=StreamPipe, level=OuterControl).name("x3748").ctrl(x3812).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // UnitPipe(List(b1003, b967),Block(Const(())))
    val b4153 = StreamOut(field="offset").name("b4153").ctrl(x3748).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // x3728 = StreamOutNew(BurstCmdBus)
    isAccum(b4153) = false
    bufferDepthOf(b4153) = 1
    val b4154 = StreamOut(field="size").name("b4154").ctrl(x3748).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // x3728 = StreamOutNew(BurstCmdBus)
    isAccum(b4154) = false
    bufferDepthOf(b4154) = 1
    val x3729 = StreamIn(field="data").name("x3729").ctrl(x3748).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // x3729 = StreamInNew(BurstDataBus())
    isAccum(x3729) = false
    bufferDepthOf(x3729) = 1
    val x3738 = UnitController(style=SeqPipe, level=InnerControl).name("x3738").ctrl(x3748).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // UnitPipe(List(b1003, b967),Block(x3737))
    val x3730 = b997 // FixConvert(b997,TRUE,_32,_0) (Same Type. No op)
    val x3731 = OpDef(op=FixSla, inputs=List(x3730, Const(2))).name("x3731").ctrl(x3738).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // FixLsh(x3730,Const(2))
    val x3732 = x3731 // FixConvert(x3731,TRUE,_64,_0)
    val x3733 = DramAddress(x3633).name("x3733").ctrl(x3738).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // GetDRAMAddress(x3633)
    val x3735_x3734 = OpDef(op=FixAdd, inputs=List(x3732, x3733)).name("x3735_x3734").ctrl(x3738).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // FixAdd(x3732,x3733)
    // x3735 = SimpleStruct(ArrayBuffer((offset,x3734), (size,Const(4096)), (isLoad,Const(true))))
    val x3736 = OpDef(op=BitAnd, inputs=List(b1003, b967)).name("x3736").ctrl(x3738).srcCtx("UnrollingBase.scala:28:66") // And(b1003,b967)
    val x3737_b4155_b4153 = WriteMem(b4153, x3735_x3734).name("x3737_b4155_b4153").ctrl(x3738).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // StreamWrite(x3728,x3735,x3736)
    val x3737_b4156_b4154 = WriteMem(b4154, Const(4096)).name("x3737_b4156_b4154").ctrl(x3738).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // StreamWrite(x3728,x3735,x3736)
    val x3739 = FringeDenseLoad(dram=List(x3633), cmdStream=List(b4153, b4154), dataStream=List(x3729)).name("x3739").ctrl(x3748).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // FringeDenseLoad(x3633,x3728,x3729)
    val x3740 = Counter(min=Const(0), max=Const(1024), step=Const(1), par=16).name("x3740").ctrl(x3748).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // CounterNew(Const(0),Const(1024),Const(1),Const(16))
    val x3741 = CounterChain(List(x3740)).name("x3741").ctrl(x3748).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // CounterChainNew(List(x3740))
    val x3747 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3741).name("x3747").ctrl(x3748).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // UnrolledForeach(List(b1003, b967),x3741,Block(Const(())),List(List(b1086)),List(List(b1087)))
    val b1086 = CounterIter(x3740, None).name("b1086").ctrl(x3747) // b1086
    val b1087 = Const(true).name("b1087").ctrl(x3747) // b1087
    val x3742 = OpDef(op=BitAnd, inputs=List(b1087, b1003)).name("x3742").ctrl(x3747).srcCtx("UnrollingBase.scala:28:66") // And(b1087,b1003)
    val x3743 = OpDef(op=BitAnd, inputs=List(x3742, b967)).name("x3743").ctrl(x3747).srcCtx("UnrollingBase.scala:28:66") // And(x3742,b967)
    val x3744_x3744 = ReadMem(x3729).name("x3744_x3744").ctrl(x3747).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // ParStreamRead(x3729,List(x3743))
    val x3745_x3745 = x3744_x3744 // x3745 = VectorApply(x3744,0)
    val x3746 = StoreBanks(List(x3669_d0_b0), List(b1086), x3745_x3745).name("x3746").ctrl(x3747).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // ParSRAMStore(x3669,List(List(b1086)),List(x3745),List(x3743))
    val x3769 = UnitController(style=StreamPipe, level=OuterControl).name("x3769").ctrl(x3812).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // UnitPipe(List(b1004, b967),Block(Const(())))
    val b4157 = StreamOut(field="offset").name("b4157").ctrl(x3769).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // x3749 = StreamOutNew(BurstCmdBus)
    isAccum(b4157) = false
    bufferDepthOf(b4157) = 1
    val b4158 = StreamOut(field="size").name("b4158").ctrl(x3769).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // x3749 = StreamOutNew(BurstCmdBus)
    isAccum(b4158) = false
    bufferDepthOf(b4158) = 1
    val x3750 = StreamIn(field="data").name("x3750").ctrl(x3769).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // x3750 = StreamInNew(BurstDataBus())
    isAccum(x3750) = false
    bufferDepthOf(x3750) = 1
    val x3759 = UnitController(style=SeqPipe, level=InnerControl).name("x3759").ctrl(x3769).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // UnitPipe(List(b1004, b967),Block(x3758))
    val x3751 = b998 // FixConvert(b998,TRUE,_32,_0) (Same Type. No op)
    val x3752 = OpDef(op=FixSla, inputs=List(x3751, Const(2))).name("x3752").ctrl(x3759).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // FixLsh(x3751,Const(2))
    val x3753 = x3752 // FixConvert(x3752,TRUE,_64,_0)
    val x3754 = DramAddress(x3633).name("x3754").ctrl(x3759).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // GetDRAMAddress(x3633)
    val x3756_x3755 = OpDef(op=FixAdd, inputs=List(x3753, x3754)).name("x3756_x3755").ctrl(x3759).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // FixAdd(x3753,x3754)
    // x3756 = SimpleStruct(ArrayBuffer((offset,x3755), (size,Const(4096)), (isLoad,Const(true))))
    val x3757 = OpDef(op=BitAnd, inputs=List(b1004, b967)).name("x3757").ctrl(x3759).srcCtx("UnrollingBase.scala:28:66") // And(b1004,b967)
    val x3758_b4159_b4157 = WriteMem(b4157, x3756_x3755).name("x3758_b4159_b4157").ctrl(x3759).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // StreamWrite(x3749,x3756,x3757)
    val x3758_b4160_b4158 = WriteMem(b4158, Const(4096)).name("x3758_b4160_b4158").ctrl(x3759).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // StreamWrite(x3749,x3756,x3757)
    val x3760 = FringeDenseLoad(dram=List(x3633), cmdStream=List(b4157, b4158), dataStream=List(x3750)).name("x3760").ctrl(x3769).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // FringeDenseLoad(x3633,x3749,x3750)
    val x3761 = Counter(min=Const(0), max=Const(1024), step=Const(1), par=16).name("x3761").ctrl(x3769).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // CounterNew(Const(0),Const(1024),Const(1),Const(16))
    val x3762 = CounterChain(List(x3761)).name("x3762").ctrl(x3769).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // CounterChainNew(List(x3761))
    val x3768 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3762).name("x3768").ctrl(x3769).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // UnrolledForeach(List(b1004, b967),x3762,Block(Const(())),List(List(b1109)),List(List(b1110)))
    val b1109 = CounterIter(x3761, None).name("b1109").ctrl(x3768) // b1109
    val b1110 = Const(true).name("b1110").ctrl(x3768) // b1110
    val x3763 = OpDef(op=BitAnd, inputs=List(b1110, b1004)).name("x3763").ctrl(x3768).srcCtx("UnrollingBase.scala:28:66") // And(b1110,b1004)
    val x3764 = OpDef(op=BitAnd, inputs=List(x3763, b967)).name("x3764").ctrl(x3768).srcCtx("UnrollingBase.scala:28:66") // And(x3763,b967)
    val x3765_x3765 = ReadMem(x3750).name("x3765_x3765").ctrl(x3768).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // ParStreamRead(x3750,List(x3764))
    val x3766_x3766 = x3765_x3765 // x3766 = VectorApply(x3765,0)
    val x3767 = StoreBanks(List(x3670_d0_b0), List(b1109), x3766_x3766).name("x3767").ctrl(x3768).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // ParSRAMStore(x3670,List(List(b1109)),List(x3766),List(x3764))
    val x3790 = UnitController(style=StreamPipe, level=OuterControl).name("x3790").ctrl(x3812).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // UnitPipe(List(b1005, b967),Block(Const(())))
    val b4161 = StreamOut(field="offset").name("b4161").ctrl(x3790).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // x3770 = StreamOutNew(BurstCmdBus)
    isAccum(b4161) = false
    bufferDepthOf(b4161) = 1
    val b4162 = StreamOut(field="size").name("b4162").ctrl(x3790).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // x3770 = StreamOutNew(BurstCmdBus)
    isAccum(b4162) = false
    bufferDepthOf(b4162) = 1
    val x3771 = StreamIn(field="data").name("x3771").ctrl(x3790).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // x3771 = StreamInNew(BurstDataBus())
    isAccum(x3771) = false
    bufferDepthOf(x3771) = 1
    val x3780 = UnitController(style=SeqPipe, level=InnerControl).name("x3780").ctrl(x3790).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // UnitPipe(List(b1005, b967),Block(x3779))
    val x3772 = b999 // FixConvert(b999,TRUE,_32,_0) (Same Type. No op)
    val x3773 = OpDef(op=FixSla, inputs=List(x3772, Const(2))).name("x3773").ctrl(x3780).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // FixLsh(x3772,Const(2))
    val x3774 = x3773 // FixConvert(x3773,TRUE,_64,_0)
    val x3775 = DramAddress(x3633).name("x3775").ctrl(x3780).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // GetDRAMAddress(x3633)
    val x3777_x3776 = OpDef(op=FixAdd, inputs=List(x3774, x3775)).name("x3777_x3776").ctrl(x3780).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // FixAdd(x3774,x3775)
    // x3777 = SimpleStruct(ArrayBuffer((offset,x3776), (size,Const(4096)), (isLoad,Const(true))))
    val x3778 = OpDef(op=BitAnd, inputs=List(b1005, b967)).name("x3778").ctrl(x3780).srcCtx("UnrollingBase.scala:28:66") // And(b1005,b967)
    val x3779_b4163_b4161 = WriteMem(b4161, x3777_x3776).name("x3779_b4163_b4161").ctrl(x3780).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // StreamWrite(x3770,x3777,x3778)
    val x3779_b4164_b4162 = WriteMem(b4162, Const(4096)).name("x3779_b4164_b4162").ctrl(x3780).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // StreamWrite(x3770,x3777,x3778)
    val x3781 = FringeDenseLoad(dram=List(x3633), cmdStream=List(b4161, b4162), dataStream=List(x3771)).name("x3781").ctrl(x3790).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // FringeDenseLoad(x3633,x3770,x3771)
    val x3782 = Counter(min=Const(0), max=Const(1024), step=Const(1), par=16).name("x3782").ctrl(x3790).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // CounterNew(Const(0),Const(1024),Const(1),Const(16))
    val x3783 = CounterChain(List(x3782)).name("x3783").ctrl(x3790).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // CounterChainNew(List(x3782))
    val x3789 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3783).name("x3789").ctrl(x3790).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // UnrolledForeach(List(b1005, b967),x3783,Block(Const(())),List(List(b1132)),List(List(b1133)))
    val b1132 = CounterIter(x3782, None).name("b1132").ctrl(x3789) // b1132
    val b1133 = Const(true).name("b1133").ctrl(x3789) // b1133
    val x3784 = OpDef(op=BitAnd, inputs=List(b1133, b1005)).name("x3784").ctrl(x3789).srcCtx("UnrollingBase.scala:28:66") // And(b1133,b1005)
    val x3785 = OpDef(op=BitAnd, inputs=List(x3784, b967)).name("x3785").ctrl(x3789).srcCtx("UnrollingBase.scala:28:66") // And(x3784,b967)
    val x3786_x3786 = ReadMem(x3771).name("x3786_x3786").ctrl(x3789).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // ParStreamRead(x3771,List(x3785))
    val x3787_x3787 = x3786_x3786 // x3787 = VectorApply(x3786,0)
    val x3788 = StoreBanks(List(x3671_d0_b0), List(b1132), x3787_x3787).name("x3788").ctrl(x3789).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // ParSRAMStore(x3671,List(List(b1132)),List(x3787),List(x3785))
    val x3811 = UnitController(style=StreamPipe, level=OuterControl).name("x3811").ctrl(x3812).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // UnitPipe(List(b1006, b967),Block(Const(())))
    val b4165 = StreamOut(field="offset").name("b4165").ctrl(x3811).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // x3791 = StreamOutNew(BurstCmdBus)
    isAccum(b4165) = false
    bufferDepthOf(b4165) = 1
    val b4166 = StreamOut(field="size").name("b4166").ctrl(x3811).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // x3791 = StreamOutNew(BurstCmdBus)
    isAccum(b4166) = false
    bufferDepthOf(b4166) = 1
    val x3792 = StreamIn(field="data").name("x3792").ctrl(x3811).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // x3792 = StreamInNew(BurstDataBus())
    isAccum(x3792) = false
    bufferDepthOf(x3792) = 1
    val x3801 = UnitController(style=SeqPipe, level=InnerControl).name("x3801").ctrl(x3811).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // UnitPipe(List(b1006, b967),Block(x3800))
    val x3793 = b1000 // FixConvert(b1000,TRUE,_32,_0) (Same Type. No op)
    val x3794 = OpDef(op=FixSla, inputs=List(x3793, Const(2))).name("x3794").ctrl(x3801).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // FixLsh(x3793,Const(2))
    val x3795 = x3794 // FixConvert(x3794,TRUE,_64,_0)
    val x3796 = DramAddress(x3633).name("x3796").ctrl(x3801).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // GetDRAMAddress(x3633)
    val x3798_x3797 = OpDef(op=FixAdd, inputs=List(x3795, x3796)).name("x3798_x3797").ctrl(x3801).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // FixAdd(x3795,x3796)
    // x3798 = SimpleStruct(ArrayBuffer((offset,x3797), (size,Const(4096)), (isLoad,Const(true))))
    val x3799 = OpDef(op=BitAnd, inputs=List(b1006, b967)).name("x3799").ctrl(x3801).srcCtx("UnrollingBase.scala:28:66") // And(b1006,b967)
    val x3800_b4167_b4165 = WriteMem(b4165, x3798_x3797).name("x3800_b4167_b4165").ctrl(x3801).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // StreamWrite(x3791,x3798,x3799)
    val x3800_b4168_b4166 = WriteMem(b4166, Const(4096)).name("x3800_b4168_b4166").ctrl(x3801).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // StreamWrite(x3791,x3798,x3799)
    val x3802 = FringeDenseLoad(dram=List(x3633), cmdStream=List(b4165, b4166), dataStream=List(x3792)).name("x3802").ctrl(x3811).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // FringeDenseLoad(x3633,x3791,x3792)
    val x3803 = Counter(min=Const(0), max=Const(1024), step=Const(1), par=16).name("x3803").ctrl(x3811).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // CounterNew(Const(0),Const(1024),Const(1),Const(16))
    val x3804 = CounterChain(List(x3803)).name("x3804").ctrl(x3811).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // CounterChainNew(List(x3803))
    val x3810 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3804).name("x3810").ctrl(x3811).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // UnrolledForeach(List(b1006, b967),x3804,Block(Const(())),List(List(b1155)),List(List(b1156)))
    val b1155 = CounterIter(x3803, None).name("b1155").ctrl(x3810) // b1155
    val b1156 = Const(true).name("b1156").ctrl(x3810) // b1156
    val x3805 = OpDef(op=BitAnd, inputs=List(b1156, b1006)).name("x3805").ctrl(x3810).srcCtx("UnrollingBase.scala:28:66") // And(b1156,b1006)
    val x3806 = OpDef(op=BitAnd, inputs=List(x3805, b967)).name("x3806").ctrl(x3810).srcCtx("UnrollingBase.scala:28:66") // And(x3805,b967)
    val x3807_x3807 = ReadMem(x3792).name("x3807_x3807").ctrl(x3810).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // ParStreamRead(x3792,List(x3806))
    val x3808_x3808 = x3807_x3807 // x3808 = VectorApply(x3807,0)
    val x3809 = StoreBanks(List(x3672_d0_b0), List(b1155), x3808_x3808).name("x3809").ctrl(x3810).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:39:14") // ParSRAMStore(x3672,List(List(b1155)),List(x3808),List(x3806))
    val x3813_d0_b0 = SRAM(size=65536, banking=Strided(banks=16, stride=1)).name("x3813_d0_b0").ctrl(x4122).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:40:32:outTile") // x3813 = SRAMNew(ArrayBuffer(Const(64), Const(1024)))
    isAccum(x3813_d0_b0) = false
    bufferDepthOf(x3813_d0_b0) = 3
    val x3814_d0_b0 = SRAM(size=65536, banking=Strided(banks=16, stride=1)).name("x3814_d0_b0").ctrl(x4122).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:40:32:outTile") // x3814 = SRAMNew(ArrayBuffer(Const(64), Const(1024)))
    isAccum(x3814_d0_b0) = false
    bufferDepthOf(x3814_d0_b0) = 3
    val x3815_d0_b0 = SRAM(size=65536, banking=Strided(banks=16, stride=1)).name("x3815_d0_b0").ctrl(x4122).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:40:32:outTile") // x3815 = SRAMNew(ArrayBuffer(Const(64), Const(1024)))
    isAccum(x3815_d0_b0) = false
    bufferDepthOf(x3815_d0_b0) = 3
    val x3816_d0_b0 = SRAM(size=65536, banking=Strided(banks=16, stride=1)).name("x3816_d0_b0").ctrl(x4122).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:40:32:outTile") // x3816 = SRAMNew(ArrayBuffer(Const(64), Const(1024)))
    isAccum(x3816_d0_b0) = false
    bufferDepthOf(x3816_d0_b0) = 3
    val x3817_d0_b0 = SRAM(size=65536, banking=Strided(banks=16, stride=1)).name("x3817_d0_b0").ctrl(x4122).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:40:32:outTile") // x3817 = SRAMNew(ArrayBuffer(Const(64), Const(1024)))
    isAccum(x3817_d0_b0) = false
    bufferDepthOf(x3817_d0_b0) = 3
    val x3818_d0_b0 = SRAM(size=65536, banking=Strided(banks=16, stride=1)).name("x3818_d0_b0").ctrl(x4122).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:40:32:outTile") // x3818 = SRAMNew(ArrayBuffer(Const(64), Const(1024)))
    isAccum(x3818_d0_b0) = false
    bufferDepthOf(x3818_d0_b0) = 3
    val x3891 = UnitController(style=ForkJoin, level=OuterControl).name("x3891").ctrl(x4122).srcCtx("UnrollingTransformer.scala:431:43") // ParallelPipe(List(b967),Block(Const(())))
    val x3819 = Counter(min=Const(0), max=Const(1024), step=Const(1), par=16).name("x3819").ctrl(x3891).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:41:36") // CounterNew(Const(0),Const(1024),Const(1),Const(16))
    val x3820 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1).name("x3820").ctrl(x3891).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:41:23") // CounterNew(Const(0),Const(64),Const(1),Const(1))
    val x3821 = CounterChain(List(x3820,x3819)).name("x3821").ctrl(x3891).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:41:44") // CounterChainNew(List(x3820, x3819))
    val x3830 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3821).name("x3830").ctrl(x3891).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:41:44") // UnrolledForeach(List(b1001, b967),x3821,Block(Const(())),List(List(b1189), List(b1190)),List(List(b1191), List(b1192)))
    val b1189 = CounterIter(x3820, Some(0)).name("b1189").ctrl(x3830) // b1189
    val b1191 = Const(true).name("b1191").ctrl(x3830) // b1191
    val b1190 = CounterIter(x3819, None).name("b1190").ctrl(x3830) // b1190
    val b1192 = Const(true).name("b1192").ctrl(x3830) // b1192
    val x3822 = OpDef(op=BitAnd, inputs=List(b1191, b1192)).name("x3822").ctrl(x3830).srcCtx("UnrollingBase.scala:28:66") // And(b1191,b1192)
    val x3823 = OpDef(op=BitAnd, inputs=List(b1001, b967)).name("x3823").ctrl(x3830).srcCtx("UnrollingBase.scala:28:66") // And(b1001,b967)
    val x3824 = OpDef(op=BitAnd, inputs=List(x3822, x3823)).name("x3824").ctrl(x3830).srcCtx("UnrollingBase.scala:28:66") // And(x3822,x3823)
    val x3825 = LoadBanks(List(x3642_d0_b0), List(b1189)).name("x3825").ctrl(x3830).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:41:77") // SRAMLoad(x3642,ArrayBuffer(Const(64)),List(b1189),Const(0),x3824)
    val x3826 = LoadBanks(List(x3667_d0_b0), List(b1190)).name("x3826").ctrl(x3830).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:41:86") // ParSRAMLoad(x3667,List(List(b1190)),List(x3824))
    val x3827 = x3826 // x3827 = VectorApply(x3826,0)
    val x3828 = OpDef(op=FixMul, inputs=List(x3825, x3827)).name("x3828").ctrl(x3830).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:41:82") // FixMul(x3825,x3827)
    val x3829 = StoreBanks(List(x3813_d0_b0), List(b1189, b1190), x3828).name("x3829").ctrl(x3830).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:41:73") // ParSRAMStore(x3813,List(List(b1189, b1190)),List(x3828),List(x3824))
    val x3831 = Counter(min=Const(0), max=Const(1024), step=Const(1), par=16).name("x3831").ctrl(x3891).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:41:36") // CounterNew(Const(0),Const(1024),Const(1),Const(16))
    val x3832 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1).name("x3832").ctrl(x3891).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:41:23") // CounterNew(Const(0),Const(64),Const(1),Const(1))
    val x3833 = CounterChain(List(x3832,x3831)).name("x3833").ctrl(x3891).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:41:44") // CounterChainNew(List(x3832, x3831))
    val x3842 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3833).name("x3842").ctrl(x3891).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:41:44") // UnrolledForeach(List(b1002, b967),x3833,Block(Const(())),List(List(b1202), List(b1203)),List(List(b1204), List(b1205)))
    val b1202 = CounterIter(x3832, Some(0)).name("b1202").ctrl(x3842) // b1202
    val b1204 = Const(true).name("b1204").ctrl(x3842) // b1204
    val b1203 = CounterIter(x3831, None).name("b1203").ctrl(x3842) // b1203
    val b1205 = Const(true).name("b1205").ctrl(x3842) // b1205
    val x3834 = OpDef(op=BitAnd, inputs=List(b1204, b1205)).name("x3834").ctrl(x3842).srcCtx("UnrollingBase.scala:28:66") // And(b1204,b1205)
    val x3835 = OpDef(op=BitAnd, inputs=List(b1002, b967)).name("x3835").ctrl(x3842).srcCtx("UnrollingBase.scala:28:66") // And(b1002,b967)
    val x3836 = OpDef(op=BitAnd, inputs=List(x3834, x3835)).name("x3836").ctrl(x3842).srcCtx("UnrollingBase.scala:28:66") // And(x3834,x3835)
    val x3837 = LoadBanks(List(x3642_d1_b0), List(b1202)).name("x3837").ctrl(x3842).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:41:77") // SRAMLoad(x3642,ArrayBuffer(Const(64)),List(b1202),Const(0),x3836)
    val x3838 = LoadBanks(List(x3668_d0_b0), List(b1203)).name("x3838").ctrl(x3842).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:41:86") // ParSRAMLoad(x3668,List(List(b1203)),List(x3836))
    val x3839 = x3838 // x3839 = VectorApply(x3838,0)
    val x3840 = OpDef(op=FixMul, inputs=List(x3837, x3839)).name("x3840").ctrl(x3842).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:41:82") // FixMul(x3837,x3839)
    val x3841 = StoreBanks(List(x3814_d0_b0), List(b1202, b1203), x3840).name("x3841").ctrl(x3842).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:41:73") // ParSRAMStore(x3814,List(List(b1202, b1203)),List(x3840),List(x3836))
    val x3843 = Counter(min=Const(0), max=Const(1024), step=Const(1), par=16).name("x3843").ctrl(x3891).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:41:36") // CounterNew(Const(0),Const(1024),Const(1),Const(16))
    val x3844 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1).name("x3844").ctrl(x3891).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:41:23") // CounterNew(Const(0),Const(64),Const(1),Const(1))
    val x3845 = CounterChain(List(x3844,x3843)).name("x3845").ctrl(x3891).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:41:44") // CounterChainNew(List(x3844, x3843))
    val x3854 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3845).name("x3854").ctrl(x3891).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:41:44") // UnrolledForeach(List(b1003, b967),x3845,Block(Const(())),List(List(b1215), List(b1216)),List(List(b1217), List(b1218)))
    val b1215 = CounterIter(x3844, Some(0)).name("b1215").ctrl(x3854) // b1215
    val b1217 = Const(true).name("b1217").ctrl(x3854) // b1217
    val b1216 = CounterIter(x3843, None).name("b1216").ctrl(x3854) // b1216
    val b1218 = Const(true).name("b1218").ctrl(x3854) // b1218
    val x3846 = OpDef(op=BitAnd, inputs=List(b1217, b1218)).name("x3846").ctrl(x3854).srcCtx("UnrollingBase.scala:28:66") // And(b1217,b1218)
    val x3847 = OpDef(op=BitAnd, inputs=List(b1003, b967)).name("x3847").ctrl(x3854).srcCtx("UnrollingBase.scala:28:66") // And(b1003,b967)
    val x3848 = OpDef(op=BitAnd, inputs=List(x3846, x3847)).name("x3848").ctrl(x3854).srcCtx("UnrollingBase.scala:28:66") // And(x3846,x3847)
    val x3849 = LoadBanks(List(x3642_d2_b0), List(b1215)).name("x3849").ctrl(x3854).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:41:77") // SRAMLoad(x3642,ArrayBuffer(Const(64)),List(b1215),Const(0),x3848)
    val x3850 = LoadBanks(List(x3669_d0_b0), List(b1216)).name("x3850").ctrl(x3854).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:41:86") // ParSRAMLoad(x3669,List(List(b1216)),List(x3848))
    val x3851 = x3850 // x3851 = VectorApply(x3850,0)
    val x3852 = OpDef(op=FixMul, inputs=List(x3849, x3851)).name("x3852").ctrl(x3854).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:41:82") // FixMul(x3849,x3851)
    val x3853 = StoreBanks(List(x3815_d0_b0), List(b1215, b1216), x3852).name("x3853").ctrl(x3854).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:41:73") // ParSRAMStore(x3815,List(List(b1215, b1216)),List(x3852),List(x3848))
    val x3855 = Counter(min=Const(0), max=Const(1024), step=Const(1), par=16).name("x3855").ctrl(x3891).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:41:36") // CounterNew(Const(0),Const(1024),Const(1),Const(16))
    val x3856 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1).name("x3856").ctrl(x3891).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:41:23") // CounterNew(Const(0),Const(64),Const(1),Const(1))
    val x3857 = CounterChain(List(x3856,x3855)).name("x3857").ctrl(x3891).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:41:44") // CounterChainNew(List(x3856, x3855))
    val x3866 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3857).name("x3866").ctrl(x3891).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:41:44") // UnrolledForeach(List(b1004, b967),x3857,Block(Const(())),List(List(b1228), List(b1229)),List(List(b1230), List(b1231)))
    val b1228 = CounterIter(x3856, Some(0)).name("b1228").ctrl(x3866) // b1228
    val b1230 = Const(true).name("b1230").ctrl(x3866) // b1230
    val b1229 = CounterIter(x3855, None).name("b1229").ctrl(x3866) // b1229
    val b1231 = Const(true).name("b1231").ctrl(x3866) // b1231
    val x3858 = OpDef(op=BitAnd, inputs=List(b1230, b1231)).name("x3858").ctrl(x3866).srcCtx("UnrollingBase.scala:28:66") // And(b1230,b1231)
    val x3859 = OpDef(op=BitAnd, inputs=List(b1004, b967)).name("x3859").ctrl(x3866).srcCtx("UnrollingBase.scala:28:66") // And(b1004,b967)
    val x3860 = OpDef(op=BitAnd, inputs=List(x3858, x3859)).name("x3860").ctrl(x3866).srcCtx("UnrollingBase.scala:28:66") // And(x3858,x3859)
    val x3861 = LoadBanks(List(x3642_d3_b0), List(b1228)).name("x3861").ctrl(x3866).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:41:77") // SRAMLoad(x3642,ArrayBuffer(Const(64)),List(b1228),Const(0),x3860)
    val x3862 = LoadBanks(List(x3670_d0_b0), List(b1229)).name("x3862").ctrl(x3866).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:41:86") // ParSRAMLoad(x3670,List(List(b1229)),List(x3860))
    val x3863 = x3862 // x3863 = VectorApply(x3862,0)
    val x3864 = OpDef(op=FixMul, inputs=List(x3861, x3863)).name("x3864").ctrl(x3866).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:41:82") // FixMul(x3861,x3863)
    val x3865 = StoreBanks(List(x3816_d0_b0), List(b1228, b1229), x3864).name("x3865").ctrl(x3866).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:41:73") // ParSRAMStore(x3816,List(List(b1228, b1229)),List(x3864),List(x3860))
    val x3867 = Counter(min=Const(0), max=Const(1024), step=Const(1), par=16).name("x3867").ctrl(x3891).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:41:36") // CounterNew(Const(0),Const(1024),Const(1),Const(16))
    val x3868 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1).name("x3868").ctrl(x3891).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:41:23") // CounterNew(Const(0),Const(64),Const(1),Const(1))
    val x3869 = CounterChain(List(x3868,x3867)).name("x3869").ctrl(x3891).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:41:44") // CounterChainNew(List(x3868, x3867))
    val x3878 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3869).name("x3878").ctrl(x3891).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:41:44") // UnrolledForeach(List(b1005, b967),x3869,Block(Const(())),List(List(b1241), List(b1242)),List(List(b1243), List(b1244)))
    val b1241 = CounterIter(x3868, Some(0)).name("b1241").ctrl(x3878) // b1241
    val b1243 = Const(true).name("b1243").ctrl(x3878) // b1243
    val b1242 = CounterIter(x3867, None).name("b1242").ctrl(x3878) // b1242
    val b1244 = Const(true).name("b1244").ctrl(x3878) // b1244
    val x3870 = OpDef(op=BitAnd, inputs=List(b1243, b1244)).name("x3870").ctrl(x3878).srcCtx("UnrollingBase.scala:28:66") // And(b1243,b1244)
    val x3871 = OpDef(op=BitAnd, inputs=List(b1005, b967)).name("x3871").ctrl(x3878).srcCtx("UnrollingBase.scala:28:66") // And(b1005,b967)
    val x3872 = OpDef(op=BitAnd, inputs=List(x3870, x3871)).name("x3872").ctrl(x3878).srcCtx("UnrollingBase.scala:28:66") // And(x3870,x3871)
    val x3873 = LoadBanks(List(x3642_d4_b0), List(b1241)).name("x3873").ctrl(x3878).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:41:77") // SRAMLoad(x3642,ArrayBuffer(Const(64)),List(b1241),Const(0),x3872)
    val x3874 = LoadBanks(List(x3671_d0_b0), List(b1242)).name("x3874").ctrl(x3878).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:41:86") // ParSRAMLoad(x3671,List(List(b1242)),List(x3872))
    val x3875 = x3874 // x3875 = VectorApply(x3874,0)
    val x3876 = OpDef(op=FixMul, inputs=List(x3873, x3875)).name("x3876").ctrl(x3878).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:41:82") // FixMul(x3873,x3875)
    val x3877 = StoreBanks(List(x3817_d0_b0), List(b1241, b1242), x3876).name("x3877").ctrl(x3878).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:41:73") // ParSRAMStore(x3817,List(List(b1241, b1242)),List(x3876),List(x3872))
    val x3879 = Counter(min=Const(0), max=Const(1024), step=Const(1), par=16).name("x3879").ctrl(x3891).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:41:36") // CounterNew(Const(0),Const(1024),Const(1),Const(16))
    val x3880 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1).name("x3880").ctrl(x3891).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:41:23") // CounterNew(Const(0),Const(64),Const(1),Const(1))
    val x3881 = CounterChain(List(x3880,x3879)).name("x3881").ctrl(x3891).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:41:44") // CounterChainNew(List(x3880, x3879))
    val x3890 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3881).name("x3890").ctrl(x3891).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:41:44") // UnrolledForeach(List(b1006, b967),x3881,Block(Const(())),List(List(b1254), List(b1255)),List(List(b1256), List(b1257)))
    val b1254 = CounterIter(x3880, Some(0)).name("b1254").ctrl(x3890) // b1254
    val b1256 = Const(true).name("b1256").ctrl(x3890) // b1256
    val b1255 = CounterIter(x3879, None).name("b1255").ctrl(x3890) // b1255
    val b1257 = Const(true).name("b1257").ctrl(x3890) // b1257
    val x3882 = OpDef(op=BitAnd, inputs=List(b1256, b1257)).name("x3882").ctrl(x3890).srcCtx("UnrollingBase.scala:28:66") // And(b1256,b1257)
    val x3883 = OpDef(op=BitAnd, inputs=List(b1006, b967)).name("x3883").ctrl(x3890).srcCtx("UnrollingBase.scala:28:66") // And(b1006,b967)
    val x3884 = OpDef(op=BitAnd, inputs=List(x3882, x3883)).name("x3884").ctrl(x3890).srcCtx("UnrollingBase.scala:28:66") // And(x3882,x3883)
    val x3885 = LoadBanks(List(x3642_d5_b0), List(b1254)).name("x3885").ctrl(x3890).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:41:77") // SRAMLoad(x3642,ArrayBuffer(Const(64)),List(b1254),Const(0),x3884)
    val x3886 = LoadBanks(List(x3672_d0_b0), List(b1255)).name("x3886").ctrl(x3890).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:41:86") // ParSRAMLoad(x3672,List(List(b1255)),List(x3884))
    val x3887 = x3886 // x3887 = VectorApply(x3886,0)
    val x3888 = OpDef(op=FixMul, inputs=List(x3885, x3887)).name("x3888").ctrl(x3890).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:41:82") // FixMul(x3885,x3887)
    val x3889 = StoreBanks(List(x3818_d0_b0), List(b1254, b1255), x3888).name("x3889").ctrl(x3890).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:41:73") // ParSRAMStore(x3818,List(List(b1254, b1255)),List(x3888),List(x3884))
    val x3904 = UnitController(style=ForkJoin, level=OuterControl).name("x3904").ctrl(x4122).srcCtx("UnrollingTransformer.scala:431:43") // ParallelPipe(List(b967),Block(Const(())))
    val x3893 = UnitController(style=SeqPipe, level=InnerControl).name("x3893").ctrl(x3904).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:37:39") // UnitPipe(List(b1001, b967),Block(Const(())))
    val x3892 = OpDef(op=FixAdd, inputs=List(b966, Const(64))).name("x3892").ctrl(x3893).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:19") // FixAdd(b966,Const(64))
    val x3895 = UnitController(style=SeqPipe, level=InnerControl).name("x3895").ctrl(x3904).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:37:39") // UnitPipe(List(b1002, b967),Block(Const(())))
    val x3894 = OpDef(op=FixAdd, inputs=List(b966, Const(64))).name("x3894").ctrl(x3895).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:19") // FixAdd(b966,Const(64))
    val x3897 = UnitController(style=SeqPipe, level=InnerControl).name("x3897").ctrl(x3904).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:37:39") // UnitPipe(List(b1003, b967),Block(Const(())))
    val x3896 = OpDef(op=FixAdd, inputs=List(b966, Const(64))).name("x3896").ctrl(x3897).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:19") // FixAdd(b966,Const(64))
    val x3899 = UnitController(style=SeqPipe, level=InnerControl).name("x3899").ctrl(x3904).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:37:39") // UnitPipe(List(b1004, b967),Block(Const(())))
    val x3898 = OpDef(op=FixAdd, inputs=List(b966, Const(64))).name("x3898").ctrl(x3899).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:19") // FixAdd(b966,Const(64))
    val x3901 = UnitController(style=SeqPipe, level=InnerControl).name("x3901").ctrl(x3904).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:37:39") // UnitPipe(List(b1005, b967),Block(Const(())))
    val x3900 = OpDef(op=FixAdd, inputs=List(b966, Const(64))).name("x3900").ctrl(x3901).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:19") // FixAdd(b966,Const(64))
    val x3903 = UnitController(style=SeqPipe, level=InnerControl).name("x3903").ctrl(x3904).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:37:39") // UnitPipe(List(b1006, b967),Block(Const(())))
    val x3902 = OpDef(op=FixAdd, inputs=List(b966, Const(64))).name("x3902").ctrl(x3903).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:19") // FixAdd(b966,Const(64))
    val x4121 = UnitController(style=ForkJoin, level=OuterControl).name("x4121").ctrl(x4122).srcCtx("UnrollingTransformer.scala:431:43") // ParallelPipe(List(b967),Block(Const(())))
    val x3905 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1).name("x3905").ctrl(x4121).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // CounterNew(Const(0),Const(64),Const(1),Const(1))
    val x3906 = CounterChain(List(x3905)).name("x3906").ctrl(x4121).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // CounterChainNew(List(x3905))
    val x3940 = LoopController(style=StreamPipe, level=OuterControl, cchain=x3906).name("x3940").ctrl(x4121).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // UnrolledForeach(List(b1001, b967),x3906,Block(Const(())),List(List(b1293)),List(List(b1294)))
    val b1293 = CounterIter(x3905, Some(0)).name("b1293").ctrl(x3940) // b1293
    val b1294 = Const(true).name("b1294").ctrl(x3940) // b1294
    val b4169 = StreamOut(field="offset").name("b4169").ctrl(x3940).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // x3907 = StreamOutNew(BurstCmdBus)
    isAccum(b4169) = false
    bufferDepthOf(b4169) = 1
    val b4170 = StreamOut(field="size").name("b4170").ctrl(x3940).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // x3907 = StreamOutNew(BurstCmdBus)
    isAccum(b4170) = false
    bufferDepthOf(b4170) = 1
    val x3908 = StreamOut(field="data").name("x3908").ctrl(x3940).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // x3908 = StreamOutNew(BurstFullDataBus())
    isAccum(x3908) = false
    bufferDepthOf(x3908) = 1
    val x3909 = StreamIn(field="ack").name("x3909").ctrl(x3940).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // x3909 = StreamInNew(BurstAckBus)
    isAccum(x3909) = false
    bufferDepthOf(x3909) = 1
    val x3924 = UnitController(style=SeqPipe, level=InnerControl).name("x3924").ctrl(x3940).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // UnitPipe(List(b1294, b1001, b967),Block(x3923))
    val x3910 = OpDef(op=FixAdd, inputs=List(b966, b1293)).name("x3910").ctrl(x3924).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // FixAdd(b966,b1293)
    val x3911 = x3910 // FixConvert(x3910,TRUE,_32,_0) (Same Type. No op)
    val x3912 = ReadMem(x3627_d0).name("x3912").ctrl(x3924).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:28:30") // RegRead(x3627)
    val x3913 = OpDef(op=FixMul, inputs=List(x3911, x3912)).name("x3913").ctrl(x3924).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // FixMul(x3911,x3912)
    val x3914 = b995 // FixConvert(b995,TRUE,_32,_0) (Same Type. No op)
    val x3915 = OpDef(op=FixAdd, inputs=List(x3913, x3914)).name("x3915").ctrl(x3924).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // FixAdd(x3913,x3914)
    val x3916 = OpDef(op=FixSla, inputs=List(x3915, Const(2))).name("x3916").ctrl(x3924).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // FixLsh(x3915,Const(2))
    val x3917 = x3916 // FixConvert(x3916,TRUE,_64,_0)
    val x3918 = DramAddress(x3636).name("x3918").ctrl(x3924).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // GetDRAMAddress(x3636)
    val x3920_x3919 = OpDef(op=FixAdd, inputs=List(x3917, x3918)).name("x3920_x3919").ctrl(x3924).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // FixAdd(x3917,x3918)
    // x3920 = SimpleStruct(ArrayBuffer((offset,x3919), (size,Const(4096)), (isLoad,Const(false))))
    val x3921 = OpDef(op=BitAnd, inputs=List(b1294, b1001)).name("x3921").ctrl(x3924).srcCtx("UnrollingBase.scala:28:66") // And(b1294,b1001)
    val x3922 = OpDef(op=BitAnd, inputs=List(x3921, b967)).name("x3922").ctrl(x3924).srcCtx("UnrollingBase.scala:28:66") // And(x3921,b967)
    val x3923_b4171_b4169 = WriteMem(b4169, x3920_x3919).name("x3923_b4171_b4169").ctrl(x3924).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // StreamWrite(x3907,x3920,x3922)
    val x3923_b4172_b4170 = WriteMem(b4170, Const(4096)).name("x3923_b4172_b4170").ctrl(x3924).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // StreamWrite(x3907,x3920,x3922)
    val x3925 = Counter(min=Const(0), max=Const(1024), step=Const(1), par=16).name("x3925").ctrl(x3940).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // CounterNew(Const(0),Const(1024),Const(1),Const(16))
    val x3926 = CounterChain(List(x3925)).name("x3926").ctrl(x3940).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // CounterChainNew(List(x3925))
    val x3934 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3926).name("x3934").ctrl(x3940).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // UnrolledForeach(List(b1294, b1001, b967),x3926,Block(Const(())),List(List(b1315)),List(List(b1316)))
    val b1315 = CounterIter(x3925, None).name("b1315").ctrl(x3934) // b1315
    val b1316 = Const(true).name("b1316").ctrl(x3934) // b1316
    val x3927 = OpDef(op=BitAnd, inputs=List(b1316, b1294)).name("x3927").ctrl(x3934).srcCtx("UnrollingBase.scala:28:66") // And(b1316,b1294)
    val x3928 = OpDef(op=BitAnd, inputs=List(b1001, b967)).name("x3928").ctrl(x3934).srcCtx("UnrollingBase.scala:28:66") // And(b1001,b967)
    val x3929 = OpDef(op=BitAnd, inputs=List(x3927, x3928)).name("x3929").ctrl(x3934).srcCtx("UnrollingBase.scala:28:66") // And(x3927,x3928)
    val x3930 = LoadBanks(List(x3813_d0_b0), List(b1293, b1315)).name("x3930").ctrl(x3934).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // ParSRAMLoad(x3813,List(List(b1293, b1315)),List(x3929))
    val x3932_x3931 = x3930 // x3931 = VectorApply(x3930,0)
    // x3932 = SimpleStruct(ArrayBuffer((_1,x3931), (_2,Const(true))))
    val x3933_x3933_x3908 = WriteMem(x3908, x3932_x3931).name("x3933_x3933_x3908").ctrl(x3934).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // ParStreamWrite(x3908,List(x3932),List(x3929))
    val x3935 = FringeDenseStore(dram=List(x3636), cmdStream=List(b4169, b4170), dataStream=List(x3908), ackStream=List(x3909)).name("x3935").ctrl(x3940).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // FringeDenseStore(x3636,x3907,x3908,x3909)
    val x3939 = UnitController(style=SeqPipe, level=InnerControl).name("x3939").ctrl(x3940).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // UnitPipe(List(b1294, b1001, b967),Block(Const(())))
    val x3936 = OpDef(op=BitAnd, inputs=List(b1294, b1001)).name("x3936").ctrl(x3939).srcCtx("UnrollingBase.scala:28:66") // And(b1294,b1001)
    val x3937 = OpDef(op=BitAnd, inputs=List(x3936, b967)).name("x3937").ctrl(x3939).srcCtx("UnrollingBase.scala:28:66") // And(x3936,b967)
    val x3938_x3938 = ReadMem(x3909).name("x3938_x3938").ctrl(x3939).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // StreamRead(x3909,x3937)
    val x3941 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1).name("x3941").ctrl(x4121).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // CounterNew(Const(0),Const(64),Const(1),Const(1))
    val x3942 = CounterChain(List(x3941)).name("x3942").ctrl(x4121).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // CounterChainNew(List(x3941))
    val x3976 = LoopController(style=StreamPipe, level=OuterControl, cchain=x3942).name("x3976").ctrl(x4121).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // UnrolledForeach(List(b1002, b967),x3942,Block(Const(())),List(List(b1331)),List(List(b1332)))
    val b1331 = CounterIter(x3941, Some(0)).name("b1331").ctrl(x3976) // b1331
    val b1332 = Const(true).name("b1332").ctrl(x3976) // b1332
    val b4173 = StreamOut(field="offset").name("b4173").ctrl(x3976).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // x3943 = StreamOutNew(BurstCmdBus)
    isAccum(b4173) = false
    bufferDepthOf(b4173) = 1
    val b4174 = StreamOut(field="size").name("b4174").ctrl(x3976).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // x3943 = StreamOutNew(BurstCmdBus)
    isAccum(b4174) = false
    bufferDepthOf(b4174) = 1
    val x3944 = StreamOut(field="data").name("x3944").ctrl(x3976).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // x3944 = StreamOutNew(BurstFullDataBus())
    isAccum(x3944) = false
    bufferDepthOf(x3944) = 1
    val x3945 = StreamIn(field="ack").name("x3945").ctrl(x3976).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // x3945 = StreamInNew(BurstAckBus)
    isAccum(x3945) = false
    bufferDepthOf(x3945) = 1
    def split1 = {
    val x3960 = UnitController(style=SeqPipe, level=InnerControl).name("x3960").ctrl(x3976).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // UnitPipe(List(b1332, b1002, b967),Block(x3959))
    val x3946 = OpDef(op=FixAdd, inputs=List(b966, b1331)).name("x3946").ctrl(x3960).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // FixAdd(b966,b1331)
    val x3947 = x3946 // FixConvert(x3946,TRUE,_32,_0) (Same Type. No op)
    val x3948 = ReadMem(x3627_d0).name("x3948").ctrl(x3960).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:28:30") // RegRead(x3627)
    val x3949 = OpDef(op=FixMul, inputs=List(x3947, x3948)).name("x3949").ctrl(x3960).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // FixMul(x3947,x3948)
    val x3950 = b996 // FixConvert(b996,TRUE,_32,_0) (Same Type. No op)
    val x3951 = OpDef(op=FixAdd, inputs=List(x3949, x3950)).name("x3951").ctrl(x3960).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // FixAdd(x3949,x3950)
    val x3952 = OpDef(op=FixSla, inputs=List(x3951, Const(2))).name("x3952").ctrl(x3960).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // FixLsh(x3951,Const(2))
    val x3953 = x3952 // FixConvert(x3952,TRUE,_64,_0)
    val x3954 = DramAddress(x3636).name("x3954").ctrl(x3960).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // GetDRAMAddress(x3636)
    val x3956_x3955 = OpDef(op=FixAdd, inputs=List(x3953, x3954)).name("x3956_x3955").ctrl(x3960).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // FixAdd(x3953,x3954)
    // x3956 = SimpleStruct(ArrayBuffer((offset,x3955), (size,Const(4096)), (isLoad,Const(false))))
    val x3957 = OpDef(op=BitAnd, inputs=List(b1332, b1002)).name("x3957").ctrl(x3960).srcCtx("UnrollingBase.scala:28:66") // And(b1332,b1002)
    val x3958 = OpDef(op=BitAnd, inputs=List(x3957, b967)).name("x3958").ctrl(x3960).srcCtx("UnrollingBase.scala:28:66") // And(x3957,b967)
    val x3959_b4175_b4173 = WriteMem(b4173, x3956_x3955).name("x3959_b4175_b4173").ctrl(x3960).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // StreamWrite(x3943,x3956,x3958)
    val x3959_b4176_b4174 = WriteMem(b4174, Const(4096)).name("x3959_b4176_b4174").ctrl(x3960).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // StreamWrite(x3943,x3956,x3958)
    val x3961 = Counter(min=Const(0), max=Const(1024), step=Const(1), par=16).name("x3961").ctrl(x3976).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // CounterNew(Const(0),Const(1024),Const(1),Const(16))
    val x3962 = CounterChain(List(x3961)).name("x3962").ctrl(x3976).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // CounterChainNew(List(x3961))
    val x3970 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3962).name("x3970").ctrl(x3976).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // UnrolledForeach(List(b1332, b1002, b967),x3962,Block(Const(())),List(List(b1353)),List(List(b1354)))
    val b1353 = CounterIter(x3961, None).name("b1353").ctrl(x3970) // b1353
    val b1354 = Const(true).name("b1354").ctrl(x3970) // b1354
    val x3963 = OpDef(op=BitAnd, inputs=List(b1354, b1332)).name("x3963").ctrl(x3970).srcCtx("UnrollingBase.scala:28:66") // And(b1354,b1332)
    val x3964 = OpDef(op=BitAnd, inputs=List(b1002, b967)).name("x3964").ctrl(x3970).srcCtx("UnrollingBase.scala:28:66") // And(b1002,b967)
    val x3965 = OpDef(op=BitAnd, inputs=List(x3963, x3964)).name("x3965").ctrl(x3970).srcCtx("UnrollingBase.scala:28:66") // And(x3963,x3964)
    val x3966 = LoadBanks(List(x3814_d0_b0), List(b1331, b1353)).name("x3966").ctrl(x3970).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // ParSRAMLoad(x3814,List(List(b1331, b1353)),List(x3965))
    val x3968_x3967 = x3966 // x3967 = VectorApply(x3966,0)
    // x3968 = SimpleStruct(ArrayBuffer((_1,x3967), (_2,Const(true))))
    val x3969_x3969_x3944 = WriteMem(x3944, x3968_x3967).name("x3969_x3969_x3944").ctrl(x3970).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // ParStreamWrite(x3944,List(x3968),List(x3965))
    val x3971 = FringeDenseStore(dram=List(x3636), cmdStream=List(b4173, b4174), dataStream=List(x3944), ackStream=List(x3945)).name("x3971").ctrl(x3976).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // FringeDenseStore(x3636,x3943,x3944,x3945)
    val x3975 = UnitController(style=SeqPipe, level=InnerControl).name("x3975").ctrl(x3976).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // UnitPipe(List(b1332, b1002, b967),Block(Const(())))
    val x3972 = OpDef(op=BitAnd, inputs=List(b1332, b1002)).name("x3972").ctrl(x3975).srcCtx("UnrollingBase.scala:28:66") // And(b1332,b1002)
    val x3973 = OpDef(op=BitAnd, inputs=List(x3972, b967)).name("x3973").ctrl(x3975).srcCtx("UnrollingBase.scala:28:66") // And(x3972,b967)
    val x3974_x3974 = ReadMem(x3945).name("x3974_x3974").ctrl(x3975).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // StreamRead(x3945,x3973)
    val x3977 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1).name("x3977").ctrl(x4121).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // CounterNew(Const(0),Const(64),Const(1),Const(1))
    val x3978 = CounterChain(List(x3977)).name("x3978").ctrl(x4121).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // CounterChainNew(List(x3977))
    val x4012 = LoopController(style=StreamPipe, level=OuterControl, cchain=x3978).name("x4012").ctrl(x4121).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // UnrolledForeach(List(b1003, b967),x3978,Block(Const(())),List(List(b1369)),List(List(b1370)))
    val b1369 = CounterIter(x3977, Some(0)).name("b1369").ctrl(x4012) // b1369
    val b1370 = Const(true).name("b1370").ctrl(x4012) // b1370
    val b4177 = StreamOut(field="offset").name("b4177").ctrl(x4012).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // x3979 = StreamOutNew(BurstCmdBus)
    isAccum(b4177) = false
    bufferDepthOf(b4177) = 1
    val b4178 = StreamOut(field="size").name("b4178").ctrl(x4012).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // x3979 = StreamOutNew(BurstCmdBus)
    isAccum(b4178) = false
    bufferDepthOf(b4178) = 1
    val x3980 = StreamOut(field="data").name("x3980").ctrl(x4012).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // x3980 = StreamOutNew(BurstFullDataBus())
    isAccum(x3980) = false
    bufferDepthOf(x3980) = 1
    val x3981 = StreamIn(field="ack").name("x3981").ctrl(x4012).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // x3981 = StreamInNew(BurstAckBus)
    isAccum(x3981) = false
    bufferDepthOf(x3981) = 1
    val x3996 = UnitController(style=SeqPipe, level=InnerControl).name("x3996").ctrl(x4012).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // UnitPipe(List(b1370, b1003, b967),Block(x3995))
    val x3982 = OpDef(op=FixAdd, inputs=List(b966, b1369)).name("x3982").ctrl(x3996).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // FixAdd(b966,b1369)
    val x3983 = x3982 // FixConvert(x3982,TRUE,_32,_0) (Same Type. No op)
    val x3984 = ReadMem(x3627_d0).name("x3984").ctrl(x3996).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:28:30") // RegRead(x3627)
    val x3985 = OpDef(op=FixMul, inputs=List(x3983, x3984)).name("x3985").ctrl(x3996).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // FixMul(x3983,x3984)
    val x3986 = b997 // FixConvert(b997,TRUE,_32,_0) (Same Type. No op)
    val x3987 = OpDef(op=FixAdd, inputs=List(x3985, x3986)).name("x3987").ctrl(x3996).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // FixAdd(x3985,x3986)
    val x3988 = OpDef(op=FixSla, inputs=List(x3987, Const(2))).name("x3988").ctrl(x3996).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // FixLsh(x3987,Const(2))
    val x3989 = x3988 // FixConvert(x3988,TRUE,_64,_0)
    val x3990 = DramAddress(x3636).name("x3990").ctrl(x3996).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // GetDRAMAddress(x3636)
    val x3992_x3991 = OpDef(op=FixAdd, inputs=List(x3989, x3990)).name("x3992_x3991").ctrl(x3996).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // FixAdd(x3989,x3990)
    // x3992 = SimpleStruct(ArrayBuffer((offset,x3991), (size,Const(4096)), (isLoad,Const(false))))
    val x3993 = OpDef(op=BitAnd, inputs=List(b1370, b1003)).name("x3993").ctrl(x3996).srcCtx("UnrollingBase.scala:28:66") // And(b1370,b1003)
    val x3994 = OpDef(op=BitAnd, inputs=List(x3993, b967)).name("x3994").ctrl(x3996).srcCtx("UnrollingBase.scala:28:66") // And(x3993,b967)
    val x3995_b4179_b4177 = WriteMem(b4177, x3992_x3991).name("x3995_b4179_b4177").ctrl(x3996).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // StreamWrite(x3979,x3992,x3994)
    val x3995_b4180_b4178 = WriteMem(b4178, Const(4096)).name("x3995_b4180_b4178").ctrl(x3996).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // StreamWrite(x3979,x3992,x3994)
    val x3997 = Counter(min=Const(0), max=Const(1024), step=Const(1), par=16).name("x3997").ctrl(x4012).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // CounterNew(Const(0),Const(1024),Const(1),Const(16))
    val x3998 = CounterChain(List(x3997)).name("x3998").ctrl(x4012).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // CounterChainNew(List(x3997))
    val x4006 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3998).name("x4006").ctrl(x4012).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // UnrolledForeach(List(b1370, b1003, b967),x3998,Block(Const(())),List(List(b1391)),List(List(b1392)))
    val b1391 = CounterIter(x3997, None).name("b1391").ctrl(x4006) // b1391
    val b1392 = Const(true).name("b1392").ctrl(x4006) // b1392
    val x3999 = OpDef(op=BitAnd, inputs=List(b1392, b1370)).name("x3999").ctrl(x4006).srcCtx("UnrollingBase.scala:28:66") // And(b1392,b1370)
    val x4000 = OpDef(op=BitAnd, inputs=List(b1003, b967)).name("x4000").ctrl(x4006).srcCtx("UnrollingBase.scala:28:66") // And(b1003,b967)
    val x4001 = OpDef(op=BitAnd, inputs=List(x3999, x4000)).name("x4001").ctrl(x4006).srcCtx("UnrollingBase.scala:28:66") // And(x3999,x4000)
    val x4002 = LoadBanks(List(x3815_d0_b0), List(b1369, b1391)).name("x4002").ctrl(x4006).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // ParSRAMLoad(x3815,List(List(b1369, b1391)),List(x4001))
    val x4004_x4003 = x4002 // x4003 = VectorApply(x4002,0)
    // x4004 = SimpleStruct(ArrayBuffer((_1,x4003), (_2,Const(true))))
    val x4005_x4005_x3980 = WriteMem(x3980, x4004_x4003).name("x4005_x4005_x3980").ctrl(x4006).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // ParStreamWrite(x3980,List(x4004),List(x4001))
    val x4007 = FringeDenseStore(dram=List(x3636), cmdStream=List(b4177, b4178), dataStream=List(x3980), ackStream=List(x3981)).name("x4007").ctrl(x4012).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // FringeDenseStore(x3636,x3979,x3980,x3981)
    val x4011 = UnitController(style=SeqPipe, level=InnerControl).name("x4011").ctrl(x4012).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // UnitPipe(List(b1370, b1003, b967),Block(Const(())))
    val x4008 = OpDef(op=BitAnd, inputs=List(b1370, b1003)).name("x4008").ctrl(x4011).srcCtx("UnrollingBase.scala:28:66") // And(b1370,b1003)
    val x4009 = OpDef(op=BitAnd, inputs=List(x4008, b967)).name("x4009").ctrl(x4011).srcCtx("UnrollingBase.scala:28:66") // And(x4008,b967)
    val x4010_x4010 = ReadMem(x3981).name("x4010_x4010").ctrl(x4011).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // StreamRead(x3981,x4009)
    val x4013 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1).name("x4013").ctrl(x4121).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // CounterNew(Const(0),Const(64),Const(1),Const(1))
    val x4014 = CounterChain(List(x4013)).name("x4014").ctrl(x4121).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // CounterChainNew(List(x4013))
    val x4048 = LoopController(style=StreamPipe, level=OuterControl, cchain=x4014).name("x4048").ctrl(x4121).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // UnrolledForeach(List(b1004, b967),x4014,Block(Const(())),List(List(b1407)),List(List(b1408)))
    val b1407 = CounterIter(x4013, Some(0)).name("b1407").ctrl(x4048) // b1407
    val b1408 = Const(true).name("b1408").ctrl(x4048) // b1408
    val b4181 = StreamOut(field="offset").name("b4181").ctrl(x4048).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // x4015 = StreamOutNew(BurstCmdBus)
    isAccum(b4181) = false
    bufferDepthOf(b4181) = 1
    val b4182 = StreamOut(field="size").name("b4182").ctrl(x4048).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // x4015 = StreamOutNew(BurstCmdBus)
    isAccum(b4182) = false
    bufferDepthOf(b4182) = 1
    val x4016 = StreamOut(field="data").name("x4016").ctrl(x4048).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // x4016 = StreamOutNew(BurstFullDataBus())
    isAccum(x4016) = false
    bufferDepthOf(x4016) = 1
    val x4017 = StreamIn(field="ack").name("x4017").ctrl(x4048).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // x4017 = StreamInNew(BurstAckBus)
    isAccum(x4017) = false
    bufferDepthOf(x4017) = 1
    val x4032 = UnitController(style=SeqPipe, level=InnerControl).name("x4032").ctrl(x4048).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // UnitPipe(List(b1408, b1004, b967),Block(x4031))
    val x4018 = OpDef(op=FixAdd, inputs=List(b966, b1407)).name("x4018").ctrl(x4032).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // FixAdd(b966,b1407)
    val x4019 = x4018 // FixConvert(x4018,TRUE,_32,_0) (Same Type. No op)
    val x4020 = ReadMem(x3627_d0).name("x4020").ctrl(x4032).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:28:30") // RegRead(x3627)
    val x4021 = OpDef(op=FixMul, inputs=List(x4019, x4020)).name("x4021").ctrl(x4032).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // FixMul(x4019,x4020)
    val x4022 = b998 // FixConvert(b998,TRUE,_32,_0) (Same Type. No op)
    val x4023 = OpDef(op=FixAdd, inputs=List(x4021, x4022)).name("x4023").ctrl(x4032).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // FixAdd(x4021,x4022)
    val x4024 = OpDef(op=FixSla, inputs=List(x4023, Const(2))).name("x4024").ctrl(x4032).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // FixLsh(x4023,Const(2))
    val x4025 = x4024 // FixConvert(x4024,TRUE,_64,_0)
    val x4026 = DramAddress(x3636).name("x4026").ctrl(x4032).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // GetDRAMAddress(x3636)
    val x4028_x4027 = OpDef(op=FixAdd, inputs=List(x4025, x4026)).name("x4028_x4027").ctrl(x4032).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // FixAdd(x4025,x4026)
    // x4028 = SimpleStruct(ArrayBuffer((offset,x4027), (size,Const(4096)), (isLoad,Const(false))))
    val x4029 = OpDef(op=BitAnd, inputs=List(b1408, b1004)).name("x4029").ctrl(x4032).srcCtx("UnrollingBase.scala:28:66") // And(b1408,b1004)
    val x4030 = OpDef(op=BitAnd, inputs=List(x4029, b967)).name("x4030").ctrl(x4032).srcCtx("UnrollingBase.scala:28:66") // And(x4029,b967)
    val x4031_b4183_b4181 = WriteMem(b4181, x4028_x4027).name("x4031_b4183_b4181").ctrl(x4032).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // StreamWrite(x4015,x4028,x4030)
    val x4031_b4184_b4182 = WriteMem(b4182, Const(4096)).name("x4031_b4184_b4182").ctrl(x4032).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // StreamWrite(x4015,x4028,x4030)
    val x4033 = Counter(min=Const(0), max=Const(1024), step=Const(1), par=16).name("x4033").ctrl(x4048).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // CounterNew(Const(0),Const(1024),Const(1),Const(16))
    val x4034 = CounterChain(List(x4033)).name("x4034").ctrl(x4048).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // CounterChainNew(List(x4033))
    val x4042 = LoopController(style=InnerPipe, level=InnerControl, cchain=x4034).name("x4042").ctrl(x4048).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // UnrolledForeach(List(b1408, b1004, b967),x4034,Block(Const(())),List(List(b1429)),List(List(b1430)))
    val b1429 = CounterIter(x4033, None).name("b1429").ctrl(x4042) // b1429
    val b1430 = Const(true).name("b1430").ctrl(x4042) // b1430
    val x4035 = OpDef(op=BitAnd, inputs=List(b1430, b1408)).name("x4035").ctrl(x4042).srcCtx("UnrollingBase.scala:28:66") // And(b1430,b1408)
    val x4036 = OpDef(op=BitAnd, inputs=List(b1004, b967)).name("x4036").ctrl(x4042).srcCtx("UnrollingBase.scala:28:66") // And(b1004,b967)
    val x4037 = OpDef(op=BitAnd, inputs=List(x4035, x4036)).name("x4037").ctrl(x4042).srcCtx("UnrollingBase.scala:28:66") // And(x4035,x4036)
    val x4038 = LoadBanks(List(x3816_d0_b0), List(b1407, b1429)).name("x4038").ctrl(x4042).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // ParSRAMLoad(x3816,List(List(b1407, b1429)),List(x4037))
    val x4040_x4039 = x4038 // x4039 = VectorApply(x4038,0)
    // x4040 = SimpleStruct(ArrayBuffer((_1,x4039), (_2,Const(true))))
    val x4041_x4041_x4016 = WriteMem(x4016, x4040_x4039).name("x4041_x4041_x4016").ctrl(x4042).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // ParStreamWrite(x4016,List(x4040),List(x4037))
    val x4043 = FringeDenseStore(dram=List(x3636), cmdStream=List(b4181, b4182), dataStream=List(x4016), ackStream=List(x4017)).name("x4043").ctrl(x4048).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // FringeDenseStore(x3636,x4015,x4016,x4017)
    val x4047 = UnitController(style=SeqPipe, level=InnerControl).name("x4047").ctrl(x4048).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // UnitPipe(List(b1408, b1004, b967),Block(Const(())))
    val x4044 = OpDef(op=BitAnd, inputs=List(b1408, b1004)).name("x4044").ctrl(x4047).srcCtx("UnrollingBase.scala:28:66") // And(b1408,b1004)
    val x4045 = OpDef(op=BitAnd, inputs=List(x4044, b967)).name("x4045").ctrl(x4047).srcCtx("UnrollingBase.scala:28:66") // And(x4044,b967)
    val x4046_x4046 = ReadMem(x4017).name("x4046_x4046").ctrl(x4047).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // StreamRead(x4017,x4045)
    val x4049 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1).name("x4049").ctrl(x4121).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // CounterNew(Const(0),Const(64),Const(1),Const(1))
    val x4050 = CounterChain(List(x4049)).name("x4050").ctrl(x4121).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // CounterChainNew(List(x4049))
    val x4084 = LoopController(style=StreamPipe, level=OuterControl, cchain=x4050).name("x4084").ctrl(x4121).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // UnrolledForeach(List(b1005, b967),x4050,Block(Const(())),List(List(b1445)),List(List(b1446)))
    val b1445 = CounterIter(x4049, Some(0)).name("b1445").ctrl(x4084) // b1445
    val b1446 = Const(true).name("b1446").ctrl(x4084) // b1446
    val b4185 = StreamOut(field="offset").name("b4185").ctrl(x4084).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // x4051 = StreamOutNew(BurstCmdBus)
    isAccum(b4185) = false
    bufferDepthOf(b4185) = 1
    val b4186 = StreamOut(field="size").name("b4186").ctrl(x4084).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // x4051 = StreamOutNew(BurstCmdBus)
    isAccum(b4186) = false
    bufferDepthOf(b4186) = 1
    val x4052 = StreamOut(field="data").name("x4052").ctrl(x4084).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // x4052 = StreamOutNew(BurstFullDataBus())
    isAccum(x4052) = false
    bufferDepthOf(x4052) = 1
    val x4053 = StreamIn(field="ack").name("x4053").ctrl(x4084).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // x4053 = StreamInNew(BurstAckBus)
    isAccum(x4053) = false
    bufferDepthOf(x4053) = 1
    val x4068 = UnitController(style=SeqPipe, level=InnerControl).name("x4068").ctrl(x4084).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // UnitPipe(List(b1446, b1005, b967),Block(x4067))
    val x4054 = OpDef(op=FixAdd, inputs=List(b966, b1445)).name("x4054").ctrl(x4068).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // FixAdd(b966,b1445)
    val x4055 = x4054 // FixConvert(x4054,TRUE,_32,_0) (Same Type. No op)
    val x4056 = ReadMem(x3627_d0).name("x4056").ctrl(x4068).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:28:30") // RegRead(x3627)
    val x4057 = OpDef(op=FixMul, inputs=List(x4055, x4056)).name("x4057").ctrl(x4068).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // FixMul(x4055,x4056)
    val x4058 = b999 // FixConvert(b999,TRUE,_32,_0) (Same Type. No op)
    val x4059 = OpDef(op=FixAdd, inputs=List(x4057, x4058)).name("x4059").ctrl(x4068).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // FixAdd(x4057,x4058)
    val x4060 = OpDef(op=FixSla, inputs=List(x4059, Const(2))).name("x4060").ctrl(x4068).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // FixLsh(x4059,Const(2))
    val x4061 = x4060 // FixConvert(x4060,TRUE,_64,_0)
    val x4062 = DramAddress(x3636).name("x4062").ctrl(x4068).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // GetDRAMAddress(x3636)
    val x4064_x4063 = OpDef(op=FixAdd, inputs=List(x4061, x4062)).name("x4064_x4063").ctrl(x4068).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // FixAdd(x4061,x4062)
    // x4064 = SimpleStruct(ArrayBuffer((offset,x4063), (size,Const(4096)), (isLoad,Const(false))))
    val x4065 = OpDef(op=BitAnd, inputs=List(b1446, b1005)).name("x4065").ctrl(x4068).srcCtx("UnrollingBase.scala:28:66") // And(b1446,b1005)
    val x4066 = OpDef(op=BitAnd, inputs=List(x4065, b967)).name("x4066").ctrl(x4068).srcCtx("UnrollingBase.scala:28:66") // And(x4065,b967)
    val x4067_b4187_b4185 = WriteMem(b4185, x4064_x4063).name("x4067_b4187_b4185").ctrl(x4068).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // StreamWrite(x4051,x4064,x4066)
    val x4067_b4188_b4186 = WriteMem(b4186, Const(4096)).name("x4067_b4188_b4186").ctrl(x4068).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // StreamWrite(x4051,x4064,x4066)
    val x4069 = Counter(min=Const(0), max=Const(1024), step=Const(1), par=16).name("x4069").ctrl(x4084).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // CounterNew(Const(0),Const(1024),Const(1),Const(16))
    val x4070 = CounterChain(List(x4069)).name("x4070").ctrl(x4084).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // CounterChainNew(List(x4069))
    val x4078 = LoopController(style=InnerPipe, level=InnerControl, cchain=x4070).name("x4078").ctrl(x4084).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // UnrolledForeach(List(b1446, b1005, b967),x4070,Block(Const(())),List(List(b1467)),List(List(b1468)))
    val b1467 = CounterIter(x4069, None).name("b1467").ctrl(x4078) // b1467
    val b1468 = Const(true).name("b1468").ctrl(x4078) // b1468
    val x4071 = OpDef(op=BitAnd, inputs=List(b1468, b1446)).name("x4071").ctrl(x4078).srcCtx("UnrollingBase.scala:28:66") // And(b1468,b1446)
    val x4072 = OpDef(op=BitAnd, inputs=List(b1005, b967)).name("x4072").ctrl(x4078).srcCtx("UnrollingBase.scala:28:66") // And(b1005,b967)
    val x4073 = OpDef(op=BitAnd, inputs=List(x4071, x4072)).name("x4073").ctrl(x4078).srcCtx("UnrollingBase.scala:28:66") // And(x4071,x4072)
    val x4074 = LoadBanks(List(x3817_d0_b0), List(b1445, b1467)).name("x4074").ctrl(x4078).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // ParSRAMLoad(x3817,List(List(b1445, b1467)),List(x4073))
    val x4076_x4075 = x4074 // x4075 = VectorApply(x4074,0)
    // x4076 = SimpleStruct(ArrayBuffer((_1,x4075), (_2,Const(true))))
    val x4077_x4077_x4052 = WriteMem(x4052, x4076_x4075).name("x4077_x4077_x4052").ctrl(x4078).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // ParStreamWrite(x4052,List(x4076),List(x4073))
    val x4079 = FringeDenseStore(dram=List(x3636), cmdStream=List(b4185, b4186), dataStream=List(x4052), ackStream=List(x4053)).name("x4079").ctrl(x4084).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // FringeDenseStore(x3636,x4051,x4052,x4053)
    val x4083 = UnitController(style=SeqPipe, level=InnerControl).name("x4083").ctrl(x4084).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // UnitPipe(List(b1446, b1005, b967),Block(Const(())))
    val x4080 = OpDef(op=BitAnd, inputs=List(b1446, b1005)).name("x4080").ctrl(x4083).srcCtx("UnrollingBase.scala:28:66") // And(b1446,b1005)
    val x4081 = OpDef(op=BitAnd, inputs=List(x4080, b967)).name("x4081").ctrl(x4083).srcCtx("UnrollingBase.scala:28:66") // And(x4080,b967)
    val x4082_x4082 = ReadMem(x4053).name("x4082_x4082").ctrl(x4083).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // StreamRead(x4053,x4081)
    val x4085 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1).name("x4085").ctrl(x4121).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // CounterNew(Const(0),Const(64),Const(1),Const(1))
    val x4086 = CounterChain(List(x4085)).name("x4086").ctrl(x4121).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // CounterChainNew(List(x4085))
    val x4120 = LoopController(style=StreamPipe, level=OuterControl, cchain=x4086).name("x4120").ctrl(x4121).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // UnrolledForeach(List(b1006, b967),x4086,Block(Const(())),List(List(b1483)),List(List(b1484)))
    val b1483 = CounterIter(x4085, Some(0)).name("b1483").ctrl(x4120) // b1483
    val b1484 = Const(true).name("b1484").ctrl(x4120) // b1484
    val b4189 = StreamOut(field="offset").name("b4189").ctrl(x4120).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // x4087 = StreamOutNew(BurstCmdBus)
    isAccum(b4189) = false
    bufferDepthOf(b4189) = 1
    val b4190 = StreamOut(field="size").name("b4190").ctrl(x4120).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // x4087 = StreamOutNew(BurstCmdBus)
    isAccum(b4190) = false
    bufferDepthOf(b4190) = 1
    val x4088 = StreamOut(field="data").name("x4088").ctrl(x4120).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // x4088 = StreamOutNew(BurstFullDataBus())
    isAccum(x4088) = false
    bufferDepthOf(x4088) = 1
    val x4089 = StreamIn(field="ack").name("x4089").ctrl(x4120).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // x4089 = StreamInNew(BurstAckBus)
    isAccum(x4089) = false
    bufferDepthOf(x4089) = 1
    val x4104 = UnitController(style=SeqPipe, level=InnerControl).name("x4104").ctrl(x4120).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // UnitPipe(List(b1484, b1006, b967),Block(x4103))
    val x4090 = OpDef(op=FixAdd, inputs=List(b966, b1483)).name("x4090").ctrl(x4104).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // FixAdd(b966,b1483)
    val x4091 = x4090 // FixConvert(x4090,TRUE,_32,_0) (Same Type. No op)
    val x4092 = ReadMem(x3627_d0).name("x4092").ctrl(x4104).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:28:30") // RegRead(x3627)
    val x4093 = OpDef(op=FixMul, inputs=List(x4091, x4092)).name("x4093").ctrl(x4104).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // FixMul(x4091,x4092)
    val x4094 = b1000 // FixConvert(b1000,TRUE,_32,_0) (Same Type. No op)
    val x4095 = OpDef(op=FixAdd, inputs=List(x4093, x4094)).name("x4095").ctrl(x4104).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // FixAdd(x4093,x4094)
    val x4096 = OpDef(op=FixSla, inputs=List(x4095, Const(2))).name("x4096").ctrl(x4104).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // FixLsh(x4095,Const(2))
    val x4097 = x4096 // FixConvert(x4096,TRUE,_64,_0)
    val x4098 = DramAddress(x3636).name("x4098").ctrl(x4104).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // GetDRAMAddress(x3636)
    val x4100_x4099 = OpDef(op=FixAdd, inputs=List(x4097, x4098)).name("x4100_x4099").ctrl(x4104).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // FixAdd(x4097,x4098)
    // x4100 = SimpleStruct(ArrayBuffer((offset,x4099), (size,Const(4096)), (isLoad,Const(false))))
    val x4101 = OpDef(op=BitAnd, inputs=List(b1484, b1006)).name("x4101").ctrl(x4104).srcCtx("UnrollingBase.scala:28:66") // And(b1484,b1006)
    val x4102 = OpDef(op=BitAnd, inputs=List(x4101, b967)).name("x4102").ctrl(x4104).srcCtx("UnrollingBase.scala:28:66") // And(x4101,b967)
    val x4103_b4191_b4189 = WriteMem(b4189, x4100_x4099).name("x4103_b4191_b4189").ctrl(x4104).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // StreamWrite(x4087,x4100,x4102)
    val x4103_b4192_b4190 = WriteMem(b4190, Const(4096)).name("x4103_b4192_b4190").ctrl(x4104).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // StreamWrite(x4087,x4100,x4102)
    val x4105 = Counter(min=Const(0), max=Const(1024), step=Const(1), par=16).name("x4105").ctrl(x4120).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // CounterNew(Const(0),Const(1024),Const(1),Const(16))
    val x4106 = CounterChain(List(x4105)).name("x4106").ctrl(x4120).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // CounterChainNew(List(x4105))
    val x4114 = LoopController(style=InnerPipe, level=InnerControl, cchain=x4106).name("x4114").ctrl(x4120).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // UnrolledForeach(List(b1484, b1006, b967),x4106,Block(Const(())),List(List(b1505)),List(List(b1506)))
    val b1505 = CounterIter(x4105, None).name("b1505").ctrl(x4114) // b1505
    val b1506 = Const(true).name("b1506").ctrl(x4114) // b1506
    val x4107 = OpDef(op=BitAnd, inputs=List(b1506, b1484)).name("x4107").ctrl(x4114).srcCtx("UnrollingBase.scala:28:66") // And(b1506,b1484)
    val x4108 = OpDef(op=BitAnd, inputs=List(b1006, b967)).name("x4108").ctrl(x4114).srcCtx("UnrollingBase.scala:28:66") // And(b1006,b967)
    val x4109 = OpDef(op=BitAnd, inputs=List(x4107, x4108)).name("x4109").ctrl(x4114).srcCtx("UnrollingBase.scala:28:66") // And(x4107,x4108)
    val x4110 = LoadBanks(List(x3818_d0_b0), List(b1483, b1505)).name("x4110").ctrl(x4114).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // ParSRAMLoad(x3818,List(List(b1483, b1505)),List(x4109))
    val x4112_x4111 = x4110 // x4111 = VectorApply(x4110,0)
    // x4112 = SimpleStruct(ArrayBuffer((_1,x4111), (_2,Const(true))))
    val x4113_x4113_x4088 = WriteMem(x4088, x4112_x4111).name("x4113_x4113_x4088").ctrl(x4114).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // ParStreamWrite(x4088,List(x4112),List(x4109))
    val x4115 = FringeDenseStore(dram=List(x3636), cmdStream=List(b4189, b4190), dataStream=List(x4088), ackStream=List(x4089)).name("x4115").ctrl(x4120).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // FringeDenseStore(x3636,x4087,x4088,x4089)
    val x4119 = UnitController(style=SeqPipe, level=InnerControl).name("x4119").ctrl(x4120).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // UnitPipe(List(b1484, b1006, b967),Block(Const(())))
    val x4116 = OpDef(op=BitAnd, inputs=List(b1484, b1006)).name("x4116").ctrl(x4119).srcCtx("UnrollingBase.scala:28:66") // And(b1484,b1006)
    val x4117 = OpDef(op=BitAnd, inputs=List(x4116, b967)).name("x4117").ctrl(x4119).srcCtx("UnrollingBase.scala:28:66") // And(x4116,b967)
    val x4118_x4118 = ReadMem(x4089).name("x4118_x4118").ctrl(x4119).srcCtx("OuterProduct__op1_1_op2_6_ip2_16_ip1_1_ts1_64_ts2_1024.scala:42:43") // StreamRead(x4089,x4117)
    }; split1
    
  }
}
