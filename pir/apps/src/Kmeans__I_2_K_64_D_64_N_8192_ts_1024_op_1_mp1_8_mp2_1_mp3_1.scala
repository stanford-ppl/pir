import pir._
import pir.node._
import arch._
import prism.enums._

object Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1 extends PIRApp {
  def main(implicit design:PIRDesign) = {
    import design.pirmeta._
    val x7853 = withCtrl(design.top.topController) { ArgIn(init=0).name("x7853").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:27:22:iters") } // ArgInNew(Const(0))
    isAccum(x7853) = false
    bufferDepthOf(x7853) = 1
    boundOf(x7853) = 2
    val x7854 = withCtrl(design.top.topController) { ArgIn(init=0).name("x7854").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:28:22:N") } // ArgInNew(Const(0))
    isAccum(x7854) = false
    bufferDepthOf(x7854) = 1
    boundOf(x7854) = 8192
    val x7857 = withCtrl(design.top.topController) { ReadMem(x7854).name("x7857").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:33:26") } // RegRead(x7854)
    val x7858 = withCtrl(design.top.topController) { DRAM(dims=List(x7857, Const(64))).name("x7858").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:33:25:points") } // x7858 = DRAMNew(ArrayBuffer(x7857, Const(64)),Const(0))
    val x7859 = withCtrl(design.top.topController) { DRAM(dims=List(Const(64), Const(64))).name("x7859").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:34:28:centroids") } // x7859 = DRAMNew(ArrayBuffer(Const(64), Const(64)),Const(0))
    val x8708 = withCtrl(design.top.topController) { UnitController(style=SeqPipe, level=OuterControl).name("x8708").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:38:11") } // Hwblock(Block(Const(())),false)
    val x7863_d0_b0 = withCtrl(x8708) { SRAM(size=4096, banking=Strided(banks=16, stride=1)).name("x7863_d0_b0").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:39:24:cts") } // x7863 = SRAMNew(ArrayBuffer(Const(64), Const(64)))
    isAccum(x7863_d0_b0) = false
    bufferDepthOf(x7863_d0_b0) = 1
    staticDimsOf(x7863_d0_b0) = List(64, 64)
    val x7863_d1_b0 = withCtrl(x8708) { SRAM(size=4096, banking=Strided(banks=16, stride=1)).name("x7863_d1_b0").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:39:24:cts") } // x7863 = SRAMNew(ArrayBuffer(Const(64), Const(64)))
    isAccum(x7863_d1_b0) = false
    bufferDepthOf(x7863_d1_b0) = 1
    staticDimsOf(x7863_d1_b0) = List(64, 64)
    val x7863_d2_b0 = withCtrl(x8708) { SRAM(size=4096, banking=Strided(banks=16, stride=1)).name("x7863_d2_b0").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:39:24:cts") } // x7863 = SRAMNew(ArrayBuffer(Const(64), Const(64)))
    isAccum(x7863_d2_b0) = false
    bufferDepthOf(x7863_d2_b0) = 1
    staticDimsOf(x7863_d2_b0) = List(64, 64)
    val x7863_d3_b0 = withCtrl(x8708) { SRAM(size=4096, banking=Strided(banks=16, stride=1)).name("x7863_d3_b0").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:39:24:cts") } // x7863 = SRAMNew(ArrayBuffer(Const(64), Const(64)))
    isAccum(x7863_d3_b0) = false
    bufferDepthOf(x7863_d3_b0) = 1
    staticDimsOf(x7863_d3_b0) = List(64, 64)
    val x7863_d4_b0 = withCtrl(x8708) { SRAM(size=4096, banking=Strided(banks=16, stride=1)).name("x7863_d4_b0").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:39:24:cts") } // x7863 = SRAMNew(ArrayBuffer(Const(64), Const(64)))
    isAccum(x7863_d4_b0) = false
    bufferDepthOf(x7863_d4_b0) = 1
    staticDimsOf(x7863_d4_b0) = List(64, 64)
    val x7863_d5_b0 = withCtrl(x8708) { SRAM(size=4096, banking=Strided(banks=16, stride=1)).name("x7863_d5_b0").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:39:24:cts") } // x7863 = SRAMNew(ArrayBuffer(Const(64), Const(64)))
    isAccum(x7863_d5_b0) = false
    bufferDepthOf(x7863_d5_b0) = 1
    staticDimsOf(x7863_d5_b0) = List(64, 64)
    val x7863_d6_b0 = withCtrl(x8708) { SRAM(size=4096, banking=Strided(banks=16, stride=1)).name("x7863_d6_b0").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:39:24:cts") } // x7863 = SRAMNew(ArrayBuffer(Const(64), Const(64)))
    isAccum(x7863_d6_b0) = false
    bufferDepthOf(x7863_d6_b0) = 1
    staticDimsOf(x7863_d6_b0) = List(64, 64)
    val x7863_d7_b0 = withCtrl(x8708) { SRAM(size=4096, banking=Strided(banks=16, stride=1)).name("x7863_d7_b0").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:39:24:cts") } // x7863 = SRAMNew(ArrayBuffer(Const(64), Const(64)))
    isAccum(x7863_d7_b0) = false
    bufferDepthOf(x7863_d7_b0) = 1
    staticDimsOf(x7863_d7_b0) = List(64, 64)
    val x7863_d8_b0 = withCtrl(x8708) { SRAM(size=4096, banking=Strided(banks=16, stride=1)).name("x7863_d8_b0").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:39:24:cts") } // x7863 = SRAMNew(ArrayBuffer(Const(64), Const(64)))
    isAccum(x7863_d8_b0) = false
    bufferDepthOf(x7863_d8_b0) = 1
    staticDimsOf(x7863_d8_b0) = List(64, 64)
    val x7864 = withCtrl(x8708) { Counter(min=Const(0), max=Const(64), step=Const(1), par=1).name("x7864").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:41:11") } // CounterNew(Const(0),Const(64),Const(1),Const(1))
    val x7865 = withCtrl(x8708) { CounterChain(List(x7864)).name("x7865").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:41:11") } // CounterChainNew(List(x7864))
    val x7887 = withCtrl(x8708) { LoopController(style=StreamPipe, level=OuterControl, cchain=x7865).name("x7887").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:41:11") } // UnrolledForeach(List(Const(true)),x7865,Block(Const(())),List(List(b2881)),List(List(b2882)))
    val b2881 = withCtrl(x7887) { CounterIter(x7864, Some(0)).name("b2881") } // b2881
    val b2882 = withCtrl(x7887) { Const(true).name("b2882") } // b2882
    val b8812 = withCtrl(x7887) { StreamOut(field="offset").name("b8812").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:41:11") } // x7866 = StreamOutNew(BurstCmdBus)
    isAccum(b8812) = false
    bufferDepthOf(b8812) = 1
    val b8813 = withCtrl(x7887) { StreamOut(field="size").name("b8813").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:41:11") } // x7866 = StreamOutNew(BurstCmdBus)
    isAccum(b8813) = false
    bufferDepthOf(b8813) = 1
    val x7867 = withCtrl(x7887) { StreamIn(field="data").name("x7867").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:41:11") } // x7867 = StreamInNew(BurstDataBus())
    isAccum(x7867) = false
    bufferDepthOf(x7867) = 1
    val x7878 = withCtrl(x7887) { UnitController(style=SeqPipe, level=InnerControl).name("x7878").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:41:11") } // UnitPipe(List(b2882),Block(x7877))
    val x7868 = withCtrl(x7878) { b2881 } // FixConvert(b2881,TRUE,_32,_0) (Same Type. No op)
    val x7869 = withCtrl(x7878) { OpDef(op=FixSla, inputs=List(x7868, Const(6))).name("x7869").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:41:11") } // FixLsh(x7868,Const(6))
    val x7870 = withCtrl(x7878) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x7871 = withCtrl(x7878) { OpDef(op=FixAdd, inputs=List(x7869, x7870)).name("x7871").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:41:11") } // FixAdd(x7869,x7870)
    val x7872 = withCtrl(x7878) { OpDef(op=FixSla, inputs=List(x7871, Const(2))).name("x7872").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:41:11") } // FixLsh(x7871,Const(2))
    val x7873 = withCtrl(x7878) { x7872 } // FixConvert(x7872,TRUE,_64,_0)
    val x7874 = withCtrl(x7878) { DramAddress(x7858).name("x7874").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:41:11") } // GetDRAMAddress(x7858)
    val x7876_x7875 = withCtrl(x7878) { OpDef(op=FixAdd, inputs=List(x7873, x7874)).name("x7876_x7875").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:41:11") } // FixAdd(x7873,x7874)
    // x7876 = SimpleStruct(ArrayBuffer((offset,x7875), (size,Const(256)), (isLoad,Const(true))))
    val x7877_b8814_b8812 = withCtrl(x7878) { WriteMem(b8812, x7876_x7875).name("x7877_b8814_b8812").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:41:11") } // StreamWrite(x7866,x7876,b2882)
    val x7877_b8815_b8813 = withCtrl(x7878) { WriteMem(b8813, Const(256)).name("x7877_b8815_b8813").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:41:11") } // StreamWrite(x7866,x7876,b2882)
    val x7879 = withCtrl(x7887) { FringeDenseLoad(dram=List(x7858), cmdStream=List(b8812, b8813), dataStream=List(x7867)).name("x7879").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:41:11") } // FringeDenseLoad(x7858,x7866,x7867)
    val x7880 = withCtrl(x7887) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x7880").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:41:11") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x7881 = withCtrl(x7887) { CounterChain(List(x7880)).name("x7881").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:41:11") } // CounterChainNew(List(x7880))
    val x7886 = withCtrl(x7887) { LoopController(style=InnerPipe, level=InnerControl, cchain=x7881).name("x7886").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:41:11") } // UnrolledForeach(List(b2882),x7881,Block(Const(())),List(List(b2899)),List(List(b2900)))
    val b2899 = withCtrl(x7886) { CounterIter(x7880, None).name("b2899") } // b2899
    val b2900 = withCtrl(x7886) { Const(true).name("b2900") } // b2900
    val x7882 = withCtrl(x7886) { OpDef(op=BitAnd, inputs=List(b2900, b2882)).name("x7882").srcCtx("UnrollingBase.scala:28:66") } // And(b2900,b2882)
    val x7883_x7883 = withCtrl(x7886) { ReadMem(x7867).name("x7883_x7883").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:41:11") } // ParStreamRead(x7867,List(x7882))
    val x7884_x7884 = withCtrl(x7886) { x7883_x7883 } // VectorApply(x7883,0)
    val x7885 = withCtrl(x7886) { StoreBanks(List(List(x7863_d0_b0), List(x7863_d5_b0), List(x7863_d1_b0), List(x7863_d6_b0), List(x7863_d2_b0), List(x7863_d7_b0), List(x7863_d3_b0), List(x7863_d8_b0), List(x7863_d4_b0)), List(b2881, b2899), x7884_x7884).name("x7885").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:41:11") } // ParSRAMStore(x7863,List(List(b2881, b2899)),List(x7884),List(x7882))
    val x7888 = withCtrl(x8708) { ReadMem(x7853).name("x7888").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:43:26") } // RegRead(x7853)
    val x7889 = withCtrl(x8708) { Counter(min=Const(0), max=x7888, step=Const(1), par=1).name("x7889").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:43:32") } // CounterNew(Const(0),x7888,Const(1),Const(1))
    val x7890 = withCtrl(x8708) { CounterChain(List(x7889)).name("x7890").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:43:37") } // CounterChainNew(List(x7889))
    val x8679 = withCtrl(x8708) { LoopController(style=SeqPipe, level=OuterControl, cchain=x7890).name("x8679").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:43:37") } // UnrolledForeach(List(Const(true)),x7890,Block(Const(())),List(List(b2910)),List(List(b2911)))
    val b2910 = withCtrl(x8679) { CounterIter(x7889, Some(0)).name("b2910") } // b2910
    val b2911 = withCtrl(x8679) { Const(true).name("b2911") } // b2911
    val x7891_d0_b0 = withCtrl(x8679) { SRAM(size=4096, banking=Strided(banks=16, stride=1)).name("x7891_d0_b0").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:44:41:newCents") } // x7891 = SRAMNew(ArrayBuffer(Const(64), Const(64)))
    isAccum(x7891_d0_b0) = false
    bufferDepthOf(x7891_d0_b0) = 1
    staticDimsOf(x7891_d0_b0) = List(64, 64)
    val x7891_d1_b0 = withCtrl(x8679) { SRAM(size=4096, banking=Strided(banks=16, stride=1)).name("x7891_d1_b0").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:44:41:newCents") } // x7891 = SRAMNew(ArrayBuffer(Const(64), Const(64)))
    isAccum(x7891_d1_b0) = false
    bufferDepthOf(x7891_d1_b0) = 1
    staticDimsOf(x7891_d1_b0) = List(64, 64)
    val x7891_d2_b0 = withCtrl(x8679) { SRAM(size=4096, banking=Strided(banks=16, stride=1)).name("x7891_d2_b0").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:44:41:newCents") } // x7891 = SRAMNew(ArrayBuffer(Const(64), Const(64)))
    isAccum(x7891_d2_b0) = true
    bufferDepthOf(x7891_d2_b0) = 1
    staticDimsOf(x7891_d2_b0) = List(64, 64)
    val x7892 = withCtrl(x8679) { ReadMem(x7854).name("x7892").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:44:55") } // RegRead(x7854)
    val x7893 = withCtrl(x8679) { Counter(min=Const(0), max=x7892, step=Const(1024), par=1).name("x7893").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:44:63") } // CounterNew(Const(0),x7892,Const(1024),Const(1))
    val x7894 = withCtrl(x8679) { CounterChain(List(x7893)).name("x7894").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:65:10") } // CounterChainNew(List(x7893))
    val x8657 = withCtrl(x8679) { LoopController(style=MetaPipe, level=OuterControl, cchain=x7894).name("x8657").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:65:10") } // UnrolledReduce(List(b2911),x7894,x7891,Block((x7891) => Const(())),List(List(b2919)),List(List(b2920)))
    val b2919 = withCtrl(x8657) { CounterIter(x7893, Some(0)).name("b2919") } // b2919
    val b2920 = withCtrl(x8657) { Const(true).name("b2920") } // b2920
    val x7895_d0_b0 = withCtrl(x8657) { SRAM(size=8192, banking=Strided(banks=16, stride=1)).name("x7895_d0_b0").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:45:28:pts") } // x7895 = SRAMNew(ArrayBuffer(Const(1024), Const(64)))
    isAccum(x7895_d0_b0) = false
    bufferDepthOf(x7895_d0_b0) = 2
    staticDimsOf(x7895_d0_b0) = List(1024, 64)
    val x7895_d0_b1 = withCtrl(x8657) { SRAM(size=8192, banking=Strided(banks=16, stride=1)).name("x7895_d0_b1").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:45:28:pts") } // x7895 = SRAMNew(ArrayBuffer(Const(1024), Const(64)))
    isAccum(x7895_d0_b1) = false
    bufferDepthOf(x7895_d0_b1) = 2
    staticDimsOf(x7895_d0_b1) = List(1024, 64)
    val x7895_d0_b2 = withCtrl(x8657) { SRAM(size=8192, banking=Strided(banks=16, stride=1)).name("x7895_d0_b2").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:45:28:pts") } // x7895 = SRAMNew(ArrayBuffer(Const(1024), Const(64)))
    isAccum(x7895_d0_b2) = false
    bufferDepthOf(x7895_d0_b2) = 2
    staticDimsOf(x7895_d0_b2) = List(1024, 64)
    val x7895_d0_b3 = withCtrl(x8657) { SRAM(size=8192, banking=Strided(banks=16, stride=1)).name("x7895_d0_b3").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:45:28:pts") } // x7895 = SRAMNew(ArrayBuffer(Const(1024), Const(64)))
    isAccum(x7895_d0_b3) = false
    bufferDepthOf(x7895_d0_b3) = 2
    staticDimsOf(x7895_d0_b3) = List(1024, 64)
    val x7895_d0_b4 = withCtrl(x8657) { SRAM(size=8192, banking=Strided(banks=16, stride=1)).name("x7895_d0_b4").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:45:28:pts") } // x7895 = SRAMNew(ArrayBuffer(Const(1024), Const(64)))
    isAccum(x7895_d0_b4) = false
    bufferDepthOf(x7895_d0_b4) = 2
    staticDimsOf(x7895_d0_b4) = List(1024, 64)
    val x7895_d0_b5 = withCtrl(x8657) { SRAM(size=8192, banking=Strided(banks=16, stride=1)).name("x7895_d0_b5").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:45:28:pts") } // x7895 = SRAMNew(ArrayBuffer(Const(1024), Const(64)))
    isAccum(x7895_d0_b5) = false
    bufferDepthOf(x7895_d0_b5) = 2
    staticDimsOf(x7895_d0_b5) = List(1024, 64)
    val x7895_d0_b6 = withCtrl(x8657) { SRAM(size=8192, banking=Strided(banks=16, stride=1)).name("x7895_d0_b6").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:45:28:pts") } // x7895 = SRAMNew(ArrayBuffer(Const(1024), Const(64)))
    isAccum(x7895_d0_b6) = false
    bufferDepthOf(x7895_d0_b6) = 2
    staticDimsOf(x7895_d0_b6) = List(1024, 64)
    val x7895_d0_b7 = withCtrl(x8657) { SRAM(size=8192, banking=Strided(banks=16, stride=1)).name("x7895_d0_b7").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:45:28:pts") } // x7895 = SRAMNew(ArrayBuffer(Const(1024), Const(64)))
    isAccum(x7895_d0_b7) = false
    bufferDepthOf(x7895_d0_b7) = 2
    staticDimsOf(x7895_d0_b7) = List(1024, 64)
    val x7895_d1_b0 = withCtrl(x8657) { SRAM(size=8192, banking=Strided(banks=16, stride=1)).name("x7895_d1_b0").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:45:28:pts") } // x7895 = SRAMNew(ArrayBuffer(Const(1024), Const(64)))
    isAccum(x7895_d1_b0) = false
    bufferDepthOf(x7895_d1_b0) = 2
    staticDimsOf(x7895_d1_b0) = List(1024, 64)
    val x7895_d1_b1 = withCtrl(x8657) { SRAM(size=8192, banking=Strided(banks=16, stride=1)).name("x7895_d1_b1").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:45:28:pts") } // x7895 = SRAMNew(ArrayBuffer(Const(1024), Const(64)))
    isAccum(x7895_d1_b1) = false
    bufferDepthOf(x7895_d1_b1) = 2
    staticDimsOf(x7895_d1_b1) = List(1024, 64)
    val x7895_d1_b2 = withCtrl(x8657) { SRAM(size=8192, banking=Strided(banks=16, stride=1)).name("x7895_d1_b2").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:45:28:pts") } // x7895 = SRAMNew(ArrayBuffer(Const(1024), Const(64)))
    isAccum(x7895_d1_b2) = false
    bufferDepthOf(x7895_d1_b2) = 2
    staticDimsOf(x7895_d1_b2) = List(1024, 64)
    val x7895_d1_b3 = withCtrl(x8657) { SRAM(size=8192, banking=Strided(banks=16, stride=1)).name("x7895_d1_b3").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:45:28:pts") } // x7895 = SRAMNew(ArrayBuffer(Const(1024), Const(64)))
    isAccum(x7895_d1_b3) = false
    bufferDepthOf(x7895_d1_b3) = 2
    staticDimsOf(x7895_d1_b3) = List(1024, 64)
    val x7895_d1_b4 = withCtrl(x8657) { SRAM(size=8192, banking=Strided(banks=16, stride=1)).name("x7895_d1_b4").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:45:28:pts") } // x7895 = SRAMNew(ArrayBuffer(Const(1024), Const(64)))
    isAccum(x7895_d1_b4) = false
    bufferDepthOf(x7895_d1_b4) = 2
    staticDimsOf(x7895_d1_b4) = List(1024, 64)
    val x7895_d1_b5 = withCtrl(x8657) { SRAM(size=8192, banking=Strided(banks=16, stride=1)).name("x7895_d1_b5").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:45:28:pts") } // x7895 = SRAMNew(ArrayBuffer(Const(1024), Const(64)))
    isAccum(x7895_d1_b5) = false
    bufferDepthOf(x7895_d1_b5) = 2
    staticDimsOf(x7895_d1_b5) = List(1024, 64)
    val x7895_d1_b6 = withCtrl(x8657) { SRAM(size=8192, banking=Strided(banks=16, stride=1)).name("x7895_d1_b6").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:45:28:pts") } // x7895 = SRAMNew(ArrayBuffer(Const(1024), Const(64)))
    isAccum(x7895_d1_b6) = false
    bufferDepthOf(x7895_d1_b6) = 2
    staticDimsOf(x7895_d1_b6) = List(1024, 64)
    val x7895_d1_b7 = withCtrl(x8657) { SRAM(size=8192, banking=Strided(banks=16, stride=1)).name("x7895_d1_b7").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:45:28:pts") } // x7895 = SRAMNew(ArrayBuffer(Const(1024), Const(64)))
    isAccum(x7895_d1_b7) = false
    bufferDepthOf(x7895_d1_b7) = 2
    staticDimsOf(x7895_d1_b7) = List(1024, 64)
    val x7897 = withCtrl(x8657) { UnitController(style=SeqPipe, level=InnerControl).name("x7897").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:65:10") } // UnitPipe(List(b2920, b2911),Block(Const(())))
    val x7896 = withCtrl(x7897) { OpDef(op=FixAdd, inputs=List(b2919, Const(1024))).name("x7896").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:46:31") } // FixAdd(b2919,Const(1024))
    val x7898 = withCtrl(x8657) { Counter(min=Const(0), max=Const(1024), step=Const(1), par=1).name("x7898").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:46:15") } // CounterNew(Const(0),Const(1024),Const(1),Const(1))
    val x7899 = withCtrl(x8657) { CounterChain(List(x7898)).name("x7899").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:46:15") } // CounterChainNew(List(x7898))
    val x7926 = withCtrl(x8657) { LoopController(style=StreamPipe, level=OuterControl, cchain=x7899).name("x7926").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:46:15") } // UnrolledForeach(List(b2920, b2911),x7899,Block(Const(())),List(List(b2926)),List(List(b2927)))
    val b2926 = withCtrl(x7926) { CounterIter(x7898, Some(0)).name("b2926") } // b2926
    val b2927 = withCtrl(x7926) { Const(true).name("b2927") } // b2927
    val b8816 = withCtrl(x7926) { StreamOut(field="offset").name("b8816").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:46:15") } // x7900 = StreamOutNew(BurstCmdBus)
    isAccum(b8816) = false
    bufferDepthOf(b8816) = 1
    val b8817 = withCtrl(x7926) { StreamOut(field="size").name("b8817").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:46:15") } // x7900 = StreamOutNew(BurstCmdBus)
    isAccum(b8817) = false
    bufferDepthOf(b8817) = 1
    val x7901 = withCtrl(x7926) { StreamIn(field="data").name("x7901").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:46:15") } // x7901 = StreamInNew(BurstDataBus())
    isAccum(x7901) = false
    bufferDepthOf(x7901) = 1
    val x7915 = withCtrl(x7926) { UnitController(style=SeqPipe, level=InnerControl).name("x7915").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:46:15") } // UnitPipe(List(b2927, b2920, b2911),Block(x7914))
    val x7902 = withCtrl(x7915) { OpDef(op=FixAdd, inputs=List(b2919, b2926)).name("x7902").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:46:15") } // FixAdd(b2919,b2926)
    val x7903 = withCtrl(x7915) { x7902 } // FixConvert(x7902,TRUE,_32,_0) (Same Type. No op)
    val x7904 = withCtrl(x7915) { OpDef(op=FixSla, inputs=List(x7903, Const(6))).name("x7904").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:46:15") } // FixLsh(x7903,Const(6))
    val x7905 = withCtrl(x7915) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x7906 = withCtrl(x7915) { OpDef(op=FixAdd, inputs=List(x7904, x7905)).name("x7906").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:46:15") } // FixAdd(x7904,x7905)
    val x7907 = withCtrl(x7915) { OpDef(op=FixSla, inputs=List(x7906, Const(2))).name("x7907").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:46:15") } // FixLsh(x7906,Const(2))
    val x7908 = withCtrl(x7915) { x7907 } // FixConvert(x7907,TRUE,_64,_0)
    val x7909 = withCtrl(x7915) { DramAddress(x7858).name("x7909").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:46:15") } // GetDRAMAddress(x7858)
    val x7911_x7910 = withCtrl(x7915) { OpDef(op=FixAdd, inputs=List(x7908, x7909)).name("x7911_x7910").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:46:15") } // FixAdd(x7908,x7909)
    // x7911 = SimpleStruct(ArrayBuffer((offset,x7910), (size,Const(256)), (isLoad,Const(true))))
    val x7912 = withCtrl(x7915) { OpDef(op=BitAnd, inputs=List(b2927, b2920)).name("x7912").srcCtx("UnrollingBase.scala:28:66") } // And(b2927,b2920)
    val x7913 = withCtrl(x7915) { OpDef(op=BitAnd, inputs=List(x7912, b2911)).name("x7913").srcCtx("UnrollingBase.scala:28:66") } // And(x7912,b2911)
    val x7914_b8818_b8816 = withCtrl(x7915) { WriteMem(b8816, x7911_x7910).name("x7914_b8818_b8816").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:46:15") } // StreamWrite(x7900,x7911,x7913)
    val x7914_b8819_b8817 = withCtrl(x7915) { WriteMem(b8817, Const(256)).name("x7914_b8819_b8817").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:46:15") } // StreamWrite(x7900,x7911,x7913)
    val x7916 = withCtrl(x7926) { FringeDenseLoad(dram=List(x7858), cmdStream=List(b8816, b8817), dataStream=List(x7901)).name("x7916").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:46:15") } // FringeDenseLoad(x7858,x7900,x7901)
    val x7917 = withCtrl(x7926) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x7917").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:46:15") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x7918 = withCtrl(x7926) { CounterChain(List(x7917)).name("x7918").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:46:15") } // CounterChainNew(List(x7917))
    val x7925 = withCtrl(x7926) { LoopController(style=InnerPipe, level=InnerControl, cchain=x7918).name("x7925").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:46:15") } // UnrolledForeach(List(b2927, b2920, b2911),x7918,Block(Const(())),List(List(b2947)),List(List(b2948)))
    val b2947 = withCtrl(x7925) { CounterIter(x7917, None).name("b2947") } // b2947
    val b2948 = withCtrl(x7925) { Const(true).name("b2948") } // b2948
    val x7919 = withCtrl(x7925) { OpDef(op=BitAnd, inputs=List(b2948, b2927)).name("x7919").srcCtx("UnrollingBase.scala:28:66") } // And(b2948,b2927)
    val x7920 = withCtrl(x7925) { OpDef(op=BitAnd, inputs=List(b2920, b2911)).name("x7920").srcCtx("UnrollingBase.scala:28:66") } // And(b2920,b2911)
    val x7921 = withCtrl(x7925) { OpDef(op=BitAnd, inputs=List(x7919, x7920)).name("x7921").srcCtx("UnrollingBase.scala:28:66") } // And(x7919,x7920)
    val x7922_x7922 = withCtrl(x7925) { ReadMem(x7901).name("x7922_x7922").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:46:15") } // ParStreamRead(x7901,List(x7921))
    val x7923_x7923 = withCtrl(x7925) { x7922_x7922 } // VectorApply(x7922,0)
    val x7924 = withCtrl(x7925) { StoreBanks(List(List(x7895_d0_b0, x7895_d0_b1, x7895_d0_b2, x7895_d0_b3, x7895_d0_b4, x7895_d0_b5, x7895_d0_b6, x7895_d0_b7), List(x7895_d1_b0, x7895_d1_b1, x7895_d1_b2, x7895_d1_b3, x7895_d1_b4, x7895_d1_b5, x7895_d1_b6, x7895_d1_b7)), List(b2926, b2947), x7923_x7923).name("x7924").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:46:15") } // ParSRAMStore(x7895,List(List(b2926, b2947)),List(x7923),List(x7921))
    val x7927_d0_b0 = withCtrl(x8657) { SRAM(size=4096, banking=Strided(banks=16, stride=1)).name("x7927_d0_b0").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:48:28") } // x7927 = SRAMNew(ArrayBuffer(Const(64), Const(64)))
    isAccum(x7927_d0_b0) = false
    bufferDepthOf(x7927_d0_b0) = 2
    staticDimsOf(x7927_d0_b0) = List(64, 64)
    val x7927_d1_b0 = withCtrl(x8657) { SRAM(size=4096, banking=Strided(banks=16, stride=1)).name("x7927_d1_b0").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:48:28") } // x7927 = SRAMNew(ArrayBuffer(Const(64), Const(64)))
    isAccum(x7927_d1_b0) = true
    bufferDepthOf(x7927_d1_b0) = 1
    staticDimsOf(x7927_d1_b0) = List(64, 64)
    val x7928 = withCtrl(x8657) { Counter(min=Const(0), max=Const(1024), step=Const(1), par=8).name("x7928").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:48:45") } // CounterNew(Const(0),Const(1024),Const(1),Const(8))
    val x7929 = withCtrl(x8657) { CounterChain(List(x7928)).name("x7929").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:64:12") } // CounterChainNew(List(x7928))
    val x8640 = withCtrl(x8657) { LoopController(style=MetaPipe, level=OuterControl, cchain=x7929).name("x8640").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:64:12") } // UnrolledReduce(List(b2920, b2911),x7929,x7927,Block((x7927) => Const(())),List(List(b2963, b2964, b2965, b2966, b2967, b2968, b2969, b2970)),List(List(b2971, b2972, b2973, b2974, b2975, b2976, b2977, b2978)))
    val b2963 = withCtrl(x8640) { CounterIter(x7928, Some(0)).name("b2963") } // b2963
    val b2971 = withCtrl(x8640) { Const(true).name("b2971") } // b2971
    val b2964 = withCtrl(x8640) { CounterIter(x7928, Some(1)).name("b2964") } // b2964
    val b2972 = withCtrl(x8640) { Const(true).name("b2972") } // b2972
    val b2965 = withCtrl(x8640) { CounterIter(x7928, Some(2)).name("b2965") } // b2965
    val b2973 = withCtrl(x8640) { Const(true).name("b2973") } // b2973
    val b2966 = withCtrl(x8640) { CounterIter(x7928, Some(3)).name("b2966") } // b2966
    val b2974 = withCtrl(x8640) { Const(true).name("b2974") } // b2974
    val b2967 = withCtrl(x8640) { CounterIter(x7928, Some(4)).name("b2967") } // b2967
    val b2975 = withCtrl(x8640) { Const(true).name("b2975") } // b2975
    val b2968 = withCtrl(x8640) { CounterIter(x7928, Some(5)).name("b2968") } // b2968
    val b2976 = withCtrl(x8640) { Const(true).name("b2976") } // b2976
    val b2969 = withCtrl(x8640) { CounterIter(x7928, Some(6)).name("b2969") } // b2969
    val b2977 = withCtrl(x8640) { Const(true).name("b2977") } // b2977
    val b2970 = withCtrl(x8640) { CounterIter(x7928, Some(7)).name("b2970") } // b2970
    val b2978 = withCtrl(x8640) { Const(true).name("b2978") } // b2978
    val x7930_d0_b0 = withCtrl(x8640) { SRAM(size=64, banking=Strided(banks=16, stride=1)).name("x7930_d0_b0").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:49:32:dists") } // x7930 = SRAMNew(ArrayBuffer(Const(64)))
    isAccum(x7930_d0_b0) = false
    bufferDepthOf(x7930_d0_b0) = 3
    staticDimsOf(x7930_d0_b0) = List(64)
    val x7930_d1_b0 = withCtrl(x8640) { SRAM(size=64, banking=Strided(banks=16, stride=1)).name("x7930_d1_b0").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:49:32:dists") } // x7930 = SRAMNew(ArrayBuffer(Const(64)))
    isAccum(x7930_d1_b0) = false
    bufferDepthOf(x7930_d1_b0) = 2
    staticDimsOf(x7930_d1_b0) = List(64)
    val x7931_d0_b0 = withCtrl(x8640) { SRAM(size=64, banking=Strided(banks=16, stride=1)).name("x7931_d0_b0").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:49:32:dists") } // x7931 = SRAMNew(ArrayBuffer(Const(64)))
    isAccum(x7931_d0_b0) = false
    bufferDepthOf(x7931_d0_b0) = 3
    staticDimsOf(x7931_d0_b0) = List(64)
    val x7931_d1_b0 = withCtrl(x8640) { SRAM(size=64, banking=Strided(banks=16, stride=1)).name("x7931_d1_b0").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:49:32:dists") } // x7931 = SRAMNew(ArrayBuffer(Const(64)))
    isAccum(x7931_d1_b0) = false
    bufferDepthOf(x7931_d1_b0) = 2
    staticDimsOf(x7931_d1_b0) = List(64)
    val x7932_d0_b0 = withCtrl(x8640) { SRAM(size=64, banking=Strided(banks=16, stride=1)).name("x7932_d0_b0").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:49:32:dists") } // x7932 = SRAMNew(ArrayBuffer(Const(64)))
    isAccum(x7932_d0_b0) = false
    bufferDepthOf(x7932_d0_b0) = 3
    staticDimsOf(x7932_d0_b0) = List(64)
    val x7932_d1_b0 = withCtrl(x8640) { SRAM(size=64, banking=Strided(banks=16, stride=1)).name("x7932_d1_b0").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:49:32:dists") } // x7932 = SRAMNew(ArrayBuffer(Const(64)))
    isAccum(x7932_d1_b0) = false
    bufferDepthOf(x7932_d1_b0) = 2
    staticDimsOf(x7932_d1_b0) = List(64)
    val x7933_d0_b0 = withCtrl(x8640) { SRAM(size=64, banking=Strided(banks=16, stride=1)).name("x7933_d0_b0").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:49:32:dists") } // x7933 = SRAMNew(ArrayBuffer(Const(64)))
    isAccum(x7933_d0_b0) = false
    bufferDepthOf(x7933_d0_b0) = 3
    staticDimsOf(x7933_d0_b0) = List(64)
    val x7933_d1_b0 = withCtrl(x8640) { SRAM(size=64, banking=Strided(banks=16, stride=1)).name("x7933_d1_b0").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:49:32:dists") } // x7933 = SRAMNew(ArrayBuffer(Const(64)))
    isAccum(x7933_d1_b0) = false
    bufferDepthOf(x7933_d1_b0) = 2
    staticDimsOf(x7933_d1_b0) = List(64)
    val x7934_d0_b0 = withCtrl(x8640) { SRAM(size=64, banking=Strided(banks=16, stride=1)).name("x7934_d0_b0").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:49:32:dists") } // x7934 = SRAMNew(ArrayBuffer(Const(64)))
    isAccum(x7934_d0_b0) = false
    bufferDepthOf(x7934_d0_b0) = 3
    staticDimsOf(x7934_d0_b0) = List(64)
    val x7934_d1_b0 = withCtrl(x8640) { SRAM(size=64, banking=Strided(banks=16, stride=1)).name("x7934_d1_b0").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:49:32:dists") } // x7934 = SRAMNew(ArrayBuffer(Const(64)))
    isAccum(x7934_d1_b0) = false
    bufferDepthOf(x7934_d1_b0) = 2
    staticDimsOf(x7934_d1_b0) = List(64)
    val x7935_d0_b0 = withCtrl(x8640) { SRAM(size=64, banking=Strided(banks=16, stride=1)).name("x7935_d0_b0").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:49:32:dists") } // x7935 = SRAMNew(ArrayBuffer(Const(64)))
    isAccum(x7935_d0_b0) = false
    bufferDepthOf(x7935_d0_b0) = 3
    staticDimsOf(x7935_d0_b0) = List(64)
    val x7935_d1_b0 = withCtrl(x8640) { SRAM(size=64, banking=Strided(banks=16, stride=1)).name("x7935_d1_b0").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:49:32:dists") } // x7935 = SRAMNew(ArrayBuffer(Const(64)))
    isAccum(x7935_d1_b0) = false
    bufferDepthOf(x7935_d1_b0) = 2
    staticDimsOf(x7935_d1_b0) = List(64)
    val x7936_d0_b0 = withCtrl(x8640) { SRAM(size=64, banking=Strided(banks=16, stride=1)).name("x7936_d0_b0").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:49:32:dists") } // x7936 = SRAMNew(ArrayBuffer(Const(64)))
    isAccum(x7936_d0_b0) = false
    bufferDepthOf(x7936_d0_b0) = 3
    staticDimsOf(x7936_d0_b0) = List(64)
    val x7936_d1_b0 = withCtrl(x8640) { SRAM(size=64, banking=Strided(banks=16, stride=1)).name("x7936_d1_b0").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:49:32:dists") } // x7936 = SRAMNew(ArrayBuffer(Const(64)))
    isAccum(x7936_d1_b0) = false
    bufferDepthOf(x7936_d1_b0) = 2
    staticDimsOf(x7936_d1_b0) = List(64)
    val x7937_d0_b0 = withCtrl(x8640) { SRAM(size=64, banking=Strided(banks=16, stride=1)).name("x7937_d0_b0").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:49:32:dists") } // x7937 = SRAMNew(ArrayBuffer(Const(64)))
    isAccum(x7937_d0_b0) = false
    bufferDepthOf(x7937_d0_b0) = 3
    staticDimsOf(x7937_d0_b0) = List(64)
    val x7937_d1_b0 = withCtrl(x8640) { SRAM(size=64, banking=Strided(banks=16, stride=1)).name("x7937_d1_b0").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:49:32:dists") } // x7937 = SRAMNew(ArrayBuffer(Const(64)))
    isAccum(x7937_d1_b0) = false
    bufferDepthOf(x7937_d1_b0) = 2
    staticDimsOf(x7937_d1_b0) = List(64)
    val x8178 = withCtrl(x8640) { UnitController(style=ForkJoin, level=OuterControl).name("x8178").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b2920, b2911),Block(Const(())))
    val x7938 = withCtrl(x8178) { Counter(min=Const(0), max=Const(64), step=Const(1), par=1).name("x7938").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:50:28") } // CounterNew(Const(0),Const(64),Const(1),Const(1))
    val x7939 = withCtrl(x8178) { CounterChain(List(x7938)).name("x7939").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:50:37") } // CounterChainNew(List(x7938))
    val x7967 = withCtrl(x8178) { LoopController(style=MetaPipe, level=OuterControl, cchain=x7939).name("x7967").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:50:37") } // UnrolledForeach(List(b2971, b2920, b2911),x7939,Block(Const(())),List(List(b3003)),List(List(b3004)))
    val b3003 = withCtrl(x7967) { CounterIter(x7938, Some(0)).name("b3003") } // b3003
    val b3004 = withCtrl(x7967) { Const(true).name("b3004") } // b3004
    val x7940_d0 = withCtrl(x7967) { Reg(init=Some(0)).name("x7940_d0").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:36:dist") } // x7940 = RegNew(Const(0))
    isAccum(x7940_d0) = false
    bufferDepthOf(x7940_d0) = 2
    val x7940_d1 = withCtrl(x7967) { Reg(init=Some(0)).name("x7940_d1").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:36:dist") } // x7940 = RegNew(Const(0))
    isAccum(x7940_d1) = true
    bufferDepthOf(x7940_d1) = 1
    val x7941 = withCtrl(x7967) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x7941").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:43") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x7942 = withCtrl(x7967) { CounterChain(List(x7941)).name("x7942").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:86") } // CounterChainNew(List(x7941))
    val x7960 = withCtrl(x7967) { LoopController(style=InnerPipe, level=InnerControl, cchain=x7942).name("x7960").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:86") } // UnrolledReduce(List(b3004, b2971, b2920, b2911),x7942,x7940,Block((x7940) => Const(())),List(List(b3008)),List(List(b3009)))
    val b3008 = withCtrl(x7960) { CounterIter(x7941, None).name("b3008") } // b3008
    val b3009 = withCtrl(x7960) { Const(true).name("b3009") } // b3009
    val x7943 = withCtrl(x7960) { OpDef(op=BitAnd, inputs=List(b3009, b3004)).name("x7943").srcCtx("UnrollingBase.scala:28:66") } // And(b3009,b3004)
    val x7944 = withCtrl(x7960) { OpDef(op=BitAnd, inputs=List(b2971, b2920)).name("x7944").srcCtx("UnrollingBase.scala:28:66") } // And(b2971,b2920)
    val x7945 = withCtrl(x7960) { OpDef(op=BitAnd, inputs=List(x7943, x7944)).name("x7945").srcCtx("UnrollingBase.scala:28:66") } // And(x7943,x7944)
    val x7946 = withCtrl(x7960) { OpDef(op=BitAnd, inputs=List(x7945, b2911)).name("x7946").srcCtx("UnrollingBase.scala:28:66") } // And(x7945,b2911)
    val x7947 = withCtrl(x7960) { LoadBanks(List(x7895_d1_b0), List(b2963, b3008)).name("x7947").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:60") } // ParSRAMLoad(x7895,List(List(b2963, b3008)),List(x7946))
    val x7948 = withCtrl(x7960) { x7947 } // VectorApply(x7947,0)
    val x7949 = withCtrl(x7960) { LoadBanks(List(x7863_d1_b0), List(b3003, b3008)).name("x7949").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:72") } // ParSRAMLoad(x7863,List(List(b3003, b3008)),List(x7946))
    val x7950 = withCtrl(x7960) { x7949 } // VectorApply(x7949,0)
    val x7951 = withCtrl(x7960) { OpDef(op=FixSub, inputs=List(x7948, x7950)).name("x7951").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:67") } // FixSub(x7948,x7950)
    val x7952 = withCtrl(x7960) { OpDef(op=FixMul, inputs=List(x7951, x7951)).name("x7952").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:80") } // FixMul(x7951,x7951)
    val x7953 = withCtrl(x7960) { ReadMem(x7940_d1).name("x7953").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:86") } // RegRead(x7940)
    val x7954 = withCtrl(x7960) { OpDef(op=FixEql, inputs=List(b3008, Const(0))).name("x7954").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:86") } // FixEql(b3008,Const(0))
    val x7955 = withCtrl(x7960) { ReduceAccumOp(op=FixAdd, input=x7952, accum=x7953).name("x7955").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:88") } // FixAdd(x7952,x7953)
    val x7956 = withCtrl(x7960) { OpDef(op=BitAnd, inputs=List(b3004, b2971)).name("x7956").srcCtx("UnrollingBase.scala:28:66") } // And(b3004,b2971)
    val x7957 = withCtrl(x7960) { OpDef(op=BitAnd, inputs=List(b2920, b2911)).name("x7957").srcCtx("UnrollingBase.scala:28:66") } // And(b2920,b2911)
    val x7958 = withCtrl(x7960) { OpDef(op=BitAnd, inputs=List(x7956, x7957)).name("x7958").srcCtx("UnrollingBase.scala:28:66") } // And(x7956,x7957)
    val x7959_x7940_d0 = withCtrl(x7960) { WriteMem(x7940_d0, x7955).name("x7959_x7940_d0").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:86") } // RegWrite(x7940,x7955,x7958)
    antiDepsOf(x7959_x7940_d0)=List(x7953)
    val x7959_x7940_d1 = withCtrl(x7960) { WriteMem(x7940_d1, x7955).name("x7959_x7940_d1").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:86") } // RegWrite(x7940,x7955,x7958)
    antiDepsOf(x7959_x7940_d1)=List(x7953)
    val x7966 = withCtrl(x7967) { UnitController(style=SeqPipe, level=InnerControl).name("x7966").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:50:37") } // UnitPipe(List(b3004, b2971, b2920, b2911),Block(Const(())))
    val x7961 = withCtrl(x7966) { ReadMem(x7940_d0).name("x7961").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:52:32") } // RegRead(x7940)
    val x7962 = withCtrl(x7966) { OpDef(op=BitAnd, inputs=List(b3004, b2971)).name("x7962").srcCtx("UnrollingBase.scala:28:66") } // And(b3004,b2971)
    val x7963 = withCtrl(x7966) { OpDef(op=BitAnd, inputs=List(b2920, b2911)).name("x7963").srcCtx("UnrollingBase.scala:28:66") } // And(b2920,b2911)
    val x7964 = withCtrl(x7966) { OpDef(op=BitAnd, inputs=List(x7962, x7963)).name("x7964").srcCtx("UnrollingBase.scala:28:66") } // And(x7962,x7963)
    val x7965 = withCtrl(x7966) { StoreBanks(List(List(x7930_d0_b0), List(x7930_d1_b0)), List(b3003), x7961).name("x7965").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:52:25") } // SRAMStore(x7930,ArrayBuffer(Const(64)),List(b3003),Const(0),x7961,x7964)
    val x7968 = withCtrl(x8178) { Counter(min=Const(0), max=Const(64), step=Const(1), par=1).name("x7968").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:50:28") } // CounterNew(Const(0),Const(64),Const(1),Const(1))
    val x7969 = withCtrl(x8178) { CounterChain(List(x7968)).name("x7969").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:50:37") } // CounterChainNew(List(x7968))
    val x7997 = withCtrl(x8178) { LoopController(style=MetaPipe, level=OuterControl, cchain=x7969).name("x7997").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:50:37") } // UnrolledForeach(List(b2972, b2920, b2911),x7969,Block(Const(())),List(List(b3035)),List(List(b3036)))
    val b3035 = withCtrl(x7997) { CounterIter(x7968, Some(0)).name("b3035") } // b3035
    val b3036 = withCtrl(x7997) { Const(true).name("b3036") } // b3036
    val x7970_d0 = withCtrl(x7997) { Reg(init=Some(0)).name("x7970_d0").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:36:dist") } // x7970 = RegNew(Const(0))
    isAccum(x7970_d0) = false
    bufferDepthOf(x7970_d0) = 2
    val x7970_d1 = withCtrl(x7997) { Reg(init=Some(0)).name("x7970_d1").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:36:dist") } // x7970 = RegNew(Const(0))
    isAccum(x7970_d1) = true
    bufferDepthOf(x7970_d1) = 1
    val x7971 = withCtrl(x7997) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x7971").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:43") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x7972 = withCtrl(x7997) { CounterChain(List(x7971)).name("x7972").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:86") } // CounterChainNew(List(x7971))
    val x7990 = withCtrl(x7997) { LoopController(style=InnerPipe, level=InnerControl, cchain=x7972).name("x7990").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:86") } // UnrolledReduce(List(b3036, b2972, b2920, b2911),x7972,x7970,Block((x7970) => Const(())),List(List(b3040)),List(List(b3041)))
    val b3040 = withCtrl(x7990) { CounterIter(x7971, None).name("b3040") } // b3040
    val b3041 = withCtrl(x7990) { Const(true).name("b3041") } // b3041
    val x7973 = withCtrl(x7990) { OpDef(op=BitAnd, inputs=List(b3041, b3036)).name("x7973").srcCtx("UnrollingBase.scala:28:66") } // And(b3041,b3036)
    val x7974 = withCtrl(x7990) { OpDef(op=BitAnd, inputs=List(b2972, b2920)).name("x7974").srcCtx("UnrollingBase.scala:28:66") } // And(b2972,b2920)
    val x7975 = withCtrl(x7990) { OpDef(op=BitAnd, inputs=List(x7973, x7974)).name("x7975").srcCtx("UnrollingBase.scala:28:66") } // And(x7973,x7974)
    val x7976 = withCtrl(x7990) { OpDef(op=BitAnd, inputs=List(x7975, b2911)).name("x7976").srcCtx("UnrollingBase.scala:28:66") } // And(x7975,b2911)
    val x7977 = withCtrl(x7990) { LoadBanks(List(x7895_d1_b1), List(b2964, b3040)).name("x7977").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:60") } // ParSRAMLoad(x7895,List(List(b2964, b3040)),List(x7976))
    val x7978 = withCtrl(x7990) { x7977 } // VectorApply(x7977,0)
    val x7979 = withCtrl(x7990) { LoadBanks(List(x7863_d2_b0), List(b3035, b3040)).name("x7979").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:72") } // ParSRAMLoad(x7863,List(List(b3035, b3040)),List(x7976))
    val x7980 = withCtrl(x7990) { x7979 } // VectorApply(x7979,0)
    val x7981 = withCtrl(x7990) { OpDef(op=FixSub, inputs=List(x7978, x7980)).name("x7981").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:67") } // FixSub(x7978,x7980)
    val x7982 = withCtrl(x7990) { OpDef(op=FixMul, inputs=List(x7981, x7981)).name("x7982").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:80") } // FixMul(x7981,x7981)
    val x7983 = withCtrl(x7990) { ReadMem(x7970_d1).name("x7983").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:86") } // RegRead(x7970)
    val x7984 = withCtrl(x7990) { OpDef(op=FixEql, inputs=List(b3040, Const(0))).name("x7984").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:86") } // FixEql(b3040,Const(0))
    val x7985 = withCtrl(x7990) { ReduceAccumOp(op=FixAdd, input=x7982, accum=x7983).name("x7985").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:88") } // FixAdd(x7982,x7983)
    val x7986 = withCtrl(x7990) { OpDef(op=BitAnd, inputs=List(b3036, b2972)).name("x7986").srcCtx("UnrollingBase.scala:28:66") } // And(b3036,b2972)
    val x7987 = withCtrl(x7990) { OpDef(op=BitAnd, inputs=List(b2920, b2911)).name("x7987").srcCtx("UnrollingBase.scala:28:66") } // And(b2920,b2911)
    val x7988 = withCtrl(x7990) { OpDef(op=BitAnd, inputs=List(x7986, x7987)).name("x7988").srcCtx("UnrollingBase.scala:28:66") } // And(x7986,x7987)
    val x7989_x7970_d0 = withCtrl(x7990) { WriteMem(x7970_d0, x7985).name("x7989_x7970_d0").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:86") } // RegWrite(x7970,x7985,x7988)
    antiDepsOf(x7989_x7970_d0)=List(x7983)
    val x7989_x7970_d1 = withCtrl(x7990) { WriteMem(x7970_d1, x7985).name("x7989_x7970_d1").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:86") } // RegWrite(x7970,x7985,x7988)
    antiDepsOf(x7989_x7970_d1)=List(x7983)
    val x7996 = withCtrl(x7997) { UnitController(style=SeqPipe, level=InnerControl).name("x7996").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:50:37") } // UnitPipe(List(b3036, b2972, b2920, b2911),Block(Const(())))
    val x7991 = withCtrl(x7996) { ReadMem(x7970_d0).name("x7991").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:52:32") } // RegRead(x7970)
    val x7992 = withCtrl(x7996) { OpDef(op=BitAnd, inputs=List(b3036, b2972)).name("x7992").srcCtx("UnrollingBase.scala:28:66") } // And(b3036,b2972)
    val x7993 = withCtrl(x7996) { OpDef(op=BitAnd, inputs=List(b2920, b2911)).name("x7993").srcCtx("UnrollingBase.scala:28:66") } // And(b2920,b2911)
    val x7994 = withCtrl(x7996) { OpDef(op=BitAnd, inputs=List(x7992, x7993)).name("x7994").srcCtx("UnrollingBase.scala:28:66") } // And(x7992,x7993)
    val x7995 = withCtrl(x7996) { StoreBanks(List(List(x7931_d0_b0), List(x7931_d1_b0)), List(b3035), x7991).name("x7995").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:52:25") } // SRAMStore(x7931,ArrayBuffer(Const(64)),List(b3035),Const(0),x7991,x7994)
    val x7998 = withCtrl(x8178) { Counter(min=Const(0), max=Const(64), step=Const(1), par=1).name("x7998").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:50:28") } // CounterNew(Const(0),Const(64),Const(1),Const(1))
    val x7999 = withCtrl(x8178) { CounterChain(List(x7998)).name("x7999").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:50:37") } // CounterChainNew(List(x7998))
    val x8027 = withCtrl(x8178) { LoopController(style=MetaPipe, level=OuterControl, cchain=x7999).name("x8027").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:50:37") } // UnrolledForeach(List(b2973, b2920, b2911),x7999,Block(Const(())),List(List(b3067)),List(List(b3068)))
    val b3067 = withCtrl(x8027) { CounterIter(x7998, Some(0)).name("b3067") } // b3067
    val b3068 = withCtrl(x8027) { Const(true).name("b3068") } // b3068
    val x8000_d0 = withCtrl(x8027) { Reg(init=Some(0)).name("x8000_d0").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:36:dist") } // x8000 = RegNew(Const(0))
    isAccum(x8000_d0) = false
    bufferDepthOf(x8000_d0) = 2
    val x8000_d1 = withCtrl(x8027) { Reg(init=Some(0)).name("x8000_d1").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:36:dist") } // x8000 = RegNew(Const(0))
    isAccum(x8000_d1) = true
    bufferDepthOf(x8000_d1) = 1
    val x8001 = withCtrl(x8027) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x8001").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:43") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x8002 = withCtrl(x8027) { CounterChain(List(x8001)).name("x8002").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:86") } // CounterChainNew(List(x8001))
    val x8020 = withCtrl(x8027) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8002).name("x8020").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:86") } // UnrolledReduce(List(b3068, b2973, b2920, b2911),x8002,x8000,Block((x8000) => Const(())),List(List(b3072)),List(List(b3073)))
    val b3072 = withCtrl(x8020) { CounterIter(x8001, None).name("b3072") } // b3072
    val b3073 = withCtrl(x8020) { Const(true).name("b3073") } // b3073
    val x8003 = withCtrl(x8020) { OpDef(op=BitAnd, inputs=List(b3073, b3068)).name("x8003").srcCtx("UnrollingBase.scala:28:66") } // And(b3073,b3068)
    val x8004 = withCtrl(x8020) { OpDef(op=BitAnd, inputs=List(b2973, b2920)).name("x8004").srcCtx("UnrollingBase.scala:28:66") } // And(b2973,b2920)
    val x8005 = withCtrl(x8020) { OpDef(op=BitAnd, inputs=List(x8003, x8004)).name("x8005").srcCtx("UnrollingBase.scala:28:66") } // And(x8003,x8004)
    val x8006 = withCtrl(x8020) { OpDef(op=BitAnd, inputs=List(x8005, b2911)).name("x8006").srcCtx("UnrollingBase.scala:28:66") } // And(x8005,b2911)
    val x8007 = withCtrl(x8020) { LoadBanks(List(x7895_d1_b2), List(b2965, b3072)).name("x8007").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:60") } // ParSRAMLoad(x7895,List(List(b2965, b3072)),List(x8006))
    val x8008 = withCtrl(x8020) { x8007 } // VectorApply(x8007,0)
    val x8009 = withCtrl(x8020) { LoadBanks(List(x7863_d3_b0), List(b3067, b3072)).name("x8009").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:72") } // ParSRAMLoad(x7863,List(List(b3067, b3072)),List(x8006))
    val x8010 = withCtrl(x8020) { x8009 } // VectorApply(x8009,0)
    val x8011 = withCtrl(x8020) { OpDef(op=FixSub, inputs=List(x8008, x8010)).name("x8011").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:67") } // FixSub(x8008,x8010)
    val x8012 = withCtrl(x8020) { OpDef(op=FixMul, inputs=List(x8011, x8011)).name("x8012").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:80") } // FixMul(x8011,x8011)
    val x8013 = withCtrl(x8020) { ReadMem(x8000_d1).name("x8013").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:86") } // RegRead(x8000)
    val x8014 = withCtrl(x8020) { OpDef(op=FixEql, inputs=List(b3072, Const(0))).name("x8014").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:86") } // FixEql(b3072,Const(0))
    val x8015 = withCtrl(x8020) { ReduceAccumOp(op=FixAdd, input=x8012, accum=x8013).name("x8015").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:88") } // FixAdd(x8012,x8013)
    val x8016 = withCtrl(x8020) { OpDef(op=BitAnd, inputs=List(b3068, b2973)).name("x8016").srcCtx("UnrollingBase.scala:28:66") } // And(b3068,b2973)
    val x8017 = withCtrl(x8020) { OpDef(op=BitAnd, inputs=List(b2920, b2911)).name("x8017").srcCtx("UnrollingBase.scala:28:66") } // And(b2920,b2911)
    val x8018 = withCtrl(x8020) { OpDef(op=BitAnd, inputs=List(x8016, x8017)).name("x8018").srcCtx("UnrollingBase.scala:28:66") } // And(x8016,x8017)
    val x8019_x8000_d0 = withCtrl(x8020) { WriteMem(x8000_d0, x8015).name("x8019_x8000_d0").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:86") } // RegWrite(x8000,x8015,x8018)
    antiDepsOf(x8019_x8000_d0)=List(x8013)
    val x8019_x8000_d1 = withCtrl(x8020) { WriteMem(x8000_d1, x8015).name("x8019_x8000_d1").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:86") } // RegWrite(x8000,x8015,x8018)
    antiDepsOf(x8019_x8000_d1)=List(x8013)
    val x8026 = withCtrl(x8027) { UnitController(style=SeqPipe, level=InnerControl).name("x8026").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:50:37") } // UnitPipe(List(b3068, b2973, b2920, b2911),Block(Const(())))
    val x8021 = withCtrl(x8026) { ReadMem(x8000_d0).name("x8021").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:52:32") } // RegRead(x8000)
    val x8022 = withCtrl(x8026) { OpDef(op=BitAnd, inputs=List(b3068, b2973)).name("x8022").srcCtx("UnrollingBase.scala:28:66") } // And(b3068,b2973)
    val x8023 = withCtrl(x8026) { OpDef(op=BitAnd, inputs=List(b2920, b2911)).name("x8023").srcCtx("UnrollingBase.scala:28:66") } // And(b2920,b2911)
    val x8024 = withCtrl(x8026) { OpDef(op=BitAnd, inputs=List(x8022, x8023)).name("x8024").srcCtx("UnrollingBase.scala:28:66") } // And(x8022,x8023)
    val x8025 = withCtrl(x8026) { StoreBanks(List(List(x7932_d0_b0), List(x7932_d1_b0)), List(b3067), x8021).name("x8025").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:52:25") } // SRAMStore(x7932,ArrayBuffer(Const(64)),List(b3067),Const(0),x8021,x8024)
    val x8028 = withCtrl(x8178) { Counter(min=Const(0), max=Const(64), step=Const(1), par=1).name("x8028").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:50:28") } // CounterNew(Const(0),Const(64),Const(1),Const(1))
    val x8029 = withCtrl(x8178) { CounterChain(List(x8028)).name("x8029").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:50:37") } // CounterChainNew(List(x8028))
    val x8057 = withCtrl(x8178) { LoopController(style=MetaPipe, level=OuterControl, cchain=x8029).name("x8057").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:50:37") } // UnrolledForeach(List(b2974, b2920, b2911),x8029,Block(Const(())),List(List(b3099)),List(List(b3100)))
    val b3099 = withCtrl(x8057) { CounterIter(x8028, Some(0)).name("b3099") } // b3099
    val b3100 = withCtrl(x8057) { Const(true).name("b3100") } // b3100
    val x8030_d0 = withCtrl(x8057) { Reg(init=Some(0)).name("x8030_d0").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:36:dist") } // x8030 = RegNew(Const(0))
    isAccum(x8030_d0) = false
    bufferDepthOf(x8030_d0) = 2
    val x8030_d1 = withCtrl(x8057) { Reg(init=Some(0)).name("x8030_d1").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:36:dist") } // x8030 = RegNew(Const(0))
    isAccum(x8030_d1) = true
    bufferDepthOf(x8030_d1) = 1
    val x8031 = withCtrl(x8057) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x8031").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:43") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x8032 = withCtrl(x8057) { CounterChain(List(x8031)).name("x8032").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:86") } // CounterChainNew(List(x8031))
    val x8050 = withCtrl(x8057) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8032).name("x8050").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:86") } // UnrolledReduce(List(b3100, b2974, b2920, b2911),x8032,x8030,Block((x8030) => Const(())),List(List(b3104)),List(List(b3105)))
    val b3104 = withCtrl(x8050) { CounterIter(x8031, None).name("b3104") } // b3104
    val b3105 = withCtrl(x8050) { Const(true).name("b3105") } // b3105
    val x8033 = withCtrl(x8050) { OpDef(op=BitAnd, inputs=List(b3105, b3100)).name("x8033").srcCtx("UnrollingBase.scala:28:66") } // And(b3105,b3100)
    val x8034 = withCtrl(x8050) { OpDef(op=BitAnd, inputs=List(b2974, b2920)).name("x8034").srcCtx("UnrollingBase.scala:28:66") } // And(b2974,b2920)
    val x8035 = withCtrl(x8050) { OpDef(op=BitAnd, inputs=List(x8033, x8034)).name("x8035").srcCtx("UnrollingBase.scala:28:66") } // And(x8033,x8034)
    val x8036 = withCtrl(x8050) { OpDef(op=BitAnd, inputs=List(x8035, b2911)).name("x8036").srcCtx("UnrollingBase.scala:28:66") } // And(x8035,b2911)
    val x8037 = withCtrl(x8050) { LoadBanks(List(x7895_d1_b3), List(b2966, b3104)).name("x8037").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:60") } // ParSRAMLoad(x7895,List(List(b2966, b3104)),List(x8036))
    val x8038 = withCtrl(x8050) { x8037 } // VectorApply(x8037,0)
    val x8039 = withCtrl(x8050) { LoadBanks(List(x7863_d4_b0), List(b3099, b3104)).name("x8039").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:72") } // ParSRAMLoad(x7863,List(List(b3099, b3104)),List(x8036))
    val x8040 = withCtrl(x8050) { x8039 } // VectorApply(x8039,0)
    val x8041 = withCtrl(x8050) { OpDef(op=FixSub, inputs=List(x8038, x8040)).name("x8041").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:67") } // FixSub(x8038,x8040)
    val x8042 = withCtrl(x8050) { OpDef(op=FixMul, inputs=List(x8041, x8041)).name("x8042").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:80") } // FixMul(x8041,x8041)
    val x8043 = withCtrl(x8050) { ReadMem(x8030_d1).name("x8043").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:86") } // RegRead(x8030)
    val x8044 = withCtrl(x8050) { OpDef(op=FixEql, inputs=List(b3104, Const(0))).name("x8044").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:86") } // FixEql(b3104,Const(0))
    val x8045 = withCtrl(x8050) { ReduceAccumOp(op=FixAdd, input=x8042, accum=x8043).name("x8045").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:88") } // FixAdd(x8042,x8043)
    val x8046 = withCtrl(x8050) { OpDef(op=BitAnd, inputs=List(b3100, b2974)).name("x8046").srcCtx("UnrollingBase.scala:28:66") } // And(b3100,b2974)
    val x8047 = withCtrl(x8050) { OpDef(op=BitAnd, inputs=List(b2920, b2911)).name("x8047").srcCtx("UnrollingBase.scala:28:66") } // And(b2920,b2911)
    val x8048 = withCtrl(x8050) { OpDef(op=BitAnd, inputs=List(x8046, x8047)).name("x8048").srcCtx("UnrollingBase.scala:28:66") } // And(x8046,x8047)
    val x8049_x8030_d0 = withCtrl(x8050) { WriteMem(x8030_d0, x8045).name("x8049_x8030_d0").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:86") } // RegWrite(x8030,x8045,x8048)
    antiDepsOf(x8049_x8030_d0)=List(x8043)
    val x8049_x8030_d1 = withCtrl(x8050) { WriteMem(x8030_d1, x8045).name("x8049_x8030_d1").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:86") } // RegWrite(x8030,x8045,x8048)
    antiDepsOf(x8049_x8030_d1)=List(x8043)
    val x8056 = withCtrl(x8057) { UnitController(style=SeqPipe, level=InnerControl).name("x8056").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:50:37") } // UnitPipe(List(b3100, b2974, b2920, b2911),Block(Const(())))
    val x8051 = withCtrl(x8056) { ReadMem(x8030_d0).name("x8051").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:52:32") } // RegRead(x8030)
    val x8052 = withCtrl(x8056) { OpDef(op=BitAnd, inputs=List(b3100, b2974)).name("x8052").srcCtx("UnrollingBase.scala:28:66") } // And(b3100,b2974)
    val x8053 = withCtrl(x8056) { OpDef(op=BitAnd, inputs=List(b2920, b2911)).name("x8053").srcCtx("UnrollingBase.scala:28:66") } // And(b2920,b2911)
    val x8054 = withCtrl(x8056) { OpDef(op=BitAnd, inputs=List(x8052, x8053)).name("x8054").srcCtx("UnrollingBase.scala:28:66") } // And(x8052,x8053)
    val x8055 = withCtrl(x8056) { StoreBanks(List(List(x7933_d0_b0), List(x7933_d1_b0)), List(b3099), x8051).name("x8055").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:52:25") } // SRAMStore(x7933,ArrayBuffer(Const(64)),List(b3099),Const(0),x8051,x8054)
    val x8058 = withCtrl(x8178) { Counter(min=Const(0), max=Const(64), step=Const(1), par=1).name("x8058").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:50:28") } // CounterNew(Const(0),Const(64),Const(1),Const(1))
    val x8059 = withCtrl(x8178) { CounterChain(List(x8058)).name("x8059").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:50:37") } // CounterChainNew(List(x8058))
    val x8087 = withCtrl(x8178) { LoopController(style=MetaPipe, level=OuterControl, cchain=x8059).name("x8087").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:50:37") } // UnrolledForeach(List(b2975, b2920, b2911),x8059,Block(Const(())),List(List(b3131)),List(List(b3132)))
    val b3131 = withCtrl(x8087) { CounterIter(x8058, Some(0)).name("b3131") } // b3131
    val b3132 = withCtrl(x8087) { Const(true).name("b3132") } // b3132
    val x8060_d0 = withCtrl(x8087) { Reg(init=Some(0)).name("x8060_d0").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:36:dist") } // x8060 = RegNew(Const(0))
    isAccum(x8060_d0) = false
    bufferDepthOf(x8060_d0) = 2
    val x8060_d1 = withCtrl(x8087) { Reg(init=Some(0)).name("x8060_d1").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:36:dist") } // x8060 = RegNew(Const(0))
    isAccum(x8060_d1) = true
    bufferDepthOf(x8060_d1) = 1
    val x8061 = withCtrl(x8087) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x8061").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:43") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x8062 = withCtrl(x8087) { CounterChain(List(x8061)).name("x8062").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:86") } // CounterChainNew(List(x8061))
    val x8080 = withCtrl(x8087) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8062).name("x8080").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:86") } // UnrolledReduce(List(b3132, b2975, b2920, b2911),x8062,x8060,Block((x8060) => Const(())),List(List(b3136)),List(List(b3137)))
    val b3136 = withCtrl(x8080) { CounterIter(x8061, None).name("b3136") } // b3136
    val b3137 = withCtrl(x8080) { Const(true).name("b3137") } // b3137
    val x8063 = withCtrl(x8080) { OpDef(op=BitAnd, inputs=List(b3137, b3132)).name("x8063").srcCtx("UnrollingBase.scala:28:66") } // And(b3137,b3132)
    val x8064 = withCtrl(x8080) { OpDef(op=BitAnd, inputs=List(b2975, b2920)).name("x8064").srcCtx("UnrollingBase.scala:28:66") } // And(b2975,b2920)
    val x8065 = withCtrl(x8080) { OpDef(op=BitAnd, inputs=List(x8063, x8064)).name("x8065").srcCtx("UnrollingBase.scala:28:66") } // And(x8063,x8064)
    val x8066 = withCtrl(x8080) { OpDef(op=BitAnd, inputs=List(x8065, b2911)).name("x8066").srcCtx("UnrollingBase.scala:28:66") } // And(x8065,b2911)
    val x8067 = withCtrl(x8080) { LoadBanks(List(x7895_d1_b4), List(b2967, b3136)).name("x8067").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:60") } // ParSRAMLoad(x7895,List(List(b2967, b3136)),List(x8066))
    val x8068 = withCtrl(x8080) { x8067 } // VectorApply(x8067,0)
    val x8069 = withCtrl(x8080) { LoadBanks(List(x7863_d5_b0), List(b3131, b3136)).name("x8069").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:72") } // ParSRAMLoad(x7863,List(List(b3131, b3136)),List(x8066))
    val x8070 = withCtrl(x8080) { x8069 } // VectorApply(x8069,0)
    val x8071 = withCtrl(x8080) { OpDef(op=FixSub, inputs=List(x8068, x8070)).name("x8071").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:67") } // FixSub(x8068,x8070)
    val x8072 = withCtrl(x8080) { OpDef(op=FixMul, inputs=List(x8071, x8071)).name("x8072").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:80") } // FixMul(x8071,x8071)
    def split1 = {
    val x8073 = withCtrl(x8080) { ReadMem(x8060_d1).name("x8073").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:86") } // RegRead(x8060)
    val x8074 = withCtrl(x8080) { OpDef(op=FixEql, inputs=List(b3136, Const(0))).name("x8074").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:86") } // FixEql(b3136,Const(0))
    val x8075 = withCtrl(x8080) { ReduceAccumOp(op=FixAdd, input=x8072, accum=x8073).name("x8075").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:88") } // FixAdd(x8072,x8073)
    val x8076 = withCtrl(x8080) { OpDef(op=BitAnd, inputs=List(b3132, b2975)).name("x8076").srcCtx("UnrollingBase.scala:28:66") } // And(b3132,b2975)
    val x8077 = withCtrl(x8080) { OpDef(op=BitAnd, inputs=List(b2920, b2911)).name("x8077").srcCtx("UnrollingBase.scala:28:66") } // And(b2920,b2911)
    val x8078 = withCtrl(x8080) { OpDef(op=BitAnd, inputs=List(x8076, x8077)).name("x8078").srcCtx("UnrollingBase.scala:28:66") } // And(x8076,x8077)
    val x8079_x8060_d0 = withCtrl(x8080) { WriteMem(x8060_d0, x8075).name("x8079_x8060_d0").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:86") } // RegWrite(x8060,x8075,x8078)
    antiDepsOf(x8079_x8060_d0)=List(x8073)
    val x8079_x8060_d1 = withCtrl(x8080) { WriteMem(x8060_d1, x8075).name("x8079_x8060_d1").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:86") } // RegWrite(x8060,x8075,x8078)
    antiDepsOf(x8079_x8060_d1)=List(x8073)
    val x8086 = withCtrl(x8087) { UnitController(style=SeqPipe, level=InnerControl).name("x8086").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:50:37") } // UnitPipe(List(b3132, b2975, b2920, b2911),Block(Const(())))
    val x8081 = withCtrl(x8086) { ReadMem(x8060_d0).name("x8081").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:52:32") } // RegRead(x8060)
    val x8082 = withCtrl(x8086) { OpDef(op=BitAnd, inputs=List(b3132, b2975)).name("x8082").srcCtx("UnrollingBase.scala:28:66") } // And(b3132,b2975)
    val x8083 = withCtrl(x8086) { OpDef(op=BitAnd, inputs=List(b2920, b2911)).name("x8083").srcCtx("UnrollingBase.scala:28:66") } // And(b2920,b2911)
    val x8084 = withCtrl(x8086) { OpDef(op=BitAnd, inputs=List(x8082, x8083)).name("x8084").srcCtx("UnrollingBase.scala:28:66") } // And(x8082,x8083)
    val x8085 = withCtrl(x8086) { StoreBanks(List(List(x7934_d0_b0), List(x7934_d1_b0)), List(b3131), x8081).name("x8085").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:52:25") } // SRAMStore(x7934,ArrayBuffer(Const(64)),List(b3131),Const(0),x8081,x8084)
    val x8088 = withCtrl(x8178) { Counter(min=Const(0), max=Const(64), step=Const(1), par=1).name("x8088").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:50:28") } // CounterNew(Const(0),Const(64),Const(1),Const(1))
    val x8089 = withCtrl(x8178) { CounterChain(List(x8088)).name("x8089").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:50:37") } // CounterChainNew(List(x8088))
    val x8117 = withCtrl(x8178) { LoopController(style=MetaPipe, level=OuterControl, cchain=x8089).name("x8117").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:50:37") } // UnrolledForeach(List(b2976, b2920, b2911),x8089,Block(Const(())),List(List(b3163)),List(List(b3164)))
    val b3163 = withCtrl(x8117) { CounterIter(x8088, Some(0)).name("b3163") } // b3163
    val b3164 = withCtrl(x8117) { Const(true).name("b3164") } // b3164
    val x8090_d0 = withCtrl(x8117) { Reg(init=Some(0)).name("x8090_d0").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:36:dist") } // x8090 = RegNew(Const(0))
    isAccum(x8090_d0) = false
    bufferDepthOf(x8090_d0) = 2
    val x8090_d1 = withCtrl(x8117) { Reg(init=Some(0)).name("x8090_d1").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:36:dist") } // x8090 = RegNew(Const(0))
    isAccum(x8090_d1) = true
    bufferDepthOf(x8090_d1) = 1
    val x8091 = withCtrl(x8117) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x8091").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:43") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x8092 = withCtrl(x8117) { CounterChain(List(x8091)).name("x8092").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:86") } // CounterChainNew(List(x8091))
    val x8110 = withCtrl(x8117) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8092).name("x8110").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:86") } // UnrolledReduce(List(b3164, b2976, b2920, b2911),x8092,x8090,Block((x8090) => Const(())),List(List(b3168)),List(List(b3169)))
    val b3168 = withCtrl(x8110) { CounterIter(x8091, None).name("b3168") } // b3168
    val b3169 = withCtrl(x8110) { Const(true).name("b3169") } // b3169
    val x8093 = withCtrl(x8110) { OpDef(op=BitAnd, inputs=List(b3169, b3164)).name("x8093").srcCtx("UnrollingBase.scala:28:66") } // And(b3169,b3164)
    val x8094 = withCtrl(x8110) { OpDef(op=BitAnd, inputs=List(b2976, b2920)).name("x8094").srcCtx("UnrollingBase.scala:28:66") } // And(b2976,b2920)
    val x8095 = withCtrl(x8110) { OpDef(op=BitAnd, inputs=List(x8093, x8094)).name("x8095").srcCtx("UnrollingBase.scala:28:66") } // And(x8093,x8094)
    val x8096 = withCtrl(x8110) { OpDef(op=BitAnd, inputs=List(x8095, b2911)).name("x8096").srcCtx("UnrollingBase.scala:28:66") } // And(x8095,b2911)
    val x8097 = withCtrl(x8110) { LoadBanks(List(x7895_d1_b5), List(b2968, b3168)).name("x8097").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:60") } // ParSRAMLoad(x7895,List(List(b2968, b3168)),List(x8096))
    val x8098 = withCtrl(x8110) { x8097 } // VectorApply(x8097,0)
    val x8099 = withCtrl(x8110) { LoadBanks(List(x7863_d6_b0), List(b3163, b3168)).name("x8099").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:72") } // ParSRAMLoad(x7863,List(List(b3163, b3168)),List(x8096))
    val x8100 = withCtrl(x8110) { x8099 } // VectorApply(x8099,0)
    val x8101 = withCtrl(x8110) { OpDef(op=FixSub, inputs=List(x8098, x8100)).name("x8101").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:67") } // FixSub(x8098,x8100)
    val x8102 = withCtrl(x8110) { OpDef(op=FixMul, inputs=List(x8101, x8101)).name("x8102").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:80") } // FixMul(x8101,x8101)
    val x8103 = withCtrl(x8110) { ReadMem(x8090_d1).name("x8103").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:86") } // RegRead(x8090)
    val x8104 = withCtrl(x8110) { OpDef(op=FixEql, inputs=List(b3168, Const(0))).name("x8104").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:86") } // FixEql(b3168,Const(0))
    val x8105 = withCtrl(x8110) { ReduceAccumOp(op=FixAdd, input=x8102, accum=x8103).name("x8105").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:88") } // FixAdd(x8102,x8103)
    val x8106 = withCtrl(x8110) { OpDef(op=BitAnd, inputs=List(b3164, b2976)).name("x8106").srcCtx("UnrollingBase.scala:28:66") } // And(b3164,b2976)
    val x8107 = withCtrl(x8110) { OpDef(op=BitAnd, inputs=List(b2920, b2911)).name("x8107").srcCtx("UnrollingBase.scala:28:66") } // And(b2920,b2911)
    val x8108 = withCtrl(x8110) { OpDef(op=BitAnd, inputs=List(x8106, x8107)).name("x8108").srcCtx("UnrollingBase.scala:28:66") } // And(x8106,x8107)
    val x8109_x8090_d0 = withCtrl(x8110) { WriteMem(x8090_d0, x8105).name("x8109_x8090_d0").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:86") } // RegWrite(x8090,x8105,x8108)
    antiDepsOf(x8109_x8090_d0)=List(x8103)
    val x8109_x8090_d1 = withCtrl(x8110) { WriteMem(x8090_d1, x8105).name("x8109_x8090_d1").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:86") } // RegWrite(x8090,x8105,x8108)
    antiDepsOf(x8109_x8090_d1)=List(x8103)
    val x8116 = withCtrl(x8117) { UnitController(style=SeqPipe, level=InnerControl).name("x8116").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:50:37") } // UnitPipe(List(b3164, b2976, b2920, b2911),Block(Const(())))
    val x8111 = withCtrl(x8116) { ReadMem(x8090_d0).name("x8111").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:52:32") } // RegRead(x8090)
    val x8112 = withCtrl(x8116) { OpDef(op=BitAnd, inputs=List(b3164, b2976)).name("x8112").srcCtx("UnrollingBase.scala:28:66") } // And(b3164,b2976)
    val x8113 = withCtrl(x8116) { OpDef(op=BitAnd, inputs=List(b2920, b2911)).name("x8113").srcCtx("UnrollingBase.scala:28:66") } // And(b2920,b2911)
    val x8114 = withCtrl(x8116) { OpDef(op=BitAnd, inputs=List(x8112, x8113)).name("x8114").srcCtx("UnrollingBase.scala:28:66") } // And(x8112,x8113)
    val x8115 = withCtrl(x8116) { StoreBanks(List(List(x7935_d0_b0), List(x7935_d1_b0)), List(b3163), x8111).name("x8115").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:52:25") } // SRAMStore(x7935,ArrayBuffer(Const(64)),List(b3163),Const(0),x8111,x8114)
    val x8118 = withCtrl(x8178) { Counter(min=Const(0), max=Const(64), step=Const(1), par=1).name("x8118").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:50:28") } // CounterNew(Const(0),Const(64),Const(1),Const(1))
    val x8119 = withCtrl(x8178) { CounterChain(List(x8118)).name("x8119").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:50:37") } // CounterChainNew(List(x8118))
    val x8147 = withCtrl(x8178) { LoopController(style=MetaPipe, level=OuterControl, cchain=x8119).name("x8147").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:50:37") } // UnrolledForeach(List(b2977, b2920, b2911),x8119,Block(Const(())),List(List(b3195)),List(List(b3196)))
    val b3195 = withCtrl(x8147) { CounterIter(x8118, Some(0)).name("b3195") } // b3195
    val b3196 = withCtrl(x8147) { Const(true).name("b3196") } // b3196
    val x8120_d0 = withCtrl(x8147) { Reg(init=Some(0)).name("x8120_d0").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:36:dist") } // x8120 = RegNew(Const(0))
    isAccum(x8120_d0) = false
    bufferDepthOf(x8120_d0) = 2
    val x8120_d1 = withCtrl(x8147) { Reg(init=Some(0)).name("x8120_d1").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:36:dist") } // x8120 = RegNew(Const(0))
    isAccum(x8120_d1) = true
    bufferDepthOf(x8120_d1) = 1
    val x8121 = withCtrl(x8147) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x8121").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:43") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x8122 = withCtrl(x8147) { CounterChain(List(x8121)).name("x8122").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:86") } // CounterChainNew(List(x8121))
    val x8140 = withCtrl(x8147) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8122).name("x8140").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:86") } // UnrolledReduce(List(b3196, b2977, b2920, b2911),x8122,x8120,Block((x8120) => Const(())),List(List(b3200)),List(List(b3201)))
    val b3200 = withCtrl(x8140) { CounterIter(x8121, None).name("b3200") } // b3200
    val b3201 = withCtrl(x8140) { Const(true).name("b3201") } // b3201
    val x8123 = withCtrl(x8140) { OpDef(op=BitAnd, inputs=List(b3201, b3196)).name("x8123").srcCtx("UnrollingBase.scala:28:66") } // And(b3201,b3196)
    val x8124 = withCtrl(x8140) { OpDef(op=BitAnd, inputs=List(b2977, b2920)).name("x8124").srcCtx("UnrollingBase.scala:28:66") } // And(b2977,b2920)
    val x8125 = withCtrl(x8140) { OpDef(op=BitAnd, inputs=List(x8123, x8124)).name("x8125").srcCtx("UnrollingBase.scala:28:66") } // And(x8123,x8124)
    val x8126 = withCtrl(x8140) { OpDef(op=BitAnd, inputs=List(x8125, b2911)).name("x8126").srcCtx("UnrollingBase.scala:28:66") } // And(x8125,b2911)
    val x8127 = withCtrl(x8140) { LoadBanks(List(x7895_d1_b6), List(b2969, b3200)).name("x8127").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:60") } // ParSRAMLoad(x7895,List(List(b2969, b3200)),List(x8126))
    val x8128 = withCtrl(x8140) { x8127 } // VectorApply(x8127,0)
    val x8129 = withCtrl(x8140) { LoadBanks(List(x7863_d7_b0), List(b3195, b3200)).name("x8129").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:72") } // ParSRAMLoad(x7863,List(List(b3195, b3200)),List(x8126))
    val x8130 = withCtrl(x8140) { x8129 } // VectorApply(x8129,0)
    val x8131 = withCtrl(x8140) { OpDef(op=FixSub, inputs=List(x8128, x8130)).name("x8131").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:67") } // FixSub(x8128,x8130)
    val x8132 = withCtrl(x8140) { OpDef(op=FixMul, inputs=List(x8131, x8131)).name("x8132").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:80") } // FixMul(x8131,x8131)
    val x8133 = withCtrl(x8140) { ReadMem(x8120_d1).name("x8133").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:86") } // RegRead(x8120)
    val x8134 = withCtrl(x8140) { OpDef(op=FixEql, inputs=List(b3200, Const(0))).name("x8134").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:86") } // FixEql(b3200,Const(0))
    val x8135 = withCtrl(x8140) { ReduceAccumOp(op=FixAdd, input=x8132, accum=x8133).name("x8135").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:88") } // FixAdd(x8132,x8133)
    val x8136 = withCtrl(x8140) { OpDef(op=BitAnd, inputs=List(b3196, b2977)).name("x8136").srcCtx("UnrollingBase.scala:28:66") } // And(b3196,b2977)
    val x8137 = withCtrl(x8140) { OpDef(op=BitAnd, inputs=List(b2920, b2911)).name("x8137").srcCtx("UnrollingBase.scala:28:66") } // And(b2920,b2911)
    val x8138 = withCtrl(x8140) { OpDef(op=BitAnd, inputs=List(x8136, x8137)).name("x8138").srcCtx("UnrollingBase.scala:28:66") } // And(x8136,x8137)
    val x8139_x8120_d0 = withCtrl(x8140) { WriteMem(x8120_d0, x8135).name("x8139_x8120_d0").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:86") } // RegWrite(x8120,x8135,x8138)
    antiDepsOf(x8139_x8120_d0)=List(x8133)
    val x8139_x8120_d1 = withCtrl(x8140) { WriteMem(x8120_d1, x8135).name("x8139_x8120_d1").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:86") } // RegWrite(x8120,x8135,x8138)
    antiDepsOf(x8139_x8120_d1)=List(x8133)
    val x8146 = withCtrl(x8147) { UnitController(style=SeqPipe, level=InnerControl).name("x8146").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:50:37") } // UnitPipe(List(b3196, b2977, b2920, b2911),Block(Const(())))
    val x8141 = withCtrl(x8146) { ReadMem(x8120_d0).name("x8141").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:52:32") } // RegRead(x8120)
    val x8142 = withCtrl(x8146) { OpDef(op=BitAnd, inputs=List(b3196, b2977)).name("x8142").srcCtx("UnrollingBase.scala:28:66") } // And(b3196,b2977)
    val x8143 = withCtrl(x8146) { OpDef(op=BitAnd, inputs=List(b2920, b2911)).name("x8143").srcCtx("UnrollingBase.scala:28:66") } // And(b2920,b2911)
    val x8144 = withCtrl(x8146) { OpDef(op=BitAnd, inputs=List(x8142, x8143)).name("x8144").srcCtx("UnrollingBase.scala:28:66") } // And(x8142,x8143)
    val x8145 = withCtrl(x8146) { StoreBanks(List(List(x7936_d0_b0), List(x7936_d1_b0)), List(b3195), x8141).name("x8145").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:52:25") } // SRAMStore(x7936,ArrayBuffer(Const(64)),List(b3195),Const(0),x8141,x8144)
    val x8148 = withCtrl(x8178) { Counter(min=Const(0), max=Const(64), step=Const(1), par=1).name("x8148").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:50:28") } // CounterNew(Const(0),Const(64),Const(1),Const(1))
    val x8149 = withCtrl(x8178) { CounterChain(List(x8148)).name("x8149").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:50:37") } // CounterChainNew(List(x8148))
    val x8177 = withCtrl(x8178) { LoopController(style=MetaPipe, level=OuterControl, cchain=x8149).name("x8177").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:50:37") } // UnrolledForeach(List(b2978, b2920, b2911),x8149,Block(Const(())),List(List(b3227)),List(List(b3228)))
    val b3227 = withCtrl(x8177) { CounterIter(x8148, Some(0)).name("b3227") } // b3227
    val b3228 = withCtrl(x8177) { Const(true).name("b3228") } // b3228
    val x8150_d0 = withCtrl(x8177) { Reg(init=Some(0)).name("x8150_d0").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:36:dist") } // x8150 = RegNew(Const(0))
    isAccum(x8150_d0) = false
    bufferDepthOf(x8150_d0) = 2
    val x8150_d1 = withCtrl(x8177) { Reg(init=Some(0)).name("x8150_d1").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:36:dist") } // x8150 = RegNew(Const(0))
    isAccum(x8150_d1) = true
    bufferDepthOf(x8150_d1) = 1
    val x8151 = withCtrl(x8177) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x8151").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:43") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x8152 = withCtrl(x8177) { CounterChain(List(x8151)).name("x8152").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:86") } // CounterChainNew(List(x8151))
    val x8170 = withCtrl(x8177) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8152).name("x8170").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:86") } // UnrolledReduce(List(b3228, b2978, b2920, b2911),x8152,x8150,Block((x8150) => Const(())),List(List(b3232)),List(List(b3233)))
    val b3232 = withCtrl(x8170) { CounterIter(x8151, None).name("b3232") } // b3232
    val b3233 = withCtrl(x8170) { Const(true).name("b3233") } // b3233
    val x8153 = withCtrl(x8170) { OpDef(op=BitAnd, inputs=List(b3233, b3228)).name("x8153").srcCtx("UnrollingBase.scala:28:66") } // And(b3233,b3228)
    val x8154 = withCtrl(x8170) { OpDef(op=BitAnd, inputs=List(b2978, b2920)).name("x8154").srcCtx("UnrollingBase.scala:28:66") } // And(b2978,b2920)
    val x8155 = withCtrl(x8170) { OpDef(op=BitAnd, inputs=List(x8153, x8154)).name("x8155").srcCtx("UnrollingBase.scala:28:66") } // And(x8153,x8154)
    val x8156 = withCtrl(x8170) { OpDef(op=BitAnd, inputs=List(x8155, b2911)).name("x8156").srcCtx("UnrollingBase.scala:28:66") } // And(x8155,b2911)
    val x8157 = withCtrl(x8170) { LoadBanks(List(x7895_d1_b7), List(b2970, b3232)).name("x8157").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:60") } // ParSRAMLoad(x7895,List(List(b2970, b3232)),List(x8156))
    val x8158 = withCtrl(x8170) { x8157 } // VectorApply(x8157,0)
    val x8159 = withCtrl(x8170) { LoadBanks(List(x7863_d8_b0), List(b3227, b3232)).name("x8159").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:72") } // ParSRAMLoad(x7863,List(List(b3227, b3232)),List(x8156))
    val x8160 = withCtrl(x8170) { x8159 } // VectorApply(x8159,0)
    val x8161 = withCtrl(x8170) { OpDef(op=FixSub, inputs=List(x8158, x8160)).name("x8161").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:67") } // FixSub(x8158,x8160)
    val x8162 = withCtrl(x8170) { OpDef(op=FixMul, inputs=List(x8161, x8161)).name("x8162").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:80") } // FixMul(x8161,x8161)
    val x8163 = withCtrl(x8170) { ReadMem(x8150_d1).name("x8163").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:86") } // RegRead(x8150)
    val x8164 = withCtrl(x8170) { OpDef(op=FixEql, inputs=List(b3232, Const(0))).name("x8164").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:86") } // FixEql(b3232,Const(0))
    val x8165 = withCtrl(x8170) { ReduceAccumOp(op=FixAdd, input=x8162, accum=x8163).name("x8165").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:88") } // FixAdd(x8162,x8163)
    val x8166 = withCtrl(x8170) { OpDef(op=BitAnd, inputs=List(b3228, b2978)).name("x8166").srcCtx("UnrollingBase.scala:28:66") } // And(b3228,b2978)
    val x8167 = withCtrl(x8170) { OpDef(op=BitAnd, inputs=List(b2920, b2911)).name("x8167").srcCtx("UnrollingBase.scala:28:66") } // And(b2920,b2911)
    val x8168 = withCtrl(x8170) { OpDef(op=BitAnd, inputs=List(x8166, x8167)).name("x8168").srcCtx("UnrollingBase.scala:28:66") } // And(x8166,x8167)
    val x8169_x8150_d0 = withCtrl(x8170) { WriteMem(x8150_d0, x8165).name("x8169_x8150_d0").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:86") } // RegWrite(x8150,x8165,x8168)
    antiDepsOf(x8169_x8150_d0)=List(x8163)
    val x8169_x8150_d1 = withCtrl(x8170) { WriteMem(x8150_d1, x8165).name("x8169_x8150_d1").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:51:86") } // RegWrite(x8150,x8165,x8168)
    antiDepsOf(x8169_x8150_d1)=List(x8163)
    val x8176 = withCtrl(x8177) { UnitController(style=SeqPipe, level=InnerControl).name("x8176").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:50:37") } // UnitPipe(List(b3228, b2978, b2920, b2911),Block(Const(())))
    val x8171 = withCtrl(x8176) { ReadMem(x8150_d0).name("x8171").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:52:32") } // RegRead(x8150)
    val x8172 = withCtrl(x8176) { OpDef(op=BitAnd, inputs=List(b3228, b2978)).name("x8172").srcCtx("UnrollingBase.scala:28:66") } // And(b3228,b2978)
    val x8173 = withCtrl(x8176) { OpDef(op=BitAnd, inputs=List(b2920, b2911)).name("x8173").srcCtx("UnrollingBase.scala:28:66") } // And(b2920,b2911)
    val x8174 = withCtrl(x8176) { OpDef(op=BitAnd, inputs=List(x8172, x8173)).name("x8174").srcCtx("UnrollingBase.scala:28:66") } // And(x8172,x8173)
    val x8175 = withCtrl(x8176) { StoreBanks(List(List(x7937_d0_b0), List(x7937_d1_b0)), List(b3227), x8171).name("x8175").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:52:25") } // SRAMStore(x7937,ArrayBuffer(Const(64)),List(b3227),Const(0),x8171,x8174)
    val x8179_d0 = withCtrl(x8640) { Reg(init=Some(0)).name("x8179_d0").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:54:37:minDist") } // x8179 = RegNew(Const(0))
    isAccum(x8179_d0) = false
    bufferDepthOf(x8179_d0) = 2
    val x8179_d1 = withCtrl(x8640) { Reg(init=Some(0)).name("x8179_d1").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:54:37:minDist") } // x8179 = RegNew(Const(0))
    isAccum(x8179_d1) = true
    bufferDepthOf(x8179_d1) = 1
    val x8180_d0 = withCtrl(x8640) { Reg(init=Some(0)).name("x8180_d0").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:54:37:minDist") } // x8180 = RegNew(Const(0))
    isAccum(x8180_d0) = false
    bufferDepthOf(x8180_d0) = 2
    val x8180_d1 = withCtrl(x8640) { Reg(init=Some(0)).name("x8180_d1").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:54:37:minDist") } // x8180 = RegNew(Const(0))
    isAccum(x8180_d1) = true
    bufferDepthOf(x8180_d1) = 1
    val x8181_d0 = withCtrl(x8640) { Reg(init=Some(0)).name("x8181_d0").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:54:37:minDist") } // x8181 = RegNew(Const(0))
    isAccum(x8181_d0) = false
    bufferDepthOf(x8181_d0) = 2
    val x8181_d1 = withCtrl(x8640) { Reg(init=Some(0)).name("x8181_d1").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:54:37:minDist") } // x8181 = RegNew(Const(0))
    isAccum(x8181_d1) = true
    bufferDepthOf(x8181_d1) = 1
    val x8182_d0 = withCtrl(x8640) { Reg(init=Some(0)).name("x8182_d0").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:54:37:minDist") } // x8182 = RegNew(Const(0))
    isAccum(x8182_d0) = false
    bufferDepthOf(x8182_d0) = 2
    val x8182_d1 = withCtrl(x8640) { Reg(init=Some(0)).name("x8182_d1").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:54:37:minDist") } // x8182 = RegNew(Const(0))
    isAccum(x8182_d1) = true
    bufferDepthOf(x8182_d1) = 1
    val x8183_d0 = withCtrl(x8640) { Reg(init=Some(0)).name("x8183_d0").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:54:37:minDist") } // x8183 = RegNew(Const(0))
    isAccum(x8183_d0) = false
    bufferDepthOf(x8183_d0) = 2
    val x8183_d1 = withCtrl(x8640) { Reg(init=Some(0)).name("x8183_d1").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:54:37:minDist") } // x8183 = RegNew(Const(0))
    isAccum(x8183_d1) = true
    bufferDepthOf(x8183_d1) = 1
    val x8184_d0 = withCtrl(x8640) { Reg(init=Some(0)).name("x8184_d0").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:54:37:minDist") } // x8184 = RegNew(Const(0))
    isAccum(x8184_d0) = false
    bufferDepthOf(x8184_d0) = 2
    val x8184_d1 = withCtrl(x8640) { Reg(init=Some(0)).name("x8184_d1").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:54:37:minDist") } // x8184 = RegNew(Const(0))
    isAccum(x8184_d1) = true
    bufferDepthOf(x8184_d1) = 1
    val x8185_d0 = withCtrl(x8640) { Reg(init=Some(0)).name("x8185_d0").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:54:37:minDist") } // x8185 = RegNew(Const(0))
    isAccum(x8185_d0) = false
    bufferDepthOf(x8185_d0) = 2
    val x8185_d1 = withCtrl(x8640) { Reg(init=Some(0)).name("x8185_d1").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:54:37:minDist") } // x8185 = RegNew(Const(0))
    isAccum(x8185_d1) = true
    bufferDepthOf(x8185_d1) = 1
    val x8186_d0 = withCtrl(x8640) { Reg(init=Some(0)).name("x8186_d0").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:54:37:minDist") } // x8186 = RegNew(Const(0))
    isAccum(x8186_d0) = false
    bufferDepthOf(x8186_d0) = 2
    val x8186_d1 = withCtrl(x8640) { Reg(init=Some(0)).name("x8186_d1").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:54:37:minDist") } // x8186 = RegNew(Const(0))
    isAccum(x8186_d1) = true
    bufferDepthOf(x8186_d1) = 1
    val x8299 = withCtrl(x8640) { UnitController(style=ForkJoin, level=OuterControl).name("x8299").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b2920, b2911),Block(Const(())))
    val x8187 = withCtrl(x8299) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x8187").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:54:49") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x8188 = withCtrl(x8299) { CounterChain(List(x8187)).name("x8188").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:54:77") } // CounterChainNew(List(x8187))
    val x8200 = withCtrl(x8299) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8188).name("x8200").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:54:77") } // UnrolledReduce(List(b2971, b2920, b2911),x8188,x8179,Block((x8179) => Const(())),List(List(b3284)),List(List(b3285)))
    val b3284 = withCtrl(x8200) { CounterIter(x8187, None).name("b3284") } // b3284
    val b3285 = withCtrl(x8200) { Const(true).name("b3285") } // b3285
    val x8189 = withCtrl(x8200) { OpDef(op=BitAnd, inputs=List(b3285, b2971)).name("x8189").srcCtx("UnrollingBase.scala:28:66") } // And(b3285,b2971)
    val x8190 = withCtrl(x8200) { OpDef(op=BitAnd, inputs=List(b2920, b2911)).name("x8190").srcCtx("UnrollingBase.scala:28:66") } // And(b2920,b2911)
    val x8191 = withCtrl(x8200) { OpDef(op=BitAnd, inputs=List(x8189, x8190)).name("x8191").srcCtx("UnrollingBase.scala:28:66") } // And(x8189,x8190)
    val x8192 = withCtrl(x8200) { LoadBanks(List(x7930_d1_b0), List(b3284)).name("x8192").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:54:70") } // ParSRAMLoad(x7930,List(List(b3284)),List(x8191))
    val x8193 = withCtrl(x8200) { x8192 } // VectorApply(x8192,0)
    val x8194 = withCtrl(x8200) { ReadMem(x8179_d1).name("x8194").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:54:77") } // RegRead(x8179)
    val x8195 = withCtrl(x8200) { OpDef(op=FixEql, inputs=List(b3284, Const(0))).name("x8195").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:54:77") } // FixEql(b3284,Const(0))
    val x8196 = withCtrl(x8200) { ReduceAccumOp(op=FixMin, input=x8193, accum=x8194).name("x8196").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:54:91") } // Min(x8193,x8194)
    val x8197 = withCtrl(x8200) { OpDef(op=BitAnd, inputs=List(b2971, b2920)).name("x8197").srcCtx("UnrollingBase.scala:28:66") } // And(b2971,b2920)
    val x8198 = withCtrl(x8200) { OpDef(op=BitAnd, inputs=List(x8197, b2911)).name("x8198").srcCtx("UnrollingBase.scala:28:66") } // And(x8197,b2911)
    val x8199_x8179_d0 = withCtrl(x8200) { WriteMem(x8179_d0, x8196).name("x8199_x8179_d0").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:54:77") } // RegWrite(x8179,x8196,x8198)
    antiDepsOf(x8199_x8179_d0)=List(x8194)
    val x8199_x8179_d1 = withCtrl(x8200) { WriteMem(x8179_d1, x8196).name("x8199_x8179_d1").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:54:77") } // RegWrite(x8179,x8196,x8198)
    antiDepsOf(x8199_x8179_d1)=List(x8194)
    val x8201 = withCtrl(x8299) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x8201").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:54:49") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x8202 = withCtrl(x8299) { CounterChain(List(x8201)).name("x8202").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:54:77") } // CounterChainNew(List(x8201))
    val x8214 = withCtrl(x8299) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8202).name("x8214").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:54:77") } // UnrolledReduce(List(b2972, b2920, b2911),x8202,x8180,Block((x8180) => Const(())),List(List(b3298)),List(List(b3299)))
    val b3298 = withCtrl(x8214) { CounterIter(x8201, None).name("b3298") } // b3298
    val b3299 = withCtrl(x8214) { Const(true).name("b3299") } // b3299
    val x8203 = withCtrl(x8214) { OpDef(op=BitAnd, inputs=List(b3299, b2972)).name("x8203").srcCtx("UnrollingBase.scala:28:66") } // And(b3299,b2972)
    val x8204 = withCtrl(x8214) { OpDef(op=BitAnd, inputs=List(b2920, b2911)).name("x8204").srcCtx("UnrollingBase.scala:28:66") } // And(b2920,b2911)
    val x8205 = withCtrl(x8214) { OpDef(op=BitAnd, inputs=List(x8203, x8204)).name("x8205").srcCtx("UnrollingBase.scala:28:66") } // And(x8203,x8204)
    val x8206 = withCtrl(x8214) { LoadBanks(List(x7931_d1_b0), List(b3298)).name("x8206").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:54:70") } // ParSRAMLoad(x7931,List(List(b3298)),List(x8205))
    val x8207 = withCtrl(x8214) { x8206 } // VectorApply(x8206,0)
    val x8208 = withCtrl(x8214) { ReadMem(x8180_d1).name("x8208").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:54:77") } // RegRead(x8180)
    val x8209 = withCtrl(x8214) { OpDef(op=FixEql, inputs=List(b3298, Const(0))).name("x8209").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:54:77") } // FixEql(b3298,Const(0))
    val x8210 = withCtrl(x8214) { ReduceAccumOp(op=FixMin, input=x8207, accum=x8208).name("x8210").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:54:91") } // Min(x8207,x8208)
    val x8211 = withCtrl(x8214) { OpDef(op=BitAnd, inputs=List(b2972, b2920)).name("x8211").srcCtx("UnrollingBase.scala:28:66") } // And(b2972,b2920)
    val x8212 = withCtrl(x8214) { OpDef(op=BitAnd, inputs=List(x8211, b2911)).name("x8212").srcCtx("UnrollingBase.scala:28:66") } // And(x8211,b2911)
    val x8213_x8180_d0 = withCtrl(x8214) { WriteMem(x8180_d0, x8210).name("x8213_x8180_d0").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:54:77") } // RegWrite(x8180,x8210,x8212)
    antiDepsOf(x8213_x8180_d0)=List(x8208)
    val x8213_x8180_d1 = withCtrl(x8214) { WriteMem(x8180_d1, x8210).name("x8213_x8180_d1").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:54:77") } // RegWrite(x8180,x8210,x8212)
    antiDepsOf(x8213_x8180_d1)=List(x8208)
    val x8215 = withCtrl(x8299) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x8215").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:54:49") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x8216 = withCtrl(x8299) { CounterChain(List(x8215)).name("x8216").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:54:77") } // CounterChainNew(List(x8215))
    val x8228 = withCtrl(x8299) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8216).name("x8228").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:54:77") } // UnrolledReduce(List(b2973, b2920, b2911),x8216,x8181,Block((x8181) => Const(())),List(List(b3312)),List(List(b3313)))
    val b3312 = withCtrl(x8228) { CounterIter(x8215, None).name("b3312") } // b3312
    val b3313 = withCtrl(x8228) { Const(true).name("b3313") } // b3313
    val x8217 = withCtrl(x8228) { OpDef(op=BitAnd, inputs=List(b3313, b2973)).name("x8217").srcCtx("UnrollingBase.scala:28:66") } // And(b3313,b2973)
    val x8218 = withCtrl(x8228) { OpDef(op=BitAnd, inputs=List(b2920, b2911)).name("x8218").srcCtx("UnrollingBase.scala:28:66") } // And(b2920,b2911)
    val x8219 = withCtrl(x8228) { OpDef(op=BitAnd, inputs=List(x8217, x8218)).name("x8219").srcCtx("UnrollingBase.scala:28:66") } // And(x8217,x8218)
    val x8220 = withCtrl(x8228) { LoadBanks(List(x7932_d1_b0), List(b3312)).name("x8220").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:54:70") } // ParSRAMLoad(x7932,List(List(b3312)),List(x8219))
    val x8221 = withCtrl(x8228) { x8220 } // VectorApply(x8220,0)
    val x8222 = withCtrl(x8228) { ReadMem(x8181_d1).name("x8222").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:54:77") } // RegRead(x8181)
    val x8223 = withCtrl(x8228) { OpDef(op=FixEql, inputs=List(b3312, Const(0))).name("x8223").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:54:77") } // FixEql(b3312,Const(0))
    val x8224 = withCtrl(x8228) { ReduceAccumOp(op=FixMin, input=x8221, accum=x8222).name("x8224").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:54:91") } // Min(x8221,x8222)
    val x8225 = withCtrl(x8228) { OpDef(op=BitAnd, inputs=List(b2973, b2920)).name("x8225").srcCtx("UnrollingBase.scala:28:66") } // And(b2973,b2920)
    val x8226 = withCtrl(x8228) { OpDef(op=BitAnd, inputs=List(x8225, b2911)).name("x8226").srcCtx("UnrollingBase.scala:28:66") } // And(x8225,b2911)
    val x8227_x8181_d0 = withCtrl(x8228) { WriteMem(x8181_d0, x8224).name("x8227_x8181_d0").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:54:77") } // RegWrite(x8181,x8224,x8226)
    antiDepsOf(x8227_x8181_d0)=List(x8222)
    val x8227_x8181_d1 = withCtrl(x8228) { WriteMem(x8181_d1, x8224).name("x8227_x8181_d1").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:54:77") } // RegWrite(x8181,x8224,x8226)
    antiDepsOf(x8227_x8181_d1)=List(x8222)
    val x8229 = withCtrl(x8299) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x8229").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:54:49") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x8230 = withCtrl(x8299) { CounterChain(List(x8229)).name("x8230").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:54:77") } // CounterChainNew(List(x8229))
    val x8242 = withCtrl(x8299) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8230).name("x8242").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:54:77") } // UnrolledReduce(List(b2974, b2920, b2911),x8230,x8182,Block((x8182) => Const(())),List(List(b3326)),List(List(b3327)))
    val b3326 = withCtrl(x8242) { CounterIter(x8229, None).name("b3326") } // b3326
    val b3327 = withCtrl(x8242) { Const(true).name("b3327") } // b3327
    val x8231 = withCtrl(x8242) { OpDef(op=BitAnd, inputs=List(b3327, b2974)).name("x8231").srcCtx("UnrollingBase.scala:28:66") } // And(b3327,b2974)
    val x8232 = withCtrl(x8242) { OpDef(op=BitAnd, inputs=List(b2920, b2911)).name("x8232").srcCtx("UnrollingBase.scala:28:66") } // And(b2920,b2911)
    val x8233 = withCtrl(x8242) { OpDef(op=BitAnd, inputs=List(x8231, x8232)).name("x8233").srcCtx("UnrollingBase.scala:28:66") } // And(x8231,x8232)
    val x8234 = withCtrl(x8242) { LoadBanks(List(x7933_d1_b0), List(b3326)).name("x8234").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:54:70") } // ParSRAMLoad(x7933,List(List(b3326)),List(x8233))
    val x8235 = withCtrl(x8242) { x8234 } // VectorApply(x8234,0)
    val x8236 = withCtrl(x8242) { ReadMem(x8182_d1).name("x8236").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:54:77") } // RegRead(x8182)
    val x8237 = withCtrl(x8242) { OpDef(op=FixEql, inputs=List(b3326, Const(0))).name("x8237").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:54:77") } // FixEql(b3326,Const(0))
    val x8238 = withCtrl(x8242) { ReduceAccumOp(op=FixMin, input=x8235, accum=x8236).name("x8238").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:54:91") } // Min(x8235,x8236)
    val x8239 = withCtrl(x8242) { OpDef(op=BitAnd, inputs=List(b2974, b2920)).name("x8239").srcCtx("UnrollingBase.scala:28:66") } // And(b2974,b2920)
    val x8240 = withCtrl(x8242) { OpDef(op=BitAnd, inputs=List(x8239, b2911)).name("x8240").srcCtx("UnrollingBase.scala:28:66") } // And(x8239,b2911)
    val x8241_x8182_d0 = withCtrl(x8242) { WriteMem(x8182_d0, x8238).name("x8241_x8182_d0").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:54:77") } // RegWrite(x8182,x8238,x8240)
    antiDepsOf(x8241_x8182_d0)=List(x8236)
    val x8241_x8182_d1 = withCtrl(x8242) { WriteMem(x8182_d1, x8238).name("x8241_x8182_d1").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:54:77") } // RegWrite(x8182,x8238,x8240)
    antiDepsOf(x8241_x8182_d1)=List(x8236)
    val x8243 = withCtrl(x8299) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x8243").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:54:49") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x8244 = withCtrl(x8299) { CounterChain(List(x8243)).name("x8244").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:54:77") } // CounterChainNew(List(x8243))
    val x8256 = withCtrl(x8299) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8244).name("x8256").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:54:77") } // UnrolledReduce(List(b2975, b2920, b2911),x8244,x8183,Block((x8183) => Const(())),List(List(b3340)),List(List(b3341)))
    val b3340 = withCtrl(x8256) { CounterIter(x8243, None).name("b3340") } // b3340
    val b3341 = withCtrl(x8256) { Const(true).name("b3341") } // b3341
    val x8245 = withCtrl(x8256) { OpDef(op=BitAnd, inputs=List(b3341, b2975)).name("x8245").srcCtx("UnrollingBase.scala:28:66") } // And(b3341,b2975)
    val x8246 = withCtrl(x8256) { OpDef(op=BitAnd, inputs=List(b2920, b2911)).name("x8246").srcCtx("UnrollingBase.scala:28:66") } // And(b2920,b2911)
    val x8247 = withCtrl(x8256) { OpDef(op=BitAnd, inputs=List(x8245, x8246)).name("x8247").srcCtx("UnrollingBase.scala:28:66") } // And(x8245,x8246)
    val x8248 = withCtrl(x8256) { LoadBanks(List(x7934_d1_b0), List(b3340)).name("x8248").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:54:70") } // ParSRAMLoad(x7934,List(List(b3340)),List(x8247))
    val x8249 = withCtrl(x8256) { x8248 } // VectorApply(x8248,0)
    val x8250 = withCtrl(x8256) { ReadMem(x8183_d1).name("x8250").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:54:77") } // RegRead(x8183)
    val x8251 = withCtrl(x8256) { OpDef(op=FixEql, inputs=List(b3340, Const(0))).name("x8251").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:54:77") } // FixEql(b3340,Const(0))
    val x8252 = withCtrl(x8256) { ReduceAccumOp(op=FixMin, input=x8249, accum=x8250).name("x8252").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:54:91") } // Min(x8249,x8250)
    val x8253 = withCtrl(x8256) { OpDef(op=BitAnd, inputs=List(b2975, b2920)).name("x8253").srcCtx("UnrollingBase.scala:28:66") } // And(b2975,b2920)
    val x8254 = withCtrl(x8256) { OpDef(op=BitAnd, inputs=List(x8253, b2911)).name("x8254").srcCtx("UnrollingBase.scala:28:66") } // And(x8253,b2911)
    val x8255_x8183_d0 = withCtrl(x8256) { WriteMem(x8183_d0, x8252).name("x8255_x8183_d0").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:54:77") } // RegWrite(x8183,x8252,x8254)
    antiDepsOf(x8255_x8183_d0)=List(x8250)
    val x8255_x8183_d1 = withCtrl(x8256) { WriteMem(x8183_d1, x8252).name("x8255_x8183_d1").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:54:77") } // RegWrite(x8183,x8252,x8254)
    antiDepsOf(x8255_x8183_d1)=List(x8250)
    val x8257 = withCtrl(x8299) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x8257").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:54:49") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x8258 = withCtrl(x8299) { CounterChain(List(x8257)).name("x8258").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:54:77") } // CounterChainNew(List(x8257))
    val x8270 = withCtrl(x8299) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8258).name("x8270").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:54:77") } // UnrolledReduce(List(b2976, b2920, b2911),x8258,x8184,Block((x8184) => Const(())),List(List(b3354)),List(List(b3355)))
    val b3354 = withCtrl(x8270) { CounterIter(x8257, None).name("b3354") } // b3354
    val b3355 = withCtrl(x8270) { Const(true).name("b3355") } // b3355
    val x8259 = withCtrl(x8270) { OpDef(op=BitAnd, inputs=List(b3355, b2976)).name("x8259").srcCtx("UnrollingBase.scala:28:66") } // And(b3355,b2976)
    val x8260 = withCtrl(x8270) { OpDef(op=BitAnd, inputs=List(b2920, b2911)).name("x8260").srcCtx("UnrollingBase.scala:28:66") } // And(b2920,b2911)
    val x8261 = withCtrl(x8270) { OpDef(op=BitAnd, inputs=List(x8259, x8260)).name("x8261").srcCtx("UnrollingBase.scala:28:66") } // And(x8259,x8260)
    val x8262 = withCtrl(x8270) { LoadBanks(List(x7935_d1_b0), List(b3354)).name("x8262").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:54:70") } // ParSRAMLoad(x7935,List(List(b3354)),List(x8261))
    val x8263 = withCtrl(x8270) { x8262 } // VectorApply(x8262,0)
    val x8264 = withCtrl(x8270) { ReadMem(x8184_d1).name("x8264").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:54:77") } // RegRead(x8184)
    val x8265 = withCtrl(x8270) { OpDef(op=FixEql, inputs=List(b3354, Const(0))).name("x8265").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:54:77") } // FixEql(b3354,Const(0))
    val x8266 = withCtrl(x8270) { ReduceAccumOp(op=FixMin, input=x8263, accum=x8264).name("x8266").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:54:91") } // Min(x8263,x8264)
    val x8267 = withCtrl(x8270) { OpDef(op=BitAnd, inputs=List(b2976, b2920)).name("x8267").srcCtx("UnrollingBase.scala:28:66") } // And(b2976,b2920)
    val x8268 = withCtrl(x8270) { OpDef(op=BitAnd, inputs=List(x8267, b2911)).name("x8268").srcCtx("UnrollingBase.scala:28:66") } // And(x8267,b2911)
    val x8269_x8184_d0 = withCtrl(x8270) { WriteMem(x8184_d0, x8266).name("x8269_x8184_d0").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:54:77") } // RegWrite(x8184,x8266,x8268)
    antiDepsOf(x8269_x8184_d0)=List(x8264)
    val x8269_x8184_d1 = withCtrl(x8270) { WriteMem(x8184_d1, x8266).name("x8269_x8184_d1").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:54:77") } // RegWrite(x8184,x8266,x8268)
    antiDepsOf(x8269_x8184_d1)=List(x8264)
    val x8271 = withCtrl(x8299) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x8271").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:54:49") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x8272 = withCtrl(x8299) { CounterChain(List(x8271)).name("x8272").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:54:77") } // CounterChainNew(List(x8271))
    val x8284 = withCtrl(x8299) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8272).name("x8284").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:54:77") } // UnrolledReduce(List(b2977, b2920, b2911),x8272,x8185,Block((x8185) => Const(())),List(List(b3368)),List(List(b3369)))
    val b3368 = withCtrl(x8284) { CounterIter(x8271, None).name("b3368") } // b3368
    val b3369 = withCtrl(x8284) { Const(true).name("b3369") } // b3369
    val x8273 = withCtrl(x8284) { OpDef(op=BitAnd, inputs=List(b3369, b2977)).name("x8273").srcCtx("UnrollingBase.scala:28:66") } // And(b3369,b2977)
    val x8274 = withCtrl(x8284) { OpDef(op=BitAnd, inputs=List(b2920, b2911)).name("x8274").srcCtx("UnrollingBase.scala:28:66") } // And(b2920,b2911)
    val x8275 = withCtrl(x8284) { OpDef(op=BitAnd, inputs=List(x8273, x8274)).name("x8275").srcCtx("UnrollingBase.scala:28:66") } // And(x8273,x8274)
    val x8276 = withCtrl(x8284) { LoadBanks(List(x7936_d1_b0), List(b3368)).name("x8276").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:54:70") } // ParSRAMLoad(x7936,List(List(b3368)),List(x8275))
    val x8277 = withCtrl(x8284) { x8276 } // VectorApply(x8276,0)
    val x8278 = withCtrl(x8284) { ReadMem(x8185_d1).name("x8278").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:54:77") } // RegRead(x8185)
    val x8279 = withCtrl(x8284) { OpDef(op=FixEql, inputs=List(b3368, Const(0))).name("x8279").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:54:77") } // FixEql(b3368,Const(0))
    val x8280 = withCtrl(x8284) { ReduceAccumOp(op=FixMin, input=x8277, accum=x8278).name("x8280").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:54:91") } // Min(x8277,x8278)
    val x8281 = withCtrl(x8284) { OpDef(op=BitAnd, inputs=List(b2977, b2920)).name("x8281").srcCtx("UnrollingBase.scala:28:66") } // And(b2977,b2920)
    val x8282 = withCtrl(x8284) { OpDef(op=BitAnd, inputs=List(x8281, b2911)).name("x8282").srcCtx("UnrollingBase.scala:28:66") } // And(x8281,b2911)
    val x8283_x8185_d0 = withCtrl(x8284) { WriteMem(x8185_d0, x8280).name("x8283_x8185_d0").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:54:77") } // RegWrite(x8185,x8280,x8282)
    antiDepsOf(x8283_x8185_d0)=List(x8278)
    val x8283_x8185_d1 = withCtrl(x8284) { WriteMem(x8185_d1, x8280).name("x8283_x8185_d1").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:54:77") } // RegWrite(x8185,x8280,x8282)
    antiDepsOf(x8283_x8185_d1)=List(x8278)
    val x8285 = withCtrl(x8299) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x8285").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:54:49") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x8286 = withCtrl(x8299) { CounterChain(List(x8285)).name("x8286").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:54:77") } // CounterChainNew(List(x8285))
    val x8298 = withCtrl(x8299) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8286).name("x8298").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:54:77") } // UnrolledReduce(List(b2978, b2920, b2911),x8286,x8186,Block((x8186) => Const(())),List(List(b3382)),List(List(b3383)))
    val b3382 = withCtrl(x8298) { CounterIter(x8285, None).name("b3382") } // b3382
    val b3383 = withCtrl(x8298) { Const(true).name("b3383") } // b3383
    val x8287 = withCtrl(x8298) { OpDef(op=BitAnd, inputs=List(b3383, b2978)).name("x8287").srcCtx("UnrollingBase.scala:28:66") } // And(b3383,b2978)
    val x8288 = withCtrl(x8298) { OpDef(op=BitAnd, inputs=List(b2920, b2911)).name("x8288").srcCtx("UnrollingBase.scala:28:66") } // And(b2920,b2911)
    val x8289 = withCtrl(x8298) { OpDef(op=BitAnd, inputs=List(x8287, x8288)).name("x8289").srcCtx("UnrollingBase.scala:28:66") } // And(x8287,x8288)
    val x8290 = withCtrl(x8298) { LoadBanks(List(x7937_d1_b0), List(b3382)).name("x8290").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:54:70") } // ParSRAMLoad(x7937,List(List(b3382)),List(x8289))
    val x8291 = withCtrl(x8298) { x8290 } // VectorApply(x8290,0)
    val x8292 = withCtrl(x8298) { ReadMem(x8186_d1).name("x8292").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:54:77") } // RegRead(x8186)
    val x8293 = withCtrl(x8298) { OpDef(op=FixEql, inputs=List(b3382, Const(0))).name("x8293").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:54:77") } // FixEql(b3382,Const(0))
    val x8294 = withCtrl(x8298) { ReduceAccumOp(op=FixMin, input=x8291, accum=x8292).name("x8294").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:54:91") } // Min(x8291,x8292)
    val x8295 = withCtrl(x8298) { OpDef(op=BitAnd, inputs=List(b2978, b2920)).name("x8295").srcCtx("UnrollingBase.scala:28:66") } // And(b2978,b2920)
    val x8296 = withCtrl(x8298) { OpDef(op=BitAnd, inputs=List(x8295, b2911)).name("x8296").srcCtx("UnrollingBase.scala:28:66") } // And(x8295,b2911)
    val x8297_x8186_d0 = withCtrl(x8298) { WriteMem(x8186_d0, x8294).name("x8297_x8186_d0").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:54:77") } // RegWrite(x8186,x8294,x8296)
    antiDepsOf(x8297_x8186_d0)=List(x8292)
    val x8297_x8186_d1 = withCtrl(x8298) { WriteMem(x8186_d1, x8294).name("x8297_x8186_d1").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:54:77") } // RegWrite(x8186,x8294,x8296)
    antiDepsOf(x8297_x8186_d1)=List(x8292)
    val x8300_d0 = withCtrl(x8640) { Reg(init=Some(0)).name("x8300_d0").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:55:37:minCent") } // x8300 = RegNew(Const(0))
    isAccum(x8300_d0) = false
    bufferDepthOf(x8300_d0) = 2
    val x8300_d1 = withCtrl(x8640) { Reg(init=Some(0)).name("x8300_d1").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:55:37:minCent") } // x8300 = RegNew(Const(0))
    isAccum(x8300_d1) = true
    bufferDepthOf(x8300_d1) = 1
    val x8301_d0 = withCtrl(x8640) { Reg(init=Some(0)).name("x8301_d0").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:55:37:minCent") } // x8301 = RegNew(Const(0))
    isAccum(x8301_d0) = false
    bufferDepthOf(x8301_d0) = 2
    val x8301_d1 = withCtrl(x8640) { Reg(init=Some(0)).name("x8301_d1").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:55:37:minCent") } // x8301 = RegNew(Const(0))
    isAccum(x8301_d1) = true
    bufferDepthOf(x8301_d1) = 1
    val x8302_d0 = withCtrl(x8640) { Reg(init=Some(0)).name("x8302_d0").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:55:37:minCent") } // x8302 = RegNew(Const(0))
    isAccum(x8302_d0) = false
    bufferDepthOf(x8302_d0) = 2
    val x8302_d1 = withCtrl(x8640) { Reg(init=Some(0)).name("x8302_d1").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:55:37:minCent") } // x8302 = RegNew(Const(0))
    isAccum(x8302_d1) = true
    bufferDepthOf(x8302_d1) = 1
    val x8303_d0 = withCtrl(x8640) { Reg(init=Some(0)).name("x8303_d0").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:55:37:minCent") } // x8303 = RegNew(Const(0))
    isAccum(x8303_d0) = false
    bufferDepthOf(x8303_d0) = 2
    val x8303_d1 = withCtrl(x8640) { Reg(init=Some(0)).name("x8303_d1").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:55:37:minCent") } // x8303 = RegNew(Const(0))
    isAccum(x8303_d1) = true
    bufferDepthOf(x8303_d1) = 1
    val x8304_d0 = withCtrl(x8640) { Reg(init=Some(0)).name("x8304_d0").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:55:37:minCent") } // x8304 = RegNew(Const(0))
    isAccum(x8304_d0) = false
    bufferDepthOf(x8304_d0) = 2
    val x8304_d1 = withCtrl(x8640) { Reg(init=Some(0)).name("x8304_d1").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:55:37:minCent") } // x8304 = RegNew(Const(0))
    isAccum(x8304_d1) = true
    bufferDepthOf(x8304_d1) = 1
    val x8305_d0 = withCtrl(x8640) { Reg(init=Some(0)).name("x8305_d0").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:55:37:minCent") } // x8305 = RegNew(Const(0))
    isAccum(x8305_d0) = false
    bufferDepthOf(x8305_d0) = 2
    val x8305_d1 = withCtrl(x8640) { Reg(init=Some(0)).name("x8305_d1").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:55:37:minCent") } // x8305 = RegNew(Const(0))
    isAccum(x8305_d1) = true
    bufferDepthOf(x8305_d1) = 1
    val x8306_d0 = withCtrl(x8640) { Reg(init=Some(0)).name("x8306_d0").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:55:37:minCent") } // x8306 = RegNew(Const(0))
    isAccum(x8306_d0) = false
    bufferDepthOf(x8306_d0) = 2
    val x8306_d1 = withCtrl(x8640) { Reg(init=Some(0)).name("x8306_d1").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:55:37:minCent") } // x8306 = RegNew(Const(0))
    isAccum(x8306_d1) = true
    bufferDepthOf(x8306_d1) = 1
    val x8307_d0 = withCtrl(x8640) { Reg(init=Some(0)).name("x8307_d0").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:55:37:minCent") } // x8307 = RegNew(Const(0))
    isAccum(x8307_d0) = false
    bufferDepthOf(x8307_d0) = 2
    val x8307_d1 = withCtrl(x8640) { Reg(init=Some(0)).name("x8307_d1").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:55:37:minCent") } // x8307 = RegNew(Const(0))
    isAccum(x8307_d1) = true
    bufferDepthOf(x8307_d1) = 1
    val x8444 = withCtrl(x8640) { UnitController(style=ForkJoin, level=OuterControl).name("x8444").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b2920, b2911),Block(Const(())))
    val x8308 = withCtrl(x8444) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x8308").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:55:53") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x8309 = withCtrl(x8444) { CounterChain(List(x8308)).name("x8309").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:57:15") } // CounterChainNew(List(x8308))
    val x8324 = withCtrl(x8444) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8309).name("x8324").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:57:15") } // UnrolledReduce(List(b2971, b2920, b2911),x8309,x8300,Block((x8300) => Const(())),List(List(b3421)),List(List(b3422)))
    val b3421 = withCtrl(x8324) { CounterIter(x8308, None).name("b3421") } // b3421
    val b3422 = withCtrl(x8324) { Const(true).name("b3422") } // b3422
    val x8310 = withCtrl(x8324) { OpDef(op=BitAnd, inputs=List(b3422, b2971)).name("x8310").srcCtx("UnrollingBase.scala:28:66") } // And(b3422,b2971)
    val x8311 = withCtrl(x8324) { OpDef(op=BitAnd, inputs=List(b2920, b2911)).name("x8311").srcCtx("UnrollingBase.scala:28:66") } // And(b2920,b2911)
    val x8312 = withCtrl(x8324) { OpDef(op=BitAnd, inputs=List(x8310, x8311)).name("x8312").srcCtx("UnrollingBase.scala:28:66") } // And(x8310,x8311)
    val x8313 = withCtrl(x8324) { LoadBanks(List(x7930_d0_b0), List(b3421)).name("x8313").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:56:24") } // ParSRAMLoad(x7930,List(List(b3421)),List(x8312))
    val x8314 = withCtrl(x8324) { x8313 } // VectorApply(x8313,0)
    val x8315 = withCtrl(x8324) { ReadMem(x8179_d0).name("x8315").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:56:40") } // RegRead(x8179)
    val x8316 = withCtrl(x8324) { OpDef(op=FixEql, inputs=List(x8314, x8315)).name("x8316").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:56:29") } // FixEql(x8314,x8315)
    val x8317 = withCtrl(x8324) { OpDef(op=MuxOp, inputs=List(x8316, b3421, Const(-1))).name("x8317").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:56:18") } // Mux(x8316,b3421,Const(-1))
    val x8318 = withCtrl(x8324) { ReadMem(x8300_d1).name("x8318").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:57:15") } // RegRead(x8300)
    val x8319 = withCtrl(x8324) { OpDef(op=FixEql, inputs=List(b3421, Const(0))).name("x8319").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:57:15") } // FixEql(b3421,Const(0))
    val x8320 = withCtrl(x8324) { ReduceAccumOp(op=FixMax, input=x8317, accum=x8318).name("x8320").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:57:29") } // Max(x8317,x8318)
    val x8321 = withCtrl(x8324) { OpDef(op=BitAnd, inputs=List(b2971, b2920)).name("x8321").srcCtx("UnrollingBase.scala:28:66") } // And(b2971,b2920)
    val x8322 = withCtrl(x8324) { OpDef(op=BitAnd, inputs=List(x8321, b2911)).name("x8322").srcCtx("UnrollingBase.scala:28:66") } // And(x8321,b2911)
    val x8323_x8300_d0 = withCtrl(x8324) { WriteMem(x8300_d0, x8320).name("x8323_x8300_d0").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:57:15") } // RegWrite(x8300,x8320,x8322)
    antiDepsOf(x8323_x8300_d0)=List(x8318)
    val x8323_x8300_d1 = withCtrl(x8324) { WriteMem(x8300_d1, x8320).name("x8323_x8300_d1").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:57:15") } // RegWrite(x8300,x8320,x8322)
    antiDepsOf(x8323_x8300_d1)=List(x8318)
    val x8325 = withCtrl(x8444) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x8325").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:55:53") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x8326 = withCtrl(x8444) { CounterChain(List(x8325)).name("x8326").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:57:15") } // CounterChainNew(List(x8325))
    val x8341 = withCtrl(x8444) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8326).name("x8341").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:57:15") } // UnrolledReduce(List(b2972, b2920, b2911),x8326,x8301,Block((x8301) => Const(())),List(List(b3438)),List(List(b3439)))
    val b3438 = withCtrl(x8341) { CounterIter(x8325, None).name("b3438") } // b3438
    val b3439 = withCtrl(x8341) { Const(true).name("b3439") } // b3439
    val x8327 = withCtrl(x8341) { OpDef(op=BitAnd, inputs=List(b3439, b2972)).name("x8327").srcCtx("UnrollingBase.scala:28:66") } // And(b3439,b2972)
    val x8328 = withCtrl(x8341) { OpDef(op=BitAnd, inputs=List(b2920, b2911)).name("x8328").srcCtx("UnrollingBase.scala:28:66") } // And(b2920,b2911)
    val x8329 = withCtrl(x8341) { OpDef(op=BitAnd, inputs=List(x8327, x8328)).name("x8329").srcCtx("UnrollingBase.scala:28:66") } // And(x8327,x8328)
    val x8330 = withCtrl(x8341) { LoadBanks(List(x7931_d0_b0), List(b3438)).name("x8330").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:56:24") } // ParSRAMLoad(x7931,List(List(b3438)),List(x8329))
    val x8331 = withCtrl(x8341) { x8330 } // VectorApply(x8330,0)
    val x8332 = withCtrl(x8341) { ReadMem(x8180_d0).name("x8332").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:56:40") } // RegRead(x8180)
    val x8333 = withCtrl(x8341) { OpDef(op=FixEql, inputs=List(x8331, x8332)).name("x8333").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:56:29") } // FixEql(x8331,x8332)
    val x8334 = withCtrl(x8341) { OpDef(op=MuxOp, inputs=List(x8333, b3438, Const(-1))).name("x8334").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:56:18") } // Mux(x8333,b3438,Const(-1))
    val x8335 = withCtrl(x8341) { ReadMem(x8301_d1).name("x8335").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:57:15") } // RegRead(x8301)
    val x8336 = withCtrl(x8341) { OpDef(op=FixEql, inputs=List(b3438, Const(0))).name("x8336").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:57:15") } // FixEql(b3438,Const(0))
    val x8337 = withCtrl(x8341) { ReduceAccumOp(op=FixMax, input=x8334, accum=x8335).name("x8337").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:57:29") } // Max(x8334,x8335)
    val x8338 = withCtrl(x8341) { OpDef(op=BitAnd, inputs=List(b2972, b2920)).name("x8338").srcCtx("UnrollingBase.scala:28:66") } // And(b2972,b2920)
    val x8339 = withCtrl(x8341) { OpDef(op=BitAnd, inputs=List(x8338, b2911)).name("x8339").srcCtx("UnrollingBase.scala:28:66") } // And(x8338,b2911)
    val x8340_x8301_d0 = withCtrl(x8341) { WriteMem(x8301_d0, x8337).name("x8340_x8301_d0").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:57:15") } // RegWrite(x8301,x8337,x8339)
    antiDepsOf(x8340_x8301_d0)=List(x8335)
    val x8340_x8301_d1 = withCtrl(x8341) { WriteMem(x8301_d1, x8337).name("x8340_x8301_d1").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:57:15") } // RegWrite(x8301,x8337,x8339)
    antiDepsOf(x8340_x8301_d1)=List(x8335)
    val x8342 = withCtrl(x8444) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x8342").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:55:53") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x8343 = withCtrl(x8444) { CounterChain(List(x8342)).name("x8343").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:57:15") } // CounterChainNew(List(x8342))
    val x8358 = withCtrl(x8444) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8343).name("x8358").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:57:15") } // UnrolledReduce(List(b2973, b2920, b2911),x8343,x8302,Block((x8302) => Const(())),List(List(b3455)),List(List(b3456)))
    val b3455 = withCtrl(x8358) { CounterIter(x8342, None).name("b3455") } // b3455
    val b3456 = withCtrl(x8358) { Const(true).name("b3456") } // b3456
    val x8344 = withCtrl(x8358) { OpDef(op=BitAnd, inputs=List(b3456, b2973)).name("x8344").srcCtx("UnrollingBase.scala:28:66") } // And(b3456,b2973)
    val x8345 = withCtrl(x8358) { OpDef(op=BitAnd, inputs=List(b2920, b2911)).name("x8345").srcCtx("UnrollingBase.scala:28:66") } // And(b2920,b2911)
    val x8346 = withCtrl(x8358) { OpDef(op=BitAnd, inputs=List(x8344, x8345)).name("x8346").srcCtx("UnrollingBase.scala:28:66") } // And(x8344,x8345)
    val x8347 = withCtrl(x8358) { LoadBanks(List(x7932_d0_b0), List(b3455)).name("x8347").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:56:24") } // ParSRAMLoad(x7932,List(List(b3455)),List(x8346))
    val x8348 = withCtrl(x8358) { x8347 } // VectorApply(x8347,0)
    val x8349 = withCtrl(x8358) { ReadMem(x8181_d0).name("x8349").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:56:40") } // RegRead(x8181)
    val x8350 = withCtrl(x8358) { OpDef(op=FixEql, inputs=List(x8348, x8349)).name("x8350").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:56:29") } // FixEql(x8348,x8349)
    val x8351 = withCtrl(x8358) { OpDef(op=MuxOp, inputs=List(x8350, b3455, Const(-1))).name("x8351").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:56:18") } // Mux(x8350,b3455,Const(-1))
    val x8352 = withCtrl(x8358) { ReadMem(x8302_d1).name("x8352").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:57:15") } // RegRead(x8302)
    val x8353 = withCtrl(x8358) { OpDef(op=FixEql, inputs=List(b3455, Const(0))).name("x8353").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:57:15") } // FixEql(b3455,Const(0))
    val x8354 = withCtrl(x8358) { ReduceAccumOp(op=FixMax, input=x8351, accum=x8352).name("x8354").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:57:29") } // Max(x8351,x8352)
    val x8355 = withCtrl(x8358) { OpDef(op=BitAnd, inputs=List(b2973, b2920)).name("x8355").srcCtx("UnrollingBase.scala:28:66") } // And(b2973,b2920)
    val x8356 = withCtrl(x8358) { OpDef(op=BitAnd, inputs=List(x8355, b2911)).name("x8356").srcCtx("UnrollingBase.scala:28:66") } // And(x8355,b2911)
    val x8357_x8302_d0 = withCtrl(x8358) { WriteMem(x8302_d0, x8354).name("x8357_x8302_d0").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:57:15") } // RegWrite(x8302,x8354,x8356)
    antiDepsOf(x8357_x8302_d0)=List(x8352)
    val x8357_x8302_d1 = withCtrl(x8358) { WriteMem(x8302_d1, x8354).name("x8357_x8302_d1").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:57:15") } // RegWrite(x8302,x8354,x8356)
    antiDepsOf(x8357_x8302_d1)=List(x8352)
    val x8359 = withCtrl(x8444) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x8359").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:55:53") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x8360 = withCtrl(x8444) { CounterChain(List(x8359)).name("x8360").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:57:15") } // CounterChainNew(List(x8359))
    val x8375 = withCtrl(x8444) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8360).name("x8375").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:57:15") } // UnrolledReduce(List(b2974, b2920, b2911),x8360,x8303,Block((x8303) => Const(())),List(List(b3472)),List(List(b3473)))
    val b3472 = withCtrl(x8375) { CounterIter(x8359, None).name("b3472") } // b3472
    val b3473 = withCtrl(x8375) { Const(true).name("b3473") } // b3473
    val x8361 = withCtrl(x8375) { OpDef(op=BitAnd, inputs=List(b3473, b2974)).name("x8361").srcCtx("UnrollingBase.scala:28:66") } // And(b3473,b2974)
    val x8362 = withCtrl(x8375) { OpDef(op=BitAnd, inputs=List(b2920, b2911)).name("x8362").srcCtx("UnrollingBase.scala:28:66") } // And(b2920,b2911)
    val x8363 = withCtrl(x8375) { OpDef(op=BitAnd, inputs=List(x8361, x8362)).name("x8363").srcCtx("UnrollingBase.scala:28:66") } // And(x8361,x8362)
    val x8364 = withCtrl(x8375) { LoadBanks(List(x7933_d0_b0), List(b3472)).name("x8364").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:56:24") } // ParSRAMLoad(x7933,List(List(b3472)),List(x8363))
    val x8365 = withCtrl(x8375) { x8364 } // VectorApply(x8364,0)
    val x8366 = withCtrl(x8375) { ReadMem(x8182_d0).name("x8366").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:56:40") } // RegRead(x8182)
    val x8367 = withCtrl(x8375) { OpDef(op=FixEql, inputs=List(x8365, x8366)).name("x8367").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:56:29") } // FixEql(x8365,x8366)
    val x8368 = withCtrl(x8375) { OpDef(op=MuxOp, inputs=List(x8367, b3472, Const(-1))).name("x8368").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:56:18") } // Mux(x8367,b3472,Const(-1))
    val x8369 = withCtrl(x8375) { ReadMem(x8303_d1).name("x8369").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:57:15") } // RegRead(x8303)
    val x8370 = withCtrl(x8375) { OpDef(op=FixEql, inputs=List(b3472, Const(0))).name("x8370").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:57:15") } // FixEql(b3472,Const(0))
    val x8371 = withCtrl(x8375) { ReduceAccumOp(op=FixMax, input=x8368, accum=x8369).name("x8371").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:57:29") } // Max(x8368,x8369)
    val x8372 = withCtrl(x8375) { OpDef(op=BitAnd, inputs=List(b2974, b2920)).name("x8372").srcCtx("UnrollingBase.scala:28:66") } // And(b2974,b2920)
    val x8373 = withCtrl(x8375) { OpDef(op=BitAnd, inputs=List(x8372, b2911)).name("x8373").srcCtx("UnrollingBase.scala:28:66") } // And(x8372,b2911)
    val x8374_x8303_d0 = withCtrl(x8375) { WriteMem(x8303_d0, x8371).name("x8374_x8303_d0").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:57:15") } // RegWrite(x8303,x8371,x8373)
    antiDepsOf(x8374_x8303_d0)=List(x8369)
    val x8374_x8303_d1 = withCtrl(x8375) { WriteMem(x8303_d1, x8371).name("x8374_x8303_d1").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:57:15") } // RegWrite(x8303,x8371,x8373)
    antiDepsOf(x8374_x8303_d1)=List(x8369)
    val x8376 = withCtrl(x8444) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x8376").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:55:53") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x8377 = withCtrl(x8444) { CounterChain(List(x8376)).name("x8377").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:57:15") } // CounterChainNew(List(x8376))
    val x8392 = withCtrl(x8444) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8377).name("x8392").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:57:15") } // UnrolledReduce(List(b2975, b2920, b2911),x8377,x8304,Block((x8304) => Const(())),List(List(b3489)),List(List(b3490)))
    val b3489 = withCtrl(x8392) { CounterIter(x8376, None).name("b3489") } // b3489
    val b3490 = withCtrl(x8392) { Const(true).name("b3490") } // b3490
    val x8378 = withCtrl(x8392) { OpDef(op=BitAnd, inputs=List(b3490, b2975)).name("x8378").srcCtx("UnrollingBase.scala:28:66") } // And(b3490,b2975)
    val x8379 = withCtrl(x8392) { OpDef(op=BitAnd, inputs=List(b2920, b2911)).name("x8379").srcCtx("UnrollingBase.scala:28:66") } // And(b2920,b2911)
    val x8380 = withCtrl(x8392) { OpDef(op=BitAnd, inputs=List(x8378, x8379)).name("x8380").srcCtx("UnrollingBase.scala:28:66") } // And(x8378,x8379)
    val x8381 = withCtrl(x8392) { LoadBanks(List(x7934_d0_b0), List(b3489)).name("x8381").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:56:24") } // ParSRAMLoad(x7934,List(List(b3489)),List(x8380))
    val x8382 = withCtrl(x8392) { x8381 } // VectorApply(x8381,0)
    val x8383 = withCtrl(x8392) { ReadMem(x8183_d0).name("x8383").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:56:40") } // RegRead(x8183)
    val x8384 = withCtrl(x8392) { OpDef(op=FixEql, inputs=List(x8382, x8383)).name("x8384").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:56:29") } // FixEql(x8382,x8383)
    val x8385 = withCtrl(x8392) { OpDef(op=MuxOp, inputs=List(x8384, b3489, Const(-1))).name("x8385").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:56:18") } // Mux(x8384,b3489,Const(-1))
    val x8386 = withCtrl(x8392) { ReadMem(x8304_d1).name("x8386").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:57:15") } // RegRead(x8304)
    val x8387 = withCtrl(x8392) { OpDef(op=FixEql, inputs=List(b3489, Const(0))).name("x8387").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:57:15") } // FixEql(b3489,Const(0))
    val x8388 = withCtrl(x8392) { ReduceAccumOp(op=FixMax, input=x8385, accum=x8386).name("x8388").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:57:29") } // Max(x8385,x8386)
    val x8389 = withCtrl(x8392) { OpDef(op=BitAnd, inputs=List(b2975, b2920)).name("x8389").srcCtx("UnrollingBase.scala:28:66") } // And(b2975,b2920)
    val x8390 = withCtrl(x8392) { OpDef(op=BitAnd, inputs=List(x8389, b2911)).name("x8390").srcCtx("UnrollingBase.scala:28:66") } // And(x8389,b2911)
    val x8391_x8304_d0 = withCtrl(x8392) { WriteMem(x8304_d0, x8388).name("x8391_x8304_d0").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:57:15") } // RegWrite(x8304,x8388,x8390)
    antiDepsOf(x8391_x8304_d0)=List(x8386)
    val x8391_x8304_d1 = withCtrl(x8392) { WriteMem(x8304_d1, x8388).name("x8391_x8304_d1").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:57:15") } // RegWrite(x8304,x8388,x8390)
    def split2 = {
    antiDepsOf(x8391_x8304_d1)=List(x8386)
    val x8393 = withCtrl(x8444) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x8393").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:55:53") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x8394 = withCtrl(x8444) { CounterChain(List(x8393)).name("x8394").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:57:15") } // CounterChainNew(List(x8393))
    val x8409 = withCtrl(x8444) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8394).name("x8409").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:57:15") } // UnrolledReduce(List(b2976, b2920, b2911),x8394,x8305,Block((x8305) => Const(())),List(List(b3506)),List(List(b3507)))
    val b3506 = withCtrl(x8409) { CounterIter(x8393, None).name("b3506") } // b3506
    val b3507 = withCtrl(x8409) { Const(true).name("b3507") } // b3507
    val x8395 = withCtrl(x8409) { OpDef(op=BitAnd, inputs=List(b3507, b2976)).name("x8395").srcCtx("UnrollingBase.scala:28:66") } // And(b3507,b2976)
    val x8396 = withCtrl(x8409) { OpDef(op=BitAnd, inputs=List(b2920, b2911)).name("x8396").srcCtx("UnrollingBase.scala:28:66") } // And(b2920,b2911)
    val x8397 = withCtrl(x8409) { OpDef(op=BitAnd, inputs=List(x8395, x8396)).name("x8397").srcCtx("UnrollingBase.scala:28:66") } // And(x8395,x8396)
    val x8398 = withCtrl(x8409) { LoadBanks(List(x7935_d0_b0), List(b3506)).name("x8398").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:56:24") } // ParSRAMLoad(x7935,List(List(b3506)),List(x8397))
    val x8399 = withCtrl(x8409) { x8398 } // VectorApply(x8398,0)
    val x8400 = withCtrl(x8409) { ReadMem(x8184_d0).name("x8400").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:56:40") } // RegRead(x8184)
    val x8401 = withCtrl(x8409) { OpDef(op=FixEql, inputs=List(x8399, x8400)).name("x8401").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:56:29") } // FixEql(x8399,x8400)
    val x8402 = withCtrl(x8409) { OpDef(op=MuxOp, inputs=List(x8401, b3506, Const(-1))).name("x8402").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:56:18") } // Mux(x8401,b3506,Const(-1))
    val x8403 = withCtrl(x8409) { ReadMem(x8305_d1).name("x8403").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:57:15") } // RegRead(x8305)
    val x8404 = withCtrl(x8409) { OpDef(op=FixEql, inputs=List(b3506, Const(0))).name("x8404").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:57:15") } // FixEql(b3506,Const(0))
    val x8405 = withCtrl(x8409) { ReduceAccumOp(op=FixMax, input=x8402, accum=x8403).name("x8405").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:57:29") } // Max(x8402,x8403)
    val x8406 = withCtrl(x8409) { OpDef(op=BitAnd, inputs=List(b2976, b2920)).name("x8406").srcCtx("UnrollingBase.scala:28:66") } // And(b2976,b2920)
    val x8407 = withCtrl(x8409) { OpDef(op=BitAnd, inputs=List(x8406, b2911)).name("x8407").srcCtx("UnrollingBase.scala:28:66") } // And(x8406,b2911)
    val x8408_x8305_d0 = withCtrl(x8409) { WriteMem(x8305_d0, x8405).name("x8408_x8305_d0").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:57:15") } // RegWrite(x8305,x8405,x8407)
    antiDepsOf(x8408_x8305_d0)=List(x8403)
    val x8408_x8305_d1 = withCtrl(x8409) { WriteMem(x8305_d1, x8405).name("x8408_x8305_d1").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:57:15") } // RegWrite(x8305,x8405,x8407)
    antiDepsOf(x8408_x8305_d1)=List(x8403)
    val x8410 = withCtrl(x8444) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x8410").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:55:53") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x8411 = withCtrl(x8444) { CounterChain(List(x8410)).name("x8411").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:57:15") } // CounterChainNew(List(x8410))
    val x8426 = withCtrl(x8444) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8411).name("x8426").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:57:15") } // UnrolledReduce(List(b2977, b2920, b2911),x8411,x8306,Block((x8306) => Const(())),List(List(b3523)),List(List(b3524)))
    val b3523 = withCtrl(x8426) { CounterIter(x8410, None).name("b3523") } // b3523
    val b3524 = withCtrl(x8426) { Const(true).name("b3524") } // b3524
    val x8412 = withCtrl(x8426) { OpDef(op=BitAnd, inputs=List(b3524, b2977)).name("x8412").srcCtx("UnrollingBase.scala:28:66") } // And(b3524,b2977)
    val x8413 = withCtrl(x8426) { OpDef(op=BitAnd, inputs=List(b2920, b2911)).name("x8413").srcCtx("UnrollingBase.scala:28:66") } // And(b2920,b2911)
    val x8414 = withCtrl(x8426) { OpDef(op=BitAnd, inputs=List(x8412, x8413)).name("x8414").srcCtx("UnrollingBase.scala:28:66") } // And(x8412,x8413)
    val x8415 = withCtrl(x8426) { LoadBanks(List(x7936_d0_b0), List(b3523)).name("x8415").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:56:24") } // ParSRAMLoad(x7936,List(List(b3523)),List(x8414))
    val x8416 = withCtrl(x8426) { x8415 } // VectorApply(x8415,0)
    val x8417 = withCtrl(x8426) { ReadMem(x8185_d0).name("x8417").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:56:40") } // RegRead(x8185)
    val x8418 = withCtrl(x8426) { OpDef(op=FixEql, inputs=List(x8416, x8417)).name("x8418").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:56:29") } // FixEql(x8416,x8417)
    val x8419 = withCtrl(x8426) { OpDef(op=MuxOp, inputs=List(x8418, b3523, Const(-1))).name("x8419").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:56:18") } // Mux(x8418,b3523,Const(-1))
    val x8420 = withCtrl(x8426) { ReadMem(x8306_d1).name("x8420").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:57:15") } // RegRead(x8306)
    val x8421 = withCtrl(x8426) { OpDef(op=FixEql, inputs=List(b3523, Const(0))).name("x8421").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:57:15") } // FixEql(b3523,Const(0))
    val x8422 = withCtrl(x8426) { ReduceAccumOp(op=FixMax, input=x8419, accum=x8420).name("x8422").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:57:29") } // Max(x8419,x8420)
    val x8423 = withCtrl(x8426) { OpDef(op=BitAnd, inputs=List(b2977, b2920)).name("x8423").srcCtx("UnrollingBase.scala:28:66") } // And(b2977,b2920)
    val x8424 = withCtrl(x8426) { OpDef(op=BitAnd, inputs=List(x8423, b2911)).name("x8424").srcCtx("UnrollingBase.scala:28:66") } // And(x8423,b2911)
    val x8425_x8306_d0 = withCtrl(x8426) { WriteMem(x8306_d0, x8422).name("x8425_x8306_d0").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:57:15") } // RegWrite(x8306,x8422,x8424)
    antiDepsOf(x8425_x8306_d0)=List(x8420)
    val x8425_x8306_d1 = withCtrl(x8426) { WriteMem(x8306_d1, x8422).name("x8425_x8306_d1").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:57:15") } // RegWrite(x8306,x8422,x8424)
    antiDepsOf(x8425_x8306_d1)=List(x8420)
    val x8427 = withCtrl(x8444) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x8427").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:55:53") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x8428 = withCtrl(x8444) { CounterChain(List(x8427)).name("x8428").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:57:15") } // CounterChainNew(List(x8427))
    val x8443 = withCtrl(x8444) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8428).name("x8443").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:57:15") } // UnrolledReduce(List(b2978, b2920, b2911),x8428,x8307,Block((x8307) => Const(())),List(List(b3540)),List(List(b3541)))
    val b3540 = withCtrl(x8443) { CounterIter(x8427, None).name("b3540") } // b3540
    val b3541 = withCtrl(x8443) { Const(true).name("b3541") } // b3541
    val x8429 = withCtrl(x8443) { OpDef(op=BitAnd, inputs=List(b3541, b2978)).name("x8429").srcCtx("UnrollingBase.scala:28:66") } // And(b3541,b2978)
    val x8430 = withCtrl(x8443) { OpDef(op=BitAnd, inputs=List(b2920, b2911)).name("x8430").srcCtx("UnrollingBase.scala:28:66") } // And(b2920,b2911)
    val x8431 = withCtrl(x8443) { OpDef(op=BitAnd, inputs=List(x8429, x8430)).name("x8431").srcCtx("UnrollingBase.scala:28:66") } // And(x8429,x8430)
    val x8432 = withCtrl(x8443) { LoadBanks(List(x7937_d0_b0), List(b3540)).name("x8432").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:56:24") } // ParSRAMLoad(x7937,List(List(b3540)),List(x8431))
    val x8433 = withCtrl(x8443) { x8432 } // VectorApply(x8432,0)
    val x8434 = withCtrl(x8443) { ReadMem(x8186_d0).name("x8434").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:56:40") } // RegRead(x8186)
    val x8435 = withCtrl(x8443) { OpDef(op=FixEql, inputs=List(x8433, x8434)).name("x8435").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:56:29") } // FixEql(x8433,x8434)
    val x8436 = withCtrl(x8443) { OpDef(op=MuxOp, inputs=List(x8435, b3540, Const(-1))).name("x8436").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:56:18") } // Mux(x8435,b3540,Const(-1))
    val x8437 = withCtrl(x8443) { ReadMem(x8307_d1).name("x8437").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:57:15") } // RegRead(x8307)
    val x8438 = withCtrl(x8443) { OpDef(op=FixEql, inputs=List(b3540, Const(0))).name("x8438").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:57:15") } // FixEql(b3540,Const(0))
    val x8439 = withCtrl(x8443) { ReduceAccumOp(op=FixMax, input=x8436, accum=x8437).name("x8439").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:57:29") } // Max(x8436,x8437)
    val x8440 = withCtrl(x8443) { OpDef(op=BitAnd, inputs=List(b2978, b2920)).name("x8440").srcCtx("UnrollingBase.scala:28:66") } // And(b2978,b2920)
    val x8441 = withCtrl(x8443) { OpDef(op=BitAnd, inputs=List(x8440, b2911)).name("x8441").srcCtx("UnrollingBase.scala:28:66") } // And(x8440,b2911)
    val x8442_x8307_d0 = withCtrl(x8443) { WriteMem(x8307_d0, x8439).name("x8442_x8307_d0").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:57:15") } // RegWrite(x8307,x8439,x8441)
    antiDepsOf(x8442_x8307_d0)=List(x8437)
    val x8442_x8307_d1 = withCtrl(x8443) { WriteMem(x8307_d1, x8439).name("x8442_x8307_d1").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:57:15") } // RegWrite(x8307,x8439,x8441)
    antiDepsOf(x8442_x8307_d1)=List(x8437)
    val x8445_d0_b0 = withCtrl(x8640) { SRAM(size=4096, banking=Strided(banks=16, stride=1)).name("x8445_d0_b0").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:59:36:localCent") } // x8445 = SRAMNew(ArrayBuffer(Const(64), Const(64)))
    isAccum(x8445_d0_b0) = false
    bufferDepthOf(x8445_d0_b0) = 2
    staticDimsOf(x8445_d0_b0) = List(64, 64)
    val x8446_d0_b0 = withCtrl(x8640) { SRAM(size=4096, banking=Strided(banks=16, stride=1)).name("x8446_d0_b0").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:59:36:localCent") } // x8446 = SRAMNew(ArrayBuffer(Const(64), Const(64)))
    isAccum(x8446_d0_b0) = false
    bufferDepthOf(x8446_d0_b0) = 2
    staticDimsOf(x8446_d0_b0) = List(64, 64)
    val x8447_d0_b0 = withCtrl(x8640) { SRAM(size=4096, banking=Strided(banks=16, stride=1)).name("x8447_d0_b0").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:59:36:localCent") } // x8447 = SRAMNew(ArrayBuffer(Const(64), Const(64)))
    isAccum(x8447_d0_b0) = false
    bufferDepthOf(x8447_d0_b0) = 2
    staticDimsOf(x8447_d0_b0) = List(64, 64)
    val x8448_d0_b0 = withCtrl(x8640) { SRAM(size=4096, banking=Strided(banks=16, stride=1)).name("x8448_d0_b0").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:59:36:localCent") } // x8448 = SRAMNew(ArrayBuffer(Const(64), Const(64)))
    isAccum(x8448_d0_b0) = false
    bufferDepthOf(x8448_d0_b0) = 2
    staticDimsOf(x8448_d0_b0) = List(64, 64)
    val x8449_d0_b0 = withCtrl(x8640) { SRAM(size=4096, banking=Strided(banks=16, stride=1)).name("x8449_d0_b0").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:59:36:localCent") } // x8449 = SRAMNew(ArrayBuffer(Const(64), Const(64)))
    isAccum(x8449_d0_b0) = false
    bufferDepthOf(x8449_d0_b0) = 2
    staticDimsOf(x8449_d0_b0) = List(64, 64)
    val x8450_d0_b0 = withCtrl(x8640) { SRAM(size=4096, banking=Strided(banks=16, stride=1)).name("x8450_d0_b0").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:59:36:localCent") } // x8450 = SRAMNew(ArrayBuffer(Const(64), Const(64)))
    isAccum(x8450_d0_b0) = false
    bufferDepthOf(x8450_d0_b0) = 2
    staticDimsOf(x8450_d0_b0) = List(64, 64)
    val x8451_d0_b0 = withCtrl(x8640) { SRAM(size=4096, banking=Strided(banks=16, stride=1)).name("x8451_d0_b0").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:59:36:localCent") } // x8451 = SRAMNew(ArrayBuffer(Const(64), Const(64)))
    isAccum(x8451_d0_b0) = false
    bufferDepthOf(x8451_d0_b0) = 2
    staticDimsOf(x8451_d0_b0) = List(64, 64)
    val x8452_d0_b0 = withCtrl(x8640) { SRAM(size=4096, banking=Strided(banks=16, stride=1)).name("x8452_d0_b0").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:59:36:localCent") } // x8452 = SRAMNew(ArrayBuffer(Const(64), Const(64)))
    isAccum(x8452_d0_b0) = false
    bufferDepthOf(x8452_d0_b0) = 2
    staticDimsOf(x8452_d0_b0) = List(64, 64)
    val x8565 = withCtrl(x8640) { UnitController(style=ForkJoin, level=OuterControl).name("x8565").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b2920, b2911),Block(Const(())))
    val x8453 = withCtrl(x8565) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x8453").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:60:31") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x8454 = withCtrl(x8565) { Counter(min=Const(0), max=Const(64), step=Const(1), par=1).name("x8454").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:60:23") } // CounterNew(Const(0),Const(64),Const(1),Const(1))
    val x8455 = withCtrl(x8565) { CounterChain(List(x8454,x8453)).name("x8455").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:60:38") } // CounterChainNew(List(x8454, x8453))
    val x8466 = withCtrl(x8565) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8455).name("x8466").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:60:38") } // UnrolledForeach(List(b2971, b2920, b2911),x8455,Block(Const(())),List(List(b3590), List(b3591)),List(List(b3592), List(b3593)))
    val b3590 = withCtrl(x8466) { CounterIter(x8454, Some(0)).name("b3590") } // b3590
    val b3592 = withCtrl(x8466) { Const(true).name("b3592") } // b3592
    val b3591 = withCtrl(x8466) { CounterIter(x8453, None).name("b3591") } // b3591
    val b3593 = withCtrl(x8466) { Const(true).name("b3593") } // b3593
    val x8456 = withCtrl(x8466) { ReadMem(x8300_d0).name("x8456").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:61:52") } // RegRead(x8300)
    val x8457 = withCtrl(x8466) { OpDef(op=FixEql, inputs=List(b3590, x8456)).name("x8457").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:61:41") } // FixEql(b3590,x8456)
    val x8458 = withCtrl(x8466) { OpDef(op=BitAnd, inputs=List(b3592, b3593)).name("x8458").srcCtx("UnrollingBase.scala:28:66") } // And(b3592,b3593)
    val x8459 = withCtrl(x8466) { OpDef(op=BitAnd, inputs=List(b2971, b2920)).name("x8459").srcCtx("UnrollingBase.scala:28:66") } // And(b2971,b2920)
    val x8460 = withCtrl(x8466) { OpDef(op=BitAnd, inputs=List(x8458, x8459)).name("x8460").srcCtx("UnrollingBase.scala:28:66") } // And(x8458,x8459)
    val x8461 = withCtrl(x8466) { OpDef(op=BitAnd, inputs=List(x8460, b2911)).name("x8461").srcCtx("UnrollingBase.scala:28:66") } // And(x8460,b2911)
    val x8462 = withCtrl(x8466) { LoadBanks(List(x7895_d0_b0), List(b2963, b3591)).name("x8462").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:61:62") } // ParSRAMLoad(x7895,List(List(b2963, b3591)),List(x8461))
    val x8463 = withCtrl(x8466) { x8462 } // VectorApply(x8462,0)
    val x8464 = withCtrl(x8466) { OpDef(op=MuxOp, inputs=List(x8457, x8463, Const(0))).name("x8464").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:61:37") } // Mux(x8457,x8463,Const(0))
    val x8465 = withCtrl(x8466) { StoreBanks(List(List(x8445_d0_b0)), List(b3590, b3591), x8464).name("x8465").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:61:32") } // ParSRAMStore(x8445,List(List(b3590, b3591)),List(x8464),List(x8461))
    val x8467 = withCtrl(x8565) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x8467").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:60:31") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x8468 = withCtrl(x8565) { Counter(min=Const(0), max=Const(64), step=Const(1), par=1).name("x8468").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:60:23") } // CounterNew(Const(0),Const(64),Const(1),Const(1))
    val x8469 = withCtrl(x8565) { CounterChain(List(x8468,x8467)).name("x8469").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:60:38") } // CounterChainNew(List(x8468, x8467))
    val x8480 = withCtrl(x8565) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8469).name("x8480").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:60:38") } // UnrolledForeach(List(b2972, b2920, b2911),x8469,Block(Const(())),List(List(b3605), List(b3606)),List(List(b3607), List(b3608)))
    val b3605 = withCtrl(x8480) { CounterIter(x8468, Some(0)).name("b3605") } // b3605
    val b3607 = withCtrl(x8480) { Const(true).name("b3607") } // b3607
    val b3606 = withCtrl(x8480) { CounterIter(x8467, None).name("b3606") } // b3606
    val b3608 = withCtrl(x8480) { Const(true).name("b3608") } // b3608
    val x8470 = withCtrl(x8480) { ReadMem(x8301_d0).name("x8470").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:61:52") } // RegRead(x8301)
    val x8471 = withCtrl(x8480) { OpDef(op=FixEql, inputs=List(b3605, x8470)).name("x8471").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:61:41") } // FixEql(b3605,x8470)
    val x8472 = withCtrl(x8480) { OpDef(op=BitAnd, inputs=List(b3607, b3608)).name("x8472").srcCtx("UnrollingBase.scala:28:66") } // And(b3607,b3608)
    val x8473 = withCtrl(x8480) { OpDef(op=BitAnd, inputs=List(b2972, b2920)).name("x8473").srcCtx("UnrollingBase.scala:28:66") } // And(b2972,b2920)
    val x8474 = withCtrl(x8480) { OpDef(op=BitAnd, inputs=List(x8472, x8473)).name("x8474").srcCtx("UnrollingBase.scala:28:66") } // And(x8472,x8473)
    val x8475 = withCtrl(x8480) { OpDef(op=BitAnd, inputs=List(x8474, b2911)).name("x8475").srcCtx("UnrollingBase.scala:28:66") } // And(x8474,b2911)
    val x8476 = withCtrl(x8480) { LoadBanks(List(x7895_d0_b1), List(b2964, b3606)).name("x8476").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:61:62") } // ParSRAMLoad(x7895,List(List(b2964, b3606)),List(x8475))
    val x8477 = withCtrl(x8480) { x8476 } // VectorApply(x8476,0)
    val x8478 = withCtrl(x8480) { OpDef(op=MuxOp, inputs=List(x8471, x8477, Const(0))).name("x8478").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:61:37") } // Mux(x8471,x8477,Const(0))
    val x8479 = withCtrl(x8480) { StoreBanks(List(List(x8446_d0_b0)), List(b3605, b3606), x8478).name("x8479").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:61:32") } // ParSRAMStore(x8446,List(List(b3605, b3606)),List(x8478),List(x8475))
    val x8481 = withCtrl(x8565) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x8481").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:60:31") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x8482 = withCtrl(x8565) { Counter(min=Const(0), max=Const(64), step=Const(1), par=1).name("x8482").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:60:23") } // CounterNew(Const(0),Const(64),Const(1),Const(1))
    val x8483 = withCtrl(x8565) { CounterChain(List(x8482,x8481)).name("x8483").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:60:38") } // CounterChainNew(List(x8482, x8481))
    val x8494 = withCtrl(x8565) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8483).name("x8494").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:60:38") } // UnrolledForeach(List(b2973, b2920, b2911),x8483,Block(Const(())),List(List(b3620), List(b3621)),List(List(b3622), List(b3623)))
    val b3620 = withCtrl(x8494) { CounterIter(x8482, Some(0)).name("b3620") } // b3620
    val b3622 = withCtrl(x8494) { Const(true).name("b3622") } // b3622
    val b3621 = withCtrl(x8494) { CounterIter(x8481, None).name("b3621") } // b3621
    val b3623 = withCtrl(x8494) { Const(true).name("b3623") } // b3623
    val x8484 = withCtrl(x8494) { ReadMem(x8302_d0).name("x8484").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:61:52") } // RegRead(x8302)
    val x8485 = withCtrl(x8494) { OpDef(op=FixEql, inputs=List(b3620, x8484)).name("x8485").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:61:41") } // FixEql(b3620,x8484)
    val x8486 = withCtrl(x8494) { OpDef(op=BitAnd, inputs=List(b3622, b3623)).name("x8486").srcCtx("UnrollingBase.scala:28:66") } // And(b3622,b3623)
    val x8487 = withCtrl(x8494) { OpDef(op=BitAnd, inputs=List(b2973, b2920)).name("x8487").srcCtx("UnrollingBase.scala:28:66") } // And(b2973,b2920)
    val x8488 = withCtrl(x8494) { OpDef(op=BitAnd, inputs=List(x8486, x8487)).name("x8488").srcCtx("UnrollingBase.scala:28:66") } // And(x8486,x8487)
    val x8489 = withCtrl(x8494) { OpDef(op=BitAnd, inputs=List(x8488, b2911)).name("x8489").srcCtx("UnrollingBase.scala:28:66") } // And(x8488,b2911)
    val x8490 = withCtrl(x8494) { LoadBanks(List(x7895_d0_b2), List(b2965, b3621)).name("x8490").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:61:62") } // ParSRAMLoad(x7895,List(List(b2965, b3621)),List(x8489))
    val x8491 = withCtrl(x8494) { x8490 } // VectorApply(x8490,0)
    val x8492 = withCtrl(x8494) { OpDef(op=MuxOp, inputs=List(x8485, x8491, Const(0))).name("x8492").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:61:37") } // Mux(x8485,x8491,Const(0))
    val x8493 = withCtrl(x8494) { StoreBanks(List(List(x8447_d0_b0)), List(b3620, b3621), x8492).name("x8493").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:61:32") } // ParSRAMStore(x8447,List(List(b3620, b3621)),List(x8492),List(x8489))
    val x8495 = withCtrl(x8565) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x8495").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:60:31") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x8496 = withCtrl(x8565) { Counter(min=Const(0), max=Const(64), step=Const(1), par=1).name("x8496").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:60:23") } // CounterNew(Const(0),Const(64),Const(1),Const(1))
    val x8497 = withCtrl(x8565) { CounterChain(List(x8496,x8495)).name("x8497").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:60:38") } // CounterChainNew(List(x8496, x8495))
    val x8508 = withCtrl(x8565) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8497).name("x8508").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:60:38") } // UnrolledForeach(List(b2974, b2920, b2911),x8497,Block(Const(())),List(List(b3635), List(b3636)),List(List(b3637), List(b3638)))
    val b3635 = withCtrl(x8508) { CounterIter(x8496, Some(0)).name("b3635") } // b3635
    val b3637 = withCtrl(x8508) { Const(true).name("b3637") } // b3637
    val b3636 = withCtrl(x8508) { CounterIter(x8495, None).name("b3636") } // b3636
    val b3638 = withCtrl(x8508) { Const(true).name("b3638") } // b3638
    val x8498 = withCtrl(x8508) { ReadMem(x8303_d0).name("x8498").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:61:52") } // RegRead(x8303)
    val x8499 = withCtrl(x8508) { OpDef(op=FixEql, inputs=List(b3635, x8498)).name("x8499").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:61:41") } // FixEql(b3635,x8498)
    val x8500 = withCtrl(x8508) { OpDef(op=BitAnd, inputs=List(b3637, b3638)).name("x8500").srcCtx("UnrollingBase.scala:28:66") } // And(b3637,b3638)
    val x8501 = withCtrl(x8508) { OpDef(op=BitAnd, inputs=List(b2974, b2920)).name("x8501").srcCtx("UnrollingBase.scala:28:66") } // And(b2974,b2920)
    val x8502 = withCtrl(x8508) { OpDef(op=BitAnd, inputs=List(x8500, x8501)).name("x8502").srcCtx("UnrollingBase.scala:28:66") } // And(x8500,x8501)
    val x8503 = withCtrl(x8508) { OpDef(op=BitAnd, inputs=List(x8502, b2911)).name("x8503").srcCtx("UnrollingBase.scala:28:66") } // And(x8502,b2911)
    val x8504 = withCtrl(x8508) { LoadBanks(List(x7895_d0_b3), List(b2966, b3636)).name("x8504").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:61:62") } // ParSRAMLoad(x7895,List(List(b2966, b3636)),List(x8503))
    val x8505 = withCtrl(x8508) { x8504 } // VectorApply(x8504,0)
    val x8506 = withCtrl(x8508) { OpDef(op=MuxOp, inputs=List(x8499, x8505, Const(0))).name("x8506").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:61:37") } // Mux(x8499,x8505,Const(0))
    val x8507 = withCtrl(x8508) { StoreBanks(List(List(x8448_d0_b0)), List(b3635, b3636), x8506).name("x8507").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:61:32") } // ParSRAMStore(x8448,List(List(b3635, b3636)),List(x8506),List(x8503))
    val x8509 = withCtrl(x8565) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x8509").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:60:31") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x8510 = withCtrl(x8565) { Counter(min=Const(0), max=Const(64), step=Const(1), par=1).name("x8510").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:60:23") } // CounterNew(Const(0),Const(64),Const(1),Const(1))
    val x8511 = withCtrl(x8565) { CounterChain(List(x8510,x8509)).name("x8511").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:60:38") } // CounterChainNew(List(x8510, x8509))
    val x8522 = withCtrl(x8565) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8511).name("x8522").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:60:38") } // UnrolledForeach(List(b2975, b2920, b2911),x8511,Block(Const(())),List(List(b3650), List(b3651)),List(List(b3652), List(b3653)))
    val b3650 = withCtrl(x8522) { CounterIter(x8510, Some(0)).name("b3650") } // b3650
    val b3652 = withCtrl(x8522) { Const(true).name("b3652") } // b3652
    val b3651 = withCtrl(x8522) { CounterIter(x8509, None).name("b3651") } // b3651
    val b3653 = withCtrl(x8522) { Const(true).name("b3653") } // b3653
    val x8512 = withCtrl(x8522) { ReadMem(x8304_d0).name("x8512").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:61:52") } // RegRead(x8304)
    val x8513 = withCtrl(x8522) { OpDef(op=FixEql, inputs=List(b3650, x8512)).name("x8513").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:61:41") } // FixEql(b3650,x8512)
    val x8514 = withCtrl(x8522) { OpDef(op=BitAnd, inputs=List(b3652, b3653)).name("x8514").srcCtx("UnrollingBase.scala:28:66") } // And(b3652,b3653)
    val x8515 = withCtrl(x8522) { OpDef(op=BitAnd, inputs=List(b2975, b2920)).name("x8515").srcCtx("UnrollingBase.scala:28:66") } // And(b2975,b2920)
    val x8516 = withCtrl(x8522) { OpDef(op=BitAnd, inputs=List(x8514, x8515)).name("x8516").srcCtx("UnrollingBase.scala:28:66") } // And(x8514,x8515)
    val x8517 = withCtrl(x8522) { OpDef(op=BitAnd, inputs=List(x8516, b2911)).name("x8517").srcCtx("UnrollingBase.scala:28:66") } // And(x8516,b2911)
    val x8518 = withCtrl(x8522) { LoadBanks(List(x7895_d0_b4), List(b2967, b3651)).name("x8518").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:61:62") } // ParSRAMLoad(x7895,List(List(b2967, b3651)),List(x8517))
    val x8519 = withCtrl(x8522) { x8518 } // VectorApply(x8518,0)
    val x8520 = withCtrl(x8522) { OpDef(op=MuxOp, inputs=List(x8513, x8519, Const(0))).name("x8520").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:61:37") } // Mux(x8513,x8519,Const(0))
    val x8521 = withCtrl(x8522) { StoreBanks(List(List(x8449_d0_b0)), List(b3650, b3651), x8520).name("x8521").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:61:32") } // ParSRAMStore(x8449,List(List(b3650, b3651)),List(x8520),List(x8517))
    val x8523 = withCtrl(x8565) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x8523").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:60:31") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x8524 = withCtrl(x8565) { Counter(min=Const(0), max=Const(64), step=Const(1), par=1).name("x8524").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:60:23") } // CounterNew(Const(0),Const(64),Const(1),Const(1))
    val x8525 = withCtrl(x8565) { CounterChain(List(x8524,x8523)).name("x8525").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:60:38") } // CounterChainNew(List(x8524, x8523))
    val x8536 = withCtrl(x8565) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8525).name("x8536").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:60:38") } // UnrolledForeach(List(b2976, b2920, b2911),x8525,Block(Const(())),List(List(b3665), List(b3666)),List(List(b3667), List(b3668)))
    val b3665 = withCtrl(x8536) { CounterIter(x8524, Some(0)).name("b3665") } // b3665
    val b3667 = withCtrl(x8536) { Const(true).name("b3667") } // b3667
    val b3666 = withCtrl(x8536) { CounterIter(x8523, None).name("b3666") } // b3666
    val b3668 = withCtrl(x8536) { Const(true).name("b3668") } // b3668
    val x8526 = withCtrl(x8536) { ReadMem(x8305_d0).name("x8526").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:61:52") } // RegRead(x8305)
    val x8527 = withCtrl(x8536) { OpDef(op=FixEql, inputs=List(b3665, x8526)).name("x8527").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:61:41") } // FixEql(b3665,x8526)
    val x8528 = withCtrl(x8536) { OpDef(op=BitAnd, inputs=List(b3667, b3668)).name("x8528").srcCtx("UnrollingBase.scala:28:66") } // And(b3667,b3668)
    val x8529 = withCtrl(x8536) { OpDef(op=BitAnd, inputs=List(b2976, b2920)).name("x8529").srcCtx("UnrollingBase.scala:28:66") } // And(b2976,b2920)
    val x8530 = withCtrl(x8536) { OpDef(op=BitAnd, inputs=List(x8528, x8529)).name("x8530").srcCtx("UnrollingBase.scala:28:66") } // And(x8528,x8529)
    val x8531 = withCtrl(x8536) { OpDef(op=BitAnd, inputs=List(x8530, b2911)).name("x8531").srcCtx("UnrollingBase.scala:28:66") } // And(x8530,b2911)
    val x8532 = withCtrl(x8536) { LoadBanks(List(x7895_d0_b5), List(b2968, b3666)).name("x8532").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:61:62") } // ParSRAMLoad(x7895,List(List(b2968, b3666)),List(x8531))
    val x8533 = withCtrl(x8536) { x8532 } // VectorApply(x8532,0)
    val x8534 = withCtrl(x8536) { OpDef(op=MuxOp, inputs=List(x8527, x8533, Const(0))).name("x8534").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:61:37") } // Mux(x8527,x8533,Const(0))
    val x8535 = withCtrl(x8536) { StoreBanks(List(List(x8450_d0_b0)), List(b3665, b3666), x8534).name("x8535").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:61:32") } // ParSRAMStore(x8450,List(List(b3665, b3666)),List(x8534),List(x8531))
    val x8537 = withCtrl(x8565) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x8537").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:60:31") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x8538 = withCtrl(x8565) { Counter(min=Const(0), max=Const(64), step=Const(1), par=1).name("x8538").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:60:23") } // CounterNew(Const(0),Const(64),Const(1),Const(1))
    val x8539 = withCtrl(x8565) { CounterChain(List(x8538,x8537)).name("x8539").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:60:38") } // CounterChainNew(List(x8538, x8537))
    val x8550 = withCtrl(x8565) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8539).name("x8550").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:60:38") } // UnrolledForeach(List(b2977, b2920, b2911),x8539,Block(Const(())),List(List(b3680), List(b3681)),List(List(b3682), List(b3683)))
    val b3680 = withCtrl(x8550) { CounterIter(x8538, Some(0)).name("b3680") } // b3680
    val b3682 = withCtrl(x8550) { Const(true).name("b3682") } // b3682
    val b3681 = withCtrl(x8550) { CounterIter(x8537, None).name("b3681") } // b3681
    val b3683 = withCtrl(x8550) { Const(true).name("b3683") } // b3683
    val x8540 = withCtrl(x8550) { ReadMem(x8306_d0).name("x8540").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:61:52") } // RegRead(x8306)
    val x8541 = withCtrl(x8550) { OpDef(op=FixEql, inputs=List(b3680, x8540)).name("x8541").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:61:41") } // FixEql(b3680,x8540)
    val x8542 = withCtrl(x8550) { OpDef(op=BitAnd, inputs=List(b3682, b3683)).name("x8542").srcCtx("UnrollingBase.scala:28:66") } // And(b3682,b3683)
    val x8543 = withCtrl(x8550) { OpDef(op=BitAnd, inputs=List(b2977, b2920)).name("x8543").srcCtx("UnrollingBase.scala:28:66") } // And(b2977,b2920)
    val x8544 = withCtrl(x8550) { OpDef(op=BitAnd, inputs=List(x8542, x8543)).name("x8544").srcCtx("UnrollingBase.scala:28:66") } // And(x8542,x8543)
    val x8545 = withCtrl(x8550) { OpDef(op=BitAnd, inputs=List(x8544, b2911)).name("x8545").srcCtx("UnrollingBase.scala:28:66") } // And(x8544,b2911)
    val x8546 = withCtrl(x8550) { LoadBanks(List(x7895_d0_b6), List(b2969, b3681)).name("x8546").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:61:62") } // ParSRAMLoad(x7895,List(List(b2969, b3681)),List(x8545))
    val x8547 = withCtrl(x8550) { x8546 } // VectorApply(x8546,0)
    val x8548 = withCtrl(x8550) { OpDef(op=MuxOp, inputs=List(x8541, x8547, Const(0))).name("x8548").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:61:37") } // Mux(x8541,x8547,Const(0))
    val x8549 = withCtrl(x8550) { StoreBanks(List(List(x8451_d0_b0)), List(b3680, b3681), x8548).name("x8549").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:61:32") } // ParSRAMStore(x8451,List(List(b3680, b3681)),List(x8548),List(x8545))
    val x8551 = withCtrl(x8565) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x8551").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:60:31") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x8552 = withCtrl(x8565) { Counter(min=Const(0), max=Const(64), step=Const(1), par=1).name("x8552").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:60:23") } // CounterNew(Const(0),Const(64),Const(1),Const(1))
    val x8553 = withCtrl(x8565) { CounterChain(List(x8552,x8551)).name("x8553").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:60:38") } // CounterChainNew(List(x8552, x8551))
    val x8564 = withCtrl(x8565) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8553).name("x8564").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:60:38") } // UnrolledForeach(List(b2978, b2920, b2911),x8553,Block(Const(())),List(List(b3695), List(b3696)),List(List(b3697), List(b3698)))
    val b3695 = withCtrl(x8564) { CounterIter(x8552, Some(0)).name("b3695") } // b3695
    val b3697 = withCtrl(x8564) { Const(true).name("b3697") } // b3697
    val b3696 = withCtrl(x8564) { CounterIter(x8551, None).name("b3696") } // b3696
    val b3698 = withCtrl(x8564) { Const(true).name("b3698") } // b3698
    val x8554 = withCtrl(x8564) { ReadMem(x8307_d0).name("x8554").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:61:52") } // RegRead(x8307)
    val x8555 = withCtrl(x8564) { OpDef(op=FixEql, inputs=List(b3695, x8554)).name("x8555").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:61:41") } // FixEql(b3695,x8554)
    val x8556 = withCtrl(x8564) { OpDef(op=BitAnd, inputs=List(b3697, b3698)).name("x8556").srcCtx("UnrollingBase.scala:28:66") } // And(b3697,b3698)
    val x8557 = withCtrl(x8564) { OpDef(op=BitAnd, inputs=List(b2978, b2920)).name("x8557").srcCtx("UnrollingBase.scala:28:66") } // And(b2978,b2920)
    val x8558 = withCtrl(x8564) { OpDef(op=BitAnd, inputs=List(x8556, x8557)).name("x8558").srcCtx("UnrollingBase.scala:28:66") } // And(x8556,x8557)
    val x8559 = withCtrl(x8564) { OpDef(op=BitAnd, inputs=List(x8558, b2911)).name("x8559").srcCtx("UnrollingBase.scala:28:66") } // And(x8558,b2911)
    val x8560 = withCtrl(x8564) { LoadBanks(List(x7895_d0_b7), List(b2970, b3696)).name("x8560").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:61:62") } // ParSRAMLoad(x7895,List(List(b2970, b3696)),List(x8559))
    val x8561 = withCtrl(x8564) { x8560 } // VectorApply(x8560,0)
    val x8562 = withCtrl(x8564) { OpDef(op=MuxOp, inputs=List(x8555, x8561, Const(0))).name("x8562").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:61:37") } // Mux(x8555,x8561,Const(0))
    val x8563 = withCtrl(x8564) { StoreBanks(List(List(x8452_d0_b0)), List(b3695, b3696), x8562).name("x8563").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:61:32") } // ParSRAMStore(x8452,List(List(b3695, b3696)),List(x8562),List(x8559))
    val x8566 = withCtrl(x8640) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x8566").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:64:12") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x8567 = withCtrl(x8640) { Counter(min=Const(0), max=Const(64), step=Const(1), par=1).name("x8567").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:64:12") } // CounterNew(Const(0),Const(64),Const(1),Const(1))
    val x8568 = withCtrl(x8640) { CounterChain(List(x8567,x8566)).name("x8568").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:64:12") } // CounterChainNew(ArrayBuffer(x8567, x8566))
    val x8639 = withCtrl(x8640) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8568).name("x8639").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:64:12") } // UnrolledForeach(List(),x8568,Block(Const(())),ArrayBuffer(List(b3711), List(b3712)),ArrayBuffer(List(b3713), List(b3714)))
    val b3711 = withCtrl(x8639) { CounterIter(x8567, Some(0)).name("b3711") } // b3711
    val b3713 = withCtrl(x8639) { Const(true).name("b3713") } // b3713
    val b3712 = withCtrl(x8639) { CounterIter(x8566, None).name("b3712") } // b3712
    val b3714 = withCtrl(x8639) { Const(true).name("b3714") } // b3714
    val x8569 = withCtrl(x8639) { OpDef(op=BitAnd, inputs=List(b3713, b3714)).name("x8569").srcCtx("UnrollingBase.scala:28:66") } // And(b3713,b3714)
    val x8570 = withCtrl(x8639) { OpDef(op=BitAnd, inputs=List(b2920, b2911)).name("x8570").srcCtx("UnrollingBase.scala:28:66") } // And(b2920,b2911)
    val x8571 = withCtrl(x8639) { OpDef(op=BitAnd, inputs=List(x8569, x8570)).name("x8571").srcCtx("UnrollingBase.scala:28:66") } // And(x8569,x8570)
    val x8572 = withCtrl(x8639) { LoadBanks(List(x8445_d0_b0), List(b3711, b3712)).name("x8572").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:64:12") } // ParSRAMLoad(x8445,List(ArrayBuffer(b3711, b3712)),List(x8571))
    val x8573 = withCtrl(x8639) { x8572 } // VectorApply(x8572,0)
    val x8574 = withCtrl(x8639) { LoadBanks(List(x8446_d0_b0), List(b3711, b3712)).name("x8574").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:64:12") } // ParSRAMLoad(x8446,List(ArrayBuffer(b3711, b3712)),List(x8571))
    val x8575 = withCtrl(x8639) { x8574 } // VectorApply(x8574,0)
    val x8576 = withCtrl(x8639) { LoadBanks(List(x8447_d0_b0), List(b3711, b3712)).name("x8576").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:64:12") } // ParSRAMLoad(x8447,List(ArrayBuffer(b3711, b3712)),List(x8571))
    val x8577 = withCtrl(x8639) { x8576 } // VectorApply(x8576,0)
    val x8578 = withCtrl(x8639) { LoadBanks(List(x8448_d0_b0), List(b3711, b3712)).name("x8578").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:64:12") } // ParSRAMLoad(x8448,List(ArrayBuffer(b3711, b3712)),List(x8571))
    val x8579 = withCtrl(x8639) { x8578 } // VectorApply(x8578,0)
    val x8580 = withCtrl(x8639) { LoadBanks(List(x8449_d0_b0), List(b3711, b3712)).name("x8580").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:64:12") } // ParSRAMLoad(x8449,List(ArrayBuffer(b3711, b3712)),List(x8571))
    val x8581 = withCtrl(x8639) { x8580 } // VectorApply(x8580,0)
    val x8582 = withCtrl(x8639) { LoadBanks(List(x8450_d0_b0), List(b3711, b3712)).name("x8582").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:64:12") } // ParSRAMLoad(x8450,List(ArrayBuffer(b3711, b3712)),List(x8571))
    val x8583 = withCtrl(x8639) { x8582 } // VectorApply(x8582,0)
    val x8584 = withCtrl(x8639) { LoadBanks(List(x8451_d0_b0), List(b3711, b3712)).name("x8584").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:64:12") } // ParSRAMLoad(x8451,List(ArrayBuffer(b3711, b3712)),List(x8571))
    val x8585 = withCtrl(x8639) { x8584 } // VectorApply(x8584,0)
    val x8586 = withCtrl(x8639) { LoadBanks(List(x8452_d0_b0), List(b3711, b3712)).name("x8586").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:64:12") } // ParSRAMLoad(x8452,List(ArrayBuffer(b3711, b3712)),List(x8571))
    val x8587 = withCtrl(x8639) { x8586 } // VectorApply(x8586,0)
    val x8588 = withCtrl(x8639) { LoadBanks(List(x7927_d1_b0), List(b3711, b3712)).name("x8588").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:64:12") } // ParSRAMLoad(x7927,List(ArrayBuffer(b3711, b3712)),List(x8571))
    val x8589 = withCtrl(x8639) { x8588 } // VectorApply(x8588,0)
    val x8590 = withCtrl(x8639) { OpDef(op=BitAnd, inputs=List(b2971, b2920)).name("x8590").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:64:12") } // And(b2971,b2920)
    val x8591 = withCtrl(x8639) { OpDef(op=BitAnd, inputs=List(x8590, b2911)).name("x8591").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:64:12") } // And(x8590,b2911)
    val x8592 = withCtrl(x8639) { OpDef(op=BitAnd, inputs=List(b2972, b2920)).name("x8592").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:64:12") } // And(b2972,b2920)
    val x8593 = withCtrl(x8639) { OpDef(op=BitAnd, inputs=List(x8592, b2911)).name("x8593").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:64:12") } // And(x8592,b2911)
    val x8594 = withCtrl(x8639) { OpDef(op=BitAnd, inputs=List(b2973, b2920)).name("x8594").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:64:12") } // And(b2973,b2920)
    val x8595 = withCtrl(x8639) { OpDef(op=BitAnd, inputs=List(x8594, b2911)).name("x8595").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:64:12") } // And(x8594,b2911)
    val x8596 = withCtrl(x8639) { OpDef(op=BitAnd, inputs=List(b2974, b2920)).name("x8596").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:64:12") } // And(b2974,b2920)
    val x8597 = withCtrl(x8639) { OpDef(op=BitAnd, inputs=List(x8596, b2911)).name("x8597").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:64:12") } // And(x8596,b2911)
    val x8598 = withCtrl(x8639) { OpDef(op=BitAnd, inputs=List(b2975, b2920)).name("x8598").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:64:12") } // And(b2975,b2920)
    val x8599 = withCtrl(x8639) { OpDef(op=BitAnd, inputs=List(x8598, b2911)).name("x8599").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:64:12") } // And(x8598,b2911)
    val x8600 = withCtrl(x8639) { OpDef(op=BitAnd, inputs=List(b2976, b2920)).name("x8600").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:64:12") } // And(b2976,b2920)
    val x8601 = withCtrl(x8639) { OpDef(op=BitAnd, inputs=List(x8600, b2911)).name("x8601").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:64:12") } // And(x8600,b2911)
    val x8602 = withCtrl(x8639) { OpDef(op=BitAnd, inputs=List(b2977, b2920)).name("x8602").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:64:12") } // And(b2977,b2920)
    val x8603 = withCtrl(x8639) { OpDef(op=BitAnd, inputs=List(x8602, b2911)).name("x8603").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:64:12") } // And(x8602,b2911)
    val x8604 = withCtrl(x8639) { OpDef(op=BitAnd, inputs=List(b2978, b2920)).name("x8604").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:64:12") } // And(b2978,b2920)
    val x8605 = withCtrl(x8639) { OpDef(op=BitAnd, inputs=List(x8604, b2911)).name("x8605").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:64:12") } // And(x8604,b2911)
    val x8606 = withCtrl(x8639) { OpDef(op=BitAnd, inputs=List(x8591, x8571)).name("x8606").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:64:12") } // And(x8591,x8571)
    val x8607 = withCtrl(x8639) { OpDef(op=BitAnd, inputs=List(x8593, x8571)).name("x8607").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:64:12") } // And(x8593,x8571)
    val x8608 = withCtrl(x8639) { OpDef(op=BitAnd, inputs=List(x8595, x8571)).name("x8608").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:64:12") } // And(x8595,x8571)
    val x8609 = withCtrl(x8639) { OpDef(op=BitAnd, inputs=List(x8597, x8571)).name("x8609").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:64:12") } // And(x8597,x8571)
    val x8610 = withCtrl(x8639) { OpDef(op=BitAnd, inputs=List(x8599, x8571)).name("x8610").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:64:12") } // And(x8599,x8571)
    val x8611 = withCtrl(x8639) { OpDef(op=BitAnd, inputs=List(x8601, x8571)).name("x8611").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:64:12") } // And(x8601,x8571)
    val x8612 = withCtrl(x8639) { OpDef(op=BitAnd, inputs=List(x8603, x8571)).name("x8612").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:64:12") } // And(x8603,x8571)
    val x8613 = withCtrl(x8639) { OpDef(op=BitAnd, inputs=List(x8605, x8571)).name("x8613").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:64:12") } // And(x8605,x8571)
    val x8614 = withCtrl(x8639) { OpDef(op=FixAdd, inputs=List(x8573, x8575)).name("x8614").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:64:14") } // FixAdd(x8573,x8575)
    val x8615 = withCtrl(x8639) { OpDef(op=MuxOp, inputs=List(x8607, x8614, x8573)).name("x8615").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:64:12") } // Mux(x8607,x8614,x8573)
    val x8616 = withCtrl(x8639) { OpDef(op=BitOr, inputs=List(x8606, x8607)).name("x8616").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:64:12") } // Or(x8606,x8607)
    val x8617 = withCtrl(x8639) { OpDef(op=FixAdd, inputs=List(x8577, x8579)).name("x8617").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:64:14") } // FixAdd(x8577,x8579)
    val x8618 = withCtrl(x8639) { OpDef(op=MuxOp, inputs=List(x8609, x8617, x8577)).name("x8618").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:64:12") } // Mux(x8609,x8617,x8577)
    val x8619 = withCtrl(x8639) { OpDef(op=BitOr, inputs=List(x8608, x8609)).name("x8619").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:64:12") } // Or(x8608,x8609)
    val x8620 = withCtrl(x8639) { OpDef(op=FixAdd, inputs=List(x8581, x8583)).name("x8620").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:64:14") } // FixAdd(x8581,x8583)
    val x8621 = withCtrl(x8639) { OpDef(op=MuxOp, inputs=List(x8611, x8620, x8581)).name("x8621").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:64:12") } // Mux(x8611,x8620,x8581)
    val x8622 = withCtrl(x8639) { OpDef(op=BitOr, inputs=List(x8610, x8611)).name("x8622").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:64:12") } // Or(x8610,x8611)
    val x8623 = withCtrl(x8639) { OpDef(op=FixAdd, inputs=List(x8585, x8587)).name("x8623").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:64:14") } // FixAdd(x8585,x8587)
    val x8624 = withCtrl(x8639) { OpDef(op=MuxOp, inputs=List(x8613, x8623, x8585)).name("x8624").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:64:12") } // Mux(x8613,x8623,x8585)
    val x8625 = withCtrl(x8639) { OpDef(op=BitOr, inputs=List(x8612, x8613)).name("x8625").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:64:12") } // Or(x8612,x8613)
    val x8626 = withCtrl(x8639) { OpDef(op=FixAdd, inputs=List(x8615, x8618)).name("x8626").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:64:14") } // FixAdd(x8615,x8618)
    val x8627 = withCtrl(x8639) { OpDef(op=MuxOp, inputs=List(x8619, x8626, x8615)).name("x8627").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:64:12") } // Mux(x8619,x8626,x8615)
    val x8628 = withCtrl(x8639) { OpDef(op=BitOr, inputs=List(x8616, x8619)).name("x8628").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:64:12") } // Or(x8616,x8619)
    val x8629 = withCtrl(x8639) { OpDef(op=FixAdd, inputs=List(x8621, x8624)).name("x8629").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:64:14") } // FixAdd(x8621,x8624)
    val x8630 = withCtrl(x8639) { OpDef(op=MuxOp, inputs=List(x8625, x8629, x8621)).name("x8630").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:64:12") } // Mux(x8625,x8629,x8621)
    val x8631 = withCtrl(x8639) { OpDef(op=BitOr, inputs=List(x8622, x8625)).name("x8631").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:64:12") } // Or(x8622,x8625)
    val x8632 = withCtrl(x8639) { OpDef(op=FixAdd, inputs=List(x8627, x8630)).name("x8632").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:64:14") } // FixAdd(x8627,x8630)
    val x8633 = withCtrl(x8639) { OpDef(op=MuxOp, inputs=List(x8631, x8632, x8627)).name("x8633").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:64:12") } // Mux(x8631,x8632,x8627)
    val x8634 = withCtrl(x8639) { OpDef(op=BitOr, inputs=List(x8628, x8631)).name("x8634").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:64:12") } // Or(x8628,x8631)
    val x8635 = withCtrl(x8639) { OpDef(op=FixEql, inputs=List(b2963, Const(0))).name("x8635").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:64:12") } // FixEql(b2963,Const(0))
    val x8636 = withCtrl(x8639) { OpDef(op=FixAdd, inputs=List(x8633, x8589)).name("x8636").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:64:14") } // FixAdd(x8633,x8589)
    val x8637 = withCtrl(x8639) { OpDef(op=MuxOp, inputs=List(x8635, x8633, x8636)).name("x8637").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:64:12") } // Mux(x8635,x8633,x8636)
    val x8638 = withCtrl(x8639) { StoreBanks(List(List(x7927_d0_b0), List(x7927_d1_b0)), List(b3711, b3712), x8637).name("x8638").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:64:12") } // ParSRAMStore(x7927,List(ArrayBuffer(b3711, b3712)),List(x8637),List(x8571))
    antiDepsOf(x8638)=List(x8588)
    val x8641 = withCtrl(x8657) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x8641").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:65:10") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x8642 = withCtrl(x8657) { Counter(min=Const(0), max=Const(64), step=Const(1), par=1).name("x8642").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:65:10") } // CounterNew(Const(0),Const(64),Const(1),Const(1))
    val x8643 = withCtrl(x8657) { CounterChain(List(x8642,x8641)).name("x8643").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:65:10") } // CounterChainNew(ArrayBuffer(x8642, x8641))
    val x8656 = withCtrl(x8657) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8643).name("x8656").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:65:10") } // UnrolledForeach(List(),x8643,Block(Const(())),ArrayBuffer(List(b3787), List(b3788)),ArrayBuffer(List(b3789), List(b3790)))
    val b3787 = withCtrl(x8656) { CounterIter(x8642, Some(0)).name("b3787") } // b3787
    val b3789 = withCtrl(x8656) { Const(true).name("b3789") } // b3789
    val b3788 = withCtrl(x8656) { CounterIter(x8641, None).name("b3788") } // b3788
    val b3790 = withCtrl(x8656) { Const(true).name("b3790") } // b3790
    val x8644 = withCtrl(x8656) { OpDef(op=BitAnd, inputs=List(b3789, b3790)).name("x8644").srcCtx("UnrollingBase.scala:28:66") } // And(b3789,b3790)
    val x8645 = withCtrl(x8656) { OpDef(op=BitAnd, inputs=List(x8644, b2911)).name("x8645").srcCtx("UnrollingBase.scala:28:66") } // And(x8644,b2911)
    val x8646 = withCtrl(x8656) { LoadBanks(List(x7927_d0_b0), List(b3787, b3788)).name("x8646").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:65:10") } // ParSRAMLoad(x7927,List(ArrayBuffer(b3787, b3788)),List(x8645))
    val x8647 = withCtrl(x8656) { x8646 } // VectorApply(x8646,0)
    val x8648 = withCtrl(x8656) { LoadBanks(List(x7891_d2_b0), List(b3787, b3788)).name("x8648").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:65:10") } // ParSRAMLoad(x7891,List(ArrayBuffer(b3787, b3788)),List(x8645))
    val x8649 = withCtrl(x8656) { x8648 } // VectorApply(x8648,0)
    val x8650 = withCtrl(x8656) { OpDef(op=BitAnd, inputs=List(b2920, b2911)).name("x8650").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:65:10") } // And(b2920,b2911)
    val x8651 = withCtrl(x8656) { OpDef(op=BitAnd, inputs=List(x8650, x8645)).name("x8651").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:65:10") } // And(x8650,x8645)
    val x8652 = withCtrl(x8656) { OpDef(op=FixEql, inputs=List(b2919, Const(0))).name("x8652").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:65:10") } // FixEql(b2919,Const(0))
    val x8653 = withCtrl(x8656) { OpDef(op=FixAdd, inputs=List(x8647, x8649)).name("x8653").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:65:12") } // FixAdd(x8647,x8649)
    val x8654 = withCtrl(x8656) { OpDef(op=MuxOp, inputs=List(x8652, x8647, x8653)).name("x8654").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:65:10") } // Mux(x8652,x8647,x8653)
    val x8655 = withCtrl(x8656) { StoreBanks(List(List(x7891_d0_b0), List(x7891_d1_b0), List(x7891_d2_b0)), List(b3787, b3788), x8654).name("x8655").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:65:10") } // ParSRAMStore(x7891,List(ArrayBuffer(b3787, b3788)),List(x8654),List(x8645))
    antiDepsOf(x8655)=List(x8648)
    val x8658 = withCtrl(x8679) { Counter(min=Const(0), max=Const(64), step=Const(1), par=1).name("x8658").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:67:24") } // CounterNew(Const(0),Const(64),Const(1),Const(1))
    val x8659 = withCtrl(x8679) { CounterChain(List(x8658)).name("x8659").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:67:32") } // CounterChainNew(List(x8658))
    val x8678 = withCtrl(x8679) { LoopController(style=MetaPipe, level=OuterControl, cchain=x8659).name("x8678").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:67:32") } // UnrolledForeach(List(b2911),x8659,Block(Const(())),List(List(b3807)),List(List(b3808)))
    val b3807 = withCtrl(x8678) { CounterIter(x8658, Some(0)).name("b3807") } // b3807
    val b3808 = withCtrl(x8678) { Const(true).name("b3808") } // b3808
    val x8660 = withCtrl(x8678) { Reg(init=Some(0)).name("x8660").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:68:33:centCount") } // x8660 = RegNew(Const(0))
    isAccum(x8660) = false
    bufferDepthOf(x8660) = 2
    val x8665 = withCtrl(x8678) { UnitController(style=SeqPipe, level=InnerControl).name("x8665").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:69:16") } // UnitPipe(List(b3808, b2911),Block(x8664))
    val x8661 = withCtrl(x8665) { OpDef(op=BitAnd, inputs=List(b3808, b2911)).name("x8661").srcCtx("UnrollingBase.scala:28:66") } // And(b3808,b2911)
    val x8662 = withCtrl(x8665) { LoadBanks(List(x7891_d1_b0), List(b3807, Const(63))).name("x8662").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:70:38") } // SRAMLoad(x7891,ArrayBuffer(Const(64), Const(64)),List(b3807, Const(63)),Const(0),x8661)
    val x8663 = withCtrl(x8665) { OpDef(op=FixMax, inputs=List(x8662, Const(1))).name("x8663").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:70:29") } // Max(x8662,Const(1))
    val x8664_x8660 = withCtrl(x8665) { WriteMem(x8660, x8663).name("x8664_x8660").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:70:23") } // RegWrite(x8660,x8663,x8661)
    val x8666 = withCtrl(x8678) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x8666").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:72:21") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x8667 = withCtrl(x8678) { CounterChain(List(x8666)).name("x8667").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:72:28") } // CounterChainNew(List(x8666))
    val x8677 = withCtrl(x8678) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8667).name("x8677").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:72:28") } // UnrolledForeach(List(b3808, b2911),x8667,Block(Const(())),List(List(b3817)),List(List(b3818)))
    val b3817 = withCtrl(x8677) { CounterIter(x8666, None).name("b3817") } // b3817
    val b3818 = withCtrl(x8677) { Const(true).name("b3818") } // b3818
    val x8668 = withCtrl(x8677) { ReadMem(x8660).name("x8668").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:73:40") } // RegRead(x8660)
    val x8669 = withCtrl(x8677) { OpDef(op=FixEql, inputs=List(x8668, Const(0))).name("x8669").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:73:40") } // FixEql(x8668,Const(0))
    val x8670 = withCtrl(x8677) { OpDef(op=BitAnd, inputs=List(b3818, b3808)).name("x8670").srcCtx("UnrollingBase.scala:28:66") } // And(b3818,b3808)
    val x8671 = withCtrl(x8677) { OpDef(op=BitAnd, inputs=List(x8670, b2911)).name("x8671").srcCtx("UnrollingBase.scala:28:66") } // And(x8670,b2911)
    val x8672 = withCtrl(x8677) { LoadBanks(List(x7891_d0_b0), List(b3807, b3817)).name("x8672").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:73:69") } // ParSRAMLoad(x7891,List(List(b3807, b3817)),List(x8671))
    val x8673 = withCtrl(x8677) { x8672 } // VectorApply(x8672,0)
    val x8674 = withCtrl(x8677) { OpDef(op=FixDiv, inputs=List(x8673, x8668)).name("x8674").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:73:76") } // FixDiv(x8673,x8668)
    val x8675 = withCtrl(x8677) { OpDef(op=MuxOp, inputs=List(x8669, Const(0), x8674)).name("x8675").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:73:29") } // Mux(x8669,Const(0),x8674)
    val x8676 = withCtrl(x8677) { StoreBanks(List(List(x7863_d0_b0), List(x7863_d5_b0), List(x7863_d1_b0), List(x7863_d6_b0), List(x7863_d2_b0), List(x7863_d7_b0), List(x7863_d3_b0), List(x7863_d8_b0), List(x7863_d4_b0)), List(b3807, b3817), x8675).name("x8676").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:73:24") } // ParSRAMStore(x7863,List(List(b3807, b3817)),List(x8675),List(x8671))
    val x8680 = withCtrl(x8708) { Counter(min=Const(0), max=Const(64), step=Const(1), par=1).name("x8680").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:78:33") } // CounterNew(Const(0),Const(64),Const(1),Const(1))
    val x8681 = withCtrl(x8708) { CounterChain(List(x8680)).name("x8681").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:78:33") } // CounterChainNew(List(x8680))
    val x8707 = withCtrl(x8708) { LoopController(style=StreamPipe, level=OuterControl, cchain=x8681).name("x8707").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:78:33") } // UnrolledForeach(List(Const(true)),x8681,Block(Const(())),List(List(b3833)),List(List(b3834)))
    val b3833 = withCtrl(x8707) { CounterIter(x8680, Some(0)).name("b3833") } // b3833
    val b3834 = withCtrl(x8707) { Const(true).name("b3834") } // b3834
    val b8820 = withCtrl(x8707) { StreamOut(field="offset").name("b8820").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:78:33") } // x8682 = StreamOutNew(BurstCmdBus)
    isAccum(b8820) = false
    bufferDepthOf(b8820) = 1
    val b8821 = withCtrl(x8707) { StreamOut(field="size").name("b8821").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:78:33") } // x8682 = StreamOutNew(BurstCmdBus)
    isAccum(b8821) = false
    bufferDepthOf(b8821) = 1
    val x8683 = withCtrl(x8707) { StreamOut(field="data").name("x8683").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:78:33") } // x8683 = StreamOutNew(BurstFullDataBus())
    isAccum(x8683) = false
    bufferDepthOf(x8683) = 1
    val x8684 = withCtrl(x8707) { StreamIn(field="ack").name("x8684").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:78:33") } // x8684 = StreamInNew(BurstAckBus)
    isAccum(x8684) = false
    bufferDepthOf(x8684) = 1
    val x8695 = withCtrl(x8707) { UnitController(style=SeqPipe, level=InnerControl).name("x8695").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:78:33") } // UnitPipe(List(b3834),Block(x8694))
    val x8685 = withCtrl(x8695) { b3833 } // FixConvert(b3833,TRUE,_32,_0) (Same Type. No op)
    val x8686 = withCtrl(x8695) { OpDef(op=FixSla, inputs=List(x8685, Const(6))).name("x8686").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:78:33") } // FixLsh(x8685,Const(6))
    val x8687 = withCtrl(x8695) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x8688 = withCtrl(x8695) { OpDef(op=FixAdd, inputs=List(x8686, x8687)).name("x8688").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:78:33") } // FixAdd(x8686,x8687)
    val x8689 = withCtrl(x8695) { OpDef(op=FixSla, inputs=List(x8688, Const(2))).name("x8689").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:78:33") } // FixLsh(x8688,Const(2))
    val x8690 = withCtrl(x8695) { x8689 } // FixConvert(x8689,TRUE,_64,_0)
    val x8691 = withCtrl(x8695) { DramAddress(x7859).name("x8691").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:78:33") } // GetDRAMAddress(x7859)
    val x8693_x8692 = withCtrl(x8695) { OpDef(op=FixAdd, inputs=List(x8690, x8691)).name("x8693_x8692").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:78:33") } // FixAdd(x8690,x8691)
    // x8693 = SimpleStruct(ArrayBuffer((offset,x8692), (size,Const(256)), (isLoad,Const(false))))
    val x8694_b8822_b8820 = withCtrl(x8695) { WriteMem(b8820, x8693_x8692).name("x8694_b8822_b8820").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:78:33") } // StreamWrite(x8682,x8693,b3834)
    val x8694_b8823_b8821 = withCtrl(x8695) { WriteMem(b8821, Const(256)).name("x8694_b8823_b8821").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:78:33") } // StreamWrite(x8682,x8693,b3834)
    val x8696 = withCtrl(x8707) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x8696").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:78:33") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x8697 = withCtrl(x8707) { CounterChain(List(x8696)).name("x8697").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:78:33") } // CounterChainNew(List(x8696))
    val x8703 = withCtrl(x8707) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8697).name("x8703").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:78:33") } // UnrolledForeach(List(b3834),x8697,Block(Const(())),List(List(b3851)),List(List(b3852)))
    val b3851 = withCtrl(x8703) { CounterIter(x8696, None).name("b3851") } // b3851
    val b3852 = withCtrl(x8703) { Const(true).name("b3852") } // b3852
    val x8698 = withCtrl(x8703) { OpDef(op=BitAnd, inputs=List(b3852, b3834)).name("x8698").srcCtx("UnrollingBase.scala:28:66") } // And(b3852,b3834)
    val x8699 = withCtrl(x8703) { LoadBanks(List(x7863_d0_b0), List(b3833, b3851)).name("x8699").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:78:33") } // ParSRAMLoad(x7863,List(List(b3833, b3851)),List(x8698))
    val x8701_x8700 = withCtrl(x8703) { x8699 } // VectorApply(x8699,0)
    // x8701 = SimpleStruct(ArrayBuffer((_1,x8700), (_2,Const(true))))
    val x8702_x8702_x8683 = withCtrl(x8703) { WriteMem(x8683, x8701_x8700).name("x8702_x8702_x8683").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:78:33") } // ParStreamWrite(x8683,List(x8701),List(x8698))
    val x8704 = withCtrl(x8707) { FringeDenseStore(dram=List(x7859), cmdStream=List(b8820, b8821), dataStream=List(x8683), ackStream=List(x8684)).name("x8704").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:78:33") } // FringeDenseStore(x7859,x8682,x8683,x8684)
    val x8706 = withCtrl(x8707) { UnitController(style=SeqPipe, level=InnerControl).name("x8706").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:78:33") } // UnitPipe(List(b3834),Block(Const(())))
    val x8705_x8705 = withCtrl(x8706) { ReadMem(x8684).name("x8705_x8705").srcCtx("Kmeans__I_2_K_64_D_64_N_8192_ts_1024_op_1_mp1_8_mp2_1_mp3_1.scala:78:33") } // StreamRead(x8684,b3834)
    }; split2
    }; split1
    
  }
}
