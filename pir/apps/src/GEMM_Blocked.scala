import pir._
import pir.node._
import arch._
import prism.enums._

object GEMM_Blocked extends PIRApp {
  def main(implicit design:PIRDesign) = {
    import design.pirmeta._
    val x3167_d0 = withCtrl(design.top.topController) { ArgIn(init=0).name("x3167_d0").srcCtx("GEMM_Blocked.scala:208:20") } // ArgInNew(Const(0))
    isAccum(x3167_d0) = false
    bufferDepthOf(x3167_d0) = 1
    boundOf(x3167_d0) = 128
    val x3167_d1 = withCtrl(design.top.topController) { ArgIn(init=0).name("x3167_d1").srcCtx("GEMM_Blocked.scala:208:20") } // ArgInNew(Const(0))
    isAccum(x3167_d1) = false
    bufferDepthOf(x3167_d1) = 1
    boundOf(x3167_d1) = 128
    val x3167_d2 = withCtrl(design.top.topController) { ArgIn(init=0).name("x3167_d2").srcCtx("GEMM_Blocked.scala:208:20") } // ArgInNew(Const(0))
    isAccum(x3167_d2) = false
    bufferDepthOf(x3167_d2) = 1
    boundOf(x3167_d2) = 128
    val x3167_d3 = withCtrl(design.top.topController) { ArgIn(init=0).name("x3167_d3").srcCtx("GEMM_Blocked.scala:208:20") } // ArgInNew(Const(0))
    isAccum(x3167_d3) = false
    bufferDepthOf(x3167_d3) = 1
    boundOf(x3167_d3) = 128
    val x3167_d4 = withCtrl(design.top.topController) { ArgIn(init=0).name("x3167_d4").srcCtx("GEMM_Blocked.scala:208:20") } // ArgInNew(Const(0))
    isAccum(x3167_d4) = false
    bufferDepthOf(x3167_d4) = 1
    boundOf(x3167_d4) = 128
    val x3167_d5 = withCtrl(design.top.topController) { ArgIn(init=0).name("x3167_d5").srcCtx("GEMM_Blocked.scala:208:20") } // ArgInNew(Const(0))
    isAccum(x3167_d5) = false
    bufferDepthOf(x3167_d5) = 1
    boundOf(x3167_d5) = 128
    val x3169 = withCtrl(design.top.topController) { ReadMem(x3167_d0).name("x3169").srcCtx("GEMM_Blocked.scala:210:30") } // RegRead(x3167)
    val x3170 = withCtrl(design.top.topController) { ReadMem(x3167_d0).name("x3170").srcCtx("GEMM_Blocked.scala:210:26") } // RegRead(x3167)
    val x3171 = withCtrl(design.top.topController) { DRAM(dims=List(x3170, x3169)).name("x3171").srcCtx("GEMM_Blocked.scala:210:25") } // x3171 = DRAMNew(ArrayBuffer(x3170, x3169),Const(0))
    val x3172 = withCtrl(design.top.topController) { ReadMem(x3167_d0).name("x3172").srcCtx("GEMM_Blocked.scala:211:30") } // RegRead(x3167)
    val x3173 = withCtrl(design.top.topController) { ReadMem(x3167_d0).name("x3173").srcCtx("GEMM_Blocked.scala:211:26") } // RegRead(x3167)
    val x3174 = withCtrl(design.top.topController) { DRAM(dims=List(x3173, x3172)).name("x3174").srcCtx("GEMM_Blocked.scala:211:25") } // x3174 = DRAMNew(ArrayBuffer(x3173, x3172),Const(0))
    val x3175 = withCtrl(design.top.topController) { ReadMem(x3167_d0).name("x3175").srcCtx("GEMM_Blocked.scala:212:30") } // RegRead(x3167)
    val x3176 = withCtrl(design.top.topController) { ReadMem(x3167_d0).name("x3176").srcCtx("GEMM_Blocked.scala:212:26") } // RegRead(x3167)
    val x3177 = withCtrl(design.top.topController) { DRAM(dims=List(x3176, x3175)).name("x3177").srcCtx("GEMM_Blocked.scala:212:25") } // x3177 = DRAMNew(ArrayBuffer(x3176, x3175),Const(0))
    val x3391 = withCtrl(design.top.topController) { UnitController(style=SeqPipe, level=OuterControl).name("x3391").srcCtx("GEMM_Blocked.scala:218:10") } // Hwblock(Block(Const(())),false)
    val x3181 = withCtrl(x3391) { ReadMem(x3167_d0).name("x3181").srcCtx("GEMM_Blocked.scala:219:15") } // RegRead(x3167)
    val x3182 = withCtrl(x3391) { Counter(min=Const(0), max=x3181, step=Const(64), par=1).name("x3182").srcCtx("GEMM_Blocked.scala:219:26") } // CounterNew(Const(0),x3181,Const(64),Const(1))
    val x3183 = withCtrl(x3391) { CounterChain(List(x3182)).name("x3183").srcCtx("GEMM_Blocked.scala:219:39") } // CounterChainNew(List(x3182))
    val x3390 = withCtrl(x3391) { LoopController(style=MetaPipe, level=OuterControl, cchain=x3183).name("x3390").srcCtx("GEMM_Blocked.scala:219:39") } // UnrolledForeach(List(Const(true)),x3183,Block(Const(())),List(List(b1803)),List(List(b1804)))
    val b1803 = withCtrl(x3390) { CounterIter(x3182, Some(0)).name("b1803") } // b1803
    val b1804 = withCtrl(x3390) { Const(true).name("b1804") } // b1804
    val x3184 = withCtrl(x3390) { ReadMem(x3167_d0).name("x3184").srcCtx("GEMM_Blocked.scala:220:17") } // RegRead(x3167)
    val x3185 = withCtrl(x3390) { Counter(min=Const(0), max=x3184, step=Const(64), par=1).name("x3185").srcCtx("GEMM_Blocked.scala:220:27") } // CounterNew(Const(0),x3184,Const(64),Const(1))
    val x3186 = withCtrl(x3390) { CounterChain(List(x3185)).name("x3186").srcCtx("GEMM_Blocked.scala:220:40") } // CounterChainNew(List(x3185))
    val x3389 = withCtrl(x3390) { LoopController(style=MetaPipe, level=OuterControl, cchain=x3186).name("x3389").srcCtx("GEMM_Blocked.scala:220:40") } // UnrolledForeach(List(b1804),x3186,Block(Const(())),List(List(b1808)),List(List(b1809)))
    val b1808 = withCtrl(x3389) { CounterIter(x3185, Some(0)).name("b1808") } // b1808
    val b1809 = withCtrl(x3389) { Const(true).name("b1809") } // b1809
    val x3187_d0_b0 = withCtrl(x3389) { SRAM(size=4096, banking=Strided(banks=16, stride=1)).name("x3187_d0_b0").srcCtx("GEMM_Blocked.scala:221:30") } // x3187 = SRAMNew(ArrayBuffer(Const(64), Const(64)))
    isAccum(x3187_d0_b0) = false
    bufferDepthOf(x3187_d0_b0) = 3
    staticDimsOf(x3187_d0_b0) = List(64, 64)
    val x3187_d1_b0 = withCtrl(x3389) { SRAM(size=4096, banking=Strided(banks=16, stride=1)).name("x3187_d1_b0").srcCtx("GEMM_Blocked.scala:221:30") } // x3187 = SRAMNew(ArrayBuffer(Const(64), Const(64)))
    isAccum(x3187_d1_b0) = true
    bufferDepthOf(x3187_d1_b0) = 1
    staticDimsOf(x3187_d1_b0) = List(64, 64)
    val x3188 = withCtrl(x3389) { ReadMem(x3167_d0).name("x3188").srcCtx("GEMM_Blocked.scala:222:35") } // RegRead(x3167)
    val x3189 = withCtrl(x3389) { Counter(min=Const(0), max=x3188, step=Const(64), par=1).name("x3189").srcCtx("GEMM_Blocked.scala:222:45") } // CounterNew(Const(0),x3188,Const(64),Const(1))
    val x3190 = withCtrl(x3389) { CounterChain(List(x3189)).name("x3190").srcCtx("GEMM_Blocked.scala:239:12") } // CounterChainNew(List(x3189))
    val x3349 = withCtrl(x3389) { LoopController(style=MetaPipe, level=OuterControl, cchain=x3190).name("x3349").srcCtx("GEMM_Blocked.scala:239:12") } // UnrolledReduce(List(b1809, b1804),x3190,x3187,Block((x3187) => Const(())),List(List(b1817)),List(List(b1818)))
    val b1817 = withCtrl(x3349) { CounterIter(x3189, Some(0)).name("b1817") } // b1817
    val b1818 = withCtrl(x3349) { Const(true).name("b1818") } // b1818
    val x3191_d0_b0 = withCtrl(x3349) { SRAM(size=4096, banking=Strided(banks=16, stride=1)).name("x3191_d0_b0").srcCtx("GEMM_Blocked.scala:223:40") } // x3191 = SRAMNew(ArrayBuffer(Const(64), Const(64)))
    isAccum(x3191_d0_b0) = false
    bufferDepthOf(x3191_d0_b0) = 2
    staticDimsOf(x3191_d0_b0) = List(64, 64)
    val x3192_d0_b0 = withCtrl(x3349) { SRAM(size=4096, banking=Strided(banks=16, stride=1)).name("x3192_d0_b0").srcCtx("GEMM_Blocked.scala:224:33") } // x3192 = SRAMNew(ArrayBuffer(Const(64), Const(64)))
    isAccum(x3192_d0_b0) = false
    bufferDepthOf(x3192_d0_b0) = 2
    staticDimsOf(x3192_d0_b0) = List(64, 64)
    val x3195 = withCtrl(x3349) { UnitController(style=SeqPipe, level=InnerControl).name("x3195").srcCtx("GEMM_Blocked.scala:239:12") } // UnitPipe(List(b1818, b1809, b1804),Block(Const(())))
    val x3193 = withCtrl(x3195) { OpDef(op=FixAdd, inputs=List(b1817, Const(64))).name("x3193").srcCtx("GEMM_Blocked.scala:225:38") } // FixAdd(b1817,Const(64))
    val x3194 = withCtrl(x3195) { OpDef(op=FixAdd, inputs=List(b1808, Const(64))).name("x3194").srcCtx("GEMM_Blocked.scala:225:49") } // FixAdd(b1808,Const(64))
    val x3196 = withCtrl(x3349) { Counter(min=Const(0), max=Const(64), step=Const(1), par=1).name("x3196").srcCtx("GEMM_Blocked.scala:225:20") } // CounterNew(Const(0),Const(64),Const(1),Const(1))
    val x3197 = withCtrl(x3349) { CounterChain(List(x3196)).name("x3197").srcCtx("GEMM_Blocked.scala:225:20") } // CounterChainNew(List(x3196))
    val x3227 = withCtrl(x3349) { LoopController(style=StreamPipe, level=OuterControl, cchain=x3197).name("x3227").srcCtx("GEMM_Blocked.scala:225:20") } // UnrolledForeach(List(b1818, b1809, b1804),x3197,Block(Const(())),List(List(b1826)),List(List(b1827)))
    val b1826 = withCtrl(x3227) { CounterIter(x3196, Some(0)).name("b1826") } // b1826
    val b1827 = withCtrl(x3227) { Const(true).name("b1827") } // b1827
    val b3432 = withCtrl(x3227) { StreamOut(field="offset").name("b3432").srcCtx("GEMM_Blocked.scala:225:20") } // x3198 = StreamOutNew(BurstCmdBus)
    isAccum(b3432) = false
    bufferDepthOf(b3432) = 1
    val b3433 = withCtrl(x3227) { StreamOut(field="size").name("b3433").srcCtx("GEMM_Blocked.scala:225:20") } // x3198 = StreamOutNew(BurstCmdBus)
    isAccum(b3433) = false
    bufferDepthOf(b3433) = 1
    val x3199 = withCtrl(x3227) { StreamIn(field="data").name("x3199").srcCtx("GEMM_Blocked.scala:225:20") } // x3199 = StreamInNew(BurstDataBus())
    isAccum(x3199) = false
    bufferDepthOf(x3199) = 1
    val x3215 = withCtrl(x3227) { UnitController(style=SeqPipe, level=InnerControl).name("x3215").srcCtx("GEMM_Blocked.scala:225:20") } // UnitPipe(List(b1827, b1818, b1809, b1804),Block(x3214))
    val x3200 = withCtrl(x3215) { OpDef(op=FixAdd, inputs=List(b1817, b1826)).name("x3200").srcCtx("GEMM_Blocked.scala:225:20") } // FixAdd(b1817,b1826)
    val x3201 = withCtrl(x3215) { x3200 } // FixConvert(x3200,TRUE,_32,_0) (Same Type. No op)
    val x3202 = withCtrl(x3215) { ReadMem(x3167_d0).name("x3202").srcCtx("GEMM_Blocked.scala:211:30") } // RegRead(x3167)
    val x3203 = withCtrl(x3215) { OpDef(op=FixMul, inputs=List(x3201, x3202)).name("x3203").srcCtx("GEMM_Blocked.scala:225:20") } // FixMul(x3201,x3202)
    val x3204 = withCtrl(x3215) { b1808 } // FixConvert(b1808,TRUE,_32,_0) (Same Type. No op)
    val x3205 = withCtrl(x3215) { OpDef(op=FixAdd, inputs=List(x3203, x3204)).name("x3205").srcCtx("GEMM_Blocked.scala:225:20") } // FixAdd(x3203,x3204)
    val x3206 = withCtrl(x3215) { OpDef(op=FixSla, inputs=List(x3205, Const(2))).name("x3206").srcCtx("GEMM_Blocked.scala:225:20") } // FixLsh(x3205,Const(2))
    val x3207 = withCtrl(x3215) { x3206 } // FixConvert(x3206,TRUE,_64,_0)
    val x3208 = withCtrl(x3215) { DramAddress(x3174).name("x3208").srcCtx("GEMM_Blocked.scala:225:20") } // GetDRAMAddress(x3174)
    val x3210_x3209 = withCtrl(x3215) { OpDef(op=FixAdd, inputs=List(x3207, x3208)).name("x3210_x3209").srcCtx("GEMM_Blocked.scala:225:20") } // FixAdd(x3207,x3208)
    // x3210 = SimpleStruct(ArrayBuffer((offset,x3209), (size,Const(256)), (isLoad,Const(true))))
    val x3211 = withCtrl(x3215) { OpDef(op=BitAnd, inputs=List(b1827, b1818)).name("x3211").srcCtx("UnrollingBase.scala:28:66") } // And(b1827,b1818)
    val x3212 = withCtrl(x3215) { OpDef(op=BitAnd, inputs=List(b1809, b1804)).name("x3212").srcCtx("UnrollingBase.scala:28:66") } // And(b1809,b1804)
    val x3213 = withCtrl(x3215) { OpDef(op=BitAnd, inputs=List(x3211, x3212)).name("x3213").srcCtx("UnrollingBase.scala:28:66") } // And(x3211,x3212)
    val x3214_b3434_b3432 = withCtrl(x3215) { WriteMem(b3432, x3210_x3209).name("x3214_b3434_b3432").srcCtx("GEMM_Blocked.scala:225:20") } // StreamWrite(x3198,x3210,x3213)
    val x3214_b3435_b3433 = withCtrl(x3215) { WriteMem(b3433, Const(256)).name("x3214_b3435_b3433").srcCtx("GEMM_Blocked.scala:225:20") } // StreamWrite(x3198,x3210,x3213)
    val x3216 = withCtrl(x3227) { FringeDenseLoad(dram=List(x3174), cmdStream=List(b3432, b3433), dataStream=List(x3199)).name("x3216").srcCtx("GEMM_Blocked.scala:225:20") } // FringeDenseLoad(x3174,x3198,x3199)
    val x3217 = withCtrl(x3227) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x3217").srcCtx("GEMM_Blocked.scala:225:20") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x3218 = withCtrl(x3227) { CounterChain(List(x3217)).name("x3218").srcCtx("GEMM_Blocked.scala:225:20") } // CounterChainNew(List(x3217))
    val x3226 = withCtrl(x3227) { LoopController(style=InnerPipe, level=InnerControl, cchain=x3218).name("x3226").srcCtx("GEMM_Blocked.scala:225:20") } // UnrolledForeach(List(b1827, b1818, b1809, b1804),x3218,Block(Const(())),List(List(b1849)),List(List(b1850)))
    val b1849 = withCtrl(x3226) { CounterIter(x3217, None).name("b1849") } // b1849
    val b1850 = withCtrl(x3226) { Const(true).name("b1850") } // b1850
    val x3219 = withCtrl(x3226) { OpDef(op=BitAnd, inputs=List(b1850, b1827)).name("x3219").srcCtx("UnrollingBase.scala:28:66") } // And(b1850,b1827)
    val x3220 = withCtrl(x3226) { OpDef(op=BitAnd, inputs=List(b1818, b1809)).name("x3220").srcCtx("UnrollingBase.scala:28:66") } // And(b1818,b1809)
    val x3221 = withCtrl(x3226) { OpDef(op=BitAnd, inputs=List(x3219, x3220)).name("x3221").srcCtx("UnrollingBase.scala:28:66") } // And(x3219,x3220)
    val x3222 = withCtrl(x3226) { OpDef(op=BitAnd, inputs=List(x3221, b1804)).name("x3222").srcCtx("UnrollingBase.scala:28:66") } // And(x3221,b1804)
    val x3223_x3223 = withCtrl(x3226) { ReadMem(x3199).name("x3223_x3223").srcCtx("GEMM_Blocked.scala:225:20") } // ParStreamRead(x3199,List(x3222))
    val x3224_x3224 = withCtrl(x3226) { x3223_x3223 } // VectorApply(x3223,0)
    val x3225 = withCtrl(x3226) { StoreBanks(List(List(x3192_d0_b0)), List(b1826, b1849), x3224_x3224).name("x3225").srcCtx("GEMM_Blocked.scala:225:20") } // ParSRAMStore(x3192,List(List(b1826, b1849)),List(x3224),List(x3222))
    val x3228 = withCtrl(x3349) { Counter(min=Const(0), max=Const(64), step=Const(1), par=1).name("x3228").srcCtx("GEMM_Blocked.scala:226:30") } // CounterNew(Const(0),Const(64),Const(1),Const(1))
    val x3229 = withCtrl(x3349) { CounterChain(List(x3228)).name("x3229").srcCtx("GEMM_Blocked.scala:226:42") } // CounterChainNew(List(x3228))
    val x3330 = withCtrl(x3349) { LoopController(style=MetaPipe, level=OuterControl, cchain=x3229).name("x3330").srcCtx("GEMM_Blocked.scala:226:42") } // UnrolledForeach(List(b1818, b1809, b1804),x3229,Block(Const(())),List(List(b1862)),List(List(b1863)))
    val b1862 = withCtrl(x3330) { CounterIter(x3228, Some(0)).name("b1862") } // b1862
    val b1863 = withCtrl(x3330) { Const(true).name("b1863") } // b1863
    val x3230_d0_b0 = withCtrl(x3330) { SRAM(size=64, banking=Strided(banks=1, stride=1)).name("x3230_d0_b0").srcCtx("GEMM_Blocked.scala:227:35") } // x3230 = SRAMNew(ArrayBuffer(Const(64)))
    isAccum(x3230_d0_b0) = false
    bufferDepthOf(x3230_d0_b0) = 2
    staticDimsOf(x3230_d0_b0) = List(64)
    val x3231 = withCtrl(x3330) { Reg(init=Some(0)).name("x3231").srcCtx("GEMM_Blocked.scala:226:42") } // x3231 = RegNew(Const(0))
    isAccum(x3231) = false
    bufferDepthOf(x3231) = 2
    val x3239 = withCtrl(x3330) { UnitController(style=SeqPipe, level=InnerControl).name("x3239").srcCtx("GEMM_Blocked.scala:226:42") } // UnitPipe(List(b1863, b1818, b1809, b1804),Block(Const(())))
    val x3232 = withCtrl(x3239) { OpDef(op=FixAdd, inputs=List(b1803, b1862)).name("x3232").srcCtx("GEMM_Blocked.scala:228:36") } // FixAdd(b1803,b1862)
    val x3233 = withCtrl(x3239) { OpDef(op=FixAdd, inputs=List(b1817, Const(64))).name("x3233").srcCtx("GEMM_Blocked.scala:228:46") } // FixAdd(b1817,Const(64))
    val x3234 = withCtrl(x3239) { OpDef(op=FixAdd, inputs=List(x3232, Const(1))).name("x3234").srcCtx("GEMM_Blocked.scala:228:33") } // FixAdd(x3232,Const(1))
    val x3235 = withCtrl(x3239) { OpDef(op=BitAnd, inputs=List(b1863, b1818)).name("x3235").srcCtx("UnrollingBase.scala:28:66") } // And(b1863,b1818)
    val x3236 = withCtrl(x3239) { OpDef(op=BitAnd, inputs=List(b1809, b1804)).name("x3236").srcCtx("UnrollingBase.scala:28:66") } // And(b1809,b1804)
    val x3237 = withCtrl(x3239) { OpDef(op=BitAnd, inputs=List(x3235, x3236)).name("x3237").srcCtx("UnrollingBase.scala:28:66") } // And(x3235,x3236)
    val x3238_x3231 = withCtrl(x3239) { WriteMem(x3231, x3232).name("x3238_x3231").srcCtx("GEMM_Blocked.scala:226:42") } // RegWrite(x3231,x3232,x3237)
    val x3240 = withCtrl(x3330) { Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x3240").srcCtx("GEMM_Blocked.scala:228:22") } // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x3241 = withCtrl(x3330) { CounterChain(List(x3240)).name("x3241").srcCtx("GEMM_Blocked.scala:228:22") } // CounterChainNew(List(x3240))
    val x3274 = withCtrl(x3330) { LoopController(style=StreamPipe, level=OuterControl, cchain=x3241).name("x3274").srcCtx("GEMM_Blocked.scala:228:22") } // UnrolledForeach(List(b1863, b1818, b1809, b1804),x3241,Block(Const(())),List(List(b1876)),List(List(b1877)))
    val b1876 = withCtrl(x3274) { CounterIter(x3240, Some(0)).name("b1876") } // b1876
    val b1877 = withCtrl(x3274) { Const(true).name("b1877") } // b1877
    val b3436 = withCtrl(x3274) { StreamOut(field="offset").name("b3436").srcCtx("GEMM_Blocked.scala:228:22") } // x3242 = StreamOutNew(BurstCmdBus)
    isAccum(b3436) = false
    bufferDepthOf(b3436) = 1
    val b3437 = withCtrl(x3274) { StreamOut(field="size").name("b3437").srcCtx("GEMM_Blocked.scala:228:22") } // x3242 = StreamOutNew(BurstCmdBus)
    isAccum(b3437) = false
    bufferDepthOf(b3437) = 1
    val x3243 = withCtrl(x3274) { StreamIn(field="data").name("x3243").srcCtx("GEMM_Blocked.scala:228:22") } // x3243 = StreamInNew(BurstDataBus())
    isAccum(x3243) = false
    bufferDepthOf(x3243) = 1
    val x3261 = withCtrl(x3274) { UnitController(style=SeqPipe, level=InnerControl).name("x3261").srcCtx("GEMM_Blocked.scala:228:22") } // UnitPipe(List(b1877, b1863, b1818, b1809, b1804),Block(x3260))
    val x3244 = withCtrl(x3261) { ReadMem(x3231).name("x3244").srcCtx("GEMM_Blocked.scala:226:42") } // RegRead(x3231)
    val x3245 = withCtrl(x3261) { OpDef(op=FixAdd, inputs=List(x3244, b1876)).name("x3245").srcCtx("GEMM_Blocked.scala:228:22") } // FixAdd(x3244,b1876)
    val x3246 = withCtrl(x3261) { x3245 } // FixConvert(x3245,TRUE,_32,_0) (Same Type. No op)
    val x3247 = withCtrl(x3261) { ReadMem(x3167_d0).name("x3247").srcCtx("GEMM_Blocked.scala:210:30") } // RegRead(x3167)
    val x3248 = withCtrl(x3261) { OpDef(op=FixMul, inputs=List(x3246, x3247)).name("x3248").srcCtx("GEMM_Blocked.scala:228:22") } // FixMul(x3246,x3247)
    val x3249 = withCtrl(x3261) { b1817 } // FixConvert(b1817,TRUE,_32,_0) (Same Type. No op)
    val x3250 = withCtrl(x3261) { OpDef(op=FixAdd, inputs=List(x3248, x3249)).name("x3250").srcCtx("GEMM_Blocked.scala:228:22") } // FixAdd(x3248,x3249)
    val x3251 = withCtrl(x3261) { OpDef(op=FixSla, inputs=List(x3250, Const(2))).name("x3251").srcCtx("GEMM_Blocked.scala:228:22") } // FixLsh(x3250,Const(2))
    val x3252 = withCtrl(x3261) { x3251 } // FixConvert(x3251,TRUE,_64,_0)
    val x3253 = withCtrl(x3261) { DramAddress(x3171).name("x3253").srcCtx("GEMM_Blocked.scala:228:22") } // GetDRAMAddress(x3171)
    val x3255_x3254 = withCtrl(x3261) { OpDef(op=FixAdd, inputs=List(x3252, x3253)).name("x3255_x3254").srcCtx("GEMM_Blocked.scala:228:22") } // FixAdd(x3252,x3253)
    // x3255 = SimpleStruct(ArrayBuffer((offset,x3254), (size,Const(256)), (isLoad,Const(true))))
    val x3256 = withCtrl(x3261) { OpDef(op=BitAnd, inputs=List(b1877, b1863)).name("x3256").srcCtx("UnrollingBase.scala:28:66") } // And(b1877,b1863)
    val x3257 = withCtrl(x3261) { OpDef(op=BitAnd, inputs=List(b1818, b1809)).name("x3257").srcCtx("UnrollingBase.scala:28:66") } // And(b1818,b1809)
    val x3258 = withCtrl(x3261) { OpDef(op=BitAnd, inputs=List(x3256, x3257)).name("x3258").srcCtx("UnrollingBase.scala:28:66") } // And(x3256,x3257)
    val x3259 = withCtrl(x3261) { OpDef(op=BitAnd, inputs=List(x3258, b1804)).name("x3259").srcCtx("UnrollingBase.scala:28:66") } // And(x3258,b1804)
    val x3260_b3438_b3436 = withCtrl(x3261) { WriteMem(b3436, x3255_x3254).name("x3260_b3438_b3436").srcCtx("GEMM_Blocked.scala:228:22") } // StreamWrite(x3242,x3255,x3259)
    val x3260_b3439_b3437 = withCtrl(x3261) { WriteMem(b3437, Const(256)).name("x3260_b3439_b3437").srcCtx("GEMM_Blocked.scala:228:22") } // StreamWrite(x3242,x3255,x3259)
    val x3262 = withCtrl(x3274) { FringeDenseLoad(dram=List(x3171), cmdStream=List(b3436, b3437), dataStream=List(x3243)).name("x3262").srcCtx("GEMM_Blocked.scala:228:22") } // FringeDenseLoad(x3171,x3242,x3243)
    val x3263 = withCtrl(x3274) { Counter(min=Const(0), max=Const(64), step=Const(1), par=1).name("x3263").srcCtx("GEMM_Blocked.scala:228:22") } // CounterNew(Const(0),Const(64),Const(1),Const(1))
    val x3264 = withCtrl(x3274) { CounterChain(List(x3263)).name("x3264").srcCtx("GEMM_Blocked.scala:228:22") } // CounterChainNew(List(x3263))
    val x3273 = withCtrl(x3274) { LoopController(style=InnerPipe, level=InnerControl, cchain=x3264).name("x3273").srcCtx("GEMM_Blocked.scala:228:22") } // UnrolledForeach(List(b1877, b1863, b1818, b1809, b1804),x3264,Block(Const(())),List(List(b1901)),List(List(b1902)))
    val b1901 = withCtrl(x3273) { CounterIter(x3263, None).name("b1901") } // b1901
    val b1902 = withCtrl(x3273) { Const(true).name("b1902") } // b1902
    val x3265 = withCtrl(x3273) { OpDef(op=BitAnd, inputs=List(b1902, b1877)).name("x3265").srcCtx("UnrollingBase.scala:28:66") } // And(b1902,b1877)
    val x3266 = withCtrl(x3273) { OpDef(op=BitAnd, inputs=List(b1863, b1818)).name("x3266").srcCtx("UnrollingBase.scala:28:66") } // And(b1863,b1818)
    val x3267 = withCtrl(x3273) { OpDef(op=BitAnd, inputs=List(b1809, b1804)).name("x3267").srcCtx("UnrollingBase.scala:28:66") } // And(b1809,b1804)
    val x3268 = withCtrl(x3273) { OpDef(op=BitAnd, inputs=List(x3265, x3266)).name("x3268").srcCtx("UnrollingBase.scala:28:66") } // And(x3265,x3266)
    val x3269 = withCtrl(x3273) { OpDef(op=BitAnd, inputs=List(x3268, x3267)).name("x3269").srcCtx("UnrollingBase.scala:28:66") } // And(x3268,x3267)
    val x3270_x3270 = withCtrl(x3273) { ReadMem(x3243).name("x3270_x3270").srcCtx("GEMM_Blocked.scala:228:22") } // ParStreamRead(x3243,List(x3269))
    val x3271_x3271 = withCtrl(x3273) { x3270_x3270 } // VectorApply(x3270,0)
    val x3272 = withCtrl(x3273) { StoreBanks(List(List(x3230_d0_b0)), List(b1901), x3271_x3271).name("x3272").srcCtx("GEMM_Blocked.scala:228:22") } // ParSRAMStore(x3230,List(List(b1901)),List(x3271),List(x3269))
    val x3275_d0_b0 = withCtrl(x3330) { SRAM(size=64, banking=Strided(banks=16, stride=1)).name("x3275_d0_b0").srcCtx("GEMM_Blocked.scala:229:34") } // x3275 = SRAMNew(ArrayBuffer(Const(64)))
    isAccum(x3275_d0_b0) = false
    bufferDepthOf(x3275_d0_b0) = 2
    staticDimsOf(x3275_d0_b0) = List(64)
    val x3275_d1_b0 = withCtrl(x3330) { SRAM(size=64, banking=Strided(banks=16, stride=1)).name("x3275_d1_b0").srcCtx("GEMM_Blocked.scala:229:34") } // x3275 = SRAMNew(ArrayBuffer(Const(64)))
    isAccum(x3275_d1_b0) = true
    bufferDepthOf(x3275_d1_b0) = 1
    staticDimsOf(x3275_d1_b0) = List(64)
    val x3276 = withCtrl(x3330) { Counter(min=Const(0), max=Const(64), step=Const(1), par=1).name("x3276").srcCtx("GEMM_Blocked.scala:230:47") } // CounterNew(Const(0),Const(64),Const(1),Const(1))
    val x3277 = withCtrl(x3330) { CounterChain(List(x3276)).name("x3277").srcCtx("GEMM_Blocked.scala:235:16") } // CounterChainNew(List(x3276))
    val x3319 = withCtrl(x3330) { LoopController(style=MetaPipe, level=OuterControl, cchain=x3277).name("x3319").srcCtx("GEMM_Blocked.scala:235:16") } // UnrolledReduce(List(b1863, b1818, b1809, b1804),x3277,x3275,Block((x3275) => Const(())),List(List(b1918)),List(List(b1919)))
    val b1918 = withCtrl(x3319) { CounterIter(x3276, Some(0)).name("b1918") } // b1918
    val b1919 = withCtrl(x3319) { Const(true).name("b1919") } // b1919
    val x3278_d0_b0 = withCtrl(x3319) { SRAM(size=64, banking=Strided(banks=16, stride=1)).name("x3278_d0_b0").srcCtx("GEMM_Blocked.scala:231:44") } // x3278 = SRAMNew(ArrayBuffer(Const(64)))
    isAccum(x3278_d0_b0) = false
    bufferDepthOf(x3278_d0_b0) = 2
    staticDimsOf(x3278_d0_b0) = List(64)
    val x3279 = withCtrl(x3319) { Reg(init=Some(0.0)).name("x3279").srcCtx("GEMM_Blocked.scala:235:16") } // x3279 = RegNew(Const(0))
    isAccum(x3279) = false
    bufferDepthOf(x3279) = 2
    val x3286 = withCtrl(x3319) { UnitController(style=SeqPipe, level=InnerControl).name("x3286").srcCtx("GEMM_Blocked.scala:235:16") } // UnitPipe(List(b1919, b1863, b1818, b1809, b1804),Block(Const(())))
    val x3280 = withCtrl(x3286) { OpDef(op=BitAnd, inputs=List(b1919, b1863)).name("x3280").srcCtx("UnrollingBase.scala:28:66") } // And(b1919,b1863)
    val x3281 = withCtrl(x3286) { OpDef(op=BitAnd, inputs=List(b1818, b1809)).name("x3281").srcCtx("UnrollingBase.scala:28:66") } // And(b1818,b1809)
    val x3282 = withCtrl(x3286) { OpDef(op=BitAnd, inputs=List(x3280, x3281)).name("x3282").srcCtx("UnrollingBase.scala:28:66") } // And(x3280,x3281)
    val x3283 = withCtrl(x3286) { OpDef(op=BitAnd, inputs=List(x3282, b1804)).name("x3283").srcCtx("UnrollingBase.scala:28:66") } // And(x3282,b1804)
    val x3284 = withCtrl(x3286) { LoadBanks(List(x3230_d0_b0), List(b1918)).name("x3284").srcCtx("GEMM_Blocked.scala:232:36") } // SRAMLoad(x3230,ArrayBuffer(Const(64)),List(b1918),Const(0),x3283)
    val x3285_x3279 = withCtrl(x3286) { WriteMem(x3279, x3284).name("x3285_x3279").srcCtx("GEMM_Blocked.scala:235:16") } // RegWrite(x3279,x3284,x3283)
    val x3287 = withCtrl(x3319) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x3287").srcCtx("GEMM_Blocked.scala:233:33") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x3288 = withCtrl(x3319) { CounterChain(List(x3287)).name("x3288").srcCtx("GEMM_Blocked.scala:233:41") } // CounterChainNew(List(x3287))
    val x3299 = withCtrl(x3319) { LoopController(style=InnerPipe, level=InnerControl, cchain=x3288).name("x3299").srcCtx("GEMM_Blocked.scala:233:41") } // UnrolledForeach(List(b1919, b1863, b1818, b1809, b1804),x3288,Block(Const(())),List(List(b1931)),List(List(b1932)))
    val b1931 = withCtrl(x3299) { CounterIter(x3287, None).name("b1931") } // b1931
    val b1932 = withCtrl(x3299) { Const(true).name("b1932") } // b1932
    val x3289 = withCtrl(x3299) { OpDef(op=BitAnd, inputs=List(b1932, b1919)).name("x3289").srcCtx("UnrollingBase.scala:28:66") } // And(b1932,b1919)
    val x3290 = withCtrl(x3299) { OpDef(op=BitAnd, inputs=List(b1863, b1818)).name("x3290").srcCtx("UnrollingBase.scala:28:66") } // And(b1863,b1818)
    val x3291 = withCtrl(x3299) { OpDef(op=BitAnd, inputs=List(b1809, b1804)).name("x3291").srcCtx("UnrollingBase.scala:28:66") } // And(b1809,b1804)
    val x3292 = withCtrl(x3299) { OpDef(op=BitAnd, inputs=List(x3289, x3290)).name("x3292").srcCtx("UnrollingBase.scala:28:66") } // And(x3289,x3290)
    val x3293 = withCtrl(x3299) { OpDef(op=BitAnd, inputs=List(x3292, x3291)).name("x3293").srcCtx("UnrollingBase.scala:28:66") } // And(x3292,x3291)
    val x3294 = withCtrl(x3299) { LoadBanks(List(x3192_d0_b0), List(b1918, b1931)).name("x3294").srcCtx("GEMM_Blocked.scala:233:73") } // ParSRAMLoad(x3192,List(List(b1918, b1931)),List(x3293))
    val x3295 = withCtrl(x3299) { x3294 } // VectorApply(x3294,0)
    val x3296 = withCtrl(x3299) { ReadMem(x3279).name("x3296").srcCtx("GEMM_Blocked.scala:235:16") } // RegRead(x3279)
    val x3297 = withCtrl(x3299) { OpDef(op=FixMul, inputs=List(x3295, x3296)).name("x3297").srcCtx("GEMM_Blocked.scala:233:80") } // FixMul(x3295,x3296)
    val x3298 = withCtrl(x3299) { StoreBanks(List(List(x3278_d0_b0)), List(b1931), x3297).name("x3298").srcCtx("GEMM_Blocked.scala:233:65") } // ParSRAMStore(x3278,List(List(b1931)),List(x3297),List(x3293))
    val x3300 = withCtrl(x3319) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x3300").srcCtx("GEMM_Blocked.scala:235:16") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x3301 = withCtrl(x3319) { CounterChain(List(x3300)).name("x3301").srcCtx("GEMM_Blocked.scala:235:16") } // CounterChainNew(ArrayBuffer(x3300))
    val x3318 = withCtrl(x3319) { LoopController(style=InnerPipe, level=InnerControl, cchain=x3301).name("x3318").srcCtx("GEMM_Blocked.scala:235:16") } // UnrolledForeach(List(),x3301,Block(Const(())),ArrayBuffer(List(b1944)),ArrayBuffer(List(b1945)))
    val b1944 = withCtrl(x3318) { CounterIter(x3300, None).name("b1944") } // b1944
    val b1945 = withCtrl(x3318) { Const(true).name("b1945") } // b1945
    val x3302 = withCtrl(x3318) { OpDef(op=BitAnd, inputs=List(b1945, b1863)).name("x3302").srcCtx("UnrollingBase.scala:28:66") } // And(b1945,b1863)
    val x3303 = withCtrl(x3318) { OpDef(op=BitAnd, inputs=List(b1818, b1809)).name("x3303").srcCtx("UnrollingBase.scala:28:66") } // And(b1818,b1809)
    val x3304 = withCtrl(x3318) { OpDef(op=BitAnd, inputs=List(x3302, x3303)).name("x3304").srcCtx("UnrollingBase.scala:28:66") } // And(x3302,x3303)
    val x3305 = withCtrl(x3318) { OpDef(op=BitAnd, inputs=List(x3304, b1804)).name("x3305").srcCtx("UnrollingBase.scala:28:66") } // And(x3304,b1804)
    val x3306 = withCtrl(x3318) { LoadBanks(List(x3278_d0_b0), List(b1944)).name("x3306").srcCtx("GEMM_Blocked.scala:235:16") } // ParSRAMLoad(x3278,List(ArrayBuffer(b1944)),List(x3305))
    val x3307 = withCtrl(x3318) { x3306 } // VectorApply(x3306,0)
    val x3308 = withCtrl(x3318) { LoadBanks(List(x3275_d1_b0), List(b1944)).name("x3308").srcCtx("GEMM_Blocked.scala:235:16") } // ParSRAMLoad(x3275,List(ArrayBuffer(b1944)),List(x3305))
    val x3309 = withCtrl(x3318) { x3308 } // VectorApply(x3308,0)
    val x3310 = withCtrl(x3318) { OpDef(op=BitAnd, inputs=List(b1919, b1863)).name("x3310").srcCtx("GEMM_Blocked.scala:235:16") } // And(b1919,b1863)
    val x3311 = withCtrl(x3318) { OpDef(op=BitAnd, inputs=List(x3310, x3303)).name("x3311").srcCtx("GEMM_Blocked.scala:235:16") } // And(x3310,x3303)
    val x3312 = withCtrl(x3318) { OpDef(op=BitAnd, inputs=List(x3311, b1804)).name("x3312").srcCtx("GEMM_Blocked.scala:235:16") } // And(x3311,b1804)
    val x3313 = withCtrl(x3318) { OpDef(op=BitAnd, inputs=List(x3312, x3305)).name("x3313").srcCtx("GEMM_Blocked.scala:235:16") } // And(x3312,x3305)
    val x3314 = withCtrl(x3318) { OpDef(op=FixEql, inputs=List(b1918, Const(0))).name("x3314").srcCtx("GEMM_Blocked.scala:235:16") } // FixEql(b1918,Const(0))
    val x3315 = withCtrl(x3318) { OpDef(op=FixAdd, inputs=List(x3307, x3309)).name("x3315").srcCtx("GEMM_Blocked.scala:235:18") } // FixAdd(x3307,x3309)
    val x3316 = withCtrl(x3318) { OpDef(op=MuxOp, inputs=List(x3314, x3307, x3315)).name("x3316").srcCtx("GEMM_Blocked.scala:235:16") } // Mux(x3314,x3307,x3315)
    val x3317 = withCtrl(x3318) { StoreBanks(List(List(x3275_d0_b0), List(x3275_d1_b0)), List(b1944), x3316).name("x3317").srcCtx("GEMM_Blocked.scala:235:16") } // ParSRAMStore(x3275,List(ArrayBuffer(b1944)),List(x3316),List(x3305))
    antiDepsOf(x3317)=List(x3308)
    val x3320 = withCtrl(x3330) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x3320").srcCtx("GEMM_Blocked.scala:236:31") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x3321 = withCtrl(x3330) { CounterChain(List(x3320)).name("x3321").srcCtx("GEMM_Blocked.scala:236:38") } // CounterChainNew(List(x3320))
    val x3329 = withCtrl(x3330) { LoopController(style=InnerPipe, level=InnerControl, cchain=x3321).name("x3329").srcCtx("GEMM_Blocked.scala:236:38") } // UnrolledForeach(List(b1863, b1818, b1809, b1804),x3321,Block(Const(())),List(List(b1966)),List(List(b1967)))
    val b1966 = withCtrl(x3329) { CounterIter(x3320, None).name("b1966") } // b1966
    val b1967 = withCtrl(x3329) { Const(true).name("b1967") } // b1967
    val x3322 = withCtrl(x3329) { OpDef(op=BitAnd, inputs=List(b1967, b1863)).name("x3322").srcCtx("UnrollingBase.scala:28:66") } // And(b1967,b1863)
    val x3323 = withCtrl(x3329) { OpDef(op=BitAnd, inputs=List(b1818, b1809)).name("x3323").srcCtx("UnrollingBase.scala:28:66") } // And(b1818,b1809)
    val x3324 = withCtrl(x3329) { OpDef(op=BitAnd, inputs=List(x3322, x3323)).name("x3324").srcCtx("UnrollingBase.scala:28:66") } // And(x3322,x3323)
    val x3325 = withCtrl(x3329) { OpDef(op=BitAnd, inputs=List(x3324, b1804)).name("x3325").srcCtx("UnrollingBase.scala:28:66") } // And(x3324,b1804)
    val x3326 = withCtrl(x3329) { LoadBanks(List(x3275_d0_b0), List(b1966)).name("x3326").srcCtx("GEMM_Blocked.scala:236:74") } // ParSRAMLoad(x3275,List(List(b1966)),List(x3325))
    val x3327 = withCtrl(x3329) { x3326 } // VectorApply(x3326,0)
    val x3328 = withCtrl(x3329) { StoreBanks(List(List(x3191_d0_b0)), List(b1862, b1966), x3327).name("x3328").srcCtx("GEMM_Blocked.scala:236:67") } // ParSRAMStore(x3191,List(List(b1862, b1966)),List(x3327),List(x3325))
    val x3331 = withCtrl(x3349) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x3331").srcCtx("GEMM_Blocked.scala:239:12") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x3332 = withCtrl(x3349) { Counter(min=Const(0), max=Const(64), step=Const(1), par=1).name("x3332").srcCtx("GEMM_Blocked.scala:239:12") } // CounterNew(Const(0),Const(64),Const(1),Const(1))
    val x3333 = withCtrl(x3349) { CounterChain(List(x3332,x3331)).name("x3333").srcCtx("GEMM_Blocked.scala:239:12") } // CounterChainNew(ArrayBuffer(x3332, x3331))
    val x3348 = withCtrl(x3349) { LoopController(style=InnerPipe, level=InnerControl, cchain=x3333).name("x3348").srcCtx("GEMM_Blocked.scala:239:12") } // UnrolledForeach(List(),x3333,Block(Const(())),ArrayBuffer(List(b1977), List(b1978)),ArrayBuffer(List(b1979), List(b1980)))
    val b1977 = withCtrl(x3348) { CounterIter(x3332, Some(0)).name("b1977") } // b1977
    val b1979 = withCtrl(x3348) { Const(true).name("b1979") } // b1979
    val b1978 = withCtrl(x3348) { CounterIter(x3331, None).name("b1978") } // b1978
    val b1980 = withCtrl(x3348) { Const(true).name("b1980") } // b1980
    val x3334 = withCtrl(x3348) { OpDef(op=BitAnd, inputs=List(b1979, b1980)).name("x3334").srcCtx("UnrollingBase.scala:28:66") } // And(b1979,b1980)
    val x3335 = withCtrl(x3348) { OpDef(op=BitAnd, inputs=List(b1809, b1804)).name("x3335").srcCtx("UnrollingBase.scala:28:66") } // And(b1809,b1804)
    val x3336 = withCtrl(x3348) { OpDef(op=BitAnd, inputs=List(x3334, x3335)).name("x3336").srcCtx("UnrollingBase.scala:28:66") } // And(x3334,x3335)
    val x3337 = withCtrl(x3348) { LoadBanks(List(x3191_d0_b0), List(b1977, b1978)).name("x3337").srcCtx("GEMM_Blocked.scala:239:12") } // ParSRAMLoad(x3191,List(ArrayBuffer(b1977, b1978)),List(x3336))
    val x3338 = withCtrl(x3348) { x3337 } // VectorApply(x3337,0)
    val x3339 = withCtrl(x3348) { LoadBanks(List(x3187_d1_b0), List(b1977, b1978)).name("x3339").srcCtx("GEMM_Blocked.scala:239:12") } // ParSRAMLoad(x3187,List(ArrayBuffer(b1977, b1978)),List(x3336))
    val x3340 = withCtrl(x3348) { x3339 } // VectorApply(x3339,0)
    val x3341 = withCtrl(x3348) { OpDef(op=BitAnd, inputs=List(b1818, b1809)).name("x3341").srcCtx("GEMM_Blocked.scala:239:12") } // And(b1818,b1809)
    val x3342 = withCtrl(x3348) { OpDef(op=BitAnd, inputs=List(x3341, b1804)).name("x3342").srcCtx("GEMM_Blocked.scala:239:12") } // And(x3341,b1804)
    val x3343 = withCtrl(x3348) { OpDef(op=BitAnd, inputs=List(x3342, x3336)).name("x3343").srcCtx("GEMM_Blocked.scala:239:12") } // And(x3342,x3336)
    val x3344 = withCtrl(x3348) { OpDef(op=FixEql, inputs=List(b1817, Const(0))).name("x3344").srcCtx("GEMM_Blocked.scala:239:12") } // FixEql(b1817,Const(0))
    val x3345 = withCtrl(x3348) { OpDef(op=FixAdd, inputs=List(x3338, x3340)).name("x3345").srcCtx("GEMM_Blocked.scala:239:14") } // FixAdd(x3338,x3340)
    val x3346 = withCtrl(x3348) { OpDef(op=MuxOp, inputs=List(x3344, x3338, x3345)).name("x3346").srcCtx("GEMM_Blocked.scala:239:12") } // Mux(x3344,x3338,x3345)
    val x3347 = withCtrl(x3348) { StoreBanks(List(List(x3187_d0_b0), List(x3187_d1_b0)), List(b1977, b1978), x3346).name("x3347").srcCtx("GEMM_Blocked.scala:239:12") } // ParSRAMStore(x3187,List(ArrayBuffer(b1977, b1978)),List(x3346),List(x3336))
    antiDepsOf(x3347)=List(x3339)
    val x3352 = withCtrl(x3389) { UnitController(style=SeqPipe, level=InnerControl).name("x3352").srcCtx("GEMM_Blocked.scala:220:40") } // UnitPipe(List(b1809, b1804),Block(Const(())))
    val x3350 = withCtrl(x3352) { OpDef(op=FixAdd, inputs=List(b1803, Const(64))).name("x3350").srcCtx("GEMM_Blocked.scala:240:24") } // FixAdd(b1803,Const(64))
    val x3351 = withCtrl(x3352) { OpDef(op=FixAdd, inputs=List(b1808, Const(64))).name("x3351").srcCtx("GEMM_Blocked.scala:240:36") } // FixAdd(b1808,Const(64))
    val x3353 = withCtrl(x3389) { Counter(min=Const(0), max=Const(64), step=Const(1), par=1).name("x3353").srcCtx("GEMM_Blocked.scala:240:48") } // CounterNew(Const(0),Const(64),Const(1),Const(1))
    val x3354 = withCtrl(x3389) { CounterChain(List(x3353)).name("x3354").srcCtx("GEMM_Blocked.scala:240:48") } // CounterChainNew(List(x3353))
    val x3388 = withCtrl(x3389) { LoopController(style=StreamPipe, level=OuterControl, cchain=x3354).name("x3388").srcCtx("GEMM_Blocked.scala:240:48") } // UnrolledForeach(List(b1809, b1804),x3354,Block(Const(())),List(List(b2002)),List(List(b2003)))
    val b2002 = withCtrl(x3388) { CounterIter(x3353, Some(0)).name("b2002") } // b2002
    val b2003 = withCtrl(x3388) { Const(true).name("b2003") } // b2003
    val b3440 = withCtrl(x3388) { StreamOut(field="offset").name("b3440").srcCtx("GEMM_Blocked.scala:240:48") } // x3355 = StreamOutNew(BurstCmdBus)
    isAccum(b3440) = false
    bufferDepthOf(b3440) = 1
    val b3441 = withCtrl(x3388) { StreamOut(field="size").name("b3441").srcCtx("GEMM_Blocked.scala:240:48") } // x3355 = StreamOutNew(BurstCmdBus)
    isAccum(b3441) = false
    bufferDepthOf(b3441) = 1
    val x3356 = withCtrl(x3388) { StreamOut(field="data").name("x3356").srcCtx("GEMM_Blocked.scala:240:48") } // x3356 = StreamOutNew(BurstFullDataBus())
    isAccum(x3356) = false
    bufferDepthOf(x3356) = 1
    val x3357 = withCtrl(x3388) { StreamIn(field="ack").name("x3357").srcCtx("GEMM_Blocked.scala:240:48") } // x3357 = StreamInNew(BurstAckBus)
    isAccum(x3357) = false
    bufferDepthOf(x3357) = 1
    val x3372 = withCtrl(x3388) { UnitController(style=SeqPipe, level=InnerControl).name("x3372").srcCtx("GEMM_Blocked.scala:240:48") } // UnitPipe(List(b2003, b1809, b1804),Block(x3371))
    val x3358 = withCtrl(x3372) { OpDef(op=FixAdd, inputs=List(b1803, b2002)).name("x3358").srcCtx("GEMM_Blocked.scala:240:48") } // FixAdd(b1803,b2002)
    val x3359 = withCtrl(x3372) { x3358 } // FixConvert(x3358,TRUE,_32,_0) (Same Type. No op)
    val x3360 = withCtrl(x3372) { ReadMem(x3167_d0).name("x3360").srcCtx("GEMM_Blocked.scala:212:30") } // RegRead(x3167)
    val x3361 = withCtrl(x3372) { OpDef(op=FixMul, inputs=List(x3359, x3360)).name("x3361").srcCtx("GEMM_Blocked.scala:240:48") } // FixMul(x3359,x3360)
    val x3362 = withCtrl(x3372) { b1808 } // FixConvert(b1808,TRUE,_32,_0) (Same Type. No op)
    val x3363 = withCtrl(x3372) { OpDef(op=FixAdd, inputs=List(x3361, x3362)).name("x3363").srcCtx("GEMM_Blocked.scala:240:48") } // FixAdd(x3361,x3362)
    val x3364 = withCtrl(x3372) { OpDef(op=FixSla, inputs=List(x3363, Const(2))).name("x3364").srcCtx("GEMM_Blocked.scala:240:48") } // FixLsh(x3363,Const(2))
    val x3365 = withCtrl(x3372) { x3364 } // FixConvert(x3364,TRUE,_64,_0)
    val x3366 = withCtrl(x3372) { DramAddress(x3177).name("x3366").srcCtx("GEMM_Blocked.scala:240:48") } // GetDRAMAddress(x3177)
    val x3368_x3367 = withCtrl(x3372) { OpDef(op=FixAdd, inputs=List(x3365, x3366)).name("x3368_x3367").srcCtx("GEMM_Blocked.scala:240:48") } // FixAdd(x3365,x3366)
    // x3368 = SimpleStruct(ArrayBuffer((offset,x3367), (size,Const(256)), (isLoad,Const(false))))
    val x3369 = withCtrl(x3372) { OpDef(op=BitAnd, inputs=List(b2003, b1809)).name("x3369").srcCtx("UnrollingBase.scala:28:66") } // And(b2003,b1809)
    val x3370 = withCtrl(x3372) { OpDef(op=BitAnd, inputs=List(x3369, b1804)).name("x3370").srcCtx("UnrollingBase.scala:28:66") } // And(x3369,b1804)
    val x3371_b3442_b3440 = withCtrl(x3372) { WriteMem(b3440, x3368_x3367).name("x3371_b3442_b3440").srcCtx("GEMM_Blocked.scala:240:48") } // StreamWrite(x3355,x3368,x3370)
    val x3371_b3443_b3441 = withCtrl(x3372) { WriteMem(b3441, Const(256)).name("x3371_b3443_b3441").srcCtx("GEMM_Blocked.scala:240:48") } // StreamWrite(x3355,x3368,x3370)
    val x3373 = withCtrl(x3388) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x3373").srcCtx("GEMM_Blocked.scala:240:48") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x3374 = withCtrl(x3388) { CounterChain(List(x3373)).name("x3374").srcCtx("GEMM_Blocked.scala:240:48") } // CounterChainNew(List(x3373))
    val x3382 = withCtrl(x3388) { LoopController(style=InnerPipe, level=InnerControl, cchain=x3374).name("x3382").srcCtx("GEMM_Blocked.scala:240:48") } // UnrolledForeach(List(b2003, b1809, b1804),x3374,Block(Const(())),List(List(b2024)),List(List(b2025)))
    val b2024 = withCtrl(x3382) { CounterIter(x3373, None).name("b2024") } // b2024
    val b2025 = withCtrl(x3382) { Const(true).name("b2025") } // b2025
    val x3375 = withCtrl(x3382) { OpDef(op=BitAnd, inputs=List(b2025, b2003)).name("x3375").srcCtx("UnrollingBase.scala:28:66") } // And(b2025,b2003)
    val x3376 = withCtrl(x3382) { OpDef(op=BitAnd, inputs=List(b1809, b1804)).name("x3376").srcCtx("UnrollingBase.scala:28:66") } // And(b1809,b1804)
    val x3377 = withCtrl(x3382) { OpDef(op=BitAnd, inputs=List(x3375, x3376)).name("x3377").srcCtx("UnrollingBase.scala:28:66") } // And(x3375,x3376)
    val x3378 = withCtrl(x3382) { LoadBanks(List(x3187_d0_b0), List(b2002, b2024)).name("x3378").srcCtx("GEMM_Blocked.scala:240:48") } // ParSRAMLoad(x3187,List(List(b2002, b2024)),List(x3377))
    val x3380_x3379 = withCtrl(x3382) { x3378 } // VectorApply(x3378,0)
    // x3380 = SimpleStruct(ArrayBuffer((_1,x3379), (_2,Const(true))))
    val x3381_x3381_x3356 = withCtrl(x3382) { WriteMem(x3356, x3380_x3379).name("x3381_x3381_x3356").srcCtx("GEMM_Blocked.scala:240:48") } // ParStreamWrite(x3356,List(x3380),List(x3377))
    val x3383 = withCtrl(x3388) { FringeDenseStore(dram=List(x3177), cmdStream=List(b3440, b3441), dataStream=List(x3356), ackStream=List(x3357)).name("x3383").srcCtx("GEMM_Blocked.scala:240:48") } // FringeDenseStore(x3177,x3355,x3356,x3357)
    val x3387 = withCtrl(x3388) { UnitController(style=SeqPipe, level=InnerControl).name("x3387").srcCtx("GEMM_Blocked.scala:240:48") } // UnitPipe(List(b2003, b1809, b1804),Block(Const(())))
    val x3384 = withCtrl(x3387) { OpDef(op=BitAnd, inputs=List(b2003, b1809)).name("x3384").srcCtx("UnrollingBase.scala:28:66") } // And(b2003,b1809)
    val x3385 = withCtrl(x3387) { OpDef(op=BitAnd, inputs=List(x3384, b1804)).name("x3385").srcCtx("UnrollingBase.scala:28:66") } // And(x3384,b1804)
    val x3386_x3386 = withCtrl(x3387) { ReadMem(x3357).name("x3386_x3386").srcCtx("GEMM_Blocked.scala:240:48") } // StreamRead(x3357,x3385)
    
  }
}
