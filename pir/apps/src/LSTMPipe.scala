import pir._
import pir.node._
import arch._
import prism.enums._

object LSTMPipe extends PIRApp {
  def main(implicit design:PIRDesign) = {
    import design.pirmeta._
    val x6386 = withCtrl(design.top.topController) { DRAM(dims=List(Const(8), Const(128))).name("x6386").srcCtx("LSTMPipe.scala:24:23:dout") } // x6386 = DRAMNew(ArrayBuffer(Const(8), Const(128)),Const(0))
    val x6387 = withCtrl(design.top.topController) { DRAM(dims=List(Const(8), Const(128))).name("x6387").srcCtx("DataGenerator.scala:156:20:xInit") } // x6387 = DRAMNew(ArrayBuffer(Const(8), Const(128)),Const(0))
    val x6394 = withCtrl(design.top.topController) { DRAM(dims=List(Const(2), Const(128))).name("x6394").srcCtx("DataGenerator.scala:156:20:cInit") } // x6394 = DRAMNew(ArrayBuffer(Const(2), Const(128)),Const(0))
    val x6401 = withCtrl(design.top.topController) { DRAM(dims=List(Const(2), Const(128))).name("x6401").srcCtx("DataGenerator.scala:156:20:hInit") } // x6401 = DRAMNew(ArrayBuffer(Const(2), Const(128)),Const(0))
    val x6408 = withCtrl(design.top.topController) { DRAM(dims=List(Const(2), Const(128), Const(4), Const(128))).name("x6408").srcCtx("DataGenerator.scala:182:20:wxInit") } // x6408 = DRAMNew(ArrayBuffer(Const(2), Const(128), Const(4), Const(128)),Const(0))
    val x6421 = withCtrl(design.top.topController) { DRAM(dims=List(Const(2), Const(128), Const(4), Const(128))).name("x6421").srcCtx("DataGenerator.scala:182:20:whInit") } // x6421 = DRAMNew(ArrayBuffer(Const(2), Const(128), Const(4), Const(128)),Const(0))
    val x6434 = withCtrl(design.top.topController) { DRAM(dims=List(Const(2), Const(4), Const(128))).name("x6434").srcCtx("DataGenerator.scala:168:20:bInit") } // x6434 = DRAMNew(ArrayBuffer(Const(2), Const(4), Const(128)),Const(0))
    val x6946 = withCtrl(design.top.topController) { UnitController(style=SeqPipe, level=OuterControl).name("x6946").srcCtx("LSTMPipe.scala:34:11") } // Hwblock(Block(Const(())),false)
    val x6444_d0_b0 = withCtrl(x6946) { SRAM(size=1024, banking=Strided(banks=16, stride=1)).name("x6444_d0_b0").srcCtx("DataGenerator.scala:43:21:x") } // x6444 = SRAMNew(ArrayBuffer(Const(8), Const(128)))
    isAccum(x6444_d0_b0) = false
    bufferDepthOf(x6444_d0_b0) = 1
    staticDimsOf(x6444_d0_b0) = List(8, 128)
    val x6444_d1_b0 = withCtrl(x6946) { SRAM(size=1024, banking=Strided(banks=16, stride=1)).name("x6444_d1_b0").srcCtx("DataGenerator.scala:43:21:x") } // x6444 = SRAMNew(ArrayBuffer(Const(8), Const(128)))
    isAccum(x6444_d1_b0) = false
    bufferDepthOf(x6444_d1_b0) = 4
    staticDimsOf(x6444_d1_b0) = List(8, 128)
    val x6445 = withCtrl(x6946) { Counter(min=Const(0), max=Const(8), step=Const(1), par=1).name("x6445").srcCtx("DataGenerator.scala:44:8") } // CounterNew(Const(0),Const(8),Const(1),Const(1))
    val x6446 = withCtrl(x6946) { CounterChain(List(x6445)).name("x6446").srcCtx("DataGenerator.scala:44:8") } // CounterChainNew(List(x6445))
    val x6468 = withCtrl(x6946) { LoopController(style=StreamPipe, level=OuterControl, cchain=x6446).name("x6468").srcCtx("DataGenerator.scala:44:8") } // UnrolledForeach(List(Const(true)),x6446,Block(Const(())),List(List(b3495)),List(List(b3496)))
    val b3495 = withCtrl(x6468) { CounterIter(x6445, Some(0)).name("b3495") } // b3495
    val b3496 = withCtrl(x6468) { Const(true).name("b3496") } // b3496
    val b6957 = withCtrl(x6468) { StreamOut(field="offset").name("b6957").srcCtx("DataGenerator.scala:44:8") } // x6447 = StreamOutNew(BurstCmdBus)
    isAccum(b6957) = false
    bufferDepthOf(b6957) = 1
    val b6958 = withCtrl(x6468) { StreamOut(field="size").name("b6958").srcCtx("DataGenerator.scala:44:8") } // x6447 = StreamOutNew(BurstCmdBus)
    isAccum(b6958) = false
    bufferDepthOf(b6958) = 1
    val x6448 = withCtrl(x6468) { StreamIn(field="data").name("x6448").srcCtx("DataGenerator.scala:44:8") } // x6448 = StreamInNew(BurstDataBus())
    isAccum(x6448) = false
    bufferDepthOf(x6448) = 1
    val x6459 = withCtrl(x6468) { UnitController(style=SeqPipe, level=InnerControl).name("x6459").srcCtx("DataGenerator.scala:44:8") } // UnitPipe(List(b3496),Block(x6458))
    val x6449 = withCtrl(x6459) { b3495 } // FixConvert(b3495,TRUE,_32,_0) (Same Type. No op)
    val x6450 = withCtrl(x6459) { OpDef(op=FixSla, inputs=List(x6449, Const(7))).name("x6450").srcCtx("DataGenerator.scala:44:8") } // FixLsh(x6449,Const(7))
    val x6451 = withCtrl(x6459) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x6452 = withCtrl(x6459) { OpDef(op=FixAdd, inputs=List(x6450, x6451)).name("x6452").srcCtx("DataGenerator.scala:44:8") } // FixAdd(x6450,x6451)
    val x6453 = withCtrl(x6459) { OpDef(op=FixSla, inputs=List(x6452, Const(2))).name("x6453").srcCtx("DataGenerator.scala:44:8") } // FixLsh(x6452,Const(2))
    val x6454 = withCtrl(x6459) { x6453 } // FixConvert(x6453,TRUE,_64,_0)
    val x6455 = withCtrl(x6459) { DramAddress(x6387).name("x6455").srcCtx("DataGenerator.scala:44:8") } // GetDRAMAddress(x6387)
    val x6457_x6456 = withCtrl(x6459) { OpDef(op=FixAdd, inputs=List(x6454, x6455)).name("x6457_x6456").srcCtx("DataGenerator.scala:44:8") } // FixAdd(x6454,x6455)
    // x6457 = SimpleStruct(ArrayBuffer((offset,x6456), (size,Const(512)), (isLoad,Const(true))))
    val x6458_b6959_b6957 = withCtrl(x6459) { WriteMem(b6957, x6457_x6456).name("x6458_b6959_b6957").srcCtx("DataGenerator.scala:44:8") } // StreamWrite(x6447,x6457,b3496)
    val x6458_b6960_b6958 = withCtrl(x6459) { WriteMem(b6958, Const(512)).name("x6458_b6960_b6958").srcCtx("DataGenerator.scala:44:8") } // StreamWrite(x6447,x6457,b3496)
    val x6460 = withCtrl(x6468) { FringeDenseLoad(dram=List(x6387), cmdStream=List(b6957, b6958), dataStream=List(x6448)).name("x6460").srcCtx("DataGenerator.scala:44:8") } // FringeDenseLoad(x6387,x6447,x6448)
    val x6461 = withCtrl(x6468) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6461").srcCtx("DataGenerator.scala:44:8") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6462 = withCtrl(x6468) { CounterChain(List(x6461)).name("x6462").srcCtx("DataGenerator.scala:44:8") } // CounterChainNew(List(x6461))
    val x6467 = withCtrl(x6468) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6462).name("x6467").srcCtx("DataGenerator.scala:44:8") } // UnrolledForeach(List(b3496),x6462,Block(Const(())),List(List(b3513)),List(List(b3514)))
    val b3513 = withCtrl(x6467) { CounterIter(x6461, None).name("b3513") } // b3513
    val b3514 = withCtrl(x6467) { Const(true).name("b3514") } // b3514
    val x6463 = withCtrl(x6467) { OpDef(op=BitAnd, inputs=List(b3514, b3496)).name("x6463").srcCtx("UnrollingBase.scala:28:66") } // And(b3514,b3496)
    val x6464_x6464 = withCtrl(x6467) { ReadMem(x6448).name("x6464_x6464").srcCtx("DataGenerator.scala:44:8") } // ParStreamRead(x6448,List(x6463))
    val x6465_x6465 = withCtrl(x6467) { x6464_x6464 } // VectorApply(x6464,0)
    val x6466 = withCtrl(x6467) { StoreBanks(List(List(x6444_d0_b0), List(x6444_d1_b0)), List(b3495, b3513), x6465_x6465).name("x6466").srcCtx("DataGenerator.scala:44:8") } // ParSRAMStore(x6444,List(List(b3495, b3513)),List(x6465),List(x6463))
    val x6469_d0_b0 = withCtrl(x6946) { SRAM(size=256, banking=Strided(banks=16, stride=1)).name("x6469_d0_b0").srcCtx("DataGenerator.scala:43:21:c") } // x6469 = SRAMNew(ArrayBuffer(Const(2), Const(128)))
    isAccum(x6469_d0_b0) = false
    bufferDepthOf(x6469_d0_b0) = 2
    staticDimsOf(x6469_d0_b0) = List(2, 128)
    val x6469_d1_b0 = withCtrl(x6946) { SRAM(size=256, banking=Strided(banks=16, stride=1)).name("x6469_d1_b0").srcCtx("DataGenerator.scala:43:21:c") } // x6469 = SRAMNew(ArrayBuffer(Const(2), Const(128)))
    isAccum(x6469_d1_b0) = false
    bufferDepthOf(x6469_d1_b0) = 2
    staticDimsOf(x6469_d1_b0) = List(2, 128)
    val x6470 = withCtrl(x6946) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x6470").srcCtx("DataGenerator.scala:44:8") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x6471 = withCtrl(x6946) { CounterChain(List(x6470)).name("x6471").srcCtx("DataGenerator.scala:44:8") } // CounterChainNew(List(x6470))
    val x6493 = withCtrl(x6946) { LoopController(style=StreamPipe, level=OuterControl, cchain=x6471).name("x6493").srcCtx("DataGenerator.scala:44:8") } // UnrolledForeach(List(Const(true)),x6471,Block(Const(())),List(List(b3524)),List(List(b3525)))
    val b3524 = withCtrl(x6493) { CounterIter(x6470, Some(0)).name("b3524") } // b3524
    val b3525 = withCtrl(x6493) { Const(true).name("b3525") } // b3525
    val b6961 = withCtrl(x6493) { StreamOut(field="offset").name("b6961").srcCtx("DataGenerator.scala:44:8") } // x6472 = StreamOutNew(BurstCmdBus)
    isAccum(b6961) = false
    bufferDepthOf(b6961) = 1
    val b6962 = withCtrl(x6493) { StreamOut(field="size").name("b6962").srcCtx("DataGenerator.scala:44:8") } // x6472 = StreamOutNew(BurstCmdBus)
    isAccum(b6962) = false
    bufferDepthOf(b6962) = 1
    val x6473 = withCtrl(x6493) { StreamIn(field="data").name("x6473").srcCtx("DataGenerator.scala:44:8") } // x6473 = StreamInNew(BurstDataBus())
    isAccum(x6473) = false
    bufferDepthOf(x6473) = 1
    val x6484 = withCtrl(x6493) { UnitController(style=SeqPipe, level=InnerControl).name("x6484").srcCtx("DataGenerator.scala:44:8") } // UnitPipe(List(b3525),Block(x6483))
    val x6474 = withCtrl(x6484) { b3524 } // FixConvert(b3524,TRUE,_32,_0) (Same Type. No op)
    val x6475 = withCtrl(x6484) { OpDef(op=FixSla, inputs=List(x6474, Const(7))).name("x6475").srcCtx("DataGenerator.scala:44:8") } // FixLsh(x6474,Const(7))
    val x6476 = withCtrl(x6484) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x6477 = withCtrl(x6484) { OpDef(op=FixAdd, inputs=List(x6475, x6476)).name("x6477").srcCtx("DataGenerator.scala:44:8") } // FixAdd(x6475,x6476)
    val x6478 = withCtrl(x6484) { OpDef(op=FixSla, inputs=List(x6477, Const(2))).name("x6478").srcCtx("DataGenerator.scala:44:8") } // FixLsh(x6477,Const(2))
    val x6479 = withCtrl(x6484) { x6478 } // FixConvert(x6478,TRUE,_64,_0)
    val x6480 = withCtrl(x6484) { DramAddress(x6394).name("x6480").srcCtx("DataGenerator.scala:44:8") } // GetDRAMAddress(x6394)
    val x6482_x6481 = withCtrl(x6484) { OpDef(op=FixAdd, inputs=List(x6479, x6480)).name("x6482_x6481").srcCtx("DataGenerator.scala:44:8") } // FixAdd(x6479,x6480)
    // x6482 = SimpleStruct(ArrayBuffer((offset,x6481), (size,Const(512)), (isLoad,Const(true))))
    val x6483_b6963_b6961 = withCtrl(x6484) { WriteMem(b6961, x6482_x6481).name("x6483_b6963_b6961").srcCtx("DataGenerator.scala:44:8") } // StreamWrite(x6472,x6482,b3525)
    val x6483_b6964_b6962 = withCtrl(x6484) { WriteMem(b6962, Const(512)).name("x6483_b6964_b6962").srcCtx("DataGenerator.scala:44:8") } // StreamWrite(x6472,x6482,b3525)
    val x6485 = withCtrl(x6493) { FringeDenseLoad(dram=List(x6394), cmdStream=List(b6961, b6962), dataStream=List(x6473)).name("x6485").srcCtx("DataGenerator.scala:44:8") } // FringeDenseLoad(x6394,x6472,x6473)
    val x6486 = withCtrl(x6493) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6486").srcCtx("DataGenerator.scala:44:8") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6487 = withCtrl(x6493) { CounterChain(List(x6486)).name("x6487").srcCtx("DataGenerator.scala:44:8") } // CounterChainNew(List(x6486))
    val x6492 = withCtrl(x6493) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6487).name("x6492").srcCtx("DataGenerator.scala:44:8") } // UnrolledForeach(List(b3525),x6487,Block(Const(())),List(List(b3542)),List(List(b3543)))
    val b3542 = withCtrl(x6492) { CounterIter(x6486, None).name("b3542") } // b3542
    val b3543 = withCtrl(x6492) { Const(true).name("b3543") } // b3543
    val x6488 = withCtrl(x6492) { OpDef(op=BitAnd, inputs=List(b3543, b3525)).name("x6488").srcCtx("UnrollingBase.scala:28:66") } // And(b3543,b3525)
    val x6489_x6489 = withCtrl(x6492) { ReadMem(x6473).name("x6489_x6489").srcCtx("DataGenerator.scala:44:8") } // ParStreamRead(x6473,List(x6488))
    val x6490_x6490 = withCtrl(x6492) { x6489_x6489 } // VectorApply(x6489,0)
    val x6491 = withCtrl(x6492) { StoreBanks(List(List(x6469_d0_b0), List(x6469_d1_b0)), List(b3524, b3542), x6490_x6490).name("x6491").srcCtx("DataGenerator.scala:44:8") } // ParSRAMStore(x6469,List(List(b3524, b3542)),List(x6490),List(x6488))
    val x6494_d0_b0 = withCtrl(x6946) { SRAM(size=256, banking=Strided(banks=16, stride=1)).name("x6494_d0_b0").srcCtx("DataGenerator.scala:43:21:h") } // x6494 = SRAMNew(ArrayBuffer(Const(2), Const(128)))
    isAccum(x6494_d0_b0) = false
    bufferDepthOf(x6494_d0_b0) = 4
    staticDimsOf(x6494_d0_b0) = List(2, 128)
    val x6495 = withCtrl(x6946) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x6495").srcCtx("DataGenerator.scala:44:8") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x6496 = withCtrl(x6946) { CounterChain(List(x6495)).name("x6496").srcCtx("DataGenerator.scala:44:8") } // CounterChainNew(List(x6495))
    val x6518 = withCtrl(x6946) { LoopController(style=StreamPipe, level=OuterControl, cchain=x6496).name("x6518").srcCtx("DataGenerator.scala:44:8") } // UnrolledForeach(List(Const(true)),x6496,Block(Const(())),List(List(b3553)),List(List(b3554)))
    val b3553 = withCtrl(x6518) { CounterIter(x6495, Some(0)).name("b3553") } // b3553
    val b3554 = withCtrl(x6518) { Const(true).name("b3554") } // b3554
    val b6965 = withCtrl(x6518) { StreamOut(field="offset").name("b6965").srcCtx("DataGenerator.scala:44:8") } // x6497 = StreamOutNew(BurstCmdBus)
    isAccum(b6965) = false
    bufferDepthOf(b6965) = 1
    val b6966 = withCtrl(x6518) { StreamOut(field="size").name("b6966").srcCtx("DataGenerator.scala:44:8") } // x6497 = StreamOutNew(BurstCmdBus)
    isAccum(b6966) = false
    bufferDepthOf(b6966) = 1
    val x6498 = withCtrl(x6518) { StreamIn(field="data").name("x6498").srcCtx("DataGenerator.scala:44:8") } // x6498 = StreamInNew(BurstDataBus())
    isAccum(x6498) = false
    bufferDepthOf(x6498) = 1
    val x6509 = withCtrl(x6518) { UnitController(style=SeqPipe, level=InnerControl).name("x6509").srcCtx("DataGenerator.scala:44:8") } // UnitPipe(List(b3554),Block(x6508))
    val x6499 = withCtrl(x6509) { b3553 } // FixConvert(b3553,TRUE,_32,_0) (Same Type. No op)
    val x6500 = withCtrl(x6509) { OpDef(op=FixSla, inputs=List(x6499, Const(7))).name("x6500").srcCtx("DataGenerator.scala:44:8") } // FixLsh(x6499,Const(7))
    val x6501 = withCtrl(x6509) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x6502 = withCtrl(x6509) { OpDef(op=FixAdd, inputs=List(x6500, x6501)).name("x6502").srcCtx("DataGenerator.scala:44:8") } // FixAdd(x6500,x6501)
    val x6503 = withCtrl(x6509) { OpDef(op=FixSla, inputs=List(x6502, Const(2))).name("x6503").srcCtx("DataGenerator.scala:44:8") } // FixLsh(x6502,Const(2))
    val x6504 = withCtrl(x6509) { x6503 } // FixConvert(x6503,TRUE,_64,_0)
    val x6505 = withCtrl(x6509) { DramAddress(x6401).name("x6505").srcCtx("DataGenerator.scala:44:8") } // GetDRAMAddress(x6401)
    val x6507_x6506 = withCtrl(x6509) { OpDef(op=FixAdd, inputs=List(x6504, x6505)).name("x6507_x6506").srcCtx("DataGenerator.scala:44:8") } // FixAdd(x6504,x6505)
    // x6507 = SimpleStruct(ArrayBuffer((offset,x6506), (size,Const(512)), (isLoad,Const(true))))
    val x6508_b6967_b6965 = withCtrl(x6509) { WriteMem(b6965, x6507_x6506).name("x6508_b6967_b6965").srcCtx("DataGenerator.scala:44:8") } // StreamWrite(x6497,x6507,b3554)
    val x6508_b6968_b6966 = withCtrl(x6509) { WriteMem(b6966, Const(512)).name("x6508_b6968_b6966").srcCtx("DataGenerator.scala:44:8") } // StreamWrite(x6497,x6507,b3554)
    val x6510 = withCtrl(x6518) { FringeDenseLoad(dram=List(x6401), cmdStream=List(b6965, b6966), dataStream=List(x6498)).name("x6510").srcCtx("DataGenerator.scala:44:8") } // FringeDenseLoad(x6401,x6497,x6498)
    val x6511 = withCtrl(x6518) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6511").srcCtx("DataGenerator.scala:44:8") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6512 = withCtrl(x6518) { CounterChain(List(x6511)).name("x6512").srcCtx("DataGenerator.scala:44:8") } // CounterChainNew(List(x6511))
    val x6517 = withCtrl(x6518) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6512).name("x6517").srcCtx("DataGenerator.scala:44:8") } // UnrolledForeach(List(b3554),x6512,Block(Const(())),List(List(b3571)),List(List(b3572)))
    val b3571 = withCtrl(x6517) { CounterIter(x6511, None).name("b3571") } // b3571
    val b3572 = withCtrl(x6517) { Const(true).name("b3572") } // b3572
    val x6513 = withCtrl(x6517) { OpDef(op=BitAnd, inputs=List(b3572, b3554)).name("x6513").srcCtx("UnrollingBase.scala:28:66") } // And(b3572,b3554)
    val x6514_x6514 = withCtrl(x6517) { ReadMem(x6498).name("x6514_x6514").srcCtx("DataGenerator.scala:44:8") } // ParStreamRead(x6498,List(x6513))
    val x6515_x6515 = withCtrl(x6517) { x6514_x6514 } // VectorApply(x6514,0)
    val x6516 = withCtrl(x6517) { StoreBanks(List(List(x6494_d0_b0)), List(b3553, b3571), x6515_x6515).name("x6516").srcCtx("DataGenerator.scala:44:8") } // ParSRAMStore(x6494,List(List(b3553, b3571)),List(x6515),List(x6513))
    val x6519_d0_b0 = withCtrl(x6946) { SRAM(size=32768, banking=Strided(banks=16, stride=1)).name("x6519_d0_b0").srcCtx("DataGenerator.scala:76:21:wx") } // x6519 = SRAMNew(ArrayBuffer(Const(2), Const(128), Const(4), Const(128)))
    isAccum(x6519_d0_b0) = false
    bufferDepthOf(x6519_d0_b0) = 1
    staticDimsOf(x6519_d0_b0) = List(2, 128, 4, 128)
    val x6519_d0_b1 = withCtrl(x6946) { SRAM(size=32768, banking=Strided(banks=16, stride=1)).name("x6519_d0_b1").srcCtx("DataGenerator.scala:76:21:wx") } // x6519 = SRAMNew(ArrayBuffer(Const(2), Const(128), Const(4), Const(128)))
    isAccum(x6519_d0_b1) = false
    bufferDepthOf(x6519_d0_b1) = 1
    staticDimsOf(x6519_d0_b1) = List(2, 128, 4, 128)
    val x6519_d0_b2 = withCtrl(x6946) { SRAM(size=32768, banking=Strided(banks=16, stride=1)).name("x6519_d0_b2").srcCtx("DataGenerator.scala:76:21:wx") } // x6519 = SRAMNew(ArrayBuffer(Const(2), Const(128), Const(4), Const(128)))
    isAccum(x6519_d0_b2) = false
    bufferDepthOf(x6519_d0_b2) = 1
    staticDimsOf(x6519_d0_b2) = List(2, 128, 4, 128)
    val x6519_d0_b3 = withCtrl(x6946) { SRAM(size=32768, banking=Strided(banks=16, stride=1)).name("x6519_d0_b3").srcCtx("DataGenerator.scala:76:21:wx") } // x6519 = SRAMNew(ArrayBuffer(Const(2), Const(128), Const(4), Const(128)))
    isAccum(x6519_d0_b3) = false
    bufferDepthOf(x6519_d0_b3) = 1
    staticDimsOf(x6519_d0_b3) = List(2, 128, 4, 128)
    val x6520 = withCtrl(x6946) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x6520").srcCtx("DataGenerator.scala:77:8") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x6521 = withCtrl(x6946) { Counter(min=Const(0), max=Const(128), step=Const(1), par=1).name("x6521").srcCtx("DataGenerator.scala:77:8") } // CounterNew(Const(0),Const(128),Const(1),Const(1))
    val x6522 = withCtrl(x6946) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x6522").srcCtx("DataGenerator.scala:77:8") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x6523 = withCtrl(x6946) { CounterChain(List(x6520,x6521,x6522)).name("x6523").srcCtx("DataGenerator.scala:77:8") } // CounterChainNew(List(x6520, x6521, x6522))
    val x6555 = withCtrl(x6946) { LoopController(style=StreamPipe, level=OuterControl, cchain=x6523).name("x6555").srcCtx("DataGenerator.scala:77:8") } // UnrolledForeach(List(Const(true)),x6523,Block(Const(())),List(List(b3584), List(b3585), List(b3586)),List(List(b3587), List(b3588), List(b3589)))
    val b3584 = withCtrl(x6555) { CounterIter(x6520, Some(0)).name("b3584") } // b3584
    val b3587 = withCtrl(x6555) { Const(true).name("b3587") } // b3587
    val b3585 = withCtrl(x6555) { CounterIter(x6521, Some(0)).name("b3585") } // b3585
    val b3588 = withCtrl(x6555) { Const(true).name("b3588") } // b3588
    val b3586 = withCtrl(x6555) { CounterIter(x6522, Some(0)).name("b3586") } // b3586
    val b3589 = withCtrl(x6555) { Const(true).name("b3589") } // b3589
    val b6969 = withCtrl(x6555) { StreamOut(field="offset").name("b6969").srcCtx("DataGenerator.scala:77:8") } // x6524 = StreamOutNew(BurstCmdBus)
    isAccum(b6969) = false
    bufferDepthOf(b6969) = 1
    val b6970 = withCtrl(x6555) { StreamOut(field="size").name("b6970").srcCtx("DataGenerator.scala:77:8") } // x6524 = StreamOutNew(BurstCmdBus)
    isAccum(b6970) = false
    bufferDepthOf(b6970) = 1
    val x6525 = withCtrl(x6555) { StreamIn(field="data").name("x6525").srcCtx("DataGenerator.scala:77:8") } // x6525 = StreamInNew(BurstDataBus())
    isAccum(x6525) = false
    bufferDepthOf(x6525) = 1
    val x6544 = withCtrl(x6555) { UnitController(style=SeqPipe, level=InnerControl).name("x6544").srcCtx("DataGenerator.scala:77:8") } // UnitPipe(List(b3587, b3588, b3589),Block(x6543))
    val x6526 = withCtrl(x6544) { b3584 } // FixConvert(b3584,TRUE,_32,_0) (Same Type. No op)
    val x6527 = withCtrl(x6544) { OpDef(op=FixSla, inputs=List(x6526, Const(16))).name("x6527").srcCtx("DataGenerator.scala:77:8") } // FixLsh(x6526,Const(16))
    val x6528 = withCtrl(x6544) { b3585 } // FixConvert(b3585,TRUE,_32,_0) (Same Type. No op)
    val x6529 = withCtrl(x6544) { OpDef(op=FixSla, inputs=List(x6528, Const(9))).name("x6529").srcCtx("DataGenerator.scala:77:8") } // FixLsh(x6528,Const(9))
    val x6530 = withCtrl(x6544) { b3586 } // FixConvert(b3586,TRUE,_32,_0) (Same Type. No op)
    val x6531 = withCtrl(x6544) { OpDef(op=FixSla, inputs=List(x6530, Const(7))).name("x6531").srcCtx("DataGenerator.scala:77:8") } // FixLsh(x6530,Const(7))
    val x6532 = withCtrl(x6544) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x6533 = withCtrl(x6544) { OpDef(op=FixAdd, inputs=List(x6527, x6529)).name("x6533").srcCtx("DataGenerator.scala:77:8") } // FixAdd(x6527,x6529)
    val x6534 = withCtrl(x6544) { OpDef(op=FixAdd, inputs=List(x6531, x6532)).name("x6534").srcCtx("DataGenerator.scala:77:8") } // FixAdd(x6531,x6532)
    val x6535 = withCtrl(x6544) { OpDef(op=FixAdd, inputs=List(x6533, x6534)).name("x6535").srcCtx("DataGenerator.scala:77:8") } // FixAdd(x6533,x6534)
    val x6536 = withCtrl(x6544) { OpDef(op=FixSla, inputs=List(x6535, Const(2))).name("x6536").srcCtx("DataGenerator.scala:77:8") } // FixLsh(x6535,Const(2))
    val x6537 = withCtrl(x6544) { x6536 } // FixConvert(x6536,TRUE,_64,_0)
    val x6538 = withCtrl(x6544) { DramAddress(x6408).name("x6538").srcCtx("DataGenerator.scala:77:8") } // GetDRAMAddress(x6408)
    val x6540_x6539 = withCtrl(x6544) { OpDef(op=FixAdd, inputs=List(x6537, x6538)).name("x6540_x6539").srcCtx("DataGenerator.scala:77:8") } // FixAdd(x6537,x6538)
    // x6540 = SimpleStruct(ArrayBuffer((offset,x6539), (size,Const(512)), (isLoad,Const(true))))
    val x6541 = withCtrl(x6544) { OpDef(op=BitAnd, inputs=List(b3587, b3588)).name("x6541").srcCtx("UnrollingBase.scala:28:66") } // And(b3587,b3588)
    val x6542 = withCtrl(x6544) { OpDef(op=BitAnd, inputs=List(x6541, b3589)).name("x6542").srcCtx("UnrollingBase.scala:28:66") } // And(x6541,b3589)
    val x6543_b6971_b6969 = withCtrl(x6544) { WriteMem(b6969, x6540_x6539).name("x6543_b6971_b6969").srcCtx("DataGenerator.scala:77:8") } // StreamWrite(x6524,x6540,x6542)
    val x6543_b6972_b6970 = withCtrl(x6544) { WriteMem(b6970, Const(512)).name("x6543_b6972_b6970").srcCtx("DataGenerator.scala:77:8") } // StreamWrite(x6524,x6540,x6542)
    val x6545 = withCtrl(x6555) { FringeDenseLoad(dram=List(x6408), cmdStream=List(b6969, b6970), dataStream=List(x6525)).name("x6545").srcCtx("DataGenerator.scala:77:8") } // FringeDenseLoad(x6408,x6524,x6525)
    val x6546 = withCtrl(x6555) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6546").srcCtx("DataGenerator.scala:77:8") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6547 = withCtrl(x6555) { CounterChain(List(x6546)).name("x6547").srcCtx("DataGenerator.scala:77:8") } // CounterChainNew(List(x6546))
    val x6554 = withCtrl(x6555) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6547).name("x6554").srcCtx("DataGenerator.scala:77:8") } // UnrolledForeach(List(b3587, b3588, b3589),x6547,Block(Const(())),List(List(b3614)),List(List(b3615)))
    val b3614 = withCtrl(x6554) { CounterIter(x6546, None).name("b3614") } // b3614
    val b3615 = withCtrl(x6554) { Const(true).name("b3615") } // b3615
    val x6548 = withCtrl(x6554) { OpDef(op=BitAnd, inputs=List(b3615, b3587)).name("x6548").srcCtx("UnrollingBase.scala:28:66") } // And(b3615,b3587)
    val x6549 = withCtrl(x6554) { OpDef(op=BitAnd, inputs=List(b3588, b3589)).name("x6549").srcCtx("UnrollingBase.scala:28:66") } // And(b3588,b3589)
    val x6550 = withCtrl(x6554) { OpDef(op=BitAnd, inputs=List(x6548, x6549)).name("x6550").srcCtx("UnrollingBase.scala:28:66") } // And(x6548,x6549)
    val x6551_x6551 = withCtrl(x6554) { ReadMem(x6525).name("x6551_x6551").srcCtx("DataGenerator.scala:77:8") } // ParStreamRead(x6525,List(x6550))
    val x6552_x6552 = withCtrl(x6554) { x6551_x6551 } // VectorApply(x6551,0)
    val x6553 = withCtrl(x6554) { StoreBanks(List(List(x6519_d0_b0, x6519_d0_b1, x6519_d0_b2, x6519_d0_b3)), List(b3584, b3585, b3586, b3614), x6552_x6552).name("x6553").srcCtx("DataGenerator.scala:77:8") } // ParSRAMStore(x6519,List(List(b3584, b3585, b3586, b3614)),List(x6552),List(x6550))
    val x6556_d0_b0 = withCtrl(x6946) { SRAM(size=32768, banking=Strided(banks=16, stride=1)).name("x6556_d0_b0").srcCtx("DataGenerator.scala:76:21:wh") } // x6556 = SRAMNew(ArrayBuffer(Const(2), Const(128), Const(4), Const(128)))
    isAccum(x6556_d0_b0) = false
    bufferDepthOf(x6556_d0_b0) = 1
    staticDimsOf(x6556_d0_b0) = List(2, 128, 4, 128)
    val x6556_d0_b1 = withCtrl(x6946) { SRAM(size=32768, banking=Strided(banks=16, stride=1)).name("x6556_d0_b1").srcCtx("DataGenerator.scala:76:21:wh") } // x6556 = SRAMNew(ArrayBuffer(Const(2), Const(128), Const(4), Const(128)))
    isAccum(x6556_d0_b1) = false
    bufferDepthOf(x6556_d0_b1) = 1
    staticDimsOf(x6556_d0_b1) = List(2, 128, 4, 128)
    val x6556_d0_b2 = withCtrl(x6946) { SRAM(size=32768, banking=Strided(banks=16, stride=1)).name("x6556_d0_b2").srcCtx("DataGenerator.scala:76:21:wh") } // x6556 = SRAMNew(ArrayBuffer(Const(2), Const(128), Const(4), Const(128)))
    isAccum(x6556_d0_b2) = false
    bufferDepthOf(x6556_d0_b2) = 1
    staticDimsOf(x6556_d0_b2) = List(2, 128, 4, 128)
    val x6556_d0_b3 = withCtrl(x6946) { SRAM(size=32768, banking=Strided(banks=16, stride=1)).name("x6556_d0_b3").srcCtx("DataGenerator.scala:76:21:wh") } // x6556 = SRAMNew(ArrayBuffer(Const(2), Const(128), Const(4), Const(128)))
    isAccum(x6556_d0_b3) = false
    bufferDepthOf(x6556_d0_b3) = 1
    staticDimsOf(x6556_d0_b3) = List(2, 128, 4, 128)
    val x6557 = withCtrl(x6946) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x6557").srcCtx("DataGenerator.scala:77:8") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x6558 = withCtrl(x6946) { Counter(min=Const(0), max=Const(128), step=Const(1), par=1).name("x6558").srcCtx("DataGenerator.scala:77:8") } // CounterNew(Const(0),Const(128),Const(1),Const(1))
    val x6559 = withCtrl(x6946) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x6559").srcCtx("DataGenerator.scala:77:8") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x6560 = withCtrl(x6946) { CounterChain(List(x6557,x6558,x6559)).name("x6560").srcCtx("DataGenerator.scala:77:8") } // CounterChainNew(List(x6557, x6558, x6559))
    val x6592 = withCtrl(x6946) { LoopController(style=StreamPipe, level=OuterControl, cchain=x6560).name("x6592").srcCtx("DataGenerator.scala:77:8") } // UnrolledForeach(List(Const(true)),x6560,Block(Const(())),List(List(b3629), List(b3630), List(b3631)),List(List(b3632), List(b3633), List(b3634)))
    val b3629 = withCtrl(x6592) { CounterIter(x6557, Some(0)).name("b3629") } // b3629
    val b3632 = withCtrl(x6592) { Const(true).name("b3632") } // b3632
    val b3630 = withCtrl(x6592) { CounterIter(x6558, Some(0)).name("b3630") } // b3630
    val b3633 = withCtrl(x6592) { Const(true).name("b3633") } // b3633
    val b3631 = withCtrl(x6592) { CounterIter(x6559, Some(0)).name("b3631") } // b3631
    val b3634 = withCtrl(x6592) { Const(true).name("b3634") } // b3634
    val b6973 = withCtrl(x6592) { StreamOut(field="offset").name("b6973").srcCtx("DataGenerator.scala:77:8") } // x6561 = StreamOutNew(BurstCmdBus)
    isAccum(b6973) = false
    bufferDepthOf(b6973) = 1
    val b6974 = withCtrl(x6592) { StreamOut(field="size").name("b6974").srcCtx("DataGenerator.scala:77:8") } // x6561 = StreamOutNew(BurstCmdBus)
    isAccum(b6974) = false
    bufferDepthOf(b6974) = 1
    val x6562 = withCtrl(x6592) { StreamIn(field="data").name("x6562").srcCtx("DataGenerator.scala:77:8") } // x6562 = StreamInNew(BurstDataBus())
    isAccum(x6562) = false
    bufferDepthOf(x6562) = 1
    val x6581 = withCtrl(x6592) { UnitController(style=SeqPipe, level=InnerControl).name("x6581").srcCtx("DataGenerator.scala:77:8") } // UnitPipe(List(b3632, b3633, b3634),Block(x6580))
    val x6563 = withCtrl(x6581) { b3629 } // FixConvert(b3629,TRUE,_32,_0) (Same Type. No op)
    val x6564 = withCtrl(x6581) { OpDef(op=FixSla, inputs=List(x6563, Const(16))).name("x6564").srcCtx("DataGenerator.scala:77:8") } // FixLsh(x6563,Const(16))
    val x6565 = withCtrl(x6581) { b3630 } // FixConvert(b3630,TRUE,_32,_0) (Same Type. No op)
    val x6566 = withCtrl(x6581) { OpDef(op=FixSla, inputs=List(x6565, Const(9))).name("x6566").srcCtx("DataGenerator.scala:77:8") } // FixLsh(x6565,Const(9))
    val x6567 = withCtrl(x6581) { b3631 } // FixConvert(b3631,TRUE,_32,_0) (Same Type. No op)
    val x6568 = withCtrl(x6581) { OpDef(op=FixSla, inputs=List(x6567, Const(7))).name("x6568").srcCtx("DataGenerator.scala:77:8") } // FixLsh(x6567,Const(7))
    val x6569 = withCtrl(x6581) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x6570 = withCtrl(x6581) { OpDef(op=FixAdd, inputs=List(x6564, x6566)).name("x6570").srcCtx("DataGenerator.scala:77:8") } // FixAdd(x6564,x6566)
    val x6571 = withCtrl(x6581) { OpDef(op=FixAdd, inputs=List(x6568, x6569)).name("x6571").srcCtx("DataGenerator.scala:77:8") } // FixAdd(x6568,x6569)
    val x6572 = withCtrl(x6581) { OpDef(op=FixAdd, inputs=List(x6570, x6571)).name("x6572").srcCtx("DataGenerator.scala:77:8") } // FixAdd(x6570,x6571)
    val x6573 = withCtrl(x6581) { OpDef(op=FixSla, inputs=List(x6572, Const(2))).name("x6573").srcCtx("DataGenerator.scala:77:8") } // FixLsh(x6572,Const(2))
    val x6574 = withCtrl(x6581) { x6573 } // FixConvert(x6573,TRUE,_64,_0)
    val x6575 = withCtrl(x6581) { DramAddress(x6421).name("x6575").srcCtx("DataGenerator.scala:77:8") } // GetDRAMAddress(x6421)
    val x6577_x6576 = withCtrl(x6581) { OpDef(op=FixAdd, inputs=List(x6574, x6575)).name("x6577_x6576").srcCtx("DataGenerator.scala:77:8") } // FixAdd(x6574,x6575)
    // x6577 = SimpleStruct(ArrayBuffer((offset,x6576), (size,Const(512)), (isLoad,Const(true))))
    val x6578 = withCtrl(x6581) { OpDef(op=BitAnd, inputs=List(b3632, b3633)).name("x6578").srcCtx("UnrollingBase.scala:28:66") } // And(b3632,b3633)
    val x6579 = withCtrl(x6581) { OpDef(op=BitAnd, inputs=List(x6578, b3634)).name("x6579").srcCtx("UnrollingBase.scala:28:66") } // And(x6578,b3634)
    val x6580_b6975_b6973 = withCtrl(x6581) { WriteMem(b6973, x6577_x6576).name("x6580_b6975_b6973").srcCtx("DataGenerator.scala:77:8") } // StreamWrite(x6561,x6577,x6579)
    val x6580_b6976_b6974 = withCtrl(x6581) { WriteMem(b6974, Const(512)).name("x6580_b6976_b6974").srcCtx("DataGenerator.scala:77:8") } // StreamWrite(x6561,x6577,x6579)
    val x6582 = withCtrl(x6592) { FringeDenseLoad(dram=List(x6421), cmdStream=List(b6973, b6974), dataStream=List(x6562)).name("x6582").srcCtx("DataGenerator.scala:77:8") } // FringeDenseLoad(x6421,x6561,x6562)
    val x6583 = withCtrl(x6592) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6583").srcCtx("DataGenerator.scala:77:8") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6584 = withCtrl(x6592) { CounterChain(List(x6583)).name("x6584").srcCtx("DataGenerator.scala:77:8") } // CounterChainNew(List(x6583))
    val x6591 = withCtrl(x6592) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6584).name("x6591").srcCtx("DataGenerator.scala:77:8") } // UnrolledForeach(List(b3632, b3633, b3634),x6584,Block(Const(())),List(List(b3659)),List(List(b3660)))
    val b3659 = withCtrl(x6591) { CounterIter(x6583, None).name("b3659") } // b3659
    val b3660 = withCtrl(x6591) { Const(true).name("b3660") } // b3660
    val x6585 = withCtrl(x6591) { OpDef(op=BitAnd, inputs=List(b3660, b3632)).name("x6585").srcCtx("UnrollingBase.scala:28:66") } // And(b3660,b3632)
    val x6586 = withCtrl(x6591) { OpDef(op=BitAnd, inputs=List(b3633, b3634)).name("x6586").srcCtx("UnrollingBase.scala:28:66") } // And(b3633,b3634)
    val x6587 = withCtrl(x6591) { OpDef(op=BitAnd, inputs=List(x6585, x6586)).name("x6587").srcCtx("UnrollingBase.scala:28:66") } // And(x6585,x6586)
    val x6588_x6588 = withCtrl(x6591) { ReadMem(x6562).name("x6588_x6588").srcCtx("DataGenerator.scala:77:8") } // ParStreamRead(x6562,List(x6587))
    val x6589_x6589 = withCtrl(x6591) { x6588_x6588 } // VectorApply(x6588,0)
    val x6590 = withCtrl(x6591) { StoreBanks(List(List(x6556_d0_b0, x6556_d0_b1, x6556_d0_b2, x6556_d0_b3)), List(b3629, b3630, b3631, b3659), x6589_x6589).name("x6590").srcCtx("DataGenerator.scala:77:8") } // ParSRAMStore(x6556,List(List(b3629, b3630, b3631, b3659)),List(x6589),List(x6587))
    val x6593_d0_b0 = withCtrl(x6946) { SRAM(size=256, banking=Strided(banks=16, stride=1)).name("x6593_d0_b0").srcCtx("DataGenerator.scala:59:21:b") } // x6593 = SRAMNew(ArrayBuffer(Const(2), Const(4), Const(128)))
    isAccum(x6593_d0_b0) = false
    bufferDepthOf(x6593_d0_b0) = 1
    staticDimsOf(x6593_d0_b0) = List(2, 4, 128)
    val x6593_d0_b1 = withCtrl(x6946) { SRAM(size=256, banking=Strided(banks=16, stride=1)).name("x6593_d0_b1").srcCtx("DataGenerator.scala:59:21:b") } // x6593 = SRAMNew(ArrayBuffer(Const(2), Const(4), Const(128)))
    isAccum(x6593_d0_b1) = false
    bufferDepthOf(x6593_d0_b1) = 1
    staticDimsOf(x6593_d0_b1) = List(2, 4, 128)
    val x6593_d0_b2 = withCtrl(x6946) { SRAM(size=256, banking=Strided(banks=16, stride=1)).name("x6593_d0_b2").srcCtx("DataGenerator.scala:59:21:b") } // x6593 = SRAMNew(ArrayBuffer(Const(2), Const(4), Const(128)))
    isAccum(x6593_d0_b2) = false
    bufferDepthOf(x6593_d0_b2) = 1
    staticDimsOf(x6593_d0_b2) = List(2, 4, 128)
    val x6593_d0_b3 = withCtrl(x6946) { SRAM(size=256, banking=Strided(banks=16, stride=1)).name("x6593_d0_b3").srcCtx("DataGenerator.scala:59:21:b") } // x6593 = SRAMNew(ArrayBuffer(Const(2), Const(4), Const(128)))
    isAccum(x6593_d0_b3) = false
    bufferDepthOf(x6593_d0_b3) = 1
    staticDimsOf(x6593_d0_b3) = List(2, 4, 128)
    val x6594 = withCtrl(x6946) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x6594").srcCtx("DataGenerator.scala:60:8") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x6595 = withCtrl(x6946) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x6595").srcCtx("DataGenerator.scala:60:8") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x6596 = withCtrl(x6946) { CounterChain(List(x6594,x6595)).name("x6596").srcCtx("DataGenerator.scala:60:8") } // CounterChainNew(List(x6594, x6595))
    val x6623 = withCtrl(x6946) { LoopController(style=StreamPipe, level=OuterControl, cchain=x6596).name("x6623").srcCtx("DataGenerator.scala:60:8") } // UnrolledForeach(List(Const(true)),x6596,Block(Const(())),List(List(b3673), List(b3674)),List(List(b3675), List(b3676)))
    val b3673 = withCtrl(x6623) { CounterIter(x6594, Some(0)).name("b3673") } // b3673
    val b3675 = withCtrl(x6623) { Const(true).name("b3675") } // b3675
    val b3674 = withCtrl(x6623) { CounterIter(x6595, Some(0)).name("b3674") } // b3674
    val b3676 = withCtrl(x6623) { Const(true).name("b3676") } // b3676
    val b6977 = withCtrl(x6623) { StreamOut(field="offset").name("b6977").srcCtx("DataGenerator.scala:60:8") } // x6597 = StreamOutNew(BurstCmdBus)
    isAccum(b6977) = false
    bufferDepthOf(b6977) = 1
    val b6978 = withCtrl(x6623) { StreamOut(field="size").name("b6978").srcCtx("DataGenerator.scala:60:8") } // x6597 = StreamOutNew(BurstCmdBus)
    isAccum(b6978) = false
    bufferDepthOf(b6978) = 1
    val x6598 = withCtrl(x6623) { StreamIn(field="data").name("x6598").srcCtx("DataGenerator.scala:60:8") } // x6598 = StreamInNew(BurstDataBus())
    isAccum(x6598) = false
    bufferDepthOf(x6598) = 1
    val x6613 = withCtrl(x6623) { UnitController(style=SeqPipe, level=InnerControl).name("x6613").srcCtx("DataGenerator.scala:60:8") } // UnitPipe(List(b3675, b3676),Block(x6612))
    val x6599 = withCtrl(x6613) { b3673 } // FixConvert(b3673,TRUE,_32,_0) (Same Type. No op)
    val x6600 = withCtrl(x6613) { OpDef(op=FixSla, inputs=List(x6599, Const(9))).name("x6600").srcCtx("DataGenerator.scala:60:8") } // FixLsh(x6599,Const(9))
    val x6601 = withCtrl(x6613) { b3674 } // FixConvert(b3674,TRUE,_32,_0) (Same Type. No op)
    val x6602 = withCtrl(x6613) { OpDef(op=FixSla, inputs=List(x6601, Const(7))).name("x6602").srcCtx("DataGenerator.scala:60:8") } // FixLsh(x6601,Const(7))
    val x6603 = withCtrl(x6613) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x6604 = withCtrl(x6613) { OpDef(op=FixAdd, inputs=List(x6600, x6602)).name("x6604").srcCtx("DataGenerator.scala:60:8") } // FixAdd(x6600,x6602)
    val x6605 = withCtrl(x6613) { OpDef(op=FixAdd, inputs=List(x6604, x6603)).name("x6605").srcCtx("DataGenerator.scala:60:8") } // FixAdd(x6604,x6603)
    val x6606 = withCtrl(x6613) { OpDef(op=FixSla, inputs=List(x6605, Const(2))).name("x6606").srcCtx("DataGenerator.scala:60:8") } // FixLsh(x6605,Const(2))
    val x6607 = withCtrl(x6613) { x6606 } // FixConvert(x6606,TRUE,_64,_0)
    val x6608 = withCtrl(x6613) { DramAddress(x6434).name("x6608").srcCtx("DataGenerator.scala:60:8") } // GetDRAMAddress(x6434)
    val x6610_x6609 = withCtrl(x6613) { OpDef(op=FixAdd, inputs=List(x6607, x6608)).name("x6610_x6609").srcCtx("DataGenerator.scala:60:8") } // FixAdd(x6607,x6608)
    // x6610 = SimpleStruct(ArrayBuffer((offset,x6609), (size,Const(512)), (isLoad,Const(true))))
    val x6611 = withCtrl(x6613) { OpDef(op=BitAnd, inputs=List(b3675, b3676)).name("x6611").srcCtx("UnrollingBase.scala:28:66") } // And(b3675,b3676)
    val x6612_b6979_b6977 = withCtrl(x6613) { WriteMem(b6977, x6610_x6609).name("x6612_b6979_b6977").srcCtx("DataGenerator.scala:60:8") } // StreamWrite(x6597,x6610,x6611)
    val x6612_b6980_b6978 = withCtrl(x6613) { WriteMem(b6978, Const(512)).name("x6612_b6980_b6978").srcCtx("DataGenerator.scala:60:8") } // StreamWrite(x6597,x6610,x6611)
    val x6614 = withCtrl(x6623) { FringeDenseLoad(dram=List(x6434), cmdStream=List(b6977, b6978), dataStream=List(x6598)).name("x6614").srcCtx("DataGenerator.scala:60:8") } // FringeDenseLoad(x6434,x6597,x6598)
    val x6615 = withCtrl(x6623) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6615").srcCtx("DataGenerator.scala:60:8") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6616 = withCtrl(x6623) { CounterChain(List(x6615)).name("x6616").srcCtx("DataGenerator.scala:60:8") } // CounterChainNew(List(x6615))
    val x6622 = withCtrl(x6623) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6616).name("x6622").srcCtx("DataGenerator.scala:60:8") } // UnrolledForeach(List(b3675, b3676),x6616,Block(Const(())),List(List(b3697)),List(List(b3698)))
    val b3697 = withCtrl(x6622) { CounterIter(x6615, None).name("b3697") } // b3697
    val b3698 = withCtrl(x6622) { Const(true).name("b3698") } // b3698
    val x6617 = withCtrl(x6622) { OpDef(op=BitAnd, inputs=List(b3698, b3675)).name("x6617").srcCtx("UnrollingBase.scala:28:66") } // And(b3698,b3675)
    val x6618 = withCtrl(x6622) { OpDef(op=BitAnd, inputs=List(x6617, b3676)).name("x6618").srcCtx("UnrollingBase.scala:28:66") } // And(x6617,b3676)
    val x6619_x6619 = withCtrl(x6622) { ReadMem(x6598).name("x6619_x6619").srcCtx("DataGenerator.scala:60:8") } // ParStreamRead(x6598,List(x6618))
    val x6620_x6620 = withCtrl(x6622) { x6619_x6619 } // VectorApply(x6619,0)
    val x6621 = withCtrl(x6622) { StoreBanks(List(List(x6593_d0_b0, x6593_d0_b1, x6593_d0_b2, x6593_d0_b3)), List(b3673, b3674, b3697), x6620_x6620).name("x6621").srcCtx("DataGenerator.scala:60:8") } // ParSRAMStore(x6593,List(List(b3673, b3674, b3697)),List(x6620),List(x6618))
    val x6624 = withCtrl(x6946) { Counter(min=Const(0), max=Const(8), step=Const(1), par=1).name("x6624").srcCtx("LSTMPipe.scala:42:28") } // CounterNew(Const(0),Const(8),Const(1),Const(1))
    val x6625 = withCtrl(x6946) { CounterChain(List(x6624)).name("x6625").srcCtx("LSTMPipe.scala:42:41") } // CounterChainNew(List(x6624))
    val x6917 = withCtrl(x6946) { LoopController(style=MetaPipe, level=OuterControl, cchain=x6625).name("x6917").srcCtx("LSTMPipe.scala:42:41") } // UnrolledForeach(List(Const(true)),x6625,Block(Const(())),List(List(b3708)),List(List(b3709)))
    val b3708 = withCtrl(x6917) { CounterIter(x6624, Some(0)).name("b3708") } // b3708
    val b3709 = withCtrl(x6917) { Const(true).name("b3709") } // b3709
    val x6626 = withCtrl(x6917) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x6626").srcCtx("LSTMPipe.scala:43:31") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x6627 = withCtrl(x6917) { CounterChain(List(x6626)).name("x6627").srcCtx("LSTMPipe.scala:43:45") } // CounterChainNew(List(x6626))
    val x6916 = withCtrl(x6917) { LoopController(style=MetaPipe, level=OuterControl, cchain=x6627).name("x6916").srcCtx("LSTMPipe.scala:43:45") } // UnrolledForeach(List(b3709),x6627,Block(Const(())),List(List(b3712)),List(List(b3713)))
    val b3712 = withCtrl(x6916) { CounterIter(x6626, Some(0)).name("b3712") } // b3712
    val b3713 = withCtrl(x6916) { Const(true).name("b3713") } // b3713
    val x6628_d0_b0 = withCtrl(x6916) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x6628_d0_b0").srcCtx("LSTMPipe.scala:46:34:reduceMem") } // x6628 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x6628_d0_b0) = false
    bufferDepthOf(x6628_d0_b0) = 2
    staticDimsOf(x6628_d0_b0) = List(4, 128)
    val x6628_d0_b1 = withCtrl(x6916) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x6628_d0_b1").srcCtx("LSTMPipe.scala:46:34:reduceMem") } // x6628 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x6628_d0_b1) = false
    bufferDepthOf(x6628_d0_b1) = 2
    staticDimsOf(x6628_d0_b1) = List(4, 128)
    val x6628_d0_b2 = withCtrl(x6916) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x6628_d0_b2").srcCtx("LSTMPipe.scala:46:34:reduceMem") } // x6628 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x6628_d0_b2) = false
    bufferDepthOf(x6628_d0_b2) = 2
    staticDimsOf(x6628_d0_b2) = List(4, 128)
    val x6628_d0_b3 = withCtrl(x6916) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x6628_d0_b3").srcCtx("LSTMPipe.scala:46:34:reduceMem") } // x6628 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x6628_d0_b3) = false
    bufferDepthOf(x6628_d0_b3) = 2
    staticDimsOf(x6628_d0_b3) = List(4, 128)
    val x6628_d1_b0 = withCtrl(x6916) { SRAM(size=512, banking=Strided(banks=1, stride=128)).name("x6628_d1_b0").srcCtx("LSTMPipe.scala:46:34:reduceMem") } // x6628 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x6628_d1_b0) = true
    bufferDepthOf(x6628_d1_b0) = 1
    staticDimsOf(x6628_d1_b0) = List(4, 128)
    val x6629_d0_b0 = withCtrl(x6916) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x6629_d0_b0").srcCtx("LSTMPipe.scala:47:32:foldMem") } // x6629 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x6629_d0_b0) = false
    bufferDepthOf(x6629_d0_b0) = 2
    staticDimsOf(x6629_d0_b0) = List(4, 128)
    val x6629_d0_b1 = withCtrl(x6916) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x6629_d0_b1").srcCtx("LSTMPipe.scala:47:32:foldMem") } // x6629 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x6629_d0_b1) = false
    bufferDepthOf(x6629_d0_b1) = 2
    staticDimsOf(x6629_d0_b1) = List(4, 128)
    val x6629_d0_b2 = withCtrl(x6916) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x6629_d0_b2").srcCtx("LSTMPipe.scala:47:32:foldMem") } // x6629 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x6629_d0_b2) = false
    bufferDepthOf(x6629_d0_b2) = 2
    staticDimsOf(x6629_d0_b2) = List(4, 128)
    val x6629_d0_b3 = withCtrl(x6916) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x6629_d0_b3").srcCtx("LSTMPipe.scala:47:32:foldMem") } // x6629 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x6629_d0_b3) = false
    bufferDepthOf(x6629_d0_b3) = 2
    staticDimsOf(x6629_d0_b3) = List(4, 128)
    val x6630 = withCtrl(x6916) { Counter(min=Const(0), max=Const(128), step=Const(1), par=1).name("x6630").srcCtx("LSTMPipe.scala:49:79") } // CounterNew(Const(0),Const(128),Const(1),Const(1))
    val x6631 = withCtrl(x6916) { CounterChain(List(x6630)).name("x6631").srcCtx("LSTMPipe.scala:63:12") } // CounterChainNew(List(x6630))
    val x6732 = withCtrl(x6916) { LoopController(style=MetaPipe, level=OuterControl, cchain=x6631).name("x6732").srcCtx("LSTMPipe.scala:63:12") } // UnrolledReduce(List(b3713, b3709),x6631,x6628,Block((x6628) => Const(())),List(List(b3721)),List(List(b3722)))
    val b3721 = withCtrl(x6732) { CounterIter(x6630, Some(0)).name("b3721") } // b3721
    val b3722 = withCtrl(x6732) { Const(true).name("b3722") } // b3722
    val x6632_d0_b0 = withCtrl(x6732) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x6632_d0_b0").srcCtx("LSTMPipe.scala:50:29:re") } // x6632 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x6632_d0_b0) = false
    bufferDepthOf(x6632_d0_b0) = 2
    staticDimsOf(x6632_d0_b0) = List(4, 128)
    val x6632_d0_b1 = withCtrl(x6732) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x6632_d0_b1").srcCtx("LSTMPipe.scala:50:29:re") } // x6632 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x6632_d0_b1) = false
    bufferDepthOf(x6632_d0_b1) = 2
    staticDimsOf(x6632_d0_b1) = List(4, 128)
    val x6632_d0_b2 = withCtrl(x6732) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x6632_d0_b2").srcCtx("LSTMPipe.scala:50:29:re") } // x6632 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x6632_d0_b2) = false
    bufferDepthOf(x6632_d0_b2) = 2
    staticDimsOf(x6632_d0_b2) = List(4, 128)
    val x6632_d0_b3 = withCtrl(x6732) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x6632_d0_b3").srcCtx("LSTMPipe.scala:50:29:re") } // x6632 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x6632_d0_b3) = false
    bufferDepthOf(x6632_d0_b3) = 2
    staticDimsOf(x6632_d0_b3) = List(4, 128)
    val x6633_d0 = withCtrl(x6732) { Reg(init=Some(0.0)).name("x6633_d0").srcCtx("LSTMPipe.scala:63:12") } // x6633 = RegNew(Const(0))
    isAccum(x6633_d0) = false
    bufferDepthOf(x6633_d0) = 2
    val x6633_d1 = withCtrl(x6732) { Reg(init=Some(0.0)).name("x6633_d1").srcCtx("LSTMPipe.scala:63:12") } // x6633 = RegNew(Const(0))
    isAccum(x6633_d1) = false
    bufferDepthOf(x6633_d1) = 2
    val x6633_d2 = withCtrl(x6732) { Reg(init=Some(0.0)).name("x6633_d2").srcCtx("LSTMPipe.scala:63:12") } // x6633 = RegNew(Const(0))
    isAccum(x6633_d2) = false
    bufferDepthOf(x6633_d2) = 2
    val x6633_d3 = withCtrl(x6732) { Reg(init=Some(0.0)).name("x6633_d3").srcCtx("LSTMPipe.scala:63:12") } // x6633 = RegNew(Const(0))
    isAccum(x6633_d3) = false
    bufferDepthOf(x6633_d3) = 2
    val x6634_d0 = withCtrl(x6732) { Reg(init=Some(0.0)).name("x6634_d0").srcCtx("LSTMPipe.scala:63:12") } // x6634 = RegNew(Const(0))
    isAccum(x6634_d0) = false
    bufferDepthOf(x6634_d0) = 2
    val x6634_d1 = withCtrl(x6732) { Reg(init=Some(0.0)).name("x6634_d1").srcCtx("LSTMPipe.scala:63:12") } // x6634 = RegNew(Const(0))
    isAccum(x6634_d1) = false
    bufferDepthOf(x6634_d1) = 2
    val x6634_d2 = withCtrl(x6732) { Reg(init=Some(0.0)).name("x6634_d2").srcCtx("LSTMPipe.scala:63:12") } // x6634 = RegNew(Const(0))
    isAccum(x6634_d2) = false
    bufferDepthOf(x6634_d2) = 2
    val x6634_d3 = withCtrl(x6732) { Reg(init=Some(0.0)).name("x6634_d3").srcCtx("LSTMPipe.scala:63:12") } // x6634 = RegNew(Const(0))
    isAccum(x6634_d3) = false
    bufferDepthOf(x6634_d3) = 2
    val x6641 = withCtrl(x6732) { UnitController(style=SeqPipe, level=InnerControl).name("x6641").srcCtx("LSTMPipe.scala:63:12") } // UnitPipe(List(b3722, b3713, b3709),Block(Const(())))
    val x6635 = withCtrl(x6641) { OpDef(op=BitAnd, inputs=List(b3722, b3713)).name("x6635").srcCtx("UnrollingBase.scala:28:66") } // And(b3722,b3713)
    val x6636 = withCtrl(x6641) { OpDef(op=BitAnd, inputs=List(x6635, b3709)).name("x6636").srcCtx("UnrollingBase.scala:28:66") } // And(x6635,b3709)
    val x6637 = withCtrl(x6641) { LoadBanks(List(x6444_d1_b0), List(b3708, b3721)).name("x6637").srcCtx("LSTMPipe.scala:51:25:xEle") } // SRAMLoad(x6444,ArrayBuffer(Const(8), Const(128)),List(b3708, b3721),Const(0),x6636)
    val x6638 = withCtrl(x6641) { LoadBanks(List(x6494_d0_b0), List(b3712, b3721)).name("x6638").srcCtx("LSTMPipe.scala:52:25:hEle") } // SRAMLoad(x6494,ArrayBuffer(Const(2), Const(128)),List(b3712, b3721),Const(0),x6636)
    val x6639_x6633_d0 = withCtrl(x6641) { WriteMem(x6633_d0, x6637).name("x6639_x6633_d0").srcCtx("LSTMPipe.scala:63:12") } // RegWrite(x6633,x6637,x6636)
    val x6639_x6633_d1 = withCtrl(x6641) { WriteMem(x6633_d1, x6637).name("x6639_x6633_d1").srcCtx("LSTMPipe.scala:63:12") } // RegWrite(x6633,x6637,x6636)
    val x6639_x6633_d2 = withCtrl(x6641) { WriteMem(x6633_d2, x6637).name("x6639_x6633_d2").srcCtx("LSTMPipe.scala:63:12") } // RegWrite(x6633,x6637,x6636)
    val x6639_x6633_d3 = withCtrl(x6641) { WriteMem(x6633_d3, x6637).name("x6639_x6633_d3").srcCtx("LSTMPipe.scala:63:12") } // RegWrite(x6633,x6637,x6636)
    val x6640_x6634_d0 = withCtrl(x6641) { WriteMem(x6634_d0, x6638).name("x6640_x6634_d0").srcCtx("LSTMPipe.scala:63:12") } // RegWrite(x6634,x6638,x6636)
    val x6640_x6634_d1 = withCtrl(x6641) { WriteMem(x6634_d1, x6638).name("x6640_x6634_d1").srcCtx("LSTMPipe.scala:63:12") } // RegWrite(x6634,x6638,x6636)
    val x6640_x6634_d2 = withCtrl(x6641) { WriteMem(x6634_d2, x6638).name("x6640_x6634_d2").srcCtx("LSTMPipe.scala:63:12") } // RegWrite(x6634,x6638,x6636)
    val x6640_x6634_d3 = withCtrl(x6641) { WriteMem(x6634_d3, x6638).name("x6640_x6634_d3").srcCtx("LSTMPipe.scala:63:12") } // RegWrite(x6634,x6638,x6636)
    val x6642 = withCtrl(x6732) { Counter(min=Const(0), max=Const(4), step=Const(1), par=4).name("x6642").srcCtx("LSTMPipe.scala:53:34") } // CounterNew(Const(0),Const(4),Const(1),Const(4))
    val x6643 = withCtrl(x6732) { CounterChain(List(x6642)).name("x6643").srcCtx("LSTMPipe.scala:53:47") } // CounterChainNew(List(x6642))
    val x6713 = withCtrl(x6732) { LoopController(style=MetaPipe, level=OuterControl, cchain=x6643).name("x6713").srcCtx("LSTMPipe.scala:53:47") } // UnrolledForeach(List(b3722, b3713, b3709),x6643,Block(Const(())),List(List(b3735, b3736, b3737, b3738)),List(List(b3739, b3740, b3741, b3742)))
    val b3735 = withCtrl(x6713) { CounterIter(x6642, Some(0)).name("b3735") } // b3735
    val b3739 = withCtrl(x6713) { Const(true).name("b3739") } // b3739
    val b3736 = withCtrl(x6713) { CounterIter(x6642, Some(1)).name("b3736") } // b3736
    val b3740 = withCtrl(x6713) { Const(true).name("b3740") } // b3740
    val b3737 = withCtrl(x6713) { CounterIter(x6642, Some(2)).name("b3737") } // b3737
    val b3741 = withCtrl(x6713) { Const(true).name("b3741") } // b3741
    val b3738 = withCtrl(x6713) { CounterIter(x6642, Some(3)).name("b3738") } // b3738
    val b3742 = withCtrl(x6713) { Const(true).name("b3742") } // b3742
    val x6712 = withCtrl(x6713) { UnitController(style=ForkJoin, level=OuterControl).name("x6712").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b3722, b3713, b3709),Block(Const(())))
    val x6644 = withCtrl(x6712) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6644").srcCtx("LSTMPipe.scala:55:42") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6645 = withCtrl(x6712) { CounterChain(List(x6644)).name("x6645").srcCtx("LSTMPipe.scala:55:56") } // CounterChainNew(List(x6644))
    val x6660 = withCtrl(x6712) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6645).name("x6660").srcCtx("LSTMPipe.scala:55:56") } // UnrolledForeach(List(b3739, b3722, b3713, b3709),x6645,Block(Const(())),List(List(b3751)),List(List(b3752)))
    val b3751 = withCtrl(x6660) { CounterIter(x6644, None).name("b3751") } // b3751
    val b3752 = withCtrl(x6660) { Const(true).name("b3752") } // b3752
    val x6646 = withCtrl(x6660) { OpDef(op=BitAnd, inputs=List(b3752, b3739)).name("x6646").srcCtx("UnrollingBase.scala:28:66") } // And(b3752,b3739)
    val x6647 = withCtrl(x6660) { OpDef(op=BitAnd, inputs=List(b3722, b3713)).name("x6647").srcCtx("UnrollingBase.scala:28:66") } // And(b3722,b3713)
    val x6648 = withCtrl(x6660) { OpDef(op=BitAnd, inputs=List(x6646, x6647)).name("x6648").srcCtx("UnrollingBase.scala:28:66") } // And(x6646,x6647)
    val x6649 = withCtrl(x6660) { OpDef(op=BitAnd, inputs=List(x6648, b3709)).name("x6649").srcCtx("UnrollingBase.scala:28:66") } // And(x6648,b3709)
    val x6650 = withCtrl(x6660) { LoadBanks(List(x6519_d0_b0), List(b3712, b3721, b3735, b3751)).name("x6650").srcCtx("LSTMPipe.scala:56:29") } // ParSRAMLoad(x6519,List(List(b3712, b3721, b3735, b3751)),List(x6649))
    val x6651 = withCtrl(x6660) { x6650 } // VectorApply(x6650,0)
    val x6652 = withCtrl(x6660) { ReadMem(x6633_d0).name("x6652").srcCtx("LSTMPipe.scala:63:12") } // RegRead(x6633)
    val x6653 = withCtrl(x6660) { OpDef(op=FixMul, inputs=List(x6651, x6652)).name("x6653").srcCtx("LSTMPipe.scala:56:67:reX") } // FixMul(x6651,x6652)
    val x6654 = withCtrl(x6660) { LoadBanks(List(x6556_d0_b0), List(b3712, b3721, b3735, b3751)).name("x6654").srcCtx("LSTMPipe.scala:57:29") } // ParSRAMLoad(x6556,List(List(b3712, b3721, b3735, b3751)),List(x6649))
    val x6655 = withCtrl(x6660) { x6654 } // VectorApply(x6654,0)
    val x6656 = withCtrl(x6660) { ReadMem(x6634_d0).name("x6656").srcCtx("LSTMPipe.scala:63:12") } // RegRead(x6634)
    val x6657 = withCtrl(x6660) { OpDef(op=FixMul, inputs=List(x6655, x6656)).name("x6657").srcCtx("LSTMPipe.scala:57:67:reH") } // FixMul(x6655,x6656)
    val x6658 = withCtrl(x6660) { OpDef(op=FixAdd, inputs=List(x6653, x6657)).name("x6658").srcCtx("LSTMPipe.scala:58:46") } // FixAdd(x6653,x6657)
    val x6659 = withCtrl(x6660) { StoreBanks(List(List(x6632_d0_b0)), List(b3735, b3751), x6658).name("x6659").srcCtx("LSTMPipe.scala:58:40") } // ParSRAMStore(x6632,List(List(b3735, b3751)),List(x6658),List(x6649))
    val x6661 = withCtrl(x6712) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6661").srcCtx("LSTMPipe.scala:55:42") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6662 = withCtrl(x6712) { CounterChain(List(x6661)).name("x6662").srcCtx("LSTMPipe.scala:55:56") } // CounterChainNew(List(x6661))
    val x6677 = withCtrl(x6712) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6662).name("x6677").srcCtx("LSTMPipe.scala:55:56") } // UnrolledForeach(List(b3740, b3722, b3713, b3709),x6662,Block(Const(())),List(List(b3768)),List(List(b3769)))
    val b3768 = withCtrl(x6677) { CounterIter(x6661, None).name("b3768") } // b3768
    val b3769 = withCtrl(x6677) { Const(true).name("b3769") } // b3769
    val x6663 = withCtrl(x6677) { OpDef(op=BitAnd, inputs=List(b3769, b3740)).name("x6663").srcCtx("UnrollingBase.scala:28:66") } // And(b3769,b3740)
    val x6664 = withCtrl(x6677) { OpDef(op=BitAnd, inputs=List(b3722, b3713)).name("x6664").srcCtx("UnrollingBase.scala:28:66") } // And(b3722,b3713)
    val x6665 = withCtrl(x6677) { OpDef(op=BitAnd, inputs=List(x6663, x6664)).name("x6665").srcCtx("UnrollingBase.scala:28:66") } // And(x6663,x6664)
    val x6666 = withCtrl(x6677) { OpDef(op=BitAnd, inputs=List(x6665, b3709)).name("x6666").srcCtx("UnrollingBase.scala:28:66") } // And(x6665,b3709)
    val x6667 = withCtrl(x6677) { LoadBanks(List(x6519_d0_b1), List(b3712, b3721, b3736, b3768)).name("x6667").srcCtx("LSTMPipe.scala:56:29") } // ParSRAMLoad(x6519,List(List(b3712, b3721, b3736, b3768)),List(x6666))
    val x6668 = withCtrl(x6677) { x6667 } // VectorApply(x6667,0)
    val x6669 = withCtrl(x6677) { ReadMem(x6633_d1).name("x6669").srcCtx("LSTMPipe.scala:63:12") } // RegRead(x6633)
    val x6670 = withCtrl(x6677) { OpDef(op=FixMul, inputs=List(x6668, x6669)).name("x6670").srcCtx("LSTMPipe.scala:56:67:reX") } // FixMul(x6668,x6669)
    val x6671 = withCtrl(x6677) { LoadBanks(List(x6556_d0_b1), List(b3712, b3721, b3736, b3768)).name("x6671").srcCtx("LSTMPipe.scala:57:29") } // ParSRAMLoad(x6556,List(List(b3712, b3721, b3736, b3768)),List(x6666))
    val x6672 = withCtrl(x6677) { x6671 } // VectorApply(x6671,0)
    val x6673 = withCtrl(x6677) { ReadMem(x6634_d1).name("x6673").srcCtx("LSTMPipe.scala:63:12") } // RegRead(x6634)
    val x6674 = withCtrl(x6677) { OpDef(op=FixMul, inputs=List(x6672, x6673)).name("x6674").srcCtx("LSTMPipe.scala:57:67:reH") } // FixMul(x6672,x6673)
    val x6675 = withCtrl(x6677) { OpDef(op=FixAdd, inputs=List(x6670, x6674)).name("x6675").srcCtx("LSTMPipe.scala:58:46") } // FixAdd(x6670,x6674)
    val x6676 = withCtrl(x6677) { StoreBanks(List(List(x6632_d0_b1)), List(b3736, b3768), x6675).name("x6676").srcCtx("LSTMPipe.scala:58:40") } // ParSRAMStore(x6632,List(List(b3736, b3768)),List(x6675),List(x6666))
    val x6678 = withCtrl(x6712) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6678").srcCtx("LSTMPipe.scala:55:42") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6679 = withCtrl(x6712) { CounterChain(List(x6678)).name("x6679").srcCtx("LSTMPipe.scala:55:56") } // CounterChainNew(List(x6678))
    val x6694 = withCtrl(x6712) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6679).name("x6694").srcCtx("LSTMPipe.scala:55:56") } // UnrolledForeach(List(b3741, b3722, b3713, b3709),x6679,Block(Const(())),List(List(b3785)),List(List(b3786)))
    val b3785 = withCtrl(x6694) { CounterIter(x6678, None).name("b3785") } // b3785
    val b3786 = withCtrl(x6694) { Const(true).name("b3786") } // b3786
    val x6680 = withCtrl(x6694) { OpDef(op=BitAnd, inputs=List(b3786, b3741)).name("x6680").srcCtx("UnrollingBase.scala:28:66") } // And(b3786,b3741)
    val x6681 = withCtrl(x6694) { OpDef(op=BitAnd, inputs=List(b3722, b3713)).name("x6681").srcCtx("UnrollingBase.scala:28:66") } // And(b3722,b3713)
    val x6682 = withCtrl(x6694) { OpDef(op=BitAnd, inputs=List(x6680, x6681)).name("x6682").srcCtx("UnrollingBase.scala:28:66") } // And(x6680,x6681)
    val x6683 = withCtrl(x6694) { OpDef(op=BitAnd, inputs=List(x6682, b3709)).name("x6683").srcCtx("UnrollingBase.scala:28:66") } // And(x6682,b3709)
    val x6684 = withCtrl(x6694) { LoadBanks(List(x6519_d0_b2), List(b3712, b3721, b3737, b3785)).name("x6684").srcCtx("LSTMPipe.scala:56:29") } // ParSRAMLoad(x6519,List(List(b3712, b3721, b3737, b3785)),List(x6683))
    val x6685 = withCtrl(x6694) { x6684 } // VectorApply(x6684,0)
    val x6686 = withCtrl(x6694) { ReadMem(x6633_d2).name("x6686").srcCtx("LSTMPipe.scala:63:12") } // RegRead(x6633)
    val x6687 = withCtrl(x6694) { OpDef(op=FixMul, inputs=List(x6685, x6686)).name("x6687").srcCtx("LSTMPipe.scala:56:67:reX") } // FixMul(x6685,x6686)
    val x6688 = withCtrl(x6694) { LoadBanks(List(x6556_d0_b2), List(b3712, b3721, b3737, b3785)).name("x6688").srcCtx("LSTMPipe.scala:57:29") } // ParSRAMLoad(x6556,List(List(b3712, b3721, b3737, b3785)),List(x6683))
    val x6689 = withCtrl(x6694) { x6688 } // VectorApply(x6688,0)
    def split1 = {
    val x6690 = withCtrl(x6694) { ReadMem(x6634_d2).name("x6690").srcCtx("LSTMPipe.scala:63:12") } // RegRead(x6634)
    val x6691 = withCtrl(x6694) { OpDef(op=FixMul, inputs=List(x6689, x6690)).name("x6691").srcCtx("LSTMPipe.scala:57:67:reH") } // FixMul(x6689,x6690)
    val x6692 = withCtrl(x6694) { OpDef(op=FixAdd, inputs=List(x6687, x6691)).name("x6692").srcCtx("LSTMPipe.scala:58:46") } // FixAdd(x6687,x6691)
    val x6693 = withCtrl(x6694) { StoreBanks(List(List(x6632_d0_b2)), List(b3737, b3785), x6692).name("x6693").srcCtx("LSTMPipe.scala:58:40") } // ParSRAMStore(x6632,List(List(b3737, b3785)),List(x6692),List(x6683))
    val x6695 = withCtrl(x6712) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6695").srcCtx("LSTMPipe.scala:55:42") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6696 = withCtrl(x6712) { CounterChain(List(x6695)).name("x6696").srcCtx("LSTMPipe.scala:55:56") } // CounterChainNew(List(x6695))
    val x6711 = withCtrl(x6712) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6696).name("x6711").srcCtx("LSTMPipe.scala:55:56") } // UnrolledForeach(List(b3742, b3722, b3713, b3709),x6696,Block(Const(())),List(List(b3802)),List(List(b3803)))
    val b3802 = withCtrl(x6711) { CounterIter(x6695, None).name("b3802") } // b3802
    val b3803 = withCtrl(x6711) { Const(true).name("b3803") } // b3803
    val x6697 = withCtrl(x6711) { OpDef(op=BitAnd, inputs=List(b3803, b3742)).name("x6697").srcCtx("UnrollingBase.scala:28:66") } // And(b3803,b3742)
    val x6698 = withCtrl(x6711) { OpDef(op=BitAnd, inputs=List(b3722, b3713)).name("x6698").srcCtx("UnrollingBase.scala:28:66") } // And(b3722,b3713)
    val x6699 = withCtrl(x6711) { OpDef(op=BitAnd, inputs=List(x6697, x6698)).name("x6699").srcCtx("UnrollingBase.scala:28:66") } // And(x6697,x6698)
    val x6700 = withCtrl(x6711) { OpDef(op=BitAnd, inputs=List(x6699, b3709)).name("x6700").srcCtx("UnrollingBase.scala:28:66") } // And(x6699,b3709)
    val x6701 = withCtrl(x6711) { LoadBanks(List(x6519_d0_b3), List(b3712, b3721, b3738, b3802)).name("x6701").srcCtx("LSTMPipe.scala:56:29") } // ParSRAMLoad(x6519,List(List(b3712, b3721, b3738, b3802)),List(x6700))
    val x6702 = withCtrl(x6711) { x6701 } // VectorApply(x6701,0)
    val x6703 = withCtrl(x6711) { ReadMem(x6633_d3).name("x6703").srcCtx("LSTMPipe.scala:63:12") } // RegRead(x6633)
    val x6704 = withCtrl(x6711) { OpDef(op=FixMul, inputs=List(x6702, x6703)).name("x6704").srcCtx("LSTMPipe.scala:56:67:reX") } // FixMul(x6702,x6703)
    val x6705 = withCtrl(x6711) { LoadBanks(List(x6556_d0_b3), List(b3712, b3721, b3738, b3802)).name("x6705").srcCtx("LSTMPipe.scala:57:29") } // ParSRAMLoad(x6556,List(List(b3712, b3721, b3738, b3802)),List(x6700))
    val x6706 = withCtrl(x6711) { x6705 } // VectorApply(x6705,0)
    val x6707 = withCtrl(x6711) { ReadMem(x6634_d3).name("x6707").srcCtx("LSTMPipe.scala:63:12") } // RegRead(x6634)
    val x6708 = withCtrl(x6711) { OpDef(op=FixMul, inputs=List(x6706, x6707)).name("x6708").srcCtx("LSTMPipe.scala:57:67:reH") } // FixMul(x6706,x6707)
    val x6709 = withCtrl(x6711) { OpDef(op=FixAdd, inputs=List(x6704, x6708)).name("x6709").srcCtx("LSTMPipe.scala:58:46") } // FixAdd(x6704,x6708)
    val x6710 = withCtrl(x6711) { StoreBanks(List(List(x6632_d0_b3)), List(b3738, b3802), x6709).name("x6710").srcCtx("LSTMPipe.scala:58:40") } // ParSRAMStore(x6632,List(List(b3738, b3802)),List(x6709),List(x6700))
    val x6714 = withCtrl(x6732) { Counter(min=Const(0), max=Const(128), step=Const(1), par=1).name("x6714").srcCtx("LSTMPipe.scala:63:12") } // CounterNew(Const(0),Const(128),Const(1),Const(1))
    val x6715 = withCtrl(x6732) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x6715").srcCtx("LSTMPipe.scala:63:12") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x6716 = withCtrl(x6732) { CounterChain(List(x6715,x6714)).name("x6716").srcCtx("LSTMPipe.scala:63:12") } // CounterChainNew(ArrayBuffer(x6715, x6714))
    val x6731 = withCtrl(x6732) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6716).name("x6731").srcCtx("LSTMPipe.scala:63:12") } // UnrolledForeach(List(),x6716,Block(Const(())),ArrayBuffer(List(b3821), List(b3822)),ArrayBuffer(List(b3823), List(b3824)))
    val b3821 = withCtrl(x6731) { CounterIter(x6715, Some(0)).name("b3821") } // b3821
    val b3823 = withCtrl(x6731) { Const(true).name("b3823") } // b3823
    val b3822 = withCtrl(x6731) { CounterIter(x6714, None).name("b3822") } // b3822
    val b3824 = withCtrl(x6731) { Const(true).name("b3824") } // b3824
    val x6717 = withCtrl(x6731) { OpDef(op=BitAnd, inputs=List(b3823, b3824)).name("x6717").srcCtx("UnrollingBase.scala:28:66") } // And(b3823,b3824)
    val x6718 = withCtrl(x6731) { OpDef(op=BitAnd, inputs=List(b3713, b3709)).name("x6718").srcCtx("UnrollingBase.scala:28:66") } // And(b3713,b3709)
    val x6719 = withCtrl(x6731) { OpDef(op=BitAnd, inputs=List(x6717, x6718)).name("x6719").srcCtx("UnrollingBase.scala:28:66") } // And(x6717,x6718)
    val x6720 = withCtrl(x6731) { LoadBanks(List(x6632_d0_b0, x6632_d0_b1, x6632_d0_b2, x6632_d0_b3), List(b3821, b3822)).name("x6720").srcCtx("LSTMPipe.scala:63:12") } // ParSRAMLoad(x6632,List(ArrayBuffer(b3821, b3822)),List(x6719))
    val x6721 = withCtrl(x6731) { x6720 } // VectorApply(x6720,0)
    val x6722 = withCtrl(x6731) { LoadBanks(List(x6628_d1_b0), List(b3821, b3822)).name("x6722").srcCtx("LSTMPipe.scala:63:12") } // ParSRAMLoad(x6628,List(ArrayBuffer(b3821, b3822)),List(x6719))
    val x6723 = withCtrl(x6731) { x6722 } // VectorApply(x6722,0)
    val x6724 = withCtrl(x6731) { OpDef(op=BitAnd, inputs=List(b3722, b3713)).name("x6724").srcCtx("LSTMPipe.scala:63:12") } // And(b3722,b3713)
    val x6725 = withCtrl(x6731) { OpDef(op=BitAnd, inputs=List(x6724, b3709)).name("x6725").srcCtx("LSTMPipe.scala:63:12") } // And(x6724,b3709)
    val x6726 = withCtrl(x6731) { OpDef(op=BitAnd, inputs=List(x6725, x6719)).name("x6726").srcCtx("LSTMPipe.scala:63:12") } // And(x6725,x6719)
    val x6727 = withCtrl(x6731) { OpDef(op=FixEql, inputs=List(b3721, Const(0))).name("x6727").srcCtx("LSTMPipe.scala:63:12") } // FixEql(b3721,Const(0))
    val x6728 = withCtrl(x6731) { OpDef(op=FixAdd, inputs=List(x6721, x6723)).name("x6728").srcCtx("LSTMPipe.scala:63:14") } // FixAdd(x6721,x6723)
    val x6729 = withCtrl(x6731) { OpDef(op=MuxOp, inputs=List(x6727, x6721, x6728)).name("x6729").srcCtx("LSTMPipe.scala:63:12") } // Mux(x6727,x6721,x6728)
    val x6730 = withCtrl(x6731) { StoreBanks(List(List(x6628_d0_b0, x6628_d0_b1, x6628_d0_b2, x6628_d0_b3), List(x6628_d1_b0)), List(b3821, b3822), x6729).name("x6730").srcCtx("LSTMPipe.scala:63:12") } // ParSRAMStore(x6628,List(ArrayBuffer(b3821, b3822)),List(x6729),List(x6719))
    antiDepsOf(x6730)=List(x6722)
    val x6733 = withCtrl(x6916) { Counter(min=Const(0), max=Const(4), step=Const(1), par=4).name("x6733").srcCtx("LSTMPipe.scala:66:32") } // CounterNew(Const(0),Const(4),Const(1),Const(4))
    val x6734 = withCtrl(x6916) { CounterChain(List(x6733)).name("x6734").srcCtx("LSTMPipe.scala:66:45") } // CounterChainNew(List(x6733))
    val x6784 = withCtrl(x6916) { LoopController(style=MetaPipe, level=OuterControl, cchain=x6734).name("x6784").srcCtx("LSTMPipe.scala:66:45") } // UnrolledForeach(List(b3713, b3709),x6734,Block(Const(())),List(List(b3843, b3844, b3845, b3846)),List(List(b3847, b3848, b3849, b3850)))
    val b3843 = withCtrl(x6784) { CounterIter(x6733, Some(0)).name("b3843") } // b3843
    val b3847 = withCtrl(x6784) { Const(true).name("b3847") } // b3847
    val b3844 = withCtrl(x6784) { CounterIter(x6733, Some(1)).name("b3844") } // b3844
    val b3848 = withCtrl(x6784) { Const(true).name("b3848") } // b3848
    val b3845 = withCtrl(x6784) { CounterIter(x6733, Some(2)).name("b3845") } // b3845
    val b3849 = withCtrl(x6784) { Const(true).name("b3849") } // b3849
    val b3846 = withCtrl(x6784) { CounterIter(x6733, Some(3)).name("b3846") } // b3846
    val b3850 = withCtrl(x6784) { Const(true).name("b3850") } // b3850
    val x6783 = withCtrl(x6784) { UnitController(style=ForkJoin, level=OuterControl).name("x6783").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b3713, b3709),Block(Const(())))
    val x6735 = withCtrl(x6783) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6735").srcCtx("LSTMPipe.scala:67:40") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6736 = withCtrl(x6783) { CounterChain(List(x6735)).name("x6736").srcCtx("LSTMPipe.scala:67:54") } // CounterChainNew(List(x6735))
    val x6746 = withCtrl(x6783) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6736).name("x6746").srcCtx("LSTMPipe.scala:67:54") } // UnrolledForeach(List(b3847, b3713, b3709),x6736,Block(Const(())),List(List(b3859)),List(List(b3860)))
    val b3859 = withCtrl(x6746) { CounterIter(x6735, None).name("b3859") } // b3859
    val b3860 = withCtrl(x6746) { Const(true).name("b3860") } // b3860
    val x6737 = withCtrl(x6746) { OpDef(op=BitAnd, inputs=List(b3860, b3847)).name("x6737").srcCtx("UnrollingBase.scala:28:66") } // And(b3860,b3847)
    val x6738 = withCtrl(x6746) { OpDef(op=BitAnd, inputs=List(b3713, b3709)).name("x6738").srcCtx("UnrollingBase.scala:28:66") } // And(b3713,b3709)
    val x6739 = withCtrl(x6746) { OpDef(op=BitAnd, inputs=List(x6737, x6738)).name("x6739").srcCtx("UnrollingBase.scala:28:66") } // And(x6737,x6738)
    val x6740 = withCtrl(x6746) { LoadBanks(List(x6628_d0_b0), List(b3843, b3859)).name("x6740").srcCtx("LSTMPipe.scala:68:48") } // ParSRAMLoad(x6628,List(List(b3843, b3859)),List(x6739))
    val x6741 = withCtrl(x6746) { x6740 } // VectorApply(x6740,0)
    val x6742 = withCtrl(x6746) { LoadBanks(List(x6593_d0_b0), List(b3712, b3843, b3859)).name("x6742").srcCtx("LSTMPipe.scala:68:66") } // ParSRAMLoad(x6593,List(List(b3712, b3843, b3859)),List(x6739))
    val x6743 = withCtrl(x6746) { x6742 } // VectorApply(x6742,0)
    val x6744 = withCtrl(x6746) { OpDef(op=FixAdd, inputs=List(x6741, x6743)).name("x6744").srcCtx("LSTMPipe.scala:68:63") } // FixAdd(x6741,x6743)
    val x6745 = withCtrl(x6746) { StoreBanks(List(List(x6629_d0_b0)), List(b3843, b3859), x6744).name("x6745").srcCtx("LSTMPipe.scala:68:37") } // ParSRAMStore(x6629,List(List(b3843, b3859)),List(x6744),List(x6739))
    val x6747 = withCtrl(x6783) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6747").srcCtx("LSTMPipe.scala:67:40") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6748 = withCtrl(x6783) { CounterChain(List(x6747)).name("x6748").srcCtx("LSTMPipe.scala:67:54") } // CounterChainNew(List(x6747))
    val x6758 = withCtrl(x6783) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6748).name("x6758").srcCtx("LSTMPipe.scala:67:54") } // UnrolledForeach(List(b3848, b3713, b3709),x6748,Block(Const(())),List(List(b3871)),List(List(b3872)))
    val b3871 = withCtrl(x6758) { CounterIter(x6747, None).name("b3871") } // b3871
    val b3872 = withCtrl(x6758) { Const(true).name("b3872") } // b3872
    val x6749 = withCtrl(x6758) { OpDef(op=BitAnd, inputs=List(b3872, b3848)).name("x6749").srcCtx("UnrollingBase.scala:28:66") } // And(b3872,b3848)
    val x6750 = withCtrl(x6758) { OpDef(op=BitAnd, inputs=List(b3713, b3709)).name("x6750").srcCtx("UnrollingBase.scala:28:66") } // And(b3713,b3709)
    val x6751 = withCtrl(x6758) { OpDef(op=BitAnd, inputs=List(x6749, x6750)).name("x6751").srcCtx("UnrollingBase.scala:28:66") } // And(x6749,x6750)
    val x6752 = withCtrl(x6758) { LoadBanks(List(x6628_d0_b1), List(b3844, b3871)).name("x6752").srcCtx("LSTMPipe.scala:68:48") } // ParSRAMLoad(x6628,List(List(b3844, b3871)),List(x6751))
    val x6753 = withCtrl(x6758) { x6752 } // VectorApply(x6752,0)
    val x6754 = withCtrl(x6758) { LoadBanks(List(x6593_d0_b1), List(b3712, b3844, b3871)).name("x6754").srcCtx("LSTMPipe.scala:68:66") } // ParSRAMLoad(x6593,List(List(b3712, b3844, b3871)),List(x6751))
    val x6755 = withCtrl(x6758) { x6754 } // VectorApply(x6754,0)
    val x6756 = withCtrl(x6758) { OpDef(op=FixAdd, inputs=List(x6753, x6755)).name("x6756").srcCtx("LSTMPipe.scala:68:63") } // FixAdd(x6753,x6755)
    val x6757 = withCtrl(x6758) { StoreBanks(List(List(x6629_d0_b1)), List(b3844, b3871), x6756).name("x6757").srcCtx("LSTMPipe.scala:68:37") } // ParSRAMStore(x6629,List(List(b3844, b3871)),List(x6756),List(x6751))
    val x6759 = withCtrl(x6783) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6759").srcCtx("LSTMPipe.scala:67:40") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6760 = withCtrl(x6783) { CounterChain(List(x6759)).name("x6760").srcCtx("LSTMPipe.scala:67:54") } // CounterChainNew(List(x6759))
    val x6770 = withCtrl(x6783) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6760).name("x6770").srcCtx("LSTMPipe.scala:67:54") } // UnrolledForeach(List(b3849, b3713, b3709),x6760,Block(Const(())),List(List(b3883)),List(List(b3884)))
    val b3883 = withCtrl(x6770) { CounterIter(x6759, None).name("b3883") } // b3883
    val b3884 = withCtrl(x6770) { Const(true).name("b3884") } // b3884
    val x6761 = withCtrl(x6770) { OpDef(op=BitAnd, inputs=List(b3884, b3849)).name("x6761").srcCtx("UnrollingBase.scala:28:66") } // And(b3884,b3849)
    val x6762 = withCtrl(x6770) { OpDef(op=BitAnd, inputs=List(b3713, b3709)).name("x6762").srcCtx("UnrollingBase.scala:28:66") } // And(b3713,b3709)
    val x6763 = withCtrl(x6770) { OpDef(op=BitAnd, inputs=List(x6761, x6762)).name("x6763").srcCtx("UnrollingBase.scala:28:66") } // And(x6761,x6762)
    val x6764 = withCtrl(x6770) { LoadBanks(List(x6628_d0_b2), List(b3845, b3883)).name("x6764").srcCtx("LSTMPipe.scala:68:48") } // ParSRAMLoad(x6628,List(List(b3845, b3883)),List(x6763))
    val x6765 = withCtrl(x6770) { x6764 } // VectorApply(x6764,0)
    val x6766 = withCtrl(x6770) { LoadBanks(List(x6593_d0_b2), List(b3712, b3845, b3883)).name("x6766").srcCtx("LSTMPipe.scala:68:66") } // ParSRAMLoad(x6593,List(List(b3712, b3845, b3883)),List(x6763))
    val x6767 = withCtrl(x6770) { x6766 } // VectorApply(x6766,0)
    val x6768 = withCtrl(x6770) { OpDef(op=FixAdd, inputs=List(x6765, x6767)).name("x6768").srcCtx("LSTMPipe.scala:68:63") } // FixAdd(x6765,x6767)
    val x6769 = withCtrl(x6770) { StoreBanks(List(List(x6629_d0_b2)), List(b3845, b3883), x6768).name("x6769").srcCtx("LSTMPipe.scala:68:37") } // ParSRAMStore(x6629,List(List(b3845, b3883)),List(x6768),List(x6763))
    val x6771 = withCtrl(x6783) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6771").srcCtx("LSTMPipe.scala:67:40") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6772 = withCtrl(x6783) { CounterChain(List(x6771)).name("x6772").srcCtx("LSTMPipe.scala:67:54") } // CounterChainNew(List(x6771))
    val x6782 = withCtrl(x6783) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6772).name("x6782").srcCtx("LSTMPipe.scala:67:54") } // UnrolledForeach(List(b3850, b3713, b3709),x6772,Block(Const(())),List(List(b3895)),List(List(b3896)))
    val b3895 = withCtrl(x6782) { CounterIter(x6771, None).name("b3895") } // b3895
    val b3896 = withCtrl(x6782) { Const(true).name("b3896") } // b3896
    val x6773 = withCtrl(x6782) { OpDef(op=BitAnd, inputs=List(b3896, b3850)).name("x6773").srcCtx("UnrollingBase.scala:28:66") } // And(b3896,b3850)
    val x6774 = withCtrl(x6782) { OpDef(op=BitAnd, inputs=List(b3713, b3709)).name("x6774").srcCtx("UnrollingBase.scala:28:66") } // And(b3713,b3709)
    val x6775 = withCtrl(x6782) { OpDef(op=BitAnd, inputs=List(x6773, x6774)).name("x6775").srcCtx("UnrollingBase.scala:28:66") } // And(x6773,x6774)
    val x6776 = withCtrl(x6782) { LoadBanks(List(x6628_d0_b3), List(b3846, b3895)).name("x6776").srcCtx("LSTMPipe.scala:68:48") } // ParSRAMLoad(x6628,List(List(b3846, b3895)),List(x6775))
    val x6777 = withCtrl(x6782) { x6776 } // VectorApply(x6776,0)
    val x6778 = withCtrl(x6782) { LoadBanks(List(x6593_d0_b3), List(b3712, b3846, b3895)).name("x6778").srcCtx("LSTMPipe.scala:68:66") } // ParSRAMLoad(x6593,List(List(b3712, b3846, b3895)),List(x6775))
    val x6779 = withCtrl(x6782) { x6778 } // VectorApply(x6778,0)
    val x6780 = withCtrl(x6782) { OpDef(op=FixAdd, inputs=List(x6777, x6779)).name("x6780").srcCtx("LSTMPipe.scala:68:63") } // FixAdd(x6777,x6779)
    val x6781 = withCtrl(x6782) { StoreBanks(List(List(x6629_d0_b3)), List(b3846, b3895), x6780).name("x6781").srcCtx("LSTMPipe.scala:68:37") } // ParSRAMStore(x6629,List(List(b3846, b3895)),List(x6780),List(x6775))
    val x6785_d0_b0 = withCtrl(x6916) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x6785_d0_b0").srcCtx("LSTMPipe.scala:73:29:cbuf") } // x6785 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x6785_d0_b0) = false
    bufferDepthOf(x6785_d0_b0) = 2
    staticDimsOf(x6785_d0_b0) = List(128)
    val x6785_d1_b0 = withCtrl(x6916) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x6785_d1_b0").srcCtx("LSTMPipe.scala:73:29:cbuf") } // x6785 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x6785_d1_b0) = false
    bufferDepthOf(x6785_d1_b0) = 2
    staticDimsOf(x6785_d1_b0) = List(128)
    val x6786_d0_b0 = withCtrl(x6916) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x6786_d0_b0").srcCtx("LSTMPipe.scala:74:29:hbuf") } // x6786 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x6786_d0_b0) = false
    bufferDepthOf(x6786_d0_b0) = 2
    staticDimsOf(x6786_d0_b0) = List(128)
    val x6786_d1_b0 = withCtrl(x6916) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x6786_d1_b0").srcCtx("LSTMPipe.scala:74:29:hbuf") } // x6786 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x6786_d1_b0) = true
    bufferDepthOf(x6786_d1_b0) = 1
    staticDimsOf(x6786_d1_b0) = List(128)
    val x6787 = withCtrl(x6916) { FIFO(size=16).name("x6787").srcCtx("LSTMPipe.scala:75:37:splitQsigEle") } // x6787 = FIFONew(Const(16))
    isAccum(x6787) = false
    bufferDepthOf(x6787) = 2
    val x6788 = withCtrl(x6916) { FIFO(size=16).name("x6788").srcCtx("LSTMPipe.scala:76:37:splitQactEle") } // x6788 = FIFONew(Const(16))
    isAccum(x6788) = false
    bufferDepthOf(x6788) = 2
    val x6789 = withCtrl(x6916) { FIFO(size=16).name("x6789").srcCtx("LSTMPipe.scala:77:36:splitQclast") } // x6789 = FIFONew(Const(16))
    isAccum(x6789) = false
    bufferDepthOf(x6789) = 2
    val x6790 = withCtrl(x6916) { FIFO(size=16).name("x6790").srcCtx("LSTMPipe.scala:78:39:splitQcNewMult") } // x6790 = FIFONew(Const(16))
    isAccum(x6790) = false
    bufferDepthOf(x6790) = 2
    val x6791 = withCtrl(x6916) { FIFO(size=16).name("x6791").srcCtx("LSTMPipe.scala:79:42:splitQcNewMultAdd") } // x6791 = FIFONew(Const(16))
    isAccum(x6791) = false
    bufferDepthOf(x6791) = 2
    val x6792 = withCtrl(x6916) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x6792").srcCtx("LSTMPipe.scala:81:27") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x6793 = withCtrl(x6916) { CounterChain(List(x6792)).name("x6793").srcCtx("LSTMPipe.scala:81:33") } // CounterChainNew(List(x6792))
    val x6903 = withCtrl(x6916) { LoopController(style=MetaPipe, level=OuterControl, cchain=x6793).name("x6903").srcCtx("LSTMPipe.scala:81:33") } // UnrolledForeach(List(b3713, b3709),x6793,Block(Const(())),List(List(b3918)),List(List(b3919)))
    val b3918 = withCtrl(x6903) { CounterIter(x6792, Some(0)).name("b3918") } // b3918
    val b3919 = withCtrl(x6903) { Const(true).name("b3919") } // b3919
    val x6794 = withCtrl(x6903) { FIFO(size=16).name("x6794").srcCtx("LSTMPipe.scala:82:31:sigQ") } // x6794 = FIFONew(Const(16))
    isAccum(x6794) = false
    bufferDepthOf(x6794) = 2
    val x6795 = withCtrl(x6903) { FIFO(size=16).name("x6795").srcCtx("LSTMPipe.scala:83:31:actQ") } // x6795 = FIFONew(Const(16))
    isAccum(x6795) = false
    bufferDepthOf(x6795) = 2
    val x6796 = withCtrl(x6903) { FIFO(size=16).name("x6796").srcCtx("LSTMPipe.scala:84:34:sigEleQ") } // x6796 = FIFONew(Const(16))
    isAccum(x6796) = false
    bufferDepthOf(x6796) = 2
    val x6797 = withCtrl(x6903) { FIFO(size=16).name("x6797").srcCtx("LSTMPipe.scala:85:39:cNewMultAddQ") } // x6797 = FIFONew(Const(16))
    isAccum(x6797) = false
    bufferDepthOf(x6797) = 2
    val x6798 = withCtrl(x6903) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6798").srcCtx("LSTMPipe.scala:87:40") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6799 = withCtrl(x6903) { CounterChain(List(x6798)).name("x6799").srcCtx("LSTMPipe.scala:87:54") } // CounterChainNew(List(x6798))
    val x6827 = withCtrl(x6903) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6799).name("x6827").srcCtx("LSTMPipe.scala:87:54") } // UnrolledForeach(List(b3919, b3713, b3709),x6799,Block(Const(())),List(List(b3926)),List(List(b3927)))
    val b3926 = withCtrl(x6827) { CounterIter(x6798, None).name("b3926") } // b3926
    val b3927 = withCtrl(x6827) { Const(true).name("b3927") } // b3927
    val x6800 = withCtrl(x6827) { OpDef(op=BitAnd, inputs=List(b3927, b3919)).name("x6800").srcCtx("UnrollingBase.scala:28:66") } // And(b3927,b3919)
    val x6801 = withCtrl(x6827) { OpDef(op=BitAnd, inputs=List(b3713, b3709)).name("x6801").srcCtx("UnrollingBase.scala:28:66") } // And(b3713,b3709)
    val x6802 = withCtrl(x6827) { OpDef(op=BitAnd, inputs=List(x6800, x6801)).name("x6802").srcCtx("UnrollingBase.scala:28:66") } // And(x6800,x6801)
    val x6803 = withCtrl(x6827) { LoadBanks(List(x6469_d1_b0), List(b3712, b3926)).name("x6803").srcCtx("LSTMPipe.scala:88:27:cEle") } // ParSRAMLoad(x6469,List(List(b3712, b3926)),List(x6802))
    val x6804 = withCtrl(x6827) { x6803 } // VectorApply(x6803,0)
    val x6805 = withCtrl(x6827) { LoadBanks(List(x6629_d0_b0, x6629_d0_b1, x6629_d0_b2, x6629_d0_b3), List(b3918, b3926)).name("x6805").srcCtx("LSTMPipe.scala:89:33:pEle") } // ParSRAMLoad(x6629,List(List(b3918, b3926)),List(x6802))
    val x6806 = withCtrl(x6827) { x6805 } // VectorApply(x6805,0)
    val x6807 = withCtrl(x6827) { OpDef(op=FixLt, inputs=List(x6806, Const(-2.5))).name("x6807").srcCtx("NonLinearity.scala:94:12") } // FixLt(x6806,Const(-2.5))
    val x6808 = withCtrl(x6827) { OpDef(op=FixLeq, inputs=List(Const(2.5), x6806)).name("x6808").srcCtx("NonLinearity.scala:94:34") } // FixLeq(Const(2.5),x6806)
    val x6809 = withCtrl(x6827) { OpDef(op=FixMul, inputs=List(Const(0.19999999), x6806)).name("x6809").srcCtx("NonLinearity.scala:94:56") } // FixMul(Const(0.199999988079071044921875),x6806)
    val x6810 = withCtrl(x6827) { OpDef(op=FixAdd, inputs=List(x6809, Const(0.5))).name("x6810").srcCtx("NonLinearity.scala:94:61") } // FixAdd(x6809,Const(0.5))
    val x6811 = withCtrl(x6827) { OpDef(op=MuxOp, inputs=List(x6808, Const(1.0), x6810)).name("x6811").srcCtx("NonLinearity.scala:94:30") } // Mux(x6808,Const(1),x6810)
    val x6812 = withCtrl(x6827) { OpDef(op=MuxOp, inputs=List(x6807, Const(0.0), x6811)).name("x6812").srcCtx("NonLinearity.scala:94:8:sigEle") } // Mux(x6807,Const(0),x6811)
    val x6813 = withCtrl(x6827) { OpDef(op=FixAbs, inputs=List(x6806)).name("x6813").srcCtx("NonLinearity.scala:73:20:absin") } // FixAbs(x6806)
    val x6814 = withCtrl(x6827) { OpDef(op=FixSra, inputs=List(x6813, Const(2))).name("x6814").srcCtx("NonLinearity.scala:74:22:div4") } // FixRsh(x6813,Const(2))
    val x6815 = withCtrl(x6827) { OpDef(op=FixAdd, inputs=List(x6814, Const(0.375))).name("x6815").srcCtx("NonLinearity.scala:75:19:li") } // FixAdd(x6814,Const(0.375))
    val x6816 = withCtrl(x6827) { OpDef(op=FixLt, inputs=List(Const(2.5), x6813)).name("x6816").srcCtx("NonLinearity.scala:76:28") } // FixLt(Const(2.5),x6813)
    val x6817 = withCtrl(x6827) { OpDef(op=FixLt, inputs=List(Const(0.5), x6813)).name("x6817").srcCtx("NonLinearity.scala:77:14") } // FixLt(Const(0.5),x6813)
    val x6818 = withCtrl(x6827) { OpDef(op=FixLt, inputs=List(x6813, Const(2.5))).name("x6818").srcCtx("NonLinearity.scala:77:31") } // FixLt(x6813,Const(2.5))
    val x6819 = withCtrl(x6827) { OpDef(op=BitAnd, inputs=List(x6817, x6818)).name("x6819").srcCtx("NonLinearity.scala:77:22") } // And(x6817,x6818)
    val x6820 = withCtrl(x6827) { OpDef(op=MuxOp, inputs=List(x6819, x6815, x6813)).name("x6820").srcCtx("NonLinearity.scala:77:10") } // Mux(x6819,x6815,x6813)
    val x6821 = withCtrl(x6827) { OpDef(op=MuxOp, inputs=List(x6816, Const(1.0), x6820)).name("x6821").srcCtx("NonLinearity.scala:76:21:absout") } // Mux(x6816,Const(1),x6820)
    val x6822 = withCtrl(x6827) { OpDef(op=FixNeg, inputs=List(x6821)).name("x6822").srcCtx("NonLinearity.scala:80:23:negout") } // FixNeg(x6821)
    val x6823 = withCtrl(x6827) { OpDef(op=FixLt, inputs=List(x6806, Const(0.0))).name("x6823").srcCtx("NonLinearity.scala:81:12") } // FixLt(x6806,Const(0))
    val x6824 = withCtrl(x6827) { OpDef(op=MuxOp, inputs=List(x6823, x6822, x6821)).name("x6824").srcCtx("NonLinearity.scala:81:8:actEle") } // Mux(x6823,x6822,x6821)
    val x6825_x6794 = withCtrl(x6827) { WriteMem(x6794, x6812).name("x6825_x6794").srcCtx("LSTMPipe.scala:94:23") } // ParFIFOEnq(x6794,List(x6812),List(x6802))
    val x6826_x6795 = withCtrl(x6827) { WriteMem(x6795, x6824).name("x6826_x6795").srcCtx("LSTMPipe.scala:95:23") } // ParFIFOEnq(x6795,List(x6824),List(x6802))
    val x6828 = withCtrl(x6903) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6828").srcCtx("LSTMPipe.scala:100:40") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6829 = withCtrl(x6903) { CounterChain(List(x6828)).name("x6829").srcCtx("LSTMPipe.scala:100:54") } // CounterChainNew(List(x6828))
    val x6849 = withCtrl(x6903) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6829).name("x6849").srcCtx("LSTMPipe.scala:100:54") } // UnrolledForeach(List(b3919, b3713, b3709),x6829,Block(Const(())),List(List(b3958)),List(List(b3959)))
    val b3958 = withCtrl(x6849) { CounterIter(x6828, None).name("b3958") } // b3958
    val b3959 = withCtrl(x6849) { Const(true).name("b3959") } // b3959
    val x6830 = withCtrl(x6849) { OpDef(op=BitAnd, inputs=List(b3959, b3919)).name("x6830").srcCtx("UnrollingBase.scala:28:66") } // And(b3959,b3919)
    val x6831 = withCtrl(x6849) { OpDef(op=BitAnd, inputs=List(b3713, b3709)).name("x6831").srcCtx("UnrollingBase.scala:28:66") } // And(b3713,b3709)
    val x6832 = withCtrl(x6849) { OpDef(op=BitAnd, inputs=List(x6830, x6831)).name("x6832").srcCtx("UnrollingBase.scala:28:66") } // And(x6830,x6831)
    val x6833 = withCtrl(x6849) { ReadMem(x6794).name("x6833").srcCtx("LSTMPipe.scala:101:36:sigEle") } // ParFIFODeq(x6794,List(x6832))
    val x6834 = withCtrl(x6849) { x6833 } // VectorApply(x6833,0)
    val x6835 = withCtrl(x6849) { ReadMem(x6795).name("x6835").srcCtx("LSTMPipe.scala:102:36:actEle") } // ParFIFODeq(x6795,List(x6832))
    val x6836 = withCtrl(x6849) { x6835 } // VectorApply(x6835,0)
    val x6837 = withCtrl(x6849) { LoadBanks(List(x6785_d1_b0), List(b3958)).name("x6837").srcCtx("LSTMPipe.scala:104:31:cLast") } // ParSRAMLoad(x6785,List(List(b3958)),List(x6832))
    val x6838 = withCtrl(x6849) { x6837 } // VectorApply(x6837,0)
    val x6839 = withCtrl(x6849) { OpDef(op=FixMul, inputs=List(x6838, x6836)).name("x6839").srcCtx("LSTMPipe.scala:105:36:cNewMult") } // FixMul(x6838,x6836)
    val x6840 = withCtrl(x6849) { LoadBanks(List(x6469_d0_b0), List(b3712, b3958)).name("x6840").srcCtx("LSTMPipe.scala:106:43") } // ParSRAMLoad(x6469,List(List(b3712, b3958)),List(x6832))
    val x6841 = withCtrl(x6849) { x6840 } // VectorApply(x6840,0)
    val x6842 = withCtrl(x6849) { OpDef(op=FixMul, inputs=List(x6834, x6841)).name("x6842").srcCtx("LSTMPipe.scala:106:40") } // FixMul(x6834,x6841)
    val x6843 = withCtrl(x6849) { OpDef(op=FixAdd, inputs=List(x6842, x6838)).name("x6843").srcCtx("LSTMPipe.scala:106:65:cNewMultAdd") } // FixAdd(x6842,x6838)
    val x6844_x6787 = withCtrl(x6849) { WriteMem(x6787, x6834).name("x6844_x6787").srcCtx("LSTMPipe.scala:108:31") } // ParFIFOEnq(x6787,List(x6834),List(x6832))
    val x6845_x6788 = withCtrl(x6849) { WriteMem(x6788, x6836).name("x6845_x6788").srcCtx("LSTMPipe.scala:109:31") } // ParFIFOEnq(x6788,List(x6836),List(x6832))
    val x6846_x6789 = withCtrl(x6849) { WriteMem(x6789, x6838).name("x6846_x6789").srcCtx("LSTMPipe.scala:110:30") } // ParFIFOEnq(x6789,List(x6838),List(x6832))
    val x6847_x6790 = withCtrl(x6849) { WriteMem(x6790, x6839).name("x6847_x6790").srcCtx("LSTMPipe.scala:111:33") } // ParFIFOEnq(x6790,List(x6839),List(x6832))
    val x6848_x6791 = withCtrl(x6849) { WriteMem(x6791, x6843).name("x6848_x6791").srcCtx("LSTMPipe.scala:112:36") } // ParFIFOEnq(x6791,List(x6843),List(x6832))
    val x6850 = withCtrl(x6903) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6850").srcCtx("LSTMPipe.scala:115:40") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6851 = withCtrl(x6903) { CounterChain(List(x6850)).name("x6851").srcCtx("LSTMPipe.scala:115:54") } // CounterChainNew(List(x6850))
    val x6874 = withCtrl(x6903) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6851).name("x6874").srcCtx("LSTMPipe.scala:115:54") } // UnrolledForeach(List(b3919, b3713, b3709),x6851,Block(Const(())),List(List(b3982)),List(List(b3983)))
    val b3982 = withCtrl(x6874) { CounterIter(x6850, None).name("b3982") } // b3982
    val b3983 = withCtrl(x6874) { Const(true).name("b3983") } // b3983
    val x6852 = withCtrl(x6874) { OpDef(op=BitAnd, inputs=List(b3983, b3919)).name("x6852").srcCtx("UnrollingBase.scala:28:66") } // And(b3983,b3919)
    val x6853 = withCtrl(x6874) { OpDef(op=BitAnd, inputs=List(b3713, b3709)).name("x6853").srcCtx("UnrollingBase.scala:28:66") } // And(b3713,b3709)
    val x6854 = withCtrl(x6874) { OpDef(op=BitAnd, inputs=List(x6852, x6853)).name("x6854").srcCtx("UnrollingBase.scala:28:66") } // And(x6852,x6853)
    val x6855 = withCtrl(x6874) { ReadMem(x6787).name("x6855").srcCtx("LSTMPipe.scala:116:44:sigEle") } // ParFIFODeq(x6787,List(x6854))
    val x6856 = withCtrl(x6874) { x6855 } // VectorApply(x6855,0)
    val x6857 = withCtrl(x6874) { ReadMem(x6788).name("x6857").srcCtx("LSTMPipe.scala:117:44:actEle") } // ParFIFODeq(x6788,List(x6854))
    val x6858 = withCtrl(x6874) { x6857 } // VectorApply(x6857,0)
    val x6859 = withCtrl(x6874) { ReadMem(x6789).name("x6859").srcCtx("LSTMPipe.scala:118:42:cLast") } // ParFIFODeq(x6789,List(x6854))
    val x6860 = withCtrl(x6874) { x6859 } // VectorApply(x6859,0)
    val x6861 = withCtrl(x6874) { ReadMem(x6790).name("x6861").srcCtx("LSTMPipe.scala:119:48:cNewMult") } // ParFIFODeq(x6790,List(x6854))
    val x6862 = withCtrl(x6874) { x6861 } // VectorApply(x6861,0)
    val x6863 = withCtrl(x6874) { ReadMem(x6791).name("x6863").srcCtx("LSTMPipe.scala:120:54:cNewMultAdd") } // ParFIFODeq(x6791,List(x6854))
    val x6864 = withCtrl(x6874) { x6863 } // VectorApply(x6863,0)
    val x6865 = withCtrl(x6874) { OpDef(op=FixEql, inputs=List(b3918, Const(0))).name("x6865").srcCtx("LSTMPipe.scala:16:40") } // FixEql(b3918,Const(0))
    val x6866 = withCtrl(x6874) { OpDef(op=FixEql, inputs=List(b3918, Const(1))).name("x6866").srcCtx("LSTMPipe.scala:18:40") } // FixEql(b3918,Const(1))
    val x6867 = withCtrl(x6874) { OpDef(op=FixEql, inputs=List(b3918, Const(2))).name("x6867").srcCtx("LSTMPipe.scala:20:40") } // FixEql(b3918,Const(2))
    val x6868 = withCtrl(x6874) { OpDef(op=MuxOp, inputs=List(x6867, x6864, x6860)).name("x6868").srcCtx("LSTMPipe.scala:124:24") } // Mux(x6867,x6864,x6860)
    val x6869 = withCtrl(x6874) { OpDef(op=MuxOp, inputs=List(x6866, x6862, x6868)).name("x6869").srcCtx("LSTMPipe.scala:123:22") } // Mux(x6866,x6862,x6868)
    val x6870 = withCtrl(x6874) { OpDef(op=MuxOp, inputs=List(x6865, x6856, x6869)).name("x6870").srcCtx("LSTMPipe.scala:122:38") } // Mux(x6865,x6856,x6869)
    val x6871 = withCtrl(x6874) { StoreBanks(List(List(x6785_d0_b0), List(x6785_d1_b0)), List(b3982), x6870).name("x6871").srcCtx("LSTMPipe.scala:122:33") } // ParSRAMStore(x6785,List(List(b3982)),List(x6870),List(x6854))
    val x6872_x6796 = withCtrl(x6874) { WriteMem(x6796, x6856).name("x6872_x6796").srcCtx("LSTMPipe.scala:127:26") } // ParFIFOEnq(x6796,List(x6856),List(x6854))
    val x6873_x6797 = withCtrl(x6874) { WriteMem(x6797, x6864).name("x6873_x6797").srcCtx("LSTMPipe.scala:128:31") } // ParFIFOEnq(x6797,List(x6864),List(x6854))
    val x6875 = withCtrl(x6903) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6875").srcCtx("LSTMPipe.scala:131:40") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6876 = withCtrl(x6903) { CounterChain(List(x6875)).name("x6876").srcCtx("LSTMPipe.scala:131:54") } // CounterChainNew(List(x6875))
    val x6902 = withCtrl(x6903) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6876).name("x6902").srcCtx("LSTMPipe.scala:131:54") } // UnrolledForeach(List(b3919, b3713, b3709),x6876,Block(Const(())),List(List(b4009)),List(List(b4010)))
    val b4009 = withCtrl(x6902) { CounterIter(x6875, None).name("b4009") } // b4009
    val b4010 = withCtrl(x6902) { Const(true).name("b4010") } // b4010
    val x6877 = withCtrl(x6902) { OpDef(op=BitAnd, inputs=List(b4010, b3919)).name("x6877").srcCtx("UnrollingBase.scala:28:66") } // And(b4010,b3919)
    val x6878 = withCtrl(x6902) { OpDef(op=BitAnd, inputs=List(b3713, b3709)).name("x6878").srcCtx("UnrollingBase.scala:28:66") } // And(b3713,b3709)
    val x6879 = withCtrl(x6902) { OpDef(op=BitAnd, inputs=List(x6877, x6878)).name("x6879").srcCtx("UnrollingBase.scala:28:66") } // And(x6877,x6878)
    val x6880 = withCtrl(x6902) { ReadMem(x6796).name("x6880").srcCtx("LSTMPipe.scala:132:39:sigEle") } // ParFIFODeq(x6796,List(x6879))
    val x6881 = withCtrl(x6902) { x6880 } // VectorApply(x6880,0)
    val x6882 = withCtrl(x6902) { ReadMem(x6797).name("x6882").srcCtx("LSTMPipe.scala:133:49:cNewMultAdd") } // ParFIFODeq(x6797,List(x6879))
    val x6883 = withCtrl(x6902) { x6882 } // VectorApply(x6882,0)
    val x6884 = withCtrl(x6902) { OpDef(op=FixAbs, inputs=List(x6883)).name("x6884").srcCtx("NonLinearity.scala:73:20:absin") } // FixAbs(x6883)
    val x6885 = withCtrl(x6902) { OpDef(op=FixSra, inputs=List(x6884, Const(2))).name("x6885").srcCtx("NonLinearity.scala:74:22:div4") } // FixRsh(x6884,Const(2))
    val x6886 = withCtrl(x6902) { OpDef(op=FixAdd, inputs=List(x6885, Const(0.375))).name("x6886").srcCtx("NonLinearity.scala:75:19:li") } // FixAdd(x6885,Const(0.375))
    val x6887 = withCtrl(x6902) { OpDef(op=FixLt, inputs=List(Const(2.5), x6884)).name("x6887").srcCtx("NonLinearity.scala:76:28") } // FixLt(Const(2.5),x6884)
    val x6888 = withCtrl(x6902) { OpDef(op=FixLt, inputs=List(Const(0.5), x6884)).name("x6888").srcCtx("NonLinearity.scala:77:14") } // FixLt(Const(0.5),x6884)
    val x6889 = withCtrl(x6902) { OpDef(op=FixLt, inputs=List(x6884, Const(2.5))).name("x6889").srcCtx("NonLinearity.scala:77:31") } // FixLt(x6884,Const(2.5))
    val x6890 = withCtrl(x6902) { OpDef(op=BitAnd, inputs=List(x6888, x6889)).name("x6890").srcCtx("NonLinearity.scala:77:22") } // And(x6888,x6889)
    val x6891 = withCtrl(x6902) { OpDef(op=MuxOp, inputs=List(x6890, x6886, x6884)).name("x6891").srcCtx("NonLinearity.scala:77:10") } // Mux(x6890,x6886,x6884)
    val x6892 = withCtrl(x6902) { OpDef(op=MuxOp, inputs=List(x6887, Const(1.0), x6891)).name("x6892").srcCtx("NonLinearity.scala:76:21:absout") } // Mux(x6887,Const(1),x6891)
    val x6893 = withCtrl(x6902) { OpDef(op=FixNeg, inputs=List(x6892)).name("x6893").srcCtx("NonLinearity.scala:80:23:negout") } // FixNeg(x6892)
    val x6894 = withCtrl(x6902) { OpDef(op=FixLt, inputs=List(x6883, Const(0.0))).name("x6894").srcCtx("NonLinearity.scala:81:12") } // FixLt(x6883,Const(0))
    val x6895 = withCtrl(x6902) { OpDef(op=MuxOp, inputs=List(x6894, x6893, x6892)).name("x6895").srcCtx("NonLinearity.scala:81:8") } // Mux(x6894,x6893,x6892)
    val x6896 = withCtrl(x6902) { OpDef(op=FixAdd, inputs=List(x6895, x6881)).name("x6896").srcCtx("LSTMPipe.scala:135:50:hNew") } // FixAdd(x6895,x6881)
    val x6897 = withCtrl(x6902) { LoadBanks(List(x6786_d1_b0), List(b4009)).name("x6897").srcCtx("LSTMPipe.scala:136:31:hLast") } // ParSRAMLoad(x6786,List(List(b4009)),List(x6879))
    val x6898 = withCtrl(x6902) { x6897 } // VectorApply(x6897,0)
    val x6899 = withCtrl(x6902) { OpDef(op=FixEql, inputs=List(b3918, Const(3))).name("x6899").srcCtx("LSTMPipe.scala:22:40") } // FixEql(b3918,Const(3))
    val x6900 = withCtrl(x6902) { OpDef(op=MuxOp, inputs=List(x6899, x6896, x6898)).name("x6900").srcCtx("LSTMPipe.scala:138:31:update") } // Mux(x6899,x6896,x6898)
    val x6901 = withCtrl(x6902) { StoreBanks(List(List(x6786_d0_b0), List(x6786_d1_b0)), List(b4009), x6900).name("x6901").srcCtx("LSTMPipe.scala:139:33") } // ParSRAMStore(x6786,List(List(b4009)),List(x6900),List(x6879))
    antiDepsOf(x6901)=List(x6897)
    val x6904 = withCtrl(x6916) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6904").srcCtx("LSTMPipe.scala:143:38") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6905 = withCtrl(x6916) { CounterChain(List(x6904)).name("x6905").srcCtx("LSTMPipe.scala:143:52") } // CounterChainNew(List(x6904))
    val x6915 = withCtrl(x6916) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6905).name("x6915").srcCtx("LSTMPipe.scala:143:52") } // UnrolledForeach(List(b3713, b3709),x6905,Block(Const(())),List(List(b4040)),List(List(b4041)))
    val b4040 = withCtrl(x6915) { CounterIter(x6904, None).name("b4040") } // b4040
    val b4041 = withCtrl(x6915) { Const(true).name("b4041") } // b4041
    val x6906 = withCtrl(x6915) { OpDef(op=BitAnd, inputs=List(b4041, b3713)).name("x6906").srcCtx("UnrollingBase.scala:28:66") } // And(b4041,b3713)
    val x6907 = withCtrl(x6915) { OpDef(op=BitAnd, inputs=List(x6906, b3709)).name("x6907").srcCtx("UnrollingBase.scala:28:66") } // And(x6906,b3709)
    val x6908 = withCtrl(x6915) { LoadBanks(List(x6786_d0_b0), List(b4040)).name("x6908").srcCtx("LSTMPipe.scala:144:42") } // ParSRAMLoad(x6786,List(List(b4040)),List(x6907))
    val x6909 = withCtrl(x6915) { x6908 } // VectorApply(x6908,0)
    val x6910 = withCtrl(x6915) { StoreBanks(List(List(x6494_d0_b0)), List(b3712, b4040), x6909).name("x6910").srcCtx("LSTMPipe.scala:144:36") } // ParSRAMStore(x6494,List(List(b3712, b4040)),List(x6909),List(x6907))
    val x6911 = withCtrl(x6915) { StoreBanks(List(List(x6444_d0_b0), List(x6444_d1_b0)), List(b3708, b4040), x6909).name("x6911").srcCtx("LSTMPipe.scala:145:35") } // ParSRAMStore(x6444,List(List(b3708, b4040)),List(x6909),List(x6907))
    val x6912 = withCtrl(x6915) { LoadBanks(List(x6785_d0_b0), List(b4040)).name("x6912").srcCtx("LSTMPipe.scala:146:42") } // ParSRAMLoad(x6785,List(List(b4040)),List(x6907))
    val x6913 = withCtrl(x6915) { x6912 } // VectorApply(x6912,0)
    val x6914 = withCtrl(x6915) { StoreBanks(List(List(x6469_d0_b0), List(x6469_d1_b0)), List(b3712, b4040), x6913).name("x6914").srcCtx("LSTMPipe.scala:146:36") } // ParSRAMStore(x6469,List(List(b3712, b4040)),List(x6913),List(x6907))
    val x6918 = withCtrl(x6946) { Counter(min=Const(0), max=Const(8), step=Const(1), par=1).name("x6918").srcCtx("LSTMPipe.scala:151:47") } // CounterNew(Const(0),Const(8),Const(1),Const(1))
    val x6919 = withCtrl(x6946) { CounterChain(List(x6918)).name("x6919").srcCtx("LSTMPipe.scala:151:47") } // CounterChainNew(List(x6918))
    val x6945 = withCtrl(x6946) { LoopController(style=StreamPipe, level=OuterControl, cchain=x6919).name("x6945").srcCtx("LSTMPipe.scala:151:47") } // UnrolledForeach(List(Const(true)),x6919,Block(Const(())),List(List(b4056)),List(List(b4057)))
    val b4056 = withCtrl(x6945) { CounterIter(x6918, Some(0)).name("b4056") } // b4056
    val b4057 = withCtrl(x6945) { Const(true).name("b4057") } // b4057
    val b6981 = withCtrl(x6945) { StreamOut(field="offset").name("b6981").srcCtx("LSTMPipe.scala:151:47") } // x6920 = StreamOutNew(BurstCmdBus)
    isAccum(b6981) = false
    bufferDepthOf(b6981) = 1
    val b6982 = withCtrl(x6945) { StreamOut(field="size").name("b6982").srcCtx("LSTMPipe.scala:151:47") } // x6920 = StreamOutNew(BurstCmdBus)
    isAccum(b6982) = false
    bufferDepthOf(b6982) = 1
    val x6921 = withCtrl(x6945) { StreamOut(field="data").name("x6921").srcCtx("LSTMPipe.scala:151:47") } // x6921 = StreamOutNew(BurstFullDataBus())
    isAccum(x6921) = false
    bufferDepthOf(x6921) = 1
    val x6922 = withCtrl(x6945) { StreamIn(field="ack").name("x6922").srcCtx("LSTMPipe.scala:151:47") } // x6922 = StreamInNew(BurstAckBus)
    isAccum(x6922) = false
    bufferDepthOf(x6922) = 1
    val x6933 = withCtrl(x6945) { UnitController(style=SeqPipe, level=InnerControl).name("x6933").srcCtx("LSTMPipe.scala:151:47") } // UnitPipe(List(b4057),Block(x6932))
    val x6923 = withCtrl(x6933) { b4056 } // FixConvert(b4056,TRUE,_32,_0) (Same Type. No op)
    val x6924 = withCtrl(x6933) { OpDef(op=FixSla, inputs=List(x6923, Const(7))).name("x6924").srcCtx("LSTMPipe.scala:151:47") } // FixLsh(x6923,Const(7))
    val x6925 = withCtrl(x6933) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x6926 = withCtrl(x6933) { OpDef(op=FixAdd, inputs=List(x6924, x6925)).name("x6926").srcCtx("LSTMPipe.scala:151:47") } // FixAdd(x6924,x6925)
    val x6927 = withCtrl(x6933) { OpDef(op=FixSla, inputs=List(x6926, Const(2))).name("x6927").srcCtx("LSTMPipe.scala:151:47") } // FixLsh(x6926,Const(2))
    val x6928 = withCtrl(x6933) { x6927 } // FixConvert(x6927,TRUE,_64,_0)
    val x6929 = withCtrl(x6933) { DramAddress(x6386).name("x6929").srcCtx("LSTMPipe.scala:151:47") } // GetDRAMAddress(x6386)
    val x6931_x6930 = withCtrl(x6933) { OpDef(op=FixAdd, inputs=List(x6928, x6929)).name("x6931_x6930").srcCtx("LSTMPipe.scala:151:47") } // FixAdd(x6928,x6929)
    // x6931 = SimpleStruct(ArrayBuffer((offset,x6930), (size,Const(512)), (isLoad,Const(false))))
    val x6932_b6983_b6981 = withCtrl(x6933) { WriteMem(b6981, x6931_x6930).name("x6932_b6983_b6981").srcCtx("LSTMPipe.scala:151:47") } // StreamWrite(x6920,x6931,b4057)
    val x6932_b6984_b6982 = withCtrl(x6933) { WriteMem(b6982, Const(512)).name("x6932_b6984_b6982").srcCtx("LSTMPipe.scala:151:47") } // StreamWrite(x6920,x6931,b4057)
    val x6934 = withCtrl(x6945) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6934").srcCtx("LSTMPipe.scala:151:47") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6935 = withCtrl(x6945) { CounterChain(List(x6934)).name("x6935").srcCtx("LSTMPipe.scala:151:47") } // CounterChainNew(List(x6934))
    val x6941 = withCtrl(x6945) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6935).name("x6941").srcCtx("LSTMPipe.scala:151:47") } // UnrolledForeach(List(b4057),x6935,Block(Const(())),List(List(b4074)),List(List(b4075)))
    val b4074 = withCtrl(x6941) { CounterIter(x6934, None).name("b4074") } // b4074
    val b4075 = withCtrl(x6941) { Const(true).name("b4075") } // b4075
    val x6936 = withCtrl(x6941) { OpDef(op=BitAnd, inputs=List(b4075, b4057)).name("x6936").srcCtx("UnrollingBase.scala:28:66") } // And(b4075,b4057)
    val x6937 = withCtrl(x6941) { LoadBanks(List(x6444_d0_b0), List(b4056, b4074)).name("x6937").srcCtx("LSTMPipe.scala:151:47") } // ParSRAMLoad(x6444,List(List(b4056, b4074)),List(x6936))
    val x6939_x6938 = withCtrl(x6941) { x6937 } // VectorApply(x6937,0)
    // x6939 = SimpleStruct(ArrayBuffer((_1,x6938), (_2,Const(true))))
    val x6940_x6940_x6921 = withCtrl(x6941) { WriteMem(x6921, x6939_x6938).name("x6940_x6940_x6921").srcCtx("LSTMPipe.scala:151:47") } // ParStreamWrite(x6921,List(x6939),List(x6936))
    val x6942 = withCtrl(x6945) { FringeDenseStore(dram=List(x6386), cmdStream=List(b6981, b6982), dataStream=List(x6921), ackStream=List(x6922)).name("x6942").srcCtx("LSTMPipe.scala:151:47") } // FringeDenseStore(x6386,x6920,x6921,x6922)
    val x6944 = withCtrl(x6945) { UnitController(style=SeqPipe, level=InnerControl).name("x6944").srcCtx("LSTMPipe.scala:151:47") } // UnitPipe(List(b4057),Block(Const(())))
    val x6943_x6943 = withCtrl(x6944) { ReadMem(x6922).name("x6943_x6943").srcCtx("LSTMPipe.scala:151:47") } // StreamRead(x6922,b4057)
    }; split1
    
  }
}
