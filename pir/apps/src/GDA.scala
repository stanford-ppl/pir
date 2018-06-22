import pir._
import pir.node._
import arch._
import prism.enums._

object GDA extends PIRApp {
  def main(implicit design:PIRDesign) = {
    import design.pirmeta._
    val x3147 = ArgIn(init=0).name("x3147").ctrl(top).srcCtx("GDA.scala:22:18:R") // ArgInNew(Const(0))
    isAccum(x3147) = false
    bufferDepthOf(x3147) = 1
    boundOf(x3147) = 1024
    val x3149 = ReadMem(x3147).name("x3149").ctrl(top).srcCtx("GDA.scala:25:21") // RegRead(x3147)
    val x3150 = DRAM().name("x3150").ctrl(top).srcCtx("GDA.scala:25:20:x") // x3150 = DRAMNew(ArrayBuffer(x3149, Const(96)),Const(0))
    val x3151 = ReadMem(x3147).name("x3151").ctrl(top).srcCtx("GDA.scala:26:23") // RegRead(x3147)
    val x3152 = DRAM().name("x3152").ctrl(top).srcCtx("GDA.scala:26:22:y") // x3152 = DRAMNew(ArrayBuffer(x3151),Const(0))
    val x3153 = DRAM().name("x3153").ctrl(top).srcCtx("GDA.scala:27:22:mu0") // x3153 = DRAMNew(ArrayBuffer(Const(96)),Const(0))
    val x3154 = DRAM().name("x3154").ctrl(top).srcCtx("GDA.scala:28:22:mu1") // x3154 = DRAMNew(ArrayBuffer(Const(96)),Const(0))
    val x3155 = DRAM().name("x3155").ctrl(top).srcCtx("GDA.scala:29:24:sigma") // x3155 = DRAMNew(ArrayBuffer(Const(96), Const(96)),Const(0))
    val x3343 = UnitController(style=SeqPipe, level=OuterControl).name("x3343").ctrl(top).srcCtx("GDA.scala:36:11") // Hwblock(Block(Const(())),false)
    val x3160_d0_b0 = SRAM(size=96, banking=Strided(banks=16, stride=1)).name("x3160_d0_b0").ctrl(x3343).srcCtx("GDA.scala:37:28:mu0Tile") // x3160 = SRAMNew(ArrayBuffer(Const(96)))
    isAccum(x3160_d0_b0) = false
    bufferDepthOf(x3160_d0_b0) = 1
    val x3161_d0_b0 = SRAM(size=96, banking=Strided(banks=16, stride=1)).name("x3161_d0_b0").ctrl(x3343).srcCtx("GDA.scala:38:28:mu1Tile") // x3161 = SRAMNew(ArrayBuffer(Const(96)))
    isAccum(x3161_d0_b0) = false
    bufferDepthOf(x3161_d0_b0) = 1
    val x3179 = UnitController(style=StreamPipe, level=OuterControl).name("x3179").ctrl(x3343).srcCtx("GDA.scala:40:17") // UnitPipe(List(Const(true)),Block(Const(())))
    val b3395 = StreamOut(field="offset").name("b3395").ctrl(x3179).srcCtx("GDA.scala:40:17") // x3162 = StreamOutNew(BurstCmdBus)
    isAccum(b3395) = false
    bufferDepthOf(b3395) = 1
    val b3396 = StreamOut(field="size").name("b3396").ctrl(x3179).srcCtx("GDA.scala:40:17") // x3162 = StreamOutNew(BurstCmdBus)
    isAccum(b3396) = false
    bufferDepthOf(b3396) = 1
    val x3163 = StreamIn(field="data").name("x3163").ctrl(x3179).srcCtx("GDA.scala:40:17") // x3163 = StreamInNew(BurstDataBus())
    isAccum(x3163) = false
    bufferDepthOf(x3163) = 1
    val x3171 = UnitController(style=SeqPipe, level=InnerControl).name("x3171").ctrl(x3179).srcCtx("GDA.scala:40:17") // UnitPipe(List(Const(true)),Block(x3170))
    val x3164 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x3165 = OpDef(op=FixSla, inputs=List(x3164, Const(2))).name("x3165").ctrl(x3171).srcCtx("GDA.scala:40:17") // FixLsh(x3164,Const(2))
    val x3166 = x3165 // FixConvert(x3165,TRUE,_64,_0)
    val x3167 = DramAddress(x3153).name("x3167").ctrl(x3171).srcCtx("GDA.scala:40:17") // GetDRAMAddress(x3153)
    val x3169_x3168 = OpDef(op=FixAdd, inputs=List(x3166, x3167)).name("x3169_x3168").ctrl(x3171).srcCtx("GDA.scala:40:17") // FixAdd(x3166,x3167)
    // x3169 = SimpleStruct(ArrayBuffer((offset,x3168), (size,Const(384)), (isLoad,Const(true))))
    val x3170_b3397_b3395 = WriteMem(b3395, x3169_x3168).name("x3170_b3397_b3395").ctrl(x3171).srcCtx("GDA.scala:40:17") // StreamWrite(x3162,x3169,Const(true))
    val x3170_b3398_b3396 = WriteMem(b3396, Const(384)).name("x3170_b3398_b3396").ctrl(x3171).srcCtx("GDA.scala:40:17") // StreamWrite(x3162,x3169,Const(true))
    val x3172 = FringeDenseLoad(dram=List(x3153), cmdStream=List(b3395, b3396), dataStream=List(x3163)).name("x3172").ctrl(x3179).srcCtx("GDA.scala:40:17") // FringeDenseLoad(x3153,x3162,x3163)
    val x3173 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16).name("x3173").ctrl(x3179).srcCtx("GDA.scala:40:17") // CounterNew(Const(0),Const(96),Const(1),Const(16))
    val x3174 = CounterChain(List(x3173)).name("x3174").ctrl(x3179).srcCtx("GDA.scala:40:17") // CounterChainNew(List(x3173))
    val x3178 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3174).name("x3178").ctrl(x3179).srcCtx("GDA.scala:40:17") // UnrolledForeach(List(Const(true)),x3174,Block(Const(())),List(List(b1872)),List(List(b1873)))
    val b1872 = CounterIter(x3173, None).name("b1872").ctrl(x3178) // b1872
    val b1873 = Const(true).name("b1873").ctrl(x3178) // b1873
    val x3175_x3175 = ReadMem(x3163).name("x3175_x3175").ctrl(x3178).srcCtx("GDA.scala:40:17") // ParStreamRead(x3163,List(b1873))
    val x3176_x3176 = x3175_x3175 // x3176 = VectorApply(x3175,0)
    val x3177 = StoreBanks(List(x3160_d0_b0), List(b1872), x3176_x3176).name("x3177").ctrl(x3178).srcCtx("GDA.scala:40:17") // ParSRAMStore(x3160,List(List(b1872)),List(x3176),List(b1873))
    val x3197 = UnitController(style=StreamPipe, level=OuterControl).name("x3197").ctrl(x3343).srcCtx("GDA.scala:41:17") // UnitPipe(List(Const(true)),Block(Const(())))
    val b3399 = StreamOut(field="offset").name("b3399").ctrl(x3197).srcCtx("GDA.scala:41:17") // x3180 = StreamOutNew(BurstCmdBus)
    isAccum(b3399) = false
    bufferDepthOf(b3399) = 1
    val b3400 = StreamOut(field="size").name("b3400").ctrl(x3197).srcCtx("GDA.scala:41:17") // x3180 = StreamOutNew(BurstCmdBus)
    isAccum(b3400) = false
    bufferDepthOf(b3400) = 1
    val x3181 = StreamIn(field="data").name("x3181").ctrl(x3197).srcCtx("GDA.scala:41:17") // x3181 = StreamInNew(BurstDataBus())
    isAccum(x3181) = false
    bufferDepthOf(x3181) = 1
    val x3189 = UnitController(style=SeqPipe, level=InnerControl).name("x3189").ctrl(x3197).srcCtx("GDA.scala:41:17") // UnitPipe(List(Const(true)),Block(x3188))
    val x3182 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x3183 = OpDef(op=FixSla, inputs=List(x3182, Const(2))).name("x3183").ctrl(x3189).srcCtx("GDA.scala:41:17") // FixLsh(x3182,Const(2))
    val x3184 = x3183 // FixConvert(x3183,TRUE,_64,_0)
    val x3185 = DramAddress(x3154).name("x3185").ctrl(x3189).srcCtx("GDA.scala:41:17") // GetDRAMAddress(x3154)
    val x3187_x3186 = OpDef(op=FixAdd, inputs=List(x3184, x3185)).name("x3187_x3186").ctrl(x3189).srcCtx("GDA.scala:41:17") // FixAdd(x3184,x3185)
    // x3187 = SimpleStruct(ArrayBuffer((offset,x3186), (size,Const(384)), (isLoad,Const(true))))
    val x3188_b3401_b3399 = WriteMem(b3399, x3187_x3186).name("x3188_b3401_b3399").ctrl(x3189).srcCtx("GDA.scala:41:17") // StreamWrite(x3180,x3187,Const(true))
    val x3188_b3402_b3400 = WriteMem(b3400, Const(384)).name("x3188_b3402_b3400").ctrl(x3189).srcCtx("GDA.scala:41:17") // StreamWrite(x3180,x3187,Const(true))
    val x3190 = FringeDenseLoad(dram=List(x3154), cmdStream=List(b3399, b3400), dataStream=List(x3181)).name("x3190").ctrl(x3197).srcCtx("GDA.scala:41:17") // FringeDenseLoad(x3154,x3180,x3181)
    val x3191 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16).name("x3191").ctrl(x3197).srcCtx("GDA.scala:41:17") // CounterNew(Const(0),Const(96),Const(1),Const(16))
    val x3192 = CounterChain(List(x3191)).name("x3192").ctrl(x3197).srcCtx("GDA.scala:41:17") // CounterChainNew(List(x3191))
    val x3196 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3192).name("x3196").ctrl(x3197).srcCtx("GDA.scala:41:17") // UnrolledForeach(List(Const(true)),x3192,Block(Const(())),List(List(b1892)),List(List(b1893)))
    val b1892 = CounterIter(x3191, None).name("b1892").ctrl(x3196) // b1892
    val b1893 = Const(true).name("b1893").ctrl(x3196) // b1893
    val x3193_x3193 = ReadMem(x3181).name("x3193_x3193").ctrl(x3196).srcCtx("GDA.scala:41:17") // ParStreamRead(x3181,List(b1893))
    val x3194_x3194 = x3193_x3193 // x3194 = VectorApply(x3193,0)
    val x3195 = StoreBanks(List(x3161_d0_b0), List(b1892), x3194_x3194).name("x3195").ctrl(x3196).srcCtx("GDA.scala:41:17") // ParSRAMStore(x3161,List(List(b1892)),List(x3194),List(b1893))
    val x3198_d0_b0 = SRAM(size=9216, banking=Strided(banks=16, stride=1)).name("x3198_d0_b0").ctrl(x3343).srcCtx("GDA.scala:44:29:sigmaOut") // x3198 = SRAMNew(ArrayBuffer(Const(96), Const(96)))
    isAccum(x3198_d0_b0) = false
    bufferDepthOf(x3198_d0_b0) = 1
    val x3198_d1_b0 = SRAM(size=9216, banking=Strided(banks=1, stride=96)).name("x3198_d1_b0").ctrl(x3343).srcCtx("GDA.scala:44:29:sigmaOut") // x3198 = SRAMNew(ArrayBuffer(Const(96), Const(96)))
    isAccum(x3198_d1_b0) = true
    bufferDepthOf(x3198_d1_b0) = 1
    val x3199 = ReadMem(x3147).name("x3199").ctrl(x3343).srcCtx("GDA.scala:46:27") // RegRead(x3147)
    val x3200 = Counter(min=Const(0), max=x3199, step=Const(32), par=1).name("x3200").ctrl(x3343).srcCtx("GDA.scala:46:35") // CounterNew(Const(0),x3199,Const(32),Const(1))
    val x3201 = CounterChain(List(x3200)).name("x3201").ctrl(x3343).srcCtx("GDA.scala:68:8") // CounterChainNew(List(x3200))
    val x3314 = LoopController(style=MetaPipe, level=OuterControl, cchain=x3201).name("x3314").ctrl(x3343).srcCtx("GDA.scala:68:8") // UnrolledReduce(List(Const(true)),x3201,x3198,Block((x3198) => Const(())),List(List(b1907)),List(List(b1908)))
    val b1907 = CounterIter(x3200, Some(0)).name("b1907").ctrl(x3314) // b1907
    val b1908 = Const(true).name("b1908").ctrl(x3314) // b1908
    val x3202_d0_b0 = SRAM(size=32, banking=Strided(banks=16, stride=1)).name("x3202_d0_b0").ctrl(x3314).srcCtx("GDA.scala:47:33:gdaYtile") // x3202 = SRAMNew(ArrayBuffer(Const(32)))
    isAccum(x3202_d0_b0) = false
    bufferDepthOf(x3202_d0_b0) = 2
    val x3203_d0_b0 = SRAM(size=3072, banking=Strided(banks=16, stride=1)).name("x3203_d0_b0").ctrl(x3314).srcCtx("GDA.scala:48:31:gdaXtile") // x3203 = SRAMNew(ArrayBuffer(Const(32), Const(96)))
    isAccum(x3203_d0_b0) = false
    bufferDepthOf(x3203_d0_b0) = 2
    val x3205 = UnitController(style=SeqPipe, level=InnerControl).name("x3205").ctrl(x3314).srcCtx("GDA.scala:50:18") // UnitPipe(List(b1908),Block(Const(())))
    val x3204 = OpDef(op=FixAdd, inputs=List(b1907, Const(32))).name("x3204").ctrl(x3205).srcCtx("GDA.scala:51:34") // FixAdd(b1907,Const(32))
    val x3224 = UnitController(style=StreamPipe, level=OuterControl).name("x3224").ctrl(x3314).srcCtx("GDA.scala:51:20") // UnitPipe(List(b1908),Block(Const(())))
    val b3403 = StreamOut(field="offset").name("b3403").ctrl(x3224).srcCtx("GDA.scala:51:20") // x3206 = StreamOutNew(BurstCmdBus)
    isAccum(b3403) = false
    bufferDepthOf(b3403) = 1
    val b3404 = StreamOut(field="size").name("b3404").ctrl(x3224).srcCtx("GDA.scala:51:20") // x3206 = StreamOutNew(BurstCmdBus)
    isAccum(b3404) = false
    bufferDepthOf(b3404) = 1
    val x3207 = StreamIn(field="data").name("x3207").ctrl(x3224).srcCtx("GDA.scala:51:20") // x3207 = StreamInNew(BurstDataBus())
    isAccum(x3207) = false
    bufferDepthOf(x3207) = 1
    val x3215 = UnitController(style=SeqPipe, level=InnerControl).name("x3215").ctrl(x3224).srcCtx("GDA.scala:51:20") // UnitPipe(List(b1908),Block(x3214))
    val x3208 = b1907 // FixConvert(b1907,TRUE,_32,_0) (Same Type. No op)
    val x3209 = OpDef(op=FixSla, inputs=List(x3208, Const(2))).name("x3209").ctrl(x3215).srcCtx("GDA.scala:51:20") // FixLsh(x3208,Const(2))
    val x3210 = x3209 // FixConvert(x3209,TRUE,_64,_0)
    val x3211 = DramAddress(x3152).name("x3211").ctrl(x3215).srcCtx("GDA.scala:51:20") // GetDRAMAddress(x3152)
    val x3213_x3212 = OpDef(op=FixAdd, inputs=List(x3210, x3211)).name("x3213_x3212").ctrl(x3215).srcCtx("GDA.scala:51:20") // FixAdd(x3210,x3211)
    // x3213 = SimpleStruct(ArrayBuffer((offset,x3212), (size,Const(128)), (isLoad,Const(true))))
    val x3214_b3405_b3403 = WriteMem(b3403, x3213_x3212).name("x3214_b3405_b3403").ctrl(x3215).srcCtx("GDA.scala:51:20") // StreamWrite(x3206,x3213,b1908)
    val x3214_b3406_b3404 = WriteMem(b3404, Const(128)).name("x3214_b3406_b3404").ctrl(x3215).srcCtx("GDA.scala:51:20") // StreamWrite(x3206,x3213,b1908)
    val x3216 = FringeDenseLoad(dram=List(x3152), cmdStream=List(b3403, b3404), dataStream=List(x3207)).name("x3216").ctrl(x3224).srcCtx("GDA.scala:51:20") // FringeDenseLoad(x3152,x3206,x3207)
    val x3217 = Counter(min=Const(0), max=Const(32), step=Const(1), par=16).name("x3217").ctrl(x3224).srcCtx("GDA.scala:51:20") // CounterNew(Const(0),Const(32),Const(1),Const(16))
    val x3218 = CounterChain(List(x3217)).name("x3218").ctrl(x3224).srcCtx("GDA.scala:51:20") // CounterChainNew(List(x3217))
    val x3223 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3218).name("x3223").ctrl(x3224).srcCtx("GDA.scala:51:20") // UnrolledForeach(List(b1908),x3218,Block(Const(())),List(List(b1926)),List(List(b1927)))
    val b1926 = CounterIter(x3217, None).name("b1926").ctrl(x3223) // b1926
    val b1927 = Const(true).name("b1927").ctrl(x3223) // b1927
    val x3219 = OpDef(op=BitAnd, inputs=List(b1927, b1908)).name("x3219").ctrl(x3223).srcCtx("UnrollingBase.scala:28:66") // And(b1927,b1908)
    val x3220_x3220 = ReadMem(x3207).name("x3220_x3220").ctrl(x3223).srcCtx("GDA.scala:51:20") // ParStreamRead(x3207,List(x3219))
    val x3221_x3221 = x3220_x3220 // x3221 = VectorApply(x3220,0)
    val x3222 = StoreBanks(List(x3202_d0_b0), List(b1926), x3221_x3221).name("x3222").ctrl(x3223).srcCtx("GDA.scala:51:20") // ParSRAMStore(x3202,List(List(b1926)),List(x3221),List(x3219))
    val x3225 = Counter(min=Const(0), max=Const(32), step=Const(1), par=1).name("x3225").ctrl(x3314).srcCtx("GDA.scala:52:20") // CounterNew(Const(0),Const(32),Const(1),Const(1))
    val x3226 = CounterChain(List(x3225)).name("x3226").ctrl(x3314).srcCtx("GDA.scala:52:20") // CounterChainNew(List(x3225))
    val x3251 = LoopController(style=StreamPipe, level=OuterControl, cchain=x3226).name("x3251").ctrl(x3314).srcCtx("GDA.scala:52:20") // UnrolledForeach(List(b1908),x3226,Block(Const(())),List(List(b1936)),List(List(b1937)))
    val b1936 = CounterIter(x3225, Some(0)).name("b1936").ctrl(x3251) // b1936
    val b1937 = Const(true).name("b1937").ctrl(x3251) // b1937
    val b3407 = StreamOut(field="offset").name("b3407").ctrl(x3251).srcCtx("GDA.scala:52:20") // x3227 = StreamOutNew(BurstCmdBus)
    isAccum(b3407) = false
    bufferDepthOf(b3407) = 1
    val b3408 = StreamOut(field="size").name("b3408").ctrl(x3251).srcCtx("GDA.scala:52:20") // x3227 = StreamOutNew(BurstCmdBus)
    isAccum(b3408) = false
    bufferDepthOf(b3408) = 1
    val x3228 = StreamIn(field="data").name("x3228").ctrl(x3251).srcCtx("GDA.scala:52:20") // x3228 = StreamInNew(BurstDataBus())
    isAccum(x3228) = false
    bufferDepthOf(x3228) = 1
    val x3241 = UnitController(style=SeqPipe, level=InnerControl).name("x3241").ctrl(x3251).srcCtx("GDA.scala:52:20") // UnitPipe(List(b1937, b1908),Block(x3240))
    val x3229 = OpDef(op=FixAdd, inputs=List(b1907, b1936)).name("x3229").ctrl(x3241).srcCtx("GDA.scala:52:20") // FixAdd(b1907,b1936)
    val x3230 = x3229 // FixConvert(x3229,TRUE,_32,_0) (Same Type. No op)
    val x3231 = OpDef(op=FixMul, inputs=List(x3230, Const(96))).name("x3231").ctrl(x3241).srcCtx("GDA.scala:52:20") // FixMul(x3230,Const(96))
    val x3232 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x3233 = OpDef(op=FixAdd, inputs=List(x3231, x3232)).name("x3233").ctrl(x3241).srcCtx("GDA.scala:52:20") // FixAdd(x3231,x3232)
    val x3234 = OpDef(op=FixSla, inputs=List(x3233, Const(2))).name("x3234").ctrl(x3241).srcCtx("GDA.scala:52:20") // FixLsh(x3233,Const(2))
    val x3235 = x3234 // FixConvert(x3234,TRUE,_64,_0)
    val x3236 = DramAddress(x3150).name("x3236").ctrl(x3241).srcCtx("GDA.scala:52:20") // GetDRAMAddress(x3150)
    val x3238_x3237 = OpDef(op=FixAdd, inputs=List(x3235, x3236)).name("x3238_x3237").ctrl(x3241).srcCtx("GDA.scala:52:20") // FixAdd(x3235,x3236)
    // x3238 = SimpleStruct(ArrayBuffer((offset,x3237), (size,Const(384)), (isLoad,Const(true))))
    val x3239 = OpDef(op=BitAnd, inputs=List(b1937, b1908)).name("x3239").ctrl(x3241).srcCtx("UnrollingBase.scala:28:66") // And(b1937,b1908)
    val x3240_b3409_b3407 = WriteMem(b3407, x3238_x3237).name("x3240_b3409_b3407").ctrl(x3241).srcCtx("GDA.scala:52:20") // StreamWrite(x3227,x3238,x3239)
    val x3240_b3410_b3408 = WriteMem(b3408, Const(384)).name("x3240_b3410_b3408").ctrl(x3241).srcCtx("GDA.scala:52:20") // StreamWrite(x3227,x3238,x3239)
    val x3242 = FringeDenseLoad(dram=List(x3150), cmdStream=List(b3407, b3408), dataStream=List(x3228)).name("x3242").ctrl(x3251).srcCtx("GDA.scala:52:20") // FringeDenseLoad(x3150,x3227,x3228)
    val x3243 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16).name("x3243").ctrl(x3251).srcCtx("GDA.scala:52:20") // CounterNew(Const(0),Const(96),Const(1),Const(16))
    val x3244 = CounterChain(List(x3243)).name("x3244").ctrl(x3251).srcCtx("GDA.scala:52:20") // CounterChainNew(List(x3243))
    val x3250 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3244).name("x3250").ctrl(x3251).srcCtx("GDA.scala:52:20") // UnrolledForeach(List(b1937, b1908),x3244,Block(Const(())),List(List(b1956)),List(List(b1957)))
    val b1956 = CounterIter(x3243, None).name("b1956").ctrl(x3250) // b1956
    val b1957 = Const(true).name("b1957").ctrl(x3250) // b1957
    val x3245 = OpDef(op=BitAnd, inputs=List(b1957, b1937)).name("x3245").ctrl(x3250).srcCtx("UnrollingBase.scala:28:66") // And(b1957,b1937)
    val x3246 = OpDef(op=BitAnd, inputs=List(x3245, b1908)).name("x3246").ctrl(x3250).srcCtx("UnrollingBase.scala:28:66") // And(x3245,b1908)
    val x3247_x3247 = ReadMem(x3228).name("x3247_x3247").ctrl(x3250).srcCtx("GDA.scala:52:20") // ParStreamRead(x3228,List(x3246))
    val x3248_x3248 = x3247_x3247 // x3248 = VectorApply(x3247,0)
    val x3249 = StoreBanks(List(x3203_d0_b0), List(b1936, b1956), x3248_x3248).name("x3249").ctrl(x3250).srcCtx("GDA.scala:52:20") // ParSRAMStore(x3203,List(List(b1936, b1956)),List(x3248),List(x3246))
    val x3252_d0_b0 = SRAM(size=9216, banking=Strided(banks=1, stride=96)).name("x3252_d0_b0").ctrl(x3314).srcCtx("GDA.scala:55:31:sigmaBlk") // x3252 = SRAMNew(ArrayBuffer(Const(96), Const(96)))
    isAccum(x3252_d0_b0) = false
    bufferDepthOf(x3252_d0_b0) = 2
    val x3252_d1_b0 = SRAM(size=9216, banking=Strided(banks=1, stride=96)).name("x3252_d1_b0").ctrl(x3314).srcCtx("GDA.scala:55:31:sigmaBlk") // x3252 = SRAMNew(ArrayBuffer(Const(96), Const(96)))
    isAccum(x3252_d1_b0) = true
    bufferDepthOf(x3252_d1_b0) = 1
    val x3253 = Counter(min=Const(0), max=Const(32), step=Const(1), par=1).name("x3253").ctrl(x3314).srcCtx("GDA.scala:57:32") // CounterNew(Const(0),Const(32),Const(1),Const(1))
    val x3254 = CounterChain(List(x3253)).name("x3254").ctrl(x3314).srcCtx("GDA.scala:67:10") // CounterChainNew(List(x3253))
    val x3300 = LoopController(style=MetaPipe, level=OuterControl, cchain=x3254).name("x3300").ctrl(x3314).srcCtx("GDA.scala:67:10") // UnrolledReduce(List(b1908),x3254,x3252,Block((x3252) => Const(())),List(List(b1972)),List(List(b1973)))
    val b1972 = CounterIter(x3253, Some(0)).name("b1972").ctrl(x3300) // b1972
    val b1973 = Const(true).name("b1973").ctrl(x3300) // b1973
    val x3255_d0_b0 = SRAM(size=96, banking=Strided(banks=16, stride=1)).name("x3255_d0_b0").ctrl(x3300).srcCtx("GDA.scala:58:32:subTile") // x3255 = SRAMNew(ArrayBuffer(Const(96)))
    isAccum(x3255_d0_b0) = false
    bufferDepthOf(x3255_d0_b0) = 2
    val x3255_d1_b0 = SRAM(size=96, banking=Strided(banks=16, stride=1)).name("x3255_d1_b0").ctrl(x3300).srcCtx("GDA.scala:58:32:subTile") // x3255 = SRAMNew(ArrayBuffer(Const(96)))
    isAccum(x3255_d1_b0) = false
    bufferDepthOf(x3255_d1_b0) = 2
    val x3256_d0_b0 = SRAM(size=9216, banking=Strided(banks=16, stride=1)).name("x3256_d0_b0").ctrl(x3300).srcCtx("GDA.scala:59:34:sigmaTile") // x3256 = SRAMNew(ArrayBuffer(Const(96), Const(96)))
    isAccum(x3256_d0_b0) = false
    bufferDepthOf(x3256_d0_b0) = 2
    val x3257 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16).name("x3257").ctrl(x3300).srcCtx("GDA.scala:60:21") // CounterNew(Const(0),Const(96),Const(1),Const(16))
    val x3258 = CounterChain(List(x3257)).name("x3258").ctrl(x3300).srcCtx("GDA.scala:60:29") // CounterChainNew(List(x3257))
    val x3272 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3258).name("x3272").ctrl(x3300).srcCtx("GDA.scala:60:29") // UnrolledForeach(List(b1973, b1908),x3258,Block(Const(())),List(List(b1978)),List(List(b1979)))
    val b1978 = CounterIter(x3257, None).name("b1978").ctrl(x3272) // b1978
    val b1979 = Const(true).name("b1979").ctrl(x3272) // b1979
    val x3259 = OpDef(op=BitAnd, inputs=List(b1979, b1973)).name("x3259").ctrl(x3272).srcCtx("UnrollingBase.scala:28:66") // And(b1979,b1973)
    val x3260 = OpDef(op=BitAnd, inputs=List(x3259, b1908)).name("x3260").ctrl(x3272).srcCtx("UnrollingBase.scala:28:66") // And(x3259,b1908)
    val x3261 = LoadBanks(List(x3203_d0_b0), List(b1972, b1978)).name("x3261").ctrl(x3272).srcCtx("GDA.scala:61:35") // ParSRAMLoad(x3203,List(List(b1972, b1978)),List(x3260))
    val x3262 = x3261 // x3262 = VectorApply(x3261,0)
    val x3263 = LoadBanks(List(x3202_d0_b0), List(b1972)).name("x3263").ctrl(x3272).srcCtx("GDA.scala:61:58") // SRAMLoad(x3202,ArrayBuffer(Const(32)),List(b1972),Const(0),x3260)
    val x3264 = OpDef(op=FixEql, inputs=List(x3263, Const(1))).name("x3264").ctrl(x3272).srcCtx("GDA.scala:61:63") // FixEql(x3263,Const(1))
    val x3265 = LoadBanks(List(x3161_d0_b0), List(b1978)).name("x3265").ctrl(x3272).srcCtx("GDA.scala:61:76") // ParSRAMLoad(x3161,List(List(b1978)),List(x3260))
    val x3266 = x3265 // x3266 = VectorApply(x3265,0)
    val x3267 = LoadBanks(List(x3160_d0_b0), List(b1978)).name("x3267").ctrl(x3272).srcCtx("GDA.scala:61:89") // ParSRAMLoad(x3160,List(List(b1978)),List(x3260))
    val x3268 = x3267 // x3268 = VectorApply(x3267,0)
    val x3269 = OpDef(op=MuxOp, inputs=List(x3264, x3266, x3268)).name("x3269").ctrl(x3272).srcCtx("GDA.scala:61:49") // Mux(x3264,x3266,x3268)
    val x3270 = OpDef(op=FixSub, inputs=List(x3262, x3269)).name("x3270").ctrl(x3272).srcCtx("GDA.scala:61:44") // FixSub(x3262,x3269)
    val x3271 = StoreBanks(List(x3255_d0_b0, x3255_d1_b0), List(b1978), x3270).name("x3271").ctrl(x3272).srcCtx("GDA.scala:61:25") // ParSRAMStore(x3255,List(List(b1978)),List(x3270),List(x3260))
    val x3273 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16).name("x3273").ctrl(x3300).srcCtx("GDA.scala:63:29") // CounterNew(Const(0),Const(96),Const(1),Const(16))
    val x3274 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1).name("x3274").ctrl(x3300).srcCtx("GDA.scala:63:21") // CounterNew(Const(0),Const(96),Const(1),Const(1))
    val x3275 = CounterChain(List(x3274,x3273)).name("x3275").ctrl(x3300).srcCtx("GDA.scala:63:37") // CounterChainNew(List(x3274, x3273))
    val x3284 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3275).name("x3284").ctrl(x3300).srcCtx("GDA.scala:63:37") // UnrolledForeach(List(b1973, b1908),x3275,Block(Const(())),List(List(b1997), List(b1998)),List(List(b1999), List(b2000)))
    val b1997 = CounterIter(x3274, Some(0)).name("b1997").ctrl(x3284) // b1997
    val b1999 = Const(true).name("b1999").ctrl(x3284) // b1999
    val b1998 = CounterIter(x3273, None).name("b1998").ctrl(x3284) // b1998
    val b2000 = Const(true).name("b2000").ctrl(x3284) // b2000
    val x3276 = OpDef(op=BitAnd, inputs=List(b1999, b2000)).name("x3276").ctrl(x3284).srcCtx("UnrollingBase.scala:28:66") // And(b1999,b2000)
    val x3277 = OpDef(op=BitAnd, inputs=List(b1973, b1908)).name("x3277").ctrl(x3284).srcCtx("UnrollingBase.scala:28:66") // And(b1973,b1908)
    val x3278 = OpDef(op=BitAnd, inputs=List(x3276, x3277)).name("x3278").ctrl(x3284).srcCtx("UnrollingBase.scala:28:66") // And(x3276,x3277)
    val x3279 = LoadBanks(List(x3255_d1_b0), List(b1997)).name("x3279").ctrl(x3284).srcCtx("GDA.scala:64:40") // SRAMLoad(x3255,ArrayBuffer(Const(96)),List(b1997),Const(0),x3278)
    val x3280 = LoadBanks(List(x3255_d0_b0), List(b1998)).name("x3280").ctrl(x3284).srcCtx("GDA.scala:64:54") // ParSRAMLoad(x3255,List(List(b1998)),List(x3278))
    val x3281 = x3280 // x3281 = VectorApply(x3280,0)
    val x3282 = OpDef(op=FixMul, inputs=List(x3279, x3281)).name("x3282").ctrl(x3284).srcCtx("GDA.scala:64:45") // FixMul(x3279,x3281)
    val x3283 = StoreBanks(List(x3256_d0_b0), List(b1997, b1998), x3282).name("x3283").ctrl(x3284).srcCtx("GDA.scala:64:31") // ParSRAMStore(x3256,List(List(b1997, b1998)),List(x3282),List(x3278))
    val x3285 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1).name("x3285").ctrl(x3300).srcCtx("GDA.scala:67:10") // CounterNew(Const(0),Const(96),Const(1),Const(1))
    val x3286 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1).name("x3286").ctrl(x3300).srcCtx("GDA.scala:67:10") // CounterNew(Const(0),Const(96),Const(1),Const(1))
    val x3287 = CounterChain(List(x3286,x3285)).name("x3287").ctrl(x3300).srcCtx("GDA.scala:67:10") // CounterChainNew(ArrayBuffer(x3286, x3285))
    val x3299 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3287).name("x3299").ctrl(x3300).srcCtx("GDA.scala:67:10") // UnrolledForeach(List(),x3287,Block(Const(())),ArrayBuffer(List(b2010), List(b2011)),ArrayBuffer(List(b2012), List(b2013)))
    val b2010 = CounterIter(x3286, Some(0)).name("b2010").ctrl(x3299) // b2010
    val b2012 = Const(true).name("b2012").ctrl(x3299) // b2012
    val b2011 = CounterIter(x3285, None).name("b2011").ctrl(x3299) // b2011
    val b2013 = Const(true).name("b2013").ctrl(x3299) // b2013
    val x3288 = OpDef(op=BitAnd, inputs=List(b2012, b2013)).name("x3288").ctrl(x3299).srcCtx("UnrollingBase.scala:28:66") // And(b2012,b2013)
    val x3289 = OpDef(op=BitAnd, inputs=List(x3288, b1908)).name("x3289").ctrl(x3299).srcCtx("UnrollingBase.scala:28:66") // And(x3288,b1908)
    val x3290 = LoadBanks(List(x3256_d0_b0), List(b2010, b2011)).name("x3290").ctrl(x3299).srcCtx("GDA.scala:67:10") // ParSRAMLoad(x3256,List(ArrayBuffer(b2010, b2011)),List(x3289))
    val x3291 = x3290 // x3291 = VectorApply(x3290,0)
    val x3292 = LoadBanks(List(x3252_d1_b0), List(b2010, b2011)).name("x3292").ctrl(x3299).srcCtx("GDA.scala:67:10") // ParSRAMLoad(x3252,List(ArrayBuffer(b2010, b2011)),List(x3289))
    val x3293 = x3292 // x3293 = VectorApply(x3292,0)
    val x3294 = OpDef(op=BitAnd, inputs=List(b1973, b1908)).name("x3294").ctrl(x3299).srcCtx("GDA.scala:67:10") // And(b1973,b1908)
    val x3295 = OpDef(op=BitAnd, inputs=List(x3294, x3289)).name("x3295").ctrl(x3299).srcCtx("GDA.scala:67:10") // And(x3294,x3289)
    val x3296 = OpDef(op=FixEql, inputs=List(b1972, Const(0))).name("x3296").ctrl(x3299).srcCtx("GDA.scala:67:10") // FixEql(b1972,Const(0))
    val x3297 = ReduceAccumOp(op=FixAdd, input=x3291, accum=x3293).name("x3297").ctrl(x3299).srcCtx("GDA.scala:67:12") // FixAdd(x3291,x3293)
    val x3298 = StoreBanks(List(x3252_d0_b0, x3252_d1_b0), List(b2010, b2011), x3297).name("x3298").ctrl(x3299).srcCtx("GDA.scala:67:10") // ParSRAMStore(x3252,List(ArrayBuffer(b2010, b2011)),List(x3297),List(x3289))
    val x3301 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1).name("x3301").ctrl(x3314).srcCtx("GDA.scala:68:8") // CounterNew(Const(0),Const(96),Const(1),Const(1))
    val x3302 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1).name("x3302").ctrl(x3314).srcCtx("GDA.scala:68:8") // CounterNew(Const(0),Const(96),Const(1),Const(1))
    val x3303 = CounterChain(List(x3302,x3301)).name("x3303").ctrl(x3314).srcCtx("GDA.scala:68:8") // CounterChainNew(ArrayBuffer(x3302, x3301))
    val x3313 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3303).name("x3313").ctrl(x3314).srcCtx("GDA.scala:68:8") // UnrolledForeach(List(),x3303,Block(Const(())),ArrayBuffer(List(b2027), List(b2028)),ArrayBuffer(List(b2029), List(b2030)))
    val b2027 = CounterIter(x3302, Some(0)).name("b2027").ctrl(x3313) // b2027
    val b2029 = Const(true).name("b2029").ctrl(x3313) // b2029
    val b2028 = CounterIter(x3301, None).name("b2028").ctrl(x3313) // b2028
    val b2030 = Const(true).name("b2030").ctrl(x3313) // b2030
    val x3304 = OpDef(op=BitAnd, inputs=List(b2029, b2030)).name("x3304").ctrl(x3313).srcCtx("UnrollingBase.scala:28:66") // And(b2029,b2030)
    val x3305 = LoadBanks(List(x3252_d0_b0), List(b2027, b2028)).name("x3305").ctrl(x3313).srcCtx("GDA.scala:68:8") // ParSRAMLoad(x3252,List(ArrayBuffer(b2027, b2028)),List(x3304))
    val x3306 = x3305 // x3306 = VectorApply(x3305,0)
    val x3307 = LoadBanks(List(x3198_d1_b0), List(b2027, b2028)).name("x3307").ctrl(x3313).srcCtx("GDA.scala:68:8") // ParSRAMLoad(x3198,List(ArrayBuffer(b2027, b2028)),List(x3304))
    val x3308 = x3307 // x3308 = VectorApply(x3307,0)
    val x3309 = OpDef(op=BitAnd, inputs=List(b1908, x3304)).name("x3309").ctrl(x3313).srcCtx("GDA.scala:68:8") // And(b1908,x3304)
    val x3310 = OpDef(op=FixEql, inputs=List(b1907, Const(0))).name("x3310").ctrl(x3313).srcCtx("GDA.scala:68:8") // FixEql(b1907,Const(0))
    val x3311 = ReduceAccumOp(op=FixAdd, input=x3306, accum=x3308).name("x3311").ctrl(x3313).srcCtx("GDA.scala:68:10") // FixAdd(x3306,x3308)
    val x3312 = StoreBanks(List(x3198_d0_b0, x3198_d1_b0), List(b2027, b2028), x3311).name("x3312").ctrl(x3313).srcCtx("GDA.scala:68:8") // ParSRAMStore(x3198,List(ArrayBuffer(b2027, b2028)),List(x3311),List(x3304))
    val x3315 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1).name("x3315").ctrl(x3343).srcCtx("GDA.scala:70:36") // CounterNew(Const(0),Const(96),Const(1),Const(1))
    val x3316 = CounterChain(List(x3315)).name("x3316").ctrl(x3343).srcCtx("GDA.scala:70:36") // CounterChainNew(List(x3315))
    val x3342 = LoopController(style=StreamPipe, level=OuterControl, cchain=x3316).name("x3342").ctrl(x3343).srcCtx("GDA.scala:70:36") // UnrolledForeach(List(Const(true)),x3316,Block(Const(())),List(List(b2044)),List(List(b2045)))
    val b2044 = CounterIter(x3315, Some(0)).name("b2044").ctrl(x3342) // b2044
    val b2045 = Const(true).name("b2045").ctrl(x3342) // b2045
    val b3411 = StreamOut(field="offset").name("b3411").ctrl(x3342).srcCtx("GDA.scala:70:36") // x3317 = StreamOutNew(BurstCmdBus)
    isAccum(b3411) = false
    bufferDepthOf(b3411) = 1
    val b3412 = StreamOut(field="size").name("b3412").ctrl(x3342).srcCtx("GDA.scala:70:36") // x3317 = StreamOutNew(BurstCmdBus)
    isAccum(b3412) = false
    bufferDepthOf(b3412) = 1
    val x3318 = StreamOut(field="data").name("x3318").ctrl(x3342).srcCtx("GDA.scala:70:36") // x3318 = StreamOutNew(BurstFullDataBus())
    isAccum(x3318) = false
    bufferDepthOf(x3318) = 1
    val x3319 = StreamIn(field="ack").name("x3319").ctrl(x3342).srcCtx("GDA.scala:70:36") // x3319 = StreamInNew(BurstAckBus)
    isAccum(x3319) = false
    bufferDepthOf(x3319) = 1
    val x3330 = UnitController(style=SeqPipe, level=InnerControl).name("x3330").ctrl(x3342).srcCtx("GDA.scala:70:36") // UnitPipe(List(b2045),Block(x3329))
    val x3320 = b2044 // FixConvert(b2044,TRUE,_32,_0) (Same Type. No op)
    val x3321 = OpDef(op=FixMul, inputs=List(x3320, Const(96))).name("x3321").ctrl(x3330).srcCtx("GDA.scala:70:36") // FixMul(x3320,Const(96))
    val x3322 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x3323 = OpDef(op=FixAdd, inputs=List(x3321, x3322)).name("x3323").ctrl(x3330).srcCtx("GDA.scala:70:36") // FixAdd(x3321,x3322)
    val x3324 = OpDef(op=FixSla, inputs=List(x3323, Const(2))).name("x3324").ctrl(x3330).srcCtx("GDA.scala:70:36") // FixLsh(x3323,Const(2))
    val x3325 = x3324 // FixConvert(x3324,TRUE,_64,_0)
    val x3326 = DramAddress(x3155).name("x3326").ctrl(x3330).srcCtx("GDA.scala:70:36") // GetDRAMAddress(x3155)
    val x3328_x3327 = OpDef(op=FixAdd, inputs=List(x3325, x3326)).name("x3328_x3327").ctrl(x3330).srcCtx("GDA.scala:70:36") // FixAdd(x3325,x3326)
    // x3328 = SimpleStruct(ArrayBuffer((offset,x3327), (size,Const(384)), (isLoad,Const(false))))
    val x3329_b3413_b3411 = WriteMem(b3411, x3328_x3327).name("x3329_b3413_b3411").ctrl(x3330).srcCtx("GDA.scala:70:36") // StreamWrite(x3317,x3328,b2045)
    val x3329_b3414_b3412 = WriteMem(b3412, Const(384)).name("x3329_b3414_b3412").ctrl(x3330).srcCtx("GDA.scala:70:36") // StreamWrite(x3317,x3328,b2045)
    val x3331 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16).name("x3331").ctrl(x3342).srcCtx("GDA.scala:70:36") // CounterNew(Const(0),Const(96),Const(1),Const(16))
    val x3332 = CounterChain(List(x3331)).name("x3332").ctrl(x3342).srcCtx("GDA.scala:70:36") // CounterChainNew(List(x3331))
    val x3338 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3332).name("x3338").ctrl(x3342).srcCtx("GDA.scala:70:36") // UnrolledForeach(List(b2045),x3332,Block(Const(())),List(List(b2062)),List(List(b2063)))
    val b2062 = CounterIter(x3331, None).name("b2062").ctrl(x3338) // b2062
    val b2063 = Const(true).name("b2063").ctrl(x3338) // b2063
    val x3333 = OpDef(op=BitAnd, inputs=List(b2063, b2045)).name("x3333").ctrl(x3338).srcCtx("UnrollingBase.scala:28:66") // And(b2063,b2045)
    val x3334 = LoadBanks(List(x3198_d0_b0), List(b2044, b2062)).name("x3334").ctrl(x3338).srcCtx("GDA.scala:70:36") // ParSRAMLoad(x3198,List(List(b2044, b2062)),List(x3333))
    val x3336_x3335 = x3334 // x3335 = VectorApply(x3334,0)
    // x3336 = SimpleStruct(ArrayBuffer((_1,x3335), (_2,Const(true))))
    val x3337_x3337_x3318 = WriteMem(x3318, x3336_x3335).name("x3337_x3337_x3318").ctrl(x3338).srcCtx("GDA.scala:70:36") // ParStreamWrite(x3318,List(x3336),List(x3333))
    val x3339 = FringeDenseStore(dram=List(x3155), cmdStream=List(b3411, b3412), dataStream=List(x3318), ackStream=List(x3319)).name("x3339").ctrl(x3342).srcCtx("GDA.scala:70:36") // FringeDenseStore(x3155,x3317,x3318,x3319)
    val x3341 = UnitController(style=SeqPipe, level=InnerControl).name("x3341").ctrl(x3342).srcCtx("GDA.scala:70:36") // UnitPipe(List(b2045),Block(Const(())))
    val x3340_x3340 = ReadMem(x3319).name("x3340_x3340").ctrl(x3341).srcCtx("GDA.scala:70:36") // StreamRead(x3319,b2045)
    
  }
}
