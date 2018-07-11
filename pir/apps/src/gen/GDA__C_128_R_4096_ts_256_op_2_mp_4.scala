import pir._
import pir.node._
import arch._
import prism.enums._

object GDA__C_128_R_4096_ts_256_op_2_mp_4 extends PIRApp {
  def main(implicit design:PIRDesign) = {
    import design.pirmeta._
    val x4902 = ArgIn(init=0).name("x4902").ctrl(top).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:22:18:R") // ArgInNew(Const(0))
    isAccum(x4902) = false
    bufferDepthOf(x4902) = 1
    boundOf(x4902) = 1024
    // x4904.deps=List(x4903, x4902)
    val x4904 = ReadMem(x4902).name("x4904").ctrl(top).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:25:21") // RegRead(x4902)
    val x4905 = DRAM(dims=List(x4904, Const(128))).name("x4905").ctrl(top).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:25:20:x") // x4905 = DRAMNew(ArrayBuffer(x4904, Const(128)),Const(0))
    // x4906.deps=List(x4903, x4902)
    val x4906 = ReadMem(x4902).name("x4906").ctrl(top).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:26:23") // RegRead(x4902)
    val x4907 = DRAM(dims=List(x4906)).name("x4907").ctrl(top).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:26:22:y") // x4907 = DRAMNew(ArrayBuffer(x4906),Const(0))
    val x4908 = DRAM(dims=List(Const(128))).name("x4908").ctrl(top).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:27:22:mu0") // x4908 = DRAMNew(ArrayBuffer(Const(128)),Const(0))
    val x4909 = DRAM(dims=List(Const(128))).name("x4909").ctrl(top).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:28:22:mu1") // x4909 = DRAMNew(ArrayBuffer(Const(128)),Const(0))
    val x4910 = DRAM(dims=List(Const(128), Const(128))).name("x4910").ctrl(top).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:29:24:sigma") // x4910 = DRAMNew(ArrayBuffer(Const(128), Const(128)),Const(0))
    val x5437 = UnitController(style=SeqPipe, level=OuterControl).name("x5437").ctrl(top).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:36:11") // Hwblock(Block(Const(())),false)
    val x4915_d0_b0 = SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x4915_d0_b0").ctrl(x5437).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:37:28:mu0Tile") // x4915 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x4915_d0_b0) = false
    bufferDepthOf(x4915_d0_b0) = 1
    staticDimsOf(x4915_d0_b0) = List(128)
    val x4915_d1_b0 = SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x4915_d1_b0").ctrl(x5437).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:37:28:mu0Tile") // x4915 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x4915_d1_b0) = false
    bufferDepthOf(x4915_d1_b0) = 1
    staticDimsOf(x4915_d1_b0) = List(128)
    val x4915_d2_b0 = SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x4915_d2_b0").ctrl(x5437).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:37:28:mu0Tile") // x4915 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x4915_d2_b0) = false
    bufferDepthOf(x4915_d2_b0) = 1
    staticDimsOf(x4915_d2_b0) = List(128)
    val x4915_d3_b0 = SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x4915_d3_b0").ctrl(x5437).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:37:28:mu0Tile") // x4915 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x4915_d3_b0) = false
    bufferDepthOf(x4915_d3_b0) = 1
    staticDimsOf(x4915_d3_b0) = List(128)
    val x4915_d4_b0 = SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x4915_d4_b0").ctrl(x5437).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:37:28:mu0Tile") // x4915 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x4915_d4_b0) = false
    bufferDepthOf(x4915_d4_b0) = 1
    staticDimsOf(x4915_d4_b0) = List(128)
    val x4915_d5_b0 = SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x4915_d5_b0").ctrl(x5437).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:37:28:mu0Tile") // x4915 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x4915_d5_b0) = false
    bufferDepthOf(x4915_d5_b0) = 1
    staticDimsOf(x4915_d5_b0) = List(128)
    val x4915_d6_b0 = SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x4915_d6_b0").ctrl(x5437).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:37:28:mu0Tile") // x4915 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x4915_d6_b0) = false
    bufferDepthOf(x4915_d6_b0) = 1
    staticDimsOf(x4915_d6_b0) = List(128)
    val x4915_d7_b0 = SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x4915_d7_b0").ctrl(x5437).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:37:28:mu0Tile") // x4915 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x4915_d7_b0) = false
    bufferDepthOf(x4915_d7_b0) = 1
    staticDimsOf(x4915_d7_b0) = List(128)
    val x4916_d0_b0 = SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x4916_d0_b0").ctrl(x5437).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:38:28:mu1Tile") // x4916 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x4916_d0_b0) = false
    bufferDepthOf(x4916_d0_b0) = 1
    staticDimsOf(x4916_d0_b0) = List(128)
    val x4916_d1_b0 = SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x4916_d1_b0").ctrl(x5437).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:38:28:mu1Tile") // x4916 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x4916_d1_b0) = false
    bufferDepthOf(x4916_d1_b0) = 1
    staticDimsOf(x4916_d1_b0) = List(128)
    val x4916_d2_b0 = SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x4916_d2_b0").ctrl(x5437).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:38:28:mu1Tile") // x4916 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x4916_d2_b0) = false
    bufferDepthOf(x4916_d2_b0) = 1
    staticDimsOf(x4916_d2_b0) = List(128)
    val x4916_d3_b0 = SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x4916_d3_b0").ctrl(x5437).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:38:28:mu1Tile") // x4916 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x4916_d3_b0) = false
    bufferDepthOf(x4916_d3_b0) = 1
    staticDimsOf(x4916_d3_b0) = List(128)
    val x4916_d4_b0 = SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x4916_d4_b0").ctrl(x5437).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:38:28:mu1Tile") // x4916 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x4916_d4_b0) = false
    bufferDepthOf(x4916_d4_b0) = 1
    staticDimsOf(x4916_d4_b0) = List(128)
    val x4916_d5_b0 = SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x4916_d5_b0").ctrl(x5437).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:38:28:mu1Tile") // x4916 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x4916_d5_b0) = false
    bufferDepthOf(x4916_d5_b0) = 1
    staticDimsOf(x4916_d5_b0) = List(128)
    val x4916_d6_b0 = SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x4916_d6_b0").ctrl(x5437).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:38:28:mu1Tile") // x4916 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x4916_d6_b0) = false
    bufferDepthOf(x4916_d6_b0) = 1
    staticDimsOf(x4916_d6_b0) = List(128)
    val x4916_d7_b0 = SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x4916_d7_b0").ctrl(x5437).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:38:28:mu1Tile") // x4916 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x4916_d7_b0) = false
    bufferDepthOf(x4916_d7_b0) = 1
    staticDimsOf(x4916_d7_b0) = List(128)
    val x4953 = UnitController(style=ForkJoin, level=OuterControl).name("x4953").ctrl(x5437).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:39:16") // ParallelPipe(List(Const(true)),Block(Const(())))
    val x4934 = UnitController(style=StreamPipe, level=OuterControl).name("x4934").ctrl(x4953).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:40:17") // UnitPipe(List(Const(true)),Block(Const(())))
    val b5489 = StreamOut(field="offset").name("b5489").ctrl(x4934).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:40:17") // x4917 = StreamOutNew(BurstCmdBus)
    isAccum(b5489) = false
    bufferDepthOf(b5489) = 1
    val b5490 = StreamOut(field="size").name("b5490").ctrl(x4934).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:40:17") // x4917 = StreamOutNew(BurstCmdBus)
    isAccum(b5490) = false
    bufferDepthOf(b5490) = 1
    val x4918 = StreamIn(field="data").name("x4918").ctrl(x4934).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:40:17") // x4918 = StreamInNew(BurstDataBus())
    isAccum(x4918) = false
    bufferDepthOf(x4918) = 1
    val x4926 = UnitController(style=SeqPipe, level=InnerControl).name("x4926").ctrl(x4934).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:40:17") // UnitPipe(List(Const(true)),Block(x4925))
    val x4919 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x4920 = OpDef(op=FixSla, inputs=List(x4919, Const(2))).name("x4920").ctrl(x4926).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:40:17") // FixLsh(x4919,Const(2))
    val x4921 = x4920 // FixConvert(x4920,TRUE,_64,_0)
    val x4922 = DramAddress(x4908).name("x4922").ctrl(x4926).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:40:17") // GetDRAMAddress(x4908)
    val x4924_x4923 = OpDef(op=FixAdd, inputs=List(x4921, x4922)).name("x4924_x4923").ctrl(x4926).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:40:17") // FixAdd(x4921,x4922)
    // x4924 = SimpleStruct(ArrayBuffer((offset,x4923), (size,Const(512)), (isLoad,Const(true))))
    val x4925_b5491_b5489 = WriteMem(b5489, x4924_x4923).name("x4925_b5491_b5489").ctrl(x4926).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:40:17") // StreamWrite(x4917,x4924,Const(true))
    val x4925_b5492_b5490 = WriteMem(b5490, Const(512)).name("x4925_b5492_b5490").ctrl(x4926).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:40:17") // StreamWrite(x4917,x4924,Const(true))
    val x4927 = FringeDenseLoad(dram=List(x4908), cmdStream=List(b5489, b5490), dataStream=List(x4918)).name("x4927").ctrl(x4934).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:40:17") // FringeDenseLoad(x4908,x4917,x4918)
    val x4928 = Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x4928").ctrl(x4934).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:40:17") // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x4929 = CounterChain(List(x4928)).name("x4929").ctrl(x4934).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:40:17") // CounterChainNew(List(x4928))
    val x4933 = LoopController(style=InnerPipe, level=InnerControl, cchain=x4929).name("x4933").ctrl(x4934).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:40:17") // UnrolledForeach(List(Const(true)),x4929,Block(Const(())),List(List(b1872)),List(List(b1873)))
    val b1872 = CounterIter(x4928, None).name("b1872").ctrl(x4933) // b1872
    val b1873 = Const(true).name("b1873").ctrl(x4933) // b1873
    val x4930_x4930 = ReadMem(x4918).name("x4930_x4930").ctrl(x4933).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:40:17") // ParStreamRead(x4918,List(b1873))
    val x4931_x4931 = x4930_x4930 // x4931 = VectorApply(x4930,0)
    val x4932 = StoreBanks(List(x4915_d0_b0, x4915_d5_b0, x4915_d1_b0, x4915_d6_b0, x4915_d2_b0, x4915_d7_b0, x4915_d3_b0, x4915_d4_b0), List(b1872), x4931_x4931).name("x4932").ctrl(x4933).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:40:17") // ParSRAMStore(x4915,List(List(b1872)),List(x4931),List(b1873))
    val x4952 = UnitController(style=StreamPipe, level=OuterControl).name("x4952").ctrl(x4953).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:41:17") // UnitPipe(List(Const(true)),Block(Const(())))
    val b5493 = StreamOut(field="offset").name("b5493").ctrl(x4952).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:41:17") // x4935 = StreamOutNew(BurstCmdBus)
    isAccum(b5493) = false
    bufferDepthOf(b5493) = 1
    val b5494 = StreamOut(field="size").name("b5494").ctrl(x4952).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:41:17") // x4935 = StreamOutNew(BurstCmdBus)
    isAccum(b5494) = false
    bufferDepthOf(b5494) = 1
    val x4936 = StreamIn(field="data").name("x4936").ctrl(x4952).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:41:17") // x4936 = StreamInNew(BurstDataBus())
    isAccum(x4936) = false
    bufferDepthOf(x4936) = 1
    val x4944 = UnitController(style=SeqPipe, level=InnerControl).name("x4944").ctrl(x4952).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:41:17") // UnitPipe(List(Const(true)),Block(x4943))
    val x4937 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x4938 = OpDef(op=FixSla, inputs=List(x4937, Const(2))).name("x4938").ctrl(x4944).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:41:17") // FixLsh(x4937,Const(2))
    val x4939 = x4938 // FixConvert(x4938,TRUE,_64,_0)
    val x4940 = DramAddress(x4909).name("x4940").ctrl(x4944).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:41:17") // GetDRAMAddress(x4909)
    val x4942_x4941 = OpDef(op=FixAdd, inputs=List(x4939, x4940)).name("x4942_x4941").ctrl(x4944).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:41:17") // FixAdd(x4939,x4940)
    // x4942 = SimpleStruct(ArrayBuffer((offset,x4941), (size,Const(512)), (isLoad,Const(true))))
    val x4943_b5495_b5493 = WriteMem(b5493, x4942_x4941).name("x4943_b5495_b5493").ctrl(x4944).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:41:17") // StreamWrite(x4935,x4942,Const(true))
    val x4943_b5496_b5494 = WriteMem(b5494, Const(512)).name("x4943_b5496_b5494").ctrl(x4944).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:41:17") // StreamWrite(x4935,x4942,Const(true))
    val x4945 = FringeDenseLoad(dram=List(x4909), cmdStream=List(b5493, b5494), dataStream=List(x4936)).name("x4945").ctrl(x4952).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:41:17") // FringeDenseLoad(x4909,x4935,x4936)
    val x4946 = Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x4946").ctrl(x4952).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:41:17") // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x4947 = CounterChain(List(x4946)).name("x4947").ctrl(x4952).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:41:17") // CounterChainNew(List(x4946))
    val x4951 = LoopController(style=InnerPipe, level=InnerControl, cchain=x4947).name("x4951").ctrl(x4952).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:41:17") // UnrolledForeach(List(Const(true)),x4947,Block(Const(())),List(List(b1892)),List(List(b1893)))
    val b1892 = CounterIter(x4946, None).name("b1892").ctrl(x4951) // b1892
    val b1893 = Const(true).name("b1893").ctrl(x4951) // b1893
    val x4948_x4948 = ReadMem(x4936).name("x4948_x4948").ctrl(x4951).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:41:17") // ParStreamRead(x4936,List(b1893))
    val x4949_x4949 = x4948_x4948 // x4949 = VectorApply(x4948,0)
    val x4950 = StoreBanks(List(x4916_d0_b0, x4916_d5_b0, x4916_d1_b0, x4916_d6_b0, x4916_d2_b0, x4916_d7_b0, x4916_d3_b0, x4916_d4_b0), List(b1892), x4949_x4949).name("x4950").ctrl(x4951).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:41:17") // ParSRAMStore(x4916,List(List(b1892)),List(x4949),List(b1893))
    val x4954_d0_b0 = SRAM(size=16384, banking=Strided(banks=16, stride=1)).name("x4954_d0_b0").ctrl(x5437).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:44:29:sigmaOut") // x4954 = SRAMNew(ArrayBuffer(Const(128), Const(128)))
    isAccum(x4954_d0_b0) = false
    bufferDepthOf(x4954_d0_b0) = 1
    staticDimsOf(x4954_d0_b0) = List(128, 128)
    val x4954_d1_b0 = SRAM(size=16384, banking=Strided(banks=1, stride=128)).name("x4954_d1_b0").ctrl(x5437).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:44:29:sigmaOut") // x4954 = SRAMNew(ArrayBuffer(Const(128), Const(128)))
    isAccum(x4954_d1_b0) = true
    bufferDepthOf(x4954_d1_b0) = 1
    staticDimsOf(x4954_d1_b0) = List(128, 128)
    val x4955 = ReadMem(x4902).name("x4955").ctrl(x5437).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:46:27") // RegRead(x4902)
    val x4956 = Counter(min=Const(0), max=x4955, step=Const(256), par=2).name("x4956").ctrl(x5437).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:46:35") // CounterNew(Const(0),x4955,Const(256),Const(2))
    val x4957 = CounterChain(List(x4956)).name("x4957").ctrl(x5437).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:68:8") // CounterChainNew(List(x4956))
    val x5408 = LoopController(style=MetaPipe, level=OuterControl, cchain=x4957).name("x5408").ctrl(x5437).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:68:8") // UnrolledReduce(List(Const(true)),x4957,x4954,Block((x4954) => Const(())),List(List(b1907, b1908)),List(List(b1909, b1910)))
    val b1907 = CounterIter(x4956, Some(0)).name("b1907").ctrl(x5408) // b1907
    val b1909 = Const(true).name("b1909").ctrl(x5408) // b1909
    val b1908 = CounterIter(x4956, Some(1)).name("b1908").ctrl(x5408) // b1908
    val b1910 = Const(true).name("b1910").ctrl(x5408) // b1910
    val x4958_d0_b0 = SRAM(size=256, banking=Strided(banks=16, stride=1)).name("x4958_d0_b0").ctrl(x5408).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:47:33:gdaYtile") // x4958 = SRAMNew(ArrayBuffer(Const(256)))
    isAccum(x4958_d0_b0) = false
    bufferDepthOf(x4958_d0_b0) = 2
    staticDimsOf(x4958_d0_b0) = List(256)
    val x4959_d0_b0 = SRAM(size=256, banking=Strided(banks=16, stride=1)).name("x4959_d0_b0").ctrl(x5408).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:47:33:gdaYtile") // x4959 = SRAMNew(ArrayBuffer(Const(256)))
    isAccum(x4959_d0_b0) = false
    bufferDepthOf(x4959_d0_b0) = 2
    staticDimsOf(x4959_d0_b0) = List(256)
    val x4960_d0_b0 = SRAM(size=8192, banking=Strided(banks=16, stride=1)).name("x4960_d0_b0").ctrl(x5408).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:48:31:gdaXtile") // x4960 = SRAMNew(ArrayBuffer(Const(256), Const(128)))
    isAccum(x4960_d0_b0) = false
    bufferDepthOf(x4960_d0_b0) = 2
    staticDimsOf(x4960_d0_b0) = List(256, 128)
    val x4960_d0_b1 = SRAM(size=8192, banking=Strided(banks=16, stride=1)).name("x4960_d0_b1").ctrl(x5408).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:48:31:gdaXtile") // x4960 = SRAMNew(ArrayBuffer(Const(256), Const(128)))
    isAccum(x4960_d0_b1) = false
    bufferDepthOf(x4960_d0_b1) = 2
    staticDimsOf(x4960_d0_b1) = List(256, 128)
    val x4960_d0_b2 = SRAM(size=8192, banking=Strided(banks=16, stride=1)).name("x4960_d0_b2").ctrl(x5408).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:48:31:gdaXtile") // x4960 = SRAMNew(ArrayBuffer(Const(256), Const(128)))
    isAccum(x4960_d0_b2) = false
    bufferDepthOf(x4960_d0_b2) = 2
    staticDimsOf(x4960_d0_b2) = List(256, 128)
    val x4960_d0_b3 = SRAM(size=8192, banking=Strided(banks=16, stride=1)).name("x4960_d0_b3").ctrl(x5408).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:48:31:gdaXtile") // x4960 = SRAMNew(ArrayBuffer(Const(256), Const(128)))
    isAccum(x4960_d0_b3) = false
    bufferDepthOf(x4960_d0_b3) = 2
    staticDimsOf(x4960_d0_b3) = List(256, 128)
    val x4961_d0_b0 = SRAM(size=8192, banking=Strided(banks=16, stride=1)).name("x4961_d0_b0").ctrl(x5408).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:48:31:gdaXtile") // x4961 = SRAMNew(ArrayBuffer(Const(256), Const(128)))
    isAccum(x4961_d0_b0) = false
    bufferDepthOf(x4961_d0_b0) = 2
    staticDimsOf(x4961_d0_b0) = List(256, 128)
    val x4961_d0_b1 = SRAM(size=8192, banking=Strided(banks=16, stride=1)).name("x4961_d0_b1").ctrl(x5408).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:48:31:gdaXtile") // x4961 = SRAMNew(ArrayBuffer(Const(256), Const(128)))
    isAccum(x4961_d0_b1) = false
    bufferDepthOf(x4961_d0_b1) = 2
    staticDimsOf(x4961_d0_b1) = List(256, 128)
    val x4961_d0_b2 = SRAM(size=8192, banking=Strided(banks=16, stride=1)).name("x4961_d0_b2").ctrl(x5408).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:48:31:gdaXtile") // x4961 = SRAMNew(ArrayBuffer(Const(256), Const(128)))
    isAccum(x4961_d0_b2) = false
    bufferDepthOf(x4961_d0_b2) = 2
    staticDimsOf(x4961_d0_b2) = List(256, 128)
    val x4961_d0_b3 = SRAM(size=8192, banking=Strided(banks=16, stride=1)).name("x4961_d0_b3").ctrl(x5408).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:48:31:gdaXtile") // x4961 = SRAMNew(ArrayBuffer(Const(256), Const(128)))
    isAccum(x4961_d0_b3) = false
    bufferDepthOf(x4961_d0_b3) = 2
    staticDimsOf(x4961_d0_b3) = List(256, 128)
    val x5060 = UnitController(style=ForkJoin, level=OuterControl).name("x5060").ctrl(x5408).srcCtx("UnrollingTransformer.scala:431:43") // ParallelPipe(List(Const(true)),Block(Const(())))
    val x5010 = UnitController(style=ForkJoin, level=OuterControl).name("x5010").ctrl(x5060).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:50:18") // ParallelPipe(List(b1909),Block(Const(())))
    val x4963 = UnitController(style=SeqPipe, level=InnerControl).name("x4963").ctrl(x5010).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:50:18") // UnitPipe(List(b1909),Block(Const(())))
    val x4962 = OpDef(op=FixAdd, inputs=List(b1907, Const(256))).name("x4962").ctrl(x4963).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:51:34") // FixAdd(b1907,Const(256))
    val x4982 = UnitController(style=StreamPipe, level=OuterControl).name("x4982").ctrl(x5010).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:51:20") // UnitPipe(List(b1909),Block(Const(())))
    val b5497 = StreamOut(field="offset").name("b5497").ctrl(x4982).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:51:20") // x4964 = StreamOutNew(BurstCmdBus)
    isAccum(b5497) = false
    bufferDepthOf(b5497) = 1
    val b5498 = StreamOut(field="size").name("b5498").ctrl(x4982).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:51:20") // x4964 = StreamOutNew(BurstCmdBus)
    isAccum(b5498) = false
    bufferDepthOf(b5498) = 1
    val x4965 = StreamIn(field="data").name("x4965").ctrl(x4982).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:51:20") // x4965 = StreamInNew(BurstDataBus())
    isAccum(x4965) = false
    bufferDepthOf(x4965) = 1
    val x4973 = UnitController(style=SeqPipe, level=InnerControl).name("x4973").ctrl(x4982).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:51:20") // UnitPipe(List(b1909),Block(x4972))
    val x4966 = b1907 // FixConvert(b1907,TRUE,_32,_0) (Same Type. No op)
    val x4967 = OpDef(op=FixSla, inputs=List(x4966, Const(2))).name("x4967").ctrl(x4973).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:51:20") // FixLsh(x4966,Const(2))
    val x4968 = x4967 // FixConvert(x4967,TRUE,_64,_0)
    val x4969 = DramAddress(x4907).name("x4969").ctrl(x4973).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:51:20") // GetDRAMAddress(x4907)
    val x4971_x4970 = OpDef(op=FixAdd, inputs=List(x4968, x4969)).name("x4971_x4970").ctrl(x4973).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:51:20") // FixAdd(x4968,x4969)
    // x4971 = SimpleStruct(ArrayBuffer((offset,x4970), (size,Const(1024)), (isLoad,Const(true))))
    val x4972_b5499_b5497 = WriteMem(b5497, x4971_x4970).name("x4972_b5499_b5497").ctrl(x4973).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:51:20") // StreamWrite(x4964,x4971,b1909)
    val x4972_b5500_b5498 = WriteMem(b5498, Const(1024)).name("x4972_b5500_b5498").ctrl(x4973).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:51:20") // StreamWrite(x4964,x4971,b1909)
    val x4974 = FringeDenseLoad(dram=List(x4907), cmdStream=List(b5497, b5498), dataStream=List(x4965)).name("x4974").ctrl(x4982).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:51:20") // FringeDenseLoad(x4907,x4964,x4965)
    val x4975 = Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x4975").ctrl(x4982).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:51:20") // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x4976 = CounterChain(List(x4975)).name("x4976").ctrl(x4982).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:51:20") // CounterChainNew(List(x4975))
    val x4981 = LoopController(style=InnerPipe, level=InnerControl, cchain=x4976).name("x4981").ctrl(x4982).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:51:20") // UnrolledForeach(List(b1909),x4976,Block(Const(())),List(List(b1930)),List(List(b1931)))
    val b1930 = CounterIter(x4975, None).name("b1930").ctrl(x4981) // b1930
    val b1931 = Const(true).name("b1931").ctrl(x4981) // b1931
    val x4977 = OpDef(op=BitAnd, inputs=List(b1931, b1909)).name("x4977").ctrl(x4981).srcCtx("UnrollingBase.scala:28:66") // And(b1931,b1909)
    val x4978_x4978 = ReadMem(x4965).name("x4978_x4978").ctrl(x4981).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:51:20") // ParStreamRead(x4965,List(x4977))
    val x4979_x4979 = x4978_x4978 // x4979 = VectorApply(x4978,0)
    val x4980 = StoreBanks(List(x4958_d0_b0), List(b1930), x4979_x4979).name("x4980").ctrl(x4981).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:51:20") // ParSRAMStore(x4958,List(List(b1930)),List(x4979),List(x4977))
    val x4983 = Counter(min=Const(0), max=Const(256), step=Const(1), par=1).name("x4983").ctrl(x5010).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:52:20") // CounterNew(Const(0),Const(256),Const(1),Const(1))
    val x4984 = CounterChain(List(x4983)).name("x4984").ctrl(x5010).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:52:20") // CounterChainNew(List(x4983))
    val x5009 = LoopController(style=StreamPipe, level=OuterControl, cchain=x4984).name("x5009").ctrl(x5010).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:52:20") // UnrolledForeach(List(b1909),x4984,Block(Const(())),List(List(b1940)),List(List(b1941)))
    val b1940 = CounterIter(x4983, Some(0)).name("b1940").ctrl(x5009) // b1940
    val b1941 = Const(true).name("b1941").ctrl(x5009) // b1941
    val b5501 = StreamOut(field="offset").name("b5501").ctrl(x5009).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:52:20") // x4985 = StreamOutNew(BurstCmdBus)
    isAccum(b5501) = false
    bufferDepthOf(b5501) = 1
    val b5502 = StreamOut(field="size").name("b5502").ctrl(x5009).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:52:20") // x4985 = StreamOutNew(BurstCmdBus)
    isAccum(b5502) = false
    bufferDepthOf(b5502) = 1
    val x4986 = StreamIn(field="data").name("x4986").ctrl(x5009).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:52:20") // x4986 = StreamInNew(BurstDataBus())
    isAccum(x4986) = false
    bufferDepthOf(x4986) = 1
    val x4999 = UnitController(style=SeqPipe, level=InnerControl).name("x4999").ctrl(x5009).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:52:20") // UnitPipe(List(b1941, b1909),Block(x4998))
    val x4987 = OpDef(op=FixAdd, inputs=List(b1907, b1940)).name("x4987").ctrl(x4999).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:52:20") // FixAdd(b1907,b1940)
    val x4988 = x4987 // FixConvert(x4987,TRUE,_32,_0) (Same Type. No op)
    val x4989 = OpDef(op=FixSla, inputs=List(x4988, Const(7))).name("x4989").ctrl(x4999).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:52:20") // FixLsh(x4988,Const(7))
    val x4990 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x4991 = OpDef(op=FixAdd, inputs=List(x4989, x4990)).name("x4991").ctrl(x4999).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:52:20") // FixAdd(x4989,x4990)
    val x4992 = OpDef(op=FixSla, inputs=List(x4991, Const(2))).name("x4992").ctrl(x4999).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:52:20") // FixLsh(x4991,Const(2))
    val x4993 = x4992 // FixConvert(x4992,TRUE,_64,_0)
    val x4994 = DramAddress(x4905).name("x4994").ctrl(x4999).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:52:20") // GetDRAMAddress(x4905)
    val x4996_x4995 = OpDef(op=FixAdd, inputs=List(x4993, x4994)).name("x4996_x4995").ctrl(x4999).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:52:20") // FixAdd(x4993,x4994)
    // x4996 = SimpleStruct(ArrayBuffer((offset,x4995), (size,Const(512)), (isLoad,Const(true))))
    val x4997 = OpDef(op=BitAnd, inputs=List(b1941, b1909)).name("x4997").ctrl(x4999).srcCtx("UnrollingBase.scala:28:66") // And(b1941,b1909)
    val x4998_b5503_b5501 = WriteMem(b5501, x4996_x4995).name("x4998_b5503_b5501").ctrl(x4999).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:52:20") // StreamWrite(x4985,x4996,x4997)
    val x4998_b5504_b5502 = WriteMem(b5502, Const(512)).name("x4998_b5504_b5502").ctrl(x4999).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:52:20") // StreamWrite(x4985,x4996,x4997)
    val x5000 = FringeDenseLoad(dram=List(x4905), cmdStream=List(b5501, b5502), dataStream=List(x4986)).name("x5000").ctrl(x5009).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:52:20") // FringeDenseLoad(x4905,x4985,x4986)
    val x5001 = Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x5001").ctrl(x5009).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:52:20") // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x5002 = CounterChain(List(x5001)).name("x5002").ctrl(x5009).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:52:20") // CounterChainNew(List(x5001))
    val x5008 = LoopController(style=InnerPipe, level=InnerControl, cchain=x5002).name("x5008").ctrl(x5009).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:52:20") // UnrolledForeach(List(b1941, b1909),x5002,Block(Const(())),List(List(b1960)),List(List(b1961)))
    val b1960 = CounterIter(x5001, None).name("b1960").ctrl(x5008) // b1960
    val b1961 = Const(true).name("b1961").ctrl(x5008) // b1961
    val x5003 = OpDef(op=BitAnd, inputs=List(b1961, b1941)).name("x5003").ctrl(x5008).srcCtx("UnrollingBase.scala:28:66") // And(b1961,b1941)
    val x5004 = OpDef(op=BitAnd, inputs=List(x5003, b1909)).name("x5004").ctrl(x5008).srcCtx("UnrollingBase.scala:28:66") // And(x5003,b1909)
    val x5005_x5005 = ReadMem(x4986).name("x5005_x5005").ctrl(x5008).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:52:20") // ParStreamRead(x4986,List(x5004))
    val x5006_x5006 = x5005_x5005 // x5006 = VectorApply(x5005,0)
    val x5007 = StoreBanks(List(x4960_d0_b0, x4960_d0_b1, x4960_d0_b2, x4960_d0_b3), List(b1940, b1960), x5006_x5006).name("x5007").ctrl(x5008).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:52:20") // ParSRAMStore(x4960,List(List(b1940, b1960)),List(x5006),List(x5004))
    val x5059 = UnitController(style=ForkJoin, level=OuterControl).name("x5059").ctrl(x5060).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:50:18") // ParallelPipe(List(b1910),Block(Const(())))
    val x5012 = UnitController(style=SeqPipe, level=InnerControl).name("x5012").ctrl(x5059).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:50:18") // UnitPipe(List(b1910),Block(Const(())))
    val x5011 = OpDef(op=FixAdd, inputs=List(b1908, Const(256))).name("x5011").ctrl(x5012).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:51:34") // FixAdd(b1908,Const(256))
    val x5031 = UnitController(style=StreamPipe, level=OuterControl).name("x5031").ctrl(x5059).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:51:20") // UnitPipe(List(b1910),Block(Const(())))
    val b5505 = StreamOut(field="offset").name("b5505").ctrl(x5031).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:51:20") // x5013 = StreamOutNew(BurstCmdBus)
    isAccum(b5505) = false
    bufferDepthOf(b5505) = 1
    val b5506 = StreamOut(field="size").name("b5506").ctrl(x5031).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:51:20") // x5013 = StreamOutNew(BurstCmdBus)
    isAccum(b5506) = false
    bufferDepthOf(b5506) = 1
    val x5014 = StreamIn(field="data").name("x5014").ctrl(x5031).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:51:20") // x5014 = StreamInNew(BurstDataBus())
    isAccum(x5014) = false
    bufferDepthOf(x5014) = 1
    val x5022 = UnitController(style=SeqPipe, level=InnerControl).name("x5022").ctrl(x5031).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:51:20") // UnitPipe(List(b1910),Block(x5021))
    val x5015 = b1908 // FixConvert(b1908,TRUE,_32,_0) (Same Type. No op)
    val x5016 = OpDef(op=FixSla, inputs=List(x5015, Const(2))).name("x5016").ctrl(x5022).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:51:20") // FixLsh(x5015,Const(2))
    val x5017 = x5016 // FixConvert(x5016,TRUE,_64,_0)
    val x5018 = DramAddress(x4907).name("x5018").ctrl(x5022).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:51:20") // GetDRAMAddress(x4907)
    val x5020_x5019 = OpDef(op=FixAdd, inputs=List(x5017, x5018)).name("x5020_x5019").ctrl(x5022).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:51:20") // FixAdd(x5017,x5018)
    // x5020 = SimpleStruct(ArrayBuffer((offset,x5019), (size,Const(1024)), (isLoad,Const(true))))
    val x5021_b5507_b5505 = WriteMem(b5505, x5020_x5019).name("x5021_b5507_b5505").ctrl(x5022).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:51:20") // StreamWrite(x5013,x5020,b1910)
    val x5021_b5508_b5506 = WriteMem(b5506, Const(1024)).name("x5021_b5508_b5506").ctrl(x5022).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:51:20") // StreamWrite(x5013,x5020,b1910)
    val x5023 = FringeDenseLoad(dram=List(x4907), cmdStream=List(b5505, b5506), dataStream=List(x5014)).name("x5023").ctrl(x5031).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:51:20") // FringeDenseLoad(x4907,x5013,x5014)
    val x5024 = Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x5024").ctrl(x5031).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:51:20") // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x5025 = CounterChain(List(x5024)).name("x5025").ctrl(x5031).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:51:20") // CounterChainNew(List(x5024))
    val x5030 = LoopController(style=InnerPipe, level=InnerControl, cchain=x5025).name("x5030").ctrl(x5031).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:51:20") // UnrolledForeach(List(b1910),x5025,Block(Const(())),List(List(b1985)),List(List(b1986)))
    val b1985 = CounterIter(x5024, None).name("b1985").ctrl(x5030) // b1985
    val b1986 = Const(true).name("b1986").ctrl(x5030) // b1986
    val x5026 = OpDef(op=BitAnd, inputs=List(b1986, b1910)).name("x5026").ctrl(x5030).srcCtx("UnrollingBase.scala:28:66") // And(b1986,b1910)
    val x5027_x5027 = ReadMem(x5014).name("x5027_x5027").ctrl(x5030).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:51:20") // ParStreamRead(x5014,List(x5026))
    val x5028_x5028 = x5027_x5027 // x5028 = VectorApply(x5027,0)
    val x5029 = StoreBanks(List(x4959_d0_b0), List(b1985), x5028_x5028).name("x5029").ctrl(x5030).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:51:20") // ParSRAMStore(x4959,List(List(b1985)),List(x5028),List(x5026))
    val x5032 = Counter(min=Const(0), max=Const(256), step=Const(1), par=1).name("x5032").ctrl(x5059).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:52:20") // CounterNew(Const(0),Const(256),Const(1),Const(1))
    val x5033 = CounterChain(List(x5032)).name("x5033").ctrl(x5059).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:52:20") // CounterChainNew(List(x5032))
    val x5058 = LoopController(style=StreamPipe, level=OuterControl, cchain=x5033).name("x5058").ctrl(x5059).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:52:20") // UnrolledForeach(List(b1910),x5033,Block(Const(())),List(List(b1995)),List(List(b1996)))
    val b1995 = CounterIter(x5032, Some(0)).name("b1995").ctrl(x5058) // b1995
    val b1996 = Const(true).name("b1996").ctrl(x5058) // b1996
    val b5509 = StreamOut(field="offset").name("b5509").ctrl(x5058).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:52:20") // x5034 = StreamOutNew(BurstCmdBus)
    isAccum(b5509) = false
    bufferDepthOf(b5509) = 1
    val b5510 = StreamOut(field="size").name("b5510").ctrl(x5058).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:52:20") // x5034 = StreamOutNew(BurstCmdBus)
    isAccum(b5510) = false
    bufferDepthOf(b5510) = 1
    val x5035 = StreamIn(field="data").name("x5035").ctrl(x5058).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:52:20") // x5035 = StreamInNew(BurstDataBus())
    isAccum(x5035) = false
    bufferDepthOf(x5035) = 1
    val x5048 = UnitController(style=SeqPipe, level=InnerControl).name("x5048").ctrl(x5058).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:52:20") // UnitPipe(List(b1996, b1910),Block(x5047))
    val x5036 = OpDef(op=FixAdd, inputs=List(b1908, b1995)).name("x5036").ctrl(x5048).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:52:20") // FixAdd(b1908,b1995)
    val x5037 = x5036 // FixConvert(x5036,TRUE,_32,_0) (Same Type. No op)
    val x5038 = OpDef(op=FixSla, inputs=List(x5037, Const(7))).name("x5038").ctrl(x5048).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:52:20") // FixLsh(x5037,Const(7))
    val x5039 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x5040 = OpDef(op=FixAdd, inputs=List(x5038, x5039)).name("x5040").ctrl(x5048).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:52:20") // FixAdd(x5038,x5039)
    val x5041 = OpDef(op=FixSla, inputs=List(x5040, Const(2))).name("x5041").ctrl(x5048).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:52:20") // FixLsh(x5040,Const(2))
    val x5042 = x5041 // FixConvert(x5041,TRUE,_64,_0)
    val x5043 = DramAddress(x4905).name("x5043").ctrl(x5048).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:52:20") // GetDRAMAddress(x4905)
    val x5045_x5044 = OpDef(op=FixAdd, inputs=List(x5042, x5043)).name("x5045_x5044").ctrl(x5048).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:52:20") // FixAdd(x5042,x5043)
    // x5045 = SimpleStruct(ArrayBuffer((offset,x5044), (size,Const(512)), (isLoad,Const(true))))
    val x5046 = OpDef(op=BitAnd, inputs=List(b1996, b1910)).name("x5046").ctrl(x5048).srcCtx("UnrollingBase.scala:28:66") // And(b1996,b1910)
    val x5047_b5511_b5509 = WriteMem(b5509, x5045_x5044).name("x5047_b5511_b5509").ctrl(x5048).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:52:20") // StreamWrite(x5034,x5045,x5046)
    val x5047_b5512_b5510 = WriteMem(b5510, Const(512)).name("x5047_b5512_b5510").ctrl(x5048).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:52:20") // StreamWrite(x5034,x5045,x5046)
    val x5049 = FringeDenseLoad(dram=List(x4905), cmdStream=List(b5509, b5510), dataStream=List(x5035)).name("x5049").ctrl(x5058).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:52:20") // FringeDenseLoad(x4905,x5034,x5035)
    val x5050 = Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x5050").ctrl(x5058).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:52:20") // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x5051 = CounterChain(List(x5050)).name("x5051").ctrl(x5058).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:52:20") // CounterChainNew(List(x5050))
    val x5057 = LoopController(style=InnerPipe, level=InnerControl, cchain=x5051).name("x5057").ctrl(x5058).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:52:20") // UnrolledForeach(List(b1996, b1910),x5051,Block(Const(())),List(List(b2015)),List(List(b2016)))
    val b2015 = CounterIter(x5050, None).name("b2015").ctrl(x5057) // b2015
    val b2016 = Const(true).name("b2016").ctrl(x5057) // b2016
    val x5052 = OpDef(op=BitAnd, inputs=List(b2016, b1996)).name("x5052").ctrl(x5057).srcCtx("UnrollingBase.scala:28:66") // And(b2016,b1996)
    val x5053 = OpDef(op=BitAnd, inputs=List(x5052, b1910)).name("x5053").ctrl(x5057).srcCtx("UnrollingBase.scala:28:66") // And(x5052,b1910)
    val x5054_x5054 = ReadMem(x5035).name("x5054_x5054").ctrl(x5057).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:52:20") // ParStreamRead(x5035,List(x5053))
    val x5055_x5055 = x5054_x5054 // x5055 = VectorApply(x5054,0)
    val x5056 = StoreBanks(List(x4961_d0_b0, x4961_d0_b1, x4961_d0_b2, x4961_d0_b3), List(b1995, b2015), x5055_x5055).name("x5056").ctrl(x5057).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:52:20") // ParSRAMStore(x4961,List(List(b1995, b2015)),List(x5055),List(x5053))
    val x5061_d0_b0 = SRAM(size=16384, banking=Strided(banks=1, stride=128)).name("x5061_d0_b0").ctrl(x5408).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:55:31:sigmaBlk") // x5061 = SRAMNew(ArrayBuffer(Const(128), Const(128)))
    isAccum(x5061_d0_b0) = false
    bufferDepthOf(x5061_d0_b0) = 2
    staticDimsOf(x5061_d0_b0) = List(128, 128)
    val x5061_d1_b0 = SRAM(size=16384, banking=Strided(banks=1, stride=128)).name("x5061_d1_b0").ctrl(x5408).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:55:31:sigmaBlk") // x5061 = SRAMNew(ArrayBuffer(Const(128), Const(128)))
    isAccum(x5061_d1_b0) = true
    bufferDepthOf(x5061_d1_b0) = 1
    staticDimsOf(x5061_d1_b0) = List(128, 128)
    val x5062_d0_b0 = SRAM(size=16384, banking=Strided(banks=1, stride=128)).name("x5062_d0_b0").ctrl(x5408).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:55:31:sigmaBlk") // x5062 = SRAMNew(ArrayBuffer(Const(128), Const(128)))
    isAccum(x5062_d0_b0) = false
    bufferDepthOf(x5062_d0_b0) = 2
    staticDimsOf(x5062_d0_b0) = List(128, 128)
    val x5062_d1_b0 = SRAM(size=16384, banking=Strided(banks=1, stride=128)).name("x5062_d1_b0").ctrl(x5408).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:55:31:sigmaBlk") // x5062 = SRAMNew(ArrayBuffer(Const(128), Const(128)))
    isAccum(x5062_d1_b0) = true
    bufferDepthOf(x5062_d1_b0) = 1
    staticDimsOf(x5062_d1_b0) = List(128, 128)
    val x5387 = UnitController(style=ForkJoin, level=OuterControl).name("x5387").ctrl(x5408).srcCtx("UnrollingTransformer.scala:431:43") // ParallelPipe(List(Const(true)),Block(Const(())))
    val x5063 = Counter(min=Const(0), max=Const(256), step=Const(1), par=4).name("x5063").ctrl(x5387).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:57:32") // CounterNew(Const(0),Const(256),Const(1),Const(4))
    val x5064 = CounterChain(List(x5063)).name("x5064").ctrl(x5387).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:67:10") // CounterChainNew(List(x5063))
    val x5224 = LoopController(style=MetaPipe, level=OuterControl, cchain=x5064).name("x5224").ctrl(x5387).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:67:10") // UnrolledReduce(List(b1909),x5064,x5061,Block((x5061) => Const(())),List(List(b2038, b2039, b2040, b2041)),List(List(b2042, b2043, b2044, b2045)))
    val b2038 = CounterIter(x5063, Some(0)).name("b2038").ctrl(x5224) // b2038
    val b2042 = Const(true).name("b2042").ctrl(x5224) // b2042
    val b2039 = CounterIter(x5063, Some(1)).name("b2039").ctrl(x5224) // b2039
    val b2043 = Const(true).name("b2043").ctrl(x5224) // b2043
    val b2040 = CounterIter(x5063, Some(2)).name("b2040").ctrl(x5224) // b2040
    val b2044 = Const(true).name("b2044").ctrl(x5224) // b2044
    val b2041 = CounterIter(x5063, Some(3)).name("b2041").ctrl(x5224) // b2041
    val b2045 = Const(true).name("b2045").ctrl(x5224) // b2045
    val x5065_d0_b0 = SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x5065_d0_b0").ctrl(x5224).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:58:32:subTile") // x5065 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x5065_d0_b0) = false
    bufferDepthOf(x5065_d0_b0) = 2
    staticDimsOf(x5065_d0_b0) = List(128)
    val x5065_d1_b0 = SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x5065_d1_b0").ctrl(x5224).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:58:32:subTile") // x5065 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x5065_d1_b0) = false
    bufferDepthOf(x5065_d1_b0) = 2
    staticDimsOf(x5065_d1_b0) = List(128)
    val x5066_d0_b0 = SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x5066_d0_b0").ctrl(x5224).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:58:32:subTile") // x5066 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x5066_d0_b0) = false
    bufferDepthOf(x5066_d0_b0) = 2
    staticDimsOf(x5066_d0_b0) = List(128)
    val x5066_d1_b0 = SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x5066_d1_b0").ctrl(x5224).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:58:32:subTile") // x5066 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x5066_d1_b0) = false
    bufferDepthOf(x5066_d1_b0) = 2
    staticDimsOf(x5066_d1_b0) = List(128)
    val x5067_d0_b0 = SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x5067_d0_b0").ctrl(x5224).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:58:32:subTile") // x5067 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x5067_d0_b0) = false
    bufferDepthOf(x5067_d0_b0) = 2
    staticDimsOf(x5067_d0_b0) = List(128)
    val x5067_d1_b0 = SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x5067_d1_b0").ctrl(x5224).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:58:32:subTile") // x5067 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x5067_d1_b0) = false
    bufferDepthOf(x5067_d1_b0) = 2
    staticDimsOf(x5067_d1_b0) = List(128)
    val x5068_d0_b0 = SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x5068_d0_b0").ctrl(x5224).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:58:32:subTile") // x5068 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x5068_d0_b0) = false
    bufferDepthOf(x5068_d0_b0) = 2
    staticDimsOf(x5068_d0_b0) = List(128)
    val x5068_d1_b0 = SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x5068_d1_b0").ctrl(x5224).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:58:32:subTile") // x5068 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x5068_d1_b0) = false
    bufferDepthOf(x5068_d1_b0) = 2
    staticDimsOf(x5068_d1_b0) = List(128)
    val x5069_d0_b0 = SRAM(size=16384, banking=Strided(banks=16, stride=1)).name("x5069_d0_b0").ctrl(x5224).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:59:34:sigmaTile") // x5069 = SRAMNew(ArrayBuffer(Const(128), Const(128)))
    isAccum(x5069_d0_b0) = false
    bufferDepthOf(x5069_d0_b0) = 2
    staticDimsOf(x5069_d0_b0) = List(128, 128)
    val x5070_d0_b0 = SRAM(size=16384, banking=Strided(banks=16, stride=1)).name("x5070_d0_b0").ctrl(x5224).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:59:34:sigmaTile") // x5070 = SRAMNew(ArrayBuffer(Const(128), Const(128)))
    isAccum(x5070_d0_b0) = false
    bufferDepthOf(x5070_d0_b0) = 2
    staticDimsOf(x5070_d0_b0) = List(128, 128)
    val x5071_d0_b0 = SRAM(size=16384, banking=Strided(banks=16, stride=1)).name("x5071_d0_b0").ctrl(x5224).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:59:34:sigmaTile") // x5071 = SRAMNew(ArrayBuffer(Const(128), Const(128)))
    isAccum(x5071_d0_b0) = false
    bufferDepthOf(x5071_d0_b0) = 2
    staticDimsOf(x5071_d0_b0) = List(128, 128)
    val x5072_d0_b0 = SRAM(size=16384, banking=Strided(banks=16, stride=1)).name("x5072_d0_b0").ctrl(x5224).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:59:34:sigmaTile") // x5072 = SRAMNew(ArrayBuffer(Const(128), Const(128)))
    isAccum(x5072_d0_b0) = false
    bufferDepthOf(x5072_d0_b0) = 2
    staticDimsOf(x5072_d0_b0) = List(128, 128)
    val x5137 = UnitController(style=ForkJoin, level=OuterControl).name("x5137").ctrl(x5224).srcCtx("UnrollingTransformer.scala:431:43") // ParallelPipe(List(b1909),Block(Const(())))
    val x5073 = Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x5073").ctrl(x5137).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:60:21") // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x5074 = CounterChain(List(x5073)).name("x5074").ctrl(x5137).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:60:29") // CounterChainNew(List(x5073))
    val x5088 = LoopController(style=InnerPipe, level=InnerControl, cchain=x5074).name("x5088").ctrl(x5137).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:60:29") // UnrolledForeach(List(b2042, b1909),x5074,Block(Const(())),List(List(b2062)),List(List(b2063)))
    val b2062 = CounterIter(x5073, None).name("b2062").ctrl(x5088) // b2062
    val b2063 = Const(true).name("b2063").ctrl(x5088) // b2063
    val x5075 = OpDef(op=BitAnd, inputs=List(b2063, b2042)).name("x5075").ctrl(x5088).srcCtx("UnrollingBase.scala:28:66") // And(b2063,b2042)
    val x5076 = OpDef(op=BitAnd, inputs=List(x5075, b1909)).name("x5076").ctrl(x5088).srcCtx("UnrollingBase.scala:28:66") // And(x5075,b1909)
    val x5077 = LoadBanks(List(x4960_d0_b0), List(b2038, b2062)).name("x5077").ctrl(x5088).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:61:35") // ParSRAMLoad(x4960,List(List(b2038, b2062)),List(x5076))
    val x5078 = x5077 // x5078 = VectorApply(x5077,0)
    val x5079 = LoadBanks(List(x4958_d0_b0), List(b2038)).name("x5079").ctrl(x5088).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:61:58") // SRAMLoad(x4958,ArrayBuffer(Const(256)),List(b2038),Const(0),x5076)
    val x5080 = OpDef(op=FixEql, inputs=List(x5079, Const(1))).name("x5080").ctrl(x5088).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:61:63") // FixEql(x5079,Const(1))
    val x5081 = LoadBanks(List(x4916_d0_b0), List(b2062)).name("x5081").ctrl(x5088).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:61:76") // ParSRAMLoad(x4916,List(List(b2062)),List(x5076))
    val x5082 = x5081 // x5082 = VectorApply(x5081,0)
    val x5083 = LoadBanks(List(x4915_d0_b0), List(b2062)).name("x5083").ctrl(x5088).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:61:89") // ParSRAMLoad(x4915,List(List(b2062)),List(x5076))
    val x5084 = x5083 // x5084 = VectorApply(x5083,0)
    val x5085 = OpDef(op=MuxOp, inputs=List(x5080, x5082, x5084)).name("x5085").ctrl(x5088).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:61:49") // Mux(x5080,x5082,x5084)
    val x5086 = OpDef(op=FixSub, inputs=List(x5078, x5085)).name("x5086").ctrl(x5088).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:61:44") // FixSub(x5078,x5085)
    val x5087 = StoreBanks(List(x5065_d0_b0, x5065_d1_b0), List(b2062), x5086).name("x5087").ctrl(x5088).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:61:25") // ParSRAMStore(x5065,List(List(b2062)),List(x5086),List(x5076))
    val x5089 = Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x5089").ctrl(x5137).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:60:21") // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x5090 = CounterChain(List(x5089)).name("x5090").ctrl(x5137).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:60:29") // CounterChainNew(List(x5089))
    val x5104 = LoopController(style=InnerPipe, level=InnerControl, cchain=x5090).name("x5104").ctrl(x5137).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:60:29") // UnrolledForeach(List(b2043, b1909),x5090,Block(Const(())),List(List(b2078)),List(List(b2079)))
    val b2078 = CounterIter(x5089, None).name("b2078").ctrl(x5104) // b2078
    val b2079 = Const(true).name("b2079").ctrl(x5104) // b2079
    val x5091 = OpDef(op=BitAnd, inputs=List(b2079, b2043)).name("x5091").ctrl(x5104).srcCtx("UnrollingBase.scala:28:66") // And(b2079,b2043)
    val x5092 = OpDef(op=BitAnd, inputs=List(x5091, b1909)).name("x5092").ctrl(x5104).srcCtx("UnrollingBase.scala:28:66") // And(x5091,b1909)
    val x5093 = LoadBanks(List(x4960_d0_b1), List(b2039, b2078)).name("x5093").ctrl(x5104).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:61:35") // ParSRAMLoad(x4960,List(List(b2039, b2078)),List(x5092))
    val x5094 = x5093 // x5094 = VectorApply(x5093,0)
    val x5095 = LoadBanks(List(x4958_d0_b0), List(b2039)).name("x5095").ctrl(x5104).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:61:58") // SRAMLoad(x4958,ArrayBuffer(Const(256)),List(b2039),Const(0),x5092)
    val x5096 = OpDef(op=FixEql, inputs=List(x5095, Const(1))).name("x5096").ctrl(x5104).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:61:63") // FixEql(x5095,Const(1))
    val x5097 = LoadBanks(List(x4916_d1_b0), List(b2078)).name("x5097").ctrl(x5104).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:61:76") // ParSRAMLoad(x4916,List(List(b2078)),List(x5092))
    val x5098 = x5097 // x5098 = VectorApply(x5097,0)
    val x5099 = LoadBanks(List(x4915_d1_b0), List(b2078)).name("x5099").ctrl(x5104).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:61:89") // ParSRAMLoad(x4915,List(List(b2078)),List(x5092))
    val x5100 = x5099 // x5100 = VectorApply(x5099,0)
    val x5101 = OpDef(op=MuxOp, inputs=List(x5096, x5098, x5100)).name("x5101").ctrl(x5104).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:61:49") // Mux(x5096,x5098,x5100)
    val x5102 = OpDef(op=FixSub, inputs=List(x5094, x5101)).name("x5102").ctrl(x5104).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:61:44") // FixSub(x5094,x5101)
    val x5103 = StoreBanks(List(x5066_d0_b0, x5066_d1_b0), List(b2078), x5102).name("x5103").ctrl(x5104).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:61:25") // ParSRAMStore(x5066,List(List(b2078)),List(x5102),List(x5092))
    val x5105 = Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x5105").ctrl(x5137).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:60:21") // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x5106 = CounterChain(List(x5105)).name("x5106").ctrl(x5137).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:60:29") // CounterChainNew(List(x5105))
    val x5120 = LoopController(style=InnerPipe, level=InnerControl, cchain=x5106).name("x5120").ctrl(x5137).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:60:29") // UnrolledForeach(List(b2044, b1909),x5106,Block(Const(())),List(List(b2094)),List(List(b2095)))
    val b2094 = CounterIter(x5105, None).name("b2094").ctrl(x5120) // b2094
    val b2095 = Const(true).name("b2095").ctrl(x5120) // b2095
    val x5107 = OpDef(op=BitAnd, inputs=List(b2095, b2044)).name("x5107").ctrl(x5120).srcCtx("UnrollingBase.scala:28:66") // And(b2095,b2044)
    val x5108 = OpDef(op=BitAnd, inputs=List(x5107, b1909)).name("x5108").ctrl(x5120).srcCtx("UnrollingBase.scala:28:66") // And(x5107,b1909)
    val x5109 = LoadBanks(List(x4960_d0_b2), List(b2040, b2094)).name("x5109").ctrl(x5120).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:61:35") // ParSRAMLoad(x4960,List(List(b2040, b2094)),List(x5108))
    val x5110 = x5109 // x5110 = VectorApply(x5109,0)
    val x5111 = LoadBanks(List(x4958_d0_b0), List(b2040)).name("x5111").ctrl(x5120).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:61:58") // SRAMLoad(x4958,ArrayBuffer(Const(256)),List(b2040),Const(0),x5108)
    val x5112 = OpDef(op=FixEql, inputs=List(x5111, Const(1))).name("x5112").ctrl(x5120).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:61:63") // FixEql(x5111,Const(1))
    val x5113 = LoadBanks(List(x4916_d2_b0), List(b2094)).name("x5113").ctrl(x5120).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:61:76") // ParSRAMLoad(x4916,List(List(b2094)),List(x5108))
    val x5114 = x5113 // x5114 = VectorApply(x5113,0)
    val x5115 = LoadBanks(List(x4915_d2_b0), List(b2094)).name("x5115").ctrl(x5120).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:61:89") // ParSRAMLoad(x4915,List(List(b2094)),List(x5108))
    val x5116 = x5115 // x5116 = VectorApply(x5115,0)
    val x5117 = OpDef(op=MuxOp, inputs=List(x5112, x5114, x5116)).name("x5117").ctrl(x5120).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:61:49") // Mux(x5112,x5114,x5116)
    val x5118 = OpDef(op=FixSub, inputs=List(x5110, x5117)).name("x5118").ctrl(x5120).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:61:44") // FixSub(x5110,x5117)
    val x5119 = StoreBanks(List(x5067_d0_b0, x5067_d1_b0), List(b2094), x5118).name("x5119").ctrl(x5120).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:61:25") // ParSRAMStore(x5067,List(List(b2094)),List(x5118),List(x5108))
    val x5121 = Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x5121").ctrl(x5137).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:60:21") // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x5122 = CounterChain(List(x5121)).name("x5122").ctrl(x5137).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:60:29") // CounterChainNew(List(x5121))
    val x5136 = LoopController(style=InnerPipe, level=InnerControl, cchain=x5122).name("x5136").ctrl(x5137).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:60:29") // UnrolledForeach(List(b2045, b1909),x5122,Block(Const(())),List(List(b2110)),List(List(b2111)))
    val b2110 = CounterIter(x5121, None).name("b2110").ctrl(x5136) // b2110
    val b2111 = Const(true).name("b2111").ctrl(x5136) // b2111
    val x5123 = OpDef(op=BitAnd, inputs=List(b2111, b2045)).name("x5123").ctrl(x5136).srcCtx("UnrollingBase.scala:28:66") // And(b2111,b2045)
    val x5124 = OpDef(op=BitAnd, inputs=List(x5123, b1909)).name("x5124").ctrl(x5136).srcCtx("UnrollingBase.scala:28:66") // And(x5123,b1909)
    val x5125 = LoadBanks(List(x4960_d0_b3), List(b2041, b2110)).name("x5125").ctrl(x5136).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:61:35") // ParSRAMLoad(x4960,List(List(b2041, b2110)),List(x5124))
    val x5126 = x5125 // x5126 = VectorApply(x5125,0)
    val x5127 = LoadBanks(List(x4958_d0_b0), List(b2041)).name("x5127").ctrl(x5136).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:61:58") // SRAMLoad(x4958,ArrayBuffer(Const(256)),List(b2041),Const(0),x5124)
    val x5128 = OpDef(op=FixEql, inputs=List(x5127, Const(1))).name("x5128").ctrl(x5136).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:61:63") // FixEql(x5127,Const(1))
    val x5129 = LoadBanks(List(x4916_d3_b0), List(b2110)).name("x5129").ctrl(x5136).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:61:76") // ParSRAMLoad(x4916,List(List(b2110)),List(x5124))
    val x5130 = x5129 // x5130 = VectorApply(x5129,0)
    val x5131 = LoadBanks(List(x4915_d3_b0), List(b2110)).name("x5131").ctrl(x5136).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:61:89") // ParSRAMLoad(x4915,List(List(b2110)),List(x5124))
    val x5132 = x5131 // x5132 = VectorApply(x5131,0)
    val x5133 = OpDef(op=MuxOp, inputs=List(x5128, x5130, x5132)).name("x5133").ctrl(x5136).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:61:49") // Mux(x5128,x5130,x5132)
    val x5134 = OpDef(op=FixSub, inputs=List(x5126, x5133)).name("x5134").ctrl(x5136).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:61:44") // FixSub(x5126,x5133)
    val x5135 = StoreBanks(List(x5068_d0_b0, x5068_d1_b0), List(b2110), x5134).name("x5135").ctrl(x5136).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:61:25") // ParSRAMStore(x5068,List(List(b2110)),List(x5134),List(x5124))
    val x5186 = UnitController(style=ForkJoin, level=OuterControl).name("x5186").ctrl(x5224).srcCtx("UnrollingTransformer.scala:431:43") // ParallelPipe(List(b1909),Block(Const(())))
    val x5138 = Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x5138").ctrl(x5186).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:63:29") // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x5139 = Counter(min=Const(0), max=Const(128), step=Const(1), par=1).name("x5139").ctrl(x5186).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:63:21") // CounterNew(Const(0),Const(128),Const(1),Const(1))
    val x5140 = CounterChain(List(x5139,x5138)).name("x5140").ctrl(x5186).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:63:37") // CounterChainNew(List(x5139, x5138))
    val x5149 = LoopController(style=InnerPipe, level=InnerControl, cchain=x5140).name("x5149").ctrl(x5186).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:63:37") // UnrolledForeach(List(b2042, b1909),x5140,Block(Const(())),List(List(b2139), List(b2140)),List(List(b2141), List(b2142)))
    val b2139 = CounterIter(x5139, Some(0)).name("b2139").ctrl(x5149) // b2139
    val b2141 = Const(true).name("b2141").ctrl(x5149) // b2141
    val b2140 = CounterIter(x5138, None).name("b2140").ctrl(x5149) // b2140
    val b2142 = Const(true).name("b2142").ctrl(x5149) // b2142
    val x5141 = OpDef(op=BitAnd, inputs=List(b2141, b2142)).name("x5141").ctrl(x5149).srcCtx("UnrollingBase.scala:28:66") // And(b2141,b2142)
    val x5142 = OpDef(op=BitAnd, inputs=List(b2042, b1909)).name("x5142").ctrl(x5149).srcCtx("UnrollingBase.scala:28:66") // And(b2042,b1909)
    val x5143 = OpDef(op=BitAnd, inputs=List(x5141, x5142)).name("x5143").ctrl(x5149).srcCtx("UnrollingBase.scala:28:66") // And(x5141,x5142)
    val x5144 = LoadBanks(List(x5065_d1_b0), List(b2139)).name("x5144").ctrl(x5149).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:64:40") // SRAMLoad(x5065,ArrayBuffer(Const(128)),List(b2139),Const(0),x5143)
    val x5145 = LoadBanks(List(x5065_d0_b0), List(b2140)).name("x5145").ctrl(x5149).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:64:54") // ParSRAMLoad(x5065,List(List(b2140)),List(x5143))
    val x5146 = x5145 // x5146 = VectorApply(x5145,0)
    val x5147 = OpDef(op=FixMul, inputs=List(x5144, x5146)).name("x5147").ctrl(x5149).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:64:45") // FixMul(x5144,x5146)
    val x5148 = StoreBanks(List(x5069_d0_b0), List(b2139, b2140), x5147).name("x5148").ctrl(x5149).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:64:31") // ParSRAMStore(x5069,List(List(b2139, b2140)),List(x5147),List(x5143))
    val x5150 = Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x5150").ctrl(x5186).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:63:29") // CounterNew(Const(0),Const(128),Const(1),Const(16))
    def split1 = {
    val x5151 = Counter(min=Const(0), max=Const(128), step=Const(1), par=1).name("x5151").ctrl(x5186).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:63:21") // CounterNew(Const(0),Const(128),Const(1),Const(1))
    val x5152 = CounterChain(List(x5151,x5150)).name("x5152").ctrl(x5186).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:63:37") // CounterChainNew(List(x5151, x5150))
    val x5161 = LoopController(style=InnerPipe, level=InnerControl, cchain=x5152).name("x5161").ctrl(x5186).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:63:37") // UnrolledForeach(List(b2043, b1909),x5152,Block(Const(())),List(List(b2152), List(b2153)),List(List(b2154), List(b2155)))
    val b2152 = CounterIter(x5151, Some(0)).name("b2152").ctrl(x5161) // b2152
    val b2154 = Const(true).name("b2154").ctrl(x5161) // b2154
    val b2153 = CounterIter(x5150, None).name("b2153").ctrl(x5161) // b2153
    val b2155 = Const(true).name("b2155").ctrl(x5161) // b2155
    val x5153 = OpDef(op=BitAnd, inputs=List(b2154, b2155)).name("x5153").ctrl(x5161).srcCtx("UnrollingBase.scala:28:66") // And(b2154,b2155)
    val x5154 = OpDef(op=BitAnd, inputs=List(b2043, b1909)).name("x5154").ctrl(x5161).srcCtx("UnrollingBase.scala:28:66") // And(b2043,b1909)
    val x5155 = OpDef(op=BitAnd, inputs=List(x5153, x5154)).name("x5155").ctrl(x5161).srcCtx("UnrollingBase.scala:28:66") // And(x5153,x5154)
    val x5156 = LoadBanks(List(x5066_d1_b0), List(b2152)).name("x5156").ctrl(x5161).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:64:40") // SRAMLoad(x5066,ArrayBuffer(Const(128)),List(b2152),Const(0),x5155)
    val x5157 = LoadBanks(List(x5066_d0_b0), List(b2153)).name("x5157").ctrl(x5161).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:64:54") // ParSRAMLoad(x5066,List(List(b2153)),List(x5155))
    val x5158 = x5157 // x5158 = VectorApply(x5157,0)
    val x5159 = OpDef(op=FixMul, inputs=List(x5156, x5158)).name("x5159").ctrl(x5161).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:64:45") // FixMul(x5156,x5158)
    val x5160 = StoreBanks(List(x5070_d0_b0), List(b2152, b2153), x5159).name("x5160").ctrl(x5161).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:64:31") // ParSRAMStore(x5070,List(List(b2152, b2153)),List(x5159),List(x5155))
    val x5162 = Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x5162").ctrl(x5186).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:63:29") // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x5163 = Counter(min=Const(0), max=Const(128), step=Const(1), par=1).name("x5163").ctrl(x5186).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:63:21") // CounterNew(Const(0),Const(128),Const(1),Const(1))
    val x5164 = CounterChain(List(x5163,x5162)).name("x5164").ctrl(x5186).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:63:37") // CounterChainNew(List(x5163, x5162))
    val x5173 = LoopController(style=InnerPipe, level=InnerControl, cchain=x5164).name("x5173").ctrl(x5186).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:63:37") // UnrolledForeach(List(b2044, b1909),x5164,Block(Const(())),List(List(b2165), List(b2166)),List(List(b2167), List(b2168)))
    val b2165 = CounterIter(x5163, Some(0)).name("b2165").ctrl(x5173) // b2165
    val b2167 = Const(true).name("b2167").ctrl(x5173) // b2167
    val b2166 = CounterIter(x5162, None).name("b2166").ctrl(x5173) // b2166
    val b2168 = Const(true).name("b2168").ctrl(x5173) // b2168
    val x5165 = OpDef(op=BitAnd, inputs=List(b2167, b2168)).name("x5165").ctrl(x5173).srcCtx("UnrollingBase.scala:28:66") // And(b2167,b2168)
    val x5166 = OpDef(op=BitAnd, inputs=List(b2044, b1909)).name("x5166").ctrl(x5173).srcCtx("UnrollingBase.scala:28:66") // And(b2044,b1909)
    val x5167 = OpDef(op=BitAnd, inputs=List(x5165, x5166)).name("x5167").ctrl(x5173).srcCtx("UnrollingBase.scala:28:66") // And(x5165,x5166)
    val x5168 = LoadBanks(List(x5067_d1_b0), List(b2165)).name("x5168").ctrl(x5173).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:64:40") // SRAMLoad(x5067,ArrayBuffer(Const(128)),List(b2165),Const(0),x5167)
    val x5169 = LoadBanks(List(x5067_d0_b0), List(b2166)).name("x5169").ctrl(x5173).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:64:54") // ParSRAMLoad(x5067,List(List(b2166)),List(x5167))
    val x5170 = x5169 // x5170 = VectorApply(x5169,0)
    val x5171 = OpDef(op=FixMul, inputs=List(x5168, x5170)).name("x5171").ctrl(x5173).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:64:45") // FixMul(x5168,x5170)
    val x5172 = StoreBanks(List(x5071_d0_b0), List(b2165, b2166), x5171).name("x5172").ctrl(x5173).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:64:31") // ParSRAMStore(x5071,List(List(b2165, b2166)),List(x5171),List(x5167))
    val x5174 = Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x5174").ctrl(x5186).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:63:29") // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x5175 = Counter(min=Const(0), max=Const(128), step=Const(1), par=1).name("x5175").ctrl(x5186).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:63:21") // CounterNew(Const(0),Const(128),Const(1),Const(1))
    val x5176 = CounterChain(List(x5175,x5174)).name("x5176").ctrl(x5186).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:63:37") // CounterChainNew(List(x5175, x5174))
    val x5185 = LoopController(style=InnerPipe, level=InnerControl, cchain=x5176).name("x5185").ctrl(x5186).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:63:37") // UnrolledForeach(List(b2045, b1909),x5176,Block(Const(())),List(List(b2178), List(b2179)),List(List(b2180), List(b2181)))
    val b2178 = CounterIter(x5175, Some(0)).name("b2178").ctrl(x5185) // b2178
    val b2180 = Const(true).name("b2180").ctrl(x5185) // b2180
    val b2179 = CounterIter(x5174, None).name("b2179").ctrl(x5185) // b2179
    val b2181 = Const(true).name("b2181").ctrl(x5185) // b2181
    val x5177 = OpDef(op=BitAnd, inputs=List(b2180, b2181)).name("x5177").ctrl(x5185).srcCtx("UnrollingBase.scala:28:66") // And(b2180,b2181)
    val x5178 = OpDef(op=BitAnd, inputs=List(b2045, b1909)).name("x5178").ctrl(x5185).srcCtx("UnrollingBase.scala:28:66") // And(b2045,b1909)
    val x5179 = OpDef(op=BitAnd, inputs=List(x5177, x5178)).name("x5179").ctrl(x5185).srcCtx("UnrollingBase.scala:28:66") // And(x5177,x5178)
    val x5180 = LoadBanks(List(x5068_d1_b0), List(b2178)).name("x5180").ctrl(x5185).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:64:40") // SRAMLoad(x5068,ArrayBuffer(Const(128)),List(b2178),Const(0),x5179)
    val x5181 = LoadBanks(List(x5068_d0_b0), List(b2179)).name("x5181").ctrl(x5185).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:64:54") // ParSRAMLoad(x5068,List(List(b2179)),List(x5179))
    val x5182 = x5181 // x5182 = VectorApply(x5181,0)
    val x5183 = OpDef(op=FixMul, inputs=List(x5180, x5182)).name("x5183").ctrl(x5185).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:64:45") // FixMul(x5180,x5182)
    val x5184 = StoreBanks(List(x5072_d0_b0), List(b2178, b2179), x5183).name("x5184").ctrl(x5185).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:64:31") // ParSRAMStore(x5072,List(List(b2178, b2179)),List(x5183),List(x5179))
    val x5187 = Counter(min=Const(0), max=Const(128), step=Const(1), par=1).name("x5187").ctrl(x5224).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:67:10") // CounterNew(Const(0),Const(128),Const(1),Const(1))
    val x5188 = Counter(min=Const(0), max=Const(128), step=Const(1), par=1).name("x5188").ctrl(x5224).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:67:10") // CounterNew(Const(0),Const(128),Const(1),Const(1))
    val x5189 = CounterChain(List(x5188,x5187)).name("x5189").ctrl(x5224).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:67:10") // CounterChainNew(ArrayBuffer(x5188, x5187))
    val x5223 = LoopController(style=InnerPipe, level=InnerControl, cchain=x5189).name("x5223").ctrl(x5224).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:67:10") // UnrolledForeach(List(),x5189,Block(Const(())),ArrayBuffer(List(b2192), List(b2193)),ArrayBuffer(List(b2194), List(b2195)))
    val b2192 = CounterIter(x5188, Some(0)).name("b2192").ctrl(x5223) // b2192
    val b2194 = Const(true).name("b2194").ctrl(x5223) // b2194
    val b2193 = CounterIter(x5187, None).name("b2193").ctrl(x5223) // b2193
    val b2195 = Const(true).name("b2195").ctrl(x5223) // b2195
    val x5190 = OpDef(op=BitAnd, inputs=List(b2194, b2195)).name("x5190").ctrl(x5223).srcCtx("UnrollingBase.scala:28:66") // And(b2194,b2195)
    val x5191 = OpDef(op=BitAnd, inputs=List(x5190, b1909)).name("x5191").ctrl(x5223).srcCtx("UnrollingBase.scala:28:66") // And(x5190,b1909)
    val x5192 = LoadBanks(List(x5069_d0_b0), List(b2192, b2193)).name("x5192").ctrl(x5223).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:67:10") // ParSRAMLoad(x5069,List(ArrayBuffer(b2192, b2193)),List(x5191))
    val x5193 = x5192 // x5193 = VectorApply(x5192,0)
    val x5194 = LoadBanks(List(x5070_d0_b0), List(b2192, b2193)).name("x5194").ctrl(x5223).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:67:10") // ParSRAMLoad(x5070,List(ArrayBuffer(b2192, b2193)),List(x5191))
    val x5195 = x5194 // x5195 = VectorApply(x5194,0)
    val x5196 = LoadBanks(List(x5071_d0_b0), List(b2192, b2193)).name("x5196").ctrl(x5223).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:67:10") // ParSRAMLoad(x5071,List(ArrayBuffer(b2192, b2193)),List(x5191))
    val x5197 = x5196 // x5197 = VectorApply(x5196,0)
    val x5198 = LoadBanks(List(x5072_d0_b0), List(b2192, b2193)).name("x5198").ctrl(x5223).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:67:10") // ParSRAMLoad(x5072,List(ArrayBuffer(b2192, b2193)),List(x5191))
    val x5199 = x5198 // x5199 = VectorApply(x5198,0)
    val x5200 = LoadBanks(List(x5061_d1_b0), List(b2192, b2193)).name("x5200").ctrl(x5223).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:67:10") // ParSRAMLoad(x5061,List(ArrayBuffer(b2192, b2193)),List(x5191))
    val x5201 = x5200 // x5201 = VectorApply(x5200,0)
    val x5202 = OpDef(op=BitAnd, inputs=List(b2042, b1909)).name("x5202").ctrl(x5223).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:67:10") // And(b2042,b1909)
    val x5203 = OpDef(op=BitAnd, inputs=List(b2043, b1909)).name("x5203").ctrl(x5223).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:67:10") // And(b2043,b1909)
    val x5204 = OpDef(op=BitAnd, inputs=List(b2044, b1909)).name("x5204").ctrl(x5223).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:67:10") // And(b2044,b1909)
    val x5205 = OpDef(op=BitAnd, inputs=List(b2045, b1909)).name("x5205").ctrl(x5223).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:67:10") // And(b2045,b1909)
    val x5206 = OpDef(op=BitAnd, inputs=List(x5202, x5191)).name("x5206").ctrl(x5223).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:67:10") // And(x5202,x5191)
    val x5207 = OpDef(op=BitAnd, inputs=List(x5203, x5191)).name("x5207").ctrl(x5223).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:67:10") // And(x5203,x5191)
    val x5208 = OpDef(op=BitAnd, inputs=List(x5204, x5191)).name("x5208").ctrl(x5223).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:67:10") // And(x5204,x5191)
    val x5209 = OpDef(op=BitAnd, inputs=List(x5205, x5191)).name("x5209").ctrl(x5223).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:67:10") // And(x5205,x5191)
    val x5210 = OpDef(op=FixAdd, inputs=List(x5193, x5195)).name("x5210").ctrl(x5223).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:67:12") // FixAdd(x5193,x5195)
    val x5211 = OpDef(op=MuxOp, inputs=List(x5207, x5210, x5193)).name("x5211").ctrl(x5223).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:67:10") // Mux(x5207,x5210,x5193)
    val x5212 = OpDef(op=BitOr, inputs=List(x5206, x5207)).name("x5212").ctrl(x5223).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:67:10") // Or(x5206,x5207)
    val x5213 = OpDef(op=FixAdd, inputs=List(x5197, x5199)).name("x5213").ctrl(x5223).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:67:12") // FixAdd(x5197,x5199)
    val x5214 = OpDef(op=MuxOp, inputs=List(x5209, x5213, x5197)).name("x5214").ctrl(x5223).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:67:10") // Mux(x5209,x5213,x5197)
    val x5215 = OpDef(op=BitOr, inputs=List(x5208, x5209)).name("x5215").ctrl(x5223).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:67:10") // Or(x5208,x5209)
    val x5216 = OpDef(op=FixAdd, inputs=List(x5211, x5214)).name("x5216").ctrl(x5223).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:67:12") // FixAdd(x5211,x5214)
    val x5217 = OpDef(op=MuxOp, inputs=List(x5215, x5216, x5211)).name("x5217").ctrl(x5223).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:67:10") // Mux(x5215,x5216,x5211)
    val x5218 = OpDef(op=BitOr, inputs=List(x5212, x5215)).name("x5218").ctrl(x5223).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:67:10") // Or(x5212,x5215)
    val x5219 = OpDef(op=FixEql, inputs=List(b2038, Const(0))).name("x5219").ctrl(x5223).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:67:10") // FixEql(b2038,Const(0))
    val x5220 = OpDef(op=FixAdd, inputs=List(x5217, x5201)).name("x5220").ctrl(x5223).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:67:12") // FixAdd(x5217,x5201)
    val x5221 = OpDef(op=MuxOp, inputs=List(x5219, x5217, x5220)).name("x5221").ctrl(x5223).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:67:10") // Mux(x5219,x5217,x5220)
    // x5222.deps=List(x5200)
    val x5222 = StoreBanks(List(x5061_d0_b0, x5061_d1_b0), List(b2192, b2193), x5221).name("x5222").ctrl(x5223).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:67:10") // ParSRAMStore(x5061,List(ArrayBuffer(b2192, b2193)),List(x5221),List(x5191))
    val x5225 = Counter(min=Const(0), max=Const(256), step=Const(1), par=4).name("x5225").ctrl(x5387).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:57:32") // CounterNew(Const(0),Const(256),Const(1),Const(4))
    val x5226 = CounterChain(List(x5225)).name("x5226").ctrl(x5387).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:67:10") // CounterChainNew(List(x5225))
    val x5386 = LoopController(style=MetaPipe, level=OuterControl, cchain=x5226).name("x5386").ctrl(x5387).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:67:10") // UnrolledReduce(List(b1910),x5226,x5062,Block((x5062) => Const(())),List(List(b2231, b2232, b2233, b2234)),List(List(b2235, b2236, b2237, b2238)))
    val b2231 = CounterIter(x5225, Some(0)).name("b2231").ctrl(x5386) // b2231
    val b2235 = Const(true).name("b2235").ctrl(x5386) // b2235
    val b2232 = CounterIter(x5225, Some(1)).name("b2232").ctrl(x5386) // b2232
    val b2236 = Const(true).name("b2236").ctrl(x5386) // b2236
    val b2233 = CounterIter(x5225, Some(2)).name("b2233").ctrl(x5386) // b2233
    val b2237 = Const(true).name("b2237").ctrl(x5386) // b2237
    val b2234 = CounterIter(x5225, Some(3)).name("b2234").ctrl(x5386) // b2234
    val b2238 = Const(true).name("b2238").ctrl(x5386) // b2238
    val x5227_d0_b0 = SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x5227_d0_b0").ctrl(x5386).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:58:32:subTile") // x5227 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x5227_d0_b0) = false
    bufferDepthOf(x5227_d0_b0) = 2
    staticDimsOf(x5227_d0_b0) = List(128)
    val x5227_d1_b0 = SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x5227_d1_b0").ctrl(x5386).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:58:32:subTile") // x5227 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x5227_d1_b0) = false
    bufferDepthOf(x5227_d1_b0) = 2
    staticDimsOf(x5227_d1_b0) = List(128)
    val x5228_d0_b0 = SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x5228_d0_b0").ctrl(x5386).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:58:32:subTile") // x5228 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x5228_d0_b0) = false
    bufferDepthOf(x5228_d0_b0) = 2
    staticDimsOf(x5228_d0_b0) = List(128)
    val x5228_d1_b0 = SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x5228_d1_b0").ctrl(x5386).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:58:32:subTile") // x5228 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x5228_d1_b0) = false
    bufferDepthOf(x5228_d1_b0) = 2
    staticDimsOf(x5228_d1_b0) = List(128)
    val x5229_d0_b0 = SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x5229_d0_b0").ctrl(x5386).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:58:32:subTile") // x5229 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x5229_d0_b0) = false
    bufferDepthOf(x5229_d0_b0) = 2
    staticDimsOf(x5229_d0_b0) = List(128)
    val x5229_d1_b0 = SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x5229_d1_b0").ctrl(x5386).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:58:32:subTile") // x5229 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x5229_d1_b0) = false
    bufferDepthOf(x5229_d1_b0) = 2
    staticDimsOf(x5229_d1_b0) = List(128)
    val x5230_d0_b0 = SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x5230_d0_b0").ctrl(x5386).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:58:32:subTile") // x5230 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x5230_d0_b0) = false
    bufferDepthOf(x5230_d0_b0) = 2
    staticDimsOf(x5230_d0_b0) = List(128)
    val x5230_d1_b0 = SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x5230_d1_b0").ctrl(x5386).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:58:32:subTile") // x5230 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x5230_d1_b0) = false
    bufferDepthOf(x5230_d1_b0) = 2
    staticDimsOf(x5230_d1_b0) = List(128)
    val x5231_d0_b0 = SRAM(size=16384, banking=Strided(banks=16, stride=1)).name("x5231_d0_b0").ctrl(x5386).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:59:34:sigmaTile") // x5231 = SRAMNew(ArrayBuffer(Const(128), Const(128)))
    isAccum(x5231_d0_b0) = false
    bufferDepthOf(x5231_d0_b0) = 2
    staticDimsOf(x5231_d0_b0) = List(128, 128)
    val x5232_d0_b0 = SRAM(size=16384, banking=Strided(banks=16, stride=1)).name("x5232_d0_b0").ctrl(x5386).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:59:34:sigmaTile") // x5232 = SRAMNew(ArrayBuffer(Const(128), Const(128)))
    isAccum(x5232_d0_b0) = false
    bufferDepthOf(x5232_d0_b0) = 2
    staticDimsOf(x5232_d0_b0) = List(128, 128)
    val x5233_d0_b0 = SRAM(size=16384, banking=Strided(banks=16, stride=1)).name("x5233_d0_b0").ctrl(x5386).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:59:34:sigmaTile") // x5233 = SRAMNew(ArrayBuffer(Const(128), Const(128)))
    isAccum(x5233_d0_b0) = false
    bufferDepthOf(x5233_d0_b0) = 2
    staticDimsOf(x5233_d0_b0) = List(128, 128)
    val x5234_d0_b0 = SRAM(size=16384, banking=Strided(banks=16, stride=1)).name("x5234_d0_b0").ctrl(x5386).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:59:34:sigmaTile") // x5234 = SRAMNew(ArrayBuffer(Const(128), Const(128)))
    isAccum(x5234_d0_b0) = false
    bufferDepthOf(x5234_d0_b0) = 2
    staticDimsOf(x5234_d0_b0) = List(128, 128)
    val x5299 = UnitController(style=ForkJoin, level=OuterControl).name("x5299").ctrl(x5386).srcCtx("UnrollingTransformer.scala:431:43") // ParallelPipe(List(b1910),Block(Const(())))
    val x5235 = Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x5235").ctrl(x5299).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:60:21") // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x5236 = CounterChain(List(x5235)).name("x5236").ctrl(x5299).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:60:29") // CounterChainNew(List(x5235))
    val x5250 = LoopController(style=InnerPipe, level=InnerControl, cchain=x5236).name("x5250").ctrl(x5299).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:60:29") // UnrolledForeach(List(b2235, b1910),x5236,Block(Const(())),List(List(b2255)),List(List(b2256)))
    val b2255 = CounterIter(x5235, None).name("b2255").ctrl(x5250) // b2255
    val b2256 = Const(true).name("b2256").ctrl(x5250) // b2256
    val x5237 = OpDef(op=BitAnd, inputs=List(b2256, b2235)).name("x5237").ctrl(x5250).srcCtx("UnrollingBase.scala:28:66") // And(b2256,b2235)
    val x5238 = OpDef(op=BitAnd, inputs=List(x5237, b1910)).name("x5238").ctrl(x5250).srcCtx("UnrollingBase.scala:28:66") // And(x5237,b1910)
    val x5239 = LoadBanks(List(x4961_d0_b0), List(b2231, b2255)).name("x5239").ctrl(x5250).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:61:35") // ParSRAMLoad(x4961,List(List(b2231, b2255)),List(x5238))
    val x5240 = x5239 // x5240 = VectorApply(x5239,0)
    val x5241 = LoadBanks(List(x4959_d0_b0), List(b2231)).name("x5241").ctrl(x5250).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:61:58") // SRAMLoad(x4959,ArrayBuffer(Const(256)),List(b2231),Const(0),x5238)
    val x5242 = OpDef(op=FixEql, inputs=List(x5241, Const(1))).name("x5242").ctrl(x5250).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:61:63") // FixEql(x5241,Const(1))
    val x5243 = LoadBanks(List(x4916_d4_b0), List(b2255)).name("x5243").ctrl(x5250).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:61:76") // ParSRAMLoad(x4916,List(List(b2255)),List(x5238))
    val x5244 = x5243 // x5244 = VectorApply(x5243,0)
    val x5245 = LoadBanks(List(x4915_d4_b0), List(b2255)).name("x5245").ctrl(x5250).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:61:89") // ParSRAMLoad(x4915,List(List(b2255)),List(x5238))
    val x5246 = x5245 // x5246 = VectorApply(x5245,0)
    val x5247 = OpDef(op=MuxOp, inputs=List(x5242, x5244, x5246)).name("x5247").ctrl(x5250).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:61:49") // Mux(x5242,x5244,x5246)
    val x5248 = OpDef(op=FixSub, inputs=List(x5240, x5247)).name("x5248").ctrl(x5250).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:61:44") // FixSub(x5240,x5247)
    val x5249 = StoreBanks(List(x5227_d0_b0, x5227_d1_b0), List(b2255), x5248).name("x5249").ctrl(x5250).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:61:25") // ParSRAMStore(x5227,List(List(b2255)),List(x5248),List(x5238))
    val x5251 = Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x5251").ctrl(x5299).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:60:21") // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x5252 = CounterChain(List(x5251)).name("x5252").ctrl(x5299).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:60:29") // CounterChainNew(List(x5251))
    val x5266 = LoopController(style=InnerPipe, level=InnerControl, cchain=x5252).name("x5266").ctrl(x5299).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:60:29") // UnrolledForeach(List(b2236, b1910),x5252,Block(Const(())),List(List(b2271)),List(List(b2272)))
    val b2271 = CounterIter(x5251, None).name("b2271").ctrl(x5266) // b2271
    val b2272 = Const(true).name("b2272").ctrl(x5266) // b2272
    val x5253 = OpDef(op=BitAnd, inputs=List(b2272, b2236)).name("x5253").ctrl(x5266).srcCtx("UnrollingBase.scala:28:66") // And(b2272,b2236)
    val x5254 = OpDef(op=BitAnd, inputs=List(x5253, b1910)).name("x5254").ctrl(x5266).srcCtx("UnrollingBase.scala:28:66") // And(x5253,b1910)
    val x5255 = LoadBanks(List(x4961_d0_b1), List(b2232, b2271)).name("x5255").ctrl(x5266).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:61:35") // ParSRAMLoad(x4961,List(List(b2232, b2271)),List(x5254))
    val x5256 = x5255 // x5256 = VectorApply(x5255,0)
    val x5257 = LoadBanks(List(x4959_d0_b0), List(b2232)).name("x5257").ctrl(x5266).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:61:58") // SRAMLoad(x4959,ArrayBuffer(Const(256)),List(b2232),Const(0),x5254)
    val x5258 = OpDef(op=FixEql, inputs=List(x5257, Const(1))).name("x5258").ctrl(x5266).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:61:63") // FixEql(x5257,Const(1))
    val x5259 = LoadBanks(List(x4916_d5_b0), List(b2271)).name("x5259").ctrl(x5266).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:61:76") // ParSRAMLoad(x4916,List(List(b2271)),List(x5254))
    val x5260 = x5259 // x5260 = VectorApply(x5259,0)
    val x5261 = LoadBanks(List(x4915_d5_b0), List(b2271)).name("x5261").ctrl(x5266).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:61:89") // ParSRAMLoad(x4915,List(List(b2271)),List(x5254))
    val x5262 = x5261 // x5262 = VectorApply(x5261,0)
    val x5263 = OpDef(op=MuxOp, inputs=List(x5258, x5260, x5262)).name("x5263").ctrl(x5266).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:61:49") // Mux(x5258,x5260,x5262)
    val x5264 = OpDef(op=FixSub, inputs=List(x5256, x5263)).name("x5264").ctrl(x5266).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:61:44") // FixSub(x5256,x5263)
    val x5265 = StoreBanks(List(x5228_d0_b0, x5228_d1_b0), List(b2271), x5264).name("x5265").ctrl(x5266).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:61:25") // ParSRAMStore(x5228,List(List(b2271)),List(x5264),List(x5254))
    val x5267 = Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x5267").ctrl(x5299).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:60:21") // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x5268 = CounterChain(List(x5267)).name("x5268").ctrl(x5299).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:60:29") // CounterChainNew(List(x5267))
    val x5282 = LoopController(style=InnerPipe, level=InnerControl, cchain=x5268).name("x5282").ctrl(x5299).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:60:29") // UnrolledForeach(List(b2237, b1910),x5268,Block(Const(())),List(List(b2287)),List(List(b2288)))
    val b2287 = CounterIter(x5267, None).name("b2287").ctrl(x5282) // b2287
    val b2288 = Const(true).name("b2288").ctrl(x5282) // b2288
    val x5269 = OpDef(op=BitAnd, inputs=List(b2288, b2237)).name("x5269").ctrl(x5282).srcCtx("UnrollingBase.scala:28:66") // And(b2288,b2237)
    val x5270 = OpDef(op=BitAnd, inputs=List(x5269, b1910)).name("x5270").ctrl(x5282).srcCtx("UnrollingBase.scala:28:66") // And(x5269,b1910)
    val x5271 = LoadBanks(List(x4961_d0_b2), List(b2233, b2287)).name("x5271").ctrl(x5282).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:61:35") // ParSRAMLoad(x4961,List(List(b2233, b2287)),List(x5270))
    val x5272 = x5271 // x5272 = VectorApply(x5271,0)
    val x5273 = LoadBanks(List(x4959_d0_b0), List(b2233)).name("x5273").ctrl(x5282).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:61:58") // SRAMLoad(x4959,ArrayBuffer(Const(256)),List(b2233),Const(0),x5270)
    val x5274 = OpDef(op=FixEql, inputs=List(x5273, Const(1))).name("x5274").ctrl(x5282).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:61:63") // FixEql(x5273,Const(1))
    val x5275 = LoadBanks(List(x4916_d6_b0), List(b2287)).name("x5275").ctrl(x5282).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:61:76") // ParSRAMLoad(x4916,List(List(b2287)),List(x5270))
    val x5276 = x5275 // x5276 = VectorApply(x5275,0)
    val x5277 = LoadBanks(List(x4915_d6_b0), List(b2287)).name("x5277").ctrl(x5282).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:61:89") // ParSRAMLoad(x4915,List(List(b2287)),List(x5270))
    val x5278 = x5277 // x5278 = VectorApply(x5277,0)
    val x5279 = OpDef(op=MuxOp, inputs=List(x5274, x5276, x5278)).name("x5279").ctrl(x5282).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:61:49") // Mux(x5274,x5276,x5278)
    val x5280 = OpDef(op=FixSub, inputs=List(x5272, x5279)).name("x5280").ctrl(x5282).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:61:44") // FixSub(x5272,x5279)
    val x5281 = StoreBanks(List(x5229_d0_b0, x5229_d1_b0), List(b2287), x5280).name("x5281").ctrl(x5282).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:61:25") // ParSRAMStore(x5229,List(List(b2287)),List(x5280),List(x5270))
    val x5283 = Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x5283").ctrl(x5299).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:60:21") // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x5284 = CounterChain(List(x5283)).name("x5284").ctrl(x5299).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:60:29") // CounterChainNew(List(x5283))
    val x5298 = LoopController(style=InnerPipe, level=InnerControl, cchain=x5284).name("x5298").ctrl(x5299).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:60:29") // UnrolledForeach(List(b2238, b1910),x5284,Block(Const(())),List(List(b2303)),List(List(b2304)))
    val b2303 = CounterIter(x5283, None).name("b2303").ctrl(x5298) // b2303
    val b2304 = Const(true).name("b2304").ctrl(x5298) // b2304
    val x5285 = OpDef(op=BitAnd, inputs=List(b2304, b2238)).name("x5285").ctrl(x5298).srcCtx("UnrollingBase.scala:28:66") // And(b2304,b2238)
    val x5286 = OpDef(op=BitAnd, inputs=List(x5285, b1910)).name("x5286").ctrl(x5298).srcCtx("UnrollingBase.scala:28:66") // And(x5285,b1910)
    val x5287 = LoadBanks(List(x4961_d0_b3), List(b2234, b2303)).name("x5287").ctrl(x5298).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:61:35") // ParSRAMLoad(x4961,List(List(b2234, b2303)),List(x5286))
    val x5288 = x5287 // x5288 = VectorApply(x5287,0)
    val x5289 = LoadBanks(List(x4959_d0_b0), List(b2234)).name("x5289").ctrl(x5298).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:61:58") // SRAMLoad(x4959,ArrayBuffer(Const(256)),List(b2234),Const(0),x5286)
    val x5290 = OpDef(op=FixEql, inputs=List(x5289, Const(1))).name("x5290").ctrl(x5298).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:61:63") // FixEql(x5289,Const(1))
    val x5291 = LoadBanks(List(x4916_d7_b0), List(b2303)).name("x5291").ctrl(x5298).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:61:76") // ParSRAMLoad(x4916,List(List(b2303)),List(x5286))
    val x5292 = x5291 // x5292 = VectorApply(x5291,0)
    val x5293 = LoadBanks(List(x4915_d7_b0), List(b2303)).name("x5293").ctrl(x5298).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:61:89") // ParSRAMLoad(x4915,List(List(b2303)),List(x5286))
    val x5294 = x5293 // x5294 = VectorApply(x5293,0)
    val x5295 = OpDef(op=MuxOp, inputs=List(x5290, x5292, x5294)).name("x5295").ctrl(x5298).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:61:49") // Mux(x5290,x5292,x5294)
    val x5296 = OpDef(op=FixSub, inputs=List(x5288, x5295)).name("x5296").ctrl(x5298).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:61:44") // FixSub(x5288,x5295)
    val x5297 = StoreBanks(List(x5230_d0_b0, x5230_d1_b0), List(b2303), x5296).name("x5297").ctrl(x5298).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:61:25") // ParSRAMStore(x5230,List(List(b2303)),List(x5296),List(x5286))
    val x5348 = UnitController(style=ForkJoin, level=OuterControl).name("x5348").ctrl(x5386).srcCtx("UnrollingTransformer.scala:431:43") // ParallelPipe(List(b1910),Block(Const(())))
    val x5300 = Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x5300").ctrl(x5348).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:63:29") // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x5301 = Counter(min=Const(0), max=Const(128), step=Const(1), par=1).name("x5301").ctrl(x5348).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:63:21") // CounterNew(Const(0),Const(128),Const(1),Const(1))
    val x5302 = CounterChain(List(x5301,x5300)).name("x5302").ctrl(x5348).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:63:37") // CounterChainNew(List(x5301, x5300))
    val x5311 = LoopController(style=InnerPipe, level=InnerControl, cchain=x5302).name("x5311").ctrl(x5348).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:63:37") // UnrolledForeach(List(b2235, b1910),x5302,Block(Const(())),List(List(b2332), List(b2333)),List(List(b2334), List(b2335)))
    val b2332 = CounterIter(x5301, Some(0)).name("b2332").ctrl(x5311) // b2332
    val b2334 = Const(true).name("b2334").ctrl(x5311) // b2334
    val b2333 = CounterIter(x5300, None).name("b2333").ctrl(x5311) // b2333
    val b2335 = Const(true).name("b2335").ctrl(x5311) // b2335
    val x5303 = OpDef(op=BitAnd, inputs=List(b2334, b2335)).name("x5303").ctrl(x5311).srcCtx("UnrollingBase.scala:28:66") // And(b2334,b2335)
    val x5304 = OpDef(op=BitAnd, inputs=List(b2235, b1910)).name("x5304").ctrl(x5311).srcCtx("UnrollingBase.scala:28:66") // And(b2235,b1910)
    val x5305 = OpDef(op=BitAnd, inputs=List(x5303, x5304)).name("x5305").ctrl(x5311).srcCtx("UnrollingBase.scala:28:66") // And(x5303,x5304)
    val x5306 = LoadBanks(List(x5227_d1_b0), List(b2332)).name("x5306").ctrl(x5311).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:64:40") // SRAMLoad(x5227,ArrayBuffer(Const(128)),List(b2332),Const(0),x5305)
    val x5307 = LoadBanks(List(x5227_d0_b0), List(b2333)).name("x5307").ctrl(x5311).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:64:54") // ParSRAMLoad(x5227,List(List(b2333)),List(x5305))
    val x5308 = x5307 // x5308 = VectorApply(x5307,0)
    val x5309 = OpDef(op=FixMul, inputs=List(x5306, x5308)).name("x5309").ctrl(x5311).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:64:45") // FixMul(x5306,x5308)
    val x5310 = StoreBanks(List(x5231_d0_b0), List(b2332, b2333), x5309).name("x5310").ctrl(x5311).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:64:31") // ParSRAMStore(x5231,List(List(b2332, b2333)),List(x5309),List(x5305))
    val x5312 = Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x5312").ctrl(x5348).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:63:29") // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x5313 = Counter(min=Const(0), max=Const(128), step=Const(1), par=1).name("x5313").ctrl(x5348).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:63:21") // CounterNew(Const(0),Const(128),Const(1),Const(1))
    val x5314 = CounterChain(List(x5313,x5312)).name("x5314").ctrl(x5348).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:63:37") // CounterChainNew(List(x5313, x5312))
    val x5323 = LoopController(style=InnerPipe, level=InnerControl, cchain=x5314).name("x5323").ctrl(x5348).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:63:37") // UnrolledForeach(List(b2236, b1910),x5314,Block(Const(())),List(List(b2345), List(b2346)),List(List(b2347), List(b2348)))
    val b2345 = CounterIter(x5313, Some(0)).name("b2345").ctrl(x5323) // b2345
    val b2347 = Const(true).name("b2347").ctrl(x5323) // b2347
    val b2346 = CounterIter(x5312, None).name("b2346").ctrl(x5323) // b2346
    val b2348 = Const(true).name("b2348").ctrl(x5323) // b2348
    val x5315 = OpDef(op=BitAnd, inputs=List(b2347, b2348)).name("x5315").ctrl(x5323).srcCtx("UnrollingBase.scala:28:66") // And(b2347,b2348)
    val x5316 = OpDef(op=BitAnd, inputs=List(b2236, b1910)).name("x5316").ctrl(x5323).srcCtx("UnrollingBase.scala:28:66") // And(b2236,b1910)
    val x5317 = OpDef(op=BitAnd, inputs=List(x5315, x5316)).name("x5317").ctrl(x5323).srcCtx("UnrollingBase.scala:28:66") // And(x5315,x5316)
    val x5318 = LoadBanks(List(x5228_d1_b0), List(b2345)).name("x5318").ctrl(x5323).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:64:40") // SRAMLoad(x5228,ArrayBuffer(Const(128)),List(b2345),Const(0),x5317)
    val x5319 = LoadBanks(List(x5228_d0_b0), List(b2346)).name("x5319").ctrl(x5323).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:64:54") // ParSRAMLoad(x5228,List(List(b2346)),List(x5317))
    val x5320 = x5319 // x5320 = VectorApply(x5319,0)
    val x5321 = OpDef(op=FixMul, inputs=List(x5318, x5320)).name("x5321").ctrl(x5323).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:64:45") // FixMul(x5318,x5320)
    val x5322 = StoreBanks(List(x5232_d0_b0), List(b2345, b2346), x5321).name("x5322").ctrl(x5323).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:64:31") // ParSRAMStore(x5232,List(List(b2345, b2346)),List(x5321),List(x5317))
    val x5324 = Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x5324").ctrl(x5348).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:63:29") // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x5325 = Counter(min=Const(0), max=Const(128), step=Const(1), par=1).name("x5325").ctrl(x5348).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:63:21") // CounterNew(Const(0),Const(128),Const(1),Const(1))
    val x5326 = CounterChain(List(x5325,x5324)).name("x5326").ctrl(x5348).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:63:37") // CounterChainNew(List(x5325, x5324))
    val x5335 = LoopController(style=InnerPipe, level=InnerControl, cchain=x5326).name("x5335").ctrl(x5348).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:63:37") // UnrolledForeach(List(b2237, b1910),x5326,Block(Const(())),List(List(b2358), List(b2359)),List(List(b2360), List(b2361)))
    val b2358 = CounterIter(x5325, Some(0)).name("b2358").ctrl(x5335) // b2358
    val b2360 = Const(true).name("b2360").ctrl(x5335) // b2360
    val b2359 = CounterIter(x5324, None).name("b2359").ctrl(x5335) // b2359
    val b2361 = Const(true).name("b2361").ctrl(x5335) // b2361
    val x5327 = OpDef(op=BitAnd, inputs=List(b2360, b2361)).name("x5327").ctrl(x5335).srcCtx("UnrollingBase.scala:28:66") // And(b2360,b2361)
    val x5328 = OpDef(op=BitAnd, inputs=List(b2237, b1910)).name("x5328").ctrl(x5335).srcCtx("UnrollingBase.scala:28:66") // And(b2237,b1910)
    val x5329 = OpDef(op=BitAnd, inputs=List(x5327, x5328)).name("x5329").ctrl(x5335).srcCtx("UnrollingBase.scala:28:66") // And(x5327,x5328)
    val x5330 = LoadBanks(List(x5229_d1_b0), List(b2358)).name("x5330").ctrl(x5335).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:64:40") // SRAMLoad(x5229,ArrayBuffer(Const(128)),List(b2358),Const(0),x5329)
    val x5331 = LoadBanks(List(x5229_d0_b0), List(b2359)).name("x5331").ctrl(x5335).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:64:54") // ParSRAMLoad(x5229,List(List(b2359)),List(x5329))
    val x5332 = x5331 // x5332 = VectorApply(x5331,0)
    val x5333 = OpDef(op=FixMul, inputs=List(x5330, x5332)).name("x5333").ctrl(x5335).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:64:45") // FixMul(x5330,x5332)
    val x5334 = StoreBanks(List(x5233_d0_b0), List(b2358, b2359), x5333).name("x5334").ctrl(x5335).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:64:31") // ParSRAMStore(x5233,List(List(b2358, b2359)),List(x5333),List(x5329))
    val x5336 = Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x5336").ctrl(x5348).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:63:29") // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x5337 = Counter(min=Const(0), max=Const(128), step=Const(1), par=1).name("x5337").ctrl(x5348).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:63:21") // CounterNew(Const(0),Const(128),Const(1),Const(1))
    val x5338 = CounterChain(List(x5337,x5336)).name("x5338").ctrl(x5348).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:63:37") // CounterChainNew(List(x5337, x5336))
    val x5347 = LoopController(style=InnerPipe, level=InnerControl, cchain=x5338).name("x5347").ctrl(x5348).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:63:37") // UnrolledForeach(List(b2238, b1910),x5338,Block(Const(())),List(List(b2371), List(b2372)),List(List(b2373), List(b2374)))
    val b2371 = CounterIter(x5337, Some(0)).name("b2371").ctrl(x5347) // b2371
    val b2373 = Const(true).name("b2373").ctrl(x5347) // b2373
    val b2372 = CounterIter(x5336, None).name("b2372").ctrl(x5347) // b2372
    val b2374 = Const(true).name("b2374").ctrl(x5347) // b2374
    val x5339 = OpDef(op=BitAnd, inputs=List(b2373, b2374)).name("x5339").ctrl(x5347).srcCtx("UnrollingBase.scala:28:66") // And(b2373,b2374)
    val x5340 = OpDef(op=BitAnd, inputs=List(b2238, b1910)).name("x5340").ctrl(x5347).srcCtx("UnrollingBase.scala:28:66") // And(b2238,b1910)
    val x5341 = OpDef(op=BitAnd, inputs=List(x5339, x5340)).name("x5341").ctrl(x5347).srcCtx("UnrollingBase.scala:28:66") // And(x5339,x5340)
    val x5342 = LoadBanks(List(x5230_d1_b0), List(b2371)).name("x5342").ctrl(x5347).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:64:40") // SRAMLoad(x5230,ArrayBuffer(Const(128)),List(b2371),Const(0),x5341)
    val x5343 = LoadBanks(List(x5230_d0_b0), List(b2372)).name("x5343").ctrl(x5347).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:64:54") // ParSRAMLoad(x5230,List(List(b2372)),List(x5341))
    val x5344 = x5343 // x5344 = VectorApply(x5343,0)
    val x5345 = OpDef(op=FixMul, inputs=List(x5342, x5344)).name("x5345").ctrl(x5347).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:64:45") // FixMul(x5342,x5344)
    val x5346 = StoreBanks(List(x5234_d0_b0), List(b2371, b2372), x5345).name("x5346").ctrl(x5347).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:64:31") // ParSRAMStore(x5234,List(List(b2371, b2372)),List(x5345),List(x5341))
    val x5349 = Counter(min=Const(0), max=Const(128), step=Const(1), par=1).name("x5349").ctrl(x5386).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:67:10") // CounterNew(Const(0),Const(128),Const(1),Const(1))
    val x5350 = Counter(min=Const(0), max=Const(128), step=Const(1), par=1).name("x5350").ctrl(x5386).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:67:10") // CounterNew(Const(0),Const(128),Const(1),Const(1))
    val x5351 = CounterChain(List(x5350,x5349)).name("x5351").ctrl(x5386).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:67:10") // CounterChainNew(ArrayBuffer(x5350, x5349))
    val x5385 = LoopController(style=InnerPipe, level=InnerControl, cchain=x5351).name("x5385").ctrl(x5386).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:67:10") // UnrolledForeach(List(),x5351,Block(Const(())),ArrayBuffer(List(b2385), List(b2386)),ArrayBuffer(List(b2387), List(b2388)))
    val b2385 = CounterIter(x5350, Some(0)).name("b2385").ctrl(x5385) // b2385
    val b2387 = Const(true).name("b2387").ctrl(x5385) // b2387
    val b2386 = CounterIter(x5349, None).name("b2386").ctrl(x5385) // b2386
    val b2388 = Const(true).name("b2388").ctrl(x5385) // b2388
    val x5352 = OpDef(op=BitAnd, inputs=List(b2387, b2388)).name("x5352").ctrl(x5385).srcCtx("UnrollingBase.scala:28:66") // And(b2387,b2388)
    val x5353 = OpDef(op=BitAnd, inputs=List(x5352, b1910)).name("x5353").ctrl(x5385).srcCtx("UnrollingBase.scala:28:66") // And(x5352,b1910)
    val x5354 = LoadBanks(List(x5231_d0_b0), List(b2385, b2386)).name("x5354").ctrl(x5385).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:67:10") // ParSRAMLoad(x5231,List(ArrayBuffer(b2385, b2386)),List(x5353))
    val x5355 = x5354 // x5355 = VectorApply(x5354,0)
    val x5356 = LoadBanks(List(x5232_d0_b0), List(b2385, b2386)).name("x5356").ctrl(x5385).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:67:10") // ParSRAMLoad(x5232,List(ArrayBuffer(b2385, b2386)),List(x5353))
    val x5357 = x5356 // x5357 = VectorApply(x5356,0)
    val x5358 = LoadBanks(List(x5233_d0_b0), List(b2385, b2386)).name("x5358").ctrl(x5385).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:67:10") // ParSRAMLoad(x5233,List(ArrayBuffer(b2385, b2386)),List(x5353))
    val x5359 = x5358 // x5359 = VectorApply(x5358,0)
    val x5360 = LoadBanks(List(x5234_d0_b0), List(b2385, b2386)).name("x5360").ctrl(x5385).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:67:10") // ParSRAMLoad(x5234,List(ArrayBuffer(b2385, b2386)),List(x5353))
    val x5361 = x5360 // x5361 = VectorApply(x5360,0)
    val x5362 = LoadBanks(List(x5062_d1_b0), List(b2385, b2386)).name("x5362").ctrl(x5385).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:67:10") // ParSRAMLoad(x5062,List(ArrayBuffer(b2385, b2386)),List(x5353))
    val x5363 = x5362 // x5363 = VectorApply(x5362,0)
    val x5364 = OpDef(op=BitAnd, inputs=List(b2235, b1910)).name("x5364").ctrl(x5385).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:67:10") // And(b2235,b1910)
    val x5365 = OpDef(op=BitAnd, inputs=List(b2236, b1910)).name("x5365").ctrl(x5385).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:67:10") // And(b2236,b1910)
    val x5366 = OpDef(op=BitAnd, inputs=List(b2237, b1910)).name("x5366").ctrl(x5385).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:67:10") // And(b2237,b1910)
    val x5367 = OpDef(op=BitAnd, inputs=List(b2238, b1910)).name("x5367").ctrl(x5385).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:67:10") // And(b2238,b1910)
    val x5368 = OpDef(op=BitAnd, inputs=List(x5364, x5353)).name("x5368").ctrl(x5385).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:67:10") // And(x5364,x5353)
    val x5369 = OpDef(op=BitAnd, inputs=List(x5365, x5353)).name("x5369").ctrl(x5385).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:67:10") // And(x5365,x5353)
    val x5370 = OpDef(op=BitAnd, inputs=List(x5366, x5353)).name("x5370").ctrl(x5385).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:67:10") // And(x5366,x5353)
    val x5371 = OpDef(op=BitAnd, inputs=List(x5367, x5353)).name("x5371").ctrl(x5385).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:67:10") // And(x5367,x5353)
    val x5372 = OpDef(op=FixAdd, inputs=List(x5355, x5357)).name("x5372").ctrl(x5385).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:67:12") // FixAdd(x5355,x5357)
    val x5373 = OpDef(op=MuxOp, inputs=List(x5369, x5372, x5355)).name("x5373").ctrl(x5385).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:67:10") // Mux(x5369,x5372,x5355)
    val x5374 = OpDef(op=BitOr, inputs=List(x5368, x5369)).name("x5374").ctrl(x5385).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:67:10") // Or(x5368,x5369)
    val x5375 = OpDef(op=FixAdd, inputs=List(x5359, x5361)).name("x5375").ctrl(x5385).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:67:12") // FixAdd(x5359,x5361)
    val x5376 = OpDef(op=MuxOp, inputs=List(x5371, x5375, x5359)).name("x5376").ctrl(x5385).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:67:10") // Mux(x5371,x5375,x5359)
    val x5377 = OpDef(op=BitOr, inputs=List(x5370, x5371)).name("x5377").ctrl(x5385).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:67:10") // Or(x5370,x5371)
    val x5378 = OpDef(op=FixAdd, inputs=List(x5373, x5376)).name("x5378").ctrl(x5385).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:67:12") // FixAdd(x5373,x5376)
    val x5379 = OpDef(op=MuxOp, inputs=List(x5377, x5378, x5373)).name("x5379").ctrl(x5385).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:67:10") // Mux(x5377,x5378,x5373)
    val x5380 = OpDef(op=BitOr, inputs=List(x5374, x5377)).name("x5380").ctrl(x5385).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:67:10") // Or(x5374,x5377)
    val x5381 = OpDef(op=FixEql, inputs=List(b2231, Const(0))).name("x5381").ctrl(x5385).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:67:10") // FixEql(b2231,Const(0))
    val x5382 = OpDef(op=FixAdd, inputs=List(x5379, x5363)).name("x5382").ctrl(x5385).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:67:12") // FixAdd(x5379,x5363)
    val x5383 = OpDef(op=MuxOp, inputs=List(x5381, x5379, x5382)).name("x5383").ctrl(x5385).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:67:10") // Mux(x5381,x5379,x5382)
    // x5384.deps=List(x5362)
    val x5384 = StoreBanks(List(x5062_d0_b0, x5062_d1_b0), List(b2385, b2386), x5383).name("x5384").ctrl(x5385).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:67:10") // ParSRAMStore(x5062,List(ArrayBuffer(b2385, b2386)),List(x5383),List(x5353))
    val x5388 = Counter(min=Const(0), max=Const(128), step=Const(1), par=1).name("x5388").ctrl(x5408).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:68:8") // CounterNew(Const(0),Const(128),Const(1),Const(1))
    val x5389 = Counter(min=Const(0), max=Const(128), step=Const(1), par=1).name("x5389").ctrl(x5408).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:68:8") // CounterNew(Const(0),Const(128),Const(1),Const(1))
    val x5390 = CounterChain(List(x5389,x5388)).name("x5390").ctrl(x5408).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:68:8") // CounterChainNew(ArrayBuffer(x5389, x5388))
    val x5407 = LoopController(style=InnerPipe, level=InnerControl, cchain=x5390).name("x5407").ctrl(x5408).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:68:8") // UnrolledForeach(List(),x5390,Block(Const(())),ArrayBuffer(List(b2425), List(b2426)),ArrayBuffer(List(b2427), List(b2428)))
    val b2425 = CounterIter(x5389, Some(0)).name("b2425").ctrl(x5407) // b2425
    val b2427 = Const(true).name("b2427").ctrl(x5407) // b2427
    val b2426 = CounterIter(x5388, None).name("b2426").ctrl(x5407) // b2426
    val b2428 = Const(true).name("b2428").ctrl(x5407) // b2428
    val x5391 = OpDef(op=BitAnd, inputs=List(b2427, b2428)).name("x5391").ctrl(x5407).srcCtx("UnrollingBase.scala:28:66") // And(b2427,b2428)
    val x5392 = LoadBanks(List(x5061_d0_b0), List(b2425, b2426)).name("x5392").ctrl(x5407).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:68:8") // ParSRAMLoad(x5061,List(ArrayBuffer(b2425, b2426)),List(x5391))
    val x5393 = x5392 // x5393 = VectorApply(x5392,0)
    val x5394 = LoadBanks(List(x5062_d0_b0), List(b2425, b2426)).name("x5394").ctrl(x5407).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:68:8") // ParSRAMLoad(x5062,List(ArrayBuffer(b2425, b2426)),List(x5391))
    val x5395 = x5394 // x5395 = VectorApply(x5394,0)
    val x5396 = LoadBanks(List(x4954_d1_b0), List(b2425, b2426)).name("x5396").ctrl(x5407).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:68:8") // ParSRAMLoad(x4954,List(ArrayBuffer(b2425, b2426)),List(x5391))
    val x5397 = x5396 // x5397 = VectorApply(x5396,0)
    val x5398 = OpDef(op=BitAnd, inputs=List(b1909, x5391)).name("x5398").ctrl(x5407).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:68:8") // And(b1909,x5391)
    val x5399 = OpDef(op=BitAnd, inputs=List(b1910, x5391)).name("x5399").ctrl(x5407).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:68:8") // And(b1910,x5391)
    val x5400 = OpDef(op=FixAdd, inputs=List(x5393, x5395)).name("x5400").ctrl(x5407).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:68:10") // FixAdd(x5393,x5395)
    val x5401 = OpDef(op=MuxOp, inputs=List(x5399, x5400, x5393)).name("x5401").ctrl(x5407).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:68:8") // Mux(x5399,x5400,x5393)
    val x5402 = OpDef(op=BitOr, inputs=List(x5398, x5399)).name("x5402").ctrl(x5407).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:68:8") // Or(x5398,x5399)
    val x5403 = OpDef(op=FixEql, inputs=List(b1907, Const(0))).name("x5403").ctrl(x5407).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:68:8") // FixEql(b1907,Const(0))
    val x5404 = OpDef(op=FixAdd, inputs=List(x5401, x5397)).name("x5404").ctrl(x5407).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:68:10") // FixAdd(x5401,x5397)
    val x5405 = OpDef(op=MuxOp, inputs=List(x5403, x5401, x5404)).name("x5405").ctrl(x5407).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:68:8") // Mux(x5403,x5401,x5404)
    // x5406.deps=List(x5396)
    val x5406 = StoreBanks(List(x4954_d0_b0, x4954_d1_b0), List(b2425, b2426), x5405).name("x5406").ctrl(x5407).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:68:8") // ParSRAMStore(x4954,List(ArrayBuffer(b2425, b2426)),List(x5405),List(x5391))
    val x5409 = Counter(min=Const(0), max=Const(128), step=Const(1), par=1).name("x5409").ctrl(x5437).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:70:36") // CounterNew(Const(0),Const(128),Const(1),Const(1))
    val x5410 = CounterChain(List(x5409)).name("x5410").ctrl(x5437).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:70:36") // CounterChainNew(List(x5409))
    val x5436 = LoopController(style=StreamPipe, level=OuterControl, cchain=x5410).name("x5436").ctrl(x5437).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:70:36") // UnrolledForeach(List(Const(true)),x5410,Block(Const(())),List(List(b2449)),List(List(b2450)))
    val b2449 = CounterIter(x5409, Some(0)).name("b2449").ctrl(x5436) // b2449
    val b2450 = Const(true).name("b2450").ctrl(x5436) // b2450
    val b5513 = StreamOut(field="offset").name("b5513").ctrl(x5436).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:70:36") // x5411 = StreamOutNew(BurstCmdBus)
    isAccum(b5513) = false
    bufferDepthOf(b5513) = 1
    val b5514 = StreamOut(field="size").name("b5514").ctrl(x5436).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:70:36") // x5411 = StreamOutNew(BurstCmdBus)
    isAccum(b5514) = false
    bufferDepthOf(b5514) = 1
    val x5412 = StreamOut(field="data").name("x5412").ctrl(x5436).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:70:36") // x5412 = StreamOutNew(BurstFullDataBus())
    isAccum(x5412) = false
    bufferDepthOf(x5412) = 1
    val x5413 = StreamIn(field="ack").name("x5413").ctrl(x5436).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:70:36") // x5413 = StreamInNew(BurstAckBus)
    isAccum(x5413) = false
    bufferDepthOf(x5413) = 1
    val x5424 = UnitController(style=SeqPipe, level=InnerControl).name("x5424").ctrl(x5436).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:70:36") // UnitPipe(List(b2450),Block(x5423))
    val x5414 = b2449 // FixConvert(b2449,TRUE,_32,_0) (Same Type. No op)
    val x5415 = OpDef(op=FixSla, inputs=List(x5414, Const(7))).name("x5415").ctrl(x5424).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:70:36") // FixLsh(x5414,Const(7))
    val x5416 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x5417 = OpDef(op=FixAdd, inputs=List(x5415, x5416)).name("x5417").ctrl(x5424).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:70:36") // FixAdd(x5415,x5416)
    val x5418 = OpDef(op=FixSla, inputs=List(x5417, Const(2))).name("x5418").ctrl(x5424).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:70:36") // FixLsh(x5417,Const(2))
    val x5419 = x5418 // FixConvert(x5418,TRUE,_64,_0)
    val x5420 = DramAddress(x4910).name("x5420").ctrl(x5424).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:70:36") // GetDRAMAddress(x4910)
    val x5422_x5421 = OpDef(op=FixAdd, inputs=List(x5419, x5420)).name("x5422_x5421").ctrl(x5424).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:70:36") // FixAdd(x5419,x5420)
    // x5422 = SimpleStruct(ArrayBuffer((offset,x5421), (size,Const(512)), (isLoad,Const(false))))
    val x5423_b5515_b5513 = WriteMem(b5513, x5422_x5421).name("x5423_b5515_b5513").ctrl(x5424).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:70:36") // StreamWrite(x5411,x5422,b2450)
    val x5423_b5516_b5514 = WriteMem(b5514, Const(512)).name("x5423_b5516_b5514").ctrl(x5424).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:70:36") // StreamWrite(x5411,x5422,b2450)
    val x5425 = Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x5425").ctrl(x5436).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:70:36") // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x5426 = CounterChain(List(x5425)).name("x5426").ctrl(x5436).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:70:36") // CounterChainNew(List(x5425))
    val x5432 = LoopController(style=InnerPipe, level=InnerControl, cchain=x5426).name("x5432").ctrl(x5436).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:70:36") // UnrolledForeach(List(b2450),x5426,Block(Const(())),List(List(b2467)),List(List(b2468)))
    val b2467 = CounterIter(x5425, None).name("b2467").ctrl(x5432) // b2467
    val b2468 = Const(true).name("b2468").ctrl(x5432) // b2468
    val x5427 = OpDef(op=BitAnd, inputs=List(b2468, b2450)).name("x5427").ctrl(x5432).srcCtx("UnrollingBase.scala:28:66") // And(b2468,b2450)
    val x5428 = LoadBanks(List(x4954_d0_b0), List(b2449, b2467)).name("x5428").ctrl(x5432).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:70:36") // ParSRAMLoad(x4954,List(List(b2449, b2467)),List(x5427))
    val x5430_x5429 = x5428 // x5429 = VectorApply(x5428,0)
    // x5430 = SimpleStruct(ArrayBuffer((_1,x5429), (_2,Const(true))))
    val x5431_x5431_x5412 = WriteMem(x5412, x5430_x5429).name("x5431_x5431_x5412").ctrl(x5432).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:70:36") // ParStreamWrite(x5412,List(x5430),List(x5427))
    val x5433 = FringeDenseStore(dram=List(x4910), cmdStream=List(b5513, b5514), dataStream=List(x5412), ackStream=List(x5413)).name("x5433").ctrl(x5436).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:70:36") // FringeDenseStore(x4910,x5411,x5412,x5413)
    val x5435 = UnitController(style=SeqPipe, level=InnerControl).name("x5435").ctrl(x5436).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:70:36") // UnitPipe(List(b2450),Block(Const(())))
    val x5434_x5434 = ReadMem(x5413).name("x5434_x5434").ctrl(x5435).srcCtx("GDA__C_128_R_4096_ts_256_op_2_mp_4.scala:70:36") // StreamRead(x5413,b2450)
    }; split1
    
  }
}
