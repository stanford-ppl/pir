import pir._
import pir.node._
import arch._
import prism.enums._

object GDA extends PIRApp {
  def main(implicit design:PIRDesign) = {
    import design.pirmeta._
    val x3149 = ArgIn(init=0).name("x3149").ctrl(top).srcCtx("GDA.scala:22:18:R") // ArgInNew(Const(0))
    isAccum(x3149) = false
    bufferDepthOf(x3149) = 1
    boundOf(x3149) = 1024
    val x3151 = ReadMem(x3149).name("x3151").ctrl(top).srcCtx("GDA.scala:25:21") // RegRead(x3149)
    val x3152 = DRAM(dims=List(x3151, Const(96))).name("x3152").ctrl(top).srcCtx("GDA.scala:25:20:x") // x3152 = DRAMNew(ArrayBuffer(x3151, Const(96)),Const(0))
    val x3153 = ReadMem(x3149).name("x3153").ctrl(top).srcCtx("GDA.scala:26:23") // RegRead(x3149)
    val x3154 = DRAM(dims=List(x3153)).name("x3154").ctrl(top).srcCtx("GDA.scala:26:22:y") // x3154 = DRAMNew(ArrayBuffer(x3153),Const(0))
    val x3155 = DRAM(dims=List(Const(96))).name("x3155").ctrl(top).srcCtx("GDA.scala:27:22:mu0") // x3155 = DRAMNew(ArrayBuffer(Const(96)),Const(0))
    val x3156 = DRAM(dims=List(Const(96))).name("x3156").ctrl(top).srcCtx("GDA.scala:28:22:mu1") // x3156 = DRAMNew(ArrayBuffer(Const(96)),Const(0))
    val x3157 = DRAM(dims=List(Const(96), Const(96))).name("x3157").ctrl(top).srcCtx("GDA.scala:29:24:sigma") // x3157 = DRAMNew(ArrayBuffer(Const(96), Const(96)),Const(0))
    val x3347 = UnitController(style=SeqPipe, level=OuterControl).name("x3347").ctrl(top).srcCtx("GDA.scala:36:11") // Hwblock(Block(Const(())),false)
    val x3162_d0_b0 = SRAM(size=96, banking=Strided(banks=16, stride=1)).name("x3162_d0_b0").ctrl(x3347).srcCtx("GDA.scala:37:28:mu0Tile") // x3162 = SRAMNew(ArrayBuffer(Const(96)))
    isAccum(x3162_d0_b0) = false
    bufferDepthOf(x3162_d0_b0) = 1
    val x3163_d0_b0 = SRAM(size=96, banking=Strided(banks=16, stride=1)).name("x3163_d0_b0").ctrl(x3347).srcCtx("GDA.scala:38:28:mu1Tile") // x3163 = SRAMNew(ArrayBuffer(Const(96)))
    isAccum(x3163_d0_b0) = false
    bufferDepthOf(x3163_d0_b0) = 1
    val x3200 = UnitController(style=ForkJoin, level=OuterControl).name("x3200").ctrl(x3347).srcCtx("GDA.scala:39:16") // ParallelPipe(List(Const(true)),Block(Const(())))
    val x3181 = UnitController(style=StreamPipe, level=OuterControl).name("x3181").ctrl(x3200).srcCtx("GDA.scala:40:17") // UnitPipe(List(Const(true)),Block(Const(())))
    val b3399 = StreamOut(field="offset").name("b3399").ctrl(x3181).srcCtx("GDA.scala:40:17") // x3164 = StreamOutNew(BurstCmdBus)
    isAccum(b3399) = false
    bufferDepthOf(b3399) = 1
    val b3400 = StreamOut(field="size").name("b3400").ctrl(x3181).srcCtx("GDA.scala:40:17") // x3164 = StreamOutNew(BurstCmdBus)
    isAccum(b3400) = false
    bufferDepthOf(b3400) = 1
    val x3165 = StreamIn(field="data").name("x3165").ctrl(x3181).srcCtx("GDA.scala:40:17") // x3165 = StreamInNew(BurstDataBus())
    isAccum(x3165) = false
    bufferDepthOf(x3165) = 1
    val x3173 = UnitController(style=SeqPipe, level=InnerControl).name("x3173").ctrl(x3181).srcCtx("GDA.scala:40:17") // UnitPipe(List(Const(true)),Block(x3172))
    val x3166 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x3167 = OpDef(op=FixSla, inputs=List(x3166, Const(2))).name("x3167").ctrl(x3173).srcCtx("GDA.scala:40:17") // FixLsh(x3166,Const(2))
    val x3168 = x3167 // FixConvert(x3167,TRUE,_64,_0)
    val x3169 = DramAddress(x3155).name("x3169").ctrl(x3173).srcCtx("GDA.scala:40:17") // GetDRAMAddress(x3155)
    val x3171_x3170 = OpDef(op=FixAdd, inputs=List(x3168, x3169)).name("x3171_x3170").ctrl(x3173).srcCtx("GDA.scala:40:17") // FixAdd(x3168,x3169)
    // x3171 = SimpleStruct(ArrayBuffer((offset,x3170), (size,Const(384)), (isLoad,Const(true))))
    val x3172_b3401_b3399 = WriteMem(b3399, x3171_x3170).name("x3172_b3401_b3399").ctrl(x3173).srcCtx("GDA.scala:40:17") // StreamWrite(x3164,x3171,Const(true))
    val x3172_b3402_b3400 = WriteMem(b3400, Const(384)).name("x3172_b3402_b3400").ctrl(x3173).srcCtx("GDA.scala:40:17") // StreamWrite(x3164,x3171,Const(true))
    val x3174 = FringeDenseLoad(dram=List(x3155), cmdStream=List(b3399, b3400), dataStream=List(x3165)).name("x3174").ctrl(x3181).srcCtx("GDA.scala:40:17") // FringeDenseLoad(x3155,x3164,x3165)
    val x3175 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16).name("x3175").ctrl(x3181).srcCtx("GDA.scala:40:17") // CounterNew(Const(0),Const(96),Const(1),Const(16))
    val x3176 = CounterChain(List(x3175)).name("x3176").ctrl(x3181).srcCtx("GDA.scala:40:17") // CounterChainNew(List(x3175))
    val x3180 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3176).name("x3180").ctrl(x3181).srcCtx("GDA.scala:40:17") // UnrolledForeach(List(Const(true)),x3176,Block(Const(())),List(List(b1872)),List(List(b1873)))
    val b1872 = CounterIter(x3175, None).name("b1872").ctrl(x3180) // b1872
    val b1873 = Const(true).name("b1873").ctrl(x3180) // b1873
    val x3177_x3177 = ReadMem(x3165).name("x3177_x3177").ctrl(x3180).srcCtx("GDA.scala:40:17") // ParStreamRead(x3165,List(b1873))
    val x3178_x3178 = x3177_x3177 // x3178 = VectorApply(x3177,0)
    val x3179 = StoreBanks(List(x3162_d0_b0), List(b1872), x3178_x3178).name("x3179").ctrl(x3180).srcCtx("GDA.scala:40:17") // ParSRAMStore(x3162,List(List(b1872)),List(x3178),List(b1873))
    val x3199 = UnitController(style=StreamPipe, level=OuterControl).name("x3199").ctrl(x3200).srcCtx("GDA.scala:41:17") // UnitPipe(List(Const(true)),Block(Const(())))
    val b3403 = StreamOut(field="offset").name("b3403").ctrl(x3199).srcCtx("GDA.scala:41:17") // x3182 = StreamOutNew(BurstCmdBus)
    isAccum(b3403) = false
    bufferDepthOf(b3403) = 1
    val b3404 = StreamOut(field="size").name("b3404").ctrl(x3199).srcCtx("GDA.scala:41:17") // x3182 = StreamOutNew(BurstCmdBus)
    isAccum(b3404) = false
    bufferDepthOf(b3404) = 1
    val x3183 = StreamIn(field="data").name("x3183").ctrl(x3199).srcCtx("GDA.scala:41:17") // x3183 = StreamInNew(BurstDataBus())
    isAccum(x3183) = false
    bufferDepthOf(x3183) = 1
    val x3191 = UnitController(style=SeqPipe, level=InnerControl).name("x3191").ctrl(x3199).srcCtx("GDA.scala:41:17") // UnitPipe(List(Const(true)),Block(x3190))
    val x3184 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x3185 = OpDef(op=FixSla, inputs=List(x3184, Const(2))).name("x3185").ctrl(x3191).srcCtx("GDA.scala:41:17") // FixLsh(x3184,Const(2))
    val x3186 = x3185 // FixConvert(x3185,TRUE,_64,_0)
    val x3187 = DramAddress(x3156).name("x3187").ctrl(x3191).srcCtx("GDA.scala:41:17") // GetDRAMAddress(x3156)
    val x3189_x3188 = OpDef(op=FixAdd, inputs=List(x3186, x3187)).name("x3189_x3188").ctrl(x3191).srcCtx("GDA.scala:41:17") // FixAdd(x3186,x3187)
    // x3189 = SimpleStruct(ArrayBuffer((offset,x3188), (size,Const(384)), (isLoad,Const(true))))
    val x3190_b3405_b3403 = WriteMem(b3403, x3189_x3188).name("x3190_b3405_b3403").ctrl(x3191).srcCtx("GDA.scala:41:17") // StreamWrite(x3182,x3189,Const(true))
    val x3190_b3406_b3404 = WriteMem(b3404, Const(384)).name("x3190_b3406_b3404").ctrl(x3191).srcCtx("GDA.scala:41:17") // StreamWrite(x3182,x3189,Const(true))
    val x3192 = FringeDenseLoad(dram=List(x3156), cmdStream=List(b3403, b3404), dataStream=List(x3183)).name("x3192").ctrl(x3199).srcCtx("GDA.scala:41:17") // FringeDenseLoad(x3156,x3182,x3183)
    val x3193 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16).name("x3193").ctrl(x3199).srcCtx("GDA.scala:41:17") // CounterNew(Const(0),Const(96),Const(1),Const(16))
    val x3194 = CounterChain(List(x3193)).name("x3194").ctrl(x3199).srcCtx("GDA.scala:41:17") // CounterChainNew(List(x3193))
    val x3198 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3194).name("x3198").ctrl(x3199).srcCtx("GDA.scala:41:17") // UnrolledForeach(List(Const(true)),x3194,Block(Const(())),List(List(b1892)),List(List(b1893)))
    val b1892 = CounterIter(x3193, None).name("b1892").ctrl(x3198) // b1892
    val b1893 = Const(true).name("b1893").ctrl(x3198) // b1893
    val x3195_x3195 = ReadMem(x3183).name("x3195_x3195").ctrl(x3198).srcCtx("GDA.scala:41:17") // ParStreamRead(x3183,List(b1893))
    val x3196_x3196 = x3195_x3195 // x3196 = VectorApply(x3195,0)
    val x3197 = StoreBanks(List(x3163_d0_b0), List(b1892), x3196_x3196).name("x3197").ctrl(x3198).srcCtx("GDA.scala:41:17") // ParSRAMStore(x3163,List(List(b1892)),List(x3196),List(b1893))
    val x3201_d0_b0 = SRAM(size=9216, banking=Strided(banks=16, stride=1)).name("x3201_d0_b0").ctrl(x3347).srcCtx("GDA.scala:44:29:sigmaOut") // x3201 = SRAMNew(ArrayBuffer(Const(96), Const(96)))
    isAccum(x3201_d0_b0) = false
    bufferDepthOf(x3201_d0_b0) = 1
    val x3201_d1_b0 = SRAM(size=9216, banking=Strided(banks=1, stride=96)).name("x3201_d1_b0").ctrl(x3347).srcCtx("GDA.scala:44:29:sigmaOut") // x3201 = SRAMNew(ArrayBuffer(Const(96), Const(96)))
    isAccum(x3201_d1_b0) = true
    bufferDepthOf(x3201_d1_b0) = 1
    val x3202 = ReadMem(x3149).name("x3202").ctrl(x3347).srcCtx("GDA.scala:46:27") // RegRead(x3149)
    val x3203 = Counter(min=Const(0), max=x3202, step=Const(32), par=1).name("x3203").ctrl(x3347).srcCtx("GDA.scala:46:35") // CounterNew(Const(0),x3202,Const(32),Const(1))
    val x3204 = CounterChain(List(x3203)).name("x3204").ctrl(x3347).srcCtx("GDA.scala:68:8") // CounterChainNew(List(x3203))
    val x3318 = LoopController(style=MetaPipe, level=OuterControl, cchain=x3204).name("x3318").ctrl(x3347).srcCtx("GDA.scala:68:8") // UnrolledReduce(List(Const(true)),x3204,x3201,Block((x3201) => Const(())),List(List(b1907)),List(List(b1908)))
    val b1907 = CounterIter(x3203, Some(0)).name("b1907").ctrl(x3318) // b1907
    val b1908 = Const(true).name("b1908").ctrl(x3318) // b1908
    val x3205_d0_b0 = SRAM(size=32, banking=Strided(banks=16, stride=1)).name("x3205_d0_b0").ctrl(x3318).srcCtx("GDA.scala:47:33:gdaYtile") // x3205 = SRAMNew(ArrayBuffer(Const(32)))
    isAccum(x3205_d0_b0) = false
    bufferDepthOf(x3205_d0_b0) = 2
    val x3206_d0_b0 = SRAM(size=3072, banking=Strided(banks=16, stride=1)).name("x3206_d0_b0").ctrl(x3318).srcCtx("GDA.scala:48:31:gdaXtile") // x3206 = SRAMNew(ArrayBuffer(Const(32), Const(96)))
    isAccum(x3206_d0_b0) = false
    bufferDepthOf(x3206_d0_b0) = 2
    val x3255 = UnitController(style=ForkJoin, level=OuterControl).name("x3255").ctrl(x3318).srcCtx("GDA.scala:50:18") // ParallelPipe(List(b1908),Block(Const(())))
    val x3208 = UnitController(style=SeqPipe, level=InnerControl).name("x3208").ctrl(x3255).srcCtx("GDA.scala:50:18") // UnitPipe(List(b1908),Block(Const(())))
    val x3207 = OpDef(op=FixAdd, inputs=List(b1907, Const(32))).name("x3207").ctrl(x3208).srcCtx("GDA.scala:51:34") // FixAdd(b1907,Const(32))
    val x3227 = UnitController(style=StreamPipe, level=OuterControl).name("x3227").ctrl(x3255).srcCtx("GDA.scala:51:20") // UnitPipe(List(b1908),Block(Const(())))
    val b3407 = StreamOut(field="offset").name("b3407").ctrl(x3227).srcCtx("GDA.scala:51:20") // x3209 = StreamOutNew(BurstCmdBus)
    isAccum(b3407) = false
    bufferDepthOf(b3407) = 1
    val b3408 = StreamOut(field="size").name("b3408").ctrl(x3227).srcCtx("GDA.scala:51:20") // x3209 = StreamOutNew(BurstCmdBus)
    isAccum(b3408) = false
    bufferDepthOf(b3408) = 1
    val x3210 = StreamIn(field="data").name("x3210").ctrl(x3227).srcCtx("GDA.scala:51:20") // x3210 = StreamInNew(BurstDataBus())
    isAccum(x3210) = false
    bufferDepthOf(x3210) = 1
    val x3218 = UnitController(style=SeqPipe, level=InnerControl).name("x3218").ctrl(x3227).srcCtx("GDA.scala:51:20") // UnitPipe(List(b1908),Block(x3217))
    val x3211 = b1907 // FixConvert(b1907,TRUE,_32,_0) (Same Type. No op)
    val x3212 = OpDef(op=FixSla, inputs=List(x3211, Const(2))).name("x3212").ctrl(x3218).srcCtx("GDA.scala:51:20") // FixLsh(x3211,Const(2))
    val x3213 = x3212 // FixConvert(x3212,TRUE,_64,_0)
    val x3214 = DramAddress(x3154).name("x3214").ctrl(x3218).srcCtx("GDA.scala:51:20") // GetDRAMAddress(x3154)
    val x3216_x3215 = OpDef(op=FixAdd, inputs=List(x3213, x3214)).name("x3216_x3215").ctrl(x3218).srcCtx("GDA.scala:51:20") // FixAdd(x3213,x3214)
    // x3216 = SimpleStruct(ArrayBuffer((offset,x3215), (size,Const(128)), (isLoad,Const(true))))
    val x3217_b3409_b3407 = WriteMem(b3407, x3216_x3215).name("x3217_b3409_b3407").ctrl(x3218).srcCtx("GDA.scala:51:20") // StreamWrite(x3209,x3216,b1908)
    val x3217_b3410_b3408 = WriteMem(b3408, Const(128)).name("x3217_b3410_b3408").ctrl(x3218).srcCtx("GDA.scala:51:20") // StreamWrite(x3209,x3216,b1908)
    val x3219 = FringeDenseLoad(dram=List(x3154), cmdStream=List(b3407, b3408), dataStream=List(x3210)).name("x3219").ctrl(x3227).srcCtx("GDA.scala:51:20") // FringeDenseLoad(x3154,x3209,x3210)
    val x3220 = Counter(min=Const(0), max=Const(32), step=Const(1), par=16).name("x3220").ctrl(x3227).srcCtx("GDA.scala:51:20") // CounterNew(Const(0),Const(32),Const(1),Const(16))
    val x3221 = CounterChain(List(x3220)).name("x3221").ctrl(x3227).srcCtx("GDA.scala:51:20") // CounterChainNew(List(x3220))
    val x3226 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3221).name("x3226").ctrl(x3227).srcCtx("GDA.scala:51:20") // UnrolledForeach(List(b1908),x3221,Block(Const(())),List(List(b1926)),List(List(b1927)))
    val b1926 = CounterIter(x3220, None).name("b1926").ctrl(x3226) // b1926
    val b1927 = Const(true).name("b1927").ctrl(x3226) // b1927
    val x3222 = OpDef(op=BitAnd, inputs=List(b1927, b1908)).name("x3222").ctrl(x3226).srcCtx("UnrollingBase.scala:28:66") // And(b1927,b1908)
    val x3223_x3223 = ReadMem(x3210).name("x3223_x3223").ctrl(x3226).srcCtx("GDA.scala:51:20") // ParStreamRead(x3210,List(x3222))
    val x3224_x3224 = x3223_x3223 // x3224 = VectorApply(x3223,0)
    val x3225 = StoreBanks(List(x3205_d0_b0), List(b1926), x3224_x3224).name("x3225").ctrl(x3226).srcCtx("GDA.scala:51:20") // ParSRAMStore(x3205,List(List(b1926)),List(x3224),List(x3222))
    val x3228 = Counter(min=Const(0), max=Const(32), step=Const(1), par=1).name("x3228").ctrl(x3255).srcCtx("GDA.scala:52:20") // CounterNew(Const(0),Const(32),Const(1),Const(1))
    val x3229 = CounterChain(List(x3228)).name("x3229").ctrl(x3255).srcCtx("GDA.scala:52:20") // CounterChainNew(List(x3228))
    val x3254 = LoopController(style=StreamPipe, level=OuterControl, cchain=x3229).name("x3254").ctrl(x3255).srcCtx("GDA.scala:52:20") // UnrolledForeach(List(b1908),x3229,Block(Const(())),List(List(b1936)),List(List(b1937)))
    val b1936 = CounterIter(x3228, Some(0)).name("b1936").ctrl(x3254) // b1936
    val b1937 = Const(true).name("b1937").ctrl(x3254) // b1937
    val b3411 = StreamOut(field="offset").name("b3411").ctrl(x3254).srcCtx("GDA.scala:52:20") // x3230 = StreamOutNew(BurstCmdBus)
    isAccum(b3411) = false
    bufferDepthOf(b3411) = 1
    val b3412 = StreamOut(field="size").name("b3412").ctrl(x3254).srcCtx("GDA.scala:52:20") // x3230 = StreamOutNew(BurstCmdBus)
    isAccum(b3412) = false
    bufferDepthOf(b3412) = 1
    val x3231 = StreamIn(field="data").name("x3231").ctrl(x3254).srcCtx("GDA.scala:52:20") // x3231 = StreamInNew(BurstDataBus())
    isAccum(x3231) = false
    bufferDepthOf(x3231) = 1
    val x3244 = UnitController(style=SeqPipe, level=InnerControl).name("x3244").ctrl(x3254).srcCtx("GDA.scala:52:20") // UnitPipe(List(b1937, b1908),Block(x3243))
    val x3232 = OpDef(op=FixAdd, inputs=List(b1907, b1936)).name("x3232").ctrl(x3244).srcCtx("GDA.scala:52:20") // FixAdd(b1907,b1936)
    val x3233 = x3232 // FixConvert(x3232,TRUE,_32,_0) (Same Type. No op)
    val x3234 = OpDef(op=FixMul, inputs=List(x3233, Const(96))).name("x3234").ctrl(x3244).srcCtx("GDA.scala:52:20") // FixMul(x3233,Const(96))
    val x3235 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x3236 = OpDef(op=FixAdd, inputs=List(x3234, x3235)).name("x3236").ctrl(x3244).srcCtx("GDA.scala:52:20") // FixAdd(x3234,x3235)
    val x3237 = OpDef(op=FixSla, inputs=List(x3236, Const(2))).name("x3237").ctrl(x3244).srcCtx("GDA.scala:52:20") // FixLsh(x3236,Const(2))
    val x3238 = x3237 // FixConvert(x3237,TRUE,_64,_0)
    val x3239 = DramAddress(x3152).name("x3239").ctrl(x3244).srcCtx("GDA.scala:52:20") // GetDRAMAddress(x3152)
    val x3241_x3240 = OpDef(op=FixAdd, inputs=List(x3238, x3239)).name("x3241_x3240").ctrl(x3244).srcCtx("GDA.scala:52:20") // FixAdd(x3238,x3239)
    // x3241 = SimpleStruct(ArrayBuffer((offset,x3240), (size,Const(384)), (isLoad,Const(true))))
    val x3242 = OpDef(op=BitAnd, inputs=List(b1937, b1908)).name("x3242").ctrl(x3244).srcCtx("UnrollingBase.scala:28:66") // And(b1937,b1908)
    val x3243_b3413_b3411 = WriteMem(b3411, x3241_x3240).name("x3243_b3413_b3411").ctrl(x3244).srcCtx("GDA.scala:52:20") // StreamWrite(x3230,x3241,x3242)
    val x3243_b3414_b3412 = WriteMem(b3412, Const(384)).name("x3243_b3414_b3412").ctrl(x3244).srcCtx("GDA.scala:52:20") // StreamWrite(x3230,x3241,x3242)
    val x3245 = FringeDenseLoad(dram=List(x3152), cmdStream=List(b3411, b3412), dataStream=List(x3231)).name("x3245").ctrl(x3254).srcCtx("GDA.scala:52:20") // FringeDenseLoad(x3152,x3230,x3231)
    val x3246 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16).name("x3246").ctrl(x3254).srcCtx("GDA.scala:52:20") // CounterNew(Const(0),Const(96),Const(1),Const(16))
    val x3247 = CounterChain(List(x3246)).name("x3247").ctrl(x3254).srcCtx("GDA.scala:52:20") // CounterChainNew(List(x3246))
    val x3253 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3247).name("x3253").ctrl(x3254).srcCtx("GDA.scala:52:20") // UnrolledForeach(List(b1937, b1908),x3247,Block(Const(())),List(List(b1956)),List(List(b1957)))
    val b1956 = CounterIter(x3246, None).name("b1956").ctrl(x3253) // b1956
    val b1957 = Const(true).name("b1957").ctrl(x3253) // b1957
    val x3248 = OpDef(op=BitAnd, inputs=List(b1957, b1937)).name("x3248").ctrl(x3253).srcCtx("UnrollingBase.scala:28:66") // And(b1957,b1937)
    val x3249 = OpDef(op=BitAnd, inputs=List(x3248, b1908)).name("x3249").ctrl(x3253).srcCtx("UnrollingBase.scala:28:66") // And(x3248,b1908)
    val x3250_x3250 = ReadMem(x3231).name("x3250_x3250").ctrl(x3253).srcCtx("GDA.scala:52:20") // ParStreamRead(x3231,List(x3249))
    val x3251_x3251 = x3250_x3250 // x3251 = VectorApply(x3250,0)
    val x3252 = StoreBanks(List(x3206_d0_b0), List(b1936, b1956), x3251_x3251).name("x3252").ctrl(x3253).srcCtx("GDA.scala:52:20") // ParSRAMStore(x3206,List(List(b1936, b1956)),List(x3251),List(x3249))
    val x3256_d0_b0 = SRAM(size=9216, banking=Strided(banks=1, stride=96)).name("x3256_d0_b0").ctrl(x3318).srcCtx("GDA.scala:55:31:sigmaBlk") // x3256 = SRAMNew(ArrayBuffer(Const(96), Const(96)))
    isAccum(x3256_d0_b0) = false
    bufferDepthOf(x3256_d0_b0) = 2
    val x3256_d1_b0 = SRAM(size=9216, banking=Strided(banks=1, stride=96)).name("x3256_d1_b0").ctrl(x3318).srcCtx("GDA.scala:55:31:sigmaBlk") // x3256 = SRAMNew(ArrayBuffer(Const(96), Const(96)))
    isAccum(x3256_d1_b0) = true
    bufferDepthOf(x3256_d1_b0) = 1
    val x3257 = Counter(min=Const(0), max=Const(32), step=Const(1), par=1).name("x3257").ctrl(x3318).srcCtx("GDA.scala:57:32") // CounterNew(Const(0),Const(32),Const(1),Const(1))
    val x3258 = CounterChain(List(x3257)).name("x3258").ctrl(x3318).srcCtx("GDA.scala:67:10") // CounterChainNew(List(x3257))
    val x3304 = LoopController(style=MetaPipe, level=OuterControl, cchain=x3258).name("x3304").ctrl(x3318).srcCtx("GDA.scala:67:10") // UnrolledReduce(List(b1908),x3258,x3256,Block((x3256) => Const(())),List(List(b1972)),List(List(b1973)))
    val b1972 = CounterIter(x3257, Some(0)).name("b1972").ctrl(x3304) // b1972
    val b1973 = Const(true).name("b1973").ctrl(x3304) // b1973
    val x3259_d0_b0 = SRAM(size=96, banking=Strided(banks=16, stride=1)).name("x3259_d0_b0").ctrl(x3304).srcCtx("GDA.scala:58:32:subTile") // x3259 = SRAMNew(ArrayBuffer(Const(96)))
    isAccum(x3259_d0_b0) = false
    bufferDepthOf(x3259_d0_b0) = 2
    val x3259_d1_b0 = SRAM(size=96, banking=Strided(banks=16, stride=1)).name("x3259_d1_b0").ctrl(x3304).srcCtx("GDA.scala:58:32:subTile") // x3259 = SRAMNew(ArrayBuffer(Const(96)))
    isAccum(x3259_d1_b0) = false
    bufferDepthOf(x3259_d1_b0) = 2
    val x3260_d0_b0 = SRAM(size=9216, banking=Strided(banks=16, stride=1)).name("x3260_d0_b0").ctrl(x3304).srcCtx("GDA.scala:59:34:sigmaTile") // x3260 = SRAMNew(ArrayBuffer(Const(96), Const(96)))
    isAccum(x3260_d0_b0) = false
    bufferDepthOf(x3260_d0_b0) = 2
    val x3261 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16).name("x3261").ctrl(x3304).srcCtx("GDA.scala:60:21") // CounterNew(Const(0),Const(96),Const(1),Const(16))
    val x3262 = CounterChain(List(x3261)).name("x3262").ctrl(x3304).srcCtx("GDA.scala:60:29") // CounterChainNew(List(x3261))
    val x3276 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3262).name("x3276").ctrl(x3304).srcCtx("GDA.scala:60:29") // UnrolledForeach(List(b1973, b1908),x3262,Block(Const(())),List(List(b1978)),List(List(b1979)))
    val b1978 = CounterIter(x3261, None).name("b1978").ctrl(x3276) // b1978
    val b1979 = Const(true).name("b1979").ctrl(x3276) // b1979
    val x3263 = OpDef(op=BitAnd, inputs=List(b1979, b1973)).name("x3263").ctrl(x3276).srcCtx("UnrollingBase.scala:28:66") // And(b1979,b1973)
    val x3264 = OpDef(op=BitAnd, inputs=List(x3263, b1908)).name("x3264").ctrl(x3276).srcCtx("UnrollingBase.scala:28:66") // And(x3263,b1908)
    val x3265 = LoadBanks(List(x3206_d0_b0), List(b1972, b1978)).name("x3265").ctrl(x3276).srcCtx("GDA.scala:61:35") // ParSRAMLoad(x3206,List(List(b1972, b1978)),List(x3264))
    val x3266 = x3265 // x3266 = VectorApply(x3265,0)
    val x3267 = LoadBanks(List(x3205_d0_b0), List(b1972)).name("x3267").ctrl(x3276).srcCtx("GDA.scala:61:58") // SRAMLoad(x3205,ArrayBuffer(Const(32)),List(b1972),Const(0),x3264)
    val x3268 = OpDef(op=FixEql, inputs=List(x3267, Const(1))).name("x3268").ctrl(x3276).srcCtx("GDA.scala:61:63") // FixEql(x3267,Const(1))
    val x3269 = LoadBanks(List(x3163_d0_b0), List(b1978)).name("x3269").ctrl(x3276).srcCtx("GDA.scala:61:76") // ParSRAMLoad(x3163,List(List(b1978)),List(x3264))
    val x3270 = x3269 // x3270 = VectorApply(x3269,0)
    val x3271 = LoadBanks(List(x3162_d0_b0), List(b1978)).name("x3271").ctrl(x3276).srcCtx("GDA.scala:61:89") // ParSRAMLoad(x3162,List(List(b1978)),List(x3264))
    val x3272 = x3271 // x3272 = VectorApply(x3271,0)
    val x3273 = OpDef(op=MuxOp, inputs=List(x3268, x3270, x3272)).name("x3273").ctrl(x3276).srcCtx("GDA.scala:61:49") // Mux(x3268,x3270,x3272)
    val x3274 = OpDef(op=FixSub, inputs=List(x3266, x3273)).name("x3274").ctrl(x3276).srcCtx("GDA.scala:61:44") // FixSub(x3266,x3273)
    val x3275 = StoreBanks(List(x3259_d0_b0, x3259_d1_b0), List(b1978), x3274).name("x3275").ctrl(x3276).srcCtx("GDA.scala:61:25") // ParSRAMStore(x3259,List(List(b1978)),List(x3274),List(x3264))
    val x3277 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16).name("x3277").ctrl(x3304).srcCtx("GDA.scala:63:29") // CounterNew(Const(0),Const(96),Const(1),Const(16))
    val x3278 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1).name("x3278").ctrl(x3304).srcCtx("GDA.scala:63:21") // CounterNew(Const(0),Const(96),Const(1),Const(1))
    val x3279 = CounterChain(List(x3278,x3277)).name("x3279").ctrl(x3304).srcCtx("GDA.scala:63:37") // CounterChainNew(List(x3278, x3277))
    val x3288 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3279).name("x3288").ctrl(x3304).srcCtx("GDA.scala:63:37") // UnrolledForeach(List(b1973, b1908),x3279,Block(Const(())),List(List(b1997), List(b1998)),List(List(b1999), List(b2000)))
    val b1997 = CounterIter(x3278, Some(0)).name("b1997").ctrl(x3288) // b1997
    val b1999 = Const(true).name("b1999").ctrl(x3288) // b1999
    val b1998 = CounterIter(x3277, None).name("b1998").ctrl(x3288) // b1998
    val b2000 = Const(true).name("b2000").ctrl(x3288) // b2000
    val x3280 = OpDef(op=BitAnd, inputs=List(b1999, b2000)).name("x3280").ctrl(x3288).srcCtx("UnrollingBase.scala:28:66") // And(b1999,b2000)
    val x3281 = OpDef(op=BitAnd, inputs=List(b1973, b1908)).name("x3281").ctrl(x3288).srcCtx("UnrollingBase.scala:28:66") // And(b1973,b1908)
    val x3282 = OpDef(op=BitAnd, inputs=List(x3280, x3281)).name("x3282").ctrl(x3288).srcCtx("UnrollingBase.scala:28:66") // And(x3280,x3281)
    val x3283 = LoadBanks(List(x3259_d1_b0), List(b1997)).name("x3283").ctrl(x3288).srcCtx("GDA.scala:64:40") // SRAMLoad(x3259,ArrayBuffer(Const(96)),List(b1997),Const(0),x3282)
    val x3284 = LoadBanks(List(x3259_d0_b0), List(b1998)).name("x3284").ctrl(x3288).srcCtx("GDA.scala:64:54") // ParSRAMLoad(x3259,List(List(b1998)),List(x3282))
    val x3285 = x3284 // x3285 = VectorApply(x3284,0)
    val x3286 = OpDef(op=FixMul, inputs=List(x3283, x3285)).name("x3286").ctrl(x3288).srcCtx("GDA.scala:64:45") // FixMul(x3283,x3285)
    val x3287 = StoreBanks(List(x3260_d0_b0), List(b1997, b1998), x3286).name("x3287").ctrl(x3288).srcCtx("GDA.scala:64:31") // ParSRAMStore(x3260,List(List(b1997, b1998)),List(x3286),List(x3282))
    val x3289 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1).name("x3289").ctrl(x3304).srcCtx("GDA.scala:67:10") // CounterNew(Const(0),Const(96),Const(1),Const(1))
    val x3290 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1).name("x3290").ctrl(x3304).srcCtx("GDA.scala:67:10") // CounterNew(Const(0),Const(96),Const(1),Const(1))
    val x3291 = CounterChain(List(x3290,x3289)).name("x3291").ctrl(x3304).srcCtx("GDA.scala:67:10") // CounterChainNew(ArrayBuffer(x3290, x3289))
    val x3303 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3291).name("x3303").ctrl(x3304).srcCtx("GDA.scala:67:10") // UnrolledForeach(List(),x3291,Block(Const(())),ArrayBuffer(List(b2010), List(b2011)),ArrayBuffer(List(b2012), List(b2013)))
    val b2010 = CounterIter(x3290, Some(0)).name("b2010").ctrl(x3303) // b2010
    val b2012 = Const(true).name("b2012").ctrl(x3303) // b2012
    val b2011 = CounterIter(x3289, None).name("b2011").ctrl(x3303) // b2011
    val b2013 = Const(true).name("b2013").ctrl(x3303) // b2013
    val x3292 = OpDef(op=BitAnd, inputs=List(b2012, b2013)).name("x3292").ctrl(x3303).srcCtx("UnrollingBase.scala:28:66") // And(b2012,b2013)
    val x3293 = OpDef(op=BitAnd, inputs=List(x3292, b1908)).name("x3293").ctrl(x3303).srcCtx("UnrollingBase.scala:28:66") // And(x3292,b1908)
    val x3294 = LoadBanks(List(x3260_d0_b0), List(b2010, b2011)).name("x3294").ctrl(x3303).srcCtx("GDA.scala:67:10") // ParSRAMLoad(x3260,List(ArrayBuffer(b2010, b2011)),List(x3293))
    val x3295 = x3294 // x3295 = VectorApply(x3294,0)
    val x3296 = LoadBanks(List(x3256_d1_b0), List(b2010, b2011)).name("x3296").ctrl(x3303).srcCtx("GDA.scala:67:10") // ParSRAMLoad(x3256,List(ArrayBuffer(b2010, b2011)),List(x3293))
    val x3297 = x3296 // x3297 = VectorApply(x3296,0)
    val x3298 = OpDef(op=BitAnd, inputs=List(b1973, b1908)).name("x3298").ctrl(x3303).srcCtx("GDA.scala:67:10") // And(b1973,b1908)
    val x3299 = OpDef(op=BitAnd, inputs=List(x3298, x3293)).name("x3299").ctrl(x3303).srcCtx("GDA.scala:67:10") // And(x3298,x3293)
    val x3300 = OpDef(op=FixEql, inputs=List(b1972, Const(0))).name("x3300").ctrl(x3303).srcCtx("GDA.scala:67:10") // FixEql(b1972,Const(0))
    val x3301 = ReduceAccumOp(op=FixAdd, input=x3295, accum=x3297).name("x3301").ctrl(x3303).srcCtx("GDA.scala:67:12") // FixAdd(x3295,x3297)
    val x3302 = StoreBanks(List(x3256_d0_b0, x3256_d1_b0), List(b2010, b2011), x3301).name("x3302").ctrl(x3303).srcCtx("GDA.scala:67:10") // ParSRAMStore(x3256,List(ArrayBuffer(b2010, b2011)),List(x3301),List(x3293))
    val x3305 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1).name("x3305").ctrl(x3318).srcCtx("GDA.scala:68:8") // CounterNew(Const(0),Const(96),Const(1),Const(1))
    val x3306 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1).name("x3306").ctrl(x3318).srcCtx("GDA.scala:68:8") // CounterNew(Const(0),Const(96),Const(1),Const(1))
    val x3307 = CounterChain(List(x3306,x3305)).name("x3307").ctrl(x3318).srcCtx("GDA.scala:68:8") // CounterChainNew(ArrayBuffer(x3306, x3305))
    val x3317 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3307).name("x3317").ctrl(x3318).srcCtx("GDA.scala:68:8") // UnrolledForeach(List(),x3307,Block(Const(())),ArrayBuffer(List(b2027), List(b2028)),ArrayBuffer(List(b2029), List(b2030)))
    val b2027 = CounterIter(x3306, Some(0)).name("b2027").ctrl(x3317) // b2027
    val b2029 = Const(true).name("b2029").ctrl(x3317) // b2029
    val b2028 = CounterIter(x3305, None).name("b2028").ctrl(x3317) // b2028
    val b2030 = Const(true).name("b2030").ctrl(x3317) // b2030
    val x3308 = OpDef(op=BitAnd, inputs=List(b2029, b2030)).name("x3308").ctrl(x3317).srcCtx("UnrollingBase.scala:28:66") // And(b2029,b2030)
    val x3309 = LoadBanks(List(x3256_d0_b0), List(b2027, b2028)).name("x3309").ctrl(x3317).srcCtx("GDA.scala:68:8") // ParSRAMLoad(x3256,List(ArrayBuffer(b2027, b2028)),List(x3308))
    val x3310 = x3309 // x3310 = VectorApply(x3309,0)
    val x3311 = LoadBanks(List(x3201_d1_b0), List(b2027, b2028)).name("x3311").ctrl(x3317).srcCtx("GDA.scala:68:8") // ParSRAMLoad(x3201,List(ArrayBuffer(b2027, b2028)),List(x3308))
    val x3312 = x3311 // x3312 = VectorApply(x3311,0)
    val x3313 = OpDef(op=BitAnd, inputs=List(b1908, x3308)).name("x3313").ctrl(x3317).srcCtx("GDA.scala:68:8") // And(b1908,x3308)
    val x3314 = OpDef(op=FixEql, inputs=List(b1907, Const(0))).name("x3314").ctrl(x3317).srcCtx("GDA.scala:68:8") // FixEql(b1907,Const(0))
    val x3315 = ReduceAccumOp(op=FixAdd, input=x3310, accum=x3312).name("x3315").ctrl(x3317).srcCtx("GDA.scala:68:10") // FixAdd(x3310,x3312)
    val x3316 = StoreBanks(List(x3201_d0_b0, x3201_d1_b0), List(b2027, b2028), x3315).name("x3316").ctrl(x3317).srcCtx("GDA.scala:68:8") // ParSRAMStore(x3201,List(ArrayBuffer(b2027, b2028)),List(x3315),List(x3308))
    val x3319 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1).name("x3319").ctrl(x3347).srcCtx("GDA.scala:70:36") // CounterNew(Const(0),Const(96),Const(1),Const(1))
    val x3320 = CounterChain(List(x3319)).name("x3320").ctrl(x3347).srcCtx("GDA.scala:70:36") // CounterChainNew(List(x3319))
    val x3346 = LoopController(style=StreamPipe, level=OuterControl, cchain=x3320).name("x3346").ctrl(x3347).srcCtx("GDA.scala:70:36") // UnrolledForeach(List(Const(true)),x3320,Block(Const(())),List(List(b2044)),List(List(b2045)))
    val b2044 = CounterIter(x3319, Some(0)).name("b2044").ctrl(x3346) // b2044
    val b2045 = Const(true).name("b2045").ctrl(x3346) // b2045
    val b3415 = StreamOut(field="offset").name("b3415").ctrl(x3346).srcCtx("GDA.scala:70:36") // x3321 = StreamOutNew(BurstCmdBus)
    isAccum(b3415) = false
    bufferDepthOf(b3415) = 1
    val b3416 = StreamOut(field="size").name("b3416").ctrl(x3346).srcCtx("GDA.scala:70:36") // x3321 = StreamOutNew(BurstCmdBus)
    isAccum(b3416) = false
    bufferDepthOf(b3416) = 1
    val x3322 = StreamOut(field="data").name("x3322").ctrl(x3346).srcCtx("GDA.scala:70:36") // x3322 = StreamOutNew(BurstFullDataBus())
    isAccum(x3322) = false
    bufferDepthOf(x3322) = 1
    val x3323 = StreamIn(field="ack").name("x3323").ctrl(x3346).srcCtx("GDA.scala:70:36") // x3323 = StreamInNew(BurstAckBus)
    isAccum(x3323) = false
    bufferDepthOf(x3323) = 1
    val x3334 = UnitController(style=SeqPipe, level=InnerControl).name("x3334").ctrl(x3346).srcCtx("GDA.scala:70:36") // UnitPipe(List(b2045),Block(x3333))
    val x3324 = b2044 // FixConvert(b2044,TRUE,_32,_0) (Same Type. No op)
    val x3325 = OpDef(op=FixMul, inputs=List(x3324, Const(96))).name("x3325").ctrl(x3334).srcCtx("GDA.scala:70:36") // FixMul(x3324,Const(96))
    val x3326 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x3327 = OpDef(op=FixAdd, inputs=List(x3325, x3326)).name("x3327").ctrl(x3334).srcCtx("GDA.scala:70:36") // FixAdd(x3325,x3326)
    val x3328 = OpDef(op=FixSla, inputs=List(x3327, Const(2))).name("x3328").ctrl(x3334).srcCtx("GDA.scala:70:36") // FixLsh(x3327,Const(2))
    val x3329 = x3328 // FixConvert(x3328,TRUE,_64,_0)
    val x3330 = DramAddress(x3157).name("x3330").ctrl(x3334).srcCtx("GDA.scala:70:36") // GetDRAMAddress(x3157)
    val x3332_x3331 = OpDef(op=FixAdd, inputs=List(x3329, x3330)).name("x3332_x3331").ctrl(x3334).srcCtx("GDA.scala:70:36") // FixAdd(x3329,x3330)
    // x3332 = SimpleStruct(ArrayBuffer((offset,x3331), (size,Const(384)), (isLoad,Const(false))))
    val x3333_b3417_b3415 = WriteMem(b3415, x3332_x3331).name("x3333_b3417_b3415").ctrl(x3334).srcCtx("GDA.scala:70:36") // StreamWrite(x3321,x3332,b2045)
    val x3333_b3418_b3416 = WriteMem(b3416, Const(384)).name("x3333_b3418_b3416").ctrl(x3334).srcCtx("GDA.scala:70:36") // StreamWrite(x3321,x3332,b2045)
    val x3335 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16).name("x3335").ctrl(x3346).srcCtx("GDA.scala:70:36") // CounterNew(Const(0),Const(96),Const(1),Const(16))
    val x3336 = CounterChain(List(x3335)).name("x3336").ctrl(x3346).srcCtx("GDA.scala:70:36") // CounterChainNew(List(x3335))
    val x3342 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3336).name("x3342").ctrl(x3346).srcCtx("GDA.scala:70:36") // UnrolledForeach(List(b2045),x3336,Block(Const(())),List(List(b2062)),List(List(b2063)))
    val b2062 = CounterIter(x3335, None).name("b2062").ctrl(x3342) // b2062
    val b2063 = Const(true).name("b2063").ctrl(x3342) // b2063
    val x3337 = OpDef(op=BitAnd, inputs=List(b2063, b2045)).name("x3337").ctrl(x3342).srcCtx("UnrollingBase.scala:28:66") // And(b2063,b2045)
    val x3338 = LoadBanks(List(x3201_d0_b0), List(b2044, b2062)).name("x3338").ctrl(x3342).srcCtx("GDA.scala:70:36") // ParSRAMLoad(x3201,List(List(b2044, b2062)),List(x3337))
    val x3340_x3339 = x3338 // x3339 = VectorApply(x3338,0)
    // x3340 = SimpleStruct(ArrayBuffer((_1,x3339), (_2,Const(true))))
    val x3341_x3341_x3322 = WriteMem(x3322, x3340_x3339).name("x3341_x3341_x3322").ctrl(x3342).srcCtx("GDA.scala:70:36") // ParStreamWrite(x3322,List(x3340),List(x3337))
    val x3343 = FringeDenseStore(dram=List(x3157), cmdStream=List(b3415, b3416), dataStream=List(x3322), ackStream=List(x3323)).name("x3343").ctrl(x3346).srcCtx("GDA.scala:70:36") // FringeDenseStore(x3157,x3321,x3322,x3323)
    val x3345 = UnitController(style=SeqPipe, level=InnerControl).name("x3345").ctrl(x3346).srcCtx("GDA.scala:70:36") // UnitPipe(List(b2045),Block(Const(())))
    val x3344_x3344 = ReadMem(x3323).name("x3344_x3344").ctrl(x3345).srcCtx("GDA.scala:70:36") // StreamRead(x3323,b2045)
    
  }
}
