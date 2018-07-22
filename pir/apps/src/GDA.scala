import pir._
import pir.node._
import arch._
import prism.enums._

object GDA extends PIRApp {
  def main(implicit design:PIRDesign) = {
    import design.pirmeta._
    val x3191 = withCtrl(design.top.topController) { ArgIn(init=0).name("x3191").srcCtx("GDA.scala:22:18:R") } // ArgInNew(Const(0))
    isAccum(x3191) = false
    bufferDepthOf(x3191) = 1
    boundOf(x3191) = 1024
    val x3193 = withCtrl(design.top.topController) { ReadMem(x3191).name("x3193").srcCtx("GDA.scala:25:21") } // RegRead(x3191)
    val x3194 = withCtrl(design.top.topController) { DRAM(dims=List(x3193, Const(64))).name("x3194").srcCtx("GDA.scala:25:20:x") } // x3194 = DRAMNew(ArrayBuffer(x3193, Const(64)),Const(0))
    val x3195 = withCtrl(design.top.topController) { ReadMem(x3191).name("x3195").srcCtx("GDA.scala:26:23") } // RegRead(x3191)
    val x3196 = withCtrl(design.top.topController) { DRAM(dims=List(x3195)).name("x3196").srcCtx("GDA.scala:26:22:y") } // x3196 = DRAMNew(ArrayBuffer(x3195),Const(0))
    val x3197 = withCtrl(design.top.topController) { DRAM(dims=List(Const(64))).name("x3197").srcCtx("GDA.scala:27:22:mu0") } // x3197 = DRAMNew(ArrayBuffer(Const(64)),Const(0))
    val x3198 = withCtrl(design.top.topController) { DRAM(dims=List(Const(64))).name("x3198").srcCtx("GDA.scala:28:22:mu1") } // x3198 = DRAMNew(ArrayBuffer(Const(64)),Const(0))
    val x3199 = withCtrl(design.top.topController) { DRAM(dims=List(Const(64), Const(64))).name("x3199").srcCtx("GDA.scala:29:24:sigma") } // x3199 = DRAMNew(ArrayBuffer(Const(64), Const(64)),Const(0))
    val x3393 = withCtrl(design.top.topController) { UnitController(style=SeqPipe, level=OuterControl).name("x3393").srcCtx("GDA.scala:36:11") } // Hwblock(Block(Const(())),false)
    val x3204_d0_b0 = withCtrl(x3393) { SRAM(size=64, banking=Strided(banks=16, stride=1)).name("x3204_d0_b0").srcCtx("GDA.scala:37:28:mu0Tile") } // x3204 = SRAMNew(ArrayBuffer(Const(64)))
    isAccum(x3204_d0_b0) = false
    bufferDepthOf(x3204_d0_b0) = 1
    staticDimsOf(x3204_d0_b0) = List(64)
    val x3205_d0_b0 = withCtrl(x3393) { SRAM(size=64, banking=Strided(banks=16, stride=1)).name("x3205_d0_b0").srcCtx("GDA.scala:38:28:mu1Tile") } // x3205 = SRAMNew(ArrayBuffer(Const(64)))
    isAccum(x3205_d0_b0) = false
    bufferDepthOf(x3205_d0_b0) = 1
    staticDimsOf(x3205_d0_b0) = List(64)
    val x3242 = withCtrl(x3393) { UnitController(style=ForkJoin, level=OuterControl).name("x3242").srcCtx("GDA.scala:39:16") } // ParallelPipe(List(Const(true)),Block(Const(())))
    val x3223 = withCtrl(x3242) { UnitController(style=StreamPipe, level=OuterControl).name("x3223").srcCtx("GDA.scala:40:17") } // UnitPipe(List(Const(true)),Block(Const(())))
    val b3445 = withCtrl(x3223) { StreamOut(field="offset").name("b3445").srcCtx("GDA.scala:40:17") } // x3206 = StreamOutNew(BurstCmdBus)
    isAccum(b3445) = false
    bufferDepthOf(b3445) = 1
    val b3446 = withCtrl(x3223) { StreamOut(field="size").name("b3446").srcCtx("GDA.scala:40:17") } // x3206 = StreamOutNew(BurstCmdBus)
    isAccum(b3446) = false
    bufferDepthOf(b3446) = 1
    val x3207 = withCtrl(x3223) { StreamIn(field="data").name("x3207").srcCtx("GDA.scala:40:17") } // x3207 = StreamInNew(BurstDataBus())
    isAccum(x3207) = false
    bufferDepthOf(x3207) = 1
    val x3215 = withCtrl(x3223) { UnitController(style=SeqPipe, level=InnerControl).name("x3215").srcCtx("GDA.scala:40:17") } // UnitPipe(List(Const(true)),Block(x3214))
    val x3208 = withCtrl(x3215) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x3209 = withCtrl(x3215) { OpDef(op=FixSla, inputs=List(x3208, Const(2))).name("x3209").srcCtx("GDA.scala:40:17") } // FixLsh(x3208,Const(2))
    val x3210 = withCtrl(x3215) { x3209 } // FixConvert(x3209,TRUE,_64,_0)
    val x3211 = withCtrl(x3215) { DramAddress(x3197).name("x3211").srcCtx("GDA.scala:40:17") } // GetDRAMAddress(x3197)
    val x3213_x3212 = withCtrl(x3215) { OpDef(op=FixAdd, inputs=List(x3210, x3211)).name("x3213_x3212").srcCtx("GDA.scala:40:17") } // FixAdd(x3210,x3211)
    // x3213 = SimpleStruct(ArrayBuffer((offset,x3212), (size,Const(256)), (isLoad,Const(true))))
    val x3214_b3447_b3445 = withCtrl(x3215) { WriteMem(b3445, x3213_x3212).name("x3214_b3447_b3445").srcCtx("GDA.scala:40:17") } // StreamWrite(x3206,x3213,Const(true))
    val x3214_b3448_b3446 = withCtrl(x3215) { WriteMem(b3446, Const(256)).name("x3214_b3448_b3446").srcCtx("GDA.scala:40:17") } // StreamWrite(x3206,x3213,Const(true))
    val x3216 = withCtrl(x3223) { FringeDenseLoad(dram=List(x3197), cmdStream=List(b3445, b3446), dataStream=List(x3207)).name("x3216").srcCtx("GDA.scala:40:17") } // FringeDenseLoad(x3197,x3206,x3207)
    val x3217 = withCtrl(x3223) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x3217").srcCtx("GDA.scala:40:17") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x3218 = withCtrl(x3223) { CounterChain(List(x3217)).name("x3218").srcCtx("GDA.scala:40:17") } // CounterChainNew(List(x3217))
    val x3222 = withCtrl(x3223) { LoopController(style=InnerPipe, level=InnerControl, cchain=x3218).name("x3222").srcCtx("GDA.scala:40:17") } // UnrolledForeach(List(Const(true)),x3218,Block(Const(())),List(List(b1894)),List(List(b1895)))
    val b1894 = withCtrl(x3222) { CounterIter(x3217, None).name("b1894") } // b1894
    val b1895 = withCtrl(x3222) { Const(true).name("b1895") } // b1895
    val x3219_x3219 = withCtrl(x3222) { ReadMem(x3207).name("x3219_x3219").srcCtx("GDA.scala:40:17") } // ParStreamRead(x3207,List(b1895))
    val x3220_x3220 = withCtrl(x3222) { x3219_x3219 } // VectorApply(x3219,0)
    val x3221 = withCtrl(x3222) { StoreBanks(List(List(x3204_d0_b0)), List(b1894), x3220_x3220).name("x3221").srcCtx("GDA.scala:40:17") } // ParSRAMStore(x3204,List(List(b1894)),List(x3220),List(b1895))
    val x3241 = withCtrl(x3242) { UnitController(style=StreamPipe, level=OuterControl).name("x3241").srcCtx("GDA.scala:41:17") } // UnitPipe(List(Const(true)),Block(Const(())))
    val b3449 = withCtrl(x3241) { StreamOut(field="offset").name("b3449").srcCtx("GDA.scala:41:17") } // x3224 = StreamOutNew(BurstCmdBus)
    isAccum(b3449) = false
    bufferDepthOf(b3449) = 1
    val b3450 = withCtrl(x3241) { StreamOut(field="size").name("b3450").srcCtx("GDA.scala:41:17") } // x3224 = StreamOutNew(BurstCmdBus)
    isAccum(b3450) = false
    bufferDepthOf(b3450) = 1
    val x3225 = withCtrl(x3241) { StreamIn(field="data").name("x3225").srcCtx("GDA.scala:41:17") } // x3225 = StreamInNew(BurstDataBus())
    isAccum(x3225) = false
    bufferDepthOf(x3225) = 1
    val x3233 = withCtrl(x3241) { UnitController(style=SeqPipe, level=InnerControl).name("x3233").srcCtx("GDA.scala:41:17") } // UnitPipe(List(Const(true)),Block(x3232))
    val x3226 = withCtrl(x3233) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x3227 = withCtrl(x3233) { OpDef(op=FixSla, inputs=List(x3226, Const(2))).name("x3227").srcCtx("GDA.scala:41:17") } // FixLsh(x3226,Const(2))
    val x3228 = withCtrl(x3233) { x3227 } // FixConvert(x3227,TRUE,_64,_0)
    val x3229 = withCtrl(x3233) { DramAddress(x3198).name("x3229").srcCtx("GDA.scala:41:17") } // GetDRAMAddress(x3198)
    val x3231_x3230 = withCtrl(x3233) { OpDef(op=FixAdd, inputs=List(x3228, x3229)).name("x3231_x3230").srcCtx("GDA.scala:41:17") } // FixAdd(x3228,x3229)
    // x3231 = SimpleStruct(ArrayBuffer((offset,x3230), (size,Const(256)), (isLoad,Const(true))))
    val x3232_b3451_b3449 = withCtrl(x3233) { WriteMem(b3449, x3231_x3230).name("x3232_b3451_b3449").srcCtx("GDA.scala:41:17") } // StreamWrite(x3224,x3231,Const(true))
    val x3232_b3452_b3450 = withCtrl(x3233) { WriteMem(b3450, Const(256)).name("x3232_b3452_b3450").srcCtx("GDA.scala:41:17") } // StreamWrite(x3224,x3231,Const(true))
    val x3234 = withCtrl(x3241) { FringeDenseLoad(dram=List(x3198), cmdStream=List(b3449, b3450), dataStream=List(x3225)).name("x3234").srcCtx("GDA.scala:41:17") } // FringeDenseLoad(x3198,x3224,x3225)
    val x3235 = withCtrl(x3241) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x3235").srcCtx("GDA.scala:41:17") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x3236 = withCtrl(x3241) { CounterChain(List(x3235)).name("x3236").srcCtx("GDA.scala:41:17") } // CounterChainNew(List(x3235))
    val x3240 = withCtrl(x3241) { LoopController(style=InnerPipe, level=InnerControl, cchain=x3236).name("x3240").srcCtx("GDA.scala:41:17") } // UnrolledForeach(List(Const(true)),x3236,Block(Const(())),List(List(b1914)),List(List(b1915)))
    val b1914 = withCtrl(x3240) { CounterIter(x3235, None).name("b1914") } // b1914
    val b1915 = withCtrl(x3240) { Const(true).name("b1915") } // b1915
    val x3237_x3237 = withCtrl(x3240) { ReadMem(x3225).name("x3237_x3237").srcCtx("GDA.scala:41:17") } // ParStreamRead(x3225,List(b1915))
    val x3238_x3238 = withCtrl(x3240) { x3237_x3237 } // VectorApply(x3237,0)
    val x3239 = withCtrl(x3240) { StoreBanks(List(List(x3205_d0_b0)), List(b1914), x3238_x3238).name("x3239").srcCtx("GDA.scala:41:17") } // ParSRAMStore(x3205,List(List(b1914)),List(x3238),List(b1915))
    val x3243_d0_b0 = withCtrl(x3393) { SRAM(size=4096, banking=Strided(banks=16, stride=1)).name("x3243_d0_b0").srcCtx("GDA.scala:44:29:sigmaOut") } // x3243 = SRAMNew(ArrayBuffer(Const(64), Const(64)))
    isAccum(x3243_d0_b0) = false
    bufferDepthOf(x3243_d0_b0) = 1
    staticDimsOf(x3243_d0_b0) = List(64, 64)
    val x3243_d1_b0 = withCtrl(x3393) { SRAM(size=4096, banking=Strided(banks=16, stride=1)).name("x3243_d1_b0").srcCtx("GDA.scala:44:29:sigmaOut") } // x3243 = SRAMNew(ArrayBuffer(Const(64), Const(64)))
    isAccum(x3243_d1_b0) = true
    bufferDepthOf(x3243_d1_b0) = 1
    staticDimsOf(x3243_d1_b0) = List(64, 64)
    val x3244 = withCtrl(x3393) { ReadMem(x3191).name("x3244").srcCtx("GDA.scala:46:34") } // RegRead(x3191)
    val x3245 = withCtrl(x3393) { Counter(min=Const(0), max=x3244, step=Const(32), par=1).name("x3245").srcCtx("GDA.scala:46:42") } // CounterNew(Const(0),x3244,Const(32),Const(1))
    val x3246 = withCtrl(x3393) { CounterChain(List(x3245)).name("x3246").srcCtx("GDA.scala:70:8") } // CounterChainNew(List(x3245))
    val x3364 = withCtrl(x3393) { LoopController(style=MetaPipe, level=OuterControl, cchain=x3246).name("x3364").srcCtx("GDA.scala:70:8") } // UnrolledReduce(List(Const(true)),x3246,x3243,Block((x3243) => Const(())),List(List(b1929)),List(List(b1930)))
    val b1929 = withCtrl(x3364) { CounterIter(x3245, Some(0)).name("b1929") } // b1929
    val b1930 = withCtrl(x3364) { Const(true).name("b1930") } // b1930
    val x3247_d0_b0 = withCtrl(x3364) { SRAM(size=32, banking=Strided(banks=16, stride=1)).name("x3247_d0_b0").srcCtx("GDA.scala:47:33:gdaYtile") } // x3247 = SRAMNew(ArrayBuffer(Const(32)))
    isAccum(x3247_d0_b0) = false
    bufferDepthOf(x3247_d0_b0) = 2
    staticDimsOf(x3247_d0_b0) = List(32)
    val x3248_d0_b0 = withCtrl(x3364) { SRAM(size=2048, banking=Strided(banks=16, stride=1)).name("x3248_d0_b0").srcCtx("GDA.scala:48:31:gdaXtile") } // x3248 = SRAMNew(ArrayBuffer(Const(32), Const(64)))
    isAccum(x3248_d0_b0) = false
    bufferDepthOf(x3248_d0_b0) = 2
    staticDimsOf(x3248_d0_b0) = List(32, 64)
    val x3297 = withCtrl(x3364) { UnitController(style=ForkJoin, level=OuterControl).name("x3297").srcCtx("GDA.scala:50:18") } // ParallelPipe(List(b1930),Block(Const(())))
    val x3250 = withCtrl(x3297) { UnitController(style=SeqPipe, level=InnerControl).name("x3250").srcCtx("GDA.scala:50:18") } // UnitPipe(List(b1930),Block(Const(())))
    val x3249 = withCtrl(x3250) { OpDef(op=FixAdd, inputs=List(b1929, Const(32))).name("x3249").srcCtx("GDA.scala:51:34") } // FixAdd(b1929,Const(32))
    val x3269 = withCtrl(x3297) { UnitController(style=StreamPipe, level=OuterControl).name("x3269").srcCtx("GDA.scala:51:20") } // UnitPipe(List(b1930),Block(Const(())))
    val b3453 = withCtrl(x3269) { StreamOut(field="offset").name("b3453").srcCtx("GDA.scala:51:20") } // x3251 = StreamOutNew(BurstCmdBus)
    isAccum(b3453) = false
    bufferDepthOf(b3453) = 1
    val b3454 = withCtrl(x3269) { StreamOut(field="size").name("b3454").srcCtx("GDA.scala:51:20") } // x3251 = StreamOutNew(BurstCmdBus)
    isAccum(b3454) = false
    bufferDepthOf(b3454) = 1
    val x3252 = withCtrl(x3269) { StreamIn(field="data").name("x3252").srcCtx("GDA.scala:51:20") } // x3252 = StreamInNew(BurstDataBus())
    isAccum(x3252) = false
    bufferDepthOf(x3252) = 1
    val x3260 = withCtrl(x3269) { UnitController(style=SeqPipe, level=InnerControl).name("x3260").srcCtx("GDA.scala:51:20") } // UnitPipe(List(b1930),Block(x3259))
    val x3253 = withCtrl(x3260) { b1929 } // FixConvert(b1929,TRUE,_32,_0) (Same Type. No op)
    val x3254 = withCtrl(x3260) { OpDef(op=FixSla, inputs=List(x3253, Const(2))).name("x3254").srcCtx("GDA.scala:51:20") } // FixLsh(x3253,Const(2))
    val x3255 = withCtrl(x3260) { x3254 } // FixConvert(x3254,TRUE,_64,_0)
    val x3256 = withCtrl(x3260) { DramAddress(x3196).name("x3256").srcCtx("GDA.scala:51:20") } // GetDRAMAddress(x3196)
    val x3258_x3257 = withCtrl(x3260) { OpDef(op=FixAdd, inputs=List(x3255, x3256)).name("x3258_x3257").srcCtx("GDA.scala:51:20") } // FixAdd(x3255,x3256)
    // x3258 = SimpleStruct(ArrayBuffer((offset,x3257), (size,Const(128)), (isLoad,Const(true))))
    val x3259_b3455_b3453 = withCtrl(x3260) { WriteMem(b3453, x3258_x3257).name("x3259_b3455_b3453").srcCtx("GDA.scala:51:20") } // StreamWrite(x3251,x3258,b1930)
    val x3259_b3456_b3454 = withCtrl(x3260) { WriteMem(b3454, Const(128)).name("x3259_b3456_b3454").srcCtx("GDA.scala:51:20") } // StreamWrite(x3251,x3258,b1930)
    val x3261 = withCtrl(x3269) { FringeDenseLoad(dram=List(x3196), cmdStream=List(b3453, b3454), dataStream=List(x3252)).name("x3261").srcCtx("GDA.scala:51:20") } // FringeDenseLoad(x3196,x3251,x3252)
    val x3262 = withCtrl(x3269) { Counter(min=Const(0), max=Const(32), step=Const(1), par=16).name("x3262").srcCtx("GDA.scala:51:20") } // CounterNew(Const(0),Const(32),Const(1),Const(16))
    val x3263 = withCtrl(x3269) { CounterChain(List(x3262)).name("x3263").srcCtx("GDA.scala:51:20") } // CounterChainNew(List(x3262))
    val x3268 = withCtrl(x3269) { LoopController(style=InnerPipe, level=InnerControl, cchain=x3263).name("x3268").srcCtx("GDA.scala:51:20") } // UnrolledForeach(List(b1930),x3263,Block(Const(())),List(List(b1948)),List(List(b1949)))
    val b1948 = withCtrl(x3268) { CounterIter(x3262, None).name("b1948") } // b1948
    val b1949 = withCtrl(x3268) { Const(true).name("b1949") } // b1949
    val x3264 = withCtrl(x3268) { OpDef(op=BitAnd, inputs=List(b1949, b1930)).name("x3264").srcCtx("UnrollingBase.scala:28:66") } // And(b1949,b1930)
    val x3265_x3265 = withCtrl(x3268) { ReadMem(x3252).name("x3265_x3265").srcCtx("GDA.scala:51:20") } // ParStreamRead(x3252,List(x3264))
    val x3266_x3266 = withCtrl(x3268) { x3265_x3265 } // VectorApply(x3265,0)
    val x3267 = withCtrl(x3268) { StoreBanks(List(List(x3247_d0_b0)), List(b1948), x3266_x3266).name("x3267").srcCtx("GDA.scala:51:20") } // ParSRAMStore(x3247,List(List(b1948)),List(x3266),List(x3264))
    val x3270 = withCtrl(x3297) { Counter(min=Const(0), max=Const(32), step=Const(1), par=1).name("x3270").srcCtx("GDA.scala:52:20") } // CounterNew(Const(0),Const(32),Const(1),Const(1))
    val x3271 = withCtrl(x3297) { CounterChain(List(x3270)).name("x3271").srcCtx("GDA.scala:52:20") } // CounterChainNew(List(x3270))
    val x3296 = withCtrl(x3297) { LoopController(style=StreamPipe, level=OuterControl, cchain=x3271).name("x3296").srcCtx("GDA.scala:52:20") } // UnrolledForeach(List(b1930),x3271,Block(Const(())),List(List(b1958)),List(List(b1959)))
    val b1958 = withCtrl(x3296) { CounterIter(x3270, Some(0)).name("b1958") } // b1958
    val b1959 = withCtrl(x3296) { Const(true).name("b1959") } // b1959
    val b3457 = withCtrl(x3296) { StreamOut(field="offset").name("b3457").srcCtx("GDA.scala:52:20") } // x3272 = StreamOutNew(BurstCmdBus)
    isAccum(b3457) = false
    bufferDepthOf(b3457) = 1
    val b3458 = withCtrl(x3296) { StreamOut(field="size").name("b3458").srcCtx("GDA.scala:52:20") } // x3272 = StreamOutNew(BurstCmdBus)
    isAccum(b3458) = false
    bufferDepthOf(b3458) = 1
    val x3273 = withCtrl(x3296) { StreamIn(field="data").name("x3273").srcCtx("GDA.scala:52:20") } // x3273 = StreamInNew(BurstDataBus())
    isAccum(x3273) = false
    bufferDepthOf(x3273) = 1
    val x3286 = withCtrl(x3296) { UnitController(style=SeqPipe, level=InnerControl).name("x3286").srcCtx("GDA.scala:52:20") } // UnitPipe(List(b1959, b1930),Block(x3285))
    val x3274 = withCtrl(x3286) { OpDef(op=FixAdd, inputs=List(b1929, b1958)).name("x3274").srcCtx("GDA.scala:52:20") } // FixAdd(b1929,b1958)
    val x3275 = withCtrl(x3286) { x3274 } // FixConvert(x3274,TRUE,_32,_0) (Same Type. No op)
    val x3276 = withCtrl(x3286) { OpDef(op=FixSla, inputs=List(x3275, Const(6))).name("x3276").srcCtx("GDA.scala:52:20") } // FixLsh(x3275,Const(6))
    val x3277 = withCtrl(x3286) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x3278 = withCtrl(x3286) { OpDef(op=FixAdd, inputs=List(x3276, x3277)).name("x3278").srcCtx("GDA.scala:52:20") } // FixAdd(x3276,x3277)
    val x3279 = withCtrl(x3286) { OpDef(op=FixSla, inputs=List(x3278, Const(2))).name("x3279").srcCtx("GDA.scala:52:20") } // FixLsh(x3278,Const(2))
    val x3280 = withCtrl(x3286) { x3279 } // FixConvert(x3279,TRUE,_64,_0)
    val x3281 = withCtrl(x3286) { DramAddress(x3194).name("x3281").srcCtx("GDA.scala:52:20") } // GetDRAMAddress(x3194)
    val x3283_x3282 = withCtrl(x3286) { OpDef(op=FixAdd, inputs=List(x3280, x3281)).name("x3283_x3282").srcCtx("GDA.scala:52:20") } // FixAdd(x3280,x3281)
    // x3283 = SimpleStruct(ArrayBuffer((offset,x3282), (size,Const(256)), (isLoad,Const(true))))
    val x3284 = withCtrl(x3286) { OpDef(op=BitAnd, inputs=List(b1959, b1930)).name("x3284").srcCtx("UnrollingBase.scala:28:66") } // And(b1959,b1930)
    val x3285_b3459_b3457 = withCtrl(x3286) { WriteMem(b3457, x3283_x3282).name("x3285_b3459_b3457").srcCtx("GDA.scala:52:20") } // StreamWrite(x3272,x3283,x3284)
    val x3285_b3460_b3458 = withCtrl(x3286) { WriteMem(b3458, Const(256)).name("x3285_b3460_b3458").srcCtx("GDA.scala:52:20") } // StreamWrite(x3272,x3283,x3284)
    val x3287 = withCtrl(x3296) { FringeDenseLoad(dram=List(x3194), cmdStream=List(b3457, b3458), dataStream=List(x3273)).name("x3287").srcCtx("GDA.scala:52:20") } // FringeDenseLoad(x3194,x3272,x3273)
    val x3288 = withCtrl(x3296) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x3288").srcCtx("GDA.scala:52:20") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x3289 = withCtrl(x3296) { CounterChain(List(x3288)).name("x3289").srcCtx("GDA.scala:52:20") } // CounterChainNew(List(x3288))
    val x3295 = withCtrl(x3296) { LoopController(style=InnerPipe, level=InnerControl, cchain=x3289).name("x3295").srcCtx("GDA.scala:52:20") } // UnrolledForeach(List(b1959, b1930),x3289,Block(Const(())),List(List(b1978)),List(List(b1979)))
    val b1978 = withCtrl(x3295) { CounterIter(x3288, None).name("b1978") } // b1978
    val b1979 = withCtrl(x3295) { Const(true).name("b1979") } // b1979
    val x3290 = withCtrl(x3295) { OpDef(op=BitAnd, inputs=List(b1979, b1959)).name("x3290").srcCtx("UnrollingBase.scala:28:66") } // And(b1979,b1959)
    val x3291 = withCtrl(x3295) { OpDef(op=BitAnd, inputs=List(x3290, b1930)).name("x3291").srcCtx("UnrollingBase.scala:28:66") } // And(x3290,b1930)
    val x3292_x3292 = withCtrl(x3295) { ReadMem(x3273).name("x3292_x3292").srcCtx("GDA.scala:52:20") } // ParStreamRead(x3273,List(x3291))
    val x3293_x3293 = withCtrl(x3295) { x3292_x3292 } // VectorApply(x3292,0)
    val x3294 = withCtrl(x3295) { StoreBanks(List(List(x3248_d0_b0)), List(b1958, b1978), x3293_x3293).name("x3294").srcCtx("GDA.scala:52:20") } // ParSRAMStore(x3248,List(List(b1958, b1978)),List(x3293),List(x3291))
    val x3298_d0_b0 = withCtrl(x3364) { SRAM(size=4096, banking=Strided(banks=16, stride=1)).name("x3298_d0_b0").srcCtx("GDA.scala:55:31:sigmaBlk") } // x3298 = SRAMNew(ArrayBuffer(Const(64), Const(64)))
    isAccum(x3298_d0_b0) = false
    bufferDepthOf(x3298_d0_b0) = 2
    staticDimsOf(x3298_d0_b0) = List(64, 64)
    val x3298_d1_b0 = withCtrl(x3364) { SRAM(size=4096, banking=Strided(banks=16, stride=1)).name("x3298_d1_b0").srcCtx("GDA.scala:55:31:sigmaBlk") } // x3298 = SRAMNew(ArrayBuffer(Const(64), Const(64)))
    isAccum(x3298_d1_b0) = true
    bufferDepthOf(x3298_d1_b0) = 1
    staticDimsOf(x3298_d1_b0) = List(64, 64)
    val x3299 = withCtrl(x3364) { Counter(min=Const(0), max=Const(32), step=Const(1), par=1).name("x3299").srcCtx("GDA.scala:57:39") } // CounterNew(Const(0),Const(32),Const(1),Const(1))
    val x3300 = withCtrl(x3364) { CounterChain(List(x3299)).name("x3300").srcCtx("GDA.scala:69:10") } // CounterChainNew(List(x3299))
    val x3349 = withCtrl(x3364) { LoopController(style=MetaPipe, level=OuterControl, cchain=x3300).name("x3349").srcCtx("GDA.scala:69:10") } // UnrolledReduce(List(b1930),x3300,x3298,Block((x3298) => Const(())),List(List(b1994)),List(List(b1995)))
    val b1994 = withCtrl(x3349) { CounterIter(x3299, Some(0)).name("b1994") } // b1994
    val b1995 = withCtrl(x3349) { Const(true).name("b1995") } // b1995
    val x3301_d0_b0 = withCtrl(x3349) { SRAM(size=64, banking=Strided(banks=16, stride=1)).name("x3301_d0_b0").srcCtx("GDA.scala:58:32:subTile") } // x3301 = SRAMNew(ArrayBuffer(Const(64)))
    isAccum(x3301_d0_b0) = false
    bufferDepthOf(x3301_d0_b0) = 2
    staticDimsOf(x3301_d0_b0) = List(64)
    val x3301_d1_b0 = withCtrl(x3349) { SRAM(size=64, banking=Strided(banks=16, stride=1)).name("x3301_d1_b0").srcCtx("GDA.scala:58:32:subTile") } // x3301 = SRAMNew(ArrayBuffer(Const(64)))
    isAccum(x3301_d1_b0) = false
    bufferDepthOf(x3301_d1_b0) = 2
    staticDimsOf(x3301_d1_b0) = List(64)
    val x3302_d0_b0 = withCtrl(x3349) { SRAM(size=4096, banking=Strided(banks=16, stride=1)).name("x3302_d0_b0").srcCtx("GDA.scala:59:34:sigmaTile") } // x3302 = SRAMNew(ArrayBuffer(Const(64), Const(64)))
    isAccum(x3302_d0_b0) = false
    bufferDepthOf(x3302_d0_b0) = 2
    staticDimsOf(x3302_d0_b0) = List(64, 64)
    val x3303 = withCtrl(x3349) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x3303").srcCtx("GDA.scala:60:21") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x3304 = withCtrl(x3349) { CounterChain(List(x3303)).name("x3304").srcCtx("GDA.scala:60:29") } // CounterChainNew(List(x3303))
    val x3318 = withCtrl(x3349) { LoopController(style=InnerPipe, level=InnerControl, cchain=x3304).name("x3318").srcCtx("GDA.scala:60:29") } // UnrolledForeach(List(b1995, b1930),x3304,Block(Const(())),List(List(b2000)),List(List(b2001)))
    val b2000 = withCtrl(x3318) { CounterIter(x3303, None).name("b2000") } // b2000
    val b2001 = withCtrl(x3318) { Const(true).name("b2001") } // b2001
    val x3305 = withCtrl(x3318) { OpDef(op=BitAnd, inputs=List(b2001, b1995)).name("x3305").srcCtx("UnrollingBase.scala:28:66") } // And(b2001,b1995)
    val x3306 = withCtrl(x3318) { OpDef(op=BitAnd, inputs=List(x3305, b1930)).name("x3306").srcCtx("UnrollingBase.scala:28:66") } // And(x3305,b1930)
    val x3307 = withCtrl(x3318) { LoadBanks(List(x3248_d0_b0), List(b1994, b2000)).name("x3307").srcCtx("GDA.scala:61:35") } // ParSRAMLoad(x3248,List(List(b1994, b2000)),List(x3306))
    val x3308 = withCtrl(x3318) { x3307 } // VectorApply(x3307,0)
    val x3309 = withCtrl(x3318) { LoadBanks(List(x3247_d0_b0), List(b1994)).name("x3309").srcCtx("GDA.scala:61:58") } // SRAMLoad(x3247,ArrayBuffer(Const(32)),List(b1994),Const(0),x3306)
    val x3310 = withCtrl(x3318) { OpDef(op=FixEql, inputs=List(x3309, Const(1))).name("x3310").srcCtx("GDA.scala:61:63") } // FixEql(x3309,Const(1))
    val x3311 = withCtrl(x3318) { LoadBanks(List(x3205_d0_b0), List(b2000)).name("x3311").srcCtx("GDA.scala:61:76") } // ParSRAMLoad(x3205,List(List(b2000)),List(x3306))
    val x3312 = withCtrl(x3318) { x3311 } // VectorApply(x3311,0)
    val x3313 = withCtrl(x3318) { LoadBanks(List(x3204_d0_b0), List(b2000)).name("x3313").srcCtx("GDA.scala:61:89") } // ParSRAMLoad(x3204,List(List(b2000)),List(x3306))
    val x3314 = withCtrl(x3318) { x3313 } // VectorApply(x3313,0)
    val x3315 = withCtrl(x3318) { OpDef(op=MuxOp, inputs=List(x3310, x3312, x3314)).name("x3315").srcCtx("GDA.scala:61:49") } // Mux(x3310,x3312,x3314)
    val x3316 = withCtrl(x3318) { OpDef(op=FixSub, inputs=List(x3308, x3315)).name("x3316").srcCtx("GDA.scala:61:44") } // FixSub(x3308,x3315)
    val x3317 = withCtrl(x3318) { StoreBanks(List(List(x3301_d0_b0), List(x3301_d1_b0)), List(b2000), x3316).name("x3317").srcCtx("GDA.scala:61:25") } // ParSRAMStore(x3301,List(List(b2000)),List(x3316),List(x3306))
    val x3319 = withCtrl(x3349) { Counter(min=Const(0), max=Const(64), step=Const(1), par=1).name("x3319").srcCtx("GDA.scala:63:26") } // CounterNew(Const(0),Const(64),Const(1),Const(1))
    val x3320 = withCtrl(x3349) { CounterChain(List(x3319)).name("x3320").srcCtx("GDA.scala:63:35") } // CounterChainNew(List(x3319))
    val x3332 = withCtrl(x3349) { LoopController(style=MetaPipe, level=OuterControl, cchain=x3320).name("x3332").srcCtx("GDA.scala:63:35") } // UnrolledForeach(List(b1995, b1930),x3320,Block(Const(())),List(List(b2018)),List(List(b2019)))
    val b2018 = withCtrl(x3332) { CounterIter(x3319, Some(0)).name("b2018") } // b2018
    val b2019 = withCtrl(x3332) { Const(true).name("b2019") } // b2019
    val x3321 = withCtrl(x3332) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x3321").srcCtx("GDA.scala:64:23") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x3322 = withCtrl(x3332) { CounterChain(List(x3321)).name("x3322").srcCtx("GDA.scala:64:31") } // CounterChainNew(List(x3321))
    val x3331 = withCtrl(x3332) { LoopController(style=InnerPipe, level=InnerControl, cchain=x3322).name("x3331").srcCtx("GDA.scala:64:31") } // UnrolledForeach(List(b2019, b1995, b1930),x3322,Block(Const(())),List(List(b2022)),List(List(b2023)))
    val b2022 = withCtrl(x3331) { CounterIter(x3321, None).name("b2022") } // b2022
    val b2023 = withCtrl(x3331) { Const(true).name("b2023") } // b2023
    val x3323 = withCtrl(x3331) { OpDef(op=BitAnd, inputs=List(b2023, b2019)).name("x3323").srcCtx("UnrollingBase.scala:28:66") } // And(b2023,b2019)
    val x3324 = withCtrl(x3331) { OpDef(op=BitAnd, inputs=List(b1995, b1930)).name("x3324").srcCtx("UnrollingBase.scala:28:66") } // And(b1995,b1930)
    val x3325 = withCtrl(x3331) { OpDef(op=BitAnd, inputs=List(x3323, x3324)).name("x3325").srcCtx("UnrollingBase.scala:28:66") } // And(x3323,x3324)
    val x3326 = withCtrl(x3331) { LoadBanks(List(x3301_d1_b0), List(b2018)).name("x3326").srcCtx("GDA.scala:65:42") } // SRAMLoad(x3301,ArrayBuffer(Const(64)),List(b2018),Const(0),x3325)
    val x3327 = withCtrl(x3331) { LoadBanks(List(x3301_d0_b0), List(b2022)).name("x3327").srcCtx("GDA.scala:65:56") } // ParSRAMLoad(x3301,List(List(b2022)),List(x3325))
    val x3328 = withCtrl(x3331) { x3327 } // VectorApply(x3327,0)
    val x3329 = withCtrl(x3331) { OpDef(op=FixMul, inputs=List(x3326, x3328)).name("x3329").srcCtx("GDA.scala:65:47") } // FixMul(x3326,x3328)
    val x3330 = withCtrl(x3331) { StoreBanks(List(List(x3302_d0_b0)), List(b2018, b2022), x3329).name("x3330").srcCtx("GDA.scala:65:33") } // ParSRAMStore(x3302,List(List(b2018, b2022)),List(x3329),List(x3325))
    val x3333 = withCtrl(x3349) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x3333").srcCtx("GDA.scala:69:10") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x3334 = withCtrl(x3349) { Counter(min=Const(0), max=Const(64), step=Const(1), par=1).name("x3334").srcCtx("GDA.scala:69:10") } // CounterNew(Const(0),Const(64),Const(1),Const(1))
    val x3335 = withCtrl(x3349) { CounterChain(List(x3334,x3333)).name("x3335").srcCtx("GDA.scala:69:10") } // CounterChainNew(ArrayBuffer(x3334, x3333))
    val x3348 = withCtrl(x3349) { LoopController(style=InnerPipe, level=InnerControl, cchain=x3335).name("x3348").srcCtx("GDA.scala:69:10") } // UnrolledForeach(List(),x3335,Block(Const(())),ArrayBuffer(List(b2034), List(b2035)),ArrayBuffer(List(b2036), List(b2037)))
    val b2034 = withCtrl(x3348) { CounterIter(x3334, Some(0)).name("b2034") } // b2034
    val b2036 = withCtrl(x3348) { Const(true).name("b2036") } // b2036
    val b2035 = withCtrl(x3348) { CounterIter(x3333, None).name("b2035") } // b2035
    val b2037 = withCtrl(x3348) { Const(true).name("b2037") } // b2037
    val x3336 = withCtrl(x3348) { OpDef(op=BitAnd, inputs=List(b2036, b2037)).name("x3336").srcCtx("UnrollingBase.scala:28:66") } // And(b2036,b2037)
    val x3337 = withCtrl(x3348) { OpDef(op=BitAnd, inputs=List(x3336, b1930)).name("x3337").srcCtx("UnrollingBase.scala:28:66") } // And(x3336,b1930)
    val x3338 = withCtrl(x3348) { LoadBanks(List(x3302_d0_b0), List(b2034, b2035)).name("x3338").srcCtx("GDA.scala:69:10") } // ParSRAMLoad(x3302,List(ArrayBuffer(b2034, b2035)),List(x3337))
    val x3339 = withCtrl(x3348) { x3338 } // VectorApply(x3338,0)
    val x3340 = withCtrl(x3348) { LoadBanks(List(x3298_d1_b0), List(b2034, b2035)).name("x3340").srcCtx("GDA.scala:69:10") } // ParSRAMLoad(x3298,List(ArrayBuffer(b2034, b2035)),List(x3337))
    val x3341 = withCtrl(x3348) { x3340 } // VectorApply(x3340,0)
    val x3342 = withCtrl(x3348) { OpDef(op=BitAnd, inputs=List(b1995, b1930)).name("x3342").srcCtx("GDA.scala:69:10") } // And(b1995,b1930)
    val x3343 = withCtrl(x3348) { OpDef(op=BitAnd, inputs=List(x3342, x3337)).name("x3343").srcCtx("GDA.scala:69:10") } // And(x3342,x3337)
    val x3344 = withCtrl(x3348) { OpDef(op=FixEql, inputs=List(b1994, Const(0))).name("x3344").srcCtx("GDA.scala:69:10") } // FixEql(b1994,Const(0))
    val x3345 = withCtrl(x3348) { OpDef(op=FixAdd, inputs=List(x3339, x3341)).name("x3345").srcCtx("GDA.scala:69:12") } // FixAdd(x3339,x3341)
    val x3346 = withCtrl(x3348) { OpDef(op=MuxOp, inputs=List(x3344, x3339, x3345)).name("x3346").srcCtx("GDA.scala:69:10") } // Mux(x3344,x3339,x3345)
    val x3347 = withCtrl(x3348) { StoreBanks(List(List(x3298_d0_b0), List(x3298_d1_b0)), List(b2034, b2035), x3346).name("x3347").srcCtx("GDA.scala:69:10") } // ParSRAMStore(x3298,List(ArrayBuffer(b2034, b2035)),List(x3346),List(x3337))
    antiDepsOf(x3347)=List(x3340)
    val x3350 = withCtrl(x3364) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x3350").srcCtx("GDA.scala:70:8") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x3351 = withCtrl(x3364) { Counter(min=Const(0), max=Const(64), step=Const(1), par=1).name("x3351").srcCtx("GDA.scala:70:8") } // CounterNew(Const(0),Const(64),Const(1),Const(1))
    val x3352 = withCtrl(x3364) { CounterChain(List(x3351,x3350)).name("x3352").srcCtx("GDA.scala:70:8") } // CounterChainNew(ArrayBuffer(x3351, x3350))
    val x3363 = withCtrl(x3364) { LoopController(style=InnerPipe, level=InnerControl, cchain=x3352).name("x3363").srcCtx("GDA.scala:70:8") } // UnrolledForeach(List(),x3352,Block(Const(())),ArrayBuffer(List(b2052), List(b2053)),ArrayBuffer(List(b2054), List(b2055)))
    val b2052 = withCtrl(x3363) { CounterIter(x3351, Some(0)).name("b2052") } // b2052
    val b2054 = withCtrl(x3363) { Const(true).name("b2054") } // b2054
    val b2053 = withCtrl(x3363) { CounterIter(x3350, None).name("b2053") } // b2053
    val b2055 = withCtrl(x3363) { Const(true).name("b2055") } // b2055
    val x3353 = withCtrl(x3363) { OpDef(op=BitAnd, inputs=List(b2054, b2055)).name("x3353").srcCtx("UnrollingBase.scala:28:66") } // And(b2054,b2055)
    val x3354 = withCtrl(x3363) { LoadBanks(List(x3298_d0_b0), List(b2052, b2053)).name("x3354").srcCtx("GDA.scala:70:8") } // ParSRAMLoad(x3298,List(ArrayBuffer(b2052, b2053)),List(x3353))
    val x3355 = withCtrl(x3363) { x3354 } // VectorApply(x3354,0)
    val x3356 = withCtrl(x3363) { LoadBanks(List(x3243_d1_b0), List(b2052, b2053)).name("x3356").srcCtx("GDA.scala:70:8") } // ParSRAMLoad(x3243,List(ArrayBuffer(b2052, b2053)),List(x3353))
    val x3357 = withCtrl(x3363) { x3356 } // VectorApply(x3356,0)
    val x3358 = withCtrl(x3363) { OpDef(op=BitAnd, inputs=List(b1930, x3353)).name("x3358").srcCtx("GDA.scala:70:8") } // And(b1930,x3353)
    val x3359 = withCtrl(x3363) { OpDef(op=FixEql, inputs=List(b1929, Const(0))).name("x3359").srcCtx("GDA.scala:70:8") } // FixEql(b1929,Const(0))
    val x3360 = withCtrl(x3363) { OpDef(op=FixAdd, inputs=List(x3355, x3357)).name("x3360").srcCtx("GDA.scala:70:10") } // FixAdd(x3355,x3357)
    val x3361 = withCtrl(x3363) { OpDef(op=MuxOp, inputs=List(x3359, x3355, x3360)).name("x3361").srcCtx("GDA.scala:70:8") } // Mux(x3359,x3355,x3360)
    val x3362 = withCtrl(x3363) { StoreBanks(List(List(x3243_d0_b0), List(x3243_d1_b0)), List(b2052, b2053), x3361).name("x3362").srcCtx("GDA.scala:70:8") } // ParSRAMStore(x3243,List(ArrayBuffer(b2052, b2053)),List(x3361),List(x3353))
    antiDepsOf(x3362)=List(x3356)
    val x3365 = withCtrl(x3393) { Counter(min=Const(0), max=Const(64), step=Const(1), par=1).name("x3365").srcCtx("GDA.scala:72:36") } // CounterNew(Const(0),Const(64),Const(1),Const(1))
    val x3366 = withCtrl(x3393) { CounterChain(List(x3365)).name("x3366").srcCtx("GDA.scala:72:36") } // CounterChainNew(List(x3365))
    val x3392 = withCtrl(x3393) { LoopController(style=StreamPipe, level=OuterControl, cchain=x3366).name("x3392").srcCtx("GDA.scala:72:36") } // UnrolledForeach(List(Const(true)),x3366,Block(Const(())),List(List(b2070)),List(List(b2071)))
    val b2070 = withCtrl(x3392) { CounterIter(x3365, Some(0)).name("b2070") } // b2070
    val b2071 = withCtrl(x3392) { Const(true).name("b2071") } // b2071
    val b3461 = withCtrl(x3392) { StreamOut(field="offset").name("b3461").srcCtx("GDA.scala:72:36") } // x3367 = StreamOutNew(BurstCmdBus)
    isAccum(b3461) = false
    bufferDepthOf(b3461) = 1
    val b3462 = withCtrl(x3392) { StreamOut(field="size").name("b3462").srcCtx("GDA.scala:72:36") } // x3367 = StreamOutNew(BurstCmdBus)
    isAccum(b3462) = false
    bufferDepthOf(b3462) = 1
    val x3368 = withCtrl(x3392) { StreamOut(field="data").name("x3368").srcCtx("GDA.scala:72:36") } // x3368 = StreamOutNew(BurstFullDataBus())
    isAccum(x3368) = false
    bufferDepthOf(x3368) = 1
    val x3369 = withCtrl(x3392) { StreamIn(field="ack").name("x3369").srcCtx("GDA.scala:72:36") } // x3369 = StreamInNew(BurstAckBus)
    isAccum(x3369) = false
    bufferDepthOf(x3369) = 1
    val x3380 = withCtrl(x3392) { UnitController(style=SeqPipe, level=InnerControl).name("x3380").srcCtx("GDA.scala:72:36") } // UnitPipe(List(b2071),Block(x3379))
    val x3370 = withCtrl(x3380) { b2070 } // FixConvert(b2070,TRUE,_32,_0) (Same Type. No op)
    val x3371 = withCtrl(x3380) { OpDef(op=FixSla, inputs=List(x3370, Const(6))).name("x3371").srcCtx("GDA.scala:72:36") } // FixLsh(x3370,Const(6))
    val x3372 = withCtrl(x3380) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x3373 = withCtrl(x3380) { OpDef(op=FixAdd, inputs=List(x3371, x3372)).name("x3373").srcCtx("GDA.scala:72:36") } // FixAdd(x3371,x3372)
    val x3374 = withCtrl(x3380) { OpDef(op=FixSla, inputs=List(x3373, Const(2))).name("x3374").srcCtx("GDA.scala:72:36") } // FixLsh(x3373,Const(2))
    val x3375 = withCtrl(x3380) { x3374 } // FixConvert(x3374,TRUE,_64,_0)
    val x3376 = withCtrl(x3380) { DramAddress(x3199).name("x3376").srcCtx("GDA.scala:72:36") } // GetDRAMAddress(x3199)
    val x3378_x3377 = withCtrl(x3380) { OpDef(op=FixAdd, inputs=List(x3375, x3376)).name("x3378_x3377").srcCtx("GDA.scala:72:36") } // FixAdd(x3375,x3376)
    // x3378 = SimpleStruct(ArrayBuffer((offset,x3377), (size,Const(256)), (isLoad,Const(false))))
    val x3379_b3463_b3461 = withCtrl(x3380) { WriteMem(b3461, x3378_x3377).name("x3379_b3463_b3461").srcCtx("GDA.scala:72:36") } // StreamWrite(x3367,x3378,b2071)
    val x3379_b3464_b3462 = withCtrl(x3380) { WriteMem(b3462, Const(256)).name("x3379_b3464_b3462").srcCtx("GDA.scala:72:36") } // StreamWrite(x3367,x3378,b2071)
    val x3381 = withCtrl(x3392) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x3381").srcCtx("GDA.scala:72:36") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x3382 = withCtrl(x3392) { CounterChain(List(x3381)).name("x3382").srcCtx("GDA.scala:72:36") } // CounterChainNew(List(x3381))
    val x3388 = withCtrl(x3392) { LoopController(style=InnerPipe, level=InnerControl, cchain=x3382).name("x3388").srcCtx("GDA.scala:72:36") } // UnrolledForeach(List(b2071),x3382,Block(Const(())),List(List(b2088)),List(List(b2089)))
    val b2088 = withCtrl(x3388) { CounterIter(x3381, None).name("b2088") } // b2088
    val b2089 = withCtrl(x3388) { Const(true).name("b2089") } // b2089
    val x3383 = withCtrl(x3388) { OpDef(op=BitAnd, inputs=List(b2089, b2071)).name("x3383").srcCtx("UnrollingBase.scala:28:66") } // And(b2089,b2071)
    val x3384 = withCtrl(x3388) { LoadBanks(List(x3243_d0_b0), List(b2070, b2088)).name("x3384").srcCtx("GDA.scala:72:36") } // ParSRAMLoad(x3243,List(List(b2070, b2088)),List(x3383))
    val x3386_x3385 = withCtrl(x3388) { x3384 } // VectorApply(x3384,0)
    // x3386 = SimpleStruct(ArrayBuffer((_1,x3385), (_2,Const(true))))
    val x3387_x3387_x3368 = withCtrl(x3388) { WriteMem(x3368, x3386_x3385).name("x3387_x3387_x3368").srcCtx("GDA.scala:72:36") } // ParStreamWrite(x3368,List(x3386),List(x3383))
    val x3389 = withCtrl(x3392) { FringeDenseStore(dram=List(x3199), cmdStream=List(b3461, b3462), dataStream=List(x3368), ackStream=List(x3369)).name("x3389").srcCtx("GDA.scala:72:36") } // FringeDenseStore(x3199,x3367,x3368,x3369)
    val x3391 = withCtrl(x3392) { UnitController(style=SeqPipe, level=InnerControl).name("x3391").srcCtx("GDA.scala:72:36") } // UnitPipe(List(b2071),Block(Const(())))
    val x3390_x3390 = withCtrl(x3391) { ReadMem(x3369).name("x3390_x3390").srcCtx("GDA.scala:72:36") } // StreamRead(x3369,b2071)
    
  }
}
