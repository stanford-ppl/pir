import pir._
import pir.node._
import arch._
import prism.enums._

object GEMM_Blocked extends PIRApp {
  def main(implicit design:PIRDesign) = {
    import design.pirmeta._
    val x4322_d0 = withCtrl(design.top.topController) { ArgIn(init=0).name("x4322_d0").srcCtx("GEMM_Blocked.scala:24:20") } // ArgInNew(Const(0))
    isAccum(x4322_d0) = false
    bufferDepthOf(x4322_d0) = 1
    boundOf(x4322_d0) = 128
    val x4322_d1 = withCtrl(design.top.topController) { ArgIn(init=0).name("x4322_d1").srcCtx("GEMM_Blocked.scala:24:20") } // ArgInNew(Const(0))
    isAccum(x4322_d1) = false
    bufferDepthOf(x4322_d1) = 1
    boundOf(x4322_d1) = 128
    val x4322_d2 = withCtrl(design.top.topController) { ArgIn(init=0).name("x4322_d2").srcCtx("GEMM_Blocked.scala:24:20") } // ArgInNew(Const(0))
    isAccum(x4322_d2) = false
    bufferDepthOf(x4322_d2) = 1
    boundOf(x4322_d2) = 128
    val x4322_d3 = withCtrl(design.top.topController) { ArgIn(init=0).name("x4322_d3").srcCtx("GEMM_Blocked.scala:24:20") } // ArgInNew(Const(0))
    isAccum(x4322_d3) = false
    bufferDepthOf(x4322_d3) = 1
    boundOf(x4322_d3) = 128
    val x4322_d4 = withCtrl(design.top.topController) { ArgIn(init=0).name("x4322_d4").srcCtx("GEMM_Blocked.scala:24:20") } // ArgInNew(Const(0))
    isAccum(x4322_d4) = false
    bufferDepthOf(x4322_d4) = 1
    boundOf(x4322_d4) = 128
    val x4323 = withCtrl(design.top.topController) { ArgIn(init=0).name("x4323").srcCtx("GEMM_Blocked.scala:25:21") } // ArgInNew(Const(0))
    isAccum(x4323) = false
    bufferDepthOf(x4323) = 1
    boundOf(x4323) = 128
    val x4326 = withCtrl(design.top.topController) { ReadMem(x4322_d0).name("x4326").srcCtx("GEMM_Blocked.scala:28:31") } // RegRead(x4322)
    val x4327 = withCtrl(design.top.topController) { ReadMem(x4323).name("x4327").srcCtx("GEMM_Blocked.scala:28:26") } // RegRead(x4323)
    val x4328 = withCtrl(design.top.topController) { DRAM(dims=List(x4327, x4326)).name("x4328").srcCtx("GEMM_Blocked.scala:28:25") } // x4328 = DRAMNew(ArrayBuffer(x4327, x4326),Const(0))
    val x4329 = withCtrl(design.top.topController) { ReadMem(x4322_d0).name("x4329").srcCtx("GEMM_Blocked.scala:29:30") } // RegRead(x4322)
    val x4330 = withCtrl(design.top.topController) { ReadMem(x4322_d0).name("x4330").srcCtx("GEMM_Blocked.scala:29:26") } // RegRead(x4322)
    val x4331 = withCtrl(design.top.topController) { DRAM(dims=List(x4330, x4329)).name("x4331").srcCtx("GEMM_Blocked.scala:29:25") } // x4331 = DRAMNew(ArrayBuffer(x4330, x4329),Const(0))
    val x4332 = withCtrl(design.top.topController) { ReadMem(x4322_d0).name("x4332").srcCtx("GEMM_Blocked.scala:30:31") } // RegRead(x4322)
    val x4333 = withCtrl(design.top.topController) { ReadMem(x4323).name("x4333").srcCtx("GEMM_Blocked.scala:30:26") } // RegRead(x4323)
    val x4334 = withCtrl(design.top.topController) { DRAM(dims=List(x4333, x4332)).name("x4334").srcCtx("GEMM_Blocked.scala:30:25") } // x4334 = DRAMNew(ArrayBuffer(x4333, x4332),Const(0))
    val x4767 = withCtrl(design.top.topController) { UnitController(style=SeqPipe, level=OuterControl).name("x4767").srcCtx("GEMM_Blocked.scala:36:10") } // Hwblock(Block(Const(())),false)
    val x4338 = withCtrl(x4767) { ReadMem(x4323).name("x4338").srcCtx("GEMM_Blocked.scala:37:15") } // RegRead(x4323)
    val x4339 = withCtrl(x4767) { Counter(min=Const(0), max=x4338, step=Const(64), par=1).name("x4339").srcCtx("GEMM_Blocked.scala:37:27") } // CounterNew(Const(0),x4338,Const(64),Const(1))
    val x4340 = withCtrl(x4767) { CounterChain(List(x4339)).name("x4340").srcCtx("GEMM_Blocked.scala:37:40") } // CounterChainNew(List(x4339))
    val x4766 = withCtrl(x4767) { LoopController(style=MetaPipe, level=OuterControl, cchain=x4340).name("x4766").srcCtx("GEMM_Blocked.scala:37:40") } // UnrolledForeach(List(Const(true)),x4340,Block(Const(())),List(List(b1827)),List(List(b1828)))
    val b1827 = withCtrl(x4766) { CounterIter(x4339, Some(0)).name("b1827") } // b1827
    val b1828 = withCtrl(x4766) { Const(true).name("b1828") } // b1828
    val x4341 = withCtrl(x4766) { ReadMem(x4322_d0).name("x4341").srcCtx("GEMM_Blocked.scala:38:17") } // RegRead(x4322)
    val x4342 = withCtrl(x4766) { Counter(min=Const(0), max=x4341, step=Const(64), par=1).name("x4342").srcCtx("GEMM_Blocked.scala:38:27") } // CounterNew(Const(0),x4341,Const(64),Const(1))
    val x4343 = withCtrl(x4766) { CounterChain(List(x4342)).name("x4343").srcCtx("GEMM_Blocked.scala:38:40") } // CounterChainNew(List(x4342))
    val x4765 = withCtrl(x4766) { LoopController(style=MetaPipe, level=OuterControl, cchain=x4343).name("x4765").srcCtx("GEMM_Blocked.scala:38:40") } // UnrolledForeach(List(b1828),x4343,Block(Const(())),List(List(b1832)),List(List(b1833)))
    val b1832 = withCtrl(x4765) { CounterIter(x4342, Some(0)).name("b1832") } // b1832
    val b1833 = withCtrl(x4765) { Const(true).name("b1833") } // b1833
    val x4344_d0_b0 = withCtrl(x4765) { SRAM(size=4096, banking=Strided(banks=16, stride=1)).name("x4344_d0_b0").srcCtx("GEMM_Blocked.scala:39:30") } // x4344 = SRAMNew(ArrayBuffer(Const(64), Const(64)))
    isAccum(x4344_d0_b0) = false
    bufferDepthOf(x4344_d0_b0) = 3
    staticDimsOf(x4344_d0_b0) = List(64, 64)
    val x4344_d1_b0 = withCtrl(x4765) { SRAM(size=4096, banking=Strided(banks=16, stride=1)).name("x4344_d1_b0").srcCtx("GEMM_Blocked.scala:39:30") } // x4344 = SRAMNew(ArrayBuffer(Const(64), Const(64)))
    isAccum(x4344_d1_b0) = true
    bufferDepthOf(x4344_d1_b0) = 1
    staticDimsOf(x4344_d1_b0) = List(64, 64)
    val x4345 = withCtrl(x4765) { ReadMem(x4322_d0).name("x4345").srcCtx("GEMM_Blocked.scala:40:35") } // RegRead(x4322)
    val x4346 = withCtrl(x4765) { Counter(min=Const(0), max=x4345, step=Const(64), par=1).name("x4346").srcCtx("GEMM_Blocked.scala:40:45") } // CounterNew(Const(0),x4345,Const(64),Const(1))
    val x4347 = withCtrl(x4765) { CounterChain(List(x4346)).name("x4347").srcCtx("GEMM_Blocked.scala:57:12") } // CounterChainNew(List(x4346))
    val x4725 = withCtrl(x4765) { LoopController(style=MetaPipe, level=OuterControl, cchain=x4347).name("x4725").srcCtx("GEMM_Blocked.scala:57:12") } // UnrolledReduce(List(b1833, b1828),x4347,x4344,Block((x4344) => Const(())),List(List(b1841)),List(List(b1842)))
    val b1841 = withCtrl(x4725) { CounterIter(x4346, Some(0)).name("b1841") } // b1841
    val b1842 = withCtrl(x4725) { Const(true).name("b1842") } // b1842
    val x4348_d0_b0 = withCtrl(x4725) { SRAM(size=4096, banking=Strided(banks=16, stride=1)).name("x4348_d0_b0").srcCtx("GEMM_Blocked.scala:41:40") } // x4348 = SRAMNew(ArrayBuffer(Const(64), Const(64)))
    isAccum(x4348_d0_b0) = false
    bufferDepthOf(x4348_d0_b0) = 2
    staticDimsOf(x4348_d0_b0) = List(64, 64)
    val x4349_d0_b0 = withCtrl(x4725) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x4349_d0_b0").srcCtx("GEMM_Blocked.scala:42:33") } // x4349 = SRAMNew(ArrayBuffer(Const(64), Const(64)))
    isAccum(x4349_d0_b0) = false
    bufferDepthOf(x4349_d0_b0) = 2
    staticDimsOf(x4349_d0_b0) = List(64, 64)
    val x4349_d0_b1 = withCtrl(x4725) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x4349_d0_b1").srcCtx("GEMM_Blocked.scala:42:33") } // x4349 = SRAMNew(ArrayBuffer(Const(64), Const(64)))
    isAccum(x4349_d0_b1) = false
    bufferDepthOf(x4349_d0_b1) = 2
    staticDimsOf(x4349_d0_b1) = List(64, 64)
    val x4349_d0_b2 = withCtrl(x4725) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x4349_d0_b2").srcCtx("GEMM_Blocked.scala:42:33") } // x4349 = SRAMNew(ArrayBuffer(Const(64), Const(64)))
    isAccum(x4349_d0_b2) = false
    bufferDepthOf(x4349_d0_b2) = 2
    staticDimsOf(x4349_d0_b2) = List(64, 64)
    val x4349_d0_b3 = withCtrl(x4725) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x4349_d0_b3").srcCtx("GEMM_Blocked.scala:42:33") } // x4349 = SRAMNew(ArrayBuffer(Const(64), Const(64)))
    isAccum(x4349_d0_b3) = false
    bufferDepthOf(x4349_d0_b3) = 2
    staticDimsOf(x4349_d0_b3) = List(64, 64)
    val x4349_d0_b4 = withCtrl(x4725) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x4349_d0_b4").srcCtx("GEMM_Blocked.scala:42:33") } // x4349 = SRAMNew(ArrayBuffer(Const(64), Const(64)))
    isAccum(x4349_d0_b4) = false
    bufferDepthOf(x4349_d0_b4) = 2
    staticDimsOf(x4349_d0_b4) = List(64, 64)
    val x4349_d0_b5 = withCtrl(x4725) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x4349_d0_b5").srcCtx("GEMM_Blocked.scala:42:33") } // x4349 = SRAMNew(ArrayBuffer(Const(64), Const(64)))
    isAccum(x4349_d0_b5) = false
    bufferDepthOf(x4349_d0_b5) = 2
    staticDimsOf(x4349_d0_b5) = List(64, 64)
    val x4349_d0_b6 = withCtrl(x4725) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x4349_d0_b6").srcCtx("GEMM_Blocked.scala:42:33") } // x4349 = SRAMNew(ArrayBuffer(Const(64), Const(64)))
    isAccum(x4349_d0_b6) = false
    bufferDepthOf(x4349_d0_b6) = 2
    staticDimsOf(x4349_d0_b6) = List(64, 64)
    val x4349_d0_b7 = withCtrl(x4725) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x4349_d0_b7").srcCtx("GEMM_Blocked.scala:42:33") } // x4349 = SRAMNew(ArrayBuffer(Const(64), Const(64)))
    isAccum(x4349_d0_b7) = false
    bufferDepthOf(x4349_d0_b7) = 2
    staticDimsOf(x4349_d0_b7) = List(64, 64)
    val x4352 = withCtrl(x4725) { UnitController(style=SeqPipe, level=InnerControl).name("x4352").srcCtx("GEMM_Blocked.scala:57:12") } // UnitPipe(List(b1842, b1833, b1828),Block(Const(())))
    val x4350 = withCtrl(x4352) { OpDef(op=FixAdd, inputs=List(b1841, Const(64))).name("x4350").srcCtx("GEMM_Blocked.scala:43:38") } // FixAdd(b1841,Const(64))
    val x4351 = withCtrl(x4352) { OpDef(op=FixAdd, inputs=List(b1832, Const(64))).name("x4351").srcCtx("GEMM_Blocked.scala:43:49") } // FixAdd(b1832,Const(64))
    val x4353 = withCtrl(x4725) { Counter(min=Const(0), max=Const(64), step=Const(1), par=1).name("x4353").srcCtx("GEMM_Blocked.scala:43:20") } // CounterNew(Const(0),Const(64),Const(1),Const(1))
    val x4354 = withCtrl(x4725) { CounterChain(List(x4353)).name("x4354").srcCtx("GEMM_Blocked.scala:43:20") } // CounterChainNew(List(x4353))
    val x4384 = withCtrl(x4725) { LoopController(style=StreamPipe, level=OuterControl, cchain=x4354).name("x4384").srcCtx("GEMM_Blocked.scala:43:20") } // UnrolledForeach(List(b1842, b1833, b1828),x4354,Block(Const(())),List(List(b1850)),List(List(b1851)))
    val b1850 = withCtrl(x4384) { CounterIter(x4353, Some(0)).name("b1850") } // b1850
    val b1851 = withCtrl(x4384) { Const(true).name("b1851") } // b1851
    val b4808 = withCtrl(x4384) { StreamOut(field="offset").name("b4808").srcCtx("GEMM_Blocked.scala:43:20") } // x4355 = StreamOutNew(BurstCmdBus)
    isAccum(b4808) = false
    bufferDepthOf(b4808) = 1
    val b4809 = withCtrl(x4384) { StreamOut(field="size").name("b4809").srcCtx("GEMM_Blocked.scala:43:20") } // x4355 = StreamOutNew(BurstCmdBus)
    isAccum(b4809) = false
    bufferDepthOf(b4809) = 1
    val x4356 = withCtrl(x4384) { StreamIn(field="data").name("x4356").srcCtx("GEMM_Blocked.scala:43:20") } // x4356 = StreamInNew(BurstDataBus())
    isAccum(x4356) = false
    bufferDepthOf(x4356) = 1
    val x4372 = withCtrl(x4384) { UnitController(style=SeqPipe, level=InnerControl).name("x4372").srcCtx("GEMM_Blocked.scala:43:20") } // UnitPipe(List(b1851, b1842, b1833, b1828),Block(x4371))
    val x4357 = withCtrl(x4372) { OpDef(op=FixAdd, inputs=List(b1841, b1850)).name("x4357").srcCtx("GEMM_Blocked.scala:43:20") } // FixAdd(b1841,b1850)
    val x4358 = withCtrl(x4372) { x4357 } // FixConvert(x4357,TRUE,_32,_0) (Same Type. No op)
    val x4359 = withCtrl(x4372) { ReadMem(x4322_d0).name("x4359").srcCtx("GEMM_Blocked.scala:29:30") } // RegRead(x4322)
    val x4360 = withCtrl(x4372) { OpDef(op=FixMul, inputs=List(x4358, x4359)).name("x4360").srcCtx("GEMM_Blocked.scala:43:20") } // FixMul(x4358,x4359)
    val x4361 = withCtrl(x4372) { b1832 } // FixConvert(b1832,TRUE,_32,_0) (Same Type. No op)
    val x4362 = withCtrl(x4372) { OpDef(op=FixAdd, inputs=List(x4360, x4361)).name("x4362").srcCtx("GEMM_Blocked.scala:43:20") } // FixAdd(x4360,x4361)
    val x4363 = withCtrl(x4372) { OpDef(op=FixSla, inputs=List(x4362, Const(2))).name("x4363").srcCtx("GEMM_Blocked.scala:43:20") } // FixLsh(x4362,Const(2))
    val x4364 = withCtrl(x4372) { x4363 } // FixConvert(x4363,TRUE,_64,_0)
    val x4365 = withCtrl(x4372) { DramAddress(x4331).name("x4365").srcCtx("GEMM_Blocked.scala:43:20") } // GetDRAMAddress(x4331)
    val x4367_x4366 = withCtrl(x4372) { OpDef(op=FixAdd, inputs=List(x4364, x4365)).name("x4367_x4366").srcCtx("GEMM_Blocked.scala:43:20") } // FixAdd(x4364,x4365)
    // x4367 = SimpleStruct(ArrayBuffer((offset,x4366), (size,Const(256)), (isLoad,Const(true))))
    val x4368 = withCtrl(x4372) { OpDef(op=BitAnd, inputs=List(b1851, b1842)).name("x4368").srcCtx("UnrollingBase.scala:28:66") } // And(b1851,b1842)
    val x4369 = withCtrl(x4372) { OpDef(op=BitAnd, inputs=List(b1833, b1828)).name("x4369").srcCtx("UnrollingBase.scala:28:66") } // And(b1833,b1828)
    val x4370 = withCtrl(x4372) { OpDef(op=BitAnd, inputs=List(x4368, x4369)).name("x4370").srcCtx("UnrollingBase.scala:28:66") } // And(x4368,x4369)
    val x4371_b4810_b4808 = withCtrl(x4372) { WriteMem(b4808, x4367_x4366).name("x4371_b4810_b4808").srcCtx("GEMM_Blocked.scala:43:20") } // StreamWrite(x4355,x4367,x4370)
    val x4371_b4811_b4809 = withCtrl(x4372) { WriteMem(b4809, Const(256)).name("x4371_b4811_b4809").srcCtx("GEMM_Blocked.scala:43:20") } // StreamWrite(x4355,x4367,x4370)
    val x4373 = withCtrl(x4384) { FringeDenseLoad(dram=List(x4331), cmdStream=List(b4808, b4809), dataStream=List(x4356)).name("x4373").srcCtx("GEMM_Blocked.scala:43:20") } // FringeDenseLoad(x4331,x4355,x4356)
    val x4374 = withCtrl(x4384) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x4374").srcCtx("GEMM_Blocked.scala:43:20") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x4375 = withCtrl(x4384) { CounterChain(List(x4374)).name("x4375").srcCtx("GEMM_Blocked.scala:43:20") } // CounterChainNew(List(x4374))
    val x4383 = withCtrl(x4384) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4375).name("x4383").srcCtx("GEMM_Blocked.scala:43:20") } // UnrolledForeach(List(b1851, b1842, b1833, b1828),x4375,Block(Const(())),List(List(b1873)),List(List(b1874)))
    val b1873 = withCtrl(x4383) { CounterIter(x4374, None).name("b1873") } // b1873
    val b1874 = withCtrl(x4383) { Const(true).name("b1874") } // b1874
    val x4376 = withCtrl(x4383) { OpDef(op=BitAnd, inputs=List(b1874, b1851)).name("x4376").srcCtx("UnrollingBase.scala:28:66") } // And(b1874,b1851)
    val x4377 = withCtrl(x4383) { OpDef(op=BitAnd, inputs=List(b1842, b1833)).name("x4377").srcCtx("UnrollingBase.scala:28:66") } // And(b1842,b1833)
    val x4378 = withCtrl(x4383) { OpDef(op=BitAnd, inputs=List(x4376, x4377)).name("x4378").srcCtx("UnrollingBase.scala:28:66") } // And(x4376,x4377)
    val x4379 = withCtrl(x4383) { OpDef(op=BitAnd, inputs=List(x4378, b1828)).name("x4379").srcCtx("UnrollingBase.scala:28:66") } // And(x4378,b1828)
    val x4380_x4380 = withCtrl(x4383) { ReadMem(x4356).name("x4380_x4380").srcCtx("GEMM_Blocked.scala:43:20") } // ParStreamRead(x4356,List(x4379))
    val x4381_x4381 = withCtrl(x4383) { x4380_x4380 } // VectorApply(x4380,0)
    val x4382 = withCtrl(x4383) { StoreBanks(List(List(x4349_d0_b0, x4349_d0_b1, x4349_d0_b2, x4349_d0_b3, x4349_d0_b4, x4349_d0_b5, x4349_d0_b6, x4349_d0_b7)), List(b1850, b1873), x4381_x4381).name("x4382").srcCtx("GEMM_Blocked.scala:43:20") } // ParSRAMStore(x4349,List(List(b1850, b1873)),List(x4381),List(x4379))
    val x4385 = withCtrl(x4725) { Counter(min=Const(0), max=Const(64), step=Const(1), par=1).name("x4385").srcCtx("GEMM_Blocked.scala:44:30") } // CounterNew(Const(0),Const(64),Const(1),Const(1))
    val x4386 = withCtrl(x4725) { CounterChain(List(x4385)).name("x4386").srcCtx("GEMM_Blocked.scala:44:42") } // CounterChainNew(List(x4385))
    val x4706 = withCtrl(x4725) { LoopController(style=MetaPipe, level=OuterControl, cchain=x4386).name("x4706").srcCtx("GEMM_Blocked.scala:44:42") } // UnrolledForeach(List(b1842, b1833, b1828),x4386,Block(Const(())),List(List(b1886)),List(List(b1887)))
    val b1886 = withCtrl(x4706) { CounterIter(x4385, Some(0)).name("b1886") } // b1886
    val b1887 = withCtrl(x4706) { Const(true).name("b1887") } // b1887
    val x4387_d0_b0 = withCtrl(x4706) { SRAM(size=64, banking=Strided(banks=16, stride=1)).name("x4387_d0_b0").srcCtx("GEMM_Blocked.scala:45:35") } // x4387 = SRAMNew(ArrayBuffer(Const(64)))
    isAccum(x4387_d0_b0) = false
    bufferDepthOf(x4387_d0_b0) = 2
    staticDimsOf(x4387_d0_b0) = List(64)
    val x4388 = withCtrl(x4706) { Reg(init=Some(0)).name("x4388").srcCtx("GEMM_Blocked.scala:44:42") } // x4388 = RegNew(Const(0))
    isAccum(x4388) = false
    bufferDepthOf(x4388) = 2
    val x4396 = withCtrl(x4706) { UnitController(style=SeqPipe, level=InnerControl).name("x4396").srcCtx("GEMM_Blocked.scala:44:42") } // UnitPipe(List(b1887, b1842, b1833, b1828),Block(Const(())))
    val x4389 = withCtrl(x4396) { OpDef(op=FixAdd, inputs=List(b1827, b1886)).name("x4389").srcCtx("GEMM_Blocked.scala:46:36") } // FixAdd(b1827,b1886)
    val x4390 = withCtrl(x4396) { OpDef(op=FixAdd, inputs=List(b1841, Const(64))).name("x4390").srcCtx("GEMM_Blocked.scala:46:46") } // FixAdd(b1841,Const(64))
    val x4391 = withCtrl(x4396) { OpDef(op=FixAdd, inputs=List(x4389, Const(1))).name("x4391").srcCtx("GEMM_Blocked.scala:46:33") } // FixAdd(x4389,Const(1))
    val x4392 = withCtrl(x4396) { OpDef(op=BitAnd, inputs=List(b1887, b1842)).name("x4392").srcCtx("UnrollingBase.scala:28:66") } // And(b1887,b1842)
    val x4393 = withCtrl(x4396) { OpDef(op=BitAnd, inputs=List(b1833, b1828)).name("x4393").srcCtx("UnrollingBase.scala:28:66") } // And(b1833,b1828)
    val x4394 = withCtrl(x4396) { OpDef(op=BitAnd, inputs=List(x4392, x4393)).name("x4394").srcCtx("UnrollingBase.scala:28:66") } // And(x4392,x4393)
    val x4395_x4388 = withCtrl(x4396) { WriteMem(x4388, x4389).name("x4395_x4388").srcCtx("GEMM_Blocked.scala:44:42") } // RegWrite(x4388,x4389,x4394)
    val x4397 = withCtrl(x4706) { Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x4397").srcCtx("GEMM_Blocked.scala:46:22") } // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x4398 = withCtrl(x4706) { CounterChain(List(x4397)).name("x4398").srcCtx("GEMM_Blocked.scala:46:22") } // CounterChainNew(List(x4397))
    val x4431 = withCtrl(x4706) { LoopController(style=StreamPipe, level=OuterControl, cchain=x4398).name("x4431").srcCtx("GEMM_Blocked.scala:46:22") } // UnrolledForeach(List(b1887, b1842, b1833, b1828),x4398,Block(Const(())),List(List(b1900)),List(List(b1901)))
    val b1900 = withCtrl(x4431) { CounterIter(x4397, Some(0)).name("b1900") } // b1900
    val b1901 = withCtrl(x4431) { Const(true).name("b1901") } // b1901
    val b4812 = withCtrl(x4431) { StreamOut(field="offset").name("b4812").srcCtx("GEMM_Blocked.scala:46:22") } // x4399 = StreamOutNew(BurstCmdBus)
    isAccum(b4812) = false
    bufferDepthOf(b4812) = 1
    val b4813 = withCtrl(x4431) { StreamOut(field="size").name("b4813").srcCtx("GEMM_Blocked.scala:46:22") } // x4399 = StreamOutNew(BurstCmdBus)
    isAccum(b4813) = false
    bufferDepthOf(b4813) = 1
    val x4400 = withCtrl(x4431) { StreamIn(field="data").name("x4400").srcCtx("GEMM_Blocked.scala:46:22") } // x4400 = StreamInNew(BurstDataBus())
    isAccum(x4400) = false
    bufferDepthOf(x4400) = 1
    val x4418 = withCtrl(x4431) { UnitController(style=SeqPipe, level=InnerControl).name("x4418").srcCtx("GEMM_Blocked.scala:46:22") } // UnitPipe(List(b1901, b1887, b1842, b1833, b1828),Block(x4417))
    val x4401 = withCtrl(x4418) { ReadMem(x4388).name("x4401").srcCtx("GEMM_Blocked.scala:44:42") } // RegRead(x4388)
    val x4402 = withCtrl(x4418) { OpDef(op=FixAdd, inputs=List(x4401, b1900)).name("x4402").srcCtx("GEMM_Blocked.scala:46:22") } // FixAdd(x4401,b1900)
    val x4403 = withCtrl(x4418) { x4402 } // FixConvert(x4402,TRUE,_32,_0) (Same Type. No op)
    val x4404 = withCtrl(x4418) { ReadMem(x4322_d0).name("x4404").srcCtx("GEMM_Blocked.scala:28:31") } // RegRead(x4322)
    val x4405 = withCtrl(x4418) { OpDef(op=FixMul, inputs=List(x4403, x4404)).name("x4405").srcCtx("GEMM_Blocked.scala:46:22") } // FixMul(x4403,x4404)
    val x4406 = withCtrl(x4418) { b1841 } // FixConvert(b1841,TRUE,_32,_0) (Same Type. No op)
    val x4407 = withCtrl(x4418) { OpDef(op=FixAdd, inputs=List(x4405, x4406)).name("x4407").srcCtx("GEMM_Blocked.scala:46:22") } // FixAdd(x4405,x4406)
    val x4408 = withCtrl(x4418) { OpDef(op=FixSla, inputs=List(x4407, Const(2))).name("x4408").srcCtx("GEMM_Blocked.scala:46:22") } // FixLsh(x4407,Const(2))
    val x4409 = withCtrl(x4418) { x4408 } // FixConvert(x4408,TRUE,_64,_0)
    val x4410 = withCtrl(x4418) { DramAddress(x4328).name("x4410").srcCtx("GEMM_Blocked.scala:46:22") } // GetDRAMAddress(x4328)
    val x4412_x4411 = withCtrl(x4418) { OpDef(op=FixAdd, inputs=List(x4409, x4410)).name("x4412_x4411").srcCtx("GEMM_Blocked.scala:46:22") } // FixAdd(x4409,x4410)
    // x4412 = SimpleStruct(ArrayBuffer((offset,x4411), (size,Const(256)), (isLoad,Const(true))))
    val x4413 = withCtrl(x4418) { OpDef(op=BitAnd, inputs=List(b1901, b1887)).name("x4413").srcCtx("UnrollingBase.scala:28:66") } // And(b1901,b1887)
    val x4414 = withCtrl(x4418) { OpDef(op=BitAnd, inputs=List(b1842, b1833)).name("x4414").srcCtx("UnrollingBase.scala:28:66") } // And(b1842,b1833)
    val x4415 = withCtrl(x4418) { OpDef(op=BitAnd, inputs=List(x4413, x4414)).name("x4415").srcCtx("UnrollingBase.scala:28:66") } // And(x4413,x4414)
    val x4416 = withCtrl(x4418) { OpDef(op=BitAnd, inputs=List(x4415, b1828)).name("x4416").srcCtx("UnrollingBase.scala:28:66") } // And(x4415,b1828)
    val x4417_b4814_b4812 = withCtrl(x4418) { WriteMem(b4812, x4412_x4411).name("x4417_b4814_b4812").srcCtx("GEMM_Blocked.scala:46:22") } // StreamWrite(x4399,x4412,x4416)
    val x4417_b4815_b4813 = withCtrl(x4418) { WriteMem(b4813, Const(256)).name("x4417_b4815_b4813").srcCtx("GEMM_Blocked.scala:46:22") } // StreamWrite(x4399,x4412,x4416)
    val x4419 = withCtrl(x4431) { FringeDenseLoad(dram=List(x4328), cmdStream=List(b4812, b4813), dataStream=List(x4400)).name("x4419").srcCtx("GEMM_Blocked.scala:46:22") } // FringeDenseLoad(x4328,x4399,x4400)
    val x4420 = withCtrl(x4431) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x4420").srcCtx("GEMM_Blocked.scala:46:22") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x4421 = withCtrl(x4431) { CounterChain(List(x4420)).name("x4421").srcCtx("GEMM_Blocked.scala:46:22") } // CounterChainNew(List(x4420))
    val x4430 = withCtrl(x4431) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4421).name("x4430").srcCtx("GEMM_Blocked.scala:46:22") } // UnrolledForeach(List(b1901, b1887, b1842, b1833, b1828),x4421,Block(Const(())),List(List(b1925)),List(List(b1926)))
    val b1925 = withCtrl(x4430) { CounterIter(x4420, None).name("b1925") } // b1925
    val b1926 = withCtrl(x4430) { Const(true).name("b1926") } // b1926
    val x4422 = withCtrl(x4430) { OpDef(op=BitAnd, inputs=List(b1926, b1901)).name("x4422").srcCtx("UnrollingBase.scala:28:66") } // And(b1926,b1901)
    val x4423 = withCtrl(x4430) { OpDef(op=BitAnd, inputs=List(b1887, b1842)).name("x4423").srcCtx("UnrollingBase.scala:28:66") } // And(b1887,b1842)
    val x4424 = withCtrl(x4430) { OpDef(op=BitAnd, inputs=List(b1833, b1828)).name("x4424").srcCtx("UnrollingBase.scala:28:66") } // And(b1833,b1828)
    val x4425 = withCtrl(x4430) { OpDef(op=BitAnd, inputs=List(x4422, x4423)).name("x4425").srcCtx("UnrollingBase.scala:28:66") } // And(x4422,x4423)
    val x4426 = withCtrl(x4430) { OpDef(op=BitAnd, inputs=List(x4425, x4424)).name("x4426").srcCtx("UnrollingBase.scala:28:66") } // And(x4425,x4424)
    val x4427_x4427 = withCtrl(x4430) { ReadMem(x4400).name("x4427_x4427").srcCtx("GEMM_Blocked.scala:46:22") } // ParStreamRead(x4400,List(x4426))
    val x4428_x4428 = withCtrl(x4430) { x4427_x4427 } // VectorApply(x4427,0)
    val x4429 = withCtrl(x4430) { StoreBanks(List(List(x4387_d0_b0)), List(b1925), x4428_x4428).name("x4429").srcCtx("GEMM_Blocked.scala:46:22") } // ParSRAMStore(x4387,List(List(b1925)),List(x4428),List(x4426))
    val x4432_d0_b0 = withCtrl(x4706) { SRAM(size=64, banking=Strided(banks=16, stride=1)).name("x4432_d0_b0").srcCtx("GEMM_Blocked.scala:47:34") } // x4432 = SRAMNew(ArrayBuffer(Const(64)))
    isAccum(x4432_d0_b0) = false
    bufferDepthOf(x4432_d0_b0) = 2
    staticDimsOf(x4432_d0_b0) = List(64)
    val x4432_d1_b0 = withCtrl(x4706) { SRAM(size=64, banking=Strided(banks=16, stride=1)).name("x4432_d1_b0").srcCtx("GEMM_Blocked.scala:47:34") } // x4432 = SRAMNew(ArrayBuffer(Const(64)))
    isAccum(x4432_d1_b0) = true
    bufferDepthOf(x4432_d1_b0) = 1
    staticDimsOf(x4432_d1_b0) = List(64)
    val x4433 = withCtrl(x4706) { Counter(min=Const(0), max=Const(64), step=Const(1), par=8).name("x4433").srcCtx("GEMM_Blocked.scala:48:47") } // CounterNew(Const(0),Const(64),Const(1),Const(8))
    val x4434 = withCtrl(x4706) { CounterChain(List(x4433)).name("x4434").srcCtx("GEMM_Blocked.scala:53:16") } // CounterChainNew(List(x4433))
    val x4695 = withCtrl(x4706) { LoopController(style=MetaPipe, level=OuterControl, cchain=x4434).name("x4695").srcCtx("GEMM_Blocked.scala:53:16") } // UnrolledReduce(List(b1887, b1842, b1833, b1828),x4434,x4432,Block((x4432) => Const(())),List(List(b1942, b1943, b1944, b1945, b1946, b1947, b1948, b1949)),List(List(b1950, b1951, b1952, b1953, b1954, b1955, b1956, b1957)))
    val b1942 = withCtrl(x4695) { CounterIter(x4433, Some(0)).name("b1942") } // b1942
    val b1950 = withCtrl(x4695) { Const(true).name("b1950") } // b1950
    val b1943 = withCtrl(x4695) { CounterIter(x4433, Some(1)).name("b1943") } // b1943
    val b1951 = withCtrl(x4695) { Const(true).name("b1951") } // b1951
    val b1944 = withCtrl(x4695) { CounterIter(x4433, Some(2)).name("b1944") } // b1944
    val b1952 = withCtrl(x4695) { Const(true).name("b1952") } // b1952
    val b1945 = withCtrl(x4695) { CounterIter(x4433, Some(3)).name("b1945") } // b1945
    val b1953 = withCtrl(x4695) { Const(true).name("b1953") } // b1953
    val b1946 = withCtrl(x4695) { CounterIter(x4433, Some(4)).name("b1946") } // b1946
    val b1954 = withCtrl(x4695) { Const(true).name("b1954") } // b1954
    val b1947 = withCtrl(x4695) { CounterIter(x4433, Some(5)).name("b1947") } // b1947
    val b1955 = withCtrl(x4695) { Const(true).name("b1955") } // b1955
    val b1948 = withCtrl(x4695) { CounterIter(x4433, Some(6)).name("b1948") } // b1948
    val b1956 = withCtrl(x4695) { Const(true).name("b1956") } // b1956
    val b1949 = withCtrl(x4695) { CounterIter(x4433, Some(7)).name("b1949") } // b1949
    val b1957 = withCtrl(x4695) { Const(true).name("b1957") } // b1957
    val x4435_d0_b0 = withCtrl(x4695) { SRAM(size=64, banking=Strided(banks=16, stride=1)).name("x4435_d0_b0").srcCtx("GEMM_Blocked.scala:49:44") } // x4435 = SRAMNew(ArrayBuffer(Const(64)))
    isAccum(x4435_d0_b0) = false
    bufferDepthOf(x4435_d0_b0) = 2
    staticDimsOf(x4435_d0_b0) = List(64)
    val x4436_d0_b0 = withCtrl(x4695) { SRAM(size=64, banking=Strided(banks=16, stride=1)).name("x4436_d0_b0").srcCtx("GEMM_Blocked.scala:49:44") } // x4436 = SRAMNew(ArrayBuffer(Const(64)))
    isAccum(x4436_d0_b0) = false
    bufferDepthOf(x4436_d0_b0) = 2
    staticDimsOf(x4436_d0_b0) = List(64)
    val x4437_d0_b0 = withCtrl(x4695) { SRAM(size=64, banking=Strided(banks=16, stride=1)).name("x4437_d0_b0").srcCtx("GEMM_Blocked.scala:49:44") } // x4437 = SRAMNew(ArrayBuffer(Const(64)))
    isAccum(x4437_d0_b0) = false
    bufferDepthOf(x4437_d0_b0) = 2
    staticDimsOf(x4437_d0_b0) = List(64)
    val x4438_d0_b0 = withCtrl(x4695) { SRAM(size=64, banking=Strided(banks=16, stride=1)).name("x4438_d0_b0").srcCtx("GEMM_Blocked.scala:49:44") } // x4438 = SRAMNew(ArrayBuffer(Const(64)))
    isAccum(x4438_d0_b0) = false
    bufferDepthOf(x4438_d0_b0) = 2
    staticDimsOf(x4438_d0_b0) = List(64)
    val x4439_d0_b0 = withCtrl(x4695) { SRAM(size=64, banking=Strided(banks=16, stride=1)).name("x4439_d0_b0").srcCtx("GEMM_Blocked.scala:49:44") } // x4439 = SRAMNew(ArrayBuffer(Const(64)))
    isAccum(x4439_d0_b0) = false
    bufferDepthOf(x4439_d0_b0) = 2
    staticDimsOf(x4439_d0_b0) = List(64)
    val x4440_d0_b0 = withCtrl(x4695) { SRAM(size=64, banking=Strided(banks=16, stride=1)).name("x4440_d0_b0").srcCtx("GEMM_Blocked.scala:49:44") } // x4440 = SRAMNew(ArrayBuffer(Const(64)))
    isAccum(x4440_d0_b0) = false
    bufferDepthOf(x4440_d0_b0) = 2
    staticDimsOf(x4440_d0_b0) = List(64)
    val x4441_d0_b0 = withCtrl(x4695) { SRAM(size=64, banking=Strided(banks=16, stride=1)).name("x4441_d0_b0").srcCtx("GEMM_Blocked.scala:49:44") } // x4441 = SRAMNew(ArrayBuffer(Const(64)))
    isAccum(x4441_d0_b0) = false
    bufferDepthOf(x4441_d0_b0) = 2
    staticDimsOf(x4441_d0_b0) = List(64)
    val x4442_d0_b0 = withCtrl(x4695) { SRAM(size=64, banking=Strided(banks=16, stride=1)).name("x4442_d0_b0").srcCtx("GEMM_Blocked.scala:49:44") } // x4442 = SRAMNew(ArrayBuffer(Const(64)))
    isAccum(x4442_d0_b0) = false
    bufferDepthOf(x4442_d0_b0) = 2
    staticDimsOf(x4442_d0_b0) = List(64)
    val x4443 = withCtrl(x4695) { Reg(init=Some(0.0)).name("x4443").srcCtx("GEMM_Blocked.scala:53:16") } // x4443 = RegNew(Const(0))
    isAccum(x4443) = false
    bufferDepthOf(x4443) = 2
    val x4444 = withCtrl(x4695) { Reg(init=Some(0.0)).name("x4444").srcCtx("GEMM_Blocked.scala:53:16") } // x4444 = RegNew(Const(0))
    isAccum(x4444) = false
    bufferDepthOf(x4444) = 2
    val x4445 = withCtrl(x4695) { Reg(init=Some(0.0)).name("x4445").srcCtx("GEMM_Blocked.scala:53:16") } // x4445 = RegNew(Const(0))
    isAccum(x4445) = false
    bufferDepthOf(x4445) = 2
    val x4446 = withCtrl(x4695) { Reg(init=Some(0.0)).name("x4446").srcCtx("GEMM_Blocked.scala:53:16") } // x4446 = RegNew(Const(0))
    isAccum(x4446) = false
    bufferDepthOf(x4446) = 2
    val x4447 = withCtrl(x4695) { Reg(init=Some(0.0)).name("x4447").srcCtx("GEMM_Blocked.scala:53:16") } // x4447 = RegNew(Const(0))
    isAccum(x4447) = false
    bufferDepthOf(x4447) = 2
    val x4448 = withCtrl(x4695) { Reg(init=Some(0.0)).name("x4448").srcCtx("GEMM_Blocked.scala:53:16") } // x4448 = RegNew(Const(0))
    isAccum(x4448) = false
    bufferDepthOf(x4448) = 2
    val x4449 = withCtrl(x4695) { Reg(init=Some(0.0)).name("x4449").srcCtx("GEMM_Blocked.scala:53:16") } // x4449 = RegNew(Const(0))
    isAccum(x4449) = false
    bufferDepthOf(x4449) = 2
    val x4450 = withCtrl(x4695) { Reg(init=Some(0.0)).name("x4450").srcCtx("GEMM_Blocked.scala:53:16") } // x4450 = RegNew(Const(0))
    isAccum(x4450) = false
    bufferDepthOf(x4450) = 2
    val x4507 = withCtrl(x4695) { UnitController(style=ForkJoin, level=OuterControl).name("x4507").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b1887, b1842, b1833, b1828),Block(Const(())))
    val x4457 = withCtrl(x4507) { UnitController(style=SeqPipe, level=InnerControl).name("x4457").srcCtx("GEMM_Blocked.scala:53:16") } // UnitPipe(List(b1950, b1887, b1842, b1833, b1828),Block(Const(())))
    val x4451 = withCtrl(x4457) { OpDef(op=BitAnd, inputs=List(b1950, b1887)).name("x4451").srcCtx("UnrollingBase.scala:28:66") } // And(b1950,b1887)
    val x4452 = withCtrl(x4457) { OpDef(op=BitAnd, inputs=List(b1842, b1833)).name("x4452").srcCtx("UnrollingBase.scala:28:66") } // And(b1842,b1833)
    val x4453 = withCtrl(x4457) { OpDef(op=BitAnd, inputs=List(x4451, x4452)).name("x4453").srcCtx("UnrollingBase.scala:28:66") } // And(x4451,x4452)
    val x4454 = withCtrl(x4457) { OpDef(op=BitAnd, inputs=List(x4453, b1828)).name("x4454").srcCtx("UnrollingBase.scala:28:66") } // And(x4453,b1828)
    val x4455 = withCtrl(x4457) { LoadBanks(List(x4387_d0_b0), List(b1942)).name("x4455").srcCtx("GEMM_Blocked.scala:50:36") } // SRAMLoad(x4387,ArrayBuffer(Const(64)),List(b1942),Const(0),x4454)
    val x4456_x4443 = withCtrl(x4457) { WriteMem(x4443, x4455).name("x4456_x4443").srcCtx("GEMM_Blocked.scala:53:16") } // RegWrite(x4443,x4455,x4454)
    val x4464 = withCtrl(x4507) { UnitController(style=SeqPipe, level=InnerControl).name("x4464").srcCtx("GEMM_Blocked.scala:53:16") } // UnitPipe(List(b1951, b1887, b1842, b1833, b1828),Block(Const(())))
    val x4458 = withCtrl(x4464) { OpDef(op=BitAnd, inputs=List(b1951, b1887)).name("x4458").srcCtx("UnrollingBase.scala:28:66") } // And(b1951,b1887)
    val x4459 = withCtrl(x4464) { OpDef(op=BitAnd, inputs=List(b1842, b1833)).name("x4459").srcCtx("UnrollingBase.scala:28:66") } // And(b1842,b1833)
    val x4460 = withCtrl(x4464) { OpDef(op=BitAnd, inputs=List(x4458, x4459)).name("x4460").srcCtx("UnrollingBase.scala:28:66") } // And(x4458,x4459)
    val x4461 = withCtrl(x4464) { OpDef(op=BitAnd, inputs=List(x4460, b1828)).name("x4461").srcCtx("UnrollingBase.scala:28:66") } // And(x4460,b1828)
    val x4462 = withCtrl(x4464) { LoadBanks(List(x4387_d0_b0), List(b1943)).name("x4462").srcCtx("GEMM_Blocked.scala:50:36") } // SRAMLoad(x4387,ArrayBuffer(Const(64)),List(b1943),Const(0),x4461)
    val x4463_x4444 = withCtrl(x4464) { WriteMem(x4444, x4462).name("x4463_x4444").srcCtx("GEMM_Blocked.scala:53:16") } // RegWrite(x4444,x4462,x4461)
    val x4471 = withCtrl(x4507) { UnitController(style=SeqPipe, level=InnerControl).name("x4471").srcCtx("GEMM_Blocked.scala:53:16") } // UnitPipe(List(b1952, b1887, b1842, b1833, b1828),Block(Const(())))
    val x4465 = withCtrl(x4471) { OpDef(op=BitAnd, inputs=List(b1952, b1887)).name("x4465").srcCtx("UnrollingBase.scala:28:66") } // And(b1952,b1887)
    val x4466 = withCtrl(x4471) { OpDef(op=BitAnd, inputs=List(b1842, b1833)).name("x4466").srcCtx("UnrollingBase.scala:28:66") } // And(b1842,b1833)
    val x4467 = withCtrl(x4471) { OpDef(op=BitAnd, inputs=List(x4465, x4466)).name("x4467").srcCtx("UnrollingBase.scala:28:66") } // And(x4465,x4466)
    val x4468 = withCtrl(x4471) { OpDef(op=BitAnd, inputs=List(x4467, b1828)).name("x4468").srcCtx("UnrollingBase.scala:28:66") } // And(x4467,b1828)
    val x4469 = withCtrl(x4471) { LoadBanks(List(x4387_d0_b0), List(b1944)).name("x4469").srcCtx("GEMM_Blocked.scala:50:36") } // SRAMLoad(x4387,ArrayBuffer(Const(64)),List(b1944),Const(0),x4468)
    val x4470_x4445 = withCtrl(x4471) { WriteMem(x4445, x4469).name("x4470_x4445").srcCtx("GEMM_Blocked.scala:53:16") } // RegWrite(x4445,x4469,x4468)
    val x4478 = withCtrl(x4507) { UnitController(style=SeqPipe, level=InnerControl).name("x4478").srcCtx("GEMM_Blocked.scala:53:16") } // UnitPipe(List(b1953, b1887, b1842, b1833, b1828),Block(Const(())))
    val x4472 = withCtrl(x4478) { OpDef(op=BitAnd, inputs=List(b1953, b1887)).name("x4472").srcCtx("UnrollingBase.scala:28:66") } // And(b1953,b1887)
    val x4473 = withCtrl(x4478) { OpDef(op=BitAnd, inputs=List(b1842, b1833)).name("x4473").srcCtx("UnrollingBase.scala:28:66") } // And(b1842,b1833)
    val x4474 = withCtrl(x4478) { OpDef(op=BitAnd, inputs=List(x4472, x4473)).name("x4474").srcCtx("UnrollingBase.scala:28:66") } // And(x4472,x4473)
    val x4475 = withCtrl(x4478) { OpDef(op=BitAnd, inputs=List(x4474, b1828)).name("x4475").srcCtx("UnrollingBase.scala:28:66") } // And(x4474,b1828)
    val x4476 = withCtrl(x4478) { LoadBanks(List(x4387_d0_b0), List(b1945)).name("x4476").srcCtx("GEMM_Blocked.scala:50:36") } // SRAMLoad(x4387,ArrayBuffer(Const(64)),List(b1945),Const(0),x4475)
    val x4477_x4446 = withCtrl(x4478) { WriteMem(x4446, x4476).name("x4477_x4446").srcCtx("GEMM_Blocked.scala:53:16") } // RegWrite(x4446,x4476,x4475)
    val x4485 = withCtrl(x4507) { UnitController(style=SeqPipe, level=InnerControl).name("x4485").srcCtx("GEMM_Blocked.scala:53:16") } // UnitPipe(List(b1954, b1887, b1842, b1833, b1828),Block(Const(())))
    val x4479 = withCtrl(x4485) { OpDef(op=BitAnd, inputs=List(b1954, b1887)).name("x4479").srcCtx("UnrollingBase.scala:28:66") } // And(b1954,b1887)
    val x4480 = withCtrl(x4485) { OpDef(op=BitAnd, inputs=List(b1842, b1833)).name("x4480").srcCtx("UnrollingBase.scala:28:66") } // And(b1842,b1833)
    val x4481 = withCtrl(x4485) { OpDef(op=BitAnd, inputs=List(x4479, x4480)).name("x4481").srcCtx("UnrollingBase.scala:28:66") } // And(x4479,x4480)
    val x4482 = withCtrl(x4485) { OpDef(op=BitAnd, inputs=List(x4481, b1828)).name("x4482").srcCtx("UnrollingBase.scala:28:66") } // And(x4481,b1828)
    val x4483 = withCtrl(x4485) { LoadBanks(List(x4387_d0_b0), List(b1946)).name("x4483").srcCtx("GEMM_Blocked.scala:50:36") } // SRAMLoad(x4387,ArrayBuffer(Const(64)),List(b1946),Const(0),x4482)
    val x4484_x4447 = withCtrl(x4485) { WriteMem(x4447, x4483).name("x4484_x4447").srcCtx("GEMM_Blocked.scala:53:16") } // RegWrite(x4447,x4483,x4482)
    val x4492 = withCtrl(x4507) { UnitController(style=SeqPipe, level=InnerControl).name("x4492").srcCtx("GEMM_Blocked.scala:53:16") } // UnitPipe(List(b1955, b1887, b1842, b1833, b1828),Block(Const(())))
    val x4486 = withCtrl(x4492) { OpDef(op=BitAnd, inputs=List(b1955, b1887)).name("x4486").srcCtx("UnrollingBase.scala:28:66") } // And(b1955,b1887)
    val x4487 = withCtrl(x4492) { OpDef(op=BitAnd, inputs=List(b1842, b1833)).name("x4487").srcCtx("UnrollingBase.scala:28:66") } // And(b1842,b1833)
    val x4488 = withCtrl(x4492) { OpDef(op=BitAnd, inputs=List(x4486, x4487)).name("x4488").srcCtx("UnrollingBase.scala:28:66") } // And(x4486,x4487)
    val x4489 = withCtrl(x4492) { OpDef(op=BitAnd, inputs=List(x4488, b1828)).name("x4489").srcCtx("UnrollingBase.scala:28:66") } // And(x4488,b1828)
    val x4490 = withCtrl(x4492) { LoadBanks(List(x4387_d0_b0), List(b1947)).name("x4490").srcCtx("GEMM_Blocked.scala:50:36") } // SRAMLoad(x4387,ArrayBuffer(Const(64)),List(b1947),Const(0),x4489)
    val x4491_x4448 = withCtrl(x4492) { WriteMem(x4448, x4490).name("x4491_x4448").srcCtx("GEMM_Blocked.scala:53:16") } // RegWrite(x4448,x4490,x4489)
    val x4499 = withCtrl(x4507) { UnitController(style=SeqPipe, level=InnerControl).name("x4499").srcCtx("GEMM_Blocked.scala:53:16") } // UnitPipe(List(b1956, b1887, b1842, b1833, b1828),Block(Const(())))
    val x4493 = withCtrl(x4499) { OpDef(op=BitAnd, inputs=List(b1956, b1887)).name("x4493").srcCtx("UnrollingBase.scala:28:66") } // And(b1956,b1887)
    val x4494 = withCtrl(x4499) { OpDef(op=BitAnd, inputs=List(b1842, b1833)).name("x4494").srcCtx("UnrollingBase.scala:28:66") } // And(b1842,b1833)
    val x4495 = withCtrl(x4499) { OpDef(op=BitAnd, inputs=List(x4493, x4494)).name("x4495").srcCtx("UnrollingBase.scala:28:66") } // And(x4493,x4494)
    val x4496 = withCtrl(x4499) { OpDef(op=BitAnd, inputs=List(x4495, b1828)).name("x4496").srcCtx("UnrollingBase.scala:28:66") } // And(x4495,b1828)
    val x4497 = withCtrl(x4499) { LoadBanks(List(x4387_d0_b0), List(b1948)).name("x4497").srcCtx("GEMM_Blocked.scala:50:36") } // SRAMLoad(x4387,ArrayBuffer(Const(64)),List(b1948),Const(0),x4496)
    val x4498_x4449 = withCtrl(x4499) { WriteMem(x4449, x4497).name("x4498_x4449").srcCtx("GEMM_Blocked.scala:53:16") } // RegWrite(x4449,x4497,x4496)
    val x4506 = withCtrl(x4507) { UnitController(style=SeqPipe, level=InnerControl).name("x4506").srcCtx("GEMM_Blocked.scala:53:16") } // UnitPipe(List(b1957, b1887, b1842, b1833, b1828),Block(Const(())))
    val x4500 = withCtrl(x4506) { OpDef(op=BitAnd, inputs=List(b1957, b1887)).name("x4500").srcCtx("UnrollingBase.scala:28:66") } // And(b1957,b1887)
    val x4501 = withCtrl(x4506) { OpDef(op=BitAnd, inputs=List(b1842, b1833)).name("x4501").srcCtx("UnrollingBase.scala:28:66") } // And(b1842,b1833)
    val x4502 = withCtrl(x4506) { OpDef(op=BitAnd, inputs=List(x4500, x4501)).name("x4502").srcCtx("UnrollingBase.scala:28:66") } // And(x4500,x4501)
    val x4503 = withCtrl(x4506) { OpDef(op=BitAnd, inputs=List(x4502, b1828)).name("x4503").srcCtx("UnrollingBase.scala:28:66") } // And(x4502,b1828)
    val x4504 = withCtrl(x4506) { LoadBanks(List(x4387_d0_b0), List(b1949)).name("x4504").srcCtx("GEMM_Blocked.scala:50:36") } // SRAMLoad(x4387,ArrayBuffer(Const(64)),List(b1949),Const(0),x4503)
    val x4505_x4450 = withCtrl(x4506) { WriteMem(x4450, x4504).name("x4505_x4450").srcCtx("GEMM_Blocked.scala:53:16") } // RegWrite(x4450,x4504,x4503)
    val x4612 = withCtrl(x4695) { UnitController(style=ForkJoin, level=OuterControl).name("x4612").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b1887, b1842, b1833, b1828),Block(Const(())))
    val x4508 = withCtrl(x4612) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x4508").srcCtx("GEMM_Blocked.scala:51:33") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x4509 = withCtrl(x4612) { CounterChain(List(x4508)).name("x4509").srcCtx("GEMM_Blocked.scala:51:41") } // CounterChainNew(List(x4508))
    val x4520 = withCtrl(x4612) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4509).name("x4520").srcCtx("GEMM_Blocked.scala:51:41") } // UnrolledForeach(List(b1950, b1887, b1842, b1833, b1828),x4509,Block(Const(())),List(List(b2047)),List(List(b2048)))
    val b2047 = withCtrl(x4520) { CounterIter(x4508, None).name("b2047") } // b2047
    val b2048 = withCtrl(x4520) { Const(true).name("b2048") } // b2048
    val x4510 = withCtrl(x4520) { OpDef(op=BitAnd, inputs=List(b2048, b1950)).name("x4510").srcCtx("UnrollingBase.scala:28:66") } // And(b2048,b1950)
    val x4511 = withCtrl(x4520) { OpDef(op=BitAnd, inputs=List(b1887, b1842)).name("x4511").srcCtx("UnrollingBase.scala:28:66") } // And(b1887,b1842)
    val x4512 = withCtrl(x4520) { OpDef(op=BitAnd, inputs=List(b1833, b1828)).name("x4512").srcCtx("UnrollingBase.scala:28:66") } // And(b1833,b1828)
    val x4513 = withCtrl(x4520) { OpDef(op=BitAnd, inputs=List(x4510, x4511)).name("x4513").srcCtx("UnrollingBase.scala:28:66") } // And(x4510,x4511)
    val x4514 = withCtrl(x4520) { OpDef(op=BitAnd, inputs=List(x4513, x4512)).name("x4514").srcCtx("UnrollingBase.scala:28:66") } // And(x4513,x4512)
    val x4515 = withCtrl(x4520) { LoadBanks(List(x4349_d0_b0), List(b1942, b2047)).name("x4515").srcCtx("GEMM_Blocked.scala:51:73") } // ParSRAMLoad(x4349,List(List(b1942, b2047)),List(x4514))
    val x4516 = withCtrl(x4520) { x4515 } // VectorApply(x4515,0)
    val x4517 = withCtrl(x4520) { ReadMem(x4443).name("x4517").srcCtx("GEMM_Blocked.scala:53:16") } // RegRead(x4443)
    val x4518 = withCtrl(x4520) { OpDef(op=FixMul, inputs=List(x4516, x4517)).name("x4518").srcCtx("GEMM_Blocked.scala:51:80") } // FixMul(x4516,x4517)
    val x4519 = withCtrl(x4520) { StoreBanks(List(List(x4435_d0_b0)), List(b2047), x4518).name("x4519").srcCtx("GEMM_Blocked.scala:51:65") } // ParSRAMStore(x4435,List(List(b2047)),List(x4518),List(x4514))
    val x4521 = withCtrl(x4612) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x4521").srcCtx("GEMM_Blocked.scala:51:33") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x4522 = withCtrl(x4612) { CounterChain(List(x4521)).name("x4522").srcCtx("GEMM_Blocked.scala:51:41") } // CounterChainNew(List(x4521))
    val x4533 = withCtrl(x4612) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4522).name("x4533").srcCtx("GEMM_Blocked.scala:51:41") } // UnrolledForeach(List(b1951, b1887, b1842, b1833, b1828),x4522,Block(Const(())),List(List(b2060)),List(List(b2061)))
    val b2060 = withCtrl(x4533) { CounterIter(x4521, None).name("b2060") } // b2060
    val b2061 = withCtrl(x4533) { Const(true).name("b2061") } // b2061
    val x4523 = withCtrl(x4533) { OpDef(op=BitAnd, inputs=List(b2061, b1951)).name("x4523").srcCtx("UnrollingBase.scala:28:66") } // And(b2061,b1951)
    val x4524 = withCtrl(x4533) { OpDef(op=BitAnd, inputs=List(b1887, b1842)).name("x4524").srcCtx("UnrollingBase.scala:28:66") } // And(b1887,b1842)
    val x4525 = withCtrl(x4533) { OpDef(op=BitAnd, inputs=List(b1833, b1828)).name("x4525").srcCtx("UnrollingBase.scala:28:66") } // And(b1833,b1828)
    val x4526 = withCtrl(x4533) { OpDef(op=BitAnd, inputs=List(x4523, x4524)).name("x4526").srcCtx("UnrollingBase.scala:28:66") } // And(x4523,x4524)
    val x4527 = withCtrl(x4533) { OpDef(op=BitAnd, inputs=List(x4526, x4525)).name("x4527").srcCtx("UnrollingBase.scala:28:66") } // And(x4526,x4525)
    val x4528 = withCtrl(x4533) { LoadBanks(List(x4349_d0_b1), List(b1943, b2060)).name("x4528").srcCtx("GEMM_Blocked.scala:51:73") } // ParSRAMLoad(x4349,List(List(b1943, b2060)),List(x4527))
    val x4529 = withCtrl(x4533) { x4528 } // VectorApply(x4528,0)
    val x4530 = withCtrl(x4533) { ReadMem(x4444).name("x4530").srcCtx("GEMM_Blocked.scala:53:16") } // RegRead(x4444)
    val x4531 = withCtrl(x4533) { OpDef(op=FixMul, inputs=List(x4529, x4530)).name("x4531").srcCtx("GEMM_Blocked.scala:51:80") } // FixMul(x4529,x4530)
    val x4532 = withCtrl(x4533) { StoreBanks(List(List(x4436_d0_b0)), List(b2060), x4531).name("x4532").srcCtx("GEMM_Blocked.scala:51:65") } // ParSRAMStore(x4436,List(List(b2060)),List(x4531),List(x4527))
    val x4534 = withCtrl(x4612) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x4534").srcCtx("GEMM_Blocked.scala:51:33") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x4535 = withCtrl(x4612) { CounterChain(List(x4534)).name("x4535").srcCtx("GEMM_Blocked.scala:51:41") } // CounterChainNew(List(x4534))
    val x4546 = withCtrl(x4612) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4535).name("x4546").srcCtx("GEMM_Blocked.scala:51:41") } // UnrolledForeach(List(b1952, b1887, b1842, b1833, b1828),x4535,Block(Const(())),List(List(b2073)),List(List(b2074)))
    val b2073 = withCtrl(x4546) { CounterIter(x4534, None).name("b2073") } // b2073
    val b2074 = withCtrl(x4546) { Const(true).name("b2074") } // b2074
    val x4536 = withCtrl(x4546) { OpDef(op=BitAnd, inputs=List(b2074, b1952)).name("x4536").srcCtx("UnrollingBase.scala:28:66") } // And(b2074,b1952)
    val x4537 = withCtrl(x4546) { OpDef(op=BitAnd, inputs=List(b1887, b1842)).name("x4537").srcCtx("UnrollingBase.scala:28:66") } // And(b1887,b1842)
    val x4538 = withCtrl(x4546) { OpDef(op=BitAnd, inputs=List(b1833, b1828)).name("x4538").srcCtx("UnrollingBase.scala:28:66") } // And(b1833,b1828)
    val x4539 = withCtrl(x4546) { OpDef(op=BitAnd, inputs=List(x4536, x4537)).name("x4539").srcCtx("UnrollingBase.scala:28:66") } // And(x4536,x4537)
    val x4540 = withCtrl(x4546) { OpDef(op=BitAnd, inputs=List(x4539, x4538)).name("x4540").srcCtx("UnrollingBase.scala:28:66") } // And(x4539,x4538)
    val x4541 = withCtrl(x4546) { LoadBanks(List(x4349_d0_b2), List(b1944, b2073)).name("x4541").srcCtx("GEMM_Blocked.scala:51:73") } // ParSRAMLoad(x4349,List(List(b1944, b2073)),List(x4540))
    val x4542 = withCtrl(x4546) { x4541 } // VectorApply(x4541,0)
    val x4543 = withCtrl(x4546) { ReadMem(x4445).name("x4543").srcCtx("GEMM_Blocked.scala:53:16") } // RegRead(x4445)
    val x4544 = withCtrl(x4546) { OpDef(op=FixMul, inputs=List(x4542, x4543)).name("x4544").srcCtx("GEMM_Blocked.scala:51:80") } // FixMul(x4542,x4543)
    val x4545 = withCtrl(x4546) { StoreBanks(List(List(x4437_d0_b0)), List(b2073), x4544).name("x4545").srcCtx("GEMM_Blocked.scala:51:65") } // ParSRAMStore(x4437,List(List(b2073)),List(x4544),List(x4540))
    val x4547 = withCtrl(x4612) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x4547").srcCtx("GEMM_Blocked.scala:51:33") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x4548 = withCtrl(x4612) { CounterChain(List(x4547)).name("x4548").srcCtx("GEMM_Blocked.scala:51:41") } // CounterChainNew(List(x4547))
    val x4559 = withCtrl(x4612) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4548).name("x4559").srcCtx("GEMM_Blocked.scala:51:41") } // UnrolledForeach(List(b1953, b1887, b1842, b1833, b1828),x4548,Block(Const(())),List(List(b2086)),List(List(b2087)))
    val b2086 = withCtrl(x4559) { CounterIter(x4547, None).name("b2086") } // b2086
    val b2087 = withCtrl(x4559) { Const(true).name("b2087") } // b2087
    val x4549 = withCtrl(x4559) { OpDef(op=BitAnd, inputs=List(b2087, b1953)).name("x4549").srcCtx("UnrollingBase.scala:28:66") } // And(b2087,b1953)
    val x4550 = withCtrl(x4559) { OpDef(op=BitAnd, inputs=List(b1887, b1842)).name("x4550").srcCtx("UnrollingBase.scala:28:66") } // And(b1887,b1842)
    val x4551 = withCtrl(x4559) { OpDef(op=BitAnd, inputs=List(b1833, b1828)).name("x4551").srcCtx("UnrollingBase.scala:28:66") } // And(b1833,b1828)
    val x4552 = withCtrl(x4559) { OpDef(op=BitAnd, inputs=List(x4549, x4550)).name("x4552").srcCtx("UnrollingBase.scala:28:66") } // And(x4549,x4550)
    val x4553 = withCtrl(x4559) { OpDef(op=BitAnd, inputs=List(x4552, x4551)).name("x4553").srcCtx("UnrollingBase.scala:28:66") } // And(x4552,x4551)
    val x4554 = withCtrl(x4559) { LoadBanks(List(x4349_d0_b3), List(b1945, b2086)).name("x4554").srcCtx("GEMM_Blocked.scala:51:73") } // ParSRAMLoad(x4349,List(List(b1945, b2086)),List(x4553))
    val x4555 = withCtrl(x4559) { x4554 } // VectorApply(x4554,0)
    val x4556 = withCtrl(x4559) { ReadMem(x4446).name("x4556").srcCtx("GEMM_Blocked.scala:53:16") } // RegRead(x4446)
    val x4557 = withCtrl(x4559) { OpDef(op=FixMul, inputs=List(x4555, x4556)).name("x4557").srcCtx("GEMM_Blocked.scala:51:80") } // FixMul(x4555,x4556)
    val x4558 = withCtrl(x4559) { StoreBanks(List(List(x4438_d0_b0)), List(b2086), x4557).name("x4558").srcCtx("GEMM_Blocked.scala:51:65") } // ParSRAMStore(x4438,List(List(b2086)),List(x4557),List(x4553))
    val x4560 = withCtrl(x4612) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x4560").srcCtx("GEMM_Blocked.scala:51:33") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x4561 = withCtrl(x4612) { CounterChain(List(x4560)).name("x4561").srcCtx("GEMM_Blocked.scala:51:41") } // CounterChainNew(List(x4560))
    val x4572 = withCtrl(x4612) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4561).name("x4572").srcCtx("GEMM_Blocked.scala:51:41") } // UnrolledForeach(List(b1954, b1887, b1842, b1833, b1828),x4561,Block(Const(())),List(List(b2099)),List(List(b2100)))
    val b2099 = withCtrl(x4572) { CounterIter(x4560, None).name("b2099") } // b2099
    val b2100 = withCtrl(x4572) { Const(true).name("b2100") } // b2100
    val x4562 = withCtrl(x4572) { OpDef(op=BitAnd, inputs=List(b2100, b1954)).name("x4562").srcCtx("UnrollingBase.scala:28:66") } // And(b2100,b1954)
    val x4563 = withCtrl(x4572) { OpDef(op=BitAnd, inputs=List(b1887, b1842)).name("x4563").srcCtx("UnrollingBase.scala:28:66") } // And(b1887,b1842)
    val x4564 = withCtrl(x4572) { OpDef(op=BitAnd, inputs=List(b1833, b1828)).name("x4564").srcCtx("UnrollingBase.scala:28:66") } // And(b1833,b1828)
    val x4565 = withCtrl(x4572) { OpDef(op=BitAnd, inputs=List(x4562, x4563)).name("x4565").srcCtx("UnrollingBase.scala:28:66") } // And(x4562,x4563)
    val x4566 = withCtrl(x4572) { OpDef(op=BitAnd, inputs=List(x4565, x4564)).name("x4566").srcCtx("UnrollingBase.scala:28:66") } // And(x4565,x4564)
    val x4567 = withCtrl(x4572) { LoadBanks(List(x4349_d0_b4), List(b1946, b2099)).name("x4567").srcCtx("GEMM_Blocked.scala:51:73") } // ParSRAMLoad(x4349,List(List(b1946, b2099)),List(x4566))
    val x4568 = withCtrl(x4572) { x4567 } // VectorApply(x4567,0)
    val x4569 = withCtrl(x4572) { ReadMem(x4447).name("x4569").srcCtx("GEMM_Blocked.scala:53:16") } // RegRead(x4447)
    val x4570 = withCtrl(x4572) { OpDef(op=FixMul, inputs=List(x4568, x4569)).name("x4570").srcCtx("GEMM_Blocked.scala:51:80") } // FixMul(x4568,x4569)
    val x4571 = withCtrl(x4572) { StoreBanks(List(List(x4439_d0_b0)), List(b2099), x4570).name("x4571").srcCtx("GEMM_Blocked.scala:51:65") } // ParSRAMStore(x4439,List(List(b2099)),List(x4570),List(x4566))
    val x4573 = withCtrl(x4612) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x4573").srcCtx("GEMM_Blocked.scala:51:33") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x4574 = withCtrl(x4612) { CounterChain(List(x4573)).name("x4574").srcCtx("GEMM_Blocked.scala:51:41") } // CounterChainNew(List(x4573))
    val x4585 = withCtrl(x4612) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4574).name("x4585").srcCtx("GEMM_Blocked.scala:51:41") } // UnrolledForeach(List(b1955, b1887, b1842, b1833, b1828),x4574,Block(Const(())),List(List(b2112)),List(List(b2113)))
    val b2112 = withCtrl(x4585) { CounterIter(x4573, None).name("b2112") } // b2112
    val b2113 = withCtrl(x4585) { Const(true).name("b2113") } // b2113
    val x4575 = withCtrl(x4585) { OpDef(op=BitAnd, inputs=List(b2113, b1955)).name("x4575").srcCtx("UnrollingBase.scala:28:66") } // And(b2113,b1955)
    val x4576 = withCtrl(x4585) { OpDef(op=BitAnd, inputs=List(b1887, b1842)).name("x4576").srcCtx("UnrollingBase.scala:28:66") } // And(b1887,b1842)
    val x4577 = withCtrl(x4585) { OpDef(op=BitAnd, inputs=List(b1833, b1828)).name("x4577").srcCtx("UnrollingBase.scala:28:66") } // And(b1833,b1828)
    val x4578 = withCtrl(x4585) { OpDef(op=BitAnd, inputs=List(x4575, x4576)).name("x4578").srcCtx("UnrollingBase.scala:28:66") } // And(x4575,x4576)
    val x4579 = withCtrl(x4585) { OpDef(op=BitAnd, inputs=List(x4578, x4577)).name("x4579").srcCtx("UnrollingBase.scala:28:66") } // And(x4578,x4577)
    val x4580 = withCtrl(x4585) { LoadBanks(List(x4349_d0_b5), List(b1947, b2112)).name("x4580").srcCtx("GEMM_Blocked.scala:51:73") } // ParSRAMLoad(x4349,List(List(b1947, b2112)),List(x4579))
    val x4581 = withCtrl(x4585) { x4580 } // VectorApply(x4580,0)
    val x4582 = withCtrl(x4585) { ReadMem(x4448).name("x4582").srcCtx("GEMM_Blocked.scala:53:16") } // RegRead(x4448)
    val x4583 = withCtrl(x4585) { OpDef(op=FixMul, inputs=List(x4581, x4582)).name("x4583").srcCtx("GEMM_Blocked.scala:51:80") } // FixMul(x4581,x4582)
    val x4584 = withCtrl(x4585) { StoreBanks(List(List(x4440_d0_b0)), List(b2112), x4583).name("x4584").srcCtx("GEMM_Blocked.scala:51:65") } // ParSRAMStore(x4440,List(List(b2112)),List(x4583),List(x4579))
    val x4586 = withCtrl(x4612) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x4586").srcCtx("GEMM_Blocked.scala:51:33") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x4587 = withCtrl(x4612) { CounterChain(List(x4586)).name("x4587").srcCtx("GEMM_Blocked.scala:51:41") } // CounterChainNew(List(x4586))
    val x4598 = withCtrl(x4612) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4587).name("x4598").srcCtx("GEMM_Blocked.scala:51:41") } // UnrolledForeach(List(b1956, b1887, b1842, b1833, b1828),x4587,Block(Const(())),List(List(b2125)),List(List(b2126)))
    val b2125 = withCtrl(x4598) { CounterIter(x4586, None).name("b2125") } // b2125
    val b2126 = withCtrl(x4598) { Const(true).name("b2126") } // b2126
    val x4588 = withCtrl(x4598) { OpDef(op=BitAnd, inputs=List(b2126, b1956)).name("x4588").srcCtx("UnrollingBase.scala:28:66") } // And(b2126,b1956)
    val x4589 = withCtrl(x4598) { OpDef(op=BitAnd, inputs=List(b1887, b1842)).name("x4589").srcCtx("UnrollingBase.scala:28:66") } // And(b1887,b1842)
    val x4590 = withCtrl(x4598) { OpDef(op=BitAnd, inputs=List(b1833, b1828)).name("x4590").srcCtx("UnrollingBase.scala:28:66") } // And(b1833,b1828)
    val x4591 = withCtrl(x4598) { OpDef(op=BitAnd, inputs=List(x4588, x4589)).name("x4591").srcCtx("UnrollingBase.scala:28:66") } // And(x4588,x4589)
    val x4592 = withCtrl(x4598) { OpDef(op=BitAnd, inputs=List(x4591, x4590)).name("x4592").srcCtx("UnrollingBase.scala:28:66") } // And(x4591,x4590)
    val x4593 = withCtrl(x4598) { LoadBanks(List(x4349_d0_b6), List(b1948, b2125)).name("x4593").srcCtx("GEMM_Blocked.scala:51:73") } // ParSRAMLoad(x4349,List(List(b1948, b2125)),List(x4592))
    val x4594 = withCtrl(x4598) { x4593 } // VectorApply(x4593,0)
    val x4595 = withCtrl(x4598) { ReadMem(x4449).name("x4595").srcCtx("GEMM_Blocked.scala:53:16") } // RegRead(x4449)
    val x4596 = withCtrl(x4598) { OpDef(op=FixMul, inputs=List(x4594, x4595)).name("x4596").srcCtx("GEMM_Blocked.scala:51:80") } // FixMul(x4594,x4595)
    val x4597 = withCtrl(x4598) { StoreBanks(List(List(x4441_d0_b0)), List(b2125), x4596).name("x4597").srcCtx("GEMM_Blocked.scala:51:65") } // ParSRAMStore(x4441,List(List(b2125)),List(x4596),List(x4592))
    val x4599 = withCtrl(x4612) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x4599").srcCtx("GEMM_Blocked.scala:51:33") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x4600 = withCtrl(x4612) { CounterChain(List(x4599)).name("x4600").srcCtx("GEMM_Blocked.scala:51:41") } // CounterChainNew(List(x4599))
    val x4611 = withCtrl(x4612) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4600).name("x4611").srcCtx("GEMM_Blocked.scala:51:41") } // UnrolledForeach(List(b1957, b1887, b1842, b1833, b1828),x4600,Block(Const(())),List(List(b2138)),List(List(b2139)))
    val b2138 = withCtrl(x4611) { CounterIter(x4599, None).name("b2138") } // b2138
    val b2139 = withCtrl(x4611) { Const(true).name("b2139") } // b2139
    val x4601 = withCtrl(x4611) { OpDef(op=BitAnd, inputs=List(b2139, b1957)).name("x4601").srcCtx("UnrollingBase.scala:28:66") } // And(b2139,b1957)
    val x4602 = withCtrl(x4611) { OpDef(op=BitAnd, inputs=List(b1887, b1842)).name("x4602").srcCtx("UnrollingBase.scala:28:66") } // And(b1887,b1842)
    val x4603 = withCtrl(x4611) { OpDef(op=BitAnd, inputs=List(b1833, b1828)).name("x4603").srcCtx("UnrollingBase.scala:28:66") } // And(b1833,b1828)
    val x4604 = withCtrl(x4611) { OpDef(op=BitAnd, inputs=List(x4601, x4602)).name("x4604").srcCtx("UnrollingBase.scala:28:66") } // And(x4601,x4602)
    val x4605 = withCtrl(x4611) { OpDef(op=BitAnd, inputs=List(x4604, x4603)).name("x4605").srcCtx("UnrollingBase.scala:28:66") } // And(x4604,x4603)
    val x4606 = withCtrl(x4611) { LoadBanks(List(x4349_d0_b7), List(b1949, b2138)).name("x4606").srcCtx("GEMM_Blocked.scala:51:73") } // ParSRAMLoad(x4349,List(List(b1949, b2138)),List(x4605))
    val x4607 = withCtrl(x4611) { x4606 } // VectorApply(x4606,0)
    val x4608 = withCtrl(x4611) { ReadMem(x4450).name("x4608").srcCtx("GEMM_Blocked.scala:53:16") } // RegRead(x4450)
    val x4609 = withCtrl(x4611) { OpDef(op=FixMul, inputs=List(x4607, x4608)).name("x4609").srcCtx("GEMM_Blocked.scala:51:80") } // FixMul(x4607,x4608)
    val x4610 = withCtrl(x4611) { StoreBanks(List(List(x4442_d0_b0)), List(b2138), x4609).name("x4610").srcCtx("GEMM_Blocked.scala:51:65") } // ParSRAMStore(x4442,List(List(b2138)),List(x4609),List(x4605))
    val x4613 = withCtrl(x4695) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x4613").srcCtx("GEMM_Blocked.scala:53:16") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x4614 = withCtrl(x4695) { CounterChain(List(x4613)).name("x4614").srcCtx("GEMM_Blocked.scala:53:16") } // CounterChainNew(ArrayBuffer(x4613))
    val x4694 = withCtrl(x4695) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4614).name("x4694").srcCtx("GEMM_Blocked.scala:53:16") } // UnrolledForeach(List(),x4614,Block(Const(())),ArrayBuffer(List(b2152)),ArrayBuffer(List(b2153)))
    val b2152 = withCtrl(x4694) { CounterIter(x4613, None).name("b2152") } // b2152
    val b2153 = withCtrl(x4694) { Const(true).name("b2153") } // b2153
    val x4615 = withCtrl(x4694) { OpDef(op=BitAnd, inputs=List(b2153, b1887)).name("x4615").srcCtx("UnrollingBase.scala:28:66") } // And(b2153,b1887)
    val x4616 = withCtrl(x4694) { OpDef(op=BitAnd, inputs=List(b1842, b1833)).name("x4616").srcCtx("UnrollingBase.scala:28:66") } // And(b1842,b1833)
    val x4617 = withCtrl(x4694) { OpDef(op=BitAnd, inputs=List(x4615, x4616)).name("x4617").srcCtx("UnrollingBase.scala:28:66") } // And(x4615,x4616)
    val x4618 = withCtrl(x4694) { OpDef(op=BitAnd, inputs=List(x4617, b1828)).name("x4618").srcCtx("UnrollingBase.scala:28:66") } // And(x4617,b1828)
    val x4619 = withCtrl(x4694) { LoadBanks(List(x4435_d0_b0), List(b2152)).name("x4619").srcCtx("GEMM_Blocked.scala:53:16") } // ParSRAMLoad(x4435,List(ArrayBuffer(b2152)),List(x4618))
    val x4620 = withCtrl(x4694) { x4619 } // VectorApply(x4619,0)
    val x4621 = withCtrl(x4694) { LoadBanks(List(x4436_d0_b0), List(b2152)).name("x4621").srcCtx("GEMM_Blocked.scala:53:16") } // ParSRAMLoad(x4436,List(ArrayBuffer(b2152)),List(x4618))
    val x4622 = withCtrl(x4694) { x4621 } // VectorApply(x4621,0)
    val x4623 = withCtrl(x4694) { LoadBanks(List(x4437_d0_b0), List(b2152)).name("x4623").srcCtx("GEMM_Blocked.scala:53:16") } // ParSRAMLoad(x4437,List(ArrayBuffer(b2152)),List(x4618))
    val x4624 = withCtrl(x4694) { x4623 } // VectorApply(x4623,0)
    val x4625 = withCtrl(x4694) { LoadBanks(List(x4438_d0_b0), List(b2152)).name("x4625").srcCtx("GEMM_Blocked.scala:53:16") } // ParSRAMLoad(x4438,List(ArrayBuffer(b2152)),List(x4618))
    val x4626 = withCtrl(x4694) { x4625 } // VectorApply(x4625,0)
    val x4627 = withCtrl(x4694) { LoadBanks(List(x4439_d0_b0), List(b2152)).name("x4627").srcCtx("GEMM_Blocked.scala:53:16") } // ParSRAMLoad(x4439,List(ArrayBuffer(b2152)),List(x4618))
    val x4628 = withCtrl(x4694) { x4627 } // VectorApply(x4627,0)
    val x4629 = withCtrl(x4694) { LoadBanks(List(x4440_d0_b0), List(b2152)).name("x4629").srcCtx("GEMM_Blocked.scala:53:16") } // ParSRAMLoad(x4440,List(ArrayBuffer(b2152)),List(x4618))
    val x4630 = withCtrl(x4694) { x4629 } // VectorApply(x4629,0)
    val x4631 = withCtrl(x4694) { LoadBanks(List(x4441_d0_b0), List(b2152)).name("x4631").srcCtx("GEMM_Blocked.scala:53:16") } // ParSRAMLoad(x4441,List(ArrayBuffer(b2152)),List(x4618))
    val x4632 = withCtrl(x4694) { x4631 } // VectorApply(x4631,0)
    val x4633 = withCtrl(x4694) { LoadBanks(List(x4442_d0_b0), List(b2152)).name("x4633").srcCtx("GEMM_Blocked.scala:53:16") } // ParSRAMLoad(x4442,List(ArrayBuffer(b2152)),List(x4618))
    val x4634 = withCtrl(x4694) { x4633 } // VectorApply(x4633,0)
    val x4635 = withCtrl(x4694) { LoadBanks(List(x4432_d1_b0), List(b2152)).name("x4635").srcCtx("GEMM_Blocked.scala:53:16") } // ParSRAMLoad(x4432,List(ArrayBuffer(b2152)),List(x4618))
    val x4636 = withCtrl(x4694) { x4635 } // VectorApply(x4635,0)
    val x4637 = withCtrl(x4694) { OpDef(op=BitAnd, inputs=List(b1950, b1887)).name("x4637").srcCtx("GEMM_Blocked.scala:53:16") } // And(b1950,b1887)
    val x4638 = withCtrl(x4694) { OpDef(op=BitAnd, inputs=List(x4637, x4616)).name("x4638").srcCtx("GEMM_Blocked.scala:53:16") } // And(x4637,x4616)
    val x4639 = withCtrl(x4694) { OpDef(op=BitAnd, inputs=List(x4638, b1828)).name("x4639").srcCtx("GEMM_Blocked.scala:53:16") } // And(x4638,b1828)
    def split1 = {
    val x4640 = withCtrl(x4694) { OpDef(op=BitAnd, inputs=List(b1951, b1887)).name("x4640").srcCtx("GEMM_Blocked.scala:53:16") } // And(b1951,b1887)
    val x4641 = withCtrl(x4694) { OpDef(op=BitAnd, inputs=List(x4640, x4616)).name("x4641").srcCtx("GEMM_Blocked.scala:53:16") } // And(x4640,x4616)
    val x4642 = withCtrl(x4694) { OpDef(op=BitAnd, inputs=List(x4641, b1828)).name("x4642").srcCtx("GEMM_Blocked.scala:53:16") } // And(x4641,b1828)
    val x4643 = withCtrl(x4694) { OpDef(op=BitAnd, inputs=List(b1952, b1887)).name("x4643").srcCtx("GEMM_Blocked.scala:53:16") } // And(b1952,b1887)
    val x4644 = withCtrl(x4694) { OpDef(op=BitAnd, inputs=List(x4643, x4616)).name("x4644").srcCtx("GEMM_Blocked.scala:53:16") } // And(x4643,x4616)
    val x4645 = withCtrl(x4694) { OpDef(op=BitAnd, inputs=List(x4644, b1828)).name("x4645").srcCtx("GEMM_Blocked.scala:53:16") } // And(x4644,b1828)
    val x4646 = withCtrl(x4694) { OpDef(op=BitAnd, inputs=List(b1953, b1887)).name("x4646").srcCtx("GEMM_Blocked.scala:53:16") } // And(b1953,b1887)
    val x4647 = withCtrl(x4694) { OpDef(op=BitAnd, inputs=List(x4646, x4616)).name("x4647").srcCtx("GEMM_Blocked.scala:53:16") } // And(x4646,x4616)
    val x4648 = withCtrl(x4694) { OpDef(op=BitAnd, inputs=List(x4647, b1828)).name("x4648").srcCtx("GEMM_Blocked.scala:53:16") } // And(x4647,b1828)
    val x4649 = withCtrl(x4694) { OpDef(op=BitAnd, inputs=List(b1954, b1887)).name("x4649").srcCtx("GEMM_Blocked.scala:53:16") } // And(b1954,b1887)
    val x4650 = withCtrl(x4694) { OpDef(op=BitAnd, inputs=List(x4649, x4616)).name("x4650").srcCtx("GEMM_Blocked.scala:53:16") } // And(x4649,x4616)
    val x4651 = withCtrl(x4694) { OpDef(op=BitAnd, inputs=List(x4650, b1828)).name("x4651").srcCtx("GEMM_Blocked.scala:53:16") } // And(x4650,b1828)
    val x4652 = withCtrl(x4694) { OpDef(op=BitAnd, inputs=List(b1955, b1887)).name("x4652").srcCtx("GEMM_Blocked.scala:53:16") } // And(b1955,b1887)
    val x4653 = withCtrl(x4694) { OpDef(op=BitAnd, inputs=List(x4652, x4616)).name("x4653").srcCtx("GEMM_Blocked.scala:53:16") } // And(x4652,x4616)
    val x4654 = withCtrl(x4694) { OpDef(op=BitAnd, inputs=List(x4653, b1828)).name("x4654").srcCtx("GEMM_Blocked.scala:53:16") } // And(x4653,b1828)
    val x4655 = withCtrl(x4694) { OpDef(op=BitAnd, inputs=List(b1956, b1887)).name("x4655").srcCtx("GEMM_Blocked.scala:53:16") } // And(b1956,b1887)
    val x4656 = withCtrl(x4694) { OpDef(op=BitAnd, inputs=List(x4655, x4616)).name("x4656").srcCtx("GEMM_Blocked.scala:53:16") } // And(x4655,x4616)
    val x4657 = withCtrl(x4694) { OpDef(op=BitAnd, inputs=List(x4656, b1828)).name("x4657").srcCtx("GEMM_Blocked.scala:53:16") } // And(x4656,b1828)
    val x4658 = withCtrl(x4694) { OpDef(op=BitAnd, inputs=List(b1957, b1887)).name("x4658").srcCtx("GEMM_Blocked.scala:53:16") } // And(b1957,b1887)
    val x4659 = withCtrl(x4694) { OpDef(op=BitAnd, inputs=List(x4658, x4616)).name("x4659").srcCtx("GEMM_Blocked.scala:53:16") } // And(x4658,x4616)
    val x4660 = withCtrl(x4694) { OpDef(op=BitAnd, inputs=List(x4659, b1828)).name("x4660").srcCtx("GEMM_Blocked.scala:53:16") } // And(x4659,b1828)
    val x4661 = withCtrl(x4694) { OpDef(op=BitAnd, inputs=List(x4639, x4618)).name("x4661").srcCtx("GEMM_Blocked.scala:53:16") } // And(x4639,x4618)
    val x4662 = withCtrl(x4694) { OpDef(op=BitAnd, inputs=List(x4642, x4618)).name("x4662").srcCtx("GEMM_Blocked.scala:53:16") } // And(x4642,x4618)
    val x4663 = withCtrl(x4694) { OpDef(op=BitAnd, inputs=List(x4645, x4618)).name("x4663").srcCtx("GEMM_Blocked.scala:53:16") } // And(x4645,x4618)
    val x4664 = withCtrl(x4694) { OpDef(op=BitAnd, inputs=List(x4648, x4618)).name("x4664").srcCtx("GEMM_Blocked.scala:53:16") } // And(x4648,x4618)
    val x4665 = withCtrl(x4694) { OpDef(op=BitAnd, inputs=List(x4651, x4618)).name("x4665").srcCtx("GEMM_Blocked.scala:53:16") } // And(x4651,x4618)
    val x4666 = withCtrl(x4694) { OpDef(op=BitAnd, inputs=List(x4654, x4618)).name("x4666").srcCtx("GEMM_Blocked.scala:53:16") } // And(x4654,x4618)
    val x4667 = withCtrl(x4694) { OpDef(op=BitAnd, inputs=List(x4657, x4618)).name("x4667").srcCtx("GEMM_Blocked.scala:53:16") } // And(x4657,x4618)
    val x4668 = withCtrl(x4694) { OpDef(op=BitAnd, inputs=List(x4660, x4618)).name("x4668").srcCtx("GEMM_Blocked.scala:53:16") } // And(x4660,x4618)
    val x4669 = withCtrl(x4694) { OpDef(op=FixAdd, inputs=List(x4620, x4622)).name("x4669").srcCtx("GEMM_Blocked.scala:53:18") } // FixAdd(x4620,x4622)
    val x4670 = withCtrl(x4694) { OpDef(op=MuxOp, inputs=List(x4662, x4669, x4620)).name("x4670").srcCtx("GEMM_Blocked.scala:53:16") } // Mux(x4662,x4669,x4620)
    val x4671 = withCtrl(x4694) { OpDef(op=BitOr, inputs=List(x4661, x4662)).name("x4671").srcCtx("GEMM_Blocked.scala:53:16") } // Or(x4661,x4662)
    val x4672 = withCtrl(x4694) { OpDef(op=FixAdd, inputs=List(x4624, x4626)).name("x4672").srcCtx("GEMM_Blocked.scala:53:18") } // FixAdd(x4624,x4626)
    val x4673 = withCtrl(x4694) { OpDef(op=MuxOp, inputs=List(x4664, x4672, x4624)).name("x4673").srcCtx("GEMM_Blocked.scala:53:16") } // Mux(x4664,x4672,x4624)
    val x4674 = withCtrl(x4694) { OpDef(op=BitOr, inputs=List(x4663, x4664)).name("x4674").srcCtx("GEMM_Blocked.scala:53:16") } // Or(x4663,x4664)
    val x4675 = withCtrl(x4694) { OpDef(op=FixAdd, inputs=List(x4628, x4630)).name("x4675").srcCtx("GEMM_Blocked.scala:53:18") } // FixAdd(x4628,x4630)
    val x4676 = withCtrl(x4694) { OpDef(op=MuxOp, inputs=List(x4666, x4675, x4628)).name("x4676").srcCtx("GEMM_Blocked.scala:53:16") } // Mux(x4666,x4675,x4628)
    val x4677 = withCtrl(x4694) { OpDef(op=BitOr, inputs=List(x4665, x4666)).name("x4677").srcCtx("GEMM_Blocked.scala:53:16") } // Or(x4665,x4666)
    val x4678 = withCtrl(x4694) { OpDef(op=FixAdd, inputs=List(x4632, x4634)).name("x4678").srcCtx("GEMM_Blocked.scala:53:18") } // FixAdd(x4632,x4634)
    val x4679 = withCtrl(x4694) { OpDef(op=MuxOp, inputs=List(x4668, x4678, x4632)).name("x4679").srcCtx("GEMM_Blocked.scala:53:16") } // Mux(x4668,x4678,x4632)
    val x4680 = withCtrl(x4694) { OpDef(op=BitOr, inputs=List(x4667, x4668)).name("x4680").srcCtx("GEMM_Blocked.scala:53:16") } // Or(x4667,x4668)
    val x4681 = withCtrl(x4694) { OpDef(op=FixAdd, inputs=List(x4670, x4673)).name("x4681").srcCtx("GEMM_Blocked.scala:53:18") } // FixAdd(x4670,x4673)
    val x4682 = withCtrl(x4694) { OpDef(op=MuxOp, inputs=List(x4674, x4681, x4670)).name("x4682").srcCtx("GEMM_Blocked.scala:53:16") } // Mux(x4674,x4681,x4670)
    val x4683 = withCtrl(x4694) { OpDef(op=BitOr, inputs=List(x4671, x4674)).name("x4683").srcCtx("GEMM_Blocked.scala:53:16") } // Or(x4671,x4674)
    val x4684 = withCtrl(x4694) { OpDef(op=FixAdd, inputs=List(x4676, x4679)).name("x4684").srcCtx("GEMM_Blocked.scala:53:18") } // FixAdd(x4676,x4679)
    val x4685 = withCtrl(x4694) { OpDef(op=MuxOp, inputs=List(x4680, x4684, x4676)).name("x4685").srcCtx("GEMM_Blocked.scala:53:16") } // Mux(x4680,x4684,x4676)
    val x4686 = withCtrl(x4694) { OpDef(op=BitOr, inputs=List(x4677, x4680)).name("x4686").srcCtx("GEMM_Blocked.scala:53:16") } // Or(x4677,x4680)
    val x4687 = withCtrl(x4694) { OpDef(op=FixAdd, inputs=List(x4682, x4685)).name("x4687").srcCtx("GEMM_Blocked.scala:53:18") } // FixAdd(x4682,x4685)
    val x4688 = withCtrl(x4694) { OpDef(op=MuxOp, inputs=List(x4686, x4687, x4682)).name("x4688").srcCtx("GEMM_Blocked.scala:53:16") } // Mux(x4686,x4687,x4682)
    val x4689 = withCtrl(x4694) { OpDef(op=BitOr, inputs=List(x4683, x4686)).name("x4689").srcCtx("GEMM_Blocked.scala:53:16") } // Or(x4683,x4686)
    val x4690 = withCtrl(x4694) { OpDef(op=FixEql, inputs=List(b1942, Const(0))).name("x4690").srcCtx("GEMM_Blocked.scala:53:16") } // FixEql(b1942,Const(0))
    val x4691 = withCtrl(x4694) { OpDef(op=FixAdd, inputs=List(x4688, x4636)).name("x4691").srcCtx("GEMM_Blocked.scala:53:18") } // FixAdd(x4688,x4636)
    val x4692 = withCtrl(x4694) { OpDef(op=MuxOp, inputs=List(x4690, x4688, x4691)).name("x4692").srcCtx("GEMM_Blocked.scala:53:16") } // Mux(x4690,x4688,x4691)
    val x4693 = withCtrl(x4694) { StoreBanks(List(List(x4432_d0_b0), List(x4432_d1_b0)), List(b2152), x4692).name("x4693").srcCtx("GEMM_Blocked.scala:53:16") } // ParSRAMStore(x4432,List(ArrayBuffer(b2152)),List(x4692),List(x4618))
    antiDepsOf(x4693)=List(x4635)
    val x4696 = withCtrl(x4706) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x4696").srcCtx("GEMM_Blocked.scala:54:31") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x4697 = withCtrl(x4706) { CounterChain(List(x4696)).name("x4697").srcCtx("GEMM_Blocked.scala:54:38") } // CounterChainNew(List(x4696))
    val x4705 = withCtrl(x4706) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4697).name("x4705").srcCtx("GEMM_Blocked.scala:54:38") } // UnrolledForeach(List(b1887, b1842, b1833, b1828),x4697,Block(Const(())),List(List(b2237)),List(List(b2238)))
    val b2237 = withCtrl(x4705) { CounterIter(x4696, None).name("b2237") } // b2237
    val b2238 = withCtrl(x4705) { Const(true).name("b2238") } // b2238
    val x4698 = withCtrl(x4705) { OpDef(op=BitAnd, inputs=List(b2238, b1887)).name("x4698").srcCtx("UnrollingBase.scala:28:66") } // And(b2238,b1887)
    val x4699 = withCtrl(x4705) { OpDef(op=BitAnd, inputs=List(b1842, b1833)).name("x4699").srcCtx("UnrollingBase.scala:28:66") } // And(b1842,b1833)
    val x4700 = withCtrl(x4705) { OpDef(op=BitAnd, inputs=List(x4698, x4699)).name("x4700").srcCtx("UnrollingBase.scala:28:66") } // And(x4698,x4699)
    val x4701 = withCtrl(x4705) { OpDef(op=BitAnd, inputs=List(x4700, b1828)).name("x4701").srcCtx("UnrollingBase.scala:28:66") } // And(x4700,b1828)
    val x4702 = withCtrl(x4705) { LoadBanks(List(x4432_d0_b0), List(b2237)).name("x4702").srcCtx("GEMM_Blocked.scala:54:74") } // ParSRAMLoad(x4432,List(List(b2237)),List(x4701))
    val x4703 = withCtrl(x4705) { x4702 } // VectorApply(x4702,0)
    val x4704 = withCtrl(x4705) { StoreBanks(List(List(x4348_d0_b0)), List(b1886, b2237), x4703).name("x4704").srcCtx("GEMM_Blocked.scala:54:67") } // ParSRAMStore(x4348,List(List(b1886, b2237)),List(x4703),List(x4701))
    val x4707 = withCtrl(x4725) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x4707").srcCtx("GEMM_Blocked.scala:57:12") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x4708 = withCtrl(x4725) { Counter(min=Const(0), max=Const(64), step=Const(1), par=1).name("x4708").srcCtx("GEMM_Blocked.scala:57:12") } // CounterNew(Const(0),Const(64),Const(1),Const(1))
    val x4709 = withCtrl(x4725) { CounterChain(List(x4708,x4707)).name("x4709").srcCtx("GEMM_Blocked.scala:57:12") } // CounterChainNew(ArrayBuffer(x4708, x4707))
    val x4724 = withCtrl(x4725) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4709).name("x4724").srcCtx("GEMM_Blocked.scala:57:12") } // UnrolledForeach(List(),x4709,Block(Const(())),ArrayBuffer(List(b2248), List(b2249)),ArrayBuffer(List(b2250), List(b2251)))
    val b2248 = withCtrl(x4724) { CounterIter(x4708, Some(0)).name("b2248") } // b2248
    val b2250 = withCtrl(x4724) { Const(true).name("b2250") } // b2250
    val b2249 = withCtrl(x4724) { CounterIter(x4707, None).name("b2249") } // b2249
    val b2251 = withCtrl(x4724) { Const(true).name("b2251") } // b2251
    val x4710 = withCtrl(x4724) { OpDef(op=BitAnd, inputs=List(b2250, b2251)).name("x4710").srcCtx("UnrollingBase.scala:28:66") } // And(b2250,b2251)
    val x4711 = withCtrl(x4724) { OpDef(op=BitAnd, inputs=List(b1833, b1828)).name("x4711").srcCtx("UnrollingBase.scala:28:66") } // And(b1833,b1828)
    val x4712 = withCtrl(x4724) { OpDef(op=BitAnd, inputs=List(x4710, x4711)).name("x4712").srcCtx("UnrollingBase.scala:28:66") } // And(x4710,x4711)
    val x4713 = withCtrl(x4724) { LoadBanks(List(x4348_d0_b0), List(b2248, b2249)).name("x4713").srcCtx("GEMM_Blocked.scala:57:12") } // ParSRAMLoad(x4348,List(ArrayBuffer(b2248, b2249)),List(x4712))
    val x4714 = withCtrl(x4724) { x4713 } // VectorApply(x4713,0)
    val x4715 = withCtrl(x4724) { LoadBanks(List(x4344_d1_b0), List(b2248, b2249)).name("x4715").srcCtx("GEMM_Blocked.scala:57:12") } // ParSRAMLoad(x4344,List(ArrayBuffer(b2248, b2249)),List(x4712))
    val x4716 = withCtrl(x4724) { x4715 } // VectorApply(x4715,0)
    val x4717 = withCtrl(x4724) { OpDef(op=BitAnd, inputs=List(b1842, b1833)).name("x4717").srcCtx("GEMM_Blocked.scala:57:12") } // And(b1842,b1833)
    val x4718 = withCtrl(x4724) { OpDef(op=BitAnd, inputs=List(x4717, b1828)).name("x4718").srcCtx("GEMM_Blocked.scala:57:12") } // And(x4717,b1828)
    val x4719 = withCtrl(x4724) { OpDef(op=BitAnd, inputs=List(x4718, x4712)).name("x4719").srcCtx("GEMM_Blocked.scala:57:12") } // And(x4718,x4712)
    val x4720 = withCtrl(x4724) { OpDef(op=FixEql, inputs=List(b1841, Const(0))).name("x4720").srcCtx("GEMM_Blocked.scala:57:12") } // FixEql(b1841,Const(0))
    val x4721 = withCtrl(x4724) { OpDef(op=FixAdd, inputs=List(x4714, x4716)).name("x4721").srcCtx("GEMM_Blocked.scala:57:14") } // FixAdd(x4714,x4716)
    val x4722 = withCtrl(x4724) { OpDef(op=MuxOp, inputs=List(x4720, x4714, x4721)).name("x4722").srcCtx("GEMM_Blocked.scala:57:12") } // Mux(x4720,x4714,x4721)
    val x4723 = withCtrl(x4724) { StoreBanks(List(List(x4344_d0_b0), List(x4344_d1_b0)), List(b2248, b2249), x4722).name("x4723").srcCtx("GEMM_Blocked.scala:57:12") } // ParSRAMStore(x4344,List(ArrayBuffer(b2248, b2249)),List(x4722),List(x4712))
    antiDepsOf(x4723)=List(x4715)
    val x4728 = withCtrl(x4765) { UnitController(style=SeqPipe, level=InnerControl).name("x4728").srcCtx("GEMM_Blocked.scala:38:40") } // UnitPipe(List(b1833, b1828),Block(Const(())))
    val x4726 = withCtrl(x4728) { OpDef(op=FixAdd, inputs=List(b1827, Const(64))).name("x4726").srcCtx("GEMM_Blocked.scala:58:24") } // FixAdd(b1827,Const(64))
    val x4727 = withCtrl(x4728) { OpDef(op=FixAdd, inputs=List(b1832, Const(64))).name("x4727").srcCtx("GEMM_Blocked.scala:58:36") } // FixAdd(b1832,Const(64))
    val x4729 = withCtrl(x4765) { Counter(min=Const(0), max=Const(64), step=Const(1), par=1).name("x4729").srcCtx("GEMM_Blocked.scala:58:48") } // CounterNew(Const(0),Const(64),Const(1),Const(1))
    val x4730 = withCtrl(x4765) { CounterChain(List(x4729)).name("x4730").srcCtx("GEMM_Blocked.scala:58:48") } // CounterChainNew(List(x4729))
    val x4764 = withCtrl(x4765) { LoopController(style=StreamPipe, level=OuterControl, cchain=x4730).name("x4764").srcCtx("GEMM_Blocked.scala:58:48") } // UnrolledForeach(List(b1833, b1828),x4730,Block(Const(())),List(List(b2273)),List(List(b2274)))
    val b2273 = withCtrl(x4764) { CounterIter(x4729, Some(0)).name("b2273") } // b2273
    val b2274 = withCtrl(x4764) { Const(true).name("b2274") } // b2274
    val b4816 = withCtrl(x4764) { StreamOut(field="offset").name("b4816").srcCtx("GEMM_Blocked.scala:58:48") } // x4731 = StreamOutNew(BurstCmdBus)
    isAccum(b4816) = false
    bufferDepthOf(b4816) = 1
    val b4817 = withCtrl(x4764) { StreamOut(field="size").name("b4817").srcCtx("GEMM_Blocked.scala:58:48") } // x4731 = StreamOutNew(BurstCmdBus)
    isAccum(b4817) = false
    bufferDepthOf(b4817) = 1
    val x4732 = withCtrl(x4764) { StreamOut(field="data").name("x4732").srcCtx("GEMM_Blocked.scala:58:48") } // x4732 = StreamOutNew(BurstFullDataBus())
    isAccum(x4732) = false
    bufferDepthOf(x4732) = 1
    val x4733 = withCtrl(x4764) { StreamIn(field="ack").name("x4733").srcCtx("GEMM_Blocked.scala:58:48") } // x4733 = StreamInNew(BurstAckBus)
    isAccum(x4733) = false
    bufferDepthOf(x4733) = 1
    val x4748 = withCtrl(x4764) { UnitController(style=SeqPipe, level=InnerControl).name("x4748").srcCtx("GEMM_Blocked.scala:58:48") } // UnitPipe(List(b2274, b1833, b1828),Block(x4747))
    val x4734 = withCtrl(x4748) { OpDef(op=FixAdd, inputs=List(b1827, b2273)).name("x4734").srcCtx("GEMM_Blocked.scala:58:48") } // FixAdd(b1827,b2273)
    val x4735 = withCtrl(x4748) { x4734 } // FixConvert(x4734,TRUE,_32,_0) (Same Type. No op)
    val x4736 = withCtrl(x4748) { ReadMem(x4322_d0).name("x4736").srcCtx("GEMM_Blocked.scala:30:31") } // RegRead(x4322)
    val x4737 = withCtrl(x4748) { OpDef(op=FixMul, inputs=List(x4735, x4736)).name("x4737").srcCtx("GEMM_Blocked.scala:58:48") } // FixMul(x4735,x4736)
    val x4738 = withCtrl(x4748) { b1832 } // FixConvert(b1832,TRUE,_32,_0) (Same Type. No op)
    val x4739 = withCtrl(x4748) { OpDef(op=FixAdd, inputs=List(x4737, x4738)).name("x4739").srcCtx("GEMM_Blocked.scala:58:48") } // FixAdd(x4737,x4738)
    val x4740 = withCtrl(x4748) { OpDef(op=FixSla, inputs=List(x4739, Const(2))).name("x4740").srcCtx("GEMM_Blocked.scala:58:48") } // FixLsh(x4739,Const(2))
    val x4741 = withCtrl(x4748) { x4740 } // FixConvert(x4740,TRUE,_64,_0)
    val x4742 = withCtrl(x4748) { DramAddress(x4334).name("x4742").srcCtx("GEMM_Blocked.scala:58:48") } // GetDRAMAddress(x4334)
    val x4744_x4743 = withCtrl(x4748) { OpDef(op=FixAdd, inputs=List(x4741, x4742)).name("x4744_x4743").srcCtx("GEMM_Blocked.scala:58:48") } // FixAdd(x4741,x4742)
    // x4744 = SimpleStruct(ArrayBuffer((offset,x4743), (size,Const(256)), (isLoad,Const(false))))
    val x4745 = withCtrl(x4748) { OpDef(op=BitAnd, inputs=List(b2274, b1833)).name("x4745").srcCtx("UnrollingBase.scala:28:66") } // And(b2274,b1833)
    val x4746 = withCtrl(x4748) { OpDef(op=BitAnd, inputs=List(x4745, b1828)).name("x4746").srcCtx("UnrollingBase.scala:28:66") } // And(x4745,b1828)
    val x4747_b4818_b4816 = withCtrl(x4748) { WriteMem(b4816, x4744_x4743).name("x4747_b4818_b4816").srcCtx("GEMM_Blocked.scala:58:48") } // StreamWrite(x4731,x4744,x4746)
    val x4747_b4819_b4817 = withCtrl(x4748) { WriteMem(b4817, Const(256)).name("x4747_b4819_b4817").srcCtx("GEMM_Blocked.scala:58:48") } // StreamWrite(x4731,x4744,x4746)
    val x4749 = withCtrl(x4764) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x4749").srcCtx("GEMM_Blocked.scala:58:48") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x4750 = withCtrl(x4764) { CounterChain(List(x4749)).name("x4750").srcCtx("GEMM_Blocked.scala:58:48") } // CounterChainNew(List(x4749))
    val x4758 = withCtrl(x4764) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4750).name("x4758").srcCtx("GEMM_Blocked.scala:58:48") } // UnrolledForeach(List(b2274, b1833, b1828),x4750,Block(Const(())),List(List(b2295)),List(List(b2296)))
    val b2295 = withCtrl(x4758) { CounterIter(x4749, None).name("b2295") } // b2295
    val b2296 = withCtrl(x4758) { Const(true).name("b2296") } // b2296
    val x4751 = withCtrl(x4758) { OpDef(op=BitAnd, inputs=List(b2296, b2274)).name("x4751").srcCtx("UnrollingBase.scala:28:66") } // And(b2296,b2274)
    val x4752 = withCtrl(x4758) { OpDef(op=BitAnd, inputs=List(b1833, b1828)).name("x4752").srcCtx("UnrollingBase.scala:28:66") } // And(b1833,b1828)
    val x4753 = withCtrl(x4758) { OpDef(op=BitAnd, inputs=List(x4751, x4752)).name("x4753").srcCtx("UnrollingBase.scala:28:66") } // And(x4751,x4752)
    val x4754 = withCtrl(x4758) { LoadBanks(List(x4344_d0_b0), List(b2273, b2295)).name("x4754").srcCtx("GEMM_Blocked.scala:58:48") } // ParSRAMLoad(x4344,List(List(b2273, b2295)),List(x4753))
    val x4756_x4755 = withCtrl(x4758) { x4754 } // VectorApply(x4754,0)
    // x4756 = SimpleStruct(ArrayBuffer((_1,x4755), (_2,Const(true))))
    val x4757_x4757_x4732 = withCtrl(x4758) { WriteMem(x4732, x4756_x4755).name("x4757_x4757_x4732").srcCtx("GEMM_Blocked.scala:58:48") } // ParStreamWrite(x4732,List(x4756),List(x4753))
    val x4759 = withCtrl(x4764) { FringeDenseStore(dram=List(x4334), cmdStream=List(b4816, b4817), dataStream=List(x4732), ackStream=List(x4733)).name("x4759").srcCtx("GEMM_Blocked.scala:58:48") } // FringeDenseStore(x4334,x4731,x4732,x4733)
    val x4763 = withCtrl(x4764) { UnitController(style=SeqPipe, level=InnerControl).name("x4763").srcCtx("GEMM_Blocked.scala:58:48") } // UnitPipe(List(b2274, b1833, b1828),Block(Const(())))
    val x4760 = withCtrl(x4763) { OpDef(op=BitAnd, inputs=List(b2274, b1833)).name("x4760").srcCtx("UnrollingBase.scala:28:66") } // And(b2274,b1833)
    val x4761 = withCtrl(x4763) { OpDef(op=BitAnd, inputs=List(x4760, b1828)).name("x4761").srcCtx("UnrollingBase.scala:28:66") } // And(x4760,b1828)
    val x4762_x4762 = withCtrl(x4763) { ReadMem(x4733).name("x4762_x4762").srcCtx("GEMM_Blocked.scala:58:48") } // StreamRead(x4733,x4761)
    }; split1
    
  }
}
