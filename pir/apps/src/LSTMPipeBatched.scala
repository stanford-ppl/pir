import pir._
import pir.node._
import arch._
import prism.enums._

object LSTMPipeBatched extends PIRApp {
  def main(implicit design:PIRDesign) = {
    import design.pirmeta._
    val x6451 = withCtrl(design.top.topController) { DRAM(dims=List(Const(8), Const(8), Const(128))).name("x6451").srcCtx("LSTMPipeBatched.scala:17:23:dout") } // x6451 = DRAMNew(ArrayBuffer(Const(8), Const(8), Const(128)),Const(0))
    val x7003 = withCtrl(design.top.topController) { UnitController(style=SeqPipe, level=OuterControl).name("x7003").srcCtx("LSTMPipeBatched.scala:19:11") } // Hwblock(Block(Const(())),false)
    val x6452 = withCtrl(x7003) { DRAM(dims=List(Const(2), Const(128))).name("x6452").srcCtx("DataGenerator.scala:156:20:k") } // x6452 = DRAMNew(ArrayBuffer(Const(2), Const(128)),Const(0))
    val x6454_d0_b0 = withCtrl(x7003) { SRAM(size=256, banking=Strided(banks=16, stride=1)).name("x6454_d0_b0").srcCtx("DataGenerator.scala:43:21:c") } // x6454 = SRAMNew(ArrayBuffer(Const(2), Const(128)))
    isAccum(x6454_d0_b0) = false
    bufferDepthOf(x6454_d0_b0) = 1
    staticDimsOf(x6454_d0_b0) = List(2, 128)
    val x6462 = withCtrl(x7003) { UnitController(style=SeqPipe, level=InnerControl).name("x6462").srcCtx("LSTMPipeBatched.scala:19:11") } // UnitPipe(List(Const(true)),Block(Const(())))
    val x6463 = withCtrl(x7003) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x6463").srcCtx("DataGenerator.scala:44:8") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x6464 = withCtrl(x7003) { CounterChain(List(x6463)).name("x6464").srcCtx("DataGenerator.scala:44:8") } // CounterChainNew(List(x6463))
    val x6486 = withCtrl(x7003) { LoopController(style=StreamPipe, level=OuterControl, cchain=x6464).name("x6486").srcCtx("DataGenerator.scala:44:8") } // UnrolledForeach(List(Const(true)),x6464,Block(Const(())),List(List(b3541)),List(List(b3542)))
    val b3541 = withCtrl(x6486) { CounterIter(x6463, Some(0)).name("b3541") } // b3541
    val b3542 = withCtrl(x6486) { Const(true).name("b3542") } // b3542
    val b7018 = withCtrl(x6486) { StreamOut(field="offset").name("b7018").srcCtx("DataGenerator.scala:44:8") } // x6465 = StreamOutNew(BurstCmdBus)
    isAccum(b7018) = false
    bufferDepthOf(b7018) = 1
    val b7019 = withCtrl(x6486) { StreamOut(field="size").name("b7019").srcCtx("DataGenerator.scala:44:8") } // x6465 = StreamOutNew(BurstCmdBus)
    isAccum(b7019) = false
    bufferDepthOf(b7019) = 1
    val x6466 = withCtrl(x6486) { StreamIn(field="data").name("x6466").srcCtx("DataGenerator.scala:44:8") } // x6466 = StreamInNew(BurstDataBus())
    isAccum(x6466) = false
    bufferDepthOf(x6466) = 1
    val x6477 = withCtrl(x6486) { UnitController(style=SeqPipe, level=InnerControl).name("x6477").srcCtx("DataGenerator.scala:44:8") } // UnitPipe(List(b3542),Block(x6476))
    val x6467 = withCtrl(x6477) { b3541 } // FixConvert(b3541,TRUE,_32,_0) (Same Type. No op)
    val x6468 = withCtrl(x6477) { OpDef(op=FixSla, inputs=List(x6467, Const(7))).name("x6468").srcCtx("DataGenerator.scala:44:8") } // FixLsh(x6467,Const(7))
    val x6469 = withCtrl(x6477) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x6470 = withCtrl(x6477) { OpDef(op=FixAdd, inputs=List(x6468, x6469)).name("x6470").srcCtx("DataGenerator.scala:44:8") } // FixAdd(x6468,x6469)
    val x6471 = withCtrl(x6477) { OpDef(op=FixSla, inputs=List(x6470, Const(2))).name("x6471").srcCtx("DataGenerator.scala:44:8") } // FixLsh(x6470,Const(2))
    val x6472 = withCtrl(x6477) { x6471 } // FixConvert(x6471,TRUE,_64,_0)
    val x6473 = withCtrl(x6477) { DramAddress(x6452).name("x6473").srcCtx("DataGenerator.scala:44:8") } // GetDRAMAddress(x6452)
    val x6475_x6474 = withCtrl(x6477) { OpDef(op=FixAdd, inputs=List(x6472, x6473)).name("x6475_x6474").srcCtx("DataGenerator.scala:44:8") } // FixAdd(x6472,x6473)
    // x6475 = SimpleStruct(ArrayBuffer((offset,x6474), (size,Const(512)), (isLoad,Const(true))))
    val x6476_b7020_b7018 = withCtrl(x6477) { WriteMem(b7018, x6475_x6474).name("x6476_b7020_b7018").srcCtx("DataGenerator.scala:44:8") } // StreamWrite(x6465,x6475,b3542)
    val x6476_b7021_b7019 = withCtrl(x6477) { WriteMem(b7019, Const(512)).name("x6476_b7021_b7019").srcCtx("DataGenerator.scala:44:8") } // StreamWrite(x6465,x6475,b3542)
    val x6478 = withCtrl(x6486) { FringeDenseLoad(dram=List(x6452), cmdStream=List(b7018, b7019), dataStream=List(x6466)).name("x6478").srcCtx("DataGenerator.scala:44:8") } // FringeDenseLoad(x6452,x6465,x6466)
    val x6479 = withCtrl(x6486) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6479").srcCtx("DataGenerator.scala:44:8") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6480 = withCtrl(x6486) { CounterChain(List(x6479)).name("x6480").srcCtx("DataGenerator.scala:44:8") } // CounterChainNew(List(x6479))
    val x6485 = withCtrl(x6486) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6480).name("x6485").srcCtx("DataGenerator.scala:44:8") } // UnrolledForeach(List(b3542),x6480,Block(Const(())),List(List(b3559)),List(List(b3560)))
    val b3559 = withCtrl(x6485) { CounterIter(x6479, None).name("b3559") } // b3559
    val b3560 = withCtrl(x6485) { Const(true).name("b3560") } // b3560
    val x6481 = withCtrl(x6485) { OpDef(op=BitAnd, inputs=List(b3560, b3542)).name("x6481").srcCtx("UnrollingBase.scala:28:66") } // And(b3560,b3542)
    val x6482_x6482 = withCtrl(x6485) { ReadMem(x6466).name("x6482_x6482").srcCtx("DataGenerator.scala:44:8") } // ParStreamRead(x6466,List(x6481))
    val x6483_x6483 = withCtrl(x6485) { x6482_x6482 } // VectorApply(x6482,0)
    val x6484 = withCtrl(x6485) { StoreBanks(List(List(x6454_d0_b0)), List(b3541, b3559), x6483_x6483).name("x6484").srcCtx("DataGenerator.scala:44:8") } // ParSRAMStore(x6454,List(List(b3541, b3559)),List(x6483),List(x6481))
    val x6487 = withCtrl(x7003) { DRAM(dims=List(Const(2), Const(128))).name("x6487").srcCtx("DataGenerator.scala:156:20:k") } // x6487 = DRAMNew(ArrayBuffer(Const(2), Const(128)),Const(0))
    val x6489_d0_b0 = withCtrl(x7003) { SRAM(size=256, banking=Strided(banks=16, stride=1)).name("x6489_d0_b0").srcCtx("DataGenerator.scala:43:21:h") } // x6489 = SRAMNew(ArrayBuffer(Const(2), Const(128)))
    isAccum(x6489_d0_b0) = false
    bufferDepthOf(x6489_d0_b0) = 1
    staticDimsOf(x6489_d0_b0) = List(2, 128)
    val x6489_d1_b0 = withCtrl(x7003) { SRAM(size=256, banking=Strided(banks=16, stride=1)).name("x6489_d1_b0").srcCtx("DataGenerator.scala:43:21:h") } // x6489 = SRAMNew(ArrayBuffer(Const(2), Const(128)))
    isAccum(x6489_d1_b0) = false
    bufferDepthOf(x6489_d1_b0) = 1
    staticDimsOf(x6489_d1_b0) = List(2, 128)
    val x6497 = withCtrl(x7003) { UnitController(style=SeqPipe, level=InnerControl).name("x6497").srcCtx("LSTMPipeBatched.scala:19:11") } // UnitPipe(List(Const(true)),Block(Const(())))
    val x6498 = withCtrl(x7003) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x6498").srcCtx("DataGenerator.scala:44:8") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x6499 = withCtrl(x7003) { CounterChain(List(x6498)).name("x6499").srcCtx("DataGenerator.scala:44:8") } // CounterChainNew(List(x6498))
    val x6521 = withCtrl(x7003) { LoopController(style=StreamPipe, level=OuterControl, cchain=x6499).name("x6521").srcCtx("DataGenerator.scala:44:8") } // UnrolledForeach(List(Const(true)),x6499,Block(Const(())),List(List(b3580)),List(List(b3581)))
    val b3580 = withCtrl(x6521) { CounterIter(x6498, Some(0)).name("b3580") } // b3580
    val b3581 = withCtrl(x6521) { Const(true).name("b3581") } // b3581
    val b7022 = withCtrl(x6521) { StreamOut(field="offset").name("b7022").srcCtx("DataGenerator.scala:44:8") } // x6500 = StreamOutNew(BurstCmdBus)
    isAccum(b7022) = false
    bufferDepthOf(b7022) = 1
    val b7023 = withCtrl(x6521) { StreamOut(field="size").name("b7023").srcCtx("DataGenerator.scala:44:8") } // x6500 = StreamOutNew(BurstCmdBus)
    isAccum(b7023) = false
    bufferDepthOf(b7023) = 1
    val x6501 = withCtrl(x6521) { StreamIn(field="data").name("x6501").srcCtx("DataGenerator.scala:44:8") } // x6501 = StreamInNew(BurstDataBus())
    isAccum(x6501) = false
    bufferDepthOf(x6501) = 1
    val x6512 = withCtrl(x6521) { UnitController(style=SeqPipe, level=InnerControl).name("x6512").srcCtx("DataGenerator.scala:44:8") } // UnitPipe(List(b3581),Block(x6511))
    val x6502 = withCtrl(x6512) { b3580 } // FixConvert(b3580,TRUE,_32,_0) (Same Type. No op)
    val x6503 = withCtrl(x6512) { OpDef(op=FixSla, inputs=List(x6502, Const(7))).name("x6503").srcCtx("DataGenerator.scala:44:8") } // FixLsh(x6502,Const(7))
    val x6504 = withCtrl(x6512) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x6505 = withCtrl(x6512) { OpDef(op=FixAdd, inputs=List(x6503, x6504)).name("x6505").srcCtx("DataGenerator.scala:44:8") } // FixAdd(x6503,x6504)
    val x6506 = withCtrl(x6512) { OpDef(op=FixSla, inputs=List(x6505, Const(2))).name("x6506").srcCtx("DataGenerator.scala:44:8") } // FixLsh(x6505,Const(2))
    val x6507 = withCtrl(x6512) { x6506 } // FixConvert(x6506,TRUE,_64,_0)
    val x6508 = withCtrl(x6512) { DramAddress(x6487).name("x6508").srcCtx("DataGenerator.scala:44:8") } // GetDRAMAddress(x6487)
    val x6510_x6509 = withCtrl(x6512) { OpDef(op=FixAdd, inputs=List(x6507, x6508)).name("x6510_x6509").srcCtx("DataGenerator.scala:44:8") } // FixAdd(x6507,x6508)
    // x6510 = SimpleStruct(ArrayBuffer((offset,x6509), (size,Const(512)), (isLoad,Const(true))))
    val x6511_b7024_b7022 = withCtrl(x6512) { WriteMem(b7022, x6510_x6509).name("x6511_b7024_b7022").srcCtx("DataGenerator.scala:44:8") } // StreamWrite(x6500,x6510,b3581)
    val x6511_b7025_b7023 = withCtrl(x6512) { WriteMem(b7023, Const(512)).name("x6511_b7025_b7023").srcCtx("DataGenerator.scala:44:8") } // StreamWrite(x6500,x6510,b3581)
    val x6513 = withCtrl(x6521) { FringeDenseLoad(dram=List(x6487), cmdStream=List(b7022, b7023), dataStream=List(x6501)).name("x6513").srcCtx("DataGenerator.scala:44:8") } // FringeDenseLoad(x6487,x6500,x6501)
    val x6514 = withCtrl(x6521) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6514").srcCtx("DataGenerator.scala:44:8") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6515 = withCtrl(x6521) { CounterChain(List(x6514)).name("x6515").srcCtx("DataGenerator.scala:44:8") } // CounterChainNew(List(x6514))
    val x6520 = withCtrl(x6521) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6515).name("x6520").srcCtx("DataGenerator.scala:44:8") } // UnrolledForeach(List(b3581),x6515,Block(Const(())),List(List(b3598)),List(List(b3599)))
    val b3598 = withCtrl(x6520) { CounterIter(x6514, None).name("b3598") } // b3598
    val b3599 = withCtrl(x6520) { Const(true).name("b3599") } // b3599
    val x6516 = withCtrl(x6520) { OpDef(op=BitAnd, inputs=List(b3599, b3581)).name("x6516").srcCtx("UnrollingBase.scala:28:66") } // And(b3599,b3581)
    val x6517_x6517 = withCtrl(x6520) { ReadMem(x6501).name("x6517_x6517").srcCtx("DataGenerator.scala:44:8") } // ParStreamRead(x6501,List(x6516))
    val x6518_x6518 = withCtrl(x6520) { x6517_x6517 } // VectorApply(x6517,0)
    val x6519 = withCtrl(x6520) { StoreBanks(List(List(x6489_d0_b0), List(x6489_d1_b0)), List(b3580, b3598), x6518_x6518).name("x6519").srcCtx("DataGenerator.scala:44:8") } // ParSRAMStore(x6489,List(List(b3580, b3598)),List(x6518),List(x6516))
    val x6522 = withCtrl(x7003) { DRAM(dims=List(Const(2), Const(128), Const(4), Const(128))).name("x6522").srcCtx("DataGenerator.scala:182:20:k") } // x6522 = DRAMNew(ArrayBuffer(Const(2), Const(128), Const(4), Const(128)),Const(0))
    val x6524_d0_b0 = withCtrl(x7003) { SRAM(size=32768, banking=Strided(banks=16, stride=1)).name("x6524_d0_b0").srcCtx("DataGenerator.scala:76:21:wx") } // x6524 = SRAMNew(ArrayBuffer(Const(2), Const(128), Const(4), Const(128)))
    isAccum(x6524_d0_b0) = false
    bufferDepthOf(x6524_d0_b0) = 1
    staticDimsOf(x6524_d0_b0) = List(2, 128, 4, 128)
    val x6524_d0_b1 = withCtrl(x7003) { SRAM(size=32768, banking=Strided(banks=16, stride=1)).name("x6524_d0_b1").srcCtx("DataGenerator.scala:76:21:wx") } // x6524 = SRAMNew(ArrayBuffer(Const(2), Const(128), Const(4), Const(128)))
    isAccum(x6524_d0_b1) = false
    bufferDepthOf(x6524_d0_b1) = 1
    staticDimsOf(x6524_d0_b1) = List(2, 128, 4, 128)
    val x6524_d0_b2 = withCtrl(x7003) { SRAM(size=32768, banking=Strided(banks=16, stride=1)).name("x6524_d0_b2").srcCtx("DataGenerator.scala:76:21:wx") } // x6524 = SRAMNew(ArrayBuffer(Const(2), Const(128), Const(4), Const(128)))
    isAccum(x6524_d0_b2) = false
    bufferDepthOf(x6524_d0_b2) = 1
    staticDimsOf(x6524_d0_b2) = List(2, 128, 4, 128)
    val x6524_d0_b3 = withCtrl(x7003) { SRAM(size=32768, banking=Strided(banks=16, stride=1)).name("x6524_d0_b3").srcCtx("DataGenerator.scala:76:21:wx") } // x6524 = SRAMNew(ArrayBuffer(Const(2), Const(128), Const(4), Const(128)))
    isAccum(x6524_d0_b3) = false
    bufferDepthOf(x6524_d0_b3) = 1
    staticDimsOf(x6524_d0_b3) = List(2, 128, 4, 128)
    val x6538 = withCtrl(x7003) { UnitController(style=SeqPipe, level=InnerControl).name("x6538").srcCtx("LSTMPipeBatched.scala:19:11") } // UnitPipe(List(Const(true)),Block(Const(())))
    val x6539 = withCtrl(x7003) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x6539").srcCtx("DataGenerator.scala:77:8") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x6540 = withCtrl(x7003) { Counter(min=Const(0), max=Const(128), step=Const(1), par=1).name("x6540").srcCtx("DataGenerator.scala:77:8") } // CounterNew(Const(0),Const(128),Const(1),Const(1))
    val x6541 = withCtrl(x7003) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x6541").srcCtx("DataGenerator.scala:77:8") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x6542 = withCtrl(x7003) { CounterChain(List(x6539,x6540,x6541)).name("x6542").srcCtx("DataGenerator.scala:77:8") } // CounterChainNew(List(x6539, x6540, x6541))
    val x6574 = withCtrl(x7003) { LoopController(style=StreamPipe, level=OuterControl, cchain=x6542).name("x6574").srcCtx("DataGenerator.scala:77:8") } // UnrolledForeach(List(Const(true)),x6542,Block(Const(())),List(List(b3627), List(b3628), List(b3629)),List(List(b3630), List(b3631), List(b3632)))
    val b3627 = withCtrl(x6574) { CounterIter(x6539, Some(0)).name("b3627") } // b3627
    val b3630 = withCtrl(x6574) { Const(true).name("b3630") } // b3630
    val b3628 = withCtrl(x6574) { CounterIter(x6540, Some(0)).name("b3628") } // b3628
    val b3631 = withCtrl(x6574) { Const(true).name("b3631") } // b3631
    val b3629 = withCtrl(x6574) { CounterIter(x6541, Some(0)).name("b3629") } // b3629
    val b3632 = withCtrl(x6574) { Const(true).name("b3632") } // b3632
    val b7026 = withCtrl(x6574) { StreamOut(field="offset").name("b7026").srcCtx("DataGenerator.scala:77:8") } // x6543 = StreamOutNew(BurstCmdBus)
    isAccum(b7026) = false
    bufferDepthOf(b7026) = 1
    val b7027 = withCtrl(x6574) { StreamOut(field="size").name("b7027").srcCtx("DataGenerator.scala:77:8") } // x6543 = StreamOutNew(BurstCmdBus)
    isAccum(b7027) = false
    bufferDepthOf(b7027) = 1
    val x6544 = withCtrl(x6574) { StreamIn(field="data").name("x6544").srcCtx("DataGenerator.scala:77:8") } // x6544 = StreamInNew(BurstDataBus())
    isAccum(x6544) = false
    bufferDepthOf(x6544) = 1
    val x6563 = withCtrl(x6574) { UnitController(style=SeqPipe, level=InnerControl).name("x6563").srcCtx("DataGenerator.scala:77:8") } // UnitPipe(List(b3630, b3631, b3632),Block(x6562))
    val x6545 = withCtrl(x6563) { b3627 } // FixConvert(b3627,TRUE,_32,_0) (Same Type. No op)
    val x6546 = withCtrl(x6563) { OpDef(op=FixSla, inputs=List(x6545, Const(16))).name("x6546").srcCtx("DataGenerator.scala:77:8") } // FixLsh(x6545,Const(16))
    val x6547 = withCtrl(x6563) { b3628 } // FixConvert(b3628,TRUE,_32,_0) (Same Type. No op)
    val x6548 = withCtrl(x6563) { OpDef(op=FixSla, inputs=List(x6547, Const(9))).name("x6548").srcCtx("DataGenerator.scala:77:8") } // FixLsh(x6547,Const(9))
    val x6549 = withCtrl(x6563) { b3629 } // FixConvert(b3629,TRUE,_32,_0) (Same Type. No op)
    val x6550 = withCtrl(x6563) { OpDef(op=FixSla, inputs=List(x6549, Const(7))).name("x6550").srcCtx("DataGenerator.scala:77:8") } // FixLsh(x6549,Const(7))
    val x6551 = withCtrl(x6563) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x6552 = withCtrl(x6563) { OpDef(op=FixAdd, inputs=List(x6546, x6548)).name("x6552").srcCtx("DataGenerator.scala:77:8") } // FixAdd(x6546,x6548)
    val x6553 = withCtrl(x6563) { OpDef(op=FixAdd, inputs=List(x6550, x6551)).name("x6553").srcCtx("DataGenerator.scala:77:8") } // FixAdd(x6550,x6551)
    val x6554 = withCtrl(x6563) { OpDef(op=FixAdd, inputs=List(x6552, x6553)).name("x6554").srcCtx("DataGenerator.scala:77:8") } // FixAdd(x6552,x6553)
    val x6555 = withCtrl(x6563) { OpDef(op=FixSla, inputs=List(x6554, Const(2))).name("x6555").srcCtx("DataGenerator.scala:77:8") } // FixLsh(x6554,Const(2))
    val x6556 = withCtrl(x6563) { x6555 } // FixConvert(x6555,TRUE,_64,_0)
    val x6557 = withCtrl(x6563) { DramAddress(x6522).name("x6557").srcCtx("DataGenerator.scala:77:8") } // GetDRAMAddress(x6522)
    val x6559_x6558 = withCtrl(x6563) { OpDef(op=FixAdd, inputs=List(x6556, x6557)).name("x6559_x6558").srcCtx("DataGenerator.scala:77:8") } // FixAdd(x6556,x6557)
    // x6559 = SimpleStruct(ArrayBuffer((offset,x6558), (size,Const(512)), (isLoad,Const(true))))
    val x6560 = withCtrl(x6563) { OpDef(op=BitAnd, inputs=List(b3630, b3631)).name("x6560").srcCtx("UnrollingBase.scala:28:66") } // And(b3630,b3631)
    val x6561 = withCtrl(x6563) { OpDef(op=BitAnd, inputs=List(x6560, b3632)).name("x6561").srcCtx("UnrollingBase.scala:28:66") } // And(x6560,b3632)
    val x6562_b7028_b7026 = withCtrl(x6563) { WriteMem(b7026, x6559_x6558).name("x6562_b7028_b7026").srcCtx("DataGenerator.scala:77:8") } // StreamWrite(x6543,x6559,x6561)
    val x6562_b7029_b7027 = withCtrl(x6563) { WriteMem(b7027, Const(512)).name("x6562_b7029_b7027").srcCtx("DataGenerator.scala:77:8") } // StreamWrite(x6543,x6559,x6561)
    val x6564 = withCtrl(x6574) { FringeDenseLoad(dram=List(x6522), cmdStream=List(b7026, b7027), dataStream=List(x6544)).name("x6564").srcCtx("DataGenerator.scala:77:8") } // FringeDenseLoad(x6522,x6543,x6544)
    val x6565 = withCtrl(x6574) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6565").srcCtx("DataGenerator.scala:77:8") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6566 = withCtrl(x6574) { CounterChain(List(x6565)).name("x6566").srcCtx("DataGenerator.scala:77:8") } // CounterChainNew(List(x6565))
    val x6573 = withCtrl(x6574) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6566).name("x6573").srcCtx("DataGenerator.scala:77:8") } // UnrolledForeach(List(b3630, b3631, b3632),x6566,Block(Const(())),List(List(b3657)),List(List(b3658)))
    val b3657 = withCtrl(x6573) { CounterIter(x6565, None).name("b3657") } // b3657
    val b3658 = withCtrl(x6573) { Const(true).name("b3658") } // b3658
    val x6567 = withCtrl(x6573) { OpDef(op=BitAnd, inputs=List(b3658, b3630)).name("x6567").srcCtx("UnrollingBase.scala:28:66") } // And(b3658,b3630)
    val x6568 = withCtrl(x6573) { OpDef(op=BitAnd, inputs=List(b3631, b3632)).name("x6568").srcCtx("UnrollingBase.scala:28:66") } // And(b3631,b3632)
    val x6569 = withCtrl(x6573) { OpDef(op=BitAnd, inputs=List(x6567, x6568)).name("x6569").srcCtx("UnrollingBase.scala:28:66") } // And(x6567,x6568)
    val x6570_x6570 = withCtrl(x6573) { ReadMem(x6544).name("x6570_x6570").srcCtx("DataGenerator.scala:77:8") } // ParStreamRead(x6544,List(x6569))
    val x6571_x6571 = withCtrl(x6573) { x6570_x6570 } // VectorApply(x6570,0)
    val x6572 = withCtrl(x6573) { StoreBanks(List(List(x6524_d0_b0, x6524_d0_b1, x6524_d0_b2, x6524_d0_b3)), List(b3627, b3628, b3629, b3657), x6571_x6571).name("x6572").srcCtx("DataGenerator.scala:77:8") } // ParSRAMStore(x6524,List(List(b3627, b3628, b3629, b3657)),List(x6571),List(x6569))
    val x6575 = withCtrl(x7003) { DRAM(dims=List(Const(2), Const(128), Const(4), Const(128))).name("x6575").srcCtx("DataGenerator.scala:182:20:k") } // x6575 = DRAMNew(ArrayBuffer(Const(2), Const(128), Const(4), Const(128)),Const(0))
    val x6577_d0_b0 = withCtrl(x7003) { SRAM(size=32768, banking=Strided(banks=16, stride=1)).name("x6577_d0_b0").srcCtx("DataGenerator.scala:76:21:wh") } // x6577 = SRAMNew(ArrayBuffer(Const(2), Const(128), Const(4), Const(128)))
    isAccum(x6577_d0_b0) = false
    bufferDepthOf(x6577_d0_b0) = 1
    staticDimsOf(x6577_d0_b0) = List(2, 128, 4, 128)
    val x6577_d0_b1 = withCtrl(x7003) { SRAM(size=32768, banking=Strided(banks=16, stride=1)).name("x6577_d0_b1").srcCtx("DataGenerator.scala:76:21:wh") } // x6577 = SRAMNew(ArrayBuffer(Const(2), Const(128), Const(4), Const(128)))
    isAccum(x6577_d0_b1) = false
    bufferDepthOf(x6577_d0_b1) = 1
    staticDimsOf(x6577_d0_b1) = List(2, 128, 4, 128)
    val x6577_d0_b2 = withCtrl(x7003) { SRAM(size=32768, banking=Strided(banks=16, stride=1)).name("x6577_d0_b2").srcCtx("DataGenerator.scala:76:21:wh") } // x6577 = SRAMNew(ArrayBuffer(Const(2), Const(128), Const(4), Const(128)))
    isAccum(x6577_d0_b2) = false
    bufferDepthOf(x6577_d0_b2) = 1
    staticDimsOf(x6577_d0_b2) = List(2, 128, 4, 128)
    val x6577_d0_b3 = withCtrl(x7003) { SRAM(size=32768, banking=Strided(banks=16, stride=1)).name("x6577_d0_b3").srcCtx("DataGenerator.scala:76:21:wh") } // x6577 = SRAMNew(ArrayBuffer(Const(2), Const(128), Const(4), Const(128)))
    isAccum(x6577_d0_b3) = false
    bufferDepthOf(x6577_d0_b3) = 1
    staticDimsOf(x6577_d0_b3) = List(2, 128, 4, 128)
    val x6591 = withCtrl(x7003) { UnitController(style=SeqPipe, level=InnerControl).name("x6591").srcCtx("LSTMPipeBatched.scala:19:11") } // UnitPipe(List(Const(true)),Block(Const(())))
    val x6592 = withCtrl(x7003) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x6592").srcCtx("DataGenerator.scala:77:8") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x6593 = withCtrl(x7003) { Counter(min=Const(0), max=Const(128), step=Const(1), par=1).name("x6593").srcCtx("DataGenerator.scala:77:8") } // CounterNew(Const(0),Const(128),Const(1),Const(1))
    val x6594 = withCtrl(x7003) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x6594").srcCtx("DataGenerator.scala:77:8") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x6595 = withCtrl(x7003) { CounterChain(List(x6592,x6593,x6594)).name("x6595").srcCtx("DataGenerator.scala:77:8") } // CounterChainNew(List(x6592, x6593, x6594))
    val x6627 = withCtrl(x7003) { LoopController(style=StreamPipe, level=OuterControl, cchain=x6595).name("x6627").srcCtx("DataGenerator.scala:77:8") } // UnrolledForeach(List(Const(true)),x6595,Block(Const(())),List(List(b3688), List(b3689), List(b3690)),List(List(b3691), List(b3692), List(b3693)))
    val b3688 = withCtrl(x6627) { CounterIter(x6592, Some(0)).name("b3688") } // b3688
    val b3691 = withCtrl(x6627) { Const(true).name("b3691") } // b3691
    val b3689 = withCtrl(x6627) { CounterIter(x6593, Some(0)).name("b3689") } // b3689
    val b3692 = withCtrl(x6627) { Const(true).name("b3692") } // b3692
    val b3690 = withCtrl(x6627) { CounterIter(x6594, Some(0)).name("b3690") } // b3690
    val b3693 = withCtrl(x6627) { Const(true).name("b3693") } // b3693
    val b7030 = withCtrl(x6627) { StreamOut(field="offset").name("b7030").srcCtx("DataGenerator.scala:77:8") } // x6596 = StreamOutNew(BurstCmdBus)
    isAccum(b7030) = false
    bufferDepthOf(b7030) = 1
    val b7031 = withCtrl(x6627) { StreamOut(field="size").name("b7031").srcCtx("DataGenerator.scala:77:8") } // x6596 = StreamOutNew(BurstCmdBus)
    isAccum(b7031) = false
    bufferDepthOf(b7031) = 1
    val x6597 = withCtrl(x6627) { StreamIn(field="data").name("x6597").srcCtx("DataGenerator.scala:77:8") } // x6597 = StreamInNew(BurstDataBus())
    isAccum(x6597) = false
    bufferDepthOf(x6597) = 1
    val x6616 = withCtrl(x6627) { UnitController(style=SeqPipe, level=InnerControl).name("x6616").srcCtx("DataGenerator.scala:77:8") } // UnitPipe(List(b3691, b3692, b3693),Block(x6615))
    val x6598 = withCtrl(x6616) { b3688 } // FixConvert(b3688,TRUE,_32,_0) (Same Type. No op)
    val x6599 = withCtrl(x6616) { OpDef(op=FixSla, inputs=List(x6598, Const(16))).name("x6599").srcCtx("DataGenerator.scala:77:8") } // FixLsh(x6598,Const(16))
    val x6600 = withCtrl(x6616) { b3689 } // FixConvert(b3689,TRUE,_32,_0) (Same Type. No op)
    val x6601 = withCtrl(x6616) { OpDef(op=FixSla, inputs=List(x6600, Const(9))).name("x6601").srcCtx("DataGenerator.scala:77:8") } // FixLsh(x6600,Const(9))
    val x6602 = withCtrl(x6616) { b3690 } // FixConvert(b3690,TRUE,_32,_0) (Same Type. No op)
    val x6603 = withCtrl(x6616) { OpDef(op=FixSla, inputs=List(x6602, Const(7))).name("x6603").srcCtx("DataGenerator.scala:77:8") } // FixLsh(x6602,Const(7))
    val x6604 = withCtrl(x6616) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x6605 = withCtrl(x6616) { OpDef(op=FixAdd, inputs=List(x6599, x6601)).name("x6605").srcCtx("DataGenerator.scala:77:8") } // FixAdd(x6599,x6601)
    val x6606 = withCtrl(x6616) { OpDef(op=FixAdd, inputs=List(x6603, x6604)).name("x6606").srcCtx("DataGenerator.scala:77:8") } // FixAdd(x6603,x6604)
    val x6607 = withCtrl(x6616) { OpDef(op=FixAdd, inputs=List(x6605, x6606)).name("x6607").srcCtx("DataGenerator.scala:77:8") } // FixAdd(x6605,x6606)
    val x6608 = withCtrl(x6616) { OpDef(op=FixSla, inputs=List(x6607, Const(2))).name("x6608").srcCtx("DataGenerator.scala:77:8") } // FixLsh(x6607,Const(2))
    val x6609 = withCtrl(x6616) { x6608 } // FixConvert(x6608,TRUE,_64,_0)
    val x6610 = withCtrl(x6616) { DramAddress(x6575).name("x6610").srcCtx("DataGenerator.scala:77:8") } // GetDRAMAddress(x6575)
    val x6612_x6611 = withCtrl(x6616) { OpDef(op=FixAdd, inputs=List(x6609, x6610)).name("x6612_x6611").srcCtx("DataGenerator.scala:77:8") } // FixAdd(x6609,x6610)
    // x6612 = SimpleStruct(ArrayBuffer((offset,x6611), (size,Const(512)), (isLoad,Const(true))))
    val x6613 = withCtrl(x6616) { OpDef(op=BitAnd, inputs=List(b3691, b3692)).name("x6613").srcCtx("UnrollingBase.scala:28:66") } // And(b3691,b3692)
    val x6614 = withCtrl(x6616) { OpDef(op=BitAnd, inputs=List(x6613, b3693)).name("x6614").srcCtx("UnrollingBase.scala:28:66") } // And(x6613,b3693)
    val x6615_b7032_b7030 = withCtrl(x6616) { WriteMem(b7030, x6612_x6611).name("x6615_b7032_b7030").srcCtx("DataGenerator.scala:77:8") } // StreamWrite(x6596,x6612,x6614)
    val x6615_b7033_b7031 = withCtrl(x6616) { WriteMem(b7031, Const(512)).name("x6615_b7033_b7031").srcCtx("DataGenerator.scala:77:8") } // StreamWrite(x6596,x6612,x6614)
    val x6617 = withCtrl(x6627) { FringeDenseLoad(dram=List(x6575), cmdStream=List(b7030, b7031), dataStream=List(x6597)).name("x6617").srcCtx("DataGenerator.scala:77:8") } // FringeDenseLoad(x6575,x6596,x6597)
    val x6618 = withCtrl(x6627) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6618").srcCtx("DataGenerator.scala:77:8") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6619 = withCtrl(x6627) { CounterChain(List(x6618)).name("x6619").srcCtx("DataGenerator.scala:77:8") } // CounterChainNew(List(x6618))
    val x6626 = withCtrl(x6627) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6619).name("x6626").srcCtx("DataGenerator.scala:77:8") } // UnrolledForeach(List(b3691, b3692, b3693),x6619,Block(Const(())),List(List(b3718)),List(List(b3719)))
    val b3718 = withCtrl(x6626) { CounterIter(x6618, None).name("b3718") } // b3718
    val b3719 = withCtrl(x6626) { Const(true).name("b3719") } // b3719
    val x6620 = withCtrl(x6626) { OpDef(op=BitAnd, inputs=List(b3719, b3691)).name("x6620").srcCtx("UnrollingBase.scala:28:66") } // And(b3719,b3691)
    val x6621 = withCtrl(x6626) { OpDef(op=BitAnd, inputs=List(b3692, b3693)).name("x6621").srcCtx("UnrollingBase.scala:28:66") } // And(b3692,b3693)
    val x6622 = withCtrl(x6626) { OpDef(op=BitAnd, inputs=List(x6620, x6621)).name("x6622").srcCtx("UnrollingBase.scala:28:66") } // And(x6620,x6621)
    val x6623_x6623 = withCtrl(x6626) { ReadMem(x6597).name("x6623_x6623").srcCtx("DataGenerator.scala:77:8") } // ParStreamRead(x6597,List(x6622))
    val x6624_x6624 = withCtrl(x6626) { x6623_x6623 } // VectorApply(x6623,0)
    val x6625 = withCtrl(x6626) { StoreBanks(List(List(x6577_d0_b0, x6577_d0_b1, x6577_d0_b2, x6577_d0_b3)), List(b3688, b3689, b3690, b3718), x6624_x6624).name("x6625").srcCtx("DataGenerator.scala:77:8") } // ParSRAMStore(x6577,List(List(b3688, b3689, b3690, b3718)),List(x6624),List(x6622))
    val x6628 = withCtrl(x7003) { DRAM(dims=List(Const(2), Const(4), Const(128))).name("x6628").srcCtx("DataGenerator.scala:168:20:k") } // x6628 = DRAMNew(ArrayBuffer(Const(2), Const(4), Const(128)),Const(0))
    val x6630_d0_b0 = withCtrl(x7003) { SRAM(size=1024, banking=Strided(banks=16, stride=1)).name("x6630_d0_b0").srcCtx("DataGenerator.scala:59:21:b") } // x6630 = SRAMNew(ArrayBuffer(Const(2), Const(4), Const(128)))
    isAccum(x6630_d0_b0) = false
    bufferDepthOf(x6630_d0_b0) = 1
    staticDimsOf(x6630_d0_b0) = List(2, 4, 128)
    val x6641 = withCtrl(x7003) { UnitController(style=SeqPipe, level=InnerControl).name("x6641").srcCtx("LSTMPipeBatched.scala:19:11") } // UnitPipe(List(Const(true)),Block(Const(())))
    val x6642 = withCtrl(x7003) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x6642").srcCtx("DataGenerator.scala:60:8") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x6643 = withCtrl(x7003) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x6643").srcCtx("DataGenerator.scala:60:8") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x6644 = withCtrl(x7003) { CounterChain(List(x6642,x6643)).name("x6644").srcCtx("DataGenerator.scala:60:8") } // CounterChainNew(List(x6642, x6643))
    val x6671 = withCtrl(x7003) { LoopController(style=StreamPipe, level=OuterControl, cchain=x6644).name("x6671").srcCtx("DataGenerator.scala:60:8") } // UnrolledForeach(List(Const(true)),x6644,Block(Const(())),List(List(b3745), List(b3746)),List(List(b3747), List(b3748)))
    val b3745 = withCtrl(x6671) { CounterIter(x6642, Some(0)).name("b3745") } // b3745
    val b3747 = withCtrl(x6671) { Const(true).name("b3747") } // b3747
    val b3746 = withCtrl(x6671) { CounterIter(x6643, Some(0)).name("b3746") } // b3746
    val b3748 = withCtrl(x6671) { Const(true).name("b3748") } // b3748
    val b7034 = withCtrl(x6671) { StreamOut(field="offset").name("b7034").srcCtx("DataGenerator.scala:60:8") } // x6645 = StreamOutNew(BurstCmdBus)
    isAccum(b7034) = false
    bufferDepthOf(b7034) = 1
    val b7035 = withCtrl(x6671) { StreamOut(field="size").name("b7035").srcCtx("DataGenerator.scala:60:8") } // x6645 = StreamOutNew(BurstCmdBus)
    isAccum(b7035) = false
    bufferDepthOf(b7035) = 1
    val x6646 = withCtrl(x6671) { StreamIn(field="data").name("x6646").srcCtx("DataGenerator.scala:60:8") } // x6646 = StreamInNew(BurstDataBus())
    isAccum(x6646) = false
    bufferDepthOf(x6646) = 1
    val x6661 = withCtrl(x6671) { UnitController(style=SeqPipe, level=InnerControl).name("x6661").srcCtx("DataGenerator.scala:60:8") } // UnitPipe(List(b3747, b3748),Block(x6660))
    val x6647 = withCtrl(x6661) { b3745 } // FixConvert(b3745,TRUE,_32,_0) (Same Type. No op)
    val x6648 = withCtrl(x6661) { OpDef(op=FixSla, inputs=List(x6647, Const(9))).name("x6648").srcCtx("DataGenerator.scala:60:8") } // FixLsh(x6647,Const(9))
    val x6649 = withCtrl(x6661) { b3746 } // FixConvert(b3746,TRUE,_32,_0) (Same Type. No op)
    val x6650 = withCtrl(x6661) { OpDef(op=FixSla, inputs=List(x6649, Const(7))).name("x6650").srcCtx("DataGenerator.scala:60:8") } // FixLsh(x6649,Const(7))
    val x6651 = withCtrl(x6661) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x6652 = withCtrl(x6661) { OpDef(op=FixAdd, inputs=List(x6648, x6650)).name("x6652").srcCtx("DataGenerator.scala:60:8") } // FixAdd(x6648,x6650)
    val x6653 = withCtrl(x6661) { OpDef(op=FixAdd, inputs=List(x6652, x6651)).name("x6653").srcCtx("DataGenerator.scala:60:8") } // FixAdd(x6652,x6651)
    val x6654 = withCtrl(x6661) { OpDef(op=FixSla, inputs=List(x6653, Const(2))).name("x6654").srcCtx("DataGenerator.scala:60:8") } // FixLsh(x6653,Const(2))
    val x6655 = withCtrl(x6661) { x6654 } // FixConvert(x6654,TRUE,_64,_0)
    val x6656 = withCtrl(x6661) { DramAddress(x6628).name("x6656").srcCtx("DataGenerator.scala:60:8") } // GetDRAMAddress(x6628)
    val x6658_x6657 = withCtrl(x6661) { OpDef(op=FixAdd, inputs=List(x6655, x6656)).name("x6658_x6657").srcCtx("DataGenerator.scala:60:8") } // FixAdd(x6655,x6656)
    // x6658 = SimpleStruct(ArrayBuffer((offset,x6657), (size,Const(512)), (isLoad,Const(true))))
    val x6659 = withCtrl(x6661) { OpDef(op=BitAnd, inputs=List(b3747, b3748)).name("x6659").srcCtx("UnrollingBase.scala:28:66") } // And(b3747,b3748)
    val x6660_b7036_b7034 = withCtrl(x6661) { WriteMem(b7034, x6658_x6657).name("x6660_b7036_b7034").srcCtx("DataGenerator.scala:60:8") } // StreamWrite(x6645,x6658,x6659)
    val x6660_b7037_b7035 = withCtrl(x6661) { WriteMem(b7035, Const(512)).name("x6660_b7037_b7035").srcCtx("DataGenerator.scala:60:8") } // StreamWrite(x6645,x6658,x6659)
    val x6662 = withCtrl(x6671) { FringeDenseLoad(dram=List(x6628), cmdStream=List(b7034, b7035), dataStream=List(x6646)).name("x6662").srcCtx("DataGenerator.scala:60:8") } // FringeDenseLoad(x6628,x6645,x6646)
    val x6663 = withCtrl(x6671) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6663").srcCtx("DataGenerator.scala:60:8") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6664 = withCtrl(x6671) { CounterChain(List(x6663)).name("x6664").srcCtx("DataGenerator.scala:60:8") } // CounterChainNew(List(x6663))
    val x6670 = withCtrl(x6671) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6664).name("x6670").srcCtx("DataGenerator.scala:60:8") } // UnrolledForeach(List(b3747, b3748),x6664,Block(Const(())),List(List(b3769)),List(List(b3770)))
    val b3769 = withCtrl(x6670) { CounterIter(x6663, None).name("b3769") } // b3769
    val b3770 = withCtrl(x6670) { Const(true).name("b3770") } // b3770
    val x6665 = withCtrl(x6670) { OpDef(op=BitAnd, inputs=List(b3770, b3747)).name("x6665").srcCtx("UnrollingBase.scala:28:66") } // And(b3770,b3747)
    val x6666 = withCtrl(x6670) { OpDef(op=BitAnd, inputs=List(x6665, b3748)).name("x6666").srcCtx("UnrollingBase.scala:28:66") } // And(x6665,b3748)
    val x6667_x6667 = withCtrl(x6670) { ReadMem(x6646).name("x6667_x6667").srcCtx("DataGenerator.scala:60:8") } // ParStreamRead(x6646,List(x6666))
    val x6668_x6668 = withCtrl(x6670) { x6667_x6667 } // VectorApply(x6667,0)
    val x6669 = withCtrl(x6670) { StoreBanks(List(List(x6630_d0_b0)), List(b3745, b3746, b3769), x6668_x6668).name("x6669").srcCtx("DataGenerator.scala:60:8") } // ParSRAMStore(x6630,List(List(b3745, b3746, b3769)),List(x6668),List(x6666))
    val x6672 = withCtrl(x7003) { Counter(min=Const(0), max=Const(8), step=Const(1), par=1).name("x6672").srcCtx("LSTMPipeBatched.scala:26:41") } // CounterNew(Const(0),Const(8),Const(1),Const(1))
    val x6673 = withCtrl(x7003) { CounterChain(List(x6672)).name("x6673").srcCtx("LSTMPipeBatched.scala:26:47") } // CounterChainNew(List(x6672))
    val x7002 = withCtrl(x7003) { LoopController(style=SeqPipe, level=OuterControl, cchain=x6673).name("x7002").srcCtx("LSTMPipeBatched.scala:26:47") } // UnrolledForeach(List(Const(true)),x6673,Block(Const(())),List(List(b3780)),List(List(b3781)))
    val b3780 = withCtrl(x7002) { CounterIter(x6672, Some(0)).name("b3780") } // b3780
    val b3781 = withCtrl(x7002) { Const(true).name("b3781") } // b3781
    val x6674_d0_b0 = withCtrl(x7002) { SRAM(size=1024, banking=Strided(banks=16, stride=1)).name("x6674_d0_b0").srcCtx("LSTMPipeBatched.scala:27:24:x") } // x6674 = SRAMNew(ArrayBuffer(Const(8), Const(128)))
    isAccum(x6674_d0_b0) = false
    bufferDepthOf(x6674_d0_b0) = 1
    staticDimsOf(x6674_d0_b0) = List(8, 128)
    val x6675 = withCtrl(x7002) { DRAM(dims=List(Const(8), Const(8), Const(128))).name("x6675").srcCtx("DataGenerator.scala:168:20:k") } // x6675 = DRAMNew(ArrayBuffer(Const(8), Const(8), Const(128)),Const(0))
    val x6690 = withCtrl(x7002) { UnitController(style=SeqPipe, level=InnerControl).name("x6690").srcCtx("LSTMPipeBatched.scala:26:47") } // UnitPipe(List(b3781),Block(Const(())))
    val x6687 = withCtrl(x6690) { OpDef(op=FixAdd, inputs=List(b3780, Const(1))).name("x6687").srcCtx("LSTMPipeBatched.scala:28:22") } // FixAdd(b3780,Const(1))
    val x6691 = withCtrl(x7002) { Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x6691").srcCtx("LSTMPipeBatched.scala:28:11") } // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x6692 = withCtrl(x7002) { Counter(min=Const(0), max=Const(8), step=Const(1), par=1).name("x6692").srcCtx("LSTMPipeBatched.scala:28:11") } // CounterNew(Const(0),Const(8),Const(1),Const(1))
    val x6693 = withCtrl(x7002) { CounterChain(List(x6691,x6692)).name("x6693").srcCtx("LSTMPipeBatched.scala:28:11") } // CounterChainNew(List(x6691, x6692))
    val x6723 = withCtrl(x7002) { LoopController(style=StreamPipe, level=OuterControl, cchain=x6693).name("x6723").srcCtx("LSTMPipeBatched.scala:28:11") } // UnrolledForeach(List(b3781),x6693,Block(Const(())),List(List(b3802), List(b3803)),List(List(b3804), List(b3805)))
    val b3802 = withCtrl(x6723) { CounterIter(x6691, Some(0)).name("b3802") } // b3802
    val b3804 = withCtrl(x6723) { Const(true).name("b3804") } // b3804
    val b3803 = withCtrl(x6723) { CounterIter(x6692, Some(0)).name("b3803") } // b3803
    val b3805 = withCtrl(x6723) { Const(true).name("b3805") } // b3805
    val b7038 = withCtrl(x6723) { StreamOut(field="offset").name("b7038").srcCtx("LSTMPipeBatched.scala:28:11") } // x6694 = StreamOutNew(BurstCmdBus)
    isAccum(b7038) = false
    bufferDepthOf(b7038) = 1
    val b7039 = withCtrl(x6723) { StreamOut(field="size").name("b7039").srcCtx("LSTMPipeBatched.scala:28:11") } // x6694 = StreamOutNew(BurstCmdBus)
    isAccum(b7039) = false
    bufferDepthOf(b7039) = 1
    val x6695 = withCtrl(x6723) { StreamIn(field="data").name("x6695").srcCtx("LSTMPipeBatched.scala:28:11") } // x6695 = StreamInNew(BurstDataBus())
    isAccum(x6695) = false
    bufferDepthOf(x6695) = 1
    val x6712 = withCtrl(x6723) { UnitController(style=SeqPipe, level=InnerControl).name("x6712").srcCtx("LSTMPipeBatched.scala:28:11") } // UnitPipe(List(b3804, b3805, b3781),Block(x6711))
    val x6696 = withCtrl(x6712) { OpDef(op=FixAdd, inputs=List(b3780, b3802)).name("x6696").srcCtx("LSTMPipeBatched.scala:28:11") } // FixAdd(b3780,b3802)
    val x6697 = withCtrl(x6712) { x6696 } // FixConvert(x6696,TRUE,_32,_0) (Same Type. No op)
    val x6698 = withCtrl(x6712) { OpDef(op=FixSla, inputs=List(x6697, Const(10))).name("x6698").srcCtx("LSTMPipeBatched.scala:28:11") } // FixLsh(x6697,Const(10))
    val x6699 = withCtrl(x6712) { b3803 } // FixConvert(b3803,TRUE,_32,_0) (Same Type. No op)
    val x6700 = withCtrl(x6712) { OpDef(op=FixSla, inputs=List(x6699, Const(7))).name("x6700").srcCtx("LSTMPipeBatched.scala:28:11") } // FixLsh(x6699,Const(7))
    val x6701 = withCtrl(x6712) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x6702 = withCtrl(x6712) { OpDef(op=FixAdd, inputs=List(x6698, x6700)).name("x6702").srcCtx("LSTMPipeBatched.scala:28:11") } // FixAdd(x6698,x6700)
    val x6703 = withCtrl(x6712) { OpDef(op=FixAdd, inputs=List(x6702, x6701)).name("x6703").srcCtx("LSTMPipeBatched.scala:28:11") } // FixAdd(x6702,x6701)
    val x6704 = withCtrl(x6712) { OpDef(op=FixSla, inputs=List(x6703, Const(2))).name("x6704").srcCtx("LSTMPipeBatched.scala:28:11") } // FixLsh(x6703,Const(2))
    val x6705 = withCtrl(x6712) { x6704 } // FixConvert(x6704,TRUE,_64,_0)
    val x6706 = withCtrl(x6712) { DramAddress(x6675).name("x6706").srcCtx("LSTMPipeBatched.scala:28:11") } // GetDRAMAddress(x6675)
    val x6708_x6707 = withCtrl(x6712) { OpDef(op=FixAdd, inputs=List(x6705, x6706)).name("x6708_x6707").srcCtx("LSTMPipeBatched.scala:28:11") } // FixAdd(x6705,x6706)
    // x6708 = SimpleStruct(ArrayBuffer((offset,x6707), (size,Const(512)), (isLoad,Const(true))))
    val x6709 = withCtrl(x6712) { OpDef(op=BitAnd, inputs=List(b3804, b3805)).name("x6709").srcCtx("UnrollingBase.scala:28:66") } // And(b3804,b3805)
    val x6710 = withCtrl(x6712) { OpDef(op=BitAnd, inputs=List(x6709, b3781)).name("x6710").srcCtx("UnrollingBase.scala:28:66") } // And(x6709,b3781)
    val x6711_b7040_b7038 = withCtrl(x6712) { WriteMem(b7038, x6708_x6707).name("x6711_b7040_b7038").srcCtx("LSTMPipeBatched.scala:28:11") } // StreamWrite(x6694,x6708,x6710)
    val x6711_b7041_b7039 = withCtrl(x6712) { WriteMem(b7039, Const(512)).name("x6711_b7041_b7039").srcCtx("LSTMPipeBatched.scala:28:11") } // StreamWrite(x6694,x6708,x6710)
    val x6713 = withCtrl(x6723) { FringeDenseLoad(dram=List(x6675), cmdStream=List(b7038, b7039), dataStream=List(x6695)).name("x6713").srcCtx("LSTMPipeBatched.scala:28:11") } // FringeDenseLoad(x6675,x6694,x6695)
    val x6714 = withCtrl(x6723) { Counter(min=Const(0), max=Const(128), step=Const(1), par=1).name("x6714").srcCtx("LSTMPipeBatched.scala:28:11") } // CounterNew(Const(0),Const(128),Const(1),Const(1))
    val x6715 = withCtrl(x6723) { CounterChain(List(x6714)).name("x6715").srcCtx("LSTMPipeBatched.scala:28:11") } // CounterChainNew(List(x6714))
    val x6722 = withCtrl(x6723) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6715).name("x6722").srcCtx("LSTMPipeBatched.scala:28:11") } // UnrolledForeach(List(b3804, b3805, b3781),x6715,Block(Const(())),List(List(b3828)),List(List(b3829)))
    val b3828 = withCtrl(x6722) { CounterIter(x6714, None).name("b3828") } // b3828
    val b3829 = withCtrl(x6722) { Const(true).name("b3829") } // b3829
    val x6716 = withCtrl(x6722) { OpDef(op=BitAnd, inputs=List(b3829, b3804)).name("x6716").srcCtx("UnrollingBase.scala:28:66") } // And(b3829,b3804)
    val x6717 = withCtrl(x6722) { OpDef(op=BitAnd, inputs=List(b3805, b3781)).name("x6717").srcCtx("UnrollingBase.scala:28:66") } // And(b3805,b3781)
    val x6718 = withCtrl(x6722) { OpDef(op=BitAnd, inputs=List(x6716, x6717)).name("x6718").srcCtx("UnrollingBase.scala:28:66") } // And(x6716,x6717)
    val x6719_x6719 = withCtrl(x6722) { ReadMem(x6695).name("x6719_x6719").srcCtx("LSTMPipeBatched.scala:28:11") } // ParStreamRead(x6695,List(x6718))
    val x6720_x6720 = withCtrl(x6722) { x6719_x6719 } // VectorApply(x6719,0)
    val x6721 = withCtrl(x6722) { StoreBanks(List(List(x6674_d0_b0)), List(b3803, b3828), x6720_x6720).name("x6721").srcCtx("LSTMPipeBatched.scala:28:11") } // ParSRAMStore(x6674,List(List(b3803, b3828)),List(x6720),List(x6718))
    val x6724 = withCtrl(x7002) { Counter(min=Const(0), max=Const(8), step=Const(1), par=1).name("x6724").srcCtx("LSTMPipeBatched.scala:29:30") } // CounterNew(Const(0),Const(8),Const(1),Const(1))
    val x6725 = withCtrl(x7002) { CounterChain(List(x6724)).name("x6725").srcCtx("LSTMPipeBatched.scala:29:43") } // CounterChainNew(List(x6724))
    val x7001 = withCtrl(x7002) { LoopController(style=MetaPipe, level=OuterControl, cchain=x6725).name("x7001").srcCtx("LSTMPipeBatched.scala:29:43") } // UnrolledForeach(List(b3781),x6725,Block(Const(())),List(List(b3840)),List(List(b3841)))
    val b3840 = withCtrl(x7001) { CounterIter(x6724, Some(0)).name("b3840") } // b3840
    val b3841 = withCtrl(x7001) { Const(true).name("b3841") } // b3841
    val x7000 = withCtrl(x7001) { UnitController(style=SeqPipe, level=OuterControl).name("x7000").srcCtx("LSTMPipeStep.scala:16:10") } // UnitPipe(List(b3841, b3781),Block(Const(())))
    val x6726_d0_b0 = withCtrl(x7000) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x6726_d0_b0").srcCtx("LSTMPipeStep.scala:17:29:layerBuf") } // x6726 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x6726_d0_b0) = false
    bufferDepthOf(x6726_d0_b0) = 1
    staticDimsOf(x6726_d0_b0) = List(128)
    val x6726_d1_b0 = withCtrl(x7000) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x6726_d1_b0").srcCtx("LSTMPipeStep.scala:17:29:layerBuf") } // x6726 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x6726_d1_b0) = false
    bufferDepthOf(x6726_d1_b0) = 1
    staticDimsOf(x6726_d1_b0) = List(128)
    val x6727 = withCtrl(x7000) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6727").srcCtx("LSTMPipeStep.scala:18:31") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6728 = withCtrl(x7000) { CounterChain(List(x6727)).name("x6728").srcCtx("LSTMPipeStep.scala:18:45") } // CounterChainNew(List(x6727))
    val x6734 = withCtrl(x7000) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6728).name("x6734").srcCtx("LSTMPipeStep.scala:18:45") } // UnrolledForeach(List(b3841, b3781),x6728,Block(Const(())),List(List(b3845)),List(List(b3846)))
    val b3845 = withCtrl(x6734) { CounterIter(x6727, None).name("b3845") } // b3845
    val b3846 = withCtrl(x6734) { Const(true).name("b3846") } // b3846
    val x6729 = withCtrl(x6734) { OpDef(op=BitAnd, inputs=List(b3846, b3841)).name("x6729").srcCtx("UnrollingBase.scala:28:66") } // And(b3846,b3841)
    val x6730 = withCtrl(x6734) { OpDef(op=BitAnd, inputs=List(x6729, b3781)).name("x6730").srcCtx("UnrollingBase.scala:28:66") } // And(x6729,b3781)
    val x6731 = withCtrl(x6734) { LoadBanks(List(x6674_d0_b0), List(b3840, b3845)).name("x6731").srcCtx("LSTMPipeStep.scala:19:31") } // ParSRAMLoad(x6674,List(List(b3840, b3845)),List(x6730))
    val x6732 = withCtrl(x6734) { x6731 } // VectorApply(x6731,0)
    val x6733 = withCtrl(x6734) { StoreBanks(List(List(x6726_d0_b0), List(x6726_d1_b0)), List(b3845), x6732).name("x6733").srcCtx("LSTMPipeStep.scala:19:28") } // ParSRAMStore(x6726,List(List(b3845)),List(x6732),List(x6730))
    val x6735 = withCtrl(x7000) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x6735").srcCtx("LSTMPipeStep.scala:22:24") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x6736 = withCtrl(x7000) { CounterChain(List(x6735)).name("x6736").srcCtx("LSTMPipeStep.scala:22:30") } // CounterChainNew(List(x6735))
    val x6953 = withCtrl(x7000) { LoopController(style=MetaPipe, level=OuterControl, cchain=x6736).name("x6953").srcCtx("LSTMPipeStep.scala:22:30") } // UnrolledForeach(List(b3841, b3781),x6736,Block(Const(())),List(List(b3855)),List(List(b3856)))
    val b3855 = withCtrl(x6953) { CounterIter(x6735, Some(0)).name("b3855") } // b3855
    val b3856 = withCtrl(x6953) { Const(true).name("b3856") } // b3856
    val x6952 = withCtrl(x6953) { UnitController(style=SeqPipe, level=OuterControl).name("x6952").srcCtx("LSTMPipeStep.scala:23:14") } // UnitPipe(List(b3856, b3841, b3781),Block(Const(())))
    val x6737_d0_b0 = withCtrl(x6952) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x6737_d0_b0").srcCtx("LSTMPipeStep.scala:24:34:reduceMem") } // x6737 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x6737_d0_b0) = false
    bufferDepthOf(x6737_d0_b0) = 1
    staticDimsOf(x6737_d0_b0) = List(4, 128)
    val x6737_d1_b0 = withCtrl(x6952) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x6737_d1_b0").srcCtx("LSTMPipeStep.scala:24:34:reduceMem") } // x6737 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x6737_d1_b0) = true
    bufferDepthOf(x6737_d1_b0) = 1
    staticDimsOf(x6737_d1_b0) = List(4, 128)
    val x6738_d0_b0 = withCtrl(x6952) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x6738_d0_b0").srcCtx("LSTMPipeStep.scala:25:32:foldMem") } // x6738 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x6738_d0_b0) = false
    bufferDepthOf(x6738_d0_b0) = 1
    staticDimsOf(x6738_d0_b0) = List(4, 128)
    val x6739 = withCtrl(x6952) { Counter(min=Const(0), max=Const(128), step=Const(1), par=1).name("x6739").srcCtx("LSTMPipeStep.scala:28:63") } // CounterNew(Const(0),Const(128),Const(1),Const(1))
    val x6740 = withCtrl(x6952) { CounterChain(List(x6739)).name("x6740").srcCtx("LSTMPipeStep.scala:41:14") } // CounterChainNew(List(x6739))
    val x6848 = withCtrl(x6952) { LoopController(style=MetaPipe, level=OuterControl, cchain=x6740).name("x6848").srcCtx("LSTMPipeStep.scala:41:14") } // UnrolledReduce(List(b3856, b3841, b3781),x6740,x6737,Block((x6737) => Const(())),List(List(b3864)),List(List(b3865)))
    val b3864 = withCtrl(x6848) { CounterIter(x6739, Some(0)).name("b3864") } // b3864
    val b3865 = withCtrl(x6848) { Const(true).name("b3865") } // b3865
    val x6741_d0_b0 = withCtrl(x6848) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x6741_d0_b0").srcCtx("LSTMPipeStep.scala:29:31:re") } // x6741 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x6741_d0_b0) = false
    bufferDepthOf(x6741_d0_b0) = 2
    staticDimsOf(x6741_d0_b0) = List(4, 128)
    val x6741_d0_b1 = withCtrl(x6848) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x6741_d0_b1").srcCtx("LSTMPipeStep.scala:29:31:re") } // x6741 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x6741_d0_b1) = false
    bufferDepthOf(x6741_d0_b1) = 2
    staticDimsOf(x6741_d0_b1) = List(4, 128)
    val x6741_d0_b2 = withCtrl(x6848) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x6741_d0_b2").srcCtx("LSTMPipeStep.scala:29:31:re") } // x6741 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x6741_d0_b2) = false
    bufferDepthOf(x6741_d0_b2) = 2
    staticDimsOf(x6741_d0_b2) = List(4, 128)
    val x6741_d0_b3 = withCtrl(x6848) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x6741_d0_b3").srcCtx("LSTMPipeStep.scala:29:31:re") } // x6741 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x6741_d0_b3) = false
    bufferDepthOf(x6741_d0_b3) = 2
    staticDimsOf(x6741_d0_b3) = List(4, 128)
    val x6742_d0 = withCtrl(x6848) { Reg(init=Some(0.0)).name("x6742_d0").srcCtx("LSTMPipeStep.scala:41:14") } // x6742 = RegNew(Const(0))
    isAccum(x6742_d0) = false
    bufferDepthOf(x6742_d0) = 2
    val x6742_d1 = withCtrl(x6848) { Reg(init=Some(0.0)).name("x6742_d1").srcCtx("LSTMPipeStep.scala:41:14") } // x6742 = RegNew(Const(0))
    isAccum(x6742_d1) = false
    bufferDepthOf(x6742_d1) = 2
    val x6742_d2 = withCtrl(x6848) { Reg(init=Some(0.0)).name("x6742_d2").srcCtx("LSTMPipeStep.scala:41:14") } // x6742 = RegNew(Const(0))
    isAccum(x6742_d2) = false
    bufferDepthOf(x6742_d2) = 2
    val x6742_d3 = withCtrl(x6848) { Reg(init=Some(0.0)).name("x6742_d3").srcCtx("LSTMPipeStep.scala:41:14") } // x6742 = RegNew(Const(0))
    isAccum(x6742_d3) = false
    bufferDepthOf(x6742_d3) = 2
    val x6743_d0 = withCtrl(x6848) { Reg(init=Some(0.0)).name("x6743_d0").srcCtx("LSTMPipeStep.scala:41:14") } // x6743 = RegNew(Const(0))
    isAccum(x6743_d0) = false
    bufferDepthOf(x6743_d0) = 2
    val x6743_d1 = withCtrl(x6848) { Reg(init=Some(0.0)).name("x6743_d1").srcCtx("LSTMPipeStep.scala:41:14") } // x6743 = RegNew(Const(0))
    isAccum(x6743_d1) = false
    bufferDepthOf(x6743_d1) = 2
    val x6743_d2 = withCtrl(x6848) { Reg(init=Some(0.0)).name("x6743_d2").srcCtx("LSTMPipeStep.scala:41:14") } // x6743 = RegNew(Const(0))
    isAccum(x6743_d2) = false
    bufferDepthOf(x6743_d2) = 2
    val x6743_d3 = withCtrl(x6848) { Reg(init=Some(0.0)).name("x6743_d3").srcCtx("LSTMPipeStep.scala:41:14") } // x6743 = RegNew(Const(0))
    isAccum(x6743_d3) = false
    bufferDepthOf(x6743_d3) = 2
    val x6751 = withCtrl(x6848) { UnitController(style=SeqPipe, level=InnerControl).name("x6751").srcCtx("LSTMPipeStep.scala:41:14") } // UnitPipe(List(b3865, b3856, b3841, b3781),Block(Const(())))
    val x6744 = withCtrl(x6751) { OpDef(op=BitAnd, inputs=List(b3865, b3856)).name("x6744").srcCtx("UnrollingBase.scala:28:66") } // And(b3865,b3856)
    val x6745 = withCtrl(x6751) { OpDef(op=BitAnd, inputs=List(b3841, b3781)).name("x6745").srcCtx("UnrollingBase.scala:28:66") } // And(b3841,b3781)
    val x6746 = withCtrl(x6751) { OpDef(op=BitAnd, inputs=List(x6744, x6745)).name("x6746").srcCtx("UnrollingBase.scala:28:66") } // And(x6744,x6745)
    val x6747 = withCtrl(x6751) { LoadBanks(List(x6726_d1_b0), List(b3864)).name("x6747").srcCtx("LSTMPipeStep.scala:30:34:xEle") } // SRAMLoad(x6726,ArrayBuffer(Const(128)),List(b3864),Const(0),x6746)
    val x6748 = withCtrl(x6751) { LoadBanks(List(x6489_d1_b0), List(b3855, b3864)).name("x6748").srcCtx("LSTMPipeStep.scala:31:27:hEle") } // SRAMLoad(x6489,ArrayBuffer(Const(2), Const(128)),List(b3855, b3864),Const(0),x6746)
    val x6749_x6742_d0 = withCtrl(x6751) { WriteMem(x6742_d0, x6747).name("x6749_x6742_d0").srcCtx("LSTMPipeStep.scala:41:14") } // RegWrite(x6742,x6747,x6746)
    val x6749_x6742_d1 = withCtrl(x6751) { WriteMem(x6742_d1, x6747).name("x6749_x6742_d1").srcCtx("LSTMPipeStep.scala:41:14") } // RegWrite(x6742,x6747,x6746)
    val x6749_x6742_d2 = withCtrl(x6751) { WriteMem(x6742_d2, x6747).name("x6749_x6742_d2").srcCtx("LSTMPipeStep.scala:41:14") } // RegWrite(x6742,x6747,x6746)
    val x6749_x6742_d3 = withCtrl(x6751) { WriteMem(x6742_d3, x6747).name("x6749_x6742_d3").srcCtx("LSTMPipeStep.scala:41:14") } // RegWrite(x6742,x6747,x6746)
    val x6750_x6743_d0 = withCtrl(x6751) { WriteMem(x6743_d0, x6748).name("x6750_x6743_d0").srcCtx("LSTMPipeStep.scala:41:14") } // RegWrite(x6743,x6748,x6746)
    val x6750_x6743_d1 = withCtrl(x6751) { WriteMem(x6743_d1, x6748).name("x6750_x6743_d1").srcCtx("LSTMPipeStep.scala:41:14") } // RegWrite(x6743,x6748,x6746)
    val x6750_x6743_d2 = withCtrl(x6751) { WriteMem(x6743_d2, x6748).name("x6750_x6743_d2").srcCtx("LSTMPipeStep.scala:41:14") } // RegWrite(x6743,x6748,x6746)
    val x6750_x6743_d3 = withCtrl(x6751) { WriteMem(x6743_d3, x6748).name("x6750_x6743_d3").srcCtx("LSTMPipeStep.scala:41:14") } // RegWrite(x6743,x6748,x6746)
    val x6752 = withCtrl(x6848) { Counter(min=Const(0), max=Const(4), step=Const(1), par=4).name("x6752").srcCtx("LSTMPipeStep.scala:32:36") } // CounterNew(Const(0),Const(4),Const(1),Const(4))
    val x6753 = withCtrl(x6848) { CounterChain(List(x6752)).name("x6753").srcCtx("LSTMPipeStep.scala:32:49") } // CounterChainNew(List(x6752))
    val x6827 = withCtrl(x6848) { LoopController(style=MetaPipe, level=OuterControl, cchain=x6753).name("x6827").srcCtx("LSTMPipeStep.scala:32:49") } // UnrolledForeach(List(b3865, b3856, b3841, b3781),x6753,Block(Const(())),List(List(b3879, b3880, b3881, b3882)),List(List(b3883, b3884, b3885, b3886)))
    val b3879 = withCtrl(x6827) { CounterIter(x6752, Some(0)).name("b3879") } // b3879
    val b3883 = withCtrl(x6827) { Const(true).name("b3883") } // b3883
    val b3880 = withCtrl(x6827) { CounterIter(x6752, Some(1)).name("b3880") } // b3880
    val b3884 = withCtrl(x6827) { Const(true).name("b3884") } // b3884
    val b3881 = withCtrl(x6827) { CounterIter(x6752, Some(2)).name("b3881") } // b3881
    val b3885 = withCtrl(x6827) { Const(true).name("b3885") } // b3885
    val b3882 = withCtrl(x6827) { CounterIter(x6752, Some(3)).name("b3882") } // b3882
    val b3886 = withCtrl(x6827) { Const(true).name("b3886") } // b3886
    val x6826 = withCtrl(x6827) { UnitController(style=ForkJoin, level=OuterControl).name("x6826").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b3865, b3856, b3841, b3781),Block(Const(())))
    val x6754 = withCtrl(x6826) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6754").srcCtx("LSTMPipeStep.scala:33:44") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6755 = withCtrl(x6826) { CounterChain(List(x6754)).name("x6755").srcCtx("LSTMPipeStep.scala:33:58") } // CounterChainNew(List(x6754))
    val x6771 = withCtrl(x6826) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6755).name("x6771").srcCtx("LSTMPipeStep.scala:33:58") } // UnrolledForeach(List(b3883, b3865, b3856, b3841, b3781),x6755,Block(Const(())),List(List(b3895)),List(List(b3896)))
    val b3895 = withCtrl(x6771) { CounterIter(x6754, None).name("b3895") } // b3895
    val b3896 = withCtrl(x6771) { Const(true).name("b3896") } // b3896
    val x6756 = withCtrl(x6771) { OpDef(op=BitAnd, inputs=List(b3896, b3883)).name("x6756").srcCtx("UnrollingBase.scala:28:66") } // And(b3896,b3883)
    val x6757 = withCtrl(x6771) { OpDef(op=BitAnd, inputs=List(b3865, b3856)).name("x6757").srcCtx("UnrollingBase.scala:28:66") } // And(b3865,b3856)
    val x6758 = withCtrl(x6771) { OpDef(op=BitAnd, inputs=List(b3841, b3781)).name("x6758").srcCtx("UnrollingBase.scala:28:66") } // And(b3841,b3781)
    val x6759 = withCtrl(x6771) { OpDef(op=BitAnd, inputs=List(x6756, x6757)).name("x6759").srcCtx("UnrollingBase.scala:28:66") } // And(x6756,x6757)
    val x6760 = withCtrl(x6771) { OpDef(op=BitAnd, inputs=List(x6759, x6758)).name("x6760").srcCtx("UnrollingBase.scala:28:66") } // And(x6759,x6758)
    val x6761 = withCtrl(x6771) { LoadBanks(List(x6524_d0_b0), List(b3855, b3864, b3879, b3895)).name("x6761").srcCtx("LSTMPipeStep.scala:34:31") } // ParSRAMLoad(x6524,List(List(b3855, b3864, b3879, b3895)),List(x6760))
    val x6762 = withCtrl(x6771) { x6761 } // VectorApply(x6761,0)
    val x6763 = withCtrl(x6771) { ReadMem(x6742_d0).name("x6763").srcCtx("LSTMPipeStep.scala:41:14") } // RegRead(x6742)
    val x6764 = withCtrl(x6771) { OpDef(op=FixMul, inputs=List(x6762, x6763)).name("x6764").srcCtx("LSTMPipeStep.scala:34:69:reX") } // FixMul(x6762,x6763)
    val x6765 = withCtrl(x6771) { LoadBanks(List(x6577_d0_b0), List(b3855, b3864, b3879, b3895)).name("x6765").srcCtx("LSTMPipeStep.scala:35:31") } // ParSRAMLoad(x6577,List(List(b3855, b3864, b3879, b3895)),List(x6760))
    val x6766 = withCtrl(x6771) { x6765 } // VectorApply(x6765,0)
    val x6767 = withCtrl(x6771) { ReadMem(x6743_d0).name("x6767").srcCtx("LSTMPipeStep.scala:41:14") } // RegRead(x6743)
    val x6768 = withCtrl(x6771) { OpDef(op=FixMul, inputs=List(x6766, x6767)).name("x6768").srcCtx("LSTMPipeStep.scala:35:69:reH") } // FixMul(x6766,x6767)
    val x6769 = withCtrl(x6771) { OpDef(op=FixAdd, inputs=List(x6764, x6768)).name("x6769").srcCtx("LSTMPipeStep.scala:36:48") } // FixAdd(x6764,x6768)
    val x6770 = withCtrl(x6771) { StoreBanks(List(List(x6741_d0_b0)), List(b3879, b3895), x6769).name("x6770").srcCtx("LSTMPipeStep.scala:36:42") } // ParSRAMStore(x6741,List(List(b3879, b3895)),List(x6769),List(x6760))
    val x6772 = withCtrl(x6826) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6772").srcCtx("LSTMPipeStep.scala:33:44") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6773 = withCtrl(x6826) { CounterChain(List(x6772)).name("x6773").srcCtx("LSTMPipeStep.scala:33:58") } // CounterChainNew(List(x6772))
    val x6789 = withCtrl(x6826) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6773).name("x6789").srcCtx("LSTMPipeStep.scala:33:58") } // UnrolledForeach(List(b3884, b3865, b3856, b3841, b3781),x6773,Block(Const(())),List(List(b3913)),List(List(b3914)))
    val b3913 = withCtrl(x6789) { CounterIter(x6772, None).name("b3913") } // b3913
    val b3914 = withCtrl(x6789) { Const(true).name("b3914") } // b3914
    val x6774 = withCtrl(x6789) { OpDef(op=BitAnd, inputs=List(b3914, b3884)).name("x6774").srcCtx("UnrollingBase.scala:28:66") } // And(b3914,b3884)
    val x6775 = withCtrl(x6789) { OpDef(op=BitAnd, inputs=List(b3865, b3856)).name("x6775").srcCtx("UnrollingBase.scala:28:66") } // And(b3865,b3856)
    val x6776 = withCtrl(x6789) { OpDef(op=BitAnd, inputs=List(b3841, b3781)).name("x6776").srcCtx("UnrollingBase.scala:28:66") } // And(b3841,b3781)
    val x6777 = withCtrl(x6789) { OpDef(op=BitAnd, inputs=List(x6774, x6775)).name("x6777").srcCtx("UnrollingBase.scala:28:66") } // And(x6774,x6775)
    val x6778 = withCtrl(x6789) { OpDef(op=BitAnd, inputs=List(x6777, x6776)).name("x6778").srcCtx("UnrollingBase.scala:28:66") } // And(x6777,x6776)
    val x6779 = withCtrl(x6789) { LoadBanks(List(x6524_d0_b1), List(b3855, b3864, b3880, b3913)).name("x6779").srcCtx("LSTMPipeStep.scala:34:31") } // ParSRAMLoad(x6524,List(List(b3855, b3864, b3880, b3913)),List(x6778))
    val x6780 = withCtrl(x6789) { x6779 } // VectorApply(x6779,0)
    val x6781 = withCtrl(x6789) { ReadMem(x6742_d1).name("x6781").srcCtx("LSTMPipeStep.scala:41:14") } // RegRead(x6742)
    val x6782 = withCtrl(x6789) { OpDef(op=FixMul, inputs=List(x6780, x6781)).name("x6782").srcCtx("LSTMPipeStep.scala:34:69:reX") } // FixMul(x6780,x6781)
    val x6783 = withCtrl(x6789) { LoadBanks(List(x6577_d0_b1), List(b3855, b3864, b3880, b3913)).name("x6783").srcCtx("LSTMPipeStep.scala:35:31") } // ParSRAMLoad(x6577,List(List(b3855, b3864, b3880, b3913)),List(x6778))
    val x6784 = withCtrl(x6789) { x6783 } // VectorApply(x6783,0)
    val x6785 = withCtrl(x6789) { ReadMem(x6743_d1).name("x6785").srcCtx("LSTMPipeStep.scala:41:14") } // RegRead(x6743)
    val x6786 = withCtrl(x6789) { OpDef(op=FixMul, inputs=List(x6784, x6785)).name("x6786").srcCtx("LSTMPipeStep.scala:35:69:reH") } // FixMul(x6784,x6785)
    val x6787 = withCtrl(x6789) { OpDef(op=FixAdd, inputs=List(x6782, x6786)).name("x6787").srcCtx("LSTMPipeStep.scala:36:48") } // FixAdd(x6782,x6786)
    val x6788 = withCtrl(x6789) { StoreBanks(List(List(x6741_d0_b1)), List(b3880, b3913), x6787).name("x6788").srcCtx("LSTMPipeStep.scala:36:42") } // ParSRAMStore(x6741,List(List(b3880, b3913)),List(x6787),List(x6778))
    val x6790 = withCtrl(x6826) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6790").srcCtx("LSTMPipeStep.scala:33:44") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6791 = withCtrl(x6826) { CounterChain(List(x6790)).name("x6791").srcCtx("LSTMPipeStep.scala:33:58") } // CounterChainNew(List(x6790))
    val x6807 = withCtrl(x6826) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6791).name("x6807").srcCtx("LSTMPipeStep.scala:33:58") } // UnrolledForeach(List(b3885, b3865, b3856, b3841, b3781),x6791,Block(Const(())),List(List(b3931)),List(List(b3932)))
    val b3931 = withCtrl(x6807) { CounterIter(x6790, None).name("b3931") } // b3931
    val b3932 = withCtrl(x6807) { Const(true).name("b3932") } // b3932
    val x6792 = withCtrl(x6807) { OpDef(op=BitAnd, inputs=List(b3932, b3885)).name("x6792").srcCtx("UnrollingBase.scala:28:66") } // And(b3932,b3885)
    val x6793 = withCtrl(x6807) { OpDef(op=BitAnd, inputs=List(b3865, b3856)).name("x6793").srcCtx("UnrollingBase.scala:28:66") } // And(b3865,b3856)
    val x6794 = withCtrl(x6807) { OpDef(op=BitAnd, inputs=List(b3841, b3781)).name("x6794").srcCtx("UnrollingBase.scala:28:66") } // And(b3841,b3781)
    val x6795 = withCtrl(x6807) { OpDef(op=BitAnd, inputs=List(x6792, x6793)).name("x6795").srcCtx("UnrollingBase.scala:28:66") } // And(x6792,x6793)
    def split1 = {
    val x6796 = withCtrl(x6807) { OpDef(op=BitAnd, inputs=List(x6795, x6794)).name("x6796").srcCtx("UnrollingBase.scala:28:66") } // And(x6795,x6794)
    val x6797 = withCtrl(x6807) { LoadBanks(List(x6524_d0_b2), List(b3855, b3864, b3881, b3931)).name("x6797").srcCtx("LSTMPipeStep.scala:34:31") } // ParSRAMLoad(x6524,List(List(b3855, b3864, b3881, b3931)),List(x6796))
    val x6798 = withCtrl(x6807) { x6797 } // VectorApply(x6797,0)
    val x6799 = withCtrl(x6807) { ReadMem(x6742_d2).name("x6799").srcCtx("LSTMPipeStep.scala:41:14") } // RegRead(x6742)
    val x6800 = withCtrl(x6807) { OpDef(op=FixMul, inputs=List(x6798, x6799)).name("x6800").srcCtx("LSTMPipeStep.scala:34:69:reX") } // FixMul(x6798,x6799)
    val x6801 = withCtrl(x6807) { LoadBanks(List(x6577_d0_b2), List(b3855, b3864, b3881, b3931)).name("x6801").srcCtx("LSTMPipeStep.scala:35:31") } // ParSRAMLoad(x6577,List(List(b3855, b3864, b3881, b3931)),List(x6796))
    val x6802 = withCtrl(x6807) { x6801 } // VectorApply(x6801,0)
    val x6803 = withCtrl(x6807) { ReadMem(x6743_d2).name("x6803").srcCtx("LSTMPipeStep.scala:41:14") } // RegRead(x6743)
    val x6804 = withCtrl(x6807) { OpDef(op=FixMul, inputs=List(x6802, x6803)).name("x6804").srcCtx("LSTMPipeStep.scala:35:69:reH") } // FixMul(x6802,x6803)
    val x6805 = withCtrl(x6807) { OpDef(op=FixAdd, inputs=List(x6800, x6804)).name("x6805").srcCtx("LSTMPipeStep.scala:36:48") } // FixAdd(x6800,x6804)
    val x6806 = withCtrl(x6807) { StoreBanks(List(List(x6741_d0_b2)), List(b3881, b3931), x6805).name("x6806").srcCtx("LSTMPipeStep.scala:36:42") } // ParSRAMStore(x6741,List(List(b3881, b3931)),List(x6805),List(x6796))
    val x6808 = withCtrl(x6826) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6808").srcCtx("LSTMPipeStep.scala:33:44") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6809 = withCtrl(x6826) { CounterChain(List(x6808)).name("x6809").srcCtx("LSTMPipeStep.scala:33:58") } // CounterChainNew(List(x6808))
    val x6825 = withCtrl(x6826) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6809).name("x6825").srcCtx("LSTMPipeStep.scala:33:58") } // UnrolledForeach(List(b3886, b3865, b3856, b3841, b3781),x6809,Block(Const(())),List(List(b3949)),List(List(b3950)))
    val b3949 = withCtrl(x6825) { CounterIter(x6808, None).name("b3949") } // b3949
    val b3950 = withCtrl(x6825) { Const(true).name("b3950") } // b3950
    val x6810 = withCtrl(x6825) { OpDef(op=BitAnd, inputs=List(b3950, b3886)).name("x6810").srcCtx("UnrollingBase.scala:28:66") } // And(b3950,b3886)
    val x6811 = withCtrl(x6825) { OpDef(op=BitAnd, inputs=List(b3865, b3856)).name("x6811").srcCtx("UnrollingBase.scala:28:66") } // And(b3865,b3856)
    val x6812 = withCtrl(x6825) { OpDef(op=BitAnd, inputs=List(b3841, b3781)).name("x6812").srcCtx("UnrollingBase.scala:28:66") } // And(b3841,b3781)
    val x6813 = withCtrl(x6825) { OpDef(op=BitAnd, inputs=List(x6810, x6811)).name("x6813").srcCtx("UnrollingBase.scala:28:66") } // And(x6810,x6811)
    val x6814 = withCtrl(x6825) { OpDef(op=BitAnd, inputs=List(x6813, x6812)).name("x6814").srcCtx("UnrollingBase.scala:28:66") } // And(x6813,x6812)
    val x6815 = withCtrl(x6825) { LoadBanks(List(x6524_d0_b3), List(b3855, b3864, b3882, b3949)).name("x6815").srcCtx("LSTMPipeStep.scala:34:31") } // ParSRAMLoad(x6524,List(List(b3855, b3864, b3882, b3949)),List(x6814))
    val x6816 = withCtrl(x6825) { x6815 } // VectorApply(x6815,0)
    val x6817 = withCtrl(x6825) { ReadMem(x6742_d3).name("x6817").srcCtx("LSTMPipeStep.scala:41:14") } // RegRead(x6742)
    val x6818 = withCtrl(x6825) { OpDef(op=FixMul, inputs=List(x6816, x6817)).name("x6818").srcCtx("LSTMPipeStep.scala:34:69:reX") } // FixMul(x6816,x6817)
    val x6819 = withCtrl(x6825) { LoadBanks(List(x6577_d0_b3), List(b3855, b3864, b3882, b3949)).name("x6819").srcCtx("LSTMPipeStep.scala:35:31") } // ParSRAMLoad(x6577,List(List(b3855, b3864, b3882, b3949)),List(x6814))
    val x6820 = withCtrl(x6825) { x6819 } // VectorApply(x6819,0)
    val x6821 = withCtrl(x6825) { ReadMem(x6743_d3).name("x6821").srcCtx("LSTMPipeStep.scala:41:14") } // RegRead(x6743)
    val x6822 = withCtrl(x6825) { OpDef(op=FixMul, inputs=List(x6820, x6821)).name("x6822").srcCtx("LSTMPipeStep.scala:35:69:reH") } // FixMul(x6820,x6821)
    val x6823 = withCtrl(x6825) { OpDef(op=FixAdd, inputs=List(x6818, x6822)).name("x6823").srcCtx("LSTMPipeStep.scala:36:48") } // FixAdd(x6818,x6822)
    val x6824 = withCtrl(x6825) { StoreBanks(List(List(x6741_d0_b3)), List(b3882, b3949), x6823).name("x6824").srcCtx("LSTMPipeStep.scala:36:42") } // ParSRAMStore(x6741,List(List(b3882, b3949)),List(x6823),List(x6814))
    val x6828 = withCtrl(x6848) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6828").srcCtx("LSTMPipeStep.scala:41:14") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6829 = withCtrl(x6848) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x6829").srcCtx("LSTMPipeStep.scala:41:14") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x6830 = withCtrl(x6848) { CounterChain(List(x6829,x6828)).name("x6830").srcCtx("LSTMPipeStep.scala:41:14") } // CounterChainNew(ArrayBuffer(x6829, x6828))
    val x6847 = withCtrl(x6848) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6830).name("x6847").srcCtx("LSTMPipeStep.scala:41:14") } // UnrolledForeach(List(),x6830,Block(Const(())),ArrayBuffer(List(b3969), List(b3970)),ArrayBuffer(List(b3971), List(b3972)))
    val b3969 = withCtrl(x6847) { CounterIter(x6829, Some(0)).name("b3969") } // b3969
    val b3971 = withCtrl(x6847) { Const(true).name("b3971") } // b3971
    val b3970 = withCtrl(x6847) { CounterIter(x6828, None).name("b3970") } // b3970
    val b3972 = withCtrl(x6847) { Const(true).name("b3972") } // b3972
    val x6831 = withCtrl(x6847) { OpDef(op=BitAnd, inputs=List(b3971, b3972)).name("x6831").srcCtx("UnrollingBase.scala:28:66") } // And(b3971,b3972)
    val x6832 = withCtrl(x6847) { OpDef(op=BitAnd, inputs=List(b3856, b3841)).name("x6832").srcCtx("UnrollingBase.scala:28:66") } // And(b3856,b3841)
    val x6833 = withCtrl(x6847) { OpDef(op=BitAnd, inputs=List(x6831, x6832)).name("x6833").srcCtx("UnrollingBase.scala:28:66") } // And(x6831,x6832)
    val x6834 = withCtrl(x6847) { OpDef(op=BitAnd, inputs=List(x6833, b3781)).name("x6834").srcCtx("UnrollingBase.scala:28:66") } // And(x6833,b3781)
    val x6835 = withCtrl(x6847) { LoadBanks(List(x6741_d0_b0, x6741_d0_b1, x6741_d0_b2, x6741_d0_b3), List(b3969, b3970)).name("x6835").srcCtx("LSTMPipeStep.scala:41:14") } // ParSRAMLoad(x6741,List(ArrayBuffer(b3969, b3970)),List(x6834))
    val x6836 = withCtrl(x6847) { x6835 } // VectorApply(x6835,0)
    val x6837 = withCtrl(x6847) { LoadBanks(List(x6737_d1_b0), List(b3969, b3970)).name("x6837").srcCtx("LSTMPipeStep.scala:41:14") } // ParSRAMLoad(x6737,List(ArrayBuffer(b3969, b3970)),List(x6834))
    val x6838 = withCtrl(x6847) { x6837 } // VectorApply(x6837,0)
    val x6839 = withCtrl(x6847) { OpDef(op=BitAnd, inputs=List(b3865, b3856)).name("x6839").srcCtx("LSTMPipeStep.scala:41:14") } // And(b3865,b3856)
    val x6840 = withCtrl(x6847) { OpDef(op=BitAnd, inputs=List(b3841, b3781)).name("x6840").srcCtx("LSTMPipeStep.scala:41:14") } // And(b3841,b3781)
    val x6841 = withCtrl(x6847) { OpDef(op=BitAnd, inputs=List(x6839, x6840)).name("x6841").srcCtx("LSTMPipeStep.scala:41:14") } // And(x6839,x6840)
    val x6842 = withCtrl(x6847) { OpDef(op=BitAnd, inputs=List(x6841, x6834)).name("x6842").srcCtx("LSTMPipeStep.scala:41:14") } // And(x6841,x6834)
    val x6843 = withCtrl(x6847) { OpDef(op=FixEql, inputs=List(b3864, Const(0))).name("x6843").srcCtx("LSTMPipeStep.scala:41:14") } // FixEql(b3864,Const(0))
    val x6844 = withCtrl(x6847) { OpDef(op=FixAdd, inputs=List(x6836, x6838)).name("x6844").srcCtx("LSTMPipeStep.scala:41:16") } // FixAdd(x6836,x6838)
    val x6845 = withCtrl(x6847) { OpDef(op=MuxOp, inputs=List(x6843, x6836, x6844)).name("x6845").srcCtx("LSTMPipeStep.scala:41:14") } // Mux(x6843,x6836,x6844)
    val x6846 = withCtrl(x6847) { StoreBanks(List(List(x6737_d0_b0), List(x6737_d1_b0)), List(b3969, b3970), x6845).name("x6846").srcCtx("LSTMPipeStep.scala:41:14") } // ParSRAMStore(x6737,List(ArrayBuffer(b3969, b3970)),List(x6845),List(x6834))
    antiDepsOf(x6846)=List(x6837)
    val x6849 = withCtrl(x6952) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x6849").srcCtx("LSTMPipeStep.scala:43:34") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x6850 = withCtrl(x6952) { CounterChain(List(x6849)).name("x6850").srcCtx("LSTMPipeStep.scala:43:50") } // CounterChainNew(List(x6849))
    val x6864 = withCtrl(x6952) { LoopController(style=MetaPipe, level=OuterControl, cchain=x6850).name("x6864").srcCtx("LSTMPipeStep.scala:43:50") } // UnrolledForeach(List(b3856, b3841, b3781),x6850,Block(Const(())),List(List(b3993)),List(List(b3994)))
    val b3993 = withCtrl(x6864) { CounterIter(x6849, Some(0)).name("b3993") } // b3993
    val b3994 = withCtrl(x6864) { Const(true).name("b3994") } // b3994
    val x6851 = withCtrl(x6864) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6851").srcCtx("LSTMPipeStep.scala:44:42") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6852 = withCtrl(x6864) { CounterChain(List(x6851)).name("x6852").srcCtx("LSTMPipeStep.scala:44:56") } // CounterChainNew(List(x6851))
    val x6863 = withCtrl(x6864) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6852).name("x6863").srcCtx("LSTMPipeStep.scala:44:56") } // UnrolledForeach(List(b3994, b3856, b3841, b3781),x6852,Block(Const(())),List(List(b3997)),List(List(b3998)))
    val b3997 = withCtrl(x6863) { CounterIter(x6851, None).name("b3997") } // b3997
    val b3998 = withCtrl(x6863) { Const(true).name("b3998") } // b3998
    val x6853 = withCtrl(x6863) { OpDef(op=BitAnd, inputs=List(b3998, b3994)).name("x6853").srcCtx("UnrollingBase.scala:28:66") } // And(b3998,b3994)
    val x6854 = withCtrl(x6863) { OpDef(op=BitAnd, inputs=List(b3856, b3841)).name("x6854").srcCtx("UnrollingBase.scala:28:66") } // And(b3856,b3841)
    val x6855 = withCtrl(x6863) { OpDef(op=BitAnd, inputs=List(x6853, x6854)).name("x6855").srcCtx("UnrollingBase.scala:28:66") } // And(x6853,x6854)
    val x6856 = withCtrl(x6863) { OpDef(op=BitAnd, inputs=List(x6855, b3781)).name("x6856").srcCtx("UnrollingBase.scala:28:66") } // And(x6855,b3781)
    val x6857 = withCtrl(x6863) { LoadBanks(List(x6737_d0_b0), List(b3993, b3997)).name("x6857").srcCtx("LSTMPipeStep.scala:45:40") } // ParSRAMLoad(x6737,List(List(b3993, b3997)),List(x6856))
    val x6858 = withCtrl(x6863) { x6857 } // VectorApply(x6857,0)
    val x6859 = withCtrl(x6863) { LoadBanks(List(x6630_d0_b0), List(b3855, b3993, b3997)).name("x6859").srcCtx("LSTMPipeStep.scala:45:64") } // ParSRAMLoad(x6630,List(List(b3855, b3993, b3997)),List(x6856))
    val x6860 = withCtrl(x6863) { x6859 } // VectorApply(x6859,0)
    val x6861 = withCtrl(x6863) { OpDef(op=FixAdd, inputs=List(x6858, x6860)).name("x6861").srcCtx("LSTMPipeStep.scala:45:61:foldVal") } // FixAdd(x6858,x6860)
    val x6862 = withCtrl(x6863) { StoreBanks(List(List(x6738_d0_b0)), List(b3993, b3997), x6861).name("x6862").srcCtx("LSTMPipeStep.scala:46:45") } // ParSRAMStore(x6738,List(List(b3993, b3997)),List(x6861),List(x6856))
    val x6865 = withCtrl(x6952) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x6865").srcCtx("LSTMPipeStep.scala:54:27") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x6866 = withCtrl(x6952) { CounterChain(List(x6865)).name("x6866").srcCtx("LSTMPipeStep.scala:54:33") } // CounterChainNew(List(x6865))
    val x6951 = withCtrl(x6952) { LoopController(style=MetaPipe, level=OuterControl, cchain=x6866).name("x6951").srcCtx("LSTMPipeStep.scala:54:33") } // UnrolledForeach(List(b3856, b3841, b3781),x6866,Block(Const(())),List(List(b4013)),List(List(b4014)))
    val b4013 = withCtrl(x6951) { CounterIter(x6865, Some(0)).name("b4013") } // b4013
    val b4014 = withCtrl(x6951) { Const(true).name("b4014") } // b4014
    val x6867 = withCtrl(x6951) { FIFO(size=16).name("x6867").srcCtx("LSTMPipeStep.scala:55:31:sigQ") } // x6867 = FIFONew(Const(16))
    isAccum(x6867) = false
    bufferDepthOf(x6867) = 2
    val x6868 = withCtrl(x6951) { FIFO(size=16).name("x6868").srcCtx("LSTMPipeStep.scala:56:31:actQ") } // x6868 = FIFONew(Const(16))
    isAccum(x6868) = false
    bufferDepthOf(x6868) = 2
    val x6869 = withCtrl(x6951) { FIFO(size=16).name("x6869").srcCtx("LSTMPipeStep.scala:57:35:hUpdateQ") } // x6869 = FIFONew(Const(16))
    isAccum(x6869) = false
    bufferDepthOf(x6869) = 1
    val x6870 = withCtrl(x6951) { FIFO(size=16).name("x6870").srcCtx("LSTMPipeStep.scala:58:35:cUpdateQ") } // x6870 = FIFONew(Const(16))
    isAccum(x6870) = false
    bufferDepthOf(x6870) = 1
    val x6871 = withCtrl(x6951) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6871").srcCtx("LSTMPipeStep.scala:60:40") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6872 = withCtrl(x6951) { CounterChain(List(x6871)).name("x6872").srcCtx("LSTMPipeStep.scala:60:54") } // CounterChainNew(List(x6871))
    val x6894 = withCtrl(x6951) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6872).name("x6894").srcCtx("LSTMPipeStep.scala:60:54") } // UnrolledForeach(List(b4014, b3856, b3841, b3781),x6872,Block(Const(())),List(List(b4021)),List(List(b4022)))
    val b4021 = withCtrl(x6894) { CounterIter(x6871, None).name("b4021") } // b4021
    val b4022 = withCtrl(x6894) { Const(true).name("b4022") } // b4022
    val x6873 = withCtrl(x6894) { OpDef(op=BitAnd, inputs=List(b4022, b4014)).name("x6873").srcCtx("UnrollingBase.scala:28:66") } // And(b4022,b4014)
    val x6874 = withCtrl(x6894) { OpDef(op=BitAnd, inputs=List(b3856, b3841)).name("x6874").srcCtx("UnrollingBase.scala:28:66") } // And(b3856,b3841)
    val x6875 = withCtrl(x6894) { OpDef(op=BitAnd, inputs=List(x6873, x6874)).name("x6875").srcCtx("UnrollingBase.scala:28:66") } // And(x6873,x6874)
    val x6876 = withCtrl(x6894) { OpDef(op=BitAnd, inputs=List(x6875, b3781)).name("x6876").srcCtx("UnrollingBase.scala:28:66") } // And(x6875,b3781)
    val x6877 = withCtrl(x6894) { LoadBanks(List(x6738_d0_b0), List(b4013, b4021)).name("x6877").srcCtx("LSTMPipeStep.scala:61:33:pEle") } // ParSRAMLoad(x6738,List(List(b4013, b4021)),List(x6876))
    val x6878 = withCtrl(x6894) { x6877 } // VectorApply(x6877,0)
    val x6879_d0_b0 = withCtrl(x6894) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x6879_d0_b0").srcCtx("NonLinearity.scala:35:37:lut") } // x6879 = LUTNew(List(1024), Seq(Const(-0.9999997615814208984375),Const(-0.9999997615814208984375),Const(-0.999999701976776123046875)... [1021 more]))
    isAccum(x6879_d0_b0) = false
    bufferDepthOf(x6879_d0_b0) = 1
    staticDimsOf(x6879_d0_b0) = List(1024)
    val x6880 = withCtrl(x6894) { OpDef(op=FixSub, inputs=List(x6878, Const(-8.0))).name("x6880").srcCtx("NonLinearity.scala:36:22") } // FixSub(x6878,Const(-8))
    val x6881 = withCtrl(x6894) { OpDef(op=FixSla, inputs=List(x6880, Const(6))).name("x6881").srcCtx("NonLinearity.scala:36:30") } // FixLsh(x6880,Const(6))
    // x6882 = FixConvert(x6881,TRUE,_32,_0) x.tp=FixPt[TRUE,_8,_24] {
    val x6882 = withCtrl(x6894) { OpDef(op=FixSra, inputs=List(x6881, Const("24"))).name("x6882").srcCtx("NonLinearity.scala:36:41:index") } // FixConvert(x6881,TRUE,_32,_0)
    // }
    val x6883 = withCtrl(x6894) { LoadBanks(List(x6879_d0_b0), List(x6882)).name("x6883").srcCtx("NonLinearity.scala:37:17:sigVal") } // LUTLoad(x6879,List(x6882),x6876)
    val x6884_d0_b0 = withCtrl(x6894) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x6884_d0_b0").srcCtx("NonLinearity.scala:35:37:lut") } // x6884 = LUTNew(List(1024), Seq(Const(-0.9999997615814208984375),Const(-0.9999997615814208984375),Const(-0.999999701976776123046875)... [1021 more]))
    isAccum(x6884_d0_b0) = false
    bufferDepthOf(x6884_d0_b0) = 1
    staticDimsOf(x6884_d0_b0) = List(1024)
    val x6885 = withCtrl(x6894) { LoadBanks(List(x6884_d0_b0), List(x6882)).name("x6885").srcCtx("NonLinearity.scala:37:17:actVal") } // LUTLoad(x6884,List(x6882),x6876)
    val x6886 = withCtrl(x6894) { OpDef(op=FixLt, inputs=List(Const(8.0), x6878)).name("x6886").srcCtx("LSTMPipeStep.scala:65:33:isHigh") } // FixLt(Const(8),x6878)
    val x6887 = withCtrl(x6894) { OpDef(op=FixLt, inputs=List(x6878, Const(-8.0))).name("x6887").srcCtx("LSTMPipeStep.scala:66:32:isLow") } // FixLt(x6878,Const(-8))
    val x6888 = withCtrl(x6894) { OpDef(op=MuxOp, inputs=List(x6887, Const(0.0), x6883)).name("x6888").srcCtx("LSTMPipeStep.scala:68:52") } // Mux(x6887,Const(0),x6883)
    val x6889 = withCtrl(x6894) { OpDef(op=MuxOp, inputs=List(x6886, Const(1.0), x6888)).name("x6889").srcCtx("LSTMPipeStep.scala:68:31:sigEle") } // Mux(x6886,Const(1),x6888)
    val x6890 = withCtrl(x6894) { OpDef(op=MuxOp, inputs=List(x6887, Const(-1.0), x6885)).name("x6890").srcCtx("LSTMPipeStep.scala:69:52") } // Mux(x6887,Const(-1),x6885)
    val x6891 = withCtrl(x6894) { OpDef(op=MuxOp, inputs=List(x6886, Const(1.0), x6890)).name("x6891").srcCtx("LSTMPipeStep.scala:69:31:actEle") } // Mux(x6886,Const(1),x6890)
    val x6892_x6867 = withCtrl(x6894) { WriteMem(x6867, x6889).name("x6892_x6867").srcCtx("LSTMPipeStep.scala:71:23") } // ParFIFOEnq(x6867,List(x6889),List(x6876))
    val x6893_x6868 = withCtrl(x6894) { WriteMem(x6868, x6891).name("x6893_x6868").srcCtx("LSTMPipeStep.scala:72:23") } // ParFIFOEnq(x6868,List(x6891),List(x6876))
    val x6950 = withCtrl(x6951) { UnitController(style=SeqPipe, level=OuterControl).name("x6950").srcCtx("LSTMPipeStep.scala:77:18") } // UnitPipe(List(b4014, b3856, b3841, b3781),Block(Const(())))
    val x6895 = withCtrl(x6950) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6895").srcCtx("LSTMPipeStep.scala:79:42") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6896 = withCtrl(x6950) { CounterChain(List(x6895)).name("x6896").srcCtx("LSTMPipeStep.scala:79:56") } // CounterChainNew(List(x6895))
    val x6935 = withCtrl(x6950) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6896).name("x6935").srcCtx("LSTMPipeStep.scala:79:56") } // UnrolledForeach(List(b4014, b3856, b3841, b3781),x6896,Block(Const(())),List(List(b4047)),List(List(b4048)))
    val b4047 = withCtrl(x6935) { CounterIter(x6895, None).name("b4047") } // b4047
    val b4048 = withCtrl(x6935) { Const(true).name("b4048") } // b4048
    val x6897 = withCtrl(x6935) { OpDef(op=BitAnd, inputs=List(b4048, b4014)).name("x6897").srcCtx("UnrollingBase.scala:28:66") } // And(b4048,b4014)
    val x6898 = withCtrl(x6935) { OpDef(op=BitAnd, inputs=List(b3856, b3841)).name("x6898").srcCtx("UnrollingBase.scala:28:66") } // And(b3856,b3841)
    val x6899 = withCtrl(x6935) { OpDef(op=BitAnd, inputs=List(x6897, x6898)).name("x6899").srcCtx("UnrollingBase.scala:28:66") } // And(x6897,x6898)
    val x6900 = withCtrl(x6935) { OpDef(op=BitAnd, inputs=List(x6899, b3781)).name("x6900").srcCtx("UnrollingBase.scala:28:66") } // And(x6899,b3781)
    val x6901 = withCtrl(x6935) { ReadMem(x6867).name("x6901").srcCtx("LSTMPipeStep.scala:80:38:sigEle") } // ParFIFODeq(x6867,List(x6900))
    val x6902 = withCtrl(x6935) { x6901 } // VectorApply(x6901,0)
    val x6903 = withCtrl(x6935) { ReadMem(x6868).name("x6903").srcCtx("LSTMPipeStep.scala:81:38:actEle") } // ParFIFODeq(x6868,List(x6900))
    val x6904 = withCtrl(x6935) { x6903 } // VectorApply(x6903,0)
    val x6905 = withCtrl(x6935) { LoadBanks(List(x6454_d0_b0), List(b3855, b4047)).name("x6905").srcCtx("LSTMPipeStep.scala:83:30:cLast") } // ParSRAMLoad(x6454,List(List(b3855, b4047)),List(x6900))
    val x6906 = withCtrl(x6935) { x6905 } // VectorApply(x6905,0)
    val x6907 = withCtrl(x6935) { OpDef(op=FixMul, inputs=List(x6906, x6904)).name("x6907").srcCtx("LSTMPipeStep.scala:84:38:cNewMult") } // FixMul(x6906,x6904)
    val x6908 = withCtrl(x6935) { OpDef(op=FixMul, inputs=List(x6902, x6906)).name("x6908").srcCtx("LSTMPipeStep.scala:85:42") } // FixMul(x6902,x6906)
    val x6909 = withCtrl(x6935) { OpDef(op=FixAdd, inputs=List(x6908, x6906)).name("x6909").srcCtx("LSTMPipeStep.scala:85:50:cNewMultAdd") } // FixAdd(x6908,x6906)
    val x6910 = withCtrl(x6935) { OpDef(op=FixEql, inputs=List(b4013, Const(0))).name("x6910").srcCtx("package.scala:28:42") } // FixEql(b4013,Const(0))
    val x6911 = withCtrl(x6935) { OpDef(op=FixEql, inputs=List(b4013, Const(1))).name("x6911").srcCtx("package.scala:31:42") } // FixEql(b4013,Const(1))
    val x6912 = withCtrl(x6935) { OpDef(op=FixEql, inputs=List(b4013, Const(2))).name("x6912").srcCtx("package.scala:34:42") } // FixEql(b4013,Const(2))
    val x6913 = withCtrl(x6935) { OpDef(op=MuxOp, inputs=List(x6912, x6909, x6906)).name("x6913").srcCtx("LSTMPipeStep.scala:89:24") } // Mux(x6912,x6909,x6906)
    val x6914 = withCtrl(x6935) { OpDef(op=MuxOp, inputs=List(x6911, x6907, x6913)).name("x6914").srcCtx("LSTMPipeStep.scala:88:22") } // Mux(x6911,x6907,x6913)
    val x6915 = withCtrl(x6935) { OpDef(op=MuxOp, inputs=List(x6910, x6902, x6914)).name("x6915").srcCtx("LSTMPipeStep.scala:87:34:cUpdate") } // Mux(x6910,x6902,x6914)
    val x6916 = withCtrl(x6935) { OpDef(op=FixAbs, inputs=List(x6909)).name("x6916").srcCtx("NonLinearity.scala:108:20:absin") } // FixAbs(x6909)
    val x6917 = withCtrl(x6935) { OpDef(op=FixSra, inputs=List(x6916, Const(2))).name("x6917").srcCtx("NonLinearity.scala:109:22:div4") } // FixRsh(x6916,Const(2))
    val x6918 = withCtrl(x6935) { OpDef(op=FixAdd, inputs=List(x6917, Const(0.375))).name("x6918").srcCtx("NonLinearity.scala:110:19:li") } // FixAdd(x6917,Const(0.375))
    val x6919 = withCtrl(x6935) { OpDef(op=FixLt, inputs=List(Const(2.5), x6916)).name("x6919").srcCtx("NonLinearity.scala:111:28") } // FixLt(Const(2.5),x6916)
    val x6920 = withCtrl(x6935) { OpDef(op=FixLt, inputs=List(Const(0.5), x6916)).name("x6920").srcCtx("NonLinearity.scala:112:14") } // FixLt(Const(0.5),x6916)
    val x6921 = withCtrl(x6935) { OpDef(op=FixLt, inputs=List(x6916, Const(2.5))).name("x6921").srcCtx("NonLinearity.scala:112:31") } // FixLt(x6916,Const(2.5))
    val x6922 = withCtrl(x6935) { OpDef(op=BitAnd, inputs=List(x6920, x6921)).name("x6922").srcCtx("NonLinearity.scala:112:22") } // And(x6920,x6921)
    val x6923 = withCtrl(x6935) { OpDef(op=MuxOp, inputs=List(x6922, x6918, x6916)).name("x6923").srcCtx("NonLinearity.scala:112:10") } // Mux(x6922,x6918,x6916)
    val x6924 = withCtrl(x6935) { OpDef(op=MuxOp, inputs=List(x6919, Const(1.0), x6923)).name("x6924").srcCtx("NonLinearity.scala:111:21:absout") } // Mux(x6919,Const(1),x6923)
    val x6925 = withCtrl(x6935) { OpDef(op=FixNeg, inputs=List(x6924)).name("x6925").srcCtx("NonLinearity.scala:115:23:negout") } // FixNeg(x6924)
    val x6926 = withCtrl(x6935) { OpDef(op=FixLt, inputs=List(x6909, Const(0.0))).name("x6926").srcCtx("NonLinearity.scala:116:12") } // FixLt(x6909,Const(0))
    val x6927 = withCtrl(x6935) { OpDef(op=MuxOp, inputs=List(x6926, x6925, x6924)).name("x6927").srcCtx("NonLinearity.scala:116:8") } // Mux(x6926,x6925,x6924)
    val x6928 = withCtrl(x6935) { OpDef(op=FixAdd, inputs=List(x6927, x6902)).name("x6928").srcCtx("LSTMPipeStep.scala:92:52:hNew") } // FixAdd(x6927,x6902)
    val x6929 = withCtrl(x6935) { LoadBanks(List(x6489_d0_b0), List(b3855, b4047)).name("x6929").srcCtx("LSTMPipeStep.scala:93:30:hLast") } // ParSRAMLoad(x6489,List(List(b3855, b4047)),List(x6900))
    val x6930 = withCtrl(x6935) { x6929 } // VectorApply(x6929,0)
    val x6931 = withCtrl(x6935) { OpDef(op=FixEql, inputs=List(b4013, Const(3))).name("x6931").srcCtx("package.scala:37:42") } // FixEql(b4013,Const(3))
    val x6932 = withCtrl(x6935) { OpDef(op=MuxOp, inputs=List(x6931, x6928, x6930)).name("x6932").srcCtx("LSTMPipeStep.scala:94:34:hUpdate") } // Mux(x6931,x6928,x6930)
    val x6933_x6870 = withCtrl(x6935) { WriteMem(x6870, x6915).name("x6933_x6870").srcCtx("LSTMPipeStep.scala:96:29") } // ParFIFOEnq(x6870,List(x6915),List(x6900))
    val x6934_x6869 = withCtrl(x6935) { WriteMem(x6869, x6932).name("x6934_x6869").srcCtx("LSTMPipeStep.scala:97:29") } // ParFIFOEnq(x6869,List(x6932),List(x6900))
    val x6936 = withCtrl(x6950) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6936").srcCtx("LSTMPipeStep.scala:100:42") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6937 = withCtrl(x6950) { CounterChain(List(x6936)).name("x6937").srcCtx("LSTMPipeStep.scala:100:56") } // CounterChainNew(List(x6936))
    val x6949 = withCtrl(x6950) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6937).name("x6949").srcCtx("LSTMPipeStep.scala:100:56") } // UnrolledForeach(List(b4014, b3856, b3841, b3781),x6937,Block(Const(())),List(List(b4090)),List(List(b4091)))
    val b4090 = withCtrl(x6949) { CounterIter(x6936, None).name("b4090") } // b4090
    val b4091 = withCtrl(x6949) { Const(true).name("b4091") } // b4091
    val x6938 = withCtrl(x6949) { OpDef(op=BitAnd, inputs=List(b4091, b4014)).name("x6938").srcCtx("UnrollingBase.scala:28:66") } // And(b4091,b4014)
    val x6939 = withCtrl(x6949) { OpDef(op=BitAnd, inputs=List(b3856, b3841)).name("x6939").srcCtx("UnrollingBase.scala:28:66") } // And(b3856,b3841)
    val x6940 = withCtrl(x6949) { OpDef(op=BitAnd, inputs=List(x6938, x6939)).name("x6940").srcCtx("UnrollingBase.scala:28:66") } // And(x6938,x6939)
    val x6941 = withCtrl(x6949) { OpDef(op=BitAnd, inputs=List(x6940, b3781)).name("x6941").srcCtx("UnrollingBase.scala:28:66") } // And(x6940,b3781)
    val x6942 = withCtrl(x6949) { ReadMem(x6869).name("x6942").srcCtx("LSTMPipeStep.scala:101:37:hNew") } // ParFIFODeq(x6869,List(x6941))
    val x6943 = withCtrl(x6949) { x6942 } // VectorApply(x6942,0)
    val x6944 = withCtrl(x6949) { ReadMem(x6870).name("x6944").srcCtx("LSTMPipeStep.scala:102:37:cNew") } // ParFIFODeq(x6870,List(x6941))
    val x6945 = withCtrl(x6949) { x6944 } // VectorApply(x6944,0)
    val x6946 = withCtrl(x6949) { StoreBanks(List(List(x6489_d0_b0), List(x6489_d1_b0)), List(b3855, b4090), x6943).name("x6946").srcCtx("LSTMPipeStep.scala:103:40") } // ParSRAMStore(x6489,List(List(b3855, b4090)),List(x6943),List(x6941))
    val x6947 = withCtrl(x6949) { StoreBanks(List(List(x6726_d0_b0), List(x6726_d1_b0)), List(b4090), x6943).name("x6947").srcCtx("LSTMPipeStep.scala:104:39") } // ParSRAMStore(x6726,List(List(b4090)),List(x6943),List(x6941))
    val x6948 = withCtrl(x6949) { StoreBanks(List(List(x6454_d0_b0)), List(b3855, b4090), x6945).name("x6948").srcCtx("LSTMPipeStep.scala:105:40") } // ParSRAMStore(x6454,List(List(b3855, b4090)),List(x6945),List(x6941))
    val x6956 = withCtrl(x7000) { UnitController(style=SeqPipe, level=InnerControl).name("x6956").srcCtx("LSTMPipeStep.scala:16:10") } // UnitPipe(List(b3841, b3781),Block(Const(())))
    val x6954 = withCtrl(x6956) { OpDef(op=FixAdd, inputs=List(b3780, Const(1))).name("x6954").srcCtx("LSTMPipeStep.scala:112:11") } // FixAdd(b3780,Const(1))
    val x6955 = withCtrl(x6956) { OpDef(op=FixAdd, inputs=List(b3840, Const(1))).name("x6955").srcCtx("LSTMPipeStep.scala:112:11") } // FixAdd(b3840,Const(1))
    val x6957 = withCtrl(x7000) { Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x6957").srcCtx("LSTMPipeStep.scala:112:54") } // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x6958 = withCtrl(x7000) { Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x6958").srcCtx("LSTMPipeStep.scala:112:54") } // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x6959 = withCtrl(x7000) { CounterChain(List(x6957,x6958)).name("x6959").srcCtx("LSTMPipeStep.scala:112:54") } // CounterChainNew(List(x6957, x6958))
    val x6999 = withCtrl(x7000) { LoopController(style=StreamPipe, level=OuterControl, cchain=x6959).name("x6999").srcCtx("LSTMPipeStep.scala:112:54") } // UnrolledForeach(List(b3841, b3781),x6959,Block(Const(())),List(List(b4114), List(b4115)),List(List(b4116), List(b4117)))
    val b4114 = withCtrl(x6999) { CounterIter(x6957, Some(0)).name("b4114") } // b4114
    val b4116 = withCtrl(x6999) { Const(true).name("b4116") } // b4116
    val b4115 = withCtrl(x6999) { CounterIter(x6958, Some(0)).name("b4115") } // b4115
    val b4117 = withCtrl(x6999) { Const(true).name("b4117") } // b4117
    val b7042 = withCtrl(x6999) { StreamOut(field="offset").name("b7042").srcCtx("LSTMPipeStep.scala:112:54") } // x6960 = StreamOutNew(BurstCmdBus)
    isAccum(b7042) = false
    bufferDepthOf(b7042) = 1
    val b7043 = withCtrl(x6999) { StreamOut(field="size").name("b7043").srcCtx("LSTMPipeStep.scala:112:54") } // x6960 = StreamOutNew(BurstCmdBus)
    isAccum(b7043) = false
    bufferDepthOf(b7043) = 1
    val x6961 = withCtrl(x6999) { StreamOut(field="data").name("x6961").srcCtx("LSTMPipeStep.scala:112:54") } // x6961 = StreamOutNew(BurstFullDataBus())
    isAccum(x6961) = false
    bufferDepthOf(x6961) = 1
    val x6962 = withCtrl(x6999) { StreamIn(field="ack").name("x6962").srcCtx("LSTMPipeStep.scala:112:54") } // x6962 = StreamInNew(BurstAckBus)
    isAccum(x6962) = false
    bufferDepthOf(x6962) = 1
    val x6981 = withCtrl(x6999) { UnitController(style=SeqPipe, level=InnerControl).name("x6981").srcCtx("LSTMPipeStep.scala:112:54") } // UnitPipe(List(b4116, b4117, b3841, b3781),Block(x6980))
    val x6963 = withCtrl(x6981) { OpDef(op=FixAdd, inputs=List(b3780, b4114)).name("x6963").srcCtx("LSTMPipeStep.scala:112:54") } // FixAdd(b3780,b4114)
    val x6964 = withCtrl(x6981) { OpDef(op=FixAdd, inputs=List(b3840, b4115)).name("x6964").srcCtx("LSTMPipeStep.scala:112:54") } // FixAdd(b3840,b4115)
    val x6965 = withCtrl(x6981) { x6963 } // FixConvert(x6963,TRUE,_32,_0) (Same Type. No op)
    val x6966 = withCtrl(x6981) { OpDef(op=FixSla, inputs=List(x6965, Const(10))).name("x6966").srcCtx("LSTMPipeStep.scala:112:54") } // FixLsh(x6965,Const(10))
    val x6967 = withCtrl(x6981) { x6964 } // FixConvert(x6964,TRUE,_32,_0) (Same Type. No op)
    val x6968 = withCtrl(x6981) { OpDef(op=FixSla, inputs=List(x6967, Const(7))).name("x6968").srcCtx("LSTMPipeStep.scala:112:54") } // FixLsh(x6967,Const(7))
    val x6969 = withCtrl(x6981) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x6970 = withCtrl(x6981) { OpDef(op=FixAdd, inputs=List(x6966, x6968)).name("x6970").srcCtx("LSTMPipeStep.scala:112:54") } // FixAdd(x6966,x6968)
    val x6971 = withCtrl(x6981) { OpDef(op=FixAdd, inputs=List(x6970, x6969)).name("x6971").srcCtx("LSTMPipeStep.scala:112:54") } // FixAdd(x6970,x6969)
    val x6972 = withCtrl(x6981) { OpDef(op=FixSla, inputs=List(x6971, Const(2))).name("x6972").srcCtx("LSTMPipeStep.scala:112:54") } // FixLsh(x6971,Const(2))
    val x6973 = withCtrl(x6981) { x6972 } // FixConvert(x6972,TRUE,_64,_0)
    val x6974 = withCtrl(x6981) { DramAddress(x6451).name("x6974").srcCtx("LSTMPipeStep.scala:112:54") } // GetDRAMAddress(x6451)
    val x6976_x6975 = withCtrl(x6981) { OpDef(op=FixAdd, inputs=List(x6973, x6974)).name("x6976_x6975").srcCtx("LSTMPipeStep.scala:112:54") } // FixAdd(x6973,x6974)
    // x6976 = SimpleStruct(ArrayBuffer((offset,x6975), (size,Const(512)), (isLoad,Const(false))))
    val x6977 = withCtrl(x6981) { OpDef(op=BitAnd, inputs=List(b4116, b4117)).name("x6977").srcCtx("UnrollingBase.scala:28:66") } // And(b4116,b4117)
    val x6978 = withCtrl(x6981) { OpDef(op=BitAnd, inputs=List(b3841, b3781)).name("x6978").srcCtx("UnrollingBase.scala:28:66") } // And(b3841,b3781)
    val x6979 = withCtrl(x6981) { OpDef(op=BitAnd, inputs=List(x6977, x6978)).name("x6979").srcCtx("UnrollingBase.scala:28:66") } // And(x6977,x6978)
    val x6980_b7044_b7042 = withCtrl(x6981) { WriteMem(b7042, x6976_x6975).name("x6980_b7044_b7042").srcCtx("LSTMPipeStep.scala:112:54") } // StreamWrite(x6960,x6976,x6979)
    val x6980_b7045_b7043 = withCtrl(x6981) { WriteMem(b7043, Const(512)).name("x6980_b7045_b7043").srcCtx("LSTMPipeStep.scala:112:54") } // StreamWrite(x6960,x6976,x6979)
    val x6982 = withCtrl(x6999) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6982").srcCtx("LSTMPipeStep.scala:112:54") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6983 = withCtrl(x6999) { CounterChain(List(x6982)).name("x6983").srcCtx("LSTMPipeStep.scala:112:54") } // CounterChainNew(List(x6982))
    val x6992 = withCtrl(x6999) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6983).name("x6992").srcCtx("LSTMPipeStep.scala:112:54") } // UnrolledForeach(List(b4116, b4117, b3841, b3781),x6983,Block(Const(())),List(List(b4142)),List(List(b4143)))
    val b4142 = withCtrl(x6992) { CounterIter(x6982, None).name("b4142") } // b4142
    val b4143 = withCtrl(x6992) { Const(true).name("b4143") } // b4143
    val x6984 = withCtrl(x6992) { OpDef(op=BitAnd, inputs=List(b4143, b4116)).name("x6984").srcCtx("UnrollingBase.scala:28:66") } // And(b4143,b4116)
    val x6985 = withCtrl(x6992) { OpDef(op=BitAnd, inputs=List(b4117, b3841)).name("x6985").srcCtx("UnrollingBase.scala:28:66") } // And(b4117,b3841)
    val x6986 = withCtrl(x6992) { OpDef(op=BitAnd, inputs=List(x6984, x6985)).name("x6986").srcCtx("UnrollingBase.scala:28:66") } // And(x6984,x6985)
    val x6987 = withCtrl(x6992) { OpDef(op=BitAnd, inputs=List(x6986, b3781)).name("x6987").srcCtx("UnrollingBase.scala:28:66") } // And(x6986,b3781)
    val x6988 = withCtrl(x6992) { LoadBanks(List(x6726_d0_b0), List(b4142)).name("x6988").srcCtx("LSTMPipeStep.scala:112:54") } // ParSRAMLoad(x6726,List(List(b4142)),List(x6987))
    val x6990_x6989 = withCtrl(x6992) { x6988 } // VectorApply(x6988,0)
    // x6990 = SimpleStruct(ArrayBuffer((_1,x6989), (_2,Const(true))))
    val x6991_x6991_x6961 = withCtrl(x6992) { WriteMem(x6961, x6990_x6989).name("x6991_x6991_x6961").srcCtx("LSTMPipeStep.scala:112:54") } // ParStreamWrite(x6961,List(x6990),List(x6987))
    val x6993 = withCtrl(x6999) { FringeDenseStore(dram=List(x6451), cmdStream=List(b7042, b7043), dataStream=List(x6961), ackStream=List(x6962)).name("x6993").srcCtx("LSTMPipeStep.scala:112:54") } // FringeDenseStore(x6451,x6960,x6961,x6962)
    val x6998 = withCtrl(x6999) { UnitController(style=SeqPipe, level=InnerControl).name("x6998").srcCtx("LSTMPipeStep.scala:112:54") } // UnitPipe(List(b4116, b4117, b3841, b3781),Block(Const(())))
    val x6994 = withCtrl(x6998) { OpDef(op=BitAnd, inputs=List(b4116, b4117)).name("x6994").srcCtx("UnrollingBase.scala:28:66") } // And(b4116,b4117)
    val x6995 = withCtrl(x6998) { OpDef(op=BitAnd, inputs=List(b3841, b3781)).name("x6995").srcCtx("UnrollingBase.scala:28:66") } // And(b3841,b3781)
    val x6996 = withCtrl(x6998) { OpDef(op=BitAnd, inputs=List(x6994, x6995)).name("x6996").srcCtx("UnrollingBase.scala:28:66") } // And(x6994,x6995)
    val x6997_x6997 = withCtrl(x6998) { ReadMem(x6962).name("x6997_x6997").srcCtx("LSTMPipeStep.scala:112:54") } // StreamRead(x6962,x6996)
    }; split1
    
  }
}
