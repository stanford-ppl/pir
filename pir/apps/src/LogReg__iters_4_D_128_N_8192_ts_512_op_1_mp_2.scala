import pir._
import pir.node._
import arch._
import prism.enums._

object LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2 extends PIRApp {
  def main(implicit design:PIRDesign) = {
    import design.pirmeta._
    val x3854 = withCtrl(design.top.topController) { ArgIn(init=0).name("x3854").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:25:22:iters") } // ArgInNew(Const(0))
    isAccum(x3854) = false
    bufferDepthOf(x3854) = 1
    boundOf(x3854) = 4
    val x3855 = withCtrl(design.top.topController) { ArgIn(init=0).name("x3855").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:26:22:N") } // ArgInNew(Const(0))
    isAccum(x3855) = false
    bufferDepthOf(x3855) = 1
    boundOf(x3855) = 8192
    val x3858 = withCtrl(design.top.topController) { ReadMem(x3855).name("x3858").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:30:21") } // RegRead(x3855)
    val x3859 = withCtrl(design.top.topController) { DRAM(dims=List(x3858, Const(128))).name("x3859").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:30:20:x") } // x3859 = DRAMNew(ArrayBuffer(x3858, Const(128)),Const(0.0))
    val x3860 = withCtrl(design.top.topController) { ReadMem(x3855).name("x3860").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:31:21") } // RegRead(x3855)
    val x3861 = withCtrl(design.top.topController) { DRAM(dims=List(x3860)).name("x3861").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:31:20:y") } // x3861 = DRAMNew(ArrayBuffer(x3860),Const(0.0))
    val x3862 = withCtrl(design.top.topController) { DRAM(dims=List(Const(128))).name("x3862").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:32:24:theta") } // x3862 = DRAMNew(ArrayBuffer(Const(128)),Const(0.0))
    val x4113 = withCtrl(design.top.topController) { UnitController(style=SeqPipe, level=OuterControl).name("x4113").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:38:11") } // Hwblock(Block(Const(())),false)
    val x3868_d0_b0 = withCtrl(x4113) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x3868_d0_b0").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:39:27:btheta") } // x3868 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x3868_d0_b0) = false
    bufferDepthOf(x3868_d0_b0) = 1
    staticDimsOf(x3868_d0_b0) = List(128)
    val x3868_d1_b0 = withCtrl(x4113) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x3868_d1_b0").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:39:27:btheta") } // x3868 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x3868_d1_b0) = true
    bufferDepthOf(x3868_d1_b0) = 1
    staticDimsOf(x3868_d1_b0) = List(128)
    val x3868_d2_b0 = withCtrl(x4113) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x3868_d2_b0").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:39:27:btheta") } // x3868 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x3868_d2_b0) = false
    bufferDepthOf(x3868_d2_b0) = 1
    staticDimsOf(x3868_d2_b0) = List(128)
    val x3868_d3_b0 = withCtrl(x4113) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x3868_d3_b0").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:39:27:btheta") } // x3868 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x3868_d3_b0) = false
    bufferDepthOf(x3868_d3_b0) = 1
    staticDimsOf(x3868_d3_b0) = List(128)
    val x3886 = withCtrl(x4113) { UnitController(style=StreamPipe, level=OuterControl).name("x3886").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:41:14") } // UnitPipe(List(Const(true)),Block(Const(())))
    val b4183 = withCtrl(x3886) { StreamOut(field="offset").name("b4183").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:41:14") } // x3869 = StreamOutNew(BurstCmdBus)
    isAccum(b4183) = false
    bufferDepthOf(b4183) = 1
    val b4184 = withCtrl(x3886) { StreamOut(field="size").name("b4184").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:41:14") } // x3869 = StreamOutNew(BurstCmdBus)
    isAccum(b4184) = false
    bufferDepthOf(b4184) = 1
    val x3870 = withCtrl(x3886) { StreamIn(field="data").name("x3870").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:41:14") } // x3870 = StreamInNew(BurstDataBus())
    isAccum(x3870) = false
    bufferDepthOf(x3870) = 1
    val x3878 = withCtrl(x3886) { UnitController(style=SeqPipe, level=InnerControl).name("x3878").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:41:14") } // UnitPipe(List(Const(true)),Block(x3877))
    val x3871 = withCtrl(x3878) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x3872 = withCtrl(x3878) { OpDef(op=FixSla, inputs=List(x3871, Const(2))).name("x3872").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:41:14") } // FixLsh(x3871,Const(2))
    val x3873 = withCtrl(x3878) { x3872 } // FixConvert(x3872,TRUE,_64,_0)
    val x3874 = withCtrl(x3878) { DramAddress(x3862).name("x3874").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:41:14") } // GetDRAMAddress(x3862)
    val x3876_x3875 = withCtrl(x3878) { OpDef(op=FixAdd, inputs=List(x3873, x3874)).name("x3876_x3875").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:41:14") } // FixAdd(x3873,x3874)
    // x3876 = SimpleStruct(ArrayBuffer((offset,x3875), (size,Const(512)), (isLoad,Const(true))))
    val x3877_b4185_b4183 = withCtrl(x3878) { WriteMem(b4183, x3876_x3875).name("x3877_b4185_b4183").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:41:14") } // StreamWrite(x3869,x3876,Const(true))
    val x3877_b4186_b4184 = withCtrl(x3878) { WriteMem(b4184, Const(512)).name("x3877_b4186_b4184").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:41:14") } // StreamWrite(x3869,x3876,Const(true))
    val x3879 = withCtrl(x3886) { FringeDenseLoad(dram=List(x3862), cmdStream=List(b4183, b4184), dataStream=List(x3870)).name("x3879").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:41:14") } // FringeDenseLoad(x3862,x3869,x3870)
    val x3880 = withCtrl(x3886) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x3880").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:41:14") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x3881 = withCtrl(x3886) { CounterChain(List(x3880)).name("x3881").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:41:14") } // CounterChainNew(List(x3880))
    val x3885 = withCtrl(x3886) { LoopController(style=InnerPipe, level=InnerControl, cchain=x3881).name("x3885").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:41:14") } // UnrolledForeach(List(Const(true)),x3881,Block(Const(())),List(List(b2180)),List(List(b2181)))
    val b2180 = withCtrl(x3885) { CounterIter(x3880, None).name("b2180") } // b2180
    val b2181 = withCtrl(x3885) { Const(true).name("b2181") } // b2181
    val x3882_x3882 = withCtrl(x3885) { ReadMem(x3870).name("x3882_x3882").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:41:14") } // ParStreamRead(x3870,List(b2181))
    val x3883_x3883 = withCtrl(x3885) { x3882_x3882 } // VectorApply(x3882,0)
    val x3884 = withCtrl(x3885) { StoreBanks(List(List(x3868_d0_b0), List(x3868_d1_b0), List(x3868_d2_b0), List(x3868_d3_b0)), List(b2180), x3883_x3883).name("x3884").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:41:14") } // ParSRAMStore(x3868,List(List(b2180)),List(x3883),List(b2181))
    val x3887 = withCtrl(x4113) { ReadMem(x3854).name("x3887").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:43:26") } // RegRead(x3854)
    val x3888 = withCtrl(x4113) { Counter(min=Const(0), max=x3887, step=Const(1), par=1).name("x3888").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:43:32") } // CounterNew(Const(0),x3887,Const(1),Const(1))
    val x3889 = withCtrl(x4113) { CounterChain(List(x3888)).name("x3889").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:43:38") } // CounterChainNew(List(x3888))
    val x4090 = withCtrl(x4113) { LoopController(style=SeqPipe, level=OuterControl, cchain=x3889).name("x4090").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:43:38") } // UnrolledForeach(List(Const(true)),x3889,Block(Const(())),List(List(b2190)),List(List(b2191)))
    val b2190 = withCtrl(x4090) { CounterIter(x3888, Some(0)).name("b2190") } // b2190
    val b2191 = withCtrl(x4090) { Const(true).name("b2191") } // b2191
    val x3890_d0_b0 = withCtrl(x4090) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x3890_d0_b0").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:45:37:grad") } // x3890 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x3890_d0_b0) = false
    bufferDepthOf(x3890_d0_b0) = 1
    staticDimsOf(x3890_d0_b0) = List(128)
    val x3890_d1_b0 = withCtrl(x4090) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x3890_d1_b0").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:45:37:grad") } // x3890 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x3890_d1_b0) = true
    bufferDepthOf(x3890_d1_b0) = 1
    staticDimsOf(x3890_d1_b0) = List(128)
    val x3891 = withCtrl(x4090) { ReadMem(x3855).name("x3891").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:45:49") } // RegRead(x3855)
    val x3892 = withCtrl(x4090) { Counter(min=Const(0), max=x3891, step=Const(512), par=1).name("x3892").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:45:57") } // CounterNew(Const(0),x3891,Const(512),Const(1))
    val x3893 = withCtrl(x4090) { CounterChain(List(x3892)).name("x3893").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:62:11") } // CounterChainNew(List(x3892))
    val x4078 = withCtrl(x4090) { LoopController(style=MetaPipe, level=OuterControl, cchain=x3893).name("x4078").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:62:11") } // UnrolledReduce(List(b2191),x3893,x3890,Block((x3890) => Const(())),List(List(b2198)),List(List(b2199)))
    val b2198 = withCtrl(x4078) { CounterIter(x3892, Some(0)).name("b2198") } // b2198
    val b2199 = withCtrl(x4078) { Const(true).name("b2199") } // b2199
    val x3894_d0_b0 = withCtrl(x4078) { SRAM(size=32768, banking=Strided(banks=16, stride=1)).name("x3894_d0_b0").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:46:30:xTile") } // x3894 = SRAMNew(ArrayBuffer(Const(512), Const(128)))
    isAccum(x3894_d0_b0) = false
    bufferDepthOf(x3894_d0_b0) = 2
    staticDimsOf(x3894_d0_b0) = List(512, 128)
    val x3894_d0_b1 = withCtrl(x4078) { SRAM(size=32768, banking=Strided(banks=16, stride=1)).name("x3894_d0_b1").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:46:30:xTile") } // x3894 = SRAMNew(ArrayBuffer(Const(512), Const(128)))
    isAccum(x3894_d0_b1) = false
    bufferDepthOf(x3894_d0_b1) = 2
    staticDimsOf(x3894_d0_b1) = List(512, 128)
    val x3894_d1_b0 = withCtrl(x4078) { SRAM(size=32768, banking=Strided(banks=16, stride=1)).name("x3894_d1_b0").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:46:30:xTile") } // x3894 = SRAMNew(ArrayBuffer(Const(512), Const(128)))
    isAccum(x3894_d1_b0) = false
    bufferDepthOf(x3894_d1_b0) = 2
    staticDimsOf(x3894_d1_b0) = List(512, 128)
    val x3894_d1_b1 = withCtrl(x4078) { SRAM(size=32768, banking=Strided(banks=16, stride=1)).name("x3894_d1_b1").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:46:30:xTile") } // x3894 = SRAMNew(ArrayBuffer(Const(512), Const(128)))
    isAccum(x3894_d1_b1) = false
    bufferDepthOf(x3894_d1_b1) = 2
    staticDimsOf(x3894_d1_b1) = List(512, 128)
    val x3895_d0_b0 = withCtrl(x4078) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x3895_d0_b0").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:47:30:yTile") } // x3895 = SRAMNew(ArrayBuffer(Const(512)))
    isAccum(x3895_d0_b0) = false
    bufferDepthOf(x3895_d0_b0) = 2
    staticDimsOf(x3895_d0_b0) = List(512)
    val x3948 = withCtrl(x4078) { UnitController(style=ForkJoin, level=OuterControl).name("x3948").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:48:20") } // ParallelPipe(List(b2199, b2191),Block(Const(())))
    val x3897 = withCtrl(x3948) { UnitController(style=SeqPipe, level=InnerControl).name("x3897").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:48:20") } // UnitPipe(List(b2199, b2191),Block(Const(())))
    val x3896 = withCtrl(x3897) { OpDef(op=FixAdd, inputs=List(b2198, Const(512))).name("x3896").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:49:30") } // FixAdd(b2198,Const(512))
    val x3898 = withCtrl(x3948) { Counter(min=Const(0), max=Const(512), step=Const(1), par=1).name("x3898").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:49:19") } // CounterNew(Const(0),Const(512),Const(1),Const(1))
    val x3899 = withCtrl(x3948) { CounterChain(List(x3898)).name("x3899").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:49:19") } // CounterChainNew(List(x3898))
    val x3926 = withCtrl(x3948) { LoopController(style=StreamPipe, level=OuterControl, cchain=x3899).name("x3926").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:49:19") } // UnrolledForeach(List(b2199, b2191),x3899,Block(Const(())),List(List(b2206)),List(List(b2207)))
    val b2206 = withCtrl(x3926) { CounterIter(x3898, Some(0)).name("b2206") } // b2206
    val b2207 = withCtrl(x3926) { Const(true).name("b2207") } // b2207
    val b4187 = withCtrl(x3926) { StreamOut(field="offset").name("b4187").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:49:19") } // x3900 = StreamOutNew(BurstCmdBus)
    isAccum(b4187) = false
    bufferDepthOf(b4187) = 1
    val b4188 = withCtrl(x3926) { StreamOut(field="size").name("b4188").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:49:19") } // x3900 = StreamOutNew(BurstCmdBus)
    isAccum(b4188) = false
    bufferDepthOf(b4188) = 1
    val x3901 = withCtrl(x3926) { StreamIn(field="data").name("x3901").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:49:19") } // x3901 = StreamInNew(BurstDataBus())
    isAccum(x3901) = false
    bufferDepthOf(x3901) = 1
    val x3915 = withCtrl(x3926) { UnitController(style=SeqPipe, level=InnerControl).name("x3915").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:49:19") } // UnitPipe(List(b2207, b2199, b2191),Block(x3914))
    val x3902 = withCtrl(x3915) { OpDef(op=FixAdd, inputs=List(b2198, b2206)).name("x3902").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:49:19") } // FixAdd(b2198,b2206)
    val x3903 = withCtrl(x3915) { x3902 } // FixConvert(x3902,TRUE,_32,_0) (Same Type. No op)
    val x3904 = withCtrl(x3915) { OpDef(op=FixSla, inputs=List(x3903, Const(7))).name("x3904").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:49:19") } // FixLsh(x3903,Const(7))
    val x3905 = withCtrl(x3915) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x3906 = withCtrl(x3915) { OpDef(op=FixAdd, inputs=List(x3904, x3905)).name("x3906").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:49:19") } // FixAdd(x3904,x3905)
    val x3907 = withCtrl(x3915) { OpDef(op=FixSla, inputs=List(x3906, Const(2))).name("x3907").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:49:19") } // FixLsh(x3906,Const(2))
    val x3908 = withCtrl(x3915) { x3907 } // FixConvert(x3907,TRUE,_64,_0)
    val x3909 = withCtrl(x3915) { DramAddress(x3859).name("x3909").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:49:19") } // GetDRAMAddress(x3859)
    val x3911_x3910 = withCtrl(x3915) { OpDef(op=FixAdd, inputs=List(x3908, x3909)).name("x3911_x3910").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:49:19") } // FixAdd(x3908,x3909)
    // x3911 = SimpleStruct(ArrayBuffer((offset,x3910), (size,Const(512)), (isLoad,Const(true))))
    val x3912 = withCtrl(x3915) { OpDef(op=BitAnd, inputs=List(b2207, b2199)).name("x3912").srcCtx("UnrollingBase.scala:28:66") } // And(b2207,b2199)
    val x3913 = withCtrl(x3915) { OpDef(op=BitAnd, inputs=List(x3912, b2191)).name("x3913").srcCtx("UnrollingBase.scala:28:66") } // And(x3912,b2191)
    val x3914_b4189_b4187 = withCtrl(x3915) { WriteMem(b4187, x3911_x3910).name("x3914_b4189_b4187").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:49:19") } // StreamWrite(x3900,x3911,x3913)
    val x3914_b4190_b4188 = withCtrl(x3915) { WriteMem(b4188, Const(512)).name("x3914_b4190_b4188").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:49:19") } // StreamWrite(x3900,x3911,x3913)
    val x3916 = withCtrl(x3926) { FringeDenseLoad(dram=List(x3859), cmdStream=List(b4187, b4188), dataStream=List(x3901)).name("x3916").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:49:19") } // FringeDenseLoad(x3859,x3900,x3901)
    val x3917 = withCtrl(x3926) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x3917").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:49:19") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x3918 = withCtrl(x3926) { CounterChain(List(x3917)).name("x3918").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:49:19") } // CounterChainNew(List(x3917))
    val x3925 = withCtrl(x3926) { LoopController(style=InnerPipe, level=InnerControl, cchain=x3918).name("x3925").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:49:19") } // UnrolledForeach(List(b2207, b2199, b2191),x3918,Block(Const(())),List(List(b2227)),List(List(b2228)))
    val b2227 = withCtrl(x3925) { CounterIter(x3917, None).name("b2227") } // b2227
    val b2228 = withCtrl(x3925) { Const(true).name("b2228") } // b2228
    val x3919 = withCtrl(x3925) { OpDef(op=BitAnd, inputs=List(b2228, b2207)).name("x3919").srcCtx("UnrollingBase.scala:28:66") } // And(b2228,b2207)
    val x3920 = withCtrl(x3925) { OpDef(op=BitAnd, inputs=List(b2199, b2191)).name("x3920").srcCtx("UnrollingBase.scala:28:66") } // And(b2199,b2191)
    val x3921 = withCtrl(x3925) { OpDef(op=BitAnd, inputs=List(x3919, x3920)).name("x3921").srcCtx("UnrollingBase.scala:28:66") } // And(x3919,x3920)
    val x3922_x3922 = withCtrl(x3925) { ReadMem(x3901).name("x3922_x3922").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:49:19") } // ParStreamRead(x3901,List(x3921))
    val x3923_x3923 = withCtrl(x3925) { x3922_x3922 } // VectorApply(x3922,0)
    val x3924 = withCtrl(x3925) { StoreBanks(List(List(x3894_d0_b0, x3894_d0_b1), List(x3894_d1_b0, x3894_d1_b1)), List(b2206, b2227), x3923_x3923).name("x3924").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:49:19") } // ParSRAMStore(x3894,List(List(b2206, b2227)),List(x3923),List(x3921))
    val x3947 = withCtrl(x3948) { UnitController(style=StreamPipe, level=OuterControl).name("x3947").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:50:19") } // UnitPipe(List(b2199, b2191),Block(Const(())))
    val b4191 = withCtrl(x3947) { StreamOut(field="offset").name("b4191").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:50:19") } // x3927 = StreamOutNew(BurstCmdBus)
    isAccum(b4191) = false
    bufferDepthOf(b4191) = 1
    val b4192 = withCtrl(x3947) { StreamOut(field="size").name("b4192").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:50:19") } // x3927 = StreamOutNew(BurstCmdBus)
    isAccum(b4192) = false
    bufferDepthOf(b4192) = 1
    val x3928 = withCtrl(x3947) { StreamIn(field="data").name("x3928").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:50:19") } // x3928 = StreamInNew(BurstDataBus())
    isAccum(x3928) = false
    bufferDepthOf(x3928) = 1
    val x3937 = withCtrl(x3947) { UnitController(style=SeqPipe, level=InnerControl).name("x3937").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:50:19") } // UnitPipe(List(b2199, b2191),Block(x3936))
    val x3929 = withCtrl(x3937) { b2198 } // FixConvert(b2198,TRUE,_32,_0) (Same Type. No op)
    val x3930 = withCtrl(x3937) { OpDef(op=FixSla, inputs=List(x3929, Const(2))).name("x3930").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:50:19") } // FixLsh(x3929,Const(2))
    val x3931 = withCtrl(x3937) { x3930 } // FixConvert(x3930,TRUE,_64,_0)
    val x3932 = withCtrl(x3937) { DramAddress(x3861).name("x3932").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:50:19") } // GetDRAMAddress(x3861)
    val x3934_x3933 = withCtrl(x3937) { OpDef(op=FixAdd, inputs=List(x3931, x3932)).name("x3934_x3933").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:50:19") } // FixAdd(x3931,x3932)
    // x3934 = SimpleStruct(ArrayBuffer((offset,x3933), (size,Const(2048)), (isLoad,Const(true))))
    val x3935 = withCtrl(x3937) { OpDef(op=BitAnd, inputs=List(b2199, b2191)).name("x3935").srcCtx("UnrollingBase.scala:28:66") } // And(b2199,b2191)
    val x3936_b4193_b4191 = withCtrl(x3937) { WriteMem(b4191, x3934_x3933).name("x3936_b4193_b4191").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:50:19") } // StreamWrite(x3927,x3934,x3935)
    val x3936_b4194_b4192 = withCtrl(x3937) { WriteMem(b4192, Const(2048)).name("x3936_b4194_b4192").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:50:19") } // StreamWrite(x3927,x3934,x3935)
    val x3938 = withCtrl(x3947) { FringeDenseLoad(dram=List(x3861), cmdStream=List(b4191, b4192), dataStream=List(x3928)).name("x3938").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:50:19") } // FringeDenseLoad(x3861,x3927,x3928)
    val x3939 = withCtrl(x3947) { Counter(min=Const(0), max=Const(512), step=Const(1), par=16).name("x3939").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:50:19") } // CounterNew(Const(0),Const(512),Const(1),Const(16))
    val x3940 = withCtrl(x3947) { CounterChain(List(x3939)).name("x3940").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:50:19") } // CounterChainNew(List(x3939))
    val x3946 = withCtrl(x3947) { LoopController(style=InnerPipe, level=InnerControl, cchain=x3940).name("x3946").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:50:19") } // UnrolledForeach(List(b2199, b2191),x3940,Block(Const(())),List(List(b2251)),List(List(b2252)))
    val b2251 = withCtrl(x3946) { CounterIter(x3939, None).name("b2251") } // b2251
    val b2252 = withCtrl(x3946) { Const(true).name("b2252") } // b2252
    val x3941 = withCtrl(x3946) { OpDef(op=BitAnd, inputs=List(b2252, b2199)).name("x3941").srcCtx("UnrollingBase.scala:28:66") } // And(b2252,b2199)
    val x3942 = withCtrl(x3946) { OpDef(op=BitAnd, inputs=List(x3941, b2191)).name("x3942").srcCtx("UnrollingBase.scala:28:66") } // And(x3941,b2191)
    val x3943_x3943 = withCtrl(x3946) { ReadMem(x3928).name("x3943_x3943").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:50:19") } // ParStreamRead(x3928,List(x3942))
    val x3944_x3944 = withCtrl(x3946) { x3943_x3943 } // VectorApply(x3943,0)
    val x3945 = withCtrl(x3946) { StoreBanks(List(List(x3895_d0_b0)), List(b2251), x3944_x3944).name("x3945").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:50:19") } // ParSRAMStore(x3895,List(List(b2251)),List(x3944),List(x3942))
    val x3949_d0_b0 = withCtrl(x4078) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x3949_d0_b0").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:52:28") } // x3949 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x3949_d0_b0) = false
    bufferDepthOf(x3949_d0_b0) = 2
    staticDimsOf(x3949_d0_b0) = List(128)
    val x3949_d1_b0 = withCtrl(x4078) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x3949_d1_b0").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:52:28") } // x3949 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x3949_d1_b0) = true
    bufferDepthOf(x3949_d1_b0) = 1
    staticDimsOf(x3949_d1_b0) = List(128)
    val x3950 = withCtrl(x4078) { Counter(min=Const(0), max=Const(512), step=Const(1), par=2).name("x3950").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:52:48") } // CounterNew(Const(0),Const(512),Const(1),Const(2))
    val x3951 = withCtrl(x4078) { CounterChain(List(x3950)).name("x3951").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:61:13") } // CounterChainNew(List(x3950))
    val x4063 = withCtrl(x4078) { LoopController(style=MetaPipe, level=OuterControl, cchain=x3951).name("x4063").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:61:13") } // UnrolledReduce(List(b2199, b2191),x3951,x3949,Block((x3949) => Const(())),List(List(b2266, b2267)),List(List(b2268, b2269)))
    val b2266 = withCtrl(x4063) { CounterIter(x3950, Some(0)).name("b2266") } // b2266
    val b2268 = withCtrl(x4063) { Const(true).name("b2268") } // b2268
    val b2267 = withCtrl(x4063) { CounterIter(x3950, Some(1)).name("b2267") } // b2267
    val b2269 = withCtrl(x4063) { Const(true).name("b2269") } // b2269
    val x3952_d0 = withCtrl(x4063) { Reg(init=Some(0.0)).name("x3952_d0").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:53:33:dot") } // x3952 = RegNew(Const(0.0))
    isAccum(x3952_d0) = false
    bufferDepthOf(x3952_d0) = 2
    val x3952_d1 = withCtrl(x4063) { Reg(init=Some(0.0)).name("x3952_d1").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:53:33:dot") } // x3952 = RegNew(Const(0.0))
    isAccum(x3952_d1) = true
    bufferDepthOf(x3952_d1) = 1
    val x3953_d0 = withCtrl(x4063) { Reg(init=Some(0.0)).name("x3953_d0").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:53:33:dot") } // x3953 = RegNew(Const(0.0))
    isAccum(x3953_d0) = false
    bufferDepthOf(x3953_d0) = 2
    val x3953_d1 = withCtrl(x4063) { Reg(init=Some(0.0)).name("x3953_d1").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:53:33:dot") } // x3953 = RegNew(Const(0.0))
    isAccum(x3953_d1) = true
    bufferDepthOf(x3953_d1) = 1
    val x3988 = withCtrl(x4063) { UnitController(style=ForkJoin, level=OuterControl).name("x3988").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b2199, b2191),Block(Const(())))
    val x3954 = withCtrl(x3988) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x3954").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:53:45") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x3955 = withCtrl(x3988) { CounterChain(List(x3954)).name("x3955").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:53:86") } // CounterChainNew(List(x3954))
    val x3970 = withCtrl(x3988) { LoopController(style=InnerPipe, level=InnerControl, cchain=x3955).name("x3970").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:53:86") } // UnrolledReduce(List(b2268, b2199, b2191),x3955,x3952,Block((x3952) => Const(())),List(List(b2276)),List(List(b2277)))
    val b2276 = withCtrl(x3970) { CounterIter(x3954, None).name("b2276") } // b2276
    val b2277 = withCtrl(x3970) { Const(true).name("b2277") } // b2277
    val x3956 = withCtrl(x3970) { OpDef(op=BitAnd, inputs=List(b2277, b2268)).name("x3956").srcCtx("UnrollingBase.scala:28:66") } // And(b2277,b2268)
    val x3957 = withCtrl(x3970) { OpDef(op=BitAnd, inputs=List(b2199, b2191)).name("x3957").srcCtx("UnrollingBase.scala:28:66") } // And(b2199,b2191)
    val x3958 = withCtrl(x3970) { OpDef(op=BitAnd, inputs=List(x3956, x3957)).name("x3958").srcCtx("UnrollingBase.scala:28:66") } // And(x3956,x3957)
    val x3959 = withCtrl(x3970) { LoadBanks(List(x3894_d1_b0), List(b2266, b2276)).name("x3959").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:53:65") } // ParSRAMLoad(x3894,List(List(b2266, b2276)),List(x3958))
    val x3960 = withCtrl(x3970) { x3959 } // VectorApply(x3959,0)
    val x3961 = withCtrl(x3970) { LoadBanks(List(x3868_d2_b0), List(b2276)).name("x3961").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:53:80") } // ParSRAMLoad(x3868,List(List(b2276)),List(x3958))
    val x3962 = withCtrl(x3970) { x3961 } // VectorApply(x3961,0)
    val x3963 = withCtrl(x3970) { OpDef(op=FltMul, inputs=List(x3960, x3962)).name("x3963").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:53:72") } // FltMul(x3960,x3962)
    val x3964 = withCtrl(x3970) { ReadMem(x3952_d1).name("x3964").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:53:86") } // RegRead(x3952)
    val x3965 = withCtrl(x3970) { OpDef(op=FixEql, inputs=List(b2276, Const(0))).name("x3965").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:53:86") } // FixEql(b2276,Const(0))
    val x3966 = withCtrl(x3970) { ReduceAccumOp(op=FltAdd, input=x3963, accum=x3964).name("x3966").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:53:90") } // FltAdd(x3963,x3964)
    val x3967 = withCtrl(x3970) { OpDef(op=BitAnd, inputs=List(b2268, b2199)).name("x3967").srcCtx("UnrollingBase.scala:28:66") } // And(b2268,b2199)
    val x3968 = withCtrl(x3970) { OpDef(op=BitAnd, inputs=List(x3967, b2191)).name("x3968").srcCtx("UnrollingBase.scala:28:66") } // And(x3967,b2191)
    val x3969_x3952_d0 = withCtrl(x3970) { WriteMem(x3952_d0, x3966).name("x3969_x3952_d0").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:53:86") } // RegWrite(x3952,x3966,x3968)
    antiDepsOf(x3969_x3952_d0)=List(x3964)
    val x3969_x3952_d1 = withCtrl(x3970) { WriteMem(x3952_d1, x3966).name("x3969_x3952_d1").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:53:86") } // RegWrite(x3952,x3966,x3968)
    antiDepsOf(x3969_x3952_d1)=List(x3964)
    val x3971 = withCtrl(x3988) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x3971").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:53:45") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x3972 = withCtrl(x3988) { CounterChain(List(x3971)).name("x3972").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:53:86") } // CounterChainNew(List(x3971))
    val x3987 = withCtrl(x3988) { LoopController(style=InnerPipe, level=InnerControl, cchain=x3972).name("x3987").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:53:86") } // UnrolledReduce(List(b2269, b2199, b2191),x3972,x3953,Block((x3953) => Const(())),List(List(b2293)),List(List(b2294)))
    val b2293 = withCtrl(x3987) { CounterIter(x3971, None).name("b2293") } // b2293
    val b2294 = withCtrl(x3987) { Const(true).name("b2294") } // b2294
    val x3973 = withCtrl(x3987) { OpDef(op=BitAnd, inputs=List(b2294, b2269)).name("x3973").srcCtx("UnrollingBase.scala:28:66") } // And(b2294,b2269)
    val x3974 = withCtrl(x3987) { OpDef(op=BitAnd, inputs=List(b2199, b2191)).name("x3974").srcCtx("UnrollingBase.scala:28:66") } // And(b2199,b2191)
    val x3975 = withCtrl(x3987) { OpDef(op=BitAnd, inputs=List(x3973, x3974)).name("x3975").srcCtx("UnrollingBase.scala:28:66") } // And(x3973,x3974)
    val x3976 = withCtrl(x3987) { LoadBanks(List(x3894_d1_b1), List(b2267, b2293)).name("x3976").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:53:65") } // ParSRAMLoad(x3894,List(List(b2267, b2293)),List(x3975))
    val x3977 = withCtrl(x3987) { x3976 } // VectorApply(x3976,0)
    val x3978 = withCtrl(x3987) { LoadBanks(List(x3868_d3_b0), List(b2293)).name("x3978").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:53:80") } // ParSRAMLoad(x3868,List(List(b2293)),List(x3975))
    val x3979 = withCtrl(x3987) { x3978 } // VectorApply(x3978,0)
    val x3980 = withCtrl(x3987) { OpDef(op=FltMul, inputs=List(x3977, x3979)).name("x3980").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:53:72") } // FltMul(x3977,x3979)
    val x3981 = withCtrl(x3987) { ReadMem(x3953_d1).name("x3981").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:53:86") } // RegRead(x3953)
    val x3982 = withCtrl(x3987) { OpDef(op=FixEql, inputs=List(b2293, Const(0))).name("x3982").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:53:86") } // FixEql(b2293,Const(0))
    val x3983 = withCtrl(x3987) { ReduceAccumOp(op=FltAdd, input=x3980, accum=x3981).name("x3983").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:53:90") } // FltAdd(x3980,x3981)
    val x3984 = withCtrl(x3987) { OpDef(op=BitAnd, inputs=List(b2269, b2199)).name("x3984").srcCtx("UnrollingBase.scala:28:66") } // And(b2269,b2199)
    val x3985 = withCtrl(x3987) { OpDef(op=BitAnd, inputs=List(x3984, b2191)).name("x3985").srcCtx("UnrollingBase.scala:28:66") } // And(x3984,b2191)
    val x3986_x3953_d0 = withCtrl(x3987) { WriteMem(x3953_d0, x3983).name("x3986_x3953_d0").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:53:86") } // RegWrite(x3953,x3983,x3985)
    antiDepsOf(x3986_x3953_d0)=List(x3981)
    val x3986_x3953_d1 = withCtrl(x3987) { WriteMem(x3953_d1, x3983).name("x3986_x3953_d1").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:53:86") } // RegWrite(x3953,x3983,x3985)
    antiDepsOf(x3986_x3953_d1)=List(x3981)
    val x3989 = withCtrl(x4063) { Reg(init=Some(0.0)).name("x3989").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:54:26:sub") } // x3989 = RegNew(Const(0.0))
    isAccum(x3989) = false
    bufferDepthOf(x3989) = 2
    val x3990 = withCtrl(x4063) { Reg(init=Some(0.0)).name("x3990").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:54:26:sub") } // x3990 = RegNew(Const(0.0))
    isAccum(x3990) = false
    bufferDepthOf(x3990) = 2
    val x4013 = withCtrl(x4063) { UnitController(style=ForkJoin, level=OuterControl).name("x4013").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b2199, b2191),Block(Const(())))
    val x4001 = withCtrl(x4013) { UnitController(style=SeqPipe, level=InnerControl).name("x4001").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:55:18") } // UnitPipe(List(b2268, b2199, b2191),Block(x4000))
    val x3991 = withCtrl(x4001) { OpDef(op=BitAnd, inputs=List(b2268, b2199)).name("x3991").srcCtx("UnrollingBase.scala:28:66") } // And(b2268,b2199)
    val x3992 = withCtrl(x4001) { OpDef(op=BitAnd, inputs=List(x3991, b2191)).name("x3992").srcCtx("UnrollingBase.scala:28:66") } // And(x3991,b2191)
    val x3993 = withCtrl(x4001) { LoadBanks(List(x3895_d0_b0), List(b2266)).name("x3993").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:56:27") } // SRAMLoad(x3895,ArrayBuffer(Const(512)),List(b2266),Const(0),x3992)
    val x3994 = withCtrl(x4001) { ReadMem(x3952_d0).name("x3994").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:56:45") } // RegRead(x3952)
    val x3995 = withCtrl(x4001) { OpDef(op=FltNeg, inputs=List(x3994)).name("x3995").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:21:47") } // FltNeg(x3994)
    val x3996 = withCtrl(x4001) { OpDef(op=FltExp, inputs=List(x3995)).name("x3996").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:21:46") } // FltExp(x3995)
    val x3997 = withCtrl(x4001) { OpDef(op=FltAdd, inputs=List(x3996, Const(1.0))).name("x3997").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:21:51") } // FltAdd(x3996,Const(1))
    val x3998 = withCtrl(x4001) { OpDef(op=FltDiv, inputs=List(Const(1.0), x3997)).name("x3998").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:21:41") } // FltDiv(Const(1),x3997)
    val x3999 = withCtrl(x4001) { OpDef(op=FltSub, inputs=List(x3993, x3998)).name("x3999").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:56:32") } // FltSub(x3993,x3998)
    val x4000_x3989 = withCtrl(x4001) { WriteMem(x3989, x3999).name("x4000_x3989").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:56:19") } // RegWrite(x3989,x3999,x3992)
    val x4012 = withCtrl(x4013) { UnitController(style=SeqPipe, level=InnerControl).name("x4012").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:55:18") } // UnitPipe(List(b2269, b2199, b2191),Block(x4011))
    val x4002 = withCtrl(x4012) { OpDef(op=BitAnd, inputs=List(b2269, b2199)).name("x4002").srcCtx("UnrollingBase.scala:28:66") } // And(b2269,b2199)
    val x4003 = withCtrl(x4012) { OpDef(op=BitAnd, inputs=List(x4002, b2191)).name("x4003").srcCtx("UnrollingBase.scala:28:66") } // And(x4002,b2191)
    val x4004 = withCtrl(x4012) { LoadBanks(List(x3895_d0_b0), List(b2267)).name("x4004").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:56:27") } // SRAMLoad(x3895,ArrayBuffer(Const(512)),List(b2267),Const(0),x4003)
    val x4005 = withCtrl(x4012) { ReadMem(x3953_d0).name("x4005").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:56:45") } // RegRead(x3953)
    val x4006 = withCtrl(x4012) { OpDef(op=FltNeg, inputs=List(x4005)).name("x4006").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:21:47") } // FltNeg(x4005)
    val x4007 = withCtrl(x4012) { OpDef(op=FltExp, inputs=List(x4006)).name("x4007").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:21:46") } // FltExp(x4006)
    val x4008 = withCtrl(x4012) { OpDef(op=FltAdd, inputs=List(x4007, Const(1.0))).name("x4008").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:21:51") } // FltAdd(x4007,Const(1))
    val x4009 = withCtrl(x4012) { OpDef(op=FltDiv, inputs=List(Const(1.0), x4008)).name("x4009").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:21:41") } // FltDiv(Const(1),x4008)
    val x4010 = withCtrl(x4012) { OpDef(op=FltSub, inputs=List(x4004, x4009)).name("x4010").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:56:32") } // FltSub(x4004,x4009)
    val x4011_x3990 = withCtrl(x4012) { WriteMem(x3990, x4010).name("x4011_x3990").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:56:19") } // RegWrite(x3990,x4010,x4003)
    val x4014_d0_b0 = withCtrl(x4063) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x4014_d0_b0").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:58:34:gradRow") } // x4014 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x4014_d0_b0) = false
    bufferDepthOf(x4014_d0_b0) = 2
    staticDimsOf(x4014_d0_b0) = List(128)
    val x4015_d0_b0 = withCtrl(x4063) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x4015_d0_b0").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:58:34:gradRow") } // x4015 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x4015_d0_b0) = false
    bufferDepthOf(x4015_d0_b0) = 2
    staticDimsOf(x4015_d0_b0) = List(128)
    val x4038 = withCtrl(x4063) { UnitController(style=ForkJoin, level=OuterControl).name("x4038").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b2199, b2191),Block(Const(())))
    val x4016 = withCtrl(x4038) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x4016").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:59:28") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x4017 = withCtrl(x4038) { CounterChain(List(x4016)).name("x4017").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:59:36") } // CounterChainNew(List(x4016))
    val x4026 = withCtrl(x4038) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4017).name("x4026").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:59:36") } // UnrolledForeach(List(b2268, b2199, b2191),x4017,Block(Const(())),List(List(b2342)),List(List(b2343)))
    val b2342 = withCtrl(x4026) { CounterIter(x4016, None).name("b2342") } // b2342
    val b2343 = withCtrl(x4026) { Const(true).name("b2343") } // b2343
    val x4018 = withCtrl(x4026) { OpDef(op=BitAnd, inputs=List(b2343, b2268)).name("x4018").srcCtx("UnrollingBase.scala:28:66") } // And(b2343,b2268)
    val x4019 = withCtrl(x4026) { OpDef(op=BitAnd, inputs=List(b2199, b2191)).name("x4019").srcCtx("UnrollingBase.scala:28:66") } // And(b2199,b2191)
    val x4020 = withCtrl(x4026) { OpDef(op=BitAnd, inputs=List(x4018, x4019)).name("x4020").srcCtx("UnrollingBase.scala:28:66") } // And(x4018,x4019)
    val x4021 = withCtrl(x4026) { LoadBanks(List(x3894_d0_b0), List(b2266, b2342)).name("x4021").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:59:61") } // ParSRAMLoad(x3894,List(List(b2266, b2342)),List(x4020))
    val x4022 = withCtrl(x4026) { x4021 } // VectorApply(x4021,0)
    val x4023 = withCtrl(x4026) { ReadMem(x3989).name("x4023").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:59:75") } // RegRead(x3989)
    val x4024 = withCtrl(x4026) { OpDef(op=FltMul, inputs=List(x4022, x4023)).name("x4024").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:59:69") } // FltMul(x4022,x4023)
    val x4025 = withCtrl(x4026) { StoreBanks(List(List(x4014_d0_b0)), List(b2342), x4024).name("x4025").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:59:54") } // ParSRAMStore(x4014,List(List(b2342)),List(x4024),List(x4020))
    val x4027 = withCtrl(x4038) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x4027").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:59:28") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x4028 = withCtrl(x4038) { CounterChain(List(x4027)).name("x4028").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:59:36") } // CounterChainNew(List(x4027))
    val x4037 = withCtrl(x4038) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4028).name("x4037").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:59:36") } // UnrolledForeach(List(b2269, b2199, b2191),x4028,Block(Const(())),List(List(b2353)),List(List(b2354)))
    val b2353 = withCtrl(x4037) { CounterIter(x4027, None).name("b2353") } // b2353
    val b2354 = withCtrl(x4037) { Const(true).name("b2354") } // b2354
    val x4029 = withCtrl(x4037) { OpDef(op=BitAnd, inputs=List(b2354, b2269)).name("x4029").srcCtx("UnrollingBase.scala:28:66") } // And(b2354,b2269)
    val x4030 = withCtrl(x4037) { OpDef(op=BitAnd, inputs=List(b2199, b2191)).name("x4030").srcCtx("UnrollingBase.scala:28:66") } // And(b2199,b2191)
    val x4031 = withCtrl(x4037) { OpDef(op=BitAnd, inputs=List(x4029, x4030)).name("x4031").srcCtx("UnrollingBase.scala:28:66") } // And(x4029,x4030)
    val x4032 = withCtrl(x4037) { LoadBanks(List(x3894_d0_b1), List(b2267, b2353)).name("x4032").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:59:61") } // ParSRAMLoad(x3894,List(List(b2267, b2353)),List(x4031))
    val x4033 = withCtrl(x4037) { x4032 } // VectorApply(x4032,0)
    val x4034 = withCtrl(x4037) { ReadMem(x3990).name("x4034").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:59:75") } // RegRead(x3990)
    val x4035 = withCtrl(x4037) { OpDef(op=FltMul, inputs=List(x4033, x4034)).name("x4035").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:59:69") } // FltMul(x4033,x4034)
    val x4036 = withCtrl(x4037) { StoreBanks(List(List(x4015_d0_b0)), List(b2353), x4035).name("x4036").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:59:54") } // ParSRAMStore(x4015,List(List(b2353)),List(x4035),List(x4031))
    val x4039 = withCtrl(x4063) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x4039").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:61:13") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x4040 = withCtrl(x4063) { CounterChain(List(x4039)).name("x4040").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:61:13") } // CounterChainNew(ArrayBuffer(x4039))
    val x4062 = withCtrl(x4063) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4040).name("x4062").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:61:13") } // UnrolledForeach(List(),x4040,Block(Const(())),ArrayBuffer(List(b2365)),ArrayBuffer(List(b2366)))
    val b2365 = withCtrl(x4062) { CounterIter(x4039, None).name("b2365") } // b2365
    val b2366 = withCtrl(x4062) { Const(true).name("b2366") } // b2366
    val x4041 = withCtrl(x4062) { OpDef(op=BitAnd, inputs=List(b2366, b2199)).name("x4041").srcCtx("UnrollingBase.scala:28:66") } // And(b2366,b2199)
    val x4042 = withCtrl(x4062) { OpDef(op=BitAnd, inputs=List(x4041, b2191)).name("x4042").srcCtx("UnrollingBase.scala:28:66") } // And(x4041,b2191)
    val x4043 = withCtrl(x4062) { LoadBanks(List(x4014_d0_b0), List(b2365)).name("x4043").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:61:13") } // ParSRAMLoad(x4014,List(ArrayBuffer(b2365)),List(x4042))
    val x4044 = withCtrl(x4062) { x4043 } // VectorApply(x4043,0)
    val x4045 = withCtrl(x4062) { LoadBanks(List(x4015_d0_b0), List(b2365)).name("x4045").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:61:13") } // ParSRAMLoad(x4015,List(ArrayBuffer(b2365)),List(x4042))
    val x4046 = withCtrl(x4062) { x4045 } // VectorApply(x4045,0)
    val x4047 = withCtrl(x4062) { LoadBanks(List(x3949_d1_b0), List(b2365)).name("x4047").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:61:13") } // ParSRAMLoad(x3949,List(ArrayBuffer(b2365)),List(x4042))
    val x4048 = withCtrl(x4062) { x4047 } // VectorApply(x4047,0)
    val x4049 = withCtrl(x4062) { OpDef(op=BitAnd, inputs=List(b2268, b2199)).name("x4049").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:61:13") } // And(b2268,b2199)
    val x4050 = withCtrl(x4062) { OpDef(op=BitAnd, inputs=List(x4049, b2191)).name("x4050").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:61:13") } // And(x4049,b2191)
    val x4051 = withCtrl(x4062) { OpDef(op=BitAnd, inputs=List(b2269, b2199)).name("x4051").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:61:13") } // And(b2269,b2199)
    val x4052 = withCtrl(x4062) { OpDef(op=BitAnd, inputs=List(x4051, b2191)).name("x4052").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:61:13") } // And(x4051,b2191)
    val x4053 = withCtrl(x4062) { OpDef(op=BitAnd, inputs=List(x4050, x4042)).name("x4053").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:61:13") } // And(x4050,x4042)
    val x4054 = withCtrl(x4062) { OpDef(op=BitAnd, inputs=List(x4052, x4042)).name("x4054").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:61:13") } // And(x4052,x4042)
    val x4055 = withCtrl(x4062) { OpDef(op=FltAdd, inputs=List(x4044, x4046)).name("x4055").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:61:17") } // FltAdd(x4044,x4046)
    val x4056 = withCtrl(x4062) { OpDef(op=MuxOp, inputs=List(x4054, x4055, x4044)).name("x4056").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:61:13") } // Mux(x4054,x4055,x4044)
    val x4057 = withCtrl(x4062) { OpDef(op=BitOr, inputs=List(x4053, x4054)).name("x4057").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:61:13") } // Or(x4053,x4054)
    val x4058 = withCtrl(x4062) { OpDef(op=FixEql, inputs=List(b2266, Const(0))).name("x4058").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:61:13") } // FixEql(b2266,Const(0))
    val x4059 = withCtrl(x4062) { OpDef(op=FltAdd, inputs=List(x4056, x4048)).name("x4059").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:61:17") } // FltAdd(x4056,x4048)
    val x4060 = withCtrl(x4062) { OpDef(op=MuxOp, inputs=List(x4058, x4056, x4059)).name("x4060").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:61:13") } // Mux(x4058,x4056,x4059)
    val x4061 = withCtrl(x4062) { StoreBanks(List(List(x3949_d0_b0), List(x3949_d1_b0)), List(b2365), x4060).name("x4061").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:61:13") } // ParSRAMStore(x3949,List(ArrayBuffer(b2365)),List(x4060),List(x4042))
    antiDepsOf(x4061)=List(x4047)
    val x4064 = withCtrl(x4078) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x4064").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:62:11") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x4065 = withCtrl(x4078) { CounterChain(List(x4064)).name("x4065").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:62:11") } // CounterChainNew(ArrayBuffer(x4064))
    val x4077 = withCtrl(x4078) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4065).name("x4077").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:62:11") } // UnrolledForeach(List(),x4065,Block(Const(())),ArrayBuffer(List(b2390)),ArrayBuffer(List(b2391)))
    val b2390 = withCtrl(x4077) { CounterIter(x4064, None).name("b2390") } // b2390
    val b2391 = withCtrl(x4077) { Const(true).name("b2391") } // b2391
    val x4066 = withCtrl(x4077) { OpDef(op=BitAnd, inputs=List(b2391, b2191)).name("x4066").srcCtx("UnrollingBase.scala:28:66") } // And(b2391,b2191)
    val x4067 = withCtrl(x4077) { LoadBanks(List(x3949_d0_b0), List(b2390)).name("x4067").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:62:11") } // ParSRAMLoad(x3949,List(ArrayBuffer(b2390)),List(x4066))
    val x4068 = withCtrl(x4077) { x4067 } // VectorApply(x4067,0)
    val x4069 = withCtrl(x4077) { LoadBanks(List(x3890_d1_b0), List(b2390)).name("x4069").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:62:11") } // ParSRAMLoad(x3890,List(ArrayBuffer(b2390)),List(x4066))
    val x4070 = withCtrl(x4077) { x4069 } // VectorApply(x4069,0)
    val x4071 = withCtrl(x4077) { OpDef(op=BitAnd, inputs=List(b2199, b2191)).name("x4071").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:62:11") } // And(b2199,b2191)
    val x4072 = withCtrl(x4077) { OpDef(op=BitAnd, inputs=List(x4071, x4066)).name("x4072").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:62:11") } // And(x4071,x4066)
    val x4073 = withCtrl(x4077) { OpDef(op=FixEql, inputs=List(b2198, Const(0))).name("x4073").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:62:11") } // FixEql(b2198,Const(0))
    val x4074 = withCtrl(x4077) { OpDef(op=FltAdd, inputs=List(x4068, x4070)).name("x4074").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:62:15") } // FltAdd(x4068,x4070)
    val x4075 = withCtrl(x4077) { OpDef(op=MuxOp, inputs=List(x4073, x4068, x4074)).name("x4075").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:62:11") } // Mux(x4073,x4068,x4074)
    val x4076 = withCtrl(x4077) { StoreBanks(List(List(x3890_d0_b0), List(x3890_d1_b0)), List(b2390), x4075).name("x4076").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:62:11") } // ParSRAMStore(x3890,List(ArrayBuffer(b2390)),List(x4075),List(x4066))
    antiDepsOf(x4076)=List(x4069)
    val x4079 = withCtrl(x4090) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x4079").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:64:24") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x4080 = withCtrl(x4090) { CounterChain(List(x4079)).name("x4080").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:64:32") } // CounterChainNew(List(x4079))
    val x4089 = withCtrl(x4090) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4080).name("x4089").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:64:32") } // UnrolledForeach(List(b2191),x4080,Block(Const(())),List(List(b2407)),List(List(b2408)))
    val b2407 = withCtrl(x4089) { CounterIter(x4079, None).name("b2407") } // b2407
    val b2408 = withCtrl(x4089) { Const(true).name("b2408") } // b2408
    val x4081 = withCtrl(x4089) { OpDef(op=BitAnd, inputs=List(b2408, b2191)).name("x4081").srcCtx("UnrollingBase.scala:28:66") } // And(b2408,b2191)
    val x4082 = withCtrl(x4089) { LoadBanks(List(x3868_d1_b0), List(b2407)).name("x4082").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:64:57") } // ParSRAMLoad(x3868,List(List(b2407)),List(x4081))
    val x4083 = withCtrl(x4089) { x4082 } // VectorApply(x4082,0)
    val x4084 = withCtrl(x4089) { LoadBanks(List(x3890_d0_b0), List(b2407)).name("x4084").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:64:67") } // ParSRAMLoad(x3890,List(List(b2407)),List(x4081))
    val x4085 = withCtrl(x4089) { x4084 } // VectorApply(x4084,0)
    val x4086 = withCtrl(x4089) { OpDef(op=FltMul, inputs=List(x4085, Const(1.0E-7))).name("x4086").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:64:71") } // FltMul(x4085,Const(0.0000001000000011686097423080354928970337))
    val x4087 = withCtrl(x4089) { OpDef(op=FltAdd, inputs=List(x4083, x4086)).name("x4087").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:64:61") } // FltAdd(x4083,x4086)
    val x4088 = withCtrl(x4089) { StoreBanks(List(List(x3868_d0_b0), List(x3868_d1_b0), List(x3868_d2_b0), List(x3868_d3_b0)), List(b2407), x4087).name("x4088").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:64:49") } // ParSRAMStore(x3868,List(List(b2407)),List(x4087),List(x4081))
    antiDepsOf(x4088)=List(x4082)
    val x4112 = withCtrl(x4113) { UnitController(style=StreamPipe, level=OuterControl).name("x4112").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:68:26") } // UnitPipe(List(Const(true)),Block(Const(())))
    val b4195 = withCtrl(x4112) { StreamOut(field="offset").name("b4195").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:68:26") } // x4091 = StreamOutNew(BurstCmdBus)
    isAccum(b4195) = false
    bufferDepthOf(b4195) = 1
    val b4196 = withCtrl(x4112) { StreamOut(field="size").name("b4196").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:68:26") } // x4091 = StreamOutNew(BurstCmdBus)
    isAccum(b4196) = false
    bufferDepthOf(b4196) = 1
    val x4092 = withCtrl(x4112) { StreamOut(field="data").name("x4092").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:68:26") } // x4092 = StreamOutNew(BurstFullDataBus())
    isAccum(x4092) = false
    bufferDepthOf(x4092) = 1
    val x4093 = withCtrl(x4112) { StreamIn(field="ack").name("x4093").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:68:26") } // x4093 = StreamInNew(BurstAckBus)
    isAccum(x4093) = false
    bufferDepthOf(x4093) = 1
    val x4101 = withCtrl(x4112) { UnitController(style=SeqPipe, level=InnerControl).name("x4101").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:68:26") } // UnitPipe(List(Const(true)),Block(x4100))
    val x4094 = withCtrl(x4101) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x4095 = withCtrl(x4101) { OpDef(op=FixSla, inputs=List(x4094, Const(2))).name("x4095").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:68:26") } // FixLsh(x4094,Const(2))
    val x4096 = withCtrl(x4101) { x4095 } // FixConvert(x4095,TRUE,_64,_0)
    val x4097 = withCtrl(x4101) { DramAddress(x3862).name("x4097").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:68:26") } // GetDRAMAddress(x3862)
    val x4099_x4098 = withCtrl(x4101) { OpDef(op=FixAdd, inputs=List(x4096, x4097)).name("x4099_x4098").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:68:26") } // FixAdd(x4096,x4097)
    // x4099 = SimpleStruct(ArrayBuffer((offset,x4098), (size,Const(512)), (isLoad,Const(false))))
    val x4100_b4197_b4195 = withCtrl(x4101) { WriteMem(b4195, x4099_x4098).name("x4100_b4197_b4195").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:68:26") } // StreamWrite(x4091,x4099,Const(true))
    val x4100_b4198_b4196 = withCtrl(x4101) { WriteMem(b4196, Const(512)).name("x4100_b4198_b4196").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:68:26") } // StreamWrite(x4091,x4099,Const(true))
    val x4102 = withCtrl(x4112) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x4102").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:68:26") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x4103 = withCtrl(x4112) { CounterChain(List(x4102)).name("x4103").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:68:26") } // CounterChainNew(List(x4102))
    val x4108 = withCtrl(x4112) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4103).name("x4108").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:68:26") } // UnrolledForeach(List(Const(true)),x4103,Block(Const(())),List(List(b2432)),List(List(b2433)))
    val b2432 = withCtrl(x4108) { CounterIter(x4102, None).name("b2432") } // b2432
    val b2433 = withCtrl(x4108) { Const(true).name("b2433") } // b2433
    val x4104 = withCtrl(x4108) { LoadBanks(List(x3868_d0_b0), List(b2432)).name("x4104").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:68:26") } // ParSRAMLoad(x3868,List(List(b2432)),List(b2433))
    val x4106_x4105 = withCtrl(x4108) { x4104 } // VectorApply(x4104,0)
    // x4106 = SimpleStruct(ArrayBuffer((_1,x4105), (_2,Const(true))))
    val x4107_x4107_x4092 = withCtrl(x4108) { WriteMem(x4092, x4106_x4105).name("x4107_x4107_x4092").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:68:26") } // ParStreamWrite(x4092,List(x4106),List(b2433))
    val x4109 = withCtrl(x4112) { FringeDenseStore(dram=List(x3862), cmdStream=List(b4195, b4196), dataStream=List(x4092), ackStream=List(x4093)).name("x4109").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:68:26") } // FringeDenseStore(x3862,x4091,x4092,x4093)
    val x4111 = withCtrl(x4112) { UnitController(style=SeqPipe, level=InnerControl).name("x4111").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:68:26") } // UnitPipe(List(Const(true)),Block(Const(())))
    val x4110_x4110 = withCtrl(x4111) { ReadMem(x4093).name("x4110_x4110").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_2.scala:68:26") } // StreamRead(x4093,Const(true))
    
  }
}
