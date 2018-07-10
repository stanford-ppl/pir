import pir._
import pir.node._
import arch._
import prism.enums._

object BFS extends PIRApp {
  def main(implicit design:PIRDesign) = {
    import design.pirmeta._
    val x3090 = DRAM(dims=List(Const(9600000))).name("x3090").ctrl(top).srcCtx("BFS.scala:15:26:edges") // x3090 = DRAMNew(ArrayBuffer(Const(9600000)),Const(0))
    val x3091 = DRAM(dims=List(Const(8000))).name("x3091").ctrl(top).srcCtx("BFS.scala:16:27:counts") // x3091 = DRAMNew(ArrayBuffer(Const(8000)),Const(0))
    val x3092 = DRAM(dims=List(Const(8000))).name("x3092").ctrl(top).srcCtx("BFS.scala:17:24:ids") // x3092 = DRAMNew(ArrayBuffer(Const(8000)),Const(0))
    val x3093 = DRAM(dims=List(Const(8000))).name("x3093").ctrl(top).srcCtx("BFS.scala:18:27:result") // x3093 = DRAMNew(ArrayBuffer(Const(8000)),Const(0))
    val x3097 = ArgIn(init=0).name("x3097").ctrl(top).srcCtx("BFS.scala:24:22:depth") // ArgInNew(Const(0))
    isAccum(x3097) = false
    bufferDepthOf(x3097) = 1
    val x3099 = ArgIn(init=0).name("x3099").ctrl(top).srcCtx("BFS.scala:27:21:anpe") // ArgInNew(Const(0))
    isAccum(x3099) = false
    bufferDepthOf(x3099) = 1
    val x3330 = UnitController(style=SeqPipe, level=OuterControl).name("x3330").ctrl(top).srcCtx("BFS.scala:30:11") // Hwblock(Block(Const(())),false)
    val x3101_d0_b0 = SRAM(size=8000, banking=Strided(banks=1, stride=1)).name("x3101_d0_b0").ctrl(x3330).srcCtx("BFS.scala:31:36:frontierNodes") // x3101 = SRAMNew(ArrayBuffer(Const(8000)))
    isAccum(x3101_d0_b0) = false
    bufferDepthOf(x3101_d0_b0) = 1
    val x3102_d0_b0 = SRAM(size=8000, banking=Strided(banks=1, stride=1)).name("x3102_d0_b0").ctrl(x3330).srcCtx("BFS.scala:32:37:frontierCounts") // x3102 = SRAMNew(ArrayBuffer(Const(8000)))
    isAccum(x3102_d0_b0) = false
    bufferDepthOf(x3102_d0_b0) = 1
    val x3103_d0_b0 = SRAM(size=8000, banking=Strided(banks=1, stride=1)).name("x3103_d0_b0").ctrl(x3330).srcCtx("BFS.scala:33:34:frontierIds") // x3103 = SRAMNew(ArrayBuffer(Const(8000)))
    isAccum(x3103_d0_b0) = false
    bufferDepthOf(x3103_d0_b0) = 1
    val x3104_d0_b0 = SRAM(size=8000, banking=Strided(banks=1, stride=1)).name("x3104_d0_b0").ctrl(x3330).srcCtx("BFS.scala:34:37:frontierLevels") // x3104 = SRAMNew(ArrayBuffer(Const(8000)))
    isAccum(x3104_d0_b0) = false
    bufferDepthOf(x3104_d0_b0) = 1
    val x3104_d1_b0 = SRAM(size=8000, banking=Strided(banks=1, stride=1)).name("x3104_d1_b0").ctrl(x3330).srcCtx("BFS.scala:34:37:frontierLevels") // x3104 = SRAMNew(ArrayBuffer(Const(8000)))
    isAccum(x3104_d1_b0) = false
    bufferDepthOf(x3104_d1_b0) = 1
    val x3105_d0_b0 = SRAM(size=8000, banking=Strided(banks=1, stride=1)).name("x3105_d0_b0").ctrl(x3330).srcCtx("BFS.scala:35:35:currentNodes") // x3105 = SRAMNew(ArrayBuffer(Const(8000)))
    isAccum(x3105_d0_b0) = false
    bufferDepthOf(x3105_d0_b0) = 1
    val x3105_d1_b0 = SRAM(size=8000, banking=Strided(banks=1, stride=1)).name("x3105_d1_b0").ctrl(x3330).srcCtx("BFS.scala:35:35:currentNodes") // x3105 = SRAMNew(ArrayBuffer(Const(8000)))
    isAccum(x3105_d1_b0) = false
    bufferDepthOf(x3105_d1_b0) = 1
    val x3105_d2_b0 = SRAM(size=8000, banking=Strided(banks=1, stride=1)).name("x3105_d2_b0").ctrl(x3330).srcCtx("BFS.scala:35:35:currentNodes") // x3105 = SRAMNew(ArrayBuffer(Const(8000)))
    isAccum(x3105_d2_b0) = false
    bufferDepthOf(x3105_d2_b0) = 1
    val x3106_d0_b0 = SRAM(size=8000, banking=Strided(banks=1, stride=1)).name("x3106_d0_b0").ctrl(x3330).srcCtx("BFS.scala:36:31:pieceMem") // x3106 = SRAMNew(ArrayBuffer(Const(8000)))
    isAccum(x3106_d0_b0) = false
    bufferDepthOf(x3106_d0_b0) = 1
    val x3107_d0 = Reg(init=Some(0)).name("x3107_d0").ctrl(x3330).srcCtx("BFS.scala:38:31:concatReg") // x3107 = RegNew(Const(0))
    isAccum(x3107_d0) = false
    bufferDepthOf(x3107_d0) = 1
    val x3107_d1 = Reg(init=Some(0)).name("x3107_d1").ctrl(x3330).srcCtx("BFS.scala:38:31:concatReg") // x3107 = RegNew(Const(0))
    isAccum(x3107_d1) = false
    bufferDepthOf(x3107_d1) = 1
    val x3107_d2 = Reg(init=Some(0)).name("x3107_d2").ctrl(x3330).srcCtx("BFS.scala:38:31:concatReg") // x3107 = RegNew(Const(0))
    isAccum(x3107_d2) = false
    bufferDepthOf(x3107_d2) = 1
    val x3107_d3 = Reg(init=Some(0)).name("x3107_d3").ctrl(x3330).srcCtx("BFS.scala:38:31:concatReg") // x3107 = RegNew(Const(0))
    isAccum(x3107_d3) = false
    bufferDepthOf(x3107_d3) = 1
    val x3107_d4 = Reg(init=Some(0)).name("x3107_d4").ctrl(x3330).srcCtx("BFS.scala:38:31:concatReg") // x3107 = RegNew(Const(0))
    isAccum(x3107_d4) = false
    bufferDepthOf(x3107_d4) = 1
    val x3107_d5 = Reg(init=Some(0)).name("x3107_d5").ctrl(x3330).srcCtx("BFS.scala:38:31:concatReg") // x3107 = RegNew(Const(0))
    isAccum(x3107_d5) = true
    bufferDepthOf(x3107_d5) = 1
    val x3107_d6 = Reg(init=Some(0)).name("x3107_d6").ctrl(x3330).srcCtx("BFS.scala:38:31:concatReg") // x3107 = RegNew(Const(0))
    isAccum(x3107_d6) = false
    bufferDepthOf(x3107_d6) = 1
    val x3108 = Reg(init=Some(1)).name("x3108").ctrl(x3330).srcCtx("BFS.scala:39:30:numEdges") // x3108 = RegNew(Const(1))
    isAccum(x3108) = false
    bufferDepthOf(x3108) = 1
    val x3109 = ReadMem(x3099).name("x3109").ctrl(x3330).srcCtx("BFS.scala:42:15") // RegRead(x3099)
    val x3110 = Counter(min=Const(0), max=x3109, step=Const(1), par=1).name("x3110").ctrl(x3330).srcCtx("BFS.scala:42:20") // CounterNew(Const(0),x3109,Const(1),Const(1))
    val x3111 = CounterChain(List(x3110)).name("x3111").ctrl(x3330).srcCtx("BFS.scala:42:25") // CounterChainNew(List(x3110))
    val x3116 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3111).name("x3116").ctrl(x3330).srcCtx("BFS.scala:42:25") // UnrolledForeach(List(Const(true)),x3111,Block(Const(())),List(List(b1804)),List(List(b1805)))
    val b1804 = CounterIter(x3110, None).name("b1804").ctrl(x3116) // b1804
    val b1805 = Const(true).name("b1805").ctrl(x3116) // b1805
    val x3112 = StoreBanks(List(x3101_d0_b0), List(b1804), Const(0)).name("x3112").ctrl(x3116).srcCtx("BFS.scala:43:26") // ParSRAMStore(x3101,List(List(b1804)),List(Const(0)),List(b1805))
    val x3113 = StoreBanks(List(x3104_d0_b0, x3104_d1_b0), List(b1804), Const(0)).name("x3113").ctrl(x3116).srcCtx("BFS.scala:45:27") // ParSRAMStore(x3104,List(List(b1804)),List(Const(0)),List(b1805))
    val x3114 = StoreBanks(List(x3105_d0_b0, x3105_d1_b0, x3105_d2_b0), List(b1804), Const(0)).name("x3114").ctrl(x3116).srcCtx("BFS.scala:46:25") // ParSRAMStore(x3105,List(List(b1804)),List(Const(0)),List(b1805))
    val x3115 = StoreBanks(List(x3106_d0_b0), List(b1804), Const(0)).name("x3115").ctrl(x3116).srcCtx("BFS.scala:47:21") // ParSRAMStore(x3106,List(List(b1804)),List(Const(0)),List(b1805))
    val x3153 = UnitController(style=ForkJoin, level=OuterControl).name("x3153").ctrl(x3330).srcCtx("BFS.scala:49:16") // ParallelPipe(List(Const(true)),Block(Const(())))
    val x3134 = UnitController(style=StreamPipe, level=OuterControl).name("x3134").ctrl(x3153).srcCtx("BFS.scala:50:21") // UnitPipe(List(Const(true)),Block(Const(())))
    val b3348 = StreamOut(field="offset").name("b3348").ctrl(x3134).srcCtx("BFS.scala:50:21") // x3117 = StreamOutNew(BurstCmdBus)
    isAccum(b3348) = false
    bufferDepthOf(b3348) = 1
    val b3349 = StreamOut(field="size").name("b3349").ctrl(x3134).srcCtx("BFS.scala:50:21") // x3117 = StreamOutNew(BurstCmdBus)
    isAccum(b3349) = false
    bufferDepthOf(b3349) = 1
    val x3118 = StreamIn(field="data").name("x3118").ctrl(x3134).srcCtx("BFS.scala:50:21") // x3118 = StreamInNew(BurstDataBus())
    isAccum(x3118) = false
    bufferDepthOf(x3118) = 1
    val x3126 = UnitController(style=SeqPipe, level=InnerControl).name("x3126").ctrl(x3134).srcCtx("BFS.scala:50:21") // UnitPipe(List(Const(true)),Block(x3125))
    val x3119 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x3120 = OpDef(op=FixSla, inputs=List(x3119, Const(2))).name("x3120").ctrl(x3126).srcCtx("BFS.scala:50:21") // FixLsh(x3119,Const(2))
    val x3121 = x3120 // FixConvert(x3120,TRUE,_64,_0)
    val x3122 = DramAddress(x3092).name("x3122").ctrl(x3126).srcCtx("BFS.scala:50:21") // GetDRAMAddress(x3092)
    val x3124_x3123 = OpDef(op=FixAdd, inputs=List(x3121, x3122)).name("x3124_x3123").ctrl(x3126).srcCtx("BFS.scala:50:21") // FixAdd(x3121,x3122)
    // x3124 = SimpleStruct(ArrayBuffer((offset,x3123), (size,Const(32000)), (isLoad,Const(true))))
    val x3125_b3350_b3348 = WriteMem(b3348, x3124_x3123).name("x3125_b3350_b3348").ctrl(x3126).srcCtx("BFS.scala:50:21") // StreamWrite(x3117,x3124,Const(true))
    val x3125_b3351_b3349 = WriteMem(b3349, Const(32000)).name("x3125_b3351_b3349").ctrl(x3126).srcCtx("BFS.scala:50:21") // StreamWrite(x3117,x3124,Const(true))
    val x3127 = FringeDenseLoad(dram=List(x3092), cmdStream=List(b3348, b3349), dataStream=List(x3118)).name("x3127").ctrl(x3134).srcCtx("BFS.scala:50:21") // FringeDenseLoad(x3092,x3117,x3118)
    val x3128 = Counter(min=Const(0), max=Const(8000), step=Const(1), par=1).name("x3128").ctrl(x3134).srcCtx("BFS.scala:50:21") // CounterNew(Const(0),Const(8000),Const(1),Const(1))
    val x3129 = CounterChain(List(x3128)).name("x3129").ctrl(x3134).srcCtx("BFS.scala:50:21") // CounterChainNew(List(x3128))
    val x3133 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3129).name("x3133").ctrl(x3134).srcCtx("BFS.scala:50:21") // UnrolledForeach(List(Const(true)),x3129,Block(Const(())),List(List(b1824)),List(List(b1825)))
    val b1824 = CounterIter(x3128, None).name("b1824").ctrl(x3133) // b1824
    val b1825 = Const(true).name("b1825").ctrl(x3133) // b1825
    val x3130_x3130 = ReadMem(x3118).name("x3130_x3130").ctrl(x3133).srcCtx("BFS.scala:50:21") // ParStreamRead(x3118,List(b1825))
    val x3131_x3131 = x3130_x3130 // x3131 = VectorApply(x3130,0)
    val x3132 = StoreBanks(List(x3103_d0_b0), List(b1824), x3131_x3131).name("x3132").ctrl(x3133).srcCtx("BFS.scala:50:21") // ParSRAMStore(x3103,List(List(b1824)),List(x3131),List(b1825))
    val x3152 = UnitController(style=StreamPipe, level=OuterControl).name("x3152").ctrl(x3153).srcCtx("BFS.scala:51:24") // UnitPipe(List(Const(true)),Block(Const(())))
    val b3352 = StreamOut(field="offset").name("b3352").ctrl(x3152).srcCtx("BFS.scala:51:24") // x3135 = StreamOutNew(BurstCmdBus)
    isAccum(b3352) = false
    bufferDepthOf(b3352) = 1
    val b3353 = StreamOut(field="size").name("b3353").ctrl(x3152).srcCtx("BFS.scala:51:24") // x3135 = StreamOutNew(BurstCmdBus)
    isAccum(b3353) = false
    bufferDepthOf(b3353) = 1
    val x3136 = StreamIn(field="data").name("x3136").ctrl(x3152).srcCtx("BFS.scala:51:24") // x3136 = StreamInNew(BurstDataBus())
    isAccum(x3136) = false
    bufferDepthOf(x3136) = 1
    val x3144 = UnitController(style=SeqPipe, level=InnerControl).name("x3144").ctrl(x3152).srcCtx("BFS.scala:51:24") // UnitPipe(List(Const(true)),Block(x3143))
    val x3137 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x3138 = OpDef(op=FixSla, inputs=List(x3137, Const(2))).name("x3138").ctrl(x3144).srcCtx("BFS.scala:51:24") // FixLsh(x3137,Const(2))
    val x3139 = x3138 // FixConvert(x3138,TRUE,_64,_0)
    val x3140 = DramAddress(x3091).name("x3140").ctrl(x3144).srcCtx("BFS.scala:51:24") // GetDRAMAddress(x3091)
    val x3142_x3141 = OpDef(op=FixAdd, inputs=List(x3139, x3140)).name("x3142_x3141").ctrl(x3144).srcCtx("BFS.scala:51:24") // FixAdd(x3139,x3140)
    // x3142 = SimpleStruct(ArrayBuffer((offset,x3141), (size,Const(32000)), (isLoad,Const(true))))
    val x3143_b3354_b3352 = WriteMem(b3352, x3142_x3141).name("x3143_b3354_b3352").ctrl(x3144).srcCtx("BFS.scala:51:24") // StreamWrite(x3135,x3142,Const(true))
    val x3143_b3355_b3353 = WriteMem(b3353, Const(32000)).name("x3143_b3355_b3353").ctrl(x3144).srcCtx("BFS.scala:51:24") // StreamWrite(x3135,x3142,Const(true))
    val x3145 = FringeDenseLoad(dram=List(x3091), cmdStream=List(b3352, b3353), dataStream=List(x3136)).name("x3145").ctrl(x3152).srcCtx("BFS.scala:51:24") // FringeDenseLoad(x3091,x3135,x3136)
    val x3146 = Counter(min=Const(0), max=Const(8000), step=Const(1), par=1).name("x3146").ctrl(x3152).srcCtx("BFS.scala:51:24") // CounterNew(Const(0),Const(8000),Const(1),Const(1))
    val x3147 = CounterChain(List(x3146)).name("x3147").ctrl(x3152).srcCtx("BFS.scala:51:24") // CounterChainNew(List(x3146))
    val x3151 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3147).name("x3151").ctrl(x3152).srcCtx("BFS.scala:51:24") // UnrolledForeach(List(Const(true)),x3147,Block(Const(())),List(List(b1844)),List(List(b1845)))
    val b1844 = CounterIter(x3146, None).name("b1844").ctrl(x3151) // b1844
    val b1845 = Const(true).name("b1845").ctrl(x3151) // b1845
    val x3148_x3148 = ReadMem(x3136).name("x3148_x3148").ctrl(x3151).srcCtx("BFS.scala:51:24") // ParStreamRead(x3136,List(b1845))
    val x3149_x3149 = x3148_x3148 // x3149 = VectorApply(x3148,0)
    val x3150 = StoreBanks(List(x3102_d0_b0), List(b1844), x3149_x3149).name("x3150").ctrl(x3151).srcCtx("BFS.scala:51:24") // ParSRAMStore(x3102,List(List(b1844)),List(x3149),List(b1845))
    val x3154 = ReadMem(x3097).name("x3154").ctrl(x3330).srcCtx("BFS.scala:54:32") // RegRead(x3097)
    val x3155 = Counter(min=Const(0), max=x3154, step=Const(1), par=1).name("x3155").ctrl(x3330).srcCtx("BFS.scala:54:38") // CounterNew(Const(0),x3154,Const(1),Const(1))
    val x3156 = CounterChain(List(x3155)).name("x3156").ctrl(x3330).srcCtx("BFS.scala:54:44") // CounterChainNew(List(x3155))
    val x3329 = LoopController(style=SeqPipe, level=OuterControl, cchain=x3156).name("x3329").ctrl(x3330).srcCtx("BFS.scala:54:44") // UnrolledForeach(List(Const(true)),x3156,Block(Const(())),List(List(b1855)),List(List(b1856)))
    val b1855 = CounterIter(x3155, Some(0)).name("b1855").ctrl(x3329) // b1855
    val b1856 = Const(true).name("b1856").ctrl(x3329) // b1856
    val x3157_d0 = Reg(init=Some(1)).name("x3157_d0").ctrl(x3329).srcCtx("BFS.scala:55:31:nextLen") // x3157 = RegNew(Const(1))
    isAccum(x3157_d0) = false
    bufferDepthOf(x3157_d0) = 1
    val x3157_d1 = Reg(init=Some(1)).name("x3157_d1").ctrl(x3329).srcCtx("BFS.scala:55:31:nextLen") // x3157 = RegNew(Const(1))
    isAccum(x3157_d1) = false
    bufferDepthOf(x3157_d1) = 1
    val x3157_d2 = Reg(init=Some(1)).name("x3157_d2").ctrl(x3329).srcCtx("BFS.scala:55:31:nextLen") // x3157 = RegNew(Const(1))
    isAccum(x3157_d2) = false
    bufferDepthOf(x3157_d2) = 1
    val x3158_d0 = Reg(init=Some(1)).name("x3158_d0").ctrl(x3329).srcCtx("BFS.scala:56:30:x$3") // x3158 = RegNew(Const(1))
    isAccum(x3158_d0) = false
    bufferDepthOf(x3158_d0) = 1
    val x3158_d1 = Reg(init=Some(1)).name("x3158_d1").ctrl(x3329).srcCtx("BFS.scala:56:30:x$3") // x3158 = RegNew(Const(1))
    isAccum(x3158_d1) = false
    bufferDepthOf(x3158_d1) = 1
    val x3159 = ReadMem(x3108).name("x3159").ctrl(x3329).srcCtx("BFS.scala:57:28") // RegRead(x3108)
    val x3160 = Counter(min=Const(0), max=x3159, step=Const(1), par=1).name("x3160").ctrl(x3329).srcCtx("BFS.scala:57:37") // CounterNew(Const(0),x3159,Const(1),Const(1))
    val x3161 = CounterChain(List(x3160)).name("x3161").ctrl(x3329).srcCtx("BFS.scala:57:43") // CounterChainNew(List(x3160))
    val x3259 = LoopController(style=SeqPipe, level=OuterControl, cchain=x3161).name("x3259").ctrl(x3329).srcCtx("BFS.scala:57:43") // UnrolledForeach(List(b1856),x3161,Block(Const(())),List(List(b1862)),List(List(b1863)))
    val b1862 = CounterIter(x3160, Some(0)).name("b1862").ctrl(x3259) // b1862
    val b1863 = Const(true).name("b1863").ctrl(x3259) // b1863
    val x3162 = Reg(init=Some(0)).name("x3162").ctrl(x3259).srcCtx("BFS.scala:57:43") // x3162 = RegNew(Const(0))
    isAccum(x3162) = false
    bufferDepthOf(x3162) = 1
    val x3173 = UnitController(style=SeqPipe, level=InnerControl).name("x3173").ctrl(x3259).srcCtx("BFS.scala:57:43") // UnitPipe(List(b1863, b1856),Block(Const(())))
    val x3163 = OpDef(op=BitAnd, inputs=List(b1863, b1856)).name("x3163").ctrl(x3173).srcCtx("UnrollingBase.scala:28:66") // And(b1863,b1856)
    val x3164 = LoadBanks(List(x3105_d2_b0), List(b1862)).name("x3164").ctrl(x3173).srcCtx("BFS.scala:60:35:fetch") // SRAMLoad(x3105,ArrayBuffer(Const(8000)),List(b1862),Const(0),x3163)
    val x3165 = LoadBanks(List(x3103_d0_b0), List(x3164)).name("x3165").ctrl(x3173).srcCtx("BFS.scala:62:32") // SRAMLoad(x3103,ArrayBuffer(Const(8000)),List(x3164),Const(0),x3163)
    val x3166_x3158_d0 = WriteMem(x3158_d0, x3165).name("x3166_x3158_d0").ctrl(x3173).srcCtx("BFS.scala:62:18") // RegWrite(x3158,x3165,x3163)
    val x3166_x3158_d1 = WriteMem(x3158_d1, x3165).name("x3166_x3158_d1").ctrl(x3173).srcCtx("BFS.scala:62:18") // RegWrite(x3158,x3165,x3163)
    val x3167 = LoadBanks(List(x3102_d0_b0), List(x3164)).name("x3167").ctrl(x3173).srcCtx("BFS.scala:63:36") // SRAMLoad(x3102,ArrayBuffer(Const(8000)),List(x3164),Const(0),x3163)
    val x3168_x3157_d0 = WriteMem(x3157_d0, x3167).name("x3168_x3157_d0").ctrl(x3173).srcCtx("BFS.scala:63:19") // RegWrite(x3157,x3167,x3163)
    val x3168_x3157_d1 = WriteMem(x3157_d1, x3167).name("x3168_x3157_d1").ctrl(x3173).srcCtx("BFS.scala:63:19") // RegWrite(x3157,x3167,x3163)
    val x3168_x3157_d2 = WriteMem(x3157_d2, x3167).name("x3168_x3157_d2").ctrl(x3173).srcCtx("BFS.scala:63:19") // RegWrite(x3157,x3167,x3163)
    val x3169 = ReadMem(x3158_d1).name("x3169").ctrl(x3173).srcCtx("BFS.scala:67:41") // RegRead(x3158)
    val x3170 = ReadMem(x3157_d2).name("x3170").ctrl(x3173).srcCtx("BFS.scala:67:50") // RegRead(x3157)
    val x3171 = OpDef(op=FixAdd, inputs=List(x3169, x3170)).name("x3171").ctrl(x3173).srcCtx("BFS.scala:67:48") // FixAdd(x3169,x3170)
    val x3172_x3162 = WriteMem(x3162, x3170).name("x3172_x3162").ctrl(x3173).srcCtx("BFS.scala:57:43") // RegWrite(x3162,x3170,x3163)
    val x3240 = UnitController(style=StreamPipe, level=OuterControl).name("x3240").ctrl(x3259).srcCtx("BFS.scala:67:20") // UnitPipe(List(b1863, b1856),Block(Const(())))
    val b3356 = StreamOut(field="offset").name("b3356").ctrl(x3240).srcCtx("BFS.scala:67:20") // x3174 = StreamOutNew(BurstCmdBus)
    isAccum(b3356) = false
    bufferDepthOf(b3356) = 1
    val b3357 = StreamOut(field="size").name("b3357").ctrl(x3240).srcCtx("BFS.scala:67:20") // x3174 = StreamOutNew(BurstCmdBus)
    isAccum(b3357) = false
    bufferDepthOf(b3357) = 1
    val b3358 = FIFO(size=16).name("b3358").ctrl(x3240).srcCtx("BFS.scala:67:20") // x3175 = FIFONew(Const(16))
    isAccum(b3358) = false
    bufferDepthOf(b3358) = 2
    val b3359 = FIFO(size=16).name("b3359").ctrl(x3240).srcCtx("BFS.scala:67:20") // x3175 = FIFONew(Const(16))
    isAccum(b3359) = false
    bufferDepthOf(b3359) = 2
    val b3360 = FIFO(size=16).name("b3360").ctrl(x3240).srcCtx("BFS.scala:67:20") // x3175 = FIFONew(Const(16))
    isAccum(b3360) = false
    bufferDepthOf(b3360) = 2
    val x3176 = StreamIn(field="data").name("x3176").ctrl(x3240).srcCtx("BFS.scala:67:20") // x3176 = StreamInNew(BurstDataBus())
    isAccum(x3176) = false
    bufferDepthOf(x3176) = 1
    val x3209 = UnitController(style=SeqPipe, level=InnerControl).name("x3209").ctrl(x3240).srcCtx("BFS.scala:67:20") // UnitPipe(List(b1863, b1856),Block(x3208))
    val x3177 = ReadMem(x3158_d0).name("x3177").ctrl(x3209).srcCtx("BFS.scala:67:38") // RegRead(x3158)
    val x3178 = x3177 // FixConvert(x3177,TRUE,_32,_0) (Same Type. No op)
    val x3179 = OpDef(op=FixSla, inputs=List(x3178, Const(2))).name("x3179").ctrl(x3209).srcCtx("BFS.scala:67:20") // FixLsh(x3178,Const(2))
    val x3180 = x3179 // x3180 = DataAsBits(x3179)
    val x3181 = OpDef(op=BitAnd, inputs=List(x3180, Const(31))).name("x3181").ctrl(x3209).srcCtx("BFS.scala:67:20") // VectorSlice(x3180,5,0) strMask=00000000000000000000000000011111
    val x3182 = x3181 // x3182 = BitsAsData(x3181,FixPt[TRUE,_32,_0])
    val x3183 = ReadMem(x3162).name("x3183").ctrl(x3209).srcCtx("BFS.scala:57:43") // RegRead(x3162)
    val x3184 = OpDef(op=FixSla, inputs=List(x3183, Const(2))).name("x3184").ctrl(x3209).srcCtx("BFS.scala:67:20") // FixLsh(x3183,Const(2))
    val x3185 = OpDef(op=FixSub, inputs=List(x3179, x3182)).name("x3185").ctrl(x3209).srcCtx("BFS.scala:67:20") // FixSub(x3179,x3182)
    val x3186 = OpDef(op=FixAdd, inputs=List(x3179, x3184)).name("x3186").ctrl(x3209).srcCtx("BFS.scala:67:20") // FixAdd(x3179,x3184)
    val x3187 = x3186 // x3187 = DataAsBits(x3186)
    val x3188 = OpDef(op=BitAnd, inputs=List(x3187, Const(31))).name("x3188").ctrl(x3209).srcCtx("BFS.scala:67:20") // VectorSlice(x3187,5,0) strMask=00000000000000000000000000011111
    val x3189 = x3188 // x3189 = BitsAsData(x3188,FixPt[TRUE,_32,_0])
    val x3190 = OpDef(op=FixEql, inputs=List(x3189, Const(0))).name("x3190").ctrl(x3209).srcCtx("BFS.scala:67:20") // FixEql(x3189,Const(0))
    val x3191 = OpDef(op=FixSub, inputs=List(Const(64), x3189)).name("x3191").ctrl(x3209).srcCtx("BFS.scala:67:20") // FixSub(Const(64),x3189)
    val x3192 = OpDef(op=MuxOp, inputs=List(x3190, Const(0), x3191)).name("x3192").ctrl(x3209).srcCtx("BFS.scala:67:20") // Mux(x3190,Const(0),x3191)
    val x3207_x3193 = OpDef(op=FixSra, inputs=List(x3182, Const(2))).name("x3207_x3193").ctrl(x3209).srcCtx("BFS.scala:67:20") // FixRsh(x3182,Const(2))
    val x3194 = OpDef(op=FixSra, inputs=List(x3192, Const(2))).name("x3194").ctrl(x3209).srcCtx("BFS.scala:67:20") // FixRsh(x3192,Const(2))
    val x3207_x3195 = OpDef(op=FixAdd, inputs=List(x3207_x3193, x3183)).name("x3207_x3195").ctrl(x3209).srcCtx("BFS.scala:67:20") // FixAdd(x3193,x3183)
    val x3196 = OpDef(op=FixAdd, inputs=List(x3183, x3207_x3193)).name("x3196").ctrl(x3209).srcCtx("BFS.scala:67:20") // FixAdd(x3183,x3193)
    val x3207_x3197 = OpDef(op=FixAdd, inputs=List(x3196, x3194)).name("x3207_x3197").ctrl(x3209).srcCtx("BFS.scala:67:20") // FixAdd(x3196,x3194)
    val x3198 = OpDef(op=FixAdd, inputs=List(x3184, x3182)).name("x3198").ctrl(x3209).srcCtx("BFS.scala:67:20") // FixAdd(x3184,x3182)
    val x3204_x3199 = OpDef(op=FixAdd, inputs=List(x3198, x3192)).name("x3204_x3199").ctrl(x3209).srcCtx("BFS.scala:67:20") // FixAdd(x3198,x3192)
    val x3200 = x3185 // FixConvert(x3185,TRUE,_64,_0)
    val x3201 = DramAddress(x3090).name("x3201").ctrl(x3209).srcCtx("BFS.scala:67:20") // GetDRAMAddress(x3090)
    val x3202 = OpDef(op=FixAdd, inputs=List(x3200, x3201)).name("x3202").ctrl(x3209).srcCtx("BFS.scala:67:20") // FixAdd(x3200,x3201)
    val x3204_x3203 = x3202 // FixConvert(x3202,TRUE,_64,_0) (Same Type. No op)
    // x3204 = SimpleStruct(ArrayBuffer((offset,x3203), (size,x3199), (isLoad,Const(true))))
    val x3205 = OpDef(op=BitAnd, inputs=List(b1863, b1856)).name("x3205").ctrl(x3209).srcCtx("UnrollingBase.scala:28:66") // And(b1863,b1856)
    val x3206_b3361_b3356 = WriteMem(b3356, x3204_x3203).name("x3206_b3361_b3356").ctrl(x3209).srcCtx("BFS.scala:67:20") // StreamWrite(x3174,x3204,x3205)
    val x3206_b3362_b3357 = WriteMem(b3357, x3204_x3199).name("x3206_b3362_b3357").ctrl(x3209).srcCtx("BFS.scala:67:20") // StreamWrite(x3174,x3204,x3205)
    // x3207 = SimpleStruct(ArrayBuffer((size,x3197), (start,x3193), (end,x3195)))
    val x3208_b3363_b3358 = WriteMem(b3358, x3207_x3197).name("x3208_b3363_b3358").ctrl(x3209).srcCtx("BFS.scala:67:20") // FIFOEnq(x3175,x3207,x3205)
    val x3208_b3364_b3359 = WriteMem(b3359, x3207_x3193).name("x3208_b3364_b3359").ctrl(x3209).srcCtx("BFS.scala:67:20") // FIFOEnq(x3175,x3207,x3205)
    val x3208_b3365_b3360 = WriteMem(b3360, x3207_x3195).name("x3208_b3365_b3360").ctrl(x3209).srcCtx("BFS.scala:67:20") // FIFOEnq(x3175,x3207,x3205)
    val x3210 = FringeDenseLoad(dram=List(x3090), cmdStream=List(b3356, b3357), dataStream=List(x3176)).name("x3210").ctrl(x3240).srcCtx("BFS.scala:67:20") // FringeDenseLoad(x3090,x3174,x3176)
    val x3239 = UnitController(style=SeqPipe, level=OuterControl).name("x3239").ctrl(x3240).srcCtx("BFS.scala:67:20") // UnitPipe(List(b1863, b1856),Block(Const(())))
    val x3211 = Reg(init=Some(0)).name("x3211").ctrl(x3239).srcCtx("BFS.scala:67:20") // x3211 = RegNew(Const(0))
    isAccum(x3211) = false
    bufferDepthOf(x3211) = 1
    val x3212 = Reg(init=Some(0)).name("x3212").ctrl(x3239).srcCtx("BFS.scala:67:20") // x3212 = RegNew(Const(0))
    isAccum(x3212) = false
    bufferDepthOf(x3212) = 1
    val x3213 = Reg(init=Some(0)).name("x3213").ctrl(x3239).srcCtx("BFS.scala:67:20") // x3213 = RegNew(Const(0))
    isAccum(x3213) = false
    bufferDepthOf(x3213) = 1
    val x3222 = UnitController(style=SeqPipe, level=InnerControl).name("x3222").ctrl(x3239).srcCtx("BFS.scala:67:20") // UnitPipe(List(b1863, b1856),Block(x3221))
    val x3214 = OpDef(op=BitAnd, inputs=List(b1863, b1856)).name("x3214").ctrl(x3222).srcCtx("UnrollingBase.scala:28:66") // And(b1863,b1856)
    val x3215_b3366 = ReadMem(b3358).name("x3215_b3366").ctrl(x3222).srcCtx("BFS.scala:67:20") // FIFODeq(x3175,x3214)
    val x3215_b3367 = ReadMem(b3359).name("x3215_b3367").ctrl(x3222).srcCtx("BFS.scala:67:20") // FIFODeq(x3175,x3214)
    val x3215_b3368 = ReadMem(b3360).name("x3215_b3368").ctrl(x3222).srcCtx("BFS.scala:67:20") // FIFODeq(x3175,x3214)
    val x3216 = x3215_b3367 // x3216 = FieldApply(x3215,start)
    val x3217_x3211 = WriteMem(x3211, x3216).name("x3217_x3211").ctrl(x3222).srcCtx("BFS.scala:67:20") // RegWrite(x3211,x3216,x3214)
    val x3218 = x3215_b3368 // x3218 = FieldApply(x3215,end)
    val x3219_x3212 = WriteMem(x3212, x3218).name("x3219_x3212").ctrl(x3222).srcCtx("BFS.scala:67:20") // RegWrite(x3212,x3218,x3214)
    val x3220 = x3215_b3366 // x3220 = FieldApply(x3215,size)
    val x3221_x3213 = WriteMem(x3213, x3220).name("x3221_x3213").ctrl(x3222).srcCtx("BFS.scala:67:20") // RegWrite(x3213,x3220,x3214)
    val x3223 = ReadMem(x3213).name("x3223").ctrl(x3239).srcCtx("BFS.scala:67:20") // RegRead(x3213)
    val x3224 = Counter(min=Const(0), max=x3223, step=Const(1), par=1).name("x3224").ctrl(x3239).srcCtx("BFS.scala:67:20") // CounterNew(Const(0),x3223,Const(1),Const(1))
    val x3225 = CounterChain(List(x3224)).name("x3225").ctrl(x3239).srcCtx("BFS.scala:67:20") // CounterChainNew(List(x3224))
    val x3238 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3225).name("x3238").ctrl(x3239).srcCtx("BFS.scala:67:20") // UnrolledForeach(List(b1863, b1856),x3225,Block(Const(())),List(List(b1924)),List(List(b1925)))
    val b1924 = CounterIter(x3224, None).name("b1924").ctrl(x3238) // b1924
    val b1925 = Const(true).name("b1925").ctrl(x3238) // b1925
    val x3226 = ReadMem(x3211).name("x3226").ctrl(x3238).srcCtx("BFS.scala:67:20") // RegRead(x3211)
    val x3227 = OpDef(op=FixLeq, inputs=List(x3226, b1924)).name("x3227").ctrl(x3238).srcCtx("BFS.scala:67:20") // FixLeq(x3226,b1924)
    val x3228 = ReadMem(x3212).name("x3228").ctrl(x3238).srcCtx("BFS.scala:67:20") // RegRead(x3212)
    val x3229 = OpDef(op=FixLt, inputs=List(b1924, x3228)).name("x3229").ctrl(x3238).srcCtx("BFS.scala:67:20") // FixLt(b1924,x3228)
    val x3230 = OpDef(op=BitAnd, inputs=List(x3227, x3229)).name("x3230").ctrl(x3238).srcCtx("BFS.scala:67:20") // And(x3227,x3229)
    val x3231 = OpDef(op=FixSub, inputs=List(b1924, x3226)).name("x3231").ctrl(x3238).srcCtx("BFS.scala:67:20") // FixSub(b1924,x3226)
    val x3232 = OpDef(op=BitAnd, inputs=List(b1925, b1863)).name("x3232").ctrl(x3238).srcCtx("UnrollingBase.scala:28:66") // And(b1925,b1863)
    val x3233 = OpDef(op=BitAnd, inputs=List(x3232, b1856)).name("x3233").ctrl(x3238).srcCtx("UnrollingBase.scala:28:66") // And(x3232,b1856)
    val x3234_x3234 = ReadMem(x3176).name("x3234_x3234").ctrl(x3238).srcCtx("BFS.scala:67:20") // ParStreamRead(x3176,List(x3233))
    val x3235_x3235 = x3234_x3234 // x3235 = VectorApply(x3234,0)
    val x3236 = OpDef(op=BitAnd, inputs=List(x3230, x3233)).name("x3236").ctrl(x3238).srcCtx("UnrollingTransformer.scala:239:41") // And(x3230,x3233)
    val x3237 = StoreBanks(List(x3106_d0_b0), List(x3231), x3235_x3235).name("x3237").ctrl(x3238).srcCtx("BFS.scala:67:20") // ParSRAMStore(x3106,List(List(x3231)),List(x3235),List(x3236))
    val x3241 = ReadMem(x3157_d1).name("x3241").ctrl(x3259).srcCtx("BFS.scala:70:19") // RegRead(x3157)
    val x3242 = Counter(min=Const(0), max=x3241, step=Const(1), par=1).name("x3242").ctrl(x3259).srcCtx("BFS.scala:70:27") // CounterNew(Const(0),x3241,Const(1),Const(1))
    val x3243 = CounterChain(List(x3242)).name("x3243").ctrl(x3259).srcCtx("BFS.scala:70:33") // CounterChainNew(List(x3242))
    val x3251 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3243).name("x3251").ctrl(x3259).srcCtx("BFS.scala:70:33") // UnrolledForeach(List(b1863, b1856),x3243,Block(Const(())),List(List(b1944)),List(List(b1945)))
    val b1944 = CounterIter(x3242, None).name("b1944").ctrl(x3251) // b1944
    val b1945 = Const(true).name("b1945").ctrl(x3251) // b1945
    val x3244 = ReadMem(x3107_d6).name("x3244").ctrl(x3251).srcCtx("BFS.scala:79:47") // RegRead(x3107)
    val x3245 = OpDef(op=FixAdd, inputs=List(b1944, x3244)).name("x3245").ctrl(x3251).srcCtx("BFS.scala:79:35:frontierAddr") // FixAdd(b1944,x3244)
    val x3246 = OpDef(op=BitAnd, inputs=List(b1945, b1863)).name("x3246").ctrl(x3251).srcCtx("UnrollingBase.scala:28:66") // And(b1945,b1863)
    val x3247 = OpDef(op=BitAnd, inputs=List(x3246, b1856)).name("x3247").ctrl(x3251).srcCtx("UnrollingBase.scala:28:66") // And(x3246,b1856)
    val x3248 = LoadBanks(List(x3106_d0_b0), List(b1944)).name("x3248").ctrl(x3251).srcCtx("BFS.scala:80:51") // ParSRAMLoad(x3106,List(List(b1944)),List(x3247))
    val x3249 = x3248 // x3249 = VectorApply(x3248,0)
    val x3250 = StoreBanks(List(x3101_d0_b0), List(x3245), x3249).name("x3250").ctrl(x3251).srcCtx("BFS.scala:80:41") // ParSRAMStore(x3101,List(List(x3245)),List(x3249),List(x3247))
    val x3258 = UnitController(style=SeqPipe, level=InnerControl).name("x3258").ctrl(x3259).srcCtx("BFS.scala:57:43") // UnitPipe(List(b1863, b1856),Block(Const(())))
    val x3252 = ReadMem(x3157_d0).name("x3252").ctrl(x3258).srcCtx("BFS.scala:82:44") // RegRead(x3157)
    val x3253 = ReadMem(x3107_d5).name("x3253").ctrl(x3258).srcCtx("BFS.scala:82:32") // RegRead(x3107)
    val x3254 = OpDef(op=FixAdd, inputs=List(x3253, x3252)).name("x3254").ctrl(x3258).srcCtx("BFS.scala:82:42") // FixAdd(x3253,x3252)
    val x3255 = OpDef(op=FixMin, inputs=List(Const(8000), x3254)).name("x3255").ctrl(x3258).srcCtx("BFS.scala:82:27") // Min(Const(8000),x3254)
    val x3256 = OpDef(op=BitAnd, inputs=List(b1863, b1856)).name("x3256").ctrl(x3258).srcCtx("UnrollingBase.scala:28:66") // And(b1863,b1856)
    val x3257_x3107_d0 = WriteMem(x3107_d0, x3255).name("x3257_x3107_d0").ctrl(x3258).srcCtx("BFS.scala:82:21") // RegWrite(x3107,x3255,x3256)
    val x3257_x3107_d5 = WriteMem(x3107_d5, x3255).name("x3257_x3107_d5").ctrl(x3258).srcCtx("BFS.scala:82:21") // RegWrite(x3107,x3255,x3256)
    val x3257_x3107_d1 = WriteMem(x3107_d1, x3255).name("x3257_x3107_d1").ctrl(x3258).srcCtx("BFS.scala:82:21") // RegWrite(x3107,x3255,x3256)
    val x3257_x3107_d6 = WriteMem(x3107_d6, x3255).name("x3257_x3107_d6").ctrl(x3258).srcCtx("BFS.scala:82:21") // RegWrite(x3107,x3255,x3256)
    val x3257_x3107_d2 = WriteMem(x3107_d2, x3255).name("x3257_x3107_d2").ctrl(x3258).srcCtx("BFS.scala:82:21") // RegWrite(x3107,x3255,x3256)
    val x3257_x3107_d3 = WriteMem(x3107_d3, x3255).name("x3257_x3107_d3").ctrl(x3258).srcCtx("BFS.scala:82:21") // RegWrite(x3107,x3255,x3256)
    val x3257_x3107_d4 = WriteMem(x3107_d4, x3255).name("x3257_x3107_d4").ctrl(x3258).srcCtx("BFS.scala:82:21") // RegWrite(x3107,x3255,x3256)
    val x3260 = ReadMem(x3107_d4).name("x3260").ctrl(x3329).srcCtx("BFS.scala:85:17") // RegRead(x3107)
    val x3261 = Counter(min=Const(0), max=x3260, step=Const(1), par=1).name("x3261").ctrl(x3329).srcCtx("BFS.scala:85:27") // CounterNew(Const(0),x3260,Const(1),Const(1))
    val x3262 = CounterChain(List(x3261)).name("x3262").ctrl(x3329).srcCtx("BFS.scala:85:33") // CounterChainNew(List(x3261))
    val x3267 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3262).name("x3267").ctrl(x3329).srcCtx("BFS.scala:85:33") // UnrolledForeach(List(b1856),x3262,Block(Const(())),List(List(b1965)),List(List(b1966)))
    val b1965 = CounterIter(x3261, None).name("b1965").ctrl(x3267) // b1965
    val b1966 = Const(true).name("b1966").ctrl(x3267) // b1966
    val x3263 = OpDef(op=BitAnd, inputs=List(b1966, b1856)).name("x3263").ctrl(x3267).srcCtx("UnrollingBase.scala:28:66") // And(b1966,b1856)
    val x3264 = LoadBanks(List(x3101_d0_b0), List(b1965)).name("x3264").ctrl(x3267).srcCtx("BFS.scala:85:73") // ParSRAMLoad(x3101,List(List(b1965)),List(x3263))
    val x3265 = x3264 // x3265 = VectorApply(x3264,0)
    val x3266 = StoreBanks(List(x3105_d0_b0, x3105_d1_b0, x3105_d2_b0), List(b1965), x3265).name("x3266").ctrl(x3267).srcCtx("BFS.scala:85:58") // ParSRAMStore(x3105,List(List(b1965)),List(x3265),List(x3263))
    val x3268 = ReadMem(x3107_d3).name("x3268").ctrl(x3329).srcCtx("BFS.scala:86:17") // RegRead(x3107)
    val x3269 = Counter(min=Const(0), max=x3268, step=Const(1), par=1).name("x3269").ctrl(x3329).srcCtx("BFS.scala:86:27") // CounterNew(Const(0),x3268,Const(1),Const(1))
    val x3270 = CounterChain(List(x3269)).name("x3270").ctrl(x3329).srcCtx("BFS.scala:86:33") // CounterChainNew(List(x3269))
    val x3274 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3270).name("x3274").ctrl(x3329).srcCtx("BFS.scala:86:33") // UnrolledForeach(List(b1856),x3270,Block(Const(())),List(List(b1975)),List(List(b1976)))
    val b1975 = CounterIter(x3269, None).name("b1975").ctrl(x3274) // b1975
    val b1976 = Const(true).name("b1976").ctrl(x3274) // b1976
    val x3271 = OpDef(op=FixAdd, inputs=List(b1855, Const(1))).name("x3271").ctrl(x3274).srcCtx("BFS.scala:86:64") // FixAdd(b1855,Const(1))
    val x3272 = OpDef(op=BitAnd, inputs=List(b1976, b1856)).name("x3272").ctrl(x3274).srcCtx("UnrollingBase.scala:28:66") // And(b1976,b1856)
    val x3273 = StoreBanks(List(x3104_d0_b0, x3104_d1_b0), List(b1975), x3271).name("x3273").ctrl(x3274).srcCtx("BFS.scala:86:60") // ParSRAMStore(x3104,List(List(b1975)),List(x3271),List(x3272))
    val x3275_d0 = Reg(init=Some(0)).name("x3275_d0").ctrl(x3329).srcCtx("BFS.scala:87:41") // x3275 = RegNew(Const(0))
    isAccum(x3275_d0) = false
    bufferDepthOf(x3275_d0) = 1
    val x3275_d1 = Reg(init=Some(0)).name("x3275_d1").ctrl(x3329).srcCtx("BFS.scala:87:41") // x3275 = RegNew(Const(0))
    isAccum(x3275_d1) = false
    bufferDepthOf(x3275_d1) = 1
    val x3287 = UnitController(style=SeqPipe, level=InnerControl).name("x3287").ctrl(x3329).srcCtx("BFS.scala:87:41") // UnitPipe(List(b1856),Block(x3286))
    val x3276 = ReadMem(x3107_d2).name("x3276").ctrl(x3287).srcCtx("BFS.scala:87:30") // RegRead(x3107)
    val x3277 = OpDef(op=FixLt, inputs=List(x3276, Const(16))).name("x3277").ctrl(x3287).srcCtx("BFS.scala:87:41") // FixLt(x3276,Const(16))
    val x3278 = x3276 // x3278 = DataAsBits(x3276)
    val x3279 = OpDef(op=BitAnd, inputs=List(x3278, Const(7))).name("x3279").ctrl(x3287).srcCtx("BFS.scala:87:41") // VectorSlice(x3278,3,0) strMask=00000000000000000000000000000111
    val x3280 = x3279 // x3280 = BitsAsData(x3279,FixPt[TRUE,_32,_0])
    val x3281 = OpDef(op=FixEql, inputs=List(x3280, Const(0))).name("x3281").ctrl(x3287).srcCtx("BFS.scala:87:41") // FixEql(x3280,Const(0))
    val x3282 = OpDef(op=FixAdd, inputs=List(x3276, Const(16))).name("x3282").ctrl(x3287).srcCtx("BFS.scala:87:41") // FixAdd(x3276,Const(16))
    val x3283 = OpDef(op=FixSub, inputs=List(x3282, x3280)).name("x3283").ctrl(x3287).srcCtx("BFS.scala:87:41") // FixSub(x3282,x3280)
    val x3284 = OpDef(op=MuxOp, inputs=List(x3281, x3276, x3283)).name("x3284").ctrl(x3287).srcCtx("BFS.scala:87:41") // Mux(x3281,x3276,x3283)
    val x3285 = OpDef(op=MuxOp, inputs=List(x3277, Const(16), x3284)).name("x3285").ctrl(x3287).srcCtx("BFS.scala:87:41") // Mux(x3277,Const(16),x3284)
    val x3286_x3275_d0 = WriteMem(x3275_d0, x3285).name("x3286_x3275_d0").ctrl(x3287).srcCtx("BFS.scala:87:41") // RegWrite(x3275,x3285,b1856)
    val x3286_x3275_d1 = WriteMem(x3275_d1, x3285).name("x3286_x3275_d1").ctrl(x3287).srcCtx("BFS.scala:87:41") // RegWrite(x3275,x3285,b1856)
    val x3324 = UnitController(style=StreamPipe, level=OuterControl).name("x3324").ctrl(x3329).srcCtx("BFS.scala:87:41") // UnitPipe(List(b1856),Block(Const(())))
    val x3288 = StreamOut(field="data").name("x3288").ctrl(x3324).srcCtx("BFS.scala:87:41") // x3288 = StreamOutNew(ScatterCmdBus())
    isAccum(x3288) = false
    bufferDepthOf(x3288) = 1
    val x3289 = StreamIn(field="ack").name("x3289").ctrl(x3324).srcCtx("BFS.scala:87:41") // x3289 = StreamInNew(ScatterAckBus)
    isAccum(x3289) = false
    bufferDepthOf(x3289) = 1
    val x3290 = ReadMem(x3275_d1).name("x3290").ctrl(x3324).srcCtx("BFS.scala:87:41") // RegRead(x3275)
    val x3291 = Counter(min=Const(0), max=x3290, step=Const(1), par=1).name("x3291").ctrl(x3324).srcCtx("BFS.scala:87:41") // CounterNew(Const(0),x3290,Const(1),Const(1))
    val x3292 = CounterChain(List(x3291)).name("x3292").ctrl(x3324).srcCtx("BFS.scala:87:41") // CounterChainNew(List(x3291))
    val x3315 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3292).name("x3315").ctrl(x3324).srcCtx("BFS.scala:87:41") // UnrolledForeach(List(b1856),x3292,Block(Const(())),List(List(b1997)),List(List(b1998)))
    val b1997 = CounterIter(x3291, None).name("b1997").ctrl(x3315) // b1997
    val b1998 = Const(true).name("b1998").ctrl(x3315) // b1998
    val x3293 = ReadMem(x3107_d1).name("x3293").ctrl(x3315).srcCtx("BFS.scala:87:30") // RegRead(x3107)
    val x3294 = OpDef(op=FixSub, inputs=List(x3293, Const(1))).name("x3294").ctrl(x3315).srcCtx("BFS.scala:87:41") // FixSub(x3293,Const(1))
    val x3295 = OpDef(op=FixMax, inputs=List(x3294, Const(0))).name("x3295").ctrl(x3315).srcCtx("BFS.scala:87:41") // Max(x3294,Const(0))
    val x3296 = OpDef(op=BitAnd, inputs=List(b1998, b1856)).name("x3296").ctrl(x3315).srcCtx("UnrollingBase.scala:28:66") // And(b1998,b1856)
    val x3297 = LoadBanks(List(x3105_d1_b0), List(x3295)).name("x3297").ctrl(x3315).srcCtx("BFS.scala:87:41") // SRAMLoad(x3105,ArrayBuffer(Const(8000)),List(x3295),Const(0),x3296)
    val x3298 = OpDef(op=FixLeq, inputs=List(x3293, b1997)).name("x3298").ctrl(x3315).srcCtx("BFS.scala:87:41") // FixLeq(x3293,b1997)
    val x3299 = OpDef(op=FixSla, inputs=List(x3297, Const(2))).name("x3299").ctrl(x3315).srcCtx("BFS.scala:87:41") // FixLsh(x3297,Const(2))
    val x3300 = x3299 // FixConvert(x3299,TRUE,_64,_0)
    val x3301 = DramAddress(x3093).name("x3301").ctrl(x3315).srcCtx("BFS.scala:87:41") // GetDRAMAddress(x3093)
    val x3302 = OpDef(op=FixAdd, inputs=List(x3300, x3301)).name("x3302").ctrl(x3315).srcCtx("BFS.scala:87:41") // FixAdd(x3300,x3301)
    val x3303 = LoadBanks(List(x3105_d0_b0), List(b1997)).name("x3303").ctrl(x3315).srcCtx("BFS.scala:87:41") // ParSRAMLoad(x3105,List(List(b1997)),List(x3296))
    val x3304 = x3303 // x3304 = VectorApply(x3303,0)
    val x3305 = OpDef(op=FixSla, inputs=List(x3304, Const(2))).name("x3305").ctrl(x3315).srcCtx("BFS.scala:87:41") // FixLsh(x3304,Const(2))
    val x3306 = x3305 // FixConvert(x3305,TRUE,_64,_0)
    val x3307 = OpDef(op=FixAdd, inputs=List(x3306, x3301)).name("x3307").ctrl(x3315).srcCtx("BFS.scala:87:41") // FixAdd(x3306,x3301)
    val x3313_x3308 = OpDef(op=MuxOp, inputs=List(x3298, x3302, x3307)).name("x3313_x3308").ctrl(x3315).srcCtx("BFS.scala:87:41") // Mux(x3298,x3302,x3307)
    val x3309 = LoadBanks(List(x3104_d1_b0), List(x3295)).name("x3309").ctrl(x3315).srcCtx("BFS.scala:87:41") // SRAMLoad(x3104,ArrayBuffer(Const(8000)),List(x3295),Const(0),x3296)
    val x3310 = LoadBanks(List(x3104_d0_b0), List(b1997)).name("x3310").ctrl(x3315).srcCtx("BFS.scala:87:41") // ParSRAMLoad(x3104,List(List(b1997)),List(x3296))
    val x3311 = x3310 // x3311 = VectorApply(x3310,0)
    val x3313_x3312 = OpDef(op=MuxOp, inputs=List(x3298, x3309, x3311)).name("x3313_x3312").ctrl(x3315).srcCtx("BFS.scala:87:41") // Mux(x3298,x3309,x3311)
    // x3313 = SimpleStruct(ArrayBuffer((_1,x3312), (_2,x3308)))
    val x3314_x3314_x3288 = WriteMem(x3288, x3313_x3312).name("x3314_x3314_x3288").ctrl(x3315).srcCtx("BFS.scala:87:41") // ParStreamWrite(x3288,List(x3313),List(x3296))
    val x3316 = FringeSparseStore(dram=List(x3093), cmdStream=List(x3288), ackStream=List(x3289)).name("x3316").ctrl(x3324).srcCtx("BFS.scala:87:41") // FringeSparseStore(x3093,x3288,x3289)
    val x3317 = ReadMem(x3275_d0).name("x3317").ctrl(x3324).srcCtx("BFS.scala:87:41") // RegRead(x3275)
    val x3318 = Counter(min=Const(0), max=x3317, step=Const(16), par=1).name("x3318").ctrl(x3324).srcCtx("BFS.scala:87:41") // CounterNew(Const(0),x3317,Const(16),Const(1))
    val x3319 = CounterChain(List(x3318)).name("x3319").ctrl(x3324).srcCtx("BFS.scala:87:41") // CounterChainNew(List(x3318))
    val x3323 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3319).name("x3323").ctrl(x3324).srcCtx("BFS.scala:87:41") // UnrolledForeach(List(b1856),x3319,Block(Const(())),List(List(b2026)),List(List(b2027)))
    val b2026 = CounterIter(x3318, None).name("b2026").ctrl(x3323) // b2026
    val b2027 = Const(true).name("b2027").ctrl(x3323) // b2027
    val x3320 = OpDef(op=BitAnd, inputs=List(b2027, b1856)).name("x3320").ctrl(x3323).srcCtx("UnrollingBase.scala:28:66") // And(b2027,b1856)
    val x3321_x3321 = ReadMem(x3289).name("x3321_x3321").ctrl(x3323).srcCtx("BFS.scala:87:41") // ParStreamRead(x3289,List(x3320))
    val x3322_x3322 = x3321_x3321 // x3322 = VectorApply(x3321,0)
    val x3328 = UnitController(style=SeqPipe, level=InnerControl).name("x3328").ctrl(x3329).srcCtx("BFS.scala:54:44") // UnitPipe(List(b1856),Block(Const(())))
    val x3325 = ReadMem(x3107_d0).name("x3325").ctrl(x3328).srcCtx("BFS.scala:88:21") // RegRead(x3107)
    val x3326_x3108 = WriteMem(x3108, x3325).name("x3326_x3108").ctrl(x3328).srcCtx("BFS.scala:88:18") // RegWrite(x3108,x3325,b1856)
    val x3327_x3107_d0 = WriteMem(x3107_d0, Const(0)).name("x3327_x3107_d0").ctrl(x3328).srcCtx("BFS.scala:89:19") // RegWrite(x3107,Const(0),b1856)
    val x3327_x3107_d5 = WriteMem(x3107_d5, Const(0)).name("x3327_x3107_d5").ctrl(x3328).srcCtx("BFS.scala:89:19") // RegWrite(x3107,Const(0),b1856)
    val x3327_x3107_d1 = WriteMem(x3107_d1, Const(0)).name("x3327_x3107_d1").ctrl(x3328).srcCtx("BFS.scala:89:19") // RegWrite(x3107,Const(0),b1856)
    val x3327_x3107_d6 = WriteMem(x3107_d6, Const(0)).name("x3327_x3107_d6").ctrl(x3328).srcCtx("BFS.scala:89:19") // RegWrite(x3107,Const(0),b1856)
    val x3327_x3107_d2 = WriteMem(x3107_d2, Const(0)).name("x3327_x3107_d2").ctrl(x3328).srcCtx("BFS.scala:89:19") // RegWrite(x3107,Const(0),b1856)
    val x3327_x3107_d3 = WriteMem(x3107_d3, Const(0)).name("x3327_x3107_d3").ctrl(x3328).srcCtx("BFS.scala:89:19") // RegWrite(x3107,Const(0),b1856)
    val x3327_x3107_d4 = WriteMem(x3107_d4, Const(0)).name("x3327_x3107_d4").ctrl(x3328).srcCtx("BFS.scala:89:19") // RegWrite(x3107,Const(0),b1856)
    
  }
}
