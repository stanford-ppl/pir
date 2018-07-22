import pir._
import pir.node._
import arch._
import prism.enums._

object OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1 extends PIRApp {
  def main(implicit design:PIRDesign) = {
    import design.pirmeta._
    val x2078 = withCtrl(design.top.topController) { ArgIn(init=0).name("x2078").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:21:22:sizeA") } // ArgInNew(Const(0))
    isAccum(x2078) = false
    bufferDepthOf(x2078) = 1
    boundOf(x2078) = 12288
    val x2079_d0 = withCtrl(design.top.topController) { ArgIn(init=0).name("x2079_d0").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:22:22:sizeB") } // ArgInNew(Const(0))
    isAccum(x2079_d0) = false
    bufferDepthOf(x2079_d0) = 1
    boundOf(x2079_d0) = 12288
    val x2079_d1 = withCtrl(design.top.topController) { ArgIn(init=0).name("x2079_d1").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:22:22:sizeB") } // ArgInNew(Const(0))
    isAccum(x2079_d1) = false
    bufferDepthOf(x2079_d1) = 1
    boundOf(x2079_d1) = 12288
    val x2079_d2 = withCtrl(design.top.topController) { ArgIn(init=0).name("x2079_d2").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:22:22:sizeB") } // ArgInNew(Const(0))
    isAccum(x2079_d2) = false
    bufferDepthOf(x2079_d2) = 1
    boundOf(x2079_d2) = 12288
    val x2082 = withCtrl(design.top.topController) { ReadMem(x2078).name("x2082").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:26:24") } // RegRead(x2078)
    val x2083 = withCtrl(design.top.topController) { DRAM(dims=List(x2082)).name("x2083").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:26:23:vec1") } // x2083 = DRAMNew(ArrayBuffer(x2082),Const(0))
    val x2084 = withCtrl(design.top.topController) { ReadMem(x2079_d0).name("x2084").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:27:24") } // RegRead(x2079)
    val x2085 = withCtrl(design.top.topController) { DRAM(dims=List(x2084)).name("x2085").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:27:23:vec2") } // x2085 = DRAMNew(ArrayBuffer(x2084),Const(0))
    val x2086 = withCtrl(design.top.topController) { ReadMem(x2079_d0).name("x2086").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:28:30") } // RegRead(x2079)
    val x2087 = withCtrl(design.top.topController) { ReadMem(x2078).name("x2087").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:28:23") } // RegRead(x2078)
    val x2088 = withCtrl(design.top.topController) { DRAM(dims=List(x2087, x2086)).name("x2088").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:28:22:out") } // x2088 = DRAMNew(ArrayBuffer(x2087, x2086),Const(0))
    val x2276 = withCtrl(design.top.topController) { UnitController(style=SeqPipe, level=OuterControl).name("x2276").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:33:11") } // Hwblock(Block(Const(())),false)
    val x2091 = withCtrl(x2276) { ReadMem(x2078).name("x2091").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:34:15") } // RegRead(x2078)
    val x2092 = withCtrl(x2276) { Counter(min=Const(0), max=x2091, step=Const(64), par=1).name("x2092").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:34:28") } // CounterNew(Const(0),x2091,Const(64),Const(1))
    val x2093 = withCtrl(x2276) { CounterChain(List(x2092)).name("x2093").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:34:36") } // CounterChainNew(List(x2092))
    val x2275 = withCtrl(x2276) { LoopController(style=MetaPipe, level=OuterControl, cchain=x2093).name("x2275").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:34:36") } // UnrolledForeach(List(Const(true)),x2093,Block(Const(())),List(List(b966)),List(List(b967)))
    val b966 = withCtrl(x2275) { CounterIter(x2092, Some(0)).name("b966") } // b966
    val b967 = withCtrl(x2275) { Const(true).name("b967") } // b967
    val x2094_d0_b0 = withCtrl(x2275) { SRAM(size=64, banking=Strided(banks=16, stride=1)).name("x2094_d0_b0").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:35:25:b1") } // x2094 = SRAMNew(ArrayBuffer(Const(64)))
    isAccum(x2094_d0_b0) = false
    bufferDepthOf(x2094_d0_b0) = 2
    staticDimsOf(x2094_d0_b0) = List(64)
    val x2094_d1_b0 = withCtrl(x2275) { SRAM(size=64, banking=Strided(banks=16, stride=1)).name("x2094_d1_b0").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:35:25:b1") } // x2094 = SRAMNew(ArrayBuffer(Const(64)))
    isAccum(x2094_d1_b0) = false
    bufferDepthOf(x2094_d1_b0) = 2
    staticDimsOf(x2094_d1_b0) = List(64)
    val x2096 = withCtrl(x2275) { UnitController(style=SeqPipe, level=InnerControl).name("x2096").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:34:36") } // UnitPipe(List(b967),Block(Const(())))
    val x2095 = withCtrl(x2096) { OpDef(op=FixAdd, inputs=List(b966, Const(64))).name("x2095").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:36:26") } // FixAdd(b966,Const(64))
    val x2115 = withCtrl(x2275) { UnitController(style=StreamPipe, level=OuterControl).name("x2115").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:36:12") } // UnitPipe(List(b967),Block(Const(())))
    val b2293 = withCtrl(x2115) { StreamOut(field="offset").name("b2293").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:36:12") } // x2097 = StreamOutNew(BurstCmdBus)
    isAccum(b2293) = false
    bufferDepthOf(b2293) = 1
    val b2294 = withCtrl(x2115) { StreamOut(field="size").name("b2294").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:36:12") } // x2097 = StreamOutNew(BurstCmdBus)
    isAccum(b2294) = false
    bufferDepthOf(b2294) = 1
    val x2098 = withCtrl(x2115) { StreamIn(field="data").name("x2098").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:36:12") } // x2098 = StreamInNew(BurstDataBus())
    isAccum(x2098) = false
    bufferDepthOf(x2098) = 1
    val x2106 = withCtrl(x2115) { UnitController(style=SeqPipe, level=InnerControl).name("x2106").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:36:12") } // UnitPipe(List(b967),Block(x2105))
    val x2099 = withCtrl(x2106) { b966 } // FixConvert(b966,TRUE,_32,_0) (Same Type. No op)
    val x2100 = withCtrl(x2106) { OpDef(op=FixSla, inputs=List(x2099, Const(2))).name("x2100").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:36:12") } // FixLsh(x2099,Const(2))
    val x2101 = withCtrl(x2106) { x2100 } // FixConvert(x2100,TRUE,_64,_0)
    val x2102 = withCtrl(x2106) { DramAddress(x2083).name("x2102").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:36:12") } // GetDRAMAddress(x2083)
    val x2104_x2103 = withCtrl(x2106) { OpDef(op=FixAdd, inputs=List(x2101, x2102)).name("x2104_x2103").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:36:12") } // FixAdd(x2101,x2102)
    // x2104 = SimpleStruct(ArrayBuffer((offset,x2103), (size,Const(256)), (isLoad,Const(true))))
    val x2105_b2295_b2293 = withCtrl(x2106) { WriteMem(b2293, x2104_x2103).name("x2105_b2295_b2293").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:36:12") } // StreamWrite(x2097,x2104,b967)
    val x2105_b2296_b2294 = withCtrl(x2106) { WriteMem(b2294, Const(256)).name("x2105_b2296_b2294").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:36:12") } // StreamWrite(x2097,x2104,b967)
    val x2107 = withCtrl(x2115) { FringeDenseLoad(dram=List(x2083), cmdStream=List(b2293, b2294), dataStream=List(x2098)).name("x2107").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:36:12") } // FringeDenseLoad(x2083,x2097,x2098)
    val x2108 = withCtrl(x2115) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x2108").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:36:12") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x2109 = withCtrl(x2115) { CounterChain(List(x2108)).name("x2109").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:36:12") } // CounterChainNew(List(x2108))
    val x2114 = withCtrl(x2115) { LoopController(style=InnerPipe, level=InnerControl, cchain=x2109).name("x2114").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:36:12") } // UnrolledForeach(List(b967),x2109,Block(Const(())),List(List(b984)),List(List(b985)))
    val b984 = withCtrl(x2114) { CounterIter(x2108, None).name("b984") } // b984
    val b985 = withCtrl(x2114) { Const(true).name("b985") } // b985
    val x2110 = withCtrl(x2114) { OpDef(op=BitAnd, inputs=List(b985, b967)).name("x2110").srcCtx("UnrollingBase.scala:28:66") } // And(b985,b967)
    val x2111_x2111 = withCtrl(x2114) { ReadMem(x2098).name("x2111_x2111").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:36:12") } // ParStreamRead(x2098,List(x2110))
    val x2112_x2112 = withCtrl(x2114) { x2111_x2111 } // VectorApply(x2111,0)
    val x2113 = withCtrl(x2114) { StoreBanks(List(List(x2094_d0_b0), List(x2094_d1_b0)), List(b984), x2112_x2112).name("x2113").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:36:12") } // ParSRAMStore(x2094,List(List(b984)),List(x2112),List(x2110))
    val x2116 = withCtrl(x2275) { ReadMem(x2079_d0).name("x2116").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:37:17") } // RegRead(x2079)
    val x2117 = withCtrl(x2275) { Counter(min=Const(0), max=x2116, step=Const(1024), par=2).name("x2117").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:37:30") } // CounterNew(Const(0),x2116,Const(1024),Const(2))
    val x2118 = withCtrl(x2275) { CounterChain(List(x2117)).name("x2118").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:37:39") } // CounterChainNew(List(x2117))
    val x2274 = withCtrl(x2275) { LoopController(style=MetaPipe, level=OuterControl, cchain=x2118).name("x2274").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:37:39") } // UnrolledForeach(List(b967),x2118,Block(Const(())),List(List(b995, b996)),List(List(b997, b998)))
    val b995 = withCtrl(x2274) { CounterIter(x2117, Some(0)).name("b995") } // b995
    val b997 = withCtrl(x2274) { Const(true).name("b997") } // b997
    val b996 = withCtrl(x2274) { CounterIter(x2117, Some(1)).name("b996") } // b996
    val b998 = withCtrl(x2274) { Const(true).name("b998") } // b998
    val x2119_d0_b0 = withCtrl(x2274) { SRAM(size=1024, banking=Strided(banks=16, stride=1)).name("x2119_d0_b0").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:38:27:b2") } // x2119 = SRAMNew(ArrayBuffer(Const(1024)))
    isAccum(x2119_d0_b0) = false
    bufferDepthOf(x2119_d0_b0) = 2
    staticDimsOf(x2119_d0_b0) = List(1024)
    val x2120_d0_b0 = withCtrl(x2274) { SRAM(size=1024, banking=Strided(banks=16, stride=1)).name("x2120_d0_b0").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:38:27:b2") } // x2120 = SRAMNew(ArrayBuffer(Const(1024)))
    isAccum(x2120_d0_b0) = false
    bufferDepthOf(x2120_d0_b0) = 2
    staticDimsOf(x2120_d0_b0) = List(1024)
    val x2125 = withCtrl(x2274) { UnitController(style=ForkJoin, level=OuterControl).name("x2125").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b967),Block(Const(())))
    val x2122 = withCtrl(x2125) { UnitController(style=SeqPipe, level=InnerControl).name("x2122").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:37:39") } // UnitPipe(List(b997, b967),Block(Const(())))
    val x2121 = withCtrl(x2122) { OpDef(op=FixAdd, inputs=List(b995, Const(1024))).name("x2121").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:39:28") } // FixAdd(b995,Const(1024))
    val x2124 = withCtrl(x2125) { UnitController(style=SeqPipe, level=InnerControl).name("x2124").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:37:39") } // UnitPipe(List(b998, b967),Block(Const(())))
    val x2123 = withCtrl(x2124) { OpDef(op=FixAdd, inputs=List(b996, Const(1024))).name("x2123").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:39:28") } // FixAdd(b996,Const(1024))
    val x2168 = withCtrl(x2274) { UnitController(style=ForkJoin, level=OuterControl).name("x2168").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b967),Block(Const(())))
    val x2146 = withCtrl(x2168) { UnitController(style=StreamPipe, level=OuterControl).name("x2146").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:39:14") } // UnitPipe(List(b997, b967),Block(Const(())))
    val b2297 = withCtrl(x2146) { StreamOut(field="offset").name("b2297").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:39:14") } // x2126 = StreamOutNew(BurstCmdBus)
    isAccum(b2297) = false
    bufferDepthOf(b2297) = 1
    val b2298 = withCtrl(x2146) { StreamOut(field="size").name("b2298").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:39:14") } // x2126 = StreamOutNew(BurstCmdBus)
    isAccum(b2298) = false
    bufferDepthOf(b2298) = 1
    val x2127 = withCtrl(x2146) { StreamIn(field="data").name("x2127").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:39:14") } // x2127 = StreamInNew(BurstDataBus())
    isAccum(x2127) = false
    bufferDepthOf(x2127) = 1
    val x2136 = withCtrl(x2146) { UnitController(style=SeqPipe, level=InnerControl).name("x2136").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:39:14") } // UnitPipe(List(b997, b967),Block(x2135))
    val x2128 = withCtrl(x2136) { b995 } // FixConvert(b995,TRUE,_32,_0) (Same Type. No op)
    val x2129 = withCtrl(x2136) { OpDef(op=FixSla, inputs=List(x2128, Const(2))).name("x2129").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:39:14") } // FixLsh(x2128,Const(2))
    val x2130 = withCtrl(x2136) { x2129 } // FixConvert(x2129,TRUE,_64,_0)
    val x2131 = withCtrl(x2136) { DramAddress(x2085).name("x2131").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:39:14") } // GetDRAMAddress(x2085)
    val x2133_x2132 = withCtrl(x2136) { OpDef(op=FixAdd, inputs=List(x2130, x2131)).name("x2133_x2132").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:39:14") } // FixAdd(x2130,x2131)
    // x2133 = SimpleStruct(ArrayBuffer((offset,x2132), (size,Const(4096)), (isLoad,Const(true))))
    val x2134 = withCtrl(x2136) { OpDef(op=BitAnd, inputs=List(b997, b967)).name("x2134").srcCtx("UnrollingBase.scala:28:66") } // And(b997,b967)
    val x2135_b2299_b2297 = withCtrl(x2136) { WriteMem(b2297, x2133_x2132).name("x2135_b2299_b2297").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:39:14") } // StreamWrite(x2126,x2133,x2134)
    val x2135_b2300_b2298 = withCtrl(x2136) { WriteMem(b2298, Const(4096)).name("x2135_b2300_b2298").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:39:14") } // StreamWrite(x2126,x2133,x2134)
    val x2137 = withCtrl(x2146) { FringeDenseLoad(dram=List(x2085), cmdStream=List(b2297, b2298), dataStream=List(x2127)).name("x2137").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:39:14") } // FringeDenseLoad(x2085,x2126,x2127)
    val x2138 = withCtrl(x2146) { Counter(min=Const(0), max=Const(1024), step=Const(1), par=16).name("x2138").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:39:14") } // CounterNew(Const(0),Const(1024),Const(1),Const(16))
    val x2139 = withCtrl(x2146) { CounterChain(List(x2138)).name("x2139").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:39:14") } // CounterChainNew(List(x2138))
    val x2145 = withCtrl(x2146) { LoopController(style=InnerPipe, level=InnerControl, cchain=x2139).name("x2145").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:39:14") } // UnrolledForeach(List(b997, b967),x2139,Block(Const(())),List(List(b1020)),List(List(b1021)))
    val b1020 = withCtrl(x2145) { CounterIter(x2138, None).name("b1020") } // b1020
    val b1021 = withCtrl(x2145) { Const(true).name("b1021") } // b1021
    val x2140 = withCtrl(x2145) { OpDef(op=BitAnd, inputs=List(b1021, b997)).name("x2140").srcCtx("UnrollingBase.scala:28:66") } // And(b1021,b997)
    val x2141 = withCtrl(x2145) { OpDef(op=BitAnd, inputs=List(x2140, b967)).name("x2141").srcCtx("UnrollingBase.scala:28:66") } // And(x2140,b967)
    val x2142_x2142 = withCtrl(x2145) { ReadMem(x2127).name("x2142_x2142").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:39:14") } // ParStreamRead(x2127,List(x2141))
    val x2143_x2143 = withCtrl(x2145) { x2142_x2142 } // VectorApply(x2142,0)
    val x2144 = withCtrl(x2145) { StoreBanks(List(List(x2119_d0_b0)), List(b1020), x2143_x2143).name("x2144").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:39:14") } // ParSRAMStore(x2119,List(List(b1020)),List(x2143),List(x2141))
    val x2167 = withCtrl(x2168) { UnitController(style=StreamPipe, level=OuterControl).name("x2167").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:39:14") } // UnitPipe(List(b998, b967),Block(Const(())))
    val b2301 = withCtrl(x2167) { StreamOut(field="offset").name("b2301").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:39:14") } // x2147 = StreamOutNew(BurstCmdBus)
    isAccum(b2301) = false
    bufferDepthOf(b2301) = 1
    val b2302 = withCtrl(x2167) { StreamOut(field="size").name("b2302").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:39:14") } // x2147 = StreamOutNew(BurstCmdBus)
    isAccum(b2302) = false
    bufferDepthOf(b2302) = 1
    val x2148 = withCtrl(x2167) { StreamIn(field="data").name("x2148").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:39:14") } // x2148 = StreamInNew(BurstDataBus())
    isAccum(x2148) = false
    bufferDepthOf(x2148) = 1
    val x2157 = withCtrl(x2167) { UnitController(style=SeqPipe, level=InnerControl).name("x2157").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:39:14") } // UnitPipe(List(b998, b967),Block(x2156))
    val x2149 = withCtrl(x2157) { b996 } // FixConvert(b996,TRUE,_32,_0) (Same Type. No op)
    val x2150 = withCtrl(x2157) { OpDef(op=FixSla, inputs=List(x2149, Const(2))).name("x2150").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:39:14") } // FixLsh(x2149,Const(2))
    val x2151 = withCtrl(x2157) { x2150 } // FixConvert(x2150,TRUE,_64,_0)
    val x2152 = withCtrl(x2157) { DramAddress(x2085).name("x2152").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:39:14") } // GetDRAMAddress(x2085)
    val x2154_x2153 = withCtrl(x2157) { OpDef(op=FixAdd, inputs=List(x2151, x2152)).name("x2154_x2153").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:39:14") } // FixAdd(x2151,x2152)
    // x2154 = SimpleStruct(ArrayBuffer((offset,x2153), (size,Const(4096)), (isLoad,Const(true))))
    val x2155 = withCtrl(x2157) { OpDef(op=BitAnd, inputs=List(b998, b967)).name("x2155").srcCtx("UnrollingBase.scala:28:66") } // And(b998,b967)
    val x2156_b2303_b2301 = withCtrl(x2157) { WriteMem(b2301, x2154_x2153).name("x2156_b2303_b2301").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:39:14") } // StreamWrite(x2147,x2154,x2155)
    val x2156_b2304_b2302 = withCtrl(x2157) { WriteMem(b2302, Const(4096)).name("x2156_b2304_b2302").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:39:14") } // StreamWrite(x2147,x2154,x2155)
    val x2158 = withCtrl(x2167) { FringeDenseLoad(dram=List(x2085), cmdStream=List(b2301, b2302), dataStream=List(x2148)).name("x2158").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:39:14") } // FringeDenseLoad(x2085,x2147,x2148)
    val x2159 = withCtrl(x2167) { Counter(min=Const(0), max=Const(1024), step=Const(1), par=16).name("x2159").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:39:14") } // CounterNew(Const(0),Const(1024),Const(1),Const(16))
    val x2160 = withCtrl(x2167) { CounterChain(List(x2159)).name("x2160").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:39:14") } // CounterChainNew(List(x2159))
    val x2166 = withCtrl(x2167) { LoopController(style=InnerPipe, level=InnerControl, cchain=x2160).name("x2166").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:39:14") } // UnrolledForeach(List(b998, b967),x2160,Block(Const(())),List(List(b1043)),List(List(b1044)))
    val b1043 = withCtrl(x2166) { CounterIter(x2159, None).name("b1043") } // b1043
    val b1044 = withCtrl(x2166) { Const(true).name("b1044") } // b1044
    val x2161 = withCtrl(x2166) { OpDef(op=BitAnd, inputs=List(b1044, b998)).name("x2161").srcCtx("UnrollingBase.scala:28:66") } // And(b1044,b998)
    val x2162 = withCtrl(x2166) { OpDef(op=BitAnd, inputs=List(x2161, b967)).name("x2162").srcCtx("UnrollingBase.scala:28:66") } // And(x2161,b967)
    val x2163_x2163 = withCtrl(x2166) { ReadMem(x2148).name("x2163_x2163").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:39:14") } // ParStreamRead(x2148,List(x2162))
    val x2164_x2164 = withCtrl(x2166) { x2163_x2163 } // VectorApply(x2163,0)
    val x2165 = withCtrl(x2166) { StoreBanks(List(List(x2120_d0_b0)), List(b1043), x2164_x2164).name("x2165").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:39:14") } // ParSRAMStore(x2120,List(List(b1043)),List(x2164),List(x2162))
    val x2169_d0_b0 = withCtrl(x2274) { SRAM(size=65536, banking=Strided(banks=16, stride=1)).name("x2169_d0_b0").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:40:32:outTile") } // x2169 = SRAMNew(ArrayBuffer(Const(64), Const(1024)))
    isAccum(x2169_d0_b0) = false
    bufferDepthOf(x2169_d0_b0) = 3
    staticDimsOf(x2169_d0_b0) = List(64, 1024)
    val x2170_d0_b0 = withCtrl(x2274) { SRAM(size=65536, banking=Strided(banks=16, stride=1)).name("x2170_d0_b0").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:40:32:outTile") } // x2170 = SRAMNew(ArrayBuffer(Const(64), Const(1024)))
    isAccum(x2170_d0_b0) = false
    bufferDepthOf(x2170_d0_b0) = 3
    staticDimsOf(x2170_d0_b0) = List(64, 1024)
    val x2195 = withCtrl(x2274) { UnitController(style=ForkJoin, level=OuterControl).name("x2195").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b967),Block(Const(())))
    val x2171 = withCtrl(x2195) { Counter(min=Const(0), max=Const(1024), step=Const(1), par=16).name("x2171").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:41:36") } // CounterNew(Const(0),Const(1024),Const(1),Const(16))
    val x2172 = withCtrl(x2195) { Counter(min=Const(0), max=Const(64), step=Const(1), par=1).name("x2172").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:41:23") } // CounterNew(Const(0),Const(64),Const(1),Const(1))
    val x2173 = withCtrl(x2195) { CounterChain(List(x2172,x2171)).name("x2173").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:41:44") } // CounterChainNew(List(x2172, x2171))
    val x2182 = withCtrl(x2195) { LoopController(style=InnerPipe, level=InnerControl, cchain=x2173).name("x2182").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:41:44") } // UnrolledForeach(List(b997, b967),x2173,Block(Const(())),List(List(b1061), List(b1062)),List(List(b1063), List(b1064)))
    val b1061 = withCtrl(x2182) { CounterIter(x2172, Some(0)).name("b1061") } // b1061
    val b1063 = withCtrl(x2182) { Const(true).name("b1063") } // b1063
    val b1062 = withCtrl(x2182) { CounterIter(x2171, None).name("b1062") } // b1062
    val b1064 = withCtrl(x2182) { Const(true).name("b1064") } // b1064
    val x2174 = withCtrl(x2182) { OpDef(op=BitAnd, inputs=List(b1063, b1064)).name("x2174").srcCtx("UnrollingBase.scala:28:66") } // And(b1063,b1064)
    val x2175 = withCtrl(x2182) { OpDef(op=BitAnd, inputs=List(b997, b967)).name("x2175").srcCtx("UnrollingBase.scala:28:66") } // And(b997,b967)
    val x2176 = withCtrl(x2182) { OpDef(op=BitAnd, inputs=List(x2174, x2175)).name("x2176").srcCtx("UnrollingBase.scala:28:66") } // And(x2174,x2175)
    val x2177 = withCtrl(x2182) { LoadBanks(List(x2094_d0_b0), List(b1061)).name("x2177").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:41:77") } // SRAMLoad(x2094,ArrayBuffer(Const(64)),List(b1061),Const(0),x2176)
    val x2178 = withCtrl(x2182) { LoadBanks(List(x2119_d0_b0), List(b1062)).name("x2178").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:41:86") } // ParSRAMLoad(x2119,List(List(b1062)),List(x2176))
    val x2179 = withCtrl(x2182) { x2178 } // VectorApply(x2178,0)
    val x2180 = withCtrl(x2182) { OpDef(op=FixMul, inputs=List(x2177, x2179)).name("x2180").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:41:82") } // FixMul(x2177,x2179)
    val x2181 = withCtrl(x2182) { StoreBanks(List(List(x2169_d0_b0)), List(b1061, b1062), x2180).name("x2181").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:41:73") } // ParSRAMStore(x2169,List(List(b1061, b1062)),List(x2180),List(x2176))
    val x2183 = withCtrl(x2195) { Counter(min=Const(0), max=Const(1024), step=Const(1), par=16).name("x2183").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:41:36") } // CounterNew(Const(0),Const(1024),Const(1),Const(16))
    val x2184 = withCtrl(x2195) { Counter(min=Const(0), max=Const(64), step=Const(1), par=1).name("x2184").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:41:23") } // CounterNew(Const(0),Const(64),Const(1),Const(1))
    val x2185 = withCtrl(x2195) { CounterChain(List(x2184,x2183)).name("x2185").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:41:44") } // CounterChainNew(List(x2184, x2183))
    val x2194 = withCtrl(x2195) { LoopController(style=InnerPipe, level=InnerControl, cchain=x2185).name("x2194").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:41:44") } // UnrolledForeach(List(b998, b967),x2185,Block(Const(())),List(List(b1074), List(b1075)),List(List(b1076), List(b1077)))
    val b1074 = withCtrl(x2194) { CounterIter(x2184, Some(0)).name("b1074") } // b1074
    val b1076 = withCtrl(x2194) { Const(true).name("b1076") } // b1076
    val b1075 = withCtrl(x2194) { CounterIter(x2183, None).name("b1075") } // b1075
    val b1077 = withCtrl(x2194) { Const(true).name("b1077") } // b1077
    val x2186 = withCtrl(x2194) { OpDef(op=BitAnd, inputs=List(b1076, b1077)).name("x2186").srcCtx("UnrollingBase.scala:28:66") } // And(b1076,b1077)
    val x2187 = withCtrl(x2194) { OpDef(op=BitAnd, inputs=List(b998, b967)).name("x2187").srcCtx("UnrollingBase.scala:28:66") } // And(b998,b967)
    val x2188 = withCtrl(x2194) { OpDef(op=BitAnd, inputs=List(x2186, x2187)).name("x2188").srcCtx("UnrollingBase.scala:28:66") } // And(x2186,x2187)
    val x2189 = withCtrl(x2194) { LoadBanks(List(x2094_d1_b0), List(b1074)).name("x2189").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:41:77") } // SRAMLoad(x2094,ArrayBuffer(Const(64)),List(b1074),Const(0),x2188)
    val x2190 = withCtrl(x2194) { LoadBanks(List(x2120_d0_b0), List(b1075)).name("x2190").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:41:86") } // ParSRAMLoad(x2120,List(List(b1075)),List(x2188))
    val x2191 = withCtrl(x2194) { x2190 } // VectorApply(x2190,0)
    val x2192 = withCtrl(x2194) { OpDef(op=FixMul, inputs=List(x2189, x2191)).name("x2192").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:41:82") } // FixMul(x2189,x2191)
    val x2193 = withCtrl(x2194) { StoreBanks(List(List(x2170_d0_b0)), List(b1074, b1075), x2192).name("x2193").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:41:73") } // ParSRAMStore(x2170,List(List(b1074, b1075)),List(x2192),List(x2188))
    val x2200 = withCtrl(x2274) { UnitController(style=ForkJoin, level=OuterControl).name("x2200").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b967),Block(Const(())))
    val x2197 = withCtrl(x2200) { UnitController(style=SeqPipe, level=InnerControl).name("x2197").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:37:39") } // UnitPipe(List(b997, b967),Block(Const(())))
    val x2196 = withCtrl(x2197) { OpDef(op=FixAdd, inputs=List(b966, Const(64))).name("x2196").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:42:19") } // FixAdd(b966,Const(64))
    val x2199 = withCtrl(x2200) { UnitController(style=SeqPipe, level=InnerControl).name("x2199").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:37:39") } // UnitPipe(List(b998, b967),Block(Const(())))
    val x2198 = withCtrl(x2199) { OpDef(op=FixAdd, inputs=List(b966, Const(64))).name("x2198").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:42:19") } // FixAdd(b966,Const(64))
    val x2273 = withCtrl(x2274) { UnitController(style=ForkJoin, level=OuterControl).name("x2273").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b967),Block(Const(())))
    val x2201 = withCtrl(x2273) { Counter(min=Const(0), max=Const(64), step=Const(1), par=1).name("x2201").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:42:43") } // CounterNew(Const(0),Const(64),Const(1),Const(1))
    val x2202 = withCtrl(x2273) { CounterChain(List(x2201)).name("x2202").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:42:43") } // CounterChainNew(List(x2201))
    val x2236 = withCtrl(x2273) { LoopController(style=StreamPipe, level=OuterControl, cchain=x2202).name("x2236").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:42:43") } // UnrolledForeach(List(b997, b967),x2202,Block(Const(())),List(List(b1097)),List(List(b1098)))
    val b1097 = withCtrl(x2236) { CounterIter(x2201, Some(0)).name("b1097") } // b1097
    val b1098 = withCtrl(x2236) { Const(true).name("b1098") } // b1098
    val b2305 = withCtrl(x2236) { StreamOut(field="offset").name("b2305").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:42:43") } // x2203 = StreamOutNew(BurstCmdBus)
    isAccum(b2305) = false
    bufferDepthOf(b2305) = 1
    val b2306 = withCtrl(x2236) { StreamOut(field="size").name("b2306").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:42:43") } // x2203 = StreamOutNew(BurstCmdBus)
    isAccum(b2306) = false
    bufferDepthOf(b2306) = 1
    val x2204 = withCtrl(x2236) { StreamOut(field="data").name("x2204").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:42:43") } // x2204 = StreamOutNew(BurstFullDataBus())
    isAccum(x2204) = false
    bufferDepthOf(x2204) = 1
    val x2205 = withCtrl(x2236) { StreamIn(field="ack").name("x2205").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:42:43") } // x2205 = StreamInNew(BurstAckBus)
    isAccum(x2205) = false
    bufferDepthOf(x2205) = 1
    val x2220 = withCtrl(x2236) { UnitController(style=SeqPipe, level=InnerControl).name("x2220").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:42:43") } // UnitPipe(List(b1098, b997, b967),Block(x2219))
    val x2206 = withCtrl(x2220) { OpDef(op=FixAdd, inputs=List(b966, b1097)).name("x2206").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:42:43") } // FixAdd(b966,b1097)
    val x2207 = withCtrl(x2220) { x2206 } // FixConvert(x2206,TRUE,_32,_0) (Same Type. No op)
    val x2208 = withCtrl(x2220) { ReadMem(x2079_d0).name("x2208").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:28:30") } // RegRead(x2079)
    val x2209 = withCtrl(x2220) { OpDef(op=FixMul, inputs=List(x2207, x2208)).name("x2209").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:42:43") } // FixMul(x2207,x2208)
    val x2210 = withCtrl(x2220) { b995 } // FixConvert(b995,TRUE,_32,_0) (Same Type. No op)
    val x2211 = withCtrl(x2220) { OpDef(op=FixAdd, inputs=List(x2209, x2210)).name("x2211").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:42:43") } // FixAdd(x2209,x2210)
    val x2212 = withCtrl(x2220) { OpDef(op=FixSla, inputs=List(x2211, Const(2))).name("x2212").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:42:43") } // FixLsh(x2211,Const(2))
    val x2213 = withCtrl(x2220) { x2212 } // FixConvert(x2212,TRUE,_64,_0)
    val x2214 = withCtrl(x2220) { DramAddress(x2088).name("x2214").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:42:43") } // GetDRAMAddress(x2088)
    val x2216_x2215 = withCtrl(x2220) { OpDef(op=FixAdd, inputs=List(x2213, x2214)).name("x2216_x2215").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:42:43") } // FixAdd(x2213,x2214)
    // x2216 = SimpleStruct(ArrayBuffer((offset,x2215), (size,Const(4096)), (isLoad,Const(false))))
    val x2217 = withCtrl(x2220) { OpDef(op=BitAnd, inputs=List(b1098, b997)).name("x2217").srcCtx("UnrollingBase.scala:28:66") } // And(b1098,b997)
    val x2218 = withCtrl(x2220) { OpDef(op=BitAnd, inputs=List(x2217, b967)).name("x2218").srcCtx("UnrollingBase.scala:28:66") } // And(x2217,b967)
    val x2219_b2307_b2305 = withCtrl(x2220) { WriteMem(b2305, x2216_x2215).name("x2219_b2307_b2305").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:42:43") } // StreamWrite(x2203,x2216,x2218)
    val x2219_b2308_b2306 = withCtrl(x2220) { WriteMem(b2306, Const(4096)).name("x2219_b2308_b2306").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:42:43") } // StreamWrite(x2203,x2216,x2218)
    val x2221 = withCtrl(x2236) { Counter(min=Const(0), max=Const(1024), step=Const(1), par=16).name("x2221").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:42:43") } // CounterNew(Const(0),Const(1024),Const(1),Const(16))
    val x2222 = withCtrl(x2236) { CounterChain(List(x2221)).name("x2222").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:42:43") } // CounterChainNew(List(x2221))
    val x2230 = withCtrl(x2236) { LoopController(style=InnerPipe, level=InnerControl, cchain=x2222).name("x2230").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:42:43") } // UnrolledForeach(List(b1098, b997, b967),x2222,Block(Const(())),List(List(b1119)),List(List(b1120)))
    val b1119 = withCtrl(x2230) { CounterIter(x2221, None).name("b1119") } // b1119
    val b1120 = withCtrl(x2230) { Const(true).name("b1120") } // b1120
    val x2223 = withCtrl(x2230) { OpDef(op=BitAnd, inputs=List(b1120, b1098)).name("x2223").srcCtx("UnrollingBase.scala:28:66") } // And(b1120,b1098)
    val x2224 = withCtrl(x2230) { OpDef(op=BitAnd, inputs=List(b997, b967)).name("x2224").srcCtx("UnrollingBase.scala:28:66") } // And(b997,b967)
    val x2225 = withCtrl(x2230) { OpDef(op=BitAnd, inputs=List(x2223, x2224)).name("x2225").srcCtx("UnrollingBase.scala:28:66") } // And(x2223,x2224)
    val x2226 = withCtrl(x2230) { LoadBanks(List(x2169_d0_b0), List(b1097, b1119)).name("x2226").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:42:43") } // ParSRAMLoad(x2169,List(List(b1097, b1119)),List(x2225))
    val x2228_x2227 = withCtrl(x2230) { x2226 } // VectorApply(x2226,0)
    // x2228 = SimpleStruct(ArrayBuffer((_1,x2227), (_2,Const(true))))
    val x2229_x2229_x2204 = withCtrl(x2230) { WriteMem(x2204, x2228_x2227).name("x2229_x2229_x2204").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:42:43") } // ParStreamWrite(x2204,List(x2228),List(x2225))
    val x2231 = withCtrl(x2236) { FringeDenseStore(dram=List(x2088), cmdStream=List(b2305, b2306), dataStream=List(x2204), ackStream=List(x2205)).name("x2231").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:42:43") } // FringeDenseStore(x2088,x2203,x2204,x2205)
    val x2235 = withCtrl(x2236) { UnitController(style=SeqPipe, level=InnerControl).name("x2235").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:42:43") } // UnitPipe(List(b1098, b997, b967),Block(Const(())))
    val x2232 = withCtrl(x2235) { OpDef(op=BitAnd, inputs=List(b1098, b997)).name("x2232").srcCtx("UnrollingBase.scala:28:66") } // And(b1098,b997)
    val x2233 = withCtrl(x2235) { OpDef(op=BitAnd, inputs=List(x2232, b967)).name("x2233").srcCtx("UnrollingBase.scala:28:66") } // And(x2232,b967)
    val x2234_x2234 = withCtrl(x2235) { ReadMem(x2205).name("x2234_x2234").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:42:43") } // StreamRead(x2205,x2233)
    val x2237 = withCtrl(x2273) { Counter(min=Const(0), max=Const(64), step=Const(1), par=1).name("x2237").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:42:43") } // CounterNew(Const(0),Const(64),Const(1),Const(1))
    val x2238 = withCtrl(x2273) { CounterChain(List(x2237)).name("x2238").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:42:43") } // CounterChainNew(List(x2237))
    val x2272 = withCtrl(x2273) { LoopController(style=StreamPipe, level=OuterControl, cchain=x2238).name("x2272").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:42:43") } // UnrolledForeach(List(b998, b967),x2238,Block(Const(())),List(List(b1135)),List(List(b1136)))
    val b1135 = withCtrl(x2272) { CounterIter(x2237, Some(0)).name("b1135") } // b1135
    val b1136 = withCtrl(x2272) { Const(true).name("b1136") } // b1136
    val b2309 = withCtrl(x2272) { StreamOut(field="offset").name("b2309").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:42:43") } // x2239 = StreamOutNew(BurstCmdBus)
    isAccum(b2309) = false
    bufferDepthOf(b2309) = 1
    val b2310 = withCtrl(x2272) { StreamOut(field="size").name("b2310").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:42:43") } // x2239 = StreamOutNew(BurstCmdBus)
    isAccum(b2310) = false
    bufferDepthOf(b2310) = 1
    val x2240 = withCtrl(x2272) { StreamOut(field="data").name("x2240").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:42:43") } // x2240 = StreamOutNew(BurstFullDataBus())
    isAccum(x2240) = false
    bufferDepthOf(x2240) = 1
    val x2241 = withCtrl(x2272) { StreamIn(field="ack").name("x2241").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:42:43") } // x2241 = StreamInNew(BurstAckBus)
    isAccum(x2241) = false
    bufferDepthOf(x2241) = 1
    val x2256 = withCtrl(x2272) { UnitController(style=SeqPipe, level=InnerControl).name("x2256").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:42:43") } // UnitPipe(List(b1136, b998, b967),Block(x2255))
    val x2242 = withCtrl(x2256) { OpDef(op=FixAdd, inputs=List(b966, b1135)).name("x2242").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:42:43") } // FixAdd(b966,b1135)
    val x2243 = withCtrl(x2256) { x2242 } // FixConvert(x2242,TRUE,_32,_0) (Same Type. No op)
    val x2244 = withCtrl(x2256) { ReadMem(x2079_d0).name("x2244").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:28:30") } // RegRead(x2079)
    val x2245 = withCtrl(x2256) { OpDef(op=FixMul, inputs=List(x2243, x2244)).name("x2245").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:42:43") } // FixMul(x2243,x2244)
    val x2246 = withCtrl(x2256) { b996 } // FixConvert(b996,TRUE,_32,_0) (Same Type. No op)
    val x2247 = withCtrl(x2256) { OpDef(op=FixAdd, inputs=List(x2245, x2246)).name("x2247").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:42:43") } // FixAdd(x2245,x2246)
    val x2248 = withCtrl(x2256) { OpDef(op=FixSla, inputs=List(x2247, Const(2))).name("x2248").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:42:43") } // FixLsh(x2247,Const(2))
    val x2249 = withCtrl(x2256) { x2248 } // FixConvert(x2248,TRUE,_64,_0)
    val x2250 = withCtrl(x2256) { DramAddress(x2088).name("x2250").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:42:43") } // GetDRAMAddress(x2088)
    val x2252_x2251 = withCtrl(x2256) { OpDef(op=FixAdd, inputs=List(x2249, x2250)).name("x2252_x2251").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:42:43") } // FixAdd(x2249,x2250)
    // x2252 = SimpleStruct(ArrayBuffer((offset,x2251), (size,Const(4096)), (isLoad,Const(false))))
    val x2253 = withCtrl(x2256) { OpDef(op=BitAnd, inputs=List(b1136, b998)).name("x2253").srcCtx("UnrollingBase.scala:28:66") } // And(b1136,b998)
    val x2254 = withCtrl(x2256) { OpDef(op=BitAnd, inputs=List(x2253, b967)).name("x2254").srcCtx("UnrollingBase.scala:28:66") } // And(x2253,b967)
    val x2255_b2311_b2309 = withCtrl(x2256) { WriteMem(b2309, x2252_x2251).name("x2255_b2311_b2309").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:42:43") } // StreamWrite(x2239,x2252,x2254)
    val x2255_b2312_b2310 = withCtrl(x2256) { WriteMem(b2310, Const(4096)).name("x2255_b2312_b2310").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:42:43") } // StreamWrite(x2239,x2252,x2254)
    val x2257 = withCtrl(x2272) { Counter(min=Const(0), max=Const(1024), step=Const(1), par=16).name("x2257").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:42:43") } // CounterNew(Const(0),Const(1024),Const(1),Const(16))
    val x2258 = withCtrl(x2272) { CounterChain(List(x2257)).name("x2258").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:42:43") } // CounterChainNew(List(x2257))
    val x2266 = withCtrl(x2272) { LoopController(style=InnerPipe, level=InnerControl, cchain=x2258).name("x2266").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:42:43") } // UnrolledForeach(List(b1136, b998, b967),x2258,Block(Const(())),List(List(b1157)),List(List(b1158)))
    val b1157 = withCtrl(x2266) { CounterIter(x2257, None).name("b1157") } // b1157
    val b1158 = withCtrl(x2266) { Const(true).name("b1158") } // b1158
    val x2259 = withCtrl(x2266) { OpDef(op=BitAnd, inputs=List(b1158, b1136)).name("x2259").srcCtx("UnrollingBase.scala:28:66") } // And(b1158,b1136)
    val x2260 = withCtrl(x2266) { OpDef(op=BitAnd, inputs=List(b998, b967)).name("x2260").srcCtx("UnrollingBase.scala:28:66") } // And(b998,b967)
    val x2261 = withCtrl(x2266) { OpDef(op=BitAnd, inputs=List(x2259, x2260)).name("x2261").srcCtx("UnrollingBase.scala:28:66") } // And(x2259,x2260)
    val x2262 = withCtrl(x2266) { LoadBanks(List(x2170_d0_b0), List(b1135, b1157)).name("x2262").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:42:43") } // ParSRAMLoad(x2170,List(List(b1135, b1157)),List(x2261))
    val x2264_x2263 = withCtrl(x2266) { x2262 } // VectorApply(x2262,0)
    // x2264 = SimpleStruct(ArrayBuffer((_1,x2263), (_2,Const(true))))
    val x2265_x2265_x2240 = withCtrl(x2266) { WriteMem(x2240, x2264_x2263).name("x2265_x2265_x2240").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:42:43") } // ParStreamWrite(x2240,List(x2264),List(x2261))
    val x2267 = withCtrl(x2272) { FringeDenseStore(dram=List(x2088), cmdStream=List(b2309, b2310), dataStream=List(x2240), ackStream=List(x2241)).name("x2267").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:42:43") } // FringeDenseStore(x2088,x2239,x2240,x2241)
    val x2271 = withCtrl(x2272) { UnitController(style=SeqPipe, level=InnerControl).name("x2271").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:42:43") } // UnitPipe(List(b1136, b998, b967),Block(Const(())))
    val x2268 = withCtrl(x2271) { OpDef(op=BitAnd, inputs=List(b1136, b998)).name("x2268").srcCtx("UnrollingBase.scala:28:66") } // And(b1136,b998)
    val x2269 = withCtrl(x2271) { OpDef(op=BitAnd, inputs=List(x2268, b967)).name("x2269").srcCtx("UnrollingBase.scala:28:66") } // And(x2268,b967)
    val x2270_x2270 = withCtrl(x2271) { ReadMem(x2241).name("x2270_x2270").srcCtx("OuterProduct__M_12288_N_12288_ts1_64_ts2_1024_op1_1_op2_2_ip2_16_ip1_1.scala:42:43") } // StreamRead(x2241,x2269)
    
  }
}
