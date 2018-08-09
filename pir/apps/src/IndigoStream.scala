import pir._
import pir.node._
import arch._
import prism.enums._

object IndigoStream extends PIRApp {
  def main(implicit design:PIRDesign) = {
    import design.pirmeta._
    val x4370 = withCtrl(design.top.topController) { StreamIn(field="data").name("x4370").srcCtx("IndigoStream.scala:52:40:x") } // x4370 = StreamInNew(GPInput1)
    isAccum(x4370) = false
    bufferDepthOf(x4370) = 1
    countOf(x4370) = Some(1024l)
    val x4371 = withCtrl(design.top.topController) { StreamOut(field="data").name("x4371").srcCtx("IndigoStream.scala:53:50:stream_out") } // x4371 = StreamOutNew(GPOutput1)
    isAccum(x4371) = false
    bufferDepthOf(x4371) = 1
    // x4372 = Forever() TODO: Unmatched Node
    val x4605 = withCtrl(design.top.topController) { ForeverController().name("x4605").srcCtx("IndigoStream.scala:59:26") } // Hwblock(Block(Const(())),true)
    val x4373 = withCtrl(x4605) { Reg(init=Some(0.0)).name("x4373").srcCtx("IndigoStream.scala:90:47:s_reg") } // x4373 = RegNew(Const(0.0))
    isAccum(x4373) = false
    bufferDepthOf(x4373) = 2
    val x4374_d0_b0 = withCtrl(x4605) { RegFile(size=2, inits=None).name("x4374_d0_b0").srcCtx("IndigoStream.scala:91:57:L1_h") } // x4374 = RegFileNew(ArrayBuffer(Const(2)),None) banking:Strided(banks=1, stride=1)
    isAccum(x4374_d0_b0) = false
    bufferDepthOf(x4374_d0_b0) = 6
    staticDimsOf(x4374_d0_b0) = List(2)
    val x4374_d1_b0 = withCtrl(x4605) { RegFile(size=2, inits=None).name("x4374_d1_b0").srcCtx("IndigoStream.scala:91:57:L1_h") } // x4374 = RegFileNew(ArrayBuffer(Const(2)),None) banking:Strided(banks=1, stride=1)
    isAccum(x4374_d1_b0) = false
    bufferDepthOf(x4374_d1_b0) = 5
    staticDimsOf(x4374_d1_b0) = List(2)
    val x4375_d0_b0 = withCtrl(x4605) { RegFile(size=8, inits=None).name("x4375_d0_b0").srcCtx("IndigoStream.scala:92:52:L1_tmp") } // x4375 = RegFileNew(ArrayBuffer(Const(8)),None) banking:Strided(banks=1, stride=1)
    isAccum(x4375_d0_b0) = false
    bufferDepthOf(x4375_d0_b0) = 2
    staticDimsOf(x4375_d0_b0) = List(8)
    val x4375_d1_b0 = withCtrl(x4605) { RegFile(size=8, inits=None).name("x4375_d1_b0").srcCtx("IndigoStream.scala:92:52:L1_tmp") } // x4375 = RegFileNew(ArrayBuffer(Const(8)),None) banking:Strided(banks=1, stride=1)
    isAccum(x4375_d1_b0) = false
    bufferDepthOf(x4375_d1_b0) = 2
    staticDimsOf(x4375_d1_b0) = List(8)
    val x4375_d2_b0 = withCtrl(x4605) { RegFile(size=8, inits=None).name("x4375_d2_b0").srcCtx("IndigoStream.scala:92:52:L1_tmp") } // x4375 = RegFileNew(ArrayBuffer(Const(8)),None) banking:Strided(banks=1, stride=1)
    isAccum(x4375_d2_b0) = false
    bufferDepthOf(x4375_d2_b0) = 2
    staticDimsOf(x4375_d2_b0) = List(8)
    val x4375_d3_b0 = withCtrl(x4605) { RegFile(size=8, inits=None).name("x4375_d3_b0").srcCtx("IndigoStream.scala:92:52:L1_tmp") } // x4375 = RegFileNew(ArrayBuffer(Const(8)),None) banking:Strided(banks=1, stride=1)
    isAccum(x4375_d3_b0) = false
    bufferDepthOf(x4375_d3_b0) = 2
    staticDimsOf(x4375_d3_b0) = List(8)
    val x4376 = withCtrl(x4605) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x4376").srcCtx("IndigoStream.scala:96:35") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x4377 = withCtrl(x4605) { CounterChain(List(x4376)).name("x4377").srcCtx("IndigoStream.scala:96:54") } // CounterChainNew(List(x4376))
    val x4379 = withCtrl(x4605) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4377).name("x4379").srcCtx("IndigoStream.scala:96:54") } // UnrolledForeach(List(Const(true)),x4377,Block(Const(())),List(List(b3176)),List(List(b3177)))
    val b3176 = withCtrl(x4379) { CounterIter(x4376, None).name("b3176") } // b3176
    val b3177 = withCtrl(x4379) { Const(true).name("b3177") } // b3177
    val x4378 = withCtrl(x4379) { StoreBanks(List(List(x4374_d0_b0), List(x4374_d1_b0)), List(b3176), Const(0.0)).name("x4378").srcCtx("IndigoStream.scala:97:41") } // ParRegFileStore(x4374,List(List(b3176)),List(Const(0.0)),List(Const(true)))
    val x4380_d0_b0 = withCtrl(x4605) { RegFile(size=2, inits=None).name("x4380_d0_b0").srcCtx("IndigoStream.scala:100:57:L1_C") } // x4380 = RegFileNew(ArrayBuffer(Const(2)),None) banking:Strided(banks=1, stride=1)
    isAccum(x4380_d0_b0) = false
    bufferDepthOf(x4380_d0_b0) = 4
    staticDimsOf(x4380_d0_b0) = List(2)
    val x4381_d0_b0 = withCtrl(x4605) { RegFile(size=2, inits=None).name("x4381_d0_b0").srcCtx("IndigoStream.scala:101:52:L1_C_p") } // x4381 = RegFileNew(ArrayBuffer(Const(2)),None) banking:Strided(banks=1, stride=1)
    isAccum(x4381_d0_b0) = false
    bufferDepthOf(x4381_d0_b0) = 1
    staticDimsOf(x4381_d0_b0) = List(2)
    val x4382 = withCtrl(x4605) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x4382").srcCtx("IndigoStream.scala:103:35") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x4383 = withCtrl(x4605) { CounterChain(List(x4382)).name("x4383").srcCtx("IndigoStream.scala:103:54") } // CounterChainNew(List(x4382))
    val x4385 = withCtrl(x4605) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4383).name("x4385").srcCtx("IndigoStream.scala:103:54") } // UnrolledForeach(List(Const(true)),x4383,Block(Const(())),List(List(b3184)),List(List(b3185)))
    val b3184 = withCtrl(x4385) { CounterIter(x4382, None).name("b3184") } // b3184
    val b3185 = withCtrl(x4385) { Const(true).name("b3185") } // b3185
    val x4384 = withCtrl(x4385) { StoreBanks(List(List(x4380_d0_b0)), List(b3184), Const(0.0)).name("x4384").srcCtx("IndigoStream.scala:104:41") } // ParRegFileStore(x4380,List(List(b3184)),List(Const(0.0)),List(Const(true)))
    val x4386_d0_b0 = withCtrl(x4605) { RegFile(size=5, inits=None).name("x4386_d0_b0").srcCtx("IndigoStream.scala:107:52:L2_tmp") } // x4386 = RegFileNew(ArrayBuffer(Const(5)),None) banking:Strided(banks=1, stride=1)
    isAccum(x4386_d0_b0) = false
    bufferDepthOf(x4386_d0_b0) = 2
    staticDimsOf(x4386_d0_b0) = List(5)
    val x4387_d0_b0 = withCtrl(x4605) { LUT(inits=List(17.0), banking=Strided(banks=1, stride=1)).name("x4387_d0_b0").srcCtx("IndigoStream.scala:109:79:input") } // x4387 = LUTNew(List(1, 1), Seq(Const(17.0000)))
    isAccum(x4387_d0_b0) = false
    bufferDepthOf(x4387_d0_b0) = 1
    staticDimsOf(x4387_d0_b0) = List(1, 1)
    val x4388_d0_b0 = withCtrl(x4605) { LUT(inits=List(-0.0919745, -0.17325345, 0.07901578, -0.22862436, -0.33561853, 0.37217242, 0.28228363, 0.62458235, -0.710848, 0.6203918, -0.63714087, 0.33077937, 0.7237721, -0.70807374, -0.7135825, 0.3864259, 0.36924678, -0.63785505, 0.16256636, -0.26040027, -0.35571584, 0.15920722, -0.22067755, 0.45712513), banking=Strided(banks=1, stride=8)).name("x4388_d0_b0").srcCtx("IndigoStream.scala:111:119:L1_W_LUT") } // x4388 = LUTNew(List(3, 8), Seq(Const(-0.0919744968414306640625),Const(-0.1732534468173980712890625),Const(0.07901577651500701904296875)... [21 more]))
    isAccum(x4388_d0_b0) = false
    bufferDepthOf(x4388_d0_b0) = 1
    staticDimsOf(x4388_d0_b0) = List(3, 8)
    val x4389_d0_b0 = withCtrl(x4605) { LUT(inits=List(1.5417027E-5, 7.0991955E-4, 1.34923275E-5, -5.104995E-6, 0.0, 0.0, 3.5079447E-7, 1.2294923E-7), banking=Strided(banks=1, stride=1)).name("x4389_d0_b0").srcCtx("IndigoStream.scala:112:74:L1_B_LUT") } // x4389 = LUTNew(List(8), Seq(Const(0.00001541702658869326114654541015625),Const(0.0007099195499904453754425048828125),Const(0.00001349232752545503899455070495605469)... [5 more]))
    isAccum(x4389_d0_b0) = false
    bufferDepthOf(x4389_d0_b0) = 1
    staticDimsOf(x4389_d0_b0) = List(8)
    val x4390_d0_b0 = withCtrl(x4605) { LUT(inits=List(0.19029346, 0.47020784, -0.7556679, 0.34903032, -0.049420655, 0.05752103, 0.73263806, -0.2690976, -1.846328E-5, -0.66465366), banking=Strided(banks=1, stride=5)).name("x4390_d0_b0").srcCtx("IndigoStream.scala:115:89:L2_W_LUT") } // x4390 = LUTNew(List(2, 5), Seq(Const(0.1902934610843658447265625),Const(0.4702078402042388916015625),Const(-0.7556679248809814453125)... [7 more]))
    isAccum(x4390_d0_b0) = false
    bufferDepthOf(x4390_d0_b0) = 1
    staticDimsOf(x4390_d0_b0) = List(2, 5)
    val x4391_d0_b0 = withCtrl(x4605) { LUT(inits=List(0.79848474, 0.8013729, 0.8108321, 0.79501843, 0.79428965), banking=Strided(banks=1, stride=1)).name("x4391_d0_b0").srcCtx("IndigoStream.scala:116:75:L2_B_LUT") } // x4391 = LUTNew(List(5), Seq(Const(0.798484742641448974609375),Const(0.80137288570404052734375),Const(0.810832083225250244140625)... [2 more]))
    isAccum(x4391_d0_b0) = false
    bufferDepthOf(x4391_d0_b0) = 1
    staticDimsOf(x4391_d0_b0) = List(5)
    val x4394 = withCtrl(x4605) { UnitController(style=SeqPipe, level=InnerControl).name("x4394").srcCtx("IndigoStream.scala:118:30") } // UnitPipe(List(Const(true)),Block(x4393))
    val x4392_x4392 = withCtrl(x4394) { ReadMem(x4370).name("x4392_x4392").srcCtx("IndigoStream.scala:119:44") } // StreamRead(x4370,Const(true))
    val x4393_x4373 = withCtrl(x4394) { WriteMem(x4373, x4392_x4392).name("x4393_x4373").srcCtx("IndigoStream.scala:119:39") } // RegWrite(x4373,x4392,Const(true))
    val x4436 = withCtrl(x4605) { UnitController(style=SeqPipe, level=OuterControl).name("x4436").srcCtx("IndigoStream.scala:124:30") } // UnitPipe(List(Const(true)),Block(Const(())))
    val x4435 = withCtrl(x4436) { UnitController(style=SeqPipe, level=OuterControl).name("x4435").srcCtx("IndigoStream.scala:125:38") } // UnitPipe(List(Const(true)),Block(Const(())))
    val x4395_d0_b0 = withCtrl(x4435) { RegFile(size=3, inits=None).name("x4395_d0_b0").srcCtx("IndigoStream.scala:126:64:input_step") } // x4395 = RegFileNew(ArrayBuffer(Const(3)),None) banking:Strided(banks=1, stride=1)
    isAccum(x4395_d0_b0) = false
    bufferDepthOf(x4395_d0_b0) = 1
    staticDimsOf(x4395_d0_b0) = List(3)
    val x4396 = withCtrl(x4435) { Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x4396").srcCtx("IndigoStream.scala:128:43") } // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x4397 = withCtrl(x4435) { CounterChain(List(x4396)).name("x4397").srcCtx("IndigoStream.scala:128:60") } // CounterChainNew(List(x4396))
    val x4400 = withCtrl(x4435) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4397).name("x4400").srcCtx("IndigoStream.scala:128:60") } // UnrolledForeach(List(Const(true)),x4397,Block(Const(())),List(List(b3200)),List(List(b3201)))
    val b3200 = withCtrl(x4400) { CounterIter(x4396, None).name("b3200") } // b3200
    val b3201 = withCtrl(x4400) { Const(true).name("b3201") } // b3201
    val x4398 = withCtrl(x4400) { LoadBanks(List(x4387_d0_b0), List(Const(0), b3200)).name("x4398").srcCtx("IndigoStream.scala:129:62") } // LUTLoad(x4387,List(Const(0), b3200),b3201)
    val x4399 = withCtrl(x4400) { StoreBanks(List(List(x4395_d0_b0)), List(b3200), x4398).name("x4399").srcCtx("IndigoStream.scala:129:55") } // ParRegFileStore(x4395,List(List(b3200)),List(x4398),List(Const(true)))
    val x4401 = withCtrl(x4435) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x4401").srcCtx("IndigoStream.scala:132:43") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x4402 = withCtrl(x4435) { CounterChain(List(x4401)).name("x4402").srcCtx("IndigoStream.scala:132:62") } // CounterChainNew(List(x4401))
    val x4407 = withCtrl(x4435) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4402).name("x4407").srcCtx("IndigoStream.scala:132:62") } // UnrolledForeach(List(Const(true)),x4402,Block(Const(())),List(List(b3207)),List(List(b3208)))
    val b3207 = withCtrl(x4407) { CounterIter(x4401, None).name("b3207") } // b3207
    val b3208 = withCtrl(x4407) { Const(true).name("b3208") } // b3208
    val x4403 = withCtrl(x4407) { OpDef(op=FixAdd, inputs=List(b3207, Const(1))).name("x4403").srcCtx("IndigoStream.scala:133:54") } // FixAdd(b3207,Const(1))
    val x4404 = withCtrl(x4407) { LoadBanks(List(x4374_d1_b0), List(b3207)).name("x4404").srcCtx("IndigoStream.scala:133:74") } // ParRegFileLoad(x4374,List(List(b3207)),List(Const(true)))
    val x4405 = withCtrl(x4407) { x4404 } // VectorApply(x4404,0)
    val x4406 = withCtrl(x4407) { StoreBanks(List(List(x4395_d0_b0)), List(x4403), x4405).name("x4406").srcCtx("IndigoStream.scala:133:68") } // ParRegFileStore(x4395,List(List(x4403)),List(x4405),List(Const(true)))
    val x4408 = withCtrl(x4435) { Counter(min=Const(0), max=Const(8), step=Const(1), par=1).name("x4408").srcCtx("IndigoStream.scala:135:81") } // CounterNew(Const(0),Const(8),Const(1),Const(1))
    val x4409 = withCtrl(x4435) { CounterChain(List(x4408)).name("x4409").srcCtx("IndigoStream.scala:135:87") } // CounterChainNew(List(x4408))
    val x4434 = withCtrl(x4435) { LoopController(style=MetaPipe, level=OuterControl, cchain=x4409).name("x4434").srcCtx("IndigoStream.scala:135:87") } // UnrolledForeach(List(Const(true)),x4409,Block(Const(())),List(List(b3216)),List(List(b3217)))
    val b3216 = withCtrl(x4434) { CounterIter(x4408, Some(0)).name("b3216") } // b3216
    val b3217 = withCtrl(x4434) { Const(true).name("b3217") } // b3217
    val x4410 = withCtrl(x4434) { Reg(init=Some(0.0)).name("x4410").srcCtx("IndigoStream.scala:137:52:w") } // x4410 = RegNew(Const(0.0))
    isAccum(x4410) = false
    bufferDepthOf(x4410) = 1
    val x4411_d0 = withCtrl(x4434) { Reg(init=Some(0.0)).name("x4411_d0").srcCtx("IndigoStream.scala:139:63") } // x4411 = RegNew(Const(0.0))
    isAccum(x4411_d0) = false
    bufferDepthOf(x4411_d0) = 2
    val x4411_d1 = withCtrl(x4434) { Reg(init=Some(0.0)).name("x4411_d1").srcCtx("IndigoStream.scala:139:63") } // x4411 = RegNew(Const(0.0))
    isAccum(x4411_d1) = true
    bufferDepthOf(x4411_d1) = 1
    val x4412 = withCtrl(x4434) { Counter(min=Const(0), max=Const(3), step=Const(1), par=1).name("x4412").srcCtx("IndigoStream.scala:139:112") } // CounterNew(Const(0),Const(3),Const(1),Const(1))
    val x4413 = withCtrl(x4434) { CounterChain(List(x4412)).name("x4413").srcCtx("IndigoStream.scala:142:42") } // CounterChainNew(List(x4412))
    val x4424 = withCtrl(x4434) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4413).name("x4424").srcCtx("IndigoStream.scala:142:42") } // UnrolledReduce(List(b3217),x4413,x4411,Block((x4411) => Const(())),List(List(b3222)),List(List(b3223)))
    val b3222 = withCtrl(x4424) { CounterIter(x4412, None).name("b3222") } // b3222
    val b3223 = withCtrl(x4424) { Const(true).name("b3223") } // b3223
    val x4414 = withCtrl(x4424) { OpDef(op=BitAnd, inputs=List(b3223, b3217)).name("x4414").srcCtx("UnrollingBase.scala:28:66") } // And(b3223,b3217)
    val x4415 = withCtrl(x4424) { LoadBanks(List(x4388_d0_b0), List(b3222, b3216)).name("x4415").srcCtx("IndigoStream.scala:140:68") } // LUTLoad(x4388,List(b3222, b3216),x4414)
    val x4416 = withCtrl(x4424) { x4415 } // FltConvert(x4415,_24,_8) //TODO
    val x4417 = withCtrl(x4424) { LoadBanks(List(x4395_d0_b0), List(b3222)).name("x4417").srcCtx("IndigoStream.scala:141:66") } // ParRegFileLoad(x4395,List(List(b3222)),List(Const(true)))
    val x4418 = withCtrl(x4424) { x4417 } // VectorApply(x4417,0)
    val x4419 = withCtrl(x4424) { OpDef(op=FltMul, inputs=List(x4416, x4418)).name("x4419").srcCtx("IndigoStream.scala:141:54") } // FltMul(x4416,x4418)
    val x4420 = withCtrl(x4424) { ReadMem(x4411_d1).name("x4420").srcCtx("IndigoStream.scala:142:42") } // RegRead(x4411)
    val x4421 = withCtrl(x4424) { OpDef(op=FixEql, inputs=List(b3222, Const(0))).name("x4421").srcCtx("IndigoStream.scala:142:42") } // FixEql(b3222,Const(0))
    val x4422 = withCtrl(x4424) { ReduceAccumOp(op=FltAdd, input=x4419, accum=x4420).name("x4422").srcCtx("IndigoStream.scala:142:45") } // FltAdd(x4419,x4420)
    val x4423_x4411_d0 = withCtrl(x4424) { WriteMem(x4411_d0, x4422).name("x4423_x4411_d0").srcCtx("IndigoStream.scala:142:42") } // RegWrite(x4411,x4422,b3217)
    antiDepsOf(x4423_x4411_d0)=List(x4420)
    val x4423_x4411_d1 = withCtrl(x4424) { WriteMem(x4411_d1, x4422).name("x4423_x4411_d1").srcCtx("IndigoStream.scala:142:42") } // RegWrite(x4411,x4422,b3217)
    antiDepsOf(x4423_x4411_d1)=List(x4420)
    val x4433 = withCtrl(x4434) { UnitController(style=SeqPipe, level=InnerControl).name("x4433").srcCtx("IndigoStream.scala:135:87") } // UnitPipe(List(b3217),Block(Const(())))
    val x4425 = withCtrl(x4433) { ReadMem(x4411_d0).name("x4425").srcCtx("IndigoStream.scala:142:42") } // RegRead(x4411)
    val x4426_x4410 = withCtrl(x4433) { WriteMem(x4410, x4425).name("x4426_x4410").srcCtx("IndigoStream.scala:139:43") } // RegWrite(x4410,x4425,b3217)
    val x4427 = withCtrl(x4433) { ReadMem(x4410).name("x4427").srcCtx("IndigoStream.scala:144:53") } // RegRead(x4410)
    antiDepsOf(x4427)=List(x4426_x4410)
    val x4428 = withCtrl(x4433) { LoadBanks(List(x4389_d0_b0), List(b3216)).name("x4428").srcCtx("IndigoStream.scala:144:65") } // LUTLoad(x4389,List(b3216),b3217)
    val x4429 = withCtrl(x4433) { OpDef(op=FltAdd, inputs=List(x4427, x4428)).name("x4429").srcCtx("IndigoStream.scala:144:55") } // FltAdd(x4427,x4428)
    val x4430 = withCtrl(x4433) { ReadMem(x4373).name("x4430").srcCtx("IndigoStream.scala:144:71") } // RegRead(x4373)
    val x4431 = withCtrl(x4433) { OpDef(op=FltAdd, inputs=List(x4429, x4430)).name("x4431").srcCtx("IndigoStream.scala:144:69") } // FltAdd(x4429,x4430)
    val x4432 = withCtrl(x4433) { StoreBanks(List(List(x4375_d0_b0), List(x4375_d1_b0), List(x4375_d2_b0), List(x4375_d3_b0)), List(b3216), x4431).name("x4432").srcCtx("IndigoStream.scala:144:51") } // RegFileStore(x4375,List(b3216),x4431,b3217)
    val x4562 = withCtrl(x4605) { UnitController(style=SeqPipe, level=OuterControl).name("x4562").srcCtx("IndigoStream.scala:159:30") } // UnitPipe(List(Const(true)),Block(Const(())))
    val x4561 = withCtrl(x4562) { UnitController(style=SeqPipe, level=OuterControl).name("x4561").srcCtx("IndigoStream.scala:161:38") } // UnitPipe(List(Const(true)),Block(Const(())))
    val x4437 = withCtrl(x4561) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x4437").srcCtx("IndigoStream.scala:162:51") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x4438 = withCtrl(x4561) { CounterChain(List(x4437)).name("x4438").srcCtx("IndigoStream.scala:162:71") } // CounterChainNew(List(x4437))
    val x4554 = withCtrl(x4561) { LoopController(style=MetaPipe, level=OuterControl, cchain=x4438).name("x4554").srcCtx("IndigoStream.scala:162:71") } // UnrolledForeach(List(Const(true)),x4438,Block(Const(())),List(List(b3249)),List(List(b3250)))
    val b3249 = withCtrl(x4554) { CounterIter(x4437, Some(0)).name("b3249") } // b3249
    val b3250 = withCtrl(x4554) { Const(true).name("b3250") } // b3250
    val x4553 = withCtrl(x4554) { UnitController(style=SeqPipe, level=InnerControl).name("x4553").srcCtx("IndigoStream.scala:162:71") } // UnitPipe(List(b3250),Block(Const(())))
    val x4439 = withCtrl(x4553) { LoadBanks(List(x4380_d0_b0), List(b3249)).name("x4439").srcCtx("IndigoStream.scala:163:62") } // RegFileLoad(x4380,List(b3249),b3250)
    val x4440 = withCtrl(x4553) { OpDef(op=FixAdd, inputs=List(b3249, Const(4))).name("x4440").srcCtx("IndigoStream.scala:163:91") } // FixAdd(b3249,Const(4))
    val x4441 = withCtrl(x4553) { LoadBanks(List(x4375_d3_b0), List(x4440)).name("x4441").srcCtx("IndigoStream.scala:163:88") } // RegFileLoad(x4375,List(x4440),b3250)
    val x4442 = withCtrl(x4553) { OpDef(op=FltAdd, inputs=List(x4441, Const(1.0))).name("x4442").srcCtx("IndigoStream.scala:163:111") } // FltAdd(x4441,Const(1))
    val x4443 = withCtrl(x4553) { OpDef(op=FltDiv, inputs=List(x4442, Const(2.0))).name("x4443").srcCtx("IndigoStream.scala:64:45") } // FltDiv(x4442,Const(2))
    val x4444 = withCtrl(x4553) { x4443 } // FltPtToFixPt(x4443,TRUE,_16,_16) //TODO
    val x4445 = withCtrl(x4553) { OpDef(op=FixAbs, inputs=List(x4444)).name("x4445").srcCtx("IndigoStream.scala:71:42:absp") } // FixAbs(x4444)
    val x4446 = withCtrl(x4553) { OpDef(op=FixLt, inputs=List(Const(2.5), x4445)).name("x4446").srcCtx("IndigoStream.scala:72:49") } // FixLt(Const(2.5),x4445)
    val x4447 = withCtrl(x4553) { OpDef(op=BitNot, inputs=List(x4446)).name("x4447").srcCtx("IndigoStream.scala:72:40") } // Not(x4446)
    val x4448 = withCtrl(x4553) { OpDef(op=FixLt, inputs=List(Const(0.5), x4445)).name("x4448").srcCtx("IndigoStream.scala:74:56") } // FixLt(Const(0.5),x4445)
    val x4449 = withCtrl(x4553) { OpDef(op=FixLeq, inputs=List(x4445, Const(2.5))).name("x4449").srcCtx("IndigoStream.scala:74:73") } // FixLeq(x4445,Const(2.5))
    val x4450 = withCtrl(x4553) { OpDef(op=BitAnd, inputs=List(x4448, x4449)).name("x4450").srcCtx("IndigoStream.scala:74:64") } // And(x4448,x4449)
    val x4451 = withCtrl(x4553) { OpDef(op=BitAnd, inputs=List(x4450, x4447)).name("x4451").srcCtx("IndigoStream.scala:72:40") } // And(x4450,x4447)
    val x4452 = withCtrl(x4553) { OpDef(op=BitNot, inputs=List(x4450)).name("x4452").srcCtx("IndigoStream.scala:72:40") } // Not(x4450)
    val x4453 = withCtrl(x4553) { OpDef(op=BitAnd, inputs=List(x4452, x4447)).name("x4453").srcCtx("IndigoStream.scala:72:40") } // And(x4452,x4447)
    val x4454 = withCtrl(x4553) { OpDef(op=FixSra, inputs=List(x4445, Const(2))).name("x4454").srcCtx("IndigoStream.scala:76:52:div4") } // FixRsh(x4445,Const(2))
    val x4455 = withCtrl(x4553) { OpDef(op=FixAdd, inputs=List(x4454, Const(0.375))).name("x4455").srcCtx("IndigoStream.scala:77:58:div4Offset") } // FixAdd(x4454,Const(0.375))
    val x4456 = withCtrl(x4553) { OpDef(op=MuxOp, inputs=List(x4451, x4455, x4445)).name("x4456").srcCtx("IndigoStream.scala:72:40") } // Mux(x4451,x4455,x4445)
    val x4457 = withCtrl(x4553) { OpDef(op=MuxOp, inputs=List(x4446, Const(1.0), x4456)).name("x4457").srcCtx("IndigoStream.scala:72:40") } // Mux(x4446,Const(1),x4456)
    val x4458 = withCtrl(x4553) { OpDef(op=FixNeg, inputs=List(x4457)).name("x4458").srcCtx("IndigoStream.scala:83:52:mabre") } // FixNeg(x4457)
    val x4459 = withCtrl(x4553) { OpDef(op=FixLt, inputs=List(x4444, Const(0.0))).name("x4459").srcCtx("IndigoStream.scala:84:45") } // FixLt(x4444,Const(0))
    val x4460 = withCtrl(x4553) { OpDef(op=MuxOp, inputs=List(x4459, x4458, x4457)).name("x4460").srcCtx("IndigoStream.scala:84:40:re") } // Mux(x4459,x4458,x4457)
    val x4461 = withCtrl(x4553) { x4460 } // FixPtToFltPt(x4460,_24,_8) //TODO
    val x4462 = withCtrl(x4553) { OpDef(op=FltAdd, inputs=List(x4461, Const(1.0))).name("x4462").srcCtx("IndigoStream.scala:64:60") } // FltAdd(x4461,Const(1))
    val x4463 = withCtrl(x4553) { OpDef(op=FltDiv, inputs=List(x4462, Const(2.0))).name("x4463").srcCtx("IndigoStream.scala:64:75") } // FltDiv(x4462,Const(2))
    val x4464 = withCtrl(x4553) { OpDef(op=FltMul, inputs=List(x4439, x4463)).name("x4464").srcCtx("IndigoStream.scala:163:66") } // FltMul(x4439,x4463)
    val x4465 = withCtrl(x4553) { LoadBanks(List(x4375_d2_b0), List(b3249)).name("x4465").srcCtx("IndigoStream.scala:163:141") } // RegFileLoad(x4375,List(b3249),b3250)
    val x4466 = withCtrl(x4553) { OpDef(op=FltDiv, inputs=List(x4465, Const(2.0))).name("x4466").srcCtx("IndigoStream.scala:64:45") } // FltDiv(x4465,Const(2))
    val x4467 = withCtrl(x4553) { x4466 } // FltPtToFixPt(x4466,TRUE,_16,_16) //TODO
    val x4468 = withCtrl(x4553) { OpDef(op=FixAbs, inputs=List(x4467)).name("x4468").srcCtx("IndigoStream.scala:71:42:absp") } // FixAbs(x4467)
    val x4469 = withCtrl(x4553) { OpDef(op=FixLt, inputs=List(Const(2.5), x4468)).name("x4469").srcCtx("IndigoStream.scala:72:49") } // FixLt(Const(2.5),x4468)
    val x4470 = withCtrl(x4553) { OpDef(op=BitNot, inputs=List(x4469)).name("x4470").srcCtx("IndigoStream.scala:72:40") } // Not(x4469)
    val x4471 = withCtrl(x4553) { OpDef(op=FixLt, inputs=List(Const(0.5), x4468)).name("x4471").srcCtx("IndigoStream.scala:74:56") } // FixLt(Const(0.5),x4468)
    val x4472 = withCtrl(x4553) { OpDef(op=FixLeq, inputs=List(x4468, Const(2.5))).name("x4472").srcCtx("IndigoStream.scala:74:73") } // FixLeq(x4468,Const(2.5))
    val x4473 = withCtrl(x4553) { OpDef(op=BitAnd, inputs=List(x4471, x4472)).name("x4473").srcCtx("IndigoStream.scala:74:64") } // And(x4471,x4472)
    val x4474 = withCtrl(x4553) { OpDef(op=BitAnd, inputs=List(x4473, x4470)).name("x4474").srcCtx("IndigoStream.scala:72:40") } // And(x4473,x4470)
    val x4475 = withCtrl(x4553) { OpDef(op=BitNot, inputs=List(x4473)).name("x4475").srcCtx("IndigoStream.scala:72:40") } // Not(x4473)
    val x4476 = withCtrl(x4553) { OpDef(op=BitAnd, inputs=List(x4475, x4470)).name("x4476").srcCtx("IndigoStream.scala:72:40") } // And(x4475,x4470)
    val x4477 = withCtrl(x4553) { OpDef(op=FixSra, inputs=List(x4468, Const(2))).name("x4477").srcCtx("IndigoStream.scala:76:52:div4") } // FixRsh(x4468,Const(2))
    val x4478 = withCtrl(x4553) { OpDef(op=FixAdd, inputs=List(x4477, Const(0.375))).name("x4478").srcCtx("IndigoStream.scala:77:58:div4Offset") } // FixAdd(x4477,Const(0.375))
    val x4479 = withCtrl(x4553) { OpDef(op=MuxOp, inputs=List(x4474, x4478, x4468)).name("x4479").srcCtx("IndigoStream.scala:72:40") } // Mux(x4474,x4478,x4468)
    val x4480 = withCtrl(x4553) { OpDef(op=MuxOp, inputs=List(x4469, Const(1.0), x4479)).name("x4480").srcCtx("IndigoStream.scala:72:40") } // Mux(x4469,Const(1),x4479)
    val x4481 = withCtrl(x4553) { OpDef(op=FixNeg, inputs=List(x4480)).name("x4481").srcCtx("IndigoStream.scala:83:52:mabre") } // FixNeg(x4480)
    val x4482 = withCtrl(x4553) { OpDef(op=FixLt, inputs=List(x4467, Const(0.0))).name("x4482").srcCtx("IndigoStream.scala:84:45") } // FixLt(x4467,Const(0))
    val x4483 = withCtrl(x4553) { OpDef(op=MuxOp, inputs=List(x4482, x4481, x4480)).name("x4483").srcCtx("IndigoStream.scala:84:40:re") } // Mux(x4482,x4481,x4480)
    val x4484 = withCtrl(x4553) { x4483 } // FixPtToFltPt(x4483,_24,_8) //TODO
    val x4485 = withCtrl(x4553) { OpDef(op=FltAdd, inputs=List(x4484, Const(1.0))).name("x4485").srcCtx("IndigoStream.scala:64:60") } // FltAdd(x4484,Const(1))
    val x4486 = withCtrl(x4553) { OpDef(op=FltDiv, inputs=List(x4485, Const(2.0))).name("x4486").srcCtx("IndigoStream.scala:64:75") } // FltDiv(x4485,Const(2))
    val x4487 = withCtrl(x4553) { OpDef(op=FixAdd, inputs=List(b3249, Const(2))).name("x4487").srcCtx("IndigoStream.scala:163:168") } // FixAdd(b3249,Const(2))
    val x4488 = withCtrl(x4553) { LoadBanks(List(x4375_d1_b0), List(x4487)).name("x4488").srcCtx("IndigoStream.scala:163:165") } // RegFileLoad(x4375,List(x4487),b3250)
    val x4489 = withCtrl(x4553) { x4488 } // FltPtToFixPt(x4488,TRUE,_16,_16) //TODO
    val x4490 = withCtrl(x4553) { OpDef(op=FixAbs, inputs=List(x4489)).name("x4490").srcCtx("IndigoStream.scala:71:42:absp") } // FixAbs(x4489)
    val x4491 = withCtrl(x4553) { OpDef(op=FixLt, inputs=List(Const(2.5), x4490)).name("x4491").srcCtx("IndigoStream.scala:72:49") } // FixLt(Const(2.5),x4490)
    val x4492 = withCtrl(x4553) { OpDef(op=BitNot, inputs=List(x4491)).name("x4492").srcCtx("IndigoStream.scala:72:40") } // Not(x4491)
    val x4493 = withCtrl(x4553) { OpDef(op=FixLt, inputs=List(Const(0.5), x4490)).name("x4493").srcCtx("IndigoStream.scala:74:56") } // FixLt(Const(0.5),x4490)
    val x4494 = withCtrl(x4553) { OpDef(op=FixLeq, inputs=List(x4490, Const(2.5))).name("x4494").srcCtx("IndigoStream.scala:74:73") } // FixLeq(x4490,Const(2.5))
    val x4495 = withCtrl(x4553) { OpDef(op=BitAnd, inputs=List(x4493, x4494)).name("x4495").srcCtx("IndigoStream.scala:74:64") } // And(x4493,x4494)
    val x4496 = withCtrl(x4553) { OpDef(op=BitAnd, inputs=List(x4495, x4492)).name("x4496").srcCtx("IndigoStream.scala:72:40") } // And(x4495,x4492)
    val x4497 = withCtrl(x4553) { OpDef(op=BitNot, inputs=List(x4495)).name("x4497").srcCtx("IndigoStream.scala:72:40") } // Not(x4495)
    val x4498 = withCtrl(x4553) { OpDef(op=BitAnd, inputs=List(x4497, x4492)).name("x4498").srcCtx("IndigoStream.scala:72:40") } // And(x4497,x4492)
    val x4499 = withCtrl(x4553) { OpDef(op=FixSra, inputs=List(x4490, Const(2))).name("x4499").srcCtx("IndigoStream.scala:76:52:div4") } // FixRsh(x4490,Const(2))
    val x4500 = withCtrl(x4553) { OpDef(op=FixAdd, inputs=List(x4499, Const(0.375))).name("x4500").srcCtx("IndigoStream.scala:77:58:div4Offset") } // FixAdd(x4499,Const(0.375))
    val x4501 = withCtrl(x4553) { OpDef(op=MuxOp, inputs=List(x4496, x4500, x4490)).name("x4501").srcCtx("IndigoStream.scala:72:40") } // Mux(x4496,x4500,x4490)
    val x4502 = withCtrl(x4553) { OpDef(op=MuxOp, inputs=List(x4491, Const(1.0), x4501)).name("x4502").srcCtx("IndigoStream.scala:72:40") } // Mux(x4491,Const(1),x4501)
    val x4503 = withCtrl(x4553) { OpDef(op=FixNeg, inputs=List(x4502)).name("x4503").srcCtx("IndigoStream.scala:83:52:mabre") } // FixNeg(x4502)
    val x4504 = withCtrl(x4553) { OpDef(op=FixLt, inputs=List(x4489, Const(0.0))).name("x4504").srcCtx("IndigoStream.scala:84:45") } // FixLt(x4489,Const(0))
    val x4505 = withCtrl(x4553) { OpDef(op=MuxOp, inputs=List(x4504, x4503, x4502)).name("x4505").srcCtx("IndigoStream.scala:84:40:re") } // Mux(x4504,x4503,x4502)
    val x4506 = withCtrl(x4553) { x4505 } // FixPtToFltPt(x4505,_24,_8) //TODO
    val x4507 = withCtrl(x4553) { OpDef(op=FltMul, inputs=List(x4486, x4506)).name("x4507").srcCtx("IndigoStream.scala:163:146") } // FltMul(x4486,x4506)
    val x4508 = withCtrl(x4553) { OpDef(op=FltAdd, inputs=List(x4464, x4507)).name("x4508").srcCtx("IndigoStream.scala:163:119:l1") } // FltAdd(x4464,x4507)
    val x4509 = withCtrl(x4553) { x4508 } // FltPtToFixPt(x4508,TRUE,_16,_16) //TODO
    val x4510 = withCtrl(x4553) { OpDef(op=FixAbs, inputs=List(x4509)).name("x4510").srcCtx("IndigoStream.scala:71:42:absp") } // FixAbs(x4509)
    val x4511 = withCtrl(x4553) { OpDef(op=FixLt, inputs=List(Const(2.5), x4510)).name("x4511").srcCtx("IndigoStream.scala:72:49") } // FixLt(Const(2.5),x4510)
    val x4512 = withCtrl(x4553) { OpDef(op=BitNot, inputs=List(x4511)).name("x4512").srcCtx("IndigoStream.scala:72:40") } // Not(x4511)
    val x4513 = withCtrl(x4553) { OpDef(op=FixLt, inputs=List(Const(0.5), x4510)).name("x4513").srcCtx("IndigoStream.scala:74:56") } // FixLt(Const(0.5),x4510)
    val x4514 = withCtrl(x4553) { OpDef(op=FixLeq, inputs=List(x4510, Const(2.5))).name("x4514").srcCtx("IndigoStream.scala:74:73") } // FixLeq(x4510,Const(2.5))
    val x4515 = withCtrl(x4553) { OpDef(op=BitAnd, inputs=List(x4513, x4514)).name("x4515").srcCtx("IndigoStream.scala:74:64") } // And(x4513,x4514)
    val x4516 = withCtrl(x4553) { OpDef(op=BitAnd, inputs=List(x4515, x4512)).name("x4516").srcCtx("IndigoStream.scala:72:40") } // And(x4515,x4512)
    val x4517 = withCtrl(x4553) { OpDef(op=BitNot, inputs=List(x4515)).name("x4517").srcCtx("IndigoStream.scala:72:40") } // Not(x4515)
    val x4518 = withCtrl(x4553) { OpDef(op=BitAnd, inputs=List(x4517, x4512)).name("x4518").srcCtx("IndigoStream.scala:72:40") } // And(x4517,x4512)
    val x4519 = withCtrl(x4553) { OpDef(op=FixSra, inputs=List(x4510, Const(2))).name("x4519").srcCtx("IndigoStream.scala:76:52:div4") } // FixRsh(x4510,Const(2))
    val x4520 = withCtrl(x4553) { OpDef(op=FixAdd, inputs=List(x4519, Const(0.375))).name("x4520").srcCtx("IndigoStream.scala:77:58:div4Offset") } // FixAdd(x4519,Const(0.375))
    val x4521 = withCtrl(x4553) { OpDef(op=MuxOp, inputs=List(x4516, x4520, x4510)).name("x4521").srcCtx("IndigoStream.scala:72:40") } // Mux(x4516,x4520,x4510)
    val x4522 = withCtrl(x4553) { OpDef(op=MuxOp, inputs=List(x4511, Const(1.0), x4521)).name("x4522").srcCtx("IndigoStream.scala:72:40") } // Mux(x4511,Const(1),x4521)
    val x4523 = withCtrl(x4553) { OpDef(op=FixNeg, inputs=List(x4522)).name("x4523").srcCtx("IndigoStream.scala:83:52:mabre") } // FixNeg(x4522)
    val x4524 = withCtrl(x4553) { OpDef(op=FixLt, inputs=List(x4509, Const(0.0))).name("x4524").srcCtx("IndigoStream.scala:84:45") } // FixLt(x4509,Const(0))
    val x4525 = withCtrl(x4553) { OpDef(op=MuxOp, inputs=List(x4524, x4523, x4522)).name("x4525").srcCtx("IndigoStream.scala:84:40:re") } // Mux(x4524,x4523,x4522)
    val x4526 = withCtrl(x4553) { x4525 } // FixPtToFltPt(x4525,_24,_8) //TODO
    val x4527 = withCtrl(x4553) { OpDef(op=FixAdd, inputs=List(b3249, Const(6))).name("x4527").srcCtx("IndigoStream.scala:164:99") } // FixAdd(b3249,Const(6))
    val x4528 = withCtrl(x4553) { LoadBanks(List(x4375_d0_b0), List(x4527)).name("x4528").srcCtx("IndigoStream.scala:164:96") } // RegFileLoad(x4375,List(x4527),b3250)
    val x4529 = withCtrl(x4553) { OpDef(op=FltDiv, inputs=List(x4528, Const(2.0))).name("x4529").srcCtx("IndigoStream.scala:64:45") } // FltDiv(x4528,Const(2))
    val x4530 = withCtrl(x4553) { x4529 } // FltPtToFixPt(x4529,TRUE,_16,_16) //TODO
    val x4531 = withCtrl(x4553) { OpDef(op=FixAbs, inputs=List(x4530)).name("x4531").srcCtx("IndigoStream.scala:71:42:absp") } // FixAbs(x4530)
    val x4532 = withCtrl(x4553) { OpDef(op=FixLt, inputs=List(Const(2.5), x4531)).name("x4532").srcCtx("IndigoStream.scala:72:49") } // FixLt(Const(2.5),x4531)
    val x4533 = withCtrl(x4553) { OpDef(op=BitNot, inputs=List(x4532)).name("x4533").srcCtx("IndigoStream.scala:72:40") } // Not(x4532)
    val x4534 = withCtrl(x4553) { OpDef(op=FixLt, inputs=List(Const(0.5), x4531)).name("x4534").srcCtx("IndigoStream.scala:74:56") } // FixLt(Const(0.5),x4531)
    val x4535 = withCtrl(x4553) { OpDef(op=FixLeq, inputs=List(x4531, Const(2.5))).name("x4535").srcCtx("IndigoStream.scala:74:73") } // FixLeq(x4531,Const(2.5))
    val x4536 = withCtrl(x4553) { OpDef(op=BitAnd, inputs=List(x4534, x4535)).name("x4536").srcCtx("IndigoStream.scala:74:64") } // And(x4534,x4535)
    val x4537 = withCtrl(x4553) { OpDef(op=BitAnd, inputs=List(x4536, x4533)).name("x4537").srcCtx("IndigoStream.scala:72:40") } // And(x4536,x4533)
    val x4538 = withCtrl(x4553) { OpDef(op=BitNot, inputs=List(x4536)).name("x4538").srcCtx("IndigoStream.scala:72:40") } // Not(x4536)
    val x4539 = withCtrl(x4553) { OpDef(op=BitAnd, inputs=List(x4538, x4533)).name("x4539").srcCtx("IndigoStream.scala:72:40") } // And(x4538,x4533)
    val x4540 = withCtrl(x4553) { OpDef(op=FixSra, inputs=List(x4531, Const(2))).name("x4540").srcCtx("IndigoStream.scala:76:52:div4") } // FixRsh(x4531,Const(2))
    val x4541 = withCtrl(x4553) { OpDef(op=FixAdd, inputs=List(x4540, Const(0.375))).name("x4541").srcCtx("IndigoStream.scala:77:58:div4Offset") } // FixAdd(x4540,Const(0.375))
    val x4542 = withCtrl(x4553) { OpDef(op=MuxOp, inputs=List(x4537, x4541, x4531)).name("x4542").srcCtx("IndigoStream.scala:72:40") } // Mux(x4537,x4541,x4531)
    val x4543 = withCtrl(x4553) { OpDef(op=MuxOp, inputs=List(x4532, Const(1.0), x4542)).name("x4543").srcCtx("IndigoStream.scala:72:40") } // Mux(x4532,Const(1),x4542)
    val x4544 = withCtrl(x4553) { OpDef(op=FixNeg, inputs=List(x4543)).name("x4544").srcCtx("IndigoStream.scala:83:52:mabre") } // FixNeg(x4543)
    val x4545 = withCtrl(x4553) { OpDef(op=FixLt, inputs=List(x4530, Const(0.0))).name("x4545").srcCtx("IndigoStream.scala:84:45") } // FixLt(x4530,Const(0))
    val x4546 = withCtrl(x4553) { OpDef(op=MuxOp, inputs=List(x4545, x4544, x4543)).name("x4546").srcCtx("IndigoStream.scala:84:40:re") } // Mux(x4545,x4544,x4543)
    val x4547 = withCtrl(x4553) { x4546 } // FixPtToFltPt(x4546,_24,_8) //TODO
    val x4548 = withCtrl(x4553) { OpDef(op=FltAdd, inputs=List(x4547, Const(1.0))).name("x4548").srcCtx("IndigoStream.scala:64:60") } // FltAdd(x4547,Const(1))
    val x4549 = withCtrl(x4553) { OpDef(op=FltDiv, inputs=List(x4548, Const(2.0))).name("x4549").srcCtx("IndigoStream.scala:64:75") } // FltDiv(x4548,Const(2))
    val x4550 = withCtrl(x4553) { OpDef(op=FltMul, inputs=List(x4526, x4549)).name("x4550").srcCtx("IndigoStream.scala:164:74") } // FltMul(x4526,x4549)
    val x4551 = withCtrl(x4553) { StoreBanks(List(List(x4374_d0_b0), List(x4374_d1_b0)), List(b3249), x4550).name("x4551").srcCtx("IndigoStream.scala:164:57") } // RegFileStore(x4374,List(b3249),x4550,b3250)
    val x4552 = withCtrl(x4553) { StoreBanks(List(List(x4381_d0_b0)), List(b3249), x4508).name("x4552").srcCtx("IndigoStream.scala:165:59") } // RegFileStore(x4381,List(b3249),x4508,b3250)
    val x4555 = withCtrl(x4561) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x4555").srcCtx("IndigoStream.scala:167:51") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x4556 = withCtrl(x4561) { CounterChain(List(x4555)).name("x4556").srcCtx("IndigoStream.scala:167:71") } // CounterChainNew(List(x4555))
    val x4560 = withCtrl(x4561) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4556).name("x4560").srcCtx("IndigoStream.scala:167:71") } // UnrolledForeach(List(Const(true)),x4556,Block(Const(())),List(List(b3369)),List(List(b3370)))
    val b3369 = withCtrl(x4560) { CounterIter(x4555, None).name("b3369") } // b3369
    val b3370 = withCtrl(x4560) { Const(true).name("b3370") } // b3370
    val x4557 = withCtrl(x4560) { LoadBanks(List(x4381_d0_b0), List(b3369)).name("x4557").srcCtx("IndigoStream.scala:168:65") } // ParRegFileLoad(x4381,List(List(b3369)),List(Const(true)))
    val x4558 = withCtrl(x4560) { x4557 } // VectorApply(x4557,0)
    val x4559 = withCtrl(x4560) { StoreBanks(List(List(x4380_d0_b0)), List(b3369), x4558).name("x4559").srcCtx("IndigoStream.scala:168:57") } // ParRegFileStore(x4380,List(List(b3369)),List(x4558),List(Const(true)))
    val x4587 = withCtrl(x4605) { UnitController(style=SeqPipe, level=OuterControl).name("x4587").srcCtx("IndigoStream.scala:177:30") } // UnitPipe(List(Const(true)),Block(Const(())))
    val x4563 = withCtrl(x4587) { Counter(min=Const(0), max=Const(5), step=Const(1), par=1).name("x4563").srcCtx("IndigoStream.scala:178:64") } // CounterNew(Const(0),Const(5),Const(1),Const(1))
    val x4564 = withCtrl(x4587) { CounterChain(List(x4563)).name("x4564").srcCtx("IndigoStream.scala:178:70") } // CounterChainNew(List(x4563))
    val x4586 = withCtrl(x4587) { LoopController(style=MetaPipe, level=OuterControl, cchain=x4564).name("x4586").srcCtx("IndigoStream.scala:178:70") } // UnrolledForeach(List(Const(true)),x4564,Block(Const(())),List(List(b3379)),List(List(b3380)))
    val b3379 = withCtrl(x4586) { CounterIter(x4563, Some(0)).name("b3379") } // b3379
    val b3380 = withCtrl(x4586) { Const(true).name("b3380") } // b3380
    val x4565 = withCtrl(x4586) { Reg(init=Some(0.0)).name("x4565").srcCtx("IndigoStream.scala:180:52:w") } // x4565 = RegNew(Const(0.0))
    isAccum(x4565) = false
    bufferDepthOf(x4565) = 1
    val x4566_d0 = withCtrl(x4586) { Reg(init=Some(0.0)).name("x4566_d0").srcCtx("IndigoStream.scala:182:63") } // x4566 = RegNew(Const(0.0))
    isAccum(x4566_d0) = false
    bufferDepthOf(x4566_d0) = 2
    val x4566_d1 = withCtrl(x4586) { Reg(init=Some(0.0)).name("x4566_d1").srcCtx("IndigoStream.scala:182:63") } // x4566 = RegNew(Const(0.0))
    isAccum(x4566_d1) = true
    bufferDepthOf(x4566_d1) = 1
    val x4567 = withCtrl(x4586) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x4567").srcCtx("IndigoStream.scala:182:91") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x4568 = withCtrl(x4586) { CounterChain(List(x4567)).name("x4568").srcCtx("IndigoStream.scala:184:42") } // CounterChainNew(List(x4567))
    val x4578 = withCtrl(x4586) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4568).name("x4578").srcCtx("IndigoStream.scala:184:42") } // UnrolledReduce(List(b3380),x4568,x4566,Block((x4566) => Const(())),List(List(b3385)),List(List(b3386)))
    val b3385 = withCtrl(x4578) { CounterIter(x4567, None).name("b3385") } // b3385
    val b3386 = withCtrl(x4578) { Const(true).name("b3386") } // b3386
    val x4569 = withCtrl(x4578) { LoadBanks(List(x4374_d0_b0), List(b3385)).name("x4569").srcCtx("IndigoStream.scala:183:53") } // ParRegFileLoad(x4374,List(List(b3385)),List(Const(true)))
    val x4570 = withCtrl(x4578) { x4569 } // VectorApply(x4569,0)
    val x4571 = withCtrl(x4578) { OpDef(op=BitAnd, inputs=List(b3386, b3380)).name("x4571").srcCtx("UnrollingBase.scala:28:66") } // And(b3386,b3380)
    val x4572 = withCtrl(x4578) { LoadBanks(List(x4390_d0_b0), List(b3385, b3379)).name("x4572").srcCtx("IndigoStream.scala:183:67") } // LUTLoad(x4390,List(b3385, b3379),x4571)
    val x4573 = withCtrl(x4578) { OpDef(op=FltMul, inputs=List(x4570, x4572)).name("x4573").srcCtx("IndigoStream.scala:183:57") } // FltMul(x4570,x4572)
    val x4574 = withCtrl(x4578) { ReadMem(x4566_d1).name("x4574").srcCtx("IndigoStream.scala:184:42") } // RegRead(x4566)
    val x4575 = withCtrl(x4578) { OpDef(op=FixEql, inputs=List(b3385, Const(0))).name("x4575").srcCtx("IndigoStream.scala:184:42") } // FixEql(b3385,Const(0))
    val x4576 = withCtrl(x4578) { ReduceAccumOp(op=FltAdd, input=x4573, accum=x4574).name("x4576").srcCtx("IndigoStream.scala:184:44") } // FltAdd(x4573,x4574)
    val x4577_x4566_d0 = withCtrl(x4578) { WriteMem(x4566_d0, x4576).name("x4577_x4566_d0").srcCtx("IndigoStream.scala:184:42") } // RegWrite(x4566,x4576,b3380)
    antiDepsOf(x4577_x4566_d0)=List(x4574)
    val x4577_x4566_d1 = withCtrl(x4578) { WriteMem(x4566_d1, x4576).name("x4577_x4566_d1").srcCtx("IndigoStream.scala:184:42") } // RegWrite(x4566,x4576,b3380)
    antiDepsOf(x4577_x4566_d1)=List(x4574)
    val x4585 = withCtrl(x4586) { UnitController(style=SeqPipe, level=InnerControl).name("x4585").srcCtx("IndigoStream.scala:178:70") } // UnitPipe(List(b3380),Block(Const(())))
    val x4579 = withCtrl(x4585) { ReadMem(x4566_d0).name("x4579").srcCtx("IndigoStream.scala:184:42") } // RegRead(x4566)
    val x4580_x4565 = withCtrl(x4585) { WriteMem(x4565, x4579).name("x4580_x4565").srcCtx("IndigoStream.scala:182:43") } // RegWrite(x4565,x4579,b3380)
    val x4581 = withCtrl(x4585) { ReadMem(x4565).name("x4581").srcCtx("IndigoStream.scala:186:53") } // RegRead(x4565)
    antiDepsOf(x4581)=List(x4580_x4565)
    val x4582 = withCtrl(x4585) { LoadBanks(List(x4391_d0_b0), List(b3379)).name("x4582").srcCtx("IndigoStream.scala:186:65") } // LUTLoad(x4391,List(b3379),b3380)
    val x4583 = withCtrl(x4585) { OpDef(op=FltAdd, inputs=List(x4581, x4582)).name("x4583").srcCtx("IndigoStream.scala:186:55") } // FltAdd(x4581,x4582)
    val x4584 = withCtrl(x4585) { StoreBanks(List(List(x4386_d0_b0)), List(b3379), x4583).name("x4584").srcCtx("IndigoStream.scala:186:51") } // RegFileStore(x4386,List(b3379),x4583,b3380)
    val x4604 = withCtrl(x4605) { UnitController(style=SeqPipe, level=OuterControl).name("x4604").srcCtx("IndigoStream.scala:194:30") } // UnitPipe(List(Const(true)),Block(Const(())))
    val x4588 = withCtrl(x4604) { Reg(init=Some(0.0)).name("x4588").srcCtx("IndigoStream.scala:196:44:w") } // x4588 = RegNew(Const(0.0))
    isAccum(x4588) = false
    bufferDepthOf(x4588) = 1
    val x4589_d0 = withCtrl(x4604) { Reg(init=Some(0.0)).name("x4589_d0").srcCtx("IndigoStream.scala:198:55") } // x4589 = RegNew(Const(0.0))
    isAccum(x4589_d0) = false
    bufferDepthOf(x4589_d0) = 1
    val x4589_d1 = withCtrl(x4604) { Reg(init=Some(0.0)).name("x4589_d1").srcCtx("IndigoStream.scala:198:55") } // x4589 = RegNew(Const(0.0))
    isAccum(x4589_d1) = true
    bufferDepthOf(x4589_d1) = 1
    val x4590 = withCtrl(x4604) { Counter(min=Const(0), max=Const(5), step=Const(1), par=1).name("x4590").srcCtx("IndigoStream.scala:198:90") } // CounterNew(Const(0),Const(5),Const(1),Const(1))
    val x4591 = withCtrl(x4604) { CounterChain(List(x4590)).name("x4591").srcCtx("IndigoStream.scala:200:34") } // CounterChainNew(List(x4590))
    val x4598 = withCtrl(x4604) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4591).name("x4598").srcCtx("IndigoStream.scala:200:34") } // UnrolledReduce(List(Const(true)),x4591,x4589,Block((x4589) => Const(())),List(List(b3410)),List(List(b3411)))
    val b3410 = withCtrl(x4598) { CounterIter(x4590, None).name("b3410") } // b3410
    val b3411 = withCtrl(x4598) { Const(true).name("b3411") } // b3411
    val x4592 = withCtrl(x4598) { LoadBanks(List(x4386_d0_b0), List(b3410)).name("x4592").srcCtx("IndigoStream.scala:199:47") } // ParRegFileLoad(x4386,List(List(b3410)),List(Const(true)))
    val x4593 = withCtrl(x4598) { x4592 } // VectorApply(x4592,0)
    val x4594 = withCtrl(x4598) { ReadMem(x4589_d1).name("x4594").srcCtx("IndigoStream.scala:200:34") } // RegRead(x4589)
    val x4595 = withCtrl(x4598) { OpDef(op=FixEql, inputs=List(b3410, Const(0))).name("x4595").srcCtx("IndigoStream.scala:200:34") } // FixEql(b3410,Const(0))
    val x4596 = withCtrl(x4598) { ReduceAccumOp(op=FltAdd, input=x4593, accum=x4594).name("x4596").srcCtx("IndigoStream.scala:200:38") } // FltAdd(x4593,x4594)
    val x4597_x4589_d0 = withCtrl(x4598) { WriteMem(x4589_d0, x4596).name("x4597_x4589_d0").srcCtx("IndigoStream.scala:200:34") } // RegWrite(x4589,x4596,Const(true))
    antiDepsOf(x4597_x4589_d0)=List(x4594)
    val x4597_x4589_d1 = withCtrl(x4598) { WriteMem(x4589_d1, x4596).name("x4597_x4589_d1").srcCtx("IndigoStream.scala:200:34") } // RegWrite(x4589,x4596,Const(true))
    antiDepsOf(x4597_x4589_d1)=List(x4594)
    val x4603 = withCtrl(x4604) { UnitController(style=SeqPipe, level=InnerControl).name("x4603").srcCtx("IndigoStream.scala:194:30") } // UnitPipe(List(Const(true)),Block(Const(())))
    val x4599 = withCtrl(x4603) { ReadMem(x4589_d0).name("x4599").srcCtx("IndigoStream.scala:200:34") } // RegRead(x4589)
    val x4600_x4588 = withCtrl(x4603) { WriteMem(x4588, x4599).name("x4600_x4588").srcCtx("IndigoStream.scala:198:35") } // RegWrite(x4588,x4599,Const(true))
    val x4601 = withCtrl(x4603) { ReadMem(x4588).name("x4601").srcCtx("IndigoStream.scala:202:47") } // RegRead(x4588)
    antiDepsOf(x4601)=List(x4600_x4588)
    val x4602_x4602_x4371 = withCtrl(x4603) { WriteMem(x4371, x4601).name("x4602_x4602_x4371").srcCtx("IndigoStream.scala:202:44") } // StreamWrite(x4371,x4601,Const(true))
    
  }
}
