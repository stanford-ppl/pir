import pir._
import pir.node._
import arch._
import prism.enums._

object Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1 extends PIRApp {
  def main(implicit design:PIRDesign) = {
    import design.pirmeta._
    val x6057 = withCtrl(design.top.topController) { ArgIn(init=0).name("x6057").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:27:22:iters") } // ArgInNew(Const(0))
    isAccum(x6057) = false
    bufferDepthOf(x6057) = 1
    boundOf(x6057) = 2
    val x6058 = withCtrl(design.top.topController) { ArgIn(init=0).name("x6058").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:28:22:N") } // ArgInNew(Const(0))
    isAccum(x6058) = false
    bufferDepthOf(x6058) = 1
    boundOf(x6058) = 16384
    val x6061 = withCtrl(design.top.topController) { ReadMem(x6058).name("x6061").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:33:26") } // RegRead(x6058)
    val x6062 = withCtrl(design.top.topController) { DRAM(dims=List(x6061, Const(64))).name("x6062").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:33:25:points") } // x6062 = DRAMNew(ArrayBuffer(x6061, Const(64)),Const(0))
    val x6063 = withCtrl(design.top.topController) { DRAM(dims=List(Const(64), Const(64))).name("x6063").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:34:28:centroids") } // x6063 = DRAMNew(ArrayBuffer(Const(64), Const(64)),Const(0))
    val x6564 = withCtrl(design.top.topController) { UnitController(style=SeqPipe, level=OuterControl).name("x6564").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:38:11") } // Hwblock(Block(Const(())),false)
    val x6067_d0_b0 = withCtrl(x6564) { SRAM(size=4096, banking=Strided(banks=16, stride=1)).name("x6067_d0_b0").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:39:24:cts") } // x6067 = SRAMNew(ArrayBuffer(Const(64), Const(64)))
    isAccum(x6067_d0_b0) = false
    bufferDepthOf(x6067_d0_b0) = 1
    staticDimsOf(x6067_d0_b0) = List(64, 64)
    val x6067_d1_b0 = withCtrl(x6564) { SRAM(size=4096, banking=Strided(banks=16, stride=1)).name("x6067_d1_b0").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:39:24:cts") } // x6067 = SRAMNew(ArrayBuffer(Const(64), Const(64)))
    isAccum(x6067_d1_b0) = false
    bufferDepthOf(x6067_d1_b0) = 1
    staticDimsOf(x6067_d1_b0) = List(64, 64)
    val x6067_d2_b0 = withCtrl(x6564) { SRAM(size=4096, banking=Strided(banks=16, stride=1)).name("x6067_d2_b0").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:39:24:cts") } // x6067 = SRAMNew(ArrayBuffer(Const(64), Const(64)))
    isAccum(x6067_d2_b0) = false
    bufferDepthOf(x6067_d2_b0) = 1
    staticDimsOf(x6067_d2_b0) = List(64, 64)
    val x6067_d3_b0 = withCtrl(x6564) { SRAM(size=4096, banking=Strided(banks=16, stride=1)).name("x6067_d3_b0").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:39:24:cts") } // x6067 = SRAMNew(ArrayBuffer(Const(64), Const(64)))
    isAccum(x6067_d3_b0) = false
    bufferDepthOf(x6067_d3_b0) = 1
    staticDimsOf(x6067_d3_b0) = List(64, 64)
    val x6067_d4_b0 = withCtrl(x6564) { SRAM(size=4096, banking=Strided(banks=16, stride=1)).name("x6067_d4_b0").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:39:24:cts") } // x6067 = SRAMNew(ArrayBuffer(Const(64), Const(64)))
    isAccum(x6067_d4_b0) = false
    bufferDepthOf(x6067_d4_b0) = 1
    staticDimsOf(x6067_d4_b0) = List(64, 64)
    val x6068 = withCtrl(x6564) { Counter(min=Const(0), max=Const(64), step=Const(1), par=1).name("x6068").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:41:11") } // CounterNew(Const(0),Const(64),Const(1),Const(1))
    val x6069 = withCtrl(x6564) { CounterChain(List(x6068)).name("x6069").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:41:11") } // CounterChainNew(List(x6068))
    val x6091 = withCtrl(x6564) { LoopController(style=StreamPipe, level=OuterControl, cchain=x6069).name("x6091").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:41:11") } // UnrolledForeach(List(Const(true)),x6069,Block(Const(())),List(List(b2881)),List(List(b2882)))
    val b2881 = withCtrl(x6091) { CounterIter(x6068, Some(0)).name("b2881") } // b2881
    val b2882 = withCtrl(x6091) { Const(true).name("b2882") } // b2882
    val b6668 = withCtrl(x6091) { StreamOut(field="offset").name("b6668").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:41:11") } // x6070 = StreamOutNew(BurstCmdBus)
    isAccum(b6668) = false
    bufferDepthOf(b6668) = 1
    val b6669 = withCtrl(x6091) { StreamOut(field="size").name("b6669").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:41:11") } // x6070 = StreamOutNew(BurstCmdBus)
    isAccum(b6669) = false
    bufferDepthOf(b6669) = 1
    val x6071 = withCtrl(x6091) { StreamIn(field="data").name("x6071").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:41:11") } // x6071 = StreamInNew(BurstDataBus())
    isAccum(x6071) = false
    bufferDepthOf(x6071) = 1
    val x6082 = withCtrl(x6091) { UnitController(style=SeqPipe, level=InnerControl).name("x6082").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:41:11") } // UnitPipe(List(b2882),Block(x6081))
    val x6072 = withCtrl(x6082) { b2881 } // FixConvert(b2881,TRUE,_32,_0) (Same Type. No op)
    val x6073 = withCtrl(x6082) { OpDef(op=FixSla, inputs=List(x6072, Const(6))).name("x6073").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:41:11") } // FixLsh(x6072,Const(6))
    val x6074 = withCtrl(x6082) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x6075 = withCtrl(x6082) { OpDef(op=FixAdd, inputs=List(x6073, x6074)).name("x6075").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:41:11") } // FixAdd(x6073,x6074)
    val x6076 = withCtrl(x6082) { OpDef(op=FixSla, inputs=List(x6075, Const(2))).name("x6076").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:41:11") } // FixLsh(x6075,Const(2))
    val x6077 = withCtrl(x6082) { x6076 } // FixConvert(x6076,TRUE,_64,_0)
    val x6078 = withCtrl(x6082) { DramAddress(x6062).name("x6078").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:41:11") } // GetDRAMAddress(x6062)
    val x6080_x6079 = withCtrl(x6082) { OpDef(op=FixAdd, inputs=List(x6077, x6078)).name("x6080_x6079").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:41:11") } // FixAdd(x6077,x6078)
    // x6080 = SimpleStruct(ArrayBuffer((offset,x6079), (size,Const(256)), (isLoad,Const(true))))
    val x6081_b6670_b6668 = withCtrl(x6082) { WriteMem(b6668, x6080_x6079).name("x6081_b6670_b6668").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:41:11") } // StreamWrite(x6070,x6080,b2882)
    val x6081_b6671_b6669 = withCtrl(x6082) { WriteMem(b6669, Const(256)).name("x6081_b6671_b6669").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:41:11") } // StreamWrite(x6070,x6080,b2882)
    val x6083 = withCtrl(x6091) { FringeDenseLoad(dram=List(x6062), cmdStream=List(b6668, b6669), dataStream=List(x6071)).name("x6083").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:41:11") } // FringeDenseLoad(x6062,x6070,x6071)
    val x6084 = withCtrl(x6091) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x6084").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:41:11") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x6085 = withCtrl(x6091) { CounterChain(List(x6084)).name("x6085").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:41:11") } // CounterChainNew(List(x6084))
    val x6090 = withCtrl(x6091) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6085).name("x6090").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:41:11") } // UnrolledForeach(List(b2882),x6085,Block(Const(())),List(List(b2899)),List(List(b2900)))
    val b2899 = withCtrl(x6090) { CounterIter(x6084, None).name("b2899") } // b2899
    val b2900 = withCtrl(x6090) { Const(true).name("b2900") } // b2900
    val x6086 = withCtrl(x6090) { OpDef(op=BitAnd, inputs=List(b2900, b2882)).name("x6086").srcCtx("UnrollingBase.scala:28:66") } // And(b2900,b2882)
    val x6087_x6087 = withCtrl(x6090) { ReadMem(x6071).name("x6087_x6087").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:41:11") } // ParStreamRead(x6071,List(x6086))
    val x6088_x6088 = withCtrl(x6090) { x6087_x6087 } // VectorApply(x6087,0)
    val x6089 = withCtrl(x6090) { StoreBanks(List(List(x6067_d0_b0), List(x6067_d1_b0), List(x6067_d2_b0), List(x6067_d3_b0), List(x6067_d4_b0)), List(b2881, b2899), x6088_x6088).name("x6089").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:41:11") } // ParSRAMStore(x6067,List(List(b2881, b2899)),List(x6088),List(x6086))
    val x6092 = withCtrl(x6564) { ReadMem(x6057).name("x6092").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:43:26") } // RegRead(x6057)
    val x6093 = withCtrl(x6564) { Counter(min=Const(0), max=x6092, step=Const(1), par=1).name("x6093").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:43:32") } // CounterNew(Const(0),x6092,Const(1),Const(1))
    val x6094 = withCtrl(x6564) { CounterChain(List(x6093)).name("x6094").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:43:37") } // CounterChainNew(List(x6093))
    val x6535 = withCtrl(x6564) { LoopController(style=SeqPipe, level=OuterControl, cchain=x6094).name("x6535").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:43:37") } // UnrolledForeach(List(Const(true)),x6094,Block(Const(())),List(List(b2910)),List(List(b2911)))
    val b2910 = withCtrl(x6535) { CounterIter(x6093, Some(0)).name("b2910") } // b2910
    val b2911 = withCtrl(x6535) { Const(true).name("b2911") } // b2911
    val x6095_d0_b0 = withCtrl(x6535) { SRAM(size=4096, banking=Strided(banks=16, stride=1)).name("x6095_d0_b0").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:44:41:newCents") } // x6095 = SRAMNew(ArrayBuffer(Const(64), Const(64)))
    isAccum(x6095_d0_b0) = false
    bufferDepthOf(x6095_d0_b0) = 1
    staticDimsOf(x6095_d0_b0) = List(64, 64)
    val x6095_d1_b0 = withCtrl(x6535) { SRAM(size=4096, banking=Strided(banks=16, stride=1)).name("x6095_d1_b0").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:44:41:newCents") } // x6095 = SRAMNew(ArrayBuffer(Const(64), Const(64)))
    isAccum(x6095_d1_b0) = false
    bufferDepthOf(x6095_d1_b0) = 1
    staticDimsOf(x6095_d1_b0) = List(64, 64)
    val x6095_d2_b0 = withCtrl(x6535) { SRAM(size=4096, banking=Strided(banks=16, stride=1)).name("x6095_d2_b0").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:44:41:newCents") } // x6095 = SRAMNew(ArrayBuffer(Const(64), Const(64)))
    isAccum(x6095_d2_b0) = true
    bufferDepthOf(x6095_d2_b0) = 1
    staticDimsOf(x6095_d2_b0) = List(64, 64)
    val x6096 = withCtrl(x6535) { ReadMem(x6058).name("x6096").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:44:55") } // RegRead(x6058)
    val x6097 = withCtrl(x6535) { Counter(min=Const(0), max=x6096, step=Const(1024), par=1).name("x6097").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:44:63") } // CounterNew(Const(0),x6096,Const(1024),Const(1))
    val x6098 = withCtrl(x6535) { CounterChain(List(x6097)).name("x6098").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:65:10") } // CounterChainNew(List(x6097))
    val x6513 = withCtrl(x6535) { LoopController(style=MetaPipe, level=OuterControl, cchain=x6098).name("x6513").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:65:10") } // UnrolledReduce(List(b2911),x6098,x6095,Block((x6095) => Const(())),List(List(b2919)),List(List(b2920)))
    val b2919 = withCtrl(x6513) { CounterIter(x6097, Some(0)).name("b2919") } // b2919
    val b2920 = withCtrl(x6513) { Const(true).name("b2920") } // b2920
    val x6099_d0_b0 = withCtrl(x6513) { SRAM(size=16384, banking=Strided(banks=16, stride=1)).name("x6099_d0_b0").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:45:28:pts") } // x6099 = SRAMNew(ArrayBuffer(Const(1024), Const(64)))
    isAccum(x6099_d0_b0) = false
    bufferDepthOf(x6099_d0_b0) = 2
    staticDimsOf(x6099_d0_b0) = List(1024, 64)
    val x6099_d0_b1 = withCtrl(x6513) { SRAM(size=16384, banking=Strided(banks=16, stride=1)).name("x6099_d0_b1").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:45:28:pts") } // x6099 = SRAMNew(ArrayBuffer(Const(1024), Const(64)))
    isAccum(x6099_d0_b1) = false
    bufferDepthOf(x6099_d0_b1) = 2
    staticDimsOf(x6099_d0_b1) = List(1024, 64)
    val x6099_d0_b2 = withCtrl(x6513) { SRAM(size=16384, banking=Strided(banks=16, stride=1)).name("x6099_d0_b2").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:45:28:pts") } // x6099 = SRAMNew(ArrayBuffer(Const(1024), Const(64)))
    isAccum(x6099_d0_b2) = false
    bufferDepthOf(x6099_d0_b2) = 2
    staticDimsOf(x6099_d0_b2) = List(1024, 64)
    val x6099_d0_b3 = withCtrl(x6513) { SRAM(size=16384, banking=Strided(banks=16, stride=1)).name("x6099_d0_b3").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:45:28:pts") } // x6099 = SRAMNew(ArrayBuffer(Const(1024), Const(64)))
    isAccum(x6099_d0_b3) = false
    bufferDepthOf(x6099_d0_b3) = 2
    staticDimsOf(x6099_d0_b3) = List(1024, 64)
    val x6099_d1_b0 = withCtrl(x6513) { SRAM(size=16384, banking=Strided(banks=16, stride=1)).name("x6099_d1_b0").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:45:28:pts") } // x6099 = SRAMNew(ArrayBuffer(Const(1024), Const(64)))
    isAccum(x6099_d1_b0) = false
    bufferDepthOf(x6099_d1_b0) = 2
    staticDimsOf(x6099_d1_b0) = List(1024, 64)
    val x6099_d1_b1 = withCtrl(x6513) { SRAM(size=16384, banking=Strided(banks=16, stride=1)).name("x6099_d1_b1").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:45:28:pts") } // x6099 = SRAMNew(ArrayBuffer(Const(1024), Const(64)))
    isAccum(x6099_d1_b1) = false
    bufferDepthOf(x6099_d1_b1) = 2
    staticDimsOf(x6099_d1_b1) = List(1024, 64)
    val x6099_d1_b2 = withCtrl(x6513) { SRAM(size=16384, banking=Strided(banks=16, stride=1)).name("x6099_d1_b2").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:45:28:pts") } // x6099 = SRAMNew(ArrayBuffer(Const(1024), Const(64)))
    isAccum(x6099_d1_b2) = false
    bufferDepthOf(x6099_d1_b2) = 2
    staticDimsOf(x6099_d1_b2) = List(1024, 64)
    val x6099_d1_b3 = withCtrl(x6513) { SRAM(size=16384, banking=Strided(banks=16, stride=1)).name("x6099_d1_b3").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:45:28:pts") } // x6099 = SRAMNew(ArrayBuffer(Const(1024), Const(64)))
    isAccum(x6099_d1_b3) = false
    bufferDepthOf(x6099_d1_b3) = 2
    staticDimsOf(x6099_d1_b3) = List(1024, 64)
    val x6101 = withCtrl(x6513) { UnitController(style=SeqPipe, level=InnerControl).name("x6101").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:65:10") } // UnitPipe(List(b2920, b2911),Block(Const(())))
    val x6100 = withCtrl(x6101) { OpDef(op=FixAdd, inputs=List(b2919, Const(1024))).name("x6100").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:46:31") } // FixAdd(b2919,Const(1024))
    val x6102 = withCtrl(x6513) { Counter(min=Const(0), max=Const(1024), step=Const(1), par=1).name("x6102").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:46:15") } // CounterNew(Const(0),Const(1024),Const(1),Const(1))
    val x6103 = withCtrl(x6513) { CounterChain(List(x6102)).name("x6103").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:46:15") } // CounterChainNew(List(x6102))
    val x6130 = withCtrl(x6513) { LoopController(style=StreamPipe, level=OuterControl, cchain=x6103).name("x6130").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:46:15") } // UnrolledForeach(List(b2920, b2911),x6103,Block(Const(())),List(List(b2926)),List(List(b2927)))
    val b2926 = withCtrl(x6130) { CounterIter(x6102, Some(0)).name("b2926") } // b2926
    val b2927 = withCtrl(x6130) { Const(true).name("b2927") } // b2927
    val b6672 = withCtrl(x6130) { StreamOut(field="offset").name("b6672").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:46:15") } // x6104 = StreamOutNew(BurstCmdBus)
    isAccum(b6672) = false
    bufferDepthOf(b6672) = 1
    val b6673 = withCtrl(x6130) { StreamOut(field="size").name("b6673").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:46:15") } // x6104 = StreamOutNew(BurstCmdBus)
    isAccum(b6673) = false
    bufferDepthOf(b6673) = 1
    val x6105 = withCtrl(x6130) { StreamIn(field="data").name("x6105").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:46:15") } // x6105 = StreamInNew(BurstDataBus())
    isAccum(x6105) = false
    bufferDepthOf(x6105) = 1
    val x6119 = withCtrl(x6130) { UnitController(style=SeqPipe, level=InnerControl).name("x6119").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:46:15") } // UnitPipe(List(b2927, b2920, b2911),Block(x6118))
    val x6106 = withCtrl(x6119) { OpDef(op=FixAdd, inputs=List(b2919, b2926)).name("x6106").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:46:15") } // FixAdd(b2919,b2926)
    val x6107 = withCtrl(x6119) { x6106 } // FixConvert(x6106,TRUE,_32,_0) (Same Type. No op)
    val x6108 = withCtrl(x6119) { OpDef(op=FixSla, inputs=List(x6107, Const(6))).name("x6108").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:46:15") } // FixLsh(x6107,Const(6))
    val x6109 = withCtrl(x6119) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x6110 = withCtrl(x6119) { OpDef(op=FixAdd, inputs=List(x6108, x6109)).name("x6110").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:46:15") } // FixAdd(x6108,x6109)
    val x6111 = withCtrl(x6119) { OpDef(op=FixSla, inputs=List(x6110, Const(2))).name("x6111").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:46:15") } // FixLsh(x6110,Const(2))
    val x6112 = withCtrl(x6119) { x6111 } // FixConvert(x6111,TRUE,_64,_0)
    val x6113 = withCtrl(x6119) { DramAddress(x6062).name("x6113").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:46:15") } // GetDRAMAddress(x6062)
    val x6115_x6114 = withCtrl(x6119) { OpDef(op=FixAdd, inputs=List(x6112, x6113)).name("x6115_x6114").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:46:15") } // FixAdd(x6112,x6113)
    // x6115 = SimpleStruct(ArrayBuffer((offset,x6114), (size,Const(256)), (isLoad,Const(true))))
    val x6116 = withCtrl(x6119) { OpDef(op=BitAnd, inputs=List(b2927, b2920)).name("x6116").srcCtx("UnrollingBase.scala:28:66") } // And(b2927,b2920)
    val x6117 = withCtrl(x6119) { OpDef(op=BitAnd, inputs=List(x6116, b2911)).name("x6117").srcCtx("UnrollingBase.scala:28:66") } // And(x6116,b2911)
    val x6118_b6674_b6672 = withCtrl(x6119) { WriteMem(b6672, x6115_x6114).name("x6118_b6674_b6672").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:46:15") } // StreamWrite(x6104,x6115,x6117)
    val x6118_b6675_b6673 = withCtrl(x6119) { WriteMem(b6673, Const(256)).name("x6118_b6675_b6673").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:46:15") } // StreamWrite(x6104,x6115,x6117)
    val x6120 = withCtrl(x6130) { FringeDenseLoad(dram=List(x6062), cmdStream=List(b6672, b6673), dataStream=List(x6105)).name("x6120").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:46:15") } // FringeDenseLoad(x6062,x6104,x6105)
    val x6121 = withCtrl(x6130) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x6121").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:46:15") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x6122 = withCtrl(x6130) { CounterChain(List(x6121)).name("x6122").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:46:15") } // CounterChainNew(List(x6121))
    val x6129 = withCtrl(x6130) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6122).name("x6129").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:46:15") } // UnrolledForeach(List(b2927, b2920, b2911),x6122,Block(Const(())),List(List(b2947)),List(List(b2948)))
    val b2947 = withCtrl(x6129) { CounterIter(x6121, None).name("b2947") } // b2947
    val b2948 = withCtrl(x6129) { Const(true).name("b2948") } // b2948
    val x6123 = withCtrl(x6129) { OpDef(op=BitAnd, inputs=List(b2948, b2927)).name("x6123").srcCtx("UnrollingBase.scala:28:66") } // And(b2948,b2927)
    val x6124 = withCtrl(x6129) { OpDef(op=BitAnd, inputs=List(b2920, b2911)).name("x6124").srcCtx("UnrollingBase.scala:28:66") } // And(b2920,b2911)
    val x6125 = withCtrl(x6129) { OpDef(op=BitAnd, inputs=List(x6123, x6124)).name("x6125").srcCtx("UnrollingBase.scala:28:66") } // And(x6123,x6124)
    val x6126_x6126 = withCtrl(x6129) { ReadMem(x6105).name("x6126_x6126").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:46:15") } // ParStreamRead(x6105,List(x6125))
    val x6127_x6127 = withCtrl(x6129) { x6126_x6126 } // VectorApply(x6126,0)
    val x6128 = withCtrl(x6129) { StoreBanks(List(List(x6099_d0_b0, x6099_d0_b1, x6099_d0_b2, x6099_d0_b3), List(x6099_d1_b0, x6099_d1_b1, x6099_d1_b2, x6099_d1_b3)), List(b2926, b2947), x6127_x6127).name("x6128").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:46:15") } // ParSRAMStore(x6099,List(List(b2926, b2947)),List(x6127),List(x6125))
    val x6131_d0_b0 = withCtrl(x6513) { SRAM(size=4096, banking=Strided(banks=16, stride=1)).name("x6131_d0_b0").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:48:28") } // x6131 = SRAMNew(ArrayBuffer(Const(64), Const(64)))
    isAccum(x6131_d0_b0) = false
    bufferDepthOf(x6131_d0_b0) = 2
    staticDimsOf(x6131_d0_b0) = List(64, 64)
    val x6131_d1_b0 = withCtrl(x6513) { SRAM(size=4096, banking=Strided(banks=16, stride=1)).name("x6131_d1_b0").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:48:28") } // x6131 = SRAMNew(ArrayBuffer(Const(64), Const(64)))
    isAccum(x6131_d1_b0) = true
    bufferDepthOf(x6131_d1_b0) = 1
    staticDimsOf(x6131_d1_b0) = List(64, 64)
    val x6132 = withCtrl(x6513) { Counter(min=Const(0), max=Const(1024), step=Const(1), par=4).name("x6132").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:48:45") } // CounterNew(Const(0),Const(1024),Const(1),Const(4))
    val x6133 = withCtrl(x6513) { CounterChain(List(x6132)).name("x6133").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:64:12") } // CounterChainNew(List(x6132))
    val x6496 = withCtrl(x6513) { LoopController(style=MetaPipe, level=OuterControl, cchain=x6133).name("x6496").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:64:12") } // UnrolledReduce(List(b2920, b2911),x6133,x6131,Block((x6131) => Const(())),List(List(b2963, b2964, b2965, b2966)),List(List(b2967, b2968, b2969, b2970)))
    val b2963 = withCtrl(x6496) { CounterIter(x6132, Some(0)).name("b2963") } // b2963
    val b2967 = withCtrl(x6496) { Const(true).name("b2967") } // b2967
    val b2964 = withCtrl(x6496) { CounterIter(x6132, Some(1)).name("b2964") } // b2964
    val b2968 = withCtrl(x6496) { Const(true).name("b2968") } // b2968
    val b2965 = withCtrl(x6496) { CounterIter(x6132, Some(2)).name("b2965") } // b2965
    val b2969 = withCtrl(x6496) { Const(true).name("b2969") } // b2969
    val b2966 = withCtrl(x6496) { CounterIter(x6132, Some(3)).name("b2966") } // b2966
    val b2970 = withCtrl(x6496) { Const(true).name("b2970") } // b2970
    val x6134_d0_b0 = withCtrl(x6496) { SRAM(size=64, banking=Strided(banks=16, stride=1)).name("x6134_d0_b0").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:49:32:dists") } // x6134 = SRAMNew(ArrayBuffer(Const(64)))
    isAccum(x6134_d0_b0) = false
    bufferDepthOf(x6134_d0_b0) = 3
    staticDimsOf(x6134_d0_b0) = List(64)
    val x6134_d1_b0 = withCtrl(x6496) { SRAM(size=64, banking=Strided(banks=16, stride=1)).name("x6134_d1_b0").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:49:32:dists") } // x6134 = SRAMNew(ArrayBuffer(Const(64)))
    isAccum(x6134_d1_b0) = false
    bufferDepthOf(x6134_d1_b0) = 2
    staticDimsOf(x6134_d1_b0) = List(64)
    val x6135_d0_b0 = withCtrl(x6496) { SRAM(size=64, banking=Strided(banks=16, stride=1)).name("x6135_d0_b0").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:49:32:dists") } // x6135 = SRAMNew(ArrayBuffer(Const(64)))
    isAccum(x6135_d0_b0) = false
    bufferDepthOf(x6135_d0_b0) = 3
    staticDimsOf(x6135_d0_b0) = List(64)
    val x6135_d1_b0 = withCtrl(x6496) { SRAM(size=64, banking=Strided(banks=16, stride=1)).name("x6135_d1_b0").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:49:32:dists") } // x6135 = SRAMNew(ArrayBuffer(Const(64)))
    isAccum(x6135_d1_b0) = false
    bufferDepthOf(x6135_d1_b0) = 2
    staticDimsOf(x6135_d1_b0) = List(64)
    val x6136_d0_b0 = withCtrl(x6496) { SRAM(size=64, banking=Strided(banks=16, stride=1)).name("x6136_d0_b0").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:49:32:dists") } // x6136 = SRAMNew(ArrayBuffer(Const(64)))
    isAccum(x6136_d0_b0) = false
    bufferDepthOf(x6136_d0_b0) = 3
    staticDimsOf(x6136_d0_b0) = List(64)
    val x6136_d1_b0 = withCtrl(x6496) { SRAM(size=64, banking=Strided(banks=16, stride=1)).name("x6136_d1_b0").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:49:32:dists") } // x6136 = SRAMNew(ArrayBuffer(Const(64)))
    isAccum(x6136_d1_b0) = false
    bufferDepthOf(x6136_d1_b0) = 2
    staticDimsOf(x6136_d1_b0) = List(64)
    val x6137_d0_b0 = withCtrl(x6496) { SRAM(size=64, banking=Strided(banks=16, stride=1)).name("x6137_d0_b0").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:49:32:dists") } // x6137 = SRAMNew(ArrayBuffer(Const(64)))
    isAccum(x6137_d0_b0) = false
    bufferDepthOf(x6137_d0_b0) = 3
    staticDimsOf(x6137_d0_b0) = List(64)
    val x6137_d1_b0 = withCtrl(x6496) { SRAM(size=64, banking=Strided(banks=16, stride=1)).name("x6137_d1_b0").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:49:32:dists") } // x6137 = SRAMNew(ArrayBuffer(Const(64)))
    isAccum(x6137_d1_b0) = false
    bufferDepthOf(x6137_d1_b0) = 2
    staticDimsOf(x6137_d1_b0) = List(64)
    val x6258 = withCtrl(x6496) { UnitController(style=ForkJoin, level=OuterControl).name("x6258").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b2920, b2911),Block(Const(())))
    val x6138 = withCtrl(x6258) { Counter(min=Const(0), max=Const(64), step=Const(1), par=1).name("x6138").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:50:28") } // CounterNew(Const(0),Const(64),Const(1),Const(1))
    val x6139 = withCtrl(x6258) { CounterChain(List(x6138)).name("x6139").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:50:37") } // CounterChainNew(List(x6138))
    val x6167 = withCtrl(x6258) { LoopController(style=MetaPipe, level=OuterControl, cchain=x6139).name("x6167").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:50:37") } // UnrolledForeach(List(b2967, b2920, b2911),x6139,Block(Const(())),List(List(b2983)),List(List(b2984)))
    val b2983 = withCtrl(x6167) { CounterIter(x6138, Some(0)).name("b2983") } // b2983
    val b2984 = withCtrl(x6167) { Const(true).name("b2984") } // b2984
    val x6140_d0 = withCtrl(x6167) { Reg(init=Some(0)).name("x6140_d0").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:51:36:dist") } // x6140 = RegNew(Const(0))
    isAccum(x6140_d0) = false
    bufferDepthOf(x6140_d0) = 2
    val x6140_d1 = withCtrl(x6167) { Reg(init=Some(0)).name("x6140_d1").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:51:36:dist") } // x6140 = RegNew(Const(0))
    isAccum(x6140_d1) = true
    bufferDepthOf(x6140_d1) = 1
    val x6141 = withCtrl(x6167) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x6141").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:51:43") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x6142 = withCtrl(x6167) { CounterChain(List(x6141)).name("x6142").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:51:86") } // CounterChainNew(List(x6141))
    val x6160 = withCtrl(x6167) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6142).name("x6160").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:51:86") } // UnrolledReduce(List(b2984, b2967, b2920, b2911),x6142,x6140,Block((x6140) => Const(())),List(List(b2988)),List(List(b2989)))
    val b2988 = withCtrl(x6160) { CounterIter(x6141, None).name("b2988") } // b2988
    val b2989 = withCtrl(x6160) { Const(true).name("b2989") } // b2989
    val x6143 = withCtrl(x6160) { OpDef(op=BitAnd, inputs=List(b2989, b2984)).name("x6143").srcCtx("UnrollingBase.scala:28:66") } // And(b2989,b2984)
    val x6144 = withCtrl(x6160) { OpDef(op=BitAnd, inputs=List(b2967, b2920)).name("x6144").srcCtx("UnrollingBase.scala:28:66") } // And(b2967,b2920)
    val x6145 = withCtrl(x6160) { OpDef(op=BitAnd, inputs=List(x6143, x6144)).name("x6145").srcCtx("UnrollingBase.scala:28:66") } // And(x6143,x6144)
    val x6146 = withCtrl(x6160) { OpDef(op=BitAnd, inputs=List(x6145, b2911)).name("x6146").srcCtx("UnrollingBase.scala:28:66") } // And(x6145,b2911)
    val x6147 = withCtrl(x6160) { LoadBanks(List(x6099_d1_b0), List(b2963, b2988)).name("x6147").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:51:60") } // ParSRAMLoad(x6099,List(List(b2963, b2988)),List(x6146))
    val x6148 = withCtrl(x6160) { x6147 } // VectorApply(x6147,0)
    val x6149 = withCtrl(x6160) { LoadBanks(List(x6067_d1_b0), List(b2983, b2988)).name("x6149").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:51:72") } // ParSRAMLoad(x6067,List(List(b2983, b2988)),List(x6146))
    val x6150 = withCtrl(x6160) { x6149 } // VectorApply(x6149,0)
    val x6151 = withCtrl(x6160) { OpDef(op=FixSub, inputs=List(x6148, x6150)).name("x6151").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:51:67") } // FixSub(x6148,x6150)
    val x6152 = withCtrl(x6160) { OpDef(op=FixMul, inputs=List(x6151, x6151)).name("x6152").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:51:80") } // FixMul(x6151,x6151)
    val x6153 = withCtrl(x6160) { ReadMem(x6140_d1).name("x6153").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:51:86") } // RegRead(x6140)
    val x6154 = withCtrl(x6160) { OpDef(op=FixEql, inputs=List(b2988, Const(0))).name("x6154").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:51:86") } // FixEql(b2988,Const(0))
    val x6155 = withCtrl(x6160) { ReduceAccumOp(op=FixAdd, input=x6152, accum=x6153).name("x6155").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:51:88") } // FixAdd(x6152,x6153)
    val x6156 = withCtrl(x6160) { OpDef(op=BitAnd, inputs=List(b2984, b2967)).name("x6156").srcCtx("UnrollingBase.scala:28:66") } // And(b2984,b2967)
    val x6157 = withCtrl(x6160) { OpDef(op=BitAnd, inputs=List(b2920, b2911)).name("x6157").srcCtx("UnrollingBase.scala:28:66") } // And(b2920,b2911)
    val x6158 = withCtrl(x6160) { OpDef(op=BitAnd, inputs=List(x6156, x6157)).name("x6158").srcCtx("UnrollingBase.scala:28:66") } // And(x6156,x6157)
    val x6159_x6140_d0 = withCtrl(x6160) { WriteMem(x6140_d0, x6155).name("x6159_x6140_d0").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:51:86") } // RegWrite(x6140,x6155,x6158)
    antiDepsOf(x6159_x6140_d0)=List(x6153)
    val x6159_x6140_d1 = withCtrl(x6160) { WriteMem(x6140_d1, x6155).name("x6159_x6140_d1").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:51:86") } // RegWrite(x6140,x6155,x6158)
    antiDepsOf(x6159_x6140_d1)=List(x6153)
    val x6166 = withCtrl(x6167) { UnitController(style=SeqPipe, level=InnerControl).name("x6166").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:50:37") } // UnitPipe(List(b2984, b2967, b2920, b2911),Block(Const(())))
    val x6161 = withCtrl(x6166) { ReadMem(x6140_d0).name("x6161").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:52:32") } // RegRead(x6140)
    val x6162 = withCtrl(x6166) { OpDef(op=BitAnd, inputs=List(b2984, b2967)).name("x6162").srcCtx("UnrollingBase.scala:28:66") } // And(b2984,b2967)
    val x6163 = withCtrl(x6166) { OpDef(op=BitAnd, inputs=List(b2920, b2911)).name("x6163").srcCtx("UnrollingBase.scala:28:66") } // And(b2920,b2911)
    val x6164 = withCtrl(x6166) { OpDef(op=BitAnd, inputs=List(x6162, x6163)).name("x6164").srcCtx("UnrollingBase.scala:28:66") } // And(x6162,x6163)
    val x6165 = withCtrl(x6166) { StoreBanks(List(List(x6134_d0_b0), List(x6134_d1_b0)), List(b2983), x6161).name("x6165").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:52:25") } // SRAMStore(x6134,ArrayBuffer(Const(64)),List(b2983),Const(0),x6161,x6164)
    val x6168 = withCtrl(x6258) { Counter(min=Const(0), max=Const(64), step=Const(1), par=1).name("x6168").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:50:28") } // CounterNew(Const(0),Const(64),Const(1),Const(1))
    val x6169 = withCtrl(x6258) { CounterChain(List(x6168)).name("x6169").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:50:37") } // CounterChainNew(List(x6168))
    val x6197 = withCtrl(x6258) { LoopController(style=MetaPipe, level=OuterControl, cchain=x6169).name("x6197").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:50:37") } // UnrolledForeach(List(b2968, b2920, b2911),x6169,Block(Const(())),List(List(b3015)),List(List(b3016)))
    val b3015 = withCtrl(x6197) { CounterIter(x6168, Some(0)).name("b3015") } // b3015
    val b3016 = withCtrl(x6197) { Const(true).name("b3016") } // b3016
    val x6170_d0 = withCtrl(x6197) { Reg(init=Some(0)).name("x6170_d0").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:51:36:dist") } // x6170 = RegNew(Const(0))
    isAccum(x6170_d0) = false
    bufferDepthOf(x6170_d0) = 2
    val x6170_d1 = withCtrl(x6197) { Reg(init=Some(0)).name("x6170_d1").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:51:36:dist") } // x6170 = RegNew(Const(0))
    isAccum(x6170_d1) = true
    bufferDepthOf(x6170_d1) = 1
    val x6171 = withCtrl(x6197) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x6171").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:51:43") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x6172 = withCtrl(x6197) { CounterChain(List(x6171)).name("x6172").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:51:86") } // CounterChainNew(List(x6171))
    val x6190 = withCtrl(x6197) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6172).name("x6190").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:51:86") } // UnrolledReduce(List(b3016, b2968, b2920, b2911),x6172,x6170,Block((x6170) => Const(())),List(List(b3020)),List(List(b3021)))
    val b3020 = withCtrl(x6190) { CounterIter(x6171, None).name("b3020") } // b3020
    val b3021 = withCtrl(x6190) { Const(true).name("b3021") } // b3021
    val x6173 = withCtrl(x6190) { OpDef(op=BitAnd, inputs=List(b3021, b3016)).name("x6173").srcCtx("UnrollingBase.scala:28:66") } // And(b3021,b3016)
    val x6174 = withCtrl(x6190) { OpDef(op=BitAnd, inputs=List(b2968, b2920)).name("x6174").srcCtx("UnrollingBase.scala:28:66") } // And(b2968,b2920)
    val x6175 = withCtrl(x6190) { OpDef(op=BitAnd, inputs=List(x6173, x6174)).name("x6175").srcCtx("UnrollingBase.scala:28:66") } // And(x6173,x6174)
    val x6176 = withCtrl(x6190) { OpDef(op=BitAnd, inputs=List(x6175, b2911)).name("x6176").srcCtx("UnrollingBase.scala:28:66") } // And(x6175,b2911)
    val x6177 = withCtrl(x6190) { LoadBanks(List(x6099_d1_b1), List(b2964, b3020)).name("x6177").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:51:60") } // ParSRAMLoad(x6099,List(List(b2964, b3020)),List(x6176))
    val x6178 = withCtrl(x6190) { x6177 } // VectorApply(x6177,0)
    val x6179 = withCtrl(x6190) { LoadBanks(List(x6067_d2_b0), List(b3015, b3020)).name("x6179").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:51:72") } // ParSRAMLoad(x6067,List(List(b3015, b3020)),List(x6176))
    val x6180 = withCtrl(x6190) { x6179 } // VectorApply(x6179,0)
    val x6181 = withCtrl(x6190) { OpDef(op=FixSub, inputs=List(x6178, x6180)).name("x6181").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:51:67") } // FixSub(x6178,x6180)
    val x6182 = withCtrl(x6190) { OpDef(op=FixMul, inputs=List(x6181, x6181)).name("x6182").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:51:80") } // FixMul(x6181,x6181)
    val x6183 = withCtrl(x6190) { ReadMem(x6170_d1).name("x6183").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:51:86") } // RegRead(x6170)
    val x6184 = withCtrl(x6190) { OpDef(op=FixEql, inputs=List(b3020, Const(0))).name("x6184").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:51:86") } // FixEql(b3020,Const(0))
    val x6185 = withCtrl(x6190) { ReduceAccumOp(op=FixAdd, input=x6182, accum=x6183).name("x6185").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:51:88") } // FixAdd(x6182,x6183)
    val x6186 = withCtrl(x6190) { OpDef(op=BitAnd, inputs=List(b3016, b2968)).name("x6186").srcCtx("UnrollingBase.scala:28:66") } // And(b3016,b2968)
    val x6187 = withCtrl(x6190) { OpDef(op=BitAnd, inputs=List(b2920, b2911)).name("x6187").srcCtx("UnrollingBase.scala:28:66") } // And(b2920,b2911)
    val x6188 = withCtrl(x6190) { OpDef(op=BitAnd, inputs=List(x6186, x6187)).name("x6188").srcCtx("UnrollingBase.scala:28:66") } // And(x6186,x6187)
    val x6189_x6170_d0 = withCtrl(x6190) { WriteMem(x6170_d0, x6185).name("x6189_x6170_d0").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:51:86") } // RegWrite(x6170,x6185,x6188)
    antiDepsOf(x6189_x6170_d0)=List(x6183)
    val x6189_x6170_d1 = withCtrl(x6190) { WriteMem(x6170_d1, x6185).name("x6189_x6170_d1").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:51:86") } // RegWrite(x6170,x6185,x6188)
    antiDepsOf(x6189_x6170_d1)=List(x6183)
    val x6196 = withCtrl(x6197) { UnitController(style=SeqPipe, level=InnerControl).name("x6196").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:50:37") } // UnitPipe(List(b3016, b2968, b2920, b2911),Block(Const(())))
    val x6191 = withCtrl(x6196) { ReadMem(x6170_d0).name("x6191").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:52:32") } // RegRead(x6170)
    val x6192 = withCtrl(x6196) { OpDef(op=BitAnd, inputs=List(b3016, b2968)).name("x6192").srcCtx("UnrollingBase.scala:28:66") } // And(b3016,b2968)
    val x6193 = withCtrl(x6196) { OpDef(op=BitAnd, inputs=List(b2920, b2911)).name("x6193").srcCtx("UnrollingBase.scala:28:66") } // And(b2920,b2911)
    val x6194 = withCtrl(x6196) { OpDef(op=BitAnd, inputs=List(x6192, x6193)).name("x6194").srcCtx("UnrollingBase.scala:28:66") } // And(x6192,x6193)
    val x6195 = withCtrl(x6196) { StoreBanks(List(List(x6135_d0_b0), List(x6135_d1_b0)), List(b3015), x6191).name("x6195").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:52:25") } // SRAMStore(x6135,ArrayBuffer(Const(64)),List(b3015),Const(0),x6191,x6194)
    val x6198 = withCtrl(x6258) { Counter(min=Const(0), max=Const(64), step=Const(1), par=1).name("x6198").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:50:28") } // CounterNew(Const(0),Const(64),Const(1),Const(1))
    val x6199 = withCtrl(x6258) { CounterChain(List(x6198)).name("x6199").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:50:37") } // CounterChainNew(List(x6198))
    val x6227 = withCtrl(x6258) { LoopController(style=MetaPipe, level=OuterControl, cchain=x6199).name("x6227").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:50:37") } // UnrolledForeach(List(b2969, b2920, b2911),x6199,Block(Const(())),List(List(b3047)),List(List(b3048)))
    val b3047 = withCtrl(x6227) { CounterIter(x6198, Some(0)).name("b3047") } // b3047
    val b3048 = withCtrl(x6227) { Const(true).name("b3048") } // b3048
    val x6200_d0 = withCtrl(x6227) { Reg(init=Some(0)).name("x6200_d0").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:51:36:dist") } // x6200 = RegNew(Const(0))
    isAccum(x6200_d0) = false
    bufferDepthOf(x6200_d0) = 2
    val x6200_d1 = withCtrl(x6227) { Reg(init=Some(0)).name("x6200_d1").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:51:36:dist") } // x6200 = RegNew(Const(0))
    isAccum(x6200_d1) = true
    bufferDepthOf(x6200_d1) = 1
    val x6201 = withCtrl(x6227) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x6201").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:51:43") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x6202 = withCtrl(x6227) { CounterChain(List(x6201)).name("x6202").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:51:86") } // CounterChainNew(List(x6201))
    val x6220 = withCtrl(x6227) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6202).name("x6220").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:51:86") } // UnrolledReduce(List(b3048, b2969, b2920, b2911),x6202,x6200,Block((x6200) => Const(())),List(List(b3052)),List(List(b3053)))
    val b3052 = withCtrl(x6220) { CounterIter(x6201, None).name("b3052") } // b3052
    val b3053 = withCtrl(x6220) { Const(true).name("b3053") } // b3053
    val x6203 = withCtrl(x6220) { OpDef(op=BitAnd, inputs=List(b3053, b3048)).name("x6203").srcCtx("UnrollingBase.scala:28:66") } // And(b3053,b3048)
    val x6204 = withCtrl(x6220) { OpDef(op=BitAnd, inputs=List(b2969, b2920)).name("x6204").srcCtx("UnrollingBase.scala:28:66") } // And(b2969,b2920)
    val x6205 = withCtrl(x6220) { OpDef(op=BitAnd, inputs=List(x6203, x6204)).name("x6205").srcCtx("UnrollingBase.scala:28:66") } // And(x6203,x6204)
    val x6206 = withCtrl(x6220) { OpDef(op=BitAnd, inputs=List(x6205, b2911)).name("x6206").srcCtx("UnrollingBase.scala:28:66") } // And(x6205,b2911)
    val x6207 = withCtrl(x6220) { LoadBanks(List(x6099_d1_b2), List(b2965, b3052)).name("x6207").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:51:60") } // ParSRAMLoad(x6099,List(List(b2965, b3052)),List(x6206))
    val x6208 = withCtrl(x6220) { x6207 } // VectorApply(x6207,0)
    val x6209 = withCtrl(x6220) { LoadBanks(List(x6067_d3_b0), List(b3047, b3052)).name("x6209").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:51:72") } // ParSRAMLoad(x6067,List(List(b3047, b3052)),List(x6206))
    val x6210 = withCtrl(x6220) { x6209 } // VectorApply(x6209,0)
    val x6211 = withCtrl(x6220) { OpDef(op=FixSub, inputs=List(x6208, x6210)).name("x6211").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:51:67") } // FixSub(x6208,x6210)
    val x6212 = withCtrl(x6220) { OpDef(op=FixMul, inputs=List(x6211, x6211)).name("x6212").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:51:80") } // FixMul(x6211,x6211)
    val x6213 = withCtrl(x6220) { ReadMem(x6200_d1).name("x6213").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:51:86") } // RegRead(x6200)
    val x6214 = withCtrl(x6220) { OpDef(op=FixEql, inputs=List(b3052, Const(0))).name("x6214").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:51:86") } // FixEql(b3052,Const(0))
    val x6215 = withCtrl(x6220) { ReduceAccumOp(op=FixAdd, input=x6212, accum=x6213).name("x6215").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:51:88") } // FixAdd(x6212,x6213)
    val x6216 = withCtrl(x6220) { OpDef(op=BitAnd, inputs=List(b3048, b2969)).name("x6216").srcCtx("UnrollingBase.scala:28:66") } // And(b3048,b2969)
    val x6217 = withCtrl(x6220) { OpDef(op=BitAnd, inputs=List(b2920, b2911)).name("x6217").srcCtx("UnrollingBase.scala:28:66") } // And(b2920,b2911)
    val x6218 = withCtrl(x6220) { OpDef(op=BitAnd, inputs=List(x6216, x6217)).name("x6218").srcCtx("UnrollingBase.scala:28:66") } // And(x6216,x6217)
    val x6219_x6200_d0 = withCtrl(x6220) { WriteMem(x6200_d0, x6215).name("x6219_x6200_d0").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:51:86") } // RegWrite(x6200,x6215,x6218)
    antiDepsOf(x6219_x6200_d0)=List(x6213)
    val x6219_x6200_d1 = withCtrl(x6220) { WriteMem(x6200_d1, x6215).name("x6219_x6200_d1").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:51:86") } // RegWrite(x6200,x6215,x6218)
    antiDepsOf(x6219_x6200_d1)=List(x6213)
    val x6226 = withCtrl(x6227) { UnitController(style=SeqPipe, level=InnerControl).name("x6226").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:50:37") } // UnitPipe(List(b3048, b2969, b2920, b2911),Block(Const(())))
    val x6221 = withCtrl(x6226) { ReadMem(x6200_d0).name("x6221").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:52:32") } // RegRead(x6200)
    val x6222 = withCtrl(x6226) { OpDef(op=BitAnd, inputs=List(b3048, b2969)).name("x6222").srcCtx("UnrollingBase.scala:28:66") } // And(b3048,b2969)
    val x6223 = withCtrl(x6226) { OpDef(op=BitAnd, inputs=List(b2920, b2911)).name("x6223").srcCtx("UnrollingBase.scala:28:66") } // And(b2920,b2911)
    val x6224 = withCtrl(x6226) { OpDef(op=BitAnd, inputs=List(x6222, x6223)).name("x6224").srcCtx("UnrollingBase.scala:28:66") } // And(x6222,x6223)
    val x6225 = withCtrl(x6226) { StoreBanks(List(List(x6136_d0_b0), List(x6136_d1_b0)), List(b3047), x6221).name("x6225").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:52:25") } // SRAMStore(x6136,ArrayBuffer(Const(64)),List(b3047),Const(0),x6221,x6224)
    val x6228 = withCtrl(x6258) { Counter(min=Const(0), max=Const(64), step=Const(1), par=1).name("x6228").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:50:28") } // CounterNew(Const(0),Const(64),Const(1),Const(1))
    val x6229 = withCtrl(x6258) { CounterChain(List(x6228)).name("x6229").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:50:37") } // CounterChainNew(List(x6228))
    val x6257 = withCtrl(x6258) { LoopController(style=MetaPipe, level=OuterControl, cchain=x6229).name("x6257").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:50:37") } // UnrolledForeach(List(b2970, b2920, b2911),x6229,Block(Const(())),List(List(b3079)),List(List(b3080)))
    val b3079 = withCtrl(x6257) { CounterIter(x6228, Some(0)).name("b3079") } // b3079
    val b3080 = withCtrl(x6257) { Const(true).name("b3080") } // b3080
    val x6230_d0 = withCtrl(x6257) { Reg(init=Some(0)).name("x6230_d0").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:51:36:dist") } // x6230 = RegNew(Const(0))
    isAccum(x6230_d0) = false
    bufferDepthOf(x6230_d0) = 2
    val x6230_d1 = withCtrl(x6257) { Reg(init=Some(0)).name("x6230_d1").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:51:36:dist") } // x6230 = RegNew(Const(0))
    isAccum(x6230_d1) = true
    bufferDepthOf(x6230_d1) = 1
    val x6231 = withCtrl(x6257) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x6231").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:51:43") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x6232 = withCtrl(x6257) { CounterChain(List(x6231)).name("x6232").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:51:86") } // CounterChainNew(List(x6231))
    val x6250 = withCtrl(x6257) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6232).name("x6250").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:51:86") } // UnrolledReduce(List(b3080, b2970, b2920, b2911),x6232,x6230,Block((x6230) => Const(())),List(List(b3084)),List(List(b3085)))
    val b3084 = withCtrl(x6250) { CounterIter(x6231, None).name("b3084") } // b3084
    val b3085 = withCtrl(x6250) { Const(true).name("b3085") } // b3085
    val x6233 = withCtrl(x6250) { OpDef(op=BitAnd, inputs=List(b3085, b3080)).name("x6233").srcCtx("UnrollingBase.scala:28:66") } // And(b3085,b3080)
    val x6234 = withCtrl(x6250) { OpDef(op=BitAnd, inputs=List(b2970, b2920)).name("x6234").srcCtx("UnrollingBase.scala:28:66") } // And(b2970,b2920)
    val x6235 = withCtrl(x6250) { OpDef(op=BitAnd, inputs=List(x6233, x6234)).name("x6235").srcCtx("UnrollingBase.scala:28:66") } // And(x6233,x6234)
    val x6236 = withCtrl(x6250) { OpDef(op=BitAnd, inputs=List(x6235, b2911)).name("x6236").srcCtx("UnrollingBase.scala:28:66") } // And(x6235,b2911)
    val x6237 = withCtrl(x6250) { LoadBanks(List(x6099_d1_b3), List(b2966, b3084)).name("x6237").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:51:60") } // ParSRAMLoad(x6099,List(List(b2966, b3084)),List(x6236))
    val x6238 = withCtrl(x6250) { x6237 } // VectorApply(x6237,0)
    val x6239 = withCtrl(x6250) { LoadBanks(List(x6067_d4_b0), List(b3079, b3084)).name("x6239").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:51:72") } // ParSRAMLoad(x6067,List(List(b3079, b3084)),List(x6236))
    val x6240 = withCtrl(x6250) { x6239 } // VectorApply(x6239,0)
    val x6241 = withCtrl(x6250) { OpDef(op=FixSub, inputs=List(x6238, x6240)).name("x6241").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:51:67") } // FixSub(x6238,x6240)
    val x6242 = withCtrl(x6250) { OpDef(op=FixMul, inputs=List(x6241, x6241)).name("x6242").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:51:80") } // FixMul(x6241,x6241)
    val x6243 = withCtrl(x6250) { ReadMem(x6230_d1).name("x6243").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:51:86") } // RegRead(x6230)
    val x6244 = withCtrl(x6250) { OpDef(op=FixEql, inputs=List(b3084, Const(0))).name("x6244").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:51:86") } // FixEql(b3084,Const(0))
    val x6245 = withCtrl(x6250) { ReduceAccumOp(op=FixAdd, input=x6242, accum=x6243).name("x6245").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:51:88") } // FixAdd(x6242,x6243)
    val x6246 = withCtrl(x6250) { OpDef(op=BitAnd, inputs=List(b3080, b2970)).name("x6246").srcCtx("UnrollingBase.scala:28:66") } // And(b3080,b2970)
    val x6247 = withCtrl(x6250) { OpDef(op=BitAnd, inputs=List(b2920, b2911)).name("x6247").srcCtx("UnrollingBase.scala:28:66") } // And(b2920,b2911)
    val x6248 = withCtrl(x6250) { OpDef(op=BitAnd, inputs=List(x6246, x6247)).name("x6248").srcCtx("UnrollingBase.scala:28:66") } // And(x6246,x6247)
    val x6249_x6230_d0 = withCtrl(x6250) { WriteMem(x6230_d0, x6245).name("x6249_x6230_d0").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:51:86") } // RegWrite(x6230,x6245,x6248)
    antiDepsOf(x6249_x6230_d0)=List(x6243)
    val x6249_x6230_d1 = withCtrl(x6250) { WriteMem(x6230_d1, x6245).name("x6249_x6230_d1").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:51:86") } // RegWrite(x6230,x6245,x6248)
    antiDepsOf(x6249_x6230_d1)=List(x6243)
    val x6256 = withCtrl(x6257) { UnitController(style=SeqPipe, level=InnerControl).name("x6256").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:50:37") } // UnitPipe(List(b3080, b2970, b2920, b2911),Block(Const(())))
    val x6251 = withCtrl(x6256) { ReadMem(x6230_d0).name("x6251").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:52:32") } // RegRead(x6230)
    val x6252 = withCtrl(x6256) { OpDef(op=BitAnd, inputs=List(b3080, b2970)).name("x6252").srcCtx("UnrollingBase.scala:28:66") } // And(b3080,b2970)
    val x6253 = withCtrl(x6256) { OpDef(op=BitAnd, inputs=List(b2920, b2911)).name("x6253").srcCtx("UnrollingBase.scala:28:66") } // And(b2920,b2911)
    val x6254 = withCtrl(x6256) { OpDef(op=BitAnd, inputs=List(x6252, x6253)).name("x6254").srcCtx("UnrollingBase.scala:28:66") } // And(x6252,x6253)
    val x6255 = withCtrl(x6256) { StoreBanks(List(List(x6137_d0_b0), List(x6137_d1_b0)), List(b3079), x6251).name("x6255").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:52:25") } // SRAMStore(x6137,ArrayBuffer(Const(64)),List(b3079),Const(0),x6251,x6254)
    val x6259_d0 = withCtrl(x6496) { Reg(init=Some(0)).name("x6259_d0").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:54:37:minDist") } // x6259 = RegNew(Const(0))
    isAccum(x6259_d0) = false
    bufferDepthOf(x6259_d0) = 2
    val x6259_d1 = withCtrl(x6496) { Reg(init=Some(0)).name("x6259_d1").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:54:37:minDist") } // x6259 = RegNew(Const(0))
    isAccum(x6259_d1) = true
    bufferDepthOf(x6259_d1) = 1
    val x6260_d0 = withCtrl(x6496) { Reg(init=Some(0)).name("x6260_d0").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:54:37:minDist") } // x6260 = RegNew(Const(0))
    isAccum(x6260_d0) = false
    bufferDepthOf(x6260_d0) = 2
    val x6260_d1 = withCtrl(x6496) { Reg(init=Some(0)).name("x6260_d1").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:54:37:minDist") } // x6260 = RegNew(Const(0))
    isAccum(x6260_d1) = true
    bufferDepthOf(x6260_d1) = 1
    val x6261_d0 = withCtrl(x6496) { Reg(init=Some(0)).name("x6261_d0").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:54:37:minDist") } // x6261 = RegNew(Const(0))
    isAccum(x6261_d0) = false
    bufferDepthOf(x6261_d0) = 2
    val x6261_d1 = withCtrl(x6496) { Reg(init=Some(0)).name("x6261_d1").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:54:37:minDist") } // x6261 = RegNew(Const(0))
    isAccum(x6261_d1) = true
    bufferDepthOf(x6261_d1) = 1
    val x6262_d0 = withCtrl(x6496) { Reg(init=Some(0)).name("x6262_d0").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:54:37:minDist") } // x6262 = RegNew(Const(0))
    isAccum(x6262_d0) = false
    bufferDepthOf(x6262_d0) = 2
    val x6262_d1 = withCtrl(x6496) { Reg(init=Some(0)).name("x6262_d1").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:54:37:minDist") } // x6262 = RegNew(Const(0))
    isAccum(x6262_d1) = true
    bufferDepthOf(x6262_d1) = 1
    val x6319 = withCtrl(x6496) { UnitController(style=ForkJoin, level=OuterControl).name("x6319").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b2920, b2911),Block(Const(())))
    val x6263 = withCtrl(x6319) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x6263").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:54:49") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x6264 = withCtrl(x6319) { CounterChain(List(x6263)).name("x6264").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:54:77") } // CounterChainNew(List(x6263))
    val x6276 = withCtrl(x6319) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6264).name("x6276").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:54:77") } // UnrolledReduce(List(b2967, b2920, b2911),x6264,x6259,Block((x6259) => Const(())),List(List(b3124)),List(List(b3125)))
    val b3124 = withCtrl(x6276) { CounterIter(x6263, None).name("b3124") } // b3124
    val b3125 = withCtrl(x6276) { Const(true).name("b3125") } // b3125
    val x6265 = withCtrl(x6276) { OpDef(op=BitAnd, inputs=List(b3125, b2967)).name("x6265").srcCtx("UnrollingBase.scala:28:66") } // And(b3125,b2967)
    val x6266 = withCtrl(x6276) { OpDef(op=BitAnd, inputs=List(b2920, b2911)).name("x6266").srcCtx("UnrollingBase.scala:28:66") } // And(b2920,b2911)
    val x6267 = withCtrl(x6276) { OpDef(op=BitAnd, inputs=List(x6265, x6266)).name("x6267").srcCtx("UnrollingBase.scala:28:66") } // And(x6265,x6266)
    val x6268 = withCtrl(x6276) { LoadBanks(List(x6134_d1_b0), List(b3124)).name("x6268").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:54:70") } // ParSRAMLoad(x6134,List(List(b3124)),List(x6267))
    val x6269 = withCtrl(x6276) { x6268 } // VectorApply(x6268,0)
    val x6270 = withCtrl(x6276) { ReadMem(x6259_d1).name("x6270").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:54:77") } // RegRead(x6259)
    val x6271 = withCtrl(x6276) { OpDef(op=FixEql, inputs=List(b3124, Const(0))).name("x6271").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:54:77") } // FixEql(b3124,Const(0))
    val x6272 = withCtrl(x6276) { ReduceAccumOp(op=FixMin, input=x6269, accum=x6270).name("x6272").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:54:91") } // Min(x6269,x6270)
    val x6273 = withCtrl(x6276) { OpDef(op=BitAnd, inputs=List(b2967, b2920)).name("x6273").srcCtx("UnrollingBase.scala:28:66") } // And(b2967,b2920)
    val x6274 = withCtrl(x6276) { OpDef(op=BitAnd, inputs=List(x6273, b2911)).name("x6274").srcCtx("UnrollingBase.scala:28:66") } // And(x6273,b2911)
    val x6275_x6259_d0 = withCtrl(x6276) { WriteMem(x6259_d0, x6272).name("x6275_x6259_d0").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:54:77") } // RegWrite(x6259,x6272,x6274)
    antiDepsOf(x6275_x6259_d0)=List(x6270)
    val x6275_x6259_d1 = withCtrl(x6276) { WriteMem(x6259_d1, x6272).name("x6275_x6259_d1").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:54:77") } // RegWrite(x6259,x6272,x6274)
    antiDepsOf(x6275_x6259_d1)=List(x6270)
    val x6277 = withCtrl(x6319) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x6277").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:54:49") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x6278 = withCtrl(x6319) { CounterChain(List(x6277)).name("x6278").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:54:77") } // CounterChainNew(List(x6277))
    val x6290 = withCtrl(x6319) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6278).name("x6290").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:54:77") } // UnrolledReduce(List(b2968, b2920, b2911),x6278,x6260,Block((x6260) => Const(())),List(List(b3138)),List(List(b3139)))
    val b3138 = withCtrl(x6290) { CounterIter(x6277, None).name("b3138") } // b3138
    val b3139 = withCtrl(x6290) { Const(true).name("b3139") } // b3139
    val x6279 = withCtrl(x6290) { OpDef(op=BitAnd, inputs=List(b3139, b2968)).name("x6279").srcCtx("UnrollingBase.scala:28:66") } // And(b3139,b2968)
    val x6280 = withCtrl(x6290) { OpDef(op=BitAnd, inputs=List(b2920, b2911)).name("x6280").srcCtx("UnrollingBase.scala:28:66") } // And(b2920,b2911)
    val x6281 = withCtrl(x6290) { OpDef(op=BitAnd, inputs=List(x6279, x6280)).name("x6281").srcCtx("UnrollingBase.scala:28:66") } // And(x6279,x6280)
    val x6282 = withCtrl(x6290) { LoadBanks(List(x6135_d1_b0), List(b3138)).name("x6282").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:54:70") } // ParSRAMLoad(x6135,List(List(b3138)),List(x6281))
    val x6283 = withCtrl(x6290) { x6282 } // VectorApply(x6282,0)
    val x6284 = withCtrl(x6290) { ReadMem(x6260_d1).name("x6284").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:54:77") } // RegRead(x6260)
    val x6285 = withCtrl(x6290) { OpDef(op=FixEql, inputs=List(b3138, Const(0))).name("x6285").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:54:77") } // FixEql(b3138,Const(0))
    val x6286 = withCtrl(x6290) { ReduceAccumOp(op=FixMin, input=x6283, accum=x6284).name("x6286").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:54:91") } // Min(x6283,x6284)
    val x6287 = withCtrl(x6290) { OpDef(op=BitAnd, inputs=List(b2968, b2920)).name("x6287").srcCtx("UnrollingBase.scala:28:66") } // And(b2968,b2920)
    val x6288 = withCtrl(x6290) { OpDef(op=BitAnd, inputs=List(x6287, b2911)).name("x6288").srcCtx("UnrollingBase.scala:28:66") } // And(x6287,b2911)
    val x6289_x6260_d0 = withCtrl(x6290) { WriteMem(x6260_d0, x6286).name("x6289_x6260_d0").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:54:77") } // RegWrite(x6260,x6286,x6288)
    antiDepsOf(x6289_x6260_d0)=List(x6284)
    val x6289_x6260_d1 = withCtrl(x6290) { WriteMem(x6260_d1, x6286).name("x6289_x6260_d1").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:54:77") } // RegWrite(x6260,x6286,x6288)
    antiDepsOf(x6289_x6260_d1)=List(x6284)
    val x6291 = withCtrl(x6319) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x6291").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:54:49") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x6292 = withCtrl(x6319) { CounterChain(List(x6291)).name("x6292").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:54:77") } // CounterChainNew(List(x6291))
    val x6304 = withCtrl(x6319) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6292).name("x6304").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:54:77") } // UnrolledReduce(List(b2969, b2920, b2911),x6292,x6261,Block((x6261) => Const(())),List(List(b3152)),List(List(b3153)))
    val b3152 = withCtrl(x6304) { CounterIter(x6291, None).name("b3152") } // b3152
    val b3153 = withCtrl(x6304) { Const(true).name("b3153") } // b3153
    val x6293 = withCtrl(x6304) { OpDef(op=BitAnd, inputs=List(b3153, b2969)).name("x6293").srcCtx("UnrollingBase.scala:28:66") } // And(b3153,b2969)
    val x6294 = withCtrl(x6304) { OpDef(op=BitAnd, inputs=List(b2920, b2911)).name("x6294").srcCtx("UnrollingBase.scala:28:66") } // And(b2920,b2911)
    val x6295 = withCtrl(x6304) { OpDef(op=BitAnd, inputs=List(x6293, x6294)).name("x6295").srcCtx("UnrollingBase.scala:28:66") } // And(x6293,x6294)
    val x6296 = withCtrl(x6304) { LoadBanks(List(x6136_d1_b0), List(b3152)).name("x6296").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:54:70") } // ParSRAMLoad(x6136,List(List(b3152)),List(x6295))
    val x6297 = withCtrl(x6304) { x6296 } // VectorApply(x6296,0)
    val x6298 = withCtrl(x6304) { ReadMem(x6261_d1).name("x6298").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:54:77") } // RegRead(x6261)
    val x6299 = withCtrl(x6304) { OpDef(op=FixEql, inputs=List(b3152, Const(0))).name("x6299").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:54:77") } // FixEql(b3152,Const(0))
    val x6300 = withCtrl(x6304) { ReduceAccumOp(op=FixMin, input=x6297, accum=x6298).name("x6300").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:54:91") } // Min(x6297,x6298)
    val x6301 = withCtrl(x6304) { OpDef(op=BitAnd, inputs=List(b2969, b2920)).name("x6301").srcCtx("UnrollingBase.scala:28:66") } // And(b2969,b2920)
    val x6302 = withCtrl(x6304) { OpDef(op=BitAnd, inputs=List(x6301, b2911)).name("x6302").srcCtx("UnrollingBase.scala:28:66") } // And(x6301,b2911)
    val x6303_x6261_d0 = withCtrl(x6304) { WriteMem(x6261_d0, x6300).name("x6303_x6261_d0").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:54:77") } // RegWrite(x6261,x6300,x6302)
    antiDepsOf(x6303_x6261_d0)=List(x6298)
    val x6303_x6261_d1 = withCtrl(x6304) { WriteMem(x6261_d1, x6300).name("x6303_x6261_d1").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:54:77") } // RegWrite(x6261,x6300,x6302)
    antiDepsOf(x6303_x6261_d1)=List(x6298)
    val x6305 = withCtrl(x6319) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x6305").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:54:49") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x6306 = withCtrl(x6319) { CounterChain(List(x6305)).name("x6306").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:54:77") } // CounterChainNew(List(x6305))
    val x6318 = withCtrl(x6319) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6306).name("x6318").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:54:77") } // UnrolledReduce(List(b2970, b2920, b2911),x6306,x6262,Block((x6262) => Const(())),List(List(b3166)),List(List(b3167)))
    val b3166 = withCtrl(x6318) { CounterIter(x6305, None).name("b3166") } // b3166
    val b3167 = withCtrl(x6318) { Const(true).name("b3167") } // b3167
    val x6307 = withCtrl(x6318) { OpDef(op=BitAnd, inputs=List(b3167, b2970)).name("x6307").srcCtx("UnrollingBase.scala:28:66") } // And(b3167,b2970)
    val x6308 = withCtrl(x6318) { OpDef(op=BitAnd, inputs=List(b2920, b2911)).name("x6308").srcCtx("UnrollingBase.scala:28:66") } // And(b2920,b2911)
    val x6309 = withCtrl(x6318) { OpDef(op=BitAnd, inputs=List(x6307, x6308)).name("x6309").srcCtx("UnrollingBase.scala:28:66") } // And(x6307,x6308)
    val x6310 = withCtrl(x6318) { LoadBanks(List(x6137_d1_b0), List(b3166)).name("x6310").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:54:70") } // ParSRAMLoad(x6137,List(List(b3166)),List(x6309))
    val x6311 = withCtrl(x6318) { x6310 } // VectorApply(x6310,0)
    val x6312 = withCtrl(x6318) { ReadMem(x6262_d1).name("x6312").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:54:77") } // RegRead(x6262)
    val x6313 = withCtrl(x6318) { OpDef(op=FixEql, inputs=List(b3166, Const(0))).name("x6313").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:54:77") } // FixEql(b3166,Const(0))
    val x6314 = withCtrl(x6318) { ReduceAccumOp(op=FixMin, input=x6311, accum=x6312).name("x6314").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:54:91") } // Min(x6311,x6312)
    val x6315 = withCtrl(x6318) { OpDef(op=BitAnd, inputs=List(b2970, b2920)).name("x6315").srcCtx("UnrollingBase.scala:28:66") } // And(b2970,b2920)
    val x6316 = withCtrl(x6318) { OpDef(op=BitAnd, inputs=List(x6315, b2911)).name("x6316").srcCtx("UnrollingBase.scala:28:66") } // And(x6315,b2911)
    val x6317_x6262_d0 = withCtrl(x6318) { WriteMem(x6262_d0, x6314).name("x6317_x6262_d0").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:54:77") } // RegWrite(x6262,x6314,x6316)
    antiDepsOf(x6317_x6262_d0)=List(x6312)
    val x6317_x6262_d1 = withCtrl(x6318) { WriteMem(x6262_d1, x6314).name("x6317_x6262_d1").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:54:77") } // RegWrite(x6262,x6314,x6316)
    antiDepsOf(x6317_x6262_d1)=List(x6312)
    val x6320_d0 = withCtrl(x6496) { Reg(init=Some(0)).name("x6320_d0").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:55:37:minCent") } // x6320 = RegNew(Const(0))
    isAccum(x6320_d0) = false
    bufferDepthOf(x6320_d0) = 2
    val x6320_d1 = withCtrl(x6496) { Reg(init=Some(0)).name("x6320_d1").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:55:37:minCent") } // x6320 = RegNew(Const(0))
    isAccum(x6320_d1) = true
    bufferDepthOf(x6320_d1) = 1
    val x6321_d0 = withCtrl(x6496) { Reg(init=Some(0)).name("x6321_d0").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:55:37:minCent") } // x6321 = RegNew(Const(0))
    isAccum(x6321_d0) = false
    bufferDepthOf(x6321_d0) = 2
    val x6321_d1 = withCtrl(x6496) { Reg(init=Some(0)).name("x6321_d1").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:55:37:minCent") } // x6321 = RegNew(Const(0))
    isAccum(x6321_d1) = true
    bufferDepthOf(x6321_d1) = 1
    val x6322_d0 = withCtrl(x6496) { Reg(init=Some(0)).name("x6322_d0").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:55:37:minCent") } // x6322 = RegNew(Const(0))
    def split1 = {
    isAccum(x6322_d0) = false
    bufferDepthOf(x6322_d0) = 2
    val x6322_d1 = withCtrl(x6496) { Reg(init=Some(0)).name("x6322_d1").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:55:37:minCent") } // x6322 = RegNew(Const(0))
    isAccum(x6322_d1) = true
    bufferDepthOf(x6322_d1) = 1
    val x6323_d0 = withCtrl(x6496) { Reg(init=Some(0)).name("x6323_d0").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:55:37:minCent") } // x6323 = RegNew(Const(0))
    isAccum(x6323_d0) = false
    bufferDepthOf(x6323_d0) = 2
    val x6323_d1 = withCtrl(x6496) { Reg(init=Some(0)).name("x6323_d1").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:55:37:minCent") } // x6323 = RegNew(Const(0))
    isAccum(x6323_d1) = true
    bufferDepthOf(x6323_d1) = 1
    val x6392 = withCtrl(x6496) { UnitController(style=ForkJoin, level=OuterControl).name("x6392").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b2920, b2911),Block(Const(())))
    val x6324 = withCtrl(x6392) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x6324").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:55:53") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x6325 = withCtrl(x6392) { CounterChain(List(x6324)).name("x6325").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:57:15") } // CounterChainNew(List(x6324))
    val x6340 = withCtrl(x6392) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6325).name("x6340").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:57:15") } // UnrolledReduce(List(b2967, b2920, b2911),x6325,x6320,Block((x6320) => Const(())),List(List(b3193)),List(List(b3194)))
    val b3193 = withCtrl(x6340) { CounterIter(x6324, None).name("b3193") } // b3193
    val b3194 = withCtrl(x6340) { Const(true).name("b3194") } // b3194
    val x6326 = withCtrl(x6340) { OpDef(op=BitAnd, inputs=List(b3194, b2967)).name("x6326").srcCtx("UnrollingBase.scala:28:66") } // And(b3194,b2967)
    val x6327 = withCtrl(x6340) { OpDef(op=BitAnd, inputs=List(b2920, b2911)).name("x6327").srcCtx("UnrollingBase.scala:28:66") } // And(b2920,b2911)
    val x6328 = withCtrl(x6340) { OpDef(op=BitAnd, inputs=List(x6326, x6327)).name("x6328").srcCtx("UnrollingBase.scala:28:66") } // And(x6326,x6327)
    val x6329 = withCtrl(x6340) { LoadBanks(List(x6134_d0_b0), List(b3193)).name("x6329").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:56:24") } // ParSRAMLoad(x6134,List(List(b3193)),List(x6328))
    val x6330 = withCtrl(x6340) { x6329 } // VectorApply(x6329,0)
    val x6331 = withCtrl(x6340) { ReadMem(x6259_d0).name("x6331").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:56:40") } // RegRead(x6259)
    val x6332 = withCtrl(x6340) { OpDef(op=FixEql, inputs=List(x6330, x6331)).name("x6332").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:56:29") } // FixEql(x6330,x6331)
    val x6333 = withCtrl(x6340) { OpDef(op=MuxOp, inputs=List(x6332, b3193, Const(-1))).name("x6333").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:56:18") } // Mux(x6332,b3193,Const(-1))
    val x6334 = withCtrl(x6340) { ReadMem(x6320_d1).name("x6334").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:57:15") } // RegRead(x6320)
    val x6335 = withCtrl(x6340) { OpDef(op=FixEql, inputs=List(b3193, Const(0))).name("x6335").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:57:15") } // FixEql(b3193,Const(0))
    val x6336 = withCtrl(x6340) { ReduceAccumOp(op=FixMax, input=x6333, accum=x6334).name("x6336").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:57:29") } // Max(x6333,x6334)
    val x6337 = withCtrl(x6340) { OpDef(op=BitAnd, inputs=List(b2967, b2920)).name("x6337").srcCtx("UnrollingBase.scala:28:66") } // And(b2967,b2920)
    val x6338 = withCtrl(x6340) { OpDef(op=BitAnd, inputs=List(x6337, b2911)).name("x6338").srcCtx("UnrollingBase.scala:28:66") } // And(x6337,b2911)
    val x6339_x6320_d0 = withCtrl(x6340) { WriteMem(x6320_d0, x6336).name("x6339_x6320_d0").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:57:15") } // RegWrite(x6320,x6336,x6338)
    antiDepsOf(x6339_x6320_d0)=List(x6334)
    val x6339_x6320_d1 = withCtrl(x6340) { WriteMem(x6320_d1, x6336).name("x6339_x6320_d1").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:57:15") } // RegWrite(x6320,x6336,x6338)
    antiDepsOf(x6339_x6320_d1)=List(x6334)
    val x6341 = withCtrl(x6392) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x6341").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:55:53") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x6342 = withCtrl(x6392) { CounterChain(List(x6341)).name("x6342").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:57:15") } // CounterChainNew(List(x6341))
    val x6357 = withCtrl(x6392) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6342).name("x6357").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:57:15") } // UnrolledReduce(List(b2968, b2920, b2911),x6342,x6321,Block((x6321) => Const(())),List(List(b3210)),List(List(b3211)))
    val b3210 = withCtrl(x6357) { CounterIter(x6341, None).name("b3210") } // b3210
    val b3211 = withCtrl(x6357) { Const(true).name("b3211") } // b3211
    val x6343 = withCtrl(x6357) { OpDef(op=BitAnd, inputs=List(b3211, b2968)).name("x6343").srcCtx("UnrollingBase.scala:28:66") } // And(b3211,b2968)
    val x6344 = withCtrl(x6357) { OpDef(op=BitAnd, inputs=List(b2920, b2911)).name("x6344").srcCtx("UnrollingBase.scala:28:66") } // And(b2920,b2911)
    val x6345 = withCtrl(x6357) { OpDef(op=BitAnd, inputs=List(x6343, x6344)).name("x6345").srcCtx("UnrollingBase.scala:28:66") } // And(x6343,x6344)
    val x6346 = withCtrl(x6357) { LoadBanks(List(x6135_d0_b0), List(b3210)).name("x6346").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:56:24") } // ParSRAMLoad(x6135,List(List(b3210)),List(x6345))
    val x6347 = withCtrl(x6357) { x6346 } // VectorApply(x6346,0)
    val x6348 = withCtrl(x6357) { ReadMem(x6260_d0).name("x6348").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:56:40") } // RegRead(x6260)
    val x6349 = withCtrl(x6357) { OpDef(op=FixEql, inputs=List(x6347, x6348)).name("x6349").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:56:29") } // FixEql(x6347,x6348)
    val x6350 = withCtrl(x6357) { OpDef(op=MuxOp, inputs=List(x6349, b3210, Const(-1))).name("x6350").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:56:18") } // Mux(x6349,b3210,Const(-1))
    val x6351 = withCtrl(x6357) { ReadMem(x6321_d1).name("x6351").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:57:15") } // RegRead(x6321)
    val x6352 = withCtrl(x6357) { OpDef(op=FixEql, inputs=List(b3210, Const(0))).name("x6352").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:57:15") } // FixEql(b3210,Const(0))
    val x6353 = withCtrl(x6357) { ReduceAccumOp(op=FixMax, input=x6350, accum=x6351).name("x6353").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:57:29") } // Max(x6350,x6351)
    val x6354 = withCtrl(x6357) { OpDef(op=BitAnd, inputs=List(b2968, b2920)).name("x6354").srcCtx("UnrollingBase.scala:28:66") } // And(b2968,b2920)
    val x6355 = withCtrl(x6357) { OpDef(op=BitAnd, inputs=List(x6354, b2911)).name("x6355").srcCtx("UnrollingBase.scala:28:66") } // And(x6354,b2911)
    val x6356_x6321_d0 = withCtrl(x6357) { WriteMem(x6321_d0, x6353).name("x6356_x6321_d0").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:57:15") } // RegWrite(x6321,x6353,x6355)
    antiDepsOf(x6356_x6321_d0)=List(x6351)
    val x6356_x6321_d1 = withCtrl(x6357) { WriteMem(x6321_d1, x6353).name("x6356_x6321_d1").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:57:15") } // RegWrite(x6321,x6353,x6355)
    antiDepsOf(x6356_x6321_d1)=List(x6351)
    val x6358 = withCtrl(x6392) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x6358").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:55:53") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x6359 = withCtrl(x6392) { CounterChain(List(x6358)).name("x6359").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:57:15") } // CounterChainNew(List(x6358))
    val x6374 = withCtrl(x6392) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6359).name("x6374").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:57:15") } // UnrolledReduce(List(b2969, b2920, b2911),x6359,x6322,Block((x6322) => Const(())),List(List(b3227)),List(List(b3228)))
    val b3227 = withCtrl(x6374) { CounterIter(x6358, None).name("b3227") } // b3227
    val b3228 = withCtrl(x6374) { Const(true).name("b3228") } // b3228
    val x6360 = withCtrl(x6374) { OpDef(op=BitAnd, inputs=List(b3228, b2969)).name("x6360").srcCtx("UnrollingBase.scala:28:66") } // And(b3228,b2969)
    val x6361 = withCtrl(x6374) { OpDef(op=BitAnd, inputs=List(b2920, b2911)).name("x6361").srcCtx("UnrollingBase.scala:28:66") } // And(b2920,b2911)
    val x6362 = withCtrl(x6374) { OpDef(op=BitAnd, inputs=List(x6360, x6361)).name("x6362").srcCtx("UnrollingBase.scala:28:66") } // And(x6360,x6361)
    val x6363 = withCtrl(x6374) { LoadBanks(List(x6136_d0_b0), List(b3227)).name("x6363").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:56:24") } // ParSRAMLoad(x6136,List(List(b3227)),List(x6362))
    val x6364 = withCtrl(x6374) { x6363 } // VectorApply(x6363,0)
    val x6365 = withCtrl(x6374) { ReadMem(x6261_d0).name("x6365").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:56:40") } // RegRead(x6261)
    val x6366 = withCtrl(x6374) { OpDef(op=FixEql, inputs=List(x6364, x6365)).name("x6366").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:56:29") } // FixEql(x6364,x6365)
    val x6367 = withCtrl(x6374) { OpDef(op=MuxOp, inputs=List(x6366, b3227, Const(-1))).name("x6367").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:56:18") } // Mux(x6366,b3227,Const(-1))
    val x6368 = withCtrl(x6374) { ReadMem(x6322_d1).name("x6368").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:57:15") } // RegRead(x6322)
    val x6369 = withCtrl(x6374) { OpDef(op=FixEql, inputs=List(b3227, Const(0))).name("x6369").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:57:15") } // FixEql(b3227,Const(0))
    val x6370 = withCtrl(x6374) { ReduceAccumOp(op=FixMax, input=x6367, accum=x6368).name("x6370").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:57:29") } // Max(x6367,x6368)
    val x6371 = withCtrl(x6374) { OpDef(op=BitAnd, inputs=List(b2969, b2920)).name("x6371").srcCtx("UnrollingBase.scala:28:66") } // And(b2969,b2920)
    val x6372 = withCtrl(x6374) { OpDef(op=BitAnd, inputs=List(x6371, b2911)).name("x6372").srcCtx("UnrollingBase.scala:28:66") } // And(x6371,b2911)
    val x6373_x6322_d0 = withCtrl(x6374) { WriteMem(x6322_d0, x6370).name("x6373_x6322_d0").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:57:15") } // RegWrite(x6322,x6370,x6372)
    antiDepsOf(x6373_x6322_d0)=List(x6368)
    val x6373_x6322_d1 = withCtrl(x6374) { WriteMem(x6322_d1, x6370).name("x6373_x6322_d1").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:57:15") } // RegWrite(x6322,x6370,x6372)
    antiDepsOf(x6373_x6322_d1)=List(x6368)
    val x6375 = withCtrl(x6392) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x6375").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:55:53") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x6376 = withCtrl(x6392) { CounterChain(List(x6375)).name("x6376").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:57:15") } // CounterChainNew(List(x6375))
    val x6391 = withCtrl(x6392) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6376).name("x6391").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:57:15") } // UnrolledReduce(List(b2970, b2920, b2911),x6376,x6323,Block((x6323) => Const(())),List(List(b3244)),List(List(b3245)))
    val b3244 = withCtrl(x6391) { CounterIter(x6375, None).name("b3244") } // b3244
    val b3245 = withCtrl(x6391) { Const(true).name("b3245") } // b3245
    val x6377 = withCtrl(x6391) { OpDef(op=BitAnd, inputs=List(b3245, b2970)).name("x6377").srcCtx("UnrollingBase.scala:28:66") } // And(b3245,b2970)
    val x6378 = withCtrl(x6391) { OpDef(op=BitAnd, inputs=List(b2920, b2911)).name("x6378").srcCtx("UnrollingBase.scala:28:66") } // And(b2920,b2911)
    val x6379 = withCtrl(x6391) { OpDef(op=BitAnd, inputs=List(x6377, x6378)).name("x6379").srcCtx("UnrollingBase.scala:28:66") } // And(x6377,x6378)
    val x6380 = withCtrl(x6391) { LoadBanks(List(x6137_d0_b0), List(b3244)).name("x6380").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:56:24") } // ParSRAMLoad(x6137,List(List(b3244)),List(x6379))
    val x6381 = withCtrl(x6391) { x6380 } // VectorApply(x6380,0)
    val x6382 = withCtrl(x6391) { ReadMem(x6262_d0).name("x6382").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:56:40") } // RegRead(x6262)
    val x6383 = withCtrl(x6391) { OpDef(op=FixEql, inputs=List(x6381, x6382)).name("x6383").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:56:29") } // FixEql(x6381,x6382)
    val x6384 = withCtrl(x6391) { OpDef(op=MuxOp, inputs=List(x6383, b3244, Const(-1))).name("x6384").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:56:18") } // Mux(x6383,b3244,Const(-1))
    val x6385 = withCtrl(x6391) { ReadMem(x6323_d1).name("x6385").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:57:15") } // RegRead(x6323)
    val x6386 = withCtrl(x6391) { OpDef(op=FixEql, inputs=List(b3244, Const(0))).name("x6386").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:57:15") } // FixEql(b3244,Const(0))
    val x6387 = withCtrl(x6391) { ReduceAccumOp(op=FixMax, input=x6384, accum=x6385).name("x6387").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:57:29") } // Max(x6384,x6385)
    val x6388 = withCtrl(x6391) { OpDef(op=BitAnd, inputs=List(b2970, b2920)).name("x6388").srcCtx("UnrollingBase.scala:28:66") } // And(b2970,b2920)
    val x6389 = withCtrl(x6391) { OpDef(op=BitAnd, inputs=List(x6388, b2911)).name("x6389").srcCtx("UnrollingBase.scala:28:66") } // And(x6388,b2911)
    val x6390_x6323_d0 = withCtrl(x6391) { WriteMem(x6323_d0, x6387).name("x6390_x6323_d0").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:57:15") } // RegWrite(x6323,x6387,x6389)
    antiDepsOf(x6390_x6323_d0)=List(x6385)
    val x6390_x6323_d1 = withCtrl(x6391) { WriteMem(x6323_d1, x6387).name("x6390_x6323_d1").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:57:15") } // RegWrite(x6323,x6387,x6389)
    antiDepsOf(x6390_x6323_d1)=List(x6385)
    val x6393_d0_b0 = withCtrl(x6496) { SRAM(size=4096, banking=Strided(banks=16, stride=1)).name("x6393_d0_b0").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:59:36:localCent") } // x6393 = SRAMNew(ArrayBuffer(Const(64), Const(64)))
    isAccum(x6393_d0_b0) = false
    bufferDepthOf(x6393_d0_b0) = 2
    staticDimsOf(x6393_d0_b0) = List(64, 64)
    val x6394_d0_b0 = withCtrl(x6496) { SRAM(size=4096, banking=Strided(banks=16, stride=1)).name("x6394_d0_b0").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:59:36:localCent") } // x6394 = SRAMNew(ArrayBuffer(Const(64), Const(64)))
    isAccum(x6394_d0_b0) = false
    bufferDepthOf(x6394_d0_b0) = 2
    staticDimsOf(x6394_d0_b0) = List(64, 64)
    val x6395_d0_b0 = withCtrl(x6496) { SRAM(size=4096, banking=Strided(banks=16, stride=1)).name("x6395_d0_b0").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:59:36:localCent") } // x6395 = SRAMNew(ArrayBuffer(Const(64), Const(64)))
    isAccum(x6395_d0_b0) = false
    bufferDepthOf(x6395_d0_b0) = 2
    staticDimsOf(x6395_d0_b0) = List(64, 64)
    val x6396_d0_b0 = withCtrl(x6496) { SRAM(size=4096, banking=Strided(banks=16, stride=1)).name("x6396_d0_b0").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:59:36:localCent") } // x6396 = SRAMNew(ArrayBuffer(Const(64), Const(64)))
    isAccum(x6396_d0_b0) = false
    bufferDepthOf(x6396_d0_b0) = 2
    staticDimsOf(x6396_d0_b0) = List(64, 64)
    val x6453 = withCtrl(x6496) { UnitController(style=ForkJoin, level=OuterControl).name("x6453").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b2920, b2911),Block(Const(())))
    val x6397 = withCtrl(x6453) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x6397").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:60:31") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x6398 = withCtrl(x6453) { Counter(min=Const(0), max=Const(64), step=Const(1), par=1).name("x6398").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:60:23") } // CounterNew(Const(0),Const(64),Const(1),Const(1))
    val x6399 = withCtrl(x6453) { CounterChain(List(x6398,x6397)).name("x6399").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:60:38") } // CounterChainNew(List(x6398, x6397))
    val x6410 = withCtrl(x6453) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6399).name("x6410").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:60:38") } // UnrolledForeach(List(b2967, b2920, b2911),x6399,Block(Const(())),List(List(b3278), List(b3279)),List(List(b3280), List(b3281)))
    val b3278 = withCtrl(x6410) { CounterIter(x6398, Some(0)).name("b3278") } // b3278
    val b3280 = withCtrl(x6410) { Const(true).name("b3280") } // b3280
    val b3279 = withCtrl(x6410) { CounterIter(x6397, None).name("b3279") } // b3279
    val b3281 = withCtrl(x6410) { Const(true).name("b3281") } // b3281
    val x6400 = withCtrl(x6410) { ReadMem(x6320_d0).name("x6400").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:61:52") } // RegRead(x6320)
    val x6401 = withCtrl(x6410) { OpDef(op=FixEql, inputs=List(b3278, x6400)).name("x6401").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:61:41") } // FixEql(b3278,x6400)
    val x6402 = withCtrl(x6410) { OpDef(op=BitAnd, inputs=List(b3280, b3281)).name("x6402").srcCtx("UnrollingBase.scala:28:66") } // And(b3280,b3281)
    val x6403 = withCtrl(x6410) { OpDef(op=BitAnd, inputs=List(b2967, b2920)).name("x6403").srcCtx("UnrollingBase.scala:28:66") } // And(b2967,b2920)
    val x6404 = withCtrl(x6410) { OpDef(op=BitAnd, inputs=List(x6402, x6403)).name("x6404").srcCtx("UnrollingBase.scala:28:66") } // And(x6402,x6403)
    val x6405 = withCtrl(x6410) { OpDef(op=BitAnd, inputs=List(x6404, b2911)).name("x6405").srcCtx("UnrollingBase.scala:28:66") } // And(x6404,b2911)
    val x6406 = withCtrl(x6410) { LoadBanks(List(x6099_d0_b0), List(b2963, b3279)).name("x6406").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:61:62") } // ParSRAMLoad(x6099,List(List(b2963, b3279)),List(x6405))
    val x6407 = withCtrl(x6410) { x6406 } // VectorApply(x6406,0)
    val x6408 = withCtrl(x6410) { OpDef(op=MuxOp, inputs=List(x6401, x6407, Const(0))).name("x6408").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:61:37") } // Mux(x6401,x6407,Const(0))
    val x6409 = withCtrl(x6410) { StoreBanks(List(List(x6393_d0_b0)), List(b3278, b3279), x6408).name("x6409").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:61:32") } // ParSRAMStore(x6393,List(List(b3278, b3279)),List(x6408),List(x6405))
    val x6411 = withCtrl(x6453) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x6411").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:60:31") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x6412 = withCtrl(x6453) { Counter(min=Const(0), max=Const(64), step=Const(1), par=1).name("x6412").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:60:23") } // CounterNew(Const(0),Const(64),Const(1),Const(1))
    val x6413 = withCtrl(x6453) { CounterChain(List(x6412,x6411)).name("x6413").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:60:38") } // CounterChainNew(List(x6412, x6411))
    val x6424 = withCtrl(x6453) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6413).name("x6424").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:60:38") } // UnrolledForeach(List(b2968, b2920, b2911),x6413,Block(Const(())),List(List(b3293), List(b3294)),List(List(b3295), List(b3296)))
    val b3293 = withCtrl(x6424) { CounterIter(x6412, Some(0)).name("b3293") } // b3293
    val b3295 = withCtrl(x6424) { Const(true).name("b3295") } // b3295
    val b3294 = withCtrl(x6424) { CounterIter(x6411, None).name("b3294") } // b3294
    val b3296 = withCtrl(x6424) { Const(true).name("b3296") } // b3296
    val x6414 = withCtrl(x6424) { ReadMem(x6321_d0).name("x6414").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:61:52") } // RegRead(x6321)
    val x6415 = withCtrl(x6424) { OpDef(op=FixEql, inputs=List(b3293, x6414)).name("x6415").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:61:41") } // FixEql(b3293,x6414)
    val x6416 = withCtrl(x6424) { OpDef(op=BitAnd, inputs=List(b3295, b3296)).name("x6416").srcCtx("UnrollingBase.scala:28:66") } // And(b3295,b3296)
    val x6417 = withCtrl(x6424) { OpDef(op=BitAnd, inputs=List(b2968, b2920)).name("x6417").srcCtx("UnrollingBase.scala:28:66") } // And(b2968,b2920)
    val x6418 = withCtrl(x6424) { OpDef(op=BitAnd, inputs=List(x6416, x6417)).name("x6418").srcCtx("UnrollingBase.scala:28:66") } // And(x6416,x6417)
    val x6419 = withCtrl(x6424) { OpDef(op=BitAnd, inputs=List(x6418, b2911)).name("x6419").srcCtx("UnrollingBase.scala:28:66") } // And(x6418,b2911)
    val x6420 = withCtrl(x6424) { LoadBanks(List(x6099_d0_b1), List(b2964, b3294)).name("x6420").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:61:62") } // ParSRAMLoad(x6099,List(List(b2964, b3294)),List(x6419))
    val x6421 = withCtrl(x6424) { x6420 } // VectorApply(x6420,0)
    val x6422 = withCtrl(x6424) { OpDef(op=MuxOp, inputs=List(x6415, x6421, Const(0))).name("x6422").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:61:37") } // Mux(x6415,x6421,Const(0))
    val x6423 = withCtrl(x6424) { StoreBanks(List(List(x6394_d0_b0)), List(b3293, b3294), x6422).name("x6423").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:61:32") } // ParSRAMStore(x6394,List(List(b3293, b3294)),List(x6422),List(x6419))
    val x6425 = withCtrl(x6453) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x6425").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:60:31") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x6426 = withCtrl(x6453) { Counter(min=Const(0), max=Const(64), step=Const(1), par=1).name("x6426").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:60:23") } // CounterNew(Const(0),Const(64),Const(1),Const(1))
    val x6427 = withCtrl(x6453) { CounterChain(List(x6426,x6425)).name("x6427").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:60:38") } // CounterChainNew(List(x6426, x6425))
    val x6438 = withCtrl(x6453) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6427).name("x6438").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:60:38") } // UnrolledForeach(List(b2969, b2920, b2911),x6427,Block(Const(())),List(List(b3308), List(b3309)),List(List(b3310), List(b3311)))
    val b3308 = withCtrl(x6438) { CounterIter(x6426, Some(0)).name("b3308") } // b3308
    val b3310 = withCtrl(x6438) { Const(true).name("b3310") } // b3310
    val b3309 = withCtrl(x6438) { CounterIter(x6425, None).name("b3309") } // b3309
    val b3311 = withCtrl(x6438) { Const(true).name("b3311") } // b3311
    val x6428 = withCtrl(x6438) { ReadMem(x6322_d0).name("x6428").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:61:52") } // RegRead(x6322)
    val x6429 = withCtrl(x6438) { OpDef(op=FixEql, inputs=List(b3308, x6428)).name("x6429").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:61:41") } // FixEql(b3308,x6428)
    val x6430 = withCtrl(x6438) { OpDef(op=BitAnd, inputs=List(b3310, b3311)).name("x6430").srcCtx("UnrollingBase.scala:28:66") } // And(b3310,b3311)
    val x6431 = withCtrl(x6438) { OpDef(op=BitAnd, inputs=List(b2969, b2920)).name("x6431").srcCtx("UnrollingBase.scala:28:66") } // And(b2969,b2920)
    val x6432 = withCtrl(x6438) { OpDef(op=BitAnd, inputs=List(x6430, x6431)).name("x6432").srcCtx("UnrollingBase.scala:28:66") } // And(x6430,x6431)
    val x6433 = withCtrl(x6438) { OpDef(op=BitAnd, inputs=List(x6432, b2911)).name("x6433").srcCtx("UnrollingBase.scala:28:66") } // And(x6432,b2911)
    val x6434 = withCtrl(x6438) { LoadBanks(List(x6099_d0_b2), List(b2965, b3309)).name("x6434").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:61:62") } // ParSRAMLoad(x6099,List(List(b2965, b3309)),List(x6433))
    val x6435 = withCtrl(x6438) { x6434 } // VectorApply(x6434,0)
    val x6436 = withCtrl(x6438) { OpDef(op=MuxOp, inputs=List(x6429, x6435, Const(0))).name("x6436").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:61:37") } // Mux(x6429,x6435,Const(0))
    val x6437 = withCtrl(x6438) { StoreBanks(List(List(x6395_d0_b0)), List(b3308, b3309), x6436).name("x6437").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:61:32") } // ParSRAMStore(x6395,List(List(b3308, b3309)),List(x6436),List(x6433))
    val x6439 = withCtrl(x6453) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x6439").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:60:31") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x6440 = withCtrl(x6453) { Counter(min=Const(0), max=Const(64), step=Const(1), par=1).name("x6440").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:60:23") } // CounterNew(Const(0),Const(64),Const(1),Const(1))
    val x6441 = withCtrl(x6453) { CounterChain(List(x6440,x6439)).name("x6441").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:60:38") } // CounterChainNew(List(x6440, x6439))
    val x6452 = withCtrl(x6453) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6441).name("x6452").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:60:38") } // UnrolledForeach(List(b2970, b2920, b2911),x6441,Block(Const(())),List(List(b3323), List(b3324)),List(List(b3325), List(b3326)))
    val b3323 = withCtrl(x6452) { CounterIter(x6440, Some(0)).name("b3323") } // b3323
    val b3325 = withCtrl(x6452) { Const(true).name("b3325") } // b3325
    val b3324 = withCtrl(x6452) { CounterIter(x6439, None).name("b3324") } // b3324
    val b3326 = withCtrl(x6452) { Const(true).name("b3326") } // b3326
    val x6442 = withCtrl(x6452) { ReadMem(x6323_d0).name("x6442").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:61:52") } // RegRead(x6323)
    val x6443 = withCtrl(x6452) { OpDef(op=FixEql, inputs=List(b3323, x6442)).name("x6443").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:61:41") } // FixEql(b3323,x6442)
    val x6444 = withCtrl(x6452) { OpDef(op=BitAnd, inputs=List(b3325, b3326)).name("x6444").srcCtx("UnrollingBase.scala:28:66") } // And(b3325,b3326)
    val x6445 = withCtrl(x6452) { OpDef(op=BitAnd, inputs=List(b2970, b2920)).name("x6445").srcCtx("UnrollingBase.scala:28:66") } // And(b2970,b2920)
    val x6446 = withCtrl(x6452) { OpDef(op=BitAnd, inputs=List(x6444, x6445)).name("x6446").srcCtx("UnrollingBase.scala:28:66") } // And(x6444,x6445)
    val x6447 = withCtrl(x6452) { OpDef(op=BitAnd, inputs=List(x6446, b2911)).name("x6447").srcCtx("UnrollingBase.scala:28:66") } // And(x6446,b2911)
    val x6448 = withCtrl(x6452) { LoadBanks(List(x6099_d0_b3), List(b2966, b3324)).name("x6448").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:61:62") } // ParSRAMLoad(x6099,List(List(b2966, b3324)),List(x6447))
    val x6449 = withCtrl(x6452) { x6448 } // VectorApply(x6448,0)
    val x6450 = withCtrl(x6452) { OpDef(op=MuxOp, inputs=List(x6443, x6449, Const(0))).name("x6450").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:61:37") } // Mux(x6443,x6449,Const(0))
    val x6451 = withCtrl(x6452) { StoreBanks(List(List(x6396_d0_b0)), List(b3323, b3324), x6450).name("x6451").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:61:32") } // ParSRAMStore(x6396,List(List(b3323, b3324)),List(x6450),List(x6447))
    val x6454 = withCtrl(x6496) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x6454").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:64:12") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x6455 = withCtrl(x6496) { Counter(min=Const(0), max=Const(64), step=Const(1), par=1).name("x6455").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:64:12") } // CounterNew(Const(0),Const(64),Const(1),Const(1))
    val x6456 = withCtrl(x6496) { CounterChain(List(x6455,x6454)).name("x6456").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:64:12") } // CounterChainNew(ArrayBuffer(x6455, x6454))
    val x6495 = withCtrl(x6496) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6456).name("x6495").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:64:12") } // UnrolledForeach(List(),x6456,Block(Const(())),ArrayBuffer(List(b3339), List(b3340)),ArrayBuffer(List(b3341), List(b3342)))
    val b3339 = withCtrl(x6495) { CounterIter(x6455, Some(0)).name("b3339") } // b3339
    val b3341 = withCtrl(x6495) { Const(true).name("b3341") } // b3341
    val b3340 = withCtrl(x6495) { CounterIter(x6454, None).name("b3340") } // b3340
    val b3342 = withCtrl(x6495) { Const(true).name("b3342") } // b3342
    val x6457 = withCtrl(x6495) { OpDef(op=BitAnd, inputs=List(b3341, b3342)).name("x6457").srcCtx("UnrollingBase.scala:28:66") } // And(b3341,b3342)
    val x6458 = withCtrl(x6495) { OpDef(op=BitAnd, inputs=List(b2920, b2911)).name("x6458").srcCtx("UnrollingBase.scala:28:66") } // And(b2920,b2911)
    val x6459 = withCtrl(x6495) { OpDef(op=BitAnd, inputs=List(x6457, x6458)).name("x6459").srcCtx("UnrollingBase.scala:28:66") } // And(x6457,x6458)
    val x6460 = withCtrl(x6495) { LoadBanks(List(x6393_d0_b0), List(b3339, b3340)).name("x6460").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:64:12") } // ParSRAMLoad(x6393,List(ArrayBuffer(b3339, b3340)),List(x6459))
    val x6461 = withCtrl(x6495) { x6460 } // VectorApply(x6460,0)
    val x6462 = withCtrl(x6495) { LoadBanks(List(x6394_d0_b0), List(b3339, b3340)).name("x6462").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:64:12") } // ParSRAMLoad(x6394,List(ArrayBuffer(b3339, b3340)),List(x6459))
    val x6463 = withCtrl(x6495) { x6462 } // VectorApply(x6462,0)
    val x6464 = withCtrl(x6495) { LoadBanks(List(x6395_d0_b0), List(b3339, b3340)).name("x6464").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:64:12") } // ParSRAMLoad(x6395,List(ArrayBuffer(b3339, b3340)),List(x6459))
    val x6465 = withCtrl(x6495) { x6464 } // VectorApply(x6464,0)
    val x6466 = withCtrl(x6495) { LoadBanks(List(x6396_d0_b0), List(b3339, b3340)).name("x6466").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:64:12") } // ParSRAMLoad(x6396,List(ArrayBuffer(b3339, b3340)),List(x6459))
    val x6467 = withCtrl(x6495) { x6466 } // VectorApply(x6466,0)
    val x6468 = withCtrl(x6495) { LoadBanks(List(x6131_d1_b0), List(b3339, b3340)).name("x6468").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:64:12") } // ParSRAMLoad(x6131,List(ArrayBuffer(b3339, b3340)),List(x6459))
    val x6469 = withCtrl(x6495) { x6468 } // VectorApply(x6468,0)
    val x6470 = withCtrl(x6495) { OpDef(op=BitAnd, inputs=List(b2967, b2920)).name("x6470").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:64:12") } // And(b2967,b2920)
    val x6471 = withCtrl(x6495) { OpDef(op=BitAnd, inputs=List(x6470, b2911)).name("x6471").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:64:12") } // And(x6470,b2911)
    val x6472 = withCtrl(x6495) { OpDef(op=BitAnd, inputs=List(b2968, b2920)).name("x6472").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:64:12") } // And(b2968,b2920)
    val x6473 = withCtrl(x6495) { OpDef(op=BitAnd, inputs=List(x6472, b2911)).name("x6473").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:64:12") } // And(x6472,b2911)
    val x6474 = withCtrl(x6495) { OpDef(op=BitAnd, inputs=List(b2969, b2920)).name("x6474").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:64:12") } // And(b2969,b2920)
    val x6475 = withCtrl(x6495) { OpDef(op=BitAnd, inputs=List(x6474, b2911)).name("x6475").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:64:12") } // And(x6474,b2911)
    val x6476 = withCtrl(x6495) { OpDef(op=BitAnd, inputs=List(b2970, b2920)).name("x6476").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:64:12") } // And(b2970,b2920)
    val x6477 = withCtrl(x6495) { OpDef(op=BitAnd, inputs=List(x6476, b2911)).name("x6477").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:64:12") } // And(x6476,b2911)
    val x6478 = withCtrl(x6495) { OpDef(op=BitAnd, inputs=List(x6471, x6459)).name("x6478").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:64:12") } // And(x6471,x6459)
    val x6479 = withCtrl(x6495) { OpDef(op=BitAnd, inputs=List(x6473, x6459)).name("x6479").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:64:12") } // And(x6473,x6459)
    val x6480 = withCtrl(x6495) { OpDef(op=BitAnd, inputs=List(x6475, x6459)).name("x6480").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:64:12") } // And(x6475,x6459)
    val x6481 = withCtrl(x6495) { OpDef(op=BitAnd, inputs=List(x6477, x6459)).name("x6481").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:64:12") } // And(x6477,x6459)
    val x6482 = withCtrl(x6495) { OpDef(op=FixAdd, inputs=List(x6461, x6463)).name("x6482").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:64:14") } // FixAdd(x6461,x6463)
    val x6483 = withCtrl(x6495) { OpDef(op=MuxOp, inputs=List(x6479, x6482, x6461)).name("x6483").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:64:12") } // Mux(x6479,x6482,x6461)
    val x6484 = withCtrl(x6495) { OpDef(op=BitOr, inputs=List(x6478, x6479)).name("x6484").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:64:12") } // Or(x6478,x6479)
    val x6485 = withCtrl(x6495) { OpDef(op=FixAdd, inputs=List(x6465, x6467)).name("x6485").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:64:14") } // FixAdd(x6465,x6467)
    val x6486 = withCtrl(x6495) { OpDef(op=MuxOp, inputs=List(x6481, x6485, x6465)).name("x6486").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:64:12") } // Mux(x6481,x6485,x6465)
    val x6487 = withCtrl(x6495) { OpDef(op=BitOr, inputs=List(x6480, x6481)).name("x6487").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:64:12") } // Or(x6480,x6481)
    val x6488 = withCtrl(x6495) { OpDef(op=FixAdd, inputs=List(x6483, x6486)).name("x6488").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:64:14") } // FixAdd(x6483,x6486)
    val x6489 = withCtrl(x6495) { OpDef(op=MuxOp, inputs=List(x6487, x6488, x6483)).name("x6489").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:64:12") } // Mux(x6487,x6488,x6483)
    val x6490 = withCtrl(x6495) { OpDef(op=BitOr, inputs=List(x6484, x6487)).name("x6490").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:64:12") } // Or(x6484,x6487)
    val x6491 = withCtrl(x6495) { OpDef(op=FixEql, inputs=List(b2963, Const(0))).name("x6491").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:64:12") } // FixEql(b2963,Const(0))
    val x6492 = withCtrl(x6495) { OpDef(op=FixAdd, inputs=List(x6489, x6469)).name("x6492").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:64:14") } // FixAdd(x6489,x6469)
    val x6493 = withCtrl(x6495) { OpDef(op=MuxOp, inputs=List(x6491, x6489, x6492)).name("x6493").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:64:12") } // Mux(x6491,x6489,x6492)
    val x6494 = withCtrl(x6495) { StoreBanks(List(List(x6131_d0_b0), List(x6131_d1_b0)), List(b3339, b3340), x6493).name("x6494").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:64:12") } // ParSRAMStore(x6131,List(ArrayBuffer(b3339, b3340)),List(x6493),List(x6459))
    antiDepsOf(x6494)=List(x6468)
    val x6497 = withCtrl(x6513) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x6497").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:65:10") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x6498 = withCtrl(x6513) { Counter(min=Const(0), max=Const(64), step=Const(1), par=1).name("x6498").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:65:10") } // CounterNew(Const(0),Const(64),Const(1),Const(1))
    val x6499 = withCtrl(x6513) { CounterChain(List(x6498,x6497)).name("x6499").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:65:10") } // CounterChainNew(ArrayBuffer(x6498, x6497))
    val x6512 = withCtrl(x6513) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6499).name("x6512").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:65:10") } // UnrolledForeach(List(),x6499,Block(Const(())),ArrayBuffer(List(b3383), List(b3384)),ArrayBuffer(List(b3385), List(b3386)))
    val b3383 = withCtrl(x6512) { CounterIter(x6498, Some(0)).name("b3383") } // b3383
    val b3385 = withCtrl(x6512) { Const(true).name("b3385") } // b3385
    val b3384 = withCtrl(x6512) { CounterIter(x6497, None).name("b3384") } // b3384
    val b3386 = withCtrl(x6512) { Const(true).name("b3386") } // b3386
    val x6500 = withCtrl(x6512) { OpDef(op=BitAnd, inputs=List(b3385, b3386)).name("x6500").srcCtx("UnrollingBase.scala:28:66") } // And(b3385,b3386)
    val x6501 = withCtrl(x6512) { OpDef(op=BitAnd, inputs=List(x6500, b2911)).name("x6501").srcCtx("UnrollingBase.scala:28:66") } // And(x6500,b2911)
    val x6502 = withCtrl(x6512) { LoadBanks(List(x6131_d0_b0), List(b3383, b3384)).name("x6502").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:65:10") } // ParSRAMLoad(x6131,List(ArrayBuffer(b3383, b3384)),List(x6501))
    val x6503 = withCtrl(x6512) { x6502 } // VectorApply(x6502,0)
    val x6504 = withCtrl(x6512) { LoadBanks(List(x6095_d2_b0), List(b3383, b3384)).name("x6504").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:65:10") } // ParSRAMLoad(x6095,List(ArrayBuffer(b3383, b3384)),List(x6501))
    val x6505 = withCtrl(x6512) { x6504 } // VectorApply(x6504,0)
    val x6506 = withCtrl(x6512) { OpDef(op=BitAnd, inputs=List(b2920, b2911)).name("x6506").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:65:10") } // And(b2920,b2911)
    val x6507 = withCtrl(x6512) { OpDef(op=BitAnd, inputs=List(x6506, x6501)).name("x6507").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:65:10") } // And(x6506,x6501)
    val x6508 = withCtrl(x6512) { OpDef(op=FixEql, inputs=List(b2919, Const(0))).name("x6508").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:65:10") } // FixEql(b2919,Const(0))
    val x6509 = withCtrl(x6512) { OpDef(op=FixAdd, inputs=List(x6503, x6505)).name("x6509").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:65:12") } // FixAdd(x6503,x6505)
    val x6510 = withCtrl(x6512) { OpDef(op=MuxOp, inputs=List(x6508, x6503, x6509)).name("x6510").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:65:10") } // Mux(x6508,x6503,x6509)
    val x6511 = withCtrl(x6512) { StoreBanks(List(List(x6095_d0_b0), List(x6095_d1_b0), List(x6095_d2_b0)), List(b3383, b3384), x6510).name("x6511").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:65:10") } // ParSRAMStore(x6095,List(ArrayBuffer(b3383, b3384)),List(x6510),List(x6501))
    antiDepsOf(x6511)=List(x6504)
    val x6514 = withCtrl(x6535) { Counter(min=Const(0), max=Const(64), step=Const(1), par=1).name("x6514").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:67:24") } // CounterNew(Const(0),Const(64),Const(1),Const(1))
    val x6515 = withCtrl(x6535) { CounterChain(List(x6514)).name("x6515").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:67:32") } // CounterChainNew(List(x6514))
    val x6534 = withCtrl(x6535) { LoopController(style=MetaPipe, level=OuterControl, cchain=x6515).name("x6534").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:67:32") } // UnrolledForeach(List(b2911),x6515,Block(Const(())),List(List(b3403)),List(List(b3404)))
    val b3403 = withCtrl(x6534) { CounterIter(x6514, Some(0)).name("b3403") } // b3403
    val b3404 = withCtrl(x6534) { Const(true).name("b3404") } // b3404
    val x6516 = withCtrl(x6534) { Reg(init=Some(0)).name("x6516").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:68:33:centCount") } // x6516 = RegNew(Const(0))
    isAccum(x6516) = false
    bufferDepthOf(x6516) = 2
    val x6521 = withCtrl(x6534) { UnitController(style=SeqPipe, level=InnerControl).name("x6521").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:69:16") } // UnitPipe(List(b3404, b2911),Block(x6520))
    val x6517 = withCtrl(x6521) { OpDef(op=BitAnd, inputs=List(b3404, b2911)).name("x6517").srcCtx("UnrollingBase.scala:28:66") } // And(b3404,b2911)
    val x6518 = withCtrl(x6521) { LoadBanks(List(x6095_d1_b0), List(b3403, Const(63))).name("x6518").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:70:38") } // SRAMLoad(x6095,ArrayBuffer(Const(64), Const(64)),List(b3403, Const(63)),Const(0),x6517)
    val x6519 = withCtrl(x6521) { OpDef(op=FixMax, inputs=List(x6518, Const(1))).name("x6519").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:70:29") } // Max(x6518,Const(1))
    val x6520_x6516 = withCtrl(x6521) { WriteMem(x6516, x6519).name("x6520_x6516").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:70:23") } // RegWrite(x6516,x6519,x6517)
    val x6522 = withCtrl(x6534) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x6522").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:72:21") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x6523 = withCtrl(x6534) { CounterChain(List(x6522)).name("x6523").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:72:28") } // CounterChainNew(List(x6522))
    val x6533 = withCtrl(x6534) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6523).name("x6533").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:72:28") } // UnrolledForeach(List(b3404, b2911),x6523,Block(Const(())),List(List(b3413)),List(List(b3414)))
    val b3413 = withCtrl(x6533) { CounterIter(x6522, None).name("b3413") } // b3413
    val b3414 = withCtrl(x6533) { Const(true).name("b3414") } // b3414
    val x6524 = withCtrl(x6533) { ReadMem(x6516).name("x6524").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:73:40") } // RegRead(x6516)
    val x6525 = withCtrl(x6533) { OpDef(op=FixEql, inputs=List(x6524, Const(0))).name("x6525").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:73:40") } // FixEql(x6524,Const(0))
    val x6526 = withCtrl(x6533) { OpDef(op=BitAnd, inputs=List(b3414, b3404)).name("x6526").srcCtx("UnrollingBase.scala:28:66") } // And(b3414,b3404)
    val x6527 = withCtrl(x6533) { OpDef(op=BitAnd, inputs=List(x6526, b2911)).name("x6527").srcCtx("UnrollingBase.scala:28:66") } // And(x6526,b2911)
    val x6528 = withCtrl(x6533) { LoadBanks(List(x6095_d0_b0), List(b3403, b3413)).name("x6528").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:73:69") } // ParSRAMLoad(x6095,List(List(b3403, b3413)),List(x6527))
    val x6529 = withCtrl(x6533) { x6528 } // VectorApply(x6528,0)
    val x6530 = withCtrl(x6533) { OpDef(op=FixDiv, inputs=List(x6529, x6524)).name("x6530").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:73:76") } // FixDiv(x6529,x6524)
    val x6531 = withCtrl(x6533) { OpDef(op=MuxOp, inputs=List(x6525, Const(0), x6530)).name("x6531").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:73:29") } // Mux(x6525,Const(0),x6530)
    val x6532 = withCtrl(x6533) { StoreBanks(List(List(x6067_d0_b0), List(x6067_d1_b0), List(x6067_d2_b0), List(x6067_d3_b0), List(x6067_d4_b0)), List(b3403, b3413), x6531).name("x6532").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:73:24") } // ParSRAMStore(x6067,List(List(b3403, b3413)),List(x6531),List(x6527))
    val x6536 = withCtrl(x6564) { Counter(min=Const(0), max=Const(64), step=Const(1), par=1).name("x6536").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:78:33") } // CounterNew(Const(0),Const(64),Const(1),Const(1))
    val x6537 = withCtrl(x6564) { CounterChain(List(x6536)).name("x6537").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:78:33") } // CounterChainNew(List(x6536))
    val x6563 = withCtrl(x6564) { LoopController(style=StreamPipe, level=OuterControl, cchain=x6537).name("x6563").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:78:33") } // UnrolledForeach(List(Const(true)),x6537,Block(Const(())),List(List(b3429)),List(List(b3430)))
    val b3429 = withCtrl(x6563) { CounterIter(x6536, Some(0)).name("b3429") } // b3429
    val b3430 = withCtrl(x6563) { Const(true).name("b3430") } // b3430
    val b6676 = withCtrl(x6563) { StreamOut(field="offset").name("b6676").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:78:33") } // x6538 = StreamOutNew(BurstCmdBus)
    isAccum(b6676) = false
    bufferDepthOf(b6676) = 1
    val b6677 = withCtrl(x6563) { StreamOut(field="size").name("b6677").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:78:33") } // x6538 = StreamOutNew(BurstCmdBus)
    isAccum(b6677) = false
    bufferDepthOf(b6677) = 1
    val x6539 = withCtrl(x6563) { StreamOut(field="data").name("x6539").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:78:33") } // x6539 = StreamOutNew(BurstFullDataBus())
    isAccum(x6539) = false
    bufferDepthOf(x6539) = 1
    val x6540 = withCtrl(x6563) { StreamIn(field="ack").name("x6540").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:78:33") } // x6540 = StreamInNew(BurstAckBus)
    isAccum(x6540) = false
    bufferDepthOf(x6540) = 1
    val x6551 = withCtrl(x6563) { UnitController(style=SeqPipe, level=InnerControl).name("x6551").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:78:33") } // UnitPipe(List(b3430),Block(x6550))
    val x6541 = withCtrl(x6551) { b3429 } // FixConvert(b3429,TRUE,_32,_0) (Same Type. No op)
    val x6542 = withCtrl(x6551) { OpDef(op=FixSla, inputs=List(x6541, Const(6))).name("x6542").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:78:33") } // FixLsh(x6541,Const(6))
    val x6543 = withCtrl(x6551) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x6544 = withCtrl(x6551) { OpDef(op=FixAdd, inputs=List(x6542, x6543)).name("x6544").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:78:33") } // FixAdd(x6542,x6543)
    val x6545 = withCtrl(x6551) { OpDef(op=FixSla, inputs=List(x6544, Const(2))).name("x6545").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:78:33") } // FixLsh(x6544,Const(2))
    val x6546 = withCtrl(x6551) { x6545 } // FixConvert(x6545,TRUE,_64,_0)
    val x6547 = withCtrl(x6551) { DramAddress(x6063).name("x6547").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:78:33") } // GetDRAMAddress(x6063)
    val x6549_x6548 = withCtrl(x6551) { OpDef(op=FixAdd, inputs=List(x6546, x6547)).name("x6549_x6548").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:78:33") } // FixAdd(x6546,x6547)
    // x6549 = SimpleStruct(ArrayBuffer((offset,x6548), (size,Const(256)), (isLoad,Const(false))))
    val x6550_b6678_b6676 = withCtrl(x6551) { WriteMem(b6676, x6549_x6548).name("x6550_b6678_b6676").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:78:33") } // StreamWrite(x6538,x6549,b3430)
    val x6550_b6679_b6677 = withCtrl(x6551) { WriteMem(b6677, Const(256)).name("x6550_b6679_b6677").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:78:33") } // StreamWrite(x6538,x6549,b3430)
    val x6552 = withCtrl(x6563) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x6552").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:78:33") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x6553 = withCtrl(x6563) { CounterChain(List(x6552)).name("x6553").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:78:33") } // CounterChainNew(List(x6552))
    val x6559 = withCtrl(x6563) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6553).name("x6559").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:78:33") } // UnrolledForeach(List(b3430),x6553,Block(Const(())),List(List(b3447)),List(List(b3448)))
    val b3447 = withCtrl(x6559) { CounterIter(x6552, None).name("b3447") } // b3447
    val b3448 = withCtrl(x6559) { Const(true).name("b3448") } // b3448
    val x6554 = withCtrl(x6559) { OpDef(op=BitAnd, inputs=List(b3448, b3430)).name("x6554").srcCtx("UnrollingBase.scala:28:66") } // And(b3448,b3430)
    val x6555 = withCtrl(x6559) { LoadBanks(List(x6067_d0_b0), List(b3429, b3447)).name("x6555").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:78:33") } // ParSRAMLoad(x6067,List(List(b3429, b3447)),List(x6554))
    val x6557_x6556 = withCtrl(x6559) { x6555 } // VectorApply(x6555,0)
    // x6557 = SimpleStruct(ArrayBuffer((_1,x6556), (_2,Const(true))))
    val x6558_x6558_x6539 = withCtrl(x6559) { WriteMem(x6539, x6557_x6556).name("x6558_x6558_x6539").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:78:33") } // ParStreamWrite(x6539,List(x6557),List(x6554))
    val x6560 = withCtrl(x6563) { FringeDenseStore(dram=List(x6063), cmdStream=List(b6676, b6677), dataStream=List(x6539), ackStream=List(x6540)).name("x6560").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:78:33") } // FringeDenseStore(x6063,x6538,x6539,x6540)
    val x6562 = withCtrl(x6563) { UnitController(style=SeqPipe, level=InnerControl).name("x6562").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:78:33") } // UnitPipe(List(b3430),Block(Const(())))
    val x6561_x6561 = withCtrl(x6562) { ReadMem(x6540).name("x6561_x6561").srcCtx("Kmeans__I_2_N_16384_K_64_D_64_ts_1024_op_1_mp1_4_mp2_1_mp3_1.scala:78:33") } // StreamRead(x6540,b3430)
    }; split1
    
  }
}
