import pir._
import pir.node._
import arch._
import prism.enums._

object LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4 extends PIRApp {
  def main(implicit design:PIRDesign) = {
    import design.pirmeta._
    val x4366 = withCtrl(design.top.topController) { ArgIn(init=0).name("x4366").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:25:22:iters") } // ArgInNew(Const(0))
    isAccum(x4366) = false
    bufferDepthOf(x4366) = 1
    boundOf(x4366) = 4
    val x4367 = withCtrl(design.top.topController) { ArgIn(init=0).name("x4367").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:26:22:N") } // ArgInNew(Const(0))
    isAccum(x4367) = false
    bufferDepthOf(x4367) = 1
    boundOf(x4367) = 8192
    val x4370 = withCtrl(design.top.topController) { ReadMem(x4367).name("x4370").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:30:21") } // RegRead(x4367)
    val x4371 = withCtrl(design.top.topController) { DRAM(dims=List(x4370, Const(128))).name("x4371").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:30:20:x") } // x4371 = DRAMNew(ArrayBuffer(x4370, Const(128)),Const(0.0))
    val x4372 = withCtrl(design.top.topController) { ReadMem(x4367).name("x4372").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:31:21") } // RegRead(x4367)
    val x4373 = withCtrl(design.top.topController) { DRAM(dims=List(x4372)).name("x4373").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:31:20:y") } // x4373 = DRAMNew(ArrayBuffer(x4372),Const(0.0))
    val x4374 = withCtrl(design.top.topController) { DRAM(dims=List(Const(128))).name("x4374").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:32:24:theta") } // x4374 = DRAMNew(ArrayBuffer(Const(128)),Const(0.0))
    val x4725 = withCtrl(design.top.topController) { UnitController(style=SeqPipe, level=OuterControl).name("x4725").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:38:11") } // Hwblock(Block(Const(())),false)
    val x4380_d0_b0 = withCtrl(x4725) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x4380_d0_b0").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:39:27:btheta") } // x4380 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x4380_d0_b0) = false
    bufferDepthOf(x4380_d0_b0) = 1
    staticDimsOf(x4380_d0_b0) = List(128)
    val x4380_d1_b0 = withCtrl(x4725) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x4380_d1_b0").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:39:27:btheta") } // x4380 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x4380_d1_b0) = true
    bufferDepthOf(x4380_d1_b0) = 1
    staticDimsOf(x4380_d1_b0) = List(128)
    val x4380_d2_b0 = withCtrl(x4725) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x4380_d2_b0").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:39:27:btheta") } // x4380 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x4380_d2_b0) = false
    bufferDepthOf(x4380_d2_b0) = 1
    staticDimsOf(x4380_d2_b0) = List(128)
    val x4380_d3_b0 = withCtrl(x4725) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x4380_d3_b0").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:39:27:btheta") } // x4380 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x4380_d3_b0) = false
    bufferDepthOf(x4380_d3_b0) = 1
    staticDimsOf(x4380_d3_b0) = List(128)
    val x4380_d4_b0 = withCtrl(x4725) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x4380_d4_b0").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:39:27:btheta") } // x4380 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x4380_d4_b0) = false
    bufferDepthOf(x4380_d4_b0) = 1
    staticDimsOf(x4380_d4_b0) = List(128)
    val x4380_d5_b0 = withCtrl(x4725) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x4380_d5_b0").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:39:27:btheta") } // x4380 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x4380_d5_b0) = false
    bufferDepthOf(x4380_d5_b0) = 1
    staticDimsOf(x4380_d5_b0) = List(128)
    val x4398 = withCtrl(x4725) { UnitController(style=StreamPipe, level=OuterControl).name("x4398").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:41:14") } // UnitPipe(List(Const(true)),Block(Const(())))
    val b4795 = withCtrl(x4398) { StreamOut(field="offset").name("b4795").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:41:14") } // x4381 = StreamOutNew(BurstCmdBus)
    isAccum(b4795) = false
    bufferDepthOf(b4795) = 1
    val b4796 = withCtrl(x4398) { StreamOut(field="size").name("b4796").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:41:14") } // x4381 = StreamOutNew(BurstCmdBus)
    isAccum(b4796) = false
    bufferDepthOf(b4796) = 1
    val x4382 = withCtrl(x4398) { StreamIn(field="data").name("x4382").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:41:14") } // x4382 = StreamInNew(BurstDataBus())
    isAccum(x4382) = false
    bufferDepthOf(x4382) = 1
    val x4390 = withCtrl(x4398) { UnitController(style=SeqPipe, level=InnerControl).name("x4390").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:41:14") } // UnitPipe(List(Const(true)),Block(x4389))
    val x4383 = withCtrl(x4390) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x4384 = withCtrl(x4390) { OpDef(op=FixSla, inputs=List(x4383, Const(2))).name("x4384").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:41:14") } // FixLsh(x4383,Const(2))
    val x4385 = withCtrl(x4390) { x4384 } // FixConvert(x4384,TRUE,_64,_0)
    val x4386 = withCtrl(x4390) { DramAddress(x4374).name("x4386").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:41:14") } // GetDRAMAddress(x4374)
    val x4388_x4387 = withCtrl(x4390) { OpDef(op=FixAdd, inputs=List(x4385, x4386)).name("x4388_x4387").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:41:14") } // FixAdd(x4385,x4386)
    // x4388 = SimpleStruct(ArrayBuffer((offset,x4387), (size,Const(512)), (isLoad,Const(true))))
    val x4389_b4797_b4795 = withCtrl(x4390) { WriteMem(b4795, x4388_x4387).name("x4389_b4797_b4795").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:41:14") } // StreamWrite(x4381,x4388,Const(true))
    val x4389_b4798_b4796 = withCtrl(x4390) { WriteMem(b4796, Const(512)).name("x4389_b4798_b4796").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:41:14") } // StreamWrite(x4381,x4388,Const(true))
    val x4391 = withCtrl(x4398) { FringeDenseLoad(dram=List(x4374), cmdStream=List(b4795, b4796), dataStream=List(x4382)).name("x4391").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:41:14") } // FringeDenseLoad(x4374,x4381,x4382)
    val x4392 = withCtrl(x4398) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x4392").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:41:14") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x4393 = withCtrl(x4398) { CounterChain(List(x4392)).name("x4393").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:41:14") } // CounterChainNew(List(x4392))
    val x4397 = withCtrl(x4398) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4393).name("x4397").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:41:14") } // UnrolledForeach(List(Const(true)),x4393,Block(Const(())),List(List(b2180)),List(List(b2181)))
    val b2180 = withCtrl(x4397) { CounterIter(x4392, None).name("b2180") } // b2180
    val b2181 = withCtrl(x4397) { Const(true).name("b2181") } // b2181
    val x4394_x4394 = withCtrl(x4397) { ReadMem(x4382).name("x4394_x4394").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:41:14") } // ParStreamRead(x4382,List(b2181))
    val x4395_x4395 = withCtrl(x4397) { x4394_x4394 } // VectorApply(x4394,0)
    val x4396 = withCtrl(x4397) { StoreBanks(List(List(x4380_d0_b0), List(x4380_d5_b0), List(x4380_d1_b0), List(x4380_d2_b0), List(x4380_d3_b0), List(x4380_d4_b0)), List(b2180), x4395_x4395).name("x4396").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:41:14") } // ParSRAMStore(x4380,List(List(b2180)),List(x4395),List(b2181))
    val x4399 = withCtrl(x4725) { ReadMem(x4366).name("x4399").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:43:26") } // RegRead(x4366)
    val x4400 = withCtrl(x4725) { Counter(min=Const(0), max=x4399, step=Const(1), par=1).name("x4400").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:43:32") } // CounterNew(Const(0),x4399,Const(1),Const(1))
    val x4401 = withCtrl(x4725) { CounterChain(List(x4400)).name("x4401").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:43:38") } // CounterChainNew(List(x4400))
    val x4702 = withCtrl(x4725) { LoopController(style=SeqPipe, level=OuterControl, cchain=x4401).name("x4702").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:43:38") } // UnrolledForeach(List(Const(true)),x4401,Block(Const(())),List(List(b2190)),List(List(b2191)))
    val b2190 = withCtrl(x4702) { CounterIter(x4400, Some(0)).name("b2190") } // b2190
    val b2191 = withCtrl(x4702) { Const(true).name("b2191") } // b2191
    val x4402_d0_b0 = withCtrl(x4702) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x4402_d0_b0").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:45:37:grad") } // x4402 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x4402_d0_b0) = false
    bufferDepthOf(x4402_d0_b0) = 1
    staticDimsOf(x4402_d0_b0) = List(128)
    val x4402_d1_b0 = withCtrl(x4702) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x4402_d1_b0").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:45:37:grad") } // x4402 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x4402_d1_b0) = true
    bufferDepthOf(x4402_d1_b0) = 1
    staticDimsOf(x4402_d1_b0) = List(128)
    val x4403 = withCtrl(x4702) { ReadMem(x4367).name("x4403").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:45:49") } // RegRead(x4367)
    val x4404 = withCtrl(x4702) { Counter(min=Const(0), max=x4403, step=Const(512), par=1).name("x4404").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:45:57") } // CounterNew(Const(0),x4403,Const(512),Const(1))
    val x4405 = withCtrl(x4702) { CounterChain(List(x4404)).name("x4405").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:62:11") } // CounterChainNew(List(x4404))
    val x4690 = withCtrl(x4702) { LoopController(style=MetaPipe, level=OuterControl, cchain=x4405).name("x4690").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:62:11") } // UnrolledReduce(List(b2191),x4405,x4402,Block((x4402) => Const(())),List(List(b2198)),List(List(b2199)))
    val b2198 = withCtrl(x4690) { CounterIter(x4404, Some(0)).name("b2198") } // b2198
    val b2199 = withCtrl(x4690) { Const(true).name("b2199") } // b2199
    val x4406_d0_b0 = withCtrl(x4690) { SRAM(size=16384, banking=Strided(banks=16, stride=1)).name("x4406_d0_b0").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:46:30:xTile") } // x4406 = SRAMNew(ArrayBuffer(Const(512), Const(128)))
    isAccum(x4406_d0_b0) = false
    bufferDepthOf(x4406_d0_b0) = 2
    staticDimsOf(x4406_d0_b0) = List(512, 128)
    val x4406_d0_b1 = withCtrl(x4690) { SRAM(size=16384, banking=Strided(banks=16, stride=1)).name("x4406_d0_b1").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:46:30:xTile") } // x4406 = SRAMNew(ArrayBuffer(Const(512), Const(128)))
    isAccum(x4406_d0_b1) = false
    bufferDepthOf(x4406_d0_b1) = 2
    staticDimsOf(x4406_d0_b1) = List(512, 128)
    val x4406_d0_b2 = withCtrl(x4690) { SRAM(size=16384, banking=Strided(banks=16, stride=1)).name("x4406_d0_b2").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:46:30:xTile") } // x4406 = SRAMNew(ArrayBuffer(Const(512), Const(128)))
    isAccum(x4406_d0_b2) = false
    bufferDepthOf(x4406_d0_b2) = 2
    staticDimsOf(x4406_d0_b2) = List(512, 128)
    val x4406_d0_b3 = withCtrl(x4690) { SRAM(size=16384, banking=Strided(banks=16, stride=1)).name("x4406_d0_b3").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:46:30:xTile") } // x4406 = SRAMNew(ArrayBuffer(Const(512), Const(128)))
    isAccum(x4406_d0_b3) = false
    bufferDepthOf(x4406_d0_b3) = 2
    staticDimsOf(x4406_d0_b3) = List(512, 128)
    val x4406_d1_b0 = withCtrl(x4690) { SRAM(size=16384, banking=Strided(banks=16, stride=1)).name("x4406_d1_b0").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:46:30:xTile") } // x4406 = SRAMNew(ArrayBuffer(Const(512), Const(128)))
    isAccum(x4406_d1_b0) = false
    bufferDepthOf(x4406_d1_b0) = 2
    staticDimsOf(x4406_d1_b0) = List(512, 128)
    val x4406_d1_b1 = withCtrl(x4690) { SRAM(size=16384, banking=Strided(banks=16, stride=1)).name("x4406_d1_b1").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:46:30:xTile") } // x4406 = SRAMNew(ArrayBuffer(Const(512), Const(128)))
    isAccum(x4406_d1_b1) = false
    bufferDepthOf(x4406_d1_b1) = 2
    staticDimsOf(x4406_d1_b1) = List(512, 128)
    val x4406_d1_b2 = withCtrl(x4690) { SRAM(size=16384, banking=Strided(banks=16, stride=1)).name("x4406_d1_b2").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:46:30:xTile") } // x4406 = SRAMNew(ArrayBuffer(Const(512), Const(128)))
    isAccum(x4406_d1_b2) = false
    bufferDepthOf(x4406_d1_b2) = 2
    staticDimsOf(x4406_d1_b2) = List(512, 128)
    val x4406_d1_b3 = withCtrl(x4690) { SRAM(size=16384, banking=Strided(banks=16, stride=1)).name("x4406_d1_b3").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:46:30:xTile") } // x4406 = SRAMNew(ArrayBuffer(Const(512), Const(128)))
    isAccum(x4406_d1_b3) = false
    bufferDepthOf(x4406_d1_b3) = 2
    staticDimsOf(x4406_d1_b3) = List(512, 128)
    val x4407_d0_b0 = withCtrl(x4690) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x4407_d0_b0").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:47:30:yTile") } // x4407 = SRAMNew(ArrayBuffer(Const(512)))
    isAccum(x4407_d0_b0) = false
    bufferDepthOf(x4407_d0_b0) = 2
    staticDimsOf(x4407_d0_b0) = List(512)
    val x4460 = withCtrl(x4690) { UnitController(style=ForkJoin, level=OuterControl).name("x4460").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:48:20") } // ParallelPipe(List(b2199, b2191),Block(Const(())))
    val x4409 = withCtrl(x4460) { UnitController(style=SeqPipe, level=InnerControl).name("x4409").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:48:20") } // UnitPipe(List(b2199, b2191),Block(Const(())))
    val x4408 = withCtrl(x4409) { OpDef(op=FixAdd, inputs=List(b2198, Const(512))).name("x4408").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:49:30") } // FixAdd(b2198,Const(512))
    val x4410 = withCtrl(x4460) { Counter(min=Const(0), max=Const(512), step=Const(1), par=1).name("x4410").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:49:19") } // CounterNew(Const(0),Const(512),Const(1),Const(1))
    val x4411 = withCtrl(x4460) { CounterChain(List(x4410)).name("x4411").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:49:19") } // CounterChainNew(List(x4410))
    val x4438 = withCtrl(x4460) { LoopController(style=StreamPipe, level=OuterControl, cchain=x4411).name("x4438").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:49:19") } // UnrolledForeach(List(b2199, b2191),x4411,Block(Const(())),List(List(b2206)),List(List(b2207)))
    val b2206 = withCtrl(x4438) { CounterIter(x4410, Some(0)).name("b2206") } // b2206
    val b2207 = withCtrl(x4438) { Const(true).name("b2207") } // b2207
    val b4799 = withCtrl(x4438) { StreamOut(field="offset").name("b4799").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:49:19") } // x4412 = StreamOutNew(BurstCmdBus)
    isAccum(b4799) = false
    bufferDepthOf(b4799) = 1
    val b4800 = withCtrl(x4438) { StreamOut(field="size").name("b4800").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:49:19") } // x4412 = StreamOutNew(BurstCmdBus)
    isAccum(b4800) = false
    bufferDepthOf(b4800) = 1
    val x4413 = withCtrl(x4438) { StreamIn(field="data").name("x4413").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:49:19") } // x4413 = StreamInNew(BurstDataBus())
    isAccum(x4413) = false
    bufferDepthOf(x4413) = 1
    val x4427 = withCtrl(x4438) { UnitController(style=SeqPipe, level=InnerControl).name("x4427").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:49:19") } // UnitPipe(List(b2207, b2199, b2191),Block(x4426))
    val x4414 = withCtrl(x4427) { OpDef(op=FixAdd, inputs=List(b2198, b2206)).name("x4414").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:49:19") } // FixAdd(b2198,b2206)
    val x4415 = withCtrl(x4427) { x4414 } // FixConvert(x4414,TRUE,_32,_0) (Same Type. No op)
    val x4416 = withCtrl(x4427) { OpDef(op=FixSla, inputs=List(x4415, Const(7))).name("x4416").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:49:19") } // FixLsh(x4415,Const(7))
    val x4417 = withCtrl(x4427) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x4418 = withCtrl(x4427) { OpDef(op=FixAdd, inputs=List(x4416, x4417)).name("x4418").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:49:19") } // FixAdd(x4416,x4417)
    val x4419 = withCtrl(x4427) { OpDef(op=FixSla, inputs=List(x4418, Const(2))).name("x4419").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:49:19") } // FixLsh(x4418,Const(2))
    val x4420 = withCtrl(x4427) { x4419 } // FixConvert(x4419,TRUE,_64,_0)
    val x4421 = withCtrl(x4427) { DramAddress(x4371).name("x4421").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:49:19") } // GetDRAMAddress(x4371)
    val x4423_x4422 = withCtrl(x4427) { OpDef(op=FixAdd, inputs=List(x4420, x4421)).name("x4423_x4422").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:49:19") } // FixAdd(x4420,x4421)
    // x4423 = SimpleStruct(ArrayBuffer((offset,x4422), (size,Const(512)), (isLoad,Const(true))))
    val x4424 = withCtrl(x4427) { OpDef(op=BitAnd, inputs=List(b2207, b2199)).name("x4424").srcCtx("UnrollingBase.scala:28:66") } // And(b2207,b2199)
    val x4425 = withCtrl(x4427) { OpDef(op=BitAnd, inputs=List(x4424, b2191)).name("x4425").srcCtx("UnrollingBase.scala:28:66") } // And(x4424,b2191)
    val x4426_b4801_b4799 = withCtrl(x4427) { WriteMem(b4799, x4423_x4422).name("x4426_b4801_b4799").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:49:19") } // StreamWrite(x4412,x4423,x4425)
    val x4426_b4802_b4800 = withCtrl(x4427) { WriteMem(b4800, Const(512)).name("x4426_b4802_b4800").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:49:19") } // StreamWrite(x4412,x4423,x4425)
    val x4428 = withCtrl(x4438) { FringeDenseLoad(dram=List(x4371), cmdStream=List(b4799, b4800), dataStream=List(x4413)).name("x4428").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:49:19") } // FringeDenseLoad(x4371,x4412,x4413)
    val x4429 = withCtrl(x4438) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x4429").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:49:19") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x4430 = withCtrl(x4438) { CounterChain(List(x4429)).name("x4430").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:49:19") } // CounterChainNew(List(x4429))
    val x4437 = withCtrl(x4438) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4430).name("x4437").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:49:19") } // UnrolledForeach(List(b2207, b2199, b2191),x4430,Block(Const(())),List(List(b2227)),List(List(b2228)))
    val b2227 = withCtrl(x4437) { CounterIter(x4429, None).name("b2227") } // b2227
    val b2228 = withCtrl(x4437) { Const(true).name("b2228") } // b2228
    val x4431 = withCtrl(x4437) { OpDef(op=BitAnd, inputs=List(b2228, b2207)).name("x4431").srcCtx("UnrollingBase.scala:28:66") } // And(b2228,b2207)
    val x4432 = withCtrl(x4437) { OpDef(op=BitAnd, inputs=List(b2199, b2191)).name("x4432").srcCtx("UnrollingBase.scala:28:66") } // And(b2199,b2191)
    val x4433 = withCtrl(x4437) { OpDef(op=BitAnd, inputs=List(x4431, x4432)).name("x4433").srcCtx("UnrollingBase.scala:28:66") } // And(x4431,x4432)
    val x4434_x4434 = withCtrl(x4437) { ReadMem(x4413).name("x4434_x4434").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:49:19") } // ParStreamRead(x4413,List(x4433))
    val x4435_x4435 = withCtrl(x4437) { x4434_x4434 } // VectorApply(x4434,0)
    val x4436 = withCtrl(x4437) { StoreBanks(List(List(x4406_d0_b0, x4406_d0_b1, x4406_d0_b2, x4406_d0_b3), List(x4406_d1_b0, x4406_d1_b1, x4406_d1_b2, x4406_d1_b3)), List(b2206, b2227), x4435_x4435).name("x4436").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:49:19") } // ParSRAMStore(x4406,List(List(b2206, b2227)),List(x4435),List(x4433))
    val x4459 = withCtrl(x4460) { UnitController(style=StreamPipe, level=OuterControl).name("x4459").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:50:19") } // UnitPipe(List(b2199, b2191),Block(Const(())))
    val b4803 = withCtrl(x4459) { StreamOut(field="offset").name("b4803").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:50:19") } // x4439 = StreamOutNew(BurstCmdBus)
    isAccum(b4803) = false
    bufferDepthOf(b4803) = 1
    val b4804 = withCtrl(x4459) { StreamOut(field="size").name("b4804").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:50:19") } // x4439 = StreamOutNew(BurstCmdBus)
    isAccum(b4804) = false
    bufferDepthOf(b4804) = 1
    val x4440 = withCtrl(x4459) { StreamIn(field="data").name("x4440").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:50:19") } // x4440 = StreamInNew(BurstDataBus())
    isAccum(x4440) = false
    bufferDepthOf(x4440) = 1
    val x4449 = withCtrl(x4459) { UnitController(style=SeqPipe, level=InnerControl).name("x4449").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:50:19") } // UnitPipe(List(b2199, b2191),Block(x4448))
    val x4441 = withCtrl(x4449) { b2198 } // FixConvert(b2198,TRUE,_32,_0) (Same Type. No op)
    val x4442 = withCtrl(x4449) { OpDef(op=FixSla, inputs=List(x4441, Const(2))).name("x4442").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:50:19") } // FixLsh(x4441,Const(2))
    val x4443 = withCtrl(x4449) { x4442 } // FixConvert(x4442,TRUE,_64,_0)
    val x4444 = withCtrl(x4449) { DramAddress(x4373).name("x4444").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:50:19") } // GetDRAMAddress(x4373)
    val x4446_x4445 = withCtrl(x4449) { OpDef(op=FixAdd, inputs=List(x4443, x4444)).name("x4446_x4445").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:50:19") } // FixAdd(x4443,x4444)
    // x4446 = SimpleStruct(ArrayBuffer((offset,x4445), (size,Const(2048)), (isLoad,Const(true))))
    val x4447 = withCtrl(x4449) { OpDef(op=BitAnd, inputs=List(b2199, b2191)).name("x4447").srcCtx("UnrollingBase.scala:28:66") } // And(b2199,b2191)
    val x4448_b4805_b4803 = withCtrl(x4449) { WriteMem(b4803, x4446_x4445).name("x4448_b4805_b4803").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:50:19") } // StreamWrite(x4439,x4446,x4447)
    val x4448_b4806_b4804 = withCtrl(x4449) { WriteMem(b4804, Const(2048)).name("x4448_b4806_b4804").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:50:19") } // StreamWrite(x4439,x4446,x4447)
    val x4450 = withCtrl(x4459) { FringeDenseLoad(dram=List(x4373), cmdStream=List(b4803, b4804), dataStream=List(x4440)).name("x4450").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:50:19") } // FringeDenseLoad(x4373,x4439,x4440)
    val x4451 = withCtrl(x4459) { Counter(min=Const(0), max=Const(512), step=Const(1), par=16).name("x4451").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:50:19") } // CounterNew(Const(0),Const(512),Const(1),Const(16))
    val x4452 = withCtrl(x4459) { CounterChain(List(x4451)).name("x4452").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:50:19") } // CounterChainNew(List(x4451))
    val x4458 = withCtrl(x4459) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4452).name("x4458").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:50:19") } // UnrolledForeach(List(b2199, b2191),x4452,Block(Const(())),List(List(b2251)),List(List(b2252)))
    val b2251 = withCtrl(x4458) { CounterIter(x4451, None).name("b2251") } // b2251
    val b2252 = withCtrl(x4458) { Const(true).name("b2252") } // b2252
    val x4453 = withCtrl(x4458) { OpDef(op=BitAnd, inputs=List(b2252, b2199)).name("x4453").srcCtx("UnrollingBase.scala:28:66") } // And(b2252,b2199)
    val x4454 = withCtrl(x4458) { OpDef(op=BitAnd, inputs=List(x4453, b2191)).name("x4454").srcCtx("UnrollingBase.scala:28:66") } // And(x4453,b2191)
    val x4455_x4455 = withCtrl(x4458) { ReadMem(x4440).name("x4455_x4455").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:50:19") } // ParStreamRead(x4440,List(x4454))
    val x4456_x4456 = withCtrl(x4458) { x4455_x4455 } // VectorApply(x4455,0)
    val x4457 = withCtrl(x4458) { StoreBanks(List(List(x4407_d0_b0)), List(b2251), x4456_x4456).name("x4457").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:50:19") } // ParSRAMStore(x4407,List(List(b2251)),List(x4456),List(x4454))
    val x4461_d0_b0 = withCtrl(x4690) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x4461_d0_b0").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:52:28") } // x4461 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x4461_d0_b0) = false
    bufferDepthOf(x4461_d0_b0) = 2
    staticDimsOf(x4461_d0_b0) = List(128)
    val x4461_d1_b0 = withCtrl(x4690) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x4461_d1_b0").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:52:28") } // x4461 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x4461_d1_b0) = true
    bufferDepthOf(x4461_d1_b0) = 1
    staticDimsOf(x4461_d1_b0) = List(128)
    val x4462 = withCtrl(x4690) { Counter(min=Const(0), max=Const(512), step=Const(1), par=4).name("x4462").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:52:48") } // CounterNew(Const(0),Const(512),Const(1),Const(4))
    val x4463 = withCtrl(x4690) { CounterChain(List(x4462)).name("x4463").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:61:13") } // CounterChainNew(List(x4462))
    val x4675 = withCtrl(x4690) { LoopController(style=MetaPipe, level=OuterControl, cchain=x4463).name("x4675").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:61:13") } // UnrolledReduce(List(b2199, b2191),x4463,x4461,Block((x4461) => Const(())),List(List(b2266, b2267, b2268, b2269)),List(List(b2270, b2271, b2272, b2273)))
    val b2266 = withCtrl(x4675) { CounterIter(x4462, Some(0)).name("b2266") } // b2266
    val b2270 = withCtrl(x4675) { Const(true).name("b2270") } // b2270
    val b2267 = withCtrl(x4675) { CounterIter(x4462, Some(1)).name("b2267") } // b2267
    val b2271 = withCtrl(x4675) { Const(true).name("b2271") } // b2271
    val b2268 = withCtrl(x4675) { CounterIter(x4462, Some(2)).name("b2268") } // b2268
    val b2272 = withCtrl(x4675) { Const(true).name("b2272") } // b2272
    val b2269 = withCtrl(x4675) { CounterIter(x4462, Some(3)).name("b2269") } // b2269
    val b2273 = withCtrl(x4675) { Const(true).name("b2273") } // b2273
    val x4464_d0 = withCtrl(x4675) { Reg(init=Some(0.0)).name("x4464_d0").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:53:33:dot") } // x4464 = RegNew(Const(0.0))
    isAccum(x4464_d0) = false
    bufferDepthOf(x4464_d0) = 2
    val x4464_d1 = withCtrl(x4675) { Reg(init=Some(0.0)).name("x4464_d1").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:53:33:dot") } // x4464 = RegNew(Const(0.0))
    isAccum(x4464_d1) = true
    bufferDepthOf(x4464_d1) = 1
    val x4465_d0 = withCtrl(x4675) { Reg(init=Some(0.0)).name("x4465_d0").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:53:33:dot") } // x4465 = RegNew(Const(0.0))
    isAccum(x4465_d0) = false
    bufferDepthOf(x4465_d0) = 2
    val x4465_d1 = withCtrl(x4675) { Reg(init=Some(0.0)).name("x4465_d1").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:53:33:dot") } // x4465 = RegNew(Const(0.0))
    isAccum(x4465_d1) = true
    bufferDepthOf(x4465_d1) = 1
    val x4466_d0 = withCtrl(x4675) { Reg(init=Some(0.0)).name("x4466_d0").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:53:33:dot") } // x4466 = RegNew(Const(0.0))
    isAccum(x4466_d0) = false
    bufferDepthOf(x4466_d0) = 2
    val x4466_d1 = withCtrl(x4675) { Reg(init=Some(0.0)).name("x4466_d1").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:53:33:dot") } // x4466 = RegNew(Const(0.0))
    isAccum(x4466_d1) = true
    bufferDepthOf(x4466_d1) = 1
    val x4467_d0 = withCtrl(x4675) { Reg(init=Some(0.0)).name("x4467_d0").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:53:33:dot") } // x4467 = RegNew(Const(0.0))
    isAccum(x4467_d0) = false
    bufferDepthOf(x4467_d0) = 2
    val x4467_d1 = withCtrl(x4675) { Reg(init=Some(0.0)).name("x4467_d1").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:53:33:dot") } // x4467 = RegNew(Const(0.0))
    isAccum(x4467_d1) = true
    bufferDepthOf(x4467_d1) = 1
    val x4536 = withCtrl(x4675) { UnitController(style=ForkJoin, level=OuterControl).name("x4536").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b2199, b2191),Block(Const(())))
    val x4468 = withCtrl(x4536) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x4468").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:53:45") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x4469 = withCtrl(x4536) { CounterChain(List(x4468)).name("x4469").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:53:86") } // CounterChainNew(List(x4468))
    val x4484 = withCtrl(x4536) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4469).name("x4484").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:53:86") } // UnrolledReduce(List(b2270, b2199, b2191),x4469,x4464,Block((x4464) => Const(())),List(List(b2286)),List(List(b2287)))
    val b2286 = withCtrl(x4484) { CounterIter(x4468, None).name("b2286") } // b2286
    val b2287 = withCtrl(x4484) { Const(true).name("b2287") } // b2287
    val x4470 = withCtrl(x4484) { OpDef(op=BitAnd, inputs=List(b2287, b2270)).name("x4470").srcCtx("UnrollingBase.scala:28:66") } // And(b2287,b2270)
    val x4471 = withCtrl(x4484) { OpDef(op=BitAnd, inputs=List(b2199, b2191)).name("x4471").srcCtx("UnrollingBase.scala:28:66") } // And(b2199,b2191)
    val x4472 = withCtrl(x4484) { OpDef(op=BitAnd, inputs=List(x4470, x4471)).name("x4472").srcCtx("UnrollingBase.scala:28:66") } // And(x4470,x4471)
    val x4473 = withCtrl(x4484) { LoadBanks(List(x4406_d1_b0), List(b2266, b2286)).name("x4473").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:53:65") } // ParSRAMLoad(x4406,List(List(b2266, b2286)),List(x4472))
    val x4474 = withCtrl(x4484) { x4473 } // VectorApply(x4473,0)
    val x4475 = withCtrl(x4484) { LoadBanks(List(x4380_d2_b0), List(b2286)).name("x4475").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:53:80") } // ParSRAMLoad(x4380,List(List(b2286)),List(x4472))
    val x4476 = withCtrl(x4484) { x4475 } // VectorApply(x4475,0)
    val x4477 = withCtrl(x4484) { OpDef(op=FltMul, inputs=List(x4474, x4476)).name("x4477").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:53:72") } // FltMul(x4474,x4476)
    val x4478 = withCtrl(x4484) { ReadMem(x4464_d1).name("x4478").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:53:86") } // RegRead(x4464)
    val x4479 = withCtrl(x4484) { OpDef(op=FixEql, inputs=List(b2286, Const(0))).name("x4479").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:53:86") } // FixEql(b2286,Const(0))
    val x4480 = withCtrl(x4484) { ReduceAccumOp(op=FltAdd, input=x4477, accum=x4478).name("x4480").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:53:90") } // FltAdd(x4477,x4478)
    val x4481 = withCtrl(x4484) { OpDef(op=BitAnd, inputs=List(b2270, b2199)).name("x4481").srcCtx("UnrollingBase.scala:28:66") } // And(b2270,b2199)
    val x4482 = withCtrl(x4484) { OpDef(op=BitAnd, inputs=List(x4481, b2191)).name("x4482").srcCtx("UnrollingBase.scala:28:66") } // And(x4481,b2191)
    val x4483_x4464_d0 = withCtrl(x4484) { WriteMem(x4464_d0, x4480).name("x4483_x4464_d0").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:53:86") } // RegWrite(x4464,x4480,x4482)
    antiDepsOf(x4483_x4464_d0)=List(x4478)
    val x4483_x4464_d1 = withCtrl(x4484) { WriteMem(x4464_d1, x4480).name("x4483_x4464_d1").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:53:86") } // RegWrite(x4464,x4480,x4482)
    antiDepsOf(x4483_x4464_d1)=List(x4478)
    val x4485 = withCtrl(x4536) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x4485").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:53:45") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x4486 = withCtrl(x4536) { CounterChain(List(x4485)).name("x4486").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:53:86") } // CounterChainNew(List(x4485))
    val x4501 = withCtrl(x4536) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4486).name("x4501").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:53:86") } // UnrolledReduce(List(b2271, b2199, b2191),x4486,x4465,Block((x4465) => Const(())),List(List(b2303)),List(List(b2304)))
    val b2303 = withCtrl(x4501) { CounterIter(x4485, None).name("b2303") } // b2303
    val b2304 = withCtrl(x4501) { Const(true).name("b2304") } // b2304
    val x4487 = withCtrl(x4501) { OpDef(op=BitAnd, inputs=List(b2304, b2271)).name("x4487").srcCtx("UnrollingBase.scala:28:66") } // And(b2304,b2271)
    val x4488 = withCtrl(x4501) { OpDef(op=BitAnd, inputs=List(b2199, b2191)).name("x4488").srcCtx("UnrollingBase.scala:28:66") } // And(b2199,b2191)
    val x4489 = withCtrl(x4501) { OpDef(op=BitAnd, inputs=List(x4487, x4488)).name("x4489").srcCtx("UnrollingBase.scala:28:66") } // And(x4487,x4488)
    val x4490 = withCtrl(x4501) { LoadBanks(List(x4406_d1_b1), List(b2267, b2303)).name("x4490").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:53:65") } // ParSRAMLoad(x4406,List(List(b2267, b2303)),List(x4489))
    val x4491 = withCtrl(x4501) { x4490 } // VectorApply(x4490,0)
    val x4492 = withCtrl(x4501) { LoadBanks(List(x4380_d3_b0), List(b2303)).name("x4492").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:53:80") } // ParSRAMLoad(x4380,List(List(b2303)),List(x4489))
    val x4493 = withCtrl(x4501) { x4492 } // VectorApply(x4492,0)
    val x4494 = withCtrl(x4501) { OpDef(op=FltMul, inputs=List(x4491, x4493)).name("x4494").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:53:72") } // FltMul(x4491,x4493)
    val x4495 = withCtrl(x4501) { ReadMem(x4465_d1).name("x4495").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:53:86") } // RegRead(x4465)
    val x4496 = withCtrl(x4501) { OpDef(op=FixEql, inputs=List(b2303, Const(0))).name("x4496").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:53:86") } // FixEql(b2303,Const(0))
    val x4497 = withCtrl(x4501) { ReduceAccumOp(op=FltAdd, input=x4494, accum=x4495).name("x4497").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:53:90") } // FltAdd(x4494,x4495)
    val x4498 = withCtrl(x4501) { OpDef(op=BitAnd, inputs=List(b2271, b2199)).name("x4498").srcCtx("UnrollingBase.scala:28:66") } // And(b2271,b2199)
    val x4499 = withCtrl(x4501) { OpDef(op=BitAnd, inputs=List(x4498, b2191)).name("x4499").srcCtx("UnrollingBase.scala:28:66") } // And(x4498,b2191)
    val x4500_x4465_d0 = withCtrl(x4501) { WriteMem(x4465_d0, x4497).name("x4500_x4465_d0").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:53:86") } // RegWrite(x4465,x4497,x4499)
    antiDepsOf(x4500_x4465_d0)=List(x4495)
    val x4500_x4465_d1 = withCtrl(x4501) { WriteMem(x4465_d1, x4497).name("x4500_x4465_d1").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:53:86") } // RegWrite(x4465,x4497,x4499)
    antiDepsOf(x4500_x4465_d1)=List(x4495)
    val x4502 = withCtrl(x4536) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x4502").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:53:45") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x4503 = withCtrl(x4536) { CounterChain(List(x4502)).name("x4503").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:53:86") } // CounterChainNew(List(x4502))
    val x4518 = withCtrl(x4536) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4503).name("x4518").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:53:86") } // UnrolledReduce(List(b2272, b2199, b2191),x4503,x4466,Block((x4466) => Const(())),List(List(b2320)),List(List(b2321)))
    val b2320 = withCtrl(x4518) { CounterIter(x4502, None).name("b2320") } // b2320
    val b2321 = withCtrl(x4518) { Const(true).name("b2321") } // b2321
    val x4504 = withCtrl(x4518) { OpDef(op=BitAnd, inputs=List(b2321, b2272)).name("x4504").srcCtx("UnrollingBase.scala:28:66") } // And(b2321,b2272)
    val x4505 = withCtrl(x4518) { OpDef(op=BitAnd, inputs=List(b2199, b2191)).name("x4505").srcCtx("UnrollingBase.scala:28:66") } // And(b2199,b2191)
    val x4506 = withCtrl(x4518) { OpDef(op=BitAnd, inputs=List(x4504, x4505)).name("x4506").srcCtx("UnrollingBase.scala:28:66") } // And(x4504,x4505)
    val x4507 = withCtrl(x4518) { LoadBanks(List(x4406_d1_b2), List(b2268, b2320)).name("x4507").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:53:65") } // ParSRAMLoad(x4406,List(List(b2268, b2320)),List(x4506))
    val x4508 = withCtrl(x4518) { x4507 } // VectorApply(x4507,0)
    val x4509 = withCtrl(x4518) { LoadBanks(List(x4380_d4_b0), List(b2320)).name("x4509").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:53:80") } // ParSRAMLoad(x4380,List(List(b2320)),List(x4506))
    val x4510 = withCtrl(x4518) { x4509 } // VectorApply(x4509,0)
    val x4511 = withCtrl(x4518) { OpDef(op=FltMul, inputs=List(x4508, x4510)).name("x4511").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:53:72") } // FltMul(x4508,x4510)
    val x4512 = withCtrl(x4518) { ReadMem(x4466_d1).name("x4512").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:53:86") } // RegRead(x4466)
    val x4513 = withCtrl(x4518) { OpDef(op=FixEql, inputs=List(b2320, Const(0))).name("x4513").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:53:86") } // FixEql(b2320,Const(0))
    val x4514 = withCtrl(x4518) { ReduceAccumOp(op=FltAdd, input=x4511, accum=x4512).name("x4514").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:53:90") } // FltAdd(x4511,x4512)
    val x4515 = withCtrl(x4518) { OpDef(op=BitAnd, inputs=List(b2272, b2199)).name("x4515").srcCtx("UnrollingBase.scala:28:66") } // And(b2272,b2199)
    val x4516 = withCtrl(x4518) { OpDef(op=BitAnd, inputs=List(x4515, b2191)).name("x4516").srcCtx("UnrollingBase.scala:28:66") } // And(x4515,b2191)
    val x4517_x4466_d0 = withCtrl(x4518) { WriteMem(x4466_d0, x4514).name("x4517_x4466_d0").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:53:86") } // RegWrite(x4466,x4514,x4516)
    antiDepsOf(x4517_x4466_d0)=List(x4512)
    val x4517_x4466_d1 = withCtrl(x4518) { WriteMem(x4466_d1, x4514).name("x4517_x4466_d1").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:53:86") } // RegWrite(x4466,x4514,x4516)
    antiDepsOf(x4517_x4466_d1)=List(x4512)
    val x4519 = withCtrl(x4536) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x4519").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:53:45") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x4520 = withCtrl(x4536) { CounterChain(List(x4519)).name("x4520").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:53:86") } // CounterChainNew(List(x4519))
    val x4535 = withCtrl(x4536) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4520).name("x4535").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:53:86") } // UnrolledReduce(List(b2273, b2199, b2191),x4520,x4467,Block((x4467) => Const(())),List(List(b2337)),List(List(b2338)))
    val b2337 = withCtrl(x4535) { CounterIter(x4519, None).name("b2337") } // b2337
    val b2338 = withCtrl(x4535) { Const(true).name("b2338") } // b2338
    val x4521 = withCtrl(x4535) { OpDef(op=BitAnd, inputs=List(b2338, b2273)).name("x4521").srcCtx("UnrollingBase.scala:28:66") } // And(b2338,b2273)
    val x4522 = withCtrl(x4535) { OpDef(op=BitAnd, inputs=List(b2199, b2191)).name("x4522").srcCtx("UnrollingBase.scala:28:66") } // And(b2199,b2191)
    val x4523 = withCtrl(x4535) { OpDef(op=BitAnd, inputs=List(x4521, x4522)).name("x4523").srcCtx("UnrollingBase.scala:28:66") } // And(x4521,x4522)
    val x4524 = withCtrl(x4535) { LoadBanks(List(x4406_d1_b3), List(b2269, b2337)).name("x4524").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:53:65") } // ParSRAMLoad(x4406,List(List(b2269, b2337)),List(x4523))
    val x4525 = withCtrl(x4535) { x4524 } // VectorApply(x4524,0)
    val x4526 = withCtrl(x4535) { LoadBanks(List(x4380_d5_b0), List(b2337)).name("x4526").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:53:80") } // ParSRAMLoad(x4380,List(List(b2337)),List(x4523))
    val x4527 = withCtrl(x4535) { x4526 } // VectorApply(x4526,0)
    val x4528 = withCtrl(x4535) { OpDef(op=FltMul, inputs=List(x4525, x4527)).name("x4528").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:53:72") } // FltMul(x4525,x4527)
    val x4529 = withCtrl(x4535) { ReadMem(x4467_d1).name("x4529").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:53:86") } // RegRead(x4467)
    val x4530 = withCtrl(x4535) { OpDef(op=FixEql, inputs=List(b2337, Const(0))).name("x4530").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:53:86") } // FixEql(b2337,Const(0))
    val x4531 = withCtrl(x4535) { ReduceAccumOp(op=FltAdd, input=x4528, accum=x4529).name("x4531").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:53:90") } // FltAdd(x4528,x4529)
    val x4532 = withCtrl(x4535) { OpDef(op=BitAnd, inputs=List(b2273, b2199)).name("x4532").srcCtx("UnrollingBase.scala:28:66") } // And(b2273,b2199)
    val x4533 = withCtrl(x4535) { OpDef(op=BitAnd, inputs=List(x4532, b2191)).name("x4533").srcCtx("UnrollingBase.scala:28:66") } // And(x4532,b2191)
    val x4534_x4467_d0 = withCtrl(x4535) { WriteMem(x4467_d0, x4531).name("x4534_x4467_d0").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:53:86") } // RegWrite(x4467,x4531,x4533)
    antiDepsOf(x4534_x4467_d0)=List(x4529)
    val x4534_x4467_d1 = withCtrl(x4535) { WriteMem(x4467_d1, x4531).name("x4534_x4467_d1").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:53:86") } // RegWrite(x4467,x4531,x4533)
    antiDepsOf(x4534_x4467_d1)=List(x4529)
    val x4537 = withCtrl(x4675) { Reg(init=Some(0.0)).name("x4537").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:54:26:sub") } // x4537 = RegNew(Const(0.0))
    isAccum(x4537) = false
    bufferDepthOf(x4537) = 2
    val x4538 = withCtrl(x4675) { Reg(init=Some(0.0)).name("x4538").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:54:26:sub") } // x4538 = RegNew(Const(0.0))
    isAccum(x4538) = false
    bufferDepthOf(x4538) = 2
    val x4539 = withCtrl(x4675) { Reg(init=Some(0.0)).name("x4539").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:54:26:sub") } // x4539 = RegNew(Const(0.0))
    isAccum(x4539) = false
    bufferDepthOf(x4539) = 2
    val x4540 = withCtrl(x4675) { Reg(init=Some(0.0)).name("x4540").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:54:26:sub") } // x4540 = RegNew(Const(0.0))
    isAccum(x4540) = false
    bufferDepthOf(x4540) = 2
    val x4585 = withCtrl(x4675) { UnitController(style=ForkJoin, level=OuterControl).name("x4585").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b2199, b2191),Block(Const(())))
    val x4551 = withCtrl(x4585) { UnitController(style=SeqPipe, level=InnerControl).name("x4551").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:55:18") } // UnitPipe(List(b2270, b2199, b2191),Block(x4550))
    val x4541 = withCtrl(x4551) { OpDef(op=BitAnd, inputs=List(b2270, b2199)).name("x4541").srcCtx("UnrollingBase.scala:28:66") } // And(b2270,b2199)
    val x4542 = withCtrl(x4551) { OpDef(op=BitAnd, inputs=List(x4541, b2191)).name("x4542").srcCtx("UnrollingBase.scala:28:66") } // And(x4541,b2191)
    val x4543 = withCtrl(x4551) { LoadBanks(List(x4407_d0_b0), List(b2266)).name("x4543").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:56:27") } // SRAMLoad(x4407,ArrayBuffer(Const(512)),List(b2266),Const(0),x4542)
    val x4544 = withCtrl(x4551) { ReadMem(x4464_d0).name("x4544").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:56:45") } // RegRead(x4464)
    val x4545 = withCtrl(x4551) { OpDef(op=FltNeg, inputs=List(x4544)).name("x4545").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:21:47") } // FltNeg(x4544)
    val x4546 = withCtrl(x4551) { OpDef(op=FltExp, inputs=List(x4545)).name("x4546").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:21:46") } // FltExp(x4545)
    val x4547 = withCtrl(x4551) { OpDef(op=FltAdd, inputs=List(x4546, Const(1.0))).name("x4547").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:21:51") } // FltAdd(x4546,Const(1))
    val x4548 = withCtrl(x4551) { OpDef(op=FltDiv, inputs=List(Const(1.0), x4547)).name("x4548").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:21:41") } // FltDiv(Const(1),x4547)
    val x4549 = withCtrl(x4551) { OpDef(op=FltSub, inputs=List(x4543, x4548)).name("x4549").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:56:32") } // FltSub(x4543,x4548)
    val x4550_x4537 = withCtrl(x4551) { WriteMem(x4537, x4549).name("x4550_x4537").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:56:19") } // RegWrite(x4537,x4549,x4542)
    val x4562 = withCtrl(x4585) { UnitController(style=SeqPipe, level=InnerControl).name("x4562").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:55:18") } // UnitPipe(List(b2271, b2199, b2191),Block(x4561))
    val x4552 = withCtrl(x4562) { OpDef(op=BitAnd, inputs=List(b2271, b2199)).name("x4552").srcCtx("UnrollingBase.scala:28:66") } // And(b2271,b2199)
    val x4553 = withCtrl(x4562) { OpDef(op=BitAnd, inputs=List(x4552, b2191)).name("x4553").srcCtx("UnrollingBase.scala:28:66") } // And(x4552,b2191)
    val x4554 = withCtrl(x4562) { LoadBanks(List(x4407_d0_b0), List(b2267)).name("x4554").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:56:27") } // SRAMLoad(x4407,ArrayBuffer(Const(512)),List(b2267),Const(0),x4553)
    val x4555 = withCtrl(x4562) { ReadMem(x4465_d0).name("x4555").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:56:45") } // RegRead(x4465)
    val x4556 = withCtrl(x4562) { OpDef(op=FltNeg, inputs=List(x4555)).name("x4556").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:21:47") } // FltNeg(x4555)
    val x4557 = withCtrl(x4562) { OpDef(op=FltExp, inputs=List(x4556)).name("x4557").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:21:46") } // FltExp(x4556)
    val x4558 = withCtrl(x4562) { OpDef(op=FltAdd, inputs=List(x4557, Const(1.0))).name("x4558").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:21:51") } // FltAdd(x4557,Const(1))
    val x4559 = withCtrl(x4562) { OpDef(op=FltDiv, inputs=List(Const(1.0), x4558)).name("x4559").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:21:41") } // FltDiv(Const(1),x4558)
    val x4560 = withCtrl(x4562) { OpDef(op=FltSub, inputs=List(x4554, x4559)).name("x4560").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:56:32") } // FltSub(x4554,x4559)
    val x4561_x4538 = withCtrl(x4562) { WriteMem(x4538, x4560).name("x4561_x4538").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:56:19") } // RegWrite(x4538,x4560,x4553)
    val x4573 = withCtrl(x4585) { UnitController(style=SeqPipe, level=InnerControl).name("x4573").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:55:18") } // UnitPipe(List(b2272, b2199, b2191),Block(x4572))
    val x4563 = withCtrl(x4573) { OpDef(op=BitAnd, inputs=List(b2272, b2199)).name("x4563").srcCtx("UnrollingBase.scala:28:66") } // And(b2272,b2199)
    val x4564 = withCtrl(x4573) { OpDef(op=BitAnd, inputs=List(x4563, b2191)).name("x4564").srcCtx("UnrollingBase.scala:28:66") } // And(x4563,b2191)
    val x4565 = withCtrl(x4573) { LoadBanks(List(x4407_d0_b0), List(b2268)).name("x4565").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:56:27") } // SRAMLoad(x4407,ArrayBuffer(Const(512)),List(b2268),Const(0),x4564)
    val x4566 = withCtrl(x4573) { ReadMem(x4466_d0).name("x4566").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:56:45") } // RegRead(x4466)
    val x4567 = withCtrl(x4573) { OpDef(op=FltNeg, inputs=List(x4566)).name("x4567").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:21:47") } // FltNeg(x4566)
    val x4568 = withCtrl(x4573) { OpDef(op=FltExp, inputs=List(x4567)).name("x4568").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:21:46") } // FltExp(x4567)
    val x4569 = withCtrl(x4573) { OpDef(op=FltAdd, inputs=List(x4568, Const(1.0))).name("x4569").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:21:51") } // FltAdd(x4568,Const(1))
    val x4570 = withCtrl(x4573) { OpDef(op=FltDiv, inputs=List(Const(1.0), x4569)).name("x4570").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:21:41") } // FltDiv(Const(1),x4569)
    val x4571 = withCtrl(x4573) { OpDef(op=FltSub, inputs=List(x4565, x4570)).name("x4571").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:56:32") } // FltSub(x4565,x4570)
    val x4572_x4539 = withCtrl(x4573) { WriteMem(x4539, x4571).name("x4572_x4539").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:56:19") } // RegWrite(x4539,x4571,x4564)
    val x4584 = withCtrl(x4585) { UnitController(style=SeqPipe, level=InnerControl).name("x4584").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:55:18") } // UnitPipe(List(b2273, b2199, b2191),Block(x4583))
    val x4574 = withCtrl(x4584) { OpDef(op=BitAnd, inputs=List(b2273, b2199)).name("x4574").srcCtx("UnrollingBase.scala:28:66") } // And(b2273,b2199)
    val x4575 = withCtrl(x4584) { OpDef(op=BitAnd, inputs=List(x4574, b2191)).name("x4575").srcCtx("UnrollingBase.scala:28:66") } // And(x4574,b2191)
    val x4576 = withCtrl(x4584) { LoadBanks(List(x4407_d0_b0), List(b2269)).name("x4576").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:56:27") } // SRAMLoad(x4407,ArrayBuffer(Const(512)),List(b2269),Const(0),x4575)
    val x4577 = withCtrl(x4584) { ReadMem(x4467_d0).name("x4577").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:56:45") } // RegRead(x4467)
    val x4578 = withCtrl(x4584) { OpDef(op=FltNeg, inputs=List(x4577)).name("x4578").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:21:47") } // FltNeg(x4577)
    val x4579 = withCtrl(x4584) { OpDef(op=FltExp, inputs=List(x4578)).name("x4579").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:21:46") } // FltExp(x4578)
    val x4580 = withCtrl(x4584) { OpDef(op=FltAdd, inputs=List(x4579, Const(1.0))).name("x4580").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:21:51") } // FltAdd(x4579,Const(1))
    val x4581 = withCtrl(x4584) { OpDef(op=FltDiv, inputs=List(Const(1.0), x4580)).name("x4581").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:21:41") } // FltDiv(Const(1),x4580)
    val x4582 = withCtrl(x4584) { OpDef(op=FltSub, inputs=List(x4576, x4581)).name("x4582").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:56:32") } // FltSub(x4576,x4581)
    val x4583_x4540 = withCtrl(x4584) { WriteMem(x4540, x4582).name("x4583_x4540").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:56:19") } // RegWrite(x4540,x4582,x4575)
    val x4586_d0_b0 = withCtrl(x4675) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x4586_d0_b0").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:58:34:gradRow") } // x4586 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x4586_d0_b0) = false
    bufferDepthOf(x4586_d0_b0) = 2
    staticDimsOf(x4586_d0_b0) = List(128)
    val x4587_d0_b0 = withCtrl(x4675) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x4587_d0_b0").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:58:34:gradRow") } // x4587 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x4587_d0_b0) = false
    bufferDepthOf(x4587_d0_b0) = 2
    staticDimsOf(x4587_d0_b0) = List(128)
    val x4588_d0_b0 = withCtrl(x4675) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x4588_d0_b0").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:58:34:gradRow") } // x4588 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x4588_d0_b0) = false
    bufferDepthOf(x4588_d0_b0) = 2
    staticDimsOf(x4588_d0_b0) = List(128)
    val x4589_d0_b0 = withCtrl(x4675) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x4589_d0_b0").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:58:34:gradRow") } // x4589 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x4589_d0_b0) = false
    bufferDepthOf(x4589_d0_b0) = 2
    staticDimsOf(x4589_d0_b0) = List(128)
    val x4634 = withCtrl(x4675) { UnitController(style=ForkJoin, level=OuterControl).name("x4634").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b2199, b2191),Block(Const(())))
    val x4590 = withCtrl(x4634) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x4590").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:59:28") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x4591 = withCtrl(x4634) { CounterChain(List(x4590)).name("x4591").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:59:36") } // CounterChainNew(List(x4590))
    val x4600 = withCtrl(x4634) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4591).name("x4600").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:59:36") } // UnrolledForeach(List(b2270, b2199, b2191),x4591,Block(Const(())),List(List(b2416)),List(List(b2417)))
    val b2416 = withCtrl(x4600) { CounterIter(x4590, None).name("b2416") } // b2416
    val b2417 = withCtrl(x4600) { Const(true).name("b2417") } // b2417
    val x4592 = withCtrl(x4600) { OpDef(op=BitAnd, inputs=List(b2417, b2270)).name("x4592").srcCtx("UnrollingBase.scala:28:66") } // And(b2417,b2270)
    val x4593 = withCtrl(x4600) { OpDef(op=BitAnd, inputs=List(b2199, b2191)).name("x4593").srcCtx("UnrollingBase.scala:28:66") } // And(b2199,b2191)
    val x4594 = withCtrl(x4600) { OpDef(op=BitAnd, inputs=List(x4592, x4593)).name("x4594").srcCtx("UnrollingBase.scala:28:66") } // And(x4592,x4593)
    val x4595 = withCtrl(x4600) { LoadBanks(List(x4406_d0_b0), List(b2266, b2416)).name("x4595").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:59:61") } // ParSRAMLoad(x4406,List(List(b2266, b2416)),List(x4594))
    val x4596 = withCtrl(x4600) { x4595 } // VectorApply(x4595,0)
    val x4597 = withCtrl(x4600) { ReadMem(x4537).name("x4597").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:59:75") } // RegRead(x4537)
    val x4598 = withCtrl(x4600) { OpDef(op=FltMul, inputs=List(x4596, x4597)).name("x4598").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:59:69") } // FltMul(x4596,x4597)
    val x4599 = withCtrl(x4600) { StoreBanks(List(List(x4586_d0_b0)), List(b2416), x4598).name("x4599").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:59:54") } // ParSRAMStore(x4586,List(List(b2416)),List(x4598),List(x4594))
    val x4601 = withCtrl(x4634) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x4601").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:59:28") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x4602 = withCtrl(x4634) { CounterChain(List(x4601)).name("x4602").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:59:36") } // CounterChainNew(List(x4601))
    val x4611 = withCtrl(x4634) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4602).name("x4611").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:59:36") } // UnrolledForeach(List(b2271, b2199, b2191),x4602,Block(Const(())),List(List(b2427)),List(List(b2428)))
    val b2427 = withCtrl(x4611) { CounterIter(x4601, None).name("b2427") } // b2427
    val b2428 = withCtrl(x4611) { Const(true).name("b2428") } // b2428
    val x4603 = withCtrl(x4611) { OpDef(op=BitAnd, inputs=List(b2428, b2271)).name("x4603").srcCtx("UnrollingBase.scala:28:66") } // And(b2428,b2271)
    val x4604 = withCtrl(x4611) { OpDef(op=BitAnd, inputs=List(b2199, b2191)).name("x4604").srcCtx("UnrollingBase.scala:28:66") } // And(b2199,b2191)
    val x4605 = withCtrl(x4611) { OpDef(op=BitAnd, inputs=List(x4603, x4604)).name("x4605").srcCtx("UnrollingBase.scala:28:66") } // And(x4603,x4604)
    val x4606 = withCtrl(x4611) { LoadBanks(List(x4406_d0_b1), List(b2267, b2427)).name("x4606").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:59:61") } // ParSRAMLoad(x4406,List(List(b2267, b2427)),List(x4605))
    val x4607 = withCtrl(x4611) { x4606 } // VectorApply(x4606,0)
    val x4608 = withCtrl(x4611) { ReadMem(x4538).name("x4608").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:59:75") } // RegRead(x4538)
    val x4609 = withCtrl(x4611) { OpDef(op=FltMul, inputs=List(x4607, x4608)).name("x4609").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:59:69") } // FltMul(x4607,x4608)
    val x4610 = withCtrl(x4611) { StoreBanks(List(List(x4587_d0_b0)), List(b2427), x4609).name("x4610").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:59:54") } // ParSRAMStore(x4587,List(List(b2427)),List(x4609),List(x4605))
    val x4612 = withCtrl(x4634) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x4612").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:59:28") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x4613 = withCtrl(x4634) { CounterChain(List(x4612)).name("x4613").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:59:36") } // CounterChainNew(List(x4612))
    val x4622 = withCtrl(x4634) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4613).name("x4622").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:59:36") } // UnrolledForeach(List(b2272, b2199, b2191),x4613,Block(Const(())),List(List(b2438)),List(List(b2439)))
    val b2438 = withCtrl(x4622) { CounterIter(x4612, None).name("b2438") } // b2438
    val b2439 = withCtrl(x4622) { Const(true).name("b2439") } // b2439
    val x4614 = withCtrl(x4622) { OpDef(op=BitAnd, inputs=List(b2439, b2272)).name("x4614").srcCtx("UnrollingBase.scala:28:66") } // And(b2439,b2272)
    val x4615 = withCtrl(x4622) { OpDef(op=BitAnd, inputs=List(b2199, b2191)).name("x4615").srcCtx("UnrollingBase.scala:28:66") } // And(b2199,b2191)
    val x4616 = withCtrl(x4622) { OpDef(op=BitAnd, inputs=List(x4614, x4615)).name("x4616").srcCtx("UnrollingBase.scala:28:66") } // And(x4614,x4615)
    val x4617 = withCtrl(x4622) { LoadBanks(List(x4406_d0_b2), List(b2268, b2438)).name("x4617").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:59:61") } // ParSRAMLoad(x4406,List(List(b2268, b2438)),List(x4616))
    val x4618 = withCtrl(x4622) { x4617 } // VectorApply(x4617,0)
    val x4619 = withCtrl(x4622) { ReadMem(x4539).name("x4619").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:59:75") } // RegRead(x4539)
    val x4620 = withCtrl(x4622) { OpDef(op=FltMul, inputs=List(x4618, x4619)).name("x4620").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:59:69") } // FltMul(x4618,x4619)
    val x4621 = withCtrl(x4622) { StoreBanks(List(List(x4588_d0_b0)), List(b2438), x4620).name("x4621").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:59:54") } // ParSRAMStore(x4588,List(List(b2438)),List(x4620),List(x4616))
    val x4623 = withCtrl(x4634) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x4623").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:59:28") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x4624 = withCtrl(x4634) { CounterChain(List(x4623)).name("x4624").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:59:36") } // CounterChainNew(List(x4623))
    val x4633 = withCtrl(x4634) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4624).name("x4633").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:59:36") } // UnrolledForeach(List(b2273, b2199, b2191),x4624,Block(Const(())),List(List(b2449)),List(List(b2450)))
    val b2449 = withCtrl(x4633) { CounterIter(x4623, None).name("b2449") } // b2449
    val b2450 = withCtrl(x4633) { Const(true).name("b2450") } // b2450
    val x4625 = withCtrl(x4633) { OpDef(op=BitAnd, inputs=List(b2450, b2273)).name("x4625").srcCtx("UnrollingBase.scala:28:66") } // And(b2450,b2273)
    val x4626 = withCtrl(x4633) { OpDef(op=BitAnd, inputs=List(b2199, b2191)).name("x4626").srcCtx("UnrollingBase.scala:28:66") } // And(b2199,b2191)
    val x4627 = withCtrl(x4633) { OpDef(op=BitAnd, inputs=List(x4625, x4626)).name("x4627").srcCtx("UnrollingBase.scala:28:66") } // And(x4625,x4626)
    val x4628 = withCtrl(x4633) { LoadBanks(List(x4406_d0_b3), List(b2269, b2449)).name("x4628").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:59:61") } // ParSRAMLoad(x4406,List(List(b2269, b2449)),List(x4627))
    val x4629 = withCtrl(x4633) { x4628 } // VectorApply(x4628,0)
    val x4630 = withCtrl(x4633) { ReadMem(x4540).name("x4630").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:59:75") } // RegRead(x4540)
    val x4631 = withCtrl(x4633) { OpDef(op=FltMul, inputs=List(x4629, x4630)).name("x4631").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:59:69") } // FltMul(x4629,x4630)
    val x4632 = withCtrl(x4633) { StoreBanks(List(List(x4589_d0_b0)), List(b2449), x4631).name("x4632").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:59:54") } // ParSRAMStore(x4589,List(List(b2449)),List(x4631),List(x4627))
    val x4635 = withCtrl(x4675) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x4635").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:61:13") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x4636 = withCtrl(x4675) { CounterChain(List(x4635)).name("x4636").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:61:13") } // CounterChainNew(ArrayBuffer(x4635))
    val x4674 = withCtrl(x4675) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4636).name("x4674").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:61:13") } // UnrolledForeach(List(),x4636,Block(Const(())),ArrayBuffer(List(b2461)),ArrayBuffer(List(b2462)))
    val b2461 = withCtrl(x4674) { CounterIter(x4635, None).name("b2461") } // b2461
    val b2462 = withCtrl(x4674) { Const(true).name("b2462") } // b2462
    val x4637 = withCtrl(x4674) { OpDef(op=BitAnd, inputs=List(b2462, b2199)).name("x4637").srcCtx("UnrollingBase.scala:28:66") } // And(b2462,b2199)
    val x4638 = withCtrl(x4674) { OpDef(op=BitAnd, inputs=List(x4637, b2191)).name("x4638").srcCtx("UnrollingBase.scala:28:66") } // And(x4637,b2191)
    val x4639 = withCtrl(x4674) { LoadBanks(List(x4586_d0_b0), List(b2461)).name("x4639").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:61:13") } // ParSRAMLoad(x4586,List(ArrayBuffer(b2461)),List(x4638))
    val x4640 = withCtrl(x4674) { x4639 } // VectorApply(x4639,0)
    val x4641 = withCtrl(x4674) { LoadBanks(List(x4587_d0_b0), List(b2461)).name("x4641").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:61:13") } // ParSRAMLoad(x4587,List(ArrayBuffer(b2461)),List(x4638))
    val x4642 = withCtrl(x4674) { x4641 } // VectorApply(x4641,0)
    val x4643 = withCtrl(x4674) { LoadBanks(List(x4588_d0_b0), List(b2461)).name("x4643").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:61:13") } // ParSRAMLoad(x4588,List(ArrayBuffer(b2461)),List(x4638))
    val x4644 = withCtrl(x4674) { x4643 } // VectorApply(x4643,0)
    val x4645 = withCtrl(x4674) { LoadBanks(List(x4589_d0_b0), List(b2461)).name("x4645").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:61:13") } // ParSRAMLoad(x4589,List(ArrayBuffer(b2461)),List(x4638))
    val x4646 = withCtrl(x4674) { x4645 } // VectorApply(x4645,0)
    val x4647 = withCtrl(x4674) { LoadBanks(List(x4461_d1_b0), List(b2461)).name("x4647").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:61:13") } // ParSRAMLoad(x4461,List(ArrayBuffer(b2461)),List(x4638))
    val x4648 = withCtrl(x4674) { x4647 } // VectorApply(x4647,0)
    val x4649 = withCtrl(x4674) { OpDef(op=BitAnd, inputs=List(b2270, b2199)).name("x4649").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:61:13") } // And(b2270,b2199)
    val x4650 = withCtrl(x4674) { OpDef(op=BitAnd, inputs=List(x4649, b2191)).name("x4650").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:61:13") } // And(x4649,b2191)
    val x4651 = withCtrl(x4674) { OpDef(op=BitAnd, inputs=List(b2271, b2199)).name("x4651").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:61:13") } // And(b2271,b2199)
    val x4652 = withCtrl(x4674) { OpDef(op=BitAnd, inputs=List(x4651, b2191)).name("x4652").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:61:13") } // And(x4651,b2191)
    val x4653 = withCtrl(x4674) { OpDef(op=BitAnd, inputs=List(b2272, b2199)).name("x4653").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:61:13") } // And(b2272,b2199)
    val x4654 = withCtrl(x4674) { OpDef(op=BitAnd, inputs=List(x4653, b2191)).name("x4654").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:61:13") } // And(x4653,b2191)
    val x4655 = withCtrl(x4674) { OpDef(op=BitAnd, inputs=List(b2273, b2199)).name("x4655").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:61:13") } // And(b2273,b2199)
    val x4656 = withCtrl(x4674) { OpDef(op=BitAnd, inputs=List(x4655, b2191)).name("x4656").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:61:13") } // And(x4655,b2191)
    val x4657 = withCtrl(x4674) { OpDef(op=BitAnd, inputs=List(x4650, x4638)).name("x4657").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:61:13") } // And(x4650,x4638)
    val x4658 = withCtrl(x4674) { OpDef(op=BitAnd, inputs=List(x4652, x4638)).name("x4658").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:61:13") } // And(x4652,x4638)
    val x4659 = withCtrl(x4674) { OpDef(op=BitAnd, inputs=List(x4654, x4638)).name("x4659").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:61:13") } // And(x4654,x4638)
    val x4660 = withCtrl(x4674) { OpDef(op=BitAnd, inputs=List(x4656, x4638)).name("x4660").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:61:13") } // And(x4656,x4638)
    val x4661 = withCtrl(x4674) { OpDef(op=FltAdd, inputs=List(x4640, x4642)).name("x4661").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:61:17") } // FltAdd(x4640,x4642)
    val x4662 = withCtrl(x4674) { OpDef(op=MuxOp, inputs=List(x4658, x4661, x4640)).name("x4662").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:61:13") } // Mux(x4658,x4661,x4640)
    val x4663 = withCtrl(x4674) { OpDef(op=BitOr, inputs=List(x4657, x4658)).name("x4663").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:61:13") } // Or(x4657,x4658)
    val x4664 = withCtrl(x4674) { OpDef(op=FltAdd, inputs=List(x4644, x4646)).name("x4664").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:61:17") } // FltAdd(x4644,x4646)
    val x4665 = withCtrl(x4674) { OpDef(op=MuxOp, inputs=List(x4660, x4664, x4644)).name("x4665").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:61:13") } // Mux(x4660,x4664,x4644)
    val x4666 = withCtrl(x4674) { OpDef(op=BitOr, inputs=List(x4659, x4660)).name("x4666").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:61:13") } // Or(x4659,x4660)
    val x4667 = withCtrl(x4674) { OpDef(op=FltAdd, inputs=List(x4662, x4665)).name("x4667").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:61:17") } // FltAdd(x4662,x4665)
    val x4668 = withCtrl(x4674) { OpDef(op=MuxOp, inputs=List(x4666, x4667, x4662)).name("x4668").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:61:13") } // Mux(x4666,x4667,x4662)
    val x4669 = withCtrl(x4674) { OpDef(op=BitOr, inputs=List(x4663, x4666)).name("x4669").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:61:13") } // Or(x4663,x4666)
    val x4670 = withCtrl(x4674) { OpDef(op=FixEql, inputs=List(b2266, Const(0))).name("x4670").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:61:13") } // FixEql(b2266,Const(0))
    val x4671 = withCtrl(x4674) { OpDef(op=FltAdd, inputs=List(x4668, x4648)).name("x4671").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:61:17") } // FltAdd(x4668,x4648)
    val x4672 = withCtrl(x4674) { OpDef(op=MuxOp, inputs=List(x4670, x4668, x4671)).name("x4672").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:61:13") } // Mux(x4670,x4668,x4671)
    val x4673 = withCtrl(x4674) { StoreBanks(List(List(x4461_d0_b0), List(x4461_d1_b0)), List(b2461), x4672).name("x4673").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:61:13") } // ParSRAMStore(x4461,List(ArrayBuffer(b2461)),List(x4672),List(x4638))
    antiDepsOf(x4673)=List(x4647)
    val x4676 = withCtrl(x4690) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x4676").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:62:11") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x4677 = withCtrl(x4690) { CounterChain(List(x4676)).name("x4677").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:62:11") } // CounterChainNew(ArrayBuffer(x4676))
    val x4689 = withCtrl(x4690) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4677).name("x4689").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:62:11") } // UnrolledForeach(List(),x4677,Block(Const(())),ArrayBuffer(List(b2502)),ArrayBuffer(List(b2503)))
    def split1 = {
    val b2502 = withCtrl(x4689) { CounterIter(x4676, None).name("b2502") } // b2502
    val b2503 = withCtrl(x4689) { Const(true).name("b2503") } // b2503
    val x4678 = withCtrl(x4689) { OpDef(op=BitAnd, inputs=List(b2503, b2191)).name("x4678").srcCtx("UnrollingBase.scala:28:66") } // And(b2503,b2191)
    val x4679 = withCtrl(x4689) { LoadBanks(List(x4461_d0_b0), List(b2502)).name("x4679").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:62:11") } // ParSRAMLoad(x4461,List(ArrayBuffer(b2502)),List(x4678))
    val x4680 = withCtrl(x4689) { x4679 } // VectorApply(x4679,0)
    val x4681 = withCtrl(x4689) { LoadBanks(List(x4402_d1_b0), List(b2502)).name("x4681").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:62:11") } // ParSRAMLoad(x4402,List(ArrayBuffer(b2502)),List(x4678))
    val x4682 = withCtrl(x4689) { x4681 } // VectorApply(x4681,0)
    val x4683 = withCtrl(x4689) { OpDef(op=BitAnd, inputs=List(b2199, b2191)).name("x4683").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:62:11") } // And(b2199,b2191)
    val x4684 = withCtrl(x4689) { OpDef(op=BitAnd, inputs=List(x4683, x4678)).name("x4684").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:62:11") } // And(x4683,x4678)
    val x4685 = withCtrl(x4689) { OpDef(op=FixEql, inputs=List(b2198, Const(0))).name("x4685").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:62:11") } // FixEql(b2198,Const(0))
    val x4686 = withCtrl(x4689) { OpDef(op=FltAdd, inputs=List(x4680, x4682)).name("x4686").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:62:15") } // FltAdd(x4680,x4682)
    val x4687 = withCtrl(x4689) { OpDef(op=MuxOp, inputs=List(x4685, x4680, x4686)).name("x4687").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:62:11") } // Mux(x4685,x4680,x4686)
    val x4688 = withCtrl(x4689) { StoreBanks(List(List(x4402_d0_b0), List(x4402_d1_b0)), List(b2502), x4687).name("x4688").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:62:11") } // ParSRAMStore(x4402,List(ArrayBuffer(b2502)),List(x4687),List(x4678))
    antiDepsOf(x4688)=List(x4681)
    val x4691 = withCtrl(x4702) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x4691").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:64:24") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x4692 = withCtrl(x4702) { CounterChain(List(x4691)).name("x4692").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:64:32") } // CounterChainNew(List(x4691))
    val x4701 = withCtrl(x4702) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4692).name("x4701").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:64:32") } // UnrolledForeach(List(b2191),x4692,Block(Const(())),List(List(b2519)),List(List(b2520)))
    val b2519 = withCtrl(x4701) { CounterIter(x4691, None).name("b2519") } // b2519
    val b2520 = withCtrl(x4701) { Const(true).name("b2520") } // b2520
    val x4693 = withCtrl(x4701) { OpDef(op=BitAnd, inputs=List(b2520, b2191)).name("x4693").srcCtx("UnrollingBase.scala:28:66") } // And(b2520,b2191)
    val x4694 = withCtrl(x4701) { LoadBanks(List(x4380_d1_b0), List(b2519)).name("x4694").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:64:57") } // ParSRAMLoad(x4380,List(List(b2519)),List(x4693))
    val x4695 = withCtrl(x4701) { x4694 } // VectorApply(x4694,0)
    val x4696 = withCtrl(x4701) { LoadBanks(List(x4402_d0_b0), List(b2519)).name("x4696").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:64:67") } // ParSRAMLoad(x4402,List(List(b2519)),List(x4693))
    val x4697 = withCtrl(x4701) { x4696 } // VectorApply(x4696,0)
    val x4698 = withCtrl(x4701) { OpDef(op=FltMul, inputs=List(x4697, Const(1.0E-7))).name("x4698").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:64:71") } // FltMul(x4697,Const(0.0000001000000011686097423080354928970337))
    val x4699 = withCtrl(x4701) { OpDef(op=FltAdd, inputs=List(x4695, x4698)).name("x4699").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:64:61") } // FltAdd(x4695,x4698)
    val x4700 = withCtrl(x4701) { StoreBanks(List(List(x4380_d0_b0), List(x4380_d5_b0), List(x4380_d1_b0), List(x4380_d2_b0), List(x4380_d3_b0), List(x4380_d4_b0)), List(b2519), x4699).name("x4700").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:64:49") } // ParSRAMStore(x4380,List(List(b2519)),List(x4699),List(x4693))
    antiDepsOf(x4700)=List(x4694)
    val x4724 = withCtrl(x4725) { UnitController(style=StreamPipe, level=OuterControl).name("x4724").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:68:26") } // UnitPipe(List(Const(true)),Block(Const(())))
    val b4807 = withCtrl(x4724) { StreamOut(field="offset").name("b4807").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:68:26") } // x4703 = StreamOutNew(BurstCmdBus)
    isAccum(b4807) = false
    bufferDepthOf(b4807) = 1
    val b4808 = withCtrl(x4724) { StreamOut(field="size").name("b4808").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:68:26") } // x4703 = StreamOutNew(BurstCmdBus)
    isAccum(b4808) = false
    bufferDepthOf(b4808) = 1
    val x4704 = withCtrl(x4724) { StreamOut(field="data").name("x4704").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:68:26") } // x4704 = StreamOutNew(BurstFullDataBus())
    isAccum(x4704) = false
    bufferDepthOf(x4704) = 1
    val x4705 = withCtrl(x4724) { StreamIn(field="ack").name("x4705").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:68:26") } // x4705 = StreamInNew(BurstAckBus)
    isAccum(x4705) = false
    bufferDepthOf(x4705) = 1
    val x4713 = withCtrl(x4724) { UnitController(style=SeqPipe, level=InnerControl).name("x4713").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:68:26") } // UnitPipe(List(Const(true)),Block(x4712))
    val x4706 = withCtrl(x4713) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x4707 = withCtrl(x4713) { OpDef(op=FixSla, inputs=List(x4706, Const(2))).name("x4707").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:68:26") } // FixLsh(x4706,Const(2))
    val x4708 = withCtrl(x4713) { x4707 } // FixConvert(x4707,TRUE,_64,_0)
    val x4709 = withCtrl(x4713) { DramAddress(x4374).name("x4709").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:68:26") } // GetDRAMAddress(x4374)
    val x4711_x4710 = withCtrl(x4713) { OpDef(op=FixAdd, inputs=List(x4708, x4709)).name("x4711_x4710").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:68:26") } // FixAdd(x4708,x4709)
    // x4711 = SimpleStruct(ArrayBuffer((offset,x4710), (size,Const(512)), (isLoad,Const(false))))
    val x4712_b4809_b4807 = withCtrl(x4713) { WriteMem(b4807, x4711_x4710).name("x4712_b4809_b4807").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:68:26") } // StreamWrite(x4703,x4711,Const(true))
    val x4712_b4810_b4808 = withCtrl(x4713) { WriteMem(b4808, Const(512)).name("x4712_b4810_b4808").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:68:26") } // StreamWrite(x4703,x4711,Const(true))
    val x4714 = withCtrl(x4724) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x4714").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:68:26") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x4715 = withCtrl(x4724) { CounterChain(List(x4714)).name("x4715").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:68:26") } // CounterChainNew(List(x4714))
    val x4720 = withCtrl(x4724) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4715).name("x4720").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:68:26") } // UnrolledForeach(List(Const(true)),x4715,Block(Const(())),List(List(b2544)),List(List(b2545)))
    val b2544 = withCtrl(x4720) { CounterIter(x4714, None).name("b2544") } // b2544
    val b2545 = withCtrl(x4720) { Const(true).name("b2545") } // b2545
    val x4716 = withCtrl(x4720) { LoadBanks(List(x4380_d0_b0), List(b2544)).name("x4716").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:68:26") } // ParSRAMLoad(x4380,List(List(b2544)),List(b2545))
    val x4718_x4717 = withCtrl(x4720) { x4716 } // VectorApply(x4716,0)
    // x4718 = SimpleStruct(ArrayBuffer((_1,x4717), (_2,Const(true))))
    val x4719_x4719_x4704 = withCtrl(x4720) { WriteMem(x4704, x4718_x4717).name("x4719_x4719_x4704").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:68:26") } // ParStreamWrite(x4704,List(x4718),List(b2545))
    val x4721 = withCtrl(x4724) { FringeDenseStore(dram=List(x4374), cmdStream=List(b4807, b4808), dataStream=List(x4704), ackStream=List(x4705)).name("x4721").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:68:26") } // FringeDenseStore(x4374,x4703,x4704,x4705)
    val x4723 = withCtrl(x4724) { UnitController(style=SeqPipe, level=InnerControl).name("x4723").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:68:26") } // UnitPipe(List(Const(true)),Block(Const(())))
    val x4722_x4722 = withCtrl(x4723) { ReadMem(x4705).name("x4722_x4722").srcCtx("LogReg__iters_4_D_128_N_8192_ts_512_op_1_mp_4.scala:68:26") } // StreamRead(x4705,Const(true))
    }; split1
    
  }
}
