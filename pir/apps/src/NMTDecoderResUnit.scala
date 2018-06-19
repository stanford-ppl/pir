import pir._
import pir.node._
import arch._
import prism.enums._

object NMTDecoderResUnit extends PIRApp {
  def main(implicit design:PIRDesign) = {
    import design.pirmeta._
    val x34364 = DRAM().name("x34364").ctrl(top).srcCtx("NMTDSE.scala:455:24:xDRAM") // x34364 = DRAMNew(ArrayBuffer(Const(1), Const(4), Const(512)),Const(0))
    val x34372 = DRAM().name("x34372").ctrl(top).srcCtx("CellsPar.scala:80:24") // x34372 = DRAMNew(ArrayBuffer(Const(1), Const(512)),Const(0))
    val x34373 = DRAM().name("x34373").ctrl(top).srcCtx("CellsPar.scala:81:24") // x34373 = DRAMNew(ArrayBuffer(Const(1), Const(2048)),Const(0))
    val x34374 = DRAM().name("x34374").ctrl(top).srcCtx("CellsPar.scala:82:24") // x34374 = DRAMNew(ArrayBuffer(Const(1), Const(512)),Const(0))
    val x34400 = DRAM().name("x34400").ctrl(top).srcCtx("CellsPar.scala:90:21:kernel") // x34400 = DRAMNew(ArrayBuffer(Const(1536), Const(512)),Const(0))
    val x34401 = DRAM().name("x34401").ctrl(top).srcCtx("CellsPar.scala:91:21:kernel") // x34401 = DRAMNew(ArrayBuffer(Const(1536), Const(512)),Const(0))
    val x34402 = DRAM().name("x34402").ctrl(top).srcCtx("CellsPar.scala:92:21:kernel") // x34402 = DRAMNew(ArrayBuffer(Const(1536), Const(512)),Const(0))
    val x34403 = DRAM().name("x34403").ctrl(top).srcCtx("CellsPar.scala:93:21:kernel") // x34403 = DRAMNew(ArrayBuffer(Const(1536), Const(512)),Const(0))
    val x34404 = DRAM().name("x34404").ctrl(top).srcCtx("CellsPar.scala:94:25") // x34404 = DRAMNew(ArrayBuffer(Const(1536), Const(2048)),Const(0))
    val x34448 = DRAM().name("x34448").ctrl(top).srcCtx("CellsPar.scala:80:24") // x34448 = DRAMNew(ArrayBuffer(Const(1), Const(512)),Const(0))
    val x34449 = DRAM().name("x34449").ctrl(top).srcCtx("CellsPar.scala:81:24") // x34449 = DRAMNew(ArrayBuffer(Const(1), Const(2048)),Const(0))
    val x34450 = DRAM().name("x34450").ctrl(top).srcCtx("CellsPar.scala:82:24") // x34450 = DRAMNew(ArrayBuffer(Const(1), Const(512)),Const(0))
    val x34476 = DRAM().name("x34476").ctrl(top).srcCtx("CellsPar.scala:90:21:kernel") // x34476 = DRAMNew(ArrayBuffer(Const(1536), Const(512)),Const(0))
    val x34477 = DRAM().name("x34477").ctrl(top).srcCtx("CellsPar.scala:91:21:kernel") // x34477 = DRAMNew(ArrayBuffer(Const(1536), Const(512)),Const(0))
    val x34478 = DRAM().name("x34478").ctrl(top).srcCtx("CellsPar.scala:92:21:kernel") // x34478 = DRAMNew(ArrayBuffer(Const(1536), Const(512)),Const(0))
    val x34479 = DRAM().name("x34479").ctrl(top).srcCtx("CellsPar.scala:93:21:kernel") // x34479 = DRAMNew(ArrayBuffer(Const(1536), Const(512)),Const(0))
    val x34480 = DRAM().name("x34480").ctrl(top).srcCtx("CellsPar.scala:94:25") // x34480 = DRAMNew(ArrayBuffer(Const(1536), Const(2048)),Const(0))
    val x34524 = DRAM().name("x34524").ctrl(top).srcCtx("CellsPar.scala:80:24") // x34524 = DRAMNew(ArrayBuffer(Const(1), Const(512)),Const(0))
    val x34525 = DRAM().name("x34525").ctrl(top).srcCtx("CellsPar.scala:81:24") // x34525 = DRAMNew(ArrayBuffer(Const(1), Const(2048)),Const(0))
    val x34526 = DRAM().name("x34526").ctrl(top).srcCtx("CellsPar.scala:82:24") // x34526 = DRAMNew(ArrayBuffer(Const(1), Const(512)),Const(0))
    val x34552 = DRAM().name("x34552").ctrl(top).srcCtx("CellsPar.scala:90:21:kernel") // x34552 = DRAMNew(ArrayBuffer(Const(1536), Const(512)),Const(0))
    val x34553 = DRAM().name("x34553").ctrl(top).srcCtx("CellsPar.scala:91:21:kernel") // x34553 = DRAMNew(ArrayBuffer(Const(1536), Const(512)),Const(0))
    val x34554 = DRAM().name("x34554").ctrl(top).srcCtx("CellsPar.scala:92:21:kernel") // x34554 = DRAMNew(ArrayBuffer(Const(1536), Const(512)),Const(0))
    val x34555 = DRAM().name("x34555").ctrl(top).srcCtx("CellsPar.scala:93:21:kernel") // x34555 = DRAMNew(ArrayBuffer(Const(1536), Const(512)),Const(0))
    val x34556 = DRAM().name("x34556").ctrl(top).srcCtx("CellsPar.scala:94:25") // x34556 = DRAMNew(ArrayBuffer(Const(1536), Const(2048)),Const(0))
    val x34600 = DRAM().name("x34600").ctrl(top).srcCtx("CellsPar.scala:80:24") // x34600 = DRAMNew(ArrayBuffer(Const(1), Const(512)),Const(0))
    val x34601 = DRAM().name("x34601").ctrl(top).srcCtx("CellsPar.scala:81:24") // x34601 = DRAMNew(ArrayBuffer(Const(1), Const(2048)),Const(0))
    val x34602 = DRAM().name("x34602").ctrl(top).srcCtx("CellsPar.scala:82:24:output_hidden") // x34602 = DRAMNew(ArrayBuffer(Const(1), Const(512)),Const(0))
    val x34628 = DRAM().name("x34628").ctrl(top).srcCtx("CellsPar.scala:90:21:kernel") // x34628 = DRAMNew(ArrayBuffer(Const(1536), Const(512)),Const(0))
    val x34629 = DRAM().name("x34629").ctrl(top).srcCtx("CellsPar.scala:91:21:kernel") // x34629 = DRAMNew(ArrayBuffer(Const(1536), Const(512)),Const(0))
    val x34630 = DRAM().name("x34630").ctrl(top).srcCtx("CellsPar.scala:92:21:kernel") // x34630 = DRAMNew(ArrayBuffer(Const(1536), Const(512)),Const(0))
    val x34631 = DRAM().name("x34631").ctrl(top).srcCtx("CellsPar.scala:93:21:kernel") // x34631 = DRAMNew(ArrayBuffer(Const(1536), Const(512)),Const(0))
    val x34632 = DRAM().name("x34632").ctrl(top).srcCtx("CellsPar.scala:94:25") // x34632 = DRAMNew(ArrayBuffer(Const(1536), Const(2048)),Const(0))
    val x36799 = UnitController(style=SeqPipe, level=OuterControl).name("x36799").ctrl(top).srcCtx("NMTDSE.scala:464:11") // Hwblock(Block(Const(())),false)
    val x34676_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34676_d0_b0").ctrl(x36799).srcCtx("NMTDSE.scala:465:66") // x34676 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34676_d0_b0) = false
    bufferDepthOf(x34676_d0_b0) = 1
    val x34676_d1_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34676_d1_b0").ctrl(x36799).srcCtx("NMTDSE.scala:465:66") // x34676 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34676_d1_b0) = false
    bufferDepthOf(x34676_d1_b0) = 1
    val x34676_d2_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34676_d2_b0").ctrl(x36799).srcCtx("NMTDSE.scala:465:66") // x34676 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34676_d2_b0) = false
    bufferDepthOf(x34676_d2_b0) = 1
    val x34676_d3_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34676_d3_b0").ctrl(x36799).srcCtx("NMTDSE.scala:465:66") // x34676 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34676_d3_b0) = false
    bufferDepthOf(x34676_d3_b0) = 1
    val x34677_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34677_d0_b0").ctrl(x36799).srcCtx("CellsPar.scala:139:20") // x34677 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34677_d0_b0) = false
    bufferDepthOf(x34677_d0_b0) = 1
    val x34677_d1_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34677_d1_b0").ctrl(x36799).srcCtx("CellsPar.scala:139:20") // x34677 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34677_d1_b0) = false
    bufferDepthOf(x34677_d1_b0) = 1
    val x34677_d2_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34677_d2_b0").ctrl(x36799).srcCtx("CellsPar.scala:139:20") // x34677 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34677_d2_b0) = false
    bufferDepthOf(x34677_d2_b0) = 1
    val x34677_d3_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34677_d3_b0").ctrl(x36799).srcCtx("CellsPar.scala:139:20") // x34677 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34677_d3_b0) = false
    bufferDepthOf(x34677_d3_b0) = 1
    val x34677_d4_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34677_d4_b0").ctrl(x36799).srcCtx("CellsPar.scala:139:20") // x34677 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34677_d4_b0) = false
    bufferDepthOf(x34677_d4_b0) = 1
    val x34677_d5_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34677_d5_b0").ctrl(x36799).srcCtx("CellsPar.scala:139:20") // x34677 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34677_d5_b0) = false
    bufferDepthOf(x34677_d5_b0) = 1
    val x34677_d6_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34677_d6_b0").ctrl(x36799).srcCtx("CellsPar.scala:139:20") // x34677 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34677_d6_b0) = false
    bufferDepthOf(x34677_d6_b0) = 1
    val x34677_d7_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34677_d7_b0").ctrl(x36799).srcCtx("CellsPar.scala:139:20") // x34677 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34677_d7_b0) = false
    bufferDepthOf(x34677_d7_b0) = 1
    val x34678_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34678_d0_b0").ctrl(x36799).srcCtx("CellsPar.scala:140:20") // x34678 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34678_d0_b0) = true
    bufferDepthOf(x34678_d0_b0) = 1
    val x34679_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34679_d0_b0").ctrl(x36799).srcCtx("CellsPar.scala:141:45:targetSRAM") // x34679 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34679_d0_b0) = false
    bufferDepthOf(x34679_d0_b0) = 1
    val x34679_d1_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34679_d1_b0").ctrl(x36799).srcCtx("CellsPar.scala:141:45:targetSRAM") // x34679 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34679_d1_b0) = true
    bufferDepthOf(x34679_d1_b0) = 1
    val x34680_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34680_d0_b0").ctrl(x36799).srcCtx("CellsPar.scala:141:79:targetSRAM") // x34680 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34680_d0_b0) = false
    bufferDepthOf(x34680_d0_b0) = 1
    val x34680_d1_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34680_d1_b0").ctrl(x36799).srcCtx("CellsPar.scala:141:79:targetSRAM") // x34680 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34680_d1_b0) = true
    bufferDepthOf(x34680_d1_b0) = 1
    val x34681_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34681_d0_b0").ctrl(x36799).srcCtx("CellsPar.scala:142:44:targetSRAM") // x34681 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34681_d0_b0) = false
    bufferDepthOf(x34681_d0_b0) = 1
    val x34681_d1_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34681_d1_b0").ctrl(x36799).srcCtx("CellsPar.scala:142:44:targetSRAM") // x34681 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34681_d1_b0) = true
    bufferDepthOf(x34681_d1_b0) = 1
    val x34682_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34682_d0_b0").ctrl(x36799).srcCtx("CellsPar.scala:142:78:targetSRAM") // x34682 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34682_d0_b0) = false
    bufferDepthOf(x34682_d0_b0) = 1
    val x34682_d1_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34682_d1_b0").ctrl(x36799).srcCtx("CellsPar.scala:142:78:targetSRAM") // x34682 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34682_d1_b0) = true
    bufferDepthOf(x34682_d1_b0) = 1
    val x34683_d0_b0 = SRAM(size=2048, banking=Strided(banks=1, stride=2048)).name("x34683_d0_b0").ctrl(x36799).srcCtx("CellsPar.scala:143:23:bias") // x34683 = SRAMNew(ArrayBuffer(Const(1), Const(2048)))
    isAccum(x34683_d0_b0) = false
    bufferDepthOf(x34683_d0_b0) = 1
    val x34683_d1_b0 = SRAM(size=2048, banking=Strided(banks=1, stride=2048)).name("x34683_d1_b0").ctrl(x36799).srcCtx("CellsPar.scala:143:23:bias") // x34683 = SRAMNew(ArrayBuffer(Const(1), Const(2048)))
    isAccum(x34683_d1_b0) = false
    bufferDepthOf(x34683_d1_b0) = 1
    val x34683_d2_b0 = SRAM(size=2048, banking=Strided(banks=1, stride=2048)).name("x34683_d2_b0").ctrl(x36799).srcCtx("CellsPar.scala:143:23:bias") // x34683 = SRAMNew(ArrayBuffer(Const(1), Const(2048)))
    isAccum(x34683_d2_b0) = false
    bufferDepthOf(x34683_d2_b0) = 1
    val x34683_d3_b0 = SRAM(size=2048, banking=Strided(banks=1, stride=2048)).name("x34683_d3_b0").ctrl(x36799).srcCtx("CellsPar.scala:143:23:bias") // x34683 = SRAMNew(ArrayBuffer(Const(1), Const(2048)))
    isAccum(x34683_d3_b0) = false
    bufferDepthOf(x34683_d3_b0) = 1
    val x34684_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34684_d0_b0").ctrl(x36799).srcCtx("CellsPar.scala:139:20") // x34684 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34684_d0_b0) = false
    bufferDepthOf(x34684_d0_b0) = 1
    val x34684_d1_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34684_d1_b0").ctrl(x36799).srcCtx("CellsPar.scala:139:20") // x34684 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34684_d1_b0) = false
    bufferDepthOf(x34684_d1_b0) = 1
    val x34684_d2_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34684_d2_b0").ctrl(x36799).srcCtx("CellsPar.scala:139:20") // x34684 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34684_d2_b0) = false
    bufferDepthOf(x34684_d2_b0) = 1
    val x34684_d3_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34684_d3_b0").ctrl(x36799).srcCtx("CellsPar.scala:139:20") // x34684 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34684_d3_b0) = false
    bufferDepthOf(x34684_d3_b0) = 1
    val x34684_d4_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34684_d4_b0").ctrl(x36799).srcCtx("CellsPar.scala:139:20") // x34684 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34684_d4_b0) = false
    bufferDepthOf(x34684_d4_b0) = 1
    val x34684_d5_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34684_d5_b0").ctrl(x36799).srcCtx("CellsPar.scala:139:20") // x34684 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34684_d5_b0) = false
    bufferDepthOf(x34684_d5_b0) = 1
    val x34684_d6_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34684_d6_b0").ctrl(x36799).srcCtx("CellsPar.scala:139:20") // x34684 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34684_d6_b0) = false
    bufferDepthOf(x34684_d6_b0) = 1
    val x34684_d7_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34684_d7_b0").ctrl(x36799).srcCtx("CellsPar.scala:139:20") // x34684 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34684_d7_b0) = false
    bufferDepthOf(x34684_d7_b0) = 1
    val x34685_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34685_d0_b0").ctrl(x36799).srcCtx("CellsPar.scala:140:20") // x34685 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34685_d0_b0) = true
    bufferDepthOf(x34685_d0_b0) = 1
    val x34686_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34686_d0_b0").ctrl(x36799).srcCtx("CellsPar.scala:141:45:targetSRAM") // x34686 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34686_d0_b0) = false
    bufferDepthOf(x34686_d0_b0) = 1
    val x34686_d1_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34686_d1_b0").ctrl(x36799).srcCtx("CellsPar.scala:141:45:targetSRAM") // x34686 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34686_d1_b0) = true
    bufferDepthOf(x34686_d1_b0) = 1
    val x34687_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34687_d0_b0").ctrl(x36799).srcCtx("CellsPar.scala:141:79:targetSRAM") // x34687 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34687_d0_b0) = false
    bufferDepthOf(x34687_d0_b0) = 1
    val x34687_d1_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34687_d1_b0").ctrl(x36799).srcCtx("CellsPar.scala:141:79:targetSRAM") // x34687 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34687_d1_b0) = true
    bufferDepthOf(x34687_d1_b0) = 1
    val x34688_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34688_d0_b0").ctrl(x36799).srcCtx("CellsPar.scala:142:44:targetSRAM") // x34688 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34688_d0_b0) = false
    bufferDepthOf(x34688_d0_b0) = 1
    val x34688_d1_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34688_d1_b0").ctrl(x36799).srcCtx("CellsPar.scala:142:44:targetSRAM") // x34688 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34688_d1_b0) = true
    bufferDepthOf(x34688_d1_b0) = 1
    val x34689_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34689_d0_b0").ctrl(x36799).srcCtx("CellsPar.scala:142:78:targetSRAM") // x34689 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34689_d0_b0) = false
    bufferDepthOf(x34689_d0_b0) = 1
    val x34689_d1_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34689_d1_b0").ctrl(x36799).srcCtx("CellsPar.scala:142:78:targetSRAM") // x34689 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34689_d1_b0) = true
    bufferDepthOf(x34689_d1_b0) = 1
    val x34690_d0_b0 = SRAM(size=2048, banking=Strided(banks=1, stride=2048)).name("x34690_d0_b0").ctrl(x36799).srcCtx("CellsPar.scala:143:23:bias") // x34690 = SRAMNew(ArrayBuffer(Const(1), Const(2048)))
    isAccum(x34690_d0_b0) = false
    bufferDepthOf(x34690_d0_b0) = 1
    val x34690_d1_b0 = SRAM(size=2048, banking=Strided(banks=1, stride=2048)).name("x34690_d1_b0").ctrl(x36799).srcCtx("CellsPar.scala:143:23:bias") // x34690 = SRAMNew(ArrayBuffer(Const(1), Const(2048)))
    isAccum(x34690_d1_b0) = false
    bufferDepthOf(x34690_d1_b0) = 1
    val x34690_d2_b0 = SRAM(size=2048, banking=Strided(banks=1, stride=2048)).name("x34690_d2_b0").ctrl(x36799).srcCtx("CellsPar.scala:143:23:bias") // x34690 = SRAMNew(ArrayBuffer(Const(1), Const(2048)))
    isAccum(x34690_d2_b0) = false
    bufferDepthOf(x34690_d2_b0) = 1
    val x34690_d3_b0 = SRAM(size=2048, banking=Strided(banks=1, stride=2048)).name("x34690_d3_b0").ctrl(x36799).srcCtx("CellsPar.scala:143:23:bias") // x34690 = SRAMNew(ArrayBuffer(Const(1), Const(2048)))
    isAccum(x34690_d3_b0) = false
    bufferDepthOf(x34690_d3_b0) = 1
    val x34691_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34691_d0_b0").ctrl(x36799).srcCtx("CellsPar.scala:139:20") // x34691 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34691_d0_b0) = false
    bufferDepthOf(x34691_d0_b0) = 1
    val x34691_d1_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34691_d1_b0").ctrl(x36799).srcCtx("CellsPar.scala:139:20") // x34691 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34691_d1_b0) = false
    bufferDepthOf(x34691_d1_b0) = 1
    val x34691_d2_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34691_d2_b0").ctrl(x36799).srcCtx("CellsPar.scala:139:20") // x34691 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34691_d2_b0) = false
    bufferDepthOf(x34691_d2_b0) = 1
    val x34691_d3_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34691_d3_b0").ctrl(x36799).srcCtx("CellsPar.scala:139:20") // x34691 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34691_d3_b0) = false
    bufferDepthOf(x34691_d3_b0) = 1
    val x34691_d4_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34691_d4_b0").ctrl(x36799).srcCtx("CellsPar.scala:139:20") // x34691 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34691_d4_b0) = false
    bufferDepthOf(x34691_d4_b0) = 1
    val x34691_d5_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34691_d5_b0").ctrl(x36799).srcCtx("CellsPar.scala:139:20") // x34691 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34691_d5_b0) = false
    bufferDepthOf(x34691_d5_b0) = 1
    val x34691_d6_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34691_d6_b0").ctrl(x36799).srcCtx("CellsPar.scala:139:20") // x34691 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34691_d6_b0) = false
    bufferDepthOf(x34691_d6_b0) = 1
    val x34691_d7_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34691_d7_b0").ctrl(x36799).srcCtx("CellsPar.scala:139:20") // x34691 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34691_d7_b0) = false
    bufferDepthOf(x34691_d7_b0) = 1
    val x34692_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34692_d0_b0").ctrl(x36799).srcCtx("CellsPar.scala:140:20") // x34692 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34692_d0_b0) = true
    bufferDepthOf(x34692_d0_b0) = 1
    val x34693_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34693_d0_b0").ctrl(x36799).srcCtx("CellsPar.scala:141:45:targetSRAM") // x34693 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34693_d0_b0) = false
    bufferDepthOf(x34693_d0_b0) = 1
    val x34693_d1_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34693_d1_b0").ctrl(x36799).srcCtx("CellsPar.scala:141:45:targetSRAM") // x34693 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34693_d1_b0) = true
    bufferDepthOf(x34693_d1_b0) = 1
    val x34694_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34694_d0_b0").ctrl(x36799).srcCtx("CellsPar.scala:141:79:targetSRAM") // x34694 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34694_d0_b0) = false
    bufferDepthOf(x34694_d0_b0) = 1
    val x34694_d1_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34694_d1_b0").ctrl(x36799).srcCtx("CellsPar.scala:141:79:targetSRAM") // x34694 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34694_d1_b0) = true
    bufferDepthOf(x34694_d1_b0) = 1
    val x34695_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34695_d0_b0").ctrl(x36799).srcCtx("CellsPar.scala:142:44:targetSRAM") // x34695 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34695_d0_b0) = false
    bufferDepthOf(x34695_d0_b0) = 1
    val x34695_d1_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34695_d1_b0").ctrl(x36799).srcCtx("CellsPar.scala:142:44:targetSRAM") // x34695 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34695_d1_b0) = true
    bufferDepthOf(x34695_d1_b0) = 1
    val x34696_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34696_d0_b0").ctrl(x36799).srcCtx("CellsPar.scala:142:78:targetSRAM") // x34696 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34696_d0_b0) = false
    bufferDepthOf(x34696_d0_b0) = 1
    val x34696_d1_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34696_d1_b0").ctrl(x36799).srcCtx("CellsPar.scala:142:78:targetSRAM") // x34696 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34696_d1_b0) = true
    bufferDepthOf(x34696_d1_b0) = 1
    val x34697_d0_b0 = SRAM(size=2048, banking=Strided(banks=1, stride=2048)).name("x34697_d0_b0").ctrl(x36799).srcCtx("CellsPar.scala:143:23:bias") // x34697 = SRAMNew(ArrayBuffer(Const(1), Const(2048)))
    isAccum(x34697_d0_b0) = false
    bufferDepthOf(x34697_d0_b0) = 1
    val x34697_d1_b0 = SRAM(size=2048, banking=Strided(banks=1, stride=2048)).name("x34697_d1_b0").ctrl(x36799).srcCtx("CellsPar.scala:143:23:bias") // x34697 = SRAMNew(ArrayBuffer(Const(1), Const(2048)))
    isAccum(x34697_d1_b0) = false
    bufferDepthOf(x34697_d1_b0) = 1
    val x34697_d2_b0 = SRAM(size=2048, banking=Strided(banks=1, stride=2048)).name("x34697_d2_b0").ctrl(x36799).srcCtx("CellsPar.scala:143:23:bias") // x34697 = SRAMNew(ArrayBuffer(Const(1), Const(2048)))
    isAccum(x34697_d2_b0) = false
    bufferDepthOf(x34697_d2_b0) = 1
    val x34697_d3_b0 = SRAM(size=2048, banking=Strided(banks=1, stride=2048)).name("x34697_d3_b0").ctrl(x36799).srcCtx("CellsPar.scala:143:23:bias") // x34697 = SRAMNew(ArrayBuffer(Const(1), Const(2048)))
    isAccum(x34697_d3_b0) = false
    bufferDepthOf(x34697_d3_b0) = 1
    val x34698_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34698_d0_b0").ctrl(x36799).srcCtx("CellsPar.scala:139:20") // x34698 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34698_d0_b0) = false
    bufferDepthOf(x34698_d0_b0) = 1
    val x34698_d1_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34698_d1_b0").ctrl(x36799).srcCtx("CellsPar.scala:139:20") // x34698 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34698_d1_b0) = false
    bufferDepthOf(x34698_d1_b0) = 1
    val x34698_d2_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34698_d2_b0").ctrl(x36799).srcCtx("CellsPar.scala:139:20") // x34698 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34698_d2_b0) = false
    bufferDepthOf(x34698_d2_b0) = 1
    val x34698_d3_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34698_d3_b0").ctrl(x36799).srcCtx("CellsPar.scala:139:20") // x34698 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34698_d3_b0) = false
    bufferDepthOf(x34698_d3_b0) = 1
    val x34698_d4_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34698_d4_b0").ctrl(x36799).srcCtx("CellsPar.scala:139:20") // x34698 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34698_d4_b0) = false
    bufferDepthOf(x34698_d4_b0) = 1
    val x34699_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34699_d0_b0").ctrl(x36799).srcCtx("CellsPar.scala:140:20") // x34699 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34699_d0_b0) = false
    bufferDepthOf(x34699_d0_b0) = 1
    val x34699_d1_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34699_d1_b0").ctrl(x36799).srcCtx("CellsPar.scala:140:20") // x34699 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34699_d1_b0) = true
    bufferDepthOf(x34699_d1_b0) = 1
    val x34700_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34700_d0_b0").ctrl(x36799).srcCtx("CellsPar.scala:141:45:targetSRAM") // x34700 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34700_d0_b0) = false
    bufferDepthOf(x34700_d0_b0) = 1
    val x34700_d1_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34700_d1_b0").ctrl(x36799).srcCtx("CellsPar.scala:141:45:targetSRAM") // x34700 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34700_d1_b0) = true
    bufferDepthOf(x34700_d1_b0) = 1
    val x34701_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34701_d0_b0").ctrl(x36799).srcCtx("CellsPar.scala:141:79:targetSRAM") // x34701 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34701_d0_b0) = false
    bufferDepthOf(x34701_d0_b0) = 1
    val x34701_d1_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34701_d1_b0").ctrl(x36799).srcCtx("CellsPar.scala:141:79:targetSRAM") // x34701 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34701_d1_b0) = true
    bufferDepthOf(x34701_d1_b0) = 1
    val x34702_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34702_d0_b0").ctrl(x36799).srcCtx("CellsPar.scala:142:44:targetSRAM") // x34702 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34702_d0_b0) = false
    bufferDepthOf(x34702_d0_b0) = 1
    val x34702_d1_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34702_d1_b0").ctrl(x36799).srcCtx("CellsPar.scala:142:44:targetSRAM") // x34702 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34702_d1_b0) = true
    bufferDepthOf(x34702_d1_b0) = 1
    val x34703_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34703_d0_b0").ctrl(x36799).srcCtx("CellsPar.scala:142:78:targetSRAM") // x34703 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34703_d0_b0) = false
    bufferDepthOf(x34703_d0_b0) = 1
    val x34703_d1_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34703_d1_b0").ctrl(x36799).srcCtx("CellsPar.scala:142:78:targetSRAM") // x34703 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34703_d1_b0) = true
    bufferDepthOf(x34703_d1_b0) = 1
    val x34704_d0_b0 = SRAM(size=2048, banking=Strided(banks=1, stride=2048)).name("x34704_d0_b0").ctrl(x36799).srcCtx("CellsPar.scala:143:23:bias") // x34704 = SRAMNew(ArrayBuffer(Const(1), Const(2048)))
    isAccum(x34704_d0_b0) = false
    bufferDepthOf(x34704_d0_b0) = 1
    val x34704_d1_b0 = SRAM(size=2048, banking=Strided(banks=1, stride=2048)).name("x34704_d1_b0").ctrl(x36799).srcCtx("CellsPar.scala:143:23:bias") // x34704 = SRAMNew(ArrayBuffer(Const(1), Const(2048)))
    isAccum(x34704_d1_b0) = false
    bufferDepthOf(x34704_d1_b0) = 1
    val x34704_d2_b0 = SRAM(size=2048, banking=Strided(banks=1, stride=2048)).name("x34704_d2_b0").ctrl(x36799).srcCtx("CellsPar.scala:143:23:bias") // x34704 = SRAMNew(ArrayBuffer(Const(1), Const(2048)))
    isAccum(x34704_d2_b0) = false
    bufferDepthOf(x34704_d2_b0) = 1
    val x34704_d3_b0 = SRAM(size=2048, banking=Strided(banks=1, stride=2048)).name("x34704_d3_b0").ctrl(x36799).srcCtx("CellsPar.scala:143:23:bias") // x34704 = SRAMNew(ArrayBuffer(Const(1), Const(2048)))
    isAccum(x34704_d3_b0) = false
    bufferDepthOf(x34704_d3_b0) = 1
    val x34705 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x34705").ctrl(x36799).srcCtx("CellsPar.scala:161:12") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x34706 = CounterChain(List(x34705)).name("x34706").ctrl(x36799).srcCtx("CellsPar.scala:161:12") // CounterChainNew(List(x34705))
    val x34728 = LoopController(style=StreamPipe, level=OuterControl, cchain=x34706).name("x34728").ctrl(x36799).srcCtx("CellsPar.scala:161:12") // UnrolledForeach(List(Const(true)),x34706,Block(Const(())),List(List(b22154)),List(List(b22155)))
    val b22154 = CounterIter(x34705, Some(0)).name("b22154").ctrl(x34728) // b22154
    val b22155 = Const(true).name("b22155").ctrl(x34728) // b22155
    val b36809 = StreamOut(field="offset").name("b36809").ctrl(x34728).srcCtx("CellsPar.scala:161:12") // x34707 = StreamOutNew(BurstCmdBus)
    isAccum(b36809) = false
    bufferDepthOf(b36809) = 1
    val b36810 = StreamOut(field="size").name("b36810").ctrl(x34728).srcCtx("CellsPar.scala:161:12") // x34707 = StreamOutNew(BurstCmdBus)
    isAccum(b36810) = false
    bufferDepthOf(b36810) = 1
    val x34708 = StreamIn(field="data").name("x34708").ctrl(x34728).srcCtx("CellsPar.scala:161:12") // x34708 = StreamInNew(BurstDataBus())
    isAccum(x34708) = false
    bufferDepthOf(x34708) = 1
    val x34719 = UnitController(style=SeqPipe, level=InnerControl).name("x34719").ctrl(x34728).srcCtx("CellsPar.scala:161:12") // UnitPipe(List(b22155),Block(x34718))
    val x34709 = b22154 // FixConvert(b22154,TRUE,_32,_0) (Same Type. No op)
    val x34710 = OpDef(op=FixSla, inputs=List(x34709, Const(11))).name("x34710").ctrl(x34719).srcCtx("CellsPar.scala:161:12") // FixLsh(x34709,Const(11))
    val x34711 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x34712 = OpDef(op=FixAdd, inputs=List(x34710, x34711)).name("x34712").ctrl(x34719).srcCtx("CellsPar.scala:161:12") // FixAdd(x34710,x34711)
    val x34713 = OpDef(op=FixSla, inputs=List(x34712, Const(2))).name("x34713").ctrl(x34719).srcCtx("CellsPar.scala:161:12") // FixLsh(x34712,Const(2))
    val x34714 = x34713 // FixConvert(x34713,TRUE,_64,_0)
    val x34715 = DramAddress(x34373).name("x34715").ctrl(x34719).srcCtx("CellsPar.scala:161:12") // GetDRAMAddress(x34373)
    val x34717_x34716 = OpDef(op=FixAdd, inputs=List(x34714, x34715)).name("x34717_x34716").ctrl(x34719).srcCtx("CellsPar.scala:161:12") // FixAdd(x34714,x34715)
    // x34717 = SimpleStruct(ArrayBuffer((offset,x34716), (size,Const(8192)), (isLoad,Const(true))))
    val x34718_b36811_b36809 = WriteMem(b36809, x34717_x34716).name("x34718_b36811_b36809").ctrl(x34719).srcCtx("CellsPar.scala:161:12") // StreamWrite(x34707,x34717,b22155)
    val x34718_b36812_b36810 = WriteMem(b36810, Const(8192)).name("x34718_b36812_b36810").ctrl(x34719).srcCtx("CellsPar.scala:161:12") // StreamWrite(x34707,x34717,b22155)
    val x34720 = FringeDenseLoad(dram=List(x34373), cmdStream=List(b36809, b36810), dataStream=List(x34708)).name("x34720").ctrl(x34728).srcCtx("CellsPar.scala:161:12") // FringeDenseLoad(x34373,x34707,x34708)
    val x34721 = Counter(min=Const(0), max=Const(2048), step=Const(1), par=1).name("x34721").ctrl(x34728).srcCtx("CellsPar.scala:161:12") // CounterNew(Const(0),Const(2048),Const(1),Const(1))
    val x34722 = CounterChain(List(x34721)).name("x34722").ctrl(x34728).srcCtx("CellsPar.scala:161:12") // CounterChainNew(List(x34721))
    val x34727 = LoopController(style=InnerPipe, level=InnerControl, cchain=x34722).name("x34727").ctrl(x34728).srcCtx("CellsPar.scala:161:12") // UnrolledForeach(List(b22155),x34722,Block(Const(())),List(List(b22172)),List(List(b22173)))
    val b22172 = CounterIter(x34721, None).name("b22172").ctrl(x34727) // b22172
    val b22173 = Const(true).name("b22173").ctrl(x34727) // b22173
    val x34723 = OpDef(op=BitAnd, inputs=List(b22173, b22155)).name("x34723").ctrl(x34727).srcCtx("UnrollingBase.scala:28:66") // And(b22173,b22155)
    val x34724_x34724 = ReadMem(x34708).name("x34724_x34724").ctrl(x34727).srcCtx("CellsPar.scala:161:12") // ParStreamRead(x34708,List(x34723))
    val x34725_x34725 = x34724_x34724 // x34725 = VectorApply(x34724,0)
    val x34726 = StoreBanks(List(x34683_d0_b0, x34683_d1_b0, x34683_d2_b0, x34683_d3_b0), List(b22154, b22172), x34725_x34725).name("x34726").ctrl(x34727).srcCtx("CellsPar.scala:161:12") // ParSRAMStore(x34683,List(List(b22154, b22172)),List(x34725),List(x34723))
    val x34729 = Counter(min=Const(0), max=Const(512), step=Const(1), par=1).name("x34729").ctrl(x36799).srcCtx("CellsPar.scala:162:50") // CounterNew(Const(0),Const(512),Const(1),Const(1))
    val x34730 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x34730").ctrl(x36799).srcCtx("CellsPar.scala:162:27") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x34731 = CounterChain(List(x34730,x34729)).name("x34731").ctrl(x36799).srcCtx("CellsPar.scala:162:63") // CounterChainNew(List(x34730, x34729))
    val x34736 = LoopController(style=MetaPipe, level=OuterControl, cchain=x34731).name("x34736").ctrl(x36799).srcCtx("CellsPar.scala:162:63") // UnrolledForeach(List(Const(true)),x34731,Block(Const(())),List(List(b22183), List(b22184)),List(List(b22185), List(b22186)))
    val b22183 = CounterIter(x34730, Some(0)).name("b22183").ctrl(x34736) // b22183
    val b22185 = Const(true).name("b22185").ctrl(x34736) // b22185
    val b22184 = CounterIter(x34729, Some(0)).name("b22184").ctrl(x34736) // b22184
    val b22186 = Const(true).name("b22186").ctrl(x34736) // b22186
    val x34735 = UnitController(style=SeqPipe, level=InnerControl).name("x34735").ctrl(x34736).srcCtx("CellsPar.scala:163:18") // UnitPipe(List(b22185, b22186),Block(Const(())))
    val x34732 = OpDef(op=BitAnd, inputs=List(b22185, b22186)).name("x34732").ctrl(x34735).srcCtx("UnrollingBase.scala:28:66") // And(b22185,b22186)
    val x34733 = StoreBanks(List(x34678_d0_b0), List(b22183, b22184), Const(0.0)).name("x34733").ctrl(x34735).srcCtx("CellsPar.scala:164:18") // SRAMStore(x34678,ArrayBuffer(Const(1), Const(512)),List(b22183, b22184),Const(0),Const(0),x34732)
    val x34734 = StoreBanks(List(x34677_d0_b0, x34677_d5_b0, x34677_d1_b0, x34677_d6_b0, x34677_d2_b0, x34677_d7_b0, x34677_d3_b0, x34677_d4_b0), List(b22183, b22184), Const(0.0)).name("x34734").ctrl(x34735).srcCtx("CellsPar.scala:165:18") // SRAMStore(x34677,ArrayBuffer(Const(1), Const(512)),List(b22183, b22184),Const(0),Const(0),x34732)
    val x34737 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x34737").ctrl(x36799).srcCtx("CellsPar.scala:161:12") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x34738 = CounterChain(List(x34737)).name("x34738").ctrl(x36799).srcCtx("CellsPar.scala:161:12") // CounterChainNew(List(x34737))
    val x34760 = LoopController(style=StreamPipe, level=OuterControl, cchain=x34738).name("x34760").ctrl(x36799).srcCtx("CellsPar.scala:161:12") // UnrolledForeach(List(Const(true)),x34738,Block(Const(())),List(List(b22195)),List(List(b22196)))
    val b22195 = CounterIter(x34737, Some(0)).name("b22195").ctrl(x34760) // b22195
    val b22196 = Const(true).name("b22196").ctrl(x34760) // b22196
    val b36813 = StreamOut(field="offset").name("b36813").ctrl(x34760).srcCtx("CellsPar.scala:161:12") // x34739 = StreamOutNew(BurstCmdBus)
    isAccum(b36813) = false
    bufferDepthOf(b36813) = 1
    val b36814 = StreamOut(field="size").name("b36814").ctrl(x34760).srcCtx("CellsPar.scala:161:12") // x34739 = StreamOutNew(BurstCmdBus)
    isAccum(b36814) = false
    bufferDepthOf(b36814) = 1
    val x34740 = StreamIn(field="data").name("x34740").ctrl(x34760).srcCtx("CellsPar.scala:161:12") // x34740 = StreamInNew(BurstDataBus())
    isAccum(x34740) = false
    bufferDepthOf(x34740) = 1
    val x34751 = UnitController(style=SeqPipe, level=InnerControl).name("x34751").ctrl(x34760).srcCtx("CellsPar.scala:161:12") // UnitPipe(List(b22196),Block(x34750))
    val x34741 = b22195 // FixConvert(b22195,TRUE,_32,_0) (Same Type. No op)
    val x34742 = OpDef(op=FixSla, inputs=List(x34741, Const(11))).name("x34742").ctrl(x34751).srcCtx("CellsPar.scala:161:12") // FixLsh(x34741,Const(11))
    val x34743 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x34744 = OpDef(op=FixAdd, inputs=List(x34742, x34743)).name("x34744").ctrl(x34751).srcCtx("CellsPar.scala:161:12") // FixAdd(x34742,x34743)
    val x34745 = OpDef(op=FixSla, inputs=List(x34744, Const(2))).name("x34745").ctrl(x34751).srcCtx("CellsPar.scala:161:12") // FixLsh(x34744,Const(2))
    val x34746 = x34745 // FixConvert(x34745,TRUE,_64,_0)
    val x34747 = DramAddress(x34449).name("x34747").ctrl(x34751).srcCtx("CellsPar.scala:161:12") // GetDRAMAddress(x34449)
    val x34749_x34748 = OpDef(op=FixAdd, inputs=List(x34746, x34747)).name("x34749_x34748").ctrl(x34751).srcCtx("CellsPar.scala:161:12") // FixAdd(x34746,x34747)
    // x34749 = SimpleStruct(ArrayBuffer((offset,x34748), (size,Const(8192)), (isLoad,Const(true))))
    val x34750_b36815_b36813 = WriteMem(b36813, x34749_x34748).name("x34750_b36815_b36813").ctrl(x34751).srcCtx("CellsPar.scala:161:12") // StreamWrite(x34739,x34749,b22196)
    val x34750_b36816_b36814 = WriteMem(b36814, Const(8192)).name("x34750_b36816_b36814").ctrl(x34751).srcCtx("CellsPar.scala:161:12") // StreamWrite(x34739,x34749,b22196)
    val x34752 = FringeDenseLoad(dram=List(x34449), cmdStream=List(b36813, b36814), dataStream=List(x34740)).name("x34752").ctrl(x34760).srcCtx("CellsPar.scala:161:12") // FringeDenseLoad(x34449,x34739,x34740)
    val x34753 = Counter(min=Const(0), max=Const(2048), step=Const(1), par=1).name("x34753").ctrl(x34760).srcCtx("CellsPar.scala:161:12") // CounterNew(Const(0),Const(2048),Const(1),Const(1))
    val x34754 = CounterChain(List(x34753)).name("x34754").ctrl(x34760).srcCtx("CellsPar.scala:161:12") // CounterChainNew(List(x34753))
    val x34759 = LoopController(style=InnerPipe, level=InnerControl, cchain=x34754).name("x34759").ctrl(x34760).srcCtx("CellsPar.scala:161:12") // UnrolledForeach(List(b22196),x34754,Block(Const(())),List(List(b22213)),List(List(b22214)))
    val b22213 = CounterIter(x34753, None).name("b22213").ctrl(x34759) // b22213
    val b22214 = Const(true).name("b22214").ctrl(x34759) // b22214
    val x34755 = OpDef(op=BitAnd, inputs=List(b22214, b22196)).name("x34755").ctrl(x34759).srcCtx("UnrollingBase.scala:28:66") // And(b22214,b22196)
    val x34756_x34756 = ReadMem(x34740).name("x34756_x34756").ctrl(x34759).srcCtx("CellsPar.scala:161:12") // ParStreamRead(x34740,List(x34755))
    val x34757_x34757 = x34756_x34756 // x34757 = VectorApply(x34756,0)
    val x34758 = StoreBanks(List(x34690_d0_b0, x34690_d1_b0, x34690_d2_b0, x34690_d3_b0), List(b22195, b22213), x34757_x34757).name("x34758").ctrl(x34759).srcCtx("CellsPar.scala:161:12") // ParSRAMStore(x34690,List(List(b22195, b22213)),List(x34757),List(x34755))
    val x34761 = Counter(min=Const(0), max=Const(512), step=Const(1), par=1).name("x34761").ctrl(x36799).srcCtx("CellsPar.scala:162:50") // CounterNew(Const(0),Const(512),Const(1),Const(1))
    val x34762 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x34762").ctrl(x36799).srcCtx("CellsPar.scala:162:27") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x34763 = CounterChain(List(x34762,x34761)).name("x34763").ctrl(x36799).srcCtx("CellsPar.scala:162:63") // CounterChainNew(List(x34762, x34761))
    val x34768 = LoopController(style=MetaPipe, level=OuterControl, cchain=x34763).name("x34768").ctrl(x36799).srcCtx("CellsPar.scala:162:63") // UnrolledForeach(List(Const(true)),x34763,Block(Const(())),List(List(b22224), List(b22225)),List(List(b22226), List(b22227)))
    val b22224 = CounterIter(x34762, Some(0)).name("b22224").ctrl(x34768) // b22224
    val b22226 = Const(true).name("b22226").ctrl(x34768) // b22226
    val b22225 = CounterIter(x34761, Some(0)).name("b22225").ctrl(x34768) // b22225
    val b22227 = Const(true).name("b22227").ctrl(x34768) // b22227
    val x34767 = UnitController(style=SeqPipe, level=InnerControl).name("x34767").ctrl(x34768).srcCtx("CellsPar.scala:163:18") // UnitPipe(List(b22226, b22227),Block(Const(())))
    val x34764 = OpDef(op=BitAnd, inputs=List(b22226, b22227)).name("x34764").ctrl(x34767).srcCtx("UnrollingBase.scala:28:66") // And(b22226,b22227)
    val x34765 = StoreBanks(List(x34685_d0_b0), List(b22224, b22225), Const(0.0)).name("x34765").ctrl(x34767).srcCtx("CellsPar.scala:164:18") // SRAMStore(x34685,ArrayBuffer(Const(1), Const(512)),List(b22224, b22225),Const(0),Const(0),x34764)
    val x34766 = StoreBanks(List(x34684_d0_b0, x34684_d5_b0, x34684_d1_b0, x34684_d6_b0, x34684_d2_b0, x34684_d7_b0, x34684_d3_b0, x34684_d4_b0), List(b22224, b22225), Const(0.0)).name("x34766").ctrl(x34767).srcCtx("CellsPar.scala:165:18") // SRAMStore(x34684,ArrayBuffer(Const(1), Const(512)),List(b22224, b22225),Const(0),Const(0),x34764)
    val x34769 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x34769").ctrl(x36799).srcCtx("CellsPar.scala:161:12") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x34770 = CounterChain(List(x34769)).name("x34770").ctrl(x36799).srcCtx("CellsPar.scala:161:12") // CounterChainNew(List(x34769))
    val x34792 = LoopController(style=StreamPipe, level=OuterControl, cchain=x34770).name("x34792").ctrl(x36799).srcCtx("CellsPar.scala:161:12") // UnrolledForeach(List(Const(true)),x34770,Block(Const(())),List(List(b22236)),List(List(b22237)))
    val b22236 = CounterIter(x34769, Some(0)).name("b22236").ctrl(x34792) // b22236
    val b22237 = Const(true).name("b22237").ctrl(x34792) // b22237
    val b36817 = StreamOut(field="offset").name("b36817").ctrl(x34792).srcCtx("CellsPar.scala:161:12") // x34771 = StreamOutNew(BurstCmdBus)
    isAccum(b36817) = false
    bufferDepthOf(b36817) = 1
    val b36818 = StreamOut(field="size").name("b36818").ctrl(x34792).srcCtx("CellsPar.scala:161:12") // x34771 = StreamOutNew(BurstCmdBus)
    isAccum(b36818) = false
    bufferDepthOf(b36818) = 1
    val x34772 = StreamIn(field="data").name("x34772").ctrl(x34792).srcCtx("CellsPar.scala:161:12") // x34772 = StreamInNew(BurstDataBus())
    isAccum(x34772) = false
    bufferDepthOf(x34772) = 1
    val x34783 = UnitController(style=SeqPipe, level=InnerControl).name("x34783").ctrl(x34792).srcCtx("CellsPar.scala:161:12") // UnitPipe(List(b22237),Block(x34782))
    val x34773 = b22236 // FixConvert(b22236,TRUE,_32,_0) (Same Type. No op)
    val x34774 = OpDef(op=FixSla, inputs=List(x34773, Const(11))).name("x34774").ctrl(x34783).srcCtx("CellsPar.scala:161:12") // FixLsh(x34773,Const(11))
    val x34775 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x34776 = OpDef(op=FixAdd, inputs=List(x34774, x34775)).name("x34776").ctrl(x34783).srcCtx("CellsPar.scala:161:12") // FixAdd(x34774,x34775)
    val x34777 = OpDef(op=FixSla, inputs=List(x34776, Const(2))).name("x34777").ctrl(x34783).srcCtx("CellsPar.scala:161:12") // FixLsh(x34776,Const(2))
    val x34778 = x34777 // FixConvert(x34777,TRUE,_64,_0)
    val x34779 = DramAddress(x34525).name("x34779").ctrl(x34783).srcCtx("CellsPar.scala:161:12") // GetDRAMAddress(x34525)
    val x34781_x34780 = OpDef(op=FixAdd, inputs=List(x34778, x34779)).name("x34781_x34780").ctrl(x34783).srcCtx("CellsPar.scala:161:12") // FixAdd(x34778,x34779)
    // x34781 = SimpleStruct(ArrayBuffer((offset,x34780), (size,Const(8192)), (isLoad,Const(true))))
    val x34782_b36819_b36817 = WriteMem(b36817, x34781_x34780).name("x34782_b36819_b36817").ctrl(x34783).srcCtx("CellsPar.scala:161:12") // StreamWrite(x34771,x34781,b22237)
    val x34782_b36820_b36818 = WriteMem(b36818, Const(8192)).name("x34782_b36820_b36818").ctrl(x34783).srcCtx("CellsPar.scala:161:12") // StreamWrite(x34771,x34781,b22237)
    val x34784 = FringeDenseLoad(dram=List(x34525), cmdStream=List(b36817, b36818), dataStream=List(x34772)).name("x34784").ctrl(x34792).srcCtx("CellsPar.scala:161:12") // FringeDenseLoad(x34525,x34771,x34772)
    val x34785 = Counter(min=Const(0), max=Const(2048), step=Const(1), par=1).name("x34785").ctrl(x34792).srcCtx("CellsPar.scala:161:12") // CounterNew(Const(0),Const(2048),Const(1),Const(1))
    val x34786 = CounterChain(List(x34785)).name("x34786").ctrl(x34792).srcCtx("CellsPar.scala:161:12") // CounterChainNew(List(x34785))
    val x34791 = LoopController(style=InnerPipe, level=InnerControl, cchain=x34786).name("x34791").ctrl(x34792).srcCtx("CellsPar.scala:161:12") // UnrolledForeach(List(b22237),x34786,Block(Const(())),List(List(b22254)),List(List(b22255)))
    val b22254 = CounterIter(x34785, None).name("b22254").ctrl(x34791) // b22254
    val b22255 = Const(true).name("b22255").ctrl(x34791) // b22255
    val x34787 = OpDef(op=BitAnd, inputs=List(b22255, b22237)).name("x34787").ctrl(x34791).srcCtx("UnrollingBase.scala:28:66") // And(b22255,b22237)
    val x34788_x34788 = ReadMem(x34772).name("x34788_x34788").ctrl(x34791).srcCtx("CellsPar.scala:161:12") // ParStreamRead(x34772,List(x34787))
    val x34789_x34789 = x34788_x34788 // x34789 = VectorApply(x34788,0)
    val x34790 = StoreBanks(List(x34697_d0_b0, x34697_d1_b0, x34697_d2_b0, x34697_d3_b0), List(b22236, b22254), x34789_x34789).name("x34790").ctrl(x34791).srcCtx("CellsPar.scala:161:12") // ParSRAMStore(x34697,List(List(b22236, b22254)),List(x34789),List(x34787))
    val x34793 = Counter(min=Const(0), max=Const(512), step=Const(1), par=1).name("x34793").ctrl(x36799).srcCtx("CellsPar.scala:162:50") // CounterNew(Const(0),Const(512),Const(1),Const(1))
    val x34794 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x34794").ctrl(x36799).srcCtx("CellsPar.scala:162:27") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x34795 = CounterChain(List(x34794,x34793)).name("x34795").ctrl(x36799).srcCtx("CellsPar.scala:162:63") // CounterChainNew(List(x34794, x34793))
    val x34800 = LoopController(style=MetaPipe, level=OuterControl, cchain=x34795).name("x34800").ctrl(x36799).srcCtx("CellsPar.scala:162:63") // UnrolledForeach(List(Const(true)),x34795,Block(Const(())),List(List(b22265), List(b22266)),List(List(b22267), List(b22268)))
    val b22265 = CounterIter(x34794, Some(0)).name("b22265").ctrl(x34800) // b22265
    val b22267 = Const(true).name("b22267").ctrl(x34800) // b22267
    val b22266 = CounterIter(x34793, Some(0)).name("b22266").ctrl(x34800) // b22266
    val b22268 = Const(true).name("b22268").ctrl(x34800) // b22268
    val x34799 = UnitController(style=SeqPipe, level=InnerControl).name("x34799").ctrl(x34800).srcCtx("CellsPar.scala:163:18") // UnitPipe(List(b22267, b22268),Block(Const(())))
    val x34796 = OpDef(op=BitAnd, inputs=List(b22267, b22268)).name("x34796").ctrl(x34799).srcCtx("UnrollingBase.scala:28:66") // And(b22267,b22268)
    val x34797 = StoreBanks(List(x34692_d0_b0), List(b22265, b22266), Const(0.0)).name("x34797").ctrl(x34799).srcCtx("CellsPar.scala:164:18") // SRAMStore(x34692,ArrayBuffer(Const(1), Const(512)),List(b22265, b22266),Const(0),Const(0),x34796)
    val x34798 = StoreBanks(List(x34691_d0_b0, x34691_d5_b0, x34691_d1_b0, x34691_d6_b0, x34691_d2_b0, x34691_d7_b0, x34691_d3_b0, x34691_d4_b0), List(b22265, b22266), Const(0.0)).name("x34798").ctrl(x34799).srcCtx("CellsPar.scala:165:18") // SRAMStore(x34691,ArrayBuffer(Const(1), Const(512)),List(b22265, b22266),Const(0),Const(0),x34796)
    val x34801 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x34801").ctrl(x36799).srcCtx("CellsPar.scala:161:12") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x34802 = CounterChain(List(x34801)).name("x34802").ctrl(x36799).srcCtx("CellsPar.scala:161:12") // CounterChainNew(List(x34801))
    val x34824 = LoopController(style=StreamPipe, level=OuterControl, cchain=x34802).name("x34824").ctrl(x36799).srcCtx("CellsPar.scala:161:12") // UnrolledForeach(List(Const(true)),x34802,Block(Const(())),List(List(b22277)),List(List(b22278)))
    val b22277 = CounterIter(x34801, Some(0)).name("b22277").ctrl(x34824) // b22277
    val b22278 = Const(true).name("b22278").ctrl(x34824) // b22278
    val b36821 = StreamOut(field="offset").name("b36821").ctrl(x34824).srcCtx("CellsPar.scala:161:12") // x34803 = StreamOutNew(BurstCmdBus)
    isAccum(b36821) = false
    bufferDepthOf(b36821) = 1
    val b36822 = StreamOut(field="size").name("b36822").ctrl(x34824).srcCtx("CellsPar.scala:161:12") // x34803 = StreamOutNew(BurstCmdBus)
    isAccum(b36822) = false
    bufferDepthOf(b36822) = 1
    val x34804 = StreamIn(field="data").name("x34804").ctrl(x34824).srcCtx("CellsPar.scala:161:12") // x34804 = StreamInNew(BurstDataBus())
    isAccum(x34804) = false
    bufferDepthOf(x34804) = 1
    val x34815 = UnitController(style=SeqPipe, level=InnerControl).name("x34815").ctrl(x34824).srcCtx("CellsPar.scala:161:12") // UnitPipe(List(b22278),Block(x34814))
    val x34805 = b22277 // FixConvert(b22277,TRUE,_32,_0) (Same Type. No op)
    val x34806 = OpDef(op=FixSla, inputs=List(x34805, Const(11))).name("x34806").ctrl(x34815).srcCtx("CellsPar.scala:161:12") // FixLsh(x34805,Const(11))
    val x34807 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x34808 = OpDef(op=FixAdd, inputs=List(x34806, x34807)).name("x34808").ctrl(x34815).srcCtx("CellsPar.scala:161:12") // FixAdd(x34806,x34807)
    val x34809 = OpDef(op=FixSla, inputs=List(x34808, Const(2))).name("x34809").ctrl(x34815).srcCtx("CellsPar.scala:161:12") // FixLsh(x34808,Const(2))
    val x34810 = x34809 // FixConvert(x34809,TRUE,_64,_0)
    val x34811 = DramAddress(x34601).name("x34811").ctrl(x34815).srcCtx("CellsPar.scala:161:12") // GetDRAMAddress(x34601)
    val x34813_x34812 = OpDef(op=FixAdd, inputs=List(x34810, x34811)).name("x34813_x34812").ctrl(x34815).srcCtx("CellsPar.scala:161:12") // FixAdd(x34810,x34811)
    // x34813 = SimpleStruct(ArrayBuffer((offset,x34812), (size,Const(8192)), (isLoad,Const(true))))
    val x34814_b36823_b36821 = WriteMem(b36821, x34813_x34812).name("x34814_b36823_b36821").ctrl(x34815).srcCtx("CellsPar.scala:161:12") // StreamWrite(x34803,x34813,b22278)
    val x34814_b36824_b36822 = WriteMem(b36822, Const(8192)).name("x34814_b36824_b36822").ctrl(x34815).srcCtx("CellsPar.scala:161:12") // StreamWrite(x34803,x34813,b22278)
    val x34816 = FringeDenseLoad(dram=List(x34601), cmdStream=List(b36821, b36822), dataStream=List(x34804)).name("x34816").ctrl(x34824).srcCtx("CellsPar.scala:161:12") // FringeDenseLoad(x34601,x34803,x34804)
    val x34817 = Counter(min=Const(0), max=Const(2048), step=Const(1), par=1).name("x34817").ctrl(x34824).srcCtx("CellsPar.scala:161:12") // CounterNew(Const(0),Const(2048),Const(1),Const(1))
    val x34818 = CounterChain(List(x34817)).name("x34818").ctrl(x34824).srcCtx("CellsPar.scala:161:12") // CounterChainNew(List(x34817))
    val x34823 = LoopController(style=InnerPipe, level=InnerControl, cchain=x34818).name("x34823").ctrl(x34824).srcCtx("CellsPar.scala:161:12") // UnrolledForeach(List(b22278),x34818,Block(Const(())),List(List(b22295)),List(List(b22296)))
    val b22295 = CounterIter(x34817, None).name("b22295").ctrl(x34823) // b22295
    val b22296 = Const(true).name("b22296").ctrl(x34823) // b22296
    val x34819 = OpDef(op=BitAnd, inputs=List(b22296, b22278)).name("x34819").ctrl(x34823).srcCtx("UnrollingBase.scala:28:66") // And(b22296,b22278)
    val x34820_x34820 = ReadMem(x34804).name("x34820_x34820").ctrl(x34823).srcCtx("CellsPar.scala:161:12") // ParStreamRead(x34804,List(x34819))
    val x34821_x34821 = x34820_x34820 // x34821 = VectorApply(x34820,0)
    val x34822 = StoreBanks(List(x34704_d0_b0, x34704_d1_b0, x34704_d2_b0, x34704_d3_b0), List(b22277, b22295), x34821_x34821).name("x34822").ctrl(x34823).srcCtx("CellsPar.scala:161:12") // ParSRAMStore(x34704,List(List(b22277, b22295)),List(x34821),List(x34819))
    val x34825 = Counter(min=Const(0), max=Const(512), step=Const(1), par=1).name("x34825").ctrl(x36799).srcCtx("CellsPar.scala:162:50") // CounterNew(Const(0),Const(512),Const(1),Const(1))
    val x34826 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x34826").ctrl(x36799).srcCtx("CellsPar.scala:162:27") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x34827 = CounterChain(List(x34826,x34825)).name("x34827").ctrl(x36799).srcCtx("CellsPar.scala:162:63") // CounterChainNew(List(x34826, x34825))
    val x34832 = LoopController(style=MetaPipe, level=OuterControl, cchain=x34827).name("x34832").ctrl(x36799).srcCtx("CellsPar.scala:162:63") // UnrolledForeach(List(Const(true)),x34827,Block(Const(())),List(List(b22306), List(b22307)),List(List(b22308), List(b22309)))
    val b22306 = CounterIter(x34826, Some(0)).name("b22306").ctrl(x34832) // b22306
    val b22308 = Const(true).name("b22308").ctrl(x34832) // b22308
    val b22307 = CounterIter(x34825, Some(0)).name("b22307").ctrl(x34832) // b22307
    val b22309 = Const(true).name("b22309").ctrl(x34832) // b22309
    val x34831 = UnitController(style=SeqPipe, level=InnerControl).name("x34831").ctrl(x34832).srcCtx("CellsPar.scala:163:18") // UnitPipe(List(b22308, b22309),Block(Const(())))
    val x34828 = OpDef(op=BitAnd, inputs=List(b22308, b22309)).name("x34828").ctrl(x34831).srcCtx("UnrollingBase.scala:28:66") // And(b22308,b22309)
    val x34829 = StoreBanks(List(x34699_d0_b0, x34699_d1_b0), List(b22306, b22307), Const(0.0)).name("x34829").ctrl(x34831).srcCtx("CellsPar.scala:164:18") // SRAMStore(x34699,ArrayBuffer(Const(1), Const(512)),List(b22306, b22307),Const(0),Const(0),x34828)
    val x34830 = StoreBanks(List(x34698_d0_b0, x34698_d1_b0, x34698_d2_b0, x34698_d3_b0, x34698_d4_b0), List(b22306, b22307), Const(0.0)).name("x34830").ctrl(x34831).srcCtx("CellsPar.scala:165:18") // SRAMStore(x34698,ArrayBuffer(Const(1), Const(512)),List(b22306, b22307),Const(0),Const(0),x34828)
    val x34833 = Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x34833").ctrl(x36799).srcCtx("NMTDSE.scala:477:35") // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x34834 = CounterChain(List(x34833)).name("x34834").ctrl(x36799).srcCtx("NMTDSE.scala:477:41") // CounterChainNew(List(x34833))
    val x36742 = LoopController(style=SeqPipe, level=OuterControl, cchain=x34834).name("x36742").ctrl(x36799).srcCtx("NMTDSE.scala:477:41") // UnrolledForeach(List(Const(true)),x34834,Block(Const(())),List(List(b22319)),List(List(b22320)))
    val b22319 = CounterIter(x34833, Some(0)).name("b22319").ctrl(x36742) // b22319
    val b22320 = Const(true).name("b22320").ctrl(x36742) // b22320
    val x34836 = UnitController(style=SeqPipe, level=InnerControl).name("x34836").ctrl(x36742).srcCtx("NMTDSE.scala:477:41") // UnitPipe(List(b22320),Block(Const(())))
    val x34835 = OpDef(op=FixAdd, inputs=List(b22319, Const(1))).name("x34835").ctrl(x34836).srcCtx("NMTDSE.scala:478:34") // FixAdd(b22319,Const(1))
    val x34837 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x34837").ctrl(x36742).srcCtx("NMTDSE.scala:478:24") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x34838 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x34838").ctrl(x36742).srcCtx("NMTDSE.scala:478:24") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x34839 = CounterChain(List(x34837,x34838)).name("x34839").ctrl(x36742).srcCtx("NMTDSE.scala:478:24") // CounterChainNew(List(x34837, x34838))
    val x34869 = LoopController(style=StreamPipe, level=OuterControl, cchain=x34839).name("x34869").ctrl(x36742).srcCtx("NMTDSE.scala:478:24") // UnrolledForeach(List(b22320),x34839,Block(Const(())),List(List(b22326), List(b22327)),List(List(b22328), List(b22329)))
    val b22326 = CounterIter(x34837, Some(0)).name("b22326").ctrl(x34869) // b22326
    val b22328 = Const(true).name("b22328").ctrl(x34869) // b22328
    val b22327 = CounterIter(x34838, Some(0)).name("b22327").ctrl(x34869) // b22327
    val b22329 = Const(true).name("b22329").ctrl(x34869) // b22329
    val b36825 = StreamOut(field="offset").name("b36825").ctrl(x34869).srcCtx("NMTDSE.scala:478:24") // x34840 = StreamOutNew(BurstCmdBus)
    isAccum(b36825) = false
    bufferDepthOf(b36825) = 1
    val b36826 = StreamOut(field="size").name("b36826").ctrl(x34869).srcCtx("NMTDSE.scala:478:24") // x34840 = StreamOutNew(BurstCmdBus)
    isAccum(b36826) = false
    bufferDepthOf(b36826) = 1
    val x34841 = StreamIn(field="data").name("x34841").ctrl(x34869).srcCtx("NMTDSE.scala:478:24") // x34841 = StreamInNew(BurstDataBus())
    isAccum(x34841) = false
    bufferDepthOf(x34841) = 1
    val x34858 = UnitController(style=SeqPipe, level=InnerControl).name("x34858").ctrl(x34869).srcCtx("NMTDSE.scala:478:24") // UnitPipe(List(b22328, b22329, b22320),Block(x34857))
    val x34842 = OpDef(op=FixAdd, inputs=List(b22319, b22327)).name("x34842").ctrl(x34858).srcCtx("NMTDSE.scala:478:24") // FixAdd(b22319,b22327)
    val x34843 = b22326 // FixConvert(b22326,TRUE,_32,_0) (Same Type. No op)
    val x34844 = OpDef(op=FixSla, inputs=List(x34843, Const(11))).name("x34844").ctrl(x34858).srcCtx("NMTDSE.scala:478:24") // FixLsh(x34843,Const(11))
    val x34845 = x34842 // FixConvert(x34842,TRUE,_32,_0) (Same Type. No op)
    val x34846 = OpDef(op=FixSla, inputs=List(x34845, Const(9))).name("x34846").ctrl(x34858).srcCtx("NMTDSE.scala:478:24") // FixLsh(x34845,Const(9))
    val x34847 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x34848 = OpDef(op=FixAdd, inputs=List(x34844, x34846)).name("x34848").ctrl(x34858).srcCtx("NMTDSE.scala:478:24") // FixAdd(x34844,x34846)
    val x34849 = OpDef(op=FixAdd, inputs=List(x34848, x34847)).name("x34849").ctrl(x34858).srcCtx("NMTDSE.scala:478:24") // FixAdd(x34848,x34847)
    val x34850 = OpDef(op=FixSla, inputs=List(x34849, Const(2))).name("x34850").ctrl(x34858).srcCtx("NMTDSE.scala:478:24") // FixLsh(x34849,Const(2))
    val x34851 = x34850 // FixConvert(x34850,TRUE,_64,_0)
    val x34852 = DramAddress(x34364).name("x34852").ctrl(x34858).srcCtx("NMTDSE.scala:478:24") // GetDRAMAddress(x34364)
    val x34854_x34853 = OpDef(op=FixAdd, inputs=List(x34851, x34852)).name("x34854_x34853").ctrl(x34858).srcCtx("NMTDSE.scala:478:24") // FixAdd(x34851,x34852)
    // x34854 = SimpleStruct(ArrayBuffer((offset,x34853), (size,Const(2048)), (isLoad,Const(true))))
    val x34855 = OpDef(op=BitAnd, inputs=List(b22328, b22329)).name("x34855").ctrl(x34858).srcCtx("UnrollingBase.scala:28:66") // And(b22328,b22329)
    val x34856 = OpDef(op=BitAnd, inputs=List(x34855, b22320)).name("x34856").ctrl(x34858).srcCtx("UnrollingBase.scala:28:66") // And(x34855,b22320)
    val x34857_b36827_b36825 = WriteMem(b36825, x34854_x34853).name("x34857_b36827_b36825").ctrl(x34858).srcCtx("NMTDSE.scala:478:24") // StreamWrite(x34840,x34854,x34856)
    val x34857_b36828_b36826 = WriteMem(b36826, Const(2048)).name("x34857_b36828_b36826").ctrl(x34858).srcCtx("NMTDSE.scala:478:24") // StreamWrite(x34840,x34854,x34856)
    val x34859 = FringeDenseLoad(dram=List(x34364), cmdStream=List(b36825, b36826), dataStream=List(x34841)).name("x34859").ctrl(x34869).srcCtx("NMTDSE.scala:478:24") // FringeDenseLoad(x34364,x34840,x34841)
    val x34860 = Counter(min=Const(0), max=Const(512), step=Const(1), par=1).name("x34860").ctrl(x34869).srcCtx("NMTDSE.scala:478:24") // CounterNew(Const(0),Const(512),Const(1),Const(1))
    val x34861 = CounterChain(List(x34860)).name("x34861").ctrl(x34869).srcCtx("NMTDSE.scala:478:24") // CounterChainNew(List(x34860))
    val x34868 = LoopController(style=InnerPipe, level=InnerControl, cchain=x34861).name("x34868").ctrl(x34869).srcCtx("NMTDSE.scala:478:24") // UnrolledForeach(List(b22328, b22329, b22320),x34861,Block(Const(())),List(List(b22352)),List(List(b22353)))
    val b22352 = CounterIter(x34860, None).name("b22352").ctrl(x34868) // b22352
    val b22353 = Const(true).name("b22353").ctrl(x34868) // b22353
    val x34862 = OpDef(op=BitAnd, inputs=List(b22353, b22328)).name("x34862").ctrl(x34868).srcCtx("UnrollingBase.scala:28:66") // And(b22353,b22328)
    val x34863 = OpDef(op=BitAnd, inputs=List(b22329, b22320)).name("x34863").ctrl(x34868).srcCtx("UnrollingBase.scala:28:66") // And(b22329,b22320)
    val x34864 = OpDef(op=BitAnd, inputs=List(x34862, x34863)).name("x34864").ctrl(x34868).srcCtx("UnrollingBase.scala:28:66") // And(x34862,x34863)
    val x34865_x34865 = ReadMem(x34841).name("x34865_x34865").ctrl(x34868).srcCtx("NMTDSE.scala:478:24") // ParStreamRead(x34841,List(x34864))
    val x34866_x34866 = x34865_x34865 // x34866 = VectorApply(x34865,0)
    val x34867 = StoreBanks(List(x34676_d0_b0, x34676_d1_b0, x34676_d2_b0, x34676_d3_b0), List(b22326, b22352), x34866_x34866).name("x34867").ctrl(x34868).srcCtx("NMTDSE.scala:478:24") // ParSRAMStore(x34676,List(List(b22326, b22352)),List(x34866),List(x34864))
    val x34870 = Counter(min=Const(0), max=Const(512), step=Const(16), par=1).name("x34870").ctrl(x36742).srcCtx("CellsPar.scala:190:58") // CounterNew(Const(0),Const(512),Const(16),Const(1))
    val x34871 = Counter(min=Const(0), max=Const(1536), step=Const(16), par=1).name("x34871").ctrl(x36742).srcCtx("CellsPar.scala:190:33") // CounterNew(Const(0),Const(1536),Const(16),Const(1))
    val x34872 = CounterChain(List(x34871,x34870)).name("x34872").ctrl(x36742).srcCtx("CellsPar.scala:190:72") // CounterChainNew(List(x34871, x34870))
    val x34977 = LoopController(style=MetaPipe, level=OuterControl, cchain=x34872).name("x34977").ctrl(x36742).srcCtx("CellsPar.scala:190:72") // UnrolledForeach(List(b22320),x34872,Block(Const(())),List(List(b22365), List(b22366)),List(List(b22367), List(b22368)))
    val b22365 = CounterIter(x34871, Some(0)).name("b22365").ctrl(x34977) // b22365
    val b22367 = Const(true).name("b22367").ctrl(x34977) // b22367
    val b22366 = CounterIter(x34870, Some(0)).name("b22366").ctrl(x34977) // b22366
    val b22368 = Const(true).name("b22368").ctrl(x34977) // b22368
    val x34873_d0_b0 = SRAM(size=256, banking=Strided(banks=1, stride=16)).name("x34873_d0_b0").ctrl(x34977).srcCtx("CellsPar.scala:191:33:tileKernel") // x34873 = SRAMNew(ArrayBuffer(Const(16), Const(16)))
    isAccum(x34873_d0_b0) = false
    bufferDepthOf(x34873_d0_b0) = 2
    val x34876 = UnitController(style=SeqPipe, level=InnerControl).name("x34876").ctrl(x34977).srcCtx("CellsPar.scala:190:72") // UnitPipe(List(b22367, b22368, b22320),Block(Const(())))
    val x34874 = OpDef(op=FixAdd, inputs=List(b22365, Const(16))).name("x34874").ctrl(x34876).srcCtx("CellsPar.scala:192:36") // FixAdd(b22365,Const(16))
    val x34875 = OpDef(op=FixAdd, inputs=List(b22366, Const(16))).name("x34875").ctrl(x34876).srcCtx("CellsPar.scala:192:45") // FixAdd(b22366,Const(16))
    val x34877 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x34877").ctrl(x34977).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x34878 = CounterChain(List(x34877)).name("x34878").ctrl(x34977).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x34877))
    val x34907 = LoopController(style=StreamPipe, level=OuterControl, cchain=x34878).name("x34907").ctrl(x34977).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b22367, b22368, b22320),x34878,Block(Const(())),List(List(b22375)),List(List(b22376)))
    val b22375 = CounterIter(x34877, Some(0)).name("b22375").ctrl(x34907) // b22375
    val b22376 = Const(true).name("b22376").ctrl(x34907) // b22376
    val b36829 = StreamOut(field="offset").name("b36829").ctrl(x34907).srcCtx("CellsPar.scala:192:20") // x34879 = StreamOutNew(BurstCmdBus)
    isAccum(b36829) = false
    bufferDepthOf(b36829) = 1
    val b36830 = StreamOut(field="size").name("b36830").ctrl(x34907).srcCtx("CellsPar.scala:192:20") // x34879 = StreamOutNew(BurstCmdBus)
    isAccum(b36830) = false
    bufferDepthOf(b36830) = 1
    val x34880 = StreamIn(field="data").name("x34880").ctrl(x34907).srcCtx("CellsPar.scala:192:20") // x34880 = StreamInNew(BurstDataBus())
    isAccum(x34880) = false
    bufferDepthOf(x34880) = 1
    val x34895 = UnitController(style=SeqPipe, level=InnerControl).name("x34895").ctrl(x34907).srcCtx("CellsPar.scala:192:20") // UnitPipe(List(b22376, b22367, b22368, b22320),Block(x34894))
    val x34881 = OpDef(op=FixAdd, inputs=List(b22365, b22375)).name("x34881").ctrl(x34895).srcCtx("CellsPar.scala:192:20") // FixAdd(b22365,b22375)
    val x34882 = x34881 // FixConvert(x34881,TRUE,_32,_0) (Same Type. No op)
    val x34883 = OpDef(op=FixSla, inputs=List(x34882, Const(9))).name("x34883").ctrl(x34895).srcCtx("CellsPar.scala:192:20") // FixLsh(x34882,Const(9))
    val x34884 = b22366 // FixConvert(b22366,TRUE,_32,_0) (Same Type. No op)
    val x34885 = OpDef(op=FixAdd, inputs=List(x34883, x34884)).name("x34885").ctrl(x34895).srcCtx("CellsPar.scala:192:20") // FixAdd(x34883,x34884)
    val x34886 = OpDef(op=FixSla, inputs=List(x34885, Const(2))).name("x34886").ctrl(x34895).srcCtx("CellsPar.scala:192:20") // FixLsh(x34885,Const(2))
    val x34887 = x34886 // FixConvert(x34886,TRUE,_64,_0)
    val x34888 = DramAddress(x34400).name("x34888").ctrl(x34895).srcCtx("CellsPar.scala:192:20") // GetDRAMAddress(x34400)
    val x34890_x34889 = OpDef(op=FixAdd, inputs=List(x34887, x34888)).name("x34890_x34889").ctrl(x34895).srcCtx("CellsPar.scala:192:20") // FixAdd(x34887,x34888)
    // x34890 = SimpleStruct(ArrayBuffer((offset,x34889), (size,Const(64)), (isLoad,Const(true))))
    val x34891 = OpDef(op=BitAnd, inputs=List(b22376, b22367)).name("x34891").ctrl(x34895).srcCtx("UnrollingBase.scala:28:66") // And(b22376,b22367)
    val x34892 = OpDef(op=BitAnd, inputs=List(b22368, b22320)).name("x34892").ctrl(x34895).srcCtx("UnrollingBase.scala:28:66") // And(b22368,b22320)
    val x34893 = OpDef(op=BitAnd, inputs=List(x34891, x34892)).name("x34893").ctrl(x34895).srcCtx("UnrollingBase.scala:28:66") // And(x34891,x34892)
    val x34894_b36831_b36829 = WriteMem(b36829, x34890_x34889).name("x34894_b36831_b36829").ctrl(x34895).srcCtx("CellsPar.scala:192:20") // StreamWrite(x34879,x34890,x34893)
    val x34894_b36832_b36830 = WriteMem(b36830, Const(64)).name("x34894_b36832_b36830").ctrl(x34895).srcCtx("CellsPar.scala:192:20") // StreamWrite(x34879,x34890,x34893)
    val x34896 = FringeDenseLoad(dram=List(x34400), cmdStream=List(b36829, b36830), dataStream=List(x34880)).name("x34896").ctrl(x34907).srcCtx("CellsPar.scala:192:20") // FringeDenseLoad(x34400,x34879,x34880)
    val x34897 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x34897").ctrl(x34907).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x34898 = CounterChain(List(x34897)).name("x34898").ctrl(x34907).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x34897))
    val x34906 = LoopController(style=InnerPipe, level=InnerControl, cchain=x34898).name("x34906").ctrl(x34907).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b22376, b22367, b22368, b22320),x34898,Block(Const(())),List(List(b22397)),List(List(b22398)))
    val b22397 = CounterIter(x34897, None).name("b22397").ctrl(x34906) // b22397
    val b22398 = Const(true).name("b22398").ctrl(x34906) // b22398
    val x34899 = OpDef(op=BitAnd, inputs=List(b22398, b22376)).name("x34899").ctrl(x34906).srcCtx("UnrollingBase.scala:28:66") // And(b22398,b22376)
    val x34900 = OpDef(op=BitAnd, inputs=List(b22367, b22368)).name("x34900").ctrl(x34906).srcCtx("UnrollingBase.scala:28:66") // And(b22367,b22368)
    val x34901 = OpDef(op=BitAnd, inputs=List(x34899, x34900)).name("x34901").ctrl(x34906).srcCtx("UnrollingBase.scala:28:66") // And(x34899,x34900)
    val x34902 = OpDef(op=BitAnd, inputs=List(x34901, b22320)).name("x34902").ctrl(x34906).srcCtx("UnrollingBase.scala:28:66") // And(x34901,b22320)
    val x34903_x34903 = ReadMem(x34880).name("x34903_x34903").ctrl(x34906).srcCtx("CellsPar.scala:192:20") // ParStreamRead(x34880,List(x34902))
    val x34904_x34904 = x34903_x34903 // x34904 = VectorApply(x34903,0)
    val x34905 = StoreBanks(List(x34873_d0_b0), List(b22375, b22397), x34904_x34904).name("x34905").ctrl(x34906).srcCtx("CellsPar.scala:192:20") // ParSRAMStore(x34873,List(List(b22375, b22397)),List(x34904),List(x34902))
    val x34908 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x34908").ctrl(x34977).srcCtx("CellsPar.scala:193:34") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x34909 = CounterChain(List(x34908)).name("x34909").ctrl(x34977).srcCtx("CellsPar.scala:193:46") // CounterChainNew(List(x34908))
    val x34976 = LoopController(style=MetaPipe, level=OuterControl, cchain=x34909).name("x34976").ctrl(x34977).srcCtx("CellsPar.scala:193:46") // UnrolledForeach(List(b22367, b22368, b22320),x34909,Block(Const(())),List(List(b22410)),List(List(b22411)))
    val b22410 = CounterIter(x34908, Some(0)).name("b22410").ctrl(x34976) // b22410
    val b22411 = Const(true).name("b22411").ctrl(x34976) // b22411
    val x34910 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x34910").ctrl(x34976).srcCtx("CellsPar.scala:194:28") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x34911 = CounterChain(List(x34910)).name("x34911").ctrl(x34976).srcCtx("CellsPar.scala:194:42") // CounterChainNew(List(x34910))
    val x34975 = LoopController(style=MetaPipe, level=OuterControl, cchain=x34911).name("x34975").ctrl(x34976).srcCtx("CellsPar.scala:194:42") // UnrolledForeach(List(b22411, b22367, b22368, b22320),x34911,Block(Const(())),List(List(b22414)),List(List(b22415)))
    val b22414 = CounterIter(x34910, Some(0)).name("b22414").ctrl(x34975) // b22414
    val b22415 = Const(true).name("b22415").ctrl(x34975) // b22415
    val x34912_d0 = Reg(init=Some(0.0)).name("x34912_d0").ctrl(x34975).srcCtx("CellsPar.scala:195:34:prod") // x34912 = RegNew(Const(0))
    isAccum(x34912_d0) = false
    bufferDepthOf(x34912_d0) = 2
    val x34912_d1 = Reg(init=Some(0.0)).name("x34912_d1").ctrl(x34975).srcCtx("CellsPar.scala:195:34:prod") // x34912 = RegNew(Const(0))
    isAccum(x34912_d1) = true
    bufferDepthOf(x34912_d1) = 1
    val x34913 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x34913").ctrl(x34975).srcCtx("CellsPar.scala:195:43") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x34914 = CounterChain(List(x34913)).name("x34914").ctrl(x34975).srcCtx("CellsPar.scala:207:15") // CounterChainNew(List(x34913))
    val x34940 = LoopController(style=InnerPipe, level=InnerControl, cchain=x34914).name("x34940").ctrl(x34975).srcCtx("CellsPar.scala:207:15") // UnrolledReduce(List(b22415, b22411, b22367, b22368, b22320),x34914,x34912,Block((x34912) => Const(())),List(List(b22419)),List(List(b22420)))
    val b22419 = CounterIter(x34913, None).name("b22419").ctrl(x34940) // b22419
    val b22420 = Const(true).name("b22420").ctrl(x34940) // b22420
    val x34915 = OpDef(op=FixAdd, inputs=List(b22365, b22419)).name("x34915").ctrl(x34940).srcCtx("CellsPar.scala:196:42:reduce_size_offset") // FixAdd(b22365,b22419)
    val x34916 = OpDef(op=BitAnd, inputs=List(b22420, b22415)).name("x34916").ctrl(x34940).srcCtx("UnrollingBase.scala:28:66") // And(b22420,b22415)
    val x34917 = OpDef(op=BitAnd, inputs=List(b22411, b22367)).name("x34917").ctrl(x34940).srcCtx("UnrollingBase.scala:28:66") // And(b22411,b22367)
    val x34918 = OpDef(op=BitAnd, inputs=List(b22368, b22320)).name("x34918").ctrl(x34940).srcCtx("UnrollingBase.scala:28:66") // And(b22368,b22320)
    val x34919 = OpDef(op=BitAnd, inputs=List(x34916, x34917)).name("x34919").ctrl(x34940).srcCtx("UnrollingBase.scala:28:66") // And(x34916,x34917)
    val x34920 = OpDef(op=BitAnd, inputs=List(x34919, x34918)).name("x34920").ctrl(x34940).srcCtx("UnrollingBase.scala:28:66") // And(x34919,x34918)
    val x34921 = LoadBanks(List(x34873_d0_b0), List(b22419, b22414)).name("x34921").ctrl(x34940).srcCtx("CellsPar.scala:203:34:tk") // ParSRAMLoad(x34873,List(List(b22419, b22414)),List(x34920))
    val x34922 = x34921 // x34922 = VectorApply(x34921,0)
    val x34923 = LoadBanks(List(x34676_d3_b0), List(b22410, x34915)).name("x34923").ctrl(x34940).srcCtx("CellsPar.scala:204:28") // ParSRAMLoad(x34676,List(List(b22410, x34915)),List(x34920))
    val x34924 = x34923 // x34924 = VectorApply(x34923,0)
    val x34925 = OpDef(op=FixMul, inputs=List(x34924, x34922)).name("x34925").ctrl(x34940).srcCtx("CellsPar.scala:204:52:tempA") // FixMul(x34924,x34922)
    val x34926 = OpDef(op=FixSub, inputs=List(x34915, Const(512))).name("x34926").ctrl(x34940).srcCtx("CellsPar.scala:205:51") // FixSub(x34915,Const(512))
    val x34927 = LoadBanks(List(x34677_d7_b0), List(b22410, x34926)).name("x34927").ctrl(x34940).srcCtx("CellsPar.scala:205:28") // ParSRAMLoad(x34677,List(List(b22410, x34926)),List(x34920))
    val x34928 = x34927 // x34928 = VectorApply(x34927,0)
    val x34929 = OpDef(op=FixMul, inputs=List(x34928, x34922)).name("x34929").ctrl(x34940).srcCtx("CellsPar.scala:205:67:tempB") // FixMul(x34928,x34922)
    val x34930 = OpDef(op=FixLt, inputs=List(x34915, Const(512))).name("x34930").ctrl(x34940).srcCtx("CellsPar.scala:206:38") // FixLt(x34915,Const(512))
    val x34931 = OpDef(op=MuxOp, inputs=List(x34930, x34925, x34929)).name("x34931").ctrl(x34940).srcCtx("CellsPar.scala:206:18") // Mux(x34930,x34925,x34929)
    val x34932 = ReadMem(x34912_d1).name("x34932").ctrl(x34940).srcCtx("CellsPar.scala:207:15") // RegRead(x34912)
    val x34933 = OpDef(op=FixEql, inputs=List(b22419, Const(0))).name("x34933").ctrl(x34940).srcCtx("CellsPar.scala:207:15") // FixEql(b22419,Const(0))
    val x34934 = ReduceAccumOp(op=FixAdd, input=x34931, accum=x34932).name("x34934").ctrl(x34940).srcCtx("CellsPar.scala:207:17") // FixAdd(x34931,x34932)
    val x34935 = OpDef(op=BitAnd, inputs=List(b22415, b22411)).name("x34935").ctrl(x34940).srcCtx("UnrollingBase.scala:28:66") // And(b22415,b22411)
    val x34936 = OpDef(op=BitAnd, inputs=List(b22367, b22368)).name("x34936").ctrl(x34940).srcCtx("UnrollingBase.scala:28:66") // And(b22367,b22368)
    val x34937 = OpDef(op=BitAnd, inputs=List(x34935, x34936)).name("x34937").ctrl(x34940).srcCtx("UnrollingBase.scala:28:66") // And(x34935,x34936)
    val x34938 = OpDef(op=BitAnd, inputs=List(x34937, b22320)).name("x34938").ctrl(x34940).srcCtx("UnrollingBase.scala:28:66") // And(x34937,b22320)
    val x34939_x34912_d0 = WriteMem(x34912_d0, x34934).name("x34939_x34912_d0").ctrl(x34940).srcCtx("CellsPar.scala:207:15") // RegWrite(x34912,x34934,x34938)
    val x34939_x34912_d1 = WriteMem(x34912_d1, x34934).name("x34939_x34912_d1").ctrl(x34940).srcCtx("CellsPar.scala:207:15") // RegWrite(x34912,x34934,x34938)
    val x34974 = UnitController(style=SeqPipe, level=InnerControl).name("x34974").ctrl(x34975).srcCtx("CellsPar.scala:194:42") // UnitPipe(List(b22415, b22411, b22367, b22368, b22320),Block(Const(())))
    val x34941 = OpDef(op=FixAdd, inputs=List(b22366, b22414)).name("x34941").ctrl(x34974).srcCtx("CellsPar.scala:209:31:colOffset") // FixAdd(b22366,b22414)
    val x34942 = ReadMem(x34912_d0).name("x34942").ctrl(x34974).srcCtx("CellsPar.scala:210:28") // RegRead(x34912)
    val x34943 = OpDef(op=FixEql, inputs=List(b22365, Const(0))).name("x34943").ctrl(x34974).srcCtx("CellsPar.scala:210:42") // FixEql(b22365,Const(0))
    val x34944 = OpDef(op=BitAnd, inputs=List(b22415, b22411)).name("x34944").ctrl(x34974).srcCtx("UnrollingBase.scala:28:66") // And(b22415,b22411)
    val x34945 = OpDef(op=BitAnd, inputs=List(b22367, b22368)).name("x34945").ctrl(x34974).srcCtx("UnrollingBase.scala:28:66") // And(b22367,b22368)
    val x34946 = OpDef(op=BitAnd, inputs=List(x34944, x34945)).name("x34946").ctrl(x34974).srcCtx("UnrollingBase.scala:28:66") // And(x34944,x34945)
    val x34947 = OpDef(op=BitAnd, inputs=List(x34946, b22320)).name("x34947").ctrl(x34974).srcCtx("UnrollingBase.scala:28:66") // And(x34946,b22320)
    val x34948 = LoadBanks(List(x34683_d3_b0), List(Const(0), x34941)).name("x34948").ctrl(x34974).srcCtx("CellsPar.scala:210:52") // SRAMLoad(x34683,ArrayBuffer(Const(1), Const(2048)),List(Const(0), x34941),Const(0),x34947)
    val x34949 = LoadBanks(List(x34679_d1_b0), List(b22410, x34941)).name("x34949").ctrl(x34974).srcCtx("CellsPar.scala:210:91") // SRAMLoad(x34679,ArrayBuffer(Const(1), Const(512)),List(b22410, x34941),Const(0),x34947)
    val x34950 = OpDef(op=MuxOp, inputs=List(x34943, x34948, x34949)).name("x34950").ctrl(x34974).srcCtx("CellsPar.scala:210:39") // Mux(x34943,x34948,x34949)
    val x34951 = OpDef(op=FixAdd, inputs=List(x34942, x34950)).name("x34951").ctrl(x34974).srcCtx("CellsPar.scala:210:34:ele") // FixAdd(x34942,x34950)
    val x34952 = OpDef(op=FixLeq, inputs=List(Const(1520), b22365)).name("x34952").ctrl(x34974).srcCtx("CellsPar.scala:218:46") // FixLeq(Const(1520),b22365)
    // x34953 = FixConvert(Const(0),TRUE,_8,_24) x.tp=FixPt[TRUE,_32,_0] {
    val x34953_int1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("01111111111111111111111111111111"))).name("x34953_int1").ctrl(x34974).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x34953_int2 = OpDef(op=FixSla, inputs=List(x34953_int1, Const(24))).name("x34953_int2").ctrl(x34974).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x34953_frac1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("00000000000000000000000000000000"))).name("x34953_frac1").ctrl(x34974).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x34953_frac2 = OpDef(op=FixSla, inputs=List(x34953_frac1, Const(24))).name("x34953_frac2").ctrl(x34974).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x34953 = OpDef(op=BitOr, inputs=List(x34953_int2, x34953_frac2)).name("x34953").ctrl(x34974).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    // }
    val x34954 = OpDef(op=FixAdd, inputs=List(x34951, x34953)).name("x34954").ctrl(x34974).srcCtx("CellsPar.scala:218:87") // FixAdd(x34951,x34953)
    val x34955 = OpDef(op=FixSra, inputs=List(x34954, Const(1))).name("x34955").ctrl(x34974).srcCtx("CellsPar.scala:29:22") // FixRsh(x34954,Const(1))
    val x34956 = x34955 // FixConvert(x34955,TRUE,_8,_24) (Same Type. No op)
    val x34957 = OpDef(op=FixAbs, inputs=List(x34956)).name("x34957").ctrl(x34974).srcCtx("CellsPar.scala:37:19:absp") // FixAbs(x34956)
    val x34958 = OpDef(op=FixLt, inputs=List(Const(2.5), x34957)).name("x34958").ctrl(x34974).srcCtx("CellsPar.scala:51:23:cond1") // FixLt(Const(2.5),x34957)
    val x34959 = OpDef(op=FixLt, inputs=List(Const(0.5), x34957)).name("x34959").ctrl(x34974).srcCtx("CellsPar.scala:54:35") // FixLt(Const(0.5),x34957)
    val x34960 = OpDef(op=FixLeq, inputs=List(x34957, Const(2.5))).name("x34960").ctrl(x34974).srcCtx("CellsPar.scala:54:52") // FixLeq(x34957,Const(2.5))
    val x34961 = OpDef(op=BitAnd, inputs=List(x34959, x34960)).name("x34961").ctrl(x34974).srcCtx("CellsPar.scala:54:43:cond2") // And(x34959,x34960)
    val x34962 = OpDef(op=FixSra, inputs=List(x34957, Const(2))).name("x34962").ctrl(x34974).srcCtx("CellsPar.scala:56:21:div4") // FixRsh(x34957,Const(2))
    val x34963 = OpDef(op=FixAdd, inputs=List(x34962, Const(0.375))).name("x34963").ctrl(x34974).srcCtx("CellsPar.scala:57:27:div4Offset") // FixAdd(x34962,Const(0.375))
    val x34964 = OpDef(op=MuxOp, inputs=List(x34961, x34963, x34957)).name("x34964").ctrl(x34974).srcCtx("CellsPar.scala:58:20:body2") // Mux(x34961,x34963,x34957)
    val x34965 = OpDef(op=MuxOp, inputs=List(x34958, Const(1.0), x34964)).name("x34965").ctrl(x34974).srcCtx("CellsPar.scala:60:20:absre") // Mux(x34958,Const(1),x34964)
    val x34966 = OpDef(op=FixNeg, inputs=List(x34965)).name("x34966").ctrl(x34974).srcCtx("CellsPar.scala:62:29:mabre") // FixNeg(x34965)
    val x34967 = OpDef(op=FixLt, inputs=List(x34956, Const(0.0))).name("x34967").ctrl(x34974).srcCtx("CellsPar.scala:63:22") // FixLt(x34956,Const(0))
    val x34968 = OpDef(op=MuxOp, inputs=List(x34967, x34966, x34965)).name("x34968").ctrl(x34974).srcCtx("CellsPar.scala:63:17:re") // Mux(x34967,x34966,x34965)
    val x34969 = x34968 // FixConvert(x34968,TRUE,_8,_24) (Same Type. No op)
    val x34970 = OpDef(op=FixAdd, inputs=List(x34969, Const(1.0))).name("x34970").ctrl(x34974).srcCtx("CellsPar.scala:29:33") // FixAdd(x34969,Const(1))
    val x34971 = OpDef(op=FixSra, inputs=List(x34970, Const(1))).name("x34971").ctrl(x34974).srcCtx("CellsPar.scala:29:44") // FixRsh(x34970,Const(1))
    val x34972 = OpDef(op=MuxOp, inputs=List(x34952, x34971, x34951)).name("x34972").ctrl(x34974).srcCtx("CellsPar.scala:218:43") // Mux(x34952,x34971,x34951)
    val x34973 = StoreBanks(List(x34679_d0_b0, x34679_d1_b0), List(b22410, x34941), x34972).name("x34973").ctrl(x34974).srcCtx("CellsPar.scala:218:38") // SRAMStore(x34679,ArrayBuffer(Const(1), Const(512)),List(b22410, x34941),Const(0),x34972,x34947)
    val x34978 = Counter(min=Const(0), max=Const(512), step=Const(16), par=1).name("x34978").ctrl(x36742).srcCtx("CellsPar.scala:190:58") // CounterNew(Const(0),Const(512),Const(16),Const(1))
    val x34979 = Counter(min=Const(0), max=Const(1536), step=Const(16), par=1).name("x34979").ctrl(x36742).srcCtx("CellsPar.scala:190:33") // CounterNew(Const(0),Const(1536),Const(16),Const(1))
    val x34980 = CounterChain(List(x34979,x34978)).name("x34980").ctrl(x36742).srcCtx("CellsPar.scala:190:72") // CounterChainNew(List(x34979, x34978))
    val x35083 = LoopController(style=MetaPipe, level=OuterControl, cchain=x34980).name("x35083").ctrl(x36742).srcCtx("CellsPar.scala:190:72") // UnrolledForeach(List(b22320),x34980,Block(Const(())),List(List(b22487), List(b22488)),List(List(b22489), List(b22490)))
    val b22487 = CounterIter(x34979, Some(0)).name("b22487").ctrl(x35083) // b22487
    val b22489 = Const(true).name("b22489").ctrl(x35083) // b22489
    val b22488 = CounterIter(x34978, Some(0)).name("b22488").ctrl(x35083) // b22488
    val b22490 = Const(true).name("b22490").ctrl(x35083) // b22490
    val x34981_d0_b0 = SRAM(size=256, banking=Strided(banks=1, stride=16)).name("x34981_d0_b0").ctrl(x35083).srcCtx("CellsPar.scala:191:33:tileKernel") // x34981 = SRAMNew(ArrayBuffer(Const(16), Const(16)))
    isAccum(x34981_d0_b0) = false
    bufferDepthOf(x34981_d0_b0) = 2
    val x34984 = UnitController(style=SeqPipe, level=InnerControl).name("x34984").ctrl(x35083).srcCtx("CellsPar.scala:190:72") // UnitPipe(List(b22489, b22490, b22320),Block(Const(())))
    val x34982 = OpDef(op=FixAdd, inputs=List(b22487, Const(16))).name("x34982").ctrl(x34984).srcCtx("CellsPar.scala:192:36") // FixAdd(b22487,Const(16))
    val x34983 = OpDef(op=FixAdd, inputs=List(b22488, Const(16))).name("x34983").ctrl(x34984).srcCtx("CellsPar.scala:192:45") // FixAdd(b22488,Const(16))
    val x34985 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x34985").ctrl(x35083).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x34986 = CounterChain(List(x34985)).name("x34986").ctrl(x35083).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x34985))
    val x35015 = LoopController(style=StreamPipe, level=OuterControl, cchain=x34986).name("x35015").ctrl(x35083).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b22489, b22490, b22320),x34986,Block(Const(())),List(List(b22497)),List(List(b22498)))
    val b22497 = CounterIter(x34985, Some(0)).name("b22497").ctrl(x35015) // b22497
    val b22498 = Const(true).name("b22498").ctrl(x35015) // b22498
    val b36833 = StreamOut(field="offset").name("b36833").ctrl(x35015).srcCtx("CellsPar.scala:192:20") // x34987 = StreamOutNew(BurstCmdBus)
    isAccum(b36833) = false
    bufferDepthOf(b36833) = 1
    val b36834 = StreamOut(field="size").name("b36834").ctrl(x35015).srcCtx("CellsPar.scala:192:20") // x34987 = StreamOutNew(BurstCmdBus)
    isAccum(b36834) = false
    bufferDepthOf(b36834) = 1
    val x34988 = StreamIn(field="data").name("x34988").ctrl(x35015).srcCtx("CellsPar.scala:192:20") // x34988 = StreamInNew(BurstDataBus())
    isAccum(x34988) = false
    bufferDepthOf(x34988) = 1
    val x35003 = UnitController(style=SeqPipe, level=InnerControl).name("x35003").ctrl(x35015).srcCtx("CellsPar.scala:192:20") // UnitPipe(List(b22498, b22489, b22490, b22320),Block(x35002))
    val x34989 = OpDef(op=FixAdd, inputs=List(b22487, b22497)).name("x34989").ctrl(x35003).srcCtx("CellsPar.scala:192:20") // FixAdd(b22487,b22497)
    val x34990 = x34989 // FixConvert(x34989,TRUE,_32,_0) (Same Type. No op)
    val x34991 = OpDef(op=FixSla, inputs=List(x34990, Const(9))).name("x34991").ctrl(x35003).srcCtx("CellsPar.scala:192:20") // FixLsh(x34990,Const(9))
    val x34992 = b22488 // FixConvert(b22488,TRUE,_32,_0) (Same Type. No op)
    val x34993 = OpDef(op=FixAdd, inputs=List(x34991, x34992)).name("x34993").ctrl(x35003).srcCtx("CellsPar.scala:192:20") // FixAdd(x34991,x34992)
    val x34994 = OpDef(op=FixSla, inputs=List(x34993, Const(2))).name("x34994").ctrl(x35003).srcCtx("CellsPar.scala:192:20") // FixLsh(x34993,Const(2))
    val x34995 = x34994 // FixConvert(x34994,TRUE,_64,_0)
    val x34996 = DramAddress(x34401).name("x34996").ctrl(x35003).srcCtx("CellsPar.scala:192:20") // GetDRAMAddress(x34401)
    val x34998_x34997 = OpDef(op=FixAdd, inputs=List(x34995, x34996)).name("x34998_x34997").ctrl(x35003).srcCtx("CellsPar.scala:192:20") // FixAdd(x34995,x34996)
    // x34998 = SimpleStruct(ArrayBuffer((offset,x34997), (size,Const(64)), (isLoad,Const(true))))
    val x34999 = OpDef(op=BitAnd, inputs=List(b22498, b22489)).name("x34999").ctrl(x35003).srcCtx("UnrollingBase.scala:28:66") // And(b22498,b22489)
    val x35000 = OpDef(op=BitAnd, inputs=List(b22490, b22320)).name("x35000").ctrl(x35003).srcCtx("UnrollingBase.scala:28:66") // And(b22490,b22320)
    val x35001 = OpDef(op=BitAnd, inputs=List(x34999, x35000)).name("x35001").ctrl(x35003).srcCtx("UnrollingBase.scala:28:66") // And(x34999,x35000)
    val x35002_b36835_b36833 = WriteMem(b36833, x34998_x34997).name("x35002_b36835_b36833").ctrl(x35003).srcCtx("CellsPar.scala:192:20") // StreamWrite(x34987,x34998,x35001)
    val x35002_b36836_b36834 = WriteMem(b36834, Const(64)).name("x35002_b36836_b36834").ctrl(x35003).srcCtx("CellsPar.scala:192:20") // StreamWrite(x34987,x34998,x35001)
    val x35004 = FringeDenseLoad(dram=List(x34401), cmdStream=List(b36833, b36834), dataStream=List(x34988)).name("x35004").ctrl(x35015).srcCtx("CellsPar.scala:192:20") // FringeDenseLoad(x34401,x34987,x34988)
    val x35005 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x35005").ctrl(x35015).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x35006 = CounterChain(List(x35005)).name("x35006").ctrl(x35015).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x35005))
    val x35014 = LoopController(style=InnerPipe, level=InnerControl, cchain=x35006).name("x35014").ctrl(x35015).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b22498, b22489, b22490, b22320),x35006,Block(Const(())),List(List(b22519)),List(List(b22520)))
    val b22519 = CounterIter(x35005, None).name("b22519").ctrl(x35014) // b22519
    val b22520 = Const(true).name("b22520").ctrl(x35014) // b22520
    val x35007 = OpDef(op=BitAnd, inputs=List(b22520, b22498)).name("x35007").ctrl(x35014).srcCtx("UnrollingBase.scala:28:66") // And(b22520,b22498)
    val x35008 = OpDef(op=BitAnd, inputs=List(b22489, b22490)).name("x35008").ctrl(x35014).srcCtx("UnrollingBase.scala:28:66") // And(b22489,b22490)
    val x35009 = OpDef(op=BitAnd, inputs=List(x35007, x35008)).name("x35009").ctrl(x35014).srcCtx("UnrollingBase.scala:28:66") // And(x35007,x35008)
    val x35010 = OpDef(op=BitAnd, inputs=List(x35009, b22320)).name("x35010").ctrl(x35014).srcCtx("UnrollingBase.scala:28:66") // And(x35009,b22320)
    val x35011_x35011 = ReadMem(x34988).name("x35011_x35011").ctrl(x35014).srcCtx("CellsPar.scala:192:20") // ParStreamRead(x34988,List(x35010))
    val x35012_x35012 = x35011_x35011 // x35012 = VectorApply(x35011,0)
    val x35013 = StoreBanks(List(x34981_d0_b0), List(b22497, b22519), x35012_x35012).name("x35013").ctrl(x35014).srcCtx("CellsPar.scala:192:20") // ParSRAMStore(x34981,List(List(b22497, b22519)),List(x35012),List(x35010))
    val x35016 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x35016").ctrl(x35083).srcCtx("CellsPar.scala:193:34") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x35017 = CounterChain(List(x35016)).name("x35017").ctrl(x35083).srcCtx("CellsPar.scala:193:46") // CounterChainNew(List(x35016))
    val x35082 = LoopController(style=MetaPipe, level=OuterControl, cchain=x35017).name("x35082").ctrl(x35083).srcCtx("CellsPar.scala:193:46") // UnrolledForeach(List(b22489, b22490, b22320),x35017,Block(Const(())),List(List(b22532)),List(List(b22533)))
    val b22532 = CounterIter(x35016, Some(0)).name("b22532").ctrl(x35082) // b22532
    val b22533 = Const(true).name("b22533").ctrl(x35082) // b22533
    val x35018 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x35018").ctrl(x35082).srcCtx("CellsPar.scala:194:28") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x35019 = CounterChain(List(x35018)).name("x35019").ctrl(x35082).srcCtx("CellsPar.scala:194:42") // CounterChainNew(List(x35018))
    val x35081 = LoopController(style=MetaPipe, level=OuterControl, cchain=x35019).name("x35081").ctrl(x35082).srcCtx("CellsPar.scala:194:42") // UnrolledForeach(List(b22533, b22489, b22490, b22320),x35019,Block(Const(())),List(List(b22536)),List(List(b22537)))
    val b22536 = CounterIter(x35018, Some(0)).name("b22536").ctrl(x35081) // b22536
    val b22537 = Const(true).name("b22537").ctrl(x35081) // b22537
    val x35020_d0 = Reg(init=Some(0.0)).name("x35020_d0").ctrl(x35081).srcCtx("CellsPar.scala:195:34:prod") // x35020 = RegNew(Const(0))
    isAccum(x35020_d0) = false
    bufferDepthOf(x35020_d0) = 2
    val x35020_d1 = Reg(init=Some(0.0)).name("x35020_d1").ctrl(x35081).srcCtx("CellsPar.scala:195:34:prod") // x35020 = RegNew(Const(0))
    isAccum(x35020_d1) = true
    bufferDepthOf(x35020_d1) = 1
    val x35021 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x35021").ctrl(x35081).srcCtx("CellsPar.scala:195:43") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x35022 = CounterChain(List(x35021)).name("x35022").ctrl(x35081).srcCtx("CellsPar.scala:207:15") // CounterChainNew(List(x35021))
    val x35048 = LoopController(style=InnerPipe, level=InnerControl, cchain=x35022).name("x35048").ctrl(x35081).srcCtx("CellsPar.scala:207:15") // UnrolledReduce(List(b22537, b22533, b22489, b22490, b22320),x35022,x35020,Block((x35020) => Const(())),List(List(b22541)),List(List(b22542)))
    val b22541 = CounterIter(x35021, None).name("b22541").ctrl(x35048) // b22541
    val b22542 = Const(true).name("b22542").ctrl(x35048) // b22542
    val x35023 = OpDef(op=FixAdd, inputs=List(b22487, b22541)).name("x35023").ctrl(x35048).srcCtx("CellsPar.scala:196:42:reduce_size_offset") // FixAdd(b22487,b22541)
    val x35024 = OpDef(op=BitAnd, inputs=List(b22542, b22537)).name("x35024").ctrl(x35048).srcCtx("UnrollingBase.scala:28:66") // And(b22542,b22537)
    val x35025 = OpDef(op=BitAnd, inputs=List(b22533, b22489)).name("x35025").ctrl(x35048).srcCtx("UnrollingBase.scala:28:66") // And(b22533,b22489)
    val x35026 = OpDef(op=BitAnd, inputs=List(b22490, b22320)).name("x35026").ctrl(x35048).srcCtx("UnrollingBase.scala:28:66") // And(b22490,b22320)
    val x35027 = OpDef(op=BitAnd, inputs=List(x35024, x35025)).name("x35027").ctrl(x35048).srcCtx("UnrollingBase.scala:28:66") // And(x35024,x35025)
    val x35028 = OpDef(op=BitAnd, inputs=List(x35027, x35026)).name("x35028").ctrl(x35048).srcCtx("UnrollingBase.scala:28:66") // And(x35027,x35026)
    val x35029 = LoadBanks(List(x34981_d0_b0), List(b22541, b22536)).name("x35029").ctrl(x35048).srcCtx("CellsPar.scala:203:34:tk") // ParSRAMLoad(x34981,List(List(b22541, b22536)),List(x35028))
    val x35030 = x35029 // x35030 = VectorApply(x35029,0)
    val x35031 = LoadBanks(List(x34676_d2_b0), List(b22532, x35023)).name("x35031").ctrl(x35048).srcCtx("CellsPar.scala:204:28") // ParSRAMLoad(x34676,List(List(b22532, x35023)),List(x35028))
    val x35032 = x35031 // x35032 = VectorApply(x35031,0)
    val x35033 = OpDef(op=FixMul, inputs=List(x35032, x35030)).name("x35033").ctrl(x35048).srcCtx("CellsPar.scala:204:52:tempA") // FixMul(x35032,x35030)
    val x35034 = OpDef(op=FixSub, inputs=List(x35023, Const(512))).name("x35034").ctrl(x35048).srcCtx("CellsPar.scala:205:51") // FixSub(x35023,Const(512))
    val x35035 = LoadBanks(List(x34677_d6_b0), List(b22532, x35034)).name("x35035").ctrl(x35048).srcCtx("CellsPar.scala:205:28") // ParSRAMLoad(x34677,List(List(b22532, x35034)),List(x35028))
    val x35036 = x35035 // x35036 = VectorApply(x35035,0)
    val x35037 = OpDef(op=FixMul, inputs=List(x35036, x35030)).name("x35037").ctrl(x35048).srcCtx("CellsPar.scala:205:67:tempB") // FixMul(x35036,x35030)
    val x35038 = OpDef(op=FixLt, inputs=List(x35023, Const(512))).name("x35038").ctrl(x35048).srcCtx("CellsPar.scala:206:38") // FixLt(x35023,Const(512))
    val x35039 = OpDef(op=MuxOp, inputs=List(x35038, x35033, x35037)).name("x35039").ctrl(x35048).srcCtx("CellsPar.scala:206:18") // Mux(x35038,x35033,x35037)
    val x35040 = ReadMem(x35020_d1).name("x35040").ctrl(x35048).srcCtx("CellsPar.scala:207:15") // RegRead(x35020)
    val x35041 = OpDef(op=FixEql, inputs=List(b22541, Const(0))).name("x35041").ctrl(x35048).srcCtx("CellsPar.scala:207:15") // FixEql(b22541,Const(0))
    val x35042 = ReduceAccumOp(op=FixAdd, input=x35039, accum=x35040).name("x35042").ctrl(x35048).srcCtx("CellsPar.scala:207:17") // FixAdd(x35039,x35040)
    val x35043 = OpDef(op=BitAnd, inputs=List(b22537, b22533)).name("x35043").ctrl(x35048).srcCtx("UnrollingBase.scala:28:66") // And(b22537,b22533)
    val x35044 = OpDef(op=BitAnd, inputs=List(b22489, b22490)).name("x35044").ctrl(x35048).srcCtx("UnrollingBase.scala:28:66") // And(b22489,b22490)
    val x35045 = OpDef(op=BitAnd, inputs=List(x35043, x35044)).name("x35045").ctrl(x35048).srcCtx("UnrollingBase.scala:28:66") // And(x35043,x35044)
    val x35046 = OpDef(op=BitAnd, inputs=List(x35045, b22320)).name("x35046").ctrl(x35048).srcCtx("UnrollingBase.scala:28:66") // And(x35045,b22320)
    val x35047_x35020_d0 = WriteMem(x35020_d0, x35042).name("x35047_x35020_d0").ctrl(x35048).srcCtx("CellsPar.scala:207:15") // RegWrite(x35020,x35042,x35046)
    val x35047_x35020_d1 = WriteMem(x35020_d1, x35042).name("x35047_x35020_d1").ctrl(x35048).srcCtx("CellsPar.scala:207:15") // RegWrite(x35020,x35042,x35046)
    val x35080 = UnitController(style=SeqPipe, level=InnerControl).name("x35080").ctrl(x35081).srcCtx("CellsPar.scala:194:42") // UnitPipe(List(b22537, b22533, b22489, b22490, b22320),Block(Const(())))
    val x35049 = OpDef(op=FixAdd, inputs=List(b22488, b22536)).name("x35049").ctrl(x35080).srcCtx("CellsPar.scala:209:31:colOffset") // FixAdd(b22488,b22536)
    val x35050 = ReadMem(x35020_d0).name("x35050").ctrl(x35080).srcCtx("CellsPar.scala:210:28") // RegRead(x35020)
    val x35051 = OpDef(op=FixEql, inputs=List(b22487, Const(0))).name("x35051").ctrl(x35080).srcCtx("CellsPar.scala:210:42") // FixEql(b22487,Const(0))
    val x35052 = OpDef(op=FixAdd, inputs=List(x35049, Const(512))).name("x35052").ctrl(x35080).srcCtx("CellsPar.scala:210:66") // FixAdd(x35049,Const(512))
    val x35053 = OpDef(op=BitAnd, inputs=List(b22537, b22533)).name("x35053").ctrl(x35080).srcCtx("UnrollingBase.scala:28:66") // And(b22537,b22533)
    val x35054 = OpDef(op=BitAnd, inputs=List(b22489, b22490)).name("x35054").ctrl(x35080).srcCtx("UnrollingBase.scala:28:66") // And(b22489,b22490)
    val x35055 = OpDef(op=BitAnd, inputs=List(x35053, x35054)).name("x35055").ctrl(x35080).srcCtx("UnrollingBase.scala:28:66") // And(x35053,x35054)
    val x35056 = OpDef(op=BitAnd, inputs=List(x35055, b22320)).name("x35056").ctrl(x35080).srcCtx("UnrollingBase.scala:28:66") // And(x35055,b22320)
    val x35057 = LoadBanks(List(x34683_d2_b0), List(Const(0), x35052)).name("x35057").ctrl(x35080).srcCtx("CellsPar.scala:210:52") // SRAMLoad(x34683,ArrayBuffer(Const(1), Const(2048)),List(Const(0), x35052),Const(0),x35056)
    val x35058 = LoadBanks(List(x34680_d1_b0), List(b22532, x35049)).name("x35058").ctrl(x35080).srcCtx("CellsPar.scala:210:91") // SRAMLoad(x34680,ArrayBuffer(Const(1), Const(512)),List(b22532, x35049),Const(0),x35056)
    val x35059 = OpDef(op=MuxOp, inputs=List(x35051, x35057, x35058)).name("x35059").ctrl(x35080).srcCtx("CellsPar.scala:210:39") // Mux(x35051,x35057,x35058)
    val x35060 = OpDef(op=FixAdd, inputs=List(x35050, x35059)).name("x35060").ctrl(x35080).srcCtx("CellsPar.scala:210:34:ele") // FixAdd(x35050,x35059)
    val x35061 = OpDef(op=FixLeq, inputs=List(Const(1520), b22487)).name("x35061").ctrl(x35080).srcCtx("CellsPar.scala:218:46") // FixLeq(Const(1520),b22487)
    // x35062 = FixConvert(Const(0),TRUE,_8,_24) x.tp=FixPt[TRUE,_32,_0] {
    val x35062_int1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("01111111111111111111111111111111"))).name("x35062_int1").ctrl(x35080).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x35062_int2 = OpDef(op=FixSla, inputs=List(x35062_int1, Const(24))).name("x35062_int2").ctrl(x35080).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x35062_frac1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("00000000000000000000000000000000"))).name("x35062_frac1").ctrl(x35080).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x35062_frac2 = OpDef(op=FixSla, inputs=List(x35062_frac1, Const(24))).name("x35062_frac2").ctrl(x35080).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x35062 = OpDef(op=BitOr, inputs=List(x35062_int2, x35062_frac2)).name("x35062").ctrl(x35080).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    // }
    val x35063 = OpDef(op=FixAdd, inputs=List(x35060, x35062)).name("x35063").ctrl(x35080).srcCtx("CellsPar.scala:218:87") // FixAdd(x35060,x35062)
    val x35064 = x35063 // FixConvert(x35063,TRUE,_8,_24) (Same Type. No op)
    val x35065 = OpDef(op=FixAbs, inputs=List(x35064)).name("x35065").ctrl(x35080).srcCtx("CellsPar.scala:37:19:absp") // FixAbs(x35064)
    val x35066 = OpDef(op=FixLt, inputs=List(Const(2.5), x35065)).name("x35066").ctrl(x35080).srcCtx("CellsPar.scala:51:23:cond1") // FixLt(Const(2.5),x35065)
    val x35067 = OpDef(op=FixLt, inputs=List(Const(0.5), x35065)).name("x35067").ctrl(x35080).srcCtx("CellsPar.scala:54:35") // FixLt(Const(0.5),x35065)
    val x35068 = OpDef(op=FixLeq, inputs=List(x35065, Const(2.5))).name("x35068").ctrl(x35080).srcCtx("CellsPar.scala:54:52") // FixLeq(x35065,Const(2.5))
    val x35069 = OpDef(op=BitAnd, inputs=List(x35067, x35068)).name("x35069").ctrl(x35080).srcCtx("CellsPar.scala:54:43:cond2") // And(x35067,x35068)
    val x35070 = OpDef(op=FixSra, inputs=List(x35065, Const(2))).name("x35070").ctrl(x35080).srcCtx("CellsPar.scala:56:21:div4") // FixRsh(x35065,Const(2))
    val x35071 = OpDef(op=FixAdd, inputs=List(x35070, Const(0.375))).name("x35071").ctrl(x35080).srcCtx("CellsPar.scala:57:27:div4Offset") // FixAdd(x35070,Const(0.375))
    val x35072 = OpDef(op=MuxOp, inputs=List(x35069, x35071, x35065)).name("x35072").ctrl(x35080).srcCtx("CellsPar.scala:58:20:body2") // Mux(x35069,x35071,x35065)
    val x35073 = OpDef(op=MuxOp, inputs=List(x35066, Const(1.0), x35072)).name("x35073").ctrl(x35080).srcCtx("CellsPar.scala:60:20:absre") // Mux(x35066,Const(1),x35072)
    val x35074 = OpDef(op=FixNeg, inputs=List(x35073)).name("x35074").ctrl(x35080).srcCtx("CellsPar.scala:62:29:mabre") // FixNeg(x35073)
    val x35075 = OpDef(op=FixLt, inputs=List(x35064, Const(0.0))).name("x35075").ctrl(x35080).srcCtx("CellsPar.scala:63:22") // FixLt(x35064,Const(0))
    val x35076 = OpDef(op=MuxOp, inputs=List(x35075, x35074, x35073)).name("x35076").ctrl(x35080).srcCtx("CellsPar.scala:63:17:re") // Mux(x35075,x35074,x35073)
    val x35077 = x35076 // FixConvert(x35076,TRUE,_8,_24) (Same Type. No op)
    val x35078 = OpDef(op=MuxOp, inputs=List(x35061, x35077, x35060)).name("x35078").ctrl(x35080).srcCtx("CellsPar.scala:218:43") // Mux(x35061,x35077,x35060)
    val x35079 = StoreBanks(List(x34680_d0_b0, x34680_d1_b0), List(b22532, x35049), x35078).name("x35079").ctrl(x35080).srcCtx("CellsPar.scala:218:38") // SRAMStore(x34680,ArrayBuffer(Const(1), Const(512)),List(b22532, x35049),Const(0),x35078,x35056)
    val x35084 = Counter(min=Const(0), max=Const(512), step=Const(16), par=1).name("x35084").ctrl(x36742).srcCtx("CellsPar.scala:190:58") // CounterNew(Const(0),Const(512),Const(16),Const(1))
    val x35085 = Counter(min=Const(0), max=Const(1536), step=Const(16), par=1).name("x35085").ctrl(x36742).srcCtx("CellsPar.scala:190:33") // CounterNew(Const(0),Const(1536),Const(16),Const(1))
    val x35086 = CounterChain(List(x35085,x35084)).name("x35086").ctrl(x36742).srcCtx("CellsPar.scala:190:72") // CounterChainNew(List(x35085, x35084))
    val x35192 = LoopController(style=MetaPipe, level=OuterControl, cchain=x35086).name("x35192").ctrl(x36742).srcCtx("CellsPar.scala:190:72") // UnrolledForeach(List(b22320),x35086,Block(Const(())),List(List(b22607), List(b22608)),List(List(b22609), List(b22610)))
    val b22607 = CounterIter(x35085, Some(0)).name("b22607").ctrl(x35192) // b22607
    val b22609 = Const(true).name("b22609").ctrl(x35192) // b22609
    val b22608 = CounterIter(x35084, Some(0)).name("b22608").ctrl(x35192) // b22608
    val b22610 = Const(true).name("b22610").ctrl(x35192) // b22610
    val x35087_d0_b0 = SRAM(size=256, banking=Strided(banks=1, stride=16)).name("x35087_d0_b0").ctrl(x35192).srcCtx("CellsPar.scala:191:33:tileKernel") // x35087 = SRAMNew(ArrayBuffer(Const(16), Const(16)))
    isAccum(x35087_d0_b0) = false
    bufferDepthOf(x35087_d0_b0) = 2
    val x35090 = UnitController(style=SeqPipe, level=InnerControl).name("x35090").ctrl(x35192).srcCtx("CellsPar.scala:190:72") // UnitPipe(List(b22609, b22610, b22320),Block(Const(())))
    val x35088 = OpDef(op=FixAdd, inputs=List(b22607, Const(16))).name("x35088").ctrl(x35090).srcCtx("CellsPar.scala:192:36") // FixAdd(b22607,Const(16))
    val x35089 = OpDef(op=FixAdd, inputs=List(b22608, Const(16))).name("x35089").ctrl(x35090).srcCtx("CellsPar.scala:192:45") // FixAdd(b22608,Const(16))
    val x35091 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x35091").ctrl(x35192).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x35092 = CounterChain(List(x35091)).name("x35092").ctrl(x35192).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x35091))
    val x35121 = LoopController(style=StreamPipe, level=OuterControl, cchain=x35092).name("x35121").ctrl(x35192).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b22609, b22610, b22320),x35092,Block(Const(())),List(List(b22617)),List(List(b22618)))
    val b22617 = CounterIter(x35091, Some(0)).name("b22617").ctrl(x35121) // b22617
    val b22618 = Const(true).name("b22618").ctrl(x35121) // b22618
    val b36837 = StreamOut(field="offset").name("b36837").ctrl(x35121).srcCtx("CellsPar.scala:192:20") // x35093 = StreamOutNew(BurstCmdBus)
    isAccum(b36837) = false
    bufferDepthOf(b36837) = 1
    val b36838 = StreamOut(field="size").name("b36838").ctrl(x35121).srcCtx("CellsPar.scala:192:20") // x35093 = StreamOutNew(BurstCmdBus)
    isAccum(b36838) = false
    bufferDepthOf(b36838) = 1
    val x35094 = StreamIn(field="data").name("x35094").ctrl(x35121).srcCtx("CellsPar.scala:192:20") // x35094 = StreamInNew(BurstDataBus())
    isAccum(x35094) = false
    bufferDepthOf(x35094) = 1
    val x35109 = UnitController(style=SeqPipe, level=InnerControl).name("x35109").ctrl(x35121).srcCtx("CellsPar.scala:192:20") // UnitPipe(List(b22618, b22609, b22610, b22320),Block(x35108))
    val x35095 = OpDef(op=FixAdd, inputs=List(b22607, b22617)).name("x35095").ctrl(x35109).srcCtx("CellsPar.scala:192:20") // FixAdd(b22607,b22617)
    val x35096 = x35095 // FixConvert(x35095,TRUE,_32,_0) (Same Type. No op)
    val x35097 = OpDef(op=FixSla, inputs=List(x35096, Const(9))).name("x35097").ctrl(x35109).srcCtx("CellsPar.scala:192:20") // FixLsh(x35096,Const(9))
    val x35098 = b22608 // FixConvert(b22608,TRUE,_32,_0) (Same Type. No op)
    val x35099 = OpDef(op=FixAdd, inputs=List(x35097, x35098)).name("x35099").ctrl(x35109).srcCtx("CellsPar.scala:192:20") // FixAdd(x35097,x35098)
    val x35100 = OpDef(op=FixSla, inputs=List(x35099, Const(2))).name("x35100").ctrl(x35109).srcCtx("CellsPar.scala:192:20") // FixLsh(x35099,Const(2))
    val x35101 = x35100 // FixConvert(x35100,TRUE,_64,_0)
    val x35102 = DramAddress(x34402).name("x35102").ctrl(x35109).srcCtx("CellsPar.scala:192:20") // GetDRAMAddress(x34402)
    val x35104_x35103 = OpDef(op=FixAdd, inputs=List(x35101, x35102)).name("x35104_x35103").ctrl(x35109).srcCtx("CellsPar.scala:192:20") // FixAdd(x35101,x35102)
    // x35104 = SimpleStruct(ArrayBuffer((offset,x35103), (size,Const(64)), (isLoad,Const(true))))
    val x35105 = OpDef(op=BitAnd, inputs=List(b22618, b22609)).name("x35105").ctrl(x35109).srcCtx("UnrollingBase.scala:28:66") // And(b22618,b22609)
    val x35106 = OpDef(op=BitAnd, inputs=List(b22610, b22320)).name("x35106").ctrl(x35109).srcCtx("UnrollingBase.scala:28:66") // And(b22610,b22320)
    val x35107 = OpDef(op=BitAnd, inputs=List(x35105, x35106)).name("x35107").ctrl(x35109).srcCtx("UnrollingBase.scala:28:66") // And(x35105,x35106)
    val x35108_b36839_b36837 = WriteMem(b36837, x35104_x35103).name("x35108_b36839_b36837").ctrl(x35109).srcCtx("CellsPar.scala:192:20") // StreamWrite(x35093,x35104,x35107)
    val x35108_b36840_b36838 = WriteMem(b36838, Const(64)).name("x35108_b36840_b36838").ctrl(x35109).srcCtx("CellsPar.scala:192:20") // StreamWrite(x35093,x35104,x35107)
    val x35110 = FringeDenseLoad(dram=List(x34402), cmdStream=List(b36837, b36838), dataStream=List(x35094)).name("x35110").ctrl(x35121).srcCtx("CellsPar.scala:192:20") // FringeDenseLoad(x34402,x35093,x35094)
    val x35111 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x35111").ctrl(x35121).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x35112 = CounterChain(List(x35111)).name("x35112").ctrl(x35121).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x35111))
    val x35120 = LoopController(style=InnerPipe, level=InnerControl, cchain=x35112).name("x35120").ctrl(x35121).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b22618, b22609, b22610, b22320),x35112,Block(Const(())),List(List(b22639)),List(List(b22640)))
    val b22639 = CounterIter(x35111, None).name("b22639").ctrl(x35120) // b22639
    val b22640 = Const(true).name("b22640").ctrl(x35120) // b22640
    val x35113 = OpDef(op=BitAnd, inputs=List(b22640, b22618)).name("x35113").ctrl(x35120).srcCtx("UnrollingBase.scala:28:66") // And(b22640,b22618)
    val x35114 = OpDef(op=BitAnd, inputs=List(b22609, b22610)).name("x35114").ctrl(x35120).srcCtx("UnrollingBase.scala:28:66") // And(b22609,b22610)
    val x35115 = OpDef(op=BitAnd, inputs=List(x35113, x35114)).name("x35115").ctrl(x35120).srcCtx("UnrollingBase.scala:28:66") // And(x35113,x35114)
    val x35116 = OpDef(op=BitAnd, inputs=List(x35115, b22320)).name("x35116").ctrl(x35120).srcCtx("UnrollingBase.scala:28:66") // And(x35115,b22320)
    val x35117_x35117 = ReadMem(x35094).name("x35117_x35117").ctrl(x35120).srcCtx("CellsPar.scala:192:20") // ParStreamRead(x35094,List(x35116))
    val x35118_x35118 = x35117_x35117 // x35118 = VectorApply(x35117,0)
    val x35119 = StoreBanks(List(x35087_d0_b0), List(b22617, b22639), x35118_x35118).name("x35119").ctrl(x35120).srcCtx("CellsPar.scala:192:20") // ParSRAMStore(x35087,List(List(b22617, b22639)),List(x35118),List(x35116))
    val x35122 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x35122").ctrl(x35192).srcCtx("CellsPar.scala:193:34") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x35123 = CounterChain(List(x35122)).name("x35123").ctrl(x35192).srcCtx("CellsPar.scala:193:46") // CounterChainNew(List(x35122))
    val x35191 = LoopController(style=MetaPipe, level=OuterControl, cchain=x35123).name("x35191").ctrl(x35192).srcCtx("CellsPar.scala:193:46") // UnrolledForeach(List(b22609, b22610, b22320),x35123,Block(Const(())),List(List(b22652)),List(List(b22653)))
    val b22652 = CounterIter(x35122, Some(0)).name("b22652").ctrl(x35191) // b22652
    val b22653 = Const(true).name("b22653").ctrl(x35191) // b22653
    val x35124 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x35124").ctrl(x35191).srcCtx("CellsPar.scala:194:28") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x35125 = CounterChain(List(x35124)).name("x35125").ctrl(x35191).srcCtx("CellsPar.scala:194:42") // CounterChainNew(List(x35124))
    val x35190 = LoopController(style=MetaPipe, level=OuterControl, cchain=x35125).name("x35190").ctrl(x35191).srcCtx("CellsPar.scala:194:42") // UnrolledForeach(List(b22653, b22609, b22610, b22320),x35125,Block(Const(())),List(List(b22656)),List(List(b22657)))
    val b22656 = CounterIter(x35124, Some(0)).name("b22656").ctrl(x35190) // b22656
    val b22657 = Const(true).name("b22657").ctrl(x35190) // b22657
    val x35126_d0 = Reg(init=Some(0.0)).name("x35126_d0").ctrl(x35190).srcCtx("CellsPar.scala:195:34:prod") // x35126 = RegNew(Const(0))
    isAccum(x35126_d0) = false
    bufferDepthOf(x35126_d0) = 2
    val x35126_d1 = Reg(init=Some(0.0)).name("x35126_d1").ctrl(x35190).srcCtx("CellsPar.scala:195:34:prod") // x35126 = RegNew(Const(0))
    isAccum(x35126_d1) = true
    bufferDepthOf(x35126_d1) = 1
    val x35127 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x35127").ctrl(x35190).srcCtx("CellsPar.scala:195:43") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x35128 = CounterChain(List(x35127)).name("x35128").ctrl(x35190).srcCtx("CellsPar.scala:207:15") // CounterChainNew(List(x35127))
    val x35154 = LoopController(style=InnerPipe, level=InnerControl, cchain=x35128).name("x35154").ctrl(x35190).srcCtx("CellsPar.scala:207:15") // UnrolledReduce(List(b22657, b22653, b22609, b22610, b22320),x35128,x35126,Block((x35126) => Const(())),List(List(b22661)),List(List(b22662)))
    val b22661 = CounterIter(x35127, None).name("b22661").ctrl(x35154) // b22661
    val b22662 = Const(true).name("b22662").ctrl(x35154) // b22662
    val x35129 = OpDef(op=FixAdd, inputs=List(b22607, b22661)).name("x35129").ctrl(x35154).srcCtx("CellsPar.scala:196:42:reduce_size_offset") // FixAdd(b22607,b22661)
    val x35130 = OpDef(op=BitAnd, inputs=List(b22662, b22657)).name("x35130").ctrl(x35154).srcCtx("UnrollingBase.scala:28:66") // And(b22662,b22657)
    val x35131 = OpDef(op=BitAnd, inputs=List(b22653, b22609)).name("x35131").ctrl(x35154).srcCtx("UnrollingBase.scala:28:66") // And(b22653,b22609)
    val x35132 = OpDef(op=BitAnd, inputs=List(b22610, b22320)).name("x35132").ctrl(x35154).srcCtx("UnrollingBase.scala:28:66") // And(b22610,b22320)
    val x35133 = OpDef(op=BitAnd, inputs=List(x35130, x35131)).name("x35133").ctrl(x35154).srcCtx("UnrollingBase.scala:28:66") // And(x35130,x35131)
    val x35134 = OpDef(op=BitAnd, inputs=List(x35133, x35132)).name("x35134").ctrl(x35154).srcCtx("UnrollingBase.scala:28:66") // And(x35133,x35132)
    val x35135 = LoadBanks(List(x35087_d0_b0), List(b22661, b22656)).name("x35135").ctrl(x35154).srcCtx("CellsPar.scala:203:34:tk") // ParSRAMLoad(x35087,List(List(b22661, b22656)),List(x35134))
    val x35136 = x35135 // x35136 = VectorApply(x35135,0)
    val x35137 = LoadBanks(List(x34676_d1_b0), List(b22652, x35129)).name("x35137").ctrl(x35154).srcCtx("CellsPar.scala:204:28") // ParSRAMLoad(x34676,List(List(b22652, x35129)),List(x35134))
    val x35138 = x35137 // x35138 = VectorApply(x35137,0)
    val x35139 = OpDef(op=FixMul, inputs=List(x35138, x35136)).name("x35139").ctrl(x35154).srcCtx("CellsPar.scala:204:52:tempA") // FixMul(x35138,x35136)
    val x35140 = OpDef(op=FixSub, inputs=List(x35129, Const(512))).name("x35140").ctrl(x35154).srcCtx("CellsPar.scala:205:51") // FixSub(x35129,Const(512))
    val x35141 = LoadBanks(List(x34677_d5_b0), List(b22652, x35140)).name("x35141").ctrl(x35154).srcCtx("CellsPar.scala:205:28") // ParSRAMLoad(x34677,List(List(b22652, x35140)),List(x35134))
    val x35142 = x35141 // x35142 = VectorApply(x35141,0)
    val x35143 = OpDef(op=FixMul, inputs=List(x35142, x35136)).name("x35143").ctrl(x35154).srcCtx("CellsPar.scala:205:67:tempB") // FixMul(x35142,x35136)
    val x35144 = OpDef(op=FixLt, inputs=List(x35129, Const(512))).name("x35144").ctrl(x35154).srcCtx("CellsPar.scala:206:38") // FixLt(x35129,Const(512))
    val x35145 = OpDef(op=MuxOp, inputs=List(x35144, x35139, x35143)).name("x35145").ctrl(x35154).srcCtx("CellsPar.scala:206:18") // Mux(x35144,x35139,x35143)
    val x35146 = ReadMem(x35126_d1).name("x35146").ctrl(x35154).srcCtx("CellsPar.scala:207:15") // RegRead(x35126)
    val x35147 = OpDef(op=FixEql, inputs=List(b22661, Const(0))).name("x35147").ctrl(x35154).srcCtx("CellsPar.scala:207:15") // FixEql(b22661,Const(0))
    val x35148 = ReduceAccumOp(op=FixAdd, input=x35145, accum=x35146).name("x35148").ctrl(x35154).srcCtx("CellsPar.scala:207:17") // FixAdd(x35145,x35146)
    val x35149 = OpDef(op=BitAnd, inputs=List(b22657, b22653)).name("x35149").ctrl(x35154).srcCtx("UnrollingBase.scala:28:66") // And(b22657,b22653)
    val x35150 = OpDef(op=BitAnd, inputs=List(b22609, b22610)).name("x35150").ctrl(x35154).srcCtx("UnrollingBase.scala:28:66") // And(b22609,b22610)
    val x35151 = OpDef(op=BitAnd, inputs=List(x35149, x35150)).name("x35151").ctrl(x35154).srcCtx("UnrollingBase.scala:28:66") // And(x35149,x35150)
    val x35152 = OpDef(op=BitAnd, inputs=List(x35151, b22320)).name("x35152").ctrl(x35154).srcCtx("UnrollingBase.scala:28:66") // And(x35151,b22320)
    val x35153_x35126_d0 = WriteMem(x35126_d0, x35148).name("x35153_x35126_d0").ctrl(x35154).srcCtx("CellsPar.scala:207:15") // RegWrite(x35126,x35148,x35152)
    val x35153_x35126_d1 = WriteMem(x35126_d1, x35148).name("x35153_x35126_d1").ctrl(x35154).srcCtx("CellsPar.scala:207:15") // RegWrite(x35126,x35148,x35152)
    val x35189 = UnitController(style=SeqPipe, level=InnerControl).name("x35189").ctrl(x35190).srcCtx("CellsPar.scala:194:42") // UnitPipe(List(b22657, b22653, b22609, b22610, b22320),Block(Const(())))
    val x35155 = OpDef(op=FixAdd, inputs=List(b22608, b22656)).name("x35155").ctrl(x35189).srcCtx("CellsPar.scala:209:31:colOffset") // FixAdd(b22608,b22656)
    val x35156 = ReadMem(x35126_d0).name("x35156").ctrl(x35189).srcCtx("CellsPar.scala:210:28") // RegRead(x35126)
    val x35157 = OpDef(op=FixEql, inputs=List(b22607, Const(0))).name("x35157").ctrl(x35189).srcCtx("CellsPar.scala:210:42") // FixEql(b22607,Const(0))
    val x35158 = OpDef(op=FixAdd, inputs=List(x35155, Const(1024))).name("x35158").ctrl(x35189).srcCtx("CellsPar.scala:210:66") // FixAdd(x35155,Const(1024))
    val x35159 = OpDef(op=BitAnd, inputs=List(b22657, b22653)).name("x35159").ctrl(x35189).srcCtx("UnrollingBase.scala:28:66") // And(b22657,b22653)
    val x35160 = OpDef(op=BitAnd, inputs=List(b22609, b22610)).name("x35160").ctrl(x35189).srcCtx("UnrollingBase.scala:28:66") // And(b22609,b22610)
    val x35161 = OpDef(op=BitAnd, inputs=List(x35159, x35160)).name("x35161").ctrl(x35189).srcCtx("UnrollingBase.scala:28:66") // And(x35159,x35160)
    val x35162 = OpDef(op=BitAnd, inputs=List(x35161, b22320)).name("x35162").ctrl(x35189).srcCtx("UnrollingBase.scala:28:66") // And(x35161,b22320)
    val x35163 = LoadBanks(List(x34683_d1_b0), List(Const(0), x35158)).name("x35163").ctrl(x35189).srcCtx("CellsPar.scala:210:52") // SRAMLoad(x34683,ArrayBuffer(Const(1), Const(2048)),List(Const(0), x35158),Const(0),x35162)
    val x35164 = LoadBanks(List(x34681_d1_b0), List(b22652, x35155)).name("x35164").ctrl(x35189).srcCtx("CellsPar.scala:210:91") // SRAMLoad(x34681,ArrayBuffer(Const(1), Const(512)),List(b22652, x35155),Const(0),x35162)
    val x35165 = OpDef(op=MuxOp, inputs=List(x35157, x35163, x35164)).name("x35165").ctrl(x35189).srcCtx("CellsPar.scala:210:39") // Mux(x35157,x35163,x35164)
    val x35166 = OpDef(op=FixAdd, inputs=List(x35156, x35165)).name("x35166").ctrl(x35189).srcCtx("CellsPar.scala:210:34:ele") // FixAdd(x35156,x35165)
    val x35167 = OpDef(op=FixLeq, inputs=List(Const(1520), b22607)).name("x35167").ctrl(x35189).srcCtx("CellsPar.scala:218:46") // FixLeq(Const(1520),b22607)
    // x35168 = FixConvert(Const(1),TRUE,_8,_24) x.tp=FixPt[TRUE,_32,_0] {
    val x35168_int1 = OpDef(op=BitAnd, inputs=List(Const(1), Const("01111111111111111111111111111111"))).name("x35168_int1").ctrl(x35189).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(1),TRUE,_8,_24)
    val x35168_int2 = OpDef(op=FixSla, inputs=List(x35168_int1, Const(24))).name("x35168_int2").ctrl(x35189).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(1),TRUE,_8,_24)
    val x35168_frac1 = OpDef(op=BitAnd, inputs=List(Const(1), Const("00000000000000000000000000000000"))).name("x35168_frac1").ctrl(x35189).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(1),TRUE,_8,_24)
    val x35168_frac2 = OpDef(op=FixSla, inputs=List(x35168_frac1, Const(24))).name("x35168_frac2").ctrl(x35189).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(1),TRUE,_8,_24)
    val x35168 = OpDef(op=BitOr, inputs=List(x35168_int2, x35168_frac2)).name("x35168").ctrl(x35189).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(1),TRUE,_8,_24)
    // }
    val x35169 = OpDef(op=FixAdd, inputs=List(x35166, x35168)).name("x35169").ctrl(x35189).srcCtx("CellsPar.scala:218:87") // FixAdd(x35166,x35168)
    val x35170 = OpDef(op=FixSra, inputs=List(x35169, Const(1))).name("x35170").ctrl(x35189).srcCtx("CellsPar.scala:29:22") // FixRsh(x35169,Const(1))
    val x35171 = x35170 // FixConvert(x35170,TRUE,_8,_24) (Same Type. No op)
    val x35172 = OpDef(op=FixAbs, inputs=List(x35171)).name("x35172").ctrl(x35189).srcCtx("CellsPar.scala:37:19:absp") // FixAbs(x35171)
    val x35173 = OpDef(op=FixLt, inputs=List(Const(2.5), x35172)).name("x35173").ctrl(x35189).srcCtx("CellsPar.scala:51:23:cond1") // FixLt(Const(2.5),x35172)
    val x35174 = OpDef(op=FixLt, inputs=List(Const(0.5), x35172)).name("x35174").ctrl(x35189).srcCtx("CellsPar.scala:54:35") // FixLt(Const(0.5),x35172)
    val x35175 = OpDef(op=FixLeq, inputs=List(x35172, Const(2.5))).name("x35175").ctrl(x35189).srcCtx("CellsPar.scala:54:52") // FixLeq(x35172,Const(2.5))
    val x35176 = OpDef(op=BitAnd, inputs=List(x35174, x35175)).name("x35176").ctrl(x35189).srcCtx("CellsPar.scala:54:43:cond2") // And(x35174,x35175)
    val x35177 = OpDef(op=FixSra, inputs=List(x35172, Const(2))).name("x35177").ctrl(x35189).srcCtx("CellsPar.scala:56:21:div4") // FixRsh(x35172,Const(2))
    val x35178 = OpDef(op=FixAdd, inputs=List(x35177, Const(0.375))).name("x35178").ctrl(x35189).srcCtx("CellsPar.scala:57:27:div4Offset") // FixAdd(x35177,Const(0.375))
    val x35179 = OpDef(op=MuxOp, inputs=List(x35176, x35178, x35172)).name("x35179").ctrl(x35189).srcCtx("CellsPar.scala:58:20:body2") // Mux(x35176,x35178,x35172)
    val x35180 = OpDef(op=MuxOp, inputs=List(x35173, Const(1.0), x35179)).name("x35180").ctrl(x35189).srcCtx("CellsPar.scala:60:20:absre") // Mux(x35173,Const(1),x35179)
    val x35181 = OpDef(op=FixNeg, inputs=List(x35180)).name("x35181").ctrl(x35189).srcCtx("CellsPar.scala:62:29:mabre") // FixNeg(x35180)
    val x35182 = OpDef(op=FixLt, inputs=List(x35171, Const(0.0))).name("x35182").ctrl(x35189).srcCtx("CellsPar.scala:63:22") // FixLt(x35171,Const(0))
    val x35183 = OpDef(op=MuxOp, inputs=List(x35182, x35181, x35180)).name("x35183").ctrl(x35189).srcCtx("CellsPar.scala:63:17:re") // Mux(x35182,x35181,x35180)
    val x35184 = x35183 // FixConvert(x35183,TRUE,_8,_24) (Same Type. No op)
    val x35185 = OpDef(op=FixAdd, inputs=List(x35184, Const(1.0))).name("x35185").ctrl(x35189).srcCtx("CellsPar.scala:29:33") // FixAdd(x35184,Const(1))
    val x35186 = OpDef(op=FixSra, inputs=List(x35185, Const(1))).name("x35186").ctrl(x35189).srcCtx("CellsPar.scala:29:44") // FixRsh(x35185,Const(1))
    val x35187 = OpDef(op=MuxOp, inputs=List(x35167, x35186, x35166)).name("x35187").ctrl(x35189).srcCtx("CellsPar.scala:218:43") // Mux(x35167,x35186,x35166)
    val x35188 = StoreBanks(List(x34681_d0_b0, x34681_d1_b0), List(b22652, x35155), x35187).name("x35188").ctrl(x35189).srcCtx("CellsPar.scala:218:38") // SRAMStore(x34681,ArrayBuffer(Const(1), Const(512)),List(b22652, x35155),Const(0),x35187,x35162)
    val x35193 = Counter(min=Const(0), max=Const(512), step=Const(16), par=1).name("x35193").ctrl(x36742).srcCtx("CellsPar.scala:190:58") // CounterNew(Const(0),Const(512),Const(16),Const(1))
    val x35194 = Counter(min=Const(0), max=Const(1536), step=Const(16), par=1).name("x35194").ctrl(x36742).srcCtx("CellsPar.scala:190:33") // CounterNew(Const(0),Const(1536),Const(16),Const(1))
    val x35195 = CounterChain(List(x35194,x35193)).name("x35195").ctrl(x36742).srcCtx("CellsPar.scala:190:72") // CounterChainNew(List(x35194, x35193))
    val x35301 = LoopController(style=MetaPipe, level=OuterControl, cchain=x35195).name("x35301").ctrl(x36742).srcCtx("CellsPar.scala:190:72") // UnrolledForeach(List(b22320),x35195,Block(Const(())),List(List(b22730), List(b22731)),List(List(b22732), List(b22733)))
    val b22730 = CounterIter(x35194, Some(0)).name("b22730").ctrl(x35301) // b22730
    val b22732 = Const(true).name("b22732").ctrl(x35301) // b22732
    val b22731 = CounterIter(x35193, Some(0)).name("b22731").ctrl(x35301) // b22731
    val b22733 = Const(true).name("b22733").ctrl(x35301) // b22733
    val x35196_d0_b0 = SRAM(size=256, banking=Strided(banks=1, stride=16)).name("x35196_d0_b0").ctrl(x35301).srcCtx("CellsPar.scala:191:33:tileKernel") // x35196 = SRAMNew(ArrayBuffer(Const(16), Const(16)))
    isAccum(x35196_d0_b0) = false
    bufferDepthOf(x35196_d0_b0) = 2
    val x35199 = UnitController(style=SeqPipe, level=InnerControl).name("x35199").ctrl(x35301).srcCtx("CellsPar.scala:190:72") // UnitPipe(List(b22732, b22733, b22320),Block(Const(())))
    val x35197 = OpDef(op=FixAdd, inputs=List(b22730, Const(16))).name("x35197").ctrl(x35199).srcCtx("CellsPar.scala:192:36") // FixAdd(b22730,Const(16))
    val x35198 = OpDef(op=FixAdd, inputs=List(b22731, Const(16))).name("x35198").ctrl(x35199).srcCtx("CellsPar.scala:192:45") // FixAdd(b22731,Const(16))
    val x35200 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x35200").ctrl(x35301).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x35201 = CounterChain(List(x35200)).name("x35201").ctrl(x35301).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x35200))
    val x35230 = LoopController(style=StreamPipe, level=OuterControl, cchain=x35201).name("x35230").ctrl(x35301).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b22732, b22733, b22320),x35201,Block(Const(())),List(List(b22740)),List(List(b22741)))
    val b22740 = CounterIter(x35200, Some(0)).name("b22740").ctrl(x35230) // b22740
    val b22741 = Const(true).name("b22741").ctrl(x35230) // b22741
    val b36841 = StreamOut(field="offset").name("b36841").ctrl(x35230).srcCtx("CellsPar.scala:192:20") // x35202 = StreamOutNew(BurstCmdBus)
    isAccum(b36841) = false
    bufferDepthOf(b36841) = 1
    
    def fun2 = {
    val b36842 = StreamOut(field="size").name("b36842").ctrl(x35230).srcCtx("CellsPar.scala:192:20") // x35202 = StreamOutNew(BurstCmdBus)
    isAccum(b36842) = false
    bufferDepthOf(b36842) = 1
    val x35203 = StreamIn(field="data").name("x35203").ctrl(x35230).srcCtx("CellsPar.scala:192:20") // x35203 = StreamInNew(BurstDataBus())
    isAccum(x35203) = false
    bufferDepthOf(x35203) = 1
    val x35218 = UnitController(style=SeqPipe, level=InnerControl).name("x35218").ctrl(x35230).srcCtx("CellsPar.scala:192:20") // UnitPipe(List(b22741, b22732, b22733, b22320),Block(x35217))
    val x35204 = OpDef(op=FixAdd, inputs=List(b22730, b22740)).name("x35204").ctrl(x35218).srcCtx("CellsPar.scala:192:20") // FixAdd(b22730,b22740)
    val x35205 = x35204 // FixConvert(x35204,TRUE,_32,_0) (Same Type. No op)
    val x35206 = OpDef(op=FixSla, inputs=List(x35205, Const(9))).name("x35206").ctrl(x35218).srcCtx("CellsPar.scala:192:20") // FixLsh(x35205,Const(9))
    val x35207 = b22731 // FixConvert(b22731,TRUE,_32,_0) (Same Type. No op)
    val x35208 = OpDef(op=FixAdd, inputs=List(x35206, x35207)).name("x35208").ctrl(x35218).srcCtx("CellsPar.scala:192:20") // FixAdd(x35206,x35207)
    val x35209 = OpDef(op=FixSla, inputs=List(x35208, Const(2))).name("x35209").ctrl(x35218).srcCtx("CellsPar.scala:192:20") // FixLsh(x35208,Const(2))
    val x35210 = x35209 // FixConvert(x35209,TRUE,_64,_0)
    val x35211 = DramAddress(x34403).name("x35211").ctrl(x35218).srcCtx("CellsPar.scala:192:20") // GetDRAMAddress(x34403)
    val x35213_x35212 = OpDef(op=FixAdd, inputs=List(x35210, x35211)).name("x35213_x35212").ctrl(x35218).srcCtx("CellsPar.scala:192:20") // FixAdd(x35210,x35211)
    // x35213 = SimpleStruct(ArrayBuffer((offset,x35212), (size,Const(64)), (isLoad,Const(true))))
    val x35214 = OpDef(op=BitAnd, inputs=List(b22741, b22732)).name("x35214").ctrl(x35218).srcCtx("UnrollingBase.scala:28:66") // And(b22741,b22732)
    val x35215 = OpDef(op=BitAnd, inputs=List(b22733, b22320)).name("x35215").ctrl(x35218).srcCtx("UnrollingBase.scala:28:66") // And(b22733,b22320)
    val x35216 = OpDef(op=BitAnd, inputs=List(x35214, x35215)).name("x35216").ctrl(x35218).srcCtx("UnrollingBase.scala:28:66") // And(x35214,x35215)
    val x35217_b36843_b36841 = WriteMem(b36841, x35213_x35212).name("x35217_b36843_b36841").ctrl(x35218).srcCtx("CellsPar.scala:192:20") // StreamWrite(x35202,x35213,x35216)
    val x35217_b36844_b36842 = WriteMem(b36842, Const(64)).name("x35217_b36844_b36842").ctrl(x35218).srcCtx("CellsPar.scala:192:20") // StreamWrite(x35202,x35213,x35216)
    val x35219 = FringeDenseLoad(dram=List(x34403), cmdStream=List(b36841, b36842), dataStream=List(x35203)).name("x35219").ctrl(x35230).srcCtx("CellsPar.scala:192:20") // FringeDenseLoad(x34403,x35202,x35203)
    val x35220 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x35220").ctrl(x35230).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x35221 = CounterChain(List(x35220)).name("x35221").ctrl(x35230).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x35220))
    val x35229 = LoopController(style=InnerPipe, level=InnerControl, cchain=x35221).name("x35229").ctrl(x35230).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b22741, b22732, b22733, b22320),x35221,Block(Const(())),List(List(b22762)),List(List(b22763)))
    val b22762 = CounterIter(x35220, None).name("b22762").ctrl(x35229) // b22762
    val b22763 = Const(true).name("b22763").ctrl(x35229) // b22763
    val x35222 = OpDef(op=BitAnd, inputs=List(b22763, b22741)).name("x35222").ctrl(x35229).srcCtx("UnrollingBase.scala:28:66") // And(b22763,b22741)
    val x35223 = OpDef(op=BitAnd, inputs=List(b22732, b22733)).name("x35223").ctrl(x35229).srcCtx("UnrollingBase.scala:28:66") // And(b22732,b22733)
    val x35224 = OpDef(op=BitAnd, inputs=List(x35222, x35223)).name("x35224").ctrl(x35229).srcCtx("UnrollingBase.scala:28:66") // And(x35222,x35223)
    val x35225 = OpDef(op=BitAnd, inputs=List(x35224, b22320)).name("x35225").ctrl(x35229).srcCtx("UnrollingBase.scala:28:66") // And(x35224,b22320)
    val x35226_x35226 = ReadMem(x35203).name("x35226_x35226").ctrl(x35229).srcCtx("CellsPar.scala:192:20") // ParStreamRead(x35203,List(x35225))
    val x35227_x35227 = x35226_x35226 // x35227 = VectorApply(x35226,0)
    val x35228 = StoreBanks(List(x35196_d0_b0), List(b22740, b22762), x35227_x35227).name("x35228").ctrl(x35229).srcCtx("CellsPar.scala:192:20") // ParSRAMStore(x35196,List(List(b22740, b22762)),List(x35227),List(x35225))
    val x35231 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x35231").ctrl(x35301).srcCtx("CellsPar.scala:193:34") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x35232 = CounterChain(List(x35231)).name("x35232").ctrl(x35301).srcCtx("CellsPar.scala:193:46") // CounterChainNew(List(x35231))
    val x35300 = LoopController(style=MetaPipe, level=OuterControl, cchain=x35232).name("x35300").ctrl(x35301).srcCtx("CellsPar.scala:193:46") // UnrolledForeach(List(b22732, b22733, b22320),x35232,Block(Const(())),List(List(b22775)),List(List(b22776)))
    val b22775 = CounterIter(x35231, Some(0)).name("b22775").ctrl(x35300) // b22775
    val b22776 = Const(true).name("b22776").ctrl(x35300) // b22776
    val x35233 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x35233").ctrl(x35300).srcCtx("CellsPar.scala:194:28") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x35234 = CounterChain(List(x35233)).name("x35234").ctrl(x35300).srcCtx("CellsPar.scala:194:42") // CounterChainNew(List(x35233))
    val x35299 = LoopController(style=MetaPipe, level=OuterControl, cchain=x35234).name("x35299").ctrl(x35300).srcCtx("CellsPar.scala:194:42") // UnrolledForeach(List(b22776, b22732, b22733, b22320),x35234,Block(Const(())),List(List(b22779)),List(List(b22780)))
    val b22779 = CounterIter(x35233, Some(0)).name("b22779").ctrl(x35299) // b22779
    val b22780 = Const(true).name("b22780").ctrl(x35299) // b22780
    val x35235_d0 = Reg(init=Some(0.0)).name("x35235_d0").ctrl(x35299).srcCtx("CellsPar.scala:195:34:prod") // x35235 = RegNew(Const(0))
    isAccum(x35235_d0) = false
    bufferDepthOf(x35235_d0) = 2
    val x35235_d1 = Reg(init=Some(0.0)).name("x35235_d1").ctrl(x35299).srcCtx("CellsPar.scala:195:34:prod") // x35235 = RegNew(Const(0))
    isAccum(x35235_d1) = true
    bufferDepthOf(x35235_d1) = 1
    val x35236 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x35236").ctrl(x35299).srcCtx("CellsPar.scala:195:43") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x35237 = CounterChain(List(x35236)).name("x35237").ctrl(x35299).srcCtx("CellsPar.scala:207:15") // CounterChainNew(List(x35236))
    val x35263 = LoopController(style=InnerPipe, level=InnerControl, cchain=x35237).name("x35263").ctrl(x35299).srcCtx("CellsPar.scala:207:15") // UnrolledReduce(List(b22780, b22776, b22732, b22733, b22320),x35237,x35235,Block((x35235) => Const(())),List(List(b22784)),List(List(b22785)))
    val b22784 = CounterIter(x35236, None).name("b22784").ctrl(x35263) // b22784
    val b22785 = Const(true).name("b22785").ctrl(x35263) // b22785
    val x35238 = OpDef(op=FixAdd, inputs=List(b22730, b22784)).name("x35238").ctrl(x35263).srcCtx("CellsPar.scala:196:42:reduce_size_offset") // FixAdd(b22730,b22784)
    val x35239 = OpDef(op=BitAnd, inputs=List(b22785, b22780)).name("x35239").ctrl(x35263).srcCtx("UnrollingBase.scala:28:66") // And(b22785,b22780)
    val x35240 = OpDef(op=BitAnd, inputs=List(b22776, b22732)).name("x35240").ctrl(x35263).srcCtx("UnrollingBase.scala:28:66") // And(b22776,b22732)
    val x35241 = OpDef(op=BitAnd, inputs=List(b22733, b22320)).name("x35241").ctrl(x35263).srcCtx("UnrollingBase.scala:28:66") // And(b22733,b22320)
    val x35242 = OpDef(op=BitAnd, inputs=List(x35239, x35240)).name("x35242").ctrl(x35263).srcCtx("UnrollingBase.scala:28:66") // And(x35239,x35240)
    val x35243 = OpDef(op=BitAnd, inputs=List(x35242, x35241)).name("x35243").ctrl(x35263).srcCtx("UnrollingBase.scala:28:66") // And(x35242,x35241)
    val x35244 = LoadBanks(List(x35196_d0_b0), List(b22784, b22779)).name("x35244").ctrl(x35263).srcCtx("CellsPar.scala:203:34:tk") // ParSRAMLoad(x35196,List(List(b22784, b22779)),List(x35243))
    val x35245 = x35244 // x35245 = VectorApply(x35244,0)
    val x35246 = LoadBanks(List(x34676_d0_b0), List(b22775, x35238)).name("x35246").ctrl(x35263).srcCtx("CellsPar.scala:204:28") // ParSRAMLoad(x34676,List(List(b22775, x35238)),List(x35243))
    val x35247 = x35246 // x35247 = VectorApply(x35246,0)
    val x35248 = OpDef(op=FixMul, inputs=List(x35247, x35245)).name("x35248").ctrl(x35263).srcCtx("CellsPar.scala:204:52:tempA") // FixMul(x35247,x35245)
    val x35249 = OpDef(op=FixSub, inputs=List(x35238, Const(512))).name("x35249").ctrl(x35263).srcCtx("CellsPar.scala:205:51") // FixSub(x35238,Const(512))
    val x35250 = LoadBanks(List(x34677_d4_b0), List(b22775, x35249)).name("x35250").ctrl(x35263).srcCtx("CellsPar.scala:205:28") // ParSRAMLoad(x34677,List(List(b22775, x35249)),List(x35243))
    val x35251 = x35250 // x35251 = VectorApply(x35250,0)
    val x35252 = OpDef(op=FixMul, inputs=List(x35251, x35245)).name("x35252").ctrl(x35263).srcCtx("CellsPar.scala:205:67:tempB") // FixMul(x35251,x35245)
    val x35253 = OpDef(op=FixLt, inputs=List(x35238, Const(512))).name("x35253").ctrl(x35263).srcCtx("CellsPar.scala:206:38") // FixLt(x35238,Const(512))
    val x35254 = OpDef(op=MuxOp, inputs=List(x35253, x35248, x35252)).name("x35254").ctrl(x35263).srcCtx("CellsPar.scala:206:18") // Mux(x35253,x35248,x35252)
    val x35255 = ReadMem(x35235_d1).name("x35255").ctrl(x35263).srcCtx("CellsPar.scala:207:15") // RegRead(x35235)
    val x35256 = OpDef(op=FixEql, inputs=List(b22784, Const(0))).name("x35256").ctrl(x35263).srcCtx("CellsPar.scala:207:15") // FixEql(b22784,Const(0))
    val x35257 = ReduceAccumOp(op=FixAdd, input=x35254, accum=x35255).name("x35257").ctrl(x35263).srcCtx("CellsPar.scala:207:17") // FixAdd(x35254,x35255)
    val x35258 = OpDef(op=BitAnd, inputs=List(b22780, b22776)).name("x35258").ctrl(x35263).srcCtx("UnrollingBase.scala:28:66") // And(b22780,b22776)
    val x35259 = OpDef(op=BitAnd, inputs=List(b22732, b22733)).name("x35259").ctrl(x35263).srcCtx("UnrollingBase.scala:28:66") // And(b22732,b22733)
    val x35260 = OpDef(op=BitAnd, inputs=List(x35258, x35259)).name("x35260").ctrl(x35263).srcCtx("UnrollingBase.scala:28:66") // And(x35258,x35259)
    val x35261 = OpDef(op=BitAnd, inputs=List(x35260, b22320)).name("x35261").ctrl(x35263).srcCtx("UnrollingBase.scala:28:66") // And(x35260,b22320)
    val x35262_x35235_d0 = WriteMem(x35235_d0, x35257).name("x35262_x35235_d0").ctrl(x35263).srcCtx("CellsPar.scala:207:15") // RegWrite(x35235,x35257,x35261)
    val x35262_x35235_d1 = WriteMem(x35235_d1, x35257).name("x35262_x35235_d1").ctrl(x35263).srcCtx("CellsPar.scala:207:15") // RegWrite(x35235,x35257,x35261)
    val x35298 = UnitController(style=SeqPipe, level=InnerControl).name("x35298").ctrl(x35299).srcCtx("CellsPar.scala:194:42") // UnitPipe(List(b22780, b22776, b22732, b22733, b22320),Block(Const(())))
    val x35264 = OpDef(op=FixAdd, inputs=List(b22731, b22779)).name("x35264").ctrl(x35298).srcCtx("CellsPar.scala:209:31:colOffset") // FixAdd(b22731,b22779)
    val x35265 = ReadMem(x35235_d0).name("x35265").ctrl(x35298).srcCtx("CellsPar.scala:210:28") // RegRead(x35235)
    val x35266 = OpDef(op=FixEql, inputs=List(b22730, Const(0))).name("x35266").ctrl(x35298).srcCtx("CellsPar.scala:210:42") // FixEql(b22730,Const(0))
    val x35267 = OpDef(op=FixAdd, inputs=List(x35264, Const(1536))).name("x35267").ctrl(x35298).srcCtx("CellsPar.scala:210:66") // FixAdd(x35264,Const(1536))
    val x35268 = OpDef(op=BitAnd, inputs=List(b22780, b22776)).name("x35268").ctrl(x35298).srcCtx("UnrollingBase.scala:28:66") // And(b22780,b22776)
    val x35269 = OpDef(op=BitAnd, inputs=List(b22732, b22733)).name("x35269").ctrl(x35298).srcCtx("UnrollingBase.scala:28:66") // And(b22732,b22733)
    val x35270 = OpDef(op=BitAnd, inputs=List(x35268, x35269)).name("x35270").ctrl(x35298).srcCtx("UnrollingBase.scala:28:66") // And(x35268,x35269)
    val x35271 = OpDef(op=BitAnd, inputs=List(x35270, b22320)).name("x35271").ctrl(x35298).srcCtx("UnrollingBase.scala:28:66") // And(x35270,b22320)
    val x35272 = LoadBanks(List(x34683_d0_b0), List(Const(0), x35267)).name("x35272").ctrl(x35298).srcCtx("CellsPar.scala:210:52") // SRAMLoad(x34683,ArrayBuffer(Const(1), Const(2048)),List(Const(0), x35267),Const(0),x35271)
    val x35273 = LoadBanks(List(x34682_d1_b0), List(b22775, x35264)).name("x35273").ctrl(x35298).srcCtx("CellsPar.scala:210:91") // SRAMLoad(x34682,ArrayBuffer(Const(1), Const(512)),List(b22775, x35264),Const(0),x35271)
    val x35274 = OpDef(op=MuxOp, inputs=List(x35266, x35272, x35273)).name("x35274").ctrl(x35298).srcCtx("CellsPar.scala:210:39") // Mux(x35266,x35272,x35273)
    val x35275 = OpDef(op=FixAdd, inputs=List(x35265, x35274)).name("x35275").ctrl(x35298).srcCtx("CellsPar.scala:210:34:ele") // FixAdd(x35265,x35274)
    val x35276 = OpDef(op=FixLeq, inputs=List(Const(1520), b22730)).name("x35276").ctrl(x35298).srcCtx("CellsPar.scala:218:46") // FixLeq(Const(1520),b22730)
    // x35277 = FixConvert(Const(0),TRUE,_8,_24) x.tp=FixPt[TRUE,_32,_0] {
    val x35277_int1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("01111111111111111111111111111111"))).name("x35277_int1").ctrl(x35298).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x35277_int2 = OpDef(op=FixSla, inputs=List(x35277_int1, Const(24))).name("x35277_int2").ctrl(x35298).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x35277_frac1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("00000000000000000000000000000000"))).name("x35277_frac1").ctrl(x35298).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x35277_frac2 = OpDef(op=FixSla, inputs=List(x35277_frac1, Const(24))).name("x35277_frac2").ctrl(x35298).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x35277 = OpDef(op=BitOr, inputs=List(x35277_int2, x35277_frac2)).name("x35277").ctrl(x35298).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    // }
    val x35278 = OpDef(op=FixAdd, inputs=List(x35275, x35277)).name("x35278").ctrl(x35298).srcCtx("CellsPar.scala:218:87") // FixAdd(x35275,x35277)
    val x35279 = OpDef(op=FixSra, inputs=List(x35278, Const(1))).name("x35279").ctrl(x35298).srcCtx("CellsPar.scala:29:22") // FixRsh(x35278,Const(1))
    val x35280 = x35279 // FixConvert(x35279,TRUE,_8,_24) (Same Type. No op)
    val x35281 = OpDef(op=FixAbs, inputs=List(x35280)).name("x35281").ctrl(x35298).srcCtx("CellsPar.scala:37:19:absp") // FixAbs(x35280)
    val x35282 = OpDef(op=FixLt, inputs=List(Const(2.5), x35281)).name("x35282").ctrl(x35298).srcCtx("CellsPar.scala:51:23:cond1") // FixLt(Const(2.5),x35281)
    val x35283 = OpDef(op=FixLt, inputs=List(Const(0.5), x35281)).name("x35283").ctrl(x35298).srcCtx("CellsPar.scala:54:35") // FixLt(Const(0.5),x35281)
    val x35284 = OpDef(op=FixLeq, inputs=List(x35281, Const(2.5))).name("x35284").ctrl(x35298).srcCtx("CellsPar.scala:54:52") // FixLeq(x35281,Const(2.5))
    val x35285 = OpDef(op=BitAnd, inputs=List(x35283, x35284)).name("x35285").ctrl(x35298).srcCtx("CellsPar.scala:54:43:cond2") // And(x35283,x35284)
    val x35286 = OpDef(op=FixSra, inputs=List(x35281, Const(2))).name("x35286").ctrl(x35298).srcCtx("CellsPar.scala:56:21:div4") // FixRsh(x35281,Const(2))
    val x35287 = OpDef(op=FixAdd, inputs=List(x35286, Const(0.375))).name("x35287").ctrl(x35298).srcCtx("CellsPar.scala:57:27:div4Offset") // FixAdd(x35286,Const(0.375))
    val x35288 = OpDef(op=MuxOp, inputs=List(x35285, x35287, x35281)).name("x35288").ctrl(x35298).srcCtx("CellsPar.scala:58:20:body2") // Mux(x35285,x35287,x35281)
    val x35289 = OpDef(op=MuxOp, inputs=List(x35282, Const(1.0), x35288)).name("x35289").ctrl(x35298).srcCtx("CellsPar.scala:60:20:absre") // Mux(x35282,Const(1),x35288)
    val x35290 = OpDef(op=FixNeg, inputs=List(x35289)).name("x35290").ctrl(x35298).srcCtx("CellsPar.scala:62:29:mabre") // FixNeg(x35289)
    val x35291 = OpDef(op=FixLt, inputs=List(x35280, Const(0.0))).name("x35291").ctrl(x35298).srcCtx("CellsPar.scala:63:22") // FixLt(x35280,Const(0))
    val x35292 = OpDef(op=MuxOp, inputs=List(x35291, x35290, x35289)).name("x35292").ctrl(x35298).srcCtx("CellsPar.scala:63:17:re") // Mux(x35291,x35290,x35289)
    val x35293 = x35292 // FixConvert(x35292,TRUE,_8,_24) (Same Type. No op)
    val x35294 = OpDef(op=FixAdd, inputs=List(x35293, Const(1.0))).name("x35294").ctrl(x35298).srcCtx("CellsPar.scala:29:33") // FixAdd(x35293,Const(1))
    val x35295 = OpDef(op=FixSra, inputs=List(x35294, Const(1))).name("x35295").ctrl(x35298).srcCtx("CellsPar.scala:29:44") // FixRsh(x35294,Const(1))
    val x35296 = OpDef(op=MuxOp, inputs=List(x35276, x35295, x35275)).name("x35296").ctrl(x35298).srcCtx("CellsPar.scala:218:43") // Mux(x35276,x35295,x35275)
    val x35297 = StoreBanks(List(x34682_d0_b0, x34682_d1_b0), List(b22775, x35264), x35296).name("x35297").ctrl(x35298).srcCtx("CellsPar.scala:218:38") // SRAMStore(x34682,ArrayBuffer(Const(1), Const(512)),List(b22775, x35264),Const(0),x35296,x35271)
    val x35302 = Counter(min=Const(0), max=Const(512), step=Const(1), par=1).name("x35302").ctrl(x36742).srcCtx("CellsPar.scala:243:50") // CounterNew(Const(0),Const(512),Const(1),Const(1))
    val x35303 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x35303").ctrl(x36742).srcCtx("CellsPar.scala:243:27") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x35304 = CounterChain(List(x35303,x35302)).name("x35304").ctrl(x36742).srcCtx("CellsPar.scala:243:62") // CounterChainNew(List(x35303, x35302))
    val x35337 = LoopController(style=InnerPipe, level=InnerControl, cchain=x35304).name("x35337").ctrl(x36742).srcCtx("CellsPar.scala:243:62") // UnrolledForeach(List(b22320),x35304,Block(Const(())),List(List(b22854), List(b22855)),List(List(b22856), List(b22857)))
    val b22854 = CounterIter(x35303, Some(0)).name("b22854").ctrl(x35337) // b22854
    val b22856 = Const(true).name("b22856").ctrl(x35337) // b22856
    val b22855 = CounterIter(x35302, None).name("b22855").ctrl(x35337) // b22855
    val b22857 = Const(true).name("b22857").ctrl(x35337) // b22857
    val x35305 = OpDef(op=BitAnd, inputs=List(b22856, b22857)).name("x35305").ctrl(x35337).srcCtx("UnrollingBase.scala:28:66") // And(b22856,b22857)
    val x35306 = OpDef(op=BitAnd, inputs=List(x35305, b22320)).name("x35306").ctrl(x35337).srcCtx("UnrollingBase.scala:28:66") // And(x35305,b22320)
    val x35307 = LoadBanks(List(x34678_d0_b0), List(b22854, b22855)).name("x35307").ctrl(x35337).srcCtx("CellsPar.scala:244:22") // ParSRAMLoad(x34678,List(List(b22854, b22855)),List(x35306))
    val x35308 = x35307 // x35308 = VectorApply(x35307,0)
    val x35309 = LoadBanks(List(x34681_d0_b0), List(b22854, b22855)).name("x35309").ctrl(x35337).srcCtx("CellsPar.scala:244:34") // ParSRAMLoad(x34681,List(List(b22854, b22855)),List(x35306))
    val x35310 = x35309 // x35310 = VectorApply(x35309,0)
    val x35311 = OpDef(op=FixMul, inputs=List(x35308, x35310)).name("x35311").ctrl(x35337).srcCtx("CellsPar.scala:244:28") // FixMul(x35308,x35310)
    val x35312 = LoadBanks(List(x34679_d0_b0), List(b22854, b22855)).name("x35312").ctrl(x35337).srcCtx("CellsPar.scala:244:46") // ParSRAMLoad(x34679,List(List(b22854, b22855)),List(x35306))
    val x35313 = x35312 // x35313 = VectorApply(x35312,0)
    val x35314 = LoadBanks(List(x34680_d0_b0), List(b22854, b22855)).name("x35314").ctrl(x35337).srcCtx("CellsPar.scala:244:59") // ParSRAMLoad(x34680,List(List(b22854, b22855)),List(x35306))
    val x35315 = x35314 // x35315 = VectorApply(x35314,0)
    val x35316 = OpDef(op=FixMul, inputs=List(x35313, x35315)).name("x35316").ctrl(x35337).srcCtx("CellsPar.scala:244:52") // FixMul(x35313,x35315)
    val x35317 = OpDef(op=FixAdd, inputs=List(x35311, x35316)).name("x35317").ctrl(x35337).srcCtx("CellsPar.scala:244:40:new_c") // FixAdd(x35311,x35316)
    val x35318 = x35317 // FixConvert(x35317,TRUE,_8,_24) (Same Type. No op)
    val x35319 = OpDef(op=FixAbs, inputs=List(x35318)).name("x35319").ctrl(x35337).srcCtx("CellsPar.scala:37:19:absp") // FixAbs(x35318)
    val x35320 = OpDef(op=FixLt, inputs=List(Const(2.5), x35319)).name("x35320").ctrl(x35337).srcCtx("CellsPar.scala:51:23:cond1") // FixLt(Const(2.5),x35319)
    val x35321 = OpDef(op=FixLt, inputs=List(Const(0.5), x35319)).name("x35321").ctrl(x35337).srcCtx("CellsPar.scala:54:35") // FixLt(Const(0.5),x35319)
    val x35322 = OpDef(op=FixLeq, inputs=List(x35319, Const(2.5))).name("x35322").ctrl(x35337).srcCtx("CellsPar.scala:54:52") // FixLeq(x35319,Const(2.5))
    val x35323 = OpDef(op=BitAnd, inputs=List(x35321, x35322)).name("x35323").ctrl(x35337).srcCtx("CellsPar.scala:54:43:cond2") // And(x35321,x35322)
    val x35324 = OpDef(op=FixSra, inputs=List(x35319, Const(2))).name("x35324").ctrl(x35337).srcCtx("CellsPar.scala:56:21:div4") // FixRsh(x35319,Const(2))
    val x35325 = OpDef(op=FixAdd, inputs=List(x35324, Const(0.375))).name("x35325").ctrl(x35337).srcCtx("CellsPar.scala:57:27:div4Offset") // FixAdd(x35324,Const(0.375))
    val x35326 = OpDef(op=MuxOp, inputs=List(x35323, x35325, x35319)).name("x35326").ctrl(x35337).srcCtx("CellsPar.scala:58:20:body2") // Mux(x35323,x35325,x35319)
    val x35327 = OpDef(op=MuxOp, inputs=List(x35320, Const(1.0), x35326)).name("x35327").ctrl(x35337).srcCtx("CellsPar.scala:60:20:absre") // Mux(x35320,Const(1),x35326)
    val x35328 = OpDef(op=FixNeg, inputs=List(x35327)).name("x35328").ctrl(x35337).srcCtx("CellsPar.scala:62:29:mabre") // FixNeg(x35327)
    val x35329 = OpDef(op=FixLt, inputs=List(x35318, Const(0.0))).name("x35329").ctrl(x35337).srcCtx("CellsPar.scala:63:22") // FixLt(x35318,Const(0))
    val x35330 = OpDef(op=MuxOp, inputs=List(x35329, x35328, x35327)).name("x35330").ctrl(x35337).srcCtx("CellsPar.scala:63:17:re") // Mux(x35329,x35328,x35327)
    val x35331 = x35330 // FixConvert(x35330,TRUE,_8,_24) (Same Type. No op)
    val x35332 = LoadBanks(List(x34682_d0_b0), List(b22854, b22855)).name("x35332").ctrl(x35337).srcCtx("CellsPar.scala:245:45") // ParSRAMLoad(x34682,List(List(b22854, b22855)),List(x35306))
    val x35333 = x35332 // x35333 = VectorApply(x35332,0)
    val x35334 = OpDef(op=FixMul, inputs=List(x35331, x35333)).name("x35334").ctrl(x35337).srcCtx("CellsPar.scala:245:39") // FixMul(x35331,x35333)
    val x35335 = StoreBanks(List(x34677_d0_b0, x34677_d5_b0, x34677_d1_b0, x34677_d6_b0, x34677_d2_b0, x34677_d7_b0, x34677_d3_b0, x34677_d4_b0), List(b22854, b22855), x35334).name("x35335").ctrl(x35337).srcCtx("CellsPar.scala:245:16") // ParSRAMStore(x34677,List(List(b22854, b22855)),List(x35334),List(x35306))
    val x35336 = StoreBanks(List(x34678_d0_b0), List(b22854, b22855), x35317).name("x35336").ctrl(x35337).srcCtx("CellsPar.scala:246:16") // ParSRAMStore(x34678,List(List(b22854, b22855)),List(x35317),List(x35306))
    val x35338 = Counter(min=Const(0), max=Const(512), step=Const(16), par=1).name("x35338").ctrl(x36742).srcCtx("CellsPar.scala:190:58") // CounterNew(Const(0),Const(512),Const(16),Const(1))
    val x35339 = Counter(min=Const(0), max=Const(1536), step=Const(16), par=1).name("x35339").ctrl(x36742).srcCtx("CellsPar.scala:190:33") // CounterNew(Const(0),Const(1536),Const(16),Const(1))
    val x35340 = CounterChain(List(x35339,x35338)).name("x35340").ctrl(x36742).srcCtx("CellsPar.scala:190:72") // CounterChainNew(List(x35339, x35338))
    val x35445 = LoopController(style=MetaPipe, level=OuterControl, cchain=x35340).name("x35445").ctrl(x36742).srcCtx("CellsPar.scala:190:72") // UnrolledForeach(List(b22320),x35340,Block(Const(())),List(List(b22894), List(b22895)),List(List(b22896), List(b22897)))
    val b22894 = CounterIter(x35339, Some(0)).name("b22894").ctrl(x35445) // b22894
    val b22896 = Const(true).name("b22896").ctrl(x35445) // b22896
    val b22895 = CounterIter(x35338, Some(0)).name("b22895").ctrl(x35445) // b22895
    val b22897 = Const(true).name("b22897").ctrl(x35445) // b22897
    val x35341_d0_b0 = SRAM(size=256, banking=Strided(banks=1, stride=16)).name("x35341_d0_b0").ctrl(x35445).srcCtx("CellsPar.scala:191:33:tileKernel") // x35341 = SRAMNew(ArrayBuffer(Const(16), Const(16)))
    isAccum(x35341_d0_b0) = false
    bufferDepthOf(x35341_d0_b0) = 2
    val x35344 = UnitController(style=SeqPipe, level=InnerControl).name("x35344").ctrl(x35445).srcCtx("CellsPar.scala:190:72") // UnitPipe(List(b22896, b22897, b22320),Block(Const(())))
    val x35342 = OpDef(op=FixAdd, inputs=List(b22894, Const(16))).name("x35342").ctrl(x35344).srcCtx("CellsPar.scala:192:36") // FixAdd(b22894,Const(16))
    val x35343 = OpDef(op=FixAdd, inputs=List(b22895, Const(16))).name("x35343").ctrl(x35344).srcCtx("CellsPar.scala:192:45") // FixAdd(b22895,Const(16))
    val x35345 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x35345").ctrl(x35445).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x35346 = CounterChain(List(x35345)).name("x35346").ctrl(x35445).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x35345))
    val x35375 = LoopController(style=StreamPipe, level=OuterControl, cchain=x35346).name("x35375").ctrl(x35445).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b22896, b22897, b22320),x35346,Block(Const(())),List(List(b22904)),List(List(b22905)))
    val b22904 = CounterIter(x35345, Some(0)).name("b22904").ctrl(x35375) // b22904
    val b22905 = Const(true).name("b22905").ctrl(x35375) // b22905
    val b36845 = StreamOut(field="offset").name("b36845").ctrl(x35375).srcCtx("CellsPar.scala:192:20") // x35347 = StreamOutNew(BurstCmdBus)
    isAccum(b36845) = false
    bufferDepthOf(b36845) = 1
    val b36846 = StreamOut(field="size").name("b36846").ctrl(x35375).srcCtx("CellsPar.scala:192:20") // x35347 = StreamOutNew(BurstCmdBus)
    isAccum(b36846) = false
    bufferDepthOf(b36846) = 1
    val x35348 = StreamIn(field="data").name("x35348").ctrl(x35375).srcCtx("CellsPar.scala:192:20") // x35348 = StreamInNew(BurstDataBus())
    isAccum(x35348) = false
    bufferDepthOf(x35348) = 1
    val x35363 = UnitController(style=SeqPipe, level=InnerControl).name("x35363").ctrl(x35375).srcCtx("CellsPar.scala:192:20") // UnitPipe(List(b22905, b22896, b22897, b22320),Block(x35362))
    val x35349 = OpDef(op=FixAdd, inputs=List(b22894, b22904)).name("x35349").ctrl(x35363).srcCtx("CellsPar.scala:192:20") // FixAdd(b22894,b22904)
    val x35350 = x35349 // FixConvert(x35349,TRUE,_32,_0) (Same Type. No op)
    val x35351 = OpDef(op=FixSla, inputs=List(x35350, Const(9))).name("x35351").ctrl(x35363).srcCtx("CellsPar.scala:192:20") // FixLsh(x35350,Const(9))
    val x35352 = b22895 // FixConvert(b22895,TRUE,_32,_0) (Same Type. No op)
    val x35353 = OpDef(op=FixAdd, inputs=List(x35351, x35352)).name("x35353").ctrl(x35363).srcCtx("CellsPar.scala:192:20") // FixAdd(x35351,x35352)
    val x35354 = OpDef(op=FixSla, inputs=List(x35353, Const(2))).name("x35354").ctrl(x35363).srcCtx("CellsPar.scala:192:20") // FixLsh(x35353,Const(2))
    val x35355 = x35354 // FixConvert(x35354,TRUE,_64,_0)
    val x35356 = DramAddress(x34476).name("x35356").ctrl(x35363).srcCtx("CellsPar.scala:192:20") // GetDRAMAddress(x34476)
    val x35358_x35357 = OpDef(op=FixAdd, inputs=List(x35355, x35356)).name("x35358_x35357").ctrl(x35363).srcCtx("CellsPar.scala:192:20") // FixAdd(x35355,x35356)
    // x35358 = SimpleStruct(ArrayBuffer((offset,x35357), (size,Const(64)), (isLoad,Const(true))))
    val x35359 = OpDef(op=BitAnd, inputs=List(b22905, b22896)).name("x35359").ctrl(x35363).srcCtx("UnrollingBase.scala:28:66") // And(b22905,b22896)
    val x35360 = OpDef(op=BitAnd, inputs=List(b22897, b22320)).name("x35360").ctrl(x35363).srcCtx("UnrollingBase.scala:28:66") // And(b22897,b22320)
    val x35361 = OpDef(op=BitAnd, inputs=List(x35359, x35360)).name("x35361").ctrl(x35363).srcCtx("UnrollingBase.scala:28:66") // And(x35359,x35360)
    val x35362_b36847_b36845 = WriteMem(b36845, x35358_x35357).name("x35362_b36847_b36845").ctrl(x35363).srcCtx("CellsPar.scala:192:20") // StreamWrite(x35347,x35358,x35361)
    val x35362_b36848_b36846 = WriteMem(b36846, Const(64)).name("x35362_b36848_b36846").ctrl(x35363).srcCtx("CellsPar.scala:192:20") // StreamWrite(x35347,x35358,x35361)
    val x35364 = FringeDenseLoad(dram=List(x34476), cmdStream=List(b36845, b36846), dataStream=List(x35348)).name("x35364").ctrl(x35375).srcCtx("CellsPar.scala:192:20") // FringeDenseLoad(x34476,x35347,x35348)
    val x35365 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x35365").ctrl(x35375).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x35366 = CounterChain(List(x35365)).name("x35366").ctrl(x35375).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x35365))
    val x35374 = LoopController(style=InnerPipe, level=InnerControl, cchain=x35366).name("x35374").ctrl(x35375).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b22905, b22896, b22897, b22320),x35366,Block(Const(())),List(List(b22926)),List(List(b22927)))
    val b22926 = CounterIter(x35365, None).name("b22926").ctrl(x35374) // b22926
    val b22927 = Const(true).name("b22927").ctrl(x35374) // b22927
    val x35367 = OpDef(op=BitAnd, inputs=List(b22927, b22905)).name("x35367").ctrl(x35374).srcCtx("UnrollingBase.scala:28:66") // And(b22927,b22905)
    val x35368 = OpDef(op=BitAnd, inputs=List(b22896, b22897)).name("x35368").ctrl(x35374).srcCtx("UnrollingBase.scala:28:66") // And(b22896,b22897)
    val x35369 = OpDef(op=BitAnd, inputs=List(x35367, x35368)).name("x35369").ctrl(x35374).srcCtx("UnrollingBase.scala:28:66") // And(x35367,x35368)
    val x35370 = OpDef(op=BitAnd, inputs=List(x35369, b22320)).name("x35370").ctrl(x35374).srcCtx("UnrollingBase.scala:28:66") // And(x35369,b22320)
    val x35371_x35371 = ReadMem(x35348).name("x35371_x35371").ctrl(x35374).srcCtx("CellsPar.scala:192:20") // ParStreamRead(x35348,List(x35370))
    val x35372_x35372 = x35371_x35371 // x35372 = VectorApply(x35371,0)
    val x35373 = StoreBanks(List(x35341_d0_b0), List(b22904, b22926), x35372_x35372).name("x35373").ctrl(x35374).srcCtx("CellsPar.scala:192:20") // ParSRAMStore(x35341,List(List(b22904, b22926)),List(x35372),List(x35370))
    val x35376 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x35376").ctrl(x35445).srcCtx("CellsPar.scala:193:34") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x35377 = CounterChain(List(x35376)).name("x35377").ctrl(x35445).srcCtx("CellsPar.scala:193:46") // CounterChainNew(List(x35376))
    val x35444 = LoopController(style=MetaPipe, level=OuterControl, cchain=x35377).name("x35444").ctrl(x35445).srcCtx("CellsPar.scala:193:46") // UnrolledForeach(List(b22896, b22897, b22320),x35377,Block(Const(())),List(List(b22939)),List(List(b22940)))
    val b22939 = CounterIter(x35376, Some(0)).name("b22939").ctrl(x35444) // b22939
    val b22940 = Const(true).name("b22940").ctrl(x35444) // b22940
    val x35378 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x35378").ctrl(x35444).srcCtx("CellsPar.scala:194:28") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x35379 = CounterChain(List(x35378)).name("x35379").ctrl(x35444).srcCtx("CellsPar.scala:194:42") // CounterChainNew(List(x35378))
    val x35443 = LoopController(style=MetaPipe, level=OuterControl, cchain=x35379).name("x35443").ctrl(x35444).srcCtx("CellsPar.scala:194:42") // UnrolledForeach(List(b22940, b22896, b22897, b22320),x35379,Block(Const(())),List(List(b22943)),List(List(b22944)))
    val b22943 = CounterIter(x35378, Some(0)).name("b22943").ctrl(x35443) // b22943
    val b22944 = Const(true).name("b22944").ctrl(x35443) // b22944
    val x35380_d0 = Reg(init=Some(0.0)).name("x35380_d0").ctrl(x35443).srcCtx("CellsPar.scala:195:34:prod") // x35380 = RegNew(Const(0))
    isAccum(x35380_d0) = false
    bufferDepthOf(x35380_d0) = 2
    val x35380_d1 = Reg(init=Some(0.0)).name("x35380_d1").ctrl(x35443).srcCtx("CellsPar.scala:195:34:prod") // x35380 = RegNew(Const(0))
    isAccum(x35380_d1) = true
    bufferDepthOf(x35380_d1) = 1
    val x35381 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x35381").ctrl(x35443).srcCtx("CellsPar.scala:195:43") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x35382 = CounterChain(List(x35381)).name("x35382").ctrl(x35443).srcCtx("CellsPar.scala:207:15") // CounterChainNew(List(x35381))
    val x35408 = LoopController(style=InnerPipe, level=InnerControl, cchain=x35382).name("x35408").ctrl(x35443).srcCtx("CellsPar.scala:207:15") // UnrolledReduce(List(b22944, b22940, b22896, b22897, b22320),x35382,x35380,Block((x35380) => Const(())),List(List(b22948)),List(List(b22949)))
    val b22948 = CounterIter(x35381, None).name("b22948").ctrl(x35408) // b22948
    val b22949 = Const(true).name("b22949").ctrl(x35408) // b22949
    val x35383 = OpDef(op=FixAdd, inputs=List(b22894, b22948)).name("x35383").ctrl(x35408).srcCtx("CellsPar.scala:196:42:reduce_size_offset") // FixAdd(b22894,b22948)
    val x35384 = OpDef(op=BitAnd, inputs=List(b22949, b22944)).name("x35384").ctrl(x35408).srcCtx("UnrollingBase.scala:28:66") // And(b22949,b22944)
    val x35385 = OpDef(op=BitAnd, inputs=List(b22940, b22896)).name("x35385").ctrl(x35408).srcCtx("UnrollingBase.scala:28:66") // And(b22940,b22896)
    val x35386 = OpDef(op=BitAnd, inputs=List(b22897, b22320)).name("x35386").ctrl(x35408).srcCtx("UnrollingBase.scala:28:66") // And(b22897,b22320)
    val x35387 = OpDef(op=BitAnd, inputs=List(x35384, x35385)).name("x35387").ctrl(x35408).srcCtx("UnrollingBase.scala:28:66") // And(x35384,x35385)
    val x35388 = OpDef(op=BitAnd, inputs=List(x35387, x35386)).name("x35388").ctrl(x35408).srcCtx("UnrollingBase.scala:28:66") // And(x35387,x35386)
    val x35389 = LoadBanks(List(x35341_d0_b0), List(b22948, b22943)).name("x35389").ctrl(x35408).srcCtx("CellsPar.scala:203:34:tk") // ParSRAMLoad(x35341,List(List(b22948, b22943)),List(x35388))
    val x35390 = x35389 // x35390 = VectorApply(x35389,0)
    val x35391 = LoadBanks(List(x34677_d3_b0), List(b22939, x35383)).name("x35391").ctrl(x35408).srcCtx("CellsPar.scala:204:28") // ParSRAMLoad(x34677,List(List(b22939, x35383)),List(x35388))
    val x35392 = x35391 // x35392 = VectorApply(x35391,0)
    val x35393 = OpDef(op=FixMul, inputs=List(x35392, x35390)).name("x35393").ctrl(x35408).srcCtx("CellsPar.scala:204:52:tempA") // FixMul(x35392,x35390)
    val x35394 = OpDef(op=FixSub, inputs=List(x35383, Const(512))).name("x35394").ctrl(x35408).srcCtx("CellsPar.scala:205:51") // FixSub(x35383,Const(512))
    val x35395 = LoadBanks(List(x34684_d7_b0), List(b22939, x35394)).name("x35395").ctrl(x35408).srcCtx("CellsPar.scala:205:28") // ParSRAMLoad(x34684,List(List(b22939, x35394)),List(x35388))
    val x35396 = x35395 // x35396 = VectorApply(x35395,0)
    val x35397 = OpDef(op=FixMul, inputs=List(x35396, x35390)).name("x35397").ctrl(x35408).srcCtx("CellsPar.scala:205:67:tempB") // FixMul(x35396,x35390)
    val x35398 = OpDef(op=FixLt, inputs=List(x35383, Const(512))).name("x35398").ctrl(x35408).srcCtx("CellsPar.scala:206:38") // FixLt(x35383,Const(512))
    val x35399 = OpDef(op=MuxOp, inputs=List(x35398, x35393, x35397)).name("x35399").ctrl(x35408).srcCtx("CellsPar.scala:206:18") // Mux(x35398,x35393,x35397)
    val x35400 = ReadMem(x35380_d1).name("x35400").ctrl(x35408).srcCtx("CellsPar.scala:207:15") // RegRead(x35380)
    val x35401 = OpDef(op=FixEql, inputs=List(b22948, Const(0))).name("x35401").ctrl(x35408).srcCtx("CellsPar.scala:207:15") // FixEql(b22948,Const(0))
    val x35402 = ReduceAccumOp(op=FixAdd, input=x35399, accum=x35400).name("x35402").ctrl(x35408).srcCtx("CellsPar.scala:207:17") // FixAdd(x35399,x35400)
    val x35403 = OpDef(op=BitAnd, inputs=List(b22944, b22940)).name("x35403").ctrl(x35408).srcCtx("UnrollingBase.scala:28:66") // And(b22944,b22940)
    val x35404 = OpDef(op=BitAnd, inputs=List(b22896, b22897)).name("x35404").ctrl(x35408).srcCtx("UnrollingBase.scala:28:66") // And(b22896,b22897)
    val x35405 = OpDef(op=BitAnd, inputs=List(x35403, x35404)).name("x35405").ctrl(x35408).srcCtx("UnrollingBase.scala:28:66") // And(x35403,x35404)
    val x35406 = OpDef(op=BitAnd, inputs=List(x35405, b22320)).name("x35406").ctrl(x35408).srcCtx("UnrollingBase.scala:28:66") // And(x35405,b22320)
    val x35407_x35380_d0 = WriteMem(x35380_d0, x35402).name("x35407_x35380_d0").ctrl(x35408).srcCtx("CellsPar.scala:207:15") // RegWrite(x35380,x35402,x35406)
    val x35407_x35380_d1 = WriteMem(x35380_d1, x35402).name("x35407_x35380_d1").ctrl(x35408).srcCtx("CellsPar.scala:207:15") // RegWrite(x35380,x35402,x35406)
    val x35442 = UnitController(style=SeqPipe, level=InnerControl).name("x35442").ctrl(x35443).srcCtx("CellsPar.scala:194:42") // UnitPipe(List(b22944, b22940, b22896, b22897, b22320),Block(Const(())))
    val x35409 = OpDef(op=FixAdd, inputs=List(b22895, b22943)).name("x35409").ctrl(x35442).srcCtx("CellsPar.scala:209:31:colOffset") // FixAdd(b22895,b22943)
    val x35410 = ReadMem(x35380_d0).name("x35410").ctrl(x35442).srcCtx("CellsPar.scala:210:28") // RegRead(x35380)
    val x35411 = OpDef(op=FixEql, inputs=List(b22894, Const(0))).name("x35411").ctrl(x35442).srcCtx("CellsPar.scala:210:42") // FixEql(b22894,Const(0))
    val x35412 = OpDef(op=BitAnd, inputs=List(b22944, b22940)).name("x35412").ctrl(x35442).srcCtx("UnrollingBase.scala:28:66") // And(b22944,b22940)
    val x35413 = OpDef(op=BitAnd, inputs=List(b22896, b22897)).name("x35413").ctrl(x35442).srcCtx("UnrollingBase.scala:28:66") // And(b22896,b22897)
    val x35414 = OpDef(op=BitAnd, inputs=List(x35412, x35413)).name("x35414").ctrl(x35442).srcCtx("UnrollingBase.scala:28:66") // And(x35412,x35413)
    val x35415 = OpDef(op=BitAnd, inputs=List(x35414, b22320)).name("x35415").ctrl(x35442).srcCtx("UnrollingBase.scala:28:66") // And(x35414,b22320)
    val x35416 = LoadBanks(List(x34690_d3_b0), List(Const(0), x35409)).name("x35416").ctrl(x35442).srcCtx("CellsPar.scala:210:52") // SRAMLoad(x34690,ArrayBuffer(Const(1), Const(2048)),List(Const(0), x35409),Const(0),x35415)
    val x35417 = LoadBanks(List(x34686_d1_b0), List(b22939, x35409)).name("x35417").ctrl(x35442).srcCtx("CellsPar.scala:210:91") // SRAMLoad(x34686,ArrayBuffer(Const(1), Const(512)),List(b22939, x35409),Const(0),x35415)
    val x35418 = OpDef(op=MuxOp, inputs=List(x35411, x35416, x35417)).name("x35418").ctrl(x35442).srcCtx("CellsPar.scala:210:39") // Mux(x35411,x35416,x35417)
    val x35419 = OpDef(op=FixAdd, inputs=List(x35410, x35418)).name("x35419").ctrl(x35442).srcCtx("CellsPar.scala:210:34:ele") // FixAdd(x35410,x35418)
    val x35420 = OpDef(op=FixLeq, inputs=List(Const(1520), b22894)).name("x35420").ctrl(x35442).srcCtx("CellsPar.scala:218:46") // FixLeq(Const(1520),b22894)
    // x35421 = FixConvert(Const(0),TRUE,_8,_24) x.tp=FixPt[TRUE,_32,_0] {
    val x35421_int1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("01111111111111111111111111111111"))).name("x35421_int1").ctrl(x35442).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x35421_int2 = OpDef(op=FixSla, inputs=List(x35421_int1, Const(24))).name("x35421_int2").ctrl(x35442).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x35421_frac1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("00000000000000000000000000000000"))).name("x35421_frac1").ctrl(x35442).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x35421_frac2 = OpDef(op=FixSla, inputs=List(x35421_frac1, Const(24))).name("x35421_frac2").ctrl(x35442).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x35421 = OpDef(op=BitOr, inputs=List(x35421_int2, x35421_frac2)).name("x35421").ctrl(x35442).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    // }
    val x35422 = OpDef(op=FixAdd, inputs=List(x35419, x35421)).name("x35422").ctrl(x35442).srcCtx("CellsPar.scala:218:87") // FixAdd(x35419,x35421)
    val x35423 = OpDef(op=FixSra, inputs=List(x35422, Const(1))).name("x35423").ctrl(x35442).srcCtx("CellsPar.scala:29:22") // FixRsh(x35422,Const(1))
    val x35424 = x35423 // FixConvert(x35423,TRUE,_8,_24) (Same Type. No op)
    val x35425 = OpDef(op=FixAbs, inputs=List(x35424)).name("x35425").ctrl(x35442).srcCtx("CellsPar.scala:37:19:absp") // FixAbs(x35424)
    val x35426 = OpDef(op=FixLt, inputs=List(Const(2.5), x35425)).name("x35426").ctrl(x35442).srcCtx("CellsPar.scala:51:23:cond1") // FixLt(Const(2.5),x35425)
    val x35427 = OpDef(op=FixLt, inputs=List(Const(0.5), x35425)).name("x35427").ctrl(x35442).srcCtx("CellsPar.scala:54:35") // FixLt(Const(0.5),x35425)
    val x35428 = OpDef(op=FixLeq, inputs=List(x35425, Const(2.5))).name("x35428").ctrl(x35442).srcCtx("CellsPar.scala:54:52") // FixLeq(x35425,Const(2.5))
    val x35429 = OpDef(op=BitAnd, inputs=List(x35427, x35428)).name("x35429").ctrl(x35442).srcCtx("CellsPar.scala:54:43:cond2") // And(x35427,x35428)
    val x35430 = OpDef(op=FixSra, inputs=List(x35425, Const(2))).name("x35430").ctrl(x35442).srcCtx("CellsPar.scala:56:21:div4") // FixRsh(x35425,Const(2))
    val x35431 = OpDef(op=FixAdd, inputs=List(x35430, Const(0.375))).name("x35431").ctrl(x35442).srcCtx("CellsPar.scala:57:27:div4Offset") // FixAdd(x35430,Const(0.375))
    val x35432 = OpDef(op=MuxOp, inputs=List(x35429, x35431, x35425)).name("x35432").ctrl(x35442).srcCtx("CellsPar.scala:58:20:body2") // Mux(x35429,x35431,x35425)
    val x35433 = OpDef(op=MuxOp, inputs=List(x35426, Const(1.0), x35432)).name("x35433").ctrl(x35442).srcCtx("CellsPar.scala:60:20:absre") // Mux(x35426,Const(1),x35432)
    val x35434 = OpDef(op=FixNeg, inputs=List(x35433)).name("x35434").ctrl(x35442).srcCtx("CellsPar.scala:62:29:mabre") // FixNeg(x35433)
    val x35435 = OpDef(op=FixLt, inputs=List(x35424, Const(0.0))).name("x35435").ctrl(x35442).srcCtx("CellsPar.scala:63:22") // FixLt(x35424,Const(0))
    val x35436 = OpDef(op=MuxOp, inputs=List(x35435, x35434, x35433)).name("x35436").ctrl(x35442).srcCtx("CellsPar.scala:63:17:re") // Mux(x35435,x35434,x35433)
    val x35437 = x35436 // FixConvert(x35436,TRUE,_8,_24) (Same Type. No op)
    val x35438 = OpDef(op=FixAdd, inputs=List(x35437, Const(1.0))).name("x35438").ctrl(x35442).srcCtx("CellsPar.scala:29:33") // FixAdd(x35437,Const(1))
    val x35439 = OpDef(op=FixSra, inputs=List(x35438, Const(1))).name("x35439").ctrl(x35442).srcCtx("CellsPar.scala:29:44") // FixRsh(x35438,Const(1))
    val x35440 = OpDef(op=MuxOp, inputs=List(x35420, x35439, x35419)).name("x35440").ctrl(x35442).srcCtx("CellsPar.scala:218:43") // Mux(x35420,x35439,x35419)
    val x35441 = StoreBanks(List(x34686_d0_b0, x34686_d1_b0), List(b22939, x35409), x35440).name("x35441").ctrl(x35442).srcCtx("CellsPar.scala:218:38") // SRAMStore(x34686,ArrayBuffer(Const(1), Const(512)),List(b22939, x35409),Const(0),x35440,x35415)
    val x35446 = Counter(min=Const(0), max=Const(512), step=Const(16), par=1).name("x35446").ctrl(x36742).srcCtx("CellsPar.scala:190:58") // CounterNew(Const(0),Const(512),Const(16),Const(1))
    val x35447 = Counter(min=Const(0), max=Const(1536), step=Const(16), par=1).name("x35447").ctrl(x36742).srcCtx("CellsPar.scala:190:33") // CounterNew(Const(0),Const(1536),Const(16),Const(1))
    val x35448 = CounterChain(List(x35447,x35446)).name("x35448").ctrl(x36742).srcCtx("CellsPar.scala:190:72") // CounterChainNew(List(x35447, x35446))
    val x35551 = LoopController(style=MetaPipe, level=OuterControl, cchain=x35448).name("x35551").ctrl(x36742).srcCtx("CellsPar.scala:190:72") // UnrolledForeach(List(b22320),x35448,Block(Const(())),List(List(b23016), List(b23017)),List(List(b23018), List(b23019)))
    val b23016 = CounterIter(x35447, Some(0)).name("b23016").ctrl(x35551) // b23016
    val b23018 = Const(true).name("b23018").ctrl(x35551) // b23018
    val b23017 = CounterIter(x35446, Some(0)).name("b23017").ctrl(x35551) // b23017
    val b23019 = Const(true).name("b23019").ctrl(x35551) // b23019
    val x35449_d0_b0 = SRAM(size=256, banking=Strided(banks=1, stride=16)).name("x35449_d0_b0").ctrl(x35551).srcCtx("CellsPar.scala:191:33:tileKernel") // x35449 = SRAMNew(ArrayBuffer(Const(16), Const(16)))
    isAccum(x35449_d0_b0) = false
    bufferDepthOf(x35449_d0_b0) = 2
    val x35452 = UnitController(style=SeqPipe, level=InnerControl).name("x35452").ctrl(x35551).srcCtx("CellsPar.scala:190:72") // UnitPipe(List(b23018, b23019, b22320),Block(Const(())))
    val x35450 = OpDef(op=FixAdd, inputs=List(b23016, Const(16))).name("x35450").ctrl(x35452).srcCtx("CellsPar.scala:192:36") // FixAdd(b23016,Const(16))
    val x35451 = OpDef(op=FixAdd, inputs=List(b23017, Const(16))).name("x35451").ctrl(x35452).srcCtx("CellsPar.scala:192:45") // FixAdd(b23017,Const(16))
    val x35453 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x35453").ctrl(x35551).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x35454 = CounterChain(List(x35453)).name("x35454").ctrl(x35551).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x35453))
    val x35483 = LoopController(style=StreamPipe, level=OuterControl, cchain=x35454).name("x35483").ctrl(x35551).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b23018, b23019, b22320),x35454,Block(Const(())),List(List(b23026)),List(List(b23027)))
    val b23026 = CounterIter(x35453, Some(0)).name("b23026").ctrl(x35483) // b23026
    val b23027 = Const(true).name("b23027").ctrl(x35483) // b23027
    val b36849 = StreamOut(field="offset").name("b36849").ctrl(x35483).srcCtx("CellsPar.scala:192:20") // x35455 = StreamOutNew(BurstCmdBus)
    isAccum(b36849) = false
    bufferDepthOf(b36849) = 1
    val b36850 = StreamOut(field="size").name("b36850").ctrl(x35483).srcCtx("CellsPar.scala:192:20") // x35455 = StreamOutNew(BurstCmdBus)
    isAccum(b36850) = false
    bufferDepthOf(b36850) = 1
    val x35456 = StreamIn(field="data").name("x35456").ctrl(x35483).srcCtx("CellsPar.scala:192:20") // x35456 = StreamInNew(BurstDataBus())
    isAccum(x35456) = false
    bufferDepthOf(x35456) = 1
    val x35471 = UnitController(style=SeqPipe, level=InnerControl).name("x35471").ctrl(x35483).srcCtx("CellsPar.scala:192:20") // UnitPipe(List(b23027, b23018, b23019, b22320),Block(x35470))
    val x35457 = OpDef(op=FixAdd, inputs=List(b23016, b23026)).name("x35457").ctrl(x35471).srcCtx("CellsPar.scala:192:20") // FixAdd(b23016,b23026)
    val x35458 = x35457 // FixConvert(x35457,TRUE,_32,_0) (Same Type. No op)
    val x35459 = OpDef(op=FixSla, inputs=List(x35458, Const(9))).name("x35459").ctrl(x35471).srcCtx("CellsPar.scala:192:20") // FixLsh(x35458,Const(9))
    val x35460 = b23017 // FixConvert(b23017,TRUE,_32,_0) (Same Type. No op)
    val x35461 = OpDef(op=FixAdd, inputs=List(x35459, x35460)).name("x35461").ctrl(x35471).srcCtx("CellsPar.scala:192:20") // FixAdd(x35459,x35460)
    val x35462 = OpDef(op=FixSla, inputs=List(x35461, Const(2))).name("x35462").ctrl(x35471).srcCtx("CellsPar.scala:192:20") // FixLsh(x35461,Const(2))
    val x35463 = x35462 // FixConvert(x35462,TRUE,_64,_0)
    val x35464 = DramAddress(x34477).name("x35464").ctrl(x35471).srcCtx("CellsPar.scala:192:20") // GetDRAMAddress(x34477)
    val x35466_x35465 = OpDef(op=FixAdd, inputs=List(x35463, x35464)).name("x35466_x35465").ctrl(x35471).srcCtx("CellsPar.scala:192:20") // FixAdd(x35463,x35464)
    // x35466 = SimpleStruct(ArrayBuffer((offset,x35465), (size,Const(64)), (isLoad,Const(true))))
    val x35467 = OpDef(op=BitAnd, inputs=List(b23027, b23018)).name("x35467").ctrl(x35471).srcCtx("UnrollingBase.scala:28:66") // And(b23027,b23018)
    val x35468 = OpDef(op=BitAnd, inputs=List(b23019, b22320)).name("x35468").ctrl(x35471).srcCtx("UnrollingBase.scala:28:66") // And(b23019,b22320)
    val x35469 = OpDef(op=BitAnd, inputs=List(x35467, x35468)).name("x35469").ctrl(x35471).srcCtx("UnrollingBase.scala:28:66") // And(x35467,x35468)
    val x35470_b36851_b36849 = WriteMem(b36849, x35466_x35465).name("x35470_b36851_b36849").ctrl(x35471).srcCtx("CellsPar.scala:192:20") // StreamWrite(x35455,x35466,x35469)
    val x35470_b36852_b36850 = WriteMem(b36850, Const(64)).name("x35470_b36852_b36850").ctrl(x35471).srcCtx("CellsPar.scala:192:20") // StreamWrite(x35455,x35466,x35469)
    val x35472 = FringeDenseLoad(dram=List(x34477), cmdStream=List(b36849, b36850), dataStream=List(x35456)).name("x35472").ctrl(x35483).srcCtx("CellsPar.scala:192:20") // FringeDenseLoad(x34477,x35455,x35456)
    val x35473 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x35473").ctrl(x35483).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x35474 = CounterChain(List(x35473)).name("x35474").ctrl(x35483).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x35473))
    val x35482 = LoopController(style=InnerPipe, level=InnerControl, cchain=x35474).name("x35482").ctrl(x35483).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b23027, b23018, b23019, b22320),x35474,Block(Const(())),List(List(b23048)),List(List(b23049)))
    val b23048 = CounterIter(x35473, None).name("b23048").ctrl(x35482) // b23048
    val b23049 = Const(true).name("b23049").ctrl(x35482) // b23049
    val x35475 = OpDef(op=BitAnd, inputs=List(b23049, b23027)).name("x35475").ctrl(x35482).srcCtx("UnrollingBase.scala:28:66") // And(b23049,b23027)
    val x35476 = OpDef(op=BitAnd, inputs=List(b23018, b23019)).name("x35476").ctrl(x35482).srcCtx("UnrollingBase.scala:28:66") // And(b23018,b23019)
    val x35477 = OpDef(op=BitAnd, inputs=List(x35475, x35476)).name("x35477").ctrl(x35482).srcCtx("UnrollingBase.scala:28:66") // And(x35475,x35476)
    val x35478 = OpDef(op=BitAnd, inputs=List(x35477, b22320)).name("x35478").ctrl(x35482).srcCtx("UnrollingBase.scala:28:66") // And(x35477,b22320)
    val x35479_x35479 = ReadMem(x35456).name("x35479_x35479").ctrl(x35482).srcCtx("CellsPar.scala:192:20") // ParStreamRead(x35456,List(x35478))
    val x35480_x35480 = x35479_x35479 // x35480 = VectorApply(x35479,0)
    val x35481 = StoreBanks(List(x35449_d0_b0), List(b23026, b23048), x35480_x35480).name("x35481").ctrl(x35482).srcCtx("CellsPar.scala:192:20") // ParSRAMStore(x35449,List(List(b23026, b23048)),List(x35480),List(x35478))
    val x35484 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x35484").ctrl(x35551).srcCtx("CellsPar.scala:193:34") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x35485 = CounterChain(List(x35484)).name("x35485").ctrl(x35551).srcCtx("CellsPar.scala:193:46") // CounterChainNew(List(x35484))
    val x35550 = LoopController(style=MetaPipe, level=OuterControl, cchain=x35485).name("x35550").ctrl(x35551).srcCtx("CellsPar.scala:193:46") // UnrolledForeach(List(b23018, b23019, b22320),x35485,Block(Const(())),List(List(b23061)),List(List(b23062)))
    val b23061 = CounterIter(x35484, Some(0)).name("b23061").ctrl(x35550) // b23061
    val b23062 = Const(true).name("b23062").ctrl(x35550) // b23062
    val x35486 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x35486").ctrl(x35550).srcCtx("CellsPar.scala:194:28") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x35487 = CounterChain(List(x35486)).name("x35487").ctrl(x35550).srcCtx("CellsPar.scala:194:42") // CounterChainNew(List(x35486))
    val x35549 = LoopController(style=MetaPipe, level=OuterControl, cchain=x35487).name("x35549").ctrl(x35550).srcCtx("CellsPar.scala:194:42") // UnrolledForeach(List(b23062, b23018, b23019, b22320),x35487,Block(Const(())),List(List(b23065)),List(List(b23066)))
    val b23065 = CounterIter(x35486, Some(0)).name("b23065").ctrl(x35549) // b23065
    val b23066 = Const(true).name("b23066").ctrl(x35549) // b23066
    val x35488_d0 = Reg(init=Some(0.0)).name("x35488_d0").ctrl(x35549).srcCtx("CellsPar.scala:195:34:prod") // x35488 = RegNew(Const(0))
    isAccum(x35488_d0) = false
    bufferDepthOf(x35488_d0) = 2
    val x35488_d1 = Reg(init=Some(0.0)).name("x35488_d1").ctrl(x35549).srcCtx("CellsPar.scala:195:34:prod") // x35488 = RegNew(Const(0))
    isAccum(x35488_d1) = true
    bufferDepthOf(x35488_d1) = 1
    val x35489 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x35489").ctrl(x35549).srcCtx("CellsPar.scala:195:43") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x35490 = CounterChain(List(x35489)).name("x35490").ctrl(x35549).srcCtx("CellsPar.scala:207:15") // CounterChainNew(List(x35489))
    val x35516 = LoopController(style=InnerPipe, level=InnerControl, cchain=x35490).name("x35516").ctrl(x35549).srcCtx("CellsPar.scala:207:15") // UnrolledReduce(List(b23066, b23062, b23018, b23019, b22320),x35490,x35488,Block((x35488) => Const(())),List(List(b23070)),List(List(b23071)))
    val b23070 = CounterIter(x35489, None).name("b23070").ctrl(x35516) // b23070
    val b23071 = Const(true).name("b23071").ctrl(x35516) // b23071
    val x35491 = OpDef(op=FixAdd, inputs=List(b23016, b23070)).name("x35491").ctrl(x35516).srcCtx("CellsPar.scala:196:42:reduce_size_offset") // FixAdd(b23016,b23070)
    val x35492 = OpDef(op=BitAnd, inputs=List(b23071, b23066)).name("x35492").ctrl(x35516).srcCtx("UnrollingBase.scala:28:66") // And(b23071,b23066)
    val x35493 = OpDef(op=BitAnd, inputs=List(b23062, b23018)).name("x35493").ctrl(x35516).srcCtx("UnrollingBase.scala:28:66") // And(b23062,b23018)
    val x35494 = OpDef(op=BitAnd, inputs=List(b23019, b22320)).name("x35494").ctrl(x35516).srcCtx("UnrollingBase.scala:28:66") // And(b23019,b22320)
    val x35495 = OpDef(op=BitAnd, inputs=List(x35492, x35493)).name("x35495").ctrl(x35516).srcCtx("UnrollingBase.scala:28:66") // And(x35492,x35493)
    val x35496 = OpDef(op=BitAnd, inputs=List(x35495, x35494)).name("x35496").ctrl(x35516).srcCtx("UnrollingBase.scala:28:66") // And(x35495,x35494)
    val x35497 = LoadBanks(List(x35449_d0_b0), List(b23070, b23065)).name("x35497").ctrl(x35516).srcCtx("CellsPar.scala:203:34:tk") // ParSRAMLoad(x35449,List(List(b23070, b23065)),List(x35496))
    val x35498 = x35497 // x35498 = VectorApply(x35497,0)
    val x35499 = LoadBanks(List(x34677_d2_b0), List(b23061, x35491)).name("x35499").ctrl(x35516).srcCtx("CellsPar.scala:204:28") // ParSRAMLoad(x34677,List(List(b23061, x35491)),List(x35496))
    val x35500 = x35499 // x35500 = VectorApply(x35499,0)
    val x35501 = OpDef(op=FixMul, inputs=List(x35500, x35498)).name("x35501").ctrl(x35516).srcCtx("CellsPar.scala:204:52:tempA") // FixMul(x35500,x35498)
    val x35502 = OpDef(op=FixSub, inputs=List(x35491, Const(512))).name("x35502").ctrl(x35516).srcCtx("CellsPar.scala:205:51") // FixSub(x35491,Const(512))
    val x35503 = LoadBanks(List(x34684_d6_b0), List(b23061, x35502)).name("x35503").ctrl(x35516).srcCtx("CellsPar.scala:205:28") // ParSRAMLoad(x34684,List(List(b23061, x35502)),List(x35496))
    val x35504 = x35503 // x35504 = VectorApply(x35503,0)
    val x35505 = OpDef(op=FixMul, inputs=List(x35504, x35498)).name("x35505").ctrl(x35516).srcCtx("CellsPar.scala:205:67:tempB") // FixMul(x35504,x35498)
    val x35506 = OpDef(op=FixLt, inputs=List(x35491, Const(512))).name("x35506").ctrl(x35516).srcCtx("CellsPar.scala:206:38") // FixLt(x35491,Const(512))
    val x35507 = OpDef(op=MuxOp, inputs=List(x35506, x35501, x35505)).name("x35507").ctrl(x35516).srcCtx("CellsPar.scala:206:18") // Mux(x35506,x35501,x35505)
    val x35508 = ReadMem(x35488_d1).name("x35508").ctrl(x35516).srcCtx("CellsPar.scala:207:15") // RegRead(x35488)
    val x35509 = OpDef(op=FixEql, inputs=List(b23070, Const(0))).name("x35509").ctrl(x35516).srcCtx("CellsPar.scala:207:15") // FixEql(b23070,Const(0))
    val x35510 = ReduceAccumOp(op=FixAdd, input=x35507, accum=x35508).name("x35510").ctrl(x35516).srcCtx("CellsPar.scala:207:17") // FixAdd(x35507,x35508)
    val x35511 = OpDef(op=BitAnd, inputs=List(b23066, b23062)).name("x35511").ctrl(x35516).srcCtx("UnrollingBase.scala:28:66") // And(b23066,b23062)
    val x35512 = OpDef(op=BitAnd, inputs=List(b23018, b23019)).name("x35512").ctrl(x35516).srcCtx("UnrollingBase.scala:28:66") // And(b23018,b23019)
    val x35513 = OpDef(op=BitAnd, inputs=List(x35511, x35512)).name("x35513").ctrl(x35516).srcCtx("UnrollingBase.scala:28:66") // And(x35511,x35512)
    val x35514 = OpDef(op=BitAnd, inputs=List(x35513, b22320)).name("x35514").ctrl(x35516).srcCtx("UnrollingBase.scala:28:66") // And(x35513,b22320)
    val x35515_x35488_d0 = WriteMem(x35488_d0, x35510).name("x35515_x35488_d0").ctrl(x35516).srcCtx("CellsPar.scala:207:15") // RegWrite(x35488,x35510,x35514)
    val x35515_x35488_d1 = WriteMem(x35488_d1, x35510).name("x35515_x35488_d1").ctrl(x35516).srcCtx("CellsPar.scala:207:15") // RegWrite(x35488,x35510,x35514)
    val x35548 = UnitController(style=SeqPipe, level=InnerControl).name("x35548").ctrl(x35549).srcCtx("CellsPar.scala:194:42") // UnitPipe(List(b23066, b23062, b23018, b23019, b22320),Block(Const(())))
    val x35517 = OpDef(op=FixAdd, inputs=List(b23017, b23065)).name("x35517").ctrl(x35548).srcCtx("CellsPar.scala:209:31:colOffset") // FixAdd(b23017,b23065)
    val x35518 = ReadMem(x35488_d0).name("x35518").ctrl(x35548).srcCtx("CellsPar.scala:210:28") // RegRead(x35488)
    val x35519 = OpDef(op=FixEql, inputs=List(b23016, Const(0))).name("x35519").ctrl(x35548).srcCtx("CellsPar.scala:210:42") // FixEql(b23016,Const(0))
    val x35520 = OpDef(op=FixAdd, inputs=List(x35517, Const(512))).name("x35520").ctrl(x35548).srcCtx("CellsPar.scala:210:66") // FixAdd(x35517,Const(512))
    val x35521 = OpDef(op=BitAnd, inputs=List(b23066, b23062)).name("x35521").ctrl(x35548).srcCtx("UnrollingBase.scala:28:66") // And(b23066,b23062)
    val x35522 = OpDef(op=BitAnd, inputs=List(b23018, b23019)).name("x35522").ctrl(x35548).srcCtx("UnrollingBase.scala:28:66") // And(b23018,b23019)
    val x35523 = OpDef(op=BitAnd, inputs=List(x35521, x35522)).name("x35523").ctrl(x35548).srcCtx("UnrollingBase.scala:28:66") // And(x35521,x35522)
    val x35524 = OpDef(op=BitAnd, inputs=List(x35523, b22320)).name("x35524").ctrl(x35548).srcCtx("UnrollingBase.scala:28:66") // And(x35523,b22320)
    val x35525 = LoadBanks(List(x34690_d2_b0), List(Const(0), x35520)).name("x35525").ctrl(x35548).srcCtx("CellsPar.scala:210:52") // SRAMLoad(x34690,ArrayBuffer(Const(1), Const(2048)),List(Const(0), x35520),Const(0),x35524)
    val x35526 = LoadBanks(List(x34687_d1_b0), List(b23061, x35517)).name("x35526").ctrl(x35548).srcCtx("CellsPar.scala:210:91") // SRAMLoad(x34687,ArrayBuffer(Const(1), Const(512)),List(b23061, x35517),Const(0),x35524)
    val x35527 = OpDef(op=MuxOp, inputs=List(x35519, x35525, x35526)).name("x35527").ctrl(x35548).srcCtx("CellsPar.scala:210:39") // Mux(x35519,x35525,x35526)
    val x35528 = OpDef(op=FixAdd, inputs=List(x35518, x35527)).name("x35528").ctrl(x35548).srcCtx("CellsPar.scala:210:34:ele") // FixAdd(x35518,x35527)
    val x35529 = OpDef(op=FixLeq, inputs=List(Const(1520), b23016)).name("x35529").ctrl(x35548).srcCtx("CellsPar.scala:218:46") // FixLeq(Const(1520),b23016)
    // x35530 = FixConvert(Const(0),TRUE,_8,_24) x.tp=FixPt[TRUE,_32,_0] {
    val x35530_int1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("01111111111111111111111111111111"))).name("x35530_int1").ctrl(x35548).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x35530_int2 = OpDef(op=FixSla, inputs=List(x35530_int1, Const(24))).name("x35530_int2").ctrl(x35548).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x35530_frac1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("00000000000000000000000000000000"))).name("x35530_frac1").ctrl(x35548).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x35530_frac2 = OpDef(op=FixSla, inputs=List(x35530_frac1, Const(24))).name("x35530_frac2").ctrl(x35548).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x35530 = OpDef(op=BitOr, inputs=List(x35530_int2, x35530_frac2)).name("x35530").ctrl(x35548).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    // }
    val x35531 = OpDef(op=FixAdd, inputs=List(x35528, x35530)).name("x35531").ctrl(x35548).srcCtx("CellsPar.scala:218:87") // FixAdd(x35528,x35530)
    val x35532 = x35531 // FixConvert(x35531,TRUE,_8,_24) (Same Type. No op)
    val x35533 = OpDef(op=FixAbs, inputs=List(x35532)).name("x35533").ctrl(x35548).srcCtx("CellsPar.scala:37:19:absp") // FixAbs(x35532)
    val x35534 = OpDef(op=FixLt, inputs=List(Const(2.5), x35533)).name("x35534").ctrl(x35548).srcCtx("CellsPar.scala:51:23:cond1") // FixLt(Const(2.5),x35533)
    val x35535 = OpDef(op=FixLt, inputs=List(Const(0.5), x35533)).name("x35535").ctrl(x35548).srcCtx("CellsPar.scala:54:35") // FixLt(Const(0.5),x35533)
    val x35536 = OpDef(op=FixLeq, inputs=List(x35533, Const(2.5))).name("x35536").ctrl(x35548).srcCtx("CellsPar.scala:54:52") // FixLeq(x35533,Const(2.5))
    val x35537 = OpDef(op=BitAnd, inputs=List(x35535, x35536)).name("x35537").ctrl(x35548).srcCtx("CellsPar.scala:54:43:cond2") // And(x35535,x35536)
    val x35538 = OpDef(op=FixSra, inputs=List(x35533, Const(2))).name("x35538").ctrl(x35548).srcCtx("CellsPar.scala:56:21:div4") // FixRsh(x35533,Const(2))
    val x35539 = OpDef(op=FixAdd, inputs=List(x35538, Const(0.375))).name("x35539").ctrl(x35548).srcCtx("CellsPar.scala:57:27:div4Offset") // FixAdd(x35538,Const(0.375))
    val x35540 = OpDef(op=MuxOp, inputs=List(x35537, x35539, x35533)).name("x35540").ctrl(x35548).srcCtx("CellsPar.scala:58:20:body2") // Mux(x35537,x35539,x35533)
    val x35541 = OpDef(op=MuxOp, inputs=List(x35534, Const(1.0), x35540)).name("x35541").ctrl(x35548).srcCtx("CellsPar.scala:60:20:absre") // Mux(x35534,Const(1),x35540)
    val x35542 = OpDef(op=FixNeg, inputs=List(x35541)).name("x35542").ctrl(x35548).srcCtx("CellsPar.scala:62:29:mabre") // FixNeg(x35541)
    val x35543 = OpDef(op=FixLt, inputs=List(x35532, Const(0.0))).name("x35543").ctrl(x35548).srcCtx("CellsPar.scala:63:22") // FixLt(x35532,Const(0))
    val x35544 = OpDef(op=MuxOp, inputs=List(x35543, x35542, x35541)).name("x35544").ctrl(x35548).srcCtx("CellsPar.scala:63:17:re") // Mux(x35543,x35542,x35541)
    val x35545 = x35544 // FixConvert(x35544,TRUE,_8,_24) (Same Type. No op)
    val x35546 = OpDef(op=MuxOp, inputs=List(x35529, x35545, x35528)).name("x35546").ctrl(x35548).srcCtx("CellsPar.scala:218:43") // Mux(x35529,x35545,x35528)
    val x35547 = StoreBanks(List(x34687_d0_b0, x34687_d1_b0), List(b23061, x35517), x35546).name("x35547").ctrl(x35548).srcCtx("CellsPar.scala:218:38") // SRAMStore(x34687,ArrayBuffer(Const(1), Const(512)),List(b23061, x35517),Const(0),x35546,x35524)
    val x35552 = Counter(min=Const(0), max=Const(512), step=Const(16), par=1).name("x35552").ctrl(x36742).srcCtx("CellsPar.scala:190:58") // CounterNew(Const(0),Const(512),Const(16),Const(1))
    val x35553 = Counter(min=Const(0), max=Const(1536), step=Const(16), par=1).name("x35553").ctrl(x36742).srcCtx("CellsPar.scala:190:33") // CounterNew(Const(0),Const(1536),Const(16),Const(1))
    val x35554 = CounterChain(List(x35553,x35552)).name("x35554").ctrl(x36742).srcCtx("CellsPar.scala:190:72") // CounterChainNew(List(x35553, x35552))
    val x35660 = LoopController(style=MetaPipe, level=OuterControl, cchain=x35554).name("x35660").ctrl(x36742).srcCtx("CellsPar.scala:190:72") // UnrolledForeach(List(b22320),x35554,Block(Const(())),List(List(b23136), List(b23137)),List(List(b23138), List(b23139)))
    val b23136 = CounterIter(x35553, Some(0)).name("b23136").ctrl(x35660) // b23136
    val b23138 = Const(true).name("b23138").ctrl(x35660) // b23138
    val b23137 = CounterIter(x35552, Some(0)).name("b23137").ctrl(x35660) // b23137
    val b23139 = Const(true).name("b23139").ctrl(x35660) // b23139
    val x35555_d0_b0 = SRAM(size=256, banking=Strided(banks=1, stride=16)).name("x35555_d0_b0").ctrl(x35660).srcCtx("CellsPar.scala:191:33:tileKernel") // x35555 = SRAMNew(ArrayBuffer(Const(16), Const(16)))
    isAccum(x35555_d0_b0) = false
    bufferDepthOf(x35555_d0_b0) = 2
    val x35558 = UnitController(style=SeqPipe, level=InnerControl).name("x35558").ctrl(x35660).srcCtx("CellsPar.scala:190:72") // UnitPipe(List(b23138, b23139, b22320),Block(Const(())))
    val x35556 = OpDef(op=FixAdd, inputs=List(b23136, Const(16))).name("x35556").ctrl(x35558).srcCtx("CellsPar.scala:192:36") // FixAdd(b23136,Const(16))
    val x35557 = OpDef(op=FixAdd, inputs=List(b23137, Const(16))).name("x35557").ctrl(x35558).srcCtx("CellsPar.scala:192:45") // FixAdd(b23137,Const(16))
    val x35559 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x35559").ctrl(x35660).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x35560 = CounterChain(List(x35559)).name("x35560").ctrl(x35660).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x35559))
    val x35589 = LoopController(style=StreamPipe, level=OuterControl, cchain=x35560).name("x35589").ctrl(x35660).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b23138, b23139, b22320),x35560,Block(Const(())),List(List(b23146)),List(List(b23147)))
    val b23146 = CounterIter(x35559, Some(0)).name("b23146").ctrl(x35589) // b23146
    val b23147 = Const(true).name("b23147").ctrl(x35589) // b23147
    val b36853 = StreamOut(field="offset").name("b36853").ctrl(x35589).srcCtx("CellsPar.scala:192:20") // x35561 = StreamOutNew(BurstCmdBus)
    isAccum(b36853) = false
    bufferDepthOf(b36853) = 1
    val b36854 = StreamOut(field="size").name("b36854").ctrl(x35589).srcCtx("CellsPar.scala:192:20") // x35561 = StreamOutNew(BurstCmdBus)
    isAccum(b36854) = false
    bufferDepthOf(b36854) = 1
    val x35562 = StreamIn(field="data").name("x35562").ctrl(x35589).srcCtx("CellsPar.scala:192:20") // x35562 = StreamInNew(BurstDataBus())
    isAccum(x35562) = false
    bufferDepthOf(x35562) = 1
    val x35577 = UnitController(style=SeqPipe, level=InnerControl).name("x35577").ctrl(x35589).srcCtx("CellsPar.scala:192:20") // UnitPipe(List(b23147, b23138, b23139, b22320),Block(x35576))
    val x35563 = OpDef(op=FixAdd, inputs=List(b23136, b23146)).name("x35563").ctrl(x35577).srcCtx("CellsPar.scala:192:20") // FixAdd(b23136,b23146)
    val x35564 = x35563 // FixConvert(x35563,TRUE,_32,_0) (Same Type. No op)
    val x35565 = OpDef(op=FixSla, inputs=List(x35564, Const(9))).name("x35565").ctrl(x35577).srcCtx("CellsPar.scala:192:20") // FixLsh(x35564,Const(9))
    val x35566 = b23137 // FixConvert(b23137,TRUE,_32,_0) (Same Type. No op)
    val x35567 = OpDef(op=FixAdd, inputs=List(x35565, x35566)).name("x35567").ctrl(x35577).srcCtx("CellsPar.scala:192:20") // FixAdd(x35565,x35566)
    val x35568 = OpDef(op=FixSla, inputs=List(x35567, Const(2))).name("x35568").ctrl(x35577).srcCtx("CellsPar.scala:192:20") // FixLsh(x35567,Const(2))
    val x35569 = x35568 // FixConvert(x35568,TRUE,_64,_0)
    val x35570 = DramAddress(x34478).name("x35570").ctrl(x35577).srcCtx("CellsPar.scala:192:20") // GetDRAMAddress(x34478)
    val x35572_x35571 = OpDef(op=FixAdd, inputs=List(x35569, x35570)).name("x35572_x35571").ctrl(x35577).srcCtx("CellsPar.scala:192:20") // FixAdd(x35569,x35570)
    // x35572 = SimpleStruct(ArrayBuffer((offset,x35571), (size,Const(64)), (isLoad,Const(true))))
    val x35573 = OpDef(op=BitAnd, inputs=List(b23147, b23138)).name("x35573").ctrl(x35577).srcCtx("UnrollingBase.scala:28:66") // And(b23147,b23138)
    val x35574 = OpDef(op=BitAnd, inputs=List(b23139, b22320)).name("x35574").ctrl(x35577).srcCtx("UnrollingBase.scala:28:66") // And(b23139,b22320)
    val x35575 = OpDef(op=BitAnd, inputs=List(x35573, x35574)).name("x35575").ctrl(x35577).srcCtx("UnrollingBase.scala:28:66") // And(x35573,x35574)
    val x35576_b36855_b36853 = WriteMem(b36853, x35572_x35571).name("x35576_b36855_b36853").ctrl(x35577).srcCtx("CellsPar.scala:192:20") // StreamWrite(x35561,x35572,x35575)
    val x35576_b36856_b36854 = WriteMem(b36854, Const(64)).name("x35576_b36856_b36854").ctrl(x35577).srcCtx("CellsPar.scala:192:20") // StreamWrite(x35561,x35572,x35575)
    val x35578 = FringeDenseLoad(dram=List(x34478), cmdStream=List(b36853, b36854), dataStream=List(x35562)).name("x35578").ctrl(x35589).srcCtx("CellsPar.scala:192:20") // FringeDenseLoad(x34478,x35561,x35562)
    val x35579 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x35579").ctrl(x35589).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x35580 = CounterChain(List(x35579)).name("x35580").ctrl(x35589).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x35579))
    val x35588 = LoopController(style=InnerPipe, level=InnerControl, cchain=x35580).name("x35588").ctrl(x35589).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b23147, b23138, b23139, b22320),x35580,Block(Const(())),List(List(b23168)),List(List(b23169)))
    val b23168 = CounterIter(x35579, None).name("b23168").ctrl(x35588) // b23168
    val b23169 = Const(true).name("b23169").ctrl(x35588) // b23169
    val x35581 = OpDef(op=BitAnd, inputs=List(b23169, b23147)).name("x35581").ctrl(x35588).srcCtx("UnrollingBase.scala:28:66") // And(b23169,b23147)
    val x35582 = OpDef(op=BitAnd, inputs=List(b23138, b23139)).name("x35582").ctrl(x35588).srcCtx("UnrollingBase.scala:28:66") // And(b23138,b23139)
    val x35583 = OpDef(op=BitAnd, inputs=List(x35581, x35582)).name("x35583").ctrl(x35588).srcCtx("UnrollingBase.scala:28:66") // And(x35581,x35582)
    val x35584 = OpDef(op=BitAnd, inputs=List(x35583, b22320)).name("x35584").ctrl(x35588).srcCtx("UnrollingBase.scala:28:66") // And(x35583,b22320)
    val x35585_x35585 = ReadMem(x35562).name("x35585_x35585").ctrl(x35588).srcCtx("CellsPar.scala:192:20") // ParStreamRead(x35562,List(x35584))
    val x35586_x35586 = x35585_x35585 // x35586 = VectorApply(x35585,0)
    val x35587 = StoreBanks(List(x35555_d0_b0), List(b23146, b23168), x35586_x35586).name("x35587").ctrl(x35588).srcCtx("CellsPar.scala:192:20") // ParSRAMStore(x35555,List(List(b23146, b23168)),List(x35586),List(x35584))
    val x35590 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x35590").ctrl(x35660).srcCtx("CellsPar.scala:193:34") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x35591 = CounterChain(List(x35590)).name("x35591").ctrl(x35660).srcCtx("CellsPar.scala:193:46") // CounterChainNew(List(x35590))
    val x35659 = LoopController(style=MetaPipe, level=OuterControl, cchain=x35591).name("x35659").ctrl(x35660).srcCtx("CellsPar.scala:193:46") // UnrolledForeach(List(b23138, b23139, b22320),x35591,Block(Const(())),List(List(b23181)),List(List(b23182)))
    val b23181 = CounterIter(x35590, Some(0)).name("b23181").ctrl(x35659) // b23181
    val b23182 = Const(true).name("b23182").ctrl(x35659) // b23182
    val x35592 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x35592").ctrl(x35659).srcCtx("CellsPar.scala:194:28") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x35593 = CounterChain(List(x35592)).name("x35593").ctrl(x35659).srcCtx("CellsPar.scala:194:42") // CounterChainNew(List(x35592))
    val x35658 = LoopController(style=MetaPipe, level=OuterControl, cchain=x35593).name("x35658").ctrl(x35659).srcCtx("CellsPar.scala:194:42") // UnrolledForeach(List(b23182, b23138, b23139, b22320),x35593,Block(Const(())),List(List(b23185)),List(List(b23186)))
    val b23185 = CounterIter(x35592, Some(0)).name("b23185").ctrl(x35658) // b23185
    val b23186 = Const(true).name("b23186").ctrl(x35658) // b23186
    val x35594_d0 = Reg(init=Some(0.0)).name("x35594_d0").ctrl(x35658).srcCtx("CellsPar.scala:195:34:prod") // x35594 = RegNew(Const(0))
    isAccum(x35594_d0) = false
    bufferDepthOf(x35594_d0) = 2
    val x35594_d1 = Reg(init=Some(0.0)).name("x35594_d1").ctrl(x35658).srcCtx("CellsPar.scala:195:34:prod") // x35594 = RegNew(Const(0))
    isAccum(x35594_d1) = true
    bufferDepthOf(x35594_d1) = 1
    val x35595 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x35595").ctrl(x35658).srcCtx("CellsPar.scala:195:43") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x35596 = CounterChain(List(x35595)).name("x35596").ctrl(x35658).srcCtx("CellsPar.scala:207:15") // CounterChainNew(List(x35595))
    val x35622 = LoopController(style=InnerPipe, level=InnerControl, cchain=x35596).name("x35622").ctrl(x35658).srcCtx("CellsPar.scala:207:15") // UnrolledReduce(List(b23186, b23182, b23138, b23139, b22320),x35596,x35594,Block((x35594) => Const(())),List(List(b23190)),List(List(b23191)))
    val b23190 = CounterIter(x35595, None).name("b23190").ctrl(x35622) // b23190
    val b23191 = Const(true).name("b23191").ctrl(x35622) // b23191
    val x35597 = OpDef(op=FixAdd, inputs=List(b23136, b23190)).name("x35597").ctrl(x35622).srcCtx("CellsPar.scala:196:42:reduce_size_offset") // FixAdd(b23136,b23190)
    val x35598 = OpDef(op=BitAnd, inputs=List(b23191, b23186)).name("x35598").ctrl(x35622).srcCtx("UnrollingBase.scala:28:66") // And(b23191,b23186)
    val x35599 = OpDef(op=BitAnd, inputs=List(b23182, b23138)).name("x35599").ctrl(x35622).srcCtx("UnrollingBase.scala:28:66") // And(b23182,b23138)
    val x35600 = OpDef(op=BitAnd, inputs=List(b23139, b22320)).name("x35600").ctrl(x35622).srcCtx("UnrollingBase.scala:28:66") // And(b23139,b22320)
    val x35601 = OpDef(op=BitAnd, inputs=List(x35598, x35599)).name("x35601").ctrl(x35622).srcCtx("UnrollingBase.scala:28:66") // And(x35598,x35599)
    val x35602 = OpDef(op=BitAnd, inputs=List(x35601, x35600)).name("x35602").ctrl(x35622).srcCtx("UnrollingBase.scala:28:66") // And(x35601,x35600)
    val x35603 = LoadBanks(List(x35555_d0_b0), List(b23190, b23185)).name("x35603").ctrl(x35622).srcCtx("CellsPar.scala:203:34:tk") // ParSRAMLoad(x35555,List(List(b23190, b23185)),List(x35602))
    val x35604 = x35603 // x35604 = VectorApply(x35603,0)
    val x35605 = LoadBanks(List(x34677_d1_b0), List(b23181, x35597)).name("x35605").ctrl(x35622).srcCtx("CellsPar.scala:204:28") // ParSRAMLoad(x34677,List(List(b23181, x35597)),List(x35602))
    val x35606 = x35605 // x35606 = VectorApply(x35605,0)
    val x35607 = OpDef(op=FixMul, inputs=List(x35606, x35604)).name("x35607").ctrl(x35622).srcCtx("CellsPar.scala:204:52:tempA") // FixMul(x35606,x35604)
    val x35608 = OpDef(op=FixSub, inputs=List(x35597, Const(512))).name("x35608").ctrl(x35622).srcCtx("CellsPar.scala:205:51") // FixSub(x35597,Const(512))
    val x35609 = LoadBanks(List(x34684_d5_b0), List(b23181, x35608)).name("x35609").ctrl(x35622).srcCtx("CellsPar.scala:205:28") // ParSRAMLoad(x34684,List(List(b23181, x35608)),List(x35602))
    val x35610 = x35609 // x35610 = VectorApply(x35609,0)
    val x35611 = OpDef(op=FixMul, inputs=List(x35610, x35604)).name("x35611").ctrl(x35622).srcCtx("CellsPar.scala:205:67:tempB") // FixMul(x35610,x35604)
    val x35612 = OpDef(op=FixLt, inputs=List(x35597, Const(512))).name("x35612").ctrl(x35622).srcCtx("CellsPar.scala:206:38") // FixLt(x35597,Const(512))
    val x35613 = OpDef(op=MuxOp, inputs=List(x35612, x35607, x35611)).name("x35613").ctrl(x35622).srcCtx("CellsPar.scala:206:18") // Mux(x35612,x35607,x35611)
    val x35614 = ReadMem(x35594_d1).name("x35614").ctrl(x35622).srcCtx("CellsPar.scala:207:15") // RegRead(x35594)
    val x35615 = OpDef(op=FixEql, inputs=List(b23190, Const(0))).name("x35615").ctrl(x35622).srcCtx("CellsPar.scala:207:15") // FixEql(b23190,Const(0))
    val x35616 = ReduceAccumOp(op=FixAdd, input=x35613, accum=x35614).name("x35616").ctrl(x35622).srcCtx("CellsPar.scala:207:17") // FixAdd(x35613,x35614)
    val x35617 = OpDef(op=BitAnd, inputs=List(b23186, b23182)).name("x35617").ctrl(x35622).srcCtx("UnrollingBase.scala:28:66") // And(b23186,b23182)
    val x35618 = OpDef(op=BitAnd, inputs=List(b23138, b23139)).name("x35618").ctrl(x35622).srcCtx("UnrollingBase.scala:28:66") // And(b23138,b23139)
    val x35619 = OpDef(op=BitAnd, inputs=List(x35617, x35618)).name("x35619").ctrl(x35622).srcCtx("UnrollingBase.scala:28:66") // And(x35617,x35618)
    val x35620 = OpDef(op=BitAnd, inputs=List(x35619, b22320)).name("x35620").ctrl(x35622).srcCtx("UnrollingBase.scala:28:66") // And(x35619,b22320)
    val x35621_x35594_d0 = WriteMem(x35594_d0, x35616).name("x35621_x35594_d0").ctrl(x35622).srcCtx("CellsPar.scala:207:15") // RegWrite(x35594,x35616,x35620)
    val x35621_x35594_d1 = WriteMem(x35594_d1, x35616).name("x35621_x35594_d1").ctrl(x35622).srcCtx("CellsPar.scala:207:15") // RegWrite(x35594,x35616,x35620)
    val x35657 = UnitController(style=SeqPipe, level=InnerControl).name("x35657").ctrl(x35658).srcCtx("CellsPar.scala:194:42") // UnitPipe(List(b23186, b23182, b23138, b23139, b22320),Block(Const(())))
    val x35623 = OpDef(op=FixAdd, inputs=List(b23137, b23185)).name("x35623").ctrl(x35657).srcCtx("CellsPar.scala:209:31:colOffset") // FixAdd(b23137,b23185)
    val x35624 = ReadMem(x35594_d0).name("x35624").ctrl(x35657).srcCtx("CellsPar.scala:210:28") // RegRead(x35594)
    val x35625 = OpDef(op=FixEql, inputs=List(b23136, Const(0))).name("x35625").ctrl(x35657).srcCtx("CellsPar.scala:210:42") // FixEql(b23136,Const(0))
    val x35626 = OpDef(op=FixAdd, inputs=List(x35623, Const(1024))).name("x35626").ctrl(x35657).srcCtx("CellsPar.scala:210:66") // FixAdd(x35623,Const(1024))
    val x35627 = OpDef(op=BitAnd, inputs=List(b23186, b23182)).name("x35627").ctrl(x35657).srcCtx("UnrollingBase.scala:28:66") // And(b23186,b23182)
    val x35628 = OpDef(op=BitAnd, inputs=List(b23138, b23139)).name("x35628").ctrl(x35657).srcCtx("UnrollingBase.scala:28:66") // And(b23138,b23139)
    val x35629 = OpDef(op=BitAnd, inputs=List(x35627, x35628)).name("x35629").ctrl(x35657).srcCtx("UnrollingBase.scala:28:66") // And(x35627,x35628)
    val x35630 = OpDef(op=BitAnd, inputs=List(x35629, b22320)).name("x35630").ctrl(x35657).srcCtx("UnrollingBase.scala:28:66") // And(x35629,b22320)
    val x35631 = LoadBanks(List(x34690_d1_b0), List(Const(0), x35626)).name("x35631").ctrl(x35657).srcCtx("CellsPar.scala:210:52") // SRAMLoad(x34690,ArrayBuffer(Const(1), Const(2048)),List(Const(0), x35626),Const(0),x35630)
    val x35632 = LoadBanks(List(x34688_d1_b0), List(b23181, x35623)).name("x35632").ctrl(x35657).srcCtx("CellsPar.scala:210:91") // SRAMLoad(x34688,ArrayBuffer(Const(1), Const(512)),List(b23181, x35623),Const(0),x35630)
    val x35633 = OpDef(op=MuxOp, inputs=List(x35625, x35631, x35632)).name("x35633").ctrl(x35657).srcCtx("CellsPar.scala:210:39") // Mux(x35625,x35631,x35632)
    val x35634 = OpDef(op=FixAdd, inputs=List(x35624, x35633)).name("x35634").ctrl(x35657).srcCtx("CellsPar.scala:210:34:ele") // FixAdd(x35624,x35633)
    val x35635 = OpDef(op=FixLeq, inputs=List(Const(1520), b23136)).name("x35635").ctrl(x35657).srcCtx("CellsPar.scala:218:46") // FixLeq(Const(1520),b23136)
    // x35636 = FixConvert(Const(1),TRUE,_8,_24) x.tp=FixPt[TRUE,_32,_0] {
    val x35636_int1 = OpDef(op=BitAnd, inputs=List(Const(1), Const("01111111111111111111111111111111"))).name("x35636_int1").ctrl(x35657).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(1),TRUE,_8,_24)
    val x35636_int2 = OpDef(op=FixSla, inputs=List(x35636_int1, Const(24))).name("x35636_int2").ctrl(x35657).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(1),TRUE,_8,_24)
    val x35636_frac1 = OpDef(op=BitAnd, inputs=List(Const(1), Const("00000000000000000000000000000000"))).name("x35636_frac1").ctrl(x35657).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(1),TRUE,_8,_24)
    val x35636_frac2 = OpDef(op=FixSla, inputs=List(x35636_frac1, Const(24))).name("x35636_frac2").ctrl(x35657).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(1),TRUE,_8,_24)
    val x35636 = OpDef(op=BitOr, inputs=List(x35636_int2, x35636_frac2)).name("x35636").ctrl(x35657).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(1),TRUE,_8,_24)
    // }
    val x35637 = OpDef(op=FixAdd, inputs=List(x35634, x35636)).name("x35637").ctrl(x35657).srcCtx("CellsPar.scala:218:87") // FixAdd(x35634,x35636)
    val x35638 = OpDef(op=FixSra, inputs=List(x35637, Const(1))).name("x35638").ctrl(x35657).srcCtx("CellsPar.scala:29:22") // FixRsh(x35637,Const(1))
    val x35639 = x35638 // FixConvert(x35638,TRUE,_8,_24) (Same Type. No op)
    val x35640 = OpDef(op=FixAbs, inputs=List(x35639)).name("x35640").ctrl(x35657).srcCtx("CellsPar.scala:37:19:absp") // FixAbs(x35639)
    val x35641 = OpDef(op=FixLt, inputs=List(Const(2.5), x35640)).name("x35641").ctrl(x35657).srcCtx("CellsPar.scala:51:23:cond1") // FixLt(Const(2.5),x35640)
    val x35642 = OpDef(op=FixLt, inputs=List(Const(0.5), x35640)).name("x35642").ctrl(x35657).srcCtx("CellsPar.scala:54:35") // FixLt(Const(0.5),x35640)
    val x35643 = OpDef(op=FixLeq, inputs=List(x35640, Const(2.5))).name("x35643").ctrl(x35657).srcCtx("CellsPar.scala:54:52") // FixLeq(x35640,Const(2.5))
    val x35644 = OpDef(op=BitAnd, inputs=List(x35642, x35643)).name("x35644").ctrl(x35657).srcCtx("CellsPar.scala:54:43:cond2") // And(x35642,x35643)
    val x35645 = OpDef(op=FixSra, inputs=List(x35640, Const(2))).name("x35645").ctrl(x35657).srcCtx("CellsPar.scala:56:21:div4") // FixRsh(x35640,Const(2))
    val x35646 = OpDef(op=FixAdd, inputs=List(x35645, Const(0.375))).name("x35646").ctrl(x35657).srcCtx("CellsPar.scala:57:27:div4Offset") // FixAdd(x35645,Const(0.375))
    val x35647 = OpDef(op=MuxOp, inputs=List(x35644, x35646, x35640)).name("x35647").ctrl(x35657).srcCtx("CellsPar.scala:58:20:body2") // Mux(x35644,x35646,x35640)
    val x35648 = OpDef(op=MuxOp, inputs=List(x35641, Const(1.0), x35647)).name("x35648").ctrl(x35657).srcCtx("CellsPar.scala:60:20:absre") // Mux(x35641,Const(1),x35647)
    val x35649 = OpDef(op=FixNeg, inputs=List(x35648)).name("x35649").ctrl(x35657).srcCtx("CellsPar.scala:62:29:mabre") // FixNeg(x35648)
    val x35650 = OpDef(op=FixLt, inputs=List(x35639, Const(0.0))).name("x35650").ctrl(x35657).srcCtx("CellsPar.scala:63:22") // FixLt(x35639,Const(0))
    val x35651 = OpDef(op=MuxOp, inputs=List(x35650, x35649, x35648)).name("x35651").ctrl(x35657).srcCtx("CellsPar.scala:63:17:re") // Mux(x35650,x35649,x35648)
    val x35652 = x35651 // FixConvert(x35651,TRUE,_8,_24) (Same Type. No op)
    val x35653 = OpDef(op=FixAdd, inputs=List(x35652, Const(1.0))).name("x35653").ctrl(x35657).srcCtx("CellsPar.scala:29:33") // FixAdd(x35652,Const(1))
    val x35654 = OpDef(op=FixSra, inputs=List(x35653, Const(1))).name("x35654").ctrl(x35657).srcCtx("CellsPar.scala:29:44") // FixRsh(x35653,Const(1))
    val x35655 = OpDef(op=MuxOp, inputs=List(x35635, x35654, x35634)).name("x35655").ctrl(x35657).srcCtx("CellsPar.scala:218:43") // Mux(x35635,x35654,x35634)
    val x35656 = StoreBanks(List(x34688_d0_b0, x34688_d1_b0), List(b23181, x35623), x35655).name("x35656").ctrl(x35657).srcCtx("CellsPar.scala:218:38") // SRAMStore(x34688,ArrayBuffer(Const(1), Const(512)),List(b23181, x35623),Const(0),x35655,x35630)
    val x35661 = Counter(min=Const(0), max=Const(512), step=Const(16), par=1).name("x35661").ctrl(x36742).srcCtx("CellsPar.scala:190:58") // CounterNew(Const(0),Const(512),Const(16),Const(1))
    val x35662 = Counter(min=Const(0), max=Const(1536), step=Const(16), par=1).name("x35662").ctrl(x36742).srcCtx("CellsPar.scala:190:33") // CounterNew(Const(0),Const(1536),Const(16),Const(1))
    val x35663 = CounterChain(List(x35662,x35661)).name("x35663").ctrl(x36742).srcCtx("CellsPar.scala:190:72") // CounterChainNew(List(x35662, x35661))
    val x35769 = LoopController(style=MetaPipe, level=OuterControl, cchain=x35663).name("x35769").ctrl(x36742).srcCtx("CellsPar.scala:190:72") // UnrolledForeach(List(b22320),x35663,Block(Const(())),List(List(b23259), List(b23260)),List(List(b23261), List(b23262)))
    val b23259 = CounterIter(x35662, Some(0)).name("b23259").ctrl(x35769) // b23259
    val b23261 = Const(true).name("b23261").ctrl(x35769) // b23261
    val b23260 = CounterIter(x35661, Some(0)).name("b23260").ctrl(x35769) // b23260
    val b23262 = Const(true).name("b23262").ctrl(x35769) // b23262
    val x35664_d0_b0 = SRAM(size=256, banking=Strided(banks=1, stride=16)).name("x35664_d0_b0").ctrl(x35769).srcCtx("CellsPar.scala:191:33:tileKernel") // x35664 = SRAMNew(ArrayBuffer(Const(16), Const(16)))
    isAccum(x35664_d0_b0) = false
    bufferDepthOf(x35664_d0_b0) = 2
    val x35667 = UnitController(style=SeqPipe, level=InnerControl).name("x35667").ctrl(x35769).srcCtx("CellsPar.scala:190:72") // UnitPipe(List(b23261, b23262, b22320),Block(Const(())))
    val x35665 = OpDef(op=FixAdd, inputs=List(b23259, Const(16))).name("x35665").ctrl(x35667).srcCtx("CellsPar.scala:192:36") // FixAdd(b23259,Const(16))
    val x35666 = OpDef(op=FixAdd, inputs=List(b23260, Const(16))).name("x35666").ctrl(x35667).srcCtx("CellsPar.scala:192:45") // FixAdd(b23260,Const(16))
    val x35668 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x35668").ctrl(x35769).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x35669 = CounterChain(List(x35668)).name("x35669").ctrl(x35769).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x35668))
    val x35698 = LoopController(style=StreamPipe, level=OuterControl, cchain=x35669).name("x35698").ctrl(x35769).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b23261, b23262, b22320),x35669,Block(Const(())),List(List(b23269)),List(List(b23270)))
    val b23269 = CounterIter(x35668, Some(0)).name("b23269").ctrl(x35698) // b23269
    val b23270 = Const(true).name("b23270").ctrl(x35698) // b23270
    val b36857 = StreamOut(field="offset").name("b36857").ctrl(x35698).srcCtx("CellsPar.scala:192:20") // x35670 = StreamOutNew(BurstCmdBus)
    isAccum(b36857) = false
    bufferDepthOf(b36857) = 1
    val b36858 = StreamOut(field="size").name("b36858").ctrl(x35698).srcCtx("CellsPar.scala:192:20") // x35670 = StreamOutNew(BurstCmdBus)
    isAccum(b36858) = false
    bufferDepthOf(b36858) = 1
    val x35671 = StreamIn(field="data").name("x35671").ctrl(x35698).srcCtx("CellsPar.scala:192:20") // x35671 = StreamInNew(BurstDataBus())
    isAccum(x35671) = false
    bufferDepthOf(x35671) = 1
    val x35686 = UnitController(style=SeqPipe, level=InnerControl).name("x35686").ctrl(x35698).srcCtx("CellsPar.scala:192:20") // UnitPipe(List(b23270, b23261, b23262, b22320),Block(x35685))
    val x35672 = OpDef(op=FixAdd, inputs=List(b23259, b23269)).name("x35672").ctrl(x35686).srcCtx("CellsPar.scala:192:20") // FixAdd(b23259,b23269)
    val x35673 = x35672 // FixConvert(x35672,TRUE,_32,_0) (Same Type. No op)
    val x35674 = OpDef(op=FixSla, inputs=List(x35673, Const(9))).name("x35674").ctrl(x35686).srcCtx("CellsPar.scala:192:20") // FixLsh(x35673,Const(9))
    val x35675 = b23260 // FixConvert(b23260,TRUE,_32,_0) (Same Type. No op)
    val x35676 = OpDef(op=FixAdd, inputs=List(x35674, x35675)).name("x35676").ctrl(x35686).srcCtx("CellsPar.scala:192:20") // FixAdd(x35674,x35675)
    val x35677 = OpDef(op=FixSla, inputs=List(x35676, Const(2))).name("x35677").ctrl(x35686).srcCtx("CellsPar.scala:192:20") // FixLsh(x35676,Const(2))
    val x35678 = x35677 // FixConvert(x35677,TRUE,_64,_0)
    val x35679 = DramAddress(x34479).name("x35679").ctrl(x35686).srcCtx("CellsPar.scala:192:20") // GetDRAMAddress(x34479)
    val x35681_x35680 = OpDef(op=FixAdd, inputs=List(x35678, x35679)).name("x35681_x35680").ctrl(x35686).srcCtx("CellsPar.scala:192:20") // FixAdd(x35678,x35679)
    // x35681 = SimpleStruct(ArrayBuffer((offset,x35680), (size,Const(64)), (isLoad,Const(true))))
    val x35682 = OpDef(op=BitAnd, inputs=List(b23270, b23261)).name("x35682").ctrl(x35686).srcCtx("UnrollingBase.scala:28:66") // And(b23270,b23261)
    val x35683 = OpDef(op=BitAnd, inputs=List(b23262, b22320)).name("x35683").ctrl(x35686).srcCtx("UnrollingBase.scala:28:66") // And(b23262,b22320)
    val x35684 = OpDef(op=BitAnd, inputs=List(x35682, x35683)).name("x35684").ctrl(x35686).srcCtx("UnrollingBase.scala:28:66") // And(x35682,x35683)
    val x35685_b36859_b36857 = WriteMem(b36857, x35681_x35680).name("x35685_b36859_b36857").ctrl(x35686).srcCtx("CellsPar.scala:192:20") // StreamWrite(x35670,x35681,x35684)
    val x35685_b36860_b36858 = WriteMem(b36858, Const(64)).name("x35685_b36860_b36858").ctrl(x35686).srcCtx("CellsPar.scala:192:20") // StreamWrite(x35670,x35681,x35684)
    val x35687 = FringeDenseLoad(dram=List(x34479), cmdStream=List(b36857, b36858), dataStream=List(x35671)).name("x35687").ctrl(x35698).srcCtx("CellsPar.scala:192:20") // FringeDenseLoad(x34479,x35670,x35671)
    val x35688 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x35688").ctrl(x35698).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x35689 = CounterChain(List(x35688)).name("x35689").ctrl(x35698).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x35688))
    val x35697 = LoopController(style=InnerPipe, level=InnerControl, cchain=x35689).name("x35697").ctrl(x35698).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b23270, b23261, b23262, b22320),x35689,Block(Const(())),List(List(b23291)),List(List(b23292)))
    val b23291 = CounterIter(x35688, None).name("b23291").ctrl(x35697) // b23291
    val b23292 = Const(true).name("b23292").ctrl(x35697) // b23292
    val x35690 = OpDef(op=BitAnd, inputs=List(b23292, b23270)).name("x35690").ctrl(x35697).srcCtx("UnrollingBase.scala:28:66") // And(b23292,b23270)
    val x35691 = OpDef(op=BitAnd, inputs=List(b23261, b23262)).name("x35691").ctrl(x35697).srcCtx("UnrollingBase.scala:28:66") // And(b23261,b23262)
    val x35692 = OpDef(op=BitAnd, inputs=List(x35690, x35691)).name("x35692").ctrl(x35697).srcCtx("UnrollingBase.scala:28:66") // And(x35690,x35691)
    val x35693 = OpDef(op=BitAnd, inputs=List(x35692, b22320)).name("x35693").ctrl(x35697).srcCtx("UnrollingBase.scala:28:66") // And(x35692,b22320)
    val x35694_x35694 = ReadMem(x35671).name("x35694_x35694").ctrl(x35697).srcCtx("CellsPar.scala:192:20") // ParStreamRead(x35671,List(x35693))
    val x35695_x35695 = x35694_x35694 // x35695 = VectorApply(x35694,0)
    val x35696 = StoreBanks(List(x35664_d0_b0), List(b23269, b23291), x35695_x35695).name("x35696").ctrl(x35697).srcCtx("CellsPar.scala:192:20") // ParSRAMStore(x35664,List(List(b23269, b23291)),List(x35695),List(x35693))
    val x35699 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x35699").ctrl(x35769).srcCtx("CellsPar.scala:193:34") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x35700 = CounterChain(List(x35699)).name("x35700").ctrl(x35769).srcCtx("CellsPar.scala:193:46") // CounterChainNew(List(x35699))
    val x35768 = LoopController(style=MetaPipe, level=OuterControl, cchain=x35700).name("x35768").ctrl(x35769).srcCtx("CellsPar.scala:193:46") // UnrolledForeach(List(b23261, b23262, b22320),x35700,Block(Const(())),List(List(b23304)),List(List(b23305)))
    val b23304 = CounterIter(x35699, Some(0)).name("b23304").ctrl(x35768) // b23304
    val b23305 = Const(true).name("b23305").ctrl(x35768) // b23305
    val x35701 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x35701").ctrl(x35768).srcCtx("CellsPar.scala:194:28") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x35702 = CounterChain(List(x35701)).name("x35702").ctrl(x35768).srcCtx("CellsPar.scala:194:42") // CounterChainNew(List(x35701))
    val x35767 = LoopController(style=MetaPipe, level=OuterControl, cchain=x35702).name("x35767").ctrl(x35768).srcCtx("CellsPar.scala:194:42") // UnrolledForeach(List(b23305, b23261, b23262, b22320),x35702,Block(Const(())),List(List(b23308)),List(List(b23309)))
    val b23308 = CounterIter(x35701, Some(0)).name("b23308").ctrl(x35767) // b23308
    val b23309 = Const(true).name("b23309").ctrl(x35767) // b23309
    val x35703_d0 = Reg(init=Some(0.0)).name("x35703_d0").ctrl(x35767).srcCtx("CellsPar.scala:195:34:prod") // x35703 = RegNew(Const(0))
    isAccum(x35703_d0) = false
    bufferDepthOf(x35703_d0) = 2
    val x35703_d1 = Reg(init=Some(0.0)).name("x35703_d1").ctrl(x35767).srcCtx("CellsPar.scala:195:34:prod") // x35703 = RegNew(Const(0))
    isAccum(x35703_d1) = true
    bufferDepthOf(x35703_d1) = 1
    val x35704 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x35704").ctrl(x35767).srcCtx("CellsPar.scala:195:43") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x35705 = CounterChain(List(x35704)).name("x35705").ctrl(x35767).srcCtx("CellsPar.scala:207:15") // CounterChainNew(List(x35704))
    val x35731 = LoopController(style=InnerPipe, level=InnerControl, cchain=x35705).name("x35731").ctrl(x35767).srcCtx("CellsPar.scala:207:15") // UnrolledReduce(List(b23309, b23305, b23261, b23262, b22320),x35705,x35703,Block((x35703) => Const(())),List(List(b23313)),List(List(b23314)))
    val b23313 = CounterIter(x35704, None).name("b23313").ctrl(x35731) // b23313
    val b23314 = Const(true).name("b23314").ctrl(x35731) // b23314
    val x35706 = OpDef(op=FixAdd, inputs=List(b23259, b23313)).name("x35706").ctrl(x35731).srcCtx("CellsPar.scala:196:42:reduce_size_offset") // FixAdd(b23259,b23313)
    val x35707 = OpDef(op=BitAnd, inputs=List(b23314, b23309)).name("x35707").ctrl(x35731).srcCtx("UnrollingBase.scala:28:66") // And(b23314,b23309)
    val x35708 = OpDef(op=BitAnd, inputs=List(b23305, b23261)).name("x35708").ctrl(x35731).srcCtx("UnrollingBase.scala:28:66") // And(b23305,b23261)
    val x35709 = OpDef(op=BitAnd, inputs=List(b23262, b22320)).name("x35709").ctrl(x35731).srcCtx("UnrollingBase.scala:28:66") // And(b23262,b22320)
    val x35710 = OpDef(op=BitAnd, inputs=List(x35707, x35708)).name("x35710").ctrl(x35731).srcCtx("UnrollingBase.scala:28:66") // And(x35707,x35708)
    val x35711 = OpDef(op=BitAnd, inputs=List(x35710, x35709)).name("x35711").ctrl(x35731).srcCtx("UnrollingBase.scala:28:66") // And(x35710,x35709)
    val x35712 = LoadBanks(List(x35664_d0_b0), List(b23313, b23308)).name("x35712").ctrl(x35731).srcCtx("CellsPar.scala:203:34:tk") // ParSRAMLoad(x35664,List(List(b23313, b23308)),List(x35711))
    val x35713 = x35712 // x35713 = VectorApply(x35712,0)
    val x35714 = LoadBanks(List(x34677_d0_b0), List(b23304, x35706)).name("x35714").ctrl(x35731).srcCtx("CellsPar.scala:204:28") // ParSRAMLoad(x34677,List(List(b23304, x35706)),List(x35711))
    val x35715 = x35714 // x35715 = VectorApply(x35714,0)
    val x35716 = OpDef(op=FixMul, inputs=List(x35715, x35713)).name("x35716").ctrl(x35731).srcCtx("CellsPar.scala:204:52:tempA") // FixMul(x35715,x35713)
    val x35717 = OpDef(op=FixSub, inputs=List(x35706, Const(512))).name("x35717").ctrl(x35731).srcCtx("CellsPar.scala:205:51") // FixSub(x35706,Const(512))
    val x35718 = LoadBanks(List(x34684_d4_b0), List(b23304, x35717)).name("x35718").ctrl(x35731).srcCtx("CellsPar.scala:205:28") // ParSRAMLoad(x34684,List(List(b23304, x35717)),List(x35711))
    val x35719 = x35718 // x35719 = VectorApply(x35718,0)
    val x35720 = OpDef(op=FixMul, inputs=List(x35719, x35713)).name("x35720").ctrl(x35731).srcCtx("CellsPar.scala:205:67:tempB") // FixMul(x35719,x35713)
    val x35721 = OpDef(op=FixLt, inputs=List(x35706, Const(512))).name("x35721").ctrl(x35731).srcCtx("CellsPar.scala:206:38") // FixLt(x35706,Const(512))
    val x35722 = OpDef(op=MuxOp, inputs=List(x35721, x35716, x35720)).name("x35722").ctrl(x35731).srcCtx("CellsPar.scala:206:18") // Mux(x35721,x35716,x35720)
    val x35723 = ReadMem(x35703_d1).name("x35723").ctrl(x35731).srcCtx("CellsPar.scala:207:15") // RegRead(x35703)
    val x35724 = OpDef(op=FixEql, inputs=List(b23313, Const(0))).name("x35724").ctrl(x35731).srcCtx("CellsPar.scala:207:15") // FixEql(b23313,Const(0))
    val x35725 = ReduceAccumOp(op=FixAdd, input=x35722, accum=x35723).name("x35725").ctrl(x35731).srcCtx("CellsPar.scala:207:17") // FixAdd(x35722,x35723)
    val x35726 = OpDef(op=BitAnd, inputs=List(b23309, b23305)).name("x35726").ctrl(x35731).srcCtx("UnrollingBase.scala:28:66") // And(b23309,b23305)
    val x35727 = OpDef(op=BitAnd, inputs=List(b23261, b23262)).name("x35727").ctrl(x35731).srcCtx("UnrollingBase.scala:28:66") // And(b23261,b23262)
    val x35728 = OpDef(op=BitAnd, inputs=List(x35726, x35727)).name("x35728").ctrl(x35731).srcCtx("UnrollingBase.scala:28:66") // And(x35726,x35727)
    val x35729 = OpDef(op=BitAnd, inputs=List(x35728, b22320)).name("x35729").ctrl(x35731).srcCtx("UnrollingBase.scala:28:66") // And(x35728,b22320)
    val x35730_x35703_d0 = WriteMem(x35703_d0, x35725).name("x35730_x35703_d0").ctrl(x35731).srcCtx("CellsPar.scala:207:15") // RegWrite(x35703,x35725,x35729)
    val x35730_x35703_d1 = WriteMem(x35703_d1, x35725).name("x35730_x35703_d1").ctrl(x35731).srcCtx("CellsPar.scala:207:15") // RegWrite(x35703,x35725,x35729)
    val x35766 = UnitController(style=SeqPipe, level=InnerControl).name("x35766").ctrl(x35767).srcCtx("CellsPar.scala:194:42") // UnitPipe(List(b23309, b23305, b23261, b23262, b22320),Block(Const(())))
    val x35732 = OpDef(op=FixAdd, inputs=List(b23260, b23308)).name("x35732").ctrl(x35766).srcCtx("CellsPar.scala:209:31:colOffset") // FixAdd(b23260,b23308)
    val x35733 = ReadMem(x35703_d0).name("x35733").ctrl(x35766).srcCtx("CellsPar.scala:210:28") // RegRead(x35703)
    val x35734 = OpDef(op=FixEql, inputs=List(b23259, Const(0))).name("x35734").ctrl(x35766).srcCtx("CellsPar.scala:210:42") // FixEql(b23259,Const(0))
    val x35735 = OpDef(op=FixAdd, inputs=List(x35732, Const(1536))).name("x35735").ctrl(x35766).srcCtx("CellsPar.scala:210:66") // FixAdd(x35732,Const(1536))
    val x35736 = OpDef(op=BitAnd, inputs=List(b23309, b23305)).name("x35736").ctrl(x35766).srcCtx("UnrollingBase.scala:28:66") // And(b23309,b23305)
    val x35737 = OpDef(op=BitAnd, inputs=List(b23261, b23262)).name("x35737").ctrl(x35766).srcCtx("UnrollingBase.scala:28:66") // And(b23261,b23262)
    val x35738 = OpDef(op=BitAnd, inputs=List(x35736, x35737)).name("x35738").ctrl(x35766).srcCtx("UnrollingBase.scala:28:66") // And(x35736,x35737)
    val x35739 = OpDef(op=BitAnd, inputs=List(x35738, b22320)).name("x35739").ctrl(x35766).srcCtx("UnrollingBase.scala:28:66") // And(x35738,b22320)
    val x35740 = LoadBanks(List(x34690_d0_b0), List(Const(0), x35735)).name("x35740").ctrl(x35766).srcCtx("CellsPar.scala:210:52") // SRAMLoad(x34690,ArrayBuffer(Const(1), Const(2048)),List(Const(0), x35735),Const(0),x35739)
    val x35741 = LoadBanks(List(x34689_d1_b0), List(b23304, x35732)).name("x35741").ctrl(x35766).srcCtx("CellsPar.scala:210:91") // SRAMLoad(x34689,ArrayBuffer(Const(1), Const(512)),List(b23304, x35732),Const(0),x35739)
    val x35742 = OpDef(op=MuxOp, inputs=List(x35734, x35740, x35741)).name("x35742").ctrl(x35766).srcCtx("CellsPar.scala:210:39") // Mux(x35734,x35740,x35741)
    val x35743 = OpDef(op=FixAdd, inputs=List(x35733, x35742)).name("x35743").ctrl(x35766).srcCtx("CellsPar.scala:210:34:ele") // FixAdd(x35733,x35742)
    val x35744 = OpDef(op=FixLeq, inputs=List(Const(1520), b23259)).name("x35744").ctrl(x35766).srcCtx("CellsPar.scala:218:46") // FixLeq(Const(1520),b23259)
    // x35745 = FixConvert(Const(0),TRUE,_8,_24) x.tp=FixPt[TRUE,_32,_0] {
    val x35745_int1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("01111111111111111111111111111111"))).name("x35745_int1").ctrl(x35766).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x35745_int2 = OpDef(op=FixSla, inputs=List(x35745_int1, Const(24))).name("x35745_int2").ctrl(x35766).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x35745_frac1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("00000000000000000000000000000000"))).name("x35745_frac1").ctrl(x35766).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x35745_frac2 = OpDef(op=FixSla, inputs=List(x35745_frac1, Const(24))).name("x35745_frac2").ctrl(x35766).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x35745 = OpDef(op=BitOr, inputs=List(x35745_int2, x35745_frac2)).name("x35745").ctrl(x35766).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    // }
    val x35746 = OpDef(op=FixAdd, inputs=List(x35743, x35745)).name("x35746").ctrl(x35766).srcCtx("CellsPar.scala:218:87") // FixAdd(x35743,x35745)
    val x35747 = OpDef(op=FixSra, inputs=List(x35746, Const(1))).name("x35747").ctrl(x35766).srcCtx("CellsPar.scala:29:22") // FixRsh(x35746,Const(1))
    val x35748 = x35747 // FixConvert(x35747,TRUE,_8,_24) (Same Type. No op)
    val x35749 = OpDef(op=FixAbs, inputs=List(x35748)).name("x35749").ctrl(x35766).srcCtx("CellsPar.scala:37:19:absp") // FixAbs(x35748)
    val x35750 = OpDef(op=FixLt, inputs=List(Const(2.5), x35749)).name("x35750").ctrl(x35766).srcCtx("CellsPar.scala:51:23:cond1") // FixLt(Const(2.5),x35749)
    val x35751 = OpDef(op=FixLt, inputs=List(Const(0.5), x35749)).name("x35751").ctrl(x35766).srcCtx("CellsPar.scala:54:35") // FixLt(Const(0.5),x35749)
    val x35752 = OpDef(op=FixLeq, inputs=List(x35749, Const(2.5))).name("x35752").ctrl(x35766).srcCtx("CellsPar.scala:54:52") // FixLeq(x35749,Const(2.5))
    val x35753 = OpDef(op=BitAnd, inputs=List(x35751, x35752)).name("x35753").ctrl(x35766).srcCtx("CellsPar.scala:54:43:cond2") // And(x35751,x35752)
    val x35754 = OpDef(op=FixSra, inputs=List(x35749, Const(2))).name("x35754").ctrl(x35766).srcCtx("CellsPar.scala:56:21:div4") // FixRsh(x35749,Const(2))
    val x35755 = OpDef(op=FixAdd, inputs=List(x35754, Const(0.375))).name("x35755").ctrl(x35766).srcCtx("CellsPar.scala:57:27:div4Offset") // FixAdd(x35754,Const(0.375))
    val x35756 = OpDef(op=MuxOp, inputs=List(x35753, x35755, x35749)).name("x35756").ctrl(x35766).srcCtx("CellsPar.scala:58:20:body2") // Mux(x35753,x35755,x35749)
    val x35757 = OpDef(op=MuxOp, inputs=List(x35750, Const(1.0), x35756)).name("x35757").ctrl(x35766).srcCtx("CellsPar.scala:60:20:absre") // Mux(x35750,Const(1),x35756)
    val x35758 = OpDef(op=FixNeg, inputs=List(x35757)).name("x35758").ctrl(x35766).srcCtx("CellsPar.scala:62:29:mabre") // FixNeg(x35757)
    val x35759 = OpDef(op=FixLt, inputs=List(x35748, Const(0.0))).name("x35759").ctrl(x35766).srcCtx("CellsPar.scala:63:22") // FixLt(x35748,Const(0))
    val x35760 = OpDef(op=MuxOp, inputs=List(x35759, x35758, x35757)).name("x35760").ctrl(x35766).srcCtx("CellsPar.scala:63:17:re") // Mux(x35759,x35758,x35757)
    val x35761 = x35760 // FixConvert(x35760,TRUE,_8,_24) (Same Type. No op)
    val x35762 = OpDef(op=FixAdd, inputs=List(x35761, Const(1.0))).name("x35762").ctrl(x35766).srcCtx("CellsPar.scala:29:33") // FixAdd(x35761,Const(1))
    val x35763 = OpDef(op=FixSra, inputs=List(x35762, Const(1))).name("x35763").ctrl(x35766).srcCtx("CellsPar.scala:29:44") // FixRsh(x35762,Const(1))
    val x35764 = OpDef(op=MuxOp, inputs=List(x35744, x35763, x35743)).name("x35764").ctrl(x35766).srcCtx("CellsPar.scala:218:43") // Mux(x35744,x35763,x35743)
    val x35765 = StoreBanks(List(x34689_d0_b0, x34689_d1_b0), List(b23304, x35732), x35764).name("x35765").ctrl(x35766).srcCtx("CellsPar.scala:218:38") // SRAMStore(x34689,ArrayBuffer(Const(1), Const(512)),List(b23304, x35732),Const(0),x35764,x35739)
    val x35770 = Counter(min=Const(0), max=Const(512), step=Const(1), par=1).name("x35770").ctrl(x36742).srcCtx("CellsPar.scala:243:50") // CounterNew(Const(0),Const(512),Const(1),Const(1))
    val x35771 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x35771").ctrl(x36742).srcCtx("CellsPar.scala:243:27") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x35772 = CounterChain(List(x35771,x35770)).name("x35772").ctrl(x36742).srcCtx("CellsPar.scala:243:62") // CounterChainNew(List(x35771, x35770))
    val x35805 = LoopController(style=InnerPipe, level=InnerControl, cchain=x35772).name("x35805").ctrl(x36742).srcCtx("CellsPar.scala:243:62") // UnrolledForeach(List(b22320),x35772,Block(Const(())),List(List(b23383), List(b23384)),List(List(b23385), List(b23386)))
    val b23383 = CounterIter(x35771, Some(0)).name("b23383").ctrl(x35805) // b23383
    val b23385 = Const(true).name("b23385").ctrl(x35805) // b23385
    val b23384 = CounterIter(x35770, None).name("b23384").ctrl(x35805) // b23384
    val b23386 = Const(true).name("b23386").ctrl(x35805) // b23386
    val x35773 = OpDef(op=BitAnd, inputs=List(b23385, b23386)).name("x35773").ctrl(x35805).srcCtx("UnrollingBase.scala:28:66") // And(b23385,b23386)
    val x35774 = OpDef(op=BitAnd, inputs=List(x35773, b22320)).name("x35774").ctrl(x35805).srcCtx("UnrollingBase.scala:28:66") // And(x35773,b22320)
    val x35775 = LoadBanks(List(x34685_d0_b0), List(b23383, b23384)).name("x35775").ctrl(x35805).srcCtx("CellsPar.scala:244:22") // ParSRAMLoad(x34685,List(List(b23383, b23384)),List(x35774))
    val x35776 = x35775 // x35776 = VectorApply(x35775,0)
    val x35777 = LoadBanks(List(x34688_d0_b0), List(b23383, b23384)).name("x35777").ctrl(x35805).srcCtx("CellsPar.scala:244:34") // ParSRAMLoad(x34688,List(List(b23383, b23384)),List(x35774))
    val x35778 = x35777 // x35778 = VectorApply(x35777,0)
    val x35779 = OpDef(op=FixMul, inputs=List(x35776, x35778)).name("x35779").ctrl(x35805).srcCtx("CellsPar.scala:244:28") // FixMul(x35776,x35778)
    val x35780 = LoadBanks(List(x34686_d0_b0), List(b23383, b23384)).name("x35780").ctrl(x35805).srcCtx("CellsPar.scala:244:46") // ParSRAMLoad(x34686,List(List(b23383, b23384)),List(x35774))
    val x35781 = x35780 // x35781 = VectorApply(x35780,0)
    val x35782 = LoadBanks(List(x34687_d0_b0), List(b23383, b23384)).name("x35782").ctrl(x35805).srcCtx("CellsPar.scala:244:59") // ParSRAMLoad(x34687,List(List(b23383, b23384)),List(x35774))
    val x35783 = x35782 // x35783 = VectorApply(x35782,0)
    val x35784 = OpDef(op=FixMul, inputs=List(x35781, x35783)).name("x35784").ctrl(x35805).srcCtx("CellsPar.scala:244:52") // FixMul(x35781,x35783)
    val x35785 = OpDef(op=FixAdd, inputs=List(x35779, x35784)).name("x35785").ctrl(x35805).srcCtx("CellsPar.scala:244:40:new_c") // FixAdd(x35779,x35784)
    val x35786 = x35785 // FixConvert(x35785,TRUE,_8,_24) (Same Type. No op)
    val x35787 = OpDef(op=FixAbs, inputs=List(x35786)).name("x35787").ctrl(x35805).srcCtx("CellsPar.scala:37:19:absp") // FixAbs(x35786)
    val x35788 = OpDef(op=FixLt, inputs=List(Const(2.5), x35787)).name("x35788").ctrl(x35805).srcCtx("CellsPar.scala:51:23:cond1") // FixLt(Const(2.5),x35787)
    val x35789 = OpDef(op=FixLt, inputs=List(Const(0.5), x35787)).name("x35789").ctrl(x35805).srcCtx("CellsPar.scala:54:35") // FixLt(Const(0.5),x35787)
    val x35790 = OpDef(op=FixLeq, inputs=List(x35787, Const(2.5))).name("x35790").ctrl(x35805).srcCtx("CellsPar.scala:54:52") // FixLeq(x35787,Const(2.5))
    val x35791 = OpDef(op=BitAnd, inputs=List(x35789, x35790)).name("x35791").ctrl(x35805).srcCtx("CellsPar.scala:54:43:cond2") // And(x35789,x35790)
    val x35792 = OpDef(op=FixSra, inputs=List(x35787, Const(2))).name("x35792").ctrl(x35805).srcCtx("CellsPar.scala:56:21:div4") // FixRsh(x35787,Const(2))
    val x35793 = OpDef(op=FixAdd, inputs=List(x35792, Const(0.375))).name("x35793").ctrl(x35805).srcCtx("CellsPar.scala:57:27:div4Offset") // FixAdd(x35792,Const(0.375))
    val x35794 = OpDef(op=MuxOp, inputs=List(x35791, x35793, x35787)).name("x35794").ctrl(x35805).srcCtx("CellsPar.scala:58:20:body2") // Mux(x35791,x35793,x35787)
    val x35795 = OpDef(op=MuxOp, inputs=List(x35788, Const(1.0), x35794)).name("x35795").ctrl(x35805).srcCtx("CellsPar.scala:60:20:absre") // Mux(x35788,Const(1),x35794)
    val x35796 = OpDef(op=FixNeg, inputs=List(x35795)).name("x35796").ctrl(x35805).srcCtx("CellsPar.scala:62:29:mabre") // FixNeg(x35795)
    val x35797 = OpDef(op=FixLt, inputs=List(x35786, Const(0.0))).name("x35797").ctrl(x35805).srcCtx("CellsPar.scala:63:22") // FixLt(x35786,Const(0))
    val x35798 = OpDef(op=MuxOp, inputs=List(x35797, x35796, x35795)).name("x35798").ctrl(x35805).srcCtx("CellsPar.scala:63:17:re") // Mux(x35797,x35796,x35795)
    val x35799 = x35798 // FixConvert(x35798,TRUE,_8,_24) (Same Type. No op)
    val x35800 = LoadBanks(List(x34689_d0_b0), List(b23383, b23384)).name("x35800").ctrl(x35805).srcCtx("CellsPar.scala:245:45") // ParSRAMLoad(x34689,List(List(b23383, b23384)),List(x35774))
    val x35801 = x35800 // x35801 = VectorApply(x35800,0)
    val x35802 = OpDef(op=FixMul, inputs=List(x35799, x35801)).name("x35802").ctrl(x35805).srcCtx("CellsPar.scala:245:39") // FixMul(x35799,x35801)
    val x35803 = StoreBanks(List(x34684_d0_b0, x34684_d5_b0, x34684_d1_b0, x34684_d6_b0, x34684_d2_b0, x34684_d7_b0, x34684_d3_b0, x34684_d4_b0), List(b23383, b23384), x35802).name("x35803").ctrl(x35805).srcCtx("CellsPar.scala:245:16") // ParSRAMStore(x34684,List(List(b23383, b23384)),List(x35802),List(x35774))
    val x35804 = StoreBanks(List(x34685_d0_b0), List(b23383, b23384), x35785).name("x35804").ctrl(x35805).srcCtx("CellsPar.scala:246:16") // ParSRAMStore(x34685,List(List(b23383, b23384)),List(x35785),List(x35774))
    val x35806 = Counter(min=Const(0), max=Const(512), step=Const(16), par=1).name("x35806").ctrl(x36742).srcCtx("CellsPar.scala:190:58") // CounterNew(Const(0),Const(512),Const(16),Const(1))
    val x35807 = Counter(min=Const(0), max=Const(1536), step=Const(16), par=1).name("x35807").ctrl(x36742).srcCtx("CellsPar.scala:190:33") // CounterNew(Const(0),Const(1536),Const(16),Const(1))
    val x35808 = CounterChain(List(x35807,x35806)).name("x35808").ctrl(x36742).srcCtx("CellsPar.scala:190:72") // CounterChainNew(List(x35807, x35806))
    val x35913 = LoopController(style=MetaPipe, level=OuterControl, cchain=x35808).name("x35913").ctrl(x36742).srcCtx("CellsPar.scala:190:72") // UnrolledForeach(List(b22320),x35808,Block(Const(())),List(List(b23423), List(b23424)),List(List(b23425), List(b23426)))
    val b23423 = CounterIter(x35807, Some(0)).name("b23423").ctrl(x35913) // b23423
    val b23425 = Const(true).name("b23425").ctrl(x35913) // b23425
    val b23424 = CounterIter(x35806, Some(0)).name("b23424").ctrl(x35913) // b23424
    val b23426 = Const(true).name("b23426").ctrl(x35913) // b23426
    val x35809_d0_b0 = SRAM(size=256, banking=Strided(banks=1, stride=16)).name("x35809_d0_b0").ctrl(x35913).srcCtx("CellsPar.scala:191:33:tileKernel") // x35809 = SRAMNew(ArrayBuffer(Const(16), Const(16)))
    isAccum(x35809_d0_b0) = false
    bufferDepthOf(x35809_d0_b0) = 2
    val x35812 = UnitController(style=SeqPipe, level=InnerControl).name("x35812").ctrl(x35913).srcCtx("CellsPar.scala:190:72") // UnitPipe(List(b23425, b23426, b22320),Block(Const(())))
    val x35810 = OpDef(op=FixAdd, inputs=List(b23423, Const(16))).name("x35810").ctrl(x35812).srcCtx("CellsPar.scala:192:36") // FixAdd(b23423,Const(16))
    val x35811 = OpDef(op=FixAdd, inputs=List(b23424, Const(16))).name("x35811").ctrl(x35812).srcCtx("CellsPar.scala:192:45") // FixAdd(b23424,Const(16))
    val x35813 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x35813").ctrl(x35913).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x35814 = CounterChain(List(x35813)).name("x35814").ctrl(x35913).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x35813))
    val x35843 = LoopController(style=StreamPipe, level=OuterControl, cchain=x35814).name("x35843").ctrl(x35913).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b23425, b23426, b22320),x35814,Block(Const(())),List(List(b23433)),List(List(b23434)))
    val b23433 = CounterIter(x35813, Some(0)).name("b23433").ctrl(x35843) // b23433
    val b23434 = Const(true).name("b23434").ctrl(x35843) // b23434
    val b36861 = StreamOut(field="offset").name("b36861").ctrl(x35843).srcCtx("CellsPar.scala:192:20") // x35815 = StreamOutNew(BurstCmdBus)
    isAccum(b36861) = false
    bufferDepthOf(b36861) = 1
    val b36862 = StreamOut(field="size").name("b36862").ctrl(x35843).srcCtx("CellsPar.scala:192:20") // x35815 = StreamOutNew(BurstCmdBus)
    isAccum(b36862) = false
    bufferDepthOf(b36862) = 1
    val x35816 = StreamIn(field="data").name("x35816").ctrl(x35843).srcCtx("CellsPar.scala:192:20") // x35816 = StreamInNew(BurstDataBus())
    isAccum(x35816) = false
    bufferDepthOf(x35816) = 1
    val x35831 = UnitController(style=SeqPipe, level=InnerControl).name("x35831").ctrl(x35843).srcCtx("CellsPar.scala:192:20") // UnitPipe(List(b23434, b23425, b23426, b22320),Block(x35830))
    val x35817 = OpDef(op=FixAdd, inputs=List(b23423, b23433)).name("x35817").ctrl(x35831).srcCtx("CellsPar.scala:192:20") // FixAdd(b23423,b23433)
    val x35818 = x35817 // FixConvert(x35817,TRUE,_32,_0) (Same Type. No op)
    val x35819 = OpDef(op=FixSla, inputs=List(x35818, Const(9))).name("x35819").ctrl(x35831).srcCtx("CellsPar.scala:192:20") // FixLsh(x35818,Const(9))
    val x35820 = b23424 // FixConvert(b23424,TRUE,_32,_0) (Same Type. No op)
    val x35821 = OpDef(op=FixAdd, inputs=List(x35819, x35820)).name("x35821").ctrl(x35831).srcCtx("CellsPar.scala:192:20") // FixAdd(x35819,x35820)
    val x35822 = OpDef(op=FixSla, inputs=List(x35821, Const(2))).name("x35822").ctrl(x35831).srcCtx("CellsPar.scala:192:20") // FixLsh(x35821,Const(2))
    val x35823 = x35822 // FixConvert(x35822,TRUE,_64,_0)
    val x35824 = DramAddress(x34552).name("x35824").ctrl(x35831).srcCtx("CellsPar.scala:192:20") // GetDRAMAddress(x34552)
    val x35826_x35825 = OpDef(op=FixAdd, inputs=List(x35823, x35824)).name("x35826_x35825").ctrl(x35831).srcCtx("CellsPar.scala:192:20") // FixAdd(x35823,x35824)
    // x35826 = SimpleStruct(ArrayBuffer((offset,x35825), (size,Const(64)), (isLoad,Const(true))))
    val x35827 = OpDef(op=BitAnd, inputs=List(b23434, b23425)).name("x35827").ctrl(x35831).srcCtx("UnrollingBase.scala:28:66") // And(b23434,b23425)
    val x35828 = OpDef(op=BitAnd, inputs=List(b23426, b22320)).name("x35828").ctrl(x35831).srcCtx("UnrollingBase.scala:28:66") // And(b23426,b22320)
    val x35829 = OpDef(op=BitAnd, inputs=List(x35827, x35828)).name("x35829").ctrl(x35831).srcCtx("UnrollingBase.scala:28:66") // And(x35827,x35828)
    val x35830_b36863_b36861 = WriteMem(b36861, x35826_x35825).name("x35830_b36863_b36861").ctrl(x35831).srcCtx("CellsPar.scala:192:20") // StreamWrite(x35815,x35826,x35829)
    val x35830_b36864_b36862 = WriteMem(b36862, Const(64)).name("x35830_b36864_b36862").ctrl(x35831).srcCtx("CellsPar.scala:192:20") // StreamWrite(x35815,x35826,x35829)
    val x35832 = FringeDenseLoad(dram=List(x34552), cmdStream=List(b36861, b36862), dataStream=List(x35816)).name("x35832").ctrl(x35843).srcCtx("CellsPar.scala:192:20") // FringeDenseLoad(x34552,x35815,x35816)
    val x35833 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x35833").ctrl(x35843).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x35834 = CounterChain(List(x35833)).name("x35834").ctrl(x35843).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x35833))
    val x35842 = LoopController(style=InnerPipe, level=InnerControl, cchain=x35834).name("x35842").ctrl(x35843).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b23434, b23425, b23426, b22320),x35834,Block(Const(())),List(List(b23455)),List(List(b23456)))
    val b23455 = CounterIter(x35833, None).name("b23455").ctrl(x35842) // b23455
    val b23456 = Const(true).name("b23456").ctrl(x35842) // b23456
    val x35835 = OpDef(op=BitAnd, inputs=List(b23456, b23434)).name("x35835").ctrl(x35842).srcCtx("UnrollingBase.scala:28:66") // And(b23456,b23434)
    val x35836 = OpDef(op=BitAnd, inputs=List(b23425, b23426)).name("x35836").ctrl(x35842).srcCtx("UnrollingBase.scala:28:66") // And(b23425,b23426)
    val x35837 = OpDef(op=BitAnd, inputs=List(x35835, x35836)).name("x35837").ctrl(x35842).srcCtx("UnrollingBase.scala:28:66") // And(x35835,x35836)
    val x35838 = OpDef(op=BitAnd, inputs=List(x35837, b22320)).name("x35838").ctrl(x35842).srcCtx("UnrollingBase.scala:28:66") // And(x35837,b22320)
    val x35839_x35839 = ReadMem(x35816).name("x35839_x35839").ctrl(x35842).srcCtx("CellsPar.scala:192:20") // ParStreamRead(x35816,List(x35838))
    val x35840_x35840 = x35839_x35839 // x35840 = VectorApply(x35839,0)
    val x35841 = StoreBanks(List(x35809_d0_b0), List(b23433, b23455), x35840_x35840).name("x35841").ctrl(x35842).srcCtx("CellsPar.scala:192:20") // ParSRAMStore(x35809,List(List(b23433, b23455)),List(x35840),List(x35838))
    val x35844 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x35844").ctrl(x35913).srcCtx("CellsPar.scala:193:34") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x35845 = CounterChain(List(x35844)).name("x35845").ctrl(x35913).srcCtx("CellsPar.scala:193:46") // CounterChainNew(List(x35844))
    val x35912 = LoopController(style=MetaPipe, level=OuterControl, cchain=x35845).name("x35912").ctrl(x35913).srcCtx("CellsPar.scala:193:46") // UnrolledForeach(List(b23425, b23426, b22320),x35845,Block(Const(())),List(List(b23468)),List(List(b23469)))
    val b23468 = CounterIter(x35844, Some(0)).name("b23468").ctrl(x35912) // b23468
    val b23469 = Const(true).name("b23469").ctrl(x35912) // b23469
    val x35846 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x35846").ctrl(x35912).srcCtx("CellsPar.scala:194:28") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x35847 = CounterChain(List(x35846)).name("x35847").ctrl(x35912).srcCtx("CellsPar.scala:194:42") // CounterChainNew(List(x35846))
    val x35911 = LoopController(style=MetaPipe, level=OuterControl, cchain=x35847).name("x35911").ctrl(x35912).srcCtx("CellsPar.scala:194:42") // UnrolledForeach(List(b23469, b23425, b23426, b22320),x35847,Block(Const(())),List(List(b23472)),List(List(b23473)))
    val b23472 = CounterIter(x35846, Some(0)).name("b23472").ctrl(x35911) // b23472
    val b23473 = Const(true).name("b23473").ctrl(x35911) // b23473
    val x35848_d0 = Reg(init=Some(0.0)).name("x35848_d0").ctrl(x35911).srcCtx("CellsPar.scala:195:34:prod") // x35848 = RegNew(Const(0))
    isAccum(x35848_d0) = false
    bufferDepthOf(x35848_d0) = 2
    val x35848_d1 = Reg(init=Some(0.0)).name("x35848_d1").ctrl(x35911).srcCtx("CellsPar.scala:195:34:prod") // x35848 = RegNew(Const(0))
    isAccum(x35848_d1) = true
    bufferDepthOf(x35848_d1) = 1
    val x35849 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x35849").ctrl(x35911).srcCtx("CellsPar.scala:195:43") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x35850 = CounterChain(List(x35849)).name("x35850").ctrl(x35911).srcCtx("CellsPar.scala:207:15") // CounterChainNew(List(x35849))
    val x35876 = LoopController(style=InnerPipe, level=InnerControl, cchain=x35850).name("x35876").ctrl(x35911).srcCtx("CellsPar.scala:207:15") // UnrolledReduce(List(b23473, b23469, b23425, b23426, b22320),x35850,x35848,Block((x35848) => Const(())),List(List(b23477)),List(List(b23478)))
    val b23477 = CounterIter(x35849, None).name("b23477").ctrl(x35876) // b23477
    val b23478 = Const(true).name("b23478").ctrl(x35876) // b23478
    val x35851 = OpDef(op=FixAdd, inputs=List(b23423, b23477)).name("x35851").ctrl(x35876).srcCtx("CellsPar.scala:196:42:reduce_size_offset") // FixAdd(b23423,b23477)
    val x35852 = OpDef(op=BitAnd, inputs=List(b23478, b23473)).name("x35852").ctrl(x35876).srcCtx("UnrollingBase.scala:28:66") // And(b23478,b23473)
    val x35853 = OpDef(op=BitAnd, inputs=List(b23469, b23425)).name("x35853").ctrl(x35876).srcCtx("UnrollingBase.scala:28:66") // And(b23469,b23425)
    val x35854 = OpDef(op=BitAnd, inputs=List(b23426, b22320)).name("x35854").ctrl(x35876).srcCtx("UnrollingBase.scala:28:66") // And(b23426,b22320)
    val x35855 = OpDef(op=BitAnd, inputs=List(x35852, x35853)).name("x35855").ctrl(x35876).srcCtx("UnrollingBase.scala:28:66") // And(x35852,x35853)
    val x35856 = OpDef(op=BitAnd, inputs=List(x35855, x35854)).name("x35856").ctrl(x35876).srcCtx("UnrollingBase.scala:28:66") // And(x35855,x35854)
    val x35857 = LoadBanks(List(x35809_d0_b0), List(b23477, b23472)).name("x35857").ctrl(x35876).srcCtx("CellsPar.scala:203:34:tk") // ParSRAMLoad(x35809,List(List(b23477, b23472)),List(x35856))
    val x35858 = x35857 // x35858 = VectorApply(x35857,0)
    val x35859 = LoadBanks(List(x34684_d3_b0), List(b23468, x35851)).name("x35859").ctrl(x35876).srcCtx("CellsPar.scala:204:28") // ParSRAMLoad(x34684,List(List(b23468, x35851)),List(x35856))
    val x35860 = x35859 // x35860 = VectorApply(x35859,0)
    val x35861 = OpDef(op=FixMul, inputs=List(x35860, x35858)).name("x35861").ctrl(x35876).srcCtx("CellsPar.scala:204:52:tempA") // FixMul(x35860,x35858)
    val x35862 = OpDef(op=FixSub, inputs=List(x35851, Const(512))).name("x35862").ctrl(x35876).srcCtx("CellsPar.scala:205:51") // FixSub(x35851,Const(512))
    val x35863 = LoadBanks(List(x34691_d7_b0), List(b23468, x35862)).name("x35863").ctrl(x35876).srcCtx("CellsPar.scala:205:28") // ParSRAMLoad(x34691,List(List(b23468, x35862)),List(x35856))
    val x35864 = x35863 // x35864 = VectorApply(x35863,0)
    val x35865 = OpDef(op=FixMul, inputs=List(x35864, x35858)).name("x35865").ctrl(x35876).srcCtx("CellsPar.scala:205:67:tempB") // FixMul(x35864,x35858)
    val x35866 = OpDef(op=FixLt, inputs=List(x35851, Const(512))).name("x35866").ctrl(x35876).srcCtx("CellsPar.scala:206:38") // FixLt(x35851,Const(512))
    val x35867 = OpDef(op=MuxOp, inputs=List(x35866, x35861, x35865)).name("x35867").ctrl(x35876).srcCtx("CellsPar.scala:206:18") // Mux(x35866,x35861,x35865)
    val x35868 = ReadMem(x35848_d1).name("x35868").ctrl(x35876).srcCtx("CellsPar.scala:207:15") // RegRead(x35848)
    val x35869 = OpDef(op=FixEql, inputs=List(b23477, Const(0))).name("x35869").ctrl(x35876).srcCtx("CellsPar.scala:207:15") // FixEql(b23477,Const(0))
    val x35870 = ReduceAccumOp(op=FixAdd, input=x35867, accum=x35868).name("x35870").ctrl(x35876).srcCtx("CellsPar.scala:207:17") // FixAdd(x35867,x35868)
    val x35871 = OpDef(op=BitAnd, inputs=List(b23473, b23469)).name("x35871").ctrl(x35876).srcCtx("UnrollingBase.scala:28:66") // And(b23473,b23469)
    val x35872 = OpDef(op=BitAnd, inputs=List(b23425, b23426)).name("x35872").ctrl(x35876).srcCtx("UnrollingBase.scala:28:66") // And(b23425,b23426)
    val x35873 = OpDef(op=BitAnd, inputs=List(x35871, x35872)).name("x35873").ctrl(x35876).srcCtx("UnrollingBase.scala:28:66") // And(x35871,x35872)
    val x35874 = OpDef(op=BitAnd, inputs=List(x35873, b22320)).name("x35874").ctrl(x35876).srcCtx("UnrollingBase.scala:28:66") // And(x35873,b22320)
    val x35875_x35848_d0 = WriteMem(x35848_d0, x35870).name("x35875_x35848_d0").ctrl(x35876).srcCtx("CellsPar.scala:207:15") // RegWrite(x35848,x35870,x35874)
    val x35875_x35848_d1 = WriteMem(x35848_d1, x35870).name("x35875_x35848_d1").ctrl(x35876).srcCtx("CellsPar.scala:207:15") // RegWrite(x35848,x35870,x35874)
    val x35910 = UnitController(style=SeqPipe, level=InnerControl).name("x35910").ctrl(x35911).srcCtx("CellsPar.scala:194:42") // UnitPipe(List(b23473, b23469, b23425, b23426, b22320),Block(Const(())))
    val x35877 = OpDef(op=FixAdd, inputs=List(b23424, b23472)).name("x35877").ctrl(x35910).srcCtx("CellsPar.scala:209:31:colOffset") // FixAdd(b23424,b23472)
    val x35878 = ReadMem(x35848_d0).name("x35878").ctrl(x35910).srcCtx("CellsPar.scala:210:28") // RegRead(x35848)
    val x35879 = OpDef(op=FixEql, inputs=List(b23423, Const(0))).name("x35879").ctrl(x35910).srcCtx("CellsPar.scala:210:42") // FixEql(b23423,Const(0))
    val x35880 = OpDef(op=BitAnd, inputs=List(b23473, b23469)).name("x35880").ctrl(x35910).srcCtx("UnrollingBase.scala:28:66") // And(b23473,b23469)
    val x35881 = OpDef(op=BitAnd, inputs=List(b23425, b23426)).name("x35881").ctrl(x35910).srcCtx("UnrollingBase.scala:28:66") // And(b23425,b23426)
    val x35882 = OpDef(op=BitAnd, inputs=List(x35880, x35881)).name("x35882").ctrl(x35910).srcCtx("UnrollingBase.scala:28:66") // And(x35880,x35881)
    val x35883 = OpDef(op=BitAnd, inputs=List(x35882, b22320)).name("x35883").ctrl(x35910).srcCtx("UnrollingBase.scala:28:66") // And(x35882,b22320)
    val x35884 = LoadBanks(List(x34697_d3_b0), List(Const(0), x35877)).name("x35884").ctrl(x35910).srcCtx("CellsPar.scala:210:52") // SRAMLoad(x34697,ArrayBuffer(Const(1), Const(2048)),List(Const(0), x35877),Const(0),x35883)
    val x35885 = LoadBanks(List(x34693_d1_b0), List(b23468, x35877)).name("x35885").ctrl(x35910).srcCtx("CellsPar.scala:210:91") // SRAMLoad(x34693,ArrayBuffer(Const(1), Const(512)),List(b23468, x35877),Const(0),x35883)
    val x35886 = OpDef(op=MuxOp, inputs=List(x35879, x35884, x35885)).name("x35886").ctrl(x35910).srcCtx("CellsPar.scala:210:39") // Mux(x35879,x35884,x35885)
    val x35887 = OpDef(op=FixAdd, inputs=List(x35878, x35886)).name("x35887").ctrl(x35910).srcCtx("CellsPar.scala:210:34:ele") // FixAdd(x35878,x35886)
    val x35888 = OpDef(op=FixLeq, inputs=List(Const(1520), b23423)).name("x35888").ctrl(x35910).srcCtx("CellsPar.scala:218:46") // FixLeq(Const(1520),b23423)
    // x35889 = FixConvert(Const(0),TRUE,_8,_24) x.tp=FixPt[TRUE,_32,_0] {
    val x35889_int1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("01111111111111111111111111111111"))).name("x35889_int1").ctrl(x35910).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x35889_int2 = OpDef(op=FixSla, inputs=List(x35889_int1, Const(24))).name("x35889_int2").ctrl(x35910).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x35889_frac1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("00000000000000000000000000000000"))).name("x35889_frac1").ctrl(x35910).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x35889_frac2 = OpDef(op=FixSla, inputs=List(x35889_frac1, Const(24))).name("x35889_frac2").ctrl(x35910).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x35889 = OpDef(op=BitOr, inputs=List(x35889_int2, x35889_frac2)).name("x35889").ctrl(x35910).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    // }
    val x35890 = OpDef(op=FixAdd, inputs=List(x35887, x35889)).name("x35890").ctrl(x35910).srcCtx("CellsPar.scala:218:87") // FixAdd(x35887,x35889)
    val x35891 = OpDef(op=FixSra, inputs=List(x35890, Const(1))).name("x35891").ctrl(x35910).srcCtx("CellsPar.scala:29:22") // FixRsh(x35890,Const(1))
    val x35892 = x35891 // FixConvert(x35891,TRUE,_8,_24) (Same Type. No op)
    val x35893 = OpDef(op=FixAbs, inputs=List(x35892)).name("x35893").ctrl(x35910).srcCtx("CellsPar.scala:37:19:absp") // FixAbs(x35892)
    val x35894 = OpDef(op=FixLt, inputs=List(Const(2.5), x35893)).name("x35894").ctrl(x35910).srcCtx("CellsPar.scala:51:23:cond1") // FixLt(Const(2.5),x35893)
    val x35895 = OpDef(op=FixLt, inputs=List(Const(0.5), x35893)).name("x35895").ctrl(x35910).srcCtx("CellsPar.scala:54:35") // FixLt(Const(0.5),x35893)
    val x35896 = OpDef(op=FixLeq, inputs=List(x35893, Const(2.5))).name("x35896").ctrl(x35910).srcCtx("CellsPar.scala:54:52") // FixLeq(x35893,Const(2.5))
    val x35897 = OpDef(op=BitAnd, inputs=List(x35895, x35896)).name("x35897").ctrl(x35910).srcCtx("CellsPar.scala:54:43:cond2") // And(x35895,x35896)
    val x35898 = OpDef(op=FixSra, inputs=List(x35893, Const(2))).name("x35898").ctrl(x35910).srcCtx("CellsPar.scala:56:21:div4") // FixRsh(x35893,Const(2))
    val x35899 = OpDef(op=FixAdd, inputs=List(x35898, Const(0.375))).name("x35899").ctrl(x35910).srcCtx("CellsPar.scala:57:27:div4Offset") // FixAdd(x35898,Const(0.375))
    val x35900 = OpDef(op=MuxOp, inputs=List(x35897, x35899, x35893)).name("x35900").ctrl(x35910).srcCtx("CellsPar.scala:58:20:body2") // Mux(x35897,x35899,x35893)
    val x35901 = OpDef(op=MuxOp, inputs=List(x35894, Const(1.0), x35900)).name("x35901").ctrl(x35910).srcCtx("CellsPar.scala:60:20:absre") // Mux(x35894,Const(1),x35900)
    val x35902 = OpDef(op=FixNeg, inputs=List(x35901)).name("x35902").ctrl(x35910).srcCtx("CellsPar.scala:62:29:mabre") // FixNeg(x35901)
    val x35903 = OpDef(op=FixLt, inputs=List(x35892, Const(0.0))).name("x35903").ctrl(x35910).srcCtx("CellsPar.scala:63:22") // FixLt(x35892,Const(0))
    val x35904 = OpDef(op=MuxOp, inputs=List(x35903, x35902, x35901)).name("x35904").ctrl(x35910).srcCtx("CellsPar.scala:63:17:re") // Mux(x35903,x35902,x35901)
    val x35905 = x35904 // FixConvert(x35904,TRUE,_8,_24) (Same Type. No op)
    val x35906 = OpDef(op=FixAdd, inputs=List(x35905, Const(1.0))).name("x35906").ctrl(x35910).srcCtx("CellsPar.scala:29:33") // FixAdd(x35905,Const(1))
    val x35907 = OpDef(op=FixSra, inputs=List(x35906, Const(1))).name("x35907").ctrl(x35910).srcCtx("CellsPar.scala:29:44") // FixRsh(x35906,Const(1))
    val x35908 = OpDef(op=MuxOp, inputs=List(x35888, x35907, x35887)).name("x35908").ctrl(x35910).srcCtx("CellsPar.scala:218:43") // Mux(x35888,x35907,x35887)
    val x35909 = StoreBanks(List(x34693_d0_b0, x34693_d1_b0), List(b23468, x35877), x35908).name("x35909").ctrl(x35910).srcCtx("CellsPar.scala:218:38") // SRAMStore(x34693,ArrayBuffer(Const(1), Const(512)),List(b23468, x35877),Const(0),x35908,x35883)
    val x35914 = Counter(min=Const(0), max=Const(512), step=Const(16), par=1).name("x35914").ctrl(x36742).srcCtx("CellsPar.scala:190:58") // CounterNew(Const(0),Const(512),Const(16),Const(1))
    val x35915 = Counter(min=Const(0), max=Const(1536), step=Const(16), par=1).name("x35915").ctrl(x36742).srcCtx("CellsPar.scala:190:33") // CounterNew(Const(0),Const(1536),Const(16),Const(1))
    val x35916 = CounterChain(List(x35915,x35914)).name("x35916").ctrl(x36742).srcCtx("CellsPar.scala:190:72") // CounterChainNew(List(x35915, x35914))
    val x36019 = LoopController(style=MetaPipe, level=OuterControl, cchain=x35916).name("x36019").ctrl(x36742).srcCtx("CellsPar.scala:190:72") // UnrolledForeach(List(b22320),x35916,Block(Const(())),List(List(b23545), List(b23546)),List(List(b23547), List(b23548)))
    val b23545 = CounterIter(x35915, Some(0)).name("b23545").ctrl(x36019) // b23545
    val b23547 = Const(true).name("b23547").ctrl(x36019) // b23547
    val b23546 = CounterIter(x35914, Some(0)).name("b23546").ctrl(x36019) // b23546
    val b23548 = Const(true).name("b23548").ctrl(x36019) // b23548
    val x35917_d0_b0 = SRAM(size=256, banking=Strided(banks=1, stride=16)).name("x35917_d0_b0").ctrl(x36019).srcCtx("CellsPar.scala:191:33:tileKernel") // x35917 = SRAMNew(ArrayBuffer(Const(16), Const(16)))
    isAccum(x35917_d0_b0) = false
    bufferDepthOf(x35917_d0_b0) = 2
    val x35920 = UnitController(style=SeqPipe, level=InnerControl).name("x35920").ctrl(x36019).srcCtx("CellsPar.scala:190:72") // UnitPipe(List(b23547, b23548, b22320),Block(Const(())))
    val x35918 = OpDef(op=FixAdd, inputs=List(b23545, Const(16))).name("x35918").ctrl(x35920).srcCtx("CellsPar.scala:192:36") // FixAdd(b23545,Const(16))
    val x35919 = OpDef(op=FixAdd, inputs=List(b23546, Const(16))).name("x35919").ctrl(x35920).srcCtx("CellsPar.scala:192:45") // FixAdd(b23546,Const(16))
    val x35921 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x35921").ctrl(x36019).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x35922 = CounterChain(List(x35921)).name("x35922").ctrl(x36019).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x35921))
    val x35951 = LoopController(style=StreamPipe, level=OuterControl, cchain=x35922).name("x35951").ctrl(x36019).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b23547, b23548, b22320),x35922,Block(Const(())),List(List(b23555)),List(List(b23556)))
    val b23555 = CounterIter(x35921, Some(0)).name("b23555").ctrl(x35951) // b23555
    val b23556 = Const(true).name("b23556").ctrl(x35951) // b23556
    val b36865 = StreamOut(field="offset").name("b36865").ctrl(x35951).srcCtx("CellsPar.scala:192:20") // x35923 = StreamOutNew(BurstCmdBus)
    isAccum(b36865) = false
    bufferDepthOf(b36865) = 1
    val b36866 = StreamOut(field="size").name("b36866").ctrl(x35951).srcCtx("CellsPar.scala:192:20") // x35923 = StreamOutNew(BurstCmdBus)
    isAccum(b36866) = false
    bufferDepthOf(b36866) = 1
    val x35924 = StreamIn(field="data").name("x35924").ctrl(x35951).srcCtx("CellsPar.scala:192:20") // x35924 = StreamInNew(BurstDataBus())
    isAccum(x35924) = false
    bufferDepthOf(x35924) = 1
    val x35939 = UnitController(style=SeqPipe, level=InnerControl).name("x35939").ctrl(x35951).srcCtx("CellsPar.scala:192:20") // UnitPipe(List(b23556, b23547, b23548, b22320),Block(x35938))
    val x35925 = OpDef(op=FixAdd, inputs=List(b23545, b23555)).name("x35925").ctrl(x35939).srcCtx("CellsPar.scala:192:20") // FixAdd(b23545,b23555)
    val x35926 = x35925 // FixConvert(x35925,TRUE,_32,_0) (Same Type. No op)
    val x35927 = OpDef(op=FixSla, inputs=List(x35926, Const(9))).name("x35927").ctrl(x35939).srcCtx("CellsPar.scala:192:20") // FixLsh(x35926,Const(9))
    val x35928 = b23546 // FixConvert(b23546,TRUE,_32,_0) (Same Type. No op)
    val x35929 = OpDef(op=FixAdd, inputs=List(x35927, x35928)).name("x35929").ctrl(x35939).srcCtx("CellsPar.scala:192:20") // FixAdd(x35927,x35928)
    val x35930 = OpDef(op=FixSla, inputs=List(x35929, Const(2))).name("x35930").ctrl(x35939).srcCtx("CellsPar.scala:192:20") // FixLsh(x35929,Const(2))
    val x35931 = x35930 // FixConvert(x35930,TRUE,_64,_0)
    val x35932 = DramAddress(x34553).name("x35932").ctrl(x35939).srcCtx("CellsPar.scala:192:20") // GetDRAMAddress(x34553)
    val x35934_x35933 = OpDef(op=FixAdd, inputs=List(x35931, x35932)).name("x35934_x35933").ctrl(x35939).srcCtx("CellsPar.scala:192:20") // FixAdd(x35931,x35932)
    // x35934 = SimpleStruct(ArrayBuffer((offset,x35933), (size,Const(64)), (isLoad,Const(true))))
    val x35935 = OpDef(op=BitAnd, inputs=List(b23556, b23547)).name("x35935").ctrl(x35939).srcCtx("UnrollingBase.scala:28:66") // And(b23556,b23547)
    val x35936 = OpDef(op=BitAnd, inputs=List(b23548, b22320)).name("x35936").ctrl(x35939).srcCtx("UnrollingBase.scala:28:66") // And(b23548,b22320)
    val x35937 = OpDef(op=BitAnd, inputs=List(x35935, x35936)).name("x35937").ctrl(x35939).srcCtx("UnrollingBase.scala:28:66") // And(x35935,x35936)
    val x35938_b36867_b36865 = WriteMem(b36865, x35934_x35933).name("x35938_b36867_b36865").ctrl(x35939).srcCtx("CellsPar.scala:192:20") // StreamWrite(x35923,x35934,x35937)
    val x35938_b36868_b36866 = WriteMem(b36866, Const(64)).name("x35938_b36868_b36866").ctrl(x35939).srcCtx("CellsPar.scala:192:20") // StreamWrite(x35923,x35934,x35937)
    val x35940 = FringeDenseLoad(dram=List(x34553), cmdStream=List(b36865, b36866), dataStream=List(x35924)).name("x35940").ctrl(x35951).srcCtx("CellsPar.scala:192:20") // FringeDenseLoad(x34553,x35923,x35924)
    val x35941 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x35941").ctrl(x35951).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x35942 = CounterChain(List(x35941)).name("x35942").ctrl(x35951).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x35941))
    val x35950 = LoopController(style=InnerPipe, level=InnerControl, cchain=x35942).name("x35950").ctrl(x35951).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b23556, b23547, b23548, b22320),x35942,Block(Const(())),List(List(b23577)),List(List(b23578)))
    val b23577 = CounterIter(x35941, None).name("b23577").ctrl(x35950) // b23577
    val b23578 = Const(true).name("b23578").ctrl(x35950) // b23578
    val x35943 = OpDef(op=BitAnd, inputs=List(b23578, b23556)).name("x35943").ctrl(x35950).srcCtx("UnrollingBase.scala:28:66") // And(b23578,b23556)
    val x35944 = OpDef(op=BitAnd, inputs=List(b23547, b23548)).name("x35944").ctrl(x35950).srcCtx("UnrollingBase.scala:28:66") // And(b23547,b23548)
    val x35945 = OpDef(op=BitAnd, inputs=List(x35943, x35944)).name("x35945").ctrl(x35950).srcCtx("UnrollingBase.scala:28:66") // And(x35943,x35944)
    val x35946 = OpDef(op=BitAnd, inputs=List(x35945, b22320)).name("x35946").ctrl(x35950).srcCtx("UnrollingBase.scala:28:66") // And(x35945,b22320)
    val x35947_x35947 = ReadMem(x35924).name("x35947_x35947").ctrl(x35950).srcCtx("CellsPar.scala:192:20") // ParStreamRead(x35924,List(x35946))
    val x35948_x35948 = x35947_x35947 // x35948 = VectorApply(x35947,0)
    val x35949 = StoreBanks(List(x35917_d0_b0), List(b23555, b23577), x35948_x35948).name("x35949").ctrl(x35950).srcCtx("CellsPar.scala:192:20") // ParSRAMStore(x35917,List(List(b23555, b23577)),List(x35948),List(x35946))
    val x35952 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x35952").ctrl(x36019).srcCtx("CellsPar.scala:193:34") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x35953 = CounterChain(List(x35952)).name("x35953").ctrl(x36019).srcCtx("CellsPar.scala:193:46") // CounterChainNew(List(x35952))
    val x36018 = LoopController(style=MetaPipe, level=OuterControl, cchain=x35953).name("x36018").ctrl(x36019).srcCtx("CellsPar.scala:193:46") // UnrolledForeach(List(b23547, b23548, b22320),x35953,Block(Const(())),List(List(b23590)),List(List(b23591)))
    val b23590 = CounterIter(x35952, Some(0)).name("b23590").ctrl(x36018) // b23590
    val b23591 = Const(true).name("b23591").ctrl(x36018) // b23591
    val x35954 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x35954").ctrl(x36018).srcCtx("CellsPar.scala:194:28") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x35955 = CounterChain(List(x35954)).name("x35955").ctrl(x36018).srcCtx("CellsPar.scala:194:42") // CounterChainNew(List(x35954))
    val x36017 = LoopController(style=MetaPipe, level=OuterControl, cchain=x35955).name("x36017").ctrl(x36018).srcCtx("CellsPar.scala:194:42") // UnrolledForeach(List(b23591, b23547, b23548, b22320),x35955,Block(Const(())),List(List(b23594)),List(List(b23595)))
    val b23594 = CounterIter(x35954, Some(0)).name("b23594").ctrl(x36017) // b23594
    val b23595 = Const(true).name("b23595").ctrl(x36017) // b23595
    val x35956_d0 = Reg(init=Some(0.0)).name("x35956_d0").ctrl(x36017).srcCtx("CellsPar.scala:195:34:prod") // x35956 = RegNew(Const(0))
    isAccum(x35956_d0) = false
    bufferDepthOf(x35956_d0) = 2
    val x35956_d1 = Reg(init=Some(0.0)).name("x35956_d1").ctrl(x36017).srcCtx("CellsPar.scala:195:34:prod") // x35956 = RegNew(Const(0))
    isAccum(x35956_d1) = true
    bufferDepthOf(x35956_d1) = 1
    val x35957 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x35957").ctrl(x36017).srcCtx("CellsPar.scala:195:43") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x35958 = CounterChain(List(x35957)).name("x35958").ctrl(x36017).srcCtx("CellsPar.scala:207:15") // CounterChainNew(List(x35957))
    val x35984 = LoopController(style=InnerPipe, level=InnerControl, cchain=x35958).name("x35984").ctrl(x36017).srcCtx("CellsPar.scala:207:15") // UnrolledReduce(List(b23595, b23591, b23547, b23548, b22320),x35958,x35956,Block((x35956) => Const(())),List(List(b23599)),List(List(b23600)))
    val b23599 = CounterIter(x35957, None).name("b23599").ctrl(x35984) // b23599
    def fun1 = {
    val b23600 = Const(true).name("b23600").ctrl(x35984) // b23600
    val x35959 = OpDef(op=FixAdd, inputs=List(b23545, b23599)).name("x35959").ctrl(x35984).srcCtx("CellsPar.scala:196:42:reduce_size_offset") // FixAdd(b23545,b23599)
    val x35960 = OpDef(op=BitAnd, inputs=List(b23600, b23595)).name("x35960").ctrl(x35984).srcCtx("UnrollingBase.scala:28:66") // And(b23600,b23595)
    val x35961 = OpDef(op=BitAnd, inputs=List(b23591, b23547)).name("x35961").ctrl(x35984).srcCtx("UnrollingBase.scala:28:66") // And(b23591,b23547)
    val x35962 = OpDef(op=BitAnd, inputs=List(b23548, b22320)).name("x35962").ctrl(x35984).srcCtx("UnrollingBase.scala:28:66") // And(b23548,b22320)
    val x35963 = OpDef(op=BitAnd, inputs=List(x35960, x35961)).name("x35963").ctrl(x35984).srcCtx("UnrollingBase.scala:28:66") // And(x35960,x35961)
    val x35964 = OpDef(op=BitAnd, inputs=List(x35963, x35962)).name("x35964").ctrl(x35984).srcCtx("UnrollingBase.scala:28:66") // And(x35963,x35962)
    val x35965 = LoadBanks(List(x35917_d0_b0), List(b23599, b23594)).name("x35965").ctrl(x35984).srcCtx("CellsPar.scala:203:34:tk") // ParSRAMLoad(x35917,List(List(b23599, b23594)),List(x35964))
    val x35966 = x35965 // x35966 = VectorApply(x35965,0)
    val x35967 = LoadBanks(List(x34684_d2_b0), List(b23590, x35959)).name("x35967").ctrl(x35984).srcCtx("CellsPar.scala:204:28") // ParSRAMLoad(x34684,List(List(b23590, x35959)),List(x35964))
    val x35968 = x35967 // x35968 = VectorApply(x35967,0)
    val x35969 = OpDef(op=FixMul, inputs=List(x35968, x35966)).name("x35969").ctrl(x35984).srcCtx("CellsPar.scala:204:52:tempA") // FixMul(x35968,x35966)
    val x35970 = OpDef(op=FixSub, inputs=List(x35959, Const(512))).name("x35970").ctrl(x35984).srcCtx("CellsPar.scala:205:51") // FixSub(x35959,Const(512))
    val x35971 = LoadBanks(List(x34691_d6_b0), List(b23590, x35970)).name("x35971").ctrl(x35984).srcCtx("CellsPar.scala:205:28") // ParSRAMLoad(x34691,List(List(b23590, x35970)),List(x35964))
    val x35972 = x35971 // x35972 = VectorApply(x35971,0)
    val x35973 = OpDef(op=FixMul, inputs=List(x35972, x35966)).name("x35973").ctrl(x35984).srcCtx("CellsPar.scala:205:67:tempB") // FixMul(x35972,x35966)
    val x35974 = OpDef(op=FixLt, inputs=List(x35959, Const(512))).name("x35974").ctrl(x35984).srcCtx("CellsPar.scala:206:38") // FixLt(x35959,Const(512))
    val x35975 = OpDef(op=MuxOp, inputs=List(x35974, x35969, x35973)).name("x35975").ctrl(x35984).srcCtx("CellsPar.scala:206:18") // Mux(x35974,x35969,x35973)
    val x35976 = ReadMem(x35956_d1).name("x35976").ctrl(x35984).srcCtx("CellsPar.scala:207:15") // RegRead(x35956)
    val x35977 = OpDef(op=FixEql, inputs=List(b23599, Const(0))).name("x35977").ctrl(x35984).srcCtx("CellsPar.scala:207:15") // FixEql(b23599,Const(0))
    val x35978 = ReduceAccumOp(op=FixAdd, input=x35975, accum=x35976).name("x35978").ctrl(x35984).srcCtx("CellsPar.scala:207:17") // FixAdd(x35975,x35976)
    val x35979 = OpDef(op=BitAnd, inputs=List(b23595, b23591)).name("x35979").ctrl(x35984).srcCtx("UnrollingBase.scala:28:66") // And(b23595,b23591)
    val x35980 = OpDef(op=BitAnd, inputs=List(b23547, b23548)).name("x35980").ctrl(x35984).srcCtx("UnrollingBase.scala:28:66") // And(b23547,b23548)
    val x35981 = OpDef(op=BitAnd, inputs=List(x35979, x35980)).name("x35981").ctrl(x35984).srcCtx("UnrollingBase.scala:28:66") // And(x35979,x35980)
    val x35982 = OpDef(op=BitAnd, inputs=List(x35981, b22320)).name("x35982").ctrl(x35984).srcCtx("UnrollingBase.scala:28:66") // And(x35981,b22320)
    val x35983_x35956_d0 = WriteMem(x35956_d0, x35978).name("x35983_x35956_d0").ctrl(x35984).srcCtx("CellsPar.scala:207:15") // RegWrite(x35956,x35978,x35982)
    val x35983_x35956_d1 = WriteMem(x35956_d1, x35978).name("x35983_x35956_d1").ctrl(x35984).srcCtx("CellsPar.scala:207:15") // RegWrite(x35956,x35978,x35982)
    val x36016 = UnitController(style=SeqPipe, level=InnerControl).name("x36016").ctrl(x36017).srcCtx("CellsPar.scala:194:42") // UnitPipe(List(b23595, b23591, b23547, b23548, b22320),Block(Const(())))
    val x35985 = OpDef(op=FixAdd, inputs=List(b23546, b23594)).name("x35985").ctrl(x36016).srcCtx("CellsPar.scala:209:31:colOffset") // FixAdd(b23546,b23594)
    val x35986 = ReadMem(x35956_d0).name("x35986").ctrl(x36016).srcCtx("CellsPar.scala:210:28") // RegRead(x35956)
    val x35987 = OpDef(op=FixEql, inputs=List(b23545, Const(0))).name("x35987").ctrl(x36016).srcCtx("CellsPar.scala:210:42") // FixEql(b23545,Const(0))
    val x35988 = OpDef(op=FixAdd, inputs=List(x35985, Const(512))).name("x35988").ctrl(x36016).srcCtx("CellsPar.scala:210:66") // FixAdd(x35985,Const(512))
    val x35989 = OpDef(op=BitAnd, inputs=List(b23595, b23591)).name("x35989").ctrl(x36016).srcCtx("UnrollingBase.scala:28:66") // And(b23595,b23591)
    val x35990 = OpDef(op=BitAnd, inputs=List(b23547, b23548)).name("x35990").ctrl(x36016).srcCtx("UnrollingBase.scala:28:66") // And(b23547,b23548)
    val x35991 = OpDef(op=BitAnd, inputs=List(x35989, x35990)).name("x35991").ctrl(x36016).srcCtx("UnrollingBase.scala:28:66") // And(x35989,x35990)
    val x35992 = OpDef(op=BitAnd, inputs=List(x35991, b22320)).name("x35992").ctrl(x36016).srcCtx("UnrollingBase.scala:28:66") // And(x35991,b22320)
    val x35993 = LoadBanks(List(x34697_d2_b0), List(Const(0), x35988)).name("x35993").ctrl(x36016).srcCtx("CellsPar.scala:210:52") // SRAMLoad(x34697,ArrayBuffer(Const(1), Const(2048)),List(Const(0), x35988),Const(0),x35992)
    val x35994 = LoadBanks(List(x34694_d1_b0), List(b23590, x35985)).name("x35994").ctrl(x36016).srcCtx("CellsPar.scala:210:91") // SRAMLoad(x34694,ArrayBuffer(Const(1), Const(512)),List(b23590, x35985),Const(0),x35992)
    val x35995 = OpDef(op=MuxOp, inputs=List(x35987, x35993, x35994)).name("x35995").ctrl(x36016).srcCtx("CellsPar.scala:210:39") // Mux(x35987,x35993,x35994)
    val x35996 = OpDef(op=FixAdd, inputs=List(x35986, x35995)).name("x35996").ctrl(x36016).srcCtx("CellsPar.scala:210:34:ele") // FixAdd(x35986,x35995)
    val x35997 = OpDef(op=FixLeq, inputs=List(Const(1520), b23545)).name("x35997").ctrl(x36016).srcCtx("CellsPar.scala:218:46") // FixLeq(Const(1520),b23545)
    // x35998 = FixConvert(Const(0),TRUE,_8,_24) x.tp=FixPt[TRUE,_32,_0] {
    val x35998_int1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("01111111111111111111111111111111"))).name("x35998_int1").ctrl(x36016).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x35998_int2 = OpDef(op=FixSla, inputs=List(x35998_int1, Const(24))).name("x35998_int2").ctrl(x36016).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x35998_frac1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("00000000000000000000000000000000"))).name("x35998_frac1").ctrl(x36016).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x35998_frac2 = OpDef(op=FixSla, inputs=List(x35998_frac1, Const(24))).name("x35998_frac2").ctrl(x36016).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x35998 = OpDef(op=BitOr, inputs=List(x35998_int2, x35998_frac2)).name("x35998").ctrl(x36016).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    // }
    val x35999 = OpDef(op=FixAdd, inputs=List(x35996, x35998)).name("x35999").ctrl(x36016).srcCtx("CellsPar.scala:218:87") // FixAdd(x35996,x35998)
    val x36000 = x35999 // FixConvert(x35999,TRUE,_8,_24) (Same Type. No op)
    val x36001 = OpDef(op=FixAbs, inputs=List(x36000)).name("x36001").ctrl(x36016).srcCtx("CellsPar.scala:37:19:absp") // FixAbs(x36000)
    val x36002 = OpDef(op=FixLt, inputs=List(Const(2.5), x36001)).name("x36002").ctrl(x36016).srcCtx("CellsPar.scala:51:23:cond1") // FixLt(Const(2.5),x36001)
    val x36003 = OpDef(op=FixLt, inputs=List(Const(0.5), x36001)).name("x36003").ctrl(x36016).srcCtx("CellsPar.scala:54:35") // FixLt(Const(0.5),x36001)
    val x36004 = OpDef(op=FixLeq, inputs=List(x36001, Const(2.5))).name("x36004").ctrl(x36016).srcCtx("CellsPar.scala:54:52") // FixLeq(x36001,Const(2.5))
    val x36005 = OpDef(op=BitAnd, inputs=List(x36003, x36004)).name("x36005").ctrl(x36016).srcCtx("CellsPar.scala:54:43:cond2") // And(x36003,x36004)
    val x36006 = OpDef(op=FixSra, inputs=List(x36001, Const(2))).name("x36006").ctrl(x36016).srcCtx("CellsPar.scala:56:21:div4") // FixRsh(x36001,Const(2))
    val x36007 = OpDef(op=FixAdd, inputs=List(x36006, Const(0.375))).name("x36007").ctrl(x36016).srcCtx("CellsPar.scala:57:27:div4Offset") // FixAdd(x36006,Const(0.375))
    val x36008 = OpDef(op=MuxOp, inputs=List(x36005, x36007, x36001)).name("x36008").ctrl(x36016).srcCtx("CellsPar.scala:58:20:body2") // Mux(x36005,x36007,x36001)
    val x36009 = OpDef(op=MuxOp, inputs=List(x36002, Const(1.0), x36008)).name("x36009").ctrl(x36016).srcCtx("CellsPar.scala:60:20:absre") // Mux(x36002,Const(1),x36008)
    val x36010 = OpDef(op=FixNeg, inputs=List(x36009)).name("x36010").ctrl(x36016).srcCtx("CellsPar.scala:62:29:mabre") // FixNeg(x36009)
    val x36011 = OpDef(op=FixLt, inputs=List(x36000, Const(0.0))).name("x36011").ctrl(x36016).srcCtx("CellsPar.scala:63:22") // FixLt(x36000,Const(0))
    val x36012 = OpDef(op=MuxOp, inputs=List(x36011, x36010, x36009)).name("x36012").ctrl(x36016).srcCtx("CellsPar.scala:63:17:re") // Mux(x36011,x36010,x36009)
    val x36013 = x36012 // FixConvert(x36012,TRUE,_8,_24) (Same Type. No op)
    val x36014 = OpDef(op=MuxOp, inputs=List(x35997, x36013, x35996)).name("x36014").ctrl(x36016).srcCtx("CellsPar.scala:218:43") // Mux(x35997,x36013,x35996)
    val x36015 = StoreBanks(List(x34694_d0_b0, x34694_d1_b0), List(b23590, x35985), x36014).name("x36015").ctrl(x36016).srcCtx("CellsPar.scala:218:38") // SRAMStore(x34694,ArrayBuffer(Const(1), Const(512)),List(b23590, x35985),Const(0),x36014,x35992)
    val x36020 = Counter(min=Const(0), max=Const(512), step=Const(16), par=1).name("x36020").ctrl(x36742).srcCtx("CellsPar.scala:190:58") // CounterNew(Const(0),Const(512),Const(16),Const(1))
    val x36021 = Counter(min=Const(0), max=Const(1536), step=Const(16), par=1).name("x36021").ctrl(x36742).srcCtx("CellsPar.scala:190:33") // CounterNew(Const(0),Const(1536),Const(16),Const(1))
    val x36022 = CounterChain(List(x36021,x36020)).name("x36022").ctrl(x36742).srcCtx("CellsPar.scala:190:72") // CounterChainNew(List(x36021, x36020))
    val x36128 = LoopController(style=MetaPipe, level=OuterControl, cchain=x36022).name("x36128").ctrl(x36742).srcCtx("CellsPar.scala:190:72") // UnrolledForeach(List(b22320),x36022,Block(Const(())),List(List(b23665), List(b23666)),List(List(b23667), List(b23668)))
    val b23665 = CounterIter(x36021, Some(0)).name("b23665").ctrl(x36128) // b23665
    val b23667 = Const(true).name("b23667").ctrl(x36128) // b23667
    val b23666 = CounterIter(x36020, Some(0)).name("b23666").ctrl(x36128) // b23666
    val b23668 = Const(true).name("b23668").ctrl(x36128) // b23668
    val x36023_d0_b0 = SRAM(size=256, banking=Strided(banks=1, stride=16)).name("x36023_d0_b0").ctrl(x36128).srcCtx("CellsPar.scala:191:33:tileKernel") // x36023 = SRAMNew(ArrayBuffer(Const(16), Const(16)))
    isAccum(x36023_d0_b0) = false
    bufferDepthOf(x36023_d0_b0) = 2
    val x36026 = UnitController(style=SeqPipe, level=InnerControl).name("x36026").ctrl(x36128).srcCtx("CellsPar.scala:190:72") // UnitPipe(List(b23667, b23668, b22320),Block(Const(())))
    val x36024 = OpDef(op=FixAdd, inputs=List(b23665, Const(16))).name("x36024").ctrl(x36026).srcCtx("CellsPar.scala:192:36") // FixAdd(b23665,Const(16))
    val x36025 = OpDef(op=FixAdd, inputs=List(b23666, Const(16))).name("x36025").ctrl(x36026).srcCtx("CellsPar.scala:192:45") // FixAdd(b23666,Const(16))
    val x36027 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x36027").ctrl(x36128).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x36028 = CounterChain(List(x36027)).name("x36028").ctrl(x36128).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x36027))
    val x36057 = LoopController(style=StreamPipe, level=OuterControl, cchain=x36028).name("x36057").ctrl(x36128).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b23667, b23668, b22320),x36028,Block(Const(())),List(List(b23675)),List(List(b23676)))
    val b23675 = CounterIter(x36027, Some(0)).name("b23675").ctrl(x36057) // b23675
    val b23676 = Const(true).name("b23676").ctrl(x36057) // b23676
    val b36869 = StreamOut(field="offset").name("b36869").ctrl(x36057).srcCtx("CellsPar.scala:192:20") // x36029 = StreamOutNew(BurstCmdBus)
    isAccum(b36869) = false
    bufferDepthOf(b36869) = 1
    val b36870 = StreamOut(field="size").name("b36870").ctrl(x36057).srcCtx("CellsPar.scala:192:20") // x36029 = StreamOutNew(BurstCmdBus)
    isAccum(b36870) = false
    bufferDepthOf(b36870) = 1
    val x36030 = StreamIn(field="data").name("x36030").ctrl(x36057).srcCtx("CellsPar.scala:192:20") // x36030 = StreamInNew(BurstDataBus())
    isAccum(x36030) = false
    bufferDepthOf(x36030) = 1
    val x36045 = UnitController(style=SeqPipe, level=InnerControl).name("x36045").ctrl(x36057).srcCtx("CellsPar.scala:192:20") // UnitPipe(List(b23676, b23667, b23668, b22320),Block(x36044))
    val x36031 = OpDef(op=FixAdd, inputs=List(b23665, b23675)).name("x36031").ctrl(x36045).srcCtx("CellsPar.scala:192:20") // FixAdd(b23665,b23675)
    val x36032 = x36031 // FixConvert(x36031,TRUE,_32,_0) (Same Type. No op)
    val x36033 = OpDef(op=FixSla, inputs=List(x36032, Const(9))).name("x36033").ctrl(x36045).srcCtx("CellsPar.scala:192:20") // FixLsh(x36032,Const(9))
    val x36034 = b23666 // FixConvert(b23666,TRUE,_32,_0) (Same Type. No op)
    val x36035 = OpDef(op=FixAdd, inputs=List(x36033, x36034)).name("x36035").ctrl(x36045).srcCtx("CellsPar.scala:192:20") // FixAdd(x36033,x36034)
    val x36036 = OpDef(op=FixSla, inputs=List(x36035, Const(2))).name("x36036").ctrl(x36045).srcCtx("CellsPar.scala:192:20") // FixLsh(x36035,Const(2))
    val x36037 = x36036 // FixConvert(x36036,TRUE,_64,_0)
    val x36038 = DramAddress(x34554).name("x36038").ctrl(x36045).srcCtx("CellsPar.scala:192:20") // GetDRAMAddress(x34554)
    val x36040_x36039 = OpDef(op=FixAdd, inputs=List(x36037, x36038)).name("x36040_x36039").ctrl(x36045).srcCtx("CellsPar.scala:192:20") // FixAdd(x36037,x36038)
    // x36040 = SimpleStruct(ArrayBuffer((offset,x36039), (size,Const(64)), (isLoad,Const(true))))
    val x36041 = OpDef(op=BitAnd, inputs=List(b23676, b23667)).name("x36041").ctrl(x36045).srcCtx("UnrollingBase.scala:28:66") // And(b23676,b23667)
    val x36042 = OpDef(op=BitAnd, inputs=List(b23668, b22320)).name("x36042").ctrl(x36045).srcCtx("UnrollingBase.scala:28:66") // And(b23668,b22320)
    val x36043 = OpDef(op=BitAnd, inputs=List(x36041, x36042)).name("x36043").ctrl(x36045).srcCtx("UnrollingBase.scala:28:66") // And(x36041,x36042)
    val x36044_b36871_b36869 = WriteMem(b36869, x36040_x36039).name("x36044_b36871_b36869").ctrl(x36045).srcCtx("CellsPar.scala:192:20") // StreamWrite(x36029,x36040,x36043)
    val x36044_b36872_b36870 = WriteMem(b36870, Const(64)).name("x36044_b36872_b36870").ctrl(x36045).srcCtx("CellsPar.scala:192:20") // StreamWrite(x36029,x36040,x36043)
    val x36046 = FringeDenseLoad(dram=List(x34554), cmdStream=List(b36869, b36870), dataStream=List(x36030)).name("x36046").ctrl(x36057).srcCtx("CellsPar.scala:192:20") // FringeDenseLoad(x34554,x36029,x36030)
    val x36047 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x36047").ctrl(x36057).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x36048 = CounterChain(List(x36047)).name("x36048").ctrl(x36057).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x36047))
    val x36056 = LoopController(style=InnerPipe, level=InnerControl, cchain=x36048).name("x36056").ctrl(x36057).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b23676, b23667, b23668, b22320),x36048,Block(Const(())),List(List(b23697)),List(List(b23698)))
    val b23697 = CounterIter(x36047, None).name("b23697").ctrl(x36056) // b23697
    val b23698 = Const(true).name("b23698").ctrl(x36056) // b23698
    val x36049 = OpDef(op=BitAnd, inputs=List(b23698, b23676)).name("x36049").ctrl(x36056).srcCtx("UnrollingBase.scala:28:66") // And(b23698,b23676)
    val x36050 = OpDef(op=BitAnd, inputs=List(b23667, b23668)).name("x36050").ctrl(x36056).srcCtx("UnrollingBase.scala:28:66") // And(b23667,b23668)
    val x36051 = OpDef(op=BitAnd, inputs=List(x36049, x36050)).name("x36051").ctrl(x36056).srcCtx("UnrollingBase.scala:28:66") // And(x36049,x36050)
    val x36052 = OpDef(op=BitAnd, inputs=List(x36051, b22320)).name("x36052").ctrl(x36056).srcCtx("UnrollingBase.scala:28:66") // And(x36051,b22320)
    val x36053_x36053 = ReadMem(x36030).name("x36053_x36053").ctrl(x36056).srcCtx("CellsPar.scala:192:20") // ParStreamRead(x36030,List(x36052))
    val x36054_x36054 = x36053_x36053 // x36054 = VectorApply(x36053,0)
    val x36055 = StoreBanks(List(x36023_d0_b0), List(b23675, b23697), x36054_x36054).name("x36055").ctrl(x36056).srcCtx("CellsPar.scala:192:20") // ParSRAMStore(x36023,List(List(b23675, b23697)),List(x36054),List(x36052))
    val x36058 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x36058").ctrl(x36128).srcCtx("CellsPar.scala:193:34") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x36059 = CounterChain(List(x36058)).name("x36059").ctrl(x36128).srcCtx("CellsPar.scala:193:46") // CounterChainNew(List(x36058))
    val x36127 = LoopController(style=MetaPipe, level=OuterControl, cchain=x36059).name("x36127").ctrl(x36128).srcCtx("CellsPar.scala:193:46") // UnrolledForeach(List(b23667, b23668, b22320),x36059,Block(Const(())),List(List(b23710)),List(List(b23711)))
    val b23710 = CounterIter(x36058, Some(0)).name("b23710").ctrl(x36127) // b23710
    val b23711 = Const(true).name("b23711").ctrl(x36127) // b23711
    val x36060 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x36060").ctrl(x36127).srcCtx("CellsPar.scala:194:28") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x36061 = CounterChain(List(x36060)).name("x36061").ctrl(x36127).srcCtx("CellsPar.scala:194:42") // CounterChainNew(List(x36060))
    val x36126 = LoopController(style=MetaPipe, level=OuterControl, cchain=x36061).name("x36126").ctrl(x36127).srcCtx("CellsPar.scala:194:42") // UnrolledForeach(List(b23711, b23667, b23668, b22320),x36061,Block(Const(())),List(List(b23714)),List(List(b23715)))
    val b23714 = CounterIter(x36060, Some(0)).name("b23714").ctrl(x36126) // b23714
    val b23715 = Const(true).name("b23715").ctrl(x36126) // b23715
    val x36062_d0 = Reg(init=Some(0.0)).name("x36062_d0").ctrl(x36126).srcCtx("CellsPar.scala:195:34:prod") // x36062 = RegNew(Const(0))
    isAccum(x36062_d0) = false
    bufferDepthOf(x36062_d0) = 2
    val x36062_d1 = Reg(init=Some(0.0)).name("x36062_d1").ctrl(x36126).srcCtx("CellsPar.scala:195:34:prod") // x36062 = RegNew(Const(0))
    isAccum(x36062_d1) = true
    bufferDepthOf(x36062_d1) = 1
    val x36063 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x36063").ctrl(x36126).srcCtx("CellsPar.scala:195:43") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x36064 = CounterChain(List(x36063)).name("x36064").ctrl(x36126).srcCtx("CellsPar.scala:207:15") // CounterChainNew(List(x36063))
    val x36090 = LoopController(style=InnerPipe, level=InnerControl, cchain=x36064).name("x36090").ctrl(x36126).srcCtx("CellsPar.scala:207:15") // UnrolledReduce(List(b23715, b23711, b23667, b23668, b22320),x36064,x36062,Block((x36062) => Const(())),List(List(b23719)),List(List(b23720)))
    val b23719 = CounterIter(x36063, None).name("b23719").ctrl(x36090) // b23719
    val b23720 = Const(true).name("b23720").ctrl(x36090) // b23720
    val x36065 = OpDef(op=FixAdd, inputs=List(b23665, b23719)).name("x36065").ctrl(x36090).srcCtx("CellsPar.scala:196:42:reduce_size_offset") // FixAdd(b23665,b23719)
    val x36066 = OpDef(op=BitAnd, inputs=List(b23720, b23715)).name("x36066").ctrl(x36090).srcCtx("UnrollingBase.scala:28:66") // And(b23720,b23715)
    val x36067 = OpDef(op=BitAnd, inputs=List(b23711, b23667)).name("x36067").ctrl(x36090).srcCtx("UnrollingBase.scala:28:66") // And(b23711,b23667)
    val x36068 = OpDef(op=BitAnd, inputs=List(b23668, b22320)).name("x36068").ctrl(x36090).srcCtx("UnrollingBase.scala:28:66") // And(b23668,b22320)
    val x36069 = OpDef(op=BitAnd, inputs=List(x36066, x36067)).name("x36069").ctrl(x36090).srcCtx("UnrollingBase.scala:28:66") // And(x36066,x36067)
    val x36070 = OpDef(op=BitAnd, inputs=List(x36069, x36068)).name("x36070").ctrl(x36090).srcCtx("UnrollingBase.scala:28:66") // And(x36069,x36068)
    val x36071 = LoadBanks(List(x36023_d0_b0), List(b23719, b23714)).name("x36071").ctrl(x36090).srcCtx("CellsPar.scala:203:34:tk") // ParSRAMLoad(x36023,List(List(b23719, b23714)),List(x36070))
    val x36072 = x36071 // x36072 = VectorApply(x36071,0)
    val x36073 = LoadBanks(List(x34684_d1_b0), List(b23710, x36065)).name("x36073").ctrl(x36090).srcCtx("CellsPar.scala:204:28") // ParSRAMLoad(x34684,List(List(b23710, x36065)),List(x36070))
    val x36074 = x36073 // x36074 = VectorApply(x36073,0)
    val x36075 = OpDef(op=FixMul, inputs=List(x36074, x36072)).name("x36075").ctrl(x36090).srcCtx("CellsPar.scala:204:52:tempA") // FixMul(x36074,x36072)
    val x36076 = OpDef(op=FixSub, inputs=List(x36065, Const(512))).name("x36076").ctrl(x36090).srcCtx("CellsPar.scala:205:51") // FixSub(x36065,Const(512))
    val x36077 = LoadBanks(List(x34691_d5_b0), List(b23710, x36076)).name("x36077").ctrl(x36090).srcCtx("CellsPar.scala:205:28") // ParSRAMLoad(x34691,List(List(b23710, x36076)),List(x36070))
    val x36078 = x36077 // x36078 = VectorApply(x36077,0)
    val x36079 = OpDef(op=FixMul, inputs=List(x36078, x36072)).name("x36079").ctrl(x36090).srcCtx("CellsPar.scala:205:67:tempB") // FixMul(x36078,x36072)
    val x36080 = OpDef(op=FixLt, inputs=List(x36065, Const(512))).name("x36080").ctrl(x36090).srcCtx("CellsPar.scala:206:38") // FixLt(x36065,Const(512))
    val x36081 = OpDef(op=MuxOp, inputs=List(x36080, x36075, x36079)).name("x36081").ctrl(x36090).srcCtx("CellsPar.scala:206:18") // Mux(x36080,x36075,x36079)
    val x36082 = ReadMem(x36062_d1).name("x36082").ctrl(x36090).srcCtx("CellsPar.scala:207:15") // RegRead(x36062)
    val x36083 = OpDef(op=FixEql, inputs=List(b23719, Const(0))).name("x36083").ctrl(x36090).srcCtx("CellsPar.scala:207:15") // FixEql(b23719,Const(0))
    val x36084 = ReduceAccumOp(op=FixAdd, input=x36081, accum=x36082).name("x36084").ctrl(x36090).srcCtx("CellsPar.scala:207:17") // FixAdd(x36081,x36082)
    val x36085 = OpDef(op=BitAnd, inputs=List(b23715, b23711)).name("x36085").ctrl(x36090).srcCtx("UnrollingBase.scala:28:66") // And(b23715,b23711)
    val x36086 = OpDef(op=BitAnd, inputs=List(b23667, b23668)).name("x36086").ctrl(x36090).srcCtx("UnrollingBase.scala:28:66") // And(b23667,b23668)
    val x36087 = OpDef(op=BitAnd, inputs=List(x36085, x36086)).name("x36087").ctrl(x36090).srcCtx("UnrollingBase.scala:28:66") // And(x36085,x36086)
    val x36088 = OpDef(op=BitAnd, inputs=List(x36087, b22320)).name("x36088").ctrl(x36090).srcCtx("UnrollingBase.scala:28:66") // And(x36087,b22320)
    val x36089_x36062_d0 = WriteMem(x36062_d0, x36084).name("x36089_x36062_d0").ctrl(x36090).srcCtx("CellsPar.scala:207:15") // RegWrite(x36062,x36084,x36088)
    val x36089_x36062_d1 = WriteMem(x36062_d1, x36084).name("x36089_x36062_d1").ctrl(x36090).srcCtx("CellsPar.scala:207:15") // RegWrite(x36062,x36084,x36088)
    val x36125 = UnitController(style=SeqPipe, level=InnerControl).name("x36125").ctrl(x36126).srcCtx("CellsPar.scala:194:42") // UnitPipe(List(b23715, b23711, b23667, b23668, b22320),Block(Const(())))
    val x36091 = OpDef(op=FixAdd, inputs=List(b23666, b23714)).name("x36091").ctrl(x36125).srcCtx("CellsPar.scala:209:31:colOffset") // FixAdd(b23666,b23714)
    val x36092 = ReadMem(x36062_d0).name("x36092").ctrl(x36125).srcCtx("CellsPar.scala:210:28") // RegRead(x36062)
    val x36093 = OpDef(op=FixEql, inputs=List(b23665, Const(0))).name("x36093").ctrl(x36125).srcCtx("CellsPar.scala:210:42") // FixEql(b23665,Const(0))
    val x36094 = OpDef(op=FixAdd, inputs=List(x36091, Const(1024))).name("x36094").ctrl(x36125).srcCtx("CellsPar.scala:210:66") // FixAdd(x36091,Const(1024))
    val x36095 = OpDef(op=BitAnd, inputs=List(b23715, b23711)).name("x36095").ctrl(x36125).srcCtx("UnrollingBase.scala:28:66") // And(b23715,b23711)
    val x36096 = OpDef(op=BitAnd, inputs=List(b23667, b23668)).name("x36096").ctrl(x36125).srcCtx("UnrollingBase.scala:28:66") // And(b23667,b23668)
    val x36097 = OpDef(op=BitAnd, inputs=List(x36095, x36096)).name("x36097").ctrl(x36125).srcCtx("UnrollingBase.scala:28:66") // And(x36095,x36096)
    val x36098 = OpDef(op=BitAnd, inputs=List(x36097, b22320)).name("x36098").ctrl(x36125).srcCtx("UnrollingBase.scala:28:66") // And(x36097,b22320)
    val x36099 = LoadBanks(List(x34697_d1_b0), List(Const(0), x36094)).name("x36099").ctrl(x36125).srcCtx("CellsPar.scala:210:52") // SRAMLoad(x34697,ArrayBuffer(Const(1), Const(2048)),List(Const(0), x36094),Const(0),x36098)
    val x36100 = LoadBanks(List(x34695_d1_b0), List(b23710, x36091)).name("x36100").ctrl(x36125).srcCtx("CellsPar.scala:210:91") // SRAMLoad(x34695,ArrayBuffer(Const(1), Const(512)),List(b23710, x36091),Const(0),x36098)
    val x36101 = OpDef(op=MuxOp, inputs=List(x36093, x36099, x36100)).name("x36101").ctrl(x36125).srcCtx("CellsPar.scala:210:39") // Mux(x36093,x36099,x36100)
    val x36102 = OpDef(op=FixAdd, inputs=List(x36092, x36101)).name("x36102").ctrl(x36125).srcCtx("CellsPar.scala:210:34:ele") // FixAdd(x36092,x36101)
    val x36103 = OpDef(op=FixLeq, inputs=List(Const(1520), b23665)).name("x36103").ctrl(x36125).srcCtx("CellsPar.scala:218:46") // FixLeq(Const(1520),b23665)
    // x36104 = FixConvert(Const(1),TRUE,_8,_24) x.tp=FixPt[TRUE,_32,_0] {
    val x36104_int1 = OpDef(op=BitAnd, inputs=List(Const(1), Const("01111111111111111111111111111111"))).name("x36104_int1").ctrl(x36125).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(1),TRUE,_8,_24)
    val x36104_int2 = OpDef(op=FixSla, inputs=List(x36104_int1, Const(24))).name("x36104_int2").ctrl(x36125).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(1),TRUE,_8,_24)
    val x36104_frac1 = OpDef(op=BitAnd, inputs=List(Const(1), Const("00000000000000000000000000000000"))).name("x36104_frac1").ctrl(x36125).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(1),TRUE,_8,_24)
    val x36104_frac2 = OpDef(op=FixSla, inputs=List(x36104_frac1, Const(24))).name("x36104_frac2").ctrl(x36125).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(1),TRUE,_8,_24)
    val x36104 = OpDef(op=BitOr, inputs=List(x36104_int2, x36104_frac2)).name("x36104").ctrl(x36125).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(1),TRUE,_8,_24)
    // }
    val x36105 = OpDef(op=FixAdd, inputs=List(x36102, x36104)).name("x36105").ctrl(x36125).srcCtx("CellsPar.scala:218:87") // FixAdd(x36102,x36104)
    val x36106 = OpDef(op=FixSra, inputs=List(x36105, Const(1))).name("x36106").ctrl(x36125).srcCtx("CellsPar.scala:29:22") // FixRsh(x36105,Const(1))
    val x36107 = x36106 // FixConvert(x36106,TRUE,_8,_24) (Same Type. No op)
    val x36108 = OpDef(op=FixAbs, inputs=List(x36107)).name("x36108").ctrl(x36125).srcCtx("CellsPar.scala:37:19:absp") // FixAbs(x36107)
    val x36109 = OpDef(op=FixLt, inputs=List(Const(2.5), x36108)).name("x36109").ctrl(x36125).srcCtx("CellsPar.scala:51:23:cond1") // FixLt(Const(2.5),x36108)
    val x36110 = OpDef(op=FixLt, inputs=List(Const(0.5), x36108)).name("x36110").ctrl(x36125).srcCtx("CellsPar.scala:54:35") // FixLt(Const(0.5),x36108)
    val x36111 = OpDef(op=FixLeq, inputs=List(x36108, Const(2.5))).name("x36111").ctrl(x36125).srcCtx("CellsPar.scala:54:52") // FixLeq(x36108,Const(2.5))
    val x36112 = OpDef(op=BitAnd, inputs=List(x36110, x36111)).name("x36112").ctrl(x36125).srcCtx("CellsPar.scala:54:43:cond2") // And(x36110,x36111)
    val x36113 = OpDef(op=FixSra, inputs=List(x36108, Const(2))).name("x36113").ctrl(x36125).srcCtx("CellsPar.scala:56:21:div4") // FixRsh(x36108,Const(2))
    val x36114 = OpDef(op=FixAdd, inputs=List(x36113, Const(0.375))).name("x36114").ctrl(x36125).srcCtx("CellsPar.scala:57:27:div4Offset") // FixAdd(x36113,Const(0.375))
    val x36115 = OpDef(op=MuxOp, inputs=List(x36112, x36114, x36108)).name("x36115").ctrl(x36125).srcCtx("CellsPar.scala:58:20:body2") // Mux(x36112,x36114,x36108)
    val x36116 = OpDef(op=MuxOp, inputs=List(x36109, Const(1.0), x36115)).name("x36116").ctrl(x36125).srcCtx("CellsPar.scala:60:20:absre") // Mux(x36109,Const(1),x36115)
    val x36117 = OpDef(op=FixNeg, inputs=List(x36116)).name("x36117").ctrl(x36125).srcCtx("CellsPar.scala:62:29:mabre") // FixNeg(x36116)
    val x36118 = OpDef(op=FixLt, inputs=List(x36107, Const(0.0))).name("x36118").ctrl(x36125).srcCtx("CellsPar.scala:63:22") // FixLt(x36107,Const(0))
    val x36119 = OpDef(op=MuxOp, inputs=List(x36118, x36117, x36116)).name("x36119").ctrl(x36125).srcCtx("CellsPar.scala:63:17:re") // Mux(x36118,x36117,x36116)
    val x36120 = x36119 // FixConvert(x36119,TRUE,_8,_24) (Same Type. No op)
    val x36121 = OpDef(op=FixAdd, inputs=List(x36120, Const(1.0))).name("x36121").ctrl(x36125).srcCtx("CellsPar.scala:29:33") // FixAdd(x36120,Const(1))
    val x36122 = OpDef(op=FixSra, inputs=List(x36121, Const(1))).name("x36122").ctrl(x36125).srcCtx("CellsPar.scala:29:44") // FixRsh(x36121,Const(1))
    val x36123 = OpDef(op=MuxOp, inputs=List(x36103, x36122, x36102)).name("x36123").ctrl(x36125).srcCtx("CellsPar.scala:218:43") // Mux(x36103,x36122,x36102)
    val x36124 = StoreBanks(List(x34695_d0_b0, x34695_d1_b0), List(b23710, x36091), x36123).name("x36124").ctrl(x36125).srcCtx("CellsPar.scala:218:38") // SRAMStore(x34695,ArrayBuffer(Const(1), Const(512)),List(b23710, x36091),Const(0),x36123,x36098)
    val x36129 = Counter(min=Const(0), max=Const(512), step=Const(16), par=1).name("x36129").ctrl(x36742).srcCtx("CellsPar.scala:190:58") // CounterNew(Const(0),Const(512),Const(16),Const(1))
    val x36130 = Counter(min=Const(0), max=Const(1536), step=Const(16), par=1).name("x36130").ctrl(x36742).srcCtx("CellsPar.scala:190:33") // CounterNew(Const(0),Const(1536),Const(16),Const(1))
    val x36131 = CounterChain(List(x36130,x36129)).name("x36131").ctrl(x36742).srcCtx("CellsPar.scala:190:72") // CounterChainNew(List(x36130, x36129))
    val x36237 = LoopController(style=MetaPipe, level=OuterControl, cchain=x36131).name("x36237").ctrl(x36742).srcCtx("CellsPar.scala:190:72") // UnrolledForeach(List(b22320),x36131,Block(Const(())),List(List(b23788), List(b23789)),List(List(b23790), List(b23791)))
    val b23788 = CounterIter(x36130, Some(0)).name("b23788").ctrl(x36237) // b23788
    val b23790 = Const(true).name("b23790").ctrl(x36237) // b23790
    val b23789 = CounterIter(x36129, Some(0)).name("b23789").ctrl(x36237) // b23789
    val b23791 = Const(true).name("b23791").ctrl(x36237) // b23791
    val x36132_d0_b0 = SRAM(size=256, banking=Strided(banks=1, stride=16)).name("x36132_d0_b0").ctrl(x36237).srcCtx("CellsPar.scala:191:33:tileKernel") // x36132 = SRAMNew(ArrayBuffer(Const(16), Const(16)))
    isAccum(x36132_d0_b0) = false
    bufferDepthOf(x36132_d0_b0) = 2
    val x36135 = UnitController(style=SeqPipe, level=InnerControl).name("x36135").ctrl(x36237).srcCtx("CellsPar.scala:190:72") // UnitPipe(List(b23790, b23791, b22320),Block(Const(())))
    val x36133 = OpDef(op=FixAdd, inputs=List(b23788, Const(16))).name("x36133").ctrl(x36135).srcCtx("CellsPar.scala:192:36") // FixAdd(b23788,Const(16))
    val x36134 = OpDef(op=FixAdd, inputs=List(b23789, Const(16))).name("x36134").ctrl(x36135).srcCtx("CellsPar.scala:192:45") // FixAdd(b23789,Const(16))
    val x36136 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x36136").ctrl(x36237).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x36137 = CounterChain(List(x36136)).name("x36137").ctrl(x36237).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x36136))
    val x36166 = LoopController(style=StreamPipe, level=OuterControl, cchain=x36137).name("x36166").ctrl(x36237).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b23790, b23791, b22320),x36137,Block(Const(())),List(List(b23798)),List(List(b23799)))
    val b23798 = CounterIter(x36136, Some(0)).name("b23798").ctrl(x36166) // b23798
    val b23799 = Const(true).name("b23799").ctrl(x36166) // b23799
    val b36873 = StreamOut(field="offset").name("b36873").ctrl(x36166).srcCtx("CellsPar.scala:192:20") // x36138 = StreamOutNew(BurstCmdBus)
    isAccum(b36873) = false
    bufferDepthOf(b36873) = 1
    val b36874 = StreamOut(field="size").name("b36874").ctrl(x36166).srcCtx("CellsPar.scala:192:20") // x36138 = StreamOutNew(BurstCmdBus)
    isAccum(b36874) = false
    bufferDepthOf(b36874) = 1
    val x36139 = StreamIn(field="data").name("x36139").ctrl(x36166).srcCtx("CellsPar.scala:192:20") // x36139 = StreamInNew(BurstDataBus())
    isAccum(x36139) = false
    bufferDepthOf(x36139) = 1
    val x36154 = UnitController(style=SeqPipe, level=InnerControl).name("x36154").ctrl(x36166).srcCtx("CellsPar.scala:192:20") // UnitPipe(List(b23799, b23790, b23791, b22320),Block(x36153))
    val x36140 = OpDef(op=FixAdd, inputs=List(b23788, b23798)).name("x36140").ctrl(x36154).srcCtx("CellsPar.scala:192:20") // FixAdd(b23788,b23798)
    val x36141 = x36140 // FixConvert(x36140,TRUE,_32,_0) (Same Type. No op)
    val x36142 = OpDef(op=FixSla, inputs=List(x36141, Const(9))).name("x36142").ctrl(x36154).srcCtx("CellsPar.scala:192:20") // FixLsh(x36141,Const(9))
    val x36143 = b23789 // FixConvert(b23789,TRUE,_32,_0) (Same Type. No op)
    val x36144 = OpDef(op=FixAdd, inputs=List(x36142, x36143)).name("x36144").ctrl(x36154).srcCtx("CellsPar.scala:192:20") // FixAdd(x36142,x36143)
    val x36145 = OpDef(op=FixSla, inputs=List(x36144, Const(2))).name("x36145").ctrl(x36154).srcCtx("CellsPar.scala:192:20") // FixLsh(x36144,Const(2))
    val x36146 = x36145 // FixConvert(x36145,TRUE,_64,_0)
    val x36147 = DramAddress(x34555).name("x36147").ctrl(x36154).srcCtx("CellsPar.scala:192:20") // GetDRAMAddress(x34555)
    val x36149_x36148 = OpDef(op=FixAdd, inputs=List(x36146, x36147)).name("x36149_x36148").ctrl(x36154).srcCtx("CellsPar.scala:192:20") // FixAdd(x36146,x36147)
    // x36149 = SimpleStruct(ArrayBuffer((offset,x36148), (size,Const(64)), (isLoad,Const(true))))
    val x36150 = OpDef(op=BitAnd, inputs=List(b23799, b23790)).name("x36150").ctrl(x36154).srcCtx("UnrollingBase.scala:28:66") // And(b23799,b23790)
    val x36151 = OpDef(op=BitAnd, inputs=List(b23791, b22320)).name("x36151").ctrl(x36154).srcCtx("UnrollingBase.scala:28:66") // And(b23791,b22320)
    val x36152 = OpDef(op=BitAnd, inputs=List(x36150, x36151)).name("x36152").ctrl(x36154).srcCtx("UnrollingBase.scala:28:66") // And(x36150,x36151)
    val x36153_b36875_b36873 = WriteMem(b36873, x36149_x36148).name("x36153_b36875_b36873").ctrl(x36154).srcCtx("CellsPar.scala:192:20") // StreamWrite(x36138,x36149,x36152)
    val x36153_b36876_b36874 = WriteMem(b36874, Const(64)).name("x36153_b36876_b36874").ctrl(x36154).srcCtx("CellsPar.scala:192:20") // StreamWrite(x36138,x36149,x36152)
    val x36155 = FringeDenseLoad(dram=List(x34555), cmdStream=List(b36873, b36874), dataStream=List(x36139)).name("x36155").ctrl(x36166).srcCtx("CellsPar.scala:192:20") // FringeDenseLoad(x34555,x36138,x36139)
    val x36156 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x36156").ctrl(x36166).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x36157 = CounterChain(List(x36156)).name("x36157").ctrl(x36166).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x36156))
    val x36165 = LoopController(style=InnerPipe, level=InnerControl, cchain=x36157).name("x36165").ctrl(x36166).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b23799, b23790, b23791, b22320),x36157,Block(Const(())),List(List(b23820)),List(List(b23821)))
    val b23820 = CounterIter(x36156, None).name("b23820").ctrl(x36165) // b23820
    val b23821 = Const(true).name("b23821").ctrl(x36165) // b23821
    val x36158 = OpDef(op=BitAnd, inputs=List(b23821, b23799)).name("x36158").ctrl(x36165).srcCtx("UnrollingBase.scala:28:66") // And(b23821,b23799)
    val x36159 = OpDef(op=BitAnd, inputs=List(b23790, b23791)).name("x36159").ctrl(x36165).srcCtx("UnrollingBase.scala:28:66") // And(b23790,b23791)
    val x36160 = OpDef(op=BitAnd, inputs=List(x36158, x36159)).name("x36160").ctrl(x36165).srcCtx("UnrollingBase.scala:28:66") // And(x36158,x36159)
    val x36161 = OpDef(op=BitAnd, inputs=List(x36160, b22320)).name("x36161").ctrl(x36165).srcCtx("UnrollingBase.scala:28:66") // And(x36160,b22320)
    val x36162_x36162 = ReadMem(x36139).name("x36162_x36162").ctrl(x36165).srcCtx("CellsPar.scala:192:20") // ParStreamRead(x36139,List(x36161))
    val x36163_x36163 = x36162_x36162 // x36163 = VectorApply(x36162,0)
    val x36164 = StoreBanks(List(x36132_d0_b0), List(b23798, b23820), x36163_x36163).name("x36164").ctrl(x36165).srcCtx("CellsPar.scala:192:20") // ParSRAMStore(x36132,List(List(b23798, b23820)),List(x36163),List(x36161))
    val x36167 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x36167").ctrl(x36237).srcCtx("CellsPar.scala:193:34") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x36168 = CounterChain(List(x36167)).name("x36168").ctrl(x36237).srcCtx("CellsPar.scala:193:46") // CounterChainNew(List(x36167))
    val x36236 = LoopController(style=MetaPipe, level=OuterControl, cchain=x36168).name("x36236").ctrl(x36237).srcCtx("CellsPar.scala:193:46") // UnrolledForeach(List(b23790, b23791, b22320),x36168,Block(Const(())),List(List(b23833)),List(List(b23834)))
    val b23833 = CounterIter(x36167, Some(0)).name("b23833").ctrl(x36236) // b23833
    val b23834 = Const(true).name("b23834").ctrl(x36236) // b23834
    val x36169 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x36169").ctrl(x36236).srcCtx("CellsPar.scala:194:28") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x36170 = CounterChain(List(x36169)).name("x36170").ctrl(x36236).srcCtx("CellsPar.scala:194:42") // CounterChainNew(List(x36169))
    val x36235 = LoopController(style=MetaPipe, level=OuterControl, cchain=x36170).name("x36235").ctrl(x36236).srcCtx("CellsPar.scala:194:42") // UnrolledForeach(List(b23834, b23790, b23791, b22320),x36170,Block(Const(())),List(List(b23837)),List(List(b23838)))
    val b23837 = CounterIter(x36169, Some(0)).name("b23837").ctrl(x36235) // b23837
    val b23838 = Const(true).name("b23838").ctrl(x36235) // b23838
    val x36171_d0 = Reg(init=Some(0.0)).name("x36171_d0").ctrl(x36235).srcCtx("CellsPar.scala:195:34:prod") // x36171 = RegNew(Const(0))
    isAccum(x36171_d0) = false
    bufferDepthOf(x36171_d0) = 2
    val x36171_d1 = Reg(init=Some(0.0)).name("x36171_d1").ctrl(x36235).srcCtx("CellsPar.scala:195:34:prod") // x36171 = RegNew(Const(0))
    isAccum(x36171_d1) = true
    bufferDepthOf(x36171_d1) = 1
    val x36172 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x36172").ctrl(x36235).srcCtx("CellsPar.scala:195:43") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x36173 = CounterChain(List(x36172)).name("x36173").ctrl(x36235).srcCtx("CellsPar.scala:207:15") // CounterChainNew(List(x36172))
    val x36199 = LoopController(style=InnerPipe, level=InnerControl, cchain=x36173).name("x36199").ctrl(x36235).srcCtx("CellsPar.scala:207:15") // UnrolledReduce(List(b23838, b23834, b23790, b23791, b22320),x36173,x36171,Block((x36171) => Const(())),List(List(b23842)),List(List(b23843)))
    val b23842 = CounterIter(x36172, None).name("b23842").ctrl(x36199) // b23842
    val b23843 = Const(true).name("b23843").ctrl(x36199) // b23843
    val x36174 = OpDef(op=FixAdd, inputs=List(b23788, b23842)).name("x36174").ctrl(x36199).srcCtx("CellsPar.scala:196:42:reduce_size_offset") // FixAdd(b23788,b23842)
    val x36175 = OpDef(op=BitAnd, inputs=List(b23843, b23838)).name("x36175").ctrl(x36199).srcCtx("UnrollingBase.scala:28:66") // And(b23843,b23838)
    val x36176 = OpDef(op=BitAnd, inputs=List(b23834, b23790)).name("x36176").ctrl(x36199).srcCtx("UnrollingBase.scala:28:66") // And(b23834,b23790)
    val x36177 = OpDef(op=BitAnd, inputs=List(b23791, b22320)).name("x36177").ctrl(x36199).srcCtx("UnrollingBase.scala:28:66") // And(b23791,b22320)
    val x36178 = OpDef(op=BitAnd, inputs=List(x36175, x36176)).name("x36178").ctrl(x36199).srcCtx("UnrollingBase.scala:28:66") // And(x36175,x36176)
    val x36179 = OpDef(op=BitAnd, inputs=List(x36178, x36177)).name("x36179").ctrl(x36199).srcCtx("UnrollingBase.scala:28:66") // And(x36178,x36177)
    val x36180 = LoadBanks(List(x36132_d0_b0), List(b23842, b23837)).name("x36180").ctrl(x36199).srcCtx("CellsPar.scala:203:34:tk") // ParSRAMLoad(x36132,List(List(b23842, b23837)),List(x36179))
    val x36181 = x36180 // x36181 = VectorApply(x36180,0)
    val x36182 = LoadBanks(List(x34684_d0_b0), List(b23833, x36174)).name("x36182").ctrl(x36199).srcCtx("CellsPar.scala:204:28") // ParSRAMLoad(x34684,List(List(b23833, x36174)),List(x36179))
    val x36183 = x36182 // x36183 = VectorApply(x36182,0)
    val x36184 = OpDef(op=FixMul, inputs=List(x36183, x36181)).name("x36184").ctrl(x36199).srcCtx("CellsPar.scala:204:52:tempA") // FixMul(x36183,x36181)
    val x36185 = OpDef(op=FixSub, inputs=List(x36174, Const(512))).name("x36185").ctrl(x36199).srcCtx("CellsPar.scala:205:51") // FixSub(x36174,Const(512))
    val x36186 = LoadBanks(List(x34691_d4_b0), List(b23833, x36185)).name("x36186").ctrl(x36199).srcCtx("CellsPar.scala:205:28") // ParSRAMLoad(x34691,List(List(b23833, x36185)),List(x36179))
    val x36187 = x36186 // x36187 = VectorApply(x36186,0)
    val x36188 = OpDef(op=FixMul, inputs=List(x36187, x36181)).name("x36188").ctrl(x36199).srcCtx("CellsPar.scala:205:67:tempB") // FixMul(x36187,x36181)
    val x36189 = OpDef(op=FixLt, inputs=List(x36174, Const(512))).name("x36189").ctrl(x36199).srcCtx("CellsPar.scala:206:38") // FixLt(x36174,Const(512))
    val x36190 = OpDef(op=MuxOp, inputs=List(x36189, x36184, x36188)).name("x36190").ctrl(x36199).srcCtx("CellsPar.scala:206:18") // Mux(x36189,x36184,x36188)
    val x36191 = ReadMem(x36171_d1).name("x36191").ctrl(x36199).srcCtx("CellsPar.scala:207:15") // RegRead(x36171)
    val x36192 = OpDef(op=FixEql, inputs=List(b23842, Const(0))).name("x36192").ctrl(x36199).srcCtx("CellsPar.scala:207:15") // FixEql(b23842,Const(0))
    val x36193 = ReduceAccumOp(op=FixAdd, input=x36190, accum=x36191).name("x36193").ctrl(x36199).srcCtx("CellsPar.scala:207:17") // FixAdd(x36190,x36191)
    val x36194 = OpDef(op=BitAnd, inputs=List(b23838, b23834)).name("x36194").ctrl(x36199).srcCtx("UnrollingBase.scala:28:66") // And(b23838,b23834)
    val x36195 = OpDef(op=BitAnd, inputs=List(b23790, b23791)).name("x36195").ctrl(x36199).srcCtx("UnrollingBase.scala:28:66") // And(b23790,b23791)
    val x36196 = OpDef(op=BitAnd, inputs=List(x36194, x36195)).name("x36196").ctrl(x36199).srcCtx("UnrollingBase.scala:28:66") // And(x36194,x36195)
    val x36197 = OpDef(op=BitAnd, inputs=List(x36196, b22320)).name("x36197").ctrl(x36199).srcCtx("UnrollingBase.scala:28:66") // And(x36196,b22320)
    val x36198_x36171_d0 = WriteMem(x36171_d0, x36193).name("x36198_x36171_d0").ctrl(x36199).srcCtx("CellsPar.scala:207:15") // RegWrite(x36171,x36193,x36197)
    val x36198_x36171_d1 = WriteMem(x36171_d1, x36193).name("x36198_x36171_d1").ctrl(x36199).srcCtx("CellsPar.scala:207:15") // RegWrite(x36171,x36193,x36197)
    val x36234 = UnitController(style=SeqPipe, level=InnerControl).name("x36234").ctrl(x36235).srcCtx("CellsPar.scala:194:42") // UnitPipe(List(b23838, b23834, b23790, b23791, b22320),Block(Const(())))
    val x36200 = OpDef(op=FixAdd, inputs=List(b23789, b23837)).name("x36200").ctrl(x36234).srcCtx("CellsPar.scala:209:31:colOffset") // FixAdd(b23789,b23837)
    val x36201 = ReadMem(x36171_d0).name("x36201").ctrl(x36234).srcCtx("CellsPar.scala:210:28") // RegRead(x36171)
    val x36202 = OpDef(op=FixEql, inputs=List(b23788, Const(0))).name("x36202").ctrl(x36234).srcCtx("CellsPar.scala:210:42") // FixEql(b23788,Const(0))
    val x36203 = OpDef(op=FixAdd, inputs=List(x36200, Const(1536))).name("x36203").ctrl(x36234).srcCtx("CellsPar.scala:210:66") // FixAdd(x36200,Const(1536))
    val x36204 = OpDef(op=BitAnd, inputs=List(b23838, b23834)).name("x36204").ctrl(x36234).srcCtx("UnrollingBase.scala:28:66") // And(b23838,b23834)
    val x36205 = OpDef(op=BitAnd, inputs=List(b23790, b23791)).name("x36205").ctrl(x36234).srcCtx("UnrollingBase.scala:28:66") // And(b23790,b23791)
    val x36206 = OpDef(op=BitAnd, inputs=List(x36204, x36205)).name("x36206").ctrl(x36234).srcCtx("UnrollingBase.scala:28:66") // And(x36204,x36205)
    val x36207 = OpDef(op=BitAnd, inputs=List(x36206, b22320)).name("x36207").ctrl(x36234).srcCtx("UnrollingBase.scala:28:66") // And(x36206,b22320)
    val x36208 = LoadBanks(List(x34697_d0_b0), List(Const(0), x36203)).name("x36208").ctrl(x36234).srcCtx("CellsPar.scala:210:52") // SRAMLoad(x34697,ArrayBuffer(Const(1), Const(2048)),List(Const(0), x36203),Const(0),x36207)
    val x36209 = LoadBanks(List(x34696_d1_b0), List(b23833, x36200)).name("x36209").ctrl(x36234).srcCtx("CellsPar.scala:210:91") // SRAMLoad(x34696,ArrayBuffer(Const(1), Const(512)),List(b23833, x36200),Const(0),x36207)
    val x36210 = OpDef(op=MuxOp, inputs=List(x36202, x36208, x36209)).name("x36210").ctrl(x36234).srcCtx("CellsPar.scala:210:39") // Mux(x36202,x36208,x36209)
    val x36211 = OpDef(op=FixAdd, inputs=List(x36201, x36210)).name("x36211").ctrl(x36234).srcCtx("CellsPar.scala:210:34:ele") // FixAdd(x36201,x36210)
    val x36212 = OpDef(op=FixLeq, inputs=List(Const(1520), b23788)).name("x36212").ctrl(x36234).srcCtx("CellsPar.scala:218:46") // FixLeq(Const(1520),b23788)
    // x36213 = FixConvert(Const(0),TRUE,_8,_24) x.tp=FixPt[TRUE,_32,_0] {
    val x36213_int1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("01111111111111111111111111111111"))).name("x36213_int1").ctrl(x36234).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x36213_int2 = OpDef(op=FixSla, inputs=List(x36213_int1, Const(24))).name("x36213_int2").ctrl(x36234).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x36213_frac1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("00000000000000000000000000000000"))).name("x36213_frac1").ctrl(x36234).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x36213_frac2 = OpDef(op=FixSla, inputs=List(x36213_frac1, Const(24))).name("x36213_frac2").ctrl(x36234).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x36213 = OpDef(op=BitOr, inputs=List(x36213_int2, x36213_frac2)).name("x36213").ctrl(x36234).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    // }
    val x36214 = OpDef(op=FixAdd, inputs=List(x36211, x36213)).name("x36214").ctrl(x36234).srcCtx("CellsPar.scala:218:87") // FixAdd(x36211,x36213)
    val x36215 = OpDef(op=FixSra, inputs=List(x36214, Const(1))).name("x36215").ctrl(x36234).srcCtx("CellsPar.scala:29:22") // FixRsh(x36214,Const(1))
    val x36216 = x36215 // FixConvert(x36215,TRUE,_8,_24) (Same Type. No op)
    val x36217 = OpDef(op=FixAbs, inputs=List(x36216)).name("x36217").ctrl(x36234).srcCtx("CellsPar.scala:37:19:absp") // FixAbs(x36216)
    val x36218 = OpDef(op=FixLt, inputs=List(Const(2.5), x36217)).name("x36218").ctrl(x36234).srcCtx("CellsPar.scala:51:23:cond1") // FixLt(Const(2.5),x36217)
    val x36219 = OpDef(op=FixLt, inputs=List(Const(0.5), x36217)).name("x36219").ctrl(x36234).srcCtx("CellsPar.scala:54:35") // FixLt(Const(0.5),x36217)
    val x36220 = OpDef(op=FixLeq, inputs=List(x36217, Const(2.5))).name("x36220").ctrl(x36234).srcCtx("CellsPar.scala:54:52") // FixLeq(x36217,Const(2.5))
    val x36221 = OpDef(op=BitAnd, inputs=List(x36219, x36220)).name("x36221").ctrl(x36234).srcCtx("CellsPar.scala:54:43:cond2") // And(x36219,x36220)
    val x36222 = OpDef(op=FixSra, inputs=List(x36217, Const(2))).name("x36222").ctrl(x36234).srcCtx("CellsPar.scala:56:21:div4") // FixRsh(x36217,Const(2))
    val x36223 = OpDef(op=FixAdd, inputs=List(x36222, Const(0.375))).name("x36223").ctrl(x36234).srcCtx("CellsPar.scala:57:27:div4Offset") // FixAdd(x36222,Const(0.375))
    val x36224 = OpDef(op=MuxOp, inputs=List(x36221, x36223, x36217)).name("x36224").ctrl(x36234).srcCtx("CellsPar.scala:58:20:body2") // Mux(x36221,x36223,x36217)
    val x36225 = OpDef(op=MuxOp, inputs=List(x36218, Const(1.0), x36224)).name("x36225").ctrl(x36234).srcCtx("CellsPar.scala:60:20:absre") // Mux(x36218,Const(1),x36224)
    val x36226 = OpDef(op=FixNeg, inputs=List(x36225)).name("x36226").ctrl(x36234).srcCtx("CellsPar.scala:62:29:mabre") // FixNeg(x36225)
    val x36227 = OpDef(op=FixLt, inputs=List(x36216, Const(0.0))).name("x36227").ctrl(x36234).srcCtx("CellsPar.scala:63:22") // FixLt(x36216,Const(0))
    val x36228 = OpDef(op=MuxOp, inputs=List(x36227, x36226, x36225)).name("x36228").ctrl(x36234).srcCtx("CellsPar.scala:63:17:re") // Mux(x36227,x36226,x36225)
    val x36229 = x36228 // FixConvert(x36228,TRUE,_8,_24) (Same Type. No op)
    val x36230 = OpDef(op=FixAdd, inputs=List(x36229, Const(1.0))).name("x36230").ctrl(x36234).srcCtx("CellsPar.scala:29:33") // FixAdd(x36229,Const(1))
    val x36231 = OpDef(op=FixSra, inputs=List(x36230, Const(1))).name("x36231").ctrl(x36234).srcCtx("CellsPar.scala:29:44") // FixRsh(x36230,Const(1))
    val x36232 = OpDef(op=MuxOp, inputs=List(x36212, x36231, x36211)).name("x36232").ctrl(x36234).srcCtx("CellsPar.scala:218:43") // Mux(x36212,x36231,x36211)
    val x36233 = StoreBanks(List(x34696_d0_b0, x34696_d1_b0), List(b23833, x36200), x36232).name("x36233").ctrl(x36234).srcCtx("CellsPar.scala:218:38") // SRAMStore(x34696,ArrayBuffer(Const(1), Const(512)),List(b23833, x36200),Const(0),x36232,x36207)
    val x36238 = Counter(min=Const(0), max=Const(512), step=Const(1), par=1).name("x36238").ctrl(x36742).srcCtx("CellsPar.scala:267:50") // CounterNew(Const(0),Const(512),Const(1),Const(1))
    val x36239 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x36239").ctrl(x36742).srcCtx("CellsPar.scala:267:27") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x36240 = CounterChain(List(x36239,x36238)).name("x36240").ctrl(x36742).srcCtx("CellsPar.scala:267:62") // CounterChainNew(List(x36239, x36238))
    val x36273 = LoopController(style=InnerPipe, level=InnerControl, cchain=x36240).name("x36273").ctrl(x36742).srcCtx("CellsPar.scala:267:62") // UnrolledForeach(List(b22320),x36240,Block(Const(())),List(List(b23912), List(b23913)),List(List(b23914), List(b23915)))
    val b23912 = CounterIter(x36239, Some(0)).name("b23912").ctrl(x36273) // b23912
    val b23914 = Const(true).name("b23914").ctrl(x36273) // b23914
    val b23913 = CounterIter(x36238, None).name("b23913").ctrl(x36273) // b23913
    val b23915 = Const(true).name("b23915").ctrl(x36273) // b23915
    val x36241 = OpDef(op=BitAnd, inputs=List(b23914, b23915)).name("x36241").ctrl(x36273).srcCtx("UnrollingBase.scala:28:66") // And(b23914,b23915)
    val x36242 = OpDef(op=BitAnd, inputs=List(x36241, b22320)).name("x36242").ctrl(x36273).srcCtx("UnrollingBase.scala:28:66") // And(x36241,b22320)
    val x36243 = LoadBanks(List(x34692_d0_b0), List(b23912, b23913)).name("x36243").ctrl(x36273).srcCtx("CellsPar.scala:268:22") // ParSRAMLoad(x34692,List(List(b23912, b23913)),List(x36242))
    val x36244 = x36243 // x36244 = VectorApply(x36243,0)
    val x36245 = LoadBanks(List(x34695_d0_b0), List(b23912, b23913)).name("x36245").ctrl(x36273).srcCtx("CellsPar.scala:268:34") // ParSRAMLoad(x34695,List(List(b23912, b23913)),List(x36242))
    val x36246 = x36245 // x36246 = VectorApply(x36245,0)
    val x36247 = OpDef(op=FixMul, inputs=List(x36244, x36246)).name("x36247").ctrl(x36273).srcCtx("CellsPar.scala:268:28") // FixMul(x36244,x36246)
    val x36248 = LoadBanks(List(x34693_d0_b0), List(b23912, b23913)).name("x36248").ctrl(x36273).srcCtx("CellsPar.scala:268:46") // ParSRAMLoad(x34693,List(List(b23912, b23913)),List(x36242))
    val x36249 = x36248 // x36249 = VectorApply(x36248,0)
    val x36250 = LoadBanks(List(x34694_d0_b0), List(b23912, b23913)).name("x36250").ctrl(x36273).srcCtx("CellsPar.scala:268:59") // ParSRAMLoad(x34694,List(List(b23912, b23913)),List(x36242))
    val x36251 = x36250 // x36251 = VectorApply(x36250,0)
    val x36252 = OpDef(op=FixMul, inputs=List(x36249, x36251)).name("x36252").ctrl(x36273).srcCtx("CellsPar.scala:268:52") // FixMul(x36249,x36251)
    val x36253 = OpDef(op=FixAdd, inputs=List(x36247, x36252)).name("x36253").ctrl(x36273).srcCtx("CellsPar.scala:268:40:new_c") // FixAdd(x36247,x36252)
    val x36254 = x36253 // FixConvert(x36253,TRUE,_8,_24) (Same Type. No op)
    val x36255 = OpDef(op=FixAbs, inputs=List(x36254)).name("x36255").ctrl(x36273).srcCtx("CellsPar.scala:37:19:absp") // FixAbs(x36254)
    val x36256 = OpDef(op=FixLt, inputs=List(Const(2.5), x36255)).name("x36256").ctrl(x36273).srcCtx("CellsPar.scala:51:23:cond1") // FixLt(Const(2.5),x36255)
    val x36257 = OpDef(op=FixLt, inputs=List(Const(0.5), x36255)).name("x36257").ctrl(x36273).srcCtx("CellsPar.scala:54:35") // FixLt(Const(0.5),x36255)
    val x36258 = OpDef(op=FixLeq, inputs=List(x36255, Const(2.5))).name("x36258").ctrl(x36273).srcCtx("CellsPar.scala:54:52") // FixLeq(x36255,Const(2.5))
    val x36259 = OpDef(op=BitAnd, inputs=List(x36257, x36258)).name("x36259").ctrl(x36273).srcCtx("CellsPar.scala:54:43:cond2") // And(x36257,x36258)
    val x36260 = OpDef(op=FixSra, inputs=List(x36255, Const(2))).name("x36260").ctrl(x36273).srcCtx("CellsPar.scala:56:21:div4") // FixRsh(x36255,Const(2))
    val x36261 = OpDef(op=FixAdd, inputs=List(x36260, Const(0.375))).name("x36261").ctrl(x36273).srcCtx("CellsPar.scala:57:27:div4Offset") // FixAdd(x36260,Const(0.375))
    val x36262 = OpDef(op=MuxOp, inputs=List(x36259, x36261, x36255)).name("x36262").ctrl(x36273).srcCtx("CellsPar.scala:58:20:body2") // Mux(x36259,x36261,x36255)
    val x36263 = OpDef(op=MuxOp, inputs=List(x36256, Const(1.0), x36262)).name("x36263").ctrl(x36273).srcCtx("CellsPar.scala:60:20:absre") // Mux(x36256,Const(1),x36262)
    val x36264 = OpDef(op=FixNeg, inputs=List(x36263)).name("x36264").ctrl(x36273).srcCtx("CellsPar.scala:62:29:mabre") // FixNeg(x36263)
    val x36265 = OpDef(op=FixLt, inputs=List(x36254, Const(0.0))).name("x36265").ctrl(x36273).srcCtx("CellsPar.scala:63:22") // FixLt(x36254,Const(0))
    val x36266 = OpDef(op=MuxOp, inputs=List(x36265, x36264, x36263)).name("x36266").ctrl(x36273).srcCtx("CellsPar.scala:63:17:re") // Mux(x36265,x36264,x36263)
    val x36267 = x36266 // FixConvert(x36266,TRUE,_8,_24) (Same Type. No op)
    val x36268 = LoadBanks(List(x34696_d0_b0), List(b23912, b23913)).name("x36268").ctrl(x36273).srcCtx("CellsPar.scala:272:47") // ParSRAMLoad(x34696,List(List(b23912, b23913)),List(x36242))
    val x36269 = x36268 // x36269 = VectorApply(x36268,0)
    val x36270 = OpDef(op=FixMul, inputs=List(x36267, x36269)).name("x36270").ctrl(x36273).srcCtx("CellsPar.scala:272:41") // FixMul(x36267,x36269)
    val x36271 = StoreBanks(List(x34691_d0_b0, x34691_d5_b0, x34691_d1_b0, x34691_d6_b0, x34691_d2_b0, x34691_d7_b0, x34691_d3_b0, x34691_d4_b0), List(b23912, b23913), x36270).name("x36271").ctrl(x36273).srcCtx("CellsPar.scala:272:18") // ParSRAMStore(x34691,List(List(b23912, b23913)),List(x36270),List(x36242))
    val x36272 = StoreBanks(List(x34692_d0_b0), List(b23912, b23913), x36253).name("x36272").ctrl(x36273).srcCtx("CellsPar.scala:274:16") // ParSRAMStore(x34692,List(List(b23912, b23913)),List(x36253),List(x36242))
    val x36274 = Counter(min=Const(0), max=Const(512), step=Const(16), par=1).name("x36274").ctrl(x36742).srcCtx("CellsPar.scala:190:58") // CounterNew(Const(0),Const(512),Const(16),Const(1))
    val x36275 = Counter(min=Const(0), max=Const(1536), step=Const(16), par=1).name("x36275").ctrl(x36742).srcCtx("CellsPar.scala:190:33") // CounterNew(Const(0),Const(1536),Const(16),Const(1))
    val x36276 = CounterChain(List(x36275,x36274)).name("x36276").ctrl(x36742).srcCtx("CellsPar.scala:190:72") // CounterChainNew(List(x36275, x36274))
    val x36381 = LoopController(style=MetaPipe, level=OuterControl, cchain=x36276).name("x36381").ctrl(x36742).srcCtx("CellsPar.scala:190:72") // UnrolledForeach(List(b22320),x36276,Block(Const(())),List(List(b23952), List(b23953)),List(List(b23954), List(b23955)))
    val b23952 = CounterIter(x36275, Some(0)).name("b23952").ctrl(x36381) // b23952
    val b23954 = Const(true).name("b23954").ctrl(x36381) // b23954
    val b23953 = CounterIter(x36274, Some(0)).name("b23953").ctrl(x36381) // b23953
    val b23955 = Const(true).name("b23955").ctrl(x36381) // b23955
    val x36277_d0_b0 = SRAM(size=256, banking=Strided(banks=1, stride=16)).name("x36277_d0_b0").ctrl(x36381).srcCtx("CellsPar.scala:191:33:tileKernel") // x36277 = SRAMNew(ArrayBuffer(Const(16), Const(16)))
    isAccum(x36277_d0_b0) = false
    bufferDepthOf(x36277_d0_b0) = 2
    val x36280 = UnitController(style=SeqPipe, level=InnerControl).name("x36280").ctrl(x36381).srcCtx("CellsPar.scala:190:72") // UnitPipe(List(b23954, b23955, b22320),Block(Const(())))
    val x36278 = OpDef(op=FixAdd, inputs=List(b23952, Const(16))).name("x36278").ctrl(x36280).srcCtx("CellsPar.scala:192:36") // FixAdd(b23952,Const(16))
    val x36279 = OpDef(op=FixAdd, inputs=List(b23953, Const(16))).name("x36279").ctrl(x36280).srcCtx("CellsPar.scala:192:45") // FixAdd(b23953,Const(16))
    val x36281 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x36281").ctrl(x36381).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x36282 = CounterChain(List(x36281)).name("x36282").ctrl(x36381).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x36281))
    val x36311 = LoopController(style=StreamPipe, level=OuterControl, cchain=x36282).name("x36311").ctrl(x36381).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b23954, b23955, b22320),x36282,Block(Const(())),List(List(b23962)),List(List(b23963)))
    val b23962 = CounterIter(x36281, Some(0)).name("b23962").ctrl(x36311) // b23962
    val b23963 = Const(true).name("b23963").ctrl(x36311) // b23963
    val b36877 = StreamOut(field="offset").name("b36877").ctrl(x36311).srcCtx("CellsPar.scala:192:20") // x36283 = StreamOutNew(BurstCmdBus)
    isAccum(b36877) = false
    bufferDepthOf(b36877) = 1
    val b36878 = StreamOut(field="size").name("b36878").ctrl(x36311).srcCtx("CellsPar.scala:192:20") // x36283 = StreamOutNew(BurstCmdBus)
    isAccum(b36878) = false
    bufferDepthOf(b36878) = 1
    val x36284 = StreamIn(field="data").name("x36284").ctrl(x36311).srcCtx("CellsPar.scala:192:20") // x36284 = StreamInNew(BurstDataBus())
    isAccum(x36284) = false
    bufferDepthOf(x36284) = 1
    val x36299 = UnitController(style=SeqPipe, level=InnerControl).name("x36299").ctrl(x36311).srcCtx("CellsPar.scala:192:20") // UnitPipe(List(b23963, b23954, b23955, b22320),Block(x36298))
    val x36285 = OpDef(op=FixAdd, inputs=List(b23952, b23962)).name("x36285").ctrl(x36299).srcCtx("CellsPar.scala:192:20") // FixAdd(b23952,b23962)
    val x36286 = x36285 // FixConvert(x36285,TRUE,_32,_0) (Same Type. No op)
    val x36287 = OpDef(op=FixSla, inputs=List(x36286, Const(9))).name("x36287").ctrl(x36299).srcCtx("CellsPar.scala:192:20") // FixLsh(x36286,Const(9))
    val x36288 = b23953 // FixConvert(b23953,TRUE,_32,_0) (Same Type. No op)
    val x36289 = OpDef(op=FixAdd, inputs=List(x36287, x36288)).name("x36289").ctrl(x36299).srcCtx("CellsPar.scala:192:20") // FixAdd(x36287,x36288)
    val x36290 = OpDef(op=FixSla, inputs=List(x36289, Const(2))).name("x36290").ctrl(x36299).srcCtx("CellsPar.scala:192:20") // FixLsh(x36289,Const(2))
    val x36291 = x36290 // FixConvert(x36290,TRUE,_64,_0)
    val x36292 = DramAddress(x34628).name("x36292").ctrl(x36299).srcCtx("CellsPar.scala:192:20") // GetDRAMAddress(x34628)
    val x36294_x36293 = OpDef(op=FixAdd, inputs=List(x36291, x36292)).name("x36294_x36293").ctrl(x36299).srcCtx("CellsPar.scala:192:20") // FixAdd(x36291,x36292)
    // x36294 = SimpleStruct(ArrayBuffer((offset,x36293), (size,Const(64)), (isLoad,Const(true))))
    val x36295 = OpDef(op=BitAnd, inputs=List(b23963, b23954)).name("x36295").ctrl(x36299).srcCtx("UnrollingBase.scala:28:66") // And(b23963,b23954)
    val x36296 = OpDef(op=BitAnd, inputs=List(b23955, b22320)).name("x36296").ctrl(x36299).srcCtx("UnrollingBase.scala:28:66") // And(b23955,b22320)
    val x36297 = OpDef(op=BitAnd, inputs=List(x36295, x36296)).name("x36297").ctrl(x36299).srcCtx("UnrollingBase.scala:28:66") // And(x36295,x36296)
    val x36298_b36879_b36877 = WriteMem(b36877, x36294_x36293).name("x36298_b36879_b36877").ctrl(x36299).srcCtx("CellsPar.scala:192:20") // StreamWrite(x36283,x36294,x36297)
    val x36298_b36880_b36878 = WriteMem(b36878, Const(64)).name("x36298_b36880_b36878").ctrl(x36299).srcCtx("CellsPar.scala:192:20") // StreamWrite(x36283,x36294,x36297)
    val x36300 = FringeDenseLoad(dram=List(x34628), cmdStream=List(b36877, b36878), dataStream=List(x36284)).name("x36300").ctrl(x36311).srcCtx("CellsPar.scala:192:20") // FringeDenseLoad(x34628,x36283,x36284)
    val x36301 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x36301").ctrl(x36311).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x36302 = CounterChain(List(x36301)).name("x36302").ctrl(x36311).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x36301))
    val x36310 = LoopController(style=InnerPipe, level=InnerControl, cchain=x36302).name("x36310").ctrl(x36311).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b23963, b23954, b23955, b22320),x36302,Block(Const(())),List(List(b23984)),List(List(b23985)))
    val b23984 = CounterIter(x36301, None).name("b23984").ctrl(x36310) // b23984
    val b23985 = Const(true).name("b23985").ctrl(x36310) // b23985
    val x36303 = OpDef(op=BitAnd, inputs=List(b23985, b23963)).name("x36303").ctrl(x36310).srcCtx("UnrollingBase.scala:28:66") // And(b23985,b23963)
    val x36304 = OpDef(op=BitAnd, inputs=List(b23954, b23955)).name("x36304").ctrl(x36310).srcCtx("UnrollingBase.scala:28:66") // And(b23954,b23955)
    val x36305 = OpDef(op=BitAnd, inputs=List(x36303, x36304)).name("x36305").ctrl(x36310).srcCtx("UnrollingBase.scala:28:66") // And(x36303,x36304)
    val x36306 = OpDef(op=BitAnd, inputs=List(x36305, b22320)).name("x36306").ctrl(x36310).srcCtx("UnrollingBase.scala:28:66") // And(x36305,b22320)
    val x36307_x36307 = ReadMem(x36284).name("x36307_x36307").ctrl(x36310).srcCtx("CellsPar.scala:192:20") // ParStreamRead(x36284,List(x36306))
    val x36308_x36308 = x36307_x36307 // x36308 = VectorApply(x36307,0)
    val x36309 = StoreBanks(List(x36277_d0_b0), List(b23962, b23984), x36308_x36308).name("x36309").ctrl(x36310).srcCtx("CellsPar.scala:192:20") // ParSRAMStore(x36277,List(List(b23962, b23984)),List(x36308),List(x36306))
    val x36312 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x36312").ctrl(x36381).srcCtx("CellsPar.scala:193:34") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x36313 = CounterChain(List(x36312)).name("x36313").ctrl(x36381).srcCtx("CellsPar.scala:193:46") // CounterChainNew(List(x36312))
    val x36380 = LoopController(style=MetaPipe, level=OuterControl, cchain=x36313).name("x36380").ctrl(x36381).srcCtx("CellsPar.scala:193:46") // UnrolledForeach(List(b23954, b23955, b22320),x36313,Block(Const(())),List(List(b23997)),List(List(b23998)))
    val b23997 = CounterIter(x36312, Some(0)).name("b23997").ctrl(x36380) // b23997
    val b23998 = Const(true).name("b23998").ctrl(x36380) // b23998
    val x36314 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x36314").ctrl(x36380).srcCtx("CellsPar.scala:194:28") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x36315 = CounterChain(List(x36314)).name("x36315").ctrl(x36380).srcCtx("CellsPar.scala:194:42") // CounterChainNew(List(x36314))
    val x36379 = LoopController(style=MetaPipe, level=OuterControl, cchain=x36315).name("x36379").ctrl(x36380).srcCtx("CellsPar.scala:194:42") // UnrolledForeach(List(b23998, b23954, b23955, b22320),x36315,Block(Const(())),List(List(b24001)),List(List(b24002)))
    val b24001 = CounterIter(x36314, Some(0)).name("b24001").ctrl(x36379) // b24001
    val b24002 = Const(true).name("b24002").ctrl(x36379) // b24002
    val x36316_d0 = Reg(init=Some(0.0)).name("x36316_d0").ctrl(x36379).srcCtx("CellsPar.scala:195:34:prod") // x36316 = RegNew(Const(0))
    isAccum(x36316_d0) = false
    bufferDepthOf(x36316_d0) = 2
    val x36316_d1 = Reg(init=Some(0.0)).name("x36316_d1").ctrl(x36379).srcCtx("CellsPar.scala:195:34:prod") // x36316 = RegNew(Const(0))
    isAccum(x36316_d1) = true
    bufferDepthOf(x36316_d1) = 1
    val x36317 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x36317").ctrl(x36379).srcCtx("CellsPar.scala:195:43") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x36318 = CounterChain(List(x36317)).name("x36318").ctrl(x36379).srcCtx("CellsPar.scala:207:15") // CounterChainNew(List(x36317))
    val x36344 = LoopController(style=InnerPipe, level=InnerControl, cchain=x36318).name("x36344").ctrl(x36379).srcCtx("CellsPar.scala:207:15") // UnrolledReduce(List(b24002, b23998, b23954, b23955, b22320),x36318,x36316,Block((x36316) => Const(())),List(List(b24006)),List(List(b24007)))
    val b24006 = CounterIter(x36317, None).name("b24006").ctrl(x36344) // b24006
    val b24007 = Const(true).name("b24007").ctrl(x36344) // b24007
    val x36319 = OpDef(op=FixAdd, inputs=List(b23952, b24006)).name("x36319").ctrl(x36344).srcCtx("CellsPar.scala:196:42:reduce_size_offset") // FixAdd(b23952,b24006)
    val x36320 = OpDef(op=BitAnd, inputs=List(b24007, b24002)).name("x36320").ctrl(x36344).srcCtx("UnrollingBase.scala:28:66") // And(b24007,b24002)
    val x36321 = OpDef(op=BitAnd, inputs=List(b23998, b23954)).name("x36321").ctrl(x36344).srcCtx("UnrollingBase.scala:28:66") // And(b23998,b23954)
    val x36322 = OpDef(op=BitAnd, inputs=List(b23955, b22320)).name("x36322").ctrl(x36344).srcCtx("UnrollingBase.scala:28:66") // And(b23955,b22320)
    val x36323 = OpDef(op=BitAnd, inputs=List(x36320, x36321)).name("x36323").ctrl(x36344).srcCtx("UnrollingBase.scala:28:66") // And(x36320,x36321)
    val x36324 = OpDef(op=BitAnd, inputs=List(x36323, x36322)).name("x36324").ctrl(x36344).srcCtx("UnrollingBase.scala:28:66") // And(x36323,x36322)
    val x36325 = LoadBanks(List(x36277_d0_b0), List(b24006, b24001)).name("x36325").ctrl(x36344).srcCtx("CellsPar.scala:203:34:tk") // ParSRAMLoad(x36277,List(List(b24006, b24001)),List(x36324))
    val x36326 = x36325 // x36326 = VectorApply(x36325,0)
    val x36327 = LoadBanks(List(x34691_d3_b0), List(b23997, x36319)).name("x36327").ctrl(x36344).srcCtx("CellsPar.scala:204:28") // ParSRAMLoad(x34691,List(List(b23997, x36319)),List(x36324))
    val x36328 = x36327 // x36328 = VectorApply(x36327,0)
    val x36329 = OpDef(op=FixMul, inputs=List(x36328, x36326)).name("x36329").ctrl(x36344).srcCtx("CellsPar.scala:204:52:tempA") // FixMul(x36328,x36326)
    val x36330 = OpDef(op=FixSub, inputs=List(x36319, Const(512))).name("x36330").ctrl(x36344).srcCtx("CellsPar.scala:205:51") // FixSub(x36319,Const(512))
    val x36331 = LoadBanks(List(x34698_d4_b0), List(b23997, x36330)).name("x36331").ctrl(x36344).srcCtx("CellsPar.scala:205:28") // ParSRAMLoad(x34698,List(List(b23997, x36330)),List(x36324))
    val x36332 = x36331 // x36332 = VectorApply(x36331,0)
    val x36333 = OpDef(op=FixMul, inputs=List(x36332, x36326)).name("x36333").ctrl(x36344).srcCtx("CellsPar.scala:205:67:tempB") // FixMul(x36332,x36326)
    val x36334 = OpDef(op=FixLt, inputs=List(x36319, Const(512))).name("x36334").ctrl(x36344).srcCtx("CellsPar.scala:206:38") // FixLt(x36319,Const(512))
    val x36335 = OpDef(op=MuxOp, inputs=List(x36334, x36329, x36333)).name("x36335").ctrl(x36344).srcCtx("CellsPar.scala:206:18") // Mux(x36334,x36329,x36333)
    val x36336 = ReadMem(x36316_d1).name("x36336").ctrl(x36344).srcCtx("CellsPar.scala:207:15") // RegRead(x36316)
    val x36337 = OpDef(op=FixEql, inputs=List(b24006, Const(0))).name("x36337").ctrl(x36344).srcCtx("CellsPar.scala:207:15") // FixEql(b24006,Const(0))
    val x36338 = ReduceAccumOp(op=FixAdd, input=x36335, accum=x36336).name("x36338").ctrl(x36344).srcCtx("CellsPar.scala:207:17") // FixAdd(x36335,x36336)
    val x36339 = OpDef(op=BitAnd, inputs=List(b24002, b23998)).name("x36339").ctrl(x36344).srcCtx("UnrollingBase.scala:28:66") // And(b24002,b23998)
    val x36340 = OpDef(op=BitAnd, inputs=List(b23954, b23955)).name("x36340").ctrl(x36344).srcCtx("UnrollingBase.scala:28:66") // And(b23954,b23955)
    val x36341 = OpDef(op=BitAnd, inputs=List(x36339, x36340)).name("x36341").ctrl(x36344).srcCtx("UnrollingBase.scala:28:66") // And(x36339,x36340)
    val x36342 = OpDef(op=BitAnd, inputs=List(x36341, b22320)).name("x36342").ctrl(x36344).srcCtx("UnrollingBase.scala:28:66") // And(x36341,b22320)
    val x36343_x36316_d0 = WriteMem(x36316_d0, x36338).name("x36343_x36316_d0").ctrl(x36344).srcCtx("CellsPar.scala:207:15") // RegWrite(x36316,x36338,x36342)
    val x36343_x36316_d1 = WriteMem(x36316_d1, x36338).name("x36343_x36316_d1").ctrl(x36344).srcCtx("CellsPar.scala:207:15") // RegWrite(x36316,x36338,x36342)
    val x36378 = UnitController(style=SeqPipe, level=InnerControl).name("x36378").ctrl(x36379).srcCtx("CellsPar.scala:194:42") // UnitPipe(List(b24002, b23998, b23954, b23955, b22320),Block(Const(())))
    val x36345 = OpDef(op=FixAdd, inputs=List(b23953, b24001)).name("x36345").ctrl(x36378).srcCtx("CellsPar.scala:209:31:colOffset") // FixAdd(b23953,b24001)
    val x36346 = ReadMem(x36316_d0).name("x36346").ctrl(x36378).srcCtx("CellsPar.scala:210:28") // RegRead(x36316)
    val x36347 = OpDef(op=FixEql, inputs=List(b23952, Const(0))).name("x36347").ctrl(x36378).srcCtx("CellsPar.scala:210:42") // FixEql(b23952,Const(0))
    val x36348 = OpDef(op=BitAnd, inputs=List(b24002, b23998)).name("x36348").ctrl(x36378).srcCtx("UnrollingBase.scala:28:66") // And(b24002,b23998)
    val x36349 = OpDef(op=BitAnd, inputs=List(b23954, b23955)).name("x36349").ctrl(x36378).srcCtx("UnrollingBase.scala:28:66") // And(b23954,b23955)
    val x36350 = OpDef(op=BitAnd, inputs=List(x36348, x36349)).name("x36350").ctrl(x36378).srcCtx("UnrollingBase.scala:28:66") // And(x36348,x36349)
    val x36351 = OpDef(op=BitAnd, inputs=List(x36350, b22320)).name("x36351").ctrl(x36378).srcCtx("UnrollingBase.scala:28:66") // And(x36350,b22320)
    val x36352 = LoadBanks(List(x34704_d3_b0), List(Const(0), x36345)).name("x36352").ctrl(x36378).srcCtx("CellsPar.scala:210:52") // SRAMLoad(x34704,ArrayBuffer(Const(1), Const(2048)),List(Const(0), x36345),Const(0),x36351)
    val x36353 = LoadBanks(List(x34700_d1_b0), List(b23997, x36345)).name("x36353").ctrl(x36378).srcCtx("CellsPar.scala:210:91") // SRAMLoad(x34700,ArrayBuffer(Const(1), Const(512)),List(b23997, x36345),Const(0),x36351)
    val x36354 = OpDef(op=MuxOp, inputs=List(x36347, x36352, x36353)).name("x36354").ctrl(x36378).srcCtx("CellsPar.scala:210:39") // Mux(x36347,x36352,x36353)
    val x36355 = OpDef(op=FixAdd, inputs=List(x36346, x36354)).name("x36355").ctrl(x36378).srcCtx("CellsPar.scala:210:34:ele") // FixAdd(x36346,x36354)
    val x36356 = OpDef(op=FixLeq, inputs=List(Const(1520), b23952)).name("x36356").ctrl(x36378).srcCtx("CellsPar.scala:218:46") // FixLeq(Const(1520),b23952)
    // x36357 = FixConvert(Const(0),TRUE,_8,_24) x.tp=FixPt[TRUE,_32,_0] {
    val x36357_int1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("01111111111111111111111111111111"))).name("x36357_int1").ctrl(x36378).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x36357_int2 = OpDef(op=FixSla, inputs=List(x36357_int1, Const(24))).name("x36357_int2").ctrl(x36378).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x36357_frac1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("00000000000000000000000000000000"))).name("x36357_frac1").ctrl(x36378).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x36357_frac2 = OpDef(op=FixSla, inputs=List(x36357_frac1, Const(24))).name("x36357_frac2").ctrl(x36378).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x36357 = OpDef(op=BitOr, inputs=List(x36357_int2, x36357_frac2)).name("x36357").ctrl(x36378).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    // }
    val x36358 = OpDef(op=FixAdd, inputs=List(x36355, x36357)).name("x36358").ctrl(x36378).srcCtx("CellsPar.scala:218:87") // FixAdd(x36355,x36357)
    val x36359 = OpDef(op=FixSra, inputs=List(x36358, Const(1))).name("x36359").ctrl(x36378).srcCtx("CellsPar.scala:29:22") // FixRsh(x36358,Const(1))
    val x36360 = x36359 // FixConvert(x36359,TRUE,_8,_24) (Same Type. No op)
    val x36361 = OpDef(op=FixAbs, inputs=List(x36360)).name("x36361").ctrl(x36378).srcCtx("CellsPar.scala:37:19:absp") // FixAbs(x36360)
    val x36362 = OpDef(op=FixLt, inputs=List(Const(2.5), x36361)).name("x36362").ctrl(x36378).srcCtx("CellsPar.scala:51:23:cond1") // FixLt(Const(2.5),x36361)
    val x36363 = OpDef(op=FixLt, inputs=List(Const(0.5), x36361)).name("x36363").ctrl(x36378).srcCtx("CellsPar.scala:54:35") // FixLt(Const(0.5),x36361)
    val x36364 = OpDef(op=FixLeq, inputs=List(x36361, Const(2.5))).name("x36364").ctrl(x36378).srcCtx("CellsPar.scala:54:52") // FixLeq(x36361,Const(2.5))
    val x36365 = OpDef(op=BitAnd, inputs=List(x36363, x36364)).name("x36365").ctrl(x36378).srcCtx("CellsPar.scala:54:43:cond2") // And(x36363,x36364)
    val x36366 = OpDef(op=FixSra, inputs=List(x36361, Const(2))).name("x36366").ctrl(x36378).srcCtx("CellsPar.scala:56:21:div4") // FixRsh(x36361,Const(2))
    val x36367 = OpDef(op=FixAdd, inputs=List(x36366, Const(0.375))).name("x36367").ctrl(x36378).srcCtx("CellsPar.scala:57:27:div4Offset") // FixAdd(x36366,Const(0.375))
    val x36368 = OpDef(op=MuxOp, inputs=List(x36365, x36367, x36361)).name("x36368").ctrl(x36378).srcCtx("CellsPar.scala:58:20:body2") // Mux(x36365,x36367,x36361)
    val x36369 = OpDef(op=MuxOp, inputs=List(x36362, Const(1.0), x36368)).name("x36369").ctrl(x36378).srcCtx("CellsPar.scala:60:20:absre") // Mux(x36362,Const(1),x36368)
    val x36370 = OpDef(op=FixNeg, inputs=List(x36369)).name("x36370").ctrl(x36378).srcCtx("CellsPar.scala:62:29:mabre") // FixNeg(x36369)
    val x36371 = OpDef(op=FixLt, inputs=List(x36360, Const(0.0))).name("x36371").ctrl(x36378).srcCtx("CellsPar.scala:63:22") // FixLt(x36360,Const(0))
    val x36372 = OpDef(op=MuxOp, inputs=List(x36371, x36370, x36369)).name("x36372").ctrl(x36378).srcCtx("CellsPar.scala:63:17:re") // Mux(x36371,x36370,x36369)
    val x36373 = x36372 // FixConvert(x36372,TRUE,_8,_24) (Same Type. No op)
    val x36374 = OpDef(op=FixAdd, inputs=List(x36373, Const(1.0))).name("x36374").ctrl(x36378).srcCtx("CellsPar.scala:29:33") // FixAdd(x36373,Const(1))
    val x36375 = OpDef(op=FixSra, inputs=List(x36374, Const(1))).name("x36375").ctrl(x36378).srcCtx("CellsPar.scala:29:44") // FixRsh(x36374,Const(1))
    val x36376 = OpDef(op=MuxOp, inputs=List(x36356, x36375, x36355)).name("x36376").ctrl(x36378).srcCtx("CellsPar.scala:218:43") // Mux(x36356,x36375,x36355)
    val x36377 = StoreBanks(List(x34700_d0_b0, x34700_d1_b0), List(b23997, x36345), x36376).name("x36377").ctrl(x36378).srcCtx("CellsPar.scala:218:38") // SRAMStore(x34700,ArrayBuffer(Const(1), Const(512)),List(b23997, x36345),Const(0),x36376,x36351)
    val x36382 = Counter(min=Const(0), max=Const(512), step=Const(16), par=1).name("x36382").ctrl(x36742).srcCtx("CellsPar.scala:190:58") // CounterNew(Const(0),Const(512),Const(16),Const(1))
    val x36383 = Counter(min=Const(0), max=Const(1536), step=Const(16), par=1).name("x36383").ctrl(x36742).srcCtx("CellsPar.scala:190:33") // CounterNew(Const(0),Const(1536),Const(16),Const(1))
    val x36384 = CounterChain(List(x36383,x36382)).name("x36384").ctrl(x36742).srcCtx("CellsPar.scala:190:72") // CounterChainNew(List(x36383, x36382))
    val x36487 = LoopController(style=MetaPipe, level=OuterControl, cchain=x36384).name("x36487").ctrl(x36742).srcCtx("CellsPar.scala:190:72") // UnrolledForeach(List(b22320),x36384,Block(Const(())),List(List(b24074), List(b24075)),List(List(b24076), List(b24077)))
    val b24074 = CounterIter(x36383, Some(0)).name("b24074").ctrl(x36487) // b24074
    val b24076 = Const(true).name("b24076").ctrl(x36487) // b24076
    val b24075 = CounterIter(x36382, Some(0)).name("b24075").ctrl(x36487) // b24075
    val b24077 = Const(true).name("b24077").ctrl(x36487) // b24077
    val x36385_d0_b0 = SRAM(size=256, banking=Strided(banks=1, stride=16)).name("x36385_d0_b0").ctrl(x36487).srcCtx("CellsPar.scala:191:33:tileKernel") // x36385 = SRAMNew(ArrayBuffer(Const(16), Const(16)))
    isAccum(x36385_d0_b0) = false
    bufferDepthOf(x36385_d0_b0) = 2
    val x36388 = UnitController(style=SeqPipe, level=InnerControl).name("x36388").ctrl(x36487).srcCtx("CellsPar.scala:190:72") // UnitPipe(List(b24076, b24077, b22320),Block(Const(())))
    val x36386 = OpDef(op=FixAdd, inputs=List(b24074, Const(16))).name("x36386").ctrl(x36388).srcCtx("CellsPar.scala:192:36") // FixAdd(b24074,Const(16))
    val x36387 = OpDef(op=FixAdd, inputs=List(b24075, Const(16))).name("x36387").ctrl(x36388).srcCtx("CellsPar.scala:192:45") // FixAdd(b24075,Const(16))
    val x36389 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x36389").ctrl(x36487).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x36390 = CounterChain(List(x36389)).name("x36390").ctrl(x36487).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x36389))
    val x36419 = LoopController(style=StreamPipe, level=OuterControl, cchain=x36390).name("x36419").ctrl(x36487).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b24076, b24077, b22320),x36390,Block(Const(())),List(List(b24084)),List(List(b24085)))
    val b24084 = CounterIter(x36389, Some(0)).name("b24084").ctrl(x36419) // b24084
    val b24085 = Const(true).name("b24085").ctrl(x36419) // b24085
    val b36881 = StreamOut(field="offset").name("b36881").ctrl(x36419).srcCtx("CellsPar.scala:192:20") // x36391 = StreamOutNew(BurstCmdBus)
    isAccum(b36881) = false
    bufferDepthOf(b36881) = 1
    val b36882 = StreamOut(field="size").name("b36882").ctrl(x36419).srcCtx("CellsPar.scala:192:20") // x36391 = StreamOutNew(BurstCmdBus)
    isAccum(b36882) = false
    bufferDepthOf(b36882) = 1
    val x36392 = StreamIn(field="data").name("x36392").ctrl(x36419).srcCtx("CellsPar.scala:192:20") // x36392 = StreamInNew(BurstDataBus())
    isAccum(x36392) = false
    bufferDepthOf(x36392) = 1
    val x36407 = UnitController(style=SeqPipe, level=InnerControl).name("x36407").ctrl(x36419).srcCtx("CellsPar.scala:192:20") // UnitPipe(List(b24085, b24076, b24077, b22320),Block(x36406))
    val x36393 = OpDef(op=FixAdd, inputs=List(b24074, b24084)).name("x36393").ctrl(x36407).srcCtx("CellsPar.scala:192:20") // FixAdd(b24074,b24084)
    val x36394 = x36393 // FixConvert(x36393,TRUE,_32,_0) (Same Type. No op)
    val x36395 = OpDef(op=FixSla, inputs=List(x36394, Const(9))).name("x36395").ctrl(x36407).srcCtx("CellsPar.scala:192:20") // FixLsh(x36394,Const(9))
    val x36396 = b24075 // FixConvert(b24075,TRUE,_32,_0) (Same Type. No op)
    val x36397 = OpDef(op=FixAdd, inputs=List(x36395, x36396)).name("x36397").ctrl(x36407).srcCtx("CellsPar.scala:192:20") // FixAdd(x36395,x36396)
    val x36398 = OpDef(op=FixSla, inputs=List(x36397, Const(2))).name("x36398").ctrl(x36407).srcCtx("CellsPar.scala:192:20") // FixLsh(x36397,Const(2))
    val x36399 = x36398 // FixConvert(x36398,TRUE,_64,_0)
    val x36400 = DramAddress(x34629).name("x36400").ctrl(x36407).srcCtx("CellsPar.scala:192:20") // GetDRAMAddress(x34629)
    val x36402_x36401 = OpDef(op=FixAdd, inputs=List(x36399, x36400)).name("x36402_x36401").ctrl(x36407).srcCtx("CellsPar.scala:192:20") // FixAdd(x36399,x36400)
    // x36402 = SimpleStruct(ArrayBuffer((offset,x36401), (size,Const(64)), (isLoad,Const(true))))
    val x36403 = OpDef(op=BitAnd, inputs=List(b24085, b24076)).name("x36403").ctrl(x36407).srcCtx("UnrollingBase.scala:28:66") // And(b24085,b24076)
    val x36404 = OpDef(op=BitAnd, inputs=List(b24077, b22320)).name("x36404").ctrl(x36407).srcCtx("UnrollingBase.scala:28:66") // And(b24077,b22320)
    val x36405 = OpDef(op=BitAnd, inputs=List(x36403, x36404)).name("x36405").ctrl(x36407).srcCtx("UnrollingBase.scala:28:66") // And(x36403,x36404)
    val x36406_b36883_b36881 = WriteMem(b36881, x36402_x36401).name("x36406_b36883_b36881").ctrl(x36407).srcCtx("CellsPar.scala:192:20") // StreamWrite(x36391,x36402,x36405)
    val x36406_b36884_b36882 = WriteMem(b36882, Const(64)).name("x36406_b36884_b36882").ctrl(x36407).srcCtx("CellsPar.scala:192:20") // StreamWrite(x36391,x36402,x36405)
    val x36408 = FringeDenseLoad(dram=List(x34629), cmdStream=List(b36881, b36882), dataStream=List(x36392)).name("x36408").ctrl(x36419).srcCtx("CellsPar.scala:192:20") // FringeDenseLoad(x34629,x36391,x36392)
    val x36409 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x36409").ctrl(x36419).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x36410 = CounterChain(List(x36409)).name("x36410").ctrl(x36419).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x36409))
    val x36418 = LoopController(style=InnerPipe, level=InnerControl, cchain=x36410).name("x36418").ctrl(x36419).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b24085, b24076, b24077, b22320),x36410,Block(Const(())),List(List(b24106)),List(List(b24107)))
    val b24106 = CounterIter(x36409, None).name("b24106").ctrl(x36418) // b24106
    val b24107 = Const(true).name("b24107").ctrl(x36418) // b24107
    val x36411 = OpDef(op=BitAnd, inputs=List(b24107, b24085)).name("x36411").ctrl(x36418).srcCtx("UnrollingBase.scala:28:66") // And(b24107,b24085)
    val x36412 = OpDef(op=BitAnd, inputs=List(b24076, b24077)).name("x36412").ctrl(x36418).srcCtx("UnrollingBase.scala:28:66") // And(b24076,b24077)
    val x36413 = OpDef(op=BitAnd, inputs=List(x36411, x36412)).name("x36413").ctrl(x36418).srcCtx("UnrollingBase.scala:28:66") // And(x36411,x36412)
    val x36414 = OpDef(op=BitAnd, inputs=List(x36413, b22320)).name("x36414").ctrl(x36418).srcCtx("UnrollingBase.scala:28:66") // And(x36413,b22320)
    val x36415_x36415 = ReadMem(x36392).name("x36415_x36415").ctrl(x36418).srcCtx("CellsPar.scala:192:20") // ParStreamRead(x36392,List(x36414))
    val x36416_x36416 = x36415_x36415 // x36416 = VectorApply(x36415,0)
    val x36417 = StoreBanks(List(x36385_d0_b0), List(b24084, b24106), x36416_x36416).name("x36417").ctrl(x36418).srcCtx("CellsPar.scala:192:20") // ParSRAMStore(x36385,List(List(b24084, b24106)),List(x36416),List(x36414))
    val x36420 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x36420").ctrl(x36487).srcCtx("CellsPar.scala:193:34") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x36421 = CounterChain(List(x36420)).name("x36421").ctrl(x36487).srcCtx("CellsPar.scala:193:46") // CounterChainNew(List(x36420))
    val x36486 = LoopController(style=MetaPipe, level=OuterControl, cchain=x36421).name("x36486").ctrl(x36487).srcCtx("CellsPar.scala:193:46") // UnrolledForeach(List(b24076, b24077, b22320),x36421,Block(Const(())),List(List(b24119)),List(List(b24120)))
    val b24119 = CounterIter(x36420, Some(0)).name("b24119").ctrl(x36486) // b24119
    val b24120 = Const(true).name("b24120").ctrl(x36486) // b24120
    val x36422 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x36422").ctrl(x36486).srcCtx("CellsPar.scala:194:28") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x36423 = CounterChain(List(x36422)).name("x36423").ctrl(x36486).srcCtx("CellsPar.scala:194:42") // CounterChainNew(List(x36422))
    val x36485 = LoopController(style=MetaPipe, level=OuterControl, cchain=x36423).name("x36485").ctrl(x36486).srcCtx("CellsPar.scala:194:42") // UnrolledForeach(List(b24120, b24076, b24077, b22320),x36423,Block(Const(())),List(List(b24123)),List(List(b24124)))
    val b24123 = CounterIter(x36422, Some(0)).name("b24123").ctrl(x36485) // b24123
    val b24124 = Const(true).name("b24124").ctrl(x36485) // b24124
    val x36424_d0 = Reg(init=Some(0.0)).name("x36424_d0").ctrl(x36485).srcCtx("CellsPar.scala:195:34:prod") // x36424 = RegNew(Const(0))
    isAccum(x36424_d0) = false
    bufferDepthOf(x36424_d0) = 2
    val x36424_d1 = Reg(init=Some(0.0)).name("x36424_d1").ctrl(x36485).srcCtx("CellsPar.scala:195:34:prod") // x36424 = RegNew(Const(0))
    isAccum(x36424_d1) = true
    bufferDepthOf(x36424_d1) = 1
    val x36425 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x36425").ctrl(x36485).srcCtx("CellsPar.scala:195:43") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x36426 = CounterChain(List(x36425)).name("x36426").ctrl(x36485).srcCtx("CellsPar.scala:207:15") // CounterChainNew(List(x36425))
    val x36452 = LoopController(style=InnerPipe, level=InnerControl, cchain=x36426).name("x36452").ctrl(x36485).srcCtx("CellsPar.scala:207:15") // UnrolledReduce(List(b24124, b24120, b24076, b24077, b22320),x36426,x36424,Block((x36424) => Const(())),List(List(b24128)),List(List(b24129)))
    val b24128 = CounterIter(x36425, None).name("b24128").ctrl(x36452) // b24128
    val b24129 = Const(true).name("b24129").ctrl(x36452) // b24129
    val x36427 = OpDef(op=FixAdd, inputs=List(b24074, b24128)).name("x36427").ctrl(x36452).srcCtx("CellsPar.scala:196:42:reduce_size_offset") // FixAdd(b24074,b24128)
    val x36428 = OpDef(op=BitAnd, inputs=List(b24129, b24124)).name("x36428").ctrl(x36452).srcCtx("UnrollingBase.scala:28:66") // And(b24129,b24124)
    val x36429 = OpDef(op=BitAnd, inputs=List(b24120, b24076)).name("x36429").ctrl(x36452).srcCtx("UnrollingBase.scala:28:66") // And(b24120,b24076)
    val x36430 = OpDef(op=BitAnd, inputs=List(b24077, b22320)).name("x36430").ctrl(x36452).srcCtx("UnrollingBase.scala:28:66") // And(b24077,b22320)
    val x36431 = OpDef(op=BitAnd, inputs=List(x36428, x36429)).name("x36431").ctrl(x36452).srcCtx("UnrollingBase.scala:28:66") // And(x36428,x36429)
    val x36432 = OpDef(op=BitAnd, inputs=List(x36431, x36430)).name("x36432").ctrl(x36452).srcCtx("UnrollingBase.scala:28:66") // And(x36431,x36430)
    val x36433 = LoadBanks(List(x36385_d0_b0), List(b24128, b24123)).name("x36433").ctrl(x36452).srcCtx("CellsPar.scala:203:34:tk") // ParSRAMLoad(x36385,List(List(b24128, b24123)),List(x36432))
    val x36434 = x36433 // x36434 = VectorApply(x36433,0)
    val x36435 = LoadBanks(List(x34691_d2_b0), List(b24119, x36427)).name("x36435").ctrl(x36452).srcCtx("CellsPar.scala:204:28") // ParSRAMLoad(x34691,List(List(b24119, x36427)),List(x36432))
    val x36436 = x36435 // x36436 = VectorApply(x36435,0)
    val x36437 = OpDef(op=FixMul, inputs=List(x36436, x36434)).name("x36437").ctrl(x36452).srcCtx("CellsPar.scala:204:52:tempA") // FixMul(x36436,x36434)
    val x36438 = OpDef(op=FixSub, inputs=List(x36427, Const(512))).name("x36438").ctrl(x36452).srcCtx("CellsPar.scala:205:51") // FixSub(x36427,Const(512))
    val x36439 = LoadBanks(List(x34698_d3_b0), List(b24119, x36438)).name("x36439").ctrl(x36452).srcCtx("CellsPar.scala:205:28") // ParSRAMLoad(x34698,List(List(b24119, x36438)),List(x36432))
    val x36440 = x36439 // x36440 = VectorApply(x36439,0)
    val x36441 = OpDef(op=FixMul, inputs=List(x36440, x36434)).name("x36441").ctrl(x36452).srcCtx("CellsPar.scala:205:67:tempB") // FixMul(x36440,x36434)
    val x36442 = OpDef(op=FixLt, inputs=List(x36427, Const(512))).name("x36442").ctrl(x36452).srcCtx("CellsPar.scala:206:38") // FixLt(x36427,Const(512))
    val x36443 = OpDef(op=MuxOp, inputs=List(x36442, x36437, x36441)).name("x36443").ctrl(x36452).srcCtx("CellsPar.scala:206:18") // Mux(x36442,x36437,x36441)
    val x36444 = ReadMem(x36424_d1).name("x36444").ctrl(x36452).srcCtx("CellsPar.scala:207:15") // RegRead(x36424)
    val x36445 = OpDef(op=FixEql, inputs=List(b24128, Const(0))).name("x36445").ctrl(x36452).srcCtx("CellsPar.scala:207:15") // FixEql(b24128,Const(0))
    val x36446 = ReduceAccumOp(op=FixAdd, input=x36443, accum=x36444).name("x36446").ctrl(x36452).srcCtx("CellsPar.scala:207:17") // FixAdd(x36443,x36444)
    val x36447 = OpDef(op=BitAnd, inputs=List(b24124, b24120)).name("x36447").ctrl(x36452).srcCtx("UnrollingBase.scala:28:66") // And(b24124,b24120)
    val x36448 = OpDef(op=BitAnd, inputs=List(b24076, b24077)).name("x36448").ctrl(x36452).srcCtx("UnrollingBase.scala:28:66") // And(b24076,b24077)
    val x36449 = OpDef(op=BitAnd, inputs=List(x36447, x36448)).name("x36449").ctrl(x36452).srcCtx("UnrollingBase.scala:28:66") // And(x36447,x36448)
    val x36450 = OpDef(op=BitAnd, inputs=List(x36449, b22320)).name("x36450").ctrl(x36452).srcCtx("UnrollingBase.scala:28:66") // And(x36449,b22320)
    val x36451_x36424_d0 = WriteMem(x36424_d0, x36446).name("x36451_x36424_d0").ctrl(x36452).srcCtx("CellsPar.scala:207:15") // RegWrite(x36424,x36446,x36450)
    val x36451_x36424_d1 = WriteMem(x36424_d1, x36446).name("x36451_x36424_d1").ctrl(x36452).srcCtx("CellsPar.scala:207:15") // RegWrite(x36424,x36446,x36450)
    val x36484 = UnitController(style=SeqPipe, level=InnerControl).name("x36484").ctrl(x36485).srcCtx("CellsPar.scala:194:42") // UnitPipe(List(b24124, b24120, b24076, b24077, b22320),Block(Const(())))
    val x36453 = OpDef(op=FixAdd, inputs=List(b24075, b24123)).name("x36453").ctrl(x36484).srcCtx("CellsPar.scala:209:31:colOffset") // FixAdd(b24075,b24123)
    val x36454 = ReadMem(x36424_d0).name("x36454").ctrl(x36484).srcCtx("CellsPar.scala:210:28") // RegRead(x36424)
    val x36455 = OpDef(op=FixEql, inputs=List(b24074, Const(0))).name("x36455").ctrl(x36484).srcCtx("CellsPar.scala:210:42") // FixEql(b24074,Const(0))
    val x36456 = OpDef(op=FixAdd, inputs=List(x36453, Const(512))).name("x36456").ctrl(x36484).srcCtx("CellsPar.scala:210:66") // FixAdd(x36453,Const(512))
    val x36457 = OpDef(op=BitAnd, inputs=List(b24124, b24120)).name("x36457").ctrl(x36484).srcCtx("UnrollingBase.scala:28:66") // And(b24124,b24120)
    val x36458 = OpDef(op=BitAnd, inputs=List(b24076, b24077)).name("x36458").ctrl(x36484).srcCtx("UnrollingBase.scala:28:66") // And(b24076,b24077)
    val x36459 = OpDef(op=BitAnd, inputs=List(x36457, x36458)).name("x36459").ctrl(x36484).srcCtx("UnrollingBase.scala:28:66") // And(x36457,x36458)
    val x36460 = OpDef(op=BitAnd, inputs=List(x36459, b22320)).name("x36460").ctrl(x36484).srcCtx("UnrollingBase.scala:28:66") // And(x36459,b22320)
    val x36461 = LoadBanks(List(x34704_d2_b0), List(Const(0), x36456)).name("x36461").ctrl(x36484).srcCtx("CellsPar.scala:210:52") // SRAMLoad(x34704,ArrayBuffer(Const(1), Const(2048)),List(Const(0), x36456),Const(0),x36460)
    val x36462 = LoadBanks(List(x34701_d1_b0), List(b24119, x36453)).name("x36462").ctrl(x36484).srcCtx("CellsPar.scala:210:91") // SRAMLoad(x34701,ArrayBuffer(Const(1), Const(512)),List(b24119, x36453),Const(0),x36460)
    val x36463 = OpDef(op=MuxOp, inputs=List(x36455, x36461, x36462)).name("x36463").ctrl(x36484).srcCtx("CellsPar.scala:210:39") // Mux(x36455,x36461,x36462)
    val x36464 = OpDef(op=FixAdd, inputs=List(x36454, x36463)).name("x36464").ctrl(x36484).srcCtx("CellsPar.scala:210:34:ele") // FixAdd(x36454,x36463)
    val x36465 = OpDef(op=FixLeq, inputs=List(Const(1520), b24074)).name("x36465").ctrl(x36484).srcCtx("CellsPar.scala:218:46") // FixLeq(Const(1520),b24074)
    // x36466 = FixConvert(Const(0),TRUE,_8,_24) x.tp=FixPt[TRUE,_32,_0] {
    val x36466_int1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("01111111111111111111111111111111"))).name("x36466_int1").ctrl(x36484).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x36466_int2 = OpDef(op=FixSla, inputs=List(x36466_int1, Const(24))).name("x36466_int2").ctrl(x36484).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x36466_frac1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("00000000000000000000000000000000"))).name("x36466_frac1").ctrl(x36484).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x36466_frac2 = OpDef(op=FixSla, inputs=List(x36466_frac1, Const(24))).name("x36466_frac2").ctrl(x36484).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x36466 = OpDef(op=BitOr, inputs=List(x36466_int2, x36466_frac2)).name("x36466").ctrl(x36484).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    // }
    val x36467 = OpDef(op=FixAdd, inputs=List(x36464, x36466)).name("x36467").ctrl(x36484).srcCtx("CellsPar.scala:218:87") // FixAdd(x36464,x36466)
    val x36468 = x36467 // FixConvert(x36467,TRUE,_8,_24) (Same Type. No op)
    val x36469 = OpDef(op=FixAbs, inputs=List(x36468)).name("x36469").ctrl(x36484).srcCtx("CellsPar.scala:37:19:absp") // FixAbs(x36468)
    val x36470 = OpDef(op=FixLt, inputs=List(Const(2.5), x36469)).name("x36470").ctrl(x36484).srcCtx("CellsPar.scala:51:23:cond1") // FixLt(Const(2.5),x36469)
    val x36471 = OpDef(op=FixLt, inputs=List(Const(0.5), x36469)).name("x36471").ctrl(x36484).srcCtx("CellsPar.scala:54:35") // FixLt(Const(0.5),x36469)
    val x36472 = OpDef(op=FixLeq, inputs=List(x36469, Const(2.5))).name("x36472").ctrl(x36484).srcCtx("CellsPar.scala:54:52") // FixLeq(x36469,Const(2.5))
    val x36473 = OpDef(op=BitAnd, inputs=List(x36471, x36472)).name("x36473").ctrl(x36484).srcCtx("CellsPar.scala:54:43:cond2") // And(x36471,x36472)
    val x36474 = OpDef(op=FixSra, inputs=List(x36469, Const(2))).name("x36474").ctrl(x36484).srcCtx("CellsPar.scala:56:21:div4") // FixRsh(x36469,Const(2))
    val x36475 = OpDef(op=FixAdd, inputs=List(x36474, Const(0.375))).name("x36475").ctrl(x36484).srcCtx("CellsPar.scala:57:27:div4Offset") // FixAdd(x36474,Const(0.375))
    val x36476 = OpDef(op=MuxOp, inputs=List(x36473, x36475, x36469)).name("x36476").ctrl(x36484).srcCtx("CellsPar.scala:58:20:body2") // Mux(x36473,x36475,x36469)
    val x36477 = OpDef(op=MuxOp, inputs=List(x36470, Const(1.0), x36476)).name("x36477").ctrl(x36484).srcCtx("CellsPar.scala:60:20:absre") // Mux(x36470,Const(1),x36476)
    val x36478 = OpDef(op=FixNeg, inputs=List(x36477)).name("x36478").ctrl(x36484).srcCtx("CellsPar.scala:62:29:mabre") // FixNeg(x36477)
    val x36479 = OpDef(op=FixLt, inputs=List(x36468, Const(0.0))).name("x36479").ctrl(x36484).srcCtx("CellsPar.scala:63:22") // FixLt(x36468,Const(0))
    val x36480 = OpDef(op=MuxOp, inputs=List(x36479, x36478, x36477)).name("x36480").ctrl(x36484).srcCtx("CellsPar.scala:63:17:re") // Mux(x36479,x36478,x36477)
    val x36481 = x36480 // FixConvert(x36480,TRUE,_8,_24) (Same Type. No op)
    val x36482 = OpDef(op=MuxOp, inputs=List(x36465, x36481, x36464)).name("x36482").ctrl(x36484).srcCtx("CellsPar.scala:218:43") // Mux(x36465,x36481,x36464)
    val x36483 = StoreBanks(List(x34701_d0_b0, x34701_d1_b0), List(b24119, x36453), x36482).name("x36483").ctrl(x36484).srcCtx("CellsPar.scala:218:38") // SRAMStore(x34701,ArrayBuffer(Const(1), Const(512)),List(b24119, x36453),Const(0),x36482,x36460)
    val x36488 = Counter(min=Const(0), max=Const(512), step=Const(16), par=1).name("x36488").ctrl(x36742).srcCtx("CellsPar.scala:190:58") // CounterNew(Const(0),Const(512),Const(16),Const(1))
    val x36489 = Counter(min=Const(0), max=Const(1536), step=Const(16), par=1).name("x36489").ctrl(x36742).srcCtx("CellsPar.scala:190:33") // CounterNew(Const(0),Const(1536),Const(16),Const(1))
    val x36490 = CounterChain(List(x36489,x36488)).name("x36490").ctrl(x36742).srcCtx("CellsPar.scala:190:72") // CounterChainNew(List(x36489, x36488))
    val x36596 = LoopController(style=MetaPipe, level=OuterControl, cchain=x36490).name("x36596").ctrl(x36742).srcCtx("CellsPar.scala:190:72") // UnrolledForeach(List(b22320),x36490,Block(Const(())),List(List(b24194), List(b24195)),List(List(b24196), List(b24197)))
    val b24194 = CounterIter(x36489, Some(0)).name("b24194").ctrl(x36596) // b24194
    val b24196 = Const(true).name("b24196").ctrl(x36596) // b24196
    val b24195 = CounterIter(x36488, Some(0)).name("b24195").ctrl(x36596) // b24195
    val b24197 = Const(true).name("b24197").ctrl(x36596) // b24197
    val x36491_d0_b0 = SRAM(size=256, banking=Strided(banks=1, stride=16)).name("x36491_d0_b0").ctrl(x36596).srcCtx("CellsPar.scala:191:33:tileKernel") // x36491 = SRAMNew(ArrayBuffer(Const(16), Const(16)))
    isAccum(x36491_d0_b0) = false
    bufferDepthOf(x36491_d0_b0) = 2
    val x36494 = UnitController(style=SeqPipe, level=InnerControl).name("x36494").ctrl(x36596).srcCtx("CellsPar.scala:190:72") // UnitPipe(List(b24196, b24197, b22320),Block(Const(())))
    val x36492 = OpDef(op=FixAdd, inputs=List(b24194, Const(16))).name("x36492").ctrl(x36494).srcCtx("CellsPar.scala:192:36") // FixAdd(b24194,Const(16))
    val x36493 = OpDef(op=FixAdd, inputs=List(b24195, Const(16))).name("x36493").ctrl(x36494).srcCtx("CellsPar.scala:192:45") // FixAdd(b24195,Const(16))
    val x36495 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x36495").ctrl(x36596).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x36496 = CounterChain(List(x36495)).name("x36496").ctrl(x36596).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x36495))
    val x36525 = LoopController(style=StreamPipe, level=OuterControl, cchain=x36496).name("x36525").ctrl(x36596).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b24196, b24197, b22320),x36496,Block(Const(())),List(List(b24204)),List(List(b24205)))
    val b24204 = CounterIter(x36495, Some(0)).name("b24204").ctrl(x36525) // b24204
    val b24205 = Const(true).name("b24205").ctrl(x36525) // b24205
    val b36885 = StreamOut(field="offset").name("b36885").ctrl(x36525).srcCtx("CellsPar.scala:192:20") // x36497 = StreamOutNew(BurstCmdBus)
    isAccum(b36885) = false
    bufferDepthOf(b36885) = 1
    val b36886 = StreamOut(field="size").name("b36886").ctrl(x36525).srcCtx("CellsPar.scala:192:20") // x36497 = StreamOutNew(BurstCmdBus)
    isAccum(b36886) = false
    bufferDepthOf(b36886) = 1
    val x36498 = StreamIn(field="data").name("x36498").ctrl(x36525).srcCtx("CellsPar.scala:192:20") // x36498 = StreamInNew(BurstDataBus())
    isAccum(x36498) = false
    bufferDepthOf(x36498) = 1
    val x36513 = UnitController(style=SeqPipe, level=InnerControl).name("x36513").ctrl(x36525).srcCtx("CellsPar.scala:192:20") // UnitPipe(List(b24205, b24196, b24197, b22320),Block(x36512))
    val x36499 = OpDef(op=FixAdd, inputs=List(b24194, b24204)).name("x36499").ctrl(x36513).srcCtx("CellsPar.scala:192:20") // FixAdd(b24194,b24204)
    val x36500 = x36499 // FixConvert(x36499,TRUE,_32,_0) (Same Type. No op)
    val x36501 = OpDef(op=FixSla, inputs=List(x36500, Const(9))).name("x36501").ctrl(x36513).srcCtx("CellsPar.scala:192:20") // FixLsh(x36500,Const(9))
    val x36502 = b24195 // FixConvert(b24195,TRUE,_32,_0) (Same Type. No op)
    val x36503 = OpDef(op=FixAdd, inputs=List(x36501, x36502)).name("x36503").ctrl(x36513).srcCtx("CellsPar.scala:192:20") // FixAdd(x36501,x36502)
    val x36504 = OpDef(op=FixSla, inputs=List(x36503, Const(2))).name("x36504").ctrl(x36513).srcCtx("CellsPar.scala:192:20") // FixLsh(x36503,Const(2))
    val x36505 = x36504 // FixConvert(x36504,TRUE,_64,_0)
    val x36506 = DramAddress(x34630).name("x36506").ctrl(x36513).srcCtx("CellsPar.scala:192:20") // GetDRAMAddress(x34630)
    val x36508_x36507 = OpDef(op=FixAdd, inputs=List(x36505, x36506)).name("x36508_x36507").ctrl(x36513).srcCtx("CellsPar.scala:192:20") // FixAdd(x36505,x36506)
    // x36508 = SimpleStruct(ArrayBuffer((offset,x36507), (size,Const(64)), (isLoad,Const(true))))
    val x36509 = OpDef(op=BitAnd, inputs=List(b24205, b24196)).name("x36509").ctrl(x36513).srcCtx("UnrollingBase.scala:28:66") // And(b24205,b24196)
    val x36510 = OpDef(op=BitAnd, inputs=List(b24197, b22320)).name("x36510").ctrl(x36513).srcCtx("UnrollingBase.scala:28:66") // And(b24197,b22320)
    val x36511 = OpDef(op=BitAnd, inputs=List(x36509, x36510)).name("x36511").ctrl(x36513).srcCtx("UnrollingBase.scala:28:66") // And(x36509,x36510)
    val x36512_b36887_b36885 = WriteMem(b36885, x36508_x36507).name("x36512_b36887_b36885").ctrl(x36513).srcCtx("CellsPar.scala:192:20") // StreamWrite(x36497,x36508,x36511)
    val x36512_b36888_b36886 = WriteMem(b36886, Const(64)).name("x36512_b36888_b36886").ctrl(x36513).srcCtx("CellsPar.scala:192:20") // StreamWrite(x36497,x36508,x36511)
    val x36514 = FringeDenseLoad(dram=List(x34630), cmdStream=List(b36885, b36886), dataStream=List(x36498)).name("x36514").ctrl(x36525).srcCtx("CellsPar.scala:192:20") // FringeDenseLoad(x34630,x36497,x36498)
    val x36515 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x36515").ctrl(x36525).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x36516 = CounterChain(List(x36515)).name("x36516").ctrl(x36525).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x36515))
    val x36524 = LoopController(style=InnerPipe, level=InnerControl, cchain=x36516).name("x36524").ctrl(x36525).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b24205, b24196, b24197, b22320),x36516,Block(Const(())),List(List(b24226)),List(List(b24227)))
    val b24226 = CounterIter(x36515, None).name("b24226").ctrl(x36524) // b24226
    val b24227 = Const(true).name("b24227").ctrl(x36524) // b24227
    val x36517 = OpDef(op=BitAnd, inputs=List(b24227, b24205)).name("x36517").ctrl(x36524).srcCtx("UnrollingBase.scala:28:66") // And(b24227,b24205)
    val x36518 = OpDef(op=BitAnd, inputs=List(b24196, b24197)).name("x36518").ctrl(x36524).srcCtx("UnrollingBase.scala:28:66") // And(b24196,b24197)
    val x36519 = OpDef(op=BitAnd, inputs=List(x36517, x36518)).name("x36519").ctrl(x36524).srcCtx("UnrollingBase.scala:28:66") // And(x36517,x36518)
    val x36520 = OpDef(op=BitAnd, inputs=List(x36519, b22320)).name("x36520").ctrl(x36524).srcCtx("UnrollingBase.scala:28:66") // And(x36519,b22320)
    val x36521_x36521 = ReadMem(x36498).name("x36521_x36521").ctrl(x36524).srcCtx("CellsPar.scala:192:20") // ParStreamRead(x36498,List(x36520))
    val x36522_x36522 = x36521_x36521 // x36522 = VectorApply(x36521,0)
    val x36523 = StoreBanks(List(x36491_d0_b0), List(b24204, b24226), x36522_x36522).name("x36523").ctrl(x36524).srcCtx("CellsPar.scala:192:20") // ParSRAMStore(x36491,List(List(b24204, b24226)),List(x36522),List(x36520))
    val x36526 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x36526").ctrl(x36596).srcCtx("CellsPar.scala:193:34") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x36527 = CounterChain(List(x36526)).name("x36527").ctrl(x36596).srcCtx("CellsPar.scala:193:46") // CounterChainNew(List(x36526))
    val x36595 = LoopController(style=MetaPipe, level=OuterControl, cchain=x36527).name("x36595").ctrl(x36596).srcCtx("CellsPar.scala:193:46") // UnrolledForeach(List(b24196, b24197, b22320),x36527,Block(Const(())),List(List(b24239)),List(List(b24240)))
    val b24239 = CounterIter(x36526, Some(0)).name("b24239").ctrl(x36595) // b24239
    val b24240 = Const(true).name("b24240").ctrl(x36595) // b24240
    val x36528 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x36528").ctrl(x36595).srcCtx("CellsPar.scala:194:28") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x36529 = CounterChain(List(x36528)).name("x36529").ctrl(x36595).srcCtx("CellsPar.scala:194:42") // CounterChainNew(List(x36528))
    val x36594 = LoopController(style=MetaPipe, level=OuterControl, cchain=x36529).name("x36594").ctrl(x36595).srcCtx("CellsPar.scala:194:42") // UnrolledForeach(List(b24240, b24196, b24197, b22320),x36529,Block(Const(())),List(List(b24243)),List(List(b24244)))
    val b24243 = CounterIter(x36528, Some(0)).name("b24243").ctrl(x36594) // b24243
    val b24244 = Const(true).name("b24244").ctrl(x36594) // b24244
    val x36530_d0 = Reg(init=Some(0.0)).name("x36530_d0").ctrl(x36594).srcCtx("CellsPar.scala:195:34:prod") // x36530 = RegNew(Const(0))
    isAccum(x36530_d0) = false
    bufferDepthOf(x36530_d0) = 2
    val x36530_d1 = Reg(init=Some(0.0)).name("x36530_d1").ctrl(x36594).srcCtx("CellsPar.scala:195:34:prod") // x36530 = RegNew(Const(0))
    isAccum(x36530_d1) = true
    bufferDepthOf(x36530_d1) = 1
    val x36531 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x36531").ctrl(x36594).srcCtx("CellsPar.scala:195:43") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x36532 = CounterChain(List(x36531)).name("x36532").ctrl(x36594).srcCtx("CellsPar.scala:207:15") // CounterChainNew(List(x36531))
    val x36558 = LoopController(style=InnerPipe, level=InnerControl, cchain=x36532).name("x36558").ctrl(x36594).srcCtx("CellsPar.scala:207:15") // UnrolledReduce(List(b24244, b24240, b24196, b24197, b22320),x36532,x36530,Block((x36530) => Const(())),List(List(b24248)),List(List(b24249)))
    val b24248 = CounterIter(x36531, None).name("b24248").ctrl(x36558) // b24248
    val b24249 = Const(true).name("b24249").ctrl(x36558) // b24249
    val x36533 = OpDef(op=FixAdd, inputs=List(b24194, b24248)).name("x36533").ctrl(x36558).srcCtx("CellsPar.scala:196:42:reduce_size_offset") // FixAdd(b24194,b24248)
    val x36534 = OpDef(op=BitAnd, inputs=List(b24249, b24244)).name("x36534").ctrl(x36558).srcCtx("UnrollingBase.scala:28:66") // And(b24249,b24244)
    val x36535 = OpDef(op=BitAnd, inputs=List(b24240, b24196)).name("x36535").ctrl(x36558).srcCtx("UnrollingBase.scala:28:66") // And(b24240,b24196)
    val x36536 = OpDef(op=BitAnd, inputs=List(b24197, b22320)).name("x36536").ctrl(x36558).srcCtx("UnrollingBase.scala:28:66") // And(b24197,b22320)
    val x36537 = OpDef(op=BitAnd, inputs=List(x36534, x36535)).name("x36537").ctrl(x36558).srcCtx("UnrollingBase.scala:28:66") // And(x36534,x36535)
    val x36538 = OpDef(op=BitAnd, inputs=List(x36537, x36536)).name("x36538").ctrl(x36558).srcCtx("UnrollingBase.scala:28:66") // And(x36537,x36536)
    val x36539 = LoadBanks(List(x36491_d0_b0), List(b24248, b24243)).name("x36539").ctrl(x36558).srcCtx("CellsPar.scala:203:34:tk") // ParSRAMLoad(x36491,List(List(b24248, b24243)),List(x36538))
    val x36540 = x36539 // x36540 = VectorApply(x36539,0)
    val x36541 = LoadBanks(List(x34691_d1_b0), List(b24239, x36533)).name("x36541").ctrl(x36558).srcCtx("CellsPar.scala:204:28") // ParSRAMLoad(x34691,List(List(b24239, x36533)),List(x36538))
    val x36542 = x36541 // x36542 = VectorApply(x36541,0)
    val x36543 = OpDef(op=FixMul, inputs=List(x36542, x36540)).name("x36543").ctrl(x36558).srcCtx("CellsPar.scala:204:52:tempA") // FixMul(x36542,x36540)
    val x36544 = OpDef(op=FixSub, inputs=List(x36533, Const(512))).name("x36544").ctrl(x36558).srcCtx("CellsPar.scala:205:51") // FixSub(x36533,Const(512))
    val x36545 = LoadBanks(List(x34698_d2_b0), List(b24239, x36544)).name("x36545").ctrl(x36558).srcCtx("CellsPar.scala:205:28") // ParSRAMLoad(x34698,List(List(b24239, x36544)),List(x36538))
    val x36546 = x36545 // x36546 = VectorApply(x36545,0)
    val x36547 = OpDef(op=FixMul, inputs=List(x36546, x36540)).name("x36547").ctrl(x36558).srcCtx("CellsPar.scala:205:67:tempB") // FixMul(x36546,x36540)
    val x36548 = OpDef(op=FixLt, inputs=List(x36533, Const(512))).name("x36548").ctrl(x36558).srcCtx("CellsPar.scala:206:38") // FixLt(x36533,Const(512))
    val x36549 = OpDef(op=MuxOp, inputs=List(x36548, x36543, x36547)).name("x36549").ctrl(x36558).srcCtx("CellsPar.scala:206:18") // Mux(x36548,x36543,x36547)
    val x36550 = ReadMem(x36530_d1).name("x36550").ctrl(x36558).srcCtx("CellsPar.scala:207:15") // RegRead(x36530)
    val x36551 = OpDef(op=FixEql, inputs=List(b24248, Const(0))).name("x36551").ctrl(x36558).srcCtx("CellsPar.scala:207:15") // FixEql(b24248,Const(0))
    val x36552 = ReduceAccumOp(op=FixAdd, input=x36549, accum=x36550).name("x36552").ctrl(x36558).srcCtx("CellsPar.scala:207:17") // FixAdd(x36549,x36550)
    val x36553 = OpDef(op=BitAnd, inputs=List(b24244, b24240)).name("x36553").ctrl(x36558).srcCtx("UnrollingBase.scala:28:66") // And(b24244,b24240)
    val x36554 = OpDef(op=BitAnd, inputs=List(b24196, b24197)).name("x36554").ctrl(x36558).srcCtx("UnrollingBase.scala:28:66") // And(b24196,b24197)
    val x36555 = OpDef(op=BitAnd, inputs=List(x36553, x36554)).name("x36555").ctrl(x36558).srcCtx("UnrollingBase.scala:28:66") // And(x36553,x36554)
    val x36556 = OpDef(op=BitAnd, inputs=List(x36555, b22320)).name("x36556").ctrl(x36558).srcCtx("UnrollingBase.scala:28:66") // And(x36555,b22320)
    val x36557_x36530_d0 = WriteMem(x36530_d0, x36552).name("x36557_x36530_d0").ctrl(x36558).srcCtx("CellsPar.scala:207:15") // RegWrite(x36530,x36552,x36556)
    val x36557_x36530_d1 = WriteMem(x36530_d1, x36552).name("x36557_x36530_d1").ctrl(x36558).srcCtx("CellsPar.scala:207:15") // RegWrite(x36530,x36552,x36556)
    val x36593 = UnitController(style=SeqPipe, level=InnerControl).name("x36593").ctrl(x36594).srcCtx("CellsPar.scala:194:42") // UnitPipe(List(b24244, b24240, b24196, b24197, b22320),Block(Const(())))
    val x36559 = OpDef(op=FixAdd, inputs=List(b24195, b24243)).name("x36559").ctrl(x36593).srcCtx("CellsPar.scala:209:31:colOffset") // FixAdd(b24195,b24243)
    val x36560 = ReadMem(x36530_d0).name("x36560").ctrl(x36593).srcCtx("CellsPar.scala:210:28") // RegRead(x36530)
    val x36561 = OpDef(op=FixEql, inputs=List(b24194, Const(0))).name("x36561").ctrl(x36593).srcCtx("CellsPar.scala:210:42") // FixEql(b24194,Const(0))
    val x36562 = OpDef(op=FixAdd, inputs=List(x36559, Const(1024))).name("x36562").ctrl(x36593).srcCtx("CellsPar.scala:210:66") // FixAdd(x36559,Const(1024))
    val x36563 = OpDef(op=BitAnd, inputs=List(b24244, b24240)).name("x36563").ctrl(x36593).srcCtx("UnrollingBase.scala:28:66") // And(b24244,b24240)
    val x36564 = OpDef(op=BitAnd, inputs=List(b24196, b24197)).name("x36564").ctrl(x36593).srcCtx("UnrollingBase.scala:28:66") // And(b24196,b24197)
    val x36565 = OpDef(op=BitAnd, inputs=List(x36563, x36564)).name("x36565").ctrl(x36593).srcCtx("UnrollingBase.scala:28:66") // And(x36563,x36564)
    val x36566 = OpDef(op=BitAnd, inputs=List(x36565, b22320)).name("x36566").ctrl(x36593).srcCtx("UnrollingBase.scala:28:66") // And(x36565,b22320)
    val x36567 = LoadBanks(List(x34704_d1_b0), List(Const(0), x36562)).name("x36567").ctrl(x36593).srcCtx("CellsPar.scala:210:52") // SRAMLoad(x34704,ArrayBuffer(Const(1), Const(2048)),List(Const(0), x36562),Const(0),x36566)
    val x36568 = LoadBanks(List(x34702_d1_b0), List(b24239, x36559)).name("x36568").ctrl(x36593).srcCtx("CellsPar.scala:210:91") // SRAMLoad(x34702,ArrayBuffer(Const(1), Const(512)),List(b24239, x36559),Const(0),x36566)
    val x36569 = OpDef(op=MuxOp, inputs=List(x36561, x36567, x36568)).name("x36569").ctrl(x36593).srcCtx("CellsPar.scala:210:39") // Mux(x36561,x36567,x36568)
    val x36570 = OpDef(op=FixAdd, inputs=List(x36560, x36569)).name("x36570").ctrl(x36593).srcCtx("CellsPar.scala:210:34:ele") // FixAdd(x36560,x36569)
    val x36571 = OpDef(op=FixLeq, inputs=List(Const(1520), b24194)).name("x36571").ctrl(x36593).srcCtx("CellsPar.scala:218:46") // FixLeq(Const(1520),b24194)
    // x36572 = FixConvert(Const(1),TRUE,_8,_24) x.tp=FixPt[TRUE,_32,_0] {
    val x36572_int1 = OpDef(op=BitAnd, inputs=List(Const(1), Const("01111111111111111111111111111111"))).name("x36572_int1").ctrl(x36593).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(1),TRUE,_8,_24)
    val x36572_int2 = OpDef(op=FixSla, inputs=List(x36572_int1, Const(24))).name("x36572_int2").ctrl(x36593).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(1),TRUE,_8,_24)
    val x36572_frac1 = OpDef(op=BitAnd, inputs=List(Const(1), Const("00000000000000000000000000000000"))).name("x36572_frac1").ctrl(x36593).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(1),TRUE,_8,_24)
    val x36572_frac2 = OpDef(op=FixSla, inputs=List(x36572_frac1, Const(24))).name("x36572_frac2").ctrl(x36593).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(1),TRUE,_8,_24)
    val x36572 = OpDef(op=BitOr, inputs=List(x36572_int2, x36572_frac2)).name("x36572").ctrl(x36593).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(1),TRUE,_8,_24)
    // }
    val x36573 = OpDef(op=FixAdd, inputs=List(x36570, x36572)).name("x36573").ctrl(x36593).srcCtx("CellsPar.scala:218:87") // FixAdd(x36570,x36572)
    val x36574 = OpDef(op=FixSra, inputs=List(x36573, Const(1))).name("x36574").ctrl(x36593).srcCtx("CellsPar.scala:29:22") // FixRsh(x36573,Const(1))
    val x36575 = x36574 // FixConvert(x36574,TRUE,_8,_24) (Same Type. No op)
    val x36576 = OpDef(op=FixAbs, inputs=List(x36575)).name("x36576").ctrl(x36593).srcCtx("CellsPar.scala:37:19:absp") // FixAbs(x36575)
    val x36577 = OpDef(op=FixLt, inputs=List(Const(2.5), x36576)).name("x36577").ctrl(x36593).srcCtx("CellsPar.scala:51:23:cond1") // FixLt(Const(2.5),x36576)
    val x36578 = OpDef(op=FixLt, inputs=List(Const(0.5), x36576)).name("x36578").ctrl(x36593).srcCtx("CellsPar.scala:54:35") // FixLt(Const(0.5),x36576)
    val x36579 = OpDef(op=FixLeq, inputs=List(x36576, Const(2.5))).name("x36579").ctrl(x36593).srcCtx("CellsPar.scala:54:52") // FixLeq(x36576,Const(2.5))
    val x36580 = OpDef(op=BitAnd, inputs=List(x36578, x36579)).name("x36580").ctrl(x36593).srcCtx("CellsPar.scala:54:43:cond2") // And(x36578,x36579)
    val x36581 = OpDef(op=FixSra, inputs=List(x36576, Const(2))).name("x36581").ctrl(x36593).srcCtx("CellsPar.scala:56:21:div4") // FixRsh(x36576,Const(2))
    val x36582 = OpDef(op=FixAdd, inputs=List(x36581, Const(0.375))).name("x36582").ctrl(x36593).srcCtx("CellsPar.scala:57:27:div4Offset") // FixAdd(x36581,Const(0.375))
    val x36583 = OpDef(op=MuxOp, inputs=List(x36580, x36582, x36576)).name("x36583").ctrl(x36593).srcCtx("CellsPar.scala:58:20:body2") // Mux(x36580,x36582,x36576)
    val x36584 = OpDef(op=MuxOp, inputs=List(x36577, Const(1.0), x36583)).name("x36584").ctrl(x36593).srcCtx("CellsPar.scala:60:20:absre") // Mux(x36577,Const(1),x36583)
    val x36585 = OpDef(op=FixNeg, inputs=List(x36584)).name("x36585").ctrl(x36593).srcCtx("CellsPar.scala:62:29:mabre") // FixNeg(x36584)
    val x36586 = OpDef(op=FixLt, inputs=List(x36575, Const(0.0))).name("x36586").ctrl(x36593).srcCtx("CellsPar.scala:63:22") // FixLt(x36575,Const(0))
    val x36587 = OpDef(op=MuxOp, inputs=List(x36586, x36585, x36584)).name("x36587").ctrl(x36593).srcCtx("CellsPar.scala:63:17:re") // Mux(x36586,x36585,x36584)
    val x36588 = x36587 // FixConvert(x36587,TRUE,_8,_24) (Same Type. No op)
    val x36589 = OpDef(op=FixAdd, inputs=List(x36588, Const(1.0))).name("x36589").ctrl(x36593).srcCtx("CellsPar.scala:29:33") // FixAdd(x36588,Const(1))
    val x36590 = OpDef(op=FixSra, inputs=List(x36589, Const(1))).name("x36590").ctrl(x36593).srcCtx("CellsPar.scala:29:44") // FixRsh(x36589,Const(1))
    val x36591 = OpDef(op=MuxOp, inputs=List(x36571, x36590, x36570)).name("x36591").ctrl(x36593).srcCtx("CellsPar.scala:218:43") // Mux(x36571,x36590,x36570)
    val x36592 = StoreBanks(List(x34702_d0_b0, x34702_d1_b0), List(b24239, x36559), x36591).name("x36592").ctrl(x36593).srcCtx("CellsPar.scala:218:38") // SRAMStore(x34702,ArrayBuffer(Const(1), Const(512)),List(b24239, x36559),Const(0),x36591,x36566)
    val x36597 = Counter(min=Const(0), max=Const(512), step=Const(16), par=1).name("x36597").ctrl(x36742).srcCtx("CellsPar.scala:190:58") // CounterNew(Const(0),Const(512),Const(16),Const(1))
    val x36598 = Counter(min=Const(0), max=Const(1536), step=Const(16), par=1).name("x36598").ctrl(x36742).srcCtx("CellsPar.scala:190:33") // CounterNew(Const(0),Const(1536),Const(16),Const(1))
    val x36599 = CounterChain(List(x36598,x36597)).name("x36599").ctrl(x36742).srcCtx("CellsPar.scala:190:72") // CounterChainNew(List(x36598, x36597))
    val x36705 = LoopController(style=MetaPipe, level=OuterControl, cchain=x36599).name("x36705").ctrl(x36742).srcCtx("CellsPar.scala:190:72") // UnrolledForeach(List(b22320),x36599,Block(Const(())),List(List(b24317), List(b24318)),List(List(b24319), List(b24320)))
    val b24317 = CounterIter(x36598, Some(0)).name("b24317").ctrl(x36705) // b24317
    val b24319 = Const(true).name("b24319").ctrl(x36705) // b24319
    val b24318 = CounterIter(x36597, Some(0)).name("b24318").ctrl(x36705) // b24318
    val b24320 = Const(true).name("b24320").ctrl(x36705) // b24320
    val x36600_d0_b0 = SRAM(size=256, banking=Strided(banks=1, stride=16)).name("x36600_d0_b0").ctrl(x36705).srcCtx("CellsPar.scala:191:33:tileKernel") // x36600 = SRAMNew(ArrayBuffer(Const(16), Const(16)))
    isAccum(x36600_d0_b0) = false
    bufferDepthOf(x36600_d0_b0) = 2
    val x36603 = UnitController(style=SeqPipe, level=InnerControl).name("x36603").ctrl(x36705).srcCtx("CellsPar.scala:190:72") // UnitPipe(List(b24319, b24320, b22320),Block(Const(())))
    val x36601 = OpDef(op=FixAdd, inputs=List(b24317, Const(16))).name("x36601").ctrl(x36603).srcCtx("CellsPar.scala:192:36") // FixAdd(b24317,Const(16))
    val x36602 = OpDef(op=FixAdd, inputs=List(b24318, Const(16))).name("x36602").ctrl(x36603).srcCtx("CellsPar.scala:192:45") // FixAdd(b24318,Const(16))
    val x36604 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x36604").ctrl(x36705).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x36605 = CounterChain(List(x36604)).name("x36605").ctrl(x36705).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x36604))
    val x36634 = LoopController(style=StreamPipe, level=OuterControl, cchain=x36605).name("x36634").ctrl(x36705).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b24319, b24320, b22320),x36605,Block(Const(())),List(List(b24327)),List(List(b24328)))
    val b24327 = CounterIter(x36604, Some(0)).name("b24327").ctrl(x36634) // b24327
    val b24328 = Const(true).name("b24328").ctrl(x36634) // b24328
    val b36889 = StreamOut(field="offset").name("b36889").ctrl(x36634).srcCtx("CellsPar.scala:192:20") // x36606 = StreamOutNew(BurstCmdBus)
    isAccum(b36889) = false
    bufferDepthOf(b36889) = 1
    val b36890 = StreamOut(field="size").name("b36890").ctrl(x36634).srcCtx("CellsPar.scala:192:20") // x36606 = StreamOutNew(BurstCmdBus)
    isAccum(b36890) = false
    bufferDepthOf(b36890) = 1
    val x36607 = StreamIn(field="data").name("x36607").ctrl(x36634).srcCtx("CellsPar.scala:192:20") // x36607 = StreamInNew(BurstDataBus())
    isAccum(x36607) = false
    bufferDepthOf(x36607) = 1
    val x36622 = UnitController(style=SeqPipe, level=InnerControl).name("x36622").ctrl(x36634).srcCtx("CellsPar.scala:192:20") // UnitPipe(List(b24328, b24319, b24320, b22320),Block(x36621))
    val x36608 = OpDef(op=FixAdd, inputs=List(b24317, b24327)).name("x36608").ctrl(x36622).srcCtx("CellsPar.scala:192:20") // FixAdd(b24317,b24327)
    val x36609 = x36608 // FixConvert(x36608,TRUE,_32,_0) (Same Type. No op)
    val x36610 = OpDef(op=FixSla, inputs=List(x36609, Const(9))).name("x36610").ctrl(x36622).srcCtx("CellsPar.scala:192:20") // FixLsh(x36609,Const(9))
    val x36611 = b24318 // FixConvert(b24318,TRUE,_32,_0) (Same Type. No op)
    val x36612 = OpDef(op=FixAdd, inputs=List(x36610, x36611)).name("x36612").ctrl(x36622).srcCtx("CellsPar.scala:192:20") // FixAdd(x36610,x36611)
    val x36613 = OpDef(op=FixSla, inputs=List(x36612, Const(2))).name("x36613").ctrl(x36622).srcCtx("CellsPar.scala:192:20") // FixLsh(x36612,Const(2))
    val x36614 = x36613 // FixConvert(x36613,TRUE,_64,_0)
    val x36615 = DramAddress(x34631).name("x36615").ctrl(x36622).srcCtx("CellsPar.scala:192:20") // GetDRAMAddress(x34631)
    val x36617_x36616 = OpDef(op=FixAdd, inputs=List(x36614, x36615)).name("x36617_x36616").ctrl(x36622).srcCtx("CellsPar.scala:192:20") // FixAdd(x36614,x36615)
    // x36617 = SimpleStruct(ArrayBuffer((offset,x36616), (size,Const(64)), (isLoad,Const(true))))
    val x36618 = OpDef(op=BitAnd, inputs=List(b24328, b24319)).name("x36618").ctrl(x36622).srcCtx("UnrollingBase.scala:28:66") // And(b24328,b24319)
    val x36619 = OpDef(op=BitAnd, inputs=List(b24320, b22320)).name("x36619").ctrl(x36622).srcCtx("UnrollingBase.scala:28:66") // And(b24320,b22320)
    val x36620 = OpDef(op=BitAnd, inputs=List(x36618, x36619)).name("x36620").ctrl(x36622).srcCtx("UnrollingBase.scala:28:66") // And(x36618,x36619)
    val x36621_b36891_b36889 = WriteMem(b36889, x36617_x36616).name("x36621_b36891_b36889").ctrl(x36622).srcCtx("CellsPar.scala:192:20") // StreamWrite(x36606,x36617,x36620)
    val x36621_b36892_b36890 = WriteMem(b36890, Const(64)).name("x36621_b36892_b36890").ctrl(x36622).srcCtx("CellsPar.scala:192:20") // StreamWrite(x36606,x36617,x36620)
    val x36623 = FringeDenseLoad(dram=List(x34631), cmdStream=List(b36889, b36890), dataStream=List(x36607)).name("x36623").ctrl(x36634).srcCtx("CellsPar.scala:192:20") // FringeDenseLoad(x34631,x36606,x36607)
    val x36624 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x36624").ctrl(x36634).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x36625 = CounterChain(List(x36624)).name("x36625").ctrl(x36634).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x36624))
    val x36633 = LoopController(style=InnerPipe, level=InnerControl, cchain=x36625).name("x36633").ctrl(x36634).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b24328, b24319, b24320, b22320),x36625,Block(Const(())),List(List(b24349)),List(List(b24350)))
    val b24349 = CounterIter(x36624, None).name("b24349").ctrl(x36633) // b24349
    val b24350 = Const(true).name("b24350").ctrl(x36633) // b24350
    val x36626 = OpDef(op=BitAnd, inputs=List(b24350, b24328)).name("x36626").ctrl(x36633).srcCtx("UnrollingBase.scala:28:66") // And(b24350,b24328)
    val x36627 = OpDef(op=BitAnd, inputs=List(b24319, b24320)).name("x36627").ctrl(x36633).srcCtx("UnrollingBase.scala:28:66") // And(b24319,b24320)
    val x36628 = OpDef(op=BitAnd, inputs=List(x36626, x36627)).name("x36628").ctrl(x36633).srcCtx("UnrollingBase.scala:28:66") // And(x36626,x36627)
    val x36629 = OpDef(op=BitAnd, inputs=List(x36628, b22320)).name("x36629").ctrl(x36633).srcCtx("UnrollingBase.scala:28:66") // And(x36628,b22320)
    val x36630_x36630 = ReadMem(x36607).name("x36630_x36630").ctrl(x36633).srcCtx("CellsPar.scala:192:20") // ParStreamRead(x36607,List(x36629))
    val x36631_x36631 = x36630_x36630 // x36631 = VectorApply(x36630,0)
    val x36632 = StoreBanks(List(x36600_d0_b0), List(b24327, b24349), x36631_x36631).name("x36632").ctrl(x36633).srcCtx("CellsPar.scala:192:20") // ParSRAMStore(x36600,List(List(b24327, b24349)),List(x36631),List(x36629))
    val x36635 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x36635").ctrl(x36705).srcCtx("CellsPar.scala:193:34") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x36636 = CounterChain(List(x36635)).name("x36636").ctrl(x36705).srcCtx("CellsPar.scala:193:46") // CounterChainNew(List(x36635))
    val x36704 = LoopController(style=MetaPipe, level=OuterControl, cchain=x36636).name("x36704").ctrl(x36705).srcCtx("CellsPar.scala:193:46") // UnrolledForeach(List(b24319, b24320, b22320),x36636,Block(Const(())),List(List(b24362)),List(List(b24363)))
    val b24362 = CounterIter(x36635, Some(0)).name("b24362").ctrl(x36704) // b24362
    val b24363 = Const(true).name("b24363").ctrl(x36704) // b24363
    val x36637 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x36637").ctrl(x36704).srcCtx("CellsPar.scala:194:28") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x36638 = CounterChain(List(x36637)).name("x36638").ctrl(x36704).srcCtx("CellsPar.scala:194:42") // CounterChainNew(List(x36637))
    val x36703 = LoopController(style=MetaPipe, level=OuterControl, cchain=x36638).name("x36703").ctrl(x36704).srcCtx("CellsPar.scala:194:42") // UnrolledForeach(List(b24363, b24319, b24320, b22320),x36638,Block(Const(())),List(List(b24366)),List(List(b24367)))
    val b24366 = CounterIter(x36637, Some(0)).name("b24366").ctrl(x36703) // b24366
    val b24367 = Const(true).name("b24367").ctrl(x36703) // b24367
    val x36639_d0 = Reg(init=Some(0.0)).name("x36639_d0").ctrl(x36703).srcCtx("CellsPar.scala:195:34:prod") // x36639 = RegNew(Const(0))
    isAccum(x36639_d0) = false
    bufferDepthOf(x36639_d0) = 2
    val x36639_d1 = Reg(init=Some(0.0)).name("x36639_d1").ctrl(x36703).srcCtx("CellsPar.scala:195:34:prod") // x36639 = RegNew(Const(0))
    isAccum(x36639_d1) = true
    bufferDepthOf(x36639_d1) = 1
    val x36640 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x36640").ctrl(x36703).srcCtx("CellsPar.scala:195:43") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x36641 = CounterChain(List(x36640)).name("x36641").ctrl(x36703).srcCtx("CellsPar.scala:207:15") // CounterChainNew(List(x36640))
    val x36667 = LoopController(style=InnerPipe, level=InnerControl, cchain=x36641).name("x36667").ctrl(x36703).srcCtx("CellsPar.scala:207:15") // UnrolledReduce(List(b24367, b24363, b24319, b24320, b22320),x36641,x36639,Block((x36639) => Const(())),List(List(b24371)),List(List(b24372)))
    val b24371 = CounterIter(x36640, None).name("b24371").ctrl(x36667) // b24371
    val b24372 = Const(true).name("b24372").ctrl(x36667) // b24372
    val x36642 = OpDef(op=FixAdd, inputs=List(b24317, b24371)).name("x36642").ctrl(x36667).srcCtx("CellsPar.scala:196:42:reduce_size_offset") // FixAdd(b24317,b24371)
    val x36643 = OpDef(op=BitAnd, inputs=List(b24372, b24367)).name("x36643").ctrl(x36667).srcCtx("UnrollingBase.scala:28:66") // And(b24372,b24367)
    val x36644 = OpDef(op=BitAnd, inputs=List(b24363, b24319)).name("x36644").ctrl(x36667).srcCtx("UnrollingBase.scala:28:66") // And(b24363,b24319)
    val x36645 = OpDef(op=BitAnd, inputs=List(b24320, b22320)).name("x36645").ctrl(x36667).srcCtx("UnrollingBase.scala:28:66") // And(b24320,b22320)
    val x36646 = OpDef(op=BitAnd, inputs=List(x36643, x36644)).name("x36646").ctrl(x36667).srcCtx("UnrollingBase.scala:28:66") // And(x36643,x36644)
    val x36647 = OpDef(op=BitAnd, inputs=List(x36646, x36645)).name("x36647").ctrl(x36667).srcCtx("UnrollingBase.scala:28:66") // And(x36646,x36645)
    val x36648 = LoadBanks(List(x36600_d0_b0), List(b24371, b24366)).name("x36648").ctrl(x36667).srcCtx("CellsPar.scala:203:34:tk") // ParSRAMLoad(x36600,List(List(b24371, b24366)),List(x36647))
    val x36649 = x36648 // x36649 = VectorApply(x36648,0)
    val x36650 = LoadBanks(List(x34691_d0_b0), List(b24362, x36642)).name("x36650").ctrl(x36667).srcCtx("CellsPar.scala:204:28") // ParSRAMLoad(x34691,List(List(b24362, x36642)),List(x36647))
    val x36651 = x36650 // x36651 = VectorApply(x36650,0)
    val x36652 = OpDef(op=FixMul, inputs=List(x36651, x36649)).name("x36652").ctrl(x36667).srcCtx("CellsPar.scala:204:52:tempA") // FixMul(x36651,x36649)
    val x36653 = OpDef(op=FixSub, inputs=List(x36642, Const(512))).name("x36653").ctrl(x36667).srcCtx("CellsPar.scala:205:51") // FixSub(x36642,Const(512))
    val x36654 = LoadBanks(List(x34698_d1_b0), List(b24362, x36653)).name("x36654").ctrl(x36667).srcCtx("CellsPar.scala:205:28") // ParSRAMLoad(x34698,List(List(b24362, x36653)),List(x36647))
    val x36655 = x36654 // x36655 = VectorApply(x36654,0)
    val x36656 = OpDef(op=FixMul, inputs=List(x36655, x36649)).name("x36656").ctrl(x36667).srcCtx("CellsPar.scala:205:67:tempB") // FixMul(x36655,x36649)
    val x36657 = OpDef(op=FixLt, inputs=List(x36642, Const(512))).name("x36657").ctrl(x36667).srcCtx("CellsPar.scala:206:38") // FixLt(x36642,Const(512))
    val x36658 = OpDef(op=MuxOp, inputs=List(x36657, x36652, x36656)).name("x36658").ctrl(x36667).srcCtx("CellsPar.scala:206:18") // Mux(x36657,x36652,x36656)
    val x36659 = ReadMem(x36639_d1).name("x36659").ctrl(x36667).srcCtx("CellsPar.scala:207:15") // RegRead(x36639)
    val x36660 = OpDef(op=FixEql, inputs=List(b24371, Const(0))).name("x36660").ctrl(x36667).srcCtx("CellsPar.scala:207:15") // FixEql(b24371,Const(0))
    val x36661 = ReduceAccumOp(op=FixAdd, input=x36658, accum=x36659).name("x36661").ctrl(x36667).srcCtx("CellsPar.scala:207:17") // FixAdd(x36658,x36659)
    val x36662 = OpDef(op=BitAnd, inputs=List(b24367, b24363)).name("x36662").ctrl(x36667).srcCtx("UnrollingBase.scala:28:66") // And(b24367,b24363)
    val x36663 = OpDef(op=BitAnd, inputs=List(b24319, b24320)).name("x36663").ctrl(x36667).srcCtx("UnrollingBase.scala:28:66") // And(b24319,b24320)
    val x36664 = OpDef(op=BitAnd, inputs=List(x36662, x36663)).name("x36664").ctrl(x36667).srcCtx("UnrollingBase.scala:28:66") // And(x36662,x36663)
    val x36665 = OpDef(op=BitAnd, inputs=List(x36664, b22320)).name("x36665").ctrl(x36667).srcCtx("UnrollingBase.scala:28:66") // And(x36664,b22320)
    val x36666_x36639_d0 = WriteMem(x36639_d0, x36661).name("x36666_x36639_d0").ctrl(x36667).srcCtx("CellsPar.scala:207:15") // RegWrite(x36639,x36661,x36665)
    val x36666_x36639_d1 = WriteMem(x36639_d1, x36661).name("x36666_x36639_d1").ctrl(x36667).srcCtx("CellsPar.scala:207:15") // RegWrite(x36639,x36661,x36665)
    val x36702 = UnitController(style=SeqPipe, level=InnerControl).name("x36702").ctrl(x36703).srcCtx("CellsPar.scala:194:42") // UnitPipe(List(b24367, b24363, b24319, b24320, b22320),Block(Const(())))
    val x36668 = OpDef(op=FixAdd, inputs=List(b24318, b24366)).name("x36668").ctrl(x36702).srcCtx("CellsPar.scala:209:31:colOffset") // FixAdd(b24318,b24366)
    val x36669 = ReadMem(x36639_d0).name("x36669").ctrl(x36702).srcCtx("CellsPar.scala:210:28") // RegRead(x36639)
    val x36670 = OpDef(op=FixEql, inputs=List(b24317, Const(0))).name("x36670").ctrl(x36702).srcCtx("CellsPar.scala:210:42") // FixEql(b24317,Const(0))
    val x36671 = OpDef(op=FixAdd, inputs=List(x36668, Const(1536))).name("x36671").ctrl(x36702).srcCtx("CellsPar.scala:210:66") // FixAdd(x36668,Const(1536))
    val x36672 = OpDef(op=BitAnd, inputs=List(b24367, b24363)).name("x36672").ctrl(x36702).srcCtx("UnrollingBase.scala:28:66") // And(b24367,b24363)
    val x36673 = OpDef(op=BitAnd, inputs=List(b24319, b24320)).name("x36673").ctrl(x36702).srcCtx("UnrollingBase.scala:28:66") // And(b24319,b24320)
    val x36674 = OpDef(op=BitAnd, inputs=List(x36672, x36673)).name("x36674").ctrl(x36702).srcCtx("UnrollingBase.scala:28:66") // And(x36672,x36673)
    val x36675 = OpDef(op=BitAnd, inputs=List(x36674, b22320)).name("x36675").ctrl(x36702).srcCtx("UnrollingBase.scala:28:66") // And(x36674,b22320)
    val x36676 = LoadBanks(List(x34704_d0_b0), List(Const(0), x36671)).name("x36676").ctrl(x36702).srcCtx("CellsPar.scala:210:52") // SRAMLoad(x34704,ArrayBuffer(Const(1), Const(2048)),List(Const(0), x36671),Const(0),x36675)
    val x36677 = LoadBanks(List(x34703_d1_b0), List(b24362, x36668)).name("x36677").ctrl(x36702).srcCtx("CellsPar.scala:210:91") // SRAMLoad(x34703,ArrayBuffer(Const(1), Const(512)),List(b24362, x36668),Const(0),x36675)
    val x36678 = OpDef(op=MuxOp, inputs=List(x36670, x36676, x36677)).name("x36678").ctrl(x36702).srcCtx("CellsPar.scala:210:39") // Mux(x36670,x36676,x36677)
    val x36679 = OpDef(op=FixAdd, inputs=List(x36669, x36678)).name("x36679").ctrl(x36702).srcCtx("CellsPar.scala:210:34:ele") // FixAdd(x36669,x36678)
    val x36680 = OpDef(op=FixLeq, inputs=List(Const(1520), b24317)).name("x36680").ctrl(x36702).srcCtx("CellsPar.scala:218:46") // FixLeq(Const(1520),b24317)
    // x36681 = FixConvert(Const(0),TRUE,_8,_24) x.tp=FixPt[TRUE,_32,_0] {
    val x36681_int1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("01111111111111111111111111111111"))).name("x36681_int1").ctrl(x36702).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x36681_int2 = OpDef(op=FixSla, inputs=List(x36681_int1, Const(24))).name("x36681_int2").ctrl(x36702).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x36681_frac1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("00000000000000000000000000000000"))).name("x36681_frac1").ctrl(x36702).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x36681_frac2 = OpDef(op=FixSla, inputs=List(x36681_frac1, Const(24))).name("x36681_frac2").ctrl(x36702).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x36681 = OpDef(op=BitOr, inputs=List(x36681_int2, x36681_frac2)).name("x36681").ctrl(x36702).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    // }
    val x36682 = OpDef(op=FixAdd, inputs=List(x36679, x36681)).name("x36682").ctrl(x36702).srcCtx("CellsPar.scala:218:87") // FixAdd(x36679,x36681)
    val x36683 = OpDef(op=FixSra, inputs=List(x36682, Const(1))).name("x36683").ctrl(x36702).srcCtx("CellsPar.scala:29:22") // FixRsh(x36682,Const(1))
    val x36684 = x36683 // FixConvert(x36683,TRUE,_8,_24) (Same Type. No op)
    val x36685 = OpDef(op=FixAbs, inputs=List(x36684)).name("x36685").ctrl(x36702).srcCtx("CellsPar.scala:37:19:absp") // FixAbs(x36684)
    val x36686 = OpDef(op=FixLt, inputs=List(Const(2.5), x36685)).name("x36686").ctrl(x36702).srcCtx("CellsPar.scala:51:23:cond1") // FixLt(Const(2.5),x36685)
    val x36687 = OpDef(op=FixLt, inputs=List(Const(0.5), x36685)).name("x36687").ctrl(x36702).srcCtx("CellsPar.scala:54:35") // FixLt(Const(0.5),x36685)
    val x36688 = OpDef(op=FixLeq, inputs=List(x36685, Const(2.5))).name("x36688").ctrl(x36702).srcCtx("CellsPar.scala:54:52") // FixLeq(x36685,Const(2.5))
    val x36689 = OpDef(op=BitAnd, inputs=List(x36687, x36688)).name("x36689").ctrl(x36702).srcCtx("CellsPar.scala:54:43:cond2") // And(x36687,x36688)
    val x36690 = OpDef(op=FixSra, inputs=List(x36685, Const(2))).name("x36690").ctrl(x36702).srcCtx("CellsPar.scala:56:21:div4") // FixRsh(x36685,Const(2))
    val x36691 = OpDef(op=FixAdd, inputs=List(x36690, Const(0.375))).name("x36691").ctrl(x36702).srcCtx("CellsPar.scala:57:27:div4Offset") // FixAdd(x36690,Const(0.375))
    val x36692 = OpDef(op=MuxOp, inputs=List(x36689, x36691, x36685)).name("x36692").ctrl(x36702).srcCtx("CellsPar.scala:58:20:body2") // Mux(x36689,x36691,x36685)
    val x36693 = OpDef(op=MuxOp, inputs=List(x36686, Const(1.0), x36692)).name("x36693").ctrl(x36702).srcCtx("CellsPar.scala:60:20:absre") // Mux(x36686,Const(1),x36692)
    val x36694 = OpDef(op=FixNeg, inputs=List(x36693)).name("x36694").ctrl(x36702).srcCtx("CellsPar.scala:62:29:mabre") // FixNeg(x36693)
    val x36695 = OpDef(op=FixLt, inputs=List(x36684, Const(0.0))).name("x36695").ctrl(x36702).srcCtx("CellsPar.scala:63:22") // FixLt(x36684,Const(0))
    val x36696 = OpDef(op=MuxOp, inputs=List(x36695, x36694, x36693)).name("x36696").ctrl(x36702).srcCtx("CellsPar.scala:63:17:re") // Mux(x36695,x36694,x36693)
    val x36697 = x36696 // FixConvert(x36696,TRUE,_8,_24) (Same Type. No op)
    val x36698 = OpDef(op=FixAdd, inputs=List(x36697, Const(1.0))).name("x36698").ctrl(x36702).srcCtx("CellsPar.scala:29:33") // FixAdd(x36697,Const(1))
    val x36699 = OpDef(op=FixSra, inputs=List(x36698, Const(1))).name("x36699").ctrl(x36702).srcCtx("CellsPar.scala:29:44") // FixRsh(x36698,Const(1))
    val x36700 = OpDef(op=MuxOp, inputs=List(x36680, x36699, x36679)).name("x36700").ctrl(x36702).srcCtx("CellsPar.scala:218:43") // Mux(x36680,x36699,x36679)
    val x36701 = StoreBanks(List(x34703_d0_b0, x34703_d1_b0), List(b24362, x36668), x36700).name("x36701").ctrl(x36702).srcCtx("CellsPar.scala:218:38") // SRAMStore(x34703,ArrayBuffer(Const(1), Const(512)),List(b24362, x36668),Const(0),x36700,x36675)
    val x36706 = Counter(min=Const(0), max=Const(512), step=Const(1), par=1).name("x36706").ctrl(x36742).srcCtx("CellsPar.scala:267:50") // CounterNew(Const(0),Const(512),Const(1),Const(1))
    val x36707 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x36707").ctrl(x36742).srcCtx("CellsPar.scala:267:27") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x36708 = CounterChain(List(x36707,x36706)).name("x36708").ctrl(x36742).srcCtx("CellsPar.scala:267:62") // CounterChainNew(List(x36707, x36706))
    val x36741 = LoopController(style=InnerPipe, level=InnerControl, cchain=x36708).name("x36741").ctrl(x36742).srcCtx("CellsPar.scala:267:62") // UnrolledForeach(List(b22320),x36708,Block(Const(())),List(List(b24441), List(b24442)),List(List(b24443), List(b24444)))
    val b24441 = CounterIter(x36707, Some(0)).name("b24441").ctrl(x36741) // b24441
    val b24443 = Const(true).name("b24443").ctrl(x36741) // b24443
    val b24442 = CounterIter(x36706, None).name("b24442").ctrl(x36741) // b24442
    val b24444 = Const(true).name("b24444").ctrl(x36741) // b24444
    val x36709 = OpDef(op=BitAnd, inputs=List(b24443, b24444)).name("x36709").ctrl(x36741).srcCtx("UnrollingBase.scala:28:66") // And(b24443,b24444)
    val x36710 = OpDef(op=BitAnd, inputs=List(x36709, b22320)).name("x36710").ctrl(x36741).srcCtx("UnrollingBase.scala:28:66") // And(x36709,b22320)
    val x36711 = LoadBanks(List(x34699_d1_b0), List(b24441, b24442)).name("x36711").ctrl(x36741).srcCtx("CellsPar.scala:268:22") // ParSRAMLoad(x34699,List(List(b24441, b24442)),List(x36710))
    val x36712 = x36711 // x36712 = VectorApply(x36711,0)
    val x36713 = LoadBanks(List(x34702_d0_b0), List(b24441, b24442)).name("x36713").ctrl(x36741).srcCtx("CellsPar.scala:268:34") // ParSRAMLoad(x34702,List(List(b24441, b24442)),List(x36710))
    val x36714 = x36713 // x36714 = VectorApply(x36713,0)
    val x36715 = OpDef(op=FixMul, inputs=List(x36712, x36714)).name("x36715").ctrl(x36741).srcCtx("CellsPar.scala:268:28") // FixMul(x36712,x36714)
    val x36716 = LoadBanks(List(x34700_d0_b0), List(b24441, b24442)).name("x36716").ctrl(x36741).srcCtx("CellsPar.scala:268:46") // ParSRAMLoad(x34700,List(List(b24441, b24442)),List(x36710))
    val x36717 = x36716 // x36717 = VectorApply(x36716,0)
    val x36718 = LoadBanks(List(x34701_d0_b0), List(b24441, b24442)).name("x36718").ctrl(x36741).srcCtx("CellsPar.scala:268:59") // ParSRAMLoad(x34701,List(List(b24441, b24442)),List(x36710))
    val x36719 = x36718 // x36719 = VectorApply(x36718,0)
    val x36720 = OpDef(op=FixMul, inputs=List(x36717, x36719)).name("x36720").ctrl(x36741).srcCtx("CellsPar.scala:268:52") // FixMul(x36717,x36719)
    val x36721 = OpDef(op=FixAdd, inputs=List(x36715, x36720)).name("x36721").ctrl(x36741).srcCtx("CellsPar.scala:268:40:new_c") // FixAdd(x36715,x36720)
    val x36722 = x36721 // FixConvert(x36721,TRUE,_8,_24) (Same Type. No op)
    val x36723 = OpDef(op=FixAbs, inputs=List(x36722)).name("x36723").ctrl(x36741).srcCtx("CellsPar.scala:37:19:absp") // FixAbs(x36722)
    val x36724 = OpDef(op=FixLt, inputs=List(Const(2.5), x36723)).name("x36724").ctrl(x36741).srcCtx("CellsPar.scala:51:23:cond1") // FixLt(Const(2.5),x36723)
    val x36725 = OpDef(op=FixLt, inputs=List(Const(0.5), x36723)).name("x36725").ctrl(x36741).srcCtx("CellsPar.scala:54:35") // FixLt(Const(0.5),x36723)
    val x36726 = OpDef(op=FixLeq, inputs=List(x36723, Const(2.5))).name("x36726").ctrl(x36741).srcCtx("CellsPar.scala:54:52") // FixLeq(x36723,Const(2.5))
    val x36727 = OpDef(op=BitAnd, inputs=List(x36725, x36726)).name("x36727").ctrl(x36741).srcCtx("CellsPar.scala:54:43:cond2") // And(x36725,x36726)
    val x36728 = OpDef(op=FixSra, inputs=List(x36723, Const(2))).name("x36728").ctrl(x36741).srcCtx("CellsPar.scala:56:21:div4") // FixRsh(x36723,Const(2))
    val x36729 = OpDef(op=FixAdd, inputs=List(x36728, Const(0.375))).name("x36729").ctrl(x36741).srcCtx("CellsPar.scala:57:27:div4Offset") // FixAdd(x36728,Const(0.375))
    val x36730 = OpDef(op=MuxOp, inputs=List(x36727, x36729, x36723)).name("x36730").ctrl(x36741).srcCtx("CellsPar.scala:58:20:body2") // Mux(x36727,x36729,x36723)
    val x36731 = OpDef(op=MuxOp, inputs=List(x36724, Const(1.0), x36730)).name("x36731").ctrl(x36741).srcCtx("CellsPar.scala:60:20:absre") // Mux(x36724,Const(1),x36730)
    val x36732 = OpDef(op=FixNeg, inputs=List(x36731)).name("x36732").ctrl(x36741).srcCtx("CellsPar.scala:62:29:mabre") // FixNeg(x36731)
    val x36733 = OpDef(op=FixLt, inputs=List(x36722, Const(0.0))).name("x36733").ctrl(x36741).srcCtx("CellsPar.scala:63:22") // FixLt(x36722,Const(0))
    val x36734 = OpDef(op=MuxOp, inputs=List(x36733, x36732, x36731)).name("x36734").ctrl(x36741).srcCtx("CellsPar.scala:63:17:re") // Mux(x36733,x36732,x36731)
    val x36735 = x36734 // FixConvert(x36734,TRUE,_8,_24) (Same Type. No op)
    val x36736 = LoadBanks(List(x34703_d0_b0), List(b24441, b24442)).name("x36736").ctrl(x36741).srcCtx("CellsPar.scala:272:47") // ParSRAMLoad(x34703,List(List(b24441, b24442)),List(x36710))
    val x36737 = x36736 // x36737 = VectorApply(x36736,0)
    val x36738 = OpDef(op=FixMul, inputs=List(x36735, x36737)).name("x36738").ctrl(x36741).srcCtx("CellsPar.scala:272:41") // FixMul(x36735,x36737)
    val x36739 = StoreBanks(List(x34698_d0_b0, x34698_d1_b0, x34698_d2_b0, x34698_d3_b0, x34698_d4_b0), List(b24441, b24442), x36738).name("x36739").ctrl(x36741).srcCtx("CellsPar.scala:272:18") // ParSRAMStore(x34698,List(List(b24441, b24442)),List(x36738),List(x36710))
    val x36740 = StoreBanks(List(x34699_d0_b0, x34699_d1_b0), List(b24441, b24442), x36721).name("x36740").ctrl(x36741).srcCtx("CellsPar.scala:274:16") // ParSRAMStore(x34699,List(List(b24441, b24442)),List(x36721),List(x36710))
    val x36743 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x36743").ctrl(x36799).srcCtx("CellsPar.scala:175:46") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x36744 = CounterChain(List(x36743)).name("x36744").ctrl(x36799).srcCtx("CellsPar.scala:175:46") // CounterChainNew(List(x36743))
    val x36770 = LoopController(style=StreamPipe, level=OuterControl, cchain=x36744).name("x36770").ctrl(x36799).srcCtx("CellsPar.scala:175:46") // UnrolledForeach(List(Const(true)),x36744,Block(Const(())),List(List(b24481)),List(List(b24482)))
    val b24481 = CounterIter(x36743, Some(0)).name("b24481").ctrl(x36770) // b24481
    val b24482 = Const(true).name("b24482").ctrl(x36770) // b24482
    val b36893 = StreamOut(field="offset").name("b36893").ctrl(x36770).srcCtx("CellsPar.scala:175:46") // x36745 = StreamOutNew(BurstCmdBus)
    isAccum(b36893) = false
    bufferDepthOf(b36893) = 1
    val b36894 = StreamOut(field="size").name("b36894").ctrl(x36770).srcCtx("CellsPar.scala:175:46") // x36745 = StreamOutNew(BurstCmdBus)
    isAccum(b36894) = false
    bufferDepthOf(b36894) = 1
    val x36746 = StreamOut(field="data").name("x36746").ctrl(x36770).srcCtx("CellsPar.scala:175:46") // x36746 = StreamOutNew(BurstFullDataBus())
    isAccum(x36746) = false
    bufferDepthOf(x36746) = 1
    val x36747 = StreamIn(field="ack").name("x36747").ctrl(x36770).srcCtx("CellsPar.scala:175:46") // x36747 = StreamInNew(BurstAckBus)
    isAccum(x36747) = false
    bufferDepthOf(x36747) = 1
    val x36758 = UnitController(style=SeqPipe, level=InnerControl).name("x36758").ctrl(x36770).srcCtx("CellsPar.scala:175:46") // UnitPipe(List(b24482),Block(x36757))
    val x36748 = b24481 // FixConvert(b24481,TRUE,_32,_0) (Same Type. No op)
    val x36749 = OpDef(op=FixSla, inputs=List(x36748, Const(9))).name("x36749").ctrl(x36758).srcCtx("CellsPar.scala:175:46") // FixLsh(x36748,Const(9))
    val x36750 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x36751 = OpDef(op=FixAdd, inputs=List(x36749, x36750)).name("x36751").ctrl(x36758).srcCtx("CellsPar.scala:175:46") // FixAdd(x36749,x36750)
    val x36752 = OpDef(op=FixSla, inputs=List(x36751, Const(2))).name("x36752").ctrl(x36758).srcCtx("CellsPar.scala:175:46") // FixLsh(x36751,Const(2))
    val x36753 = x36752 // FixConvert(x36752,TRUE,_64,_0)
    val x36754 = DramAddress(x34600).name("x36754").ctrl(x36758).srcCtx("CellsPar.scala:175:46") // GetDRAMAddress(x34600)
    val x36756_x36755 = OpDef(op=FixAdd, inputs=List(x36753, x36754)).name("x36756_x36755").ctrl(x36758).srcCtx("CellsPar.scala:175:46") // FixAdd(x36753,x36754)
    // x36756 = SimpleStruct(ArrayBuffer((offset,x36755), (size,Const(2048)), (isLoad,Const(false))))
    val x36757_b36895_b36893 = WriteMem(b36893, x36756_x36755).name("x36757_b36895_b36893").ctrl(x36758).srcCtx("CellsPar.scala:175:46") // StreamWrite(x36745,x36756,b24482)
    val x36757_b36896_b36894 = WriteMem(b36894, Const(2048)).name("x36757_b36896_b36894").ctrl(x36758).srcCtx("CellsPar.scala:175:46") // StreamWrite(x36745,x36756,b24482)
    val x36759 = Counter(min=Const(0), max=Const(512), step=Const(1), par=1).name("x36759").ctrl(x36770).srcCtx("CellsPar.scala:175:46") // CounterNew(Const(0),Const(512),Const(1),Const(1))
    val x36760 = CounterChain(List(x36759)).name("x36760").ctrl(x36770).srcCtx("CellsPar.scala:175:46") // CounterChainNew(List(x36759))
    val x36766 = LoopController(style=InnerPipe, level=InnerControl, cchain=x36760).name("x36766").ctrl(x36770).srcCtx("CellsPar.scala:175:46") // UnrolledForeach(List(b24482),x36760,Block(Const(())),List(List(b24499)),List(List(b24500)))
    val b24499 = CounterIter(x36759, None).name("b24499").ctrl(x36766) // b24499
    val b24500 = Const(true).name("b24500").ctrl(x36766) // b24500
    val x36761 = OpDef(op=BitAnd, inputs=List(b24500, b24482)).name("x36761").ctrl(x36766).srcCtx("UnrollingBase.scala:28:66") // And(b24500,b24482)
    val x36762 = LoadBanks(List(x34699_d0_b0), List(b24481, b24499)).name("x36762").ctrl(x36766).srcCtx("CellsPar.scala:175:46") // ParSRAMLoad(x34699,List(List(b24481, b24499)),List(x36761))
    val x36764_x36763 = x36762 // x36763 = VectorApply(x36762,0)
    // x36764 = SimpleStruct(ArrayBuffer((_1,x36763), (_2,Const(true))))
    val x36765_x36765_x36746 = WriteMem(x36746, x36764_x36763).name("x36765_x36765_x36746").ctrl(x36766).srcCtx("CellsPar.scala:175:46") // ParStreamWrite(x36746,List(x36764),List(x36761))
    val x36767 = FringeDenseStore(dram=List(x34600), cmdStream=List(b36893, b36894), dataStream=List(x36746), ackStream=List(x36747)).name("x36767").ctrl(x36770).srcCtx("CellsPar.scala:175:46") // FringeDenseStore(x34600,x36745,x36746,x36747)
    val x36769 = UnitController(style=SeqPipe, level=InnerControl).name("x36769").ctrl(x36770).srcCtx("CellsPar.scala:175:46") // UnitPipe(List(b24482),Block(Const(())))
    val x36768_x36768 = ReadMem(x36747).name("x36768_x36768").ctrl(x36769).srcCtx("CellsPar.scala:175:46") // StreamRead(x36747,b24482)
    val x36771 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x36771").ctrl(x36799).srcCtx("CellsPar.scala:176:46") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x36772 = CounterChain(List(x36771)).name("x36772").ctrl(x36799).srcCtx("CellsPar.scala:176:46") // CounterChainNew(List(x36771))
    val x36798 = LoopController(style=StreamPipe, level=OuterControl, cchain=x36772).name("x36798").ctrl(x36799).srcCtx("CellsPar.scala:176:46") // UnrolledForeach(List(Const(true)),x36772,Block(Const(())),List(List(b24513)),List(List(b24514)))
    val b24513 = CounterIter(x36771, Some(0)).name("b24513").ctrl(x36798) // b24513
    val b24514 = Const(true).name("b24514").ctrl(x36798) // b24514
    val b36897 = StreamOut(field="offset").name("b36897").ctrl(x36798).srcCtx("CellsPar.scala:176:46") // x36773 = StreamOutNew(BurstCmdBus)
    isAccum(b36897) = false
    bufferDepthOf(b36897) = 1
    val b36898 = StreamOut(field="size").name("b36898").ctrl(x36798).srcCtx("CellsPar.scala:176:46") // x36773 = StreamOutNew(BurstCmdBus)
    isAccum(b36898) = false
    bufferDepthOf(b36898) = 1
    val x36774 = StreamOut(field="data").name("x36774").ctrl(x36798).srcCtx("CellsPar.scala:176:46") // x36774 = StreamOutNew(BurstFullDataBus())
    isAccum(x36774) = false
    bufferDepthOf(x36774) = 1
    val x36775 = StreamIn(field="ack").name("x36775").ctrl(x36798).srcCtx("CellsPar.scala:176:46") // x36775 = StreamInNew(BurstAckBus)
    isAccum(x36775) = false
    bufferDepthOf(x36775) = 1
    val x36786 = UnitController(style=SeqPipe, level=InnerControl).name("x36786").ctrl(x36798).srcCtx("CellsPar.scala:176:46") // UnitPipe(List(b24514),Block(x36785))
    val x36776 = b24513 // FixConvert(b24513,TRUE,_32,_0) (Same Type. No op)
    val x36777 = OpDef(op=FixSla, inputs=List(x36776, Const(9))).name("x36777").ctrl(x36786).srcCtx("CellsPar.scala:176:46") // FixLsh(x36776,Const(9))
    val x36778 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x36779 = OpDef(op=FixAdd, inputs=List(x36777, x36778)).name("x36779").ctrl(x36786).srcCtx("CellsPar.scala:176:46") // FixAdd(x36777,x36778)
    val x36780 = OpDef(op=FixSla, inputs=List(x36779, Const(2))).name("x36780").ctrl(x36786).srcCtx("CellsPar.scala:176:46") // FixLsh(x36779,Const(2))
    val x36781 = x36780 // FixConvert(x36780,TRUE,_64,_0)
    val x36782 = DramAddress(x34602).name("x36782").ctrl(x36786).srcCtx("CellsPar.scala:176:46") // GetDRAMAddress(x34602)
    val x36784_x36783 = OpDef(op=FixAdd, inputs=List(x36781, x36782)).name("x36784_x36783").ctrl(x36786).srcCtx("CellsPar.scala:176:46") // FixAdd(x36781,x36782)
    // x36784 = SimpleStruct(ArrayBuffer((offset,x36783), (size,Const(2048)), (isLoad,Const(false))))
    val x36785_b36899_b36897 = WriteMem(b36897, x36784_x36783).name("x36785_b36899_b36897").ctrl(x36786).srcCtx("CellsPar.scala:176:46") // StreamWrite(x36773,x36784,b24514)
    val x36785_b36900_b36898 = WriteMem(b36898, Const(2048)).name("x36785_b36900_b36898").ctrl(x36786).srcCtx("CellsPar.scala:176:46") // StreamWrite(x36773,x36784,b24514)
    val x36787 = Counter(min=Const(0), max=Const(512), step=Const(1), par=1).name("x36787").ctrl(x36798).srcCtx("CellsPar.scala:176:46") // CounterNew(Const(0),Const(512),Const(1),Const(1))
    val x36788 = CounterChain(List(x36787)).name("x36788").ctrl(x36798).srcCtx("CellsPar.scala:176:46") // CounterChainNew(List(x36787))
    val x36794 = LoopController(style=InnerPipe, level=InnerControl, cchain=x36788).name("x36794").ctrl(x36798).srcCtx("CellsPar.scala:176:46") // UnrolledForeach(List(b24514),x36788,Block(Const(())),List(List(b24531)),List(List(b24532)))
    val b24531 = CounterIter(x36787, None).name("b24531").ctrl(x36794) // b24531
    val b24532 = Const(true).name("b24532").ctrl(x36794) // b24532
    val x36789 = OpDef(op=BitAnd, inputs=List(b24532, b24514)).name("x36789").ctrl(x36794).srcCtx("UnrollingBase.scala:28:66") // And(b24532,b24514)
    val x36790 = LoadBanks(List(x34698_d0_b0), List(b24513, b24531)).name("x36790").ctrl(x36794).srcCtx("CellsPar.scala:176:46") // ParSRAMLoad(x34698,List(List(b24513, b24531)),List(x36789))
    val x36792_x36791 = x36790 // x36791 = VectorApply(x36790,0)
    // x36792 = SimpleStruct(ArrayBuffer((_1,x36791), (_2,Const(true))))
    val x36793_x36793_x36774 = WriteMem(x36774, x36792_x36791).name("x36793_x36793_x36774").ctrl(x36794).srcCtx("CellsPar.scala:176:46") // ParStreamWrite(x36774,List(x36792),List(x36789))
    val x36795 = FringeDenseStore(dram=List(x34602), cmdStream=List(b36897, b36898), dataStream=List(x36774), ackStream=List(x36775)).name("x36795").ctrl(x36798).srcCtx("CellsPar.scala:176:46") // FringeDenseStore(x34602,x36773,x36774,x36775)
    val x36797 = UnitController(style=SeqPipe, level=InnerControl).name("x36797").ctrl(x36798).srcCtx("CellsPar.scala:176:46") // UnitPipe(List(b24514),Block(Const(())))
    val x36796_x36796 = ReadMem(x36775).name("x36796_x36796").ctrl(x36797).srcCtx("CellsPar.scala:176:46") // StreamRead(x36775,b24514)
    }
    fun1
    }
    fun2
  }
}
