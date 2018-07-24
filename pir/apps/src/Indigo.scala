import pir._
import pir.node._
import arch._
import prism.enums._

object Indigo extends PIRApp {
  def main(implicit design:PIRDesign) = {
    import design.pirmeta._
    val x4274 = withCtrl(design.top.topController) { ArgIn(init=0.0).name("x4274").srcCtx("Indigo.scala:48:30:x") } // ArgInNew(Const(0.0))
    isAccum(x4274) = false
    bufferDepthOf(x4274) = 1
    val x4275 = withCtrl(design.top.topController) { ArgOut(init=0.0).name("x4275").srcCtx("Indigo.scala:49:33:out") } // ArgOutNew(Const(0.0))
    isAccum(x4275) = false
    bufferDepthOf(x4275) = 1
    // x2 = StringToFltPt(x1,_24,_8) TODO: Unmatched Node
    val x4501 = withCtrl(design.top.topController) { UnitController(style=SeqPipe, level=OuterControl).name("x4501").srcCtx("Indigo.scala:53:23") } // Hwblock(Block(Const(())),false)
    val x4277 = withCtrl(x4501) { Reg(init=Some(0.0)).name("x4277").srcCtx("Indigo.scala:84:47:s_reg") } // x4277 = RegNew(Const(0.0))
    isAccum(x4277) = false
    bufferDepthOf(x4277) = 1
    val x4278_d0_b0 = withCtrl(x4501) { RegFile(size=2, inits=None).name("x4278_d0_b0").srcCtx("Indigo.scala:85:50:L1_h") } // x4278 = RegFileNew(ArrayBuffer(Const(2)),None) banking:Strided(banks=1, stride=1)
    isAccum(x4278_d0_b0) = false
    bufferDepthOf(x4278_d0_b0) = 1
    staticDimsOf(x4278_d0_b0) = List(2)
    val x4278_d1_b0 = withCtrl(x4501) { RegFile(size=2, inits=None).name("x4278_d1_b0").srcCtx("Indigo.scala:85:50:L1_h") } // x4278 = RegFileNew(ArrayBuffer(Const(2)),None) banking:Strided(banks=1, stride=1)
    isAccum(x4278_d1_b0) = false
    bufferDepthOf(x4278_d1_b0) = 1
    staticDimsOf(x4278_d1_b0) = List(2)
    val x4279_d0_b0 = withCtrl(x4501) { RegFile(size=8, inits=None).name("x4279_d0_b0").srcCtx("Indigo.scala:86:52:L1_tmp") } // x4279 = RegFileNew(ArrayBuffer(Const(8)),None) banking:Strided(banks=1, stride=1)
    isAccum(x4279_d0_b0) = false
    bufferDepthOf(x4279_d0_b0) = 1
    staticDimsOf(x4279_d0_b0) = List(8)
    val x4279_d1_b0 = withCtrl(x4501) { RegFile(size=8, inits=None).name("x4279_d1_b0").srcCtx("Indigo.scala:86:52:L1_tmp") } // x4279 = RegFileNew(ArrayBuffer(Const(8)),None) banking:Strided(banks=1, stride=1)
    isAccum(x4279_d1_b0) = false
    bufferDepthOf(x4279_d1_b0) = 1
    staticDimsOf(x4279_d1_b0) = List(8)
    val x4279_d2_b0 = withCtrl(x4501) { RegFile(size=8, inits=None).name("x4279_d2_b0").srcCtx("Indigo.scala:86:52:L1_tmp") } // x4279 = RegFileNew(ArrayBuffer(Const(8)),None) banking:Strided(banks=1, stride=1)
    isAccum(x4279_d2_b0) = false
    bufferDepthOf(x4279_d2_b0) = 1
    staticDimsOf(x4279_d2_b0) = List(8)
    val x4279_d3_b0 = withCtrl(x4501) { RegFile(size=8, inits=None).name("x4279_d3_b0").srcCtx("Indigo.scala:86:52:L1_tmp") } // x4279 = RegFileNew(ArrayBuffer(Const(8)),None) banking:Strided(banks=1, stride=1)
    isAccum(x4279_d3_b0) = false
    bufferDepthOf(x4279_d3_b0) = 1
    staticDimsOf(x4279_d3_b0) = List(8)
    val x4280 = withCtrl(x4501) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x4280").srcCtx("Indigo.scala:89:35") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x4281 = withCtrl(x4501) { CounterChain(List(x4280)).name("x4281").srcCtx("Indigo.scala:89:54") } // CounterChainNew(List(x4280))
    val x4283 = withCtrl(x4501) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4281).name("x4283").srcCtx("Indigo.scala:89:54") } // UnrolledForeach(List(Const(true)),x4281,Block(Const(())),List(List(b3117)),List(List(b3118)))
    val b3117 = withCtrl(x4283) { CounterIter(x4280, None).name("b3117") } // b3117
    val b3118 = withCtrl(x4283) { Const(true).name("b3118") } // b3118
    val x4282 = withCtrl(x4283) { StoreBanks(List(List(x4278_d0_b0), List(x4278_d1_b0)), List(b3117), Const(0.0)).name("x4282").srcCtx("Indigo.scala:90:41") } // ParRegFileStore(x4278,List(List(b3117)),List(Const(0.0)),List(Const(true)))
    val x4284_d0_b0 = withCtrl(x4501) { RegFile(size=2, inits=None).name("x4284_d0_b0").srcCtx("Indigo.scala:93:50:L1_C") } // x4284 = RegFileNew(ArrayBuffer(Const(2)),None) banking:Strided(banks=1, stride=1)
    isAccum(x4284_d0_b0) = false
    bufferDepthOf(x4284_d0_b0) = 1
    staticDimsOf(x4284_d0_b0) = List(2)
    val x4284_d1_b0 = withCtrl(x4501) { RegFile(size=2, inits=None).name("x4284_d1_b0").srcCtx("Indigo.scala:93:50:L1_C") } // x4284 = RegFileNew(ArrayBuffer(Const(2)),None) banking:Strided(banks=1, stride=1)
    isAccum(x4284_d1_b0) = true
    bufferDepthOf(x4284_d1_b0) = 1
    staticDimsOf(x4284_d1_b0) = List(2)
    val x4285 = withCtrl(x4501) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x4285").srcCtx("Indigo.scala:95:35") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x4286 = withCtrl(x4501) { CounterChain(List(x4285)).name("x4286").srcCtx("Indigo.scala:95:54") } // CounterChainNew(List(x4285))
    val x4288 = withCtrl(x4501) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4286).name("x4288").srcCtx("Indigo.scala:95:54") } // UnrolledForeach(List(Const(true)),x4286,Block(Const(())),List(List(b3124)),List(List(b3125)))
    val b3124 = withCtrl(x4288) { CounterIter(x4285, None).name("b3124") } // b3124
    val b3125 = withCtrl(x4288) { Const(true).name("b3125") } // b3125
    val x4287 = withCtrl(x4288) { StoreBanks(List(List(x4284_d0_b0), List(x4284_d1_b0)), List(b3124), Const(0.0)).name("x4287").srcCtx("Indigo.scala:96:41") } // ParRegFileStore(x4284,List(List(b3124)),List(Const(0.0)),List(Const(true)))
    val x4289_d0_b0 = withCtrl(x4501) { RegFile(size=5, inits=None).name("x4289_d0_b0").srcCtx("Indigo.scala:99:52:L2_tmp") } // x4289 = RegFileNew(ArrayBuffer(Const(5)),None) banking:Strided(banks=1, stride=1)
    isAccum(x4289_d0_b0) = false
    bufferDepthOf(x4289_d0_b0) = 1
    staticDimsOf(x4289_d0_b0) = List(5)
    val x4290_d0_b0 = withCtrl(x4501) { LUT(inits=List(17.0), banking=Strided(banks=1, stride=1)).name("x4290_d0_b0").srcCtx("Indigo.scala:101:79:input") } // x4290 = LUTNew(List(1, 1), Seq(Const(17.0000)))
    isAccum(x4290_d0_b0) = false
    bufferDepthOf(x4290_d0_b0) = 1
    staticDimsOf(x4290_d0_b0) = List(1, 1)
    val x4291_d0_b0 = withCtrl(x4501) { LUT(inits=List(-0.0919745, -0.17325345, 0.07901578, -0.22862436, -0.33561853, 0.37217242, 0.28228363, 0.62458235, -0.710848, 0.6203918, -0.63714087, 0.33077937, 0.7237721, -0.70807374, -0.7135825, 0.3864259, 0.36924678, -0.63785505, 0.16256636, -0.26040027, -0.35571584, 0.15920722, -0.22067755, 0.45712513), banking=Strided(banks=1, stride=8)).name("x4291_d0_b0").srcCtx("Indigo.scala:103:119:L1_W_LUT") } // x4291 = LUTNew(List(3, 8), Seq(Const(-0.0919744968414306640625),Const(-0.1732534468173980712890625),Const(0.07901577651500701904296875)... [21 more]))
    isAccum(x4291_d0_b0) = false
    bufferDepthOf(x4291_d0_b0) = 1
    staticDimsOf(x4291_d0_b0) = List(3, 8)
    val x4292_d0_b0 = withCtrl(x4501) { LUT(inits=List(1.5417027E-5, 7.0991955E-4, 1.34923275E-5, -5.104995E-6, 0.0, 0.0, 3.5079447E-7, 1.2294923E-7), banking=Strided(banks=1, stride=1)).name("x4292_d0_b0").srcCtx("Indigo.scala:104:92:L1_B_LUT") } // x4292 = LUTNew(List(8), Seq(Const(0.00001541702658869326114654541015625),Const(0.0007099195499904453754425048828125),Const(0.00001349232752545503899455070495605469)... [5 more]))
    isAccum(x4292_d0_b0) = false
    bufferDepthOf(x4292_d0_b0) = 1
    staticDimsOf(x4292_d0_b0) = List(8)
    val x4293_d0_b0 = withCtrl(x4501) { LUT(inits=List(0.19029346, 0.47020784, -0.7556679, 0.34903032, -0.049420655, 0.05752103, 0.73263806, -0.2690976, -1.846328E-5, -0.66465366), banking=Strided(banks=1, stride=5)).name("x4293_d0_b0").srcCtx("Indigo.scala:107:89:L2_W_LUT") } // x4293 = LUTNew(List(2, 5), Seq(Const(0.1902934610843658447265625),Const(0.4702078402042388916015625),Const(-0.7556679248809814453125)... [7 more]))
    isAccum(x4293_d0_b0) = false
    bufferDepthOf(x4293_d0_b0) = 1
    staticDimsOf(x4293_d0_b0) = List(2, 5)
    val x4294_d0_b0 = withCtrl(x4501) { LUT(inits=List(0.79848474, 0.8013729, 0.8108321, 0.79501843, 0.79428965), banking=Strided(banks=1, stride=1)).name("x4294_d0_b0").srcCtx("Indigo.scala:108:75:L2_B_LUT") } // x4294 = LUTNew(List(5), Seq(Const(0.798484742641448974609375),Const(0.80137288570404052734375),Const(0.810832083225250244140625)... [2 more]))
    isAccum(x4294_d0_b0) = false
    bufferDepthOf(x4294_d0_b0) = 1
    staticDimsOf(x4294_d0_b0) = List(5)
    val x4297 = withCtrl(x4501) { UnitController(style=SeqPipe, level=InnerControl).name("x4297").srcCtx("Indigo.scala:110:30") } // UnitPipe(List(Const(true)),Block(x4296))
    val x4295 = withCtrl(x4297) { ReadMem(x4274).name("x4295").srcCtx("Indigo.scala:111:42") } // RegRead(x4274)
    val x4296_x4277 = withCtrl(x4297) { WriteMem(x4277, x4295).name("x4296_x4277").srcCtx("Indigo.scala:111:39") } // RegWrite(x4277,x4295,Const(true))
    val x4338 = withCtrl(x4501) { UnitController(style=SeqPipe, level=OuterControl).name("x4338").srcCtx("Indigo.scala:116:30") } // UnitPipe(List(Const(true)),Block(Const(())))
    val x4298_d0_b0 = withCtrl(x4338) { RegFile(size=3, inits=None).name("x4298_d0_b0").srcCtx("Indigo.scala:117:64:input_step") } // x4298 = RegFileNew(ArrayBuffer(Const(3)),None) banking:Strided(banks=1, stride=1)
    isAccum(x4298_d0_b0) = false
    bufferDepthOf(x4298_d0_b0) = 1
    staticDimsOf(x4298_d0_b0) = List(3)
    val x4299 = withCtrl(x4338) { Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x4299").srcCtx("Indigo.scala:119:43") } // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x4300 = withCtrl(x4338) { CounterChain(List(x4299)).name("x4300").srcCtx("Indigo.scala:119:60") } // CounterChainNew(List(x4299))
    val x4303 = withCtrl(x4338) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4300).name("x4303").srcCtx("Indigo.scala:119:60") } // UnrolledForeach(List(Const(true)),x4300,Block(Const(())),List(List(b3140)),List(List(b3141)))
    val b3140 = withCtrl(x4303) { CounterIter(x4299, None).name("b3140") } // b3140
    val b3141 = withCtrl(x4303) { Const(true).name("b3141") } // b3141
    val x4301 = withCtrl(x4303) { LoadBanks(List(x4290_d0_b0), List(Const(0), b3140)).name("x4301").srcCtx("Indigo.scala:120:62") } // LUTLoad(x4290,List(Const(0), b3140),b3141)
    val x4302 = withCtrl(x4303) { StoreBanks(List(List(x4298_d0_b0)), List(b3140), x4301).name("x4302").srcCtx("Indigo.scala:120:55") } // ParRegFileStore(x4298,List(List(b3140)),List(x4301),List(Const(true)))
    val x4304 = withCtrl(x4338) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x4304").srcCtx("Indigo.scala:123:43") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x4305 = withCtrl(x4338) { CounterChain(List(x4304)).name("x4305").srcCtx("Indigo.scala:123:62") } // CounterChainNew(List(x4304))
    val x4310 = withCtrl(x4338) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4305).name("x4310").srcCtx("Indigo.scala:123:62") } // UnrolledForeach(List(Const(true)),x4305,Block(Const(())),List(List(b3147)),List(List(b3148)))
    val b3147 = withCtrl(x4310) { CounterIter(x4304, None).name("b3147") } // b3147
    val b3148 = withCtrl(x4310) { Const(true).name("b3148") } // b3148
    val x4306 = withCtrl(x4310) { OpDef(op=FixAdd, inputs=List(b3147, Const(1))).name("x4306").srcCtx("Indigo.scala:124:54") } // FixAdd(b3147,Const(1))
    val x4307 = withCtrl(x4310) { LoadBanks(List(x4278_d1_b0), List(b3147)).name("x4307").srcCtx("Indigo.scala:124:74") } // ParRegFileLoad(x4278,List(List(b3147)),List(Const(true)))
    val x4308 = withCtrl(x4310) { x4307 } // VectorApply(x4307,0)
    val x4309 = withCtrl(x4310) { StoreBanks(List(List(x4298_d0_b0)), List(x4306), x4308).name("x4309").srcCtx("Indigo.scala:124:68") } // ParRegFileStore(x4298,List(List(x4306)),List(x4308),List(Const(true)))
    val x4311 = withCtrl(x4338) { Counter(min=Const(0), max=Const(8), step=Const(1), par=1).name("x4311").srcCtx("Indigo.scala:127:43") } // CounterNew(Const(0),Const(8),Const(1),Const(1))
    val x4312 = withCtrl(x4338) { CounterChain(List(x4311)).name("x4312").srcCtx("Indigo.scala:127:81") } // CounterChainNew(List(x4311))
    val x4337 = withCtrl(x4338) { LoopController(style=MetaPipe, level=OuterControl, cchain=x4312).name("x4337").srcCtx("Indigo.scala:127:81") } // UnrolledForeach(List(Const(true)),x4312,Block(Const(())),List(List(b3156)),List(List(b3157)))
    val b3156 = withCtrl(x4337) { CounterIter(x4311, Some(0)).name("b3156") } // b3156
    val b3157 = withCtrl(x4337) { Const(true).name("b3157") } // b3157
    val x4313 = withCtrl(x4337) { Reg(init=Some(0.0)).name("x4313").srcCtx("Indigo.scala:129:52:w") } // x4313 = RegNew(Const(0.0))
    isAccum(x4313) = false
    bufferDepthOf(x4313) = 1
    val x4314_d0 = withCtrl(x4337) { Reg(init=Some(0.0)).name("x4314_d0").srcCtx("Indigo.scala:131:63") } // x4314 = RegNew(Const(0.0))
    isAccum(x4314_d0) = false
    bufferDepthOf(x4314_d0) = 2
    val x4314_d1 = withCtrl(x4337) { Reg(init=Some(0.0)).name("x4314_d1").srcCtx("Indigo.scala:131:63") } // x4314 = RegNew(Const(0.0))
    isAccum(x4314_d1) = true
    bufferDepthOf(x4314_d1) = 1
    val x4315 = withCtrl(x4337) { Counter(min=Const(0), max=Const(3), step=Const(1), par=1).name("x4315").srcCtx("Indigo.scala:131:107") } // CounterNew(Const(0),Const(3),Const(1),Const(1))
    val x4316 = withCtrl(x4337) { CounterChain(List(x4315)).name("x4316").srcCtx("Indigo.scala:133:42") } // CounterChainNew(List(x4315))
    val x4327 = withCtrl(x4337) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4316).name("x4327").srcCtx("Indigo.scala:133:42") } // UnrolledReduce(List(b3157),x4316,x4314,Block((x4314) => Const(())),List(List(b3162)),List(List(b3163)))
    val b3162 = withCtrl(x4327) { CounterIter(x4315, None).name("b3162") } // b3162
    val b3163 = withCtrl(x4327) { Const(true).name("b3163") } // b3163
    val x4317 = withCtrl(x4327) { OpDef(op=BitAnd, inputs=List(b3163, b3157)).name("x4317").srcCtx("UnrollingBase.scala:28:66") } // And(b3163,b3157)
    val x4318 = withCtrl(x4327) { LoadBanks(List(x4291_d0_b0), List(b3162, b3156)).name("x4318").srcCtx("Indigo.scala:132:57") } // LUTLoad(x4291,List(b3162, b3156),x4317)
    val x4319 = withCtrl(x4327) { x4318 } // FltConvert(x4318,_24,_8) //TODO
    val x4320 = withCtrl(x4327) { LoadBanks(List(x4298_d0_b0), List(b3162)).name("x4320").srcCtx("Indigo.scala:132:85") } // ParRegFileLoad(x4298,List(List(b3162)),List(Const(true)))
    val x4321 = withCtrl(x4327) { x4320 } // VectorApply(x4320,0)
    val x4322 = withCtrl(x4327) { OpDef(op=FltMul, inputs=List(x4319, x4321)).name("x4322").srcCtx("Indigo.scala:132:73") } // FltMul(x4319,x4321)
    val x4323 = withCtrl(x4327) { ReadMem(x4314_d1).name("x4323").srcCtx("Indigo.scala:133:42") } // RegRead(x4314)
    val x4324 = withCtrl(x4327) { OpDef(op=FixEql, inputs=List(b3162, Const(0))).name("x4324").srcCtx("Indigo.scala:133:42") } // FixEql(b3162,Const(0))
    val x4325 = withCtrl(x4327) { ReduceAccumOp(op=FltAdd, input=x4322, accum=x4323).name("x4325").srcCtx("Indigo.scala:133:45") } // FltAdd(x4322,x4323)
    val x4326_x4314_d0 = withCtrl(x4327) { WriteMem(x4314_d0, x4325).name("x4326_x4314_d0").srcCtx("Indigo.scala:133:42") } // RegWrite(x4314,x4325,b3157)
    antiDepsOf(x4326_x4314_d0)=List(x4323)
    val x4326_x4314_d1 = withCtrl(x4327) { WriteMem(x4314_d1, x4325).name("x4326_x4314_d1").srcCtx("Indigo.scala:133:42") } // RegWrite(x4314,x4325,b3157)
    antiDepsOf(x4326_x4314_d1)=List(x4323)
    val x4336 = withCtrl(x4337) { UnitController(style=SeqPipe, level=InnerControl).name("x4336").srcCtx("Indigo.scala:127:81") } // UnitPipe(List(b3157),Block(Const(())))
    val x4328 = withCtrl(x4336) { ReadMem(x4314_d0).name("x4328").srcCtx("Indigo.scala:133:42") } // RegRead(x4314)
    val x4329_x4313 = withCtrl(x4336) { WriteMem(x4313, x4328).name("x4329_x4313").srcCtx("Indigo.scala:131:43") } // RegWrite(x4313,x4328,b3157)
    val x4330 = withCtrl(x4336) { ReadMem(x4313).name("x4330").srcCtx("Indigo.scala:135:53") } // RegRead(x4313)
    antiDepsOf(x4330)=List(x4329_x4313)
    val x4331 = withCtrl(x4336) { LoadBanks(List(x4292_d0_b0), List(b3156)).name("x4331").srcCtx("Indigo.scala:135:65") } // LUTLoad(x4292,List(b3156),b3157)
    val x4332 = withCtrl(x4336) { OpDef(op=FltAdd, inputs=List(x4330, x4331)).name("x4332").srcCtx("Indigo.scala:135:55") } // FltAdd(x4330,x4331)
    val x4333 = withCtrl(x4336) { ReadMem(x4277).name("x4333").srcCtx("Indigo.scala:135:71") } // RegRead(x4277)
    val x4334 = withCtrl(x4336) { OpDef(op=FltAdd, inputs=List(x4332, x4333)).name("x4334").srcCtx("Indigo.scala:135:69") } // FltAdd(x4332,x4333)
    val x4335 = withCtrl(x4336) { StoreBanks(List(List(x4279_d0_b0), List(x4279_d1_b0), List(x4279_d2_b0), List(x4279_d3_b0)), List(b3156), x4334).name("x4335").srcCtx("Indigo.scala:135:51") } // RegFileStore(x4279,List(b3156),x4334,b3157)
    val x4458 = withCtrl(x4501) { UnitController(style=SeqPipe, level=OuterControl).name("x4458").srcCtx("Indigo.scala:142:30") } // UnitPipe(List(Const(true)),Block(Const(())))
    val x4339 = withCtrl(x4458) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x4339").srcCtx("Indigo.scala:144:43") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x4340 = withCtrl(x4458) { CounterChain(List(x4339)).name("x4340").srcCtx("Indigo.scala:144:63") } // CounterChainNew(List(x4339))
    val x4457 = withCtrl(x4458) { LoopController(style=MetaPipe, level=OuterControl, cchain=x4340).name("x4457").srcCtx("Indigo.scala:144:63") } // UnrolledForeach(List(Const(true)),x4340,Block(Const(())),List(List(b3188)),List(List(b3189)))
    val b3188 = withCtrl(x4457) { CounterIter(x4339, Some(0)).name("b3188") } // b3188
    val b3189 = withCtrl(x4457) { Const(true).name("b3189") } // b3189
    val x4456 = withCtrl(x4457) { UnitController(style=SeqPipe, level=InnerControl).name("x4456").srcCtx("Indigo.scala:144:63") } // UnitPipe(List(b3189),Block(Const(())))
    val x4341 = withCtrl(x4456) { LoadBanks(List(x4284_d1_b0), List(b3188)).name("x4341").srcCtx("Indigo.scala:145:55") } // RegFileLoad(x4284,List(b3188),b3189)
    val x4342 = withCtrl(x4456) { OpDef(op=FixAdd, inputs=List(b3188, Const(4))).name("x4342").srcCtx("Indigo.scala:145:84") } // FixAdd(b3188,Const(4))
    val x4343 = withCtrl(x4456) { LoadBanks(List(x4279_d3_b0), List(x4342)).name("x4343").srcCtx("Indigo.scala:145:81") } // RegFileLoad(x4279,List(x4342),b3189)
    val x4344 = withCtrl(x4456) { OpDef(op=FltAdd, inputs=List(x4343, Const(1.0))).name("x4344").srcCtx("Indigo.scala:145:104") } // FltAdd(x4343,Const(1))
    val x4345 = withCtrl(x4456) { OpDef(op=FltDiv, inputs=List(x4344, Const(2.0))).name("x4345").srcCtx("Indigo.scala:58:45") } // FltDiv(x4344,Const(2))
    val x4346 = withCtrl(x4456) { x4345 } // FltPtToFixPt(x4345,TRUE,_4,_8) //TODO
    val x4347 = withCtrl(x4456) { OpDef(op=FixAbs, inputs=List(x4346)).name("x4347").srcCtx("Indigo.scala:65:42:absp") } // FixAbs(x4346)
    val x4348 = withCtrl(x4456) { OpDef(op=FixLt, inputs=List(Const(2.5), x4347)).name("x4348").srcCtx("Indigo.scala:66:49") } // FixLt(Const(2.5),x4347)
    val x4349 = withCtrl(x4456) { OpDef(op=BitNot, inputs=List(x4348)).name("x4349").srcCtx("Indigo.scala:66:40") } // Not(x4348)
    val x4350 = withCtrl(x4456) { OpDef(op=FixLt, inputs=List(Const(0.5), x4347)).name("x4350").srcCtx("Indigo.scala:68:56") } // FixLt(Const(0.5),x4347)
    val x4351 = withCtrl(x4456) { OpDef(op=FixLeq, inputs=List(x4347, Const(2.5))).name("x4351").srcCtx("Indigo.scala:68:73") } // FixLeq(x4347,Const(2.5))
    val x4352 = withCtrl(x4456) { OpDef(op=BitAnd, inputs=List(x4350, x4351)).name("x4352").srcCtx("Indigo.scala:68:64") } // And(x4350,x4351)
    val x4353 = withCtrl(x4456) { OpDef(op=BitAnd, inputs=List(x4352, x4349)).name("x4353").srcCtx("Indigo.scala:66:40") } // And(x4352,x4349)
    val x4354 = withCtrl(x4456) { OpDef(op=BitNot, inputs=List(x4352)).name("x4354").srcCtx("Indigo.scala:66:40") } // Not(x4352)
    val x4355 = withCtrl(x4456) { OpDef(op=BitAnd, inputs=List(x4354, x4349)).name("x4355").srcCtx("Indigo.scala:66:40") } // And(x4354,x4349)
    val x4356 = withCtrl(x4456) { OpDef(op=FixSra, inputs=List(x4347, Const(2))).name("x4356").srcCtx("Indigo.scala:70:52:div4") } // FixRsh(x4347,Const(2))
    val x4357 = withCtrl(x4456) { OpDef(op=FixAdd, inputs=List(x4356, Const(0.375))).name("x4357").srcCtx("Indigo.scala:71:58:div4Offset") } // FixAdd(x4356,Const(0.375))
    val x4358 = withCtrl(x4456) { OpDef(op=MuxOp, inputs=List(x4353, x4357, x4347)).name("x4358").srcCtx("Indigo.scala:66:40") } // Mux(x4353,x4357,x4347)
    val x4359 = withCtrl(x4456) { OpDef(op=MuxOp, inputs=List(x4348, Const(1.0), x4358)).name("x4359").srcCtx("Indigo.scala:66:40") } // Mux(x4348,Const(1),x4358)
    val x4360 = withCtrl(x4456) { OpDef(op=FixNeg, inputs=List(x4359)).name("x4360").srcCtx("Indigo.scala:77:52:mabre") } // FixNeg(x4359)
    val x4361 = withCtrl(x4456) { OpDef(op=FixLt, inputs=List(x4346, Const(0.0))).name("x4361").srcCtx("Indigo.scala:78:45") } // FixLt(x4346,Const(0))
    val x4362 = withCtrl(x4456) { OpDef(op=MuxOp, inputs=List(x4361, x4360, x4359)).name("x4362").srcCtx("Indigo.scala:78:40:re") } // Mux(x4361,x4360,x4359)
    val x4363 = withCtrl(x4456) { x4362 } // FixPtToFltPt(x4362,_24,_8) //TODO
    val x4364 = withCtrl(x4456) { OpDef(op=FltAdd, inputs=List(x4363, Const(1.0))).name("x4364").srcCtx("Indigo.scala:58:60") } // FltAdd(x4363,Const(1))
    val x4365 = withCtrl(x4456) { OpDef(op=FltDiv, inputs=List(x4364, Const(2.0))).name("x4365").srcCtx("Indigo.scala:58:75") } // FltDiv(x4364,Const(2))
    val x4366 = withCtrl(x4456) { OpDef(op=FltMul, inputs=List(x4341, x4365)).name("x4366").srcCtx("Indigo.scala:145:59") } // FltMul(x4341,x4365)
    val x4367 = withCtrl(x4456) { LoadBanks(List(x4279_d2_b0), List(b3188)).name("x4367").srcCtx("Indigo.scala:145:134") } // RegFileLoad(x4279,List(b3188),b3189)
    val x4368 = withCtrl(x4456) { OpDef(op=FltDiv, inputs=List(x4367, Const(2.0))).name("x4368").srcCtx("Indigo.scala:58:45") } // FltDiv(x4367,Const(2))
    val x4369 = withCtrl(x4456) { x4368 } // FltPtToFixPt(x4368,TRUE,_4,_8) //TODO
    val x4370 = withCtrl(x4456) { OpDef(op=FixAbs, inputs=List(x4369)).name("x4370").srcCtx("Indigo.scala:65:42:absp") } // FixAbs(x4369)
    val x4371 = withCtrl(x4456) { OpDef(op=FixLt, inputs=List(Const(2.5), x4370)).name("x4371").srcCtx("Indigo.scala:66:49") } // FixLt(Const(2.5),x4370)
    val x4372 = withCtrl(x4456) { OpDef(op=BitNot, inputs=List(x4371)).name("x4372").srcCtx("Indigo.scala:66:40") } // Not(x4371)
    val x4373 = withCtrl(x4456) { OpDef(op=FixLt, inputs=List(Const(0.5), x4370)).name("x4373").srcCtx("Indigo.scala:68:56") } // FixLt(Const(0.5),x4370)
    val x4374 = withCtrl(x4456) { OpDef(op=FixLeq, inputs=List(x4370, Const(2.5))).name("x4374").srcCtx("Indigo.scala:68:73") } // FixLeq(x4370,Const(2.5))
    val x4375 = withCtrl(x4456) { OpDef(op=BitAnd, inputs=List(x4373, x4374)).name("x4375").srcCtx("Indigo.scala:68:64") } // And(x4373,x4374)
    val x4376 = withCtrl(x4456) { OpDef(op=BitAnd, inputs=List(x4375, x4372)).name("x4376").srcCtx("Indigo.scala:66:40") } // And(x4375,x4372)
    val x4377 = withCtrl(x4456) { OpDef(op=BitNot, inputs=List(x4375)).name("x4377").srcCtx("Indigo.scala:66:40") } // Not(x4375)
    val x4378 = withCtrl(x4456) { OpDef(op=BitAnd, inputs=List(x4377, x4372)).name("x4378").srcCtx("Indigo.scala:66:40") } // And(x4377,x4372)
    val x4379 = withCtrl(x4456) { OpDef(op=FixSra, inputs=List(x4370, Const(2))).name("x4379").srcCtx("Indigo.scala:70:52:div4") } // FixRsh(x4370,Const(2))
    val x4380 = withCtrl(x4456) { OpDef(op=FixAdd, inputs=List(x4379, Const(0.375))).name("x4380").srcCtx("Indigo.scala:71:58:div4Offset") } // FixAdd(x4379,Const(0.375))
    val x4381 = withCtrl(x4456) { OpDef(op=MuxOp, inputs=List(x4376, x4380, x4370)).name("x4381").srcCtx("Indigo.scala:66:40") } // Mux(x4376,x4380,x4370)
    val x4382 = withCtrl(x4456) { OpDef(op=MuxOp, inputs=List(x4371, Const(1.0), x4381)).name("x4382").srcCtx("Indigo.scala:66:40") } // Mux(x4371,Const(1),x4381)
    val x4383 = withCtrl(x4456) { OpDef(op=FixNeg, inputs=List(x4382)).name("x4383").srcCtx("Indigo.scala:77:52:mabre") } // FixNeg(x4382)
    val x4384 = withCtrl(x4456) { OpDef(op=FixLt, inputs=List(x4369, Const(0.0))).name("x4384").srcCtx("Indigo.scala:78:45") } // FixLt(x4369,Const(0))
    val x4385 = withCtrl(x4456) { OpDef(op=MuxOp, inputs=List(x4384, x4383, x4382)).name("x4385").srcCtx("Indigo.scala:78:40:re") } // Mux(x4384,x4383,x4382)
    val x4386 = withCtrl(x4456) { x4385 } // FixPtToFltPt(x4385,_24,_8) //TODO
    val x4387 = withCtrl(x4456) { OpDef(op=FltAdd, inputs=List(x4386, Const(1.0))).name("x4387").srcCtx("Indigo.scala:58:60") } // FltAdd(x4386,Const(1))
    val x4388 = withCtrl(x4456) { OpDef(op=FltDiv, inputs=List(x4387, Const(2.0))).name("x4388").srcCtx("Indigo.scala:58:75") } // FltDiv(x4387,Const(2))
    val x4389 = withCtrl(x4456) { OpDef(op=FixAdd, inputs=List(b3188, Const(2))).name("x4389").srcCtx("Indigo.scala:145:161") } // FixAdd(b3188,Const(2))
    val x4390 = withCtrl(x4456) { LoadBanks(List(x4279_d1_b0), List(x4389)).name("x4390").srcCtx("Indigo.scala:145:158") } // RegFileLoad(x4279,List(x4389),b3189)
    val x4391 = withCtrl(x4456) { x4390 } // FltPtToFixPt(x4390,TRUE,_4,_8) //TODO
    val x4392 = withCtrl(x4456) { OpDef(op=FixAbs, inputs=List(x4391)).name("x4392").srcCtx("Indigo.scala:65:42:absp") } // FixAbs(x4391)
    val x4393 = withCtrl(x4456) { OpDef(op=FixLt, inputs=List(Const(2.5), x4392)).name("x4393").srcCtx("Indigo.scala:66:49") } // FixLt(Const(2.5),x4392)
    val x4394 = withCtrl(x4456) { OpDef(op=BitNot, inputs=List(x4393)).name("x4394").srcCtx("Indigo.scala:66:40") } // Not(x4393)
    val x4395 = withCtrl(x4456) { OpDef(op=FixLt, inputs=List(Const(0.5), x4392)).name("x4395").srcCtx("Indigo.scala:68:56") } // FixLt(Const(0.5),x4392)
    val x4396 = withCtrl(x4456) { OpDef(op=FixLeq, inputs=List(x4392, Const(2.5))).name("x4396").srcCtx("Indigo.scala:68:73") } // FixLeq(x4392,Const(2.5))
    val x4397 = withCtrl(x4456) { OpDef(op=BitAnd, inputs=List(x4395, x4396)).name("x4397").srcCtx("Indigo.scala:68:64") } // And(x4395,x4396)
    val x4398 = withCtrl(x4456) { OpDef(op=BitAnd, inputs=List(x4397, x4394)).name("x4398").srcCtx("Indigo.scala:66:40") } // And(x4397,x4394)
    val x4399 = withCtrl(x4456) { OpDef(op=BitNot, inputs=List(x4397)).name("x4399").srcCtx("Indigo.scala:66:40") } // Not(x4397)
    val x4400 = withCtrl(x4456) { OpDef(op=BitAnd, inputs=List(x4399, x4394)).name("x4400").srcCtx("Indigo.scala:66:40") } // And(x4399,x4394)
    val x4401 = withCtrl(x4456) { OpDef(op=FixSra, inputs=List(x4392, Const(2))).name("x4401").srcCtx("Indigo.scala:70:52:div4") } // FixRsh(x4392,Const(2))
    val x4402 = withCtrl(x4456) { OpDef(op=FixAdd, inputs=List(x4401, Const(0.375))).name("x4402").srcCtx("Indigo.scala:71:58:div4Offset") } // FixAdd(x4401,Const(0.375))
    val x4403 = withCtrl(x4456) { OpDef(op=MuxOp, inputs=List(x4398, x4402, x4392)).name("x4403").srcCtx("Indigo.scala:66:40") } // Mux(x4398,x4402,x4392)
    val x4404 = withCtrl(x4456) { OpDef(op=MuxOp, inputs=List(x4393, Const(1.0), x4403)).name("x4404").srcCtx("Indigo.scala:66:40") } // Mux(x4393,Const(1),x4403)
    val x4405 = withCtrl(x4456) { OpDef(op=FixNeg, inputs=List(x4404)).name("x4405").srcCtx("Indigo.scala:77:52:mabre") } // FixNeg(x4404)
    val x4406 = withCtrl(x4456) { OpDef(op=FixLt, inputs=List(x4391, Const(0.0))).name("x4406").srcCtx("Indigo.scala:78:45") } // FixLt(x4391,Const(0))
    val x4407 = withCtrl(x4456) { OpDef(op=MuxOp, inputs=List(x4406, x4405, x4404)).name("x4407").srcCtx("Indigo.scala:78:40:re") } // Mux(x4406,x4405,x4404)
    val x4408 = withCtrl(x4456) { x4407 } // FixPtToFltPt(x4407,_24,_8) //TODO
    val x4409 = withCtrl(x4456) { OpDef(op=FltMul, inputs=List(x4388, x4408)).name("x4409").srcCtx("Indigo.scala:145:139") } // FltMul(x4388,x4408)
    val x4410 = withCtrl(x4456) { OpDef(op=FltAdd, inputs=List(x4366, x4409)).name("x4410").srcCtx("Indigo.scala:145:112") } // FltAdd(x4366,x4409)
    val x4411 = withCtrl(x4456) { StoreBanks(List(List(x4284_d0_b0), List(x4284_d1_b0)), List(b3188), x4410).name("x4411").srcCtx("Indigo.scala:145:49") } // RegFileStore(x4284,List(b3188),x4410,b3189)
    antiDepsOf(x4411)=List(x4341)
    val x4412 = withCtrl(x4456) { LoadBanks(List(x4284_d0_b0), List(b3188)).name("x4412").srcCtx("Indigo.scala:146:66") } // RegFileLoad(x4284,List(b3188),b3189)
    antiDepsOf(x4412)=List(x4411)
    val x4413 = withCtrl(x4456) { x4412 } // FltPtToFixPt(x4412,TRUE,_4,_8) //TODO
    val x4414 = withCtrl(x4456) { OpDef(op=FixAbs, inputs=List(x4413)).name("x4414").srcCtx("Indigo.scala:65:42:absp") } // FixAbs(x4413)
    val x4415 = withCtrl(x4456) { OpDef(op=FixLt, inputs=List(Const(2.5), x4414)).name("x4415").srcCtx("Indigo.scala:66:49") } // FixLt(Const(2.5),x4414)
    val x4416 = withCtrl(x4456) { OpDef(op=BitNot, inputs=List(x4415)).name("x4416").srcCtx("Indigo.scala:66:40") } // Not(x4415)
    val x4417 = withCtrl(x4456) { OpDef(op=FixLt, inputs=List(Const(0.5), x4414)).name("x4417").srcCtx("Indigo.scala:68:56") } // FixLt(Const(0.5),x4414)
    val x4418 = withCtrl(x4456) { OpDef(op=FixLeq, inputs=List(x4414, Const(2.5))).name("x4418").srcCtx("Indigo.scala:68:73") } // FixLeq(x4414,Const(2.5))
    val x4419 = withCtrl(x4456) { OpDef(op=BitAnd, inputs=List(x4417, x4418)).name("x4419").srcCtx("Indigo.scala:68:64") } // And(x4417,x4418)
    val x4420 = withCtrl(x4456) { OpDef(op=BitAnd, inputs=List(x4419, x4416)).name("x4420").srcCtx("Indigo.scala:66:40") } // And(x4419,x4416)
    val x4421 = withCtrl(x4456) { OpDef(op=BitNot, inputs=List(x4419)).name("x4421").srcCtx("Indigo.scala:66:40") } // Not(x4419)
    val x4422 = withCtrl(x4456) { OpDef(op=BitAnd, inputs=List(x4421, x4416)).name("x4422").srcCtx("Indigo.scala:66:40") } // And(x4421,x4416)
    val x4423 = withCtrl(x4456) { OpDef(op=FixSra, inputs=List(x4414, Const(2))).name("x4423").srcCtx("Indigo.scala:70:52:div4") } // FixRsh(x4414,Const(2))
    val x4424 = withCtrl(x4456) { OpDef(op=FixAdd, inputs=List(x4423, Const(0.375))).name("x4424").srcCtx("Indigo.scala:71:58:div4Offset") } // FixAdd(x4423,Const(0.375))
    val x4425 = withCtrl(x4456) { OpDef(op=MuxOp, inputs=List(x4420, x4424, x4414)).name("x4425").srcCtx("Indigo.scala:66:40") } // Mux(x4420,x4424,x4414)
    val x4426 = withCtrl(x4456) { OpDef(op=MuxOp, inputs=List(x4415, Const(1.0), x4425)).name("x4426").srcCtx("Indigo.scala:66:40") } // Mux(x4415,Const(1),x4425)
    val x4427 = withCtrl(x4456) { OpDef(op=FixNeg, inputs=List(x4426)).name("x4427").srcCtx("Indigo.scala:77:52:mabre") } // FixNeg(x4426)
    val x4428 = withCtrl(x4456) { OpDef(op=FixLt, inputs=List(x4413, Const(0.0))).name("x4428").srcCtx("Indigo.scala:78:45") } // FixLt(x4413,Const(0))
    val x4429 = withCtrl(x4456) { OpDef(op=MuxOp, inputs=List(x4428, x4427, x4426)).name("x4429").srcCtx("Indigo.scala:78:40:re") } // Mux(x4428,x4427,x4426)
    val x4430 = withCtrl(x4456) { x4429 } // FixPtToFltPt(x4429,_24,_8) //TODO
    val x4431 = withCtrl(x4456) { OpDef(op=FixAdd, inputs=List(b3188, Const(6))).name("x4431").srcCtx("Indigo.scala:146:96") } // FixAdd(b3188,Const(6))
    val x4432 = withCtrl(x4456) { LoadBanks(List(x4279_d0_b0), List(x4431)).name("x4432").srcCtx("Indigo.scala:146:93") } // RegFileLoad(x4279,List(x4431),b3189)
    val x4433 = withCtrl(x4456) { OpDef(op=FltDiv, inputs=List(x4432, Const(2.0))).name("x4433").srcCtx("Indigo.scala:58:45") } // FltDiv(x4432,Const(2))
    val x4434 = withCtrl(x4456) { x4433 } // FltPtToFixPt(x4433,TRUE,_4,_8) //TODO
    val x4435 = withCtrl(x4456) { OpDef(op=FixAbs, inputs=List(x4434)).name("x4435").srcCtx("Indigo.scala:65:42:absp") } // FixAbs(x4434)
    val x4436 = withCtrl(x4456) { OpDef(op=FixLt, inputs=List(Const(2.5), x4435)).name("x4436").srcCtx("Indigo.scala:66:49") } // FixLt(Const(2.5),x4435)
    val x4437 = withCtrl(x4456) { OpDef(op=BitNot, inputs=List(x4436)).name("x4437").srcCtx("Indigo.scala:66:40") } // Not(x4436)
    val x4438 = withCtrl(x4456) { OpDef(op=FixLt, inputs=List(Const(0.5), x4435)).name("x4438").srcCtx("Indigo.scala:68:56") } // FixLt(Const(0.5),x4435)
    val x4439 = withCtrl(x4456) { OpDef(op=FixLeq, inputs=List(x4435, Const(2.5))).name("x4439").srcCtx("Indigo.scala:68:73") } // FixLeq(x4435,Const(2.5))
    val x4440 = withCtrl(x4456) { OpDef(op=BitAnd, inputs=List(x4438, x4439)).name("x4440").srcCtx("Indigo.scala:68:64") } // And(x4438,x4439)
    val x4441 = withCtrl(x4456) { OpDef(op=BitAnd, inputs=List(x4440, x4437)).name("x4441").srcCtx("Indigo.scala:66:40") } // And(x4440,x4437)
    val x4442 = withCtrl(x4456) { OpDef(op=BitNot, inputs=List(x4440)).name("x4442").srcCtx("Indigo.scala:66:40") } // Not(x4440)
    val x4443 = withCtrl(x4456) { OpDef(op=BitAnd, inputs=List(x4442, x4437)).name("x4443").srcCtx("Indigo.scala:66:40") } // And(x4442,x4437)
    val x4444 = withCtrl(x4456) { OpDef(op=FixSra, inputs=List(x4435, Const(2))).name("x4444").srcCtx("Indigo.scala:70:52:div4") } // FixRsh(x4435,Const(2))
    val x4445 = withCtrl(x4456) { OpDef(op=FixAdd, inputs=List(x4444, Const(0.375))).name("x4445").srcCtx("Indigo.scala:71:58:div4Offset") } // FixAdd(x4444,Const(0.375))
    val x4446 = withCtrl(x4456) { OpDef(op=MuxOp, inputs=List(x4441, x4445, x4435)).name("x4446").srcCtx("Indigo.scala:66:40") } // Mux(x4441,x4445,x4435)
    val x4447 = withCtrl(x4456) { OpDef(op=MuxOp, inputs=List(x4436, Const(1.0), x4446)).name("x4447").srcCtx("Indigo.scala:66:40") } // Mux(x4436,Const(1),x4446)
    val x4448 = withCtrl(x4456) { OpDef(op=FixNeg, inputs=List(x4447)).name("x4448").srcCtx("Indigo.scala:77:52:mabre") } // FixNeg(x4447)
    val x4449 = withCtrl(x4456) { OpDef(op=FixLt, inputs=List(x4434, Const(0.0))).name("x4449").srcCtx("Indigo.scala:78:45") } // FixLt(x4434,Const(0))
    val x4450 = withCtrl(x4456) { OpDef(op=MuxOp, inputs=List(x4449, x4448, x4447)).name("x4450").srcCtx("Indigo.scala:78:40:re") } // Mux(x4449,x4448,x4447)
    val x4451 = withCtrl(x4456) { x4450 } // FixPtToFltPt(x4450,_24,_8) //TODO
    val x4452 = withCtrl(x4456) { OpDef(op=FltAdd, inputs=List(x4451, Const(1.0))).name("x4452").srcCtx("Indigo.scala:58:60") } // FltAdd(x4451,Const(1))
    val x4453 = withCtrl(x4456) { OpDef(op=FltDiv, inputs=List(x4452, Const(2.0))).name("x4453").srcCtx("Indigo.scala:58:75") } // FltDiv(x4452,Const(2))
    val x4454 = withCtrl(x4456) { OpDef(op=FltMul, inputs=List(x4430, x4453)).name("x4454").srcCtx("Indigo.scala:146:71") } // FltMul(x4430,x4453)
    val x4455 = withCtrl(x4456) { StoreBanks(List(List(x4278_d0_b0), List(x4278_d1_b0)), List(b3188), x4454).name("x4455").srcCtx("Indigo.scala:146:49") } // RegFileStore(x4278,List(b3188),x4454,b3189)
    val x4483 = withCtrl(x4501) { UnitController(style=SeqPipe, level=OuterControl).name("x4483").srcCtx("Indigo.scala:153:30") } // UnitPipe(List(Const(true)),Block(Const(())))
    val x4459 = withCtrl(x4483) { Counter(min=Const(0), max=Const(5), step=Const(1), par=1).name("x4459").srcCtx("Indigo.scala:154:43") } // CounterNew(Const(0),Const(5),Const(1),Const(1))
    val x4460 = withCtrl(x4483) { CounterChain(List(x4459)).name("x4460").srcCtx("Indigo.scala:154:64") } // CounterChainNew(List(x4459))
    val x4482 = withCtrl(x4483) { LoopController(style=MetaPipe, level=OuterControl, cchain=x4460).name("x4482").srcCtx("Indigo.scala:154:64") } // UnrolledForeach(List(Const(true)),x4460,Block(Const(())),List(List(b3310)),List(List(b3311)))
    val b3310 = withCtrl(x4482) { CounterIter(x4459, Some(0)).name("b3310") } // b3310
    val b3311 = withCtrl(x4482) { Const(true).name("b3311") } // b3311
    val x4461 = withCtrl(x4482) { Reg(init=Some(0.0)).name("x4461").srcCtx("Indigo.scala:156:52:w") } // x4461 = RegNew(Const(0.0))
    isAccum(x4461) = false
    bufferDepthOf(x4461) = 1
    val x4462_d0 = withCtrl(x4482) { Reg(init=Some(0.0)).name("x4462_d0").srcCtx("Indigo.scala:158:63") } // x4462 = RegNew(Const(0.0))
    isAccum(x4462_d0) = false
    bufferDepthOf(x4462_d0) = 2
    val x4462_d1 = withCtrl(x4482) { Reg(init=Some(0.0)).name("x4462_d1").srcCtx("Indigo.scala:158:63") } // x4462 = RegNew(Const(0.0))
    isAccum(x4462_d1) = true
    bufferDepthOf(x4462_d1) = 1
    val x4463 = withCtrl(x4482) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x4463").srcCtx("Indigo.scala:158:91") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x4464 = withCtrl(x4482) { CounterChain(List(x4463)).name("x4464").srcCtx("Indigo.scala:160:42") } // CounterChainNew(List(x4463))
    val x4474 = withCtrl(x4482) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4464).name("x4474").srcCtx("Indigo.scala:160:42") } // UnrolledReduce(List(b3311),x4464,x4462,Block((x4462) => Const(())),List(List(b3316)),List(List(b3317)))
    val b3316 = withCtrl(x4474) { CounterIter(x4463, None).name("b3316") } // b3316
    val b3317 = withCtrl(x4474) { Const(true).name("b3317") } // b3317
    val x4465 = withCtrl(x4474) { LoadBanks(List(x4278_d0_b0), List(b3316)).name("x4465").srcCtx("Indigo.scala:159:53") } // ParRegFileLoad(x4278,List(List(b3316)),List(Const(true)))
    val x4466 = withCtrl(x4474) { x4465 } // VectorApply(x4465,0)
    val x4467 = withCtrl(x4474) { OpDef(op=BitAnd, inputs=List(b3317, b3311)).name("x4467").srcCtx("UnrollingBase.scala:28:66") } // And(b3317,b3311)
    val x4468 = withCtrl(x4474) { LoadBanks(List(x4293_d0_b0), List(b3316, b3310)).name("x4468").srcCtx("Indigo.scala:159:67") } // LUTLoad(x4293,List(b3316, b3310),x4467)
    val x4469 = withCtrl(x4474) { OpDef(op=FltMul, inputs=List(x4466, x4468)).name("x4469").srcCtx("Indigo.scala:159:57") } // FltMul(x4466,x4468)
    val x4470 = withCtrl(x4474) { ReadMem(x4462_d1).name("x4470").srcCtx("Indigo.scala:160:42") } // RegRead(x4462)
    val x4471 = withCtrl(x4474) { OpDef(op=FixEql, inputs=List(b3316, Const(0))).name("x4471").srcCtx("Indigo.scala:160:42") } // FixEql(b3316,Const(0))
    val x4472 = withCtrl(x4474) { ReduceAccumOp(op=FltAdd, input=x4469, accum=x4470).name("x4472").srcCtx("Indigo.scala:160:44") } // FltAdd(x4469,x4470)
    val x4473_x4462_d0 = withCtrl(x4474) { WriteMem(x4462_d0, x4472).name("x4473_x4462_d0").srcCtx("Indigo.scala:160:42") } // RegWrite(x4462,x4472,b3311)
    antiDepsOf(x4473_x4462_d0)=List(x4470)
    val x4473_x4462_d1 = withCtrl(x4474) { WriteMem(x4462_d1, x4472).name("x4473_x4462_d1").srcCtx("Indigo.scala:160:42") } // RegWrite(x4462,x4472,b3311)
    antiDepsOf(x4473_x4462_d1)=List(x4470)
    val x4481 = withCtrl(x4482) { UnitController(style=SeqPipe, level=InnerControl).name("x4481").srcCtx("Indigo.scala:154:64") } // UnitPipe(List(b3311),Block(Const(())))
    val x4475 = withCtrl(x4481) { ReadMem(x4462_d0).name("x4475").srcCtx("Indigo.scala:160:42") } // RegRead(x4462)
    val x4476_x4461 = withCtrl(x4481) { WriteMem(x4461, x4475).name("x4476_x4461").srcCtx("Indigo.scala:158:43") } // RegWrite(x4461,x4475,b3311)
    val x4477 = withCtrl(x4481) { ReadMem(x4461).name("x4477").srcCtx("Indigo.scala:162:53") } // RegRead(x4461)
    antiDepsOf(x4477)=List(x4476_x4461)
    val x4478 = withCtrl(x4481) { LoadBanks(List(x4294_d0_b0), List(b3310)).name("x4478").srcCtx("Indigo.scala:162:65") } // LUTLoad(x4294,List(b3310),b3311)
    val x4479 = withCtrl(x4481) { OpDef(op=FltAdd, inputs=List(x4477, x4478)).name("x4479").srcCtx("Indigo.scala:162:55") } // FltAdd(x4477,x4478)
    val x4480 = withCtrl(x4481) { StoreBanks(List(List(x4289_d0_b0)), List(b3310), x4479).name("x4480").srcCtx("Indigo.scala:162:51") } // RegFileStore(x4289,List(b3310),x4479,b3311)
    val x4500 = withCtrl(x4501) { UnitController(style=SeqPipe, level=OuterControl).name("x4500").srcCtx("Indigo.scala:170:30") } // UnitPipe(List(Const(true)),Block(Const(())))
    val x4484 = withCtrl(x4500) { Reg(init=Some(0.0)).name("x4484").srcCtx("Indigo.scala:172:44:w") } // x4484 = RegNew(Const(0.0))
    isAccum(x4484) = false
    bufferDepthOf(x4484) = 1
    val x4485_d0 = withCtrl(x4500) { Reg(init=Some(0.0)).name("x4485_d0").srcCtx("Indigo.scala:174:55") } // x4485 = RegNew(Const(0.0))
    isAccum(x4485_d0) = false
    bufferDepthOf(x4485_d0) = 1
    val x4485_d1 = withCtrl(x4500) { Reg(init=Some(0.0)).name("x4485_d1").srcCtx("Indigo.scala:174:55") } // x4485 = RegNew(Const(0.0))
    isAccum(x4485_d1) = true
    bufferDepthOf(x4485_d1) = 1
    val x4486 = withCtrl(x4500) { Counter(min=Const(0), max=Const(5), step=Const(1), par=1).name("x4486").srcCtx("Indigo.scala:174:85") } // CounterNew(Const(0),Const(5),Const(1),Const(1))
    val x4487 = withCtrl(x4500) { CounterChain(List(x4486)).name("x4487").srcCtx("Indigo.scala:176:34") } // CounterChainNew(List(x4486))
    val x4494 = withCtrl(x4500) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4487).name("x4494").srcCtx("Indigo.scala:176:34") } // UnrolledReduce(List(Const(true)),x4487,x4485,Block((x4485) => Const(())),List(List(b3341)),List(List(b3342)))
    val b3341 = withCtrl(x4494) { CounterIter(x4486, None).name("b3341") } // b3341
    val b3342 = withCtrl(x4494) { Const(true).name("b3342") } // b3342
    val x4488 = withCtrl(x4494) { LoadBanks(List(x4289_d0_b0), List(b3341)).name("x4488").srcCtx("Indigo.scala:175:47") } // ParRegFileLoad(x4289,List(List(b3341)),List(Const(true)))
    val x4489 = withCtrl(x4494) { x4488 } // VectorApply(x4488,0)
    val x4490 = withCtrl(x4494) { ReadMem(x4485_d1).name("x4490").srcCtx("Indigo.scala:176:34") } // RegRead(x4485)
    val x4491 = withCtrl(x4494) { OpDef(op=FixEql, inputs=List(b3341, Const(0))).name("x4491").srcCtx("Indigo.scala:176:34") } // FixEql(b3341,Const(0))
    val x4492 = withCtrl(x4494) { ReduceAccumOp(op=FltAdd, input=x4489, accum=x4490).name("x4492").srcCtx("Indigo.scala:176:38") } // FltAdd(x4489,x4490)
    val x4493_x4485_d0 = withCtrl(x4494) { WriteMem(x4485_d0, x4492).name("x4493_x4485_d0").srcCtx("Indigo.scala:176:34") } // RegWrite(x4485,x4492,Const(true))
    antiDepsOf(x4493_x4485_d0)=List(x4490)
    val x4493_x4485_d1 = withCtrl(x4494) { WriteMem(x4485_d1, x4492).name("x4493_x4485_d1").srcCtx("Indigo.scala:176:34") } // RegWrite(x4485,x4492,Const(true))
    antiDepsOf(x4493_x4485_d1)=List(x4490)
    val x4499 = withCtrl(x4500) { UnitController(style=SeqPipe, level=InnerControl).name("x4499").srcCtx("Indigo.scala:170:30") } // UnitPipe(List(Const(true)),Block(Const(())))
    val x4495 = withCtrl(x4499) { ReadMem(x4485_d0).name("x4495").srcCtx("Indigo.scala:176:34") } // RegRead(x4485)
    val x4496_x4484 = withCtrl(x4499) { WriteMem(x4484, x4495).name("x4496_x4484").srcCtx("Indigo.scala:174:35") } // RegWrite(x4484,x4495,Const(true))
    val x4497 = withCtrl(x4499) { ReadMem(x4484).name("x4497").srcCtx("Indigo.scala:178:40") } // RegRead(x4484)
    antiDepsOf(x4497)=List(x4496_x4484)
    val x4498_x4275 = withCtrl(x4499) { WriteMem(x4275, x4497).name("x4498_x4275").srcCtx("Indigo.scala:178:37") } // RegWrite(x4275,x4497,Const(true))
    
  }
}
