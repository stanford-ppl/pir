import pir._
import pir.node._
import arch._
import prism.enums._

object GDA extends PIRApp {
  def main(implicit design:PIRDesign) = {
    import design.pirmeta._
    val x3159 = ArgIn(init=0).name("x3159").ctrl(top).srcCtx("GDA.scala:22:18:R") // ArgInNew(Const(0))
    isAccum(x3159) = false
    bufferDepthOf(x3159) = 1
    boundOf(x3159) = 1024
    val x3161 = ReadMem(x3159).name("x3161").ctrl(top).srcCtx("GDA.scala:25:21") // RegRead(x3159)
    val x3162 = DRAM(dims=List(x3161, Const(128))).name("x3162").ctrl(top).srcCtx("GDA.scala:25:20:x") // x3162 = DRAMNew(ArrayBuffer(x3161, Const(128)),Const(0))
    val x3163 = ReadMem(x3159).name("x3163").ctrl(top).srcCtx("GDA.scala:26:23") // RegRead(x3159)
    val x3164 = DRAM(dims=List(x3163)).name("x3164").ctrl(top).srcCtx("GDA.scala:26:22:y") // x3164 = DRAMNew(ArrayBuffer(x3163),Const(0))
    val x3165 = DRAM(dims=List(Const(128))).name("x3165").ctrl(top).srcCtx("GDA.scala:27:22:mu0") // x3165 = DRAMNew(ArrayBuffer(Const(128)),Const(0))
    val x3166 = DRAM(dims=List(Const(128))).name("x3166").ctrl(top).srcCtx("GDA.scala:28:22:mu1") // x3166 = DRAMNew(ArrayBuffer(Const(128)),Const(0))
    val x3167 = DRAM(dims=List(Const(128), Const(128))).name("x3167").ctrl(top).srcCtx("GDA.scala:29:24:sigma") // x3167 = DRAMNew(ArrayBuffer(Const(128), Const(128)),Const(0))
    val x3359 = UnitController(style=SeqPipe, level=OuterControl).name("x3359").ctrl(top).srcCtx("GDA.scala:36:11") // Hwblock(Block(Const(())),false)
    val x3172_d0_b0 = SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x3172_d0_b0").ctrl(x3359).srcCtx("GDA.scala:37:28:mu0Tile") // x3172 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x3172_d0_b0) = false
    bufferDepthOf(x3172_d0_b0) = 1
    staticDimsOf(x3172_d0_b0) = List(128)
    val x3173_d0_b0 = SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x3173_d0_b0").ctrl(x3359).srcCtx("GDA.scala:38:28:mu1Tile") // x3173 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x3173_d0_b0) = false
    bufferDepthOf(x3173_d0_b0) = 1
    staticDimsOf(x3173_d0_b0) = List(128)
    val x3210 = UnitController(style=ForkJoin, level=OuterControl).name("x3210").ctrl(x3359).srcCtx("GDA.scala:39:16") // ParallelPipe(List(Const(true)),Block(Const(())))
    val x3191 = UnitController(style=StreamPipe, level=OuterControl).name("x3191").ctrl(x3210).srcCtx("GDA.scala:40:17") // UnitPipe(List(Const(true)),Block(Const(())))
    val b3411 = StreamOut(field="offset").name("b3411").ctrl(x3191).srcCtx("GDA.scala:40:17") // x3174 = StreamOutNew(BurstCmdBus)
    isAccum(b3411) = false
    bufferDepthOf(b3411) = 1
    val b3412 = StreamOut(field="size").name("b3412").ctrl(x3191).srcCtx("GDA.scala:40:17") // x3174 = StreamOutNew(BurstCmdBus)
    isAccum(b3412) = false
    bufferDepthOf(b3412) = 1
    val x3175 = StreamIn(field="data").name("x3175").ctrl(x3191).srcCtx("GDA.scala:40:17") // x3175 = StreamInNew(BurstDataBus())
    isAccum(x3175) = false
    bufferDepthOf(x3175) = 1
    val x3183 = UnitController(style=SeqPipe, level=InnerControl).name("x3183").ctrl(x3191).srcCtx("GDA.scala:40:17") // UnitPipe(List(Const(true)),Block(x3182))
    val x3176 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x3177 = OpDef(op=FixSla, inputs=List(x3176, Const(2))).name("x3177").ctrl(x3183).srcCtx("GDA.scala:40:17") // FixLsh(x3176,Const(2))
    val x3178 = x3177 // FixConvert(x3177,TRUE,_64,_0)
    val x3179 = DramAddress(x3165).name("x3179").ctrl(x3183).srcCtx("GDA.scala:40:17") // GetDRAMAddress(x3165)
    val x3181_x3180 = OpDef(op=FixAdd, inputs=List(x3178, x3179)).name("x3181_x3180").ctrl(x3183).srcCtx("GDA.scala:40:17") // FixAdd(x3178,x3179)
    // x3181 = SimpleStruct(ArrayBuffer((offset,x3180), (size,Const(512)), (isLoad,Const(true))))
    val x3182_b3413_b3411 = WriteMem(b3411, x3181_x3180).name("x3182_b3413_b3411").ctrl(x3183).srcCtx("GDA.scala:40:17") // StreamWrite(x3174,x3181,Const(true))
    val x3182_b3414_b3412 = WriteMem(b3412, Const(512)).name("x3182_b3414_b3412").ctrl(x3183).srcCtx("GDA.scala:40:17") // StreamWrite(x3174,x3181,Const(true))
    val x3184 = FringeDenseLoad(dram=List(x3165), cmdStream=List(b3411, b3412), dataStream=List(x3175)).name("x3184").ctrl(x3191).srcCtx("GDA.scala:40:17") // FringeDenseLoad(x3165,x3174,x3175)
    val x3185 = Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x3185").ctrl(x3191).srcCtx("GDA.scala:40:17") // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x3186 = CounterChain(List(x3185)).name("x3186").ctrl(x3191).srcCtx("GDA.scala:40:17") // CounterChainNew(List(x3185))
    val x3190 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3186).name("x3190").ctrl(x3191).srcCtx("GDA.scala:40:17") // UnrolledForeach(List(Const(true)),x3186,Block(Const(())),List(List(b1872)),List(List(b1873)))
    val b1872 = CounterIter(x3185, None).name("b1872").ctrl(x3190) // b1872
    val b1873 = Const(true).name("b1873").ctrl(x3190) // b1873
    val x3187_x3187 = ReadMem(x3175).name("x3187_x3187").ctrl(x3190).srcCtx("GDA.scala:40:17") // ParStreamRead(x3175,List(b1873))
    val x3188_x3188 = x3187_x3187 // x3188 = VectorApply(x3187,0)
    val x3189 = StoreBanks(List(x3172_d0_b0), List(b1872), x3188_x3188).name("x3189").ctrl(x3190).srcCtx("GDA.scala:40:17") // ParSRAMStore(x3172,List(List(b1872)),List(x3188),List(b1873))
    val x3209 = UnitController(style=StreamPipe, level=OuterControl).name("x3209").ctrl(x3210).srcCtx("GDA.scala:41:17") // UnitPipe(List(Const(true)),Block(Const(())))
    val b3415 = StreamOut(field="offset").name("b3415").ctrl(x3209).srcCtx("GDA.scala:41:17") // x3192 = StreamOutNew(BurstCmdBus)
    isAccum(b3415) = false
    bufferDepthOf(b3415) = 1
    val b3416 = StreamOut(field="size").name("b3416").ctrl(x3209).srcCtx("GDA.scala:41:17") // x3192 = StreamOutNew(BurstCmdBus)
    isAccum(b3416) = false
    bufferDepthOf(b3416) = 1
    val x3193 = StreamIn(field="data").name("x3193").ctrl(x3209).srcCtx("GDA.scala:41:17") // x3193 = StreamInNew(BurstDataBus())
    isAccum(x3193) = false
    bufferDepthOf(x3193) = 1
    val x3201 = UnitController(style=SeqPipe, level=InnerControl).name("x3201").ctrl(x3209).srcCtx("GDA.scala:41:17") // UnitPipe(List(Const(true)),Block(x3200))
    val x3194 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x3195 = OpDef(op=FixSla, inputs=List(x3194, Const(2))).name("x3195").ctrl(x3201).srcCtx("GDA.scala:41:17") // FixLsh(x3194,Const(2))
    val x3196 = x3195 // FixConvert(x3195,TRUE,_64,_0)
    val x3197 = DramAddress(x3166).name("x3197").ctrl(x3201).srcCtx("GDA.scala:41:17") // GetDRAMAddress(x3166)
    val x3199_x3198 = OpDef(op=FixAdd, inputs=List(x3196, x3197)).name("x3199_x3198").ctrl(x3201).srcCtx("GDA.scala:41:17") // FixAdd(x3196,x3197)
    // x3199 = SimpleStruct(ArrayBuffer((offset,x3198), (size,Const(512)), (isLoad,Const(true))))
    val x3200_b3417_b3415 = WriteMem(b3415, x3199_x3198).name("x3200_b3417_b3415").ctrl(x3201).srcCtx("GDA.scala:41:17") // StreamWrite(x3192,x3199,Const(true))
    val x3200_b3418_b3416 = WriteMem(b3416, Const(512)).name("x3200_b3418_b3416").ctrl(x3201).srcCtx("GDA.scala:41:17") // StreamWrite(x3192,x3199,Const(true))
    val x3202 = FringeDenseLoad(dram=List(x3166), cmdStream=List(b3415, b3416), dataStream=List(x3193)).name("x3202").ctrl(x3209).srcCtx("GDA.scala:41:17") // FringeDenseLoad(x3166,x3192,x3193)
    val x3203 = Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x3203").ctrl(x3209).srcCtx("GDA.scala:41:17") // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x3204 = CounterChain(List(x3203)).name("x3204").ctrl(x3209).srcCtx("GDA.scala:41:17") // CounterChainNew(List(x3203))
    val x3208 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3204).name("x3208").ctrl(x3209).srcCtx("GDA.scala:41:17") // UnrolledForeach(List(Const(true)),x3204,Block(Const(())),List(List(b1892)),List(List(b1893)))
    val b1892 = CounterIter(x3203, None).name("b1892").ctrl(x3208) // b1892
    val b1893 = Const(true).name("b1893").ctrl(x3208) // b1893
    val x3205_x3205 = ReadMem(x3193).name("x3205_x3205").ctrl(x3208).srcCtx("GDA.scala:41:17") // ParStreamRead(x3193,List(b1893))
    val x3206_x3206 = x3205_x3205 // x3206 = VectorApply(x3205,0)
    val x3207 = StoreBanks(List(x3173_d0_b0), List(b1892), x3206_x3206).name("x3207").ctrl(x3208).srcCtx("GDA.scala:41:17") // ParSRAMStore(x3173,List(List(b1892)),List(x3206),List(b1893))
    val x3211_d0_b0 = SRAM(size=16384, banking=Strided(banks=16, stride=1)).name("x3211_d0_b0").ctrl(x3359).srcCtx("GDA.scala:44:29:sigmaOut") // x3211 = SRAMNew(ArrayBuffer(Const(128), Const(128)))
    isAccum(x3211_d0_b0) = false
    bufferDepthOf(x3211_d0_b0) = 1
    staticDimsOf(x3211_d0_b0) = List(128, 128)
    val x3211_d1_b0 = SRAM(size=16384, banking=Strided(banks=1, stride=128)).name("x3211_d1_b0").ctrl(x3359).srcCtx("GDA.scala:44:29:sigmaOut") // x3211 = SRAMNew(ArrayBuffer(Const(128), Const(128)))
    isAccum(x3211_d1_b0) = true
    bufferDepthOf(x3211_d1_b0) = 1
    staticDimsOf(x3211_d1_b0) = List(128, 128)
    val x3212 = ReadMem(x3159).name("x3212").ctrl(x3359).srcCtx("GDA.scala:46:27") // RegRead(x3159)
    val x3213 = Counter(min=Const(0), max=x3212, step=Const(32), par=1).name("x3213").ctrl(x3359).srcCtx("GDA.scala:46:35") // CounterNew(Const(0),x3212,Const(32),Const(1))
    val x3214 = CounterChain(List(x3213)).name("x3214").ctrl(x3359).srcCtx("GDA.scala:68:8") // CounterChainNew(List(x3213))
    val x3330 = LoopController(style=MetaPipe, level=OuterControl, cchain=x3214).name("x3330").ctrl(x3359).srcCtx("GDA.scala:68:8") // UnrolledReduce(List(Const(true)),x3214,x3211,Block((x3211) => Const(())),List(List(b1907)),List(List(b1908)))
    val b1907 = CounterIter(x3213, Some(0)).name("b1907").ctrl(x3330) // b1907
    val b1908 = Const(true).name("b1908").ctrl(x3330) // b1908
    val x3215_d0_b0 = SRAM(size=32, banking=Strided(banks=16, stride=1)).name("x3215_d0_b0").ctrl(x3330).srcCtx("GDA.scala:47:33:gdaYtile") // x3215 = SRAMNew(ArrayBuffer(Const(32)))
    isAccum(x3215_d0_b0) = false
    bufferDepthOf(x3215_d0_b0) = 2
    staticDimsOf(x3215_d0_b0) = List(32)
    val x3216_d0_b0 = SRAM(size=4096, banking=Strided(banks=16, stride=1)).name("x3216_d0_b0").ctrl(x3330).srcCtx("GDA.scala:48:31:gdaXtile") // x3216 = SRAMNew(ArrayBuffer(Const(32), Const(128)))
    isAccum(x3216_d0_b0) = false
    bufferDepthOf(x3216_d0_b0) = 2
    staticDimsOf(x3216_d0_b0) = List(32, 128)
    val x3265 = UnitController(style=ForkJoin, level=OuterControl).name("x3265").ctrl(x3330).srcCtx("GDA.scala:50:18") // ParallelPipe(List(b1908),Block(Const(())))
    val x3218 = UnitController(style=SeqPipe, level=InnerControl).name("x3218").ctrl(x3265).srcCtx("GDA.scala:50:18") // UnitPipe(List(b1908),Block(Const(())))
    val x3217 = OpDef(op=FixAdd, inputs=List(b1907, Const(32))).name("x3217").ctrl(x3218).srcCtx("GDA.scala:51:34") // FixAdd(b1907,Const(32))
    val x3237 = UnitController(style=StreamPipe, level=OuterControl).name("x3237").ctrl(x3265).srcCtx("GDA.scala:51:20") // UnitPipe(List(b1908),Block(Const(())))
    val b3419 = StreamOut(field="offset").name("b3419").ctrl(x3237).srcCtx("GDA.scala:51:20") // x3219 = StreamOutNew(BurstCmdBus)
    isAccum(b3419) = false
    bufferDepthOf(b3419) = 1
    val b3420 = StreamOut(field="size").name("b3420").ctrl(x3237).srcCtx("GDA.scala:51:20") // x3219 = StreamOutNew(BurstCmdBus)
    isAccum(b3420) = false
    bufferDepthOf(b3420) = 1
    val x3220 = StreamIn(field="data").name("x3220").ctrl(x3237).srcCtx("GDA.scala:51:20") // x3220 = StreamInNew(BurstDataBus())
    isAccum(x3220) = false
    bufferDepthOf(x3220) = 1
    val x3228 = UnitController(style=SeqPipe, level=InnerControl).name("x3228").ctrl(x3237).srcCtx("GDA.scala:51:20") // UnitPipe(List(b1908),Block(x3227))
    val x3221 = b1907 // FixConvert(b1907,TRUE,_32,_0) (Same Type. No op)
    val x3222 = OpDef(op=FixSla, inputs=List(x3221, Const(2))).name("x3222").ctrl(x3228).srcCtx("GDA.scala:51:20") // FixLsh(x3221,Const(2))
    val x3223 = x3222 // FixConvert(x3222,TRUE,_64,_0)
    val x3224 = DramAddress(x3164).name("x3224").ctrl(x3228).srcCtx("GDA.scala:51:20") // GetDRAMAddress(x3164)
    val x3226_x3225 = OpDef(op=FixAdd, inputs=List(x3223, x3224)).name("x3226_x3225").ctrl(x3228).srcCtx("GDA.scala:51:20") // FixAdd(x3223,x3224)
    // x3226 = SimpleStruct(ArrayBuffer((offset,x3225), (size,Const(128)), (isLoad,Const(true))))
    val x3227_b3421_b3419 = WriteMem(b3419, x3226_x3225).name("x3227_b3421_b3419").ctrl(x3228).srcCtx("GDA.scala:51:20") // StreamWrite(x3219,x3226,b1908)
    val x3227_b3422_b3420 = WriteMem(b3420, Const(128)).name("x3227_b3422_b3420").ctrl(x3228).srcCtx("GDA.scala:51:20") // StreamWrite(x3219,x3226,b1908)
    val x3229 = FringeDenseLoad(dram=List(x3164), cmdStream=List(b3419, b3420), dataStream=List(x3220)).name("x3229").ctrl(x3237).srcCtx("GDA.scala:51:20") // FringeDenseLoad(x3164,x3219,x3220)
    val x3230 = Counter(min=Const(0), max=Const(32), step=Const(1), par=16).name("x3230").ctrl(x3237).srcCtx("GDA.scala:51:20") // CounterNew(Const(0),Const(32),Const(1),Const(16))
    val x3231 = CounterChain(List(x3230)).name("x3231").ctrl(x3237).srcCtx("GDA.scala:51:20") // CounterChainNew(List(x3230))
    val x3236 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3231).name("x3236").ctrl(x3237).srcCtx("GDA.scala:51:20") // UnrolledForeach(List(b1908),x3231,Block(Const(())),List(List(b1926)),List(List(b1927)))
    val b1926 = CounterIter(x3230, None).name("b1926").ctrl(x3236) // b1926
    val b1927 = Const(true).name("b1927").ctrl(x3236) // b1927
    val x3232 = OpDef(op=BitAnd, inputs=List(b1927, b1908)).name("x3232").ctrl(x3236).srcCtx("UnrollingBase.scala:28:66") // And(b1927,b1908)
    val x3233_x3233 = ReadMem(x3220).name("x3233_x3233").ctrl(x3236).srcCtx("GDA.scala:51:20") // ParStreamRead(x3220,List(x3232))
    val x3234_x3234 = x3233_x3233 // x3234 = VectorApply(x3233,0)
    val x3235 = StoreBanks(List(x3215_d0_b0), List(b1926), x3234_x3234).name("x3235").ctrl(x3236).srcCtx("GDA.scala:51:20") // ParSRAMStore(x3215,List(List(b1926)),List(x3234),List(x3232))
    val x3238 = Counter(min=Const(0), max=Const(32), step=Const(1), par=1).name("x3238").ctrl(x3265).srcCtx("GDA.scala:52:20") // CounterNew(Const(0),Const(32),Const(1),Const(1))
    val x3239 = CounterChain(List(x3238)).name("x3239").ctrl(x3265).srcCtx("GDA.scala:52:20") // CounterChainNew(List(x3238))
    val x3264 = LoopController(style=StreamPipe, level=OuterControl, cchain=x3239).name("x3264").ctrl(x3265).srcCtx("GDA.scala:52:20") // UnrolledForeach(List(b1908),x3239,Block(Const(())),List(List(b1936)),List(List(b1937)))
    val b1936 = CounterIter(x3238, Some(0)).name("b1936").ctrl(x3264) // b1936
    val b1937 = Const(true).name("b1937").ctrl(x3264) // b1937
    val b3423 = StreamOut(field="offset").name("b3423").ctrl(x3264).srcCtx("GDA.scala:52:20") // x3240 = StreamOutNew(BurstCmdBus)
    isAccum(b3423) = false
    bufferDepthOf(b3423) = 1
    val b3424 = StreamOut(field="size").name("b3424").ctrl(x3264).srcCtx("GDA.scala:52:20") // x3240 = StreamOutNew(BurstCmdBus)
    isAccum(b3424) = false
    bufferDepthOf(b3424) = 1
    val x3241 = StreamIn(field="data").name("x3241").ctrl(x3264).srcCtx("GDA.scala:52:20") // x3241 = StreamInNew(BurstDataBus())
    isAccum(x3241) = false
    bufferDepthOf(x3241) = 1
    val x3254 = UnitController(style=SeqPipe, level=InnerControl).name("x3254").ctrl(x3264).srcCtx("GDA.scala:52:20") // UnitPipe(List(b1937, b1908),Block(x3253))
    val x3242 = OpDef(op=FixAdd, inputs=List(b1907, b1936)).name("x3242").ctrl(x3254).srcCtx("GDA.scala:52:20") // FixAdd(b1907,b1936)
    val x3243 = x3242 // FixConvert(x3242,TRUE,_32,_0) (Same Type. No op)
    val x3244 = OpDef(op=FixSla, inputs=List(x3243, Const(7))).name("x3244").ctrl(x3254).srcCtx("GDA.scala:52:20") // FixLsh(x3243,Const(7))
    val x3245 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x3246 = OpDef(op=FixAdd, inputs=List(x3244, x3245)).name("x3246").ctrl(x3254).srcCtx("GDA.scala:52:20") // FixAdd(x3244,x3245)
    val x3247 = OpDef(op=FixSla, inputs=List(x3246, Const(2))).name("x3247").ctrl(x3254).srcCtx("GDA.scala:52:20") // FixLsh(x3246,Const(2))
    val x3248 = x3247 // FixConvert(x3247,TRUE,_64,_0)
    val x3249 = DramAddress(x3162).name("x3249").ctrl(x3254).srcCtx("GDA.scala:52:20") // GetDRAMAddress(x3162)
    val x3251_x3250 = OpDef(op=FixAdd, inputs=List(x3248, x3249)).name("x3251_x3250").ctrl(x3254).srcCtx("GDA.scala:52:20") // FixAdd(x3248,x3249)
    // x3251 = SimpleStruct(ArrayBuffer((offset,x3250), (size,Const(512)), (isLoad,Const(true))))
    val x3252 = OpDef(op=BitAnd, inputs=List(b1937, b1908)).name("x3252").ctrl(x3254).srcCtx("UnrollingBase.scala:28:66") // And(b1937,b1908)
    val x3253_b3425_b3423 = WriteMem(b3423, x3251_x3250).name("x3253_b3425_b3423").ctrl(x3254).srcCtx("GDA.scala:52:20") // StreamWrite(x3240,x3251,x3252)
    val x3253_b3426_b3424 = WriteMem(b3424, Const(512)).name("x3253_b3426_b3424").ctrl(x3254).srcCtx("GDA.scala:52:20") // StreamWrite(x3240,x3251,x3252)
    val x3255 = FringeDenseLoad(dram=List(x3162), cmdStream=List(b3423, b3424), dataStream=List(x3241)).name("x3255").ctrl(x3264).srcCtx("GDA.scala:52:20") // FringeDenseLoad(x3162,x3240,x3241)
    val x3256 = Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x3256").ctrl(x3264).srcCtx("GDA.scala:52:20") // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x3257 = CounterChain(List(x3256)).name("x3257").ctrl(x3264).srcCtx("GDA.scala:52:20") // CounterChainNew(List(x3256))
    val x3263 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3257).name("x3263").ctrl(x3264).srcCtx("GDA.scala:52:20") // UnrolledForeach(List(b1937, b1908),x3257,Block(Const(())),List(List(b1956)),List(List(b1957)))
    val b1956 = CounterIter(x3256, None).name("b1956").ctrl(x3263) // b1956
    val b1957 = Const(true).name("b1957").ctrl(x3263) // b1957
    val x3258 = OpDef(op=BitAnd, inputs=List(b1957, b1937)).name("x3258").ctrl(x3263).srcCtx("UnrollingBase.scala:28:66") // And(b1957,b1937)
    val x3259 = OpDef(op=BitAnd, inputs=List(x3258, b1908)).name("x3259").ctrl(x3263).srcCtx("UnrollingBase.scala:28:66") // And(x3258,b1908)
    val x3260_x3260 = ReadMem(x3241).name("x3260_x3260").ctrl(x3263).srcCtx("GDA.scala:52:20") // ParStreamRead(x3241,List(x3259))
    val x3261_x3261 = x3260_x3260 // x3261 = VectorApply(x3260,0)
    val x3262 = StoreBanks(List(x3216_d0_b0), List(b1936, b1956), x3261_x3261).name("x3262").ctrl(x3263).srcCtx("GDA.scala:52:20") // ParSRAMStore(x3216,List(List(b1936, b1956)),List(x3261),List(x3259))
    val x3266_d0_b0 = SRAM(size=16384, banking=Strided(banks=1, stride=128)).name("x3266_d0_b0").ctrl(x3330).srcCtx("GDA.scala:55:31:sigmaBlk") // x3266 = SRAMNew(ArrayBuffer(Const(128), Const(128)))
    isAccum(x3266_d0_b0) = false
    bufferDepthOf(x3266_d0_b0) = 2
    staticDimsOf(x3266_d0_b0) = List(128, 128)
    val x3266_d1_b0 = SRAM(size=16384, banking=Strided(banks=1, stride=128)).name("x3266_d1_b0").ctrl(x3330).srcCtx("GDA.scala:55:31:sigmaBlk") // x3266 = SRAMNew(ArrayBuffer(Const(128), Const(128)))
    isAccum(x3266_d1_b0) = true
    bufferDepthOf(x3266_d1_b0) = 1
    staticDimsOf(x3266_d1_b0) = List(128, 128)
    val x3267 = Counter(min=Const(0), max=Const(32), step=Const(1), par=1).name("x3267").ctrl(x3330).srcCtx("GDA.scala:57:32") // CounterNew(Const(0),Const(32),Const(1),Const(1))
    val x3268 = CounterChain(List(x3267)).name("x3268").ctrl(x3330).srcCtx("GDA.scala:67:10") // CounterChainNew(List(x3267))
    val x3315 = LoopController(style=MetaPipe, level=OuterControl, cchain=x3268).name("x3315").ctrl(x3330).srcCtx("GDA.scala:67:10") // UnrolledReduce(List(b1908),x3268,x3266,Block((x3266) => Const(())),List(List(b1972)),List(List(b1973)))
    val b1972 = CounterIter(x3267, Some(0)).name("b1972").ctrl(x3315) // b1972
    val b1973 = Const(true).name("b1973").ctrl(x3315) // b1973
    val x3269_d0_b0 = SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x3269_d0_b0").ctrl(x3315).srcCtx("GDA.scala:58:32:subTile") // x3269 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x3269_d0_b0) = false
    bufferDepthOf(x3269_d0_b0) = 2
    staticDimsOf(x3269_d0_b0) = List(128)
    val x3269_d1_b0 = SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x3269_d1_b0").ctrl(x3315).srcCtx("GDA.scala:58:32:subTile") // x3269 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x3269_d1_b0) = false
    bufferDepthOf(x3269_d1_b0) = 2
    staticDimsOf(x3269_d1_b0) = List(128)
    val x3270_d0_b0 = SRAM(size=16384, banking=Strided(banks=16, stride=1)).name("x3270_d0_b0").ctrl(x3315).srcCtx("GDA.scala:59:34:sigmaTile") // x3270 = SRAMNew(ArrayBuffer(Const(128), Const(128)))
    isAccum(x3270_d0_b0) = false
    bufferDepthOf(x3270_d0_b0) = 2
    staticDimsOf(x3270_d0_b0) = List(128, 128)
    val x3271 = Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x3271").ctrl(x3315).srcCtx("GDA.scala:60:21") // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x3272 = CounterChain(List(x3271)).name("x3272").ctrl(x3315).srcCtx("GDA.scala:60:29") // CounterChainNew(List(x3271))
    val x3286 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3272).name("x3286").ctrl(x3315).srcCtx("GDA.scala:60:29") // UnrolledForeach(List(b1973, b1908),x3272,Block(Const(())),List(List(b1978)),List(List(b1979)))
    val b1978 = CounterIter(x3271, None).name("b1978").ctrl(x3286) // b1978
    val b1979 = Const(true).name("b1979").ctrl(x3286) // b1979
    val x3273 = OpDef(op=BitAnd, inputs=List(b1979, b1973)).name("x3273").ctrl(x3286).srcCtx("UnrollingBase.scala:28:66") // And(b1979,b1973)
    val x3274 = OpDef(op=BitAnd, inputs=List(x3273, b1908)).name("x3274").ctrl(x3286).srcCtx("UnrollingBase.scala:28:66") // And(x3273,b1908)
    val x3275 = LoadBanks(List(x3216_d0_b0), List(b1972, b1978)).name("x3275").ctrl(x3286).srcCtx("GDA.scala:61:35") // ParSRAMLoad(x3216,List(List(b1972, b1978)),List(x3274))
    val x3276 = x3275 // x3276 = VectorApply(x3275,0)
    val x3277 = LoadBanks(List(x3215_d0_b0), List(b1972)).name("x3277").ctrl(x3286).srcCtx("GDA.scala:61:58") // SRAMLoad(x3215,ArrayBuffer(Const(32)),List(b1972),Const(0),x3274)
    val x3278 = OpDef(op=FixEql, inputs=List(x3277, Const(1))).name("x3278").ctrl(x3286).srcCtx("GDA.scala:61:63") // FixEql(x3277,Const(1))
    val x3279 = LoadBanks(List(x3173_d0_b0), List(b1978)).name("x3279").ctrl(x3286).srcCtx("GDA.scala:61:76") // ParSRAMLoad(x3173,List(List(b1978)),List(x3274))
    val x3280 = x3279 // x3280 = VectorApply(x3279,0)
    val x3281 = LoadBanks(List(x3172_d0_b0), List(b1978)).name("x3281").ctrl(x3286).srcCtx("GDA.scala:61:89") // ParSRAMLoad(x3172,List(List(b1978)),List(x3274))
    val x3282 = x3281 // x3282 = VectorApply(x3281,0)
    val x3283 = OpDef(op=MuxOp, inputs=List(x3278, x3280, x3282)).name("x3283").ctrl(x3286).srcCtx("GDA.scala:61:49") // Mux(x3278,x3280,x3282)
    val x3284 = OpDef(op=FixSub, inputs=List(x3276, x3283)).name("x3284").ctrl(x3286).srcCtx("GDA.scala:61:44") // FixSub(x3276,x3283)
    val x3285 = StoreBanks(List(x3269_d0_b0, x3269_d1_b0), List(b1978), x3284).name("x3285").ctrl(x3286).srcCtx("GDA.scala:61:25") // ParSRAMStore(x3269,List(List(b1978)),List(x3284),List(x3274))
    val x3287 = Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x3287").ctrl(x3315).srcCtx("GDA.scala:63:29") // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x3288 = Counter(min=Const(0), max=Const(128), step=Const(1), par=1).name("x3288").ctrl(x3315).srcCtx("GDA.scala:63:21") // CounterNew(Const(0),Const(128),Const(1),Const(1))
    val x3289 = CounterChain(List(x3288,x3287)).name("x3289").ctrl(x3315).srcCtx("GDA.scala:63:37") // CounterChainNew(List(x3288, x3287))
    val x3298 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3289).name("x3298").ctrl(x3315).srcCtx("GDA.scala:63:37") // UnrolledForeach(List(b1973, b1908),x3289,Block(Const(())),List(List(b1997), List(b1998)),List(List(b1999), List(b2000)))
    val b1997 = CounterIter(x3288, Some(0)).name("b1997").ctrl(x3298) // b1997
    val b1999 = Const(true).name("b1999").ctrl(x3298) // b1999
    val b1998 = CounterIter(x3287, None).name("b1998").ctrl(x3298) // b1998
    val b2000 = Const(true).name("b2000").ctrl(x3298) // b2000
    val x3290 = OpDef(op=BitAnd, inputs=List(b1999, b2000)).name("x3290").ctrl(x3298).srcCtx("UnrollingBase.scala:28:66") // And(b1999,b2000)
    val x3291 = OpDef(op=BitAnd, inputs=List(b1973, b1908)).name("x3291").ctrl(x3298).srcCtx("UnrollingBase.scala:28:66") // And(b1973,b1908)
    val x3292 = OpDef(op=BitAnd, inputs=List(x3290, x3291)).name("x3292").ctrl(x3298).srcCtx("UnrollingBase.scala:28:66") // And(x3290,x3291)
    val x3293 = LoadBanks(List(x3269_d1_b0), List(b1997)).name("x3293").ctrl(x3298).srcCtx("GDA.scala:64:40") // SRAMLoad(x3269,ArrayBuffer(Const(128)),List(b1997),Const(0),x3292)
    val x3294 = LoadBanks(List(x3269_d0_b0), List(b1998)).name("x3294").ctrl(x3298).srcCtx("GDA.scala:64:54") // ParSRAMLoad(x3269,List(List(b1998)),List(x3292))
    val x3295 = x3294 // x3295 = VectorApply(x3294,0)
    val x3296 = OpDef(op=FixMul, inputs=List(x3293, x3295)).name("x3296").ctrl(x3298).srcCtx("GDA.scala:64:45") // FixMul(x3293,x3295)
    val x3297 = StoreBanks(List(x3270_d0_b0), List(b1997, b1998), x3296).name("x3297").ctrl(x3298).srcCtx("GDA.scala:64:31") // ParSRAMStore(x3270,List(List(b1997, b1998)),List(x3296),List(x3292))
    val x3299 = Counter(min=Const(0), max=Const(128), step=Const(1), par=1).name("x3299").ctrl(x3315).srcCtx("GDA.scala:67:10") // CounterNew(Const(0),Const(128),Const(1),Const(1))
    val x3300 = Counter(min=Const(0), max=Const(128), step=Const(1), par=1).name("x3300").ctrl(x3315).srcCtx("GDA.scala:67:10") // CounterNew(Const(0),Const(128),Const(1),Const(1))
    val x3301 = CounterChain(List(x3300,x3299)).name("x3301").ctrl(x3315).srcCtx("GDA.scala:67:10") // CounterChainNew(ArrayBuffer(x3300, x3299))
    val x3314 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3301).name("x3314").ctrl(x3315).srcCtx("GDA.scala:67:10") // UnrolledForeach(List(),x3301,Block(Const(())),ArrayBuffer(List(b2010), List(b2011)),ArrayBuffer(List(b2012), List(b2013)))
    val b2010 = CounterIter(x3300, Some(0)).name("b2010").ctrl(x3314) // b2010
    val b2012 = Const(true).name("b2012").ctrl(x3314) // b2012
    val b2011 = CounterIter(x3299, None).name("b2011").ctrl(x3314) // b2011
    val b2013 = Const(true).name("b2013").ctrl(x3314) // b2013
    val x3302 = OpDef(op=BitAnd, inputs=List(b2012, b2013)).name("x3302").ctrl(x3314).srcCtx("UnrollingBase.scala:28:66") // And(b2012,b2013)
    val x3303 = OpDef(op=BitAnd, inputs=List(x3302, b1908)).name("x3303").ctrl(x3314).srcCtx("UnrollingBase.scala:28:66") // And(x3302,b1908)
    val x3304 = LoadBanks(List(x3270_d0_b0), List(b2010, b2011)).name("x3304").ctrl(x3314).srcCtx("GDA.scala:67:10") // ParSRAMLoad(x3270,List(ArrayBuffer(b2010, b2011)),List(x3303))
    val x3305 = x3304 // x3305 = VectorApply(x3304,0)
    val x3306 = LoadBanks(List(x3266_d1_b0), List(b2010, b2011)).name("x3306").ctrl(x3314).srcCtx("GDA.scala:67:10") // ParSRAMLoad(x3266,List(ArrayBuffer(b2010, b2011)),List(x3303))
    val x3307 = x3306 // x3307 = VectorApply(x3306,0)
    val x3308 = OpDef(op=BitAnd, inputs=List(b1973, b1908)).name("x3308").ctrl(x3314).srcCtx("GDA.scala:67:10") // And(b1973,b1908)
    val x3309 = OpDef(op=BitAnd, inputs=List(x3308, x3303)).name("x3309").ctrl(x3314).srcCtx("GDA.scala:67:10") // And(x3308,x3303)
    val x3310 = OpDef(op=FixEql, inputs=List(b1972, Const(0))).name("x3310").ctrl(x3314).srcCtx("GDA.scala:67:10") // FixEql(b1972,Const(0))
    val x3311 = OpDef(op=FixAdd, inputs=List(x3305, x3307)).name("x3311").ctrl(x3314).srcCtx("GDA.scala:67:12") // FixAdd(x3305,x3307)
    val x3312 = OpDef(op=MuxOp, inputs=List(x3310, x3305, x3311)).name("x3312").ctrl(x3314).srcCtx("GDA.scala:67:10") // Mux(x3310,x3305,x3311)
    val x3313 = StoreBanks(List(x3266_d0_b0, x3266_d1_b0), List(b2010, b2011), x3312).name("x3313").ctrl(x3314).srcCtx("GDA.scala:67:10") // ParSRAMStore(x3266,List(ArrayBuffer(b2010, b2011)),List(x3312),List(x3303))
    antiDepsOf(x3313)=List(x3306)
    val x3316 = Counter(min=Const(0), max=Const(128), step=Const(1), par=1).name("x3316").ctrl(x3330).srcCtx("GDA.scala:68:8") // CounterNew(Const(0),Const(128),Const(1),Const(1))
    val x3317 = Counter(min=Const(0), max=Const(128), step=Const(1), par=1).name("x3317").ctrl(x3330).srcCtx("GDA.scala:68:8") // CounterNew(Const(0),Const(128),Const(1),Const(1))
    val x3318 = CounterChain(List(x3317,x3316)).name("x3318").ctrl(x3330).srcCtx("GDA.scala:68:8") // CounterChainNew(ArrayBuffer(x3317, x3316))
    val x3329 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3318).name("x3329").ctrl(x3330).srcCtx("GDA.scala:68:8") // UnrolledForeach(List(),x3318,Block(Const(())),ArrayBuffer(List(b2028), List(b2029)),ArrayBuffer(List(b2030), List(b2031)))
    val b2028 = CounterIter(x3317, Some(0)).name("b2028").ctrl(x3329) // b2028
    val b2030 = Const(true).name("b2030").ctrl(x3329) // b2030
    val b2029 = CounterIter(x3316, None).name("b2029").ctrl(x3329) // b2029
    val b2031 = Const(true).name("b2031").ctrl(x3329) // b2031
    val x3319 = OpDef(op=BitAnd, inputs=List(b2030, b2031)).name("x3319").ctrl(x3329).srcCtx("UnrollingBase.scala:28:66") // And(b2030,b2031)
    val x3320 = LoadBanks(List(x3266_d0_b0), List(b2028, b2029)).name("x3320").ctrl(x3329).srcCtx("GDA.scala:68:8") // ParSRAMLoad(x3266,List(ArrayBuffer(b2028, b2029)),List(x3319))
    val x3321 = x3320 // x3321 = VectorApply(x3320,0)
    val x3322 = LoadBanks(List(x3211_d1_b0), List(b2028, b2029)).name("x3322").ctrl(x3329).srcCtx("GDA.scala:68:8") // ParSRAMLoad(x3211,List(ArrayBuffer(b2028, b2029)),List(x3319))
    val x3323 = x3322 // x3323 = VectorApply(x3322,0)
    val x3324 = OpDef(op=BitAnd, inputs=List(b1908, x3319)).name("x3324").ctrl(x3329).srcCtx("GDA.scala:68:8") // And(b1908,x3319)
    val x3325 = OpDef(op=FixEql, inputs=List(b1907, Const(0))).name("x3325").ctrl(x3329).srcCtx("GDA.scala:68:8") // FixEql(b1907,Const(0))
    val x3326 = OpDef(op=FixAdd, inputs=List(x3321, x3323)).name("x3326").ctrl(x3329).srcCtx("GDA.scala:68:10") // FixAdd(x3321,x3323)
    val x3327 = OpDef(op=MuxOp, inputs=List(x3325, x3321, x3326)).name("x3327").ctrl(x3329).srcCtx("GDA.scala:68:8") // Mux(x3325,x3321,x3326)
    val x3328 = StoreBanks(List(x3211_d0_b0, x3211_d1_b0), List(b2028, b2029), x3327).name("x3328").ctrl(x3329).srcCtx("GDA.scala:68:8") // ParSRAMStore(x3211,List(ArrayBuffer(b2028, b2029)),List(x3327),List(x3319))
    antiDepsOf(x3328)=List(x3322)
    val x3331 = Counter(min=Const(0), max=Const(128), step=Const(1), par=1).name("x3331").ctrl(x3359).srcCtx("GDA.scala:70:36") // CounterNew(Const(0),Const(128),Const(1),Const(1))
    val x3332 = CounterChain(List(x3331)).name("x3332").ctrl(x3359).srcCtx("GDA.scala:70:36") // CounterChainNew(List(x3331))
    val x3358 = LoopController(style=StreamPipe, level=OuterControl, cchain=x3332).name("x3358").ctrl(x3359).srcCtx("GDA.scala:70:36") // UnrolledForeach(List(Const(true)),x3332,Block(Const(())),List(List(b2046)),List(List(b2047)))
    val b2046 = CounterIter(x3331, Some(0)).name("b2046").ctrl(x3358) // b2046
    val b2047 = Const(true).name("b2047").ctrl(x3358) // b2047
    val b3427 = StreamOut(field="offset").name("b3427").ctrl(x3358).srcCtx("GDA.scala:70:36") // x3333 = StreamOutNew(BurstCmdBus)
    isAccum(b3427) = false
    bufferDepthOf(b3427) = 1
    val b3428 = StreamOut(field="size").name("b3428").ctrl(x3358).srcCtx("GDA.scala:70:36") // x3333 = StreamOutNew(BurstCmdBus)
    isAccum(b3428) = false
    bufferDepthOf(b3428) = 1
    val x3334 = StreamOut(field="data").name("x3334").ctrl(x3358).srcCtx("GDA.scala:70:36") // x3334 = StreamOutNew(BurstFullDataBus())
    isAccum(x3334) = false
    bufferDepthOf(x3334) = 1
    val x3335 = StreamIn(field="ack").name("x3335").ctrl(x3358).srcCtx("GDA.scala:70:36") // x3335 = StreamInNew(BurstAckBus)
    isAccum(x3335) = false
    bufferDepthOf(x3335) = 1
    val x3346 = UnitController(style=SeqPipe, level=InnerControl).name("x3346").ctrl(x3358).srcCtx("GDA.scala:70:36") // UnitPipe(List(b2047),Block(x3345))
    val x3336 = b2046 // FixConvert(b2046,TRUE,_32,_0) (Same Type. No op)
    val x3337 = OpDef(op=FixSla, inputs=List(x3336, Const(7))).name("x3337").ctrl(x3346).srcCtx("GDA.scala:70:36") // FixLsh(x3336,Const(7))
    val x3338 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x3339 = OpDef(op=FixAdd, inputs=List(x3337, x3338)).name("x3339").ctrl(x3346).srcCtx("GDA.scala:70:36") // FixAdd(x3337,x3338)
    val x3340 = OpDef(op=FixSla, inputs=List(x3339, Const(2))).name("x3340").ctrl(x3346).srcCtx("GDA.scala:70:36") // FixLsh(x3339,Const(2))
    val x3341 = x3340 // FixConvert(x3340,TRUE,_64,_0)
    val x3342 = DramAddress(x3167).name("x3342").ctrl(x3346).srcCtx("GDA.scala:70:36") // GetDRAMAddress(x3167)
    val x3344_x3343 = OpDef(op=FixAdd, inputs=List(x3341, x3342)).name("x3344_x3343").ctrl(x3346).srcCtx("GDA.scala:70:36") // FixAdd(x3341,x3342)
    // x3344 = SimpleStruct(ArrayBuffer((offset,x3343), (size,Const(512)), (isLoad,Const(false))))
    val x3345_b3429_b3427 = WriteMem(b3427, x3344_x3343).name("x3345_b3429_b3427").ctrl(x3346).srcCtx("GDA.scala:70:36") // StreamWrite(x3333,x3344,b2047)
    val x3345_b3430_b3428 = WriteMem(b3428, Const(512)).name("x3345_b3430_b3428").ctrl(x3346).srcCtx("GDA.scala:70:36") // StreamWrite(x3333,x3344,b2047)
    val x3347 = Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x3347").ctrl(x3358).srcCtx("GDA.scala:70:36") // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x3348 = CounterChain(List(x3347)).name("x3348").ctrl(x3358).srcCtx("GDA.scala:70:36") // CounterChainNew(List(x3347))
    val x3354 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3348).name("x3354").ctrl(x3358).srcCtx("GDA.scala:70:36") // UnrolledForeach(List(b2047),x3348,Block(Const(())),List(List(b2064)),List(List(b2065)))
    val b2064 = CounterIter(x3347, None).name("b2064").ctrl(x3354) // b2064
    val b2065 = Const(true).name("b2065").ctrl(x3354) // b2065
    val x3349 = OpDef(op=BitAnd, inputs=List(b2065, b2047)).name("x3349").ctrl(x3354).srcCtx("UnrollingBase.scala:28:66") // And(b2065,b2047)
    val x3350 = LoadBanks(List(x3211_d0_b0), List(b2046, b2064)).name("x3350").ctrl(x3354).srcCtx("GDA.scala:70:36") // ParSRAMLoad(x3211,List(List(b2046, b2064)),List(x3349))
    val x3352_x3351 = x3350 // x3351 = VectorApply(x3350,0)
    // x3352 = SimpleStruct(ArrayBuffer((_1,x3351), (_2,Const(true))))
    val x3353_x3353_x3334 = WriteMem(x3334, x3352_x3351).name("x3353_x3353_x3334").ctrl(x3354).srcCtx("GDA.scala:70:36") // ParStreamWrite(x3334,List(x3352),List(x3349))
    val x3355 = FringeDenseStore(dram=List(x3167), cmdStream=List(b3427, b3428), dataStream=List(x3334), ackStream=List(x3335)).name("x3355").ctrl(x3358).srcCtx("GDA.scala:70:36") // FringeDenseStore(x3167,x3333,x3334,x3335)
    val x3357 = UnitController(style=SeqPipe, level=InnerControl).name("x3357").ctrl(x3358).srcCtx("GDA.scala:70:36") // UnitPipe(List(b2047),Block(Const(())))
    val x3356_x3356 = ReadMem(x3335).name("x3356_x3356").ctrl(x3357).srcCtx("GDA.scala:70:36") // StreamRead(x3335,b2047)
    
  }
}
