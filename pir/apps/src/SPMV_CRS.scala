import pir._
import pir.node._
import arch._
import prism.enums._

object SPMV_CRS extends PIRApp {
  def main(implicit design:PIRDesign) = {
    import design.pirmeta._
    val x4861 = DRAM(dims=List(Const(1666))).name("x4861").ctrl(top).srcCtx("SPMV_CRS.scala:43:30:values_dram") // x4861 = DRAMNew(ArrayBuffer(Const(1666)),Const(0))
    val x4862 = DRAM(dims=List(Const(1666))).name("x4862").ctrl(top).srcCtx("SPMV_CRS.scala:44:30:cols_dram") // x4862 = DRAMNew(ArrayBuffer(Const(1666)),Const(0))
    val x4863 = DRAM(dims=List(Const(495))).name("x4863").ctrl(top).srcCtx("SPMV_CRS.scala:45:31:rowid_dram") // x4863 = DRAMNew(ArrayBuffer(Const(495)),Const(0))
    val x4864 = DRAM(dims=List(Const(494))).name("x4864").ctrl(top).srcCtx("SPMV_CRS.scala:46:27:vec_dram") // x4864 = DRAMNew(ArrayBuffer(Const(494)),Const(0))
    val x4865 = DRAM(dims=List(Const(494))).name("x4865").ctrl(top).srcCtx("SPMV_CRS.scala:47:30:result_dram") // x4865 = DRAMNew(ArrayBuffer(Const(494)),Const(0))
    val x4866 = ArgIn(init=0).name("x4866").ctrl(top).srcCtx("SPMV_CRS.scala:56:21:size") // ArgInNew(Const(0))
    isAccum(x4866) = false
    bufferDepthOf(x4866) = 1
    boundOf(x4866) = 494
    val x5271 = UnitController(style=SeqPipe, level=OuterControl).name("x5271").ctrl(top).srcCtx("SPMV_CRS.scala:63:11") // Hwblock(Block(Const(())),false)
    val x4883 = Reg(init=Some(0)).name("x4883").ctrl(x5271).srcCtx("SPMV_CRS.scala:63:11") // x4883 = RegNew(Const(0))
    isAccum(x4883) = false
    bufferDepthOf(x4883) = 1
    val x4887 = UnitController(style=SeqPipe, level=InnerControl).name("x4887").ctrl(x5271).srcCtx("SPMV_CRS.scala:63:11") // UnitPipe(List(Const(true)),Block(Const(())))
    val x4884 = ReadMem(x4866).name("x4884").ctrl(x4887).srcCtx("SPMV_CRS.scala:64:15") // RegRead(x4866)
    val x4885 = OpDef(op=FixDiv, inputs=List(x4884, Const(494))).name("x4885").ctrl(x4887).srcCtx("SPMV_CRS.scala:64:19") // FixDiv(x4884,Const(494))
    val x4886_x4883 = WriteMem(x4883, x4885).name("x4886_x4883").ctrl(x4887).srcCtx("SPMV_CRS.scala:63:11") // RegWrite(x4883,x4885,Const(true))
    // x4888.deps=List(x4887, x4883)
    val x4888 = ReadMem(x4883).name("x4888").ctrl(x5271).srcCtx("SPMV_CRS.scala:63:11") // RegRead(x4883)
    val x4889 = Counter(min=Const(0), max=x4888, step=Const(1), par=1).name("x4889").ctrl(x5271).srcCtx("SPMV_CRS.scala:64:34") // CounterNew(Const(0),x4888,Const(1),Const(1))
    val x4890 = CounterChain(List(x4889)).name("x4890").ctrl(x5271).srcCtx("SPMV_CRS.scala:64:48") // CounterChainNew(List(x4889))
    val x5270 = LoopController(style=MetaPipe, level=OuterControl, cchain=x4890).name("x5270").ctrl(x5271).srcCtx("SPMV_CRS.scala:64:48") // UnrolledForeach(List(Const(true)),x4890,Block(Const(())),List(List(b2650)),List(List(b2651)))
    val b2650 = CounterIter(x4889, Some(0)).name("b2650").ctrl(x5270) // b2650
    val b2651 = Const(true).name("b2651").ctrl(x5270) // b2651
    val x4891_d0_b0 = SRAM(size=495, banking=Strided(banks=16, stride=1)).name("x4891_d0_b0").ctrl(x5270).srcCtx("SPMV_CRS.scala:65:35:rowid_sram") // x4891 = SRAMNew(ArrayBuffer(Const(495)))
    isAccum(x4891_d0_b0) = false
    bufferDepthOf(x4891_d0_b0) = 2
    val x4891_d1_b0 = SRAM(size=495, banking=Strided(banks=16, stride=1)).name("x4891_d1_b0").ctrl(x5270).srcCtx("SPMV_CRS.scala:65:35:rowid_sram") // x4891 = SRAMNew(ArrayBuffer(Const(495)))
    isAccum(x4891_d1_b0) = false
    bufferDepthOf(x4891_d1_b0) = 2
    val x4892_d0_b0 = SRAM(size=494, banking=Strided(banks=1, stride=1)).name("x4892_d0_b0").ctrl(x5270).srcCtx("SPMV_CRS.scala:66:34:result_sram") // x4892 = SRAMNew(ArrayBuffer(Const(494)))
    isAccum(x4892_d0_b0) = false
    bufferDepthOf(x4892_d0_b0) = 3
    val x4893 = Reg(init=Some(0)).name("x4893").ctrl(x5270).srcCtx("SPMV_CRS.scala:64:48") // x4893 = RegNew(Const(0))
    isAccum(x4893) = false
    bufferDepthOf(x4893) = 2
    val x4894 = Reg(init=Some(0)).name("x4894").ctrl(x5270).srcCtx("SPMV_CRS.scala:64:48") // x4894 = RegNew(Const(0))
    isAccum(x4894) = false
    bufferDepthOf(x4894) = 4
    val x4895 = Reg(init=Some(0)).name("x4895").ctrl(x5270).srcCtx("SPMV_CRS.scala:64:48") // x4895 = RegNew(Const(0))
    isAccum(x4895) = false
    bufferDepthOf(x4895) = 2
    val x4903 = UnitController(style=SeqPipe, level=InnerControl).name("x4903").ctrl(x5270).srcCtx("SPMV_CRS.scala:64:48") // UnitPipe(List(b2651),Block(Const(())))
    val x4896 = OpDef(op=FixMul, inputs=List(b2650, Const(495))).name("x4896").ctrl(x4903).srcCtx("SPMV_CRS.scala:68:37:x$1") // FixMul(b2650,Const(495))
    val x4897 = OpDef(op=FixAdd, inputs=List(b2650, Const(1))).name("x4897").ctrl(x4903).srcCtx("SPMV_CRS.scala:68:56") // FixAdd(b2650,Const(1))
    val x4898 = OpDef(op=FixMul, inputs=List(x4897, Const(495))).name("x4898").ctrl(x4903).srcCtx("SPMV_CRS.scala:68:59") // FixMul(x4897,Const(495))
    val x4899 = OpDef(op=FixSub, inputs=List(x4898, x4896)).name("x4899").ctrl(x4903).srcCtx("SPMV_CRS.scala:68:20") // FixSub(x4898,x4896)
    val x4900_x4893 = WriteMem(x4893, x4896).name("x4900_x4893").ctrl(x4903).srcCtx("SPMV_CRS.scala:64:48") // RegWrite(x4893,x4896,b2651)
    val x4901_x4894 = WriteMem(x4894, x4897).name("x4901_x4894").ctrl(x4903).srcCtx("SPMV_CRS.scala:64:48") // RegWrite(x4894,x4897,b2651)
    val x4902_x4895 = WriteMem(x4895, x4899).name("x4902_x4895").ctrl(x4903).srcCtx("SPMV_CRS.scala:64:48") // RegWrite(x4895,x4899,b2651)
    val x4967 = UnitController(style=StreamPipe, level=OuterControl).name("x4967").ctrl(x5270).srcCtx("SPMV_CRS.scala:68:20") // UnitPipe(List(b2651),Block(Const(())))
    val b5308 = StreamOut(field="offset").name("b5308").ctrl(x4967).srcCtx("SPMV_CRS.scala:68:20") // x4904 = StreamOutNew(BurstCmdBus)
    isAccum(b5308) = false
    bufferDepthOf(b5308) = 1
    val b5309 = StreamOut(field="size").name("b5309").ctrl(x4967).srcCtx("SPMV_CRS.scala:68:20") // x4904 = StreamOutNew(BurstCmdBus)
    isAccum(b5309) = false
    bufferDepthOf(b5309) = 1
    val b5310 = FIFO(size=16).name("b5310").ctrl(x4967).srcCtx("SPMV_CRS.scala:68:20") // x4905 = FIFONew(Const(16))
    isAccum(b5310) = false
    bufferDepthOf(b5310) = 2
    val b5311 = FIFO(size=16).name("b5311").ctrl(x4967).srcCtx("SPMV_CRS.scala:68:20") // x4905 = FIFONew(Const(16))
    isAccum(b5311) = false
    bufferDepthOf(b5311) = 2
    val b5312 = FIFO(size=16).name("b5312").ctrl(x4967).srcCtx("SPMV_CRS.scala:68:20") // x4905 = FIFONew(Const(16))
    isAccum(b5312) = false
    bufferDepthOf(b5312) = 2
    val x4906 = StreamIn(field="data").name("x4906").ctrl(x4967).srcCtx("SPMV_CRS.scala:68:20") // x4906 = StreamInNew(BurstDataBus())
    isAccum(x4906) = false
    bufferDepthOf(x4906) = 1
    val x4938 = UnitController(style=SeqPipe, level=InnerControl).name("x4938").ctrl(x4967).srcCtx("SPMV_CRS.scala:68:20") // UnitPipe(List(b2651),Block(x4937))
    val x4907 = ReadMem(x4893).name("x4907").ctrl(x4938).srcCtx("SPMV_CRS.scala:64:48") // RegRead(x4893)
    val x4908 = x4907 // FixConvert(x4907,TRUE,_32,_0) (Same Type. No op)
    val x4909 = OpDef(op=FixSla, inputs=List(x4908, Const(2))).name("x4909").ctrl(x4938).srcCtx("SPMV_CRS.scala:68:20") // FixLsh(x4908,Const(2))
    val x4910 = x4909 // x4910 = DataAsBits(x4909)
    val x4911 = OpDef(op=BitAnd, inputs=List(x4910, Const(31))).name("x4911").ctrl(x4938).srcCtx("SPMV_CRS.scala:68:20") // VectorSlice(x4910,5,0) strMask=00000000000000000000000000011111
    val x4912 = x4911 // x4912 = BitsAsData(x4911,FixPt[TRUE,_32,_0])
    val x4913 = ReadMem(x4895).name("x4913").ctrl(x4938).srcCtx("SPMV_CRS.scala:64:48") // RegRead(x4895)
    val x4914 = OpDef(op=FixSla, inputs=List(x4913, Const(2))).name("x4914").ctrl(x4938).srcCtx("SPMV_CRS.scala:68:20") // FixLsh(x4913,Const(2))
    val x4915 = OpDef(op=FixSub, inputs=List(x4909, x4912)).name("x4915").ctrl(x4938).srcCtx("SPMV_CRS.scala:68:20") // FixSub(x4909,x4912)
    val x4916 = OpDef(op=FixAdd, inputs=List(x4909, x4914)).name("x4916").ctrl(x4938).srcCtx("SPMV_CRS.scala:68:20") // FixAdd(x4909,x4914)
    val x4917 = x4916 // x4917 = DataAsBits(x4916)
    val x4918 = OpDef(op=BitAnd, inputs=List(x4917, Const(31))).name("x4918").ctrl(x4938).srcCtx("SPMV_CRS.scala:68:20") // VectorSlice(x4917,5,0) strMask=00000000000000000000000000011111
    val x4919 = x4918 // x4919 = BitsAsData(x4918,FixPt[TRUE,_32,_0])
    val x4920 = OpDef(op=FixEql, inputs=List(x4919, Const(0))).name("x4920").ctrl(x4938).srcCtx("SPMV_CRS.scala:68:20") // FixEql(x4919,Const(0))
    val x4921 = OpDef(op=FixSub, inputs=List(Const(64), x4919)).name("x4921").ctrl(x4938).srcCtx("SPMV_CRS.scala:68:20") // FixSub(Const(64),x4919)
    val x4922 = OpDef(op=MuxOp, inputs=List(x4920, Const(0), x4921)).name("x4922").ctrl(x4938).srcCtx("SPMV_CRS.scala:68:20") // Mux(x4920,Const(0),x4921)
    val x4936_x4923 = OpDef(op=FixSra, inputs=List(x4912, Const(2))).name("x4936_x4923").ctrl(x4938).srcCtx("SPMV_CRS.scala:68:20") // FixRsh(x4912,Const(2))
    val x4924 = OpDef(op=FixSra, inputs=List(x4922, Const(2))).name("x4924").ctrl(x4938).srcCtx("SPMV_CRS.scala:68:20") // FixRsh(x4922,Const(2))
    val x4936_x4925 = OpDef(op=FixAdd, inputs=List(x4936_x4923, x4913)).name("x4936_x4925").ctrl(x4938).srcCtx("SPMV_CRS.scala:68:20") // FixAdd(x4923,x4913)
    val x4926 = OpDef(op=FixAdd, inputs=List(x4913, x4936_x4923)).name("x4926").ctrl(x4938).srcCtx("SPMV_CRS.scala:68:20") // FixAdd(x4913,x4923)
    val x4936_x4927 = OpDef(op=FixAdd, inputs=List(x4926, x4924)).name("x4936_x4927").ctrl(x4938).srcCtx("SPMV_CRS.scala:68:20") // FixAdd(x4926,x4924)
    val x4928 = OpDef(op=FixAdd, inputs=List(x4914, x4912)).name("x4928").ctrl(x4938).srcCtx("SPMV_CRS.scala:68:20") // FixAdd(x4914,x4912)
    val x4934_x4929 = OpDef(op=FixAdd, inputs=List(x4928, x4922)).name("x4934_x4929").ctrl(x4938).srcCtx("SPMV_CRS.scala:68:20") // FixAdd(x4928,x4922)
    val x4930 = x4915 // FixConvert(x4915,TRUE,_64,_0)
    val x4931 = DramAddress(x4863).name("x4931").ctrl(x4938).srcCtx("SPMV_CRS.scala:68:20") // GetDRAMAddress(x4863)
    val x4932 = OpDef(op=FixAdd, inputs=List(x4930, x4931)).name("x4932").ctrl(x4938).srcCtx("SPMV_CRS.scala:68:20") // FixAdd(x4930,x4931)
    val x4934_x4933 = x4932 // FixConvert(x4932,TRUE,_64,_0) (Same Type. No op)
    // x4934 = SimpleStruct(ArrayBuffer((offset,x4933), (size,x4929), (isLoad,Const(true))))
    val x4935_b5313_b5308 = WriteMem(b5308, x4934_x4933).name("x4935_b5313_b5308").ctrl(x4938).srcCtx("SPMV_CRS.scala:68:20") // StreamWrite(x4904,x4934,b2651)
    val x4935_b5314_b5309 = WriteMem(b5309, x4934_x4929).name("x4935_b5314_b5309").ctrl(x4938).srcCtx("SPMV_CRS.scala:68:20") // StreamWrite(x4904,x4934,b2651)
    // x4936 = SimpleStruct(ArrayBuffer((size,x4927), (start,x4923), (end,x4925)))
    val x4937_b5315_b5310 = WriteMem(b5310, x4936_x4927).name("x4937_b5315_b5310").ctrl(x4938).srcCtx("SPMV_CRS.scala:68:20") // FIFOEnq(x4905,x4936,b2651)
    val x4937_b5316_b5311 = WriteMem(b5311, x4936_x4923).name("x4937_b5316_b5311").ctrl(x4938).srcCtx("SPMV_CRS.scala:68:20") // FIFOEnq(x4905,x4936,b2651)
    val x4937_b5317_b5312 = WriteMem(b5312, x4936_x4925).name("x4937_b5317_b5312").ctrl(x4938).srcCtx("SPMV_CRS.scala:68:20") // FIFOEnq(x4905,x4936,b2651)
    val x4939 = FringeDenseLoad(dram=List(x4863), cmdStream=List(b5308, b5309), dataStream=List(x4906)).name("x4939").ctrl(x4967).srcCtx("SPMV_CRS.scala:68:20") // FringeDenseLoad(x4863,x4904,x4906)
    val x4966 = UnitController(style=SeqPipe, level=OuterControl).name("x4966").ctrl(x4967).srcCtx("SPMV_CRS.scala:68:20") // UnitPipe(List(b2651),Block(Const(())))
    val x4940 = Reg(init=Some(0)).name("x4940").ctrl(x4966).srcCtx("SPMV_CRS.scala:68:20") // x4940 = RegNew(Const(0))
    isAccum(x4940) = false
    bufferDepthOf(x4940) = 1
    val x4941 = Reg(init=Some(0)).name("x4941").ctrl(x4966).srcCtx("SPMV_CRS.scala:68:20") // x4941 = RegNew(Const(0))
    isAccum(x4941) = false
    bufferDepthOf(x4941) = 1
    val x4942 = Reg(init=Some(0)).name("x4942").ctrl(x4966).srcCtx("SPMV_CRS.scala:68:20") // x4942 = RegNew(Const(0))
    isAccum(x4942) = false
    bufferDepthOf(x4942) = 1
    val x4950 = UnitController(style=SeqPipe, level=InnerControl).name("x4950").ctrl(x4966).srcCtx("SPMV_CRS.scala:68:20") // UnitPipe(List(b2651),Block(x4949))
    val x4943_b5318 = ReadMem(b5310).name("x4943_b5318").ctrl(x4950).srcCtx("SPMV_CRS.scala:68:20") // FIFODeq(x4905,b2651)
    val x4943_b5319 = ReadMem(b5311).name("x4943_b5319").ctrl(x4950).srcCtx("SPMV_CRS.scala:68:20") // FIFODeq(x4905,b2651)
    val x4943_b5320 = ReadMem(b5312).name("x4943_b5320").ctrl(x4950).srcCtx("SPMV_CRS.scala:68:20") // FIFODeq(x4905,b2651)
    val x4944 = x4943_b5319 // x4944 = FieldApply(x4943,start)
    val x4945_x4940 = WriteMem(x4940, x4944).name("x4945_x4940").ctrl(x4950).srcCtx("SPMV_CRS.scala:68:20") // RegWrite(x4940,x4944,b2651)
    val x4946 = x4943_b5320 // x4946 = FieldApply(x4943,end)
    val x4947_x4941 = WriteMem(x4941, x4946).name("x4947_x4941").ctrl(x4950).srcCtx("SPMV_CRS.scala:68:20") // RegWrite(x4941,x4946,b2651)
    val x4948 = x4943_b5318 // x4948 = FieldApply(x4943,size)
    val x4949_x4942 = WriteMem(x4942, x4948).name("x4949_x4942").ctrl(x4950).srcCtx("SPMV_CRS.scala:68:20") // RegWrite(x4942,x4948,b2651)
    // x4951.deps=List(x4950, x4942)
    val x4951 = ReadMem(x4942).name("x4951").ctrl(x4966).srcCtx("SPMV_CRS.scala:68:20") // RegRead(x4942)
    val x4952 = Counter(min=Const(0), max=x4951, step=Const(1), par=16).name("x4952").ctrl(x4966).srcCtx("SPMV_CRS.scala:68:20") // CounterNew(Const(0),x4951,Const(1),Const(16))
    val x4953 = CounterChain(List(x4952)).name("x4953").ctrl(x4966).srcCtx("SPMV_CRS.scala:68:20") // CounterChainNew(List(x4952))
    val x4965 = LoopController(style=InnerPipe, level=InnerControl, cchain=x4953).name("x4965").ctrl(x4966).srcCtx("SPMV_CRS.scala:68:20") // UnrolledForeach(List(b2651),x4953,Block(Const(())),List(List(b2711)),List(List(b2712)))
    val b2711 = CounterIter(x4952, None).name("b2711").ctrl(x4965) // b2711
    val b2712 = Const(true).name("b2712").ctrl(x4965) // b2712
    val x4954 = ReadMem(x4940).name("x4954").ctrl(x4965).srcCtx("SPMV_CRS.scala:68:20") // RegRead(x4940)
    val x4955 = OpDef(op=FixLeq, inputs=List(x4954, b2711)).name("x4955").ctrl(x4965).srcCtx("SPMV_CRS.scala:68:20") // FixLeq(x4954,b2711)
    val x4956 = ReadMem(x4941).name("x4956").ctrl(x4965).srcCtx("SPMV_CRS.scala:68:20") // RegRead(x4941)
    val x4957 = OpDef(op=FixLt, inputs=List(b2711, x4956)).name("x4957").ctrl(x4965).srcCtx("SPMV_CRS.scala:68:20") // FixLt(b2711,x4956)
    val x4958 = OpDef(op=BitAnd, inputs=List(x4955, x4957)).name("x4958").ctrl(x4965).srcCtx("SPMV_CRS.scala:68:20") // And(x4955,x4957)
    val x4959 = OpDef(op=FixSub, inputs=List(b2711, x4954)).name("x4959").ctrl(x4965).srcCtx("SPMV_CRS.scala:68:20") // FixSub(b2711,x4954)
    val x4960 = OpDef(op=BitAnd, inputs=List(b2712, b2651)).name("x4960").ctrl(x4965).srcCtx("UnrollingBase.scala:28:66") // And(b2712,b2651)
    val x4961_x4961 = ReadMem(x4906).name("x4961_x4961").ctrl(x4965).srcCtx("SPMV_CRS.scala:68:20") // ParStreamRead(x4906,List(x4960))
    val x4962_x4962 = x4961_x4961 // x4962 = VectorApply(x4961,0)
    val x4963 = OpDef(op=BitAnd, inputs=List(x4958, x4960)).name("x4963").ctrl(x4965).srcCtx("UnrollingTransformer.scala:239:41") // And(x4958,x4960)
    val x4964 = StoreBanks(List(x4891_d0_b0, x4891_d1_b0), List(x4959), x4962_x4962).name("x4964").ctrl(x4965).srcCtx("SPMV_CRS.scala:68:20") // ParSRAMStore(x4891,List(List(x4959)),List(x4962),List(x4963))
    val x4968 = Counter(min=Const(0), max=Const(494), step=Const(1), par=1).name("x4968").ctrl(x5270).srcCtx("SPMV_CRS.scala:69:31") // CounterNew(Const(0),Const(494),Const(1),Const(1))
    val x4969 = CounterChain(List(x4968)).name("x4969").ctrl(x5270).srcCtx("SPMV_CRS.scala:69:43") // CounterChainNew(List(x4968))
    val x5199 = LoopController(style=MetaPipe, level=OuterControl, cchain=x4969).name("x5199").ctrl(x5270).srcCtx("SPMV_CRS.scala:69:43") // UnrolledForeach(List(b2651),x4969,Block(Const(())),List(List(b2729)),List(List(b2730)))
    val b2729 = CounterIter(x4968, Some(0)).name("b2729").ctrl(x5199) // b2729
    val b2730 = Const(true).name("b2730").ctrl(x5199) // b2730
    val x4970_d0_b0 = SRAM(size=494, banking=Strided(banks=1, stride=1)).name("x4970_d0_b0").ctrl(x5199).srcCtx("SPMV_CRS.scala:70:36:cols_sram") // x4970 = SRAMNew(ArrayBuffer(Const(494)))
    isAccum(x4970_d0_b0) = false
    bufferDepthOf(x4970_d0_b0) = 4
    val x4971_d0_b0 = SRAM(size=494, banking=Strided(banks=2, stride=1)).name("x4971_d0_b0").ctrl(x5199).srcCtx("SPMV_CRS.scala:71:36:values_sram") // x4971 = SRAMNew(ArrayBuffer(Const(494)))
    isAccum(x4971_d0_b0) = false
    bufferDepthOf(x4971_d0_b0) = 5
    val x4972_d0_b0 = SRAM(size=494, banking=Strided(banks=2, stride=1)).name("x4972_d0_b0").ctrl(x5199).srcCtx("SPMV_CRS.scala:72:33:vec_sram") // x4972 = SRAMNew(ArrayBuffer(Const(494)))
    isAccum(x4972_d0_b0) = false
    bufferDepthOf(x4972_d0_b0) = 2
    val x4973_d0 = Reg(init=Some(0)).name("x4973_d0").ctrl(x5199).srcCtx("SPMV_CRS.scala:69:43") // x4973 = RegNew(Const(0))
    isAccum(x4973_d0) = false
    bufferDepthOf(x4973_d0) = 3
    val x4973_d1 = Reg(init=Some(0)).name("x4973_d1").ctrl(x5199).srcCtx("SPMV_CRS.scala:69:43") // x4973 = RegNew(Const(0))
    isAccum(x4973_d1) = false
    bufferDepthOf(x4973_d1) = 2
    val x4973_d2 = Reg(init=Some(0)).name("x4973_d2").ctrl(x5199).srcCtx("SPMV_CRS.scala:69:43") // x4973 = RegNew(Const(0))
    isAccum(x4973_d2) = false
    bufferDepthOf(x4973_d2) = 2
    val x4973_d3 = Reg(init=Some(0)).name("x4973_d3").ctrl(x5199).srcCtx("SPMV_CRS.scala:69:43") // x4973 = RegNew(Const(0))
    isAccum(x4973_d3) = false
    bufferDepthOf(x4973_d3) = 2
    val x4974_d0 = Reg(init=Some(0)).name("x4974_d0").ctrl(x5199).srcCtx("SPMV_CRS.scala:69:43") // x4974 = RegNew(Const(0))
    isAccum(x4974_d0) = false
    bufferDepthOf(x4974_d0) = 3
    val x4974_d1 = Reg(init=Some(0)).name("x4974_d1").ctrl(x5199).srcCtx("SPMV_CRS.scala:69:43") // x4974 = RegNew(Const(0))
    isAccum(x4974_d1) = false
    bufferDepthOf(x4974_d1) = 2
    val x4981 = UnitController(style=SeqPipe, level=InnerControl).name("x4981").ctrl(x5199).srcCtx("SPMV_CRS.scala:69:43") // UnitPipe(List(b2730, b2651),Block(Const(())))
    val x4975 = OpDef(op=BitAnd, inputs=List(b2730, b2651)).name("x4975").ctrl(x4981).srcCtx("UnrollingBase.scala:28:66") // And(b2730,b2651)
    val x4976 = LoadBanks(List(x4891_d1_b0), List(b2729)).name("x4976").ctrl(x4981).srcCtx("SPMV_CRS.scala:74:36:x$3") // SRAMLoad(x4891,ArrayBuffer(Const(495)),List(b2729),Const(0),x4975)
    val x4977 = OpDef(op=FixAdd, inputs=List(b2729, Const(1))).name("x4977").ctrl(x4981).srcCtx("SPMV_CRS.scala:75:37") // FixAdd(b2729,Const(1))
    val x4978 = LoadBanks(List(x4891_d0_b0), List(x4977)).name("x4978").ctrl(x4981).srcCtx("SPMV_CRS.scala:75:35:stop_id") // SRAMLoad(x4891,ArrayBuffer(Const(495)),List(x4977),Const(0),x4975)
    val x4979_x4973_d0 = WriteMem(x4973_d0, x4976).name("x4979_x4973_d0").ctrl(x4981).srcCtx("SPMV_CRS.scala:69:43") // RegWrite(x4973,x4976,x4975)
    val x4979_x4973_d1 = WriteMem(x4973_d1, x4976).name("x4979_x4973_d1").ctrl(x4981).srcCtx("SPMV_CRS.scala:69:43") // RegWrite(x4973,x4976,x4975)
    val x4979_x4973_d2 = WriteMem(x4973_d2, x4976).name("x4979_x4973_d2").ctrl(x4981).srcCtx("SPMV_CRS.scala:69:43") // RegWrite(x4973,x4976,x4975)
    val x4979_x4973_d3 = WriteMem(x4973_d3, x4976).name("x4979_x4973_d3").ctrl(x4981).srcCtx("SPMV_CRS.scala:69:43") // RegWrite(x4973,x4976,x4975)
    val x4980_x4974_d0 = WriteMem(x4974_d0, x4978).name("x4980_x4974_d0").ctrl(x4981).srcCtx("SPMV_CRS.scala:69:43") // RegWrite(x4974,x4978,x4975)
    val x4980_x4974_d1 = WriteMem(x4974_d1, x4978).name("x4980_x4974_d1").ctrl(x4981).srcCtx("SPMV_CRS.scala:69:43") // RegWrite(x4974,x4978,x4975)
    val x5123 = UnitController(style=ForkJoin, level=OuterControl).name("x5123").ctrl(x5199).srcCtx("SPMV_CRS.scala:76:19") // ParallelPipe(List(b2730, b2651),Block(Const(())))
    val x4982_d0 = Reg(init=Some(0)).name("x4982_d0").ctrl(x5123).srcCtx("SPMV_CRS.scala:76:19") // x4982 = RegNew(Const(0))
    isAccum(x4982_d0) = false
    bufferDepthOf(x4982_d0) = 1
    val x4982_d1 = Reg(init=Some(0)).name("x4982_d1").ctrl(x5123).srcCtx("SPMV_CRS.scala:76:19") // x4982 = RegNew(Const(0))
    isAccum(x4982_d1) = false
    bufferDepthOf(x4982_d1) = 1
    val x4988 = UnitController(style=SeqPipe, level=InnerControl).name("x4988").ctrl(x5123).srcCtx("SPMV_CRS.scala:76:19") // UnitPipe(List(b2730, b2651),Block(Const(())))
    val x4983 = ReadMem(x4974_d1).name("x4983").ctrl(x4988).srcCtx("SPMV_CRS.scala:69:43") // RegRead(x4974)
    val x4984 = ReadMem(x4973_d3).name("x4984").ctrl(x4988).srcCtx("SPMV_CRS.scala:69:43") // RegRead(x4973)
    val x4985 = OpDef(op=FixSub, inputs=List(x4983, x4984)).name("x4985").ctrl(x4988).srcCtx("SPMV_CRS.scala:77:23") // FixSub(x4983,x4984)
    val x4986 = OpDef(op=BitAnd, inputs=List(b2730, b2651)).name("x4986").ctrl(x4988).srcCtx("UnrollingBase.scala:28:66") // And(b2730,b2651)
    val x4987_x4982_d0 = WriteMem(x4982_d0, x4985).name("x4987_x4982_d0").ctrl(x4988).srcCtx("SPMV_CRS.scala:76:19") // RegWrite(x4982,x4985,x4986)
    val x4987_x4982_d1 = WriteMem(x4982_d1, x4985).name("x4987_x4982_d1").ctrl(x4988).srcCtx("SPMV_CRS.scala:76:19") // RegWrite(x4982,x4985,x4986)
    val x5055 = UnitController(style=StreamPipe, level=OuterControl).name("x5055").ctrl(x5123).srcCtx("SPMV_CRS.scala:77:23") // UnitPipe(List(b2730, b2651),Block(Const(())))
    val b5321 = StreamOut(field="offset").name("b5321").ctrl(x5055).srcCtx("SPMV_CRS.scala:77:23") // x4989 = StreamOutNew(BurstCmdBus)
    isAccum(b5321) = false
    bufferDepthOf(b5321) = 1
    val b5322 = StreamOut(field="size").name("b5322").ctrl(x5055).srcCtx("SPMV_CRS.scala:77:23") // x4989 = StreamOutNew(BurstCmdBus)
    isAccum(b5322) = false
    bufferDepthOf(b5322) = 1
    val b5323 = FIFO(size=16).name("b5323").ctrl(x5055).srcCtx("SPMV_CRS.scala:77:23") // x4990 = FIFONew(Const(16))
    isAccum(b5323) = false
    bufferDepthOf(b5323) = 2
    val b5324 = FIFO(size=16).name("b5324").ctrl(x5055).srcCtx("SPMV_CRS.scala:77:23") // x4990 = FIFONew(Const(16))
    isAccum(b5324) = false
    bufferDepthOf(b5324) = 2
    val b5325 = FIFO(size=16).name("b5325").ctrl(x5055).srcCtx("SPMV_CRS.scala:77:23") // x4990 = FIFONew(Const(16))
    isAccum(b5325) = false
    bufferDepthOf(b5325) = 2
    val x4991 = StreamIn(field="data").name("x4991").ctrl(x5055).srcCtx("SPMV_CRS.scala:77:23") // x4991 = StreamInNew(BurstDataBus())
    isAccum(x4991) = false
    bufferDepthOf(x4991) = 1
    val x5024 = UnitController(style=SeqPipe, level=InnerControl).name("x5024").ctrl(x5055).srcCtx("SPMV_CRS.scala:77:23") // UnitPipe(List(b2730, b2651),Block(x5023))
    val x4992 = ReadMem(x4973_d2).name("x4992").ctrl(x5024).srcCtx("SPMV_CRS.scala:69:43") // RegRead(x4973)
    val x4993 = x4992 // FixConvert(x4992,TRUE,_32,_0) (Same Type. No op)
    val x4994 = OpDef(op=FixSla, inputs=List(x4993, Const(2))).name("x4994").ctrl(x5024).srcCtx("SPMV_CRS.scala:77:23") // FixLsh(x4993,Const(2))
    val x4995 = x4994 // x4995 = DataAsBits(x4994)
    val x4996 = OpDef(op=BitAnd, inputs=List(x4995, Const(31))).name("x4996").ctrl(x5024).srcCtx("SPMV_CRS.scala:77:23") // VectorSlice(x4995,5,0) strMask=00000000000000000000000000011111
    val x4997 = x4996 // x4997 = BitsAsData(x4996,FixPt[TRUE,_32,_0])
    val x4998 = ReadMem(x4982_d1).name("x4998").ctrl(x5024).srcCtx("SPMV_CRS.scala:76:19") // RegRead(x4982)
    val x4999 = OpDef(op=FixSla, inputs=List(x4998, Const(2))).name("x4999").ctrl(x5024).srcCtx("SPMV_CRS.scala:77:23") // FixLsh(x4998,Const(2))
    val x5000 = OpDef(op=FixSub, inputs=List(x4994, x4997)).name("x5000").ctrl(x5024).srcCtx("SPMV_CRS.scala:77:23") // FixSub(x4994,x4997)
    val x5001 = OpDef(op=FixAdd, inputs=List(x4994, x4999)).name("x5001").ctrl(x5024).srcCtx("SPMV_CRS.scala:77:23") // FixAdd(x4994,x4999)
    val x5002 = x5001 // x5002 = DataAsBits(x5001)
    val x5003 = OpDef(op=BitAnd, inputs=List(x5002, Const(31))).name("x5003").ctrl(x5024).srcCtx("SPMV_CRS.scala:77:23") // VectorSlice(x5002,5,0) strMask=00000000000000000000000000011111
    val x5004 = x5003 // x5004 = BitsAsData(x5003,FixPt[TRUE,_32,_0])
    val x5005 = OpDef(op=FixEql, inputs=List(x5004, Const(0))).name("x5005").ctrl(x5024).srcCtx("SPMV_CRS.scala:77:23") // FixEql(x5004,Const(0))
    val x5006 = OpDef(op=FixSub, inputs=List(Const(64), x5004)).name("x5006").ctrl(x5024).srcCtx("SPMV_CRS.scala:77:23") // FixSub(Const(64),x5004)
    val x5007 = OpDef(op=MuxOp, inputs=List(x5005, Const(0), x5006)).name("x5007").ctrl(x5024).srcCtx("SPMV_CRS.scala:77:23") // Mux(x5005,Const(0),x5006)
    val x5022_x5008 = OpDef(op=FixSra, inputs=List(x4997, Const(2))).name("x5022_x5008").ctrl(x5024).srcCtx("SPMV_CRS.scala:77:23") // FixRsh(x4997,Const(2))
    val x5009 = OpDef(op=FixSra, inputs=List(x5007, Const(2))).name("x5009").ctrl(x5024).srcCtx("SPMV_CRS.scala:77:23") // FixRsh(x5007,Const(2))
    val x5022_x5010 = OpDef(op=FixAdd, inputs=List(x5022_x5008, x4998)).name("x5022_x5010").ctrl(x5024).srcCtx("SPMV_CRS.scala:77:23") // FixAdd(x5008,x4998)
    val x5011 = OpDef(op=FixAdd, inputs=List(x4998, x5022_x5008)).name("x5011").ctrl(x5024).srcCtx("SPMV_CRS.scala:77:23") // FixAdd(x4998,x5008)
    val x5022_x5012 = OpDef(op=FixAdd, inputs=List(x5011, x5009)).name("x5022_x5012").ctrl(x5024).srcCtx("SPMV_CRS.scala:77:23") // FixAdd(x5011,x5009)
    val x5013 = OpDef(op=FixAdd, inputs=List(x4999, x4997)).name("x5013").ctrl(x5024).srcCtx("SPMV_CRS.scala:77:23") // FixAdd(x4999,x4997)
    val x5019_x5014 = OpDef(op=FixAdd, inputs=List(x5013, x5007)).name("x5019_x5014").ctrl(x5024).srcCtx("SPMV_CRS.scala:77:23") // FixAdd(x5013,x5007)
    val x5015 = x5000 // FixConvert(x5000,TRUE,_64,_0)
    val x5016 = DramAddress(x4862).name("x5016").ctrl(x5024).srcCtx("SPMV_CRS.scala:77:23") // GetDRAMAddress(x4862)
    val x5017 = OpDef(op=FixAdd, inputs=List(x5015, x5016)).name("x5017").ctrl(x5024).srcCtx("SPMV_CRS.scala:77:23") // FixAdd(x5015,x5016)
    val x5019_x5018 = x5017 // FixConvert(x5017,TRUE,_64,_0) (Same Type. No op)
    // x5019 = SimpleStruct(ArrayBuffer((offset,x5018), (size,x5014), (isLoad,Const(true))))
    val x5020 = OpDef(op=BitAnd, inputs=List(b2730, b2651)).name("x5020").ctrl(x5024).srcCtx("UnrollingBase.scala:28:66") // And(b2730,b2651)
    val x5021_b5326_b5321 = WriteMem(b5321, x5019_x5018).name("x5021_b5326_b5321").ctrl(x5024).srcCtx("SPMV_CRS.scala:77:23") // StreamWrite(x4989,x5019,x5020)
    val x5021_b5327_b5322 = WriteMem(b5322, x5019_x5014).name("x5021_b5327_b5322").ctrl(x5024).srcCtx("SPMV_CRS.scala:77:23") // StreamWrite(x4989,x5019,x5020)
    // x5022 = SimpleStruct(ArrayBuffer((size,x5012), (start,x5008), (end,x5010)))
    val x5023_b5328_b5323 = WriteMem(b5323, x5022_x5012).name("x5023_b5328_b5323").ctrl(x5024).srcCtx("SPMV_CRS.scala:77:23") // FIFOEnq(x4990,x5022,x5020)
    val x5023_b5329_b5324 = WriteMem(b5324, x5022_x5008).name("x5023_b5329_b5324").ctrl(x5024).srcCtx("SPMV_CRS.scala:77:23") // FIFOEnq(x4990,x5022,x5020)
    val x5023_b5330_b5325 = WriteMem(b5325, x5022_x5010).name("x5023_b5330_b5325").ctrl(x5024).srcCtx("SPMV_CRS.scala:77:23") // FIFOEnq(x4990,x5022,x5020)
    val x5025 = FringeDenseLoad(dram=List(x4862), cmdStream=List(b5321, b5322), dataStream=List(x4991)).name("x5025").ctrl(x5055).srcCtx("SPMV_CRS.scala:77:23") // FringeDenseLoad(x4862,x4989,x4991)
    val x5054 = UnitController(style=SeqPipe, level=OuterControl).name("x5054").ctrl(x5055).srcCtx("SPMV_CRS.scala:77:23") // UnitPipe(List(b2730, b2651),Block(Const(())))
    val x5026 = Reg(init=Some(0)).name("x5026").ctrl(x5054).srcCtx("SPMV_CRS.scala:77:23") // x5026 = RegNew(Const(0))
    isAccum(x5026) = false
    bufferDepthOf(x5026) = 1
    val x5027 = Reg(init=Some(0)).name("x5027").ctrl(x5054).srcCtx("SPMV_CRS.scala:77:23") // x5027 = RegNew(Const(0))
    isAccum(x5027) = false
    bufferDepthOf(x5027) = 1
    val x5028 = Reg(init=Some(0)).name("x5028").ctrl(x5054).srcCtx("SPMV_CRS.scala:77:23") // x5028 = RegNew(Const(0))
    isAccum(x5028) = false
    bufferDepthOf(x5028) = 1
    val x5037 = UnitController(style=SeqPipe, level=InnerControl).name("x5037").ctrl(x5054).srcCtx("SPMV_CRS.scala:77:23") // UnitPipe(List(b2730, b2651),Block(x5036))
    val x5029 = OpDef(op=BitAnd, inputs=List(b2730, b2651)).name("x5029").ctrl(x5037).srcCtx("UnrollingBase.scala:28:66") // And(b2730,b2651)
    val x5030_b5331 = ReadMem(b5323).name("x5030_b5331").ctrl(x5037).srcCtx("SPMV_CRS.scala:77:23") // FIFODeq(x4990,x5029)
    val x5030_b5332 = ReadMem(b5324).name("x5030_b5332").ctrl(x5037).srcCtx("SPMV_CRS.scala:77:23") // FIFODeq(x4990,x5029)
    val x5030_b5333 = ReadMem(b5325).name("x5030_b5333").ctrl(x5037).srcCtx("SPMV_CRS.scala:77:23") // FIFODeq(x4990,x5029)
    val x5031 = x5030_b5332 // x5031 = FieldApply(x5030,start)
    val x5032_x5026 = WriteMem(x5026, x5031).name("x5032_x5026").ctrl(x5037).srcCtx("SPMV_CRS.scala:77:23") // RegWrite(x5026,x5031,x5029)
    val x5033 = x5030_b5333 // x5033 = FieldApply(x5030,end)
    val x5034_x5027 = WriteMem(x5027, x5033).name("x5034_x5027").ctrl(x5037).srcCtx("SPMV_CRS.scala:77:23") // RegWrite(x5027,x5033,x5029)
    val x5035 = x5030_b5331 // x5035 = FieldApply(x5030,size)
    val x5036_x5028 = WriteMem(x5028, x5035).name("x5036_x5028").ctrl(x5037).srcCtx("SPMV_CRS.scala:77:23") // RegWrite(x5028,x5035,x5029)
    // x5038.deps=List(x5037, x5028)
    val x5038 = ReadMem(x5028).name("x5038").ctrl(x5054).srcCtx("SPMV_CRS.scala:77:23") // RegRead(x5028)
    val x5039 = Counter(min=Const(0), max=x5038, step=Const(1), par=1).name("x5039").ctrl(x5054).srcCtx("SPMV_CRS.scala:77:23") // CounterNew(Const(0),x5038,Const(1),Const(1))
    val x5040 = CounterChain(List(x5039)).name("x5040").ctrl(x5054).srcCtx("SPMV_CRS.scala:77:23") // CounterChainNew(List(x5039))
    val x5053 = LoopController(style=InnerPipe, level=InnerControl, cchain=x5040).name("x5053").ctrl(x5054).srcCtx("SPMV_CRS.scala:77:23") // UnrolledForeach(List(b2730, b2651),x5040,Block(Const(())),List(List(b2798)),List(List(b2799)))
    val b2798 = CounterIter(x5039, None).name("b2798").ctrl(x5053) // b2798
    val b2799 = Const(true).name("b2799").ctrl(x5053) // b2799
    val x5041 = ReadMem(x5026).name("x5041").ctrl(x5053).srcCtx("SPMV_CRS.scala:77:23") // RegRead(x5026)
    val x5042 = OpDef(op=FixLeq, inputs=List(x5041, b2798)).name("x5042").ctrl(x5053).srcCtx("SPMV_CRS.scala:77:23") // FixLeq(x5041,b2798)
    val x5043 = ReadMem(x5027).name("x5043").ctrl(x5053).srcCtx("SPMV_CRS.scala:77:23") // RegRead(x5027)
    val x5044 = OpDef(op=FixLt, inputs=List(b2798, x5043)).name("x5044").ctrl(x5053).srcCtx("SPMV_CRS.scala:77:23") // FixLt(b2798,x5043)
    val x5045 = OpDef(op=BitAnd, inputs=List(x5042, x5044)).name("x5045").ctrl(x5053).srcCtx("SPMV_CRS.scala:77:23") // And(x5042,x5044)
    val x5046 = OpDef(op=FixSub, inputs=List(b2798, x5041)).name("x5046").ctrl(x5053).srcCtx("SPMV_CRS.scala:77:23") // FixSub(b2798,x5041)
    val x5047 = OpDef(op=BitAnd, inputs=List(b2799, b2730)).name("x5047").ctrl(x5053).srcCtx("UnrollingBase.scala:28:66") // And(b2799,b2730)
    val x5048 = OpDef(op=BitAnd, inputs=List(x5047, b2651)).name("x5048").ctrl(x5053).srcCtx("UnrollingBase.scala:28:66") // And(x5047,b2651)
    val x5049_x5049 = ReadMem(x4991).name("x5049_x5049").ctrl(x5053).srcCtx("SPMV_CRS.scala:77:23") // ParStreamRead(x4991,List(x5048))
    val x5050_x5050 = x5049_x5049 // x5050 = VectorApply(x5049,0)
    val x5051 = OpDef(op=BitAnd, inputs=List(x5045, x5048)).name("x5051").ctrl(x5053).srcCtx("UnrollingTransformer.scala:239:41") // And(x5045,x5048)
    val x5052 = StoreBanks(List(x4970_d0_b0), List(x5046), x5050_x5050).name("x5052").ctrl(x5053).srcCtx("SPMV_CRS.scala:77:23") // ParSRAMStore(x4970,List(List(x5046)),List(x5050),List(x5051))
    val x5122 = UnitController(style=StreamPipe, level=OuterControl).name("x5122").ctrl(x5123).srcCtx("SPMV_CRS.scala:78:25") // UnitPipe(List(b2730, b2651),Block(Const(())))
    val b5334 = StreamOut(field="offset").name("b5334").ctrl(x5122).srcCtx("SPMV_CRS.scala:78:25") // x5056 = StreamOutNew(BurstCmdBus)
    isAccum(b5334) = false
    bufferDepthOf(b5334) = 1
    val b5335 = StreamOut(field="size").name("b5335").ctrl(x5122).srcCtx("SPMV_CRS.scala:78:25") // x5056 = StreamOutNew(BurstCmdBus)
    isAccum(b5335) = false
    bufferDepthOf(b5335) = 1
    val b5336 = FIFO(size=16).name("b5336").ctrl(x5122).srcCtx("SPMV_CRS.scala:78:25") // x5057 = FIFONew(Const(16))
    isAccum(b5336) = false
    bufferDepthOf(b5336) = 2
    val b5337 = FIFO(size=16).name("b5337").ctrl(x5122).srcCtx("SPMV_CRS.scala:78:25") // x5057 = FIFONew(Const(16))
    isAccum(b5337) = false
    bufferDepthOf(b5337) = 2
    val b5338 = FIFO(size=16).name("b5338").ctrl(x5122).srcCtx("SPMV_CRS.scala:78:25") // x5057 = FIFONew(Const(16))
    isAccum(b5338) = false
    bufferDepthOf(b5338) = 2
    val x5058 = StreamIn(field="data").name("x5058").ctrl(x5122).srcCtx("SPMV_CRS.scala:78:25") // x5058 = StreamInNew(BurstDataBus())
    isAccum(x5058) = false
    bufferDepthOf(x5058) = 1
    val x5091 = UnitController(style=SeqPipe, level=InnerControl).name("x5091").ctrl(x5122).srcCtx("SPMV_CRS.scala:78:25") // UnitPipe(List(b2730, b2651),Block(x5090))
    val x5059 = ReadMem(x4973_d1).name("x5059").ctrl(x5091).srcCtx("SPMV_CRS.scala:69:43") // RegRead(x4973)
    val x5060 = x5059 // FixConvert(x5059,TRUE,_32,_0) (Same Type. No op)
    val x5061 = OpDef(op=FixSla, inputs=List(x5060, Const(2))).name("x5061").ctrl(x5091).srcCtx("SPMV_CRS.scala:78:25") // FixLsh(x5060,Const(2))
    val x5062 = x5061 // x5062 = DataAsBits(x5061)
    val x5063 = OpDef(op=BitAnd, inputs=List(x5062, Const(31))).name("x5063").ctrl(x5091).srcCtx("SPMV_CRS.scala:78:25") // VectorSlice(x5062,5,0) strMask=00000000000000000000000000011111
    val x5064 = x5063 // x5064 = BitsAsData(x5063,FixPt[TRUE,_32,_0])
    val x5065 = ReadMem(x4982_d0).name("x5065").ctrl(x5091).srcCtx("SPMV_CRS.scala:76:19") // RegRead(x4982)
    val x5066 = OpDef(op=FixSla, inputs=List(x5065, Const(2))).name("x5066").ctrl(x5091).srcCtx("SPMV_CRS.scala:78:25") // FixLsh(x5065,Const(2))
    val x5067 = OpDef(op=FixSub, inputs=List(x5061, x5064)).name("x5067").ctrl(x5091).srcCtx("SPMV_CRS.scala:78:25") // FixSub(x5061,x5064)
    val x5068 = OpDef(op=FixAdd, inputs=List(x5061, x5066)).name("x5068").ctrl(x5091).srcCtx("SPMV_CRS.scala:78:25") // FixAdd(x5061,x5066)
    val x5069 = x5068 // x5069 = DataAsBits(x5068)
    val x5070 = OpDef(op=BitAnd, inputs=List(x5069, Const(31))).name("x5070").ctrl(x5091).srcCtx("SPMV_CRS.scala:78:25") // VectorSlice(x5069,5,0) strMask=00000000000000000000000000011111
    val x5071 = x5070 // x5071 = BitsAsData(x5070,FixPt[TRUE,_32,_0])
    val x5072 = OpDef(op=FixEql, inputs=List(x5071, Const(0))).name("x5072").ctrl(x5091).srcCtx("SPMV_CRS.scala:78:25") // FixEql(x5071,Const(0))
    val x5073 = OpDef(op=FixSub, inputs=List(Const(64), x5071)).name("x5073").ctrl(x5091).srcCtx("SPMV_CRS.scala:78:25") // FixSub(Const(64),x5071)
    val x5074 = OpDef(op=MuxOp, inputs=List(x5072, Const(0), x5073)).name("x5074").ctrl(x5091).srcCtx("SPMV_CRS.scala:78:25") // Mux(x5072,Const(0),x5073)
    val x5089_x5075 = OpDef(op=FixSra, inputs=List(x5064, Const(2))).name("x5089_x5075").ctrl(x5091).srcCtx("SPMV_CRS.scala:78:25") // FixRsh(x5064,Const(2))
    val x5076 = OpDef(op=FixSra, inputs=List(x5074, Const(2))).name("x5076").ctrl(x5091).srcCtx("SPMV_CRS.scala:78:25") // FixRsh(x5074,Const(2))
    val x5089_x5077 = OpDef(op=FixAdd, inputs=List(x5089_x5075, x5065)).name("x5089_x5077").ctrl(x5091).srcCtx("SPMV_CRS.scala:78:25") // FixAdd(x5075,x5065)
    val x5078 = OpDef(op=FixAdd, inputs=List(x5065, x5089_x5075)).name("x5078").ctrl(x5091).srcCtx("SPMV_CRS.scala:78:25") // FixAdd(x5065,x5075)
    val x5089_x5079 = OpDef(op=FixAdd, inputs=List(x5078, x5076)).name("x5089_x5079").ctrl(x5091).srcCtx("SPMV_CRS.scala:78:25") // FixAdd(x5078,x5076)
    val x5080 = OpDef(op=FixAdd, inputs=List(x5066, x5064)).name("x5080").ctrl(x5091).srcCtx("SPMV_CRS.scala:78:25") // FixAdd(x5066,x5064)
    val x5086_x5081 = OpDef(op=FixAdd, inputs=List(x5080, x5074)).name("x5086_x5081").ctrl(x5091).srcCtx("SPMV_CRS.scala:78:25") // FixAdd(x5080,x5074)
    val x5082 = x5067 // FixConvert(x5067,TRUE,_64,_0)
    val x5083 = DramAddress(x4861).name("x5083").ctrl(x5091).srcCtx("SPMV_CRS.scala:78:25") // GetDRAMAddress(x4861)
    val x5084 = OpDef(op=FixAdd, inputs=List(x5082, x5083)).name("x5084").ctrl(x5091).srcCtx("SPMV_CRS.scala:78:25") // FixAdd(x5082,x5083)
    val x5086_x5085 = x5084 // FixConvert(x5084,TRUE,_64,_0) (Same Type. No op)
    // x5086 = SimpleStruct(ArrayBuffer((offset,x5085), (size,x5081), (isLoad,Const(true))))
    val x5087 = OpDef(op=BitAnd, inputs=List(b2730, b2651)).name("x5087").ctrl(x5091).srcCtx("UnrollingBase.scala:28:66") // And(b2730,b2651)
    val x5088_b5339_b5334 = WriteMem(b5334, x5086_x5085).name("x5088_b5339_b5334").ctrl(x5091).srcCtx("SPMV_CRS.scala:78:25") // StreamWrite(x5056,x5086,x5087)
    val x5088_b5340_b5335 = WriteMem(b5335, x5086_x5081).name("x5088_b5340_b5335").ctrl(x5091).srcCtx("SPMV_CRS.scala:78:25") // StreamWrite(x5056,x5086,x5087)
    // x5089 = SimpleStruct(ArrayBuffer((size,x5079), (start,x5075), (end,x5077)))
    val x5090_b5341_b5336 = WriteMem(b5336, x5089_x5079).name("x5090_b5341_b5336").ctrl(x5091).srcCtx("SPMV_CRS.scala:78:25") // FIFOEnq(x5057,x5089,x5087)
    val x5090_b5342_b5337 = WriteMem(b5337, x5089_x5075).name("x5090_b5342_b5337").ctrl(x5091).srcCtx("SPMV_CRS.scala:78:25") // FIFOEnq(x5057,x5089,x5087)
    val x5090_b5343_b5338 = WriteMem(b5338, x5089_x5077).name("x5090_b5343_b5338").ctrl(x5091).srcCtx("SPMV_CRS.scala:78:25") // FIFOEnq(x5057,x5089,x5087)
    val x5092 = FringeDenseLoad(dram=List(x4861), cmdStream=List(b5334, b5335), dataStream=List(x5058)).name("x5092").ctrl(x5122).srcCtx("SPMV_CRS.scala:78:25") // FringeDenseLoad(x4861,x5056,x5058)
    val x5121 = UnitController(style=SeqPipe, level=OuterControl).name("x5121").ctrl(x5122).srcCtx("SPMV_CRS.scala:78:25") // UnitPipe(List(b2730, b2651),Block(Const(())))
    val x5093 = Reg(init=Some(0)).name("x5093").ctrl(x5121).srcCtx("SPMV_CRS.scala:78:25") // x5093 = RegNew(Const(0))
    isAccum(x5093) = false
    bufferDepthOf(x5093) = 1
    val x5094 = Reg(init=Some(0)).name("x5094").ctrl(x5121).srcCtx("SPMV_CRS.scala:78:25") // x5094 = RegNew(Const(0))
    isAccum(x5094) = false
    bufferDepthOf(x5094) = 1
    val x5095 = Reg(init=Some(0)).name("x5095").ctrl(x5121).srcCtx("SPMV_CRS.scala:78:25") // x5095 = RegNew(Const(0))
    isAccum(x5095) = false
    bufferDepthOf(x5095) = 1
    val x5104 = UnitController(style=SeqPipe, level=InnerControl).name("x5104").ctrl(x5121).srcCtx("SPMV_CRS.scala:78:25") // UnitPipe(List(b2730, b2651),Block(x5103))
    val x5096 = OpDef(op=BitAnd, inputs=List(b2730, b2651)).name("x5096").ctrl(x5104).srcCtx("UnrollingBase.scala:28:66") // And(b2730,b2651)
    val x5097_b5344 = ReadMem(b5336).name("x5097_b5344").ctrl(x5104).srcCtx("SPMV_CRS.scala:78:25") // FIFODeq(x5057,x5096)
    val x5097_b5345 = ReadMem(b5337).name("x5097_b5345").ctrl(x5104).srcCtx("SPMV_CRS.scala:78:25") // FIFODeq(x5057,x5096)
    val x5097_b5346 = ReadMem(b5338).name("x5097_b5346").ctrl(x5104).srcCtx("SPMV_CRS.scala:78:25") // FIFODeq(x5057,x5096)
    val x5098 = x5097_b5345 // x5098 = FieldApply(x5097,start)
    val x5099_x5093 = WriteMem(x5093, x5098).name("x5099_x5093").ctrl(x5104).srcCtx("SPMV_CRS.scala:78:25") // RegWrite(x5093,x5098,x5096)
    val x5100 = x5097_b5346 // x5100 = FieldApply(x5097,end)
    val x5101_x5094 = WriteMem(x5094, x5100).name("x5101_x5094").ctrl(x5104).srcCtx("SPMV_CRS.scala:78:25") // RegWrite(x5094,x5100,x5096)
    val x5102 = x5097_b5344 // x5102 = FieldApply(x5097,size)
    val x5103_x5095 = WriteMem(x5095, x5102).name("x5103_x5095").ctrl(x5104).srcCtx("SPMV_CRS.scala:78:25") // RegWrite(x5095,x5102,x5096)
    // x5105.deps=List(x5104, x5095)
    val x5105 = ReadMem(x5095).name("x5105").ctrl(x5121).srcCtx("SPMV_CRS.scala:78:25") // RegRead(x5095)
    val x5106 = Counter(min=Const(0), max=x5105, step=Const(1), par=1).name("x5106").ctrl(x5121).srcCtx("SPMV_CRS.scala:78:25") // CounterNew(Const(0),x5105,Const(1),Const(1))
    val x5107 = CounterChain(List(x5106)).name("x5107").ctrl(x5121).srcCtx("SPMV_CRS.scala:78:25") // CounterChainNew(List(x5106))
    val x5120 = LoopController(style=InnerPipe, level=InnerControl, cchain=x5107).name("x5120").ctrl(x5121).srcCtx("SPMV_CRS.scala:78:25") // UnrolledForeach(List(b2730, b2651),x5107,Block(Const(())),List(List(b2863)),List(List(b2864)))
    val b2863 = CounterIter(x5106, None).name("b2863").ctrl(x5120) // b2863
    val b2864 = Const(true).name("b2864").ctrl(x5120) // b2864
    val x5108 = ReadMem(x5093).name("x5108").ctrl(x5120).srcCtx("SPMV_CRS.scala:78:25") // RegRead(x5093)
    val x5109 = OpDef(op=FixLeq, inputs=List(x5108, b2863)).name("x5109").ctrl(x5120).srcCtx("SPMV_CRS.scala:78:25") // FixLeq(x5108,b2863)
    val x5110 = ReadMem(x5094).name("x5110").ctrl(x5120).srcCtx("SPMV_CRS.scala:78:25") // RegRead(x5094)
    val x5111 = OpDef(op=FixLt, inputs=List(b2863, x5110)).name("x5111").ctrl(x5120).srcCtx("SPMV_CRS.scala:78:25") // FixLt(b2863,x5110)
    val x5112 = OpDef(op=BitAnd, inputs=List(x5109, x5111)).name("x5112").ctrl(x5120).srcCtx("SPMV_CRS.scala:78:25") // And(x5109,x5111)
    val x5113 = OpDef(op=FixSub, inputs=List(b2863, x5108)).name("x5113").ctrl(x5120).srcCtx("SPMV_CRS.scala:78:25") // FixSub(b2863,x5108)
    val x5114 = OpDef(op=BitAnd, inputs=List(b2864, b2730)).name("x5114").ctrl(x5120).srcCtx("UnrollingBase.scala:28:66") // And(b2864,b2730)
    val x5115 = OpDef(op=BitAnd, inputs=List(x5114, b2651)).name("x5115").ctrl(x5120).srcCtx("UnrollingBase.scala:28:66") // And(x5114,b2651)
    val x5116_x5116 = ReadMem(x5058).name("x5116_x5116").ctrl(x5120).srcCtx("SPMV_CRS.scala:78:25") // ParStreamRead(x5058,List(x5115))
    val x5117_x5117 = x5116_x5116 // x5117 = VectorApply(x5116,0)
    val x5118 = OpDef(op=BitAnd, inputs=List(x5112, x5115)).name("x5118").ctrl(x5120).srcCtx("UnrollingTransformer.scala:239:41") // And(x5112,x5115)
    val x5119 = StoreBanks(List(x4971_d0_b0), List(x5113), x5117_x5117).name("x5119").ctrl(x5120).srcCtx("SPMV_CRS.scala:78:25") // ParSRAMStore(x4971,List(List(x5113)),List(x5117),List(x5118))
    val x5124_d0 = Reg(init=Some(0)).name("x5124_d0").ctrl(x5199).srcCtx("SPMV_CRS.scala:69:43") // x5124 = RegNew(Const(0))
    isAccum(x5124_d0) = false
    bufferDepthOf(x5124_d0) = 4
    val x5124_d1 = Reg(init=Some(0)).name("x5124_d1").ctrl(x5199).srcCtx("SPMV_CRS.scala:69:43") // x5124 = RegNew(Const(0))
    isAccum(x5124_d1) = false
    bufferDepthOf(x5124_d1) = 3
    val x5124_d2 = Reg(init=Some(0)).name("x5124_d2").ctrl(x5199).srcCtx("SPMV_CRS.scala:69:43") // x5124 = RegNew(Const(0))
    isAccum(x5124_d2) = false
    bufferDepthOf(x5124_d2) = 3
    val x5124_d3 = Reg(init=Some(0)).name("x5124_d3").ctrl(x5199).srcCtx("SPMV_CRS.scala:69:43") // x5124 = RegNew(Const(0))
    isAccum(x5124_d3) = false
    bufferDepthOf(x5124_d3) = 2
    val x5130 = UnitController(style=SeqPipe, level=InnerControl).name("x5130").ctrl(x5199).srcCtx("SPMV_CRS.scala:69:43") // UnitPipe(List(b2730, b2651),Block(Const(())))
    val x5125 = ReadMem(x4974_d0).name("x5125").ctrl(x5130).srcCtx("SPMV_CRS.scala:69:43") // RegRead(x4974)
    val x5126 = ReadMem(x4973_d0).name("x5126").ctrl(x5130).srcCtx("SPMV_CRS.scala:69:43") // RegRead(x4973)
    val x5127 = OpDef(op=FixSub, inputs=List(x5125, x5126)).name("x5127").ctrl(x5130).srcCtx("SPMV_CRS.scala:80:55") // FixSub(x5125,x5126)
    val x5128 = OpDef(op=BitAnd, inputs=List(b2730, b2651)).name("x5128").ctrl(x5130).srcCtx("UnrollingBase.scala:28:66") // And(b2730,b2651)
    val x5129_x5124_d0 = WriteMem(x5124_d0, x5127).name("x5129_x5124_d0").ctrl(x5130).srcCtx("SPMV_CRS.scala:69:43") // RegWrite(x5124,x5127,x5128)
    val x5129_x5124_d1 = WriteMem(x5124_d1, x5127).name("x5129_x5124_d1").ctrl(x5130).srcCtx("SPMV_CRS.scala:69:43") // RegWrite(x5124,x5127,x5128)
    val x5129_x5124_d2 = WriteMem(x5124_d2, x5127).name("x5129_x5124_d2").ctrl(x5130).srcCtx("SPMV_CRS.scala:69:43") // RegWrite(x5124,x5127,x5128)
    val x5129_x5124_d3 = WriteMem(x5124_d3, x5127).name("x5129_x5124_d3").ctrl(x5130).srcCtx("SPMV_CRS.scala:69:43") // RegWrite(x5124,x5127,x5128)
    val x5131_d0 = Reg(init=Some(0)).name("x5131_d0").ctrl(x5199).srcCtx("SPMV_CRS.scala:80:20") // x5131 = RegNew(Const(0))
    isAccum(x5131_d0) = false
    bufferDepthOf(x5131_d0) = 2
    val x5131_d1 = Reg(init=Some(0)).name("x5131_d1").ctrl(x5199).srcCtx("SPMV_CRS.scala:80:20") // x5131 = RegNew(Const(0))
    isAccum(x5131_d1) = false
    bufferDepthOf(x5131_d1) = 2
    val x5144 = UnitController(style=SeqPipe, level=InnerControl).name("x5144").ctrl(x5199).srcCtx("SPMV_CRS.scala:80:20") // UnitPipe(List(b2730, b2651),Block(x5143))
    val x5132 = ReadMem(x5124_d3).name("x5132").ctrl(x5144).srcCtx("SPMV_CRS.scala:69:43") // RegRead(x5124)
    val x5133 = OpDef(op=FixLt, inputs=List(x5132, Const(16))).name("x5133").ctrl(x5144).srcCtx("SPMV_CRS.scala:80:20") // FixLt(x5132,Const(16))
    val x5134 = x5132 // x5134 = DataAsBits(x5132)
    val x5135 = OpDef(op=BitAnd, inputs=List(x5134, Const(7))).name("x5135").ctrl(x5144).srcCtx("SPMV_CRS.scala:80:20") // VectorSlice(x5134,3,0) strMask=00000000000000000000000000000111
    val x5136 = x5135 // x5136 = BitsAsData(x5135,FixPt[TRUE,_32,_0])
    val x5137 = OpDef(op=FixEql, inputs=List(x5136, Const(0))).name("x5137").ctrl(x5144).srcCtx("SPMV_CRS.scala:80:20") // FixEql(x5136,Const(0))
    val x5138 = OpDef(op=FixAdd, inputs=List(x5132, Const(16))).name("x5138").ctrl(x5144).srcCtx("SPMV_CRS.scala:80:20") // FixAdd(x5132,Const(16))
    val x5139 = OpDef(op=FixSub, inputs=List(x5138, x5136)).name("x5139").ctrl(x5144).srcCtx("SPMV_CRS.scala:80:20") // FixSub(x5138,x5136)
    val x5140 = OpDef(op=MuxOp, inputs=List(x5137, x5132, x5139)).name("x5140").ctrl(x5144).srcCtx("SPMV_CRS.scala:80:20") // Mux(x5137,x5132,x5139)
    val x5141 = OpDef(op=MuxOp, inputs=List(x5133, Const(16), x5140)).name("x5141").ctrl(x5144).srcCtx("SPMV_CRS.scala:80:20") // Mux(x5133,Const(16),x5140)
    val x5142 = OpDef(op=BitAnd, inputs=List(b2730, b2651)).name("x5142").ctrl(x5144).srcCtx("UnrollingBase.scala:28:66") // And(b2730,b2651)
    val x5143_x5131_d0 = WriteMem(x5131_d0, x5141).name("x5143_x5131_d0").ctrl(x5144).srcCtx("SPMV_CRS.scala:80:20") // RegWrite(x5131,x5141,x5142)
    val x5143_x5131_d1 = WriteMem(x5131_d1, x5141).name("x5143_x5131_d1").ctrl(x5144).srcCtx("SPMV_CRS.scala:80:20") // RegWrite(x5131,x5141,x5142)
    val x5177 = UnitController(style=StreamPipe, level=OuterControl).name("x5177").ctrl(x5199).srcCtx("SPMV_CRS.scala:80:20") // UnitPipe(List(b2730, b2651),Block(Const(())))
    val x5145 = StreamOut(field="addr").name("x5145").ctrl(x5177).srcCtx("SPMV_CRS.scala:80:20") // x5145 = StreamOutNew(GatherAddrBus)
    isAccum(x5145) = false
    bufferDepthOf(x5145) = 1
    val x5146 = StreamIn(field="data").name("x5146").ctrl(x5177).srcCtx("SPMV_CRS.scala:80:20") // x5146 = StreamInNew(GatherDataBus())
    isAccum(x5146) = false
    bufferDepthOf(x5146) = 1
    val x5147 = ReadMem(x5131_d1).name("x5147").ctrl(x5177).srcCtx("SPMV_CRS.scala:80:20") // RegRead(x5131)
    val x5148 = Counter(min=Const(0), max=x5147, step=Const(1), par=1).name("x5148").ctrl(x5177).srcCtx("SPMV_CRS.scala:80:20") // CounterNew(Const(0),x5147,Const(1),Const(1))
    val x5149 = CounterChain(List(x5148)).name("x5149").ctrl(x5177).srcCtx("SPMV_CRS.scala:80:20") // CounterChainNew(List(x5148))
    val x5163 = LoopController(style=InnerPipe, level=InnerControl, cchain=x5149).name("x5163").ctrl(x5177).srcCtx("SPMV_CRS.scala:80:20") // UnrolledForeach(List(b2730, b2651),x5149,Block(Const(())),List(List(b2905)),List(List(b2906)))
    val b2905 = CounterIter(x5148, None).name("b2905").ctrl(x5163) // b2905
    val b2906 = Const(true).name("b2906").ctrl(x5163) // b2906
    val x5150 = ReadMem(x5124_d2).name("x5150").ctrl(x5163).srcCtx("SPMV_CRS.scala:69:43") // RegRead(x5124)
    val x5151 = OpDef(op=FixLeq, inputs=List(x5150, b2905)).name("x5151").ctrl(x5163).srcCtx("SPMV_CRS.scala:80:20") // FixLeq(x5150,b2905)
    val x5152 = DramAddress(x4864).name("x5152").ctrl(x5163).srcCtx("SPMV_CRS.scala:80:20") // GetDRAMAddress(x4864)
    val x5153 = x5152 // FixConvert(x5152,TRUE,_64,_0) (Same Type. No op)
    val x5154 = OpDef(op=BitAnd, inputs=List(b2906, b2730)).name("x5154").ctrl(x5163).srcCtx("UnrollingBase.scala:28:66") // And(b2906,b2730)
    val x5155 = OpDef(op=BitAnd, inputs=List(x5154, b2651)).name("x5155").ctrl(x5163).srcCtx("UnrollingBase.scala:28:66") // And(x5154,b2651)
    val x5156 = LoadBanks(List(x4970_d0_b0), List(b2905)).name("x5156").ctrl(x5163).srcCtx("SPMV_CRS.scala:80:20") // ParSRAMLoad(x4970,List(List(b2905)),List(x5155))
    val x5157 = x5156 // x5157 = VectorApply(x5156,0)
    val x5158 = OpDef(op=FixSla, inputs=List(x5157, Const(2))).name("x5158").ctrl(x5163).srcCtx("SPMV_CRS.scala:80:20") // FixLsh(x5157,Const(2))
    val x5159 = x5158 // FixConvert(x5158,TRUE,_64,_0)
    val x5160 = OpDef(op=FixAdd, inputs=List(x5159, x5152)).name("x5160").ctrl(x5163).srcCtx("SPMV_CRS.scala:80:20") // FixAdd(x5159,x5152)
    val x5161 = OpDef(op=MuxOp, inputs=List(x5151, x5153, x5160)).name("x5161").ctrl(x5163).srcCtx("SPMV_CRS.scala:80:20") // Mux(x5151,x5153,x5160)
    val x5162_x5162_x5145 = WriteMem(x5145, x5161).name("x5162_x5162_x5145").ctrl(x5163).srcCtx("SPMV_CRS.scala:80:20") // ParStreamWrite(x5145,List(x5161),List(x5155))
    val x5164 = FringeSparseLoad(dram=List(x4864), addrStream=List(x5145), dataStream=List(x5146)).name("x5164").ctrl(x5177).srcCtx("SPMV_CRS.scala:80:20") // FringeSparseLoad(x4864,x5145,x5146)
    val x5165 = ReadMem(x5131_d0).name("x5165").ctrl(x5177).srcCtx("SPMV_CRS.scala:80:20") // RegRead(x5131)
    val x5166 = Counter(min=Const(0), max=x5165, step=Const(1), par=1).name("x5166").ctrl(x5177).srcCtx("SPMV_CRS.scala:80:20") // CounterNew(Const(0),x5165,Const(1),Const(1))
    val x5167 = CounterChain(List(x5166)).name("x5167").ctrl(x5177).srcCtx("SPMV_CRS.scala:80:20") // CounterChainNew(List(x5166))
    val x5176 = LoopController(style=InnerPipe, level=InnerControl, cchain=x5167).name("x5176").ctrl(x5177).srcCtx("SPMV_CRS.scala:80:20") // UnrolledForeach(List(b2730, b2651),x5167,Block(Const(())),List(List(b2925)),List(List(b2926)))
    val b2925 = CounterIter(x5166, None).name("b2925").ctrl(x5176) // b2925
    val b2926 = Const(true).name("b2926").ctrl(x5176) // b2926
    val x5168 = OpDef(op=BitAnd, inputs=List(b2926, b2730)).name("x5168").ctrl(x5176).srcCtx("UnrollingBase.scala:28:66") // And(b2926,b2730)
    val x5169 = OpDef(op=BitAnd, inputs=List(x5168, b2651)).name("x5169").ctrl(x5176).srcCtx("UnrollingBase.scala:28:66") // And(x5168,b2651)
    val x5170_x5170 = ReadMem(x5146).name("x5170_x5170").ctrl(x5176).srcCtx("SPMV_CRS.scala:80:20") // ParStreamRead(x5146,List(x5169))
    val x5171_x5171 = x5170_x5170 // x5171 = VectorApply(x5170,0)
    val x5172 = ReadMem(x5124_d1).name("x5172").ctrl(x5176).srcCtx("SPMV_CRS.scala:69:43") // RegRead(x5124)
    val x5173 = OpDef(op=FixLt, inputs=List(b2925, x5172)).name("x5173").ctrl(x5176).srcCtx("SPMV_CRS.scala:80:20") // FixLt(b2925,x5172)
    val x5174 = OpDef(op=BitAnd, inputs=List(x5173, x5169)).name("x5174").ctrl(x5176).srcCtx("UnrollingTransformer.scala:239:41") // And(x5173,x5169)
    val x5175 = StoreBanks(List(x4972_d0_b0), List(b2925), x5171_x5171).name("x5175").ctrl(x5176).srcCtx("SPMV_CRS.scala:80:20") // ParSRAMStore(x4972,List(List(b2925)),List(x5171),List(x5174))
    val x5178_d0 = Reg(init=Some(0.0)).name("x5178_d0").ctrl(x5199).srcCtx("SPMV_CRS.scala:82:38:element") // x5178 = RegNew(Const(0))
    isAccum(x5178_d0) = false
    bufferDepthOf(x5178_d0) = 2
    val x5178_d1 = Reg(init=Some(0.0)).name("x5178_d1").ctrl(x5199).srcCtx("SPMV_CRS.scala:82:38:element") // x5178 = RegNew(Const(0))
    isAccum(x5178_d1) = true
    bufferDepthOf(x5178_d1) = 1
    // x5179.deps=List(x5130, x5124)
    val x5179 = ReadMem(x5124_d0).name("x5179").ctrl(x5199).srcCtx("SPMV_CRS.scala:69:43") // RegRead(x5124)
    val x5180 = Counter(min=Const(0), max=x5179, step=Const(1), par=2).name("x5180").ctrl(x5199).srcCtx("SPMV_CRS.scala:82:67") // CounterNew(Const(0),x5179,Const(1),Const(2))
    val x5181 = CounterChain(List(x5180)).name("x5181").ctrl(x5199).srcCtx("SPMV_CRS.scala:84:12") // CounterChainNew(List(x5180))
    val x5194 = LoopController(style=InnerPipe, level=InnerControl, cchain=x5181).name("x5194").ctrl(x5199).srcCtx("SPMV_CRS.scala:84:12") // UnrolledReduce(List(b2730, b2651),x5181,x5178,Block((x5178) => Const(())),List(List(b2941)),List(List(b2942)))
    val b2941 = CounterIter(x5180, None).name("b2941").ctrl(x5194) // b2941
    val b2942 = Const(true).name("b2942").ctrl(x5194) // b2942
    val x5182 = OpDef(op=BitAnd, inputs=List(b2942, b2730)).name("x5182").ctrl(x5194).srcCtx("UnrollingBase.scala:28:66") // And(b2942,b2730)
    val x5183 = OpDef(op=BitAnd, inputs=List(x5182, b2651)).name("x5183").ctrl(x5194).srcCtx("UnrollingBase.scala:28:66") // And(x5182,b2651)
    val x5184 = LoadBanks(List(x4971_d0_b0), List(b2941)).name("x5184").ctrl(x5194).srcCtx("SPMV_CRS.scala:83:24") // ParSRAMLoad(x4971,List(List(b2941)),List(x5183))
    val x5185 = x5184 // x5185 = VectorApply(x5184,0)
    val x5186 = LoadBanks(List(x4972_d0_b0), List(b2941)).name("x5186").ctrl(x5194).srcCtx("SPMV_CRS.scala:83:38") // ParSRAMLoad(x4972,List(List(b2941)),List(x5183))
    val x5187 = x5186 // x5187 = VectorApply(x5186,0)
    val x5188 = OpDef(op=FixMul, inputs=List(x5185, x5187)).name("x5188").ctrl(x5194).srcCtx("SPMV_CRS.scala:83:28") // FixMul(x5185,x5187)
    val x5189 = ReadMem(x5178_d1).name("x5189").ctrl(x5194).srcCtx("SPMV_CRS.scala:84:12") // RegRead(x5178)
    val x5190 = OpDef(op=FixEql, inputs=List(b2941, Const(0))).name("x5190").ctrl(x5194).srcCtx("SPMV_CRS.scala:84:12") // FixEql(b2941,Const(0))
    val x5191 = ReduceAccumOp(op=FixAdd, input=x5188, accum=x5189).name("x5191").ctrl(x5194).srcCtx("SPMV_CRS.scala:84:14") // FixAdd(x5188,x5189)
    val x5192 = OpDef(op=BitAnd, inputs=List(b2730, b2651)).name("x5192").ctrl(x5194).srcCtx("UnrollingBase.scala:28:66") // And(b2730,b2651)
    // x5193.deps=List(x5189)
    val x5193_x5178_d0 = WriteMem(x5178_d0, x5191).name("x5193_x5178_d0").ctrl(x5194).srcCtx("SPMV_CRS.scala:84:12") // RegWrite(x5178,x5191,x5192)
    val x5193_x5178_d1 = WriteMem(x5178_d1, x5191).name("x5193_x5178_d1").ctrl(x5194).srcCtx("SPMV_CRS.scala:84:12") // RegWrite(x5178,x5191,x5192)
    def split1 = {
    val x5198 = UnitController(style=SeqPipe, level=InnerControl).name("x5198").ctrl(x5199).srcCtx("SPMV_CRS.scala:69:43") // UnitPipe(List(b2730, b2651),Block(Const(())))
    val x5195 = ReadMem(x5178_d0).name("x5195").ctrl(x5198).srcCtx("SPMV_CRS.scala:85:28") // RegRead(x5178)
    val x5196 = OpDef(op=BitAnd, inputs=List(b2730, b2651)).name("x5196").ctrl(x5198).srcCtx("UnrollingBase.scala:28:66") // And(b2730,b2651)
    val x5197 = StoreBanks(List(x4892_d0_b0), List(b2729), x5195).name("x5197").ctrl(x5198).srcCtx("SPMV_CRS.scala:85:26") // SRAMStore(x4892,ArrayBuffer(Const(494)),List(b2729),Const(0),x5195,x5196)
    val x5200 = Reg(init=Some(0)).name("x5200").ctrl(x5270).srcCtx("SPMV_CRS.scala:64:48") // x5200 = RegNew(Const(0))
    isAccum(x5200) = false
    bufferDepthOf(x5200) = 2
    val x5201 = Reg(init=Some(0)).name("x5201").ctrl(x5270).srcCtx("SPMV_CRS.scala:64:48") // x5201 = RegNew(Const(0))
    isAccum(x5201) = false
    bufferDepthOf(x5201) = 2
    val x5208 = UnitController(style=SeqPipe, level=InnerControl).name("x5208").ctrl(x5270).srcCtx("SPMV_CRS.scala:64:48") // UnitPipe(List(b2651),Block(Const(())))
    val x5202 = OpDef(op=FixMul, inputs=List(b2650, Const(494))).name("x5202").ctrl(x5208).srcCtx("SPMV_CRS.scala:87:22:x$6") // FixMul(b2650,Const(494))
    val x5203 = ReadMem(x4894).name("x5203").ctrl(x5208).srcCtx("SPMV_CRS.scala:64:48") // RegRead(x4894)
    val x5204 = OpDef(op=FixMul, inputs=List(x5203, Const(494))).name("x5204").ctrl(x5208).srcCtx("SPMV_CRS.scala:87:40") // FixMul(x5203,Const(494))
    val x5205 = OpDef(op=FixSub, inputs=List(x5204, x5202)).name("x5205").ctrl(x5208).srcCtx("SPMV_CRS.scala:87:65") // FixSub(x5204,x5202)
    val x5206_x5200 = WriteMem(x5200, x5202).name("x5206_x5200").ctrl(x5208).srcCtx("SPMV_CRS.scala:64:48") // RegWrite(x5200,x5202,b2651)
    val x5207_x5201 = WriteMem(x5201, x5205).name("x5207_x5201").ctrl(x5208).srcCtx("SPMV_CRS.scala:64:48") // RegWrite(x5201,x5205,b2651)
    val x5269 = UnitController(style=StreamPipe, level=OuterControl).name("x5269").ctrl(x5270).srcCtx("SPMV_CRS.scala:87:65") // UnitPipe(List(b2651),Block(Const(())))
    val b5347 = StreamOut(field="offset").name("b5347").ctrl(x5269).srcCtx("SPMV_CRS.scala:87:65") // x5209 = StreamOutNew(BurstCmdBus)
    isAccum(b5347) = false
    bufferDepthOf(b5347) = 1
    val b5348 = StreamOut(field="size").name("b5348").ctrl(x5269).srcCtx("SPMV_CRS.scala:87:65") // x5209 = StreamOutNew(BurstCmdBus)
    isAccum(b5348) = false
    bufferDepthOf(b5348) = 1
    val x5210 = StreamOut(field="data").name("x5210").ctrl(x5269).srcCtx("SPMV_CRS.scala:87:65") // x5210 = StreamOutNew(BurstFullDataBus())
    isAccum(x5210) = false
    bufferDepthOf(x5210) = 1
    val x5211 = StreamIn(field="ack").name("x5211").ctrl(x5269).srcCtx("SPMV_CRS.scala:87:65") // x5211 = StreamInNew(BurstAckBus)
    isAccum(x5211) = false
    bufferDepthOf(x5211) = 1
    val x5268 = UnitController(style=SeqPipe, level=OuterControl).name("x5268").ctrl(x5269).srcCtx("SPMV_CRS.scala:87:65") // UnitPipe(List(b2651),Block(Const(())))
    val x5264 = UnitController(style=SeqPipe, level=OuterControl).name("x5264").ctrl(x5268).srcCtx("SPMV_CRS.scala:87:65") // UnitPipe(List(b2651),Block(Const(())))
    val x5212 = Reg(init=Some(0)).name("x5212").ctrl(x5264).srcCtx("SPMV_CRS.scala:87:65") // x5212 = RegNew(Const(0))
    isAccum(x5212) = false
    bufferDepthOf(x5212) = 1
    val x5213 = Reg(init=Some(0)).name("x5213").ctrl(x5264).srcCtx("SPMV_CRS.scala:87:65") // x5213 = RegNew(Const(0))
    isAccum(x5213) = false
    bufferDepthOf(x5213) = 1
    val x5214 = Reg(init=Some(0)).name("x5214").ctrl(x5264).srcCtx("SPMV_CRS.scala:87:65") // x5214 = RegNew(Const(0))
    isAccum(x5214) = false
    bufferDepthOf(x5214) = 1
    val x5246 = UnitController(style=SeqPipe, level=InnerControl).name("x5246").ctrl(x5264).srcCtx("SPMV_CRS.scala:87:65") // UnitPipe(List(b2651),Block(x5245))
    val x5215 = ReadMem(x5200).name("x5215").ctrl(x5246).srcCtx("SPMV_CRS.scala:64:48") // RegRead(x5200)
    val x5216 = x5215 // FixConvert(x5215,TRUE,_32,_0) (Same Type. No op)
    val x5217 = OpDef(op=FixSla, inputs=List(x5216, Const(2))).name("x5217").ctrl(x5246).srcCtx("SPMV_CRS.scala:87:65") // FixLsh(x5216,Const(2))
    val x5218 = x5217 // x5218 = DataAsBits(x5217)
    val x5219 = OpDef(op=BitAnd, inputs=List(x5218, Const(31))).name("x5219").ctrl(x5246).srcCtx("SPMV_CRS.scala:87:65") // VectorSlice(x5218,5,0) strMask=00000000000000000000000000011111
    val x5220 = x5219 // x5220 = BitsAsData(x5219,FixPt[TRUE,_32,_0])
    val x5221 = ReadMem(x5201).name("x5221").ctrl(x5246).srcCtx("SPMV_CRS.scala:64:48") // RegRead(x5201)
    val x5222 = OpDef(op=FixSla, inputs=List(x5221, Const(2))).name("x5222").ctrl(x5246).srcCtx("SPMV_CRS.scala:87:65") // FixLsh(x5221,Const(2))
    val x5223 = OpDef(op=FixSub, inputs=List(x5217, x5220)).name("x5223").ctrl(x5246).srcCtx("SPMV_CRS.scala:87:65") // FixSub(x5217,x5220)
    val x5224 = OpDef(op=FixAdd, inputs=List(x5217, x5222)).name("x5224").ctrl(x5246).srcCtx("SPMV_CRS.scala:87:65") // FixAdd(x5217,x5222)
    val x5225 = x5224 // x5225 = DataAsBits(x5224)
    val x5226 = OpDef(op=BitAnd, inputs=List(x5225, Const(31))).name("x5226").ctrl(x5246).srcCtx("SPMV_CRS.scala:87:65") // VectorSlice(x5225,5,0) strMask=00000000000000000000000000011111
    val x5227 = x5226 // x5227 = BitsAsData(x5226,FixPt[TRUE,_32,_0])
    val x5228 = OpDef(op=FixEql, inputs=List(x5227, Const(0))).name("x5228").ctrl(x5246).srcCtx("SPMV_CRS.scala:87:65") // FixEql(x5227,Const(0))
    val x5229 = OpDef(op=FixSub, inputs=List(Const(64), x5227)).name("x5229").ctrl(x5246).srcCtx("SPMV_CRS.scala:87:65") // FixSub(Const(64),x5227)
    val x5230 = OpDef(op=MuxOp, inputs=List(x5228, Const(0), x5229)).name("x5230").ctrl(x5246).srcCtx("SPMV_CRS.scala:87:65") // Mux(x5228,Const(0),x5229)
    val x5231 = OpDef(op=FixSra, inputs=List(x5220, Const(2))).name("x5231").ctrl(x5246).srcCtx("SPMV_CRS.scala:87:65") // FixRsh(x5220,Const(2))
    val x5232 = OpDef(op=FixSra, inputs=List(x5230, Const(2))).name("x5232").ctrl(x5246).srcCtx("SPMV_CRS.scala:87:65") // FixRsh(x5230,Const(2))
    val x5233 = OpDef(op=FixAdd, inputs=List(x5231, x5221)).name("x5233").ctrl(x5246).srcCtx("SPMV_CRS.scala:87:65") // FixAdd(x5231,x5221)
    val x5234 = OpDef(op=FixAdd, inputs=List(x5221, x5231)).name("x5234").ctrl(x5246).srcCtx("SPMV_CRS.scala:87:65") // FixAdd(x5221,x5231)
    val x5235 = OpDef(op=FixAdd, inputs=List(x5234, x5232)).name("x5235").ctrl(x5246).srcCtx("SPMV_CRS.scala:87:65") // FixAdd(x5234,x5232)
    val x5236 = OpDef(op=FixAdd, inputs=List(x5222, x5220)).name("x5236").ctrl(x5246).srcCtx("SPMV_CRS.scala:87:65") // FixAdd(x5222,x5220)
    val x5241_x5237 = OpDef(op=FixAdd, inputs=List(x5236, x5230)).name("x5241_x5237").ctrl(x5246).srcCtx("SPMV_CRS.scala:87:65") // FixAdd(x5236,x5230)
    val x5238 = x5223 // FixConvert(x5223,TRUE,_64,_0)
    val x5239 = DramAddress(x4865).name("x5239").ctrl(x5246).srcCtx("SPMV_CRS.scala:87:65") // GetDRAMAddress(x4865)
    val x5241_x5240 = OpDef(op=FixAdd, inputs=List(x5238, x5239)).name("x5241_x5240").ctrl(x5246).srcCtx("SPMV_CRS.scala:87:65") // FixAdd(x5238,x5239)
    // x5241 = SimpleStruct(ArrayBuffer((offset,x5240), (size,x5237), (isLoad,Const(false))))
    val x5242_b5349_b5347 = WriteMem(b5347, x5241_x5240).name("x5242_b5349_b5347").ctrl(x5246).srcCtx("SPMV_CRS.scala:87:65") // StreamWrite(x5209,x5241,b2651)
    val x5242_b5350_b5348 = WriteMem(b5348, x5241_x5237).name("x5242_b5350_b5348").ctrl(x5246).srcCtx("SPMV_CRS.scala:87:65") // StreamWrite(x5209,x5241,b2651)
    val x5243_x5212 = WriteMem(x5212, x5231).name("x5243_x5212").ctrl(x5246).srcCtx("SPMV_CRS.scala:87:65") // RegWrite(x5212,x5231,b2651)
    val x5244_x5213 = WriteMem(x5213, x5233).name("x5244_x5213").ctrl(x5246).srcCtx("SPMV_CRS.scala:87:65") // RegWrite(x5213,x5233,b2651)
    val x5245_x5214 = WriteMem(x5214, x5235).name("x5245_x5214").ctrl(x5246).srcCtx("SPMV_CRS.scala:87:65") // RegWrite(x5214,x5235,b2651)
    // x5247.deps=List(x5246, x5214)
    val x5247 = ReadMem(x5214).name("x5247").ctrl(x5264).srcCtx("SPMV_CRS.scala:87:65") // RegRead(x5214)
    val x5248 = Counter(min=Const(0), max=x5247, step=Const(1), par=1).name("x5248").ctrl(x5264).srcCtx("SPMV_CRS.scala:87:65") // CounterNew(Const(0),x5247,Const(1),Const(1))
    val x5249 = CounterChain(List(x5248)).name("x5249").ctrl(x5264).srcCtx("SPMV_CRS.scala:87:65") // CounterChainNew(List(x5248))
    val x5263 = LoopController(style=InnerPipe, level=InnerControl, cchain=x5249).name("x5263").ctrl(x5264).srcCtx("SPMV_CRS.scala:87:65") // UnrolledForeach(List(b2651),x5249,Block(Const(())),List(List(b3007)),List(List(b3008)))
    val b3007 = CounterIter(x5248, None).name("b3007").ctrl(x5263) // b3007
    val b3008 = Const(true).name("b3008").ctrl(x5263) // b3008
    val x5250 = ReadMem(x5212).name("x5250").ctrl(x5263).srcCtx("SPMV_CRS.scala:87:65") // RegRead(x5212)
    val x5251 = OpDef(op=FixLeq, inputs=List(x5250, b3007)).name("x5251").ctrl(x5263).srcCtx("SPMV_CRS.scala:87:65") // FixLeq(x5250,b3007)
    val x5252 = ReadMem(x5213).name("x5252").ctrl(x5263).srcCtx("SPMV_CRS.scala:87:65") // RegRead(x5213)
    val x5253 = OpDef(op=FixLt, inputs=List(b3007, x5252)).name("x5253").ctrl(x5263).srcCtx("SPMV_CRS.scala:87:65") // FixLt(b3007,x5252)
    val x5261_x5254 = OpDef(op=BitAnd, inputs=List(x5251, x5253)).name("x5261_x5254").ctrl(x5263).srcCtx("SPMV_CRS.scala:87:65") // And(x5251,x5253)
    val x5255 = OpDef(op=FixSub, inputs=List(b3007, x5250)).name("x5255").ctrl(x5263).srcCtx("SPMV_CRS.scala:87:65") // FixSub(b3007,x5250)
    val x5256 = OpDef(op=BitAnd, inputs=List(b3008, b2651)).name("x5256").ctrl(x5263).srcCtx("UnrollingBase.scala:28:66") // And(b3008,b2651)
    val x5257 = OpDef(op=BitAnd, inputs=List(x5261_x5254, x5256)).name("x5257").ctrl(x5263).srcCtx("UnrollingTransformer.scala:270:41") // And(x5254,x5256)
    val x5258 = LoadBanks(List(x4892_d0_b0), List(x5255)).name("x5258").ctrl(x5263).srcCtx("SPMV_CRS.scala:87:65") // ParSRAMLoad(x4892,List(List(x5255)),List(x5257))
    val x5259 = x5258 // x5259 = VectorApply(x5258,0)
    val x5261_x5260 = OpDef(op=MuxOp, inputs=List(x5261_x5254, x5259, Const(0.0))).name("x5261_x5260").ctrl(x5263).srcCtx("SPMV_CRS.scala:87:65") // Mux(x5254,x5259,Const(0))
    // x5261 = SimpleStruct(ArrayBuffer((_1,x5260), (_2,x5254)))
    val x5262_x5262_x5210 = WriteMem(x5210, x5261_x5260).name("x5262_x5262_x5210").ctrl(x5263).srcCtx("SPMV_CRS.scala:87:65") // ParStreamWrite(x5210,List(x5261),List(x5256))
    val x5265 = FringeDenseStore(dram=List(x4865), cmdStream=List(b5347, b5348), dataStream=List(x5210), ackStream=List(x5211)).name("x5265").ctrl(x5268).srcCtx("SPMV_CRS.scala:87:65") // FringeDenseStore(x4865,x5209,x5210,x5211)
    val x5267 = UnitController(style=SeqPipe, level=InnerControl).name("x5267").ctrl(x5268).srcCtx("SPMV_CRS.scala:87:65") // UnitPipe(List(b2651),Block(Const(())))
    val x5266_x5266 = ReadMem(x5211).name("x5266_x5266").ctrl(x5267).srcCtx("SPMV_CRS.scala:87:65") // StreamRead(x5211,b2651)
    }; split1
    
  }
}
