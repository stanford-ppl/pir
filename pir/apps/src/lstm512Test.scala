import pir._
import pir.node._
import arch._
import prism.enums._

object lstm512Test extends PIRApp {
  def main(implicit design:PIRDesign) = {
    import design.pirmeta._
    val x3266 = withCtrl(design.top.topController) { ArgOut(init=0.0).name("x3266").srcCtx("sysml.scala:189:22:cArg") } // ArgOutNew(Const(0.0))
    isAccum(x3266) = false
    bufferDepthOf(x3266) = 1
    val x3267 = withCtrl(design.top.topController) { ArgOut(init=0.0).name("x3267").srcCtx("sysml.scala:190:22:hArg") } // ArgOutNew(Const(0.0))
    isAccum(x3267) = false
    bufferDepthOf(x3267) = 1
    val x3481 = withCtrl(design.top.topController) { UnitController(style=SeqPipe, level=OuterControl).name("x3481").srcCtx("sysml.scala:192:11") } // Hwblock(Block(Const(())),false)
    val x3268_d0_b0 = withCtrl(x3481) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x3268_d0_b0").srcCtx("DataGenerator.scala:236:4:wxhSrc") } // x3268 = LUTNew(List(512, 1024), Seq(Const(0.0)))
    isAccum(x3268_d0_b0) = false
    bufferDepthOf(x3268_d0_b0) = 1
    staticDimsOf(x3268_d0_b0) = List(512, 1024)
    val x3268_d1_b0 = withCtrl(x3481) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x3268_d1_b0").srcCtx("DataGenerator.scala:236:4:wxhSrc") } // x3268 = LUTNew(List(512, 1024), Seq(Const(0.0)))
    isAccum(x3268_d1_b0) = false
    bufferDepthOf(x3268_d1_b0) = 1
    staticDimsOf(x3268_d1_b0) = List(512, 1024)
    val x3269_d0_b0 = withCtrl(x3481) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x3269_d0_b0").srcCtx("DataGenerator.scala:236:4:wxhSrc") } // x3269 = LUTNew(List(512, 1024), Seq(Const(0.0)))
    isAccum(x3269_d0_b0) = false
    bufferDepthOf(x3269_d0_b0) = 1
    staticDimsOf(x3269_d0_b0) = List(512, 1024)
    val x3269_d1_b0 = withCtrl(x3481) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x3269_d1_b0").srcCtx("DataGenerator.scala:236:4:wxhSrc") } // x3269 = LUTNew(List(512, 1024), Seq(Const(0.0)))
    isAccum(x3269_d1_b0) = false
    bufferDepthOf(x3269_d1_b0) = 1
    staticDimsOf(x3269_d1_b0) = List(512, 1024)
    val x3270_d0_b0 = withCtrl(x3481) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x3270_d0_b0").srcCtx("DataGenerator.scala:236:4:wxhSrc") } // x3270 = LUTNew(List(512, 1024), Seq(Const(0.0)))
    isAccum(x3270_d0_b0) = false
    bufferDepthOf(x3270_d0_b0) = 1
    staticDimsOf(x3270_d0_b0) = List(512, 1024)
    val x3270_d1_b0 = withCtrl(x3481) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x3270_d1_b0").srcCtx("DataGenerator.scala:236:4:wxhSrc") } // x3270 = LUTNew(List(512, 1024), Seq(Const(0.0)))
    isAccum(x3270_d1_b0) = false
    bufferDepthOf(x3270_d1_b0) = 1
    staticDimsOf(x3270_d1_b0) = List(512, 1024)
    val x3271_d0_b0 = withCtrl(x3481) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x3271_d0_b0").srcCtx("DataGenerator.scala:236:4:wxhSrc") } // x3271 = LUTNew(List(512, 1024), Seq(Const(0.0)))
    isAccum(x3271_d0_b0) = false
    bufferDepthOf(x3271_d0_b0) = 1
    staticDimsOf(x3271_d0_b0) = List(512, 1024)
    val x3271_d1_b0 = withCtrl(x3481) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x3271_d1_b0").srcCtx("DataGenerator.scala:236:4:wxhSrc") } // x3271 = LUTNew(List(512, 1024), Seq(Const(0.0)))
    isAccum(x3271_d1_b0) = false
    bufferDepthOf(x3271_d1_b0) = 1
    staticDimsOf(x3271_d1_b0) = List(512, 1024)
    val x3272_d0_b0 = withCtrl(x3481) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x3272_d0_b0").srcCtx("DataGenerator.scala:220:28") } // x3272 = LUTNew(List(512), Seq(Const(0.0)))
    isAccum(x3272_d0_b0) = false
    bufferDepthOf(x3272_d0_b0) = 1
    staticDimsOf(x3272_d0_b0) = List(512)
    val x3273_d0_b0 = withCtrl(x3481) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x3273_d0_b0").srcCtx("DataGenerator.scala:220:28") } // x3273 = LUTNew(List(512), Seq(Const(0.0)))
    isAccum(x3273_d0_b0) = false
    bufferDepthOf(x3273_d0_b0) = 1
    staticDimsOf(x3273_d0_b0) = List(512)
    val x3274_d0_b0 = withCtrl(x3481) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x3274_d0_b0").srcCtx("DataGenerator.scala:220:28") } // x3274 = LUTNew(List(512), Seq(Const(0.0)))
    isAccum(x3274_d0_b0) = false
    bufferDepthOf(x3274_d0_b0) = 1
    staticDimsOf(x3274_d0_b0) = List(512)
    val x3275_d0_b0 = withCtrl(x3481) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x3275_d0_b0").srcCtx("DataGenerator.scala:220:28") } // x3275 = LUTNew(List(512), Seq(Const(0.0)))
    isAccum(x3275_d0_b0) = false
    bufferDepthOf(x3275_d0_b0) = 1
    staticDimsOf(x3275_d0_b0) = List(512)
    val x3276_d0_b0 = withCtrl(x3481) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x3276_d0_b0").srcCtx("DataGenerator.scala:220:28:cInit") } // x3276 = LUTNew(List(512), Seq(Const(0.0)))
    isAccum(x3276_d0_b0) = false
    bufferDepthOf(x3276_d0_b0) = 1
    staticDimsOf(x3276_d0_b0) = List(512)
    val x3277_d0_b0 = withCtrl(x3481) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x3277_d0_b0").srcCtx("DataGenerator.scala:220:28:hInit") } // x3277 = LUTNew(List(512), Seq(Const(0.0)))
    isAccum(x3277_d0_b0) = false
    bufferDepthOf(x3277_d0_b0) = 1
    staticDimsOf(x3277_d0_b0) = List(512)
    val x3277_d1_b0 = withCtrl(x3481) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x3277_d1_b0").srcCtx("DataGenerator.scala:220:28:hInit") } // x3277 = LUTNew(List(512), Seq(Const(0.0)))
    isAccum(x3277_d1_b0) = false
    bufferDepthOf(x3277_d1_b0) = 1
    staticDimsOf(x3277_d1_b0) = List(512)
    val x3277_d2_b0 = withCtrl(x3481) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x3277_d2_b0").srcCtx("DataGenerator.scala:220:28:hInit") } // x3277 = LUTNew(List(512), Seq(Const(0.0)))
    isAccum(x3277_d2_b0) = false
    bufferDepthOf(x3277_d2_b0) = 1
    staticDimsOf(x3277_d2_b0) = List(512)
    val x3277_d3_b0 = withCtrl(x3481) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x3277_d3_b0").srcCtx("DataGenerator.scala:220:28:hInit") } // x3277 = LUTNew(List(512), Seq(Const(0.0)))
    isAccum(x3277_d3_b0) = false
    bufferDepthOf(x3277_d3_b0) = 1
    staticDimsOf(x3277_d3_b0) = List(512)
    val x3278_d0_b0 = withCtrl(x3481) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x3278_d0_b0").srcCtx("DataGenerator.scala:220:28:xInit") } // x3278 = LUTNew(List(512), Seq(Const(0.0)))
    isAccum(x3278_d0_b0) = false
    bufferDepthOf(x3278_d0_b0) = 1
    staticDimsOf(x3278_d0_b0) = List(512)
    val x3278_d1_b0 = withCtrl(x3481) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x3278_d1_b0").srcCtx("DataGenerator.scala:220:28:xInit") } // x3278 = LUTNew(List(512), Seq(Const(0.0)))
    isAccum(x3278_d1_b0) = false
    bufferDepthOf(x3278_d1_b0) = 1
    staticDimsOf(x3278_d1_b0) = List(512)
    val x3278_d2_b0 = withCtrl(x3481) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x3278_d2_b0").srcCtx("DataGenerator.scala:220:28:xInit") } // x3278 = LUTNew(List(512), Seq(Const(0.0)))
    isAccum(x3278_d2_b0) = false
    bufferDepthOf(x3278_d2_b0) = 1
    staticDimsOf(x3278_d2_b0) = List(512)
    val x3278_d3_b0 = withCtrl(x3481) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x3278_d3_b0").srcCtx("DataGenerator.scala:220:28:xInit") } // x3278 = LUTNew(List(512), Seq(Const(0.0)))
    isAccum(x3278_d3_b0) = false
    bufferDepthOf(x3278_d3_b0) = 1
    staticDimsOf(x3278_d3_b0) = List(512)
    val x3279_d0_b0 = withCtrl(x3481) { SRAM(size=512, banking=Strided(banks=1, stride=1)).name("x3279_d0_b0").srcCtx("sysml.scala:209:22:c") } // x3279 = SRAMNew(ArrayBuffer(Const(512)))
    isAccum(x3279_d0_b0) = false
    bufferDepthOf(x3279_d0_b0) = 1
    staticDimsOf(x3279_d0_b0) = List(512)
    val x3280_d0_b0 = withCtrl(x3481) { SRAM(size=512, banking=Strided(banks=1, stride=1)).name("x3280_d0_b0").srcCtx("sysml.scala:210:22:h") } // x3280 = SRAMNew(ArrayBuffer(Const(512)))
    isAccum(x3280_d0_b0) = false
    bufferDepthOf(x3280_d0_b0) = 1
    staticDimsOf(x3280_d0_b0) = List(512)
    val x3281_d0_b0 = withCtrl(x3481) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x3281_d0_b0").srcCtx("sysml.scala:211:41:sigLUT") } // x3281 = LUTNew(List(1024), Seq(Const(-0.9999997615814208984375),Const(-0.9999997615814208984375),Const(-0.9999997615814208984375)... [1021 more]))
    isAccum(x3281_d0_b0) = false
    bufferDepthOf(x3281_d0_b0) = 1
    staticDimsOf(x3281_d0_b0) = List(1024)
    val x3282_d0_b0 = withCtrl(x3481) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x3282_d0_b0").srcCtx("sysml.scala:212:42:tanhLUT") } // x3282 = LUTNew(List(1024), Seq(Const(-0.9999997615814208984375),Const(-0.9999997615814208984375),Const(-0.9999997615814208984375)... [1021 more]))
    isAccum(x3282_d0_b0) = false
    bufferDepthOf(x3282_d0_b0) = 1
    staticDimsOf(x3282_d0_b0) = List(1024)
    val x3283 = withCtrl(x3481) { Counter(min=Const(0), max=Const(512), step=Const(1), par=1).name("x3283").srcCtx("sysml.scala:213:34") } // CounterNew(Const(0),Const(512),Const(1),Const(1))
    val x3284 = withCtrl(x3481) { CounterChain(List(x3283)).name("x3284").srcCtx("sysml.scala:213:49") } // CounterChainNew(List(x3283))
    val x3475 = withCtrl(x3481) { LoopController(style=MetaPipe, level=OuterControl, cchain=x3284).name("x3475").srcCtx("sysml.scala:213:49") } // UnrolledForeach(List(Const(true)),x3284,Block(Const(())),List(List(b2185)),List(List(b2186)))
    val b2185 = withCtrl(x3475) { CounterIter(x3283, Some(0)).name("b2185") } // b2185
    val b2186 = withCtrl(x3475) { Const(true).name("b2186") } // b2186
    val x3285_d0 = withCtrl(x3475) { Reg(init=Some(0.0)).name("x3285_d0").srcCtx("sysml.scala:410:32:g") } // x3285 = RegNew(Const(0.0))
    isAccum(x3285_d0) = false
    bufferDepthOf(x3285_d0) = 5
    val x3285_d1 = withCtrl(x3475) { Reg(init=Some(0.0)).name("x3285_d1").srcCtx("sysml.scala:410:32:g") } // x3285 = RegNew(Const(0.0))
    isAccum(x3285_d1) = true
    bufferDepthOf(x3285_d1) = 1
    val x3286 = withCtrl(x3475) { Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x3286").srcCtx("sysml.scala:411:26") } // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x3287 = withCtrl(x3475) { CounterChain(List(x3286)).name("x3287").srcCtx("sysml.scala:431:11") } // CounterChainNew(List(x3286))
    val x3318 = withCtrl(x3475) { LoopController(style=MetaPipe, level=OuterControl, cchain=x3287).name("x3318").srcCtx("sysml.scala:431:11") } // UnrolledReduce(List(b2186),x3287,x3285,Block((x3285) => Const(())),List(List(b2190)),List(List(b2191)))
    val b2190 = withCtrl(x3318) { CounterIter(x3286, Some(0)).name("b2190") } // b2190
    val b2191 = withCtrl(x3318) { Const(true).name("b2191") } // b2191
    val x3288_d0 = withCtrl(x3318) { Reg(init=Some(0.0)).name("x3288_d0").srcCtx("sysml.scala:413:39:gInner") } // x3288 = RegNew(Const(0.0))
    isAccum(x3288_d0) = false
    bufferDepthOf(x3288_d0) = 1
    val x3288_d1 = withCtrl(x3318) { Reg(init=Some(0.0)).name("x3288_d1").srcCtx("sysml.scala:413:39:gInner") } // x3288 = RegNew(Const(0.0))
    isAccum(x3288_d1) = true
    bufferDepthOf(x3288_d1) = 1
    val x3289 = withCtrl(x3318) { Counter(min=Const(0), max=Const(512), step=Const(1), par=64).name("x3289").srcCtx("sysml.scala:414:38") } // CounterNew(Const(0),Const(512),Const(1),Const(64))
    val x3290 = withCtrl(x3318) { CounterChain(List(x3289)).name("x3290").srcCtx("sysml.scala:428:13") } // CounterChainNew(List(x3289))
    val x3308 = withCtrl(x3318) { LoopController(style=InnerPipe, level=InnerControl, cchain=x3290).name("x3308").srcCtx("sysml.scala:428:13") } // UnrolledReduce(List(b2191, b2186),x3290,x3288,Block((x3288) => Const(())),List(List(b2195)),List(List(b2196)))
    val b2195 = withCtrl(x3308) { CounterIter(x3289, None).name("b2195") } // b2195
    val b2196 = withCtrl(x3308) { Const(true).name("b2196") } // b2196
    val x3291 = withCtrl(x3308) { OpDef(op=FixSla, inputs=List(b2190, Const(9))).name("x3291").srcCtx("sysml.scala:416:42") } // FixLsh(b2190,Const(9))
    val x3292 = withCtrl(x3308) { OpDef(op=FixAdd, inputs=List(x3291, b2195)).name("x3292").srcCtx("sysml.scala:416:64:iReduceIdx") } // FixAdd(x3291,b2195)
    val x3293 = withCtrl(x3308) { OpDef(op=BitAnd, inputs=List(b2196, b2191)).name("x3293").srcCtx("UnrollingBase.scala:28:66") } // And(b2196,b2191)
    val x3294 = withCtrl(x3308) { OpDef(op=BitAnd, inputs=List(x3293, b2186)).name("x3294").srcCtx("UnrollingBase.scala:28:66") } // And(x3293,b2186)
    val x3295 = withCtrl(x3308) { LoadBanks(List(x3268_d1_b0), List(b2185, x3292)).name("x3295").srcCtx("sysml.scala:417:32:wxEle") } // LUTLoad(x3268,List(b2185, x3292),x3294)
    val x3296 = withCtrl(x3308) { OpDef(op=FixAdd, inputs=List(x3292, Const(512))).name("x3296").srcCtx("sysml.scala:423:26") } // FixAdd(x3292,Const(512))
    val x3297 = withCtrl(x3308) { LoadBanks(List(x3268_d0_b0), List(b2185, x3296)).name("x3297").srcCtx("sysml.scala:421:32:whEle") } // LUTLoad(x3268,List(b2185, x3296),x3294)
    val x3298 = withCtrl(x3308) { LoadBanks(List(x3278_d3_b0), List(x3292)).name("x3298").srcCtx("sysml.scala:425:30:xEle") } // LUTLoad(x3278,List(x3292),x3294)
    val x3299 = withCtrl(x3308) { LoadBanks(List(x3277_d3_b0), List(x3292)).name("x3299").srcCtx("sysml.scala:426:30:hEle") } // LUTLoad(x3277,List(x3292),x3294)
    val x3300 = withCtrl(x3308) { OpDef(op=FltMul, inputs=List(x3297, x3299)).name("x3300").srcCtx("sysml.scala:427:19") } // FltMul(x3297,x3299)
    val x3301 = withCtrl(x3308) { OpDef(op=FltMul, inputs=List(x3295, x3298)).name("x3301").srcCtx("sysml.scala:427:34") } // FltMul(x3295,x3298)
    val x3302 = withCtrl(x3308) { OpDef(op=FltAdd, inputs=List(x3300, x3301)).name("x3302").srcCtx("sysml.scala:427:26") } // FltAdd(x3300,x3301)
    val x3303 = withCtrl(x3308) { ReadMem(x3288_d1).name("x3303").srcCtx("sysml.scala:428:13") } // RegRead(x3288)
    val x3304 = withCtrl(x3308) { OpDef(op=FixEql, inputs=List(b2195, Const(0))).name("x3304").srcCtx("sysml.scala:428:13") } // FixEql(b2195,Const(0))
    val x3305 = withCtrl(x3308) { ReduceAccumOp(op=FltAdd, input=x3302, accum=x3303).name("x3305").srcCtx("sysml.scala:428:15") } // FltAdd(x3302,x3303)
    val x3306 = withCtrl(x3308) { OpDef(op=BitAnd, inputs=List(b2191, b2186)).name("x3306").srcCtx("UnrollingBase.scala:28:66") } // And(b2191,b2186)
    val x3307_x3288_d0 = withCtrl(x3308) { WriteMem(x3288_d0, x3305).name("x3307_x3288_d0").srcCtx("sysml.scala:428:13") } // RegWrite(x3288,x3305,x3306)
    antiDepsOf(x3307_x3288_d0)=List(x3303)
    val x3307_x3288_d1 = withCtrl(x3308) { WriteMem(x3288_d1, x3305).name("x3307_x3288_d1").srcCtx("sysml.scala:428:13") } // RegWrite(x3288,x3305,x3306)
    antiDepsOf(x3307_x3288_d1)=List(x3303)
    val x3317 = withCtrl(x3318) { UnitController(style=SeqPipe, level=InnerControl).name("x3317").srcCtx("sysml.scala:431:11") } // UnitPipe(List(b2186),Block(x3316))
    val x3309 = withCtrl(x3317) { OpDef(op=BitAnd, inputs=List(b2191, b2186)).name("x3309").srcCtx("sysml.scala:431:11") } // And(b2191,b2186)
    val x3310 = withCtrl(x3317) { ReadMem(x3285_d1).name("x3310").srcCtx("sysml.scala:431:11") } // RegRead(x3285)
    val x3311 = withCtrl(x3317) { OpDef(op=FixEql, inputs=List(b2190, Const(0))).name("x3311").srcCtx("sysml.scala:431:11") } // FixEql(b2190,Const(0))
    val x3312 = withCtrl(x3317) { LoadBanks(List(x3272_d0_b0), List(b2185)).name("x3312").srcCtx("sysml.scala:432:23") } // LUTLoad(x3272,List(b2185),b2186)
    val x3313 = withCtrl(x3317) { ReadMem(x3288_d0).name("x3313").srcCtx("sysml.scala:430:11") } // RegRead(x3288)
    val x3314 = withCtrl(x3317) { OpDef(op=FltAdd, inputs=List(x3313, x3310)).name("x3314").srcCtx("sysml.scala:432:13") } // FltAdd(x3313,x3310)
    val x3315 = withCtrl(x3317) { OpDef(op=FltAdd, inputs=List(x3314, x3312)).name("x3315").srcCtx("sysml.scala:432:17") } // FltAdd(x3314,x3312)
    val x3316_x3285_d0 = withCtrl(x3317) { WriteMem(x3285_d0, x3315).name("x3316_x3285_d0").srcCtx("sysml.scala:431:11") } // RegWrite(x3285,x3315,b2186)
    antiDepsOf(x3316_x3285_d0)=List(x3310)
    val x3316_x3285_d1 = withCtrl(x3317) { WriteMem(x3285_d1, x3315).name("x3316_x3285_d1").srcCtx("sysml.scala:431:11") } // RegWrite(x3285,x3315,b2186)
    antiDepsOf(x3316_x3285_d1)=List(x3310)
    val x3319_d0 = withCtrl(x3475) { Reg(init=Some(0.0)).name("x3319_d0").srcCtx("sysml.scala:410:32:g") } // x3319 = RegNew(Const(0.0))
    isAccum(x3319_d0) = false
    bufferDepthOf(x3319_d0) = 4
    val x3319_d1 = withCtrl(x3475) { Reg(init=Some(0.0)).name("x3319_d1").srcCtx("sysml.scala:410:32:g") } // x3319 = RegNew(Const(0.0))
    isAccum(x3319_d1) = true
    bufferDepthOf(x3319_d1) = 1
    val x3320 = withCtrl(x3475) { Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x3320").srcCtx("sysml.scala:411:26") } // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x3321 = withCtrl(x3475) { CounterChain(List(x3320)).name("x3321").srcCtx("sysml.scala:431:11") } // CounterChainNew(List(x3320))
    val x3352 = withCtrl(x3475) { LoopController(style=MetaPipe, level=OuterControl, cchain=x3321).name("x3352").srcCtx("sysml.scala:431:11") } // UnrolledReduce(List(b2186),x3321,x3319,Block((x3319) => Const(())),List(List(b2228)),List(List(b2229)))
    val b2228 = withCtrl(x3352) { CounterIter(x3320, Some(0)).name("b2228") } // b2228
    val b2229 = withCtrl(x3352) { Const(true).name("b2229") } // b2229
    val x3322_d0 = withCtrl(x3352) { Reg(init=Some(0.0)).name("x3322_d0").srcCtx("sysml.scala:413:39:gInner") } // x3322 = RegNew(Const(0.0))
    isAccum(x3322_d0) = false
    bufferDepthOf(x3322_d0) = 1
    val x3322_d1 = withCtrl(x3352) { Reg(init=Some(0.0)).name("x3322_d1").srcCtx("sysml.scala:413:39:gInner") } // x3322 = RegNew(Const(0.0))
    isAccum(x3322_d1) = true
    bufferDepthOf(x3322_d1) = 1
    val x3323 = withCtrl(x3352) { Counter(min=Const(0), max=Const(512), step=Const(1), par=64).name("x3323").srcCtx("sysml.scala:414:38") } // CounterNew(Const(0),Const(512),Const(1),Const(64))
    val x3324 = withCtrl(x3352) { CounterChain(List(x3323)).name("x3324").srcCtx("sysml.scala:428:13") } // CounterChainNew(List(x3323))
    val x3342 = withCtrl(x3352) { LoopController(style=InnerPipe, level=InnerControl, cchain=x3324).name("x3342").srcCtx("sysml.scala:428:13") } // UnrolledReduce(List(b2229, b2186),x3324,x3322,Block((x3322) => Const(())),List(List(b2233)),List(List(b2234)))
    val b2233 = withCtrl(x3342) { CounterIter(x3323, None).name("b2233") } // b2233
    val b2234 = withCtrl(x3342) { Const(true).name("b2234") } // b2234
    val x3325 = withCtrl(x3342) { OpDef(op=FixSla, inputs=List(b2228, Const(9))).name("x3325").srcCtx("sysml.scala:416:42") } // FixLsh(b2228,Const(9))
    val x3326 = withCtrl(x3342) { OpDef(op=FixAdd, inputs=List(x3325, b2233)).name("x3326").srcCtx("sysml.scala:416:64:iReduceIdx") } // FixAdd(x3325,b2233)
    val x3327 = withCtrl(x3342) { OpDef(op=BitAnd, inputs=List(b2234, b2229)).name("x3327").srcCtx("UnrollingBase.scala:28:66") } // And(b2234,b2229)
    val x3328 = withCtrl(x3342) { OpDef(op=BitAnd, inputs=List(x3327, b2186)).name("x3328").srcCtx("UnrollingBase.scala:28:66") } // And(x3327,b2186)
    val x3329 = withCtrl(x3342) { LoadBanks(List(x3269_d1_b0), List(b2185, x3326)).name("x3329").srcCtx("sysml.scala:417:32:wxEle") } // LUTLoad(x3269,List(b2185, x3326),x3328)
    val x3330 = withCtrl(x3342) { OpDef(op=FixAdd, inputs=List(x3326, Const(512))).name("x3330").srcCtx("sysml.scala:423:26") } // FixAdd(x3326,Const(512))
    val x3331 = withCtrl(x3342) { LoadBanks(List(x3269_d0_b0), List(b2185, x3330)).name("x3331").srcCtx("sysml.scala:421:32:whEle") } // LUTLoad(x3269,List(b2185, x3330),x3328)
    val x3332 = withCtrl(x3342) { LoadBanks(List(x3278_d2_b0), List(x3326)).name("x3332").srcCtx("sysml.scala:425:30:xEle") } // LUTLoad(x3278,List(x3326),x3328)
    val x3333 = withCtrl(x3342) { LoadBanks(List(x3277_d2_b0), List(x3326)).name("x3333").srcCtx("sysml.scala:426:30:hEle") } // LUTLoad(x3277,List(x3326),x3328)
    val x3334 = withCtrl(x3342) { OpDef(op=FltMul, inputs=List(x3331, x3333)).name("x3334").srcCtx("sysml.scala:427:19") } // FltMul(x3331,x3333)
    val x3335 = withCtrl(x3342) { OpDef(op=FltMul, inputs=List(x3329, x3332)).name("x3335").srcCtx("sysml.scala:427:34") } // FltMul(x3329,x3332)
    val x3336 = withCtrl(x3342) { OpDef(op=FltAdd, inputs=List(x3334, x3335)).name("x3336").srcCtx("sysml.scala:427:26") } // FltAdd(x3334,x3335)
    val x3337 = withCtrl(x3342) { ReadMem(x3322_d1).name("x3337").srcCtx("sysml.scala:428:13") } // RegRead(x3322)
    val x3338 = withCtrl(x3342) { OpDef(op=FixEql, inputs=List(b2233, Const(0))).name("x3338").srcCtx("sysml.scala:428:13") } // FixEql(b2233,Const(0))
    val x3339 = withCtrl(x3342) { ReduceAccumOp(op=FltAdd, input=x3336, accum=x3337).name("x3339").srcCtx("sysml.scala:428:15") } // FltAdd(x3336,x3337)
    val x3340 = withCtrl(x3342) { OpDef(op=BitAnd, inputs=List(b2229, b2186)).name("x3340").srcCtx("UnrollingBase.scala:28:66") } // And(b2229,b2186)
    val x3341_x3322_d0 = withCtrl(x3342) { WriteMem(x3322_d0, x3339).name("x3341_x3322_d0").srcCtx("sysml.scala:428:13") } // RegWrite(x3322,x3339,x3340)
    antiDepsOf(x3341_x3322_d0)=List(x3337)
    val x3341_x3322_d1 = withCtrl(x3342) { WriteMem(x3322_d1, x3339).name("x3341_x3322_d1").srcCtx("sysml.scala:428:13") } // RegWrite(x3322,x3339,x3340)
    antiDepsOf(x3341_x3322_d1)=List(x3337)
    val x3351 = withCtrl(x3352) { UnitController(style=SeqPipe, level=InnerControl).name("x3351").srcCtx("sysml.scala:431:11") } // UnitPipe(List(b2186),Block(x3350))
    val x3343 = withCtrl(x3351) { OpDef(op=BitAnd, inputs=List(b2229, b2186)).name("x3343").srcCtx("sysml.scala:431:11") } // And(b2229,b2186)
    val x3344 = withCtrl(x3351) { ReadMem(x3319_d1).name("x3344").srcCtx("sysml.scala:431:11") } // RegRead(x3319)
    val x3345 = withCtrl(x3351) { OpDef(op=FixEql, inputs=List(b2228, Const(0))).name("x3345").srcCtx("sysml.scala:431:11") } // FixEql(b2228,Const(0))
    val x3346 = withCtrl(x3351) { LoadBanks(List(x3273_d0_b0), List(b2185)).name("x3346").srcCtx("sysml.scala:432:23") } // LUTLoad(x3273,List(b2185),b2186)
    val x3347 = withCtrl(x3351) { ReadMem(x3322_d0).name("x3347").srcCtx("sysml.scala:430:11") } // RegRead(x3322)
    val x3348 = withCtrl(x3351) { OpDef(op=FltAdd, inputs=List(x3347, x3344)).name("x3348").srcCtx("sysml.scala:432:13") } // FltAdd(x3347,x3344)
    val x3349 = withCtrl(x3351) { OpDef(op=FltAdd, inputs=List(x3348, x3346)).name("x3349").srcCtx("sysml.scala:432:17") } // FltAdd(x3348,x3346)
    val x3350_x3319_d0 = withCtrl(x3351) { WriteMem(x3319_d0, x3349).name("x3350_x3319_d0").srcCtx("sysml.scala:431:11") } // RegWrite(x3319,x3349,b2186)
    antiDepsOf(x3350_x3319_d0)=List(x3344)
    val x3350_x3319_d1 = withCtrl(x3351) { WriteMem(x3319_d1, x3349).name("x3350_x3319_d1").srcCtx("sysml.scala:431:11") } // RegWrite(x3319,x3349,b2186)
    antiDepsOf(x3350_x3319_d1)=List(x3344)
    val x3353_d0 = withCtrl(x3475) { Reg(init=Some(0.0)).name("x3353_d0").srcCtx("sysml.scala:410:32:g") } // x3353 = RegNew(Const(0.0))
    isAccum(x3353_d0) = false
    bufferDepthOf(x3353_d0) = 3
    val x3353_d1 = withCtrl(x3475) { Reg(init=Some(0.0)).name("x3353_d1").srcCtx("sysml.scala:410:32:g") } // x3353 = RegNew(Const(0.0))
    isAccum(x3353_d1) = true
    bufferDepthOf(x3353_d1) = 1
    val x3354 = withCtrl(x3475) { Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x3354").srcCtx("sysml.scala:411:26") } // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x3355 = withCtrl(x3475) { CounterChain(List(x3354)).name("x3355").srcCtx("sysml.scala:431:11") } // CounterChainNew(List(x3354))
    val x3386 = withCtrl(x3475) { LoopController(style=MetaPipe, level=OuterControl, cchain=x3355).name("x3386").srcCtx("sysml.scala:431:11") } // UnrolledReduce(List(b2186),x3355,x3353,Block((x3353) => Const(())),List(List(b2266)),List(List(b2267)))
    val b2266 = withCtrl(x3386) { CounterIter(x3354, Some(0)).name("b2266") } // b2266
    val b2267 = withCtrl(x3386) { Const(true).name("b2267") } // b2267
    val x3356_d0 = withCtrl(x3386) { Reg(init=Some(0.0)).name("x3356_d0").srcCtx("sysml.scala:413:39:gInner") } // x3356 = RegNew(Const(0.0))
    isAccum(x3356_d0) = false
    bufferDepthOf(x3356_d0) = 1
    val x3356_d1 = withCtrl(x3386) { Reg(init=Some(0.0)).name("x3356_d1").srcCtx("sysml.scala:413:39:gInner") } // x3356 = RegNew(Const(0.0))
    isAccum(x3356_d1) = true
    bufferDepthOf(x3356_d1) = 1
    val x3357 = withCtrl(x3386) { Counter(min=Const(0), max=Const(512), step=Const(1), par=64).name("x3357").srcCtx("sysml.scala:414:38") } // CounterNew(Const(0),Const(512),Const(1),Const(64))
    val x3358 = withCtrl(x3386) { CounterChain(List(x3357)).name("x3358").srcCtx("sysml.scala:428:13") } // CounterChainNew(List(x3357))
    val x3376 = withCtrl(x3386) { LoopController(style=InnerPipe, level=InnerControl, cchain=x3358).name("x3376").srcCtx("sysml.scala:428:13") } // UnrolledReduce(List(b2267, b2186),x3358,x3356,Block((x3356) => Const(())),List(List(b2271)),List(List(b2272)))
    val b2271 = withCtrl(x3376) { CounterIter(x3357, None).name("b2271") } // b2271
    val b2272 = withCtrl(x3376) { Const(true).name("b2272") } // b2272
    val x3359 = withCtrl(x3376) { OpDef(op=FixSla, inputs=List(b2266, Const(9))).name("x3359").srcCtx("sysml.scala:416:42") } // FixLsh(b2266,Const(9))
    val x3360 = withCtrl(x3376) { OpDef(op=FixAdd, inputs=List(x3359, b2271)).name("x3360").srcCtx("sysml.scala:416:64:iReduceIdx") } // FixAdd(x3359,b2271)
    val x3361 = withCtrl(x3376) { OpDef(op=BitAnd, inputs=List(b2272, b2267)).name("x3361").srcCtx("UnrollingBase.scala:28:66") } // And(b2272,b2267)
    val x3362 = withCtrl(x3376) { OpDef(op=BitAnd, inputs=List(x3361, b2186)).name("x3362").srcCtx("UnrollingBase.scala:28:66") } // And(x3361,b2186)
    val x3363 = withCtrl(x3376) { LoadBanks(List(x3270_d1_b0), List(b2185, x3360)).name("x3363").srcCtx("sysml.scala:417:32:wxEle") } // LUTLoad(x3270,List(b2185, x3360),x3362)
    val x3364 = withCtrl(x3376) { OpDef(op=FixAdd, inputs=List(x3360, Const(512))).name("x3364").srcCtx("sysml.scala:423:26") } // FixAdd(x3360,Const(512))
    val x3365 = withCtrl(x3376) { LoadBanks(List(x3270_d0_b0), List(b2185, x3364)).name("x3365").srcCtx("sysml.scala:421:32:whEle") } // LUTLoad(x3270,List(b2185, x3364),x3362)
    val x3366 = withCtrl(x3376) { LoadBanks(List(x3278_d1_b0), List(x3360)).name("x3366").srcCtx("sysml.scala:425:30:xEle") } // LUTLoad(x3278,List(x3360),x3362)
    val x3367 = withCtrl(x3376) { LoadBanks(List(x3277_d1_b0), List(x3360)).name("x3367").srcCtx("sysml.scala:426:30:hEle") } // LUTLoad(x3277,List(x3360),x3362)
    val x3368 = withCtrl(x3376) { OpDef(op=FltMul, inputs=List(x3365, x3367)).name("x3368").srcCtx("sysml.scala:427:19") } // FltMul(x3365,x3367)
    val x3369 = withCtrl(x3376) { OpDef(op=FltMul, inputs=List(x3363, x3366)).name("x3369").srcCtx("sysml.scala:427:34") } // FltMul(x3363,x3366)
    val x3370 = withCtrl(x3376) { OpDef(op=FltAdd, inputs=List(x3368, x3369)).name("x3370").srcCtx("sysml.scala:427:26") } // FltAdd(x3368,x3369)
    val x3371 = withCtrl(x3376) { ReadMem(x3356_d1).name("x3371").srcCtx("sysml.scala:428:13") } // RegRead(x3356)
    val x3372 = withCtrl(x3376) { OpDef(op=FixEql, inputs=List(b2271, Const(0))).name("x3372").srcCtx("sysml.scala:428:13") } // FixEql(b2271,Const(0))
    val x3373 = withCtrl(x3376) { ReduceAccumOp(op=FltAdd, input=x3370, accum=x3371).name("x3373").srcCtx("sysml.scala:428:15") } // FltAdd(x3370,x3371)
    val x3374 = withCtrl(x3376) { OpDef(op=BitAnd, inputs=List(b2267, b2186)).name("x3374").srcCtx("UnrollingBase.scala:28:66") } // And(b2267,b2186)
    val x3375_x3356_d0 = withCtrl(x3376) { WriteMem(x3356_d0, x3373).name("x3375_x3356_d0").srcCtx("sysml.scala:428:13") } // RegWrite(x3356,x3373,x3374)
    antiDepsOf(x3375_x3356_d0)=List(x3371)
    val x3375_x3356_d1 = withCtrl(x3376) { WriteMem(x3356_d1, x3373).name("x3375_x3356_d1").srcCtx("sysml.scala:428:13") } // RegWrite(x3356,x3373,x3374)
    antiDepsOf(x3375_x3356_d1)=List(x3371)
    val x3385 = withCtrl(x3386) { UnitController(style=SeqPipe, level=InnerControl).name("x3385").srcCtx("sysml.scala:431:11") } // UnitPipe(List(b2186),Block(x3384))
    val x3377 = withCtrl(x3385) { OpDef(op=BitAnd, inputs=List(b2267, b2186)).name("x3377").srcCtx("sysml.scala:431:11") } // And(b2267,b2186)
    val x3378 = withCtrl(x3385) { ReadMem(x3353_d1).name("x3378").srcCtx("sysml.scala:431:11") } // RegRead(x3353)
    val x3379 = withCtrl(x3385) { OpDef(op=FixEql, inputs=List(b2266, Const(0))).name("x3379").srcCtx("sysml.scala:431:11") } // FixEql(b2266,Const(0))
    val x3380 = withCtrl(x3385) { LoadBanks(List(x3274_d0_b0), List(b2185)).name("x3380").srcCtx("sysml.scala:432:23") } // LUTLoad(x3274,List(b2185),b2186)
    val x3381 = withCtrl(x3385) { ReadMem(x3356_d0).name("x3381").srcCtx("sysml.scala:430:11") } // RegRead(x3356)
    val x3382 = withCtrl(x3385) { OpDef(op=FltAdd, inputs=List(x3381, x3378)).name("x3382").srcCtx("sysml.scala:432:13") } // FltAdd(x3381,x3378)
    val x3383 = withCtrl(x3385) { OpDef(op=FltAdd, inputs=List(x3382, x3380)).name("x3383").srcCtx("sysml.scala:432:17") } // FltAdd(x3382,x3380)
    val x3384_x3353_d0 = withCtrl(x3385) { WriteMem(x3353_d0, x3383).name("x3384_x3353_d0").srcCtx("sysml.scala:431:11") } // RegWrite(x3353,x3383,b2186)
    antiDepsOf(x3384_x3353_d0)=List(x3378)
    val x3384_x3353_d1 = withCtrl(x3385) { WriteMem(x3353_d1, x3383).name("x3384_x3353_d1").srcCtx("sysml.scala:431:11") } // RegWrite(x3353,x3383,b2186)
    antiDepsOf(x3384_x3353_d1)=List(x3378)
    val x3387_d0 = withCtrl(x3475) { Reg(init=Some(0.0)).name("x3387_d0").srcCtx("sysml.scala:410:32:g") } // x3387 = RegNew(Const(0.0))
    isAccum(x3387_d0) = false
    bufferDepthOf(x3387_d0) = 2
    val x3387_d1 = withCtrl(x3475) { Reg(init=Some(0.0)).name("x3387_d1").srcCtx("sysml.scala:410:32:g") } // x3387 = RegNew(Const(0.0))
    isAccum(x3387_d1) = true
    bufferDepthOf(x3387_d1) = 1
    val x3388 = withCtrl(x3475) { Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x3388").srcCtx("sysml.scala:411:26") } // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x3389 = withCtrl(x3475) { CounterChain(List(x3388)).name("x3389").srcCtx("sysml.scala:431:11") } // CounterChainNew(List(x3388))
    val x3420 = withCtrl(x3475) { LoopController(style=MetaPipe, level=OuterControl, cchain=x3389).name("x3420").srcCtx("sysml.scala:431:11") } // UnrolledReduce(List(b2186),x3389,x3387,Block((x3387) => Const(())),List(List(b2304)),List(List(b2305)))
    val b2304 = withCtrl(x3420) { CounterIter(x3388, Some(0)).name("b2304") } // b2304
    val b2305 = withCtrl(x3420) { Const(true).name("b2305") } // b2305
    val x3390_d0 = withCtrl(x3420) { Reg(init=Some(0.0)).name("x3390_d0").srcCtx("sysml.scala:413:39:gInner") } // x3390 = RegNew(Const(0.0))
    isAccum(x3390_d0) = false
    bufferDepthOf(x3390_d0) = 1
    val x3390_d1 = withCtrl(x3420) { Reg(init=Some(0.0)).name("x3390_d1").srcCtx("sysml.scala:413:39:gInner") } // x3390 = RegNew(Const(0.0))
    isAccum(x3390_d1) = true
    bufferDepthOf(x3390_d1) = 1
    val x3391 = withCtrl(x3420) { Counter(min=Const(0), max=Const(512), step=Const(1), par=64).name("x3391").srcCtx("sysml.scala:414:38") } // CounterNew(Const(0),Const(512),Const(1),Const(64))
    val x3392 = withCtrl(x3420) { CounterChain(List(x3391)).name("x3392").srcCtx("sysml.scala:428:13") } // CounterChainNew(List(x3391))
    val x3410 = withCtrl(x3420) { LoopController(style=InnerPipe, level=InnerControl, cchain=x3392).name("x3410").srcCtx("sysml.scala:428:13") } // UnrolledReduce(List(b2305, b2186),x3392,x3390,Block((x3390) => Const(())),List(List(b2309)),List(List(b2310)))
    val b2309 = withCtrl(x3410) { CounterIter(x3391, None).name("b2309") } // b2309
    val b2310 = withCtrl(x3410) { Const(true).name("b2310") } // b2310
    val x3393 = withCtrl(x3410) { OpDef(op=FixSla, inputs=List(b2304, Const(9))).name("x3393").srcCtx("sysml.scala:416:42") } // FixLsh(b2304,Const(9))
    val x3394 = withCtrl(x3410) { OpDef(op=FixAdd, inputs=List(x3393, b2309)).name("x3394").srcCtx("sysml.scala:416:64:iReduceIdx") } // FixAdd(x3393,b2309)
    val x3395 = withCtrl(x3410) { OpDef(op=BitAnd, inputs=List(b2310, b2305)).name("x3395").srcCtx("UnrollingBase.scala:28:66") } // And(b2310,b2305)
    val x3396 = withCtrl(x3410) { OpDef(op=BitAnd, inputs=List(x3395, b2186)).name("x3396").srcCtx("UnrollingBase.scala:28:66") } // And(x3395,b2186)
    val x3397 = withCtrl(x3410) { LoadBanks(List(x3271_d1_b0), List(b2185, x3394)).name("x3397").srcCtx("sysml.scala:417:32:wxEle") } // LUTLoad(x3271,List(b2185, x3394),x3396)
    val x3398 = withCtrl(x3410) { OpDef(op=FixAdd, inputs=List(x3394, Const(512))).name("x3398").srcCtx("sysml.scala:423:26") } // FixAdd(x3394,Const(512))
    val x3399 = withCtrl(x3410) { LoadBanks(List(x3271_d0_b0), List(b2185, x3398)).name("x3399").srcCtx("sysml.scala:421:32:whEle") } // LUTLoad(x3271,List(b2185, x3398),x3396)
    val x3400 = withCtrl(x3410) { LoadBanks(List(x3278_d0_b0), List(x3394)).name("x3400").srcCtx("sysml.scala:425:30:xEle") } // LUTLoad(x3278,List(x3394),x3396)
    val x3401 = withCtrl(x3410) { LoadBanks(List(x3277_d0_b0), List(x3394)).name("x3401").srcCtx("sysml.scala:426:30:hEle") } // LUTLoad(x3277,List(x3394),x3396)
    val x3402 = withCtrl(x3410) { OpDef(op=FltMul, inputs=List(x3399, x3401)).name("x3402").srcCtx("sysml.scala:427:19") } // FltMul(x3399,x3401)
    val x3403 = withCtrl(x3410) { OpDef(op=FltMul, inputs=List(x3397, x3400)).name("x3403").srcCtx("sysml.scala:427:34") } // FltMul(x3397,x3400)
    val x3404 = withCtrl(x3410) { OpDef(op=FltAdd, inputs=List(x3402, x3403)).name("x3404").srcCtx("sysml.scala:427:26") } // FltAdd(x3402,x3403)
    val x3405 = withCtrl(x3410) { ReadMem(x3390_d1).name("x3405").srcCtx("sysml.scala:428:13") } // RegRead(x3390)
    val x3406 = withCtrl(x3410) { OpDef(op=FixEql, inputs=List(b2309, Const(0))).name("x3406").srcCtx("sysml.scala:428:13") } // FixEql(b2309,Const(0))
    val x3407 = withCtrl(x3410) { ReduceAccumOp(op=FltAdd, input=x3404, accum=x3405).name("x3407").srcCtx("sysml.scala:428:15") } // FltAdd(x3404,x3405)
    val x3408 = withCtrl(x3410) { OpDef(op=BitAnd, inputs=List(b2305, b2186)).name("x3408").srcCtx("UnrollingBase.scala:28:66") } // And(b2305,b2186)
    val x3409_x3390_d0 = withCtrl(x3410) { WriteMem(x3390_d0, x3407).name("x3409_x3390_d0").srcCtx("sysml.scala:428:13") } // RegWrite(x3390,x3407,x3408)
    antiDepsOf(x3409_x3390_d0)=List(x3405)
    val x3409_x3390_d1 = withCtrl(x3410) { WriteMem(x3390_d1, x3407).name("x3409_x3390_d1").srcCtx("sysml.scala:428:13") } // RegWrite(x3390,x3407,x3408)
    antiDepsOf(x3409_x3390_d1)=List(x3405)
    val x3419 = withCtrl(x3420) { UnitController(style=SeqPipe, level=InnerControl).name("x3419").srcCtx("sysml.scala:431:11") } // UnitPipe(List(b2186),Block(x3418))
    val x3411 = withCtrl(x3419) { OpDef(op=BitAnd, inputs=List(b2305, b2186)).name("x3411").srcCtx("sysml.scala:431:11") } // And(b2305,b2186)
    val x3412 = withCtrl(x3419) { ReadMem(x3387_d1).name("x3412").srcCtx("sysml.scala:431:11") } // RegRead(x3387)
    val x3413 = withCtrl(x3419) { OpDef(op=FixEql, inputs=List(b2304, Const(0))).name("x3413").srcCtx("sysml.scala:431:11") } // FixEql(b2304,Const(0))
    val x3414 = withCtrl(x3419) { LoadBanks(List(x3275_d0_b0), List(b2185)).name("x3414").srcCtx("sysml.scala:432:23") } // LUTLoad(x3275,List(b2185),b2186)
    val x3415 = withCtrl(x3419) { ReadMem(x3390_d0).name("x3415").srcCtx("sysml.scala:430:11") } // RegRead(x3390)
    val x3416 = withCtrl(x3419) { OpDef(op=FltAdd, inputs=List(x3415, x3412)).name("x3416").srcCtx("sysml.scala:432:13") } // FltAdd(x3415,x3412)
    val x3417 = withCtrl(x3419) { OpDef(op=FltAdd, inputs=List(x3416, x3414)).name("x3417").srcCtx("sysml.scala:432:17") } // FltAdd(x3416,x3414)
    val x3418_x3387_d0 = withCtrl(x3419) { WriteMem(x3387_d0, x3417).name("x3418_x3387_d0").srcCtx("sysml.scala:431:11") } // RegWrite(x3387,x3417,b2186)
    antiDepsOf(x3418_x3387_d0)=List(x3412)
    val x3418_x3387_d1 = withCtrl(x3419) { WriteMem(x3387_d1, x3417).name("x3418_x3387_d1").srcCtx("sysml.scala:431:11") } // RegWrite(x3387,x3417,b2186)
    antiDepsOf(x3418_x3387_d1)=List(x3412)
    val x3421 = withCtrl(x3475) { Reg(init=Some(0.0)).name("x3421").srcCtx("sysml.scala:662:24:siReg") } // x3421 = RegNew(Const(0.0))
    isAccum(x3421) = false
    bufferDepthOf(x3421) = 2
    val x3422 = withCtrl(x3475) { Reg(init=Some(0.0)).name("x3422").srcCtx("sysml.scala:663:24:tjReg") } // x3422 = RegNew(Const(0.0))
    isAccum(x3422) = false
    bufferDepthOf(x3422) = 2
    val x3423 = withCtrl(x3475) { Reg(init=Some(0.0)).name("x3423").srcCtx("sysml.scala:664:24:sfReg") } // x3423 = RegNew(Const(0.0))
    isAccum(x3423) = false
    bufferDepthOf(x3423) = 2
    val x3424 = withCtrl(x3475) { Reg(init=Some(0.0)).name("x3424").srcCtx("sysml.scala:665:24:soReg") } // x3424 = RegNew(Const(0.0))
    isAccum(x3424) = false
    bufferDepthOf(x3424) = 2
    val x3425 = withCtrl(x3475) { Counter(min=Const(0), max=Const(4), step=Const(1), par=4).name("x3425").srcCtx("sysml.scala:667:21") } // CounterNew(Const(0),Const(4),Const(1),Const(4))
    val x3426 = withCtrl(x3475) { CounterChain(List(x3425)).name("x3426").srcCtx("sysml.scala:667:28") } // CounterChainNew(List(x3425))
    val x3453 = withCtrl(x3475) { LoopController(style=InnerPipe, level=InnerControl, cchain=x3426).name("x3453").srcCtx("sysml.scala:667:28") } // UnrolledForeach(List(b2186),x3426,Block(Const(())),List(List(b2345)),List(List(b2346)))
    val b2345 = withCtrl(x3453) { CounterIter(x3425, None).name("b2345") } // b2345
    val b2346 = withCtrl(x3453) { Const(true).name("b2346") } // b2346
    val x3427 = withCtrl(x3453) { OpDef(op=FixEql, inputs=List(b2345, Const(0))).name("x3427").srcCtx("sysml.scala:669:15") } // FixEql(b2345,Const(0))
    val x3428 = withCtrl(x3453) { OpDef(op=FixEql, inputs=List(b2345, Const(1))).name("x3428").srcCtx("sysml.scala:670:17:isF") } // FixEql(b2345,Const(1))
    val x3429 = withCtrl(x3453) { OpDef(op=FixEql, inputs=List(b2345, Const(2))).name("x3429").srcCtx("sysml.scala:671:19") } // FixEql(b2345,Const(2))
    val x3430 = withCtrl(x3453) { ReadMem(x3387_d0).name("x3430").srcCtx("sysml.scala:435:11") } // RegRead(x3387)
    val x3431 = withCtrl(x3453) { ReadMem(x3353_d0).name("x3431").srcCtx("sysml.scala:435:11") } // RegRead(x3353)
    val x3432 = withCtrl(x3453) { OpDef(op=MuxOp, inputs=List(x3429, x3431, x3430)).name("x3432").srcCtx("sysml.scala:670:37") } // Mux(x3429,x3431,x3430)
    val x3433 = withCtrl(x3453) { ReadMem(x3319_d0).name("x3433").srcCtx("sysml.scala:435:11") } // RegRead(x3319)
    val x3434 = withCtrl(x3453) { OpDef(op=MuxOp, inputs=List(x3428, x3433, x3432)).name("x3434").srcCtx("sysml.scala:669:35") } // Mux(x3428,x3433,x3432)
    val x3435 = withCtrl(x3453) { ReadMem(x3285_d0).name("x3435").srcCtx("sysml.scala:435:11") } // RegRead(x3285)
    val x3436 = withCtrl(x3453) { OpDef(op=MuxOp, inputs=List(x3427, x3435, x3434)).name("x3436").srcCtx("sysml.scala:668:22:gate") } // Mux(x3427,x3435,x3434)
    val x3437 = withCtrl(x3453) { OpDef(op=FltSub, inputs=List(x3436, Const(-8))).name("x3437").srcCtx("sysml.scala:678:26") } // FltSub(x3436,Const(-8))
    val x3438 = withCtrl(x3453) { OpDef(op=FltMul, inputs=List(x3437, Const(64))).name("x3438").srcCtx("sysml.scala:678:36") } // FltMul(x3437,Const(64))
    val x3439 = withCtrl(x3453) { OpDef(op=FltPtToFixPt, inputs=List(x3438, Const(true), Const(32), Const(0))).name("x3439").srcCtx("sysml.scala:678:49:index") } // FltPtToFixPt(x3438,TRUE,_32,_0)
    val x3440 = withCtrl(x3453) { OpDef(op=BitAnd, inputs=List(b2346, b2186)).name("x3440").srcCtx("UnrollingBase.scala:28:66") } // And(b2346,b2186)
    val x3441 = withCtrl(x3453) { LoadBanks(List(x3281_d0_b0), List(x3439)).name("x3441").srcCtx("sysml.scala:679:27:sigRaw") } // LUTLoad(x3281,List(x3439),x3440)
    val x3442 = withCtrl(x3453) { LoadBanks(List(x3282_d0_b0), List(x3439)).name("x3442").srcCtx("sysml.scala:680:29:tanhRaw") } // LUTLoad(x3282,List(x3439),x3440)
    val x3443 = withCtrl(x3453) { OpDef(op=FltLt, inputs=List(Const(8), x3436)).name("x3443").srcCtx("sysml.scala:681:21:hi") } // FltLt(Const(8),x3436)
    val x3444 = withCtrl(x3453) { OpDef(op=FltLt, inputs=List(x3436, Const(-8))).name("x3444").srcCtx("sysml.scala:682:21:lo") } // FltLt(x3436,Const(-8))
    val x3445 = withCtrl(x3453) { OpDef(op=MuxOp, inputs=List(x3428, Const(-1), Const(0.0))).name("x3445").srcCtx("sysml.scala:685:23:loRaw") } // Mux(x3428,Const(-1),Const(0.0))
    val x3446 = withCtrl(x3453) { OpDef(op=MuxOp, inputs=List(x3428, x3442, x3441)).name("x3446").srcCtx("sysml.scala:690:24:actRaw") } // Mux(x3428,x3442,x3441)
    val x3447 = withCtrl(x3453) { OpDef(op=MuxOp, inputs=List(x3444, x3445, x3446)).name("x3447").srcCtx("sysml.scala:698:13") } // Mux(x3444,x3445,x3446)
    val x3448 = withCtrl(x3453) { OpDef(op=MuxOp, inputs=List(x3443, Const(1), x3447)).name("x3448").srcCtx("sysml.scala:695:24:actEle") } // Mux(x3443,Const(1),x3447)
    val x3449_x3421 = withCtrl(x3453) { WriteMem(x3421, x3448).name("x3449_x3421").srcCtx("sysml.scala:705:13") } // RegWrite(x3421,x3448,x3440)
    val x3450_x3422 = withCtrl(x3453) { WriteMem(x3422, x3448).name("x3450_x3422").srcCtx("sysml.scala:706:13") } // RegWrite(x3422,x3448,x3440)
    val x3451_x3423 = withCtrl(x3453) { WriteMem(x3423, x3448).name("x3451_x3423").srcCtx("sysml.scala:707:13") } // RegWrite(x3423,x3448,x3440)
    val x3452_x3424 = withCtrl(x3453) { WriteMem(x3424, x3448).name("x3452_x3424").srcCtx("sysml.scala:708:13") } // RegWrite(x3424,x3448,x3440)
    val x3454_d0_b0 = withCtrl(x3475) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x3454_d0_b0").srcCtx("NonLinearity.scala:48:37:lut") } // x3454 = LUTNew(List(1024), Seq(Const(-0.9999997615814208984375),Const(-0.9999997615814208984375),Const(-0.9999997615814208984375)... [1021 more]))
    isAccum(x3454_d0_b0) = false
    bufferDepthOf(x3454_d0_b0) = 1
    staticDimsOf(x3454_d0_b0) = List(1024)
    val x3474 = withCtrl(x3475) { UnitController(style=SeqPipe, level=InnerControl).name("x3474").srcCtx("sysml.scala:213:49") } // UnitPipe(List(b2186),Block(Const(())))
    val x3455 = withCtrl(x3474) { ReadMem(x3422).name("x3455").srcCtx("sysml.scala:712:20:tj") } // RegRead(x3422)
    val x3456 = withCtrl(x3474) { ReadMem(x3421).name("x3456").srcCtx("sysml.scala:711:20:si") } // RegRead(x3421)
    val x3457 = withCtrl(x3474) { OpDef(op=FltMul, inputs=List(x3456, x3455)).name("x3457").srcCtx("sysml.scala:716:21:sitjEM") } // FltMul(x3456,x3455)
    val x3458 = withCtrl(x3474) { LoadBanks(List(x3276_d0_b0), List(b2185)).name("x3458").srcCtx("sysml.scala:717:24") } // LUTLoad(x3276,List(b2185),b2186)
    val x3459 = withCtrl(x3474) { ReadMem(x3423).name("x3459").srcCtx("sysml.scala:713:20:sf") } // RegRead(x3423)
    val x3460 = withCtrl(x3474) { OpDef(op=FltMul, inputs=List(x3458, x3459)).name("x3460").srcCtx("sysml.scala:717:38:ctsfEM") } // FltMul(x3458,x3459)
    val x3461 = withCtrl(x3474) { OpDef(op=FltAdd, inputs=List(x3457, x3460)).name("x3461").srcCtx("sysml.scala:718:23:cNew") } // FltAdd(x3457,x3460)
    val x3462 = withCtrl(x3474) { StoreBanks(List(List(x3279_d0_b0)), List(b2185), x3461).name("x3462").srcCtx("sysml.scala:719:21") } // SRAMStore(x3279,ArrayBuffer(Const(512)),List(b2185),Const(0),x3461,b2186)
    val x3463 = withCtrl(x3474) { OpDef(op=FltSub, inputs=List(x3461, Const(-8))).name("x3463").srcCtx("NonLinearity.scala:49:22") } // FltSub(x3461,Const(-8))
    val x3464 = withCtrl(x3474) { OpDef(op=FltMul, inputs=List(x3463, Const(64))).name("x3464").srcCtx("NonLinearity.scala:49:30") } // FltMul(x3463,Const(64))
    val x3465 = withCtrl(x3474) { OpDef(op=FltPtToFixPt, inputs=List(x3464, Const(true), Const(32), Const(0))).name("x3465").srcCtx("NonLinearity.scala:49:41:index") } // FltPtToFixPt(x3464,TRUE,_32,_0)
    val x3466 = withCtrl(x3474) { LoadBanks(List(x3454_d0_b0), List(x3465)).name("x3466").srcCtx("NonLinearity.scala:50:17:lutVal") } // LUTLoad(x3454,List(x3465),b2186)
    val x3467 = withCtrl(x3474) { OpDef(op=FltLt, inputs=List(Const(8), x3461)).name("x3467").srcCtx("NonLinearity.scala:16:21:isHigh") } // FltLt(Const(8),x3461)
    val x3468 = withCtrl(x3474) { OpDef(op=FltLt, inputs=List(x3461, Const(-8))).name("x3468").srcCtx("NonLinearity.scala:17:20:isLow") } // FltLt(x3461,Const(-8))
    val x3469 = withCtrl(x3474) { OpDef(op=MuxOp, inputs=List(x3468, Const(-1), x3466)).name("x3469").srcCtx("NonLinearity.scala:18:24") } // Mux(x3468,Const(-1),x3466)
    val x3470 = withCtrl(x3474) { OpDef(op=MuxOp, inputs=List(x3467, Const(1), x3469)).name("x3470").srcCtx("NonLinearity.scala:18:8:cTanh") } // Mux(x3467,Const(1),x3469)
    val x3471 = withCtrl(x3474) { ReadMem(x3424).name("x3471").srcCtx("sysml.scala:714:20:so") } // RegRead(x3424)
    val x3472 = withCtrl(x3474) { OpDef(op=FltMul, inputs=List(x3470, x3471)).name("x3472").srcCtx("sysml.scala:721:22:hNew") } // FltMul(x3470,x3471)
    val x3473 = withCtrl(x3474) { StoreBanks(List(List(x3280_d0_b0)), List(b2185), x3472).name("x3473").srcCtx("sysml.scala:722:21") } // SRAMStore(x3280,ArrayBuffer(Const(512)),List(b2185),Const(0),x3472,b2186)
    val x3480 = withCtrl(x3481) { UnitController(style=SeqPipe, level=InnerControl).name("x3480").srcCtx("sysml.scala:192:11") } // UnitPipe(List(Const(true)),Block(Const(())))
    val x3476 = withCtrl(x3480) { LoadBanks(List(x3279_d0_b0), List(Const(0))).name("x3476").srcCtx("sysml.scala:236:17") } // SRAMLoad(x3279,ArrayBuffer(Const(512)),List(Const(0)),Const(0),Const(true))
    val x3477_x3266 = withCtrl(x3480) { WriteMem(x3266, x3476).name("x3477_x3266").srcCtx("sysml.scala:236:12") } // RegWrite(x3266,x3476,Const(true))
    val x3478 = withCtrl(x3480) { LoadBanks(List(x3280_d0_b0), List(Const(0))).name("x3478").srcCtx("sysml.scala:237:17") } // SRAMLoad(x3280,ArrayBuffer(Const(512)),List(Const(0)),Const(0),Const(true))
    val x3479_x3267 = withCtrl(x3480) { WriteMem(x3267, x3478).name("x3479_x3267").srcCtx("sysml.scala:237:12") } // RegWrite(x3267,x3478,Const(true))
    
  }
}
