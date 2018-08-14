import pir._
import pir.node._
import arch._
import prism.enums._

object LSTMTiled extends PIRApp {
  def main(implicit design:PIRDesign) = {
    import design.pirmeta._
    val x6220 = withCtrl(design.top.topController) { DRAM(dims=List(Const(8), Const(128))).name("x6220").srcCtx("LSTMTiled.scala:21:23:dout") } // x6220 = DRAMNew(ArrayBuffer(Const(8), Const(128)),Const(0))
    val x6221 = withCtrl(design.top.topController) { DRAM(dims=List(Const(8), Const(128))).name("x6221").srcCtx("DataGenerator.scala:156:20:xInit") } // x6221 = DRAMNew(ArrayBuffer(Const(8), Const(128)),Const(0))
    val x6228 = withCtrl(design.top.topController) { DRAM(dims=List(Const(2), Const(128))).name("x6228").srcCtx("DataGenerator.scala:156:20:cInit") } // x6228 = DRAMNew(ArrayBuffer(Const(2), Const(128)),Const(0))
    val x6235 = withCtrl(design.top.topController) { DRAM(dims=List(Const(2), Const(128))).name("x6235").srcCtx("DataGenerator.scala:156:20:hInit") } // x6235 = DRAMNew(ArrayBuffer(Const(2), Const(128)),Const(0))
    val x6242 = withCtrl(design.top.topController) { DRAM(dims=List(Const(2), Const(1), Const(4), Const(128), Const(128))).name("x6242").srcCtx("DataGenerator.scala:198:20:wxInit") } // x6242 = DRAMNew(ArrayBuffer(Const(2), Const(1), Const(4), Const(128), Const(128)),Const(0))
    val x6258 = withCtrl(design.top.topController) { DRAM(dims=List(Const(2), Const(1), Const(4), Const(128), Const(128))).name("x6258").srcCtx("DataGenerator.scala:198:20:whInit") } // x6258 = DRAMNew(ArrayBuffer(Const(2), Const(1), Const(4), Const(128), Const(128)),Const(0))
    val x6274 = withCtrl(design.top.topController) { DRAM(dims=List(Const(2), Const(4), Const(1), Const(128))).name("x6274").srcCtx("DataGenerator.scala:182:20:bInit") } // x6274 = DRAMNew(ArrayBuffer(Const(2), Const(4), Const(1), Const(128)),Const(0))
    val x6720 = withCtrl(design.top.topController) { UnitController(style=SeqPipe, level=OuterControl).name("x6720").srcCtx("LSTMTiled.scala:31:11") } // Hwblock(Block(Const(())),false)
    val x6287_d0_b0 = withCtrl(x6720) { SRAM(size=1024, banking=Strided(banks=16, stride=1)).name("x6287_d0_b0").srcCtx("DataGenerator.scala:43:21:x") } // x6287 = SRAMNew(ArrayBuffer(Const(8), Const(128)))
    isAccum(x6287_d0_b0) = false
    bufferDepthOf(x6287_d0_b0) = 1
    staticDimsOf(x6287_d0_b0) = List(8, 128)
    val x6287_d1_b0 = withCtrl(x6720) { SRAM(size=1024, banking=Strided(banks=16, stride=1)).name("x6287_d1_b0").srcCtx("DataGenerator.scala:43:21:x") } // x6287 = SRAMNew(ArrayBuffer(Const(8), Const(128)))
    isAccum(x6287_d1_b0) = false
    bufferDepthOf(x6287_d1_b0) = 2
    staticDimsOf(x6287_d1_b0) = List(8, 128)
    val x6288 = withCtrl(x6720) { Counter(min=Const(0), max=Const(8), step=Const(1), par=1).name("x6288").srcCtx("DataGenerator.scala:44:8") } // CounterNew(Const(0),Const(8),Const(1),Const(1))
    val x6289 = withCtrl(x6720) { CounterChain(List(x6288)).name("x6289").srcCtx("DataGenerator.scala:44:8") } // CounterChainNew(List(x6288))
    val x6311 = withCtrl(x6720) { LoopController(style=StreamPipe, level=OuterControl, cchain=x6289).name("x6311").srcCtx("DataGenerator.scala:44:8") } // UnrolledForeach(List(Const(true)),x6289,Block(Const(())),List(List(b3654)),List(List(b3655)))
    val b3654 = withCtrl(x6311) { CounterIter(x6288, Some(0)).name("b3654") } // b3654
    val b3655 = withCtrl(x6311) { Const(true).name("b3655") } // b3655
    val b6731 = withCtrl(x6311) { StreamOut(field="offset").name("b6731").srcCtx("DataGenerator.scala:44:8") } // x6290 = StreamOutNew(BurstCmdBus)
    isAccum(b6731) = false
    bufferDepthOf(b6731) = 1
    val b6732 = withCtrl(x6311) { StreamOut(field="size").name("b6732").srcCtx("DataGenerator.scala:44:8") } // x6290 = StreamOutNew(BurstCmdBus)
    isAccum(b6732) = false
    bufferDepthOf(b6732) = 1
    val x6291 = withCtrl(x6311) { StreamIn(field="data").name("x6291").srcCtx("DataGenerator.scala:44:8") } // x6291 = StreamInNew(BurstDataBus())
    isAccum(x6291) = false
    bufferDepthOf(x6291) = 1
    val x6302 = withCtrl(x6311) { UnitController(style=SeqPipe, level=InnerControl).name("x6302").srcCtx("DataGenerator.scala:44:8") } // UnitPipe(List(b3655),Block(x6301))
    val x6292 = withCtrl(x6302) { b3654 } // FixConvert(b3654,TRUE,_32,_0) (Same Type. No op)
    val x6293 = withCtrl(x6302) { OpDef(op=FixSla, inputs=List(x6292, Const(7))).name("x6293").srcCtx("DataGenerator.scala:44:8") } // FixLsh(x6292,Const(7))
    val x6294 = withCtrl(x6302) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x6295 = withCtrl(x6302) { OpDef(op=FixAdd, inputs=List(x6293, x6294)).name("x6295").srcCtx("DataGenerator.scala:44:8") } // FixAdd(x6293,x6294)
    val x6296 = withCtrl(x6302) { OpDef(op=FixSla, inputs=List(x6295, Const(2))).name("x6296").srcCtx("DataGenerator.scala:44:8") } // FixLsh(x6295,Const(2))
    val x6297 = withCtrl(x6302) { x6296 } // FixConvert(x6296,TRUE,_64,_0)
    val x6298 = withCtrl(x6302) { DramAddress(x6221).name("x6298").srcCtx("DataGenerator.scala:44:8") } // GetDRAMAddress(x6221)
    val x6300_x6299 = withCtrl(x6302) { OpDef(op=FixAdd, inputs=List(x6297, x6298)).name("x6300_x6299").srcCtx("DataGenerator.scala:44:8") } // FixAdd(x6297,x6298)
    // x6300 = SimpleStruct(ArrayBuffer((offset,x6299), (size,Const(512)), (isLoad,Const(true))))
    val x6301_b6733_b6731 = withCtrl(x6302) { WriteMem(b6731, x6300_x6299).name("x6301_b6733_b6731").srcCtx("DataGenerator.scala:44:8") } // StreamWrite(x6290,x6300,b3655)
    val x6301_b6734_b6732 = withCtrl(x6302) { WriteMem(b6732, Const(512)).name("x6301_b6734_b6732").srcCtx("DataGenerator.scala:44:8") } // StreamWrite(x6290,x6300,b3655)
    val x6303 = withCtrl(x6311) { FringeDenseLoad(dram=List(x6221), cmdStream=List(b6731, b6732), dataStream=List(x6291)).name("x6303").srcCtx("DataGenerator.scala:44:8") } // FringeDenseLoad(x6221,x6290,x6291)
    val x6304 = withCtrl(x6311) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6304").srcCtx("DataGenerator.scala:44:8") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6305 = withCtrl(x6311) { CounterChain(List(x6304)).name("x6305").srcCtx("DataGenerator.scala:44:8") } // CounterChainNew(List(x6304))
    val x6310 = withCtrl(x6311) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6305).name("x6310").srcCtx("DataGenerator.scala:44:8") } // UnrolledForeach(List(b3655),x6305,Block(Const(())),List(List(b3672)),List(List(b3673)))
    val b3672 = withCtrl(x6310) { CounterIter(x6304, None).name("b3672") } // b3672
    val b3673 = withCtrl(x6310) { Const(true).name("b3673") } // b3673
    val x6306 = withCtrl(x6310) { OpDef(op=BitAnd, inputs=List(b3673, b3655)).name("x6306").srcCtx("UnrollingBase.scala:28:66") } // And(b3673,b3655)
    val x6307_x6307 = withCtrl(x6310) { ReadMem(x6291).name("x6307_x6307").srcCtx("DataGenerator.scala:44:8") } // ParStreamRead(x6291,List(x6306))
    val x6308_x6308 = withCtrl(x6310) { x6307_x6307 } // VectorApply(x6307,0)
    val x6309 = withCtrl(x6310) { StoreBanks(List(List(x6287_d0_b0), List(x6287_d1_b0)), List(b3654, b3672), x6308_x6308).name("x6309").srcCtx("DataGenerator.scala:44:8") } // ParSRAMStore(x6287,List(List(b3654, b3672)),List(x6308),List(x6306))
    val x6312_d0_b0 = withCtrl(x6720) { SRAM(size=256, banking=Strided(banks=16, stride=1)).name("x6312_d0_b0").srcCtx("DataGenerator.scala:43:21:c") } // x6312 = SRAMNew(ArrayBuffer(Const(2), Const(128)))
    isAccum(x6312_d0_b0) = false
    bufferDepthOf(x6312_d0_b0) = 2
    staticDimsOf(x6312_d0_b0) = List(2, 128)
    val x6312_d1_b0 = withCtrl(x6720) { SRAM(size=256, banking=Strided(banks=16, stride=1)).name("x6312_d1_b0").srcCtx("DataGenerator.scala:43:21:c") } // x6312 = SRAMNew(ArrayBuffer(Const(2), Const(128)))
    isAccum(x6312_d1_b0) = false
    bufferDepthOf(x6312_d1_b0) = 2
    staticDimsOf(x6312_d1_b0) = List(2, 128)
    val x6313 = withCtrl(x6720) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x6313").srcCtx("DataGenerator.scala:44:8") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x6314 = withCtrl(x6720) { CounterChain(List(x6313)).name("x6314").srcCtx("DataGenerator.scala:44:8") } // CounterChainNew(List(x6313))
    val x6336 = withCtrl(x6720) { LoopController(style=StreamPipe, level=OuterControl, cchain=x6314).name("x6336").srcCtx("DataGenerator.scala:44:8") } // UnrolledForeach(List(Const(true)),x6314,Block(Const(())),List(List(b3683)),List(List(b3684)))
    val b3683 = withCtrl(x6336) { CounterIter(x6313, Some(0)).name("b3683") } // b3683
    val b3684 = withCtrl(x6336) { Const(true).name("b3684") } // b3684
    val b6735 = withCtrl(x6336) { StreamOut(field="offset").name("b6735").srcCtx("DataGenerator.scala:44:8") } // x6315 = StreamOutNew(BurstCmdBus)
    isAccum(b6735) = false
    bufferDepthOf(b6735) = 1
    val b6736 = withCtrl(x6336) { StreamOut(field="size").name("b6736").srcCtx("DataGenerator.scala:44:8") } // x6315 = StreamOutNew(BurstCmdBus)
    isAccum(b6736) = false
    bufferDepthOf(b6736) = 1
    val x6316 = withCtrl(x6336) { StreamIn(field="data").name("x6316").srcCtx("DataGenerator.scala:44:8") } // x6316 = StreamInNew(BurstDataBus())
    isAccum(x6316) = false
    bufferDepthOf(x6316) = 1
    val x6327 = withCtrl(x6336) { UnitController(style=SeqPipe, level=InnerControl).name("x6327").srcCtx("DataGenerator.scala:44:8") } // UnitPipe(List(b3684),Block(x6326))
    val x6317 = withCtrl(x6327) { b3683 } // FixConvert(b3683,TRUE,_32,_0) (Same Type. No op)
    val x6318 = withCtrl(x6327) { OpDef(op=FixSla, inputs=List(x6317, Const(7))).name("x6318").srcCtx("DataGenerator.scala:44:8") } // FixLsh(x6317,Const(7))
    val x6319 = withCtrl(x6327) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x6320 = withCtrl(x6327) { OpDef(op=FixAdd, inputs=List(x6318, x6319)).name("x6320").srcCtx("DataGenerator.scala:44:8") } // FixAdd(x6318,x6319)
    val x6321 = withCtrl(x6327) { OpDef(op=FixSla, inputs=List(x6320, Const(2))).name("x6321").srcCtx("DataGenerator.scala:44:8") } // FixLsh(x6320,Const(2))
    val x6322 = withCtrl(x6327) { x6321 } // FixConvert(x6321,TRUE,_64,_0)
    val x6323 = withCtrl(x6327) { DramAddress(x6228).name("x6323").srcCtx("DataGenerator.scala:44:8") } // GetDRAMAddress(x6228)
    val x6325_x6324 = withCtrl(x6327) { OpDef(op=FixAdd, inputs=List(x6322, x6323)).name("x6325_x6324").srcCtx("DataGenerator.scala:44:8") } // FixAdd(x6322,x6323)
    // x6325 = SimpleStruct(ArrayBuffer((offset,x6324), (size,Const(512)), (isLoad,Const(true))))
    val x6326_b6737_b6735 = withCtrl(x6327) { WriteMem(b6735, x6325_x6324).name("x6326_b6737_b6735").srcCtx("DataGenerator.scala:44:8") } // StreamWrite(x6315,x6325,b3684)
    val x6326_b6738_b6736 = withCtrl(x6327) { WriteMem(b6736, Const(512)).name("x6326_b6738_b6736").srcCtx("DataGenerator.scala:44:8") } // StreamWrite(x6315,x6325,b3684)
    val x6328 = withCtrl(x6336) { FringeDenseLoad(dram=List(x6228), cmdStream=List(b6735, b6736), dataStream=List(x6316)).name("x6328").srcCtx("DataGenerator.scala:44:8") } // FringeDenseLoad(x6228,x6315,x6316)
    val x6329 = withCtrl(x6336) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6329").srcCtx("DataGenerator.scala:44:8") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6330 = withCtrl(x6336) { CounterChain(List(x6329)).name("x6330").srcCtx("DataGenerator.scala:44:8") } // CounterChainNew(List(x6329))
    val x6335 = withCtrl(x6336) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6330).name("x6335").srcCtx("DataGenerator.scala:44:8") } // UnrolledForeach(List(b3684),x6330,Block(Const(())),List(List(b3701)),List(List(b3702)))
    val b3701 = withCtrl(x6335) { CounterIter(x6329, None).name("b3701") } // b3701
    val b3702 = withCtrl(x6335) { Const(true).name("b3702") } // b3702
    val x6331 = withCtrl(x6335) { OpDef(op=BitAnd, inputs=List(b3702, b3684)).name("x6331").srcCtx("UnrollingBase.scala:28:66") } // And(b3702,b3684)
    val x6332_x6332 = withCtrl(x6335) { ReadMem(x6316).name("x6332_x6332").srcCtx("DataGenerator.scala:44:8") } // ParStreamRead(x6316,List(x6331))
    val x6333_x6333 = withCtrl(x6335) { x6332_x6332 } // VectorApply(x6332,0)
    val x6334 = withCtrl(x6335) { StoreBanks(List(List(x6312_d0_b0), List(x6312_d1_b0)), List(b3683, b3701), x6333_x6333).name("x6334").srcCtx("DataGenerator.scala:44:8") } // ParSRAMStore(x6312,List(List(b3683, b3701)),List(x6333),List(x6331))
    val x6337_d0_b0 = withCtrl(x6720) { SRAM(size=256, banking=Strided(banks=16, stride=1)).name("x6337_d0_b0").srcCtx("DataGenerator.scala:43:21:h") } // x6337 = SRAMNew(ArrayBuffer(Const(2), Const(128)))
    isAccum(x6337_d0_b0) = false
    bufferDepthOf(x6337_d0_b0) = 2
    staticDimsOf(x6337_d0_b0) = List(2, 128)
    val x6338 = withCtrl(x6720) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x6338").srcCtx("DataGenerator.scala:44:8") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x6339 = withCtrl(x6720) { CounterChain(List(x6338)).name("x6339").srcCtx("DataGenerator.scala:44:8") } // CounterChainNew(List(x6338))
    val x6361 = withCtrl(x6720) { LoopController(style=StreamPipe, level=OuterControl, cchain=x6339).name("x6361").srcCtx("DataGenerator.scala:44:8") } // UnrolledForeach(List(Const(true)),x6339,Block(Const(())),List(List(b3712)),List(List(b3713)))
    val b3712 = withCtrl(x6361) { CounterIter(x6338, Some(0)).name("b3712") } // b3712
    val b3713 = withCtrl(x6361) { Const(true).name("b3713") } // b3713
    val b6739 = withCtrl(x6361) { StreamOut(field="offset").name("b6739").srcCtx("DataGenerator.scala:44:8") } // x6340 = StreamOutNew(BurstCmdBus)
    isAccum(b6739) = false
    bufferDepthOf(b6739) = 1
    val b6740 = withCtrl(x6361) { StreamOut(field="size").name("b6740").srcCtx("DataGenerator.scala:44:8") } // x6340 = StreamOutNew(BurstCmdBus)
    isAccum(b6740) = false
    bufferDepthOf(b6740) = 1
    val x6341 = withCtrl(x6361) { StreamIn(field="data").name("x6341").srcCtx("DataGenerator.scala:44:8") } // x6341 = StreamInNew(BurstDataBus())
    isAccum(x6341) = false
    bufferDepthOf(x6341) = 1
    val x6352 = withCtrl(x6361) { UnitController(style=SeqPipe, level=InnerControl).name("x6352").srcCtx("DataGenerator.scala:44:8") } // UnitPipe(List(b3713),Block(x6351))
    val x6342 = withCtrl(x6352) { b3712 } // FixConvert(b3712,TRUE,_32,_0) (Same Type. No op)
    val x6343 = withCtrl(x6352) { OpDef(op=FixSla, inputs=List(x6342, Const(7))).name("x6343").srcCtx("DataGenerator.scala:44:8") } // FixLsh(x6342,Const(7))
    val x6344 = withCtrl(x6352) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x6345 = withCtrl(x6352) { OpDef(op=FixAdd, inputs=List(x6343, x6344)).name("x6345").srcCtx("DataGenerator.scala:44:8") } // FixAdd(x6343,x6344)
    val x6346 = withCtrl(x6352) { OpDef(op=FixSla, inputs=List(x6345, Const(2))).name("x6346").srcCtx("DataGenerator.scala:44:8") } // FixLsh(x6345,Const(2))
    val x6347 = withCtrl(x6352) { x6346 } // FixConvert(x6346,TRUE,_64,_0)
    val x6348 = withCtrl(x6352) { DramAddress(x6235).name("x6348").srcCtx("DataGenerator.scala:44:8") } // GetDRAMAddress(x6235)
    val x6350_x6349 = withCtrl(x6352) { OpDef(op=FixAdd, inputs=List(x6347, x6348)).name("x6350_x6349").srcCtx("DataGenerator.scala:44:8") } // FixAdd(x6347,x6348)
    // x6350 = SimpleStruct(ArrayBuffer((offset,x6349), (size,Const(512)), (isLoad,Const(true))))
    val x6351_b6741_b6739 = withCtrl(x6352) { WriteMem(b6739, x6350_x6349).name("x6351_b6741_b6739").srcCtx("DataGenerator.scala:44:8") } // StreamWrite(x6340,x6350,b3713)
    val x6351_b6742_b6740 = withCtrl(x6352) { WriteMem(b6740, Const(512)).name("x6351_b6742_b6740").srcCtx("DataGenerator.scala:44:8") } // StreamWrite(x6340,x6350,b3713)
    val x6353 = withCtrl(x6361) { FringeDenseLoad(dram=List(x6235), cmdStream=List(b6739, b6740), dataStream=List(x6341)).name("x6353").srcCtx("DataGenerator.scala:44:8") } // FringeDenseLoad(x6235,x6340,x6341)
    val x6354 = withCtrl(x6361) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6354").srcCtx("DataGenerator.scala:44:8") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6355 = withCtrl(x6361) { CounterChain(List(x6354)).name("x6355").srcCtx("DataGenerator.scala:44:8") } // CounterChainNew(List(x6354))
    val x6360 = withCtrl(x6361) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6355).name("x6360").srcCtx("DataGenerator.scala:44:8") } // UnrolledForeach(List(b3713),x6355,Block(Const(())),List(List(b3730)),List(List(b3731)))
    val b3730 = withCtrl(x6360) { CounterIter(x6354, None).name("b3730") } // b3730
    val b3731 = withCtrl(x6360) { Const(true).name("b3731") } // b3731
    val x6356 = withCtrl(x6360) { OpDef(op=BitAnd, inputs=List(b3731, b3713)).name("x6356").srcCtx("UnrollingBase.scala:28:66") } // And(b3731,b3713)
    val x6357_x6357 = withCtrl(x6360) { ReadMem(x6341).name("x6357_x6357").srcCtx("DataGenerator.scala:44:8") } // ParStreamRead(x6341,List(x6356))
    val x6358_x6358 = withCtrl(x6360) { x6357_x6357 } // VectorApply(x6357,0)
    val x6359 = withCtrl(x6360) { StoreBanks(List(List(x6337_d0_b0)), List(b3712, b3730), x6358_x6358).name("x6359").srcCtx("DataGenerator.scala:44:8") } // ParSRAMStore(x6337,List(List(b3712, b3730)),List(x6358),List(x6356))
    val x6362_d0_b0 = withCtrl(x6720) { SRAM(size=131072, banking=Strided(banks=16, stride=1)).name("x6362_d0_b0").srcCtx("DataGenerator.scala:94:21:wx") } // x6362 = SRAMNew(ArrayBuffer(Const(2), Const(1), Const(4), Const(128), Const(128)))
    isAccum(x6362_d0_b0) = false
    bufferDepthOf(x6362_d0_b0) = 1
    staticDimsOf(x6362_d0_b0) = List(2, 1, 4, 128, 128)
    val x6363 = withCtrl(x6720) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x6363").srcCtx("DataGenerator.scala:95:8") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x6364 = withCtrl(x6720) { Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x6364").srcCtx("DataGenerator.scala:95:8") } // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x6365 = withCtrl(x6720) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x6365").srcCtx("DataGenerator.scala:95:8") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x6366 = withCtrl(x6720) { Counter(min=Const(0), max=Const(128), step=Const(1), par=1).name("x6366").srcCtx("DataGenerator.scala:95:8") } // CounterNew(Const(0),Const(128),Const(1),Const(1))
    val x6367 = withCtrl(x6720) { CounterChain(List(x6363,x6364,x6365,x6366)).name("x6367").srcCtx("DataGenerator.scala:95:8") } // CounterChainNew(List(x6363, x6364, x6365, x6366))
    val x6404 = withCtrl(x6720) { LoopController(style=StreamPipe, level=OuterControl, cchain=x6367).name("x6404").srcCtx("DataGenerator.scala:95:8") } // UnrolledForeach(List(Const(true)),x6367,Block(Const(())),List(List(b3744), List(b3745), List(b3746), List(b3747)),List(List(b3748), List(b3749), List(b3750), List(b3751)))
    val b3744 = withCtrl(x6404) { CounterIter(x6363, Some(0)).name("b3744") } // b3744
    val b3748 = withCtrl(x6404) { Const(true).name("b3748") } // b3748
    val b3745 = withCtrl(x6404) { CounterIter(x6364, Some(0)).name("b3745") } // b3745
    val b3749 = withCtrl(x6404) { Const(true).name("b3749") } // b3749
    val b3746 = withCtrl(x6404) { CounterIter(x6365, Some(0)).name("b3746") } // b3746
    val b3750 = withCtrl(x6404) { Const(true).name("b3750") } // b3750
    val b3747 = withCtrl(x6404) { CounterIter(x6366, Some(0)).name("b3747") } // b3747
    val b3751 = withCtrl(x6404) { Const(true).name("b3751") } // b3751
    val b6743 = withCtrl(x6404) { StreamOut(field="offset").name("b6743").srcCtx("DataGenerator.scala:95:8") } // x6368 = StreamOutNew(BurstCmdBus)
    isAccum(b6743) = false
    bufferDepthOf(b6743) = 1
    val b6744 = withCtrl(x6404) { StreamOut(field="size").name("b6744").srcCtx("DataGenerator.scala:95:8") } // x6368 = StreamOutNew(BurstCmdBus)
    isAccum(b6744) = false
    bufferDepthOf(b6744) = 1
    val x6369 = withCtrl(x6404) { StreamIn(field="data").name("x6369").srcCtx("DataGenerator.scala:95:8") } // x6369 = StreamInNew(BurstDataBus())
    isAccum(x6369) = false
    bufferDepthOf(x6369) = 1
    val x6392 = withCtrl(x6404) { UnitController(style=SeqPipe, level=InnerControl).name("x6392").srcCtx("DataGenerator.scala:95:8") } // UnitPipe(List(b3748, b3749, b3750, b3751),Block(x6391))
    val x6370 = withCtrl(x6392) { b3744 } // FixConvert(b3744,TRUE,_32,_0) (Same Type. No op)
    val x6371 = withCtrl(x6392) { OpDef(op=FixSla, inputs=List(x6370, Const(16))).name("x6371").srcCtx("DataGenerator.scala:95:8") } // FixLsh(x6370,Const(16))
    val x6372 = withCtrl(x6392) { b3745 } // FixConvert(b3745,TRUE,_32,_0) (Same Type. No op)
    val x6373 = withCtrl(x6392) { OpDef(op=FixSla, inputs=List(x6372, Const(16))).name("x6373").srcCtx("DataGenerator.scala:95:8") } // FixLsh(x6372,Const(16))
    val x6374 = withCtrl(x6392) { b3746 } // FixConvert(b3746,TRUE,_32,_0) (Same Type. No op)
    val x6375 = withCtrl(x6392) { OpDef(op=FixSla, inputs=List(x6374, Const(14))).name("x6375").srcCtx("DataGenerator.scala:95:8") } // FixLsh(x6374,Const(14))
    val x6376 = withCtrl(x6392) { b3747 } // FixConvert(b3747,TRUE,_32,_0) (Same Type. No op)
    val x6377 = withCtrl(x6392) { OpDef(op=FixSla, inputs=List(x6376, Const(7))).name("x6377").srcCtx("DataGenerator.scala:95:8") } // FixLsh(x6376,Const(7))
    val x6378 = withCtrl(x6392) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x6379 = withCtrl(x6392) { OpDef(op=FixAdd, inputs=List(x6371, x6373)).name("x6379").srcCtx("DataGenerator.scala:95:8") } // FixAdd(x6371,x6373)
    val x6380 = withCtrl(x6392) { OpDef(op=FixAdd, inputs=List(x6375, x6377)).name("x6380").srcCtx("DataGenerator.scala:95:8") } // FixAdd(x6375,x6377)
    val x6381 = withCtrl(x6392) { OpDef(op=FixAdd, inputs=List(x6379, x6380)).name("x6381").srcCtx("DataGenerator.scala:95:8") } // FixAdd(x6379,x6380)
    val x6382 = withCtrl(x6392) { OpDef(op=FixAdd, inputs=List(x6381, x6378)).name("x6382").srcCtx("DataGenerator.scala:95:8") } // FixAdd(x6381,x6378)
    val x6383 = withCtrl(x6392) { OpDef(op=FixSla, inputs=List(x6382, Const(2))).name("x6383").srcCtx("DataGenerator.scala:95:8") } // FixLsh(x6382,Const(2))
    val x6384 = withCtrl(x6392) { x6383 } // FixConvert(x6383,TRUE,_64,_0)
    val x6385 = withCtrl(x6392) { DramAddress(x6242).name("x6385").srcCtx("DataGenerator.scala:95:8") } // GetDRAMAddress(x6242)
    val x6387_x6386 = withCtrl(x6392) { OpDef(op=FixAdd, inputs=List(x6384, x6385)).name("x6387_x6386").srcCtx("DataGenerator.scala:95:8") } // FixAdd(x6384,x6385)
    // x6387 = SimpleStruct(ArrayBuffer((offset,x6386), (size,Const(512)), (isLoad,Const(true))))
    val x6388 = withCtrl(x6392) { OpDef(op=BitAnd, inputs=List(b3748, b3749)).name("x6388").srcCtx("UnrollingBase.scala:28:66") } // And(b3748,b3749)
    val x6389 = withCtrl(x6392) { OpDef(op=BitAnd, inputs=List(b3750, b3751)).name("x6389").srcCtx("UnrollingBase.scala:28:66") } // And(b3750,b3751)
    val x6390 = withCtrl(x6392) { OpDef(op=BitAnd, inputs=List(x6388, x6389)).name("x6390").srcCtx("UnrollingBase.scala:28:66") } // And(x6388,x6389)
    val x6391_b6745_b6743 = withCtrl(x6392) { WriteMem(b6743, x6387_x6386).name("x6391_b6745_b6743").srcCtx("DataGenerator.scala:95:8") } // StreamWrite(x6368,x6387,x6390)
    val x6391_b6746_b6744 = withCtrl(x6392) { WriteMem(b6744, Const(512)).name("x6391_b6746_b6744").srcCtx("DataGenerator.scala:95:8") } // StreamWrite(x6368,x6387,x6390)
    val x6393 = withCtrl(x6404) { FringeDenseLoad(dram=List(x6242), cmdStream=List(b6743, b6744), dataStream=List(x6369)).name("x6393").srcCtx("DataGenerator.scala:95:8") } // FringeDenseLoad(x6242,x6368,x6369)
    val x6394 = withCtrl(x6404) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6394").srcCtx("DataGenerator.scala:95:8") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6395 = withCtrl(x6404) { CounterChain(List(x6394)).name("x6395").srcCtx("DataGenerator.scala:95:8") } // CounterChainNew(List(x6394))
    val x6403 = withCtrl(x6404) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6395).name("x6403").srcCtx("DataGenerator.scala:95:8") } // UnrolledForeach(List(b3748, b3749, b3750, b3751),x6395,Block(Const(())),List(List(b3780)),List(List(b3781)))
    val b3780 = withCtrl(x6403) { CounterIter(x6394, None).name("b3780") } // b3780
    val b3781 = withCtrl(x6403) { Const(true).name("b3781") } // b3781
    val x6396 = withCtrl(x6403) { OpDef(op=BitAnd, inputs=List(b3781, b3748)).name("x6396").srcCtx("UnrollingBase.scala:28:66") } // And(b3781,b3748)
    val x6397 = withCtrl(x6403) { OpDef(op=BitAnd, inputs=List(b3749, b3750)).name("x6397").srcCtx("UnrollingBase.scala:28:66") } // And(b3749,b3750)
    val x6398 = withCtrl(x6403) { OpDef(op=BitAnd, inputs=List(x6396, x6397)).name("x6398").srcCtx("UnrollingBase.scala:28:66") } // And(x6396,x6397)
    val x6399 = withCtrl(x6403) { OpDef(op=BitAnd, inputs=List(x6398, b3751)).name("x6399").srcCtx("UnrollingBase.scala:28:66") } // And(x6398,b3751)
    val x6400_x6400 = withCtrl(x6403) { ReadMem(x6369).name("x6400_x6400").srcCtx("DataGenerator.scala:95:8") } // ParStreamRead(x6369,List(x6399))
    val x6401_x6401 = withCtrl(x6403) { x6400_x6400 } // VectorApply(x6400,0)
    val x6402 = withCtrl(x6403) { StoreBanks(List(List(x6362_d0_b0)), List(b3744, b3745, b3746, b3747, b3780), x6401_x6401).name("x6402").srcCtx("DataGenerator.scala:95:8") } // ParSRAMStore(x6362,List(List(b3744, b3745, b3746, b3747, b3780)),List(x6401),List(x6399))
    val x6405_d0_b0 = withCtrl(x6720) { SRAM(size=131072, banking=Strided(banks=16, stride=1)).name("x6405_d0_b0").srcCtx("DataGenerator.scala:94:21:wh") } // x6405 = SRAMNew(ArrayBuffer(Const(2), Const(1), Const(4), Const(128), Const(128)))
    isAccum(x6405_d0_b0) = false
    bufferDepthOf(x6405_d0_b0) = 1
    staticDimsOf(x6405_d0_b0) = List(2, 1, 4, 128, 128)
    val x6406 = withCtrl(x6720) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x6406").srcCtx("DataGenerator.scala:95:8") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x6407 = withCtrl(x6720) { Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x6407").srcCtx("DataGenerator.scala:95:8") } // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x6408 = withCtrl(x6720) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x6408").srcCtx("DataGenerator.scala:95:8") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x6409 = withCtrl(x6720) { Counter(min=Const(0), max=Const(128), step=Const(1), par=1).name("x6409").srcCtx("DataGenerator.scala:95:8") } // CounterNew(Const(0),Const(128),Const(1),Const(1))
    val x6410 = withCtrl(x6720) { CounterChain(List(x6406,x6407,x6408,x6409)).name("x6410").srcCtx("DataGenerator.scala:95:8") } // CounterChainNew(List(x6406, x6407, x6408, x6409))
    val x6447 = withCtrl(x6720) { LoopController(style=StreamPipe, level=OuterControl, cchain=x6410).name("x6447").srcCtx("DataGenerator.scala:95:8") } // UnrolledForeach(List(Const(true)),x6410,Block(Const(())),List(List(b3797), List(b3798), List(b3799), List(b3800)),List(List(b3801), List(b3802), List(b3803), List(b3804)))
    val b3797 = withCtrl(x6447) { CounterIter(x6406, Some(0)).name("b3797") } // b3797
    val b3801 = withCtrl(x6447) { Const(true).name("b3801") } // b3801
    val b3798 = withCtrl(x6447) { CounterIter(x6407, Some(0)).name("b3798") } // b3798
    val b3802 = withCtrl(x6447) { Const(true).name("b3802") } // b3802
    val b3799 = withCtrl(x6447) { CounterIter(x6408, Some(0)).name("b3799") } // b3799
    val b3803 = withCtrl(x6447) { Const(true).name("b3803") } // b3803
    val b3800 = withCtrl(x6447) { CounterIter(x6409, Some(0)).name("b3800") } // b3800
    val b3804 = withCtrl(x6447) { Const(true).name("b3804") } // b3804
    val b6747 = withCtrl(x6447) { StreamOut(field="offset").name("b6747").srcCtx("DataGenerator.scala:95:8") } // x6411 = StreamOutNew(BurstCmdBus)
    isAccum(b6747) = false
    bufferDepthOf(b6747) = 1
    val b6748 = withCtrl(x6447) { StreamOut(field="size").name("b6748").srcCtx("DataGenerator.scala:95:8") } // x6411 = StreamOutNew(BurstCmdBus)
    isAccum(b6748) = false
    bufferDepthOf(b6748) = 1
    val x6412 = withCtrl(x6447) { StreamIn(field="data").name("x6412").srcCtx("DataGenerator.scala:95:8") } // x6412 = StreamInNew(BurstDataBus())
    isAccum(x6412) = false
    bufferDepthOf(x6412) = 1
    val x6435 = withCtrl(x6447) { UnitController(style=SeqPipe, level=InnerControl).name("x6435").srcCtx("DataGenerator.scala:95:8") } // UnitPipe(List(b3801, b3802, b3803, b3804),Block(x6434))
    val x6413 = withCtrl(x6435) { b3797 } // FixConvert(b3797,TRUE,_32,_0) (Same Type. No op)
    val x6414 = withCtrl(x6435) { OpDef(op=FixSla, inputs=List(x6413, Const(16))).name("x6414").srcCtx("DataGenerator.scala:95:8") } // FixLsh(x6413,Const(16))
    val x6415 = withCtrl(x6435) { b3798 } // FixConvert(b3798,TRUE,_32,_0) (Same Type. No op)
    val x6416 = withCtrl(x6435) { OpDef(op=FixSla, inputs=List(x6415, Const(16))).name("x6416").srcCtx("DataGenerator.scala:95:8") } // FixLsh(x6415,Const(16))
    val x6417 = withCtrl(x6435) { b3799 } // FixConvert(b3799,TRUE,_32,_0) (Same Type. No op)
    val x6418 = withCtrl(x6435) { OpDef(op=FixSla, inputs=List(x6417, Const(14))).name("x6418").srcCtx("DataGenerator.scala:95:8") } // FixLsh(x6417,Const(14))
    val x6419 = withCtrl(x6435) { b3800 } // FixConvert(b3800,TRUE,_32,_0) (Same Type. No op)
    val x6420 = withCtrl(x6435) { OpDef(op=FixSla, inputs=List(x6419, Const(7))).name("x6420").srcCtx("DataGenerator.scala:95:8") } // FixLsh(x6419,Const(7))
    val x6421 = withCtrl(x6435) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x6422 = withCtrl(x6435) { OpDef(op=FixAdd, inputs=List(x6414, x6416)).name("x6422").srcCtx("DataGenerator.scala:95:8") } // FixAdd(x6414,x6416)
    val x6423 = withCtrl(x6435) { OpDef(op=FixAdd, inputs=List(x6418, x6420)).name("x6423").srcCtx("DataGenerator.scala:95:8") } // FixAdd(x6418,x6420)
    val x6424 = withCtrl(x6435) { OpDef(op=FixAdd, inputs=List(x6422, x6423)).name("x6424").srcCtx("DataGenerator.scala:95:8") } // FixAdd(x6422,x6423)
    val x6425 = withCtrl(x6435) { OpDef(op=FixAdd, inputs=List(x6424, x6421)).name("x6425").srcCtx("DataGenerator.scala:95:8") } // FixAdd(x6424,x6421)
    val x6426 = withCtrl(x6435) { OpDef(op=FixSla, inputs=List(x6425, Const(2))).name("x6426").srcCtx("DataGenerator.scala:95:8") } // FixLsh(x6425,Const(2))
    val x6427 = withCtrl(x6435) { x6426 } // FixConvert(x6426,TRUE,_64,_0)
    val x6428 = withCtrl(x6435) { DramAddress(x6258).name("x6428").srcCtx("DataGenerator.scala:95:8") } // GetDRAMAddress(x6258)
    val x6430_x6429 = withCtrl(x6435) { OpDef(op=FixAdd, inputs=List(x6427, x6428)).name("x6430_x6429").srcCtx("DataGenerator.scala:95:8") } // FixAdd(x6427,x6428)
    // x6430 = SimpleStruct(ArrayBuffer((offset,x6429), (size,Const(512)), (isLoad,Const(true))))
    val x6431 = withCtrl(x6435) { OpDef(op=BitAnd, inputs=List(b3801, b3802)).name("x6431").srcCtx("UnrollingBase.scala:28:66") } // And(b3801,b3802)
    val x6432 = withCtrl(x6435) { OpDef(op=BitAnd, inputs=List(b3803, b3804)).name("x6432").srcCtx("UnrollingBase.scala:28:66") } // And(b3803,b3804)
    val x6433 = withCtrl(x6435) { OpDef(op=BitAnd, inputs=List(x6431, x6432)).name("x6433").srcCtx("UnrollingBase.scala:28:66") } // And(x6431,x6432)
    val x6434_b6749_b6747 = withCtrl(x6435) { WriteMem(b6747, x6430_x6429).name("x6434_b6749_b6747").srcCtx("DataGenerator.scala:95:8") } // StreamWrite(x6411,x6430,x6433)
    val x6434_b6750_b6748 = withCtrl(x6435) { WriteMem(b6748, Const(512)).name("x6434_b6750_b6748").srcCtx("DataGenerator.scala:95:8") } // StreamWrite(x6411,x6430,x6433)
    val x6436 = withCtrl(x6447) { FringeDenseLoad(dram=List(x6258), cmdStream=List(b6747, b6748), dataStream=List(x6412)).name("x6436").srcCtx("DataGenerator.scala:95:8") } // FringeDenseLoad(x6258,x6411,x6412)
    val x6437 = withCtrl(x6447) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6437").srcCtx("DataGenerator.scala:95:8") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6438 = withCtrl(x6447) { CounterChain(List(x6437)).name("x6438").srcCtx("DataGenerator.scala:95:8") } // CounterChainNew(List(x6437))
    val x6446 = withCtrl(x6447) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6438).name("x6446").srcCtx("DataGenerator.scala:95:8") } // UnrolledForeach(List(b3801, b3802, b3803, b3804),x6438,Block(Const(())),List(List(b3833)),List(List(b3834)))
    val b3833 = withCtrl(x6446) { CounterIter(x6437, None).name("b3833") } // b3833
    val b3834 = withCtrl(x6446) { Const(true).name("b3834") } // b3834
    val x6439 = withCtrl(x6446) { OpDef(op=BitAnd, inputs=List(b3834, b3801)).name("x6439").srcCtx("UnrollingBase.scala:28:66") } // And(b3834,b3801)
    val x6440 = withCtrl(x6446) { OpDef(op=BitAnd, inputs=List(b3802, b3803)).name("x6440").srcCtx("UnrollingBase.scala:28:66") } // And(b3802,b3803)
    val x6441 = withCtrl(x6446) { OpDef(op=BitAnd, inputs=List(x6439, x6440)).name("x6441").srcCtx("UnrollingBase.scala:28:66") } // And(x6439,x6440)
    val x6442 = withCtrl(x6446) { OpDef(op=BitAnd, inputs=List(x6441, b3804)).name("x6442").srcCtx("UnrollingBase.scala:28:66") } // And(x6441,b3804)
    val x6443_x6443 = withCtrl(x6446) { ReadMem(x6412).name("x6443_x6443").srcCtx("DataGenerator.scala:95:8") } // ParStreamRead(x6412,List(x6442))
    val x6444_x6444 = withCtrl(x6446) { x6443_x6443 } // VectorApply(x6443,0)
    val x6445 = withCtrl(x6446) { StoreBanks(List(List(x6405_d0_b0)), List(b3797, b3798, b3799, b3800, b3833), x6444_x6444).name("x6445").srcCtx("DataGenerator.scala:95:8") } // ParSRAMStore(x6405,List(List(b3797, b3798, b3799, b3800, b3833)),List(x6444),List(x6442))
    val x6448_d0_b0 = withCtrl(x6720) { SRAM(size=1024, banking=Strided(banks=16, stride=1)).name("x6448_d0_b0").srcCtx("DataGenerator.scala:76:21:b") } // x6448 = SRAMNew(ArrayBuffer(Const(2), Const(1), Const(4), Const(128)))
    isAccum(x6448_d0_b0) = false
    bufferDepthOf(x6448_d0_b0) = 1
    staticDimsOf(x6448_d0_b0) = List(2, 1, 4, 128)
    val x6449 = withCtrl(x6720) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x6449").srcCtx("DataGenerator.scala:77:8") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x6450 = withCtrl(x6720) { Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x6450").srcCtx("DataGenerator.scala:77:8") } // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x6451 = withCtrl(x6720) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x6451").srcCtx("DataGenerator.scala:77:8") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x6452 = withCtrl(x6720) { CounterChain(List(x6449,x6450,x6451)).name("x6452").srcCtx("DataGenerator.scala:77:8") } // CounterChainNew(List(x6449, x6450, x6451))
    val x6484 = withCtrl(x6720) { LoopController(style=StreamPipe, level=OuterControl, cchain=x6452).name("x6484").srcCtx("DataGenerator.scala:77:8") } // UnrolledForeach(List(Const(true)),x6452,Block(Const(())),List(List(b3849), List(b3850), List(b3851)),List(List(b3852), List(b3853), List(b3854)))
    val b3849 = withCtrl(x6484) { CounterIter(x6449, Some(0)).name("b3849") } // b3849
    val b3852 = withCtrl(x6484) { Const(true).name("b3852") } // b3852
    val b3850 = withCtrl(x6484) { CounterIter(x6450, Some(0)).name("b3850") } // b3850
    val b3853 = withCtrl(x6484) { Const(true).name("b3853") } // b3853
    val b3851 = withCtrl(x6484) { CounterIter(x6451, Some(0)).name("b3851") } // b3851
    val b3854 = withCtrl(x6484) { Const(true).name("b3854") } // b3854
    val b6751 = withCtrl(x6484) { StreamOut(field="offset").name("b6751").srcCtx("DataGenerator.scala:77:8") } // x6453 = StreamOutNew(BurstCmdBus)
    isAccum(b6751) = false
    bufferDepthOf(b6751) = 1
    val b6752 = withCtrl(x6484) { StreamOut(field="size").name("b6752").srcCtx("DataGenerator.scala:77:8") } // x6453 = StreamOutNew(BurstCmdBus)
    isAccum(b6752) = false
    bufferDepthOf(b6752) = 1
    val x6454 = withCtrl(x6484) { StreamIn(field="data").name("x6454").srcCtx("DataGenerator.scala:77:8") } // x6454 = StreamInNew(BurstDataBus())
    isAccum(x6454) = false
    bufferDepthOf(x6454) = 1
    val x6473 = withCtrl(x6484) { UnitController(style=SeqPipe, level=InnerControl).name("x6473").srcCtx("DataGenerator.scala:77:8") } // UnitPipe(List(b3852, b3853, b3854),Block(x6472))
    val x6455 = withCtrl(x6473) { b3849 } // FixConvert(b3849,TRUE,_32,_0) (Same Type. No op)
    val x6456 = withCtrl(x6473) { OpDef(op=FixSla, inputs=List(x6455, Const(9))).name("x6456").srcCtx("DataGenerator.scala:77:8") } // FixLsh(x6455,Const(9))
    val x6457 = withCtrl(x6473) { b3850 } // FixConvert(b3850,TRUE,_32,_0) (Same Type. No op)
    val x6458 = withCtrl(x6473) { OpDef(op=FixSla, inputs=List(x6457, Const(7))).name("x6458").srcCtx("DataGenerator.scala:77:8") } // FixLsh(x6457,Const(7))
    val x6459 = withCtrl(x6473) { b3851 } // FixConvert(b3851,TRUE,_32,_0) (Same Type. No op)
    val x6460 = withCtrl(x6473) { OpDef(op=FixSla, inputs=List(x6459, Const(7))).name("x6460").srcCtx("DataGenerator.scala:77:8") } // FixLsh(x6459,Const(7))
    val x6461 = withCtrl(x6473) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x6462 = withCtrl(x6473) { OpDef(op=FixAdd, inputs=List(x6456, x6458)).name("x6462").srcCtx("DataGenerator.scala:77:8") } // FixAdd(x6456,x6458)
    val x6463 = withCtrl(x6473) { OpDef(op=FixAdd, inputs=List(x6460, x6461)).name("x6463").srcCtx("DataGenerator.scala:77:8") } // FixAdd(x6460,x6461)
    val x6464 = withCtrl(x6473) { OpDef(op=FixAdd, inputs=List(x6462, x6463)).name("x6464").srcCtx("DataGenerator.scala:77:8") } // FixAdd(x6462,x6463)
    val x6465 = withCtrl(x6473) { OpDef(op=FixSla, inputs=List(x6464, Const(2))).name("x6465").srcCtx("DataGenerator.scala:77:8") } // FixLsh(x6464,Const(2))
    val x6466 = withCtrl(x6473) { x6465 } // FixConvert(x6465,TRUE,_64,_0)
    val x6467 = withCtrl(x6473) { DramAddress(x6274).name("x6467").srcCtx("DataGenerator.scala:77:8") } // GetDRAMAddress(x6274)
    val x6469_x6468 = withCtrl(x6473) { OpDef(op=FixAdd, inputs=List(x6466, x6467)).name("x6469_x6468").srcCtx("DataGenerator.scala:77:8") } // FixAdd(x6466,x6467)
    // x6469 = SimpleStruct(ArrayBuffer((offset,x6468), (size,Const(512)), (isLoad,Const(true))))
    val x6470 = withCtrl(x6473) { OpDef(op=BitAnd, inputs=List(b3852, b3853)).name("x6470").srcCtx("UnrollingBase.scala:28:66") } // And(b3852,b3853)
    val x6471 = withCtrl(x6473) { OpDef(op=BitAnd, inputs=List(x6470, b3854)).name("x6471").srcCtx("UnrollingBase.scala:28:66") } // And(x6470,b3854)
    val x6472_b6753_b6751 = withCtrl(x6473) { WriteMem(b6751, x6469_x6468).name("x6472_b6753_b6751").srcCtx("DataGenerator.scala:77:8") } // StreamWrite(x6453,x6469,x6471)
    val x6472_b6754_b6752 = withCtrl(x6473) { WriteMem(b6752, Const(512)).name("x6472_b6754_b6752").srcCtx("DataGenerator.scala:77:8") } // StreamWrite(x6453,x6469,x6471)
    val x6474 = withCtrl(x6484) { FringeDenseLoad(dram=List(x6274), cmdStream=List(b6751, b6752), dataStream=List(x6454)).name("x6474").srcCtx("DataGenerator.scala:77:8") } // FringeDenseLoad(x6274,x6453,x6454)
    val x6475 = withCtrl(x6484) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6475").srcCtx("DataGenerator.scala:77:8") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6476 = withCtrl(x6484) { CounterChain(List(x6475)).name("x6476").srcCtx("DataGenerator.scala:77:8") } // CounterChainNew(List(x6475))
    val x6483 = withCtrl(x6484) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6476).name("x6483").srcCtx("DataGenerator.scala:77:8") } // UnrolledForeach(List(b3852, b3853, b3854),x6476,Block(Const(())),List(List(b3879)),List(List(b3880)))
    val b3879 = withCtrl(x6483) { CounterIter(x6475, None).name("b3879") } // b3879
    val b3880 = withCtrl(x6483) { Const(true).name("b3880") } // b3880
    val x6477 = withCtrl(x6483) { OpDef(op=BitAnd, inputs=List(b3880, b3852)).name("x6477").srcCtx("UnrollingBase.scala:28:66") } // And(b3880,b3852)
    val x6478 = withCtrl(x6483) { OpDef(op=BitAnd, inputs=List(b3853, b3854)).name("x6478").srcCtx("UnrollingBase.scala:28:66") } // And(b3853,b3854)
    val x6479 = withCtrl(x6483) { OpDef(op=BitAnd, inputs=List(x6477, x6478)).name("x6479").srcCtx("UnrollingBase.scala:28:66") } // And(x6477,x6478)
    val x6480_x6480 = withCtrl(x6483) { ReadMem(x6454).name("x6480_x6480").srcCtx("DataGenerator.scala:77:8") } // ParStreamRead(x6454,List(x6479))
    val x6481_x6481 = withCtrl(x6483) { x6480_x6480 } // VectorApply(x6480,0)
    val x6482 = withCtrl(x6483) { StoreBanks(List(List(x6448_d0_b0)), List(b3849, b3850, b3851, b3879), x6481_x6481).name("x6482").srcCtx("DataGenerator.scala:77:8") } // ParSRAMStore(x6448,List(List(b3849, b3850, b3851, b3879)),List(x6481),List(x6479))
    val x6485 = withCtrl(x6720) { Counter(min=Const(0), max=Const(8), step=Const(1), par=1).name("x6485").srcCtx("LSTMTiled.scala:42:28") } // CounterNew(Const(0),Const(8),Const(1),Const(1))
    val x6486 = withCtrl(x6720) { CounterChain(List(x6485)).name("x6486").srcCtx("LSTMTiled.scala:42:41") } // CounterChainNew(List(x6485))
    val x6691 = withCtrl(x6720) { LoopController(style=MetaPipe, level=OuterControl, cchain=x6486).name("x6691").srcCtx("LSTMTiled.scala:42:41") } // UnrolledForeach(List(Const(true)),x6486,Block(Const(())),List(List(b3891)),List(List(b3892)))
    val b3891 = withCtrl(x6691) { CounterIter(x6485, Some(0)).name("b3891") } // b3891
    val b3892 = withCtrl(x6691) { Const(true).name("b3892") } // b3892
    val x6487 = withCtrl(x6691) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x6487").srcCtx("LSTMTiled.scala:43:31") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x6488 = withCtrl(x6691) { CounterChain(List(x6487)).name("x6488").srcCtx("LSTMTiled.scala:43:45") } // CounterChainNew(List(x6487))
    val x6690 = withCtrl(x6691) { LoopController(style=MetaPipe, level=OuterControl, cchain=x6488).name("x6690").srcCtx("LSTMTiled.scala:43:45") } // UnrolledForeach(List(b3892),x6488,Block(Const(())),List(List(b3895)),List(List(b3896)))
    val b3895 = withCtrl(x6690) { CounterIter(x6487, Some(0)).name("b3895") } // b3895
    val b3896 = withCtrl(x6690) { Const(true).name("b3896") } // b3896
    val x6489 = withCtrl(x6690) { FIFO(size=16).name("x6489").srcCtx("LSTMTiled.scala:44:28:hhQ") } // x6489 = FIFONew(Const(16))
    isAccum(x6489) = false
    bufferDepthOf(x6489) = 2
    val x6490 = withCtrl(x6690) { FIFO(size=16).name("x6490").srcCtx("LSTMTiled.scala:45:28:ccQ") } // x6490 = FIFONew(Const(16))
    isAccum(x6490) = false
    bufferDepthOf(x6490) = 2
    val x6491 = withCtrl(x6690) { Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x6491").srcCtx("LSTMTiled.scala:47:37") } // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x6492 = withCtrl(x6690) { CounterChain(List(x6491)).name("x6492").srcCtx("LSTMTiled.scala:47:55") } // CounterChainNew(List(x6491))
    val x6674 = withCtrl(x6690) { LoopController(style=MetaPipe, level=OuterControl, cchain=x6492).name("x6674").srcCtx("LSTMTiled.scala:47:55") } // UnrolledForeach(List(b3896, b3892),x6492,Block(Const(())),List(List(b3901)),List(List(b3902)))
    val b3901 = withCtrl(x6674) { CounterIter(x6491, Some(0)).name("b3901") } // b3901
    val b3902 = withCtrl(x6674) { Const(true).name("b3902") } // b3902
    val x6493_d0_b0 = withCtrl(x6674) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x6493_d0_b0").srcCtx("LSTMTiled.scala:48:36:reduceMem") } // x6493 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x6493_d0_b0) = false
    bufferDepthOf(x6493_d0_b0) = 1
    staticDimsOf(x6493_d0_b0) = List(4, 128)
    val x6493_d1_b0 = withCtrl(x6674) { SRAM(size=512, banking=Strided(banks=1, stride=128)).name("x6493_d1_b0").srcCtx("LSTMTiled.scala:48:36:reduceMem") } // x6493 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x6493_d1_b0) = true
    bufferDepthOf(x6493_d1_b0) = 1
    staticDimsOf(x6493_d1_b0) = List(4, 128)
    val x6494_d0_b0 = withCtrl(x6674) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x6494_d0_b0").srcCtx("LSTMTiled.scala:49:34:foldMem") } // x6494 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x6494_d0_b0) = false
    bufferDepthOf(x6494_d0_b0) = 1
    staticDimsOf(x6494_d0_b0) = List(4, 128)
    val x6495_d0_b0 = withCtrl(x6674) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x6495_d0_b0").srcCtx("LSTMTiled.scala:50:31:cbuf") } // x6495 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x6495_d0_b0) = false
    bufferDepthOf(x6495_d0_b0) = 1
    staticDimsOf(x6495_d0_b0) = List(128)
    val x6495_d1_b0 = withCtrl(x6674) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x6495_d1_b0").srcCtx("LSTMTiled.scala:50:31:cbuf") } // x6495 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x6495_d1_b0) = true
    bufferDepthOf(x6495_d1_b0) = 1
    staticDimsOf(x6495_d1_b0) = List(128)
    val x6496_d0_b0 = withCtrl(x6674) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x6496_d0_b0").srcCtx("LSTMTiled.scala:51:31:hbuf") } // x6496 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x6496_d0_b0) = false
    bufferDepthOf(x6496_d0_b0) = 1
    staticDimsOf(x6496_d0_b0) = List(128)
    val x6496_d1_b0 = withCtrl(x6674) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x6496_d1_b0").srcCtx("LSTMTiled.scala:51:31:hbuf") } // x6496 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x6496_d1_b0) = true
    bufferDepthOf(x6496_d1_b0) = 1
    staticDimsOf(x6496_d1_b0) = List(128)
    val x6497 = withCtrl(x6674) { Counter(min=Const(0), max=Const(128), step=Const(1), par=1).name("x6497").srcCtx("LSTMTiled.scala:53:51") } // CounterNew(Const(0),Const(128),Const(1),Const(1))
    val x6498 = withCtrl(x6674) { CounterChain(List(x6497)).name("x6498").srcCtx("LSTMTiled.scala:69:14") } // CounterChainNew(List(x6497))
    val x6551 = withCtrl(x6674) { LoopController(style=MetaPipe, level=OuterControl, cchain=x6498).name("x6551").srcCtx("LSTMTiled.scala:69:14") } // UnrolledReduce(List(b3902, b3896, b3892),x6498,x6493,Block((x6493) => Const(())),List(List(b3912)),List(List(b3913)))
    val b3912 = withCtrl(x6551) { CounterIter(x6497, Some(0)).name("b3912") } // b3912
    val b3913 = withCtrl(x6551) { Const(true).name("b3913") } // b3913
    val x6499_d0_b0 = withCtrl(x6551) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x6499_d0_b0").srcCtx("LSTMTiled.scala:54:31:re") } // x6499 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x6499_d0_b0) = false
    bufferDepthOf(x6499_d0_b0) = 2
    staticDimsOf(x6499_d0_b0) = List(4, 128)
    val x6500 = withCtrl(x6551) { Reg(init=Some(0.0)).name("x6500").srcCtx("LSTMTiled.scala:69:14") } // x6500 = RegNew(Const(0))
    isAccum(x6500) = false
    bufferDepthOf(x6500) = 2
    val x6501 = withCtrl(x6551) { Reg(init=Some(0.0)).name("x6501").srcCtx("LSTMTiled.scala:69:14") } // x6501 = RegNew(Const(0))
    isAccum(x6501) = false
    bufferDepthOf(x6501) = 2
    val x6509 = withCtrl(x6551) { UnitController(style=SeqPipe, level=InnerControl).name("x6509").srcCtx("LSTMTiled.scala:69:14") } // UnitPipe(List(b3913, b3902, b3896, b3892),Block(Const(())))
    val x6502 = withCtrl(x6509) { OpDef(op=BitAnd, inputs=List(b3913, b3902)).name("x6502").srcCtx("UnrollingBase.scala:28:66") } // And(b3913,b3902)
    val x6503 = withCtrl(x6509) { OpDef(op=BitAnd, inputs=List(b3896, b3892)).name("x6503").srcCtx("UnrollingBase.scala:28:66") } // And(b3896,b3892)
    val x6504 = withCtrl(x6509) { OpDef(op=BitAnd, inputs=List(x6502, x6503)).name("x6504").srcCtx("UnrollingBase.scala:28:66") } // And(x6502,x6503)
    val x6505 = withCtrl(x6509) { LoadBanks(List(x6287_d1_b0), List(b3891, b3912)).name("x6505").srcCtx("LSTMTiled.scala:55:27:xEle") } // SRAMLoad(x6287,ArrayBuffer(Const(8), Const(128)),List(b3891, b3912),Const(0),x6504)
    val x6506 = withCtrl(x6509) { LoadBanks(List(x6337_d0_b0), List(b3895, b3912)).name("x6506").srcCtx("LSTMTiled.scala:56:27:hEle") } // SRAMLoad(x6337,ArrayBuffer(Const(2), Const(128)),List(b3895, b3912),Const(0),x6504)
    val x6507_x6500 = withCtrl(x6509) { WriteMem(x6500, x6505).name("x6507_x6500").srcCtx("LSTMTiled.scala:69:14") } // RegWrite(x6500,x6505,x6504)
    val x6508_x6501 = withCtrl(x6509) { WriteMem(x6501, x6506).name("x6508_x6501").srcCtx("LSTMTiled.scala:69:14") } // RegWrite(x6501,x6506,x6504)
    val x6510 = withCtrl(x6551) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x6510").srcCtx("LSTMTiled.scala:58:31") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x6511 = withCtrl(x6551) { CounterChain(List(x6510)).name("x6511").srcCtx("LSTMTiled.scala:58:37") } // CounterChainNew(List(x6510))
    val x6530 = withCtrl(x6551) { LoopController(style=MetaPipe, level=OuterControl, cchain=x6511).name("x6530").srcCtx("LSTMTiled.scala:58:37") } // UnrolledForeach(List(b3913, b3902, b3896, b3892),x6511,Block(Const(())),List(List(b3927)),List(List(b3928)))
    val b3927 = withCtrl(x6530) { CounterIter(x6510, Some(0)).name("b3927") } // b3927
    val b3928 = withCtrl(x6530) { Const(true).name("b3928") } // b3928
    val x6512 = withCtrl(x6530) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6512").srcCtx("LSTMTiled.scala:59:45") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6513 = withCtrl(x6530) { CounterChain(List(x6512)).name("x6513").srcCtx("LSTMTiled.scala:59:59") } // CounterChainNew(List(x6512))
    val x6529 = withCtrl(x6530) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6513).name("x6529").srcCtx("LSTMTiled.scala:59:59") } // UnrolledForeach(List(b3928, b3913, b3902, b3896, b3892),x6513,Block(Const(())),List(List(b3931)),List(List(b3932)))
    val b3931 = withCtrl(x6529) { CounterIter(x6512, None).name("b3931") } // b3931
    val b3932 = withCtrl(x6529) { Const(true).name("b3932") } // b3932
    val x6514 = withCtrl(x6529) { OpDef(op=BitAnd, inputs=List(b3932, b3928)).name("x6514").srcCtx("UnrollingBase.scala:28:66") } // And(b3932,b3928)
    val x6515 = withCtrl(x6529) { OpDef(op=BitAnd, inputs=List(b3913, b3902)).name("x6515").srcCtx("UnrollingBase.scala:28:66") } // And(b3913,b3902)
    val x6516 = withCtrl(x6529) { OpDef(op=BitAnd, inputs=List(b3896, b3892)).name("x6516").srcCtx("UnrollingBase.scala:28:66") } // And(b3896,b3892)
    val x6517 = withCtrl(x6529) { OpDef(op=BitAnd, inputs=List(x6514, x6515)).name("x6517").srcCtx("UnrollingBase.scala:28:66") } // And(x6514,x6515)
    val x6518 = withCtrl(x6529) { OpDef(op=BitAnd, inputs=List(x6517, x6516)).name("x6518").srcCtx("UnrollingBase.scala:28:66") } // And(x6517,x6516)
    val x6519 = withCtrl(x6529) { LoadBanks(List(x6362_d0_b0), List(b3895, b3901, b3927, b3912, b3931)).name("x6519").srcCtx("LSTMTiled.scala:60:33:wxEle") } // ParSRAMLoad(x6362,List(List(b3895, b3901, b3927, b3912, b3931)),List(x6518))
    val x6520 = withCtrl(x6529) { x6519 } // VectorApply(x6519,0)
    val x6521 = withCtrl(x6529) { LoadBanks(List(x6405_d0_b0), List(b3895, b3901, b3927, b3912, b3931)).name("x6521").srcCtx("LSTMTiled.scala:61:33:whEle") } // ParSRAMLoad(x6405,List(List(b3895, b3901, b3927, b3912, b3931)),List(x6518))
    val x6522 = withCtrl(x6529) { x6521 } // VectorApply(x6521,0)
    val x6523 = withCtrl(x6529) { ReadMem(x6500).name("x6523").srcCtx("LSTMTiled.scala:69:14") } // RegRead(x6500)
    val x6524 = withCtrl(x6529) { OpDef(op=FixMul, inputs=List(x6520, x6523)).name("x6524").srcCtx("LSTMTiled.scala:62:35:reX") } // FixMul(x6520,x6523)
    val x6525 = withCtrl(x6529) { ReadMem(x6501).name("x6525").srcCtx("LSTMTiled.scala:69:14") } // RegRead(x6501)
    val x6526 = withCtrl(x6529) { OpDef(op=FixMul, inputs=List(x6522, x6525)).name("x6526").srcCtx("LSTMTiled.scala:63:35:reH") } // FixMul(x6522,x6525)
    val x6527 = withCtrl(x6529) { OpDef(op=FixAdd, inputs=List(x6524, x6526)).name("x6527").srcCtx("LSTMTiled.scala:64:42") } // FixAdd(x6524,x6526)
    val x6528 = withCtrl(x6529) { StoreBanks(List(List(x6499_d0_b0)), List(b3927, b3931), x6527).name("x6528").srcCtx("LSTMTiled.scala:64:36") } // ParSRAMStore(x6499,List(List(b3927, b3931)),List(x6527),List(x6518))
    val x6531 = withCtrl(x6551) { Counter(min=Const(0), max=Const(128), step=Const(1), par=1).name("x6531").srcCtx("LSTMTiled.scala:69:14") } // CounterNew(Const(0),Const(128),Const(1),Const(1))
    val x6532 = withCtrl(x6551) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x6532").srcCtx("LSTMTiled.scala:69:14") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x6533 = withCtrl(x6551) { CounterChain(List(x6532,x6531)).name("x6533").srcCtx("LSTMTiled.scala:69:14") } // CounterChainNew(ArrayBuffer(x6532, x6531))
    val x6550 = withCtrl(x6551) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6533).name("x6550").srcCtx("LSTMTiled.scala:69:14") } // UnrolledForeach(List(),x6533,Block(Const(())),ArrayBuffer(List(b3950), List(b3951)),ArrayBuffer(List(b3952), List(b3953)))
    val b3950 = withCtrl(x6550) { CounterIter(x6532, Some(0)).name("b3950") } // b3950
    val b3952 = withCtrl(x6550) { Const(true).name("b3952") } // b3952
    val b3951 = withCtrl(x6550) { CounterIter(x6531, None).name("b3951") } // b3951
    val b3953 = withCtrl(x6550) { Const(true).name("b3953") } // b3953
    val x6534 = withCtrl(x6550) { OpDef(op=BitAnd, inputs=List(b3952, b3953)).name("x6534").srcCtx("UnrollingBase.scala:28:66") } // And(b3952,b3953)
    val x6535 = withCtrl(x6550) { OpDef(op=BitAnd, inputs=List(b3902, b3896)).name("x6535").srcCtx("UnrollingBase.scala:28:66") } // And(b3902,b3896)
    val x6536 = withCtrl(x6550) { OpDef(op=BitAnd, inputs=List(x6534, x6535)).name("x6536").srcCtx("UnrollingBase.scala:28:66") } // And(x6534,x6535)
    val x6537 = withCtrl(x6550) { OpDef(op=BitAnd, inputs=List(x6536, b3892)).name("x6537").srcCtx("UnrollingBase.scala:28:66") } // And(x6536,b3892)
    val x6538 = withCtrl(x6550) { LoadBanks(List(x6499_d0_b0), List(b3950, b3951)).name("x6538").srcCtx("LSTMTiled.scala:69:14") } // ParSRAMLoad(x6499,List(ArrayBuffer(b3950, b3951)),List(x6537))
    val x6539 = withCtrl(x6550) { x6538 } // VectorApply(x6538,0)
    val x6540 = withCtrl(x6550) { LoadBanks(List(x6493_d1_b0), List(b3950, b3951)).name("x6540").srcCtx("LSTMTiled.scala:69:14") } // ParSRAMLoad(x6493,List(ArrayBuffer(b3950, b3951)),List(x6537))
    val x6541 = withCtrl(x6550) { x6540 } // VectorApply(x6540,0)
    val x6542 = withCtrl(x6550) { OpDef(op=BitAnd, inputs=List(b3913, b3902)).name("x6542").srcCtx("LSTMTiled.scala:69:14") } // And(b3913,b3902)
    val x6543 = withCtrl(x6550) { OpDef(op=BitAnd, inputs=List(b3896, b3892)).name("x6543").srcCtx("LSTMTiled.scala:69:14") } // And(b3896,b3892)
    val x6544 = withCtrl(x6550) { OpDef(op=BitAnd, inputs=List(x6542, x6543)).name("x6544").srcCtx("LSTMTiled.scala:69:14") } // And(x6542,x6543)
    val x6545 = withCtrl(x6550) { OpDef(op=BitAnd, inputs=List(x6544, x6537)).name("x6545").srcCtx("LSTMTiled.scala:69:14") } // And(x6544,x6537)
    val x6546 = withCtrl(x6550) { OpDef(op=FixEql, inputs=List(b3912, Const(0))).name("x6546").srcCtx("LSTMTiled.scala:69:14") } // FixEql(b3912,Const(0))
    val x6547 = withCtrl(x6550) { OpDef(op=FixAdd, inputs=List(x6539, x6541)).name("x6547").srcCtx("LSTMTiled.scala:69:16") } // FixAdd(x6539,x6541)
    val x6548 = withCtrl(x6550) { OpDef(op=MuxOp, inputs=List(x6546, x6539, x6547)).name("x6548").srcCtx("LSTMTiled.scala:69:14") } // Mux(x6546,x6539,x6547)
    val x6549 = withCtrl(x6550) { StoreBanks(List(List(x6493_d0_b0), List(x6493_d1_b0)), List(b3950, b3951), x6548).name("x6549").srcCtx("LSTMTiled.scala:69:14") } // ParSRAMStore(x6493,List(ArrayBuffer(b3950, b3951)),List(x6548),List(x6537))
    antiDepsOf(x6549)=List(x6540)
    val x6552 = withCtrl(x6674) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x6552").srcCtx("LSTMTiled.scala:72:34") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x6553 = withCtrl(x6674) { CounterChain(List(x6552)).name("x6553").srcCtx("LSTMTiled.scala:72:47") } // CounterChainNew(List(x6552))
    val x6567 = withCtrl(x6674) { LoopController(style=MetaPipe, level=OuterControl, cchain=x6553).name("x6567").srcCtx("LSTMTiled.scala:72:47") } // UnrolledForeach(List(b3902, b3896, b3892),x6553,Block(Const(())),List(List(b3974)),List(List(b3975)))
    val b3974 = withCtrl(x6567) { CounterIter(x6552, Some(0)).name("b3974") } // b3974
    val b3975 = withCtrl(x6567) { Const(true).name("b3975") } // b3975
    val x6554 = withCtrl(x6567) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6554").srcCtx("LSTMTiled.scala:73:43") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6555 = withCtrl(x6567) { CounterChain(List(x6554)).name("x6555").srcCtx("LSTMTiled.scala:73:57") } // CounterChainNew(List(x6554))
    val x6566 = withCtrl(x6567) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6555).name("x6566").srcCtx("LSTMTiled.scala:73:57") } // UnrolledForeach(List(b3975, b3902, b3896, b3892),x6555,Block(Const(())),List(List(b3978)),List(List(b3979)))
    val b3978 = withCtrl(x6566) { CounterIter(x6554, None).name("b3978") } // b3978
    val b3979 = withCtrl(x6566) { Const(true).name("b3979") } // b3979
    val x6556 = withCtrl(x6566) { OpDef(op=BitAnd, inputs=List(b3979, b3975)).name("x6556").srcCtx("UnrollingBase.scala:28:66") } // And(b3979,b3975)
    val x6557 = withCtrl(x6566) { OpDef(op=BitAnd, inputs=List(b3902, b3896)).name("x6557").srcCtx("UnrollingBase.scala:28:66") } // And(b3902,b3896)
    val x6558 = withCtrl(x6566) { OpDef(op=BitAnd, inputs=List(x6556, x6557)).name("x6558").srcCtx("UnrollingBase.scala:28:66") } // And(x6556,x6557)
    val x6559 = withCtrl(x6566) { OpDef(op=BitAnd, inputs=List(x6558, b3892)).name("x6559").srcCtx("UnrollingBase.scala:28:66") } // And(x6558,b3892)
    val x6560 = withCtrl(x6566) { LoadBanks(List(x6448_d0_b0), List(b3895, b3901, b3974, b3978)).name("x6560").srcCtx("LSTMTiled.scala:75:29:bEle") } // ParSRAMLoad(x6448,List(List(b3895, b3901, b3974, b3978)),List(x6559))
    val x6561 = withCtrl(x6566) { x6560 } // VectorApply(x6560,0)
    val x6562 = withCtrl(x6566) { LoadBanks(List(x6493_d0_b0), List(b3974, b3978)).name("x6562").srcCtx("LSTMTiled.scala:76:50") } // ParSRAMLoad(x6493,List(List(b3974, b3978)),List(x6559))
    val x6563 = withCtrl(x6566) { x6562 } // VectorApply(x6562,0)
    val x6564 = withCtrl(x6566) { OpDef(op=FixAdd, inputs=List(x6563, x6561)).name("x6564").srcCtx("LSTMTiled.scala:76:65") } // FixAdd(x6563,x6561)
    val x6565 = withCtrl(x6566) { StoreBanks(List(List(x6494_d0_b0)), List(b3974, b3978), x6564).name("x6565").srcCtx("LSTMTiled.scala:76:39") } // ParSRAMStore(x6494,List(List(b3974, b3978)),List(x6564),List(x6559))
    val x6568 = withCtrl(x6674) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x6568").srcCtx("LSTMTiled.scala:80:29") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x6569 = withCtrl(x6674) { CounterChain(List(x6568)).name("x6569").srcCtx("LSTMTiled.scala:80:35") } // CounterChainNew(List(x6568))
    val x6661 = withCtrl(x6674) { LoopController(style=MetaPipe, level=OuterControl, cchain=x6569).name("x6661").srcCtx("LSTMTiled.scala:80:35") } // UnrolledForeach(List(b3902, b3896, b3892),x6569,Block(Const(())),List(List(b3994)),List(List(b3995)))
    val b3994 = withCtrl(x6661) { CounterIter(x6568, Some(0)).name("b3994") } // b3994
    val b3995 = withCtrl(x6661) { Const(true).name("b3995") } // b3995
    val x6570 = withCtrl(x6661) { FIFO(size=16).name("x6570").srcCtx("LSTMTiled.scala:81:33:sigQ") } // x6570 = FIFONew(Const(16))
    isAccum(x6570) = false
    bufferDepthOf(x6570) = 2
    val x6571 = withCtrl(x6661) { FIFO(size=16).name("x6571").srcCtx("LSTMTiled.scala:82:33:actQ") } // x6571 = FIFONew(Const(16))
    isAccum(x6571) = false
    bufferDepthOf(x6571) = 2
    val x6572 = withCtrl(x6661) { FIFO(size=16).name("x6572").srcCtx("LSTMTiled.scala:83:36:sigEleQ") } // x6572 = FIFONew(Const(16))
    isAccum(x6572) = false
    bufferDepthOf(x6572) = 2
    val x6573 = withCtrl(x6661) { FIFO(size=16).name("x6573").srcCtx("LSTMTiled.scala:84:41:cNewMultAddQ") } // x6573 = FIFONew(Const(16))
    isAccum(x6573) = false
    bufferDepthOf(x6573) = 2
    val x6574 = withCtrl(x6661) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6574").srcCtx("LSTMTiled.scala:86:43") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6575 = withCtrl(x6661) { CounterChain(List(x6574)).name("x6575").srcCtx("LSTMTiled.scala:86:57") } // CounterChainNew(List(x6574))
    val x6604 = withCtrl(x6661) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6575).name("x6604").srcCtx("LSTMTiled.scala:86:57") } // UnrolledForeach(List(b3995, b3902, b3896, b3892),x6575,Block(Const(())),List(List(b4002)),List(List(b4003)))
    val b4002 = withCtrl(x6604) { CounterIter(x6574, None).name("b4002") } // b4002
    val b4003 = withCtrl(x6604) { Const(true).name("b4003") } // b4003
    val x6576 = withCtrl(x6604) { OpDef(op=BitAnd, inputs=List(b4003, b3995)).name("x6576").srcCtx("UnrollingBase.scala:28:66") } // And(b4003,b3995)
    val x6577 = withCtrl(x6604) { OpDef(op=BitAnd, inputs=List(b3902, b3896)).name("x6577").srcCtx("UnrollingBase.scala:28:66") } // And(b3902,b3896)
    val x6578 = withCtrl(x6604) { OpDef(op=BitAnd, inputs=List(x6576, x6577)).name("x6578").srcCtx("UnrollingBase.scala:28:66") } // And(x6576,x6577)
    val x6579 = withCtrl(x6604) { OpDef(op=BitAnd, inputs=List(x6578, b3892)).name("x6579").srcCtx("UnrollingBase.scala:28:66") } // And(x6578,b3892)
    val x6580 = withCtrl(x6604) { LoadBanks(List(x6312_d1_b0), List(b3895, b4002)).name("x6580").srcCtx("LSTMTiled.scala:87:29:cEle") } // ParSRAMLoad(x6312,List(List(b3895, b4002)),List(x6579))
    val x6581 = withCtrl(x6604) { x6580 } // VectorApply(x6580,0)
    val x6582 = withCtrl(x6604) { LoadBanks(List(x6494_d0_b0), List(b3994, b4002)).name("x6582").srcCtx("LSTMTiled.scala:88:35:pEle") } // ParSRAMLoad(x6494,List(List(b3994, b4002)),List(x6579))
    val x6583 = withCtrl(x6604) { x6582 } // VectorApply(x6582,0)
    val x6584 = withCtrl(x6604) { OpDef(op=FixLt, inputs=List(x6583, Const(-2.5))).name("x6584").srcCtx("NonLinearity.scala:94:12") } // FixLt(x6583,Const(-2.5))
    val x6585 = withCtrl(x6604) { OpDef(op=FixLeq, inputs=List(Const(2.5), x6583)).name("x6585").srcCtx("NonLinearity.scala:94:34") } // FixLeq(Const(2.5),x6583)
    val x6586 = withCtrl(x6604) { OpDef(op=FixMul, inputs=List(Const(0.19999999), x6583)).name("x6586").srcCtx("NonLinearity.scala:94:56") } // FixMul(Const(0.199999988079071044921875),x6583)
    val x6587 = withCtrl(x6604) { OpDef(op=FixAdd, inputs=List(x6586, Const(0.5))).name("x6587").srcCtx("NonLinearity.scala:94:61") } // FixAdd(x6586,Const(0.5))
    val x6588 = withCtrl(x6604) { OpDef(op=MuxOp, inputs=List(x6585, Const(1.0), x6587)).name("x6588").srcCtx("NonLinearity.scala:94:30") } // Mux(x6585,Const(1),x6587)
    val x6589 = withCtrl(x6604) { OpDef(op=MuxOp, inputs=List(x6584, Const(0.0), x6588)).name("x6589").srcCtx("NonLinearity.scala:94:8:sigEle") } // Mux(x6584,Const(0),x6588)
    val x6590 = withCtrl(x6604) { OpDef(op=FixAbs, inputs=List(x6583)).name("x6590").srcCtx("NonLinearity.scala:73:20:absin") } // FixAbs(x6583)
    val x6591 = withCtrl(x6604) { OpDef(op=FixSra, inputs=List(x6590, Const(2))).name("x6591").srcCtx("NonLinearity.scala:74:22:div4") } // FixRsh(x6590,Const(2))
    val x6592 = withCtrl(x6604) { OpDef(op=FixAdd, inputs=List(x6591, Const(0.375))).name("x6592").srcCtx("NonLinearity.scala:75:19:li") } // FixAdd(x6591,Const(0.375))
    def split1 = {
    val x6593 = withCtrl(x6604) { OpDef(op=FixLt, inputs=List(Const(2.5), x6590)).name("x6593").srcCtx("NonLinearity.scala:76:28") } // FixLt(Const(2.5),x6590)
    val x6594 = withCtrl(x6604) { OpDef(op=FixLt, inputs=List(Const(0.5), x6590)).name("x6594").srcCtx("NonLinearity.scala:77:14") } // FixLt(Const(0.5),x6590)
    val x6595 = withCtrl(x6604) { OpDef(op=FixLt, inputs=List(x6590, Const(2.5))).name("x6595").srcCtx("NonLinearity.scala:77:31") } // FixLt(x6590,Const(2.5))
    val x6596 = withCtrl(x6604) { OpDef(op=BitAnd, inputs=List(x6594, x6595)).name("x6596").srcCtx("NonLinearity.scala:77:22") } // And(x6594,x6595)
    val x6597 = withCtrl(x6604) { OpDef(op=MuxOp, inputs=List(x6596, x6592, x6590)).name("x6597").srcCtx("NonLinearity.scala:77:10") } // Mux(x6596,x6592,x6590)
    val x6598 = withCtrl(x6604) { OpDef(op=MuxOp, inputs=List(x6593, Const(1.0), x6597)).name("x6598").srcCtx("NonLinearity.scala:76:21:absout") } // Mux(x6593,Const(1),x6597)
    val x6599 = withCtrl(x6604) { OpDef(op=FixNeg, inputs=List(x6598)).name("x6599").srcCtx("NonLinearity.scala:80:23:negout") } // FixNeg(x6598)
    val x6600 = withCtrl(x6604) { OpDef(op=FixLt, inputs=List(x6583, Const(0.0))).name("x6600").srcCtx("NonLinearity.scala:81:12") } // FixLt(x6583,Const(0))
    val x6601 = withCtrl(x6604) { OpDef(op=MuxOp, inputs=List(x6600, x6599, x6598)).name("x6601").srcCtx("NonLinearity.scala:81:8:actEle") } // Mux(x6600,x6599,x6598)
    val x6602_x6570 = withCtrl(x6604) { WriteMem(x6570, x6589).name("x6602_x6570").srcCtx("LSTMTiled.scala:93:25") } // ParFIFOEnq(x6570,List(x6589),List(x6579))
    val x6603_x6571 = withCtrl(x6604) { WriteMem(x6571, x6601).name("x6603_x6571").srcCtx("LSTMTiled.scala:94:25") } // ParFIFOEnq(x6571,List(x6601),List(x6579))
    val x6605 = withCtrl(x6661) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6605").srcCtx("LSTMTiled.scala:97:43") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6606 = withCtrl(x6661) { CounterChain(List(x6605)).name("x6606").srcCtx("LSTMTiled.scala:97:57") } // CounterChainNew(List(x6605))
    val x6631 = withCtrl(x6661) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6606).name("x6631").srcCtx("LSTMTiled.scala:97:57") } // UnrolledForeach(List(b3995, b3902, b3896, b3892),x6606,Block(Const(())),List(List(b4035)),List(List(b4036)))
    val b4035 = withCtrl(x6631) { CounterIter(x6605, None).name("b4035") } // b4035
    val b4036 = withCtrl(x6631) { Const(true).name("b4036") } // b4036
    val x6607 = withCtrl(x6631) { OpDef(op=BitAnd, inputs=List(b4036, b3995)).name("x6607").srcCtx("UnrollingBase.scala:28:66") } // And(b4036,b3995)
    val x6608 = withCtrl(x6631) { OpDef(op=BitAnd, inputs=List(b3902, b3896)).name("x6608").srcCtx("UnrollingBase.scala:28:66") } // And(b3902,b3896)
    val x6609 = withCtrl(x6631) { OpDef(op=BitAnd, inputs=List(x6607, x6608)).name("x6609").srcCtx("UnrollingBase.scala:28:66") } // And(x6607,x6608)
    val x6610 = withCtrl(x6631) { OpDef(op=BitAnd, inputs=List(x6609, b3892)).name("x6610").srcCtx("UnrollingBase.scala:28:66") } // And(x6609,b3892)
    val x6611 = withCtrl(x6631) { ReadMem(x6570).name("x6611").srcCtx("LSTMTiled.scala:98:38:sigEle") } // ParFIFODeq(x6570,List(x6610))
    val x6612 = withCtrl(x6631) { x6611 } // VectorApply(x6611,0)
    val x6613 = withCtrl(x6631) { ReadMem(x6571).name("x6613").srcCtx("LSTMTiled.scala:99:38:actEle") } // ParFIFODeq(x6571,List(x6610))
    val x6614 = withCtrl(x6631) { x6613 } // VectorApply(x6613,0)
    val x6615 = withCtrl(x6631) { LoadBanks(List(x6495_d1_b0), List(b4035)).name("x6615").srcCtx("LSTMTiled.scala:101:33:cLast") } // ParSRAMLoad(x6495,List(List(b4035)),List(x6610))
    val x6616 = withCtrl(x6631) { x6615 } // VectorApply(x6615,0)
    val x6617 = withCtrl(x6631) { OpDef(op=FixMul, inputs=List(x6616, x6614)).name("x6617").srcCtx("LSTMTiled.scala:102:38:cNewMult") } // FixMul(x6616,x6614)
    val x6618 = withCtrl(x6631) { LoadBanks(List(x6312_d0_b0), List(b3895, b4035)).name("x6618").srcCtx("LSTMTiled.scala:103:45") } // ParSRAMLoad(x6312,List(List(b3895, b4035)),List(x6610))
    val x6619 = withCtrl(x6631) { x6618 } // VectorApply(x6618,0)
    val x6620 = withCtrl(x6631) { OpDef(op=FixMul, inputs=List(x6612, x6619)).name("x6620").srcCtx("LSTMTiled.scala:103:42") } // FixMul(x6612,x6619)
    val x6621 = withCtrl(x6631) { OpDef(op=FixAdd, inputs=List(x6620, x6616)).name("x6621").srcCtx("LSTMTiled.scala:103:61:cNewMultAdd") } // FixAdd(x6620,x6616)
    val x6622 = withCtrl(x6631) { OpDef(op=FixEql, inputs=List(b3994, Const(0))).name("x6622").srcCtx("LSTMTiled.scala:13:40") } // FixEql(b3994,Const(0))
    val x6623 = withCtrl(x6631) { OpDef(op=FixEql, inputs=List(b3994, Const(1))).name("x6623").srcCtx("LSTMTiled.scala:15:40") } // FixEql(b3994,Const(1))
    val x6624 = withCtrl(x6631) { OpDef(op=FixEql, inputs=List(b3994, Const(2))).name("x6624").srcCtx("LSTMTiled.scala:17:40") } // FixEql(b3994,Const(2))
    val x6625 = withCtrl(x6631) { OpDef(op=MuxOp, inputs=List(x6624, x6621, x6616)).name("x6625").srcCtx("LSTMTiled.scala:108:26") } // Mux(x6624,x6621,x6616)
    val x6626 = withCtrl(x6631) { OpDef(op=MuxOp, inputs=List(x6623, x6617, x6625)).name("x6626").srcCtx("LSTMTiled.scala:107:24") } // Mux(x6623,x6617,x6625)
    val x6627 = withCtrl(x6631) { OpDef(op=MuxOp, inputs=List(x6622, x6612, x6626)).name("x6627").srcCtx("LSTMTiled.scala:106:22") } // Mux(x6622,x6612,x6626)
    val x6628 = withCtrl(x6631) { StoreBanks(List(List(x6495_d0_b0), List(x6495_d1_b0)), List(b4035), x6627).name("x6628").srcCtx("LSTMTiled.scala:105:29") } // ParSRAMStore(x6495,List(List(b4035)),List(x6627),List(x6610))
    antiDepsOf(x6628)=List(x6615)
    val x6629_x6572 = withCtrl(x6631) { WriteMem(x6572, x6612).name("x6629_x6572").srcCtx("LSTMTiled.scala:112:28") } // ParFIFOEnq(x6572,List(x6612),List(x6610))
    val x6630_x6573 = withCtrl(x6631) { WriteMem(x6573, x6621).name("x6630_x6573").srcCtx("LSTMTiled.scala:113:33") } // ParFIFOEnq(x6573,List(x6621),List(x6610))
    val x6632 = withCtrl(x6661) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6632").srcCtx("LSTMTiled.scala:116:43") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6633 = withCtrl(x6661) { CounterChain(List(x6632)).name("x6633").srcCtx("LSTMTiled.scala:116:57") } // CounterChainNew(List(x6632))
    val x6660 = withCtrl(x6661) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6633).name("x6660").srcCtx("LSTMTiled.scala:116:57") } // UnrolledForeach(List(b3995, b3902, b3896, b3892),x6633,Block(Const(())),List(List(b4064)),List(List(b4065)))
    val b4064 = withCtrl(x6660) { CounterIter(x6632, None).name("b4064") } // b4064
    val b4065 = withCtrl(x6660) { Const(true).name("b4065") } // b4065
    val x6634 = withCtrl(x6660) { OpDef(op=BitAnd, inputs=List(b4065, b3995)).name("x6634").srcCtx("UnrollingBase.scala:28:66") } // And(b4065,b3995)
    val x6635 = withCtrl(x6660) { OpDef(op=BitAnd, inputs=List(b3902, b3896)).name("x6635").srcCtx("UnrollingBase.scala:28:66") } // And(b3902,b3896)
    val x6636 = withCtrl(x6660) { OpDef(op=BitAnd, inputs=List(x6634, x6635)).name("x6636").srcCtx("UnrollingBase.scala:28:66") } // And(x6634,x6635)
    val x6637 = withCtrl(x6660) { OpDef(op=BitAnd, inputs=List(x6636, b3892)).name("x6637").srcCtx("UnrollingBase.scala:28:66") } // And(x6636,b3892)
    val x6638 = withCtrl(x6660) { ReadMem(x6572).name("x6638").srcCtx("LSTMTiled.scala:117:41:sigEle") } // ParFIFODeq(x6572,List(x6637))
    val x6639 = withCtrl(x6660) { x6638 } // VectorApply(x6638,0)
    val x6640 = withCtrl(x6660) { ReadMem(x6573).name("x6640").srcCtx("LSTMTiled.scala:118:51:cNewMultAdd") } // ParFIFODeq(x6573,List(x6637))
    val x6641 = withCtrl(x6660) { x6640 } // VectorApply(x6640,0)
    val x6642 = withCtrl(x6660) { OpDef(op=FixAbs, inputs=List(x6641)).name("x6642").srcCtx("NonLinearity.scala:73:20:absin") } // FixAbs(x6641)
    val x6643 = withCtrl(x6660) { OpDef(op=FixSra, inputs=List(x6642, Const(2))).name("x6643").srcCtx("NonLinearity.scala:74:22:div4") } // FixRsh(x6642,Const(2))
    val x6644 = withCtrl(x6660) { OpDef(op=FixAdd, inputs=List(x6643, Const(0.375))).name("x6644").srcCtx("NonLinearity.scala:75:19:li") } // FixAdd(x6643,Const(0.375))
    val x6645 = withCtrl(x6660) { OpDef(op=FixLt, inputs=List(Const(2.5), x6642)).name("x6645").srcCtx("NonLinearity.scala:76:28") } // FixLt(Const(2.5),x6642)
    val x6646 = withCtrl(x6660) { OpDef(op=FixLt, inputs=List(Const(0.5), x6642)).name("x6646").srcCtx("NonLinearity.scala:77:14") } // FixLt(Const(0.5),x6642)
    val x6647 = withCtrl(x6660) { OpDef(op=FixLt, inputs=List(x6642, Const(2.5))).name("x6647").srcCtx("NonLinearity.scala:77:31") } // FixLt(x6642,Const(2.5))
    val x6648 = withCtrl(x6660) { OpDef(op=BitAnd, inputs=List(x6646, x6647)).name("x6648").srcCtx("NonLinearity.scala:77:22") } // And(x6646,x6647)
    val x6649 = withCtrl(x6660) { OpDef(op=MuxOp, inputs=List(x6648, x6644, x6642)).name("x6649").srcCtx("NonLinearity.scala:77:10") } // Mux(x6648,x6644,x6642)
    val x6650 = withCtrl(x6660) { OpDef(op=MuxOp, inputs=List(x6645, Const(1.0), x6649)).name("x6650").srcCtx("NonLinearity.scala:76:21:absout") } // Mux(x6645,Const(1),x6649)
    val x6651 = withCtrl(x6660) { OpDef(op=FixNeg, inputs=List(x6650)).name("x6651").srcCtx("NonLinearity.scala:80:23:negout") } // FixNeg(x6650)
    val x6652 = withCtrl(x6660) { OpDef(op=FixLt, inputs=List(x6641, Const(0.0))).name("x6652").srcCtx("NonLinearity.scala:81:12") } // FixLt(x6641,Const(0))
    val x6653 = withCtrl(x6660) { OpDef(op=MuxOp, inputs=List(x6652, x6651, x6650)).name("x6653").srcCtx("NonLinearity.scala:81:8") } // Mux(x6652,x6651,x6650)
    val x6654 = withCtrl(x6660) { OpDef(op=FixAdd, inputs=List(x6653, x6639)).name("x6654").srcCtx("LSTMTiled.scala:120:52:hNew") } // FixAdd(x6653,x6639)
    val x6655 = withCtrl(x6660) { LoadBanks(List(x6496_d1_b0), List(b4064)).name("x6655").srcCtx("LSTMTiled.scala:121:33:hLast") } // ParSRAMLoad(x6496,List(List(b4064)),List(x6637))
    val x6656 = withCtrl(x6660) { x6655 } // VectorApply(x6655,0)
    val x6657 = withCtrl(x6660) { OpDef(op=FixEql, inputs=List(b3994, Const(3))).name("x6657").srcCtx("LSTMTiled.scala:19:40") } // FixEql(b3994,Const(3))
    val x6658 = withCtrl(x6660) { OpDef(op=MuxOp, inputs=List(x6657, x6654, x6656)).name("x6658").srcCtx("LSTMTiled.scala:123:33:update") } // Mux(x6657,x6654,x6656)
    val x6659 = withCtrl(x6660) { StoreBanks(List(List(x6496_d0_b0), List(x6496_d1_b0)), List(b4064), x6658).name("x6659").srcCtx("LSTMTiled.scala:124:29") } // ParSRAMStore(x6496,List(List(b4064)),List(x6658),List(x6637))
    antiDepsOf(x6659)=List(x6655)
    val x6662 = withCtrl(x6674) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6662").srcCtx("LSTMTiled.scala:129:41") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6663 = withCtrl(x6674) { CounterChain(List(x6662)).name("x6663").srcCtx("LSTMTiled.scala:129:55") } // CounterChainNew(List(x6662))
    val x6673 = withCtrl(x6674) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6663).name("x6673").srcCtx("LSTMTiled.scala:129:55") } // UnrolledForeach(List(b3902, b3896, b3892),x6663,Block(Const(())),List(List(b4096)),List(List(b4097)))
    val b4096 = withCtrl(x6673) { CounterIter(x6662, None).name("b4096") } // b4096
    val b4097 = withCtrl(x6673) { Const(true).name("b4097") } // b4097
    val x6664 = withCtrl(x6673) { OpDef(op=BitAnd, inputs=List(b4097, b3902)).name("x6664").srcCtx("UnrollingBase.scala:28:66") } // And(b4097,b3902)
    val x6665 = withCtrl(x6673) { OpDef(op=BitAnd, inputs=List(b3896, b3892)).name("x6665").srcCtx("UnrollingBase.scala:28:66") } // And(b3896,b3892)
    val x6666 = withCtrl(x6673) { OpDef(op=BitAnd, inputs=List(x6664, x6665)).name("x6666").srcCtx("UnrollingBase.scala:28:66") } // And(x6664,x6665)
    val x6667 = withCtrl(x6673) { LoadBanks(List(x6496_d0_b0), List(b4096)).name("x6667").srcCtx("LSTMTiled.scala:130:30:hNew") } // ParSRAMLoad(x6496,List(List(b4096)),List(x6666))
    val x6668 = withCtrl(x6673) { x6667 } // VectorApply(x6667,0)
    val x6669 = withCtrl(x6673) { LoadBanks(List(x6495_d0_b0), List(b4096)).name("x6669").srcCtx("LSTMTiled.scala:131:30:cNew") } // ParSRAMLoad(x6495,List(List(b4096)),List(x6666))
    val x6670 = withCtrl(x6673) { x6669 } // VectorApply(x6669,0)
    val x6671_x6489 = withCtrl(x6673) { WriteMem(x6489, x6668).name("x6671_x6489").srcCtx("LSTMTiled.scala:132:22") } // ParFIFOEnq(x6489,List(x6668),List(x6666))
    val x6672_x6490 = withCtrl(x6673) { WriteMem(x6490, x6670).name("x6672_x6490").srcCtx("LSTMTiled.scala:133:22") } // ParFIFOEnq(x6490,List(x6670),List(x6666))
    val x6675 = withCtrl(x6690) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6675").srcCtx("LSTMTiled.scala:137:38") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6676 = withCtrl(x6690) { CounterChain(List(x6675)).name("x6676").srcCtx("LSTMTiled.scala:137:52") } // CounterChainNew(List(x6675))
    val x6689 = withCtrl(x6690) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6676).name("x6689").srcCtx("LSTMTiled.scala:137:52") } // UnrolledForeach(List(b3896, b3892),x6676,Block(Const(())),List(List(b4111)),List(List(b4112)))
    val b4111 = withCtrl(x6689) { CounterIter(x6675, None).name("b4111") } // b4111
    val b4112 = withCtrl(x6689) { Const(true).name("b4112") } // b4112
    val x6677 = withCtrl(x6689) { Reg(init=Some(0.0)).name("x6677").srcCtx("LSTMTiled.scala:138:30:hReg") } // x6677 = RegNew(Const(0))
    isAccum(x6677) = false
    bufferDepthOf(x6677) = 1
    val x6678 = withCtrl(x6689) { OpDef(op=BitAnd, inputs=List(b4112, b3896)).name("x6678").srcCtx("UnrollingBase.scala:28:66") } // And(b4112,b3896)
    val x6679 = withCtrl(x6689) { OpDef(op=BitAnd, inputs=List(x6678, b3892)).name("x6679").srcCtx("UnrollingBase.scala:28:66") } // And(x6678,b3892)
    val x6680 = withCtrl(x6689) { ReadMem(x6489).name("x6680").srcCtx("LSTMTiled.scala:139:28") } // ParFIFODeq(x6489,List(x6679))
    val x6681 = withCtrl(x6689) { x6680 } // VectorApply(x6680,0)
    val x6682_x6677 = withCtrl(x6689) { WriteMem(x6677, x6681).name("x6682_x6677").srcCtx("LSTMTiled.scala:139:18") } // RegWrite(x6677,x6681,x6679)
    val x6683 = withCtrl(x6689) { ReadMem(x6677).name("x6683").srcCtx("LSTMTiled.scala:140:42") } // RegRead(x6677)
    antiDepsOf(x6683)=List(x6682_x6677)
    val x6684 = withCtrl(x6689) { StoreBanks(List(List(x6287_d0_b0), List(x6287_d1_b0)), List(b3891, b4111), x6683).name("x6684").srcCtx("LSTMTiled.scala:140:35") } // ParSRAMStore(x6287,List(List(b3891, b4111)),List(x6683),List(x6679))
    val x6685 = withCtrl(x6689) { StoreBanks(List(List(x6337_d0_b0)), List(b3895, b4111), x6683).name("x6685").srcCtx("LSTMTiled.scala:141:36") } // ParSRAMStore(x6337,List(List(b3895, b4111)),List(x6683),List(x6679))
    val x6686 = withCtrl(x6689) { ReadMem(x6490).name("x6686").srcCtx("LSTMTiled.scala:142:45") } // ParFIFODeq(x6490,List(x6679))
    val x6687 = withCtrl(x6689) { x6686 } // VectorApply(x6686,0)
    val x6688 = withCtrl(x6689) { StoreBanks(List(List(x6312_d0_b0), List(x6312_d1_b0)), List(b3895, b4111), x6687).name("x6688").srcCtx("LSTMTiled.scala:142:36") } // ParSRAMStore(x6312,List(List(b3895, b4111)),List(x6687),List(x6679))
    val x6692 = withCtrl(x6720) { Counter(min=Const(0), max=Const(8), step=Const(1), par=1).name("x6692").srcCtx("LSTMTiled.scala:147:47") } // CounterNew(Const(0),Const(8),Const(1),Const(1))
    val x6693 = withCtrl(x6720) { CounterChain(List(x6692)).name("x6693").srcCtx("LSTMTiled.scala:147:47") } // CounterChainNew(List(x6692))
    val x6719 = withCtrl(x6720) { LoopController(style=StreamPipe, level=OuterControl, cchain=x6693).name("x6719").srcCtx("LSTMTiled.scala:147:47") } // UnrolledForeach(List(Const(true)),x6693,Block(Const(())),List(List(b4130)),List(List(b4131)))
    val b4130 = withCtrl(x6719) { CounterIter(x6692, Some(0)).name("b4130") } // b4130
    val b4131 = withCtrl(x6719) { Const(true).name("b4131") } // b4131
    val b6755 = withCtrl(x6719) { StreamOut(field="offset").name("b6755").srcCtx("LSTMTiled.scala:147:47") } // x6694 = StreamOutNew(BurstCmdBus)
    isAccum(b6755) = false
    bufferDepthOf(b6755) = 1
    val b6756 = withCtrl(x6719) { StreamOut(field="size").name("b6756").srcCtx("LSTMTiled.scala:147:47") } // x6694 = StreamOutNew(BurstCmdBus)
    isAccum(b6756) = false
    bufferDepthOf(b6756) = 1
    val x6695 = withCtrl(x6719) { StreamOut(field="data").name("x6695").srcCtx("LSTMTiled.scala:147:47") } // x6695 = StreamOutNew(BurstFullDataBus())
    isAccum(x6695) = false
    bufferDepthOf(x6695) = 1
    val x6696 = withCtrl(x6719) { StreamIn(field="ack").name("x6696").srcCtx("LSTMTiled.scala:147:47") } // x6696 = StreamInNew(BurstAckBus)
    isAccum(x6696) = false
    bufferDepthOf(x6696) = 1
    val x6707 = withCtrl(x6719) { UnitController(style=SeqPipe, level=InnerControl).name("x6707").srcCtx("LSTMTiled.scala:147:47") } // UnitPipe(List(b4131),Block(x6706))
    val x6697 = withCtrl(x6707) { b4130 } // FixConvert(b4130,TRUE,_32,_0) (Same Type. No op)
    val x6698 = withCtrl(x6707) { OpDef(op=FixSla, inputs=List(x6697, Const(7))).name("x6698").srcCtx("LSTMTiled.scala:147:47") } // FixLsh(x6697,Const(7))
    val x6699 = withCtrl(x6707) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x6700 = withCtrl(x6707) { OpDef(op=FixAdd, inputs=List(x6698, x6699)).name("x6700").srcCtx("LSTMTiled.scala:147:47") } // FixAdd(x6698,x6699)
    val x6701 = withCtrl(x6707) { OpDef(op=FixSla, inputs=List(x6700, Const(2))).name("x6701").srcCtx("LSTMTiled.scala:147:47") } // FixLsh(x6700,Const(2))
    val x6702 = withCtrl(x6707) { x6701 } // FixConvert(x6701,TRUE,_64,_0)
    val x6703 = withCtrl(x6707) { DramAddress(x6220).name("x6703").srcCtx("LSTMTiled.scala:147:47") } // GetDRAMAddress(x6220)
    val x6705_x6704 = withCtrl(x6707) { OpDef(op=FixAdd, inputs=List(x6702, x6703)).name("x6705_x6704").srcCtx("LSTMTiled.scala:147:47") } // FixAdd(x6702,x6703)
    // x6705 = SimpleStruct(ArrayBuffer((offset,x6704), (size,Const(512)), (isLoad,Const(false))))
    val x6706_b6757_b6755 = withCtrl(x6707) { WriteMem(b6755, x6705_x6704).name("x6706_b6757_b6755").srcCtx("LSTMTiled.scala:147:47") } // StreamWrite(x6694,x6705,b4131)
    val x6706_b6758_b6756 = withCtrl(x6707) { WriteMem(b6756, Const(512)).name("x6706_b6758_b6756").srcCtx("LSTMTiled.scala:147:47") } // StreamWrite(x6694,x6705,b4131)
    val x6708 = withCtrl(x6719) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6708").srcCtx("LSTMTiled.scala:147:47") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6709 = withCtrl(x6719) { CounterChain(List(x6708)).name("x6709").srcCtx("LSTMTiled.scala:147:47") } // CounterChainNew(List(x6708))
    val x6715 = withCtrl(x6719) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6709).name("x6715").srcCtx("LSTMTiled.scala:147:47") } // UnrolledForeach(List(b4131),x6709,Block(Const(())),List(List(b4148)),List(List(b4149)))
    val b4148 = withCtrl(x6715) { CounterIter(x6708, None).name("b4148") } // b4148
    val b4149 = withCtrl(x6715) { Const(true).name("b4149") } // b4149
    val x6710 = withCtrl(x6715) { OpDef(op=BitAnd, inputs=List(b4149, b4131)).name("x6710").srcCtx("UnrollingBase.scala:28:66") } // And(b4149,b4131)
    val x6711 = withCtrl(x6715) { LoadBanks(List(x6287_d0_b0), List(b4130, b4148)).name("x6711").srcCtx("LSTMTiled.scala:147:47") } // ParSRAMLoad(x6287,List(List(b4130, b4148)),List(x6710))
    val x6713_x6712 = withCtrl(x6715) { x6711 } // VectorApply(x6711,0)
    // x6713 = SimpleStruct(ArrayBuffer((_1,x6712), (_2,Const(true))))
    val x6714_x6714_x6695 = withCtrl(x6715) { WriteMem(x6695, x6713_x6712).name("x6714_x6714_x6695").srcCtx("LSTMTiled.scala:147:47") } // ParStreamWrite(x6695,List(x6713),List(x6710))
    val x6716 = withCtrl(x6719) { FringeDenseStore(dram=List(x6220), cmdStream=List(b6755, b6756), dataStream=List(x6695), ackStream=List(x6696)).name("x6716").srcCtx("LSTMTiled.scala:147:47") } // FringeDenseStore(x6220,x6694,x6695,x6696)
    val x6718 = withCtrl(x6719) { UnitController(style=SeqPipe, level=InnerControl).name("x6718").srcCtx("LSTMTiled.scala:147:47") } // UnitPipe(List(b4131),Block(Const(())))
    val x6717_x6717 = withCtrl(x6718) { ReadMem(x6696).name("x6717_x6717").srcCtx("LSTMTiled.scala:147:47") } // StreamRead(x6696,b4131)
    }; split1
    
  }
}
