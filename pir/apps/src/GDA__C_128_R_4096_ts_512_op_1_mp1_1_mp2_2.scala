import pir._
import pir.node._
import arch._
import prism.enums._

object GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2 extends PIRApp {
  def main(implicit design:PIRDesign) = {
    import design.pirmeta._
    val x3255 = withCtrl(design.top.topController) { ArgIn(init=0).name("x3255").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:23:18:R") } // ArgInNew(Const(0))
    isAccum(x3255) = false
    bufferDepthOf(x3255) = 1
    boundOf(x3255) = 4096
    val x3257 = withCtrl(design.top.topController) { ReadMem(x3255).name("x3257").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:26:21") } // RegRead(x3255)
    val x3258 = withCtrl(design.top.topController) { DRAM(dims=List(x3257, Const(128))).name("x3258").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:26:20:x") } // x3258 = DRAMNew(ArrayBuffer(x3257, Const(128)),Const(0))
    val x3259 = withCtrl(design.top.topController) { ReadMem(x3255).name("x3259").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:27:23") } // RegRead(x3255)
    val x3260 = withCtrl(design.top.topController) { DRAM(dims=List(x3259)).name("x3260").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:27:22:y") } // x3260 = DRAMNew(ArrayBuffer(x3259),Const(0))
    val x3261 = withCtrl(design.top.topController) { DRAM(dims=List(Const(128))).name("x3261").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:28:22:mu0") } // x3261 = DRAMNew(ArrayBuffer(Const(128)),Const(0))
    val x3262 = withCtrl(design.top.topController) { DRAM(dims=List(Const(128))).name("x3262").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:29:22:mu1") } // x3262 = DRAMNew(ArrayBuffer(Const(128)),Const(0))
    val x3263 = withCtrl(design.top.topController) { DRAM(dims=List(Const(128), Const(128))).name("x3263").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:30:24:sigma") } // x3263 = DRAMNew(ArrayBuffer(Const(128), Const(128)),Const(0))
    val x3469 = withCtrl(design.top.topController) { UnitController(style=SeqPipe, level=OuterControl).name("x3469").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:37:11") } // Hwblock(Block(Const(())),false)
    val x3268_d0_b0 = withCtrl(x3469) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x3268_d0_b0").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:38:28:mu0Tile") } // x3268 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x3268_d0_b0) = false
    bufferDepthOf(x3268_d0_b0) = 1
    staticDimsOf(x3268_d0_b0) = List(128)
    val x3269_d0_b0 = withCtrl(x3469) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x3269_d0_b0").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:39:28:mu1Tile") } // x3269 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x3269_d0_b0) = false
    bufferDepthOf(x3269_d0_b0) = 1
    staticDimsOf(x3269_d0_b0) = List(128)
    val x3306 = withCtrl(x3469) { UnitController(style=ForkJoin, level=OuterControl).name("x3306").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:40:16") } // ParallelPipe(List(Const(true)),Block(Const(())))
    val x3287 = withCtrl(x3306) { UnitController(style=StreamPipe, level=OuterControl).name("x3287").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:41:17") } // UnitPipe(List(Const(true)),Block(Const(())))
    val b3521 = withCtrl(x3287) { StreamOut(field="offset").name("b3521").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:41:17") } // x3270 = StreamOutNew(BurstCmdBus)
    isAccum(b3521) = false
    bufferDepthOf(b3521) = 1
    val b3522 = withCtrl(x3287) { StreamOut(field="size").name("b3522").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:41:17") } // x3270 = StreamOutNew(BurstCmdBus)
    isAccum(b3522) = false
    bufferDepthOf(b3522) = 1
    val x3271 = withCtrl(x3287) { StreamIn(field="data").name("x3271").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:41:17") } // x3271 = StreamInNew(BurstDataBus())
    isAccum(x3271) = false
    bufferDepthOf(x3271) = 1
    val x3279 = withCtrl(x3287) { UnitController(style=SeqPipe, level=InnerControl).name("x3279").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:41:17") } // UnitPipe(List(Const(true)),Block(x3278))
    val x3272 = withCtrl(x3279) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x3273 = withCtrl(x3279) { OpDef(op=FixSla, inputs=List(x3272, Const(2))).name("x3273").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:41:17") } // FixLsh(x3272,Const(2))
    val x3274 = withCtrl(x3279) { x3273 } // FixConvert(x3273,TRUE,_64,_0)
    val x3275 = withCtrl(x3279) { DramAddress(x3261).name("x3275").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:41:17") } // GetDRAMAddress(x3261)
    val x3277_x3276 = withCtrl(x3279) { OpDef(op=FixAdd, inputs=List(x3274, x3275)).name("x3277_x3276").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:41:17") } // FixAdd(x3274,x3275)
    // x3277 = SimpleStruct(ArrayBuffer((offset,x3276), (size,Const(512)), (isLoad,Const(true))))
    val x3278_b3523_b3521 = withCtrl(x3279) { WriteMem(b3521, x3277_x3276).name("x3278_b3523_b3521").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:41:17") } // StreamWrite(x3270,x3277,Const(true))
    val x3278_b3524_b3522 = withCtrl(x3279) { WriteMem(b3522, Const(512)).name("x3278_b3524_b3522").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:41:17") } // StreamWrite(x3270,x3277,Const(true))
    val x3280 = withCtrl(x3287) { FringeDenseLoad(dram=List(x3261), cmdStream=List(b3521, b3522), dataStream=List(x3271)).name("x3280").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:41:17") } // FringeDenseLoad(x3261,x3270,x3271)
    val x3281 = withCtrl(x3287) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x3281").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:41:17") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x3282 = withCtrl(x3287) { CounterChain(List(x3281)).name("x3282").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:41:17") } // CounterChainNew(List(x3281))
    val x3286 = withCtrl(x3287) { LoopController(style=InnerPipe, level=InnerControl, cchain=x3282).name("x3286").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:41:17") } // UnrolledForeach(List(Const(true)),x3282,Block(Const(())),List(List(b1894)),List(List(b1895)))
    val b1894 = withCtrl(x3286) { CounterIter(x3281, None).name("b1894") } // b1894
    val b1895 = withCtrl(x3286) { Const(true).name("b1895") } // b1895
    val x3283_x3283 = withCtrl(x3286) { ReadMem(x3271).name("x3283_x3283").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:41:17") } // ParStreamRead(x3271,List(b1895))
    val x3284_x3284 = withCtrl(x3286) { x3283_x3283 } // VectorApply(x3283,0)
    val x3285 = withCtrl(x3286) { StoreBanks(List(List(x3268_d0_b0)), List(b1894), x3284_x3284).name("x3285").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:41:17") } // ParSRAMStore(x3268,List(List(b1894)),List(x3284),List(b1895))
    val x3305 = withCtrl(x3306) { UnitController(style=StreamPipe, level=OuterControl).name("x3305").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:42:17") } // UnitPipe(List(Const(true)),Block(Const(())))
    val b3525 = withCtrl(x3305) { StreamOut(field="offset").name("b3525").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:42:17") } // x3288 = StreamOutNew(BurstCmdBus)
    isAccum(b3525) = false
    bufferDepthOf(b3525) = 1
    val b3526 = withCtrl(x3305) { StreamOut(field="size").name("b3526").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:42:17") } // x3288 = StreamOutNew(BurstCmdBus)
    isAccum(b3526) = false
    bufferDepthOf(b3526) = 1
    val x3289 = withCtrl(x3305) { StreamIn(field="data").name("x3289").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:42:17") } // x3289 = StreamInNew(BurstDataBus())
    isAccum(x3289) = false
    bufferDepthOf(x3289) = 1
    val x3297 = withCtrl(x3305) { UnitController(style=SeqPipe, level=InnerControl).name("x3297").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:42:17") } // UnitPipe(List(Const(true)),Block(x3296))
    val x3290 = withCtrl(x3297) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x3291 = withCtrl(x3297) { OpDef(op=FixSla, inputs=List(x3290, Const(2))).name("x3291").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:42:17") } // FixLsh(x3290,Const(2))
    val x3292 = withCtrl(x3297) { x3291 } // FixConvert(x3291,TRUE,_64,_0)
    val x3293 = withCtrl(x3297) { DramAddress(x3262).name("x3293").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:42:17") } // GetDRAMAddress(x3262)
    val x3295_x3294 = withCtrl(x3297) { OpDef(op=FixAdd, inputs=List(x3292, x3293)).name("x3295_x3294").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:42:17") } // FixAdd(x3292,x3293)
    // x3295 = SimpleStruct(ArrayBuffer((offset,x3294), (size,Const(512)), (isLoad,Const(true))))
    val x3296_b3527_b3525 = withCtrl(x3297) { WriteMem(b3525, x3295_x3294).name("x3296_b3527_b3525").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:42:17") } // StreamWrite(x3288,x3295,Const(true))
    val x3296_b3528_b3526 = withCtrl(x3297) { WriteMem(b3526, Const(512)).name("x3296_b3528_b3526").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:42:17") } // StreamWrite(x3288,x3295,Const(true))
    val x3298 = withCtrl(x3305) { FringeDenseLoad(dram=List(x3262), cmdStream=List(b3525, b3526), dataStream=List(x3289)).name("x3298").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:42:17") } // FringeDenseLoad(x3262,x3288,x3289)
    val x3299 = withCtrl(x3305) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x3299").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:42:17") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x3300 = withCtrl(x3305) { CounterChain(List(x3299)).name("x3300").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:42:17") } // CounterChainNew(List(x3299))
    val x3304 = withCtrl(x3305) { LoopController(style=InnerPipe, level=InnerControl, cchain=x3300).name("x3304").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:42:17") } // UnrolledForeach(List(Const(true)),x3300,Block(Const(())),List(List(b1914)),List(List(b1915)))
    val b1914 = withCtrl(x3304) { CounterIter(x3299, None).name("b1914") } // b1914
    val b1915 = withCtrl(x3304) { Const(true).name("b1915") } // b1915
    val x3301_x3301 = withCtrl(x3304) { ReadMem(x3289).name("x3301_x3301").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:42:17") } // ParStreamRead(x3289,List(b1915))
    val x3302_x3302 = withCtrl(x3304) { x3301_x3301 } // VectorApply(x3301,0)
    val x3303 = withCtrl(x3304) { StoreBanks(List(List(x3269_d0_b0)), List(b1914), x3302_x3302).name("x3303").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:42:17") } // ParSRAMStore(x3269,List(List(b1914)),List(x3302),List(b1915))
    val x3307_d0_b0 = withCtrl(x3469) { SRAM(size=16384, banking=Strided(banks=16, stride=1)).name("x3307_d0_b0").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:45:29:sigmaOut") } // x3307 = SRAMNew(ArrayBuffer(Const(128), Const(128)))
    isAccum(x3307_d0_b0) = false
    bufferDepthOf(x3307_d0_b0) = 1
    staticDimsOf(x3307_d0_b0) = List(128, 128)
    val x3307_d1_b0 = withCtrl(x3469) { SRAM(size=16384, banking=Strided(banks=16, stride=1)).name("x3307_d1_b0").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:45:29:sigmaOut") } // x3307 = SRAMNew(ArrayBuffer(Const(128), Const(128)))
    isAccum(x3307_d1_b0) = true
    bufferDepthOf(x3307_d1_b0) = 1
    staticDimsOf(x3307_d1_b0) = List(128, 128)
    val x3308 = withCtrl(x3469) { ReadMem(x3255).name("x3308").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:47:34") } // RegRead(x3255)
    val x3309 = withCtrl(x3469) { Counter(min=Const(0), max=x3308, step=Const(512), par=1).name("x3309").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:47:42") } // CounterNew(Const(0),x3308,Const(512),Const(1))
    val x3310 = withCtrl(x3469) { CounterChain(List(x3309)).name("x3310").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:71:8") } // CounterChainNew(List(x3309))
    val x3440 = withCtrl(x3469) { LoopController(style=MetaPipe, level=OuterControl, cchain=x3310).name("x3440").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:71:8") } // UnrolledReduce(List(Const(true)),x3310,x3307,Block((x3307) => Const(())),List(List(b1929)),List(List(b1930)))
    val b1929 = withCtrl(x3440) { CounterIter(x3309, Some(0)).name("b1929") } // b1929
    val b1930 = withCtrl(x3440) { Const(true).name("b1930") } // b1930
    val x3311_d0_b0 = withCtrl(x3440) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x3311_d0_b0").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:48:33:gdaYtile") } // x3311 = SRAMNew(ArrayBuffer(Const(512)))
    isAccum(x3311_d0_b0) = false
    bufferDepthOf(x3311_d0_b0) = 2
    staticDimsOf(x3311_d0_b0) = List(512)
    val x3312_d0_b0 = withCtrl(x3440) { SRAM(size=65536, banking=Strided(banks=16, stride=1)).name("x3312_d0_b0").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:49:31:gdaXtile") } // x3312 = SRAMNew(ArrayBuffer(Const(512), Const(128)))
    isAccum(x3312_d0_b0) = false
    bufferDepthOf(x3312_d0_b0) = 2
    staticDimsOf(x3312_d0_b0) = List(512, 128)
    val x3361 = withCtrl(x3440) { UnitController(style=ForkJoin, level=OuterControl).name("x3361").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:51:18") } // ParallelPipe(List(b1930),Block(Const(())))
    val x3314 = withCtrl(x3361) { UnitController(style=SeqPipe, level=InnerControl).name("x3314").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:51:18") } // UnitPipe(List(b1930),Block(Const(())))
    val x3313 = withCtrl(x3314) { OpDef(op=FixAdd, inputs=List(b1929, Const(512))).name("x3313").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:52:34") } // FixAdd(b1929,Const(512))
    val x3333 = withCtrl(x3361) { UnitController(style=StreamPipe, level=OuterControl).name("x3333").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:52:20") } // UnitPipe(List(b1930),Block(Const(())))
    val b3529 = withCtrl(x3333) { StreamOut(field="offset").name("b3529").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:52:20") } // x3315 = StreamOutNew(BurstCmdBus)
    isAccum(b3529) = false
    bufferDepthOf(b3529) = 1
    val b3530 = withCtrl(x3333) { StreamOut(field="size").name("b3530").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:52:20") } // x3315 = StreamOutNew(BurstCmdBus)
    isAccum(b3530) = false
    bufferDepthOf(b3530) = 1
    val x3316 = withCtrl(x3333) { StreamIn(field="data").name("x3316").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:52:20") } // x3316 = StreamInNew(BurstDataBus())
    isAccum(x3316) = false
    bufferDepthOf(x3316) = 1
    val x3324 = withCtrl(x3333) { UnitController(style=SeqPipe, level=InnerControl).name("x3324").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:52:20") } // UnitPipe(List(b1930),Block(x3323))
    val x3317 = withCtrl(x3324) { b1929 } // FixConvert(b1929,TRUE,_32,_0) (Same Type. No op)
    val x3318 = withCtrl(x3324) { OpDef(op=FixSla, inputs=List(x3317, Const(2))).name("x3318").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:52:20") } // FixLsh(x3317,Const(2))
    val x3319 = withCtrl(x3324) { x3318 } // FixConvert(x3318,TRUE,_64,_0)
    val x3320 = withCtrl(x3324) { DramAddress(x3260).name("x3320").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:52:20") } // GetDRAMAddress(x3260)
    val x3322_x3321 = withCtrl(x3324) { OpDef(op=FixAdd, inputs=List(x3319, x3320)).name("x3322_x3321").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:52:20") } // FixAdd(x3319,x3320)
    // x3322 = SimpleStruct(ArrayBuffer((offset,x3321), (size,Const(2048)), (isLoad,Const(true))))
    val x3323_b3531_b3529 = withCtrl(x3324) { WriteMem(b3529, x3322_x3321).name("x3323_b3531_b3529").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:52:20") } // StreamWrite(x3315,x3322,b1930)
    val x3323_b3532_b3530 = withCtrl(x3324) { WriteMem(b3530, Const(2048)).name("x3323_b3532_b3530").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:52:20") } // StreamWrite(x3315,x3322,b1930)
    val x3325 = withCtrl(x3333) { FringeDenseLoad(dram=List(x3260), cmdStream=List(b3529, b3530), dataStream=List(x3316)).name("x3325").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:52:20") } // FringeDenseLoad(x3260,x3315,x3316)
    val x3326 = withCtrl(x3333) { Counter(min=Const(0), max=Const(512), step=Const(1), par=16).name("x3326").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:52:20") } // CounterNew(Const(0),Const(512),Const(1),Const(16))
    val x3327 = withCtrl(x3333) { CounterChain(List(x3326)).name("x3327").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:52:20") } // CounterChainNew(List(x3326))
    val x3332 = withCtrl(x3333) { LoopController(style=InnerPipe, level=InnerControl, cchain=x3327).name("x3332").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:52:20") } // UnrolledForeach(List(b1930),x3327,Block(Const(())),List(List(b1948)),List(List(b1949)))
    val b1948 = withCtrl(x3332) { CounterIter(x3326, None).name("b1948") } // b1948
    val b1949 = withCtrl(x3332) { Const(true).name("b1949") } // b1949
    val x3328 = withCtrl(x3332) { OpDef(op=BitAnd, inputs=List(b1949, b1930)).name("x3328").srcCtx("UnrollingBase.scala:28:66") } // And(b1949,b1930)
    val x3329_x3329 = withCtrl(x3332) { ReadMem(x3316).name("x3329_x3329").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:52:20") } // ParStreamRead(x3316,List(x3328))
    val x3330_x3330 = withCtrl(x3332) { x3329_x3329 } // VectorApply(x3329,0)
    val x3331 = withCtrl(x3332) { StoreBanks(List(List(x3311_d0_b0)), List(b1948), x3330_x3330).name("x3331").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:52:20") } // ParSRAMStore(x3311,List(List(b1948)),List(x3330),List(x3328))
    val x3334 = withCtrl(x3361) { Counter(min=Const(0), max=Const(512), step=Const(1), par=1).name("x3334").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:53:20") } // CounterNew(Const(0),Const(512),Const(1),Const(1))
    val x3335 = withCtrl(x3361) { CounterChain(List(x3334)).name("x3335").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:53:20") } // CounterChainNew(List(x3334))
    val x3360 = withCtrl(x3361) { LoopController(style=StreamPipe, level=OuterControl, cchain=x3335).name("x3360").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:53:20") } // UnrolledForeach(List(b1930),x3335,Block(Const(())),List(List(b1958)),List(List(b1959)))
    val b1958 = withCtrl(x3360) { CounterIter(x3334, Some(0)).name("b1958") } // b1958
    val b1959 = withCtrl(x3360) { Const(true).name("b1959") } // b1959
    val b3533 = withCtrl(x3360) { StreamOut(field="offset").name("b3533").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:53:20") } // x3336 = StreamOutNew(BurstCmdBus)
    isAccum(b3533) = false
    bufferDepthOf(b3533) = 1
    val b3534 = withCtrl(x3360) { StreamOut(field="size").name("b3534").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:53:20") } // x3336 = StreamOutNew(BurstCmdBus)
    isAccum(b3534) = false
    bufferDepthOf(b3534) = 1
    val x3337 = withCtrl(x3360) { StreamIn(field="data").name("x3337").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:53:20") } // x3337 = StreamInNew(BurstDataBus())
    isAccum(x3337) = false
    bufferDepthOf(x3337) = 1
    val x3350 = withCtrl(x3360) { UnitController(style=SeqPipe, level=InnerControl).name("x3350").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:53:20") } // UnitPipe(List(b1959, b1930),Block(x3349))
    val x3338 = withCtrl(x3350) { OpDef(op=FixAdd, inputs=List(b1929, b1958)).name("x3338").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:53:20") } // FixAdd(b1929,b1958)
    val x3339 = withCtrl(x3350) { x3338 } // FixConvert(x3338,TRUE,_32,_0) (Same Type. No op)
    val x3340 = withCtrl(x3350) { OpDef(op=FixSla, inputs=List(x3339, Const(7))).name("x3340").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:53:20") } // FixLsh(x3339,Const(7))
    val x3341 = withCtrl(x3350) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x3342 = withCtrl(x3350) { OpDef(op=FixAdd, inputs=List(x3340, x3341)).name("x3342").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:53:20") } // FixAdd(x3340,x3341)
    val x3343 = withCtrl(x3350) { OpDef(op=FixSla, inputs=List(x3342, Const(2))).name("x3343").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:53:20") } // FixLsh(x3342,Const(2))
    val x3344 = withCtrl(x3350) { x3343 } // FixConvert(x3343,TRUE,_64,_0)
    val x3345 = withCtrl(x3350) { DramAddress(x3258).name("x3345").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:53:20") } // GetDRAMAddress(x3258)
    val x3347_x3346 = withCtrl(x3350) { OpDef(op=FixAdd, inputs=List(x3344, x3345)).name("x3347_x3346").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:53:20") } // FixAdd(x3344,x3345)
    // x3347 = SimpleStruct(ArrayBuffer((offset,x3346), (size,Const(512)), (isLoad,Const(true))))
    val x3348 = withCtrl(x3350) { OpDef(op=BitAnd, inputs=List(b1959, b1930)).name("x3348").srcCtx("UnrollingBase.scala:28:66") } // And(b1959,b1930)
    val x3349_b3535_b3533 = withCtrl(x3350) { WriteMem(b3533, x3347_x3346).name("x3349_b3535_b3533").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:53:20") } // StreamWrite(x3336,x3347,x3348)
    val x3349_b3536_b3534 = withCtrl(x3350) { WriteMem(b3534, Const(512)).name("x3349_b3536_b3534").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:53:20") } // StreamWrite(x3336,x3347,x3348)
    val x3351 = withCtrl(x3360) { FringeDenseLoad(dram=List(x3258), cmdStream=List(b3533, b3534), dataStream=List(x3337)).name("x3351").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:53:20") } // FringeDenseLoad(x3258,x3336,x3337)
    val x3352 = withCtrl(x3360) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x3352").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:53:20") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x3353 = withCtrl(x3360) { CounterChain(List(x3352)).name("x3353").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:53:20") } // CounterChainNew(List(x3352))
    val x3359 = withCtrl(x3360) { LoopController(style=InnerPipe, level=InnerControl, cchain=x3353).name("x3359").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:53:20") } // UnrolledForeach(List(b1959, b1930),x3353,Block(Const(())),List(List(b1978)),List(List(b1979)))
    val b1978 = withCtrl(x3359) { CounterIter(x3352, None).name("b1978") } // b1978
    val b1979 = withCtrl(x3359) { Const(true).name("b1979") } // b1979
    val x3354 = withCtrl(x3359) { OpDef(op=BitAnd, inputs=List(b1979, b1959)).name("x3354").srcCtx("UnrollingBase.scala:28:66") } // And(b1979,b1959)
    val x3355 = withCtrl(x3359) { OpDef(op=BitAnd, inputs=List(x3354, b1930)).name("x3355").srcCtx("UnrollingBase.scala:28:66") } // And(x3354,b1930)
    val x3356_x3356 = withCtrl(x3359) { ReadMem(x3337).name("x3356_x3356").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:53:20") } // ParStreamRead(x3337,List(x3355))
    val x3357_x3357 = withCtrl(x3359) { x3356_x3356 } // VectorApply(x3356,0)
    val x3358 = withCtrl(x3359) { StoreBanks(List(List(x3312_d0_b0)), List(b1958, b1978), x3357_x3357).name("x3358").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:53:20") } // ParSRAMStore(x3312,List(List(b1958, b1978)),List(x3357),List(x3355))
    val x3362_d0_b0 = withCtrl(x3440) { SRAM(size=16384, banking=Strided(banks=16, stride=1)).name("x3362_d0_b0").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:56:31:sigmaBlk") } // x3362 = SRAMNew(ArrayBuffer(Const(128), Const(128)))
    isAccum(x3362_d0_b0) = false
    bufferDepthOf(x3362_d0_b0) = 2
    staticDimsOf(x3362_d0_b0) = List(128, 128)
    val x3362_d1_b0 = withCtrl(x3440) { SRAM(size=16384, banking=Strided(banks=16, stride=1)).name("x3362_d1_b0").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:56:31:sigmaBlk") } // x3362 = SRAMNew(ArrayBuffer(Const(128), Const(128)))
    isAccum(x3362_d1_b0) = true
    bufferDepthOf(x3362_d1_b0) = 1
    staticDimsOf(x3362_d1_b0) = List(128, 128)
    val x3363 = withCtrl(x3440) { Counter(min=Const(0), max=Const(512), step=Const(1), par=1).name("x3363").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:58:39") } // CounterNew(Const(0),Const(512),Const(1),Const(1))
    val x3364 = withCtrl(x3440) { CounterChain(List(x3363)).name("x3364").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:70:10") } // CounterChainNew(List(x3363))
    val x3425 = withCtrl(x3440) { LoopController(style=MetaPipe, level=OuterControl, cchain=x3364).name("x3425").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:70:10") } // UnrolledReduce(List(b1930),x3364,x3362,Block((x3362) => Const(())),List(List(b1994)),List(List(b1995)))
    val b1994 = withCtrl(x3425) { CounterIter(x3363, Some(0)).name("b1994") } // b1994
    val b1995 = withCtrl(x3425) { Const(true).name("b1995") } // b1995
    val x3365_d0_b0 = withCtrl(x3425) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x3365_d0_b0").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:59:32:subTile") } // x3365 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x3365_d0_b0) = false
    bufferDepthOf(x3365_d0_b0) = 2
    staticDimsOf(x3365_d0_b0) = List(128)
    val x3365_d1_b0 = withCtrl(x3425) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x3365_d1_b0").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:59:32:subTile") } // x3365 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x3365_d1_b0) = false
    bufferDepthOf(x3365_d1_b0) = 2
    staticDimsOf(x3365_d1_b0) = List(128)
    val x3365_d2_b0 = withCtrl(x3425) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x3365_d2_b0").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:59:32:subTile") } // x3365 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x3365_d2_b0) = false
    bufferDepthOf(x3365_d2_b0) = 2
    staticDimsOf(x3365_d2_b0) = List(128)
    val x3366_d0_b0 = withCtrl(x3425) { SRAM(size=8192, banking=Strided(banks=16, stride=1)).name("x3366_d0_b0").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:60:34:sigmaTile") } // x3366 = SRAMNew(ArrayBuffer(Const(128), Const(128)))
    isAccum(x3366_d0_b0) = false
    bufferDepthOf(x3366_d0_b0) = 2
    staticDimsOf(x3366_d0_b0) = List(128, 128)
    val x3366_d0_b1 = withCtrl(x3425) { SRAM(size=8192, banking=Strided(banks=16, stride=1)).name("x3366_d0_b1").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:60:34:sigmaTile") } // x3366 = SRAMNew(ArrayBuffer(Const(128), Const(128)))
    isAccum(x3366_d0_b1) = false
    bufferDepthOf(x3366_d0_b1) = 2
    staticDimsOf(x3366_d0_b1) = List(128, 128)
    val x3367 = withCtrl(x3425) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x3367").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:61:21") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x3368 = withCtrl(x3425) { CounterChain(List(x3367)).name("x3368").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:61:29") } // CounterChainNew(List(x3367))
    val x3382 = withCtrl(x3425) { LoopController(style=InnerPipe, level=InnerControl, cchain=x3368).name("x3382").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:61:29") } // UnrolledForeach(List(b1995, b1930),x3368,Block(Const(())),List(List(b2000)),List(List(b2001)))
    val b2000 = withCtrl(x3382) { CounterIter(x3367, None).name("b2000") } // b2000
    val b2001 = withCtrl(x3382) { Const(true).name("b2001") } // b2001
    val x3369 = withCtrl(x3382) { OpDef(op=BitAnd, inputs=List(b2001, b1995)).name("x3369").srcCtx("UnrollingBase.scala:28:66") } // And(b2001,b1995)
    val x3370 = withCtrl(x3382) { OpDef(op=BitAnd, inputs=List(x3369, b1930)).name("x3370").srcCtx("UnrollingBase.scala:28:66") } // And(x3369,b1930)
    val x3371 = withCtrl(x3382) { LoadBanks(List(x3312_d0_b0), List(b1994, b2000)).name("x3371").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:62:35") } // ParSRAMLoad(x3312,List(List(b1994, b2000)),List(x3370))
    val x3372 = withCtrl(x3382) { x3371 } // VectorApply(x3371,0)
    val x3373 = withCtrl(x3382) { LoadBanks(List(x3311_d0_b0), List(b1994)).name("x3373").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:62:58") } // SRAMLoad(x3311,ArrayBuffer(Const(512)),List(b1994),Const(0),x3370)
    val x3374 = withCtrl(x3382) { OpDef(op=FixEql, inputs=List(x3373, Const(1))).name("x3374").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:62:63") } // FixEql(x3373,Const(1))
    val x3375 = withCtrl(x3382) { LoadBanks(List(x3269_d0_b0), List(b2000)).name("x3375").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:62:76") } // ParSRAMLoad(x3269,List(List(b2000)),List(x3370))
    val x3376 = withCtrl(x3382) { x3375 } // VectorApply(x3375,0)
    val x3377 = withCtrl(x3382) { LoadBanks(List(x3268_d0_b0), List(b2000)).name("x3377").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:62:89") } // ParSRAMLoad(x3268,List(List(b2000)),List(x3370))
    val x3378 = withCtrl(x3382) { x3377 } // VectorApply(x3377,0)
    val x3379 = withCtrl(x3382) { OpDef(op=MuxOp, inputs=List(x3374, x3376, x3378)).name("x3379").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:62:49") } // Mux(x3374,x3376,x3378)
    val x3380 = withCtrl(x3382) { OpDef(op=FixSub, inputs=List(x3372, x3379)).name("x3380").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:62:44") } // FixSub(x3372,x3379)
    val x3381 = withCtrl(x3382) { StoreBanks(List(List(x3365_d0_b0), List(x3365_d1_b0), List(x3365_d2_b0)), List(b2000), x3380).name("x3381").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:62:25") } // ParSRAMStore(x3365,List(List(b2000)),List(x3380),List(x3370))
    val x3383 = withCtrl(x3425) { Counter(min=Const(0), max=Const(128), step=Const(1), par=2).name("x3383").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:64:26") } // CounterNew(Const(0),Const(128),Const(1),Const(2))
    val x3384 = withCtrl(x3425) { CounterChain(List(x3383)).name("x3384").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:64:35") } // CounterChainNew(List(x3383))
    val x3408 = withCtrl(x3425) { LoopController(style=MetaPipe, level=OuterControl, cchain=x3384).name("x3408").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:64:35") } // UnrolledForeach(List(b1995, b1930),x3384,Block(Const(())),List(List(b2018, b2019)),List(List(b2020, b2021)))
    val b2018 = withCtrl(x3408) { CounterIter(x3383, Some(0)).name("b2018") } // b2018
    val b2020 = withCtrl(x3408) { Const(true).name("b2020") } // b2020
    val b2019 = withCtrl(x3408) { CounterIter(x3383, Some(1)).name("b2019") } // b2019
    val b2021 = withCtrl(x3408) { Const(true).name("b2021") } // b2021
    val x3407 = withCtrl(x3408) { UnitController(style=ForkJoin, level=OuterControl).name("x3407").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b1995, b1930),Block(Const(())))
    val x3385 = withCtrl(x3407) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x3385").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:65:23") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x3386 = withCtrl(x3407) { CounterChain(List(x3385)).name("x3386").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:65:31") } // CounterChainNew(List(x3385))
    val x3395 = withCtrl(x3407) { LoopController(style=InnerPipe, level=InnerControl, cchain=x3386).name("x3395").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:65:31") } // UnrolledForeach(List(b2020, b1995, b1930),x3386,Block(Const(())),List(List(b2026)),List(List(b2027)))
    val b2026 = withCtrl(x3395) { CounterIter(x3385, None).name("b2026") } // b2026
    val b2027 = withCtrl(x3395) { Const(true).name("b2027") } // b2027
    val x3387 = withCtrl(x3395) { OpDef(op=BitAnd, inputs=List(b2027, b2020)).name("x3387").srcCtx("UnrollingBase.scala:28:66") } // And(b2027,b2020)
    val x3388 = withCtrl(x3395) { OpDef(op=BitAnd, inputs=List(b1995, b1930)).name("x3388").srcCtx("UnrollingBase.scala:28:66") } // And(b1995,b1930)
    val x3389 = withCtrl(x3395) { OpDef(op=BitAnd, inputs=List(x3387, x3388)).name("x3389").srcCtx("UnrollingBase.scala:28:66") } // And(x3387,x3388)
    val x3390 = withCtrl(x3395) { LoadBanks(List(x3365_d2_b0), List(b2018)).name("x3390").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:66:42") } // SRAMLoad(x3365,ArrayBuffer(Const(128)),List(b2018),Const(0),x3389)
    val x3391 = withCtrl(x3395) { LoadBanks(List(x3365_d0_b0), List(b2026)).name("x3391").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:66:56") } // ParSRAMLoad(x3365,List(List(b2026)),List(x3389))
    val x3392 = withCtrl(x3395) { x3391 } // VectorApply(x3391,0)
    val x3393 = withCtrl(x3395) { OpDef(op=FixMul, inputs=List(x3390, x3392)).name("x3393").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:66:47") } // FixMul(x3390,x3392)
    val x3394 = withCtrl(x3395) { StoreBanks(List(List(x3366_d0_b0)), List(b2018, b2026), x3393).name("x3394").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:66:33") } // ParSRAMStore(x3366,List(List(b2018, b2026)),List(x3393),List(x3389))
    val x3396 = withCtrl(x3407) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x3396").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:65:23") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x3397 = withCtrl(x3407) { CounterChain(List(x3396)).name("x3397").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:65:31") } // CounterChainNew(List(x3396))
    val x3406 = withCtrl(x3407) { LoopController(style=InnerPipe, level=InnerControl, cchain=x3397).name("x3406").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:65:31") } // UnrolledForeach(List(b2021, b1995, b1930),x3397,Block(Const(())),List(List(b2037)),List(List(b2038)))
    val b2037 = withCtrl(x3406) { CounterIter(x3396, None).name("b2037") } // b2037
    val b2038 = withCtrl(x3406) { Const(true).name("b2038") } // b2038
    val x3398 = withCtrl(x3406) { OpDef(op=BitAnd, inputs=List(b2038, b2021)).name("x3398").srcCtx("UnrollingBase.scala:28:66") } // And(b2038,b2021)
    val x3399 = withCtrl(x3406) { OpDef(op=BitAnd, inputs=List(b1995, b1930)).name("x3399").srcCtx("UnrollingBase.scala:28:66") } // And(b1995,b1930)
    val x3400 = withCtrl(x3406) { OpDef(op=BitAnd, inputs=List(x3398, x3399)).name("x3400").srcCtx("UnrollingBase.scala:28:66") } // And(x3398,x3399)
    val x3401 = withCtrl(x3406) { LoadBanks(List(x3365_d2_b0), List(b2019)).name("x3401").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:66:42") } // SRAMLoad(x3365,ArrayBuffer(Const(128)),List(b2019),Const(0),x3400)
    val x3402 = withCtrl(x3406) { LoadBanks(List(x3365_d1_b0), List(b2037)).name("x3402").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:66:56") } // ParSRAMLoad(x3365,List(List(b2037)),List(x3400))
    val x3403 = withCtrl(x3406) { x3402 } // VectorApply(x3402,0)
    val x3404 = withCtrl(x3406) { OpDef(op=FixMul, inputs=List(x3401, x3403)).name("x3404").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:66:47") } // FixMul(x3401,x3403)
    val x3405 = withCtrl(x3406) { StoreBanks(List(List(x3366_d0_b1)), List(b2019, b2037), x3404).name("x3405").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:66:33") } // ParSRAMStore(x3366,List(List(b2019, b2037)),List(x3404),List(x3400))
    val x3409 = withCtrl(x3425) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x3409").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:70:10") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x3410 = withCtrl(x3425) { Counter(min=Const(0), max=Const(128), step=Const(1), par=1).name("x3410").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:70:10") } // CounterNew(Const(0),Const(128),Const(1),Const(1))
    val x3411 = withCtrl(x3425) { CounterChain(List(x3410,x3409)).name("x3411").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:70:10") } // CounterChainNew(ArrayBuffer(x3410, x3409))
    val x3424 = withCtrl(x3425) { LoopController(style=InnerPipe, level=InnerControl, cchain=x3411).name("x3424").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:70:10") } // UnrolledForeach(List(),x3411,Block(Const(())),ArrayBuffer(List(b2050), List(b2051)),ArrayBuffer(List(b2052), List(b2053)))
    val b2050 = withCtrl(x3424) { CounterIter(x3410, Some(0)).name("b2050") } // b2050
    val b2052 = withCtrl(x3424) { Const(true).name("b2052") } // b2052
    val b2051 = withCtrl(x3424) { CounterIter(x3409, None).name("b2051") } // b2051
    val b2053 = withCtrl(x3424) { Const(true).name("b2053") } // b2053
    val x3412 = withCtrl(x3424) { OpDef(op=BitAnd, inputs=List(b2052, b2053)).name("x3412").srcCtx("UnrollingBase.scala:28:66") } // And(b2052,b2053)
    val x3413 = withCtrl(x3424) { OpDef(op=BitAnd, inputs=List(x3412, b1930)).name("x3413").srcCtx("UnrollingBase.scala:28:66") } // And(x3412,b1930)
    val x3414 = withCtrl(x3424) { LoadBanks(List(x3366_d0_b0, x3366_d0_b1), List(b2050, b2051)).name("x3414").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:70:10") } // ParSRAMLoad(x3366,List(ArrayBuffer(b2050, b2051)),List(x3413))
    val x3415 = withCtrl(x3424) { x3414 } // VectorApply(x3414,0)
    val x3416 = withCtrl(x3424) { LoadBanks(List(x3362_d1_b0), List(b2050, b2051)).name("x3416").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:70:10") } // ParSRAMLoad(x3362,List(ArrayBuffer(b2050, b2051)),List(x3413))
    val x3417 = withCtrl(x3424) { x3416 } // VectorApply(x3416,0)
    val x3418 = withCtrl(x3424) { OpDef(op=BitAnd, inputs=List(b1995, b1930)).name("x3418").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:70:10") } // And(b1995,b1930)
    val x3419 = withCtrl(x3424) { OpDef(op=BitAnd, inputs=List(x3418, x3413)).name("x3419").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:70:10") } // And(x3418,x3413)
    val x3420 = withCtrl(x3424) { OpDef(op=FixEql, inputs=List(b1994, Const(0))).name("x3420").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:70:10") } // FixEql(b1994,Const(0))
    val x3421 = withCtrl(x3424) { OpDef(op=FixAdd, inputs=List(x3415, x3417)).name("x3421").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:70:12") } // FixAdd(x3415,x3417)
    val x3422 = withCtrl(x3424) { OpDef(op=MuxOp, inputs=List(x3420, x3415, x3421)).name("x3422").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:70:10") } // Mux(x3420,x3415,x3421)
    val x3423 = withCtrl(x3424) { StoreBanks(List(List(x3362_d0_b0), List(x3362_d1_b0)), List(b2050, b2051), x3422).name("x3423").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:70:10") } // ParSRAMStore(x3362,List(ArrayBuffer(b2050, b2051)),List(x3422),List(x3413))
    antiDepsOf(x3423)=List(x3416)
    val x3426 = withCtrl(x3440) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x3426").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:71:8") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x3427 = withCtrl(x3440) { Counter(min=Const(0), max=Const(128), step=Const(1), par=1).name("x3427").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:71:8") } // CounterNew(Const(0),Const(128),Const(1),Const(1))
    val x3428 = withCtrl(x3440) { CounterChain(List(x3427,x3426)).name("x3428").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:71:8") } // CounterChainNew(ArrayBuffer(x3427, x3426))
    val x3439 = withCtrl(x3440) { LoopController(style=InnerPipe, level=InnerControl, cchain=x3428).name("x3439").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:71:8") } // UnrolledForeach(List(),x3428,Block(Const(())),ArrayBuffer(List(b2068), List(b2069)),ArrayBuffer(List(b2070), List(b2071)))
    val b2068 = withCtrl(x3439) { CounterIter(x3427, Some(0)).name("b2068") } // b2068
    val b2070 = withCtrl(x3439) { Const(true).name("b2070") } // b2070
    val b2069 = withCtrl(x3439) { CounterIter(x3426, None).name("b2069") } // b2069
    val b2071 = withCtrl(x3439) { Const(true).name("b2071") } // b2071
    val x3429 = withCtrl(x3439) { OpDef(op=BitAnd, inputs=List(b2070, b2071)).name("x3429").srcCtx("UnrollingBase.scala:28:66") } // And(b2070,b2071)
    val x3430 = withCtrl(x3439) { LoadBanks(List(x3362_d0_b0), List(b2068, b2069)).name("x3430").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:71:8") } // ParSRAMLoad(x3362,List(ArrayBuffer(b2068, b2069)),List(x3429))
    val x3431 = withCtrl(x3439) { x3430 } // VectorApply(x3430,0)
    val x3432 = withCtrl(x3439) { LoadBanks(List(x3307_d1_b0), List(b2068, b2069)).name("x3432").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:71:8") } // ParSRAMLoad(x3307,List(ArrayBuffer(b2068, b2069)),List(x3429))
    val x3433 = withCtrl(x3439) { x3432 } // VectorApply(x3432,0)
    val x3434 = withCtrl(x3439) { OpDef(op=BitAnd, inputs=List(b1930, x3429)).name("x3434").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:71:8") } // And(b1930,x3429)
    val x3435 = withCtrl(x3439) { OpDef(op=FixEql, inputs=List(b1929, Const(0))).name("x3435").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:71:8") } // FixEql(b1929,Const(0))
    val x3436 = withCtrl(x3439) { OpDef(op=FixAdd, inputs=List(x3431, x3433)).name("x3436").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:71:10") } // FixAdd(x3431,x3433)
    val x3437 = withCtrl(x3439) { OpDef(op=MuxOp, inputs=List(x3435, x3431, x3436)).name("x3437").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:71:8") } // Mux(x3435,x3431,x3436)
    val x3438 = withCtrl(x3439) { StoreBanks(List(List(x3307_d0_b0), List(x3307_d1_b0)), List(b2068, b2069), x3437).name("x3438").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:71:8") } // ParSRAMStore(x3307,List(ArrayBuffer(b2068, b2069)),List(x3437),List(x3429))
    antiDepsOf(x3438)=List(x3432)
    val x3441 = withCtrl(x3469) { Counter(min=Const(0), max=Const(128), step=Const(1), par=1).name("x3441").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:73:36") } // CounterNew(Const(0),Const(128),Const(1),Const(1))
    val x3442 = withCtrl(x3469) { CounterChain(List(x3441)).name("x3442").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:73:36") } // CounterChainNew(List(x3441))
    val x3468 = withCtrl(x3469) { LoopController(style=StreamPipe, level=OuterControl, cchain=x3442).name("x3468").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:73:36") } // UnrolledForeach(List(Const(true)),x3442,Block(Const(())),List(List(b2086)),List(List(b2087)))
    val b2086 = withCtrl(x3468) { CounterIter(x3441, Some(0)).name("b2086") } // b2086
    val b2087 = withCtrl(x3468) { Const(true).name("b2087") } // b2087
    val b3537 = withCtrl(x3468) { StreamOut(field="offset").name("b3537").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:73:36") } // x3443 = StreamOutNew(BurstCmdBus)
    isAccum(b3537) = false
    bufferDepthOf(b3537) = 1
    val b3538 = withCtrl(x3468) { StreamOut(field="size").name("b3538").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:73:36") } // x3443 = StreamOutNew(BurstCmdBus)
    isAccum(b3538) = false
    bufferDepthOf(b3538) = 1
    val x3444 = withCtrl(x3468) { StreamOut(field="data").name("x3444").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:73:36") } // x3444 = StreamOutNew(BurstFullDataBus())
    isAccum(x3444) = false
    bufferDepthOf(x3444) = 1
    val x3445 = withCtrl(x3468) { StreamIn(field="ack").name("x3445").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:73:36") } // x3445 = StreamInNew(BurstAckBus)
    isAccum(x3445) = false
    bufferDepthOf(x3445) = 1
    val x3456 = withCtrl(x3468) { UnitController(style=SeqPipe, level=InnerControl).name("x3456").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:73:36") } // UnitPipe(List(b2087),Block(x3455))
    val x3446 = withCtrl(x3456) { b2086 } // FixConvert(b2086,TRUE,_32,_0) (Same Type. No op)
    val x3447 = withCtrl(x3456) { OpDef(op=FixSla, inputs=List(x3446, Const(7))).name("x3447").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:73:36") } // FixLsh(x3446,Const(7))
    val x3448 = withCtrl(x3456) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x3449 = withCtrl(x3456) { OpDef(op=FixAdd, inputs=List(x3447, x3448)).name("x3449").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:73:36") } // FixAdd(x3447,x3448)
    val x3450 = withCtrl(x3456) { OpDef(op=FixSla, inputs=List(x3449, Const(2))).name("x3450").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:73:36") } // FixLsh(x3449,Const(2))
    val x3451 = withCtrl(x3456) { x3450 } // FixConvert(x3450,TRUE,_64,_0)
    val x3452 = withCtrl(x3456) { DramAddress(x3263).name("x3452").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:73:36") } // GetDRAMAddress(x3263)
    val x3454_x3453 = withCtrl(x3456) { OpDef(op=FixAdd, inputs=List(x3451, x3452)).name("x3454_x3453").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:73:36") } // FixAdd(x3451,x3452)
    // x3454 = SimpleStruct(ArrayBuffer((offset,x3453), (size,Const(512)), (isLoad,Const(false))))
    val x3455_b3539_b3537 = withCtrl(x3456) { WriteMem(b3537, x3454_x3453).name("x3455_b3539_b3537").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:73:36") } // StreamWrite(x3443,x3454,b2087)
    val x3455_b3540_b3538 = withCtrl(x3456) { WriteMem(b3538, Const(512)).name("x3455_b3540_b3538").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:73:36") } // StreamWrite(x3443,x3454,b2087)
    val x3457 = withCtrl(x3468) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x3457").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:73:36") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x3458 = withCtrl(x3468) { CounterChain(List(x3457)).name("x3458").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:73:36") } // CounterChainNew(List(x3457))
    val x3464 = withCtrl(x3468) { LoopController(style=InnerPipe, level=InnerControl, cchain=x3458).name("x3464").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:73:36") } // UnrolledForeach(List(b2087),x3458,Block(Const(())),List(List(b2104)),List(List(b2105)))
    val b2104 = withCtrl(x3464) { CounterIter(x3457, None).name("b2104") } // b2104
    val b2105 = withCtrl(x3464) { Const(true).name("b2105") } // b2105
    val x3459 = withCtrl(x3464) { OpDef(op=BitAnd, inputs=List(b2105, b2087)).name("x3459").srcCtx("UnrollingBase.scala:28:66") } // And(b2105,b2087)
    val x3460 = withCtrl(x3464) { LoadBanks(List(x3307_d0_b0), List(b2086, b2104)).name("x3460").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:73:36") } // ParSRAMLoad(x3307,List(List(b2086, b2104)),List(x3459))
    val x3462_x3461 = withCtrl(x3464) { x3460 } // VectorApply(x3460,0)
    // x3462 = SimpleStruct(ArrayBuffer((_1,x3461), (_2,Const(true))))
    val x3463_x3463_x3444 = withCtrl(x3464) { WriteMem(x3444, x3462_x3461).name("x3463_x3463_x3444").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:73:36") } // ParStreamWrite(x3444,List(x3462),List(x3459))
    val x3465 = withCtrl(x3468) { FringeDenseStore(dram=List(x3263), cmdStream=List(b3537, b3538), dataStream=List(x3444), ackStream=List(x3445)).name("x3465").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:73:36") } // FringeDenseStore(x3263,x3443,x3444,x3445)
    val x3467 = withCtrl(x3468) { UnitController(style=SeqPipe, level=InnerControl).name("x3467").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:73:36") } // UnitPipe(List(b2087),Block(Const(())))
    val x3466_x3466 = withCtrl(x3467) { ReadMem(x3445).name("x3466_x3466").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_1_mp2_2.scala:73:36") } // StreamRead(x3445,b2087)
    
  }
}
