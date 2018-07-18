import pir._
import pir.node._
import arch._
import prism.enums._

object GDA extends PIRApp {
  def main(implicit design:PIRDesign) = {
    import design.pirmeta._
    val x3159 = withCtrl(design.top.topController) { ArgIn(init=0).name("x3159").srcCtx("GDA.scala:22:18:R") } // ArgInNew(Const(0))
    isAccum(x3159) = false
    bufferDepthOf(x3159) = 1
    boundOf(x3159) = 1024
    val x3161 = withCtrl(design.top.topController) { ReadMem(x3159).name("x3161").srcCtx("GDA.scala:25:21") } // RegRead(x3159)
    val x3162 = withCtrl(design.top.topController) { DRAM(dims=List(x3161, Const(64))).name("x3162").srcCtx("GDA.scala:25:20:x") } // x3162 = DRAMNew(ArrayBuffer(x3161, Const(64)),Const(0))
    val x3163 = withCtrl(design.top.topController) { ReadMem(x3159).name("x3163").srcCtx("GDA.scala:26:23") } // RegRead(x3159)
    val x3164 = withCtrl(design.top.topController) { DRAM(dims=List(x3163)).name("x3164").srcCtx("GDA.scala:26:22:y") } // x3164 = DRAMNew(ArrayBuffer(x3163),Const(0))
    val x3165 = withCtrl(design.top.topController) { DRAM(dims=List(Const(64))).name("x3165").srcCtx("GDA.scala:27:22:mu0") } // x3165 = DRAMNew(ArrayBuffer(Const(64)),Const(0))
    val x3166 = withCtrl(design.top.topController) { DRAM(dims=List(Const(64))).name("x3166").srcCtx("GDA.scala:28:22:mu1") } // x3166 = DRAMNew(ArrayBuffer(Const(64)),Const(0))
    val x3167 = withCtrl(design.top.topController) { DRAM(dims=List(Const(64), Const(64))).name("x3167").srcCtx("GDA.scala:29:24:sigma") } // x3167 = DRAMNew(ArrayBuffer(Const(64), Const(64)),Const(0))
    val x3359 = withCtrl(design.top.topController) { UnitController(style=SeqPipe, level=OuterControl).name("x3359").srcCtx("GDA.scala:36:11") } // Hwblock(Block(Const(())),false)
    val x3172_d0_b0 = withCtrl(x3359) { SRAM(size=64, banking=Strided(banks=16, stride=1)).name("x3172_d0_b0").srcCtx("GDA.scala:37:28:mu0Tile") } // x3172 = SRAMNew(ArrayBuffer(Const(64)))
    isAccum(x3172_d0_b0) = false
    bufferDepthOf(x3172_d0_b0) = 1
    staticDimsOf(x3172_d0_b0) = List(64)
    val x3173_d0_b0 = withCtrl(x3359) { SRAM(size=64, banking=Strided(banks=16, stride=1)).name("x3173_d0_b0").srcCtx("GDA.scala:38:28:mu1Tile") } // x3173 = SRAMNew(ArrayBuffer(Const(64)))
    isAccum(x3173_d0_b0) = false
    bufferDepthOf(x3173_d0_b0) = 1
    staticDimsOf(x3173_d0_b0) = List(64)
    val x3210 = withCtrl(x3359) { UnitController(style=ForkJoin, level=OuterControl).name("x3210").srcCtx("GDA.scala:39:16") } // ParallelPipe(List(Const(true)),Block(Const(())))
    val x3191 = withCtrl(x3210) { UnitController(style=StreamPipe, level=OuterControl).name("x3191").srcCtx("GDA.scala:40:17") } // UnitPipe(List(Const(true)),Block(Const(())))
    val b3411 = withCtrl(x3191) { StreamOut(field="offset").name("b3411").srcCtx("GDA.scala:40:17") } // x3174 = StreamOutNew(BurstCmdBus)
    isAccum(b3411) = false
    bufferDepthOf(b3411) = 1
    val b3412 = withCtrl(x3191) { StreamOut(field="size").name("b3412").srcCtx("GDA.scala:40:17") } // x3174 = StreamOutNew(BurstCmdBus)
    isAccum(b3412) = false
    bufferDepthOf(b3412) = 1
    val x3175 = withCtrl(x3191) { StreamIn(field="data").name("x3175").srcCtx("GDA.scala:40:17") } // x3175 = StreamInNew(BurstDataBus())
    isAccum(x3175) = false
    bufferDepthOf(x3175) = 1
    val x3183 = withCtrl(x3191) { UnitController(style=SeqPipe, level=InnerControl).name("x3183").srcCtx("GDA.scala:40:17") } // UnitPipe(List(Const(true)),Block(x3182))
    val x3176 = withCtrl(x3183) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x3177 = withCtrl(x3183) { OpDef(op=FixSla, inputs=List(x3176, Const(2))).name("x3177").srcCtx("GDA.scala:40:17") } // FixLsh(x3176,Const(2))
    val x3178 = withCtrl(x3183) { x3177 } // FixConvert(x3177,TRUE,_64,_0)
    val x3179 = withCtrl(x3183) { DramAddress(x3165).name("x3179").srcCtx("GDA.scala:40:17") } // GetDRAMAddress(x3165)
    val x3181_x3180 = withCtrl(x3183) { OpDef(op=FixAdd, inputs=List(x3178, x3179)).name("x3181_x3180").srcCtx("GDA.scala:40:17") } // FixAdd(x3178,x3179)
    // x3181 = SimpleStruct(ArrayBuffer((offset,x3180), (size,Const(256)), (isLoad,Const(true))))
    val x3182_b3413_b3411 = withCtrl(x3183) { WriteMem(b3411, x3181_x3180).name("x3182_b3413_b3411").srcCtx("GDA.scala:40:17") } // StreamWrite(x3174,x3181,Const(true))
    val x3182_b3414_b3412 = withCtrl(x3183) { WriteMem(b3412, Const(256)).name("x3182_b3414_b3412").srcCtx("GDA.scala:40:17") } // StreamWrite(x3174,x3181,Const(true))
    val x3184 = withCtrl(x3191) { FringeDenseLoad(dram=List(x3165), cmdStream=List(b3411, b3412), dataStream=List(x3175)).name("x3184").srcCtx("GDA.scala:40:17") } // FringeDenseLoad(x3165,x3174,x3175)
    val x3185 = withCtrl(x3191) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x3185").srcCtx("GDA.scala:40:17") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x3186 = withCtrl(x3191) { CounterChain(List(x3185)).name("x3186").srcCtx("GDA.scala:40:17") } // CounterChainNew(List(x3185))
    val x3190 = withCtrl(x3191) { LoopController(style=InnerPipe, level=InnerControl, cchain=x3186).name("x3190").srcCtx("GDA.scala:40:17") } // UnrolledForeach(List(Const(true)),x3186,Block(Const(())),List(List(b1872)),List(List(b1873)))
    val b1872 = withCtrl(x3190) { CounterIter(x3185, None).name("b1872") } // b1872
    val b1873 = withCtrl(x3190) { Const(true).name("b1873") } // b1873
    val x3187_x3187 = withCtrl(x3190) { ReadMem(x3175).name("x3187_x3187").srcCtx("GDA.scala:40:17") } // ParStreamRead(x3175,List(b1873))
    val x3188_x3188 = withCtrl(x3190) { x3187_x3187 } // VectorApply(x3187,0)
    val x3189 = withCtrl(x3190) { StoreBanks(List(List(x3172_d0_b0)), List(b1872), x3188_x3188).name("x3189").srcCtx("GDA.scala:40:17") } // ParSRAMStore(x3172,List(List(b1872)),List(x3188),List(b1873))
    val x3209 = withCtrl(x3210) { UnitController(style=StreamPipe, level=OuterControl).name("x3209").srcCtx("GDA.scala:41:17") } // UnitPipe(List(Const(true)),Block(Const(())))
    val b3415 = withCtrl(x3209) { StreamOut(field="offset").name("b3415").srcCtx("GDA.scala:41:17") } // x3192 = StreamOutNew(BurstCmdBus)
    isAccum(b3415) = false
    bufferDepthOf(b3415) = 1
    val b3416 = withCtrl(x3209) { StreamOut(field="size").name("b3416").srcCtx("GDA.scala:41:17") } // x3192 = StreamOutNew(BurstCmdBus)
    isAccum(b3416) = false
    bufferDepthOf(b3416) = 1
    val x3193 = withCtrl(x3209) { StreamIn(field="data").name("x3193").srcCtx("GDA.scala:41:17") } // x3193 = StreamInNew(BurstDataBus())
    isAccum(x3193) = false
    bufferDepthOf(x3193) = 1
    val x3201 = withCtrl(x3209) { UnitController(style=SeqPipe, level=InnerControl).name("x3201").srcCtx("GDA.scala:41:17") } // UnitPipe(List(Const(true)),Block(x3200))
    val x3194 = withCtrl(x3201) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x3195 = withCtrl(x3201) { OpDef(op=FixSla, inputs=List(x3194, Const(2))).name("x3195").srcCtx("GDA.scala:41:17") } // FixLsh(x3194,Const(2))
    val x3196 = withCtrl(x3201) { x3195 } // FixConvert(x3195,TRUE,_64,_0)
    val x3197 = withCtrl(x3201) { DramAddress(x3166).name("x3197").srcCtx("GDA.scala:41:17") } // GetDRAMAddress(x3166)
    val x3199_x3198 = withCtrl(x3201) { OpDef(op=FixAdd, inputs=List(x3196, x3197)).name("x3199_x3198").srcCtx("GDA.scala:41:17") } // FixAdd(x3196,x3197)
    // x3199 = SimpleStruct(ArrayBuffer((offset,x3198), (size,Const(256)), (isLoad,Const(true))))
    val x3200_b3417_b3415 = withCtrl(x3201) { WriteMem(b3415, x3199_x3198).name("x3200_b3417_b3415").srcCtx("GDA.scala:41:17") } // StreamWrite(x3192,x3199,Const(true))
    val x3200_b3418_b3416 = withCtrl(x3201) { WriteMem(b3416, Const(256)).name("x3200_b3418_b3416").srcCtx("GDA.scala:41:17") } // StreamWrite(x3192,x3199,Const(true))
    val x3202 = withCtrl(x3209) { FringeDenseLoad(dram=List(x3166), cmdStream=List(b3415, b3416), dataStream=List(x3193)).name("x3202").srcCtx("GDA.scala:41:17") } // FringeDenseLoad(x3166,x3192,x3193)
    val x3203 = withCtrl(x3209) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x3203").srcCtx("GDA.scala:41:17") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x3204 = withCtrl(x3209) { CounterChain(List(x3203)).name("x3204").srcCtx("GDA.scala:41:17") } // CounterChainNew(List(x3203))
    val x3208 = withCtrl(x3209) { LoopController(style=InnerPipe, level=InnerControl, cchain=x3204).name("x3208").srcCtx("GDA.scala:41:17") } // UnrolledForeach(List(Const(true)),x3204,Block(Const(())),List(List(b1892)),List(List(b1893)))
    val b1892 = withCtrl(x3208) { CounterIter(x3203, None).name("b1892") } // b1892
    val b1893 = withCtrl(x3208) { Const(true).name("b1893") } // b1893
    val x3205_x3205 = withCtrl(x3208) { ReadMem(x3193).name("x3205_x3205").srcCtx("GDA.scala:41:17") } // ParStreamRead(x3193,List(b1893))
    val x3206_x3206 = withCtrl(x3208) { x3205_x3205 } // VectorApply(x3205,0)
    val x3207 = withCtrl(x3208) { StoreBanks(List(List(x3173_d0_b0)), List(b1892), x3206_x3206).name("x3207").srcCtx("GDA.scala:41:17") } // ParSRAMStore(x3173,List(List(b1892)),List(x3206),List(b1893))
    val x3211_d0_b0 = withCtrl(x3359) { SRAM(size=4096, banking=Strided(banks=16, stride=1)).name("x3211_d0_b0").srcCtx("GDA.scala:44:29:sigmaOut") } // x3211 = SRAMNew(ArrayBuffer(Const(64), Const(64)))
    isAccum(x3211_d0_b0) = false
    bufferDepthOf(x3211_d0_b0) = 1
    staticDimsOf(x3211_d0_b0) = List(64, 64)
    val x3211_d1_b0 = withCtrl(x3359) { SRAM(size=4096, banking=Strided(banks=16, stride=1)).name("x3211_d1_b0").srcCtx("GDA.scala:44:29:sigmaOut") } // x3211 = SRAMNew(ArrayBuffer(Const(64), Const(64)))
    isAccum(x3211_d1_b0) = true
    bufferDepthOf(x3211_d1_b0) = 1
    staticDimsOf(x3211_d1_b0) = List(64, 64)
    val x3212 = withCtrl(x3359) { ReadMem(x3159).name("x3212").srcCtx("GDA.scala:46:34") } // RegRead(x3159)
    val x3213 = withCtrl(x3359) { Counter(min=Const(0), max=x3212, step=Const(32), par=1).name("x3213").srcCtx("GDA.scala:46:42") } // CounterNew(Const(0),x3212,Const(32),Const(1))
    val x3214 = withCtrl(x3359) { CounterChain(List(x3213)).name("x3214").srcCtx("GDA.scala:68:8") } // CounterChainNew(List(x3213))
    val x3330 = withCtrl(x3359) { LoopController(style=MetaPipe, level=OuterControl, cchain=x3214).name("x3330").srcCtx("GDA.scala:68:8") } // UnrolledReduce(List(Const(true)),x3214,x3211,Block((x3211) => Const(())),List(List(b1907)),List(List(b1908)))
    val b1907 = withCtrl(x3330) { CounterIter(x3213, Some(0)).name("b1907") } // b1907
    val b1908 = withCtrl(x3330) { Const(true).name("b1908") } // b1908
    val x3215_d0_b0 = withCtrl(x3330) { SRAM(size=32, banking=Strided(banks=16, stride=1)).name("x3215_d0_b0").srcCtx("GDA.scala:47:33:gdaYtile") } // x3215 = SRAMNew(ArrayBuffer(Const(32)))
    isAccum(x3215_d0_b0) = false
    bufferDepthOf(x3215_d0_b0) = 2
    staticDimsOf(x3215_d0_b0) = List(32)
    val x3216_d0_b0 = withCtrl(x3330) { SRAM(size=2048, banking=Strided(banks=16, stride=1)).name("x3216_d0_b0").srcCtx("GDA.scala:48:31:gdaXtile") } // x3216 = SRAMNew(ArrayBuffer(Const(32), Const(64)))
    isAccum(x3216_d0_b0) = false
    bufferDepthOf(x3216_d0_b0) = 2
    staticDimsOf(x3216_d0_b0) = List(32, 64)
    val x3265 = withCtrl(x3330) { UnitController(style=ForkJoin, level=OuterControl).name("x3265").srcCtx("GDA.scala:50:18") } // ParallelPipe(List(b1908),Block(Const(())))
    val x3218 = withCtrl(x3265) { UnitController(style=SeqPipe, level=InnerControl).name("x3218").srcCtx("GDA.scala:50:18") } // UnitPipe(List(b1908),Block(Const(())))
    val x3217 = withCtrl(x3218) { OpDef(op=FixAdd, inputs=List(b1907, Const(32))).name("x3217").srcCtx("GDA.scala:51:34") } // FixAdd(b1907,Const(32))
    val x3237 = withCtrl(x3265) { UnitController(style=StreamPipe, level=OuterControl).name("x3237").srcCtx("GDA.scala:51:20") } // UnitPipe(List(b1908),Block(Const(())))
    val b3419 = withCtrl(x3237) { StreamOut(field="offset").name("b3419").srcCtx("GDA.scala:51:20") } // x3219 = StreamOutNew(BurstCmdBus)
    isAccum(b3419) = false
    bufferDepthOf(b3419) = 1
    val b3420 = withCtrl(x3237) { StreamOut(field="size").name("b3420").srcCtx("GDA.scala:51:20") } // x3219 = StreamOutNew(BurstCmdBus)
    isAccum(b3420) = false
    bufferDepthOf(b3420) = 1
    val x3220 = withCtrl(x3237) { StreamIn(field="data").name("x3220").srcCtx("GDA.scala:51:20") } // x3220 = StreamInNew(BurstDataBus())
    isAccum(x3220) = false
    bufferDepthOf(x3220) = 1
    val x3228 = withCtrl(x3237) { UnitController(style=SeqPipe, level=InnerControl).name("x3228").srcCtx("GDA.scala:51:20") } // UnitPipe(List(b1908),Block(x3227))
    val x3221 = withCtrl(x3228) { b1907 } // FixConvert(b1907,TRUE,_32,_0) (Same Type. No op)
    val x3222 = withCtrl(x3228) { OpDef(op=FixSla, inputs=List(x3221, Const(2))).name("x3222").srcCtx("GDA.scala:51:20") } // FixLsh(x3221,Const(2))
    val x3223 = withCtrl(x3228) { x3222 } // FixConvert(x3222,TRUE,_64,_0)
    val x3224 = withCtrl(x3228) { DramAddress(x3164).name("x3224").srcCtx("GDA.scala:51:20") } // GetDRAMAddress(x3164)
    val x3226_x3225 = withCtrl(x3228) { OpDef(op=FixAdd, inputs=List(x3223, x3224)).name("x3226_x3225").srcCtx("GDA.scala:51:20") } // FixAdd(x3223,x3224)
    // x3226 = SimpleStruct(ArrayBuffer((offset,x3225), (size,Const(128)), (isLoad,Const(true))))
    val x3227_b3421_b3419 = withCtrl(x3228) { WriteMem(b3419, x3226_x3225).name("x3227_b3421_b3419").srcCtx("GDA.scala:51:20") } // StreamWrite(x3219,x3226,b1908)
    val x3227_b3422_b3420 = withCtrl(x3228) { WriteMem(b3420, Const(128)).name("x3227_b3422_b3420").srcCtx("GDA.scala:51:20") } // StreamWrite(x3219,x3226,b1908)
    val x3229 = withCtrl(x3237) { FringeDenseLoad(dram=List(x3164), cmdStream=List(b3419, b3420), dataStream=List(x3220)).name("x3229").srcCtx("GDA.scala:51:20") } // FringeDenseLoad(x3164,x3219,x3220)
    val x3230 = withCtrl(x3237) { Counter(min=Const(0), max=Const(32), step=Const(1), par=16).name("x3230").srcCtx("GDA.scala:51:20") } // CounterNew(Const(0),Const(32),Const(1),Const(16))
    val x3231 = withCtrl(x3237) { CounterChain(List(x3230)).name("x3231").srcCtx("GDA.scala:51:20") } // CounterChainNew(List(x3230))
    val x3236 = withCtrl(x3237) { LoopController(style=InnerPipe, level=InnerControl, cchain=x3231).name("x3236").srcCtx("GDA.scala:51:20") } // UnrolledForeach(List(b1908),x3231,Block(Const(())),List(List(b1926)),List(List(b1927)))
    val b1926 = withCtrl(x3236) { CounterIter(x3230, None).name("b1926") } // b1926
    val b1927 = withCtrl(x3236) { Const(true).name("b1927") } // b1927
    val x3232 = withCtrl(x3236) { OpDef(op=BitAnd, inputs=List(b1927, b1908)).name("x3232").srcCtx("UnrollingBase.scala:28:66") } // And(b1927,b1908)
    val x3233_x3233 = withCtrl(x3236) { ReadMem(x3220).name("x3233_x3233").srcCtx("GDA.scala:51:20") } // ParStreamRead(x3220,List(x3232))
    val x3234_x3234 = withCtrl(x3236) { x3233_x3233 } // VectorApply(x3233,0)
    val x3235 = withCtrl(x3236) { StoreBanks(List(List(x3215_d0_b0)), List(b1926), x3234_x3234).name("x3235").srcCtx("GDA.scala:51:20") } // ParSRAMStore(x3215,List(List(b1926)),List(x3234),List(x3232))
    val x3238 = withCtrl(x3265) { Counter(min=Const(0), max=Const(32), step=Const(1), par=1).name("x3238").srcCtx("GDA.scala:52:20") } // CounterNew(Const(0),Const(32),Const(1),Const(1))
    val x3239 = withCtrl(x3265) { CounterChain(List(x3238)).name("x3239").srcCtx("GDA.scala:52:20") } // CounterChainNew(List(x3238))
    val x3264 = withCtrl(x3265) { LoopController(style=StreamPipe, level=OuterControl, cchain=x3239).name("x3264").srcCtx("GDA.scala:52:20") } // UnrolledForeach(List(b1908),x3239,Block(Const(())),List(List(b1936)),List(List(b1937)))
    val b1936 = withCtrl(x3264) { CounterIter(x3238, Some(0)).name("b1936") } // b1936
    val b1937 = withCtrl(x3264) { Const(true).name("b1937") } // b1937
    val b3423 = withCtrl(x3264) { StreamOut(field="offset").name("b3423").srcCtx("GDA.scala:52:20") } // x3240 = StreamOutNew(BurstCmdBus)
    isAccum(b3423) = false
    bufferDepthOf(b3423) = 1
    val b3424 = withCtrl(x3264) { StreamOut(field="size").name("b3424").srcCtx("GDA.scala:52:20") } // x3240 = StreamOutNew(BurstCmdBus)
    isAccum(b3424) = false
    bufferDepthOf(b3424) = 1
    val x3241 = withCtrl(x3264) { StreamIn(field="data").name("x3241").srcCtx("GDA.scala:52:20") } // x3241 = StreamInNew(BurstDataBus())
    isAccum(x3241) = false
    bufferDepthOf(x3241) = 1
    val x3254 = withCtrl(x3264) { UnitController(style=SeqPipe, level=InnerControl).name("x3254").srcCtx("GDA.scala:52:20") } // UnitPipe(List(b1937, b1908),Block(x3253))
    val x3242 = withCtrl(x3254) { OpDef(op=FixAdd, inputs=List(b1907, b1936)).name("x3242").srcCtx("GDA.scala:52:20") } // FixAdd(b1907,b1936)
    val x3243 = withCtrl(x3254) { x3242 } // FixConvert(x3242,TRUE,_32,_0) (Same Type. No op)
    val x3244 = withCtrl(x3254) { OpDef(op=FixSla, inputs=List(x3243, Const(6))).name("x3244").srcCtx("GDA.scala:52:20") } // FixLsh(x3243,Const(6))
    val x3245 = withCtrl(x3254) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x3246 = withCtrl(x3254) { OpDef(op=FixAdd, inputs=List(x3244, x3245)).name("x3246").srcCtx("GDA.scala:52:20") } // FixAdd(x3244,x3245)
    val x3247 = withCtrl(x3254) { OpDef(op=FixSla, inputs=List(x3246, Const(2))).name("x3247").srcCtx("GDA.scala:52:20") } // FixLsh(x3246,Const(2))
    val x3248 = withCtrl(x3254) { x3247 } // FixConvert(x3247,TRUE,_64,_0)
    val x3249 = withCtrl(x3254) { DramAddress(x3162).name("x3249").srcCtx("GDA.scala:52:20") } // GetDRAMAddress(x3162)
    val x3251_x3250 = withCtrl(x3254) { OpDef(op=FixAdd, inputs=List(x3248, x3249)).name("x3251_x3250").srcCtx("GDA.scala:52:20") } // FixAdd(x3248,x3249)
    // x3251 = SimpleStruct(ArrayBuffer((offset,x3250), (size,Const(256)), (isLoad,Const(true))))
    val x3252 = withCtrl(x3254) { OpDef(op=BitAnd, inputs=List(b1937, b1908)).name("x3252").srcCtx("UnrollingBase.scala:28:66") } // And(b1937,b1908)
    val x3253_b3425_b3423 = withCtrl(x3254) { WriteMem(b3423, x3251_x3250).name("x3253_b3425_b3423").srcCtx("GDA.scala:52:20") } // StreamWrite(x3240,x3251,x3252)
    val x3253_b3426_b3424 = withCtrl(x3254) { WriteMem(b3424, Const(256)).name("x3253_b3426_b3424").srcCtx("GDA.scala:52:20") } // StreamWrite(x3240,x3251,x3252)
    val x3255 = withCtrl(x3264) { FringeDenseLoad(dram=List(x3162), cmdStream=List(b3423, b3424), dataStream=List(x3241)).name("x3255").srcCtx("GDA.scala:52:20") } // FringeDenseLoad(x3162,x3240,x3241)
    val x3256 = withCtrl(x3264) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x3256").srcCtx("GDA.scala:52:20") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x3257 = withCtrl(x3264) { CounterChain(List(x3256)).name("x3257").srcCtx("GDA.scala:52:20") } // CounterChainNew(List(x3256))
    val x3263 = withCtrl(x3264) { LoopController(style=InnerPipe, level=InnerControl, cchain=x3257).name("x3263").srcCtx("GDA.scala:52:20") } // UnrolledForeach(List(b1937, b1908),x3257,Block(Const(())),List(List(b1956)),List(List(b1957)))
    val b1956 = withCtrl(x3263) { CounterIter(x3256, None).name("b1956") } // b1956
    val b1957 = withCtrl(x3263) { Const(true).name("b1957") } // b1957
    val x3258 = withCtrl(x3263) { OpDef(op=BitAnd, inputs=List(b1957, b1937)).name("x3258").srcCtx("UnrollingBase.scala:28:66") } // And(b1957,b1937)
    val x3259 = withCtrl(x3263) { OpDef(op=BitAnd, inputs=List(x3258, b1908)).name("x3259").srcCtx("UnrollingBase.scala:28:66") } // And(x3258,b1908)
    val x3260_x3260 = withCtrl(x3263) { ReadMem(x3241).name("x3260_x3260").srcCtx("GDA.scala:52:20") } // ParStreamRead(x3241,List(x3259))
    val x3261_x3261 = withCtrl(x3263) { x3260_x3260 } // VectorApply(x3260,0)
    val x3262 = withCtrl(x3263) { StoreBanks(List(List(x3216_d0_b0)), List(b1936, b1956), x3261_x3261).name("x3262").srcCtx("GDA.scala:52:20") } // ParSRAMStore(x3216,List(List(b1936, b1956)),List(x3261),List(x3259))
    val x3266_d0_b0 = withCtrl(x3330) { SRAM(size=4096, banking=Strided(banks=16, stride=1)).name("x3266_d0_b0").srcCtx("GDA.scala:55:31:sigmaBlk") } // x3266 = SRAMNew(ArrayBuffer(Const(64), Const(64)))
    isAccum(x3266_d0_b0) = false
    bufferDepthOf(x3266_d0_b0) = 2
    staticDimsOf(x3266_d0_b0) = List(64, 64)
    val x3266_d1_b0 = withCtrl(x3330) { SRAM(size=4096, banking=Strided(banks=16, stride=1)).name("x3266_d1_b0").srcCtx("GDA.scala:55:31:sigmaBlk") } // x3266 = SRAMNew(ArrayBuffer(Const(64), Const(64)))
    isAccum(x3266_d1_b0) = true
    bufferDepthOf(x3266_d1_b0) = 1
    staticDimsOf(x3266_d1_b0) = List(64, 64)
    val x3267 = withCtrl(x3330) { Counter(min=Const(0), max=Const(32), step=Const(1), par=1).name("x3267").srcCtx("GDA.scala:57:39") } // CounterNew(Const(0),Const(32),Const(1),Const(1))
    val x3268 = withCtrl(x3330) { CounterChain(List(x3267)).name("x3268").srcCtx("GDA.scala:67:10") } // CounterChainNew(List(x3267))
    val x3315 = withCtrl(x3330) { LoopController(style=MetaPipe, level=OuterControl, cchain=x3268).name("x3315").srcCtx("GDA.scala:67:10") } // UnrolledReduce(List(b1908),x3268,x3266,Block((x3266) => Const(())),List(List(b1972)),List(List(b1973)))
    val b1972 = withCtrl(x3315) { CounterIter(x3267, Some(0)).name("b1972") } // b1972
    val b1973 = withCtrl(x3315) { Const(true).name("b1973") } // b1973
    val x3269_d0_b0 = withCtrl(x3315) { SRAM(size=64, banking=Strided(banks=16, stride=1)).name("x3269_d0_b0").srcCtx("GDA.scala:58:32:subTile") } // x3269 = SRAMNew(ArrayBuffer(Const(64)))
    isAccum(x3269_d0_b0) = false
    bufferDepthOf(x3269_d0_b0) = 2
    staticDimsOf(x3269_d0_b0) = List(64)
    val x3269_d1_b0 = withCtrl(x3315) { SRAM(size=64, banking=Strided(banks=16, stride=1)).name("x3269_d1_b0").srcCtx("GDA.scala:58:32:subTile") } // x3269 = SRAMNew(ArrayBuffer(Const(64)))
    isAccum(x3269_d1_b0) = false
    bufferDepthOf(x3269_d1_b0) = 2
    staticDimsOf(x3269_d1_b0) = List(64)
    val x3270_d0_b0 = withCtrl(x3315) { SRAM(size=4096, banking=Strided(banks=16, stride=1)).name("x3270_d0_b0").srcCtx("GDA.scala:59:34:sigmaTile") } // x3270 = SRAMNew(ArrayBuffer(Const(64), Const(64)))
    isAccum(x3270_d0_b0) = false
    bufferDepthOf(x3270_d0_b0) = 2
    staticDimsOf(x3270_d0_b0) = List(64, 64)
    val x3271 = withCtrl(x3315) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x3271").srcCtx("GDA.scala:60:21") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x3272 = withCtrl(x3315) { CounterChain(List(x3271)).name("x3272").srcCtx("GDA.scala:60:29") } // CounterChainNew(List(x3271))
    val x3286 = withCtrl(x3315) { LoopController(style=InnerPipe, level=InnerControl, cchain=x3272).name("x3286").srcCtx("GDA.scala:60:29") } // UnrolledForeach(List(b1973, b1908),x3272,Block(Const(())),List(List(b1978)),List(List(b1979)))
    val b1978 = withCtrl(x3286) { CounterIter(x3271, None).name("b1978") } // b1978
    val b1979 = withCtrl(x3286) { Const(true).name("b1979") } // b1979
    val x3273 = withCtrl(x3286) { OpDef(op=BitAnd, inputs=List(b1979, b1973)).name("x3273").srcCtx("UnrollingBase.scala:28:66") } // And(b1979,b1973)
    val x3274 = withCtrl(x3286) { OpDef(op=BitAnd, inputs=List(x3273, b1908)).name("x3274").srcCtx("UnrollingBase.scala:28:66") } // And(x3273,b1908)
    val x3275 = withCtrl(x3286) { LoadBanks(List(x3216_d0_b0), List(b1972, b1978)).name("x3275").srcCtx("GDA.scala:61:35") } // ParSRAMLoad(x3216,List(List(b1972, b1978)),List(x3274))
    val x3276 = withCtrl(x3286) { x3275 } // VectorApply(x3275,0)
    val x3277 = withCtrl(x3286) { LoadBanks(List(x3215_d0_b0), List(b1972)).name("x3277").srcCtx("GDA.scala:61:58") } // SRAMLoad(x3215,ArrayBuffer(Const(32)),List(b1972),Const(0),x3274)
    val x3278 = withCtrl(x3286) { OpDef(op=FixEql, inputs=List(x3277, Const(1))).name("x3278").srcCtx("GDA.scala:61:63") } // FixEql(x3277,Const(1))
    val x3279 = withCtrl(x3286) { LoadBanks(List(x3173_d0_b0), List(b1978)).name("x3279").srcCtx("GDA.scala:61:76") } // ParSRAMLoad(x3173,List(List(b1978)),List(x3274))
    val x3280 = withCtrl(x3286) { x3279 } // VectorApply(x3279,0)
    val x3281 = withCtrl(x3286) { LoadBanks(List(x3172_d0_b0), List(b1978)).name("x3281").srcCtx("GDA.scala:61:89") } // ParSRAMLoad(x3172,List(List(b1978)),List(x3274))
    val x3282 = withCtrl(x3286) { x3281 } // VectorApply(x3281,0)
    val x3283 = withCtrl(x3286) { OpDef(op=MuxOp, inputs=List(x3278, x3280, x3282)).name("x3283").srcCtx("GDA.scala:61:49") } // Mux(x3278,x3280,x3282)
    val x3284 = withCtrl(x3286) { OpDef(op=FixSub, inputs=List(x3276, x3283)).name("x3284").srcCtx("GDA.scala:61:44") } // FixSub(x3276,x3283)
    val x3285 = withCtrl(x3286) { StoreBanks(List(List(x3269_d0_b0), List(x3269_d1_b0)), List(b1978), x3284).name("x3285").srcCtx("GDA.scala:61:25") } // ParSRAMStore(x3269,List(List(b1978)),List(x3284),List(x3274))
    val x3287 = withCtrl(x3315) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x3287").srcCtx("GDA.scala:63:29") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x3288 = withCtrl(x3315) { Counter(min=Const(0), max=Const(64), step=Const(1), par=1).name("x3288").srcCtx("GDA.scala:63:21") } // CounterNew(Const(0),Const(64),Const(1),Const(1))
    val x3289 = withCtrl(x3315) { CounterChain(List(x3288,x3287)).name("x3289").srcCtx("GDA.scala:63:37") } // CounterChainNew(List(x3288, x3287))
    val x3298 = withCtrl(x3315) { LoopController(style=InnerPipe, level=InnerControl, cchain=x3289).name("x3298").srcCtx("GDA.scala:63:37") } // UnrolledForeach(List(b1973, b1908),x3289,Block(Const(())),List(List(b1997), List(b1998)),List(List(b1999), List(b2000)))
    val b1997 = withCtrl(x3298) { CounterIter(x3288, Some(0)).name("b1997") } // b1997
    val b1999 = withCtrl(x3298) { Const(true).name("b1999") } // b1999
    val b1998 = withCtrl(x3298) { CounterIter(x3287, None).name("b1998") } // b1998
    val b2000 = withCtrl(x3298) { Const(true).name("b2000") } // b2000
    val x3290 = withCtrl(x3298) { OpDef(op=BitAnd, inputs=List(b1999, b2000)).name("x3290").srcCtx("UnrollingBase.scala:28:66") } // And(b1999,b2000)
    val x3291 = withCtrl(x3298) { OpDef(op=BitAnd, inputs=List(b1973, b1908)).name("x3291").srcCtx("UnrollingBase.scala:28:66") } // And(b1973,b1908)
    val x3292 = withCtrl(x3298) { OpDef(op=BitAnd, inputs=List(x3290, x3291)).name("x3292").srcCtx("UnrollingBase.scala:28:66") } // And(x3290,x3291)
    val x3293 = withCtrl(x3298) { LoadBanks(List(x3269_d1_b0), List(b1997)).name("x3293").srcCtx("GDA.scala:64:40") } // SRAMLoad(x3269,ArrayBuffer(Const(64)),List(b1997),Const(0),x3292)
    val x3294 = withCtrl(x3298) { LoadBanks(List(x3269_d0_b0), List(b1998)).name("x3294").srcCtx("GDA.scala:64:54") } // ParSRAMLoad(x3269,List(List(b1998)),List(x3292))
    val x3295 = withCtrl(x3298) { x3294 } // VectorApply(x3294,0)
    val x3296 = withCtrl(x3298) { OpDef(op=FixMul, inputs=List(x3293, x3295)).name("x3296").srcCtx("GDA.scala:64:45") } // FixMul(x3293,x3295)
    val x3297 = withCtrl(x3298) { StoreBanks(List(List(x3270_d0_b0)), List(b1997, b1998), x3296).name("x3297").srcCtx("GDA.scala:64:31") } // ParSRAMStore(x3270,List(List(b1997, b1998)),List(x3296),List(x3292))
    val x3299 = withCtrl(x3315) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x3299").srcCtx("GDA.scala:67:10") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x3300 = withCtrl(x3315) { Counter(min=Const(0), max=Const(64), step=Const(1), par=1).name("x3300").srcCtx("GDA.scala:67:10") } // CounterNew(Const(0),Const(64),Const(1),Const(1))
    val x3301 = withCtrl(x3315) { CounterChain(List(x3300,x3299)).name("x3301").srcCtx("GDA.scala:67:10") } // CounterChainNew(ArrayBuffer(x3300, x3299))
    val x3314 = withCtrl(x3315) { LoopController(style=InnerPipe, level=InnerControl, cchain=x3301).name("x3314").srcCtx("GDA.scala:67:10") } // UnrolledForeach(List(),x3301,Block(Const(())),ArrayBuffer(List(b2010), List(b2011)),ArrayBuffer(List(b2012), List(b2013)))
    val b2010 = withCtrl(x3314) { CounterIter(x3300, Some(0)).name("b2010") } // b2010
    val b2012 = withCtrl(x3314) { Const(true).name("b2012") } // b2012
    val b2011 = withCtrl(x3314) { CounterIter(x3299, None).name("b2011") } // b2011
    val b2013 = withCtrl(x3314) { Const(true).name("b2013") } // b2013
    val x3302 = withCtrl(x3314) { OpDef(op=BitAnd, inputs=List(b2012, b2013)).name("x3302").srcCtx("UnrollingBase.scala:28:66") } // And(b2012,b2013)
    val x3303 = withCtrl(x3314) { OpDef(op=BitAnd, inputs=List(x3302, b1908)).name("x3303").srcCtx("UnrollingBase.scala:28:66") } // And(x3302,b1908)
    val x3304 = withCtrl(x3314) { LoadBanks(List(x3270_d0_b0), List(b2010, b2011)).name("x3304").srcCtx("GDA.scala:67:10") } // ParSRAMLoad(x3270,List(ArrayBuffer(b2010, b2011)),List(x3303))
    val x3305 = withCtrl(x3314) { x3304 } // VectorApply(x3304,0)
    val x3306 = withCtrl(x3314) { LoadBanks(List(x3266_d1_b0), List(b2010, b2011)).name("x3306").srcCtx("GDA.scala:67:10") } // ParSRAMLoad(x3266,List(ArrayBuffer(b2010, b2011)),List(x3303))
    val x3307 = withCtrl(x3314) { x3306 } // VectorApply(x3306,0)
    val x3308 = withCtrl(x3314) { OpDef(op=BitAnd, inputs=List(b1973, b1908)).name("x3308").srcCtx("GDA.scala:67:10") } // And(b1973,b1908)
    val x3309 = withCtrl(x3314) { OpDef(op=BitAnd, inputs=List(x3308, x3303)).name("x3309").srcCtx("GDA.scala:67:10") } // And(x3308,x3303)
    val x3310 = withCtrl(x3314) { OpDef(op=FixEql, inputs=List(b1972, Const(0))).name("x3310").srcCtx("GDA.scala:67:10") } // FixEql(b1972,Const(0))
    val x3311 = withCtrl(x3314) { OpDef(op=FixAdd, inputs=List(x3305, x3307)).name("x3311").srcCtx("GDA.scala:67:12") } // FixAdd(x3305,x3307)
    val x3312 = withCtrl(x3314) { OpDef(op=MuxOp, inputs=List(x3310, x3305, x3311)).name("x3312").srcCtx("GDA.scala:67:10") } // Mux(x3310,x3305,x3311)
    val x3313 = withCtrl(x3314) { StoreBanks(List(List(x3266_d0_b0), List(x3266_d1_b0)), List(b2010, b2011), x3312).name("x3313").srcCtx("GDA.scala:67:10") } // ParSRAMStore(x3266,List(ArrayBuffer(b2010, b2011)),List(x3312),List(x3303))
    antiDepsOf(x3313)=List(x3306)
    val x3316 = withCtrl(x3330) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x3316").srcCtx("GDA.scala:68:8") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x3317 = withCtrl(x3330) { Counter(min=Const(0), max=Const(64), step=Const(1), par=1).name("x3317").srcCtx("GDA.scala:68:8") } // CounterNew(Const(0),Const(64),Const(1),Const(1))
    val x3318 = withCtrl(x3330) { CounterChain(List(x3317,x3316)).name("x3318").srcCtx("GDA.scala:68:8") } // CounterChainNew(ArrayBuffer(x3317, x3316))
    val x3329 = withCtrl(x3330) { LoopController(style=InnerPipe, level=InnerControl, cchain=x3318).name("x3329").srcCtx("GDA.scala:68:8") } // UnrolledForeach(List(),x3318,Block(Const(())),ArrayBuffer(List(b2028), List(b2029)),ArrayBuffer(List(b2030), List(b2031)))
    val b2028 = withCtrl(x3329) { CounterIter(x3317, Some(0)).name("b2028") } // b2028
    val b2030 = withCtrl(x3329) { Const(true).name("b2030") } // b2030
    val b2029 = withCtrl(x3329) { CounterIter(x3316, None).name("b2029") } // b2029
    val b2031 = withCtrl(x3329) { Const(true).name("b2031") } // b2031
    val x3319 = withCtrl(x3329) { OpDef(op=BitAnd, inputs=List(b2030, b2031)).name("x3319").srcCtx("UnrollingBase.scala:28:66") } // And(b2030,b2031)
    val x3320 = withCtrl(x3329) { LoadBanks(List(x3266_d0_b0), List(b2028, b2029)).name("x3320").srcCtx("GDA.scala:68:8") } // ParSRAMLoad(x3266,List(ArrayBuffer(b2028, b2029)),List(x3319))
    val x3321 = withCtrl(x3329) { x3320 } // VectorApply(x3320,0)
    val x3322 = withCtrl(x3329) { LoadBanks(List(x3211_d1_b0), List(b2028, b2029)).name("x3322").srcCtx("GDA.scala:68:8") } // ParSRAMLoad(x3211,List(ArrayBuffer(b2028, b2029)),List(x3319))
    val x3323 = withCtrl(x3329) { x3322 } // VectorApply(x3322,0)
    val x3324 = withCtrl(x3329) { OpDef(op=BitAnd, inputs=List(b1908, x3319)).name("x3324").srcCtx("GDA.scala:68:8") } // And(b1908,x3319)
    val x3325 = withCtrl(x3329) { OpDef(op=FixEql, inputs=List(b1907, Const(0))).name("x3325").srcCtx("GDA.scala:68:8") } // FixEql(b1907,Const(0))
    val x3326 = withCtrl(x3329) { OpDef(op=FixAdd, inputs=List(x3321, x3323)).name("x3326").srcCtx("GDA.scala:68:10") } // FixAdd(x3321,x3323)
    val x3327 = withCtrl(x3329) { OpDef(op=MuxOp, inputs=List(x3325, x3321, x3326)).name("x3327").srcCtx("GDA.scala:68:8") } // Mux(x3325,x3321,x3326)
    val x3328 = withCtrl(x3329) { StoreBanks(List(List(x3211_d0_b0), List(x3211_d1_b0)), List(b2028, b2029), x3327).name("x3328").srcCtx("GDA.scala:68:8") } // ParSRAMStore(x3211,List(ArrayBuffer(b2028, b2029)),List(x3327),List(x3319))
    antiDepsOf(x3328)=List(x3322)
    val x3331 = withCtrl(x3359) { Counter(min=Const(0), max=Const(64), step=Const(1), par=1).name("x3331").srcCtx("GDA.scala:70:36") } // CounterNew(Const(0),Const(64),Const(1),Const(1))
    val x3332 = withCtrl(x3359) { CounterChain(List(x3331)).name("x3332").srcCtx("GDA.scala:70:36") } // CounterChainNew(List(x3331))
    val x3358 = withCtrl(x3359) { LoopController(style=StreamPipe, level=OuterControl, cchain=x3332).name("x3358").srcCtx("GDA.scala:70:36") } // UnrolledForeach(List(Const(true)),x3332,Block(Const(())),List(List(b2046)),List(List(b2047)))
    val b2046 = withCtrl(x3358) { CounterIter(x3331, Some(0)).name("b2046") } // b2046
    val b2047 = withCtrl(x3358) { Const(true).name("b2047") } // b2047
    val b3427 = withCtrl(x3358) { StreamOut(field="offset").name("b3427").srcCtx("GDA.scala:70:36") } // x3333 = StreamOutNew(BurstCmdBus)
    isAccum(b3427) = false
    bufferDepthOf(b3427) = 1
    val b3428 = withCtrl(x3358) { StreamOut(field="size").name("b3428").srcCtx("GDA.scala:70:36") } // x3333 = StreamOutNew(BurstCmdBus)
    isAccum(b3428) = false
    bufferDepthOf(b3428) = 1
    val x3334 = withCtrl(x3358) { StreamOut(field="data").name("x3334").srcCtx("GDA.scala:70:36") } // x3334 = StreamOutNew(BurstFullDataBus())
    isAccum(x3334) = false
    bufferDepthOf(x3334) = 1
    val x3335 = withCtrl(x3358) { StreamIn(field="ack").name("x3335").srcCtx("GDA.scala:70:36") } // x3335 = StreamInNew(BurstAckBus)
    isAccum(x3335) = false
    bufferDepthOf(x3335) = 1
    val x3346 = withCtrl(x3358) { UnitController(style=SeqPipe, level=InnerControl).name("x3346").srcCtx("GDA.scala:70:36") } // UnitPipe(List(b2047),Block(x3345))
    val x3336 = withCtrl(x3346) { b2046 } // FixConvert(b2046,TRUE,_32,_0) (Same Type. No op)
    val x3337 = withCtrl(x3346) { OpDef(op=FixSla, inputs=List(x3336, Const(6))).name("x3337").srcCtx("GDA.scala:70:36") } // FixLsh(x3336,Const(6))
    val x3338 = withCtrl(x3346) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x3339 = withCtrl(x3346) { OpDef(op=FixAdd, inputs=List(x3337, x3338)).name("x3339").srcCtx("GDA.scala:70:36") } // FixAdd(x3337,x3338)
    val x3340 = withCtrl(x3346) { OpDef(op=FixSla, inputs=List(x3339, Const(2))).name("x3340").srcCtx("GDA.scala:70:36") } // FixLsh(x3339,Const(2))
    val x3341 = withCtrl(x3346) { x3340 } // FixConvert(x3340,TRUE,_64,_0)
    val x3342 = withCtrl(x3346) { DramAddress(x3167).name("x3342").srcCtx("GDA.scala:70:36") } // GetDRAMAddress(x3167)
    val x3344_x3343 = withCtrl(x3346) { OpDef(op=FixAdd, inputs=List(x3341, x3342)).name("x3344_x3343").srcCtx("GDA.scala:70:36") } // FixAdd(x3341,x3342)
    // x3344 = SimpleStruct(ArrayBuffer((offset,x3343), (size,Const(256)), (isLoad,Const(false))))
    val x3345_b3429_b3427 = withCtrl(x3346) { WriteMem(b3427, x3344_x3343).name("x3345_b3429_b3427").srcCtx("GDA.scala:70:36") } // StreamWrite(x3333,x3344,b2047)
    val x3345_b3430_b3428 = withCtrl(x3346) { WriteMem(b3428, Const(256)).name("x3345_b3430_b3428").srcCtx("GDA.scala:70:36") } // StreamWrite(x3333,x3344,b2047)
    val x3347 = withCtrl(x3358) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x3347").srcCtx("GDA.scala:70:36") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x3348 = withCtrl(x3358) { CounterChain(List(x3347)).name("x3348").srcCtx("GDA.scala:70:36") } // CounterChainNew(List(x3347))
    val x3354 = withCtrl(x3358) { LoopController(style=InnerPipe, level=InnerControl, cchain=x3348).name("x3354").srcCtx("GDA.scala:70:36") } // UnrolledForeach(List(b2047),x3348,Block(Const(())),List(List(b2064)),List(List(b2065)))
    val b2064 = withCtrl(x3354) { CounterIter(x3347, None).name("b2064") } // b2064
    val b2065 = withCtrl(x3354) { Const(true).name("b2065") } // b2065
    val x3349 = withCtrl(x3354) { OpDef(op=BitAnd, inputs=List(b2065, b2047)).name("x3349").srcCtx("UnrollingBase.scala:28:66") } // And(b2065,b2047)
    val x3350 = withCtrl(x3354) { LoadBanks(List(x3211_d0_b0), List(b2046, b2064)).name("x3350").srcCtx("GDA.scala:70:36") } // ParSRAMLoad(x3211,List(List(b2046, b2064)),List(x3349))
    val x3352_x3351 = withCtrl(x3354) { x3350 } // VectorApply(x3350,0)
    // x3352 = SimpleStruct(ArrayBuffer((_1,x3351), (_2,Const(true))))
    val x3353_x3353_x3334 = withCtrl(x3354) { WriteMem(x3334, x3352_x3351).name("x3353_x3353_x3334").srcCtx("GDA.scala:70:36") } // ParStreamWrite(x3334,List(x3352),List(x3349))
    val x3355 = withCtrl(x3358) { FringeDenseStore(dram=List(x3167), cmdStream=List(b3427, b3428), dataStream=List(x3334), ackStream=List(x3335)).name("x3355").srcCtx("GDA.scala:70:36") } // FringeDenseStore(x3167,x3333,x3334,x3335)
    val x3357 = withCtrl(x3358) { UnitController(style=SeqPipe, level=InnerControl).name("x3357").srcCtx("GDA.scala:70:36") } // UnitPipe(List(b2047),Block(Const(())))
    val x3356_x3356 = withCtrl(x3357) { ReadMem(x3335).name("x3356_x3356").srcCtx("GDA.scala:70:36") } // StreamRead(x3335,b2047)
    
  }
}
