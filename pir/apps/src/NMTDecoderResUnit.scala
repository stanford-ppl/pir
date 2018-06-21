import pir._
import pir.node._
import arch._
import prism.enums._

object NMTDecoderResUnit extends PIRApp {
  def main(implicit design:PIRDesign) = {
    import design.pirmeta._
    val x34438 = DRAM().name("x34438").ctrl(top).srcCtx("NMTDSE.scala:456:24:xDRAM") // x34438 = DRAMNew(ArrayBuffer(Const(1), Const(4), Const(512)),Const(0))
    val x34446 = DRAM().name("x34446").ctrl(top).srcCtx("CellsPar.scala:80:24") // x34446 = DRAMNew(ArrayBuffer(Const(1), Const(512)),Const(0))
    val x34447 = DRAM().name("x34447").ctrl(top).srcCtx("CellsPar.scala:81:24") // x34447 = DRAMNew(ArrayBuffer(Const(1), Const(2048)),Const(0))
    val x34448 = DRAM().name("x34448").ctrl(top).srcCtx("CellsPar.scala:82:24") // x34448 = DRAMNew(ArrayBuffer(Const(1), Const(512)),Const(0))
    val x34474 = DRAM().name("x34474").ctrl(top).srcCtx("CellsPar.scala:90:21:kernel") // x34474 = DRAMNew(ArrayBuffer(Const(1536), Const(512)),Const(0))
    val x34475 = DRAM().name("x34475").ctrl(top).srcCtx("CellsPar.scala:91:21:kernel") // x34475 = DRAMNew(ArrayBuffer(Const(1536), Const(512)),Const(0))
    val x34476 = DRAM().name("x34476").ctrl(top).srcCtx("CellsPar.scala:92:21:kernel") // x34476 = DRAMNew(ArrayBuffer(Const(1536), Const(512)),Const(0))
    val x34477 = DRAM().name("x34477").ctrl(top).srcCtx("CellsPar.scala:93:21:kernel") // x34477 = DRAMNew(ArrayBuffer(Const(1536), Const(512)),Const(0))
    val x34478 = DRAM().name("x34478").ctrl(top).srcCtx("CellsPar.scala:94:25") // x34478 = DRAMNew(ArrayBuffer(Const(1536), Const(2048)),Const(0))
    val x34522 = DRAM().name("x34522").ctrl(top).srcCtx("CellsPar.scala:80:24") // x34522 = DRAMNew(ArrayBuffer(Const(1), Const(512)),Const(0))
    val x34523 = DRAM().name("x34523").ctrl(top).srcCtx("CellsPar.scala:81:24") // x34523 = DRAMNew(ArrayBuffer(Const(1), Const(2048)),Const(0))
    val x34524 = DRAM().name("x34524").ctrl(top).srcCtx("CellsPar.scala:82:24") // x34524 = DRAMNew(ArrayBuffer(Const(1), Const(512)),Const(0))
    val x34550 = DRAM().name("x34550").ctrl(top).srcCtx("CellsPar.scala:90:21:kernel") // x34550 = DRAMNew(ArrayBuffer(Const(1536), Const(512)),Const(0))
    val x34551 = DRAM().name("x34551").ctrl(top).srcCtx("CellsPar.scala:91:21:kernel") // x34551 = DRAMNew(ArrayBuffer(Const(1536), Const(512)),Const(0))
    val x34552 = DRAM().name("x34552").ctrl(top).srcCtx("CellsPar.scala:92:21:kernel") // x34552 = DRAMNew(ArrayBuffer(Const(1536), Const(512)),Const(0))
    val x34553 = DRAM().name("x34553").ctrl(top).srcCtx("CellsPar.scala:93:21:kernel") // x34553 = DRAMNew(ArrayBuffer(Const(1536), Const(512)),Const(0))
    val x34554 = DRAM().name("x34554").ctrl(top).srcCtx("CellsPar.scala:94:25") // x34554 = DRAMNew(ArrayBuffer(Const(1536), Const(2048)),Const(0))
    val x34598 = DRAM().name("x34598").ctrl(top).srcCtx("CellsPar.scala:80:24") // x34598 = DRAMNew(ArrayBuffer(Const(1), Const(512)),Const(0))
    val x34599 = DRAM().name("x34599").ctrl(top).srcCtx("CellsPar.scala:81:24") // x34599 = DRAMNew(ArrayBuffer(Const(1), Const(2048)),Const(0))
    val x34600 = DRAM().name("x34600").ctrl(top).srcCtx("CellsPar.scala:82:24") // x34600 = DRAMNew(ArrayBuffer(Const(1), Const(512)),Const(0))
    val x34626 = DRAM().name("x34626").ctrl(top).srcCtx("CellsPar.scala:90:21:kernel") // x34626 = DRAMNew(ArrayBuffer(Const(1536), Const(512)),Const(0))
    val x34627 = DRAM().name("x34627").ctrl(top).srcCtx("CellsPar.scala:91:21:kernel") // x34627 = DRAMNew(ArrayBuffer(Const(1536), Const(512)),Const(0))
    val x34628 = DRAM().name("x34628").ctrl(top).srcCtx("CellsPar.scala:92:21:kernel") // x34628 = DRAMNew(ArrayBuffer(Const(1536), Const(512)),Const(0))
    val x34629 = DRAM().name("x34629").ctrl(top).srcCtx("CellsPar.scala:93:21:kernel") // x34629 = DRAMNew(ArrayBuffer(Const(1536), Const(512)),Const(0))
    val x34630 = DRAM().name("x34630").ctrl(top).srcCtx("CellsPar.scala:94:25") // x34630 = DRAMNew(ArrayBuffer(Const(1536), Const(2048)),Const(0))
    val x34674 = DRAM().name("x34674").ctrl(top).srcCtx("CellsPar.scala:80:24") // x34674 = DRAMNew(ArrayBuffer(Const(1), Const(512)),Const(0))
    val x34675 = DRAM().name("x34675").ctrl(top).srcCtx("CellsPar.scala:81:24") // x34675 = DRAMNew(ArrayBuffer(Const(1), Const(2048)),Const(0))
    val x34676 = DRAM().name("x34676").ctrl(top).srcCtx("CellsPar.scala:82:24:output_hidden") // x34676 = DRAMNew(ArrayBuffer(Const(1), Const(512)),Const(0))
    val x34702 = DRAM().name("x34702").ctrl(top).srcCtx("CellsPar.scala:90:21:kernel") // x34702 = DRAMNew(ArrayBuffer(Const(1536), Const(512)),Const(0))
    val x34703 = DRAM().name("x34703").ctrl(top).srcCtx("CellsPar.scala:91:21:kernel") // x34703 = DRAMNew(ArrayBuffer(Const(1536), Const(512)),Const(0))
    val x34704 = DRAM().name("x34704").ctrl(top).srcCtx("CellsPar.scala:92:21:kernel") // x34704 = DRAMNew(ArrayBuffer(Const(1536), Const(512)),Const(0))
    val x34705 = DRAM().name("x34705").ctrl(top).srcCtx("CellsPar.scala:93:21:kernel") // x34705 = DRAMNew(ArrayBuffer(Const(1536), Const(512)),Const(0))
    val x34706 = DRAM().name("x34706").ctrl(top).srcCtx("CellsPar.scala:94:25") // x34706 = DRAMNew(ArrayBuffer(Const(1536), Const(2048)),Const(0))
    val x36879 = UnitController(style=SeqPipe, level=OuterControl).name("x36879").ctrl(top).srcCtx("NMTDSE.scala:465:11") // Hwblock(Block(Const(())),false)
    val x34750_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34750_d0_b0").ctrl(x36879).srcCtx("NMTDSE.scala:466:66") // x34750 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34750_d0_b0) = false
    bufferDepthOf(x34750_d0_b0) = 1
    val x34750_d1_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34750_d1_b0").ctrl(x36879).srcCtx("NMTDSE.scala:466:66") // x34750 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34750_d1_b0) = false
    bufferDepthOf(x34750_d1_b0) = 1
    val x34750_d2_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34750_d2_b0").ctrl(x36879).srcCtx("NMTDSE.scala:466:66") // x34750 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34750_d2_b0) = false
    bufferDepthOf(x34750_d2_b0) = 1
    val x34750_d3_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34750_d3_b0").ctrl(x36879).srcCtx("NMTDSE.scala:466:66") // x34750 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34750_d3_b0) = false
    bufferDepthOf(x34750_d3_b0) = 1
    val x34751_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34751_d0_b0").ctrl(x36879).srcCtx("CellsPar.scala:139:20") // x34751 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34751_d0_b0) = false
    bufferDepthOf(x34751_d0_b0) = 1
    val x34751_d1_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34751_d1_b0").ctrl(x36879).srcCtx("CellsPar.scala:139:20") // x34751 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34751_d1_b0) = false
    bufferDepthOf(x34751_d1_b0) = 1
    val x34751_d2_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34751_d2_b0").ctrl(x36879).srcCtx("CellsPar.scala:139:20") // x34751 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34751_d2_b0) = false
    bufferDepthOf(x34751_d2_b0) = 1
    val x34751_d3_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34751_d3_b0").ctrl(x36879).srcCtx("CellsPar.scala:139:20") // x34751 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34751_d3_b0) = false
    bufferDepthOf(x34751_d3_b0) = 1
    val x34751_d4_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34751_d4_b0").ctrl(x36879).srcCtx("CellsPar.scala:139:20") // x34751 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34751_d4_b0) = false
    bufferDepthOf(x34751_d4_b0) = 1
    val x34751_d5_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34751_d5_b0").ctrl(x36879).srcCtx("CellsPar.scala:139:20") // x34751 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34751_d5_b0) = false
    bufferDepthOf(x34751_d5_b0) = 1
    val x34751_d6_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34751_d6_b0").ctrl(x36879).srcCtx("CellsPar.scala:139:20") // x34751 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34751_d6_b0) = false
    bufferDepthOf(x34751_d6_b0) = 1
    val x34751_d7_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34751_d7_b0").ctrl(x36879).srcCtx("CellsPar.scala:139:20") // x34751 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34751_d7_b0) = false
    bufferDepthOf(x34751_d7_b0) = 1
    val x34752_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34752_d0_b0").ctrl(x36879).srcCtx("CellsPar.scala:140:20") // x34752 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34752_d0_b0) = true
    bufferDepthOf(x34752_d0_b0) = 1
    val x34753_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34753_d0_b0").ctrl(x36879).srcCtx("CellsPar.scala:141:45:targetSRAM") // x34753 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34753_d0_b0) = false
    bufferDepthOf(x34753_d0_b0) = 1
    val x34753_d1_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34753_d1_b0").ctrl(x36879).srcCtx("CellsPar.scala:141:45:targetSRAM") // x34753 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34753_d1_b0) = true
    bufferDepthOf(x34753_d1_b0) = 1
    val x34754_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34754_d0_b0").ctrl(x36879).srcCtx("CellsPar.scala:141:79:targetSRAM") // x34754 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34754_d0_b0) = false
    bufferDepthOf(x34754_d0_b0) = 1
    val x34754_d1_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34754_d1_b0").ctrl(x36879).srcCtx("CellsPar.scala:141:79:targetSRAM") // x34754 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34754_d1_b0) = true
    bufferDepthOf(x34754_d1_b0) = 1
    val x34755_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34755_d0_b0").ctrl(x36879).srcCtx("CellsPar.scala:142:44:targetSRAM") // x34755 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34755_d0_b0) = false
    bufferDepthOf(x34755_d0_b0) = 1
    val x34755_d1_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34755_d1_b0").ctrl(x36879).srcCtx("CellsPar.scala:142:44:targetSRAM") // x34755 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34755_d1_b0) = true
    bufferDepthOf(x34755_d1_b0) = 1
    val x34756_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34756_d0_b0").ctrl(x36879).srcCtx("CellsPar.scala:142:78:targetSRAM") // x34756 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34756_d0_b0) = false
    bufferDepthOf(x34756_d0_b0) = 1
    val x34756_d1_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34756_d1_b0").ctrl(x36879).srcCtx("CellsPar.scala:142:78:targetSRAM") // x34756 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34756_d1_b0) = true
    bufferDepthOf(x34756_d1_b0) = 1
    val x34757_d0_b0 = SRAM(size=2048, banking=Strided(banks=1, stride=2048)).name("x34757_d0_b0").ctrl(x36879).srcCtx("CellsPar.scala:143:23:bias") // x34757 = SRAMNew(ArrayBuffer(Const(1), Const(2048)))
    isAccum(x34757_d0_b0) = false
    bufferDepthOf(x34757_d0_b0) = 1
    val x34757_d1_b0 = SRAM(size=2048, banking=Strided(banks=1, stride=2048)).name("x34757_d1_b0").ctrl(x36879).srcCtx("CellsPar.scala:143:23:bias") // x34757 = SRAMNew(ArrayBuffer(Const(1), Const(2048)))
    isAccum(x34757_d1_b0) = false
    bufferDepthOf(x34757_d1_b0) = 1
    val x34757_d2_b0 = SRAM(size=2048, banking=Strided(banks=1, stride=2048)).name("x34757_d2_b0").ctrl(x36879).srcCtx("CellsPar.scala:143:23:bias") // x34757 = SRAMNew(ArrayBuffer(Const(1), Const(2048)))
    isAccum(x34757_d2_b0) = false
    bufferDepthOf(x34757_d2_b0) = 1
    val x34757_d3_b0 = SRAM(size=2048, banking=Strided(banks=1, stride=2048)).name("x34757_d3_b0").ctrl(x36879).srcCtx("CellsPar.scala:143:23:bias") // x34757 = SRAMNew(ArrayBuffer(Const(1), Const(2048)))
    isAccum(x34757_d3_b0) = false
    bufferDepthOf(x34757_d3_b0) = 1
    val x34758_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34758_d0_b0").ctrl(x36879).srcCtx("CellsPar.scala:139:20") // x34758 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34758_d0_b0) = false
    bufferDepthOf(x34758_d0_b0) = 1
    val x34758_d1_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34758_d1_b0").ctrl(x36879).srcCtx("CellsPar.scala:139:20") // x34758 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34758_d1_b0) = false
    bufferDepthOf(x34758_d1_b0) = 1
    val x34758_d2_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34758_d2_b0").ctrl(x36879).srcCtx("CellsPar.scala:139:20") // x34758 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34758_d2_b0) = false
    bufferDepthOf(x34758_d2_b0) = 1
    val x34758_d3_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34758_d3_b0").ctrl(x36879).srcCtx("CellsPar.scala:139:20") // x34758 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34758_d3_b0) = false
    bufferDepthOf(x34758_d3_b0) = 1
    val x34758_d4_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34758_d4_b0").ctrl(x36879).srcCtx("CellsPar.scala:139:20") // x34758 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34758_d4_b0) = false
    bufferDepthOf(x34758_d4_b0) = 1
    val x34758_d5_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34758_d5_b0").ctrl(x36879).srcCtx("CellsPar.scala:139:20") // x34758 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34758_d5_b0) = false
    bufferDepthOf(x34758_d5_b0) = 1
    val x34758_d6_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34758_d6_b0").ctrl(x36879).srcCtx("CellsPar.scala:139:20") // x34758 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34758_d6_b0) = false
    bufferDepthOf(x34758_d6_b0) = 1
    val x34758_d7_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34758_d7_b0").ctrl(x36879).srcCtx("CellsPar.scala:139:20") // x34758 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34758_d7_b0) = false
    bufferDepthOf(x34758_d7_b0) = 1
    val x34758_d8_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34758_d8_b0").ctrl(x36879).srcCtx("CellsPar.scala:139:20") // x34758 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34758_d8_b0) = false
    bufferDepthOf(x34758_d8_b0) = 1
    val x34759_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34759_d0_b0").ctrl(x36879).srcCtx("CellsPar.scala:140:20") // x34759 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34759_d0_b0) = true
    bufferDepthOf(x34759_d0_b0) = 1
    val x34760_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34760_d0_b0").ctrl(x36879).srcCtx("CellsPar.scala:141:45:targetSRAM") // x34760 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34760_d0_b0) = false
    bufferDepthOf(x34760_d0_b0) = 1
    val x34760_d1_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34760_d1_b0").ctrl(x36879).srcCtx("CellsPar.scala:141:45:targetSRAM") // x34760 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34760_d1_b0) = true
    bufferDepthOf(x34760_d1_b0) = 1
    val x34761_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34761_d0_b0").ctrl(x36879).srcCtx("CellsPar.scala:141:79:targetSRAM") // x34761 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34761_d0_b0) = false
    bufferDepthOf(x34761_d0_b0) = 1
    val x34761_d1_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34761_d1_b0").ctrl(x36879).srcCtx("CellsPar.scala:141:79:targetSRAM") // x34761 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34761_d1_b0) = true
    bufferDepthOf(x34761_d1_b0) = 1
    val x34762_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34762_d0_b0").ctrl(x36879).srcCtx("CellsPar.scala:142:44:targetSRAM") // x34762 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34762_d0_b0) = false
    bufferDepthOf(x34762_d0_b0) = 1
    val x34762_d1_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34762_d1_b0").ctrl(x36879).srcCtx("CellsPar.scala:142:44:targetSRAM") // x34762 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34762_d1_b0) = true
    bufferDepthOf(x34762_d1_b0) = 1
    val x34763_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34763_d0_b0").ctrl(x36879).srcCtx("CellsPar.scala:142:78:targetSRAM") // x34763 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34763_d0_b0) = false
    bufferDepthOf(x34763_d0_b0) = 1
    val x34763_d1_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34763_d1_b0").ctrl(x36879).srcCtx("CellsPar.scala:142:78:targetSRAM") // x34763 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34763_d1_b0) = true
    bufferDepthOf(x34763_d1_b0) = 1
    val x34764_d0_b0 = SRAM(size=2048, banking=Strided(banks=1, stride=2048)).name("x34764_d0_b0").ctrl(x36879).srcCtx("CellsPar.scala:143:23:bias") // x34764 = SRAMNew(ArrayBuffer(Const(1), Const(2048)))
    isAccum(x34764_d0_b0) = false
    bufferDepthOf(x34764_d0_b0) = 1
    val x34764_d1_b0 = SRAM(size=2048, banking=Strided(banks=1, stride=2048)).name("x34764_d1_b0").ctrl(x36879).srcCtx("CellsPar.scala:143:23:bias") // x34764 = SRAMNew(ArrayBuffer(Const(1), Const(2048)))
    isAccum(x34764_d1_b0) = false
    bufferDepthOf(x34764_d1_b0) = 1
    val x34764_d2_b0 = SRAM(size=2048, banking=Strided(banks=1, stride=2048)).name("x34764_d2_b0").ctrl(x36879).srcCtx("CellsPar.scala:143:23:bias") // x34764 = SRAMNew(ArrayBuffer(Const(1), Const(2048)))
    isAccum(x34764_d2_b0) = false
    bufferDepthOf(x34764_d2_b0) = 1
    val x34764_d3_b0 = SRAM(size=2048, banking=Strided(banks=1, stride=2048)).name("x34764_d3_b0").ctrl(x36879).srcCtx("CellsPar.scala:143:23:bias") // x34764 = SRAMNew(ArrayBuffer(Const(1), Const(2048)))
    isAccum(x34764_d3_b0) = false
    bufferDepthOf(x34764_d3_b0) = 1
    val x34765_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34765_d0_b0").ctrl(x36879).srcCtx("CellsPar.scala:139:20") // x34765 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34765_d0_b0) = false
    bufferDepthOf(x34765_d0_b0) = 1
    val x34765_d1_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34765_d1_b0").ctrl(x36879).srcCtx("CellsPar.scala:139:20") // x34765 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34765_d1_b0) = false
    bufferDepthOf(x34765_d1_b0) = 1
    val x34765_d2_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34765_d2_b0").ctrl(x36879).srcCtx("CellsPar.scala:139:20") // x34765 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34765_d2_b0) = false
    bufferDepthOf(x34765_d2_b0) = 1
    val x34765_d3_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34765_d3_b0").ctrl(x36879).srcCtx("CellsPar.scala:139:20") // x34765 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34765_d3_b0) = false
    bufferDepthOf(x34765_d3_b0) = 1
    val x34765_d4_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34765_d4_b0").ctrl(x36879).srcCtx("CellsPar.scala:139:20") // x34765 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34765_d4_b0) = false
    bufferDepthOf(x34765_d4_b0) = 1
    val x34765_d5_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34765_d5_b0").ctrl(x36879).srcCtx("CellsPar.scala:139:20") // x34765 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34765_d5_b0) = false
    bufferDepthOf(x34765_d5_b0) = 1
    val x34765_d6_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34765_d6_b0").ctrl(x36879).srcCtx("CellsPar.scala:139:20") // x34765 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34765_d6_b0) = false
    bufferDepthOf(x34765_d6_b0) = 1
    val x34765_d7_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34765_d7_b0").ctrl(x36879).srcCtx("CellsPar.scala:139:20") // x34765 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34765_d7_b0) = false
    bufferDepthOf(x34765_d7_b0) = 1
    val x34765_d8_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34765_d8_b0").ctrl(x36879).srcCtx("CellsPar.scala:139:20") // x34765 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34765_d8_b0) = false
    bufferDepthOf(x34765_d8_b0) = 1
    val x34766_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34766_d0_b0").ctrl(x36879).srcCtx("CellsPar.scala:140:20") // x34766 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34766_d0_b0) = true
    bufferDepthOf(x34766_d0_b0) = 1
    val x34767_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34767_d0_b0").ctrl(x36879).srcCtx("CellsPar.scala:141:45:targetSRAM") // x34767 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34767_d0_b0) = false
    bufferDepthOf(x34767_d0_b0) = 1
    val x34767_d1_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34767_d1_b0").ctrl(x36879).srcCtx("CellsPar.scala:141:45:targetSRAM") // x34767 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34767_d1_b0) = true
    bufferDepthOf(x34767_d1_b0) = 1
    val x34768_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34768_d0_b0").ctrl(x36879).srcCtx("CellsPar.scala:141:79:targetSRAM") // x34768 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34768_d0_b0) = false
    bufferDepthOf(x34768_d0_b0) = 1
    val x34768_d1_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34768_d1_b0").ctrl(x36879).srcCtx("CellsPar.scala:141:79:targetSRAM") // x34768 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34768_d1_b0) = true
    bufferDepthOf(x34768_d1_b0) = 1
    val x34769_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34769_d0_b0").ctrl(x36879).srcCtx("CellsPar.scala:142:44:targetSRAM") // x34769 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34769_d0_b0) = false
    bufferDepthOf(x34769_d0_b0) = 1
    val x34769_d1_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34769_d1_b0").ctrl(x36879).srcCtx("CellsPar.scala:142:44:targetSRAM") // x34769 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34769_d1_b0) = true
    bufferDepthOf(x34769_d1_b0) = 1
    val x34770_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34770_d0_b0").ctrl(x36879).srcCtx("CellsPar.scala:142:78:targetSRAM") // x34770 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34770_d0_b0) = false
    bufferDepthOf(x34770_d0_b0) = 1
    val x34770_d1_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34770_d1_b0").ctrl(x36879).srcCtx("CellsPar.scala:142:78:targetSRAM") // x34770 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34770_d1_b0) = true
    bufferDepthOf(x34770_d1_b0) = 1
    val x34771_d0_b0 = SRAM(size=2048, banking=Strided(banks=1, stride=2048)).name("x34771_d0_b0").ctrl(x36879).srcCtx("CellsPar.scala:143:23:bias") // x34771 = SRAMNew(ArrayBuffer(Const(1), Const(2048)))
    isAccum(x34771_d0_b0) = false
    bufferDepthOf(x34771_d0_b0) = 1
    val x34771_d1_b0 = SRAM(size=2048, banking=Strided(banks=1, stride=2048)).name("x34771_d1_b0").ctrl(x36879).srcCtx("CellsPar.scala:143:23:bias") // x34771 = SRAMNew(ArrayBuffer(Const(1), Const(2048)))
    isAccum(x34771_d1_b0) = false
    bufferDepthOf(x34771_d1_b0) = 1
    val x34771_d2_b0 = SRAM(size=2048, banking=Strided(banks=1, stride=2048)).name("x34771_d2_b0").ctrl(x36879).srcCtx("CellsPar.scala:143:23:bias") // x34771 = SRAMNew(ArrayBuffer(Const(1), Const(2048)))
    isAccum(x34771_d2_b0) = false
    bufferDepthOf(x34771_d2_b0) = 1
    val x34771_d3_b0 = SRAM(size=2048, banking=Strided(banks=1, stride=2048)).name("x34771_d3_b0").ctrl(x36879).srcCtx("CellsPar.scala:143:23:bias") // x34771 = SRAMNew(ArrayBuffer(Const(1), Const(2048)))
    isAccum(x34771_d3_b0) = false
    bufferDepthOf(x34771_d3_b0) = 1
    val x34772_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34772_d0_b0").ctrl(x36879).srcCtx("CellsPar.scala:139:20") // x34772 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34772_d0_b0) = false
    bufferDepthOf(x34772_d0_b0) = 1
    val x34772_d1_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34772_d1_b0").ctrl(x36879).srcCtx("CellsPar.scala:139:20") // x34772 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34772_d1_b0) = false
    bufferDepthOf(x34772_d1_b0) = 1
    val x34772_d2_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34772_d2_b0").ctrl(x36879).srcCtx("CellsPar.scala:139:20") // x34772 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34772_d2_b0) = false
    bufferDepthOf(x34772_d2_b0) = 1
    val x34772_d3_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34772_d3_b0").ctrl(x36879).srcCtx("CellsPar.scala:139:20") // x34772 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34772_d3_b0) = false
    bufferDepthOf(x34772_d3_b0) = 1
    val x34772_d4_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34772_d4_b0").ctrl(x36879).srcCtx("CellsPar.scala:139:20") // x34772 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34772_d4_b0) = false
    bufferDepthOf(x34772_d4_b0) = 1
    val x34773_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34773_d0_b0").ctrl(x36879).srcCtx("CellsPar.scala:140:20") // x34773 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34773_d0_b0) = false
    bufferDepthOf(x34773_d0_b0) = 1
    val x34773_d1_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34773_d1_b0").ctrl(x36879).srcCtx("CellsPar.scala:140:20") // x34773 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34773_d1_b0) = true
    bufferDepthOf(x34773_d1_b0) = 1
    val x34774_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34774_d0_b0").ctrl(x36879).srcCtx("CellsPar.scala:141:45:targetSRAM") // x34774 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34774_d0_b0) = false
    bufferDepthOf(x34774_d0_b0) = 1
    val x34774_d1_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34774_d1_b0").ctrl(x36879).srcCtx("CellsPar.scala:141:45:targetSRAM") // x34774 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34774_d1_b0) = true
    bufferDepthOf(x34774_d1_b0) = 1
    val x34775_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34775_d0_b0").ctrl(x36879).srcCtx("CellsPar.scala:141:79:targetSRAM") // x34775 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34775_d0_b0) = false
    bufferDepthOf(x34775_d0_b0) = 1
    val x34775_d1_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34775_d1_b0").ctrl(x36879).srcCtx("CellsPar.scala:141:79:targetSRAM") // x34775 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34775_d1_b0) = true
    bufferDepthOf(x34775_d1_b0) = 1
    val x34776_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34776_d0_b0").ctrl(x36879).srcCtx("CellsPar.scala:142:44:targetSRAM") // x34776 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34776_d0_b0) = false
    bufferDepthOf(x34776_d0_b0) = 1
    val x34776_d1_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34776_d1_b0").ctrl(x36879).srcCtx("CellsPar.scala:142:44:targetSRAM") // x34776 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34776_d1_b0) = true
    bufferDepthOf(x34776_d1_b0) = 1
    val x34777_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34777_d0_b0").ctrl(x36879).srcCtx("CellsPar.scala:142:78:targetSRAM") // x34777 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34777_d0_b0) = false
    bufferDepthOf(x34777_d0_b0) = 1
    val x34777_d1_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x34777_d1_b0").ctrl(x36879).srcCtx("CellsPar.scala:142:78:targetSRAM") // x34777 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x34777_d1_b0) = true
    bufferDepthOf(x34777_d1_b0) = 1
    val x34778_d0_b0 = SRAM(size=2048, banking=Strided(banks=1, stride=2048)).name("x34778_d0_b0").ctrl(x36879).srcCtx("CellsPar.scala:143:23:bias") // x34778 = SRAMNew(ArrayBuffer(Const(1), Const(2048)))
    isAccum(x34778_d0_b0) = false
    bufferDepthOf(x34778_d0_b0) = 1
    val x34778_d1_b0 = SRAM(size=2048, banking=Strided(banks=1, stride=2048)).name("x34778_d1_b0").ctrl(x36879).srcCtx("CellsPar.scala:143:23:bias") // x34778 = SRAMNew(ArrayBuffer(Const(1), Const(2048)))
    isAccum(x34778_d1_b0) = false
    bufferDepthOf(x34778_d1_b0) = 1
    val x34778_d2_b0 = SRAM(size=2048, banking=Strided(banks=1, stride=2048)).name("x34778_d2_b0").ctrl(x36879).srcCtx("CellsPar.scala:143:23:bias") // x34778 = SRAMNew(ArrayBuffer(Const(1), Const(2048)))
    isAccum(x34778_d2_b0) = false
    bufferDepthOf(x34778_d2_b0) = 1
    val x34778_d3_b0 = SRAM(size=2048, banking=Strided(banks=1, stride=2048)).name("x34778_d3_b0").ctrl(x36879).srcCtx("CellsPar.scala:143:23:bias") // x34778 = SRAMNew(ArrayBuffer(Const(1), Const(2048)))
    isAccum(x34778_d3_b0) = false
    bufferDepthOf(x34778_d3_b0) = 1
    val x34779 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x34779").ctrl(x36879).srcCtx("CellsPar.scala:161:12") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x34780 = CounterChain(List(x34779)).name("x34780").ctrl(x36879).srcCtx("CellsPar.scala:161:12") // CounterChainNew(List(x34779))
    val x34802 = LoopController(style=StreamPipe, level=OuterControl, cchain=x34780).name("x34802").ctrl(x36879).srcCtx("CellsPar.scala:161:12") // UnrolledForeach(List(Const(true)),x34780,Block(Const(())),List(List(b22198)),List(List(b22199)))
    val b22198 = CounterIter(x34779, Some(0)).name("b22198").ctrl(x34802) // b22198
    val b22199 = Const(true).name("b22199").ctrl(x34802) // b22199
    val b36889 = StreamOut(field="offset").name("b36889").ctrl(x34802).srcCtx("CellsPar.scala:161:12") // x34781 = StreamOutNew(BurstCmdBus)
    isAccum(b36889) = false
    bufferDepthOf(b36889) = 1
    val b36890 = StreamOut(field="size").name("b36890").ctrl(x34802).srcCtx("CellsPar.scala:161:12") // x34781 = StreamOutNew(BurstCmdBus)
    isAccum(b36890) = false
    bufferDepthOf(b36890) = 1
    val x34782 = StreamIn(field="data").name("x34782").ctrl(x34802).srcCtx("CellsPar.scala:161:12") // x34782 = StreamInNew(BurstDataBus())
    isAccum(x34782) = false
    bufferDepthOf(x34782) = 1
    val x34793 = UnitController(style=SeqPipe, level=InnerControl).name("x34793").ctrl(x34802).srcCtx("CellsPar.scala:161:12") // UnitPipe(List(b22199),Block(x34792))
    val x34783 = b22198 // FixConvert(b22198,TRUE,_32,_0) (Same Type. No op)
    val x34784 = OpDef(op=FixSla, inputs=List(x34783, Const(11))).name("x34784").ctrl(x34793).srcCtx("CellsPar.scala:161:12") // FixLsh(x34783,Const(11))
    val x34785 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x34786 = OpDef(op=FixAdd, inputs=List(x34784, x34785)).name("x34786").ctrl(x34793).srcCtx("CellsPar.scala:161:12") // FixAdd(x34784,x34785)
    val x34787 = OpDef(op=FixSla, inputs=List(x34786, Const(2))).name("x34787").ctrl(x34793).srcCtx("CellsPar.scala:161:12") // FixLsh(x34786,Const(2))
    val x34788 = x34787 // FixConvert(x34787,TRUE,_64,_0)
    val x34789 = DramAddress(x34447).name("x34789").ctrl(x34793).srcCtx("CellsPar.scala:161:12") // GetDRAMAddress(x34447)
    val x34791_x34790 = OpDef(op=FixAdd, inputs=List(x34788, x34789)).name("x34791_x34790").ctrl(x34793).srcCtx("CellsPar.scala:161:12") // FixAdd(x34788,x34789)
    // x34791 = SimpleStruct(ArrayBuffer((offset,x34790), (size,Const(8192)), (isLoad,Const(true))))
    val x34792_b36891_b36889 = WriteMem(b36889, x34791_x34790).name("x34792_b36891_b36889").ctrl(x34793).srcCtx("CellsPar.scala:161:12") // StreamWrite(x34781,x34791,b22199)
    val x34792_b36892_b36890 = WriteMem(b36890, Const(8192)).name("x34792_b36892_b36890").ctrl(x34793).srcCtx("CellsPar.scala:161:12") // StreamWrite(x34781,x34791,b22199)
    val x34794 = FringeDenseLoad(dram=List(x34447), cmdStream=List(b36889, b36890), dataStream=List(x34782)).name("x34794").ctrl(x34802).srcCtx("CellsPar.scala:161:12") // FringeDenseLoad(x34447,x34781,x34782)
    val x34795 = Counter(min=Const(0), max=Const(2048), step=Const(1), par=1).name("x34795").ctrl(x34802).srcCtx("CellsPar.scala:161:12") // CounterNew(Const(0),Const(2048),Const(1),Const(1))
    val x34796 = CounterChain(List(x34795)).name("x34796").ctrl(x34802).srcCtx("CellsPar.scala:161:12") // CounterChainNew(List(x34795))
    val x34801 = LoopController(style=InnerPipe, level=InnerControl, cchain=x34796).name("x34801").ctrl(x34802).srcCtx("CellsPar.scala:161:12") // UnrolledForeach(List(b22199),x34796,Block(Const(())),List(List(b22216)),List(List(b22217)))
    val b22216 = CounterIter(x34795, None).name("b22216").ctrl(x34801) // b22216
    val b22217 = Const(true).name("b22217").ctrl(x34801) // b22217
    val x34797 = OpDef(op=BitAnd, inputs=List(b22217, b22199)).name("x34797").ctrl(x34801).srcCtx("UnrollingBase.scala:28:66") // And(b22217,b22199)
    val x34798_x34798 = ReadMem(x34782).name("x34798_x34798").ctrl(x34801).srcCtx("CellsPar.scala:161:12") // ParStreamRead(x34782,List(x34797))
    val x34799_x34799 = x34798_x34798 // x34799 = VectorApply(x34798,0)
    val x34800 = StoreBanks(List(x34757_d0_b0, x34757_d1_b0, x34757_d2_b0, x34757_d3_b0), List(b22198, b22216), x34799_x34799).name("x34800").ctrl(x34801).srcCtx("CellsPar.scala:161:12") // ParSRAMStore(x34757,List(List(b22198, b22216)),List(x34799),List(x34797))
    val x34803 = Counter(min=Const(0), max=Const(512), step=Const(1), par=1).name("x34803").ctrl(x36879).srcCtx("CellsPar.scala:162:50") // CounterNew(Const(0),Const(512),Const(1),Const(1))
    val x34804 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x34804").ctrl(x36879).srcCtx("CellsPar.scala:162:27") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x34805 = CounterChain(List(x34804,x34803)).name("x34805").ctrl(x36879).srcCtx("CellsPar.scala:162:63") // CounterChainNew(List(x34804, x34803))
    val x34810 = LoopController(style=MetaPipe, level=OuterControl, cchain=x34805).name("x34810").ctrl(x36879).srcCtx("CellsPar.scala:162:63") // UnrolledForeach(List(Const(true)),x34805,Block(Const(())),List(List(b22227), List(b22228)),List(List(b22229), List(b22230)))
    val b22227 = CounterIter(x34804, Some(0)).name("b22227").ctrl(x34810) // b22227
    val b22229 = Const(true).name("b22229").ctrl(x34810) // b22229
    val b22228 = CounterIter(x34803, Some(0)).name("b22228").ctrl(x34810) // b22228
    val b22230 = Const(true).name("b22230").ctrl(x34810) // b22230
    val x34809 = UnitController(style=SeqPipe, level=InnerControl).name("x34809").ctrl(x34810).srcCtx("CellsPar.scala:163:18") // UnitPipe(List(b22229, b22230),Block(Const(())))
    val x34806 = OpDef(op=BitAnd, inputs=List(b22229, b22230)).name("x34806").ctrl(x34809).srcCtx("UnrollingBase.scala:28:66") // And(b22229,b22230)
    val x34807 = StoreBanks(List(x34752_d0_b0), List(b22227, b22228), Const(0.0)).name("x34807").ctrl(x34809).srcCtx("CellsPar.scala:164:18") // SRAMStore(x34752,ArrayBuffer(Const(1), Const(512)),List(b22227, b22228),Const(0),Const(0),x34806)
    val x34808 = StoreBanks(List(x34751_d0_b0, x34751_d5_b0, x34751_d1_b0, x34751_d6_b0, x34751_d2_b0, x34751_d7_b0, x34751_d3_b0, x34751_d4_b0), List(b22227, b22228), Const(0.0)).name("x34808").ctrl(x34809).srcCtx("CellsPar.scala:165:18") // SRAMStore(x34751,ArrayBuffer(Const(1), Const(512)),List(b22227, b22228),Const(0),Const(0),x34806)
    val x34811 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x34811").ctrl(x36879).srcCtx("CellsPar.scala:161:12") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x34812 = CounterChain(List(x34811)).name("x34812").ctrl(x36879).srcCtx("CellsPar.scala:161:12") // CounterChainNew(List(x34811))
    val x34834 = LoopController(style=StreamPipe, level=OuterControl, cchain=x34812).name("x34834").ctrl(x36879).srcCtx("CellsPar.scala:161:12") // UnrolledForeach(List(Const(true)),x34812,Block(Const(())),List(List(b22239)),List(List(b22240)))
    val b22239 = CounterIter(x34811, Some(0)).name("b22239").ctrl(x34834) // b22239
    val b22240 = Const(true).name("b22240").ctrl(x34834) // b22240
    val b36893 = StreamOut(field="offset").name("b36893").ctrl(x34834).srcCtx("CellsPar.scala:161:12") // x34813 = StreamOutNew(BurstCmdBus)
    isAccum(b36893) = false
    bufferDepthOf(b36893) = 1
    val b36894 = StreamOut(field="size").name("b36894").ctrl(x34834).srcCtx("CellsPar.scala:161:12") // x34813 = StreamOutNew(BurstCmdBus)
    isAccum(b36894) = false
    bufferDepthOf(b36894) = 1
    val x34814 = StreamIn(field="data").name("x34814").ctrl(x34834).srcCtx("CellsPar.scala:161:12") // x34814 = StreamInNew(BurstDataBus())
    isAccum(x34814) = false
    bufferDepthOf(x34814) = 1
    val x34825 = UnitController(style=SeqPipe, level=InnerControl).name("x34825").ctrl(x34834).srcCtx("CellsPar.scala:161:12") // UnitPipe(List(b22240),Block(x34824))
    val x34815 = b22239 // FixConvert(b22239,TRUE,_32,_0) (Same Type. No op)
    val x34816 = OpDef(op=FixSla, inputs=List(x34815, Const(11))).name("x34816").ctrl(x34825).srcCtx("CellsPar.scala:161:12") // FixLsh(x34815,Const(11))
    val x34817 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x34818 = OpDef(op=FixAdd, inputs=List(x34816, x34817)).name("x34818").ctrl(x34825).srcCtx("CellsPar.scala:161:12") // FixAdd(x34816,x34817)
    val x34819 = OpDef(op=FixSla, inputs=List(x34818, Const(2))).name("x34819").ctrl(x34825).srcCtx("CellsPar.scala:161:12") // FixLsh(x34818,Const(2))
    val x34820 = x34819 // FixConvert(x34819,TRUE,_64,_0)
    val x34821 = DramAddress(x34523).name("x34821").ctrl(x34825).srcCtx("CellsPar.scala:161:12") // GetDRAMAddress(x34523)
    val x34823_x34822 = OpDef(op=FixAdd, inputs=List(x34820, x34821)).name("x34823_x34822").ctrl(x34825).srcCtx("CellsPar.scala:161:12") // FixAdd(x34820,x34821)
    // x34823 = SimpleStruct(ArrayBuffer((offset,x34822), (size,Const(8192)), (isLoad,Const(true))))
    val x34824_b36895_b36893 = WriteMem(b36893, x34823_x34822).name("x34824_b36895_b36893").ctrl(x34825).srcCtx("CellsPar.scala:161:12") // StreamWrite(x34813,x34823,b22240)
    val x34824_b36896_b36894 = WriteMem(b36894, Const(8192)).name("x34824_b36896_b36894").ctrl(x34825).srcCtx("CellsPar.scala:161:12") // StreamWrite(x34813,x34823,b22240)
    val x34826 = FringeDenseLoad(dram=List(x34523), cmdStream=List(b36893, b36894), dataStream=List(x34814)).name("x34826").ctrl(x34834).srcCtx("CellsPar.scala:161:12") // FringeDenseLoad(x34523,x34813,x34814)
    val x34827 = Counter(min=Const(0), max=Const(2048), step=Const(1), par=1).name("x34827").ctrl(x34834).srcCtx("CellsPar.scala:161:12") // CounterNew(Const(0),Const(2048),Const(1),Const(1))
    val x34828 = CounterChain(List(x34827)).name("x34828").ctrl(x34834).srcCtx("CellsPar.scala:161:12") // CounterChainNew(List(x34827))
    val x34833 = LoopController(style=InnerPipe, level=InnerControl, cchain=x34828).name("x34833").ctrl(x34834).srcCtx("CellsPar.scala:161:12") // UnrolledForeach(List(b22240),x34828,Block(Const(())),List(List(b22257)),List(List(b22258)))
    val b22257 = CounterIter(x34827, None).name("b22257").ctrl(x34833) // b22257
    val b22258 = Const(true).name("b22258").ctrl(x34833) // b22258
    val x34829 = OpDef(op=BitAnd, inputs=List(b22258, b22240)).name("x34829").ctrl(x34833).srcCtx("UnrollingBase.scala:28:66") // And(b22258,b22240)
    val x34830_x34830 = ReadMem(x34814).name("x34830_x34830").ctrl(x34833).srcCtx("CellsPar.scala:161:12") // ParStreamRead(x34814,List(x34829))
    val x34831_x34831 = x34830_x34830 // x34831 = VectorApply(x34830,0)
    val x34832 = StoreBanks(List(x34764_d0_b0, x34764_d1_b0, x34764_d2_b0, x34764_d3_b0), List(b22239, b22257), x34831_x34831).name("x34832").ctrl(x34833).srcCtx("CellsPar.scala:161:12") // ParSRAMStore(x34764,List(List(b22239, b22257)),List(x34831),List(x34829))
    val x34835 = Counter(min=Const(0), max=Const(512), step=Const(1), par=1).name("x34835").ctrl(x36879).srcCtx("CellsPar.scala:162:50") // CounterNew(Const(0),Const(512),Const(1),Const(1))
    val x34836 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x34836").ctrl(x36879).srcCtx("CellsPar.scala:162:27") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x34837 = CounterChain(List(x34836,x34835)).name("x34837").ctrl(x36879).srcCtx("CellsPar.scala:162:63") // CounterChainNew(List(x34836, x34835))
    val x34842 = LoopController(style=MetaPipe, level=OuterControl, cchain=x34837).name("x34842").ctrl(x36879).srcCtx("CellsPar.scala:162:63") // UnrolledForeach(List(Const(true)),x34837,Block(Const(())),List(List(b22268), List(b22269)),List(List(b22270), List(b22271)))
    val b22268 = CounterIter(x34836, Some(0)).name("b22268").ctrl(x34842) // b22268
    val b22270 = Const(true).name("b22270").ctrl(x34842) // b22270
    val b22269 = CounterIter(x34835, Some(0)).name("b22269").ctrl(x34842) // b22269
    val b22271 = Const(true).name("b22271").ctrl(x34842) // b22271
    val x34841 = UnitController(style=SeqPipe, level=InnerControl).name("x34841").ctrl(x34842).srcCtx("CellsPar.scala:163:18") // UnitPipe(List(b22270, b22271),Block(Const(())))
    val x34838 = OpDef(op=BitAnd, inputs=List(b22270, b22271)).name("x34838").ctrl(x34841).srcCtx("UnrollingBase.scala:28:66") // And(b22270,b22271)
    val x34839 = StoreBanks(List(x34759_d0_b0), List(b22268, b22269), Const(0.0)).name("x34839").ctrl(x34841).srcCtx("CellsPar.scala:164:18") // SRAMStore(x34759,ArrayBuffer(Const(1), Const(512)),List(b22268, b22269),Const(0),Const(0),x34838)
    val x34840 = StoreBanks(List(x34758_d0_b0, x34758_d5_b0, x34758_d1_b0, x34758_d6_b0, x34758_d2_b0, x34758_d7_b0, x34758_d3_b0, x34758_d8_b0, x34758_d4_b0), List(b22268, b22269), Const(0.0)).name("x34840").ctrl(x34841).srcCtx("CellsPar.scala:165:18") // SRAMStore(x34758,ArrayBuffer(Const(1), Const(512)),List(b22268, b22269),Const(0),Const(0),x34838)
    val x34843 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x34843").ctrl(x36879).srcCtx("CellsPar.scala:161:12") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x34844 = CounterChain(List(x34843)).name("x34844").ctrl(x36879).srcCtx("CellsPar.scala:161:12") // CounterChainNew(List(x34843))
    val x34866 = LoopController(style=StreamPipe, level=OuterControl, cchain=x34844).name("x34866").ctrl(x36879).srcCtx("CellsPar.scala:161:12") // UnrolledForeach(List(Const(true)),x34844,Block(Const(())),List(List(b22280)),List(List(b22281)))
    val b22280 = CounterIter(x34843, Some(0)).name("b22280").ctrl(x34866) // b22280
    val b22281 = Const(true).name("b22281").ctrl(x34866) // b22281
    val b36897 = StreamOut(field="offset").name("b36897").ctrl(x34866).srcCtx("CellsPar.scala:161:12") // x34845 = StreamOutNew(BurstCmdBus)
    isAccum(b36897) = false
    bufferDepthOf(b36897) = 1
    val b36898 = StreamOut(field="size").name("b36898").ctrl(x34866).srcCtx("CellsPar.scala:161:12") // x34845 = StreamOutNew(BurstCmdBus)
    isAccum(b36898) = false
    bufferDepthOf(b36898) = 1
    val x34846 = StreamIn(field="data").name("x34846").ctrl(x34866).srcCtx("CellsPar.scala:161:12") // x34846 = StreamInNew(BurstDataBus())
    isAccum(x34846) = false
    bufferDepthOf(x34846) = 1
    val x34857 = UnitController(style=SeqPipe, level=InnerControl).name("x34857").ctrl(x34866).srcCtx("CellsPar.scala:161:12") // UnitPipe(List(b22281),Block(x34856))
    val x34847 = b22280 // FixConvert(b22280,TRUE,_32,_0) (Same Type. No op)
    val x34848 = OpDef(op=FixSla, inputs=List(x34847, Const(11))).name("x34848").ctrl(x34857).srcCtx("CellsPar.scala:161:12") // FixLsh(x34847,Const(11))
    val x34849 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x34850 = OpDef(op=FixAdd, inputs=List(x34848, x34849)).name("x34850").ctrl(x34857).srcCtx("CellsPar.scala:161:12") // FixAdd(x34848,x34849)
    val x34851 = OpDef(op=FixSla, inputs=List(x34850, Const(2))).name("x34851").ctrl(x34857).srcCtx("CellsPar.scala:161:12") // FixLsh(x34850,Const(2))
    val x34852 = x34851 // FixConvert(x34851,TRUE,_64,_0)
    val x34853 = DramAddress(x34599).name("x34853").ctrl(x34857).srcCtx("CellsPar.scala:161:12") // GetDRAMAddress(x34599)
    val x34855_x34854 = OpDef(op=FixAdd, inputs=List(x34852, x34853)).name("x34855_x34854").ctrl(x34857).srcCtx("CellsPar.scala:161:12") // FixAdd(x34852,x34853)
    // x34855 = SimpleStruct(ArrayBuffer((offset,x34854), (size,Const(8192)), (isLoad,Const(true))))
    val x34856_b36899_b36897 = WriteMem(b36897, x34855_x34854).name("x34856_b36899_b36897").ctrl(x34857).srcCtx("CellsPar.scala:161:12") // StreamWrite(x34845,x34855,b22281)
    val x34856_b36900_b36898 = WriteMem(b36898, Const(8192)).name("x34856_b36900_b36898").ctrl(x34857).srcCtx("CellsPar.scala:161:12") // StreamWrite(x34845,x34855,b22281)
    val x34858 = FringeDenseLoad(dram=List(x34599), cmdStream=List(b36897, b36898), dataStream=List(x34846)).name("x34858").ctrl(x34866).srcCtx("CellsPar.scala:161:12") // FringeDenseLoad(x34599,x34845,x34846)
    val x34859 = Counter(min=Const(0), max=Const(2048), step=Const(1), par=1).name("x34859").ctrl(x34866).srcCtx("CellsPar.scala:161:12") // CounterNew(Const(0),Const(2048),Const(1),Const(1))
    val x34860 = CounterChain(List(x34859)).name("x34860").ctrl(x34866).srcCtx("CellsPar.scala:161:12") // CounterChainNew(List(x34859))
    val x34865 = LoopController(style=InnerPipe, level=InnerControl, cchain=x34860).name("x34865").ctrl(x34866).srcCtx("CellsPar.scala:161:12") // UnrolledForeach(List(b22281),x34860,Block(Const(())),List(List(b22298)),List(List(b22299)))
    val b22298 = CounterIter(x34859, None).name("b22298").ctrl(x34865) // b22298
    val b22299 = Const(true).name("b22299").ctrl(x34865) // b22299
    val x34861 = OpDef(op=BitAnd, inputs=List(b22299, b22281)).name("x34861").ctrl(x34865).srcCtx("UnrollingBase.scala:28:66") // And(b22299,b22281)
    val x34862_x34862 = ReadMem(x34846).name("x34862_x34862").ctrl(x34865).srcCtx("CellsPar.scala:161:12") // ParStreamRead(x34846,List(x34861))
    val x34863_x34863 = x34862_x34862 // x34863 = VectorApply(x34862,0)
    val x34864 = StoreBanks(List(x34771_d0_b0, x34771_d1_b0, x34771_d2_b0, x34771_d3_b0), List(b22280, b22298), x34863_x34863).name("x34864").ctrl(x34865).srcCtx("CellsPar.scala:161:12") // ParSRAMStore(x34771,List(List(b22280, b22298)),List(x34863),List(x34861))
    val x34867 = Counter(min=Const(0), max=Const(512), step=Const(1), par=1).name("x34867").ctrl(x36879).srcCtx("CellsPar.scala:162:50") // CounterNew(Const(0),Const(512),Const(1),Const(1))
    val x34868 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x34868").ctrl(x36879).srcCtx("CellsPar.scala:162:27") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x34869 = CounterChain(List(x34868,x34867)).name("x34869").ctrl(x36879).srcCtx("CellsPar.scala:162:63") // CounterChainNew(List(x34868, x34867))
    val x34874 = LoopController(style=MetaPipe, level=OuterControl, cchain=x34869).name("x34874").ctrl(x36879).srcCtx("CellsPar.scala:162:63") // UnrolledForeach(List(Const(true)),x34869,Block(Const(())),List(List(b22309), List(b22310)),List(List(b22311), List(b22312)))
    val b22309 = CounterIter(x34868, Some(0)).name("b22309").ctrl(x34874) // b22309
    val b22311 = Const(true).name("b22311").ctrl(x34874) // b22311
    val b22310 = CounterIter(x34867, Some(0)).name("b22310").ctrl(x34874) // b22310
    val b22312 = Const(true).name("b22312").ctrl(x34874) // b22312
    val x34873 = UnitController(style=SeqPipe, level=InnerControl).name("x34873").ctrl(x34874).srcCtx("CellsPar.scala:163:18") // UnitPipe(List(b22311, b22312),Block(Const(())))
    val x34870 = OpDef(op=BitAnd, inputs=List(b22311, b22312)).name("x34870").ctrl(x34873).srcCtx("UnrollingBase.scala:28:66") // And(b22311,b22312)
    val x34871 = StoreBanks(List(x34766_d0_b0), List(b22309, b22310), Const(0.0)).name("x34871").ctrl(x34873).srcCtx("CellsPar.scala:164:18") // SRAMStore(x34766,ArrayBuffer(Const(1), Const(512)),List(b22309, b22310),Const(0),Const(0),x34870)
    val x34872 = StoreBanks(List(x34765_d0_b0, x34765_d5_b0, x34765_d1_b0, x34765_d6_b0, x34765_d2_b0, x34765_d7_b0, x34765_d3_b0, x34765_d8_b0, x34765_d4_b0), List(b22309, b22310), Const(0.0)).name("x34872").ctrl(x34873).srcCtx("CellsPar.scala:165:18") // SRAMStore(x34765,ArrayBuffer(Const(1), Const(512)),List(b22309, b22310),Const(0),Const(0),x34870)
    val x34875 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x34875").ctrl(x36879).srcCtx("CellsPar.scala:161:12") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x34876 = CounterChain(List(x34875)).name("x34876").ctrl(x36879).srcCtx("CellsPar.scala:161:12") // CounterChainNew(List(x34875))
    val x34898 = LoopController(style=StreamPipe, level=OuterControl, cchain=x34876).name("x34898").ctrl(x36879).srcCtx("CellsPar.scala:161:12") // UnrolledForeach(List(Const(true)),x34876,Block(Const(())),List(List(b22321)),List(List(b22322)))
    val b22321 = CounterIter(x34875, Some(0)).name("b22321").ctrl(x34898) // b22321
    val b22322 = Const(true).name("b22322").ctrl(x34898) // b22322
    val b36901 = StreamOut(field="offset").name("b36901").ctrl(x34898).srcCtx("CellsPar.scala:161:12") // x34877 = StreamOutNew(BurstCmdBus)
    isAccum(b36901) = false
    bufferDepthOf(b36901) = 1
    val b36902 = StreamOut(field="size").name("b36902").ctrl(x34898).srcCtx("CellsPar.scala:161:12") // x34877 = StreamOutNew(BurstCmdBus)
    isAccum(b36902) = false
    bufferDepthOf(b36902) = 1
    val x34878 = StreamIn(field="data").name("x34878").ctrl(x34898).srcCtx("CellsPar.scala:161:12") // x34878 = StreamInNew(BurstDataBus())
    isAccum(x34878) = false
    bufferDepthOf(x34878) = 1
    val x34889 = UnitController(style=SeqPipe, level=InnerControl).name("x34889").ctrl(x34898).srcCtx("CellsPar.scala:161:12") // UnitPipe(List(b22322),Block(x34888))
    val x34879 = b22321 // FixConvert(b22321,TRUE,_32,_0) (Same Type. No op)
    val x34880 = OpDef(op=FixSla, inputs=List(x34879, Const(11))).name("x34880").ctrl(x34889).srcCtx("CellsPar.scala:161:12") // FixLsh(x34879,Const(11))
    val x34881 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x34882 = OpDef(op=FixAdd, inputs=List(x34880, x34881)).name("x34882").ctrl(x34889).srcCtx("CellsPar.scala:161:12") // FixAdd(x34880,x34881)
    val x34883 = OpDef(op=FixSla, inputs=List(x34882, Const(2))).name("x34883").ctrl(x34889).srcCtx("CellsPar.scala:161:12") // FixLsh(x34882,Const(2))
    val x34884 = x34883 // FixConvert(x34883,TRUE,_64,_0)
    val x34885 = DramAddress(x34675).name("x34885").ctrl(x34889).srcCtx("CellsPar.scala:161:12") // GetDRAMAddress(x34675)
    val x34887_x34886 = OpDef(op=FixAdd, inputs=List(x34884, x34885)).name("x34887_x34886").ctrl(x34889).srcCtx("CellsPar.scala:161:12") // FixAdd(x34884,x34885)
    // x34887 = SimpleStruct(ArrayBuffer((offset,x34886), (size,Const(8192)), (isLoad,Const(true))))
    val x34888_b36903_b36901 = WriteMem(b36901, x34887_x34886).name("x34888_b36903_b36901").ctrl(x34889).srcCtx("CellsPar.scala:161:12") // StreamWrite(x34877,x34887,b22322)
    val x34888_b36904_b36902 = WriteMem(b36902, Const(8192)).name("x34888_b36904_b36902").ctrl(x34889).srcCtx("CellsPar.scala:161:12") // StreamWrite(x34877,x34887,b22322)
    val x34890 = FringeDenseLoad(dram=List(x34675), cmdStream=List(b36901, b36902), dataStream=List(x34878)).name("x34890").ctrl(x34898).srcCtx("CellsPar.scala:161:12") // FringeDenseLoad(x34675,x34877,x34878)
    val x34891 = Counter(min=Const(0), max=Const(2048), step=Const(1), par=1).name("x34891").ctrl(x34898).srcCtx("CellsPar.scala:161:12") // CounterNew(Const(0),Const(2048),Const(1),Const(1))
    val x34892 = CounterChain(List(x34891)).name("x34892").ctrl(x34898).srcCtx("CellsPar.scala:161:12") // CounterChainNew(List(x34891))
    val x34897 = LoopController(style=InnerPipe, level=InnerControl, cchain=x34892).name("x34897").ctrl(x34898).srcCtx("CellsPar.scala:161:12") // UnrolledForeach(List(b22322),x34892,Block(Const(())),List(List(b22339)),List(List(b22340)))
    val b22339 = CounterIter(x34891, None).name("b22339").ctrl(x34897) // b22339
    val b22340 = Const(true).name("b22340").ctrl(x34897) // b22340
    val x34893 = OpDef(op=BitAnd, inputs=List(b22340, b22322)).name("x34893").ctrl(x34897).srcCtx("UnrollingBase.scala:28:66") // And(b22340,b22322)
    val x34894_x34894 = ReadMem(x34878).name("x34894_x34894").ctrl(x34897).srcCtx("CellsPar.scala:161:12") // ParStreamRead(x34878,List(x34893))
    val x34895_x34895 = x34894_x34894 // x34895 = VectorApply(x34894,0)
    val x34896 = StoreBanks(List(x34778_d0_b0, x34778_d1_b0, x34778_d2_b0, x34778_d3_b0), List(b22321, b22339), x34895_x34895).name("x34896").ctrl(x34897).srcCtx("CellsPar.scala:161:12") // ParSRAMStore(x34778,List(List(b22321, b22339)),List(x34895),List(x34893))
    val x34899 = Counter(min=Const(0), max=Const(512), step=Const(1), par=1).name("x34899").ctrl(x36879).srcCtx("CellsPar.scala:162:50") // CounterNew(Const(0),Const(512),Const(1),Const(1))
    val x34900 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x34900").ctrl(x36879).srcCtx("CellsPar.scala:162:27") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x34901 = CounterChain(List(x34900,x34899)).name("x34901").ctrl(x36879).srcCtx("CellsPar.scala:162:63") // CounterChainNew(List(x34900, x34899))
    val x34906 = LoopController(style=MetaPipe, level=OuterControl, cchain=x34901).name("x34906").ctrl(x36879).srcCtx("CellsPar.scala:162:63") // UnrolledForeach(List(Const(true)),x34901,Block(Const(())),List(List(b22350), List(b22351)),List(List(b22352), List(b22353)))
    val b22350 = CounterIter(x34900, Some(0)).name("b22350").ctrl(x34906) // b22350
    val b22352 = Const(true).name("b22352").ctrl(x34906) // b22352
    val b22351 = CounterIter(x34899, Some(0)).name("b22351").ctrl(x34906) // b22351
    val b22353 = Const(true).name("b22353").ctrl(x34906) // b22353
    val x34905 = UnitController(style=SeqPipe, level=InnerControl).name("x34905").ctrl(x34906).srcCtx("CellsPar.scala:163:18") // UnitPipe(List(b22352, b22353),Block(Const(())))
    val x34902 = OpDef(op=BitAnd, inputs=List(b22352, b22353)).name("x34902").ctrl(x34905).srcCtx("UnrollingBase.scala:28:66") // And(b22352,b22353)
    val x34903 = StoreBanks(List(x34773_d0_b0, x34773_d1_b0), List(b22350, b22351), Const(0.0)).name("x34903").ctrl(x34905).srcCtx("CellsPar.scala:164:18") // SRAMStore(x34773,ArrayBuffer(Const(1), Const(512)),List(b22350, b22351),Const(0),Const(0),x34902)
    val x34904 = StoreBanks(List(x34772_d0_b0, x34772_d1_b0, x34772_d2_b0, x34772_d3_b0, x34772_d4_b0), List(b22350, b22351), Const(0.0)).name("x34904").ctrl(x34905).srcCtx("CellsPar.scala:165:18") // SRAMStore(x34772,ArrayBuffer(Const(1), Const(512)),List(b22350, b22351),Const(0),Const(0),x34902)
    val x34907 = Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x34907").ctrl(x36879).srcCtx("NMTDSE.scala:478:35") // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x34908 = CounterChain(List(x34907)).name("x34908").ctrl(x36879).srcCtx("NMTDSE.scala:478:41") // CounterChainNew(List(x34907))
    val x36822 = LoopController(style=SeqPipe, level=OuterControl, cchain=x34908).name("x36822").ctrl(x36879).srcCtx("NMTDSE.scala:478:41") // UnrolledForeach(List(Const(true)),x34908,Block(Const(())),List(List(b22363)),List(List(b22364)))
    val b22363 = CounterIter(x34907, Some(0)).name("b22363").ctrl(x36822) // b22363
    val b22364 = Const(true).name("b22364").ctrl(x36822) // b22364
    val x34910 = UnitController(style=SeqPipe, level=InnerControl).name("x34910").ctrl(x36822).srcCtx("NMTDSE.scala:478:41") // UnitPipe(List(b22364),Block(Const(())))
    val x34909 = OpDef(op=FixAdd, inputs=List(b22363, Const(1))).name("x34909").ctrl(x34910).srcCtx("NMTDSE.scala:479:34") // FixAdd(b22363,Const(1))
    val x34911 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x34911").ctrl(x36822).srcCtx("NMTDSE.scala:479:24") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x34912 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x34912").ctrl(x36822).srcCtx("NMTDSE.scala:479:24") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x34913 = CounterChain(List(x34911,x34912)).name("x34913").ctrl(x36822).srcCtx("NMTDSE.scala:479:24") // CounterChainNew(List(x34911, x34912))
    val x34943 = LoopController(style=StreamPipe, level=OuterControl, cchain=x34913).name("x34943").ctrl(x36822).srcCtx("NMTDSE.scala:479:24") // UnrolledForeach(List(b22364),x34913,Block(Const(())),List(List(b22370), List(b22371)),List(List(b22372), List(b22373)))
    val b22370 = CounterIter(x34911, Some(0)).name("b22370").ctrl(x34943) // b22370
    val b22372 = Const(true).name("b22372").ctrl(x34943) // b22372
    val b22371 = CounterIter(x34912, Some(0)).name("b22371").ctrl(x34943) // b22371
    val b22373 = Const(true).name("b22373").ctrl(x34943) // b22373
    val b36905 = StreamOut(field="offset").name("b36905").ctrl(x34943).srcCtx("NMTDSE.scala:479:24") // x34914 = StreamOutNew(BurstCmdBus)
    isAccum(b36905) = false
    bufferDepthOf(b36905) = 1
    val b36906 = StreamOut(field="size").name("b36906").ctrl(x34943).srcCtx("NMTDSE.scala:479:24") // x34914 = StreamOutNew(BurstCmdBus)
    isAccum(b36906) = false
    bufferDepthOf(b36906) = 1
    val x34915 = StreamIn(field="data").name("x34915").ctrl(x34943).srcCtx("NMTDSE.scala:479:24") // x34915 = StreamInNew(BurstDataBus())
    isAccum(x34915) = false
    bufferDepthOf(x34915) = 1
    val x34932 = UnitController(style=SeqPipe, level=InnerControl).name("x34932").ctrl(x34943).srcCtx("NMTDSE.scala:479:24") // UnitPipe(List(b22372, b22373, b22364),Block(x34931))
    val x34916 = OpDef(op=FixAdd, inputs=List(b22363, b22371)).name("x34916").ctrl(x34932).srcCtx("NMTDSE.scala:479:24") // FixAdd(b22363,b22371)
    val x34917 = b22370 // FixConvert(b22370,TRUE,_32,_0) (Same Type. No op)
    val x34918 = OpDef(op=FixSla, inputs=List(x34917, Const(11))).name("x34918").ctrl(x34932).srcCtx("NMTDSE.scala:479:24") // FixLsh(x34917,Const(11))
    val x34919 = x34916 // FixConvert(x34916,TRUE,_32,_0) (Same Type. No op)
    val x34920 = OpDef(op=FixSla, inputs=List(x34919, Const(9))).name("x34920").ctrl(x34932).srcCtx("NMTDSE.scala:479:24") // FixLsh(x34919,Const(9))
    val x34921 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x34922 = OpDef(op=FixAdd, inputs=List(x34918, x34920)).name("x34922").ctrl(x34932).srcCtx("NMTDSE.scala:479:24") // FixAdd(x34918,x34920)
    val x34923 = OpDef(op=FixAdd, inputs=List(x34922, x34921)).name("x34923").ctrl(x34932).srcCtx("NMTDSE.scala:479:24") // FixAdd(x34922,x34921)
    val x34924 = OpDef(op=FixSla, inputs=List(x34923, Const(2))).name("x34924").ctrl(x34932).srcCtx("NMTDSE.scala:479:24") // FixLsh(x34923,Const(2))
    val x34925 = x34924 // FixConvert(x34924,TRUE,_64,_0)
    val x34926 = DramAddress(x34438).name("x34926").ctrl(x34932).srcCtx("NMTDSE.scala:479:24") // GetDRAMAddress(x34438)
    val x34928_x34927 = OpDef(op=FixAdd, inputs=List(x34925, x34926)).name("x34928_x34927").ctrl(x34932).srcCtx("NMTDSE.scala:479:24") // FixAdd(x34925,x34926)
    // x34928 = SimpleStruct(ArrayBuffer((offset,x34927), (size,Const(2048)), (isLoad,Const(true))))
    val x34929 = OpDef(op=BitAnd, inputs=List(b22372, b22373)).name("x34929").ctrl(x34932).srcCtx("UnrollingBase.scala:28:66") // And(b22372,b22373)
    val x34930 = OpDef(op=BitAnd, inputs=List(x34929, b22364)).name("x34930").ctrl(x34932).srcCtx("UnrollingBase.scala:28:66") // And(x34929,b22364)
    val x34931_b36907_b36905 = WriteMem(b36905, x34928_x34927).name("x34931_b36907_b36905").ctrl(x34932).srcCtx("NMTDSE.scala:479:24") // StreamWrite(x34914,x34928,x34930)
    val x34931_b36908_b36906 = WriteMem(b36906, Const(2048)).name("x34931_b36908_b36906").ctrl(x34932).srcCtx("NMTDSE.scala:479:24") // StreamWrite(x34914,x34928,x34930)
    val x34933 = FringeDenseLoad(dram=List(x34438), cmdStream=List(b36905, b36906), dataStream=List(x34915)).name("x34933").ctrl(x34943).srcCtx("NMTDSE.scala:479:24") // FringeDenseLoad(x34438,x34914,x34915)
    val x34934 = Counter(min=Const(0), max=Const(512), step=Const(1), par=1).name("x34934").ctrl(x34943).srcCtx("NMTDSE.scala:479:24") // CounterNew(Const(0),Const(512),Const(1),Const(1))
    val x34935 = CounterChain(List(x34934)).name("x34935").ctrl(x34943).srcCtx("NMTDSE.scala:479:24") // CounterChainNew(List(x34934))
    val x34942 = LoopController(style=InnerPipe, level=InnerControl, cchain=x34935).name("x34942").ctrl(x34943).srcCtx("NMTDSE.scala:479:24") // UnrolledForeach(List(b22372, b22373, b22364),x34935,Block(Const(())),List(List(b22396)),List(List(b22397)))
    val b22396 = CounterIter(x34934, None).name("b22396").ctrl(x34942) // b22396
    val b22397 = Const(true).name("b22397").ctrl(x34942) // b22397
    val x34936 = OpDef(op=BitAnd, inputs=List(b22397, b22372)).name("x34936").ctrl(x34942).srcCtx("UnrollingBase.scala:28:66") // And(b22397,b22372)
    val x34937 = OpDef(op=BitAnd, inputs=List(b22373, b22364)).name("x34937").ctrl(x34942).srcCtx("UnrollingBase.scala:28:66") // And(b22373,b22364)
    val x34938 = OpDef(op=BitAnd, inputs=List(x34936, x34937)).name("x34938").ctrl(x34942).srcCtx("UnrollingBase.scala:28:66") // And(x34936,x34937)
    val x34939_x34939 = ReadMem(x34915).name("x34939_x34939").ctrl(x34942).srcCtx("NMTDSE.scala:479:24") // ParStreamRead(x34915,List(x34938))
    val x34940_x34940 = x34939_x34939 // x34940 = VectorApply(x34939,0)
    val x34941 = StoreBanks(List(x34750_d0_b0, x34750_d1_b0, x34750_d2_b0, x34750_d3_b0), List(b22370, b22396), x34940_x34940).name("x34941").ctrl(x34942).srcCtx("NMTDSE.scala:479:24") // ParSRAMStore(x34750,List(List(b22370, b22396)),List(x34940),List(x34938))
    val x34944 = Counter(min=Const(0), max=Const(512), step=Const(16), par=1).name("x34944").ctrl(x36822).srcCtx("CellsPar.scala:190:58") // CounterNew(Const(0),Const(512),Const(16),Const(1))
    val x34945 = Counter(min=Const(0), max=Const(1536), step=Const(16), par=1).name("x34945").ctrl(x36822).srcCtx("CellsPar.scala:190:33") // CounterNew(Const(0),Const(1536),Const(16),Const(1))
    val x34946 = CounterChain(List(x34945,x34944)).name("x34946").ctrl(x36822).srcCtx("CellsPar.scala:190:72") // CounterChainNew(List(x34945, x34944))
    val x35051 = LoopController(style=MetaPipe, level=OuterControl, cchain=x34946).name("x35051").ctrl(x36822).srcCtx("CellsPar.scala:190:72") // UnrolledForeach(List(b22364),x34946,Block(Const(())),List(List(b22409), List(b22410)),List(List(b22411), List(b22412)))
    val b22409 = CounterIter(x34945, Some(0)).name("b22409").ctrl(x35051) // b22409
    val b22411 = Const(true).name("b22411").ctrl(x35051) // b22411
    val b22410 = CounterIter(x34944, Some(0)).name("b22410").ctrl(x35051) // b22410
    val b22412 = Const(true).name("b22412").ctrl(x35051) // b22412
    val x34947_d0_b0 = SRAM(size=256, banking=Strided(banks=1, stride=16)).name("x34947_d0_b0").ctrl(x35051).srcCtx("CellsPar.scala:191:33:tileKernel") // x34947 = SRAMNew(ArrayBuffer(Const(16), Const(16)))
    isAccum(x34947_d0_b0) = false
    bufferDepthOf(x34947_d0_b0) = 2
    val x34950 = UnitController(style=SeqPipe, level=InnerControl).name("x34950").ctrl(x35051).srcCtx("CellsPar.scala:190:72") // UnitPipe(List(b22411, b22412, b22364),Block(Const(())))
    val x34948 = OpDef(op=FixAdd, inputs=List(b22409, Const(16))).name("x34948").ctrl(x34950).srcCtx("CellsPar.scala:192:36") // FixAdd(b22409,Const(16))
    val x34949 = OpDef(op=FixAdd, inputs=List(b22410, Const(16))).name("x34949").ctrl(x34950).srcCtx("CellsPar.scala:192:45") // FixAdd(b22410,Const(16))
    val x34951 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x34951").ctrl(x35051).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x34952 = CounterChain(List(x34951)).name("x34952").ctrl(x35051).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x34951))
    val x34981 = LoopController(style=StreamPipe, level=OuterControl, cchain=x34952).name("x34981").ctrl(x35051).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b22411, b22412, b22364),x34952,Block(Const(())),List(List(b22419)),List(List(b22420)))
    val b22419 = CounterIter(x34951, Some(0)).name("b22419").ctrl(x34981) // b22419
    val b22420 = Const(true).name("b22420").ctrl(x34981) // b22420
    val b36909 = StreamOut(field="offset").name("b36909").ctrl(x34981).srcCtx("CellsPar.scala:192:20") // x34953 = StreamOutNew(BurstCmdBus)
    isAccum(b36909) = false
    bufferDepthOf(b36909) = 1
    val b36910 = StreamOut(field="size").name("b36910").ctrl(x34981).srcCtx("CellsPar.scala:192:20") // x34953 = StreamOutNew(BurstCmdBus)
    isAccum(b36910) = false
    bufferDepthOf(b36910) = 1
    val x34954 = StreamIn(field="data").name("x34954").ctrl(x34981).srcCtx("CellsPar.scala:192:20") // x34954 = StreamInNew(BurstDataBus())
    isAccum(x34954) = false
    bufferDepthOf(x34954) = 1
    val x34969 = UnitController(style=SeqPipe, level=InnerControl).name("x34969").ctrl(x34981).srcCtx("CellsPar.scala:192:20") // UnitPipe(List(b22420, b22411, b22412, b22364),Block(x34968))
    val x34955 = OpDef(op=FixAdd, inputs=List(b22409, b22419)).name("x34955").ctrl(x34969).srcCtx("CellsPar.scala:192:20") // FixAdd(b22409,b22419)
    val x34956 = x34955 // FixConvert(x34955,TRUE,_32,_0) (Same Type. No op)
    val x34957 = OpDef(op=FixSla, inputs=List(x34956, Const(9))).name("x34957").ctrl(x34969).srcCtx("CellsPar.scala:192:20") // FixLsh(x34956,Const(9))
    val x34958 = b22410 // FixConvert(b22410,TRUE,_32,_0) (Same Type. No op)
    val x34959 = OpDef(op=FixAdd, inputs=List(x34957, x34958)).name("x34959").ctrl(x34969).srcCtx("CellsPar.scala:192:20") // FixAdd(x34957,x34958)
    val x34960 = OpDef(op=FixSla, inputs=List(x34959, Const(2))).name("x34960").ctrl(x34969).srcCtx("CellsPar.scala:192:20") // FixLsh(x34959,Const(2))
    val x34961 = x34960 // FixConvert(x34960,TRUE,_64,_0)
    val x34962 = DramAddress(x34474).name("x34962").ctrl(x34969).srcCtx("CellsPar.scala:192:20") // GetDRAMAddress(x34474)
    val x34964_x34963 = OpDef(op=FixAdd, inputs=List(x34961, x34962)).name("x34964_x34963").ctrl(x34969).srcCtx("CellsPar.scala:192:20") // FixAdd(x34961,x34962)
    // x34964 = SimpleStruct(ArrayBuffer((offset,x34963), (size,Const(64)), (isLoad,Const(true))))
    val x34965 = OpDef(op=BitAnd, inputs=List(b22420, b22411)).name("x34965").ctrl(x34969).srcCtx("UnrollingBase.scala:28:66") // And(b22420,b22411)
    val x34966 = OpDef(op=BitAnd, inputs=List(b22412, b22364)).name("x34966").ctrl(x34969).srcCtx("UnrollingBase.scala:28:66") // And(b22412,b22364)
    val x34967 = OpDef(op=BitAnd, inputs=List(x34965, x34966)).name("x34967").ctrl(x34969).srcCtx("UnrollingBase.scala:28:66") // And(x34965,x34966)
    val x34968_b36911_b36909 = WriteMem(b36909, x34964_x34963).name("x34968_b36911_b36909").ctrl(x34969).srcCtx("CellsPar.scala:192:20") // StreamWrite(x34953,x34964,x34967)
    val x34968_b36912_b36910 = WriteMem(b36910, Const(64)).name("x34968_b36912_b36910").ctrl(x34969).srcCtx("CellsPar.scala:192:20") // StreamWrite(x34953,x34964,x34967)
    val x34970 = FringeDenseLoad(dram=List(x34474), cmdStream=List(b36909, b36910), dataStream=List(x34954)).name("x34970").ctrl(x34981).srcCtx("CellsPar.scala:192:20") // FringeDenseLoad(x34474,x34953,x34954)
    val x34971 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x34971").ctrl(x34981).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x34972 = CounterChain(List(x34971)).name("x34972").ctrl(x34981).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x34971))
    val x34980 = LoopController(style=InnerPipe, level=InnerControl, cchain=x34972).name("x34980").ctrl(x34981).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b22420, b22411, b22412, b22364),x34972,Block(Const(())),List(List(b22441)),List(List(b22442)))
    val b22441 = CounterIter(x34971, None).name("b22441").ctrl(x34980) // b22441
    val b22442 = Const(true).name("b22442").ctrl(x34980) // b22442
    val x34973 = OpDef(op=BitAnd, inputs=List(b22442, b22420)).name("x34973").ctrl(x34980).srcCtx("UnrollingBase.scala:28:66") // And(b22442,b22420)
    val x34974 = OpDef(op=BitAnd, inputs=List(b22411, b22412)).name("x34974").ctrl(x34980).srcCtx("UnrollingBase.scala:28:66") // And(b22411,b22412)
    val x34975 = OpDef(op=BitAnd, inputs=List(x34973, x34974)).name("x34975").ctrl(x34980).srcCtx("UnrollingBase.scala:28:66") // And(x34973,x34974)
    val x34976 = OpDef(op=BitAnd, inputs=List(x34975, b22364)).name("x34976").ctrl(x34980).srcCtx("UnrollingBase.scala:28:66") // And(x34975,b22364)
    val x34977_x34977 = ReadMem(x34954).name("x34977_x34977").ctrl(x34980).srcCtx("CellsPar.scala:192:20") // ParStreamRead(x34954,List(x34976))
    val x34978_x34978 = x34977_x34977 // x34978 = VectorApply(x34977,0)
    val x34979 = StoreBanks(List(x34947_d0_b0), List(b22419, b22441), x34978_x34978).name("x34979").ctrl(x34980).srcCtx("CellsPar.scala:192:20") // ParSRAMStore(x34947,List(List(b22419, b22441)),List(x34978),List(x34976))
    val x34982 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x34982").ctrl(x35051).srcCtx("CellsPar.scala:193:34") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x34983 = CounterChain(List(x34982)).name("x34983").ctrl(x35051).srcCtx("CellsPar.scala:193:46") // CounterChainNew(List(x34982))
    val x35050 = LoopController(style=MetaPipe, level=OuterControl, cchain=x34983).name("x35050").ctrl(x35051).srcCtx("CellsPar.scala:193:46") // UnrolledForeach(List(b22411, b22412, b22364),x34983,Block(Const(())),List(List(b22454)),List(List(b22455)))
    val b22454 = CounterIter(x34982, Some(0)).name("b22454").ctrl(x35050) // b22454
    val b22455 = Const(true).name("b22455").ctrl(x35050) // b22455
    val x34984 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x34984").ctrl(x35050).srcCtx("CellsPar.scala:194:28") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x34985 = CounterChain(List(x34984)).name("x34985").ctrl(x35050).srcCtx("CellsPar.scala:194:42") // CounterChainNew(List(x34984))
    val x35049 = LoopController(style=MetaPipe, level=OuterControl, cchain=x34985).name("x35049").ctrl(x35050).srcCtx("CellsPar.scala:194:42") // UnrolledForeach(List(b22455, b22411, b22412, b22364),x34985,Block(Const(())),List(List(b22458)),List(List(b22459)))
    val b22458 = CounterIter(x34984, Some(0)).name("b22458").ctrl(x35049) // b22458
    val b22459 = Const(true).name("b22459").ctrl(x35049) // b22459
    val x34986_d0 = Reg(init=Some(0.0)).name("x34986_d0").ctrl(x35049).srcCtx("CellsPar.scala:195:34:prod") // x34986 = RegNew(Const(0))
    isAccum(x34986_d0) = false
    bufferDepthOf(x34986_d0) = 2
    val x34986_d1 = Reg(init=Some(0.0)).name("x34986_d1").ctrl(x35049).srcCtx("CellsPar.scala:195:34:prod") // x34986 = RegNew(Const(0))
    isAccum(x34986_d1) = true
    bufferDepthOf(x34986_d1) = 1
    val x34987 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x34987").ctrl(x35049).srcCtx("CellsPar.scala:195:43") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x34988 = CounterChain(List(x34987)).name("x34988").ctrl(x35049).srcCtx("CellsPar.scala:207:15") // CounterChainNew(List(x34987))
    val x35014 = LoopController(style=InnerPipe, level=InnerControl, cchain=x34988).name("x35014").ctrl(x35049).srcCtx("CellsPar.scala:207:15") // UnrolledReduce(List(b22459, b22455, b22411, b22412, b22364),x34988,x34986,Block((x34986) => Const(())),List(List(b22463)),List(List(b22464)))
    val b22463 = CounterIter(x34987, None).name("b22463").ctrl(x35014) // b22463
    val b22464 = Const(true).name("b22464").ctrl(x35014) // b22464
    val x34989 = OpDef(op=FixAdd, inputs=List(b22409, b22463)).name("x34989").ctrl(x35014).srcCtx("CellsPar.scala:196:42:reduce_size_offset") // FixAdd(b22409,b22463)
    val x34990 = OpDef(op=BitAnd, inputs=List(b22464, b22459)).name("x34990").ctrl(x35014).srcCtx("UnrollingBase.scala:28:66") // And(b22464,b22459)
    val x34991 = OpDef(op=BitAnd, inputs=List(b22455, b22411)).name("x34991").ctrl(x35014).srcCtx("UnrollingBase.scala:28:66") // And(b22455,b22411)
    val x34992 = OpDef(op=BitAnd, inputs=List(b22412, b22364)).name("x34992").ctrl(x35014).srcCtx("UnrollingBase.scala:28:66") // And(b22412,b22364)
    val x34993 = OpDef(op=BitAnd, inputs=List(x34990, x34991)).name("x34993").ctrl(x35014).srcCtx("UnrollingBase.scala:28:66") // And(x34990,x34991)
    val x34994 = OpDef(op=BitAnd, inputs=List(x34993, x34992)).name("x34994").ctrl(x35014).srcCtx("UnrollingBase.scala:28:66") // And(x34993,x34992)
    val x34995 = LoadBanks(List(x34947_d0_b0), List(b22463, b22458)).name("x34995").ctrl(x35014).srcCtx("CellsPar.scala:203:34:tk") // ParSRAMLoad(x34947,List(List(b22463, b22458)),List(x34994))
    val x34996 = x34995 // x34996 = VectorApply(x34995,0)
    val x34997 = LoadBanks(List(x34750_d3_b0), List(b22454, x34989)).name("x34997").ctrl(x35014).srcCtx("CellsPar.scala:204:28") // ParSRAMLoad(x34750,List(List(b22454, x34989)),List(x34994))
    val x34998 = x34997 // x34998 = VectorApply(x34997,0)
    val x34999 = OpDef(op=FixMul, inputs=List(x34998, x34996)).name("x34999").ctrl(x35014).srcCtx("CellsPar.scala:204:52:tempA") // FixMul(x34998,x34996)
    val x35000 = OpDef(op=FixSub, inputs=List(x34989, Const(512))).name("x35000").ctrl(x35014).srcCtx("CellsPar.scala:205:51") // FixSub(x34989,Const(512))
    val x35001 = LoadBanks(List(x34751_d7_b0), List(b22454, x35000)).name("x35001").ctrl(x35014).srcCtx("CellsPar.scala:205:28") // ParSRAMLoad(x34751,List(List(b22454, x35000)),List(x34994))
    val x35002 = x35001 // x35002 = VectorApply(x35001,0)
    val x35003 = OpDef(op=FixMul, inputs=List(x35002, x34996)).name("x35003").ctrl(x35014).srcCtx("CellsPar.scala:205:67:tempB") // FixMul(x35002,x34996)
    val x35004 = OpDef(op=FixLt, inputs=List(x34989, Const(512))).name("x35004").ctrl(x35014).srcCtx("CellsPar.scala:206:38") // FixLt(x34989,Const(512))
    val x35005 = OpDef(op=MuxOp, inputs=List(x35004, x34999, x35003)).name("x35005").ctrl(x35014).srcCtx("CellsPar.scala:206:18") // Mux(x35004,x34999,x35003)
    val x35006 = ReadMem(x34986_d1).name("x35006").ctrl(x35014).srcCtx("CellsPar.scala:207:15") // RegRead(x34986)
    val x35007 = OpDef(op=FixEql, inputs=List(b22463, Const(0))).name("x35007").ctrl(x35014).srcCtx("CellsPar.scala:207:15") // FixEql(b22463,Const(0))
    val x35008 = ReduceAccumOp(op=FixAdd, input=x35005, accum=x35006).name("x35008").ctrl(x35014).srcCtx("CellsPar.scala:207:17") // FixAdd(x35005,x35006)
    val x35009 = OpDef(op=BitAnd, inputs=List(b22459, b22455)).name("x35009").ctrl(x35014).srcCtx("UnrollingBase.scala:28:66") // And(b22459,b22455)
    val x35010 = OpDef(op=BitAnd, inputs=List(b22411, b22412)).name("x35010").ctrl(x35014).srcCtx("UnrollingBase.scala:28:66") // And(b22411,b22412)
    val x35011 = OpDef(op=BitAnd, inputs=List(x35009, x35010)).name("x35011").ctrl(x35014).srcCtx("UnrollingBase.scala:28:66") // And(x35009,x35010)
    val x35012 = OpDef(op=BitAnd, inputs=List(x35011, b22364)).name("x35012").ctrl(x35014).srcCtx("UnrollingBase.scala:28:66") // And(x35011,b22364)
    val x35013_x34986_d0 = WriteMem(x34986_d0, x35008).name("x35013_x34986_d0").ctrl(x35014).srcCtx("CellsPar.scala:207:15") // RegWrite(x34986,x35008,x35012)
    val x35013_x34986_d1 = WriteMem(x34986_d1, x35008).name("x35013_x34986_d1").ctrl(x35014).srcCtx("CellsPar.scala:207:15") // RegWrite(x34986,x35008,x35012)
    val x35048 = UnitController(style=SeqPipe, level=InnerControl).name("x35048").ctrl(x35049).srcCtx("CellsPar.scala:194:42") // UnitPipe(List(b22459, b22455, b22411, b22412, b22364),Block(Const(())))
    val x35015 = OpDef(op=FixAdd, inputs=List(b22410, b22458)).name("x35015").ctrl(x35048).srcCtx("CellsPar.scala:209:31:colOffset") // FixAdd(b22410,b22458)
    val x35016 = ReadMem(x34986_d0).name("x35016").ctrl(x35048).srcCtx("CellsPar.scala:210:28") // RegRead(x34986)
    val x35017 = OpDef(op=FixEql, inputs=List(b22409, Const(0))).name("x35017").ctrl(x35048).srcCtx("CellsPar.scala:210:42") // FixEql(b22409,Const(0))
    val x35018 = OpDef(op=BitAnd, inputs=List(b22459, b22455)).name("x35018").ctrl(x35048).srcCtx("UnrollingBase.scala:28:66") // And(b22459,b22455)
    val x35019 = OpDef(op=BitAnd, inputs=List(b22411, b22412)).name("x35019").ctrl(x35048).srcCtx("UnrollingBase.scala:28:66") // And(b22411,b22412)
    val x35020 = OpDef(op=BitAnd, inputs=List(x35018, x35019)).name("x35020").ctrl(x35048).srcCtx("UnrollingBase.scala:28:66") // And(x35018,x35019)
    val x35021 = OpDef(op=BitAnd, inputs=List(x35020, b22364)).name("x35021").ctrl(x35048).srcCtx("UnrollingBase.scala:28:66") // And(x35020,b22364)
    val x35022 = LoadBanks(List(x34757_d3_b0), List(Const(0), x35015)).name("x35022").ctrl(x35048).srcCtx("CellsPar.scala:210:52") // SRAMLoad(x34757,ArrayBuffer(Const(1), Const(2048)),List(Const(0), x35015),Const(0),x35021)
    val x35023 = LoadBanks(List(x34753_d1_b0), List(b22454, x35015)).name("x35023").ctrl(x35048).srcCtx("CellsPar.scala:210:91") // SRAMLoad(x34753,ArrayBuffer(Const(1), Const(512)),List(b22454, x35015),Const(0),x35021)
    val x35024 = OpDef(op=MuxOp, inputs=List(x35017, x35022, x35023)).name("x35024").ctrl(x35048).srcCtx("CellsPar.scala:210:39") // Mux(x35017,x35022,x35023)
    val x35025 = OpDef(op=FixAdd, inputs=List(x35016, x35024)).name("x35025").ctrl(x35048).srcCtx("CellsPar.scala:210:34:ele") // FixAdd(x35016,x35024)
    val x35026 = OpDef(op=FixLeq, inputs=List(Const(1520), b22409)).name("x35026").ctrl(x35048).srcCtx("CellsPar.scala:218:46") // FixLeq(Const(1520),b22409)
    // x35027 = FixConvert(Const(0),TRUE,_8,_24) x.tp=FixPt[TRUE,_32,_0] {
    val x35027_int1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("01111111111111111111111111111111"))).name("x35027_int1").ctrl(x35048).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x35027_int2 = OpDef(op=FixSla, inputs=List(x35027_int1, Const(24))).name("x35027_int2").ctrl(x35048).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x35027_frac1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("00000000000000000000000000000000"))).name("x35027_frac1").ctrl(x35048).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x35027_frac2 = OpDef(op=FixSla, inputs=List(x35027_frac1, Const(24))).name("x35027_frac2").ctrl(x35048).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x35027 = OpDef(op=BitOr, inputs=List(x35027_int2, x35027_frac2)).name("x35027").ctrl(x35048).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    // }
    val x35028 = OpDef(op=FixAdd, inputs=List(x35025, x35027)).name("x35028").ctrl(x35048).srcCtx("CellsPar.scala:218:87") // FixAdd(x35025,x35027)
    val x35029 = OpDef(op=FixSra, inputs=List(x35028, Const(1))).name("x35029").ctrl(x35048).srcCtx("CellsPar.scala:29:22") // FixRsh(x35028,Const(1))
    val x35030 = x35029 // FixConvert(x35029,TRUE,_8,_24) (Same Type. No op)
    val x35031 = OpDef(op=FixAbs, inputs=List(x35030)).name("x35031").ctrl(x35048).srcCtx("CellsPar.scala:37:19:absp") // FixAbs(x35030)
    val x35032 = OpDef(op=FixLt, inputs=List(Const(2.5), x35031)).name("x35032").ctrl(x35048).srcCtx("CellsPar.scala:51:23:cond1") // FixLt(Const(2.5),x35031)
    val x35033 = OpDef(op=FixLt, inputs=List(Const(0.5), x35031)).name("x35033").ctrl(x35048).srcCtx("CellsPar.scala:54:35") // FixLt(Const(0.5),x35031)
    val x35034 = OpDef(op=FixLeq, inputs=List(x35031, Const(2.5))).name("x35034").ctrl(x35048).srcCtx("CellsPar.scala:54:52") // FixLeq(x35031,Const(2.5))
    val x35035 = OpDef(op=BitAnd, inputs=List(x35033, x35034)).name("x35035").ctrl(x35048).srcCtx("CellsPar.scala:54:43:cond2") // And(x35033,x35034)
    val x35036 = OpDef(op=FixSra, inputs=List(x35031, Const(2))).name("x35036").ctrl(x35048).srcCtx("CellsPar.scala:56:21:div4") // FixRsh(x35031,Const(2))
    val x35037 = OpDef(op=FixAdd, inputs=List(x35036, Const(0.375))).name("x35037").ctrl(x35048).srcCtx("CellsPar.scala:57:27:div4Offset") // FixAdd(x35036,Const(0.375))
    val x35038 = OpDef(op=MuxOp, inputs=List(x35035, x35037, x35031)).name("x35038").ctrl(x35048).srcCtx("CellsPar.scala:58:20:body2") // Mux(x35035,x35037,x35031)
    val x35039 = OpDef(op=MuxOp, inputs=List(x35032, Const(1.0), x35038)).name("x35039").ctrl(x35048).srcCtx("CellsPar.scala:60:20:absre") // Mux(x35032,Const(1),x35038)
    val x35040 = OpDef(op=FixNeg, inputs=List(x35039)).name("x35040").ctrl(x35048).srcCtx("CellsPar.scala:62:29:mabre") // FixNeg(x35039)
    val x35041 = OpDef(op=FixLt, inputs=List(x35030, Const(0.0))).name("x35041").ctrl(x35048).srcCtx("CellsPar.scala:63:22") // FixLt(x35030,Const(0))
    val x35042 = OpDef(op=MuxOp, inputs=List(x35041, x35040, x35039)).name("x35042").ctrl(x35048).srcCtx("CellsPar.scala:63:17:re") // Mux(x35041,x35040,x35039)
    val x35043 = x35042 // FixConvert(x35042,TRUE,_8,_24) (Same Type. No op)
    val x35044 = OpDef(op=FixAdd, inputs=List(x35043, Const(1.0))).name("x35044").ctrl(x35048).srcCtx("CellsPar.scala:29:33") // FixAdd(x35043,Const(1))
    val x35045 = OpDef(op=FixSra, inputs=List(x35044, Const(1))).name("x35045").ctrl(x35048).srcCtx("CellsPar.scala:29:44") // FixRsh(x35044,Const(1))
    val x35046 = OpDef(op=MuxOp, inputs=List(x35026, x35045, x35025)).name("x35046").ctrl(x35048).srcCtx("CellsPar.scala:218:43") // Mux(x35026,x35045,x35025)
    val x35047 = StoreBanks(List(x34753_d0_b0, x34753_d1_b0), List(b22454, x35015), x35046).name("x35047").ctrl(x35048).srcCtx("CellsPar.scala:218:38") // SRAMStore(x34753,ArrayBuffer(Const(1), Const(512)),List(b22454, x35015),Const(0),x35046,x35021)
    val x35052 = Counter(min=Const(0), max=Const(512), step=Const(16), par=1).name("x35052").ctrl(x36822).srcCtx("CellsPar.scala:190:58") // CounterNew(Const(0),Const(512),Const(16),Const(1))
    val x35053 = Counter(min=Const(0), max=Const(1536), step=Const(16), par=1).name("x35053").ctrl(x36822).srcCtx("CellsPar.scala:190:33") // CounterNew(Const(0),Const(1536),Const(16),Const(1))
    val x35054 = CounterChain(List(x35053,x35052)).name("x35054").ctrl(x36822).srcCtx("CellsPar.scala:190:72") // CounterChainNew(List(x35053, x35052))
    val x35157 = LoopController(style=MetaPipe, level=OuterControl, cchain=x35054).name("x35157").ctrl(x36822).srcCtx("CellsPar.scala:190:72") // UnrolledForeach(List(b22364),x35054,Block(Const(())),List(List(b22531), List(b22532)),List(List(b22533), List(b22534)))
    val b22531 = CounterIter(x35053, Some(0)).name("b22531").ctrl(x35157) // b22531
    val b22533 = Const(true).name("b22533").ctrl(x35157) // b22533
    val b22532 = CounterIter(x35052, Some(0)).name("b22532").ctrl(x35157) // b22532
    val b22534 = Const(true).name("b22534").ctrl(x35157) // b22534
    val x35055_d0_b0 = SRAM(size=256, banking=Strided(banks=1, stride=16)).name("x35055_d0_b0").ctrl(x35157).srcCtx("CellsPar.scala:191:33:tileKernel") // x35055 = SRAMNew(ArrayBuffer(Const(16), Const(16)))
    isAccum(x35055_d0_b0) = false
    bufferDepthOf(x35055_d0_b0) = 2
    val x35058 = UnitController(style=SeqPipe, level=InnerControl).name("x35058").ctrl(x35157).srcCtx("CellsPar.scala:190:72") // UnitPipe(List(b22533, b22534, b22364),Block(Const(())))
    val x35056 = OpDef(op=FixAdd, inputs=List(b22531, Const(16))).name("x35056").ctrl(x35058).srcCtx("CellsPar.scala:192:36") // FixAdd(b22531,Const(16))
    val x35057 = OpDef(op=FixAdd, inputs=List(b22532, Const(16))).name("x35057").ctrl(x35058).srcCtx("CellsPar.scala:192:45") // FixAdd(b22532,Const(16))
    val x35059 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x35059").ctrl(x35157).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x35060 = CounterChain(List(x35059)).name("x35060").ctrl(x35157).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x35059))
    val x35089 = LoopController(style=StreamPipe, level=OuterControl, cchain=x35060).name("x35089").ctrl(x35157).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b22533, b22534, b22364),x35060,Block(Const(())),List(List(b22541)),List(List(b22542)))
    val b22541 = CounterIter(x35059, Some(0)).name("b22541").ctrl(x35089) // b22541
    val b22542 = Const(true).name("b22542").ctrl(x35089) // b22542
    val b36913 = StreamOut(field="offset").name("b36913").ctrl(x35089).srcCtx("CellsPar.scala:192:20") // x35061 = StreamOutNew(BurstCmdBus)
    isAccum(b36913) = false
    bufferDepthOf(b36913) = 1
    val b36914 = StreamOut(field="size").name("b36914").ctrl(x35089).srcCtx("CellsPar.scala:192:20") // x35061 = StreamOutNew(BurstCmdBus)
    isAccum(b36914) = false
    bufferDepthOf(b36914) = 1
    val x35062 = StreamIn(field="data").name("x35062").ctrl(x35089).srcCtx("CellsPar.scala:192:20") // x35062 = StreamInNew(BurstDataBus())
    isAccum(x35062) = false
    bufferDepthOf(x35062) = 1
    val x35077 = UnitController(style=SeqPipe, level=InnerControl).name("x35077").ctrl(x35089).srcCtx("CellsPar.scala:192:20") // UnitPipe(List(b22542, b22533, b22534, b22364),Block(x35076))
    val x35063 = OpDef(op=FixAdd, inputs=List(b22531, b22541)).name("x35063").ctrl(x35077).srcCtx("CellsPar.scala:192:20") // FixAdd(b22531,b22541)
    val x35064 = x35063 // FixConvert(x35063,TRUE,_32,_0) (Same Type. No op)
    val x35065 = OpDef(op=FixSla, inputs=List(x35064, Const(9))).name("x35065").ctrl(x35077).srcCtx("CellsPar.scala:192:20") // FixLsh(x35064,Const(9))
    val x35066 = b22532 // FixConvert(b22532,TRUE,_32,_0) (Same Type. No op)
    val x35067 = OpDef(op=FixAdd, inputs=List(x35065, x35066)).name("x35067").ctrl(x35077).srcCtx("CellsPar.scala:192:20") // FixAdd(x35065,x35066)
    val x35068 = OpDef(op=FixSla, inputs=List(x35067, Const(2))).name("x35068").ctrl(x35077).srcCtx("CellsPar.scala:192:20") // FixLsh(x35067,Const(2))
    val x35069 = x35068 // FixConvert(x35068,TRUE,_64,_0)
    val x35070 = DramAddress(x34475).name("x35070").ctrl(x35077).srcCtx("CellsPar.scala:192:20") // GetDRAMAddress(x34475)
    val x35072_x35071 = OpDef(op=FixAdd, inputs=List(x35069, x35070)).name("x35072_x35071").ctrl(x35077).srcCtx("CellsPar.scala:192:20") // FixAdd(x35069,x35070)
    // x35072 = SimpleStruct(ArrayBuffer((offset,x35071), (size,Const(64)), (isLoad,Const(true))))
    val x35073 = OpDef(op=BitAnd, inputs=List(b22542, b22533)).name("x35073").ctrl(x35077).srcCtx("UnrollingBase.scala:28:66") // And(b22542,b22533)
    val x35074 = OpDef(op=BitAnd, inputs=List(b22534, b22364)).name("x35074").ctrl(x35077).srcCtx("UnrollingBase.scala:28:66") // And(b22534,b22364)
    val x35075 = OpDef(op=BitAnd, inputs=List(x35073, x35074)).name("x35075").ctrl(x35077).srcCtx("UnrollingBase.scala:28:66") // And(x35073,x35074)
    val x35076_b36915_b36913 = WriteMem(b36913, x35072_x35071).name("x35076_b36915_b36913").ctrl(x35077).srcCtx("CellsPar.scala:192:20") // StreamWrite(x35061,x35072,x35075)
    val x35076_b36916_b36914 = WriteMem(b36914, Const(64)).name("x35076_b36916_b36914").ctrl(x35077).srcCtx("CellsPar.scala:192:20") // StreamWrite(x35061,x35072,x35075)
    val x35078 = FringeDenseLoad(dram=List(x34475), cmdStream=List(b36913, b36914), dataStream=List(x35062)).name("x35078").ctrl(x35089).srcCtx("CellsPar.scala:192:20") // FringeDenseLoad(x34475,x35061,x35062)
    val x35079 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x35079").ctrl(x35089).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x35080 = CounterChain(List(x35079)).name("x35080").ctrl(x35089).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x35079))
    val x35088 = LoopController(style=InnerPipe, level=InnerControl, cchain=x35080).name("x35088").ctrl(x35089).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b22542, b22533, b22534, b22364),x35080,Block(Const(())),List(List(b22563)),List(List(b22564)))
    val b22563 = CounterIter(x35079, None).name("b22563").ctrl(x35088) // b22563
    val b22564 = Const(true).name("b22564").ctrl(x35088) // b22564
    val x35081 = OpDef(op=BitAnd, inputs=List(b22564, b22542)).name("x35081").ctrl(x35088).srcCtx("UnrollingBase.scala:28:66") // And(b22564,b22542)
    val x35082 = OpDef(op=BitAnd, inputs=List(b22533, b22534)).name("x35082").ctrl(x35088).srcCtx("UnrollingBase.scala:28:66") // And(b22533,b22534)
    val x35083 = OpDef(op=BitAnd, inputs=List(x35081, x35082)).name("x35083").ctrl(x35088).srcCtx("UnrollingBase.scala:28:66") // And(x35081,x35082)
    val x35084 = OpDef(op=BitAnd, inputs=List(x35083, b22364)).name("x35084").ctrl(x35088).srcCtx("UnrollingBase.scala:28:66") // And(x35083,b22364)
    val x35085_x35085 = ReadMem(x35062).name("x35085_x35085").ctrl(x35088).srcCtx("CellsPar.scala:192:20") // ParStreamRead(x35062,List(x35084))
    val x35086_x35086 = x35085_x35085 // x35086 = VectorApply(x35085,0)
    val x35087 = StoreBanks(List(x35055_d0_b0), List(b22541, b22563), x35086_x35086).name("x35087").ctrl(x35088).srcCtx("CellsPar.scala:192:20") // ParSRAMStore(x35055,List(List(b22541, b22563)),List(x35086),List(x35084))
    val x35090 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x35090").ctrl(x35157).srcCtx("CellsPar.scala:193:34") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x35091 = CounterChain(List(x35090)).name("x35091").ctrl(x35157).srcCtx("CellsPar.scala:193:46") // CounterChainNew(List(x35090))
    val x35156 = LoopController(style=MetaPipe, level=OuterControl, cchain=x35091).name("x35156").ctrl(x35157).srcCtx("CellsPar.scala:193:46") // UnrolledForeach(List(b22533, b22534, b22364),x35091,Block(Const(())),List(List(b22576)),List(List(b22577)))
    val b22576 = CounterIter(x35090, Some(0)).name("b22576").ctrl(x35156) // b22576
    val b22577 = Const(true).name("b22577").ctrl(x35156) // b22577
    val x35092 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x35092").ctrl(x35156).srcCtx("CellsPar.scala:194:28") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x35093 = CounterChain(List(x35092)).name("x35093").ctrl(x35156).srcCtx("CellsPar.scala:194:42") // CounterChainNew(List(x35092))
    val x35155 = LoopController(style=MetaPipe, level=OuterControl, cchain=x35093).name("x35155").ctrl(x35156).srcCtx("CellsPar.scala:194:42") // UnrolledForeach(List(b22577, b22533, b22534, b22364),x35093,Block(Const(())),List(List(b22580)),List(List(b22581)))
    val b22580 = CounterIter(x35092, Some(0)).name("b22580").ctrl(x35155) // b22580
    val b22581 = Const(true).name("b22581").ctrl(x35155) // b22581
    val x35094_d0 = Reg(init=Some(0.0)).name("x35094_d0").ctrl(x35155).srcCtx("CellsPar.scala:195:34:prod") // x35094 = RegNew(Const(0))
    isAccum(x35094_d0) = false
    bufferDepthOf(x35094_d0) = 2
    val x35094_d1 = Reg(init=Some(0.0)).name("x35094_d1").ctrl(x35155).srcCtx("CellsPar.scala:195:34:prod") // x35094 = RegNew(Const(0))
    isAccum(x35094_d1) = true
    bufferDepthOf(x35094_d1) = 1
    val x35095 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x35095").ctrl(x35155).srcCtx("CellsPar.scala:195:43") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x35096 = CounterChain(List(x35095)).name("x35096").ctrl(x35155).srcCtx("CellsPar.scala:207:15") // CounterChainNew(List(x35095))
    val x35122 = LoopController(style=InnerPipe, level=InnerControl, cchain=x35096).name("x35122").ctrl(x35155).srcCtx("CellsPar.scala:207:15") // UnrolledReduce(List(b22581, b22577, b22533, b22534, b22364),x35096,x35094,Block((x35094) => Const(())),List(List(b22585)),List(List(b22586)))
    val b22585 = CounterIter(x35095, None).name("b22585").ctrl(x35122) // b22585
    val b22586 = Const(true).name("b22586").ctrl(x35122) // b22586
    val x35097 = OpDef(op=FixAdd, inputs=List(b22531, b22585)).name("x35097").ctrl(x35122).srcCtx("CellsPar.scala:196:42:reduce_size_offset") // FixAdd(b22531,b22585)
    val x35098 = OpDef(op=BitAnd, inputs=List(b22586, b22581)).name("x35098").ctrl(x35122).srcCtx("UnrollingBase.scala:28:66") // And(b22586,b22581)
    val x35099 = OpDef(op=BitAnd, inputs=List(b22577, b22533)).name("x35099").ctrl(x35122).srcCtx("UnrollingBase.scala:28:66") // And(b22577,b22533)
    val x35100 = OpDef(op=BitAnd, inputs=List(b22534, b22364)).name("x35100").ctrl(x35122).srcCtx("UnrollingBase.scala:28:66") // And(b22534,b22364)
    val x35101 = OpDef(op=BitAnd, inputs=List(x35098, x35099)).name("x35101").ctrl(x35122).srcCtx("UnrollingBase.scala:28:66") // And(x35098,x35099)
    val x35102 = OpDef(op=BitAnd, inputs=List(x35101, x35100)).name("x35102").ctrl(x35122).srcCtx("UnrollingBase.scala:28:66") // And(x35101,x35100)
    val x35103 = LoadBanks(List(x35055_d0_b0), List(b22585, b22580)).name("x35103").ctrl(x35122).srcCtx("CellsPar.scala:203:34:tk") // ParSRAMLoad(x35055,List(List(b22585, b22580)),List(x35102))
    val x35104 = x35103 // x35104 = VectorApply(x35103,0)
    val x35105 = LoadBanks(List(x34750_d2_b0), List(b22576, x35097)).name("x35105").ctrl(x35122).srcCtx("CellsPar.scala:204:28") // ParSRAMLoad(x34750,List(List(b22576, x35097)),List(x35102))
    val x35106 = x35105 // x35106 = VectorApply(x35105,0)
    val x35107 = OpDef(op=FixMul, inputs=List(x35106, x35104)).name("x35107").ctrl(x35122).srcCtx("CellsPar.scala:204:52:tempA") // FixMul(x35106,x35104)
    val x35108 = OpDef(op=FixSub, inputs=List(x35097, Const(512))).name("x35108").ctrl(x35122).srcCtx("CellsPar.scala:205:51") // FixSub(x35097,Const(512))
    val x35109 = LoadBanks(List(x34751_d6_b0), List(b22576, x35108)).name("x35109").ctrl(x35122).srcCtx("CellsPar.scala:205:28") // ParSRAMLoad(x34751,List(List(b22576, x35108)),List(x35102))
    val x35110 = x35109 // x35110 = VectorApply(x35109,0)
    val x35111 = OpDef(op=FixMul, inputs=List(x35110, x35104)).name("x35111").ctrl(x35122).srcCtx("CellsPar.scala:205:67:tempB") // FixMul(x35110,x35104)
    val x35112 = OpDef(op=FixLt, inputs=List(x35097, Const(512))).name("x35112").ctrl(x35122).srcCtx("CellsPar.scala:206:38") // FixLt(x35097,Const(512))
    val x35113 = OpDef(op=MuxOp, inputs=List(x35112, x35107, x35111)).name("x35113").ctrl(x35122).srcCtx("CellsPar.scala:206:18") // Mux(x35112,x35107,x35111)
    val x35114 = ReadMem(x35094_d1).name("x35114").ctrl(x35122).srcCtx("CellsPar.scala:207:15") // RegRead(x35094)
    val x35115 = OpDef(op=FixEql, inputs=List(b22585, Const(0))).name("x35115").ctrl(x35122).srcCtx("CellsPar.scala:207:15") // FixEql(b22585,Const(0))
    val x35116 = ReduceAccumOp(op=FixAdd, input=x35113, accum=x35114).name("x35116").ctrl(x35122).srcCtx("CellsPar.scala:207:17") // FixAdd(x35113,x35114)
    val x35117 = OpDef(op=BitAnd, inputs=List(b22581, b22577)).name("x35117").ctrl(x35122).srcCtx("UnrollingBase.scala:28:66") // And(b22581,b22577)
    val x35118 = OpDef(op=BitAnd, inputs=List(b22533, b22534)).name("x35118").ctrl(x35122).srcCtx("UnrollingBase.scala:28:66") // And(b22533,b22534)
    val x35119 = OpDef(op=BitAnd, inputs=List(x35117, x35118)).name("x35119").ctrl(x35122).srcCtx("UnrollingBase.scala:28:66") // And(x35117,x35118)
    val x35120 = OpDef(op=BitAnd, inputs=List(x35119, b22364)).name("x35120").ctrl(x35122).srcCtx("UnrollingBase.scala:28:66") // And(x35119,b22364)
    val x35121_x35094_d0 = WriteMem(x35094_d0, x35116).name("x35121_x35094_d0").ctrl(x35122).srcCtx("CellsPar.scala:207:15") // RegWrite(x35094,x35116,x35120)
    val x35121_x35094_d1 = WriteMem(x35094_d1, x35116).name("x35121_x35094_d1").ctrl(x35122).srcCtx("CellsPar.scala:207:15") // RegWrite(x35094,x35116,x35120)
    val x35154 = UnitController(style=SeqPipe, level=InnerControl).name("x35154").ctrl(x35155).srcCtx("CellsPar.scala:194:42") // UnitPipe(List(b22581, b22577, b22533, b22534, b22364),Block(Const(())))
    val x35123 = OpDef(op=FixAdd, inputs=List(b22532, b22580)).name("x35123").ctrl(x35154).srcCtx("CellsPar.scala:209:31:colOffset") // FixAdd(b22532,b22580)
    val x35124 = ReadMem(x35094_d0).name("x35124").ctrl(x35154).srcCtx("CellsPar.scala:210:28") // RegRead(x35094)
    val x35125 = OpDef(op=FixEql, inputs=List(b22531, Const(0))).name("x35125").ctrl(x35154).srcCtx("CellsPar.scala:210:42") // FixEql(b22531,Const(0))
    val x35126 = OpDef(op=FixAdd, inputs=List(x35123, Const(512))).name("x35126").ctrl(x35154).srcCtx("CellsPar.scala:210:66") // FixAdd(x35123,Const(512))
    val x35127 = OpDef(op=BitAnd, inputs=List(b22581, b22577)).name("x35127").ctrl(x35154).srcCtx("UnrollingBase.scala:28:66") // And(b22581,b22577)
    val x35128 = OpDef(op=BitAnd, inputs=List(b22533, b22534)).name("x35128").ctrl(x35154).srcCtx("UnrollingBase.scala:28:66") // And(b22533,b22534)
    val x35129 = OpDef(op=BitAnd, inputs=List(x35127, x35128)).name("x35129").ctrl(x35154).srcCtx("UnrollingBase.scala:28:66") // And(x35127,x35128)
    val x35130 = OpDef(op=BitAnd, inputs=List(x35129, b22364)).name("x35130").ctrl(x35154).srcCtx("UnrollingBase.scala:28:66") // And(x35129,b22364)
    def split1 = {
    val x35131 = LoadBanks(List(x34757_d2_b0), List(Const(0), x35126)).name("x35131").ctrl(x35154).srcCtx("CellsPar.scala:210:52") // SRAMLoad(x34757,ArrayBuffer(Const(1), Const(2048)),List(Const(0), x35126),Const(0),x35130)
    val x35132 = LoadBanks(List(x34754_d1_b0), List(b22576, x35123)).name("x35132").ctrl(x35154).srcCtx("CellsPar.scala:210:91") // SRAMLoad(x34754,ArrayBuffer(Const(1), Const(512)),List(b22576, x35123),Const(0),x35130)
    val x35133 = OpDef(op=MuxOp, inputs=List(x35125, x35131, x35132)).name("x35133").ctrl(x35154).srcCtx("CellsPar.scala:210:39") // Mux(x35125,x35131,x35132)
    val x35134 = OpDef(op=FixAdd, inputs=List(x35124, x35133)).name("x35134").ctrl(x35154).srcCtx("CellsPar.scala:210:34:ele") // FixAdd(x35124,x35133)
    val x35135 = OpDef(op=FixLeq, inputs=List(Const(1520), b22531)).name("x35135").ctrl(x35154).srcCtx("CellsPar.scala:218:46") // FixLeq(Const(1520),b22531)
    // x35136 = FixConvert(Const(0),TRUE,_8,_24) x.tp=FixPt[TRUE,_32,_0] {
    val x35136_int1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("01111111111111111111111111111111"))).name("x35136_int1").ctrl(x35154).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x35136_int2 = OpDef(op=FixSla, inputs=List(x35136_int1, Const(24))).name("x35136_int2").ctrl(x35154).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x35136_frac1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("00000000000000000000000000000000"))).name("x35136_frac1").ctrl(x35154).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x35136_frac2 = OpDef(op=FixSla, inputs=List(x35136_frac1, Const(24))).name("x35136_frac2").ctrl(x35154).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x35136 = OpDef(op=BitOr, inputs=List(x35136_int2, x35136_frac2)).name("x35136").ctrl(x35154).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    // }
    val x35137 = OpDef(op=FixAdd, inputs=List(x35134, x35136)).name("x35137").ctrl(x35154).srcCtx("CellsPar.scala:218:87") // FixAdd(x35134,x35136)
    val x35138 = x35137 // FixConvert(x35137,TRUE,_8,_24) (Same Type. No op)
    val x35139 = OpDef(op=FixAbs, inputs=List(x35138)).name("x35139").ctrl(x35154).srcCtx("CellsPar.scala:37:19:absp") // FixAbs(x35138)
    val x35140 = OpDef(op=FixLt, inputs=List(Const(2.5), x35139)).name("x35140").ctrl(x35154).srcCtx("CellsPar.scala:51:23:cond1") // FixLt(Const(2.5),x35139)
    val x35141 = OpDef(op=FixLt, inputs=List(Const(0.5), x35139)).name("x35141").ctrl(x35154).srcCtx("CellsPar.scala:54:35") // FixLt(Const(0.5),x35139)
    val x35142 = OpDef(op=FixLeq, inputs=List(x35139, Const(2.5))).name("x35142").ctrl(x35154).srcCtx("CellsPar.scala:54:52") // FixLeq(x35139,Const(2.5))
    val x35143 = OpDef(op=BitAnd, inputs=List(x35141, x35142)).name("x35143").ctrl(x35154).srcCtx("CellsPar.scala:54:43:cond2") // And(x35141,x35142)
    val x35144 = OpDef(op=FixSra, inputs=List(x35139, Const(2))).name("x35144").ctrl(x35154).srcCtx("CellsPar.scala:56:21:div4") // FixRsh(x35139,Const(2))
    val x35145 = OpDef(op=FixAdd, inputs=List(x35144, Const(0.375))).name("x35145").ctrl(x35154).srcCtx("CellsPar.scala:57:27:div4Offset") // FixAdd(x35144,Const(0.375))
    val x35146 = OpDef(op=MuxOp, inputs=List(x35143, x35145, x35139)).name("x35146").ctrl(x35154).srcCtx("CellsPar.scala:58:20:body2") // Mux(x35143,x35145,x35139)
    val x35147 = OpDef(op=MuxOp, inputs=List(x35140, Const(1.0), x35146)).name("x35147").ctrl(x35154).srcCtx("CellsPar.scala:60:20:absre") // Mux(x35140,Const(1),x35146)
    val x35148 = OpDef(op=FixNeg, inputs=List(x35147)).name("x35148").ctrl(x35154).srcCtx("CellsPar.scala:62:29:mabre") // FixNeg(x35147)
    val x35149 = OpDef(op=FixLt, inputs=List(x35138, Const(0.0))).name("x35149").ctrl(x35154).srcCtx("CellsPar.scala:63:22") // FixLt(x35138,Const(0))
    val x35150 = OpDef(op=MuxOp, inputs=List(x35149, x35148, x35147)).name("x35150").ctrl(x35154).srcCtx("CellsPar.scala:63:17:re") // Mux(x35149,x35148,x35147)
    val x35151 = x35150 // FixConvert(x35150,TRUE,_8,_24) (Same Type. No op)
    val x35152 = OpDef(op=MuxOp, inputs=List(x35135, x35151, x35134)).name("x35152").ctrl(x35154).srcCtx("CellsPar.scala:218:43") // Mux(x35135,x35151,x35134)
    val x35153 = StoreBanks(List(x34754_d0_b0, x34754_d1_b0), List(b22576, x35123), x35152).name("x35153").ctrl(x35154).srcCtx("CellsPar.scala:218:38") // SRAMStore(x34754,ArrayBuffer(Const(1), Const(512)),List(b22576, x35123),Const(0),x35152,x35130)
    val x35158 = Counter(min=Const(0), max=Const(512), step=Const(16), par=1).name("x35158").ctrl(x36822).srcCtx("CellsPar.scala:190:58") // CounterNew(Const(0),Const(512),Const(16),Const(1))
    val x35159 = Counter(min=Const(0), max=Const(1536), step=Const(16), par=1).name("x35159").ctrl(x36822).srcCtx("CellsPar.scala:190:33") // CounterNew(Const(0),Const(1536),Const(16),Const(1))
    val x35160 = CounterChain(List(x35159,x35158)).name("x35160").ctrl(x36822).srcCtx("CellsPar.scala:190:72") // CounterChainNew(List(x35159, x35158))
    val x35266 = LoopController(style=MetaPipe, level=OuterControl, cchain=x35160).name("x35266").ctrl(x36822).srcCtx("CellsPar.scala:190:72") // UnrolledForeach(List(b22364),x35160,Block(Const(())),List(List(b22651), List(b22652)),List(List(b22653), List(b22654)))
    val b22651 = CounterIter(x35159, Some(0)).name("b22651").ctrl(x35266) // b22651
    val b22653 = Const(true).name("b22653").ctrl(x35266) // b22653
    val b22652 = CounterIter(x35158, Some(0)).name("b22652").ctrl(x35266) // b22652
    val b22654 = Const(true).name("b22654").ctrl(x35266) // b22654
    val x35161_d0_b0 = SRAM(size=256, banking=Strided(banks=1, stride=16)).name("x35161_d0_b0").ctrl(x35266).srcCtx("CellsPar.scala:191:33:tileKernel") // x35161 = SRAMNew(ArrayBuffer(Const(16), Const(16)))
    isAccum(x35161_d0_b0) = false
    bufferDepthOf(x35161_d0_b0) = 2
    val x35164 = UnitController(style=SeqPipe, level=InnerControl).name("x35164").ctrl(x35266).srcCtx("CellsPar.scala:190:72") // UnitPipe(List(b22653, b22654, b22364),Block(Const(())))
    val x35162 = OpDef(op=FixAdd, inputs=List(b22651, Const(16))).name("x35162").ctrl(x35164).srcCtx("CellsPar.scala:192:36") // FixAdd(b22651,Const(16))
    val x35163 = OpDef(op=FixAdd, inputs=List(b22652, Const(16))).name("x35163").ctrl(x35164).srcCtx("CellsPar.scala:192:45") // FixAdd(b22652,Const(16))
    val x35165 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x35165").ctrl(x35266).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x35166 = CounterChain(List(x35165)).name("x35166").ctrl(x35266).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x35165))
    val x35195 = LoopController(style=StreamPipe, level=OuterControl, cchain=x35166).name("x35195").ctrl(x35266).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b22653, b22654, b22364),x35166,Block(Const(())),List(List(b22661)),List(List(b22662)))
    val b22661 = CounterIter(x35165, Some(0)).name("b22661").ctrl(x35195) // b22661
    val b22662 = Const(true).name("b22662").ctrl(x35195) // b22662
    val b36917 = StreamOut(field="offset").name("b36917").ctrl(x35195).srcCtx("CellsPar.scala:192:20") // x35167 = StreamOutNew(BurstCmdBus)
    isAccum(b36917) = false
    bufferDepthOf(b36917) = 1
    val b36918 = StreamOut(field="size").name("b36918").ctrl(x35195).srcCtx("CellsPar.scala:192:20") // x35167 = StreamOutNew(BurstCmdBus)
    isAccum(b36918) = false
    bufferDepthOf(b36918) = 1
    val x35168 = StreamIn(field="data").name("x35168").ctrl(x35195).srcCtx("CellsPar.scala:192:20") // x35168 = StreamInNew(BurstDataBus())
    isAccum(x35168) = false
    bufferDepthOf(x35168) = 1
    val x35183 = UnitController(style=SeqPipe, level=InnerControl).name("x35183").ctrl(x35195).srcCtx("CellsPar.scala:192:20") // UnitPipe(List(b22662, b22653, b22654, b22364),Block(x35182))
    val x35169 = OpDef(op=FixAdd, inputs=List(b22651, b22661)).name("x35169").ctrl(x35183).srcCtx("CellsPar.scala:192:20") // FixAdd(b22651,b22661)
    val x35170 = x35169 // FixConvert(x35169,TRUE,_32,_0) (Same Type. No op)
    val x35171 = OpDef(op=FixSla, inputs=List(x35170, Const(9))).name("x35171").ctrl(x35183).srcCtx("CellsPar.scala:192:20") // FixLsh(x35170,Const(9))
    val x35172 = b22652 // FixConvert(b22652,TRUE,_32,_0) (Same Type. No op)
    val x35173 = OpDef(op=FixAdd, inputs=List(x35171, x35172)).name("x35173").ctrl(x35183).srcCtx("CellsPar.scala:192:20") // FixAdd(x35171,x35172)
    val x35174 = OpDef(op=FixSla, inputs=List(x35173, Const(2))).name("x35174").ctrl(x35183).srcCtx("CellsPar.scala:192:20") // FixLsh(x35173,Const(2))
    val x35175 = x35174 // FixConvert(x35174,TRUE,_64,_0)
    val x35176 = DramAddress(x34476).name("x35176").ctrl(x35183).srcCtx("CellsPar.scala:192:20") // GetDRAMAddress(x34476)
    val x35178_x35177 = OpDef(op=FixAdd, inputs=List(x35175, x35176)).name("x35178_x35177").ctrl(x35183).srcCtx("CellsPar.scala:192:20") // FixAdd(x35175,x35176)
    // x35178 = SimpleStruct(ArrayBuffer((offset,x35177), (size,Const(64)), (isLoad,Const(true))))
    val x35179 = OpDef(op=BitAnd, inputs=List(b22662, b22653)).name("x35179").ctrl(x35183).srcCtx("UnrollingBase.scala:28:66") // And(b22662,b22653)
    val x35180 = OpDef(op=BitAnd, inputs=List(b22654, b22364)).name("x35180").ctrl(x35183).srcCtx("UnrollingBase.scala:28:66") // And(b22654,b22364)
    val x35181 = OpDef(op=BitAnd, inputs=List(x35179, x35180)).name("x35181").ctrl(x35183).srcCtx("UnrollingBase.scala:28:66") // And(x35179,x35180)
    val x35182_b36919_b36917 = WriteMem(b36917, x35178_x35177).name("x35182_b36919_b36917").ctrl(x35183).srcCtx("CellsPar.scala:192:20") // StreamWrite(x35167,x35178,x35181)
    val x35182_b36920_b36918 = WriteMem(b36918, Const(64)).name("x35182_b36920_b36918").ctrl(x35183).srcCtx("CellsPar.scala:192:20") // StreamWrite(x35167,x35178,x35181)
    val x35184 = FringeDenseLoad(dram=List(x34476), cmdStream=List(b36917, b36918), dataStream=List(x35168)).name("x35184").ctrl(x35195).srcCtx("CellsPar.scala:192:20") // FringeDenseLoad(x34476,x35167,x35168)
    val x35185 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x35185").ctrl(x35195).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x35186 = CounterChain(List(x35185)).name("x35186").ctrl(x35195).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x35185))
    val x35194 = LoopController(style=InnerPipe, level=InnerControl, cchain=x35186).name("x35194").ctrl(x35195).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b22662, b22653, b22654, b22364),x35186,Block(Const(())),List(List(b22683)),List(List(b22684)))
    val b22683 = CounterIter(x35185, None).name("b22683").ctrl(x35194) // b22683
    val b22684 = Const(true).name("b22684").ctrl(x35194) // b22684
    val x35187 = OpDef(op=BitAnd, inputs=List(b22684, b22662)).name("x35187").ctrl(x35194).srcCtx("UnrollingBase.scala:28:66") // And(b22684,b22662)
    val x35188 = OpDef(op=BitAnd, inputs=List(b22653, b22654)).name("x35188").ctrl(x35194).srcCtx("UnrollingBase.scala:28:66") // And(b22653,b22654)
    val x35189 = OpDef(op=BitAnd, inputs=List(x35187, x35188)).name("x35189").ctrl(x35194).srcCtx("UnrollingBase.scala:28:66") // And(x35187,x35188)
    val x35190 = OpDef(op=BitAnd, inputs=List(x35189, b22364)).name("x35190").ctrl(x35194).srcCtx("UnrollingBase.scala:28:66") // And(x35189,b22364)
    val x35191_x35191 = ReadMem(x35168).name("x35191_x35191").ctrl(x35194).srcCtx("CellsPar.scala:192:20") // ParStreamRead(x35168,List(x35190))
    val x35192_x35192 = x35191_x35191 // x35192 = VectorApply(x35191,0)
    val x35193 = StoreBanks(List(x35161_d0_b0), List(b22661, b22683), x35192_x35192).name("x35193").ctrl(x35194).srcCtx("CellsPar.scala:192:20") // ParSRAMStore(x35161,List(List(b22661, b22683)),List(x35192),List(x35190))
    val x35196 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x35196").ctrl(x35266).srcCtx("CellsPar.scala:193:34") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x35197 = CounterChain(List(x35196)).name("x35197").ctrl(x35266).srcCtx("CellsPar.scala:193:46") // CounterChainNew(List(x35196))
    val x35265 = LoopController(style=MetaPipe, level=OuterControl, cchain=x35197).name("x35265").ctrl(x35266).srcCtx("CellsPar.scala:193:46") // UnrolledForeach(List(b22653, b22654, b22364),x35197,Block(Const(())),List(List(b22696)),List(List(b22697)))
    val b22696 = CounterIter(x35196, Some(0)).name("b22696").ctrl(x35265) // b22696
    val b22697 = Const(true).name("b22697").ctrl(x35265) // b22697
    val x35198 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x35198").ctrl(x35265).srcCtx("CellsPar.scala:194:28") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x35199 = CounterChain(List(x35198)).name("x35199").ctrl(x35265).srcCtx("CellsPar.scala:194:42") // CounterChainNew(List(x35198))
    val x35264 = LoopController(style=MetaPipe, level=OuterControl, cchain=x35199).name("x35264").ctrl(x35265).srcCtx("CellsPar.scala:194:42") // UnrolledForeach(List(b22697, b22653, b22654, b22364),x35199,Block(Const(())),List(List(b22700)),List(List(b22701)))
    val b22700 = CounterIter(x35198, Some(0)).name("b22700").ctrl(x35264) // b22700
    val b22701 = Const(true).name("b22701").ctrl(x35264) // b22701
    val x35200_d0 = Reg(init=Some(0.0)).name("x35200_d0").ctrl(x35264).srcCtx("CellsPar.scala:195:34:prod") // x35200 = RegNew(Const(0))
    isAccum(x35200_d0) = false
    bufferDepthOf(x35200_d0) = 2
    val x35200_d1 = Reg(init=Some(0.0)).name("x35200_d1").ctrl(x35264).srcCtx("CellsPar.scala:195:34:prod") // x35200 = RegNew(Const(0))
    isAccum(x35200_d1) = true
    bufferDepthOf(x35200_d1) = 1
    val x35201 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x35201").ctrl(x35264).srcCtx("CellsPar.scala:195:43") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x35202 = CounterChain(List(x35201)).name("x35202").ctrl(x35264).srcCtx("CellsPar.scala:207:15") // CounterChainNew(List(x35201))
    val x35228 = LoopController(style=InnerPipe, level=InnerControl, cchain=x35202).name("x35228").ctrl(x35264).srcCtx("CellsPar.scala:207:15") // UnrolledReduce(List(b22701, b22697, b22653, b22654, b22364),x35202,x35200,Block((x35200) => Const(())),List(List(b22705)),List(List(b22706)))
    val b22705 = CounterIter(x35201, None).name("b22705").ctrl(x35228) // b22705
    val b22706 = Const(true).name("b22706").ctrl(x35228) // b22706
    val x35203 = OpDef(op=FixAdd, inputs=List(b22651, b22705)).name("x35203").ctrl(x35228).srcCtx("CellsPar.scala:196:42:reduce_size_offset") // FixAdd(b22651,b22705)
    val x35204 = OpDef(op=BitAnd, inputs=List(b22706, b22701)).name("x35204").ctrl(x35228).srcCtx("UnrollingBase.scala:28:66") // And(b22706,b22701)
    val x35205 = OpDef(op=BitAnd, inputs=List(b22697, b22653)).name("x35205").ctrl(x35228).srcCtx("UnrollingBase.scala:28:66") // And(b22697,b22653)
    val x35206 = OpDef(op=BitAnd, inputs=List(b22654, b22364)).name("x35206").ctrl(x35228).srcCtx("UnrollingBase.scala:28:66") // And(b22654,b22364)
    val x35207 = OpDef(op=BitAnd, inputs=List(x35204, x35205)).name("x35207").ctrl(x35228).srcCtx("UnrollingBase.scala:28:66") // And(x35204,x35205)
    val x35208 = OpDef(op=BitAnd, inputs=List(x35207, x35206)).name("x35208").ctrl(x35228).srcCtx("UnrollingBase.scala:28:66") // And(x35207,x35206)
    val x35209 = LoadBanks(List(x35161_d0_b0), List(b22705, b22700)).name("x35209").ctrl(x35228).srcCtx("CellsPar.scala:203:34:tk") // ParSRAMLoad(x35161,List(List(b22705, b22700)),List(x35208))
    val x35210 = x35209 // x35210 = VectorApply(x35209,0)
    val x35211 = LoadBanks(List(x34750_d1_b0), List(b22696, x35203)).name("x35211").ctrl(x35228).srcCtx("CellsPar.scala:204:28") // ParSRAMLoad(x34750,List(List(b22696, x35203)),List(x35208))
    val x35212 = x35211 // x35212 = VectorApply(x35211,0)
    val x35213 = OpDef(op=FixMul, inputs=List(x35212, x35210)).name("x35213").ctrl(x35228).srcCtx("CellsPar.scala:204:52:tempA") // FixMul(x35212,x35210)
    val x35214 = OpDef(op=FixSub, inputs=List(x35203, Const(512))).name("x35214").ctrl(x35228).srcCtx("CellsPar.scala:205:51") // FixSub(x35203,Const(512))
    val x35215 = LoadBanks(List(x34751_d5_b0), List(b22696, x35214)).name("x35215").ctrl(x35228).srcCtx("CellsPar.scala:205:28") // ParSRAMLoad(x34751,List(List(b22696, x35214)),List(x35208))
    val x35216 = x35215 // x35216 = VectorApply(x35215,0)
    val x35217 = OpDef(op=FixMul, inputs=List(x35216, x35210)).name("x35217").ctrl(x35228).srcCtx("CellsPar.scala:205:67:tempB") // FixMul(x35216,x35210)
    val x35218 = OpDef(op=FixLt, inputs=List(x35203, Const(512))).name("x35218").ctrl(x35228).srcCtx("CellsPar.scala:206:38") // FixLt(x35203,Const(512))
    val x35219 = OpDef(op=MuxOp, inputs=List(x35218, x35213, x35217)).name("x35219").ctrl(x35228).srcCtx("CellsPar.scala:206:18") // Mux(x35218,x35213,x35217)
    val x35220 = ReadMem(x35200_d1).name("x35220").ctrl(x35228).srcCtx("CellsPar.scala:207:15") // RegRead(x35200)
    val x35221 = OpDef(op=FixEql, inputs=List(b22705, Const(0))).name("x35221").ctrl(x35228).srcCtx("CellsPar.scala:207:15") // FixEql(b22705,Const(0))
    val x35222 = ReduceAccumOp(op=FixAdd, input=x35219, accum=x35220).name("x35222").ctrl(x35228).srcCtx("CellsPar.scala:207:17") // FixAdd(x35219,x35220)
    val x35223 = OpDef(op=BitAnd, inputs=List(b22701, b22697)).name("x35223").ctrl(x35228).srcCtx("UnrollingBase.scala:28:66") // And(b22701,b22697)
    val x35224 = OpDef(op=BitAnd, inputs=List(b22653, b22654)).name("x35224").ctrl(x35228).srcCtx("UnrollingBase.scala:28:66") // And(b22653,b22654)
    val x35225 = OpDef(op=BitAnd, inputs=List(x35223, x35224)).name("x35225").ctrl(x35228).srcCtx("UnrollingBase.scala:28:66") // And(x35223,x35224)
    val x35226 = OpDef(op=BitAnd, inputs=List(x35225, b22364)).name("x35226").ctrl(x35228).srcCtx("UnrollingBase.scala:28:66") // And(x35225,b22364)
    val x35227_x35200_d0 = WriteMem(x35200_d0, x35222).name("x35227_x35200_d0").ctrl(x35228).srcCtx("CellsPar.scala:207:15") // RegWrite(x35200,x35222,x35226)
    val x35227_x35200_d1 = WriteMem(x35200_d1, x35222).name("x35227_x35200_d1").ctrl(x35228).srcCtx("CellsPar.scala:207:15") // RegWrite(x35200,x35222,x35226)
    val x35263 = UnitController(style=SeqPipe, level=InnerControl).name("x35263").ctrl(x35264).srcCtx("CellsPar.scala:194:42") // UnitPipe(List(b22701, b22697, b22653, b22654, b22364),Block(Const(())))
    val x35229 = OpDef(op=FixAdd, inputs=List(b22652, b22700)).name("x35229").ctrl(x35263).srcCtx("CellsPar.scala:209:31:colOffset") // FixAdd(b22652,b22700)
    val x35230 = ReadMem(x35200_d0).name("x35230").ctrl(x35263).srcCtx("CellsPar.scala:210:28") // RegRead(x35200)
    val x35231 = OpDef(op=FixEql, inputs=List(b22651, Const(0))).name("x35231").ctrl(x35263).srcCtx("CellsPar.scala:210:42") // FixEql(b22651,Const(0))
    val x35232 = OpDef(op=FixAdd, inputs=List(x35229, Const(1024))).name("x35232").ctrl(x35263).srcCtx("CellsPar.scala:210:66") // FixAdd(x35229,Const(1024))
    val x35233 = OpDef(op=BitAnd, inputs=List(b22701, b22697)).name("x35233").ctrl(x35263).srcCtx("UnrollingBase.scala:28:66") // And(b22701,b22697)
    val x35234 = OpDef(op=BitAnd, inputs=List(b22653, b22654)).name("x35234").ctrl(x35263).srcCtx("UnrollingBase.scala:28:66") // And(b22653,b22654)
    val x35235 = OpDef(op=BitAnd, inputs=List(x35233, x35234)).name("x35235").ctrl(x35263).srcCtx("UnrollingBase.scala:28:66") // And(x35233,x35234)
    val x35236 = OpDef(op=BitAnd, inputs=List(x35235, b22364)).name("x35236").ctrl(x35263).srcCtx("UnrollingBase.scala:28:66") // And(x35235,b22364)
    val x35237 = LoadBanks(List(x34757_d1_b0), List(Const(0), x35232)).name("x35237").ctrl(x35263).srcCtx("CellsPar.scala:210:52") // SRAMLoad(x34757,ArrayBuffer(Const(1), Const(2048)),List(Const(0), x35232),Const(0),x35236)
    val x35238 = LoadBanks(List(x34755_d1_b0), List(b22696, x35229)).name("x35238").ctrl(x35263).srcCtx("CellsPar.scala:210:91") // SRAMLoad(x34755,ArrayBuffer(Const(1), Const(512)),List(b22696, x35229),Const(0),x35236)
    val x35239 = OpDef(op=MuxOp, inputs=List(x35231, x35237, x35238)).name("x35239").ctrl(x35263).srcCtx("CellsPar.scala:210:39") // Mux(x35231,x35237,x35238)
    val x35240 = OpDef(op=FixAdd, inputs=List(x35230, x35239)).name("x35240").ctrl(x35263).srcCtx("CellsPar.scala:210:34:ele") // FixAdd(x35230,x35239)
    val x35241 = OpDef(op=FixLeq, inputs=List(Const(1520), b22651)).name("x35241").ctrl(x35263).srcCtx("CellsPar.scala:218:46") // FixLeq(Const(1520),b22651)
    // x35242 = FixConvert(Const(1),TRUE,_8,_24) x.tp=FixPt[TRUE,_32,_0] {
    val x35242_int1 = OpDef(op=BitAnd, inputs=List(Const(1), Const("01111111111111111111111111111111"))).name("x35242_int1").ctrl(x35263).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(1),TRUE,_8,_24)
    val x35242_int2 = OpDef(op=FixSla, inputs=List(x35242_int1, Const(24))).name("x35242_int2").ctrl(x35263).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(1),TRUE,_8,_24)
    val x35242_frac1 = OpDef(op=BitAnd, inputs=List(Const(1), Const("00000000000000000000000000000000"))).name("x35242_frac1").ctrl(x35263).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(1),TRUE,_8,_24)
    val x35242_frac2 = OpDef(op=FixSla, inputs=List(x35242_frac1, Const(24))).name("x35242_frac2").ctrl(x35263).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(1),TRUE,_8,_24)
    val x35242 = OpDef(op=BitOr, inputs=List(x35242_int2, x35242_frac2)).name("x35242").ctrl(x35263).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(1),TRUE,_8,_24)
    // }
    val x35243 = OpDef(op=FixAdd, inputs=List(x35240, x35242)).name("x35243").ctrl(x35263).srcCtx("CellsPar.scala:218:87") // FixAdd(x35240,x35242)
    val x35244 = OpDef(op=FixSra, inputs=List(x35243, Const(1))).name("x35244").ctrl(x35263).srcCtx("CellsPar.scala:29:22") // FixRsh(x35243,Const(1))
    val x35245 = x35244 // FixConvert(x35244,TRUE,_8,_24) (Same Type. No op)
    val x35246 = OpDef(op=FixAbs, inputs=List(x35245)).name("x35246").ctrl(x35263).srcCtx("CellsPar.scala:37:19:absp") // FixAbs(x35245)
    val x35247 = OpDef(op=FixLt, inputs=List(Const(2.5), x35246)).name("x35247").ctrl(x35263).srcCtx("CellsPar.scala:51:23:cond1") // FixLt(Const(2.5),x35246)
    val x35248 = OpDef(op=FixLt, inputs=List(Const(0.5), x35246)).name("x35248").ctrl(x35263).srcCtx("CellsPar.scala:54:35") // FixLt(Const(0.5),x35246)
    val x35249 = OpDef(op=FixLeq, inputs=List(x35246, Const(2.5))).name("x35249").ctrl(x35263).srcCtx("CellsPar.scala:54:52") // FixLeq(x35246,Const(2.5))
    val x35250 = OpDef(op=BitAnd, inputs=List(x35248, x35249)).name("x35250").ctrl(x35263).srcCtx("CellsPar.scala:54:43:cond2") // And(x35248,x35249)
    val x35251 = OpDef(op=FixSra, inputs=List(x35246, Const(2))).name("x35251").ctrl(x35263).srcCtx("CellsPar.scala:56:21:div4") // FixRsh(x35246,Const(2))
    val x35252 = OpDef(op=FixAdd, inputs=List(x35251, Const(0.375))).name("x35252").ctrl(x35263).srcCtx("CellsPar.scala:57:27:div4Offset") // FixAdd(x35251,Const(0.375))
    val x35253 = OpDef(op=MuxOp, inputs=List(x35250, x35252, x35246)).name("x35253").ctrl(x35263).srcCtx("CellsPar.scala:58:20:body2") // Mux(x35250,x35252,x35246)
    val x35254 = OpDef(op=MuxOp, inputs=List(x35247, Const(1.0), x35253)).name("x35254").ctrl(x35263).srcCtx("CellsPar.scala:60:20:absre") // Mux(x35247,Const(1),x35253)
    val x35255 = OpDef(op=FixNeg, inputs=List(x35254)).name("x35255").ctrl(x35263).srcCtx("CellsPar.scala:62:29:mabre") // FixNeg(x35254)
    val x35256 = OpDef(op=FixLt, inputs=List(x35245, Const(0.0))).name("x35256").ctrl(x35263).srcCtx("CellsPar.scala:63:22") // FixLt(x35245,Const(0))
    val x35257 = OpDef(op=MuxOp, inputs=List(x35256, x35255, x35254)).name("x35257").ctrl(x35263).srcCtx("CellsPar.scala:63:17:re") // Mux(x35256,x35255,x35254)
    val x35258 = x35257 // FixConvert(x35257,TRUE,_8,_24) (Same Type. No op)
    val x35259 = OpDef(op=FixAdd, inputs=List(x35258, Const(1.0))).name("x35259").ctrl(x35263).srcCtx("CellsPar.scala:29:33") // FixAdd(x35258,Const(1))
    val x35260 = OpDef(op=FixSra, inputs=List(x35259, Const(1))).name("x35260").ctrl(x35263).srcCtx("CellsPar.scala:29:44") // FixRsh(x35259,Const(1))
    val x35261 = OpDef(op=MuxOp, inputs=List(x35241, x35260, x35240)).name("x35261").ctrl(x35263).srcCtx("CellsPar.scala:218:43") // Mux(x35241,x35260,x35240)
    val x35262 = StoreBanks(List(x34755_d0_b0, x34755_d1_b0), List(b22696, x35229), x35261).name("x35262").ctrl(x35263).srcCtx("CellsPar.scala:218:38") // SRAMStore(x34755,ArrayBuffer(Const(1), Const(512)),List(b22696, x35229),Const(0),x35261,x35236)
    val x35267 = Counter(min=Const(0), max=Const(512), step=Const(16), par=1).name("x35267").ctrl(x36822).srcCtx("CellsPar.scala:190:58") // CounterNew(Const(0),Const(512),Const(16),Const(1))
    val x35268 = Counter(min=Const(0), max=Const(1536), step=Const(16), par=1).name("x35268").ctrl(x36822).srcCtx("CellsPar.scala:190:33") // CounterNew(Const(0),Const(1536),Const(16),Const(1))
    val x35269 = CounterChain(List(x35268,x35267)).name("x35269").ctrl(x36822).srcCtx("CellsPar.scala:190:72") // CounterChainNew(List(x35268, x35267))
    val x35375 = LoopController(style=MetaPipe, level=OuterControl, cchain=x35269).name("x35375").ctrl(x36822).srcCtx("CellsPar.scala:190:72") // UnrolledForeach(List(b22364),x35269,Block(Const(())),List(List(b22774), List(b22775)),List(List(b22776), List(b22777)))
    val b22774 = CounterIter(x35268, Some(0)).name("b22774").ctrl(x35375) // b22774
    val b22776 = Const(true).name("b22776").ctrl(x35375) // b22776
    val b22775 = CounterIter(x35267, Some(0)).name("b22775").ctrl(x35375) // b22775
    val b22777 = Const(true).name("b22777").ctrl(x35375) // b22777
    val x35270_d0_b0 = SRAM(size=256, banking=Strided(banks=1, stride=16)).name("x35270_d0_b0").ctrl(x35375).srcCtx("CellsPar.scala:191:33:tileKernel") // x35270 = SRAMNew(ArrayBuffer(Const(16), Const(16)))
    isAccum(x35270_d0_b0) = false
    bufferDepthOf(x35270_d0_b0) = 2
    val x35273 = UnitController(style=SeqPipe, level=InnerControl).name("x35273").ctrl(x35375).srcCtx("CellsPar.scala:190:72") // UnitPipe(List(b22776, b22777, b22364),Block(Const(())))
    val x35271 = OpDef(op=FixAdd, inputs=List(b22774, Const(16))).name("x35271").ctrl(x35273).srcCtx("CellsPar.scala:192:36") // FixAdd(b22774,Const(16))
    val x35272 = OpDef(op=FixAdd, inputs=List(b22775, Const(16))).name("x35272").ctrl(x35273).srcCtx("CellsPar.scala:192:45") // FixAdd(b22775,Const(16))
    val x35274 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x35274").ctrl(x35375).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x35275 = CounterChain(List(x35274)).name("x35275").ctrl(x35375).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x35274))
    val x35304 = LoopController(style=StreamPipe, level=OuterControl, cchain=x35275).name("x35304").ctrl(x35375).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b22776, b22777, b22364),x35275,Block(Const(())),List(List(b22784)),List(List(b22785)))
    val b22784 = CounterIter(x35274, Some(0)).name("b22784").ctrl(x35304) // b22784
    val b22785 = Const(true).name("b22785").ctrl(x35304) // b22785
    val b36921 = StreamOut(field="offset").name("b36921").ctrl(x35304).srcCtx("CellsPar.scala:192:20") // x35276 = StreamOutNew(BurstCmdBus)
    isAccum(b36921) = false
    bufferDepthOf(b36921) = 1
    val b36922 = StreamOut(field="size").name("b36922").ctrl(x35304).srcCtx("CellsPar.scala:192:20") // x35276 = StreamOutNew(BurstCmdBus)
    isAccum(b36922) = false
    bufferDepthOf(b36922) = 1
    val x35277 = StreamIn(field="data").name("x35277").ctrl(x35304).srcCtx("CellsPar.scala:192:20") // x35277 = StreamInNew(BurstDataBus())
    isAccum(x35277) = false
    bufferDepthOf(x35277) = 1
    val x35292 = UnitController(style=SeqPipe, level=InnerControl).name("x35292").ctrl(x35304).srcCtx("CellsPar.scala:192:20") // UnitPipe(List(b22785, b22776, b22777, b22364),Block(x35291))
    val x35278 = OpDef(op=FixAdd, inputs=List(b22774, b22784)).name("x35278").ctrl(x35292).srcCtx("CellsPar.scala:192:20") // FixAdd(b22774,b22784)
    val x35279 = x35278 // FixConvert(x35278,TRUE,_32,_0) (Same Type. No op)
    val x35280 = OpDef(op=FixSla, inputs=List(x35279, Const(9))).name("x35280").ctrl(x35292).srcCtx("CellsPar.scala:192:20") // FixLsh(x35279,Const(9))
    val x35281 = b22775 // FixConvert(b22775,TRUE,_32,_0) (Same Type. No op)
    val x35282 = OpDef(op=FixAdd, inputs=List(x35280, x35281)).name("x35282").ctrl(x35292).srcCtx("CellsPar.scala:192:20") // FixAdd(x35280,x35281)
    val x35283 = OpDef(op=FixSla, inputs=List(x35282, Const(2))).name("x35283").ctrl(x35292).srcCtx("CellsPar.scala:192:20") // FixLsh(x35282,Const(2))
    val x35284 = x35283 // FixConvert(x35283,TRUE,_64,_0)
    val x35285 = DramAddress(x34477).name("x35285").ctrl(x35292).srcCtx("CellsPar.scala:192:20") // GetDRAMAddress(x34477)
    val x35287_x35286 = OpDef(op=FixAdd, inputs=List(x35284, x35285)).name("x35287_x35286").ctrl(x35292).srcCtx("CellsPar.scala:192:20") // FixAdd(x35284,x35285)
    // x35287 = SimpleStruct(ArrayBuffer((offset,x35286), (size,Const(64)), (isLoad,Const(true))))
    val x35288 = OpDef(op=BitAnd, inputs=List(b22785, b22776)).name("x35288").ctrl(x35292).srcCtx("UnrollingBase.scala:28:66") // And(b22785,b22776)
    val x35289 = OpDef(op=BitAnd, inputs=List(b22777, b22364)).name("x35289").ctrl(x35292).srcCtx("UnrollingBase.scala:28:66") // And(b22777,b22364)
    val x35290 = OpDef(op=BitAnd, inputs=List(x35288, x35289)).name("x35290").ctrl(x35292).srcCtx("UnrollingBase.scala:28:66") // And(x35288,x35289)
    val x35291_b36923_b36921 = WriteMem(b36921, x35287_x35286).name("x35291_b36923_b36921").ctrl(x35292).srcCtx("CellsPar.scala:192:20") // StreamWrite(x35276,x35287,x35290)
    val x35291_b36924_b36922 = WriteMem(b36922, Const(64)).name("x35291_b36924_b36922").ctrl(x35292).srcCtx("CellsPar.scala:192:20") // StreamWrite(x35276,x35287,x35290)
    val x35293 = FringeDenseLoad(dram=List(x34477), cmdStream=List(b36921, b36922), dataStream=List(x35277)).name("x35293").ctrl(x35304).srcCtx("CellsPar.scala:192:20") // FringeDenseLoad(x34477,x35276,x35277)
    val x35294 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x35294").ctrl(x35304).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x35295 = CounterChain(List(x35294)).name("x35295").ctrl(x35304).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x35294))
    val x35303 = LoopController(style=InnerPipe, level=InnerControl, cchain=x35295).name("x35303").ctrl(x35304).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b22785, b22776, b22777, b22364),x35295,Block(Const(())),List(List(b22806)),List(List(b22807)))
    val b22806 = CounterIter(x35294, None).name("b22806").ctrl(x35303) // b22806
    val b22807 = Const(true).name("b22807").ctrl(x35303) // b22807
    val x35296 = OpDef(op=BitAnd, inputs=List(b22807, b22785)).name("x35296").ctrl(x35303).srcCtx("UnrollingBase.scala:28:66") // And(b22807,b22785)
    val x35297 = OpDef(op=BitAnd, inputs=List(b22776, b22777)).name("x35297").ctrl(x35303).srcCtx("UnrollingBase.scala:28:66") // And(b22776,b22777)
    val x35298 = OpDef(op=BitAnd, inputs=List(x35296, x35297)).name("x35298").ctrl(x35303).srcCtx("UnrollingBase.scala:28:66") // And(x35296,x35297)
    val x35299 = OpDef(op=BitAnd, inputs=List(x35298, b22364)).name("x35299").ctrl(x35303).srcCtx("UnrollingBase.scala:28:66") // And(x35298,b22364)
    val x35300_x35300 = ReadMem(x35277).name("x35300_x35300").ctrl(x35303).srcCtx("CellsPar.scala:192:20") // ParStreamRead(x35277,List(x35299))
    val x35301_x35301 = x35300_x35300 // x35301 = VectorApply(x35300,0)
    val x35302 = StoreBanks(List(x35270_d0_b0), List(b22784, b22806), x35301_x35301).name("x35302").ctrl(x35303).srcCtx("CellsPar.scala:192:20") // ParSRAMStore(x35270,List(List(b22784, b22806)),List(x35301),List(x35299))
    val x35305 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x35305").ctrl(x35375).srcCtx("CellsPar.scala:193:34") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x35306 = CounterChain(List(x35305)).name("x35306").ctrl(x35375).srcCtx("CellsPar.scala:193:46") // CounterChainNew(List(x35305))
    val x35374 = LoopController(style=MetaPipe, level=OuterControl, cchain=x35306).name("x35374").ctrl(x35375).srcCtx("CellsPar.scala:193:46") // UnrolledForeach(List(b22776, b22777, b22364),x35306,Block(Const(())),List(List(b22819)),List(List(b22820)))
    val b22819 = CounterIter(x35305, Some(0)).name("b22819").ctrl(x35374) // b22819
    val b22820 = Const(true).name("b22820").ctrl(x35374) // b22820
    val x35307 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x35307").ctrl(x35374).srcCtx("CellsPar.scala:194:28") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x35308 = CounterChain(List(x35307)).name("x35308").ctrl(x35374).srcCtx("CellsPar.scala:194:42") // CounterChainNew(List(x35307))
    val x35373 = LoopController(style=MetaPipe, level=OuterControl, cchain=x35308).name("x35373").ctrl(x35374).srcCtx("CellsPar.scala:194:42") // UnrolledForeach(List(b22820, b22776, b22777, b22364),x35308,Block(Const(())),List(List(b22823)),List(List(b22824)))
    val b22823 = CounterIter(x35307, Some(0)).name("b22823").ctrl(x35373) // b22823
    val b22824 = Const(true).name("b22824").ctrl(x35373) // b22824
    val x35309_d0 = Reg(init=Some(0.0)).name("x35309_d0").ctrl(x35373).srcCtx("CellsPar.scala:195:34:prod") // x35309 = RegNew(Const(0))
    isAccum(x35309_d0) = false
    bufferDepthOf(x35309_d0) = 2
    val x35309_d1 = Reg(init=Some(0.0)).name("x35309_d1").ctrl(x35373).srcCtx("CellsPar.scala:195:34:prod") // x35309 = RegNew(Const(0))
    isAccum(x35309_d1) = true
    bufferDepthOf(x35309_d1) = 1
    val x35310 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x35310").ctrl(x35373).srcCtx("CellsPar.scala:195:43") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x35311 = CounterChain(List(x35310)).name("x35311").ctrl(x35373).srcCtx("CellsPar.scala:207:15") // CounterChainNew(List(x35310))
    val x35337 = LoopController(style=InnerPipe, level=InnerControl, cchain=x35311).name("x35337").ctrl(x35373).srcCtx("CellsPar.scala:207:15") // UnrolledReduce(List(b22824, b22820, b22776, b22777, b22364),x35311,x35309,Block((x35309) => Const(())),List(List(b22828)),List(List(b22829)))
    val b22828 = CounterIter(x35310, None).name("b22828").ctrl(x35337) // b22828
    val b22829 = Const(true).name("b22829").ctrl(x35337) // b22829
    val x35312 = OpDef(op=FixAdd, inputs=List(b22774, b22828)).name("x35312").ctrl(x35337).srcCtx("CellsPar.scala:196:42:reduce_size_offset") // FixAdd(b22774,b22828)
    val x35313 = OpDef(op=BitAnd, inputs=List(b22829, b22824)).name("x35313").ctrl(x35337).srcCtx("UnrollingBase.scala:28:66") // And(b22829,b22824)
    val x35314 = OpDef(op=BitAnd, inputs=List(b22820, b22776)).name("x35314").ctrl(x35337).srcCtx("UnrollingBase.scala:28:66") // And(b22820,b22776)
    val x35315 = OpDef(op=BitAnd, inputs=List(b22777, b22364)).name("x35315").ctrl(x35337).srcCtx("UnrollingBase.scala:28:66") // And(b22777,b22364)
    val x35316 = OpDef(op=BitAnd, inputs=List(x35313, x35314)).name("x35316").ctrl(x35337).srcCtx("UnrollingBase.scala:28:66") // And(x35313,x35314)
    val x35317 = OpDef(op=BitAnd, inputs=List(x35316, x35315)).name("x35317").ctrl(x35337).srcCtx("UnrollingBase.scala:28:66") // And(x35316,x35315)
    val x35318 = LoadBanks(List(x35270_d0_b0), List(b22828, b22823)).name("x35318").ctrl(x35337).srcCtx("CellsPar.scala:203:34:tk") // ParSRAMLoad(x35270,List(List(b22828, b22823)),List(x35317))
    val x35319 = x35318 // x35319 = VectorApply(x35318,0)
    val x35320 = LoadBanks(List(x34750_d0_b0), List(b22819, x35312)).name("x35320").ctrl(x35337).srcCtx("CellsPar.scala:204:28") // ParSRAMLoad(x34750,List(List(b22819, x35312)),List(x35317))
    val x35321 = x35320 // x35321 = VectorApply(x35320,0)
    val x35322 = OpDef(op=FixMul, inputs=List(x35321, x35319)).name("x35322").ctrl(x35337).srcCtx("CellsPar.scala:204:52:tempA") // FixMul(x35321,x35319)
    val x35323 = OpDef(op=FixSub, inputs=List(x35312, Const(512))).name("x35323").ctrl(x35337).srcCtx("CellsPar.scala:205:51") // FixSub(x35312,Const(512))
    val x35324 = LoadBanks(List(x34751_d4_b0), List(b22819, x35323)).name("x35324").ctrl(x35337).srcCtx("CellsPar.scala:205:28") // ParSRAMLoad(x34751,List(List(b22819, x35323)),List(x35317))
    val x35325 = x35324 // x35325 = VectorApply(x35324,0)
    val x35326 = OpDef(op=FixMul, inputs=List(x35325, x35319)).name("x35326").ctrl(x35337).srcCtx("CellsPar.scala:205:67:tempB") // FixMul(x35325,x35319)
    val x35327 = OpDef(op=FixLt, inputs=List(x35312, Const(512))).name("x35327").ctrl(x35337).srcCtx("CellsPar.scala:206:38") // FixLt(x35312,Const(512))
    val x35328 = OpDef(op=MuxOp, inputs=List(x35327, x35322, x35326)).name("x35328").ctrl(x35337).srcCtx("CellsPar.scala:206:18") // Mux(x35327,x35322,x35326)
    val x35329 = ReadMem(x35309_d1).name("x35329").ctrl(x35337).srcCtx("CellsPar.scala:207:15") // RegRead(x35309)
    val x35330 = OpDef(op=FixEql, inputs=List(b22828, Const(0))).name("x35330").ctrl(x35337).srcCtx("CellsPar.scala:207:15") // FixEql(b22828,Const(0))
    val x35331 = ReduceAccumOp(op=FixAdd, input=x35328, accum=x35329).name("x35331").ctrl(x35337).srcCtx("CellsPar.scala:207:17") // FixAdd(x35328,x35329)
    val x35332 = OpDef(op=BitAnd, inputs=List(b22824, b22820)).name("x35332").ctrl(x35337).srcCtx("UnrollingBase.scala:28:66") // And(b22824,b22820)
    val x35333 = OpDef(op=BitAnd, inputs=List(b22776, b22777)).name("x35333").ctrl(x35337).srcCtx("UnrollingBase.scala:28:66") // And(b22776,b22777)
    val x35334 = OpDef(op=BitAnd, inputs=List(x35332, x35333)).name("x35334").ctrl(x35337).srcCtx("UnrollingBase.scala:28:66") // And(x35332,x35333)
    val x35335 = OpDef(op=BitAnd, inputs=List(x35334, b22364)).name("x35335").ctrl(x35337).srcCtx("UnrollingBase.scala:28:66") // And(x35334,b22364)
    val x35336_x35309_d0 = WriteMem(x35309_d0, x35331).name("x35336_x35309_d0").ctrl(x35337).srcCtx("CellsPar.scala:207:15") // RegWrite(x35309,x35331,x35335)
    val x35336_x35309_d1 = WriteMem(x35309_d1, x35331).name("x35336_x35309_d1").ctrl(x35337).srcCtx("CellsPar.scala:207:15") // RegWrite(x35309,x35331,x35335)
    val x35372 = UnitController(style=SeqPipe, level=InnerControl).name("x35372").ctrl(x35373).srcCtx("CellsPar.scala:194:42") // UnitPipe(List(b22824, b22820, b22776, b22777, b22364),Block(Const(())))
    val x35338 = OpDef(op=FixAdd, inputs=List(b22775, b22823)).name("x35338").ctrl(x35372).srcCtx("CellsPar.scala:209:31:colOffset") // FixAdd(b22775,b22823)
    val x35339 = ReadMem(x35309_d0).name("x35339").ctrl(x35372).srcCtx("CellsPar.scala:210:28") // RegRead(x35309)
    val x35340 = OpDef(op=FixEql, inputs=List(b22774, Const(0))).name("x35340").ctrl(x35372).srcCtx("CellsPar.scala:210:42") // FixEql(b22774,Const(0))
    val x35341 = OpDef(op=FixAdd, inputs=List(x35338, Const(1536))).name("x35341").ctrl(x35372).srcCtx("CellsPar.scala:210:66") // FixAdd(x35338,Const(1536))
    val x35342 = OpDef(op=BitAnd, inputs=List(b22824, b22820)).name("x35342").ctrl(x35372).srcCtx("UnrollingBase.scala:28:66") // And(b22824,b22820)
    val x35343 = OpDef(op=BitAnd, inputs=List(b22776, b22777)).name("x35343").ctrl(x35372).srcCtx("UnrollingBase.scala:28:66") // And(b22776,b22777)
    val x35344 = OpDef(op=BitAnd, inputs=List(x35342, x35343)).name("x35344").ctrl(x35372).srcCtx("UnrollingBase.scala:28:66") // And(x35342,x35343)
    val x35345 = OpDef(op=BitAnd, inputs=List(x35344, b22364)).name("x35345").ctrl(x35372).srcCtx("UnrollingBase.scala:28:66") // And(x35344,b22364)
    val x35346 = LoadBanks(List(x34757_d0_b0), List(Const(0), x35341)).name("x35346").ctrl(x35372).srcCtx("CellsPar.scala:210:52") // SRAMLoad(x34757,ArrayBuffer(Const(1), Const(2048)),List(Const(0), x35341),Const(0),x35345)
    val x35347 = LoadBanks(List(x34756_d1_b0), List(b22819, x35338)).name("x35347").ctrl(x35372).srcCtx("CellsPar.scala:210:91") // SRAMLoad(x34756,ArrayBuffer(Const(1), Const(512)),List(b22819, x35338),Const(0),x35345)
    val x35348 = OpDef(op=MuxOp, inputs=List(x35340, x35346, x35347)).name("x35348").ctrl(x35372).srcCtx("CellsPar.scala:210:39") // Mux(x35340,x35346,x35347)
    val x35349 = OpDef(op=FixAdd, inputs=List(x35339, x35348)).name("x35349").ctrl(x35372).srcCtx("CellsPar.scala:210:34:ele") // FixAdd(x35339,x35348)
    val x35350 = OpDef(op=FixLeq, inputs=List(Const(1520), b22774)).name("x35350").ctrl(x35372).srcCtx("CellsPar.scala:218:46") // FixLeq(Const(1520),b22774)
    // x35351 = FixConvert(Const(0),TRUE,_8,_24) x.tp=FixPt[TRUE,_32,_0] {
    val x35351_int1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("01111111111111111111111111111111"))).name("x35351_int1").ctrl(x35372).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x35351_int2 = OpDef(op=FixSla, inputs=List(x35351_int1, Const(24))).name("x35351_int2").ctrl(x35372).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x35351_frac1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("00000000000000000000000000000000"))).name("x35351_frac1").ctrl(x35372).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x35351_frac2 = OpDef(op=FixSla, inputs=List(x35351_frac1, Const(24))).name("x35351_frac2").ctrl(x35372).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x35351 = OpDef(op=BitOr, inputs=List(x35351_int2, x35351_frac2)).name("x35351").ctrl(x35372).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    // }
    val x35352 = OpDef(op=FixAdd, inputs=List(x35349, x35351)).name("x35352").ctrl(x35372).srcCtx("CellsPar.scala:218:87") // FixAdd(x35349,x35351)
    val x35353 = OpDef(op=FixSra, inputs=List(x35352, Const(1))).name("x35353").ctrl(x35372).srcCtx("CellsPar.scala:29:22") // FixRsh(x35352,Const(1))
    val x35354 = x35353 // FixConvert(x35353,TRUE,_8,_24) (Same Type. No op)
    val x35355 = OpDef(op=FixAbs, inputs=List(x35354)).name("x35355").ctrl(x35372).srcCtx("CellsPar.scala:37:19:absp") // FixAbs(x35354)
    val x35356 = OpDef(op=FixLt, inputs=List(Const(2.5), x35355)).name("x35356").ctrl(x35372).srcCtx("CellsPar.scala:51:23:cond1") // FixLt(Const(2.5),x35355)
    val x35357 = OpDef(op=FixLt, inputs=List(Const(0.5), x35355)).name("x35357").ctrl(x35372).srcCtx("CellsPar.scala:54:35") // FixLt(Const(0.5),x35355)
    val x35358 = OpDef(op=FixLeq, inputs=List(x35355, Const(2.5))).name("x35358").ctrl(x35372).srcCtx("CellsPar.scala:54:52") // FixLeq(x35355,Const(2.5))
    val x35359 = OpDef(op=BitAnd, inputs=List(x35357, x35358)).name("x35359").ctrl(x35372).srcCtx("CellsPar.scala:54:43:cond2") // And(x35357,x35358)
    val x35360 = OpDef(op=FixSra, inputs=List(x35355, Const(2))).name("x35360").ctrl(x35372).srcCtx("CellsPar.scala:56:21:div4") // FixRsh(x35355,Const(2))
    val x35361 = OpDef(op=FixAdd, inputs=List(x35360, Const(0.375))).name("x35361").ctrl(x35372).srcCtx("CellsPar.scala:57:27:div4Offset") // FixAdd(x35360,Const(0.375))
    val x35362 = OpDef(op=MuxOp, inputs=List(x35359, x35361, x35355)).name("x35362").ctrl(x35372).srcCtx("CellsPar.scala:58:20:body2") // Mux(x35359,x35361,x35355)
    val x35363 = OpDef(op=MuxOp, inputs=List(x35356, Const(1.0), x35362)).name("x35363").ctrl(x35372).srcCtx("CellsPar.scala:60:20:absre") // Mux(x35356,Const(1),x35362)
    val x35364 = OpDef(op=FixNeg, inputs=List(x35363)).name("x35364").ctrl(x35372).srcCtx("CellsPar.scala:62:29:mabre") // FixNeg(x35363)
    val x35365 = OpDef(op=FixLt, inputs=List(x35354, Const(0.0))).name("x35365").ctrl(x35372).srcCtx("CellsPar.scala:63:22") // FixLt(x35354,Const(0))
    val x35366 = OpDef(op=MuxOp, inputs=List(x35365, x35364, x35363)).name("x35366").ctrl(x35372).srcCtx("CellsPar.scala:63:17:re") // Mux(x35365,x35364,x35363)
    val x35367 = x35366 // FixConvert(x35366,TRUE,_8,_24) (Same Type. No op)
    val x35368 = OpDef(op=FixAdd, inputs=List(x35367, Const(1.0))).name("x35368").ctrl(x35372).srcCtx("CellsPar.scala:29:33") // FixAdd(x35367,Const(1))
    val x35369 = OpDef(op=FixSra, inputs=List(x35368, Const(1))).name("x35369").ctrl(x35372).srcCtx("CellsPar.scala:29:44") // FixRsh(x35368,Const(1))
    val x35370 = OpDef(op=MuxOp, inputs=List(x35350, x35369, x35349)).name("x35370").ctrl(x35372).srcCtx("CellsPar.scala:218:43") // Mux(x35350,x35369,x35349)
    val x35371 = StoreBanks(List(x34756_d0_b0, x34756_d1_b0), List(b22819, x35338), x35370).name("x35371").ctrl(x35372).srcCtx("CellsPar.scala:218:38") // SRAMStore(x34756,ArrayBuffer(Const(1), Const(512)),List(b22819, x35338),Const(0),x35370,x35345)
    val x35376 = Counter(min=Const(0), max=Const(512), step=Const(1), par=1).name("x35376").ctrl(x36822).srcCtx("CellsPar.scala:243:50") // CounterNew(Const(0),Const(512),Const(1),Const(1))
    val x35377 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x35377").ctrl(x36822).srcCtx("CellsPar.scala:243:27") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x35378 = CounterChain(List(x35377,x35376)).name("x35378").ctrl(x36822).srcCtx("CellsPar.scala:243:62") // CounterChainNew(List(x35377, x35376))
    val x35411 = LoopController(style=InnerPipe, level=InnerControl, cchain=x35378).name("x35411").ctrl(x36822).srcCtx("CellsPar.scala:243:62") // UnrolledForeach(List(b22364),x35378,Block(Const(())),List(List(b22898), List(b22899)),List(List(b22900), List(b22901)))
    val b22898 = CounterIter(x35377, Some(0)).name("b22898").ctrl(x35411) // b22898
    val b22900 = Const(true).name("b22900").ctrl(x35411) // b22900
    val b22899 = CounterIter(x35376, None).name("b22899").ctrl(x35411) // b22899
    val b22901 = Const(true).name("b22901").ctrl(x35411) // b22901
    val x35379 = OpDef(op=BitAnd, inputs=List(b22900, b22901)).name("x35379").ctrl(x35411).srcCtx("UnrollingBase.scala:28:66") // And(b22900,b22901)
    val x35380 = OpDef(op=BitAnd, inputs=List(x35379, b22364)).name("x35380").ctrl(x35411).srcCtx("UnrollingBase.scala:28:66") // And(x35379,b22364)
    val x35381 = LoadBanks(List(x34752_d0_b0), List(b22898, b22899)).name("x35381").ctrl(x35411).srcCtx("CellsPar.scala:244:22") // ParSRAMLoad(x34752,List(List(b22898, b22899)),List(x35380))
    val x35382 = x35381 // x35382 = VectorApply(x35381,0)
    val x35383 = LoadBanks(List(x34755_d0_b0), List(b22898, b22899)).name("x35383").ctrl(x35411).srcCtx("CellsPar.scala:244:34") // ParSRAMLoad(x34755,List(List(b22898, b22899)),List(x35380))
    val x35384 = x35383 // x35384 = VectorApply(x35383,0)
    val x35385 = OpDef(op=FixMul, inputs=List(x35382, x35384)).name("x35385").ctrl(x35411).srcCtx("CellsPar.scala:244:28") // FixMul(x35382,x35384)
    val x35386 = LoadBanks(List(x34753_d0_b0), List(b22898, b22899)).name("x35386").ctrl(x35411).srcCtx("CellsPar.scala:244:46") // ParSRAMLoad(x34753,List(List(b22898, b22899)),List(x35380))
    val x35387 = x35386 // x35387 = VectorApply(x35386,0)
    val x35388 = LoadBanks(List(x34754_d0_b0), List(b22898, b22899)).name("x35388").ctrl(x35411).srcCtx("CellsPar.scala:244:59") // ParSRAMLoad(x34754,List(List(b22898, b22899)),List(x35380))
    val x35389 = x35388 // x35389 = VectorApply(x35388,0)
    val x35390 = OpDef(op=FixMul, inputs=List(x35387, x35389)).name("x35390").ctrl(x35411).srcCtx("CellsPar.scala:244:52") // FixMul(x35387,x35389)
    val x35391 = OpDef(op=FixAdd, inputs=List(x35385, x35390)).name("x35391").ctrl(x35411).srcCtx("CellsPar.scala:244:40:new_c") // FixAdd(x35385,x35390)
    val x35392 = x35391 // FixConvert(x35391,TRUE,_8,_24) (Same Type. No op)
    val x35393 = OpDef(op=FixAbs, inputs=List(x35392)).name("x35393").ctrl(x35411).srcCtx("CellsPar.scala:37:19:absp") // FixAbs(x35392)
    val x35394 = OpDef(op=FixLt, inputs=List(Const(2.5), x35393)).name("x35394").ctrl(x35411).srcCtx("CellsPar.scala:51:23:cond1") // FixLt(Const(2.5),x35393)
    val x35395 = OpDef(op=FixLt, inputs=List(Const(0.5), x35393)).name("x35395").ctrl(x35411).srcCtx("CellsPar.scala:54:35") // FixLt(Const(0.5),x35393)
    val x35396 = OpDef(op=FixLeq, inputs=List(x35393, Const(2.5))).name("x35396").ctrl(x35411).srcCtx("CellsPar.scala:54:52") // FixLeq(x35393,Const(2.5))
    val x35397 = OpDef(op=BitAnd, inputs=List(x35395, x35396)).name("x35397").ctrl(x35411).srcCtx("CellsPar.scala:54:43:cond2") // And(x35395,x35396)
    val x35398 = OpDef(op=FixSra, inputs=List(x35393, Const(2))).name("x35398").ctrl(x35411).srcCtx("CellsPar.scala:56:21:div4") // FixRsh(x35393,Const(2))
    val x35399 = OpDef(op=FixAdd, inputs=List(x35398, Const(0.375))).name("x35399").ctrl(x35411).srcCtx("CellsPar.scala:57:27:div4Offset") // FixAdd(x35398,Const(0.375))
    val x35400 = OpDef(op=MuxOp, inputs=List(x35397, x35399, x35393)).name("x35400").ctrl(x35411).srcCtx("CellsPar.scala:58:20:body2") // Mux(x35397,x35399,x35393)
    val x35401 = OpDef(op=MuxOp, inputs=List(x35394, Const(1.0), x35400)).name("x35401").ctrl(x35411).srcCtx("CellsPar.scala:60:20:absre") // Mux(x35394,Const(1),x35400)
    val x35402 = OpDef(op=FixNeg, inputs=List(x35401)).name("x35402").ctrl(x35411).srcCtx("CellsPar.scala:62:29:mabre") // FixNeg(x35401)
    val x35403 = OpDef(op=FixLt, inputs=List(x35392, Const(0.0))).name("x35403").ctrl(x35411).srcCtx("CellsPar.scala:63:22") // FixLt(x35392,Const(0))
    val x35404 = OpDef(op=MuxOp, inputs=List(x35403, x35402, x35401)).name("x35404").ctrl(x35411).srcCtx("CellsPar.scala:63:17:re") // Mux(x35403,x35402,x35401)
    val x35405 = x35404 // FixConvert(x35404,TRUE,_8,_24) (Same Type. No op)
    val x35406 = LoadBanks(List(x34756_d0_b0), List(b22898, b22899)).name("x35406").ctrl(x35411).srcCtx("CellsPar.scala:245:45") // ParSRAMLoad(x34756,List(List(b22898, b22899)),List(x35380))
    val x35407 = x35406 // x35407 = VectorApply(x35406,0)
    val x35408 = OpDef(op=FixMul, inputs=List(x35405, x35407)).name("x35408").ctrl(x35411).srcCtx("CellsPar.scala:245:39") // FixMul(x35405,x35407)
    val x35409 = StoreBanks(List(x34751_d0_b0, x34751_d5_b0, x34751_d1_b0, x34751_d6_b0, x34751_d2_b0, x34751_d7_b0, x34751_d3_b0, x34751_d4_b0), List(b22898, b22899), x35408).name("x35409").ctrl(x35411).srcCtx("CellsPar.scala:245:16") // ParSRAMStore(x34751,List(List(b22898, b22899)),List(x35408),List(x35380))
    val x35410 = StoreBanks(List(x34752_d0_b0), List(b22898, b22899), x35391).name("x35410").ctrl(x35411).srcCtx("CellsPar.scala:246:16") // ParSRAMStore(x34752,List(List(b22898, b22899)),List(x35391),List(x35380))
    val x35412 = Counter(min=Const(0), max=Const(512), step=Const(16), par=1).name("x35412").ctrl(x36822).srcCtx("CellsPar.scala:190:58") // CounterNew(Const(0),Const(512),Const(16),Const(1))
    val x35413 = Counter(min=Const(0), max=Const(1536), step=Const(16), par=1).name("x35413").ctrl(x36822).srcCtx("CellsPar.scala:190:33") // CounterNew(Const(0),Const(1536),Const(16),Const(1))
    val x35414 = CounterChain(List(x35413,x35412)).name("x35414").ctrl(x36822).srcCtx("CellsPar.scala:190:72") // CounterChainNew(List(x35413, x35412))
    val x35519 = LoopController(style=MetaPipe, level=OuterControl, cchain=x35414).name("x35519").ctrl(x36822).srcCtx("CellsPar.scala:190:72") // UnrolledForeach(List(b22364),x35414,Block(Const(())),List(List(b22938), List(b22939)),List(List(b22940), List(b22941)))
    val b22938 = CounterIter(x35413, Some(0)).name("b22938").ctrl(x35519) // b22938
    val b22940 = Const(true).name("b22940").ctrl(x35519) // b22940
    val b22939 = CounterIter(x35412, Some(0)).name("b22939").ctrl(x35519) // b22939
    val b22941 = Const(true).name("b22941").ctrl(x35519) // b22941
    val x35415_d0_b0 = SRAM(size=256, banking=Strided(banks=1, stride=16)).name("x35415_d0_b0").ctrl(x35519).srcCtx("CellsPar.scala:191:33:tileKernel") // x35415 = SRAMNew(ArrayBuffer(Const(16), Const(16)))
    isAccum(x35415_d0_b0) = false
    bufferDepthOf(x35415_d0_b0) = 2
    val x35418 = UnitController(style=SeqPipe, level=InnerControl).name("x35418").ctrl(x35519).srcCtx("CellsPar.scala:190:72") // UnitPipe(List(b22940, b22941, b22364),Block(Const(())))
    val x35416 = OpDef(op=FixAdd, inputs=List(b22938, Const(16))).name("x35416").ctrl(x35418).srcCtx("CellsPar.scala:192:36") // FixAdd(b22938,Const(16))
    val x35417 = OpDef(op=FixAdd, inputs=List(b22939, Const(16))).name("x35417").ctrl(x35418).srcCtx("CellsPar.scala:192:45") // FixAdd(b22939,Const(16))
    val x35419 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x35419").ctrl(x35519).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x35420 = CounterChain(List(x35419)).name("x35420").ctrl(x35519).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x35419))
    val x35449 = LoopController(style=StreamPipe, level=OuterControl, cchain=x35420).name("x35449").ctrl(x35519).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b22940, b22941, b22364),x35420,Block(Const(())),List(List(b22948)),List(List(b22949)))
    val b22948 = CounterIter(x35419, Some(0)).name("b22948").ctrl(x35449) // b22948
    val b22949 = Const(true).name("b22949").ctrl(x35449) // b22949
    val b36925 = StreamOut(field="offset").name("b36925").ctrl(x35449).srcCtx("CellsPar.scala:192:20") // x35421 = StreamOutNew(BurstCmdBus)
    isAccum(b36925) = false
    bufferDepthOf(b36925) = 1
    val b36926 = StreamOut(field="size").name("b36926").ctrl(x35449).srcCtx("CellsPar.scala:192:20") // x35421 = StreamOutNew(BurstCmdBus)
    isAccum(b36926) = false
    bufferDepthOf(b36926) = 1
    val x35422 = StreamIn(field="data").name("x35422").ctrl(x35449).srcCtx("CellsPar.scala:192:20") // x35422 = StreamInNew(BurstDataBus())
    isAccum(x35422) = false
    bufferDepthOf(x35422) = 1
    val x35437 = UnitController(style=SeqPipe, level=InnerControl).name("x35437").ctrl(x35449).srcCtx("CellsPar.scala:192:20") // UnitPipe(List(b22949, b22940, b22941, b22364),Block(x35436))
    val x35423 = OpDef(op=FixAdd, inputs=List(b22938, b22948)).name("x35423").ctrl(x35437).srcCtx("CellsPar.scala:192:20") // FixAdd(b22938,b22948)
    val x35424 = x35423 // FixConvert(x35423,TRUE,_32,_0) (Same Type. No op)
    val x35425 = OpDef(op=FixSla, inputs=List(x35424, Const(9))).name("x35425").ctrl(x35437).srcCtx("CellsPar.scala:192:20") // FixLsh(x35424,Const(9))
    val x35426 = b22939 // FixConvert(b22939,TRUE,_32,_0) (Same Type. No op)
    val x35427 = OpDef(op=FixAdd, inputs=List(x35425, x35426)).name("x35427").ctrl(x35437).srcCtx("CellsPar.scala:192:20") // FixAdd(x35425,x35426)
    val x35428 = OpDef(op=FixSla, inputs=List(x35427, Const(2))).name("x35428").ctrl(x35437).srcCtx("CellsPar.scala:192:20") // FixLsh(x35427,Const(2))
    val x35429 = x35428 // FixConvert(x35428,TRUE,_64,_0)
    val x35430 = DramAddress(x34550).name("x35430").ctrl(x35437).srcCtx("CellsPar.scala:192:20") // GetDRAMAddress(x34550)
    val x35432_x35431 = OpDef(op=FixAdd, inputs=List(x35429, x35430)).name("x35432_x35431").ctrl(x35437).srcCtx("CellsPar.scala:192:20") // FixAdd(x35429,x35430)
    // x35432 = SimpleStruct(ArrayBuffer((offset,x35431), (size,Const(64)), (isLoad,Const(true))))
    val x35433 = OpDef(op=BitAnd, inputs=List(b22949, b22940)).name("x35433").ctrl(x35437).srcCtx("UnrollingBase.scala:28:66") // And(b22949,b22940)
    val x35434 = OpDef(op=BitAnd, inputs=List(b22941, b22364)).name("x35434").ctrl(x35437).srcCtx("UnrollingBase.scala:28:66") // And(b22941,b22364)
    val x35435 = OpDef(op=BitAnd, inputs=List(x35433, x35434)).name("x35435").ctrl(x35437).srcCtx("UnrollingBase.scala:28:66") // And(x35433,x35434)
    val x35436_b36927_b36925 = WriteMem(b36925, x35432_x35431).name("x35436_b36927_b36925").ctrl(x35437).srcCtx("CellsPar.scala:192:20") // StreamWrite(x35421,x35432,x35435)
    val x35436_b36928_b36926 = WriteMem(b36926, Const(64)).name("x35436_b36928_b36926").ctrl(x35437).srcCtx("CellsPar.scala:192:20") // StreamWrite(x35421,x35432,x35435)
    val x35438 = FringeDenseLoad(dram=List(x34550), cmdStream=List(b36925, b36926), dataStream=List(x35422)).name("x35438").ctrl(x35449).srcCtx("CellsPar.scala:192:20") // FringeDenseLoad(x34550,x35421,x35422)
    val x35439 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x35439").ctrl(x35449).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x35440 = CounterChain(List(x35439)).name("x35440").ctrl(x35449).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x35439))
    val x35448 = LoopController(style=InnerPipe, level=InnerControl, cchain=x35440).name("x35448").ctrl(x35449).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b22949, b22940, b22941, b22364),x35440,Block(Const(())),List(List(b22970)),List(List(b22971)))
    val b22970 = CounterIter(x35439, None).name("b22970").ctrl(x35448) // b22970
    val b22971 = Const(true).name("b22971").ctrl(x35448) // b22971
    val x35441 = OpDef(op=BitAnd, inputs=List(b22971, b22949)).name("x35441").ctrl(x35448).srcCtx("UnrollingBase.scala:28:66") // And(b22971,b22949)
    val x35442 = OpDef(op=BitAnd, inputs=List(b22940, b22941)).name("x35442").ctrl(x35448).srcCtx("UnrollingBase.scala:28:66") // And(b22940,b22941)
    val x35443 = OpDef(op=BitAnd, inputs=List(x35441, x35442)).name("x35443").ctrl(x35448).srcCtx("UnrollingBase.scala:28:66") // And(x35441,x35442)
    val x35444 = OpDef(op=BitAnd, inputs=List(x35443, b22364)).name("x35444").ctrl(x35448).srcCtx("UnrollingBase.scala:28:66") // And(x35443,b22364)
    val x35445_x35445 = ReadMem(x35422).name("x35445_x35445").ctrl(x35448).srcCtx("CellsPar.scala:192:20") // ParStreamRead(x35422,List(x35444))
    val x35446_x35446 = x35445_x35445 // x35446 = VectorApply(x35445,0)
    val x35447 = StoreBanks(List(x35415_d0_b0), List(b22948, b22970), x35446_x35446).name("x35447").ctrl(x35448).srcCtx("CellsPar.scala:192:20") // ParSRAMStore(x35415,List(List(b22948, b22970)),List(x35446),List(x35444))
    val x35450 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x35450").ctrl(x35519).srcCtx("CellsPar.scala:193:34") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x35451 = CounterChain(List(x35450)).name("x35451").ctrl(x35519).srcCtx("CellsPar.scala:193:46") // CounterChainNew(List(x35450))
    val x35518 = LoopController(style=MetaPipe, level=OuterControl, cchain=x35451).name("x35518").ctrl(x35519).srcCtx("CellsPar.scala:193:46") // UnrolledForeach(List(b22940, b22941, b22364),x35451,Block(Const(())),List(List(b22983)),List(List(b22984)))
    val b22983 = CounterIter(x35450, Some(0)).name("b22983").ctrl(x35518) // b22983
    val b22984 = Const(true).name("b22984").ctrl(x35518) // b22984
    val x35452 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x35452").ctrl(x35518).srcCtx("CellsPar.scala:194:28") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x35453 = CounterChain(List(x35452)).name("x35453").ctrl(x35518).srcCtx("CellsPar.scala:194:42") // CounterChainNew(List(x35452))
    val x35517 = LoopController(style=MetaPipe, level=OuterControl, cchain=x35453).name("x35517").ctrl(x35518).srcCtx("CellsPar.scala:194:42") // UnrolledForeach(List(b22984, b22940, b22941, b22364),x35453,Block(Const(())),List(List(b22987)),List(List(b22988)))
    val b22987 = CounterIter(x35452, Some(0)).name("b22987").ctrl(x35517) // b22987
    val b22988 = Const(true).name("b22988").ctrl(x35517) // b22988
    val x35454_d0 = Reg(init=Some(0.0)).name("x35454_d0").ctrl(x35517).srcCtx("CellsPar.scala:195:34:prod") // x35454 = RegNew(Const(0))
    isAccum(x35454_d0) = false
    bufferDepthOf(x35454_d0) = 2
    val x35454_d1 = Reg(init=Some(0.0)).name("x35454_d1").ctrl(x35517).srcCtx("CellsPar.scala:195:34:prod") // x35454 = RegNew(Const(0))
    isAccum(x35454_d1) = true
    bufferDepthOf(x35454_d1) = 1
    val x35455 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x35455").ctrl(x35517).srcCtx("CellsPar.scala:195:43") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x35456 = CounterChain(List(x35455)).name("x35456").ctrl(x35517).srcCtx("CellsPar.scala:207:15") // CounterChainNew(List(x35455))
    val x35482 = LoopController(style=InnerPipe, level=InnerControl, cchain=x35456).name("x35482").ctrl(x35517).srcCtx("CellsPar.scala:207:15") // UnrolledReduce(List(b22988, b22984, b22940, b22941, b22364),x35456,x35454,Block((x35454) => Const(())),List(List(b22992)),List(List(b22993)))
    val b22992 = CounterIter(x35455, None).name("b22992").ctrl(x35482) // b22992
    val b22993 = Const(true).name("b22993").ctrl(x35482) // b22993
    val x35457 = OpDef(op=FixAdd, inputs=List(b22938, b22992)).name("x35457").ctrl(x35482).srcCtx("CellsPar.scala:196:42:reduce_size_offset") // FixAdd(b22938,b22992)
    val x35458 = OpDef(op=BitAnd, inputs=List(b22993, b22988)).name("x35458").ctrl(x35482).srcCtx("UnrollingBase.scala:28:66") // And(b22993,b22988)
    val x35459 = OpDef(op=BitAnd, inputs=List(b22984, b22940)).name("x35459").ctrl(x35482).srcCtx("UnrollingBase.scala:28:66") // And(b22984,b22940)
    val x35460 = OpDef(op=BitAnd, inputs=List(b22941, b22364)).name("x35460").ctrl(x35482).srcCtx("UnrollingBase.scala:28:66") // And(b22941,b22364)
    val x35461 = OpDef(op=BitAnd, inputs=List(x35458, x35459)).name("x35461").ctrl(x35482).srcCtx("UnrollingBase.scala:28:66") // And(x35458,x35459)
    val x35462 = OpDef(op=BitAnd, inputs=List(x35461, x35460)).name("x35462").ctrl(x35482).srcCtx("UnrollingBase.scala:28:66") // And(x35461,x35460)
    val x35463 = LoadBanks(List(x35415_d0_b0), List(b22992, b22987)).name("x35463").ctrl(x35482).srcCtx("CellsPar.scala:203:34:tk") // ParSRAMLoad(x35415,List(List(b22992, b22987)),List(x35462))
    val x35464 = x35463 // x35464 = VectorApply(x35463,0)
    val x35465 = LoadBanks(List(x34751_d3_b0), List(b22983, x35457)).name("x35465").ctrl(x35482).srcCtx("CellsPar.scala:204:28") // ParSRAMLoad(x34751,List(List(b22983, x35457)),List(x35462))
    val x35466 = x35465 // x35466 = VectorApply(x35465,0)
    val x35467 = OpDef(op=FixMul, inputs=List(x35466, x35464)).name("x35467").ctrl(x35482).srcCtx("CellsPar.scala:204:52:tempA") // FixMul(x35466,x35464)
    val x35468 = OpDef(op=FixSub, inputs=List(x35457, Const(512))).name("x35468").ctrl(x35482).srcCtx("CellsPar.scala:205:51") // FixSub(x35457,Const(512))
    val x35469 = LoadBanks(List(x34758_d8_b0), List(b22983, x35468)).name("x35469").ctrl(x35482).srcCtx("CellsPar.scala:205:28") // ParSRAMLoad(x34758,List(List(b22983, x35468)),List(x35462))
    val x35470 = x35469 // x35470 = VectorApply(x35469,0)
    val x35471 = OpDef(op=FixMul, inputs=List(x35470, x35464)).name("x35471").ctrl(x35482).srcCtx("CellsPar.scala:205:67:tempB") // FixMul(x35470,x35464)
    val x35472 = OpDef(op=FixLt, inputs=List(x35457, Const(512))).name("x35472").ctrl(x35482).srcCtx("CellsPar.scala:206:38") // FixLt(x35457,Const(512))
    val x35473 = OpDef(op=MuxOp, inputs=List(x35472, x35467, x35471)).name("x35473").ctrl(x35482).srcCtx("CellsPar.scala:206:18") // Mux(x35472,x35467,x35471)
    val x35474 = ReadMem(x35454_d1).name("x35474").ctrl(x35482).srcCtx("CellsPar.scala:207:15") // RegRead(x35454)
    val x35475 = OpDef(op=FixEql, inputs=List(b22992, Const(0))).name("x35475").ctrl(x35482).srcCtx("CellsPar.scala:207:15") // FixEql(b22992,Const(0))
    val x35476 = ReduceAccumOp(op=FixAdd, input=x35473, accum=x35474).name("x35476").ctrl(x35482).srcCtx("CellsPar.scala:207:17") // FixAdd(x35473,x35474)
    val x35477 = OpDef(op=BitAnd, inputs=List(b22988, b22984)).name("x35477").ctrl(x35482).srcCtx("UnrollingBase.scala:28:66") // And(b22988,b22984)
    val x35478 = OpDef(op=BitAnd, inputs=List(b22940, b22941)).name("x35478").ctrl(x35482).srcCtx("UnrollingBase.scala:28:66") // And(b22940,b22941)
    val x35479 = OpDef(op=BitAnd, inputs=List(x35477, x35478)).name("x35479").ctrl(x35482).srcCtx("UnrollingBase.scala:28:66") // And(x35477,x35478)
    val x35480 = OpDef(op=BitAnd, inputs=List(x35479, b22364)).name("x35480").ctrl(x35482).srcCtx("UnrollingBase.scala:28:66") // And(x35479,b22364)
    val x35481_x35454_d0 = WriteMem(x35454_d0, x35476).name("x35481_x35454_d0").ctrl(x35482).srcCtx("CellsPar.scala:207:15") // RegWrite(x35454,x35476,x35480)
    val x35481_x35454_d1 = WriteMem(x35454_d1, x35476).name("x35481_x35454_d1").ctrl(x35482).srcCtx("CellsPar.scala:207:15") // RegWrite(x35454,x35476,x35480)
    val x35516 = UnitController(style=SeqPipe, level=InnerControl).name("x35516").ctrl(x35517).srcCtx("CellsPar.scala:194:42") // UnitPipe(List(b22988, b22984, b22940, b22941, b22364),Block(Const(())))
    val x35483 = OpDef(op=FixAdd, inputs=List(b22939, b22987)).name("x35483").ctrl(x35516).srcCtx("CellsPar.scala:209:31:colOffset") // FixAdd(b22939,b22987)
    val x35484 = ReadMem(x35454_d0).name("x35484").ctrl(x35516).srcCtx("CellsPar.scala:210:28") // RegRead(x35454)
    val x35485 = OpDef(op=FixEql, inputs=List(b22938, Const(0))).name("x35485").ctrl(x35516).srcCtx("CellsPar.scala:210:42") // FixEql(b22938,Const(0))
    val x35486 = OpDef(op=BitAnd, inputs=List(b22988, b22984)).name("x35486").ctrl(x35516).srcCtx("UnrollingBase.scala:28:66") // And(b22988,b22984)
    val x35487 = OpDef(op=BitAnd, inputs=List(b22940, b22941)).name("x35487").ctrl(x35516).srcCtx("UnrollingBase.scala:28:66") // And(b22940,b22941)
    val x35488 = OpDef(op=BitAnd, inputs=List(x35486, x35487)).name("x35488").ctrl(x35516).srcCtx("UnrollingBase.scala:28:66") // And(x35486,x35487)
    val x35489 = OpDef(op=BitAnd, inputs=List(x35488, b22364)).name("x35489").ctrl(x35516).srcCtx("UnrollingBase.scala:28:66") // And(x35488,b22364)
    val x35490 = LoadBanks(List(x34764_d3_b0), List(Const(0), x35483)).name("x35490").ctrl(x35516).srcCtx("CellsPar.scala:210:52") // SRAMLoad(x34764,ArrayBuffer(Const(1), Const(2048)),List(Const(0), x35483),Const(0),x35489)
    val x35491 = LoadBanks(List(x34760_d1_b0), List(b22983, x35483)).name("x35491").ctrl(x35516).srcCtx("CellsPar.scala:210:91") // SRAMLoad(x34760,ArrayBuffer(Const(1), Const(512)),List(b22983, x35483),Const(0),x35489)
    val x35492 = OpDef(op=MuxOp, inputs=List(x35485, x35490, x35491)).name("x35492").ctrl(x35516).srcCtx("CellsPar.scala:210:39") // Mux(x35485,x35490,x35491)
    val x35493 = OpDef(op=FixAdd, inputs=List(x35484, x35492)).name("x35493").ctrl(x35516).srcCtx("CellsPar.scala:210:34:ele") // FixAdd(x35484,x35492)
    val x35494 = OpDef(op=FixLeq, inputs=List(Const(1520), b22938)).name("x35494").ctrl(x35516).srcCtx("CellsPar.scala:218:46") // FixLeq(Const(1520),b22938)
    // x35495 = FixConvert(Const(0),TRUE,_8,_24) x.tp=FixPt[TRUE,_32,_0] {
    val x35495_int1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("01111111111111111111111111111111"))).name("x35495_int1").ctrl(x35516).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x35495_int2 = OpDef(op=FixSla, inputs=List(x35495_int1, Const(24))).name("x35495_int2").ctrl(x35516).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x35495_frac1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("00000000000000000000000000000000"))).name("x35495_frac1").ctrl(x35516).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x35495_frac2 = OpDef(op=FixSla, inputs=List(x35495_frac1, Const(24))).name("x35495_frac2").ctrl(x35516).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x35495 = OpDef(op=BitOr, inputs=List(x35495_int2, x35495_frac2)).name("x35495").ctrl(x35516).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    // }
    val x35496 = OpDef(op=FixAdd, inputs=List(x35493, x35495)).name("x35496").ctrl(x35516).srcCtx("CellsPar.scala:218:87") // FixAdd(x35493,x35495)
    val x35497 = OpDef(op=FixSra, inputs=List(x35496, Const(1))).name("x35497").ctrl(x35516).srcCtx("CellsPar.scala:29:22") // FixRsh(x35496,Const(1))
    val x35498 = x35497 // FixConvert(x35497,TRUE,_8,_24) (Same Type. No op)
    val x35499 = OpDef(op=FixAbs, inputs=List(x35498)).name("x35499").ctrl(x35516).srcCtx("CellsPar.scala:37:19:absp") // FixAbs(x35498)
    val x35500 = OpDef(op=FixLt, inputs=List(Const(2.5), x35499)).name("x35500").ctrl(x35516).srcCtx("CellsPar.scala:51:23:cond1") // FixLt(Const(2.5),x35499)
    val x35501 = OpDef(op=FixLt, inputs=List(Const(0.5), x35499)).name("x35501").ctrl(x35516).srcCtx("CellsPar.scala:54:35") // FixLt(Const(0.5),x35499)
    val x35502 = OpDef(op=FixLeq, inputs=List(x35499, Const(2.5))).name("x35502").ctrl(x35516).srcCtx("CellsPar.scala:54:52") // FixLeq(x35499,Const(2.5))
    val x35503 = OpDef(op=BitAnd, inputs=List(x35501, x35502)).name("x35503").ctrl(x35516).srcCtx("CellsPar.scala:54:43:cond2") // And(x35501,x35502)
    val x35504 = OpDef(op=FixSra, inputs=List(x35499, Const(2))).name("x35504").ctrl(x35516).srcCtx("CellsPar.scala:56:21:div4") // FixRsh(x35499,Const(2))
    val x35505 = OpDef(op=FixAdd, inputs=List(x35504, Const(0.375))).name("x35505").ctrl(x35516).srcCtx("CellsPar.scala:57:27:div4Offset") // FixAdd(x35504,Const(0.375))
    val x35506 = OpDef(op=MuxOp, inputs=List(x35503, x35505, x35499)).name("x35506").ctrl(x35516).srcCtx("CellsPar.scala:58:20:body2") // Mux(x35503,x35505,x35499)
    val x35507 = OpDef(op=MuxOp, inputs=List(x35500, Const(1.0), x35506)).name("x35507").ctrl(x35516).srcCtx("CellsPar.scala:60:20:absre") // Mux(x35500,Const(1),x35506)
    val x35508 = OpDef(op=FixNeg, inputs=List(x35507)).name("x35508").ctrl(x35516).srcCtx("CellsPar.scala:62:29:mabre") // FixNeg(x35507)
    val x35509 = OpDef(op=FixLt, inputs=List(x35498, Const(0.0))).name("x35509").ctrl(x35516).srcCtx("CellsPar.scala:63:22") // FixLt(x35498,Const(0))
    val x35510 = OpDef(op=MuxOp, inputs=List(x35509, x35508, x35507)).name("x35510").ctrl(x35516).srcCtx("CellsPar.scala:63:17:re") // Mux(x35509,x35508,x35507)
    val x35511 = x35510 // FixConvert(x35510,TRUE,_8,_24) (Same Type. No op)
    val x35512 = OpDef(op=FixAdd, inputs=List(x35511, Const(1.0))).name("x35512").ctrl(x35516).srcCtx("CellsPar.scala:29:33") // FixAdd(x35511,Const(1))
    val x35513 = OpDef(op=FixSra, inputs=List(x35512, Const(1))).name("x35513").ctrl(x35516).srcCtx("CellsPar.scala:29:44") // FixRsh(x35512,Const(1))
    val x35514 = OpDef(op=MuxOp, inputs=List(x35494, x35513, x35493)).name("x35514").ctrl(x35516).srcCtx("CellsPar.scala:218:43") // Mux(x35494,x35513,x35493)
    val x35515 = StoreBanks(List(x34760_d0_b0, x34760_d1_b0), List(b22983, x35483), x35514).name("x35515").ctrl(x35516).srcCtx("CellsPar.scala:218:38") // SRAMStore(x34760,ArrayBuffer(Const(1), Const(512)),List(b22983, x35483),Const(0),x35514,x35489)
    val x35520 = Counter(min=Const(0), max=Const(512), step=Const(16), par=1).name("x35520").ctrl(x36822).srcCtx("CellsPar.scala:190:58") // CounterNew(Const(0),Const(512),Const(16),Const(1))
    val x35521 = Counter(min=Const(0), max=Const(1536), step=Const(16), par=1).name("x35521").ctrl(x36822).srcCtx("CellsPar.scala:190:33") // CounterNew(Const(0),Const(1536),Const(16),Const(1))
    val x35522 = CounterChain(List(x35521,x35520)).name("x35522").ctrl(x36822).srcCtx("CellsPar.scala:190:72") // CounterChainNew(List(x35521, x35520))
    val x35625 = LoopController(style=MetaPipe, level=OuterControl, cchain=x35522).name("x35625").ctrl(x36822).srcCtx("CellsPar.scala:190:72") // UnrolledForeach(List(b22364),x35522,Block(Const(())),List(List(b23060), List(b23061)),List(List(b23062), List(b23063)))
    val b23060 = CounterIter(x35521, Some(0)).name("b23060").ctrl(x35625) // b23060
    val b23062 = Const(true).name("b23062").ctrl(x35625) // b23062
    val b23061 = CounterIter(x35520, Some(0)).name("b23061").ctrl(x35625) // b23061
    val b23063 = Const(true).name("b23063").ctrl(x35625) // b23063
    val x35523_d0_b0 = SRAM(size=256, banking=Strided(banks=1, stride=16)).name("x35523_d0_b0").ctrl(x35625).srcCtx("CellsPar.scala:191:33:tileKernel") // x35523 = SRAMNew(ArrayBuffer(Const(16), Const(16)))
    isAccum(x35523_d0_b0) = false
    bufferDepthOf(x35523_d0_b0) = 2
    val x35526 = UnitController(style=SeqPipe, level=InnerControl).name("x35526").ctrl(x35625).srcCtx("CellsPar.scala:190:72") // UnitPipe(List(b23062, b23063, b22364),Block(Const(())))
    val x35524 = OpDef(op=FixAdd, inputs=List(b23060, Const(16))).name("x35524").ctrl(x35526).srcCtx("CellsPar.scala:192:36") // FixAdd(b23060,Const(16))
    val x35525 = OpDef(op=FixAdd, inputs=List(b23061, Const(16))).name("x35525").ctrl(x35526).srcCtx("CellsPar.scala:192:45") // FixAdd(b23061,Const(16))
    val x35527 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x35527").ctrl(x35625).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x35528 = CounterChain(List(x35527)).name("x35528").ctrl(x35625).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x35527))
    val x35557 = LoopController(style=StreamPipe, level=OuterControl, cchain=x35528).name("x35557").ctrl(x35625).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b23062, b23063, b22364),x35528,Block(Const(())),List(List(b23070)),List(List(b23071)))
    val b23070 = CounterIter(x35527, Some(0)).name("b23070").ctrl(x35557) // b23070
    val b23071 = Const(true).name("b23071").ctrl(x35557) // b23071
    val b36929 = StreamOut(field="offset").name("b36929").ctrl(x35557).srcCtx("CellsPar.scala:192:20") // x35529 = StreamOutNew(BurstCmdBus)
    isAccum(b36929) = false
    bufferDepthOf(b36929) = 1
    val b36930 = StreamOut(field="size").name("b36930").ctrl(x35557).srcCtx("CellsPar.scala:192:20") // x35529 = StreamOutNew(BurstCmdBus)
    isAccum(b36930) = false
    bufferDepthOf(b36930) = 1
    val x35530 = StreamIn(field="data").name("x35530").ctrl(x35557).srcCtx("CellsPar.scala:192:20") // x35530 = StreamInNew(BurstDataBus())
    isAccum(x35530) = false
    bufferDepthOf(x35530) = 1
    val x35545 = UnitController(style=SeqPipe, level=InnerControl).name("x35545").ctrl(x35557).srcCtx("CellsPar.scala:192:20") // UnitPipe(List(b23071, b23062, b23063, b22364),Block(x35544))
    val x35531 = OpDef(op=FixAdd, inputs=List(b23060, b23070)).name("x35531").ctrl(x35545).srcCtx("CellsPar.scala:192:20") // FixAdd(b23060,b23070)
    val x35532 = x35531 // FixConvert(x35531,TRUE,_32,_0) (Same Type. No op)
    val x35533 = OpDef(op=FixSla, inputs=List(x35532, Const(9))).name("x35533").ctrl(x35545).srcCtx("CellsPar.scala:192:20") // FixLsh(x35532,Const(9))
    val x35534 = b23061 // FixConvert(b23061,TRUE,_32,_0) (Same Type. No op)
    val x35535 = OpDef(op=FixAdd, inputs=List(x35533, x35534)).name("x35535").ctrl(x35545).srcCtx("CellsPar.scala:192:20") // FixAdd(x35533,x35534)
    val x35536 = OpDef(op=FixSla, inputs=List(x35535, Const(2))).name("x35536").ctrl(x35545).srcCtx("CellsPar.scala:192:20") // FixLsh(x35535,Const(2))
    val x35537 = x35536 // FixConvert(x35536,TRUE,_64,_0)
    val x35538 = DramAddress(x34551).name("x35538").ctrl(x35545).srcCtx("CellsPar.scala:192:20") // GetDRAMAddress(x34551)
    val x35540_x35539 = OpDef(op=FixAdd, inputs=List(x35537, x35538)).name("x35540_x35539").ctrl(x35545).srcCtx("CellsPar.scala:192:20") // FixAdd(x35537,x35538)
    // x35540 = SimpleStruct(ArrayBuffer((offset,x35539), (size,Const(64)), (isLoad,Const(true))))
    val x35541 = OpDef(op=BitAnd, inputs=List(b23071, b23062)).name("x35541").ctrl(x35545).srcCtx("UnrollingBase.scala:28:66") // And(b23071,b23062)
    val x35542 = OpDef(op=BitAnd, inputs=List(b23063, b22364)).name("x35542").ctrl(x35545).srcCtx("UnrollingBase.scala:28:66") // And(b23063,b22364)
    val x35543 = OpDef(op=BitAnd, inputs=List(x35541, x35542)).name("x35543").ctrl(x35545).srcCtx("UnrollingBase.scala:28:66") // And(x35541,x35542)
    val x35544_b36931_b36929 = WriteMem(b36929, x35540_x35539).name("x35544_b36931_b36929").ctrl(x35545).srcCtx("CellsPar.scala:192:20") // StreamWrite(x35529,x35540,x35543)
    val x35544_b36932_b36930 = WriteMem(b36930, Const(64)).name("x35544_b36932_b36930").ctrl(x35545).srcCtx("CellsPar.scala:192:20") // StreamWrite(x35529,x35540,x35543)
    val x35546 = FringeDenseLoad(dram=List(x34551), cmdStream=List(b36929, b36930), dataStream=List(x35530)).name("x35546").ctrl(x35557).srcCtx("CellsPar.scala:192:20") // FringeDenseLoad(x34551,x35529,x35530)
    val x35547 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x35547").ctrl(x35557).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x35548 = CounterChain(List(x35547)).name("x35548").ctrl(x35557).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x35547))
    val x35556 = LoopController(style=InnerPipe, level=InnerControl, cchain=x35548).name("x35556").ctrl(x35557).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b23071, b23062, b23063, b22364),x35548,Block(Const(())),List(List(b23092)),List(List(b23093)))
    val b23092 = CounterIter(x35547, None).name("b23092").ctrl(x35556) // b23092
    val b23093 = Const(true).name("b23093").ctrl(x35556) // b23093
    val x35549 = OpDef(op=BitAnd, inputs=List(b23093, b23071)).name("x35549").ctrl(x35556).srcCtx("UnrollingBase.scala:28:66") // And(b23093,b23071)
    val x35550 = OpDef(op=BitAnd, inputs=List(b23062, b23063)).name("x35550").ctrl(x35556).srcCtx("UnrollingBase.scala:28:66") // And(b23062,b23063)
    val x35551 = OpDef(op=BitAnd, inputs=List(x35549, x35550)).name("x35551").ctrl(x35556).srcCtx("UnrollingBase.scala:28:66") // And(x35549,x35550)
    val x35552 = OpDef(op=BitAnd, inputs=List(x35551, b22364)).name("x35552").ctrl(x35556).srcCtx("UnrollingBase.scala:28:66") // And(x35551,b22364)
    val x35553_x35553 = ReadMem(x35530).name("x35553_x35553").ctrl(x35556).srcCtx("CellsPar.scala:192:20") // ParStreamRead(x35530,List(x35552))
    val x35554_x35554 = x35553_x35553 // x35554 = VectorApply(x35553,0)
    val x35555 = StoreBanks(List(x35523_d0_b0), List(b23070, b23092), x35554_x35554).name("x35555").ctrl(x35556).srcCtx("CellsPar.scala:192:20") // ParSRAMStore(x35523,List(List(b23070, b23092)),List(x35554),List(x35552))
    val x35558 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x35558").ctrl(x35625).srcCtx("CellsPar.scala:193:34") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x35559 = CounterChain(List(x35558)).name("x35559").ctrl(x35625).srcCtx("CellsPar.scala:193:46") // CounterChainNew(List(x35558))
    val x35624 = LoopController(style=MetaPipe, level=OuterControl, cchain=x35559).name("x35624").ctrl(x35625).srcCtx("CellsPar.scala:193:46") // UnrolledForeach(List(b23062, b23063, b22364),x35559,Block(Const(())),List(List(b23105)),List(List(b23106)))
    val b23105 = CounterIter(x35558, Some(0)).name("b23105").ctrl(x35624) // b23105
    val b23106 = Const(true).name("b23106").ctrl(x35624) // b23106
    val x35560 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x35560").ctrl(x35624).srcCtx("CellsPar.scala:194:28") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x35561 = CounterChain(List(x35560)).name("x35561").ctrl(x35624).srcCtx("CellsPar.scala:194:42") // CounterChainNew(List(x35560))
    val x35623 = LoopController(style=MetaPipe, level=OuterControl, cchain=x35561).name("x35623").ctrl(x35624).srcCtx("CellsPar.scala:194:42") // UnrolledForeach(List(b23106, b23062, b23063, b22364),x35561,Block(Const(())),List(List(b23109)),List(List(b23110)))
    val b23109 = CounterIter(x35560, Some(0)).name("b23109").ctrl(x35623) // b23109
    val b23110 = Const(true).name("b23110").ctrl(x35623) // b23110
    val x35562_d0 = Reg(init=Some(0.0)).name("x35562_d0").ctrl(x35623).srcCtx("CellsPar.scala:195:34:prod") // x35562 = RegNew(Const(0))
    isAccum(x35562_d0) = false
    bufferDepthOf(x35562_d0) = 2
    val x35562_d1 = Reg(init=Some(0.0)).name("x35562_d1").ctrl(x35623).srcCtx("CellsPar.scala:195:34:prod") // x35562 = RegNew(Const(0))
    isAccum(x35562_d1) = true
    bufferDepthOf(x35562_d1) = 1
    val x35563 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x35563").ctrl(x35623).srcCtx("CellsPar.scala:195:43") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x35564 = CounterChain(List(x35563)).name("x35564").ctrl(x35623).srcCtx("CellsPar.scala:207:15") // CounterChainNew(List(x35563))
    val x35590 = LoopController(style=InnerPipe, level=InnerControl, cchain=x35564).name("x35590").ctrl(x35623).srcCtx("CellsPar.scala:207:15") // UnrolledReduce(List(b23110, b23106, b23062, b23063, b22364),x35564,x35562,Block((x35562) => Const(())),List(List(b23114)),List(List(b23115)))
    val b23114 = CounterIter(x35563, None).name("b23114").ctrl(x35590) // b23114
    val b23115 = Const(true).name("b23115").ctrl(x35590) // b23115
    val x35565 = OpDef(op=FixAdd, inputs=List(b23060, b23114)).name("x35565").ctrl(x35590).srcCtx("CellsPar.scala:196:42:reduce_size_offset") // FixAdd(b23060,b23114)
    val x35566 = OpDef(op=BitAnd, inputs=List(b23115, b23110)).name("x35566").ctrl(x35590).srcCtx("UnrollingBase.scala:28:66") // And(b23115,b23110)
    val x35567 = OpDef(op=BitAnd, inputs=List(b23106, b23062)).name("x35567").ctrl(x35590).srcCtx("UnrollingBase.scala:28:66") // And(b23106,b23062)
    val x35568 = OpDef(op=BitAnd, inputs=List(b23063, b22364)).name("x35568").ctrl(x35590).srcCtx("UnrollingBase.scala:28:66") // And(b23063,b22364)
    val x35569 = OpDef(op=BitAnd, inputs=List(x35566, x35567)).name("x35569").ctrl(x35590).srcCtx("UnrollingBase.scala:28:66") // And(x35566,x35567)
    val x35570 = OpDef(op=BitAnd, inputs=List(x35569, x35568)).name("x35570").ctrl(x35590).srcCtx("UnrollingBase.scala:28:66") // And(x35569,x35568)
    val x35571 = LoadBanks(List(x35523_d0_b0), List(b23114, b23109)).name("x35571").ctrl(x35590).srcCtx("CellsPar.scala:203:34:tk") // ParSRAMLoad(x35523,List(List(b23114, b23109)),List(x35570))
    val x35572 = x35571 // x35572 = VectorApply(x35571,0)
    val x35573 = LoadBanks(List(x34751_d2_b0), List(b23105, x35565)).name("x35573").ctrl(x35590).srcCtx("CellsPar.scala:204:28") // ParSRAMLoad(x34751,List(List(b23105, x35565)),List(x35570))
    val x35574 = x35573 // x35574 = VectorApply(x35573,0)
    val x35575 = OpDef(op=FixMul, inputs=List(x35574, x35572)).name("x35575").ctrl(x35590).srcCtx("CellsPar.scala:204:52:tempA") // FixMul(x35574,x35572)
    val x35576 = OpDef(op=FixSub, inputs=List(x35565, Const(512))).name("x35576").ctrl(x35590).srcCtx("CellsPar.scala:205:51") // FixSub(x35565,Const(512))
    val x35577 = LoadBanks(List(x34758_d7_b0), List(b23105, x35576)).name("x35577").ctrl(x35590).srcCtx("CellsPar.scala:205:28") // ParSRAMLoad(x34758,List(List(b23105, x35576)),List(x35570))
    val x35578 = x35577 // x35578 = VectorApply(x35577,0)
    val x35579 = OpDef(op=FixMul, inputs=List(x35578, x35572)).name("x35579").ctrl(x35590).srcCtx("CellsPar.scala:205:67:tempB") // FixMul(x35578,x35572)
    val x35580 = OpDef(op=FixLt, inputs=List(x35565, Const(512))).name("x35580").ctrl(x35590).srcCtx("CellsPar.scala:206:38") // FixLt(x35565,Const(512))
    val x35581 = OpDef(op=MuxOp, inputs=List(x35580, x35575, x35579)).name("x35581").ctrl(x35590).srcCtx("CellsPar.scala:206:18") // Mux(x35580,x35575,x35579)
    val x35582 = ReadMem(x35562_d1).name("x35582").ctrl(x35590).srcCtx("CellsPar.scala:207:15") // RegRead(x35562)
    val x35583 = OpDef(op=FixEql, inputs=List(b23114, Const(0))).name("x35583").ctrl(x35590).srcCtx("CellsPar.scala:207:15") // FixEql(b23114,Const(0))
    val x35584 = ReduceAccumOp(op=FixAdd, input=x35581, accum=x35582).name("x35584").ctrl(x35590).srcCtx("CellsPar.scala:207:17") // FixAdd(x35581,x35582)
    val x35585 = OpDef(op=BitAnd, inputs=List(b23110, b23106)).name("x35585").ctrl(x35590).srcCtx("UnrollingBase.scala:28:66") // And(b23110,b23106)
    val x35586 = OpDef(op=BitAnd, inputs=List(b23062, b23063)).name("x35586").ctrl(x35590).srcCtx("UnrollingBase.scala:28:66") // And(b23062,b23063)
    val x35587 = OpDef(op=BitAnd, inputs=List(x35585, x35586)).name("x35587").ctrl(x35590).srcCtx("UnrollingBase.scala:28:66") // And(x35585,x35586)
    val x35588 = OpDef(op=BitAnd, inputs=List(x35587, b22364)).name("x35588").ctrl(x35590).srcCtx("UnrollingBase.scala:28:66") // And(x35587,b22364)
    val x35589_x35562_d0 = WriteMem(x35562_d0, x35584).name("x35589_x35562_d0").ctrl(x35590).srcCtx("CellsPar.scala:207:15") // RegWrite(x35562,x35584,x35588)
    val x35589_x35562_d1 = WriteMem(x35562_d1, x35584).name("x35589_x35562_d1").ctrl(x35590).srcCtx("CellsPar.scala:207:15") // RegWrite(x35562,x35584,x35588)
    val x35622 = UnitController(style=SeqPipe, level=InnerControl).name("x35622").ctrl(x35623).srcCtx("CellsPar.scala:194:42") // UnitPipe(List(b23110, b23106, b23062, b23063, b22364),Block(Const(())))
    val x35591 = OpDef(op=FixAdd, inputs=List(b23061, b23109)).name("x35591").ctrl(x35622).srcCtx("CellsPar.scala:209:31:colOffset") // FixAdd(b23061,b23109)
    val x35592 = ReadMem(x35562_d0).name("x35592").ctrl(x35622).srcCtx("CellsPar.scala:210:28") // RegRead(x35562)
    val x35593 = OpDef(op=FixEql, inputs=List(b23060, Const(0))).name("x35593").ctrl(x35622).srcCtx("CellsPar.scala:210:42") // FixEql(b23060,Const(0))
    val x35594 = OpDef(op=FixAdd, inputs=List(x35591, Const(512))).name("x35594").ctrl(x35622).srcCtx("CellsPar.scala:210:66") // FixAdd(x35591,Const(512))
    val x35595 = OpDef(op=BitAnd, inputs=List(b23110, b23106)).name("x35595").ctrl(x35622).srcCtx("UnrollingBase.scala:28:66") // And(b23110,b23106)
    val x35596 = OpDef(op=BitAnd, inputs=List(b23062, b23063)).name("x35596").ctrl(x35622).srcCtx("UnrollingBase.scala:28:66") // And(b23062,b23063)
    val x35597 = OpDef(op=BitAnd, inputs=List(x35595, x35596)).name("x35597").ctrl(x35622).srcCtx("UnrollingBase.scala:28:66") // And(x35595,x35596)
    val x35598 = OpDef(op=BitAnd, inputs=List(x35597, b22364)).name("x35598").ctrl(x35622).srcCtx("UnrollingBase.scala:28:66") // And(x35597,b22364)
    val x35599 = LoadBanks(List(x34764_d2_b0), List(Const(0), x35594)).name("x35599").ctrl(x35622).srcCtx("CellsPar.scala:210:52") // SRAMLoad(x34764,ArrayBuffer(Const(1), Const(2048)),List(Const(0), x35594),Const(0),x35598)
    val x35600 = LoadBanks(List(x34761_d1_b0), List(b23105, x35591)).name("x35600").ctrl(x35622).srcCtx("CellsPar.scala:210:91") // SRAMLoad(x34761,ArrayBuffer(Const(1), Const(512)),List(b23105, x35591),Const(0),x35598)
    val x35601 = OpDef(op=MuxOp, inputs=List(x35593, x35599, x35600)).name("x35601").ctrl(x35622).srcCtx("CellsPar.scala:210:39") // Mux(x35593,x35599,x35600)
    val x35602 = OpDef(op=FixAdd, inputs=List(x35592, x35601)).name("x35602").ctrl(x35622).srcCtx("CellsPar.scala:210:34:ele") // FixAdd(x35592,x35601)
    val x35603 = OpDef(op=FixLeq, inputs=List(Const(1520), b23060)).name("x35603").ctrl(x35622).srcCtx("CellsPar.scala:218:46") // FixLeq(Const(1520),b23060)
    // x35604 = FixConvert(Const(0),TRUE,_8,_24) x.tp=FixPt[TRUE,_32,_0] {
    val x35604_int1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("01111111111111111111111111111111"))).name("x35604_int1").ctrl(x35622).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x35604_int2 = OpDef(op=FixSla, inputs=List(x35604_int1, Const(24))).name("x35604_int2").ctrl(x35622).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x35604_frac1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("00000000000000000000000000000000"))).name("x35604_frac1").ctrl(x35622).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x35604_frac2 = OpDef(op=FixSla, inputs=List(x35604_frac1, Const(24))).name("x35604_frac2").ctrl(x35622).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x35604 = OpDef(op=BitOr, inputs=List(x35604_int2, x35604_frac2)).name("x35604").ctrl(x35622).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    // }
    val x35605 = OpDef(op=FixAdd, inputs=List(x35602, x35604)).name("x35605").ctrl(x35622).srcCtx("CellsPar.scala:218:87") // FixAdd(x35602,x35604)
    val x35606 = x35605 // FixConvert(x35605,TRUE,_8,_24) (Same Type. No op)
    val x35607 = OpDef(op=FixAbs, inputs=List(x35606)).name("x35607").ctrl(x35622).srcCtx("CellsPar.scala:37:19:absp") // FixAbs(x35606)
    val x35608 = OpDef(op=FixLt, inputs=List(Const(2.5), x35607)).name("x35608").ctrl(x35622).srcCtx("CellsPar.scala:51:23:cond1") // FixLt(Const(2.5),x35607)
    val x35609 = OpDef(op=FixLt, inputs=List(Const(0.5), x35607)).name("x35609").ctrl(x35622).srcCtx("CellsPar.scala:54:35") // FixLt(Const(0.5),x35607)
    val x35610 = OpDef(op=FixLeq, inputs=List(x35607, Const(2.5))).name("x35610").ctrl(x35622).srcCtx("CellsPar.scala:54:52") // FixLeq(x35607,Const(2.5))
    val x35611 = OpDef(op=BitAnd, inputs=List(x35609, x35610)).name("x35611").ctrl(x35622).srcCtx("CellsPar.scala:54:43:cond2") // And(x35609,x35610)
    val x35612 = OpDef(op=FixSra, inputs=List(x35607, Const(2))).name("x35612").ctrl(x35622).srcCtx("CellsPar.scala:56:21:div4") // FixRsh(x35607,Const(2))
    val x35613 = OpDef(op=FixAdd, inputs=List(x35612, Const(0.375))).name("x35613").ctrl(x35622).srcCtx("CellsPar.scala:57:27:div4Offset") // FixAdd(x35612,Const(0.375))
    val x35614 = OpDef(op=MuxOp, inputs=List(x35611, x35613, x35607)).name("x35614").ctrl(x35622).srcCtx("CellsPar.scala:58:20:body2") // Mux(x35611,x35613,x35607)
    val x35615 = OpDef(op=MuxOp, inputs=List(x35608, Const(1.0), x35614)).name("x35615").ctrl(x35622).srcCtx("CellsPar.scala:60:20:absre") // Mux(x35608,Const(1),x35614)
    val x35616 = OpDef(op=FixNeg, inputs=List(x35615)).name("x35616").ctrl(x35622).srcCtx("CellsPar.scala:62:29:mabre") // FixNeg(x35615)
    val x35617 = OpDef(op=FixLt, inputs=List(x35606, Const(0.0))).name("x35617").ctrl(x35622).srcCtx("CellsPar.scala:63:22") // FixLt(x35606,Const(0))
    val x35618 = OpDef(op=MuxOp, inputs=List(x35617, x35616, x35615)).name("x35618").ctrl(x35622).srcCtx("CellsPar.scala:63:17:re") // Mux(x35617,x35616,x35615)
    val x35619 = x35618 // FixConvert(x35618,TRUE,_8,_24) (Same Type. No op)
    val x35620 = OpDef(op=MuxOp, inputs=List(x35603, x35619, x35602)).name("x35620").ctrl(x35622).srcCtx("CellsPar.scala:218:43") // Mux(x35603,x35619,x35602)
    val x35621 = StoreBanks(List(x34761_d0_b0, x34761_d1_b0), List(b23105, x35591), x35620).name("x35621").ctrl(x35622).srcCtx("CellsPar.scala:218:38") // SRAMStore(x34761,ArrayBuffer(Const(1), Const(512)),List(b23105, x35591),Const(0),x35620,x35598)
    val x35626 = Counter(min=Const(0), max=Const(512), step=Const(16), par=1).name("x35626").ctrl(x36822).srcCtx("CellsPar.scala:190:58") // CounterNew(Const(0),Const(512),Const(16),Const(1))
    val x35627 = Counter(min=Const(0), max=Const(1536), step=Const(16), par=1).name("x35627").ctrl(x36822).srcCtx("CellsPar.scala:190:33") // CounterNew(Const(0),Const(1536),Const(16),Const(1))
    val x35628 = CounterChain(List(x35627,x35626)).name("x35628").ctrl(x36822).srcCtx("CellsPar.scala:190:72") // CounterChainNew(List(x35627, x35626))
    val x35734 = LoopController(style=MetaPipe, level=OuterControl, cchain=x35628).name("x35734").ctrl(x36822).srcCtx("CellsPar.scala:190:72") // UnrolledForeach(List(b22364),x35628,Block(Const(())),List(List(b23180), List(b23181)),List(List(b23182), List(b23183)))
    val b23180 = CounterIter(x35627, Some(0)).name("b23180").ctrl(x35734) // b23180
    val b23182 = Const(true).name("b23182").ctrl(x35734) // b23182
    val b23181 = CounterIter(x35626, Some(0)).name("b23181").ctrl(x35734) // b23181
    val b23183 = Const(true).name("b23183").ctrl(x35734) // b23183
    val x35629_d0_b0 = SRAM(size=256, banking=Strided(banks=1, stride=16)).name("x35629_d0_b0").ctrl(x35734).srcCtx("CellsPar.scala:191:33:tileKernel") // x35629 = SRAMNew(ArrayBuffer(Const(16), Const(16)))
    isAccum(x35629_d0_b0) = false
    bufferDepthOf(x35629_d0_b0) = 2
    val x35632 = UnitController(style=SeqPipe, level=InnerControl).name("x35632").ctrl(x35734).srcCtx("CellsPar.scala:190:72") // UnitPipe(List(b23182, b23183, b22364),Block(Const(())))
    val x35630 = OpDef(op=FixAdd, inputs=List(b23180, Const(16))).name("x35630").ctrl(x35632).srcCtx("CellsPar.scala:192:36") // FixAdd(b23180,Const(16))
    val x35631 = OpDef(op=FixAdd, inputs=List(b23181, Const(16))).name("x35631").ctrl(x35632).srcCtx("CellsPar.scala:192:45") // FixAdd(b23181,Const(16))
    val x35633 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x35633").ctrl(x35734).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x35634 = CounterChain(List(x35633)).name("x35634").ctrl(x35734).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x35633))
    val x35663 = LoopController(style=StreamPipe, level=OuterControl, cchain=x35634).name("x35663").ctrl(x35734).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b23182, b23183, b22364),x35634,Block(Const(())),List(List(b23190)),List(List(b23191)))
    val b23190 = CounterIter(x35633, Some(0)).name("b23190").ctrl(x35663) // b23190
    val b23191 = Const(true).name("b23191").ctrl(x35663) // b23191
    val b36933 = StreamOut(field="offset").name("b36933").ctrl(x35663).srcCtx("CellsPar.scala:192:20") // x35635 = StreamOutNew(BurstCmdBus)
    isAccum(b36933) = false
    bufferDepthOf(b36933) = 1
    val b36934 = StreamOut(field="size").name("b36934").ctrl(x35663).srcCtx("CellsPar.scala:192:20") // x35635 = StreamOutNew(BurstCmdBus)
    isAccum(b36934) = false
    bufferDepthOf(b36934) = 1
    val x35636 = StreamIn(field="data").name("x35636").ctrl(x35663).srcCtx("CellsPar.scala:192:20") // x35636 = StreamInNew(BurstDataBus())
    isAccum(x35636) = false
    bufferDepthOf(x35636) = 1
    val x35651 = UnitController(style=SeqPipe, level=InnerControl).name("x35651").ctrl(x35663).srcCtx("CellsPar.scala:192:20") // UnitPipe(List(b23191, b23182, b23183, b22364),Block(x35650))
    val x35637 = OpDef(op=FixAdd, inputs=List(b23180, b23190)).name("x35637").ctrl(x35651).srcCtx("CellsPar.scala:192:20") // FixAdd(b23180,b23190)
    val x35638 = x35637 // FixConvert(x35637,TRUE,_32,_0) (Same Type. No op)
    val x35639 = OpDef(op=FixSla, inputs=List(x35638, Const(9))).name("x35639").ctrl(x35651).srcCtx("CellsPar.scala:192:20") // FixLsh(x35638,Const(9))
    val x35640 = b23181 // FixConvert(b23181,TRUE,_32,_0) (Same Type. No op)
    val x35641 = OpDef(op=FixAdd, inputs=List(x35639, x35640)).name("x35641").ctrl(x35651).srcCtx("CellsPar.scala:192:20") // FixAdd(x35639,x35640)
    val x35642 = OpDef(op=FixSla, inputs=List(x35641, Const(2))).name("x35642").ctrl(x35651).srcCtx("CellsPar.scala:192:20") // FixLsh(x35641,Const(2))
    val x35643 = x35642 // FixConvert(x35642,TRUE,_64,_0)
    val x35644 = DramAddress(x34552).name("x35644").ctrl(x35651).srcCtx("CellsPar.scala:192:20") // GetDRAMAddress(x34552)
    val x35646_x35645 = OpDef(op=FixAdd, inputs=List(x35643, x35644)).name("x35646_x35645").ctrl(x35651).srcCtx("CellsPar.scala:192:20") // FixAdd(x35643,x35644)
    // x35646 = SimpleStruct(ArrayBuffer((offset,x35645), (size,Const(64)), (isLoad,Const(true))))
    val x35647 = OpDef(op=BitAnd, inputs=List(b23191, b23182)).name("x35647").ctrl(x35651).srcCtx("UnrollingBase.scala:28:66") // And(b23191,b23182)
    val x35648 = OpDef(op=BitAnd, inputs=List(b23183, b22364)).name("x35648").ctrl(x35651).srcCtx("UnrollingBase.scala:28:66") // And(b23183,b22364)
    val x35649 = OpDef(op=BitAnd, inputs=List(x35647, x35648)).name("x35649").ctrl(x35651).srcCtx("UnrollingBase.scala:28:66") // And(x35647,x35648)
    val x35650_b36935_b36933 = WriteMem(b36933, x35646_x35645).name("x35650_b36935_b36933").ctrl(x35651).srcCtx("CellsPar.scala:192:20") // StreamWrite(x35635,x35646,x35649)
    val x35650_b36936_b36934 = WriteMem(b36934, Const(64)).name("x35650_b36936_b36934").ctrl(x35651).srcCtx("CellsPar.scala:192:20") // StreamWrite(x35635,x35646,x35649)
    val x35652 = FringeDenseLoad(dram=List(x34552), cmdStream=List(b36933, b36934), dataStream=List(x35636)).name("x35652").ctrl(x35663).srcCtx("CellsPar.scala:192:20") // FringeDenseLoad(x34552,x35635,x35636)
    val x35653 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x35653").ctrl(x35663).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x35654 = CounterChain(List(x35653)).name("x35654").ctrl(x35663).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x35653))
    val x35662 = LoopController(style=InnerPipe, level=InnerControl, cchain=x35654).name("x35662").ctrl(x35663).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b23191, b23182, b23183, b22364),x35654,Block(Const(())),List(List(b23212)),List(List(b23213)))
    val b23212 = CounterIter(x35653, None).name("b23212").ctrl(x35662) // b23212
    val b23213 = Const(true).name("b23213").ctrl(x35662) // b23213
    val x35655 = OpDef(op=BitAnd, inputs=List(b23213, b23191)).name("x35655").ctrl(x35662).srcCtx("UnrollingBase.scala:28:66") // And(b23213,b23191)
    val x35656 = OpDef(op=BitAnd, inputs=List(b23182, b23183)).name("x35656").ctrl(x35662).srcCtx("UnrollingBase.scala:28:66") // And(b23182,b23183)
    val x35657 = OpDef(op=BitAnd, inputs=List(x35655, x35656)).name("x35657").ctrl(x35662).srcCtx("UnrollingBase.scala:28:66") // And(x35655,x35656)
    val x35658 = OpDef(op=BitAnd, inputs=List(x35657, b22364)).name("x35658").ctrl(x35662).srcCtx("UnrollingBase.scala:28:66") // And(x35657,b22364)
    val x35659_x35659 = ReadMem(x35636).name("x35659_x35659").ctrl(x35662).srcCtx("CellsPar.scala:192:20") // ParStreamRead(x35636,List(x35658))
    val x35660_x35660 = x35659_x35659 // x35660 = VectorApply(x35659,0)
    val x35661 = StoreBanks(List(x35629_d0_b0), List(b23190, b23212), x35660_x35660).name("x35661").ctrl(x35662).srcCtx("CellsPar.scala:192:20") // ParSRAMStore(x35629,List(List(b23190, b23212)),List(x35660),List(x35658))
    val x35664 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x35664").ctrl(x35734).srcCtx("CellsPar.scala:193:34") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x35665 = CounterChain(List(x35664)).name("x35665").ctrl(x35734).srcCtx("CellsPar.scala:193:46") // CounterChainNew(List(x35664))
    val x35733 = LoopController(style=MetaPipe, level=OuterControl, cchain=x35665).name("x35733").ctrl(x35734).srcCtx("CellsPar.scala:193:46") // UnrolledForeach(List(b23182, b23183, b22364),x35665,Block(Const(())),List(List(b23225)),List(List(b23226)))
    val b23225 = CounterIter(x35664, Some(0)).name("b23225").ctrl(x35733) // b23225
    val b23226 = Const(true).name("b23226").ctrl(x35733) // b23226
    val x35666 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x35666").ctrl(x35733).srcCtx("CellsPar.scala:194:28") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x35667 = CounterChain(List(x35666)).name("x35667").ctrl(x35733).srcCtx("CellsPar.scala:194:42") // CounterChainNew(List(x35666))
    val x35732 = LoopController(style=MetaPipe, level=OuterControl, cchain=x35667).name("x35732").ctrl(x35733).srcCtx("CellsPar.scala:194:42") // UnrolledForeach(List(b23226, b23182, b23183, b22364),x35667,Block(Const(())),List(List(b23229)),List(List(b23230)))
    val b23229 = CounterIter(x35666, Some(0)).name("b23229").ctrl(x35732) // b23229
    val b23230 = Const(true).name("b23230").ctrl(x35732) // b23230
    val x35668_d0 = Reg(init=Some(0.0)).name("x35668_d0").ctrl(x35732).srcCtx("CellsPar.scala:195:34:prod") // x35668 = RegNew(Const(0))
    isAccum(x35668_d0) = false
    bufferDepthOf(x35668_d0) = 2
    val x35668_d1 = Reg(init=Some(0.0)).name("x35668_d1").ctrl(x35732).srcCtx("CellsPar.scala:195:34:prod") // x35668 = RegNew(Const(0))
    isAccum(x35668_d1) = true
    bufferDepthOf(x35668_d1) = 1
    val x35669 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x35669").ctrl(x35732).srcCtx("CellsPar.scala:195:43") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x35670 = CounterChain(List(x35669)).name("x35670").ctrl(x35732).srcCtx("CellsPar.scala:207:15") // CounterChainNew(List(x35669))
    val x35696 = LoopController(style=InnerPipe, level=InnerControl, cchain=x35670).name("x35696").ctrl(x35732).srcCtx("CellsPar.scala:207:15") // UnrolledReduce(List(b23230, b23226, b23182, b23183, b22364),x35670,x35668,Block((x35668) => Const(())),List(List(b23234)),List(List(b23235)))
    val b23234 = CounterIter(x35669, None).name("b23234").ctrl(x35696) // b23234
    val b23235 = Const(true).name("b23235").ctrl(x35696) // b23235
    val x35671 = OpDef(op=FixAdd, inputs=List(b23180, b23234)).name("x35671").ctrl(x35696).srcCtx("CellsPar.scala:196:42:reduce_size_offset") // FixAdd(b23180,b23234)
    val x35672 = OpDef(op=BitAnd, inputs=List(b23235, b23230)).name("x35672").ctrl(x35696).srcCtx("UnrollingBase.scala:28:66") // And(b23235,b23230)
    val x35673 = OpDef(op=BitAnd, inputs=List(b23226, b23182)).name("x35673").ctrl(x35696).srcCtx("UnrollingBase.scala:28:66") // And(b23226,b23182)
    val x35674 = OpDef(op=BitAnd, inputs=List(b23183, b22364)).name("x35674").ctrl(x35696).srcCtx("UnrollingBase.scala:28:66") // And(b23183,b22364)
    val x35675 = OpDef(op=BitAnd, inputs=List(x35672, x35673)).name("x35675").ctrl(x35696).srcCtx("UnrollingBase.scala:28:66") // And(x35672,x35673)
    val x35676 = OpDef(op=BitAnd, inputs=List(x35675, x35674)).name("x35676").ctrl(x35696).srcCtx("UnrollingBase.scala:28:66") // And(x35675,x35674)
    val x35677 = LoadBanks(List(x35629_d0_b0), List(b23234, b23229)).name("x35677").ctrl(x35696).srcCtx("CellsPar.scala:203:34:tk") // ParSRAMLoad(x35629,List(List(b23234, b23229)),List(x35676))
    val x35678 = x35677 // x35678 = VectorApply(x35677,0)
    val x35679 = LoadBanks(List(x34751_d1_b0), List(b23225, x35671)).name("x35679").ctrl(x35696).srcCtx("CellsPar.scala:204:28") // ParSRAMLoad(x34751,List(List(b23225, x35671)),List(x35676))
    val x35680 = x35679 // x35680 = VectorApply(x35679,0)
    val x35681 = OpDef(op=FixMul, inputs=List(x35680, x35678)).name("x35681").ctrl(x35696).srcCtx("CellsPar.scala:204:52:tempA") // FixMul(x35680,x35678)
    val x35682 = OpDef(op=FixSub, inputs=List(x35671, Const(512))).name("x35682").ctrl(x35696).srcCtx("CellsPar.scala:205:51") // FixSub(x35671,Const(512))
    val x35683 = LoadBanks(List(x34758_d6_b0), List(b23225, x35682)).name("x35683").ctrl(x35696).srcCtx("CellsPar.scala:205:28") // ParSRAMLoad(x34758,List(List(b23225, x35682)),List(x35676))
    val x35684 = x35683 // x35684 = VectorApply(x35683,0)
    val x35685 = OpDef(op=FixMul, inputs=List(x35684, x35678)).name("x35685").ctrl(x35696).srcCtx("CellsPar.scala:205:67:tempB") // FixMul(x35684,x35678)
    val x35686 = OpDef(op=FixLt, inputs=List(x35671, Const(512))).name("x35686").ctrl(x35696).srcCtx("CellsPar.scala:206:38") // FixLt(x35671,Const(512))
    val x35687 = OpDef(op=MuxOp, inputs=List(x35686, x35681, x35685)).name("x35687").ctrl(x35696).srcCtx("CellsPar.scala:206:18") // Mux(x35686,x35681,x35685)
    val x35688 = ReadMem(x35668_d1).name("x35688").ctrl(x35696).srcCtx("CellsPar.scala:207:15") // RegRead(x35668)
    val x35689 = OpDef(op=FixEql, inputs=List(b23234, Const(0))).name("x35689").ctrl(x35696).srcCtx("CellsPar.scala:207:15") // FixEql(b23234,Const(0))
    val x35690 = ReduceAccumOp(op=FixAdd, input=x35687, accum=x35688).name("x35690").ctrl(x35696).srcCtx("CellsPar.scala:207:17") // FixAdd(x35687,x35688)
    val x35691 = OpDef(op=BitAnd, inputs=List(b23230, b23226)).name("x35691").ctrl(x35696).srcCtx("UnrollingBase.scala:28:66") // And(b23230,b23226)
    val x35692 = OpDef(op=BitAnd, inputs=List(b23182, b23183)).name("x35692").ctrl(x35696).srcCtx("UnrollingBase.scala:28:66") // And(b23182,b23183)
    val x35693 = OpDef(op=BitAnd, inputs=List(x35691, x35692)).name("x35693").ctrl(x35696).srcCtx("UnrollingBase.scala:28:66") // And(x35691,x35692)
    val x35694 = OpDef(op=BitAnd, inputs=List(x35693, b22364)).name("x35694").ctrl(x35696).srcCtx("UnrollingBase.scala:28:66") // And(x35693,b22364)
    val x35695_x35668_d0 = WriteMem(x35668_d0, x35690).name("x35695_x35668_d0").ctrl(x35696).srcCtx("CellsPar.scala:207:15") // RegWrite(x35668,x35690,x35694)
    val x35695_x35668_d1 = WriteMem(x35668_d1, x35690).name("x35695_x35668_d1").ctrl(x35696).srcCtx("CellsPar.scala:207:15") // RegWrite(x35668,x35690,x35694)
    val x35731 = UnitController(style=SeqPipe, level=InnerControl).name("x35731").ctrl(x35732).srcCtx("CellsPar.scala:194:42") // UnitPipe(List(b23230, b23226, b23182, b23183, b22364),Block(Const(())))
    val x35697 = OpDef(op=FixAdd, inputs=List(b23181, b23229)).name("x35697").ctrl(x35731).srcCtx("CellsPar.scala:209:31:colOffset") // FixAdd(b23181,b23229)
    val x35698 = ReadMem(x35668_d0).name("x35698").ctrl(x35731).srcCtx("CellsPar.scala:210:28") // RegRead(x35668)
    val x35699 = OpDef(op=FixEql, inputs=List(b23180, Const(0))).name("x35699").ctrl(x35731).srcCtx("CellsPar.scala:210:42") // FixEql(b23180,Const(0))
    val x35700 = OpDef(op=FixAdd, inputs=List(x35697, Const(1024))).name("x35700").ctrl(x35731).srcCtx("CellsPar.scala:210:66") // FixAdd(x35697,Const(1024))
    val x35701 = OpDef(op=BitAnd, inputs=List(b23230, b23226)).name("x35701").ctrl(x35731).srcCtx("UnrollingBase.scala:28:66") // And(b23230,b23226)
    val x35702 = OpDef(op=BitAnd, inputs=List(b23182, b23183)).name("x35702").ctrl(x35731).srcCtx("UnrollingBase.scala:28:66") // And(b23182,b23183)
    val x35703 = OpDef(op=BitAnd, inputs=List(x35701, x35702)).name("x35703").ctrl(x35731).srcCtx("UnrollingBase.scala:28:66") // And(x35701,x35702)
    val x35704 = OpDef(op=BitAnd, inputs=List(x35703, b22364)).name("x35704").ctrl(x35731).srcCtx("UnrollingBase.scala:28:66") // And(x35703,b22364)
    val x35705 = LoadBanks(List(x34764_d1_b0), List(Const(0), x35700)).name("x35705").ctrl(x35731).srcCtx("CellsPar.scala:210:52") // SRAMLoad(x34764,ArrayBuffer(Const(1), Const(2048)),List(Const(0), x35700),Const(0),x35704)
    val x35706 = LoadBanks(List(x34762_d1_b0), List(b23225, x35697)).name("x35706").ctrl(x35731).srcCtx("CellsPar.scala:210:91") // SRAMLoad(x34762,ArrayBuffer(Const(1), Const(512)),List(b23225, x35697),Const(0),x35704)
    val x35707 = OpDef(op=MuxOp, inputs=List(x35699, x35705, x35706)).name("x35707").ctrl(x35731).srcCtx("CellsPar.scala:210:39") // Mux(x35699,x35705,x35706)
    val x35708 = OpDef(op=FixAdd, inputs=List(x35698, x35707)).name("x35708").ctrl(x35731).srcCtx("CellsPar.scala:210:34:ele") // FixAdd(x35698,x35707)
    val x35709 = OpDef(op=FixLeq, inputs=List(Const(1520), b23180)).name("x35709").ctrl(x35731).srcCtx("CellsPar.scala:218:46") // FixLeq(Const(1520),b23180)
    // x35710 = FixConvert(Const(1),TRUE,_8,_24) x.tp=FixPt[TRUE,_32,_0] {
    val x35710_int1 = OpDef(op=BitAnd, inputs=List(Const(1), Const("01111111111111111111111111111111"))).name("x35710_int1").ctrl(x35731).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(1),TRUE,_8,_24)
    val x35710_int2 = OpDef(op=FixSla, inputs=List(x35710_int1, Const(24))).name("x35710_int2").ctrl(x35731).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(1),TRUE,_8,_24)
    val x35710_frac1 = OpDef(op=BitAnd, inputs=List(Const(1), Const("00000000000000000000000000000000"))).name("x35710_frac1").ctrl(x35731).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(1),TRUE,_8,_24)
    val x35710_frac2 = OpDef(op=FixSla, inputs=List(x35710_frac1, Const(24))).name("x35710_frac2").ctrl(x35731).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(1),TRUE,_8,_24)
    val x35710 = OpDef(op=BitOr, inputs=List(x35710_int2, x35710_frac2)).name("x35710").ctrl(x35731).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(1),TRUE,_8,_24)
    // }
    val x35711 = OpDef(op=FixAdd, inputs=List(x35708, x35710)).name("x35711").ctrl(x35731).srcCtx("CellsPar.scala:218:87") // FixAdd(x35708,x35710)
    val x35712 = OpDef(op=FixSra, inputs=List(x35711, Const(1))).name("x35712").ctrl(x35731).srcCtx("CellsPar.scala:29:22") // FixRsh(x35711,Const(1))
    val x35713 = x35712 // FixConvert(x35712,TRUE,_8,_24) (Same Type. No op)
    val x35714 = OpDef(op=FixAbs, inputs=List(x35713)).name("x35714").ctrl(x35731).srcCtx("CellsPar.scala:37:19:absp") // FixAbs(x35713)
    val x35715 = OpDef(op=FixLt, inputs=List(Const(2.5), x35714)).name("x35715").ctrl(x35731).srcCtx("CellsPar.scala:51:23:cond1") // FixLt(Const(2.5),x35714)
    val x35716 = OpDef(op=FixLt, inputs=List(Const(0.5), x35714)).name("x35716").ctrl(x35731).srcCtx("CellsPar.scala:54:35") // FixLt(Const(0.5),x35714)
    val x35717 = OpDef(op=FixLeq, inputs=List(x35714, Const(2.5))).name("x35717").ctrl(x35731).srcCtx("CellsPar.scala:54:52") // FixLeq(x35714,Const(2.5))
    val x35718 = OpDef(op=BitAnd, inputs=List(x35716, x35717)).name("x35718").ctrl(x35731).srcCtx("CellsPar.scala:54:43:cond2") // And(x35716,x35717)
    val x35719 = OpDef(op=FixSra, inputs=List(x35714, Const(2))).name("x35719").ctrl(x35731).srcCtx("CellsPar.scala:56:21:div4") // FixRsh(x35714,Const(2))
    val x35720 = OpDef(op=FixAdd, inputs=List(x35719, Const(0.375))).name("x35720").ctrl(x35731).srcCtx("CellsPar.scala:57:27:div4Offset") // FixAdd(x35719,Const(0.375))
    val x35721 = OpDef(op=MuxOp, inputs=List(x35718, x35720, x35714)).name("x35721").ctrl(x35731).srcCtx("CellsPar.scala:58:20:body2") // Mux(x35718,x35720,x35714)
    val x35722 = OpDef(op=MuxOp, inputs=List(x35715, Const(1.0), x35721)).name("x35722").ctrl(x35731).srcCtx("CellsPar.scala:60:20:absre") // Mux(x35715,Const(1),x35721)
    val x35723 = OpDef(op=FixNeg, inputs=List(x35722)).name("x35723").ctrl(x35731).srcCtx("CellsPar.scala:62:29:mabre") // FixNeg(x35722)
    val x35724 = OpDef(op=FixLt, inputs=List(x35713, Const(0.0))).name("x35724").ctrl(x35731).srcCtx("CellsPar.scala:63:22") // FixLt(x35713,Const(0))
    val x35725 = OpDef(op=MuxOp, inputs=List(x35724, x35723, x35722)).name("x35725").ctrl(x35731).srcCtx("CellsPar.scala:63:17:re") // Mux(x35724,x35723,x35722)
    val x35726 = x35725 // FixConvert(x35725,TRUE,_8,_24) (Same Type. No op)
    val x35727 = OpDef(op=FixAdd, inputs=List(x35726, Const(1.0))).name("x35727").ctrl(x35731).srcCtx("CellsPar.scala:29:33") // FixAdd(x35726,Const(1))
    val x35728 = OpDef(op=FixSra, inputs=List(x35727, Const(1))).name("x35728").ctrl(x35731).srcCtx("CellsPar.scala:29:44") // FixRsh(x35727,Const(1))
    val x35729 = OpDef(op=MuxOp, inputs=List(x35709, x35728, x35708)).name("x35729").ctrl(x35731).srcCtx("CellsPar.scala:218:43") // Mux(x35709,x35728,x35708)
    val x35730 = StoreBanks(List(x34762_d0_b0, x34762_d1_b0), List(b23225, x35697), x35729).name("x35730").ctrl(x35731).srcCtx("CellsPar.scala:218:38") // SRAMStore(x34762,ArrayBuffer(Const(1), Const(512)),List(b23225, x35697),Const(0),x35729,x35704)
    val x35735 = Counter(min=Const(0), max=Const(512), step=Const(16), par=1).name("x35735").ctrl(x36822).srcCtx("CellsPar.scala:190:58") // CounterNew(Const(0),Const(512),Const(16),Const(1))
    val x35736 = Counter(min=Const(0), max=Const(1536), step=Const(16), par=1).name("x35736").ctrl(x36822).srcCtx("CellsPar.scala:190:33") // CounterNew(Const(0),Const(1536),Const(16),Const(1))
    val x35737 = CounterChain(List(x35736,x35735)).name("x35737").ctrl(x36822).srcCtx("CellsPar.scala:190:72") // CounterChainNew(List(x35736, x35735))
    val x35843 = LoopController(style=MetaPipe, level=OuterControl, cchain=x35737).name("x35843").ctrl(x36822).srcCtx("CellsPar.scala:190:72") // UnrolledForeach(List(b22364),x35737,Block(Const(())),List(List(b23303), List(b23304)),List(List(b23305), List(b23306)))
    val b23303 = CounterIter(x35736, Some(0)).name("b23303").ctrl(x35843) // b23303
    val b23305 = Const(true).name("b23305").ctrl(x35843) // b23305
    val b23304 = CounterIter(x35735, Some(0)).name("b23304").ctrl(x35843) // b23304
    val b23306 = Const(true).name("b23306").ctrl(x35843) // b23306
    val x35738_d0_b0 = SRAM(size=256, banking=Strided(banks=1, stride=16)).name("x35738_d0_b0").ctrl(x35843).srcCtx("CellsPar.scala:191:33:tileKernel") // x35738 = SRAMNew(ArrayBuffer(Const(16), Const(16)))
    isAccum(x35738_d0_b0) = false
    bufferDepthOf(x35738_d0_b0) = 2
    def split2 = {
    val x35741 = UnitController(style=SeqPipe, level=InnerControl).name("x35741").ctrl(x35843).srcCtx("CellsPar.scala:190:72") // UnitPipe(List(b23305, b23306, b22364),Block(Const(())))
    val x35739 = OpDef(op=FixAdd, inputs=List(b23303, Const(16))).name("x35739").ctrl(x35741).srcCtx("CellsPar.scala:192:36") // FixAdd(b23303,Const(16))
    val x35740 = OpDef(op=FixAdd, inputs=List(b23304, Const(16))).name("x35740").ctrl(x35741).srcCtx("CellsPar.scala:192:45") // FixAdd(b23304,Const(16))
    val x35742 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x35742").ctrl(x35843).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x35743 = CounterChain(List(x35742)).name("x35743").ctrl(x35843).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x35742))
    val x35772 = LoopController(style=StreamPipe, level=OuterControl, cchain=x35743).name("x35772").ctrl(x35843).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b23305, b23306, b22364),x35743,Block(Const(())),List(List(b23313)),List(List(b23314)))
    val b23313 = CounterIter(x35742, Some(0)).name("b23313").ctrl(x35772) // b23313
    val b23314 = Const(true).name("b23314").ctrl(x35772) // b23314
    val b36937 = StreamOut(field="offset").name("b36937").ctrl(x35772).srcCtx("CellsPar.scala:192:20") // x35744 = StreamOutNew(BurstCmdBus)
    isAccum(b36937) = false
    bufferDepthOf(b36937) = 1
    val b36938 = StreamOut(field="size").name("b36938").ctrl(x35772).srcCtx("CellsPar.scala:192:20") // x35744 = StreamOutNew(BurstCmdBus)
    isAccum(b36938) = false
    bufferDepthOf(b36938) = 1
    val x35745 = StreamIn(field="data").name("x35745").ctrl(x35772).srcCtx("CellsPar.scala:192:20") // x35745 = StreamInNew(BurstDataBus())
    isAccum(x35745) = false
    bufferDepthOf(x35745) = 1
    val x35760 = UnitController(style=SeqPipe, level=InnerControl).name("x35760").ctrl(x35772).srcCtx("CellsPar.scala:192:20") // UnitPipe(List(b23314, b23305, b23306, b22364),Block(x35759))
    val x35746 = OpDef(op=FixAdd, inputs=List(b23303, b23313)).name("x35746").ctrl(x35760).srcCtx("CellsPar.scala:192:20") // FixAdd(b23303,b23313)
    val x35747 = x35746 // FixConvert(x35746,TRUE,_32,_0) (Same Type. No op)
    val x35748 = OpDef(op=FixSla, inputs=List(x35747, Const(9))).name("x35748").ctrl(x35760).srcCtx("CellsPar.scala:192:20") // FixLsh(x35747,Const(9))
    val x35749 = b23304 // FixConvert(b23304,TRUE,_32,_0) (Same Type. No op)
    val x35750 = OpDef(op=FixAdd, inputs=List(x35748, x35749)).name("x35750").ctrl(x35760).srcCtx("CellsPar.scala:192:20") // FixAdd(x35748,x35749)
    val x35751 = OpDef(op=FixSla, inputs=List(x35750, Const(2))).name("x35751").ctrl(x35760).srcCtx("CellsPar.scala:192:20") // FixLsh(x35750,Const(2))
    val x35752 = x35751 // FixConvert(x35751,TRUE,_64,_0)
    val x35753 = DramAddress(x34553).name("x35753").ctrl(x35760).srcCtx("CellsPar.scala:192:20") // GetDRAMAddress(x34553)
    val x35755_x35754 = OpDef(op=FixAdd, inputs=List(x35752, x35753)).name("x35755_x35754").ctrl(x35760).srcCtx("CellsPar.scala:192:20") // FixAdd(x35752,x35753)
    // x35755 = SimpleStruct(ArrayBuffer((offset,x35754), (size,Const(64)), (isLoad,Const(true))))
    val x35756 = OpDef(op=BitAnd, inputs=List(b23314, b23305)).name("x35756").ctrl(x35760).srcCtx("UnrollingBase.scala:28:66") // And(b23314,b23305)
    val x35757 = OpDef(op=BitAnd, inputs=List(b23306, b22364)).name("x35757").ctrl(x35760).srcCtx("UnrollingBase.scala:28:66") // And(b23306,b22364)
    val x35758 = OpDef(op=BitAnd, inputs=List(x35756, x35757)).name("x35758").ctrl(x35760).srcCtx("UnrollingBase.scala:28:66") // And(x35756,x35757)
    val x35759_b36939_b36937 = WriteMem(b36937, x35755_x35754).name("x35759_b36939_b36937").ctrl(x35760).srcCtx("CellsPar.scala:192:20") // StreamWrite(x35744,x35755,x35758)
    val x35759_b36940_b36938 = WriteMem(b36938, Const(64)).name("x35759_b36940_b36938").ctrl(x35760).srcCtx("CellsPar.scala:192:20") // StreamWrite(x35744,x35755,x35758)
    val x35761 = FringeDenseLoad(dram=List(x34553), cmdStream=List(b36937, b36938), dataStream=List(x35745)).name("x35761").ctrl(x35772).srcCtx("CellsPar.scala:192:20") // FringeDenseLoad(x34553,x35744,x35745)
    val x35762 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x35762").ctrl(x35772).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x35763 = CounterChain(List(x35762)).name("x35763").ctrl(x35772).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x35762))
    val x35771 = LoopController(style=InnerPipe, level=InnerControl, cchain=x35763).name("x35771").ctrl(x35772).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b23314, b23305, b23306, b22364),x35763,Block(Const(())),List(List(b23335)),List(List(b23336)))
    val b23335 = CounterIter(x35762, None).name("b23335").ctrl(x35771) // b23335
    val b23336 = Const(true).name("b23336").ctrl(x35771) // b23336
    val x35764 = OpDef(op=BitAnd, inputs=List(b23336, b23314)).name("x35764").ctrl(x35771).srcCtx("UnrollingBase.scala:28:66") // And(b23336,b23314)
    val x35765 = OpDef(op=BitAnd, inputs=List(b23305, b23306)).name("x35765").ctrl(x35771).srcCtx("UnrollingBase.scala:28:66") // And(b23305,b23306)
    val x35766 = OpDef(op=BitAnd, inputs=List(x35764, x35765)).name("x35766").ctrl(x35771).srcCtx("UnrollingBase.scala:28:66") // And(x35764,x35765)
    val x35767 = OpDef(op=BitAnd, inputs=List(x35766, b22364)).name("x35767").ctrl(x35771).srcCtx("UnrollingBase.scala:28:66") // And(x35766,b22364)
    val x35768_x35768 = ReadMem(x35745).name("x35768_x35768").ctrl(x35771).srcCtx("CellsPar.scala:192:20") // ParStreamRead(x35745,List(x35767))
    val x35769_x35769 = x35768_x35768 // x35769 = VectorApply(x35768,0)
    val x35770 = StoreBanks(List(x35738_d0_b0), List(b23313, b23335), x35769_x35769).name("x35770").ctrl(x35771).srcCtx("CellsPar.scala:192:20") // ParSRAMStore(x35738,List(List(b23313, b23335)),List(x35769),List(x35767))
    val x35773 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x35773").ctrl(x35843).srcCtx("CellsPar.scala:193:34") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x35774 = CounterChain(List(x35773)).name("x35774").ctrl(x35843).srcCtx("CellsPar.scala:193:46") // CounterChainNew(List(x35773))
    val x35842 = LoopController(style=MetaPipe, level=OuterControl, cchain=x35774).name("x35842").ctrl(x35843).srcCtx("CellsPar.scala:193:46") // UnrolledForeach(List(b23305, b23306, b22364),x35774,Block(Const(())),List(List(b23348)),List(List(b23349)))
    val b23348 = CounterIter(x35773, Some(0)).name("b23348").ctrl(x35842) // b23348
    val b23349 = Const(true).name("b23349").ctrl(x35842) // b23349
    val x35775 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x35775").ctrl(x35842).srcCtx("CellsPar.scala:194:28") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x35776 = CounterChain(List(x35775)).name("x35776").ctrl(x35842).srcCtx("CellsPar.scala:194:42") // CounterChainNew(List(x35775))
    val x35841 = LoopController(style=MetaPipe, level=OuterControl, cchain=x35776).name("x35841").ctrl(x35842).srcCtx("CellsPar.scala:194:42") // UnrolledForeach(List(b23349, b23305, b23306, b22364),x35776,Block(Const(())),List(List(b23352)),List(List(b23353)))
    val b23352 = CounterIter(x35775, Some(0)).name("b23352").ctrl(x35841) // b23352
    val b23353 = Const(true).name("b23353").ctrl(x35841) // b23353
    val x35777_d0 = Reg(init=Some(0.0)).name("x35777_d0").ctrl(x35841).srcCtx("CellsPar.scala:195:34:prod") // x35777 = RegNew(Const(0))
    isAccum(x35777_d0) = false
    bufferDepthOf(x35777_d0) = 2
    val x35777_d1 = Reg(init=Some(0.0)).name("x35777_d1").ctrl(x35841).srcCtx("CellsPar.scala:195:34:prod") // x35777 = RegNew(Const(0))
    isAccum(x35777_d1) = true
    bufferDepthOf(x35777_d1) = 1
    val x35778 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x35778").ctrl(x35841).srcCtx("CellsPar.scala:195:43") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x35779 = CounterChain(List(x35778)).name("x35779").ctrl(x35841).srcCtx("CellsPar.scala:207:15") // CounterChainNew(List(x35778))
    val x35805 = LoopController(style=InnerPipe, level=InnerControl, cchain=x35779).name("x35805").ctrl(x35841).srcCtx("CellsPar.scala:207:15") // UnrolledReduce(List(b23353, b23349, b23305, b23306, b22364),x35779,x35777,Block((x35777) => Const(())),List(List(b23357)),List(List(b23358)))
    val b23357 = CounterIter(x35778, None).name("b23357").ctrl(x35805) // b23357
    val b23358 = Const(true).name("b23358").ctrl(x35805) // b23358
    val x35780 = OpDef(op=FixAdd, inputs=List(b23303, b23357)).name("x35780").ctrl(x35805).srcCtx("CellsPar.scala:196:42:reduce_size_offset") // FixAdd(b23303,b23357)
    val x35781 = OpDef(op=BitAnd, inputs=List(b23358, b23353)).name("x35781").ctrl(x35805).srcCtx("UnrollingBase.scala:28:66") // And(b23358,b23353)
    val x35782 = OpDef(op=BitAnd, inputs=List(b23349, b23305)).name("x35782").ctrl(x35805).srcCtx("UnrollingBase.scala:28:66") // And(b23349,b23305)
    val x35783 = OpDef(op=BitAnd, inputs=List(b23306, b22364)).name("x35783").ctrl(x35805).srcCtx("UnrollingBase.scala:28:66") // And(b23306,b22364)
    val x35784 = OpDef(op=BitAnd, inputs=List(x35781, x35782)).name("x35784").ctrl(x35805).srcCtx("UnrollingBase.scala:28:66") // And(x35781,x35782)
    val x35785 = OpDef(op=BitAnd, inputs=List(x35784, x35783)).name("x35785").ctrl(x35805).srcCtx("UnrollingBase.scala:28:66") // And(x35784,x35783)
    val x35786 = LoadBanks(List(x35738_d0_b0), List(b23357, b23352)).name("x35786").ctrl(x35805).srcCtx("CellsPar.scala:203:34:tk") // ParSRAMLoad(x35738,List(List(b23357, b23352)),List(x35785))
    val x35787 = x35786 // x35787 = VectorApply(x35786,0)
    val x35788 = LoadBanks(List(x34751_d0_b0), List(b23348, x35780)).name("x35788").ctrl(x35805).srcCtx("CellsPar.scala:204:28") // ParSRAMLoad(x34751,List(List(b23348, x35780)),List(x35785))
    val x35789 = x35788 // x35789 = VectorApply(x35788,0)
    val x35790 = OpDef(op=FixMul, inputs=List(x35789, x35787)).name("x35790").ctrl(x35805).srcCtx("CellsPar.scala:204:52:tempA") // FixMul(x35789,x35787)
    val x35791 = OpDef(op=FixSub, inputs=List(x35780, Const(512))).name("x35791").ctrl(x35805).srcCtx("CellsPar.scala:205:51") // FixSub(x35780,Const(512))
    val x35792 = LoadBanks(List(x34758_d5_b0), List(b23348, x35791)).name("x35792").ctrl(x35805).srcCtx("CellsPar.scala:205:28") // ParSRAMLoad(x34758,List(List(b23348, x35791)),List(x35785))
    val x35793 = x35792 // x35793 = VectorApply(x35792,0)
    val x35794 = OpDef(op=FixMul, inputs=List(x35793, x35787)).name("x35794").ctrl(x35805).srcCtx("CellsPar.scala:205:67:tempB") // FixMul(x35793,x35787)
    val x35795 = OpDef(op=FixLt, inputs=List(x35780, Const(512))).name("x35795").ctrl(x35805).srcCtx("CellsPar.scala:206:38") // FixLt(x35780,Const(512))
    val x35796 = OpDef(op=MuxOp, inputs=List(x35795, x35790, x35794)).name("x35796").ctrl(x35805).srcCtx("CellsPar.scala:206:18") // Mux(x35795,x35790,x35794)
    val x35797 = ReadMem(x35777_d1).name("x35797").ctrl(x35805).srcCtx("CellsPar.scala:207:15") // RegRead(x35777)
    val x35798 = OpDef(op=FixEql, inputs=List(b23357, Const(0))).name("x35798").ctrl(x35805).srcCtx("CellsPar.scala:207:15") // FixEql(b23357,Const(0))
    val x35799 = ReduceAccumOp(op=FixAdd, input=x35796, accum=x35797).name("x35799").ctrl(x35805).srcCtx("CellsPar.scala:207:17") // FixAdd(x35796,x35797)
    val x35800 = OpDef(op=BitAnd, inputs=List(b23353, b23349)).name("x35800").ctrl(x35805).srcCtx("UnrollingBase.scala:28:66") // And(b23353,b23349)
    val x35801 = OpDef(op=BitAnd, inputs=List(b23305, b23306)).name("x35801").ctrl(x35805).srcCtx("UnrollingBase.scala:28:66") // And(b23305,b23306)
    val x35802 = OpDef(op=BitAnd, inputs=List(x35800, x35801)).name("x35802").ctrl(x35805).srcCtx("UnrollingBase.scala:28:66") // And(x35800,x35801)
    val x35803 = OpDef(op=BitAnd, inputs=List(x35802, b22364)).name("x35803").ctrl(x35805).srcCtx("UnrollingBase.scala:28:66") // And(x35802,b22364)
    val x35804_x35777_d0 = WriteMem(x35777_d0, x35799).name("x35804_x35777_d0").ctrl(x35805).srcCtx("CellsPar.scala:207:15") // RegWrite(x35777,x35799,x35803)
    val x35804_x35777_d1 = WriteMem(x35777_d1, x35799).name("x35804_x35777_d1").ctrl(x35805).srcCtx("CellsPar.scala:207:15") // RegWrite(x35777,x35799,x35803)
    val x35840 = UnitController(style=SeqPipe, level=InnerControl).name("x35840").ctrl(x35841).srcCtx("CellsPar.scala:194:42") // UnitPipe(List(b23353, b23349, b23305, b23306, b22364),Block(Const(())))
    val x35806 = OpDef(op=FixAdd, inputs=List(b23304, b23352)).name("x35806").ctrl(x35840).srcCtx("CellsPar.scala:209:31:colOffset") // FixAdd(b23304,b23352)
    val x35807 = ReadMem(x35777_d0).name("x35807").ctrl(x35840).srcCtx("CellsPar.scala:210:28") // RegRead(x35777)
    val x35808 = OpDef(op=FixEql, inputs=List(b23303, Const(0))).name("x35808").ctrl(x35840).srcCtx("CellsPar.scala:210:42") // FixEql(b23303,Const(0))
    val x35809 = OpDef(op=FixAdd, inputs=List(x35806, Const(1536))).name("x35809").ctrl(x35840).srcCtx("CellsPar.scala:210:66") // FixAdd(x35806,Const(1536))
    val x35810 = OpDef(op=BitAnd, inputs=List(b23353, b23349)).name("x35810").ctrl(x35840).srcCtx("UnrollingBase.scala:28:66") // And(b23353,b23349)
    val x35811 = OpDef(op=BitAnd, inputs=List(b23305, b23306)).name("x35811").ctrl(x35840).srcCtx("UnrollingBase.scala:28:66") // And(b23305,b23306)
    val x35812 = OpDef(op=BitAnd, inputs=List(x35810, x35811)).name("x35812").ctrl(x35840).srcCtx("UnrollingBase.scala:28:66") // And(x35810,x35811)
    val x35813 = OpDef(op=BitAnd, inputs=List(x35812, b22364)).name("x35813").ctrl(x35840).srcCtx("UnrollingBase.scala:28:66") // And(x35812,b22364)
    val x35814 = LoadBanks(List(x34764_d0_b0), List(Const(0), x35809)).name("x35814").ctrl(x35840).srcCtx("CellsPar.scala:210:52") // SRAMLoad(x34764,ArrayBuffer(Const(1), Const(2048)),List(Const(0), x35809),Const(0),x35813)
    val x35815 = LoadBanks(List(x34763_d1_b0), List(b23348, x35806)).name("x35815").ctrl(x35840).srcCtx("CellsPar.scala:210:91") // SRAMLoad(x34763,ArrayBuffer(Const(1), Const(512)),List(b23348, x35806),Const(0),x35813)
    val x35816 = OpDef(op=MuxOp, inputs=List(x35808, x35814, x35815)).name("x35816").ctrl(x35840).srcCtx("CellsPar.scala:210:39") // Mux(x35808,x35814,x35815)
    val x35817 = OpDef(op=FixAdd, inputs=List(x35807, x35816)).name("x35817").ctrl(x35840).srcCtx("CellsPar.scala:210:34:ele") // FixAdd(x35807,x35816)
    val x35818 = OpDef(op=FixLeq, inputs=List(Const(1520), b23303)).name("x35818").ctrl(x35840).srcCtx("CellsPar.scala:218:46") // FixLeq(Const(1520),b23303)
    // x35819 = FixConvert(Const(0),TRUE,_8,_24) x.tp=FixPt[TRUE,_32,_0] {
    val x35819_int1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("01111111111111111111111111111111"))).name("x35819_int1").ctrl(x35840).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x35819_int2 = OpDef(op=FixSla, inputs=List(x35819_int1, Const(24))).name("x35819_int2").ctrl(x35840).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x35819_frac1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("00000000000000000000000000000000"))).name("x35819_frac1").ctrl(x35840).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x35819_frac2 = OpDef(op=FixSla, inputs=List(x35819_frac1, Const(24))).name("x35819_frac2").ctrl(x35840).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x35819 = OpDef(op=BitOr, inputs=List(x35819_int2, x35819_frac2)).name("x35819").ctrl(x35840).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    // }
    val x35820 = OpDef(op=FixAdd, inputs=List(x35817, x35819)).name("x35820").ctrl(x35840).srcCtx("CellsPar.scala:218:87") // FixAdd(x35817,x35819)
    val x35821 = OpDef(op=FixSra, inputs=List(x35820, Const(1))).name("x35821").ctrl(x35840).srcCtx("CellsPar.scala:29:22") // FixRsh(x35820,Const(1))
    val x35822 = x35821 // FixConvert(x35821,TRUE,_8,_24) (Same Type. No op)
    val x35823 = OpDef(op=FixAbs, inputs=List(x35822)).name("x35823").ctrl(x35840).srcCtx("CellsPar.scala:37:19:absp") // FixAbs(x35822)
    val x35824 = OpDef(op=FixLt, inputs=List(Const(2.5), x35823)).name("x35824").ctrl(x35840).srcCtx("CellsPar.scala:51:23:cond1") // FixLt(Const(2.5),x35823)
    val x35825 = OpDef(op=FixLt, inputs=List(Const(0.5), x35823)).name("x35825").ctrl(x35840).srcCtx("CellsPar.scala:54:35") // FixLt(Const(0.5),x35823)
    val x35826 = OpDef(op=FixLeq, inputs=List(x35823, Const(2.5))).name("x35826").ctrl(x35840).srcCtx("CellsPar.scala:54:52") // FixLeq(x35823,Const(2.5))
    val x35827 = OpDef(op=BitAnd, inputs=List(x35825, x35826)).name("x35827").ctrl(x35840).srcCtx("CellsPar.scala:54:43:cond2") // And(x35825,x35826)
    val x35828 = OpDef(op=FixSra, inputs=List(x35823, Const(2))).name("x35828").ctrl(x35840).srcCtx("CellsPar.scala:56:21:div4") // FixRsh(x35823,Const(2))
    val x35829 = OpDef(op=FixAdd, inputs=List(x35828, Const(0.375))).name("x35829").ctrl(x35840).srcCtx("CellsPar.scala:57:27:div4Offset") // FixAdd(x35828,Const(0.375))
    val x35830 = OpDef(op=MuxOp, inputs=List(x35827, x35829, x35823)).name("x35830").ctrl(x35840).srcCtx("CellsPar.scala:58:20:body2") // Mux(x35827,x35829,x35823)
    val x35831 = OpDef(op=MuxOp, inputs=List(x35824, Const(1.0), x35830)).name("x35831").ctrl(x35840).srcCtx("CellsPar.scala:60:20:absre") // Mux(x35824,Const(1),x35830)
    val x35832 = OpDef(op=FixNeg, inputs=List(x35831)).name("x35832").ctrl(x35840).srcCtx("CellsPar.scala:62:29:mabre") // FixNeg(x35831)
    val x35833 = OpDef(op=FixLt, inputs=List(x35822, Const(0.0))).name("x35833").ctrl(x35840).srcCtx("CellsPar.scala:63:22") // FixLt(x35822,Const(0))
    val x35834 = OpDef(op=MuxOp, inputs=List(x35833, x35832, x35831)).name("x35834").ctrl(x35840).srcCtx("CellsPar.scala:63:17:re") // Mux(x35833,x35832,x35831)
    val x35835 = x35834 // FixConvert(x35834,TRUE,_8,_24) (Same Type. No op)
    val x35836 = OpDef(op=FixAdd, inputs=List(x35835, Const(1.0))).name("x35836").ctrl(x35840).srcCtx("CellsPar.scala:29:33") // FixAdd(x35835,Const(1))
    val x35837 = OpDef(op=FixSra, inputs=List(x35836, Const(1))).name("x35837").ctrl(x35840).srcCtx("CellsPar.scala:29:44") // FixRsh(x35836,Const(1))
    val x35838 = OpDef(op=MuxOp, inputs=List(x35818, x35837, x35817)).name("x35838").ctrl(x35840).srcCtx("CellsPar.scala:218:43") // Mux(x35818,x35837,x35817)
    val x35839 = StoreBanks(List(x34763_d0_b0, x34763_d1_b0), List(b23348, x35806), x35838).name("x35839").ctrl(x35840).srcCtx("CellsPar.scala:218:38") // SRAMStore(x34763,ArrayBuffer(Const(1), Const(512)),List(b23348, x35806),Const(0),x35838,x35813)
    val x35844 = Counter(min=Const(0), max=Const(512), step=Const(1), par=1).name("x35844").ctrl(x36822).srcCtx("CellsPar.scala:243:50") // CounterNew(Const(0),Const(512),Const(1),Const(1))
    val x35845 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x35845").ctrl(x36822).srcCtx("CellsPar.scala:243:27") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x35846 = CounterChain(List(x35845,x35844)).name("x35846").ctrl(x36822).srcCtx("CellsPar.scala:243:62") // CounterChainNew(List(x35845, x35844))
    val x35879 = LoopController(style=InnerPipe, level=InnerControl, cchain=x35846).name("x35879").ctrl(x36822).srcCtx("CellsPar.scala:243:62") // UnrolledForeach(List(b22364),x35846,Block(Const(())),List(List(b23427), List(b23428)),List(List(b23429), List(b23430)))
    val b23427 = CounterIter(x35845, Some(0)).name("b23427").ctrl(x35879) // b23427
    val b23429 = Const(true).name("b23429").ctrl(x35879) // b23429
    val b23428 = CounterIter(x35844, None).name("b23428").ctrl(x35879) // b23428
    val b23430 = Const(true).name("b23430").ctrl(x35879) // b23430
    val x35847 = OpDef(op=BitAnd, inputs=List(b23429, b23430)).name("x35847").ctrl(x35879).srcCtx("UnrollingBase.scala:28:66") // And(b23429,b23430)
    val x35848 = OpDef(op=BitAnd, inputs=List(x35847, b22364)).name("x35848").ctrl(x35879).srcCtx("UnrollingBase.scala:28:66") // And(x35847,b22364)
    val x35849 = LoadBanks(List(x34759_d0_b0), List(b23427, b23428)).name("x35849").ctrl(x35879).srcCtx("CellsPar.scala:244:22") // ParSRAMLoad(x34759,List(List(b23427, b23428)),List(x35848))
    val x35850 = x35849 // x35850 = VectorApply(x35849,0)
    val x35851 = LoadBanks(List(x34762_d0_b0), List(b23427, b23428)).name("x35851").ctrl(x35879).srcCtx("CellsPar.scala:244:34") // ParSRAMLoad(x34762,List(List(b23427, b23428)),List(x35848))
    val x35852 = x35851 // x35852 = VectorApply(x35851,0)
    val x35853 = OpDef(op=FixMul, inputs=List(x35850, x35852)).name("x35853").ctrl(x35879).srcCtx("CellsPar.scala:244:28") // FixMul(x35850,x35852)
    val x35854 = LoadBanks(List(x34760_d0_b0), List(b23427, b23428)).name("x35854").ctrl(x35879).srcCtx("CellsPar.scala:244:46") // ParSRAMLoad(x34760,List(List(b23427, b23428)),List(x35848))
    val x35855 = x35854 // x35855 = VectorApply(x35854,0)
    val x35856 = LoadBanks(List(x34761_d0_b0), List(b23427, b23428)).name("x35856").ctrl(x35879).srcCtx("CellsPar.scala:244:59") // ParSRAMLoad(x34761,List(List(b23427, b23428)),List(x35848))
    val x35857 = x35856 // x35857 = VectorApply(x35856,0)
    val x35858 = OpDef(op=FixMul, inputs=List(x35855, x35857)).name("x35858").ctrl(x35879).srcCtx("CellsPar.scala:244:52") // FixMul(x35855,x35857)
    val x35859 = OpDef(op=FixAdd, inputs=List(x35853, x35858)).name("x35859").ctrl(x35879).srcCtx("CellsPar.scala:244:40:new_c") // FixAdd(x35853,x35858)
    val x35860 = x35859 // FixConvert(x35859,TRUE,_8,_24) (Same Type. No op)
    val x35861 = OpDef(op=FixAbs, inputs=List(x35860)).name("x35861").ctrl(x35879).srcCtx("CellsPar.scala:37:19:absp") // FixAbs(x35860)
    val x35862 = OpDef(op=FixLt, inputs=List(Const(2.5), x35861)).name("x35862").ctrl(x35879).srcCtx("CellsPar.scala:51:23:cond1") // FixLt(Const(2.5),x35861)
    val x35863 = OpDef(op=FixLt, inputs=List(Const(0.5), x35861)).name("x35863").ctrl(x35879).srcCtx("CellsPar.scala:54:35") // FixLt(Const(0.5),x35861)
    val x35864 = OpDef(op=FixLeq, inputs=List(x35861, Const(2.5))).name("x35864").ctrl(x35879).srcCtx("CellsPar.scala:54:52") // FixLeq(x35861,Const(2.5))
    val x35865 = OpDef(op=BitAnd, inputs=List(x35863, x35864)).name("x35865").ctrl(x35879).srcCtx("CellsPar.scala:54:43:cond2") // And(x35863,x35864)
    val x35866 = OpDef(op=FixSra, inputs=List(x35861, Const(2))).name("x35866").ctrl(x35879).srcCtx("CellsPar.scala:56:21:div4") // FixRsh(x35861,Const(2))
    val x35867 = OpDef(op=FixAdd, inputs=List(x35866, Const(0.375))).name("x35867").ctrl(x35879).srcCtx("CellsPar.scala:57:27:div4Offset") // FixAdd(x35866,Const(0.375))
    val x35868 = OpDef(op=MuxOp, inputs=List(x35865, x35867, x35861)).name("x35868").ctrl(x35879).srcCtx("CellsPar.scala:58:20:body2") // Mux(x35865,x35867,x35861)
    val x35869 = OpDef(op=MuxOp, inputs=List(x35862, Const(1.0), x35868)).name("x35869").ctrl(x35879).srcCtx("CellsPar.scala:60:20:absre") // Mux(x35862,Const(1),x35868)
    val x35870 = OpDef(op=FixNeg, inputs=List(x35869)).name("x35870").ctrl(x35879).srcCtx("CellsPar.scala:62:29:mabre") // FixNeg(x35869)
    val x35871 = OpDef(op=FixLt, inputs=List(x35860, Const(0.0))).name("x35871").ctrl(x35879).srcCtx("CellsPar.scala:63:22") // FixLt(x35860,Const(0))
    val x35872 = OpDef(op=MuxOp, inputs=List(x35871, x35870, x35869)).name("x35872").ctrl(x35879).srcCtx("CellsPar.scala:63:17:re") // Mux(x35871,x35870,x35869)
    val x35873 = x35872 // FixConvert(x35872,TRUE,_8,_24) (Same Type. No op)
    val x35874 = LoadBanks(List(x34763_d0_b0), List(b23427, b23428)).name("x35874").ctrl(x35879).srcCtx("CellsPar.scala:245:45") // ParSRAMLoad(x34763,List(List(b23427, b23428)),List(x35848))
    val x35875 = x35874 // x35875 = VectorApply(x35874,0)
    val x35876 = OpDef(op=FixMul, inputs=List(x35873, x35875)).name("x35876").ctrl(x35879).srcCtx("CellsPar.scala:245:39") // FixMul(x35873,x35875)
    val x35877 = StoreBanks(List(x34758_d0_b0, x34758_d5_b0, x34758_d1_b0, x34758_d6_b0, x34758_d2_b0, x34758_d7_b0, x34758_d3_b0, x34758_d8_b0, x34758_d4_b0), List(b23427, b23428), x35876).name("x35877").ctrl(x35879).srcCtx("CellsPar.scala:245:16") // ParSRAMStore(x34758,List(List(b23427, b23428)),List(x35876),List(x35848))
    val x35878 = StoreBanks(List(x34759_d0_b0), List(b23427, b23428), x35859).name("x35878").ctrl(x35879).srcCtx("CellsPar.scala:246:16") // ParSRAMStore(x34759,List(List(b23427, b23428)),List(x35859),List(x35848))
    val x35880 = Counter(min=Const(0), max=Const(512), step=Const(16), par=1).name("x35880").ctrl(x36822).srcCtx("CellsPar.scala:190:58") // CounterNew(Const(0),Const(512),Const(16),Const(1))
    val x35881 = Counter(min=Const(0), max=Const(1536), step=Const(16), par=1).name("x35881").ctrl(x36822).srcCtx("CellsPar.scala:190:33") // CounterNew(Const(0),Const(1536),Const(16),Const(1))
    val x35882 = CounterChain(List(x35881,x35880)).name("x35882").ctrl(x36822).srcCtx("CellsPar.scala:190:72") // CounterChainNew(List(x35881, x35880))
    val x35987 = LoopController(style=MetaPipe, level=OuterControl, cchain=x35882).name("x35987").ctrl(x36822).srcCtx("CellsPar.scala:190:72") // UnrolledForeach(List(b22364),x35882,Block(Const(())),List(List(b23467), List(b23468)),List(List(b23469), List(b23470)))
    val b23467 = CounterIter(x35881, Some(0)).name("b23467").ctrl(x35987) // b23467
    val b23469 = Const(true).name("b23469").ctrl(x35987) // b23469
    val b23468 = CounterIter(x35880, Some(0)).name("b23468").ctrl(x35987) // b23468
    val b23470 = Const(true).name("b23470").ctrl(x35987) // b23470
    val x35883_d0_b0 = SRAM(size=256, banking=Strided(banks=1, stride=16)).name("x35883_d0_b0").ctrl(x35987).srcCtx("CellsPar.scala:191:33:tileKernel") // x35883 = SRAMNew(ArrayBuffer(Const(16), Const(16)))
    isAccum(x35883_d0_b0) = false
    bufferDepthOf(x35883_d0_b0) = 2
    val x35886 = UnitController(style=SeqPipe, level=InnerControl).name("x35886").ctrl(x35987).srcCtx("CellsPar.scala:190:72") // UnitPipe(List(b23469, b23470, b22364),Block(Const(())))
    val x35884 = OpDef(op=FixAdd, inputs=List(b23467, Const(16))).name("x35884").ctrl(x35886).srcCtx("CellsPar.scala:192:36") // FixAdd(b23467,Const(16))
    val x35885 = OpDef(op=FixAdd, inputs=List(b23468, Const(16))).name("x35885").ctrl(x35886).srcCtx("CellsPar.scala:192:45") // FixAdd(b23468,Const(16))
    val x35887 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x35887").ctrl(x35987).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x35888 = CounterChain(List(x35887)).name("x35888").ctrl(x35987).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x35887))
    val x35917 = LoopController(style=StreamPipe, level=OuterControl, cchain=x35888).name("x35917").ctrl(x35987).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b23469, b23470, b22364),x35888,Block(Const(())),List(List(b23477)),List(List(b23478)))
    val b23477 = CounterIter(x35887, Some(0)).name("b23477").ctrl(x35917) // b23477
    val b23478 = Const(true).name("b23478").ctrl(x35917) // b23478
    val b36941 = StreamOut(field="offset").name("b36941").ctrl(x35917).srcCtx("CellsPar.scala:192:20") // x35889 = StreamOutNew(BurstCmdBus)
    isAccum(b36941) = false
    bufferDepthOf(b36941) = 1
    val b36942 = StreamOut(field="size").name("b36942").ctrl(x35917).srcCtx("CellsPar.scala:192:20") // x35889 = StreamOutNew(BurstCmdBus)
    isAccum(b36942) = false
    bufferDepthOf(b36942) = 1
    val x35890 = StreamIn(field="data").name("x35890").ctrl(x35917).srcCtx("CellsPar.scala:192:20") // x35890 = StreamInNew(BurstDataBus())
    isAccum(x35890) = false
    bufferDepthOf(x35890) = 1
    val x35905 = UnitController(style=SeqPipe, level=InnerControl).name("x35905").ctrl(x35917).srcCtx("CellsPar.scala:192:20") // UnitPipe(List(b23478, b23469, b23470, b22364),Block(x35904))
    val x35891 = OpDef(op=FixAdd, inputs=List(b23467, b23477)).name("x35891").ctrl(x35905).srcCtx("CellsPar.scala:192:20") // FixAdd(b23467,b23477)
    val x35892 = x35891 // FixConvert(x35891,TRUE,_32,_0) (Same Type. No op)
    val x35893 = OpDef(op=FixSla, inputs=List(x35892, Const(9))).name("x35893").ctrl(x35905).srcCtx("CellsPar.scala:192:20") // FixLsh(x35892,Const(9))
    val x35894 = b23468 // FixConvert(b23468,TRUE,_32,_0) (Same Type. No op)
    val x35895 = OpDef(op=FixAdd, inputs=List(x35893, x35894)).name("x35895").ctrl(x35905).srcCtx("CellsPar.scala:192:20") // FixAdd(x35893,x35894)
    val x35896 = OpDef(op=FixSla, inputs=List(x35895, Const(2))).name("x35896").ctrl(x35905).srcCtx("CellsPar.scala:192:20") // FixLsh(x35895,Const(2))
    val x35897 = x35896 // FixConvert(x35896,TRUE,_64,_0)
    val x35898 = DramAddress(x34626).name("x35898").ctrl(x35905).srcCtx("CellsPar.scala:192:20") // GetDRAMAddress(x34626)
    val x35900_x35899 = OpDef(op=FixAdd, inputs=List(x35897, x35898)).name("x35900_x35899").ctrl(x35905).srcCtx("CellsPar.scala:192:20") // FixAdd(x35897,x35898)
    // x35900 = SimpleStruct(ArrayBuffer((offset,x35899), (size,Const(64)), (isLoad,Const(true))))
    val x35901 = OpDef(op=BitAnd, inputs=List(b23478, b23469)).name("x35901").ctrl(x35905).srcCtx("UnrollingBase.scala:28:66") // And(b23478,b23469)
    val x35902 = OpDef(op=BitAnd, inputs=List(b23470, b22364)).name("x35902").ctrl(x35905).srcCtx("UnrollingBase.scala:28:66") // And(b23470,b22364)
    val x35903 = OpDef(op=BitAnd, inputs=List(x35901, x35902)).name("x35903").ctrl(x35905).srcCtx("UnrollingBase.scala:28:66") // And(x35901,x35902)
    val x35904_b36943_b36941 = WriteMem(b36941, x35900_x35899).name("x35904_b36943_b36941").ctrl(x35905).srcCtx("CellsPar.scala:192:20") // StreamWrite(x35889,x35900,x35903)
    val x35904_b36944_b36942 = WriteMem(b36942, Const(64)).name("x35904_b36944_b36942").ctrl(x35905).srcCtx("CellsPar.scala:192:20") // StreamWrite(x35889,x35900,x35903)
    val x35906 = FringeDenseLoad(dram=List(x34626), cmdStream=List(b36941, b36942), dataStream=List(x35890)).name("x35906").ctrl(x35917).srcCtx("CellsPar.scala:192:20") // FringeDenseLoad(x34626,x35889,x35890)
    val x35907 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x35907").ctrl(x35917).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x35908 = CounterChain(List(x35907)).name("x35908").ctrl(x35917).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x35907))
    val x35916 = LoopController(style=InnerPipe, level=InnerControl, cchain=x35908).name("x35916").ctrl(x35917).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b23478, b23469, b23470, b22364),x35908,Block(Const(())),List(List(b23499)),List(List(b23500)))
    val b23499 = CounterIter(x35907, None).name("b23499").ctrl(x35916) // b23499
    val b23500 = Const(true).name("b23500").ctrl(x35916) // b23500
    val x35909 = OpDef(op=BitAnd, inputs=List(b23500, b23478)).name("x35909").ctrl(x35916).srcCtx("UnrollingBase.scala:28:66") // And(b23500,b23478)
    val x35910 = OpDef(op=BitAnd, inputs=List(b23469, b23470)).name("x35910").ctrl(x35916).srcCtx("UnrollingBase.scala:28:66") // And(b23469,b23470)
    val x35911 = OpDef(op=BitAnd, inputs=List(x35909, x35910)).name("x35911").ctrl(x35916).srcCtx("UnrollingBase.scala:28:66") // And(x35909,x35910)
    val x35912 = OpDef(op=BitAnd, inputs=List(x35911, b22364)).name("x35912").ctrl(x35916).srcCtx("UnrollingBase.scala:28:66") // And(x35911,b22364)
    val x35913_x35913 = ReadMem(x35890).name("x35913_x35913").ctrl(x35916).srcCtx("CellsPar.scala:192:20") // ParStreamRead(x35890,List(x35912))
    val x35914_x35914 = x35913_x35913 // x35914 = VectorApply(x35913,0)
    val x35915 = StoreBanks(List(x35883_d0_b0), List(b23477, b23499), x35914_x35914).name("x35915").ctrl(x35916).srcCtx("CellsPar.scala:192:20") // ParSRAMStore(x35883,List(List(b23477, b23499)),List(x35914),List(x35912))
    val x35918 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x35918").ctrl(x35987).srcCtx("CellsPar.scala:193:34") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x35919 = CounterChain(List(x35918)).name("x35919").ctrl(x35987).srcCtx("CellsPar.scala:193:46") // CounterChainNew(List(x35918))
    val x35986 = LoopController(style=MetaPipe, level=OuterControl, cchain=x35919).name("x35986").ctrl(x35987).srcCtx("CellsPar.scala:193:46") // UnrolledForeach(List(b23469, b23470, b22364),x35919,Block(Const(())),List(List(b23512)),List(List(b23513)))
    val b23512 = CounterIter(x35918, Some(0)).name("b23512").ctrl(x35986) // b23512
    val b23513 = Const(true).name("b23513").ctrl(x35986) // b23513
    val x35920 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x35920").ctrl(x35986).srcCtx("CellsPar.scala:194:28") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x35921 = CounterChain(List(x35920)).name("x35921").ctrl(x35986).srcCtx("CellsPar.scala:194:42") // CounterChainNew(List(x35920))
    val x35985 = LoopController(style=MetaPipe, level=OuterControl, cchain=x35921).name("x35985").ctrl(x35986).srcCtx("CellsPar.scala:194:42") // UnrolledForeach(List(b23513, b23469, b23470, b22364),x35921,Block(Const(())),List(List(b23516)),List(List(b23517)))
    val b23516 = CounterIter(x35920, Some(0)).name("b23516").ctrl(x35985) // b23516
    val b23517 = Const(true).name("b23517").ctrl(x35985) // b23517
    val x35922_d0 = Reg(init=Some(0.0)).name("x35922_d0").ctrl(x35985).srcCtx("CellsPar.scala:195:34:prod") // x35922 = RegNew(Const(0))
    isAccum(x35922_d0) = false
    bufferDepthOf(x35922_d0) = 2
    val x35922_d1 = Reg(init=Some(0.0)).name("x35922_d1").ctrl(x35985).srcCtx("CellsPar.scala:195:34:prod") // x35922 = RegNew(Const(0))
    isAccum(x35922_d1) = true
    bufferDepthOf(x35922_d1) = 1
    val x35923 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x35923").ctrl(x35985).srcCtx("CellsPar.scala:195:43") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x35924 = CounterChain(List(x35923)).name("x35924").ctrl(x35985).srcCtx("CellsPar.scala:207:15") // CounterChainNew(List(x35923))
    val x35950 = LoopController(style=InnerPipe, level=InnerControl, cchain=x35924).name("x35950").ctrl(x35985).srcCtx("CellsPar.scala:207:15") // UnrolledReduce(List(b23517, b23513, b23469, b23470, b22364),x35924,x35922,Block((x35922) => Const(())),List(List(b23521)),List(List(b23522)))
    val b23521 = CounterIter(x35923, None).name("b23521").ctrl(x35950) // b23521
    val b23522 = Const(true).name("b23522").ctrl(x35950) // b23522
    val x35925 = OpDef(op=FixAdd, inputs=List(b23467, b23521)).name("x35925").ctrl(x35950).srcCtx("CellsPar.scala:196:42:reduce_size_offset") // FixAdd(b23467,b23521)
    val x35926 = OpDef(op=BitAnd, inputs=List(b23522, b23517)).name("x35926").ctrl(x35950).srcCtx("UnrollingBase.scala:28:66") // And(b23522,b23517)
    val x35927 = OpDef(op=BitAnd, inputs=List(b23513, b23469)).name("x35927").ctrl(x35950).srcCtx("UnrollingBase.scala:28:66") // And(b23513,b23469)
    val x35928 = OpDef(op=BitAnd, inputs=List(b23470, b22364)).name("x35928").ctrl(x35950).srcCtx("UnrollingBase.scala:28:66") // And(b23470,b22364)
    val x35929 = OpDef(op=BitAnd, inputs=List(x35926, x35927)).name("x35929").ctrl(x35950).srcCtx("UnrollingBase.scala:28:66") // And(x35926,x35927)
    val x35930 = OpDef(op=BitAnd, inputs=List(x35929, x35928)).name("x35930").ctrl(x35950).srcCtx("UnrollingBase.scala:28:66") // And(x35929,x35928)
    val x35931 = LoadBanks(List(x35883_d0_b0), List(b23521, b23516)).name("x35931").ctrl(x35950).srcCtx("CellsPar.scala:203:34:tk") // ParSRAMLoad(x35883,List(List(b23521, b23516)),List(x35930))
    val x35932 = x35931 // x35932 = VectorApply(x35931,0)
    val x35933 = LoadBanks(List(x34758_d4_b0), List(b23512, x35925)).name("x35933").ctrl(x35950).srcCtx("CellsPar.scala:204:28") // ParSRAMLoad(x34758,List(List(b23512, x35925)),List(x35930))
    val x35934 = x35933 // x35934 = VectorApply(x35933,0)
    val x35935 = OpDef(op=FixMul, inputs=List(x35934, x35932)).name("x35935").ctrl(x35950).srcCtx("CellsPar.scala:204:52:tempA") // FixMul(x35934,x35932)
    val x35936 = OpDef(op=FixSub, inputs=List(x35925, Const(512))).name("x35936").ctrl(x35950).srcCtx("CellsPar.scala:205:51") // FixSub(x35925,Const(512))
    val x35937 = LoadBanks(List(x34765_d8_b0), List(b23512, x35936)).name("x35937").ctrl(x35950).srcCtx("CellsPar.scala:205:28") // ParSRAMLoad(x34765,List(List(b23512, x35936)),List(x35930))
    val x35938 = x35937 // x35938 = VectorApply(x35937,0)
    val x35939 = OpDef(op=FixMul, inputs=List(x35938, x35932)).name("x35939").ctrl(x35950).srcCtx("CellsPar.scala:205:67:tempB") // FixMul(x35938,x35932)
    val x35940 = OpDef(op=FixLt, inputs=List(x35925, Const(512))).name("x35940").ctrl(x35950).srcCtx("CellsPar.scala:206:38") // FixLt(x35925,Const(512))
    val x35941 = OpDef(op=MuxOp, inputs=List(x35940, x35935, x35939)).name("x35941").ctrl(x35950).srcCtx("CellsPar.scala:206:18") // Mux(x35940,x35935,x35939)
    val x35942 = ReadMem(x35922_d1).name("x35942").ctrl(x35950).srcCtx("CellsPar.scala:207:15") // RegRead(x35922)
    val x35943 = OpDef(op=FixEql, inputs=List(b23521, Const(0))).name("x35943").ctrl(x35950).srcCtx("CellsPar.scala:207:15") // FixEql(b23521,Const(0))
    val x35944 = ReduceAccumOp(op=FixAdd, input=x35941, accum=x35942).name("x35944").ctrl(x35950).srcCtx("CellsPar.scala:207:17") // FixAdd(x35941,x35942)
    val x35945 = OpDef(op=BitAnd, inputs=List(b23517, b23513)).name("x35945").ctrl(x35950).srcCtx("UnrollingBase.scala:28:66") // And(b23517,b23513)
    val x35946 = OpDef(op=BitAnd, inputs=List(b23469, b23470)).name("x35946").ctrl(x35950).srcCtx("UnrollingBase.scala:28:66") // And(b23469,b23470)
    val x35947 = OpDef(op=BitAnd, inputs=List(x35945, x35946)).name("x35947").ctrl(x35950).srcCtx("UnrollingBase.scala:28:66") // And(x35945,x35946)
    val x35948 = OpDef(op=BitAnd, inputs=List(x35947, b22364)).name("x35948").ctrl(x35950).srcCtx("UnrollingBase.scala:28:66") // And(x35947,b22364)
    val x35949_x35922_d0 = WriteMem(x35922_d0, x35944).name("x35949_x35922_d0").ctrl(x35950).srcCtx("CellsPar.scala:207:15") // RegWrite(x35922,x35944,x35948)
    val x35949_x35922_d1 = WriteMem(x35922_d1, x35944).name("x35949_x35922_d1").ctrl(x35950).srcCtx("CellsPar.scala:207:15") // RegWrite(x35922,x35944,x35948)
    val x35984 = UnitController(style=SeqPipe, level=InnerControl).name("x35984").ctrl(x35985).srcCtx("CellsPar.scala:194:42") // UnitPipe(List(b23517, b23513, b23469, b23470, b22364),Block(Const(())))
    val x35951 = OpDef(op=FixAdd, inputs=List(b23468, b23516)).name("x35951").ctrl(x35984).srcCtx("CellsPar.scala:209:31:colOffset") // FixAdd(b23468,b23516)
    val x35952 = ReadMem(x35922_d0).name("x35952").ctrl(x35984).srcCtx("CellsPar.scala:210:28") // RegRead(x35922)
    val x35953 = OpDef(op=FixEql, inputs=List(b23467, Const(0))).name("x35953").ctrl(x35984).srcCtx("CellsPar.scala:210:42") // FixEql(b23467,Const(0))
    val x35954 = OpDef(op=BitAnd, inputs=List(b23517, b23513)).name("x35954").ctrl(x35984).srcCtx("UnrollingBase.scala:28:66") // And(b23517,b23513)
    val x35955 = OpDef(op=BitAnd, inputs=List(b23469, b23470)).name("x35955").ctrl(x35984).srcCtx("UnrollingBase.scala:28:66") // And(b23469,b23470)
    val x35956 = OpDef(op=BitAnd, inputs=List(x35954, x35955)).name("x35956").ctrl(x35984).srcCtx("UnrollingBase.scala:28:66") // And(x35954,x35955)
    val x35957 = OpDef(op=BitAnd, inputs=List(x35956, b22364)).name("x35957").ctrl(x35984).srcCtx("UnrollingBase.scala:28:66") // And(x35956,b22364)
    val x35958 = LoadBanks(List(x34771_d3_b0), List(Const(0), x35951)).name("x35958").ctrl(x35984).srcCtx("CellsPar.scala:210:52") // SRAMLoad(x34771,ArrayBuffer(Const(1), Const(2048)),List(Const(0), x35951),Const(0),x35957)
    val x35959 = LoadBanks(List(x34767_d1_b0), List(b23512, x35951)).name("x35959").ctrl(x35984).srcCtx("CellsPar.scala:210:91") // SRAMLoad(x34767,ArrayBuffer(Const(1), Const(512)),List(b23512, x35951),Const(0),x35957)
    val x35960 = OpDef(op=MuxOp, inputs=List(x35953, x35958, x35959)).name("x35960").ctrl(x35984).srcCtx("CellsPar.scala:210:39") // Mux(x35953,x35958,x35959)
    val x35961 = OpDef(op=FixAdd, inputs=List(x35952, x35960)).name("x35961").ctrl(x35984).srcCtx("CellsPar.scala:210:34:ele") // FixAdd(x35952,x35960)
    val x35962 = OpDef(op=FixLeq, inputs=List(Const(1520), b23467)).name("x35962").ctrl(x35984).srcCtx("CellsPar.scala:218:46") // FixLeq(Const(1520),b23467)
    // x35963 = FixConvert(Const(0),TRUE,_8,_24) x.tp=FixPt[TRUE,_32,_0] {
    val x35963_int1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("01111111111111111111111111111111"))).name("x35963_int1").ctrl(x35984).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x35963_int2 = OpDef(op=FixSla, inputs=List(x35963_int1, Const(24))).name("x35963_int2").ctrl(x35984).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x35963_frac1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("00000000000000000000000000000000"))).name("x35963_frac1").ctrl(x35984).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x35963_frac2 = OpDef(op=FixSla, inputs=List(x35963_frac1, Const(24))).name("x35963_frac2").ctrl(x35984).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x35963 = OpDef(op=BitOr, inputs=List(x35963_int2, x35963_frac2)).name("x35963").ctrl(x35984).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    // }
    val x35964 = OpDef(op=FixAdd, inputs=List(x35961, x35963)).name("x35964").ctrl(x35984).srcCtx("CellsPar.scala:218:87") // FixAdd(x35961,x35963)
    val x35965 = OpDef(op=FixSra, inputs=List(x35964, Const(1))).name("x35965").ctrl(x35984).srcCtx("CellsPar.scala:29:22") // FixRsh(x35964,Const(1))
    val x35966 = x35965 // FixConvert(x35965,TRUE,_8,_24) (Same Type. No op)
    val x35967 = OpDef(op=FixAbs, inputs=List(x35966)).name("x35967").ctrl(x35984).srcCtx("CellsPar.scala:37:19:absp") // FixAbs(x35966)
    val x35968 = OpDef(op=FixLt, inputs=List(Const(2.5), x35967)).name("x35968").ctrl(x35984).srcCtx("CellsPar.scala:51:23:cond1") // FixLt(Const(2.5),x35967)
    val x35969 = OpDef(op=FixLt, inputs=List(Const(0.5), x35967)).name("x35969").ctrl(x35984).srcCtx("CellsPar.scala:54:35") // FixLt(Const(0.5),x35967)
    val x35970 = OpDef(op=FixLeq, inputs=List(x35967, Const(2.5))).name("x35970").ctrl(x35984).srcCtx("CellsPar.scala:54:52") // FixLeq(x35967,Const(2.5))
    val x35971 = OpDef(op=BitAnd, inputs=List(x35969, x35970)).name("x35971").ctrl(x35984).srcCtx("CellsPar.scala:54:43:cond2") // And(x35969,x35970)
    val x35972 = OpDef(op=FixSra, inputs=List(x35967, Const(2))).name("x35972").ctrl(x35984).srcCtx("CellsPar.scala:56:21:div4") // FixRsh(x35967,Const(2))
    val x35973 = OpDef(op=FixAdd, inputs=List(x35972, Const(0.375))).name("x35973").ctrl(x35984).srcCtx("CellsPar.scala:57:27:div4Offset") // FixAdd(x35972,Const(0.375))
    val x35974 = OpDef(op=MuxOp, inputs=List(x35971, x35973, x35967)).name("x35974").ctrl(x35984).srcCtx("CellsPar.scala:58:20:body2") // Mux(x35971,x35973,x35967)
    val x35975 = OpDef(op=MuxOp, inputs=List(x35968, Const(1.0), x35974)).name("x35975").ctrl(x35984).srcCtx("CellsPar.scala:60:20:absre") // Mux(x35968,Const(1),x35974)
    val x35976 = OpDef(op=FixNeg, inputs=List(x35975)).name("x35976").ctrl(x35984).srcCtx("CellsPar.scala:62:29:mabre") // FixNeg(x35975)
    val x35977 = OpDef(op=FixLt, inputs=List(x35966, Const(0.0))).name("x35977").ctrl(x35984).srcCtx("CellsPar.scala:63:22") // FixLt(x35966,Const(0))
    val x35978 = OpDef(op=MuxOp, inputs=List(x35977, x35976, x35975)).name("x35978").ctrl(x35984).srcCtx("CellsPar.scala:63:17:re") // Mux(x35977,x35976,x35975)
    val x35979 = x35978 // FixConvert(x35978,TRUE,_8,_24) (Same Type. No op)
    val x35980 = OpDef(op=FixAdd, inputs=List(x35979, Const(1.0))).name("x35980").ctrl(x35984).srcCtx("CellsPar.scala:29:33") // FixAdd(x35979,Const(1))
    val x35981 = OpDef(op=FixSra, inputs=List(x35980, Const(1))).name("x35981").ctrl(x35984).srcCtx("CellsPar.scala:29:44") // FixRsh(x35980,Const(1))
    val x35982 = OpDef(op=MuxOp, inputs=List(x35962, x35981, x35961)).name("x35982").ctrl(x35984).srcCtx("CellsPar.scala:218:43") // Mux(x35962,x35981,x35961)
    val x35983 = StoreBanks(List(x34767_d0_b0, x34767_d1_b0), List(b23512, x35951), x35982).name("x35983").ctrl(x35984).srcCtx("CellsPar.scala:218:38") // SRAMStore(x34767,ArrayBuffer(Const(1), Const(512)),List(b23512, x35951),Const(0),x35982,x35957)
    val x35988 = Counter(min=Const(0), max=Const(512), step=Const(16), par=1).name("x35988").ctrl(x36822).srcCtx("CellsPar.scala:190:58") // CounterNew(Const(0),Const(512),Const(16),Const(1))
    val x35989 = Counter(min=Const(0), max=Const(1536), step=Const(16), par=1).name("x35989").ctrl(x36822).srcCtx("CellsPar.scala:190:33") // CounterNew(Const(0),Const(1536),Const(16),Const(1))
    val x35990 = CounterChain(List(x35989,x35988)).name("x35990").ctrl(x36822).srcCtx("CellsPar.scala:190:72") // CounterChainNew(List(x35989, x35988))
    val x36093 = LoopController(style=MetaPipe, level=OuterControl, cchain=x35990).name("x36093").ctrl(x36822).srcCtx("CellsPar.scala:190:72") // UnrolledForeach(List(b22364),x35990,Block(Const(())),List(List(b23589), List(b23590)),List(List(b23591), List(b23592)))
    val b23589 = CounterIter(x35989, Some(0)).name("b23589").ctrl(x36093) // b23589
    val b23591 = Const(true).name("b23591").ctrl(x36093) // b23591
    val b23590 = CounterIter(x35988, Some(0)).name("b23590").ctrl(x36093) // b23590
    val b23592 = Const(true).name("b23592").ctrl(x36093) // b23592
    val x35991_d0_b0 = SRAM(size=256, banking=Strided(banks=1, stride=16)).name("x35991_d0_b0").ctrl(x36093).srcCtx("CellsPar.scala:191:33:tileKernel") // x35991 = SRAMNew(ArrayBuffer(Const(16), Const(16)))
    isAccum(x35991_d0_b0) = false
    bufferDepthOf(x35991_d0_b0) = 2
    val x35994 = UnitController(style=SeqPipe, level=InnerControl).name("x35994").ctrl(x36093).srcCtx("CellsPar.scala:190:72") // UnitPipe(List(b23591, b23592, b22364),Block(Const(())))
    val x35992 = OpDef(op=FixAdd, inputs=List(b23589, Const(16))).name("x35992").ctrl(x35994).srcCtx("CellsPar.scala:192:36") // FixAdd(b23589,Const(16))
    val x35993 = OpDef(op=FixAdd, inputs=List(b23590, Const(16))).name("x35993").ctrl(x35994).srcCtx("CellsPar.scala:192:45") // FixAdd(b23590,Const(16))
    val x35995 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x35995").ctrl(x36093).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x35996 = CounterChain(List(x35995)).name("x35996").ctrl(x36093).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x35995))
    val x36025 = LoopController(style=StreamPipe, level=OuterControl, cchain=x35996).name("x36025").ctrl(x36093).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b23591, b23592, b22364),x35996,Block(Const(())),List(List(b23599)),List(List(b23600)))
    val b23599 = CounterIter(x35995, Some(0)).name("b23599").ctrl(x36025) // b23599
    val b23600 = Const(true).name("b23600").ctrl(x36025) // b23600
    val b36945 = StreamOut(field="offset").name("b36945").ctrl(x36025).srcCtx("CellsPar.scala:192:20") // x35997 = StreamOutNew(BurstCmdBus)
    isAccum(b36945) = false
    bufferDepthOf(b36945) = 1
    val b36946 = StreamOut(field="size").name("b36946").ctrl(x36025).srcCtx("CellsPar.scala:192:20") // x35997 = StreamOutNew(BurstCmdBus)
    isAccum(b36946) = false
    bufferDepthOf(b36946) = 1
    val x35998 = StreamIn(field="data").name("x35998").ctrl(x36025).srcCtx("CellsPar.scala:192:20") // x35998 = StreamInNew(BurstDataBus())
    isAccum(x35998) = false
    bufferDepthOf(x35998) = 1
    val x36013 = UnitController(style=SeqPipe, level=InnerControl).name("x36013").ctrl(x36025).srcCtx("CellsPar.scala:192:20") // UnitPipe(List(b23600, b23591, b23592, b22364),Block(x36012))
    val x35999 = OpDef(op=FixAdd, inputs=List(b23589, b23599)).name("x35999").ctrl(x36013).srcCtx("CellsPar.scala:192:20") // FixAdd(b23589,b23599)
    val x36000 = x35999 // FixConvert(x35999,TRUE,_32,_0) (Same Type. No op)
    val x36001 = OpDef(op=FixSla, inputs=List(x36000, Const(9))).name("x36001").ctrl(x36013).srcCtx("CellsPar.scala:192:20") // FixLsh(x36000,Const(9))
    val x36002 = b23590 // FixConvert(b23590,TRUE,_32,_0) (Same Type. No op)
    val x36003 = OpDef(op=FixAdd, inputs=List(x36001, x36002)).name("x36003").ctrl(x36013).srcCtx("CellsPar.scala:192:20") // FixAdd(x36001,x36002)
    val x36004 = OpDef(op=FixSla, inputs=List(x36003, Const(2))).name("x36004").ctrl(x36013).srcCtx("CellsPar.scala:192:20") // FixLsh(x36003,Const(2))
    val x36005 = x36004 // FixConvert(x36004,TRUE,_64,_0)
    val x36006 = DramAddress(x34627).name("x36006").ctrl(x36013).srcCtx("CellsPar.scala:192:20") // GetDRAMAddress(x34627)
    val x36008_x36007 = OpDef(op=FixAdd, inputs=List(x36005, x36006)).name("x36008_x36007").ctrl(x36013).srcCtx("CellsPar.scala:192:20") // FixAdd(x36005,x36006)
    // x36008 = SimpleStruct(ArrayBuffer((offset,x36007), (size,Const(64)), (isLoad,Const(true))))
    val x36009 = OpDef(op=BitAnd, inputs=List(b23600, b23591)).name("x36009").ctrl(x36013).srcCtx("UnrollingBase.scala:28:66") // And(b23600,b23591)
    val x36010 = OpDef(op=BitAnd, inputs=List(b23592, b22364)).name("x36010").ctrl(x36013).srcCtx("UnrollingBase.scala:28:66") // And(b23592,b22364)
    val x36011 = OpDef(op=BitAnd, inputs=List(x36009, x36010)).name("x36011").ctrl(x36013).srcCtx("UnrollingBase.scala:28:66") // And(x36009,x36010)
    val x36012_b36947_b36945 = WriteMem(b36945, x36008_x36007).name("x36012_b36947_b36945").ctrl(x36013).srcCtx("CellsPar.scala:192:20") // StreamWrite(x35997,x36008,x36011)
    val x36012_b36948_b36946 = WriteMem(b36946, Const(64)).name("x36012_b36948_b36946").ctrl(x36013).srcCtx("CellsPar.scala:192:20") // StreamWrite(x35997,x36008,x36011)
    val x36014 = FringeDenseLoad(dram=List(x34627), cmdStream=List(b36945, b36946), dataStream=List(x35998)).name("x36014").ctrl(x36025).srcCtx("CellsPar.scala:192:20") // FringeDenseLoad(x34627,x35997,x35998)
    val x36015 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x36015").ctrl(x36025).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x36016 = CounterChain(List(x36015)).name("x36016").ctrl(x36025).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x36015))
    val x36024 = LoopController(style=InnerPipe, level=InnerControl, cchain=x36016).name("x36024").ctrl(x36025).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b23600, b23591, b23592, b22364),x36016,Block(Const(())),List(List(b23621)),List(List(b23622)))
    val b23621 = CounterIter(x36015, None).name("b23621").ctrl(x36024) // b23621
    val b23622 = Const(true).name("b23622").ctrl(x36024) // b23622
    val x36017 = OpDef(op=BitAnd, inputs=List(b23622, b23600)).name("x36017").ctrl(x36024).srcCtx("UnrollingBase.scala:28:66") // And(b23622,b23600)
    val x36018 = OpDef(op=BitAnd, inputs=List(b23591, b23592)).name("x36018").ctrl(x36024).srcCtx("UnrollingBase.scala:28:66") // And(b23591,b23592)
    val x36019 = OpDef(op=BitAnd, inputs=List(x36017, x36018)).name("x36019").ctrl(x36024).srcCtx("UnrollingBase.scala:28:66") // And(x36017,x36018)
    val x36020 = OpDef(op=BitAnd, inputs=List(x36019, b22364)).name("x36020").ctrl(x36024).srcCtx("UnrollingBase.scala:28:66") // And(x36019,b22364)
    val x36021_x36021 = ReadMem(x35998).name("x36021_x36021").ctrl(x36024).srcCtx("CellsPar.scala:192:20") // ParStreamRead(x35998,List(x36020))
    val x36022_x36022 = x36021_x36021 // x36022 = VectorApply(x36021,0)
    val x36023 = StoreBanks(List(x35991_d0_b0), List(b23599, b23621), x36022_x36022).name("x36023").ctrl(x36024).srcCtx("CellsPar.scala:192:20") // ParSRAMStore(x35991,List(List(b23599, b23621)),List(x36022),List(x36020))
    val x36026 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x36026").ctrl(x36093).srcCtx("CellsPar.scala:193:34") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x36027 = CounterChain(List(x36026)).name("x36027").ctrl(x36093).srcCtx("CellsPar.scala:193:46") // CounterChainNew(List(x36026))
    val x36092 = LoopController(style=MetaPipe, level=OuterControl, cchain=x36027).name("x36092").ctrl(x36093).srcCtx("CellsPar.scala:193:46") // UnrolledForeach(List(b23591, b23592, b22364),x36027,Block(Const(())),List(List(b23634)),List(List(b23635)))
    val b23634 = CounterIter(x36026, Some(0)).name("b23634").ctrl(x36092) // b23634
    val b23635 = Const(true).name("b23635").ctrl(x36092) // b23635
    val x36028 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x36028").ctrl(x36092).srcCtx("CellsPar.scala:194:28") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x36029 = CounterChain(List(x36028)).name("x36029").ctrl(x36092).srcCtx("CellsPar.scala:194:42") // CounterChainNew(List(x36028))
    val x36091 = LoopController(style=MetaPipe, level=OuterControl, cchain=x36029).name("x36091").ctrl(x36092).srcCtx("CellsPar.scala:194:42") // UnrolledForeach(List(b23635, b23591, b23592, b22364),x36029,Block(Const(())),List(List(b23638)),List(List(b23639)))
    val b23638 = CounterIter(x36028, Some(0)).name("b23638").ctrl(x36091) // b23638
    val b23639 = Const(true).name("b23639").ctrl(x36091) // b23639
    val x36030_d0 = Reg(init=Some(0.0)).name("x36030_d0").ctrl(x36091).srcCtx("CellsPar.scala:195:34:prod") // x36030 = RegNew(Const(0))
    isAccum(x36030_d0) = false
    bufferDepthOf(x36030_d0) = 2
    val x36030_d1 = Reg(init=Some(0.0)).name("x36030_d1").ctrl(x36091).srcCtx("CellsPar.scala:195:34:prod") // x36030 = RegNew(Const(0))
    isAccum(x36030_d1) = true
    bufferDepthOf(x36030_d1) = 1
    val x36031 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x36031").ctrl(x36091).srcCtx("CellsPar.scala:195:43") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x36032 = CounterChain(List(x36031)).name("x36032").ctrl(x36091).srcCtx("CellsPar.scala:207:15") // CounterChainNew(List(x36031))
    val x36058 = LoopController(style=InnerPipe, level=InnerControl, cchain=x36032).name("x36058").ctrl(x36091).srcCtx("CellsPar.scala:207:15") // UnrolledReduce(List(b23639, b23635, b23591, b23592, b22364),x36032,x36030,Block((x36030) => Const(())),List(List(b23643)),List(List(b23644)))
    val b23643 = CounterIter(x36031, None).name("b23643").ctrl(x36058) // b23643
    val b23644 = Const(true).name("b23644").ctrl(x36058) // b23644
    val x36033 = OpDef(op=FixAdd, inputs=List(b23589, b23643)).name("x36033").ctrl(x36058).srcCtx("CellsPar.scala:196:42:reduce_size_offset") // FixAdd(b23589,b23643)
    val x36034 = OpDef(op=BitAnd, inputs=List(b23644, b23639)).name("x36034").ctrl(x36058).srcCtx("UnrollingBase.scala:28:66") // And(b23644,b23639)
    val x36035 = OpDef(op=BitAnd, inputs=List(b23635, b23591)).name("x36035").ctrl(x36058).srcCtx("UnrollingBase.scala:28:66") // And(b23635,b23591)
    val x36036 = OpDef(op=BitAnd, inputs=List(b23592, b22364)).name("x36036").ctrl(x36058).srcCtx("UnrollingBase.scala:28:66") // And(b23592,b22364)
    val x36037 = OpDef(op=BitAnd, inputs=List(x36034, x36035)).name("x36037").ctrl(x36058).srcCtx("UnrollingBase.scala:28:66") // And(x36034,x36035)
    val x36038 = OpDef(op=BitAnd, inputs=List(x36037, x36036)).name("x36038").ctrl(x36058).srcCtx("UnrollingBase.scala:28:66") // And(x36037,x36036)
    val x36039 = LoadBanks(List(x35991_d0_b0), List(b23643, b23638)).name("x36039").ctrl(x36058).srcCtx("CellsPar.scala:203:34:tk") // ParSRAMLoad(x35991,List(List(b23643, b23638)),List(x36038))
    val x36040 = x36039 // x36040 = VectorApply(x36039,0)
    val x36041 = LoadBanks(List(x34758_d3_b0), List(b23634, x36033)).name("x36041").ctrl(x36058).srcCtx("CellsPar.scala:204:28") // ParSRAMLoad(x34758,List(List(b23634, x36033)),List(x36038))
    val x36042 = x36041 // x36042 = VectorApply(x36041,0)
    val x36043 = OpDef(op=FixMul, inputs=List(x36042, x36040)).name("x36043").ctrl(x36058).srcCtx("CellsPar.scala:204:52:tempA") // FixMul(x36042,x36040)
    val x36044 = OpDef(op=FixSub, inputs=List(x36033, Const(512))).name("x36044").ctrl(x36058).srcCtx("CellsPar.scala:205:51") // FixSub(x36033,Const(512))
    val x36045 = LoadBanks(List(x34765_d7_b0), List(b23634, x36044)).name("x36045").ctrl(x36058).srcCtx("CellsPar.scala:205:28") // ParSRAMLoad(x34765,List(List(b23634, x36044)),List(x36038))
    val x36046 = x36045 // x36046 = VectorApply(x36045,0)
    val x36047 = OpDef(op=FixMul, inputs=List(x36046, x36040)).name("x36047").ctrl(x36058).srcCtx("CellsPar.scala:205:67:tempB") // FixMul(x36046,x36040)
    val x36048 = OpDef(op=FixLt, inputs=List(x36033, Const(512))).name("x36048").ctrl(x36058).srcCtx("CellsPar.scala:206:38") // FixLt(x36033,Const(512))
    val x36049 = OpDef(op=MuxOp, inputs=List(x36048, x36043, x36047)).name("x36049").ctrl(x36058).srcCtx("CellsPar.scala:206:18") // Mux(x36048,x36043,x36047)
    val x36050 = ReadMem(x36030_d1).name("x36050").ctrl(x36058).srcCtx("CellsPar.scala:207:15") // RegRead(x36030)
    val x36051 = OpDef(op=FixEql, inputs=List(b23643, Const(0))).name("x36051").ctrl(x36058).srcCtx("CellsPar.scala:207:15") // FixEql(b23643,Const(0))
    val x36052 = ReduceAccumOp(op=FixAdd, input=x36049, accum=x36050).name("x36052").ctrl(x36058).srcCtx("CellsPar.scala:207:17") // FixAdd(x36049,x36050)
    val x36053 = OpDef(op=BitAnd, inputs=List(b23639, b23635)).name("x36053").ctrl(x36058).srcCtx("UnrollingBase.scala:28:66") // And(b23639,b23635)
    val x36054 = OpDef(op=BitAnd, inputs=List(b23591, b23592)).name("x36054").ctrl(x36058).srcCtx("UnrollingBase.scala:28:66") // And(b23591,b23592)
    val x36055 = OpDef(op=BitAnd, inputs=List(x36053, x36054)).name("x36055").ctrl(x36058).srcCtx("UnrollingBase.scala:28:66") // And(x36053,x36054)
    val x36056 = OpDef(op=BitAnd, inputs=List(x36055, b22364)).name("x36056").ctrl(x36058).srcCtx("UnrollingBase.scala:28:66") // And(x36055,b22364)
    val x36057_x36030_d0 = WriteMem(x36030_d0, x36052).name("x36057_x36030_d0").ctrl(x36058).srcCtx("CellsPar.scala:207:15") // RegWrite(x36030,x36052,x36056)
    val x36057_x36030_d1 = WriteMem(x36030_d1, x36052).name("x36057_x36030_d1").ctrl(x36058).srcCtx("CellsPar.scala:207:15") // RegWrite(x36030,x36052,x36056)
    val x36090 = UnitController(style=SeqPipe, level=InnerControl).name("x36090").ctrl(x36091).srcCtx("CellsPar.scala:194:42") // UnitPipe(List(b23639, b23635, b23591, b23592, b22364),Block(Const(())))
    val x36059 = OpDef(op=FixAdd, inputs=List(b23590, b23638)).name("x36059").ctrl(x36090).srcCtx("CellsPar.scala:209:31:colOffset") // FixAdd(b23590,b23638)
    val x36060 = ReadMem(x36030_d0).name("x36060").ctrl(x36090).srcCtx("CellsPar.scala:210:28") // RegRead(x36030)
    val x36061 = OpDef(op=FixEql, inputs=List(b23589, Const(0))).name("x36061").ctrl(x36090).srcCtx("CellsPar.scala:210:42") // FixEql(b23589,Const(0))
    val x36062 = OpDef(op=FixAdd, inputs=List(x36059, Const(512))).name("x36062").ctrl(x36090).srcCtx("CellsPar.scala:210:66") // FixAdd(x36059,Const(512))
    val x36063 = OpDef(op=BitAnd, inputs=List(b23639, b23635)).name("x36063").ctrl(x36090).srcCtx("UnrollingBase.scala:28:66") // And(b23639,b23635)
    val x36064 = OpDef(op=BitAnd, inputs=List(b23591, b23592)).name("x36064").ctrl(x36090).srcCtx("UnrollingBase.scala:28:66") // And(b23591,b23592)
    val x36065 = OpDef(op=BitAnd, inputs=List(x36063, x36064)).name("x36065").ctrl(x36090).srcCtx("UnrollingBase.scala:28:66") // And(x36063,x36064)
    val x36066 = OpDef(op=BitAnd, inputs=List(x36065, b22364)).name("x36066").ctrl(x36090).srcCtx("UnrollingBase.scala:28:66") // And(x36065,b22364)
    val x36067 = LoadBanks(List(x34771_d2_b0), List(Const(0), x36062)).name("x36067").ctrl(x36090).srcCtx("CellsPar.scala:210:52") // SRAMLoad(x34771,ArrayBuffer(Const(1), Const(2048)),List(Const(0), x36062),Const(0),x36066)
    val x36068 = LoadBanks(List(x34768_d1_b0), List(b23634, x36059)).name("x36068").ctrl(x36090).srcCtx("CellsPar.scala:210:91") // SRAMLoad(x34768,ArrayBuffer(Const(1), Const(512)),List(b23634, x36059),Const(0),x36066)
    val x36069 = OpDef(op=MuxOp, inputs=List(x36061, x36067, x36068)).name("x36069").ctrl(x36090).srcCtx("CellsPar.scala:210:39") // Mux(x36061,x36067,x36068)
    val x36070 = OpDef(op=FixAdd, inputs=List(x36060, x36069)).name("x36070").ctrl(x36090).srcCtx("CellsPar.scala:210:34:ele") // FixAdd(x36060,x36069)
    val x36071 = OpDef(op=FixLeq, inputs=List(Const(1520), b23589)).name("x36071").ctrl(x36090).srcCtx("CellsPar.scala:218:46") // FixLeq(Const(1520),b23589)
    // x36072 = FixConvert(Const(0),TRUE,_8,_24) x.tp=FixPt[TRUE,_32,_0] {
    val x36072_int1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("01111111111111111111111111111111"))).name("x36072_int1").ctrl(x36090).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x36072_int2 = OpDef(op=FixSla, inputs=List(x36072_int1, Const(24))).name("x36072_int2").ctrl(x36090).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x36072_frac1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("00000000000000000000000000000000"))).name("x36072_frac1").ctrl(x36090).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x36072_frac2 = OpDef(op=FixSla, inputs=List(x36072_frac1, Const(24))).name("x36072_frac2").ctrl(x36090).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x36072 = OpDef(op=BitOr, inputs=List(x36072_int2, x36072_frac2)).name("x36072").ctrl(x36090).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    // }
    val x36073 = OpDef(op=FixAdd, inputs=List(x36070, x36072)).name("x36073").ctrl(x36090).srcCtx("CellsPar.scala:218:87") // FixAdd(x36070,x36072)
    val x36074 = x36073 // FixConvert(x36073,TRUE,_8,_24) (Same Type. No op)
    val x36075 = OpDef(op=FixAbs, inputs=List(x36074)).name("x36075").ctrl(x36090).srcCtx("CellsPar.scala:37:19:absp") // FixAbs(x36074)
    val x36076 = OpDef(op=FixLt, inputs=List(Const(2.5), x36075)).name("x36076").ctrl(x36090).srcCtx("CellsPar.scala:51:23:cond1") // FixLt(Const(2.5),x36075)
    val x36077 = OpDef(op=FixLt, inputs=List(Const(0.5), x36075)).name("x36077").ctrl(x36090).srcCtx("CellsPar.scala:54:35") // FixLt(Const(0.5),x36075)
    val x36078 = OpDef(op=FixLeq, inputs=List(x36075, Const(2.5))).name("x36078").ctrl(x36090).srcCtx("CellsPar.scala:54:52") // FixLeq(x36075,Const(2.5))
    val x36079 = OpDef(op=BitAnd, inputs=List(x36077, x36078)).name("x36079").ctrl(x36090).srcCtx("CellsPar.scala:54:43:cond2") // And(x36077,x36078)
    val x36080 = OpDef(op=FixSra, inputs=List(x36075, Const(2))).name("x36080").ctrl(x36090).srcCtx("CellsPar.scala:56:21:div4") // FixRsh(x36075,Const(2))
    val x36081 = OpDef(op=FixAdd, inputs=List(x36080, Const(0.375))).name("x36081").ctrl(x36090).srcCtx("CellsPar.scala:57:27:div4Offset") // FixAdd(x36080,Const(0.375))
    val x36082 = OpDef(op=MuxOp, inputs=List(x36079, x36081, x36075)).name("x36082").ctrl(x36090).srcCtx("CellsPar.scala:58:20:body2") // Mux(x36079,x36081,x36075)
    val x36083 = OpDef(op=MuxOp, inputs=List(x36076, Const(1.0), x36082)).name("x36083").ctrl(x36090).srcCtx("CellsPar.scala:60:20:absre") // Mux(x36076,Const(1),x36082)
    val x36084 = OpDef(op=FixNeg, inputs=List(x36083)).name("x36084").ctrl(x36090).srcCtx("CellsPar.scala:62:29:mabre") // FixNeg(x36083)
    val x36085 = OpDef(op=FixLt, inputs=List(x36074, Const(0.0))).name("x36085").ctrl(x36090).srcCtx("CellsPar.scala:63:22") // FixLt(x36074,Const(0))
    val x36086 = OpDef(op=MuxOp, inputs=List(x36085, x36084, x36083)).name("x36086").ctrl(x36090).srcCtx("CellsPar.scala:63:17:re") // Mux(x36085,x36084,x36083)
    val x36087 = x36086 // FixConvert(x36086,TRUE,_8,_24) (Same Type. No op)
    val x36088 = OpDef(op=MuxOp, inputs=List(x36071, x36087, x36070)).name("x36088").ctrl(x36090).srcCtx("CellsPar.scala:218:43") // Mux(x36071,x36087,x36070)
    val x36089 = StoreBanks(List(x34768_d0_b0, x34768_d1_b0), List(b23634, x36059), x36088).name("x36089").ctrl(x36090).srcCtx("CellsPar.scala:218:38") // SRAMStore(x34768,ArrayBuffer(Const(1), Const(512)),List(b23634, x36059),Const(0),x36088,x36066)
    val x36094 = Counter(min=Const(0), max=Const(512), step=Const(16), par=1).name("x36094").ctrl(x36822).srcCtx("CellsPar.scala:190:58") // CounterNew(Const(0),Const(512),Const(16),Const(1))
    val x36095 = Counter(min=Const(0), max=Const(1536), step=Const(16), par=1).name("x36095").ctrl(x36822).srcCtx("CellsPar.scala:190:33") // CounterNew(Const(0),Const(1536),Const(16),Const(1))
    val x36096 = CounterChain(List(x36095,x36094)).name("x36096").ctrl(x36822).srcCtx("CellsPar.scala:190:72") // CounterChainNew(List(x36095, x36094))
    val x36202 = LoopController(style=MetaPipe, level=OuterControl, cchain=x36096).name("x36202").ctrl(x36822).srcCtx("CellsPar.scala:190:72") // UnrolledForeach(List(b22364),x36096,Block(Const(())),List(List(b23709), List(b23710)),List(List(b23711), List(b23712)))
    val b23709 = CounterIter(x36095, Some(0)).name("b23709").ctrl(x36202) // b23709
    val b23711 = Const(true).name("b23711").ctrl(x36202) // b23711
    val b23710 = CounterIter(x36094, Some(0)).name("b23710").ctrl(x36202) // b23710
    val b23712 = Const(true).name("b23712").ctrl(x36202) // b23712
    val x36097_d0_b0 = SRAM(size=256, banking=Strided(banks=1, stride=16)).name("x36097_d0_b0").ctrl(x36202).srcCtx("CellsPar.scala:191:33:tileKernel") // x36097 = SRAMNew(ArrayBuffer(Const(16), Const(16)))
    isAccum(x36097_d0_b0) = false
    bufferDepthOf(x36097_d0_b0) = 2
    val x36100 = UnitController(style=SeqPipe, level=InnerControl).name("x36100").ctrl(x36202).srcCtx("CellsPar.scala:190:72") // UnitPipe(List(b23711, b23712, b22364),Block(Const(())))
    val x36098 = OpDef(op=FixAdd, inputs=List(b23709, Const(16))).name("x36098").ctrl(x36100).srcCtx("CellsPar.scala:192:36") // FixAdd(b23709,Const(16))
    val x36099 = OpDef(op=FixAdd, inputs=List(b23710, Const(16))).name("x36099").ctrl(x36100).srcCtx("CellsPar.scala:192:45") // FixAdd(b23710,Const(16))
    val x36101 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x36101").ctrl(x36202).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x36102 = CounterChain(List(x36101)).name("x36102").ctrl(x36202).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x36101))
    val x36131 = LoopController(style=StreamPipe, level=OuterControl, cchain=x36102).name("x36131").ctrl(x36202).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b23711, b23712, b22364),x36102,Block(Const(())),List(List(b23719)),List(List(b23720)))
    val b23719 = CounterIter(x36101, Some(0)).name("b23719").ctrl(x36131) // b23719
    val b23720 = Const(true).name("b23720").ctrl(x36131) // b23720
    val b36949 = StreamOut(field="offset").name("b36949").ctrl(x36131).srcCtx("CellsPar.scala:192:20") // x36103 = StreamOutNew(BurstCmdBus)
    isAccum(b36949) = false
    bufferDepthOf(b36949) = 1
    val b36950 = StreamOut(field="size").name("b36950").ctrl(x36131).srcCtx("CellsPar.scala:192:20") // x36103 = StreamOutNew(BurstCmdBus)
    isAccum(b36950) = false
    bufferDepthOf(b36950) = 1
    val x36104 = StreamIn(field="data").name("x36104").ctrl(x36131).srcCtx("CellsPar.scala:192:20") // x36104 = StreamInNew(BurstDataBus())
    isAccum(x36104) = false
    bufferDepthOf(x36104) = 1
    val x36119 = UnitController(style=SeqPipe, level=InnerControl).name("x36119").ctrl(x36131).srcCtx("CellsPar.scala:192:20") // UnitPipe(List(b23720, b23711, b23712, b22364),Block(x36118))
    val x36105 = OpDef(op=FixAdd, inputs=List(b23709, b23719)).name("x36105").ctrl(x36119).srcCtx("CellsPar.scala:192:20") // FixAdd(b23709,b23719)
    val x36106 = x36105 // FixConvert(x36105,TRUE,_32,_0) (Same Type. No op)
    val x36107 = OpDef(op=FixSla, inputs=List(x36106, Const(9))).name("x36107").ctrl(x36119).srcCtx("CellsPar.scala:192:20") // FixLsh(x36106,Const(9))
    val x36108 = b23710 // FixConvert(b23710,TRUE,_32,_0) (Same Type. No op)
    val x36109 = OpDef(op=FixAdd, inputs=List(x36107, x36108)).name("x36109").ctrl(x36119).srcCtx("CellsPar.scala:192:20") // FixAdd(x36107,x36108)
    val x36110 = OpDef(op=FixSla, inputs=List(x36109, Const(2))).name("x36110").ctrl(x36119).srcCtx("CellsPar.scala:192:20") // FixLsh(x36109,Const(2))
    val x36111 = x36110 // FixConvert(x36110,TRUE,_64,_0)
    val x36112 = DramAddress(x34628).name("x36112").ctrl(x36119).srcCtx("CellsPar.scala:192:20") // GetDRAMAddress(x34628)
    val x36114_x36113 = OpDef(op=FixAdd, inputs=List(x36111, x36112)).name("x36114_x36113").ctrl(x36119).srcCtx("CellsPar.scala:192:20") // FixAdd(x36111,x36112)
    // x36114 = SimpleStruct(ArrayBuffer((offset,x36113), (size,Const(64)), (isLoad,Const(true))))
    val x36115 = OpDef(op=BitAnd, inputs=List(b23720, b23711)).name("x36115").ctrl(x36119).srcCtx("UnrollingBase.scala:28:66") // And(b23720,b23711)
    val x36116 = OpDef(op=BitAnd, inputs=List(b23712, b22364)).name("x36116").ctrl(x36119).srcCtx("UnrollingBase.scala:28:66") // And(b23712,b22364)
    val x36117 = OpDef(op=BitAnd, inputs=List(x36115, x36116)).name("x36117").ctrl(x36119).srcCtx("UnrollingBase.scala:28:66") // And(x36115,x36116)
    val x36118_b36951_b36949 = WriteMem(b36949, x36114_x36113).name("x36118_b36951_b36949").ctrl(x36119).srcCtx("CellsPar.scala:192:20") // StreamWrite(x36103,x36114,x36117)
    val x36118_b36952_b36950 = WriteMem(b36950, Const(64)).name("x36118_b36952_b36950").ctrl(x36119).srcCtx("CellsPar.scala:192:20") // StreamWrite(x36103,x36114,x36117)
    val x36120 = FringeDenseLoad(dram=List(x34628), cmdStream=List(b36949, b36950), dataStream=List(x36104)).name("x36120").ctrl(x36131).srcCtx("CellsPar.scala:192:20") // FringeDenseLoad(x34628,x36103,x36104)
    val x36121 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x36121").ctrl(x36131).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x36122 = CounterChain(List(x36121)).name("x36122").ctrl(x36131).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x36121))
    val x36130 = LoopController(style=InnerPipe, level=InnerControl, cchain=x36122).name("x36130").ctrl(x36131).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b23720, b23711, b23712, b22364),x36122,Block(Const(())),List(List(b23741)),List(List(b23742)))
    val b23741 = CounterIter(x36121, None).name("b23741").ctrl(x36130) // b23741
    val b23742 = Const(true).name("b23742").ctrl(x36130) // b23742
    val x36123 = OpDef(op=BitAnd, inputs=List(b23742, b23720)).name("x36123").ctrl(x36130).srcCtx("UnrollingBase.scala:28:66") // And(b23742,b23720)
    val x36124 = OpDef(op=BitAnd, inputs=List(b23711, b23712)).name("x36124").ctrl(x36130).srcCtx("UnrollingBase.scala:28:66") // And(b23711,b23712)
    val x36125 = OpDef(op=BitAnd, inputs=List(x36123, x36124)).name("x36125").ctrl(x36130).srcCtx("UnrollingBase.scala:28:66") // And(x36123,x36124)
    val x36126 = OpDef(op=BitAnd, inputs=List(x36125, b22364)).name("x36126").ctrl(x36130).srcCtx("UnrollingBase.scala:28:66") // And(x36125,b22364)
    val x36127_x36127 = ReadMem(x36104).name("x36127_x36127").ctrl(x36130).srcCtx("CellsPar.scala:192:20") // ParStreamRead(x36104,List(x36126))
    val x36128_x36128 = x36127_x36127 // x36128 = VectorApply(x36127,0)
    val x36129 = StoreBanks(List(x36097_d0_b0), List(b23719, b23741), x36128_x36128).name("x36129").ctrl(x36130).srcCtx("CellsPar.scala:192:20") // ParSRAMStore(x36097,List(List(b23719, b23741)),List(x36128),List(x36126))
    val x36132 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x36132").ctrl(x36202).srcCtx("CellsPar.scala:193:34") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x36133 = CounterChain(List(x36132)).name("x36133").ctrl(x36202).srcCtx("CellsPar.scala:193:46") // CounterChainNew(List(x36132))
    val x36201 = LoopController(style=MetaPipe, level=OuterControl, cchain=x36133).name("x36201").ctrl(x36202).srcCtx("CellsPar.scala:193:46") // UnrolledForeach(List(b23711, b23712, b22364),x36133,Block(Const(())),List(List(b23754)),List(List(b23755)))
    val b23754 = CounterIter(x36132, Some(0)).name("b23754").ctrl(x36201) // b23754
    val b23755 = Const(true).name("b23755").ctrl(x36201) // b23755
    val x36134 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x36134").ctrl(x36201).srcCtx("CellsPar.scala:194:28") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x36135 = CounterChain(List(x36134)).name("x36135").ctrl(x36201).srcCtx("CellsPar.scala:194:42") // CounterChainNew(List(x36134))
    val x36200 = LoopController(style=MetaPipe, level=OuterControl, cchain=x36135).name("x36200").ctrl(x36201).srcCtx("CellsPar.scala:194:42") // UnrolledForeach(List(b23755, b23711, b23712, b22364),x36135,Block(Const(())),List(List(b23758)),List(List(b23759)))
    val b23758 = CounterIter(x36134, Some(0)).name("b23758").ctrl(x36200) // b23758
    val b23759 = Const(true).name("b23759").ctrl(x36200) // b23759
    val x36136_d0 = Reg(init=Some(0.0)).name("x36136_d0").ctrl(x36200).srcCtx("CellsPar.scala:195:34:prod") // x36136 = RegNew(Const(0))
    isAccum(x36136_d0) = false
    bufferDepthOf(x36136_d0) = 2
    val x36136_d1 = Reg(init=Some(0.0)).name("x36136_d1").ctrl(x36200).srcCtx("CellsPar.scala:195:34:prod") // x36136 = RegNew(Const(0))
    isAccum(x36136_d1) = true
    bufferDepthOf(x36136_d1) = 1
    val x36137 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x36137").ctrl(x36200).srcCtx("CellsPar.scala:195:43") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x36138 = CounterChain(List(x36137)).name("x36138").ctrl(x36200).srcCtx("CellsPar.scala:207:15") // CounterChainNew(List(x36137))
    val x36164 = LoopController(style=InnerPipe, level=InnerControl, cchain=x36138).name("x36164").ctrl(x36200).srcCtx("CellsPar.scala:207:15") // UnrolledReduce(List(b23759, b23755, b23711, b23712, b22364),x36138,x36136,Block((x36136) => Const(())),List(List(b23763)),List(List(b23764)))
    val b23763 = CounterIter(x36137, None).name("b23763").ctrl(x36164) // b23763
    val b23764 = Const(true).name("b23764").ctrl(x36164) // b23764
    val x36139 = OpDef(op=FixAdd, inputs=List(b23709, b23763)).name("x36139").ctrl(x36164).srcCtx("CellsPar.scala:196:42:reduce_size_offset") // FixAdd(b23709,b23763)
    val x36140 = OpDef(op=BitAnd, inputs=List(b23764, b23759)).name("x36140").ctrl(x36164).srcCtx("UnrollingBase.scala:28:66") // And(b23764,b23759)
    val x36141 = OpDef(op=BitAnd, inputs=List(b23755, b23711)).name("x36141").ctrl(x36164).srcCtx("UnrollingBase.scala:28:66") // And(b23755,b23711)
    val x36142 = OpDef(op=BitAnd, inputs=List(b23712, b22364)).name("x36142").ctrl(x36164).srcCtx("UnrollingBase.scala:28:66") // And(b23712,b22364)
    val x36143 = OpDef(op=BitAnd, inputs=List(x36140, x36141)).name("x36143").ctrl(x36164).srcCtx("UnrollingBase.scala:28:66") // And(x36140,x36141)
    val x36144 = OpDef(op=BitAnd, inputs=List(x36143, x36142)).name("x36144").ctrl(x36164).srcCtx("UnrollingBase.scala:28:66") // And(x36143,x36142)
    val x36145 = LoadBanks(List(x36097_d0_b0), List(b23763, b23758)).name("x36145").ctrl(x36164).srcCtx("CellsPar.scala:203:34:tk") // ParSRAMLoad(x36097,List(List(b23763, b23758)),List(x36144))
    val x36146 = x36145 // x36146 = VectorApply(x36145,0)
    val x36147 = LoadBanks(List(x34758_d2_b0), List(b23754, x36139)).name("x36147").ctrl(x36164).srcCtx("CellsPar.scala:204:28") // ParSRAMLoad(x34758,List(List(b23754, x36139)),List(x36144))
    val x36148 = x36147 // x36148 = VectorApply(x36147,0)
    val x36149 = OpDef(op=FixMul, inputs=List(x36148, x36146)).name("x36149").ctrl(x36164).srcCtx("CellsPar.scala:204:52:tempA") // FixMul(x36148,x36146)
    val x36150 = OpDef(op=FixSub, inputs=List(x36139, Const(512))).name("x36150").ctrl(x36164).srcCtx("CellsPar.scala:205:51") // FixSub(x36139,Const(512))
    val x36151 = LoadBanks(List(x34765_d6_b0), List(b23754, x36150)).name("x36151").ctrl(x36164).srcCtx("CellsPar.scala:205:28") // ParSRAMLoad(x34765,List(List(b23754, x36150)),List(x36144))
    val x36152 = x36151 // x36152 = VectorApply(x36151,0)
    val x36153 = OpDef(op=FixMul, inputs=List(x36152, x36146)).name("x36153").ctrl(x36164).srcCtx("CellsPar.scala:205:67:tempB") // FixMul(x36152,x36146)
    val x36154 = OpDef(op=FixLt, inputs=List(x36139, Const(512))).name("x36154").ctrl(x36164).srcCtx("CellsPar.scala:206:38") // FixLt(x36139,Const(512))
    val x36155 = OpDef(op=MuxOp, inputs=List(x36154, x36149, x36153)).name("x36155").ctrl(x36164).srcCtx("CellsPar.scala:206:18") // Mux(x36154,x36149,x36153)
    val x36156 = ReadMem(x36136_d1).name("x36156").ctrl(x36164).srcCtx("CellsPar.scala:207:15") // RegRead(x36136)
    val x36157 = OpDef(op=FixEql, inputs=List(b23763, Const(0))).name("x36157").ctrl(x36164).srcCtx("CellsPar.scala:207:15") // FixEql(b23763,Const(0))
    val x36158 = ReduceAccumOp(op=FixAdd, input=x36155, accum=x36156).name("x36158").ctrl(x36164).srcCtx("CellsPar.scala:207:17") // FixAdd(x36155,x36156)
    val x36159 = OpDef(op=BitAnd, inputs=List(b23759, b23755)).name("x36159").ctrl(x36164).srcCtx("UnrollingBase.scala:28:66") // And(b23759,b23755)
    val x36160 = OpDef(op=BitAnd, inputs=List(b23711, b23712)).name("x36160").ctrl(x36164).srcCtx("UnrollingBase.scala:28:66") // And(b23711,b23712)
    val x36161 = OpDef(op=BitAnd, inputs=List(x36159, x36160)).name("x36161").ctrl(x36164).srcCtx("UnrollingBase.scala:28:66") // And(x36159,x36160)
    val x36162 = OpDef(op=BitAnd, inputs=List(x36161, b22364)).name("x36162").ctrl(x36164).srcCtx("UnrollingBase.scala:28:66") // And(x36161,b22364)
    val x36163_x36136_d0 = WriteMem(x36136_d0, x36158).name("x36163_x36136_d0").ctrl(x36164).srcCtx("CellsPar.scala:207:15") // RegWrite(x36136,x36158,x36162)
    val x36163_x36136_d1 = WriteMem(x36136_d1, x36158).name("x36163_x36136_d1").ctrl(x36164).srcCtx("CellsPar.scala:207:15") // RegWrite(x36136,x36158,x36162)
    val x36199 = UnitController(style=SeqPipe, level=InnerControl).name("x36199").ctrl(x36200).srcCtx("CellsPar.scala:194:42") // UnitPipe(List(b23759, b23755, b23711, b23712, b22364),Block(Const(())))
    val x36165 = OpDef(op=FixAdd, inputs=List(b23710, b23758)).name("x36165").ctrl(x36199).srcCtx("CellsPar.scala:209:31:colOffset") // FixAdd(b23710,b23758)
    val x36166 = ReadMem(x36136_d0).name("x36166").ctrl(x36199).srcCtx("CellsPar.scala:210:28") // RegRead(x36136)
    val x36167 = OpDef(op=FixEql, inputs=List(b23709, Const(0))).name("x36167").ctrl(x36199).srcCtx("CellsPar.scala:210:42") // FixEql(b23709,Const(0))
    val x36168 = OpDef(op=FixAdd, inputs=List(x36165, Const(1024))).name("x36168").ctrl(x36199).srcCtx("CellsPar.scala:210:66") // FixAdd(x36165,Const(1024))
    val x36169 = OpDef(op=BitAnd, inputs=List(b23759, b23755)).name("x36169").ctrl(x36199).srcCtx("UnrollingBase.scala:28:66") // And(b23759,b23755)
    val x36170 = OpDef(op=BitAnd, inputs=List(b23711, b23712)).name("x36170").ctrl(x36199).srcCtx("UnrollingBase.scala:28:66") // And(b23711,b23712)
    val x36171 = OpDef(op=BitAnd, inputs=List(x36169, x36170)).name("x36171").ctrl(x36199).srcCtx("UnrollingBase.scala:28:66") // And(x36169,x36170)
    val x36172 = OpDef(op=BitAnd, inputs=List(x36171, b22364)).name("x36172").ctrl(x36199).srcCtx("UnrollingBase.scala:28:66") // And(x36171,b22364)
    val x36173 = LoadBanks(List(x34771_d1_b0), List(Const(0), x36168)).name("x36173").ctrl(x36199).srcCtx("CellsPar.scala:210:52") // SRAMLoad(x34771,ArrayBuffer(Const(1), Const(2048)),List(Const(0), x36168),Const(0),x36172)
    val x36174 = LoadBanks(List(x34769_d1_b0), List(b23754, x36165)).name("x36174").ctrl(x36199).srcCtx("CellsPar.scala:210:91") // SRAMLoad(x34769,ArrayBuffer(Const(1), Const(512)),List(b23754, x36165),Const(0),x36172)
    val x36175 = OpDef(op=MuxOp, inputs=List(x36167, x36173, x36174)).name("x36175").ctrl(x36199).srcCtx("CellsPar.scala:210:39") // Mux(x36167,x36173,x36174)
    val x36176 = OpDef(op=FixAdd, inputs=List(x36166, x36175)).name("x36176").ctrl(x36199).srcCtx("CellsPar.scala:210:34:ele") // FixAdd(x36166,x36175)
    val x36177 = OpDef(op=FixLeq, inputs=List(Const(1520), b23709)).name("x36177").ctrl(x36199).srcCtx("CellsPar.scala:218:46") // FixLeq(Const(1520),b23709)
    // x36178 = FixConvert(Const(1),TRUE,_8,_24) x.tp=FixPt[TRUE,_32,_0] {
    val x36178_int1 = OpDef(op=BitAnd, inputs=List(Const(1), Const("01111111111111111111111111111111"))).name("x36178_int1").ctrl(x36199).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(1),TRUE,_8,_24)
    val x36178_int2 = OpDef(op=FixSla, inputs=List(x36178_int1, Const(24))).name("x36178_int2").ctrl(x36199).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(1),TRUE,_8,_24)
    val x36178_frac1 = OpDef(op=BitAnd, inputs=List(Const(1), Const("00000000000000000000000000000000"))).name("x36178_frac1").ctrl(x36199).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(1),TRUE,_8,_24)
    val x36178_frac2 = OpDef(op=FixSla, inputs=List(x36178_frac1, Const(24))).name("x36178_frac2").ctrl(x36199).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(1),TRUE,_8,_24)
    val x36178 = OpDef(op=BitOr, inputs=List(x36178_int2, x36178_frac2)).name("x36178").ctrl(x36199).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(1),TRUE,_8,_24)
    // }
    val x36179 = OpDef(op=FixAdd, inputs=List(x36176, x36178)).name("x36179").ctrl(x36199).srcCtx("CellsPar.scala:218:87") // FixAdd(x36176,x36178)
    val x36180 = OpDef(op=FixSra, inputs=List(x36179, Const(1))).name("x36180").ctrl(x36199).srcCtx("CellsPar.scala:29:22") // FixRsh(x36179,Const(1))
    val x36181 = x36180 // FixConvert(x36180,TRUE,_8,_24) (Same Type. No op)
    val x36182 = OpDef(op=FixAbs, inputs=List(x36181)).name("x36182").ctrl(x36199).srcCtx("CellsPar.scala:37:19:absp") // FixAbs(x36181)
    val x36183 = OpDef(op=FixLt, inputs=List(Const(2.5), x36182)).name("x36183").ctrl(x36199).srcCtx("CellsPar.scala:51:23:cond1") // FixLt(Const(2.5),x36182)
    val x36184 = OpDef(op=FixLt, inputs=List(Const(0.5), x36182)).name("x36184").ctrl(x36199).srcCtx("CellsPar.scala:54:35") // FixLt(Const(0.5),x36182)
    val x36185 = OpDef(op=FixLeq, inputs=List(x36182, Const(2.5))).name("x36185").ctrl(x36199).srcCtx("CellsPar.scala:54:52") // FixLeq(x36182,Const(2.5))
    val x36186 = OpDef(op=BitAnd, inputs=List(x36184, x36185)).name("x36186").ctrl(x36199).srcCtx("CellsPar.scala:54:43:cond2") // And(x36184,x36185)
    val x36187 = OpDef(op=FixSra, inputs=List(x36182, Const(2))).name("x36187").ctrl(x36199).srcCtx("CellsPar.scala:56:21:div4") // FixRsh(x36182,Const(2))
    val x36188 = OpDef(op=FixAdd, inputs=List(x36187, Const(0.375))).name("x36188").ctrl(x36199).srcCtx("CellsPar.scala:57:27:div4Offset") // FixAdd(x36187,Const(0.375))
    val x36189 = OpDef(op=MuxOp, inputs=List(x36186, x36188, x36182)).name("x36189").ctrl(x36199).srcCtx("CellsPar.scala:58:20:body2") // Mux(x36186,x36188,x36182)
    val x36190 = OpDef(op=MuxOp, inputs=List(x36183, Const(1.0), x36189)).name("x36190").ctrl(x36199).srcCtx("CellsPar.scala:60:20:absre") // Mux(x36183,Const(1),x36189)
    val x36191 = OpDef(op=FixNeg, inputs=List(x36190)).name("x36191").ctrl(x36199).srcCtx("CellsPar.scala:62:29:mabre") // FixNeg(x36190)
    val x36192 = OpDef(op=FixLt, inputs=List(x36181, Const(0.0))).name("x36192").ctrl(x36199).srcCtx("CellsPar.scala:63:22") // FixLt(x36181,Const(0))
    val x36193 = OpDef(op=MuxOp, inputs=List(x36192, x36191, x36190)).name("x36193").ctrl(x36199).srcCtx("CellsPar.scala:63:17:re") // Mux(x36192,x36191,x36190)
    val x36194 = x36193 // FixConvert(x36193,TRUE,_8,_24) (Same Type. No op)
    val x36195 = OpDef(op=FixAdd, inputs=List(x36194, Const(1.0))).name("x36195").ctrl(x36199).srcCtx("CellsPar.scala:29:33") // FixAdd(x36194,Const(1))
    val x36196 = OpDef(op=FixSra, inputs=List(x36195, Const(1))).name("x36196").ctrl(x36199).srcCtx("CellsPar.scala:29:44") // FixRsh(x36195,Const(1))
    val x36197 = OpDef(op=MuxOp, inputs=List(x36177, x36196, x36176)).name("x36197").ctrl(x36199).srcCtx("CellsPar.scala:218:43") // Mux(x36177,x36196,x36176)
    val x36198 = StoreBanks(List(x34769_d0_b0, x34769_d1_b0), List(b23754, x36165), x36197).name("x36198").ctrl(x36199).srcCtx("CellsPar.scala:218:38") // SRAMStore(x34769,ArrayBuffer(Const(1), Const(512)),List(b23754, x36165),Const(0),x36197,x36172)
    val x36203 = Counter(min=Const(0), max=Const(512), step=Const(16), par=1).name("x36203").ctrl(x36822).srcCtx("CellsPar.scala:190:58") // CounterNew(Const(0),Const(512),Const(16),Const(1))
    val x36204 = Counter(min=Const(0), max=Const(1536), step=Const(16), par=1).name("x36204").ctrl(x36822).srcCtx("CellsPar.scala:190:33") // CounterNew(Const(0),Const(1536),Const(16),Const(1))
    val x36205 = CounterChain(List(x36204,x36203)).name("x36205").ctrl(x36822).srcCtx("CellsPar.scala:190:72") // CounterChainNew(List(x36204, x36203))
    val x36311 = LoopController(style=MetaPipe, level=OuterControl, cchain=x36205).name("x36311").ctrl(x36822).srcCtx("CellsPar.scala:190:72") // UnrolledForeach(List(b22364),x36205,Block(Const(())),List(List(b23832), List(b23833)),List(List(b23834), List(b23835)))
    val b23832 = CounterIter(x36204, Some(0)).name("b23832").ctrl(x36311) // b23832
    val b23834 = Const(true).name("b23834").ctrl(x36311) // b23834
    val b23833 = CounterIter(x36203, Some(0)).name("b23833").ctrl(x36311) // b23833
    val b23835 = Const(true).name("b23835").ctrl(x36311) // b23835
    val x36206_d0_b0 = SRAM(size=256, banking=Strided(banks=1, stride=16)).name("x36206_d0_b0").ctrl(x36311).srcCtx("CellsPar.scala:191:33:tileKernel") // x36206 = SRAMNew(ArrayBuffer(Const(16), Const(16)))
    isAccum(x36206_d0_b0) = false
    bufferDepthOf(x36206_d0_b0) = 2
    val x36209 = UnitController(style=SeqPipe, level=InnerControl).name("x36209").ctrl(x36311).srcCtx("CellsPar.scala:190:72") // UnitPipe(List(b23834, b23835, b22364),Block(Const(())))
    val x36207 = OpDef(op=FixAdd, inputs=List(b23832, Const(16))).name("x36207").ctrl(x36209).srcCtx("CellsPar.scala:192:36") // FixAdd(b23832,Const(16))
    val x36208 = OpDef(op=FixAdd, inputs=List(b23833, Const(16))).name("x36208").ctrl(x36209).srcCtx("CellsPar.scala:192:45") // FixAdd(b23833,Const(16))
    val x36210 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x36210").ctrl(x36311).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x36211 = CounterChain(List(x36210)).name("x36211").ctrl(x36311).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x36210))
    val x36240 = LoopController(style=StreamPipe, level=OuterControl, cchain=x36211).name("x36240").ctrl(x36311).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b23834, b23835, b22364),x36211,Block(Const(())),List(List(b23842)),List(List(b23843)))
    val b23842 = CounterIter(x36210, Some(0)).name("b23842").ctrl(x36240) // b23842
    val b23843 = Const(true).name("b23843").ctrl(x36240) // b23843
    val b36953 = StreamOut(field="offset").name("b36953").ctrl(x36240).srcCtx("CellsPar.scala:192:20") // x36212 = StreamOutNew(BurstCmdBus)
    isAccum(b36953) = false
    bufferDepthOf(b36953) = 1
    val b36954 = StreamOut(field="size").name("b36954").ctrl(x36240).srcCtx("CellsPar.scala:192:20") // x36212 = StreamOutNew(BurstCmdBus)
    isAccum(b36954) = false
    bufferDepthOf(b36954) = 1
    val x36213 = StreamIn(field="data").name("x36213").ctrl(x36240).srcCtx("CellsPar.scala:192:20") // x36213 = StreamInNew(BurstDataBus())
    isAccum(x36213) = false
    bufferDepthOf(x36213) = 1
    val x36228 = UnitController(style=SeqPipe, level=InnerControl).name("x36228").ctrl(x36240).srcCtx("CellsPar.scala:192:20") // UnitPipe(List(b23843, b23834, b23835, b22364),Block(x36227))
    val x36214 = OpDef(op=FixAdd, inputs=List(b23832, b23842)).name("x36214").ctrl(x36228).srcCtx("CellsPar.scala:192:20") // FixAdd(b23832,b23842)
    val x36215 = x36214 // FixConvert(x36214,TRUE,_32,_0) (Same Type. No op)
    val x36216 = OpDef(op=FixSla, inputs=List(x36215, Const(9))).name("x36216").ctrl(x36228).srcCtx("CellsPar.scala:192:20") // FixLsh(x36215,Const(9))
    val x36217 = b23833 // FixConvert(b23833,TRUE,_32,_0) (Same Type. No op)
    val x36218 = OpDef(op=FixAdd, inputs=List(x36216, x36217)).name("x36218").ctrl(x36228).srcCtx("CellsPar.scala:192:20") // FixAdd(x36216,x36217)
    val x36219 = OpDef(op=FixSla, inputs=List(x36218, Const(2))).name("x36219").ctrl(x36228).srcCtx("CellsPar.scala:192:20") // FixLsh(x36218,Const(2))
    val x36220 = x36219 // FixConvert(x36219,TRUE,_64,_0)
    val x36221 = DramAddress(x34629).name("x36221").ctrl(x36228).srcCtx("CellsPar.scala:192:20") // GetDRAMAddress(x34629)
    val x36223_x36222 = OpDef(op=FixAdd, inputs=List(x36220, x36221)).name("x36223_x36222").ctrl(x36228).srcCtx("CellsPar.scala:192:20") // FixAdd(x36220,x36221)
    // x36223 = SimpleStruct(ArrayBuffer((offset,x36222), (size,Const(64)), (isLoad,Const(true))))
    val x36224 = OpDef(op=BitAnd, inputs=List(b23843, b23834)).name("x36224").ctrl(x36228).srcCtx("UnrollingBase.scala:28:66") // And(b23843,b23834)
    val x36225 = OpDef(op=BitAnd, inputs=List(b23835, b22364)).name("x36225").ctrl(x36228).srcCtx("UnrollingBase.scala:28:66") // And(b23835,b22364)
    val x36226 = OpDef(op=BitAnd, inputs=List(x36224, x36225)).name("x36226").ctrl(x36228).srcCtx("UnrollingBase.scala:28:66") // And(x36224,x36225)
    val x36227_b36955_b36953 = WriteMem(b36953, x36223_x36222).name("x36227_b36955_b36953").ctrl(x36228).srcCtx("CellsPar.scala:192:20") // StreamWrite(x36212,x36223,x36226)
    val x36227_b36956_b36954 = WriteMem(b36954, Const(64)).name("x36227_b36956_b36954").ctrl(x36228).srcCtx("CellsPar.scala:192:20") // StreamWrite(x36212,x36223,x36226)
    val x36229 = FringeDenseLoad(dram=List(x34629), cmdStream=List(b36953, b36954), dataStream=List(x36213)).name("x36229").ctrl(x36240).srcCtx("CellsPar.scala:192:20") // FringeDenseLoad(x34629,x36212,x36213)
    val x36230 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x36230").ctrl(x36240).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x36231 = CounterChain(List(x36230)).name("x36231").ctrl(x36240).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x36230))
    val x36239 = LoopController(style=InnerPipe, level=InnerControl, cchain=x36231).name("x36239").ctrl(x36240).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b23843, b23834, b23835, b22364),x36231,Block(Const(())),List(List(b23864)),List(List(b23865)))
    val b23864 = CounterIter(x36230, None).name("b23864").ctrl(x36239) // b23864
    val b23865 = Const(true).name("b23865").ctrl(x36239) // b23865
    val x36232 = OpDef(op=BitAnd, inputs=List(b23865, b23843)).name("x36232").ctrl(x36239).srcCtx("UnrollingBase.scala:28:66") // And(b23865,b23843)
    val x36233 = OpDef(op=BitAnd, inputs=List(b23834, b23835)).name("x36233").ctrl(x36239).srcCtx("UnrollingBase.scala:28:66") // And(b23834,b23835)
    val x36234 = OpDef(op=BitAnd, inputs=List(x36232, x36233)).name("x36234").ctrl(x36239).srcCtx("UnrollingBase.scala:28:66") // And(x36232,x36233)
    val x36235 = OpDef(op=BitAnd, inputs=List(x36234, b22364)).name("x36235").ctrl(x36239).srcCtx("UnrollingBase.scala:28:66") // And(x36234,b22364)
    val x36236_x36236 = ReadMem(x36213).name("x36236_x36236").ctrl(x36239).srcCtx("CellsPar.scala:192:20") // ParStreamRead(x36213,List(x36235))
    val x36237_x36237 = x36236_x36236 // x36237 = VectorApply(x36236,0)
    val x36238 = StoreBanks(List(x36206_d0_b0), List(b23842, b23864), x36237_x36237).name("x36238").ctrl(x36239).srcCtx("CellsPar.scala:192:20") // ParSRAMStore(x36206,List(List(b23842, b23864)),List(x36237),List(x36235))
    val x36241 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x36241").ctrl(x36311).srcCtx("CellsPar.scala:193:34") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x36242 = CounterChain(List(x36241)).name("x36242").ctrl(x36311).srcCtx("CellsPar.scala:193:46") // CounterChainNew(List(x36241))
    val x36310 = LoopController(style=MetaPipe, level=OuterControl, cchain=x36242).name("x36310").ctrl(x36311).srcCtx("CellsPar.scala:193:46") // UnrolledForeach(List(b23834, b23835, b22364),x36242,Block(Const(())),List(List(b23877)),List(List(b23878)))
    val b23877 = CounterIter(x36241, Some(0)).name("b23877").ctrl(x36310) // b23877
    val b23878 = Const(true).name("b23878").ctrl(x36310) // b23878
    val x36243 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x36243").ctrl(x36310).srcCtx("CellsPar.scala:194:28") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x36244 = CounterChain(List(x36243)).name("x36244").ctrl(x36310).srcCtx("CellsPar.scala:194:42") // CounterChainNew(List(x36243))
    val x36309 = LoopController(style=MetaPipe, level=OuterControl, cchain=x36244).name("x36309").ctrl(x36310).srcCtx("CellsPar.scala:194:42") // UnrolledForeach(List(b23878, b23834, b23835, b22364),x36244,Block(Const(())),List(List(b23881)),List(List(b23882)))
    val b23881 = CounterIter(x36243, Some(0)).name("b23881").ctrl(x36309) // b23881
    val b23882 = Const(true).name("b23882").ctrl(x36309) // b23882
    val x36245_d0 = Reg(init=Some(0.0)).name("x36245_d0").ctrl(x36309).srcCtx("CellsPar.scala:195:34:prod") // x36245 = RegNew(Const(0))
    isAccum(x36245_d0) = false
    bufferDepthOf(x36245_d0) = 2
    val x36245_d1 = Reg(init=Some(0.0)).name("x36245_d1").ctrl(x36309).srcCtx("CellsPar.scala:195:34:prod") // x36245 = RegNew(Const(0))
    isAccum(x36245_d1) = true
    bufferDepthOf(x36245_d1) = 1
    val x36246 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x36246").ctrl(x36309).srcCtx("CellsPar.scala:195:43") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x36247 = CounterChain(List(x36246)).name("x36247").ctrl(x36309).srcCtx("CellsPar.scala:207:15") // CounterChainNew(List(x36246))
    val x36273 = LoopController(style=InnerPipe, level=InnerControl, cchain=x36247).name("x36273").ctrl(x36309).srcCtx("CellsPar.scala:207:15") // UnrolledReduce(List(b23882, b23878, b23834, b23835, b22364),x36247,x36245,Block((x36245) => Const(())),List(List(b23886)),List(List(b23887)))
    val b23886 = CounterIter(x36246, None).name("b23886").ctrl(x36273) // b23886
    val b23887 = Const(true).name("b23887").ctrl(x36273) // b23887
    val x36248 = OpDef(op=FixAdd, inputs=List(b23832, b23886)).name("x36248").ctrl(x36273).srcCtx("CellsPar.scala:196:42:reduce_size_offset") // FixAdd(b23832,b23886)
    val x36249 = OpDef(op=BitAnd, inputs=List(b23887, b23882)).name("x36249").ctrl(x36273).srcCtx("UnrollingBase.scala:28:66") // And(b23887,b23882)
    val x36250 = OpDef(op=BitAnd, inputs=List(b23878, b23834)).name("x36250").ctrl(x36273).srcCtx("UnrollingBase.scala:28:66") // And(b23878,b23834)
    val x36251 = OpDef(op=BitAnd, inputs=List(b23835, b22364)).name("x36251").ctrl(x36273).srcCtx("UnrollingBase.scala:28:66") // And(b23835,b22364)
    val x36252 = OpDef(op=BitAnd, inputs=List(x36249, x36250)).name("x36252").ctrl(x36273).srcCtx("UnrollingBase.scala:28:66") // And(x36249,x36250)
    val x36253 = OpDef(op=BitAnd, inputs=List(x36252, x36251)).name("x36253").ctrl(x36273).srcCtx("UnrollingBase.scala:28:66") // And(x36252,x36251)
    val x36254 = LoadBanks(List(x36206_d0_b0), List(b23886, b23881)).name("x36254").ctrl(x36273).srcCtx("CellsPar.scala:203:34:tk") // ParSRAMLoad(x36206,List(List(b23886, b23881)),List(x36253))
    val x36255 = x36254 // x36255 = VectorApply(x36254,0)
    val x36256 = LoadBanks(List(x34758_d1_b0), List(b23877, x36248)).name("x36256").ctrl(x36273).srcCtx("CellsPar.scala:204:28") // ParSRAMLoad(x34758,List(List(b23877, x36248)),List(x36253))
    val x36257 = x36256 // x36257 = VectorApply(x36256,0)
    val x36258 = OpDef(op=FixMul, inputs=List(x36257, x36255)).name("x36258").ctrl(x36273).srcCtx("CellsPar.scala:204:52:tempA") // FixMul(x36257,x36255)
    val x36259 = OpDef(op=FixSub, inputs=List(x36248, Const(512))).name("x36259").ctrl(x36273).srcCtx("CellsPar.scala:205:51") // FixSub(x36248,Const(512))
    val x36260 = LoadBanks(List(x34765_d5_b0), List(b23877, x36259)).name("x36260").ctrl(x36273).srcCtx("CellsPar.scala:205:28") // ParSRAMLoad(x34765,List(List(b23877, x36259)),List(x36253))
    val x36261 = x36260 // x36261 = VectorApply(x36260,0)
    val x36262 = OpDef(op=FixMul, inputs=List(x36261, x36255)).name("x36262").ctrl(x36273).srcCtx("CellsPar.scala:205:67:tempB") // FixMul(x36261,x36255)
    val x36263 = OpDef(op=FixLt, inputs=List(x36248, Const(512))).name("x36263").ctrl(x36273).srcCtx("CellsPar.scala:206:38") // FixLt(x36248,Const(512))
    val x36264 = OpDef(op=MuxOp, inputs=List(x36263, x36258, x36262)).name("x36264").ctrl(x36273).srcCtx("CellsPar.scala:206:18") // Mux(x36263,x36258,x36262)
    val x36265 = ReadMem(x36245_d1).name("x36265").ctrl(x36273).srcCtx("CellsPar.scala:207:15") // RegRead(x36245)
    val x36266 = OpDef(op=FixEql, inputs=List(b23886, Const(0))).name("x36266").ctrl(x36273).srcCtx("CellsPar.scala:207:15") // FixEql(b23886,Const(0))
    val x36267 = ReduceAccumOp(op=FixAdd, input=x36264, accum=x36265).name("x36267").ctrl(x36273).srcCtx("CellsPar.scala:207:17") // FixAdd(x36264,x36265)
    val x36268 = OpDef(op=BitAnd, inputs=List(b23882, b23878)).name("x36268").ctrl(x36273).srcCtx("UnrollingBase.scala:28:66") // And(b23882,b23878)
    val x36269 = OpDef(op=BitAnd, inputs=List(b23834, b23835)).name("x36269").ctrl(x36273).srcCtx("UnrollingBase.scala:28:66") // And(b23834,b23835)
    val x36270 = OpDef(op=BitAnd, inputs=List(x36268, x36269)).name("x36270").ctrl(x36273).srcCtx("UnrollingBase.scala:28:66") // And(x36268,x36269)
    val x36271 = OpDef(op=BitAnd, inputs=List(x36270, b22364)).name("x36271").ctrl(x36273).srcCtx("UnrollingBase.scala:28:66") // And(x36270,b22364)
    val x36272_x36245_d0 = WriteMem(x36245_d0, x36267).name("x36272_x36245_d0").ctrl(x36273).srcCtx("CellsPar.scala:207:15") // RegWrite(x36245,x36267,x36271)
    val x36272_x36245_d1 = WriteMem(x36245_d1, x36267).name("x36272_x36245_d1").ctrl(x36273).srcCtx("CellsPar.scala:207:15") // RegWrite(x36245,x36267,x36271)
    val x36308 = UnitController(style=SeqPipe, level=InnerControl).name("x36308").ctrl(x36309).srcCtx("CellsPar.scala:194:42") // UnitPipe(List(b23882, b23878, b23834, b23835, b22364),Block(Const(())))
    val x36274 = OpDef(op=FixAdd, inputs=List(b23833, b23881)).name("x36274").ctrl(x36308).srcCtx("CellsPar.scala:209:31:colOffset") // FixAdd(b23833,b23881)
    val x36275 = ReadMem(x36245_d0).name("x36275").ctrl(x36308).srcCtx("CellsPar.scala:210:28") // RegRead(x36245)
    val x36276 = OpDef(op=FixEql, inputs=List(b23832, Const(0))).name("x36276").ctrl(x36308).srcCtx("CellsPar.scala:210:42") // FixEql(b23832,Const(0))
    val x36277 = OpDef(op=FixAdd, inputs=List(x36274, Const(1536))).name("x36277").ctrl(x36308).srcCtx("CellsPar.scala:210:66") // FixAdd(x36274,Const(1536))
    val x36278 = OpDef(op=BitAnd, inputs=List(b23882, b23878)).name("x36278").ctrl(x36308).srcCtx("UnrollingBase.scala:28:66") // And(b23882,b23878)
    val x36279 = OpDef(op=BitAnd, inputs=List(b23834, b23835)).name("x36279").ctrl(x36308).srcCtx("UnrollingBase.scala:28:66") // And(b23834,b23835)
    val x36280 = OpDef(op=BitAnd, inputs=List(x36278, x36279)).name("x36280").ctrl(x36308).srcCtx("UnrollingBase.scala:28:66") // And(x36278,x36279)
    val x36281 = OpDef(op=BitAnd, inputs=List(x36280, b22364)).name("x36281").ctrl(x36308).srcCtx("UnrollingBase.scala:28:66") // And(x36280,b22364)
    val x36282 = LoadBanks(List(x34771_d0_b0), List(Const(0), x36277)).name("x36282").ctrl(x36308).srcCtx("CellsPar.scala:210:52") // SRAMLoad(x34771,ArrayBuffer(Const(1), Const(2048)),List(Const(0), x36277),Const(0),x36281)
    val x36283 = LoadBanks(List(x34770_d1_b0), List(b23877, x36274)).name("x36283").ctrl(x36308).srcCtx("CellsPar.scala:210:91") // SRAMLoad(x34770,ArrayBuffer(Const(1), Const(512)),List(b23877, x36274),Const(0),x36281)
    val x36284 = OpDef(op=MuxOp, inputs=List(x36276, x36282, x36283)).name("x36284").ctrl(x36308).srcCtx("CellsPar.scala:210:39") // Mux(x36276,x36282,x36283)
    val x36285 = OpDef(op=FixAdd, inputs=List(x36275, x36284)).name("x36285").ctrl(x36308).srcCtx("CellsPar.scala:210:34:ele") // FixAdd(x36275,x36284)
    val x36286 = OpDef(op=FixLeq, inputs=List(Const(1520), b23832)).name("x36286").ctrl(x36308).srcCtx("CellsPar.scala:218:46") // FixLeq(Const(1520),b23832)
    // x36287 = FixConvert(Const(0),TRUE,_8,_24) x.tp=FixPt[TRUE,_32,_0] {
    val x36287_int1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("01111111111111111111111111111111"))).name("x36287_int1").ctrl(x36308).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x36287_int2 = OpDef(op=FixSla, inputs=List(x36287_int1, Const(24))).name("x36287_int2").ctrl(x36308).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x36287_frac1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("00000000000000000000000000000000"))).name("x36287_frac1").ctrl(x36308).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x36287_frac2 = OpDef(op=FixSla, inputs=List(x36287_frac1, Const(24))).name("x36287_frac2").ctrl(x36308).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x36287 = OpDef(op=BitOr, inputs=List(x36287_int2, x36287_frac2)).name("x36287").ctrl(x36308).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    // }
    val x36288 = OpDef(op=FixAdd, inputs=List(x36285, x36287)).name("x36288").ctrl(x36308).srcCtx("CellsPar.scala:218:87") // FixAdd(x36285,x36287)
    val x36289 = OpDef(op=FixSra, inputs=List(x36288, Const(1))).name("x36289").ctrl(x36308).srcCtx("CellsPar.scala:29:22") // FixRsh(x36288,Const(1))
    val x36290 = x36289 // FixConvert(x36289,TRUE,_8,_24) (Same Type. No op)
    val x36291 = OpDef(op=FixAbs, inputs=List(x36290)).name("x36291").ctrl(x36308).srcCtx("CellsPar.scala:37:19:absp") // FixAbs(x36290)
    val x36292 = OpDef(op=FixLt, inputs=List(Const(2.5), x36291)).name("x36292").ctrl(x36308).srcCtx("CellsPar.scala:51:23:cond1") // FixLt(Const(2.5),x36291)
    val x36293 = OpDef(op=FixLt, inputs=List(Const(0.5), x36291)).name("x36293").ctrl(x36308).srcCtx("CellsPar.scala:54:35") // FixLt(Const(0.5),x36291)
    val x36294 = OpDef(op=FixLeq, inputs=List(x36291, Const(2.5))).name("x36294").ctrl(x36308).srcCtx("CellsPar.scala:54:52") // FixLeq(x36291,Const(2.5))
    val x36295 = OpDef(op=BitAnd, inputs=List(x36293, x36294)).name("x36295").ctrl(x36308).srcCtx("CellsPar.scala:54:43:cond2") // And(x36293,x36294)
    val x36296 = OpDef(op=FixSra, inputs=List(x36291, Const(2))).name("x36296").ctrl(x36308).srcCtx("CellsPar.scala:56:21:div4") // FixRsh(x36291,Const(2))
    val x36297 = OpDef(op=FixAdd, inputs=List(x36296, Const(0.375))).name("x36297").ctrl(x36308).srcCtx("CellsPar.scala:57:27:div4Offset") // FixAdd(x36296,Const(0.375))
    val x36298 = OpDef(op=MuxOp, inputs=List(x36295, x36297, x36291)).name("x36298").ctrl(x36308).srcCtx("CellsPar.scala:58:20:body2") // Mux(x36295,x36297,x36291)
    val x36299 = OpDef(op=MuxOp, inputs=List(x36292, Const(1.0), x36298)).name("x36299").ctrl(x36308).srcCtx("CellsPar.scala:60:20:absre") // Mux(x36292,Const(1),x36298)
    val x36300 = OpDef(op=FixNeg, inputs=List(x36299)).name("x36300").ctrl(x36308).srcCtx("CellsPar.scala:62:29:mabre") // FixNeg(x36299)
    val x36301 = OpDef(op=FixLt, inputs=List(x36290, Const(0.0))).name("x36301").ctrl(x36308).srcCtx("CellsPar.scala:63:22") // FixLt(x36290,Const(0))
    val x36302 = OpDef(op=MuxOp, inputs=List(x36301, x36300, x36299)).name("x36302").ctrl(x36308).srcCtx("CellsPar.scala:63:17:re") // Mux(x36301,x36300,x36299)
    val x36303 = x36302 // FixConvert(x36302,TRUE,_8,_24) (Same Type. No op)
    val x36304 = OpDef(op=FixAdd, inputs=List(x36303, Const(1.0))).name("x36304").ctrl(x36308).srcCtx("CellsPar.scala:29:33") // FixAdd(x36303,Const(1))
    val x36305 = OpDef(op=FixSra, inputs=List(x36304, Const(1))).name("x36305").ctrl(x36308).srcCtx("CellsPar.scala:29:44") // FixRsh(x36304,Const(1))
    val x36306 = OpDef(op=MuxOp, inputs=List(x36286, x36305, x36285)).name("x36306").ctrl(x36308).srcCtx("CellsPar.scala:218:43") // Mux(x36286,x36305,x36285)
    val x36307 = StoreBanks(List(x34770_d0_b0, x34770_d1_b0), List(b23877, x36274), x36306).name("x36307").ctrl(x36308).srcCtx("CellsPar.scala:218:38") // SRAMStore(x34770,ArrayBuffer(Const(1), Const(512)),List(b23877, x36274),Const(0),x36306,x36281)
    val x36312 = Counter(min=Const(0), max=Const(512), step=Const(1), par=1).name("x36312").ctrl(x36822).srcCtx("CellsPar.scala:267:50") // CounterNew(Const(0),Const(512),Const(1),Const(1))
    val x36313 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x36313").ctrl(x36822).srcCtx("CellsPar.scala:267:27") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x36314 = CounterChain(List(x36313,x36312)).name("x36314").ctrl(x36822).srcCtx("CellsPar.scala:267:62") // CounterChainNew(List(x36313, x36312))
    val x36350 = LoopController(style=InnerPipe, level=InnerControl, cchain=x36314).name("x36350").ctrl(x36822).srcCtx("CellsPar.scala:267:62") // UnrolledForeach(List(b22364),x36314,Block(Const(())),List(List(b23956), List(b23957)),List(List(b23958), List(b23959)))
    val b23956 = CounterIter(x36313, Some(0)).name("b23956").ctrl(x36350) // b23956
    val b23958 = Const(true).name("b23958").ctrl(x36350) // b23958
    val b23957 = CounterIter(x36312, None).name("b23957").ctrl(x36350) // b23957
    val b23959 = Const(true).name("b23959").ctrl(x36350) // b23959
    val x36315 = OpDef(op=BitAnd, inputs=List(b23958, b23959)).name("x36315").ctrl(x36350).srcCtx("UnrollingBase.scala:28:66") // And(b23958,b23959)
    val x36316 = OpDef(op=BitAnd, inputs=List(x36315, b22364)).name("x36316").ctrl(x36350).srcCtx("UnrollingBase.scala:28:66") // And(x36315,b22364)
    val x36317 = LoadBanks(List(x34766_d0_b0), List(b23956, b23957)).name("x36317").ctrl(x36350).srcCtx("CellsPar.scala:268:22") // ParSRAMLoad(x34766,List(List(b23956, b23957)),List(x36316))
    val x36318 = x36317 // x36318 = VectorApply(x36317,0)
    val x36319 = LoadBanks(List(x34769_d0_b0), List(b23956, b23957)).name("x36319").ctrl(x36350).srcCtx("CellsPar.scala:268:34") // ParSRAMLoad(x34769,List(List(b23956, b23957)),List(x36316))
    val x36320 = x36319 // x36320 = VectorApply(x36319,0)
    val x36321 = OpDef(op=FixMul, inputs=List(x36318, x36320)).name("x36321").ctrl(x36350).srcCtx("CellsPar.scala:268:28") // FixMul(x36318,x36320)
    val x36322 = LoadBanks(List(x34767_d0_b0), List(b23956, b23957)).name("x36322").ctrl(x36350).srcCtx("CellsPar.scala:268:46") // ParSRAMLoad(x34767,List(List(b23956, b23957)),List(x36316))
    val x36323 = x36322 // x36323 = VectorApply(x36322,0)
    val x36324 = LoadBanks(List(x34768_d0_b0), List(b23956, b23957)).name("x36324").ctrl(x36350).srcCtx("CellsPar.scala:268:59") // ParSRAMLoad(x34768,List(List(b23956, b23957)),List(x36316))
    val x36325 = x36324 // x36325 = VectorApply(x36324,0)
    val x36326 = OpDef(op=FixMul, inputs=List(x36323, x36325)).name("x36326").ctrl(x36350).srcCtx("CellsPar.scala:268:52") // FixMul(x36323,x36325)
    val x36327 = OpDef(op=FixAdd, inputs=List(x36321, x36326)).name("x36327").ctrl(x36350).srcCtx("CellsPar.scala:268:40:new_c") // FixAdd(x36321,x36326)
    val x36328 = x36327 // FixConvert(x36327,TRUE,_8,_24) (Same Type. No op)
    val x36329 = OpDef(op=FixAbs, inputs=List(x36328)).name("x36329").ctrl(x36350).srcCtx("CellsPar.scala:37:19:absp") // FixAbs(x36328)
    val x36330 = OpDef(op=FixLt, inputs=List(Const(2.5), x36329)).name("x36330").ctrl(x36350).srcCtx("CellsPar.scala:51:23:cond1") // FixLt(Const(2.5),x36329)
    val x36331 = OpDef(op=FixLt, inputs=List(Const(0.5), x36329)).name("x36331").ctrl(x36350).srcCtx("CellsPar.scala:54:35") // FixLt(Const(0.5),x36329)
    val x36332 = OpDef(op=FixLeq, inputs=List(x36329, Const(2.5))).name("x36332").ctrl(x36350).srcCtx("CellsPar.scala:54:52") // FixLeq(x36329,Const(2.5))
    val x36333 = OpDef(op=BitAnd, inputs=List(x36331, x36332)).name("x36333").ctrl(x36350).srcCtx("CellsPar.scala:54:43:cond2") // And(x36331,x36332)
    val x36334 = OpDef(op=FixSra, inputs=List(x36329, Const(2))).name("x36334").ctrl(x36350).srcCtx("CellsPar.scala:56:21:div4") // FixRsh(x36329,Const(2))
    val x36335 = OpDef(op=FixAdd, inputs=List(x36334, Const(0.375))).name("x36335").ctrl(x36350).srcCtx("CellsPar.scala:57:27:div4Offset") // FixAdd(x36334,Const(0.375))
    val x36336 = OpDef(op=MuxOp, inputs=List(x36333, x36335, x36329)).name("x36336").ctrl(x36350).srcCtx("CellsPar.scala:58:20:body2") // Mux(x36333,x36335,x36329)
    val x36337 = OpDef(op=MuxOp, inputs=List(x36330, Const(1.0), x36336)).name("x36337").ctrl(x36350).srcCtx("CellsPar.scala:60:20:absre") // Mux(x36330,Const(1),x36336)
    val x36338 = OpDef(op=FixNeg, inputs=List(x36337)).name("x36338").ctrl(x36350).srcCtx("CellsPar.scala:62:29:mabre") // FixNeg(x36337)
    val x36339 = OpDef(op=FixLt, inputs=List(x36328, Const(0.0))).name("x36339").ctrl(x36350).srcCtx("CellsPar.scala:63:22") // FixLt(x36328,Const(0))
    val x36340 = OpDef(op=MuxOp, inputs=List(x36339, x36338, x36337)).name("x36340").ctrl(x36350).srcCtx("CellsPar.scala:63:17:re") // Mux(x36339,x36338,x36337)
    val x36341 = x36340 // FixConvert(x36340,TRUE,_8,_24) (Same Type. No op)
    val x36342 = LoadBanks(List(x34770_d0_b0), List(b23956, b23957)).name("x36342").ctrl(x36350).srcCtx("CellsPar.scala:275:47") // ParSRAMLoad(x34770,List(List(b23956, b23957)),List(x36316))
    val x36343 = x36342 // x36343 = VectorApply(x36342,0)
    val x36344 = OpDef(op=FixMul, inputs=List(x36341, x36343)).name("x36344").ctrl(x36350).srcCtx("CellsPar.scala:275:41:prod") // FixMul(x36341,x36343)
    val x36345 = LoadBanks(List(x34758_d0_b0), List(b23956, b23957)).name("x36345").ctrl(x36350).srcCtx("CellsPar.scala:276:43") // ParSRAMLoad(x34758,List(List(b23956, b23957)),List(x36316))
    val x36346 = x36345 // x36346 = VectorApply(x36345,0)
    val x36347 = OpDef(op=FixAdd, inputs=List(x36344, x36346)).name("x36347").ctrl(x36350).srcCtx("CellsPar.scala:276:40") // FixAdd(x36344,x36346)
    val x36348 = StoreBanks(List(x34765_d0_b0, x34765_d5_b0, x34765_d1_b0, x34765_d6_b0, x34765_d2_b0, x34765_d7_b0, x34765_d3_b0, x34765_d8_b0, x34765_d4_b0), List(b23956, b23957), x36344).name("x36348").ctrl(x36350).srcCtx("CellsPar.scala:276:17") // ParSRAMStore(x34765,List(List(b23956, b23957)),List(x36344),List(x36316))
    val x36349 = StoreBanks(List(x34766_d0_b0), List(b23956, b23957), x36327).name("x36349").ctrl(x36350).srcCtx("CellsPar.scala:277:16") // ParSRAMStore(x34766,List(List(b23956, b23957)),List(x36327),List(x36316))
    val x36351 = Counter(min=Const(0), max=Const(512), step=Const(16), par=1).name("x36351").ctrl(x36822).srcCtx("CellsPar.scala:190:58") // CounterNew(Const(0),Const(512),Const(16),Const(1))
    val x36352 = Counter(min=Const(0), max=Const(1536), step=Const(16), par=1).name("x36352").ctrl(x36822).srcCtx("CellsPar.scala:190:33") // CounterNew(Const(0),Const(1536),Const(16),Const(1))
    val x36353 = CounterChain(List(x36352,x36351)).name("x36353").ctrl(x36822).srcCtx("CellsPar.scala:190:72") // CounterChainNew(List(x36352, x36351))
    val x36458 = LoopController(style=MetaPipe, level=OuterControl, cchain=x36353).name("x36458").ctrl(x36822).srcCtx("CellsPar.scala:190:72") // UnrolledForeach(List(b22364),x36353,Block(Const(())),List(List(b23999), List(b24000)),List(List(b24001), List(b24002)))
    val b23999 = CounterIter(x36352, Some(0)).name("b23999").ctrl(x36458) // b23999
    val b24001 = Const(true).name("b24001").ctrl(x36458) // b24001
    val b24000 = CounterIter(x36351, Some(0)).name("b24000").ctrl(x36458) // b24000
    val b24002 = Const(true).name("b24002").ctrl(x36458) // b24002
    def split3 = {
    val x36354_d0_b0 = SRAM(size=256, banking=Strided(banks=1, stride=16)).name("x36354_d0_b0").ctrl(x36458).srcCtx("CellsPar.scala:191:33:tileKernel") // x36354 = SRAMNew(ArrayBuffer(Const(16), Const(16)))
    isAccum(x36354_d0_b0) = false
    bufferDepthOf(x36354_d0_b0) = 2
    val x36357 = UnitController(style=SeqPipe, level=InnerControl).name("x36357").ctrl(x36458).srcCtx("CellsPar.scala:190:72") // UnitPipe(List(b24001, b24002, b22364),Block(Const(())))
    val x36355 = OpDef(op=FixAdd, inputs=List(b23999, Const(16))).name("x36355").ctrl(x36357).srcCtx("CellsPar.scala:192:36") // FixAdd(b23999,Const(16))
    val x36356 = OpDef(op=FixAdd, inputs=List(b24000, Const(16))).name("x36356").ctrl(x36357).srcCtx("CellsPar.scala:192:45") // FixAdd(b24000,Const(16))
    val x36358 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x36358").ctrl(x36458).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x36359 = CounterChain(List(x36358)).name("x36359").ctrl(x36458).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x36358))
    val x36388 = LoopController(style=StreamPipe, level=OuterControl, cchain=x36359).name("x36388").ctrl(x36458).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b24001, b24002, b22364),x36359,Block(Const(())),List(List(b24009)),List(List(b24010)))
    val b24009 = CounterIter(x36358, Some(0)).name("b24009").ctrl(x36388) // b24009
    val b24010 = Const(true).name("b24010").ctrl(x36388) // b24010
    val b36957 = StreamOut(field="offset").name("b36957").ctrl(x36388).srcCtx("CellsPar.scala:192:20") // x36360 = StreamOutNew(BurstCmdBus)
    isAccum(b36957) = false
    bufferDepthOf(b36957) = 1
    val b36958 = StreamOut(field="size").name("b36958").ctrl(x36388).srcCtx("CellsPar.scala:192:20") // x36360 = StreamOutNew(BurstCmdBus)
    isAccum(b36958) = false
    bufferDepthOf(b36958) = 1
    val x36361 = StreamIn(field="data").name("x36361").ctrl(x36388).srcCtx("CellsPar.scala:192:20") // x36361 = StreamInNew(BurstDataBus())
    isAccum(x36361) = false
    bufferDepthOf(x36361) = 1
    val x36376 = UnitController(style=SeqPipe, level=InnerControl).name("x36376").ctrl(x36388).srcCtx("CellsPar.scala:192:20") // UnitPipe(List(b24010, b24001, b24002, b22364),Block(x36375))
    val x36362 = OpDef(op=FixAdd, inputs=List(b23999, b24009)).name("x36362").ctrl(x36376).srcCtx("CellsPar.scala:192:20") // FixAdd(b23999,b24009)
    val x36363 = x36362 // FixConvert(x36362,TRUE,_32,_0) (Same Type. No op)
    val x36364 = OpDef(op=FixSla, inputs=List(x36363, Const(9))).name("x36364").ctrl(x36376).srcCtx("CellsPar.scala:192:20") // FixLsh(x36363,Const(9))
    val x36365 = b24000 // FixConvert(b24000,TRUE,_32,_0) (Same Type. No op)
    val x36366 = OpDef(op=FixAdd, inputs=List(x36364, x36365)).name("x36366").ctrl(x36376).srcCtx("CellsPar.scala:192:20") // FixAdd(x36364,x36365)
    val x36367 = OpDef(op=FixSla, inputs=List(x36366, Const(2))).name("x36367").ctrl(x36376).srcCtx("CellsPar.scala:192:20") // FixLsh(x36366,Const(2))
    val x36368 = x36367 // FixConvert(x36367,TRUE,_64,_0)
    val x36369 = DramAddress(x34702).name("x36369").ctrl(x36376).srcCtx("CellsPar.scala:192:20") // GetDRAMAddress(x34702)
    val x36371_x36370 = OpDef(op=FixAdd, inputs=List(x36368, x36369)).name("x36371_x36370").ctrl(x36376).srcCtx("CellsPar.scala:192:20") // FixAdd(x36368,x36369)
    // x36371 = SimpleStruct(ArrayBuffer((offset,x36370), (size,Const(64)), (isLoad,Const(true))))
    val x36372 = OpDef(op=BitAnd, inputs=List(b24010, b24001)).name("x36372").ctrl(x36376).srcCtx("UnrollingBase.scala:28:66") // And(b24010,b24001)
    val x36373 = OpDef(op=BitAnd, inputs=List(b24002, b22364)).name("x36373").ctrl(x36376).srcCtx("UnrollingBase.scala:28:66") // And(b24002,b22364)
    val x36374 = OpDef(op=BitAnd, inputs=List(x36372, x36373)).name("x36374").ctrl(x36376).srcCtx("UnrollingBase.scala:28:66") // And(x36372,x36373)
    val x36375_b36959_b36957 = WriteMem(b36957, x36371_x36370).name("x36375_b36959_b36957").ctrl(x36376).srcCtx("CellsPar.scala:192:20") // StreamWrite(x36360,x36371,x36374)
    val x36375_b36960_b36958 = WriteMem(b36958, Const(64)).name("x36375_b36960_b36958").ctrl(x36376).srcCtx("CellsPar.scala:192:20") // StreamWrite(x36360,x36371,x36374)
    val x36377 = FringeDenseLoad(dram=List(x34702), cmdStream=List(b36957, b36958), dataStream=List(x36361)).name("x36377").ctrl(x36388).srcCtx("CellsPar.scala:192:20") // FringeDenseLoad(x34702,x36360,x36361)
    val x36378 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x36378").ctrl(x36388).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x36379 = CounterChain(List(x36378)).name("x36379").ctrl(x36388).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x36378))
    val x36387 = LoopController(style=InnerPipe, level=InnerControl, cchain=x36379).name("x36387").ctrl(x36388).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b24010, b24001, b24002, b22364),x36379,Block(Const(())),List(List(b24031)),List(List(b24032)))
    val b24031 = CounterIter(x36378, None).name("b24031").ctrl(x36387) // b24031
    val b24032 = Const(true).name("b24032").ctrl(x36387) // b24032
    val x36380 = OpDef(op=BitAnd, inputs=List(b24032, b24010)).name("x36380").ctrl(x36387).srcCtx("UnrollingBase.scala:28:66") // And(b24032,b24010)
    val x36381 = OpDef(op=BitAnd, inputs=List(b24001, b24002)).name("x36381").ctrl(x36387).srcCtx("UnrollingBase.scala:28:66") // And(b24001,b24002)
    val x36382 = OpDef(op=BitAnd, inputs=List(x36380, x36381)).name("x36382").ctrl(x36387).srcCtx("UnrollingBase.scala:28:66") // And(x36380,x36381)
    val x36383 = OpDef(op=BitAnd, inputs=List(x36382, b22364)).name("x36383").ctrl(x36387).srcCtx("UnrollingBase.scala:28:66") // And(x36382,b22364)
    val x36384_x36384 = ReadMem(x36361).name("x36384_x36384").ctrl(x36387).srcCtx("CellsPar.scala:192:20") // ParStreamRead(x36361,List(x36383))
    val x36385_x36385 = x36384_x36384 // x36385 = VectorApply(x36384,0)
    val x36386 = StoreBanks(List(x36354_d0_b0), List(b24009, b24031), x36385_x36385).name("x36386").ctrl(x36387).srcCtx("CellsPar.scala:192:20") // ParSRAMStore(x36354,List(List(b24009, b24031)),List(x36385),List(x36383))
    val x36389 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x36389").ctrl(x36458).srcCtx("CellsPar.scala:193:34") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x36390 = CounterChain(List(x36389)).name("x36390").ctrl(x36458).srcCtx("CellsPar.scala:193:46") // CounterChainNew(List(x36389))
    val x36457 = LoopController(style=MetaPipe, level=OuterControl, cchain=x36390).name("x36457").ctrl(x36458).srcCtx("CellsPar.scala:193:46") // UnrolledForeach(List(b24001, b24002, b22364),x36390,Block(Const(())),List(List(b24044)),List(List(b24045)))
    val b24044 = CounterIter(x36389, Some(0)).name("b24044").ctrl(x36457) // b24044
    val b24045 = Const(true).name("b24045").ctrl(x36457) // b24045
    val x36391 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x36391").ctrl(x36457).srcCtx("CellsPar.scala:194:28") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x36392 = CounterChain(List(x36391)).name("x36392").ctrl(x36457).srcCtx("CellsPar.scala:194:42") // CounterChainNew(List(x36391))
    val x36456 = LoopController(style=MetaPipe, level=OuterControl, cchain=x36392).name("x36456").ctrl(x36457).srcCtx("CellsPar.scala:194:42") // UnrolledForeach(List(b24045, b24001, b24002, b22364),x36392,Block(Const(())),List(List(b24048)),List(List(b24049)))
    val b24048 = CounterIter(x36391, Some(0)).name("b24048").ctrl(x36456) // b24048
    val b24049 = Const(true).name("b24049").ctrl(x36456) // b24049
    val x36393_d0 = Reg(init=Some(0.0)).name("x36393_d0").ctrl(x36456).srcCtx("CellsPar.scala:195:34:prod") // x36393 = RegNew(Const(0))
    isAccum(x36393_d0) = false
    bufferDepthOf(x36393_d0) = 2
    val x36393_d1 = Reg(init=Some(0.0)).name("x36393_d1").ctrl(x36456).srcCtx("CellsPar.scala:195:34:prod") // x36393 = RegNew(Const(0))
    isAccum(x36393_d1) = true
    bufferDepthOf(x36393_d1) = 1
    val x36394 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x36394").ctrl(x36456).srcCtx("CellsPar.scala:195:43") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x36395 = CounterChain(List(x36394)).name("x36395").ctrl(x36456).srcCtx("CellsPar.scala:207:15") // CounterChainNew(List(x36394))
    val x36421 = LoopController(style=InnerPipe, level=InnerControl, cchain=x36395).name("x36421").ctrl(x36456).srcCtx("CellsPar.scala:207:15") // UnrolledReduce(List(b24049, b24045, b24001, b24002, b22364),x36395,x36393,Block((x36393) => Const(())),List(List(b24053)),List(List(b24054)))
    val b24053 = CounterIter(x36394, None).name("b24053").ctrl(x36421) // b24053
    val b24054 = Const(true).name("b24054").ctrl(x36421) // b24054
    val x36396 = OpDef(op=FixAdd, inputs=List(b23999, b24053)).name("x36396").ctrl(x36421).srcCtx("CellsPar.scala:196:42:reduce_size_offset") // FixAdd(b23999,b24053)
    val x36397 = OpDef(op=BitAnd, inputs=List(b24054, b24049)).name("x36397").ctrl(x36421).srcCtx("UnrollingBase.scala:28:66") // And(b24054,b24049)
    val x36398 = OpDef(op=BitAnd, inputs=List(b24045, b24001)).name("x36398").ctrl(x36421).srcCtx("UnrollingBase.scala:28:66") // And(b24045,b24001)
    val x36399 = OpDef(op=BitAnd, inputs=List(b24002, b22364)).name("x36399").ctrl(x36421).srcCtx("UnrollingBase.scala:28:66") // And(b24002,b22364)
    val x36400 = OpDef(op=BitAnd, inputs=List(x36397, x36398)).name("x36400").ctrl(x36421).srcCtx("UnrollingBase.scala:28:66") // And(x36397,x36398)
    val x36401 = OpDef(op=BitAnd, inputs=List(x36400, x36399)).name("x36401").ctrl(x36421).srcCtx("UnrollingBase.scala:28:66") // And(x36400,x36399)
    val x36402 = LoadBanks(List(x36354_d0_b0), List(b24053, b24048)).name("x36402").ctrl(x36421).srcCtx("CellsPar.scala:203:34:tk") // ParSRAMLoad(x36354,List(List(b24053, b24048)),List(x36401))
    val x36403 = x36402 // x36403 = VectorApply(x36402,0)
    val x36404 = LoadBanks(List(x34765_d4_b0), List(b24044, x36396)).name("x36404").ctrl(x36421).srcCtx("CellsPar.scala:204:28") // ParSRAMLoad(x34765,List(List(b24044, x36396)),List(x36401))
    val x36405 = x36404 // x36405 = VectorApply(x36404,0)
    val x36406 = OpDef(op=FixMul, inputs=List(x36405, x36403)).name("x36406").ctrl(x36421).srcCtx("CellsPar.scala:204:52:tempA") // FixMul(x36405,x36403)
    val x36407 = OpDef(op=FixSub, inputs=List(x36396, Const(512))).name("x36407").ctrl(x36421).srcCtx("CellsPar.scala:205:51") // FixSub(x36396,Const(512))
    val x36408 = LoadBanks(List(x34772_d4_b0), List(b24044, x36407)).name("x36408").ctrl(x36421).srcCtx("CellsPar.scala:205:28") // ParSRAMLoad(x34772,List(List(b24044, x36407)),List(x36401))
    val x36409 = x36408 // x36409 = VectorApply(x36408,0)
    val x36410 = OpDef(op=FixMul, inputs=List(x36409, x36403)).name("x36410").ctrl(x36421).srcCtx("CellsPar.scala:205:67:tempB") // FixMul(x36409,x36403)
    val x36411 = OpDef(op=FixLt, inputs=List(x36396, Const(512))).name("x36411").ctrl(x36421).srcCtx("CellsPar.scala:206:38") // FixLt(x36396,Const(512))
    val x36412 = OpDef(op=MuxOp, inputs=List(x36411, x36406, x36410)).name("x36412").ctrl(x36421).srcCtx("CellsPar.scala:206:18") // Mux(x36411,x36406,x36410)
    val x36413 = ReadMem(x36393_d1).name("x36413").ctrl(x36421).srcCtx("CellsPar.scala:207:15") // RegRead(x36393)
    val x36414 = OpDef(op=FixEql, inputs=List(b24053, Const(0))).name("x36414").ctrl(x36421).srcCtx("CellsPar.scala:207:15") // FixEql(b24053,Const(0))
    val x36415 = ReduceAccumOp(op=FixAdd, input=x36412, accum=x36413).name("x36415").ctrl(x36421).srcCtx("CellsPar.scala:207:17") // FixAdd(x36412,x36413)
    val x36416 = OpDef(op=BitAnd, inputs=List(b24049, b24045)).name("x36416").ctrl(x36421).srcCtx("UnrollingBase.scala:28:66") // And(b24049,b24045)
    val x36417 = OpDef(op=BitAnd, inputs=List(b24001, b24002)).name("x36417").ctrl(x36421).srcCtx("UnrollingBase.scala:28:66") // And(b24001,b24002)
    val x36418 = OpDef(op=BitAnd, inputs=List(x36416, x36417)).name("x36418").ctrl(x36421).srcCtx("UnrollingBase.scala:28:66") // And(x36416,x36417)
    val x36419 = OpDef(op=BitAnd, inputs=List(x36418, b22364)).name("x36419").ctrl(x36421).srcCtx("UnrollingBase.scala:28:66") // And(x36418,b22364)
    val x36420_x36393_d0 = WriteMem(x36393_d0, x36415).name("x36420_x36393_d0").ctrl(x36421).srcCtx("CellsPar.scala:207:15") // RegWrite(x36393,x36415,x36419)
    val x36420_x36393_d1 = WriteMem(x36393_d1, x36415).name("x36420_x36393_d1").ctrl(x36421).srcCtx("CellsPar.scala:207:15") // RegWrite(x36393,x36415,x36419)
    val x36455 = UnitController(style=SeqPipe, level=InnerControl).name("x36455").ctrl(x36456).srcCtx("CellsPar.scala:194:42") // UnitPipe(List(b24049, b24045, b24001, b24002, b22364),Block(Const(())))
    val x36422 = OpDef(op=FixAdd, inputs=List(b24000, b24048)).name("x36422").ctrl(x36455).srcCtx("CellsPar.scala:209:31:colOffset") // FixAdd(b24000,b24048)
    val x36423 = ReadMem(x36393_d0).name("x36423").ctrl(x36455).srcCtx("CellsPar.scala:210:28") // RegRead(x36393)
    val x36424 = OpDef(op=FixEql, inputs=List(b23999, Const(0))).name("x36424").ctrl(x36455).srcCtx("CellsPar.scala:210:42") // FixEql(b23999,Const(0))
    val x36425 = OpDef(op=BitAnd, inputs=List(b24049, b24045)).name("x36425").ctrl(x36455).srcCtx("UnrollingBase.scala:28:66") // And(b24049,b24045)
    val x36426 = OpDef(op=BitAnd, inputs=List(b24001, b24002)).name("x36426").ctrl(x36455).srcCtx("UnrollingBase.scala:28:66") // And(b24001,b24002)
    val x36427 = OpDef(op=BitAnd, inputs=List(x36425, x36426)).name("x36427").ctrl(x36455).srcCtx("UnrollingBase.scala:28:66") // And(x36425,x36426)
    val x36428 = OpDef(op=BitAnd, inputs=List(x36427, b22364)).name("x36428").ctrl(x36455).srcCtx("UnrollingBase.scala:28:66") // And(x36427,b22364)
    val x36429 = LoadBanks(List(x34778_d3_b0), List(Const(0), x36422)).name("x36429").ctrl(x36455).srcCtx("CellsPar.scala:210:52") // SRAMLoad(x34778,ArrayBuffer(Const(1), Const(2048)),List(Const(0), x36422),Const(0),x36428)
    val x36430 = LoadBanks(List(x34774_d1_b0), List(b24044, x36422)).name("x36430").ctrl(x36455).srcCtx("CellsPar.scala:210:91") // SRAMLoad(x34774,ArrayBuffer(Const(1), Const(512)),List(b24044, x36422),Const(0),x36428)
    val x36431 = OpDef(op=MuxOp, inputs=List(x36424, x36429, x36430)).name("x36431").ctrl(x36455).srcCtx("CellsPar.scala:210:39") // Mux(x36424,x36429,x36430)
    val x36432 = OpDef(op=FixAdd, inputs=List(x36423, x36431)).name("x36432").ctrl(x36455).srcCtx("CellsPar.scala:210:34:ele") // FixAdd(x36423,x36431)
    val x36433 = OpDef(op=FixLeq, inputs=List(Const(1520), b23999)).name("x36433").ctrl(x36455).srcCtx("CellsPar.scala:218:46") // FixLeq(Const(1520),b23999)
    // x36434 = FixConvert(Const(0),TRUE,_8,_24) x.tp=FixPt[TRUE,_32,_0] {
    val x36434_int1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("01111111111111111111111111111111"))).name("x36434_int1").ctrl(x36455).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x36434_int2 = OpDef(op=FixSla, inputs=List(x36434_int1, Const(24))).name("x36434_int2").ctrl(x36455).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x36434_frac1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("00000000000000000000000000000000"))).name("x36434_frac1").ctrl(x36455).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x36434_frac2 = OpDef(op=FixSla, inputs=List(x36434_frac1, Const(24))).name("x36434_frac2").ctrl(x36455).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x36434 = OpDef(op=BitOr, inputs=List(x36434_int2, x36434_frac2)).name("x36434").ctrl(x36455).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    // }
    val x36435 = OpDef(op=FixAdd, inputs=List(x36432, x36434)).name("x36435").ctrl(x36455).srcCtx("CellsPar.scala:218:87") // FixAdd(x36432,x36434)
    val x36436 = OpDef(op=FixSra, inputs=List(x36435, Const(1))).name("x36436").ctrl(x36455).srcCtx("CellsPar.scala:29:22") // FixRsh(x36435,Const(1))
    val x36437 = x36436 // FixConvert(x36436,TRUE,_8,_24) (Same Type. No op)
    val x36438 = OpDef(op=FixAbs, inputs=List(x36437)).name("x36438").ctrl(x36455).srcCtx("CellsPar.scala:37:19:absp") // FixAbs(x36437)
    val x36439 = OpDef(op=FixLt, inputs=List(Const(2.5), x36438)).name("x36439").ctrl(x36455).srcCtx("CellsPar.scala:51:23:cond1") // FixLt(Const(2.5),x36438)
    val x36440 = OpDef(op=FixLt, inputs=List(Const(0.5), x36438)).name("x36440").ctrl(x36455).srcCtx("CellsPar.scala:54:35") // FixLt(Const(0.5),x36438)
    val x36441 = OpDef(op=FixLeq, inputs=List(x36438, Const(2.5))).name("x36441").ctrl(x36455).srcCtx("CellsPar.scala:54:52") // FixLeq(x36438,Const(2.5))
    val x36442 = OpDef(op=BitAnd, inputs=List(x36440, x36441)).name("x36442").ctrl(x36455).srcCtx("CellsPar.scala:54:43:cond2") // And(x36440,x36441)
    val x36443 = OpDef(op=FixSra, inputs=List(x36438, Const(2))).name("x36443").ctrl(x36455).srcCtx("CellsPar.scala:56:21:div4") // FixRsh(x36438,Const(2))
    val x36444 = OpDef(op=FixAdd, inputs=List(x36443, Const(0.375))).name("x36444").ctrl(x36455).srcCtx("CellsPar.scala:57:27:div4Offset") // FixAdd(x36443,Const(0.375))
    val x36445 = OpDef(op=MuxOp, inputs=List(x36442, x36444, x36438)).name("x36445").ctrl(x36455).srcCtx("CellsPar.scala:58:20:body2") // Mux(x36442,x36444,x36438)
    val x36446 = OpDef(op=MuxOp, inputs=List(x36439, Const(1.0), x36445)).name("x36446").ctrl(x36455).srcCtx("CellsPar.scala:60:20:absre") // Mux(x36439,Const(1),x36445)
    val x36447 = OpDef(op=FixNeg, inputs=List(x36446)).name("x36447").ctrl(x36455).srcCtx("CellsPar.scala:62:29:mabre") // FixNeg(x36446)
    val x36448 = OpDef(op=FixLt, inputs=List(x36437, Const(0.0))).name("x36448").ctrl(x36455).srcCtx("CellsPar.scala:63:22") // FixLt(x36437,Const(0))
    val x36449 = OpDef(op=MuxOp, inputs=List(x36448, x36447, x36446)).name("x36449").ctrl(x36455).srcCtx("CellsPar.scala:63:17:re") // Mux(x36448,x36447,x36446)
    val x36450 = x36449 // FixConvert(x36449,TRUE,_8,_24) (Same Type. No op)
    val x36451 = OpDef(op=FixAdd, inputs=List(x36450, Const(1.0))).name("x36451").ctrl(x36455).srcCtx("CellsPar.scala:29:33") // FixAdd(x36450,Const(1))
    val x36452 = OpDef(op=FixSra, inputs=List(x36451, Const(1))).name("x36452").ctrl(x36455).srcCtx("CellsPar.scala:29:44") // FixRsh(x36451,Const(1))
    val x36453 = OpDef(op=MuxOp, inputs=List(x36433, x36452, x36432)).name("x36453").ctrl(x36455).srcCtx("CellsPar.scala:218:43") // Mux(x36433,x36452,x36432)
    val x36454 = StoreBanks(List(x34774_d0_b0, x34774_d1_b0), List(b24044, x36422), x36453).name("x36454").ctrl(x36455).srcCtx("CellsPar.scala:218:38") // SRAMStore(x34774,ArrayBuffer(Const(1), Const(512)),List(b24044, x36422),Const(0),x36453,x36428)
    val x36459 = Counter(min=Const(0), max=Const(512), step=Const(16), par=1).name("x36459").ctrl(x36822).srcCtx("CellsPar.scala:190:58") // CounterNew(Const(0),Const(512),Const(16),Const(1))
    val x36460 = Counter(min=Const(0), max=Const(1536), step=Const(16), par=1).name("x36460").ctrl(x36822).srcCtx("CellsPar.scala:190:33") // CounterNew(Const(0),Const(1536),Const(16),Const(1))
    val x36461 = CounterChain(List(x36460,x36459)).name("x36461").ctrl(x36822).srcCtx("CellsPar.scala:190:72") // CounterChainNew(List(x36460, x36459))
    val x36564 = LoopController(style=MetaPipe, level=OuterControl, cchain=x36461).name("x36564").ctrl(x36822).srcCtx("CellsPar.scala:190:72") // UnrolledForeach(List(b22364),x36461,Block(Const(())),List(List(b24121), List(b24122)),List(List(b24123), List(b24124)))
    val b24121 = CounterIter(x36460, Some(0)).name("b24121").ctrl(x36564) // b24121
    val b24123 = Const(true).name("b24123").ctrl(x36564) // b24123
    val b24122 = CounterIter(x36459, Some(0)).name("b24122").ctrl(x36564) // b24122
    val b24124 = Const(true).name("b24124").ctrl(x36564) // b24124
    val x36462_d0_b0 = SRAM(size=256, banking=Strided(banks=1, stride=16)).name("x36462_d0_b0").ctrl(x36564).srcCtx("CellsPar.scala:191:33:tileKernel") // x36462 = SRAMNew(ArrayBuffer(Const(16), Const(16)))
    isAccum(x36462_d0_b0) = false
    bufferDepthOf(x36462_d0_b0) = 2
    val x36465 = UnitController(style=SeqPipe, level=InnerControl).name("x36465").ctrl(x36564).srcCtx("CellsPar.scala:190:72") // UnitPipe(List(b24123, b24124, b22364),Block(Const(())))
    val x36463 = OpDef(op=FixAdd, inputs=List(b24121, Const(16))).name("x36463").ctrl(x36465).srcCtx("CellsPar.scala:192:36") // FixAdd(b24121,Const(16))
    val x36464 = OpDef(op=FixAdd, inputs=List(b24122, Const(16))).name("x36464").ctrl(x36465).srcCtx("CellsPar.scala:192:45") // FixAdd(b24122,Const(16))
    val x36466 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x36466").ctrl(x36564).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x36467 = CounterChain(List(x36466)).name("x36467").ctrl(x36564).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x36466))
    val x36496 = LoopController(style=StreamPipe, level=OuterControl, cchain=x36467).name("x36496").ctrl(x36564).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b24123, b24124, b22364),x36467,Block(Const(())),List(List(b24131)),List(List(b24132)))
    val b24131 = CounterIter(x36466, Some(0)).name("b24131").ctrl(x36496) // b24131
    val b24132 = Const(true).name("b24132").ctrl(x36496) // b24132
    val b36961 = StreamOut(field="offset").name("b36961").ctrl(x36496).srcCtx("CellsPar.scala:192:20") // x36468 = StreamOutNew(BurstCmdBus)
    isAccum(b36961) = false
    bufferDepthOf(b36961) = 1
    val b36962 = StreamOut(field="size").name("b36962").ctrl(x36496).srcCtx("CellsPar.scala:192:20") // x36468 = StreamOutNew(BurstCmdBus)
    isAccum(b36962) = false
    bufferDepthOf(b36962) = 1
    val x36469 = StreamIn(field="data").name("x36469").ctrl(x36496).srcCtx("CellsPar.scala:192:20") // x36469 = StreamInNew(BurstDataBus())
    isAccum(x36469) = false
    bufferDepthOf(x36469) = 1
    val x36484 = UnitController(style=SeqPipe, level=InnerControl).name("x36484").ctrl(x36496).srcCtx("CellsPar.scala:192:20") // UnitPipe(List(b24132, b24123, b24124, b22364),Block(x36483))
    val x36470 = OpDef(op=FixAdd, inputs=List(b24121, b24131)).name("x36470").ctrl(x36484).srcCtx("CellsPar.scala:192:20") // FixAdd(b24121,b24131)
    val x36471 = x36470 // FixConvert(x36470,TRUE,_32,_0) (Same Type. No op)
    val x36472 = OpDef(op=FixSla, inputs=List(x36471, Const(9))).name("x36472").ctrl(x36484).srcCtx("CellsPar.scala:192:20") // FixLsh(x36471,Const(9))
    val x36473 = b24122 // FixConvert(b24122,TRUE,_32,_0) (Same Type. No op)
    val x36474 = OpDef(op=FixAdd, inputs=List(x36472, x36473)).name("x36474").ctrl(x36484).srcCtx("CellsPar.scala:192:20") // FixAdd(x36472,x36473)
    val x36475 = OpDef(op=FixSla, inputs=List(x36474, Const(2))).name("x36475").ctrl(x36484).srcCtx("CellsPar.scala:192:20") // FixLsh(x36474,Const(2))
    val x36476 = x36475 // FixConvert(x36475,TRUE,_64,_0)
    val x36477 = DramAddress(x34703).name("x36477").ctrl(x36484).srcCtx("CellsPar.scala:192:20") // GetDRAMAddress(x34703)
    val x36479_x36478 = OpDef(op=FixAdd, inputs=List(x36476, x36477)).name("x36479_x36478").ctrl(x36484).srcCtx("CellsPar.scala:192:20") // FixAdd(x36476,x36477)
    // x36479 = SimpleStruct(ArrayBuffer((offset,x36478), (size,Const(64)), (isLoad,Const(true))))
    val x36480 = OpDef(op=BitAnd, inputs=List(b24132, b24123)).name("x36480").ctrl(x36484).srcCtx("UnrollingBase.scala:28:66") // And(b24132,b24123)
    val x36481 = OpDef(op=BitAnd, inputs=List(b24124, b22364)).name("x36481").ctrl(x36484).srcCtx("UnrollingBase.scala:28:66") // And(b24124,b22364)
    val x36482 = OpDef(op=BitAnd, inputs=List(x36480, x36481)).name("x36482").ctrl(x36484).srcCtx("UnrollingBase.scala:28:66") // And(x36480,x36481)
    val x36483_b36963_b36961 = WriteMem(b36961, x36479_x36478).name("x36483_b36963_b36961").ctrl(x36484).srcCtx("CellsPar.scala:192:20") // StreamWrite(x36468,x36479,x36482)
    val x36483_b36964_b36962 = WriteMem(b36962, Const(64)).name("x36483_b36964_b36962").ctrl(x36484).srcCtx("CellsPar.scala:192:20") // StreamWrite(x36468,x36479,x36482)
    val x36485 = FringeDenseLoad(dram=List(x34703), cmdStream=List(b36961, b36962), dataStream=List(x36469)).name("x36485").ctrl(x36496).srcCtx("CellsPar.scala:192:20") // FringeDenseLoad(x34703,x36468,x36469)
    val x36486 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x36486").ctrl(x36496).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x36487 = CounterChain(List(x36486)).name("x36487").ctrl(x36496).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x36486))
    val x36495 = LoopController(style=InnerPipe, level=InnerControl, cchain=x36487).name("x36495").ctrl(x36496).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b24132, b24123, b24124, b22364),x36487,Block(Const(())),List(List(b24153)),List(List(b24154)))
    val b24153 = CounterIter(x36486, None).name("b24153").ctrl(x36495) // b24153
    val b24154 = Const(true).name("b24154").ctrl(x36495) // b24154
    val x36488 = OpDef(op=BitAnd, inputs=List(b24154, b24132)).name("x36488").ctrl(x36495).srcCtx("UnrollingBase.scala:28:66") // And(b24154,b24132)
    val x36489 = OpDef(op=BitAnd, inputs=List(b24123, b24124)).name("x36489").ctrl(x36495).srcCtx("UnrollingBase.scala:28:66") // And(b24123,b24124)
    val x36490 = OpDef(op=BitAnd, inputs=List(x36488, x36489)).name("x36490").ctrl(x36495).srcCtx("UnrollingBase.scala:28:66") // And(x36488,x36489)
    val x36491 = OpDef(op=BitAnd, inputs=List(x36490, b22364)).name("x36491").ctrl(x36495).srcCtx("UnrollingBase.scala:28:66") // And(x36490,b22364)
    val x36492_x36492 = ReadMem(x36469).name("x36492_x36492").ctrl(x36495).srcCtx("CellsPar.scala:192:20") // ParStreamRead(x36469,List(x36491))
    val x36493_x36493 = x36492_x36492 // x36493 = VectorApply(x36492,0)
    val x36494 = StoreBanks(List(x36462_d0_b0), List(b24131, b24153), x36493_x36493).name("x36494").ctrl(x36495).srcCtx("CellsPar.scala:192:20") // ParSRAMStore(x36462,List(List(b24131, b24153)),List(x36493),List(x36491))
    val x36497 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x36497").ctrl(x36564).srcCtx("CellsPar.scala:193:34") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x36498 = CounterChain(List(x36497)).name("x36498").ctrl(x36564).srcCtx("CellsPar.scala:193:46") // CounterChainNew(List(x36497))
    val x36563 = LoopController(style=MetaPipe, level=OuterControl, cchain=x36498).name("x36563").ctrl(x36564).srcCtx("CellsPar.scala:193:46") // UnrolledForeach(List(b24123, b24124, b22364),x36498,Block(Const(())),List(List(b24166)),List(List(b24167)))
    val b24166 = CounterIter(x36497, Some(0)).name("b24166").ctrl(x36563) // b24166
    val b24167 = Const(true).name("b24167").ctrl(x36563) // b24167
    val x36499 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x36499").ctrl(x36563).srcCtx("CellsPar.scala:194:28") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x36500 = CounterChain(List(x36499)).name("x36500").ctrl(x36563).srcCtx("CellsPar.scala:194:42") // CounterChainNew(List(x36499))
    val x36562 = LoopController(style=MetaPipe, level=OuterControl, cchain=x36500).name("x36562").ctrl(x36563).srcCtx("CellsPar.scala:194:42") // UnrolledForeach(List(b24167, b24123, b24124, b22364),x36500,Block(Const(())),List(List(b24170)),List(List(b24171)))
    val b24170 = CounterIter(x36499, Some(0)).name("b24170").ctrl(x36562) // b24170
    val b24171 = Const(true).name("b24171").ctrl(x36562) // b24171
    val x36501_d0 = Reg(init=Some(0.0)).name("x36501_d0").ctrl(x36562).srcCtx("CellsPar.scala:195:34:prod") // x36501 = RegNew(Const(0))
    isAccum(x36501_d0) = false
    bufferDepthOf(x36501_d0) = 2
    val x36501_d1 = Reg(init=Some(0.0)).name("x36501_d1").ctrl(x36562).srcCtx("CellsPar.scala:195:34:prod") // x36501 = RegNew(Const(0))
    isAccum(x36501_d1) = true
    bufferDepthOf(x36501_d1) = 1
    val x36502 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x36502").ctrl(x36562).srcCtx("CellsPar.scala:195:43") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x36503 = CounterChain(List(x36502)).name("x36503").ctrl(x36562).srcCtx("CellsPar.scala:207:15") // CounterChainNew(List(x36502))
    val x36529 = LoopController(style=InnerPipe, level=InnerControl, cchain=x36503).name("x36529").ctrl(x36562).srcCtx("CellsPar.scala:207:15") // UnrolledReduce(List(b24171, b24167, b24123, b24124, b22364),x36503,x36501,Block((x36501) => Const(())),List(List(b24175)),List(List(b24176)))
    val b24175 = CounterIter(x36502, None).name("b24175").ctrl(x36529) // b24175
    val b24176 = Const(true).name("b24176").ctrl(x36529) // b24176
    val x36504 = OpDef(op=FixAdd, inputs=List(b24121, b24175)).name("x36504").ctrl(x36529).srcCtx("CellsPar.scala:196:42:reduce_size_offset") // FixAdd(b24121,b24175)
    val x36505 = OpDef(op=BitAnd, inputs=List(b24176, b24171)).name("x36505").ctrl(x36529).srcCtx("UnrollingBase.scala:28:66") // And(b24176,b24171)
    val x36506 = OpDef(op=BitAnd, inputs=List(b24167, b24123)).name("x36506").ctrl(x36529).srcCtx("UnrollingBase.scala:28:66") // And(b24167,b24123)
    val x36507 = OpDef(op=BitAnd, inputs=List(b24124, b22364)).name("x36507").ctrl(x36529).srcCtx("UnrollingBase.scala:28:66") // And(b24124,b22364)
    val x36508 = OpDef(op=BitAnd, inputs=List(x36505, x36506)).name("x36508").ctrl(x36529).srcCtx("UnrollingBase.scala:28:66") // And(x36505,x36506)
    val x36509 = OpDef(op=BitAnd, inputs=List(x36508, x36507)).name("x36509").ctrl(x36529).srcCtx("UnrollingBase.scala:28:66") // And(x36508,x36507)
    val x36510 = LoadBanks(List(x36462_d0_b0), List(b24175, b24170)).name("x36510").ctrl(x36529).srcCtx("CellsPar.scala:203:34:tk") // ParSRAMLoad(x36462,List(List(b24175, b24170)),List(x36509))
    val x36511 = x36510 // x36511 = VectorApply(x36510,0)
    val x36512 = LoadBanks(List(x34765_d3_b0), List(b24166, x36504)).name("x36512").ctrl(x36529).srcCtx("CellsPar.scala:204:28") // ParSRAMLoad(x34765,List(List(b24166, x36504)),List(x36509))
    val x36513 = x36512 // x36513 = VectorApply(x36512,0)
    val x36514 = OpDef(op=FixMul, inputs=List(x36513, x36511)).name("x36514").ctrl(x36529).srcCtx("CellsPar.scala:204:52:tempA") // FixMul(x36513,x36511)
    val x36515 = OpDef(op=FixSub, inputs=List(x36504, Const(512))).name("x36515").ctrl(x36529).srcCtx("CellsPar.scala:205:51") // FixSub(x36504,Const(512))
    val x36516 = LoadBanks(List(x34772_d3_b0), List(b24166, x36515)).name("x36516").ctrl(x36529).srcCtx("CellsPar.scala:205:28") // ParSRAMLoad(x34772,List(List(b24166, x36515)),List(x36509))
    val x36517 = x36516 // x36517 = VectorApply(x36516,0)
    val x36518 = OpDef(op=FixMul, inputs=List(x36517, x36511)).name("x36518").ctrl(x36529).srcCtx("CellsPar.scala:205:67:tempB") // FixMul(x36517,x36511)
    val x36519 = OpDef(op=FixLt, inputs=List(x36504, Const(512))).name("x36519").ctrl(x36529).srcCtx("CellsPar.scala:206:38") // FixLt(x36504,Const(512))
    val x36520 = OpDef(op=MuxOp, inputs=List(x36519, x36514, x36518)).name("x36520").ctrl(x36529).srcCtx("CellsPar.scala:206:18") // Mux(x36519,x36514,x36518)
    val x36521 = ReadMem(x36501_d1).name("x36521").ctrl(x36529).srcCtx("CellsPar.scala:207:15") // RegRead(x36501)
    val x36522 = OpDef(op=FixEql, inputs=List(b24175, Const(0))).name("x36522").ctrl(x36529).srcCtx("CellsPar.scala:207:15") // FixEql(b24175,Const(0))
    val x36523 = ReduceAccumOp(op=FixAdd, input=x36520, accum=x36521).name("x36523").ctrl(x36529).srcCtx("CellsPar.scala:207:17") // FixAdd(x36520,x36521)
    val x36524 = OpDef(op=BitAnd, inputs=List(b24171, b24167)).name("x36524").ctrl(x36529).srcCtx("UnrollingBase.scala:28:66") // And(b24171,b24167)
    val x36525 = OpDef(op=BitAnd, inputs=List(b24123, b24124)).name("x36525").ctrl(x36529).srcCtx("UnrollingBase.scala:28:66") // And(b24123,b24124)
    val x36526 = OpDef(op=BitAnd, inputs=List(x36524, x36525)).name("x36526").ctrl(x36529).srcCtx("UnrollingBase.scala:28:66") // And(x36524,x36525)
    val x36527 = OpDef(op=BitAnd, inputs=List(x36526, b22364)).name("x36527").ctrl(x36529).srcCtx("UnrollingBase.scala:28:66") // And(x36526,b22364)
    val x36528_x36501_d0 = WriteMem(x36501_d0, x36523).name("x36528_x36501_d0").ctrl(x36529).srcCtx("CellsPar.scala:207:15") // RegWrite(x36501,x36523,x36527)
    val x36528_x36501_d1 = WriteMem(x36501_d1, x36523).name("x36528_x36501_d1").ctrl(x36529).srcCtx("CellsPar.scala:207:15") // RegWrite(x36501,x36523,x36527)
    val x36561 = UnitController(style=SeqPipe, level=InnerControl).name("x36561").ctrl(x36562).srcCtx("CellsPar.scala:194:42") // UnitPipe(List(b24171, b24167, b24123, b24124, b22364),Block(Const(())))
    val x36530 = OpDef(op=FixAdd, inputs=List(b24122, b24170)).name("x36530").ctrl(x36561).srcCtx("CellsPar.scala:209:31:colOffset") // FixAdd(b24122,b24170)
    val x36531 = ReadMem(x36501_d0).name("x36531").ctrl(x36561).srcCtx("CellsPar.scala:210:28") // RegRead(x36501)
    val x36532 = OpDef(op=FixEql, inputs=List(b24121, Const(0))).name("x36532").ctrl(x36561).srcCtx("CellsPar.scala:210:42") // FixEql(b24121,Const(0))
    val x36533 = OpDef(op=FixAdd, inputs=List(x36530, Const(512))).name("x36533").ctrl(x36561).srcCtx("CellsPar.scala:210:66") // FixAdd(x36530,Const(512))
    val x36534 = OpDef(op=BitAnd, inputs=List(b24171, b24167)).name("x36534").ctrl(x36561).srcCtx("UnrollingBase.scala:28:66") // And(b24171,b24167)
    val x36535 = OpDef(op=BitAnd, inputs=List(b24123, b24124)).name("x36535").ctrl(x36561).srcCtx("UnrollingBase.scala:28:66") // And(b24123,b24124)
    val x36536 = OpDef(op=BitAnd, inputs=List(x36534, x36535)).name("x36536").ctrl(x36561).srcCtx("UnrollingBase.scala:28:66") // And(x36534,x36535)
    val x36537 = OpDef(op=BitAnd, inputs=List(x36536, b22364)).name("x36537").ctrl(x36561).srcCtx("UnrollingBase.scala:28:66") // And(x36536,b22364)
    val x36538 = LoadBanks(List(x34778_d2_b0), List(Const(0), x36533)).name("x36538").ctrl(x36561).srcCtx("CellsPar.scala:210:52") // SRAMLoad(x34778,ArrayBuffer(Const(1), Const(2048)),List(Const(0), x36533),Const(0),x36537)
    val x36539 = LoadBanks(List(x34775_d1_b0), List(b24166, x36530)).name("x36539").ctrl(x36561).srcCtx("CellsPar.scala:210:91") // SRAMLoad(x34775,ArrayBuffer(Const(1), Const(512)),List(b24166, x36530),Const(0),x36537)
    val x36540 = OpDef(op=MuxOp, inputs=List(x36532, x36538, x36539)).name("x36540").ctrl(x36561).srcCtx("CellsPar.scala:210:39") // Mux(x36532,x36538,x36539)
    val x36541 = OpDef(op=FixAdd, inputs=List(x36531, x36540)).name("x36541").ctrl(x36561).srcCtx("CellsPar.scala:210:34:ele") // FixAdd(x36531,x36540)
    val x36542 = OpDef(op=FixLeq, inputs=List(Const(1520), b24121)).name("x36542").ctrl(x36561).srcCtx("CellsPar.scala:218:46") // FixLeq(Const(1520),b24121)
    // x36543 = FixConvert(Const(0),TRUE,_8,_24) x.tp=FixPt[TRUE,_32,_0] {
    val x36543_int1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("01111111111111111111111111111111"))).name("x36543_int1").ctrl(x36561).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x36543_int2 = OpDef(op=FixSla, inputs=List(x36543_int1, Const(24))).name("x36543_int2").ctrl(x36561).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x36543_frac1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("00000000000000000000000000000000"))).name("x36543_frac1").ctrl(x36561).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x36543_frac2 = OpDef(op=FixSla, inputs=List(x36543_frac1, Const(24))).name("x36543_frac2").ctrl(x36561).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x36543 = OpDef(op=BitOr, inputs=List(x36543_int2, x36543_frac2)).name("x36543").ctrl(x36561).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    // }
    val x36544 = OpDef(op=FixAdd, inputs=List(x36541, x36543)).name("x36544").ctrl(x36561).srcCtx("CellsPar.scala:218:87") // FixAdd(x36541,x36543)
    val x36545 = x36544 // FixConvert(x36544,TRUE,_8,_24) (Same Type. No op)
    val x36546 = OpDef(op=FixAbs, inputs=List(x36545)).name("x36546").ctrl(x36561).srcCtx("CellsPar.scala:37:19:absp") // FixAbs(x36545)
    val x36547 = OpDef(op=FixLt, inputs=List(Const(2.5), x36546)).name("x36547").ctrl(x36561).srcCtx("CellsPar.scala:51:23:cond1") // FixLt(Const(2.5),x36546)
    val x36548 = OpDef(op=FixLt, inputs=List(Const(0.5), x36546)).name("x36548").ctrl(x36561).srcCtx("CellsPar.scala:54:35") // FixLt(Const(0.5),x36546)
    val x36549 = OpDef(op=FixLeq, inputs=List(x36546, Const(2.5))).name("x36549").ctrl(x36561).srcCtx("CellsPar.scala:54:52") // FixLeq(x36546,Const(2.5))
    val x36550 = OpDef(op=BitAnd, inputs=List(x36548, x36549)).name("x36550").ctrl(x36561).srcCtx("CellsPar.scala:54:43:cond2") // And(x36548,x36549)
    val x36551 = OpDef(op=FixSra, inputs=List(x36546, Const(2))).name("x36551").ctrl(x36561).srcCtx("CellsPar.scala:56:21:div4") // FixRsh(x36546,Const(2))
    val x36552 = OpDef(op=FixAdd, inputs=List(x36551, Const(0.375))).name("x36552").ctrl(x36561).srcCtx("CellsPar.scala:57:27:div4Offset") // FixAdd(x36551,Const(0.375))
    val x36553 = OpDef(op=MuxOp, inputs=List(x36550, x36552, x36546)).name("x36553").ctrl(x36561).srcCtx("CellsPar.scala:58:20:body2") // Mux(x36550,x36552,x36546)
    val x36554 = OpDef(op=MuxOp, inputs=List(x36547, Const(1.0), x36553)).name("x36554").ctrl(x36561).srcCtx("CellsPar.scala:60:20:absre") // Mux(x36547,Const(1),x36553)
    val x36555 = OpDef(op=FixNeg, inputs=List(x36554)).name("x36555").ctrl(x36561).srcCtx("CellsPar.scala:62:29:mabre") // FixNeg(x36554)
    val x36556 = OpDef(op=FixLt, inputs=List(x36545, Const(0.0))).name("x36556").ctrl(x36561).srcCtx("CellsPar.scala:63:22") // FixLt(x36545,Const(0))
    val x36557 = OpDef(op=MuxOp, inputs=List(x36556, x36555, x36554)).name("x36557").ctrl(x36561).srcCtx("CellsPar.scala:63:17:re") // Mux(x36556,x36555,x36554)
    val x36558 = x36557 // FixConvert(x36557,TRUE,_8,_24) (Same Type. No op)
    val x36559 = OpDef(op=MuxOp, inputs=List(x36542, x36558, x36541)).name("x36559").ctrl(x36561).srcCtx("CellsPar.scala:218:43") // Mux(x36542,x36558,x36541)
    val x36560 = StoreBanks(List(x34775_d0_b0, x34775_d1_b0), List(b24166, x36530), x36559).name("x36560").ctrl(x36561).srcCtx("CellsPar.scala:218:38") // SRAMStore(x34775,ArrayBuffer(Const(1), Const(512)),List(b24166, x36530),Const(0),x36559,x36537)
    val x36565 = Counter(min=Const(0), max=Const(512), step=Const(16), par=1).name("x36565").ctrl(x36822).srcCtx("CellsPar.scala:190:58") // CounterNew(Const(0),Const(512),Const(16),Const(1))
    val x36566 = Counter(min=Const(0), max=Const(1536), step=Const(16), par=1).name("x36566").ctrl(x36822).srcCtx("CellsPar.scala:190:33") // CounterNew(Const(0),Const(1536),Const(16),Const(1))
    val x36567 = CounterChain(List(x36566,x36565)).name("x36567").ctrl(x36822).srcCtx("CellsPar.scala:190:72") // CounterChainNew(List(x36566, x36565))
    val x36673 = LoopController(style=MetaPipe, level=OuterControl, cchain=x36567).name("x36673").ctrl(x36822).srcCtx("CellsPar.scala:190:72") // UnrolledForeach(List(b22364),x36567,Block(Const(())),List(List(b24241), List(b24242)),List(List(b24243), List(b24244)))
    val b24241 = CounterIter(x36566, Some(0)).name("b24241").ctrl(x36673) // b24241
    val b24243 = Const(true).name("b24243").ctrl(x36673) // b24243
    val b24242 = CounterIter(x36565, Some(0)).name("b24242").ctrl(x36673) // b24242
    val b24244 = Const(true).name("b24244").ctrl(x36673) // b24244
    val x36568_d0_b0 = SRAM(size=256, banking=Strided(banks=1, stride=16)).name("x36568_d0_b0").ctrl(x36673).srcCtx("CellsPar.scala:191:33:tileKernel") // x36568 = SRAMNew(ArrayBuffer(Const(16), Const(16)))
    isAccum(x36568_d0_b0) = false
    bufferDepthOf(x36568_d0_b0) = 2
    val x36571 = UnitController(style=SeqPipe, level=InnerControl).name("x36571").ctrl(x36673).srcCtx("CellsPar.scala:190:72") // UnitPipe(List(b24243, b24244, b22364),Block(Const(())))
    val x36569 = OpDef(op=FixAdd, inputs=List(b24241, Const(16))).name("x36569").ctrl(x36571).srcCtx("CellsPar.scala:192:36") // FixAdd(b24241,Const(16))
    val x36570 = OpDef(op=FixAdd, inputs=List(b24242, Const(16))).name("x36570").ctrl(x36571).srcCtx("CellsPar.scala:192:45") // FixAdd(b24242,Const(16))
    val x36572 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x36572").ctrl(x36673).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x36573 = CounterChain(List(x36572)).name("x36573").ctrl(x36673).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x36572))
    val x36602 = LoopController(style=StreamPipe, level=OuterControl, cchain=x36573).name("x36602").ctrl(x36673).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b24243, b24244, b22364),x36573,Block(Const(())),List(List(b24251)),List(List(b24252)))
    val b24251 = CounterIter(x36572, Some(0)).name("b24251").ctrl(x36602) // b24251
    val b24252 = Const(true).name("b24252").ctrl(x36602) // b24252
    val b36965 = StreamOut(field="offset").name("b36965").ctrl(x36602).srcCtx("CellsPar.scala:192:20") // x36574 = StreamOutNew(BurstCmdBus)
    isAccum(b36965) = false
    bufferDepthOf(b36965) = 1
    val b36966 = StreamOut(field="size").name("b36966").ctrl(x36602).srcCtx("CellsPar.scala:192:20") // x36574 = StreamOutNew(BurstCmdBus)
    isAccum(b36966) = false
    bufferDepthOf(b36966) = 1
    val x36575 = StreamIn(field="data").name("x36575").ctrl(x36602).srcCtx("CellsPar.scala:192:20") // x36575 = StreamInNew(BurstDataBus())
    isAccum(x36575) = false
    bufferDepthOf(x36575) = 1
    val x36590 = UnitController(style=SeqPipe, level=InnerControl).name("x36590").ctrl(x36602).srcCtx("CellsPar.scala:192:20") // UnitPipe(List(b24252, b24243, b24244, b22364),Block(x36589))
    val x36576 = OpDef(op=FixAdd, inputs=List(b24241, b24251)).name("x36576").ctrl(x36590).srcCtx("CellsPar.scala:192:20") // FixAdd(b24241,b24251)
    val x36577 = x36576 // FixConvert(x36576,TRUE,_32,_0) (Same Type. No op)
    val x36578 = OpDef(op=FixSla, inputs=List(x36577, Const(9))).name("x36578").ctrl(x36590).srcCtx("CellsPar.scala:192:20") // FixLsh(x36577,Const(9))
    val x36579 = b24242 // FixConvert(b24242,TRUE,_32,_0) (Same Type. No op)
    val x36580 = OpDef(op=FixAdd, inputs=List(x36578, x36579)).name("x36580").ctrl(x36590).srcCtx("CellsPar.scala:192:20") // FixAdd(x36578,x36579)
    val x36581 = OpDef(op=FixSla, inputs=List(x36580, Const(2))).name("x36581").ctrl(x36590).srcCtx("CellsPar.scala:192:20") // FixLsh(x36580,Const(2))
    val x36582 = x36581 // FixConvert(x36581,TRUE,_64,_0)
    val x36583 = DramAddress(x34704).name("x36583").ctrl(x36590).srcCtx("CellsPar.scala:192:20") // GetDRAMAddress(x34704)
    val x36585_x36584 = OpDef(op=FixAdd, inputs=List(x36582, x36583)).name("x36585_x36584").ctrl(x36590).srcCtx("CellsPar.scala:192:20") // FixAdd(x36582,x36583)
    // x36585 = SimpleStruct(ArrayBuffer((offset,x36584), (size,Const(64)), (isLoad,Const(true))))
    val x36586 = OpDef(op=BitAnd, inputs=List(b24252, b24243)).name("x36586").ctrl(x36590).srcCtx("UnrollingBase.scala:28:66") // And(b24252,b24243)
    val x36587 = OpDef(op=BitAnd, inputs=List(b24244, b22364)).name("x36587").ctrl(x36590).srcCtx("UnrollingBase.scala:28:66") // And(b24244,b22364)
    val x36588 = OpDef(op=BitAnd, inputs=List(x36586, x36587)).name("x36588").ctrl(x36590).srcCtx("UnrollingBase.scala:28:66") // And(x36586,x36587)
    val x36589_b36967_b36965 = WriteMem(b36965, x36585_x36584).name("x36589_b36967_b36965").ctrl(x36590).srcCtx("CellsPar.scala:192:20") // StreamWrite(x36574,x36585,x36588)
    val x36589_b36968_b36966 = WriteMem(b36966, Const(64)).name("x36589_b36968_b36966").ctrl(x36590).srcCtx("CellsPar.scala:192:20") // StreamWrite(x36574,x36585,x36588)
    val x36591 = FringeDenseLoad(dram=List(x34704), cmdStream=List(b36965, b36966), dataStream=List(x36575)).name("x36591").ctrl(x36602).srcCtx("CellsPar.scala:192:20") // FringeDenseLoad(x34704,x36574,x36575)
    val x36592 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x36592").ctrl(x36602).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x36593 = CounterChain(List(x36592)).name("x36593").ctrl(x36602).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x36592))
    val x36601 = LoopController(style=InnerPipe, level=InnerControl, cchain=x36593).name("x36601").ctrl(x36602).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b24252, b24243, b24244, b22364),x36593,Block(Const(())),List(List(b24273)),List(List(b24274)))
    val b24273 = CounterIter(x36592, None).name("b24273").ctrl(x36601) // b24273
    val b24274 = Const(true).name("b24274").ctrl(x36601) // b24274
    val x36594 = OpDef(op=BitAnd, inputs=List(b24274, b24252)).name("x36594").ctrl(x36601).srcCtx("UnrollingBase.scala:28:66") // And(b24274,b24252)
    val x36595 = OpDef(op=BitAnd, inputs=List(b24243, b24244)).name("x36595").ctrl(x36601).srcCtx("UnrollingBase.scala:28:66") // And(b24243,b24244)
    val x36596 = OpDef(op=BitAnd, inputs=List(x36594, x36595)).name("x36596").ctrl(x36601).srcCtx("UnrollingBase.scala:28:66") // And(x36594,x36595)
    val x36597 = OpDef(op=BitAnd, inputs=List(x36596, b22364)).name("x36597").ctrl(x36601).srcCtx("UnrollingBase.scala:28:66") // And(x36596,b22364)
    val x36598_x36598 = ReadMem(x36575).name("x36598_x36598").ctrl(x36601).srcCtx("CellsPar.scala:192:20") // ParStreamRead(x36575,List(x36597))
    val x36599_x36599 = x36598_x36598 // x36599 = VectorApply(x36598,0)
    val x36600 = StoreBanks(List(x36568_d0_b0), List(b24251, b24273), x36599_x36599).name("x36600").ctrl(x36601).srcCtx("CellsPar.scala:192:20") // ParSRAMStore(x36568,List(List(b24251, b24273)),List(x36599),List(x36597))
    val x36603 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x36603").ctrl(x36673).srcCtx("CellsPar.scala:193:34") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x36604 = CounterChain(List(x36603)).name("x36604").ctrl(x36673).srcCtx("CellsPar.scala:193:46") // CounterChainNew(List(x36603))
    val x36672 = LoopController(style=MetaPipe, level=OuterControl, cchain=x36604).name("x36672").ctrl(x36673).srcCtx("CellsPar.scala:193:46") // UnrolledForeach(List(b24243, b24244, b22364),x36604,Block(Const(())),List(List(b24286)),List(List(b24287)))
    val b24286 = CounterIter(x36603, Some(0)).name("b24286").ctrl(x36672) // b24286
    val b24287 = Const(true).name("b24287").ctrl(x36672) // b24287
    val x36605 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x36605").ctrl(x36672).srcCtx("CellsPar.scala:194:28") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x36606 = CounterChain(List(x36605)).name("x36606").ctrl(x36672).srcCtx("CellsPar.scala:194:42") // CounterChainNew(List(x36605))
    val x36671 = LoopController(style=MetaPipe, level=OuterControl, cchain=x36606).name("x36671").ctrl(x36672).srcCtx("CellsPar.scala:194:42") // UnrolledForeach(List(b24287, b24243, b24244, b22364),x36606,Block(Const(())),List(List(b24290)),List(List(b24291)))
    val b24290 = CounterIter(x36605, Some(0)).name("b24290").ctrl(x36671) // b24290
    val b24291 = Const(true).name("b24291").ctrl(x36671) // b24291
    val x36607_d0 = Reg(init=Some(0.0)).name("x36607_d0").ctrl(x36671).srcCtx("CellsPar.scala:195:34:prod") // x36607 = RegNew(Const(0))
    isAccum(x36607_d0) = false
    bufferDepthOf(x36607_d0) = 2
    val x36607_d1 = Reg(init=Some(0.0)).name("x36607_d1").ctrl(x36671).srcCtx("CellsPar.scala:195:34:prod") // x36607 = RegNew(Const(0))
    isAccum(x36607_d1) = true
    bufferDepthOf(x36607_d1) = 1
    val x36608 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x36608").ctrl(x36671).srcCtx("CellsPar.scala:195:43") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x36609 = CounterChain(List(x36608)).name("x36609").ctrl(x36671).srcCtx("CellsPar.scala:207:15") // CounterChainNew(List(x36608))
    val x36635 = LoopController(style=InnerPipe, level=InnerControl, cchain=x36609).name("x36635").ctrl(x36671).srcCtx("CellsPar.scala:207:15") // UnrolledReduce(List(b24291, b24287, b24243, b24244, b22364),x36609,x36607,Block((x36607) => Const(())),List(List(b24295)),List(List(b24296)))
    val b24295 = CounterIter(x36608, None).name("b24295").ctrl(x36635) // b24295
    val b24296 = Const(true).name("b24296").ctrl(x36635) // b24296
    val x36610 = OpDef(op=FixAdd, inputs=List(b24241, b24295)).name("x36610").ctrl(x36635).srcCtx("CellsPar.scala:196:42:reduce_size_offset") // FixAdd(b24241,b24295)
    val x36611 = OpDef(op=BitAnd, inputs=List(b24296, b24291)).name("x36611").ctrl(x36635).srcCtx("UnrollingBase.scala:28:66") // And(b24296,b24291)
    val x36612 = OpDef(op=BitAnd, inputs=List(b24287, b24243)).name("x36612").ctrl(x36635).srcCtx("UnrollingBase.scala:28:66") // And(b24287,b24243)
    val x36613 = OpDef(op=BitAnd, inputs=List(b24244, b22364)).name("x36613").ctrl(x36635).srcCtx("UnrollingBase.scala:28:66") // And(b24244,b22364)
    val x36614 = OpDef(op=BitAnd, inputs=List(x36611, x36612)).name("x36614").ctrl(x36635).srcCtx("UnrollingBase.scala:28:66") // And(x36611,x36612)
    val x36615 = OpDef(op=BitAnd, inputs=List(x36614, x36613)).name("x36615").ctrl(x36635).srcCtx("UnrollingBase.scala:28:66") // And(x36614,x36613)
    val x36616 = LoadBanks(List(x36568_d0_b0), List(b24295, b24290)).name("x36616").ctrl(x36635).srcCtx("CellsPar.scala:203:34:tk") // ParSRAMLoad(x36568,List(List(b24295, b24290)),List(x36615))
    val x36617 = x36616 // x36617 = VectorApply(x36616,0)
    val x36618 = LoadBanks(List(x34765_d2_b0), List(b24286, x36610)).name("x36618").ctrl(x36635).srcCtx("CellsPar.scala:204:28") // ParSRAMLoad(x34765,List(List(b24286, x36610)),List(x36615))
    val x36619 = x36618 // x36619 = VectorApply(x36618,0)
    val x36620 = OpDef(op=FixMul, inputs=List(x36619, x36617)).name("x36620").ctrl(x36635).srcCtx("CellsPar.scala:204:52:tempA") // FixMul(x36619,x36617)
    val x36621 = OpDef(op=FixSub, inputs=List(x36610, Const(512))).name("x36621").ctrl(x36635).srcCtx("CellsPar.scala:205:51") // FixSub(x36610,Const(512))
    val x36622 = LoadBanks(List(x34772_d2_b0), List(b24286, x36621)).name("x36622").ctrl(x36635).srcCtx("CellsPar.scala:205:28") // ParSRAMLoad(x34772,List(List(b24286, x36621)),List(x36615))
    val x36623 = x36622 // x36623 = VectorApply(x36622,0)
    val x36624 = OpDef(op=FixMul, inputs=List(x36623, x36617)).name("x36624").ctrl(x36635).srcCtx("CellsPar.scala:205:67:tempB") // FixMul(x36623,x36617)
    val x36625 = OpDef(op=FixLt, inputs=List(x36610, Const(512))).name("x36625").ctrl(x36635).srcCtx("CellsPar.scala:206:38") // FixLt(x36610,Const(512))
    val x36626 = OpDef(op=MuxOp, inputs=List(x36625, x36620, x36624)).name("x36626").ctrl(x36635).srcCtx("CellsPar.scala:206:18") // Mux(x36625,x36620,x36624)
    val x36627 = ReadMem(x36607_d1).name("x36627").ctrl(x36635).srcCtx("CellsPar.scala:207:15") // RegRead(x36607)
    val x36628 = OpDef(op=FixEql, inputs=List(b24295, Const(0))).name("x36628").ctrl(x36635).srcCtx("CellsPar.scala:207:15") // FixEql(b24295,Const(0))
    val x36629 = ReduceAccumOp(op=FixAdd, input=x36626, accum=x36627).name("x36629").ctrl(x36635).srcCtx("CellsPar.scala:207:17") // FixAdd(x36626,x36627)
    val x36630 = OpDef(op=BitAnd, inputs=List(b24291, b24287)).name("x36630").ctrl(x36635).srcCtx("UnrollingBase.scala:28:66") // And(b24291,b24287)
    val x36631 = OpDef(op=BitAnd, inputs=List(b24243, b24244)).name("x36631").ctrl(x36635).srcCtx("UnrollingBase.scala:28:66") // And(b24243,b24244)
    val x36632 = OpDef(op=BitAnd, inputs=List(x36630, x36631)).name("x36632").ctrl(x36635).srcCtx("UnrollingBase.scala:28:66") // And(x36630,x36631)
    val x36633 = OpDef(op=BitAnd, inputs=List(x36632, b22364)).name("x36633").ctrl(x36635).srcCtx("UnrollingBase.scala:28:66") // And(x36632,b22364)
    val x36634_x36607_d0 = WriteMem(x36607_d0, x36629).name("x36634_x36607_d0").ctrl(x36635).srcCtx("CellsPar.scala:207:15") // RegWrite(x36607,x36629,x36633)
    val x36634_x36607_d1 = WriteMem(x36607_d1, x36629).name("x36634_x36607_d1").ctrl(x36635).srcCtx("CellsPar.scala:207:15") // RegWrite(x36607,x36629,x36633)
    val x36670 = UnitController(style=SeqPipe, level=InnerControl).name("x36670").ctrl(x36671).srcCtx("CellsPar.scala:194:42") // UnitPipe(List(b24291, b24287, b24243, b24244, b22364),Block(Const(())))
    val x36636 = OpDef(op=FixAdd, inputs=List(b24242, b24290)).name("x36636").ctrl(x36670).srcCtx("CellsPar.scala:209:31:colOffset") // FixAdd(b24242,b24290)
    val x36637 = ReadMem(x36607_d0).name("x36637").ctrl(x36670).srcCtx("CellsPar.scala:210:28") // RegRead(x36607)
    val x36638 = OpDef(op=FixEql, inputs=List(b24241, Const(0))).name("x36638").ctrl(x36670).srcCtx("CellsPar.scala:210:42") // FixEql(b24241,Const(0))
    val x36639 = OpDef(op=FixAdd, inputs=List(x36636, Const(1024))).name("x36639").ctrl(x36670).srcCtx("CellsPar.scala:210:66") // FixAdd(x36636,Const(1024))
    val x36640 = OpDef(op=BitAnd, inputs=List(b24291, b24287)).name("x36640").ctrl(x36670).srcCtx("UnrollingBase.scala:28:66") // And(b24291,b24287)
    val x36641 = OpDef(op=BitAnd, inputs=List(b24243, b24244)).name("x36641").ctrl(x36670).srcCtx("UnrollingBase.scala:28:66") // And(b24243,b24244)
    val x36642 = OpDef(op=BitAnd, inputs=List(x36640, x36641)).name("x36642").ctrl(x36670).srcCtx("UnrollingBase.scala:28:66") // And(x36640,x36641)
    val x36643 = OpDef(op=BitAnd, inputs=List(x36642, b22364)).name("x36643").ctrl(x36670).srcCtx("UnrollingBase.scala:28:66") // And(x36642,b22364)
    val x36644 = LoadBanks(List(x34778_d1_b0), List(Const(0), x36639)).name("x36644").ctrl(x36670).srcCtx("CellsPar.scala:210:52") // SRAMLoad(x34778,ArrayBuffer(Const(1), Const(2048)),List(Const(0), x36639),Const(0),x36643)
    val x36645 = LoadBanks(List(x34776_d1_b0), List(b24286, x36636)).name("x36645").ctrl(x36670).srcCtx("CellsPar.scala:210:91") // SRAMLoad(x34776,ArrayBuffer(Const(1), Const(512)),List(b24286, x36636),Const(0),x36643)
    val x36646 = OpDef(op=MuxOp, inputs=List(x36638, x36644, x36645)).name("x36646").ctrl(x36670).srcCtx("CellsPar.scala:210:39") // Mux(x36638,x36644,x36645)
    val x36647 = OpDef(op=FixAdd, inputs=List(x36637, x36646)).name("x36647").ctrl(x36670).srcCtx("CellsPar.scala:210:34:ele") // FixAdd(x36637,x36646)
    val x36648 = OpDef(op=FixLeq, inputs=List(Const(1520), b24241)).name("x36648").ctrl(x36670).srcCtx("CellsPar.scala:218:46") // FixLeq(Const(1520),b24241)
    // x36649 = FixConvert(Const(1),TRUE,_8,_24) x.tp=FixPt[TRUE,_32,_0] {
    val x36649_int1 = OpDef(op=BitAnd, inputs=List(Const(1), Const("01111111111111111111111111111111"))).name("x36649_int1").ctrl(x36670).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(1),TRUE,_8,_24)
    val x36649_int2 = OpDef(op=FixSla, inputs=List(x36649_int1, Const(24))).name("x36649_int2").ctrl(x36670).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(1),TRUE,_8,_24)
    val x36649_frac1 = OpDef(op=BitAnd, inputs=List(Const(1), Const("00000000000000000000000000000000"))).name("x36649_frac1").ctrl(x36670).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(1),TRUE,_8,_24)
    val x36649_frac2 = OpDef(op=FixSla, inputs=List(x36649_frac1, Const(24))).name("x36649_frac2").ctrl(x36670).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(1),TRUE,_8,_24)
    val x36649 = OpDef(op=BitOr, inputs=List(x36649_int2, x36649_frac2)).name("x36649").ctrl(x36670).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(1),TRUE,_8,_24)
    // }
    val x36650 = OpDef(op=FixAdd, inputs=List(x36647, x36649)).name("x36650").ctrl(x36670).srcCtx("CellsPar.scala:218:87") // FixAdd(x36647,x36649)
    val x36651 = OpDef(op=FixSra, inputs=List(x36650, Const(1))).name("x36651").ctrl(x36670).srcCtx("CellsPar.scala:29:22") // FixRsh(x36650,Const(1))
    val x36652 = x36651 // FixConvert(x36651,TRUE,_8,_24) (Same Type. No op)
    val x36653 = OpDef(op=FixAbs, inputs=List(x36652)).name("x36653").ctrl(x36670).srcCtx("CellsPar.scala:37:19:absp") // FixAbs(x36652)
    val x36654 = OpDef(op=FixLt, inputs=List(Const(2.5), x36653)).name("x36654").ctrl(x36670).srcCtx("CellsPar.scala:51:23:cond1") // FixLt(Const(2.5),x36653)
    val x36655 = OpDef(op=FixLt, inputs=List(Const(0.5), x36653)).name("x36655").ctrl(x36670).srcCtx("CellsPar.scala:54:35") // FixLt(Const(0.5),x36653)
    val x36656 = OpDef(op=FixLeq, inputs=List(x36653, Const(2.5))).name("x36656").ctrl(x36670).srcCtx("CellsPar.scala:54:52") // FixLeq(x36653,Const(2.5))
    val x36657 = OpDef(op=BitAnd, inputs=List(x36655, x36656)).name("x36657").ctrl(x36670).srcCtx("CellsPar.scala:54:43:cond2") // And(x36655,x36656)
    val x36658 = OpDef(op=FixSra, inputs=List(x36653, Const(2))).name("x36658").ctrl(x36670).srcCtx("CellsPar.scala:56:21:div4") // FixRsh(x36653,Const(2))
    val x36659 = OpDef(op=FixAdd, inputs=List(x36658, Const(0.375))).name("x36659").ctrl(x36670).srcCtx("CellsPar.scala:57:27:div4Offset") // FixAdd(x36658,Const(0.375))
    val x36660 = OpDef(op=MuxOp, inputs=List(x36657, x36659, x36653)).name("x36660").ctrl(x36670).srcCtx("CellsPar.scala:58:20:body2") // Mux(x36657,x36659,x36653)
    val x36661 = OpDef(op=MuxOp, inputs=List(x36654, Const(1.0), x36660)).name("x36661").ctrl(x36670).srcCtx("CellsPar.scala:60:20:absre") // Mux(x36654,Const(1),x36660)
    val x36662 = OpDef(op=FixNeg, inputs=List(x36661)).name("x36662").ctrl(x36670).srcCtx("CellsPar.scala:62:29:mabre") // FixNeg(x36661)
    val x36663 = OpDef(op=FixLt, inputs=List(x36652, Const(0.0))).name("x36663").ctrl(x36670).srcCtx("CellsPar.scala:63:22") // FixLt(x36652,Const(0))
    val x36664 = OpDef(op=MuxOp, inputs=List(x36663, x36662, x36661)).name("x36664").ctrl(x36670).srcCtx("CellsPar.scala:63:17:re") // Mux(x36663,x36662,x36661)
    val x36665 = x36664 // FixConvert(x36664,TRUE,_8,_24) (Same Type. No op)
    val x36666 = OpDef(op=FixAdd, inputs=List(x36665, Const(1.0))).name("x36666").ctrl(x36670).srcCtx("CellsPar.scala:29:33") // FixAdd(x36665,Const(1))
    val x36667 = OpDef(op=FixSra, inputs=List(x36666, Const(1))).name("x36667").ctrl(x36670).srcCtx("CellsPar.scala:29:44") // FixRsh(x36666,Const(1))
    val x36668 = OpDef(op=MuxOp, inputs=List(x36648, x36667, x36647)).name("x36668").ctrl(x36670).srcCtx("CellsPar.scala:218:43") // Mux(x36648,x36667,x36647)
    val x36669 = StoreBanks(List(x34776_d0_b0, x34776_d1_b0), List(b24286, x36636), x36668).name("x36669").ctrl(x36670).srcCtx("CellsPar.scala:218:38") // SRAMStore(x34776,ArrayBuffer(Const(1), Const(512)),List(b24286, x36636),Const(0),x36668,x36643)
    val x36674 = Counter(min=Const(0), max=Const(512), step=Const(16), par=1).name("x36674").ctrl(x36822).srcCtx("CellsPar.scala:190:58") // CounterNew(Const(0),Const(512),Const(16),Const(1))
    val x36675 = Counter(min=Const(0), max=Const(1536), step=Const(16), par=1).name("x36675").ctrl(x36822).srcCtx("CellsPar.scala:190:33") // CounterNew(Const(0),Const(1536),Const(16),Const(1))
    val x36676 = CounterChain(List(x36675,x36674)).name("x36676").ctrl(x36822).srcCtx("CellsPar.scala:190:72") // CounterChainNew(List(x36675, x36674))
    val x36782 = LoopController(style=MetaPipe, level=OuterControl, cchain=x36676).name("x36782").ctrl(x36822).srcCtx("CellsPar.scala:190:72") // UnrolledForeach(List(b22364),x36676,Block(Const(())),List(List(b24364), List(b24365)),List(List(b24366), List(b24367)))
    val b24364 = CounterIter(x36675, Some(0)).name("b24364").ctrl(x36782) // b24364
    val b24366 = Const(true).name("b24366").ctrl(x36782) // b24366
    val b24365 = CounterIter(x36674, Some(0)).name("b24365").ctrl(x36782) // b24365
    val b24367 = Const(true).name("b24367").ctrl(x36782) // b24367
    val x36677_d0_b0 = SRAM(size=256, banking=Strided(banks=1, stride=16)).name("x36677_d0_b0").ctrl(x36782).srcCtx("CellsPar.scala:191:33:tileKernel") // x36677 = SRAMNew(ArrayBuffer(Const(16), Const(16)))
    isAccum(x36677_d0_b0) = false
    bufferDepthOf(x36677_d0_b0) = 2
    val x36680 = UnitController(style=SeqPipe, level=InnerControl).name("x36680").ctrl(x36782).srcCtx("CellsPar.scala:190:72") // UnitPipe(List(b24366, b24367, b22364),Block(Const(())))
    val x36678 = OpDef(op=FixAdd, inputs=List(b24364, Const(16))).name("x36678").ctrl(x36680).srcCtx("CellsPar.scala:192:36") // FixAdd(b24364,Const(16))
    val x36679 = OpDef(op=FixAdd, inputs=List(b24365, Const(16))).name("x36679").ctrl(x36680).srcCtx("CellsPar.scala:192:45") // FixAdd(b24365,Const(16))
    val x36681 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x36681").ctrl(x36782).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x36682 = CounterChain(List(x36681)).name("x36682").ctrl(x36782).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x36681))
    val x36711 = LoopController(style=StreamPipe, level=OuterControl, cchain=x36682).name("x36711").ctrl(x36782).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b24366, b24367, b22364),x36682,Block(Const(())),List(List(b24374)),List(List(b24375)))
    val b24374 = CounterIter(x36681, Some(0)).name("b24374").ctrl(x36711) // b24374
    val b24375 = Const(true).name("b24375").ctrl(x36711) // b24375
    val b36969 = StreamOut(field="offset").name("b36969").ctrl(x36711).srcCtx("CellsPar.scala:192:20") // x36683 = StreamOutNew(BurstCmdBus)
    isAccum(b36969) = false
    bufferDepthOf(b36969) = 1
    val b36970 = StreamOut(field="size").name("b36970").ctrl(x36711).srcCtx("CellsPar.scala:192:20") // x36683 = StreamOutNew(BurstCmdBus)
    isAccum(b36970) = false
    bufferDepthOf(b36970) = 1
    val x36684 = StreamIn(field="data").name("x36684").ctrl(x36711).srcCtx("CellsPar.scala:192:20") // x36684 = StreamInNew(BurstDataBus())
    isAccum(x36684) = false
    bufferDepthOf(x36684) = 1
    val x36699 = UnitController(style=SeqPipe, level=InnerControl).name("x36699").ctrl(x36711).srcCtx("CellsPar.scala:192:20") // UnitPipe(List(b24375, b24366, b24367, b22364),Block(x36698))
    val x36685 = OpDef(op=FixAdd, inputs=List(b24364, b24374)).name("x36685").ctrl(x36699).srcCtx("CellsPar.scala:192:20") // FixAdd(b24364,b24374)
    val x36686 = x36685 // FixConvert(x36685,TRUE,_32,_0) (Same Type. No op)
    val x36687 = OpDef(op=FixSla, inputs=List(x36686, Const(9))).name("x36687").ctrl(x36699).srcCtx("CellsPar.scala:192:20") // FixLsh(x36686,Const(9))
    val x36688 = b24365 // FixConvert(b24365,TRUE,_32,_0) (Same Type. No op)
    val x36689 = OpDef(op=FixAdd, inputs=List(x36687, x36688)).name("x36689").ctrl(x36699).srcCtx("CellsPar.scala:192:20") // FixAdd(x36687,x36688)
    val x36690 = OpDef(op=FixSla, inputs=List(x36689, Const(2))).name("x36690").ctrl(x36699).srcCtx("CellsPar.scala:192:20") // FixLsh(x36689,Const(2))
    val x36691 = x36690 // FixConvert(x36690,TRUE,_64,_0)
    val x36692 = DramAddress(x34705).name("x36692").ctrl(x36699).srcCtx("CellsPar.scala:192:20") // GetDRAMAddress(x34705)
    val x36694_x36693 = OpDef(op=FixAdd, inputs=List(x36691, x36692)).name("x36694_x36693").ctrl(x36699).srcCtx("CellsPar.scala:192:20") // FixAdd(x36691,x36692)
    // x36694 = SimpleStruct(ArrayBuffer((offset,x36693), (size,Const(64)), (isLoad,Const(true))))
    val x36695 = OpDef(op=BitAnd, inputs=List(b24375, b24366)).name("x36695").ctrl(x36699).srcCtx("UnrollingBase.scala:28:66") // And(b24375,b24366)
    val x36696 = OpDef(op=BitAnd, inputs=List(b24367, b22364)).name("x36696").ctrl(x36699).srcCtx("UnrollingBase.scala:28:66") // And(b24367,b22364)
    val x36697 = OpDef(op=BitAnd, inputs=List(x36695, x36696)).name("x36697").ctrl(x36699).srcCtx("UnrollingBase.scala:28:66") // And(x36695,x36696)
    val x36698_b36971_b36969 = WriteMem(b36969, x36694_x36693).name("x36698_b36971_b36969").ctrl(x36699).srcCtx("CellsPar.scala:192:20") // StreamWrite(x36683,x36694,x36697)
    val x36698_b36972_b36970 = WriteMem(b36970, Const(64)).name("x36698_b36972_b36970").ctrl(x36699).srcCtx("CellsPar.scala:192:20") // StreamWrite(x36683,x36694,x36697)
    val x36700 = FringeDenseLoad(dram=List(x34705), cmdStream=List(b36969, b36970), dataStream=List(x36684)).name("x36700").ctrl(x36711).srcCtx("CellsPar.scala:192:20") // FringeDenseLoad(x34705,x36683,x36684)
    val x36701 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x36701").ctrl(x36711).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x36702 = CounterChain(List(x36701)).name("x36702").ctrl(x36711).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x36701))
    val x36710 = LoopController(style=InnerPipe, level=InnerControl, cchain=x36702).name("x36710").ctrl(x36711).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b24375, b24366, b24367, b22364),x36702,Block(Const(())),List(List(b24396)),List(List(b24397)))
    val b24396 = CounterIter(x36701, None).name("b24396").ctrl(x36710) // b24396
    val b24397 = Const(true).name("b24397").ctrl(x36710) // b24397
    val x36703 = OpDef(op=BitAnd, inputs=List(b24397, b24375)).name("x36703").ctrl(x36710).srcCtx("UnrollingBase.scala:28:66") // And(b24397,b24375)
    val x36704 = OpDef(op=BitAnd, inputs=List(b24366, b24367)).name("x36704").ctrl(x36710).srcCtx("UnrollingBase.scala:28:66") // And(b24366,b24367)
    val x36705 = OpDef(op=BitAnd, inputs=List(x36703, x36704)).name("x36705").ctrl(x36710).srcCtx("UnrollingBase.scala:28:66") // And(x36703,x36704)
    val x36706 = OpDef(op=BitAnd, inputs=List(x36705, b22364)).name("x36706").ctrl(x36710).srcCtx("UnrollingBase.scala:28:66") // And(x36705,b22364)
    val x36707_x36707 = ReadMem(x36684).name("x36707_x36707").ctrl(x36710).srcCtx("CellsPar.scala:192:20") // ParStreamRead(x36684,List(x36706))
    val x36708_x36708 = x36707_x36707 // x36708 = VectorApply(x36707,0)
    val x36709 = StoreBanks(List(x36677_d0_b0), List(b24374, b24396), x36708_x36708).name("x36709").ctrl(x36710).srcCtx("CellsPar.scala:192:20") // ParSRAMStore(x36677,List(List(b24374, b24396)),List(x36708),List(x36706))
    val x36712 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x36712").ctrl(x36782).srcCtx("CellsPar.scala:193:34") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x36713 = CounterChain(List(x36712)).name("x36713").ctrl(x36782).srcCtx("CellsPar.scala:193:46") // CounterChainNew(List(x36712))
    val x36781 = LoopController(style=MetaPipe, level=OuterControl, cchain=x36713).name("x36781").ctrl(x36782).srcCtx("CellsPar.scala:193:46") // UnrolledForeach(List(b24366, b24367, b22364),x36713,Block(Const(())),List(List(b24409)),List(List(b24410)))
    val b24409 = CounterIter(x36712, Some(0)).name("b24409").ctrl(x36781) // b24409
    val b24410 = Const(true).name("b24410").ctrl(x36781) // b24410
    val x36714 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x36714").ctrl(x36781).srcCtx("CellsPar.scala:194:28") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x36715 = CounterChain(List(x36714)).name("x36715").ctrl(x36781).srcCtx("CellsPar.scala:194:42") // CounterChainNew(List(x36714))
    val x36780 = LoopController(style=MetaPipe, level=OuterControl, cchain=x36715).name("x36780").ctrl(x36781).srcCtx("CellsPar.scala:194:42") // UnrolledForeach(List(b24410, b24366, b24367, b22364),x36715,Block(Const(())),List(List(b24413)),List(List(b24414)))
    val b24413 = CounterIter(x36714, Some(0)).name("b24413").ctrl(x36780) // b24413
    val b24414 = Const(true).name("b24414").ctrl(x36780) // b24414
    val x36716_d0 = Reg(init=Some(0.0)).name("x36716_d0").ctrl(x36780).srcCtx("CellsPar.scala:195:34:prod") // x36716 = RegNew(Const(0))
    isAccum(x36716_d0) = false
    bufferDepthOf(x36716_d0) = 2
    val x36716_d1 = Reg(init=Some(0.0)).name("x36716_d1").ctrl(x36780).srcCtx("CellsPar.scala:195:34:prod") // x36716 = RegNew(Const(0))
    isAccum(x36716_d1) = true
    bufferDepthOf(x36716_d1) = 1
    val x36717 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x36717").ctrl(x36780).srcCtx("CellsPar.scala:195:43") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x36718 = CounterChain(List(x36717)).name("x36718").ctrl(x36780).srcCtx("CellsPar.scala:207:15") // CounterChainNew(List(x36717))
    val x36744 = LoopController(style=InnerPipe, level=InnerControl, cchain=x36718).name("x36744").ctrl(x36780).srcCtx("CellsPar.scala:207:15") // UnrolledReduce(List(b24414, b24410, b24366, b24367, b22364),x36718,x36716,Block((x36716) => Const(())),List(List(b24418)),List(List(b24419)))
    val b24418 = CounterIter(x36717, None).name("b24418").ctrl(x36744) // b24418
    val b24419 = Const(true).name("b24419").ctrl(x36744) // b24419
    val x36719 = OpDef(op=FixAdd, inputs=List(b24364, b24418)).name("x36719").ctrl(x36744).srcCtx("CellsPar.scala:196:42:reduce_size_offset") // FixAdd(b24364,b24418)
    val x36720 = OpDef(op=BitAnd, inputs=List(b24419, b24414)).name("x36720").ctrl(x36744).srcCtx("UnrollingBase.scala:28:66") // And(b24419,b24414)
    val x36721 = OpDef(op=BitAnd, inputs=List(b24410, b24366)).name("x36721").ctrl(x36744).srcCtx("UnrollingBase.scala:28:66") // And(b24410,b24366)
    val x36722 = OpDef(op=BitAnd, inputs=List(b24367, b22364)).name("x36722").ctrl(x36744).srcCtx("UnrollingBase.scala:28:66") // And(b24367,b22364)
    val x36723 = OpDef(op=BitAnd, inputs=List(x36720, x36721)).name("x36723").ctrl(x36744).srcCtx("UnrollingBase.scala:28:66") // And(x36720,x36721)
    val x36724 = OpDef(op=BitAnd, inputs=List(x36723, x36722)).name("x36724").ctrl(x36744).srcCtx("UnrollingBase.scala:28:66") // And(x36723,x36722)
    val x36725 = LoadBanks(List(x36677_d0_b0), List(b24418, b24413)).name("x36725").ctrl(x36744).srcCtx("CellsPar.scala:203:34:tk") // ParSRAMLoad(x36677,List(List(b24418, b24413)),List(x36724))
    val x36726 = x36725 // x36726 = VectorApply(x36725,0)
    val x36727 = LoadBanks(List(x34765_d1_b0), List(b24409, x36719)).name("x36727").ctrl(x36744).srcCtx("CellsPar.scala:204:28") // ParSRAMLoad(x34765,List(List(b24409, x36719)),List(x36724))
    val x36728 = x36727 // x36728 = VectorApply(x36727,0)
    val x36729 = OpDef(op=FixMul, inputs=List(x36728, x36726)).name("x36729").ctrl(x36744).srcCtx("CellsPar.scala:204:52:tempA") // FixMul(x36728,x36726)
    val x36730 = OpDef(op=FixSub, inputs=List(x36719, Const(512))).name("x36730").ctrl(x36744).srcCtx("CellsPar.scala:205:51") // FixSub(x36719,Const(512))
    val x36731 = LoadBanks(List(x34772_d1_b0), List(b24409, x36730)).name("x36731").ctrl(x36744).srcCtx("CellsPar.scala:205:28") // ParSRAMLoad(x34772,List(List(b24409, x36730)),List(x36724))
    val x36732 = x36731 // x36732 = VectorApply(x36731,0)
    val x36733 = OpDef(op=FixMul, inputs=List(x36732, x36726)).name("x36733").ctrl(x36744).srcCtx("CellsPar.scala:205:67:tempB") // FixMul(x36732,x36726)
    val x36734 = OpDef(op=FixLt, inputs=List(x36719, Const(512))).name("x36734").ctrl(x36744).srcCtx("CellsPar.scala:206:38") // FixLt(x36719,Const(512))
    val x36735 = OpDef(op=MuxOp, inputs=List(x36734, x36729, x36733)).name("x36735").ctrl(x36744).srcCtx("CellsPar.scala:206:18") // Mux(x36734,x36729,x36733)
    val x36736 = ReadMem(x36716_d1).name("x36736").ctrl(x36744).srcCtx("CellsPar.scala:207:15") // RegRead(x36716)
    val x36737 = OpDef(op=FixEql, inputs=List(b24418, Const(0))).name("x36737").ctrl(x36744).srcCtx("CellsPar.scala:207:15") // FixEql(b24418,Const(0))
    val x36738 = ReduceAccumOp(op=FixAdd, input=x36735, accum=x36736).name("x36738").ctrl(x36744).srcCtx("CellsPar.scala:207:17") // FixAdd(x36735,x36736)
    val x36739 = OpDef(op=BitAnd, inputs=List(b24414, b24410)).name("x36739").ctrl(x36744).srcCtx("UnrollingBase.scala:28:66") // And(b24414,b24410)
    val x36740 = OpDef(op=BitAnd, inputs=List(b24366, b24367)).name("x36740").ctrl(x36744).srcCtx("UnrollingBase.scala:28:66") // And(b24366,b24367)
    val x36741 = OpDef(op=BitAnd, inputs=List(x36739, x36740)).name("x36741").ctrl(x36744).srcCtx("UnrollingBase.scala:28:66") // And(x36739,x36740)
    val x36742 = OpDef(op=BitAnd, inputs=List(x36741, b22364)).name("x36742").ctrl(x36744).srcCtx("UnrollingBase.scala:28:66") // And(x36741,b22364)
    val x36743_x36716_d0 = WriteMem(x36716_d0, x36738).name("x36743_x36716_d0").ctrl(x36744).srcCtx("CellsPar.scala:207:15") // RegWrite(x36716,x36738,x36742)
    val x36743_x36716_d1 = WriteMem(x36716_d1, x36738).name("x36743_x36716_d1").ctrl(x36744).srcCtx("CellsPar.scala:207:15") // RegWrite(x36716,x36738,x36742)
    val x36779 = UnitController(style=SeqPipe, level=InnerControl).name("x36779").ctrl(x36780).srcCtx("CellsPar.scala:194:42") // UnitPipe(List(b24414, b24410, b24366, b24367, b22364),Block(Const(())))
    val x36745 = OpDef(op=FixAdd, inputs=List(b24365, b24413)).name("x36745").ctrl(x36779).srcCtx("CellsPar.scala:209:31:colOffset") // FixAdd(b24365,b24413)
    val x36746 = ReadMem(x36716_d0).name("x36746").ctrl(x36779).srcCtx("CellsPar.scala:210:28") // RegRead(x36716)
    val x36747 = OpDef(op=FixEql, inputs=List(b24364, Const(0))).name("x36747").ctrl(x36779).srcCtx("CellsPar.scala:210:42") // FixEql(b24364,Const(0))
    val x36748 = OpDef(op=FixAdd, inputs=List(x36745, Const(1536))).name("x36748").ctrl(x36779).srcCtx("CellsPar.scala:210:66") // FixAdd(x36745,Const(1536))
    val x36749 = OpDef(op=BitAnd, inputs=List(b24414, b24410)).name("x36749").ctrl(x36779).srcCtx("UnrollingBase.scala:28:66") // And(b24414,b24410)
    val x36750 = OpDef(op=BitAnd, inputs=List(b24366, b24367)).name("x36750").ctrl(x36779).srcCtx("UnrollingBase.scala:28:66") // And(b24366,b24367)
    val x36751 = OpDef(op=BitAnd, inputs=List(x36749, x36750)).name("x36751").ctrl(x36779).srcCtx("UnrollingBase.scala:28:66") // And(x36749,x36750)
    val x36752 = OpDef(op=BitAnd, inputs=List(x36751, b22364)).name("x36752").ctrl(x36779).srcCtx("UnrollingBase.scala:28:66") // And(x36751,b22364)
    val x36753 = LoadBanks(List(x34778_d0_b0), List(Const(0), x36748)).name("x36753").ctrl(x36779).srcCtx("CellsPar.scala:210:52") // SRAMLoad(x34778,ArrayBuffer(Const(1), Const(2048)),List(Const(0), x36748),Const(0),x36752)
    val x36754 = LoadBanks(List(x34777_d1_b0), List(b24409, x36745)).name("x36754").ctrl(x36779).srcCtx("CellsPar.scala:210:91") // SRAMLoad(x34777,ArrayBuffer(Const(1), Const(512)),List(b24409, x36745),Const(0),x36752)
    val x36755 = OpDef(op=MuxOp, inputs=List(x36747, x36753, x36754)).name("x36755").ctrl(x36779).srcCtx("CellsPar.scala:210:39") // Mux(x36747,x36753,x36754)
    val x36756 = OpDef(op=FixAdd, inputs=List(x36746, x36755)).name("x36756").ctrl(x36779).srcCtx("CellsPar.scala:210:34:ele") // FixAdd(x36746,x36755)
    val x36757 = OpDef(op=FixLeq, inputs=List(Const(1520), b24364)).name("x36757").ctrl(x36779).srcCtx("CellsPar.scala:218:46") // FixLeq(Const(1520),b24364)
    // x36758 = FixConvert(Const(0),TRUE,_8,_24) x.tp=FixPt[TRUE,_32,_0] {
    val x36758_int1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("01111111111111111111111111111111"))).name("x36758_int1").ctrl(x36779).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x36758_int2 = OpDef(op=FixSla, inputs=List(x36758_int1, Const(24))).name("x36758_int2").ctrl(x36779).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x36758_frac1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("00000000000000000000000000000000"))).name("x36758_frac1").ctrl(x36779).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x36758_frac2 = OpDef(op=FixSla, inputs=List(x36758_frac1, Const(24))).name("x36758_frac2").ctrl(x36779).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x36758 = OpDef(op=BitOr, inputs=List(x36758_int2, x36758_frac2)).name("x36758").ctrl(x36779).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    // }
    val x36759 = OpDef(op=FixAdd, inputs=List(x36756, x36758)).name("x36759").ctrl(x36779).srcCtx("CellsPar.scala:218:87") // FixAdd(x36756,x36758)
    val x36760 = OpDef(op=FixSra, inputs=List(x36759, Const(1))).name("x36760").ctrl(x36779).srcCtx("CellsPar.scala:29:22") // FixRsh(x36759,Const(1))
    val x36761 = x36760 // FixConvert(x36760,TRUE,_8,_24) (Same Type. No op)
    val x36762 = OpDef(op=FixAbs, inputs=List(x36761)).name("x36762").ctrl(x36779).srcCtx("CellsPar.scala:37:19:absp") // FixAbs(x36761)
    val x36763 = OpDef(op=FixLt, inputs=List(Const(2.5), x36762)).name("x36763").ctrl(x36779).srcCtx("CellsPar.scala:51:23:cond1") // FixLt(Const(2.5),x36762)
    val x36764 = OpDef(op=FixLt, inputs=List(Const(0.5), x36762)).name("x36764").ctrl(x36779).srcCtx("CellsPar.scala:54:35") // FixLt(Const(0.5),x36762)
    val x36765 = OpDef(op=FixLeq, inputs=List(x36762, Const(2.5))).name("x36765").ctrl(x36779).srcCtx("CellsPar.scala:54:52") // FixLeq(x36762,Const(2.5))
    val x36766 = OpDef(op=BitAnd, inputs=List(x36764, x36765)).name("x36766").ctrl(x36779).srcCtx("CellsPar.scala:54:43:cond2") // And(x36764,x36765)
    val x36767 = OpDef(op=FixSra, inputs=List(x36762, Const(2))).name("x36767").ctrl(x36779).srcCtx("CellsPar.scala:56:21:div4") // FixRsh(x36762,Const(2))
    val x36768 = OpDef(op=FixAdd, inputs=List(x36767, Const(0.375))).name("x36768").ctrl(x36779).srcCtx("CellsPar.scala:57:27:div4Offset") // FixAdd(x36767,Const(0.375))
    val x36769 = OpDef(op=MuxOp, inputs=List(x36766, x36768, x36762)).name("x36769").ctrl(x36779).srcCtx("CellsPar.scala:58:20:body2") // Mux(x36766,x36768,x36762)
    val x36770 = OpDef(op=MuxOp, inputs=List(x36763, Const(1.0), x36769)).name("x36770").ctrl(x36779).srcCtx("CellsPar.scala:60:20:absre") // Mux(x36763,Const(1),x36769)
    val x36771 = OpDef(op=FixNeg, inputs=List(x36770)).name("x36771").ctrl(x36779).srcCtx("CellsPar.scala:62:29:mabre") // FixNeg(x36770)
    val x36772 = OpDef(op=FixLt, inputs=List(x36761, Const(0.0))).name("x36772").ctrl(x36779).srcCtx("CellsPar.scala:63:22") // FixLt(x36761,Const(0))
    val x36773 = OpDef(op=MuxOp, inputs=List(x36772, x36771, x36770)).name("x36773").ctrl(x36779).srcCtx("CellsPar.scala:63:17:re") // Mux(x36772,x36771,x36770)
    val x36774 = x36773 // FixConvert(x36773,TRUE,_8,_24) (Same Type. No op)
    val x36775 = OpDef(op=FixAdd, inputs=List(x36774, Const(1.0))).name("x36775").ctrl(x36779).srcCtx("CellsPar.scala:29:33") // FixAdd(x36774,Const(1))
    val x36776 = OpDef(op=FixSra, inputs=List(x36775, Const(1))).name("x36776").ctrl(x36779).srcCtx("CellsPar.scala:29:44") // FixRsh(x36775,Const(1))
    val x36777 = OpDef(op=MuxOp, inputs=List(x36757, x36776, x36756)).name("x36777").ctrl(x36779).srcCtx("CellsPar.scala:218:43") // Mux(x36757,x36776,x36756)
    val x36778 = StoreBanks(List(x34777_d0_b0, x34777_d1_b0), List(b24409, x36745), x36777).name("x36778").ctrl(x36779).srcCtx("CellsPar.scala:218:38") // SRAMStore(x34777,ArrayBuffer(Const(1), Const(512)),List(b24409, x36745),Const(0),x36777,x36752)
    val x36783 = Counter(min=Const(0), max=Const(512), step=Const(1), par=1).name("x36783").ctrl(x36822).srcCtx("CellsPar.scala:267:50") // CounterNew(Const(0),Const(512),Const(1),Const(1))
    val x36784 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x36784").ctrl(x36822).srcCtx("CellsPar.scala:267:27") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x36785 = CounterChain(List(x36784,x36783)).name("x36785").ctrl(x36822).srcCtx("CellsPar.scala:267:62") // CounterChainNew(List(x36784, x36783))
    val x36821 = LoopController(style=InnerPipe, level=InnerControl, cchain=x36785).name("x36821").ctrl(x36822).srcCtx("CellsPar.scala:267:62") // UnrolledForeach(List(b22364),x36785,Block(Const(())),List(List(b24488), List(b24489)),List(List(b24490), List(b24491)))
    val b24488 = CounterIter(x36784, Some(0)).name("b24488").ctrl(x36821) // b24488
    val b24490 = Const(true).name("b24490").ctrl(x36821) // b24490
    val b24489 = CounterIter(x36783, None).name("b24489").ctrl(x36821) // b24489
    val b24491 = Const(true).name("b24491").ctrl(x36821) // b24491
    val x36786 = OpDef(op=BitAnd, inputs=List(b24490, b24491)).name("x36786").ctrl(x36821).srcCtx("UnrollingBase.scala:28:66") // And(b24490,b24491)
    val x36787 = OpDef(op=BitAnd, inputs=List(x36786, b22364)).name("x36787").ctrl(x36821).srcCtx("UnrollingBase.scala:28:66") // And(x36786,b22364)
    val x36788 = LoadBanks(List(x34773_d1_b0), List(b24488, b24489)).name("x36788").ctrl(x36821).srcCtx("CellsPar.scala:268:22") // ParSRAMLoad(x34773,List(List(b24488, b24489)),List(x36787))
    val x36789 = x36788 // x36789 = VectorApply(x36788,0)
    val x36790 = LoadBanks(List(x34776_d0_b0), List(b24488, b24489)).name("x36790").ctrl(x36821).srcCtx("CellsPar.scala:268:34") // ParSRAMLoad(x34776,List(List(b24488, b24489)),List(x36787))
    val x36791 = x36790 // x36791 = VectorApply(x36790,0)
    val x36792 = OpDef(op=FixMul, inputs=List(x36789, x36791)).name("x36792").ctrl(x36821).srcCtx("CellsPar.scala:268:28") // FixMul(x36789,x36791)
    val x36793 = LoadBanks(List(x34774_d0_b0), List(b24488, b24489)).name("x36793").ctrl(x36821).srcCtx("CellsPar.scala:268:46") // ParSRAMLoad(x34774,List(List(b24488, b24489)),List(x36787))
    val x36794 = x36793 // x36794 = VectorApply(x36793,0)
    val x36795 = LoadBanks(List(x34775_d0_b0), List(b24488, b24489)).name("x36795").ctrl(x36821).srcCtx("CellsPar.scala:268:59") // ParSRAMLoad(x34775,List(List(b24488, b24489)),List(x36787))
    val x36796 = x36795 // x36796 = VectorApply(x36795,0)
    val x36797 = OpDef(op=FixMul, inputs=List(x36794, x36796)).name("x36797").ctrl(x36821).srcCtx("CellsPar.scala:268:52") // FixMul(x36794,x36796)
    val x36798 = OpDef(op=FixAdd, inputs=List(x36792, x36797)).name("x36798").ctrl(x36821).srcCtx("CellsPar.scala:268:40:new_c") // FixAdd(x36792,x36797)
    val x36799 = x36798 // FixConvert(x36798,TRUE,_8,_24) (Same Type. No op)
    val x36800 = OpDef(op=FixAbs, inputs=List(x36799)).name("x36800").ctrl(x36821).srcCtx("CellsPar.scala:37:19:absp") // FixAbs(x36799)
    val x36801 = OpDef(op=FixLt, inputs=List(Const(2.5), x36800)).name("x36801").ctrl(x36821).srcCtx("CellsPar.scala:51:23:cond1") // FixLt(Const(2.5),x36800)
    val x36802 = OpDef(op=FixLt, inputs=List(Const(0.5), x36800)).name("x36802").ctrl(x36821).srcCtx("CellsPar.scala:54:35") // FixLt(Const(0.5),x36800)
    val x36803 = OpDef(op=FixLeq, inputs=List(x36800, Const(2.5))).name("x36803").ctrl(x36821).srcCtx("CellsPar.scala:54:52") // FixLeq(x36800,Const(2.5))
    val x36804 = OpDef(op=BitAnd, inputs=List(x36802, x36803)).name("x36804").ctrl(x36821).srcCtx("CellsPar.scala:54:43:cond2") // And(x36802,x36803)
    val x36805 = OpDef(op=FixSra, inputs=List(x36800, Const(2))).name("x36805").ctrl(x36821).srcCtx("CellsPar.scala:56:21:div4") // FixRsh(x36800,Const(2))
    val x36806 = OpDef(op=FixAdd, inputs=List(x36805, Const(0.375))).name("x36806").ctrl(x36821).srcCtx("CellsPar.scala:57:27:div4Offset") // FixAdd(x36805,Const(0.375))
    val x36807 = OpDef(op=MuxOp, inputs=List(x36804, x36806, x36800)).name("x36807").ctrl(x36821).srcCtx("CellsPar.scala:58:20:body2") // Mux(x36804,x36806,x36800)
    val x36808 = OpDef(op=MuxOp, inputs=List(x36801, Const(1.0), x36807)).name("x36808").ctrl(x36821).srcCtx("CellsPar.scala:60:20:absre") // Mux(x36801,Const(1),x36807)
    val x36809 = OpDef(op=FixNeg, inputs=List(x36808)).name("x36809").ctrl(x36821).srcCtx("CellsPar.scala:62:29:mabre") // FixNeg(x36808)
    val x36810 = OpDef(op=FixLt, inputs=List(x36799, Const(0.0))).name("x36810").ctrl(x36821).srcCtx("CellsPar.scala:63:22") // FixLt(x36799,Const(0))
    val x36811 = OpDef(op=MuxOp, inputs=List(x36810, x36809, x36808)).name("x36811").ctrl(x36821).srcCtx("CellsPar.scala:63:17:re") // Mux(x36810,x36809,x36808)
    val x36812 = x36811 // FixConvert(x36811,TRUE,_8,_24) (Same Type. No op)
    val x36813 = LoadBanks(List(x34777_d0_b0), List(b24488, b24489)).name("x36813").ctrl(x36821).srcCtx("CellsPar.scala:275:47") // ParSRAMLoad(x34777,List(List(b24488, b24489)),List(x36787))
    val x36814 = x36813 // x36814 = VectorApply(x36813,0)
    val x36815 = OpDef(op=FixMul, inputs=List(x36812, x36814)).name("x36815").ctrl(x36821).srcCtx("CellsPar.scala:275:41:prod") // FixMul(x36812,x36814)
    val x36816 = LoadBanks(List(x34765_d0_b0), List(b24488, b24489)).name("x36816").ctrl(x36821).srcCtx("CellsPar.scala:276:43") // ParSRAMLoad(x34765,List(List(b24488, b24489)),List(x36787))
    val x36817 = x36816 // x36817 = VectorApply(x36816,0)
    val x36818 = OpDef(op=FixAdd, inputs=List(x36815, x36817)).name("x36818").ctrl(x36821).srcCtx("CellsPar.scala:276:40") // FixAdd(x36815,x36817)
    val x36819 = StoreBanks(List(x34772_d0_b0, x34772_d1_b0, x34772_d2_b0, x34772_d3_b0, x34772_d4_b0), List(b24488, b24489), x36815).name("x36819").ctrl(x36821).srcCtx("CellsPar.scala:276:17") // ParSRAMStore(x34772,List(List(b24488, b24489)),List(x36815),List(x36787))
    val x36820 = StoreBanks(List(x34773_d0_b0, x34773_d1_b0), List(b24488, b24489), x36798).name("x36820").ctrl(x36821).srcCtx("CellsPar.scala:277:16") // ParSRAMStore(x34773,List(List(b24488, b24489)),List(x36798),List(x36787))
    val x36823 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x36823").ctrl(x36879).srcCtx("CellsPar.scala:175:46") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x36824 = CounterChain(List(x36823)).name("x36824").ctrl(x36879).srcCtx("CellsPar.scala:175:46") // CounterChainNew(List(x36823))
    val x36850 = LoopController(style=StreamPipe, level=OuterControl, cchain=x36824).name("x36850").ctrl(x36879).srcCtx("CellsPar.scala:175:46") // UnrolledForeach(List(Const(true)),x36824,Block(Const(())),List(List(b24531)),List(List(b24532)))
    val b24531 = CounterIter(x36823, Some(0)).name("b24531").ctrl(x36850) // b24531
    val b24532 = Const(true).name("b24532").ctrl(x36850) // b24532
    val b36973 = StreamOut(field="offset").name("b36973").ctrl(x36850).srcCtx("CellsPar.scala:175:46") // x36825 = StreamOutNew(BurstCmdBus)
    isAccum(b36973) = false
    bufferDepthOf(b36973) = 1
    val b36974 = StreamOut(field="size").name("b36974").ctrl(x36850).srcCtx("CellsPar.scala:175:46") // x36825 = StreamOutNew(BurstCmdBus)
    isAccum(b36974) = false
    bufferDepthOf(b36974) = 1
    val x36826 = StreamOut(field="data").name("x36826").ctrl(x36850).srcCtx("CellsPar.scala:175:46") // x36826 = StreamOutNew(BurstFullDataBus())
    isAccum(x36826) = false
    bufferDepthOf(x36826) = 1
    val x36827 = StreamIn(field="ack").name("x36827").ctrl(x36850).srcCtx("CellsPar.scala:175:46") // x36827 = StreamInNew(BurstAckBus)
    isAccum(x36827) = false
    bufferDepthOf(x36827) = 1
    val x36838 = UnitController(style=SeqPipe, level=InnerControl).name("x36838").ctrl(x36850).srcCtx("CellsPar.scala:175:46") // UnitPipe(List(b24532),Block(x36837))
    val x36828 = b24531 // FixConvert(b24531,TRUE,_32,_0) (Same Type. No op)
    val x36829 = OpDef(op=FixSla, inputs=List(x36828, Const(9))).name("x36829").ctrl(x36838).srcCtx("CellsPar.scala:175:46") // FixLsh(x36828,Const(9))
    val x36830 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x36831 = OpDef(op=FixAdd, inputs=List(x36829, x36830)).name("x36831").ctrl(x36838).srcCtx("CellsPar.scala:175:46") // FixAdd(x36829,x36830)
    val x36832 = OpDef(op=FixSla, inputs=List(x36831, Const(2))).name("x36832").ctrl(x36838).srcCtx("CellsPar.scala:175:46") // FixLsh(x36831,Const(2))
    val x36833 = x36832 // FixConvert(x36832,TRUE,_64,_0)
    val x36834 = DramAddress(x34674).name("x36834").ctrl(x36838).srcCtx("CellsPar.scala:175:46") // GetDRAMAddress(x34674)
    val x36836_x36835 = OpDef(op=FixAdd, inputs=List(x36833, x36834)).name("x36836_x36835").ctrl(x36838).srcCtx("CellsPar.scala:175:46") // FixAdd(x36833,x36834)
    // x36836 = SimpleStruct(ArrayBuffer((offset,x36835), (size,Const(2048)), (isLoad,Const(false))))
    val x36837_b36975_b36973 = WriteMem(b36973, x36836_x36835).name("x36837_b36975_b36973").ctrl(x36838).srcCtx("CellsPar.scala:175:46") // StreamWrite(x36825,x36836,b24532)
    val x36837_b36976_b36974 = WriteMem(b36974, Const(2048)).name("x36837_b36976_b36974").ctrl(x36838).srcCtx("CellsPar.scala:175:46") // StreamWrite(x36825,x36836,b24532)
    val x36839 = Counter(min=Const(0), max=Const(512), step=Const(1), par=1).name("x36839").ctrl(x36850).srcCtx("CellsPar.scala:175:46") // CounterNew(Const(0),Const(512),Const(1),Const(1))
    val x36840 = CounterChain(List(x36839)).name("x36840").ctrl(x36850).srcCtx("CellsPar.scala:175:46") // CounterChainNew(List(x36839))
    val x36846 = LoopController(style=InnerPipe, level=InnerControl, cchain=x36840).name("x36846").ctrl(x36850).srcCtx("CellsPar.scala:175:46") // UnrolledForeach(List(b24532),x36840,Block(Const(())),List(List(b24549)),List(List(b24550)))
    val b24549 = CounterIter(x36839, None).name("b24549").ctrl(x36846) // b24549
    val b24550 = Const(true).name("b24550").ctrl(x36846) // b24550
    val x36841 = OpDef(op=BitAnd, inputs=List(b24550, b24532)).name("x36841").ctrl(x36846).srcCtx("UnrollingBase.scala:28:66") // And(b24550,b24532)
    val x36842 = LoadBanks(List(x34773_d0_b0), List(b24531, b24549)).name("x36842").ctrl(x36846).srcCtx("CellsPar.scala:175:46") // ParSRAMLoad(x34773,List(List(b24531, b24549)),List(x36841))
    val x36844_x36843 = x36842 // x36843 = VectorApply(x36842,0)
    // x36844 = SimpleStruct(ArrayBuffer((_1,x36843), (_2,Const(true))))
    val x36845_x36845_x36826 = WriteMem(x36826, x36844_x36843).name("x36845_x36845_x36826").ctrl(x36846).srcCtx("CellsPar.scala:175:46") // ParStreamWrite(x36826,List(x36844),List(x36841))
    val x36847 = FringeDenseStore(dram=List(x34674), cmdStream=List(b36973, b36974), dataStream=List(x36826), ackStream=List(x36827)).name("x36847").ctrl(x36850).srcCtx("CellsPar.scala:175:46") // FringeDenseStore(x34674,x36825,x36826,x36827)
    val x36849 = UnitController(style=SeqPipe, level=InnerControl).name("x36849").ctrl(x36850).srcCtx("CellsPar.scala:175:46") // UnitPipe(List(b24532),Block(Const(())))
    val x36848_x36848 = ReadMem(x36827).name("x36848_x36848").ctrl(x36849).srcCtx("CellsPar.scala:175:46") // StreamRead(x36827,b24532)
    val x36851 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x36851").ctrl(x36879).srcCtx("CellsPar.scala:176:46") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x36852 = CounterChain(List(x36851)).name("x36852").ctrl(x36879).srcCtx("CellsPar.scala:176:46") // CounterChainNew(List(x36851))
    val x36878 = LoopController(style=StreamPipe, level=OuterControl, cchain=x36852).name("x36878").ctrl(x36879).srcCtx("CellsPar.scala:176:46") // UnrolledForeach(List(Const(true)),x36852,Block(Const(())),List(List(b24563)),List(List(b24564)))
    val b24563 = CounterIter(x36851, Some(0)).name("b24563").ctrl(x36878) // b24563
    val b24564 = Const(true).name("b24564").ctrl(x36878) // b24564
    val b36977 = StreamOut(field="offset").name("b36977").ctrl(x36878).srcCtx("CellsPar.scala:176:46") // x36853 = StreamOutNew(BurstCmdBus)
    isAccum(b36977) = false
    bufferDepthOf(b36977) = 1
    val b36978 = StreamOut(field="size").name("b36978").ctrl(x36878).srcCtx("CellsPar.scala:176:46") // x36853 = StreamOutNew(BurstCmdBus)
    isAccum(b36978) = false
    bufferDepthOf(b36978) = 1
    val x36854 = StreamOut(field="data").name("x36854").ctrl(x36878).srcCtx("CellsPar.scala:176:46") // x36854 = StreamOutNew(BurstFullDataBus())
    isAccum(x36854) = false
    bufferDepthOf(x36854) = 1
    val x36855 = StreamIn(field="ack").name("x36855").ctrl(x36878).srcCtx("CellsPar.scala:176:46") // x36855 = StreamInNew(BurstAckBus)
    isAccum(x36855) = false
    bufferDepthOf(x36855) = 1
    val x36866 = UnitController(style=SeqPipe, level=InnerControl).name("x36866").ctrl(x36878).srcCtx("CellsPar.scala:176:46") // UnitPipe(List(b24564),Block(x36865))
    val x36856 = b24563 // FixConvert(b24563,TRUE,_32,_0) (Same Type. No op)
    val x36857 = OpDef(op=FixSla, inputs=List(x36856, Const(9))).name("x36857").ctrl(x36866).srcCtx("CellsPar.scala:176:46") // FixLsh(x36856,Const(9))
    val x36858 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x36859 = OpDef(op=FixAdd, inputs=List(x36857, x36858)).name("x36859").ctrl(x36866).srcCtx("CellsPar.scala:176:46") // FixAdd(x36857,x36858)
    val x36860 = OpDef(op=FixSla, inputs=List(x36859, Const(2))).name("x36860").ctrl(x36866).srcCtx("CellsPar.scala:176:46") // FixLsh(x36859,Const(2))
    val x36861 = x36860 // FixConvert(x36860,TRUE,_64,_0)
    val x36862 = DramAddress(x34676).name("x36862").ctrl(x36866).srcCtx("CellsPar.scala:176:46") // GetDRAMAddress(x34676)
    val x36864_x36863 = OpDef(op=FixAdd, inputs=List(x36861, x36862)).name("x36864_x36863").ctrl(x36866).srcCtx("CellsPar.scala:176:46") // FixAdd(x36861,x36862)
    // x36864 = SimpleStruct(ArrayBuffer((offset,x36863), (size,Const(2048)), (isLoad,Const(false))))
    val x36865_b36979_b36977 = WriteMem(b36977, x36864_x36863).name("x36865_b36979_b36977").ctrl(x36866).srcCtx("CellsPar.scala:176:46") // StreamWrite(x36853,x36864,b24564)
    val x36865_b36980_b36978 = WriteMem(b36978, Const(2048)).name("x36865_b36980_b36978").ctrl(x36866).srcCtx("CellsPar.scala:176:46") // StreamWrite(x36853,x36864,b24564)
    val x36867 = Counter(min=Const(0), max=Const(512), step=Const(1), par=1).name("x36867").ctrl(x36878).srcCtx("CellsPar.scala:176:46") // CounterNew(Const(0),Const(512),Const(1),Const(1))
    val x36868 = CounterChain(List(x36867)).name("x36868").ctrl(x36878).srcCtx("CellsPar.scala:176:46") // CounterChainNew(List(x36867))
    val x36874 = LoopController(style=InnerPipe, level=InnerControl, cchain=x36868).name("x36874").ctrl(x36878).srcCtx("CellsPar.scala:176:46") // UnrolledForeach(List(b24564),x36868,Block(Const(())),List(List(b24581)),List(List(b24582)))
    val b24581 = CounterIter(x36867, None).name("b24581").ctrl(x36874) // b24581
    val b24582 = Const(true).name("b24582").ctrl(x36874) // b24582
    val x36869 = OpDef(op=BitAnd, inputs=List(b24582, b24564)).name("x36869").ctrl(x36874).srcCtx("UnrollingBase.scala:28:66") // And(b24582,b24564)
    val x36870 = LoadBanks(List(x34772_d0_b0), List(b24563, b24581)).name("x36870").ctrl(x36874).srcCtx("CellsPar.scala:176:46") // ParSRAMLoad(x34772,List(List(b24563, b24581)),List(x36869))
    val x36872_x36871 = x36870 // x36871 = VectorApply(x36870,0)
    // x36872 = SimpleStruct(ArrayBuffer((_1,x36871), (_2,Const(true))))
    val x36873_x36873_x36854 = WriteMem(x36854, x36872_x36871).name("x36873_x36873_x36854").ctrl(x36874).srcCtx("CellsPar.scala:176:46") // ParStreamWrite(x36854,List(x36872),List(x36869))
    val x36875 = FringeDenseStore(dram=List(x34676), cmdStream=List(b36977, b36978), dataStream=List(x36854), ackStream=List(x36855)).name("x36875").ctrl(x36878).srcCtx("CellsPar.scala:176:46") // FringeDenseStore(x34676,x36853,x36854,x36855)
    val x36877 = UnitController(style=SeqPipe, level=InnerControl).name("x36877").ctrl(x36878).srcCtx("CellsPar.scala:176:46") // UnitPipe(List(b24564),Block(Const(())))
    val x36876_x36876 = ReadMem(x36855).name("x36876_x36876").ctrl(x36877).srcCtx("CellsPar.scala:176:46") // StreamRead(x36855,b24564)
    }; split3
    }; split2
    }; split1
    
  }
}
