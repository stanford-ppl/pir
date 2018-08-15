import pir._
import pir.node._
import arch._
import prism.enums._

object IndigoStream extends PIRApp {
  def main(implicit design:PIRDesign) = {
    import design.pirmeta._
    val x4266 = withCtrl(design.top.topController) { StreamIn(field="data").name("x4266").srcCtx("IndigoStream.scala:52:32:x") } // x4266 = StreamInNew(GPInput1)
    isAccum(x4266) = false
    bufferDepthOf(x4266) = 1
    countOf(x4266) = Some(1024l)
    val x4267 = withCtrl(design.top.topController) { StreamOut(field="data").name("x4267").srcCtx("IndigoStream.scala:53:50:stream_out") } // x4267 = StreamOutNew(GPOutput1)
    isAccum(x4267) = false
    bufferDepthOf(x4267) = 1
    // x4268 = Forever() TODO: Unmatched Node
    val x4494 = withCtrl(design.top.topController) { ForeverController().name("x4494").srcCtx("IndigoStream.scala:59:26") } // Hwblock(Block(Const(())),true)
    val x4269 = withCtrl(x4494) { Reg(init=Some(0.0)).name("x4269").srcCtx("IndigoStream.scala:90:47:s_reg") } // x4269 = RegNew(Const(0.0))
    isAccum(x4269) = false
    bufferDepthOf(x4269) = 2
    val x4270_d0_b0 = withCtrl(x4494) { RegFile(size=2, inits=None).name("x4270_d0_b0").srcCtx("IndigoStream.scala:91:50:L1_h") } // x4270 = RegFileNew(ArrayBuffer(Const(2)),None) banking:Strided(banks=1, stride=1)
    isAccum(x4270_d0_b0) = false
    bufferDepthOf(x4270_d0_b0) = 4
    staticDimsOf(x4270_d0_b0) = List(2)
    val x4271_d0_b0 = withCtrl(x4494) { RegFile(size=8, inits=None).name("x4271_d0_b0").srcCtx("IndigoStream.scala:92:52:L1_tmp") } // x4271 = RegFileNew(ArrayBuffer(Const(8)),None) banking:Strided(banks=1, stride=1)
    isAccum(x4271_d0_b0) = false
    bufferDepthOf(x4271_d0_b0) = 2
    staticDimsOf(x4271_d0_b0) = List(8)
    val x4271_d1_b0 = withCtrl(x4494) { RegFile(size=8, inits=None).name("x4271_d1_b0").srcCtx("IndigoStream.scala:92:52:L1_tmp") } // x4271 = RegFileNew(ArrayBuffer(Const(8)),None) banking:Strided(banks=1, stride=1)
    isAccum(x4271_d1_b0) = false
    bufferDepthOf(x4271_d1_b0) = 2
    staticDimsOf(x4271_d1_b0) = List(8)
    val x4271_d2_b0 = withCtrl(x4494) { RegFile(size=8, inits=None).name("x4271_d2_b0").srcCtx("IndigoStream.scala:92:52:L1_tmp") } // x4271 = RegFileNew(ArrayBuffer(Const(8)),None) banking:Strided(banks=1, stride=1)
    isAccum(x4271_d2_b0) = false
    bufferDepthOf(x4271_d2_b0) = 2
    staticDimsOf(x4271_d2_b0) = List(8)
    val x4271_d3_b0 = withCtrl(x4494) { RegFile(size=8, inits=None).name("x4271_d3_b0").srcCtx("IndigoStream.scala:92:52:L1_tmp") } // x4271 = RegFileNew(ArrayBuffer(Const(8)),None) banking:Strided(banks=1, stride=1)
    isAccum(x4271_d3_b0) = false
    bufferDepthOf(x4271_d3_b0) = 2
    staticDimsOf(x4271_d3_b0) = List(8)
    val x4272 = withCtrl(x4494) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x4272").srcCtx("IndigoStream.scala:96:35") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x4273 = withCtrl(x4494) { CounterChain(List(x4272)).name("x4273").srcCtx("IndigoStream.scala:96:54") } // CounterChainNew(List(x4272))
    val x4275 = withCtrl(x4494) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4273).name("x4275").srcCtx("IndigoStream.scala:96:54") } // UnrolledForeach(List(Const(true)),x4273,Block(Const(())),List(List(b3109)),List(List(b3110)))
    val b3109 = withCtrl(x4275) { CounterIter(x4272, None).name("b3109") } // b3109
    val b3110 = withCtrl(x4275) { Const(true).name("b3110") } // b3110
    val x4274 = withCtrl(x4275) { StoreBanks(List(List(x4270_d0_b0)), List(b3109), Const(0.0)).name("x4274").srcCtx("IndigoStream.scala:97:41") } // ParRegFileStore(x4270,List(List(b3109)),List(Const(0.0)),List(Const(true)))
    val x4276_d0_b0 = withCtrl(x4494) { RegFile(size=2, inits=None).name("x4276_d0_b0").srcCtx("IndigoStream.scala:100:50:L1_C") } // x4276 = RegFileNew(ArrayBuffer(Const(2)),None) banking:Strided(banks=1, stride=1)
    isAccum(x4276_d0_b0) = false
    bufferDepthOf(x4276_d0_b0) = 4
    staticDimsOf(x4276_d0_b0) = List(2)
    val x4277 = withCtrl(x4494) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x4277").srcCtx("IndigoStream.scala:102:35") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x4278 = withCtrl(x4494) { CounterChain(List(x4277)).name("x4278").srcCtx("IndigoStream.scala:102:54") } // CounterChainNew(List(x4277))
    val x4280 = withCtrl(x4494) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4278).name("x4280").srcCtx("IndigoStream.scala:102:54") } // UnrolledForeach(List(Const(true)),x4278,Block(Const(())),List(List(b3116)),List(List(b3117)))
    val b3116 = withCtrl(x4280) { CounterIter(x4277, None).name("b3116") } // b3116
    val b3117 = withCtrl(x4280) { Const(true).name("b3117") } // b3117
    val x4279 = withCtrl(x4280) { StoreBanks(List(List(x4276_d0_b0)), List(b3116), Const(0.0)).name("x4279").srcCtx("IndigoStream.scala:103:41") } // ParRegFileStore(x4276,List(List(b3116)),List(Const(0.0)),List(Const(true)))
    val x4281_d0_b0 = withCtrl(x4494) { RegFile(size=5, inits=None).name("x4281_d0_b0").srcCtx("IndigoStream.scala:106:52:L2_tmp") } // x4281 = RegFileNew(ArrayBuffer(Const(5)),None) banking:Strided(banks=1, stride=1)
    isAccum(x4281_d0_b0) = false
    bufferDepthOf(x4281_d0_b0) = 2
    staticDimsOf(x4281_d0_b0) = List(5)
    val x4282_d0_b0 = withCtrl(x4494) { LUT(inits=List(17.0), banking=Strided(banks=1, stride=1)).name("x4282_d0_b0").srcCtx("IndigoStream.scala:108:79:input") } // x4282 = LUTNew(List(1, 1), Seq(Const(17.0000)))
    isAccum(x4282_d0_b0) = false
    bufferDepthOf(x4282_d0_b0) = 1
    staticDimsOf(x4282_d0_b0) = List(1, 1)
    val x4283_d0_b0 = withCtrl(x4494) { LUT(inits=List(-0.0919745, -0.17325345, 0.07901578, -0.22862436, -0.33561853, 0.37217242, 0.28228363, 0.62458235, -0.710848, 0.6203918, -0.63714087, 0.33077937, 0.7237721, -0.70807374, -0.7135825, 0.3864259, 0.36924678, -0.63785505, 0.16256636, -0.26040027, -0.35571584, 0.15920722, -0.22067755, 0.45712513), banking=Strided(banks=1, stride=8)).name("x4283_d0_b0").srcCtx("IndigoStream.scala:110:119:L1_W_LUT") } // x4283 = LUTNew(List(3, 8), Seq(Const(-0.0919744968414306640625),Const(-0.1732534468173980712890625),Const(0.07901577651500701904296875)... [21 more]))
    isAccum(x4283_d0_b0) = false
    bufferDepthOf(x4283_d0_b0) = 1
    staticDimsOf(x4283_d0_b0) = List(3, 8)
    val x4284_d0_b0 = withCtrl(x4494) { LUT(inits=List(1.5417027E-5, 7.0991955E-4, 1.34923275E-5, -5.104995E-6, 0.0, 0.0, 3.5079447E-7, 1.2294923E-7), banking=Strided(banks=1, stride=1)).name("x4284_d0_b0").srcCtx("IndigoStream.scala:111:74:L1_B_LUT") } // x4284 = LUTNew(List(8), Seq(Const(0.00001541702658869326114654541015625),Const(0.0007099195499904453754425048828125),Const(0.00001349232752545503899455070495605469)... [5 more]))
    isAccum(x4284_d0_b0) = false
    bufferDepthOf(x4284_d0_b0) = 1
    staticDimsOf(x4284_d0_b0) = List(8)
    val x4285_d0_b0 = withCtrl(x4494) { LUT(inits=List(0.19029346, 0.47020784, -0.7556679, 0.34903032, -0.049420655, 0.05752103, 0.73263806, -0.2690976, -1.846328E-5, -0.66465366), banking=Strided(banks=1, stride=5)).name("x4285_d0_b0").srcCtx("IndigoStream.scala:114:89:L2_W_LUT") } // x4285 = LUTNew(List(2, 5), Seq(Const(0.1902934610843658447265625),Const(0.4702078402042388916015625),Const(-0.7556679248809814453125)... [7 more]))
    isAccum(x4285_d0_b0) = false
    bufferDepthOf(x4285_d0_b0) = 1
    staticDimsOf(x4285_d0_b0) = List(2, 5)
    val x4286_d0_b0 = withCtrl(x4494) { LUT(inits=List(0.79848474, 0.8013729, 0.8108321, 0.79501843, 0.79428965), banking=Strided(banks=1, stride=1)).name("x4286_d0_b0").srcCtx("IndigoStream.scala:115:75:L2_B_LUT") } // x4286 = LUTNew(List(5), Seq(Const(0.798484742641448974609375),Const(0.80137288570404052734375),Const(0.810832083225250244140625)... [2 more]))
    isAccum(x4286_d0_b0) = false
    bufferDepthOf(x4286_d0_b0) = 1
    staticDimsOf(x4286_d0_b0) = List(5)
    val x4289 = withCtrl(x4494) { UnitController(style=SeqPipe, level=InnerControl).name("x4289").srcCtx("IndigoStream.scala:117:30") } // UnitPipe(List(Const(true)),Block(x4288))
    val x4287_x4287 = withCtrl(x4289) { ReadMem(x4266).name("x4287_x4287").srcCtx("IndigoStream.scala:118:44") } // StreamRead(x4266,Const(true))
    val x4288_x4269 = withCtrl(x4289) { WriteMem(x4269, x4287_x4287).name("x4288_x4269").srcCtx("IndigoStream.scala:118:39") } // RegWrite(x4269,x4287,Const(true))
    val x4331 = withCtrl(x4494) { UnitController(style=SeqPipe, level=OuterControl).name("x4331").srcCtx("IndigoStream.scala:123:30") } // UnitPipe(List(Const(true)),Block(Const(())))
    val x4330 = withCtrl(x4331) { UnitController(style=SeqPipe, level=OuterControl).name("x4330").srcCtx("IndigoStream.scala:124:38") } // UnitPipe(List(Const(true)),Block(Const(())))
    val x4290_d0_b0 = withCtrl(x4330) { RegFile(size=3, inits=None).name("x4290_d0_b0").srcCtx("IndigoStream.scala:125:64:input_step") } // x4290 = RegFileNew(ArrayBuffer(Const(3)),None) banking:Strided(banks=1, stride=1)
    isAccum(x4290_d0_b0) = false
    bufferDepthOf(x4290_d0_b0) = 1
    staticDimsOf(x4290_d0_b0) = List(3)
    val x4291 = withCtrl(x4330) { Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x4291").srcCtx("IndigoStream.scala:127:43") } // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x4292 = withCtrl(x4330) { CounterChain(List(x4291)).name("x4292").srcCtx("IndigoStream.scala:127:60") } // CounterChainNew(List(x4291))
    val x4295 = withCtrl(x4330) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4292).name("x4295").srcCtx("IndigoStream.scala:127:60") } // UnrolledForeach(List(Const(true)),x4292,Block(Const(())),List(List(b3132)),List(List(b3133)))
    val b3132 = withCtrl(x4295) { CounterIter(x4291, None).name("b3132") } // b3132
    val b3133 = withCtrl(x4295) { Const(true).name("b3133") } // b3133
    val x4293 = withCtrl(x4295) { LoadBanks(List(x4282_d0_b0), List(Const(0), b3132)).name("x4293").srcCtx("IndigoStream.scala:128:62") } // LUTLoad(x4282,List(Const(0), b3132),b3133)
    val x4294 = withCtrl(x4295) { StoreBanks(List(List(x4290_d0_b0)), List(b3132), x4293).name("x4294").srcCtx("IndigoStream.scala:128:55") } // ParRegFileStore(x4290,List(List(b3132)),List(x4293),List(Const(true)))
    val x4296 = withCtrl(x4330) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x4296").srcCtx("IndigoStream.scala:131:43") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x4297 = withCtrl(x4330) { CounterChain(List(x4296)).name("x4297").srcCtx("IndigoStream.scala:131:62") } // CounterChainNew(List(x4296))
    val x4302 = withCtrl(x4330) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4297).name("x4302").srcCtx("IndigoStream.scala:131:62") } // UnrolledForeach(List(Const(true)),x4297,Block(Const(())),List(List(b3139)),List(List(b3140)))
    val b3139 = withCtrl(x4302) { CounterIter(x4296, None).name("b3139") } // b3139
    val b3140 = withCtrl(x4302) { Const(true).name("b3140") } // b3140
    val x4298 = withCtrl(x4302) { OpDef(op=FixAdd, inputs=List(b3139, Const(1))).name("x4298").srcCtx("IndigoStream.scala:132:54") } // FixAdd(b3139,Const(1))
    val x4299 = withCtrl(x4302) { LoadBanks(List(x4270_d0_b0), List(b3139)).name("x4299").srcCtx("IndigoStream.scala:132:74") } // ParRegFileLoad(x4270,List(List(b3139)),List(Const(true)))
    val x4300 = withCtrl(x4302) { x4299 } // VectorApply(x4299,0)
    val x4301 = withCtrl(x4302) { StoreBanks(List(List(x4290_d0_b0)), List(x4298), x4300).name("x4301").srcCtx("IndigoStream.scala:132:68") } // ParRegFileStore(x4290,List(List(x4298)),List(x4300),List(Const(true)))
    val x4303 = withCtrl(x4330) { Counter(min=Const(0), max=Const(8), step=Const(1), par=1).name("x4303").srcCtx("IndigoStream.scala:134:81") } // CounterNew(Const(0),Const(8),Const(1),Const(1))
    val x4304 = withCtrl(x4330) { CounterChain(List(x4303)).name("x4304").srcCtx("IndigoStream.scala:134:87") } // CounterChainNew(List(x4303))
    val x4329 = withCtrl(x4330) { LoopController(style=MetaPipe, level=OuterControl, cchain=x4304).name("x4329").srcCtx("IndigoStream.scala:134:87") } // UnrolledForeach(List(Const(true)),x4304,Block(Const(())),List(List(b3148)),List(List(b3149)))
    val b3148 = withCtrl(x4329) { CounterIter(x4303, Some(0)).name("b3148") } // b3148
    val b3149 = withCtrl(x4329) { Const(true).name("b3149") } // b3149
    val x4305 = withCtrl(x4329) { Reg(init=Some(0.0)).name("x4305").srcCtx("IndigoStream.scala:136:52:w") } // x4305 = RegNew(Const(0.0))
    isAccum(x4305) = false
    bufferDepthOf(x4305) = 1
    val x4306_d0 = withCtrl(x4329) { Reg(init=Some(0.0)).name("x4306_d0").srcCtx("IndigoStream.scala:138:63") } // x4306 = RegNew(Const(0.0))
    isAccum(x4306_d0) = false
    bufferDepthOf(x4306_d0) = 2
    val x4306_d1 = withCtrl(x4329) { Reg(init=Some(0.0)).name("x4306_d1").srcCtx("IndigoStream.scala:138:63") } // x4306 = RegNew(Const(0.0))
    isAccum(x4306_d1) = true
    bufferDepthOf(x4306_d1) = 1
    val x4307 = withCtrl(x4329) { Counter(min=Const(0), max=Const(3), step=Const(1), par=1).name("x4307").srcCtx("IndigoStream.scala:138:112") } // CounterNew(Const(0),Const(3),Const(1),Const(1))
    val x4308 = withCtrl(x4329) { CounterChain(List(x4307)).name("x4308").srcCtx("IndigoStream.scala:141:42") } // CounterChainNew(List(x4307))
    val x4319 = withCtrl(x4329) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4308).name("x4319").srcCtx("IndigoStream.scala:141:42") } // UnrolledReduce(List(b3149),x4308,x4306,Block((x4306) => Const(())),List(List(b3154)),List(List(b3155)))
    val b3154 = withCtrl(x4319) { CounterIter(x4307, None).name("b3154") } // b3154
    val b3155 = withCtrl(x4319) { Const(true).name("b3155") } // b3155
    val x4309 = withCtrl(x4319) { OpDef(op=BitAnd, inputs=List(b3155, b3149)).name("x4309").srcCtx("UnrollingBase.scala:28:66") } // And(b3155,b3149)
    val x4310 = withCtrl(x4319) { LoadBanks(List(x4283_d0_b0), List(b3154, b3148)).name("x4310").srcCtx("IndigoStream.scala:139:68") } // LUTLoad(x4283,List(b3154, b3148),x4309)
    val x4311 = withCtrl(x4319) { x4310 } // FltConvert(x4310,_24,_8) //TODO
    val x4312 = withCtrl(x4319) { LoadBanks(List(x4290_d0_b0), List(b3154)).name("x4312").srcCtx("IndigoStream.scala:140:66") } // ParRegFileLoad(x4290,List(List(b3154)),List(Const(true)))
    val x4313 = withCtrl(x4319) { x4312 } // VectorApply(x4312,0)
    val x4314 = withCtrl(x4319) { OpDef(op=FltMul, inputs=List(x4311, x4313)).name("x4314").srcCtx("IndigoStream.scala:140:54") } // FltMul(x4311,x4313)
    val x4315 = withCtrl(x4319) { ReadMem(x4306_d1).name("x4315").srcCtx("IndigoStream.scala:141:42") } // RegRead(x4306)
    val x4316 = withCtrl(x4319) { OpDef(op=FixEql, inputs=List(b3154, Const(0))).name("x4316").srcCtx("IndigoStream.scala:141:42") } // FixEql(b3154,Const(0))
    val x4317 = withCtrl(x4319) { ReduceAccumOp(op=FltAdd, input=x4314, accum=x4315).name("x4317").srcCtx("IndigoStream.scala:141:45") } // FltAdd(x4314,x4315)
    val x4318_x4306_d0 = withCtrl(x4319) { WriteMem(x4306_d0, x4317).name("x4318_x4306_d0").srcCtx("IndigoStream.scala:141:42") } // RegWrite(x4306,x4317,b3149)
    antiDepsOf(x4318_x4306_d0)=List(x4315)
    val x4318_x4306_d1 = withCtrl(x4319) { WriteMem(x4306_d1, x4317).name("x4318_x4306_d1").srcCtx("IndigoStream.scala:141:42") } // RegWrite(x4306,x4317,b3149)
    antiDepsOf(x4318_x4306_d1)=List(x4315)
    val x4328 = withCtrl(x4329) { UnitController(style=SeqPipe, level=InnerControl).name("x4328").srcCtx("IndigoStream.scala:134:87") } // UnitPipe(List(b3149),Block(Const(())))
    val x4320 = withCtrl(x4328) { ReadMem(x4306_d0).name("x4320").srcCtx("IndigoStream.scala:141:42") } // RegRead(x4306)
    val x4321_x4305 = withCtrl(x4328) { WriteMem(x4305, x4320).name("x4321_x4305").srcCtx("IndigoStream.scala:138:43") } // RegWrite(x4305,x4320,b3149)
    val x4322 = withCtrl(x4328) { ReadMem(x4305).name("x4322").srcCtx("IndigoStream.scala:143:53") } // RegRead(x4305)
    antiDepsOf(x4322)=List(x4321_x4305)
    val x4323 = withCtrl(x4328) { LoadBanks(List(x4284_d0_b0), List(b3148)).name("x4323").srcCtx("IndigoStream.scala:143:65") } // LUTLoad(x4284,List(b3148),b3149)
    val x4324 = withCtrl(x4328) { OpDef(op=FltAdd, inputs=List(x4322, x4323)).name("x4324").srcCtx("IndigoStream.scala:143:55") } // FltAdd(x4322,x4323)
    val x4325 = withCtrl(x4328) { ReadMem(x4269).name("x4325").srcCtx("IndigoStream.scala:143:71") } // RegRead(x4269)
    val x4326 = withCtrl(x4328) { OpDef(op=FltAdd, inputs=List(x4324, x4325)).name("x4326").srcCtx("IndigoStream.scala:143:69") } // FltAdd(x4324,x4325)
    val x4327 = withCtrl(x4328) { StoreBanks(List(List(x4271_d0_b0), List(x4271_d1_b0), List(x4271_d2_b0), List(x4271_d3_b0)), List(b3148), x4326).name("x4327").srcCtx("IndigoStream.scala:143:51") } // RegFileStore(x4271,List(b3148),x4326,b3149)
    val x4332_d0_b0 = withCtrl(x4494) { RegFile(size=2, inits=None).name("x4332_d0_b0").srcCtx("IndigoStream.scala:157:52:L1_h_p") } // x4332 = RegFileNew(ArrayBuffer(Const(2)),None) banking:Strided(banks=1, stride=1)
    isAccum(x4332_d0_b0) = false
    bufferDepthOf(x4332_d0_b0) = 2
    staticDimsOf(x4332_d0_b0) = List(2)
    val x4451 = withCtrl(x4494) { UnitController(style=SeqPipe, level=OuterControl).name("x4451").srcCtx("IndigoStream.scala:160:30") } // UnitPipe(List(Const(true)),Block(Const(())))
    val x4450 = withCtrl(x4451) { UnitController(style=SeqPipe, level=OuterControl).name("x4450").srcCtx("IndigoStream.scala:162:38") } // UnitPipe(List(Const(true)),Block(Const(())))
    val x4333 = withCtrl(x4450) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x4333").srcCtx("IndigoStream.scala:163:51") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x4334 = withCtrl(x4450) { CounterChain(List(x4333)).name("x4334").srcCtx("IndigoStream.scala:163:71") } // CounterChainNew(List(x4333))
    val x4449 = withCtrl(x4450) { LoopController(style=MetaPipe, level=OuterControl, cchain=x4334).name("x4449").srcCtx("IndigoStream.scala:163:71") } // UnrolledForeach(List(Const(true)),x4334,Block(Const(())),List(List(b3182)),List(List(b3183)))
    val b3182 = withCtrl(x4449) { CounterIter(x4333, Some(0)).name("b3182") } // b3182
    val b3183 = withCtrl(x4449) { Const(true).name("b3183") } // b3183
    val x4448 = withCtrl(x4449) { UnitController(style=SeqPipe, level=InnerControl).name("x4448").srcCtx("IndigoStream.scala:163:71") } // UnitPipe(List(b3183),Block(Const(())))
    val x4335 = withCtrl(x4448) { LoadBanks(List(x4276_d0_b0), List(b3182)).name("x4335").srcCtx("IndigoStream.scala:164:62") } // RegFileLoad(x4276,List(b3182),b3183)
    val x4336 = withCtrl(x4448) { OpDef(op=FixAdd, inputs=List(b3182, Const(4))).name("x4336").srcCtx("IndigoStream.scala:164:91") } // FixAdd(b3182,Const(4))
    val x4337 = withCtrl(x4448) { LoadBanks(List(x4271_d3_b0), List(x4336)).name("x4337").srcCtx("IndigoStream.scala:164:88") } // RegFileLoad(x4271,List(x4336),b3183)
    val x4338 = withCtrl(x4448) { OpDef(op=FltAdd, inputs=List(x4337, Const(1.0))).name("x4338").srcCtx("IndigoStream.scala:164:111") } // FltAdd(x4337,Const(1))
    val x4339 = withCtrl(x4448) { OpDef(op=FltDiv, inputs=List(x4338, Const(2.0))).name("x4339").srcCtx("IndigoStream.scala:64:45") } // FltDiv(x4338,Const(2))
    val x4340 = withCtrl(x4448) { x4339 } // FltPtToFixPt(x4339,TRUE,_16,_16) //TODO
    val x4341 = withCtrl(x4448) { OpDef(op=FixAbs, inputs=List(x4340)).name("x4341").srcCtx("IndigoStream.scala:71:42:absp") } // FixAbs(x4340)
    val x4342 = withCtrl(x4448) { OpDef(op=FixLt, inputs=List(Const(2.5), x4341)).name("x4342").srcCtx("IndigoStream.scala:72:49") } // FixLt(Const(2.5),x4341)
    val x4343 = withCtrl(x4448) { OpDef(op=BitNot, inputs=List(x4342)).name("x4343").srcCtx("IndigoStream.scala:72:40") } // Not(x4342)
    val x4344 = withCtrl(x4448) { OpDef(op=FixLt, inputs=List(Const(0.5), x4341)).name("x4344").srcCtx("IndigoStream.scala:74:56") } // FixLt(Const(0.5),x4341)
    val x4345 = withCtrl(x4448) { OpDef(op=FixLeq, inputs=List(x4341, Const(2.5))).name("x4345").srcCtx("IndigoStream.scala:74:73") } // FixLeq(x4341,Const(2.5))
    val x4346 = withCtrl(x4448) { OpDef(op=BitAnd, inputs=List(x4344, x4345)).name("x4346").srcCtx("IndigoStream.scala:74:64") } // And(x4344,x4345)
    val x4347 = withCtrl(x4448) { OpDef(op=BitAnd, inputs=List(x4346, x4343)).name("x4347").srcCtx("IndigoStream.scala:72:40") } // And(x4346,x4343)
    val x4348 = withCtrl(x4448) { OpDef(op=BitNot, inputs=List(x4346)).name("x4348").srcCtx("IndigoStream.scala:72:40") } // Not(x4346)
    val x4349 = withCtrl(x4448) { OpDef(op=BitAnd, inputs=List(x4348, x4343)).name("x4349").srcCtx("IndigoStream.scala:72:40") } // And(x4348,x4343)
    val x4350 = withCtrl(x4448) { OpDef(op=FixSra, inputs=List(x4341, Const(2))).name("x4350").srcCtx("IndigoStream.scala:76:52:div4") } // FixRsh(x4341,Const(2))
    val x4351 = withCtrl(x4448) { OpDef(op=FixAdd, inputs=List(x4350, Const(0.375))).name("x4351").srcCtx("IndigoStream.scala:77:58:div4Offset") } // FixAdd(x4350,Const(0.375))
    val x4352 = withCtrl(x4448) { OpDef(op=MuxOp, inputs=List(x4347, x4351, x4341)).name("x4352").srcCtx("IndigoStream.scala:72:40") } // Mux(x4347,x4351,x4341)
    val x4353 = withCtrl(x4448) { OpDef(op=MuxOp, inputs=List(x4342, Const(1.0), x4352)).name("x4353").srcCtx("IndigoStream.scala:72:40") } // Mux(x4342,Const(1),x4352)
    val x4354 = withCtrl(x4448) { OpDef(op=FixNeg, inputs=List(x4353)).name("x4354").srcCtx("IndigoStream.scala:83:52:mabre") } // FixNeg(x4353)
    val x4355 = withCtrl(x4448) { OpDef(op=FixLt, inputs=List(x4340, Const(0.0))).name("x4355").srcCtx("IndigoStream.scala:84:45") } // FixLt(x4340,Const(0))
    val x4356 = withCtrl(x4448) { OpDef(op=MuxOp, inputs=List(x4355, x4354, x4353)).name("x4356").srcCtx("IndigoStream.scala:84:40:re") } // Mux(x4355,x4354,x4353)
    val x4357 = withCtrl(x4448) { x4356 } // FixPtToFltPt(x4356,_24,_8) //TODO
    val x4358 = withCtrl(x4448) { OpDef(op=FltAdd, inputs=List(x4357, Const(1.0))).name("x4358").srcCtx("IndigoStream.scala:64:60") } // FltAdd(x4357,Const(1))
    val x4359 = withCtrl(x4448) { OpDef(op=FltDiv, inputs=List(x4358, Const(2.0))).name("x4359").srcCtx("IndigoStream.scala:64:75") } // FltDiv(x4358,Const(2))
    val x4360 = withCtrl(x4448) { OpDef(op=FltMul, inputs=List(x4335, x4359)).name("x4360").srcCtx("IndigoStream.scala:164:66") } // FltMul(x4335,x4359)
    val x4361 = withCtrl(x4448) { LoadBanks(List(x4271_d2_b0), List(b3182)).name("x4361").srcCtx("IndigoStream.scala:164:141") } // RegFileLoad(x4271,List(b3182),b3183)
    val x4362 = withCtrl(x4448) { OpDef(op=FltDiv, inputs=List(x4361, Const(2.0))).name("x4362").srcCtx("IndigoStream.scala:64:45") } // FltDiv(x4361,Const(2))
    val x4363 = withCtrl(x4448) { x4362 } // FltPtToFixPt(x4362,TRUE,_16,_16) //TODO
    val x4364 = withCtrl(x4448) { OpDef(op=FixAbs, inputs=List(x4363)).name("x4364").srcCtx("IndigoStream.scala:71:42:absp") } // FixAbs(x4363)
    val x4365 = withCtrl(x4448) { OpDef(op=FixLt, inputs=List(Const(2.5), x4364)).name("x4365").srcCtx("IndigoStream.scala:72:49") } // FixLt(Const(2.5),x4364)
    val x4366 = withCtrl(x4448) { OpDef(op=BitNot, inputs=List(x4365)).name("x4366").srcCtx("IndigoStream.scala:72:40") } // Not(x4365)
    val x4367 = withCtrl(x4448) { OpDef(op=FixLt, inputs=List(Const(0.5), x4364)).name("x4367").srcCtx("IndigoStream.scala:74:56") } // FixLt(Const(0.5),x4364)
    val x4368 = withCtrl(x4448) { OpDef(op=FixLeq, inputs=List(x4364, Const(2.5))).name("x4368").srcCtx("IndigoStream.scala:74:73") } // FixLeq(x4364,Const(2.5))
    val x4369 = withCtrl(x4448) { OpDef(op=BitAnd, inputs=List(x4367, x4368)).name("x4369").srcCtx("IndigoStream.scala:74:64") } // And(x4367,x4368)
    val x4370 = withCtrl(x4448) { OpDef(op=BitAnd, inputs=List(x4369, x4366)).name("x4370").srcCtx("IndigoStream.scala:72:40") } // And(x4369,x4366)
    val x4371 = withCtrl(x4448) { OpDef(op=BitNot, inputs=List(x4369)).name("x4371").srcCtx("IndigoStream.scala:72:40") } // Not(x4369)
    val x4372 = withCtrl(x4448) { OpDef(op=BitAnd, inputs=List(x4371, x4366)).name("x4372").srcCtx("IndigoStream.scala:72:40") } // And(x4371,x4366)
    val x4373 = withCtrl(x4448) { OpDef(op=FixSra, inputs=List(x4364, Const(2))).name("x4373").srcCtx("IndigoStream.scala:76:52:div4") } // FixRsh(x4364,Const(2))
    val x4374 = withCtrl(x4448) { OpDef(op=FixAdd, inputs=List(x4373, Const(0.375))).name("x4374").srcCtx("IndigoStream.scala:77:58:div4Offset") } // FixAdd(x4373,Const(0.375))
    val x4375 = withCtrl(x4448) { OpDef(op=MuxOp, inputs=List(x4370, x4374, x4364)).name("x4375").srcCtx("IndigoStream.scala:72:40") } // Mux(x4370,x4374,x4364)
    val x4376 = withCtrl(x4448) { OpDef(op=MuxOp, inputs=List(x4365, Const(1.0), x4375)).name("x4376").srcCtx("IndigoStream.scala:72:40") } // Mux(x4365,Const(1),x4375)
    val x4377 = withCtrl(x4448) { OpDef(op=FixNeg, inputs=List(x4376)).name("x4377").srcCtx("IndigoStream.scala:83:52:mabre") } // FixNeg(x4376)
    val x4378 = withCtrl(x4448) { OpDef(op=FixLt, inputs=List(x4363, Const(0.0))).name("x4378").srcCtx("IndigoStream.scala:84:45") } // FixLt(x4363,Const(0))
    val x4379 = withCtrl(x4448) { OpDef(op=MuxOp, inputs=List(x4378, x4377, x4376)).name("x4379").srcCtx("IndigoStream.scala:84:40:re") } // Mux(x4378,x4377,x4376)
    val x4380 = withCtrl(x4448) { x4379 } // FixPtToFltPt(x4379,_24,_8) //TODO
    val x4381 = withCtrl(x4448) { OpDef(op=FltAdd, inputs=List(x4380, Const(1.0))).name("x4381").srcCtx("IndigoStream.scala:64:60") } // FltAdd(x4380,Const(1))
    val x4382 = withCtrl(x4448) { OpDef(op=FltDiv, inputs=List(x4381, Const(2.0))).name("x4382").srcCtx("IndigoStream.scala:64:75") } // FltDiv(x4381,Const(2))
    val x4383 = withCtrl(x4448) { OpDef(op=FixAdd, inputs=List(b3182, Const(2))).name("x4383").srcCtx("IndigoStream.scala:164:168") } // FixAdd(b3182,Const(2))
    val x4384 = withCtrl(x4448) { LoadBanks(List(x4271_d1_b0), List(x4383)).name("x4384").srcCtx("IndigoStream.scala:164:165") } // RegFileLoad(x4271,List(x4383),b3183)
    val x4385 = withCtrl(x4448) { x4384 } // FltPtToFixPt(x4384,TRUE,_16,_16) //TODO
    val x4386 = withCtrl(x4448) { OpDef(op=FixAbs, inputs=List(x4385)).name("x4386").srcCtx("IndigoStream.scala:71:42:absp") } // FixAbs(x4385)
    val x4387 = withCtrl(x4448) { OpDef(op=FixLt, inputs=List(Const(2.5), x4386)).name("x4387").srcCtx("IndigoStream.scala:72:49") } // FixLt(Const(2.5),x4386)
    val x4388 = withCtrl(x4448) { OpDef(op=BitNot, inputs=List(x4387)).name("x4388").srcCtx("IndigoStream.scala:72:40") } // Not(x4387)
    val x4389 = withCtrl(x4448) { OpDef(op=FixLt, inputs=List(Const(0.5), x4386)).name("x4389").srcCtx("IndigoStream.scala:74:56") } // FixLt(Const(0.5),x4386)
    val x4390 = withCtrl(x4448) { OpDef(op=FixLeq, inputs=List(x4386, Const(2.5))).name("x4390").srcCtx("IndigoStream.scala:74:73") } // FixLeq(x4386,Const(2.5))
    val x4391 = withCtrl(x4448) { OpDef(op=BitAnd, inputs=List(x4389, x4390)).name("x4391").srcCtx("IndigoStream.scala:74:64") } // And(x4389,x4390)
    val x4392 = withCtrl(x4448) { OpDef(op=BitAnd, inputs=List(x4391, x4388)).name("x4392").srcCtx("IndigoStream.scala:72:40") } // And(x4391,x4388)
    val x4393 = withCtrl(x4448) { OpDef(op=BitNot, inputs=List(x4391)).name("x4393").srcCtx("IndigoStream.scala:72:40") } // Not(x4391)
    val x4394 = withCtrl(x4448) { OpDef(op=BitAnd, inputs=List(x4393, x4388)).name("x4394").srcCtx("IndigoStream.scala:72:40") } // And(x4393,x4388)
    val x4395 = withCtrl(x4448) { OpDef(op=FixSra, inputs=List(x4386, Const(2))).name("x4395").srcCtx("IndigoStream.scala:76:52:div4") } // FixRsh(x4386,Const(2))
    val x4396 = withCtrl(x4448) { OpDef(op=FixAdd, inputs=List(x4395, Const(0.375))).name("x4396").srcCtx("IndigoStream.scala:77:58:div4Offset") } // FixAdd(x4395,Const(0.375))
    val x4397 = withCtrl(x4448) { OpDef(op=MuxOp, inputs=List(x4392, x4396, x4386)).name("x4397").srcCtx("IndigoStream.scala:72:40") } // Mux(x4392,x4396,x4386)
    val x4398 = withCtrl(x4448) { OpDef(op=MuxOp, inputs=List(x4387, Const(1.0), x4397)).name("x4398").srcCtx("IndigoStream.scala:72:40") } // Mux(x4387,Const(1),x4397)
    val x4399 = withCtrl(x4448) { OpDef(op=FixNeg, inputs=List(x4398)).name("x4399").srcCtx("IndigoStream.scala:83:52:mabre") } // FixNeg(x4398)
    val x4400 = withCtrl(x4448) { OpDef(op=FixLt, inputs=List(x4385, Const(0.0))).name("x4400").srcCtx("IndigoStream.scala:84:45") } // FixLt(x4385,Const(0))
    val x4401 = withCtrl(x4448) { OpDef(op=MuxOp, inputs=List(x4400, x4399, x4398)).name("x4401").srcCtx("IndigoStream.scala:84:40:re") } // Mux(x4400,x4399,x4398)
    val x4402 = withCtrl(x4448) { x4401 } // FixPtToFltPt(x4401,_24,_8) //TODO
    val x4403 = withCtrl(x4448) { OpDef(op=FltMul, inputs=List(x4382, x4402)).name("x4403").srcCtx("IndigoStream.scala:164:146") } // FltMul(x4382,x4402)
    val x4404 = withCtrl(x4448) { OpDef(op=FltAdd, inputs=List(x4360, x4403)).name("x4404").srcCtx("IndigoStream.scala:164:119:l1") } // FltAdd(x4360,x4403)
    val x4405 = withCtrl(x4448) { x4404 } // FltPtToFixPt(x4404,TRUE,_16,_16) //TODO
    val x4406 = withCtrl(x4448) { OpDef(op=FixAbs, inputs=List(x4405)).name("x4406").srcCtx("IndigoStream.scala:71:42:absp") } // FixAbs(x4405)
    val x4407 = withCtrl(x4448) { OpDef(op=FixLt, inputs=List(Const(2.5), x4406)).name("x4407").srcCtx("IndigoStream.scala:72:49") } // FixLt(Const(2.5),x4406)
    val x4408 = withCtrl(x4448) { OpDef(op=BitNot, inputs=List(x4407)).name("x4408").srcCtx("IndigoStream.scala:72:40") } // Not(x4407)
    val x4409 = withCtrl(x4448) { OpDef(op=FixLt, inputs=List(Const(0.5), x4406)).name("x4409").srcCtx("IndigoStream.scala:74:56") } // FixLt(Const(0.5),x4406)
    val x4410 = withCtrl(x4448) { OpDef(op=FixLeq, inputs=List(x4406, Const(2.5))).name("x4410").srcCtx("IndigoStream.scala:74:73") } // FixLeq(x4406,Const(2.5))
    val x4411 = withCtrl(x4448) { OpDef(op=BitAnd, inputs=List(x4409, x4410)).name("x4411").srcCtx("IndigoStream.scala:74:64") } // And(x4409,x4410)
    val x4412 = withCtrl(x4448) { OpDef(op=BitAnd, inputs=List(x4411, x4408)).name("x4412").srcCtx("IndigoStream.scala:72:40") } // And(x4411,x4408)
    val x4413 = withCtrl(x4448) { OpDef(op=BitNot, inputs=List(x4411)).name("x4413").srcCtx("IndigoStream.scala:72:40") } // Not(x4411)
    val x4414 = withCtrl(x4448) { OpDef(op=BitAnd, inputs=List(x4413, x4408)).name("x4414").srcCtx("IndigoStream.scala:72:40") } // And(x4413,x4408)
    val x4415 = withCtrl(x4448) { OpDef(op=FixSra, inputs=List(x4406, Const(2))).name("x4415").srcCtx("IndigoStream.scala:76:52:div4") } // FixRsh(x4406,Const(2))
    val x4416 = withCtrl(x4448) { OpDef(op=FixAdd, inputs=List(x4415, Const(0.375))).name("x4416").srcCtx("IndigoStream.scala:77:58:div4Offset") } // FixAdd(x4415,Const(0.375))
    val x4417 = withCtrl(x4448) { OpDef(op=MuxOp, inputs=List(x4412, x4416, x4406)).name("x4417").srcCtx("IndigoStream.scala:72:40") } // Mux(x4412,x4416,x4406)
    val x4418 = withCtrl(x4448) { OpDef(op=MuxOp, inputs=List(x4407, Const(1.0), x4417)).name("x4418").srcCtx("IndigoStream.scala:72:40") } // Mux(x4407,Const(1),x4417)
    val x4419 = withCtrl(x4448) { OpDef(op=FixNeg, inputs=List(x4418)).name("x4419").srcCtx("IndigoStream.scala:83:52:mabre") } // FixNeg(x4418)
    val x4420 = withCtrl(x4448) { OpDef(op=FixLt, inputs=List(x4405, Const(0.0))).name("x4420").srcCtx("IndigoStream.scala:84:45") } // FixLt(x4405,Const(0))
    val x4421 = withCtrl(x4448) { OpDef(op=MuxOp, inputs=List(x4420, x4419, x4418)).name("x4421").srcCtx("IndigoStream.scala:84:40:re") } // Mux(x4420,x4419,x4418)
    val x4422 = withCtrl(x4448) { x4421 } // FixPtToFltPt(x4421,_24,_8) //TODO
    val x4423 = withCtrl(x4448) { OpDef(op=FixAdd, inputs=List(b3182, Const(6))).name("x4423").srcCtx("IndigoStream.scala:165:101") } // FixAdd(b3182,Const(6))
    val x4424 = withCtrl(x4448) { LoadBanks(List(x4271_d0_b0), List(x4423)).name("x4424").srcCtx("IndigoStream.scala:165:98") } // RegFileLoad(x4271,List(x4423),b3183)
    val x4425 = withCtrl(x4448) { OpDef(op=FltDiv, inputs=List(x4424, Const(2.0))).name("x4425").srcCtx("IndigoStream.scala:64:45") } // FltDiv(x4424,Const(2))
    val x4426 = withCtrl(x4448) { x4425 } // FltPtToFixPt(x4425,TRUE,_16,_16) //TODO
    val x4427 = withCtrl(x4448) { OpDef(op=FixAbs, inputs=List(x4426)).name("x4427").srcCtx("IndigoStream.scala:71:42:absp") } // FixAbs(x4426)
    val x4428 = withCtrl(x4448) { OpDef(op=FixLt, inputs=List(Const(2.5), x4427)).name("x4428").srcCtx("IndigoStream.scala:72:49") } // FixLt(Const(2.5),x4427)
    val x4429 = withCtrl(x4448) { OpDef(op=BitNot, inputs=List(x4428)).name("x4429").srcCtx("IndigoStream.scala:72:40") } // Not(x4428)
    val x4430 = withCtrl(x4448) { OpDef(op=FixLt, inputs=List(Const(0.5), x4427)).name("x4430").srcCtx("IndigoStream.scala:74:56") } // FixLt(Const(0.5),x4427)
    val x4431 = withCtrl(x4448) { OpDef(op=FixLeq, inputs=List(x4427, Const(2.5))).name("x4431").srcCtx("IndigoStream.scala:74:73") } // FixLeq(x4427,Const(2.5))
    val x4432 = withCtrl(x4448) { OpDef(op=BitAnd, inputs=List(x4430, x4431)).name("x4432").srcCtx("IndigoStream.scala:74:64") } // And(x4430,x4431)
    val x4433 = withCtrl(x4448) { OpDef(op=BitAnd, inputs=List(x4432, x4429)).name("x4433").srcCtx("IndigoStream.scala:72:40") } // And(x4432,x4429)
    val x4434 = withCtrl(x4448) { OpDef(op=BitNot, inputs=List(x4432)).name("x4434").srcCtx("IndigoStream.scala:72:40") } // Not(x4432)
    val x4435 = withCtrl(x4448) { OpDef(op=BitAnd, inputs=List(x4434, x4429)).name("x4435").srcCtx("IndigoStream.scala:72:40") } // And(x4434,x4429)
    val x4436 = withCtrl(x4448) { OpDef(op=FixSra, inputs=List(x4427, Const(2))).name("x4436").srcCtx("IndigoStream.scala:76:52:div4") } // FixRsh(x4427,Const(2))
    val x4437 = withCtrl(x4448) { OpDef(op=FixAdd, inputs=List(x4436, Const(0.375))).name("x4437").srcCtx("IndigoStream.scala:77:58:div4Offset") } // FixAdd(x4436,Const(0.375))
    val x4438 = withCtrl(x4448) { OpDef(op=MuxOp, inputs=List(x4433, x4437, x4427)).name("x4438").srcCtx("IndigoStream.scala:72:40") } // Mux(x4433,x4437,x4427)
    val x4439 = withCtrl(x4448) { OpDef(op=MuxOp, inputs=List(x4428, Const(1.0), x4438)).name("x4439").srcCtx("IndigoStream.scala:72:40") } // Mux(x4428,Const(1),x4438)
    val x4440 = withCtrl(x4448) { OpDef(op=FixNeg, inputs=List(x4439)).name("x4440").srcCtx("IndigoStream.scala:83:52:mabre") } // FixNeg(x4439)
    val x4441 = withCtrl(x4448) { OpDef(op=FixLt, inputs=List(x4426, Const(0.0))).name("x4441").srcCtx("IndigoStream.scala:84:45") } // FixLt(x4426,Const(0))
    val x4442 = withCtrl(x4448) { OpDef(op=MuxOp, inputs=List(x4441, x4440, x4439)).name("x4442").srcCtx("IndigoStream.scala:84:40:re") } // Mux(x4441,x4440,x4439)
    val x4443 = withCtrl(x4448) { x4442 } // FixPtToFltPt(x4442,_24,_8) //TODO
    val x4444 = withCtrl(x4448) { OpDef(op=FltAdd, inputs=List(x4443, Const(1.0))).name("x4444").srcCtx("IndigoStream.scala:64:60") } // FltAdd(x4443,Const(1))
    val x4445 = withCtrl(x4448) { OpDef(op=FltDiv, inputs=List(x4444, Const(2.0))).name("x4445").srcCtx("IndigoStream.scala:64:75") } // FltDiv(x4444,Const(2))
    val x4446 = withCtrl(x4448) { OpDef(op=FltMul, inputs=List(x4422, x4445)).name("x4446").srcCtx("IndigoStream.scala:165:76") } // FltMul(x4422,x4445)
    val x4447 = withCtrl(x4448) { StoreBanks(List(List(x4332_d0_b0)), List(b3182), x4446).name("x4447").srcCtx("IndigoStream.scala:165:59") } // RegFileStore(x4332,List(b3182),x4446,b3183)
    val x4476 = withCtrl(x4494) { UnitController(style=SeqPipe, level=OuterControl).name("x4476").srcCtx("IndigoStream.scala:178:30") } // UnitPipe(List(Const(true)),Block(Const(())))
    val x4452 = withCtrl(x4476) { Counter(min=Const(0), max=Const(5), step=Const(1), par=1).name("x4452").srcCtx("IndigoStream.scala:179:64") } // CounterNew(Const(0),Const(5),Const(1),Const(1))
    val x4453 = withCtrl(x4476) { CounterChain(List(x4452)).name("x4453").srcCtx("IndigoStream.scala:179:70") } // CounterChainNew(List(x4452))
    val x4475 = withCtrl(x4476) { LoopController(style=MetaPipe, level=OuterControl, cchain=x4453).name("x4475").srcCtx("IndigoStream.scala:179:70") } // UnrolledForeach(List(Const(true)),x4453,Block(Const(())),List(List(b3303)),List(List(b3304)))
    val b3303 = withCtrl(x4475) { CounterIter(x4452, Some(0)).name("b3303") } // b3303
    val b3304 = withCtrl(x4475) { Const(true).name("b3304") } // b3304
    val x4454 = withCtrl(x4475) { Reg(init=Some(0.0)).name("x4454").srcCtx("IndigoStream.scala:181:52:w") } // x4454 = RegNew(Const(0.0))
    isAccum(x4454) = false
    bufferDepthOf(x4454) = 1
    val x4455_d0 = withCtrl(x4475) { Reg(init=Some(0.0)).name("x4455_d0").srcCtx("IndigoStream.scala:183:63") } // x4455 = RegNew(Const(0.0))
    isAccum(x4455_d0) = false
    bufferDepthOf(x4455_d0) = 2
    val x4455_d1 = withCtrl(x4475) { Reg(init=Some(0.0)).name("x4455_d1").srcCtx("IndigoStream.scala:183:63") } // x4455 = RegNew(Const(0.0))
    isAccum(x4455_d1) = true
    bufferDepthOf(x4455_d1) = 1
    val x4456 = withCtrl(x4475) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x4456").srcCtx("IndigoStream.scala:183:91") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x4457 = withCtrl(x4475) { CounterChain(List(x4456)).name("x4457").srcCtx("IndigoStream.scala:185:42") } // CounterChainNew(List(x4456))
    val x4467 = withCtrl(x4475) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4457).name("x4467").srcCtx("IndigoStream.scala:185:42") } // UnrolledReduce(List(b3304),x4457,x4455,Block((x4455) => Const(())),List(List(b3309)),List(List(b3310)))
    val b3309 = withCtrl(x4467) { CounterIter(x4456, None).name("b3309") } // b3309
    val b3310 = withCtrl(x4467) { Const(true).name("b3310") } // b3310
    val x4458 = withCtrl(x4467) { LoadBanks(List(x4332_d0_b0), List(b3309)).name("x4458").srcCtx("IndigoStream.scala:184:55") } // ParRegFileLoad(x4332,List(List(b3309)),List(Const(true)))
    val x4459 = withCtrl(x4467) { x4458 } // VectorApply(x4458,0)
    val x4460 = withCtrl(x4467) { OpDef(op=BitAnd, inputs=List(b3310, b3304)).name("x4460").srcCtx("UnrollingBase.scala:28:66") } // And(b3310,b3304)
    val x4461 = withCtrl(x4467) { LoadBanks(List(x4285_d0_b0), List(b3309, b3303)).name("x4461").srcCtx("IndigoStream.scala:184:69") } // LUTLoad(x4285,List(b3309, b3303),x4460)
    val x4462 = withCtrl(x4467) { OpDef(op=FltMul, inputs=List(x4459, x4461)).name("x4462").srcCtx("IndigoStream.scala:184:59") } // FltMul(x4459,x4461)
    val x4463 = withCtrl(x4467) { ReadMem(x4455_d1).name("x4463").srcCtx("IndigoStream.scala:185:42") } // RegRead(x4455)
    val x4464 = withCtrl(x4467) { OpDef(op=FixEql, inputs=List(b3309, Const(0))).name("x4464").srcCtx("IndigoStream.scala:185:42") } // FixEql(b3309,Const(0))
    val x4465 = withCtrl(x4467) { ReduceAccumOp(op=FltAdd, input=x4462, accum=x4463).name("x4465").srcCtx("IndigoStream.scala:185:44") } // FltAdd(x4462,x4463)
    val x4466_x4455_d0 = withCtrl(x4467) { WriteMem(x4455_d0, x4465).name("x4466_x4455_d0").srcCtx("IndigoStream.scala:185:42") } // RegWrite(x4455,x4465,b3304)
    antiDepsOf(x4466_x4455_d0)=List(x4463)
    val x4466_x4455_d1 = withCtrl(x4467) { WriteMem(x4455_d1, x4465).name("x4466_x4455_d1").srcCtx("IndigoStream.scala:185:42") } // RegWrite(x4455,x4465,b3304)
    antiDepsOf(x4466_x4455_d1)=List(x4463)
    val x4474 = withCtrl(x4475) { UnitController(style=SeqPipe, level=InnerControl).name("x4474").srcCtx("IndigoStream.scala:179:70") } // UnitPipe(List(b3304),Block(Const(())))
    val x4468 = withCtrl(x4474) { ReadMem(x4455_d0).name("x4468").srcCtx("IndigoStream.scala:185:42") } // RegRead(x4455)
    val x4469_x4454 = withCtrl(x4474) { WriteMem(x4454, x4468).name("x4469_x4454").srcCtx("IndigoStream.scala:183:43") } // RegWrite(x4454,x4468,b3304)
    val x4470 = withCtrl(x4474) { ReadMem(x4454).name("x4470").srcCtx("IndigoStream.scala:187:53") } // RegRead(x4454)
    antiDepsOf(x4470)=List(x4469_x4454)
    val x4471 = withCtrl(x4474) { LoadBanks(List(x4286_d0_b0), List(b3303)).name("x4471").srcCtx("IndigoStream.scala:187:65") } // LUTLoad(x4286,List(b3303),b3304)
    val x4472 = withCtrl(x4474) { OpDef(op=FltAdd, inputs=List(x4470, x4471)).name("x4472").srcCtx("IndigoStream.scala:187:55") } // FltAdd(x4470,x4471)
    val x4473 = withCtrl(x4474) { StoreBanks(List(List(x4281_d0_b0)), List(b3303), x4472).name("x4473").srcCtx("IndigoStream.scala:187:51") } // RegFileStore(x4281,List(b3303),x4472,b3304)
    val x4493 = withCtrl(x4494) { UnitController(style=SeqPipe, level=OuterControl).name("x4493").srcCtx("IndigoStream.scala:195:30") } // UnitPipe(List(Const(true)),Block(Const(())))
    val x4477 = withCtrl(x4493) { Reg(init=Some(0.0)).name("x4477").srcCtx("IndigoStream.scala:197:44:w") } // x4477 = RegNew(Const(0.0))
    isAccum(x4477) = false
    bufferDepthOf(x4477) = 1
    val x4478_d0 = withCtrl(x4493) { Reg(init=Some(0.0)).name("x4478_d0").srcCtx("IndigoStream.scala:199:55") } // x4478 = RegNew(Const(0.0))
    isAccum(x4478_d0) = false
    bufferDepthOf(x4478_d0) = 1
    val x4478_d1 = withCtrl(x4493) { Reg(init=Some(0.0)).name("x4478_d1").srcCtx("IndigoStream.scala:199:55") } // x4478 = RegNew(Const(0.0))
    isAccum(x4478_d1) = true
    bufferDepthOf(x4478_d1) = 1
    val x4479 = withCtrl(x4493) { Counter(min=Const(0), max=Const(5), step=Const(1), par=1).name("x4479").srcCtx("IndigoStream.scala:199:90") } // CounterNew(Const(0),Const(5),Const(1),Const(1))
    val x4480 = withCtrl(x4493) { CounterChain(List(x4479)).name("x4480").srcCtx("IndigoStream.scala:201:34") } // CounterChainNew(List(x4479))
    val x4487 = withCtrl(x4493) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4480).name("x4487").srcCtx("IndigoStream.scala:201:34") } // UnrolledReduce(List(Const(true)),x4480,x4478,Block((x4478) => Const(())),List(List(b3334)),List(List(b3335)))
    val b3334 = withCtrl(x4487) { CounterIter(x4479, None).name("b3334") } // b3334
    val b3335 = withCtrl(x4487) { Const(true).name("b3335") } // b3335
    val x4481 = withCtrl(x4487) { LoadBanks(List(x4281_d0_b0), List(b3334)).name("x4481").srcCtx("IndigoStream.scala:200:47") } // ParRegFileLoad(x4281,List(List(b3334)),List(Const(true)))
    val x4482 = withCtrl(x4487) { x4481 } // VectorApply(x4481,0)
    val x4483 = withCtrl(x4487) { ReadMem(x4478_d1).name("x4483").srcCtx("IndigoStream.scala:201:34") } // RegRead(x4478)
    val x4484 = withCtrl(x4487) { OpDef(op=FixEql, inputs=List(b3334, Const(0))).name("x4484").srcCtx("IndigoStream.scala:201:34") } // FixEql(b3334,Const(0))
    val x4485 = withCtrl(x4487) { ReduceAccumOp(op=FltAdd, input=x4482, accum=x4483).name("x4485").srcCtx("IndigoStream.scala:201:38") } // FltAdd(x4482,x4483)
    val x4486_x4478_d0 = withCtrl(x4487) { WriteMem(x4478_d0, x4485).name("x4486_x4478_d0").srcCtx("IndigoStream.scala:201:34") } // RegWrite(x4478,x4485,Const(true))
    antiDepsOf(x4486_x4478_d0)=List(x4483)
    val x4486_x4478_d1 = withCtrl(x4487) { WriteMem(x4478_d1, x4485).name("x4486_x4478_d1").srcCtx("IndigoStream.scala:201:34") } // RegWrite(x4478,x4485,Const(true))
    antiDepsOf(x4486_x4478_d1)=List(x4483)
    val x4492 = withCtrl(x4493) { UnitController(style=SeqPipe, level=InnerControl).name("x4492").srcCtx("IndigoStream.scala:195:30") } // UnitPipe(List(Const(true)),Block(Const(())))
    val x4488 = withCtrl(x4492) { ReadMem(x4478_d0).name("x4488").srcCtx("IndigoStream.scala:201:34") } // RegRead(x4478)
    val x4489_x4477 = withCtrl(x4492) { WriteMem(x4477, x4488).name("x4489_x4477").srcCtx("IndigoStream.scala:199:35") } // RegWrite(x4477,x4488,Const(true))
    val x4490 = withCtrl(x4492) { ReadMem(x4477).name("x4490").srcCtx("IndigoStream.scala:203:47") } // RegRead(x4477)
    antiDepsOf(x4490)=List(x4489_x4477)
    val x4491_x4491_x4267 = withCtrl(x4492) { WriteMem(x4267, x4490).name("x4491_x4491_x4267").srcCtx("IndigoStream.scala:203:44") } // StreamWrite(x4267,x4490,Const(true))
    
  }
}
