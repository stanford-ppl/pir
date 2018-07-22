import pir._
import pir.node._
import arch._
import prism.enums._

object GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1 extends PIRApp {
  def main(implicit design:PIRDesign) = {
    import design.pirmeta._
    val x4622 = withCtrl(design.top.topController) { ArgIn(init=0).name("x4622").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:23:18:R") } // ArgInNew(Const(0))
    isAccum(x4622) = false
    bufferDepthOf(x4622) = 1
    boundOf(x4622) = 4096
    val x4624 = withCtrl(design.top.topController) { ReadMem(x4622).name("x4624").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:26:21") } // RegRead(x4622)
    val x4625 = withCtrl(design.top.topController) { DRAM(dims=List(x4624, Const(128))).name("x4625").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:26:20:x") } // x4625 = DRAMNew(ArrayBuffer(x4624, Const(128)),Const(0))
    val x4626 = withCtrl(design.top.topController) { ReadMem(x4622).name("x4626").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:27:23") } // RegRead(x4622)
    val x4627 = withCtrl(design.top.topController) { DRAM(dims=List(x4626)).name("x4627").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:27:22:y") } // x4627 = DRAMNew(ArrayBuffer(x4626),Const(0))
    val x4628 = withCtrl(design.top.topController) { DRAM(dims=List(Const(128))).name("x4628").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:28:22:mu0") } // x4628 = DRAMNew(ArrayBuffer(Const(128)),Const(0))
    val x4629 = withCtrl(design.top.topController) { DRAM(dims=List(Const(128))).name("x4629").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:29:22:mu1") } // x4629 = DRAMNew(ArrayBuffer(Const(128)),Const(0))
    val x4630 = withCtrl(design.top.topController) { DRAM(dims=List(Const(128), Const(128))).name("x4630").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:30:24:sigma") } // x4630 = DRAMNew(ArrayBuffer(Const(128), Const(128)),Const(0))
    val x5099 = withCtrl(design.top.topController) { UnitController(style=SeqPipe, level=OuterControl).name("x5099").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:37:11") } // Hwblock(Block(Const(())),false)
    val x4635_d0_b0 = withCtrl(x5099) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x4635_d0_b0").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:38:28:mu0Tile") } // x4635 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x4635_d0_b0) = false
    bufferDepthOf(x4635_d0_b0) = 1
    staticDimsOf(x4635_d0_b0) = List(128)
    val x4635_d1_b0 = withCtrl(x5099) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x4635_d1_b0").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:38:28:mu0Tile") } // x4635 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x4635_d1_b0) = false
    bufferDepthOf(x4635_d1_b0) = 1
    staticDimsOf(x4635_d1_b0) = List(128)
    val x4635_d2_b0 = withCtrl(x5099) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x4635_d2_b0").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:38:28:mu0Tile") } // x4635 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x4635_d2_b0) = false
    bufferDepthOf(x4635_d2_b0) = 1
    staticDimsOf(x4635_d2_b0) = List(128)
    val x4635_d3_b0 = withCtrl(x5099) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x4635_d3_b0").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:38:28:mu0Tile") } // x4635 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x4635_d3_b0) = false
    bufferDepthOf(x4635_d3_b0) = 1
    staticDimsOf(x4635_d3_b0) = List(128)
    val x4635_d4_b0 = withCtrl(x5099) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x4635_d4_b0").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:38:28:mu0Tile") } // x4635 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x4635_d4_b0) = false
    bufferDepthOf(x4635_d4_b0) = 1
    staticDimsOf(x4635_d4_b0) = List(128)
    val x4635_d5_b0 = withCtrl(x5099) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x4635_d5_b0").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:38:28:mu0Tile") } // x4635 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x4635_d5_b0) = false
    bufferDepthOf(x4635_d5_b0) = 1
    staticDimsOf(x4635_d5_b0) = List(128)
    val x4635_d6_b0 = withCtrl(x5099) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x4635_d6_b0").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:38:28:mu0Tile") } // x4635 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x4635_d6_b0) = false
    bufferDepthOf(x4635_d6_b0) = 1
    staticDimsOf(x4635_d6_b0) = List(128)
    val x4635_d7_b0 = withCtrl(x5099) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x4635_d7_b0").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:38:28:mu0Tile") } // x4635 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x4635_d7_b0) = false
    bufferDepthOf(x4635_d7_b0) = 1
    staticDimsOf(x4635_d7_b0) = List(128)
    val x4636_d0_b0 = withCtrl(x5099) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x4636_d0_b0").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:39:28:mu1Tile") } // x4636 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x4636_d0_b0) = false
    bufferDepthOf(x4636_d0_b0) = 1
    staticDimsOf(x4636_d0_b0) = List(128)
    val x4636_d1_b0 = withCtrl(x5099) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x4636_d1_b0").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:39:28:mu1Tile") } // x4636 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x4636_d1_b0) = false
    bufferDepthOf(x4636_d1_b0) = 1
    staticDimsOf(x4636_d1_b0) = List(128)
    val x4636_d2_b0 = withCtrl(x5099) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x4636_d2_b0").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:39:28:mu1Tile") } // x4636 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x4636_d2_b0) = false
    bufferDepthOf(x4636_d2_b0) = 1
    staticDimsOf(x4636_d2_b0) = List(128)
    val x4636_d3_b0 = withCtrl(x5099) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x4636_d3_b0").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:39:28:mu1Tile") } // x4636 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x4636_d3_b0) = false
    bufferDepthOf(x4636_d3_b0) = 1
    staticDimsOf(x4636_d3_b0) = List(128)
    val x4636_d4_b0 = withCtrl(x5099) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x4636_d4_b0").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:39:28:mu1Tile") } // x4636 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x4636_d4_b0) = false
    bufferDepthOf(x4636_d4_b0) = 1
    staticDimsOf(x4636_d4_b0) = List(128)
    val x4636_d5_b0 = withCtrl(x5099) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x4636_d5_b0").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:39:28:mu1Tile") } // x4636 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x4636_d5_b0) = false
    bufferDepthOf(x4636_d5_b0) = 1
    staticDimsOf(x4636_d5_b0) = List(128)
    val x4636_d6_b0 = withCtrl(x5099) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x4636_d6_b0").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:39:28:mu1Tile") } // x4636 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x4636_d6_b0) = false
    bufferDepthOf(x4636_d6_b0) = 1
    staticDimsOf(x4636_d6_b0) = List(128)
    val x4636_d7_b0 = withCtrl(x5099) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x4636_d7_b0").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:39:28:mu1Tile") } // x4636 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x4636_d7_b0) = false
    bufferDepthOf(x4636_d7_b0) = 1
    staticDimsOf(x4636_d7_b0) = List(128)
    val x4673 = withCtrl(x5099) { UnitController(style=ForkJoin, level=OuterControl).name("x4673").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:40:16") } // ParallelPipe(List(Const(true)),Block(Const(())))
    val x4654 = withCtrl(x4673) { UnitController(style=StreamPipe, level=OuterControl).name("x4654").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:41:17") } // UnitPipe(List(Const(true)),Block(Const(())))
    val b5151 = withCtrl(x4654) { StreamOut(field="offset").name("b5151").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:41:17") } // x4637 = StreamOutNew(BurstCmdBus)
    isAccum(b5151) = false
    bufferDepthOf(b5151) = 1
    val b5152 = withCtrl(x4654) { StreamOut(field="size").name("b5152").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:41:17") } // x4637 = StreamOutNew(BurstCmdBus)
    isAccum(b5152) = false
    bufferDepthOf(b5152) = 1
    val x4638 = withCtrl(x4654) { StreamIn(field="data").name("x4638").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:41:17") } // x4638 = StreamInNew(BurstDataBus())
    isAccum(x4638) = false
    bufferDepthOf(x4638) = 1
    val x4646 = withCtrl(x4654) { UnitController(style=SeqPipe, level=InnerControl).name("x4646").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:41:17") } // UnitPipe(List(Const(true)),Block(x4645))
    val x4639 = withCtrl(x4646) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x4640 = withCtrl(x4646) { OpDef(op=FixSla, inputs=List(x4639, Const(2))).name("x4640").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:41:17") } // FixLsh(x4639,Const(2))
    val x4641 = withCtrl(x4646) { x4640 } // FixConvert(x4640,TRUE,_64,_0)
    val x4642 = withCtrl(x4646) { DramAddress(x4628).name("x4642").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:41:17") } // GetDRAMAddress(x4628)
    val x4644_x4643 = withCtrl(x4646) { OpDef(op=FixAdd, inputs=List(x4641, x4642)).name("x4644_x4643").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:41:17") } // FixAdd(x4641,x4642)
    // x4644 = SimpleStruct(ArrayBuffer((offset,x4643), (size,Const(512)), (isLoad,Const(true))))
    val x4645_b5153_b5151 = withCtrl(x4646) { WriteMem(b5151, x4644_x4643).name("x4645_b5153_b5151").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:41:17") } // StreamWrite(x4637,x4644,Const(true))
    val x4645_b5154_b5152 = withCtrl(x4646) { WriteMem(b5152, Const(512)).name("x4645_b5154_b5152").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:41:17") } // StreamWrite(x4637,x4644,Const(true))
    val x4647 = withCtrl(x4654) { FringeDenseLoad(dram=List(x4628), cmdStream=List(b5151, b5152), dataStream=List(x4638)).name("x4647").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:41:17") } // FringeDenseLoad(x4628,x4637,x4638)
    val x4648 = withCtrl(x4654) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x4648").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:41:17") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x4649 = withCtrl(x4654) { CounterChain(List(x4648)).name("x4649").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:41:17") } // CounterChainNew(List(x4648))
    val x4653 = withCtrl(x4654) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4649).name("x4653").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:41:17") } // UnrolledForeach(List(Const(true)),x4649,Block(Const(())),List(List(b1894)),List(List(b1895)))
    val b1894 = withCtrl(x4653) { CounterIter(x4648, None).name("b1894") } // b1894
    val b1895 = withCtrl(x4653) { Const(true).name("b1895") } // b1895
    val x4650_x4650 = withCtrl(x4653) { ReadMem(x4638).name("x4650_x4650").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:41:17") } // ParStreamRead(x4638,List(b1895))
    val x4651_x4651 = withCtrl(x4653) { x4650_x4650 } // VectorApply(x4650,0)
    val x4652 = withCtrl(x4653) { StoreBanks(List(List(x4635_d0_b0), List(x4635_d5_b0), List(x4635_d1_b0), List(x4635_d6_b0), List(x4635_d2_b0), List(x4635_d7_b0), List(x4635_d3_b0), List(x4635_d4_b0)), List(b1894), x4651_x4651).name("x4652").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:41:17") } // ParSRAMStore(x4635,List(List(b1894)),List(x4651),List(b1895))
    val x4672 = withCtrl(x4673) { UnitController(style=StreamPipe, level=OuterControl).name("x4672").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:42:17") } // UnitPipe(List(Const(true)),Block(Const(())))
    val b5155 = withCtrl(x4672) { StreamOut(field="offset").name("b5155").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:42:17") } // x4655 = StreamOutNew(BurstCmdBus)
    isAccum(b5155) = false
    bufferDepthOf(b5155) = 1
    val b5156 = withCtrl(x4672) { StreamOut(field="size").name("b5156").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:42:17") } // x4655 = StreamOutNew(BurstCmdBus)
    isAccum(b5156) = false
    bufferDepthOf(b5156) = 1
    val x4656 = withCtrl(x4672) { StreamIn(field="data").name("x4656").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:42:17") } // x4656 = StreamInNew(BurstDataBus())
    isAccum(x4656) = false
    bufferDepthOf(x4656) = 1
    val x4664 = withCtrl(x4672) { UnitController(style=SeqPipe, level=InnerControl).name("x4664").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:42:17") } // UnitPipe(List(Const(true)),Block(x4663))
    val x4657 = withCtrl(x4664) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x4658 = withCtrl(x4664) { OpDef(op=FixSla, inputs=List(x4657, Const(2))).name("x4658").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:42:17") } // FixLsh(x4657,Const(2))
    val x4659 = withCtrl(x4664) { x4658 } // FixConvert(x4658,TRUE,_64,_0)
    val x4660 = withCtrl(x4664) { DramAddress(x4629).name("x4660").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:42:17") } // GetDRAMAddress(x4629)
    val x4662_x4661 = withCtrl(x4664) { OpDef(op=FixAdd, inputs=List(x4659, x4660)).name("x4662_x4661").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:42:17") } // FixAdd(x4659,x4660)
    // x4662 = SimpleStruct(ArrayBuffer((offset,x4661), (size,Const(512)), (isLoad,Const(true))))
    val x4663_b5157_b5155 = withCtrl(x4664) { WriteMem(b5155, x4662_x4661).name("x4663_b5157_b5155").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:42:17") } // StreamWrite(x4655,x4662,Const(true))
    val x4663_b5158_b5156 = withCtrl(x4664) { WriteMem(b5156, Const(512)).name("x4663_b5158_b5156").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:42:17") } // StreamWrite(x4655,x4662,Const(true))
    val x4665 = withCtrl(x4672) { FringeDenseLoad(dram=List(x4629), cmdStream=List(b5155, b5156), dataStream=List(x4656)).name("x4665").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:42:17") } // FringeDenseLoad(x4629,x4655,x4656)
    val x4666 = withCtrl(x4672) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x4666").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:42:17") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x4667 = withCtrl(x4672) { CounterChain(List(x4666)).name("x4667").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:42:17") } // CounterChainNew(List(x4666))
    val x4671 = withCtrl(x4672) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4667).name("x4671").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:42:17") } // UnrolledForeach(List(Const(true)),x4667,Block(Const(())),List(List(b1914)),List(List(b1915)))
    val b1914 = withCtrl(x4671) { CounterIter(x4666, None).name("b1914") } // b1914
    val b1915 = withCtrl(x4671) { Const(true).name("b1915") } // b1915
    val x4668_x4668 = withCtrl(x4671) { ReadMem(x4656).name("x4668_x4668").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:42:17") } // ParStreamRead(x4656,List(b1915))
    val x4669_x4669 = withCtrl(x4671) { x4668_x4668 } // VectorApply(x4668,0)
    val x4670 = withCtrl(x4671) { StoreBanks(List(List(x4636_d0_b0), List(x4636_d5_b0), List(x4636_d1_b0), List(x4636_d6_b0), List(x4636_d2_b0), List(x4636_d7_b0), List(x4636_d3_b0), List(x4636_d4_b0)), List(b1914), x4669_x4669).name("x4670").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:42:17") } // ParSRAMStore(x4636,List(List(b1914)),List(x4669),List(b1915))
    val x4674_d0_b0 = withCtrl(x5099) { SRAM(size=16384, banking=Strided(banks=16, stride=1)).name("x4674_d0_b0").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:45:29:sigmaOut") } // x4674 = SRAMNew(ArrayBuffer(Const(128), Const(128)))
    isAccum(x4674_d0_b0) = false
    bufferDepthOf(x4674_d0_b0) = 1
    staticDimsOf(x4674_d0_b0) = List(128, 128)
    val x4674_d1_b0 = withCtrl(x5099) { SRAM(size=16384, banking=Strided(banks=16, stride=1)).name("x4674_d1_b0").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:45:29:sigmaOut") } // x4674 = SRAMNew(ArrayBuffer(Const(128), Const(128)))
    isAccum(x4674_d1_b0) = true
    bufferDepthOf(x4674_d1_b0) = 1
    staticDimsOf(x4674_d1_b0) = List(128, 128)
    val x4675 = withCtrl(x5099) { ReadMem(x4622).name("x4675").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:47:34") } // RegRead(x4622)
    val x4676 = withCtrl(x5099) { Counter(min=Const(0), max=x4675, step=Const(512), par=1).name("x4676").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:47:42") } // CounterNew(Const(0),x4675,Const(512),Const(1))
    val x4677 = withCtrl(x5099) { CounterChain(List(x4676)).name("x4677").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:71:8") } // CounterChainNew(List(x4676))
    val x5070 = withCtrl(x5099) { LoopController(style=MetaPipe, level=OuterControl, cchain=x4677).name("x5070").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:71:8") } // UnrolledReduce(List(Const(true)),x4677,x4674,Block((x4674) => Const(())),List(List(b1929)),List(List(b1930)))
    val b1929 = withCtrl(x5070) { CounterIter(x4676, Some(0)).name("b1929") } // b1929
    val b1930 = withCtrl(x5070) { Const(true).name("b1930") } // b1930
    val x4678_d0_b0 = withCtrl(x5070) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x4678_d0_b0").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:48:33:gdaYtile") } // x4678 = SRAMNew(ArrayBuffer(Const(512)))
    isAccum(x4678_d0_b0) = false
    bufferDepthOf(x4678_d0_b0) = 2
    staticDimsOf(x4678_d0_b0) = List(512)
    val x4679_d0_b0 = withCtrl(x5070) { SRAM(size=8192, banking=Strided(banks=16, stride=1)).name("x4679_d0_b0").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:49:31:gdaXtile") } // x4679 = SRAMNew(ArrayBuffer(Const(512), Const(128)))
    isAccum(x4679_d0_b0) = false
    bufferDepthOf(x4679_d0_b0) = 2
    staticDimsOf(x4679_d0_b0) = List(512, 128)
    val x4679_d0_b1 = withCtrl(x5070) { SRAM(size=8192, banking=Strided(banks=16, stride=1)).name("x4679_d0_b1").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:49:31:gdaXtile") } // x4679 = SRAMNew(ArrayBuffer(Const(512), Const(128)))
    isAccum(x4679_d0_b1) = false
    bufferDepthOf(x4679_d0_b1) = 2
    staticDimsOf(x4679_d0_b1) = List(512, 128)
    val x4679_d0_b2 = withCtrl(x5070) { SRAM(size=8192, banking=Strided(banks=16, stride=1)).name("x4679_d0_b2").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:49:31:gdaXtile") } // x4679 = SRAMNew(ArrayBuffer(Const(512), Const(128)))
    isAccum(x4679_d0_b2) = false
    bufferDepthOf(x4679_d0_b2) = 2
    staticDimsOf(x4679_d0_b2) = List(512, 128)
    val x4679_d0_b3 = withCtrl(x5070) { SRAM(size=8192, banking=Strided(banks=16, stride=1)).name("x4679_d0_b3").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:49:31:gdaXtile") } // x4679 = SRAMNew(ArrayBuffer(Const(512), Const(128)))
    isAccum(x4679_d0_b3) = false
    bufferDepthOf(x4679_d0_b3) = 2
    staticDimsOf(x4679_d0_b3) = List(512, 128)
    val x4679_d0_b4 = withCtrl(x5070) { SRAM(size=8192, banking=Strided(banks=16, stride=1)).name("x4679_d0_b4").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:49:31:gdaXtile") } // x4679 = SRAMNew(ArrayBuffer(Const(512), Const(128)))
    isAccum(x4679_d0_b4) = false
    bufferDepthOf(x4679_d0_b4) = 2
    staticDimsOf(x4679_d0_b4) = List(512, 128)
    val x4679_d0_b5 = withCtrl(x5070) { SRAM(size=8192, banking=Strided(banks=16, stride=1)).name("x4679_d0_b5").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:49:31:gdaXtile") } // x4679 = SRAMNew(ArrayBuffer(Const(512), Const(128)))
    isAccum(x4679_d0_b5) = false
    bufferDepthOf(x4679_d0_b5) = 2
    staticDimsOf(x4679_d0_b5) = List(512, 128)
    val x4679_d0_b6 = withCtrl(x5070) { SRAM(size=8192, banking=Strided(banks=16, stride=1)).name("x4679_d0_b6").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:49:31:gdaXtile") } // x4679 = SRAMNew(ArrayBuffer(Const(512), Const(128)))
    isAccum(x4679_d0_b6) = false
    bufferDepthOf(x4679_d0_b6) = 2
    staticDimsOf(x4679_d0_b6) = List(512, 128)
    val x4679_d0_b7 = withCtrl(x5070) { SRAM(size=8192, banking=Strided(banks=16, stride=1)).name("x4679_d0_b7").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:49:31:gdaXtile") } // x4679 = SRAMNew(ArrayBuffer(Const(512), Const(128)))
    isAccum(x4679_d0_b7) = false
    bufferDepthOf(x4679_d0_b7) = 2
    staticDimsOf(x4679_d0_b7) = List(512, 128)
    val x4728 = withCtrl(x5070) { UnitController(style=ForkJoin, level=OuterControl).name("x4728").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:51:18") } // ParallelPipe(List(b1930),Block(Const(())))
    val x4681 = withCtrl(x4728) { UnitController(style=SeqPipe, level=InnerControl).name("x4681").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:51:18") } // UnitPipe(List(b1930),Block(Const(())))
    val x4680 = withCtrl(x4681) { OpDef(op=FixAdd, inputs=List(b1929, Const(512))).name("x4680").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:52:34") } // FixAdd(b1929,Const(512))
    val x4700 = withCtrl(x4728) { UnitController(style=StreamPipe, level=OuterControl).name("x4700").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:52:20") } // UnitPipe(List(b1930),Block(Const(())))
    val b5159 = withCtrl(x4700) { StreamOut(field="offset").name("b5159").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:52:20") } // x4682 = StreamOutNew(BurstCmdBus)
    isAccum(b5159) = false
    bufferDepthOf(b5159) = 1
    val b5160 = withCtrl(x4700) { StreamOut(field="size").name("b5160").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:52:20") } // x4682 = StreamOutNew(BurstCmdBus)
    isAccum(b5160) = false
    bufferDepthOf(b5160) = 1
    val x4683 = withCtrl(x4700) { StreamIn(field="data").name("x4683").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:52:20") } // x4683 = StreamInNew(BurstDataBus())
    isAccum(x4683) = false
    bufferDepthOf(x4683) = 1
    val x4691 = withCtrl(x4700) { UnitController(style=SeqPipe, level=InnerControl).name("x4691").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:52:20") } // UnitPipe(List(b1930),Block(x4690))
    val x4684 = withCtrl(x4691) { b1929 } // FixConvert(b1929,TRUE,_32,_0) (Same Type. No op)
    val x4685 = withCtrl(x4691) { OpDef(op=FixSla, inputs=List(x4684, Const(2))).name("x4685").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:52:20") } // FixLsh(x4684,Const(2))
    val x4686 = withCtrl(x4691) { x4685 } // FixConvert(x4685,TRUE,_64,_0)
    val x4687 = withCtrl(x4691) { DramAddress(x4627).name("x4687").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:52:20") } // GetDRAMAddress(x4627)
    val x4689_x4688 = withCtrl(x4691) { OpDef(op=FixAdd, inputs=List(x4686, x4687)).name("x4689_x4688").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:52:20") } // FixAdd(x4686,x4687)
    // x4689 = SimpleStruct(ArrayBuffer((offset,x4688), (size,Const(2048)), (isLoad,Const(true))))
    val x4690_b5161_b5159 = withCtrl(x4691) { WriteMem(b5159, x4689_x4688).name("x4690_b5161_b5159").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:52:20") } // StreamWrite(x4682,x4689,b1930)
    val x4690_b5162_b5160 = withCtrl(x4691) { WriteMem(b5160, Const(2048)).name("x4690_b5162_b5160").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:52:20") } // StreamWrite(x4682,x4689,b1930)
    val x4692 = withCtrl(x4700) { FringeDenseLoad(dram=List(x4627), cmdStream=List(b5159, b5160), dataStream=List(x4683)).name("x4692").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:52:20") } // FringeDenseLoad(x4627,x4682,x4683)
    val x4693 = withCtrl(x4700) { Counter(min=Const(0), max=Const(512), step=Const(1), par=16).name("x4693").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:52:20") } // CounterNew(Const(0),Const(512),Const(1),Const(16))
    val x4694 = withCtrl(x4700) { CounterChain(List(x4693)).name("x4694").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:52:20") } // CounterChainNew(List(x4693))
    val x4699 = withCtrl(x4700) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4694).name("x4699").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:52:20") } // UnrolledForeach(List(b1930),x4694,Block(Const(())),List(List(b1948)),List(List(b1949)))
    val b1948 = withCtrl(x4699) { CounterIter(x4693, None).name("b1948") } // b1948
    val b1949 = withCtrl(x4699) { Const(true).name("b1949") } // b1949
    val x4695 = withCtrl(x4699) { OpDef(op=BitAnd, inputs=List(b1949, b1930)).name("x4695").srcCtx("UnrollingBase.scala:28:66") } // And(b1949,b1930)
    val x4696_x4696 = withCtrl(x4699) { ReadMem(x4683).name("x4696_x4696").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:52:20") } // ParStreamRead(x4683,List(x4695))
    val x4697_x4697 = withCtrl(x4699) { x4696_x4696 } // VectorApply(x4696,0)
    val x4698 = withCtrl(x4699) { StoreBanks(List(List(x4678_d0_b0)), List(b1948), x4697_x4697).name("x4698").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:52:20") } // ParSRAMStore(x4678,List(List(b1948)),List(x4697),List(x4695))
    val x4701 = withCtrl(x4728) { Counter(min=Const(0), max=Const(512), step=Const(1), par=1).name("x4701").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:53:20") } // CounterNew(Const(0),Const(512),Const(1),Const(1))
    val x4702 = withCtrl(x4728) { CounterChain(List(x4701)).name("x4702").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:53:20") } // CounterChainNew(List(x4701))
    val x4727 = withCtrl(x4728) { LoopController(style=StreamPipe, level=OuterControl, cchain=x4702).name("x4727").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:53:20") } // UnrolledForeach(List(b1930),x4702,Block(Const(())),List(List(b1958)),List(List(b1959)))
    val b1958 = withCtrl(x4727) { CounterIter(x4701, Some(0)).name("b1958") } // b1958
    val b1959 = withCtrl(x4727) { Const(true).name("b1959") } // b1959
    val b5163 = withCtrl(x4727) { StreamOut(field="offset").name("b5163").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:53:20") } // x4703 = StreamOutNew(BurstCmdBus)
    isAccum(b5163) = false
    bufferDepthOf(b5163) = 1
    val b5164 = withCtrl(x4727) { StreamOut(field="size").name("b5164").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:53:20") } // x4703 = StreamOutNew(BurstCmdBus)
    isAccum(b5164) = false
    bufferDepthOf(b5164) = 1
    val x4704 = withCtrl(x4727) { StreamIn(field="data").name("x4704").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:53:20") } // x4704 = StreamInNew(BurstDataBus())
    isAccum(x4704) = false
    bufferDepthOf(x4704) = 1
    val x4717 = withCtrl(x4727) { UnitController(style=SeqPipe, level=InnerControl).name("x4717").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:53:20") } // UnitPipe(List(b1959, b1930),Block(x4716))
    val x4705 = withCtrl(x4717) { OpDef(op=FixAdd, inputs=List(b1929, b1958)).name("x4705").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:53:20") } // FixAdd(b1929,b1958)
    val x4706 = withCtrl(x4717) { x4705 } // FixConvert(x4705,TRUE,_32,_0) (Same Type. No op)
    val x4707 = withCtrl(x4717) { OpDef(op=FixSla, inputs=List(x4706, Const(7))).name("x4707").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:53:20") } // FixLsh(x4706,Const(7))
    val x4708 = withCtrl(x4717) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x4709 = withCtrl(x4717) { OpDef(op=FixAdd, inputs=List(x4707, x4708)).name("x4709").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:53:20") } // FixAdd(x4707,x4708)
    val x4710 = withCtrl(x4717) { OpDef(op=FixSla, inputs=List(x4709, Const(2))).name("x4710").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:53:20") } // FixLsh(x4709,Const(2))
    val x4711 = withCtrl(x4717) { x4710 } // FixConvert(x4710,TRUE,_64,_0)
    val x4712 = withCtrl(x4717) { DramAddress(x4625).name("x4712").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:53:20") } // GetDRAMAddress(x4625)
    val x4714_x4713 = withCtrl(x4717) { OpDef(op=FixAdd, inputs=List(x4711, x4712)).name("x4714_x4713").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:53:20") } // FixAdd(x4711,x4712)
    // x4714 = SimpleStruct(ArrayBuffer((offset,x4713), (size,Const(512)), (isLoad,Const(true))))
    val x4715 = withCtrl(x4717) { OpDef(op=BitAnd, inputs=List(b1959, b1930)).name("x4715").srcCtx("UnrollingBase.scala:28:66") } // And(b1959,b1930)
    val x4716_b5165_b5163 = withCtrl(x4717) { WriteMem(b5163, x4714_x4713).name("x4716_b5165_b5163").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:53:20") } // StreamWrite(x4703,x4714,x4715)
    val x4716_b5166_b5164 = withCtrl(x4717) { WriteMem(b5164, Const(512)).name("x4716_b5166_b5164").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:53:20") } // StreamWrite(x4703,x4714,x4715)
    val x4718 = withCtrl(x4727) { FringeDenseLoad(dram=List(x4625), cmdStream=List(b5163, b5164), dataStream=List(x4704)).name("x4718").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:53:20") } // FringeDenseLoad(x4625,x4703,x4704)
    val x4719 = withCtrl(x4727) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x4719").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:53:20") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x4720 = withCtrl(x4727) { CounterChain(List(x4719)).name("x4720").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:53:20") } // CounterChainNew(List(x4719))
    val x4726 = withCtrl(x4727) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4720).name("x4726").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:53:20") } // UnrolledForeach(List(b1959, b1930),x4720,Block(Const(())),List(List(b1978)),List(List(b1979)))
    val b1978 = withCtrl(x4726) { CounterIter(x4719, None).name("b1978") } // b1978
    val b1979 = withCtrl(x4726) { Const(true).name("b1979") } // b1979
    val x4721 = withCtrl(x4726) { OpDef(op=BitAnd, inputs=List(b1979, b1959)).name("x4721").srcCtx("UnrollingBase.scala:28:66") } // And(b1979,b1959)
    val x4722 = withCtrl(x4726) { OpDef(op=BitAnd, inputs=List(x4721, b1930)).name("x4722").srcCtx("UnrollingBase.scala:28:66") } // And(x4721,b1930)
    val x4723_x4723 = withCtrl(x4726) { ReadMem(x4704).name("x4723_x4723").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:53:20") } // ParStreamRead(x4704,List(x4722))
    val x4724_x4724 = withCtrl(x4726) { x4723_x4723 } // VectorApply(x4723,0)
    val x4725 = withCtrl(x4726) { StoreBanks(List(List(x4679_d0_b0, x4679_d0_b1, x4679_d0_b2, x4679_d0_b3, x4679_d0_b4, x4679_d0_b5, x4679_d0_b6, x4679_d0_b7)), List(b1958, b1978), x4724_x4724).name("x4725").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:53:20") } // ParSRAMStore(x4679,List(List(b1958, b1978)),List(x4724),List(x4722))
    val x4729_d0_b0 = withCtrl(x5070) { SRAM(size=16384, banking=Strided(banks=16, stride=1)).name("x4729_d0_b0").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:56:31:sigmaBlk") } // x4729 = SRAMNew(ArrayBuffer(Const(128), Const(128)))
    isAccum(x4729_d0_b0) = false
    bufferDepthOf(x4729_d0_b0) = 2
    staticDimsOf(x4729_d0_b0) = List(128, 128)
    val x4729_d1_b0 = withCtrl(x5070) { SRAM(size=16384, banking=Strided(banks=16, stride=1)).name("x4729_d1_b0").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:56:31:sigmaBlk") } // x4729 = SRAMNew(ArrayBuffer(Const(128), Const(128)))
    isAccum(x4729_d1_b0) = true
    bufferDepthOf(x4729_d1_b0) = 1
    staticDimsOf(x4729_d1_b0) = List(128, 128)
    val x4730 = withCtrl(x5070) { Counter(min=Const(0), max=Const(512), step=Const(1), par=8).name("x4730").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:58:39") } // CounterNew(Const(0),Const(512),Const(1),Const(8))
    val x4731 = withCtrl(x5070) { CounterChain(List(x4730)).name("x4731").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:70:10") } // CounterChainNew(List(x4730))
    val x5055 = withCtrl(x5070) { LoopController(style=MetaPipe, level=OuterControl, cchain=x4731).name("x5055").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:70:10") } // UnrolledReduce(List(b1930),x4731,x4729,Block((x4729) => Const(())),List(List(b1994, b1995, b1996, b1997, b1998, b1999, b2000, b2001)),List(List(b2002, b2003, b2004, b2005, b2006, b2007, b2008, b2009)))
    val b1994 = withCtrl(x5055) { CounterIter(x4730, Some(0)).name("b1994") } // b1994
    val b2002 = withCtrl(x5055) { Const(true).name("b2002") } // b2002
    val b1995 = withCtrl(x5055) { CounterIter(x4730, Some(1)).name("b1995") } // b1995
    val b2003 = withCtrl(x5055) { Const(true).name("b2003") } // b2003
    val b1996 = withCtrl(x5055) { CounterIter(x4730, Some(2)).name("b1996") } // b1996
    val b2004 = withCtrl(x5055) { Const(true).name("b2004") } // b2004
    val b1997 = withCtrl(x5055) { CounterIter(x4730, Some(3)).name("b1997") } // b1997
    val b2005 = withCtrl(x5055) { Const(true).name("b2005") } // b2005
    val b1998 = withCtrl(x5055) { CounterIter(x4730, Some(4)).name("b1998") } // b1998
    val b2006 = withCtrl(x5055) { Const(true).name("b2006") } // b2006
    val b1999 = withCtrl(x5055) { CounterIter(x4730, Some(5)).name("b1999") } // b1999
    val b2007 = withCtrl(x5055) { Const(true).name("b2007") } // b2007
    val b2000 = withCtrl(x5055) { CounterIter(x4730, Some(6)).name("b2000") } // b2000
    val b2008 = withCtrl(x5055) { Const(true).name("b2008") } // b2008
    val b2001 = withCtrl(x5055) { CounterIter(x4730, Some(7)).name("b2001") } // b2001
    val b2009 = withCtrl(x5055) { Const(true).name("b2009") } // b2009
    val x4732_d0_b0 = withCtrl(x5055) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x4732_d0_b0").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:59:32:subTile") } // x4732 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x4732_d0_b0) = false
    bufferDepthOf(x4732_d0_b0) = 2
    staticDimsOf(x4732_d0_b0) = List(128)
    val x4732_d1_b0 = withCtrl(x5055) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x4732_d1_b0").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:59:32:subTile") } // x4732 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x4732_d1_b0) = false
    bufferDepthOf(x4732_d1_b0) = 2
    staticDimsOf(x4732_d1_b0) = List(128)
    val x4733_d0_b0 = withCtrl(x5055) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x4733_d0_b0").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:59:32:subTile") } // x4733 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x4733_d0_b0) = false
    bufferDepthOf(x4733_d0_b0) = 2
    staticDimsOf(x4733_d0_b0) = List(128)
    val x4733_d1_b0 = withCtrl(x5055) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x4733_d1_b0").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:59:32:subTile") } // x4733 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x4733_d1_b0) = false
    bufferDepthOf(x4733_d1_b0) = 2
    staticDimsOf(x4733_d1_b0) = List(128)
    val x4734_d0_b0 = withCtrl(x5055) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x4734_d0_b0").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:59:32:subTile") } // x4734 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x4734_d0_b0) = false
    bufferDepthOf(x4734_d0_b0) = 2
    staticDimsOf(x4734_d0_b0) = List(128)
    val x4734_d1_b0 = withCtrl(x5055) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x4734_d1_b0").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:59:32:subTile") } // x4734 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x4734_d1_b0) = false
    bufferDepthOf(x4734_d1_b0) = 2
    staticDimsOf(x4734_d1_b0) = List(128)
    val x4735_d0_b0 = withCtrl(x5055) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x4735_d0_b0").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:59:32:subTile") } // x4735 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x4735_d0_b0) = false
    bufferDepthOf(x4735_d0_b0) = 2
    staticDimsOf(x4735_d0_b0) = List(128)
    val x4735_d1_b0 = withCtrl(x5055) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x4735_d1_b0").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:59:32:subTile") } // x4735 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x4735_d1_b0) = false
    bufferDepthOf(x4735_d1_b0) = 2
    staticDimsOf(x4735_d1_b0) = List(128)
    val x4736_d0_b0 = withCtrl(x5055) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x4736_d0_b0").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:59:32:subTile") } // x4736 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x4736_d0_b0) = false
    bufferDepthOf(x4736_d0_b0) = 2
    staticDimsOf(x4736_d0_b0) = List(128)
    val x4736_d1_b0 = withCtrl(x5055) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x4736_d1_b0").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:59:32:subTile") } // x4736 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x4736_d1_b0) = false
    bufferDepthOf(x4736_d1_b0) = 2
    staticDimsOf(x4736_d1_b0) = List(128)
    val x4737_d0_b0 = withCtrl(x5055) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x4737_d0_b0").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:59:32:subTile") } // x4737 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x4737_d0_b0) = false
    bufferDepthOf(x4737_d0_b0) = 2
    staticDimsOf(x4737_d0_b0) = List(128)
    val x4737_d1_b0 = withCtrl(x5055) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x4737_d1_b0").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:59:32:subTile") } // x4737 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x4737_d1_b0) = false
    bufferDepthOf(x4737_d1_b0) = 2
    staticDimsOf(x4737_d1_b0) = List(128)
    val x4738_d0_b0 = withCtrl(x5055) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x4738_d0_b0").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:59:32:subTile") } // x4738 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x4738_d0_b0) = false
    bufferDepthOf(x4738_d0_b0) = 2
    staticDimsOf(x4738_d0_b0) = List(128)
    val x4738_d1_b0 = withCtrl(x5055) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x4738_d1_b0").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:59:32:subTile") } // x4738 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x4738_d1_b0) = false
    bufferDepthOf(x4738_d1_b0) = 2
    staticDimsOf(x4738_d1_b0) = List(128)
    val x4739_d0_b0 = withCtrl(x5055) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x4739_d0_b0").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:59:32:subTile") } // x4739 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x4739_d0_b0) = false
    bufferDepthOf(x4739_d0_b0) = 2
    staticDimsOf(x4739_d0_b0) = List(128)
    val x4739_d1_b0 = withCtrl(x5055) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x4739_d1_b0").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:59:32:subTile") } // x4739 = SRAMNew(ArrayBuffer(Const(128)))
    isAccum(x4739_d1_b0) = false
    bufferDepthOf(x4739_d1_b0) = 2
    staticDimsOf(x4739_d1_b0) = List(128)
    val x4740_d0_b0 = withCtrl(x5055) { SRAM(size=16384, banking=Strided(banks=16, stride=1)).name("x4740_d0_b0").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:60:34:sigmaTile") } // x4740 = SRAMNew(ArrayBuffer(Const(128), Const(128)))
    isAccum(x4740_d0_b0) = false
    bufferDepthOf(x4740_d0_b0) = 2
    staticDimsOf(x4740_d0_b0) = List(128, 128)
    val x4741_d0_b0 = withCtrl(x5055) { SRAM(size=16384, banking=Strided(banks=16, stride=1)).name("x4741_d0_b0").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:60:34:sigmaTile") } // x4741 = SRAMNew(ArrayBuffer(Const(128), Const(128)))
    isAccum(x4741_d0_b0) = false
    bufferDepthOf(x4741_d0_b0) = 2
    staticDimsOf(x4741_d0_b0) = List(128, 128)
    val x4742_d0_b0 = withCtrl(x5055) { SRAM(size=16384, banking=Strided(banks=16, stride=1)).name("x4742_d0_b0").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:60:34:sigmaTile") } // x4742 = SRAMNew(ArrayBuffer(Const(128), Const(128)))
    isAccum(x4742_d0_b0) = false
    bufferDepthOf(x4742_d0_b0) = 2
    staticDimsOf(x4742_d0_b0) = List(128, 128)
    val x4743_d0_b0 = withCtrl(x5055) { SRAM(size=16384, banking=Strided(banks=16, stride=1)).name("x4743_d0_b0").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:60:34:sigmaTile") } // x4743 = SRAMNew(ArrayBuffer(Const(128), Const(128)))
    isAccum(x4743_d0_b0) = false
    bufferDepthOf(x4743_d0_b0) = 2
    staticDimsOf(x4743_d0_b0) = List(128, 128)
    val x4744_d0_b0 = withCtrl(x5055) { SRAM(size=16384, banking=Strided(banks=16, stride=1)).name("x4744_d0_b0").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:60:34:sigmaTile") } // x4744 = SRAMNew(ArrayBuffer(Const(128), Const(128)))
    isAccum(x4744_d0_b0) = false
    bufferDepthOf(x4744_d0_b0) = 2
    staticDimsOf(x4744_d0_b0) = List(128, 128)
    val x4745_d0_b0 = withCtrl(x5055) { SRAM(size=16384, banking=Strided(banks=16, stride=1)).name("x4745_d0_b0").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:60:34:sigmaTile") } // x4745 = SRAMNew(ArrayBuffer(Const(128), Const(128)))
    isAccum(x4745_d0_b0) = false
    bufferDepthOf(x4745_d0_b0) = 2
    staticDimsOf(x4745_d0_b0) = List(128, 128)
    val x4746_d0_b0 = withCtrl(x5055) { SRAM(size=16384, banking=Strided(banks=16, stride=1)).name("x4746_d0_b0").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:60:34:sigmaTile") } // x4746 = SRAMNew(ArrayBuffer(Const(128), Const(128)))
    isAccum(x4746_d0_b0) = false
    bufferDepthOf(x4746_d0_b0) = 2
    staticDimsOf(x4746_d0_b0) = List(128, 128)
    val x4747_d0_b0 = withCtrl(x5055) { SRAM(size=16384, banking=Strided(banks=16, stride=1)).name("x4747_d0_b0").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:60:34:sigmaTile") } // x4747 = SRAMNew(ArrayBuffer(Const(128), Const(128)))
    isAccum(x4747_d0_b0) = false
    bufferDepthOf(x4747_d0_b0) = 2
    staticDimsOf(x4747_d0_b0) = List(128, 128)
    val x4876 = withCtrl(x5055) { UnitController(style=ForkJoin, level=OuterControl).name("x4876").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b1930),Block(Const(())))
    val x4748 = withCtrl(x4876) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x4748").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:61:21") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x4749 = withCtrl(x4876) { CounterChain(List(x4748)).name("x4749").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:61:29") } // CounterChainNew(List(x4748))
    val x4763 = withCtrl(x4876) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4749).name("x4763").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:61:29") } // UnrolledForeach(List(b2002, b1930),x4749,Block(Const(())),List(List(b2042)),List(List(b2043)))
    val b2042 = withCtrl(x4763) { CounterIter(x4748, None).name("b2042") } // b2042
    val b2043 = withCtrl(x4763) { Const(true).name("b2043") } // b2043
    val x4750 = withCtrl(x4763) { OpDef(op=BitAnd, inputs=List(b2043, b2002)).name("x4750").srcCtx("UnrollingBase.scala:28:66") } // And(b2043,b2002)
    val x4751 = withCtrl(x4763) { OpDef(op=BitAnd, inputs=List(x4750, b1930)).name("x4751").srcCtx("UnrollingBase.scala:28:66") } // And(x4750,b1930)
    val x4752 = withCtrl(x4763) { LoadBanks(List(x4679_d0_b0), List(b1994, b2042)).name("x4752").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:62:35") } // ParSRAMLoad(x4679,List(List(b1994, b2042)),List(x4751))
    val x4753 = withCtrl(x4763) { x4752 } // VectorApply(x4752,0)
    val x4754 = withCtrl(x4763) { LoadBanks(List(x4678_d0_b0), List(b1994)).name("x4754").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:62:58") } // SRAMLoad(x4678,ArrayBuffer(Const(512)),List(b1994),Const(0),x4751)
    val x4755 = withCtrl(x4763) { OpDef(op=FixEql, inputs=List(x4754, Const(1))).name("x4755").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:62:63") } // FixEql(x4754,Const(1))
    val x4756 = withCtrl(x4763) { LoadBanks(List(x4636_d0_b0), List(b2042)).name("x4756").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:62:76") } // ParSRAMLoad(x4636,List(List(b2042)),List(x4751))
    val x4757 = withCtrl(x4763) { x4756 } // VectorApply(x4756,0)
    val x4758 = withCtrl(x4763) { LoadBanks(List(x4635_d0_b0), List(b2042)).name("x4758").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:62:89") } // ParSRAMLoad(x4635,List(List(b2042)),List(x4751))
    val x4759 = withCtrl(x4763) { x4758 } // VectorApply(x4758,0)
    val x4760 = withCtrl(x4763) { OpDef(op=MuxOp, inputs=List(x4755, x4757, x4759)).name("x4760").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:62:49") } // Mux(x4755,x4757,x4759)
    val x4761 = withCtrl(x4763) { OpDef(op=FixSub, inputs=List(x4753, x4760)).name("x4761").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:62:44") } // FixSub(x4753,x4760)
    val x4762 = withCtrl(x4763) { StoreBanks(List(List(x4732_d0_b0), List(x4732_d1_b0)), List(b2042), x4761).name("x4762").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:62:25") } // ParSRAMStore(x4732,List(List(b2042)),List(x4761),List(x4751))
    val x4764 = withCtrl(x4876) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x4764").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:61:21") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x4765 = withCtrl(x4876) { CounterChain(List(x4764)).name("x4765").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:61:29") } // CounterChainNew(List(x4764))
    val x4779 = withCtrl(x4876) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4765).name("x4779").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:61:29") } // UnrolledForeach(List(b2003, b1930),x4765,Block(Const(())),List(List(b2058)),List(List(b2059)))
    val b2058 = withCtrl(x4779) { CounterIter(x4764, None).name("b2058") } // b2058
    val b2059 = withCtrl(x4779) { Const(true).name("b2059") } // b2059
    val x4766 = withCtrl(x4779) { OpDef(op=BitAnd, inputs=List(b2059, b2003)).name("x4766").srcCtx("UnrollingBase.scala:28:66") } // And(b2059,b2003)
    val x4767 = withCtrl(x4779) { OpDef(op=BitAnd, inputs=List(x4766, b1930)).name("x4767").srcCtx("UnrollingBase.scala:28:66") } // And(x4766,b1930)
    val x4768 = withCtrl(x4779) { LoadBanks(List(x4679_d0_b1), List(b1995, b2058)).name("x4768").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:62:35") } // ParSRAMLoad(x4679,List(List(b1995, b2058)),List(x4767))
    val x4769 = withCtrl(x4779) { x4768 } // VectorApply(x4768,0)
    val x4770 = withCtrl(x4779) { LoadBanks(List(x4678_d0_b0), List(b1995)).name("x4770").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:62:58") } // SRAMLoad(x4678,ArrayBuffer(Const(512)),List(b1995),Const(0),x4767)
    val x4771 = withCtrl(x4779) { OpDef(op=FixEql, inputs=List(x4770, Const(1))).name("x4771").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:62:63") } // FixEql(x4770,Const(1))
    val x4772 = withCtrl(x4779) { LoadBanks(List(x4636_d1_b0), List(b2058)).name("x4772").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:62:76") } // ParSRAMLoad(x4636,List(List(b2058)),List(x4767))
    val x4773 = withCtrl(x4779) { x4772 } // VectorApply(x4772,0)
    val x4774 = withCtrl(x4779) { LoadBanks(List(x4635_d1_b0), List(b2058)).name("x4774").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:62:89") } // ParSRAMLoad(x4635,List(List(b2058)),List(x4767))
    val x4775 = withCtrl(x4779) { x4774 } // VectorApply(x4774,0)
    val x4776 = withCtrl(x4779) { OpDef(op=MuxOp, inputs=List(x4771, x4773, x4775)).name("x4776").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:62:49") } // Mux(x4771,x4773,x4775)
    val x4777 = withCtrl(x4779) { OpDef(op=FixSub, inputs=List(x4769, x4776)).name("x4777").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:62:44") } // FixSub(x4769,x4776)
    val x4778 = withCtrl(x4779) { StoreBanks(List(List(x4733_d0_b0), List(x4733_d1_b0)), List(b2058), x4777).name("x4778").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:62:25") } // ParSRAMStore(x4733,List(List(b2058)),List(x4777),List(x4767))
    val x4780 = withCtrl(x4876) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x4780").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:61:21") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x4781 = withCtrl(x4876) { CounterChain(List(x4780)).name("x4781").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:61:29") } // CounterChainNew(List(x4780))
    val x4795 = withCtrl(x4876) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4781).name("x4795").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:61:29") } // UnrolledForeach(List(b2004, b1930),x4781,Block(Const(())),List(List(b2074)),List(List(b2075)))
    val b2074 = withCtrl(x4795) { CounterIter(x4780, None).name("b2074") } // b2074
    val b2075 = withCtrl(x4795) { Const(true).name("b2075") } // b2075
    val x4782 = withCtrl(x4795) { OpDef(op=BitAnd, inputs=List(b2075, b2004)).name("x4782").srcCtx("UnrollingBase.scala:28:66") } // And(b2075,b2004)
    val x4783 = withCtrl(x4795) { OpDef(op=BitAnd, inputs=List(x4782, b1930)).name("x4783").srcCtx("UnrollingBase.scala:28:66") } // And(x4782,b1930)
    val x4784 = withCtrl(x4795) { LoadBanks(List(x4679_d0_b2), List(b1996, b2074)).name("x4784").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:62:35") } // ParSRAMLoad(x4679,List(List(b1996, b2074)),List(x4783))
    val x4785 = withCtrl(x4795) { x4784 } // VectorApply(x4784,0)
    val x4786 = withCtrl(x4795) { LoadBanks(List(x4678_d0_b0), List(b1996)).name("x4786").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:62:58") } // SRAMLoad(x4678,ArrayBuffer(Const(512)),List(b1996),Const(0),x4783)
    val x4787 = withCtrl(x4795) { OpDef(op=FixEql, inputs=List(x4786, Const(1))).name("x4787").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:62:63") } // FixEql(x4786,Const(1))
    val x4788 = withCtrl(x4795) { LoadBanks(List(x4636_d2_b0), List(b2074)).name("x4788").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:62:76") } // ParSRAMLoad(x4636,List(List(b2074)),List(x4783))
    val x4789 = withCtrl(x4795) { x4788 } // VectorApply(x4788,0)
    val x4790 = withCtrl(x4795) { LoadBanks(List(x4635_d2_b0), List(b2074)).name("x4790").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:62:89") } // ParSRAMLoad(x4635,List(List(b2074)),List(x4783))
    val x4791 = withCtrl(x4795) { x4790 } // VectorApply(x4790,0)
    val x4792 = withCtrl(x4795) { OpDef(op=MuxOp, inputs=List(x4787, x4789, x4791)).name("x4792").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:62:49") } // Mux(x4787,x4789,x4791)
    val x4793 = withCtrl(x4795) { OpDef(op=FixSub, inputs=List(x4785, x4792)).name("x4793").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:62:44") } // FixSub(x4785,x4792)
    val x4794 = withCtrl(x4795) { StoreBanks(List(List(x4734_d0_b0), List(x4734_d1_b0)), List(b2074), x4793).name("x4794").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:62:25") } // ParSRAMStore(x4734,List(List(b2074)),List(x4793),List(x4783))
    val x4796 = withCtrl(x4876) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x4796").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:61:21") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x4797 = withCtrl(x4876) { CounterChain(List(x4796)).name("x4797").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:61:29") } // CounterChainNew(List(x4796))
    val x4811 = withCtrl(x4876) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4797).name("x4811").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:61:29") } // UnrolledForeach(List(b2005, b1930),x4797,Block(Const(())),List(List(b2090)),List(List(b2091)))
    val b2090 = withCtrl(x4811) { CounterIter(x4796, None).name("b2090") } // b2090
    val b2091 = withCtrl(x4811) { Const(true).name("b2091") } // b2091
    val x4798 = withCtrl(x4811) { OpDef(op=BitAnd, inputs=List(b2091, b2005)).name("x4798").srcCtx("UnrollingBase.scala:28:66") } // And(b2091,b2005)
    val x4799 = withCtrl(x4811) { OpDef(op=BitAnd, inputs=List(x4798, b1930)).name("x4799").srcCtx("UnrollingBase.scala:28:66") } // And(x4798,b1930)
    val x4800 = withCtrl(x4811) { LoadBanks(List(x4679_d0_b3), List(b1997, b2090)).name("x4800").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:62:35") } // ParSRAMLoad(x4679,List(List(b1997, b2090)),List(x4799))
    val x4801 = withCtrl(x4811) { x4800 } // VectorApply(x4800,0)
    val x4802 = withCtrl(x4811) { LoadBanks(List(x4678_d0_b0), List(b1997)).name("x4802").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:62:58") } // SRAMLoad(x4678,ArrayBuffer(Const(512)),List(b1997),Const(0),x4799)
    val x4803 = withCtrl(x4811) { OpDef(op=FixEql, inputs=List(x4802, Const(1))).name("x4803").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:62:63") } // FixEql(x4802,Const(1))
    val x4804 = withCtrl(x4811) { LoadBanks(List(x4636_d3_b0), List(b2090)).name("x4804").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:62:76") } // ParSRAMLoad(x4636,List(List(b2090)),List(x4799))
    val x4805 = withCtrl(x4811) { x4804 } // VectorApply(x4804,0)
    val x4806 = withCtrl(x4811) { LoadBanks(List(x4635_d3_b0), List(b2090)).name("x4806").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:62:89") } // ParSRAMLoad(x4635,List(List(b2090)),List(x4799))
    val x4807 = withCtrl(x4811) { x4806 } // VectorApply(x4806,0)
    val x4808 = withCtrl(x4811) { OpDef(op=MuxOp, inputs=List(x4803, x4805, x4807)).name("x4808").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:62:49") } // Mux(x4803,x4805,x4807)
    val x4809 = withCtrl(x4811) { OpDef(op=FixSub, inputs=List(x4801, x4808)).name("x4809").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:62:44") } // FixSub(x4801,x4808)
    val x4810 = withCtrl(x4811) { StoreBanks(List(List(x4735_d0_b0), List(x4735_d1_b0)), List(b2090), x4809).name("x4810").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:62:25") } // ParSRAMStore(x4735,List(List(b2090)),List(x4809),List(x4799))
    val x4812 = withCtrl(x4876) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x4812").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:61:21") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x4813 = withCtrl(x4876) { CounterChain(List(x4812)).name("x4813").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:61:29") } // CounterChainNew(List(x4812))
    val x4827 = withCtrl(x4876) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4813).name("x4827").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:61:29") } // UnrolledForeach(List(b2006, b1930),x4813,Block(Const(())),List(List(b2106)),List(List(b2107)))
    val b2106 = withCtrl(x4827) { CounterIter(x4812, None).name("b2106") } // b2106
    val b2107 = withCtrl(x4827) { Const(true).name("b2107") } // b2107
    val x4814 = withCtrl(x4827) { OpDef(op=BitAnd, inputs=List(b2107, b2006)).name("x4814").srcCtx("UnrollingBase.scala:28:66") } // And(b2107,b2006)
    val x4815 = withCtrl(x4827) { OpDef(op=BitAnd, inputs=List(x4814, b1930)).name("x4815").srcCtx("UnrollingBase.scala:28:66") } // And(x4814,b1930)
    val x4816 = withCtrl(x4827) { LoadBanks(List(x4679_d0_b4), List(b1998, b2106)).name("x4816").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:62:35") } // ParSRAMLoad(x4679,List(List(b1998, b2106)),List(x4815))
    val x4817 = withCtrl(x4827) { x4816 } // VectorApply(x4816,0)
    val x4818 = withCtrl(x4827) { LoadBanks(List(x4678_d0_b0), List(b1998)).name("x4818").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:62:58") } // SRAMLoad(x4678,ArrayBuffer(Const(512)),List(b1998),Const(0),x4815)
    val x4819 = withCtrl(x4827) { OpDef(op=FixEql, inputs=List(x4818, Const(1))).name("x4819").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:62:63") } // FixEql(x4818,Const(1))
    val x4820 = withCtrl(x4827) { LoadBanks(List(x4636_d4_b0), List(b2106)).name("x4820").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:62:76") } // ParSRAMLoad(x4636,List(List(b2106)),List(x4815))
    val x4821 = withCtrl(x4827) { x4820 } // VectorApply(x4820,0)
    val x4822 = withCtrl(x4827) { LoadBanks(List(x4635_d4_b0), List(b2106)).name("x4822").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:62:89") } // ParSRAMLoad(x4635,List(List(b2106)),List(x4815))
    val x4823 = withCtrl(x4827) { x4822 } // VectorApply(x4822,0)
    val x4824 = withCtrl(x4827) { OpDef(op=MuxOp, inputs=List(x4819, x4821, x4823)).name("x4824").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:62:49") } // Mux(x4819,x4821,x4823)
    val x4825 = withCtrl(x4827) { OpDef(op=FixSub, inputs=List(x4817, x4824)).name("x4825").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:62:44") } // FixSub(x4817,x4824)
    val x4826 = withCtrl(x4827) { StoreBanks(List(List(x4736_d0_b0), List(x4736_d1_b0)), List(b2106), x4825).name("x4826").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:62:25") } // ParSRAMStore(x4736,List(List(b2106)),List(x4825),List(x4815))
    val x4828 = withCtrl(x4876) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x4828").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:61:21") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x4829 = withCtrl(x4876) { CounterChain(List(x4828)).name("x4829").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:61:29") } // CounterChainNew(List(x4828))
    val x4843 = withCtrl(x4876) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4829).name("x4843").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:61:29") } // UnrolledForeach(List(b2007, b1930),x4829,Block(Const(())),List(List(b2122)),List(List(b2123)))
    val b2122 = withCtrl(x4843) { CounterIter(x4828, None).name("b2122") } // b2122
    val b2123 = withCtrl(x4843) { Const(true).name("b2123") } // b2123
    val x4830 = withCtrl(x4843) { OpDef(op=BitAnd, inputs=List(b2123, b2007)).name("x4830").srcCtx("UnrollingBase.scala:28:66") } // And(b2123,b2007)
    val x4831 = withCtrl(x4843) { OpDef(op=BitAnd, inputs=List(x4830, b1930)).name("x4831").srcCtx("UnrollingBase.scala:28:66") } // And(x4830,b1930)
    val x4832 = withCtrl(x4843) { LoadBanks(List(x4679_d0_b5), List(b1999, b2122)).name("x4832").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:62:35") } // ParSRAMLoad(x4679,List(List(b1999, b2122)),List(x4831))
    val x4833 = withCtrl(x4843) { x4832 } // VectorApply(x4832,0)
    val x4834 = withCtrl(x4843) { LoadBanks(List(x4678_d0_b0), List(b1999)).name("x4834").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:62:58") } // SRAMLoad(x4678,ArrayBuffer(Const(512)),List(b1999),Const(0),x4831)
    val x4835 = withCtrl(x4843) { OpDef(op=FixEql, inputs=List(x4834, Const(1))).name("x4835").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:62:63") } // FixEql(x4834,Const(1))
    val x4836 = withCtrl(x4843) { LoadBanks(List(x4636_d5_b0), List(b2122)).name("x4836").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:62:76") } // ParSRAMLoad(x4636,List(List(b2122)),List(x4831))
    val x4837 = withCtrl(x4843) { x4836 } // VectorApply(x4836,0)
    val x4838 = withCtrl(x4843) { LoadBanks(List(x4635_d5_b0), List(b2122)).name("x4838").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:62:89") } // ParSRAMLoad(x4635,List(List(b2122)),List(x4831))
    val x4839 = withCtrl(x4843) { x4838 } // VectorApply(x4838,0)
    val x4840 = withCtrl(x4843) { OpDef(op=MuxOp, inputs=List(x4835, x4837, x4839)).name("x4840").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:62:49") } // Mux(x4835,x4837,x4839)
    val x4841 = withCtrl(x4843) { OpDef(op=FixSub, inputs=List(x4833, x4840)).name("x4841").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:62:44") } // FixSub(x4833,x4840)
    val x4842 = withCtrl(x4843) { StoreBanks(List(List(x4737_d0_b0), List(x4737_d1_b0)), List(b2122), x4841).name("x4842").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:62:25") } // ParSRAMStore(x4737,List(List(b2122)),List(x4841),List(x4831))
    val x4844 = withCtrl(x4876) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x4844").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:61:21") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x4845 = withCtrl(x4876) { CounterChain(List(x4844)).name("x4845").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:61:29") } // CounterChainNew(List(x4844))
    val x4859 = withCtrl(x4876) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4845).name("x4859").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:61:29") } // UnrolledForeach(List(b2008, b1930),x4845,Block(Const(())),List(List(b2138)),List(List(b2139)))
    val b2138 = withCtrl(x4859) { CounterIter(x4844, None).name("b2138") } // b2138
    val b2139 = withCtrl(x4859) { Const(true).name("b2139") } // b2139
    val x4846 = withCtrl(x4859) { OpDef(op=BitAnd, inputs=List(b2139, b2008)).name("x4846").srcCtx("UnrollingBase.scala:28:66") } // And(b2139,b2008)
    val x4847 = withCtrl(x4859) { OpDef(op=BitAnd, inputs=List(x4846, b1930)).name("x4847").srcCtx("UnrollingBase.scala:28:66") } // And(x4846,b1930)
    val x4848 = withCtrl(x4859) { LoadBanks(List(x4679_d0_b6), List(b2000, b2138)).name("x4848").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:62:35") } // ParSRAMLoad(x4679,List(List(b2000, b2138)),List(x4847))
    val x4849 = withCtrl(x4859) { x4848 } // VectorApply(x4848,0)
    val x4850 = withCtrl(x4859) { LoadBanks(List(x4678_d0_b0), List(b2000)).name("x4850").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:62:58") } // SRAMLoad(x4678,ArrayBuffer(Const(512)),List(b2000),Const(0),x4847)
    val x4851 = withCtrl(x4859) { OpDef(op=FixEql, inputs=List(x4850, Const(1))).name("x4851").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:62:63") } // FixEql(x4850,Const(1))
    val x4852 = withCtrl(x4859) { LoadBanks(List(x4636_d6_b0), List(b2138)).name("x4852").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:62:76") } // ParSRAMLoad(x4636,List(List(b2138)),List(x4847))
    val x4853 = withCtrl(x4859) { x4852 } // VectorApply(x4852,0)
    val x4854 = withCtrl(x4859) { LoadBanks(List(x4635_d6_b0), List(b2138)).name("x4854").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:62:89") } // ParSRAMLoad(x4635,List(List(b2138)),List(x4847))
    val x4855 = withCtrl(x4859) { x4854 } // VectorApply(x4854,0)
    def split1 = {
    val x4856 = withCtrl(x4859) { OpDef(op=MuxOp, inputs=List(x4851, x4853, x4855)).name("x4856").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:62:49") } // Mux(x4851,x4853,x4855)
    val x4857 = withCtrl(x4859) { OpDef(op=FixSub, inputs=List(x4849, x4856)).name("x4857").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:62:44") } // FixSub(x4849,x4856)
    val x4858 = withCtrl(x4859) { StoreBanks(List(List(x4738_d0_b0), List(x4738_d1_b0)), List(b2138), x4857).name("x4858").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:62:25") } // ParSRAMStore(x4738,List(List(b2138)),List(x4857),List(x4847))
    val x4860 = withCtrl(x4876) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x4860").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:61:21") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x4861 = withCtrl(x4876) { CounterChain(List(x4860)).name("x4861").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:61:29") } // CounterChainNew(List(x4860))
    val x4875 = withCtrl(x4876) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4861).name("x4875").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:61:29") } // UnrolledForeach(List(b2009, b1930),x4861,Block(Const(())),List(List(b2154)),List(List(b2155)))
    val b2154 = withCtrl(x4875) { CounterIter(x4860, None).name("b2154") } // b2154
    val b2155 = withCtrl(x4875) { Const(true).name("b2155") } // b2155
    val x4862 = withCtrl(x4875) { OpDef(op=BitAnd, inputs=List(b2155, b2009)).name("x4862").srcCtx("UnrollingBase.scala:28:66") } // And(b2155,b2009)
    val x4863 = withCtrl(x4875) { OpDef(op=BitAnd, inputs=List(x4862, b1930)).name("x4863").srcCtx("UnrollingBase.scala:28:66") } // And(x4862,b1930)
    val x4864 = withCtrl(x4875) { LoadBanks(List(x4679_d0_b7), List(b2001, b2154)).name("x4864").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:62:35") } // ParSRAMLoad(x4679,List(List(b2001, b2154)),List(x4863))
    val x4865 = withCtrl(x4875) { x4864 } // VectorApply(x4864,0)
    val x4866 = withCtrl(x4875) { LoadBanks(List(x4678_d0_b0), List(b2001)).name("x4866").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:62:58") } // SRAMLoad(x4678,ArrayBuffer(Const(512)),List(b2001),Const(0),x4863)
    val x4867 = withCtrl(x4875) { OpDef(op=FixEql, inputs=List(x4866, Const(1))).name("x4867").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:62:63") } // FixEql(x4866,Const(1))
    val x4868 = withCtrl(x4875) { LoadBanks(List(x4636_d7_b0), List(b2154)).name("x4868").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:62:76") } // ParSRAMLoad(x4636,List(List(b2154)),List(x4863))
    val x4869 = withCtrl(x4875) { x4868 } // VectorApply(x4868,0)
    val x4870 = withCtrl(x4875) { LoadBanks(List(x4635_d7_b0), List(b2154)).name("x4870").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:62:89") } // ParSRAMLoad(x4635,List(List(b2154)),List(x4863))
    val x4871 = withCtrl(x4875) { x4870 } // VectorApply(x4870,0)
    val x4872 = withCtrl(x4875) { OpDef(op=MuxOp, inputs=List(x4867, x4869, x4871)).name("x4872").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:62:49") } // Mux(x4867,x4869,x4871)
    val x4873 = withCtrl(x4875) { OpDef(op=FixSub, inputs=List(x4865, x4872)).name("x4873").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:62:44") } // FixSub(x4865,x4872)
    val x4874 = withCtrl(x4875) { StoreBanks(List(List(x4739_d0_b0), List(x4739_d1_b0)), List(b2154), x4873).name("x4874").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:62:25") } // ParSRAMStore(x4739,List(List(b2154)),List(x4873),List(x4863))
    val x4989 = withCtrl(x5055) { UnitController(style=ForkJoin, level=OuterControl).name("x4989").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b1930),Block(Const(())))
    val x4877 = withCtrl(x4989) { Counter(min=Const(0), max=Const(128), step=Const(1), par=1).name("x4877").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:64:26") } // CounterNew(Const(0),Const(128),Const(1),Const(1))
    val x4878 = withCtrl(x4989) { CounterChain(List(x4877)).name("x4878").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:64:35") } // CounterChainNew(List(x4877))
    val x4890 = withCtrl(x4989) { LoopController(style=MetaPipe, level=OuterControl, cchain=x4878).name("x4890").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:64:35") } // UnrolledForeach(List(b2002, b1930),x4878,Block(Const(())),List(List(b2187)),List(List(b2188)))
    val b2187 = withCtrl(x4890) { CounterIter(x4877, Some(0)).name("b2187") } // b2187
    val b2188 = withCtrl(x4890) { Const(true).name("b2188") } // b2188
    val x4879 = withCtrl(x4890) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x4879").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:65:23") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x4880 = withCtrl(x4890) { CounterChain(List(x4879)).name("x4880").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:65:31") } // CounterChainNew(List(x4879))
    val x4889 = withCtrl(x4890) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4880).name("x4889").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:65:31") } // UnrolledForeach(List(b2188, b2002, b1930),x4880,Block(Const(())),List(List(b2191)),List(List(b2192)))
    val b2191 = withCtrl(x4889) { CounterIter(x4879, None).name("b2191") } // b2191
    val b2192 = withCtrl(x4889) { Const(true).name("b2192") } // b2192
    val x4881 = withCtrl(x4889) { OpDef(op=BitAnd, inputs=List(b2192, b2188)).name("x4881").srcCtx("UnrollingBase.scala:28:66") } // And(b2192,b2188)
    val x4882 = withCtrl(x4889) { OpDef(op=BitAnd, inputs=List(b2002, b1930)).name("x4882").srcCtx("UnrollingBase.scala:28:66") } // And(b2002,b1930)
    val x4883 = withCtrl(x4889) { OpDef(op=BitAnd, inputs=List(x4881, x4882)).name("x4883").srcCtx("UnrollingBase.scala:28:66") } // And(x4881,x4882)
    val x4884 = withCtrl(x4889) { LoadBanks(List(x4732_d1_b0), List(b2187)).name("x4884").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:66:42") } // SRAMLoad(x4732,ArrayBuffer(Const(128)),List(b2187),Const(0),x4883)
    val x4885 = withCtrl(x4889) { LoadBanks(List(x4732_d0_b0), List(b2191)).name("x4885").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:66:56") } // ParSRAMLoad(x4732,List(List(b2191)),List(x4883))
    val x4886 = withCtrl(x4889) { x4885 } // VectorApply(x4885,0)
    val x4887 = withCtrl(x4889) { OpDef(op=FixMul, inputs=List(x4884, x4886)).name("x4887").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:66:47") } // FixMul(x4884,x4886)
    val x4888 = withCtrl(x4889) { StoreBanks(List(List(x4740_d0_b0)), List(b2187, b2191), x4887).name("x4888").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:66:33") } // ParSRAMStore(x4740,List(List(b2187, b2191)),List(x4887),List(x4883))
    val x4891 = withCtrl(x4989) { Counter(min=Const(0), max=Const(128), step=Const(1), par=1).name("x4891").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:64:26") } // CounterNew(Const(0),Const(128),Const(1),Const(1))
    val x4892 = withCtrl(x4989) { CounterChain(List(x4891)).name("x4892").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:64:35") } // CounterChainNew(List(x4891))
    val x4904 = withCtrl(x4989) { LoopController(style=MetaPipe, level=OuterControl, cchain=x4892).name("x4904").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:64:35") } // UnrolledForeach(List(b2003, b1930),x4892,Block(Const(())),List(List(b2203)),List(List(b2204)))
    val b2203 = withCtrl(x4904) { CounterIter(x4891, Some(0)).name("b2203") } // b2203
    val b2204 = withCtrl(x4904) { Const(true).name("b2204") } // b2204
    val x4893 = withCtrl(x4904) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x4893").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:65:23") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x4894 = withCtrl(x4904) { CounterChain(List(x4893)).name("x4894").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:65:31") } // CounterChainNew(List(x4893))
    val x4903 = withCtrl(x4904) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4894).name("x4903").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:65:31") } // UnrolledForeach(List(b2204, b2003, b1930),x4894,Block(Const(())),List(List(b2207)),List(List(b2208)))
    val b2207 = withCtrl(x4903) { CounterIter(x4893, None).name("b2207") } // b2207
    val b2208 = withCtrl(x4903) { Const(true).name("b2208") } // b2208
    val x4895 = withCtrl(x4903) { OpDef(op=BitAnd, inputs=List(b2208, b2204)).name("x4895").srcCtx("UnrollingBase.scala:28:66") } // And(b2208,b2204)
    val x4896 = withCtrl(x4903) { OpDef(op=BitAnd, inputs=List(b2003, b1930)).name("x4896").srcCtx("UnrollingBase.scala:28:66") } // And(b2003,b1930)
    val x4897 = withCtrl(x4903) { OpDef(op=BitAnd, inputs=List(x4895, x4896)).name("x4897").srcCtx("UnrollingBase.scala:28:66") } // And(x4895,x4896)
    val x4898 = withCtrl(x4903) { LoadBanks(List(x4733_d1_b0), List(b2203)).name("x4898").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:66:42") } // SRAMLoad(x4733,ArrayBuffer(Const(128)),List(b2203),Const(0),x4897)
    val x4899 = withCtrl(x4903) { LoadBanks(List(x4733_d0_b0), List(b2207)).name("x4899").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:66:56") } // ParSRAMLoad(x4733,List(List(b2207)),List(x4897))
    val x4900 = withCtrl(x4903) { x4899 } // VectorApply(x4899,0)
    val x4901 = withCtrl(x4903) { OpDef(op=FixMul, inputs=List(x4898, x4900)).name("x4901").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:66:47") } // FixMul(x4898,x4900)
    val x4902 = withCtrl(x4903) { StoreBanks(List(List(x4741_d0_b0)), List(b2203, b2207), x4901).name("x4902").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:66:33") } // ParSRAMStore(x4741,List(List(b2203, b2207)),List(x4901),List(x4897))
    val x4905 = withCtrl(x4989) { Counter(min=Const(0), max=Const(128), step=Const(1), par=1).name("x4905").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:64:26") } // CounterNew(Const(0),Const(128),Const(1),Const(1))
    val x4906 = withCtrl(x4989) { CounterChain(List(x4905)).name("x4906").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:64:35") } // CounterChainNew(List(x4905))
    val x4918 = withCtrl(x4989) { LoopController(style=MetaPipe, level=OuterControl, cchain=x4906).name("x4918").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:64:35") } // UnrolledForeach(List(b2004, b1930),x4906,Block(Const(())),List(List(b2219)),List(List(b2220)))
    val b2219 = withCtrl(x4918) { CounterIter(x4905, Some(0)).name("b2219") } // b2219
    val b2220 = withCtrl(x4918) { Const(true).name("b2220") } // b2220
    val x4907 = withCtrl(x4918) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x4907").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:65:23") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x4908 = withCtrl(x4918) { CounterChain(List(x4907)).name("x4908").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:65:31") } // CounterChainNew(List(x4907))
    val x4917 = withCtrl(x4918) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4908).name("x4917").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:65:31") } // UnrolledForeach(List(b2220, b2004, b1930),x4908,Block(Const(())),List(List(b2223)),List(List(b2224)))
    val b2223 = withCtrl(x4917) { CounterIter(x4907, None).name("b2223") } // b2223
    val b2224 = withCtrl(x4917) { Const(true).name("b2224") } // b2224
    val x4909 = withCtrl(x4917) { OpDef(op=BitAnd, inputs=List(b2224, b2220)).name("x4909").srcCtx("UnrollingBase.scala:28:66") } // And(b2224,b2220)
    val x4910 = withCtrl(x4917) { OpDef(op=BitAnd, inputs=List(b2004, b1930)).name("x4910").srcCtx("UnrollingBase.scala:28:66") } // And(b2004,b1930)
    val x4911 = withCtrl(x4917) { OpDef(op=BitAnd, inputs=List(x4909, x4910)).name("x4911").srcCtx("UnrollingBase.scala:28:66") } // And(x4909,x4910)
    val x4912 = withCtrl(x4917) { LoadBanks(List(x4734_d1_b0), List(b2219)).name("x4912").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:66:42") } // SRAMLoad(x4734,ArrayBuffer(Const(128)),List(b2219),Const(0),x4911)
    val x4913 = withCtrl(x4917) { LoadBanks(List(x4734_d0_b0), List(b2223)).name("x4913").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:66:56") } // ParSRAMLoad(x4734,List(List(b2223)),List(x4911))
    val x4914 = withCtrl(x4917) { x4913 } // VectorApply(x4913,0)
    val x4915 = withCtrl(x4917) { OpDef(op=FixMul, inputs=List(x4912, x4914)).name("x4915").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:66:47") } // FixMul(x4912,x4914)
    val x4916 = withCtrl(x4917) { StoreBanks(List(List(x4742_d0_b0)), List(b2219, b2223), x4915).name("x4916").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:66:33") } // ParSRAMStore(x4742,List(List(b2219, b2223)),List(x4915),List(x4911))
    val x4919 = withCtrl(x4989) { Counter(min=Const(0), max=Const(128), step=Const(1), par=1).name("x4919").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:64:26") } // CounterNew(Const(0),Const(128),Const(1),Const(1))
    val x4920 = withCtrl(x4989) { CounterChain(List(x4919)).name("x4920").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:64:35") } // CounterChainNew(List(x4919))
    val x4932 = withCtrl(x4989) { LoopController(style=MetaPipe, level=OuterControl, cchain=x4920).name("x4932").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:64:35") } // UnrolledForeach(List(b2005, b1930),x4920,Block(Const(())),List(List(b2235)),List(List(b2236)))
    val b2235 = withCtrl(x4932) { CounterIter(x4919, Some(0)).name("b2235") } // b2235
    val b2236 = withCtrl(x4932) { Const(true).name("b2236") } // b2236
    val x4921 = withCtrl(x4932) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x4921").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:65:23") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x4922 = withCtrl(x4932) { CounterChain(List(x4921)).name("x4922").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:65:31") } // CounterChainNew(List(x4921))
    val x4931 = withCtrl(x4932) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4922).name("x4931").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:65:31") } // UnrolledForeach(List(b2236, b2005, b1930),x4922,Block(Const(())),List(List(b2239)),List(List(b2240)))
    val b2239 = withCtrl(x4931) { CounterIter(x4921, None).name("b2239") } // b2239
    val b2240 = withCtrl(x4931) { Const(true).name("b2240") } // b2240
    val x4923 = withCtrl(x4931) { OpDef(op=BitAnd, inputs=List(b2240, b2236)).name("x4923").srcCtx("UnrollingBase.scala:28:66") } // And(b2240,b2236)
    val x4924 = withCtrl(x4931) { OpDef(op=BitAnd, inputs=List(b2005, b1930)).name("x4924").srcCtx("UnrollingBase.scala:28:66") } // And(b2005,b1930)
    val x4925 = withCtrl(x4931) { OpDef(op=BitAnd, inputs=List(x4923, x4924)).name("x4925").srcCtx("UnrollingBase.scala:28:66") } // And(x4923,x4924)
    val x4926 = withCtrl(x4931) { LoadBanks(List(x4735_d1_b0), List(b2235)).name("x4926").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:66:42") } // SRAMLoad(x4735,ArrayBuffer(Const(128)),List(b2235),Const(0),x4925)
    val x4927 = withCtrl(x4931) { LoadBanks(List(x4735_d0_b0), List(b2239)).name("x4927").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:66:56") } // ParSRAMLoad(x4735,List(List(b2239)),List(x4925))
    val x4928 = withCtrl(x4931) { x4927 } // VectorApply(x4927,0)
    val x4929 = withCtrl(x4931) { OpDef(op=FixMul, inputs=List(x4926, x4928)).name("x4929").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:66:47") } // FixMul(x4926,x4928)
    val x4930 = withCtrl(x4931) { StoreBanks(List(List(x4743_d0_b0)), List(b2235, b2239), x4929).name("x4930").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:66:33") } // ParSRAMStore(x4743,List(List(b2235, b2239)),List(x4929),List(x4925))
    val x4933 = withCtrl(x4989) { Counter(min=Const(0), max=Const(128), step=Const(1), par=1).name("x4933").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:64:26") } // CounterNew(Const(0),Const(128),Const(1),Const(1))
    val x4934 = withCtrl(x4989) { CounterChain(List(x4933)).name("x4934").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:64:35") } // CounterChainNew(List(x4933))
    val x4946 = withCtrl(x4989) { LoopController(style=MetaPipe, level=OuterControl, cchain=x4934).name("x4946").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:64:35") } // UnrolledForeach(List(b2006, b1930),x4934,Block(Const(())),List(List(b2251)),List(List(b2252)))
    val b2251 = withCtrl(x4946) { CounterIter(x4933, Some(0)).name("b2251") } // b2251
    val b2252 = withCtrl(x4946) { Const(true).name("b2252") } // b2252
    val x4935 = withCtrl(x4946) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x4935").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:65:23") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x4936 = withCtrl(x4946) { CounterChain(List(x4935)).name("x4936").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:65:31") } // CounterChainNew(List(x4935))
    val x4945 = withCtrl(x4946) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4936).name("x4945").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:65:31") } // UnrolledForeach(List(b2252, b2006, b1930),x4936,Block(Const(())),List(List(b2255)),List(List(b2256)))
    val b2255 = withCtrl(x4945) { CounterIter(x4935, None).name("b2255") } // b2255
    val b2256 = withCtrl(x4945) { Const(true).name("b2256") } // b2256
    val x4937 = withCtrl(x4945) { OpDef(op=BitAnd, inputs=List(b2256, b2252)).name("x4937").srcCtx("UnrollingBase.scala:28:66") } // And(b2256,b2252)
    val x4938 = withCtrl(x4945) { OpDef(op=BitAnd, inputs=List(b2006, b1930)).name("x4938").srcCtx("UnrollingBase.scala:28:66") } // And(b2006,b1930)
    val x4939 = withCtrl(x4945) { OpDef(op=BitAnd, inputs=List(x4937, x4938)).name("x4939").srcCtx("UnrollingBase.scala:28:66") } // And(x4937,x4938)
    val x4940 = withCtrl(x4945) { LoadBanks(List(x4736_d1_b0), List(b2251)).name("x4940").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:66:42") } // SRAMLoad(x4736,ArrayBuffer(Const(128)),List(b2251),Const(0),x4939)
    val x4941 = withCtrl(x4945) { LoadBanks(List(x4736_d0_b0), List(b2255)).name("x4941").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:66:56") } // ParSRAMLoad(x4736,List(List(b2255)),List(x4939))
    val x4942 = withCtrl(x4945) { x4941 } // VectorApply(x4941,0)
    val x4943 = withCtrl(x4945) { OpDef(op=FixMul, inputs=List(x4940, x4942)).name("x4943").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:66:47") } // FixMul(x4940,x4942)
    val x4944 = withCtrl(x4945) { StoreBanks(List(List(x4744_d0_b0)), List(b2251, b2255), x4943).name("x4944").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:66:33") } // ParSRAMStore(x4744,List(List(b2251, b2255)),List(x4943),List(x4939))
    val x4947 = withCtrl(x4989) { Counter(min=Const(0), max=Const(128), step=Const(1), par=1).name("x4947").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:64:26") } // CounterNew(Const(0),Const(128),Const(1),Const(1))
    val x4948 = withCtrl(x4989) { CounterChain(List(x4947)).name("x4948").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:64:35") } // CounterChainNew(List(x4947))
    val x4960 = withCtrl(x4989) { LoopController(style=MetaPipe, level=OuterControl, cchain=x4948).name("x4960").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:64:35") } // UnrolledForeach(List(b2007, b1930),x4948,Block(Const(())),List(List(b2267)),List(List(b2268)))
    val b2267 = withCtrl(x4960) { CounterIter(x4947, Some(0)).name("b2267") } // b2267
    val b2268 = withCtrl(x4960) { Const(true).name("b2268") } // b2268
    val x4949 = withCtrl(x4960) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x4949").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:65:23") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x4950 = withCtrl(x4960) { CounterChain(List(x4949)).name("x4950").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:65:31") } // CounterChainNew(List(x4949))
    val x4959 = withCtrl(x4960) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4950).name("x4959").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:65:31") } // UnrolledForeach(List(b2268, b2007, b1930),x4950,Block(Const(())),List(List(b2271)),List(List(b2272)))
    val b2271 = withCtrl(x4959) { CounterIter(x4949, None).name("b2271") } // b2271
    val b2272 = withCtrl(x4959) { Const(true).name("b2272") } // b2272
    val x4951 = withCtrl(x4959) { OpDef(op=BitAnd, inputs=List(b2272, b2268)).name("x4951").srcCtx("UnrollingBase.scala:28:66") } // And(b2272,b2268)
    val x4952 = withCtrl(x4959) { OpDef(op=BitAnd, inputs=List(b2007, b1930)).name("x4952").srcCtx("UnrollingBase.scala:28:66") } // And(b2007,b1930)
    val x4953 = withCtrl(x4959) { OpDef(op=BitAnd, inputs=List(x4951, x4952)).name("x4953").srcCtx("UnrollingBase.scala:28:66") } // And(x4951,x4952)
    val x4954 = withCtrl(x4959) { LoadBanks(List(x4737_d1_b0), List(b2267)).name("x4954").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:66:42") } // SRAMLoad(x4737,ArrayBuffer(Const(128)),List(b2267),Const(0),x4953)
    val x4955 = withCtrl(x4959) { LoadBanks(List(x4737_d0_b0), List(b2271)).name("x4955").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:66:56") } // ParSRAMLoad(x4737,List(List(b2271)),List(x4953))
    val x4956 = withCtrl(x4959) { x4955 } // VectorApply(x4955,0)
    val x4957 = withCtrl(x4959) { OpDef(op=FixMul, inputs=List(x4954, x4956)).name("x4957").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:66:47") } // FixMul(x4954,x4956)
    val x4958 = withCtrl(x4959) { StoreBanks(List(List(x4745_d0_b0)), List(b2267, b2271), x4957).name("x4958").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:66:33") } // ParSRAMStore(x4745,List(List(b2267, b2271)),List(x4957),List(x4953))
    val x4961 = withCtrl(x4989) { Counter(min=Const(0), max=Const(128), step=Const(1), par=1).name("x4961").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:64:26") } // CounterNew(Const(0),Const(128),Const(1),Const(1))
    val x4962 = withCtrl(x4989) { CounterChain(List(x4961)).name("x4962").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:64:35") } // CounterChainNew(List(x4961))
    val x4974 = withCtrl(x4989) { LoopController(style=MetaPipe, level=OuterControl, cchain=x4962).name("x4974").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:64:35") } // UnrolledForeach(List(b2008, b1930),x4962,Block(Const(())),List(List(b2283)),List(List(b2284)))
    val b2283 = withCtrl(x4974) { CounterIter(x4961, Some(0)).name("b2283") } // b2283
    val b2284 = withCtrl(x4974) { Const(true).name("b2284") } // b2284
    val x4963 = withCtrl(x4974) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x4963").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:65:23") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x4964 = withCtrl(x4974) { CounterChain(List(x4963)).name("x4964").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:65:31") } // CounterChainNew(List(x4963))
    val x4973 = withCtrl(x4974) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4964).name("x4973").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:65:31") } // UnrolledForeach(List(b2284, b2008, b1930),x4964,Block(Const(())),List(List(b2287)),List(List(b2288)))
    val b2287 = withCtrl(x4973) { CounterIter(x4963, None).name("b2287") } // b2287
    val b2288 = withCtrl(x4973) { Const(true).name("b2288") } // b2288
    val x4965 = withCtrl(x4973) { OpDef(op=BitAnd, inputs=List(b2288, b2284)).name("x4965").srcCtx("UnrollingBase.scala:28:66") } // And(b2288,b2284)
    val x4966 = withCtrl(x4973) { OpDef(op=BitAnd, inputs=List(b2008, b1930)).name("x4966").srcCtx("UnrollingBase.scala:28:66") } // And(b2008,b1930)
    val x4967 = withCtrl(x4973) { OpDef(op=BitAnd, inputs=List(x4965, x4966)).name("x4967").srcCtx("UnrollingBase.scala:28:66") } // And(x4965,x4966)
    val x4968 = withCtrl(x4973) { LoadBanks(List(x4738_d1_b0), List(b2283)).name("x4968").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:66:42") } // SRAMLoad(x4738,ArrayBuffer(Const(128)),List(b2283),Const(0),x4967)
    val x4969 = withCtrl(x4973) { LoadBanks(List(x4738_d0_b0), List(b2287)).name("x4969").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:66:56") } // ParSRAMLoad(x4738,List(List(b2287)),List(x4967))
    val x4970 = withCtrl(x4973) { x4969 } // VectorApply(x4969,0)
    val x4971 = withCtrl(x4973) { OpDef(op=FixMul, inputs=List(x4968, x4970)).name("x4971").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:66:47") } // FixMul(x4968,x4970)
    val x4972 = withCtrl(x4973) { StoreBanks(List(List(x4746_d0_b0)), List(b2283, b2287), x4971).name("x4972").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:66:33") } // ParSRAMStore(x4746,List(List(b2283, b2287)),List(x4971),List(x4967))
    val x4975 = withCtrl(x4989) { Counter(min=Const(0), max=Const(128), step=Const(1), par=1).name("x4975").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:64:26") } // CounterNew(Const(0),Const(128),Const(1),Const(1))
    val x4976 = withCtrl(x4989) { CounterChain(List(x4975)).name("x4976").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:64:35") } // CounterChainNew(List(x4975))
    val x4988 = withCtrl(x4989) { LoopController(style=MetaPipe, level=OuterControl, cchain=x4976).name("x4988").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:64:35") } // UnrolledForeach(List(b2009, b1930),x4976,Block(Const(())),List(List(b2299)),List(List(b2300)))
    val b2299 = withCtrl(x4988) { CounterIter(x4975, Some(0)).name("b2299") } // b2299
    val b2300 = withCtrl(x4988) { Const(true).name("b2300") } // b2300
    val x4977 = withCtrl(x4988) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x4977").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:65:23") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x4978 = withCtrl(x4988) { CounterChain(List(x4977)).name("x4978").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:65:31") } // CounterChainNew(List(x4977))
    val x4987 = withCtrl(x4988) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4978).name("x4987").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:65:31") } // UnrolledForeach(List(b2300, b2009, b1930),x4978,Block(Const(())),List(List(b2303)),List(List(b2304)))
    val b2303 = withCtrl(x4987) { CounterIter(x4977, None).name("b2303") } // b2303
    val b2304 = withCtrl(x4987) { Const(true).name("b2304") } // b2304
    val x4979 = withCtrl(x4987) { OpDef(op=BitAnd, inputs=List(b2304, b2300)).name("x4979").srcCtx("UnrollingBase.scala:28:66") } // And(b2304,b2300)
    val x4980 = withCtrl(x4987) { OpDef(op=BitAnd, inputs=List(b2009, b1930)).name("x4980").srcCtx("UnrollingBase.scala:28:66") } // And(b2009,b1930)
    val x4981 = withCtrl(x4987) { OpDef(op=BitAnd, inputs=List(x4979, x4980)).name("x4981").srcCtx("UnrollingBase.scala:28:66") } // And(x4979,x4980)
    val x4982 = withCtrl(x4987) { LoadBanks(List(x4739_d1_b0), List(b2299)).name("x4982").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:66:42") } // SRAMLoad(x4739,ArrayBuffer(Const(128)),List(b2299),Const(0),x4981)
    val x4983 = withCtrl(x4987) { LoadBanks(List(x4739_d0_b0), List(b2303)).name("x4983").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:66:56") } // ParSRAMLoad(x4739,List(List(b2303)),List(x4981))
    val x4984 = withCtrl(x4987) { x4983 } // VectorApply(x4983,0)
    val x4985 = withCtrl(x4987) { OpDef(op=FixMul, inputs=List(x4982, x4984)).name("x4985").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:66:47") } // FixMul(x4982,x4984)
    val x4986 = withCtrl(x4987) { StoreBanks(List(List(x4747_d0_b0)), List(b2299, b2303), x4985).name("x4986").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:66:33") } // ParSRAMStore(x4747,List(List(b2299, b2303)),List(x4985),List(x4981))
    val x4990 = withCtrl(x5055) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x4990").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:70:10") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x4991 = withCtrl(x5055) { Counter(min=Const(0), max=Const(128), step=Const(1), par=1).name("x4991").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:70:10") } // CounterNew(Const(0),Const(128),Const(1),Const(1))
    val x4992 = withCtrl(x5055) { CounterChain(List(x4991,x4990)).name("x4992").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:70:10") } // CounterChainNew(ArrayBuffer(x4991, x4990))
    val x5054 = withCtrl(x5055) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4992).name("x5054").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:70:10") } // UnrolledForeach(List(),x4992,Block(Const(())),ArrayBuffer(List(b2316), List(b2317)),ArrayBuffer(List(b2318), List(b2319)))
    val b2316 = withCtrl(x5054) { CounterIter(x4991, Some(0)).name("b2316") } // b2316
    val b2318 = withCtrl(x5054) { Const(true).name("b2318") } // b2318
    val b2317 = withCtrl(x5054) { CounterIter(x4990, None).name("b2317") } // b2317
    val b2319 = withCtrl(x5054) { Const(true).name("b2319") } // b2319
    val x4993 = withCtrl(x5054) { OpDef(op=BitAnd, inputs=List(b2318, b2319)).name("x4993").srcCtx("UnrollingBase.scala:28:66") } // And(b2318,b2319)
    val x4994 = withCtrl(x5054) { OpDef(op=BitAnd, inputs=List(x4993, b1930)).name("x4994").srcCtx("UnrollingBase.scala:28:66") } // And(x4993,b1930)
    val x4995 = withCtrl(x5054) { LoadBanks(List(x4740_d0_b0), List(b2316, b2317)).name("x4995").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:70:10") } // ParSRAMLoad(x4740,List(ArrayBuffer(b2316, b2317)),List(x4994))
    val x4996 = withCtrl(x5054) { x4995 } // VectorApply(x4995,0)
    val x4997 = withCtrl(x5054) { LoadBanks(List(x4741_d0_b0), List(b2316, b2317)).name("x4997").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:70:10") } // ParSRAMLoad(x4741,List(ArrayBuffer(b2316, b2317)),List(x4994))
    val x4998 = withCtrl(x5054) { x4997 } // VectorApply(x4997,0)
    val x4999 = withCtrl(x5054) { LoadBanks(List(x4742_d0_b0), List(b2316, b2317)).name("x4999").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:70:10") } // ParSRAMLoad(x4742,List(ArrayBuffer(b2316, b2317)),List(x4994))
    val x5000 = withCtrl(x5054) { x4999 } // VectorApply(x4999,0)
    val x5001 = withCtrl(x5054) { LoadBanks(List(x4743_d0_b0), List(b2316, b2317)).name("x5001").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:70:10") } // ParSRAMLoad(x4743,List(ArrayBuffer(b2316, b2317)),List(x4994))
    val x5002 = withCtrl(x5054) { x5001 } // VectorApply(x5001,0)
    val x5003 = withCtrl(x5054) { LoadBanks(List(x4744_d0_b0), List(b2316, b2317)).name("x5003").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:70:10") } // ParSRAMLoad(x4744,List(ArrayBuffer(b2316, b2317)),List(x4994))
    val x5004 = withCtrl(x5054) { x5003 } // VectorApply(x5003,0)
    val x5005 = withCtrl(x5054) { LoadBanks(List(x4745_d0_b0), List(b2316, b2317)).name("x5005").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:70:10") } // ParSRAMLoad(x4745,List(ArrayBuffer(b2316, b2317)),List(x4994))
    val x5006 = withCtrl(x5054) { x5005 } // VectorApply(x5005,0)
    val x5007 = withCtrl(x5054) { LoadBanks(List(x4746_d0_b0), List(b2316, b2317)).name("x5007").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:70:10") } // ParSRAMLoad(x4746,List(ArrayBuffer(b2316, b2317)),List(x4994))
    val x5008 = withCtrl(x5054) { x5007 } // VectorApply(x5007,0)
    val x5009 = withCtrl(x5054) { LoadBanks(List(x4747_d0_b0), List(b2316, b2317)).name("x5009").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:70:10") } // ParSRAMLoad(x4747,List(ArrayBuffer(b2316, b2317)),List(x4994))
    val x5010 = withCtrl(x5054) { x5009 } // VectorApply(x5009,0)
    val x5011 = withCtrl(x5054) { LoadBanks(List(x4729_d1_b0), List(b2316, b2317)).name("x5011").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:70:10") } // ParSRAMLoad(x4729,List(ArrayBuffer(b2316, b2317)),List(x4994))
    val x5012 = withCtrl(x5054) { x5011 } // VectorApply(x5011,0)
    val x5013 = withCtrl(x5054) { OpDef(op=BitAnd, inputs=List(b2002, b1930)).name("x5013").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:70:10") } // And(b2002,b1930)
    val x5014 = withCtrl(x5054) { OpDef(op=BitAnd, inputs=List(b2003, b1930)).name("x5014").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:70:10") } // And(b2003,b1930)
    val x5015 = withCtrl(x5054) { OpDef(op=BitAnd, inputs=List(b2004, b1930)).name("x5015").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:70:10") } // And(b2004,b1930)
    val x5016 = withCtrl(x5054) { OpDef(op=BitAnd, inputs=List(b2005, b1930)).name("x5016").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:70:10") } // And(b2005,b1930)
    val x5017 = withCtrl(x5054) { OpDef(op=BitAnd, inputs=List(b2006, b1930)).name("x5017").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:70:10") } // And(b2006,b1930)
    val x5018 = withCtrl(x5054) { OpDef(op=BitAnd, inputs=List(b2007, b1930)).name("x5018").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:70:10") } // And(b2007,b1930)
    val x5019 = withCtrl(x5054) { OpDef(op=BitAnd, inputs=List(b2008, b1930)).name("x5019").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:70:10") } // And(b2008,b1930)
    val x5020 = withCtrl(x5054) { OpDef(op=BitAnd, inputs=List(b2009, b1930)).name("x5020").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:70:10") } // And(b2009,b1930)
    val x5021 = withCtrl(x5054) { OpDef(op=BitAnd, inputs=List(x5013, x4994)).name("x5021").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:70:10") } // And(x5013,x4994)
    val x5022 = withCtrl(x5054) { OpDef(op=BitAnd, inputs=List(x5014, x4994)).name("x5022").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:70:10") } // And(x5014,x4994)
    val x5023 = withCtrl(x5054) { OpDef(op=BitAnd, inputs=List(x5015, x4994)).name("x5023").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:70:10") } // And(x5015,x4994)
    val x5024 = withCtrl(x5054) { OpDef(op=BitAnd, inputs=List(x5016, x4994)).name("x5024").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:70:10") } // And(x5016,x4994)
    val x5025 = withCtrl(x5054) { OpDef(op=BitAnd, inputs=List(x5017, x4994)).name("x5025").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:70:10") } // And(x5017,x4994)
    val x5026 = withCtrl(x5054) { OpDef(op=BitAnd, inputs=List(x5018, x4994)).name("x5026").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:70:10") } // And(x5018,x4994)
    val x5027 = withCtrl(x5054) { OpDef(op=BitAnd, inputs=List(x5019, x4994)).name("x5027").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:70:10") } // And(x5019,x4994)
    val x5028 = withCtrl(x5054) { OpDef(op=BitAnd, inputs=List(x5020, x4994)).name("x5028").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:70:10") } // And(x5020,x4994)
    val x5029 = withCtrl(x5054) { OpDef(op=FixAdd, inputs=List(x4996, x4998)).name("x5029").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:70:12") } // FixAdd(x4996,x4998)
    val x5030 = withCtrl(x5054) { OpDef(op=MuxOp, inputs=List(x5022, x5029, x4996)).name("x5030").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:70:10") } // Mux(x5022,x5029,x4996)
    val x5031 = withCtrl(x5054) { OpDef(op=BitOr, inputs=List(x5021, x5022)).name("x5031").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:70:10") } // Or(x5021,x5022)
    val x5032 = withCtrl(x5054) { OpDef(op=FixAdd, inputs=List(x5000, x5002)).name("x5032").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:70:12") } // FixAdd(x5000,x5002)
    val x5033 = withCtrl(x5054) { OpDef(op=MuxOp, inputs=List(x5024, x5032, x5000)).name("x5033").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:70:10") } // Mux(x5024,x5032,x5000)
    val x5034 = withCtrl(x5054) { OpDef(op=BitOr, inputs=List(x5023, x5024)).name("x5034").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:70:10") } // Or(x5023,x5024)
    val x5035 = withCtrl(x5054) { OpDef(op=FixAdd, inputs=List(x5004, x5006)).name("x5035").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:70:12") } // FixAdd(x5004,x5006)
    val x5036 = withCtrl(x5054) { OpDef(op=MuxOp, inputs=List(x5026, x5035, x5004)).name("x5036").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:70:10") } // Mux(x5026,x5035,x5004)
    val x5037 = withCtrl(x5054) { OpDef(op=BitOr, inputs=List(x5025, x5026)).name("x5037").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:70:10") } // Or(x5025,x5026)
    val x5038 = withCtrl(x5054) { OpDef(op=FixAdd, inputs=List(x5008, x5010)).name("x5038").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:70:12") } // FixAdd(x5008,x5010)
    val x5039 = withCtrl(x5054) { OpDef(op=MuxOp, inputs=List(x5028, x5038, x5008)).name("x5039").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:70:10") } // Mux(x5028,x5038,x5008)
    val x5040 = withCtrl(x5054) { OpDef(op=BitOr, inputs=List(x5027, x5028)).name("x5040").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:70:10") } // Or(x5027,x5028)
    val x5041 = withCtrl(x5054) { OpDef(op=FixAdd, inputs=List(x5030, x5033)).name("x5041").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:70:12") } // FixAdd(x5030,x5033)
    val x5042 = withCtrl(x5054) { OpDef(op=MuxOp, inputs=List(x5034, x5041, x5030)).name("x5042").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:70:10") } // Mux(x5034,x5041,x5030)
    val x5043 = withCtrl(x5054) { OpDef(op=BitOr, inputs=List(x5031, x5034)).name("x5043").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:70:10") } // Or(x5031,x5034)
    val x5044 = withCtrl(x5054) { OpDef(op=FixAdd, inputs=List(x5036, x5039)).name("x5044").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:70:12") } // FixAdd(x5036,x5039)
    val x5045 = withCtrl(x5054) { OpDef(op=MuxOp, inputs=List(x5040, x5044, x5036)).name("x5045").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:70:10") } // Mux(x5040,x5044,x5036)
    val x5046 = withCtrl(x5054) { OpDef(op=BitOr, inputs=List(x5037, x5040)).name("x5046").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:70:10") } // Or(x5037,x5040)
    val x5047 = withCtrl(x5054) { OpDef(op=FixAdd, inputs=List(x5042, x5045)).name("x5047").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:70:12") } // FixAdd(x5042,x5045)
    val x5048 = withCtrl(x5054) { OpDef(op=MuxOp, inputs=List(x5046, x5047, x5042)).name("x5048").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:70:10") } // Mux(x5046,x5047,x5042)
    val x5049 = withCtrl(x5054) { OpDef(op=BitOr, inputs=List(x5043, x5046)).name("x5049").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:70:10") } // Or(x5043,x5046)
    val x5050 = withCtrl(x5054) { OpDef(op=FixEql, inputs=List(b1994, Const(0))).name("x5050").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:70:10") } // FixEql(b1994,Const(0))
    val x5051 = withCtrl(x5054) { OpDef(op=FixAdd, inputs=List(x5048, x5012)).name("x5051").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:70:12") } // FixAdd(x5048,x5012)
    val x5052 = withCtrl(x5054) { OpDef(op=MuxOp, inputs=List(x5050, x5048, x5051)).name("x5052").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:70:10") } // Mux(x5050,x5048,x5051)
    val x5053 = withCtrl(x5054) { StoreBanks(List(List(x4729_d0_b0), List(x4729_d1_b0)), List(b2316, b2317), x5052).name("x5053").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:70:10") } // ParSRAMStore(x4729,List(ArrayBuffer(b2316, b2317)),List(x5052),List(x4994))
    antiDepsOf(x5053)=List(x5011)
    val x5056 = withCtrl(x5070) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x5056").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:71:8") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x5057 = withCtrl(x5070) { Counter(min=Const(0), max=Const(128), step=Const(1), par=1).name("x5057").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:71:8") } // CounterNew(Const(0),Const(128),Const(1),Const(1))
    val x5058 = withCtrl(x5070) { CounterChain(List(x5057,x5056)).name("x5058").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:71:8") } // CounterChainNew(ArrayBuffer(x5057, x5056))
    val x5069 = withCtrl(x5070) { LoopController(style=InnerPipe, level=InnerControl, cchain=x5058).name("x5069").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:71:8") } // UnrolledForeach(List(),x5058,Block(Const(())),ArrayBuffer(List(b2383), List(b2384)),ArrayBuffer(List(b2385), List(b2386)))
    val b2383 = withCtrl(x5069) { CounterIter(x5057, Some(0)).name("b2383") } // b2383
    val b2385 = withCtrl(x5069) { Const(true).name("b2385") } // b2385
    val b2384 = withCtrl(x5069) { CounterIter(x5056, None).name("b2384") } // b2384
    val b2386 = withCtrl(x5069) { Const(true).name("b2386") } // b2386
    val x5059 = withCtrl(x5069) { OpDef(op=BitAnd, inputs=List(b2385, b2386)).name("x5059").srcCtx("UnrollingBase.scala:28:66") } // And(b2385,b2386)
    val x5060 = withCtrl(x5069) { LoadBanks(List(x4729_d0_b0), List(b2383, b2384)).name("x5060").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:71:8") } // ParSRAMLoad(x4729,List(ArrayBuffer(b2383, b2384)),List(x5059))
    val x5061 = withCtrl(x5069) { x5060 } // VectorApply(x5060,0)
    val x5062 = withCtrl(x5069) { LoadBanks(List(x4674_d1_b0), List(b2383, b2384)).name("x5062").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:71:8") } // ParSRAMLoad(x4674,List(ArrayBuffer(b2383, b2384)),List(x5059))
    val x5063 = withCtrl(x5069) { x5062 } // VectorApply(x5062,0)
    val x5064 = withCtrl(x5069) { OpDef(op=BitAnd, inputs=List(b1930, x5059)).name("x5064").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:71:8") } // And(b1930,x5059)
    val x5065 = withCtrl(x5069) { OpDef(op=FixEql, inputs=List(b1929, Const(0))).name("x5065").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:71:8") } // FixEql(b1929,Const(0))
    val x5066 = withCtrl(x5069) { OpDef(op=FixAdd, inputs=List(x5061, x5063)).name("x5066").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:71:10") } // FixAdd(x5061,x5063)
    val x5067 = withCtrl(x5069) { OpDef(op=MuxOp, inputs=List(x5065, x5061, x5066)).name("x5067").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:71:8") } // Mux(x5065,x5061,x5066)
    val x5068 = withCtrl(x5069) { StoreBanks(List(List(x4674_d0_b0), List(x4674_d1_b0)), List(b2383, b2384), x5067).name("x5068").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:71:8") } // ParSRAMStore(x4674,List(ArrayBuffer(b2383, b2384)),List(x5067),List(x5059))
    antiDepsOf(x5068)=List(x5062)
    val x5071 = withCtrl(x5099) { Counter(min=Const(0), max=Const(128), step=Const(1), par=1).name("x5071").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:73:36") } // CounterNew(Const(0),Const(128),Const(1),Const(1))
    val x5072 = withCtrl(x5099) { CounterChain(List(x5071)).name("x5072").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:73:36") } // CounterChainNew(List(x5071))
    val x5098 = withCtrl(x5099) { LoopController(style=StreamPipe, level=OuterControl, cchain=x5072).name("x5098").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:73:36") } // UnrolledForeach(List(Const(true)),x5072,Block(Const(())),List(List(b2401)),List(List(b2402)))
    val b2401 = withCtrl(x5098) { CounterIter(x5071, Some(0)).name("b2401") } // b2401
    val b2402 = withCtrl(x5098) { Const(true).name("b2402") } // b2402
    val b5167 = withCtrl(x5098) { StreamOut(field="offset").name("b5167").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:73:36") } // x5073 = StreamOutNew(BurstCmdBus)
    isAccum(b5167) = false
    bufferDepthOf(b5167) = 1
    val b5168 = withCtrl(x5098) { StreamOut(field="size").name("b5168").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:73:36") } // x5073 = StreamOutNew(BurstCmdBus)
    isAccum(b5168) = false
    bufferDepthOf(b5168) = 1
    val x5074 = withCtrl(x5098) { StreamOut(field="data").name("x5074").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:73:36") } // x5074 = StreamOutNew(BurstFullDataBus())
    isAccum(x5074) = false
    bufferDepthOf(x5074) = 1
    val x5075 = withCtrl(x5098) { StreamIn(field="ack").name("x5075").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:73:36") } // x5075 = StreamInNew(BurstAckBus)
    isAccum(x5075) = false
    bufferDepthOf(x5075) = 1
    val x5086 = withCtrl(x5098) { UnitController(style=SeqPipe, level=InnerControl).name("x5086").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:73:36") } // UnitPipe(List(b2402),Block(x5085))
    val x5076 = withCtrl(x5086) { b2401 } // FixConvert(b2401,TRUE,_32,_0) (Same Type. No op)
    val x5077 = withCtrl(x5086) { OpDef(op=FixSla, inputs=List(x5076, Const(7))).name("x5077").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:73:36") } // FixLsh(x5076,Const(7))
    val x5078 = withCtrl(x5086) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x5079 = withCtrl(x5086) { OpDef(op=FixAdd, inputs=List(x5077, x5078)).name("x5079").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:73:36") } // FixAdd(x5077,x5078)
    val x5080 = withCtrl(x5086) { OpDef(op=FixSla, inputs=List(x5079, Const(2))).name("x5080").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:73:36") } // FixLsh(x5079,Const(2))
    val x5081 = withCtrl(x5086) { x5080 } // FixConvert(x5080,TRUE,_64,_0)
    val x5082 = withCtrl(x5086) { DramAddress(x4630).name("x5082").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:73:36") } // GetDRAMAddress(x4630)
    val x5084_x5083 = withCtrl(x5086) { OpDef(op=FixAdd, inputs=List(x5081, x5082)).name("x5084_x5083").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:73:36") } // FixAdd(x5081,x5082)
    // x5084 = SimpleStruct(ArrayBuffer((offset,x5083), (size,Const(512)), (isLoad,Const(false))))
    val x5085_b5169_b5167 = withCtrl(x5086) { WriteMem(b5167, x5084_x5083).name("x5085_b5169_b5167").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:73:36") } // StreamWrite(x5073,x5084,b2402)
    val x5085_b5170_b5168 = withCtrl(x5086) { WriteMem(b5168, Const(512)).name("x5085_b5170_b5168").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:73:36") } // StreamWrite(x5073,x5084,b2402)
    val x5087 = withCtrl(x5098) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x5087").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:73:36") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x5088 = withCtrl(x5098) { CounterChain(List(x5087)).name("x5088").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:73:36") } // CounterChainNew(List(x5087))
    val x5094 = withCtrl(x5098) { LoopController(style=InnerPipe, level=InnerControl, cchain=x5088).name("x5094").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:73:36") } // UnrolledForeach(List(b2402),x5088,Block(Const(())),List(List(b2419)),List(List(b2420)))
    val b2419 = withCtrl(x5094) { CounterIter(x5087, None).name("b2419") } // b2419
    val b2420 = withCtrl(x5094) { Const(true).name("b2420") } // b2420
    val x5089 = withCtrl(x5094) { OpDef(op=BitAnd, inputs=List(b2420, b2402)).name("x5089").srcCtx("UnrollingBase.scala:28:66") } // And(b2420,b2402)
    val x5090 = withCtrl(x5094) { LoadBanks(List(x4674_d0_b0), List(b2401, b2419)).name("x5090").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:73:36") } // ParSRAMLoad(x4674,List(List(b2401, b2419)),List(x5089))
    val x5092_x5091 = withCtrl(x5094) { x5090 } // VectorApply(x5090,0)
    // x5092 = SimpleStruct(ArrayBuffer((_1,x5091), (_2,Const(true))))
    val x5093_x5093_x5074 = withCtrl(x5094) { WriteMem(x5074, x5092_x5091).name("x5093_x5093_x5074").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:73:36") } // ParStreamWrite(x5074,List(x5092),List(x5089))
    val x5095 = withCtrl(x5098) { FringeDenseStore(dram=List(x4630), cmdStream=List(b5167, b5168), dataStream=List(x5074), ackStream=List(x5075)).name("x5095").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:73:36") } // FringeDenseStore(x4630,x5073,x5074,x5075)
    val x5097 = withCtrl(x5098) { UnitController(style=SeqPipe, level=InnerControl).name("x5097").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:73:36") } // UnitPipe(List(b2402),Block(Const(())))
    val x5096_x5096 = withCtrl(x5097) { ReadMem(x5075).name("x5096_x5096").srcCtx("GDA__C_128_R_4096_ts_512_op_1_mp1_8_mp2_1.scala:73:36") } // StreamRead(x5075,b2402)
    }; split1
    
  }
}
